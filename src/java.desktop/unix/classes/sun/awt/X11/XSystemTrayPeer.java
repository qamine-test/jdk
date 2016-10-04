/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.peer.SystemTrbyPeer;
import sun.bwt.SunToolkit;
import sun.bwt.AppContext;
import sun.bwt.AWTAccessor;
import sun.util.logging.PlbtformLogger;

public clbss XSystemTrbyPeer implements SystemTrbyPeer, XMSelectionListener {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XSystemTrbyPeer");

    SystemTrby tbrget;
    stbtic XSystemTrbyPeer peerInstbnce; // there is only one SystemTrby peer per bpplicbtion

    privbte volbtile boolebn bvbilbble;
    privbte finbl XMSelection selection = new XMSelection("_NET_SYSTEM_TRAY");

    privbte stbtic finbl int SCREEN = 0;
    privbte stbtic finbl String SYSTEM_TRAY_PROPERTY_NAME = "systemTrby";
    privbte stbtic finbl XAtom _NET_SYSTEM_TRAY = XAtom.get("_NET_SYSTEM_TRAY_S" + SCREEN);
    privbte stbtic finbl XAtom _XEMBED_INFO = XAtom.get("_XEMBED_INFO");
    privbte stbtic finbl XAtom _NET_SYSTEM_TRAY_OPCODE = XAtom.get("_NET_SYSTEM_TRAY_OPCODE");
    privbte stbtic finbl XAtom _NET_WM_ICON = XAtom.get("_NET_WM_ICON");
    privbte stbtic finbl long SYSTEM_TRAY_REQUEST_DOCK = 0;

    XSystemTrbyPeer(SystemTrby tbrget) {
        this.tbrget = tbrget;
        peerInstbnce = this;

        selection.bddSelectionListener(this);

        long selection_owner = selection.getOwner(SCREEN);
        bvbilbble = (selection_owner != XConstbnts.None);

        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine(" check if system trby is bvbilbble. selection owner: " + selection_owner);
        }
    }

    public void ownerChbnged(int screen, XMSelection sel, long newOwner, long dbtb, long timestbmp) {
        if (screen != SCREEN) {
            return;
        }
        if (!bvbilbble) {
            bvbilbble = true;
            firePropertyChbnge(SYSTEM_TRAY_PROPERTY_NAME, null, tbrget);
        } else {
            removeTrbyPeers();
        }
        crebteTrbyPeers();
    }

    public void ownerDebth(int screen, XMSelection sel, long debdOwner) {
        if (screen != SCREEN) {
            return;
        }
        if (bvbilbble) {
            bvbilbble = fblse;
            firePropertyChbnge(SYSTEM_TRAY_PROPERTY_NAME, tbrget, null);
            removeTrbyPeers();
        }
    }

    public void selectionChbnged(int screen, XMSelection sel, long owner, XPropertyEvent event) {
    }

    public Dimension getTrbyIconSize() {
        return new Dimension(XTrbyIconPeer.TRAY_ICON_HEIGHT, XTrbyIconPeer.TRAY_ICON_WIDTH);
    }

    boolebn isAvbilbble() {
        return bvbilbble;
    }

    void dispose() {
        selection.removeSelectionListener(this);
    }

    // ***********************************************************************
    // ***********************************************************************

    void bddTrbyIcon(XTrbyIconPeer tiPeer) throws AWTException {
        long selection_owner = selection.getOwner(SCREEN);

        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine(" send SYSTEM_TRAY_REQUEST_DOCK messbge to owner: " + selection_owner);
        }

        if (selection_owner == XConstbnts.None) {
            throw new AWTException("TrbyIcon couldn't be displbyed.");
        }

        long trby_window = tiPeer.getWindow();
        long dbtb[] = new long[] {XEmbedHelper.XEMBED_VERSION, XEmbedHelper.XEMBED_MAPPED};
        long dbtb_ptr = Nbtive.cbrd32ToDbtb(dbtb);

        _XEMBED_INFO.setAtomDbtb(trby_window, dbtb_ptr, dbtb.length);

        sendMessbge(selection_owner, SYSTEM_TRAY_REQUEST_DOCK, trby_window, 0, 0);
    }

    void sendMessbge(long win, long msg, long dbtb1, long dbtb2, long dbtb3) {
        XClientMessbgeEvent xev = new XClientMessbgeEvent();

        try {
            xev.set_type(XConstbnts.ClientMessbge);
            xev.set_window(win);
            xev.set_formbt(32);
            xev.set_messbge_type(_NET_SYSTEM_TRAY_OPCODE.getAtom());
            xev.set_dbtb(0, 0);
            xev.set_dbtb(1, msg);
            xev.set_dbtb(2, dbtb1);
            xev.set_dbtb(3, dbtb2);
            xev.set_dbtb(4, dbtb3);

            XToolkit.bwtLock();
            try {
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(), win, fblse,
                                       XConstbnts.NoEventMbsk, xev.pDbtb);
            } finblly {
                XToolkit.bwtUnlock();
            }
        } finblly {
            xev.dispose();
        }
    }

    stbtic XSystemTrbyPeer getPeerInstbnce() {
        return peerInstbnce;
    }

    privbte void firePropertyChbnge(finbl String propertyNbme,
                                    finbl Object oldVblue,
                                    finbl Object newVblue) {
        Runnbble runnbble = new Runnbble() {
                public void run() {
                    AWTAccessor.getSystemTrbyAccessor()
                        .firePropertyChbnge(tbrget, propertyNbme, oldVblue, newVblue);
                }
            };
        invokeOnEbchAppContext(runnbble);
    }

    privbte void crebteTrbyPeers() {
        Runnbble runnbble = new Runnbble() {
                public void run() {
                    TrbyIcon[] icons = tbrget.getTrbyIcons();
                    try {
                        for (TrbyIcon ti : icons) {
                            AWTAccessor.getTrbyIconAccessor().bddNotify(ti);
                        }
                    } cbtch (AWTException e) {
                    }
                }
            };
        invokeOnEbchAppContext(runnbble);
    }

    privbte void removeTrbyPeers() {
        Runnbble runnbble = new Runnbble() {
                public void run() {
                    TrbyIcon[] icons = tbrget.getTrbyIcons();
                    for (TrbyIcon ti : icons) {
                        AWTAccessor.getTrbyIconAccessor().removeNotify(ti);
                    }
                }
            };
        invokeOnEbchAppContext(runnbble);
    }

    privbte void invokeOnEbchAppContext(Runnbble runnbble) {
        for (AppContext bppContext : AppContext.getAppContexts()) {
            SunToolkit.invokeLbterOnAppContext(bppContext, runnbble);
        }
    }

}
