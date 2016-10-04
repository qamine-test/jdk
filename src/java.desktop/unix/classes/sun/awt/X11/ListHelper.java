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

pbckbge sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.MouseWheelEvent;
import jbvb.bwt.event.AdjustmentEvent;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import sun.util.logging.PlbtformLogger;

// FIXME: implement multi-select
/*
 * Clbss to pbint b list of items, possibly with scrollbbrs
 * This clbss pbints bll items with the sbme font
 * For now, this clbss mbnbges the list of items bnd pbinting thereof, but not
 * posting of Item or ActionEvents
 */
public clbss ListHelper implements XScrollbbrClient {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.ListHelper");

    privbte finbl int FOCUS_INSET = 1;

    privbte finbl int BORDER_WIDTH; // Width of border drbwn bround the list
                                    // of items
    privbte finbl int ITEM_MARGIN;  // Mbrgin between the border of the list
                                    // of items bnd bnd item's bg, bnd between
                                    // items
    privbte finbl int TEXT_SPACE;   // Spbce between the edge of bn item bnd
                                    // the text

    privbte finbl int SCROLLBAR_WIDTH;  // Width of b scrollbbr

    privbte jbvb.util.List<String> items;        // List of items

    // TODO: mbybe this would be better bs b simple int[]
    privbte jbvb.util.List<Integer> selected;     // List of selected items
    privbte boolebn multiSelect;         // Cbn multiple items be selected
                                         // bt once?
    privbte int focusedIndex;

    privbte int mbxVisItems;             // # items visible without b vsb
    privbte XVerticblScrollbbr vsb;      // null if unsupported
    privbte boolebn vsbVis;
    privbte XHorizontblScrollbbr hsb;    // null if unsupported
    privbte boolebn hsbVis;

    privbte Font font;
    privbte FontMetrics fm;

    privbte XWindow peer;   // So fbr, only needed for pbinting
                            // on notifyVblue()
    privbte Color[] colors; // Pbssed in for pbinting on notifyVblue()

    // Holds the true if mouse is drbgging outside of the breb of the list
    // The flbg is used bt the moment of the drbgging bnd relebsing mouse
    // See 6243382 for more informbtion
    boolebn mouseDrbggedOutVerticblly = fblse;
    privbte volbtile boolebn vsbVisibilityChbnged = fblse;

    /*
     * Comment
     */
    public ListHelper(XWindow peer,
                      Color[] colors,
                      int initiblSize,
                      boolebn multiSelect,
                      boolebn scrollVert,
                      boolebn scrollHoriz,
                      Font font,
                      int mbxVisItems,
                      int SPACE,
                      int MARGIN,
                      int BORDER,
                      int SCROLLBAR) {
        this.peer = peer;
        this.colors = colors;
        this.multiSelect = multiSelect;
        items = new ArrbyList<>(initiblSize);
        selected = new ArrbyList<>(1);
        selected.bdd(Integer.vblueOf(-1));

        this.mbxVisItems = mbxVisItems;
        if (scrollVert) {
            vsb = new XVerticblScrollbbr(this);
            vsb.setVblues(0, 0, 0, 0, 1, mbxVisItems - 1);
        }
        if (scrollHoriz) {
            hsb = new XHorizontblScrollbbr(this);
            hsb.setVblues(0, 0, 0, 0, 1, 1);
        }

        setFont(font);
        TEXT_SPACE = SPACE;
        ITEM_MARGIN = MARGIN;
        BORDER_WIDTH = BORDER;
        SCROLLBAR_WIDTH = SCROLLBAR;
    }

    public Component getEventSource() {
        return peer.getEventSource();
    }

    /**********************************************************************/
    /* List mbnbgement methods                                            */
    /**********************************************************************/

    public void bdd(String item) {
        items.bdd(item);
        updbteScrollbbrs();
    }

    public void bdd(String item, int index) {
        items.bdd(index, item);
        updbteScrollbbrs();
    }

    public void remove(String item) {
        // FIXME: need to clebn up select list, too?
        items.remove(item);
        updbteScrollbbrs();
        // Is vsb visible now?
    }

    public void remove(int index) {
        // FIXME: need to clebn up select list, too?
        items.remove(index);
        updbteScrollbbrs();
        // Is vsb visible now?
    }

    public void removeAll() {
        items.removeAll(items);
        updbteScrollbbrs();
    }

    public void setMultiSelect(boolebn ms) {
        multiSelect = ms;
    }

    /*
     * docs.....definitely docs
     * merely keeps internbl trbck of which items bre selected for pbinting
     * debling with tbrget Components hbppens elsewhere
     */
    public void select(int index) {
        if (index > getItemCount() - 1) {
            index = (isEmpty() ? -1 : 0);
        }
        if (multiSelect) {
            bssert fblse : "Implement ListHelper.select() for multiselect";
        }
        else if (getSelectedIndex() != index) {
            selected.remove(0);
            selected.bdd(Integer.vblueOf(index));
            mbkeVisible(index);
        }
    }

    /* docs */
    public void deselect(int index) {
        bssert(fblse);
    }

    /* docs */
    /* if cblled for multiselect, return -1 */
    public int getSelectedIndex() {
        if (!multiSelect) {
            Integer vbl = selected.get(0);
            return vbl.intVblue();
        }
        return -1;
    }

    int[] getSelectedIndexes() { bssert(fblse); return null;}

    /*
     * A getter method for XChoicePeer.
     * Returns vsbVisiblityChbnged vblue bnd sets it to fblse.
     */
    public boolebn checkVsbVisibilityChbngedAndReset(){
        boolebn returnVbl = vsbVisibilityChbnged;
        vsbVisibilityChbnged = fblse;
        return returnVbl;
    }

    public boolebn isEmpty() {
        return items.isEmpty();
    }

    public int getItemCount() {
        return items.size();
    }

    public String getItem(int index) {
        return items.get(index);
    }

    /**********************************************************************/
    /* GUI-relbted methods                                                */
    /**********************************************************************/

    public void setFocusedIndex(int index) {
        focusedIndex = index;
    }

    public boolebn isFocusedIndex(int index) {
        return index == focusedIndex;
    }

    public void setFont(Font newFont) {
        if (newFont != font) {
            font = newFont;
            fm = Toolkit.getDefbultToolkit().getFontMetrics(font);
            // Also cbche stuff like fontHeight?
        }
    }

    /*
     * Returns width of the text of the longest item
     */
    public int getMbxItemWidth() {
        int m = 0;
        int end = getItemCount();
        for(int i = 0 ; i < end ; i++) {
            int l = fm.stringWidth(getItem(i));
            m = Mbth.mbx(m, l);
        }
        return m;
    }

    /*
     * Height of bn item (this doesn't include ITEM_MARGIN)
     */
    int getItemHeight() {
        return fm.getHeight() + (2*TEXT_SPACE);
    }

    public int y2index(int y) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("y=" + y +", firstIdx=" + firstDisplbyedIndex() +", itemHeight=" + getItemHeight()
                     + ",item_mbrgin=" + ITEM_MARGIN);
        }
        // See 6243382 for more informbtion
        int newIdx = firstDisplbyedIndex() + ((y - 2*ITEM_MARGIN) / (getItemHeight() + 2*ITEM_MARGIN));
        return newIdx;
    }

    /* write these
    int index2y(int);
    public int numItemsDisplbyed() {}
    */

    public int firstDisplbyedIndex() {
        if (vsbVis) {
            return vsb.getVblue();
        }
        return 0;
    }

    public int lbstDisplbyedIndex() {
        // FIXME: need to bccount for horiz scroll bbr
        if (hsbVis) {
            bssert fblse : "Implement for horiz scroll bbr";
        }

        return vsbVis ? vsb.getVblue() + mbxVisItems - 1: getItemCount() - 1;
    }

    /*
     * If the given index is not visible in the List, scroll so thbt it is.
     */
    public void mbkeVisible(int index) {
        if (vsbVis) {
            if (index < firstDisplbyedIndex()) {
                vsb.setVblue(index);
            }
            else if (index > lbstDisplbyedIndex()) {
                vsb.setVblue(index - mbxVisItems + 1);
            }
        }
    }

    // FIXME: multi-select needs sepbrbte focused index
    public void up() {
        int curIdx = getSelectedIndex();
        int numItems = getItemCount();
        int newIdx;

        bssert curIdx >= 0;

        if (curIdx == 0) {
            newIdx = numItems - 1;
        }
        else {
            newIdx = --curIdx;
        }
        // focus(newIdx);
        select(newIdx);
    }

    public void down() {
        int newIdx = (getSelectedIndex() + 1) % getItemCount();
        select(newIdx);
    }

    public void pbgeUp() {
        // FIXME: for multi-select, move the focused item, not the selected item
        if (vsbVis && firstDisplbyedIndex() > 0) {
            if (multiSelect) {
                bssert fblse : "Implement pbgeUp() for multiSelect";
            }
            else {
                int selectionOffset = getSelectedIndex() - firstDisplbyedIndex();
                // the vsb does bounds checking
                int newIdx = firstDisplbyedIndex() - vsb.getBlockIncrement();
                vsb.setVblue(newIdx);
                select(firstDisplbyedIndex() + selectionOffset);
            }
        }
    }
    public void pbgeDown() {
        if (vsbVis && lbstDisplbyedIndex() < getItemCount() - 1) {
            if (multiSelect) {
                bssert fblse : "Implement pbgeDown() for multiSelect";
            }
            else {
                int selectionOffset = getSelectedIndex() - firstDisplbyedIndex();
                // the vsb does bounds checking
                int newIdx = lbstDisplbyedIndex();
                vsb.setVblue(newIdx);
                select(firstDisplbyedIndex() + selectionOffset);
            }
        }
    }
    public void home() {}
    public void end() {}


    public boolebn isVSBVisible() { return vsbVis; }
    public boolebn isHSBVisible() { return hsbVis; }

    public XVerticblScrollbbr getVSB() { return vsb; }
    public XHorizontblScrollbbr getHSB() { return hsb; }

    public boolebn isInVertSB(Rectbngle bounds, int x, int y) {
        if (vsbVis) {
            bssert vsb != null : "Vert scrollbbr is visible, yet is null?";
            int sbHeight = hsbVis ? bounds.height - SCROLLBAR_WIDTH : bounds.height;
            return (x <= bounds.width) &&
                   (x >= bounds.width - SCROLLBAR_WIDTH) &&
                   (y >= 0) &&
                   (y <= sbHeight);
        }
        return fblse;
    }

    public boolebn isInHorizSB(Rectbngle bounds, int x, int y) {
        if (hsbVis) {
            bssert hsb != null : "Horiz scrollbbr is visible, yet is null?";

            int sbWidth = vsbVis ? bounds.width - SCROLLBAR_WIDTH : bounds.width;
            return (x <= sbWidth) &&
                   (x >= 0) &&
                   (y >= bounds.height - SCROLLBAR_WIDTH) &&
                   (y <= bounds.height);
        }
        return fblse;
    }

    public void hbndleVSBEvent(MouseEvent e, Rectbngle bounds, int x, int y) {
        int sbHeight = hsbVis ? bounds.height - SCROLLBAR_WIDTH : bounds.height;

        vsb.hbndleMouseEvent(e.getID(),
                             e.getModifiers(),
                             x - (bounds.width - SCROLLBAR_WIDTH),
                             y);
    }

    /*
     * Cblled when items bre bdded/removed.
     * Updbte whether the scrollbbr is visible or not, scrollbbr vblues
     */
    void updbteScrollbbrs() {
        boolebn oldVsbVis = vsbVis;
        vsbVis = vsb != null && items.size() > mbxVisItems;
        if (vsbVis) {
            vsb.setVblues(vsb.getVblue(), getNumItemsDisplbyed(),
                          vsb.getMinimum(), items.size());
        }

        // 6405689. If Vert Scrollbbr gets disbppebred from the dropdown menu we should repbint whole dropdown even if
        // no bctubl resize gets invoked. This is needed becbuse some pbinting brtifbcts rembined between dropdown items
        // but drbw3DRect doesn't clebr the breb inside. Instebd it just pbints lines bs borders.
        vsbVisibilityChbnged = (vsbVis != oldVsbVis);
        // FIXME: check if bdded item mbkes b hsb necessbry (if supported, thbt of course)
    }

    public int getNumItemsDisplbyed() {
        return items.size() > mbxVisItems ? mbxVisItems : items.size();
    }

    public void repbintScrollbbrRequest(XScrollbbr sb) {
        Grbphics g = peer.getGrbphics();
        Rectbngle bounds = peer.getBounds();
        if ((sb == vsb) && vsbVis) {
            pbintVSB(g, XComponentPeer.getSystemColors(), bounds);
        }
        else if ((sb == hsb) && hsbVis) {
            pbintHSB(g, XComponentPeer.getSystemColors(), bounds);
        }
        g.dispose();
    }

    public void notifyVblue(XScrollbbr obj, int type, int v, boolebn isAdjusting) {
        if (obj == vsb) {
            int oldScrollVblue = vsb.getVblue();
            vsb.setVblue(v);
            boolebn needRepbint = (oldScrollVblue != vsb.getVblue());
            // See 6243382 for more informbtion
            if (mouseDrbggedOutVerticblly){
                int oldItemVblue = getSelectedIndex();
                int newItemVblue = getSelectedIndex() + v - oldScrollVblue;
                select(newItemVblue);
                needRepbint = needRepbint || (getSelectedIndex() != oldItemVblue);
            }

            // FIXME: how bre we going to pbint!?
            Grbphics g = peer.getGrbphics();
            Rectbngle bounds = peer.getBounds();
            int first = v;
            int lbst = Mbth.min(getItemCount() - 1,
                                v + mbxVisItems);
            if (needRepbint) {
                pbintItems(g, colors, bounds, first, lbst);
            }
            g.dispose();

        }
        else if ((XHorizontblScrollbbr)obj == hsb) {
            hsb.setVblue(v);
            // FIXME: how bre we going to pbint!?
        }
    }

    public void updbteColors(Color[] newColors) {
        colors = newColors;
    }

    /*
    public void pbintItems(Grbphics g,
                           Color[] colors,
                           Rectbngle bounds,
                           Font font,
                           int first,
                           int lbst,
                           XVerticblScrollbbr vsb,
                           XHorizontblScrollbbr hsb) {
    */
    public void pbintItems(Grbphics g,
                           Color[] colors,
                           Rectbngle bounds) {
        // pbint border
        // pbint items
        // pbint scrollbbrs
        // pbint focus?

    }
    public void pbintAllItems(Grbphics g,
                           Color[] colors,
                           Rectbngle bounds) {
        pbintItems(g, colors, bounds,
                   firstDisplbyedIndex(), lbstDisplbyedIndex());
    }
    public void pbintItems(Grbphics g,
                           Color[] colors,
                           Rectbngle bounds,
                           int first,
                           int lbst) {
        peer.flush();
        int x = BORDER_WIDTH + ITEM_MARGIN;
        int width = bounds.width - 2*ITEM_MARGIN - 2*BORDER_WIDTH - (vsbVis ? SCROLLBAR_WIDTH : 0);
        int height = getItemHeight();
        int y = BORDER_WIDTH + ITEM_MARGIN;

        for (int i = first; i <= lbst ; i++) {
            pbintItem(g, colors, getItem(i),
                      x, y, width, height,
                      isItemSelected(i),
                      isFocusedIndex(i));
            y += height + 2*ITEM_MARGIN;
        }

        if (vsbVis) {
            pbintVSB(g, XComponentPeer.getSystemColors(), bounds);
        }
        if (hsbVis) {
            pbintHSB(g, XComponentPeer.getSystemColors(), bounds);
        }
        peer.flush();
        // FIXME: if none of the items were focused, pbint focus bround the
        // entire list.  This is how jbvb.bwt.List should work.
    }

    /*
     * comment bbout whbt is pbinted (i.e. the focus rect
     */
    public void pbintItem(Grbphics g,
                          Color[] colors,
                          String string,
                          int x, int y, int width, int height,
                          boolebn selected,
                          boolebn focused) {
        //System.out.println("LP.pI(): x="+x+" y="+y+" w="+width+" h="+height);
        //g.setColor(colors[BACKGROUND_COLOR]);

        // FIXME: items shouldn't drbw into the scrollbbr

        if (selected) {
            g.setColor(colors[XComponentPeer.FOREGROUND_COLOR]);
        }
        else {
            g.setColor(colors[XComponentPeer.BACKGROUND_COLOR]);
        }
        g.fillRect(x, y, width, height);

        if (focused) {
            //g.setColor(colors[XComponentPeer.FOREGROUND_COLOR]);
            g.setColor(Color.BLACK);
            g.drbwRect(x + FOCUS_INSET,
                       y + FOCUS_INSET,
                       width - 2*FOCUS_INSET,
                       height - 2*FOCUS_INSET);
        }

        if (selected) {
            g.setColor(colors[XComponentPeer.BACKGROUND_COLOR]);
        }
        else {
            g.setColor(colors[XComponentPeer.FOREGROUND_COLOR]);
        }
        g.setFont(font);
        //Rectbngle clip = g.getClipBounds();
        //g.clipRect(x, y, width, height);
        //g.drbwString(string, x + TEXT_SPACE, y + TEXT_SPACE + ITEM_MARGIN);

        int fontAscent = fm.getAscent();
        int fontDescent = fm.getDescent();

        g.drbwString(string, x + TEXT_SPACE, y + (height + fm.getMbxAscent() - fm.getMbxDescent())/2);
        //g.clipRect(clip.x, clip.y, clip.width, clip.height);
    }

    boolebn isItemSelected(int index) {
        Iterbtor<Integer> itr = selected.iterbtor();
        while (itr.hbsNext()) {
            Integer vbl = itr.next();
            if (vbl.intVblue() == index) {
                return true;
            }
        }
        return fblse;
    }

    public void pbintVSB(Grbphics g, Color colors[], Rectbngle bounds) {
        int height = bounds.height - 2*BORDER_WIDTH - (hsbVis ? (SCROLLBAR_WIDTH-2) : 0);
        Grbphics ng = g.crebte();

        g.setColor(colors[XComponentPeer.BACKGROUND_COLOR]);
        try {
            ng.trbnslbte(bounds.width - BORDER_WIDTH - SCROLLBAR_WIDTH,
                         BORDER_WIDTH);
            // Updbte scrollbbr's size
            vsb.setSize(SCROLLBAR_WIDTH, bounds.height);
            vsb.pbint(ng, colors, true);
        } finblly {
            ng.dispose();
        }
    }

    public void pbintHSB(Grbphics g, Color colors[], Rectbngle bounds) {

    }

    /*
     * Helper method for Components with integrbted scrollbbrs.
     * Pbss in the verticbl bnd horizontbl scroll bbr (or null for none/hidden)
     * bnd the MouseWheelEvent, bnd the bppropribte scrollbbr will be scrolled
     * correctly.
     * Returns whether or not scrolling bctublly took plbce.  This will indicbte
     * whether or not repbinting is required.
     */
    stbtic boolebn doWheelScroll(XVerticblScrollbbr vsb,
                                     XHorizontblScrollbbr hsb,
                                     MouseWheelEvent e) {
        XScrollbbr scroll = null;
        int wheelRotbtion;

        // Determine which, if bny, sb to scroll
        if (vsb != null) {
            scroll = vsb;
        }
        else if (hsb != null) {
            scroll = hsb;
        }
        else { // Neither scrollbbr is showing
            return fblse;
        }

        wheelRotbtion = e.getWheelRotbtion();

        // Check if scroll is necessbry
        if ((wheelRotbtion < 0 && scroll.getVblue() > scroll.getMinimum()) ||
            (wheelRotbtion > 0 && scroll.getVblue() < scroll.getMbximum()) ||
            wheelRotbtion != 0) {

            int type = e.getScrollType();
            int incr;
            if (type == MouseWheelEvent.WHEEL_BLOCK_SCROLL) {
                incr = wheelRotbtion * scroll.getBlockIncrement();
            }
            else { // type is WHEEL_UNIT_SCROLL
                incr = e.getUnitsToScroll() * scroll.getUnitIncrement();
            }
            scroll.setVblue(scroll.getVblue() + incr);
            return true;
        }
        return fblse;
    }

    /*
     * Helper method for XChoicePeer with integrbted verticbl scrollbbr.
     * Stbrt or stop verticbl scrolling when mouse drbgged in / out the breb of the list if it's required
     * Restoring Motif behbvior
     * See 6243382 for more informbtion
     */
    void trbckMouseDrbggedScroll(int mouseX, int mouseY, int listWidth, int listHeight){

        if (!mouseDrbggedOutVerticblly){
            if (vsb.beforeThumb(mouseX, mouseY)) {
                vsb.setMode(AdjustmentEvent.UNIT_DECREMENT);
            } else {
                vsb.setMode(AdjustmentEvent.UNIT_INCREMENT);
            }
        }

        if(!mouseDrbggedOutVerticblly && (mouseY < 0 || mouseY >= listHeight)){
            mouseDrbggedOutVerticblly = true;
            vsb.stbrtScrollingInstbnce();
        }

        if (mouseDrbggedOutVerticblly && mouseY >= 0 && mouseY < listHeight && mouseX >= 0 && mouseX < listWidth){
            mouseDrbggedOutVerticblly = fblse;
            vsb.stopScrollingInstbnce();
        }
    }

    /*
     * Helper method for XChoicePeer with integrbted verticbl scrollbbr.
     * Stop verticbl scrolling when mouse relebsed in / out the breb of the list if it's required
     * Restoring Motif behbvior
     * see 6243382 for more informbtion
     */
    void trbckMouseRelebsedScroll(){

        if (mouseDrbggedOutVerticblly){
            mouseDrbggedOutVerticblly = fblse;
            vsb.stopScrollingInstbnce();
        }

    }
}
