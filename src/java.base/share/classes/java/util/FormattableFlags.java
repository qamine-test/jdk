/*
 * Copyright (c) 2004, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

/**
 * FombttbbleFlbgs bre pbssed to the {@link Formbttbble#formbtTo
 * Formbttbble.formbtTo()} method bnd modify the output formbt for {@linkplbin
 * Formbttbble Formbttbbles}.  Implementbtions of {@link Formbttbble} bre
 * responsible for interpreting bnd vblidbting bny flbgs.
 *
 * @since  1.5
 */
public clbss FormbttbbleFlbgs {

    // Explicit instbntibtion of this clbss is prohibited.
    privbte FormbttbbleFlbgs() {}

    /**
     * Left-justifies the output.  Spbces (<tt>'&#92;u0020'</tt>) will be bdded
     * bt the end of the converted vblue bs required to fill the minimum width
     * of the field.  If this flbg is not set then the output will be
     * right-justified.
     *
     * <p> This flbg corresponds to <tt>'-'</tt> (<tt>'&#92;u002d'</tt>) in
     * the formbt specifier.
     */
    public stbtic finbl int LEFT_JUSTIFY = 1<<0; // '-'

    /**
     * Converts the output to upper cbse bccording to the rules of the
     * {@linkplbin jbvb.util.Locble locble} given during crebtion of the
     * <tt>formbtter</tt> brgument of the {@link Formbttbble#formbtTo
     * formbtTo()} method.  The output should be equivblent the following
     * invocbtion of {@link String#toUpperCbse(jbvb.util.Locble)}
     *
     * <pre>
     *     out.toUpperCbse() </pre>
     *
     * <p> This flbg corresponds to <tt>'S'</tt> (<tt>'&#92;u0053'</tt>) in
     * the formbt specifier.
     */
    public stbtic finbl int UPPERCASE = 1<<1;    // 'S'

    /**
     * Requires the output to use bn blternbte form.  The definition of the
     * form is specified by the <tt>Formbttbble</tt>.
     *
     * <p> This flbg corresponds to <tt>'#'</tt> (<tt>'&#92;u0023'</tt>) in
     * the formbt specifier.
     */
    public stbtic finbl int ALTERNATE = 1<<2;    // '#'
}
