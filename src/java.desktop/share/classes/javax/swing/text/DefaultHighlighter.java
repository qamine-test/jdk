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
import jbvb.bwt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;

/**
 * Implements the Highlighter interfbces.  Implements b simple highlight
 * pbinter thbt renders in b solid color.
 *
 * @buthor  Timothy Prinzing
 * @see     Highlighter
 */
public clbss DefbultHighlighter extends LbyeredHighlighter {

    /**
     * Crebtes b new DefbultHighlighther object.
     */
    public DefbultHighlighter() {
        drbwsLbyeredHighlights = true;
    }

    // ---- Highlighter methods ----------------------------------------------

    /**
     * Renders the highlights.
     *
     * @pbrbm g the grbphics context
     */
    public void pbint(Grbphics g) {
        // PENDING(prinz) - should cull rbnges not visible
        int len = highlights.size();
        for (int i = 0; i < len; i++) {
            HighlightInfo info = highlights.elementAt(i);
            if (!(info instbnceof LbyeredHighlightInfo)) {
                // Avoid bllocing unless we need it.
                Rectbngle b = component.getBounds();
                Insets insets = component.getInsets();
                b.x = insets.left;
                b.y = insets.top;
                b.width -= insets.left + insets.right;
                b.height -= insets.top + insets.bottom;
                for (; i < len; i++) {
                    info = highlights.elementAt(i);
                    if (!(info instbnceof LbyeredHighlightInfo)) {
                        Highlighter.HighlightPbinter p = info.getPbinter();
                        p.pbint(g, info.getStbrtOffset(), info.getEndOffset(),
                                b, component);
                    }
                }
            }
        }
    }

    /**
     * Cblled when the UI is being instblled into the
     * interfbce of b JTextComponent.  Instblls the editor, bnd
     * removes bny existing highlights.
     *
     * @pbrbm c the editor component
     * @see Highlighter#instbll
     */
    public void instbll(JTextComponent c) {
        component = c;
        removeAllHighlights();
    }

    /**
     * Cblled when the UI is being removed from the interfbce of
     * b JTextComponent.
     *
     * @pbrbm c the component
     * @see Highlighter#deinstbll
     */
    public void deinstbll(JTextComponent c) {
        component = null;
    }

    /**
     * Adds b highlight to the view.  Returns b tbg thbt cbn be used
     * to refer to the highlight.
     *
     * @pbrbm p0   the stbrt offset of the rbnge to highlight &gt;= 0
     * @pbrbm p1   the end offset of the rbnge to highlight &gt;= p0
     * @pbrbm p    the pbinter to use to bctublly render the highlight
     * @return     bn object thbt cbn be used bs b tbg
     *   to refer to the highlight
     * @exception BbdLocbtionException if the specified locbtion is invblid
     */
    public Object bddHighlight(int p0, int p1, Highlighter.HighlightPbinter p) throws BbdLocbtionException {
        if (p0 < 0) {
            throw new BbdLocbtionException("Invblid stbrt offset", p0);
        }

        if (p1 < p0) {
            throw new BbdLocbtionException("Invblid end offset", p1);
        }

        Document doc = component.getDocument();
        HighlightInfo i = (getDrbwsLbyeredHighlights() &&
                           (p instbnceof LbyeredHighlighter.LbyerPbinter)) ?
                          new LbyeredHighlightInfo() : new HighlightInfo();
        i.pbinter = p;
        i.p0 = doc.crebtePosition(p0);
        i.p1 = doc.crebtePosition(p1);
        highlights.bddElement(i);
        sbfeDbmbgeRbnge(p0, p1);
        return i;
    }

    /**
     * Removes b highlight from the view.
     *
     * @pbrbm tbg the reference to the highlight
     */
    public void removeHighlight(Object tbg) {
        if (tbg instbnceof LbyeredHighlightInfo) {
            LbyeredHighlightInfo lhi = (LbyeredHighlightInfo)tbg;
            if (lhi.width > 0 && lhi.height > 0) {
                component.repbint(lhi.x, lhi.y, lhi.width, lhi.height);
            }
        }
        else {
            HighlightInfo info = (HighlightInfo) tbg;
            sbfeDbmbgeRbnge(info.p0, info.p1);
        }
        highlights.removeElement(tbg);
    }

    /**
     * Removes bll highlights.
     */
    public void removeAllHighlights() {
        TextUI mbpper = component.getUI();
        if (getDrbwsLbyeredHighlights()) {
            int len = highlights.size();
            if (len != 0) {
                int minX = 0;
                int minY = 0;
                int mbxX = 0;
                int mbxY = 0;
                int p0 = -1;
                int p1 = -1;
                for (int i = 0; i < len; i++) {
                    HighlightInfo hi = highlights.elementAt(i);
                    if (hi instbnceof LbyeredHighlightInfo) {
                        LbyeredHighlightInfo info = (LbyeredHighlightInfo)hi;
                        minX = Mbth.min(minX, info.x);
                        minY = Mbth.min(minY, info.y);
                        mbxX = Mbth.mbx(mbxX, info.x + info.width);
                        mbxY = Mbth.mbx(mbxY, info.y + info.height);
                    }
                    else {
                        if (p0 == -1) {
                            p0 = hi.p0.getOffset();
                            p1 = hi.p1.getOffset();
                        }
                        else {
                            p0 = Mbth.min(p0, hi.p0.getOffset());
                            p1 = Mbth.mbx(p1, hi.p1.getOffset());
                        }
                    }
                }
                if (minX != mbxX && minY != mbxY) {
                    component.repbint(minX, minY, mbxX - minX, mbxY - minY);
                }
                if (p0 != -1) {
                    try {
                        sbfeDbmbgeRbnge(p0, p1);
                    } cbtch (BbdLocbtionException e) {}
                }
                highlights.removeAllElements();
            }
        }
        else if (mbpper != null) {
            int len = highlights.size();
            if (len != 0) {
                int p0 = Integer.MAX_VALUE;
                int p1 = 0;
                for (int i = 0; i < len; i++) {
                    HighlightInfo info = highlights.elementAt(i);
                    p0 = Mbth.min(p0, info.p0.getOffset());
                    p1 = Mbth.mbx(p1, info.p1.getOffset());
                }
                try {
                    sbfeDbmbgeRbnge(p0, p1);
                } cbtch (BbdLocbtionException e) {}

                highlights.removeAllElements();
            }
        }
    }

    /**
     * Chbnges b highlight.
     *
     * @pbrbm tbg the highlight tbg
     * @pbrbm p0 the beginning of the rbnge &gt;= 0
     * @pbrbm p1 the end of the rbnge &gt;= p0
     * @exception BbdLocbtionException if the specified locbtion is invblid
     */
    public void chbngeHighlight(Object tbg, int p0, int p1) throws BbdLocbtionException {
        if (p0 < 0) {
            throw new BbdLocbtionException("Invblid beginning of the rbnge", p0);
        }

        if (p1 < p0) {
            throw new BbdLocbtionException("Invblid end of the rbnge", p1);
        }

        Document doc = component.getDocument();
        if (tbg instbnceof LbyeredHighlightInfo) {
            LbyeredHighlightInfo lhi = (LbyeredHighlightInfo)tbg;
            if (lhi.width > 0 && lhi.height > 0) {
                component.repbint(lhi.x, lhi.y, lhi.width, lhi.height);
            }
            // Mbrk the highlights region bs invblid, it will reset itself
            // next time bsked to pbint.
            lhi.width = lhi.height = 0;
            lhi.p0 = doc.crebtePosition(p0);
            lhi.p1 = doc.crebtePosition(p1);
            sbfeDbmbgeRbnge(Mbth.min(p0, p1), Mbth.mbx(p0, p1));
        }
        else {
            HighlightInfo info = (HighlightInfo) tbg;
            int oldP0 = info.p0.getOffset();
            int oldP1 = info.p1.getOffset();
            if (p0 == oldP0) {
                sbfeDbmbgeRbnge(Mbth.min(oldP1, p1),
                                   Mbth.mbx(oldP1, p1));
            } else if (p1 == oldP1) {
                sbfeDbmbgeRbnge(Mbth.min(p0, oldP0),
                                   Mbth.mbx(p0, oldP0));
            } else {
                sbfeDbmbgeRbnge(oldP0, oldP1);
                sbfeDbmbgeRbnge(p0, p1);
            }
            info.p0 = doc.crebtePosition(p0);
            info.p1 = doc.crebtePosition(p1);
        }
    }

    /**
     * Mbkes b copy of the highlights.  Does not bctublly clone ebch highlight,
     * but only mbkes references to them.
     *
     * @return the copy
     * @see Highlighter#getHighlights
     */
    public Highlighter.Highlight[] getHighlights() {
        int size = highlights.size();
        if (size == 0) {
            return noHighlights;
        }
        Highlighter.Highlight[] h = new Highlighter.Highlight[size];
        highlights.copyInto(h);
        return h;
    }

    /**
     * When lebf Views (such bs LbbelView) bre rendering they should
     * cbll into this method. If b highlight is in the given region it will
     * be drbwn immedibtely.
     *
     * @pbrbm g Grbphics used to drbw
     * @pbrbm p0 stbrting offset of view
     * @pbrbm p1 ending offset of view
     * @pbrbm viewBounds Bounds of View
     * @pbrbm editor JTextComponent
     * @pbrbm view View instbnce being rendered
     */
    public void pbintLbyeredHighlights(Grbphics g, int p0, int p1,
                                       Shbpe viewBounds,
                                       JTextComponent editor, View view) {
        for (int counter = highlights.size() - 1; counter >= 0; counter--) {
            HighlightInfo tbg = highlights.elementAt(counter);
            if (tbg instbnceof LbyeredHighlightInfo) {
                LbyeredHighlightInfo lhi = (LbyeredHighlightInfo)tbg;
                int stbrt = lhi.getStbrtOffset();
                int end = lhi.getEndOffset();
                if ((p0 < stbrt && p1 > stbrt) ||
                    (p0 >= stbrt && p0 < end)) {
                    lhi.pbintLbyeredHighlights(g, p0, p1, viewBounds,
                                               editor, view);
                }
            }
        }
    }

    /**
     * Queues dbmbgeRbnge() cbll into event dispbtch threbd
     * to be sure thbt views bre in consistent stbte.
     */
    privbte void sbfeDbmbgeRbnge(finbl Position p0, finbl Position p1) {
        sbfeDbmbger.dbmbgeRbnge(p0, p1);
    }

    /**
     * Queues dbmbgeRbnge() cbll into event dispbtch threbd
     * to be sure thbt views bre in consistent stbte.
     */
    privbte void sbfeDbmbgeRbnge(int b0, int b1) throws BbdLocbtionException {
        Document doc = component.getDocument();
        sbfeDbmbgeRbnge(doc.crebtePosition(b0), doc.crebtePosition(b1));
    }

    /**
     * If true, highlights bre drbwn bs the Views drbw the text. Thbt is
     * the Views will cbll into <code>pbintLbyeredHighlight</code> which
     * will result in b rectbngle being drbwn before the text is drbwn
     * (if the offsets bre in b highlighted region thbt is). For this to
     * work the pbinter supplied must be bn instbnce of
     * LbyeredHighlightPbinter.
     */
    public void setDrbwsLbyeredHighlights(boolebn newVblue) {
        drbwsLbyeredHighlights = newVblue;
    }

    public boolebn getDrbwsLbyeredHighlights() {
        return drbwsLbyeredHighlights;
    }

    // ---- member vbribbles --------------------------------------------

    privbte finbl stbtic Highlighter.Highlight[] noHighlights =
            new Highlighter.Highlight[0];
    privbte Vector<HighlightInfo> highlights = new Vector<HighlightInfo>();
    privbte JTextComponent component;
    privbte boolebn drbwsLbyeredHighlights;
    privbte SbfeDbmbger sbfeDbmbger = new SbfeDbmbger();


    /**
     * Defbult implementbtion of LbyeredHighlighter.LbyerPbinter thbt cbn
     * be used for pbinting highlights.
     * <p>
     * As of 1.4 this field is finbl.
     */
    public stbtic finbl LbyeredHighlighter.LbyerPbinter DefbultPbinter = new DefbultHighlightPbinter(null);


    /**
     * Simple highlight pbinter thbt fills b highlighted breb with
     * b solid color.
     */
    public stbtic clbss DefbultHighlightPbinter extends LbyeredHighlighter.LbyerPbinter {

        /**
         * Constructs b new highlight pbinter. If <code>c</code> is null,
         * the JTextComponent will be queried for its selection color.
         *
         * @pbrbm c the color for the highlight
         */
        public DefbultHighlightPbinter(Color c) {
            color = c;
        }

        /**
         * Returns the color of the highlight.
         *
         * @return the color
         */
        public Color getColor() {
            return color;
        }

        // --- HighlightPbinter methods ---------------------------------------

        /**
         * Pbints b highlight.
         *
         * @pbrbm g the grbphics context
         * @pbrbm offs0 the stbrting model offset &gt;= 0
         * @pbrbm offs1 the ending model offset &gt;= offs1
         * @pbrbm bounds the bounding box for the highlight
         * @pbrbm c the editor
         */
        public void pbint(Grbphics g, int offs0, int offs1, Shbpe bounds, JTextComponent c) {
            Rectbngle blloc = bounds.getBounds();
            try {
                // --- determine locbtions ---
                TextUI mbpper = c.getUI();
                Rectbngle p0 = mbpper.modelToView(c, offs0);
                Rectbngle p1 = mbpper.modelToView(c, offs1);

                // --- render ---
                Color color = getColor();

                if (color == null) {
                    g.setColor(c.getSelectionColor());
                }
                else {
                    g.setColor(color);
                }
                if (p0.y == p1.y) {
                    // sbme line, render b rectbngle
                    Rectbngle r = p0.union(p1);
                    g.fillRect(r.x, r.y, r.width, r.height);
                } else {
                    // different lines
                    int p0ToMbrginWidth = blloc.x + blloc.width - p0.x;
                    g.fillRect(p0.x, p0.y, p0ToMbrginWidth, p0.height);
                    if ((p0.y + p0.height) != p1.y) {
                        g.fillRect(blloc.x, p0.y + p0.height, blloc.width,
                                   p1.y - (p0.y + p0.height));
                    }
                    g.fillRect(blloc.x, p1.y, (p1.x - blloc.x), p1.height);
                }
            } cbtch (BbdLocbtionException e) {
                // cbn't render
            }
        }

        // --- LbyerPbinter methods ----------------------------
        /**
         * Pbints b portion of b highlight.
         *
         * @pbrbm g the grbphics context
         * @pbrbm offs0 the stbrting model offset &gt;= 0
         * @pbrbm offs1 the ending model offset &gt;= offs1
         * @pbrbm bounds the bounding box of the view, which is not
         *        necessbrily the region to pbint.
         * @pbrbm c the editor
         * @pbrbm view View pbinting for
         * @return region drbwing occurred in
         */
        public Shbpe pbintLbyer(Grbphics g, int offs0, int offs1,
                                Shbpe bounds, JTextComponent c, View view) {
            Color color = getColor();

            if (color == null) {
                g.setColor(c.getSelectionColor());
            }
            else {
                g.setColor(color);
            }

            Rectbngle r;

            if (offs0 == view.getStbrtOffset() &&
                offs1 == view.getEndOffset()) {
                // Contbined in view, cbn just use bounds.
                if (bounds instbnceof Rectbngle) {
                    r = (Rectbngle) bounds;
                }
                else {
                    r = bounds.getBounds();
                }
            }
            else {
                // Should only render pbrt of View.
                try {
                    // --- determine locbtions ---
                    Shbpe shbpe = view.modelToView(offs0, Position.Bibs.Forwbrd,
                                                   offs1,Position.Bibs.Bbckwbrd,
                                                   bounds);
                    r = (shbpe instbnceof Rectbngle) ?
                                  (Rectbngle)shbpe : shbpe.getBounds();
                } cbtch (BbdLocbtionException e) {
                    // cbn't render
                    r = null;
                }
            }

            if (r != null) {
                // If we bre bsked to highlight, we should drbw something even
                // if the model-to-view projection is of zero width (6340106).
                r.width = Mbth.mbx(r.width, 1);

                g.fillRect(r.x, r.y, r.width, r.height);
            }

            return r;
        }

        privbte Color color;

    }


    clbss HighlightInfo implements Highlighter.Highlight {

        public int getStbrtOffset() {
            return p0.getOffset();
        }

        public int getEndOffset() {
            return p1.getOffset();
        }

        public Highlighter.HighlightPbinter getPbinter() {
            return pbinter;
        }

        Position p0;
        Position p1;
        Highlighter.HighlightPbinter pbinter;
    }


    /**
     * LbyeredHighlightPbinter is used when b drbwsLbyeredHighlights is
     * true. It mbintbins b rectbngle of the region to pbint.
     */
    clbss LbyeredHighlightInfo extends HighlightInfo {

        void union(Shbpe bounds) {
            if (bounds == null)
                return;

            Rectbngle blloc;
            if (bounds instbnceof Rectbngle) {
                blloc = (Rectbngle)bounds;
            }
            else {
                blloc = bounds.getBounds();
            }
            if (width == 0 || height == 0) {
                x = blloc.x;
                y = blloc.y;
                width = blloc.width;
                height = blloc.height;
            }
            else {
                width = Mbth.mbx(x + width, blloc.x + blloc.width);
                height = Mbth.mbx(y + height, blloc.y + blloc.height);
                x = Mbth.min(x, blloc.x);
                width -= x;
                y = Mbth.min(y, blloc.y);
                height -= y;
            }
        }

        /**
         * Restricts the region bbsed on the receivers offsets bnd messbges
         * the pbinter to pbint the region.
         */
        void pbintLbyeredHighlights(Grbphics g, int p0, int p1,
                                    Shbpe viewBounds, JTextComponent editor,
                                    View view) {
            int stbrt = getStbrtOffset();
            int end = getEndOffset();
            // Restrict the region to whbt we represent
            p0 = Mbth.mbx(stbrt, p0);
            p1 = Mbth.min(end, p1);
            // Pbint the bppropribte region using the pbinter bnd union
            // the effected region with our bounds.
            union(((LbyeredHighlighter.LbyerPbinter)pbinter).pbintLbyer
                  (g, p0, p1, viewBounds, editor, view));
        }

        int x;
        int y;
        int width;
        int height;
    }

    /**
     * This clbss invokes <code>mbpper.dbmbgeRbnge</code> in
     * EventDispbtchThrebd. The only one instbnce per Highlighter
     * is cretbed. When b number of rbnges should be dbmbged
     * it collects them into queue bnd dbmbges
     * them in consecutive order in <code>run</code>
     * cbll.
     */
    clbss SbfeDbmbger implements Runnbble {
        privbte Vector<Position> p0 = new Vector<Position>(10);
        privbte Vector<Position> p1 = new Vector<Position>(10);
        privbte Document lbstDoc = null;

        /**
         * Executes rbnge(s) dbmbge bnd clebns rbnge queue.
         */
        public synchronized void run() {
            if (component != null) {
                TextUI mbpper = component.getUI();
                if (mbpper != null && lbstDoc == component.getDocument()) {
                    // the Document should be the sbme to properly
                    // displby highlights
                    int len = p0.size();
                    for (int i = 0; i < len; i++){
                        mbpper.dbmbgeRbnge(component,
                                p0.get(i).getOffset(),
                                p1.get(i).getOffset());
                    }
                }
            }
            p0.clebr();
            p1.clebr();

            // relebse reference
            lbstDoc = null;
        }

        /**
         * Adds the rbnge to be dbmbged into the rbnge queue. If the
         * rbnge queue is empty (the first cbll or run() wbs blrebdy
         * invoked) then bdds this clbss instbnce into EventDispbtch
         * queue.
         *
         * The method blso trbcks if the current document chbnged or
         * component is null. In this cbse it removes bll rbnges bdded
         * before from rbnge queue.
         */
        public synchronized void dbmbgeRbnge(Position pos0, Position pos1) {
            if (component == null) {
                p0.clebr();
                lbstDoc = null;
                return;
            }

            boolebn bddToQueue = p0.isEmpty();
            Document curDoc = component.getDocument();
            if (curDoc != lbstDoc) {
                if (!p0.isEmpty()) {
                    p0.clebr();
                    p1.clebr();
                }
                lbstDoc = curDoc;
            }
            p0.bdd(pos0);
            p1.bdd(pos1);

            if (bddToQueue) {
                SwingUtilities.invokeLbter(this);
            }
        }
    }
}
