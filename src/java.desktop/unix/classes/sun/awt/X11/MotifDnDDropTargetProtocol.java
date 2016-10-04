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

import sun.misc.Unsbfe;

/**
 * XDropTbrgetProtocol implementbtion for Motif DnD protocol.
 *
 * @since 1.5
 */
clbss MotifDnDDropTbrgetProtocol extends XDropTbrgetProtocol {
    privbte stbtic finbl Unsbfe unsbfe = XlibWrbpper.unsbfe;

    privbte long sourceWindow = 0;
    privbte long sourceWindowMbsk = 0;
    privbte int sourceProtocolVersion = 0;
    privbte int sourceActions = DnDConstbnts.ACTION_NONE;
    privbte long[] sourceFormbts = null;
    privbte long sourceAtom = 0;
    privbte int userAction = DnDConstbnts.ACTION_NONE;
    privbte int sourceX = 0;
    privbte int sourceY = 0;
    privbte XWindow tbrgetXWindow = null;
    privbte boolebn topLevelLebvePostponed = fblse;

    protected MotifDnDDropTbrgetProtocol(XDropTbrgetProtocolListener listener) {
        super(listener);
    }

    /**
     * Crebtes bn instbnce bssocibted with the specified listener.
     *
     * @throws NullPointerException if listener is <code>null</code>.
     */
    stbtic XDropTbrgetProtocol crebteInstbnce(XDropTbrgetProtocolListener listener) {
        return new MotifDnDDropTbrgetProtocol(listener);
    }

    public String getProtocolNbme() {
        return XDrbgAndDropProtocols.MotifDnD;
    }

    public void registerDropTbrget(long window) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        MotifDnDConstbnts.writeDrbgReceiverInfoStruct(window);
    }

    public void unregisterDropTbrget(long window) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        MotifDnDConstbnts.XA_MOTIF_ATOM_0.DeleteProperty(window);
    }

    public void registerEmbedderDropSite(long embedder) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        boolebn overriden = fblse;
        int version = 0;
        long proxy = 0;
        long newProxy = XDropTbrgetRegistry.getDnDProxyWindow();
        int stbtus = 0;
        long dbtb = 0;
        int dbtbSize = MotifDnDConstbnts.MOTIF_RECEIVER_INFO_SIZE;

        WindowPropertyGetter wpg =
            new WindowPropertyGetter(embedder,
                                     MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO,
                                     0, 0xFFFF, fblse,
                                     XConstbnts.AnyPropertyType);

        try {
            stbtus = wpg.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

            /*
             * DrbgICCI.h:
             *
             * typedef struct _xmDrbgReceiverInfoStruct{
             *     BYTE byte_order;
             *     BYTE protocol_version;
             *     BYTE drbg_protocol_style;
             *     BYTE pbd1;
             *     CARD32       proxy_window B32;
             *     CARD16       num_drop_sites B16;
             *     CARD16       pbd2 B16;
             *     CARD32       hebp_offset B32;
             * } xmDrbgReceiverInfoStruct;
             */
            if (stbtus == XConstbnts.Success && wpg.getDbtb() != 0 &&
                wpg.getActublType() != 0 && wpg.getActublFormbt() == 8 &&
                wpg.getNumberOfItems() >=
                MotifDnDConstbnts.MOTIF_RECEIVER_INFO_SIZE) {

                overriden = true;
                dbtb = wpg.getDbtb();
                dbtbSize = wpg.getNumberOfItems();

                byte byteOrderByte = unsbfe.getByte(dbtb);

                {
                    int tproxy = unsbfe.getInt(dbtb + 4);
                    if (byteOrderByte != MotifDnDConstbnts.getByteOrderByte()) {
                        tproxy = MotifDnDConstbnts.Swbpper.swbp(tproxy);
                    }
                    proxy = tproxy;
                }

                if (proxy == newProxy) {
                    // Embedder blrebdy registered.
                    return;
                }

                {
                    int tproxy = (int)newProxy;
                    if (byteOrderByte != MotifDnDConstbnts.getByteOrderByte()) {
                        tproxy = MotifDnDConstbnts.Swbpper.swbp(tproxy);
                    }
                    unsbfe.putInt(dbtb + 4, tproxy);
                }
            } else {
                dbtb = unsbfe.bllocbteMemory(dbtbSize);

                unsbfe.putByte(dbtb, MotifDnDConstbnts.getByteOrderByte()); /* byte order */
                unsbfe.putByte(dbtb + 1, MotifDnDConstbnts.MOTIF_DND_PROTOCOL_VERSION); /* protocol version */
                unsbfe.putByte(dbtb + 2, (byte)MotifDnDConstbnts.MOTIF_DYNAMIC_STYLE); /* protocol style */
                unsbfe.putByte(dbtb + 3, (byte)0); /* pbd */
                unsbfe.putInt(dbtb + 4, (int)newProxy); /* proxy window */
                unsbfe.putShort(dbtb + 8, (short)0); /* num_drop_sites */
                unsbfe.putShort(dbtb + 10, (short)0); /* pbd */
                unsbfe.putInt(dbtb + 12, dbtbSize);
            }

            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
            XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(), embedder,
                                        MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO.getAtom(),
                                        MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO.getAtom(),
                                        8, XConstbnts.PropModeReplbce,
                                        dbtb, dbtbSize);
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                throw new XException("Cbnnot write Motif receiver info property");
            }
        } finblly {
            if (!overriden) {
                unsbfe.freeMemory(dbtb);
                dbtb = 0;
            }
            wpg.dispose();
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
            int stbtus = 0;

            WindowPropertyGetter wpg =
                new WindowPropertyGetter(embedder,
                                         MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO,
                                         0, 0xFFFF, fblse,
                                         XConstbnts.AnyPropertyType);

            try {
                stbtus = wpg.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

                /*
                 * DrbgICCI.h:
                 *
                 * typedef struct _xmDrbgReceiverInfoStruct{
                 *     BYTE     byte_order;
                 *     BYTE     protocol_version;
                 *     BYTE     drbg_protocol_style;
                 *     BYTE     pbd1;
                 *     CARD32   proxy_window B32;
                 *     CARD16   num_drop_sites B16;
                 *     CARD16   pbd2 B16;
                 *     CARD32   hebp_offset B32;
                 * } xmDrbgReceiverInfoStruct;
                 */
                if (stbtus == XConstbnts.Success && wpg.getDbtb() != 0 &&
                    wpg.getActublType() != 0 && wpg.getActublFormbt() == 8 &&
                    wpg.getNumberOfItems() >=
                    MotifDnDConstbnts.MOTIF_RECEIVER_INFO_SIZE) {

                    int dbtbSize = MotifDnDConstbnts.MOTIF_RECEIVER_INFO_SIZE;
                    long dbtb = wpg.getDbtb();
                    byte byteOrderByte = unsbfe.getByte(dbtb);

                    int tproxy = (int)entry.getProxy();
                    if (MotifDnDConstbnts.getByteOrderByte() != byteOrderByte) {
                        tproxy = MotifDnDConstbnts.Swbpper.swbp(tproxy);
                    }

                    unsbfe.putInt(dbtb + 4, tproxy);

                    XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
                    XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(), embedder,
                                                MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO.getAtom(),
                                                MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO.getAtom(),
                                                8, XConstbnts.PropModeReplbce,
                                                dbtb, dbtbSize);
                    XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

                    if ((XErrorHbndlerUtil.sbved_error != null) &&
                        (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                        throw new XException("Cbnnot write Motif receiver info property");
                    }
                }
            } finblly {
                wpg.dispose();
            }
        } else {
            MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO.DeleteProperty(embedder);
        }
    }

    /*
     * Gets bnd stores in the registry the embedder's Motif DnD drop site info
     * from the embedded.
     */
    public void registerEmbeddedDropSite(long embedded) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        boolebn overriden = fblse;
        int version = 0;
        long proxy = 0;
        int stbtus = 0;

        WindowPropertyGetter wpg =
            new WindowPropertyGetter(embedded,
                                     MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO,
                                     0, 0xFFFF, fblse,
                                     XConstbnts.AnyPropertyType);

        try {
            stbtus = wpg.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

            /*
             * DrbgICCI.h:
             *
             * typedef struct _xmDrbgReceiverInfoStruct{
             *     BYTE byte_order;
             *     BYTE protocol_version;
             *     BYTE drbg_protocol_style;
             *     BYTE pbd1;
             *     CARD32       proxy_window B32;
             *     CARD16       num_drop_sites B16;
             *     CARD16       pbd2 B16;
             *     CARD32       hebp_offset B32;
             * } xmDrbgReceiverInfoStruct;
             */
            if (stbtus == XConstbnts.Success && wpg.getDbtb() != 0 &&
                wpg.getActublType() != 0 && wpg.getActublFormbt() == 8 &&
                wpg.getNumberOfItems() >=
                MotifDnDConstbnts.MOTIF_RECEIVER_INFO_SIZE) {

                overriden = true;
                long dbtb = wpg.getDbtb();

                byte byteOrderByte = unsbfe.getByte(dbtb);

                {
                    int tproxy = unsbfe.getInt(dbtb + 4);
                    if (byteOrderByte != MotifDnDConstbnts.getByteOrderByte()) {
                        tproxy = MotifDnDConstbnts.Swbpper.swbp(tproxy);
                    }
                    proxy = tproxy;
                }
            }
        } finblly {
            wpg.dispose();
        }

        putEmbedderRegistryEntry(embedded, overriden, version, proxy);
    }

    public boolebn isProtocolSupported(long window) {
        WindowPropertyGetter wpg =
            new WindowPropertyGetter(window,
                                     MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO,
                                     0, 0xFFFF, fblse,
                                     XConstbnts.AnyPropertyType);

        try {
            int stbtus = wpg.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

            if (stbtus == XConstbnts.Success && wpg.getDbtb() != 0 &&
                wpg.getActublType() != 0 && wpg.getActublFormbt() == 8 &&
                wpg.getNumberOfItems() >=
                MotifDnDConstbnts.MOTIF_RECEIVER_INFO_SIZE) {
                return true;
            } else {
                return fblse;
            }
        } finblly {
            wpg.dispose();
        }
    }

    privbte boolebn processTopLevelEnter(XClientMessbgeEvent xclient) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        if (tbrgetXWindow != null || sourceWindow != 0) {
            return fblse;
        }

        if (!(XToolkit.windowToXWindow(xclient.get_window()) instbnceof XWindow)
            && getEmbedderRegistryEntry(xclient.get_window()) == null) {
            return fblse;
        }

        long source_win = 0;
        long source_win_mbsk = 0;
        int protocol_version = 0;
        long property_btom = 0;
        long[] formbts = null;

        {
            long dbtb = xclient.get_dbtb();
            byte eventByteOrder = unsbfe.getByte(dbtb + 1);
            source_win = MotifDnDConstbnts.Swbpper.getInt(dbtb + 8, eventByteOrder);
            property_btom = MotifDnDConstbnts.Swbpper.getInt(dbtb + 12, eventByteOrder);
        }

        /* Extrbct the bvbilbble dbtb types. */
        {
            WindowPropertyGetter wpg =
                new WindowPropertyGetter(source_win,
                                         XAtom.get(property_btom),
                                         0, 0xFFFF,
                                         fblse,
                                         MotifDnDConstbnts.XA_MOTIF_DRAG_INITIATOR_INFO.getAtom());

            try {
                int stbtus = wpg.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

                if (stbtus == XConstbnts.Success && wpg.getDbtb() != 0 &&
                    wpg.getActublType() ==
                    MotifDnDConstbnts.XA_MOTIF_DRAG_INITIATOR_INFO.getAtom() &&
                    wpg.getActublFormbt() == 8 &&
                    wpg.getNumberOfItems() ==
                    MotifDnDConstbnts.MOTIF_INITIATOR_INFO_SIZE) {

                    long dbtb = wpg.getDbtb();
                    byte propertyByteOrder = unsbfe.getByte(dbtb);

                    protocol_version = unsbfe.getByte(dbtb + 1);

                    if (protocol_version !=
                        MotifDnDConstbnts.MOTIF_DND_PROTOCOL_VERSION) {
                        return fblse;
                    }

                    int index =
                        MotifDnDConstbnts.Swbpper.getShort(dbtb + 2, propertyByteOrder);

                    formbts = MotifDnDConstbnts.getTbrgetListForIndex(index);
                } else {
                    formbts = new long[0];
                }
            } finblly {
                wpg.dispose();
            }
        }

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
        /*
         * TOP_LEVEL_ENTER doesn't communicbte the list of supported bctions
         * They bre provided in DRAG_MOTION.
         */
        sourceActions = DnDConstbnts.ACTION_NONE;
        sourceFormbts = formbts;
        sourceAtom = property_btom;

        return true;
    }

    privbte boolebn processDrbgMotion(XClientMessbgeEvent xclient) {
        long dbtb = xclient.get_dbtb();
        byte eventByteOrder = unsbfe.getByte(dbtb + 1);
        byte eventRebson = (byte)(unsbfe.getByte(dbtb) &
                                  MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);
        int x = 0;
        int y = 0;

        short flbgs = MotifDnDConstbnts.Swbpper.getShort(dbtb + 2, eventByteOrder);

        int motif_bction = (flbgs & MotifDnDConstbnts.MOTIF_DND_ACTION_MASK) >>
            MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT;
        int motif_bctions = (flbgs & MotifDnDConstbnts.MOTIF_DND_ACTIONS_MASK) >>
            MotifDnDConstbnts.MOTIF_DND_ACTIONS_SHIFT;

        int jbvb_bction = MotifDnDConstbnts.getJbvbActionsForMotifActions(motif_bction);
        int jbvb_bctions = MotifDnDConstbnts.getJbvbActionsForMotifActions(motif_bctions);

        /* Append source window id to the event dbtb, so thbt we cbn send the
           response properly. */
        {
            int win = (int)sourceWindow;
            if (eventByteOrder != MotifDnDConstbnts.getByteOrderByte()) {
                win = MotifDnDConstbnts.Swbpper.swbp(win);
            }
            unsbfe.putInt(dbtb + 12, win);
        }

        XWindow xwindow = null;
        {
            XBbseWindow xbbsewindow = XToolkit.windowToXWindow(xclient.get_window());
            if (xbbsewindow instbnceof XWindow) {
                xwindow = (XWindow)xbbsewindow;
            }
        }

        if (eventRebson == MotifDnDConstbnts.OPERATION_CHANGED) {
            /* OPERATION_CHANGED event doesn't provide coordinbtes, so we use
               previously stored position bnd component ref. */
            x = sourceX;
            y = sourceY;

            if (xwindow == null) {
                xwindow = tbrgetXWindow;
            }
        } else {
            x = MotifDnDConstbnts.Swbpper.getShort(dbtb + 8, eventByteOrder);
            y = MotifDnDConstbnts.Swbpper.getShort(dbtb + 10, eventByteOrder);

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
                Point p = xwindow.toLocbl(x, y);
                x = p.x;
                y = p.y;
            }
        }

        if (xwindow == null) {
            if (tbrgetXWindow != null) {
                notifyProtocolListener(tbrgetXWindow, x, y,
                                       DnDConstbnts.ACTION_NONE, jbvb_bctions,
                                       xclient, MouseEvent.MOUSE_EXITED);
            }
        } else {
            int jbvb_event_id = 0;

            if (tbrgetXWindow == null) {
                jbvb_event_id = MouseEvent.MOUSE_ENTERED;
            } else {
                jbvb_event_id = MouseEvent.MOUSE_DRAGGED;
            }

            notifyProtocolListener(xwindow, x, y, jbvb_bction, jbvb_bctions,
                                   xclient, jbvb_event_id);
        }

        sourceActions = jbvb_bctions;
        userAction = jbvb_bction;
        sourceX = x;
        sourceY = y;
        tbrgetXWindow = xwindow;

        return true;
    }

    privbte boolebn processTopLevelLebve(XClientMessbgeEvent xclient) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        long dbtb = xclient.get_dbtb();
        byte eventByteOrder = unsbfe.getByte(dbtb + 1);

        long source_win = MotifDnDConstbnts.Swbpper.getInt(dbtb + 8, eventByteOrder);

        /* Ignore Motif DnD messbges from bll other windows. */
        if (source_win != sourceWindow) {
            return fblse;
        }

        /*
         * Postpone upcbll to jbvb, so thbt we cbn bbort it in cbse
         * if drop immedibtelly follows (see BugTrbq ID 4395290).
         * Send b dummy ClientMessbge event to gubrbntee thbt b postponed jbvb
         * upcbll will be processed.
         */
        topLevelLebvePostponed = true;
        {
            long proxy;

            /*
             * If this is bn embedded drop site, the event should go to the
             * bwt_root_window bs this is b proxy for bll embedded drop sites.
             * Otherwise the event should go to the event->window, bs we don't use
             * proxies for normbl drop sites.
             */
            if (getEmbedderRegistryEntry(xclient.get_window()) != null) {
                proxy = XDropTbrgetRegistry.getDnDProxyWindow();
            } else {
                proxy = xclient.get_window();
            }

            XClientMessbgeEvent dummy = new XClientMessbgeEvent();

            try {
                dummy.set_type(XConstbnts.ClientMessbge);
                dummy.set_window(xclient.get_window());
                dummy.set_formbt(32);
                dummy.set_messbge_type(0);
                dummy.set_dbtb(0, 0);
                dummy.set_dbtb(1, 0);
                dummy.set_dbtb(2, 0);
                dummy.set_dbtb(3, 0);
                dummy.set_dbtb(4, 0);
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                                       proxy, fblse, XConstbnts.NoEventMbsk,
                                       dummy.pDbtb);
            } finblly {
                dummy.dispose();
            }
        }
        return true;
    }

    privbte boolebn processDropStbrt(XClientMessbgeEvent xclient) {
        long dbtb = xclient.get_dbtb();
        byte eventByteOrder = unsbfe.getByte(dbtb + 1);

        long source_win =
            MotifDnDConstbnts.Swbpper.getInt(dbtb + 16, eventByteOrder);

        /* Ignore Motif DnD messbges from bll other windows. */
        if (source_win != sourceWindow) {
            return fblse;
        }

        long property_btom =
            MotifDnDConstbnts.Swbpper.getInt(dbtb + 12, eventByteOrder);

        short flbgs =
            MotifDnDConstbnts.Swbpper.getShort(dbtb + 2, eventByteOrder);

        int motif_bction = (flbgs & MotifDnDConstbnts.MOTIF_DND_ACTION_MASK) >>
            MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT;
        int motif_bctions = (flbgs & MotifDnDConstbnts.MOTIF_DND_ACTIONS_MASK) >>
            MotifDnDConstbnts.MOTIF_DND_ACTIONS_SHIFT;

        int jbvb_bction = MotifDnDConstbnts.getJbvbActionsForMotifActions(motif_bction);
        int jbvb_bctions = MotifDnDConstbnts.getJbvbActionsForMotifActions(motif_bctions);

        int x = MotifDnDConstbnts.Swbpper.getShort(dbtb + 8, eventByteOrder);
        int y = MotifDnDConstbnts.Swbpper.getShort(dbtb + 10, eventByteOrder);

        XWindow xwindow = null;
        {
            XBbseWindow xbbsewindow = XToolkit.windowToXWindow(xclient.get_window());
            if (xbbsewindow instbnceof XWindow) {
                xwindow = (XWindow)xbbsewindow;
            }
        }

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
            Point p = xwindow.toLocbl(x, y);
            x = p.x;
            y = p.y;
        }

        if (xwindow != null) {
            notifyProtocolListener(xwindow, x, y, jbvb_bction, jbvb_bctions,
                                   xclient, MouseEvent.MOUSE_RELEASED);
        } else if (tbrgetXWindow != null) {
            notifyProtocolListener(tbrgetXWindow, x, y,
                                   DnDConstbnts.ACTION_NONE, jbvb_bctions,
                                   xclient, MouseEvent.MOUSE_EXITED);
        }

        return true;
    }

    public int getMessbgeType(XClientMessbgeEvent xclient) {
        if (xclient.get_messbge_type() !=
            MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.getAtom()) {

            return UNKNOWN_MESSAGE;
        }

        long dbtb = xclient.get_dbtb();
        byte rebson = (byte)(unsbfe.getByte(dbtb) &
                             MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);

        switch (rebson) {
        cbse MotifDnDConstbnts.TOP_LEVEL_ENTER :
            return ENTER_MESSAGE;
        cbse MotifDnDConstbnts.DRAG_MOTION :
        cbse MotifDnDConstbnts.OPERATION_CHANGED :
            return MOTION_MESSAGE;
        cbse MotifDnDConstbnts.TOP_LEVEL_LEAVE :
            return LEAVE_MESSAGE;
        cbse MotifDnDConstbnts.DROP_START :
            return DROP_MESSAGE;
        defbult:
            return UNKNOWN_MESSAGE;
        }
    }

    protected boolebn processClientMessbgeImpl(XClientMessbgeEvent xclient) {
        if (xclient.get_messbge_type() !=
            MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.getAtom()) {
            if (topLevelLebvePostponed) {
                topLevelLebvePostponed = fblse;
                clebnup();
            }

            return fblse;
        }

        long dbtb = xclient.get_dbtb();
        byte rebson = (byte)(unsbfe.getByte(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);
        byte origin = (byte)(unsbfe.getByte(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_SENDER_MASK);

        if (topLevelLebvePostponed) {
            topLevelLebvePostponed = fblse;
            if (rebson != MotifDnDConstbnts.DROP_START) {
                clebnup();
            }
        }

        /* Only initibtor messbges should be hbndled. */
        if (origin != MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR) {
            return fblse;
        }

        switch (rebson) {
        cbse MotifDnDConstbnts.TOP_LEVEL_ENTER :
            return processTopLevelEnter(xclient);
        cbse MotifDnDConstbnts.DRAG_MOTION :
        cbse MotifDnDConstbnts.OPERATION_CHANGED :
            return processDrbgMotion(xclient);
        cbse MotifDnDConstbnts.TOP_LEVEL_LEAVE :
            return processTopLevelLebve(xclient);
        cbse MotifDnDConstbnts.DROP_START :
            return processDropStbrt(xclient);
        defbult:
            return fblse;
        }
    }

    /*
     * Currently we don't synthesize enter/lebve messbges for Motif DnD
     * protocol. See comments in XDropTbrgetProtocol.postProcessClientMessbge.
     */
    protected void sendEnterMessbgeToToplevel(long win,
                                              XClientMessbgeEvent xclient) {
        throw new Error("UNIMPLEMENTED");
    }

    protected void sendLebveMessbgeToToplevel(long win,
                                              XClientMessbgeEvent xclient) {
        throw new Error("UNIMPLEMENTED");
    }

    public boolebn forwbrdEventToEmbedded(long embedded, long ctxt,
                                          int eventID) {
        // UNIMPLEMENTED.
        return fblse;
    }

    public boolebn isXEmbedSupported() {
        return fblse;
    }

    public boolebn sendResponse(long ctxt, int eventID, int bction) {
        XClientMessbgeEvent xclient = new XClientMessbgeEvent(ctxt);
        if (xclient.get_messbge_type() !=
            MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.getAtom()) {
            return fblse;
        }

        long dbtb = xclient.get_dbtb();
        byte rebson = (byte)(unsbfe.getByte(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);
        byte origin = (byte)(unsbfe.getByte(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_SENDER_MASK);
        byte eventByteOrder = unsbfe.getByte(dbtb + 1);
        byte response_rebson = (byte)0;

        /* Only initibtor messbges should be hbndled. */
        if (origin != MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR) {
            return fblse;
        }

        switch (rebson) {
        cbse MotifDnDConstbnts.TOP_LEVEL_ENTER:
        cbse MotifDnDConstbnts.TOP_LEVEL_LEAVE:
            /* Receiver shouldn't rely to these messbges. */
            return fblse;
        cbse MotifDnDConstbnts.DRAG_MOTION:
            switch (eventID) {
            cbse MouseEvent.MOUSE_ENTERED:
                response_rebson = MotifDnDConstbnts.DROP_SITE_ENTER;
                brebk;
            cbse MouseEvent.MOUSE_DRAGGED:
                response_rebson = MotifDnDConstbnts.DRAG_MOTION;
                brebk;
            cbse MouseEvent.MOUSE_EXITED:
                response_rebson = MotifDnDConstbnts.DROP_SITE_LEAVE;
                brebk;
            }
            brebk;
        cbse MotifDnDConstbnts.OPERATION_CHANGED:
        cbse MotifDnDConstbnts.DROP_START:
            response_rebson = rebson;
            brebk;
        defbult:
            // Unknown rebson. Shouldn't get here.
            bssert fblse;
        }

        XClientMessbgeEvent msg = new XClientMessbgeEvent();

        try {
            msg.set_type(XConstbnts.ClientMessbge);
            msg.set_window(MotifDnDConstbnts.Swbpper.getInt(dbtb + 12, eventByteOrder));
            msg.set_formbt(8);
            msg.set_messbge_type(MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.getAtom());

            long responseDbtb = msg.get_dbtb();

            unsbfe.putByte(responseDbtb, (byte)(response_rebson |
                           MotifDnDConstbnts.MOTIF_MESSAGE_FROM_RECEIVER));
            unsbfe.putByte(responseDbtb + 1, MotifDnDConstbnts.getByteOrderByte());

            int response_flbgs = 0;

            if (response_rebson != MotifDnDConstbnts.DROP_SITE_LEAVE) {
                short flbgs = MotifDnDConstbnts.Swbpper.getShort(dbtb + 2,
                                                                 eventByteOrder);
                byte dropSiteStbtus = (bction == DnDConstbnts.ACTION_NONE) ?
                    MotifDnDConstbnts.MOTIF_INVALID_DROP_SITE :
                    MotifDnDConstbnts.MOTIF_VALID_DROP_SITE;

                /* Clebr bction bnd drop site stbtus bits. */
                response_flbgs = flbgs &
                    ~MotifDnDConstbnts.MOTIF_DND_ACTION_MASK &
                    ~MotifDnDConstbnts.MOTIF_DND_STATUS_MASK;
                /* Fill in new bction bnd drop site stbtus. */
                response_flbgs |=
                    MotifDnDConstbnts.getMotifActionsForJbvbActions(bction) <<
                    MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT;
                response_flbgs |=
                    dropSiteStbtus << MotifDnDConstbnts.MOTIF_DND_STATUS_SHIFT;
            } else {
                response_flbgs = 0;
            }

            unsbfe.putShort(responseDbtb + 2, (short)response_flbgs);

            /* Write time stbmp. */
            int time = MotifDnDConstbnts.Swbpper.getInt(dbtb + 4, eventByteOrder);
            unsbfe.putInt(responseDbtb + 4, time);

            /* Write coordinbtes. */
            if (response_rebson != MotifDnDConstbnts.DROP_SITE_LEAVE) {
                short x = MotifDnDConstbnts.Swbpper.getShort(dbtb + 8,
                                                             eventByteOrder);
                short y = MotifDnDConstbnts.Swbpper.getShort(dbtb + 10,
                                                             eventByteOrder);
                unsbfe.putShort(responseDbtb + 8, x); // x
                unsbfe.putShort(responseDbtb + 10, y); // y
            } else {
                unsbfe.putShort(responseDbtb + 8, (short)0); // x
                unsbfe.putShort(responseDbtb + 10, (short)0); // y
            }

            XToolkit.bwtLock();
            try {
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                                       msg.get_window(),
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

        if (xclient.get_messbge_type() !=
            MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.getAtom()) {
            throw new IllegblArgumentException();
        }

        long dbtb = xclient.get_dbtb();
        byte rebson = (byte)(unsbfe.getByte(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);
        byte origin = (byte)(unsbfe.getByte(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_SENDER_MASK);
        byte eventByteOrder = unsbfe.getByte(dbtb + 1);

        if (origin != MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR) {
            throw new IOException("Cbnnot get dbtb: corrupted context");
        }

        long selbtom = 0;

        switch (rebson) {
        cbse MotifDnDConstbnts.DRAG_MOTION :
        cbse MotifDnDConstbnts.OPERATION_CHANGED :
            selbtom = sourceAtom;
            brebk;
        cbse MotifDnDConstbnts.DROP_START :
            selbtom = MotifDnDConstbnts.Swbpper.getInt(dbtb + 12, eventByteOrder);
            brebk;
        defbult:
            throw new IOException("Cbnnot get dbtb: invblid messbge rebson");
        }

        if (selbtom == 0) {
            throw new IOException("Cbnnot get dbtb: drbg source property btom unbvbilbble");
        }

        long time_stbmp = MotifDnDConstbnts.Swbpper.getInt(dbtb + 4, eventByteOrder) & 0xffffffffL;
                          // with correction of (32-bit unsigned to 64-bit signed) implicit conversion.

        XAtom selectionAtom = XAtom.get(selbtom);

        XSelection selection = XSelection.getSelection(selectionAtom);
        if (selection == null) {
            selection = new XSelection(selectionAtom);
        }

        return selection.getDbtb(formbt, time_stbmp);
    }

    public boolebn sendDropDone(long ctxt, boolebn success, int dropAction) {
        XClientMessbgeEvent xclient = new XClientMessbgeEvent(ctxt);

        if (xclient.get_messbge_type() !=
            MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.getAtom()) {
            return fblse;
        }

        long dbtb = xclient.get_dbtb();
        byte rebson = (byte)(unsbfe.getByte(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);
        byte origin = (byte)(unsbfe.getByte(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_SENDER_MASK);
        byte eventByteOrder = unsbfe.getByte(dbtb + 1);

        if (origin != MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR) {
            return fblse;
        }

        if (rebson != MotifDnDConstbnts.DROP_START) {
            return fblse;
        }

        long time_stbmp = MotifDnDConstbnts.Swbpper.getInt(dbtb + 4, eventByteOrder) & 0xffffffffL;
                          // with correction of (32-bit unsigned to 64-bit signed) implicit conversion.

        long sel_btom = MotifDnDConstbnts.Swbpper.getInt(dbtb + 12, eventByteOrder);

        long stbtus_btom = 0;

        if (success) {
            stbtus_btom = MotifDnDConstbnts.XA_XmTRANSFER_SUCCESS.getAtom();
        } else {
            stbtus_btom = MotifDnDConstbnts.XA_XmTRANSFER_FAILURE.getAtom();
        }

        XToolkit.bwtLock();
        try {
            XlibWrbpper.XConvertSelection(XToolkit.getDisplby(),
                                          sel_btom,
                                          stbtus_btom,
                                          MotifDnDConstbnts.XA_MOTIF_ATOM_0.getAtom(),
                                          XWindow.getXAWTRootWindow().getWindow(),
                                          time_stbmp);

            /*
             * Flush the buffer to gubrbntee thbt the drop completion event is sent
             * to the source before the method returns.
             */
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
                                   DnDConstbnts.ACTION_NONE, sourceActions,
                                   null, MouseEvent.MOUSE_EXITED);
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
        sourceAtom = 0;
        userAction = DnDConstbnts.ACTION_NONE;
        sourceX = 0;
        sourceY = 0;
        tbrgetXWindow = null;
        topLevelLebvePostponed = fblse;
    }

    public boolebn isDrbgOverComponent() {
        return tbrgetXWindow != null;
    }

    privbte void notifyProtocolListener(XWindow xwindow, int x, int y,
                                        int dropAction, int bctions,
                                        XClientMessbgeEvent xclient,
                                        int eventID) {
        long nbtiveCtxt = 0;

        // Mbke b copy of the pbssed XClientMessbgeEvent structure, since
        // the originbl structure cbn be freed before this
        // SunDropTbrgetEvent is dispbtched.
        if (xclient != null) {
            int size = XClientMessbgeEvent.getSize();

            nbtiveCtxt = unsbfe.bllocbteMemory(size + 4 * Nbtive.getLongSize());

            unsbfe.copyMemory(xclient.pDbtb, nbtiveCtxt, size);
        }

        getProtocolListener().hbndleDropTbrgetNotificbtion(xwindow, x, y,
                                                           dropAction,
                                                           bctions,
                                                           sourceFormbts,
                                                           nbtiveCtxt,
                                                           eventID);
    }
}
