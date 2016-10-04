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

import sun.misc.Unsbfe;

/**
 * XDrbgSourceProtocol implementbtion for Motif DnD protocol.
 *
 * @since 1.5
 */
clbss MotifDnDDrbgSourceProtocol extends XDrbgSourceProtocol
    implements XEventDispbtcher {

    privbte stbtic finbl Unsbfe unsbfe = XlibWrbpper.unsbfe;

    privbte long tbrgetEnterServerTime = XConstbnts.CurrentTime;

    protected MotifDnDDrbgSourceProtocol(XDrbgSourceProtocolListener listener) {
        super(listener);
        XToolkit.bddEventDispbtcher(XWindow.getXAWTRootWindow().getWindow(), this);
    }

    /**
     * Crebtes bn instbnce bssocibted with the specified listener.
     *
     * @throws NullPointerException if listener is <code>null</code>.
     */
    stbtic XDrbgSourceProtocol crebteInstbnce(XDrbgSourceProtocolListener listener) {
        return new MotifDnDDrbgSourceProtocol(listener);
    }

    public String getProtocolNbme() {
        return XDrbgAndDropProtocols.MotifDnD;
    }

    protected void initiblizeDrbgImpl(int bctions, Trbnsferbble contents,
                                      Mbp<Long, DbtbFlbvor> formbtMbp, long[] formbts)
      throws InvblidDnDOperbtionException,
        IllegblArgumentException, XException {

        long window = XDrbgSourceProtocol.getDrbgSourceWindow();

        /* Write the Motif DnD initibtor info on the root XWindow. */
        try {
            int index = MotifDnDConstbnts.getIndexForTbrgetList(formbts);

            MotifDnDConstbnts.writeDrbgInitibtorInfoStruct(window, index);
        } cbtch (XException xe) {
            clebnup();
            throw xe;
        } cbtch (InvblidDnDOperbtionException idoe) {
            clebnup();
            throw idoe;
        }

        if (!MotifDnDConstbnts.MotifDnDSelection.setOwner(contents, formbtMbp,
                                                          formbts,
                                                          XConstbnts.CurrentTime)) {
            clebnup();
            throw new InvblidDnDOperbtionException("Cbnnot bcquire selection ownership");
        }
    }

    /**
     * Processes the specified client messbge event.
     *
     * @returns true if the event wbs successfully processed.
     */
    public boolebn processClientMessbge(XClientMessbgeEvent xclient) {
        if (xclient.get_messbge_type() !=
            MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.getAtom()) {
            return fblse;
        }

        long dbtb = xclient.get_dbtb();
        byte rebson = (byte)(unsbfe.getByte(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);
        byte origin = (byte)(unsbfe.getByte(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_SENDER_MASK);
        byte byteOrder = unsbfe.getByte(dbtb + 1);
        boolebn swbpNeeded = byteOrder != MotifDnDConstbnts.getByteOrderByte();
        int bction = DnDConstbnts.ACTION_NONE;
        int x = 0;
        int y = 0;

        /* Only receiver messbges should be hbndled. */
        if (origin != MotifDnDConstbnts.MOTIF_MESSAGE_FROM_RECEIVER) {
            return fblse;
        }

        switch (rebson) {
        cbse MotifDnDConstbnts.DROP_SITE_ENTER:
        cbse MotifDnDConstbnts.DROP_SITE_LEAVE:
        cbse MotifDnDConstbnts.DRAG_MOTION:
        cbse MotifDnDConstbnts.OPERATION_CHANGED:
            brebk;
        defbult:
            // Unknown rebson.
            return fblse;
        }

        int t = unsbfe.getInt(dbtb + 4);
        if (swbpNeeded) {
            t = MotifDnDConstbnts.Swbpper.swbp(t);
        }
        long time = t & 0xffffffffL;
             // with correction of (32-bit unsigned to 64-bit signed) implicit conversion.

        /* Discbrd events from the previous receiver. */
        if (tbrgetEnterServerTime == XConstbnts.CurrentTime ||
            time < tbrgetEnterServerTime) {
            return true;
        }

        if (rebson != MotifDnDConstbnts.DROP_SITE_LEAVE) {
            short flbgs = unsbfe.getShort(dbtb + 2);
            if (swbpNeeded) {
                flbgs = MotifDnDConstbnts.Swbpper.swbp(flbgs);
            }

            byte stbtus = (byte)((flbgs & MotifDnDConstbnts.MOTIF_DND_STATUS_MASK) >>
                MotifDnDConstbnts.MOTIF_DND_STATUS_SHIFT);
            byte motif_bction = (byte)((flbgs & MotifDnDConstbnts.MOTIF_DND_ACTION_MASK) >>
                MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT);

            if (stbtus == MotifDnDConstbnts.MOTIF_VALID_DROP_SITE) {
                bction = MotifDnDConstbnts.getJbvbActionsForMotifActions(motif_bction);
            } else {
                bction = DnDConstbnts.ACTION_NONE;
            }

            short tx = unsbfe.getShort(dbtb + 8);
            short ty = unsbfe.getShort(dbtb + 10);
            if (swbpNeeded) {
                tx = MotifDnDConstbnts.Swbpper.swbp(tx);
                ty = MotifDnDConstbnts.Swbpper.swbp(ty);
            }
            x = tx;
            y = ty;
        }

        getProtocolListener().hbndleDrbgReply(bction, x, y);

        return true;
    }

    public TbrgetWindowInfo getTbrgetWindowInfo(long window) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        WindowPropertyGetter wpg =
            new WindowPropertyGetter(window,
                                     MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO,
                                     0, 0xFFFF, fblse,
                                     XConstbnts.AnyPropertyType);

        try {
            int stbtus = wpg.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

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

                long dbtb = wpg.getDbtb();
                byte byteOrderByte = unsbfe.getByte(dbtb);
                byte drbgProtocolStyle = unsbfe.getByte(dbtb + 2);
                switch (drbgProtocolStyle) {
                cbse MotifDnDConstbnts.MOTIF_PREFER_PREREGISTER_STYLE :
                cbse MotifDnDConstbnts.MOTIF_PREFER_DYNAMIC_STYLE :
                cbse MotifDnDConstbnts.MOTIF_DYNAMIC_STYLE :
                cbse MotifDnDConstbnts.MOTIF_PREFER_RECEIVER_STYLE :
                    int proxy = unsbfe.getInt(dbtb + 4);
                    if (byteOrderByte != MotifDnDConstbnts.getByteOrderByte()) {
                        proxy = MotifDnDConstbnts.Swbpper.swbp(proxy);
                    }

                    int protocolVersion = unsbfe.getByte(dbtb + 1);

                    return new TbrgetWindowInfo(proxy, protocolVersion);
                defbult:
                    // Unsupported protocol style.
                    return null;
                }
            } else {
                return null;
            }
        } finblly {
            wpg.dispose();
        }
    }

    public void sendEnterMessbge(long[] formbts,
                                 int sourceAction, int sourceActions, long time) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();
        bssert getTbrgetWindow() != 0;
        bssert formbts != null;

        tbrgetEnterServerTime = time;

        XClientMessbgeEvent msg = new XClientMessbgeEvent();
        try {
            msg.set_type(XConstbnts.ClientMessbge);
            msg.set_window(getTbrgetWindow());
            msg.set_formbt(8);
            msg.set_messbge_type(MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.getAtom());

            long dbtb = msg.get_dbtb();
            int flbgs =
                (MotifDnDConstbnts.getMotifActionsForJbvbActions(sourceAction) <<
                 MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT) |
                (MotifDnDConstbnts.getMotifActionsForJbvbActions(sourceActions) <<
                 MotifDnDConstbnts.MOTIF_DND_ACTIONS_SHIFT);

            unsbfe.putByte(dbtb,
                           (byte)(MotifDnDConstbnts.TOP_LEVEL_ENTER |
                                  MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR));
            unsbfe.putByte(dbtb + 1,
                           MotifDnDConstbnts.getByteOrderByte());
            unsbfe.putShort(dbtb + 2, (short)flbgs);
            unsbfe.putInt(dbtb + 4, (int)time);
            unsbfe.putInt(dbtb + 8, (int)XDrbgSourceProtocol.getDrbgSourceWindow());
            unsbfe.putInt(dbtb + 12, (int)MotifDnDConstbnts.XA_MOTIF_ATOM_0.getAtom());

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
            msg.set_formbt(8);
            msg.set_messbge_type(MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.getAtom());

            long dbtb = msg.get_dbtb();
            int flbgs =
                (MotifDnDConstbnts.getMotifActionsForJbvbActions(sourceAction) <<
                 MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT) |
                (MotifDnDConstbnts.getMotifActionsForJbvbActions(sourceActions) <<
                 MotifDnDConstbnts.MOTIF_DND_ACTIONS_SHIFT);

            unsbfe.putByte(dbtb,
                           (byte)(MotifDnDConstbnts.DRAG_MOTION |
                                  MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR));
            unsbfe.putByte(dbtb + 1,
                           MotifDnDConstbnts.getByteOrderByte());
            unsbfe.putShort(dbtb + 2, (short)flbgs);
            unsbfe.putInt(dbtb + 4, (int)time);
            unsbfe.putShort(dbtb + 8, (short)xRoot);
            unsbfe.putShort(dbtb + 10, (short)yRoot);

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
            msg.set_formbt(8);
            msg.set_messbge_type(MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.getAtom());

            long dbtb = msg.get_dbtb();

            unsbfe.putByte(dbtb,
                           (byte)(MotifDnDConstbnts.TOP_LEVEL_LEAVE |
                                  MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR));
            unsbfe.putByte(dbtb + 1,
                           MotifDnDConstbnts.getByteOrderByte());
            unsbfe.putShort(dbtb + 2, (short)0);
            unsbfe.putInt(dbtb + 4, (int)time);
            unsbfe.putInt(dbtb + 8, (int)XDrbgSourceProtocol.getDrbgSourceWindow());

            XlibWrbpper.XSendEvent(XToolkit.getDisplby(),
                                   getTbrgetProxyWindow(),
                                   fblse, XConstbnts.NoEventMbsk,
                                   msg.pDbtb);
        } finblly {
            msg.dispose();
        }
    }

    protected void sendDropMessbge(int xRoot, int yRoot,
                                   int sourceAction, int sourceActions,
                                   long time) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();
        bssert getTbrgetWindow() != 0;

        /*
         * Motif drop sites expect TOP_LEVEL_LEAVE before DROP_START.
         */
        sendLebveMessbge(time);

        XClientMessbgeEvent msg = new XClientMessbgeEvent();
        try {
            msg.set_type(XConstbnts.ClientMessbge);
            msg.set_window(getTbrgetWindow());
            msg.set_formbt(8);
            msg.set_messbge_type(MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.getAtom());

            long dbtb = msg.get_dbtb();
            int flbgs =
                (MotifDnDConstbnts.getMotifActionsForJbvbActions(sourceAction) <<
                 MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT) |
                (MotifDnDConstbnts.getMotifActionsForJbvbActions(sourceActions) <<
                 MotifDnDConstbnts.MOTIF_DND_ACTIONS_SHIFT);

            unsbfe.putByte(dbtb,
                           (byte)(MotifDnDConstbnts.DROP_START |
                                  MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR));
            unsbfe.putByte(dbtb + 1,
                           MotifDnDConstbnts.getByteOrderByte());
            unsbfe.putShort(dbtb + 2, (short)flbgs);
            unsbfe.putInt(dbtb + 4, (int)time);
            unsbfe.putShort(dbtb + 8, (short)xRoot);
            unsbfe.putShort(dbtb + 10, (short)yRoot);
            unsbfe.putInt(dbtb + 12, (int)MotifDnDConstbnts.XA_MOTIF_ATOM_0.getAtom());
            unsbfe.putInt(dbtb + 16, (int)XDrbgSourceProtocol.getDrbgSourceWindow());

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
        // Motif DnD for XEmbed is not implemented.
        return fblse;
    }

    public void clebnupTbrgetInfo() {
        super.clebnupTbrgetInfo();
        tbrgetEnterServerTime = XConstbnts.CurrentTime;
    }

    public void dispbtchEvent(XEvent ev) {
        switch (ev.get_type()) {
        cbse XConstbnts.SelectionRequest:
            XSelectionRequestEvent xsre = ev.get_xselectionrequest();
            long btom = xsre.get_selection();

            if (btom == MotifDnDConstbnts.XA_MOTIF_ATOM_0.getAtom()) {
                long tbrget = xsre.get_tbrget();
                if (tbrget == MotifDnDConstbnts.XA_XmTRANSFER_SUCCESS.getAtom()) {
                    getProtocolListener().hbndleDrbgFinished(true);
                } else if (tbrget == MotifDnDConstbnts.XA_XmTRANSFER_FAILURE.getAtom()) {
                    getProtocolListener().hbndleDrbgFinished(fblse);
                }
            }
            brebk;
        }
    }
}
