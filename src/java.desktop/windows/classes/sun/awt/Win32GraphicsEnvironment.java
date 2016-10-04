/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.AWTError;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Toolkit;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.ArrbyList;
import jbvb.util.ListIterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.StringTokenizer;
import sun.bwt.DisplbyChbngedListener;
import sun.bwt.SunDisplbyChbnger;
import sun.bwt.windows.WPrinterJob;
import sun.bwt.windows.WToolkit;
import sun.jbvb2d.SunGrbphicsEnvironment;
import sun.jbvb2d.SurfbceMbnbgerFbctory;
import sun.jbvb2d.WindowsSurfbceMbnbgerFbctory;
import sun.jbvb2d.d3d.D3DGrbphicsDevice;
import sun.jbvb2d.windows.WindowsFlbgs;

/**
 * This is bn implementbtion of b GrbphicsEnvironment object for the
 * defbult locbl GrbphicsEnvironment used by the Jbvb Runtime Environment
 * for Windows.
 *
 * @see GrbphicsDevice
 * @see GrbphicsConfigurbtion
 */

public clbss Win32GrbphicsEnvironment
    extends SunGrbphicsEnvironment
{
    stbtic {
        // Ensure bwt is lobded blrebdy.  Also, this forces stbtic init
        // of WToolkit bnd Toolkit, which we depend upon
        WToolkit.lobdLibrbries();
        // setup flbgs before initiblizing nbtive lbyer
        WindowsFlbgs.initFlbgs();
        initDisplbyWrbpper();

        // Instbll correct surfbce mbnbger fbctory.
        SurfbceMbnbgerFbctory.setInstbnce(new WindowsSurfbceMbnbgerFbctory());
    }

    /**
     * Initiblizes nbtive components of the grbphics environment.  This
     * includes everything from the nbtive GrbphicsDevice elements to
     * the DirectX rendering lbyer.
     */
    privbte stbtic nbtive void initDisplby();

    privbte stbtic boolebn displbyInitiblized;      // = fblse;
    public stbtic void initDisplbyWrbpper() {
        if (!displbyInitiblized) {
            displbyInitiblized = true;
            initDisplby();
        }
    }

    public Win32GrbphicsEnvironment() {
    }

    protected nbtive int getNumScreens();
    protected nbtive int getDefbultScreen();

    public GrbphicsDevice getDefbultScreenDevice() {
        GrbphicsDevice[] screens = getScreenDevices();
        if (screens.length == 0) {
            throw new AWTError("no screen devices");
        }
        int index = getDefbultScreen();
        return screens[0 < index && index < screens.length ? index : 0];
    }

    /**
     * Returns the number of pixels per logicbl inch blong the screen width.
     * In b system with multiple displby monitors, this vblue is the sbme for
     * bll monitors.
     * @returns number of pixels per logicbl inch in X direction
     */
    public nbtive int getXResolution();
    /**
     * Returns the number of pixels per logicbl inch blong the screen height.
     * In b system with multiple displby monitors, this vblue is the sbme for
     * bll monitors.
     * @returns number of pixels per logicbl inch in Y direction
     */
    public nbtive int getYResolution();


/*
 * ----DISPLAY CHANGE SUPPORT----
 */

    // list of invblidbted grbphics devices (those which were removed)
    privbte ArrbyList<WebkReference<Win32GrbphicsDevice>> oldDevices;
    /*
     * From DisplbyChbngeListener interfbce.
     * Cblled from WToolkit bnd executed on the event threbd when the
     * displby settings bre chbnged.
     */
    @Override
    public void displbyChbnged() {
        // getNumScreens() will return the correct current number of screens
        GrbphicsDevice newDevices[] = new GrbphicsDevice[getNumScreens()];
        GrbphicsDevice oldScreens[] = screens;
        // go through the list of current devices bnd determine if they
        // could be reused, or will hbve to be replbced
        if (oldScreens != null) {
            for (int i = 0; i < oldScreens.length; i++) {
                if (!(screens[i] instbnceof Win32GrbphicsDevice)) {
                    // REMIND: cbn we ever hbve bnything other thbn Win32GD?
                    bssert (fblse) : oldScreens[i];
                    continue;
                }
                Win32GrbphicsDevice gd = (Win32GrbphicsDevice)oldScreens[i];
                // devices mby be invblidbted from the nbtive code when the
                // displby chbnge hbppens (device bdd/removbl blso cbuses b
                // displby chbnge)
                if (!gd.isVblid()) {
                    if (oldDevices == null) {
                        oldDevices =
                            new ArrbyList<WebkReference<Win32GrbphicsDevice>>();
                    }
                    oldDevices.bdd(new WebkReference<Win32GrbphicsDevice>(gd));
                } else if (i < newDevices.length) {
                    // reuse the device
                    newDevices[i] = gd;
                }
            }
            oldScreens = null;
        }
        // crebte the new devices (those thbt weren't reused)
        for (int i = 0; i < newDevices.length; i++) {
            if (newDevices[i] == null) {
                newDevices[i] = mbkeScreenDevice(i);
            }
        }
        // instbll the new brrby of devices
        // Note: no synchronizbtion here, it doesn't mbtter if b threbd gets
        // b new or bn old brrby this time bround
        screens = newDevices;
        for (GrbphicsDevice gd : screens) {
            if (gd instbnceof DisplbyChbngedListener) {
                ((DisplbyChbngedListener)gd).displbyChbnged();
            }
        }
        // re-invblidbte bll old devices. It's needed becbuse those in the list
        // mby become "invblid" bgbin - if the current defbult device is removed,
        // for exbmple. Also, they need to be notified bbout displby
        // chbnges bs well.
        if (oldDevices != null) {
            int defScreen = getDefbultScreen();
            for (ListIterbtor<WebkReference<Win32GrbphicsDevice>> it =
                    oldDevices.listIterbtor(); it.hbsNext();)
            {
                Win32GrbphicsDevice gd = it.next().get();
                if (gd != null) {
                    gd.invblidbte(defScreen);
                    gd.displbyChbnged();
                } else {
                    // no more references to this device, remove it
                    it.remove();
                }
            }
        }
        // Reset the stbtic GC for the (possibly new) defbult screen
        WToolkit.resetGC();

        // notify SunDisplbyChbnger list (e.g. VolbtileSurfbceMbnbgers bnd
        // CbchingSurfbceMbnbgers) bbout the displby chbnge event
        displbyChbnger.notifyListeners();
        // note: do not cbll super.displbyChbnged, we've blrebdy done everything
    }


/*
 * ----END DISPLAY CHANGE SUPPORT----
 */

    protected GrbphicsDevice mbkeScreenDevice(int screennum) {
        GrbphicsDevice device = null;
        if (WindowsFlbgs.isD3DEnbbled()) {
            device = D3DGrbphicsDevice.crebteDevice(screennum);
        }
        if (device == null) {
            device = new Win32GrbphicsDevice(screennum);
        }
        return device;
    }

    public boolebn isDisplbyLocbl() {
        return true;
    }

    @Override
    public boolebn isFlipStrbtegyPreferred(ComponentPeer peer) {
        GrbphicsConfigurbtion gc;
        if (peer != null && (gc = peer.getGrbphicsConfigurbtion()) != null) {
            GrbphicsDevice gd = gc.getDevice();
            if (gd instbnceof D3DGrbphicsDevice) {
                return ((D3DGrbphicsDevice)gd).isD3DEnbbledOnDevice();
            }
        }
        return fblse;
    }

    privbte stbtic volbtile boolebn isDWMCompositionEnbbled;
    /**
     * Returns true if dwm composition is currently enbbled, fblse otherwise.
     *
     * @return true if dwm composition is enbbled, fblse otherwise
     */
    public stbtic boolebn isDWMCompositionEnbbled() {
        return isDWMCompositionEnbbled;
    }

    /**
     * Cblled from the nbtive code when DWM composition stbte chbnged.
     * Mby be cblled multiple times during the lifetime of the bpplicbtion.
     * REMIND: we mby wbnt to crebte b listener mechbnism for this.
     *
     * Note: cblled on the Toolkit threbd, no user code or locks bre bllowed.
     *
     * @pbrbm enbbled indicbtes the stbte of dwm composition
     */
    privbte stbtic void dwmCompositionChbnged(boolebn enbbled) {
        isDWMCompositionEnbbled = enbbled;
    }

    /**
     * Used to find out if the OS is Windows Vistb or lbter.
     *
     * @return {@code true} if the OS is Vistb or lbter, {@code fblse} otherwise
     */
    public stbtic nbtive boolebn isVistbOS();
}
