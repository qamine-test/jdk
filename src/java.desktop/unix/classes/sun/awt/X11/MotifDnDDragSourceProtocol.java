/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.bwt.X11;

import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;
import jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor;

import jbvb.bwt.dnd.DnDConstbnts;
import jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption;

import jbvb.util.Mbp;

import sun.misd.Unsbff;

/**
 * XDrbgSourdfProtodol implfmfntbtion for Motif DnD protodol.
 *
 * @sindf 1.5
 */
dlbss MotifDnDDrbgSourdfProtodol fxtfnds XDrbgSourdfProtodol
    implfmfnts XEvfntDispbtdifr {

    privbtf stbtid finbl Unsbff unsbff = XlibWrbppfr.unsbff;

    privbtf long tbrgftEntfrSfrvfrTimf = XConstbnts.CurrfntTimf;

    protfdtfd MotifDnDDrbgSourdfProtodol(XDrbgSourdfProtodolListfnfr listfnfr) {
        supfr(listfnfr);
        XToolkit.bddEvfntDispbtdifr(XWindow.gftXAWTRootWindow().gftWindow(), tiis);
    }

    /**
     * Crfbtfs bn instbndf bssodibtfd witi tif spfdififd listfnfr.
     *
     * @tirows NullPointfrExdfption if listfnfr is <dodf>null</dodf>.
     */
    stbtid XDrbgSourdfProtodol drfbtfInstbndf(XDrbgSourdfProtodolListfnfr listfnfr) {
        rfturn nfw MotifDnDDrbgSourdfProtodol(listfnfr);
    }

    publid String gftProtodolNbmf() {
        rfturn XDrbgAndDropProtodols.MotifDnD;
    }

    protfdtfd void initiblizfDrbgImpl(int bdtions, Trbnsffrbblf dontfnts,
                                      Mbp<Long, DbtbFlbvor> formbtMbp, long[] formbts)
      tirows InvblidDnDOpfrbtionExdfption,
        IllfgblArgumfntExdfption, XExdfption {

        long window = XDrbgSourdfProtodol.gftDrbgSourdfWindow();

        /* Writf tif Motif DnD initibtor info on tif root XWindow. */
        try {
            int indfx = MotifDnDConstbnts.gftIndfxForTbrgftList(formbts);

            MotifDnDConstbnts.writfDrbgInitibtorInfoStrudt(window, indfx);
        } dbtdi (XExdfption xf) {
            dlfbnup();
            tirow xf;
        } dbtdi (InvblidDnDOpfrbtionExdfption idof) {
            dlfbnup();
            tirow idof;
        }

        if (!MotifDnDConstbnts.MotifDnDSflfdtion.sftOwnfr(dontfnts, formbtMbp,
                                                          formbts,
                                                          XConstbnts.CurrfntTimf)) {
            dlfbnup();
            tirow nfw InvblidDnDOpfrbtionExdfption("Cbnnot bdquirf sflfdtion ownfrsiip");
        }
    }

    /**
     * Prodfssfs tif spfdififd dlifnt mfssbgf fvfnt.
     *
     * @rfturns truf if tif fvfnt wbs suddfssfully prodfssfd.
     */
    publid boolfbn prodfssClifntMfssbgf(XClifntMfssbgfEvfnt xdlifnt) {
        if (xdlifnt.gft_mfssbgf_typf() !=
            MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.gftAtom()) {
            rfturn fblsf;
        }

        long dbtb = xdlifnt.gft_dbtb();
        bytf rfbson = (bytf)(unsbff.gftBytf(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);
        bytf origin = (bytf)(unsbff.gftBytf(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_SENDER_MASK);
        bytf bytfOrdfr = unsbff.gftBytf(dbtb + 1);
        boolfbn swbpNffdfd = bytfOrdfr != MotifDnDConstbnts.gftBytfOrdfrBytf();
        int bdtion = DnDConstbnts.ACTION_NONE;
        int x = 0;
        int y = 0;

        /* Only rfdfivfr mfssbgfs siould bf ibndlfd. */
        if (origin != MotifDnDConstbnts.MOTIF_MESSAGE_FROM_RECEIVER) {
            rfturn fblsf;
        }

        switdi (rfbson) {
        dbsf MotifDnDConstbnts.DROP_SITE_ENTER:
        dbsf MotifDnDConstbnts.DROP_SITE_LEAVE:
        dbsf MotifDnDConstbnts.DRAG_MOTION:
        dbsf MotifDnDConstbnts.OPERATION_CHANGED:
            brfbk;
        dffbult:
            // Unknown rfbson.
            rfturn fblsf;
        }

        int t = unsbff.gftInt(dbtb + 4);
        if (swbpNffdfd) {
            t = MotifDnDConstbnts.Swbppfr.swbp(t);
        }
        long timf = t & 0xffffffffL;
             // witi dorrfdtion of (32-bit unsignfd to 64-bit signfd) implidit donvfrsion.

        /* Disdbrd fvfnts from tif prfvious rfdfivfr. */
        if (tbrgftEntfrSfrvfrTimf == XConstbnts.CurrfntTimf ||
            timf < tbrgftEntfrSfrvfrTimf) {
            rfturn truf;
        }

        if (rfbson != MotifDnDConstbnts.DROP_SITE_LEAVE) {
            siort flbgs = unsbff.gftSiort(dbtb + 2);
            if (swbpNffdfd) {
                flbgs = MotifDnDConstbnts.Swbppfr.swbp(flbgs);
            }

            bytf stbtus = (bytf)((flbgs & MotifDnDConstbnts.MOTIF_DND_STATUS_MASK) >>
                MotifDnDConstbnts.MOTIF_DND_STATUS_SHIFT);
            bytf motif_bdtion = (bytf)((flbgs & MotifDnDConstbnts.MOTIF_DND_ACTION_MASK) >>
                MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT);

            if (stbtus == MotifDnDConstbnts.MOTIF_VALID_DROP_SITE) {
                bdtion = MotifDnDConstbnts.gftJbvbAdtionsForMotifAdtions(motif_bdtion);
            } flsf {
                bdtion = DnDConstbnts.ACTION_NONE;
            }

            siort tx = unsbff.gftSiort(dbtb + 8);
            siort ty = unsbff.gftSiort(dbtb + 10);
            if (swbpNffdfd) {
                tx = MotifDnDConstbnts.Swbppfr.swbp(tx);
                ty = MotifDnDConstbnts.Swbppfr.swbp(ty);
            }
            x = tx;
            y = ty;
        }

        gftProtodolListfnfr().ibndlfDrbgRfply(bdtion, x, y);

        rfturn truf;
    }

    publid TbrgftWindowInfo gftTbrgftWindowInfo(long window) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        WindowPropfrtyGfttfr wpg =
            nfw WindowPropfrtyGfttfr(window,
                                     MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO,
                                     0, 0xFFFF, fblsf,
                                     XConstbnts.AnyPropfrtyTypf);

        try {
            int stbtus = wpg.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

            /*
             * DrbgICCI.i:
             *
             * typfdff strudt _xmDrbgRfdfivfrInfoStrudt{
             *     BYTE bytf_ordfr;
             *     BYTE protodol_vfrsion;
             *     BYTE drbg_protodol_stylf;
             *     BYTE pbd1;
             *     CARD32       proxy_window B32;
             *     CARD16       num_drop_sitfs B16;
             *     CARD16       pbd2 B16;
             *     CARD32       ifbp_offsft B32;
             * } xmDrbgRfdfivfrInfoStrudt;
             */
            if (stbtus == XConstbnts.Suddfss && wpg.gftDbtb() != 0 &&
                wpg.gftAdtublTypf() != 0 && wpg.gftAdtublFormbt() == 8 &&
                wpg.gftNumbfrOfItfms() >=
                MotifDnDConstbnts.MOTIF_RECEIVER_INFO_SIZE) {

                long dbtb = wpg.gftDbtb();
                bytf bytfOrdfrBytf = unsbff.gftBytf(dbtb);
                bytf drbgProtodolStylf = unsbff.gftBytf(dbtb + 2);
                switdi (drbgProtodolStylf) {
                dbsf MotifDnDConstbnts.MOTIF_PREFER_PREREGISTER_STYLE :
                dbsf MotifDnDConstbnts.MOTIF_PREFER_DYNAMIC_STYLE :
                dbsf MotifDnDConstbnts.MOTIF_DYNAMIC_STYLE :
                dbsf MotifDnDConstbnts.MOTIF_PREFER_RECEIVER_STYLE :
                    int proxy = unsbff.gftInt(dbtb + 4);
                    if (bytfOrdfrBytf != MotifDnDConstbnts.gftBytfOrdfrBytf()) {
                        proxy = MotifDnDConstbnts.Swbppfr.swbp(proxy);
                    }

                    int protodolVfrsion = unsbff.gftBytf(dbtb + 1);

                    rfturn nfw TbrgftWindowInfo(proxy, protodolVfrsion);
                dffbult:
                    // Unsupportfd protodol stylf.
                    rfturn null;
                }
            } flsf {
                rfturn null;
            }
        } finblly {
            wpg.disposf();
        }
    }

    publid void sfndEntfrMfssbgf(long[] formbts,
                                 int sourdfAdtion, int sourdfAdtions, long timf) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();
        bssfrt gftTbrgftWindow() != 0;
        bssfrt formbts != null;

        tbrgftEntfrSfrvfrTimf = timf;

        XClifntMfssbgfEvfnt msg = nfw XClifntMfssbgfEvfnt();
        try {
            msg.sft_typf(XConstbnts.ClifntMfssbgf);
            msg.sft_window(gftTbrgftWindow());
            msg.sft_formbt(8);
            msg.sft_mfssbgf_typf(MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.gftAtom());

            long dbtb = msg.gft_dbtb();
            int flbgs =
                (MotifDnDConstbnts.gftMotifAdtionsForJbvbAdtions(sourdfAdtion) <<
                 MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT) |
                (MotifDnDConstbnts.gftMotifAdtionsForJbvbAdtions(sourdfAdtions) <<
                 MotifDnDConstbnts.MOTIF_DND_ACTIONS_SHIFT);

            unsbff.putBytf(dbtb,
                           (bytf)(MotifDnDConstbnts.TOP_LEVEL_ENTER |
                                  MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR));
            unsbff.putBytf(dbtb + 1,
                           MotifDnDConstbnts.gftBytfOrdfrBytf());
            unsbff.putSiort(dbtb + 2, (siort)flbgs);
            unsbff.putInt(dbtb + 4, (int)timf);
            unsbff.putInt(dbtb + 8, (int)XDrbgSourdfProtodol.gftDrbgSourdfWindow());
            unsbff.putInt(dbtb + 12, (int)MotifDnDConstbnts.XA_MOTIF_ATOM_0.gftAtom());

            XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                                   gftTbrgftProxyWindow(),
                                   fblsf, XConstbnts.NoEvfntMbsk,
                                   msg.pDbtb);
        } finblly {
            msg.disposf();
        }
    }

    publid void sfndMovfMfssbgf(int xRoot, int yRoot,
                                int sourdfAdtion, int sourdfAdtions, long timf) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();
        bssfrt gftTbrgftWindow() != 0;

        XClifntMfssbgfEvfnt msg = nfw XClifntMfssbgfEvfnt();
        try {
            msg.sft_typf(XConstbnts.ClifntMfssbgf);
            msg.sft_window(gftTbrgftWindow());
            msg.sft_formbt(8);
            msg.sft_mfssbgf_typf(MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.gftAtom());

            long dbtb = msg.gft_dbtb();
            int flbgs =
                (MotifDnDConstbnts.gftMotifAdtionsForJbvbAdtions(sourdfAdtion) <<
                 MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT) |
                (MotifDnDConstbnts.gftMotifAdtionsForJbvbAdtions(sourdfAdtions) <<
                 MotifDnDConstbnts.MOTIF_DND_ACTIONS_SHIFT);

            unsbff.putBytf(dbtb,
                           (bytf)(MotifDnDConstbnts.DRAG_MOTION |
                                  MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR));
            unsbff.putBytf(dbtb + 1,
                           MotifDnDConstbnts.gftBytfOrdfrBytf());
            unsbff.putSiort(dbtb + 2, (siort)flbgs);
            unsbff.putInt(dbtb + 4, (int)timf);
            unsbff.putSiort(dbtb + 8, (siort)xRoot);
            unsbff.putSiort(dbtb + 10, (siort)yRoot);

            XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                                   gftTbrgftProxyWindow(),
                                   fblsf, XConstbnts.NoEvfntMbsk,
                                   msg.pDbtb);
        } finblly {
            msg.disposf();
        }
    }

    publid void sfndLfbvfMfssbgf(long timf) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();
        bssfrt gftTbrgftWindow() != 0;

        XClifntMfssbgfEvfnt msg = nfw XClifntMfssbgfEvfnt();
        try {
            msg.sft_typf(XConstbnts.ClifntMfssbgf);
            msg.sft_window(gftTbrgftWindow());
            msg.sft_formbt(8);
            msg.sft_mfssbgf_typf(MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.gftAtom());

            long dbtb = msg.gft_dbtb();

            unsbff.putBytf(dbtb,
                           (bytf)(MotifDnDConstbnts.TOP_LEVEL_LEAVE |
                                  MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR));
            unsbff.putBytf(dbtb + 1,
                           MotifDnDConstbnts.gftBytfOrdfrBytf());
            unsbff.putSiort(dbtb + 2, (siort)0);
            unsbff.putInt(dbtb + 4, (int)timf);
            unsbff.putInt(dbtb + 8, (int)XDrbgSourdfProtodol.gftDrbgSourdfWindow());

            XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                                   gftTbrgftProxyWindow(),
                                   fblsf, XConstbnts.NoEvfntMbsk,
                                   msg.pDbtb);
        } finblly {
            msg.disposf();
        }
    }

    protfdtfd void sfndDropMfssbgf(int xRoot, int yRoot,
                                   int sourdfAdtion, int sourdfAdtions,
                                   long timf) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();
        bssfrt gftTbrgftWindow() != 0;

        /*
         * Motif drop sitfs fxpfdt TOP_LEVEL_LEAVE bfforf DROP_START.
         */
        sfndLfbvfMfssbgf(timf);

        XClifntMfssbgfEvfnt msg = nfw XClifntMfssbgfEvfnt();
        try {
            msg.sft_typf(XConstbnts.ClifntMfssbgf);
            msg.sft_window(gftTbrgftWindow());
            msg.sft_formbt(8);
            msg.sft_mfssbgf_typf(MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.gftAtom());

            long dbtb = msg.gft_dbtb();
            int flbgs =
                (MotifDnDConstbnts.gftMotifAdtionsForJbvbAdtions(sourdfAdtion) <<
                 MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT) |
                (MotifDnDConstbnts.gftMotifAdtionsForJbvbAdtions(sourdfAdtions) <<
                 MotifDnDConstbnts.MOTIF_DND_ACTIONS_SHIFT);

            unsbff.putBytf(dbtb,
                           (bytf)(MotifDnDConstbnts.DROP_START |
                                  MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR));
            unsbff.putBytf(dbtb + 1,
                           MotifDnDConstbnts.gftBytfOrdfrBytf());
            unsbff.putSiort(dbtb + 2, (siort)flbgs);
            unsbff.putInt(dbtb + 4, (int)timf);
            unsbff.putSiort(dbtb + 8, (siort)xRoot);
            unsbff.putSiort(dbtb + 10, (siort)yRoot);
            unsbff.putInt(dbtb + 12, (int)MotifDnDConstbnts.XA_MOTIF_ATOM_0.gftAtom());
            unsbff.putInt(dbtb + 16, (int)XDrbgSourdfProtodol.gftDrbgSourdfWindow());

            XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                                   gftTbrgftProxyWindow(),
                                   fblsf, XConstbnts.NoEvfntMbsk,
                                   msg.pDbtb);
        } finblly {
            msg.disposf();
        }
    }

    publid boolfbn prodfssProxyModfEvfnt(XClifntMfssbgfEvfnt xdlifnt,
                                         long sourdfWindow) {
        // Motif DnD for XEmbfd is not implfmfntfd.
        rfturn fblsf;
    }

    publid void dlfbnupTbrgftInfo() {
        supfr.dlfbnupTbrgftInfo();
        tbrgftEntfrSfrvfrTimf = XConstbnts.CurrfntTimf;
    }

    publid void dispbtdiEvfnt(XEvfnt fv) {
        switdi (fv.gft_typf()) {
        dbsf XConstbnts.SflfdtionRfqufst:
            XSflfdtionRfqufstEvfnt xsrf = fv.gft_xsflfdtionrfqufst();
            long btom = xsrf.gft_sflfdtion();

            if (btom == MotifDnDConstbnts.XA_MOTIF_ATOM_0.gftAtom()) {
                long tbrgft = xsrf.gft_tbrgft();
                if (tbrgft == MotifDnDConstbnts.XA_XmTRANSFER_SUCCESS.gftAtom()) {
                    gftProtodolListfnfr().ibndlfDrbgFinisifd(truf);
                } flsf if (tbrgft == MotifDnDConstbnts.XA_XmTRANSFER_FAILURE.gftAtom()) {
                    gftProtodolListfnfr().ibndlfDrbgFinisifd(fblsf);
                }
            }
            brfbk;
        }
    }
}
