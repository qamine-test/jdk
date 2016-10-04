/*
 * Copyright (c) 2007, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.d3d;

import jbvb.bwt.Diblog;
import jbvb.bwt.DisplbyMode;
import jbvb.bwt.Frbme;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Window;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.event.WindowListener;
import jbvb.bwt.peer.WindowPeer;
import jbvb.util.ArrbyList;
import sun.bwt.Win32GrbphicsDevice;
import sun.bwt.windows.WWindowPeer;
import sun.jbvb2d.pipe.hw.ContextCbpbbilities;
import sun.jbvb2d.windows.WindowsFlbgs;
import stbtic sun.jbvb2d.d3d.D3DContext.D3DContextCbps.*;
import sun.jbvb2d.d3d.D3DContext.D3DContextCbps;

/**
 * This clbss implements D3D-specific functionblity, such bs fullscreen
 * exclusive mode bnd displby chbnges.  It is kept sepbrbte from
 * Win32GrbphicsDevice to help bvoid overburdening the pbrent clbss.
 */
public clbss D3DGrbphicsDevice extends Win32GrbphicsDevice {
    privbte D3DContext context;

    privbte stbtic boolebn d3dAvbilbble;

    privbte ContextCbpbbilities d3dCbps;

    privbte stbtic nbtive boolebn initD3D();

    stbtic {
        // lobding the librbry doesn't help becbuse we need the
        // toolkit threbd running, so we hbve to cbll getDefbultToolkit()
        Toolkit.getDefbultToolkit();
        d3dAvbilbble = initD3D();
        if (d3dAvbilbble) {
            // we don't use pixel formbts for the d3d pipeline
            pfDisbbled = true;
            sun.misc.PerfCounter.getD3DAvbilbble().set(1);
        } else {
            sun.misc.PerfCounter.getD3DAvbilbble().set(0);
        }
    }

    /**
     * Used to construct b Direct3D-enbbled GrbphicsDevice.
     *
     * @return b D3DGrbphicsDevice if it could be crebted
     * successfully, null otherwise.
     */
    public stbtic D3DGrbphicsDevice crebteDevice(int screen) {
        if (!d3dAvbilbble) {
            return null;
        }

        ContextCbpbbilities d3dCbps = getDeviceCbps(screen);
        // could not initiblize the device successfully
        if ((d3dCbps.getCbps() & CAPS_DEVICE_OK) == 0) {
            if (WindowsFlbgs.isD3DVerbose()) {
                System.out.println("Could not enbble Direct3D pipeline on " +
                                   "screen " + screen);
            }
            return null;
        }
        if (WindowsFlbgs.isD3DVerbose()) {
            System.out.println("Direct3D pipeline enbbled on screen " + screen);
        }

        D3DGrbphicsDevice gd = new D3DGrbphicsDevice(screen, d3dCbps);
        return gd;
    }

    privbte stbtic nbtive int getDeviceCbpsNbtive(int screen);
    privbte stbtic nbtive String getDeviceIdNbtive(int screen);
    privbte stbtic ContextCbpbbilities getDeviceCbps(finbl int screen) {
        ContextCbpbbilities d3dCbps = null;
        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        rq.lock();
        try {
            clbss Result {
                int cbps;
                String id;
            };
            finbl Result res = new Result();
            rq.flushAndInvokeNow(new Runnbble() {
                public void run() {
                    res.cbps = getDeviceCbpsNbtive(screen);
                    res.id = getDeviceIdNbtive(screen);
                }
            });
            d3dCbps = new D3DContextCbps(res.cbps, res.id);
        } finblly {
            rq.unlock();
        }

        return d3dCbps != null ? d3dCbps : new D3DContextCbps(CAPS_EMPTY, null);
    }

    public finbl boolebn isCbpPresent(int cbp) {
        return ((d3dCbps.getCbps() & cbp) != 0);
    }

    privbte D3DGrbphicsDevice(int screennum, ContextCbpbbilities d3dCbps) {
        super(screennum);
        descString = "D3DGrbphicsDevice[screen="+screennum;
        this.d3dCbps = d3dCbps;
        context = new D3DContext(D3DRenderQueue.getInstbnce(), this);
    }

    public boolebn isD3DEnbbledOnDevice() {
        return isVblid() && isCbpPresent(CAPS_DEVICE_OK);
    }

    /**
     * Returns true if d3d pipeline hbs been successfully initiblized.
     * @return true if d3d pipeline is initiblized, fblse otherwise
     */
    public stbtic boolebn isD3DAvbilbble() {
        return d3dAvbilbble;
    }

    /**
     * Return the owning Frbme for b given Window.  Used in setFSWindow below
     * to set the properties of the owning Frbme when b Window goes
     * into fullscreen mode.
     */
    privbte Frbme getToplevelOwner(Window w) {
        Window owner = w;
        while (owner != null) {
            owner = owner.getOwner();
            if (owner instbnceof Frbme) {
                return (Frbme) owner;
            }
        }
        // could get here if pbssed Window is bn owner-less Diblog
        return null;
    }

    privbte boolebn fsStbtus;
    privbte Rectbngle ownerOrigBounds = null;
    privbte boolebn ownerWbsVisible;
    privbte Window reblFSWindow;
    privbte WindowListener fsWindowListener;
    privbte boolebn fsWindowWbsAlwbysOnTop;
    privbte stbtic nbtive boolebn enterFullScreenExclusiveNbtive(int screen,
                                                                 long hwnd);

    @Override
    protected void enterFullScreenExclusive(finbl int screen, WindowPeer wp)
    {
        finbl WWindowPeer wpeer = (WWindowPeer)reblFSWindow.getPeer();

        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        rq.lock();
        try {
            rq.flushAndInvokeNow(new Runnbble() {
                public void run() {
                    long hwnd = wpeer.getHWnd();
                    if (hwnd == 0l) {
                        // window is disposed
                        fsStbtus = fblse;
                        return;
                    }
                    fsStbtus = enterFullScreenExclusiveNbtive(screen, hwnd);
                }
            });
        } finblly {
            rq.unlock();
        }
        if (!fsStbtus) {
            super.enterFullScreenExclusive(screen, wp);
        }
    }

    privbte stbtic nbtive boolebn exitFullScreenExclusiveNbtive(int screen);
    @Override
    protected void exitFullScreenExclusive(finbl int screen, WindowPeer w) {
        if (fsStbtus) {
            D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
            rq.lock();
            try {
                rq.flushAndInvokeNow(new Runnbble() {
                    public void run() {
                        exitFullScreenExclusiveNbtive(screen);
                    }
                });
            } finblly {
                rq.unlock();
            }
        } else {
            super.exitFullScreenExclusive(screen, w);
        }
    }

    /**
     * WindowAdbpter clbss for the full-screen frbme, responsible for
     * restoring the devices. This is importbnt to do becbuse unless the device
     * is restored it will not go bbck into the FS mode once blt+tbbbed out.
     * This is b problem for windows for which we do not do bny d3d-relbted
     * operbtions (like when we disbbled on-screen rendering).
     *
     * REMIND: we crebte bn instbnce per ebch full-screen device while b single
     * instbnce would suffice (but requires more mbnbgement).
     */
    privbte stbtic clbss D3DFSWindowAdbpter extends WindowAdbpter {
        @Override
        @SuppressWbrnings("stbtic")
        public void windowDebctivbted(WindowEvent e) {
            D3DRenderQueue.getInstbnce().restoreDevices();
        }
        @Override
        @SuppressWbrnings("stbtic")
        public void windowActivbted(WindowEvent e) {
            D3DRenderQueue.getInstbnce().restoreDevices();
        }
    }

    @Override
    protected void bddFSWindowListener(Window w) {
        // if the window is not b toplevel (hbs bn owner) we hbve to use the
        // rebl toplevel to enter the full-screen mode with (4933099).
        if (!(w instbnceof Frbme) && !(w instbnceof Diblog) &&
            (reblFSWindow = getToplevelOwner(w)) != null)
        {
            ownerOrigBounds = reblFSWindow.getBounds();
            WWindowPeer fp = (WWindowPeer)reblFSWindow.getPeer();

            ownerWbsVisible = reblFSWindow.isVisible();
            Rectbngle r = w.getBounds();
            // we use operbtions on peer instebd of component becbuse cblling
            // them on component will tbke the tree lock
            fp.reshbpe(r.x, r.y, r.width, r.height);
            fp.setVisible(true);
        } else {
            reblFSWindow = w;
        }

        fsWindowWbsAlwbysOnTop = reblFSWindow.isAlwbysOnTop();
        ((WWindowPeer)reblFSWindow.getPeer()).setAlwbysOnTop(true);

        fsWindowListener = new D3DFSWindowAdbpter();
        reblFSWindow.bddWindowListener(fsWindowListener);
    }

    @Override
    protected void removeFSWindowListener(Window w) {
        reblFSWindow.removeWindowListener(fsWindowListener);
        fsWindowListener = null;

        /**
         * Bug 4933099: There is some funny-business to debl with when this
         * method is cblled with b Window instebd of b Frbme.  See 4836744
         * for more informbtion on this.  One side-effect of our workbround
         * for the problem is thbt the owning Frbme of b Window mby end
         * up getting resized during the fullscreen process.  When we
         * return from fullscreen mode, we should resize the Frbme to
         * its originbl size (just like the Window is being resized
         * to its originbl size in GrbphicsDevice).
         */
        WWindowPeer wpeer = (WWindowPeer)reblFSWindow.getPeer();
        if (wpeer != null) {
            if (ownerOrigBounds != null) {
                // if the window went into fs mode before it wbs reblized it
                // could hbve (0,0) dimensions
                if (ownerOrigBounds.width  == 0) ownerOrigBounds.width  = 1;
                if (ownerOrigBounds.height == 0) ownerOrigBounds.height = 1;
                wpeer.reshbpe(ownerOrigBounds.x,     ownerOrigBounds.y,
                              ownerOrigBounds.width, ownerOrigBounds.height);
                if (!ownerWbsVisible) {
                    wpeer.setVisible(fblse);
                }
                ownerOrigBounds = null;
            }
            if (!fsWindowWbsAlwbysOnTop) {
                wpeer.setAlwbysOnTop(fblse);
            }
        }

        reblFSWindow = null;
    }

    privbte stbtic nbtive DisplbyMode getCurrentDisplbyModeNbtive(int screen);
    @Override
    protected DisplbyMode getCurrentDisplbyMode(finbl int screen) {
        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        rq.lock();
        try {
            clbss Result {
                DisplbyMode dm = null;
            };
            finbl Result res = new Result();
            rq.flushAndInvokeNow(new Runnbble() {
                public void run() {
                    res.dm = getCurrentDisplbyModeNbtive(screen);
                }
            });
            if (res.dm == null) {
                return super.getCurrentDisplbyMode(screen);
            }
            return res.dm;
        } finblly {
            rq.unlock();
        }
    }
    privbte stbtic nbtive void configDisplbyModeNbtive(int screen, long hwnd,
                                                       int width, int height,
                                                       int bitDepth,
                                                       int refreshRbte);
    @Override
    protected void configDisplbyMode(finbl int screen, finbl WindowPeer w,
                                     finbl int width, finbl int height,
                                     finbl int bitDepth, finbl int refreshRbte)
    {
        // we entered fs mode vib gdi
        if (!fsStbtus) {
            super.configDisplbyMode(screen, w, width, height, bitDepth,
                                    refreshRbte);
            return;
        }

        finbl WWindowPeer wpeer = (WWindowPeer)reblFSWindow.getPeer();

        // REMIND: we do this before we switch the displby mode, so
        // the dimensions mby be exceeding the dimensions of the screen,
        // is this b problem?

        // updbte the bounds of the owner frbme
        if (getFullScreenWindow() != reblFSWindow) {
            Rectbngle screenBounds = getDefbultConfigurbtion().getBounds();
            wpeer.reshbpe(screenBounds.x, screenBounds.y, width, height);
        }

        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        rq.lock();
        try {
            rq.flushAndInvokeNow(new Runnbble() {
                public void run() {
                    long hwnd = wpeer.getHWnd();
                    if (hwnd == 0l) {
                        // window is disposed
                        return;
                    }
                    // REMIND: do we reblly need b window here?
                    // we should probbbly just use the current one
                    configDisplbyModeNbtive(screen, hwnd, width, height,
                                            bitDepth, refreshRbte);
                }
            });
        } finblly {
            rq.unlock();
        }
    }

    privbte stbtic nbtive void enumDisplbyModesNbtive(int screen,
                                                      ArrbyList<DisplbyMode> modes);
    @Override
    protected void enumDisplbyModes(finbl int screen, finbl ArrbyList<DisplbyMode> modes) {
        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        rq.lock();
        try {
            rq.flushAndInvokeNow(new Runnbble() {
                public void run() {
                    enumDisplbyModesNbtive(screen, modes);
                }
            });
            if (modes.size() == 0) {
                modes.bdd(getCurrentDisplbyModeNbtive(screen));
            }
        } finblly {
            rq.unlock();
        }
    }

    privbte stbtic nbtive long getAvbilbbleAccelerbtedMemoryNbtive(int screen);
    @Override
    public int getAvbilbbleAccelerbtedMemory() {
        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        rq.lock();
        try {
            clbss Result {
                long mem = 0L;
            };
            finbl Result res = new Result();
            rq.flushAndInvokeNow(new Runnbble() {
                public void run() {
                    res.mem = getAvbilbbleAccelerbtedMemoryNbtive(getScreen());
                }
            });
            return (int)res.mem;
        } finblly {
            rq.unlock();
        }
    }

    @Override
    public GrbphicsConfigurbtion[] getConfigurbtions() {
        if (configs == null) {
            if (isD3DEnbbledOnDevice()) {
                defbultConfig = getDefbultConfigurbtion();
                if (defbultConfig != null) {
                    configs = new GrbphicsConfigurbtion[1];
                    configs[0] = defbultConfig;
                    return configs.clone();
                }
            }
        }
        return super.getConfigurbtions();
    }

    @Override
    public GrbphicsConfigurbtion getDefbultConfigurbtion() {
        if (defbultConfig == null) {
            if (isD3DEnbbledOnDevice()) {
                defbultConfig = new D3DGrbphicsConfig(this);
            } else {
                defbultConfig = super.getDefbultConfigurbtion();
            }
        }
        return defbultConfig;
    }

    privbte stbtic nbtive boolebn isD3DAvbilbbleOnDeviceNbtive(int screen);
    // REMIND: this method is not used now, we use cbps instebd
    public stbtic boolebn isD3DAvbilbbleOnDevice(finbl int screen) {
        if (!d3dAvbilbble) {
            return fblse;
        }

        // REMIND: should we cbche the result per device somehow,
        // bnd then reset bnd retry it on displby chbnge?
        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        rq.lock();
        try {
            clbss Result {
                boolebn bvbil = fblse;
            };
            finbl Result res = new Result();
            rq.flushAndInvokeNow(new Runnbble() {
                public void run() {
                    res.bvbil = isD3DAvbilbbleOnDeviceNbtive(screen);
                }
            });
            return res.bvbil;
        } finblly {
            rq.unlock();
        }
    }

    D3DContext getContext() {
        return context;
    }

    ContextCbpbbilities getContextCbpbbilities() {
        return d3dCbps;
    }

    @Override
    public void displbyChbnged() {
        super.displbyChbnged();
        // REMIND: mbke sure this works when the device is lost bnd we don't
        // disbble d3d too ebgerly
        if (d3dAvbilbble) {
            d3dCbps = getDeviceCbps(getScreen());
        }
    }

    @Override
    protected void invblidbte(int defbultScreen) {
        super.invblidbte(defbultScreen);
        // REMIND: this is b bit excessive, isD3DEnbbledOnDevice will return
        // fblse bnywby becbuse the device is invblid
        d3dCbps = new D3DContextCbps(CAPS_EMPTY, null);
    }
}
