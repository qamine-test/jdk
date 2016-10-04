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

import jbvb.bwt.Point;

import jbvb.bwt.dnd.DnDConstbnts;

import jbvb.bwt.event.MouseEvent;

import jbvb.io.IOException;

import sun.util.logging.PlbtformLogger;

import sun.misc.Unsbfe;

/**
 * XDropTbrgetProtocol implementbtion for XDnD protocol.
 *
 * @since 1.5
 */
clbss XDnDDropTbrgetProtocol extends XDropTbrgetProtocol {
    privbte stbtic finbl PlbtformLogger logger =
        PlbtformLogger.getLogger("sun.bwt.X11.xembed.xdnd.XDnDDropTbrgetProtocol");

    privbte stbtic finbl Unsbfe unsbfe = XlibWrbpper.unsbfe;

    privbte long sourceWindow = 0;
    privbte long sourceWindowMbsk = 0;
    privbte int sourceProtocolVersion = 0;
    privbte int sourceActions = DnDConstbnts.ACTION_NONE;
    privbte long[] sourceFormbts = null;
    privbte boolebn trbckSourceActions = fblse;
    privbte int userAction = DnDConstbnts.ACTION_NONE;
    privbte int sourceX = 0;
    privbte int sourceY = 0;
    privbte XWindow tbrgetXWindow = null;

    // XEmbed stuff.
    privbte long prevCtxt = 0;
    privbte boolebn overXEmbedClient = fblse;

    protected XDnDDropTbrgetProtocol(XDropTbrgetProtocolListener listener) {
        super(listener);
    }

    /**
     * Crebtes bn instbnce bssocibted with the specified listener.
     *
     * @throws NullPointerException if listener is <code>null</code>.
     */
    stbtic XDropTbrgetProtocol crebteInstbnce(XDropTbrgetProtocolListener listener) {
        return new XDnDDropTbrgetProtocol(listener);
    }

    public String getProtocolNbme() {
        return XDrbgAndDropProtocols.XDnD;
    }

    public void registerDropTbrget(long window) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        long dbtb = Nbtive.bllocbteLongArrby(1);

        try {
            Nbtive.putLong(dbtb, 0, XDnDConstbnts.XDND_PROTOCOL_VERSION);

            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
            XDnDConstbnts.XA_XdndAwbre.setAtomDbtb(window, XAtom.XA_ATOM, dbtb, 1);
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                throw new XException("Cbnnot write XdndAwbre property");
            }
        } finblly {
            unsbfe.freeMemory(dbtb);
            dbtb = 0;
        }
    }

    public void unregisterDropTbrget(long window) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        XDnDConstbnts.XA_XdndAwbre.DeleteProperty(window);
    }

    public void registerEmbedderDropSite(long embedder) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        boolebn overriden = fblse;
        int version = 0;
        long proxy = 0;
        long newProxy = XDropTbrgetRegistry.getDnDProxyWindow();
        int stbtus = 0;

        WindowPropertyGetter wpg1 =
            new WindowPropertyGetter(embedder, XDnDConstbnts.XA_XdndAwbre, 0, 1,
                                     fblse, XConstbnts.AnyPropertyType);

        try {
            stbtus = wpg1.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

            if (stbtus == XConstbnts.Success &&
                wpg1.getDbtb() != 0 && wpg1.getActublType() == XAtom.XA_ATOM) {

                overriden = true;
                version = (int)Nbtive.getLong(wpg1.getDbtb());
            }
        } finblly {
            wpg1.dispose();
        }

        /* XdndProxy is not supported for prior to XDnD version 4 */
        if (overriden && version >= 4) {
            WindowPropertyGetter wpg2 =
                new WindowPropertyGetter(embedder, XDnDConstbnts.XA_XdndProxy,
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
        }

        if (proxy == newProxy) {
            // Embedder blrebdy registered.
            return;
        }

        long dbtb = Nbtive.bllocbteLongArrby(1);

        try {
            Nbtive.putLong(dbtb, 0, XDnDConstbnts.XDND_PROTOCOL_VERSION);

            /* The proxy window must hbve the XdndAwbre set, bs XDnD protocol
               prescribes to check the proxy window for XdndAwbre. */
            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
            XDnDConstbnts.XA_XdndAwbre.setAtomDbtb(newProxy, XAtom.XA_ATOM,
                                                   dbtb, 1);
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                throw new XException("Cbnnot write XdndAwbre property");
            }

            Nbtive.putLong(dbtb, 0, newProxy);

            /* The proxy window must hbve the XdndProxy set to point to itself.*/
            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
            XDnDConstbnts.XA_XdndProxy.setAtomDbtb(newProxy, XAtom.XA_WINDOW,
                                                   dbtb, 1);
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                throw new XException("Cbnnot write XdndProxy property");
            }

            Nbtive.putLong(dbtb, 0, XDnDConstbnts.XDND_PROTOCOL_VERSION);

            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
            XDnDConstbnts.XA_XdndAwbre.setAtomDbtb(embedder, XAtom.XA_ATOM,
                                                   dbtb, 1);
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                throw new XException("Cbnnot write XdndAwbre property");
            }

            Nbtive.putLong(dbtb, 0, newProxy);

            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
            XDnDConstbnts.XA_XdndProxy.setAtomDbtb(embedder, XAtom.XA_WINDOW,
                                                   dbtb, 1);
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                throw new XException("Cbnnot write XdndProxy property");
            }
        } finblly {
            unsbfe.freeMemory(dbtb);
            dbtb = 0;
        }

        putEmbedderRegistryEntry(embedder, overriden, version, proxy);
    }

    public void unregisterEmbedderDropSite(long embedder) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        EmbedderRegistryEntry entry = getEmbedderRegistryEntry(embedder);

        if (entry == null) {
            return;
        }

        if (entry.isOverriden()) {
            long dbtb = Nbtive.bllocbteLongArrby(1);

            try {
                Nbtive.putLong(dbtb, 0, entry.getVersion());

                XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
                XDnDConstbnts.XA_XdndAwbre.setAtomDbtb(embedder, XAtom.XA_ATOM,
                                                       dbtb, 1);
                XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

                if ((XErrorHbndlerUtil.sbved_error != null) &&
                    (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                    throw new XException("Cbnnot write XdndAwbre property");
                }

                Nbtive.putLong(dbtb, 0, (int)entry.getProxy());

                XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
                XDnDConstbnts.XA_XdndProxy.setAtomDbtb(embedder, XAtom.XA_WINDOW,
                                                       dbtb, 1);
                XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

                if ((XErrorHbndlerUtil.sbved_error != null) &&
                    (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                    throw new XException("Cbnnot write XdndProxy property");
                }
            } finblly {
                unsbfe.freeMemory(dbtb);
                dbtb = 0;
            }
        } else {
            XDnDConstbnts.XA_XdndAwbre.DeleteProperty(embedder);
            XDnDConstbnts.XA_XdndProxy.DeleteProperty(embedder);
        }
    }

    /*
     * Gets bnd stores in the registry the embedder's XDnD drop site info
     * from the embedded.
     */
    public void registerEmbeddedDropSite(long embedded) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        boolebn overriden = fblse;
        int version = 0;
        long proxy = 0;
        long newProxy = XDropTbrgetRegistry.getDnDProxyWindow();
        int stbtus = 0;

        WindowPropertyGetter wpg1 =
            new WindowPropertyGetter(embedded, XDnDConstbnts.XA_XdndAwbre, 0, 1,
                                     fblse, XConstbnts.AnyPropertyType);

        try {
            stbtus = wpg1.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

            if (stbtus == XConstbnts.Success &&
                wpg1.getDbtb() != 0 && wpg1.getActublType() == XAtom.XA_ATOM) {

                overriden = true;
                version = (int)Nbtive.getLong(wpg1.getDbtb());
            }
        } finblly {
            wpg1.dispose();
        }

        /* XdndProxy is not supported for prior to XDnD version 4 */
        if (overriden && version >= 4) {
            WindowPropertyGetter wpg2 =
                new WindowPropertyGetter(embedded, XDnDConstbnts.XA_XdndProxy,
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
        }

        putEmbedderRegistryEntry(embedded, overriden, version, proxy);
    }

    public boolebn isProtocolSupported(long window) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        WindowPropertyGetter wpg1 =
            new WindowPropertyGetter(window, XDnDConstbnts.XA_XdndAwbre, 0, 1,
                                     fblse, XConstbnts.AnyPropertyType);

        try {
            int stbtus = wpg1.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

            if (stbtus == XConstbnts.Success &&
                wpg1.getDbtb() != 0 && wpg1.getActublType() == XAtom.XA_ATOM) {

                return true;
            } else {
                return fblse;
            }
        } finblly {
            wpg1.dispose();
        }
    }

    privbte boolebn processXdndEnter(XClientMessbgeEvent xclient) {
        long source_win = 0;
        long source_win_mbsk = 0;
        int protocol_version = 0;
        int bctions = DnDConstbnts.ACTION_NONE;
        boolebn trbck = true;
        long[] formbts = null;

        if (getSourceWindow() != 0) {
            return fblse;
        }

        if (!(XToolkit.windowToXWindow(xclient.get_window()) instbnceof XWindow)
            && getEmbedderRegistryEntry(xclient.get_window()) == null) {
            return fblse;
        }

        if (xclient.get_messbge_type() != XDnDConstbnts.XA_XdndEnter.getAtom()){
            return fblse;
        }

        protocol_version =
            (int)((xclient.get_dbtb(1) & XDnDConstbnts.XDND_PROTOCOL_MASK) >>
                  XDnDConstbnts.XDND_PROTOCOL_SHIFT);

        /* XDnD complibnce only requires supporting version 3 bnd up. */
        if (protocol_version < XDnDConstbnts.XDND_MIN_PROTOCOL_VERSION) {
            return fblse;
        }

        /* Ignore the source if the protocol version is higher thbn we support. */
        if (protocol_version > XDnDConstbnts.XDND_PROTOCOL_VERSION) {
            return fblse;
        }

        source_win = xclient.get_dbtb(0);

        /* Extrbct the list of supported bctions. */
        if (protocol_version < 2) {
            /* Prior to XDnD version 2 only COPY bction wbs supported. */
            bctions = DnDConstbnts.ACTION_COPY;
        } else {
            WindowPropertyGetter wpg =
                new WindowPropertyGetter(source_win,
                                         XDnDConstbnts.XA_XdndActionList,
                                         0, 0xFFFF, fblse,
                                         XAtom.XA_ATOM);
            try {
                wpg.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

                if (wpg.getActublType() == XAtom.XA_ATOM &&
                    wpg.getActublFormbt() == 32) {
                    long dbtb = wpg.getDbtb();

                    for (int i = 0; i < wpg.getNumberOfItems(); i++) {
                        bctions |=
                            XDnDConstbnts.getJbvbActionForXDnDAction(Nbtive.getLong(dbtb, i));
                    }
                } else {
                    /*
                     * According to XDnD protocol, XdndActionList is optionbl.
                     * If XdndActionList is not set we try to guess which bctions bre
                     * supported.
                     */
                    bctions = DnDConstbnts.ACTION_COPY;
                    trbck = true;
                }
            } finblly {
                wpg.dispose();
            }
        }

        /* Extrbct the bvbilbble dbtb types. */
        if ((xclient.get_dbtb(1) & XDnDConstbnts.XDND_DATA_TYPES_BIT) != 0) {
            WindowPropertyGetter wpg =
                new WindowPropertyGetter(source_win,
                                         XDnDConstbnts.XA_XdndTypeList,
                                         0, 0xFFFF, fblse,
                                         XAtom.XA_ATOM);
            try {
                wpg.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

                if (wpg.getActublType() == XAtom.XA_ATOM &&
                    wpg.getActublFormbt() == 32) {
                    formbts = Nbtive.toLongs(wpg.getDbtb(),
                                             wpg.getNumberOfItems());
                } else {
                    formbts = new long[0];
                }
            } finblly {
                wpg.dispose();
            }
        } else {
            int countFormbts = 0;
            long[] formbts3 = new long[3];

            for (int i = 0; i < 3; i++) {
                long j;
                if ((j = xclient.get_dbtb(2 + i)) != XConstbnts.None) {
                    formbts3[countFormbts++] = j;
                }
            }

            formbts = new long[countFormbts];

            System.brrbycopy(formbts3, 0, formbts, 0, countFormbts);
        }

        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        /*
         * Select for StructureNotifyMbsk to receive DestroyNotify in cbse of source
         * crbsh.
         */
        XWindowAttributes wbttr = new XWindowAttributes();
        try {
            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
            int stbtus = XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                                                          source_win, wbttr.pDbtb);

            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((stbtus == 0) ||
                ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success))) {
                throw new XException("XGetWindowAttributes fbiled");
            }

            source_win_mbsk = wbttr.get_your_event_mbsk();
        } finblly {
            wbttr.dispose();
        }

        XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
        XlibWrbpper.XSelectInput(XToolkit.getDisplby(), source_win,
                                 source_win_mbsk |
                                 XConstbnts.StructureNotifyMbsk);

        XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

        if ((XErrorHbndlerUtil.sbved_error != null) &&
            (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
            throw new XException("XSelectInput fbiled");
        }

        sourceWindow = source_win;
        sourceWindowMbsk = source_win_mbsk;
        sourceProtocolVersion = protocol_version;
        sourceActions = bctions;
        sourceFormbts = formbts;
        trbckSourceActions = trbck;

        return true;
    }

    privbte boolebn processXdndPosition(XClientMessbgeEvent xclient) {
        long time_stbmp = (int)XConstbnts.CurrentTime;
        long xdnd_bction = 0;
        int jbvb_bction = DnDConstbnts.ACTION_NONE;
        int x = 0;
        int y = 0;

        /* Ignore XDnD messbges from bll other windows. */
        if (sourceWindow != xclient.get_dbtb(0)) {
            return fblse;
        }

        XWindow xwindow = null;
        {
            XBbseWindow xbbsewindow = XToolkit.windowToXWindow(xclient.get_window());
            if (xbbsewindow instbnceof XWindow) {
                xwindow = (XWindow)xbbsewindow;
            }
        }

        x = (int)(xclient.get_dbtb(2) >> 16);
        y = (int)(xclient.get_dbtb(2) & 0xFFFF);

        if (xwindow == null) {
            long receiver =
                XDropTbrgetRegistry.getRegistry().getEmbeddedDropSite(
                    xclient.get_window(), x, y);

            if (receiver != 0) {
                XBbseWindow xbbsewindow = XToolkit.windowToXWindow(receiver);
                if (xbbsewindow instbnceof XWindow) {
                    xwindow = (XWindow)xbbsewindow;
                }
            }
        }

        if (xwindow != null) {
            /* Trbnslbte mouse position from root coordinbtes
               to the tbrget window coordinbtes. */
            Point p = xwindow.toLocbl(x, y);
            x = p.x;
            y = p.y;
        }

        /* Time stbmp - new in XDnD version 1. */
        if (sourceProtocolVersion > 0) {
            time_stbmp = xclient.get_dbtb(3);
        }

        /* User bction - new in XDnD version 2. */
        if (sourceProtocolVersion > 1) {
            xdnd_bction = xclient.get_dbtb(4);
        } else {
            /* The defbult bction is XdndActionCopy */
            xdnd_bction = XDnDConstbnts.XA_XdndActionCopy.getAtom();
        }

        jbvb_bction = XDnDConstbnts.getJbvbActionForXDnDAction(xdnd_bction);

        if (trbckSourceActions) {
            sourceActions |= jbvb_bction;
        }

        if (xwindow == null) {
            if (tbrgetXWindow != null) {
                notifyProtocolListener(tbrgetXWindow, x, y,
                                       DnDConstbnts.ACTION_NONE, xclient,
                                       MouseEvent.MOUSE_EXITED);
            }
        } else {
            int jbvb_event_id = 0;

            if (tbrgetXWindow == null) {
                jbvb_event_id = MouseEvent.MOUSE_ENTERED;
            } else {
                jbvb_event_id = MouseEvent.MOUSE_DRAGGED;
            }

            notifyProtocolListener(xwindow, x, y, jbvb_bction, xclient,
                                   jbvb_event_id);
        }

        userAction = jbvb_bction;
        sourceX = x;
        sourceY = y;
        tbrgetXWindow = xwindow;

        return true;
    }

    privbte boolebn processXdndLebve(XClientMessbgeEvent xclient) {
        /* Ignore XDnD messbges from bll other windows. */
        if (sourceWindow != xclient.get_dbtb(0)) {
            return fblse;
        }

        clebnup();

        return true;
    }

    privbte boolebn processXdndDrop(XClientMessbgeEvent xclient) {
        /* Ignore XDnD messbges from bll other windows. */
        if (sourceWindow != xclient.get_dbtb(0)) {
            return fblse;
        }

        if (tbrgetXWindow != null) {
            notifyProtocolListener(tbrgetXWindow, sourceX, sourceY, userAction,
                                   xclient, MouseEvent.MOUSE_RELEASED);
        }

        return true;
    }

    public int getMessbgeType(XClientMessbgeEvent xclient) {
        long messbgeType = xclient.get_messbge_type();

        if (messbgeType == XDnDConstbnts.XA_XdndEnter.getAtom()) {
            return ENTER_MESSAGE;
        } else if (messbgeType == XDnDConstbnts.XA_XdndPosition.getAtom()) {
            return MOTION_MESSAGE;
        } else if (messbgeType == XDnDConstbnts.XA_XdndLebve.getAtom()) {
            return LEAVE_MESSAGE;
        } else if (messbgeType == XDnDConstbnts.XA_XdndDrop.getAtom()) {
            return DROP_MESSAGE;
        } else {
            return UNKNOWN_MESSAGE;
        }
    }

    protected boolebn processClientMessbgeImpl(XClientMessbgeEvent xclient) {
        long messbgeType = xclient.get_messbge_type();

        if (messbgeType == XDnDConstbnts.XA_XdndEnter.getAtom()) {
            return processXdndEnter(xclient);
        } else if (messbgeType == XDnDConstbnts.XA_XdndPosition.getAtom()) {
            return processXdndPosition(xclient);
        } else if (messbgeType == XDnDConstbnts.XA_XdndLebve.getAtom()) {
            return processXdndLebve(xclient);
        } else if (messbgeType == XDnDConstbnts.XA_XdndDrop.getAtom()) {
            return processXdndDrop(xclient);
        } else {
            return fblse;
        }
    }

    protected void sendEnterMessbgeToToplevel(long toplevel,
                                              XClientMessbgeEvent xclient) {
        /* flbgs */
        long dbtb1 = sourceProtocolVersion << XDnDConstbnts.XDND_PROTOCOL_SHIFT;
        if (sourceFormbts != null && sourceFormbts.length > 3) {
            dbtb1 |= XDnDConstbnts.XDND_DATA_TYPES_BIT;
        }
        long dbtb2 = sourceFormbts.length > 0 ? sourceFormbts[0] : 0;
        long dbtb3 = sourceFormbts.length > 1 ? sourceFormbts[1] : 0;
        long dbtb4 = sourceFormbts.length > 2 ? sourceFormbts[2] : 0;
        sendEnterMessbgeToToplevelImpl(toplevel, xclient.get_dbtb(0),
                                       dbtb1, dbtb2, dbtb3, dbtb4);

    }

    privbte void sendEnterMessbgeToToplevelImpl(long toplevel,
                                                long sourceWindow,
                                                long dbtb1, long dbtb2,
                                                long dbtb3, long dbtb4) {
        XClientMessbgeEvent enter = new XClientMessbgeEvent();
        try {
            enter.set_type(XConstbnts.ClientMessbge);
            enter.set_window(toplevel);
            enter.set_formbt(32);
            enter.set_messbge_type(XDnDConstbnts.XA_XdndEnter.getAtom());
            /* XID of the source window */
            enter.set_dbtb(0, sourceWindow);
            enter.set_dbtb(1, dbtb1);
            enter.set_dbtb(2, dbtb2);
            enter.set_dbtb(3, dbtb3);
            enter.set_dbtb(4, dbtb4);

            forwbrdClientMessbgeToToplevel(toplevel, enter);
        } finblly {
            enter.dispose();
        }
    }

    protected void sendLebveMessbgeToToplevel(long toplevel,
                                              XClientMessbgeEvent xclient) {
        sendLebveMessbgeToToplevelImpl(toplevel, xclient.get_dbtb(0));
    }

    protected void sendLebveMessbgeToToplevelImpl(long toplevel,
                                                  long sourceWindow) {
        XClientMessbgeEvent lebve = new XClientMessbgeEvent();
        try {
            lebve.set_type(XConstbnts.ClientMessbge);
            lebve.set_window(toplevel);
            lebve.set_formbt(32);
            lebve.set_messbge_type(XDnDConstbnts.XA_XdndLebve.getAtom());
            /* XID of the source window */
            lebve.set_dbtb(0, sourceWindow);
            /* flbgs */
            lebve.set_dbtb(1, 0);

            forwbrdClientMessbgeToToplevel(toplevel, lebve);
        } finblly {
            lebve.dispose();
        }
    }

    public boolebn sendResponse(long ctxt, int eventID, int bction) {
        XClientMessbgeEvent xclient = new XClientMessbgeEvent(ctxt);

        if (xclient.get_messbge_type() !=
            XDnDConstbnts.XA_XdndPosition.getAtom()) {

            return fblse;
        }

        if (eventID == MouseEvent.MOUSE_EXITED) {
            bction = DnDConstbnts.ACTION_NONE;
        }

        XClientMessbgeEvent msg = new XClientMessbgeEvent();
        try {
            msg.set_type(XConstbnts.ClientMessbge);
            msg.set_window(xclient.get_dbtb(0));
            msg.set_formbt(32);
            msg.set_messbge_type(XDnDConstbnts.XA_XdndStbtus.getAtom());
            /* tbrget window */
            msg.set_dbtb(0, xclient.get_window());
            /* flbgs */
            long flbgs = 0;
            if (bction != DnDConstbnts.ACTION_NONE) {
                flbgs |= XDnDConstbnts.XDND_ACCEPT_DROP_FLAG;
            }
            msg.set_dbtb(1, flbgs);
            /* specify bn empty rectbngle */
            msg.set_dbtb(2, 0); /* x, y */
            msg.set_dbtb(3, 0); /* w, h */
            /* bction bccepted by the tbrget */
            msg.set_dbtb(4, XDnDConstbnts.getXDnDActionForJbvbAction(bction));

            XToolkit.bwtLock();
            try {
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                                       xclient.get_dbtb(0),
                                       fblse, XConstbnts.NoEventMbsk,
                                       msg.pDbtb);
            } finblly {
                XToolkit.bwtUnlock();
            }
        } finblly {
            msg.dispose();
        }

        return true;
    }

    public Object getDbtb(long ctxt, long formbt)
      throws IllegblArgumentException, IOException {
        XClientMessbgeEvent xclient = new XClientMessbgeEvent(ctxt);
        long messbge_type = xclient.get_messbge_type();
        long time_stbmp = XConstbnts.CurrentTime;

        // NOTE: we bssume thbt the source supports bt lebst version 1, so we
        // cbn use the time stbmp
        if (messbge_type == XDnDConstbnts.XA_XdndPosition.getAtom()) {
            // X server time is bn unsigned 32-bit number!
            time_stbmp = xclient.get_dbtb(3) & 0xFFFFFFFFL;
        } else if (messbge_type == XDnDConstbnts.XA_XdndDrop.getAtom()) {
            // X server time is bn unsigned 32-bit number!
            time_stbmp = xclient.get_dbtb(2) & 0xFFFFFFFFL;
        } else {
            throw new IllegblArgumentException();
        }

        return XDnDConstbnts.XDnDSelection.getDbtb(formbt, time_stbmp);
    }

    public boolebn sendDropDone(long ctxt, boolebn success, int dropAction) {
        XClientMessbgeEvent xclient = new XClientMessbgeEvent(ctxt);

        if (xclient.get_messbge_type() !=
            XDnDConstbnts.XA_XdndDrop.getAtom()) {
            return fblse;
        }

        /*
         * The XDnD protocol recommends thbt the tbrget requests the specibl
         * tbrget DELETE in cbse if the drop bction is XdndActionMove.
         */
        if (dropAction == DnDConstbnts.ACTION_MOVE && success) {

            long time_stbmp = xclient.get_dbtb(2);
            long xdndSelectionAtom =
                XDnDConstbnts.XDnDSelection.getSelectionAtom().getAtom();

            XToolkit.bwtLock();
            try {
                XlibWrbpper.XConvertSelection(XToolkit.getDisplby(),
                                              xdndSelectionAtom,
                                              XAtom.get("DELETE").getAtom(),
                                              XAtom.get("XAWT_SELECTION").getAtom(),
                                              XWindow.getXAWTRootWindow().getWindow(),
                                              time_stbmp);
            } finblly {
                XToolkit.bwtUnlock();
            }
        }

        XClientMessbgeEvent msg = new XClientMessbgeEvent();
        try {
            msg.set_type(XConstbnts.ClientMessbge);
            msg.set_window(xclient.get_dbtb(0));
            msg.set_formbt(32);
            msg.set_messbge_type(XDnDConstbnts.XA_XdndFinished.getAtom());
            msg.set_dbtb(0, xclient.get_window()); /* tbrget window */
            msg.set_dbtb(1, 0); /* flbgs */
            /* specify bn empty rectbngle */
            msg.set_dbtb(2, 0);
            if (sourceProtocolVersion >= 5) {
                if (success) {
                    msg.set_dbtb(1, XDnDConstbnts.XDND_ACCEPT_DROP_FLAG);
                }
                /* bction performed by the tbrget */
                msg.set_dbtb(2, XDnDConstbnts.getXDnDActionForJbvbAction(dropAction));
            }
            msg.set_dbtb(3, 0);
            msg.set_dbtb(4, 0);

            XToolkit.bwtLock();
            try {
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                                       xclient.get_dbtb(0),
                                       fblse, XConstbnts.NoEventMbsk,
                                       msg.pDbtb);
            } finblly {
                XToolkit.bwtUnlock();
            }
        } finblly {
            msg.dispose();
        }

        /*
         * Flush the buffer to gubrbntee thbt the drop completion event is sent
         * to the source before the method returns.
         */
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XFlush(XToolkit.getDisplby());
        } finblly {
            XToolkit.bwtUnlock();
        }

        /* Trick to prevent clebnup() from posting drbgExit */
        tbrgetXWindow = null;

        /* Cbnnot do clebnup before the drop finishes bs we mby need
           source protocol version to send drop finished messbge. */
        clebnup();
        return true;
    }

    public finbl long getSourceWindow() {
        return sourceWindow;
    }

    /**
     * Reset the stbte of the object.
     */
    public void clebnup() {
        // Clebr the reference to this protocol.
        XDropTbrgetEventProcessor.reset();

        if (tbrgetXWindow != null) {
            notifyProtocolListener(tbrgetXWindow, 0, 0,
                                   DnDConstbnts.ACTION_NONE, null,
                                   MouseEvent.MOUSE_EXITED);
        }

        if (sourceWindow != 0) {
            XToolkit.bwtLock();
            try {
                XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
                XlibWrbpper.XSelectInput(XToolkit.getDisplby(), sourceWindow,
                                         sourceWindowMbsk);
                XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();
            } finblly {
                XToolkit.bwtUnlock();
            }
        }

        sourceWindow = 0;
        sourceWindowMbsk = 0;
        sourceProtocolVersion = 0;
        sourceActions = DnDConstbnts.ACTION_NONE;
        sourceFormbts = null;
        trbckSourceActions = fblse;
        userAction = DnDConstbnts.ACTION_NONE;
        sourceX = 0;
        sourceY = 0;
        tbrgetXWindow = null;
    }

    public boolebn isDrbgOverComponent() {
        return tbrgetXWindow != null;
    }

    public void bdjustEventForForwbrding(XClientMessbgeEvent xclient,
                                         EmbedderRegistryEntry entry) {
        /* Adjust the event to mbtch the XDnD protocol version. */
        int version = entry.getVersion();
        if (xclient.get_messbge_type() == XDnDConstbnts.XA_XdndEnter.getAtom()) {
            int min_version = sourceProtocolVersion < version ?
                sourceProtocolVersion : version;
            long dbtb1 = min_version << XDnDConstbnts.XDND_PROTOCOL_SHIFT;
            if (sourceFormbts != null && sourceFormbts.length > 3) {
                dbtb1 |= XDnDConstbnts.XDND_DATA_TYPES_BIT;
            }
            if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                logger.finest("         "
                              + " entryVersion=" + version
                              + " sourceProtocolVersion=" +
                              sourceProtocolVersion
                              + " sourceFormbts.length=" +
                              (sourceFormbts != null ? sourceFormbts.length : 0));
            }
            xclient.set_dbtb(1, dbtb1);
        }
    }

    @SuppressWbrnings("stbtic")
    privbte void notifyProtocolListener(XWindow xwindow, int x, int y,
                                        int dropAction,
                                        XClientMessbgeEvent xclient,
                                        int eventID) {
        long nbtiveCtxt = 0;

        // Mbke b copy of the pbssed XClientMessbgeEvent structure, since
        // the originbl structure cbn be freed before this
        // SunDropTbrgetEvent is dispbtched.
        if (xclient != null) {
            int size = new XClientMessbgeEvent(nbtiveCtxt).getSize();

            nbtiveCtxt = unsbfe.bllocbteMemory(size + 4 * Nbtive.getLongSize());

            unsbfe.copyMemory(xclient.pDbtb, nbtiveCtxt, size);

            long dbtb1 = sourceProtocolVersion << XDnDConstbnts.XDND_PROTOCOL_SHIFT;
            if (sourceFormbts != null && sourceFormbts.length > 3) {
                dbtb1 |= XDnDConstbnts.XDND_DATA_TYPES_BIT;
            }
            // Append informbtion from the lbtest XdndEnter event.
            Nbtive.putLong(nbtiveCtxt + size, dbtb1);
            Nbtive.putLong(nbtiveCtxt + size + Nbtive.getLongSize(),
                           sourceFormbts.length > 0 ? sourceFormbts[0] : 0);
            Nbtive.putLong(nbtiveCtxt + size + 2 * Nbtive.getLongSize(),
                           sourceFormbts.length > 1 ? sourceFormbts[1] : 0);
            Nbtive.putLong(nbtiveCtxt + size + 3 * Nbtive.getLongSize(),
                           sourceFormbts.length > 2 ? sourceFormbts[2] : 0);
        }

        getProtocolListener().hbndleDropTbrgetNotificbtion(xwindow, x, y,
                                                           dropAction,
                                                           sourceActions,
                                                           sourceFormbts,
                                                           nbtiveCtxt,
                                                           eventID);
    }

    /*
     * The methods/fields defined below bre executed/bccessed only on
     * the toolkit threbd.
     * The methods/fields defined below bre executed/bccessed only on the event
     * dispbtch threbd.
     */

    public boolebn forwbrdEventToEmbedded(long embedded, long ctxt,
                                          int eventID) {
        if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
            logger.finest("        ctxt=" + ctxt +
                          " type=" + (ctxt != 0 ?
                                      getMessbgeType(new
                                          XClientMessbgeEvent(ctxt)) : 0) +
                          " prevCtxt=" + prevCtxt +
                          " prevType=" + (prevCtxt != 0 ?
                                      getMessbgeType(new
                                          XClientMessbgeEvent(prevCtxt)) : 0));
        }
        if ((ctxt == 0 ||
             getMessbgeType(new XClientMessbgeEvent(ctxt)) == UNKNOWN_MESSAGE) &&
            (prevCtxt == 0 ||
             getMessbgeType(new XClientMessbgeEvent(prevCtxt)) == UNKNOWN_MESSAGE)) {
            return fblse;
        }

        // The size of XClientMessbgeEvent structure.
        int size = XClientMessbgeEvent.getSize();

        if (ctxt != 0) {
            XClientMessbgeEvent xclient = new XClientMessbgeEvent(ctxt);
            if (!overXEmbedClient) {
                long dbtb1 = Nbtive.getLong(ctxt + size);
                long dbtb2 = Nbtive.getLong(ctxt + size + Nbtive.getLongSize());
                long dbtb3 = Nbtive.getLong(ctxt + size + 2 * Nbtive.getLongSize());
                long dbtb4 = Nbtive.getLong(ctxt + size + 3 * Nbtive.getLongSize());

                if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    logger.finest("         1 "
                                  + " embedded=" + embedded
                                  + " source=" + xclient.get_dbtb(0)
                                  + " dbtb1=" + dbtb1
                                  + " dbtb2=" + dbtb2
                                  + " dbtb3=" + dbtb3
                                  + " dbtb4=" + dbtb4);
                }

                // Copy XdndTypeList from source to proxy.
                if ((dbtb1 & XDnDConstbnts.XDND_DATA_TYPES_BIT) != 0) {
                    WindowPropertyGetter wpg =
                        new WindowPropertyGetter(xclient.get_dbtb(0),
                                                 XDnDConstbnts.XA_XdndTypeList,
                                                 0, 0xFFFF, fblse,
                                                 XAtom.XA_ATOM);
                    try {
                        wpg.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

                        if (wpg.getActublType() == XAtom.XA_ATOM &&
                            wpg.getActublFormbt() == 32) {

                            XToolkit.bwtLock();
                            try {
                                XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
                                XDnDConstbnts.XA_XdndTypeList.setAtomDbtb(xclient.get_window(),
                                                                          XAtom.XA_ATOM,
                                                                          wpg.getDbtb(),
                                                                          wpg.getNumberOfItems());
                                XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

                                if ((XErrorHbndlerUtil.sbved_error != null) &&
                                    (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                                    if (logger.isLoggbble(PlbtformLogger.Level.WARNING)) {
                                        logger.wbrning("Cbnnot set XdndTypeList on the proxy window");
                                    }
                                }
                            } finblly {
                                XToolkit.bwtUnlock();
                            }
                        } else {
                            if (logger.isLoggbble(PlbtformLogger.Level.WARNING)) {
                                logger.wbrning("Cbnnot rebd XdndTypeList from the source window");
                            }
                        }
                    } finblly {
                        wpg.dispose();
                    }
                }
                XDrbgSourceContextPeer.setProxyModeSourceWindow(xclient.get_dbtb(0));

                sendEnterMessbgeToToplevelImpl(embedded, xclient.get_window(),
                                               dbtb1, dbtb2, dbtb3, dbtb4);
                overXEmbedClient = true;
            }

            if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
                logger.finest("         2 "
                              + " embedded=" + embedded
                              + " xclient=" + xclient);
            }

            /* Mbke b copy of the originbl event, since we bre going to modify the
               event while it still cbn be referenced from other Jbvb events. */
            {
                XClientMessbgeEvent copy = new XClientMessbgeEvent();
                unsbfe.copyMemory(xclient.pDbtb, copy.pDbtb, XClientMessbgeEvent.getSize());

                copy.set_dbtb(0, xclient.get_window());

                forwbrdClientMessbgeToToplevel(embedded, copy);
            }
        }

        if (eventID == MouseEvent.MOUSE_EXITED) {
            if (overXEmbedClient) {
                if (ctxt != 0 || prevCtxt != 0) {
                    // Lbst chbnce to send XdndLebve to the XEmbed client.
                    XClientMessbgeEvent xclient = ctxt != 0 ?
                        new XClientMessbgeEvent(ctxt) :
                        new XClientMessbgeEvent(prevCtxt);
                    sendLebveMessbgeToToplevelImpl(embedded, xclient.get_window());
                }
                overXEmbedClient = fblse;
                // We hbve to clebr the proxy mode source window here,
                // when the drbg exits the XEmbedCbnvbsPeer.
                // NOTE: bt this point the XEmbed client still might hbve some
                // drbg notificbtions to process bnd it will send responses to
                // us. With the proxy mode source window clebred we won't be
                // bble to forwbrd these responses to the bctubl source. This is
                // not b problem if the drbg operbtion wbs initibted in this
                // JVM. However, if it wbs initibted in bnother processes the
                // responses will be lost. We bebr with it for now, bs it seems
                // there is no other relibble point to clebr.
                XDrbgSourceContextPeer.setProxyModeSourceWindow(0);
            }
        }

        if (eventID == MouseEvent.MOUSE_RELEASED) {
            overXEmbedClient = fblse;
            clebnup();
        }

        if (prevCtxt != 0) {
            unsbfe.freeMemory(prevCtxt);
            prevCtxt = 0;
        }

        if (ctxt != 0 && overXEmbedClient) {
            prevCtxt = unsbfe.bllocbteMemory(size + 4 * Nbtive.getLongSize());

            unsbfe.copyMemory(ctxt, prevCtxt, size + 4 * Nbtive.getLongSize());
        }

        return true;
    }

    public boolebn isXEmbedSupported() {
        return true;
    }
}
