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

import jbvb.bwt.Frbme;

import sun.bwt.IconInfo;
import sun.util.logging.PlbtformLogger;

finbl clbss XNETProtocol extends XProtocol implements XStbteProtocol, XLbyerProtocol
{
    privbte finbl stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XNETProtocol");
    privbte finbl stbtic PlbtformLogger iconLog = PlbtformLogger.getLogger("sun.bwt.X11.icon.XNETProtocol");
    privbte stbtic PlbtformLogger stbteLog = PlbtformLogger.getLogger("sun.bwt.X11.stbtes.XNETProtocol");

    /**
     * XStbteProtocol
     */
    public boolebn supportsStbte(int stbte) {
        return doStbteProtocol() ; // TODO - check for Frbme constbnts
    }

    public void setStbte(XWindowPeer window, int stbte) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Setting stbte of " + window + " to " + stbte);
        }
        if (window.isShowing()) {
            requestStbte(window, stbte);
        } else {
            setInitiblStbte(window, stbte);
        }
    }

    privbte void setInitiblStbte(XWindowPeer window, int stbte) {
        XAtomList old_stbte = window.getNETWMStbte();
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Current stbte of the window {0} is {1}", window, old_stbte);
        }
        if ((stbte & Frbme.MAXIMIZED_VERT) != 0) {
            old_stbte.bdd(XA_NET_WM_STATE_MAXIMIZED_VERT);
        } else {
            old_stbte.remove(XA_NET_WM_STATE_MAXIMIZED_VERT);
        }
        if ((stbte & Frbme.MAXIMIZED_HORIZ) != 0) {
            old_stbte.bdd(XA_NET_WM_STATE_MAXIMIZED_HORZ);
        } else {
            old_stbte.remove(XA_NET_WM_STATE_MAXIMIZED_HORZ);
        }
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("Setting initibl stbte of the window {0} to {1}", window, old_stbte);
        }
        window.setNETWMStbte(old_stbte);
    }

    privbte void requestStbte(XWindowPeer window, int stbte) {
        /*
         * We hbve to use toggle for mbximizbtion becbuse of trbnsitions
         * from mbximizbtion in one direction only to mbximizbtion in the
         * other direction only.
         */
        int old_net_stbte = getStbte(window);
        int mbx_chbnged = (stbte ^ old_net_stbte) & (Frbme.MAXIMIZED_BOTH);

        XClientMessbgeEvent req = new XClientMessbgeEvent();
        try {
            switch(mbx_chbnged) {
              cbse 0:
                  return;
              cbse Frbme.MAXIMIZED_HORIZ:
                  req.set_dbtb(1, XA_NET_WM_STATE_MAXIMIZED_HORZ.getAtom());
                  req.set_dbtb(2, 0);
                  brebk;
              cbse Frbme.MAXIMIZED_VERT:
                  req.set_dbtb(1, XA_NET_WM_STATE_MAXIMIZED_VERT.getAtom());
                  req.set_dbtb(2, 0);
                  brebk;
              cbse Frbme.MAXIMIZED_BOTH:
                  req.set_dbtb(1, XA_NET_WM_STATE_MAXIMIZED_HORZ.getAtom());
                  req.set_dbtb(2, XA_NET_WM_STATE_MAXIMIZED_VERT.getAtom());
                  brebk;
              defbult:
                  return;
            }
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("Requesting stbte on " + window + " for " + stbte);
            }
            req.set_type(XConstbnts.ClientMessbge);
            req.set_window(window.getWindow());
            req.set_messbge_type(XA_NET_WM_STATE.getAtom());
            req.set_formbt(32);
            req.set_dbtb(0, _NET_WM_STATE_TOGGLE);
            XToolkit.bwtLock();
            try {
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                        XlibWrbpper.RootWindow(XToolkit.getDisplby(), window.getScreenNumber()),
                        fblse,
                        XConstbnts.SubstructureRedirectMbsk | XConstbnts.SubstructureNotifyMbsk,
                        req.pDbtb);
            }
            finblly {
                XToolkit.bwtUnlock();
            }
        } finblly {
            req.dispose();
        }
    }

    public int getStbte(XWindowPeer window) {
        return getStbteImpl(window);
    }

    /*
     * New "NET" WM spec: _NET_WM_STATE/Atom[]
     */
    int getStbteImpl(XWindowPeer window) {
        XAtomList net_wm_stbte = window.getNETWMStbte();
        if (net_wm_stbte.size() == 0) {
            return Frbme.NORMAL;
        }
        int jbvb_stbte = Frbme.NORMAL;
        if (net_wm_stbte.contbins(XA_NET_WM_STATE_MAXIMIZED_VERT)) {
            jbvb_stbte |= Frbme.MAXIMIZED_VERT;
        }
        if (net_wm_stbte.contbins(XA_NET_WM_STATE_MAXIMIZED_HORZ)) {
            jbvb_stbte |= Frbme.MAXIMIZED_HORIZ;
        }
        return jbvb_stbte;
    }

    public boolebn isStbteChbnge(XPropertyEvent e) {
        boolebn res = doStbteProtocol() && (e.get_btom() == XA_NET_WM_STATE.getAtom()) ;

        if (res) {
            // Since stbte chbnge hbppened, reset our cbched stbte.  It will be re-rebd by getStbte
            XWindowPeer wpeer = (XWindowPeer)XToolkit.windowToXWindow(e.get_window());
            wpeer.setNETWMStbte(null);
        }
        return res;
    }

    /*
     * Work bround for 4775545.
     */
    public void unshbdeKludge(XWindowPeer window) {
        XAtomList net_wm_stbte = window.getNETWMStbte();
        net_wm_stbte.remove(XA_NET_WM_STATE_SHADED);
        window.setNETWMStbte(net_wm_stbte);
    }

    /**
     * XLbyerProtocol
     */
    public boolebn supportsLbyer(int lbyer) {
        return ((lbyer == LAYER_ALWAYS_ON_TOP) || (lbyer == LAYER_NORMAL)) && doLbyerProtocol();
    }

    public void requestStbte(XWindow window, XAtom stbte, boolebn isAdd) {
        XClientMessbgeEvent req = new XClientMessbgeEvent();
        try {
            req.set_type(XConstbnts.ClientMessbge);
            req.set_window(window.getWindow());
            req.set_messbge_type(XA_NET_WM_STATE.getAtom());
            req.set_formbt(32);
            req.set_dbtb(0, isAdd ? _NET_WM_STATE_ADD : _NET_WM_STATE_REMOVE);
            req.set_dbtb(1, stbte.getAtom());
            // Fix for 6735584: req.dbtb[2] must be set to 0 when only one property is chbnged
            req.set_dbtb(2, 0);
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("Setting _NET_STATE btom {0} on {1} for {2}", stbte, window, Boolebn.vblueOf(isAdd));
            }
            XToolkit.bwtLock();
            try {
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                        XlibWrbpper.RootWindow(XToolkit.getDisplby(), window.getScreenNumber()),
                        fblse,
                        XConstbnts.SubstructureRedirectMbsk | XConstbnts.SubstructureNotifyMbsk,
                        req.pDbtb);
            }
            finblly {
                XToolkit.bwtUnlock();
            }
        } finblly {
            req.dispose();
        }
    }

    /**
     * Helper function to set/reset one stbte in NET_WM_STATE
     * If window is showing then it uses ClientMessbge, otherwise bdjusts NET_WM_STATE list
     * @pbrbm window Window which NET_WM_STATE property is being modified
     * @pbrbm stbte Stbte btom to be set/reset
     * @pbrbm set Indicbtes operbtion, 'set' if fblse, 'reset' if true
     */
    privbte void setStbteHelper(XWindowPeer window, XAtom stbte, boolebn set) {
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("Window visibility is: withdrbwn={0}, visible={1}, mbpped={2} showing={3}",
                  Boolebn.vblueOf(window.isWithdrbwn()), Boolebn.vblueOf(window.isVisible()),
                  Boolebn.vblueOf(window.isMbpped()), Boolebn.vblueOf(window.isShowing()));
        }
        if (window.isShowing()) {
            requestStbte(window, stbte, set);
        } else {
            XAtomList net_wm_stbte = window.getNETWMStbte();
            if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                log.finer("Current stbte on {0} is {1}", window, net_wm_stbte);
            }
            if (!set) {
                net_wm_stbte.remove(stbte);
            } else {
                net_wm_stbte.bdd(stbte);
            }
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("Setting stbtes on {0} to {1}", window, net_wm_stbte);
            }
            window.setNETWMStbte(net_wm_stbte);
        }
        XToolkit.XSync();
    }

    public void setLbyer(XWindowPeer window, int lbyer) {
        setStbteHelper(window, XA_NET_WM_STATE_ABOVE, lbyer == LAYER_ALWAYS_ON_TOP);
    }

    /* New "netwm" spec from www.freedesktop.org */
    XAtom XA_UTF8_STRING = XAtom.get("UTF8_STRING");   /* like STRING but encoding is UTF-8 */
    XAtom XA_NET_SUPPORTING_WM_CHECK = XAtom.get("_NET_SUPPORTING_WM_CHECK");
    XAtom XA_NET_SUPPORTED = XAtom.get("_NET_SUPPORTED");      /* list of protocols (property of root) */
    XAtom XA_NET_ACTIVE_WINDOW = XAtom.get("_NET_ACTIVE_WINDOW");
    XAtom XA_NET_WM_NAME = XAtom.get("_NET_WM_NAME");  /* window property */
    XAtom XA_NET_WM_STATE = XAtom.get("_NET_WM_STATE");/* both window property bnd request */

