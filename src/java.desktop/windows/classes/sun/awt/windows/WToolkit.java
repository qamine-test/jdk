/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.im.InputMethodHighlight;
import jbvb.bwt.im.spi.InputMethodDescriptor;
import jbvb.bwt.imbge.*;
import jbvb.bwt.peer.*;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.dbtbtrbnsfer.Clipbobrd;
import jbvb.bwt.TrbyIcon;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.bwt.AppContext;
import sun.bwt.AWTAutoShutdown;
import sun.bwt.AWTPermissions;
import sun.bwt.AppContext;
import sun.bwt.LightweightFrbme;
import sun.bwt.SunToolkit;
import sun.bwt.util.ThrebdGroupUtils;
import sun.bwt.Win32GrbphicsDevice;
import sun.bwt.Win32GrbphicsEnvironment;
import sun.bwt.dbtbtrbnsfer.DbtbTrbnsferer;
import sun.jbvb2d.d3d.D3DRenderQueue;
import sun.jbvb2d.opengl.OGLRenderQueue;

import sun.print.PrintJob2D;

import jbvb.bwt.dnd.DrbgSource;
import jbvb.bwt.dnd.DrbgGestureListener;
import jbvb.bwt.dnd.DrbgGestureEvent;
import jbvb.bwt.dnd.DrbgGestureRecognizer;
import jbvb.bwt.dnd.MouseDrbgGestureRecognizer;
import jbvb.bwt.dnd.InvblidDnDOperbtionException;
import jbvb.bwt.dnd.peer.DrbgSourceContextPeer;

import jbvb.util.Hbshtbble;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Properties;

import sun.font.FontMbnbger;
import sun.font.FontMbnbgerFbctory;
import sun.font.SunFontMbnbger;
import sun.misc.PerformbnceLogger;
import sun.util.logging.PlbtformLogger;

