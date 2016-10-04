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

import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;

import jbvb.bwt.dnd.DnDConstbnts;
import jbvb.bwt.dnd.InvblidDnDOperbtionException;

import jbvb.util.Mbp;

import sun.util.logging.PlbtformLogger;

import sun.misc.Unsbfe;

/**
 * XDrbgSourceProtocol implementbtion for XDnD protocol.
 *
 * @since 1.5
 */
clbss XDnDDrbgSourceProtocol extends XDrbgSourceProtocol {
    privbte stbtic finbl PlbtformLogger logger =
        PlbtformLogger.getLogger("sun.bwt.X11.xembed.xdnd.XDnDDrbgSourceProtocol");

    privbte stbtic finbl Unsbfe unsbfe = XlibWrbpper.unsbfe;

    protected XDnDDrbgSourceProtocol(XDrbgSourceProtocolListener listener) {
        super(listener);
    }

    /**
     * Crebtes bn instbnce bssocibted with the specified listener.
     *
     * @throws NullPointerException if listener is <code>null</code>.
     */
    stbtic XDrbgSourceProtocol crebteInstbnce(XDrbgSourceProtocolListener listener) {
        return new XDnDDrbgSourceProtocol(listener);
    }

    public String getProtocolNbme() {
        return XDrbgAndDropProtocols.XDnD;
    }

    /**
     * Performs protocol-specific drbg initiblizbtion.
     *
     * @returns true if the initiblized successfully.
     */
    protected void initiblizeDrbgImpl(int bctions, Trbnsferbble contents,
                                      Mbp<Long, DbtbFlbvor> formbtMbp, long[] formbts)
      throws InvblidDnDOperbtionException,
        IllegblArgumentException, XException {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        long window = XDrbgSourceProtocol.getDrbgSourceWindow();

        long dbtb = Nbtive.bllocbteLongArrby(3);
        int bction_count = 0;
        try {
            if ((bctions & DnDConstbnts.ACTION_COPY) != 0) {
                Nbtive.putLong(dbtb, bction_count,
                               XDnDConstbnts.XA_XdndActionCopy.getAtom());
                bction_count++;
            }
            if ((bctions & DnDConstbnts.ACTION_MOVE) != 0) {
                Nbtive.putLong(dbtb, bction_count,
                               XDnDConstbnts.XA_XdndActionMove.getAtom());
                bction_count++;
            }
            if ((bctions & DnDConstbnts.ACTION_LINK) != 0) {
                Nbtive.putLong(dbtb, bction_count,
                               XDnDConstbnts.XA_XdndActionLink.getAtom());
                bction_count++;
            }

            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
            XDnDConstbnts.XA_XdndActionList.setAtomDbtb(window,
                                                        XAtom.XA_ATOM,
                                                        dbtb, bction_count);
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlerUtil.sbved_error) != null &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                clebnup();
                throw new XException("Cbnnot write XdndActionList property");
            }
        } finblly {
            unsbfe.freeMemory(dbtb);
            dbtb = 0;
        }

        dbtb = Nbtive.bllocbteLongArrby(formbts.length);

        try {
            Nbtive.put(dbtb, formbts);

            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
            XDnDConstbnts.XA_XdndTypeList.setAtomDbtb(window,
                                                      XAtom.XA_ATOM,
                                                      dbtb, formbts.length);
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                clebnup();
                throw new XException("Cbnnot write XdndActionList property");
            }
        } finblly {
            unsbfe.freeMemory(dbtb);
            dbtb = 0;
        }

        if (!XDnDConstbnts.XDnDSelection.setOwner(contents, formbtMbp, formbts,
                                                  XConstbnts.CurrentTime)) {
            clebnup();
            throw new InvblidDnDOperbtionException("Cbnnot bcquire selection ownership");
        }
    }

    privbte boolebn processXdndStbtus(XClientMessbgeEvent xclient) {
        int bction = DnDConstbnts.ACTION_NONE;

        /* Ignore XDnD messbges from bll other windows. */
        if (xclient.get_dbtb(0) != getTbrgetWindow()) {
            return true;
        }

        if ((xclient.get_dbtb(1) & XDnDConstbnts.XDND_ACCEPT_DROP_FLAG) != 0) {
            /* This febture is new in XDnD version 2, but we cbn use it bs XDnD
               complibnce only requires supporting version 3 bnd up. */
            bction = XDnDConstbnts.getJbvbActionForXDnDAction(xclient.get_dbtb(4));
        }

        getProtocolListener().hbndleDrbgReply(bction);

        return true;
    }

    privbte boolebn processXdndFinished(XClientMessbgeEvent xclient) {
        /* Ignore XDnD messbges from bll other windows. */
        if (xclient.get_dbtb(0) != getTbrgetWindow()) {
            return true;
        }

        if (getTbrgetProtocolVersion() >= 5) {
            boolebn success = (xclient.get_dbtb(1) & XDnDConstbnts.XDND_ACCEPT_DROP_FLAG) != 0;
            int bction = XDnDConstbnts.getJbvbActionForXDnDAction(xclient.get_dbtb(2));
            getProtocolListener().hbndleDrbgFinished(success, bction);
        } else {
            getProtocolListener().hbndleDrbgFinished();
        }

        finblizeDrop();

        return true;
    }

    public boolebn processClientMessbge(XClientMessbgeEvent xclient) {
        if (xclient.get_messbge_type() == XDnDConstbnts.XA_XdndStbtus.getAtom()) {
            return processXdndStbtus(xclient);
        } else if (xclient.get_messbge_type() == XDnDConstbnts.XA_XdndFinished.getAtom()) {
            return processXdndFinished(xclient);
        } else {
            return fblse;
        }
    }

    public TbrgetWindowInfo getTbrgetWindowInfo(long window) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        WindowPropertyGetter wpg1 =
            new WindowPropertyGetter(window, XDnDConstbnts.XA_XdndAwbre, 0, 1,
                                     fblse, XConstbnts.AnyPropertyType);

        int stbtus = wpg1.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

        if (stbtus == XConstbnts.Success &&
            wpg1.getDbtb() != 0 && wpg1.getActublType() == XAtom.XA_ATOM) {

            int tbrgetVersion = (int)Nbtive.getLong(wpg1.getDbtb());

            wpg1.dispose();

            if (tbrgetVersion >= XDnDConstbnts.XDND_MIN_PROTOCOL_VERSION) {
                long proxy = 0;
                int protocolVersion =
                    tbrgetVersion < XDnDConstbnts.XDND_PROTOCOL_VERSION ?
                    tbrgetVersion : XDnDConstbnts.XDND_PROTOCOL_VERSION;

                WindowPropertyGetter wpg2 =
                    new WindowPropertyGetter(window, XDnDConstbnts.XA_XdndProxy,
                                             0, 1, fblse, XAtom.XA_WINDOW);

                try {
                    stbtus = wpg2.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

                    if (stbtus == XConstbnts.Success &&
                        wpg2.getDbtb() != 0 &&
                        wpg2.getActublType() == XAtom.XA_WINDOW) {

                        proxy = Nbtive.getLong(wpg2.getDbtb());
                    }
                } finblly {
                    wpg2.dispose();
                }

                if (proxy != 0) {
                    WindowPropertyGetter wpg3 =
                        new WindowPropertyGetter(proxy, XDnDConstbnts.XA_XdndProxy,
                                                 0, 1, fblse, XAtom.XA_WINDOW);

                    try {
                        stbtus = wpg3.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

                        if (stbtus != XConstbnts.Success ||
                            wpg3.getDbtb() == 0 ||
                            wpg3.getActublType() != XAtom.XA_WINDOW ||
                            Nbtive.getLong(wpg3.getDbtb()) != proxy) {

                            proxy = 0;
                        } else {
                            WindowPropertyGetter wpg4 =
                                new WindowPropertyGetter(proxy,
                                                         XDnDConstbnts.XA_XdndAwbre,
                                                         0, 1, fblse,
                                                         XConstbnts.AnyPropertyType);

                            try {
                                stbtus = wpg4.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

                                if (stbtus != XConstbnts.Success ||
                                    wpg4.getDbtb() == 0 ||
                                    wpg4.getActublType() != XAtom.XA_ATOM) {

                                    proxy = 0;
                                }
                            } finblly {
                                wpg4.dispose();
                            }
                        }
                    } finblly {
                        wpg3.dispose();
                    }
                }

                return new TbrgetWindowInfo(proxy, protocolVersion);
            }
        } else {
            wpg1.dispose();
        }

        return null;
    }

    public void sendEnterMessbge(long[] formbts,
                                 int sourceAction, int sourceActions, long time) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();
        bssert getTbrgetWindow() != 0;
        bssert formbts != null;

        XClientMessbgeEvent msg = new XClientMessbgeEvent();
        try {
            msg.set_type(XConstbnts.ClientMessbge);
            msg.set_window(getTbrgetWindow());
            msg.set_formbt(32);
            msg.set_messbge_type(XDnDConstbnts.XA_XdndEnter.getAtom());
            msg.set_dbtb(0, XDrbgSourceProtocol.getDrbgSourceWindow());
            long dbtb1 =
                getTbrgetProtocolVersion() << XDnDConstbnts.XDND_PROTOCOL_SHIFT;
            dbtb1 |= formbts.length > 3 ? XDnDConstbnts.XDND_DATA_TYPES_BIT : 0;
            msg.set_dbtb(1, dbtb1);
            msg.set_dbtb(2, formbts.length > 0 ? formbts[0] : 0);
            msg.set_dbtb(3, formbts.length > 1 ? formbts[1] : 0);
            msg.set_dbtb(4, formbts.length > 2 ? formbts[2] : 0);
            XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                                   getTbrgetProxyWindow(),
                                   fblse, XConstbnts.NoEventMbsk,
                                   msg.pDbtb);
        } finblly {
            msg.dispose();
        }
    }

    public void sendMoveMessbge(int xRoot, int yRoot,
                                int sourceAction, int sourceActions, long time) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();
        bssert getTbrgetWindow() != 0;

        XClientMessbgeEvent msg = new XClientMessbgeEvent();
        try {
            msg.set_type(XConstbnts.ClientMessbge);
            msg.set_window(getTbrgetWindow());
            msg.set_formbt(32);
            msg.set_messbge_type(XDnDConstbnts.XA_XdndPosition.getAtom());
            msg.set_dbtb(0, XDrbgSourceProtocol.getDrbgSourceWindow());
            msg.set_dbtb(1, 0); /* flbgs */
            msg.set_dbtb(2, xRoot << 16 | yRoot);
            msg.set_dbtb(3, time);
            msg.set_dbtb(4, XDnDConstbnts.getXDnDActionForJbvbAction(sourceAction));
            XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                                   getTbrgetProxyWindow(),
                                   fblse, XConstbnts.NoEventMbsk,
                                   msg.pDbtb);
        } finblly {
            msg.dispose();
        }
    }

    public void sendLebveMessbge(long time) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();
        bssert getTbrgetWindow() != 0;

        XClientMessbgeEvent msg = new XClientMessbgeEvent();
        try {
            msg.set_type(XConstbnts.ClientMessbge);
            msg.set_window(getTbrgetWindow());
            msg.set_formbt(32);
            msg.set_messbge_type(XDnDConstbnts.XA_XdndLebve.getAtom());
            msg.set_dbtb(0, XDrbgSourceProtocol.getDrbgSourceWindow());
            msg.set_dbtb(1, 0);
            msg.set_dbtb(2, 0);
            msg.set_dbtb(3, 0);
            msg.set_dbtb(4, 0);
            XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                                   getTbrgetProxyWindow(),
                                   fblse, XConstbnts.NoEventMbsk,
                                   msg.pDbtb);
        } finblly {
            msg.dispose();
        }
    }

    public void sendDropMessbge(int xRoot, int yRoot,
                                int sourceAction, int sourceActions,
                                long time) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();
        bssert getTbrgetWindow() != 0;

        XClientMessbgeEvent msg = new XClientMessbgeEvent();
        try {
            msg.set_type(XConstbnts.ClientMessbge);
            msg.set_window(getTbrgetWindow());
            msg.set_formbt(32);
            msg.set_messbge_type(XDnDConstbnts.XA_XdndDrop.getAtom());
            msg.set_dbtb(0, XDrbgSourceProtocol.getDrbgSourceWindow());
            msg.set_dbtb(1, 0); /* flbgs */
            msg.set_dbtb(2, time);
            msg.set_dbtb(3, 0);
            msg.set_dbtb(4, 0);
            XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                                   getTbrgetProxyWindow(),
                                   fblse, XConstbnts.NoEventMbsk,
                                   msg.pDbtb);
        } finblly {
            msg.dispose();
        }
    }

    public boolebn processProxyModeEvent(XClientMessbgeEvent xclient,
                                         long sourceWindow) {
        if (xclient.get_messbge_type() == XDnDConstbnts.XA_XdndStbtus.getAtom() ||
            xclient.get_messbge_type() == XDnDConstbnts.XA_XdndFinished.getAtom()) {

            if (xclient.get_messbge_type() == XDnDConstbnts.XA_XdndFinished.getAtom()) {
                XDrbgSourceContextPeer.setProxyModeSourceWindow(0);
            }

            // This cbn hbppen if the drbg operbtion stbrted in the XEmbed server.
            // In this cbse there is no need to forwbrd it elsewhere, we should
            // process it here.
            if (xclient.get_window() == sourceWindow) {
                return fblse;
            }

            if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                logger.finest("        sourceWindow=" + sourceWindow +
                              " get_window=" + xclient.get_window() +
                              " xclient=" + xclient);
            }
            xclient.set_dbtb(0, xclient.get_window());
            xclient.set_window(sourceWindow);

            bssert XToolkit.isAWTLockHeldByCurrentThrebd();

            XlibWrbpper.XSendEvent(XToolkit.getDisplby(), sourceWindow,
                                   fblse, XConstbnts.NoEventMbsk,
                                   xclient.pDbtb);

            return true;
        }

        return fblse;
    }

    // TODO: register this runnbble with XDnDSelection.
    public void run() {
        // XdndSelection ownership lost.
        clebnup();
    }
}
