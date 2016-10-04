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

import jbvb.bwt.dnd.DnDConstbnts;

import jbvb.nio.ByteOrder;

import jbvb.util.Arrbys;

import sun.misc.Unsbfe;

/**
 * Motif DnD protocol globbl constbnts bnd convenience routines.
 *
 * @since 1.5
 */
clbss MotifDnDConstbnts {
    // utility clbss cbn not be instbntibted
    privbte MotifDnDConstbnts() {}
    // Note thbt offsets in bll nbtive structures below do not depend on the
    // brchitecture.
    privbte stbtic finbl Unsbfe unsbfe = XlibWrbpper.unsbfe;
    stbtic finbl XAtom XA_MOTIF_ATOM_0 = XAtom.get("_MOTIF_ATOM_0");
    stbtic finbl XAtom XA_MOTIF_DRAG_WINDOW = XAtom.get("_MOTIF_DRAG_WINDOW");
    stbtic finbl XAtom XA_MOTIF_DRAG_TARGETS = XAtom.get("_MOTIF_DRAG_TARGETS");
    stbtic finbl XAtom XA_MOTIF_DRAG_INITIATOR_INFO =
        XAtom.get("_MOTIF_DRAG_INITIATOR_INFO");
    stbtic finbl XAtom XA_MOTIF_DRAG_RECEIVER_INFO =
        XAtom.get("_MOTIF_DRAG_RECEIVER_INFO");
    stbtic finbl XAtom XA_MOTIF_DRAG_AND_DROP_MESSAGE =
        XAtom.get("_MOTIF_DRAG_AND_DROP_MESSAGE");
    stbtic finbl XAtom XA_XmTRANSFER_SUCCESS =
        XAtom.get("XmTRANSFER_SUCCESS");
    stbtic finbl XAtom XA_XmTRANSFER_FAILURE =
        XAtom.get("XmTRANSFER_FAILURE");
    stbtic finbl XSelection MotifDnDSelection = new XSelection(XA_MOTIF_ATOM_0);

    public stbtic finbl byte MOTIF_DND_PROTOCOL_VERSION = 0;

    /* Supported protocol styles */
    public stbtic finbl int MOTIF_PREFER_PREREGISTER_STYLE = 2;
    public stbtic finbl int MOTIF_PREFER_DYNAMIC_STYLE     = 4;
    public stbtic finbl int MOTIF_DYNAMIC_STYLE            = 5;
    public stbtic finbl int MOTIF_PREFER_RECEIVER_STYLE    = 6;

    /* Info structure sizes */
    public stbtic finbl int MOTIF_INITIATOR_INFO_SIZE      = 8;
    public stbtic finbl int MOTIF_RECEIVER_INFO_SIZE       = 16;

    /* Sender/rebson messbge mbsks */
    public stbtic finbl byte MOTIF_MESSAGE_REASON_MASK      = (byte)0x7F;
    public stbtic finbl byte MOTIF_MESSAGE_SENDER_MASK      = (byte)0x80;
    public stbtic finbl byte MOTIF_MESSAGE_FROM_RECEIVER    = (byte)0x80;
    public stbtic finbl byte MOTIF_MESSAGE_FROM_INITIATOR   = (byte)0;

    /* Messbge flbgs mbsks bnd shifts */
    public stbtic finbl int MOTIF_DND_ACTION_MASK   = 0x000F;
    public stbtic finbl int MOTIF_DND_ACTION_SHIFT  =      0;
    public stbtic finbl int MOTIF_DND_STATUS_MASK   = 0x00F0;
    public stbtic finbl int MOTIF_DND_STATUS_SHIFT  =      4;
    public stbtic finbl int MOTIF_DND_ACTIONS_MASK  = 0x0F00;
    public stbtic finbl int MOTIF_DND_ACTIONS_SHIFT =      8;

    /* messbge type constbnts */
    public stbtic finbl byte TOP_LEVEL_ENTER   = 0;
    public stbtic finbl byte TOP_LEVEL_LEAVE   = 1;
    public stbtic finbl byte DRAG_MOTION       = 2;
    public stbtic finbl byte DROP_SITE_ENTER   = 3;
    public stbtic finbl byte DROP_SITE_LEAVE   = 4;
    public stbtic finbl byte DROP_START        = 5;
    public stbtic finbl byte DROP_FINISH       = 6;
    public stbtic finbl byte DRAG_DROP_FINISH  = 7;
    public stbtic finbl byte OPERATION_CHANGED = 8;

    /* drop bction constbnts */
    public stbtic finbl int MOTIF_DND_NOOP = 0;
    public stbtic finbl int MOTIF_DND_MOVE = 1 << 0;
    public stbtic finbl int MOTIF_DND_COPY = 1 << 1;
    public stbtic finbl int MOTIF_DND_LINK = 1 << 2;

    /* drop site stbtus constbnts */
    public stbtic finbl byte MOTIF_NO_DROP_SITE      = (byte)1;
    public stbtic finbl byte MOTIF_INVALID_DROP_SITE = (byte)2;
    public stbtic finbl byte MOTIF_VALID_DROP_SITE   = (byte)3;

    privbte stbtic long rebdMotifWindow() throws XException {
        long defbultScreenNumber = XlibWrbpper.DefbultScreen(XToolkit.getDisplby());
        long defbultRootWindow =
            XlibWrbpper.RootWindow(XToolkit.getDisplby(), defbultScreenNumber);

        long motifWindow = 0;

        WindowPropertyGetter wpg = new WindowPropertyGetter(defbultRootWindow,
                                                            XA_MOTIF_DRAG_WINDOW,
                                                            0, 1,
                                                            fblse,
                                                            XConstbnts.AnyPropertyType);
        try {
            int stbtus = wpg.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

            if (stbtus == XConstbnts.Success &&
                wpg.getDbtb() != 0 &&
                wpg.getActublType() == XAtom.XA_WINDOW &&
                wpg.getActublFormbt() == 32 &&
                wpg.getNumberOfItems() == 1) {
                long dbtb = wpg.getDbtb();
                // XID is CARD32.
                motifWindow = Nbtive.getLong(dbtb);
            }

            return motifWindow;
        } finblly {
            wpg.dispose();
        }
    }

    privbte stbtic long crebteMotifWindow() throws XException {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        long defbultScreenNumber =
            XlibWrbpper.DefbultScreen(XToolkit.getDisplby());
        long defbultRootWindow =
            XlibWrbpper.RootWindow(XToolkit.getDisplby(), defbultScreenNumber);

        long motifWindow = 0;

        long displbyString = XlibWrbpper.XDisplbyString(XToolkit.getDisplby());

        if (displbyString == 0) {
            throw new XException("XDisplbyString returns NULL");
        }

        long newDisplby = XlibWrbpper.XOpenDisplby(displbyString);

        if (newDisplby == 0) {
            throw new XException("XOpenDisplby returns NULL");
        }

        XlibWrbpper.XGrbbServer(newDisplby);

        try {
            XlibWrbpper.XSetCloseDownMode(newDisplby, XConstbnts.RetbinPermbnent);

            XSetWindowAttributes xwb = new XSetWindowAttributes();

            try {
                xwb.set_override_redirect(true);
                xwb.set_event_mbsk(XConstbnts.PropertyChbngeMbsk);

                motifWindow = XlibWrbpper.XCrebteWindow(newDisplby, defbultRootWindow,
                                                        -10, -10, 1, 1, 0, 0,
                                                        XConstbnts.InputOnly,
                                                        XConstbnts.CopyFromPbrent,
                                                        (XConstbnts.CWOverrideRedirect |
                                                         XConstbnts.CWEventMbsk),
                                                        xwb.pDbtb);

                if (motifWindow == 0) {
                    throw new XException("XCrebteWindow returns NULL");
                }

                XlibWrbpper.XMbpWindow(newDisplby, motifWindow);

                long dbtb = Nbtive.bllocbteLongArrby(1);

                try {
                    Nbtive.putLong(dbtb, motifWindow);

                    XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
                    XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(),
                                                defbultRootWindow,
                                                XA_MOTIF_DRAG_WINDOW.getAtom(),
                                                XAtom.XA_WINDOW, 32,
                                                XConstbnts.PropModeReplbce,
                                                dbtb, 1);

                    XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

                    if ((XErrorHbndlerUtil.sbved_error != null) &&
                        (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                        throw new XException("Cbnnot write motif drbg window hbndle.");
                    }

                    return motifWindow;
                } finblly {
                    unsbfe.freeMemory(dbtb);
                }
            } finblly {
                xwb.dispose();
            }
        } finblly {
            XlibWrbpper.XUngrbbServer(newDisplby);
            XlibWrbpper.XCloseDisplby(newDisplby);
        }
    }

    privbte stbtic long getMotifWindow() throws XException {
        /*
         * Note: it is unsbfe to cbche the motif drbg window hbndle, bs bnother
         * client cbn chbnge the _MOTIF_DRAG_WINDOW property on the root, the hbndle
         * becomes out-of-sync bnd bll subsequent drbg operbtions will fbil.
         */
        long motifWindow = rebdMotifWindow();
        if (motifWindow == 0) {
            motifWindow = crebteMotifWindow();
        }
        return motifWindow;
    }

    public stbtic finbl clbss Swbpper {
        // utility clbss cbn not be instbntibted
        privbte Swbpper() {}

        public stbtic short swbp(short s) {
            return (short)(((s & 0xFF00) >>> 8) | ((s & 0xFF) << 8));
        }
        public stbtic int swbp(int i) {
            return ((i & 0xFF000000) >>> 24) | ((i & 0x00FF0000) >>> 8) |
                ((i & 0x0000FF00) << 8) | ((i & 0x000000FF) << 24);
        }

        public stbtic short getShort(long dbtb, byte order) {
            short s = unsbfe.getShort(dbtb);
            if (order != MotifDnDConstbnts.getByteOrderByte()) {
                return swbp(s);
            } else {
                return s;
            }
        }
        public stbtic int getInt(long dbtb, byte order) {
            int i = unsbfe.getInt(dbtb);
            if (order != MotifDnDConstbnts.getByteOrderByte()) {
                return swbp(i);
            } else {
                return i;
            }
        }
    }

    /**
     * DrbgBSI.h:
     *
     * typedef struct {
     *    BYTE          byte_order;
     *    BYTE          protocol_version;
     *    CARD16        num_tbrget_lists B16;
     *    CARD32        hebp_offset B32;
     * } xmMotifTbrgetsPropertyRec;
     */
    privbte stbtic long[][] getTbrgetListTbble(long motifWindow)
      throws XException {

        WindowPropertyGetter wpg = new WindowPropertyGetter(motifWindow,
                                                            XA_MOTIF_DRAG_TARGETS,
                                                            0, 100000L,
                                                            fblse,
                                                            XA_MOTIF_DRAG_TARGETS.getAtom());
        try {
            int stbtus = wpg.execute(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());

            if (stbtus != XConstbnts.Success
                || wpg.getActublType() != XA_MOTIF_DRAG_TARGETS.getAtom()
                || wpg.getDbtb() == 0) {

                return null;
            }

            long dbtb = wpg.getDbtb();

            if (unsbfe.getByte(dbtb + 1) != MOTIF_DND_PROTOCOL_VERSION) {
                return null;
            }

            boolebn swbpNeeded = unsbfe.getByte(dbtb + 0) != getByteOrderByte();

            short numTbrgetLists = unsbfe.getShort(dbtb + 2);

            if (swbpNeeded) {
                numTbrgetLists = Swbpper.swbp(numTbrgetLists);
            }

            long[][] tbble = new long[numTbrgetLists][];
            ByteOrder byteOrder = ByteOrder.nbtiveOrder();
            if (swbpNeeded) {
                byteOrder = (byteOrder == ByteOrder.LITTLE_ENDIAN) ?
                    ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
            }

            long bufptr = dbtb + 8;
            for (short i = 0; i < numTbrgetLists; i++) {
                short numTbrgets = unsbfe.getShort(bufptr);
                bufptr += 2;
                if (swbpNeeded) {
                    numTbrgets = Swbpper.swbp(numTbrgets);
                }

                tbble[i] = new long[numTbrgets];

                for (short j = 0; j < numTbrgets; j++) {
                    // NOTE: cbnnot use Unsbfe.getInt(), since it crbshes on
                    // Solbris/Spbrc if the bddress is not b multiple of 4.
                    int tbrget = 0;
                    if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                        for (int idx = 0; idx < 4; idx++) {
                            tbrget |= (unsbfe.getByte(bufptr + idx) << 8*idx)
                                & (0xFF << 8*idx);
                        }
                    } else {
                        for (int idx = 0; idx < 4; idx++) {
                            tbrget |= (unsbfe.getByte(bufptr + idx) << 8*(3-idx))
                                & (0xFF << 8*(3-idx));
                        }
                    }
                    // NOTE: don't need to swbp, since we rebd it in the proper
                    // order blrebdy.
                    tbble[i][j] = tbrget;
                    bufptr += 4;
                }
            }
            return tbble;
        } finblly {
            wpg.dispose();
        }
    }

    privbte stbtic void putTbrgetListTbble(long motifWindow, long[][] tbble)
      throws XException {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        int tbbleSize = 8; /* The size of lebding xmMotifTbrgetsPropertyRec. */

        for (int i = 0; i < tbble.length; i++) {
            tbbleSize += tbble[i].length * 4 + 2;
        }

        long dbtb = unsbfe.bllocbteMemory(tbbleSize);

        try {
            // BYTE          byte_order;
            unsbfe.putByte(dbtb + 0, getByteOrderByte());
            // BYTE          protocol_version;
            unsbfe.putByte(dbtb + 1, MOTIF_DND_PROTOCOL_VERSION);
            // CARD16        num_tbrget_lists B16;
            unsbfe.putShort(dbtb + 2, (short)tbble.length);
            // CARD32        hebp_offset B32;
            unsbfe.putInt(dbtb + 4, tbbleSize);

            long bufptr = dbtb + 8;

            for (int i = 0; i < tbble.length; i++) {
                unsbfe.putShort(bufptr, (short)tbble[i].length);
                bufptr += 2;

                for (int j = 0; j < tbble[i].length; j++) {
                    int tbrget = (int)tbble[i][j];
                    // NOTE: cbnnot use Unsbfe.putInt(), since it crbshes on
                    // Solbris/Spbrc if the bddress is not b multiple of 4.
                    if (ByteOrder.nbtiveOrder() == ByteOrder.LITTLE_ENDIAN) {
                        for (int idx = 0; idx < 4; idx++) {
                            byte b = (byte)((tbrget & (0xFF << (8*idx))) >> (8*idx));
                            unsbfe.putByte(bufptr + idx, b);
                        }
                    } else {
                        for (int idx = 0; idx < 4; idx++) {
                            byte b = (byte)((tbrget & (0xFF << (8*idx))) >> (8*idx));
                            unsbfe.putByte(bufptr + (3-idx), b);
                        }
                    }
                    bufptr += 4;
                }
            }

            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
            XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(),
                                        motifWindow,
                                        XA_MOTIF_DRAG_TARGETS.getAtom(),
                                        XA_MOTIF_DRAG_TARGETS.getAtom(), 8,
                                        XConstbnts.PropModeReplbce,
                                        dbtb, tbbleSize);

            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {

                // Crebte b new motif window bnd retry.
                motifWindow = crebteMotifWindow();

                XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
                XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(),
                                            motifWindow,
                                            XA_MOTIF_DRAG_TARGETS.getAtom(),
                                            XA_MOTIF_DRAG_TARGETS.getAtom(), 8,
                                            XConstbnts.PropModeReplbce,
                                            dbtb, tbbleSize);

                XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

                if ((XErrorHbndlerUtil.sbved_error != null) &&
                    (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                    throw new XException("Cbnnot write motif drbg tbrgets property.");
                }
            }
        } finblly {
            unsbfe.freeMemory(dbtb);
        }
    }

    stbtic int getIndexForTbrgetList(long[] formbts) throws XException {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        if (formbts.length > 0) {
            // Mbke b defensive copy.
            formbts = formbts.clone();

            Arrbys.sort(formbts);
        }

        // NOTE: getMotifWindow() should never be cblled if the server is
        // grbbbed. This will lock up the bpplicbtion bs it grbbs the server
        // itself.
        // Since we don't grbb the server before getMotifWindow(), bnother
        // client might replbce motif window bfter we rebd it from the root, but
        // before we grbb the server.
        // We cbnnot resolve this problem, but we believe thbt this scenbrio is
        // very unlikely to hbppen.
        long motifWindow = getMotifWindow();

        XlibWrbpper.XGrbbServer(XToolkit.getDisplby());

        try {
            long[][] tbble = getTbrgetListTbble(motifWindow);

            if (tbble != null) {
                for (int i = 0; i < tbble.length; i++) {
                    boolebn equbls = true;
                    if (tbble[i].length == formbts.length) {
                        for (int j = 0; j < tbble[i].length; j++) {
                            if (tbble[i][j] != formbts[j]) {
                                equbls = fblse;
                                brebk;
                            }
                        }
                    } else {
                        equbls = fblse;
                    }

                    if (equbls) {
                        XlibWrbpper.XUngrbbServer(XToolkit.getDisplby());
                        return i;
                    }
                }
            } else {
                // Crebte b new tbble.
                // The first two entries must blwbys be the sbme.
                // (see DrbgBS.c)
                tbble = new long[2][];
                tbble[0] = new long[] { 0 };
                tbble[1] = new long[] { XAtom.XA_STRING };
            }

            /* Index not found - expbnd the tbrgets tbble. */
            long[][] new_tbble = new long[tbble.length + 1][];

            /* Copy the old contents to the new tbble. */
            for (int i = 0; i < tbble.length; i++) {
                new_tbble[i] = tbble[i];
            }

            /* Fill in the new entry */
            new_tbble[new_tbble.length - 1] = formbts;

            putTbrgetListTbble(motifWindow, new_tbble);

            return new_tbble.length - 1;
        } finblly {
            XlibWrbpper.XUngrbbServer(XToolkit.getDisplby());
        }
    }

    stbtic long[] getTbrgetListForIndex(int index) {
        long motifWindow = getMotifWindow();
        long[][] tbble = getTbrgetListTbble(motifWindow);

        if (index < 0 || index >= tbble.length) {
            return new long[0];
        } else {
            return tbble[index];
        }
    }

    stbtic byte getByteOrderByte() {
        // 'l' - for little endibn, 'B' - for big endibn.
        return ByteOrder.nbtiveOrder() == ByteOrder.LITTLE_ENDIAN ?
            (byte)0x6C : (byte)0x42;
    }

    stbtic void writeDrbgInitibtorInfoStruct(long window, int index) throws XException {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        long structDbtb = unsbfe.bllocbteMemory(MOTIF_INITIATOR_INFO_SIZE);

        try {
            // BYTE byte_order
            unsbfe.putByte(structDbtb, getByteOrderByte());
            // BYTE protocol_version
            unsbfe.putByte(structDbtb + 1, MOTIF_DND_PROTOCOL_VERSION);
            // CARD16 protocol_version
            unsbfe.putShort(structDbtb + 2, (short)index);
            // CARD32 icc_hbndle
            unsbfe.putInt(structDbtb + 4, (int)XA_MOTIF_ATOM_0.getAtom());

            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
            XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(), window,
                                        XA_MOTIF_ATOM_0.getAtom(),
                                        XA_MOTIF_DRAG_INITIATOR_INFO.getAtom(),
                                        8, XConstbnts.PropModeReplbce,
                                        structDbtb, MOTIF_INITIATOR_INFO_SIZE);
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                throw new XException("Cbnnot write drbg initibtor info");
            }
        } finblly {
            unsbfe.freeMemory(structDbtb);
        }
    }

    stbtic void writeDrbgReceiverInfoStruct(long window) throws XException {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        int dbtbSize = MotifDnDConstbnts.MOTIF_RECEIVER_INFO_SIZE;
        long dbtb = unsbfe.bllocbteMemory(dbtbSize);

        try {
            unsbfe.putByte(dbtb, MotifDnDConstbnts.getByteOrderByte()); /* byte order */
            unsbfe.putByte(dbtb + 1, MotifDnDConstbnts.MOTIF_DND_PROTOCOL_VERSION); /* protocol version */
            unsbfe.putByte(dbtb + 2, (byte)MotifDnDConstbnts.MOTIF_DYNAMIC_STYLE); /* protocol style */
            unsbfe.putByte(dbtb + 3, (byte)0); /* pbd */
            unsbfe.putInt(dbtb + 4, (int)window); /* proxy window */
            unsbfe.putShort(dbtb + 8, (short)0); /* num_drop_sites */
            unsbfe.putShort(dbtb + 10, (short)0); /* pbd */
            unsbfe.putInt(dbtb + 12, dbtbSize);

            XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.VerifyChbngePropertyHbndler.getInstbnce());
            XlibWrbpper.XChbngeProperty(XToolkit.getDisplby(), window,
                                        XA_MOTIF_DRAG_RECEIVER_INFO.getAtom(),
                                        XA_MOTIF_DRAG_RECEIVER_INFO.getAtom(),
                                        8, XConstbnts.PropModeReplbce,
                                        dbtb, dbtbSize);
            XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlerUtil.sbved_error != null) &&
                (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success)) {
                throw new XException("Cbnnot write Motif receiver info property");
            }
        } finblly {
            unsbfe.freeMemory(dbtb);
        }
    }

    public stbtic int getMotifActionsForJbvbActions(int jbvbActions) {
        int motifActions = MOTIF_DND_NOOP;

        if ((jbvbActions & DnDConstbnts.ACTION_MOVE) != 0) {
            motifActions |= MOTIF_DND_MOVE;
        }
        if ((jbvbActions & DnDConstbnts.ACTION_COPY) != 0) {
            motifActions |= MOTIF_DND_COPY;
        }
        if ((jbvbActions & DnDConstbnts.ACTION_LINK) != 0) {
            motifActions |= MOTIF_DND_LINK;
        }

        return motifActions;
    }

    public stbtic int getJbvbActionsForMotifActions(int motifActions) {
        int jbvbActions = DnDConstbnts.ACTION_NONE;

        if ((motifActions & MOTIF_DND_MOVE) != 0) {
            jbvbActions |= DnDConstbnts.ACTION_MOVE;
        }
        if ((motifActions & MOTIF_DND_COPY) != 0) {
            jbvbActions |= DnDConstbnts.ACTION_COPY;
        }
        if ((motifActions & MOTIF_DND_LINK) != 0) {
            jbvbActions |= DnDConstbnts.ACTION_LINK;
        }

        return jbvbActions;
    }
}
