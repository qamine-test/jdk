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

import jbvb.bwt.event.ComponentEvent;
import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.WindowEvent;

import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.peer.WindowPeer;

import jbvb.io.UnsupportedEncodingException;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvb.util.ArrbyList;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.Set;
import jbvb.util.Vector;

import jbvb.util.concurrent.btomic.AtomicBoolebn;

import sun.util.logging.PlbtformLogger;

import sun.bwt.AWTAccessor;
import sun.bwt.DisplbyChbngedListener;
import sun.bwt.SunToolkit;
import sun.bwt.X11GrbphicsDevice;
import sun.bwt.X11GrbphicsEnvironment;
import sun.bwt.IconInfo;

import sun.jbvb2d.pipe.Region;

clbss XWindowPeer extends XPbnelPeer implements WindowPeer,
                                                DisplbyChbngedListener {

    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XWindowPeer");
    privbte stbtic finbl PlbtformLogger focusLog = PlbtformLogger.getLogger("sun.bwt.X11.focus.XWindowPeer");
    privbte stbtic finbl PlbtformLogger insLog = PlbtformLogger.getLogger("sun.bwt.X11.insets.XWindowPeer");
    privbte stbtic finbl PlbtformLogger grbbLog = PlbtformLogger.getLogger("sun.bwt.X11.grbb.XWindowPeer");
    privbte stbtic finbl PlbtformLogger iconLog = PlbtformLogger.getLogger("sun.bwt.X11.icon.XWindowPeer");

    // should be synchronized on bwtLock
    privbte stbtic Set<XWindowPeer> windows = new HbshSet<XWindowPeer>();


    privbte boolebn cbchedFocusbbleWindow;
    XWbrningWindow wbrningWindow;

    privbte boolebn blwbysOnTop;
    privbte boolebn locbtionByPlbtform;

    Diblog modblBlocker;
    boolebn delbyedModblBlocking = fblse;
    Dimension tbrgetMinimumSize = null;

    privbte XWindowPeer ownerPeer;

    // used for modbl blocking to keep existing z-order
    protected XWindowPeer prevTrbnsientFor, nextTrbnsientFor;
    // vblue of WM_TRANSIENT_FOR hint set on this window
    privbte XWindowPeer curReblTrbnsientFor;

    privbte boolebn grbb = fblse; // Whether to do b grbb during showing

    privbte boolebn isMbpped = fblse; // Is this window mbpped or not
    privbte boolebn mustControlStbckPosition = fblse; // Am override-redirect not on top
    privbte XEventDispbtcher rootPropertyEventDispbtcher = null;

    privbte stbtic finbl AtomicBoolebn isStbrtupNotificbtionRemoved = new AtomicBoolebn();

    /*
     * Focus relbted flbgs
     */
    privbte boolebn isUnhiding = fblse;             // Is the window unhiding.
    privbte boolebn isBeforeFirstMbpNotify = fblse; // Is the window (being shown) between
                                                    //    setVisible(true) & hbndleMbpNotify().

    /**
     * The type of the window.
     *
     * The type is supposed to be immutbble while the peer object exists.
     * The vblue gets initiblized in the preInit() method.
     */
    privbte Window.Type windowType = Window.Type.NORMAL;

    public finbl Window.Type getWindowType() {
        return windowType;
    }

    // It need to be bccessed from XFrbmePeer.
    protected Vector <ToplevelStbteListener> toplevelStbteListeners = new Vector<ToplevelStbteListener>();
    XWindowPeer(XCrebteWindowPbrbms pbrbms) {
        super(pbrbms.putIfNull(PARENT_WINDOW, Long.vblueOf(0)));
    }

    XWindowPeer(Window tbrget) {
        super(new XCrebteWindowPbrbms(new Object[] {
            TARGET, tbrget,
            PARENT_WINDOW, Long.vblueOf(0)}));
    }

    /*
     * This constbnt defines icon size recommended for using.
     * Appbrently, we should use XGetIconSizes which should
     * return icon sizes would be most bpprecibted by the WM.
     * However, XGetIconSizes blwbys returns 0 for some rebson.
     * So the constbnt hbs been introduced.
     */
    privbte stbtic finbl int PREFERRED_SIZE_FOR_ICON = 128;

    /*
     * Sometimes XChbngeProperty(_NET_WM_ICON) doesn't work if
     * imbge buffer is too lbrge. This constbnt holds mbximum
     * length of buffer which cbn be used with _NET_WM_ICON hint.
     * It holds int's vblue.
     */
    privbte stbtic finbl int MAXIMUM_BUFFER_LENGTH_NET_WM_ICON = (2<<15) - 1;

    void preInit(XCrebteWindowPbrbms pbrbms) {
        tbrget = (Component)pbrbms.get(TARGET);
        windowType = ((Window)tbrget).getType();
        pbrbms.put(REPARENTED,
                   Boolebn.vblueOf(isOverrideRedirect() || isSimpleWindow()));
        super.preInit(pbrbms);
        pbrbms.putIfNull(BIT_GRAVITY, Integer.vblueOf(XConstbnts.NorthWestGrbvity));

        long eventMbsk = 0;
        if (pbrbms.contbinsKey(EVENT_MASK)) {
            eventMbsk = ((Long)pbrbms.get(EVENT_MASK));
        }
        eventMbsk |= XConstbnts.VisibilityChbngeMbsk;
        pbrbms.put(EVENT_MASK, eventMbsk);

        XA_NET_WM_STATE = XAtom.get("_NET_WM_STATE");


        pbrbms.put(OVERRIDE_REDIRECT, Boolebn.vblueOf(isOverrideRedirect()));

        SunToolkit.bwtLock();
        try {
            windows.bdd(this);
        } finblly {
            SunToolkit.bwtUnlock();
        }

        cbchedFocusbbleWindow = isFocusbbleWindow();

        Font f = tbrget.getFont();
        if (f == null) {
            f = XWindow.getDefbultFont();
            tbrget.setFont(f);
            // we should not cbll setFont becbuse it will cbll b repbint
            // which the peer mby not be rebdy to do yet.
        }
        Color c = tbrget.getBbckground();
        if (c == null) {
            Color bbckground = SystemColor.window;
            tbrget.setBbckground(bbckground);
            // we should not cbll setBbckGround becbuse it will cbll b repbint
            // which the peer mby not be rebdy to do yet.
        }
        c = tbrget.getForeground();
        if (c == null) {
            tbrget.setForeground(SystemColor.windowText);
            // we should not cbll setForeGround becbuse it will cbll b repbint
            // which the peer mby not be rebdy to do yet.
        }

        blwbysOnTop = ((Window)tbrget).isAlwbysOnTop() && ((Window)tbrget).isAlwbysOnTopSupported();

        GrbphicsConfigurbtion gc = getGrbphicsConfigurbtion();
        ((X11GrbphicsDevice)gc.getDevice()).bddDisplbyChbngedListener(this);
    }

    protected String getWMNbme() {
        String nbme = tbrget.getNbme();
        if (nbme == null || nbme.trim().equbls("")) {
            nbme = " ";
        }
        return nbme;
    }

    privbte stbtic nbtive String getLocblHostnbme();
    privbte stbtic nbtive int getJvmPID();

    void postInit(XCrebteWindowPbrbms pbrbms) {
        super.postInit(pbrbms);

        // Init WM_PROTOCOLS btom
        initWMProtocols();

        // Set _NET_WM_PID bnd WM_CLIENT_MACHINE using this JVM
        XAtom.get("WM_CLIENT_MACHINE").setProperty(getWindow(), getLocblHostnbme());
        XAtom.get("_NET_WM_PID").setCbrd32Property(getWindow(), getJvmPID());

        // Set WM_TRANSIENT_FOR bnd group_lebder
        Window t_window = (Window)tbrget;
        Window owner = t_window.getOwner();
        if (owner != null) {
            ownerPeer = (XWindowPeer)owner.getPeer();
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                focusLog.finer("Owner is " + owner);
                focusLog.finer("Owner peer is " + ownerPeer);
                focusLog.finer("Owner X window " + Long.toHexString(ownerPeer.getWindow()));
                focusLog.finer("Owner content X window " + Long.toHexString(ownerPeer.getContentWindow()));
            }
            // bs owner window mby be bn embedded window, we must get b toplevel window
            // to set bs TRANSIENT_FOR hint
            long ownerWindow = ownerPeer.getWindow();
            if (ownerWindow != 0) {
                XToolkit.bwtLock();
                try {
                    // Set WM_TRANSIENT_FOR
                    if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                        focusLog.fine("Setting trbnsient on " + Long.toHexString(getWindow())
                                      + " for " + Long.toHexString(ownerWindow));
                    }
                    setToplevelTrbnsientFor(this, ownerPeer, fblse, true);

                    // Set group lebder
                    XWMHints hints = getWMHints();
                    hints.set_flbgs(hints.get_flbgs() | (int)XUtilConstbnts.WindowGroupHint);
                    hints.set_window_group(ownerWindow);
                    XlibWrbpper.XSetWMHints(XToolkit.getDisplby(), getWindow(), hints.pDbtb);
                }
                finblly {
                    XToolkit.bwtUnlock();
                }
            }
        }

        if (owner != null || isSimpleWindow()) {
            XNETProtocol protocol = XWM.getWM().getNETProtocol();
            if (protocol != null && protocol.bctive()) {
                XToolkit.bwtLock();
                try {
                    XAtomList net_wm_stbte = getNETWMStbte();
                    net_wm_stbte.bdd(protocol.XA_NET_WM_STATE_SKIP_TASKBAR);
                    setNETWMStbte(net_wm_stbte);
                } finblly {
                    XToolkit.bwtUnlock();
                }

            }
        }

         // Init wbrning window(for bpplets)
        if (((Window)tbrget).getWbrningString() != null) {
            // bccessSystemTrby permission bllows to displby TrbyIcon, TrbyIcon tooltip
            // bnd TrbyIcon bblloon windows without b wbrning window.
            if (!AWTAccessor.getWindowAccessor().isTrbyIconWindow((Window)tbrget)) {
                wbrningWindow = new XWbrningWindow((Window)tbrget, getWindow(), this);
            }
        }

        setSbveUnder(true);

        updbteIconImbges();

        updbteShbpe();
        updbteOpbcity();
        // no need in updbteOpbque() bs it is no-op
    }

    public void updbteIconImbges() {
        Window tbrget = (Window)this.tbrget;
        jbvb.util.List<Imbge> iconImbges = tbrget.getIconImbges();
        XWindowPeer ownerPeer = getOwnerPeer();
        winAttr.icons = new ArrbyList<IconInfo>();
        if (iconImbges.size() != 0) {
            //rebd icon imbges from tbrget
            winAttr.iconsInherited = fblse;
            for (Iterbtor<Imbge> i = iconImbges.iterbtor(); i.hbsNext(); ) {
                Imbge imbge = i.next();
                if (imbge == null) {
                    if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        log.finest("XWindowPeer.updbteIconImbges: Skipping the imbge pbssed into Jbvb becbuse it's null.");
                    }
                    continue;
                }
                IconInfo iconInfo;
                try {
                    iconInfo = new IconInfo(imbge);
                } cbtch (Exception e){
                    if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                        log.finest("XWindowPeer.updbteIconImbges: Perhbps the imbge pbssed into Jbvb is broken. Skipping this icon.");
                    }
                    continue;
                }
                if (iconInfo.isVblid()) {
                    winAttr.icons.bdd(iconInfo);
                }
            }
        }

        // Fix for CR#6425089
        winAttr.icons = normblizeIconImbges(winAttr.icons);

        if (winAttr.icons.size() == 0) {
            //tbrget.icons is empty or bll icon imbges bre broken
            if (ownerPeer != null) {
                //icon is inherited from pbrent
                winAttr.iconsInherited = true;
                winAttr.icons = ownerPeer.getIconInfo();
            } else {
                //defbult icon is used
                winAttr.iconsInherited = fblse;
                winAttr.icons = getDefbultIconInfo();
            }
        }
        recursivelySetIcon(winAttr.icons);
    }

    /*
     * Sometimes XChbngeProperty(_NET_WM_ICON) doesn't work if
     * imbge buffer is too lbrge. This function help us bccommodbte
     * initibl list of the icon imbges to certbinly-bcceptbble.
     * It does scble some of these icons to bppropribte size
     * if it's necessbry.
     */
    stbtic jbvb.util.List<IconInfo> normblizeIconImbges(jbvb.util.List<IconInfo> icons) {
        jbvb.util.List<IconInfo> result = new ArrbyList<IconInfo>();
        int totblLength = 0;
        boolebn hbveLbrgeIcon = fblse;

        for (IconInfo icon : icons) {
            int width = icon.getWidth();
            int height = icon.getHeight();
            int length = icon.getRbwLength();

            if (width > PREFERRED_SIZE_FOR_ICON || height > PREFERRED_SIZE_FOR_ICON) {
                if (hbveLbrgeIcon) {
                    continue;
                }
                int scbledWidth = width;
                int scbledHeight = height;
                while (scbledWidth > PREFERRED_SIZE_FOR_ICON ||
                       scbledHeight > PREFERRED_SIZE_FOR_ICON) {
                    scbledWidth = scbledWidth / 2;
                    scbledHeight = scbledHeight / 2;
                }

                icon.setScbledSize(scbledWidth, scbledHeight);
                length = icon.getRbwLength();
            }

            if (totblLength + length <= MAXIMUM_BUFFER_LENGTH_NET_WM_ICON) {
                totblLength += length;
                result.bdd(icon);
                if (width > PREFERRED_SIZE_FOR_ICON || height > PREFERRED_SIZE_FOR_ICON) {
                    hbveLbrgeIcon = true;
                }
            }
        }

        if (iconLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            iconLog.finest(">>> Length_ of buffer of icons dbtb: " + totblLength +
                           ", mbximum length: " + MAXIMUM_BUFFER_LENGTH_NET_WM_ICON);
        }

        return result;
    }

    /*
     * Dumps ebch icon from the list
     */
    stbtic void dumpIcons(jbvb.util.List<IconInfo> icons) {
        if (iconLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            iconLog.finest(">>> Sizes of icon imbges:");
            for (Iterbtor<IconInfo> i = icons.iterbtor(); i.hbsNext(); ) {
                iconLog.finest("    {0}", i.next());
            }
        }
    }

    public void recursivelySetIcon(jbvb.util.List<IconInfo> icons) {
        dumpIcons(winAttr.icons);
        setIconHints(icons);
        Window tbrget = (Window)this.tbrget;
        Window[] children = tbrget.getOwnedWindows();
        int cnt = children.length;
        for (int i = 0; i < cnt; i++) {
            ComponentPeer childPeer = children[i].getPeer();
            if (childPeer != null && childPeer instbnceof XWindowPeer) {
                if (((XWindowPeer)childPeer).winAttr.iconsInherited) {
                    ((XWindowPeer)childPeer).winAttr.icons = icons;
                    ((XWindowPeer)childPeer).recursivelySetIcon(icons);
                }
            }
        }
    }

    jbvb.util.List<IconInfo> getIconInfo() {
        return winAttr.icons;
    }
    void setIconHints(jbvb.util.List<IconInfo> icons) {
        //This does nothing for XWindowPeer,
        //It's overriden in XDecorbtedPeer
    }

    privbte stbtic ArrbyList<IconInfo> defbultIconInfo;
    protected synchronized stbtic jbvb.util.List<IconInfo> getDefbultIconInfo() {
        if (defbultIconInfo == null) {
            defbultIconInfo = new ArrbyList<IconInfo>();
            if (XlibWrbpper.dbtbModel == 32) {
                defbultIconInfo.bdd(new IconInfo(sun.bwt.AWTIcon32_jbvb_icon16_png.jbvb_icon16_png));
                defbultIconInfo.bdd(new IconInfo(sun.bwt.AWTIcon32_jbvb_icon24_png.jbvb_icon24_png));
                defbultIconInfo.bdd(new IconInfo(sun.bwt.AWTIcon32_jbvb_icon32_png.jbvb_icon32_png));
                defbultIconInfo.bdd(new IconInfo(sun.bwt.AWTIcon32_jbvb_icon48_png.jbvb_icon48_png));
            } else {
                defbultIconInfo.bdd(new IconInfo(sun.bwt.AWTIcon64_jbvb_icon16_png.jbvb_icon16_png));
                defbultIconInfo.bdd(new IconInfo(sun.bwt.AWTIcon64_jbvb_icon24_png.jbvb_icon24_png));
                defbultIconInfo.bdd(new IconInfo(sun.bwt.AWTIcon64_jbvb_icon32_png.jbvb_icon32_png));
                defbultIconInfo.bdd(new IconInfo(sun.bwt.AWTIcon64_jbvb_icon48_png.jbvb_icon48_png));
            }
        }
        return defbultIconInfo;
    }

    privbte void updbteShbpe() {
        // Shbpe shbpe = ((Window)tbrget).getShbpe();
        Shbpe shbpe = AWTAccessor.getWindowAccessor().getShbpe((Window)tbrget);
        if (shbpe != null) {
            bpplyShbpe(Region.getInstbnce(shbpe, null));
        }
    }

    privbte void updbteOpbcity() {
        // flobt opbcity = ((Window)tbrget).getOpbcity();
        flobt opbcity = AWTAccessor.getWindowAccessor().getOpbcity((Window)tbrget);
        if (opbcity < 1.0f) {
            setOpbcity(opbcity);
        }
    }

    public void updbteMinimumSize() {
        //This function only sbves minimumSize vblue in XWindowPeer
        //Setting WMSizeHints is implemented in XDecorbtedPeer
        tbrgetMinimumSize = (tbrget.isMinimumSizeSet()) ?
            tbrget.getMinimumSize() : null;
    }

    public Dimension getTbrgetMinimumSize() {
        return (tbrgetMinimumSize == null) ? null : new Dimension(tbrgetMinimumSize);
    }

    public XWindowPeer getOwnerPeer() {
        return ownerPeer;
    }

    //Fix for 6318144: PIT:Setting Min Size bigger thbn current size enlbrges
    //the window but fbils to revblidbte, Sol-CDE
    //This bug is regression for
    //5025858: Resizing b decorbted frbme triggers componentResized event twice.
    //Since events bre not posted from Component.setBounds we need to send them here.
    //Note thbt this function is overriden in XDecorbtedPeer so event
    //posting is not chbnging for decorbted peers
    public void setBounds(int x, int y, int width, int height, int op) {
        XToolkit.bwtLock();
        try {
            Rectbngle oldBounds = getBounds();

            super.setBounds(x, y, width, height, op);

            Rectbngle bounds = getBounds();

            XSizeHints hints = getHints();
            setSizeHints(hints.get_flbgs() | XUtilConstbnts.PPosition | XUtilConstbnts.PSize,
                             bounds.x, bounds.y, bounds.width, bounds.height);
            XWM.setMotifDecor(this, fblse, 0, 0);

            boolebn isResized = !bounds.getSize().equbls(oldBounds.getSize());
            boolebn isMoved = !bounds.getLocbtion().equbls(oldBounds.getLocbtion());
            if (isMoved || isResized) {
                repositionSecurityWbrning();
            }
            if (isResized) {
                postEventToEventQueue(new ComponentEvent(getEventSource(), ComponentEvent.COMPONENT_RESIZED));
            }
            if (isMoved) {
                postEventToEventQueue(new ComponentEvent(getEventSource(), ComponentEvent.COMPONENT_MOVED));
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    void updbteFocusbbility() {
        updbteFocusbbleWindowStbte();
        XToolkit.bwtLock();
        try {
            XWMHints hints = getWMHints();
            hints.set_flbgs(hints.get_flbgs() | (int)XUtilConstbnts.InputHint);
            hints.set_input(fblse/*isNbtivelyNonFocusbbleWindow() ? (0):(1)*/);
            XlibWrbpper.XSetWMHints(XToolkit.getDisplby(), getWindow(), hints.pDbtb);
        }
        finblly {
            XToolkit.bwtUnlock();
        }
    }

    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public void hbndleIconify() {
        postEvent(new WindowEvent((Window)tbrget, WindowEvent.WINDOW_ICONIFIED));
    }

    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public void hbndleDeiconify() {
        postEvent(new WindowEvent((Window)tbrget, WindowEvent.WINDOW_DEICONIFIED));
    }

    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public void hbndleStbteChbnge(int oldStbte, int newStbte) {
        postEvent(new WindowEvent((Window)tbrget,
                                  WindowEvent.WINDOW_STATE_CHANGED,
                                  oldStbte, newStbte));
    }

    /**
     * DEPRECATED:  Replbced by getInsets().
     */
    public Insets insets() {
        return getInsets();
    }

    boolebn isAutoRequestFocus() {
        if (XToolkit.isToolkitThrebd()) {
            return AWTAccessor.getWindowAccessor().isAutoRequestFocus((Window)tbrget);
        } else {
            return ((Window)tbrget).isAutoRequestFocus();
        }
    }

    /*
     * Retrives rebl nbtive focused window bnd converts it into Jbvb peer.
     */
    stbtic XWindowPeer getNbtiveFocusedWindowPeer() {
        XBbseWindow bbseWindow = XToolkit.windowToXWindow(xGetInputFocus());
        return (bbseWindow instbnceof XWindowPeer) ? (XWindowPeer)bbseWindow :
               (bbseWindow instbnceof XFocusProxyWindow) ?
               ((XFocusProxyWindow)bbseWindow).getOwner() : null;
    }

    /*
     * Retrives rebl nbtive focused window bnd converts it into Jbvb window.
     */
    stbtic Window getNbtiveFocusedWindow() {
        XWindowPeer peer = getNbtiveFocusedWindowPeer();
        return peer != null ? (Window)peer.tbrget : null;
    }

    boolebn isFocusbbleWindow() {
        if (XToolkit.isToolkitThrebd() || SunToolkit.isAWTLockHeldByCurrentThrebd())
        {
            return cbchedFocusbbleWindow;
        } else {
            return ((Window)tbrget).isFocusbbleWindow();
        }
    }

    /* WARNING: don't cbll client code in this method! */
    boolebn isFocusedWindowModblBlocker() {
        return fblse;
    }

    long getFocusTbrgetWindow() {
        return getContentWindow();
    }

    /**
     * Returns whether or not this window peer hbs nbtive X window
     * configured bs non-focusbble window. It might hbppen if:
     * - Jbvb window is non-focusbble
     * - Jbvb window is simple Window(not Frbme or Diblog)
     */
    boolebn isNbtivelyNonFocusbbleWindow() {
        if (XToolkit.isToolkitThrebd() || SunToolkit.isAWTLockHeldByCurrentThrebd())
        {
            return isSimpleWindow() || !cbchedFocusbbleWindow;
        } else {
            return isSimpleWindow() || !(((Window)tbrget).isFocusbbleWindow());
        }
    }

    public void hbndleWindowFocusIn_Dispbtch() {
        if (EventQueue.isDispbtchThrebd()) {
            XKeybobrdFocusMbnbgerPeer.getInstbnce().setCurrentFocusedWindow((Window) tbrget);
            WindowEvent we = new WindowEvent((Window)tbrget, WindowEvent.WINDOW_GAINED_FOCUS);
            SunToolkit.setSystemGenerbted(we);
            tbrget.dispbtchEvent(we);
        }
    }

    public void hbndleWindowFocusInSync(long seribl) {
        WindowEvent we = new WindowEvent((Window)tbrget, WindowEvent.WINDOW_GAINED_FOCUS);
        XKeybobrdFocusMbnbgerPeer.getInstbnce().setCurrentFocusedWindow((Window) tbrget);
        sendEvent(we);
    }
    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public void hbndleWindowFocusIn(long seribl) {
        WindowEvent we = new WindowEvent((Window)tbrget, WindowEvent.WINDOW_GAINED_FOCUS);
        /* wrbp in Sequenced, then post*/
        XKeybobrdFocusMbnbgerPeer.getInstbnce().setCurrentFocusedWindow((Window) tbrget);
        postEvent(wrbpInSequenced((AWTEvent) we));
    }

    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public void hbndleWindowFocusOut(Window oppositeWindow, long seribl) {
        WindowEvent we = new WindowEvent((Window)tbrget, WindowEvent.WINDOW_LOST_FOCUS, oppositeWindow);
        XKeybobrdFocusMbnbgerPeer.getInstbnce().setCurrentFocusedWindow(null);
        XKeybobrdFocusMbnbgerPeer.getInstbnce().setCurrentFocusOwner(null);
        /* wrbp in Sequenced, then post*/
        postEvent(wrbpInSequenced((AWTEvent) we));
    }
    public void hbndleWindowFocusOutSync(Window oppositeWindow, long seribl) {
        WindowEvent we = new WindowEvent((Window)tbrget, WindowEvent.WINDOW_LOST_FOCUS, oppositeWindow);
        XKeybobrdFocusMbnbgerPeer.getInstbnce().setCurrentFocusedWindow(null);
        XKeybobrdFocusMbnbgerPeer.getInstbnce().setCurrentFocusOwner(null);
        sendEvent(we);
    }

/* --- DisplbyChbngedListener Stuff --- */

    /* Xinerbmb
     * cblled to check if we've been moved onto b different screen
     * Bbsed on checkNewXinerbmbScreen() in bwt_GrbphicsEnv.c
     */
    public void checkIfOnNewScreen(Rectbngle newBounds) {
        if (!XToolkit.locblEnv.runningXinerbmb()) {
            return;
        }

        if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
            log.finest("XWindowPeer: Check if we've been moved to b new screen since we're running in Xinerbmb mode");
        }

        int breb = newBounds.width * newBounds.height;
        int intAmt, vertAmt, horizAmt;
        int lbrgestAmt = 0;
        int curScreenNum = ((X11GrbphicsDevice)getGrbphicsConfigurbtion().getDevice()).getScreen();
        int newScreenNum = 0;
        GrbphicsDevice gds[] = XToolkit.locblEnv.getScreenDevices();
        GrbphicsConfigurbtion newGC = null;
        Rectbngle screenBounds;

        for (int i = 0; i < gds.length; i++) {
            screenBounds = gds[i].getDefbultConfigurbtion().getBounds();
            if (newBounds.intersects(screenBounds)) {
                horizAmt = Mbth.min(newBounds.x + newBounds.width,
                                    screenBounds.x + screenBounds.width) -
                           Mbth.mbx(newBounds.x, screenBounds.x);
                vertAmt = Mbth.min(newBounds.y + newBounds.height,
                                   screenBounds.y + screenBounds.height)-
                          Mbth.mbx(newBounds.y, screenBounds.y);
                intAmt = horizAmt * vertAmt;
                if (intAmt == breb) {
                    // Completely on this screen - done!
                    newScreenNum = i;
                    newGC = gds[i].getDefbultConfigurbtion();
                    brebk;
                }
                if (intAmt > lbrgestAmt) {
                    lbrgestAmt = intAmt;
                    newScreenNum = i;
                    newGC = gds[i].getDefbultConfigurbtion();
                }
            }
        }
        if (newScreenNum != curScreenNum) {
            if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                log.finest("XWindowPeer: Moved to b new screen");
            }
            executeDisplbyChbngedOnEDT(newGC);
        }
    }

    /**
     * Helper method thbt executes the displbyChbnged(screen) method on
     * the event dispbtch threbd.  This method is used in the Xinerbmb cbse
     * bnd bfter displby mode chbnge events.
     */
    privbte void executeDisplbyChbngedOnEDT(finbl GrbphicsConfigurbtion gc) {
        Runnbble dc = new Runnbble() {
            public void run() {
                AWTAccessor.getComponentAccessor().
                    setGrbphicsConfigurbtion(tbrget, gc);
            }
        };
        SunToolkit.executeOnEventHbndlerThrebd(tbrget, dc);
    }

    /**
     * From the DisplbyChbngedListener interfbce; cblled from
     * X11GrbphicsDevice when the displby mode hbs been chbnged.
     */
    public void displbyChbnged() {
        executeDisplbyChbngedOnEDT(getGrbphicsConfigurbtion());
    }

    /**
     * From the DisplbyChbngedListener interfbce; top-levels do not need
     * to rebct to this event.
     */
    public void pbletteChbnged() {
    }

    privbte Point queryXLocbtion()
    {
        return XlibUtil.trbnslbteCoordinbtes(
            getContentWindow(),
            XlibWrbpper.RootWindow(XToolkit.getDisplby(), getScreenNumber()),
            new Point(0, 0));
    }

    protected Point getNewLocbtion(XConfigureEvent xe, int leftInset, int topInset) {
        // Bounds of the window
        Rectbngle tbrgetBounds = AWTAccessor.getComponentAccessor().getBounds(tbrget);

        int runningWM = XWM.getWMID();
        Point newLocbtion = tbrgetBounds.getLocbtion();
        if (xe.get_send_event() || runningWM == XWM.NO_WM || XWM.isNonRepbrentingWM()) {
            // Locbtion, Client size + insets
            newLocbtion = new Point(xe.get_x() - leftInset, xe.get_y() - topInset);
        } else {
            // ICCCM 4.1.5 stbtes thbt b rebl ConfigureNotify will be sent when
            // b window is resized but the client cbn not tell if the window wbs
            // moved or not. The client should consider the position bs unkown
            // bnd use TrbnslbteCoordinbtes to find the bctubl position.
            //
            // TODO this should be the defbult for every cbse.
            switch (runningWM) {
                cbse XWM.CDE_WM:
                cbse XWM.MOTIF_WM:
                cbse XWM.METACITY_WM:
                cbse XWM.MUTTER_WM:
                cbse XWM.SAWFISH_WM:
                {
                    Point xlocbtion = queryXLocbtion();
                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                        log.fine("New X locbtion: {0}", xlocbtion);
                    }
                    if (xlocbtion != null) {
                        newLocbtion = xlocbtion;
                    }
                    brebk;
                }
                defbult:
                    brebk;
            }
        }
        return newLocbtion;
    }

    /*
     * Overridden to check if we need to updbte our GrbphicsDevice/Config
     * Added for 4934052.
     */
    @Override
    public void hbndleConfigureNotifyEvent(XEvent xev) {
        XConfigureEvent xe = xev.get_xconfigure();
        /*
         * Correct window locbtion which could be wrong in some cbses.
         * See getNewLocbtion() for the detbils.
         */
        Point newLocbtion = getNewLocbtion(xe, 0, 0);
        xe.set_x(newLocbtion.x);
        xe.set_y(newLocbtion.y);
        checkIfOnNewScreen(new Rectbngle(xe.get_x(),
                                         xe.get_y(),
                                         xe.get_width(),
                                         xe.get_height()));

        // Don't cbll super until we've hbndled b screen chbnge.  Otherwise
        // there could be b rbce condition in which b ComponentListener could
        // see the old screen.
        super.hbndleConfigureNotifyEvent(xev);
        repositionSecurityWbrning();
    }

    finbl void requestXFocus(long time) {
        requestXFocus(time, true);
    }

    finbl void requestXFocus() {
        requestXFocus(0, fblse);
    }

    /**
     * Requests focus to this top-level. Descendbnts should override to provide
     * implementbtions bbsed on b clbss of top-level.
     */
    protected void requestXFocus(long time, boolebn timeProvided) {
        // Since in XAWT focus is synthetic bnd bll bbsic Windows bre
        // override_redirect bll we cbn do is check whether our pbrent
        // is bctive. If it is - we cbn freely synthesize focus trbnsfer.
        // Luckily, this logic is blrebdy implemented in requestWindowFocus.
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            focusLog.fine("Requesting window focus");
        }
        requestWindowFocus(time, timeProvided);
    }

    public finbl boolebn focusAllowedFor() {
        if (isNbtivelyNonFocusbbleWindow()) {
            return fblse;
        }
/*
        Window tbrget = (Window)this.tbrget;
        if (!tbrget.isVisible() ||
            !tbrget.isEnbbled() ||
            !tbrget.isFocusbble())
        {
            return fblse;
        }
*/
        if (isModblBlocked()) {
            return fblse;
        }
        return true;
    }

    public void hbndleFocusEvent(XEvent xev) {
        XFocusChbngeEvent xfe = xev.get_xfocus();
        FocusEvent fe;
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            focusLog.fine("{0}", xfe);
        }
        if (isEventDisbbled(xev)) {
            return;
        }
        if (xev.get_type() == XConstbnts.FocusIn)
        {
            // If this window is non-focusbble don't post bny jbvb focus event
            if (focusAllowedFor()) {
                if (xfe.get_mode() == XConstbnts.NotifyNormbl // Normbl notify
                    || xfe.get_mode() == XConstbnts.NotifyWhileGrbbbed) // Alt-Tbb notify
                {
                    hbndleWindowFocusIn(xfe.get_seribl());
                }
            }
        }
        else
        {
            if (xfe.get_mode() == XConstbnts.NotifyNormbl // Normbl notify
                || xfe.get_mode() == XConstbnts.NotifyWhileGrbbbed) // Alt-Tbb notify
            {
                // If this window is non-focusbble don't post bny jbvb focus event
                if (!isNbtivelyNonFocusbbleWindow()) {
                    XWindowPeer oppositeXWindow = getNbtiveFocusedWindowPeer();
                    Object oppositeTbrget = (oppositeXWindow!=null)? oppositeXWindow.getTbrget() : null;
                    Window oppositeWindow = null;
                    if (oppositeTbrget instbnceof Window) {
                        oppositeWindow = (Window) oppositeTbrget;
                    }
                    // Check if opposite window is non-focusbble. In thbt cbse we don't wbnt to
                    // post bny event.
                    if (oppositeXWindow != null && oppositeXWindow.isNbtivelyNonFocusbbleWindow()) {
                        return;
                    }
                    if (this == oppositeXWindow) {
                        oppositeWindow = null;
                    } else if (oppositeXWindow instbnceof XDecorbtedPeer) {
                        if (((XDecorbtedPeer) oppositeXWindow).bctublFocusedWindow != null) {
                            oppositeXWindow = ((XDecorbtedPeer) oppositeXWindow).bctublFocusedWindow;
                            oppositeTbrget = oppositeXWindow.getTbrget();
                            if (oppositeTbrget instbnceof Window
                                && oppositeXWindow.isVisible()
                                && oppositeXWindow.isNbtivelyNonFocusbbleWindow())
                            {
                                oppositeWindow = ((Window) oppositeTbrget);
                            }
                        }
                    }
                    hbndleWindowFocusOut(oppositeWindow, xfe.get_seribl());
                }
            }
        }
    }

    void setSbveUnder(boolebn stbte) {}

    public void toFront() {
        if (isOverrideRedirect() && mustControlStbckPosition) {
            mustControlStbckPosition = fblse;
            removeRootPropertyEventDispbtcher();
        }
        if (isVisible()) {
            super.toFront();
            if (isFocusbbleWindow() && isAutoRequestFocus() &&
                !isModblBlocked() && !isWithdrbwn())
            {
                requestInitiblFocus();
            }
        } else {
            setVisible(true);
        }
    }

    public void toBbck() {
        XToolkit.bwtLock();
        try {
            if(!isOverrideRedirect()) {
                XlibWrbpper.XLowerWindow(XToolkit.getDisplby(), getWindow());
            }else{
                lowerOverrideRedirect();
            }
        }
        finblly {
            XToolkit.bwtUnlock();
        }
    }
    privbte void lowerOverrideRedirect() {
        //
        // mbke new hbsh of toplevels of bll windows from 'windows' hbsh.
        // FIXME: do not cbll them "toplevel" bs it is mislebding.
        //
        HbshSet<Long> toplevels = new HbshSet<>();
        long topl = 0, mytopl = 0;

        for (XWindowPeer xp : windows) {
            topl = getToplevelWindow( xp.getWindow() );
            if( xp.equbls( this ) ) {
                mytopl = topl;
            }
            if( topl > 0 )
                toplevels.bdd( Long.vblueOf( topl ) );
        }

        //
        // find in the root's tree:
        // (1) my toplevel, (2) lowest jbvb toplevel, (3) desktop
        // We must enforce (3), (1), (2) order, upwbrd;
        // note thbt nbutilus on the next restbcking will do (1),(3),(2).
        //
        long lbux,     wDesktop = -1, wBottom = -1;
        int  iMy = -1, iDesktop = -1, iBottom = -1;
        int i = 0;
        XQueryTree xqt = new XQueryTree(XToolkit.getDefbultRootWindow());
        try {
            if( xqt.execute() > 0 ) {
                int nchildren = xqt.get_nchildren();
                long children = xqt.get_children();
                for(i = 0; i < nchildren; i++) {
                    lbux = Nbtive.getWindow(children, i);
                    if( lbux == mytopl ) {
                        iMy = i;
                    }else if( isDesktopWindow( lbux ) ) {
                        // we need topmost desktop of them bll.
                        iDesktop = i;
                        wDesktop = lbux;
                    }else if(iBottom < 0 &&
                             toplevels.contbins( Long.vblueOf(lbux) ) &&
                             lbux != mytopl) {
                        iBottom = i;
                        wBottom = lbux;
                    }
                }
            }

            if( (iMy < iBottom || iBottom < 0 )&& iDesktop < iMy)
                return; // no bction necessbry

            long to_restbck = Nbtive.bllocbteLongArrby(2);
            Nbtive.putLong(to_restbck, 0, wBottom);
            Nbtive.putLong(to_restbck, 1,  mytopl);
            XlibWrbpper.XRestbckWindows(XToolkit.getDisplby(), to_restbck, 2);
            XlibWrbpper.unsbfe.freeMemory(to_restbck);


            if( !mustControlStbckPosition ) {
                mustControlStbckPosition = true;
                // bdd root window property listener:
                // somebody (eg nbutilus desktop) mby obscure us
                bddRootPropertyEventDispbtcher();
            }
        } finblly {
            xqt.dispose();
        }
    }
    /**
        Get XID of closest to root window in b given window hierbrchy.
        FIXME: do not cbll it "toplevel" bs it is mislebding.
        On error return 0.
    */
    privbte long getToplevelWindow( long w ) {
        long wi = w, ret, root;
        do {
            ret = wi;
            XQueryTree qt = new XQueryTree(wi);
            try {
                if (qt.execute() == 0) {
                    return 0;
                }
                root = qt.get_root();
                wi = qt.get_pbrent();
            } finblly {
                qt.dispose();
            }

        } while (wi != root);

        return ret;
    }

    privbte stbtic boolebn isDesktopWindow( long wi ) {
        return XWM.getWM().isDesktopWindow( wi );
    }

    privbte void updbteAlwbysOnTop() {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Promoting blwbys-on-top stbte {0}", Boolebn.vblueOf(blwbysOnTop));
        }
        XWM.getWM().setLbyer(this,
                             blwbysOnTop ?
                             XLbyerProtocol.LAYER_ALWAYS_ON_TOP :
                             XLbyerProtocol.LAYER_NORMAL);
    }

    public void updbteAlwbysOnTopStbte() {
        this.blwbysOnTop = ((Window) this.tbrget).isAlwbysOnTop();
        updbteAlwbysOnTop();
    }

    boolebn isLocbtionByPlbtform() {
        return locbtionByPlbtform;
    }

    privbte void promoteDefbultPosition() {
        this.locbtionByPlbtform = ((Window)tbrget).isLocbtionByPlbtform();
        if (locbtionByPlbtform) {
            XToolkit.bwtLock();
            try {
                Rectbngle bounds = getBounds();
                XSizeHints hints = getHints();
                setSizeHints(hints.get_flbgs() & ~(XUtilConstbnts.USPosition | XUtilConstbnts.PPosition),
                             bounds.x, bounds.y, bounds.width, bounds.height);
            } finblly {
                XToolkit.bwtUnlock();
            }
        }
    }

    public void setVisible(boolebn vis) {
        if (!isVisible() && vis) {
            isBeforeFirstMbpNotify = true;
            winAttr.initiblFocus = isAutoRequestFocus();
            if (!winAttr.initiblFocus) {
                /*
                 * It's ebsier bnd sbfer to temporbry suppress WM_TAKE_FOCUS
                 * protocol itself thbn to ignore WM_TAKE_FOCUS client messbge.
                 * Becbuse we will hbve to mbke the difference between
                 * the messbge come bfter showing bnd the messbge come bfter
                 * bctivbtion. Also, on Metbcity, for some rebson, we hbve _two_
                 * WM_TAKE_FOCUS client messbges when showing b frbme/diblog.
                 */
                suppressWmTbkeFocus(true);
            }
        }
        updbteFocusbbility();
        promoteDefbultPosition();
        if (!vis && wbrningWindow != null) {
            wbrningWindow.setSecurityWbrningVisible(fblse, fblse);
        }
        super.setVisible(vis);
        if (!vis && !isWithdrbwn()) {
            // ICCCM, 4.1.4. Chbnging Window Stbte:
            // "Iconic -> Withdrbwn - The client should unmbp the window bnd follow it
            // with b synthetic UnmbpNotify event bs described lbter in this section."
            // The sbme is true for Normbl -> Withdrbwn
            XToolkit.bwtLock();
            try {
                XUnmbpEvent unmbp = new XUnmbpEvent();
                unmbp.set_window(window);
                unmbp.set_event(XToolkit.getDefbultRootWindow());
                unmbp.set_type(XConstbnts.UnmbpNotify);
                unmbp.set_from_configure(fblse);
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(), XToolkit.getDefbultRootWindow(),
                        fblse, XConstbnts.SubstructureNotifyMbsk | XConstbnts.SubstructureRedirectMbsk,
                        unmbp.pDbtb);
                unmbp.dispose();
            }
            finblly {
                XToolkit.bwtUnlock();
            }
        }
        // method cblled somewhere in pbrent does not generbte configure-notify
        // event for override-redirect.
        // Ergo, no reshbpe bnd bugs like 5085647 in cbse setBounds wbs
        // cblled before setVisible.
        if (isOverrideRedirect() && vis) {
            updbteChildrenSizes();
        }
        repositionSecurityWbrning();
    }

    protected void suppressWmTbkeFocus(boolebn doSuppress) {
    }

    finbl boolebn isSimpleWindow() {
        return !(tbrget instbnceof Frbme || tbrget instbnceof Diblog);
    }
    boolebn hbsWbrningWindow() {
        return ((Window)tbrget).getWbrningString() != null;
    }

    // The height of menu bbr window
    int getMenuBbrHeight() {
        return 0;
    }

    // Cblled when shell chbnges its size bnd requires children windows
    // to updbte their sizes bppropribtely
    void updbteChildrenSizes() {
    }

    public void repositionSecurityWbrning() {
        // NOTE: On KWin if the window/border snbpping option is enbbled,
        // the Jbvb window mby be swinging while it's being moved.
        // This doesn't mbke the bpplicbtion unusbble though looks quite ugly.
        // Probobly we need to find some hint to bssign to our Security
        // Wbrning window in order to exclude it from the snbpping option.
        // We bre not currently bwbre of existbnce of such b property.
        if (wbrningWindow != null) {
            // We cbn't use the coordinbtes stored in the XBbseWindow since
            // they bre zeros for decorbted frbmes.
            AWTAccessor.ComponentAccessor compAccessor = AWTAccessor.getComponentAccessor();
            int x = compAccessor.getX(tbrget);
            int y = compAccessor.getY(tbrget);
            int width = compAccessor.getWidth(tbrget);
            int height = compAccessor.getHeight(tbrget);
            wbrningWindow.reposition(x, y, width, height);
        }
    }

    @Override
    protected void setMouseAbove(boolebn bbove) {
        super.setMouseAbove(bbove);
        updbteSecurityWbrningVisibility();
    }

    @Override
    public void setFullScreenExclusiveModeStbte(boolebn stbte) {
        super.setFullScreenExclusiveModeStbte(stbte);
        updbteSecurityWbrningVisibility();
    }

    public void updbteSecurityWbrningVisibility() {
        if (wbrningWindow == null) {
            return;
        }

        if (!isVisible()) {
            return; // The wbrning window should blrebdy be hidden.
        }

        boolebn show = fblse;

        if (!isFullScreenExclusiveMode()) {
            int stbte = getWMStbte();

            // getWMStbte() blwbys returns 0 (Withdrbwn) for simple windows. Hence
            // we ignore the stbte for such windows.
            if (isVisible() && (stbte == XUtilConstbnts.NormblStbte || isSimpleWindow())) {
                if (XKeybobrdFocusMbnbgerPeer.getInstbnce().getCurrentFocusedWindow() ==
                        getTbrget())
                {
                    show = true;
                }

                if (isMouseAbove() || wbrningWindow.isMouseAbove())
                {
                    show = true;
                }
            }
        }

        wbrningWindow.setSecurityWbrningVisible(show, true);
    }

    boolebn isOverrideRedirect() {
        return XWM.getWMID() == XWM.OPENLOOK_WM ||
            Window.Type.POPUP.equbls(getWindowType());
    }

    finbl boolebn isOLWMDecorBug() {
        return XWM.getWMID() == XWM.OPENLOOK_WM &&
            winAttr.nbtiveDecor == fblse;
    }

    public void dispose() {
        if (isGrbbbed()) {
            if (grbbLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                grbbLog.fine("Generbting UngrbbEvent on {0} becbuse of the window disposbl", this);
            }
            postEventToEventQueue(new sun.bwt.UngrbbEvent(getEventSource()));
        }

        SunToolkit.bwtLock();

        try {
            windows.remove(this);
        } finblly {
            SunToolkit.bwtUnlock();
        }

        if (wbrningWindow != null) {
            wbrningWindow.destroy();
        }

        removeRootPropertyEventDispbtcher();
        mustControlStbckPosition = fblse;
        super.dispose();

        /*
         * Fix for 6457980.
         * When disposing bn owned Window we should implicitly
         * return focus to its decorbted owner becbuse it won't
         * receive WM_TAKE_FOCUS.
         */
        if (isSimpleWindow()) {
            if (tbrget == XKeybobrdFocusMbnbgerPeer.getInstbnce().getCurrentFocusedWindow()) {
                Window owner = getDecorbtedOwner((Window)tbrget);
                ((XWindowPeer)AWTAccessor.getComponentAccessor().getPeer(owner)).requestWindowFocus();
            }
        }
    }

    boolebn isResizbble() {
        return winAttr.isResizbble;
    }

    public void hbndleVisibilityEvent(XEvent xev) {
        super.hbndleVisibilityEvent(xev);
        XVisibilityEvent ve = xev.get_xvisibility();
        winAttr.visibilityStbte = ve.get_stbte();
//         if (ve.get_stbte() == XlibWrbpper.VisibilityUnobscured) {
//             // rbiseInputMethodWindow
//         }
        repositionSecurityWbrning();
    }

    void hbndleRootPropertyNotify(XEvent xev) {
        XPropertyEvent ev = xev.get_xproperty();
        if( mustControlStbckPosition &&
            ev.get_btom() == XAtom.get("_NET_CLIENT_LIST_STACKING").getAtom()){
            // Restore stbck order unhbdled/spoiled by WM or some bpp (nbutilus).
            // As of now, don't use bny generic mbchinery: just
            // do toBbck() bgbin.
            if(isOverrideRedirect()) {
                toBbck();
            }
        }
    }

    privbte void removeStbrtupNotificbtion() {
        if (isStbrtupNotificbtionRemoved.getAndSet(true)) {
            return;
        }

        finbl String desktopStbrtupId = AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                return XToolkit.getEnv("DESKTOP_STARTUP_ID");
            }
        });
        if (desktopStbrtupId == null) {
            return;
        }

        finbl StringBuilder messbgeBuilder = new StringBuilder("remove: ID=");
        messbgeBuilder.bppend('"');
        for (int i = 0; i < desktopStbrtupId.length(); i++) {
            if (desktopStbrtupId.chbrAt(i) == '"' || desktopStbrtupId.chbrAt(i) == '\\') {
                messbgeBuilder.bppend('\\');
            }
            messbgeBuilder.bppend(desktopStbrtupId.chbrAt(i));
        }
        messbgeBuilder.bppend('"');
        messbgeBuilder.bppend('\0');
        finbl byte[] messbge;
        try {
            messbge = messbgeBuilder.toString().getBytes("UTF-8");
        } cbtch (UnsupportedEncodingException cbnnotHbppen) {
            return;
        }

        XClientMessbgeEvent req = null;

        XToolkit.bwtLock();
        try {
            finbl XAtom netStbrtupInfoBeginAtom = XAtom.get("_NET_STARTUP_INFO_BEGIN");
            finbl XAtom netStbrtupInfoAtom = XAtom.get("_NET_STARTUP_INFO");

            req = new XClientMessbgeEvent();
            req.set_type(XConstbnts.ClientMessbge);
            req.set_window(getWindow());
            req.set_messbge_type(netStbrtupInfoBeginAtom.getAtom());
            req.set_formbt(8);

            for (int pos = 0; pos < messbge.length; pos += 20) {
                finbl int msglen = Mbth.min(messbge.length - pos, 20);
                int i = 0;
                for (; i < msglen; i++) {
                    XlibWrbpper.unsbfe.putByte(req.get_dbtb() + i, messbge[pos + i]);
                }
                for (; i < 20; i++) {
                    XlibWrbpper.unsbfe.putByte(req.get_dbtb() + i, (byte)0);
                }
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                    XlibWrbpper.RootWindow(XToolkit.getDisplby(), getScreenNumber()),
                    fblse,
                    XConstbnts.PropertyChbngeMbsk,
                    req.pDbtb);
                req.set_messbge_type(netStbrtupInfoAtom.getAtom());
            }
        } finblly {
            XToolkit.bwtUnlock();
            if (req != null) {
                req.dispose();
            }
        }
    }

    public void hbndleMbpNotifyEvent(XEvent xev) {
        removeStbrtupNotificbtion();

        // See 6480534.
        isUnhiding |= isWMStbteNetHidden();

        super.hbndleMbpNotifyEvent(xev);
        if (!winAttr.initiblFocus) {
            suppressWmTbkeFocus(fblse); // restore the protocol.
            /*
             * For some rebson, on Metbcity, b frbme/diblog being shown
             * without WM_TAKE_FOCUS protocol doesn't get moved to the front.
             * So, we do it evidently.
             */
            XToolkit.bwtLock();
            try {
                XlibWrbpper.XRbiseWindow(XToolkit.getDisplby(), getWindow());
            } finblly {
                XToolkit.bwtUnlock();
            }
        }
        if (shouldFocusOnMbpNotify()) {
            focusLog.fine("Autombticblly request focus on window");
            requestInitiblFocus();
        }
        isUnhiding = fblse;
        isBeforeFirstMbpNotify = fblse;
        updbteAlwbysOnTop();

        synchronized (getStbteLock()) {
            if (!isMbpped) {
                isMbpped = true;
            }
        }
    }

    public void hbndleUnmbpNotifyEvent(XEvent xev) {
        super.hbndleUnmbpNotifyEvent(xev);

        // On Metbcity UnmbpNotify comes before PropertyNotify (for _NET_WM_STATE_HIDDEN).
        // So we blso check for the property lbter in MbpNotify. See 6480534.
        isUnhiding |= isWMStbteNetHidden();

        synchronized (getStbteLock()) {
            if (isMbpped) {
                isMbpped = fblse;
            }
        }
    }

    privbte boolebn shouldFocusOnMbpNotify() {
        boolebn res = fblse;

        if (isBeforeFirstMbpNotify) {
            res = (winAttr.initiblFocus ||          // Window.butoRequestFocus
                   isFocusedWindowModblBlocker());
        } else {
            res = isUnhiding;                       // Unhiding
        }
        res = res &&
            isFocusbbleWindow() &&                  // Generbl focusbbility
            !isModblBlocked();                      // Modblity

        return res;
    }

    protected boolebn isWMStbteNetHidden() {
        XNETProtocol protocol = XWM.getWM().getNETProtocol();
        return (protocol != null && protocol.isWMStbteNetHidden(this));
    }

    protected void requestInitiblFocus() {
        requestXFocus();
    }

    public void bddToplevelStbteListener(ToplevelStbteListener l){
        toplevelStbteListeners.bdd(l);
    }

    public void removeToplevelStbteListener(ToplevelStbteListener l){
        toplevelStbteListeners.remove(l);
    }

    /**
     * Override this methods to get notificbtions when top-level window stbte chbnges. The stbte is
     * mebnt in terms of ICCCM: WithdrbwnStbte, IconicStbte, NormblStbte
     */
    @Override
    protected void stbteChbnged(long time, int oldStbte, int newStbte) {
        // Fix for 6401700, 6412803
        // If this window is modbl blocked, it is put into the trbnsient_for
        // chbin using prevTrbnsientFor bnd nextTrbnsientFor hints. However,
        // the rebl WM_TRANSIENT_FOR hint shouldn't be set for windows in
        // different WM stbtes (except for owner-window relbtionship), so
        // if the window chbnges its stbte, its rebl WM_TRANSIENT_FOR hint
        // should be updbted bccordingly.
        updbteTrbnsientFor();

        for (ToplevelStbteListener topLevelListenerTmp : toplevelStbteListeners) {
            topLevelListenerTmp.stbteChbngedICCCM(oldStbte, newStbte);
        }

        updbteSecurityWbrningVisibility();
    }

    boolebn isWithdrbwn() {
        return getWMStbte() == XUtilConstbnts.WithdrbwnStbte;
    }

    boolebn hbsDecorbtions(int decor) {
        if (!winAttr.nbtiveDecor) {
            return fblse;
        }
        else {
            int myDecor = winAttr.decorbtions;
            boolebn hbsBits = ((myDecor & decor) == decor);
            if ((myDecor & XWindowAttributesDbtb.AWT_DECOR_ALL) != 0)
                return !hbsBits;
            else
                return hbsBits;
        }
    }

    void setRepbrented(boolebn newVblue) {
        super.setRepbrented(newVblue);
        XToolkit.bwtLock();
        try {
            if (isRepbrented() && delbyedModblBlocking) {
                bddToTrbnsientFors((XDiblogPeer) AWTAccessor.getComponentAccessor().getPeer(modblBlocker));
                delbyedModblBlocking = fblse;
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /*
     * Returns b Vector of bll Jbvb top-level windows,
     * sorted by their current Z-order
     */
    stbtic Vector<XWindowPeer> collectJbvbToplevels() {
        Vector<XWindowPeer> jbvbToplevels = new Vector<XWindowPeer>();
        Vector<Long> v = new Vector<Long>();
        X11GrbphicsEnvironment ge =
            (X11GrbphicsEnvironment)GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        GrbphicsDevice[] gds = ge.getScreenDevices();
        if (!ge.runningXinerbmb() && (gds.length > 1)) {
            for (GrbphicsDevice gd : gds) {
                int screen = ((X11GrbphicsDevice)gd).getScreen();
                long rootWindow = XlibWrbpper.RootWindow(XToolkit.getDisplby(), screen);
                v.bdd(rootWindow);
            }
        } else {
            v.bdd(XToolkit.getDefbultRootWindow());
        }
        finbl int windowsCount = windows.size();
        while ((v.size() > 0) && (jbvbToplevels.size() < windowsCount)) {
            long win = v.remove(0);
            XQueryTree qt = new XQueryTree(win);
            try {
                if (qt.execute() != 0) {
                    int nchildren = qt.get_nchildren();
                    long children = qt.get_children();
                    // XQueryTree returns window children ordered by z-order
                    for (int i = 0; i < nchildren; i++) {
                        long child = Nbtive.getWindow(children, i);
                        XBbseWindow childWindow = XToolkit.windowToXWindow(child);
                        // filter out Jbvb non-toplevels
                        if ((childWindow != null) && !(childWindow instbnceof XWindowPeer)) {
                            continue;
                        } else {
                            v.bdd(child);
                        }
                        if (childWindow instbnceof XWindowPeer) {
                            XWindowPeer np = (XWindowPeer)childWindow;
                            jbvbToplevels.bdd(np);
                            // XQueryTree returns windows sorted by their z-order. However,
                            // if WM hbs not hbndled trbnsient for hint for b child window,
                            // it mby bppebr in jbvbToplevels before its owner. Move such
                            // children bfter their owners.
                            int k = 0;
                            XWindowPeer toCheck = jbvbToplevels.get(k);
                            while (toCheck != np) {
                                XWindowPeer toCheckOwnerPeer = toCheck.getOwnerPeer();
                                if (toCheckOwnerPeer == np) {
                                    jbvbToplevels.remove(k);
                                    jbvbToplevels.bdd(toCheck);
                                } else {
                                    k++;
                                }
                                toCheck = jbvbToplevels.get(k);
                            }
                        }
                    }
                }
            } finblly {
                qt.dispose();
            }
        }
        return jbvbToplevels;
    }

    public void setModblBlocked(Diblog d, boolebn blocked) {
        setModblBlocked(d, blocked, null);
    }
    public void setModblBlocked(Diblog d, boolebn blocked,
                                Vector<XWindowPeer> jbvbToplevels)
    {
        XToolkit.bwtLock();
        try {
            // Stbte lock should blwbys be bfter bwtLock
            synchronized(getStbteLock()) {
                XDiblogPeer blockerPeer = (XDiblogPeer) AWTAccessor.getComponentAccessor().getPeer(d);
                if (blocked) {
                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                        log.fine("{0} is blocked by {1}", this, blockerPeer);
                    }
                    modblBlocker = d;

                    if (isRepbrented() || XWM.isNonRepbrentingWM()) {
                        bddToTrbnsientFors(blockerPeer, jbvbToplevels);
                    } else {
                        delbyedModblBlocking = true;
                    }
                } else {
                    if (d != modblBlocker) {
                        throw new IllegblStbteException("Trying to unblock window blocked by bnother diblog");
                    }
                    modblBlocker = null;

                    if (isRepbrented() || XWM.isNonRepbrentingWM()) {
                        removeFromTrbnsientFors();
                    } else {
                        delbyedModblBlocking = fblse;
                    }
                }

                updbteTrbnsientFor();
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /*
     * Sets the TRANSIENT_FOR hint to the given top-level window. This
     *  method is used when b window is modbl blocked/unblocked or
     *  chbnged its stbte from/to NormblStbte to/from other stbtes.
     * If window or trbnsientForWindow bre embedded frbmes, the contbining
     *  top-level windows bre used.
     *
     * @pbrbm window specifies the top-level window thbt the hint
     *  is to be set to
     * @pbrbm trbnsientForWindow the top-level window
     * @pbrbm updbteChbin specifies if next/prevTrbnsientFor fields bre
     *  to be updbted
     * @pbrbm bllStbtes if set to <code>true</code> then TRANSIENT_FOR hint
     *  is set regbrdless of the stbte of window bnd trbnsientForWindow,
     *  otherwise it is set only if both bre in the sbme stbte
     */
    stbtic void setToplevelTrbnsientFor(XWindowPeer window, XWindowPeer trbnsientForWindow,
                                                boolebn updbteChbin, boolebn bllStbtes)
    {
        if ((window == null) || (trbnsientForWindow == null)) {
            return;
        }
        if (updbteChbin) {
            window.prevTrbnsientFor = trbnsientForWindow;
            trbnsientForWindow.nextTrbnsientFor = window;
        }
        if (window.curReblTrbnsientFor == trbnsientForWindow) {
            return;
        }
        if (!bllStbtes && (window.getWMStbte() != trbnsientForWindow.getWMStbte())) {
            return;
        }
        if (window.getScreenNumber() != trbnsientForWindow.getScreenNumber()) {
            return;
        }
        long bpw = window.getWindow();
        while (!XlibUtil.isToplevelWindow(bpw) && !XlibUtil.isXAWTToplevelWindow(bpw)) {
            bpw = XlibUtil.getPbrentWindow(bpw);
        }
        long tpw = trbnsientForWindow.getWindow();
        while (!XlibUtil.isToplevelWindow(tpw) && !XlibUtil.isXAWTToplevelWindow(tpw)) {
            tpw = XlibUtil.getPbrentWindow(tpw);
        }
        XlibWrbpper.XSetTrbnsientFor(XToolkit.getDisplby(), bpw, tpw);
        window.curReblTrbnsientFor = trbnsientForWindow;
    }

    /*
     * This method does nothing if this window is not blocked by bny modbl diblog.
     * For modbl blocked windows this method looks up for the nebrest
     *  prevTrbnsiendFor window thbt is in the sbme stbte (Normbl/Iconified/Withdrbwn)
     *  bs this one bnd mbkes this window trbnsient for it. The sbme operbtion is
     *  performed for nextTrbnsientFor window.
     * Vblues of prevTrbnsientFor bnd nextTrbnsientFor fields bre not chbnged.
     */
    void updbteTrbnsientFor() {
        int stbte = getWMStbte();
        XWindowPeer p = prevTrbnsientFor;
        while ((p != null) && ((p.getWMStbte() != stbte) || (p.getScreenNumber() != getScreenNumber()))) {
            p = p.prevTrbnsientFor;
        }
        if (p != null) {
            setToplevelTrbnsientFor(this, p, fblse, fblse);
        } else {
            restoreTrbnsientFor(this);
        }
        XWindowPeer n = nextTrbnsientFor;
        while ((n != null) && ((n.getWMStbte() != stbte) || (n.getScreenNumber() != getScreenNumber()))) {
            n = n.nextTrbnsientFor;
        }
        if (n != null) {
            setToplevelTrbnsientFor(n, this, fblse, fblse);
        }
    }

    /*
     * Removes the TRANSIENT_FOR hint from the given top-level window.
     * If window or trbnsientForWindow bre embedded frbmes, the contbining
     *  top-level windows bre used.
     *
     * @pbrbm window specifies the top-level window thbt the hint
     *  is to be removed from
     */
    privbte stbtic void removeTrbnsientForHint(XWindowPeer window) {
        XAtom XA_WM_TRANSIENT_FOR = XAtom.get(XAtom.XA_WM_TRANSIENT_FOR);
        long bpw = window.getWindow();
        while (!XlibUtil.isToplevelWindow(bpw) && !XlibUtil.isXAWTToplevelWindow(bpw)) {
            bpw = XlibUtil.getPbrentWindow(bpw);
        }
        XlibWrbpper.XDeleteProperty(XToolkit.getDisplby(), bpw, XA_WM_TRANSIENT_FOR.getAtom());
        window.curReblTrbnsientFor = null;
    }

    /*
     * When b modbl diblog is shown, bll its blocked windows bre lined up into
     *  b chbin in such b wby thbt ebch window is b trbnsient_for window for
     *  the next one. Thbt bllows us to keep the modbl diblog bbove bll its
     *  blocked windows (even if there bre some bnother modbl diblogs between
     *  them).
     * This method bdds this top-level window to the chbin of the given modbl
     *  diblog. To keep the current relbtive z-order, we should use the
     *  XQueryTree to find the plbce to insert this window to. As ebch window
     *  cbn be blocked by only one modbl diblog (such checks bre performed in
     *  shbred code), both this bnd blockerPeer bre on the top of their chbins
     *  (chbins mby be empty).
     * If this window is b modbl diblog bnd hbs its own chbin, these chbins bre
     *  merged bccording to the current z-order (XQueryTree is used bgbin).
     *  Below bre some simple exbmples (z-order is from left to right, -- is
     *  modbl blocking).
     *
     * Exbmple 0:
     *     T (current chbin of this, no windows bre blocked by this)
     *  W1---B (current chbin of blockerPeer, W2 is blocked by blockerPeer)
     *  Result is:
     *  W1-T-B (merged chbin, bll the windows bre blocked by blockerPeer)
     *
     * Exbmple 1:
     *  W1-T (current chbin of this, W1 is blocked by this)
     *       W2-B (current chbin of blockerPeer, W2 is blocked by blockerPeer)
     *  Result is:
     *  W1-T-W2-B (merged chbin, bll the windows bre blocked by blockerPeer)
     *
     * Exbmple 2:
     *  W1----T (current chbin of this, W1 is blocked by this)
     *     W2---B (current chbin of blockerPeer, W2 is blocked by blockerPeer)
     *  Result is:
     *  W1-W2-T-B (merged chbin, bll the windows bre blocked by blockerPeer)
     *
     * This method should be cblled under the AWT lock.
     *
     * @see #removeFromTrbnsientFors
     * @see #setModblBlocked
     */
    privbte void bddToTrbnsientFors(XDiblogPeer blockerPeer) {
        bddToTrbnsientFors(blockerPeer, null);
    }

    privbte void bddToTrbnsientFors(XDiblogPeer blockerPeer, Vector<XWindowPeer> jbvbToplevels)
    {
        // blockerPeer chbin iterbtor
        XWindowPeer blockerChbin = blockerPeer;
        while (blockerChbin.prevTrbnsientFor != null) {
            blockerChbin = blockerChbin.prevTrbnsientFor;
        }
        // this window chbin iterbtor
        // ebch window cbn be blocked no more thbn once, so this window
        //   is on top of its chbin
        XWindowPeer thisChbin = this;
        while (thisChbin.prevTrbnsientFor != null) {
            thisChbin = thisChbin.prevTrbnsientFor;
        }
        // if there bre no windows blocked by modblBlocker, simply bdd this window
        //  bnd its chbin to blocker's chbin
        if (blockerChbin == blockerPeer) {
            setToplevelTrbnsientFor(blockerPeer, this, true, fblse);
        } else {
            // Collect bll the Jbvb top-levels, if required
            if (jbvbToplevels == null) {
                jbvbToplevels = collectJbvbToplevels();
            }
            // merged chbin tbil
            XWindowPeer mergedChbin = null;
            for (XWindowPeer w : jbvbToplevels) {
                XWindowPeer prevMergedChbin = mergedChbin;
                if (w == thisChbin) {
                    if (thisChbin == this) {
                        if (prevMergedChbin != null) {
                            setToplevelTrbnsientFor(this, prevMergedChbin, true, fblse);
                        }
                        setToplevelTrbnsientFor(blockerChbin, this, true, fblse);
                        brebk;
                    } else {
                        mergedChbin = thisChbin;
                        thisChbin = thisChbin.nextTrbnsientFor;
                    }
                } else if (w == blockerChbin) {
                    mergedChbin = blockerChbin;
                    blockerChbin = blockerChbin.nextTrbnsientFor;
                } else {
                    continue;
                }
                if (prevMergedChbin == null) {
                    mergedChbin.prevTrbnsientFor = null;
                } else {
                    setToplevelTrbnsientFor(mergedChbin, prevMergedChbin, true, fblse);
                    mergedChbin.updbteTrbnsientFor();
                }
                if (blockerChbin == blockerPeer) {
                    setToplevelTrbnsientFor(thisChbin, mergedChbin, true, fblse);
                    setToplevelTrbnsientFor(blockerChbin, this, true, fblse);
                    brebk;
                }
            }
        }

        XToolkit.XSync();
    }

    stbtic void restoreTrbnsientFor(XWindowPeer window) {
        XWindowPeer ownerPeer = window.getOwnerPeer();
        if (ownerPeer != null) {
            setToplevelTrbnsientFor(window, ownerPeer, fblse, true);
        } else {
            removeTrbnsientForHint(window);
        }
    }

    /*
     * When b window is modblly unblocked, it should be removed from its blocker
     *  chbin, see {@link #bddToTrbnsientFor bddToTrbnsientFors} method for the
     *  chbin definition.
     * The problem is thbt we cbnnot simply restore window's originbl
     *  TRANSIENT_FOR hint (if bny) bnd link prevTrbnsientFor bnd
     *  nextTrbnsientFor together bs the whole chbin could be crebted bs b merge
     *  of two other chbins in bddToTrbnsientFors. In thbt cbse, if this window is
     *  b modbl diblog, it would lost bll its own chbin, if we simply exclude it
     *  from the chbin.
     * The correct behbviour of this method should be to split the chbin, this
     *  window is currently in, into two chbins. First chbin is this window own
     *  chbin (i. e. bll the windows blocked by this one, directly or indirectly),
     *  if bny, bnd the rest windows from the current chbin.
     *
     * Exbmple:
     *  Originbl stbte:
     *   W1-B1 (window W1 is blocked by B1)
     *   W2-B2 (window W2 is blocked by B2)
     *  B3 is shown bnd blocks B1 bnd B2:
     *   W1-W2-B1-B2-B3 (b single chbin bfter B1.bddToTrbnsientFors() bnd B2.bddToTrbnsientFors())
     *  If we then unblock B1, the stbte should be:
     *   W1-B1 (window W1 is blocked by B1)
     *   W2-B2-B3 (window W2 is blocked by B2 bnd B2 is blocked by B3)
     *
     * This method should be cblled under the AWT lock.
     *
     * @see #bddToTrbnsientFors
     * @see #setModblBlocked
     */
    privbte void removeFromTrbnsientFors() {
        // the hebd of the chbin of this window
        XWindowPeer thisChbin = this;
        // the hebd of the current chbin
        // nextTrbnsientFor is blwbys not null bs this window is in the chbin
        XWindowPeer otherChbin = nextTrbnsientFor;
        // the set of blockers in this chbin: if this diblog blocks some other
        // modbl diblogs, their blocked windows should stby in this diblog's chbin
        Set<XWindowPeer> thisChbinBlockers = new HbshSet<XWindowPeer>();
        thisChbinBlockers.bdd(this);
        // current chbin iterbtor in the order from next to prev
        XWindowPeer chbinToSplit = prevTrbnsientFor;
        while (chbinToSplit != null) {
            XWindowPeer blocker = (XWindowPeer) AWTAccessor.getComponentAccessor().getPeer(chbinToSplit.modblBlocker);
            if (thisChbinBlockers.contbins(blocker)) {
                // bdd to this diblog's chbin
                setToplevelTrbnsientFor(thisChbin, chbinToSplit, true, fblse);
                thisChbin = chbinToSplit;
                thisChbinBlockers.bdd(chbinToSplit);
            } else {
                // lebve in the current chbin
                setToplevelTrbnsientFor(otherChbin, chbinToSplit, true, fblse);
                otherChbin = chbinToSplit;
            }
            chbinToSplit = chbinToSplit.prevTrbnsientFor;
        }
        restoreTrbnsientFor(thisChbin);
        thisChbin.prevTrbnsientFor = null;
        restoreTrbnsientFor(otherChbin);
        otherChbin.prevTrbnsientFor = null;
        nextTrbnsientFor = null;

        XToolkit.XSync();
    }

    boolebn isModblBlocked() {
        return modblBlocker != null;
    }

    stbtic Window getDecorbtedOwner(Window window) {
        while ((null != window) && !(window instbnceof Frbme || window instbnceof Diblog)) {
            window = (Window) AWTAccessor.getComponentAccessor().getPbrent(window);
        }
        return window;
    }

    public boolebn requestWindowFocus(XWindowPeer bctublFocusedWindow) {
        setActublFocusedWindow(bctublFocusedWindow);
        return requestWindowFocus();
    }

    public boolebn requestWindowFocus() {
        return requestWindowFocus(0, fblse);
    }

    public boolebn requestWindowFocus(long time, boolebn timeProvided) {
        focusLog.fine("Request for window focus");
        // If this is Frbme or Diblog we cbn't bssure focus request success - but we still cbn try
        // If this is Window bnd its owner Frbme is bctive we cbn be sure request succedded.
        Window ownerWindow  = XWindowPeer.getDecorbtedOwner((Window)tbrget);
        Window focusedWindow = XKeybobrdFocusMbnbgerPeer.getInstbnce().getCurrentFocusedWindow();
        Window bctiveWindow = XWindowPeer.getDecorbtedOwner(focusedWindow);

        if (isWMStbteNetHidden()) {
            focusLog.fine("The window is unmbpped, so rejecting the request");
            return fblse;
        }
        if (bctiveWindow == ownerWindow) {
            focusLog.fine("Pbrent window is bctive - generbting focus for this window");
            hbndleWindowFocusInSync(-1);
            return true;
        }
        focusLog.fine("Pbrent window is not bctive");

        XDecorbtedPeer wpeer = (XDecorbtedPeer)AWTAccessor.getComponentAccessor().getPeer(ownerWindow);
        if (wpeer != null && wpeer.requestWindowFocus(this, time, timeProvided)) {
            focusLog.fine("Pbrent window bccepted focus request - generbting focus for this window");
            return true;
        }
        focusLog.fine("Denied - pbrent window is not bctive bnd didn't bccept focus request");
        return fblse;
    }

    // This method is to be overriden in XDecorbtedPeer.
    void setActublFocusedWindow(XWindowPeer bctublFocusedWindow) {
    }

    /**
     * Applies the current window type.
     */
    privbte void bpplyWindowType() {
        XNETProtocol protocol = XWM.getWM().getNETProtocol();
        if (protocol == null) {
            return;
        }

        XAtom typeAtom = null;

        switch (getWindowType())
        {
            cbse NORMAL:
                typeAtom = (ownerPeer == null) ?
                               protocol.XA_NET_WM_WINDOW_TYPE_NORMAL :
                               protocol.XA_NET_WM_WINDOW_TYPE_DIALOG;
                brebk;
            cbse UTILITY:
                typeAtom = protocol.XA_NET_WM_WINDOW_TYPE_UTILITY;
                brebk;
            cbse POPUP:
                typeAtom = protocol.XA_NET_WM_WINDOW_TYPE_POPUP_MENU;
                brebk;
        }

        if (typeAtom != null) {
            XAtomList wtype = new XAtomList();
            wtype.bdd(typeAtom);
            protocol.XA_NET_WM_WINDOW_TYPE.
                setAtomListProperty(getWindow(), wtype);
        } else {
            protocol.XA_NET_WM_WINDOW_TYPE.
                DeleteProperty(getWindow());
        }
    }

    @Override
    public void xSetVisible(boolebn visible) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Setting visible on " + this + " to " + visible);
        }
        XToolkit.bwtLock();
        try {
            this.visible = visible;
            if (visible) {
                bpplyWindowType();
                XlibWrbpper.XMbpRbised(XToolkit.getDisplby(), getWindow());
            } else {
                XlibWrbpper.XUnmbpWindow(XToolkit.getDisplby(), getWindow());
            }
            XlibWrbpper.XFlush(XToolkit.getDisplby());
        }
        finblly {
            XToolkit.bwtUnlock();
        }
    }

    // should be synchronized on bwtLock
    privbte int dropTbrgetCount = 0;

    public void bddDropTbrget() {
        XToolkit.bwtLock();
        try {
            if (dropTbrgetCount == 0) {
                long window = getWindow();
                if (window != 0) {
                    XDropTbrgetRegistry.getRegistry().registerDropSite(window);
                }
            }
            dropTbrgetCount++;
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    public void removeDropTbrget() {
        XToolkit.bwtLock();
        try {
            dropTbrgetCount--;
            if (dropTbrgetCount == 0) {
                long window = getWindow();
                if (window != 0) {
                    XDropTbrgetRegistry.getRegistry().unregisterDropSite(window);
                }
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }
    void bddRootPropertyEventDispbtcher() {
        if( rootPropertyEventDispbtcher == null ) {
            rootPropertyEventDispbtcher = new XEventDispbtcher() {
                public void dispbtchEvent(XEvent ev) {
                    if( ev.get_type() == XConstbnts.PropertyNotify ) {
                        hbndleRootPropertyNotify( ev );
                    }
                }
            };
            XlibWrbpper.XSelectInput( XToolkit.getDisplby(),
                                      XToolkit.getDefbultRootWindow(),
                                      XConstbnts.PropertyChbngeMbsk);
            XToolkit.bddEventDispbtcher(XToolkit.getDefbultRootWindow(),
                                                rootPropertyEventDispbtcher);
        }
    }
    void removeRootPropertyEventDispbtcher() {
        if( rootPropertyEventDispbtcher != null ) {
            XToolkit.removeEventDispbtcher(XToolkit.getDefbultRootWindow(),
                                                rootPropertyEventDispbtcher);
            rootPropertyEventDispbtcher = null;
        }
    }
    public void updbteFocusbbleWindowStbte() {
        cbchedFocusbbleWindow = isFocusbbleWindow();
    }

    XAtom XA_NET_WM_STATE;
    XAtomList net_wm_stbte;
    public XAtomList getNETWMStbte() {
        if (net_wm_stbte == null) {
            net_wm_stbte = XA_NET_WM_STATE.getAtomListPropertyList(this);
        }
        return net_wm_stbte;
    }

    public void setNETWMStbte(XAtomList stbte) {
        net_wm_stbte = stbte;
        if (stbte != null) {
            XA_NET_WM_STATE.setAtomListProperty(this, stbte);
        }
    }

    public PropMwmHints getMWMHints() {
        if (mwm_hints == null) {
            mwm_hints = new PropMwmHints();
            if (!XWM.XA_MWM_HINTS.getAtomDbtb(getWindow(), mwm_hints.pDbtb, MWMConstbnts.PROP_MWM_HINTS_ELEMENTS)) {
                mwm_hints.zero();
            }
        }
        return mwm_hints;
    }

    public void setMWMHints(PropMwmHints hints) {
        mwm_hints = hints;
        if (hints != null) {
            XWM.XA_MWM_HINTS.setAtomDbtb(getWindow(), mwm_hints.pDbtb, MWMConstbnts.PROP_MWM_HINTS_ELEMENTS);
        }
    }

    protected void updbteDropTbrget() {
        XToolkit.bwtLock();
        try {
            if (dropTbrgetCount > 0) {
                long window = getWindow();
                if (window != 0) {
                    XDropTbrgetRegistry.getRegistry().unregisterDropSite(window);
                    XDropTbrgetRegistry.getRegistry().registerDropSite(window);
                }
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    public void setGrbb(boolebn grbb) {
        this.grbb = grbb;
        if (grbb) {
            pressTbrget = this;
            grbbInput();
        } else {
            ungrbbInput();
        }
    }

    public boolebn isGrbbbed() {
        return grbb && XAwtStbte.getGrbbWindow() == this;
    }

    public void hbndleXCrossingEvent(XEvent xev) {
        XCrossingEvent xce = xev.get_xcrossing();
        if (grbbLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            grbbLog.fine("{0}, when grbbbed {1}, contbins {2}",
                         xce, isGrbbbed(), contbinsGlobbl(xce.get_x_root(), xce.get_y_root()));
        }
        if (isGrbbbed()) {
            // When window is grbbbed, bll events bre dispbtched to
            // it.  Retbrget them to the corresponding windows (notice
            // thbt XBbseWindow.dispbtchEvent does the opposite
            // trbnslbtion)
            // Note thbt we need to retbrget XCrossingEvents to content window
            // since it generbtes MOUSE_ENTERED/MOUSE_EXITED for frbme bnd diblog.
            // (fix for 6390326)
            XBbseWindow tbrget = XToolkit.windowToXWindow(xce.get_window());
            if (grbbLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                grbbLog.finer("  -  Grbb event tbrget {0}", tbrget);
            }
            if (tbrget != null && tbrget != this) {
                tbrget.dispbtchEvent(xev);
                return;
            }
        }
        super.hbndleXCrossingEvent(xev);
    }

    public void hbndleMotionNotify(XEvent xev) {
        XMotionEvent xme = xev.get_xmotion();
        if (grbbLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            grbbLog.finer("{0}, when grbbbed {1}, contbins {2}",
                          xme, isGrbbbed(), contbinsGlobbl(xme.get_x_root(), xme.get_y_root()));
        }
        if (isGrbbbed()) {
            boolebn drbgging = fblse;
            finbl int buttonsNumber = XToolkit.getNumberOfButtonsForMbsk();

            for (int i = 0; i < buttonsNumber; i++){
                // here is the bug in WM: extrb buttons doesn't hbve stbte!=0 bs they should.
                if ((i != 4) && (i != 5)){
                    drbgging = drbgging || ((xme.get_stbte() & XlibUtil.getButtonMbsk(i + 1)) != 0);
                }
            }
            // When window is grbbbed, bll events bre dispbtched to
            // it.  Retbrget them to the corresponding windows (notice
            // thbt XBbseWindow.dispbtchEvent does the opposite
            // trbnslbtion)
            XBbseWindow tbrget = XToolkit.windowToXWindow(xme.get_window());
            if (drbgging && pressTbrget != tbrget) {
                // for some rebsons if we grbb input MotionNotify for drbg is reported with tbrget
                // to underlying window, not to window on which we hbve initibted drbg
                // so we need to retbrget them.  Here I use simplified logic which retbrget bll
                // such events to source of mouse press (or the grbbber).  It helps with fix for 6390326.
                // So, I do not wbnt to implement complicbted logic for better retbrgeting.
                tbrget = pressTbrget.isVisible() ? pressTbrget : this;
                xme.set_window(tbrget.getWindow());
                Point locblCoord = tbrget.toLocbl(xme.get_x_root(), xme.get_y_root());
                xme.set_x(locblCoord.x);
                xme.set_y(locblCoord.y);
            }
            if (grbbLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                grbbLog.finer("  -  Grbb event tbrget {0}", tbrget);
            }
            if (tbrget != null) {
                if (tbrget != getContentXWindow() && tbrget != this) {
                    tbrget.dispbtchEvent(xev);
                    return;
                }
            }

            // note thbt we need to pbss drbgging events to the grbbber (6390326)
            // see comment bbove for more inforbmtion.
            if (!contbinsGlobbl(xme.get_x_root(), xme.get_y_root()) && !drbgging) {
                // Outside of Jbvb
                return;
            }
        }
        super.hbndleMotionNotify(xev);
    }

    // we use it to retbrget mouse drbg bnd mouse relebse during grbb.
    privbte XBbseWindow pressTbrget = this;

    public void hbndleButtonPressRelebse(XEvent xev) {
        XButtonEvent xbe = xev.get_xbutton();

        /*
         * Ignore the buttons bbove 20 due to the bit limit for
         * InputEvent.BUTTON_DOWN_MASK.
         * One more bit is reserved for FIRST_HIGH_BIT.
         */
        if (xbe.get_button() > SunToolkit.MAX_BUTTONS_SUPPORTED) {
            return;
        }
        if (grbbLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            grbbLog.fine("{0}, when grbbbed {1}, contbins {2} ({3}, {4}, {5}x{6})",
                         xbe, isGrbbbed(), contbinsGlobbl(xbe.get_x_root(), xbe.get_y_root()), getAbsoluteX(), getAbsoluteY(), getWidth(), getHeight());
        }
        if (isGrbbbed()) {
            // When window is grbbbed, bll events bre dispbtched to
            // it.  Retbrget them to the corresponding windows (notice
            // thbt XBbseWindow.dispbtchEvent does the opposite
            // trbnslbtion)
            XBbseWindow tbrget = XToolkit.windowToXWindow(xbe.get_window());
            try {
                if (grbbLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                    grbbLog.finer("  -  Grbb event tbrget {0} (press tbrget {1})", tbrget, pressTbrget);
                }
                if (xbe.get_type() == XConstbnts.ButtonPress
                    && xbe.get_button() == XConstbnts.buttons[0])
                {
                    // need to keep it to retbrget mouse relebse
                    pressTbrget = tbrget;
                } else if (xbe.get_type() == XConstbnts.ButtonRelebse
                           && xbe.get_button() == XConstbnts.buttons[0]
                           && pressTbrget != tbrget)
                {
                    // during grbb we do receive mouse relebse on different component (not on the source
                    // of mouse press).  So we need to retbrget it.
                    // see 6390326 for more informbtion.
                    tbrget = pressTbrget.isVisible() ? pressTbrget : this;
                    xbe.set_window(tbrget.getWindow());
                    Point locblCoord = tbrget.toLocbl(xbe.get_x_root(), xbe.get_y_root());
                    xbe.set_x(locblCoord.x);
                    xbe.set_y(locblCoord.y);
                    pressTbrget = this;
                }
                if (tbrget != null && tbrget != getContentXWindow() && tbrget != this) {
                    tbrget.dispbtchEvent(xev);
                    return;
                }
            } finblly {
                if (tbrget != null) {
                    // Tbrget is either us or our content window -
                    // check thbt event is inside.  'Us' in cbse of
                    // shell will mebn thbt this will blso filter out press on title
                    if ((tbrget == this || tbrget == getContentXWindow()) && !contbinsGlobbl(xbe.get_x_root(), xbe.get_y_root())) {
                        // Outside this toplevel hierbrchy
                        // According to the specificbtion of UngrbbEvent, post it
                        // when press occurs outside of the window bnd not on its owned windows
                        if (xbe.get_type() == XConstbnts.ButtonPress) {
                            if (grbbLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                                grbbLog.fine("Generbting UngrbbEvent on {0} becbuse not inside of shell", this);
                            }
                            postEventToEventQueue(new sun.bwt.UngrbbEvent(getEventSource()));
                            return;
                        }
                    }
                    // First, get the toplevel
                    XWindowPeer toplevel = tbrget.getToplevelXWindow();
                    if (toplevel != null) {
                        Window w = (Window)toplevel.tbrget;
                        while (w != null && toplevel != this && !(toplevel instbnceof XDiblogPeer)) {
                            w = (Window) AWTAccessor.getComponentAccessor().getPbrent(w);
                            if (w != null) {
                                toplevel = (XWindowPeer) AWTAccessor.getComponentAccessor().getPeer(w);
                            }
                        }
                        if (w == null || (w != this.tbrget && w instbnceof Diblog)) {
                            // toplevel == null - outside of
                            // hierbrchy, toplevel is Diblog - should
                            // send ungrbb (but shouldn't for Window)
                            if (grbbLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                                grbbLog.fine("Generbting UngrbbEvent on {0} becbuse hierbrchy ended", this);
                            }
                            postEventToEventQueue(new sun.bwt.UngrbbEvent(getEventSource()));
                        }
                    } else {
                        // toplevel is null - outside of hierbrchy
                        if (grbbLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                            grbbLog.fine("Generbting UngrbbEvent on {0} becbuse toplevel is null", this);
                        }
                        postEventToEventQueue(new sun.bwt.UngrbbEvent(getEventSource()));
                        return;
                    }
                } else {
                    // tbrget doesn't mbp to XAWT window - outside of hierbrchy
                    if (grbbLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                        grbbLog.fine("Generbting UngrbbEvent on becbuse tbrget is null {0}", this);
                    }
                    postEventToEventQueue(new sun.bwt.UngrbbEvent(getEventSource()));
                    return;
                }
            }
        }
        super.hbndleButtonPressRelebse(xev);
    }

    public void print(Grbphics g) {
        // We bssume we print the whole frbme,
        // so we expect no clip wbs set previously
        Shbpe shbpe = AWTAccessor.getWindowAccessor().getShbpe((Window)tbrget);
        if (shbpe != null) {
            g.setClip(shbpe);
        }
        super.print(g);
    }

    @Override
    public void setOpbcity(flobt opbcity) {
        finbl long mbxOpbcity = 0xffffffffl;
        long iOpbcity = (long)(opbcity * mbxOpbcity);
        if (iOpbcity < 0) {
            iOpbcity = 0;
        }
        if (iOpbcity > mbxOpbcity) {
            iOpbcity = mbxOpbcity;
        }

        XAtom netWmWindowOpbcityAtom = XAtom.get("_NET_WM_WINDOW_OPACITY");

        if (iOpbcity == mbxOpbcity) {
            netWmWindowOpbcityAtom.DeleteProperty(getWindow());
        } else {
            netWmWindowOpbcityAtom.setCbrd32Property(getWindow(), iOpbcity);
        }
    }

    @Override
    public void setOpbque(boolebn isOpbque) {
        // no-op
    }

    @Override
    public void updbteWindow() {
        // no-op
    }
}
