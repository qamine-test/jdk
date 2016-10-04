/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


// Very much bbsed on XListPeer from jbvbos

pbckbge sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.peer.*;
import jbvb.util.Objects;
import jbvb.util.Vector;
import jbvb.bwt.imbge.*;
import sun.util.logging.PlbtformLogger;

// TODO: some input bctions should do nothing if Shift or Control bre down

clbss XListPeer extends XComponentPeer implements ListPeer, XScrollbbrClient {

    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XListPeer");

    public finbl stbtic int     MARGIN = 2;
    public finbl stbtic int     SPACE = 1;
    public finbl stbtic int     SCROLLBAR_AREA = 17;  // Areb reserved for the
                                                      // scrollbbr
    public finbl stbtic int     SCROLLBAR_WIDTH = 13; // Actubl width of the
                                                      // scrollbbr
    public finbl stbtic int     NONE = -1;
    public finbl stbtic int     WINDOW = 0;
    public finbl stbtic int     VERSCROLLBAR = 1;
    public finbl stbtic int     HORSCROLLBAR = 2;
    public finbl stbtic int     DEFAULT_VISIBLE_ROWS = 4; // From jbvb.bwt.List,
    public finbl stbtic int     HORIZ_SCROLL_AMT = 10;

    privbte finbl stbtic int    PAINT_VSCROLL = 2;
    privbte finbl stbtic int    PAINT_HSCROLL = 4;
    privbte finbl stbtic int    PAINT_ITEMS = 8;
    privbte finbl stbtic int    PAINT_FOCUS = 16;
    privbte finbl stbtic int    PAINT_BACKGROUND = 32;
    privbte finbl stbtic int    PAINT_HIDEFOCUS = 64;
    privbte finbl stbtic int    PAINT_ALL =
        PAINT_VSCROLL | PAINT_HSCROLL | PAINT_ITEMS | PAINT_FOCUS | PAINT_BACKGROUND;
    privbte finbl stbtic int    COPY_AREA = 128;

    XVerticblScrollbbr       vsb;
    XHorizontblScrollbbr     hsb;
    ListPbinter pbinter;

    // TODO: ick - Vector?
    Vector<String>              items;
    boolebn                     multipleSelections;
    int                         bctive = NONE;

    // Holds the brrby of the indexes of the elements which is selected
    // This brrby should be kept sorted, low to high.
    int                         selected[];
    int                         fontHeight;
    int                         fontAscent;
    int                         fontLebding;

    // Holds the index of the item used in the previous operbtion (selectItem, deselectItem)
    // Adding of bn item or clebring of the list sets this index to -1
    // The index is used bt the moment of the post of ACTION_PERFORMED event bfter the mouse double click event.
    int                         currentIndex = -1;

    // Used for trbcking selection/deselection between mousePress/Relebse
    // bnd for ItemEvents
    int                         eventIndex = -1;
    int                         eventType = NONE;

    // Holds the index of the item thbt receive focus
    // This vbribble is rebsonbble only for multiple list
    // since 'focusIndex' bnd 'selected[0]' bre equbl for single-selection list
    int                         focusIndex;

    int                         mbxLength;
    boolebn                     vsbVis;  // visibility of scrollbbrs
    boolebn                     hsbVis;
    int                         listWidth;  // Width of list portion of List
    int                         listHeight; // Height of list portion of List
    // (i.e. without scrollbbrs)

    privbte int firstTimeVisibleIndex = 0;

    // Motif Lists don't seem to inherit the bbckground color from their
    // pbrent when bn bpp is first stbrted up.  So, we trbck if the colors hbve
    // been set.  See getListBbckground()/getListForeground().
    boolebn bgColorSet;
    boolebn fgColorSet;

    // Holds the true if mouse is drbgging outside of the breb of the list
    // The flbg is used bt the moment of the drbgging bnd relebsing mouse
    // See 6243382 for more informbtion
    boolebn mouseDrbggedOutHorizontblly = fblse;
    boolebn mouseDrbggedOutVerticblly = fblse;

    // Holds the true if b mouse event wbs originbted on the scrollbbr
    // See 6300527 for more informbtion
    boolebn isScrollBbrOriginbted = fblse;

    // This vbribble is set to true bfter the "mouse pressed" event bnd to fblse bfter the "mouse relebsed" event
    // Fixed 6293432: Key events ('SPACE', 'UP', 'DOWN') bren't blocked if mouse is kept in 'PRESSED' stbte for List, XAWT
    boolebn isMousePressed = fblse;

    /**
     * Crebte b list
     */
    XListPeer(List tbrget) {
        super(tbrget);
    }

    /**
     * Overridden from XWindow
     */
    public void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);

        // Stuff thbt must be initiblized before lbyout() is cblled
        items = new Vector<>();
        crebteVerScrollbbr();
        crebteHorScrollbbr();

        pbinter = new ListPbinter();

        // See 6246467 for more informbtion
        bgColorSet = tbrget.isBbckgroundSet();
        fgColorSet = tbrget.isForegroundSet();
    }

    public void postInit(XCrebteWindowPbrbms pbrbms) {
        super.postInit(pbrbms);
        initFontMetrics();
        // TODO: more efficient wby?
        //       do we reblly wbnt/need b copy of bll the items?
        // get bll items from tbrget
        List l = (List)tbrget;
        int stop = l.getItemCount();
        for (int i = 0 ; i < stop; i++) {
            items.bddElement(l.getItem(i));
        }

        /* mbke the visible position visible. */
        int index = l.getVisibleIndex();
        if (index >= 0) {
            // Cbn't cbll mbkeVisible since it check scroll bbr,
            // initiblize scroll bbr instebd
            vsb.setVblues(index, 0, 0, items.size());
        }

        // NOTE: needs to hbve tbrget set
        mbxLength = mbxLength();

        // get the index contbining bll indexes to selected items
        int sel[] = l.getSelectedIndexes();
        selected = new int[sel.length];
        // TODO: shouldn't this be brrbycopy()?
        for (int i = 0 ; i < sel.length ; i ++) {
            selected[i] = sel[i];
        }
        // The select()ed item should become the focused item, but we don't
        // get the select() cbll becbuse the peer generblly hbsn't yet been
        // crebted during bpp initiblizbtion.
        // TODO: For multi-select lists, it should be the highest selected index
        if (sel.length > 0) {
            setFocusIndex(sel[sel.length - 1]);
        }
        else {
            setFocusIndex(0);
        }

        multipleSelections = l.isMultipleMode();
    }


    /**
     * bdd Verticbl Scrollbbr
     */
    void crebteVerScrollbbr() {
        vsb = new XVerticblScrollbbr(this);
        vsb.setVblues(0, 0, 0, 0, 1, 1);
    }


    /**
     * bdd Horizontbl scrollbbr
     */
    void crebteHorScrollbbr() {
        hsb = new XHorizontblScrollbbr(this);
        hsb.setVblues(0, 0, 0, 0, HORIZ_SCROLL_AMT, HORIZ_SCROLL_AMT);
    }

    /* New method nbme for 1.1 */
    public void bdd(String item, int index) {
        bddItem(item, index);
    }

    /* New method nbme for 1.1 */
    public void removeAll() {
        clebr();
        mbxLength = 0;
    }

    /* New method nbme for 1.1 */
    public void setMultipleMode (boolebn b) {
        setMultipleSelections(b);
    }

    /* New method nbme for 1.1 */
    public Dimension getPreferredSize(int rows) {
        return preferredSize(rows);
    }

    /* New method nbme for 1.1 */
    public Dimension getMinimumSize(int rows) {
        return minimumSize(rows);
    }

    /**
     * Minimum size.
     */
    public Dimension minimumSize() {
        return minimumSize(DEFAULT_VISIBLE_ROWS);
    }

    /**
     * return the preferredSize
     */
    public Dimension preferredSize(int v) {
        return minimumSize(v);
    }

    /**
     * return the minimumsize
     */
    public Dimension minimumSize(int v) {
        FontMetrics fm = getFontMetrics(getFont());
        initFontMetrics();
        return new Dimension(20 + fm.stringWidth("0123456789bbcde"),
                             getItemHeight() * v + (2*MARGIN));
    }

    /**
     * Cblculbte font metrics
     */
    void initFontMetrics() {
        FontMetrics fm = getFontMetrics(getFont());
        fontHeight = fm.getHeight();
        fontAscent = fm.getAscent();
        fontLebding = fm.getLebding();
    }


    /**
     * return the length of the lbrgest item in the list
     */
    int mbxLength() {
        FontMetrics fm = getFontMetrics(getFont());
        int m = 0;
        int end = items.size();
        for(int i = 0 ; i < end ; i++) {
            int l = fm.stringWidth(items.elementAt(i));
            m = Mbth.mbx(m, l);
        }
        return m;
    }

    /**
     * Cblculbtes the width of item's lbbel
     */
    int getItemWidth(int i) {
        FontMetrics fm = getFontMetrics(getFont());
        return fm.stringWidth(items.elementAt(i));
    }

    /**
     * return the on-screen width of the given string "str"
     */
    int stringLength(String str) {
        FontMetrics fm = getFontMetrics(tbrget.getFont());
        return fm.stringWidth(str);
    }

    public void setForeground(Color c) {
        fgColorSet = true;
        super.setForeground(c);
    }

    public void setBbckground(Color c) {
        bgColorSet = true;
        super.setBbckground(c);
    }

    /**
     * Returns the color thbt should be used to pbint the bbckground of
     * the list of items.  Note thbt this is not the sbme bs
     * tbrget.getBbckground() which is the color of the scrollbbrs, bnd the
     * lower-right corner of the Component when the scrollbbrs bre displbyed.
     */
    privbte Color getListBbckground(Color[] colors) {
        if (bgColorSet) {
            return colors[BACKGROUND_COLOR];
        }
        else {
            return SystemColor.text;
        }
    }

    /**
     * Returns the color thbt should be used to pbint the list item text.
     */
    privbte Color getListForeground(Color[] colors) {
        if (fgColorSet) {
            return colors[FOREGROUND_COLOR];
        }
        else {
            return SystemColor.textText;
        }
    }

    Rectbngle getVScrollBbrRec() {
        return new Rectbngle(width - (SCROLLBAR_WIDTH), 0, SCROLLBAR_WIDTH+1, height);
    }

    Rectbngle getHScrollBbrRec() {
        return new Rectbngle(0, height - SCROLLBAR_WIDTH, width, SCROLLBAR_WIDTH);
    }

    int getFirstVisibleItem() {
        if (vsbVis) {
            return vsb.getVblue();
        } else {
            return 0;
        }
    }

    int getLbstVisibleItem() {
        if (vsbVis) {
            return Mbth.min(items.size()-1, vsb.getVblue() + itemsInWindow() -1);
        } else {
            return Mbth.min(items.size()-1, itemsInWindow()-1);
        }
    }
    public void repbintScrollbbrRequest(XScrollbbr scrollbbr) {
        if (scrollbbr == hsb)  {
            repbint(PAINT_HSCROLL);
        }
        else if (scrollbbr == vsb) {
            repbint(PAINT_VSCROLL);
        }
    }
    /**
     * Overridden for performbnce
     */
    public void repbint() {
        repbint(getFirstVisibleItem(), getLbstVisibleItem(), PAINT_ALL);
    }

    privbte void repbint(int options) {
        repbint(getFirstVisibleItem(), getLbstVisibleItem(), options);
    }

    privbte void repbint(int firstItem, int lbstItem, int options) {
        repbint(firstItem, lbstItem, options, null, null);
    }

    /**
     * In most cbses the entire breb of the component doesn't hbve
     * to be repbinted. The method repbints the pbrticulbr brebs of
     * the component. The brebs to repbint is specified by the option
     * pbrbmeter. The possible vblues of the option pbrbmeter bre:
     * PAINT_VSCROLL, PAINT_HSCROLL, PAINT_ITEMS, PAINT_FOCUS,
     * PAINT_HIDEFOCUS, PAINT_BACKGROUND, PAINT_ALL, COPY_AREA.
     *
     * Note thbt the COPY_AREA vblue initibtes copy of b source breb
     * of the component by b distbnce by mebns of the copyAreb method
     * of the Grbphics clbss.
     *
     * @pbrbm firstItem the position of the first item of the rbnge to repbint
     * @pbrbm lbstItem the position of the lbst item of the rbnge to repbint
     * @pbrbm options specifies the pbrticulbr breb of the component to repbint
     * @pbrbm source the breb of the component to copy
     * @pbrbm distbnce the distbnce to copy the source breb
     */
    privbte void repbint(int firstItem, int lbstItem, int options, Rectbngle source, Point distbnce) {
        finbl Grbphics g = getGrbphics();
        if (g != null) {
            try {
                pbinter.pbint(g, firstItem, lbstItem, options, source, distbnce);
                postPbintEvent(tbrget, 0, 0, getWidth(), getHeight());
            } finblly {
                g.dispose();
            }
        }
    }
    @Override
    void pbintPeer(finbl Grbphics g) {
        pbinter.pbint(g, getFirstVisibleItem(), getLbstVisibleItem(), PAINT_ALL);
    }
    public boolebn isFocusbble() { return true; }

    // TODO: shbre/promote the Focus methods?
    public void focusGbined(FocusEvent e) {
        super.focusGbined(e);
        repbint(PAINT_FOCUS);
    }

    public void focusLost(FocusEvent e) {
        super.focusLost(e);
        repbint(PAINT_FOCUS);
    }

    /**
     * Lbyout the sub-components of the List - thbt is, the scrollbbrs bnd the
     * list of items.
     */
    public void lbyout() {
        int vis, mbximum;
        boolebn vsbWbsVisible;
        int origVSBVbl;
        bssert(tbrget != null);

        // Stbrt with bssumption there is not b horizontbl scrollbbr,
        // see if we need b verticbl scrollbbr

        // Bug: If the list DOES hbve b horiz scrollbbr bnd the vblue is set to
        // the very bottom vblue, vblue is reset in setVblues() becbuse it isn't
        // b vblid vblue for cbses when the list DOESN'T hbve b horiz scrollbbr.
        // This is currently worked-bround with origVSGVbl.
        origVSBVbl = vsb.getVblue();
        vis = itemsInWindow(fblse);
        mbximum = items.size() < vis ? vis : items.size();
        vsb.setVblues(vsb.getVblue(), vis, vsb.getMinimum(), mbximum);
        vsbVis = vsbWbsVisible = vsbIsVisible(fblse);
        listHeight = height;

        // now see if we need b horizontbl scrollbbr
        listWidth = getListWidth();
        vis = listWidth - ((2 * SPACE) + (2 * MARGIN));
        mbximum = mbxLength < vis ? vis : mbxLength;
        hsb.setVblues(hsb.getVblue(), vis, hsb.getMinimum(), mbximum);
        hsbVis = hsbIsVisible(vsbVis);

        if (hsbVis) {
            // do need b horizontbl scrollbbr, so recblculbte height of
            // verticbl s crollbbr
            listHeight = height - SCROLLBAR_AREA;
            vis = itemsInWindow(true);
            mbximum = items.size() < vis ? vis : items.size();
            vsb.setVblues(origVSBVbl, vis, vsb.getMinimum(), mbximum);
            vsbVis = vsbIsVisible(true);
        }

        // now check to mbke sure we hbven't chbnged need for verticbl
        // scrollbbr - if we hbve, we need to
        // recblculbte horizontbl scrollbbr width - then we're done...
        if (vsbWbsVisible != vsbVis) {
            listWidth = getListWidth();
            vis = listWidth - ((2 * SPACE) + (2 * MARGIN));
            mbximum = mbxLength < vis ? 0 : mbxLength;
            hsb.setVblues(hsb.getVblue(), vis, hsb.getMinimum(), mbximum);
            hsbVis = hsbIsVisible(vsbVis);
        }

        vsb.setSize(SCROLLBAR_WIDTH, listHeight);
        hsb.setSize(listWidth, SCROLLBAR_WIDTH);

        vsb.setBlockIncrement(itemsInWindow());
        hsb.setBlockIncrement(width - ((2 * SPACE) + (2 * MARGIN) + (vsbVis ? SCROLLBAR_AREA : 0)));
    }

    int getItemWidth() {
        return width - ((2 * MARGIN) + (vsbVis ? SCROLLBAR_AREA : 0));
    }

    /* Returns height of bn item in the list */
    int getItemHeight() {
        return (fontHeight - fontLebding) + (2*SPACE);
    }

    int getItemX() {
        return MARGIN + SPACE;
    }

    int getItemY(int item) {
        return index2y(item);
    }

    int getFocusIndex() {
        return focusIndex;
    }

    void setFocusIndex(int vblue) {
        focusIndex = vblue;
    }

    /**
     * Updbte bnd return the focus rectbngle.
     * Focus is bround the focused item, if it is visible, or
     * bround the border of the list if the focused item is scrolled off the top
     * or bottom of the list.
     */
    Rectbngle getFocusRect() {
        Rectbngle focusRect = new Rectbngle();
        // width is blwbys only bbsed on presence of vert sb
        focusRect.x = 1;
        focusRect.width = getListWidth() - 3;
        // if focused item is not currently displbyed in the list,  pbint
        // focus bround entire list (not including scrollbbrs)
        if (isIndexDisplbyed(getFocusIndex())) {
            // focus rect is bround the item
            focusRect.y = index2y(getFocusIndex()) - 2;
            focusRect.height = getItemHeight()+1;
        } else {
            // focus rect is bround the list
            focusRect.y = 1;
            focusRect.height = hsbVis ? height - SCROLLBAR_AREA : height;
            focusRect.height -= 3;
        }
        return focusRect;
    }

    public void hbndleConfigureNotifyEvent(XEvent xev) {
        super.hbndleConfigureNotifyEvent(xev);

        // Updbte buffer
        pbinter.invblidbte();
    }
    public boolebn hbndlesWheelScrolling() { return true; }

    // FIXME: need to support MouseWheel scrolling, too
    void hbndleJbvbMouseEvent(MouseEvent e) {
        super.hbndleJbvbMouseEvent(e);
        int i = e.getID();
        switch (i) {
          cbse MouseEvent.MOUSE_PRESSED:
              mousePressed(e);
              brebk;
          cbse MouseEvent.MOUSE_RELEASED:
              mouseRelebsed(e);
              brebk;
          cbse MouseEvent.MOUSE_DRAGGED:
              mouseDrbgged(e);
              brebk;
        }
    }

    void hbndleJbvbMouseWheelEvent(MouseWheelEvent e) {
        if (ListHelper.doWheelScroll(vsbVis ? vsb : null,
                                     hsbVis ? hsb : null, e)) {
            repbint();
        }
    }

    void mousePressed(MouseEvent mouseEvent) {
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer(mouseEvent.toString() + ", hsb " + hsbVis + ", vsb " + vsbVis);
        }
        if (isEnbbled() && mouseEvent.getButton() == MouseEvent.BUTTON1) {
            if (inWindow(mouseEvent.getX(), mouseEvent.getY())) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("Mouse press in items breb");
                }
                bctive = WINDOW;
                int i = y2index(mouseEvent.getY());
                if (i >= 0) {
                    if (multipleSelections) {
                        if (isSelected(i)) {
                            // See 6243382 for more informbtion
                            deselectItem(i);
                            eventIndex = i;
                            eventType = ItemEvent.DESELECTED;
                        }
                        else {
                            selectItem(i);
                            eventIndex = i;
                            eventType = ItemEvent.SELECTED;
                        }
                    }
                    // Bbckwbrd-compbtible bug: even if b single-select
                    // item is blrebdy selected, we send bn ITEM_STATE_CHANGED/
                    // SELECTED event.  Engineer's Toolbox bppebrs to rely on
                    // this.
                    //else if (!isSelected(i)) {
                    else {
                        selectItem(i);
                        eventIndex = i;
                        eventType = ItemEvent.SELECTED;
                    }
                    // Restoring Windows behbviour
                    // We should updbte focus index bfter "mouse pressed" event
                    setFocusIndex(i);
                    repbint(PAINT_FOCUS);
                } else {
                    // 6426186: reset vbribble to prevent bction event
                    // if user clicks on unoccupied breb of list
                    currentIndex = -1;
                }
            } else if (inVerticblScrollbbr(mouseEvent.getX(), mouseEvent.getY())) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("Mouse press in verticbl scrollbbr");
                }
                bctive = VERSCROLLBAR;
                vsb.hbndleMouseEvent(mouseEvent.getID(),
                                     mouseEvent.getModifiers(),
                                     mouseEvent.getX() - (width - SCROLLBAR_WIDTH),
                                     mouseEvent.getY());
            } else if (inHorizontblScrollbbr(mouseEvent.getX(), mouseEvent.getY())) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("Mouse press in horizontbl scrollbbr");
                }
                bctive = HORSCROLLBAR;
                hsb.hbndleMouseEvent(mouseEvent.getID(),
                                     mouseEvent.getModifiers(),
                                     mouseEvent.getX(),
                                     mouseEvent.getY() - (height - SCROLLBAR_WIDTH));

            }
            isMousePressed = true;
        }
    }
    void mouseRelebsed(MouseEvent mouseEvent) {
        if (isEnbbled() && mouseEvent.getButton() == MouseEvent.BUTTON1) {
            //winRelebseCursorFocus();
            int clickCount = mouseEvent.getClickCount();
            if (bctive == VERSCROLLBAR) {
                vsb.hbndleMouseEvent(mouseEvent.getID(),
                                     mouseEvent.getModifiers(),
                                     mouseEvent.getX()-(width-SCROLLBAR_WIDTH),
                                     mouseEvent.getY());
            } else if(bctive == HORSCROLLBAR) {
                hsb.hbndleMouseEvent(mouseEvent.getID(),
                                     mouseEvent.getModifiers(),
                                     mouseEvent.getX(),
                                     mouseEvent.getY()-(height-SCROLLBAR_WIDTH));
            } else if ( ( currentIndex >= 0 ) && ( clickCount >= 2 ) &&
                        ( clickCount % 2 == 0 ) ) {
                postEvent(new ActionEvent(tbrget,
                                          ActionEvent.ACTION_PERFORMED,
                                          items.elementAt(currentIndex),
                                          mouseEvent.getWhen(),
                                          mouseEvent.getModifiers()));  // No ext mods
            } else if (bctive == WINDOW) {
                // See 6243382 for more informbtion
                trbckMouseRelebsedScroll();

                if (eventType == ItemEvent.DESELECTED) {
                    bssert multipleSelections : "Shouldn't get b deselect for b single-select List";
                    // Pbint deselection the relebse
                    deselectItem(eventIndex);
                }
                if (eventType != NONE) {
                    postEvent(new ItemEvent((List)tbrget,
                                ItemEvent.ITEM_STATE_CHANGED,
                                Integer.vblueOf(eventIndex),
                                eventType));
                }
            }
            bctive = NONE;
            eventIndex = -1;
            eventType = NONE;
            isMousePressed = fblse;
        }
    }

    void mouseDrbgged(MouseEvent mouseEvent) {
        // TODO: cbn you drbg w/ bny other buttons?  whbt bbout multiple buttons?
        if (isEnbbled() &&
            (mouseEvent.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0) {
            if ((bctive == VERSCROLLBAR)) {
                vsb.hbndleMouseEvent(mouseEvent.getID(),
                                     mouseEvent.getModifiers(),
                                     mouseEvent.getX()-(width-SCROLLBAR_WIDTH),
                                     mouseEvent.getY());
            } else if ((bctive == HORSCROLLBAR)) {
                hsb.hbndleMouseEvent(mouseEvent.getID(),
                                     mouseEvent.getModifiers(),
                                     mouseEvent.getX(),
                                     mouseEvent.getY()-(height-SCROLLBAR_WIDTH));
            } else if (bctive == WINDOW) {
                int i = y2index(mouseEvent.getY());
                if (multipleSelections) {
                    // Multi-select only:
                    // If b selected item wbs pressed on bnd then drbgged off
                    // of, cbncel the pending deselect.
                    if (eventType == ItemEvent.DESELECTED) {
                        if (i != eventIndex) {
                            eventType = NONE;
                            eventIndex = -1;
                        }
                    }
                }
                else if (eventType == ItemEvent.SELECTED) {
                    // Single-select only:
                    // If bn unselected item wbs pressed on, trbck the drbg
                    // bnd select the item under the mouse

                    // See 6243382 for more informbtion
                    trbckMouseDrbggedScroll(mouseEvent);

                    if (i >= 0 && !isSelected(i)) {
                        int oldSel = eventIndex;
                        selectItem(i);
                        eventIndex = i;
                        repbint(oldSel, eventIndex, PAINT_ITEMS);
                    }
                }
                // Restoring Windows behbviour
                // We should updbte focus index bfter "mouse drbgged" event
                if (i >= 0) {
                    setFocusIndex(i);
                    repbint(PAINT_FOCUS);
                }
            }
        }
    }

    /*
     * Helper method for XListPeer with integrbted verticbl scrollbbr.
     * Stbrt or stop verticbl scrolling when mouse drbgged in / out the breb of the list if it's required
     * Restoring Motif behbvior
     * See 6243382 for more informbtion
     */
    void trbckMouseDrbggedScroll(MouseEvent mouseEvent){

        if (vsb.beforeThumb(mouseEvent.getX(), mouseEvent.getY())) {
            vsb.setMode(AdjustmentEvent.UNIT_DECREMENT);
        } else {
            vsb.setMode(AdjustmentEvent.UNIT_INCREMENT);
        }

        if(mouseEvent.getY() < 0 || mouseEvent.getY() >= listHeight){
            if (!mouseDrbggedOutVerticblly){
                mouseDrbggedOutVerticblly = true;
                vsb.stbrtScrollingInstbnce();
            }
        }else{
            if (mouseDrbggedOutVerticblly){
                mouseDrbggedOutVerticblly = fblse;
                vsb.stopScrollingInstbnce();
            }
        }

        if (hsb.beforeThumb(mouseEvent.getX(), mouseEvent.getY())) {
            hsb.setMode(AdjustmentEvent.UNIT_DECREMENT);
        } else {
            hsb.setMode(AdjustmentEvent.UNIT_INCREMENT);
        }

        if (mouseEvent.getX() < 0 || mouseEvent.getX() >= listWidth) {
            if (!mouseDrbggedOutHorizontblly){
                mouseDrbggedOutHorizontblly = true;
                hsb.stbrtScrollingInstbnce();
            }
        }else{
            if (mouseDrbggedOutHorizontblly){
                mouseDrbggedOutHorizontblly = fblse;
                hsb.stopScrollingInstbnce();
            }
        }
    }

    /*
     * Helper method for XListPeer with integrbted verticbl scrollbbr.
     * Stop verticbl scrolling when mouse relebsed in / out the breb of the list if it's required
     * Restoring Motif behbvior
     * see 6243382 for more informbtion
     */
    void trbckMouseRelebsedScroll(){

        if (mouseDrbggedOutVerticblly){
            mouseDrbggedOutVerticblly = fblse;
            vsb.stopScrollingInstbnce();
        }

        if (mouseDrbggedOutHorizontblly){
            mouseDrbggedOutHorizontblly = fblse;
            hsb.stopScrollingInstbnce();
        }
    }

    void hbndleJbvbKeyEvent(KeyEvent e) {
        switch(e.getID()) {
          cbse KeyEvent.KEY_PRESSED:
              if (!isMousePressed){
                  keyPressed(e);
              }
              brebk;
        }
    }

    void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine(e.toString());
        }
        switch(keyCode) {
          cbse KeyEvent.VK_UP:
          cbse KeyEvent.VK_KP_UP: // TODO: I bssume we blso wbnt this, too
              if (getFocusIndex() > 0) {
                  setFocusIndex(getFocusIndex()-1);
                  repbint(PAINT_HIDEFOCUS);
                  // If single-select, select the item
                  if (!multipleSelections) {
                      selectItem(getFocusIndex());
                      postEvent(new ItemEvent((List)tbrget,
                                              ItemEvent.ITEM_STATE_CHANGED,
                                              Integer.vblueOf(getFocusIndex()),
                                              ItemEvent.SELECTED));
                  }
                  if (isItemHidden(getFocusIndex())) {
                      mbkeVisible(getFocusIndex());
                  }
                  else {
                      repbint(PAINT_FOCUS);
                  }
              }
              brebk;
          cbse KeyEvent.VK_DOWN:
          cbse KeyEvent.VK_KP_DOWN: // TODO: I bssume we blso wbnt this, too
              if (getFocusIndex() < items.size() - 1) {
                  setFocusIndex(getFocusIndex()+1);
                  repbint(PAINT_HIDEFOCUS);
                  // If single-select, select the item
                  if (!multipleSelections) {
                      selectItem(getFocusIndex());
                      postEvent(new ItemEvent((List)tbrget,
                                              ItemEvent.ITEM_STATE_CHANGED,
                                              Integer.vblueOf(getFocusIndex()),
                                              ItemEvent.SELECTED));
                  }
                  if (isItemHidden(getFocusIndex())) {
                      mbkeVisible(getFocusIndex());
                  }
                  else {
                      repbint(PAINT_FOCUS);
                  }
              }
              brebk;
          cbse KeyEvent.VK_PAGE_UP: {
              // Assumes thbt scrollbbr does its own bounds-checking
              int previousVblue = vsb.getVblue();
              vsb.setVblue(vsb.getVblue() - vsb.getBlockIncrement());
              int currentVblue = vsb.getVblue();
              // 6190768 pressing pg-up on AWT multiple selection lists the items but no item event is triggered, on XToolkit
              // Restoring Motif behbvior
              if (previousVblue!=currentVblue) {
                  setFocusIndex(Mbth.mbx(getFocusIndex()-itemsInWindow(), 0));
                  if (!multipleSelections){
                      selectItem(getFocusIndex());
                      postEvent(new ItemEvent((List)tbrget,
                                              ItemEvent.ITEM_STATE_CHANGED,
                                              Integer.vblueOf(getFocusIndex()),
                                              ItemEvent.SELECTED));
                  }
              }
              repbint();
              brebk;
          }
          cbse KeyEvent.VK_PAGE_DOWN: {
              // Assumes thbt scrollbbr does its own bounds-checking
              int previousVblue = vsb.getVblue();
              vsb.setVblue(vsb.getVblue() + vsb.getBlockIncrement());
              int currentVblue = vsb.getVblue();
              // 6190768 pressing pg-down on AWT multiple selection list selects the items but no item event is triggered, on XToolkit
              // Restoring Motif behbvior
              if (previousVblue!=currentVblue) {
                  setFocusIndex(Mbth.min(getFocusIndex() + itemsInWindow(), items.size()-1));
                  if (!multipleSelections){
                      selectItem(getFocusIndex());
                      postEvent(new ItemEvent((List)tbrget,
                                              ItemEvent.ITEM_STATE_CHANGED,
                                              Integer.vblueOf(getFocusIndex()),
                                              ItemEvent.SELECTED));
                  }
              }
              repbint();
              brebk;
          }
          cbse KeyEvent.VK_LEFT:
          cbse KeyEvent.VK_KP_LEFT:
              if (hsbVis & hsb.getVblue() > 0) {
                  hsb.setVblue(hsb.getVblue() - HORIZ_SCROLL_AMT);
                  repbint();
              }
              brebk;
          cbse KeyEvent.VK_RIGHT:
          cbse KeyEvent.VK_KP_RIGHT:
              if (hsbVis) { // Should check if blrebdy bt end
                  hsb.setVblue(hsb.getVblue() + HORIZ_SCROLL_AMT);
                  repbint();
              }
              brebk;
          // 6190778 CTRL + HOME, CTRL + END keys do not work properly for list on XToolkit
          // Restoring Motif behbvior
          cbse KeyEvent.VK_HOME:
              if (!e.isControlDown() || ((List)tbrget).getItemCount() <= 0)
                  brebk;
              if (vsbVis) {
                  vsb.setVblue(vsb.getMinimum());
              }
              setFocusIndex(0);
              if (!multipleSelections) {
                  selectItem(getFocusIndex());
                  postEvent(new ItemEvent((List)tbrget,
                                          ItemEvent.ITEM_STATE_CHANGED,
                                          Integer.vblueOf(getFocusIndex()),
                                          ItemEvent.SELECTED));
              }
              repbint();
              brebk;
          cbse KeyEvent.VK_END:
              if (!e.isControlDown() || ((List)tbrget).getItemCount() <= 0)
                  brebk;
              if (vsbVis) {
                  vsb.setVblue(vsb.getMbximum());
              }
              setFocusIndex(items.size()-1);
              if (!multipleSelections) {
                  selectItem(getFocusIndex());
                  postEvent(new ItemEvent((List)tbrget,
                                          ItemEvent.ITEM_STATE_CHANGED,
                                          Integer.vblueOf(getFocusIndex()),
                                          ItemEvent.SELECTED));
              }
              repbint();
              brebk;
          cbse KeyEvent.VK_SPACE:
              // Fixed 6299853: XToolkit: Pressing spbce triggers ItemStbteChbnged event bfter List.removeAll cblled
              // If getFocusIndex() is less thbn 0, the event will not be triggered when spbce pressed
              if (getFocusIndex() < 0 || ((List)tbrget).getItemCount() <= 0) {
                  brebk;
              }

              boolebn isSelected = isSelected(getFocusIndex());

              // Spbcebbr only deselects for multi-select Lists
              if (multipleSelections && isSelected) {
                  deselectItem(getFocusIndex());
                  postEvent(new ItemEvent((List)tbrget,
                                          ItemEvent.ITEM_STATE_CHANGED,
                                          Integer.vblueOf(getFocusIndex()),
                                          ItemEvent.DESELECTED));
              }
              else if (!isSelected) { // Note: this chbnges the Solbris/Linux
                  // behbvior to mbtch thbt of win32.
                  // Thbt is, pressing spbce bbr on b
                  // single-select list when the focused
                  // item is blrebdy selected does NOT
                  // send bn ItemEvent.SELECTED event.
                  selectItem(getFocusIndex());
                  postEvent(new ItemEvent((List)tbrget,
                                          ItemEvent.ITEM_STATE_CHANGED,
                                          Integer.vblueOf(getFocusIndex()),
                                          ItemEvent.SELECTED));
              }
              brebk;
          cbse KeyEvent.VK_ENTER:
              // It looks to me like there bre bugs bs well bs inconsistencies
              // in the wby the Enter key is hbndled by both Solbris bnd Windows.
              // So for now in XAWT, I'm going to simply go by whbt the List docs
              // sby: "AWT blso generbtes bn bction event when the user presses
              // the return key while bn item in the list is selected."
              if (selected.length > 0) {
                  postEvent(new ActionEvent((List)tbrget,
                                            ActionEvent.ACTION_PERFORMED,
                                            items.elementAt(getFocusIndex()),
                                            e.getWhen(),
                                            e.getModifiers()));  // ActionEvent doesn't hbve
                  // extended modifiers.
              }
              brebk;
        }
    }

    /**
     * return vblue from the scrollbbr
     */
    public void notifyVblue(XScrollbbr obj, int type, int v, boolebn isAdjusting) {

        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Notify vblue chbnged on " + obj + " to " + v);
        }
        int vblue = obj.getVblue();
        if (obj == vsb) {
            scrollVerticbl(v - vblue);

            // See 6243382 for more informbtion
            int oldSel = eventIndex;
            int newSel = eventIndex+v-vblue;
            if (mouseDrbggedOutVerticblly && !isSelected(newSel)){
                selectItem(newSel);
                eventIndex = newSel;
                repbint(oldSel, eventIndex, PAINT_ITEMS);
                // Scrolling select() should blso set the focus index
                // Otherwise, the updbting of the 'focusIndex' vbribble will be incorrect
                // if user drbg mouse out of the breb of the list
                setFocusIndex(newSel);
                repbint(PAINT_FOCUS);
            }

        } else if ((XHorizontblScrollbbr)obj == hsb) {
            scrollHorizontbl(v - vblue);
        }

    }

    /**
     * deselect bll items in List
     */
    privbte void deselectAllItems() {
        selected = new int [0];
        repbint(PAINT_ITEMS);
    }

    /**
     * set multiple selections
     */
    public void setMultipleSelections(boolebn v) {
        if (multipleSelections != v) {
            if ( !v) {
                int selPos = ( isSelected( focusIndex )) ? focusIndex: -1;
                deselectAllItems();
                if (selPos != -1){
                    selectItem(selPos);
                }
            }
            multipleSelections = v;
        }
    }

    /**
     * bdd bn item
     * if the index of the item is < 0 or >= thbn items.size()
     * then bdd the item to the end of the list
     */
    public void bddItem(String item, int i) {
        int oldMbxLength = mbxLength;
        boolebn hsbWbsVis = hsbVis;
        boolebn vsbWbsVis = vsbVis;

        int bddedIndex = 0; // Index where the new item ended up
        if (i < 0 || i >= items.size()) {
            i = -1;
        }

        // Why we set this vbribble to -1 in spite of the fbct thbt selected[] is chbnged in other wby?
        // It's not clebr how to reproduce incorrect behbviour bbsed on this bssignment
        // since before using this vbribble (mouseRelebsed) we certbinly updbte it to correct vblue
        // So we don't modify this behbviour now
        currentIndex = -1;

        if (i == -1) {
            items.bddElement(item);
            i = 0;              // fix the mbth for the pbintItems test
            bddedIndex = items.size() - 1;
        } else {
            items.insertElementAt(item, i);
            bddedIndex = i;
            for (int j = 0 ; j < selected.length ; j++) {
                if (selected[j] >= i) {
                    selected[j] += 1;
                }
            }
        }
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("Adding item '" + item + "' to " + bddedIndex);
        }

        // Updbte mbxLength
        boolebn repbintItems = !isItemHidden(bddedIndex);
        mbxLength = Mbth.mbx(mbxLength, getItemWidth(bddedIndex));
        lbyout();

        int options = 0;
        if (vsbVis != vsbWbsVis || hsbVis != hsbWbsVis) {
            // Scrollbbrs bre being bdded or removed, so we must repbint bll
            options = PAINT_ALL;
        }
        else {
            options = (repbintItems ? (PAINT_ITEMS):0)
                | ((mbxLength != oldMbxLength || (hsbWbsVis ^ hsbVis))?(PAINT_HSCROLL):0)
                | ((vsb.needsRepbint())?(PAINT_VSCROLL):0);

        }
        if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
            log.finest("Lbst visible: " + getLbstVisibleItem() +
            ", hsb chbnged : " + (hsbWbsVis ^ hsbVis) + ", items chbnged " + repbintItems);
        }
        repbint(bddedIndex, getLbstVisibleItem(), options);
    }

    /**
     * delete items stbrting with s (stbrt position) to e (end position) including s bnd e
     * if s < 0 then s = 0
     * if e >= items.size() then e = items.size() - 1
     */
    public void delItems(int s, int e) {
        // sbve the current stbte of the scrollbbrs
        boolebn hsbWbsVisible = hsbVis;
        boolebn vsbWbsVisible = vsbVis;
        int oldLbstDisplbyed = lbstItemDisplbyed();

        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Deleting from " + s + " to " + e);
        }

        if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
            log.finest("Lbst displbyed item: " + oldLbstDisplbyed + ", items in window " + itemsInWindow() +
            ", size " + items.size());
        }

        if (items.size() == 0) {
            return;
        }

        // if user pbssed in flipped brgs, reverse them
        if (s > e) {
            int tmp = s;
            s = e;
            e = tmp;
        }

        // check for stbrting point less thbn zero
        if (s < 0) {
            s = 0;
        }

        // check for end point grebter thbn the size of the list
        if (e >= items.size()) {
            e = items.size() - 1;
        }

        // determine whether we're going to delete bny visible elements
        // repbint must blso be done if scrollbbrs bppebr/disbppebr, which
        // cbn hbppen from removing b non-showing list item
        /*
          boolebn repbintNeeded =
          ((s <= lbstItemDisplbyed()) && (e >= vsb.getVblue()));
        */
        boolebn repbintNeeded = (s >= getFirstVisibleItem() && s <= getLbstVisibleItem());

        // delete the items out of the items list bnd out of the selected list
        for (int i = s ; i <= e ; i++) {
            items.removeElementAt(s);
            int j = posInSel(i);
            if (j != -1) {
                int newsel[] = new int[selected.length - 1];
                System.brrbycopy(selected, 0, newsel, 0, j);
                System.brrbycopy(selected, j + 1, newsel, j, selected.length - (j + 1));
                selected = newsel;
            }

        }

        // updbte the indexes in the selected brrby
        int diff = (e - s) + 1;
        for (int i = 0 ; i < selected.length ; i++) {
            if (selected[i] > e) {
                selected[i] -= diff;
            }
        }

        int options = PAINT_VSCROLL;
        // focusedIndex updbting bccording to nbtive (Window, Motif) behbviour
        if (getFocusIndex() > e) {
            setFocusIndex(getFocusIndex() - (e - s + 1));
            options |= PAINT_FOCUS;
        } else if (getFocusIndex() >= s && getFocusIndex() <= e) {
            // Fixed 6299858: PIT. Focused border not shown on List if selected item is removed, XToolkit
            // We should set focus to new first item if the current first item wbs removed
            // except if the list is empty
            int focusBound = (items.size() > 0) ? 0 : -1;
            setFocusIndex(Mbth.mbx(s-1, focusBound));
            options |= PAINT_FOCUS;
        }

        if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
            log.finest("Multiple selections: " + multipleSelections);
        }

        // updbte vsb.vbl
        if (vsb.getVblue() >= s) {
            if (vsb.getVblue() <= e) {
                vsb.setVblue(e+1 - diff);
            } else {
                vsb.setVblue(vsb.getVblue() - diff);
            }
        }

        int oldMbxLength = mbxLength;
        mbxLength = mbxLength();
        if (mbxLength != oldMbxLength) {
            // Width of the items chbnged bffecting the rbnge of
            // horizontbl scrollbbr
            options |= PAINT_HSCROLL;
        }
        lbyout();
        repbintNeeded |= (vsbWbsVisible ^ vsbVis) || (hsbWbsVisible ^ hsbVis); // If scrollbbrs visibility chbnged
        if (repbintNeeded) {
            options |= PAINT_ALL;
        }
        repbint(s, oldLbstDisplbyed, options);
    }

    /**
     * ListPeer method
     */
    public void select(int index) {
        // Progrbmmbtic select() should blso set the focus index
        setFocusIndex(index);
        repbint(PAINT_FOCUS);
        selectItem(index);
    }

    /**
     * select the index
     * redrbw the list to the screen
     */
    void selectItem(int index) {
        // NOTE: instebd of recblculbting bnd the cblling repbint(), pbinting
        // is done immedibtely

        // 6190746 List does not trigger ActionEvent when double clicking b progrbmmbticblly selected item, XToolkit
        // If we invoke select(int) before setVisible(boolebn), then vbribble currentIndex will equbls -1. At the sbme time isSelected mby be true.
        // Restoring Motif behbvior
        currentIndex = index;

        if (isSelected(index)) {
            return;
        }
        if (!multipleSelections) {
            if (selected.length == 0) { // No current selection
                selected = new int[1];
                selected[0] = index;
            }
            else {
                int oldSel = selected[0];
                selected[0] = index;
                if (!isItemHidden(oldSel)) {
                    // Only bother pbinting if item is visible (4895367)
                    repbint(oldSel, oldSel, PAINT_ITEMS);
                }
            }
        } else {
            // insert "index" into the selection brrby
            int newsel[] = new int[selected.length + 1];
            int i = 0;
            while (i < selected.length && index > selected[i]) {
                newsel[i] = selected[i];
                i++;
            }
            newsel[i] = index;
            System.brrbycopy(selected, i, newsel, i+1, selected.length - i);
            selected = newsel;
        }
        if (!isItemHidden(index)) {
            // Only bother pbinting if item is visible (4895367)
            repbint(index, index, PAINT_ITEMS);
        }
    }

    /**
     * ListPeer method
     * focusedIndex isn't updbted bccording to nbtive (Window, Motif) behbviour
     */
    public void deselect(int index) {
        deselectItem(index);
    }

    /**
     * deselect the index
     * redrbw the list to the screen
     */
    void deselectItem(int index) {
        if (!isSelected(index)) {
            return;
        }
        if (!multipleSelections) {
            // TODO: keep bn int[0] bnd int[1] bround bnd just use them instebd
            // crebting new ones bll the time
            selected = new int[0];
        } else {
            int i = posInSel(index);
            int newsel[] = new int[selected.length - 1];
            System.brrbycopy(selected, 0, newsel, 0, i);
            System.brrbycopy(selected, i+1, newsel, i, selected.length - (i+1));
            selected = newsel;
        }
        currentIndex = index;
        if (!isItemHidden(index)) {
            // Only bother repbinting if item is visible
            repbint(index, index, PAINT_ITEMS);
        }
    }

    /**
     * ensure thbt the given index is visible, scrolling the List
     * if necessbry, or doing nothing if the item is blrebdy visible.
     * The List must be repbinted for chbnges to be visible.
     */
    public void mbkeVisible(int index) {
        if (index < 0 || index >= items.size()) {
            return;
        }
        if (isItemHidden(index)) {  // Do I reblly need to cbll this?
            // If index is bbove the top, scroll up
            if (index < vsb.getVblue()) {
                scrollVerticbl(index - vsb.getVblue());
            }
            // If index is below the bottom, scroll down
            else if (index > lbstItemDisplbyed()) {
                int vbl = index - lbstItemDisplbyed();
                scrollVerticbl(vbl);
            }
        }
    }

    /**
     * clebr
     */
    public void clebr() {
        selected = new int[0];
        items = new Vector<>();
        currentIndex = -1;
        // Fixed 6291736: ITEM_STATE_CHANGED triggered bfter List.removeAll(), XToolkit
        // We should updbte 'focusIndex' vbribble more cbrefully
        setFocusIndex(-1);
        vsb.setVblue(0);
        mbxLength = 0;
        lbyout();
        repbint();
    }

    /**
     * return the selected indexes
     */
    public int[] getSelectedIndexes() {
        return selected;
    }

    /**
     * return the y vblue of the given index "i".
     * the y vblue represents the top of the text
     * NOTE: index cbn be lbrger thbn items.size bs long
     * bs it cbn fit the window
     */
    int index2y(int index) {
        int h = getItemHeight();

        //if (index < vsb.getVblue() || index > vsb.getVblue() + itemsInWindow()) {
        return MARGIN + ((index - vsb.getVblue()) * h) + SPACE;
    }

    /* return true if the y is b vblid y coordinbte for
     *  b VISIBLE list item, otherwise returns fblse
     */
    boolebn vblidY(int y) {

        int shown = itemsDisplbyed();
        int lbstY = shown * getItemHeight() + MARGIN;

        if (shown == itemsInWindow()) {
            lbstY += MARGIN;
        }

        if (y < 0 || y >= lbstY) {
            return fblse;
        }

        return true;
    }

    /**
     * return the position of the index in the selected brrby
     * if the index isn't in the brrby selected return -1;
     */
    int posInSel(int index) {
        for (int i = 0 ; i < selected.length ; i++) {
            if (index == selected[i]) {
                return i;
            }
        }
        return -1;
    }

    boolebn isIndexDisplbyed(int idx) {
        int lbstDisplbyed = lbstItemDisplbyed();

        return idx <= lbstDisplbyed &&
            idx >= Mbth.mbx(0, lbstDisplbyed - itemsInWindow() + 1);
    }

    /**
     * returns index of lbst item displbyed in the List
     */
    int lbstItemDisplbyed() {
        int n = itemsInWindow();
        return (Mbth.min(items.size() - 1, (vsb.getVblue() + n) - 1));
    }

    /**
     * returns whether the given index is currently scrolled off the top or
     * bottom of the List.
     */
    boolebn isItemHidden(int index) {
        return index < vsb.getVblue() ||
            index >= vsb.getVblue() + itemsInWindow();
    }

    /**
     * returns the width of the list portion of the component (bccounts for
     * presence of verticbl scrollbbr)
     */
    int getListWidth() {
        return vsbVis ? width - SCROLLBAR_AREA : width;
    }

    /**
     * returns number of  items bctublly displbyed in the List
     */
    int itemsDisplbyed() {

        return (Mbth.min(items.size()-vsb.getVblue(), itemsInWindow()));

    }

    /**
     * scrollVerticbl
     * y is the number of items to scroll
     */
    void scrollVerticbl(int y) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Scrolling verticblly by " + y);
        }
        int itemsInWin = itemsInWindow();
        int h = getItemHeight();
        int pixelsToScroll = y * h;

        if (vsb.getVblue() < -y) {
            y = -vsb.getVblue();
        }
        vsb.setVblue(vsb.getVblue() + y);

        Rectbngle source = null;
        Point distbnce = null;
        int firstItem = 0, lbstItem = 0;
        int options = PAINT_HIDEFOCUS | PAINT_ITEMS | PAINT_VSCROLL | PAINT_FOCUS;
        if (y > 0) {
            if (y < itemsInWin) {
                source = new Rectbngle(MARGIN, MARGIN + pixelsToScroll, width - SCROLLBAR_AREA, h * (itemsInWin - y - 1)-1);
                distbnce = new Point(0, -pixelsToScroll);
                options |= COPY_AREA;
            }
            firstItem = vsb.getVblue() + itemsInWin - y - 1;
            lbstItem = vsb.getVblue() + itemsInWin - 1;

        } else if (y < 0) {
            if (y + itemsInWindow() > 0) {
                source = new Rectbngle(MARGIN, MARGIN, width - SCROLLBAR_AREA, h * (itemsInWin + y));
                distbnce = new Point(0, -pixelsToScroll);
                options |= COPY_AREA;
            }
            firstItem = vsb.getVblue();
            lbstItem = Mbth.min(getLbstVisibleItem(), vsb.getVblue() + -y);
        }
        repbint(firstItem, lbstItem, options, source, distbnce);
    }

    /**
     * scrollHorizontbl
     * x is the number of pixels to scroll
     */
    void scrollHorizontbl(int x) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Scrolling horizontblly by " + y);
        }
        int w = getListWidth();
        w -= ((2 * SPACE) + (2 * MARGIN));
        int h = height - (SCROLLBAR_AREA + (2 * MARGIN));
        hsb.setVblue(hsb.getVblue() + x);

        int options = PAINT_ITEMS | PAINT_HSCROLL;

        Rectbngle source = null;
        Point distbnce = null;
        if (x < 0) {
            source = new Rectbngle(MARGIN + SPACE, MARGIN, w + x, h);
            distbnce = new Point(-x, 0);
            options |= COPY_AREA;
        } else if (x > 0) {
            source = new Rectbngle(MARGIN + SPACE + x, MARGIN, w - x, h);
            distbnce = new Point(-x, 0);
            options |= COPY_AREA;
        }
        repbint(vsb.getVblue(), lbstItemDisplbyed(), options, source, distbnce);
    }

    /**
     * return the index
     */
    int y2index(int y) {
        if (!vblidY(y)) {
            return -1;
        }

        int i = (y - MARGIN) / getItemHeight() + vsb.getVblue();
        int lbst = lbstItemDisplbyed();

        if (i > lbst) {
            i = lbst;
        }

        return i;

    }

    /**
     * is the index "index" selected
     */
    boolebn isSelected(int index) {
        if (eventType == ItemEvent.SELECTED && index == eventIndex) {
            return true;
        }
        for (int i = 0 ; i < selected.length ; i++) {
            if (selected[i] == index) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * return the number of items thbt cbn fit
     * in the current window
     */
    int itemsInWindow(boolebn scrollbbrVisible) {
        int h;
        if (scrollbbrVisible) {
            h = height - ((2 * MARGIN) + SCROLLBAR_AREA);
        } else {
            h = height - 2*MARGIN;
        }
        return (h / getItemHeight());
    }

    int itemsInWindow() {
        return itemsInWindow(hsbVis);
    }

    /**
     * return true if the x bnd y position is in the horizontbl scrollbbr
     */
    boolebn inHorizontblScrollbbr(int x, int y) {
        int w = getListWidth();
        int h = height - SCROLLBAR_WIDTH;
        return (hsbVis &&  (x >= 0) && (x <= w) && (y > h));
    }

    /**
     * return true if the x bnd y position is in the verticblscrollbbr
     */
    boolebn inVerticblScrollbbr(int x, int y) {
        int w = width - SCROLLBAR_WIDTH;
        int h = hsbVis ? height - SCROLLBAR_AREA : height;
        return (vsbVis && (x > w) && (y >= 0) && (y <= h));
    }

    /**
     * return true if the x bnd y position is in the window
     */
    boolebn inWindow(int x, int y) {
        int w = getListWidth();
        int h = hsbVis ? height - SCROLLBAR_AREA : height;
        return ((x >= 0) && (x <= w)) && ((y >= 0) && (y <= h));
    }

    /**
     * return true if verticbl scrollbbr is visible bnd fblse otherwise;
     * hsbVisible is the visibility of the horizontbl scrollbbr
     */
    boolebn vsbIsVisible(boolebn hsbVisible){
        return (items.size() > itemsInWindow(hsbVisible));
    }

    /**
     * return true if horizontbl scrollbbr is visible bnd fblse otherwise;
     * vsbVisible is the visibility of the verticbl scrollbbr
     */
    boolebn hsbIsVisible(boolebn vsbVisible){
        int w = width - ((2*SPACE) + (2*MARGIN) + (vsbVisible ? SCROLLBAR_AREA : 0));
        return (mbxLength > w);
    }

    /*
     * Returns true if the event hbs been hbndled bnd should not be
     * posted to Jbvb
     */
    boolebn prePostEvent(finbl AWTEvent e) {
        if (e instbnceof MouseEvent) {
            return prePostMouseEvent((MouseEvent)e);
        }
        return super.prePostEvent(e);
    }

    /*
     * Fixed 6240151: XToolkit: Drbgging the List scrollbbr initibtes DnD
     * To be compbtible with Motif, MouseEvent originbted on the scrollbbr
     * should be sent into Jbvb in this wby:
     * - post: MOUSE_ENTERED, MOUSE_EXITED, MOUSE_MOVED
     * - don't post: MOUSE_PRESSED, MOUSE_RELEASED, MOUSE_CLICKED, MOUSE_DRAGGED
     */
    boolebn prePostMouseEvent(finbl MouseEvent me){
        if (getToplevelXWindow().isModblBlocked()) {
            return fblse;
        }

        int eventId = me.getID();

        if (eventId == MouseEvent.MOUSE_MOVED)
        {
            // only for performbnce improvement
        }else if((eventId == MouseEvent.MOUSE_DRAGGED ||
                  eventId == MouseEvent.MOUSE_RELEASED) &&
                 isScrollBbrOriginbted)
        {
            if (eventId == MouseEvent.MOUSE_RELEASED) {
                isScrollBbrOriginbted = fblse;
            }
            hbndleJbvbMouseEventOnEDT(me);
            return true;
        }else if ((eventId == MouseEvent.MOUSE_PRESSED ||
                   eventId == MouseEvent.MOUSE_CLICKED) &&
                  (inVerticblScrollbbr(me.getX(), me.getY()) ||
                   inHorizontblScrollbbr(me.getX(), me.getY())))
        {
            if (eventId == MouseEvent.MOUSE_PRESSED) {
                isScrollBbrOriginbted = true;
            }
            hbndleJbvbMouseEventOnEDT(me);
            return true;
        }
        return fblse;
    }

    /*
     * Do hbndleJbvbMouseEvent on EDT
     */
    void hbndleJbvbMouseEventOnEDT(finbl MouseEvent me){
        InvocbtionEvent ev = new InvocbtionEvent(tbrget, new Runnbble() {
            public void run() {
                hbndleJbvbMouseEvent(me);
            }
        });
        postEvent(ev);
    }

    /*
     * Fixed 5010944: List's rows overlbp one bnother
     * The bug is due to incorrent cbching of the list item size
     * So we should recblculbte font metrics on setFont
     */
    public void setFont(Font f) {
        if (!Objects.equbls(getFont(), f)) {
            super.setFont(f);
            initFontMetrics();
            lbyout();
            repbint();
        }
    }

    /**
     * Sometimes pbinter is cblled on Toolkit threbd, so the lock sequence is:
     *     bwtLock -> Pbinter -> bwtLock
     * Sometimes it is cblled on other threbds:
     *     Pbinter -> bwtLock
     * Since we cbn't gubrbntee the sequence, use bwtLock.
     */
    clbss ListPbinter {
        VolbtileImbge buffer;
        Color[] colors;

        privbte Color getListForeground() {
            if (fgColorSet) {
                return colors[FOREGROUND_COLOR];
            }
            else {
            return SystemColor.textText;
            }
        }
        privbte Color getListBbckground() {
            if (bgColorSet) {
                return colors[BACKGROUND_COLOR];
            }
            else {
                return SystemColor.text;
            }
        }

        privbte Color getDisbbledColor() {
            Color bbckgroundColor = getListBbckground();
            Color foregroundColor = getListForeground();
            return (bbckgroundColor.equbls(Color.BLACK)) ? foregroundColor.dbrker() : bbckgroundColor.dbrker();
        }

        privbte boolebn crebteBuffer() {
            VolbtileImbge locblBuffer = null;
            XToolkit.bwtLock();
            try {
                locblBuffer = buffer;
            } finblly {
                XToolkit.bwtUnlock();
            }

            if (locblBuffer == null) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("Crebting buffer " + width + "x" + height);
                }
                // use GrbphicsConfig.cCVI() instebd of Component.cVI(),
                // becbuse the lbtter mby cbuse b debdlock with the tree lock
                locblBuffer =
                    grbphicsConfig.crebteCompbtibleVolbtileImbge(width+1,
                                                                 height+1);
            }
            XToolkit.bwtLock();
            try {
                if (buffer == null) {
                    buffer = locblBuffer;
                    return true;
                }
            } finblly {
                XToolkit.bwtUnlock();
            }
            return fblse;
        }

        public void invblidbte() {
            XToolkit.bwtLock();
            try {
                if (buffer != null) {
                    buffer.flush();
                }
                buffer = null;
            } finblly {
                XToolkit.bwtUnlock();
            }
        }

        privbte void pbint(Grbphics listG, int firstItem, int lbstItem, int options) {
            pbint(listG, firstItem, lbstItem, options, null, null);
        }

        privbte void pbint(Grbphics listG, int firstItem, int lbstItem, int options,
                           Rectbngle source, Point distbnce) {
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("Repbint from " + firstItem + " to " + lbstItem + " options " + options);
            }
            if (firstItem > lbstItem) {
                int t = lbstItem;
                lbstItem = firstItem;
                firstItem = t;
            }
            if (firstItem < 0) {
                firstItem = 0;
            }
            colors = getGUIcolors();
            VolbtileImbge locblBuffer = null;
            do {
                XToolkit.bwtLock();
                try {
                    if (crebteBuffer()) {
                        // First time crebted buffer should be pbinted over bt full.
                        options = PAINT_ALL;
                    }
                    locblBuffer = buffer;
                } finblly {
                    XToolkit.bwtUnlock();
                }
                switch (locblBuffer.vblidbte(getGrbphicsConfigurbtion())) {
                  cbse VolbtileImbge.IMAGE_INCOMPATIBLE:
                      invblidbte();
                      options = PAINT_ALL;
                      continue;
                  cbse VolbtileImbge.IMAGE_RESTORED:
                      options = PAINT_ALL;
                }
                Grbphics g = locblBuffer.crebteGrbphics();

                // Note thbt the order of the following pbinting operbtions
                // should not be modified
                try {
                    g.setFont(getFont());

                    // hiding the focus rectbngle must be done prior to copying
                    // breb bnd so this is the first bction to be performed
                    if ((options & (PAINT_HIDEFOCUS)) != 0) {
                        pbintFocus(g, PAINT_HIDEFOCUS);
                    }
                    /*
                     * The shift of the component contents occurs while someone
                     * scrolls the component, the only purpose of the shift is to
                     * increbse the pbinting performbnce. The shift should be done
                     * prior to pbinting bny breb (except hiding focus) bnd bctublly
                     * it should never be done jointly with erbse bbckground.
                     */
                    if ((options & COPY_AREA) != 0) {
                        g.copyAreb(source.x, source.y, source.width, source.height,
                            distbnce.x, distbnce.y);
                    }
                    if ((options & PAINT_BACKGROUND) != 0) {
                        pbintBbckground(g);
                        // Since we mbde full erbse updbte items
                        firstItem = getFirstVisibleItem();
                        lbstItem = getLbstVisibleItem();
                    }
                    if ((options & PAINT_ITEMS) != 0) {
                        pbintItems(g, firstItem, lbstItem, options);
                    }
                    if ((options & PAINT_VSCROLL) != 0 && vsbVis) {
                        g.setClip(getVScrollBbrRec());
                        pbintVerScrollbbr(g, true);
                    }
                    if ((options & PAINT_HSCROLL) != 0 && hsbVis) {
                        g.setClip(getHScrollBbrRec());
                        pbintHorScrollbbr(g, true);
                    }
                    if ((options & (PAINT_FOCUS)) != 0) {
                        pbintFocus(g, PAINT_FOCUS);
                    }
                } finblly {
                    g.dispose();
                }
            } while (locblBuffer.contentsLost());
            listG.drbwImbge(locblBuffer, 0, 0, null);
        }

        privbte void pbintBbckground(Grbphics g) {
            g.setColor(SystemColor.window);
            g.fillRect(0, 0, width, height);
            g.setColor(getListBbckground());
            g.fillRect(0, 0, listWidth, listHeight);
            drbw3DRect(g, getSystemColors(), 0, 0, listWidth - 1, listHeight - 1, fblse);
        }

        privbte void pbintItems(Grbphics g, int firstItem, int lbstItem, int options) {
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("Pbinting items from " + firstItem + " to " + lbstItem + ", focused " + focusIndex + ", first " + getFirstVisibleItem() + ", lbst " + getLbstVisibleItem());
            }

            firstItem = Mbth.mbx(getFirstVisibleItem(), firstItem);
            if (firstItem > lbstItem) {
                int t = lbstItem;
                lbstItem = firstItem;
                firstItem = t;
            }
            firstItem = Mbth.mbx(getFirstVisibleItem(), firstItem);
            lbstItem = Mbth.min(lbstItem, items.size()-1);

            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("Actublly pbinting items from " + firstItem + " to " + lbstItem +
                          ", items in window " + itemsInWindow());
            }
            for (int i = firstItem; i <= lbstItem; i++) {
                pbintItem(g, i);
            }
        }

        privbte void pbintItem(Grbphics g, int index) {
            if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                log.finest("Pbinting item " + index);
            }
            // 4895367 - only pbint items which bre visible
            if (!isItemHidden(index)) {
                Shbpe clip = g.getClip();
                int w = getItemWidth();
                int h = getItemHeight();
                int y = getItemY(index);
                int x = getItemX();
                if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    log.finest("Setting clip " + new Rectbngle(x, y, w - (SPACE*2), h-(SPACE*2)));
                }
                g.setClip(x, y, w - (SPACE*2), h-(SPACE*2));

                // Alwbys pbint the bbckground so thbt focus is unpbinted in
                // multiselect mode
                if (isSelected(index)) {
                    if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        log.finest("Pbinted item is selected");
                    }
                    g.setColor(getListForeground());
                } else {
                    g.setColor(getListBbckground());
                }
                if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    log.finest("Filling " + new Rectbngle(x, y, w, h));
                }
                g.fillRect(x, y, w, h);

                if (index <= getLbstVisibleItem() && index < items.size()) {
                    if (!isEnbbled()){
                        g.setColor(getDisbbledColor());
                    } else if (isSelected(index)) {
                        g.setColor(getListBbckground());
                    } else {
                        g.setColor(getListForeground());
                    }
                    String str = items.elementAt(index);
                    g.drbwString(str, x - hsb.getVblue(), y + fontAscent);
                } else {
                    // Clebr the rembining breb bround the item - focus breb bnd the rest of border
                    g.setClip(x, y, listWidth, h);
                    g.setColor(getListBbckground());
                    g.fillRect(x, y, listWidth, h);
                }
                g.setClip(clip);
            }
        }

        void pbintScrollBbr(XScrollbbr scr, Grbphics g, int x, int y, int width, int height, boolebn pbintAll) {
            if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                log.finest("Pbinting scrollbbr " + scr + " width " +
                width + " height " + height + ", pbintAll " + pbintAll);
            }
            g.trbnslbte(x, y);
            scr.pbint(g, getSystemColors(), pbintAll);
            g.trbnslbte(-x, -y);
        }

        /**
         * Pbint the horizontbl scrollbbr to the screen
         *
         * @pbrbm g the grbphics context to drbw into
         * @pbrbm colors the colors used to drbw the scrollbbr
         * @pbrbm pbintAll pbint the whole scrollbbr if true, just the thumb if fblse
         */
        void pbintHorScrollbbr(Grbphics g, boolebn pbintAll) {
            int w = getListWidth();
            pbintScrollBbr(hsb, g, 0, height - (SCROLLBAR_WIDTH), w, SCROLLBAR_WIDTH, pbintAll);
        }

        /**
         * Pbint the verticbl scrollbbr to the screen
         *
         * @pbrbm g the grbphics context to drbw into
         * @pbrbm colors the colors used to drbw the scrollbbr
         * @pbrbm pbintAll pbint the whole scrollbbr if true, just the thumb if fblse
         */
        void pbintVerScrollbbr(Grbphics g, boolebn pbintAll) {
            int h = height - (hsbVis ? (SCROLLBAR_AREA-2) : 0);
            pbintScrollBbr(vsb, g, width - SCROLLBAR_WIDTH, 0, SCROLLBAR_WIDTH - 2, h, pbintAll);
        }


        privbte Rectbngle prevFocusRect;
        privbte void pbintFocus(Grbphics g, int options) {
            boolebn pbintFocus = (options & PAINT_FOCUS) != 0;
            if (pbintFocus && !hbsFocus()) {
                pbintFocus = fblse;
            }
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("Pbinting focus, focus index " + getFocusIndex() + ", focus is " +
                         (isItemHidden(getFocusIndex())?("invisible"):("visible")) + ", pbint focus is " + pbintFocus);
            }
            Shbpe clip = g.getClip();
            g.setClip(0, 0, listWidth, listHeight);
            if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                log.finest("Setting focus clip " + new Rectbngle(0, 0, listWidth, listHeight));
            }
            Rectbngle rect = getFocusRect();
            if (prevFocusRect != null) {
                // Erbse focus rect
                if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    log.finest("Erbsing previous focus rect " + prevFocusRect);
                }
                g.setColor(getListBbckground());
                g.drbwRect(prevFocusRect.x, prevFocusRect.y, prevFocusRect.width, prevFocusRect.height);
                prevFocusRect = null;
            }
            if (pbintFocus) {
                // Pbint new
                if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    log.finest("Pbinting focus rect " + rect);
                }
                g.setColor(getListForeground());  // Focus color is blwbys blbck on Linux
                g.drbwRect(rect.x, rect.y, rect.width, rect.height);
                prevFocusRect = rect;
            }
            g.setClip(clip);
        }
    }
}
