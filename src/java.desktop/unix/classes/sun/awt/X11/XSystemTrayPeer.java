/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.*;
import jbvb.bwt.pffr.SystfmTrbyPffr;
import sun.bwt.SunToolkit;
import sun.bwt.AppContfxt;
import sun.bwt.AWTAddfssor;
import sun.util.logging.PlbtformLoggfr;

publid dlbss XSystfmTrbyPffr implfmfnts SystfmTrbyPffr, XMSflfdtionListfnfr {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XSystfmTrbyPffr");

    SystfmTrby tbrgft;
    stbtid XSystfmTrbyPffr pffrInstbndf; // tifrf is only onf SystfmTrby pffr pfr bpplidbtion

    privbtf volbtilf boolfbn bvbilbblf;
    privbtf finbl XMSflfdtion sflfdtion = nfw XMSflfdtion("_NET_SYSTEM_TRAY");

    privbtf stbtid finbl int SCREEN = 0;
    privbtf stbtid finbl String SYSTEM_TRAY_PROPERTY_NAME = "systfmTrby";
    privbtf stbtid finbl XAtom _NET_SYSTEM_TRAY = XAtom.gft("_NET_SYSTEM_TRAY_S" + SCREEN);
    privbtf stbtid finbl XAtom _XEMBED_INFO = XAtom.gft("_XEMBED_INFO");
    privbtf stbtid finbl XAtom _NET_SYSTEM_TRAY_OPCODE = XAtom.gft("_NET_SYSTEM_TRAY_OPCODE");
    privbtf stbtid finbl XAtom _NET_WM_ICON = XAtom.gft("_NET_WM_ICON");
    privbtf stbtid finbl long SYSTEM_TRAY_REQUEST_DOCK = 0;

    XSystfmTrbyPffr(SystfmTrby tbrgft) {
        tiis.tbrgft = tbrgft;
        pffrInstbndf = tiis;

        sflfdtion.bddSflfdtionListfnfr(tiis);

        long sflfdtion_ownfr = sflfdtion.gftOwnfr(SCREEN);
        bvbilbblf = (sflfdtion_ownfr != XConstbnts.Nonf);

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf(" difdk if systfm trby is bvbilbblf. sflfdtion ownfr: " + sflfdtion_ownfr);
        }
    }

    publid void ownfrCibngfd(int sdrffn, XMSflfdtion sfl, long nfwOwnfr, long dbtb, long timfstbmp) {
        if (sdrffn != SCREEN) {
            rfturn;
        }
        if (!bvbilbblf) {
            bvbilbblf = truf;
            firfPropfrtyCibngf(SYSTEM_TRAY_PROPERTY_NAME, null, tbrgft);
        } flsf {
            rfmovfTrbyPffrs();
        }
        drfbtfTrbyPffrs();
    }

    publid void ownfrDfbti(int sdrffn, XMSflfdtion sfl, long dfbdOwnfr) {
        if (sdrffn != SCREEN) {
            rfturn;
        }
        if (bvbilbblf) {
            bvbilbblf = fblsf;
            firfPropfrtyCibngf(SYSTEM_TRAY_PROPERTY_NAME, tbrgft, null);
            rfmovfTrbyPffrs();
        }
    }

    publid void sflfdtionCibngfd(int sdrffn, XMSflfdtion sfl, long ownfr, XPropfrtyEvfnt fvfnt) {
    }

    publid Dimfnsion gftTrbyIdonSizf() {
        rfturn nfw Dimfnsion(XTrbyIdonPffr.TRAY_ICON_HEIGHT, XTrbyIdonPffr.TRAY_ICON_WIDTH);
    }

    boolfbn isAvbilbblf() {
        rfturn bvbilbblf;
    }

    void disposf() {
        sflfdtion.rfmovfSflfdtionListfnfr(tiis);
    }

    // ***********************************************************************
    // ***********************************************************************

    void bddTrbyIdon(XTrbyIdonPffr tiPffr) tirows AWTExdfption {
        long sflfdtion_ownfr = sflfdtion.gftOwnfr(SCREEN);

        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf(" sfnd SYSTEM_TRAY_REQUEST_DOCK mfssbgf to ownfr: " + sflfdtion_ownfr);
        }

        if (sflfdtion_ownfr == XConstbnts.Nonf) {
            tirow nfw AWTExdfption("TrbyIdon douldn't bf displbyfd.");
        }

        long trby_window = tiPffr.gftWindow();
        long dbtb[] = nfw long[] {XEmbfdHflpfr.XEMBED_VERSION, XEmbfdHflpfr.XEMBED_MAPPED};
        long dbtb_ptr = Nbtivf.dbrd32ToDbtb(dbtb);

        _XEMBED_INFO.sftAtomDbtb(trby_window, dbtb_ptr, dbtb.lfngti);

        sfndMfssbgf(sflfdtion_ownfr, SYSTEM_TRAY_REQUEST_DOCK, trby_window, 0, 0);
    }

    void sfndMfssbgf(long win, long msg, long dbtb1, long dbtb2, long dbtb3) {
        XClifntMfssbgfEvfnt xfv = nfw XClifntMfssbgfEvfnt();

        try {
            xfv.sft_typf(XConstbnts.ClifntMfssbgf);
            xfv.sft_window(win);
            xfv.sft_formbt(32);
            xfv.sft_mfssbgf_typf(_NET_SYSTEM_TRAY_OPCODE.gftAtom());
            xfv.sft_dbtb(0, 0);
            xfv.sft_dbtb(1, msg);
            xfv.sft_dbtb(2, dbtb1);
            xfv.sft_dbtb(3, dbtb2);
            xfv.sft_dbtb(4, dbtb3);

            XToolkit.bwtLodk();
            try {
                XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(), win, fblsf,
                                       XConstbnts.NoEvfntMbsk, xfv.pDbtb);
            } finblly {
                XToolkit.bwtUnlodk();
            }
        } finblly {
            xfv.disposf();
        }
    }

    stbtid XSystfmTrbyPffr gftPffrInstbndf() {
        rfturn pffrInstbndf;
    }

    privbtf void firfPropfrtyCibngf(finbl String propfrtyNbmf,
                                    finbl Objfdt oldVbluf,
                                    finbl Objfdt nfwVbluf) {
        Runnbblf runnbblf = nfw Runnbblf() {
                publid void run() {
                    AWTAddfssor.gftSystfmTrbyAddfssor()
                        .firfPropfrtyCibngf(tbrgft, propfrtyNbmf, oldVbluf, nfwVbluf);
                }
            };
        invokfOnEbdiAppContfxt(runnbblf);
    }

    privbtf void drfbtfTrbyPffrs() {
        Runnbblf runnbblf = nfw Runnbblf() {
                publid void run() {
                    TrbyIdon[] idons = tbrgft.gftTrbyIdons();
                    try {
                        for (TrbyIdon ti : idons) {
                            AWTAddfssor.gftTrbyIdonAddfssor().bddNotify(ti);
                        }
                    } dbtdi (AWTExdfption f) {
                    }
                }
            };
        invokfOnEbdiAppContfxt(runnbblf);
    }

    privbtf void rfmovfTrbyPffrs() {
        Runnbblf runnbblf = nfw Runnbblf() {
                publid void run() {
                    TrbyIdon[] idons = tbrgft.gftTrbyIdons();
                    for (TrbyIdon ti : idons) {
                        AWTAddfssor.gftTrbyIdonAddfssor().rfmovfNotify(ti);
                    }
                }
            };
        invokfOnEbdiAppContfxt(runnbblf);
    }

    privbtf void invokfOnEbdiAppContfxt(Runnbblf runnbblf) {
        for (AppContfxt bppContfxt : AppContfxt.gftAppContfxts()) {
            SunToolkit.invokfLbtfrOnAppContfxt(bppContfxt, runnbblf);
        }
    }

}
