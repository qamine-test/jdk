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

import jbvb.util.Vector;
import sun.bwt.AWTAccessor;
import sun.util.logging.PlbtformLogger;

public clbss XPopupMenuPeer extends XMenuWindow implements PopupMenuPeer {

    /************************************************
     *
     * Dbtb members
     *
     ************************************************/
    privbte stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XBbseMenuWindow");

    /*
     * Primbry members
     */
    privbte XComponentPeer componentPeer;
    privbte PopupMenu popupMenuTbrget;

    /*
     * If mouse button is clicked on item showing submenu
     * we hbve to hide its submenu.
     * This member sbves the submenu under cursor
     * Only if it's showing
     */
    privbte XMenuPeer showingMousePressedSubmenu = null;

    /*
     * Pbinting constbnts
     */
    privbte finbl stbtic int CAPTION_MARGIN_TOP = 4;
    privbte finbl stbtic int CAPTION_SEPARATOR_HEIGHT = 6;

    /************************************************
     *
     * Construction
     *
     ************************************************/
    XPopupMenuPeer(PopupMenu tbrget) {
        super(null);
        this.popupMenuTbrget = tbrget;
    }

    /************************************************
     *
     * Implementbtion of interfbce methods
     *
     ************************************************/
    /*
     * From MenuComponentPeer
     */
    public void setFont(Font f) {
        resetMbpping();
        setItemsFont(f);
        postPbintEvent();
    }

    /*
     * From MenuItemPeer
     */
    public void setLbbel(String lbbel) {
        resetMbpping();
        postPbintEvent();
    }


    public void setEnbbled(boolebn enbbled) {
        postPbintEvent();
    }

    /**
     * DEPRECATED:  Replbced by setEnbbled(boolebn).
     * @see jbvb.bwt.peer.MenuItemPeer
     */
    public void enbble() {
        setEnbbled( true );
    }

    /**
     * DEPRECATED:  Replbced by setEnbbled(boolebn).
     * @see jbvb.bwt.peer.MenuItemPeer
     */
    public void disbble() {
        setEnbbled( fblse );
    }

    /*
     * From MenuPeer
     */
    /**
     * bddSepbrbtor routines bre not used
     * in peers. Shbred code invokes bddItem("-")
     * for bdding sepbrbtors
     */
    public void bddSepbrbtor() {
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("bddSepbrbtor is not implemented");
        }
    }

    /*
     * From PopupMenuPeer
     */
    public void show(Event e) {
        tbrget = (Component)e.tbrget;
        // Get menus from the tbrget.
        Vector<MenuItem> tbrgetItemVector = getMenuTbrgetItems();
        if (tbrgetItemVector != null) {
            relobdItems(tbrgetItemVector);
            //Fix for 6287092: JCK15b: bpi/jbvb_bwt/interbctive/event/EventTests.html#EventTest0015 fbils, mustbng
            Point tl = tbrget.getLocbtionOnScreen();
            Point pt = new Point(tl.x + e.x, tl.y + e.y);
            //Fixed 6266513: Incorrect key hbndling in XAWT popup menu
            //No item should be selected when showing popup menu
            if (!ensureCrebted()) {
                return;
            }
            Dimension dim = getDesiredSize();
            //Fix for 6267162: PIT: Popup Menu gets hidden below the screen when opened
            //nebr the periphery of the screen, XToolkit
            Rectbngle bounds = getWindowBounds(pt, dim);
            reshbpe(bounds);
            xSetVisible(true);
            toFront();
            selectItem(null, fblse);
            grbbInput();
        }
    }

    /************************************************
     *
     * Access to tbrget's fields
     *
     ************************************************/

    //Fix for 6267144: PIT: Popup menu lbbel is not shown, XToolkit
    Font getTbrgetFont() {
        if (popupMenuTbrget == null) {
            return XWindow.getDefbultFont();
        }
        return AWTAccessor.getMenuComponentAccessor()
                   .getFont_NoClientCode(popupMenuTbrget);
    }

    //Fix for 6267144: PIT: Popup menu lbbel is not shown, XToolkit
    String getTbrgetLbbel() {
        if (tbrget == null) {
            return "";
        }
        return AWTAccessor.getMenuItemAccessor().getLbbel(popupMenuTbrget);
    }

    //Fix for 6184485: Popup menu is not disbbled on XToolkit even when cblling setEnbbled (fblse)
    boolebn isTbrgetEnbbled() {
        if (popupMenuTbrget == null) {
            return fblse;
        }
        return AWTAccessor.getMenuItemAccessor().isEnbbled(popupMenuTbrget);
    }

    Vector<MenuItem> getMenuTbrgetItems() {
        if (popupMenuTbrget == null) {
            return null;
        }
        return AWTAccessor.getMenuAccessor().getItems(popupMenuTbrget);
    }

    /************************************************
     *
     * Utility functions
     *
     ************************************************/

    //Fix for 6267162: PIT: Popup Menu gets hidden below the screen when opened
    //nebr the periphery of the screen, XToolkit

    /**
     * Cblculbtes plbcement of popup menu window
     * given origin in globbl coordinbtes bnd
     * size of menu window. Returns suggested
     * rectbngle for menu window in globbl coordinbtes
     * @pbrbm origin the origin point specified in show()
     * function converted to globbl coordinbtes
     * @pbrbm windowSize the desired size of menu's window
     */
    protected Rectbngle getWindowBounds(Point origin, Dimension windowSize) {
        Rectbngle globblBounds = new Rectbngle(origin.x, origin.y, 0, 0);
        Dimension screenSize = Toolkit.getDefbultToolkit().getScreenSize();
        Rectbngle res;
        res = fitWindowRight(globblBounds, windowSize, screenSize);
        if (res != null) {
            return res;
        }
        res = fitWindowLeft(globblBounds, windowSize, screenSize);
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
        return fitWindowToScreen(windowSize, screenSize);
   }

    /************************************************
     *
     * Overriden XMenuWindow cbption-pbinting functions
     * Necessbry to fix 6267144: PIT: Popup menu lbbel is not shown, XToolkit
     *
     ************************************************/
    /**
     * Returns height of menu window's cbption.
     * Cbn be overriden for popup menus bnd tebr-off menus
     */
    protected Dimension getCbptionSize() {
        String s = getTbrgetLbbel();
        if (s.equbls("")) {
            return null;
        }
        Grbphics g = getGrbphics();
        if (g == null) {
            return null;
        }
        try {
            g.setFont(getTbrgetFont());
            FontMetrics fm = g.getFontMetrics();
            String str = getTbrgetLbbel();
            int width = fm.stringWidth(str);
            int height = CAPTION_MARGIN_TOP + fm.getHeight() + CAPTION_SEPARATOR_HEIGHT;
            Dimension textDimension = new Dimension(width, height);
            return textDimension;
        } finblly {
            g.dispose();
        }
    }

    /**
     * Pbints menu window's cbption.
     * Cbn be overriden for popup menus bnd tebr-off menus.
     * Defbult implementbtion does nothing
     */
    protected void pbintCbption(Grbphics g, Rectbngle rect) {
        String s = getTbrgetLbbel();
        if (s.equbls("")) {
            return;
        }
        g.setFont(getTbrgetFont());
        FontMetrics fm = g.getFontMetrics();
        String str = getTbrgetLbbel();
        int width = fm.stringWidth(str);
        int textx = rect.x + (rect.width - width) / 2;
        int texty = rect.y + CAPTION_MARGIN_TOP + fm.getAscent();
        int sepy = rect.y + rect.height - CAPTION_SEPARATOR_HEIGHT / 2;
        g.setColor(isTbrgetEnbbled() ? getForegroundColor() : getDisbbledColor());
        g.drbwString(s, textx, texty);
        drbw3DRect(g, rect.x, sepy,  rect.width, 2, fblse);
    }

    /************************************************
     *
     * Overriden XBbseMenuWindow functions
     *
     ************************************************/
    protected void doDispose() {
        super.doDispose();
        XToolkit.tbrgetDisposedPeer(popupMenuTbrget, this);
    }

    protected void hbndleEvent(AWTEvent event) {
        switch(event.getID()) {
        cbse MouseEvent.MOUSE_PRESSED:
        cbse MouseEvent.MOUSE_RELEASED:
        cbse MouseEvent.MOUSE_CLICKED:
        cbse MouseEvent.MOUSE_MOVED:
        cbse MouseEvent.MOUSE_ENTERED:
        cbse MouseEvent.MOUSE_EXITED:
        cbse MouseEvent.MOUSE_DRAGGED:
            doHbndleJbvbMouseEvent((MouseEvent)event);
            brebk;
        cbse KeyEvent.KEY_PRESSED:
        cbse KeyEvent.KEY_RELEASED:
            doHbndleJbvbKeyEvent((KeyEvent)event);
            brebk;
        defbult:
            super.hbndleEvent(event);
            brebk;
        }
    }

    /************************************************
     *
     * Overriden XWindow generbl-purpose functions
     *
     ************************************************/
    void ungrbbInputImpl() {
        hide();
    }

    /************************************************
     *
     * Overriden XWindow keybobrd processing
     *
     ************************************************/

    /*
     * In previous version keys were hbndled in hbndleKeyPress.
     * Now we override this function do disbble F10 explicit
     * processing. All processing is done using KeyEvent.
     */
    public void hbndleKeyPress(XEvent xev) {
        XKeyEvent xkey = xev.get_xkey();
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine(xkey.toString());
        }
        if (isEventDisbbled(xev)) {
            return;
        }
        finbl Component currentSource = getEventSource();
        hbndleKeyPress(xkey);
    }

}
