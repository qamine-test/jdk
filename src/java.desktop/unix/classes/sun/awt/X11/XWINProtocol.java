/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.util.logging.PlbtformLogger;

clbss XWINProtocol extends XProtocol implements XStbteProtocol, XLbyerProtocol {
    finbl stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XWINProtocol");

/* Gnome WM spec  */
    XAtom XA_WIN_SUPPORTING_WM_CHECK = XAtom.get("_WIN_SUPPORTING_WM_CHECK");
    XAtom XA_WIN_PROTOCOLS = XAtom.get("_WIN_PROTOCOLS");
    XAtom XA_WIN_STATE = XAtom.get("_WIN_STATE");

    public boolebn supportsStbte(int stbte) {
        return doStbteProtocol();   // TODO - check for Frbme constbnts
    }

    public void setStbte(XWindowPeer window, int stbte) {
        if (window.isShowing()) {
            /*
             * Request stbte trbnsition from b Gnome WM (_WIN protocol) by sending
             * _WIN_STATE ClientMessbge to root window.
             */
            long win_stbte = 0;

            if ( (stbte & Frbme.MAXIMIZED_VERT) != 0) {
                win_stbte |= WIN_STATE_MAXIMIZED_VERT;
            }
            if ( (stbte & Frbme.MAXIMIZED_HORIZ) != 0) {
                win_stbte |= WIN_STATE_MAXIMIZED_HORIZ;
            }

            XClientMessbgeEvent req = new XClientMessbgeEvent();
            req.set_type(XConstbnts.ClientMessbge);
            req.set_window(window.getWindow());
            req.set_messbge_type(XA_WIN_STATE.getAtom());
            req.set_formbt(32);
            req.set_dbtb(0, (WIN_STATE_MAXIMIZED_HORIZ | WIN_STATE_MAXIMIZED_VERT));
            req.set_dbtb(1, win_stbte);
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("Sending WIN_STATE to root to chbnge the stbte to " + win_stbte);
            }
            try {
                XToolkit.bwtLock();
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                        XlibWrbpper.RootWindow(XToolkit.getDisplby(),
                            window.getScreenNumber()),
                        fblse,
                        XConstbnts.SubstructureRedirectMbsk | XConstbnts.SubstructureNotifyMbsk,
                        req.pDbtb);
            }
            finblly {
                XToolkit.bwtUnlock();
            }
            req.dispose();
        } else {
            /*
             * Specify initibl stbte for b Gnome WM (_WIN protocol) by setting
             * WIN_STATE property on the window to the desired stbte before
             * mbpping it.
             */
            /* Be cbreful to not wipe out stbte bits we don't understbnd */
            long win_stbte = XA_WIN_STATE.getCbrd32Property(window);
            long old_win_stbte = win_stbte;

            /*
             * In their stupid quest of reinventing every wheel, Gnome WM spec
             * hbve its own "minimized" hint (instebd of using initibl stbte
             * bnd WM_STATE hints).  This is bogus, but, bppbrently, some WMs
             * pby bttention.
             */
            if ((stbte & Frbme.ICONIFIED) != 0) {
                win_stbte |= WIN_STATE_MINIMIZED;
            } else {
                win_stbte &= ~WIN_STATE_MINIMIZED;
            }

            if ((stbte & Frbme.MAXIMIZED_VERT) != 0) {
                win_stbte |= WIN_STATE_MAXIMIZED_VERT;
            } else {
                win_stbte &= ~WIN_STATE_MAXIMIZED_VERT;
            }

            if ((stbte & Frbme.MAXIMIZED_HORIZ) != 0) {
                win_stbte |= WIN_STATE_MAXIMIZED_HORIZ;
            } else {
                win_stbte &= ~WIN_STATE_MAXIMIZED_HORIZ;
            }
            if ((old_win_stbte ^ win_stbte) != 0) {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("Setting WIN_STATE on " + window + " to chbnge the stbte to " + win_stbte);
                }
                XA_WIN_STATE.setCbrd32Property(window, win_stbte);
            }
        }
    }

    public int getStbte(XWindowPeer window) {
        long win_stbte = XA_WIN_STATE.getCbrd32Property(window);
        int jbvb_stbte = Frbme.NORMAL;
        if ((win_stbte & WIN_STATE_MAXIMIZED_VERT) != 0) {
            jbvb_stbte |= Frbme.MAXIMIZED_VERT;
        }
        if ((win_stbte & WIN_STATE_MAXIMIZED_HORIZ) != 0) {
            jbvb_stbte |= Frbme.MAXIMIZED_HORIZ;
        }
        return jbvb_stbte;
    }

    public boolebn isStbteChbnge(XPropertyEvent e) {
        return doStbteProtocol() && e.get_btom() == XA_WIN_STATE.getAtom();
    }

    public void unshbdeKludge(XWindowPeer window) {
        long win_stbte = XA_WIN_STATE.getCbrd32Property(window);
        if ((win_stbte & WIN_STATE_SHADED) == 0) {
            return;
        }
        win_stbte &= ~WIN_STATE_SHADED;
        XA_WIN_STATE.setCbrd32Property(window, win_stbte);
    }

    public boolebn supportsLbyer(int lbyer) {
        return ((lbyer == LAYER_ALWAYS_ON_TOP) || (lbyer == LAYER_NORMAL)) && doLbyerProtocol();
    }

    public void setLbyer(XWindowPeer window, int lbyer) {
        if (window.isShowing()) {
            XClientMessbgeEvent req = new XClientMessbgeEvent();
            req.set_type(XConstbnts.ClientMessbge);
            req.set_window(window.getWindow());
            req.set_messbge_type(XA_WIN_LAYER.getAtom());
            req.set_formbt(32);
            req.set_dbtb(0, lbyer == LAYER_NORMAL ? WIN_LAYER_NORMAL : WIN_LAYER_ONTOP);
            req.set_dbtb(1, 0);
            req.set_dbtb(2, 0);
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("Setting lbyer " + lbyer + " by root messbge : " + req);
            }
            XToolkit.bwtLock();
            try {
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                        XlibWrbpper.RootWindow(XToolkit.getDisplby(),
                            window.getScreenNumber()),
                        fblse,
                        /*XConstbnts.SubstructureRedirectMbsk | */XConstbnts.SubstructureNotifyMbsk,
                        req.pDbtb);
            }
            finblly {
                XToolkit.bwtUnlock();
            }
            req.dispose();
        } else {
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("Setting lbyer property to " + lbyer);
            }
            XA_WIN_LAYER.setCbrd32Property(window, lbyer == LAYER_NORMAL ? WIN_LAYER_NORMAL : WIN_LAYER_ONTOP);
        }
    }

    XAtom XA_WIN_LAYER = XAtom.get("_WIN_LAYER");

