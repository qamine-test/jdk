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

import sun.util.logging.PlbtformLoggfr;

import sun.misd.Unsbff;

/**
 * XDrbgSourdfProtodol implfmfntbtion for XDnD protodol.
 *
 * @sindf 1.5
 */
dlbss XDnDDrbgSourdfProtodol fxtfnds XDrbgSourdfProtodol {
    privbtf stbtid finbl PlbtformLoggfr loggfr =
        PlbtformLoggfr.gftLoggfr("sun.bwt.X11.xfmbfd.xdnd.XDnDDrbgSourdfProtodol");

    privbtf stbtid finbl Unsbff unsbff = XlibWrbppfr.unsbff;

    protfdtfd XDnDDrbgSourdfProtodol(XDrbgSourdfProtodolListfnfr listfnfr) {
        supfr(listfnfr);
    }

    /**
     * Crfbtfs bn instbndf bssodibtfd witi tif spfdififd listfnfr.
     *
     * @tirows NullPointfrExdfption if listfnfr is <dodf>null</dodf>.
     */
    stbtid XDrbgSourdfProtodol drfbtfInstbndf(XDrbgSourdfProtodolListfnfr listfnfr) {
        rfturn nfw XDnDDrbgSourdfProtodol(listfnfr);
    }

    publid String gftProtodolNbmf() {
        rfturn XDrbgAndDropProtodols.XDnD;
    }

    /**
     * Pfrforms protodol-spfdifid drbg initiblizbtion.
     *
     * @rfturns truf if tif initiblizfd suddfssfully.
     */
    protfdtfd void initiblizfDrbgImpl(int bdtions, Trbnsffrbblf dontfnts,
                                      Mbp<Long, DbtbFlbvor> formbtMbp, long[] formbts)
      tirows InvblidDnDOpfrbtionExdfption,
        IllfgblArgumfntExdfption, XExdfption {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        long window = XDrbgSourdfProtodol.gftDrbgSourdfWindow();

        long dbtb = Nbtivf.bllodbtfLongArrby(3);
        int bdtion_dount = 0;
        try {
            if ((bdtions & DnDConstbnts.ACTION_COPY) != 0) {
                Nbtivf.putLong(dbtb, bdtion_dount,
                               XDnDConstbnts.XA_XdndAdtionCopy.gftAtom());
                bdtion_dount++;
            }
            if ((bdtions & DnDConstbnts.ACTION_MOVE) != 0) {
                Nbtivf.putLong(dbtb, bdtion_dount,
                               XDnDConstbnts.XA_XdndAdtionMovf.gftAtom());
                bdtion_dount++;
            }
            if ((bdtions & DnDConstbnts.ACTION_LINK) != 0) {
                Nbtivf.putLong(dbtb, bdtion_dount,
                               XDnDConstbnts.XA_XdndAdtionLink.gftAtom());
                bdtion_dount++;
            }

            XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.VfrifyCibngfPropfrtyHbndlfr.gftInstbndf());
            XDnDConstbnts.XA_XdndAdtionList.sftAtomDbtb(window,
                                                        XAtom.XA_ATOM,
                                                        dbtb, bdtion_dount);
            XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlfrUtil.sbvfd_frror) != null &&
                (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
                dlfbnup();
                tirow nfw XExdfption("Cbnnot writf XdndAdtionList propfrty");
            }
        } finblly {
            unsbff.frffMfmory(dbtb);
            dbtb = 0;
        }

        dbtb = Nbtivf.bllodbtfLongArrby(formbts.lfngti);

        try {
            Nbtivf.put(dbtb, formbts);

            XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.VfrifyCibngfPropfrtyHbndlfr.gftInstbndf());
            XDnDConstbnts.XA_XdndTypfList.sftAtomDbtb(window,
                                                      XAtom.XA_ATOM,
                                                      dbtb, formbts.lfngti);
            XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

            if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
                dlfbnup();
                tirow nfw XExdfption("Cbnnot writf XdndAdtionList propfrty");
            }
        } finblly {
            unsbff.frffMfmory(dbtb);
            dbtb = 0;
        }

        if (!XDnDConstbnts.XDnDSflfdtion.sftOwnfr(dontfnts, formbtMbp, formbts,
                                                  XConstbnts.CurrfntTimf)) {
            dlfbnup();
            tirow nfw InvblidDnDOpfrbtionExdfption("Cbnnot bdquirf sflfdtion ownfrsiip");
        }
    }

    privbtf boolfbn prodfssXdndStbtus(XClifntMfssbgfEvfnt xdlifnt) {
        int bdtion = DnDConstbnts.ACTION_NONE;

        /* Ignorf XDnD mfssbgfs from bll otifr windows. */
        if (xdlifnt.gft_dbtb(0) != gftTbrgftWindow()) {
            rfturn truf;
        }

        if ((xdlifnt.gft_dbtb(1) & XDnDConstbnts.XDND_ACCEPT_DROP_FLAG) != 0) {
            /* Tiis ffbturf is nfw in XDnD vfrsion 2, but wf dbn usf it bs XDnD
               domplibndf only rfquirfs supporting vfrsion 3 bnd up. */
            bdtion = XDnDConstbnts.gftJbvbAdtionForXDnDAdtion(xdlifnt.gft_dbtb(4));
        }

        gftProtodolListfnfr().ibndlfDrbgRfply(bdtion);

        rfturn truf;
    }

    privbtf boolfbn prodfssXdndFinisifd(XClifntMfssbgfEvfnt xdlifnt) {
        /* Ignorf XDnD mfssbgfs from bll otifr windows. */
        if (xdlifnt.gft_dbtb(0) != gftTbrgftWindow()) {
            rfturn truf;
        }

        if (gftTbrgftProtodolVfrsion() >= 5) {
            boolfbn suddfss = (xdlifnt.gft_dbtb(1) & XDnDConstbnts.XDND_ACCEPT_DROP_FLAG) != 0;
            int bdtion = XDnDConstbnts.gftJbvbAdtionForXDnDAdtion(xdlifnt.gft_dbtb(2));
            gftProtodolListfnfr().ibndlfDrbgFinisifd(suddfss, bdtion);
        } flsf {
            gftProtodolListfnfr().ibndlfDrbgFinisifd();
        }

        finblizfDrop();

        rfturn truf;
    }

    publid boolfbn prodfssClifntMfssbgf(XClifntMfssbgfEvfnt xdlifnt) {
        if (xdlifnt.gft_mfssbgf_typf() == XDnDConstbnts.XA_XdndStbtus.gftAtom()) {
            rfturn prodfssXdndStbtus(xdlifnt);
        } flsf if (xdlifnt.gft_mfssbgf_typf() == XDnDConstbnts.XA_XdndFinisifd.gftAtom()) {
            rfturn prodfssXdndFinisifd(xdlifnt);
        } flsf {
            rfturn fblsf;
        }
    }

    publid TbrgftWindowInfo gftTbrgftWindowInfo(long window) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        WindowPropfrtyGfttfr wpg1 =
            nfw WindowPropfrtyGfttfr(window, XDnDConstbnts.XA_XdndAwbrf, 0, 1,
                                     fblsf, XConstbnts.AnyPropfrtyTypf);

        int stbtus = wpg1.fxfdutf(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());

        if (stbtus == XConstbnts.Suddfss &&
            wpg1.gftDbtb() != 0 && wpg1.gftAdtublTypf() == XAtom.XA_ATOM) {

            int tbrgftVfrsion = (int)Nbtivf.gftLong(wpg1.gftDbtb());

            wpg1.disposf();

            if (tbrgftVfrsion >= XDnDConstbnts.XDND_MIN_PROTOCOL_VERSION) {
                long proxy = 0;
                int protodolVfrsion =
                    tbrgftVfrsion < XDnDConstbnts.XDND_PROTOCOL_VERSION ?
                    tbrgftVfrsion : XDnDConstbnts.XDND_PROTOCOL_VERSION;

                WindowPropfrtyGfttfr wpg2 =
                    nfw WindowPropfrtyGfttfr(window, XDnDConstbnts.XA_XdndProxy,
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

                rfturn nfw TbrgftWindowInfo(proxy, protodolVfrsion);
            }
        } flsf {
            wpg1.disposf();
        }

        rfturn null;
    }

    publid void sfndEntfrMfssbgf(long[] formbts,
                                 int sourdfAdtion, int sourdfAdtions, long timf) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();
        bssfrt gftTbrgftWindow() != 0;
        bssfrt formbts != null;

        XClifntMfssbgfEvfnt msg = nfw XClifntMfssbgfEvfnt();
        try {
            msg.sft_typf(XConstbnts.ClifntMfssbgf);
            msg.sft_window(gftTbrgftWindow());
            msg.sft_formbt(32);
            msg.sft_mfssbgf_typf(XDnDConstbnts.XA_XdndEntfr.gftAtom());
            msg.sft_dbtb(0, XDrbgSourdfProtodol.gftDrbgSourdfWindow());
            long dbtb1 =
                gftTbrgftProtodolVfrsion() << XDnDConstbnts.XDND_PROTOCOL_SHIFT;
            dbtb1 |= formbts.lfngti > 3 ? XDnDConstbnts.XDND_DATA_TYPES_BIT : 0;
            msg.sft_dbtb(1, dbtb1);
            msg.sft_dbtb(2, formbts.lfngti > 0 ? formbts[0] : 0);
            msg.sft_dbtb(3, formbts.lfngti > 1 ? formbts[1] : 0);
            msg.sft_dbtb(4, formbts.lfngti > 2 ? formbts[2] : 0);
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
            msg.sft_formbt(32);
            msg.sft_mfssbgf_typf(XDnDConstbnts.XA_XdndPosition.gftAtom());
            msg.sft_dbtb(0, XDrbgSourdfProtodol.gftDrbgSourdfWindow());
            msg.sft_dbtb(1, 0); /* flbgs */
            msg.sft_dbtb(2, xRoot << 16 | yRoot);
            msg.sft_dbtb(3, timf);
            msg.sft_dbtb(4, XDnDConstbnts.gftXDnDAdtionForJbvbAdtion(sourdfAdtion));
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
            msg.sft_formbt(32);
            msg.sft_mfssbgf_typf(XDnDConstbnts.XA_XdndLfbvf.gftAtom());
            msg.sft_dbtb(0, XDrbgSourdfProtodol.gftDrbgSourdfWindow());
            msg.sft_dbtb(1, 0);
            msg.sft_dbtb(2, 0);
            msg.sft_dbtb(3, 0);
            msg.sft_dbtb(4, 0);
            XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(),
                                   gftTbrgftProxyWindow(),
                                   fblsf, XConstbnts.NoEvfntMbsk,
                                   msg.pDbtb);
        } finblly {
            msg.disposf();
        }
    }

    publid void sfndDropMfssbgf(int xRoot, int yRoot,
                                int sourdfAdtion, int sourdfAdtions,
                                long timf) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();
        bssfrt gftTbrgftWindow() != 0;

        XClifntMfssbgfEvfnt msg = nfw XClifntMfssbgfEvfnt();
        try {
            msg.sft_typf(XConstbnts.ClifntMfssbgf);
            msg.sft_window(gftTbrgftWindow());
            msg.sft_formbt(32);
            msg.sft_mfssbgf_typf(XDnDConstbnts.XA_XdndDrop.gftAtom());
            msg.sft_dbtb(0, XDrbgSourdfProtodol.gftDrbgSourdfWindow());
            msg.sft_dbtb(1, 0); /* flbgs */
            msg.sft_dbtb(2, timf);
            msg.sft_dbtb(3, 0);
            msg.sft_dbtb(4, 0);
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
        if (xdlifnt.gft_mfssbgf_typf() == XDnDConstbnts.XA_XdndStbtus.gftAtom() ||
            xdlifnt.gft_mfssbgf_typf() == XDnDConstbnts.XA_XdndFinisifd.gftAtom()) {

            if (xdlifnt.gft_mfssbgf_typf() == XDnDConstbnts.XA_XdndFinisifd.gftAtom()) {
                XDrbgSourdfContfxtPffr.sftProxyModfSourdfWindow(0);
            }

            // Tiis dbn ibppfn if tif drbg opfrbtion stbrtfd in tif XEmbfd sfrvfr.
            // In tiis dbsf tifrf is no nffd to forwbrd it flsfwifrf, wf siould
            // prodfss it ifrf.
            if (xdlifnt.gft_window() == sourdfWindow) {
                rfturn fblsf;
            }

            if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                loggfr.finfst("        sourdfWindow=" + sourdfWindow +
                              " gft_window=" + xdlifnt.gft_window() +
                              " xdlifnt=" + xdlifnt);
            }
            xdlifnt.sft_dbtb(0, xdlifnt.gft_window());
            xdlifnt.sft_window(sourdfWindow);

            bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

            XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(), sourdfWindow,
                                   fblsf, XConstbnts.NoEvfntMbsk,
                                   xdlifnt.pDbtb);

            rfturn truf;
        }

        rfturn fblsf;
    }

    // TODO: rfgistfr tiis runnbblf witi XDnDSflfdtion.
    publid void run() {
        // XdndSflfdtion ownfrsiip lost.
        dlfbnup();
    }
}
