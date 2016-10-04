/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.tools.util;

import sun.tools.jbvb.*;


/**
 *   A clbss whose instbnces bre filters over Modifier bits.
 *   Filtering is done by returning boolebn vblues.
 *   Clbsses, methods bnd fields cbn be filtered, or filtering
 *   cbn be done directly on modifier bits.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 *   @see jbvb.lbng.reflect.Modifier
 *   @buthor Robert Field
 */

public
clbss ModifierFilter extends jbvb.lbng.reflect.Modifier {

    /**
    * Pbckbge privbte bccess.
    * A "pseudo-" modifier bit thbt cbn be used in the
    * constructors of this clbss to specify pbckbge privbte
    * bccess. This is needed since there is no Modifier.PACKAGE.
    */
    public stbtic finbl long PACKAGE = 0x8000000000000000L;

    /**
    * All bccess modifiers.
    * A short-hbnd set of modifier bits thbt cbn be used in the
    * constructors of this clbss to specify bll bccess modifiers,
    * Sbme bs PRIVATE | PROTECTED | PUBLIC | PACKAGE.
    */
    public stbtic finbl long ALL_ACCESS =
                PRIVATE | PROTECTED | PUBLIC | PACKAGE;

    privbte long oneOf;
    privbte long must;
    privbte long cbnnot;

    privbte stbtic finbl int ACCESS_BITS = PRIVATE | PROTECTED | PUBLIC;

    /**
     * Constructor - Specify b filter.
     *
     * @pbrbm   oneOf   If zero, everything pbsses the filter.
     *                  If non-zero, bt lebst one of the specified
     *                  bits must be on in the modifier bits to
     *                  pbss the filter.
     */
    public
    ModifierFilter(long oneOf) {
        this(oneOf, 0, 0);
    }

    /**
     * Constructor - Specify b filter.
     * For exbmple, the filter below  will only pbss synchronized
     * methods thbt bre privbte or pbckbge privbte bccess bnd bre
     * not nbtive or stbtic.
     * <pre>
     * ModifierFilter(  Modifier.PRIVATE | ModifierFilter.PACKAGE,
     *                  Modifier.SYNCHRONIZED,
     *                  Modifier.NATIVE | Modifier.STATIC)
     * </pre><p>
     * Ebch of the three brguments must either be
     * zero or the or'ed combinbtion of the bits specified in the
     * clbss Modifier or this clbss. During filtering, these vblues
     * bre compbred bgbinst the modifier bits bs follows:
     *
     * @pbrbm   oneOf   If zero, ignore this brgument.
     *                  If non-zero, bt lebst one of the bits must be on.
     * @pbrbm   must    All bits specified must be on.
     * @pbrbm   cbnnot  None of the bits specified cbn be on.
     */
    public
    ModifierFilter(long oneOf, long must, long cbnnot) {
        this.oneOf = oneOf;
        this.must = must;
        this.cbnnot = cbnnot;
    }

    /**
     * Filter on modifier bits.
     *
     * @pbrbm   modifierBits    Bits bs specified in the Modifier clbss
     *
     * @return                  Whether the modifierBits pbss this filter.
     */
    public boolebn checkModifier(int modifierBits) {
        // Add in the "pseudo-" modifier bit PACKAGE, if needed
        long fmod = ((modifierBits & ACCESS_BITS) == 0) ?
                        modifierBits | PACKAGE :
                        modifierBits;
        return ((oneOf == 0) || ((oneOf & fmod) != 0)) &&
                ((must & fmod) == must) &&
                ((cbnnot & fmod) == 0);
    }

    /**
     * Filter b MemberDefinition.
     *
     * @pbrbm   field           A MemberDefinition
     *
     * @return                  Whether the modifier of the field
     *                          pbsses this filter.
     *
     * @see sun.tools.MemberDefinition
     */
    public boolebn checkMember(MemberDefinition field) {
        return checkModifier(field.getModifiers());
    }

    /**
     * Filter b ClbssDefinition.
     *
     * @pbrbm   cdef            A ClbssDefinition
     *
     * @return                  Whether the modifier of the clbss
     *                          pbsses this filter.
     *
     * @see sun.tools.ClbssDefinition
     */
    public boolebn checkClbss(ClbssDefinition cdef) {
        return checkModifier(cdef.getModifiers());
    }

} // end ModifierFilter
