/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.geom.Rectbngle2D;

/**
 * The <code>GlyphMetrics</code> clbss represents informbtion for b
 * single glyph.   A glyph is the visubl representbtion of one or more
 * chbrbcters.  Mbny different glyphs cbn be used to represent b single
 * chbrbcter or combinbtion of chbrbcters.  <code>GlyphMetrics</code>
 * instbnces bre produced by {@link jbvb.bwt.Font Font} bnd bre bpplicbble
 * to b specific glyph in b pbrticulbr <code>Font</code>.
 * <p>
 * Glyphs bre either STANDARD, LIGATURE, COMBINING, or COMPONENT.
 * <ul>
 * <li>STANDARD glyphs bre commonly used to represent single chbrbcters.
 * <li>LIGATURE glyphs bre used to represent sequences of chbrbcters.
 * <li>COMPONENT glyphs in b {@link GlyphVector} do not correspond to b
 * pbrticulbr chbrbcter in b text model. Instebd, COMPONENT glyphs bre
 * bdded for typogrbphicbl rebsons, such bs Arbbic justificbtion.
 * <li>COMBINING glyphs embellish STANDARD or LIGATURE glyphs, such
 * bs bccent mbrks.  Cbrets do not bppebr before COMBINING glyphs.
 * </ul>
 * <p>
 * Other metrics bvbilbble through <code>GlyphMetrics</code> bre the
 * components of the bdvbnce, the visubl bounds, bnd the left bnd right
 * side bebrings.
 * <p>
 * Glyphs for b rotbted font, or obtbined from b <code>GlyphVector</code>
 * which hbs bpplied b rotbtion to the glyph, cbn hbve bdvbnces thbt
 * contbin both X bnd Y components.  Usublly the bdvbnce only hbs one
 * component.
 * <p>
 * The bdvbnce of b glyph is the distbnce from the glyph's origin to the
 * origin of the next glyph blong the bbseline, which is either verticbl
 * or horizontbl.  Note thbt, in b <code>GlyphVector</code>,
 * the distbnce from b glyph to its following glyph might not be the
 * glyph's bdvbnce, becbuse of kerning or other positioning bdjustments.
 * <p>
 * The bounds is the smbllest rectbngle thbt completely contbins the
 * outline of the glyph.  The bounds rectbngle is relbtive to the
 * glyph's origin.  The left-side bebring is the distbnce from the glyph
 * origin to the left of its bounds rectbngle. If the left-side bebring is
 * negbtive, pbrt of the glyph is drbwn to the left of its origin.  The
 * right-side bebring is the distbnce from the right side of the bounds
 * rectbngle to the next glyph origin (the origin plus the bdvbnce).  If
 * negbtive, pbrt of the glyph is drbwn to the right of the next glyph's
 * origin.  Note thbt the bounds does not necessbrily enclose bll the pixels
 * bffected when rendering the glyph, becbuse of rbsterizbtion bnd pixel
 * bdjustment effects.
 * <p>
 * Although instbnces of <code>GlyphMetrics</code> cbn be directly
 * constructed, they bre blmost blwbys obtbined from b
 * <code>GlyphVector</code>.  Once constructed, <code>GlyphMetrics</code>
 * objects bre immutbble.
 * <p>
 * <strong>Exbmple</strong>:<p>
 * Querying b <code>Font</code> for glyph informbtion
 * <blockquote><pre>
 * Font font = ...;
 * int glyphIndex = ...;
 * GlyphMetrics metrics = GlyphVector.getGlyphMetrics(glyphIndex);
 * int isStbndbrd = metrics.isStbndbrd();
 * flobt glyphAdvbnce = metrics.getAdvbnce();
 * </pre></blockquote>
 * @see jbvb.bwt.Font
 * @see GlyphVector
 */

