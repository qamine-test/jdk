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

import sun.util.logging.PlbtformLoggfr;

import sun.misd.Unsbff;

/**
 * XDropTbrgftProtodol implfmfntbtion for XDnD protodol.
 *
 * @sindf 1.5
 */
dlbss XDnDDropTbrgftProtodol fxtfnds XDropTbrgftProtodol {
    privbtf stbtid finbl PlbtformLoggfr loggfr =
        PlbtformLoggfr.gftLoggfr("sun.bwt.X11.xfmbfd.xdnd.XDnDDropTbrgftProtodol");

    privbtf stbtid finbl Unsbff unsbff = XlibWrbppfr.unsbff;

    privbtf long sourdfWindow = 0;
    privbtf long sourdfWindowMbsk = 0;
    privbtf int sourdfProtodolVfrsion = 0;
    privbtf int sourdfAdtions = DnDConstbnts.ACTION_NONE;
    privbtf long[] sourdfFormbts = null;
    privbtf boolfbn trbdkSourdfAdtions = fblsf;
    privbtf int usfrAdtion = DnDConstbnts.ACTION_NONE;
    privbtf int sourdfX = 0;
    privbtf int sourdfY = 0;
    privbtf XWindow tbrgftXWindow = null;

    // XEmbfd stuff.
    privbtf long prfvCtxt = 0;
    privbtf boolfbn ovfrXEmbfdClifnt = fblsf;

    protfdtfd XDnDDropTbrgftProtodol(XDropTbrgftProtodolListfnfr listfnfr) {
        supfr(listfnfr);
    }

    /**
     * Crfbtfs bn instbndf bssodibtfd witi tif spfdififd listfnfr.
     *
     * @tirows NullPointfrExdfption if listfnfr is <dodf>null</dodf>.
     */
    stbtid XDropTbrgftProtodol drfbtfInstbndf(XDropTbrgftProtodolListfnfr listfnfr) {
        rfturn nfw XDnDDropTbrgftProtodol(listfnfr);
    }

    publid String gftProtodolNbmf() {
        rfturn XDrbgAndDropProtodols.XDnD;
    }

    publid void rfgistfrDropTbrgft(long window) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        long dbtb = Nbtivf.bllodbtfLongArrby(1);

        try {
            Nbtivf.putLong(dbtb, 0, XDnDConstbnts.XDND_PROTOCOL_VERSION);

            XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.VfrifyCibngfPropfrtyHbndlfr.gftInstbndf());
            XDnDConstbnts.XA_XdndAwbrf.sftAtomDbtb(window, XAtom.XA_ATOM, dbtb, 1);
            XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
                tirow nfw XExdfption("Cbnnot writf XdndAwbrf propfrty");
            }
        } finblly {
            unsbff.frffMfmory(dbtb);
            dbtb = 0;
        }
    }

    publid void unrfgistfrDropTbrgft(long window) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        XDnDConstbnts.XA_XdndAwbrf.DflftfPropfrty(window);
    }

    publid void rfgistfrEmbfddfrDropSitf(long fmbfddfr) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        boolfbn ovfrridfn = fblsf;
        int vfrsion = 0;
        long proxy = 0;
        long nfwProxy = XDropTbrgftRfgistry.gftDnDProxyWindow();
        int stbtus = 0;

        WindowPropfrtyGfttfr wpg1 =
            nfw WindowPropfrtyGfttfr(fmbfddfr, XDnDConstbnts.XA_XdndAwbrf, 0, 1,
                                     fblsf, XConstbnts.AnyPropfrtyTypf);

        try {
            stbtus = wpg1.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

            if (stbtus == XConstbnts.Suddfss &&
                wpg1.gftDbtb() != 0 && wpg1.gftAdtublTypf() == XAtom.XA_ATOM) {

                ovfrridfn = truf;
                vfrsion = (int)Nbtivf.gftLong(wpg1.gftDbtb());
            }
        } finblly {
            wpg1.disposf();
        }

        /* XdndProxy is not supportfd for prior to XDnD vfrsion 4 */
        if (ovfrridfn && vfrsion >= 4) {
            WindowPropfrtyGfttfr wpg2 =
                nfw WindowPropfrtyGfttfr(fmbfddfr, XDnDConstbnts.XA_XdndProxy,
                                         0, 1, fblsf, XAtom.XA_WINDOW);

            try {
                stbtus = wpg2.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

                if (stbtus == XConstbnts.Suddfss &&
                    wpg2.gftDbtb() != 0 &&
                    wpg2.gftAdtublTypf() == XAtom.XA_WINDOW) {

                    proxy = Nbtivf.gftLong(wpg2.gftDbtb());
                }
            } finblly {
                wpg2.disposf();
            }

            if (proxy != 0) {
                WindowPropfrtyGfttfr wpg3 =
                    nfw WindowPropfrtyGfttfr(proxy, XDnDConstbnts.XA_XdndProxy,
                                             0, 1, fblsf, XAtom.XA_WINDOW);

                try {
                    stbtus = wpg3.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

                    if (stbtus != XConstbnts.Suddfss ||
                        wpg3.gftDbtb() == 0 ||
                        wpg3.gftAdtublTypf() != XAtom.XA_WINDOW ||
                        Nbtivf.gftLong(wpg3.gftDbtb()) != proxy) {

                        proxy = 0;
                    } flsf {
                        WindowPropfrtyGfttfr wpg4 =
                            nfw WindowPropfrtyGfttfr(proxy,
                                                     XDnDConstbnts.XA_XdndAwbrf,
                                                     0, 1, fblsf,
                                                     XConstbnts.AnyPropfrtyTypf);

                        try {
                            stbtus = wpg4.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

                            if (stbtus != XConstbnts.Suddfss ||
                                wpg4.gftDbtb() == 0 ||
                                wpg4.gftAdtublTypf() != XAtom.XA_ATOM) {

                                proxy = 0;
                            }
                        } finblly {
                            wpg4.disposf();
                        }
                    }
                } finblly {
                    wpg3.disposf();
                }
            }
        }

        if (proxy == nfwProxy) {
            // Embfddfr blrfbdy rfgistfrfd.
            rfturn;
        }

        long dbtb = Nbtivf.bllodbtfLongArrby(1);

        try {
            Nbtivf.putLong(dbtb, 0, XDnDConstbnts.XDND_PROTOCOL_VERSION);

            /* Tif proxy window must ibvf tif XdndAwbrf sft, bs XDnD protodol
               prfsdribfs to difdk tif proxy window for XdndAwbrf. */
            XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.VfrifyCibngfPropfrtyHbndlfr.gftInstbndf());
            XDnDConstbnts.XA_XdndAwbrf.sftAtomDbtb(nfwProxy, XAtom.XA_ATOM,
                                                   dbtb, 1);
            XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
                tirow nfw XExdfption("Cbnnot writf XdndAwbrf propfrty");
            }

            Nbtivf.putLong(dbtb, 0, nfwProxy);

            /* Tif proxy window must ibvf tif XdndProxy sft to point to itsflf.*/
            XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.VfrifyCibngfPropfrtyHbndlfr.gftInstbndf());
            XDnDConstbnts.XA_XdndProxy.sftAtomDbtb(nfwProxy, XAtom.XA_WINDOW,
                                                   dbtb, 1);
            XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
                tirow nfw XExdfption("Cbnnot writf XdndProxy propfrty");
            }

            Nbtivf.putLong(dbtb, 0, XDnDConstbnts.XDND_PROTOCOL_VERSION);

            XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.VfrifyCibngfPropfrtyHbndlfr.gftInstbndf());
            XDnDConstbnts.XA_XdndAwbrf.sftAtomDbtb(fmbfddfr, XAtom.XA_ATOM,
                                                   dbtb, 1);
            XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
                tirow nfw XExdfption("Cbnnot writf XdndAwbrf propfrty");
            }

            Nbtivf.putLong(dbtb, 0, nfwProxy);

            XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.VfrifyCibngfPropfrtyHbndlfr.gftInstbndf());
            XDnDConstbnts.XA_XdndProxy.sftAtomDbtb(fmbfddfr, XAtom.XA_WINDOW,
                                                   dbtb, 1);
            XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
                tirow nfw XExdfption("Cbnnot writf XdndProxy propfrty");
            }
        } finblly {
            unsbff.frffMfmory(dbtb);
            dbtb = 0;
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
            long dbtb = Nbtivf.bllodbtfLongArrby(1);

            try {
                Nbtivf.putLong(dbtb, 0, fntry.gftVfrsion());

                XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.VfrifyCibngfPropfrtyHbndlfr.gftInstbndf());
                XDnDConstbnts.XA_XdndAwbrf.sftAtomDbtb(fmbfddfr, XAtom.XA_ATOM,
                                                       dbtb, 1);
                XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

                if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                    (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
                    tirow nfw XExdfption("Cbnnot writf XdndAwbrf propfrty");
                }

                Nbtivf.putLong(dbtb, 0, (int)fntry.gftProxy());

                XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.VfrifyCibngfPropfrtyHbndlfr.gftInstbndf());
                XDnDConstbnts.XA_XdndProxy.sftAtomDbtb(fmbfddfr, XAtom.XA_WINDOW,
                                                       dbtb, 1);
                XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

                if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                    (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
                    tirow nfw XExdfption("Cbnnot writf XdndProxy propfrty");
                }
            } finblly {
                unsbff.frffMfmory(dbtb);
                dbtb = 0;
            }
        } flsf {
            XDnDConstbnts.XA_XdndAwbrf.DflftfPropfrty(fmbfddfr);
            XDnDConstbnts.XA_XdndProxy.DflftfPropfrty(fmbfddfr);
        }
    }

    /*
     * Gfts bnd storfs in tif rfgistry tif fmbfddfr's XDnD drop sitf info
     * from tif fmbfddfd.
     */
    publid void rfgistfrEmbfddfdDropSitf(long fmbfddfd) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        boolfbn ovfrridfn = fblsf;
        int vfrsion = 0;
        long proxy = 0;
        long nfwProxy = XDropTbrgftRfgistry.gftDnDProxyWindow();
        int stbtus = 0;

        WindowPropfrtyGfttfr wpg1 =
            nfw WindowPropfrtyGfttfr(fmbfddfd, XDnDConstbnts.XA_XdndAwbrf, 0, 1,
                                     fblsf, XConstbnts.AnyPropfrtyTypf);

        try {
            stbtus = wpg1.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

            if (stbtus == XConstbnts.Suddfss &&
                wpg1.gftDbtb() != 0 && wpg1.gftAdtublTypf() == XAtom.XA_ATOM) {

                ovfrridfn = truf;
                vfrsion = (int)Nbtivf.gftLong(wpg1.gftDbtb());
            }
        } finblly {
            wpg1.disposf();
        }

        /* XdndProxy is not supportfd for prior to XDnD vfrsion 4 */
        if (ovfrridfn && vfrsion >= 4) {
            WindowPropfrtyGfttfr wpg2 =
                nfw WindowPropfrtyGfttfr(fmbfddfd, XDnDConstbnts.XA_XdndProxy,
                                         0, 1, fblsf, XAtom.XA_WINDOW);

            try {
                stbtus = wpg2.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

                if (stbtus == XConstbnts.Suddfss &&
                    wpg2.gftDbtb() != 0 &&
                    wpg2.gftAdtublTypf() == XAtom.XA_WINDOW) {

                    proxy = Nbtivf.gftLong(wpg2.gftDbtb());
                }
            } finblly {
                wpg2.disposf();
            }

            if (proxy != 0) {
                WindowPropfrtyGfttfr wpg3 =
                    nfw WindowPropfrtyGfttfr(proxy, XDnDConstbnts.XA_XdndProxy,
                                             0, 1, fblsf, XAtom.XA_WINDOW);

                try {
                    stbtus = wpg3.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

                    if (stbtus != XConstbnts.Suddfss ||
                        wpg3.gftDbtb() == 0 ||
                        wpg3.gftAdtublTypf() != XAtom.XA_WINDOW ||
                        Nbtivf.gftLong(wpg3.gftDbtb()) != proxy) {

                        proxy = 0;
                    } flsf {
                        WindowPropfrtyGfttfr wpg4 =
                            nfw WindowPropfrtyGfttfr(proxy,
                                                     XDnDConstbnts.XA_XdndAwbrf,
                                                     0, 1, fblsf,
                                                     XConstbnts.AnyPropfrtyTypf);

                        try {
                            stbtus = wpg4.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

                            if (stbtus != XConstbnts.Suddfss ||
                                wpg4.gftDbtb() == 0 ||
                                wpg4.gftAdtublTypf() != XAtom.XA_ATOM) {

                                proxy = 0;
                            }
                        } finblly {
                            wpg4.disposf();
                        }
                    }
                } finblly {
                    wpg3.disposf();
                }
            }
        }

        putEmbfddfrRfgistryEntry(fmbfddfd, ovfrridfn, vfrsion, proxy);
    }

    publid boolfbn isProtodolSupportfd(long window) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        WindowPropfrtyGfttfr wpg1 =
            nfw WindowPropfrtyGfttfr(window, XDnDConstbnts.XA_XdndAwbrf, 0, 1,
                                     fblsf, XConstbnts.AnyPropfrtyTypf);

        try {
            int stbtus = wpg1.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

            if (stbtus == XConstbnts.Suddfss &&
                wpg1.gftDbtb() != 0 && wpg1.gftAdtublTypf() == XAtom.XA_ATOM) {

                rfturn truf;
            } flsf {
                rfturn fblsf;
            }
        } finblly {
            wpg1.disposf();
        }
    }

    privbtf boolfbn prodfssXdndEntfr(XClifntMfssbgfEvfnt xdlifnt) {
        long sourdf_win = 0;
        long sourdf_win_mbsk = 0;
        int protodol_vfrsion = 0;
        int bdtions = DnDConstbnts.ACTION_NONE;
        boolfbn trbdk = truf;
        long[] formbts = null;

        if (gftSourdfWindow() != 0) {
            rfturn fblsf;
        }

        if (!(XToolkit.windowToXWindow(xdlifnt.gft_window()) instbndfof XWindow)
            && gftEmbfddfrRfgistryEntry(xdlifnt.gft_window()) == null) {
            rfturn fblsf;
        }

        if (xdlifnt.gft_mfssbgf_typf() != XDnDConstbnts.XA_XdndEntfr.gftAtom()){
            rfturn fblsf;
        }

        protodol_vfrsion =
            (int)((xdlifnt.gft_dbtb(1) & XDnDConstbnts.XDND_PROTOCOL_MASK) >>
                  XDnDConstbnts.XDND_PROTOCOL_SHIFT);

        /* XDnD domplibndf only rfquirfs supporting vfrsion 3 bnd up. */
        if (protodol_vfrsion < XDnDConstbnts.XDND_MIN_PROTOCOL_VERSION) {
            rfturn fblsf;
        }

        /* Ignorf tif sourdf if tif protodol vfrsion is iigifr tibn wf support. */
        if (protodol_vfrsion > XDnDConstbnts.XDND_PROTOCOL_VERSION) {
            rfturn fblsf;
        }

        sourdf_win = xdlifnt.gft_dbtb(0);

        /* Extrbdt tif list of supportfd bdtions. */
        if (protodol_vfrsion < 2) {
            /* Prior to XDnD vfrsion 2 only COPY bdtion wbs supportfd. */
            bdtions = DnDConstbnts.ACTION_COPY;
        } flsf {
            WindowPropfrtyGfttfr wpg =
                nfw WindowPropfrtyGfttfr(sourdf_win,
                                         XDnDConstbnts.XA_XdndAdtionList,
                                         0, 0xFFFF, fblsf,
                                         XAtom.XA_ATOM);
            try {
                wpg.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

                if (wpg.gftAdtublTypf() == XAtom.XA_ATOM &&
                    wpg.gftAdtublFormbt() == 32) {
                    long dbtb = wpg.gftDbtb();

                    for (int i = 0; i < wpg.gftNumbfrOfItfms(); i++) {
                        bdtions |=
                            XDnDConstbnts.gftJbvbAdtionForXDnDAdtion(Nbtivf.gftLong(dbtb, i));
                    }
                } flsf {
                    /*
                     * Addording to XDnD protodol, XdndAdtionList is optionbl.
                     * If XdndAdtionList is not sft wf try to gufss wiidi bdtions brf
                     * supportfd.
                     */
                    bdtions = DnDConstbnts.ACTION_COPY;
                    trbdk = truf;
                }
            } finblly {
                wpg.disposf();
            }
        }

        /* Extrbdt tif bvbilbblf dbtb typfs. */
        if ((xdlifnt.gft_dbtb(1) & XDnDConstbnts.XDND_DATA_TYPES_BIT) != 0) {
            WindowPropfrtyGfttfr wpg =
                nfw WindowPropfrtyGfttfr(sourdf_win,
                                         XDnDConstbnts.XA_XdndTypfList,
                                         0, 0xFFFF, fblsf,
                                         XAtom.XA_ATOM);
            try {
                wpg.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

                if (wpg.gftAdtublTypf() == XAtom.XA_ATOM &&
                    wpg.gftAdtublFormbt() == 32) {
                    formbts = Nbtivf.toLongs(wpg.gftDbtb(),
                                             wpg.gftNumbfrOfItfms());
                } flsf {
                    formbts = nfw long[0];
                }
            } finblly {
                wpg.disposf();
            }
        } flsf {
            int dountFormbts = 0;
            long[] formbts3 = nfw long[3];

            for (int i = 0; i < 3; i++) {
                long j;
                if ((j = xdlifnt.gft_dbtb(2 + i)) != XConstbnts.Nonf) {
                    formbts3[dountFormbts++] = j;
                }
            }

            formbts = nfw long[dountFormbts];

            Systfm.brrbydopy(formbts3, 0, formbts, 0, dountFormbts);
        }

        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

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
        sourdfAdtions = bdtions;
        sourdfFormbts = formbts;
        trbdkSourdfAdtions = trbdk;

        rfturn truf;
    }

    privbtf boolfbn prodfssXdndPosition(XClifntMfssbgfEvfnt xdlifnt) {
        long timf_stbmp = (int)XConstbnts.CurrfntTimf;
        long xdnd_bdtion = 0;
        int jbvb_bdtion = DnDConstbnts.ACTION_NONE;
        int x = 0;
        int y = 0;

        /* Ignorf XDnD mfssbgfs from bll otifr windows. */
        if (sourdfWindow != xdlifnt.gft_dbtb(0)) {
            rfturn fblsf;
        }

        XWindow xwindow = null;
        {
            XBbsfWindow xbbsfwindow = XToolkit.windowToXWindow(xdlifnt.gft_window());
            if (xbbsfwindow instbndfof XWindow) {
                xwindow = (XWindow)xbbsfwindow;
            }
        }

        x = (int)(xdlifnt.gft_dbtb(2) >> 16);
        y = (int)(xdlifnt.gft_dbtb(2) & 0xFFFF);

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
            /* Trbnslbtf mousf position from root doordinbtfs
               to tif tbrgft window doordinbtfs. */
            Point p = xwindow.toLodbl(x, y);
            x = p.x;
            y = p.y;
        }

        /* Timf stbmp - nfw in XDnD vfrsion 1. */
        if (sourdfProtodolVfrsion > 0) {
            timf_stbmp = xdlifnt.gft_dbtb(3);
        }

        /* Usfr bdtion - nfw in XDnD vfrsion 2. */
        if (sourdfProtodolVfrsion > 1) {
            xdnd_bdtion = xdlifnt.gft_dbtb(4);
        } flsf {
            /* Tif dffbult bdtion is XdndAdtionCopy */
            xdnd_bdtion = XDnDConstbnts.XA_XdndAdtionCopy.gftAtom();
        }

        jbvb_bdtion = XDnDConstbnts.gftJbvbAdtionForXDnDAdtion(xdnd_bdtion);

        if (trbdkSourdfAdtions) {
            sourdfAdtions |= jbvb_bdtion;
        }

        if (xwindow == null) {
            if (tbrgftXWindow != null) {
                notifyProtodolListfnfr(tbrgftXWindow, x, y,
                                       DnDConstbnts.ACTION_NONE, xdlifnt,
                                       MousfEvfnt.MOUSE_EXITED);
            }
        } flsf {
            int jbvb_fvfnt_id = 0;

            if (tbrgftXWindow == null) {
                jbvb_fvfnt_id = MousfEvfnt.MOUSE_ENTERED;
            } flsf {
                jbvb_fvfnt_id = MousfEvfnt.MOUSE_DRAGGED;
            }

            notifyProtodolListfnfr(xwindow, x, y, jbvb_bdtion, xdlifnt,
                                   jbvb_fvfnt_id);
        }

        usfrAdtion = jbvb_bdtion;
        sourdfX = x;
        sourdfY = y;
        tbrgftXWindow = xwindow;

        rfturn truf;
    }

    privbtf boolfbn prodfssXdndLfbvf(XClifntMfssbgfEvfnt xdlifnt) {
        /* Ignorf XDnD mfssbgfs from bll otifr windows. */
        if (sourdfWindow != xdlifnt.gft_dbtb(0)) {
            rfturn fblsf;
        }

        dlfbnup();

        rfturn truf;
    }

    privbtf boolfbn prodfssXdndDrop(XClifntMfssbgfEvfnt xdlifnt) {
        /* Ignorf XDnD mfssbgfs from bll otifr windows. */
        if (sourdfWindow != xdlifnt.gft_dbtb(0)) {
            rfturn fblsf;
        }

        if (tbrgftXWindow != null) {
            notifyProtodolListfnfr(tbrgftXWindow, sourdfX, sourdfY, usfrAdtion,
                                   xdlifnt, MousfEvfnt.MOUSE_RELEASED);
        }

        rfturn truf;
    }

    publid int gftMfssbgfTypf(XClifntMfssbgfEvfnt xdlifnt) {
        long mfssbgfTypf = xdlifnt.gft_mfssbgf_typf();

        if (mfssbgfTypf == XDnDConstbnts.XA_XdndEntfr.gftAtom()) {
            rfturn ENTER_MESSAGE;
        } flsf if (mfssbgfTypf == XDnDConstbnts.XA_XdndPosition.gftAtom()) {
            rfturn MOTION_MESSAGE;
        } flsf if (mfssbgfTypf == XDnDConstbnts.XA_XdndLfbvf.gftAtom()) {
            rfturn LEAVE_MESSAGE;
        } flsf if (mfssbgfTypf == XDnDConstbnts.XA_XdndDrop.gftAtom()) {
            rfturn DROP_MESSAGE;
        } flsf {
            rfturn UNKNOWN_MESSAGE;
        }
    }

    protfdtfd boolfbn prodfssClifntMfssbgfImpl(XClifntMfssbgfEvfnt xdlifnt) {
        long mfssbgfTypf = xdlifnt.gft_mfssbgf_typf();

        if (mfssbgfTypf == XDnDConstbnts.XA_XdndEntfr.gftAtom()) {
            rfturn prodfssXdndEntfr(xdlifnt);
        } flsf if (mfssbgfTypf == XDnDConstbnts.XA_XdndPosition.gftAtom()) {
            rfturn prodfssXdndPosition(xdlifnt);
        } flsf if (mfssbgfTypf == XDnDConstbnts.XA_XdndLfbvf.gftAtom()) {
            rfturn prodfssXdndLfbvf(xdlifnt);
        } flsf if (mfssbgfTypf == XDnDConstbnts.XA_XdndDrop.gftAtom()) {
            rfturn prodfssXdndDrop(xdlifnt);
        } flsf {
            rfturn fblsf;
        }
    }

    protfdtfd void sfndEntfrMfssbgfToToplfvfl(long toplfvfl,
                                              XClifntMfssbgfEvfnt xdlifnt) {
        /* flbgs */
        long dbtb1 = sourdfProtodolVfrsion << XDnDConstbnts.XDND_PROTOCOL_SHIFT;
        if (sourdfFormbts != null && sourdfFormbts.lfngti > 3) {
            dbtb1 |= XDnDConstbnts.XDND_DATA_TYPES_BIT;
        }
        long dbtb2 = sourdfFormbts.lfngti > 0 ? sourdfFormbts[0] : 0;
        long dbtb3 = sourdfFormbts.lfngti > 1 ? sourdfFormbts[1] : 0;
        long dbtb4 = sourdfFormbts.lfngti > 2 ? sourdfFormbts[2] : 0;
        sfndEntfrMfssbgfToToplfvflImpl(toplfvfl, xdlifnt.gft_dbtb(0),
                                       dbtb1, dbtb2, dbtb3, dbtb4);

    }

    privbtf void sfndEntfrMfssbgfToToplfvflImpl(long toplfvfl,
                                                long sourdfWindow,
                                                long dbtb1, long dbtb2,
                                                long dbtb3, long dbtb4) {
        XClifntMfssbgfEvfnt fntfr = nfw XClifntMfssbgfEvfnt();
        try {
            fntfr.sft_typf(XConstbnts.ClifntMfssbgf);
            fntfr.sft_window(toplfvfl);
            fntfr.sft_formbt(32);
            fntfr.sft_mfssbgf_typf(XDnDConstbnts.XA_XdndEntfr.gftAtom());
            /* XID of tif sourdf window */
            fntfr.sft_dbtb(0, sourdfWindow);
            fntfr.sft_dbtb(1, dbtb1);
            fntfr.sft_dbtb(2, dbtb2);
            fntfr.sft_dbtb(3, dbtb3);
            fntfr.sft_dbtb(4, dbtb4);

            forwbrdClifntMfssbgfToToplfvfl(toplfvfl, fntfr);
        } finblly {
            fntfr.disposf();
        }
    }

    protfdtfd void sfndLfbvfMfssbgfToToplfvfl(long toplfvfl,
                                              XClifntMfssbgfEvfnt xdlifnt) {
        sfndLfbvfMfssbgfToToplfvflImpl(toplfvfl, xdlifnt.gft_dbtb(0));
    }

    protfdtfd void sfndLfbvfMfssbgfToToplfvflImpl(long toplfvfl,
                                                  long sourdfWindow) {
        XClifntMfssbgfEvfnt lfbvf = nfw XClifntMfssbgfEvfnt();
        try {
            lfbvf.sft_typf(XConstbnts.ClifntMfssbgf);
            lfbvf.sft_window(toplfvfl);
            lfbvf.sft_formbt(32);
            lfbvf.sft_mfssbgf_typf(XDnDConstbnts.XA_XdndLfbvf.gftAtom());
            /* XID of tif sourdf window */
            lfbvf.sft_dbtb(0, sourdfWindow);
            /* flbgs */
            lfbvf.sft_dbtb(1, 0);

            forwbrdClifntMfssbgfToToplfvfl(toplfvfl, lfbvf);
        } finblly {
            lfbvf.disposf();
        }
    }

    publid boolfbn sfndRfsponsf(long dtxt, int fvfntID, int bdtion) {
        XClifntMfssbgfEvfnt xdlifnt = nfw XClifntMfssbgfEvfnt(dtxt);

        if (xdlifnt.gft_mfssbgf_typf() !=
            XDnDConstbnts.XA_XdndPosition.gftAtom()) {

            rfturn fblsf;
        }

        if (fvfntID == MousfEvfnt.MOUSE_EXITED) {
            bdtion = DnDConstbnts.ACTION_NONE;
        }

        XClifntMfssbgfEvfnt msg = nfw XClifntMfssbgfEvfnt();
        try {
            msg.sft_typf(XConstbnts.ClifntMfssbgf);
            msg.sft_window(xdlifnt.gft_dbtb(0));
            msg.sft_formbt(32);
            msg.sft_mfssbgf_typf(XDnDConstbnts.XA_XdndStbtus.gftAtom());
            /* tbrgft window */
            msg.sft_dbtb(0, xdlifnt.gft_window());
            /* flbgs */
            long flbgs = 0;
            if (bdtion != DnDConstbnts.ACTION_NONE) {
                flbgs |= XDnDConstbnts.XDND_ACCEPT_DROP_FLAG;
            }
            msg.sft_dbtb(1, flbgs);
            /* spfdify bn fmpty rfdtbnglf */
            msg.sft_dbtb(2, 0); /* x, y */
            msg.sft_dbtb(3, 0); /* w, i */
            /* bdtion bddfptfd by tif tbrgft */
            msg.sft_dbtb(4, XDnDConstbnts.gftXDnDAdtionForJbvbAdtion(bdtion));

            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                                       xdlifnt.gft_dbtb(0),
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
        long mfssbgf_typf = xdlifnt.gft_mfssbgf_typf();
        long timf_stbmp = XConstbnts.CurrfntTimf;

        // NOTE: wf bssumf tibt tif sourdf supports bt lfbst vfrsion 1, so wf
        // dbn usf tif timf stbmp
        if (mfssbgf_typf == XDnDConstbnts.XA_XdndPosition.gftAtom()) {
            // X sfrvfr timf is bn unsignfd 32-bit numbfr!
            timf_stbmp = xdlifnt.gft_dbtb(3) & 0xFFFFFFFFL;
        } flsf if (mfssbgf_typf == XDnDConstbnts.XA_XdndDrop.gftAtom()) {
            // X sfrvfr timf is bn unsignfd 32-bit numbfr!
            timf_stbmp = xdlifnt.gft_dbtb(2) & 0xFFFFFFFFL;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption();
        }

        rfturn XDnDConstbnts.XDnDSflfdtion.gftDbtb(formbt, timf_stbmp);
    }

    publid boolfbn sfndDropDonf(long dtxt, boolfbn suddfss, int dropAdtion) {
        XClifntMfssbgfEvfnt xdlifnt = nfw XClifntMfssbgfEvfnt(dtxt);

        if (xdlifnt.gft_mfssbgf_typf() !=
            XDnDConstbnts.XA_XdndDrop.gftAtom()) {
            rfturn fblsf;
        }

        /*
         * Tif XDnD protodol rfdommfnds tibt tif tbrgft rfqufsts tif spfdibl
         * tbrgft DELETE in dbsf if tif drop bdtion is XdndAdtionMovf.
         */
        if (dropAdtion == DnDConstbnts.ACTION_MOVE && suddfss) {

            long timf_stbmp = xdlifnt.gft_dbtb(2);
            long xdndSflfdtionAtom =
                XDnDConstbnts.XDnDSflfdtion.gftSflfdtionAtom().gftAtom();

            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XConvfrtSflfdtion(XToolkit.gftDisplby(),
                                              xdndSflfdtionAtom,
                                              XAtom.gft("DELETE").gftAtom(),
                                              XAtom.gft("XAWT_SELECTION").gftAtom(),
                                              XWindow.gftXAWTRootWindow().gftWindow(),
                                              timf_stbmp);
            } finblly {
                XToolkit.bwtUnlodk();
            }
        }

        XClifntMfssbgfEvfnt msg = nfw XClifntMfssbgfEvfnt();
        try {
            msg.sft_typf(XConstbnts.ClifntMfssbgf);
            msg.sft_window(xdlifnt.gft_dbtb(0));
            msg.sft_formbt(32);
            msg.sft_mfssbgf_typf(XDnDConstbnts.XA_XdndFinisifd.gftAtom());
            msg.sft_dbtb(0, xdlifnt.gft_window()); /* tbrgft window */
            msg.sft_dbtb(1, 0); /* flbgs */
            /* spfdify bn fmpty rfdtbnglf */
            msg.sft_dbtb(2, 0);
            if (sourdfProtodolVfrsion >= 5) {
                if (suddfss) {
                    msg.sft_dbtb(1, XDnDConstbnts.XDND_ACCEPT_DROP_FLAG);
                }
                /* bdtion pfrformfd by tif tbrgft */
                msg.sft_dbtb(2, XDnDConstbnts.gftXDnDAdtionForJbvbAdtion(dropAdtion));
            }
            msg.sft_dbtb(3, 0);
            msg.sft_dbtb(4, 0);

            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                                       xdlifnt.gft_dbtb(0),
                                       fblsf, XConstbnts.NoEvfntMbsk,
                                       msg.pDbtb);
            } finblly {
                XToolkit.bwtUnlodk();
            }
        } finblly {
            msg.disposf();
        }

        /*
         * Flusi tif bufffr to gubrbntff tibt tif drop domplftion fvfnt is sfnt
         * to tif sourdf bfforf tif mftiod rfturns.
         */
        XToolkit.bwtLodk();
        try {
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
                                   DnDConstbnts.ACTION_NONE, null,
                                   MousfEvfnt.MOUSE_EXITED);
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
        trbdkSourdfAdtions = fblsf;
        usfrAdtion = DnDConstbnts.ACTION_NONE;
        sourdfX = 0;
        sourdfY = 0;
        tbrgftXWindow = null;
    }

    publid boolfbn isDrbgOvfrComponfnt() {
        rfturn tbrgftXWindow != null;
    }

    publid void bdjustEvfntForForwbrding(XClifntMfssbgfEvfnt xdlifnt,
                                         EmbfddfrRfgistryEntry fntry) {
        /* Adjust tif fvfnt to mbtdi tif XDnD protodol vfrsion. */
        int vfrsion = fntry.gftVfrsion();
        if (xdlifnt.gft_mfssbgf_typf() == XDnDConstbnts.XA_XdndEntfr.gftAtom()) {
            int min_vfrsion = sourdfProtodolVfrsion < vfrsion ?
                sourdfProtodolVfrsion : vfrsion;
            long dbtb1 = min_vfrsion << XDnDConstbnts.XDND_PROTOCOL_SHIFT;
            if (sourdfFormbts != null && sourdfFormbts.lfngti > 3) {
                dbtb1 |= XDnDConstbnts.XDND_DATA_TYPES_BIT;
            }
            if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                loggfr.finfst("         "
                              + " fntryVfrsion=" + vfrsion
                              + " sourdfProtodolVfrsion=" +
                              sourdfProtodolVfrsion
                              + " sourdfFormbts.lfngti=" +
                              (sourdfFormbts != null ? sourdfFormbts.lfngti : 0));
            }
            xdlifnt.sft_dbtb(1, dbtb1);
        }
    }

    @SupprfssWbrnings("stbtid")
    privbtf void notifyProtodolListfnfr(XWindow xwindow, int x, int y,
                                        int dropAdtion,
                                        XClifntMfssbgfEvfnt xdlifnt,
                                        int fvfntID) {
        long nbtivfCtxt = 0;

        // Mbkf b dopy of tif pbssfd XClifntMfssbgfEvfnt strudturf, sindf
        // tif originbl strudturf dbn bf frffd bfforf tiis
        // SunDropTbrgftEvfnt is dispbtdifd.
        if (xdlifnt != null) {
            int sizf = nfw XClifntMfssbgfEvfnt(nbtivfCtxt).gftSizf();

            nbtivfCtxt = unsbff.bllodbtfMfmory(sizf + 4 * Nbtivf.gftLongSizf());

            unsbff.dopyMfmory(xdlifnt.pDbtb, nbtivfCtxt, sizf);

            long dbtb1 = sourdfProtodolVfrsion << XDnDConstbnts.XDND_PROTOCOL_SHIFT;
            if (sourdfFormbts != null && sourdfFormbts.lfngti > 3) {
                dbtb1 |= XDnDConstbnts.XDND_DATA_TYPES_BIT;
            }
            // Appfnd informbtion from tif lbtfst XdndEntfr fvfnt.
            Nbtivf.putLong(nbtivfCtxt + sizf, dbtb1);
            Nbtivf.putLong(nbtivfCtxt + sizf + Nbtivf.gftLongSizf(),
                           sourdfFormbts.lfngti > 0 ? sourdfFormbts[0] : 0);
            Nbtivf.putLong(nbtivfCtxt + sizf + 2 * Nbtivf.gftLongSizf(),
                           sourdfFormbts.lfngti > 1 ? sourdfFormbts[1] : 0);
            Nbtivf.putLong(nbtivfCtxt + sizf + 3 * Nbtivf.gftLongSizf(),
                           sourdfFormbts.lfngti > 2 ? sourdfFormbts[2] : 0);
        }

        gftProtodolListfnfr().ibndlfDropTbrgftNotifidbtion(xwindow, x, y,
                                                           dropAdtion,
                                                           sourdfAdtions,
                                                           sourdfFormbts,
                                                           nbtivfCtxt,
                                                           fvfntID);
    }

    /*
     * Tif mftiods/fiflds dffinfd bflow brf fxfdutfd/bddfssfd only on
     * tif toolkit tirfbd.
     * Tif mftiods/fiflds dffinfd bflow brf fxfdutfd/bddfssfd only on tif fvfnt
     * dispbtdi tirfbd.
     */

    publid boolfbn forwbrdEvfntToEmbfddfd(long fmbfddfd, long dtxt,
                                          int fvfntID) {
        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            loggfr.finfst("        dtxt=" + dtxt +
                          " typf=" + (dtxt != 0 ?
                                      gftMfssbgfTypf(nfw
                                          XClifntMfssbgfEvfnt(dtxt)) : 0) +
                          " prfvCtxt=" + prfvCtxt +
                          " prfvTypf=" + (prfvCtxt != 0 ?
                                      gftMfssbgfTypf(nfw
                                          XClifntMfssbgfEvfnt(prfvCtxt)) : 0));
        }
        if ((dtxt == 0 ||
             gftMfssbgfTypf(nfw XClifntMfssbgfEvfnt(dtxt)) == UNKNOWN_MESSAGE) &&
            (prfvCtxt == 0 ||
             gftMfssbgfTypf(nfw XClifntMfssbgfEvfnt(prfvCtxt)) == UNKNOWN_MESSAGE)) {
            rfturn fblsf;
        }

        // Tif sizf of XClifntMfssbgfEvfnt strudturf.
        int sizf = XClifntMfssbgfEvfnt.gftSizf();

        if (dtxt != 0) {
            XClifntMfssbgfEvfnt xdlifnt = nfw XClifntMfssbgfEvfnt(dtxt);
            if (!ovfrXEmbfdClifnt) {
                long dbtb1 = Nbtivf.gftLong(dtxt + sizf);
                long dbtb2 = Nbtivf.gftLong(dtxt + sizf + Nbtivf.gftLongSizf());
                long dbtb3 = Nbtivf.gftLong(dtxt + sizf + 2 * Nbtivf.gftLongSizf());
                long dbtb4 = Nbtivf.gftLong(dtxt + sizf + 3 * Nbtivf.gftLongSizf());

                if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    loggfr.finfst("         1 "
                                  + " fmbfddfd=" + fmbfddfd
                                  + " sourdf=" + xdlifnt.gft_dbtb(0)
                                  + " dbtb1=" + dbtb1
                                  + " dbtb2=" + dbtb2
                                  + " dbtb3=" + dbtb3
                                  + " dbtb4=" + dbtb4);
                }

                // Copy XdndTypfList from sourdf to proxy.
                if ((dbtb1 & XDnDConstbnts.XDND_DATA_TYPES_BIT) != 0) {
                    WindowPropfrtyGfttfr wpg =
                        nfw WindowPropfrtyGfttfr(xdlifnt.gft_dbtb(0),
                                                 XDnDConstbnts.XA_XdndTypfList,
                                                 0, 0xFFFF, fblsf,
                                                 XAtom.XA_ATOM);
                    try {
                        wpg.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

                        if (wpg.gftAdtublTypf() == XAtom.XA_ATOM &&
                            wpg.gftAdtublFormbt() == 32) {

                            XToolkit.bwtLodk();
                            try {
                                XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.VfrifyCibngfPropfrtyHbndlfr.gftInstbndf());
                                XDnDConstbnts.XA_XdndTypfList.sftAtomDbtb(xdlifnt.gft_window(),
                                                                          XAtom.XA_ATOM,
                                                                          wpg.gftDbtb(),
                                                                          wpg.gftNumbfrOfItfms());
                                XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

                                if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                                    (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
                                    if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.WARNING)) {
                                        loggfr.wbrning("Cbnnot sft XdndTypfList on tif proxy window");
                                    }
                                }
                            } finblly {
                                XToolkit.bwtUnlodk();
                            }
                        } flsf {
                            if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.WARNING)) {
                                loggfr.wbrning("Cbnnot rfbd XdndTypfList from tif sourdf window");
                            }
                        }
                    } finblly {
                        wpg.disposf();
                    }
                }
                XDrbgSourdfContfxtPffr.sftProxyModfSourdfWindow(xdlifnt.gft_dbtb(0));

                sfndEntfrMfssbgfToToplfvflImpl(fmbfddfd, xdlifnt.gft_window(),
                                               dbtb1, dbtb2, dbtb3, dbtb4);
                ovfrXEmbfdClifnt = truf;
            }

            if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                loggfr.finfst("         2 "
                              + " fmbfddfd=" + fmbfddfd
                              + " xdlifnt=" + xdlifnt);
            }

            /* Mbkf b dopy of tif originbl fvfnt, sindf wf brf going to modify tif
               fvfnt wiilf it still dbn bf rfffrfndfd from otifr Jbvb fvfnts. */
            {
                XClifntMfssbgfEvfnt dopy = nfw XClifntMfssbgfEvfnt();
                unsbff.dopyMfmory(xdlifnt.pDbtb, dopy.pDbtb, XClifntMfssbgfEvfnt.gftSizf());

                dopy.sft_dbtb(0, xdlifnt.gft_window());

                forwbrdClifntMfssbgfToToplfvfl(fmbfddfd, dopy);
            }
        }

        if (fvfntID == MousfEvfnt.MOUSE_EXITED) {
            if (ovfrXEmbfdClifnt) {
                if (dtxt != 0 || prfvCtxt != 0) {
                    // Lbst dibndf to sfnd XdndLfbvf to tif XEmbfd dlifnt.
                    XClifntMfssbgfEvfnt xdlifnt = dtxt != 0 ?
                        nfw XClifntMfssbgfEvfnt(dtxt) :
                        nfw XClifntMfssbgfEvfnt(prfvCtxt);
                    sfndLfbvfMfssbgfToToplfvflImpl(fmbfddfd, xdlifnt.gft_window());
                }
                ovfrXEmbfdClifnt = fblsf;
                // Wf ibvf to dlfbr tif proxy modf sourdf window ifrf,
                // wifn tif drbg fxits tif XEmbfdCbnvbsPffr.
                // NOTE: bt tiis point tif XEmbfd dlifnt still migit ibvf somf
                // drbg notifidbtions to prodfss bnd it will sfnd rfsponsfs to
                // us. Witi tif proxy modf sourdf window dlfbrfd wf won't bf
                // bblf to forwbrd tifsf rfsponsfs to tif bdtubl sourdf. Tiis is
                // not b problfm if tif drbg opfrbtion wbs initibtfd in tiis
                // JVM. Howfvfr, if it wbs initibtfd in bnotifr prodfssfs tif
                // rfsponsfs will bf lost. Wf bfbr witi it for now, bs it sffms
                // tifrf is no otifr rflibblf point to dlfbr.
                XDrbgSourdfContfxtPffr.sftProxyModfSourdfWindow(0);
            }
        }

        if (fvfntID == MousfEvfnt.MOUSE_RELEASED) {
            ovfrXEmbfdClifnt = fblsf;
            dlfbnup();
        }

        if (prfvCtxt != 0) {
            unsbff.frffMfmory(prfvCtxt);
            prfvCtxt = 0;
        }

        if (dtxt != 0 && ovfrXEmbfdClifnt) {
            prfvCtxt = unsbff.bllodbtfMfmory(sizf + 4 * Nbtivf.gftLongSizf());

            unsbff.dopyMfmory(dtxt, prfvCtxt, sizf + 4 * Nbtivf.gftLongSizf());
        }

        rfturn truf;
    }

    publid boolfbn isXEmbfdSupportfd() {
        rfturn truf;
    }
}