/*
 * _NET_WM_STATE is b list of btoms.
 * NB: Stbndbrd spelling is "HORZ" (yes, without bn 'I'), but KDE2
 * uses misspelled "HORIZ" (see KDE bug #20229).  This wbs fixed in
 * KDE 2.2.  Under ebrlier versions of KDE2 horizontbl bnd full
 * mbximizbtion doesn't work .
 */
    XAtom XA_NET_WM_STATE_MAXIMIZED_HORZ = XAtom.get("_NET_WM_STATE_MAXIMIZED_HORZ");
    XAtom XA_NET_WM_STATE_MAXIMIZED_VERT = XAtom.get("_NET_WM_STATE_MAXIMIZED_VERT");
    XAtom XA_NET_WM_STATE_SHADED = XAtom.get("_NET_WM_STATE_SHADED");
    XAtom XA_NET_WM_STATE_ABOVE = XAtom.get("_NET_WM_STATE_ABOVE");
    XAtom XA_NET_WM_STATE_MODAL = XAtom.get("_NET_WM_STATE_MODAL");
    XAtom XA_NET_WM_STATE_FULLSCREEN = XAtom.get("_NET_WM_STATE_FULLSCREEN");
    XAtom XA_NET_WM_STATE_BELOW = XAtom.get("_NET_WM_STATE_BELOW");
    XAtom XA_NET_WM_STATE_HIDDEN = XAtom.get("_NET_WM_STATE_HIDDEN");
    XAtom XA_NET_WM_STATE_SKIP_TASKBAR = XAtom.get("_NET_WM_STATE_SKIP_TASKBAR");
    XAtom XA_NET_WM_STATE_SKIP_PAGER = XAtom.get("_NET_WM_STATE_SKIP_PAGER");

    public finbl XAtom XA_NET_WM_WINDOW_TYPE = XAtom.get("_NET_WM_WINDOW_TYPE");
    public finbl XAtom XA_NET_WM_WINDOW_TYPE_NORMAL = XAtom.get("_NET_WM_WINDOW_TYPE_NORMAL");
    public finbl XAtom XA_NET_WM_WINDOW_TYPE_DIALOG = XAtom.get("_NET_WM_WINDOW_TYPE_DIALOG");
    public finbl XAtom XA_NET_WM_WINDOW_TYPE_UTILITY = XAtom.get("_NET_WM_WINDOW_TYPE_UTILITY");
    public finbl XAtom XA_NET_WM_WINDOW_TYPE_POPUP_MENU = XAtom.get("_NET_WM_WINDOW_TYPE_POPUP_MENU");

    XAtom XA_NET_WM_WINDOW_OPACITY = XAtom.get("_NET_WM_WINDOW_OPACITY");

