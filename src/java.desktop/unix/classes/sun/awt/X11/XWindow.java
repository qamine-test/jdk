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
import jbvb.bwt.event.*;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.imbge.ColorModel;

import jbvb.lbng.ref.WebkReference;

import jbvb.lbng.reflect.Method;

import sun.util.logging.PlbtformLogger;

import sun.bwt.*;

import sun.bwt.imbge.PixelConverter;

import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;

public clbss XWindow extends XBbseWindow implements X11ComponentPeer {
    privbte stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XWindow");
    privbte stbtic PlbtformLogger insLog = PlbtformLogger.getLogger("sun.bwt.X11.insets.XWindow");
    privbte stbtic PlbtformLogger eventLog = PlbtformLogger.getLogger("sun.bwt.X11.event.XWindow");
    privbte stbtic finbl PlbtformLogger focusLog = PlbtformLogger.getLogger("sun.bwt.X11.focus.XWindow");
    privbte stbtic PlbtformLogger keyEventLog = PlbtformLogger.getLogger("sun.bwt.X11.kye.XWindow");
  /* If b motion comes in while b multi-click is pending,
   * bllow b smudge fbctor so thbt moving the mouse by b smbll
   * bmount does not wipe out the multi-click stbte vbribbles.
   */
    privbte finbl stbtic int AWT_MULTICLICK_SMUDGE = 4;
    // ButtonXXX events stuff
    stbtic int rbutton = 0;
    stbtic int lbstX = 0, lbstY = 0;
    stbtic long lbstTime = 0;
    stbtic long lbstButton = 0;
    stbtic WebkReference<XWindow> lbstWindowRef = null;
    stbtic int clickCount = 0;

    // used to check if we need to re-crebte surfbceDbtb.
    int oldWidth = -1;
    int oldHeight = -1;

    protected PropMwmHints mwm_hints;
    protected stbtic XAtom wm_protocols;
    protected stbtic XAtom wm_delete_window;
    protected stbtic XAtom wm_tbke_focus;

    privbte boolebn stbteChbnged; // Indicbtes whether the vblue on sbvedStbte is vblid
    privbte int sbvedStbte; // Holds lbst known stbte of the top-level window

    XWindowAttributesDbtb winAttr;

    protected X11GrbphicsConfig grbphicsConfig;
    protected AwtGrbphicsConfigDbtb grbphicsConfigDbtb;

    privbte boolebn repbrented;

    XWindow pbrent;

    Component tbrget;

    privbte stbtic int JAWT_LOCK_ERROR=0x00000001;
    privbte stbtic int JAWT_LOCK_CLIP_CHANGED=0x00000002;
    privbte stbtic int JAWT_LOCK_BOUNDS_CHANGED=0x00000004;
    privbte stbtic int JAWT_LOCK_SURFACE_CHANGED=0x00000008;
    privbte int drbwStbte = JAWT_LOCK_CLIP_CHANGED |
    JAWT_LOCK_BOUNDS_CHANGED |
    JAWT_LOCK_SURFACE_CHANGED;

    public stbtic finbl String TARGET = "tbrget",
        REPARENTED = "repbrented"; // whether it is repbrented by defbult

    SurfbceDbtb surfbceDbtb;

    XRepbintAreb pbintAreb;

    // fbllbbck defbult font object
    privbte stbtic Font defbultFont;

    stbtic synchronized Font getDefbultFont() {
        if (null == defbultFont) {
            defbultFont = new Font(Font.DIALOG, Font.PLAIN, 12);
        }
        return defbultFont;
    }

    /* A bitmbsk keeps the button's numbers bs Button1Mbsk, Button2Mbsk, Button3Mbsk
     * which bre bllowed to
     * generbte the CLICK event bfter the RELEASE hbs hbppened.
     * There bre conditions thbt must be true for thbt sending CLICK event:
     * 1) button wbs initiblly PRESSED
     * 2) no movement or drbg hbs hbppened until RELEASE
    */
    privbte int mouseButtonClickAllowed = 0;

    nbtive int getNbtiveColor(Color clr, GrbphicsConfigurbtion gc);
    nbtive void getWMInsets(long window, long left, long top, long right, long bottom, long border);
    nbtive long getTopWindow(long window, long rootWin);
    nbtive void getWindowBounds(long window, long x, long y, long width, long height);
    privbte nbtive stbtic void initIDs();

    stbtic {
        initIDs();
    }

    XWindow(XCrebteWindowPbrbms pbrbms) {
        super(pbrbms);
    }

    XWindow() {
    }

    XWindow(long pbrentWindow, Rectbngle bounds) {
        super(new XCrebteWindowPbrbms(new Object[] {
            BOUNDS, bounds,
            PARENT_WINDOW, Long.vblueOf(pbrentWindow)}));
    }

    XWindow(Component tbrget, long pbrentWindow, Rectbngle bounds) {
        super(new XCrebteWindowPbrbms(new Object[] {
            BOUNDS, bounds,
            PARENT_WINDOW, Long.vblueOf(pbrentWindow),
            TARGET, tbrget}));
    }

    XWindow(Component tbrget, long pbrentWindow) {
        this(tbrget, pbrentWindow, new Rectbngle(tbrget.getBounds()));
    }

    XWindow(Component tbrget) {
        this(tbrget, (tbrget.getPbrent() == null) ? 0 : getPbrentWindowID(tbrget), new Rectbngle(tbrget.getBounds()));
    }

    XWindow(Object tbrget) {
        this(null, 0, null);
    }

    /* This crebte is used by the XEmbeddedFrbmePeer since it hbs to crebte the window
       bs b child of the netscbpe window. This netscbpe window is pbssed in bs wid */
    XWindow(long pbrentWindow) {
        super(new XCrebteWindowPbrbms(new Object[] {
            PARENT_WINDOW, Long.vblueOf(pbrentWindow),
            REPARENTED, Boolebn.TRUE,
            EMBEDDED, Boolebn.TRUE}));
    }

    protected void initGrbphicsConfigurbtion() {
        grbphicsConfig = (X11GrbphicsConfig) tbrget.getGrbphicsConfigurbtion();
        grbphicsConfigDbtb = new AwtGrbphicsConfigDbtb(grbphicsConfig.getADbtb());
    }

    void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);
        repbrented = Boolebn.TRUE.equbls(pbrbms.get(REPARENTED));

        tbrget = (Component)pbrbms.get(TARGET);

        initGrbphicsConfigurbtion();

        AwtGrbphicsConfigDbtb gDbtb = getGrbphicsConfigurbtionDbtb();
        X11GrbphicsConfig config = (X11GrbphicsConfig) getGrbphicsConfigurbtion();
        XVisublInfo visInfo = gDbtb.get_bwt_visInfo();
        pbrbms.putIfNull(EVENT_MASK, XConstbnts.KeyPressMbsk | XConstbnts.KeyRelebseMbsk
            | XConstbnts.FocusChbngeMbsk | XConstbnts.ButtonPressMbsk | XConstbnts.ButtonRelebseMbsk
            | XConstbnts.EnterWindowMbsk | XConstbnts.LebveWindowMbsk | XConstbnts.PointerMotionMbsk
            | XConstbnts.ButtonMotionMbsk | XConstbnts.ExposureMbsk | XConstbnts.StructureNotifyMbsk);

        if (tbrget != null) {
            pbrbms.putIfNull(BOUNDS, new Rectbngle(tbrget.getBounds()));
        } else {
            pbrbms.putIfNull(BOUNDS, new Rectbngle(0, 0, MIN_SIZE, MIN_SIZE));
        }
        pbrbms.putIfNull(BORDER_PIXEL, Long.vblueOf(0));
        getColorModel(); // fix 4948833: this cbll forces the color mbp to be initiblized
        pbrbms.putIfNull(COLORMAP, gDbtb.get_bwt_cmbp());
        pbrbms.putIfNull(DEPTH, gDbtb.get_bwt_depth());
        pbrbms.putIfNull(VISUAL_CLASS, Integer.vblueOf(XConstbnts.InputOutput));
        pbrbms.putIfNull(VISUAL, visInfo.get_visubl());
        pbrbms.putIfNull(VALUE_MASK, XConstbnts.CWBorderPixel | XConstbnts.CWEventMbsk | XConstbnts.CWColormbp);
        Long pbrentWindow = (Long)pbrbms.get(PARENT_WINDOW);
        if (pbrentWindow == null || pbrentWindow.longVblue() == 0) {
            XToolkit.bwtLock();
            try {
                int screen = visInfo.get_screen();
                if (screen != -1) {
                    pbrbms.bdd(PARENT_WINDOW, XlibWrbpper.RootWindow(XToolkit.getDisplby(), screen));
                } else {
                    pbrbms.bdd(PARENT_WINDOW, XToolkit.getDefbultRootWindow());
                }
            } finblly {
                XToolkit.bwtUnlock();
            }
        }

        pbintAreb = new XRepbintAreb();
        if (tbrget != null) {
            this.pbrent = getPbrentXWindowObject(tbrget.getPbrent());
        }

        pbrbms.putIfNull(BACKING_STORE, XToolkit.getBbckingStoreType());

        XToolkit.bwtLock();
        try {
            if (wm_protocols == null) {
                wm_protocols = XAtom.get("WM_PROTOCOLS");
                wm_delete_window = XAtom.get("WM_DELETE_WINDOW");
                wm_tbke_focus = XAtom.get("WM_TAKE_FOCUS");
            }
        }
        finblly {
            XToolkit.bwtUnlock();
        }
        winAttr = new XWindowAttributesDbtb();
        sbvedStbte = XUtilConstbnts.WithdrbwnStbte;
    }

    void postInit(XCrebteWindowPbrbms pbrbms) {
        super.postInit(pbrbms);

        setWMClbss(getWMClbss());

        surfbceDbtb = grbphicsConfig.crebteSurfbceDbtb(this);
        Color c;
        if (tbrget != null && (c = tbrget.getBbckground()) != null) {
            // We need b version of setBbckground thbt does not cbll repbint !!
            // bnd one thbt does not get overridden. The problem is thbt in postInit
            // we cbll setBbckground bnd we don't hbve bll the stuff initiblized to
            // do b full pbint for most peers. So we cbnnot cbll setBbckground in postInit.
            // instebd we need to cbll xSetBbckground.
            xSetBbckground(c);
        }
    }

    public GrbphicsConfigurbtion getGrbphicsConfigurbtion() {
        if (grbphicsConfig == null) {
            initGrbphicsConfigurbtion();
        }
        return grbphicsConfig;
    }

    public AwtGrbphicsConfigDbtb getGrbphicsConfigurbtionDbtb() {
        if (grbphicsConfigDbtb == null) {
            initGrbphicsConfigurbtion();
        }
        return grbphicsConfigDbtb;
    }

    protected String[] getWMClbss() {
        return new String[] {XToolkit.getCorrectXIDString(getClbss().getNbme()), XToolkit.getAWTAppClbssNbme()};
    }

    void setRepbrented(boolebn newVblue) {
        repbrented = newVblue;
    }

    boolebn isRepbrented() {
        return repbrented;
    }

    stbtic long getPbrentWindowID(Component tbrget) {

        ComponentPeer peer = tbrget.getPbrent().getPeer();
        Component temp = tbrget.getPbrent();
        while (!(peer instbnceof XWindow))
        {
            temp = temp.getPbrent();
            peer = temp.getPeer();
        }

        if (peer != null && peer instbnceof XWindow)
            return ((XWindow)peer).getContentWindow();
        else return 0;
    }


    stbtic XWindow getPbrentXWindowObject(Component tbrget) {
        if (tbrget == null) return null;
        Component temp = tbrget.getPbrent();
        if (temp == null) return null;
        ComponentPeer peer = temp.getPeer();
        if (peer == null) return null;
        while ((peer != null) && !(peer instbnceof XWindow))
        {
            temp = temp.getPbrent();
            peer = temp.getPeer();
        }
        if (peer != null && peer instbnceof XWindow)
            return (XWindow) peer;
        else return null;
    }


    boolebn isPbrentOf(XWindow win) {
        if (!(tbrget instbnceof Contbiner) || win == null || win.getTbrget() == null) {
            return fblse;
        }
        Contbiner pbrent = AWTAccessor.getComponentAccessor().getPbrent(win.tbrget);
        while (pbrent != null && pbrent != tbrget) {
            pbrent = AWTAccessor.getComponentAccessor().getPbrent(pbrent);
        }
        return (pbrent == tbrget);
    }

    public Object getTbrget() {
        return tbrget;
    }
    public Component getEventSource() {
        return tbrget;
    }

    public ColorModel getColorModel(int trbnspbrency) {
        return grbphicsConfig.getColorModel (trbnspbrency);
    }

    public ColorModel getColorModel() {
        if (grbphicsConfig != null) {
            return grbphicsConfig.getColorModel ();
        }
        else {
            return XToolkit.getStbticColorModel();
        }
    }

    Grbphics getGrbphics(SurfbceDbtb surfDbtb, Color bfore, Color bbbck, Font bfont) {
        if (surfDbtb == null) return null;

        Component tbrget = this.tbrget;

        /* Fix for bug 4746122. Color bnd Font shouldn't be null */
        Color bgColor = bbbck;
        if (bgColor == null) {
            bgColor = SystemColor.window;
        }
        Color fgColor = bfore;
        if (fgColor == null) {
            fgColor = SystemColor.windowText;
        }
        Font font = bfont;
        if (font == null) {
            font = XWindow.getDefbultFont();
        }
        return new SunGrbphics2D(surfDbtb, fgColor, bgColor, font);
    }

    public Grbphics getGrbphics() {
        return getGrbphics(surfbceDbtb,
                           tbrget.getForeground(),
                           tbrget.getBbckground(),
                           tbrget.getFont());
    }

    public FontMetrics getFontMetrics(Font font) {
        return Toolkit.getDefbultToolkit().getFontMetrics(font);
    }

    public Rectbngle getTbrgetBounds() {
        return tbrget.getBounds();
    }

    /**
     * Returns true if the event hbs been hbndled bnd should not be
     * posted to Jbvb.
     */
    boolebn prePostEvent(AWTEvent e) {
        return fblse;
    }

    stbtic Method m_sendMessbge;
    stbtic void sendEvent(finbl AWTEvent e) {
        // The uses of this method imply thbt the incoming event is system-generbted
        SunToolkit.setSystemGenerbted(e);
        PeerEvent pe = new PeerEvent(Toolkit.getDefbultToolkit(), new Runnbble() {
                public void run() {
                    AWTAccessor.getAWTEventAccessor().setPosted(e);
                    ((Component)e.getSource()).dispbtchEvent(e);
                }
            }, PeerEvent.ULTIMATE_PRIORITY_EVENT);
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER) && (e instbnceof FocusEvent)) {
            focusLog.finer("Sending " + e);
        }
        XToolkit.postEvent(XToolkit.tbrgetToAppContext(e.getSource()), pe);
    }


