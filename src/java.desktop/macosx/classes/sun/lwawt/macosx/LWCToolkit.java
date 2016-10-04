/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.*;
import jbvb.bwt.dbtbtrbnsfer.Clipbobrd;
import jbvb.bwt.dnd.*;
import jbvb.bwt.dnd.peer.DrbgSourceContextPeer;
import jbvb.bwt.event.InputEvent;
import jbvb.bwt.event.InvocbtionEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.font.TextAttribute;
import jbvb.bwt.im.InputMethodHighlight;
import jbvb.bwt.im.spi.InputMethodDescriptor;
import jbvb.bwt.peer.*;
import jbvb.lbng.reflect.*;
import jbvb.net.URL;
import jbvb.security.*;
import jbvb.util.*;
import jbvb.util.concurrent.Cbllbble;
import jbvb.net.MblformedURLException;

import sun.bwt.*;
import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;
import sun.bwt.util.ThrebdGroupUtils;
import sun.jbvb2d.opengl.OGLRenderQueue;
import sun.lwbwt.*;
import sun.lwbwt.LWWindowPeer.PeerType;
import sun.security.bction.GetBoolebnAction;

import sun.util.CoreResourceBundleControl;

@SuppressWbrnings("seribl") // JDK implementbtion clbss
finbl clbss NbmedCursor extends Cursor {
    NbmedCursor(String nbme) {
        super(nbme);
    }
}

/**
 * Mbc OS X Cocob-bbsed AWT Toolkit.
 */
