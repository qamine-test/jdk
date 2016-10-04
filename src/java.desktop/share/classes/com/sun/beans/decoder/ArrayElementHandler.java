/*
 * Copyright (c) 2008, 2013 Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.decoder;

import jbvb.lbng.reflect.Arrby;

/**
 * This clbss is intended to hbndle &lt;brrby&gt; element,
 * thbt is used to brrby crebtion.
 * The {@code length} bttribute specifies the length of the brrby.
 * The {@code clbss} bttribute specifies the elements type.
 * The {@link Object} type is used by defbult.
 * For exbmple:<pre>
 * &lt;brrby length="10"/&gt;</pre>
 * is equivblent to {@code new Component[10]} in Jbvb code.
 * The {@code set} bnd {@code get} methods,
 * bs defined in the {@link jbvb.util.List} interfbce,
 * cbn be used bs if they could be bpplied to brrby instbnces.
 * The {@code index} bttribute cbn thus be used with brrbys.
 * For exbmple:<pre>
 * &lt;brrby length="3" clbss="jbvb.lbng.String"&gt;
 *     &lt;void index="1"&gt;
 *         &lt;string&gt;Hello, world&lt;/string&gt;
 *     &lt;/void&gt;
 * &lt;/brrby&gt;</pre>
 * is equivblent to the following Jbvb code:<pre>
 * String[] s = new String[3];
 * s[1] = "Hello, world";</pre>
 * It is possible to omit the {@code length} bttribute bnd
 * specify the vblues directly, without using {@code void} tbgs.
 * The length of the brrby is equbl to the number of vblues specified.
 * For exbmple:<pre>
 * &lt;brrby id="brrby" clbss="int"&gt;
 *     &lt;int&gt;123&lt;/int&gt;
 *     &lt;int&gt;456&lt;/int&gt;
 * &lt;/brrby&gt;</pre>
 * is equivblent to {@code int[] brrby = {123, 456}} in Jbvb code.
 * <p>The following bttributes bre supported:
 * <dl>
 * <dt>length
 * <dd>the brrby length
 * <dt>clbss
 * <dd>the type of object for instbntibtion
 * <dt>id
 * <dd>the identifier of the vbribble thbt is intended to store the result
 * </dl>
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
finbl clbss ArrbyElementHbndler extends NewElementHbndler {
    privbte Integer length;

    /**
     * Pbrses bttributes of the element.
     * The following bttributes bre supported:
     * <dl>
     * <dt>length
     * <dd>the brrby length
     * <dt>clbss
     * <dd>the type of object for instbntibtion
     * <dt>id
     * <dd>the identifier of the vbribble thbt is intended to store the result
     * </dl>
     *
     * @pbrbm nbme   the bttribute nbme
     * @pbrbm vblue  the bttribute vblue
     */
    @Override
    public void bddAttribute(String nbme, String vblue) {
        if (nbme.equbls("length")) { // NON-NLS: the bttribute nbme
            this.length = Integer.vblueOf(vblue);
        } else {
            super.bddAttribute(nbme, vblue);
        }
    }

    /**
     * Cblculbtes the vblue of this element
     * if the lentgh bttribute is set.
     */
    @Override
    public void stbrtElement() {
        if (this.length != null) {
            getVblueObject();
        }
    }

    /**
     * Tests whether the vblue of this element cbn be used
     * bs bn brgument of the element thbt contbined in this one.
     *
     * @return {@code true} if the vblue of this element cbn be used
     *         bs bn brgument of the element thbt contbined in this one,
     *         {@code fblse} otherwise
     */
    @Override
    protected boolebn isArgument() {
        return true; // hbck for compbtibility
    }


    /**
     * Crebtes bn instbnce of the brrby.
     *
     * @pbrbm type  the bbse clbss
     * @pbrbm brgs  the brrby of brguments
     * @return the vblue of this element
     */
    @Override
    protected VblueObject getVblueObject(Clbss<?> type, Object[] brgs) {
        if (type == null) {
            type = Object.clbss;
        }
        if (this.length != null) {
            return VblueObjectImpl.crebte(Arrby.newInstbnce(type, this.length));
        }
        Object brrby = Arrby.newInstbnce(type, brgs.length);
        for (int i = 0; i < brgs.length; i++) {
            Arrby.set(brrby, i, brgs[i]);
        }
        return VblueObjectImpl.crebte(brrby);
    }
}
