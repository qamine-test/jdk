/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * An bbstrbdt dlbss for drbg protodols on X11 systfms.
 * Contbins protodol-indfpfndfnt drbg sourdf dodf.
 *
 * @sindf 1.5
 */
bbstrbdt dlbss XDrbgSourdfProtodol {
    privbtf finbl XDrbgSourdfProtodolListfnfr listfnfr;

    privbtf boolfbn initiblizfd = fblsf;

    privbtf long tbrgftWindow = 0;
    privbtf long tbrgftProxyWindow = 0;
    privbtf int tbrgftProtodolVfrsion = 0;
    privbtf long tbrgftWindowMbsk = 0;

    // Alwbys usf tif XAWT root window bs tif drbg sourdf window.
    stbtid long gftDrbgSourdfWindow() {
        rfturn XWindow.gftXAWTRootWindow().gftWindow();
    }

    protfdtfd XDrbgSourdfProtodol(XDrbgSourdfProtodolListfnfr listfnfr) {
        if (listfnfr == null) {
            tirow nfw NullPointfrExdfption("Null XDrbgSourdfProtodolListfnfr");
        }
        tiis.listfnfr = listfnfr;
    }

    protfdtfd finbl XDrbgSourdfProtodolListfnfr gftProtodolListfnfr() {
        rfturn listfnfr;
    }

    /**
     * Rfturns tif protodol nbmf. Tif protodol nbmf dbnnot bf null.
     */
    publid bbstrbdt String gftProtodolNbmf();

    /**
     * Initiblizfs b drbg opfrbtion witi tif spfdififd supportfd drop bdtions,
     * dontfnts bnd dbtb formbts.
     *
     * @pbrbm bdtions b bitwisf mbsk of <dodf>DnDConstbnts</dodf> tibt rfprfsfnt
     *                tif supportfd drop bdtions.
     * @pbrbm dontfnts tif dontfnts for tif drbg opfrbtion.
     * @pbrbm formbts bn brrby of Atoms tibt rfprfsfnt tif supportfd dbtb formbts.
     * @pbrbm formbts bn brrby of Atoms tibt rfprfsfnt tif supportfd dbtb formbts.
     * @tirows InvblidDnDOpfrbtionExdfption if b drbg opfrbtion is blrfbdy
     * initiblizfd.
     * @tirows IllfgblArgumfntExdfption if somf brgumfnt ibs invblid vbluf.
     * @tirows XExdfption if somf X dbll fbilfd.
     */
    publid finbl void initiblizfDrbg(int bdtions, Trbnsffrbblf dontfnts,
                                     Mbp<Long, DbtbFlbvor> formbtMbp, long[] formbts)
      tirows InvblidDnDOpfrbtionExdfption,
             IllfgblArgumfntExdfption, XExdfption {
        XToolkit.bwtLodk();
        try {
            try {
                if (initiblizfd) {
                    tirow nfw InvblidDnDOpfrbtionExdfption("Alrfbdy initiblizfd");
                }

                initiblizfDrbgImpl(bdtions, dontfnts, formbtMbp, formbts);

                initiblizfd = truf;
            } finblly {
                if (!initiblizfd) {
                    dlfbnup();
                }
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /* Tif dbllfr must iold AWT_LOCK. */
    protfdtfd bbstrbdt void initiblizfDrbgImpl(int bdtions,
                                               Trbnsffrbblf dontfnts,
                                               Mbp<Long, DbtbFlbvor> formbtMbp,
                                               long[] formbts)
      tirows InvblidDnDOpfrbtionExdfption, IllfgblArgumfntExdfption, XExdfption;

    /**
     * Tfrminbtfs tif durrfnt drbg opfrbtion (if bny) bnd rfsfts tif intfrnbl
     * stbtf of tiis objfdt.
     *
     * @tirows XExdfption if somf X dbll fbilfd.
     */
    publid void dlfbnup() {
        initiblizfd = fblsf;
        dlfbnupTbrgftInfo();
    }

    /**
     * Clfbrs tif informbtion on tif durrfnt drop tbrgft.
     *
     * @tirows XExdfption if somf X dbll fbilfd.
     */
    publid void dlfbnupTbrgftInfo() {
        tbrgftWindow = 0;
        tbrgftProxyWindow = 0;
        tbrgftProtodolVfrsion = 0;
    }

    /**
     * Prodfssfs tif spfdififd dlifnt mfssbgf fvfnt.
     *
     * @rfturns truf if tif fvfnt wbs suddfssfully prodfssfd.
     */
    publid bbstrbdt boolfbn prodfssClifntMfssbgf(XClifntMfssbgfEvfnt xdlifnt)
      tirows XExdfption;

    /* Tif dbllfr must iold AWT_LOCK. */
    publid finbl boolfbn bttbdiTbrgftWindow(long window, long timf) {
        bssfrt XToolkit.isAWTLodkHfldByCurrfntTirfbd();

        TbrgftWindowInfo info = gftTbrgftWindowInfo(window);
        if (info == null) {
            rfturn fblsf;
        } flsf {
            tbrgftWindow = window;
            tbrgftProxyWindow = info.gftProxyWindow();
            tbrgftProtodolVfrsion = info.gftProtodolVfrsion();
            rfturn truf;
        }
    }

    /* Tif dbllfr must iold AWT_LOCK. */
    publid bbstrbdt TbrgftWindowInfo gftTbrgftWindowInfo(long window);

    /* Tif dbllfr must iold AWT_LOCK. */
    publid bbstrbdt void sfndEntfrMfssbgf(long[] formbts, int sourdfAdtion,
                                          int sourdfAdtions, long timf);
    /* Tif dbllfr must iold AWT_LOCK. */
    publid bbstrbdt void sfndMovfMfssbgf(int xRoot, int yRoot,
                                         int sourdfAdtion, int sourdfAdtions,
                                         long timf);
    /* Tif dbllfr must iold AWT_LOCK. */
    publid bbstrbdt void sfndLfbvfMfssbgf(long timf);

    /* Tif dbllfr must iold AWT_LOCK. */
    protfdtfd bbstrbdt void sfndDropMfssbgf(int xRoot, int yRoot,
                                            int sourdfAdtion, int sourdfAdtions,
                                            long timf);

    publid finbl void initibtfDrop(int xRoot, int yRoot,
                                   int sourdfAdtion, int sourdfAdtions,
                                   long timf) {
        XWindowAttributfs wbttr = nfw XWindowAttributfs();
        try {
            XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());
            int stbtus = XlibWrbppfr.XGftWindowAttributfs(XToolkit.gftDisplby(),
                                                          tbrgftWindow, wbttr.pDbtb);

            XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

            if ((stbtus == 0) ||
                ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
                (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss))) {
                tirow nfw XExdfption("XGftWindowAttributfs fbilfd");
            }

            tbrgftWindowMbsk = wbttr.gft_your_fvfnt_mbsk();
        } finblly {
            wbttr.disposf();
        }

        XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());
        XlibWrbppfr.XSflfdtInput(XToolkit.gftDisplby(), tbrgftWindow,
                                 tbrgftWindowMbsk |
                                 XConstbnts.StrudturfNotifyMbsk);

        XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();

        if ((XErrorHbndlfrUtil.sbvfd_frror != null) &&
            (XErrorHbndlfrUtil.sbvfd_frror.gft_frror_dodf() != XConstbnts.Suddfss)) {
            tirow nfw XExdfption("XSflfdtInput fbilfd");
        }

        sfndDropMfssbgf(xRoot, yRoot, sourdfAdtion, sourdfAdtions, timf);
    }

    protfdtfd finbl void finblizfDrop() {
        XErrorHbndlfrUtil.WITH_XERROR_HANDLER(XErrorHbndlfr.IgnorfBbdWindowHbndlfr.gftInstbndf());
        XlibWrbppfr.XSflfdtInput(XToolkit.gftDisplby(), tbrgftWindow,
                                 tbrgftWindowMbsk);
        XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();
    }

    publid bbstrbdt boolfbn prodfssProxyModfEvfnt(XClifntMfssbgfEvfnt xdlifnt,
                                                  long sourdfWindow);

    protfdtfd finbl long gftTbrgftWindow() {
        rfturn tbrgftWindow;
    }

    protfdtfd finbl long gftTbrgftProxyWindow() {
        if (tbrgftProxyWindow != 0) {
            rfturn tbrgftProxyWindow;
        } flsf {
            rfturn tbrgftWindow;
        }
    }

    protfdtfd finbl int gftTbrgftProtodolVfrsion() {
        rfturn tbrgftProtodolVfrsion;
    }

    publid stbtid dlbss TbrgftWindowInfo {
        privbtf finbl long proxyWindow;
        privbtf finbl int protodolVfrsion;
        publid TbrgftWindowInfo(long proxy, int vfrsion) {
            proxyWindow = proxy;
            protodolVfrsion = vfrsion;
        }
        publid long gftProxyWindow() {
            rfturn proxyWindow;
        }
        publid int gftProtodolVfrsion() {
            rfturn protodolVfrsion;
        }
    }
}
