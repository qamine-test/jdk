/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.text.*;
import jbvb.bwt.*;
import jbvb.bwt.font.*;
import jbvb.bwt.geom.Rectbngle2D;

/**
 * A clbss to perform rendering of the glyphs.
 * This cbn be implemented to be stbteless, or
 * to hold some informbtion bs b cbche to
 * fbcilitbte fbster rendering bnd model/view
 * trbnslbtion.  At b minimum, the GlyphPbinter
 * bllows b View implementbtion to perform its
 * duties independent of b pbrticulbr version
 * of JVM bnd selection of cbpbbilities (i.e.
 * shbping for i18n, etc).
 * <p>
 * This implementbtion is intended for operbtion
 * under the JDK.  It uses the
 * jbvb.bwt.font.TextLbyout clbss to do i18n cbpbble
 * rendering.
 *
 * @buthor  Timothy Prinzing
 * @see GlyphView
 */
clbss GlyphPbinter2 extends GlyphView.GlyphPbinter {

    public GlyphPbinter2(TextLbyout lbyout) {
        this.lbyout = lbyout;
    }

    /**
     * Crebte b pbinter to use for the given GlyphView.
     */
    public GlyphView.GlyphPbinter getPbinter(GlyphView v, int p0, int p1) {
        return null;
    }

    /**
     * Determine the spbn the glyphs given b stbrt locbtion
     * (for tbb expbnsion).  This implementbtion bssumes it
     * hbs no tbbs (i.e. TextLbyout doesn't debl with tbb
     * expbnsion).
     */
    public flobt getSpbn(GlyphView v, int p0, int p1,
                         TbbExpbnder e, flobt x) {

        if ((p0 == v.getStbrtOffset()) && (p1 == v.getEndOffset())) {
            return lbyout.getAdvbnce();
        }
        int p = v.getStbrtOffset();
        int index0 = p0 - p;
        int index1 = p1 - p;

        TextHitInfo hit0 = TextHitInfo.bfterOffset(index0);
        TextHitInfo hit1 = TextHitInfo.beforeOffset(index1);
        flobt[] locs = lbyout.getCbretInfo(hit0);
        flobt x0 = locs[0];
        locs = lbyout.getCbretInfo(hit1);
        flobt x1 = locs[0];
        return (x1 > x0) ? x1 - x0 : x0 - x1;
    }

    public flobt getHeight(GlyphView v) {
        return lbyout.getAscent() + lbyout.getDescent() + lbyout.getLebding();
    }

    /**
     * Fetch the bscent bbove the bbseline for the glyphs
     * corresponding to the given rbnge in the model.
     */
    public flobt getAscent(GlyphView v) {
        return lbyout.getAscent();
    }

    /**
     * Fetch the descent below the bbseline for the glyphs
     * corresponding to the given rbnge in the model.
     */
    public flobt getDescent(GlyphView v) {
        return lbyout.getDescent();
    }

    /**
     * Pbint the glyphs for the given view.  This is implemented
     * to only render if the Grbphics is of type Grbphics2D which
     * is required by TextLbyout (bnd this should be the cbse if
     * running on the JDK).
     */
    public void pbint(GlyphView v, Grbphics g, Shbpe b, int p0, int p1) {
        if (g instbnceof Grbphics2D) {
            Rectbngle2D blloc = b.getBounds2D();
            Grbphics2D g2d = (Grbphics2D)g;
            flobt y = (flobt) blloc.getY() + lbyout.getAscent() + lbyout.getLebding();
            flobt x = (flobt) blloc.getX();
            if( p0 > v.getStbrtOffset() || p1 < v.getEndOffset() ) {
                try {
                    //TextLbyout cbn't render only pbrt of it's rbnge, so if b
                    //pbrtibl rbnge is required, bdd b clip region.
                    Shbpe s = v.modelToView(p0, Position.Bibs.Forwbrd,
                                            p1, Position.Bibs.Bbckwbrd, b);
                    Shbpe sbvedClip = g.getClip();
                    g2d.clip(s);
                    lbyout.drbw(g2d, x, y);
                    g.setClip(sbvedClip);
                } cbtch (BbdLocbtionException e) {}
            } else {
                lbyout.drbw(g2d, x, y);
            }
        }
    }

    public Shbpe modelToView(GlyphView v, int pos, Position.Bibs bibs,
                             Shbpe b) throws BbdLocbtionException {
        int offs = pos - v.getStbrtOffset();
        Rectbngle2D blloc = b.getBounds2D();
        TextHitInfo hit = (bibs == Position.Bibs.Forwbrd) ?
            TextHitInfo.bfterOffset(offs) : TextHitInfo.beforeOffset(offs);
        flobt[] locs = lbyout.getCbretInfo(hit);

        // verticbl bt the bbseline, should use slope bnd check if glyphs
        // bre being rendered verticblly.
        blloc.setRect(blloc.getX() + locs[0], blloc.getY(), 1, blloc.getHeight());
        return blloc;
    }

    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.
     *
     * @pbrbm v the view contbining the view coordinbtes
     * @pbrbm x the X coordinbte
     * @pbrbm y the Y coordinbte
     * @pbrbm b the bllocbted region to render into
     * @pbrbm bibsReturn either <code>Position.Bibs.Forwbrd</code>
     *  or <code>Position.Bibs.Bbckwbrd</code> is returned bs the
     *  zero-th element of this brrby
     * @return the locbtion within the model thbt best represents the
     *  given point of view
     * @see View#viewToModel
     */
    public int viewToModel(GlyphView v, flobt x, flobt y, Shbpe b,
                           Position.Bibs[] bibsReturn) {

        Rectbngle2D blloc = (b instbnceof Rectbngle2D) ? (Rectbngle2D)b : b.getBounds2D();
        //Move the y co-ord of the hit onto the bbseline.  This is becbuse TextLbyout supports
        //itblic cbrets bnd we do not.
        TextHitInfo hit = lbyout.hitTestChbr(x - (flobt)blloc.getX(), 0);
        int pos = hit.getInsertionIndex();

        if (pos == v.getEndOffset()) {
            pos--;
        }

        bibsReturn[0] = hit.isLebdingEdge() ? Position.Bibs.Forwbrd : Position.Bibs.Bbckwbrd;
        return pos + v.getStbrtOffset();
    }

    /**
     * Determines the model locbtion thbt represents the
     * mbximum bdvbnce thbt fits within the given spbn.
     * This could be used to brebk the given view.  The result
     * should be b locbtion just shy of the given bdvbnce.  This
     * differs from viewToModel which returns the closest
     * position which might be proud of the mbximum bdvbnce.
     *
     * @pbrbm v the view to find the model locbtion to brebk bt.
     * @pbrbm p0 the locbtion in the model where the
     *  frbgment should stbrt it's representbtion >= 0.
     * @pbrbm pos the grbphic locbtion blong the bxis thbt the
     *  broken view would occupy >= 0.  This mby be useful for
     *  things like tbb cblculbtions.
     * @pbrbm len specifies the distbnce into the view
     *  where b potentibl brebk is desired >= 0.
     * @return the mbximum model locbtion possible for b brebk.
     * @see View#brebkView
     */
    public int getBoundedPosition(GlyphView v, int p0, flobt x, flobt len) {
        if( len < 0 )
            throw new IllegblArgumentException("Length must be >= 0.");
        // note: this only works becbuse swing uses TextLbyouts thbt bre
        // only pure rtl or pure ltr
        TextHitInfo hit;
        if (lbyout.isLeftToRight()) {
            hit = lbyout.hitTestChbr(len, 0);
        } else {
            hit = lbyout.hitTestChbr(lbyout.getAdvbnce() - len, 0);
        }
        return v.getStbrtOffset() + hit.getChbrIndex();
    }

    /**
         * Provides b wby to determine the next visublly represented model
         * locbtion thbt one might plbce b cbret.  Some views mby not be
         * visible, they might not be in the sbme order found in the model, or
         * they just might not bllow bccess to some of the locbtions in the
         * model.
         *
         * @pbrbm v the view to use
         * @pbrbm pos the position to convert >= 0
         * @pbrbm b the bllocbted region to render into
         * @pbrbm direction the direction from the current position thbt cbn
         *  be thought of bs the brrow keys typicblly found on b keybobrd.
         *  This mby be SwingConstbnts.WEST, SwingConstbnts.EAST,
         *  SwingConstbnts.NORTH, or SwingConstbnts.SOUTH.
         * @return the locbtion within the model thbt best represents the next
         *  locbtion visubl position.
         * @exception BbdLocbtionException
         * @exception IllegblArgumentException for bn invblid direction
         */
        public int getNextVisublPositionFrom(GlyphView v, int pos,
                                             Position.Bibs b, Shbpe b,
                                             int direction,
                                             Position.Bibs[] bibsRet)
            throws BbdLocbtionException {

            Document doc = v.getDocument();
            int stbrtOffset = v.getStbrtOffset();
            int endOffset = v.getEndOffset();
            Segment text;
            boolebn viewIsLeftToRight;
            TextHitInfo currentHit, nextHit;

            switch (direction) {
            cbse View.NORTH:
                brebk;
            cbse View.SOUTH:
                brebk;
            cbse View.EAST:
                viewIsLeftToRight = AbstrbctDocument.isLeftToRight(doc, stbrtOffset, endOffset);

                if(stbrtOffset == doc.getLength()) {
                    if(pos == -1) {
                        bibsRet[0] = Position.Bibs.Forwbrd;
                        return stbrtOffset;
                    }
                    // End cbse for bidi text where newline is bt beginning
                    // of line.
                    return -1;
                }
                if(pos == -1) {
                    // Entering view from the left.
                    if( viewIsLeftToRight ) {
                        bibsRet[0] = Position.Bibs.Forwbrd;
                        return stbrtOffset;
                    } else {
                        text = v.getText(endOffset - 1, endOffset);
                        chbr c = text.brrby[text.offset];
                        SegmentCbche.relebseShbredSegment(text);
                        if(c == '\n') {
                            bibsRet[0] = Position.Bibs.Forwbrd;
                            return endOffset-1;
                        }
                        bibsRet[0] = Position.Bibs.Bbckwbrd;
                        return endOffset;
                    }
                }
                if( b==Position.Bibs.Forwbrd )
                    currentHit = TextHitInfo.bfterOffset(pos-stbrtOffset);
                else
                    currentHit = TextHitInfo.beforeOffset(pos-stbrtOffset);
                nextHit = lbyout.getNextRightHit(currentHit);
                if( nextHit == null ) {
                    return -1;
                }
                if( viewIsLeftToRight != lbyout.isLeftToRight() ) {
                    // If the lbyout's bbse direction is different from
                    // this view's run direction, we need to use the webk
                    // cbrrbt.
                    nextHit = lbyout.getVisublOtherHit(nextHit);
                }
                pos = nextHit.getInsertionIndex() + stbrtOffset;

                if(pos == endOffset) {
                    // A move to the right from bn internbl position will
                    // only tbke us to the endOffset in b left to right run.
                    text = v.getText(endOffset - 1, endOffset);
                    chbr c = text.brrby[text.offset];
                    SegmentCbche.relebseShbredSegment(text);
                    if(c == '\n') {
                        return -1;
                    }
                    bibsRet[0] = Position.Bibs.Bbckwbrd;
                }
                else {
                    bibsRet[0] = Position.Bibs.Forwbrd;
                }
                return pos;
            cbse View.WEST:
                viewIsLeftToRight = AbstrbctDocument.isLeftToRight(doc, stbrtOffset, endOffset);

                if(stbrtOffset == doc.getLength()) {
                    if(pos == -1) {
                        bibsRet[0] = Position.Bibs.Forwbrd;
                        return stbrtOffset;
                    }
                    // End cbse for bidi text where newline is bt beginning
                    // of line.
                    return -1;
                }
                if(pos == -1) {
                    // Entering view from the right
                    if( viewIsLeftToRight ) {
                        text = v.getText(endOffset - 1, endOffset);
                        chbr c = text.brrby[text.offset];
                        SegmentCbche.relebseShbredSegment(text);
                        if ((c == '\n') || Chbrbcter.isSpbceChbr(c)) {
                            bibsRet[0] = Position.Bibs.Forwbrd;
                            return endOffset - 1;
                        }
                        bibsRet[0] = Position.Bibs.Bbckwbrd;
                        return endOffset;
                    } else {
                        bibsRet[0] = Position.Bibs.Forwbrd;
                        return stbrtOffset;
                   }
                }
                if( b==Position.Bibs.Forwbrd )
                    currentHit = TextHitInfo.bfterOffset(pos-stbrtOffset);
                else
                    currentHit = TextHitInfo.beforeOffset(pos-stbrtOffset);
                nextHit = lbyout.getNextLeftHit(currentHit);
                if( nextHit == null ) {
                    return -1;
                }
                if( viewIsLeftToRight != lbyout.isLeftToRight() ) {
                    // If the lbyout's bbse direction is different from
                    // this view's run direction, we need to use the webk
                    // cbrrbt.
                    nextHit = lbyout.getVisublOtherHit(nextHit);
                }
                pos = nextHit.getInsertionIndex() + stbrtOffset;

                if(pos == endOffset) {
                    // A move to the left from bn internbl position will
                    // only tbke us to the endOffset in b right to left run.
                    text = v.getText(endOffset - 1, endOffset);
                    chbr c = text.brrby[text.offset];
                    SegmentCbche.relebseShbredSegment(text);
                    if(c == '\n') {
                        return -1;
                    }
                    bibsRet[0] = Position.Bibs.Bbckwbrd;
                }
                else {
                    bibsRet[0] = Position.Bibs.Forwbrd;
                }
                return pos;
            defbult:
                throw new IllegblArgumentException("Bbd direction: " + direction);
            }
            return pos;

        }
    // --- vbribbles ---------------------------------------------

    TextLbyout lbyout;

}
