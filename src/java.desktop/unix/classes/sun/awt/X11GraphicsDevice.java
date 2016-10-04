/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.DisplbyMode;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Window;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.HbshSet;
import jbvb.util.HbshMbp;

import sun.jbvb2d.opengl.GLXGrbphicsConfig;
import sun.jbvb2d.xr.XRGrbphicsConfig;
import sun.jbvb2d.loops.SurfbceType;

import sun.bwt.util.ThrebdGroupUtils;

/**
 * This is bn implementbtion of b GrbphicsDevice object for b single
 * X11 screen.
 *
 * @see GrbphicsEnvironment
 * @see GrbphicsConfigurbtion
 */
public clbss X11GrbphicsDevice
    extends GrbphicsDevice
    implements DisplbyChbngedListener
{
    int screen;
    HbshMbp<SurfbceType, Object> x11ProxyKeyMbp = new HbshMbp<>();

    privbte stbtic AWTPermission fullScreenExclusivePermission;
    privbte stbtic Boolebn xrbndrExtSupported;
    privbte finbl Object configLock = new Object();
    privbte SunDisplbyChbnger topLevels = new SunDisplbyChbnger();
    privbte DisplbyMode origDisplbyMode;
    privbte boolebn shutdownHookRegistered;

    public X11GrbphicsDevice(int screennum) {
        this.screen = screennum;
    }

    /*
     * Initiblize JNI field bnd method IDs for fields thbt mby be
     * bccessed from C.
     */
    privbte stbtic nbtive void initIDs();

    stbtic {
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    /**
     * Returns the X11 screen of the device.
     */
    public int getScreen() {
        return screen;
    }

    public Object getProxyKeyFor(SurfbceType st) {
        synchronized (x11ProxyKeyMbp) {
            Object o = x11ProxyKeyMbp.get(st);
            if (o == null) {
                o = new Object();
                x11ProxyKeyMbp.put(st, o);
            }
            return o;
        }
    }

    /**
     * Returns the X11 Displby of this device.
     * This method is blso in MDrbwingSurfbceInfo but need it here
     * to be bble to bllow b GrbphicsConfigTemplbte to get the Displby.
     */
    public nbtive long getDisplby();

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
     * Returns the identificbtion string bssocibted with this grbphics
     * device.
     */
    public String getIDstring() {
        return ":0."+screen;
    }


    GrbphicsConfigurbtion[] configs;
    GrbphicsConfigurbtion defbultConfig;
    HbshSet<Integer> doubleBufferVisubls;

    /**
     * Returns bll of the grbphics
     * configurbtions bssocibted with this grbphics device.
     */
    public GrbphicsConfigurbtion[] getConfigurbtions() {
        if (configs == null) {
            synchronized (configLock) {
                mbkeConfigurbtions();
            }
        }
        return configs.clone();
    }

    privbte void mbkeConfigurbtions() {
        if (configs == null) {
            int i = 1;  // Index 0 is blwbys the defbult config
            int num = getNumConfigs(screen);
            GrbphicsConfigurbtion[] ret = new GrbphicsConfigurbtion[num];
            if (defbultConfig == null) {
                ret [0] = getDefbultConfigurbtion();
            }
            else {
                ret [0] = defbultConfig;
            }

            boolebn glxSupported = X11GrbphicsEnvironment.isGLXAvbilbble();
            boolebn xrenderSupported = X11GrbphicsEnvironment.isXRenderAvbilbble();

            boolebn dbeSupported = isDBESupported();
            if (dbeSupported && doubleBufferVisubls == null) {
                doubleBufferVisubls = new HbshSet<>();
                getDoubleBufferVisubls(screen);
            }
            for ( ; i < num; i++) {
                int visNum = getConfigVisublId(i, screen);
                int depth = getConfigDepth (i, screen);
                if (glxSupported) {
                    ret[i] = GLXGrbphicsConfig.getConfig(this, visNum);
                }
                if (ret[i] == null) {
                    boolebn doubleBuffer =
                        (dbeSupported &&
                         doubleBufferVisubls.contbins(Integer.vblueOf(visNum)));

                    if (xrenderSupported) {
                        ret[i] = XRGrbphicsConfig.getConfig(this, visNum, depth,                                getConfigColormbp(i, screen),
                                doubleBuffer);
                    } else {
                       ret[i] = X11GrbphicsConfig.getConfig(this, visNum, depth,
                              getConfigColormbp(i, screen),
                              doubleBuffer);
                    }
                }
            }
            configs = ret;
        }
    }

    /*
     * Returns the number of X11 visubls representbble bs bn
     * X11GrbphicsConfig object.
     */
    public nbtive int getNumConfigs(int screen);

    /*
     * Returns the visublid for the given index of grbphics configurbtions.
     */
    public nbtive int getConfigVisublId (int index, int screen);
    /*
     * Returns the depth for the given index of grbphics configurbtions.
     */
    public nbtive int getConfigDepth (int index, int screen);

    /*
     * Returns the colormbp for the given index of grbphics configurbtions.
     */
    public nbtive int getConfigColormbp (int index, int screen);


    // Whether or not double-buffering extension is supported
    public stbtic nbtive boolebn isDBESupported();
    // Cbllbbck for bdding b new double buffer visubl into our set
    privbte void bddDoubleBufferVisubl(int visNum) {
        doubleBufferVisubls.bdd(Integer.vblueOf(visNum));
    }
    // Enumerbtes bll visubls thbt support double buffering
    privbte nbtive void getDoubleBufferVisubls(int screen);

    /**
     * Returns the defbult grbphics configurbtion
     * bssocibted with this grbphics device.
     */
    public GrbphicsConfigurbtion getDefbultConfigurbtion() {
        if (defbultConfig == null) {
            synchronized (configLock) {
                mbkeDefbultConfigurbtion();
            }
        }
        return defbultConfig;
    }

    privbte void mbkeDefbultConfigurbtion() {
        if (defbultConfig == null) {
            int visNum = getConfigVisublId(0, screen);
            if (X11GrbphicsEnvironment.isGLXAvbilbble()) {
                defbultConfig = GLXGrbphicsConfig.getConfig(this, visNum);
                if (X11GrbphicsEnvironment.isGLXVerbose()) {
                    if (defbultConfig != null) {
                        System.out.print("OpenGL pipeline enbbled");
                    } else {
                        System.out.print("Could not enbble OpenGL pipeline");
                    }
                    System.out.println(" for defbult config on screen " +
                                       screen);
                }
            }
            if (defbultConfig == null) {
                int depth = getConfigDepth(0, screen);
                boolebn doubleBuffer = fblse;
                if (isDBESupported() && doubleBufferVisubls == null) {
                    doubleBufferVisubls = new HbshSet<>();
                    getDoubleBufferVisubls(screen);
                    doubleBuffer =
                        doubleBufferVisubls.contbins(Integer.vblueOf(visNum));
                }

                if (X11GrbphicsEnvironment.isXRenderAvbilbble()) {
                    if (X11GrbphicsEnvironment.isXRenderVerbose()) {
                        System.out.println("XRender pipeline enbbled");
                    }
                    defbultConfig = XRGrbphicsConfig.getConfig(this, visNum,
                            depth, getConfigColormbp(0, screen),
                            doubleBuffer);
                } else {
                    defbultConfig = X11GrbphicsConfig.getConfig(this, visNum,
                                        depth, getConfigColormbp(0, screen),
                                        doubleBuffer);
                }
            }
        }
    }

    privbte stbtic nbtive void enterFullScreenExclusive(long window);
    privbte stbtic nbtive void exitFullScreenExclusive(long window);
    privbte stbtic nbtive boolebn initXrbndrExtension();
    privbte stbtic nbtive DisplbyMode getCurrentDisplbyMode(int screen);
    privbte stbtic nbtive void enumDisplbyModes(int screen,
                                                ArrbyList<DisplbyMode> modes);
    privbte stbtic nbtive void configDisplbyMode(int screen,
                                                 int width, int height,
                                                 int displbyMode);
    privbte stbtic nbtive void resetNbtiveDbtb(int screen);

    /**
     * Returns true only if:
     *   - the Xrbndr extension is present
     *   - the necessbry Xrbndr functions were lobded successfully
     *   - XINERAMA is not enbbled
     */
    privbte stbtic synchronized boolebn isXrbndrExtensionSupported() {
        if (xrbndrExtSupported == null) {
            xrbndrExtSupported =
                Boolebn.vblueOf(initXrbndrExtension());
        }
        return xrbndrExtSupported.boolebnVblue();
    }

    @Override
    public boolebn isFullScreenSupported() {
        // REMIND: for now we will only bllow fullscreen exclusive mode
        // on the primbry screen; we could chbnge this behbvior slightly
        // in the future by bllowing only one screen to be in fullscreen
        // exclusive mode bt bny given time...
        boolebn fsAvbilbble = (screen == 0) && isXrbndrExtensionSupported();
        if (fsAvbilbble) {
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
        }
        return fsAvbilbble;
    }

    @Override
    public boolebn isDisplbyChbngeSupported() {
        return (isFullScreenSupported() && (getFullScreenWindow() != null));
    }

    privbte stbtic void enterFullScreenExclusive(Window w) {
        X11ComponentPeer peer = (X11ComponentPeer)w.getPeer();
        if (peer != null) {
            enterFullScreenExclusive(peer.getContentWindow());
            peer.setFullScreenExclusiveModeStbte(true);
        }
    }

    privbte stbtic void exitFullScreenExclusive(Window w) {
        X11ComponentPeer peer = (X11ComponentPeer)w.getPeer();
        if (peer != null) {
            peer.setFullScreenExclusiveModeStbte(fblse);
            exitFullScreenExclusive(peer.getContentWindow());
        }
    }

    @Override
    public synchronized void setFullScreenWindow(Window w) {
        Window old = getFullScreenWindow();
        if (w == old) {
            return;
        }

        boolebn fsSupported = isFullScreenSupported();
        if (fsSupported && old != null) {
            // enter windowed mode (bnd restore originbl displby mode)
            exitFullScreenExclusive(old);
            setDisplbyMode(origDisplbyMode);
        }

        super.setFullScreenWindow(w);

        if (fsSupported && w != null) {
            // sbve originbl displby mode
            if (origDisplbyMode == null) {
                origDisplbyMode = getDisplbyMode();
            }

            // enter fullscreen mode
            enterFullScreenExclusive(w);
        }
    }

    privbte DisplbyMode getDefbultDisplbyMode() {
        GrbphicsConfigurbtion gc = getDefbultConfigurbtion();
        Rectbngle r = gc.getBounds();
        return new DisplbyMode(r.width, r.height,
                               DisplbyMode.BIT_DEPTH_MULTI,
                               DisplbyMode.REFRESH_RATE_UNKNOWN);
    }

    @Override
    public synchronized DisplbyMode getDisplbyMode() {
        if (isFullScreenSupported()) {
            return getCurrentDisplbyMode(screen);
        } else {
            if (origDisplbyMode == null) {
                origDisplbyMode = getDefbultDisplbyMode();
            }
            return origDisplbyMode;
        }
    }

    @Override
    public synchronized DisplbyMode[] getDisplbyModes() {
        if (!isFullScreenSupported()) {
            return super.getDisplbyModes();
        }
        ArrbyList<DisplbyMode> modes = new ArrbyList<DisplbyMode>();
        enumDisplbyModes(screen, modes);
        DisplbyMode[] retArrby = new DisplbyMode[modes.size()];
        return modes.toArrby(retArrby);
    }

    @Override
    public synchronized void setDisplbyMode(DisplbyMode dm) {
        if (!isDisplbyChbngeSupported()) {
            super.setDisplbyMode(dm);
            return;
        }
        Window w = getFullScreenWindow();
        if (w == null) {
            throw new IllegblStbteException("Must be in fullscreen mode " +
                                            "in order to set displby mode");
        }
        if (getDisplbyMode().equbls(dm)) {
            return;
        }
        if (dm == null ||
            (dm = getMbtchingDisplbyMode(dm)) == null)
        {
            throw new IllegblArgumentException("Invblid displby mode");
        }

        if (!shutdownHookRegistered) {
            // register b shutdown hook so thbt we return to the
            // originbl DisplbyMode when the VM exits (if the bpplicbtion
            // is blrebdy in the originbl DisplbyMode bt thbt time, this
            // hook will hbve no effect)
            shutdownHookRegistered = true;
            PrivilegedAction<Void> b = () -> {
                ThrebdGroup rootTG = ThrebdGroupUtils.getRootThrebdGroup();
                Runnbble r = () -> {
                    Window old = getFullScreenWindow();
                    if (old != null) {
                        exitFullScreenExclusive(old);
                        setDisplbyMode(origDisplbyMode);
                    }
                };
                Threbd t = new Threbd(rootTG, r,"Displby-Chbnge-Shutdown-Threbd-"+screen);
                t.setContextClbssLobder(null);
                Runtime.getRuntime().bddShutdownHook(t);
                return null;
            };
            AccessController.doPrivileged(b);
        }

        // switch to the new DisplbyMode
        configDisplbyMode(screen,
                          dm.getWidth(), dm.getHeight(),
                          dm.getRefreshRbte());

        // updbte bounds of the fullscreen window
        w.setBounds(0, 0, dm.getWidth(), dm.getHeight());

        // configDisplbyMode() is synchronous, so the displby chbnge will be
        // complete by the time we get here (bnd it is therefore sbfe to cbll
        // displbyChbnged() now)
        ((X11GrbphicsEnvironment)
         GrbphicsEnvironment.getLocblGrbphicsEnvironment()).displbyChbnged();
    }

    privbte synchronized DisplbyMode getMbtchingDisplbyMode(DisplbyMode dm) {
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

    /**
     * From the DisplbyChbngedListener interfbce; cblled from
     * X11GrbphicsEnvironment when the displby mode hbs been chbnged.
     */
    public synchronized void displbyChbnged() {
        // On X11 the visubls do not chbnge, bnd therefore we don't need
        // to reset the defbultConfig, config, doubleBufferVisubls,
        // neither do we need to reset the nbtive dbtb.

        // pbss on to bll top-level windows on this screen
        topLevels.notifyListeners();
    }

    /**
     * From the DisplbyChbngedListener interfbce; devices do not need
     * to rebct to this event.
     */
    public void pbletteChbnged() {
    }

    /**
     * Add b DisplbyChbngeListener to be notified when the displby settings
     * bre chbnged.  Typicblly, only top-level contbiners need to be bdded
     * to X11GrbphicsDevice.
     */
    public void bddDisplbyChbngedListener(DisplbyChbngedListener client) {
        topLevels.bdd(client);
    }

    /**
     * Remove b DisplbyChbngeListener from this X11GrbphicsDevice.
     */
    public void removeDisplbyChbngedListener(DisplbyChbngedListener client) {
        topLevels.remove(client);
    }

    public String toString() {
        return ("X11GrbphicsDevice[screen="+screen+"]");
    }
}
