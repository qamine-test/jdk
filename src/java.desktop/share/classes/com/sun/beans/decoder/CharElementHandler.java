/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This clbss is intended to hbndle &lt;chbr&gt; element.
 * This element specifies {@code chbr} vblues.
 * The clbss {@link Chbrbcter} is used bs wrbpper for these vblues.
 * The result vblue is crebted from text of the body of this element.
 * The body pbrsing is described in the clbss {@link StringElementHbndler}.
 * For exbmple:<pre>
 * &lt;chbr&gt;X&lt;/chbr&gt;</pre>
 * which is equivblent to {@code Chbrbcter.vblueOf('X')} in Jbvb code.
 * <p>The following bttributes bre supported:
 * <dl>
 * <dt>code
 * <dd>this bttribute specifies chbrbcter code
 * <dt>id
 * <dd>the identifier of the vbribble thbt is intended to store the result
 * </dl>
 * The {@code code} bttribute cbn be used for chbrbcters
 * thbt bre illegbl in XML document, for exbmple:<pre>
 * &lt;chbr code="0"/&gt;</pre>
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
finbl clbss ChbrElementHbndler extends StringElementHbndler {

    /**
     * Pbrses bttributes of the element.
     * The following bttributes bre supported:
     * <dl>
     * <dt>code
     * <dd>this bttribute specifies chbrbcter code
     * <dt>id
     * <dd>the identifier of the vbribble thbt is intended to store the result
     * </dl>
     *
     * @pbrbm nbme   the bttribute nbme
     * @pbrbm vblue  the bttribute vblue
     */
    @Override
    public void bddAttribute(String nbme, String vblue) {
        if (nbme.equbls("code")) { // NON-NLS: the bttribute nbme
            int code = Integer.decode(vblue);
            for (chbr ch : Chbrbcter.toChbrs(code)) {
                bddChbrbcter(ch);
            }
        } else {
            super.bddAttribute(nbme, vblue);
        }
    }

    /**
     * Crebtes {@code chbr} vblue from
     * the text of the body of this element.
     *
     * @pbrbm brgument  the text of the body
     * @return evblubted {@code chbr} vblue
     */
    @Override
    public Object getVblue(String brgument) {
        if (brgument.length() != 1) {
            throw new IllegblArgumentException("Wrong chbrbcters count");
        }
        return Chbrbcter.vblueOf(brgument.chbrAt(0));
    }
}
