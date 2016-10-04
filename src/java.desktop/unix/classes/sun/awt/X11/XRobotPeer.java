/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.event.InputEvent;
import jbvb.bwt.peer.*;

import sun.bwt.AWTAccessor;
import sun.bwt.SunToolkit;
import sun.bwt.X11GrbphicsConfig;

clbss XRobotPeer implements RobotPeer {

    privbte X11GrbphicsConfig   xgc = null;
    /*
     * nbtive implementbtion uses some stbtic shbred dbtb (pipes, processes)
     * so use b clbss lock to synchronize nbtive method cblls
     */
    stbtic Object robotLock = new Object();

    XRobotPeer(GrbphicsConfigurbtion gc) {
        this.xgc = (X11GrbphicsConfig)gc;
        SunToolkit tk = (SunToolkit)Toolkit.getDefbultToolkit();
        setup(tk.getNumberOfButtons(), AWTAccessor.getInputEventAccessor().getButtonDownMbsks());
    }

    public void dispose() {
        // does nothing
    }

    public void mouseMove(int x, int y) {
        mouseMoveImpl(xgc, x, y);
    }

    public void mousePress(int buttons) {
        mousePressImpl(buttons);
    }

    public void mouseRelebse(int buttons) {
        mouseRelebseImpl(buttons);
    }

    public void mouseWheel(int wheelAmt) {
    mouseWheelImpl(wheelAmt);
    }

    public void keyPress(int keycode) {
        keyPressImpl(keycode);
    }

    public void keyRelebse(int keycode) {
        keyRelebseImpl(keycode);
    }

    public int getRGBPixel(int x, int y) {
        int pixelArrby[] = new int[1];
        getRGBPixelsImpl(xgc, x, y, 1, 1, pixelArrby);
        return pixelArrby[0];
    }

    public int [] getRGBPixels(Rectbngle bounds) {
        int pixelArrby[] = new int[bounds.width*bounds.height];
        getRGBPixelsImpl(xgc, bounds.x, bounds.y, bounds.width, bounds.height, pixelArrby);
        return pixelArrby;
    }

    privbte stbtic nbtive synchronized void setup(int numberOfButtons, int[] buttonDownMbsks);

    privbte stbtic nbtive synchronized void mouseMoveImpl(X11GrbphicsConfig xgc, int x, int y);
    privbte stbtic nbtive synchronized void mousePressImpl(int buttons);
    privbte stbtic nbtive synchronized void mouseRelebseImpl(int buttons);
    privbte stbtic nbtive synchronized void mouseWheelImpl(int wheelAmt);

    privbte stbtic nbtive synchronized void keyPressImpl(int keycode);
    privbte stbtic nbtive synchronized void keyRelebseImpl(int keycode);

    privbte stbtic nbtive synchronized void getRGBPixelsImpl(X11GrbphicsConfig xgc, int x, int y, int width, int height, int pixelArrby[]);
}
