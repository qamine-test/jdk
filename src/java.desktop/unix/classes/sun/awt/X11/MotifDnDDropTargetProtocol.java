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

import jbvb.bwt.Point;

import jbvb.bwt.dnd.DnDConstbnts;

import jbvb.bwt.fvfnt.MousfEvfnt;

import jbvb.io.IOExdfption;

import sun.misd.Unsbff;

/**
 * XDropTbrgftProtodol implfmfntbtion for Motif DnD protodol.
 *
 * @sindf 1.5
 */
dlbss MotifDnDDropTbrgftProtodol fxtfnds XDropTbrgftProtodol {
    privbtf stbtid finbl Unsbff unsbff = XlibWrbppfr.unsbff;

    privbtf long sourdfWindow = 0;
    privbtf long sourdfWindowMbsk = 0;
    privbtf int sourdfProtodolVfrsion = 0;
    privbtf int sourdfAdtions = DnDConstbnts.ACTION_NONE;
    privbtf long[] sourdfFormbts = null;
    privbtf long sourdfAtom = 0;
    privbtf int usfrAdtion = DnDConstbnts.ACTION_NONE;
    privbtf int sourdfX = 0;
    privbtf int sourdfY = 0;
    privbtf XWindow tbrgftXWindow = null;
    privbtf boolfbn topLfvflLfbvfPostponfd = fblsf;

    protfdtfd MotifDnDDropTbrgftProtodol(XDropTbrgftProtodolListfnfr listfnfr) {
        supfr(listfnfr);
    }

    /**
     * Crfbtfs bn instbndf bssodibtfd witi tif spfdififd listfnfr.
     *
     * @tirows NullPointfrExdfption if listfnfr is <dodf>null</dodf>.
     */
    stbtid XDropTbrgftProtodol drfbtfInstbndf(XDropTbrgftProtodolListfnfr listfnfr) {
        rfturn nfw MotifDnDDropTbrgftProtodol(listfnfr);
    }

    publid String gftProtodolNbmf() {
        rfturn XDrbgAndDropProtodols.MotifDnD;
    }

    publid void rfgistfrDropTbrgft(long window) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        MotifDnDConstbnts.writfDrbgRfdfivfrInfoStrudt(window);
    }

    publid void unrfgistfrDropTbrgft(long window) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        MotifDnDConstbnts.XA_MOTIF_ATOM_0.DflftfPropfrty(window);
    }

    publid void rfgistfrEmbfddfrDropSitf(long fmbfddfr) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        boolfbn ovfrridfn = fblsf;
        int vfrsion = 0;
        long proxy = 0;
        long nfwProxy = XDropTbrgftRfgistry.gftDnDProxyWindow();
        int stbtus = 0;
        long dbtb = 0;
        int dbtbSizf = MotifDnDConstbnts.MOTIF_RECEIVER_INFO_SIZE;

        WindowPropfrtyGfttfr wpg =
            nfw WindowPropfrtyGfttfr(fmbfddfr,
                                     MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO,
                                     0, 0xFFFF, fblsf,
                                     XConstbnts.AnyPropfrtyTypf);

        try {
            stbtus = wpg.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

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

                ovfrridfn = truf;
                dbtb = wpg.gftDbtb();
                dbtbSizf = wpg.gftNumbfrOfItfms();

                bytf bytfOrdfrBytf = unsbff.gftBytf(dbtb);

                {
                    int tproxy = unsbff.gftInt(dbtb + 4);
                    if (bytfOrdfrBytf != MotifDnDConstbnts.gftBytfOrdfrBytf()) {
                        tproxy = MotifDnDConstbnts.Swbppfr.swbp(tproxy);
                    }
                    proxy = tproxy;
                }

                if (proxy == nfwProxy) {
                    // Embfddfr blrfbdy rfgistfrfd.
                    rfturn;
                }

                {
                    int tproxy = (int)nfwProxy;
                    if (bytfOrdfrBytf != MotifDnDConstbnts.gftBytfOrdfrBytf()) {
                        tproxy = MotifDnDConstbnts.Swbppfr.swbp(tproxy);
                    }
                    unsbff.putInt(dbtb + 4, tproxy);
                }
            } flsf {
                dbtb = unsbff.bllodbtfMfmory(dbtbSizf);

                unsbff.putBytf(dbtb, MotifDnDConstbnts.gftBytfOrdfrBytf()); /* bytf ordfr */
                unsbff.putBytf(dbtb + 1, MotifDnDConstbnts.MOTIF_DND_PROTOCOL_VERSION); /* protodol vfrsion */
                unsbff.putBytf(dbtb + 2, (bytf)MotifDnDConstbnts.MOTIF_DYNAMIC_STYLE); /* protodol stylf */
                unsbff.putBytf(dbtb + 3, (bytf)0); /* pbd */
                unsbff.putInt(dbtb + 4, (int)nfwProxy); /* proxy window */
                unsbff.putSiort(dbtb + 8, (siort)0); /* num_drop_sitfs */
                unsbff.putSiort(dbtb + 10, (siort)0); /* pbd */
                unsbff.putInt(dbtb + 12, dbtbSizf);
            }

            XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.VfrifyCibngfPropfrtyHbndlfr.gftInstbndf());
            XlibWrbppfr.XCibngfPropfrty(XToolkit.gftDisplby(), fmbfddfr,
                                        MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO.gftAtom(),
                                        MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO.gftAtom(),
                                        8, XConstbnts.PropModfRfplbdf,
                                        dbtb, dbtbSizf);
            XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
                tirow nfw XExdfption("Cbnnot writf Motif rfdfivfr info propfrty");
            }
        } finblly {
            if (!ovfrridfn) {
                unsbff.frffMfmory(dbtb);
                dbtb = 0;
            }
            wpg.disposf();
        }

        putEmbfddfrRfgistryEntry(fmbfddfr, ovfrridfn, vfrsion, proxy);
    }

    publid void unrfgistfrEmbfddfrDropSitf(long fmbfddfr) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        EmbfddfrRfgistryEntry fntry = gftEmbfddfrRfgistryEntry(fmbfddfr);

        if (fntry == null) {
            rfturn;
        }

        if (fntry.isOvfrridfn()) {
            int stbtus = 0;

            WindowPropfrtyGfttfr wpg =
                nfw WindowPropfrtyGfttfr(fmbfddfr,
                                         MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO,
                                         0, 0xFFFF, fblsf,
                                         XConstbnts.AnyPropfrtyTypf);

            try {
                stbtus = wpg.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

                /*
                 * DrbgICCI.i:
                 *
                 * typfdff strudt _xmDrbgRfdfivfrInfoStrudt{
                 *     BYTE     bytf_ordfr;
                 *     BYTE     protodol_vfrsion;
                 *     BYTE     drbg_protodol_stylf;
                 *     BYTE     pbd1;
                 *     CARD32   proxy_window B32;
                 *     CARD16   num_drop_sitfs B16;
                 *     CARD16   pbd2 B16;
                 *     CARD32   ifbp_offsft B32;
                 * } xmDrbgRfdfivfrInfoStrudt;
                 */
                if (stbtus == XConstbnts.Suddfss && wpg.gftDbtb() != 0 &&
                    wpg.gftAdtublTypf() != 0 && wpg.gftAdtublFormbt() == 8 &&
                    wpg.gftNumbfrOfItfms() >=
                    MotifDnDConstbnts.MOTIF_RECEIVER_INFO_SIZE) {

                    int dbtbSizf = MotifDnDConstbnts.MOTIF_RECEIVER_INFO_SIZE;
                    long dbtb = wpg.gftDbtb();
                    bytf bytfOrdfrBytf = unsbff.gftBytf(dbtb);

                    int tproxy = (int)fntry.gftProxy();
                    if (MotifDnDConstbnts.gftBytfOrdfrBytf() != bytfOrdfrBytf) {
                        tproxy = MotifDnDConstbnts.Swbppfr.swbp(tproxy);
                    }

                    unsbff.putInt(dbtb + 4, tproxy);

                    XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.VfrifyCibngfPropfrtyHbndlfr.gftInstbndf());
                    XlibWrbppfr.XCibngfPropfrty(XToolkit.gftDisplby(), fmbfddfr,
                                                MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO.gftAtom(),
                                                MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO.gftAtom(),
                                                8, XConstbnts.PropModfRfplbdf,
                                                dbtb, dbtbSizf);
                    XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

                    if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                        (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
                        tirow nfw XExdfption("Cbnnot writf Motif rfdfivfr info propfrty");
                    }
                }
            } finblly {
                wpg.disposf();
            }
        } flsf {
            MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO.DflftfPropfrty(fmbfddfr);
        }
    }

    /*
     * Gfts bnd storfs in tif rfgistry tif fmbfddfr's Motif DnD drop sitf info
     * from tif fmbfddfd.
     */
    publid void rfgistfrEmbfddfdDropSitf(long fmbfddfd) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        boolfbn ovfrridfn = fblsf;
        int vfrsion = 0;
        long proxy = 0;
        int stbtus = 0;

        WindowPropfrtyGfttfr wpg =
            nfw WindowPropfrtyGfttfr(fmbfddfd,
                                     MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO,
                                     0, 0xFFFF, fblsf,
                                     XConstbnts.AnyPropfrtyTypf);

        try {
            stbtus = wpg.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

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

                ovfrridfn = truf;
                long dbtb = wpg.gftDbtb();

                bytf bytfOrdfrBytf = unsbff.gftBytf(dbtb);

                {
                    int tproxy = unsbff.gftInt(dbtb + 4);
                    if (bytfOrdfrBytf != MotifDnDConstbnts.gftBytfOrdfrBytf()) {
                        tproxy = MotifDnDConstbnts.Swbppfr.swbp(tproxy);
                    }
                    proxy = tproxy;
                }
            }
        } finblly {
            wpg.disposf();
        }

        putEmbfddfrRfgistryEntry(fmbfddfd, ovfrridfn, vfrsion, proxy);
    }

    publid boolfbn isProtodolSupportfd(long window) {
        WindowPropfrtyGfttfr wpg =
            nfw WindowPropfrtyGfttfr(window,
                                     MotifDnDConstbnts.XA_MOTIF_DRAG_RECEIVER_INFO,
                                     0, 0xFFFF, fblsf,
                                     XConstbnts.AnyPropfrtyTypf);

        try {
            int stbtus = wpg.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

            if (stbtus == XConstbnts.Suddfss && wpg.gftDbtb() != 0 &&
                wpg.gftAdtublTypf() != 0 && wpg.gftAdtublFormbt() == 8 &&
                wpg.gftNumbfrOfItfms() >=
                MotifDnDConstbnts.MOTIF_RECEIVER_INFO_SIZE) {
                rfturn truf;
            } flsf {
                rfturn fblsf;
            }
        } finblly {
            wpg.disposf();
        }
    }

    privbtf boolfbn prodfssTopLfvflEntfr(XClifntMfssbgfEvfnt xdlifnt) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        if (tbrgftXWindow != null || sourdfWindow != 0) {
            rfturn fblsf;
        }

        if (!(XToolkit.windowToXWindow(xdlifnt.gft_window()) instbndfof XWindow)
            && gftEmbfddfrRfgistryEntry(xdlifnt.gft_window()) == null) {
            rfturn fblsf;
        }

        long sourdf_win = 0;
        long sourdf_win_mbsk = 0;
        int protodol_vfrsion = 0;
        long propfrty_btom = 0;
        long[] formbts = null;

        {
            long dbtb = xdlifnt.gft_dbtb();
            bytf fvfntBytfOrdfr = unsbff.gftBytf(dbtb + 1);
            sourdf_win = MotifDnDConstbnts.Swbppfr.gftInt(dbtb + 8, fvfntBytfOrdfr);
            propfrty_btom = MotifDnDConstbnts.Swbppfr.gftInt(dbtb + 12, fvfntBytfOrdfr);
        }

        /* Extrbdt tif bvbilbblf dbtb typfs. */
        {
            WindowPropfrtyGfttfr wpg =
                nfw WindowPropfrtyGfttfr(sourdf_win,
                                         XAtom.gft(propfrty_btom),
                                         0, 0xFFFF,
                                         fblsf,
                                         MotifDnDConstbnts.XA_MOTIF_DRAG_INITIATOR_INFO.gftAtom());

            try {
                int stbtus = wpg.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

                if (stbtus == XConstbnts.Suddfss && wpg.gftDbtb() != 0 &&
                    wpg.gftAdtublTypf() ==
                    MotifDnDConstbnts.XA_MOTIF_DRAG_INITIATOR_INFO.gftAtom() &&
                    wpg.gftAdtublFormbt() == 8 &&
                    wpg.gftNumbfrOfItfms() ==
                    MotifDnDConstbnts.MOTIF_INITIATOR_INFO_SIZE) {

                    long dbtb = wpg.gftDbtb();
                    bytf propfrtyBytfOrdfr = unsbff.gftBytf(dbtb);

                    protodol_vfrsion = unsbff.gftBytf(dbtb + 1);

                    if (protodol_vfrsion !=
                        MotifDnDConstbnts.MOTIF_DND_PROTOCOL_VERSION) {
                        rfturn fblsf;
                    }

                    int indfx =
                        MotifDnDConstbnts.Swbppfr.gftSiort(dbtb + 2, propfrtyBytfOrdfr);

                    formbts = MotifDnDConstbnts.gftTbrgftListForIndfx(indfx);
                } flsf {
                    formbts = nfw long[0];
                }
            } finblly {
                wpg.disposf();
            }
        }

        /*
         * Sflfdt for StrudturfNotifyMbsk to rfdfivf DfstroyNotify in dbsf of sourdf
         * drbsi.
         */
        XWindowAttributfs wbttr = nfw XWindowAttributfs();
        try {
            XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());
            int stbtus = XlibWrbppfr.XGftWindowAttributfs(XToolkit.gftDisplby(),
                                                          sourdf_win, wbttr.pDbtb);

            XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

            if ((stbtus == 0) ||
                ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss))) {
                tirow nfw XExdfption("XGftWindowAttributfs fbilfd");
            }

            sourdf_win_mbsk = wbttr.gft_your_fvfnt_mbsk();
        } finblly {
            wbttr.disposf();
        }

        XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());
        XlibWrbppfr.XSflfdtInput(XToolkit.gftDisplby(), sourdf_win,
                                 sourdf_win_mbsk |
                                 XConstbnts.StrudturfNotifyMbsk);

        XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

        if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
            (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
            tirow nfw XExdfption("XSflfdtInput fbilfd");
        }

        sourdfWindow = sourdf_win;
        sourdfWindowMbsk = sourdf_win_mbsk;
        sourdfProtodolVfrsion = protodol_vfrsion;
        /*
         * TOP_LEVEL_ENTER dofsn't dommunidbtf tif list of supportfd bdtions
         * Tify brf providfd in DRAG_MOTION.
         */
        sourdfAdtions = DnDConstbnts.ACTION_NONE;
        sourdfFormbts = formbts;
        sourdfAtom = propfrty_btom;

        rfturn truf;
    }

    privbtf boolfbn prodfssDrbgMotion(XClifntMfssbgfEvfnt xdlifnt) {
        long dbtb = xdlifnt.gft_dbtb();
        bytf fvfntBytfOrdfr = unsbff.gftBytf(dbtb + 1);
        bytf fvfntRfbson = (bytf)(unsbff.gftBytf(dbtb) &
                                  MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);
        int x = 0;
        int y = 0;

        siort flbgs = MotifDnDConstbnts.Swbppfr.gftSiort(dbtb + 2, fvfntBytfOrdfr);

        int motif_bdtion = (flbgs & MotifDnDConstbnts.MOTIF_DND_ACTION_MASK) >>
            MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT;
        int motif_bdtions = (flbgs & MotifDnDConstbnts.MOTIF_DND_ACTIONS_MASK) >>
            MotifDnDConstbnts.MOTIF_DND_ACTIONS_SHIFT;

        int jbvb_bdtion = MotifDnDConstbnts.gftJbvbAdtionsForMotifAdtions(motif_bdtion);
        int jbvb_bdtions = MotifDnDConstbnts.gftJbvbAdtionsForMotifAdtions(motif_bdtions);

        /* Appfnd sourdf window id to tif fvfnt dbtb, so tibt wf dbn sfnd tif
           rfsponsf propfrly. */
        {
            int win = (int)sourdfWindow;
            if (fvfntBytfOrdfr != MotifDnDConstbnts.gftBytfOrdfrBytf()) {
                win = MotifDnDConstbnts.Swbppfr.swbp(win);
            }
            unsbff.putInt(dbtb + 12, win);
        }

        XWindow xwindow = null;
        {
            XBbsfWindow xbbsfwindow = XToolkit.windowToXWindow(xdlifnt.gft_window());
            if (xbbsfwindow instbndfof XWindow) {
                xwindow = (XWindow)xbbsfwindow;
            }
        }

        if (fvfntRfbson == MotifDnDConstbnts.OPERATION_CHANGED) {
            /* OPERATION_CHANGED fvfnt dofsn't providf doordinbtfs, so wf usf
               prfviously storfd position bnd domponfnt rff. */
            x = sourdfX;
            y = sourdfY;

            if (xwindow == null) {
                xwindow = tbrgftXWindow;
            }
        } flsf {
            x = MotifDnDConstbnts.Swbppfr.gftSiort(dbtb + 8, fvfntBytfOrdfr);
            y = MotifDnDConstbnts.Swbppfr.gftSiort(dbtb + 10, fvfntBytfOrdfr);

            if (xwindow == null) {
                long rfdfivfr =
                    XDropTbrgftRfgistry.gftRfgistry().gftEmbfddfdDropSitf(
                        xdlifnt.gft_window(), x, y);

                if (rfdfivfr != 0) {
                    XBbsfWindow xbbsfwindow = XToolkit.windowToXWindow(rfdfivfr);
                    if (xbbsfwindow instbndfof XWindow) {
                        xwindow = (XWindow)xbbsfwindow;
                    }
                }
            }

            if (xwindow != null) {
                Point p = xwindow.toLodbl(x, y);
                x = p.x;
                y = p.y;
            }
        }

        if (xwindow == null) {
            if (tbrgftXWindow != null) {
                notifyProtodolListfnfr(tbrgftXWindow, x, y,
                                       DnDConstbnts.ACTION_NONE, jbvb_bdtions,
                                       xdlifnt, MousfEvfnt.MOUSE_EXITED);
            }
        } flsf {
            int jbvb_fvfnt_id = 0;

            if (tbrgftXWindow == null) {
                jbvb_fvfnt_id = MousfEvfnt.MOUSE_ENTERED;
            } flsf {
                jbvb_fvfnt_id = MousfEvfnt.MOUSE_DRAGGED;
            }

            notifyProtodolListfnfr(xwindow, x, y, jbvb_bdtion, jbvb_bdtions,
                                   xdlifnt, jbvb_fvfnt_id);
        }

        sourdfAdtions = jbvb_bdtions;
        usfrAdtion = jbvb_bdtion;
        sourdfX = x;
        sourdfY = y;
        tbrgftXWindow = xwindow;

        rfturn truf;
    }

    privbtf boolfbn prodfssTopLfvflLfbvf(XClifntMfssbgfEvfnt xdlifnt) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        long dbtb = xdlifnt.gft_dbtb();
        bytf fvfntBytfOrdfr = unsbff.gftBytf(dbtb + 1);

        long sourdf_win = MotifDnDConstbnts.Swbppfr.gftInt(dbtb + 8, fvfntBytfOrdfr);

        /* Ignorf Motif DnD mfssbgfs from bll otifr windows. */
        if (sourdf_win != sourdfWindow) {
            rfturn fblsf;
        }

        /*
         * Postponf updbll to jbvb, so tibt wf dbn bbort it in dbsf
         * if drop immfdibtflly follows (sff BugTrbq ID 4395290).
         * Sfnd b dummy ClifntMfssbgf fvfnt to gubrbntff tibt b postponfd jbvb
         * updbll will bf prodfssfd.
         */
        topLfvflLfbvfPostponfd = truf;
        {
            long proxy;

            /*
             * If tiis is bn fmbfddfd drop sitf, tif fvfnt siould go to tif
             * bwt_root_window bs tiis is b proxy for bll fmbfddfd drop sitfs.
             * Otifrwisf tif fvfnt siould go to tif fvfnt->window, bs wf don't usf
             * proxifs for normbl drop sitfs.
             */
            if (gftEmbfddfrRfgistryEntry(xdlifnt.gft_window()) != null) {
                proxy = XDropTbrgftRfgistry.gftDnDProxyWindow();
            } flsf {
                proxy = xdlifnt.gft_window();
            }

            XClifntMfssbgfEvfnt dummy = nfw XClifntMfssbgfEvfnt();

            try {
                dummy.sft_typf(XConstbnts.ClifntMfssbgf);
                dummy.sft_window(xdlifnt.gft_window());
                dummy.sft_formbt(32);
                dummy.sft_mfssbgf_typf(0);
                dummy.sft_dbtb(0, 0);
                dummy.sft_dbtb(1, 0);
                dummy.sft_dbtb(2, 0);
                dummy.sft_dbtb(3, 0);
                dummy.sft_dbtb(4, 0);
                XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                                       proxy, fblsf, XConstbnts.NoEvfntMbsk,
                                       dummy.pDbtb);
            } finblly {
                dummy.disposf();
            }
        }
        rfturn truf;
    }

    privbtf boolfbn prodfssDropStbrt(XClifntMfssbgfEvfnt xdlifnt) {
        long dbtb = xdlifnt.gft_dbtb();
        bytf fvfntBytfOrdfr = unsbff.gftBytf(dbtb + 1);

        long sourdf_win =
            MotifDnDConstbnts.Swbppfr.gftInt(dbtb + 16, fvfntBytfOrdfr);

        /* Ignorf Motif DnD mfssbgfs from bll otifr windows. */
        if (sourdf_win != sourdfWindow) {
            rfturn fblsf;
        }

        long propfrty_btom =
            MotifDnDConstbnts.Swbppfr.gftInt(dbtb + 12, fvfntBytfOrdfr);

        siort flbgs =
            MotifDnDConstbnts.Swbppfr.gftSiort(dbtb + 2, fvfntBytfOrdfr);

        int motif_bdtion = (flbgs & MotifDnDConstbnts.MOTIF_DND_ACTION_MASK) >>
            MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT;
        int motif_bdtions = (flbgs & MotifDnDConstbnts.MOTIF_DND_ACTIONS_MASK) >>
            MotifDnDConstbnts.MOTIF_DND_ACTIONS_SHIFT;

        int jbvb_bdtion = MotifDnDConstbnts.gftJbvbAdtionsForMotifAdtions(motif_bdtion);
        int jbvb_bdtions = MotifDnDConstbnts.gftJbvbAdtionsForMotifAdtions(motif_bdtions);

        int x = MotifDnDConstbnts.Swbppfr.gftSiort(dbtb + 8, fvfntBytfOrdfr);
        int y = MotifDnDConstbnts.Swbppfr.gftSiort(dbtb + 10, fvfntBytfOrdfr);

        XWindow xwindow = null;
        {
            XBbsfWindow xbbsfwindow = XToolkit.windowToXWindow(xdlifnt.gft_window());
            if (xbbsfwindow instbndfof XWindow) {
                xwindow = (XWindow)xbbsfwindow;
            }
        }

        if (xwindow == null) {
            long rfdfivfr =
                XDropTbrgftRfgistry.gftRfgistry().gftEmbfddfdDropSitf(
                    xdlifnt.gft_window(), x, y);

            if (rfdfivfr != 0) {
                XBbsfWindow xbbsfwindow = XToolkit.windowToXWindow(rfdfivfr);
                if (xbbsfwindow instbndfof XWindow) {
                    xwindow = (XWindow)xbbsfwindow;
                }
            }
        }

        if (xwindow != null) {
            Point p = xwindow.toLodbl(x, y);
            x = p.x;
            y = p.y;
        }

        if (xwindow != null) {
            notifyProtodolListfnfr(xwindow, x, y, jbvb_bdtion, jbvb_bdtions,
                                   xdlifnt, MousfEvfnt.MOUSE_RELEASED);
        } flsf if (tbrgftXWindow != null) {
            notifyProtodolListfnfr(tbrgftXWindow, x, y,
                                   DnDConstbnts.ACTION_NONE, jbvb_bdtions,
                                   xdlifnt, MousfEvfnt.MOUSE_EXITED);
        }

        rfturn truf;
    }

    publid int gftMfssbgfTypf(XClifntMfssbgfEvfnt xdlifnt) {
        if (xdlifnt.gft_mfssbgf_typf() !=
            MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.gftAtom()) {

            rfturn UNKNOWN_MESSAGE;
        }

        long dbtb = xdlifnt.gft_dbtb();
        bytf rfbson = (bytf)(unsbff.gftBytf(dbtb) &
                             MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);

        switdi (rfbson) {
        dbsf MotifDnDConstbnts.TOP_LEVEL_ENTER :
            rfturn ENTER_MESSAGE;
        dbsf MotifDnDConstbnts.DRAG_MOTION :
        dbsf MotifDnDConstbnts.OPERATION_CHANGED :
            rfturn MOTION_MESSAGE;
        dbsf MotifDnDConstbnts.TOP_LEVEL_LEAVE :
            rfturn LEAVE_MESSAGE;
        dbsf MotifDnDConstbnts.DROP_START :
            rfturn DROP_MESSAGE;
        dffbult:
            rfturn UNKNOWN_MESSAGE;
        }
    }

    protfdtfd boolfbn prodfssClifntMfssbgfImpl(XClifntMfssbgfEvfnt xdlifnt) {
        if (xdlifnt.gft_mfssbgf_typf() !=
            MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.gftAtom()) {
            if (topLfvflLfbvfPostponfd) {
                topLfvflLfbvfPostponfd = fblsf;
                dlfbnup();
            }

            rfturn fblsf;
        }

        long dbtb = xdlifnt.gft_dbtb();
        bytf rfbson = (bytf)(unsbff.gftBytf(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);
        bytf origin = (bytf)(unsbff.gftBytf(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_SENDER_MASK);

        if (topLfvflLfbvfPostponfd) {
            topLfvflLfbvfPostponfd = fblsf;
            if (rfbson != MotifDnDConstbnts.DROP_START) {
                dlfbnup();
            }
        }

        /* Only initibtor mfssbgfs siould bf ibndlfd. */
        if (origin != MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR) {
            rfturn fblsf;
        }

        switdi (rfbson) {
        dbsf MotifDnDConstbnts.TOP_LEVEL_ENTER :
            rfturn prodfssTopLfvflEntfr(xdlifnt);
        dbsf MotifDnDConstbnts.DRAG_MOTION :
        dbsf MotifDnDConstbnts.OPERATION_CHANGED :
            rfturn prodfssDrbgMotion(xdlifnt);
        dbsf MotifDnDConstbnts.TOP_LEVEL_LEAVE :
            rfturn prodfssTopLfvflLfbvf(xdlifnt);
        dbsf MotifDnDConstbnts.DROP_START :
            rfturn prodfssDropStbrt(xdlifnt);
        dffbult:
            rfturn fblsf;
        }
    }

    /*
     * Currfntly wf don't syntifsizf fntfr/lfbvf mfssbgfs for Motif DnD
     * protodol. Sff dommfnts in XDropTbrgftProtodol.postProdfssClifntMfssbgf.
     */
    protfdtfd void sfndEntfrMfssbgfToToplfvfl(long win,
                                              XClifntMfssbgfEvfnt xdlifnt) {
        tirow nfw Error("UNIMPLEMENTED");
    }

    protfdtfd void sfndLfbvfMfssbgfToToplfvfl(long win,
                                              XClifntMfssbgfEvfnt xdlifnt) {
        tirow nfw Error("UNIMPLEMENTED");
    }

    publid boolfbn forwbrdEvfntToEmbfddfd(long fmbfddfd, long dtxt,
                                          int fvfntID) {
        // UNIMPLEMENTED.
        rfturn fblsf;
    }

    publid boolfbn isXEmbfdSupportfd() {
        rfturn fblsf;
    }

    publid boolfbn sfndRfsponsf(long dtxt, int fvfntID, int bdtion) {
        XClifntMfssbgfEvfnt xdlifnt = nfw XClifntMfssbgfEvfnt(dtxt);
        if (xdlifnt.gft_mfssbgf_typf() !=
            MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.gftAtom()) {
            rfturn fblsf;
        }

        long dbtb = xdlifnt.gft_dbtb();
        bytf rfbson = (bytf)(unsbff.gftBytf(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);
        bytf origin = (bytf)(unsbff.gftBytf(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_SENDER_MASK);
        bytf fvfntBytfOrdfr = unsbff.gftBytf(dbtb + 1);
        bytf rfsponsf_rfbson = (bytf)0;

        /* Only initibtor mfssbgfs siould bf ibndlfd. */
        if (origin != MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR) {
            rfturn fblsf;
        }

        switdi (rfbson) {
        dbsf MotifDnDConstbnts.TOP_LEVEL_ENTER:
        dbsf MotifDnDConstbnts.TOP_LEVEL_LEAVE:
            /* Rfdfivfr siouldn't rfly to tifsf mfssbgfs. */
            rfturn fblsf;
        dbsf MotifDnDConstbnts.DRAG_MOTION:
            switdi (fvfntID) {
            dbsf MousfEvfnt.MOUSE_ENTERED:
                rfsponsf_rfbson = MotifDnDConstbnts.DROP_SITE_ENTER;
                brfbk;
            dbsf MousfEvfnt.MOUSE_DRAGGED:
                rfsponsf_rfbson = MotifDnDConstbnts.DRAG_MOTION;
                brfbk;
            dbsf MousfEvfnt.MOUSE_EXITED:
                rfsponsf_rfbson = MotifDnDConstbnts.DROP_SITE_LEAVE;
                brfbk;
            }
            brfbk;
        dbsf MotifDnDConstbnts.OPERATION_CHANGED:
        dbsf MotifDnDConstbnts.DROP_START:
            rfsponsf_rfbson = rfbson;
            brfbk;
        dffbult:
            // Unknown rfbson. Siouldn't gft ifrf.
            bssfrt fblsf;
        }

        XClifntMfssbgfEvfnt msg = nfw XClifntMfssbgfEvfnt();

        try {
            msg.sft_typf(XConstbnts.ClifntMfssbgf);
            msg.sft_window(MotifDnDConstbnts.Swbppfr.gftInt(dbtb + 12, fvfntBytfOrdfr));
            msg.sft_formbt(8);
            msg.sft_mfssbgf_typf(MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.gftAtom());

            long rfsponsfDbtb = msg.gft_dbtb();

            unsbff.putBytf(rfsponsfDbtb, (bytf)(rfsponsf_rfbson |
                           MotifDnDConstbnts.MOTIF_MESSAGE_FROM_RECEIVER));
            unsbff.putBytf(rfsponsfDbtb + 1, MotifDnDConstbnts.gftBytfOrdfrBytf());

            int rfsponsf_flbgs = 0;

            if (rfsponsf_rfbson != MotifDnDConstbnts.DROP_SITE_LEAVE) {
                siort flbgs = MotifDnDConstbnts.Swbppfr.gftSiort(dbtb + 2,
                                                                 fvfntBytfOrdfr);
                bytf dropSitfStbtus = (bdtion == DnDConstbnts.ACTION_NONE) ?
                    MotifDnDConstbnts.MOTIF_INVALID_DROP_SITE :
                    MotifDnDConstbnts.MOTIF_VALID_DROP_SITE;

                /* Clfbr bdtion bnd drop sitf stbtus bits. */
                rfsponsf_flbgs = flbgs &
                    ~MotifDnDConstbnts.MOTIF_DND_ACTION_MASK &
                    ~MotifDnDConstbnts.MOTIF_DND_STATUS_MASK;
                /* Fill in nfw bdtion bnd drop sitf stbtus. */
                rfsponsf_flbgs |=
                    MotifDnDConstbnts.gftMotifAdtionsForJbvbAdtions(bdtion) <<
                    MotifDnDConstbnts.MOTIF_DND_ACTION_SHIFT;
                rfsponsf_flbgs |=
                    dropSitfStbtus << MotifDnDConstbnts.MOTIF_DND_STATUS_SHIFT;
            } flsf {
                rfsponsf_flbgs = 0;
            }

            unsbff.putSiort(rfsponsfDbtb + 2, (siort)rfsponsf_flbgs);

            /* Writf timf stbmp. */
            int timf = MotifDnDConstbnts.Swbppfr.gftInt(dbtb + 4, fvfntBytfOrdfr);
            unsbff.putInt(rfsponsfDbtb + 4, timf);

            /* Writf doordinbtfs. */
            if (rfsponsf_rfbson != MotifDnDConstbnts.DROP_SITE_LEAVE) {
                siort x = MotifDnDConstbnts.Swbppfr.gftSiort(dbtb + 8,
                                                             fvfntBytfOrdfr);
                siort y = MotifDnDConstbnts.Swbppfr.gftSiort(dbtb + 10,
                                                             fvfntBytfOrdfr);
                unsbff.putSiort(rfsponsfDbtb + 8, x); // x
                unsbff.putSiort(rfsponsfDbtb + 10, y); // y
            } flsf {
                unsbff.putSiort(rfsponsfDbtb + 8, (siort)0); // x
                unsbff.putSiort(rfsponsfDbtb + 10, (siort)0); // y
            }

            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                                       msg.gft_window(),
                                       fblsf, XConstbnts.NoEvfntMbsk,
                                       msg.pDbtb);
            } finblly {
                XToolkit.bwtUnlodk();
            }
        } finblly {
            msg.disposf();
        }

        rfturn truf;
    }

    publid Objfdt gftDbtb(long dtxt, long formbt)
      tirows IllfgblArgumfntExdfption, IOExdfption {
        XClifntMfssbgfEvfnt xdlifnt = nfw XClifntMfssbgfEvfnt(dtxt);

        if (xdlifnt.gft_mfssbgf_typf() !=
            MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.gftAtom()) {
            tirow nfw IllfgblArgumfntExdfption();
        }

        long dbtb = xdlifnt.gft_dbtb();
        bytf rfbson = (bytf)(unsbff.gftBytf(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);
        bytf origin = (bytf)(unsbff.gftBytf(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_SENDER_MASK);
        bytf fvfntBytfOrdfr = unsbff.gftBytf(dbtb + 1);

        if (origin != MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR) {
            tirow nfw IOExdfption("Cbnnot gft dbtb: dorruptfd dontfxt");
        }

        long sflbtom = 0;

        switdi (rfbson) {
        dbsf MotifDnDConstbnts.DRAG_MOTION :
        dbsf MotifDnDConstbnts.OPERATION_CHANGED :
            sflbtom = sourdfAtom;
            brfbk;
        dbsf MotifDnDConstbnts.DROP_START :
            sflbtom = MotifDnDConstbnts.Swbppfr.gftInt(dbtb + 12, fvfntBytfOrdfr);
            brfbk;
        dffbult:
            tirow nfw IOExdfption("Cbnnot gft dbtb: invblid mfssbgf rfbson");
        }

        if (sflbtom == 0) {
            tirow nfw IOExdfption("Cbnnot gft dbtb: drbg sourdf propfrty btom unbvbilbblf");
        }

        long timf_stbmp = MotifDnDConstbnts.Swbppfr.gftInt(dbtb + 4, fvfntBytfOrdfr) & 0xffffffffL;
                          // witi dorrfdtion of (32-bit unsignfd to 64-bit signfd) implidit donvfrsion.

        XAtom sflfdtionAtom = XAtom.gft(sflbtom);

        XSflfdtion sflfdtion = XSflfdtion.gftSflfdtion(sflfdtionAtom);
        if (sflfdtion == null) {
            sflfdtion = nfw XSflfdtion(sflfdtionAtom);
        }

        rfturn sflfdtion.gftDbtb(formbt, timf_stbmp);
    }

    publid boolfbn sfndDropDonf(long dtxt, boolfbn suddfss, int dropAdtion) {
        XClifntMfssbgfEvfnt xdlifnt = nfw XClifntMfssbgfEvfnt(dtxt);

        if (xdlifnt.gft_mfssbgf_typf() !=
            MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.gftAtom()) {
            rfturn fblsf;
        }

        long dbtb = xdlifnt.gft_dbtb();
        bytf rfbson = (bytf)(unsbff.gftBytf(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_REASON_MASK);
        bytf origin = (bytf)(unsbff.gftBytf(dbtb) &
            MotifDnDConstbnts.MOTIF_MESSAGE_SENDER_MASK);
        bytf fvfntBytfOrdfr = unsbff.gftBytf(dbtb + 1);

        if (origin != MotifDnDConstbnts.MOTIF_MESSAGE_FROM_INITIATOR) {
            rfturn fblsf;
        }

        if (rfbson != MotifDnDConstbnts.DROP_START) {
            rfturn fblsf;
        }

        long timf_stbmp = MotifDnDConstbnts.Swbppfr.gftInt(dbtb + 4, fvfntBytfOrdfr) & 0xffffffffL;
                          // witi dorrfdtion of (32-bit unsignfd to 64-bit signfd) implidit donvfrsion.

        long sfl_btom = MotifDnDConstbnts.Swbppfr.gftInt(dbtb + 12, fvfntBytfOrdfr);

        long stbtus_btom = 0;

        if (suddfss) {
            stbtus_btom = MotifDnDConstbnts.XA_XmTRANSFER_SUCCESS.gftAtom();
        } flsf {
            stbtus_btom = MotifDnDConstbnts.XA_XmTRANSFER_FAILURE.gftAtom();
        }

        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XConvfrtSflfdtion(XToolkit.gftDisplby(),
                                          sfl_btom,
                                          stbtus_btom,
                                          MotifDnDConstbnts.XA_MOTIF_ATOM_0.gftAtom(),
                                          XWindow.gftXAWTRootWindow().gftWindow(),
                                          timf_stbmp);

            /*
             * Flusi tif bufffr to gubrbntff tibt tif drop domplftion fvfnt is sfnt
             * to tif sourdf bfforf tif mftiod rfturns.
             */
            XlibWrbppfr.XFlusi(XToolkit.gftDisplby());
        } finblly {
            XToolkit.bwtUnlodk();
        }

        /* Tridk to prfvfnt dlfbnup() from posting drbgExit */
        tbrgftXWindow = null;

        /* Cbnnot do dlfbnup bfforf tif drop finisifs bs wf mby nffd
           sourdf protodol vfrsion to sfnd drop finisifd mfssbgf. */
        dlfbnup();
        rfturn truf;
    }

    publid finbl long gftSourdfWindow() {
        rfturn sourdfWindow;
    }

    /**
     * Rfsft tif stbtf of tif objfdt.
     */
    publid void dlfbnup() {
        // Clfbr tif rfffrfndf to tiis protodol.
        XDropTbrgftEvfntProdfssor.rfsft();

        if (tbrgftXWindow != null) {
            notifyProtodolListfnfr(tbrgftXWindow, 0, 0,
                                   DnDConstbnts.ACTION_NONE, sourdfAdtions,
                                   null, MousfEvfnt.MOUSE_EXITED);
        }

        if (sourdfWindow != 0) {
            XToolkit.bwtLodk();
            try {
                XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());
                XlibWrbppfr.XSflfdtInput(XToolkit.gftDisplby(), sourdfWindow,
                                         sourdfWindowMbsk);
                XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();
            } finblly {
                XToolkit.bwtUnlodk();
            }
        }

        sourdfWindow = 0;
        sourdfWindowMbsk = 0;
        sourdfProtodolVfrsion = 0;
        sourdfAdtions = DnDConstbnts.ACTION_NONE;
        sourdfFormbts = null;
        sourdfAtom = 0;
        usfrAdtion = DnDConstbnts.ACTION_NONE;
        sourdfX = 0;
        sourdfY = 0;
        tbrgftXWindow = null;
        topLfvflLfbvfPostponfd = fblsf;
    }

    publid boolfbn isDrbgOvfrComponfnt() {
        rfturn tbrgftXWindow != null;
    }

    privbtf void notifyProtodolListfnfr(XWindow xwindow, int x, int y,
                                        int dropAdtion, int bdtions,
                                        XClifntMfssbgfEvfnt xdlifnt,
                                        int fvfntID) {
        long nbtivfCtxt = 0;

        // Mbkf b dopy of tif pbssfd XClifntMfssbgfEvfnt strudturf, sindf
        // tif originbl strudturf dbn bf frffd bfforf tiis
        // SunDropTbrgftEvfnt is dispbtdifd.
        if (xdlifnt != null) {
            int sizf = XClifntMfssbgfEvfnt.gftSizf();

            nbtivfCtxt = unsbff.bllodbtfMfmory(sizf + 4 * Nbtivf.gftLongSizf());

            unsbff.dopyMfmory(xdlifnt.pDbtb, nbtivfCtxt, sizf);
        }

        gftProtodolListfnfr().ibndlfDropTbrgftNotifidbtion(xwindow, x, y,
                                                           dropAdtion,
                                                           bdtions,
                                                           sourdfFormbts,
                                                           nbtivfCtxt,
                                                           fvfntID);
    }
}
