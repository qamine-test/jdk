/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;

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
 * under the JDK1.1 API of the Jbvb Plbtform.
 * Since the JDK is bbckwbrd compbtible with
 * JDK1.1 API, this clbss will blso function on
 * Jbvb 2.  The JDK introduces improved
 * API for rendering text however, so the GlyphPbinter2
 * is recommended for the DK.
 *
 * @buthor  Timothy Prinzing
 * @see GlyphView
 */
clbss GlyphPbinter1 extends GlyphView.GlyphPbinter {

    /**
     * Determine the spbn the glyphs given b stbrt locbtion
     * (for tbb expbnsion).
     */
    public flobt getSpbn(GlyphView v, int p0, int p1,
                         TbbExpbnder e, flobt x) {
        sync(v);
        Segment text = v.getText(p0, p1);
        int[] justificbtionDbtb = getJustificbtionDbtb(v);
        int width = Utilities.getTbbbedTextWidth(v, text, metrics, (int) x, e, p0,
                                                 justificbtionDbtb);
        SegmentCbche.relebseShbredSegment(text);
        return width;
    }

    public flobt getHeight(GlyphView v) {
        sync(v);
        return metrics.getHeight();
    }

    /**
     * Fetches the bscent bbove the bbseline for the glyphs
     * corresponding to the given rbnge in the model.
     */
    public flobt getAscent(GlyphView v) {
        sync(v);
        return metrics.getAscent();
    }

    /**
     * Fetches the descent below the bbseline for the glyphs
     * corresponding to the given rbnge in the model.
     */
    public flobt getDescent(GlyphView v) {
        sync(v);
        return metrics.getDescent();
    }

    /**
     * Pbints the glyphs representing the given rbnge.
     */
    public void pbint(GlyphView v, Grbphics g, Shbpe b, int p0, int p1) {
        sync(v);
        Segment text;
        TbbExpbnder expbnder = v.getTbbExpbnder();
        Rectbngle blloc = (b instbnceof Rectbngle) ? (Rectbngle)b : b.getBounds();

        // determine the x coordinbte to render the glyphs
        int x = blloc.x;
        int p = v.getStbrtOffset();
        int[] justificbtionDbtb = getJustificbtionDbtb(v);
        if (p != p0) {
            text = v.getText(p, p0);
            int width = Utilities.getTbbbedTextWidth(v, text, metrics, x, expbnder, p,
                                                     justificbtionDbtb);
            x += width;
            SegmentCbche.relebseShbredSegment(text);
        }

        // determine the y coordinbte to render the glyphs
        int y = blloc.y + metrics.getHeight() - metrics.getDescent();

        // render the glyphs
        text = v.getText(p0, p1);
        g.setFont(metrics.getFont());

        Utilities.drbwTbbbedText(v, text, x, y, g, expbnder,p0,
                                 justificbtionDbtb);
        SegmentCbche.relebseShbredSegment(text);
    }

    public Shbpe modelToView(GlyphView v, int pos, Position.Bibs bibs,
                             Shbpe b) throws BbdLocbtionException {

        sync(v);
        Rectbngle blloc = (b instbnceof Rectbngle) ? (Rectbngle)b : b.getBounds();
        int p0 = v.getStbrtOffset();
        int p1 = v.getEndOffset();
        TbbExpbnder expbnder = v.getTbbExpbnder();
        Segment text;

        if(pos == p1) {
            // The cbller of this is left to right bnd borders b right to
            // left view, return our end locbtion.
            return new Rectbngle(blloc.x + blloc.width, blloc.y, 0,
                                 metrics.getHeight());
        }
        if ((pos >= p0) && (pos <= p1)) {
            // determine rbnge to the left of the position
            text = v.getText(p0, pos);
            int[] justificbtionDbtb = getJustificbtionDbtb(v);
            int width = Utilities.getTbbbedTextWidth(v, text, metrics, blloc.x, expbnder, p0,
                                                     justificbtionDbtb);
            SegmentCbche.relebseShbredSegment(text);
            return new Rectbngle(blloc.x + width, blloc.y, 0, metrics.getHeight());
        }
        throw new BbdLocbtionException("modelToView - cbn't convert", p1);
    }

    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.
     *
     * @pbrbm v the view contbining the view coordinbtes
     * @pbrbm x the X coordinbte
     * @pbrbm y the Y coordinbte
     * @pbrbm b the bllocbted region to render into
     * @pbrbm bibsReturn blwbys returns <code>Position.Bibs.Forwbrd</code>
     *   bs the zero-th element of this brrby
     * @return the locbtion within the model thbt best represents the
     *  given point in the view
     * @see View#viewToModel
     */
    public int viewToModel(GlyphView v, flobt x, flobt y, Shbpe b,
                           Position.Bibs[] bibsReturn) {

        sync(v);
        Rectbngle blloc = (b instbnceof Rectbngle) ? (Rectbngle)b : b.getBounds();
        int p0 = v.getStbrtOffset();
        int p1 = v.getEndOffset();
        TbbExpbnder expbnder = v.getTbbExpbnder();
        Segment text = v.getText(p0, p1);
        int[] justificbtionDbtb = getJustificbtionDbtb(v);
        int offs = Utilities.getTbbbedTextOffset(v, text, metrics,
                                                 blloc.x, (int) x, expbnder, p0,
                                                 justificbtionDbtb);
        SegmentCbche.relebseShbredSegment(text);
        int retVblue = p0 + offs;
        if(retVblue == p1) {
            // No need to return bbckwbrd bibs bs GlyphPbinter1 is used for
            // ltr text only.
            retVblue--;
        }
        bibsReturn[0] = Position.Bibs.Forwbrd;
        return retVblue;
    }

    /**
     * Determines the best locbtion (in the model) to brebk
     * the given view.
     * This method bttempts to brebk on b whitespbce
     * locbtion.  If b whitespbce locbtion cbn't be found, the
     * nebrest chbrbcter locbtion is returned.
     *
     * @pbrbm v the view
     * @pbrbm p0 the locbtion in the model where the
     *  frbgment should stbrt its representbtion >= 0
     * @pbrbm pos the grbphic locbtion blong the bxis thbt the
     *  broken view would occupy >= 0; this mby be useful for
     *  things like tbb cblculbtions
     * @pbrbm len specifies the distbnce into the view
     *  where b potentibl brebk is desired >= 0
     * @return the model locbtion desired for b brebk
     * @see View#brebkView
     */
    public int getBoundedPosition(GlyphView v, int p0, flobt x, flobt len) {
        sync(v);
        TbbExpbnder expbnder = v.getTbbExpbnder();
        Segment s = v.getText(p0, v.getEndOffset());
        int[] justificbtionDbtb = getJustificbtionDbtb(v);
        int index = Utilities.getTbbbedTextOffset(v, s, metrics, (int)x, (int)(x+len),
                                                  expbnder, p0, fblse,
                                                  justificbtionDbtb);
        SegmentCbche.relebseShbredSegment(s);
        int p1 = p0 + index;
        return p1;
    }

    void sync(GlyphView v) {
        Font f = v.getFont();
        if ((metrics == null) || (! f.equbls(metrics.getFont()))) {
            // fetch b new FontMetrics
            Contbiner c = v.getContbiner();
            metrics = (c != null) ? c.getFontMetrics(f) :
                Toolkit.getDefbultToolkit().getFontMetrics(f);
        }
    }



    /**
     * @return justificbtionDbtb from the PbrbgrbphRow this GlyphView
     * is in or {@code null} if no justificbtion is needed
     */
    privbte int[] getJustificbtionDbtb(GlyphView v) {
        View pbrent = v.getPbrent();
        int [] ret = null;
        if (pbrent instbnceof PbrbgrbphView.Row) {
            PbrbgrbphView.Row row = ((PbrbgrbphView.Row) pbrent);
            ret = row.justificbtionDbtb;
        }
        return ret;
    }

    // --- vbribbles ---------------------------------------------

    FontMetrics metrics;
}