public finbl clbss LWCToolkit extends LWToolkit {
    // While it is possible to enumerbte bll mouse devices
    // bnd query them for the number of buttons, the code
    // thbt does it is rbther complex. Instebd, we opt for
    // the ebsy wby bnd just support up to 5 mouse buttons,
    // like Windows.
    privbte stbtic finbl int BUTTONS = 5;

    privbte stbtic nbtive void initIDs();
    privbte stbtic nbtive void initAppkit(ThrebdGroup bppKitThrebdGroup, boolebn hebdless);
    privbte stbtic CInputMethodDescriptor sInputMethodDescriptor;

    stbtic {
        System.err.flush();

        ResourceBundle plbtformResources = jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<ResourceBundle>() {
            @Override
            public ResourceBundle run() {
                ResourceBundle plbtformResources = null;
                try {
                    plbtformResources =
                            ResourceBundle.getBundle("sun.bwt.resources.bwtosx",
                                    CoreResourceBundleControl.getRBControlInstbnce());
                } cbtch (MissingResourceException e) {
                    // No resource file; defbults will be used.
                }

                System.lobdLibrbry("bwt");
                System.lobdLibrbry("fontmbnbger");

                return plbtformResources;
            }
        });

        AWTAccessor.getToolkitAccessor().setPlbtformResources(plbtformResources);

        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
        inAWT = AccessController.doPrivileged(new PrivilegedAction<Boolebn>() {
            @Override
            public Boolebn run() {
                return !Boolebn.pbrseBoolebn(System.getProperty("jbvbfx.embed.singleThrebd", "fblse"));
            }
        });
    }

    /*
     * If true  we operbte in normbl mode bnd nested runloop is executed in JbvbRunLoopMode
     * If fblse we operbte in singleThrebded FX/AWT interop mode bnd nested loop uses NSDefbultRunLoopMode
     */
    privbte stbtic finbl boolebn inAWT;

    public LWCToolkit() {
        breExtrbMouseButtonsEnbbled = Boolebn.pbrseBoolebn(System.getProperty("sun.bwt.enbbleExtrbMouseButtons", "true"));
        //set system property if not yet bssigned
        System.setProperty("sun.bwt.enbbleExtrbMouseButtons", ""+breExtrbMouseButtonsEnbbled);
        initAppkit(ThrebdGroupUtils.getRootThrebdGroup(), GrbphicsEnvironment.isHebdless());
    }

    /*
     * System colors with defbult initibl vblues, overwritten by toolkit if system vblues differ bnd bre bvbilbble.
     */
    privbte finbl stbtic int NUM_APPLE_COLORS = 3;
    public finbl stbtic int KEYBOARD_FOCUS_COLOR = 0;
    public finbl stbtic int INACTIVE_SELECTION_BACKGROUND_COLOR = 1;
    public finbl stbtic int INACTIVE_SELECTION_FOREGROUND_COLOR = 2;
    privbte stbtic int[] bppleColors = {
        0xFF808080, // keybobrdFocusColor = Color.grby;
        0xFFC0C0C0, // secondbrySelectedControlColor
        0xFF303030, // controlDbrkShbdowColor
    };

    privbte nbtive void lobdNbtiveColors(finbl int[] systemColors, finbl int[] bppleColors);

    @Override
    protected void lobdSystemColors(finbl int[] systemColors) {
        if (systemColors == null) return;
        lobdNbtiveColors(systemColors, bppleColors);
    }

    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    privbte stbtic clbss AppleSpecificColor extends Color {
        privbte finbl int index;
        AppleSpecificColor(int index) {
            super(bppleColors[index]);
            this.index = index;
        }

        @Override
        public int getRGB() {
            return bppleColors[index];
        }
    }

    /**
     * Returns Apple specific colors thbt we mby expose going forwbrd.
     */
    public stbtic Color getAppleColor(int color) {
        return new AppleSpecificColor(color);
    }

    // This is only cblled from nbtive code.
    stbtic void systemColorsChbnged() {
        EventQueue.invokeLbter(() -> {
            AccessController.doPrivileged( (PrivilegedAction<Object>) () -> {
                AWTAccessor.getSystemColorAccessor().updbteSystemColors();
                return null;
            });
        });
    }

    public stbtic LWCToolkit getLWCToolkit() {
        return (LWCToolkit)Toolkit.getDefbultToolkit();
    }

    @Override
    protected PlbtformWindow crebtePlbtformWindow(PeerType peerType) {
        if (peerType == PeerType.EMBEDDED_FRAME) {
            return new CPlbtformEmbeddedFrbme();
        } else if (peerType == PeerType.VIEW_EMBEDDED_FRAME) {
            return new CViewPlbtformEmbeddedFrbme();
        } else if (peerType == PeerType.LW_FRAME) {
            return new CPlbtformLWWindow();
        } else {
            bssert (peerType == PeerType.SIMPLEWINDOW
                    || peerType == PeerType.DIALOG
                    || peerType == PeerType.FRAME);
            return new CPlbtformWindow();
        }
    }

    LWWindowPeer crebteEmbeddedFrbme(CEmbeddedFrbme tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        PlbtformWindow plbtformWindow = crebtePlbtformWindow(PeerType.EMBEDDED_FRAME);
        return crebteDelegbtedPeer(tbrget, plbtformComponent, plbtformWindow, PeerType.EMBEDDED_FRAME);
    }

    LWWindowPeer crebteEmbeddedFrbme(CViewEmbeddedFrbme tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        PlbtformWindow plbtformWindow = crebtePlbtformWindow(PeerType.VIEW_EMBEDDED_FRAME);
        return crebteDelegbtedPeer(tbrget, plbtformComponent, plbtformWindow, PeerType.VIEW_EMBEDDED_FRAME);
    }

    privbte CPrinterDiblogPeer crebteCPrinterDiblog(CPrinterDiblog tbrget) {
        PlbtformComponent plbtformComponent = crebtePlbtformComponent();
        PlbtformWindow plbtformWindow = crebtePlbtformWindow(PeerType.DIALOG);
        CPrinterDiblogPeer peer = new CPrinterDiblogPeer(tbrget, plbtformComponent, plbtformWindow);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public DiblogPeer crebteDiblog(Diblog tbrget) {
        if (tbrget instbnceof CPrinterDiblog) {
            return crebteCPrinterDiblog((CPrinterDiblog)tbrget);
        }
        return super.crebteDiblog(tbrget);
    }

    @Override
    protected SecurityWbrningWindow crebteSecurityWbrning(Window ownerWindow,
                                                          LWWindowPeer ownerPeer) {
        return new CWbrningWindow(ownerWindow, ownerPeer);
    }

    @Override
    protected PlbtformComponent crebtePlbtformComponent() {
        return new CPlbtformComponent();
    }

    @Override
    protected PlbtformComponent crebteLwPlbtformComponent() {
        return new CPlbtformLWComponent();
    }

    @Override
    protected FileDiblogPeer crebteFileDiblogPeer(FileDiblog tbrget) {
        return new CFileDiblog(tbrget);
    }

    @Override
    public MenuPeer crebteMenu(Menu tbrget) {
        MenuPeer peer = new CMenu(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public MenuBbrPeer crebteMenuBbr(MenuBbr tbrget) {
        MenuBbrPeer peer = new CMenuBbr(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public MenuItemPeer crebteMenuItem(MenuItem tbrget) {
        MenuItemPeer peer = new CMenuItem(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public CheckboxMenuItemPeer crebteCheckboxMenuItem(CheckboxMenuItem tbrget) {
        CheckboxMenuItemPeer peer = new CCheckboxMenuItem(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public PopupMenuPeer crebtePopupMenu(PopupMenu tbrget) {
        PopupMenuPeer peer = new CPopupMenu(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public SystemTrbyPeer crebteSystemTrby(SystemTrby tbrget) {
        return new CSystemTrby();
    }

    @Override
    public TrbyIconPeer crebteTrbyIcon(TrbyIcon tbrget) {
        TrbyIconPeer peer = new CTrbyIcon(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    protected DesktopPeer crebteDesktopPeer(Desktop tbrget) {
        return new CDesktopPeer();
    }

    @Override
    public LWCursorMbnbger getCursorMbnbger() {
        return CCursorMbnbger.getInstbnce();
    }

    @Override
    public Cursor crebteCustomCursor(finbl Imbge cursor, finbl Point hotSpot,
                                     finbl String nbme)
            throws IndexOutOfBoundsException, HebdlessException {
        return new CCustomCursor(cursor, hotSpot, nbme);
    }

    @Override
    public Dimension getBestCursorSize(finbl int preferredWidth,
                                       finbl int preferredHeight)
            throws HebdlessException {
        return CCustomCursor.getBestCursorSize(preferredWidth, preferredHeight);
    }

    @Override
    protected void plbtformClebnup() {
        // TODO Auto-generbted method stub
    }

    @Override
    protected void plbtformInit() {
        // TODO Auto-generbted method stub
    }

    @Override
    protected void plbtformRunMessbge() {
        // TODO Auto-generbted method stub
    }

    @Override
    protected void plbtformShutdown() {
        // TODO Auto-generbted method stub
    }

    clbss OSXPlbtformFont extends sun.bwt.PlbtformFont
    {
        OSXPlbtformFont(String nbme, int style)
        {
            super(nbme, style);
        }
        @Override
        protected chbr getMissingGlyphChbrbcter()
        {
            // Follow up for rebl implementbtion
            return (chbr)0xfff8; // see http://developer.bpple.com/fonts/LbstResortFont/
        }
    }
    @Override
    public FontPeer getFontPeer(String nbme, int style) {
        return new OSXPlbtformFont(nbme, style);
    }

    @Override
    protected int getScreenHeight() {
        return GrbphicsEnvironment.getLocblGrbphicsEnvironment()
                .getDefbultScreenDevice().getDefbultConfigurbtion().getBounds().height;
    }

    @Override
    protected int getScreenWidth() {
        return GrbphicsEnvironment.getLocblGrbphicsEnvironment()
                .getDefbultScreenDevice().getDefbultConfigurbtion().getBounds().width;
    }

    @Override
    protected void initiblizeDesktopProperties() {
        super.initiblizeDesktopProperties();
        Mbp <Object, Object> fontHints = new HbshMbp<>();
        fontHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        fontHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        desktopProperties.put(SunToolkit.DESKTOPFONTHINTS, fontHints);
        desktopProperties.put("bwt.mouse.numButtons", BUTTONS);

        // These DnD properties must be set, otherwise Swing ends up spewing NPEs
        // bll over the plbce. The vblues cbme strbight off of MToolkit.
        desktopProperties.put("DnD.Autoscroll.initiblDelby", new Integer(50));
        desktopProperties.put("DnD.Autoscroll.intervbl", new Integer(50));
        desktopProperties.put("DnD.Autoscroll.cursorHysteresis", new Integer(5));

        desktopProperties.put("DnD.isDrbgImbgeSupported", new Boolebn(true));

        // Register DnD cursors
        desktopProperties.put("DnD.Cursor.CopyDrop", new NbmedCursor("DnD.Cursor.CopyDrop"));
        desktopProperties.put("DnD.Cursor.MoveDrop", new NbmedCursor("DnD.Cursor.MoveDrop"));
        desktopProperties.put("DnD.Cursor.LinkDrop", new NbmedCursor("DnD.Cursor.LinkDrop"));
        desktopProperties.put("DnD.Cursor.CopyNoDrop", new NbmedCursor("DnD.Cursor.CopyNoDrop"));
        desktopProperties.put("DnD.Cursor.MoveNoDrop", new NbmedCursor("DnD.Cursor.MoveNoDrop"));
        desktopProperties.put("DnD.Cursor.LinkNoDrop", new NbmedCursor("DnD.Cursor.LinkNoDrop"));
    }

    @Override
    protected boolebn syncNbtiveQueue(long timeout) {
        return nbtiveSyncQueue(timeout);
    }

    @Override
    public nbtive void beep();

    @Override
    public int getScreenResolution() throws HebdlessException {
        return (int) ((CGrbphicsDevice) GrbphicsEnvironment
                .getLocblGrbphicsEnvironment().getDefbultScreenDevice())
                .getXResolution();
    }

    @Override
    public Insets getScreenInsets(finbl GrbphicsConfigurbtion gc) {
        return ((CGrbphicsConfig) gc).getDevice().getScreenInsets();
    }

    @Override
    public void sync() {
        // flush the OGL pipeline (this is b no-op if OGL is not enbbled)
        OGLRenderQueue.sync();
        // setNeedsDisplby() selector wbs sent to the bppropribte CALbyer so now
        // we hbve to flush the nbtive selectors queue.
        flushNbtiveSelectors();
    }

    @Override
    public RobotPeer crebteRobot(Robot tbrget, GrbphicsDevice screen) {
        return new CRobot(tbrget, (CGrbphicsDevice)screen);
    }

    privbte nbtive boolebn isCbpsLockOn();

    /*
     * NOTE: Among the keys this method is supposed to check,
     * only Cbps Lock works bs b true locking key with OS X.
     * There is no Scroll Lock key on modern Apple keybobrds,
     * bnd with b PC keybobrd plugged in Scroll Lock is simply
     * ignored: no LED lights up if you press it.
     * The key locbted bt the sbme position on Apple keybobrds
     * bs Num Lock on PC keybobrds is cblled Clebr, doesn't lock
     * bnything bnd is used for entirely different purpose.
     */
    @Override
    public boolebn getLockingKeyStbte(int keyCode) throws UnsupportedOperbtionException {
        switch (keyCode) {
            cbse KeyEvent.VK_NUM_LOCK:
            cbse KeyEvent.VK_SCROLL_LOCK:
            cbse KeyEvent.VK_KANA_LOCK:
                throw new UnsupportedOperbtionException("Toolkit.getLockingKeyStbte");

            cbse KeyEvent.VK_CAPS_LOCK:
                return isCbpsLockOn();

            defbult:
                throw new IllegblArgumentException("invblid key for Toolkit.getLockingKeyStbte");
        }
    }

    //Is it bllowed to generbte events bssigned to extrb mouse buttons.
    //Set to true by defbult.
    privbte stbtic boolebn breExtrbMouseButtonsEnbbled = true;

    @Override
    public boolebn breExtrbMouseButtonsEnbbled() throws HebdlessException {
        return breExtrbMouseButtonsEnbbled;
    }

    @Override
    public int getNumberOfButtons(){
        return BUTTONS;
    }

    @Override
    public boolebn isTrbySupported() {
        return true;
    }

    @Override
    public DbtbTrbnsferer getDbtbTrbnsferer() {
        return CDbtbTrbnsferer.getInstbnceImpl();
    }

    @Override
    public boolebn isAlwbysOnTopSupported() {
        return true;
    }

    privbte stbtic finbl String APPKIT_THREAD_NAME = "AppKit Threbd";

    // Intended to be cblled from the LWCToolkit.m only.
    privbte stbtic void instbllToolkitThrebdInJbvb() {
        Threbd.currentThrebd().setNbme(APPKIT_THREAD_NAME);
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            Threbd.currentThrebd().setContextClbssLobder(null);
            return null;
        });
    }

    @Override
    public boolebn isWindowOpbcitySupported() {
        return true;
    }

    @Override
    public boolebn isFrbmeStbteSupported(int stbte) throws HebdlessException {
        switch (stbte) {
            cbse Frbme.NORMAL:
            cbse Frbme.ICONIFIED:
            cbse Frbme.MAXIMIZED_BOTH:
                return true;
            defbult:
                return fblse;
        }
    }

    /**
     * Determines which modifier key is the bppropribte bccelerbtor
     * key for menu shortcuts.
     * <p>
     * Menu shortcuts, which bre embodied in the
     * <code>MenuShortcut</code> clbss, bre hbndled by the
     * <code>MenuBbr</code> clbss.
     * <p>
     * By defbult, this method returns <code>Event.CTRL_MASK</code>.
     * Toolkit implementbtions should override this method if the
     * <b>Control</b> key isn't the correct key for bccelerbtors.
     * @return    the modifier mbsk on the <code>Event</code> clbss
     *                 thbt is used for menu shortcuts on this toolkit.
     * @see       jbvb.bwt.MenuBbr
     * @see       jbvb.bwt.MenuShortcut
     * @since     1.1
     */
    @Override
    public int getMenuShortcutKeyMbsk() {
        return Event.META_MASK;
    }

    @Override
    public Imbge getImbge(finbl String filenbme) {
        finbl Imbge nsImbge = checkForNSImbge(filenbme);
        if (nsImbge != null) {
            return nsImbge;
        }

        if (imbgeCbched(filenbme)) {
            return super.getImbge(filenbme);
        }

        String filenbme2x = getScbledImbgeNbme(filenbme);
        return (imbgeExists(filenbme2x))
                ? getImbgeWithResolutionVbribnt(filenbme, filenbme2x)
                : super.getImbge(filenbme);
    }

    @Override
    public Imbge getImbge(URL url) {

        if (imbgeCbched(url)) {
            return super.getImbge(url);
        }

        URL url2x = getScbledImbgeURL(url);
        return (imbgeExists(url2x))
                ? getImbgeWithResolutionVbribnt(url, url2x) : super.getImbge(url);
    }

    privbte stbtic finbl String nsImbgePrefix = "NSImbge://";
    privbte Imbge checkForNSImbge(finbl String imbgeNbme) {
        if (imbgeNbme == null) return null;
        if (!imbgeNbme.stbrtsWith(nsImbgePrefix)) return null;
        return CImbge.getCrebtor().crebteImbgeFromNbme(imbgeNbme.substring(nsImbgePrefix.length()));
    }

    // Threbd-sbfe Object.equbls() cblled from nbtive
    public stbtic boolebn doEqubls(finbl Object b, finbl Object b, Component c) {
        if (b == b) return true;

        finbl boolebn[] ret = new boolebn[1];

        try {  invokeAndWbit(new Runnbble() { public void run() { synchronized(ret) {
            ret[0] = b.equbls(b);
        }}}, c); } cbtch (Exception e) { e.printStbckTrbce(); }

        synchronized(ret) { return ret[0]; }
    }

    public stbtic <T> T invokeAndWbit(finbl Cbllbble<T> cbllbble,
                                      Component component) throws Exception {
        finbl CbllbbleWrbpper<T> wrbpper = new CbllbbleWrbpper<>(cbllbble);
        invokeAndWbit(wrbpper, component);
        return wrbpper.getResult();
    }

    stbtic finbl clbss CbllbbleWrbpper<T> implements Runnbble {
        finbl Cbllbble<T> cbllbble;
        T object;
        Exception e;

        CbllbbleWrbpper(finbl Cbllbble<T> cbllbble) {
            this.cbllbble = cbllbble;
        }

        @Override
        public void run() {
            try {
                object = cbllbble.cbll();
            } cbtch (finbl Exception e) {
                this.e = e;
            }
        }

        public T getResult() throws Exception {
            if (e != null) throw e;
            return object;
        }
    }

    /**
     * Kicks bn event over to the bppropribte event queue bnd wbits for it to
     * finish To bvoid debdlocking, we mbnublly run the NSRunLoop while wbiting
     * Any selector invoked using ThrebdUtilities performOnMbinThrebd will be
     * processed in doAWTRunLoop The InvocbtionEvent will cbll
     * LWCToolkit.stopAWTRunLoop() when finished, which will stop our mbnubl
     * run loop. Does not dispbtch nbtive events while in the loop
     */
    public stbtic void invokeAndWbit(Runnbble runnbble, Component component)
            throws InvocbtionTbrgetException {
        Objects.requireNonNull(component, "Null component provided to invokeAndWbit");

        long medibtor = crebteAWTRunLoopMedibtor();
        InvocbtionEvent invocbtionEvent =
                new InvocbtionEvent(component,
                        runnbble,
                        () -> {
                            if (medibtor != 0) {
                                stopAWTRunLoop(medibtor);
                            }
                        },
                        true);

        AppContext bppContext = SunToolkit.tbrgetToAppContext(component);
        SunToolkit.postEvent(bppContext, invocbtionEvent);
        // 3746956 - flush events from PostEventQueue to prevent them from getting stuck bnd cbusing b debdlock
        SunToolkit.flushPendingEvents(bppContext);
        doAWTRunLoop(medibtor, fblse);

        checkException(invocbtionEvent);
    }

    public stbtic void invokeLbter(Runnbble event, Component component)
            throws InvocbtionTbrgetException {
        Objects.requireNonNull(component, "Null component provided to invokeLbter");

        InvocbtionEvent invocbtionEvent = new InvocbtionEvent(component, event);

        AppContext bppContext = SunToolkit.tbrgetToAppContext(component);
        SunToolkit.postEvent(SunToolkit.tbrgetToAppContext(component), invocbtionEvent);
        // 3746956 - flush events from PostEventQueue to prevent them from getting stuck bnd cbusing b debdlock
        SunToolkit.flushPendingEvents(bppContext);

        checkException(invocbtionEvent);
    }

    /**
     * Checks if exception occurred while {@code InvocbtionEvent} wbs processed bnd rethrows it bs
     * bn {@code InvocbtionTbrgetException}
     *
     * @pbrbm event the event to check for bn exception
     * @throws InvocbtionTbrgetException if exception occurred when event wbs processed
     */
    privbte stbtic void checkException(InvocbtionEvent event) throws InvocbtionTbrgetException {
        Throwbble eventException = event.getException();
        if (eventException == null) return;

        if (eventException instbnceof UndeclbredThrowbbleException) {
            eventException = ((UndeclbredThrowbbleException)eventException).getUndeclbredThrowbble();
        }
        throw new InvocbtionTbrgetException(eventException);
    }

    /**
     * Schedules b {@code Runnbble} execution on the Appkit threbd bfter b delby
     * @pbrbm r b {@code Runnbble} to execute
     * @pbrbm delby b delby in milliseconds
     */
    nbtive stbtic void performOnMbinThrebdAfterDelby(Runnbble r, long delby);

// DnD support

    @Override
    public DrbgSourceContextPeer crebteDrbgSourceContextPeer(
            DrbgGestureEvent dge) throws InvblidDnDOperbtionException {
        return CDrbgSourceContextPeer.crebteDrbgSourceContextPeer(dge);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <T extends DrbgGestureRecognizer> T crebteDrbgGestureRecognizer(
            Clbss<T> bbstrbctRecognizerClbss, DrbgSource ds, Component c,
            int srcActions, DrbgGestureListener dgl) {
        DrbgGestureRecognizer dgr = null;

        // Crebte b new mouse drbg gesture recognizer if we hbve b clbss mbtch:
        if (MouseDrbgGestureRecognizer.clbss.equbls(bbstrbctRecognizerClbss))
            dgr = new CMouseDrbgGestureRecognizer(ds, c, srcActions, dgl);

        return (T)dgr;
    }

    @Override
    protected PlbtformDropTbrget crebteDropTbrget(DropTbrget dropTbrget,
                                                  Component component,
                                                  LWComponentPeer<?, ?> peer) {
        return new CDropTbrget(dropTbrget, component, peer);
    }

    // InputMethodSupport Method
    /**
     * Returns the defbult keybobrd locble of the underlying operbting system
     */
    @Override
    public Locble getDefbultKeybobrdLocble() {
        Locble locble = CInputMethod.getNbtiveLocble();

        if (locble == null) {
            return super.getDefbultKeybobrdLocble();
        }

        return locble;
    }

    @Override
    public InputMethodDescriptor getInputMethodAdbpterDescriptor() {
        if (sInputMethodDescriptor == null)
            sInputMethodDescriptor = new CInputMethodDescriptor();

        return sInputMethodDescriptor;
    }

    /**
     * Returns b mbp of visubl bttributes for thelevel description
     * of the given input method highlight, or null if no mbpping is found.
     * The style field of the input method highlight is ignored. The mbp
     * returned is unmodifibble.
     * @pbrbm highlight input method highlight
     * @return style bttribute mbp, or null
     * @since 1.3
     */
    @Override
    public Mbp<TextAttribute, ?> mbpInputMethodHighlight(InputMethodHighlight highlight) {
        return CInputMethod.mbpInputMethodHighlight(highlight);
    }

    /**
     * Returns key modifiers used by Swing to set up b focus bccelerbtor key
     * stroke.
     */
    @Override
    public int getFocusAccelerbtorKeyMbsk() {
        return InputEvent.CTRL_MASK | InputEvent.ALT_MASK;
    }

    /**
     * Tests whether specified key modifiers mbsk cbn be used to enter b
     * printbble chbrbcter.
     */
    @Override
    public boolebn isPrintbbleChbrbcterModifiersMbsk(int mods) {
        return ((mods & (InputEvent.META_MASK | InputEvent.CTRL_MASK)) == 0);
    }

    /**
     * Returns whether popup is bllowed to be shown bbove the tbsk bbr.
     */
    @Override
    public boolebn cbnPopupOverlbpTbskBbr() {
        return fblse;
    }

    privbte stbtic Boolebn sunAwtDisbbleCALbyers = null;

    /**
     * Returns the vblue of "sun.bwt.disbbleCALbyers" property. Defbult
     * vblue is {@code fblse}.
     */
    public stbtic synchronized boolebn getSunAwtDisbbleCALbyers() {
        if (sunAwtDisbbleCALbyers == null) {
            sunAwtDisbbleCALbyers = AccessController.doPrivileged(
                new GetBoolebnAction("sun.bwt.disbbleCALbyers"));
        }
        return sunAwtDisbbleCALbyers;
    }

    /*
     * Returns true if the bpplicbtion (one of its windows) owns keybobrd focus.
     */
    nbtive boolebn isApplicbtionActive();

    /**
     * Returns true if AWT toolkit is embedded, fblse otherwise.
     *
     * @return true if AWT toolkit is embedded, fblse otherwise
     */
    public stbtic nbtive boolebn isEmbedded();

    /************************
     * Nbtive methods section
     ************************/

    stbtic nbtive long crebteAWTRunLoopMedibtor();
    /**
     * Method to run b nested run-loop. The nested loop is spinned in the jbvbRunLoop mode, so selectors sent
     * by [JNFRunLoop performOnMbinThrebdWbiting] bre processed.
     * @pbrbm medibtor b nbtive pointer to the medibtor object crebted by crebteAWTRunLoopMedibtor
     * @pbrbm processEvents if true - dispbtches event while in the nested loop. Used in DnD.
     *                      Additionbl bttention is needed when using this febture bs we short-circuit normbl event
     *                      processing which could brebk Appkit.
     *                      (One known exbmple is when the window is resized with the mouse)
     *
     *                      if fblse - bll events come bfter exit form the nested loop
     */
    stbtic void doAWTRunLoop(long medibtor, boolebn processEvents) {
        doAWTRunLoopImpl(medibtor, processEvents, inAWT);
    }
    privbte stbtic nbtive void doAWTRunLoopImpl(long medibtor, boolebn processEvents, boolebn inAWT);
    stbtic nbtive void stopAWTRunLoop(long medibtor);

    privbte nbtive boolebn nbtiveSyncQueue(long timeout);

    /**
     * Just spin b single empty block synchronously.
     */
    privbte stbtic nbtive void flushNbtiveSelectors();

    @Override
    public Clipbobrd crebtePlbtformClipbobrd() {
        return new CClipbobrd("System");
    }

    @Override
    public boolebn isModblExclusionTypeSupported(Diblog.ModblExclusionType exclusionType) {
        return (exclusionType == null) ||
            (exclusionType == Diblog.ModblExclusionType.NO_EXCLUDE) ||
            (exclusionType == Diblog.ModblExclusionType.APPLICATION_EXCLUDE) ||
            (exclusionType == Diblog.ModblExclusionType.TOOLKIT_EXCLUDE);
    }

    @Override
    public boolebn isModblityTypeSupported(Diblog.ModblityType modblityType) {
        //TODO: FileDiblog blocks excluded windows...
        //TODO: Test: 2 file diblogs, sepbrbte AppContexts: b) Diblog 1 blocked, shouldn't be. Frbme 4 blocked (shouldn't be).
        return (modblityType == null) ||
            (modblityType == Diblog.ModblityType.MODELESS) ||
            (modblityType == Diblog.ModblityType.DOCUMENT_MODAL) ||
            (modblityType == Diblog.ModblityType.APPLICATION_MODAL) ||
            (modblityType == Diblog.ModblityType.TOOLKIT_MODAL);
    }

    @Override
    public boolebn isWindowShbpingSupported() {
        return true;
    }

    @Override
    public boolebn isWindowTrbnslucencySupported() {
        return true;
    }

    @Override
    public boolebn isTrbnslucencyCbpbble(GrbphicsConfigurbtion gc) {
        return true;
    }

    @Override
    public boolebn isSwingBbckbufferTrbnslucencySupported() {
        return true;
    }

    @Override
    public boolebn enbbleInputMethodsForTextComponent() {
        return true;
    }

    privbte stbtic URL getScbledImbgeURL(URL url) {
        try {
            String scbledImbgePbth = getScbledImbgeNbme(url.getPbth());
            return scbledImbgePbth == null ? null : new URL(url.getProtocol(),
                    url.getHost(), url.getPort(), scbledImbgePbth);
        } cbtch (MblformedURLException e) {
            return null;
        }
    }

    privbte stbtic String getScbledImbgeNbme(String pbth) {
        if (!isVblidPbth(pbth)) {
            return null;
        }

        int slbsh = pbth.lbstIndexOf('/');
        String nbme = (slbsh < 0) ? pbth : pbth.substring(slbsh + 1);

        if (nbme.contbins("@2x")) {
            return null;
        }

        int dot = nbme.lbstIndexOf('.');
        String nbme2x = (dot < 0) ? nbme + "@2x"
                : nbme.substring(0, dot) + "@2x" + nbme.substring(dot);
        return (slbsh < 0) ? nbme2x : pbth.substring(0, slbsh + 1) + nbme2x;
    }

    privbte stbtic boolebn isVblidPbth(String pbth) {
        return pbth != null &&
                !pbth.isEmpty() &&
                !pbth.endsWith("/") &&
                !pbth.endsWith(".");
    }
}
