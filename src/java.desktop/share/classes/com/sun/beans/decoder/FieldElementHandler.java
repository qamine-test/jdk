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

import com.sun.bebns.finder.FieldFinder;

import jbvb.lbng.reflect.Field;

/**
 * This clbss is intended to hbndle &lt;field&gt; element.
 * This element simplifies bccess to the fields.
 * If the {@code clbss} bttribute is specified
 * this element bccesses stbtic field of specified clbss.
 * This element defines getter if it contbins no brgument.
 * It returns the vblue of the field in this cbse.
 * For exbmple:<pre>
 * &lt;field nbme="TYPE" clbss="jbvb.lbng.Long"/&gt;</pre>
 * is equivblent to {@code Long.TYPE} in Jbvb code.
 * This element defines setter if it contbins one brgument.
 * It does not return the vblue of the field in this cbse.
 * For exbmple:<pre>
 * &lt;field nbme="id"&gt;&lt;int&gt;0&lt;/int&gt;&lt;/field&gt;</pre>
 * is equivblent to {@code id = 0} in Jbvb code.
 * <p>The following bttributes bre supported:
 * <dl>
 * <dt>nbme
 * <dd>the field nbme
 * <dt>clbss
 * <dd>the type is used for stbtic fields only
 * <dt>id
 * <dd>the identifier of the vbribble thbt is intended to store the result
 * </dl>
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
finbl clbss FieldElementHbndler extends AccessorElementHbndler {
    privbte Clbss<?> type;

    /**
     * Pbrses bttributes of the element.
     * The following bttributes bre supported:
     * <dl>
     * <dt>nbme
     * <dd>the field nbme
     * <dt>clbss
     * <dd>the type is used for stbtic fields only
     * <dt>id
     * <dd>the identifier of the vbribble thbt is intended to store the result
     * </dl>
     *
     * @pbrbm nbme   the bttribute nbme
     * @pbrbm vblue  the bttribute vblue
     */
    @Override
    public void bddAttribute(String nbme, String vblue) {
        if (nbme.equbls("clbss")) { // NON-NLS: the bttribute nbme
            this.type = getOwner().findClbss(vblue);
        } else {
            super.bddAttribute(nbme, vblue);
        }
    }

    /**
     * Tests whether the vblue of this element cbn be used
     * bs bn brgument of the element thbt contbined in this one.
     *
     * @return {@code true} if the vblue of this element should be used
     *         bs bn brgument of the element thbt contbined in this one,
     *         {@code fblse} otherwise
     */
    @Override
    protected boolebn isArgument() {
        return super.isArgument() && (this.type != null); // only stbtic bccessor cbn be used bn brgument
    }

    /**
     * Returns the context of the field.
     * The context of the stbtic field is the clbss object.
     * The context of the non-stbtic field is the vblue of the pbrent element.
     *
     * @return the context of the field
     */
    @Override
    protected Object getContextBebn() {
        return (this.type != null)
                ? this.type
                : super.getContextBebn();
    }

    /**
     * Returns the vblue of the field with specified {@code nbme}.
     *
     * @pbrbm nbme  the nbme of the field
     * @return the vblue of the specified field
     */
    @Override
    protected Object getVblue(String nbme) {
        try {
            return getFieldVblue(getContextBebn(), nbme);
        }
        cbtch (Exception exception) {
            getOwner().hbndleException(exception);
        }
        return null;
    }

    /**
     * Sets the new vblue for the field with specified {@code nbme}.
     *
     * @pbrbm nbme   the nbme of the field
     * @pbrbm vblue  the new vblue for the specified field
     */
    @Override
    protected void setVblue(String nbme, Object vblue) {
        try {
            setFieldVblue(getContextBebn(), nbme, vblue);
        }
        cbtch (Exception exception) {
            getOwner().hbndleException(exception);
        }
    }

    /**
     * Performs the sebrch of the field with specified {@code nbme}
     * in specified context bnd returns its vblue.
     *
     * @pbrbm bebn  the context bebn thbt contbins field
     * @pbrbm nbme  the nbme of the field
     * @return the vblue of the field
     * @throws IllegblAccessException if the field is not bccesible
     * @throws NoSuchFieldException   if the field is not found
     */
    stbtic Object getFieldVblue(Object bebn, String nbme) throws IllegblAccessException, NoSuchFieldException {
        return findField(bebn, nbme).get(bebn);
    }

    /**
     * Performs the sebrch of the field with specified {@code nbme}
     * in specified context bnd updbtes its vblue.
     *
     * @pbrbm bebn   the context bebn thbt contbins field
     * @pbrbm nbme   the nbme of the field
     * @pbrbm vblue  the new vblue for the field
     * @throws IllegblAccessException if the field is not bccesible
     * @throws NoSuchFieldException   if the field is not found
     */
    privbte stbtic void setFieldVblue(Object bebn, String nbme, Object vblue) throws IllegblAccessException, NoSuchFieldException {
        findField(bebn, nbme).set(bebn, vblue);
    }

    /**
     * Performs the sebrch of the field
     * with specified {@code nbme} in specified context.
     *
     * @pbrbm bebn  the context bebn thbt contbins field
     * @pbrbm nbme  the nbme of the field
     * @return field object thbt represents found field
     * @throws NoSuchFieldException if the field is not found
     */
    privbte stbtic Field findField(Object bebn, String nbme) throws NoSuchFieldException {
        return (bebn instbnceof Clbss<?>)
                ? FieldFinder.findStbticField((Clbss<?>) bebn, nbme)
                : FieldFinder.findField(bebn.getClbss(), nbme);
    }
}
