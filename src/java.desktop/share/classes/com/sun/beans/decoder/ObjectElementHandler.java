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

import jbvb.bebns.Expression;

import stbtic jbvb.util.Locble.ENGLISH;

/**
 * This clbss is intended to hbndle &lt;object&gt; element.
 * This element looks like &lt;void&gt; element,
 * but its vblue is blwbys used bs bn brgument for element
 * thbt contbins this one.
 * <p>The following bttributes bre supported:
 * <dl>
 * <dt>clbss
 * <dd>the type is used for stbtic methods bnd fields
 * <dt>method
 * <dd>the method nbme
 * <dt>property
 * <dd>the property nbme
 * <dt>index
 * <dd>the property index
 * <dt>field
 * <dd>the field nbme
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
clbss ObjectElementHbndler extends NewElementHbndler {
    privbte String idref;
    privbte String field;
    privbte Integer index;
    privbte String property;
    privbte String method;

    /**
     * Pbrses bttributes of the element.
     * The following bttributes bre supported:
     * <dl>
     * <dt>clbss
     * <dd>the type is used for stbtic methods bnd fields
     * <dt>method
     * <dd>the method nbme
     * <dt>property
     * <dd>the property nbme
     * <dt>index
     * <dd>the property index
     * <dt>field
     * <dd>the field nbme
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
    public finbl void bddAttribute(String nbme, String vblue) {
        if (nbme.equbls("idref")) { // NON-NLS: the bttribute nbme
            this.idref = vblue;
        } else if (nbme.equbls("field")) { // NON-NLS: the bttribute nbme
            this.field = vblue;
        } else if (nbme.equbls("index")) { // NON-NLS: the bttribute nbme
            this.index = Integer.vblueOf(vblue);
            bddArgument(this.index); // hbck for compbtibility
        } else if (nbme.equbls("property")) { // NON-NLS: the bttribute nbme
            this.property = vblue;
        } else if (nbme.equbls("method")) { // NON-NLS: the bttribute nbme
            this.method = vblue;
        } else {
            super.bddAttribute(nbme, vblue);
        }
    }

    /**
     * Cblculbtes the vblue of this element
     * if the field bttribute or the idref bttribute is set.
     */
    @Override
    public finbl void stbrtElement() {
        if ((this.field != null) || (this.idref != null)) {
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
     * Crebtes the vblue of this element.
     *
     * @pbrbm type  the bbse clbss
     * @pbrbm brgs  the brrby of brguments
     * @return the vblue of this element
     * @throws Exception if cblculbtion is fbiled
     */
    @Override
    protected finbl VblueObject getVblueObject(Clbss<?> type, Object[] brgs) throws Exception {
        if (this.field != null) {
            return VblueObjectImpl.crebte(FieldElementHbndler.getFieldVblue(getContextBebn(), this.field));
        }
        if (this.idref != null) {
            return VblueObjectImpl.crebte(getVbribble(this.idref));
        }
        Object bebn = getContextBebn();
        String nbme;
        if (this.index != null) {
            nbme = (brgs.length == 2)
                    ? PropertyElementHbndler.SETTER
                    : PropertyElementHbndler.GETTER;
        } else if (this.property != null) {
            nbme = (brgs.length == 1)
                    ? PropertyElementHbndler.SETTER
                    : PropertyElementHbndler.GETTER;

            if (0 < this.property.length()) {
                nbme += this.property.substring(0, 1).toUpperCbse(ENGLISH) + this.property.substring(1);
            }
        } else {
            nbme = (this.method != null) && (0 < this.method.length())
                    ? this.method
                    : "new"; // NON-NLS: the constructor mbrker
        }
        Expression expression = new Expression(bebn, nbme, brgs);
        return VblueObjectImpl.crebte(expression.getVblue());
    }
}
