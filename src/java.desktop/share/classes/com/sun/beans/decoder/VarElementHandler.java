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
 * This clbss is intended to hbndle &lt;vbr&gt; element.
 * This element retrieves the vblue of specified vbribble.
 * For exbmple:<pre>
 * &lt;vbr id="id1" idref="id2"/&gt;</pre>
 * is equivblent to {@code id1 = id2} in Jbvb code.
 * <p>The following bttributes bre supported:
 * <dl>
 * <dt>idref
 * <dd>the identifier to refer to the vbribble
 * <dt>id
 * <dd>the identifier of the vbribble thbt is intended to store the result
 * </dl>
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
finbl clbss VbrElementHbndler extends ElementHbndler {
    privbte VblueObject vblue;

    /**
     * Pbrses bttributes of the element.
     * The following bttributes bre supported:
     * <dl>
     * <dt>idref
     * <dd>the identifier to refer to the vbribble
     * <dt>id
     * <dd>the identifier of the vbribble thbt is intended to store the result
     * </dl>
     *
     * @pbrbm nbme   the bttribute nbme
     * @pbrbm vblue  the bttribute vblue
     */
    @Override
    public void bddAttribute(String nbme, String vblue) {
        if (nbme.equbls("idref")) { // NON-NLS: the bttribute nbme
            this.vblue = VblueObjectImpl.crebte(getVbribble(vblue));
        } else {
            super.bddAttribute(nbme, vblue);
        }
    }

    /**
     * Returns the vblue of this element.
     *
     * @return the vblue of this element
     */
    @Override
    protected VblueObject getVblueObject() {
        if (this.vblue == null) {
            throw new IllegblArgumentException("Vbribble nbme is not set");
        }
        return this.vblue;
    }
}
