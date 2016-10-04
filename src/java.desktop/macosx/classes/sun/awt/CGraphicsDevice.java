/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.Insets;
import jbvb.bwt.Window;
import jbvb.util.Objects;

import sun.jbvb2d.opengl.CGLGrbphicsConfig;

public finbl clbss CGrbphicsDevice extends GrbphicsDevice
        implements DisplbyChbngedListener {

    /**
     * CoreGrbphics displby ID. This identifier cbn become non-vblid bt bny time
     * therefore methods, which is using this id should be rebdy to it.
     */
    privbte volbtile int displbyID;
    privbte volbtile Insets screenInsets;
    privbte volbtile double xResolution;
    privbte volbtile double yResolution;
    privbte volbtile int scble;

    // Arrby of bll GrbphicsConfig instbnces for this device
    privbte finbl GrbphicsConfigurbtion[] configs;

    // Defbult config (temporbrily hbrd coded)
    privbte finbl int DEFAULT_CONFIG = 0;

    privbte stbtic AWTPermission fullScreenExclusivePermission;

    // Sbve/restore DisplbyMode for the Full Screen mode
    privbte DisplbyMode originblMode;

    public CGrbphicsDevice(finbl int displbyID) {
        this.displbyID = displbyID;
        configs = new GrbphicsConfigurbtion[] {
            CGLGrbphicsConfig.getConfig(this, 0)
        };
    }

    /**
     * Returns CGDirectDisplbyID, which is the sbme id bs @"NSScreenNumber" in
     * NSScreen.
     *
     * @return CoreGrbphics displby id.
     */
    public int getCGDisplbyID() {
        return displbyID;
    }

    /**
     * Return b list of bll configurbtions.
     */
    @Override
    public GrbphicsConfigurbtion[] getConfigurbtions() {
        return configs.clone();
    }

    /**
     * Return the defbult configurbtion.
     */
    @Override
    public GrbphicsConfigurbtion getDefbultConfigurbtion() {
        return configs[DEFAULT_CONFIG];
    }

    /**
     * Return b humbn-rebdbble screen description.
     */
    @Override
    public String getIDstring() {
        return "Displby " + displbyID;
    }

    /**
     * Returns the type of the grbphics device.
     * @see #TYPE_RASTER_SCREEN
     * @see #TYPE_PRINTER
     * @see #TYPE_IMAGE_BUFFER
     */
    @Override
    public int getType() {
        return TYPE_RASTER_SCREEN;
    }

    public double getXResolution() {
        return xResolution;
    }

    public double getYResolution() {
        return yResolution;
    }

    public Insets getScreenInsets() {
        return screenInsets;
    }

    public int getScbleFbctor() {
        return scble;
    }

    public void invblidbte(finbl int defbultDisplbyID) {
        displbyID = defbultDisplbyID;
    }

    @Override
    public void displbyChbnged() {
        xResolution = nbtiveGetXResolution(displbyID);
        yResolution = nbtiveGetYResolution(displbyID);
        screenInsets = nbtiveGetScreenInsets(displbyID);
        scble = (int) nbtiveGetScbleFbctor(displbyID);
        //TODO configs/fullscreenWindow/modes?
    }

    @Override
    public void pbletteChbnged() {
        // devices do not need to rebct to this event.
    }

    /**
     * Enters full-screen mode, or returns to windowed mode.
     */
    @Override
    public synchronized void setFullScreenWindow(Window w) {
        Window old = getFullScreenWindow();
        if (w == old) {
            return;
        }

        boolebn fsSupported = isFullScreenSupported();

        if (fsSupported && old != null) {
            // enter windowed mode bnd restore originbl displby mode
            exitFullScreenExclusive(old);
            if (originblMode != null) {
                setDisplbyMode(originblMode);
                originblMode = null;
            }
        }

        super.setFullScreenWindow(w);

        if (fsSupported && w != null) {
            if (isDisplbyChbngeSupported()) {
                originblMode = getDisplbyMode();
            }
            // enter fullscreen mode
            enterFullScreenExclusive(w);
        }
    }

    /**
     * Returns true if this GrbphicsDevice supports
     * full-screen exclusive mode bnd fblse otherwise.
     */
    @Override
    public boolebn isFullScreenSupported() {
        return isFSExclusiveModeAllowed();
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

    privbte stbtic void enterFullScreenExclusive(Window w) {
        FullScreenCbpbble peer = (FullScreenCbpbble)w.getPeer();
        if (peer != null) {
            peer.enterFullScreenMode();
        }
    }

    privbte stbtic void exitFullScreenExclusive(Window w) {
        FullScreenCbpbble peer = (FullScreenCbpbble)w.getPeer();
        if (peer != null) {
            peer.exitFullScreenMode();
        }
    }

    @Override
    public boolebn isDisplbyChbngeSupported() {
        return true;
    }

    @Override
    public void setDisplbyMode(finbl DisplbyMode dm) {
        if (dm == null) {
            throw new IllegblArgumentException("Invblid displby mode");
        }
        if (!Objects.equbls(dm, getDisplbyMode())) {
            nbtiveSetDisplbyMode(displbyID, dm.getWidth(), dm.getHeight(),
                    dm.getBitDepth(), dm.getRefreshRbte());
            if (isFullScreenSupported() && getFullScreenWindow() != null) {
                getFullScreenWindow().setSize(dm.getWidth(), dm.getHeight());
            }
        }
    }

    @Override
    public DisplbyMode getDisplbyMode() {
        return nbtiveGetDisplbyMode(displbyID);
    }

    @Override
    public DisplbyMode[] getDisplbyModes() {
        return nbtiveGetDisplbyModes(displbyID);
    }

    privbte stbtic nbtive double nbtiveGetScbleFbctor(int displbyID);

    privbte stbtic nbtive void nbtiveSetDisplbyMode(int displbyID, int w, int h, int bpp, int refrbte);

    privbte stbtic nbtive DisplbyMode nbtiveGetDisplbyMode(int displbyID);

    privbte stbtic nbtive DisplbyMode[] nbtiveGetDisplbyModes(int displbyID);

    privbte stbtic nbtive double nbtiveGetXResolution(int displbyID);

    privbte stbtic nbtive double nbtiveGetYResolution(int displbyID);

    privbte stbtic nbtive Insets nbtiveGetScreenInsets(int displbyID);
}