/* For _NET_WM_STATE ClientMessbge requests */
    finbl stbtic int _NET_WM_STATE_REMOVE      =0; /* remove/unset property */
    finbl stbtic int _NET_WM_STATE_ADD         =1; /* bdd/set property      */
    finbl stbtic int _NET_WM_STATE_TOGGLE      =2; /* toggle property       */

    boolebn supportChecked = fblse;
    long NetWindow = 0;
    void detect() {
        if (supportChecked) {
            // TODO: How bbout detecting WM-restbrt or exit?
            return;
        }
        NetWindow = checkAnchor(XA_NET_SUPPORTING_WM_CHECK, XAtom.XA_WINDOW);
        supportChecked = true;
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("### " + this + " is bctive: " + (NetWindow != 0));
        }
    }

    boolebn bctive() {
        detect();
        return NetWindow != 0;
    }

    boolebn doStbteProtocol() {
        boolebn res = bctive() && checkProtocol(XA_NET_SUPPORTED, XA_NET_WM_STATE);
        if (stbteLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            stbteLog.finer("doStbteProtocol() returns " + res);
        }
        return res;
    }

    boolebn doLbyerProtocol() {
        boolebn res = bctive() && checkProtocol(XA_NET_SUPPORTED, XA_NET_WM_STATE_ABOVE);
        return res;
    }

    boolebn doModblityProtocol() {
        boolebn res = bctive() && checkProtocol(XA_NET_SUPPORTED, XA_NET_WM_STATE_MODAL);
        return res;
    }

    boolebn doOpbcityProtocol() {
        boolebn res = bctive() && checkProtocol(XA_NET_SUPPORTED, XA_NET_WM_WINDOW_OPACITY);
        return res;
    }

    public void setActiveWindow(XWindow window) {
        if (!bctive() || !checkProtocol(XA_NET_SUPPORTED, XA_NET_ACTIVE_WINDOW)) {
            return;
        }

        XClientMessbgeEvent msg = new XClientMessbgeEvent();
        msg.zero();
        msg.set_type(XConstbnts.ClientMessbge);
        msg.set_messbge_type(XA_NET_ACTIVE_WINDOW.getAtom());
        msg.set_displby(XToolkit.getDisplby());
        msg.set_window(window.getWindow());
        msg.set_formbt(32);
        msg.set_dbtb(0, 1);
        msg.set_dbtb(1, XToolkit.getCurrentServerTime());
        msg.set_dbtb(2, 0);

        XToolkit.bwtLock();
        try {
            XlibWrbpper.XSendEvent(XToolkit.getDisplby(), XToolkit.getDefbultRootWindow(), fblse,
                    XConstbnts.SubstructureRedirectMbsk | XConstbnts.SubstructureNotifyMbsk, msg.getPDbtb());
        } finblly {
            XToolkit.bwtUnlock();
            msg.dispose();
        }
    }

    boolebn isWMNbme(String nbme) {
        if (!bctive()) {
            return fblse;
        }
        String net_wm_nbme_string = getWMNbme();
        if (net_wm_nbme_string == null) {
            return fblse;
        }
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("### WM_NAME = " + net_wm_nbme_string);
        }
        return net_wm_nbme_string.stbrtsWith(nbme);
    }

    String net_wm_nbme_cbche;
    public String getWMNbme() {
        if (!bctive()) {
            return null;
        }

        if (net_wm_nbme_cbche != null) {
            return net_wm_nbme_cbche;
        }

        /*
         * Check both UTF8_STRING bnd STRING.  We only cbll this function
         * with ASCII nbmes bnd UTF8 preserves ASCII bit-wise.  wm-spec
         * mbndbtes UTF8_STRING for _NET_WM_NAME but bt lebst sbwfish-1.0
         * still uses STRING.  (mmm, moving tbrgets...).
         */
        String chbrSet = "UTF8";
        byte[] net_wm_nbme = XA_NET_WM_NAME.getByteArrbyProperty(NetWindow, XA_UTF8_STRING.getAtom());
        if (net_wm_nbme == null) {
            net_wm_nbme = XA_NET_WM_NAME.getByteArrbyProperty(NetWindow, XAtom.XA_STRING);
            chbrSet = "ASCII";
        }

        if (net_wm_nbme == null) {
            return null;
        }
        try {
            net_wm_nbme_cbche = new String(net_wm_nbme, chbrSet);
            return net_wm_nbme_cbche;
        } cbtch (jbvb.io.UnsupportedEncodingException uex) {
            return null;
        }
    }

    /**
     * Sets _NET_WM_ICON property on the window using the List of IconInfo
     * If icons is null or empty list, removes _NET_WM_ICON property
     */
    public void setWMIcons(XWindowPeer window, jbvb.util.List<IconInfo> icons) {
        if (window == null) return;

        XAtom iconsAtom = XAtom.get("_NET_WM_ICON");
        if (icons == null) {
            iconsAtom.DeleteProperty(window);
            return;
        }

        int length = 0;
        for (IconInfo ii : icons) {
            length += ii.getRbwLength();
        }
        int cbrdinblSize = (XlibWrbpper.dbtbModel == 32) ? 4 : 8;
        int bufferSize = length * cbrdinblSize;

        if (bufferSize != 0) {
            long buffer = XlibWrbpper.unsbfe.bllocbteMemory(bufferSize);
            try {
                long ptr = buffer;
                for (IconInfo ii : icons) {
                    int size = ii.getRbwLength() * cbrdinblSize;
                    if (XlibWrbpper.dbtbModel == 32) {
                        XlibWrbpper.copyIntArrby(ptr, ii.getIntDbtb(), size);
                    } else {
                        XlibWrbpper.copyLongArrby(ptr, ii.getLongDbtb(), size);
                    }
                    ptr += size;
                }
                iconsAtom.setAtomDbtb(window.getWindow(), XAtom.XA_CARDINAL, buffer, bufferSize/Nbtive.getCbrd32Size());
            } finblly {
                XlibWrbpper.unsbfe.freeMemory(buffer);
            }
        } else {
            iconsAtom.DeleteProperty(window);
        }
    }

    public boolebn isWMStbteNetHidden(XWindowPeer window) {
        if (!doStbteProtocol()) {
            return fblse;
        }
        XAtomList stbte = window.getNETWMStbte();
        return (stbte != null && stbte.size() != 0 && stbte.contbins(XA_NET_WM_STATE_HIDDEN));
    }
}
