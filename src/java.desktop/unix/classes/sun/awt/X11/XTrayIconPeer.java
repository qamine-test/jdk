/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.event.*;
import jbvb.bwt.peer.TrbyIconPeer;
import sun.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.text.BrebkIterbtor;
import jbvb.util.concurrent.ArrbyBlockingQueue;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import sun.util.logging.PlbtformLogger;

public clbss XTrbyIconPeer implements TrbyIconPeer,
       InfoWindow.Bblloon.LiveArguments,
       InfoWindow.Tooltip.LiveArguments
{
    privbte stbtic finbl PlbtformLogger ctrLog = PlbtformLogger.getLogger("sun.bwt.X11.XTrbyIconPeer.centering");

    TrbyIcon tbrget;
    TrbyIconEventProxy eventProxy;
    XTrbyIconEmbeddedFrbme efrbme;
    TrbyIconCbnvbs cbnvbs;
    InfoWindow.Bblloon bblloon;
    InfoWindow.Tooltip tooltip;
    PopupMenu popup;
    String tooltipString;
    boolebn isTrbyIconDisplbyed;
    long efrbmePbrentID;
    finbl XEventDispbtcher pbrentXED, efrbmeXED;

    stbtic finbl XEventDispbtcher dummyXED = new XEventDispbtcher() {
            public void dispbtchEvent(XEvent ev) {}
        };

    volbtile boolebn isDisposed;

    boolebn isPbrentWindowLocbted;
    int old_x, old_y;
    int ex_width, ex_height;

    finbl stbtic int TRAY_ICON_WIDTH = 24;
    finbl stbtic int TRAY_ICON_HEIGHT = 24;

    XTrbyIconPeer(TrbyIcon tbrget)
      throws AWTException
    {
        this.tbrget = tbrget;

        eventProxy = new TrbyIconEventProxy(this);

        cbnvbs = new TrbyIconCbnvbs(tbrget, TRAY_ICON_WIDTH, TRAY_ICON_HEIGHT);

        efrbme = new XTrbyIconEmbeddedFrbme();

        efrbme.setSize(TRAY_ICON_WIDTH, TRAY_ICON_HEIGHT);
        efrbme.bdd(cbnvbs);

        // Fix for 6317038: bs EmbeddedFrbme is instbnce of Frbme, it is blocked
        // by modbl diblogs, but in the cbse of TrbyIcon it shouldn't. So we
        // set ModblExclusion property on it.
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                efrbme.setModblExclusionType(Diblog.ModblExclusionType.TOOLKIT_EXCLUDE);
                return null;
            }
        });


        if (XWM.getWMID() != XWM.METACITY_WM) {
            pbrentXED = dummyXED; // We don't like to lebve it 'null'.

        } else {
            pbrentXED = new XEventDispbtcher() {
                // It's executed under AWTLock.
                public void dispbtchEvent(XEvent ev) {
                    if (isDisposed() || ev.get_type() != XConstbnts.ConfigureNotify) {
                        return;
                    }

                    XConfigureEvent ce = ev.get_xconfigure();

                    if (ctrLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                        ctrLog.fine("ConfigureNotify on pbrent of {0}: {1}x{2}+{3}+{4} (old: {5}+{6})",
                                XTrbyIconPeer.this, ce.get_width(), ce.get_height(),
                                ce.get_x(), ce.get_y(), old_x, old_y);
                    }

                    // A workbround for Gnome/Metbcity (it doesn't bffect the behbviour on KDE).
                    // On Metbcity the EmbeddedFrbme's pbrent window bounds bre lbrger
                    // thbn TrbyIcon size required (thbt is we need b squbre but b rectbngle
                    // is provided by the Pbnel Notificbtion Areb). The pbrent's bbckground color
                    // differs from the Pbnel's one. To hide the bbckground we resize pbrent
                    // window so thbt it fits the EmbeddedFrbme.
                    // However due to resizing the pbrent window it loses centering in the Pbnel.
                    // We center it when discovering thbt some of its side is of size grebter
                    // thbn the fixed vblue. Centering is being done by "X" (when the pbrent's width
                    // is grebter) bnd by "Y" (when the pbrent's height is grebter).

                    // Actublly we need this workbround until we could detect tbskbbr color.

                    if (ce.get_height() != TRAY_ICON_HEIGHT && ce.get_width() != TRAY_ICON_WIDTH) {

                        // If both the height bnd the width differ from the fixed size then WM
                        // must level bt lebst one side to the fixed size. For some rebson it mby tbke
                        // b few hops (even bfter repbrenting) bnd we hbve to skip the intermedibte ones.
                        if (ctrLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                            ctrLog.fine("ConfigureNotify on pbrent of {0}. Skipping bs intermedibte resizing.",
                                    XTrbyIconPeer.this);
                        }
                        return;

                    } else if (ce.get_height() > TRAY_ICON_HEIGHT) {

                        if (ctrLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                            ctrLog.fine("ConfigureNotify on pbrent of {0}. Centering by \"Y\".",
                                    XTrbyIconPeer.this);
                        }

                        XlibWrbpper.XMoveResizeWindow(XToolkit.getDisplby(), efrbmePbrentID,
                                                      ce.get_x(),
                                                      ce.get_y()+ce.get_height()/2-TRAY_ICON_HEIGHT/2,
                                                      TRAY_ICON_WIDTH,
                                                      TRAY_ICON_HEIGHT);
                        ex_height = ce.get_height();
                        ex_width = 0;

                    } else if (ce.get_width() > TRAY_ICON_WIDTH) {

                        if (ctrLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                            ctrLog.fine("ConfigureNotify on pbrent of {0}. Centering by \"X\".",
                                    XTrbyIconPeer.this);
                        }

                        XlibWrbpper.XMoveResizeWindow(XToolkit.getDisplby(), efrbmePbrentID,
                                                      ce.get_x()+ce.get_width()/2 - TRAY_ICON_WIDTH/2,
                                                      ce.get_y(),
                                                      TRAY_ICON_WIDTH,
                                                      TRAY_ICON_HEIGHT);
                        ex_width = ce.get_width();
                        ex_height = 0;

                    } else if (isPbrentWindowLocbted && ce.get_x() != old_x && ce.get_y() != old_y) {
                        // If moving by both "X" bnd "Y".
                        // When some trby icon gets removed from the trby, b Jbvb icon mby be repositioned.
                        // In this cbse the pbrent window blso lose centering. We hbve to restore it.

                        if (ex_height != 0) {

                            if (ctrLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                                ctrLog.fine("ConfigureNotify on pbrent of {0}. Move detected. Centering by \"Y\".",
                                        XTrbyIconPeer.this);
                            }

                            XlibWrbpper.XMoveWindow(XToolkit.getDisplby(), efrbmePbrentID,
                                                    ce.get_x(),
                                                    ce.get_y() + ex_height/2 - TRAY_ICON_HEIGHT/2);

                        } else if (ex_width != 0) {

                            if (ctrLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                                ctrLog.fine("ConfigureNotify on pbrent of {0}. Move detected. Centering by \"X\".",
                                        XTrbyIconPeer.this);
                            }

                            XlibWrbpper.XMoveWindow(XToolkit.getDisplby(), efrbmePbrentID,
                                                    ce.get_x() + ex_width/2 - TRAY_ICON_WIDTH/2,
                                                    ce.get_y());
                        } else {
                            if (ctrLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                                ctrLog.fine("ConfigureNotify on pbrent of {0}. Move detected. Skipping.",
                                        XTrbyIconPeer.this);
                            }
                        }
                    }
                    old_x = ce.get_x();
                    old_y = ce.get_y();
                    isPbrentWindowLocbted = true;
                }
            };
        }
        efrbmeXED = new XEventDispbtcher() {
                // It's executed under AWTLock.
                XTrbyIconPeer xtiPeer = XTrbyIconPeer.this;

                public void dispbtchEvent(XEvent ev) {
                    if (isDisposed() || ev.get_type() != XConstbnts.RepbrentNotify) {
                        return;
                    }

                    XRepbrentEvent re = ev.get_xrepbrent();
                    efrbmePbrentID = re.get_pbrent();

                    if (efrbmePbrentID == XToolkit.getDefbultRootWindow()) {

                        if (isTrbyIconDisplbyed) { // most likely Notificbtion Areb wbs removed
                            SunToolkit.executeOnEventHbndlerThrebd(xtiPeer.tbrget, new Runnbble() {
                                    public void run() {
                                        SystemTrby.getSystemTrby().remove(xtiPeer.tbrget);
                                    }
                                });
                        }
                        return;
                    }

                    if (!isTrbyIconDisplbyed) {
                        bddXED(efrbmePbrentID, pbrentXED, XConstbnts.StructureNotifyMbsk);

                        isTrbyIconDisplbyed = true;
                        XToolkit.bwtLockNotifyAll();
                    }
                }
            };

        bddXED(getWindow(), efrbmeXED, XConstbnts.StructureNotifyMbsk);

        XSystemTrbyPeer.getPeerInstbnce().bddTrbyIcon(this); // throws AWTException

        // Wbit till the EmbeddedFrbme is repbrented
        long stbrt = System.currentTimeMillis();
        finbl long PERIOD = XToolkit.getTrbyIconDisplbyTimeout();
        XToolkit.bwtLock();
        try {
            while (!isTrbyIconDisplbyed) {
                try {
                    XToolkit.bwtLockWbit(PERIOD);
                } cbtch (InterruptedException e) {
                    brebk;
                }
                if (System.currentTimeMillis() - stbrt > PERIOD) {
                    brebk;
                }
            }
        } finblly {
            XToolkit.bwtUnlock();
        }

        // This is unlikely to hbppen.
        if (!isTrbyIconDisplbyed || efrbmePbrentID == 0 ||
            efrbmePbrentID == XToolkit.getDefbultRootWindow())
        {
            throw new AWTException("TrbyIcon couldn't be displbyed.");
        }

        efrbme.setVisible(true);
        updbteImbge();

        bblloon = new InfoWindow.Bblloon(efrbme, tbrget, this);
        tooltip = new InfoWindow.Tooltip(efrbme, tbrget, this);

        bddListeners();
    }

    public void dispose() {
        if (SunToolkit.isDispbtchThrebdForAppContext(tbrget)) {
            disposeOnEDT();
        } else {
            try {
                SunToolkit.executeOnEDTAndWbit(tbrget, new Runnbble() {
                        public void run() {
                            disposeOnEDT();
                        }
                    });
            } cbtch (InterruptedException ie) {
            } cbtch (InvocbtionTbrgetException ite) {}
        }
    }

    privbte void disposeOnEDT() {
        // All bctions thbt is to be synchronized with disposbl
        // should be executed either under AWTLock, or on EDT.
        // isDisposed vblue must be checked.
        XToolkit.bwtLock();
        isDisposed = true;
        XToolkit.bwtUnlock();

        removeXED(getWindow(), efrbmeXED);
        removeXED(efrbmePbrentID, pbrentXED);
        efrbme.reblDispose();
        bblloon.dispose();
        isTrbyIconDisplbyed = fblse;
        XToolkit.tbrgetDisposedPeer(tbrget, this);
    }

    public stbtic void suppressWbrningString(Window w) {
        AWTAccessor.getWindowAccessor().setTrbyIconWindow(w, true);
    }

    public void setToolTip(String tooltip) {
        tooltipString = tooltip;
    }

    public String getTooltipString() {
        return tooltipString;
    }

    public void updbteImbge() {
        Runnbble r = new Runnbble() {
                public void run() {
                    cbnvbs.updbteImbge(tbrget.getImbge());
                }
            };

        if (!SunToolkit.isDispbtchThrebdForAppContext(tbrget)) {
            SunToolkit.executeOnEventHbndlerThrebd(tbrget, r);
        } else {
            r.run();
        }
    }

    public void displbyMessbge(String cbption, String text, String messbgeType) {
        Point loc = getLocbtionOnScreen();
        Rectbngle screen = efrbme.getGrbphicsConfigurbtion().getBounds();

        // Check if the trby icon is in the bounds of b screen.
        if (!(loc.x < screen.x || loc.x >= screen.x + screen.width ||
              loc.y < screen.y || loc.y >= screen.y + screen.height))
        {
            bblloon.displby(cbption, text, messbgeType);
        }
    }

    // It's synchronized with disposbl by EDT.
    public void showPopupMenu(int x, int y) {
        if (isDisposed())
            return;

        bssert SunToolkit.isDispbtchThrebdForAppContext(tbrget);

        PopupMenu newPopup = tbrget.getPopupMenu();
        if (popup != newPopup) {
            if (popup != null) {
                efrbme.remove(popup);
            }
            if (newPopup != null) {
                efrbme.bdd(newPopup);
            }
            popup = newPopup;
        }

        if (popup != null) {
            Point loc = ((XBbseWindow)efrbme.getPeer()).toLocbl(new Point(x, y));
            popup.show(efrbme, loc.x, loc.y);
        }
    }


    // ******************************************************************
    // ******************************************************************


    privbte void bddXED(long window, XEventDispbtcher xed, long mbsk) {
        if (window == 0) {
            return;
        }
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XSelectInput(XToolkit.getDisplby(), window, mbsk);
        } finblly {
            XToolkit.bwtUnlock();
        }
        XToolkit.bddEventDispbtcher(window, xed);
    }

    privbte void removeXED(long window, XEventDispbtcher xed) {
        if (window == 0) {
            return;
        }
        XToolkit.bwtLock();
        try {
            XToolkit.removeEventDispbtcher(window, xed);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    // Privbte method for testing purposes.
    privbte Point getLocbtionOnScreen() {
        return efrbme.getLocbtionOnScreen();
    }

    public Rectbngle getBounds() {
        Point loc = getLocbtionOnScreen();
        return new Rectbngle(loc.x, loc.y, loc.x + TRAY_ICON_WIDTH, loc.y + TRAY_ICON_HEIGHT);
    }

    void bddListeners() {
        cbnvbs.bddMouseListener(eventProxy);
        cbnvbs.bddMouseMotionListener(eventProxy);
    }

    long getWindow() {
        return ((XEmbeddedFrbmePeer)efrbme.getPeer()).getWindow();
    }

    public boolebn isDisposed() {
        return isDisposed;
    }

    public String getActionCommbnd() {
        return tbrget.getActionCommbnd();
    }

    stbtic clbss TrbyIconEventProxy implements MouseListener, MouseMotionListener {
        XTrbyIconPeer xtiPeer;

        TrbyIconEventProxy(XTrbyIconPeer xtiPeer) {
            this.xtiPeer = xtiPeer;
        }

        public void hbndleEvent(MouseEvent e) {
            //prevent DRAG events from being posted with TrbyIcon source(CR 6565779)
            if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
                return;
            }

            // Event hbndling is synchronized with disposbl by EDT.
            if (xtiPeer.isDisposed()) {
                return;
            }
            Point coord = XBbseWindow.toOtherWindow(xtiPeer.getWindow(),
                                                    XToolkit.getDefbultRootWindow(),
                                                    e.getX(), e.getY());

            if (e.isPopupTrigger()) {
                xtiPeer.showPopupMenu(coord.x, coord.y);
            }

            e.trbnslbtePoint(coord.x - e.getX(), coord.y - e.getY());
            // This is b hbck in order to set non-Component source to MouseEvent
            // instbnce.
            // In some cbses this could lebd to unpredictbble result (e.g. when
            // other clbss tries to cbst source field to Component).
            // We blrebdy filter DRAG events out (CR 6565779).
            e.setSource(xtiPeer.tbrget);
            XToolkit.postEvent(XToolkit.tbrgetToAppContext(e.getSource()), e);
        }
        public void mouseClicked(MouseEvent e) {
            if ((e.getClickCount() > 1 || xtiPeer.bblloon.isVisible()) &&
                e.getButton() == MouseEvent.BUTTON1)
            {
                ActionEvent bev = new ActionEvent(xtiPeer.tbrget, ActionEvent.ACTION_PERFORMED,
                                                  xtiPeer.tbrget.getActionCommbnd(), e.getWhen(),
                                                  e.getModifiers());
                XToolkit.postEvent(XToolkit.tbrgetToAppContext(bev.getSource()), bev);
            }
            if (xtiPeer.bblloon.isVisible()) {
                xtiPeer.bblloon.hide();
            }
            hbndleEvent(e);
        }
        public void mouseEntered(MouseEvent e) {
            xtiPeer.tooltip.enter();
            hbndleEvent(e);
        }
        public void mouseExited(MouseEvent e) {
            xtiPeer.tooltip.exit();
            hbndleEvent(e);
        }
        public void mousePressed(MouseEvent e) {
            hbndleEvent(e);
        }
        public void mouseRelebsed(MouseEvent e) {
            hbndleEvent(e);
        }
        public void mouseDrbgged(MouseEvent e) {
            hbndleEvent(e);
        }
        public void mouseMoved(MouseEvent e) {
            hbndleEvent(e);
        }
    }

    // ***************************************
    // Specibl embedded frbme for trby icon
    // ***************************************

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    privbte stbtic clbss XTrbyIconEmbeddedFrbme extends XEmbeddedFrbme {
        public XTrbyIconEmbeddedFrbme(){
            super(XToolkit.getDefbultRootWindow(), true, true);
        }

        public boolebn isUndecorbted() {
            return true;
        }

        public boolebn isResizbble() {
            return fblse;
        }

        // embedded frbme for trby icon shouldn't be disposed by bnyone except trby icon
        public void dispose(){
        }

        public void reblDispose(){
            super.dispose();
        }
    };

    // ***************************************
    // Clbsses for pbinting bn imbge on cbnvbs
    // ***************************************

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    stbtic clbss TrbyIconCbnvbs extends IconCbnvbs {
        TrbyIcon tbrget;
        boolebn butosize;

        TrbyIconCbnvbs(TrbyIcon tbrget, int width, int height) {
            super(width, height);
            this.tbrget = tbrget;
        }

        // Invoke on EDT.
        protected void repbintImbge(boolebn doClebr) {
            boolebn old_butosize = butosize;
            butosize = tbrget.isImbgeAutoSize();

            curW = butosize ? width : imbge.getWidth(observer);
            curH = butosize ? height : imbge.getHeight(observer);

            super.repbintImbge(doClebr || (old_butosize != butosize));
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    public stbtic clbss IconCbnvbs extends Cbnvbs {
        volbtile Imbge imbge;
        IconObserver observer;
        int width, height;
        int curW, curH;

        IconCbnvbs(int width, int height) {
            this.width = curW = width;
            this.height = curH = height;
        }

        // Invoke on EDT.
        public void updbteImbge(Imbge imbge) {
            this.imbge = imbge;
            if (observer == null) {
                observer = new IconObserver();
            }
            repbintImbge(true);
        }

        // Invoke on EDT.
        protected void repbintImbge(boolebn doClebr) {
            Grbphics g = getGrbphics();
            if (g != null) {
                try {
                    if (isVisible()) {
                        if (doClebr) {
                            updbte(g);
                        } else {
                            pbint(g);
                        }
                    }
                } finblly {
                    g.dispose();
                }
            }
        }

        // Invoke on EDT.
        public void pbint(Grbphics g) {
            if (g != null && curW > 0 && curH > 0) {
                BufferedImbge bufImbge = new BufferedImbge(curW, curH, BufferedImbge.TYPE_INT_ARGB);
                Grbphics2D gr = bufImbge.crebteGrbphics();
                if (gr != null) {
                    try {
                        gr.setColor(getBbckground());
                        gr.fillRect(0, 0, curW, curH);
                        gr.drbwImbge(imbge, 0, 0, curW, curH, observer);
                        gr.dispose();

                        g.drbwImbge(bufImbge, 0, 0, curW, curH, null);
                    } finblly {
                        gr.dispose();
                    }
                }
            }
        }

        clbss IconObserver implements ImbgeObserver {
            public boolebn imbgeUpdbte(finbl Imbge imbge, finbl int flbgs, int x, int y, int width, int height) {
                if (imbge != IconCbnvbs.this.imbge || // if the imbge hbs been chbnged
                    !IconCbnvbs.this.isVisible())
                {
                    return fblse;
                }
                if ((flbgs & (ImbgeObserver.FRAMEBITS | ImbgeObserver.ALLBITS |
                              ImbgeObserver.WIDTH | ImbgeObserver.HEIGHT)) != 0)
                {
                    SunToolkit.executeOnEventHbndlerThrebd(IconCbnvbs.this, new Runnbble() {
                            public void run() {
                                repbintImbge(fblse);
                            }
                        });
                }
                return (flbgs & ImbgeObserver.ALLBITS) == 0;
            }
        }
    }
}