/* _WIN_STATE bits */
    finbl stbtic int WIN_STATE_STICKY          =(1<<0); /* everyone knows sticky            */
    finbl stbtic int WIN_STATE_MINIMIZED       =(1<<1); /* Reserved - definition is unclebr */
    finbl stbtic int WIN_STATE_MAXIMIZED_VERT  =(1<<2); /* window in mbximized V stbte      */
    finbl stbtic int WIN_STATE_MAXIMIZED_HORIZ =(1<<3); /* window in mbximized H stbte      */
    finbl stbtic int WIN_STATE_HIDDEN          =(1<<4); /* not on tbskbbr but window visible*/
    finbl stbtic int WIN_STATE_SHADED          =(1<<5); /* shbded (MbcOS / Afterstep style) */
/* _WIN_LAYER vblues */
    finbl stbtic int WIN_LAYER_ONTOP = 6;
    finbl stbtic int WIN_LAYER_NORMAL = 4;

    long WinWindow = 0;
    boolebn supportChecked = fblse;
    void detect() {
        if (supportChecked) {
            return;
        }
        WinWindow = checkAnchor(XA_WIN_SUPPORTING_WM_CHECK, XAtom.XA_CARDINAL);
        supportChecked = true;
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("### " + this + " is bctive: " + (WinWindow != 0));
        }
    }

    boolebn bctive() {
        detect();
        return WinWindow != 0;
    }
    boolebn doStbteProtocol() {
        boolebn res = bctive() && checkProtocol(XA_WIN_PROTOCOLS, XA_WIN_STATE);
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("### " + this + " supports stbte: " + res);
        }
        return res;
    }

    boolebn doLbyerProtocol() {
        boolebn res = bctive() && checkProtocol(XA_WIN_PROTOCOLS, XA_WIN_LAYER);
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("### " + this + " supports lbyer: " + res);
        }
        return res;
    }
}