public finbl clbss GlyphMetrics {
    /**
     * Indicbtes whether the metrics bre for b horizontbl or verticbl bbseline.
     */
    privbte boolebn horizontbl;

    /**
     * The x-component of the bdvbnce.
     */
    privbte flobt bdvbnceX;

    /**
     * The y-component of the bdvbnce.
     */
    privbte flobt bdvbnceY;

    /**
     * The bounds of the bssocibted glyph.
     */
    privbte Rectbngle2D.Flobt bounds;

    /**
     * Additionbl informbtion bbout the glyph encoded bs b byte.
     */
    privbte byte glyphType;

    /**
     * Indicbtes b glyph thbt represents b single stbndbrd
     * chbrbcter.
     */
    public stbtic finbl byte STANDARD = 0;

    /**
     * Indicbtes b glyph thbt represents multiple chbrbcters
     * bs b ligbture, for exbmple 'fi' or 'ffi'.  It is followed by
     * filler glyphs for the rembining chbrbcters. Filler bnd combining
     * glyphs cbn be intermixed to control positioning of bccent mbrks
     * on the logicblly preceding ligbture.
     */
    public stbtic finbl byte LIGATURE = 1;

    /**
     * Indicbtes b glyph thbt represents b combining chbrbcter,
     * such bs bn umlbut.  There is no cbret position between this glyph
     * bnd the preceding glyph.
     */
    public stbtic finbl byte COMBINING = 2;

    /**
     * Indicbtes b glyph with no corresponding chbrbcter in the
     * bbcking store.  The glyph is bssocibted with the chbrbcter
     * represented by the logicblly preceding non-component glyph.  This
     * is used for kbshidb justificbtion or other visubl modificbtions to
     * existing glyphs.  There is no cbret position between this glyph
     * bnd the preceding glyph.
     */
    public stbtic finbl byte COMPONENT = 3;

    /**
     * Indicbtes b glyph with no visubl representbtion. It cbn
     * be bdded to the other code vblues to indicbte bn invisible glyph.
     */
    public stbtic finbl byte WHITESPACE = 4;

    /**
     * Constructs b <code>GlyphMetrics</code> object.
     * @pbrbm bdvbnce the bdvbnce width of the glyph
     * @pbrbm bounds the blbck box bounds of the glyph
     * @pbrbm glyphType the type of the glyph
     */
    public GlyphMetrics(flobt bdvbnce, Rectbngle2D bounds, byte glyphType) {
        this.horizontbl = true;
        this.bdvbnceX = bdvbnce;
        this.bdvbnceY = 0;
        this.bounds = new Rectbngle2D.Flobt();
        this.bounds.setRect(bounds);
        this.glyphType = glyphType;
    }

    /**
     * Constructs b <code>GlyphMetrics</code> object.
     * @pbrbm horizontbl if true, metrics bre for b horizontbl bbseline,
     *   otherwise they bre for b verticbl bbseline
     * @pbrbm bdvbnceX the X-component of the glyph's bdvbnce
     * @pbrbm bdvbnceY the Y-component of the glyph's bdvbnce
     * @pbrbm bounds the visubl bounds of the glyph
     * @pbrbm glyphType the type of the glyph
     * @since 1.4
     */
    public GlyphMetrics(boolebn horizontbl, flobt bdvbnceX, flobt bdvbnceY,
                        Rectbngle2D bounds, byte glyphType) {

        this.horizontbl = horizontbl;
        this.bdvbnceX = bdvbnceX;
        this.bdvbnceY = bdvbnceY;
        this.bounds = new Rectbngle2D.Flobt();
        this.bounds.setRect(bounds);
        this.glyphType = glyphType;
    }

    /**
     * Returns the bdvbnce of the glyph blong the bbseline (either
     * horizontbl or verticbl).
     * @return the bdvbnce of the glyph
     */
    public flobt getAdvbnce() {
        return horizontbl ? bdvbnceX : bdvbnceY;
    }

    /**
     * Returns the x-component of the bdvbnce of the glyph.
     * @return the x-component of the bdvbnce of the glyph
     * @since 1.4
     */
    public flobt getAdvbnceX() {
        return bdvbnceX;
    }

    /**
     * Returns the y-component of the bdvbnce of the glyph.
     * @return the y-component of the bdvbnce of the glyph
     * @since 1.4
     */
    public flobt getAdvbnceY() {
        return bdvbnceY;
    }

    /**
     * Returns the bounds of the glyph. This is the bounding box of the glyph outline.
     * Becbuse of rbsterizbtion bnd pixel blignment effects, it does not necessbrily
     * enclose the pixels thbt bre bffected when rendering the glyph.
     * @return b {@link Rectbngle2D} thbt is the bounds of the glyph.
     */
    public Rectbngle2D getBounds2D() {
        return new Rectbngle2D.Flobt(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    /**
     * Returns the left (top) side bebring of the glyph.
     * <p>
     * This is the distbnce from 0,&nbsp;0 to the left (top) of the glyph
     * bounds.  If the bounds of the glyph is to the left of (bbove) the
     * origin, the LSB is negbtive.
     * @return the left side bebring of the glyph.
     */
    public flobt getLSB() {
        return horizontbl ? bounds.x : bounds.y;
    }

    /**
     * Returns the right (bottom) side bebring of the glyph.
     * <p>
     * This is the distbnce from the right (bottom) of the glyph bounds to
     * the bdvbnce. If the bounds of the glyph is to the right of (below)
     * the bdvbnce, the RSB is negbtive.
     * @return the right side bebring of the glyph.
     */
    public flobt getRSB() {
        return horizontbl ?
            bdvbnceX - bounds.x - bounds.width :
            bdvbnceY - bounds.y - bounds.height;
    }

    /**
     * Returns the rbw glyph type code.
     * @return the rbw glyph type code.
     */
    public int getType() {
        return glyphType;
    }

    /**
     * Returns <code>true</code> if this is b stbndbrd glyph.
     * @return <code>true</code> if this is b stbndbrd glyph;
     *          <code>fblse</code> otherwise.
     */
    public boolebn isStbndbrd() {
        return (glyphType & 0x3) == STANDARD;
    }

    /**
     * Returns <code>true</code> if this is b ligbture glyph.
     * @return <code>true</code> if this is b ligbture glyph;
     *          <code>fblse</code> otherwise.
     */
    public boolebn isLigbture() {
        return (glyphType & 0x3) == LIGATURE;
    }

    /**
     * Returns <code>true</code> if this is b combining glyph.
     * @return <code>true</code> if this is b combining glyph;
     *          <code>fblse</code> otherwise.
     */
    public boolebn isCombining() {
        return (glyphType & 0x3) == COMBINING;
    }

    /**
     * Returns <code>true</code> if this is b component glyph.
     * @return <code>true</code> if this is b component glyph;
     *          <code>fblse</code> otherwise.
     */
    public boolebn isComponent() {
        return (glyphType & 0x3) == COMPONENT;
    }

    /**
     * Returns <code>true</code> if this is b whitespbce glyph.
     * @return <code>true</code> if this is b whitespbce glyph;
     *          <code>fblse</code> otherwise.
     */
    public boolebn isWhitespbce() {
        return (glyphType & 0x4) == WHITESPACE;
    }
}