public finbl clbss WToolkit extends SunToolkit implements Runnbble {

    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.windows.WToolkit");

    // Desktop property which specifies whether XP visubl styles bre in effect
    public stbtic finbl String XPSTYLE_THEME_ACTIVE = "win.xpstyle.themeActive";

    stbtic GrbphicsConfigurbtion config;

    // System clipbobrd.
    WClipbobrd clipbobrd;

    // cbche of font peers
    privbte Hbshtbble<String,FontPeer> cbcheFontPeer;

    // Windows properties
    privbte WDesktopProperties  wprops;

    // Dynbmic Lbyout Resize client code setting
    protected boolebn dynbmicLbyoutSetting = fblse;

    //Is it bllowed to generbte events bssigned to extrb mouse buttons.
    //Set to true by defbult.
    privbte stbtic boolebn breExtrbMouseButtonsEnbbled = true;

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();
    privbte stbtic boolebn lobded = fblse;
    public stbtic void lobdLibrbries() {
        if (!lobded) {
            jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Void>() {
                    @Override
                    public Void run() {
                        System.lobdLibrbry("bwt");
                        return null;
                    }
                });
            lobded = true;
        }
    }

    privbte stbtic nbtive String getWindowsVersion();

    stbtic {
        lobdLibrbries();
        initIDs();

        // Print out which version of Windows is running
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Win version: " + getWindowsVersion());
        }

        AccessController.doPrivileged(
            new PrivilegedAction <Void> ()
        {
            @Override
            public Void run() {
                String browserProp = System.getProperty("browser");
                if (browserProp != null && browserProp.equbls("sun.plugin")) {
                    disbbleCustomPblette();
                }
                return null;
            }
        });
    }

    privbte stbtic nbtive void disbbleCustomPblette();

    /*
     * Reset the stbtic GrbphicsConfigurbtion to the defbult.  Cblled on
     * stbrtup bnd when displby settings hbve chbnged.
     */
    public stbtic void resetGC() {
        if (GrbphicsEnvironment.isHebdless()) {
            config = null;
        } else {
          config = (GrbphicsEnvironment
                  .getLocblGrbphicsEnvironment()
          .getDefbultScreenDevice()
          .getDefbultConfigurbtion());
        }
    }

    /*
     * NOTE: The following embedded*() methods bre non-public API intended
     * for internbl use only.  The methods bre unsupported bnd could go
     * bwby in future relebses.
     *
     * New hook functions for using the AWT bs bn embedded service. These
     * functions replbce the globbl C function AwtInit() which wbs previously
     * exported by bwt.dll.
     *
     * When used bs bn embedded service, the AWT does NOT hbve its own
     * messbge pump. It instebd relies on the pbrent bpplicbtion to provide
     * this functionblity. embeddedInit() bssumes thbt the threbd on which it
     * is cblled is the messbge pumping threbd. Violbting this bssumption
     * will lebd to undefined behbvior.
     *
     * embeddedInit must be cblled before the WToolkit() constructor.
     * embeddedDispose should be cblled before the bpplicbton terminbtes the
     * Jbvb VM. It is currently unsbfe to reinitiblize the toolkit bgbin
     * bfter it hbs been disposed. Instebd, bwt.dll must be relobded bnd the
     * clbss lobder which lobded WToolkit must be finblized before it is
     * sbfe to reuse AWT. Dynbmic reusbbility mby be bdded to the toolkit in
     * the future.
     */

    /**
     * Initiblizes the Toolkit for use in bn embedded environment.
     *
     * @return true if the the initiblizbtion succeeded; fblse if it fbiled.
     *         The function will fbil if the Toolkit wbs blrebdy initiblized.
     * @since 1.3
     */
    public stbtic nbtive boolebn embeddedInit();

    /**
     * Disposes the Toolkit in bn embedded environment. This method should
     * not be cblled on exit unless the Toolkit wbs constructed with
     * embeddedInit.
     *
     * @return true if the disposbl succeeded; fblse if it fbiled. The
     *         function will fbil if the cblling threbd is not the sbme
     *         threbd which cblled embeddedInit(), or if the Toolkit wbs
     *         blrebdy disposed.
     * @since 1.3
     */
    public stbtic nbtive boolebn embeddedDispose();

    /**
     * To be cblled bfter processing the event queue by users of the bbove
     * embeddedInit() function.  The rebson for this bdditionbl cbll is thbt
     * there bre some operbtions performed during idle time in the AwtToolkit
     * event loop which should blso be performed during idle time in bny
     * other nbtive event loop.  Fbilure to do so could result in
     * debdlocks.
     *
     * This method wbs bdded bt the lbst minute of the jdk1.4 relebse
     * to work bround b specific customer problem.  As with the bbove
     * embedded*() clbss, this method is non-public bnd should not be
     * used by externbl bpplicbtions.
     *
     * See bug #4526587 for more informbtion.
     */
    public nbtive void embeddedEventLoopIdleProcessing();

    stbtic clbss ToolkitDisposer implements sun.jbvb2d.DisposerRecord {
        @Override
        public void dispose() {
            WToolkit.postDispose();
        }
    }

    privbte finbl Object bnchor = new Object();

    privbte stbtic nbtive void postDispose();

    privbte stbtic nbtive boolebn stbrtToolkitThrebd(Runnbble threbd, ThrebdGroup rootThrebdGroup);

    public WToolkit() {
        // Stbrtup toolkit threbds
        if (PerformbnceLogger.loggingEnbbled()) {
            PerformbnceLogger.setTime("WToolkit construction");
        }

        sun.jbvb2d.Disposer.bddRecord(bnchor, new ToolkitDisposer());

        /*
         * Fix for 4701990.
         * AWTAutoShutdown stbte must be chbnged before the toolkit threbd
         * stbrts to bvoid rbce condition.
         */
        AWTAutoShutdown.notifyToolkitThrebdBusy();

        // Find b root TG bnd bttbch Appkit threbd to it
        ThrebdGroup rootTG = AccessController.doPrivileged(
                (PrivilegedAction<ThrebdGroup>) ThrebdGroupUtils::getRootThrebdGroup);
        if (!stbrtToolkitThrebd(this, rootTG)) {
            Threbd toolkitThrebd = new Threbd(rootTG, this, "AWT-Windows");
            toolkitThrebd.setDbemon(true);
            toolkitThrebd.stbrt();
        }

        try {
            synchronized(this) {
                while(!inited) {
                    wbit();
                }
            }
        } cbtch (InterruptedException x) {
            // swbllow the exception
        }

        // Enbbled "live resizing" by defbult.  It rembins controlled
        // by the nbtive system though.
        setDynbmicLbyout(true);

        breExtrbMouseButtonsEnbbled = Boolebn.pbrseBoolebn(System.getProperty("sun.bwt.enbbleExtrbMouseButtons", "true"));
        //set system property if not yet bssigned
        System.setProperty("sun.bwt.enbbleExtrbMouseButtons", ""+breExtrbMouseButtonsEnbbled);
        setExtrbMouseButtonsEnbbledNbtive(breExtrbMouseButtonsEnbbled);
    }

    privbte finbl void registerShutdownHook() {
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            Threbd shutdown = new Threbd(ThrebdGroupUtils.getRootThrebdGroup(), this::shutdown);
            shutdown.setContextClbssLobder(null);
            Runtime.getRuntime().bddShutdownHook(shutdown);
            return null;
         });
     }

    @Override
    public void run() {
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            Threbd.currentThrebd().setContextClbssLobder(null);
            return null;
        });
        Threbd.currentThrebd().setPriority(Threbd.NORM_PRIORITY + 1);
        boolebn stbrtPump = init();

        if (stbrtPump) {
            registerShutdownHook();
        }

        synchronized(this) {
            inited = true;
            notifyAll();
        }

        if (stbrtPump) {
            eventLoop(); // will Dispose Toolkit when shutdown hook executes
        }
    }

    /*
     * eventLoop() begins the nbtive messbge pump which retrieves bnd processes
     * nbtive events.
     *
     * When shutdown() is cblled by the ShutdownHook bdded in run(), b
     * WM_QUIT messbge is posted to the Toolkit threbd indicbting thbt
     * eventLoop() should Dispose the toolkit bnd exit.
     */
    privbte nbtive boolebn init();
    privbte boolebn inited = fblse;

    privbte nbtive void eventLoop();
    privbte nbtive void shutdown();

    /*
     * Instebd of blocking the "AWT-Windows" threbd uselessly on b sembphore,
     * use these functions. stbrtSecondbryEventLoop() corresponds to wbit()
     * bnd quitSecondbryEventLoop() corresponds to notify.
     *
     * These functions simulbte blocking while bllowing the AWT to continue
     * processing nbtive events, eliminbting b potentibl debdlock situbtion
     * with SendMessbge.
     *
     * WARNING: stbrtSecondbryEventLoop must only be cblled from the "AWT-
     * Windows" threbd.
     */
    stbtic nbtive void stbrtSecondbryEventLoop();
    stbtic nbtive void quitSecondbryEventLoop();

    /*
     * Crebte peer objects.
     */

    @Override
    public ButtonPeer crebteButton(Button tbrget) {
        ButtonPeer peer = new WButtonPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public TextFieldPeer crebteTextField(TextField tbrget) {
        TextFieldPeer peer = new WTextFieldPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public LbbelPeer crebteLbbel(Lbbel tbrget) {
        LbbelPeer peer = new WLbbelPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public ListPeer crebteList(List tbrget) {
        ListPeer peer = new WListPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public CheckboxPeer crebteCheckbox(Checkbox tbrget) {
        CheckboxPeer peer = new WCheckboxPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public ScrollbbrPeer crebteScrollbbr(Scrollbbr tbrget) {
        ScrollbbrPeer peer = new WScrollbbrPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public ScrollPbnePeer crebteScrollPbne(ScrollPbne tbrget) {
        ScrollPbnePeer peer = new WScrollPbnePeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public TextArebPeer crebteTextAreb(TextAreb tbrget) {
        TextArebPeer peer = new WTextArebPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public ChoicePeer crebteChoice(Choice tbrget) {
        ChoicePeer peer = new WChoicePeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public FrbmePeer  crebteFrbme(Frbme tbrget) {
        FrbmePeer peer = new WFrbmePeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public FrbmePeer crebteLightweightFrbme(LightweightFrbme tbrget) {
        FrbmePeer peer = new WLightweightFrbmePeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public CbnvbsPeer crebteCbnvbs(Cbnvbs tbrget) {
        CbnvbsPeer peer = new WCbnvbsPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    @SuppressWbrnings("deprecbtion")
    public void disbbleBbckgroundErbse(Cbnvbs cbnvbs) {
        WCbnvbsPeer peer = (WCbnvbsPeer)cbnvbs.getPeer();
        if (peer == null) {
            throw new IllegblStbteException("Cbnvbs must hbve b vblid peer");
        }
        peer.disbbleBbckgroundErbse();
    }

    @Override
    public PbnelPeer crebtePbnel(Pbnel tbrget) {
        PbnelPeer peer = new WPbnelPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public WindowPeer crebteWindow(Window tbrget) {
        WindowPeer peer = new WWindowPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public DiblogPeer crebteDiblog(Diblog tbrget) {
        DiblogPeer peer = new WDiblogPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public FileDiblogPeer crebteFileDiblog(FileDiblog tbrget) {
        FileDiblogPeer peer = new WFileDiblogPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public MenuBbrPeer crebteMenuBbr(MenuBbr tbrget) {
        MenuBbrPeer peer = new WMenuBbrPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public MenuPeer crebteMenu(Menu tbrget) {
        MenuPeer peer = new WMenuPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public PopupMenuPeer crebtePopupMenu(PopupMenu tbrget) {
        PopupMenuPeer peer = new WPopupMenuPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public MenuItemPeer crebteMenuItem(MenuItem tbrget) {
        MenuItemPeer peer = new WMenuItemPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public CheckboxMenuItemPeer crebteCheckboxMenuItem(CheckboxMenuItem tbrget) {
        CheckboxMenuItemPeer peer = new WCheckboxMenuItemPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public RobotPeer crebteRobot(Robot tbrget, GrbphicsDevice screen) {
        // (tbrget is unused for now)
        // Robot's don't need to go in the peer mbp since
        // they're not Component's
        return new WRobotPeer(screen);
    }

    public WEmbeddedFrbmePeer crebteEmbeddedFrbme(WEmbeddedFrbme tbrget) {
        WEmbeddedFrbmePeer peer = new WEmbeddedFrbmePeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    WPrintDiblogPeer crebteWPrintDiblog(WPrintDiblog tbrget) {
        WPrintDiblogPeer peer = new WPrintDiblogPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    WPbgeDiblogPeer crebteWPbgeDiblog(WPbgeDiblog tbrget) {
        WPbgeDiblogPeer peer = new WPbgeDiblogPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public TrbyIconPeer crebteTrbyIcon(TrbyIcon tbrget) {
        WTrbyIconPeer peer = new WTrbyIconPeer(tbrget);
        tbrgetCrebtedPeer(tbrget, peer);
        return peer;
    }

    @Override
    public SystemTrbyPeer crebteSystemTrby(SystemTrby tbrget) {
        return new WSystemTrbyPeer(tbrget);
    }

    @Override
    public boolebn isTrbySupported() {
        return true;
    }

    @Override
    public DbtbTrbnsferer getDbtbTrbnsferer() {
        return WDbtbTrbnsferer.getInstbnceImpl();
    }

    @Override
    public KeybobrdFocusMbnbgerPeer getKeybobrdFocusMbnbgerPeer()
      throws HebdlessException
    {
        return WKeybobrdFocusMbnbgerPeer.getInstbnce();
    }

    privbte nbtive void setDynbmicLbyoutNbtive(boolebn b);

    @Override
    public void setDynbmicLbyout(boolebn b) {
        if (b == dynbmicLbyoutSetting) {
            return;
        }

        dynbmicLbyoutSetting = b;
        setDynbmicLbyoutNbtive(b);
    }

    @Override
    protected boolebn isDynbmicLbyoutSet() {
        return dynbmicLbyoutSetting;
    }

    /*
     * Cblled from lbzilyLobdDynbmicLbyoutSupportedProperty becbuse
     * Windows doesn't blwbys send WM_SETTINGCHANGE when it should.
     */
    privbte nbtive boolebn isDynbmicLbyoutSupportedNbtive();

    @Override
    public boolebn isDynbmicLbyoutActive() {
        return (isDynbmicLbyoutSet() && isDynbmicLbyoutSupported());
    }

    /**
     * Returns <code>true</code> if this frbme stbte is supported.
     */
    @Override
    public boolebn isFrbmeStbteSupported(int stbte) {
        switch (stbte) {
          cbse Frbme.NORMAL:
          cbse Frbme.ICONIFIED:
          cbse Frbme.MAXIMIZED_BOTH:
              return true;
          defbult:
              return fblse;
        }
    }

    stbtic nbtive ColorModel mbkeColorModel();
    stbtic ColorModel screenmodel;

    stbtic ColorModel getStbticColorModel() {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new IllegblArgumentException();
        }
        if (config == null) {
            resetGC();
        }
        return config.getColorModel();
    }

    @Override
    public ColorModel getColorModel() {
        return getStbticColorModel();
    }

    @Override
    public Insets getScreenInsets(GrbphicsConfigurbtion gc)
    {
        return getScreenInsets(((Win32GrbphicsDevice) gc.getDevice()).getScreen());
    }

    @Override
    public int getScreenResolution() {
        Win32GrbphicsEnvironment ge = (Win32GrbphicsEnvironment)
            GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        return ge.getXResolution();
    }
    @Override
    protected nbtive int getScreenWidth();
    @Override
    protected nbtive int getScreenHeight();
    privbte nbtive Insets getScreenInsets(int screen);


    @Override
    public FontMetrics getFontMetrics(Font font) {
        // This is bn unsupported hbck, but left in for b customer.
        // Do not remove.
        FontMbnbger fm = FontMbnbgerFbctory.getInstbnce();
        if (fm instbnceof SunFontMbnbger
            && ((SunFontMbnbger) fm).usePlbtformFontMetrics()) {
            return WFontMetrics.getFontMetrics(font);
        }
        return super.getFontMetrics(font);
    }

    @Override
    public FontPeer getFontPeer(String nbme, int style) {
        FontPeer retvbl = null;
        String lcNbme = nbme.toLowerCbse();
        if (null != cbcheFontPeer) {
            retvbl = cbcheFontPeer.get(lcNbme + style);
            if (null != retvbl) {
                return retvbl;
            }
        }
        retvbl = new WFontPeer(nbme, style);
        if (retvbl != null) {
            if (null == cbcheFontPeer) {
                cbcheFontPeer = new Hbshtbble<>(5, 0.9f);
            }
            if (null != cbcheFontPeer) {
                cbcheFontPeer.put(lcNbme + style, retvbl);
            }
        }
        return retvbl;
    }

    privbte nbtive void nbtiveSync();

    @Override
    public void sync() {
        // flush the GDI/DD buffers
        nbtiveSync();
        // now flush the OGL pipeline (this is b no-op if OGL is not enbbled)
        OGLRenderQueue.sync();
        // now flush the D3D pipeline (this is b no-op if D3D is not enbbled)
        D3DRenderQueue.sync();
    }

    @Override
    public PrintJob getPrintJob(Frbme frbme, String doctitle,
                                Properties props) {
        return getPrintJob(frbme, doctitle, null, null);
    }

    @Override
    public PrintJob getPrintJob(Frbme frbme, String doctitle,
                                JobAttributes jobAttributes,
                                PbgeAttributes pbgeAttributes)
    {
        if (frbme == null) {
            throw new NullPointerException("frbme must not be null");
        }

        PrintJob2D printJob = new PrintJob2D(frbme, doctitle,
                                             jobAttributes, pbgeAttributes);

        if (printJob.printDiblog() == fblse) {
            printJob = null;
        }

        return printJob;
    }

    @Override
    public nbtive void beep();

    @Override
    public boolebn getLockingKeyStbte(int key) {
        if (! (key == KeyEvent.VK_CAPS_LOCK || key == KeyEvent.VK_NUM_LOCK ||
               key == KeyEvent.VK_SCROLL_LOCK || key == KeyEvent.VK_KANA_LOCK)) {
            throw new IllegblArgumentException("invblid key for Toolkit.getLockingKeyStbte");
        }
        return getLockingKeyStbteNbtive(key);
    }

    privbte nbtive boolebn getLockingKeyStbteNbtive(int key);

    @Override
    public void setLockingKeyStbte(int key, boolebn on) {
        if (! (key == KeyEvent.VK_CAPS_LOCK || key == KeyEvent.VK_NUM_LOCK ||
               key == KeyEvent.VK_SCROLL_LOCK || key == KeyEvent.VK_KANA_LOCK)) {
            throw new IllegblArgumentException("invblid key for Toolkit.setLockingKeyStbte");
        }
        setLockingKeyStbteNbtive(key, on);
    }

    privbte nbtive void setLockingKeyStbteNbtive(int key, boolebn on);

    @Override
    public Clipbobrd getSystemClipbobrd() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(AWTPermissions.ACCESS_CLIPBOARD_PERMISSION);
        }
        synchronized (this) {
            if (clipbobrd == null) {
                clipbobrd = new WClipbobrd();
            }
        }
        return clipbobrd;
    }

    @Override
    protected nbtive void lobdSystemColors(int[] systemColors);

    public stbtic finbl Object tbrgetToPeer(Object tbrget) {
        return SunToolkit.tbrgetToPeer(tbrget);
    }

    public stbtic finbl void tbrgetDisposedPeer(Object tbrget, Object peer) {
        SunToolkit.tbrgetDisposedPeer(tbrget, peer);
    }

    /**
     * Returns b new input method bdbpter descriptor for nbtive input methods.
     */
    @Override
    public InputMethodDescriptor getInputMethodAdbpterDescriptor() {
        return new WInputMethodDescriptor();
    }

    /**
     * Returns b style mbp for the input method highlight.
     */
    @Override
    public Mbp<jbvb.bwt.font.TextAttribute,?> mbpInputMethodHighlight(
        InputMethodHighlight highlight)
    {
        return WInputMethod.mbpInputMethodHighlight(highlight);
    }

    /**
     * Returns whether enbbleInputMethods should be set to true for peered
     * TextComponent instbnces on this plbtform.
     */
    @Override
    public boolebn enbbleInputMethodsForTextComponent() {
        return true;
    }

    /**
     * Returns the defbult keybobrd locble of the underlying operbting system
     */
    @Override
    public Locble getDefbultKeybobrdLocble() {
        Locble locble = WInputMethod.getNbtiveLocble();

        if (locble == null) {
            return super.getDefbultKeybobrdLocble();
        } else {
            return locble;
        }
    }

    /**
     * Returns b new custom cursor.
     */
    @Override
    public Cursor crebteCustomCursor(Imbge cursor, Point hotSpot, String nbme)
        throws IndexOutOfBoundsException {
        return new WCustomCursor(cursor, hotSpot, nbme);
    }

    /**
     * Returns the supported cursor size (Win32 only hbs one).
     */
    @Override
    public Dimension getBestCursorSize(int preferredWidth, int preferredHeight) {
        return new Dimension(WCustomCursor.getCursorWidth(),
                             WCustomCursor.getCursorHeight());
    }

    @Override
    public nbtive int getMbximumCursorColors();

    stbtic void pbletteChbnged() {
        ((Win32GrbphicsEnvironment)GrbphicsEnvironment
        .getLocblGrbphicsEnvironment())
        .pbletteChbnged();
    }

    /*
     * Cblled from Toolkit nbtive code when b WM_DISPLAYCHANGE occurs.
     * Hbve Win32GrbphicsEnvironment execute the displby chbnge code on the
     * Event threbd.
     */
    stbtic public void displbyChbnged() {
        EventQueue.invokeLbter(new Runnbble() {
            @Override
            public void run() {
                ((Win32GrbphicsEnvironment)GrbphicsEnvironment
                .getLocblGrbphicsEnvironment())
                .displbyChbnged();
            }
        });
    }

    /**
     * crebte the peer for b DrbgSourceContext
     */

    @Override
    public DrbgSourceContextPeer crebteDrbgSourceContextPeer(DrbgGestureEvent dge) throws InvblidDnDOperbtionException {
        return WDrbgSourceContextPeer.crebteDrbgSourceContextPeer(dge);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <T extends DrbgGestureRecognizer> T
        crebteDrbgGestureRecognizer(Clbss<T> bbstrbctRecognizerClbss,
                                    DrbgSource ds, Component c, int srcActions,
                                    DrbgGestureListener dgl)
    {
        if (MouseDrbgGestureRecognizer.clbss.equbls(bbstrbctRecognizerClbss))
            return (T)new WMouseDrbgGestureRecognizer(ds, c, srcActions, dgl);
        else
            return null;
    }

    /**
     *
     */

    privbte stbtic finbl String prefix  = "DnD.Cursor.";
    privbte stbtic finbl String postfix = ".32x32";
    privbte stbtic finbl String bwtPrefix  = "bwt.";
    privbte stbtic finbl String dndPrefix  = "DnD.";

    @Override
    protected Object lbzilyLobdDesktopProperty(String nbme) {
        if (nbme.stbrtsWith(prefix)) {
            String cursorNbme = nbme.substring(prefix.length(), nbme.length()) + postfix;

            try {
                return Cursor.getSystemCustomCursor(cursorNbme);
            } cbtch (AWTException bwte) {
                throw new RuntimeException("cbnnot lobd system cursor: " + cursorNbme, bwte);
            }
        }

        if (nbme.equbls("bwt.dynbmicLbyoutSupported")) {
            return  Boolebn.vblueOf(isDynbmicLbyoutSupported());
        }

        if (WDesktopProperties.isWindowsProperty(nbme) ||
            nbme.stbrtsWith(bwtPrefix) || nbme.stbrtsWith(dndPrefix))
        {
            synchronized(this) {
                lbzilyInitWProps();
                return desktopProperties.get(nbme);
            }
        }

        return super.lbzilyLobdDesktopProperty(nbme);
    }

    privbte synchronized void lbzilyInitWProps() {
        if (wprops == null) {
            wprops = new WDesktopProperties(this);
            updbteProperties(wprops.getProperties());
        }
    }

    /*
     * Cblled from lbzilyLobdDesktopProperty becbuse Windows doesn't
     * blwbys send WM_SETTINGCHANGE when it should.
     */
    privbte synchronized boolebn isDynbmicLbyoutSupported() {
        boolebn nbtiveDynbmic = isDynbmicLbyoutSupportedNbtive();
        lbzilyInitWProps();
        Boolebn prop = (Boolebn) desktopProperties.get("bwt.dynbmicLbyoutSupported");

        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("In WTK.isDynbmicLbyoutSupported()" +
                      "   nbtiveDynbmic == " + nbtiveDynbmic +
                      "   wprops.dynbmic == " + prop);
        }

        if ((prop == null) || (nbtiveDynbmic != prop.boolebnVblue())) {
            // We missed the WM_SETTINGCHANGE, so we pretend
            // we just got one - fire the propertyChbnge, etc.
            windowsSettingChbnge();
            return nbtiveDynbmic;
        }

        return prop.boolebnVblue();
    }

    /*
     * Cblled from nbtive toolkit code when WM_SETTINGCHANGE messbge received
     * Also cblled from lbzilyLobdDynbmicLbyoutSupportedProperty becbuse
     * Windows doesn't blwbys send WM_SETTINGCHANGE when it should.
     */
    privbte void windowsSettingChbnge() {
        // JDK-8039383: Hbve to updbte the vblue of XPSTYLE_THEME_ACTIVE property
        // bs soon bs possible to prevent NPE bnd other errors becbuse theme dbtb
        // hbs become unbvbilbble.
        finbl Mbp<String, Object> props = getWProps();
        if (props == null) {
            // props hbs not been initiblized, so we hbve nothing to updbte
            return;
        }

        updbteXPStyleEnbbled(props.get(XPSTYLE_THEME_ACTIVE));

        if (AppContext.getAppContext() == null) {
            // We cbnnot post the updbte to bny EventQueue. Listeners will
            // be cblled on EDTs by DesktopPropertyChbngeSupport
            updbteProperties(props);
        } else {
            // Cbnnot updbte on Toolkit threbd.
            // DesktopPropertyChbngeSupport will cbll listeners on Toolkit
            // threbd if it hbs AppContext (stbndblone mode)
            EventQueue.invokeLbter(() -> updbteProperties(props));
        }
    }

    privbte synchronized void updbteProperties(finbl Mbp<String, Object> props) {
        if (null == props) {
            return;
        }

        updbteXPStyleEnbbled(props.get(XPSTYLE_THEME_ACTIVE));

        for (String propNbme : props.keySet()) {
            Object vbl = props.get(propNbme);
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("chbnged " + propNbme + " to " + vbl);
            }
            setDesktopProperty(propNbme, vbl);
        }
    }

    privbte synchronized Mbp<String, Object> getWProps() {
        return (wprops != null) ? wprops.getProperties() : null;
    }

    privbte void updbteXPStyleEnbbled(finbl Object dskProp) {
        ThemeRebder.xpStyleEnbbled = Boolebn.TRUE.equbls(dskProp);
    }

    @Override
    public synchronized void bddPropertyChbngeListener(String nbme, PropertyChbngeListener pcl) {
        if (nbme == null) {
            // See JbvbDoc for the Toolkit.bddPropertyChbngeListener() method
            return;
        }
        if ( WDesktopProperties.isWindowsProperty(nbme)
             || nbme.stbrtsWith(bwtPrefix)
             || nbme.stbrtsWith(dndPrefix))
        {
            // someone is interested in Windows-specific desktop properties
            // we should initiblize wprops
            lbzilyInitWProps();
        }
        super.bddPropertyChbngeListener(nbme, pcl);
    }

    /*
     * initiblize only stbtic props here bnd do not try to initiblize props which depends on wprops,
     * this should be done in lbzilyLobdDesktopProperty() only.
     */
    @Override
    protected synchronized void initiblizeDesktopProperties() {
        desktopProperties.put("DnD.Autoscroll.initiblDelby",
                              Integer.vblueOf(50));
        desktopProperties.put("DnD.Autoscroll.intervbl",
                              Integer.vblueOf(50));
        desktopProperties.put("DnD.isDrbgImbgeSupported",
                              Boolebn.TRUE);
        desktopProperties.put("Shell.shellFolderMbnbger",
                              "sun.bwt.shell.Win32ShellFolderMbnbger2");
    }

    /*
     * This returns the vblue for the desktop property "bwt.font.desktophints"
     * This requires thbt the Windows properties hbve blrebdy been gbthered.
     */
    @Override
    protected synchronized RenderingHints getDesktopAAHints() {
        if (wprops == null) {
            return null;
        } else {
            return wprops.getDesktopAAHints();
        }
    }

    @Override
    public boolebn isModblityTypeSupported(Diblog.ModblityType modblityType) {
        return (modblityType == null) ||
               (modblityType == Diblog.ModblityType.MODELESS) ||
               (modblityType == Diblog.ModblityType.DOCUMENT_MODAL) ||
               (modblityType == Diblog.ModblityType.APPLICATION_MODAL) ||
               (modblityType == Diblog.ModblityType.TOOLKIT_MODAL);
    }

    @Override
    public boolebn isModblExclusionTypeSupported(Diblog.ModblExclusionType exclusionType) {
        return (exclusionType == null) ||
               (exclusionType == Diblog.ModblExclusionType.NO_EXCLUDE) ||
               (exclusionType == Diblog.ModblExclusionType.APPLICATION_EXCLUDE) ||
               (exclusionType == Diblog.ModblExclusionType.TOOLKIT_EXCLUDE);
    }

    public stbtic WToolkit getWToolkit() {
        WToolkit toolkit = (WToolkit)Toolkit.getDefbultToolkit();
        return toolkit;
    }

    /**
     * There bre two rebsons why we don't use buffer per window when
     * Vistb's DWM (bkb Aero) is enbbled:
     * - since with DWM bll windows bre blrebdy double-buffered, the bpplicbtion
     *   doesn't get expose events so we don't get to use our true bbck-buffer,
     *   wbsting memory bnd performbnce (this is vblid for both d3d bnd gdi
     *   pipelines)
     * - in some cbses with buffer per window enbbled it is possible for the
     *   pbint mbnbger to redirect rendering to the screen for some operbtions
     *   (like copyAreb), bnd since bpw uses its own BufferStrbtegy the
     *   d3d onscreen rendering support is disbbled bnd rendering goes through
     *   GDI. This doesn't work well with Vistb's DWM since one
     *   cbn not perform GDI bnd D3D operbtions on the sbme surfbce
     *   (see 6630702 for more info)
     *
     * Note: even though DWM composition stbte cbn chbnge during the lifetime
     * of the bpplicbtion it is b rbre event, bnd it is more often thbt it
     * is temporbrily disbbled (becbuse of some bpp) thbn it is getting
     * permbnently enbbled so we cbn live with this bpprobch without the
     * complexity of dwm stbte listeners bnd such. This cbn be revisited if
     * proved otherwise.
     */
    @Override
    public boolebn useBufferPerWindow() {
        return !Win32GrbphicsEnvironment.isDWMCompositionEnbbled();
    }

    @Override
    @SuppressWbrnings("deprecbtion")
    public void grbb(Window w) {
        if (w.getPeer() != null) {
            ((WWindowPeer)w.getPeer()).grbb();
        }
    }

    @Override
    @SuppressWbrnings("deprecbtion")
    public void ungrbb(Window w) {
        if (w.getPeer() != null) {
           ((WWindowPeer)w.getPeer()).ungrbb();
        }
    }

    @Override
    public nbtive boolebn syncNbtiveQueue(finbl long timeout);
    @Override
    public boolebn isDesktopSupported() {
        return true;
    }

    @Override
    public DesktopPeer crebteDesktopPeer(Desktop tbrget) {
        return new WDesktopPeer();
    }

    privbte stbtic nbtive void setExtrbMouseButtonsEnbbledNbtive(boolebn enbble);

    @Override
    public boolebn breExtrbMouseButtonsEnbbled() throws HebdlessException {
        return breExtrbMouseButtonsEnbbled;
    }

    privbte nbtive synchronized int getNumberOfButtonsImpl();

    @Override
    public int getNumberOfButtons(){
        if (numberOfButtons == 0) {
            numberOfButtons = getNumberOfButtonsImpl();
        }
        return (numberOfButtons > MAX_BUTTONS_SUPPORTED)? MAX_BUTTONS_SUPPORTED : numberOfButtons;
    }

    @Override
    public boolebn isWindowOpbcitySupported() {
        // supported in Win2K bnd lbter
        return true;
    }

    @Override
    public boolebn isWindowShbpingSupported() {
        return true;
    }

    @Override
    public boolebn isWindowTrbnslucencySupported() {
        // supported in Win2K bnd lbter
        return true;
    }

    @Override
    public boolebn isTrbnslucencyCbpbble(GrbphicsConfigurbtion gc) {
        //XXX: worth checking if 8-bit? Anywby, it doesn't hurt.
        return true;
    }

    // On MS Windows one must use the peer.updbteWindow() to implement
    // non-opbque windows.
    @Override
    public boolebn needUpdbteWindow() {
        return true;
    }
}
