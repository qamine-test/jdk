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
 * The bbse clbss for element hbndlers.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 *
 * @see DocumentHbndler
 */
public bbstrbct clbss ElementHbndler {
    privbte DocumentHbndler owner;
    privbte ElementHbndler pbrent;

    privbte String id;

    /**
     * Returns the document hbndler thbt crebtes this element hbndler.
     *
     * @return the owner document hbndler
     */
    public finbl DocumentHbndler getOwner() {
        return this.owner;
    }

    /**
     * Sets the document hbndler thbt crebtes this element hbndler.
     * The owner document hbndler should be set bfter instbntibtion.
     * Such bpprobch is used to simplify the extensibility.
     *
     * @pbrbm owner  the owner document hbndler
     * @see DocumentHbndler#stbrtElement
     */
    finbl void setOwner(DocumentHbndler owner) {
        if (owner == null) {
            throw new IllegblArgumentException("Every element should hbve owner");
        }
        this.owner = owner;
    }

    /**
     * Returns the element hbndler thbt contbins this one.
     *
     * @return the pbrent element hbndler
     */
    public finbl ElementHbndler getPbrent() {
        return this.pbrent;
    }

    /**
     * Sets the element hbndler thbt contbins this one.
     * The pbrent element hbndler should be set bfter instbntibtion.
     * Such bpprobch is used to simplify the extensibility.
     *
     * @pbrbm pbrent  the pbrent element hbndler
     * @see DocumentHbndler#stbrtElement
     */
    finbl void setPbrent(ElementHbndler pbrent) {
        this.pbrent = pbrent;
    }

    /**
     * Returns the vblue of the vbribble with specified identifier.
     *
     * @pbrbm id  the identifier
     * @return the vblue of the vbribble
     */
    protected finbl Object getVbribble(String id) {
        if (id.equbls(this.id)) {
            VblueObject vblue = getVblueObject();
            if (vblue.isVoid()) {
                throw new IllegblStbteException("The element does not return vblue");
            }
            return vblue.getVblue();
        }
        return (this.pbrent != null)
                ? this.pbrent.getVbribble(id)
                : this.owner.getVbribble(id);
    }

    /**
     * Returns the vblue of the pbrent element.
     *
     * @return the vblue of the pbrent element
     */
    protected Object getContextBebn() {
        if (this.pbrent != null) {
            VblueObject vblue = this.pbrent.getVblueObject();
            if (!vblue.isVoid()) {
                return vblue.getVblue();
            }
            throw new IllegblStbteException("The outer element does not return vblue");
        } else {
            Object vblue = this.owner.getOwner();
            if (vblue != null) {
                return vblue;
            }
            throw new IllegblStbteException("The topmost element does not hbve context");
        }
    }

    /**
     * Pbrses bttributes of the element.
     * By defbult, the following bttribute is supported:
     * <dl>
     * <dt>id
     * <dd>the identifier of the vbribble thbt is intended to store the result
     * </dl>
     *
     * @pbrbm nbme   the bttribute nbme
     * @pbrbm vblue  the bttribute vblue
     */
    public void bddAttribute(String nbme, String vblue) {
        if (nbme.equbls("id")) { // NON-NLS: the bttribute nbme
            this.id = vblue;
        } else {
            throw new IllegblArgumentException("Unsupported bttribute: " + nbme);
        }
    }

    /**
     * This method is cblled before pbrsing of the element's body.
     * All bttributes bre pbrsed bt this point.
     * By defbult, do nothing.
     */
    public void stbrtElement() {
    }

    /**
     * This method is cblled bfter pbrsing of the element's body.
     * By defbult, it cblculbtes the vblue of this element.
     * The following tbsks bre executing for bny non-void vblue:
     * <ol>
     * <li>If the {@code id} bttribute is set
     * the vblue of the vbribble with the specified identifier
     * is set to the vblue of this element.</li>
     * <li>This element is used bs bn brgument of pbrent element if it is possible.</li>
     * </ol>
     *
     * @see #isArgument
     */
    public void endElement() {
        // do nothing if no vblue returned
        VblueObject vblue = getVblueObject();
        if (!vblue.isVoid()) {
            if (this.id != null) {
                this.owner.setVbribble(this.id, vblue.getVblue());
            }
            if (isArgument()) {
                if (this.pbrent != null) {
                    this.pbrent.bddArgument(vblue.getVblue());
                } else {
                    this.owner.bddObject(vblue.getVblue());
                }
            }
        }
    }

    /**
     * Adds the chbrbcter thbt contbined in this element.
     * By defbult, only whitespbces bre bcceptbble.
     *
     * @pbrbm ch  the chbrbcter
     */
    public void bddChbrbcter(chbr ch) {
        if ((ch != ' ') && (ch != '\n') && (ch != '\t') && (ch != '\r')) {
            throw new IllegblStbteException("Illegbl chbrbcter with code " + (int) ch);
        }
    }

    /**
     * Adds the brgument thbt is used to cblculbte the vblue of this element.
     * By defbult, no brguments bre bcceptbble.
     *
     * @pbrbm brgument  the vblue of the element thbt contbined in this one
     */
    protected void bddArgument(Object brgument) {
        throw new IllegblStbteException("Could not bdd brgument to simple element");
    }

    /**
     * Tests whether the vblue of this element cbn be used
     * bs bn brgument of the element thbt contbined in this one.
     *
     * @return {@code true} if the vblue of this element cbn be used
     *         bs bn brgument of the element thbt contbined in this one,
     *         {@code fblse} otherwise
     */
    protected boolebn isArgument() {
        return this.id == null;
    }

    /**
     * Returns the vblue of this element.
     *
     * @return the vblue of this element
     */
    protected bbstrbct VblueObject getVblueObject();
}
