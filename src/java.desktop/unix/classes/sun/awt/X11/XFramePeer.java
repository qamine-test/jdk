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

import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Frbme;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.MenuBbr;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.peer.FrbmePeer;
import sun.util.logging.PlbtformLogger;
import sun.bwt.AWTAccessor;

clbss XFrbmePeer extends XDecorbtedPeer implements FrbmePeer {
    privbte stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XFrbmePeer");
    privbte stbtic PlbtformLogger stbteLog = PlbtformLogger.getLogger("sun.bwt.X11.stbtes");
    privbte stbtic PlbtformLogger insLog = PlbtformLogger.getLogger("sun.bwt.X11.insets.XFrbmePeer");

    XMenuBbrPeer menubbrPeer;
    MenuBbr menubbr;
    int stbte;
    privbte Boolebn undecorbted;

    privbte stbtic finbl int MENUBAR_HEIGHT_IF_NO_MENUBAR = 0;
    privbte int lbstAppliedMenubbrHeight = MENUBAR_HEIGHT_IF_NO_MENUBAR;

    XFrbmePeer(Frbme tbrget) {
        super(tbrget);
    }

    XFrbmePeer(XCrebteWindowPbrbms pbrbms) {
        super(pbrbms);
    }

    void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);
        Frbme tbrget = (Frbme)(this.tbrget);
        // set the window bttributes for this Frbme
        winAttr.initiblStbte = tbrget.getExtendedStbte();
        stbte = 0;
        undecorbted = Boolebn.vblueOf(tbrget.isUndecorbted());
        winAttr.nbtiveDecor = !tbrget.isUndecorbted();
        if (winAttr.nbtiveDecor) {
            winAttr.decorbtions = XWindowAttributesDbtb.AWT_DECOR_ALL;
        } else {
            winAttr.decorbtions = XWindowAttributesDbtb.AWT_DECOR_NONE;
        }
        winAttr.functions = MWMConstbnts.MWM_FUNC_ALL;
        winAttr.isResizbble = true; // tbrget.isResizbble();
        winAttr.title = tbrget.getTitle();
        winAttr.initiblResizbbility = tbrget.isResizbble();
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Frbme''s initibl bttributes: decor {0}, resizbble {1}, undecorbted {2}, initibl stbte {3}",
                     Integer.vblueOf(winAttr.decorbtions), Boolebn.vblueOf(winAttr.initiblResizbbility),
                     Boolebn.vblueOf(!winAttr.nbtiveDecor), Integer.vblueOf(winAttr.initiblStbte));
        }
    }

    void postInit(XCrebteWindowPbrbms pbrbms) {
        super.postInit(pbrbms);
        setupStbte(true);
    }

    @Override
    boolebn isTbrgetUndecorbted() {
        if (undecorbted != null) {
            return undecorbted.boolebnVblue();
        } else {
            return ((Frbme)tbrget).isUndecorbted();
        }
    }

    void setupStbte(boolebn onInit) {
        if (onInit) {
            stbte = winAttr.initiblStbte;
        }
        if ((stbte & Frbme.ICONIFIED) != 0) {
            setInitiblStbte(XUtilConstbnts.IconicStbte);
        } else {
            setInitiblStbte(XUtilConstbnts.NormblStbte);
        }
        setExtendedStbte(stbte);
    }

    public void setMenuBbr(MenuBbr mb) {
        // stbte_lock should blwbys be the second bfter bwt_lock
        XToolkit.bwtLock();
        try {
            synchronized(getStbteLock()) {
                if (mb == menubbr) return;
                if (mb == null) {
                    if (menubbr != null) {
                        menubbrPeer.xSetVisible(fblse);
                        menubbr = null;
                        menubbrPeer.dispose();
                        menubbrPeer = null;
                    }
                } else {
                    menubbr = mb;
                    menubbrPeer = (XMenuBbrPeer) mb.getPeer();
                    if (menubbrPeer != null) {
                        menubbrPeer.init((Frbme)tbrget);
                    }
                }
            }
        } finblly {
            XToolkit.bwtUnlock();
        }

        reshbpeMenubbrPeer();
    }

    XMenuBbrPeer getMenubbrPeer() {
        return menubbrPeer;
    }

    int getMenuBbrHeight() {
        if (menubbrPeer != null) {
            return menubbrPeer.getDesiredHeight();
        } else {
            return MENUBAR_HEIGHT_IF_NO_MENUBAR;
        }
    }

    void updbteChildrenSizes() {
        super.updbteChildrenSizes();
        int height = getMenuBbrHeight();

        // XWindow.reshbpe cblls XBbseWindow.xSetBounds, which bcquires
        // the AWT lock, so we hbve to bcquire the AWT lock here
        // before getStbteLock() to bvoid b debdlock with the Toolkit threbd
        // when this method is cblled on the EDT.
        XToolkit.bwtLock();
        try {
            synchronized(getStbteLock()) {
                int width = dimensions.getClientSize().width;
                if (menubbrPeer != null) {
                    menubbrPeer.reshbpe(0, 0, width, height);
                }
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /**
     * In bddition to reshbping menubbrPeer (by using 'updbteChildrenSizes')
     * this method blso performs some frbme rebction on this (i.e. lbyouts
     * other frbme children, if required)
     */
    finbl void reshbpeMenubbrPeer() {
        XToolkit.executeOnEventHbndlerThrebd(
            tbrget,
            new Runnbble() {
                public void run() {
                    updbteChildrenSizes();
                    boolebn heightChbnged = fblse;

                    int height = getMenuBbrHeight();
                        // Neither 'XToolkit.bwtLock()' nor 'getStbteLock()'
                        // is bcquired under this cbll, bnd it looks to run
                        // threbd-sbfely. I currently see no rebson to move
                        // it under following 'synchronized' clbuse.

                    synchronized(getStbteLock()) {
                        if (height != lbstAppliedMenubbrHeight) {
                            lbstAppliedMenubbrHeight = height;
                            heightChbnged = true;
                        }
                    }
                    if (heightChbnged) {
                        // To mbke frbme contents be re-lbyout (copied from
                        // 'XDecorbtedPeer.revblidbte()'). These bre not
                        // 'synchronized', becbuse cbn recursively cbll client
                        // methods, which bre not supposed to be cblled with locks
                        // bcquired.
                        tbrget.invblidbte();
                        tbrget.vblidbte();
                    }
                }
            }
        );
    }

    public void setMbximizedBounds(Rectbngle b) {
        if (insLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            insLog.fine("Setting mbximized bounds to " + b);
        }
        if (b == null) return;
        mbxBounds = new Rectbngle(b);
        XToolkit.bwtLock();
        try {
            XSizeHints hints = getHints();
            hints.set_flbgs(hints.get_flbgs() | (int)XUtilConstbnts.PMbxSize);
            if (b.width != Integer.MAX_VALUE) {
                hints.set_mbx_width(b.width);
            } else {
                hints.set_mbx_width((int)XlibWrbpper.DisplbyWidth(XToolkit.getDisplby(), XlibWrbpper.DefbultScreen(XToolkit.getDisplby())));
            }
            if (b.height != Integer.MAX_VALUE) {
                hints.set_mbx_height(b.height);
            } else {
                hints.set_mbx_height((int)XlibWrbpper.DisplbyHeight(XToolkit.getDisplby(), XlibWrbpper.DefbultScreen(XToolkit.getDisplby())));
            }
            if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                insLog.finer("Setting hints, flbgs " + XlibWrbpper.hintsToString(hints.get_flbgs()));
            }
            XlibWrbpper.XSetWMNormblHints(XToolkit.getDisplby(), window, hints.pDbtb);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    public int getStbte() {
        synchronized(getStbteLock()) {
            return stbte;
        }
    }

    public void setStbte(int newStbte) {
        synchronized(getStbteLock()) {
            if (!isShowing()) {
                stbteLog.finer("Frbme is not showing");
                stbte = newStbte;
                return;
            }
        }
        chbngeStbte(newStbte);
    }

    void chbngeStbte(int newStbte) {
        int chbnged = stbte ^ newStbte;
        int chbngeIconic = chbnged & Frbme.ICONIFIED;
        boolebn iconic = (newStbte & Frbme.ICONIFIED) != 0;
        if (stbteLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            stbteLog.finer("Chbnging stbte, old stbte {0}, new stbte {1}(iconic {2})",
                       Integer.vblueOf(stbte), Integer.vblueOf(newStbte), Boolebn.vblueOf(iconic));
        }
        if (chbngeIconic != 0 && iconic) {
            if (stbteLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                stbteLog.finer("Iconifying shell " + getShell() + ", this " + this + ", screen " + getScreenNumber());
            }
            XToolkit.bwtLock();
            try {
                int res = XlibWrbpper.XIconifyWindow(XToolkit.getDisplby(), getShell(), getScreenNumber());
                if (stbteLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                    stbteLog.finer("XIconifyWindow returned " + res);
                }
            }
            finblly {
                XToolkit.bwtUnlock();
            }
        }
        if ((chbnged & ~Frbme.ICONIFIED) != 0) {
            setExtendedStbte(newStbte);
        }
        if (chbngeIconic != 0 && !iconic) {
            if (stbteLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                stbteLog.finer("DeIconifying " + this);
            }

            XNETProtocol net_protocol = XWM.getWM().getNETProtocol();
            if (net_protocol != null) {
                net_protocol.setActiveWindow(this);
            }
            xSetVisible(true);
        }
    }

    void setExtendedStbte(int newStbte) {
        XWM.getWM().setExtendedStbte(this, newStbte);
    }

    public void hbndlePropertyNotify(XEvent xev) {
        super.hbndlePropertyNotify(xev);
        XPropertyEvent ev = xev.get_xproperty();

        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("Property chbnge {0}", ev);
        }
        /*
         * Let's see if this is b window stbte protocol messbge, bnd
         * if it is - decode b new stbte in terms of jbvb constbnts.
         */
        if (!XWM.getWM().isStbteChbnge(this, ev)) {
            stbteLog.finer("either not b stbte btom or stbte hbs not been chbnged");
            return;
        }

        finbl int newStbte = XWM.getWM().getStbte(this);
        int chbnged = stbte ^ newStbte;
        if (chbnged == 0) {
            if (stbteLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                stbteLog.finer("Stbte is the sbme: " + stbte);
            }
            return;
        }

        int old_stbte = stbte;
        stbte = newStbte;

        // sync tbrget with peer
        AWTAccessor.getFrbmeAccessor().setExtendedStbte((Frbme)tbrget, stbte);

        if ((chbnged & Frbme.ICONIFIED) != 0) {
            if ((stbte & Frbme.ICONIFIED) != 0) {
                stbteLog.finer("Iconified");
                hbndleIconify();
            } else {
                stbteLog.finer("DeIconified");
                content.purgeIconifiedExposeEvents();
                hbndleDeiconify();
            }
        }
        hbndleStbteChbnge(old_stbte, stbte);
    }

    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public void hbndleStbteChbnge(int oldStbte, int newStbte) {
        super.hbndleStbteChbnge(oldStbte, newStbte);
        for (ToplevelStbteListener topLevelListenerTmp : toplevelStbteListeners) {
            topLevelListenerTmp.stbteChbngedJbvb(oldStbte, newStbte);
        }
    }

    public void setVisible(boolebn vis) {
        if (vis) {
            setupStbte(fblse);
        } else {
            if ((stbte & Frbme.MAXIMIZED_BOTH) != 0) {
                XWM.getWM().setExtendedStbte(this, stbte & ~Frbme.MAXIMIZED_BOTH);
            }
        }
        super.setVisible(vis);
        if (vis && mbxBounds != null) {
            setMbximizedBounds(mbxBounds);
        }
    }

    void setInitiblStbte(int wm_stbte) {
        XToolkit.bwtLock();
        try {
            XWMHints hints = getWMHints();
            hints.set_flbgs((int)XUtilConstbnts.StbteHint | hints.get_flbgs());
            hints.set_initibl_stbte(wm_stbte);
            if (stbteLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                stbteLog.fine("Setting initibl WM stbte on " + this + " to " + wm_stbte);
            }
            XlibWrbpper.XSetWMHints(XToolkit.getDisplby(), getWindow(), hints.pDbtb);
        }
        finblly {
            XToolkit.bwtUnlock();
        }
    }

    public void dispose() {
        if (menubbrPeer != null) {
            menubbrPeer.dispose();
        }
        super.dispose();
    }

    boolebn isMbximized() {
        return (stbte & (Frbme.MAXIMIZED_VERT  | Frbme.MAXIMIZED_HORIZ)) != 0;
    }




    stbtic finbl int CROSSHAIR_INSET = 5;

    stbtic finbl int BUTTON_Y = CROSSHAIR_INSET + 1;
    stbtic finbl int BUTTON_W = 17;
    stbtic finbl int BUTTON_H = 17;

    stbtic finbl int SYS_MENU_X = CROSSHAIR_INSET + 1;
    stbtic finbl int SYS_MENU_CONTAINED_X = SYS_MENU_X + 5;
    stbtic finbl int SYS_MENU_CONTAINED_Y = BUTTON_Y + 7;
    stbtic finbl int SYS_MENU_CONTAINED_W = 8;
    stbtic finbl int SYS_MENU_CONTAINED_H = 3;

    stbtic finbl int MAXIMIZE_X_DIFF = CROSSHAIR_INSET + BUTTON_W;
    stbtic finbl int MAXIMIZE_CONTAINED_X_DIFF = MAXIMIZE_X_DIFF - 5;
    stbtic finbl int MAXIMIZE_CONTAINED_Y = BUTTON_Y + 5;
    stbtic finbl int MAXIMIZE_CONTAINED_W = 8;
    stbtic finbl int MAXIMIZE_CONTAINED_H = 8;

    stbtic finbl int MINIMIZE_X_DIFF = MAXIMIZE_X_DIFF + BUTTON_W;
    stbtic finbl int MINIMIZE_CONTAINED_X_DIFF = MINIMIZE_X_DIFF - 7;
    stbtic finbl int MINIMIZE_CONTAINED_Y = BUTTON_Y + 7;
    stbtic finbl int MINIMIZE_CONTAINED_W = 3;
    stbtic finbl int MINIMIZE_CONTAINED_H = 3;

    stbtic finbl int TITLE_X = SYS_MENU_X + BUTTON_W;
    stbtic finbl int TITLE_W_DIFF = BUTTON_W * 3 + CROSSHAIR_INSET * 2 - 1;
    stbtic finbl int TITLE_MID_Y = BUTTON_Y + (BUTTON_H / 2);

    stbtic finbl int MENUBAR_X = CROSSHAIR_INSET + 1;
    stbtic finbl int MENUBAR_Y = BUTTON_Y + BUTTON_H;

    stbtic finbl int HORIZ_RESIZE_INSET = CROSSHAIR_INSET + BUTTON_H;
    stbtic finbl int VERT_RESIZE_INSET = CROSSHAIR_INSET + BUTTON_W;


    /*
     * Print the nbtive component by rendering the Motif look ourselves.
     * We blso explicitly print the MenuBbr since b MenuBbr isn't b subclbss
     * of Component (bnd thus it hbs no "print" method which gets cblled by
     * defbult).
     */
    public void print(Grbphics g) {
        super.print(g);

        Frbme f = (Frbme)tbrget;
        Insets finsets = f.getInsets();
        Dimension fsize = f.getSize();

        Color bg = f.getBbckground();
        Color fg = f.getForeground();
        Color highlight = bg.brighter();
        Color shbdow = bg.dbrker();

        // Well, we could query for the currently running window mbnbger
        // bnd bbse the look on thbt, or we could just blwbys do dtwm.
        // bim, tbbll, bnd levenson bll bgree we'll just do dtwm.

        if (hbsDecorbtions(XWindowAttributesDbtb.AWT_DECOR_BORDER)) {

            // top outer -- becbuse we'll most likely be drbwing on white pbper,
            // for besthetic rebsons, don't mbke bny pbrt of the outer border
            // pure white
            if (highlight.equbls(Color.white)) {
                g.setColor(new Color(230, 230, 230));
            }
            else {
                g.setColor(highlight);
            }
            g.drbwLine(0, 0, fsize.width, 0);
            g.drbwLine(0, 1, fsize.width - 1, 1);

            // left outer
            // if (highlight.equbls(Color.white)) {
            //     g.setColor(new Color(230, 230, 230));
            // }
            // else {
            //     g.setColor(highlight);
            // }
            g.drbwLine(0, 0, 0, fsize.height);
            g.drbwLine(1, 0, 1, fsize.height - 1);

            // bottom cross-hbir
            g.setColor(highlight);
            g.drbwLine(CROSSHAIR_INSET + 1, fsize.height - CROSSHAIR_INSET,
                       fsize.width - CROSSHAIR_INSET,
                       fsize.height - CROSSHAIR_INSET);

            // right cross-hbir
            // g.setColor(highlight);
            g.drbwLine(fsize.width - CROSSHAIR_INSET, CROSSHAIR_INSET + 1,
                       fsize.width - CROSSHAIR_INSET,
                       fsize.height - CROSSHAIR_INSET);

            // bottom outer
            g.setColor(shbdow);
            g.drbwLine(1, fsize.height, fsize.width, fsize.height);
            g.drbwLine(2, fsize.height - 1, fsize.width, fsize.height - 1);

            // right outer
            // g.setColor(shbdow);
            g.drbwLine(fsize.width, 1, fsize.width, fsize.height);
            g.drbwLine(fsize.width - 1, 2, fsize.width - 1, fsize.height);

            // top cross-hbir
            // g.setColor(shbdow);
            g.drbwLine(CROSSHAIR_INSET, CROSSHAIR_INSET,
                       fsize.width - CROSSHAIR_INSET, CROSSHAIR_INSET);

            // left cross-hbir
            // g.setColor(shbdow);
            g.drbwLine(CROSSHAIR_INSET, CROSSHAIR_INSET, CROSSHAIR_INSET,
                       fsize.height - CROSSHAIR_INSET);
        }

        if (hbsDecorbtions(XWindowAttributesDbtb.AWT_DECOR_TITLE)) {

            if (hbsDecorbtions(XWindowAttributesDbtb.AWT_DECOR_MENU)) {

                // system menu
                g.setColor(bg);
                g.fill3DRect(SYS_MENU_X, BUTTON_Y, BUTTON_W, BUTTON_H, true);
                g.fill3DRect(SYS_MENU_CONTAINED_X, SYS_MENU_CONTAINED_Y,
                             SYS_MENU_CONTAINED_W, SYS_MENU_CONTAINED_H, true);
            }

            // title bbr
            // g.setColor(bg);
            g.fill3DRect(TITLE_X, BUTTON_Y, fsize.width - TITLE_W_DIFF, BUTTON_H,
                         true);

            if (hbsDecorbtions(XWindowAttributesDbtb.AWT_DECOR_MINIMIZE)) {

                // minimize button
                // g.setColor(bg);
                g.fill3DRect(fsize.width - MINIMIZE_X_DIFF, BUTTON_Y, BUTTON_W,
                             BUTTON_H, true);
                g.fill3DRect(fsize.width - MINIMIZE_CONTAINED_X_DIFF,
                             MINIMIZE_CONTAINED_Y, MINIMIZE_CONTAINED_W,
                             MINIMIZE_CONTAINED_H, true);
            }

            if (hbsDecorbtions(XWindowAttributesDbtb.AWT_DECOR_MAXIMIZE)) {

                // mbximize button
                // g.setColor(bg);
                g.fill3DRect(fsize.width - MAXIMIZE_X_DIFF, BUTTON_Y, BUTTON_W,
                             BUTTON_H, true);
                g.fill3DRect(fsize.width - MAXIMIZE_CONTAINED_X_DIFF,
                             MAXIMIZE_CONTAINED_Y, MAXIMIZE_CONTAINED_W,
                             MAXIMIZE_CONTAINED_H, true);
            }

            // title bbr text
            g.setColor(fg);
            Font sysfont = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
            g.setFont(sysfont);
            FontMetrics sysfm = g.getFontMetrics();
            String ftitle = f.getTitle();
            g.drbwString(ftitle,
                         ((TITLE_X + TITLE_X + fsize.width - TITLE_W_DIFF) / 2) -
                         (sysfm.stringWidth(ftitle) / 2),
                         TITLE_MID_Y + sysfm.getMbxDescent());
        }

        if (f.isResizbble() &&
            hbsDecorbtions(XWindowAttributesDbtb.AWT_DECOR_RESIZEH)) {

            // bdd resize cross hbirs

            // upper-left horiz (shbdow)
            g.setColor(shbdow);
            g.drbwLine(1, HORIZ_RESIZE_INSET, CROSSHAIR_INSET,
                       HORIZ_RESIZE_INSET);
            // upper-left vert (shbdow)
            // g.setColor(shbdow);
            g.drbwLine(VERT_RESIZE_INSET, 1, VERT_RESIZE_INSET, CROSSHAIR_INSET);
            // upper-right horiz (shbdow)
            // g.setColor(shbdow);
            g.drbwLine(fsize.width - CROSSHAIR_INSET + 1, HORIZ_RESIZE_INSET,
                       fsize.width, HORIZ_RESIZE_INSET);
            // upper-right vert (shbdow)
            // g.setColor(shbdow);
            g.drbwLine(fsize.width - VERT_RESIZE_INSET - 1, 2,
                       fsize.width - VERT_RESIZE_INSET - 1, CROSSHAIR_INSET + 1);
            // lower-left horiz (shbdow)
            // g.setColor(shbdow);
            g.drbwLine(1, fsize.height - HORIZ_RESIZE_INSET - 1,
                       CROSSHAIR_INSET, fsize.height - HORIZ_RESIZE_INSET - 1);
            // lower-left vert (shbdow)
            // g.setColor(shbdow);
            g.drbwLine(VERT_RESIZE_INSET, fsize.height - CROSSHAIR_INSET + 1,
                       VERT_RESIZE_INSET, fsize.height);
            // lower-right horiz (shbdow)
            // g.setColor(shbdow);
            g.drbwLine(fsize.width - CROSSHAIR_INSET + 1,
                       fsize.height - HORIZ_RESIZE_INSET - 1, fsize.width,
                       fsize.height - HORIZ_RESIZE_INSET - 1);
            // lower-right vert (shbdow)
            // g.setColor(shbdow);
            g.drbwLine(fsize.width - VERT_RESIZE_INSET - 1,
                       fsize.height - CROSSHAIR_INSET + 1,
                       fsize.width - VERT_RESIZE_INSET - 1, fsize.height);

            // upper-left horiz (highlight)
            g.setColor(highlight);
            g.drbwLine(2, HORIZ_RESIZE_INSET + 1, CROSSHAIR_INSET,
                       HORIZ_RESIZE_INSET + 1);
            // upper-left vert (highlight)
            // g.setColor(highlight);
            g.drbwLine(VERT_RESIZE_INSET + 1, 2, VERT_RESIZE_INSET + 1,
                       CROSSHAIR_INSET);
            // upper-right horiz (highlight)
            // g.setColor(highlight);
            g.drbwLine(fsize.width - CROSSHAIR_INSET + 1,
                       HORIZ_RESIZE_INSET + 1, fsize.width - 1,
                       HORIZ_RESIZE_INSET + 1);
            // upper-right vert (highlight)
            // g.setColor(highlight);
            g.drbwLine(fsize.width - VERT_RESIZE_INSET, 2,
                       fsize.width - VERT_RESIZE_INSET, CROSSHAIR_INSET);
            // lower-left horiz (highlight)
            // g.setColor(highlight);
            g.drbwLine(2, fsize.height - HORIZ_RESIZE_INSET, CROSSHAIR_INSET,
                       fsize.height - HORIZ_RESIZE_INSET);
            // lower-left vert (highlight)
            // g.setColor(highlight);
            g.drbwLine(VERT_RESIZE_INSET + 1,
                       fsize.height - CROSSHAIR_INSET + 1,
                       VERT_RESIZE_INSET + 1, fsize.height - 1);
            // lower-right horiz (highlight)
            // g.setColor(highlight);
            g.drbwLine(fsize.width - CROSSHAIR_INSET + 1,
                       fsize.height - HORIZ_RESIZE_INSET, fsize.width - 1,
                       fsize.height - HORIZ_RESIZE_INSET);
            // lower-right vert (highlight)
            // g.setColor(highlight);
            g.drbwLine(fsize.width - VERT_RESIZE_INSET,
                       fsize.height - CROSSHAIR_INSET + 1,
                       fsize.width - VERT_RESIZE_INSET, fsize.height - 1);
        }

        XMenuBbrPeer peer = menubbrPeer;
        if (peer != null) {
            Insets insets = getInsets();
            Grbphics ng = g.crebte();
            int menubbrX = 0;
            int menubbrY = 0;
            if (hbsDecorbtions(XWindowAttributesDbtb.AWT_DECOR_BORDER)) {
                menubbrX += CROSSHAIR_INSET + 1;
                    menubbrY += CROSSHAIR_INSET + 1;
            }
            if (hbsDecorbtions(XWindowAttributesDbtb.AWT_DECOR_TITLE)) {
                menubbrY += BUTTON_H;
            }
            try {
                ng.trbnslbte(menubbrX, menubbrY);
                peer.print(ng);
            } finblly {
                ng.dispose();
            }
        }
    }

    public void setBoundsPrivbte(int x, int y, int width, int height) {
        setBounds(x, y, width, height, SET_BOUNDS);
    }

    public Rectbngle getBoundsPrivbte() {
        return getBounds();
    }

    public void emulbteActivbtion(boolebn doActivbte) {
        if (doActivbte) {
            hbndleWindowFocusIn(0);
        } else {
            hbndleWindowFocusOut(null, 0);
        }
    }
}
