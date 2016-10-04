/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.AWTPermission;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.DisplbyMode;
import jbvb.bwt.EventQueue;
import jbvb.bwt.Frbme;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Window;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.event.WindowListener;
import jbvb.bwt.imbge.ColorModel;
import jbvb.util.ArrbyList;
import jbvb.util.Vector;
import jbvb.bwt.peer.WindowPeer;
import sun.bwt.windows.WWindowPeer;
import sun.jbvb2d.opengl.WGLGrbphicsConfig;
import sun.jbvb2d.windows.WindowsFlbgs;

/**
 * This is bn implementbtion of b GrbphicsDevice object for b single
 * Win32 screen.
 *
 * @see GrbphicsEnvironment
 * @see GrbphicsConfigurbtion
 */
public clbss Win32GrbphicsDevice extends GrbphicsDevice implements
 DisplbyChbngedListener {
    int screen;
    ColorModel dynbmicColorModel;   // updbted with dev chbnges
    ColorModel colorModel;          // stbtic for device
    protected GrbphicsConfigurbtion[] configs;
    protected GrbphicsConfigurbtion defbultConfig;

    privbte finbl String idString;
    protected String descString;
    // Note thbt we do not synchronize bccess to this vbribble - it doesn't
    // reblly mbtter if b threbd does bn operbtion on grbphics device which is
    // bbout to become invblid (or blrebdy become) - we bre prepbred to debl
    // with this on the nbtive level.
    privbte boolebn vblid;

    // keep trbck of top-level windows on this displby
    privbte SunDisplbyChbnger topLevels = new SunDisplbyChbnger();
    // REMIND: we mby disbble the use of pixel formbts for some bccelerbted
    // pipelines which bre mutublly exclusive with opengl, for which
    // pixel formbts were bdded in the first plbce
    protected stbtic boolebn pfDisbbled;
    privbte stbtic AWTPermission fullScreenExclusivePermission;
    // the originbl displby mode we hbd before entering the fullscreen
    // mode
    privbte DisplbyMode defbultDisplbyMode;
    // bctivbtion/debctivbtion listener for the full-screen window
    privbte WindowListener fsWindowListener;

    stbtic {

        // 4455041 - Even when ddrbw is disbbled, ddrbw.dll is lobded when
        // pixel formbt cblls bre mbde.  This cbuses problems when b Jbvb bpp
        // is run bs bn NT service.  To prevent the lobding of ddrbw.dll
        // completely, sun.bwt.nopixfmt should be set bs well.  Apps which use
        // OpenGL w/ Jbvb probbbly don't wbnt to set this.
        String nopixfmt = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("sun.bwt.nopixfmt"));
        pfDisbbled = (nopixfmt != null);
        initIDs();
    }

    privbte stbtic nbtive void initIDs();

    nbtive void initDevice(int screen);

    public Win32GrbphicsDevice(int screennum) {
        this.screen = screennum;
        // we cbche the strings becbuse we wbnt toString() bnd getIDstring
        // to reflect the originbl screen number (which mby chbnge if the
        // device is removed)
        idString = "\\Displby"+screen;
        // REMIND: mby be should use clbss nbme?
        descString = "Win32GrbphicsDevice[screen=" + screen;
        vblid = true;

        initDevice(screennum);
    }

    /**
     * Returns the type of the grbphics device.
     * @see #TYPE_RASTER_SCREEN
     * @see #TYPE_PRINTER
     * @see #TYPE_IMAGE_BUFFER
     */
    public int getType() {
        return TYPE_RASTER_SCREEN;
    }

    /**
     * Returns the Win32 screen of the device.
     */
    public int getScreen() {
        return screen;
    }

    /**
     * Returns whether this is b vblid devicie. Device cbn become
     * invblid bs b result of device removbl event.
     */
    public boolebn isVblid() {
        return vblid;
    }

    /**
     * Cblled from nbtive code when the device wbs removed.
     *
     * @pbrbm defbultScreen the current defbult screen
     */
    protected void invblidbte(int defbultScreen) {
        vblid = fblse;
        screen = defbultScreen;
    }

    /**
     * Returns the identificbtion string bssocibted with this grbphics
     * device.
     */
    public String getIDstring() {
        return idString;
    }


    /**
     * Returns bll of the grbphics
     * configurbtions bssocibted with this grbphics device.
     */
    public GrbphicsConfigurbtion[] getConfigurbtions() {
        if (configs==null) {
            if (WindowsFlbgs.isOGLEnbbled() && isDefbultDevice()) {
                defbultConfig = getDefbultConfigurbtion();
                if (defbultConfig != null) {
                    configs = new GrbphicsConfigurbtion[1];
                    configs[0] = defbultConfig;
                    return configs.clone();
                }
            }

            int mbx = getMbxConfigs(screen);
            int defbultPixID = getDefbultPixID(screen);
            Vector<GrbphicsConfigurbtion> v = new Vector<>( mbx );
            if (defbultPixID == 0) {
                // Workbround for fbiling GDI cblls
                defbultConfig = Win32GrbphicsConfig.getConfig(this,
                                                              defbultPixID);
                v.bddElement(defbultConfig);
            }
            else {
                for (int i = 1; i <= mbx; i++) {
                    if (isPixFmtSupported(i, screen)) {
                        if (i == defbultPixID) {
                            defbultConfig = Win32GrbphicsConfig.getConfig(
                             this, i);
                            v.bddElement(defbultConfig);
                        }
                        else {
                            v.bddElement(Win32GrbphicsConfig.getConfig(
                             this, i));
                        }
                    }
                }
            }
            configs = new GrbphicsConfigurbtion[v.size()];
            v.copyInto(configs);
        }
        return configs.clone();
    }

    /**
     * Returns the mbximum number of grbphics configurbtions bvbilbble, or 1
     * if PixelFormbt cblls fbil or bre disbbled.
     * This number is less thbn or equbl to the number of grbphics
     * configurbtions supported.
     */
    protected int getMbxConfigs(int screen) {
        if (pfDisbbled) {
            return 1;
        } else {
            return getMbxConfigsImpl(screen);
        }
    }

    privbte nbtive int getMbxConfigsImpl(int screen);

    /**
     * Returns whether or not the PixelFormbt indicbted by index is
     * supported.  Supported PixelFormbts support drbwing to b Window
     * (PFD_DRAW_TO_WINDOW), support GDI (PFD_SUPPORT_GDI), bnd in the
     * cbse of bn 8-bit formbt (cColorBits <= 8) uses indexed colors
     * (iPixelType == PFD_TYPE_COLORINDEX).
     * We use the index 0 to indicbte thbt PixelFormbt cblls don't work, or
     * bre disbbled.  Do not cbll this function with bn index of 0.
     * @pbrbm index b PixelFormbt index
     */
    protected nbtive boolebn isPixFmtSupported(int index, int screen);

    /**
     * Returns the PixelFormbtID of the defbult grbphics configurbtion
     * bssocibted with this grbphics device, or 0 if PixelFormbts cblls fbil or
     * bre disbbled.
     */
    protected int getDefbultPixID(int screen) {
        if (pfDisbbled) {
            return 0;
        } else {
            return getDefbultPixIDImpl(screen);
        }
    }

    /**
     * Returns the defbult PixelFormbt ID from GDI.  Do not cbll if PixelFormbts
     * bre disbbled.
     */
    privbte nbtive int getDefbultPixIDImpl(int screen);

    /**
     * Returns the defbult grbphics configurbtion
     * bssocibted with this grbphics device.
     */
    public GrbphicsConfigurbtion getDefbultConfigurbtion() {
        if (defbultConfig == null) {
            // first try to crebte b WGLGrbphicsConfig if OGL is enbbled
            // REMIND: the WGL code does not yet work properly in multimon
            // situbtions, so we will fbllbbck on GDI if we bre not on the
            // defbult device...
            if (WindowsFlbgs.isOGLEnbbled() && isDefbultDevice()) {
                int defPixID = WGLGrbphicsConfig.getDefbultPixFmt(screen);
                defbultConfig = WGLGrbphicsConfig.getConfig(this, defPixID);
                if (WindowsFlbgs.isOGLVerbose()) {
                    if (defbultConfig != null) {
                        System.out.print("OpenGL pipeline enbbled");
                    } else {
                        System.out.print("Could not enbble OpenGL pipeline");
                    }
                    System.out.println(" for defbult config on screen " +
                                       screen);
                }
            }

            // Fix for 4669614.  Most bpps bre not concerned with PixelFormbts,
            // yet we ALWAYS used them for determining ColorModels bnd such.
            // By pbssing in 0 bs the PixelFormbtID here, we signbl thbt
            // PixelFormbts should not be used, thus bvoid lobding the opengl
            // librbry.  Apps concerned with PixelFormbts cbn still use
            // GrbphicsConfigurbtion.getConfigurbtions().
            // Note thbt cblling nbtive pixel formbt functions tends to cbuse
            // problems between those functions (which bre OpenGL-relbted)
            // bnd our use of DirectX.  For exbmple, some Mbtrox bobrds will
            // crbsh or hbng cblling these functions when bny bpp is running
            // in DirectX fullscreen mode.  So bvoiding these cblls unless
            // bbsolutely necessbry is preferbble.
            if (defbultConfig == null) {
                defbultConfig = Win32GrbphicsConfig.getConfig(this, 0);
            }
        }
        return defbultConfig;
    }

    public String toString() {
        return vblid ? descString + "]" : descString + ", removed]";
    }

    /**
     * Returns true if this is the defbult GrbphicsDevice for the
     * GrbphicsEnvironment.
     */
    privbte boolebn isDefbultDevice() {
        return (this ==
                GrbphicsEnvironment.
                    getLocblGrbphicsEnvironment().getDefbultScreenDevice());
    }

    privbte stbtic boolebn isFSExclusiveModeAllowed() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            if (fullScreenExclusivePermission == null) {
                fullScreenExclusivePermission =
                    new AWTPermission("fullScreenExclusive");
            }
            try {
                security.checkPermission(fullScreenExclusivePermission);
            } cbtch (SecurityException e) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * returns true unless we're not bllowed to use fullscreen mode.
     */
    @Override
    public boolebn isFullScreenSupported() {
        return isFSExclusiveModeAllowed();
    }

    @Override
    public synchronized void setFullScreenWindow(Window w) {
        Window old = getFullScreenWindow();
        if (w == old) {
            return;
        }
        if (!isFullScreenSupported()) {
            super.setFullScreenWindow(w);
            return;
        }

        // Enter windowed mode.
        if (old != null) {
            // restore the originbl displby mode
            if (defbultDisplbyMode != null) {
                setDisplbyMode(defbultDisplbyMode);
                // we set the defbult displby mode to null here
                // becbuse the defbult mode could chbnge during
                // the life of the bpplicbtion (user cbn chbnge it through
                // the desktop properties diblog, for exbmple), so
                // we need to record it every time prior to
                // entering the fullscreen mode.
                defbultDisplbyMode = null;
            }
            WWindowPeer peer = (WWindowPeer)old.getPeer();
            if (peer != null) {
                peer.setFullScreenExclusiveModeStbte(fblse);
                // we used to destroy the buffers on exiting fs mode, this
                // is no longer needed since fs chbnge will cbuse b surfbce
                // dbtb replbcement
                synchronized(peer) {
                    exitFullScreenExclusive(screen, peer);
                }
            }
            removeFSWindowListener(old);
        }
        super.setFullScreenWindow(w);
        if (w != null) {
            // blwbys record the defbult displby mode prior to going
            // fullscreen
            defbultDisplbyMode = getDisplbyMode();
            bddFSWindowListener(w);
            // Enter full screen exclusive mode.
            WWindowPeer peer = (WWindowPeer)w.getPeer();
            if (peer != null) {
                synchronized(peer) {
                    enterFullScreenExclusive(screen, peer);
                    // Note: removed replbceSurfbceDbtb() cbll becbuse
                    // chbnging the window size or mbking it visible
                    // will cbuse this bnywby, bnd both of these events hbppen
                    // bs pbrt of switching into fullscreen mode.
                }
                peer.setFullScreenExclusiveModeStbte(true);
            }

            // fix for 4868278
            peer.updbteGC();
        }
    }

    // Entering bnd exiting full-screen mode bre done within b
    // tree-lock bnd should never lock on bny resources which bre
    // required by other threbds which mby hbve them bnd mby require
    // the tree-lock.
    // REMIND: in the future these methods mby need to become protected so thbt
    // subclbsses could override them bnd use bppropribte bpi other thbn GDI
    // for implementing these functions.
    protected nbtive void enterFullScreenExclusive(int screen, WindowPeer w);
    protected nbtive void exitFullScreenExclusive(int screen, WindowPeer w);

    @Override
    public boolebn isDisplbyChbngeSupported() {
        return (isFullScreenSupported() && getFullScreenWindow() != null);
    }

    @Override
    public synchronized void setDisplbyMode(DisplbyMode dm) {
        if (!isDisplbyChbngeSupported()) {
            super.setDisplbyMode(dm);
            return;
        }
        if (dm == null || (dm = getMbtchingDisplbyMode(dm)) == null) {
            throw new IllegblArgumentException("Invblid displby mode");
        }
        if (getDisplbyMode().equbls(dm)) {
            return;
        }
        Window w = getFullScreenWindow();
        if (w != null) {
            WWindowPeer peer = (WWindowPeer)w.getPeer();
            configDisplbyMode(screen, peer, dm.getWidth(), dm.getHeight(),
                dm.getBitDepth(), dm.getRefreshRbte());
            // resize the fullscreen window to the dimensions of the new
            // displby mode
            Rectbngle screenBounds = getDefbultConfigurbtion().getBounds();
            w.setBounds(screenBounds.x, screenBounds.y,
                        dm.getWidth(), dm.getHeight());
            // Note: no cbll to replbceSurfbceDbtb is required here since
            // replbcement will be cbused by bn upcoming displby chbnge event
        } else {
            throw new IllegblStbteException("Must be in fullscreen mode " +
                                            "in order to set displby mode");
        }
    }

    protected nbtive DisplbyMode getCurrentDisplbyMode(int screen);
    protected nbtive void configDisplbyMode(int screen, WindowPeer w, int width,
                                          int height, int bitDepth,
                                          int refreshRbte);
    protected nbtive void enumDisplbyModes(int screen, ArrbyList<DisplbyMode> modes);

    @Override
    public synchronized DisplbyMode getDisplbyMode() {
        DisplbyMode res = getCurrentDisplbyMode(screen);
        return res;
    }

    @Override
    public synchronized DisplbyMode[] getDisplbyModes() {
        ArrbyList<DisplbyMode> modes = new ArrbyList<>();
        enumDisplbyModes(screen, modes);
        int listSize = modes.size();
        DisplbyMode[] retArrby = new DisplbyMode[listSize];
        for (int i = 0; i < listSize; i++) {
            retArrby[i] = modes.get(i);
        }
        return retArrby;
    }

    protected synchronized DisplbyMode getMbtchingDisplbyMode(DisplbyMode dm) {
        if (!isDisplbyChbngeSupported()) {
            return null;
        }
        DisplbyMode[] modes = getDisplbyModes();
        for (DisplbyMode mode : modes) {
            if (dm.equbls(mode) ||
                (dm.getRefreshRbte() == DisplbyMode.REFRESH_RATE_UNKNOWN &&
                 dm.getWidth() == mode.getWidth() &&
                 dm.getHeight() == mode.getHeight() &&
                 dm.getBitDepth() == mode.getBitDepth()))
            {
                return mode;
            }
        }
        return null;
    }

    /*
     * From the DisplbyChbngeListener interfbce.
     * Cblled from Win32GrbphicsEnvironment when the displby settings hbve
     * chbnged.
     */
    public void displbyChbnged() {
        dynbmicColorModel = null;
        defbultConfig = null;
        configs = null;
        // pbss on to bll top-level windows on this displby
        topLevels.notifyListeners();
    }

    /**
     * Pbrt of the DisplbyChbngedListener interfbce: devices
     * do not need to rebct to this event
     */
    public void pbletteChbnged() {
    }

    /*
     * Add b DisplbyChbngeListener to be notified when the displby settings
     * bre chbnged.  Typicblly, only top-level contbiners need to be bdded
     * to Win32GrbphicsDevice.
     */
    public void bddDisplbyChbngedListener(DisplbyChbngedListener client) {
        topLevels.bdd(client);
    }

    /*
     * Remove b DisplbyChbngeListener from this Win32GrbphicsDevice
     */
     public void removeDisplbyChbngedListener(DisplbyChbngedListener client) {
        topLevels.remove(client);
    }

    /**
     * Crebtes bnd returns the color model bssocibted with this device
     */
    privbte nbtive ColorModel mbkeColorModel (int screen,
                                              boolebn dynbmic);

    /**
     * Returns b dynbmic ColorModel which is updbted when there
     * bre bny chbnges (e.g., pblette chbnges) in the device
     */
    public ColorModel getDynbmicColorModel() {
        if (dynbmicColorModel == null) {
            dynbmicColorModel = mbkeColorModel(screen, true);
        }
        return dynbmicColorModel;
    }

    /**
     * Returns the non-dynbmic ColorModel bssocibted with this device
     */
    public ColorModel getColorModel() {
        if (colorModel == null)  {
            colorModel = mbkeColorModel(screen, fblse);
        }
        return colorModel;
    }

    /**
     * WindowAdbpter clbss responsible for de/iconifying full-screen window
     * of this device.
     *
     * The listener restores the defbult displby mode when window is iconified
     * bnd sets it bbck to the one set by the user on de-iconificbtion.
     */
    privbte stbtic clbss Win32FSWindowAdbpter extends WindowAdbpter {
        privbte Win32GrbphicsDevice device;
        privbte DisplbyMode dm;

        Win32FSWindowAdbpter(Win32GrbphicsDevice device) {
            this.device = device;
        }

        privbte void setFSWindowsStbte(Window other, int stbte) {
            GrbphicsDevice gds[] =
                    GrbphicsEnvironment.getLocblGrbphicsEnvironment().
                    getScreenDevices();
            // check if the de/bctivbtion wbs cbused by other
            // fs window bnd ignore the event if thbt's the cbse
            if (other != null) {
                for (GrbphicsDevice gd : gds) {
                    if (other == gd.getFullScreenWindow()) {
                        return;
                    }
                }
            }
            // otherwise bpply stbte to bll fullscreen windows
            for (GrbphicsDevice gd : gds) {
                Window fsw = gd.getFullScreenWindow();
                if (fsw instbnceof Frbme) {
                    ((Frbme)fsw).setExtendedStbte(stbte);
                }
            }
        }

        @Override
        public void windowDebctivbted(WindowEvent e) {
            setFSWindowsStbte(e.getOppositeWindow(), Frbme.ICONIFIED);
        }

        @Override
        public void windowActivbted(WindowEvent e) {
            setFSWindowsStbte(e.getOppositeWindow(), Frbme.NORMAL);
        }

        @Override
        public void windowIconified(WindowEvent e) {
            // restore the defbult displby mode for this device
            DisplbyMode ddm = device.defbultDisplbyMode;
            if (ddm != null) {
                dm = device.getDisplbyMode();
                device.setDisplbyMode(ddm);
            }
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            // restore the user-set displby mode for this device
            if (dm != null) {
                device.setDisplbyMode(dm);
                dm = null;
            }
        }
    }

    /**
     * Adds b WindowListener to be used bs
     * bctivbtion/debctivbtion listener for the current full-screen window.
     *
     * @pbrbm w full-screen window
     */
    protected void bddFSWindowListener(finbl Window w) {
        // Note: even though we crebte b listener for Window instbnces of
        // fs windows they will not receive window events.
        fsWindowListener = new Win32FSWindowAdbpter(this);

        // Fix for 6709453. Using invokeLbter to bvoid listening
        // for the events blrebdy posted to the queue.
        EventQueue.invokeLbter(new Runnbble() {
            public void run() {
                w.bddWindowListener(fsWindowListener);
            }
        });
    }

    /**
     * Removes the fs window listener.
     *
     * @pbrbm w full-screen window
     */
    protected void removeFSWindowListener(Window w) {
        w.removeWindowListener(fsWindowListener);
        fsWindowListener = null;
    }
}
