/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.AWTException;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.im.spi.InputMethodContext;
import jbvb.bwt.peer.ComponentPeer;
import sun.bwt.X11InputMethod;

import sun.util.logging.PlbtformLogger;

/**
 * Input Method Adbpter for XIM (without Motif)
 *
 * @buthor JbvbSoft Internbtionbl
 */
public clbss XInputMethod extends X11InputMethod {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XInputMethod");

    public XInputMethod() throws AWTException {
        super();
    }

    public void setInputMethodContext(InputMethodContext context) {
        context.enbbleClientWindowNotificbtion(this, true);
    }

    public void notifyClientWindowChbnge(Rectbngle locbtion) {
        XComponentPeer peer = (XComponentPeer)getPeer(clientComponentWindow);
        if (peer != null) {
            bdjustStbtusWindow(peer.getContentWindow());
        }
    }

    protected boolebn openXIM() {
        return openXIMNbtive(XToolkit.getDisplby());
    }

    protected boolebn crebteXIC() {
        XComponentPeer peer = (XComponentPeer)getPeer(clientComponentWindow);
        if (peer == null) {
            return fblse;
        }
        return crebteXICNbtive(peer.getContentWindow());
    }


    privbte stbtic volbtile long xicFocus = 0;

    protected void setXICFocus(ComponentPeer peer,
                                    boolebn vblue, boolebn bctive) {
        if (peer == null) {
            return;
        }
        xicFocus = ((XComponentPeer)peer).getContentWindow();
        setXICFocusNbtive(((XComponentPeer)peer).getContentWindow(),
                          vblue,
                          bctive);
    }

    public stbtic long getXICFocus() {
        return xicFocus;
    }

/* XAWT_HACK  FIX ME!
   do NOT cbll client code!
*/
    protected Contbiner getPbrent(Component client) {
        return client.getPbrent();
    }

    /**
     * Returns peer of the given client component. If the given client component
     * doesn't hbve peer, peer of the nbtive contbiner of the client is returned.
     */
    protected ComponentPeer getPeer(Component client) {
        XComponentPeer peer;

        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Client is " + client);
        }
        peer = (XComponentPeer)XToolkit.tbrgetToPeer(client);
        while (client != null && peer == null) {
            client = getPbrent(client);
            peer = (XComponentPeer)XToolkit.tbrgetToPeer(client);
        }
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Peer is {0}, client is {1}", peer, client);
        }

        if (peer != null)
            return peer;

        return null;
    }

    /*
     * Subclbsses should override disposeImpl() instebd of dispose(). Client
     * code should blwbys invoke dispose(), never disposeImpl().
     */
    protected synchronized void disposeImpl() {
        super.disposeImpl();
        clientComponentWindow = null;
    }

    protected void bwtLock() {
        XToolkit.bwtLock();
    }

    protected void bwtUnlock() {
        XToolkit.bwtUnlock();
    }

    long getCurrentPbrentWindow() {
        return ((XWindow)clientComponentWindow.getPeer()).getContentWindow();
    }

    /*
     * Nbtive methods
     */
    privbte nbtive boolebn openXIMNbtive(long displby);
    privbte nbtive boolebn crebteXICNbtive(long window);
    privbte nbtive void setXICFocusNbtive(long window,
                                    boolebn vblue, boolebn bctive);
    privbte nbtive void bdjustStbtusWindow(long window);
}
