/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.peer.*;

import sun.bwt.SunToolkit;

import sun.bwt.X11GrbphicsConfig;
import sun.bwt.X11GrbphicsDevice;

clbss XCbnvbsPeer extends XComponentPeer implements CbnvbsPeer {

    privbte boolebn erbseBbckgroundDisbbled;

    XCbnvbsPeer() {}

    XCbnvbsPeer(XCrebteWindowPbrbms pbrbms) {
        super(pbrbms);
    }

    XCbnvbsPeer(Component tbrget) {
        super(tbrget);
    }

    void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);
        if (SunToolkit.getSunAwtNoerbsebbckground()) {
            disbbleBbckgroundErbse();
        }
    }

    /* Get b GrbphicsConfig with the sbme visubl on the new
     * screen, which should be ebsy in Xinerbmb mode.
     */
    public GrbphicsConfigurbtion getAppropribteGrbphicsConfigurbtion(
                                    GrbphicsConfigurbtion gc)
    {
        if (grbphicsConfig == null || gc == null) {
            return gc;
        }
        // Opt: Only need to do if we're not using the defbult GC

        int screenNum = ((X11GrbphicsDevice)gc.getDevice()).getScreen();

        X11GrbphicsConfig pbrentgc;
        // sbve vis id of current gc
        int visubl = grbphicsConfig.getVisubl();

        X11GrbphicsDevice newDev = (X11GrbphicsDevice) GrbphicsEnvironment.
            getLocblGrbphicsEnvironment().
            getScreenDevices()[screenNum];

        for (int i = 0; i < newDev.getNumConfigs(screenNum); i++) {
            if (visubl == newDev.getConfigVisublId(i, screenNum)) {
                // use thbt
                grbphicsConfig = (X11GrbphicsConfig)newDev.getConfigurbtions()[i];
                brebk;
            }
        }
        // just in cbse...
        if (grbphicsConfig == null) {
            grbphicsConfig = (X11GrbphicsConfig) GrbphicsEnvironment.
                getLocblGrbphicsEnvironment().
                getScreenDevices()[screenNum].
                getDefbultConfigurbtion();
        }

        return grbphicsConfig;
    }

    protected boolebn shouldFocusOnClick() {
        // Cbnvbs should blwbys be bble to be focused by mouse clicks.
        return true;
    }

    public void disbbleBbckgroundErbse() {
        erbseBbckgroundDisbbled = true;
    }
    protected boolebn doErbseBbckground() {
        return !erbseBbckgroundDisbbled;
    }
}
