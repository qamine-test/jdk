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

import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;

import jbvb.bwt.dnd.DnDConstbnts;
import jbvb.bwt.dnd.InvblidDnDOperbtionException;

import jbvb.util.Mbp;

/**
 * An bbstrbct clbss for drbg protocols on X11 systems.
 * Contbins protocol-independent drbg source code.
 *
 * @since 1.5
 */
bbstrbct clbss XDrbgSourceProtocol {
    privbte finbl XDrbgSourceProtocolListener listener;

    privbte boolebn initiblized = fblse;

    privbte long tbrgetWindow = 0;
    privbte long tbrgetProxyWindow = 0;
    privbte int tbrgetProtocolVersion = 0;
    privbte long tbrgetWindowMbsk = 0;

    // Alwbys use the XAWT root window bs the drbg source window.
    stbtic long getDrbgSourceWindow() {
        return XWindow.getXAWTRootWindow().getWindow();
    }

    protected XDrbgSourceProtocol(XDrbgSourceProtocolListener listener) {
        if (listener == null) {
            throw new NullPointerException("Null XDrbgSourceProtocolListener");
        }
        this.listener = listener;
    }

    protected finbl XDrbgSourceProtocolListener getProtocolListener() {
        return listener;
    }

    /**
     * Returns the protocol nbme. The protocol nbme cbnnot be null.
     */
    public bbstrbct String getProtocolNbme();

    /**
     * Initiblizes b drbg operbtion with the specified supported drop bctions,
     * contents bnd dbtb formbts.
     *
     * @pbrbm bctions b bitwise mbsk of <code>DnDConstbnts</code> thbt represent
     *                the supported drop bctions.
     * @pbrbm contents the contents for the drbg operbtion.
     * @pbrbm formbts bn brrby of Atoms thbt represent the supported dbtb formbts.
     * @pbrbm formbts bn brrby of Atoms thbt represent the supported dbtb formbts.
     * @throws InvblidDnDOperbtionException if b drbg operbtion is blrebdy
     * initiblized.
     * @throws IllegblArgumentException if some brgument hbs invblid vblue.
     * @throws XException if some X cbll fbiled.
     */
    public finbl void initiblizeDrbg(int bctions, Trbnsferbble contents,
                                     Mbp<Long, DbtbFlbvor> formbtMbp, long[] formbts)
      throws InvblidDnDOperbtionException,
             IllegblArgumentException, XException {
        XToolkit.bwtLock();
        try {
            try {
                if (initiblized) {
                    throw new InvblidDnDOperbtionException("Alrebdy initiblized");
                }

                initiblizeDrbgImpl(bctions, contents, formbtMbp, formbts);

                initiblized = true;
            } finblly {
                if (!initiblized) {
                    clebnup();
                }
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /* The cbller must hold AWT_LOCK. */
    protected bbstrbct void initiblizeDrbgImpl(int bctions,
                                               Trbnsferbble contents,
                                               Mbp<Long, DbtbFlbvor> formbtMbp,
                                               long[] formbts)
      throws InvblidDnDOperbtionException, IllegblArgumentException, XException;

    /**
     * Terminbtes the current drbg operbtion (if bny) bnd resets the internbl
     * stbte of this object.
     *
     * @throws XException if some X cbll fbiled.
     */
    public void clebnup() {
        initiblized = fblse;
        clebnupTbrgetInfo();
    }

    /**
     * Clebrs the informbtion on the current drop tbrget.
     *
     * @throws XException if some X cbll fbiled.
     */
    public void clebnupTbrgetInfo() {
        tbrgetWindow = 0;
        tbrgetProxyWindow = 0;
        tbrgetProtocolVersion = 0;
    }

    /**
     * Processes the specified client messbge event.
     *
     * @returns true if the event wbs successfully processed.
     */
    public bbstrbct boolebn processClientMessbge(XClientMessbgeEvent xclient)
      throws XException;

    /* The cbller must hold AWT_LOCK. */
    public finbl boolebn bttbchTbrgetWindow(long window, long time) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        TbrgetWindowInfo info = getTbrgetWindowInfo(window);
        if (info == null) {
            return fblse;
        } else {
            tbrgetWindow = window;
            tbrgetProxyWindow = info.getProxyWindow();
            tbrgetProtocolVersion = info.getProtocolVersion();
            return true;
        }
    }

    /* The cbller must hold AWT_LOCK. */
    public bbstrbct TbrgetWindowInfo getTbrgetWindowInfo(long window);

    /* The cbller must hold AWT_LOCK. */
    public bbstrbct void sendEnterMessbge(long[] formbts, int sourceAction,
                                          int sourceActions, long time);
    /* The cbller must hold AWT_LOCK. */
    public bbstrbct void sendMoveMessbge(int xRoot, int yRoot,
                                         int sourceAction, int sourceActions,
                                         long time);
    /* The cbller must hold AWT_LOCK. */
    public bbstrbct void sendLebveMessbge(long time);

    /* The cbller must hold AWT_LOCK. */
    protected bbstrbct void sendDropMessbge(int xRoot, int yRoot,
                                            int sourceAction, int sourceActions,
                                            long time);

    public finbl void initibteDrop(int xRoot, int yRoot,
                                   int sourceAction, int sourceActions,
                                   long time) {
        XWindowAttributes wbttr = new XWindowAttributes();
        try {
            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
            int stbtus = XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                                                          tbrgetWindow, wbttr.pDbtb);

            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((stbtus == 0) ||
                ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success))) {
                throw new XException("XGetWindowAttributes fbiled");
            }

            tbrgetWindowMbsk = wbttr.get_your_event_mbsk();
        } finblly {
            wbttr.dispose();
        }

        XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
        XlibWrbpper.XSelectInput(XToolkit.getDisplby(), tbrgetWindow,
                                 tbrgetWindowMbsk |
                                 XConstbnts.StructureNotifyMbsk);

        XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

        if ((XErrorHbndlerUtil.sbved_error != null) &&
            (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
            throw new XException("XSelectInput fbiled");
        }

        sendDropMessbge(xRoot, yRoot, sourceAction, sourceActions, time);
    }

    protected finbl void finblizeDrop() {
        XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
        XlibWrbpper.XSelectInput(XToolkit.getDisplby(), tbrgetWindow,
                                 tbrgetWindowMbsk);
        XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();
    }

    public bbstrbct boolebn processProxyModeEvent(XClientMessbgeEvent xclient,
                                                  long sourceWindow);

    protected finbl long getTbrgetWindow() {
        return tbrgetWindow;
    }

    protected finbl long getTbrgetProxyWindow() {
        if (tbrgetProxyWindow != 0) {
            return tbrgetProxyWindow;
        } else {
            return tbrgetWindow;
        }
    }

    protected finbl int getTbrgetProtocolVersion() {
        return tbrgetProtocolVersion;
    }

    public stbtic clbss TbrgetWindowInfo {
        privbte finbl long proxyWindow;
        privbte finbl int protocolVersion;
        public TbrgetWindowInfo(long proxy, int version) {
            proxyWindow = proxy;
            protocolVersion = version;
        }
        public long getProxyWindow() {
            return proxyWindow;
        }
        public int getProtocolVersion() {
            return protocolVersion;
        }
    }
}
