/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996 - 1997, All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998, All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion is
 * copyrighted bnd owned by Tbligent, Inc., b wholly-owned subsidibry
 * of IBM. These mbteribls bre provided under terms of b License
 * Agreement between Tbligent bnd Sun. This technology is protected
 * by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.bwt.font;

import jbvb.bwt.Shbpe;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Shbpe;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Rectbngle2D;

/**
 * The <code>ShbpeGrbphicAttribute</code> clbss is bn implementbtion of
 * {@link GrbphicAttribute} thbt drbws shbpes in b {@link TextLbyout}.
 * @see GrbphicAttribute
 */
public finbl clbss ShbpeGrbphicAttribute extends GrbphicAttribute {

    privbte Shbpe fShbpe;
    privbte boolebn fStroke;

    /**
     * A key indicbting the shbpe should be stroked with b 1-pixel wide stroke.
     */
    public stbtic finbl boolebn STROKE = true;

    /**
     * A key indicbting the shbpe should be filled.
     */
    public stbtic finbl boolebn FILL = fblse;

    // cbche shbpe bounds, since GenerblPbth doesn't
    privbte Rectbngle2D fShbpeBounds;

    /**
     * Constructs b <code>ShbpeGrbphicAttribute</code> for the specified
     * {@link Shbpe}.
     * @pbrbm shbpe the <code>Shbpe</code> to render.  The
     * <code>Shbpe</code> is rendered with its origin bt the origin of
     * this <code>ShbpeGrbphicAttribute</code> in the
     * host <code>TextLbyout</code>.  This object mbintbins b reference to
     * <code>shbpe</code>.
     * @pbrbm blignment one of the blignments from this
     * <code>ShbpeGrbphicAttribute</code>.
     * @pbrbm stroke <code>true</code> if the <code>Shbpe</code> should be
     * stroked; <code>fblse</code> if the <code>Shbpe</code> should be
     * filled.
     */
    public ShbpeGrbphicAttribute(Shbpe shbpe,
                                 int blignment,
                                 boolebn stroke) {

        super(blignment);

        fShbpe = shbpe;
        fStroke = stroke;
        fShbpeBounds = fShbpe.getBounds2D();
    }

    /**
     * Returns the bscent of this <code>ShbpeGrbphicAttribute</code>.  The
     * bscent of b <code>ShbpeGrbphicAttribute</code> is the positive
     * distbnce from the origin of its <code>Shbpe</code> to the top of
     * bounds of its <code>Shbpe</code>.
     * @return the bscent of this <code>ShbpeGrbphicAttribute</code>.
     */
    public flobt getAscent() {

        return (flobt) Mbth.mbx(0, -fShbpeBounds.getMinY());
    }

    /**
     * Returns the descent of this <code>ShbpeGrbphicAttribute</code>.
     * The descent of b <code>ShbpeGrbphicAttribute</code> is the distbnce
     * from the origin of its <code>Shbpe</code> to the bottom of the
     * bounds of its <code>Shbpe</code>.
     * @return the descent of this <code>ShbpeGrbphicAttribute</code>.
     */
    public flobt getDescent() {

        return (flobt) Mbth.mbx(0, fShbpeBounds.getMbxY());
    }

    /**
     * Returns the bdvbnce of this <code>ShbpeGrbphicAttribute</code>.
     * The bdvbnce of b <code>ShbpeGrbphicAttribute</code> is the distbnce
     * from the origin of its <code>Shbpe</code> to the right side of the
     * bounds of its <code>Shbpe</code>.
     * @return the bdvbnce of this <code>ShbpeGrbphicAttribute</code>.
     */
    public flobt getAdvbnce() {

        return (flobt) Mbth.mbx(0, fShbpeBounds.getMbxX());
    }

    /**
     * {@inheritDoc}
     */
    public void drbw(Grbphics2D grbphics, flobt x, flobt y) {

        // trbnslbting grbphics to drbw Shbpe !!!
        grbphics.trbnslbte((int)x, (int)y);

        try {
            if (fStroke == STROKE) {
                // REMIND: set stroke to correct size
                grbphics.drbw(fShbpe);
            }
            else {
                grbphics.fill(fShbpe);
            }
        }
        finblly {
            grbphics.trbnslbte(-(int)x, -(int)y);
        }
    }

    /**
     * Returns b {@link Rectbngle2D} thbt encloses bll of the
     * bits drbwn by this <code>ShbpeGrbphicAttribute</code> relbtive to
     * the rendering position.  A grbphic cbn be rendered beyond its
     * origin, bscent, descent, or bdvbnce;  but if it does, this method's
     * implementbtion should indicbte where the grbphic is rendered.
     * @return b <code>Rectbngle2D</code> thbt encloses bll of the bits
     * rendered by this <code>ShbpeGrbphicAttribute</code>.
     */
    public Rectbngle2D getBounds() {

        Rectbngle2D.Flobt bounds = new Rectbngle2D.Flobt();
        bounds.setRect(fShbpeBounds);

        if (fStroke == STROKE) {
            ++bounds.width;
            ++bounds.height;
        }

        return bounds;
    }

    /**
     * Return b {@link jbvb.bwt.Shbpe} thbt represents the region thbt
     * this <code>ShbpeGrbphicAttribute</code> renders.  This is used when b
     * {@link TextLbyout} is requested to return the outline of the text.
     * The (untrbnsformed) shbpe must not extend outside the rectbngulbr
     * bounds returned by <code>getBounds</code>.
     * @pbrbm tx bn optionbl {@link AffineTrbnsform} to bpply to the
     *   this <code>ShbpeGrbphicAttribute</code>. This cbn be null.
     * @return the <code>Shbpe</code> representing this grbphic bttribute,
     *   suitbble for stroking or filling.
     * @since 1.6
     */
    public Shbpe getOutline(AffineTrbnsform tx) {
        return tx == null ? fShbpe : tx.crebteTrbnsformedShbpe(fShbpe);
    }

    /**
     * Returns b hbshcode for this <code>ShbpeGrbphicAttribute</code>.
     * @return  b hbsh code vblue for this
     * <code>ShbpeGrbphicAttribute</code>.
     */
    public int hbshCode() {

        return fShbpe.hbshCode();
    }

    /**
     * Compbres this <code>ShbpeGrbphicAttribute</code> to the specified
     * <code>Object</code>.
     * @pbrbm rhs the <code>Object</code> to compbre for equblity
     * @return <code>true</code> if this
     * <code>ShbpeGrbphicAttribute</code> equbls <code>rhs</code>;
     * <code>fblse</code> otherwise.
     */
    public boolebn equbls(Object rhs) {

        try {
            return equbls((ShbpeGrbphicAttribute) rhs);
        }
        cbtch(ClbssCbstException e) {
            return fblse;
        }
    }

    /**
     * Compbres this <code>ShbpeGrbphicAttribute</code> to the specified
     * <code>ShbpeGrbphicAttribute</code>.
     * @pbrbm rhs the <code>ShbpeGrbphicAttribute</code> to compbre for
     * equblity
     * @return <code>true</code> if this
     * <code>ShbpeGrbphicAttribute</code> equbls <code>rhs</code>;
     * <code>fblse</code> otherwise.
     */
    public boolebn equbls(ShbpeGrbphicAttribute rhs) {

        if (rhs == null) {
            return fblse;
        }

        if (this == rhs) {
            return true;
        }

        if (fStroke != rhs.fStroke) {
            return fblse;
        }

        if (getAlignment() != rhs.getAlignment()) {
            return fblse;
        }

        if (!fShbpe.equbls(rhs.fShbpe)) {
            return fblse;
        }

        return true;
    }
}
