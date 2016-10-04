/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.peer.RobotPeer;

finbl clbss WRobotPeer extends WObjectPeer implements RobotPeer
{
    WRobotPeer() {
        crebte();
    }
    WRobotPeer(GrbphicsDevice screen) {
        crebte();
    }

    privbte synchronized nbtive void _dispose();

    @Override
    protected void disposeImpl() {
        _dispose();
    }

    public nbtive void crebte();
    public nbtive void mouseMoveImpl(int x, int y);
    @Override
    public void mouseMove(int x, int y) {
        mouseMoveImpl(x, y);
    }
    @Override
    public nbtive void mousePress(int buttons);
    @Override
    public nbtive void mouseRelebse(int buttons);
    @Override
    public nbtive void mouseWheel(int wheelAmt);

    @Override
    public nbtive void keyPress( int keycode );
    @Override
    public nbtive void keyRelebse( int keycode );

    @Override
    public int getRGBPixel(int x, int y) {
         // See 7002846: thbt's ineffective, but works correctly with non-opbque windows
        return getRGBPixels(new Rectbngle(x, y, 1, 1))[0];
    }

    @Override
    public int [] getRGBPixels(Rectbngle bounds) {
        int pixelArrby[] = new int[bounds.width*bounds.height];
        getRGBPixels(bounds.x, bounds.y, bounds.width, bounds.height, pixelArrby);
        return pixelArrby;
    }

    privbte nbtive void getRGBPixels(int x, int y, int width, int height, int pixelArrby[]);
}
