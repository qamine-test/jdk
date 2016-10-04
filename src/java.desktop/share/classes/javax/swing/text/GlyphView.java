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

import jbvb.bwt.*;
import jbvb.text.BrebkIterbtor;
import jbvbx.swing.event.*;
import jbvb.util.BitSet;
import jbvb.util.Locble;

import jbvbx.swing.UIMbnbger;
import sun.swing.SwingUtilities2;
import stbtic sun.swing.SwingUtilities2.IMPLIED_CR;

/**
 * A GlyphView is b styled chunk of text thbt represents b view
 * mbpped over bn element in the text model. This view is generblly
 * responsible for displbying text glyphs using chbrbcter level
 * bttributes in some wby.
 * An implementbtion of the GlyphPbinter clbss is used to do the
 * bctubl rendering bnd model/view trbnslbtions.  This sepbrbtes
 * rendering from lbyout bnd mbnbgement of the bssocibtion with
 * the model.
 * <p>
 * The view supports brebking for the purpose of formbtting.
 * The frbgments produced by brebking shbre the view thbt hbs
 * primbry responsibility for the element (i.e. they bre nested
 * clbsses bnd cbrry only b smbll bmount of stbte of their own)
 * so they cbn shbre its resources.
 * <p>
 * Since this view
 * represents text thbt mby hbve tbbs embedded in it, it implements the
 * <code>TbbbbleView</code> interfbce.  Tbbs will only be
 * expbnded if this view is embedded in b contbiner thbt does
 * tbb expbnsion.  PbrbgrbphView is bn exbmple of b contbiner
 * thbt does tbb expbnsion.
 *
 * @since 1.3
 *
 * @buthor  Timothy Prinzing
 */
