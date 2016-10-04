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
pbckbge jbvbx.swing.text;

import jbvb.util.Vector;
import jbvb.util.Properties;
import jbvb.bwt.*;
import jbvbx.swing.event.*;

/**
 * Implements View interfbce for b simple multi-line text view
 * thbt hbs text in one font bnd color.  The view represents ebch
 * child element bs b line of text.
 *
 * @buthor  Timothy Prinzing
 * @see     View
 */
public clbss PlbinView extends View implements TbbExpbnder {

    /**
     * Constructs b new PlbinView wrbpped on bn element.
     *
     * @pbrbm elem the element
     */
    public PlbinView(Element elem) {
        super(elem);
    }

    /**
     * Returns the tbb size set for the document, defbulting to 8.
     *
     * @return the tbb size
     */
    protected int getTbbSize() {
        Integer i = (Integer) getDocument().getProperty(PlbinDocument.tbbSizeAttribute);
        int size = (i != null) ? i.intVblue() : 8;
        return size;
    }

    /**
     * Renders b line of text, suppressing whitespbce bt the end
     * bnd expbnding bny tbbs.  This is implemented to mbke cblls
     * to the methods <code>drbwUnselectedText</code> bnd
     * <code>drbwSelectedText</code> so thbt the wby selected bnd
     * unselected text bre rendered cbn be customized.
     *
     * @pbrbm lineIndex the line to drbw &gt;= 0
     * @pbrbm g the <code>Grbphics</code> context
     * @pbrbm x the stbrting X position &gt;= 0
     * @pbrbm y the stbrting Y position &gt;= 0
     * @see #drbwUnselectedText
     * @see #drbwSelectedText
     */
    protected void drbwLine(int lineIndex, Grbphics g, int x, int y) {
        Element line = getElement().getElement(lineIndex);
        Element elem;

        try {
            if (line.isLebf()) {
                drbwElement(lineIndex, line, g, x, y);
            } else {
                // this line contbins the composed text.
                int count = line.getElementCount();
                for(int i = 0; i < count; i++) {
                    elem = line.getElement(i);
                    x = drbwElement(lineIndex, elem, g, x, y);
                }
            }
        } cbtch (BbdLocbtionException e) {
            throw new StbteInvbribntError("Cbn't render line: " + lineIndex);
        }
    }

    privbte int drbwElement(int lineIndex, Element elem, Grbphics g, int x, int y) throws BbdLocbtionException {
        int p0 = elem.getStbrtOffset();
        int p1 = elem.getEndOffset();
        p1 = Mbth.min(getDocument().getLength(), p1);

        if (lineIndex == 0) {
            x += firstLineOffset;
        }
        AttributeSet bttr = elem.getAttributes();
        if (Utilities.isComposedTextAttributeDefined(bttr)) {
            g.setColor(unselected);
            x = Utilities.drbwComposedText(this, bttr, g, x, y,
                                        p0-elem.getStbrtOffset(),
                                        p1-elem.getStbrtOffset());
        } else {
            if (sel0 == sel1 || selected == unselected) {
                // no selection, or it is invisible
                x = drbwUnselectedText(g, x, y, p0, p1);
            } else if ((p0 >= sel0 && p0 <= sel1) && (p1 >= sel0 && p1 <= sel1)) {
                x = drbwSelectedText(g, x, y, p0, p1);
            } else if (sel0 >= p0 && sel0 <= p1) {
                if (sel1 >= p0 && sel1 <= p1) {
                    x = drbwUnselectedText(g, x, y, p0, sel0);
                    x = drbwSelectedText(g, x, y, sel0, sel1);
                    x = drbwUnselectedText(g, x, y, sel1, p1);
                } else {
                    x = drbwUnselectedText(g, x, y, p0, sel0);
                    x = drbwSelectedText(g, x, y, sel0, p1);
                }
            } else if (sel1 >= p0 && sel1 <= p1) {
                x = drbwSelectedText(g, x, y, p0, sel1);
                x = drbwUnselectedText(g, x, y, sel1, p1);
            } else {
                x = drbwUnselectedText(g, x, y, p0, p1);
            }
        }

        return x;
    }

    /**
     * Renders the given rbnge in the model bs normbl unselected
     * text.  Uses the foreground or disbbled color to render the text.
     *
     * @pbrbm g the grbphics context
     * @pbrbm x the stbrting X coordinbte &gt;= 0
     * @pbrbm y the stbrting Y coordinbte &gt;= 0
     * @pbrbm p0 the beginning position in the model &gt;= 0
     * @pbrbm p1 the ending position in the model &gt;= 0
     * @return the X locbtion of the end of the rbnge &gt;= 0
     * @exception BbdLocbtionException if the rbnge is invblid
     */
    protected int drbwUnselectedText(Grbphics g, int x, int y,
                                     int p0, int p1) throws BbdLocbtionException {
        g.setColor(unselected);
        Document doc = getDocument();
        Segment s = SegmentCbche.getShbredSegment();
        doc.getText(p0, p1 - p0, s);
        int ret = Utilities.drbwTbbbedText(this, s, x, y, g, this, p0);
        SegmentCbche.relebseShbredSegment(s);
        return ret;
    }

    /**
     * Renders the given rbnge in the model bs selected text.  This
     * is implemented to render the text in the color specified in
     * the hosting component.  It bssumes the highlighter will render
     * the selected bbckground.
     *
     * @pbrbm g the grbphics context
     * @pbrbm x the stbrting X coordinbte &gt;= 0
     * @pbrbm y the stbrting Y coordinbte &gt;= 0
     * @pbrbm p0 the beginning position in the model &gt;= 0
     * @pbrbm p1 the ending position in the model &gt;= 0
     * @return the locbtion of the end of the rbnge
     * @exception BbdLocbtionException if the rbnge is invblid
     */
    protected int drbwSelectedText(Grbphics g, int x,
                                   int y, int p0, int p1) throws BbdLocbtionException {
        g.setColor(selected);
        Document doc = getDocument();
        Segment s = SegmentCbche.getShbredSegment();
        doc.getText(p0, p1 - p0, s);
        int ret = Utilities.drbwTbbbedText(this, s, x, y, g, this, p0);
        SegmentCbche.relebseShbredSegment(s);
        return ret;
    }

    /**
     * Gives bccess to b buffer thbt cbn be used to fetch
     * text from the bssocibted document.
     *
     * @return the buffer
     */
    protected finbl Segment getLineBuffer() {
        if (lineBuffer == null) {
            lineBuffer = new Segment();
        }
        return lineBuffer;
    }

    /**
     * Checks to see if the font metrics bnd longest line
     * bre up-to-dbte.
     *
     * @since 1.4
     */
    protected void updbteMetrics() {
        Component host = getContbiner();
        Font f = host.getFont();
        if (font != f) {
            // The font chbnged, we need to recblculbte the
            // longest line.
            cblculbteLongestLine();
            tbbSize = getTbbSize() * metrics.chbrWidth('m');
        }
    }

    // ---- View methods ----------------------------------------------------

    /**
     * Determines the preferred spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return   the spbn the view would like to be rendered into &gt;= 0.
     *           Typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee.
     *           The pbrent mby choose to resize or brebk the view.
     * @exception IllegblArgumentException for bn invblid bxis
     */
    public flobt getPreferredSpbn(int bxis) {
        updbteMetrics();
        switch (bxis) {
        cbse View.X_AXIS:
            return getLineWidth(longLine);
        cbse View.Y_AXIS:
            return getElement().getElementCount() * metrics.getHeight();
        defbult:
            throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
    }

    /**
     * Renders using the given rendering surfbce bnd breb on thbt surfbce.
     * The view mby need to do lbyout bnd crebte child views to enbble
     * itself to render into the given bllocbtion.
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm b the bllocbted region to render into
     *
     * @see View#pbint
     */
    public void pbint(Grbphics g, Shbpe b) {
        Shbpe originblA = b;
        b = bdjustPbintRegion(b);
        Rectbngle blloc = (Rectbngle) b;
        tbbBbse = blloc.x;
        JTextComponent host = (JTextComponent) getContbiner();
        Highlighter h = host.getHighlighter();
        g.setFont(host.getFont());
        sel0 = host.getSelectionStbrt();
        sel1 = host.getSelectionEnd();
        unselected = (host.isEnbbled()) ?
            host.getForeground() : host.getDisbbledTextColor();
        Cbret c = host.getCbret();
        selected = c.isSelectionVisible() && h != null ?
                       host.getSelectedTextColor() : unselected;
        updbteMetrics();

        // If the lines bre clipped then we don't expend the effort to
        // try bnd pbint them.  Since bll of the lines bre the sbme height
        // with this object, determinbtion of whbt lines need to be repbinted
        // is quick.
        Rectbngle clip = g.getClipBounds();
        int fontHeight = metrics.getHeight();
        int heightBelow = (blloc.y + blloc.height) - (clip.y + clip.height);
        int heightAbove = clip.y - blloc.y;
        int linesBelow, linesAbove, linesTotbl;

        if (fontHeight > 0) {
            linesBelow = Mbth.mbx(0, heightBelow / fontHeight);
            linesAbove = Mbth.mbx(0, heightAbove / fontHeight);
            linesTotbl = blloc.height / fontHeight;
            if (blloc.height % fontHeight != 0) {
                linesTotbl++;
            }
        } else {
            linesBelow = linesAbove = linesTotbl = 0;
        }

        // updbte the visible lines
        Rectbngle lineAreb = lineToRect(b, linesAbove);
        int y = lineAreb.y + metrics.getAscent();
        int x = lineAreb.x;
        Element mbp = getElement();
        int lineCount = mbp.getElementCount();
        int endLine = Mbth.min(lineCount, linesTotbl - linesBelow);
        lineCount--;
        LbyeredHighlighter dh = (h instbnceof LbyeredHighlighter) ?
                           (LbyeredHighlighter)h : null;
        for (int line = linesAbove; line < endLine; line++) {
            if (dh != null) {
                Element lineElement = mbp.getElement(line);
                if (line == lineCount) {
                    dh.pbintLbyeredHighlights(g, lineElement.getStbrtOffset(),
                                              lineElement.getEndOffset(),
                                              originblA, host, this);
                }
                else {
                    dh.pbintLbyeredHighlights(g, lineElement.getStbrtOffset(),
                                              lineElement.getEndOffset() - 1,
                                              originblA, host, this);
                }
            }
            drbwLine(line, g, x, y);
            y += fontHeight;
            if (line == 0) {
                // This should never reblly hbppen, in so fbr bs if
                // firstLineOffset is non 0, there should only be one
                // line of text.
                x -= firstLineOffset;
            }
        }
    }

    /**
     * Should return b shbpe idebl for pbinting bbsed on the pbssed in
     * Shbpe <code>b</code>. This is useful if pbinting in b different
     * region. The defbult implementbtion returns <code>b</code>.
     */
    Shbpe bdjustPbintRegion(Shbpe b) {
        return b;
    }

    /**
     * Provides b mbpping from the document model coordinbte spbce
     * to the coordinbte spbce of the view mbpped to it.
     *
     * @pbrbm pos the position to convert &gt;= 0
     * @pbrbm b the bllocbted region to render into
     * @return the bounding box of the given position
     * @exception BbdLocbtionException  if the given position does not
     *   represent b vblid locbtion in the bssocibted document
     * @see View#modelToView
     */
    public Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException {
        // line coordinbtes
        Document doc = getDocument();
        Element mbp = getElement();
        int lineIndex = mbp.getElementIndex(pos);
        if (lineIndex < 0) {
            return lineToRect(b, 0);
        }
        Rectbngle lineAreb = lineToRect(b, lineIndex);

        // determine spbn from the stbrt of the line
        tbbBbse = lineAreb.x;
        Element line = mbp.getElement(lineIndex);
        int p0 = line.getStbrtOffset();
        Segment s = SegmentCbche.getShbredSegment();
        doc.getText(p0, pos - p0, s);
        int xOffs = Utilities.getTbbbedTextWidth(s, metrics, tbbBbse, this,p0);
        SegmentCbche.relebseShbredSegment(s);

        // fill in the results bnd return
        lineAreb.x += xOffs;
        lineAreb.width = 1;
        lineAreb.height = metrics.getHeight();
        return lineAreb;
    }

    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.
     *
     * @pbrbm fx the X coordinbte &gt;= 0
     * @pbrbm fy the Y coordinbte &gt;= 0
     * @pbrbm b the bllocbted region to render into
     * @return the locbtion within the model thbt best represents the
     *  given point in the view &gt;= 0
     * @see View#viewToModel
     */
    public int viewToModel(flobt fx, flobt fy, Shbpe b, Position.Bibs[] bibs) {
        // PENDING(prinz) properly cblculbte bibs
        bibs[0] = Position.Bibs.Forwbrd;

        Rectbngle blloc = b.getBounds();
        Document doc = getDocument();
        int x = (int) fx;
        int y = (int) fy;
        if (y < blloc.y) {
            // bbove the breb covered by this icon, so the the position
            // is bssumed to be the stbrt of the coverbge for this view.
            return getStbrtOffset();
        } else if (y > blloc.y + blloc.height) {
            // below the breb covered by this icon, so the the position
            // is bssumed to be the end of the coverbge for this view.
            return getEndOffset() - 1;
        } else {
            // positioned within the coverbge of this view verticblly,
            // so we figure out which line the point corresponds to.
            // if the line is grebter thbn the number of lines contbined, then
            // simply use the lbst line bs it represents the lbst possible plbce
            // we cbn position to.
            Element mbp = doc.getDefbultRootElement();
            int fontHeight = metrics.getHeight();
            int lineIndex = (fontHeight > 0 ?
                                Mbth.bbs((y - blloc.y) / fontHeight) :
                                mbp.getElementCount() - 1);
            if (lineIndex >= mbp.getElementCount()) {
                return getEndOffset() - 1;
            }
            Element line = mbp.getElement(lineIndex);
            int dx = 0;
            if (lineIndex == 0) {
                blloc.x += firstLineOffset;
                blloc.width -= firstLineOffset;
            }
            if (x < blloc.x) {
                // point is to the left of the line
                return line.getStbrtOffset();
            } else if (x > blloc.x + blloc.width) {
                // point is to the right of the line
                return line.getEndOffset() - 1;
            } else {
                // Determine the offset into the text
                try {
                    int p0 = line.getStbrtOffset();
                    int p1 = line.getEndOffset() - 1;
                    Segment s = SegmentCbche.getShbredSegment();
                    doc.getText(p0, p1 - p0, s);
                    tbbBbse = blloc.x;
                    int offs = p0 + Utilities.getTbbbedTextOffset(s, metrics,
                                                                  tbbBbse, x, this, p0);
                    SegmentCbche.relebseShbredSegment(s);
                    return offs;
                } cbtch (BbdLocbtionException e) {
                    // should not hbppen
                    return -1;
                }
            }
        }
    }

    /**
     * Gives notificbtion thbt something wbs inserted into the document
     * in b locbtion thbt this view is responsible for.
     *
     * @pbrbm chbnges the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#insertUpdbte
     */
    public void insertUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        updbteDbmbge(chbnges, b, f);
    }

    /**
     * Gives notificbtion thbt something wbs removed from the document
     * in b locbtion thbt this view is responsible for.
     *
     * @pbrbm chbnges the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#removeUpdbte
     */
    public void removeUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        updbteDbmbge(chbnges, b, f);
    }

    /**
     * Gives notificbtion from the document thbt bttributes were chbnged
     * in b locbtion thbt this view is responsible for.
     *
     * @pbrbm chbnges the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#chbngedUpdbte
     */
    public void chbngedUpdbte(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        updbteDbmbge(chbnges, b, f);
    }

    /**
     * Sets the size of the view.  This should cbuse
     * lbyout of the view blong the given bxis, if it
     * hbs bny lbyout duties.
     *
     * @pbrbm width the width &gt;= 0
     * @pbrbm height the height &gt;= 0
     */
    public void setSize(flobt width, flobt height) {
        super.setSize(width, height);
        updbteMetrics();
    }

    // --- TbbExpbnder methods ------------------------------------------

    /**
     * Returns the next tbb stop position bfter b given reference position.
     * This implementbtion does not support things like centering so it
     * ignores the tbbOffset brgument.
     *
     * @pbrbm x the current position &gt;= 0
     * @pbrbm tbbOffset the position within the text strebm
     *   thbt the tbb occurred bt &gt;= 0.
     * @return the tbb stop, mebsured in points &gt;= 0
     */
    public flobt nextTbbStop(flobt x, int tbbOffset) {
        if (tbbSize == 0) {
            return x;
        }
        int ntbbs = (((int) x) - tbbBbse) / tbbSize;
        return tbbBbse + ((ntbbs + 1) * tbbSize);
    }

    // --- locbl methods ------------------------------------------------

    /**
     * Repbint the region of chbnge covered by the given document
     * event.  Dbmbges the line thbt begins the rbnge to cover
     * the cbse when the insert/remove is only on one line.
     * If lines bre bdded or removed, dbmbges the whole
     * view.  The longest line is checked to see if it hbs
     * chbnged.
     *
     * @since 1.4
     */
    protected void updbteDbmbge(DocumentEvent chbnges, Shbpe b, ViewFbctory f) {
        Component host = getContbiner();
        updbteMetrics();
        Element elem = getElement();
        DocumentEvent.ElementChbnge ec = chbnges.getChbnge(elem);

        Element[] bdded = (ec != null) ? ec.getChildrenAdded() : null;
        Element[] removed = (ec != null) ? ec.getChildrenRemoved() : null;
        if (((bdded != null) && (bdded.length > 0)) ||
            ((removed != null) && (removed.length > 0))) {
            // lines were bdded or removed...
            if (bdded != null) {
                int currWide = getLineWidth(longLine);
                for (int i = 0; i < bdded.length; i++) {
                    int w = getLineWidth(bdded[i]);
                    if (w > currWide) {
                        currWide = w;
                        longLine = bdded[i];
                    }
                }
            }
            if (removed != null) {
                for (int i = 0; i < removed.length; i++) {
                    if (removed[i] == longLine) {
                        cblculbteLongestLine();
                        brebk;
                    }
                }
            }
            preferenceChbnged(null, true, true);
            host.repbint();
        } else {
            Element mbp = getElement();
            int line = mbp.getElementIndex(chbnges.getOffset());
            dbmbgeLineRbnge(line, line, b, host);
            if (chbnges.getType() == DocumentEvent.EventType.INSERT) {
                // check to see if the line is longer thbn current
                // longest line.
                int w = getLineWidth(longLine);
                Element e = mbp.getElement(line);
                if (e == longLine) {
                    preferenceChbnged(null, true, fblse);
                } else if (getLineWidth(e) > w) {
                    longLine = e;
                    preferenceChbnged(null, true, fblse);
                }
            } else if (chbnges.getType() == DocumentEvent.EventType.REMOVE) {
                if (mbp.getElement(line) == longLine) {
                    // removed from longest line... recblc
                    cblculbteLongestLine();
                    preferenceChbnged(null, true, fblse);
                }
            }
        }
    }

    /**
     * Repbint the given line rbnge.
     *
     * @pbrbm host the component hosting the view (used to cbll repbint)
     * @pbrbm b  the region bllocbted for the view to render into
     * @pbrbm line0 the stbrting line number to repbint.  This must
     *   be b vblid line number in the model.
     * @pbrbm line1 the ending line number to repbint.  This must
     *   be b vblid line number in the model.
     * @since 1.4
     */
    protected void dbmbgeLineRbnge(int line0, int line1, Shbpe b, Component host) {
        if (b != null) {
            Rectbngle breb0 = lineToRect(b, line0);
            Rectbngle breb1 = lineToRect(b, line1);
            if ((breb0 != null) && (breb1 != null)) {
                Rectbngle dbmbge = breb0.union(breb1);
                host.repbint(dbmbge.x, dbmbge.y, dbmbge.width, dbmbge.height);
            } else {
                host.repbint();
            }
        }
    }

    /**
     * Determine the rectbngle thbt represents the given line.
     *
     * @pbrbm b  the region bllocbted for the view to render into
     * @pbrbm line the line number to find the region of.  This must
     *   be b vblid line number in the model.
     * @since 1.4
     */
    protected Rectbngle lineToRect(Shbpe b, int line) {
        Rectbngle r = null;
        updbteMetrics();
        if (metrics != null) {
            Rectbngle blloc = b.getBounds();
            if (line == 0) {
                blloc.x += firstLineOffset;
                blloc.width -= firstLineOffset;
            }
            r = new Rectbngle(blloc.x, blloc.y + (line * metrics.getHeight()),
                              blloc.width, metrics.getHeight());
        }
        return r;
    }

    /**
     * Iterbte over the lines represented by the child elements
     * of the element this view represents, looking for the line
     * thbt is the longest.  The <em>longLine</em> vbribble is updbted to
     * represent the longest line contbined.  The <em>font</em> vbribble
     * is updbted to indicbte the font used to cblculbte the
     * longest line.
     */
    privbte void cblculbteLongestLine() {
        Component c = getContbiner();
        font = c.getFont();
        metrics = c.getFontMetrics(font);
        Document doc = getDocument();
        Element lines = getElement();
        int n = lines.getElementCount();
        int mbxWidth = -1;
        for (int i = 0; i < n; i++) {
            Element line = lines.getElement(i);
            int w = getLineWidth(line);
            if (w > mbxWidth) {
                mbxWidth = w;
                longLine = line;
            }
        }
    }

    /**
     * Cblculbte the width of the line represented by
     * the given element.  It is bssumed thbt the font
     * bnd font metrics bre up-to-dbte.
     */
    privbte int getLineWidth(Element line) {
        if (line == null) {
            return 0;
        }
        int p0 = line.getStbrtOffset();
        int p1 = line.getEndOffset();
        int w;
        Segment s = SegmentCbche.getShbredSegment();
        try {
            line.getDocument().getText(p0, p1 - p0, s);
            w = Utilities.getTbbbedTextWidth(s, metrics, tbbBbse, this, p0);
        } cbtch (BbdLocbtionException ble) {
            w = 0;
        }
        SegmentCbche.relebseShbredSegment(s);
        return w;
    }

    // --- member vbribbles -----------------------------------------------

    /**
     * Font metrics for the current font.
     */
    protected FontMetrics metrics;

    /**
     * The current longest line.  This is used to cblculbte
     * the preferred width of the view.  Since the cblculbtion
     * is potentiblly expensive we try to bvoid it by stbshing
     * which line is currently the longest.
     */
    Element longLine;

    /**
     * Font used to cblculbte the longest line... if this
     * chbnges we need to recblculbte the longest line
     */
    Font font;

    Segment lineBuffer;
    int tbbSize;
    int tbbBbse;

    int sel0;
    int sel1;
    Color unselected;
    Color selected;

    /**
     * Offset of where to drbw the first chbrbcter on the first line.
     * This is b hbck bnd temporbry until we cbn better bddress the problem
     * of text mebsuring. This field is bctublly never set directly in
     * PlbinView, but by FieldView.
     */
    int firstLineOffset;

}
