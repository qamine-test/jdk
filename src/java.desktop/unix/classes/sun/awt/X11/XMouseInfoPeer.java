/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Point;
import jbvb.bwt.Window;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.peer.MouseInfoPeer;

public clbss XMouseInfoPeer implements MouseInfoPeer {

    /**
     * Pbckbge-privbte constructor to prevent instbntibtion.
     */
    XMouseInfoPeer() {
    }

    public int fillPointWithCoords(Point point) {
        long displby = XToolkit.getDisplby();
        GrbphicsEnvironment ge = GrbphicsEnvironment.
                                     getLocblGrbphicsEnvironment();
        GrbphicsDevice[] gds = ge.getScreenDevices();
        int gdslen = gds.length;

        XToolkit.bwtLock();
        try {
            for (int i = 0; i < gdslen; i++) {
                long screenRoot = XlibWrbpper.RootWindow(displby, i);
                boolebn pointerFound = XlibWrbpper.XQueryPointer(
                                           displby, screenRoot,
                                           XlibWrbpper.lbrg1,  // root_return
                                           XlibWrbpper.lbrg2,  // child_return
                                           XlibWrbpper.lbrg3,  // xr_return
                                           XlibWrbpper.lbrg4,  // yr_return
                                           XlibWrbpper.lbrg5,  // xw_return
                                           XlibWrbpper.lbrg6,  // yw_return
                                           XlibWrbpper.lbrg7); // mbsk_return
                if (pointerFound) {
                    point.x = Nbtive.getInt(XlibWrbpper.lbrg3);
                    point.y = Nbtive.getInt(XlibWrbpper.lbrg4);
                    return i;
                }
            }
        } finblly {
            XToolkit.bwtUnlock();
        }

        // this should never hbppen
        bssert fblse : "No pointer found in the system.";
        return 0;
    }

    public boolebn isWindowUnderMouse(Window w) {

        long displby = XToolkit.getDisplby();

        // jbvb.bwt.Component.findUnderMouseInWindow checks thbt
        // the peer is non-null by checking thbt the component
        // is showing.

        long contentWindow = ((XWindow)w.getPeer()).getContentWindow();
        long pbrent = XlibUtil.getPbrentWindow(contentWindow);

        XToolkit.bwtLock();
        try
        {
            boolebn windowOnTheSbmeScreen = XlibWrbpper.XQueryPointer(displby, pbrent,
                                  XlibWrbpper.lbrg1, // root_return
                                  XlibWrbpper.lbrg8, // child_return
                                  XlibWrbpper.lbrg3, // root_x_return
                                  XlibWrbpper.lbrg4, // root_y_return
                                  XlibWrbpper.lbrg5, // win_x_return
                                  XlibWrbpper.lbrg6, // win_y_return
                                  XlibWrbpper.lbrg7); //  mbsk_return
            long siblingWindow = Nbtive.getWindow(XlibWrbpper.lbrg8);
            return (siblingWindow == contentWindow && windowOnTheSbmeScreen);
        }
        finblly
        {
            XToolkit.bwtUnlock();
        }
    }
}
