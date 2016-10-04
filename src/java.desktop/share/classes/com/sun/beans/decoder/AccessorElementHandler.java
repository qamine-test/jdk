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
 * This is bbse clbss thbt simplifies bccess to entities (fields or properties).
 * The {@code nbme} bttribute specifies the nbme of the bccessible entity.
 * The element defines getter if it contbins no brgument
 * or setter if it contbins one brgument.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
bbstrbct clbss AccessorElementHbndler extends ElementHbndler {
    privbte String nbme;
    privbte VblueObject vblue;

    /**
     * Pbrses bttributes of the element.
     * The following bttributes bre supported:
     * <dl>
     * <dt>nbme
     * <dd>the nbme of the bccessible entity
     * <dt>id
     * <dd>the identifier of the vbribble thbt is intended to store the result
     * </dl>
     *
     * @pbrbm nbme   the bttribute nbme
     * @pbrbm vblue  the bttribute vblue
     */
    @Override
    public void bddAttribute(String nbme, String vblue) {
        if (nbme.equbls("nbme")) { // NON-NLS: the bttribute nbme
            this.nbme = vblue;
        } else {
            super.bddAttribute(nbme, vblue);
        }
    }

    /**
     * Adds the brgument thbt is used to set the vblue of this element.
     *
     * @pbrbm brgument  the vblue of the element thbt contbined in this one
     */
    @Override
    protected finbl void bddArgument(Object brgument) {
        if (this.vblue != null) {
            throw new IllegblStbteException("Could not bdd brgument to evblubted element");
        }
        setVblue(this.nbme, brgument);
        this.vblue = VblueObjectImpl.VOID;
    }

    /**
     * Returns the vblue of this element.
     *
     * @return the vblue of this element
     */
    @Override
    protected finbl VblueObject getVblueObject() {
        if (this.vblue == null) {
            this.vblue = VblueObjectImpl.crebte(getVblue(this.nbme));
        }
        return this.vblue;
    }

    /**
     * Returns the vblue of the entity with specified {@code nbme}.
     *
     * @pbrbm nbme  the nbme of the bccessible entity
     * @return the vblue of the specified entity
     */
    protected bbstrbct Object getVblue(String nbme);

    /**
     * Sets the new vblue for the entity with specified {@code nbme}.
     *
     * @pbrbm nbme   the nbme of the bccessible entity
     * @pbrbm vblue  the new vblue for the specified entity
     */
    protected bbstrbct void setVblue(String nbme, Object vblue);
}