/*
 * Post bn event to the event queue.
 */
// NOTE: This method mby be cblled by privileged threbds.
//       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    void postEvent(AWTEvent event) {
        XToolkit.postEvent(XToolkit.tbrgetToAppContext(event.getSource()), event);
    }

    stbtic void postEventStbtic(AWTEvent event) {
        XToolkit.postEvent(XToolkit.tbrgetToAppContext(event.getSource()), event);
    }

    public void postEventToEventQueue(finbl AWTEvent event) {
        //fix for 6239938 : Choice drop-down does not disbppebr when it loses focus, on XToolkit
        if (!prePostEvent(event)) {
            //event hbsn't been hbndled bnd must be posted to EventQueue
            postEvent(event);
        }
    }

    // overriden in XCbnvbsPeer
    protected boolebn doErbseBbckground() {
        return true;
    }

    // We need b version of setBbckground thbt does not cbll repbint !!
    // bnd one thbt does not get overridden. The problem is thbt in postInit
    // we cbll setBbckground bnd we don't hbve bll the stuff initiblized to
    // do b full pbint for most peers. So we cbnnot cbll setBbckground in postInit.
    finbl public void xSetBbckground(Color c) {
        XToolkit.bwtLock();
        try {
            winBbckground(c);
            // fix for 6558510: hbndle sun.bwt.noerbsebbckground flbg,
            // see doErbseBbckground() bnd preInit() methods in XCbnvbsPeer
            if (!doErbseBbckground()) {
                return;
            }
            // 6304250: XAWT: Items in choice show b blue border on OpenGL + Solbris10 when bbckground color is set
            // Note: When OGL is enbbled, surfbceDbtb.pixelFor() will not
            // return b pixel vblue bppropribte for pbssing to
            // XSetWindowBbckground().  Therefore, we will use the ColorModel
            // for this component in order to cblculbte b pixel vblue from
            // the given RGB vblue.
            ColorModel cm = getColorModel();
            int pixel = PixelConverter.instbnce.rgbToPixel(c.getRGB(), cm);
            XlibWrbpper.XSetWindowBbckground(XToolkit.getDisplby(), getContentWindow(), pixel);
            XlibWrbpper.XClebrWindow(XToolkit.getDisplby(), getContentWindow());
        }
        finblly {
            XToolkit.bwtUnlock();
        }
    }

    public void setBbckground(Color c) {
        xSetBbckground(c);
    }

    Color bbckgroundColor;
    void winBbckground(Color c) {
        bbckgroundColor = c;
    }

    public Color getWinBbckground() {
        Color c = null;

        if (bbckgroundColor != null) {
            c = bbckgroundColor;
        } else if (pbrent != null) {
            c = pbrent.getWinBbckground();
        }

        if (c instbnceof SystemColor) {
            c = new Color(c.getRGB());
        }

        return c;
    }

    public boolebn isEmbedded() {
        return embedded;
    }

    public finbl void repbint(int x, int y, int width, int height) {
        if (!isVisible() || getWidth() == 0 || getHeight() == 0) {
            return;
        }
        Grbphics g = getGrbphics();
        if (g != null) {
            try {
                g.setClip(x, y, width, height);
                if (SunToolkit.isDispbtchThrebdForAppContext(getTbrget())) {
                    pbint(g); // The nbtive bnd tbrget will be pbinted in plbce.
                } else {
                    pbintPeer(g);
                    postPbintEvent(tbrget, x, y, width, height);
                }
            } finblly {
                g.dispose();
            }
        }
    }

    void repbint() {
        repbint(0, 0, getWidth(), getHeight());
    }

    public void pbint(finbl Grbphics g) {
        // pbint peer
        pbintPeer(g);
    }

    void pbintPeer(finbl Grbphics g) {
    }
    //used by Peers to bvoid flickering withing pbint()
    protected void flush(){
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XFlush(XToolkit.getDisplby());
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    public void popup(int x, int y, int width, int height) {
        // TBD: grbb the pointer
        xSetBounds(x, y, width, height);
    }

    public void hbndleExposeEvent(XEvent xev) {
        super.hbndleExposeEvent(xev);
        XExposeEvent xe = xev.get_xexpose();
        if (isEventDisbbled(xev)) {
            return;
        }
        int x = xe.get_x();
        int y = xe.get_y();
        int w = xe.get_width();
        int h = xe.get_height();

        Component tbrget = getEventSource();
        AWTAccessor.ComponentAccessor compAccessor = AWTAccessor.getComponentAccessor();

        if (!compAccessor.getIgnoreRepbint(tbrget)
            && compAccessor.getWidth(tbrget) != 0
            && compAccessor.getHeight(tbrget) != 0)
        {
            postPbintEvent(tbrget, x, y, w, h);
        }
    }

    public void postPbintEvent(Component tbrget, int x, int y, int w, int h) {
        PbintEvent event = PbintEventDispbtcher.getPbintEventDispbtcher().
            crebtePbintEvent(tbrget, x, y, w, h);
        if (event != null) {
            postEventToEventQueue(event);
        }
    }

    stbtic int getModifiers(int stbte, int button, int keyCode) {
        return getModifiers(stbte, button, keyCode, 0,  fblse);
    }

    stbtic int getModifiers(int stbte, int button, int keyCode, int type, boolebn wheel_mouse) {
        int modifiers = 0;

        if (((stbte & XConstbnts.ShiftMbsk) != 0) ^ (keyCode == KeyEvent.VK_SHIFT)) {
            modifiers |= InputEvent.SHIFT_DOWN_MASK;
        }
        if (((stbte & XConstbnts.ControlMbsk) != 0) ^ (keyCode == KeyEvent.VK_CONTROL)) {
            modifiers |= InputEvent.CTRL_DOWN_MASK;
        }
        if (((stbte & XToolkit.metbMbsk) != 0) ^ (keyCode == KeyEvent.VK_META)) {
            modifiers |= InputEvent.META_DOWN_MASK;
        }
        if (((stbte & XToolkit.bltMbsk) != 0) ^ (keyCode == KeyEvent.VK_ALT)) {
            modifiers |= InputEvent.ALT_DOWN_MASK;
        }
        if (((stbte & XToolkit.modeSwitchMbsk) != 0) ^ (keyCode == KeyEvent.VK_ALT_GRAPH)) {
            modifiers |= InputEvent.ALT_GRAPH_DOWN_MASK;
        }
        //InputEvent.BUTTON_DOWN_MASK brrby is stbrting from BUTTON1_DOWN_MASK on index == 0.
        // button currently reflects b rebl button number bnd stbrts from 1. (except NOBUTTON which is zero )

        /* this is bn bttempt to refbctor button IDs in : MouseEvent, InputEvent, XlibWrbpper bnd XWindow.*/

        //reflects b button number similbr to MouseEvent.BUTTON1, 2, 3 etc.
        for (int i = 0; i < XConstbnts.buttons.length; i ++){
            //modifier should be bdded if :
            // 1) current button is now still in PRESSED stbte (mebns thbt user just pressed mouse but not relebsed yet) or
            // 2) if Xsystem reports thbt "stbte" represents thbt button wbs just relebsed. This only hbppens on RELEASE with 1,2,3 buttons.
            // ONLY one of these conditions should be TRUE to bdd thbt modifier.
            if (((stbte & XlibUtil.getButtonMbsk(i + 1)) != 0) != (button == XConstbnts.buttons[i])){
                //exclude wheel buttons from bdding their numbers bs modifiers
                if (!wheel_mouse) {
                    modifiers |= InputEvent.getMbskForButton(i+1);
                }
            }
        }
        return modifiers;
    }

    stbtic int getXModifiers(AWTKeyStroke stroke) {
        int mods = stroke.getModifiers();
        int res = 0;
        if ((mods & (InputEvent.SHIFT_DOWN_MASK | InputEvent.SHIFT_MASK)) != 0) {
            res |= XConstbnts.ShiftMbsk;
        }
        if ((mods & (InputEvent.CTRL_DOWN_MASK | InputEvent.CTRL_MASK)) != 0) {
            res |= XConstbnts.ControlMbsk;
        }
        if ((mods & (InputEvent.ALT_DOWN_MASK | InputEvent.ALT_MASK)) != 0) {
            res |= XToolkit.bltMbsk;
        }
        if ((mods & (InputEvent.META_DOWN_MASK | InputEvent.META_MASK)) != 0) {
            res |= XToolkit.metbMbsk;
        }
        if ((mods & (InputEvent.ALT_GRAPH_DOWN_MASK | InputEvent.ALT_GRAPH_MASK)) != 0) {
            res |= XToolkit.modeSwitchMbsk;
        }
        return res;
    }

    /**
     * Returns true if this event is disbbled bnd shouldn't be pbssed to Jbvb.
     * Defbult implementbtion returns fblse for bll events.
     */
    stbtic int getRightButtonNumber() {
        if (rbutton == 0) { // not initiblized yet
            XToolkit.bwtLock();
            try {
                rbutton = XlibWrbpper.XGetPointerMbpping(XToolkit.getDisplby(), XlibWrbpper.ibuffer, 3);
            }
            finblly {
                XToolkit.bwtUnlock();
            }
        }
        return rbutton;
    }

    stbtic int getMouseMovementSmudge() {
        //TODO: It's possible to rebd corresponding settings
        return AWT_MULTICLICK_SMUDGE;
    }

    public void hbndleButtonPressRelebse(XEvent xev) {
        super.hbndleButtonPressRelebse(xev);
        XButtonEvent xbe = xev.get_xbutton();
        if (isEventDisbbled(xev)) {
            return;
        }
        if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            eventLog.fine(xbe.toString());
        }
        long when;
        int modifiers;
        boolebn popupTrigger = fblse;
        int button=0;
        boolebn wheel_mouse = fblse;
        int lbutton = xbe.get_button();
        /*
         * Ignore the buttons bbove 20 due to the bit limit for
         * InputEvent.BUTTON_DOWN_MASK.
         * One more bit is reserved for FIRST_HIGH_BIT.
         */
        if (lbutton > SunToolkit.MAX_BUTTONS_SUPPORTED) {
            return;
        }
        int type = xev.get_type();
        when = xbe.get_time();
        long jWhen = XToolkit.nowMillisUTC_offset(when);

        int x = xbe.get_x();
        int y = xbe.get_y();
        if (xev.get_xbny().get_window() != window) {
            Point locblXY = toLocbl(xbe.get_x_root(), xbe.get_y_root());
            x = locblXY.x;
            y = locblXY.y;
        }

        if (type == XConstbnts.ButtonPress) {
            //Allow this mouse button to generbte CLICK event on next ButtonRelebse
            mouseButtonClickAllowed |= XlibUtil.getButtonMbsk(lbutton);
            XWindow lbstWindow = (lbstWindowRef != null) ? (lbstWindowRef.get()):(null);
            /*
               multiclick checking
            */
            if (eventLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                eventLog.finest("lbstWindow = " + lbstWindow + ", lbstButton "
                + lbstButton + ", lbstTime " + lbstTime + ", multiClickTime "
                + XToolkit.getMultiClickTime());
            }
            if (lbstWindow == this && lbstButton == lbutton && (when - lbstTime) < XToolkit.getMultiClickTime()) {
                clickCount++;
            } else {
                clickCount = 1;
                lbstWindowRef = new WebkReference<>(this);
                lbstButton = lbutton;
                lbstX = x;
                lbstY = y;
            }
            lbstTime = when;


            /*
               Check for popup trigger !!
            */
            if (lbutton == getRightButtonNumber() || lbutton > 2) {
                popupTrigger = true;
            } else {
                popupTrigger = fblse;
            }
        }

        button = XConstbnts.buttons[lbutton - 1];
        // 4 bnd 5 buttons bre usublly considered bssigned to b first wheel
        if (lbutton == XConstbnts.buttons[3] ||
            lbutton == XConstbnts.buttons[4]) {
            wheel_mouse = true;
        }

        // mbpping extrb buttons to numbers stbrting from 4.
        if ((button > XConstbnts.buttons[4]) && (!Toolkit.getDefbultToolkit().breExtrbMouseButtonsEnbbled())){
            return;
        }

        if (button > XConstbnts.buttons[4]){
            button -= 2;
        }
        modifiers = getModifiers(xbe.get_stbte(),button,0, type, wheel_mouse);

        if (!wheel_mouse) {
            MouseEvent me = new MouseEvent(getEventSource(),
                                           type == XConstbnts.ButtonPress ? MouseEvent.MOUSE_PRESSED : MouseEvent.MOUSE_RELEASED,
                                           jWhen,modifiers, x, y,
                                           xbe.get_x_root(),
                                           xbe.get_y_root(),
                                           clickCount,popupTrigger,button);

            postEventToEventQueue(me);

            if ((type == XConstbnts.ButtonRelebse) &&
                ((mouseButtonClickAllowed & XlibUtil.getButtonMbsk(lbutton)) != 0) ) // No up-button in the drbg-stbte
            {
                postEventToEventQueue(me = new MouseEvent(getEventSource(),
                                                     MouseEvent.MOUSE_CLICKED,
                                                     jWhen,
                                                     modifiers,
                                                     x, y,
                                                     xbe.get_x_root(),
                                                     xbe.get_y_root(),
                                                     clickCount,
                                                     fblse, button));
            }

        }
        else {
            if (xev.get_type() == XConstbnts.ButtonPress) {
                MouseWheelEvent mwe = new MouseWheelEvent(getEventSource(),MouseEvent.MOUSE_WHEEL, jWhen,
                                                          modifiers,
                                                          x, y,
                                                          xbe.get_x_root(),
                                                          xbe.get_y_root(),
                                                          1,fblse,MouseWheelEvent.WHEEL_UNIT_SCROLL,
                                                          3,button==4 ?  -1 : 1);
                postEventToEventQueue(mwe);
            }
        }

        /* Updbte the stbte vbribble AFTER the CLICKED event post. */
        if (type == XConstbnts.ButtonRelebse) {
            /* Exclude this mouse button from bllowed list.*/
            mouseButtonClickAllowed &= ~ XlibUtil.getButtonMbsk(lbutton);
        }
    }

    public void hbndleMotionNotify(XEvent xev) {
        super.hbndleMotionNotify(xev);
        XMotionEvent xme = xev.get_xmotion();
        if (isEventDisbbled(xev)) {
            return;
        }

        int mouseKeyStbte = 0; //(xme.get_stbte() & (XConstbnts.buttonsMbsk[0] | XConstbnts.buttonsMbsk[1] | XConstbnts.buttonsMbsk[2]));

        //this doesn't work for extrb buttons becbuse Xsystem is sending stbte==0 for every extrb button event.
        // we cbn't correct it in MouseEvent clbss bs we done it with modifiers, becbuse exbct type (DRAG|MOVE)
        // should be pbssed from XWindow.
        finbl int buttonsNumber = XToolkit.getNumberOfButtonsForMbsk();

        for (int i = 0; i < buttonsNumber; i++){
            // TODO : here is the bug in WM: extrb buttons doesn't hbve stbte!=0 bs they should.
            if ((i != 4) && (i != 5)) {
                mouseKeyStbte = mouseKeyStbte | (xme.get_stbte() & XlibUtil.getButtonMbsk(i + 1));
            }
        }

        boolebn isDrbgging = (mouseKeyStbte != 0);
        int mouseEventType = 0;

        if (isDrbgging) {
            mouseEventType = MouseEvent.MOUSE_DRAGGED;
        } else {
            mouseEventType = MouseEvent.MOUSE_MOVED;
        }

        /*
           Fix for 6176814 .  Add multiclick checking.
        */
        int x = xme.get_x();
        int y = xme.get_y();
        XWindow lbstWindow = (lbstWindowRef != null) ? (lbstWindowRef.get()):(null);

        if (!(lbstWindow == this &&
              (xme.get_time() - lbstTime) < XToolkit.getMultiClickTime()  &&
              (Mbth.bbs(lbstX - x) < AWT_MULTICLICK_SMUDGE &&
               Mbth.bbs(lbstY - y) < AWT_MULTICLICK_SMUDGE))) {
          clickCount = 0;
          lbstWindowRef = null;
          mouseButtonClickAllowed = 0;
          lbstTime = 0;
          lbstX = 0;
          lbstY = 0;
        }

        long jWhen = XToolkit.nowMillisUTC_offset(xme.get_time());
        int modifiers = getModifiers(xme.get_stbte(), 0, 0);
        boolebn popupTrigger = fblse;

        Component source = getEventSource();

        if (xme.get_window() != window) {
            Point locblXY = toLocbl(xme.get_x_root(), xme.get_y_root());
            x = locblXY.x;
            y = locblXY.y;
        }
        /* Fix for 5039416.
         * According to cbnvbs.c we shouldn't post bny MouseEvent if mouse is drbgging bnd clickCount!=0.
         */
        if ((isDrbgging && clickCount == 0) || !isDrbgging) {
            MouseEvent mme = new MouseEvent(source, mouseEventType, jWhen,
                                            modifiers, x, y, xme.get_x_root(), xme.get_y_root(),
                                            clickCount, popupTrigger, MouseEvent.NOBUTTON);
            postEventToEventQueue(mme);
        }
    }


    // REMIND: need to implement looking for disbbled events
    public nbtive boolebn x11inputMethodLookupString(long event, long [] keysymArrby);
    nbtive boolebn hbveCurrentX11InputMethodInstbnce();

    privbte boolebn mouseAboveMe;

    public boolebn isMouseAbove() {
        synchronized (getStbteLock()) {
            return mouseAboveMe;
        }
    }
    protected void setMouseAbove(boolebn bbove) {
        synchronized (getStbteLock()) {
            mouseAboveMe = bbove;
        }
    }

    protected void enterNotify(long window) {
        if (window == getWindow()) {
            setMouseAbove(true);
        }
    }
    protected void lebveNotify(long window) {
        if (window == getWindow()) {
            setMouseAbove(fblse);
        }
    }

    public void hbndleXCrossingEvent(XEvent xev) {
        super.hbndleXCrossingEvent(xev);
        XCrossingEvent xce = xev.get_xcrossing();

        if (eventLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            eventLog.finest(xce.toString());
        }

        if (xce.get_type() == XConstbnts.EnterNotify) {
            enterNotify(xce.get_window());
        } else { // LebveNotify:
            lebveNotify(xce.get_window());
        }

        // Skip event If it wbs cbused by b grbb
        // This is needed becbuse on displbys with focus-follows-mouse on MousePress X system generbtes
        // two XCrossing events with mode != NormblNotify. First of them notifies thbt the mouse hbs left
        // current component. Second one notifies thbt it hbs entered into the sbme component.
        // This looks like the window under the mouse hbs bctublly chbnged bnd Jbvb hbndle these  events
        // bccordingly. This lebds to impossibility to mbke b double click on Component (6404708)
        XWindowPeer toplevel = getToplevelXWindow();
        if (toplevel != null && !toplevel.isModblBlocked()){
            if (xce.get_mode() != XConstbnts.NotifyNormbl) {
                // 6404708 : need updbte cursor in bccordbnce with skipping Lebve/EnterNotify event
                // wherebs it doesn't need to hbndled further.
                if (xce.get_type() == XConstbnts.EnterNotify) {
                    XAwtStbte.setComponentMouseEntered(getEventSource());
                    XGlobblCursorMbnbger.nbtiveUpdbteCursor(getEventSource());
                } else { // LebveNotify:
                    XAwtStbte.setComponentMouseEntered(null);
                }
                return;
            }
        }
        // X sends XCrossing to bll hierbrchy so if the edge of child equbls to
        // bncestor bnd mouse enters child, the bncestor will get bn event too.
        // From jbvb point the event is bogus bs bncestor is obscured, so if
        // the child cbn get jbvb event itself, we skip it on bncestor.
        long childWnd = xce.get_subwindow();
        if (childWnd != XConstbnts.None) {
            XBbseWindow child = XToolkit.windowToXWindow(childWnd);
            if (child != null && child instbnceof XWindow &&
                !child.isEventDisbbled(xev))
            {
                return;
            }
        }

        // Remember old component with mouse to hbve the opportunity to send it MOUSE_EXITED.
        finbl Component compWithMouse = XAwtStbte.getComponentMouseEntered();
        if (toplevel != null) {
            if(!toplevel.isModblBlocked()){
                if (xce.get_type() == XConstbnts.EnterNotify) {
                    // Chbnge XAwtStbte's component mouse entered to the up-to-dbte one before requesting
                    // to updbte the cursor since XAwtStbte.getComponentMouseEntered() is used when the
                    // cursor is updbted (in XGlobblCursorMbnbger.findHebvyweightUnderCursor()).
                    XAwtStbte.setComponentMouseEntered(getEventSource());
                    XGlobblCursorMbnbger.nbtiveUpdbteCursor(getEventSource());
                } else { // LebveNotify:
                    XAwtStbte.setComponentMouseEntered(null);
                }
            } else {
                ((XComponentPeer) AWTAccessor.getComponentAccessor().getPeer(tbrget))
                    .pSetCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }

        if (isEventDisbbled(xev)) {
            return;
        }

        long jWhen = XToolkit.nowMillisUTC_offset(xce.get_time());
        int modifiers = getModifiers(xce.get_stbte(),0,0);
        int clickCount = 0;
        boolebn popupTrigger = fblse;
        int x = xce.get_x();
        int y = xce.get_y();
        if (xce.get_window() != window) {
            Point locblXY = toLocbl(xce.get_x_root(), xce.get_y_root());
            x = locblXY.x;
            y = locblXY.y;
        }

        // This code trbcks boundbry crossing bnd ensures MOUSE_ENTER/EXIT
        // bre posted in blternbte pbirs
        if (compWithMouse != null) {
            MouseEvent me = new MouseEvent(compWithMouse,
                MouseEvent.MOUSE_EXITED, jWhen, modifiers, xce.get_x(),
                xce.get_y(), xce.get_x_root(), xce.get_y_root(), clickCount, popupTrigger,
                MouseEvent.NOBUTTON);
            postEventToEventQueue(me);
            eventLog.finest("Clebring lbst window ref");
            lbstWindowRef = null;
        }
        if (xce.get_type() == XConstbnts.EnterNotify) {
            MouseEvent me = new MouseEvent(getEventSource(), MouseEvent.MOUSE_ENTERED,
                jWhen, modifiers, xce.get_x(), xce.get_y(), xce.get_x_root(), xce.get_y_root(), clickCount,
                popupTrigger, MouseEvent.NOBUTTON);
            postEventToEventQueue(me);
        }
    }

    public void doLbyout(int x, int y, int width, int height) {}

    public void hbndleConfigureNotifyEvent(XEvent xev) {
        Rectbngle oldBounds = getBounds();

        super.hbndleConfigureNotifyEvent(xev);
        if (insLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            insLog.finer("Configure, {0}, event disbbled: {1}",
                     xev.get_xconfigure(), isEventDisbbled(xev));
        }
        if (isEventDisbbled(xev)) {
            return;
        }

//  if ( Check if it's b resize, b move, or b stbcking order chbnge )
//  {
        Rectbngle bounds = getBounds();
        if (!bounds.getSize().equbls(oldBounds.getSize())) {
            postEventToEventQueue(new ComponentEvent(getEventSource(), ComponentEvent.COMPONENT_RESIZED));
        }
        if (!bounds.getLocbtion().equbls(oldBounds.getLocbtion())) {
            postEventToEventQueue(new ComponentEvent(getEventSource(), ComponentEvent.COMPONENT_MOVED));
        }
//  }
    }

    public void hbndleMbpNotifyEvent(XEvent xev) {
        super.hbndleMbpNotifyEvent(xev);
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Mbpped {0}", this);
        }
        if (isEventDisbbled(xev)) {
            return;
        }
        ComponentEvent ce;

        ce = new ComponentEvent(getEventSource(), ComponentEvent.COMPONENT_SHOWN);
        postEventToEventQueue(ce);
    }

    public void hbndleUnmbpNotifyEvent(XEvent xev) {
        super.hbndleUnmbpNotifyEvent(xev);
        if (isEventDisbbled(xev)) {
            return;
        }
        ComponentEvent ce;

        ce = new ComponentEvent(tbrget, ComponentEvent.COMPONENT_HIDDEN);
        postEventToEventQueue(ce);
    }

    privbte void dumpKeysymArrby(XKeyEvent ev) {
        if (keyEventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            keyEventLog.fine("  "+Long.toHexString(XlibWrbpper.XKeycodeToKeysym(XToolkit.getDisplby(), ev.get_keycode(), 0))+
                             "\n        "+Long.toHexString(XlibWrbpper.XKeycodeToKeysym(XToolkit.getDisplby(), ev.get_keycode(), 1))+
                             "\n        "+Long.toHexString(XlibWrbpper.XKeycodeToKeysym(XToolkit.getDisplby(), ev.get_keycode(), 2))+
                             "\n        "+Long.toHexString(XlibWrbpper.XKeycodeToKeysym(XToolkit.getDisplby(), ev.get_keycode(), 3)));
        }
    }
    /**
       Return unicode chbrbcter or 0 if no correspondent chbrbcter found.
       Pbrbmeter is b keysym bbsicblly from keysymdef.h
       XXX: how bbout vendor keys? Is there some with Unicode vblue bnd not in the list?
    */
    int keysymToUnicode( long keysym, int stbte ) {
        return XKeysym.convertKeysym( keysym, stbte );
    }
    int keyEventType2Id( int xEventType ) {
        return xEventType == XConstbnts.KeyPress ? jbvb.bwt.event.KeyEvent.KEY_PRESSED :
               xEventType == XConstbnts.KeyRelebse ? jbvb.bwt.event.KeyEvent.KEY_RELEASED : 0;
    }
    stbtic privbte long xkeycodeToKeysym(XKeyEvent ev) {
        return XKeysym.getKeysym( ev );
    }
    privbte long xkeycodeToPrimbryKeysym(XKeyEvent ev) {
        return XKeysym.xkeycode2primbry_keysym( ev );
    }
    stbtic privbte int primbryUnicode2JbvbKeycode(int uni) {
        return (uni > 0? sun.bwt.ExtendedKeyCodes.getExtendedKeyCodeForChbr(uni) : 0);
        //return (uni > 0? uni + 0x01000000 : 0);
    }
    void logIncomingKeyEvent(XKeyEvent ev) {
        if (keyEventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            keyEventLog.fine("--XWindow.jbvb:hbndleKeyEvent:"+ev);
        }
        dumpKeysymArrby(ev);
        if (keyEventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            keyEventLog.fine("XXXXXXXXXXXXXX jbvbkeycode will be most probbbly:0x"+ Integer.toHexString(XKeysym.getJbvbKeycodeOnly(ev)));
        }
    }
    public void hbndleKeyPress(XEvent xev) {
        super.hbndleKeyPress(xev);
        XKeyEvent ev = xev.get_xkey();
        if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            eventLog.fine(ev.toString());
        }
        if (isEventDisbbled(xev)) {
            return;
        }
        hbndleKeyPress(ev);
    }
    // cblled directly from this pbckbge, unlike hbndleKeyRelebse.
    // un-finbl it if you need to override it in b subclbss.
    finbl void hbndleKeyPress(XKeyEvent ev) {
        long keysym[] = new long[2];
        int unicodeKey = 0;
        keysym[0] = XConstbnts.NoSymbol;

        if (keyEventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            logIncomingKeyEvent( ev );
        }
        if ( //TODO check if there's bn bctive input method instbnce
             // without cblling b nbtive method. Is it necessbry though?
            hbveCurrentX11InputMethodInstbnce()) {
            if (x11inputMethodLookupString(ev.pDbtb, keysym)) {
                if (keyEventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                    keyEventLog.fine("--XWindow.jbvb XIM did process event; return; dec keysym processed:"+(keysym[0])+
                                   "; hex keysym processed:"+Long.toHexString(keysym[0])
                                   );
                }
                return;
            }else {
                unicodeKey = keysymToUnicode( keysym[0], ev.get_stbte() );
                if (keyEventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                    keyEventLog.fine("--XWindow.jbvb XIM did NOT process event, hex keysym:"+Long.toHexString(keysym[0])+"\n"+
                                     "                                         unicode key:"+Integer.toHexString(unicodeKey));
                }
            }
        }else  {
            // No input method instbnce found. For exbmple, there's b Jbvb Input Method.
            // Produce do-it-yourself keysym bnd perhbps unicode chbrbcter.
            keysym[0] = xkeycodeToKeysym(ev);
            unicodeKey = keysymToUnicode( keysym[0], ev.get_stbte() );
            if (keyEventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                keyEventLog.fine("--XWindow.jbvb XIM is bbsent;             hex keysym:"+Long.toHexString(keysym[0])+"\n"+
                                 "                                         unicode key:"+Integer.toHexString(unicodeKey));
            }
        }
        // Keysym should be converted to Unicode, if possible bnd necessbry,
        // bnd Jbvb KeyEvent keycode should be cblculbted.
        // For press we should post pressed & typed Jbvb events.
        //
        // Press event might be not processed to this time becbuse
        //  (1) either XIM could not hbndle it or
        //  (2) it wbs Lbtin 1:1 mbpping.
        //
        // Preserve modifiers to get Jbvb key code for debd keys
        boolebn isDebdKey = isDebdKey(keysym[0]);
        XKeysym.Keysym2JbvbKeycode jkc = isDebdKey ? XKeysym.getJbvbKeycode(keysym[0])
                : XKeysym.getJbvbKeycode(ev);
        if( jkc == null ) {
            jkc = new XKeysym.Keysym2JbvbKeycode(jbvb.bwt.event.KeyEvent.VK_UNDEFINED, jbvb.bwt.event.KeyEvent.KEY_LOCATION_UNKNOWN);
        }

        // Tbke the first keysym from b keysym brrby bssocibted with the XKeyevent
        // bnd convert it to Unicode. Then, even if b Jbvb keycode for the keystroke
        // is undefined, we still hbve b guess of whbt hbs been engrbved on b keytop.
        int unicodeFromPrimbryKeysym = keysymToUnicode( xkeycodeToPrimbryKeysym(ev) ,0);

        if (keyEventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            keyEventLog.fine(">>>Fire Event:"+
               (ev.get_type() == XConstbnts.KeyPress ? "KEY_PRESSED; " : "KEY_RELEASED; ")+
               "jkeycode:decimbl="+jkc.getJbvbKeycode()+
               ", hex=0x"+Integer.toHexString(jkc.getJbvbKeycode())+"; "+
               " legbcy jkeycode: decimbl="+XKeysym.getLegbcyJbvbKeycodeOnly(ev)+
               ", hex=0x"+Integer.toHexString(XKeysym.getLegbcyJbvbKeycodeOnly(ev))+"; "
            );
        }

        int jkeyToReturn = XKeysym.getLegbcyJbvbKeycodeOnly(ev); // somewby bbckwbrd compbtible
        int jkeyExtended = jkc.getJbvbKeycode() == jbvb.bwt.event.KeyEvent.VK_UNDEFINED ?
                           primbryUnicode2JbvbKeycode( unicodeFromPrimbryKeysym ) :
                             jkc.getJbvbKeycode();
        postKeyEvent( jbvb.bwt.event.KeyEvent.KEY_PRESSED,
                          ev.get_time(),
                          isDebdKey ? jkeyExtended : jkeyToReturn,
                          (unicodeKey == 0 ? jbvb.bwt.event.KeyEvent.CHAR_UNDEFINED : unicodeKey),
                          jkc.getKeyLocbtion(),
                          ev.get_stbte(),ev.getPDbtb(), XKeyEvent.getSize(), (long)(ev.get_keycode()),
                          unicodeFromPrimbryKeysym,
                          jkeyExtended);


        if (unicodeKey > 0 && !isDebdKey) {
                if (keyEventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                    keyEventLog.fine("fire _TYPED on "+unicodeKey);
                }
                postKeyEvent( jbvb.bwt.event.KeyEvent.KEY_TYPED,
                              ev.get_time(),
                              jbvb.bwt.event.KeyEvent.VK_UNDEFINED,
                              unicodeKey,
                              jbvb.bwt.event.KeyEvent.KEY_LOCATION_UNKNOWN,
                              ev.get_stbte(),ev.getPDbtb(), XKeyEvent.getSize(), (long)0,
                              unicodeFromPrimbryKeysym,
                              jbvb.bwt.event.KeyEvent.VK_UNDEFINED);

        }


    }

    public void hbndleKeyRelebse(XEvent xev) {
        super.hbndleKeyRelebse(xev);
        XKeyEvent ev = xev.get_xkey();
        if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            eventLog.fine(ev.toString());
        }
        if (isEventDisbbled(xev)) {
            return;
        }
        hbndleKeyRelebse(ev);
    }
    // un-privbte it if you need to cbll it from elsewhere
    privbte void hbndleKeyRelebse(XKeyEvent ev) {
        int unicodeKey = 0;

        if (keyEventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            logIncomingKeyEvent( ev );
        }
        // Keysym should be converted to Unicode, if possible bnd necessbry,
        // bnd Jbvb KeyEvent keycode should be cblculbted.
        // For relebse we should post relebsed event.
        //
        // Preserve modifiers to get Jbvb key code for debd keys
        long keysym = xkeycodeToKeysym(ev);
        boolebn isDebdKey = isDebdKey(keysym);
        XKeysym.Keysym2JbvbKeycode jkc = isDebdKey ? XKeysym.getJbvbKeycode(keysym)
                : XKeysym.getJbvbKeycode(ev);
        if( jkc == null ) {
            jkc = new XKeysym.Keysym2JbvbKeycode(jbvb.bwt.event.KeyEvent.VK_UNDEFINED, jbvb.bwt.event.KeyEvent.KEY_LOCATION_UNKNOWN);
        }
        if (keyEventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            keyEventLog.fine(">>>Fire Event:"+
               (ev.get_type() == XConstbnts.KeyPress ? "KEY_PRESSED; " : "KEY_RELEASED; ")+
               "jkeycode:decimbl="+jkc.getJbvbKeycode()+
               ", hex=0x"+Integer.toHexString(jkc.getJbvbKeycode())+"; "+
               " legbcy jkeycode: decimbl="+XKeysym.getLegbcyJbvbKeycodeOnly(ev)+
               ", hex=0x"+Integer.toHexString(XKeysym.getLegbcyJbvbKeycodeOnly(ev))+"; "
            );
        }
        // We obtbin keysym from IM bnd derive unicodeKey from it for KeyPress only.
        // We used to cbche thbt vblue bnd retrieve it on KeyRelebse,
        // but in cbse for exbmple of b debd key+vowel pbir, b vowel bfter b debdkey
        // might never be cbched before.
        // Also, switching between keybobrd lbyouts, we might cbche b wrong letter.
        // Thbt's why we use the sbme procedure bs if there wbs no IM instbnce: do-it-yourself unicode.
        unicodeKey = keysymToUnicode( xkeycodeToKeysym(ev), ev.get_stbte() );

        // Tbke b first keysym from b keysym brrby bssocibted with the XKeyevent
        // bnd convert it to Unicode. Then, even if Jbvb keycode for the keystroke
        // is undefined, we still will hbve b guess of whbt wbs engrbved on b keytop.
        int unicodeFromPrimbryKeysym = keysymToUnicode( xkeycodeToPrimbryKeysym(ev) ,0);

        int jkeyToReturn = XKeysym.getLegbcyJbvbKeycodeOnly(ev); // somewby bbckwbrd compbtible
        int jkeyExtended = jkc.getJbvbKeycode() == jbvb.bwt.event.KeyEvent.VK_UNDEFINED ?
                           primbryUnicode2JbvbKeycode( unicodeFromPrimbryKeysym ) :
                             jkc.getJbvbKeycode();
        postKeyEvent(  jbvb.bwt.event.KeyEvent.KEY_RELEASED,
                          ev.get_time(),
                          isDebdKey ? jkeyExtended : jkeyToReturn,
                          (unicodeKey == 0 ? jbvb.bwt.event.KeyEvent.CHAR_UNDEFINED : unicodeKey),
                          jkc.getKeyLocbtion(),
                          ev.get_stbte(),ev.getPDbtb(), XKeyEvent.getSize(), (long)(ev.get_keycode()),
                          unicodeFromPrimbryKeysym,
                          jkeyExtended);


    }


    privbte boolebn isDebdKey(long keysym){
        return XKeySymConstbnts.XK_debd_grbve <= keysym && keysym <= XKeySymConstbnts.XK_debd_semivoiced_sound;
    }

    /*
     * XmNiconic bnd Mbp/UnmbpNotify (thbt XmNiconic relies on) bre
     * unrelibble, since mbpping chbnges cbn hbppen for b virtubl desktop
     * switch or MbcOS style shbding thbt becbme quite populbr under X bs
     * well.  Yes, it probbbly should not be this wby, bs it violbtes
     * ICCCM, but reblity is thbt quite b lot of window mbnbgers bbuse
     * mbpping stbte.
     */
    int getWMStbte() {
        if (stbteChbnged) {
            stbteChbnged = fblse;
            WindowPropertyGetter getter =
                new WindowPropertyGetter(window, XWM.XA_WM_STATE, 0, 1, fblse,
                                         XWM.XA_WM_STATE);
            try {
                int stbtus = getter.execute();
                if (stbtus != XConstbnts.Success || getter.getDbtb() == 0) {
                    return sbvedStbte = XUtilConstbnts.WithdrbwnStbte;
                }

                if (getter.getActublType() != XWM.XA_WM_STATE.getAtom() && getter.getActublFormbt() != 32) {
                    return sbvedStbte = XUtilConstbnts.WithdrbwnStbte;
                }
                sbvedStbte = (int)Nbtive.getCbrd32(getter.getDbtb());
            } finblly {
                getter.dispose();
            }
        }
        return sbvedStbte;
    }

    /**
     * Override this methods to get notificbtions when top-level window stbte chbnges. The stbte is
     * mebnt in terms of ICCCM: WithdrbwnStbte, IconicStbte, NormblStbte
     */
    protected void stbteChbnged(long time, int oldStbte, int newStbte) {
    }

    @Override
    public void hbndlePropertyNotify(XEvent xev) {
        super.hbndlePropertyNotify(xev);
        XPropertyEvent ev = xev.get_xproperty();
        if (ev.get_btom() == XWM.XA_WM_STATE.getAtom()) {
            // Stbte hbs chbnged, invblidbte sbved vblue
            stbteChbnged = true;
            stbteChbnged(ev.get_time(), sbvedStbte, getWMStbte());
        }
    }

    public void reshbpe(Rectbngle bounds) {
        reshbpe(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void reshbpe(int x, int y, int width, int height) {
        if (width <= 0) {
            width = 1;
        }
        if (height <= 0) {
            height = 1;
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        xSetBounds(x, y, width, height);
        // Fixed 6322593, 6304251, 6315137:
        // XWindow's SurfbceDbtb should be invblidbted bnd recrebted bs pbrt
        // of the process of resizing the window
        // see the evblubtion of the bug 6304251 for more informbtion
        vblidbteSurfbce();
        lbyout();
    }

    public void lbyout() {}

    boolebn isShowing() {
        return visible;
    }

    boolebn isResizbble() {
        return true;
    }

    boolebn isLocbtionByPlbtform() {
        return fblse;
    }

    void updbteSizeHints() {
        updbteSizeHints(x, y, width, height);
    }

    void updbteSizeHints(int x, int y, int width, int height) {
        long flbgs = XUtilConstbnts.PSize | (isLocbtionByPlbtform() ? 0 : (XUtilConstbnts.PPosition | XUtilConstbnts.USPosition));
        if (!isResizbble()) {
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("Window {0} is not resizbble", this);
            }
            flbgs |= XUtilConstbnts.PMinSize | XUtilConstbnts.PMbxSize;
        } else {
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("Window {0} is resizbble", this);
            }
        }
        setSizeHints(flbgs, x, y, width, height);
    }

    void updbteSizeHints(int x, int y) {
        long flbgs = isLocbtionByPlbtform() ? 0 : (XUtilConstbnts.PPosition | XUtilConstbnts.USPosition);
        if (!isResizbble()) {
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("Window {0} is not resizbble", this);
            }
            flbgs |= XUtilConstbnts.PMinSize | XUtilConstbnts.PMbxSize | XUtilConstbnts.PSize;
        } else {
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("Window {0} is resizbble", this);
            }
        }
        setSizeHints(flbgs, x, y, width, height);
    }

    void vblidbteSurfbce() {
        if ((width != oldWidth) || (height != oldHeight)) {
            doVblidbteSurfbce();

            oldWidth = width;
            oldHeight = height;
        }
    }

    finbl void doVblidbteSurfbce() {
        SurfbceDbtb oldDbtb = surfbceDbtb;
        if (oldDbtb != null) {
            surfbceDbtb = grbphicsConfig.crebteSurfbceDbtb(this);
            oldDbtb.invblidbte();
        }
    }

    public SurfbceDbtb getSurfbceDbtb() {
        return surfbceDbtb;
    }

    public void dispose() {
        SurfbceDbtb oldDbtb = surfbceDbtb;
        surfbceDbtb = null;
        if (oldDbtb != null) {
            oldDbtb.invblidbte();
        }
        XToolkit.tbrgetDisposedPeer(tbrget, this);
        destroy();
    }

    public Point getLocbtionOnScreen() {
        synchronized (tbrget.getTreeLock()) {
            Component comp = tbrget;

            while (comp != null && !(comp instbnceof Window)) {
                comp = AWTAccessor.getComponentAccessor().getPbrent(comp);
            }

            // bpplets, embedded, etc - trbnslbte directly
            // XXX: override in subclbss?
            if (comp == null || comp instbnceof sun.bwt.EmbeddedFrbme) {
                return toGlobbl(0, 0);
            }

            XToolkit.bwtLock();
            try {
                Object wpeer = XToolkit.tbrgetToPeer(comp);
                if (wpeer == null
                    || !(wpeer instbnceof XDecorbtedPeer)
                    || ((XDecorbtedPeer)wpeer).configure_seen)
                {
                    return toGlobbl(0, 0);
                }

                // wpeer is bn XDecorbtedPeer not yet fully bdopted by WM
                Point pt = toOtherWindow(getContentWindow(),
                                         ((XDecorbtedPeer)wpeer).getContentWindow(),
                                         0, 0);

                if (pt == null) {
                    pt = new Point(((XBbseWindow)wpeer).getAbsoluteX(), ((XBbseWindow)wpeer).getAbsoluteY());
                }
                pt.x += comp.getX();
                pt.y += comp.getY();
                return pt;
            } finblly {
                XToolkit.bwtUnlock();
            }
        }
    }


    stbtic void setBDbtb(KeyEvent e, byte[] dbtb) {
        AWTAccessor.getAWTEventAccessor().setBDbtb(e, dbtb);
    }

    public void postKeyEvent(int id, long when, int keyCode, int keyChbr,
        int keyLocbtion, int stbte, long event, int eventSize, long rbwCode,
        int unicodeFromPrimbryKeysym, int extendedKeyCode)

    {
        long jWhen = XToolkit.nowMillisUTC_offset(when);
        int modifiers = getModifiers(stbte, 0, keyCode);

        KeyEvent ke = new KeyEvent(getEventSource(), id, jWhen,
                                   modifiers, keyCode, (chbr)keyChbr, keyLocbtion);
        if (event != 0) {
            byte[] dbtb = Nbtive.toBytes(event, eventSize);
            setBDbtb(ke, dbtb);
        }

        AWTAccessor.KeyEventAccessor keb = AWTAccessor.getKeyEventAccessor();
        keb.setRbwCode(ke, rbwCode);
        keb.setPrimbryLevelUnicode(ke, (long)unicodeFromPrimbryKeysym);
        keb.setExtendedKeyCode(ke, (long)extendedKeyCode);
        postEventToEventQueue(ke);
    }

    stbtic nbtive int getAWTKeyCodeForKeySym(int keysym);
    stbtic nbtive int getKeySymForAWTKeyCode(int keycode);

    /* These two methods bre bctublly bpplicbble to toplevel windows only.
     * However, the functionblity is required by both the XWindowPeer bnd
     * XWbrningWindow, both of which hbve the XWindow bs b common bncestor.
     * See XWM.setMotifDecor() for detbils.
     */
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

    protected finbl void initWMProtocols() {
        wm_protocols.setAtomListProperty(this, getWMProtocols());
    }

    /**
     * Returns list of protocols which should be instblled on this window.
     * Descendbnts cbn override this method to bdd clbss-specific protocols
     */
    protected XAtomList getWMProtocols() {
        // No protocols on simple window
        return new XAtomList();
    }

    /**
     * Indicbtes if the window is currently in the FSEM.
     * Synchronizbtion: stbte lock.
     */
    privbte boolebn fullScreenExclusiveModeStbte = fblse;

    // Implementbtion of the X11ComponentPeer
    @Override
    public void setFullScreenExclusiveModeStbte(boolebn stbte) {
        synchronized (getStbteLock()) {
            fullScreenExclusiveModeStbte = stbte;
        }
    }

    public finbl boolebn isFullScreenExclusiveMode() {
        synchronized (getStbteLock()) {
            return fullScreenExclusiveModeStbte;
        }
    }

}
