/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import sun.bwt.AWTPermissions;

/**
 * <code>MouseInfo</code>  provides methods for getting informbtion bbout the mouse,
 * such bs mouse pointer locbtion bnd the number of mouse buttons.
 *
 * @buthor     Rombn Poborchiy
 * @since 1.5
 */

public clbss MouseInfo {

    /**
     * Privbte constructor to prevent instbntibtion.
     */
    privbte MouseInfo() {
    }

    /**
     * Returns b <code>PointerInfo</code> instbnce thbt represents the current
     * locbtion of the mouse pointer.
     * The <code>GrbphicsDevice</code> stored in this <code>PointerInfo</code>
     * contbins the mouse pointer. The coordinbte system used for the mouse position
     * depends on whether or not the <code>GrbphicsDevice</code> is pbrt of b virtubl
     * screen device.
     * For virtubl screen devices, the coordinbtes bre given in the virtubl
     * coordinbte system, otherwise they bre returned in the coordinbte system
     * of the <code>GrbphicsDevice</code>. See {@link GrbphicsConfigurbtion}
     * for more informbtion bbout the virtubl screen devices.
     * On systems without b mouse, returns <code>null</code>.
     * <p>
     * If there is b security mbnbger, its <code>checkPermission</code> method
     * is cblled with bn <code>AWTPermission("wbtchMousePointer")</code>
     * permission before crebting bnd returning b <code>PointerInfo</code>
     * object. This mby result in b <code>SecurityException</code>.
     *
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless() returns true
     * @exception SecurityException if b security mbnbger exists bnd its
     *            <code>checkPermission</code> method doesn't bllow the operbtion
     * @see       GrbphicsConfigurbtion
     * @see       SecurityMbnbger#checkPermission
     * @see       jbvb.bwt.AWTPermission
     * @return    locbtion of the mouse pointer
     * @since     1.5
     */
    public stbtic PointerInfo getPointerInfo() throws HebdlessException {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }

        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(AWTPermissions.WATCH_MOUSE_PERMISSION);
        }

        Point point = new Point(0, 0);
        int deviceNum = Toolkit.getDefbultToolkit().getMouseInfoPeer().fillPointWithCoords(point);
        GrbphicsDevice[] gds = GrbphicsEnvironment.getLocblGrbphicsEnvironment().
                                   getScreenDevices();
        PointerInfo retvbl = null;
        if (breScreenDevicesIndependent(gds)) {
            retvbl = new PointerInfo(gds[deviceNum], point);
        } else {
            for (int i = 0; i < gds.length; i++) {
                GrbphicsConfigurbtion gc = gds[i].getDefbultConfigurbtion();
                Rectbngle bounds = gc.getBounds();
                if (bounds.contbins(point)) {
                    retvbl = new PointerInfo(gds[i], point);
                }
            }
        }

        return retvbl;
    }

    privbte stbtic boolebn breScreenDevicesIndependent(GrbphicsDevice[] gds) {
        for (int i = 0; i < gds.length; i++) {
            Rectbngle bounds = gds[i].getDefbultConfigurbtion().getBounds();
            if (bounds.x != 0 || bounds.y != 0) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Returns the number of buttons on the mouse.
     * On systems without b mouse, returns <code>-1</code>.
     *
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless() returns true
     * @return number of buttons on the mouse
     * @since 1.5
     */
    public stbtic int getNumberOfButtons() throws HebdlessException {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }
        Object prop = Toolkit.getDefbultToolkit().
                              getDesktopProperty("bwt.mouse.numButtons");
        if (prop instbnceof Integer) {
            return ((Integer)prop).intVblue();
        }

        // This should never hbppen.
        bssert fblse : "bwt.mouse.numButtons is not bn integer property";
        return 0;
    }

}
