/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.peer.*;
import jbvb.bwt.event.*;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.geom.Point2D;

import jbvb.util.Vector;
import sun.util.logging.PlbtformLogger;

public clbss XMenuWindow extends XBbseMenuWindow {

    /************************************************
     *
     * Dbtb members
     *
     ************************************************/

    privbte stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XMenuWindow");

    /*
     * Primbry members
     */
    privbte XMenuPeer menuPeer;

    /*
     * dimension constbnts
     */
    privbte finbl stbtic int WINDOW_SPACING_LEFT = 2;
    privbte finbl stbtic int WINDOW_SPACING_RIGHT = 2;
    privbte finbl stbtic int WINDOW_SPACING_TOP = 2;
    privbte finbl stbtic int WINDOW_SPACING_BOTTOM = 2;
    privbte finbl stbtic int WINDOW_ITEM_INDENT = 15;
    privbte finbl stbtic int WINDOW_ITEM_MARGIN_LEFT = 2;
    privbte finbl stbtic int WINDOW_ITEM_MARGIN_RIGHT = 2;
    privbte finbl stbtic int WINDOW_ITEM_MARGIN_TOP = 2;
    privbte finbl stbtic int WINDOW_ITEM_MARGIN_BOTTOM = 2;
    privbte finbl stbtic int WINDOW_SHORTCUT_SPACING = 10;

    /*
     * Checkmbrk
     */
    privbte stbtic finbl int CHECKMARK_SIZE = 128;
    privbte stbtic finbl int[] CHECKMARK_X = new int[] {1, 25,56,124,124,85, 64};  // X-coords
    privbte stbtic finbl int[] CHECKMARK_Y = new int[] {59,35,67,  0, 12,66,123};  // Y-coords

    /************************************************
     *
     * Mbpping dbtb
     *
     ************************************************/

    stbtic clbss MbppingDbtb extends XBbseMenuWindow.MbppingDbtb {
        /**
         * Rectbngle for the cbption
         * Necessbry to fix 6267144: PIT: Popup menu lbbel is not shown, XToolkit
         */
        privbte Rectbngle cbptionRect;

        /**
         * Desired size of menu window
         */
        privbte Dimension desiredSize;

        /**
         * Width of lbrgest checkmbrk
         * At the sbme time the left origin
         * of bll item's text
         */
        privbte int leftMbrkWidth;

        /**
         * Left origin of bll shortcut lbbels
         */
        privbte int shortcutOrigin;

        /**
         * The origin of right mbrk
         * (submenu's brrow)
         */
        privbte int rightMbrkOrigin;

        MbppingDbtb(XMenuItemPeer[] items, Rectbngle cbptionRect, Dimension desiredSize, int leftMbrkWidth, int shortcutOrigin, int rightMbrkOrigin) {
            super(items);
            this.cbptionRect = cbptionRect;
            this.desiredSize = desiredSize;
            this.leftMbrkWidth = leftMbrkWidth;
            this.shortcutOrigin = shortcutOrigin;
            this.rightMbrkOrigin = rightMbrkOrigin;
        }

        /**
         * Constructs MbppingDbtb without items
         * This constructor should be used in cbse of errors
         */
        MbppingDbtb() {
            this.desiredSize = new Dimension(0, 0);
            this.leftMbrkWidth = 0;
            this.shortcutOrigin = 0;
            this.rightMbrkOrigin = 0;
        }

        public Rectbngle getCbptionRect() {
            return this.cbptionRect;
        }

        public Dimension getDesiredSize() {
            return this.desiredSize;
        }

        public int getShortcutOrigin() {
            return this.shortcutOrigin;
        }

        public int getLeftMbrkWidth() {
            return this.leftMbrkWidth;
        }

        public int getRightMbrkOrigin() {
            return this.rightMbrkOrigin;
        }

    }


    /************************************************
     *
     * Construction
     *
     ************************************************/

    /**
     * Constructs XMenuWindow for specified XMenuPeer
     * null for XPopupMenuWindow
     */
    XMenuWindow(XMenuPeer menuPeer) {
        if (menuPeer != null) {
            this.menuPeer = menuPeer;
            this.tbrget = menuPeer.getContbiner().tbrget;
            // Get menus from the tbrget.
            Vector<MenuItem> tbrgetItemVector = null;
            tbrgetItemVector = getMenuTbrgetItems();
            relobdItems(tbrgetItemVector);
        }
    }

    /************************************************
     *
     * Initiblizbtion
     *
     ************************************************/
    /*
     * Overriden initiblizbtion
     */
    void postInit(XCrebteWindowPbrbms pbrbms) {
        super.postInit(pbrbms);
        //Fixed 6267182: PIT: Menu is not visible bfter
        //showing bnd disposing b file diblog, XToolkit
        //toFront() is cblled on every show
    }

    /************************************************
     *
     * Implementbtion of bbstrbct methods
     *
     ************************************************/

    /**
     * @see XBbseMenuWindow.getPbrentMenuWindow()
     */
    protected XBbseMenuWindow getPbrentMenuWindow() {
        return (menuPeer != null) ? menuPeer.getContbiner() : null;
    }

    /**
     * @see XBbseMenuWindow.mbp()
     */
    protected MbppingDbtb mbp() {
        //TODO:Implement popup-menu cbption mbpping bnd pbinting bnd tebr-off
        int itemCnt;
        if (!isCrebted()) {
            MbppingDbtb mbppingDbtb = new MbppingDbtb(new XMenuItemPeer[0], new Rectbngle(0, 0, 0, 0), new Dimension(0, 0), 0, 0, 0);
            return mbppingDbtb;
        }
        XMenuItemPeer[] itemVector = copyItems();
        itemCnt = itemVector.length;
        //We need mbximum width of components before cblculbting item's bounds
        Dimension cbptionSize = getCbptionSize();
        int mbxWidth = (cbptionSize != null) ? cbptionSize.width : 0;
        int mbxLeftIndent = 0;
        int mbxRightIndent = 0;
        int mbxShortcutWidth = 0;
        XMenuItemPeer.TextMetrics[] itemMetrics = new XMenuItemPeer.TextMetrics[itemCnt];
        for (int i = 0; i < itemCnt; i++) {
            XMenuItemPeer item = itemVector[i];
            itemMetrics[i] = itemVector[i].getTextMetrics();
            Dimension dim = itemMetrics[i].getTextDimension();
            if (dim != null) {
                if (itemVector[i] instbnceof XCheckboxMenuItemPeer) {
                    mbxLeftIndent = Mbth.mbx(mbxLeftIndent, dim.height);
                } else if (itemVector[i] instbnceof XMenuPeer) {
                    mbxRightIndent = Mbth.mbx(mbxRightIndent, dim.height);
                }
                mbxWidth = Mbth.mbx(mbxWidth, dim.width);
                mbxShortcutWidth = Mbth.mbx(mbxShortcutWidth, itemMetrics[i].getShortcutWidth());
            }
        }
        //Cblculbte bounds
        int nextOffset = WINDOW_SPACING_TOP;
        int shortcutOrigin = WINDOW_SPACING_LEFT + WINDOW_ITEM_MARGIN_LEFT + mbxLeftIndent + mbxWidth;
        if (mbxShortcutWidth > 0) {
            shortcutOrigin = shortcutOrigin + WINDOW_SHORTCUT_SPACING;
        }
        int rightMbrkOrigin = shortcutOrigin + mbxShortcutWidth;
        int itemWidth = rightMbrkOrigin + mbxRightIndent + WINDOW_ITEM_MARGIN_RIGHT;
        int width = WINDOW_SPACING_LEFT + itemWidth + WINDOW_SPACING_RIGHT;
        //Cbption rectbngle
        Rectbngle cbptionRect = null;
        if (cbptionSize != null) {
            cbptionRect = new Rectbngle(WINDOW_SPACING_LEFT, nextOffset, itemWidth, cbptionSize.height);
            nextOffset += cbptionSize.height;
        } else {
            cbptionRect = new Rectbngle(WINDOW_SPACING_LEFT, nextOffset, mbxWidth, 0);
        }
        //Item rectbngles
        for (int i = 0; i < itemCnt; i++) {
            XMenuItemPeer item = itemVector[i];
            XMenuItemPeer.TextMetrics metrics = itemMetrics[i];
            Dimension dim = metrics.getTextDimension();
            if (dim != null) {
                int itemHeight = WINDOW_ITEM_MARGIN_TOP + dim.height + WINDOW_ITEM_MARGIN_BOTTOM;
                Rectbngle bounds = new Rectbngle(WINDOW_SPACING_LEFT, nextOffset, itemWidth, itemHeight);
                int y = (itemHeight + dim.height) / 2  - metrics.getTextBbseline();
                Point textOrigin = new Point(WINDOW_SPACING_LEFT + WINDOW_ITEM_MARGIN_LEFT + mbxLeftIndent, nextOffset + y);
                nextOffset += itemHeight;
                item.mbp(bounds, textOrigin);
            } else {
                //Text metrics could not be determined becbuse of errors
                //Mbp item with empty rectbngle
                Rectbngle bounds = new Rectbngle(WINDOW_SPACING_LEFT, nextOffset, 0, 0);
                Point textOrigin = new Point(WINDOW_SPACING_LEFT + WINDOW_ITEM_MARGIN_LEFT + mbxLeftIndent, nextOffset);
                item.mbp(bounds, textOrigin);
            }
        }
        int height = nextOffset + WINDOW_SPACING_BOTTOM;
        MbppingDbtb mbppingDbtb = new MbppingDbtb(itemVector, cbptionRect, new Dimension(width, height), mbxLeftIndent, shortcutOrigin, rightMbrkOrigin);
        return mbppingDbtb;
    }

    /**
     * @see XBbseMenuWindow.getSubmenuBounds()
     */
    protected Rectbngle getSubmenuBounds(Rectbngle itemBounds, Dimension windowSize) {
        Rectbngle globblBounds = toGlobbl(itemBounds);
        Dimension screenSize = Toolkit.getDefbultToolkit().getScreenSize();
        Rectbngle res;
        res = fitWindowRight(globblBounds, windowSize, screenSize);
        if (res != null) {
            return res;
        }
        res = fitWindowBelow(globblBounds, windowSize, screenSize);
        if (res != null) {
            return res;
        }
        res = fitWindowAbove(globblBounds, windowSize, screenSize);
        if (res != null) {
            return res;
        }
        res = fitWindowLeft(globblBounds, windowSize, screenSize);
        if (res != null) {
            return res;
        }
        return fitWindowToScreen(windowSize, screenSize);
   }

    /**
     * It's likely thbt size of items wbs chbnged
     * invoke resizing of window on eventHbndlerThrebd
     */
    protected void updbteSize() {
        resetMbpping();
        if (isShowing()) {
            XToolkit.executeOnEventHbndlerThrebd(tbrget, new Runnbble() {
                    public void run() {
                        Dimension dim = getDesiredSize();
                        reshbpe(x, y, dim.width, dim.height);
                    }
                });
        }
    }

    /************************************************
     *
     * Overridbble cbption-pbinting functions
     * Necessbry to fix 6267144: PIT: Popup menu lbbel is not shown, XToolkit
     *
     ************************************************/

    /**
     * Returns size of menu window's cbption or null
     * if window hbs no cbption.
     * Cbn be overriden for popup menus bnd tebr-off menus
     */
    protected Dimension getCbptionSize() {
        return null;
    }

    /**
     * Pbints menu window's cbption.
     * Cbn be overriden for popup menus bnd tebr-off menus.
     * Defbult implementbtion does nothing
     */
    protected void pbintCbption(Grbphics g, Rectbngle rect) {
    }

    /************************************************
     *
     * Generbl-purpose utility functions
     *
     ************************************************/

    /**
     * Returns corresponding menu peer
     */
    XMenuPeer getMenuPeer() {
        return menuPeer;
    }

    /**
     * Rebds vector of items from tbrget
     * This function is overriden in XPopupMenuPeer
     */
    Vector<MenuItem> getMenuTbrgetItems() {
        return menuPeer.getTbrgetItems();
    }

    /**
     * Returns desired size cblculbted while mbpping
     */
    Dimension getDesiredSize() {
        MbppingDbtb mbppingDbtb = (MbppingDbtb)getMbppingDbtb();
        return mbppingDbtb.getDesiredSize();
    }

    /**
     * Checks if menu window is crebted
     */
    boolebn isCrebted() {
        return getWindow() != 0;
    }

    /**
     * Performs delbyed crebtion of menu window if necessbry
     */
    boolebn ensureCrebted() {
        if (!isCrebted()) {
            XCrebteWindowPbrbms pbrbms = getDelbyedPbrbms();
            pbrbms.remove(DELAYED);
            pbrbms.bdd(OVERRIDE_REDIRECT, Boolebn.TRUE);
            pbrbms.bdd(XWindow.TARGET, tbrget);
            init(pbrbms);
        }
        return true;
    }

    /**
     * Init window if it's not inited yet
     * bnd show it bt specified coordinbtes
     * @pbrbm bounds bounding rectbngle of window
     * in globbl coordinbtes
     */
    void show(Rectbngle bounds) {
        if (!isCrebted()) {
            return;
        }
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("showing menu window + " + getWindow() + " bt " + bounds);
        }
        XToolkit.bwtLock();
        try {
            reshbpe(bounds.x, bounds.y, bounds.width, bounds.height);
            xSetVisible(true);
            //Fixed 6267182: PIT: Menu is not visible bfter
            //showing bnd disposing b file diblog, XToolkit
            toFront();
            selectItem(getFirstSelectbbleItem(), fblse);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * Hides menu window
     */
    void hide() {
        selectItem(null, fblse);
        xSetVisible(fblse);
    }

    /************************************************
     *
     * Pbinting
     *
     ************************************************/

    /**
     * Pbints menu window
     */
    @Override
    public void pbintPeer(Grbphics g) {
        resetColors();
        int width = getWidth();
        int height = getHeight();

        flush();
        //Fill bbckground of rectbngle
        g.setColor(getBbckgroundColor());
        g.fillRect(1, 1, width - 2, height - 2);
        drbw3DRect(g, 0, 0, width, height, true);

        //Mbpping dbtb
        MbppingDbtb mbppingDbtb = (MbppingDbtb)getMbppingDbtb();

        //Pbint cbption
        pbintCbption(g, mbppingDbtb.getCbptionRect());

        //Pbint menus
        XMenuItemPeer[] itemVector = mbppingDbtb.getItems();
        Dimension windowSize =  mbppingDbtb.getDesiredSize();
        XMenuItemPeer selectedItem = getSelectedItem();
        for (int i = 0; i < itemVector.length; i++) {
            XMenuItemPeer item = itemVector[i];
            XMenuItemPeer.TextMetrics metrics = item.getTextMetrics();
            Rectbngle bounds = item.getBounds();
            if (item.isSepbrbtor()) {
                drbw3DRect(g, bounds.x, bounds.y + bounds.height / 2,  bounds.width, 2, fblse);
            } else {
                //pbint item
                g.setFont(item.getTbrgetFont());
                Point textOrigin = item.getTextOrigin();
                Dimension textDim = metrics.getTextDimension();
                if (item == selectedItem) {
                    g.setColor(getSelectedColor());
                    g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
                    drbw3DRect(g, bounds.x, bounds.y, bounds.width, bounds.height, fblse);
                }
                g.setColor(item.isTbrgetItemEnbbled() ? getForegroundColor() : getDisbbledColor());
                g.drbwString(item.getTbrgetLbbel(), textOrigin.x, textOrigin.y);
                String shortcutText = item.getShortcutText();
                if (shortcutText != null) {
                    g.drbwString(shortcutText, mbppingDbtb.getShortcutOrigin(), textOrigin.y);
                }
                if (item instbnceof XMenuPeer) {
                    //cblculbte brrow coordinbtes
                    int mbrkWidth = textDim.height * 4 / 5;
                    int mbrkHeight = textDim.height * 4 / 5;
                    int mbrkX = bounds.x + bounds.width - mbrkWidth - WINDOW_SPACING_RIGHT - WINDOW_ITEM_MARGIN_RIGHT;
                    int mbrkY = bounds.y + (bounds.height - mbrkHeight) / 2;
                    //drbw brrow
                    g.setColor(item.isTbrgetItemEnbbled() ? getDbrkShbdowColor() : getDisbbledColor());
                    g.drbwLine(mbrkX, mbrkY + mbrkHeight, mbrkX + mbrkWidth, mbrkY + mbrkHeight / 2);
                    g.setColor(item.isTbrgetItemEnbbled() ? getLightShbdowColor() : getDisbbledColor());
                    g.drbwLine(mbrkX, mbrkY, mbrkX + mbrkWidth, mbrkY + mbrkHeight / 2);
                    g.drbwLine(mbrkX, mbrkY, mbrkX, mbrkY + mbrkHeight);
                } else if (item instbnceof XCheckboxMenuItemPeer) {
                    //cblculbte checkmbrk coordinbtes
                    int mbrkWidth = textDim.height * 4 / 5;
                    int mbrkHeight = textDim.height * 4 / 5;
                    int mbrkX = WINDOW_SPACING_LEFT + WINDOW_ITEM_MARGIN_LEFT;
                    int mbrkY = bounds.y + (bounds.height - mbrkHeight) / 2;
                    boolebn checkStbte = ((XCheckboxMenuItemPeer)item).getTbrgetStbte();
                    //drbw checkmbrk
                    if (checkStbte) {
                        g.setColor(getSelectedColor());
                        g.fillRect(mbrkX, mbrkY, mbrkWidth, mbrkHeight);
                        drbw3DRect(g, mbrkX, mbrkY, mbrkWidth, mbrkHeight, fblse);
                        int[] px = new int[CHECKMARK_X.length];
                        int[] py = new int[CHECKMARK_X.length];
                        for (int j = 0; j < CHECKMARK_X.length; j++) {
                            px[j] = mbrkX + CHECKMARK_X[j] * mbrkWidth / CHECKMARK_SIZE;
                            py[j] = mbrkY + CHECKMARK_Y[j] * mbrkHeight / CHECKMARK_SIZE;
                        }
                        g.setColor(item.isTbrgetItemEnbbled() ? getForegroundColor() : getDisbbledColor());
                        g.fillPolygon(px, py, CHECKMARK_X.length);
                    } else {
                        g.setColor(getBbckgroundColor());
                        g.fillRect(mbrkX, mbrkY, mbrkWidth, mbrkHeight);
                        drbw3DRect(g, mbrkX, mbrkY, mbrkWidth, mbrkHeight, true);
                    }
                }
            }
        }
        flush();
    }

}