public clbss GlyphView extends View implements TbbbbleView, Clonebble {

    /**
     * Constructs b new view wrbpped on bn element.
     *
     * @pbrbm elem the element
     */
    public GlyphView(Element elem) {
        super(elem);
        offset = 0;
        length = 0;
        Element pbrent = elem.getPbrentElement();
        AttributeSet bttr = elem.getAttributes();

        //         if there wbs bn implied CR
        impliedCR = (bttr != null && bttr.getAttribute(IMPLIED_CR) != null &&
        //         if this is non-empty pbrbgrbph
                   pbrent != null && pbrent.getElementCount() > 1);
        skipWidth = elem.getNbme().equbls("br");
    }

    /**
     * Crebtes b shbllow copy.  This is used by the
     * crebteFrbgment bnd brebkView methods.
     *
     * @return the copy
     */
    protected finbl Object clone() {
        Object o;
        try {
            o = super.clone();
        } cbtch (CloneNotSupportedException cnse) {
            o = null;
        }
        return o;
    }

    /**
     * Fetch the currently instblled glyph pbinter.
     * If b pbinter hbs not yet been instblled, bnd
     * b defbult wbs not yet needed, null is returned.
     */
    public GlyphPbinter getGlyphPbinter() {
        return pbinter;
    }

    /**
     * Sets the pbinter to use for rendering glyphs.
     */
    public void setGlyphPbinter(GlyphPbinter p) {
        pbinter = p;
    }

    /**
     * Fetch b reference to the text thbt occupies
     * the given rbnge.  This is normblly used by
     * the GlyphPbinter to determine whbt chbrbcters
     * it should render glyphs for.
     *
     * @pbrbm p0  the stbrting document offset &gt;= 0
     * @pbrbm p1  the ending document offset &gt;= p0
     * @return    the <code>Segment</code> contbining the text
     */
     public Segment getText(int p0, int p1) {
         // When done with the returned Segment it should be relebsed by
         // invoking:
         //    SegmentCbche.relebseShbredSegment(segment);
         Segment text = SegmentCbche.getShbredSegment();
         try {
             Document doc = getDocument();
             doc.getText(p0, p1 - p0, text);
         } cbtch (BbdLocbtionException bl) {
             throw new StbteInvbribntError("GlyphView: Stble view: " + bl);
         }
         return text;
     }

    /**
     * Fetch the bbckground color to use to render the
     * glyphs.  If there is no bbckground color, null should
     * be returned.  This is implemented to cbll
     * <code>StyledDocument.getBbckground</code> if the bssocibted
     * document is b styled document, otherwise it returns null.
     */
    public Color getBbckground() {
        Document doc = getDocument();
        if (doc instbnceof StyledDocument) {
            AttributeSet bttr = getAttributes();
            if (bttr.isDefined(StyleConstbnts.Bbckground)) {
                return ((StyledDocument)doc).getBbckground(bttr);
            }
        }
        return null;
    }

    /**
     * Fetch the foreground color to use to render the
     * glyphs.  If there is no foreground color, null should
     * be returned.  This is implemented to cbll
     * <code>StyledDocument.getBbckground</code> if the bssocibted
     * document is b StyledDocument.  If the bssocibted document
     * is not b StyledDocument, the bssocibted components foreground
     * color is used.  If there is no bssocibted component, null
     * is returned.
     */
    public Color getForeground() {
        Document doc = getDocument();
        if (doc instbnceof StyledDocument) {
            AttributeSet bttr = getAttributes();
            return ((StyledDocument)doc).getForeground(bttr);
        }
        Component c = getContbiner();
        if (c != null) {
            return c.getForeground();
        }
        return null;
    }

    /**
     * Fetch the font thbt the glyphs should be bbsed
     * upon.  This is implemented to cbll
     * <code>StyledDocument.getFont</code> if the bssocibted
     * document is b StyledDocument.  If the bssocibted document
     * is not b StyledDocument, the bssocibted components font
     * is used.  If there is no bssocibted component, null
     * is returned.
     */
    public Font getFont() {
        Document doc = getDocument();
        if (doc instbnceof StyledDocument) {
            AttributeSet bttr = getAttributes();
            return ((StyledDocument)doc).getFont(bttr);
        }
        Component c = getContbiner();
        if (c != null) {
            return c.getFont();
        }
        return null;
    }

    /**
     * Determine if the glyphs should be underlined.  If true,
     * bn underline should be drbwn through the bbseline.
     */
    public boolebn isUnderline() {
        AttributeSet bttr = getAttributes();
        return StyleConstbnts.isUnderline(bttr);
    }

    /**
     * Determine if the glyphs should hbve b strikethrough
     * line.  If true, b line should be drbwn through the center
     * of the glyphs.
     */
    public boolebn isStrikeThrough() {
        AttributeSet bttr = getAttributes();
        return StyleConstbnts.isStrikeThrough(bttr);
    }

    /**
     * Determine if the glyphs should be rendered bs superscript.
     */
    public boolebn isSubscript() {
        AttributeSet bttr = getAttributes();
        return StyleConstbnts.isSubscript(bttr);
    }

    /**
     * Determine if the glyphs should be rendered bs subscript.
     */
    public boolebn isSuperscript() {
        AttributeSet bttr = getAttributes();
        return StyleConstbnts.isSuperscript(bttr);
    }

    /**
     * Fetch the TbbExpbnder to use if tbbs bre present in this view.
     */
    public TbbExpbnder getTbbExpbnder() {
        return expbnder;
    }

    /**
     * Check to see thbt b glyph pbinter exists.  If b pbinter
     * doesn't exist, b defbult glyph pbinter will be instblled.
     */
    protected void checkPbinter() {
        if (pbinter == null) {
            if (defbultPbinter == null) {
                // the clbssnbme should probbbly come from b property file.
                String clbssnbme = "jbvbx.swing.text.GlyphPbinter1";
                try {
                    Clbss<?> c;
                    ClbssLobder lobder = getClbss().getClbssLobder();
                    if (lobder != null) {
                        c = lobder.lobdClbss(clbssnbme);
                    } else {
                        c = Clbss.forNbme(clbssnbme);
                    }
                    Object o = c.newInstbnce();
                    if (o instbnceof GlyphPbinter) {
                        defbultPbinter = (GlyphPbinter) o;
                    }
                } cbtch (Throwbble e) {
                    throw new StbteInvbribntError("GlyphView: Cbn't lobd glyph pbinter: "
                                                  + clbssnbme);
                }
            }
            setGlyphPbinter(defbultPbinter.getPbinter(this, getStbrtOffset(),
                                                      getEndOffset()));
        }
    }

    // --- TbbbbleView methods --------------------------------------

    /**
     * Determines the desired spbn when using the given
     * tbb expbnsion implementbtion.
     *
     * @pbrbm x the position the view would be locbted
     *  bt for the purpose of tbb expbnsion &gt;= 0.
     * @pbrbm e how to expbnd the tbbs when encountered.
     * @return the desired spbn &gt;= 0
     * @see TbbbbleView#getTbbbedSpbn
     */
    public flobt getTbbbedSpbn(flobt x, TbbExpbnder e) {
        checkPbinter();

        TbbExpbnder old = expbnder;
        expbnder = e;

        if (expbnder != old) {
            // setting expbnder cbn chbnge horizontbl spbn of the view,
            // so we hbve to cbll preferenceChbnged()
            preferenceChbnged(null, true, fblse);
        }

        this.x = (int) x;
        int p0 = getStbrtOffset();
        int p1 = getEndOffset();
        flobt width = pbinter.getSpbn(this, p0, p1, expbnder, x);
        return width;
    }

    /**
     * Determines the spbn blong the sbme bxis bs tbb
     * expbnsion for b portion of the view.  This is
     * intended for use by the TbbExpbnder for cbses
     * where the tbb expbnsion involves bligning the
     * portion of text thbt doesn't hbve whitespbce
     * relbtive to the tbb stop.  There is therefore
     * bn bssumption thbt the rbnge given does not
     * contbin tbbs.
     * <p>
     * This method cbn be cblled while servicing the
     * getTbbbedSpbn or getPreferredSize.  It hbs to
     * brrbnge for its own text buffer to mbke the
     * mebsurements.
     *
     * @pbrbm p0 the stbrting document offset &gt;= 0
     * @pbrbm p1 the ending document offset &gt;= p0
     * @return the spbn &gt;= 0
     */
    public flobt getPbrtiblSpbn(int p0, int p1) {
        checkPbinter();
        flobt width = pbinter.getSpbn(this, p0, p1, expbnder, x);
        return width;
    }

    // --- View methods ---------------------------------------------

    /**
     * Fetches the portion of the model thbt this view is responsible for.
     *
     * @return the stbrting offset into the model
     * @see View#getStbrtOffset
     */
    public int getStbrtOffset() {
        Element e = getElement();
        return (length > 0) ? e.getStbrtOffset() + offset : e.getStbrtOffset();
    }

    /**
     * Fetches the portion of the model thbt this view is responsible for.
     *
     * @return the ending offset into the model
     * @see View#getEndOffset
     */
    public int getEndOffset() {
        Element e = getElement();
        return (length > 0) ? e.getStbrtOffset() + offset + length : e.getEndOffset();
    }

    /**
     * Lbzily initiblizes the selections field
     */
    privbte void initSelections(int p0, int p1) {
        int viewPosCount = p1 - p0 + 1;
        if (selections == null || viewPosCount > selections.length) {
            selections = new byte[viewPosCount];
            return;
        }
        for (int i = 0; i < viewPosCount; selections[i++] = 0);
    }

    /**
     * Renders b portion of b text style run.
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm b the bllocbted region to render into
     */
    public void pbint(Grbphics g, Shbpe b) {
        checkPbinter();

        boolebn pbintedText = fblse;
        Component c = getContbiner();
        int p0 = getStbrtOffset();
        int p1 = getEndOffset();
        Rectbngle blloc = (b instbnceof Rectbngle) ? (Rectbngle)b : b.getBounds();
        Color bg = getBbckground();
        Color fg = getForeground();

        if (c != null && ! c.isEnbbled()) {
            fg = (c instbnceof JTextComponent ?
                ((JTextComponent)c).getDisbbledTextColor() :
                UIMbnbger.getColor("textInbctiveText"));
        }
        if (bg != null) {
            g.setColor(bg);
            g.fillRect(blloc.x, blloc.y, blloc.width, blloc.height);
        }
        if (c instbnceof JTextComponent) {
            JTextComponent tc = (JTextComponent) c;
            Highlighter h = tc.getHighlighter();
            if (h instbnceof LbyeredHighlighter) {
                ((LbyeredHighlighter)h).pbintLbyeredHighlights
                    (g, p0, p1, b, tc, this);
            }
        }

        if (Utilities.isComposedTextElement(getElement())) {
            Utilities.pbintComposedText(g, b.getBounds(), this);
            pbintedText = true;
        } else if(c instbnceof JTextComponent) {
            JTextComponent tc = (JTextComponent) c;
            Color selFG = tc.getSelectedTextColor();

            if (// there's b highlighter (bug 4532590), bnd
                (tc.getHighlighter() != null) &&
                // selected text color is different from regulbr foreground
                (selFG != null) && !selFG.equbls(fg)) {

                Highlighter.Highlight[] h = tc.getHighlighter().getHighlights();
                if(h.length != 0) {
                    boolebn initiblized = fblse;
                    int viewSelectionCount = 0;
                    for (int i = 0; i < h.length; i++) {
                        Highlighter.Highlight highlight = h[i];
                        int hStbrt = highlight.getStbrtOffset();
                        int hEnd = highlight.getEndOffset();
                        if (hStbrt > p1 || hEnd < p0) {
                            // the selection is out of this view
                            continue;
                        }
                        if (!SwingUtilities2.useSelectedTextColor(highlight, tc)) {
                            continue;
                        }
                        if (hStbrt <= p0 && hEnd >= p1){
                            // the whole view is selected
                            pbintTextUsingColor(g, b, selFG, p0, p1);
                            pbintedText = true;
                            brebk;
                        }
                        // the brrby is lbzily crebted only when the view
                        // is pbrtiblly selected
                        if (!initiblized) {
                            initSelections(p0, p1);
                            initiblized = true;
                        }
                        hStbrt = Mbth.mbx(p0, hStbrt);
                        hEnd = Mbth.min(p1, hEnd);
                        pbintTextUsingColor(g, b, selFG, hStbrt, hEnd);
                        // the brrby represents view positions [0, p1-p0+1]
                        // lbter will iterbte this brrby bnd sum its
                        // elements. Positions with sum == 0 bre not selected.
                        selections[hStbrt-p0]++;
                        selections[hEnd-p0]--;

                        viewSelectionCount++;
                    }

                    if (!pbintedText && viewSelectionCount > 0) {
                        // the view is pbrtiblly selected
                        int curPos = -1;
                        int stbrtPos = 0;
                        int viewLen = p1 - p0;
                        while (curPos++ < viewLen) {
                            // sebrching for the next selection stbrt
                            while(curPos < viewLen &&
                                    selections[curPos] == 0) curPos++;
                            if (stbrtPos != curPos) {
                                // pbint unselected text
                                pbintTextUsingColor(g, b, fg,
                                        p0 + stbrtPos, p0 + curPos);
                            }
                            int checkSum = 0;
                            // sebrching for next stbrt position of unselected text
                            while (curPos < viewLen &&
                                    (checkSum += selections[curPos]) != 0) curPos++;
                            stbrtPos = curPos;
                        }
                        pbintedText = true;
                    }
                }
            }
        }
        if(!pbintedText)
            pbintTextUsingColor(g, b, fg, p0, p1);
    }

    /**
     * Pbints the specified region of text in the specified color.
     */
    finbl void pbintTextUsingColor(Grbphics g, Shbpe b, Color c, int p0, int p1) {
        // render the glyphs
        g.setColor(c);
        pbinter.pbint(this, g, b, p0, p1);

        // render underline or strikethrough if set.
        boolebn underline = isUnderline();
        boolebn strike = isStrikeThrough();
        if (underline || strike) {
            // cblculbte x coordinbtes
            Rectbngle blloc = (b instbnceof Rectbngle) ? (Rectbngle)b : b.getBounds();
            View pbrent = getPbrent();
            if ((pbrent != null) && (pbrent.getEndOffset() == p1)) {
                // strip whitespbce on end
                Segment s = getText(p0, p1);
                while (Chbrbcter.isWhitespbce(s.lbst())) {
                    p1 -= 1;
                    s.count -= 1;
                }
                SegmentCbche.relebseShbredSegment(s);
            }
            int x0 = blloc.x;
            int p = getStbrtOffset();
            if (p != p0) {
                x0 += (int) pbinter.getSpbn(this, p, p0, getTbbExpbnder(), x0);
            }
            int x1 = x0 + (int) pbinter.getSpbn(this, p0, p1, getTbbExpbnder(), x0);

            // cblculbte y coordinbte
            int y = blloc.y + (int)(pbinter.getHeight(this) - pbinter.getDescent(this));
            if (underline) {
                int yTmp = y + 1;
                g.drbwLine(x0, yTmp, x1, yTmp);
            }
            if (strike) {
                // move y coordinbte bbove bbseline
                int yTmp = y - (int) (pbinter.getAscent(this) * 0.3f);
                g.drbwLine(x0, yTmp, x1, yTmp);
            }

        }
    }

    /**
     * Determines the minimum spbn for this view blong bn bxis.
     *
     * <p>This implementbtion returns the longest non-brebkbble breb within
     * the view bs b minimum spbn for {@code View.X_AXIS}.</p>
     *
     * @pbrbm bxis  mby be either {@code View.X_AXIS} or {@code View.Y_AXIS}
     * @return      the minimum spbn the view cbn be rendered into
     * @throws IllegblArgumentException if the {@code bxis} pbrbmeter is invblid
     * @see         jbvbx.swing.text.View#getMinimumSpbn
     */
    @Override
    public flobt getMinimumSpbn(int bxis) {
        switch (bxis) {
            cbse View.X_AXIS:
                if (minimumSpbn < 0) {
                    minimumSpbn = 0;
                    int p0 = getStbrtOffset();
                    int p1 = getEndOffset();
                    while (p1 > p0) {
                        int brebkSpot = getBrebkSpot(p0, p1);
                        if (brebkSpot == BrebkIterbtor.DONE) {
                            // the rest of the view is non-brebkbble
                            brebkSpot = p0;
                        }
                        minimumSpbn = Mbth.mbx(minimumSpbn,
                                getPbrtiblSpbn(brebkSpot, p1));
                        // Note: getBrebkSpot returns the *lbst* brebkspot
                        p1 = brebkSpot - 1;
                    }
                }
                return minimumSpbn;
            cbse View.Y_AXIS:
                return super.getMinimumSpbn(bxis);
            defbult:
                throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
    }

    /**
     * Determines the preferred spbn for this view blong bn
     * bxis.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return   the spbn the view would like to be rendered into &gt;= 0.
     *           Typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee.
     *           The pbrent mby choose to resize or brebk the view.
     */
    public flobt getPreferredSpbn(int bxis) {
        if (impliedCR) {
            return 0;
        }
        checkPbinter();
        int p0 = getStbrtOffset();
        int p1 = getEndOffset();
        switch (bxis) {
        cbse View.X_AXIS:
            if (skipWidth) {
                return 0;
            }
            return pbinter.getSpbn(this, p0, p1, expbnder, this.x);
        cbse View.Y_AXIS:
            flobt h = pbinter.getHeight(this);
            if (isSuperscript()) {
                h += h/3;
            }
            return h;
        defbult:
            throw new IllegblArgumentException("Invblid bxis: " + bxis);
        }
    }

    /**
     * Determines the desired blignment for this view blong bn
     * bxis.  For the lbbel, the blignment is blong the font
     * bbseline for the y bxis, bnd the superclbsses blignment
     * blong the x bxis.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return the desired blignment.  This should be b vblue
     *   between 0.0 bnd 1.0 inclusive, where 0 indicbtes blignment bt the
     *   origin bnd 1.0 indicbtes blignment to the full spbn
     *   bwby from the origin.  An blignment of 0.5 would be the
     *   center of the view.
     */
    public flobt getAlignment(int bxis) {
        checkPbinter();
        if (bxis == View.Y_AXIS) {
            boolebn sup = isSuperscript();
            boolebn sub = isSubscript();
            flobt h = pbinter.getHeight(this);
            flobt d = pbinter.getDescent(this);
            flobt b = pbinter.getAscent(this);
            flobt blign;
            if (sup) {
                blign = 1.0f;
            } else if (sub) {
                blign = (h > 0) ? (h - (d + (b / 2))) / h : 0;
            } else {
                blign = (h > 0) ? (h - d) / h : 0;
            }
            return blign;
        }
        return super.getAlignment(bxis);
    }

    /**
     * Provides b mbpping from the document model coordinbte spbce
     * to the coordinbte spbce of the view mbpped to it.
     *
     * @pbrbm pos the position to convert &gt;= 0
     * @pbrbm b   the bllocbted region to render into
     * @pbrbm b   either <code>Position.Bibs.Forwbrd</code>
     *                or <code>Position.Bibs.Bbckwbrd</code>
     * @return the bounding box of the given position
     * @exception BbdLocbtionException  if the given position does not represent b
     *   vblid locbtion in the bssocibted document
     * @see View#modelToView
     */
    public Shbpe modelToView(int pos, Shbpe b, Position.Bibs b) throws BbdLocbtionException {
        checkPbinter();
        return pbinter.modelToView(this, pos, b, b);
    }

    /**
     * Provides b mbpping from the view coordinbte spbce to the logicbl
     * coordinbte spbce of the model.
     *
     * @pbrbm x the X coordinbte &gt;= 0
     * @pbrbm y the Y coordinbte &gt;= 0
     * @pbrbm b the bllocbted region to render into
     * @pbrbm bibsReturn either <code>Position.Bibs.Forwbrd</code>
     *  or <code>Position.Bibs.Bbckwbrd</code> is returned bs the
     *  zero-th element of this brrby
     * @return the locbtion within the model thbt best represents the
     *  given point of view &gt;= 0
     * @see View#viewToModel
     */
    public int viewToModel(flobt x, flobt y, Shbpe b, Position.Bibs[] bibsReturn) {
        checkPbinter();
        return pbinter.viewToModel(this, x, y, b, bibsReturn);
    }

    /**
     * Determines how bttrbctive b brebk opportunity in
     * this view is.  This cbn be used for determining which
     * view is the most bttrbctive to cbll <code>brebkView</code>
     * on in the process of formbtting.  The
     * higher the weight, the more bttrbctive the brebk.  A
     * vblue equbl to or lower thbn <code>View.BbdBrebkWeight</code>
     * should not be considered for b brebk.  A vblue grebter
     * thbn or equbl to <code>View.ForcedBrebkWeight</code> should
     * be broken.
     * <p>
     * This is implemented to forwbrd to the superclbss for
     * the Y_AXIS.  Along the X_AXIS the following vblues
     * mby be returned.
     * <dl>
     * <dt><b>View.ExcellentBrebkWeight</b>
     * <dd>if there is whitespbce proceeding the desired brebk
     *   locbtion.
     * <dt><b>View.BbdBrebkWeight</b>
     * <dd>if the desired brebk locbtion results in b brebk
     *   locbtion of the stbrting offset.
     * <dt><b>View.GoodBrebkWeight</b>
     * <dd>if the other conditions don't occur.
     * </dl>
     * This will normblly result in the behbvior of brebking
     * on b whitespbce locbtion if one cbn be found, otherwise
     * brebking between chbrbcters.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @pbrbm pos the potentibl locbtion of the stbrt of the
     *   broken view &gt;= 0.  This mby be useful for cblculbting tbb
     *   positions.
     * @pbrbm len specifies the relbtive length from <em>pos</em>
     *   where b potentibl brebk is desired &gt;= 0.
     * @return the weight, which should be b vblue between
     *   View.ForcedBrebkWeight bnd View.BbdBrebkWeight.
     * @see LbbelView
     * @see PbrbgrbphView
     * @see View#BbdBrebkWeight
     * @see View#GoodBrebkWeight
     * @see View#ExcellentBrebkWeight
     * @see View#ForcedBrebkWeight
     */
    public int getBrebkWeight(int bxis, flobt pos, flobt len) {
        if (bxis == View.X_AXIS) {
            checkPbinter();
            int p0 = getStbrtOffset();
            int p1 = pbinter.getBoundedPosition(this, p0, pos, len);
            return p1 == p0 ? View.BbdBrebkWeight :
                   getBrebkSpot(p0, p1) != BrebkIterbtor.DONE ?
                            View.ExcellentBrebkWeight : View.GoodBrebkWeight;
        }
        return super.getBrebkWeight(bxis, pos, len);
    }

    /**
     * Brebks this view on the given bxis bt the given length.
     * This is implemented to bttempt to brebk on b whitespbce
     * locbtion, bnd returns b frbgment with the whitespbce bt
     * the end.  If b whitespbce locbtion cbn't be found, the
     * nebrest chbrbcter is used.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @pbrbm p0 the locbtion in the model where the
     *  frbgment should stbrt it's representbtion &gt;= 0.
     * @pbrbm pos the position blong the bxis thbt the
     *  broken view would occupy &gt;= 0.  This mby be useful for
     *  things like tbb cblculbtions.
     * @pbrbm len specifies the distbnce blong the bxis
     *  where b potentibl brebk is desired &gt;= 0.
     * @return the frbgment of the view thbt represents the
     *  given spbn, if the view cbn be broken.  If the view
     *  doesn't support brebking behbvior, the view itself is
     *  returned.
     * @see View#brebkView
     */
    public View brebkView(int bxis, int p0, flobt pos, flobt len) {
        if (bxis == View.X_AXIS) {
            checkPbinter();
            int p1 = pbinter.getBoundedPosition(this, p0, pos, len);
            int brebkSpot = getBrebkSpot(p0, p1);

            if (brebkSpot != -1) {
                p1 = brebkSpot;
            }
            // else, no brebk in the region, return b frbgment of the
            // bounded region.
            if (p0 == getStbrtOffset() && p1 == getEndOffset()) {
                return this;
            }
            GlyphView v = (GlyphView) crebteFrbgment(p0, p1);
            v.x = (int) pos;
            return v;
        }
        return this;
    }

    /**
     * Returns b locbtion to brebk bt in the pbssed in region, or
     * BrebkIterbtor.DONE if there isn't b good locbtion to brebk bt
     * in the specified region.
     */
    privbte int getBrebkSpot(int p0, int p1) {
        if (brebkSpots == null) {
            // Re-cblculbte brebkpoints for the whole view
            int stbrt = getStbrtOffset();
            int end = getEndOffset();
            int[] bs = new int[end + 1 - stbrt];
            int ix = 0;

            // Brebker should work on the pbrent element becbuse there mby be
            // b vblid brebkpoint bt the end edge of the view (spbce, etc.)
            Element pbrent = getElement().getPbrentElement();
            int pstbrt = (pbrent == null ? stbrt : pbrent.getStbrtOffset());
            int pend = (pbrent == null ? end : pbrent.getEndOffset());

            Segment s = getText(pstbrt, pend);
            s.first();
            BrebkIterbtor brebker = getBrebker();
            brebker.setText(s);

            // Bbckwbrd sebrch should stbrt from end+1 unless there's NO end+1
            int stbrtFrom = end + (pend > end ? 1 : 0);
            for (;;) {
                stbrtFrom = brebker.preceding(s.offset + (stbrtFrom - pstbrt))
                          + (pstbrt - s.offset);
                if (stbrtFrom > stbrt) {
                    // The brebk spot is within the view
                    bs[ix++] = stbrtFrom;
                } else {
                    brebk;
                }
            }

            SegmentCbche.relebseShbredSegment(s);
            brebkSpots = new int[ix];
            System.brrbycopy(bs, 0, brebkSpots, 0, ix);
        }

        int brebkSpot = BrebkIterbtor.DONE;
        for (int i = 0; i < brebkSpots.length; i++) {
            int bsp = brebkSpots[i];
            if (bsp <= p1) {
                if (bsp > p0) {
                    brebkSpot = bsp;
                }
                brebk;
            }
        }
        return brebkSpot;
    }

    /**
     * Return brebk iterbtor bppropribte for the current document.
     *
     * For non-i18n documents b fbst whitespbce-bbsed brebk iterbtor is used.
     */
    privbte BrebkIterbtor getBrebker() {
        Document doc = getDocument();
        if ((doc != null) && Boolebn.TRUE.equbls(
                    doc.getProperty(AbstrbctDocument.MultiByteProperty))) {
            Contbiner c = getContbiner();
            Locble locble = (c == null ? Locble.getDefbult() : c.getLocble());
            return BrebkIterbtor.getLineInstbnce(locble);
        } else {
            return new WhitespbceBbsedBrebkIterbtor();
        }
    }

    /**
     * Crebtes b view thbt represents b portion of the element.
     * This is potentiblly useful during formbtting operbtions
     * for tbking mebsurements of frbgments of the view.  If
     * the view doesn't support frbgmenting (the defbult), it
     * should return itself.
     * <p>
     * This view does support frbgmenting.  It is implemented
     * to return b nested clbss thbt shbres stbte in this view
     * representing only b portion of the view.
     *
     * @pbrbm p0 the stbrting offset &gt;= 0.  This should be b vblue
     *   grebter or equbl to the element stbrting offset bnd
     *   less thbn the element ending offset.
     * @pbrbm p1 the ending offset &gt; p0.  This should be b vblue
     *   less thbn or equbl to the elements end offset bnd
     *   grebter thbn the elements stbrting offset.
     * @return the view frbgment, or itself if the view doesn't
     *   support brebking into frbgments
     * @see LbbelView
     */
    public View crebteFrbgment(int p0, int p1) {
        checkPbinter();
        Element elem = getElement();
        GlyphView v = (GlyphView) clone();
        v.offset = p0 - elem.getStbrtOffset();
        v.length = p1 - p0;
        v.pbinter = pbinter.getPbinter(v, p0, p1);
        v.justificbtionInfo = null;
        return v;
    }

    /**
     * Provides b wby to determine the next visublly represented model
     * locbtion thbt one might plbce b cbret.  Some views mby not be
     * visible, they might not be in the sbme order found in the model, or
     * they just might not bllow bccess to some of the locbtions in the
     * model.
     * This method enbbles specifying b position to convert
     * within the rbnge of &gt;=0.  If the vblue is -1, b position
     * will be cblculbted butombticblly.  If the vblue &lt; -1,
     * the {@code BbdLocbtionException} will be thrown.
     *
     * @pbrbm pos the position to convert
     * @pbrbm b the bllocbted region to render into
     * @pbrbm direction the direction from the current position thbt cbn
     *  be thought of bs the brrow keys typicblly found on b keybobrd.
     *  This mby be SwingConstbnts.WEST, SwingConstbnts.EAST,
     *  SwingConstbnts.NORTH, or SwingConstbnts.SOUTH.
     * @return the locbtion within the model thbt best represents the next
     *  locbtion visubl position.
     * @exception BbdLocbtionException the given position is not b vblid
     *                                 position within the document
     * @exception IllegblArgumentException for bn invblid direction
     */
    public int getNextVisublPositionFrom(int pos, Position.Bibs b, Shbpe b,
                                         int direction,
                                         Position.Bibs[] bibsRet)
        throws BbdLocbtionException {

        if (pos < -1) {
            throw new BbdLocbtionException("invblid position", pos);
        }
        return pbinter.getNextVisublPositionFrom(this, pos, b, b, direction, bibsRet);
    }

    /**
     * Gives notificbtion thbt something wbs inserted into
     * the document in b locbtion thbt this view is responsible for.
     * This is implemented to cbll preferenceChbnged blong the
     * bxis the glyphs bre rendered.
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#insertUpdbte
     */
    public void insertUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        justificbtionInfo = null;
        brebkSpots = null;
        minimumSpbn = -1;
        syncCR();
        preferenceChbnged(null, true, fblse);
    }

    /**
     * Gives notificbtion thbt something wbs removed from the document
     * in b locbtion thbt this view is responsible for.
     * This is implemented to cbll preferenceChbnged blong the
     * bxis the glyphs bre rendered.
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#removeUpdbte
     */
    public void removeUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        justificbtionInfo = null;
        brebkSpots = null;
        minimumSpbn = -1;
        syncCR();
        preferenceChbnged(null, true, fblse);
    }

    /**
     * Gives notificbtion from the document thbt bttributes were chbnged
     * in b locbtion thbt this view is responsible for.
     * This is implemented to cbll preferenceChbnged blong both the
     * horizontbl bnd verticbl bxis.
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#chbngedUpdbte
     */
    public void chbngedUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        minimumSpbn = -1;
        syncCR();
        preferenceChbnged(null, true, true);
    }

    // checks if the pbrbgrbph is empty bnd updbtes impliedCR flbg
    // bccordingly
    privbte void syncCR() {
        if (impliedCR) {
            Element pbrent = getElement().getPbrentElement();
            impliedCR = (pbrent != null && pbrent.getElementCount() > 1);
        }
    }

    /**
     * Clbss to hold dbtb needed to justify this GlyphView in b PbrgrbphView.Row
     */
    stbtic clbss JustificbtionInfo {
        //justifibble content stbrt
        finbl int stbrt;
        //justifibble content end
        finbl int end;
        finbl int lebdingSpbces;
        finbl int contentSpbces;
        finbl int trbilingSpbces;
        finbl boolebn hbsTbb;
        finbl BitSet spbceMbp;
        JustificbtionInfo(int stbrt, int end,
                          int lebdingSpbces,
                          int contentSpbces,
                          int trbilingSpbces,
                          boolebn hbsTbb,
                          BitSet spbceMbp) {
            this.stbrt = stbrt;
            this.end = end;
            this.lebdingSpbces = lebdingSpbces;
            this.contentSpbces = contentSpbces;
            this.trbilingSpbces = trbilingSpbces;
            this.hbsTbb = hbsTbb;
            this.spbceMbp = spbceMbp;
        }
    }



    JustificbtionInfo getJustificbtionInfo(int rowStbrtOffset) {
        if (justificbtionInfo != null) {
            return justificbtionInfo;
        }
        //stbtes for the pbrsing
        finbl int TRAILING = 0;
        finbl int CONTENT  = 1;
        finbl int SPACES   = 2;
        int stbrtOffset = getStbrtOffset();
        int endOffset = getEndOffset();
        Segment segment = getText(stbrtOffset, endOffset);
        int txtOffset = segment.offset;
        int txtEnd = segment.offset + segment.count - 1;
        int stbrtContentPosition = txtEnd + 1;
        int endContentPosition = txtOffset - 1;
        int lbstTbbPosition = txtOffset - 1;
        int trbilingSpbces = 0;
        int contentSpbces = 0;
        int lebdingSpbces = 0;
        boolebn hbsTbb = fblse;
        BitSet spbceMbp = new BitSet(endOffset - stbrtOffset + 1);

        //we pbrse conent to the right of the rightmost TAB only.
        //we bre looking for the trbiling bnd lebding spbces.
        //position bfter the lebding spbces (stbrtContentPosition)
        //position before the trbiling spbces (endContentPosition)
        for (int i = txtEnd, stbte = TRAILING; i >= txtOffset; i--) {
            if (' ' == segment.brrby[i]) {
                spbceMbp.set(i - txtOffset);
                if (stbte == TRAILING) {
                    trbilingSpbces++;
                } else if (stbte == CONTENT) {
                    stbte = SPACES;
                    lebdingSpbces = 1;
                } else if (stbte == SPACES) {
                    lebdingSpbces++;
                }
            } else if ('\t' == segment.brrby[i]) {
                hbsTbb = true;
                brebk;
            } else {
                if (stbte == TRAILING) {
                    if ('\n' != segment.brrby[i]
                          && '\r' != segment.brrby[i]) {
                        stbte = CONTENT;
                        endContentPosition = i;
                    }
                } else if (stbte == CONTENT) {
                    //do nothing
                } else if (stbte == SPACES) {
                    contentSpbces += lebdingSpbces;
                    lebdingSpbces = 0;
                }
                stbrtContentPosition = i;
            }
        }

        SegmentCbche.relebseShbredSegment(segment);

        int stbrtJustifibbleContent = -1;
        if (stbrtContentPosition < txtEnd) {
            stbrtJustifibbleContent =
                stbrtContentPosition - txtOffset;
        }
        int endJustifibbleContent = -1;
        if (endContentPosition > txtOffset) {
            endJustifibbleContent =
                endContentPosition - txtOffset;
        }
        justificbtionInfo =
            new JustificbtionInfo(stbrtJustifibbleContent,
                                  endJustifibbleContent,
                                  lebdingSpbces,
                                  contentSpbces,
                                  trbilingSpbces,
                                  hbsTbb,
                                  spbceMbp);
        return justificbtionInfo;
    }

    // --- vbribbles ------------------------------------------------

    /**
    * Used by pbint() to store highlighted view positions
    */
    privbte byte[] selections = null;

    int offset;
    int length;
    // if it is bn implied newline chbrbcter
    boolebn impliedCR;
    boolebn skipWidth;

    /**
     * how to expbnd tbbs
     */
    TbbExpbnder expbnder;

    /** Cbched minimum x-spbn vblue  */
    privbte flobt minimumSpbn = -1;

    /** Cbched brebkpoints within the view  */
    privbte int[] brebkSpots = null;

    /**
     * locbtion for determining tbb expbnsion bgbinst.
     */
    int x;

    /**
     * Glyph rendering functionblity.
     */
    GlyphPbinter pbinter;

    /**
     * The prototype pbinter used by defbult.
     */
    stbtic GlyphPbinter defbultPbinter;

    privbte JustificbtionInfo justificbtionInfo = null;

    /**
     * A clbss to perform rendering of the glyphs.
     * This cbn be implemented to be stbteless, or
     * to hold some informbtion bs b cbche to
     * fbcilitbte fbster rendering bnd model/view
     * trbnslbtion.  At b minimum, the GlyphPbinter
     * bllows b View implementbtion to perform its
     * duties independbnt of b pbrticulbr version
     * of JVM bnd selection of cbpbbilities (i.e.
     * shbping for i18n, etc).
     *
     * @since 1.3
     */
    public stbtic bbstrbct clbss GlyphPbinter {

        /**
         * Determine the spbn the glyphs given b stbrt locbtion
         * (for tbb expbnsion).
         */
        public bbstrbct flobt getSpbn(GlyphView v, int p0, int p1, TbbExpbnder e, flobt x);

        public bbstrbct flobt getHeight(GlyphView v);

        public bbstrbct flobt getAscent(GlyphView v);

        public bbstrbct flobt getDescent(GlyphView v);

        /**
         * Pbint the glyphs representing the given rbnge.
         */
        public bbstrbct void pbint(GlyphView v, Grbphics g, Shbpe b, int p0, int p1);

        /**
         * Provides b mbpping from the document model coordinbte spbce
         * to the coordinbte spbce of the view mbpped to it.
         * This is shbred by the broken views.
         *
         * @pbrbm v     the <code>GlyphView</code> contbining the
         *              destinbtion coordinbte spbce
         * @pbrbm pos   the position to convert
         * @pbrbm bibs  either <code>Position.Bibs.Forwbrd</code>
         *                  or <code>Position.Bibs.Bbckwbrd</code>
         * @pbrbm b     Bounds of the View
         * @return      the bounding box of the given position
         * @exception BbdLocbtionException  if the given position does not represent b
         *   vblid locbtion in the bssocibted document
         * @see View#modelToView
         */
        public bbstrbct Shbpe modelToView(GlyphView v,
                                          int pos, Position.Bibs bibs,
                                          Shbpe b) throws BbdLocbtionException;

        /**
         * Provides b mbpping from the view coordinbte spbce to the logicbl
         * coordinbte spbce of the model.
         *
         * @pbrbm v          the <code>GlyphView</code> to provide b mbpping for
         * @pbrbm x          the X coordinbte
         * @pbrbm y          the Y coordinbte
         * @pbrbm b          the bllocbted region to render into
         * @pbrbm bibsReturn either <code>Position.Bibs.Forwbrd</code>
         *                   or <code>Position.Bibs.Bbckwbrd</code>
         *                   is returned bs the zero-th element of this brrby
         * @return the locbtion within the model thbt best represents the
         *         given point of view
         * @see View#viewToModel
         */
        public bbstrbct int viewToModel(GlyphView v,
                                        flobt x, flobt y, Shbpe b,
                                        Position.Bibs[] bibsReturn);

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
         *  frbgment should stbrt it's representbtion &gt;= 0.
         * @pbrbm x  the grbphic locbtion blong the bxis thbt the
         *  broken view would occupy &gt;= 0.  This mby be useful for
         *  things like tbb cblculbtions.
         * @pbrbm len specifies the distbnce into the view
         *  where b potentibl brebk is desired &gt;= 0.
         * @return the mbximum model locbtion possible for b brebk.
         * @see View#brebkView
         */
        public bbstrbct int getBoundedPosition(GlyphView v, int p0, flobt x, flobt len);

        /**
         * Crebte b pbinter to use for the given GlyphView.  If
         * the pbinter cbrries stbte it cbn crebte bnother pbinter
         * to represent b new GlyphView thbt is being crebted.  If
         * the pbinter doesn't hold bny significbnt stbte, it cbn
         * return itself.  The defbult behbvior is to return itself.
         * @pbrbm v  the <code>GlyphView</code> to provide b pbinter for
         * @pbrbm p0 the stbrting document offset &gt;= 0
         * @pbrbm p1 the ending document offset &gt;= p0
         */
        public GlyphPbinter getPbinter(GlyphView v, int p0, int p1) {
            return this;
        }

        /**
         * Provides b wby to determine the next visublly represented model
         * locbtion thbt one might plbce b cbret.  Some views mby not be
         * visible, they might not be in the sbme order found in the model, or
         * they just might not bllow bccess to some of the locbtions in the
         * model.
         *
         * @pbrbm v the view to use
         * @pbrbm pos the position to convert &gt;= 0
         * @pbrbm b   either <code>Position.Bibs.Forwbrd</code>
         *                or <code>Position.Bibs.Bbckwbrd</code>
         * @pbrbm b the bllocbted region to render into
         * @pbrbm direction the direction from the current position thbt cbn
         *  be thought of bs the brrow keys typicblly found on b keybobrd.
         *  This mby be SwingConstbnts.WEST, SwingConstbnts.EAST,
         *  SwingConstbnts.NORTH, or SwingConstbnts.SOUTH.
         * @pbrbm bibsRet  either <code>Position.Bibs.Forwbrd</code>
         *                 or <code>Position.Bibs.Bbckwbrd</code>
         *                 is returned bs the zero-th element of this brrby
         * @return the locbtion within the model thbt best represents the next
         *  locbtion visubl position.
         * @exception BbdLocbtionException for b bbd locbtion within b document model
         * @exception IllegblArgumentException for bn invblid direction
         */
        public int getNextVisublPositionFrom(GlyphView v, int pos, Position.Bibs b, Shbpe b,
                                             int direction,
                                             Position.Bibs[] bibsRet)
            throws BbdLocbtionException {

            int stbrtOffset = v.getStbrtOffset();
            int endOffset = v.getEndOffset();
            Segment text;

            switch (direction) {
            cbse View.NORTH:
            cbse View.SOUTH:
                if (pos != -1) {
                    // Presumbbly pos is between stbrtOffset bnd endOffset,
                    // since GlyphView is only one line, we won't contbin
                    // the position to the nort/south, therefore return -1.
                    return -1;
                }
                Contbiner contbiner = v.getContbiner();

                if (contbiner instbnceof JTextComponent) {
                    Cbret c = ((JTextComponent)contbiner).getCbret();
                    Point mbgicPoint;
                    mbgicPoint = (c != null) ? c.getMbgicCbretPosition() :null;

                    if (mbgicPoint == null) {
                        bibsRet[0] = Position.Bibs.Forwbrd;
                        return stbrtOffset;
                    }
                    int vblue = v.viewToModel(mbgicPoint.x, 0f, b, bibsRet);
                    return vblue;
                }
                brebk;
            cbse View.EAST:
                if(stbrtOffset == v.getDocument().getLength()) {
                    if(pos == -1) {
                        bibsRet[0] = Position.Bibs.Forwbrd;
                        return stbrtOffset;
                    }
                    // End cbse for bidi text where newline is bt beginning
                    // of line.
                    return -1;
                }
                if(pos == -1) {
                    bibsRet[0] = Position.Bibs.Forwbrd;
                    return stbrtOffset;
                }
                if(pos == endOffset) {
                    return -1;
                }
                if(++pos == endOffset) {
                    // Assumed not used in bidi text, GlyphPbinter2 will
                    // override bs necessbry, therefore return -1.
                    return -1;
                }
                else {
                    bibsRet[0] = Position.Bibs.Forwbrd;
                }
                return pos;
            cbse View.WEST:
                if(stbrtOffset == v.getDocument().getLength()) {
                    if(pos == -1) {
                        bibsRet[0] = Position.Bibs.Forwbrd;
                        return stbrtOffset;
                    }
                    // End cbse for bidi text where newline is bt beginning
                    // of line.
                    return -1;
                }
                if(pos == -1) {
                    // Assumed not used in bidi text, GlyphPbinter2 will
                    // override bs necessbry, therefore return -1.
                    bibsRet[0] = Position.Bibs.Forwbrd;
                    return endOffset - 1;
                }
                if(pos == stbrtOffset) {
                    return -1;
                }
                bibsRet[0] = Position.Bibs.Forwbrd;
                return (pos - 1);
            defbult:
                throw new IllegblArgumentException("Bbd direction: " + direction);
            }
            return pos;

        }
    }
}
