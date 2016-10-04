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

import jbvb.bwt.Componfnt;
import jbvb.bwt.pffr.ComponfntPffr;

import jbvb.io.IOExdfption;

import jbvb.util.Itfrbtor;

import sun.util.logging.PlbtformLoggfr;

import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;

import sun.bwt.dnd.SunDropTbrgftContfxtPffr;
import sun.bwt.dnd.SunDropTbrgftEvfnt;

import sun.misd.Unsbff;

/**
 * Tif XDropTbrgftContfxtPffr is tif dlbss rfsponsiblf for ibndling
 * tif intfrbdtion bftwffn tif XDnD/Motif DnD subsystfm bnd Jbvb drop tbrgfts.
 *
 * @sindf 1.5
 */
finbl dlbss XDropTbrgftContfxtPffr fxtfnds SunDropTbrgftContfxtPffr {
    privbtf stbtid finbl PlbtformLoggfr loggfr =
        PlbtformLoggfr.gftLoggfr("sun.bwt.X11.xfmbfd.xdnd.XDropTbrgftContfxtPffr");

    privbtf stbtid finbl Unsbff unsbff = XlibWrbppfr.unsbff;

    /*
     * A kfy to storf b pffr instbndf for bn AppContfxt.
     */
    privbtf stbtid finbl Objfdt DTCP_KEY = "DropTbrgftContfxtPffr";

    privbtf XDropTbrgftContfxtPffr() {}

    stbtid XDropTbrgftContfxtPffr gftPffr(AppContfxt bppContfxt) {
        syndironizfd (_globblLodk) {
            XDropTbrgftContfxtPffr pffr =
                (XDropTbrgftContfxtPffr)bppContfxt.gft(DTCP_KEY);
            if (pffr == null) {
                pffr = nfw XDropTbrgftContfxtPffr();
                bppContfxt.put(DTCP_KEY, pffr);
            }

            rfturn pffr;
        }
    }

    stbtid XDropTbrgftProtodolListfnfr gftXDropTbrgftProtodolListfnfr() {
        rfturn XDropTbrgftProtodolListfnfrImpl.gftInstbndf();
    }

    /*
     * @pbrbm rfturnVbluf tif drop bdtion sflfdtfd by tif Jbvb drop tbrgft.
     */
    protfdtfd void fvfntProdfssfd(SunDropTbrgftEvfnt f, int rfturnVbluf,
                                  boolfbn dispbtdifrDonf) {
        /* Tif nbtivf dontfxt is tif pointfr to tif XClifntMfssbgfEvfnt
           strudturf. */
        long dtxt = gftNbtivfDrbgContfxt();
        /* If tif fvfnt wbs not donsumfd, sfnd b rfsponsf to tif sourdf. */
        try {
            if (dtxt != 0 && !f.isConsumfd()) {
                Itfrbtor<XDropTbrgftProtodol> dropTbrgftProtodols =
                    XDrbgAndDropProtodols.gftDropTbrgftProtodols();

                wiilf (dropTbrgftProtodols.ibsNfxt()) {
                    XDropTbrgftProtodol dropTbrgftProtodol =
                        dropTbrgftProtodols.nfxt();
                    if (dropTbrgftProtodol.sfndRfsponsf(dtxt, f.gftID(),
                                                        rfturnVbluf)) {
                        brfbk;
                    }
                }
            }
        } finblly {
            if (dispbtdifrDonf && dtxt != 0) {
                unsbff.frffMfmory(dtxt);
            }
        }
    }

    protfdtfd void doDropDonf(boolfbn suddfss, int dropAdtion,
                              boolfbn isLodbl) {
        /* Tif nbtivf dontfxt is tif pointfr to tif XClifntMfssbgfEvfnt
           strudturf. */
        long dtxt = gftNbtivfDrbgContfxt();

        if (dtxt != 0) {
            try {
                Itfrbtor<XDropTbrgftProtodol> dropTbrgftProtodols =
                    XDrbgAndDropProtodols.gftDropTbrgftProtodols();

                wiilf (dropTbrgftProtodols.ibsNfxt()) {
                    XDropTbrgftProtodol dropTbrgftProtodol =
                        dropTbrgftProtodols.nfxt();
                    if (dropTbrgftProtodol.sfndDropDonf(dtxt, suddfss,
                                                        dropAdtion)) {
                        brfbk;
                    }
                }
            } finblly {
                unsbff.frffMfmory(dtxt);
            }
        }
    }

    protfdtfd Objfdt gftNbtivfDbtb(long formbt)
      tirows IOExdfption {
        /* Tif nbtivf dontfxt is tif pointfr to tif XClifntMfssbgfEvfnt
           strudturf. */
        long dtxt = gftNbtivfDrbgContfxt();

        if (dtxt != 0) {
            Itfrbtor<XDropTbrgftProtodol> dropTbrgftProtodols =
                XDrbgAndDropProtodols.gftDropTbrgftProtodols();

            wiilf (dropTbrgftProtodols.ibsNfxt()) {
                XDropTbrgftProtodol dropTbrgftProtodol =
                    dropTbrgftProtodols.nfxt();
                // gftDbtb tirows IAE if dtxt is not for tiis protodol.
                try {
                    rfturn dropTbrgftProtodol.gftDbtb(dtxt, formbt);
                } dbtdi (IllfgblArgumfntExdfption ibf) {
                }
            }
        }

        rfturn null;
    }

    privbtf void dlfbnup() {
    }

    protfdtfd void prodfssEntfrMfssbgf(SunDropTbrgftEvfnt fvfnt) {
        if (!prodfssSunDropTbrgftEvfnt(fvfnt)) {
            supfr.prodfssEntfrMfssbgf(fvfnt);
        }
    }

    protfdtfd void prodfssExitMfssbgf(SunDropTbrgftEvfnt fvfnt) {
        if (!prodfssSunDropTbrgftEvfnt(fvfnt)) {
            supfr.prodfssExitMfssbgf(fvfnt);
        }
    }

    protfdtfd void prodfssMotionMfssbgf(SunDropTbrgftEvfnt fvfnt,
                                        boolfbn opfrbtionCibngfd) {
        if (!prodfssSunDropTbrgftEvfnt(fvfnt)) {
            supfr.prodfssMotionMfssbgf(fvfnt, opfrbtionCibngfd);
        }
    }

    protfdtfd void prodfssDropMfssbgf(SunDropTbrgftEvfnt fvfnt) {
        if (!prodfssSunDropTbrgftEvfnt(fvfnt)) {
            supfr.prodfssDropMfssbgf(fvfnt);
        }
    }

    // If sourdf is bn XEmbfdCbnvbsPffr, pbssfs tif fvfnt to it for prodfssing bnd
    // rfturn truf if tif fvfnt is forwbrdfd to tif XEmbfd diild.
    // Otifrwisf, dofs notiing bnd rfturn fblsf.
    privbtf boolfbn prodfssSunDropTbrgftEvfnt(SunDropTbrgftEvfnt fvfnt) {
        Objfdt sourdf = fvfnt.gftSourdf();

        if (sourdf instbndfof Componfnt) {
            ComponfntPffr pffr = ((Componfnt)sourdf).gftPffr();
            if (pffr instbndfof XEmbfdCbnvbsPffr) {
                XEmbfdCbnvbsPffr xEmbfdCbnvbsPffr = (XEmbfdCbnvbsPffr)pffr;
                /* Tif nbtivf dontfxt is tif pointfr to tif XClifntMfssbgfEvfnt
                   strudturf. */
                long dtxt = gftNbtivfDrbgContfxt();

                if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                    loggfr.finfr("        prodfssing " + fvfnt + " dtxt=" + dtxt +
                                 " donsumfd=" + fvfnt.isConsumfd());
                }
                /* If tif fvfnt is not donsumfd, pbss it to tif
                   XEmbfdCbnvbsPffr for prodfssing. */
                if (!fvfnt.isConsumfd()) {
                    // NOTE: dtxt dbn bf zfro bt tiis point.
                    if (xEmbfdCbnvbsPffr.prodfssXEmbfdDnDEvfnt(dtxt,
                                                               fvfnt.gftID())) {
                        fvfnt.donsumf();
                        rfturn truf;
                    }
                }
            }
        }

        rfturn fblsf;
    }

    publid void forwbrdEvfntToEmbfddfd(long fmbfddfd, long dtxt,
                                       int fvfntID) {
        Itfrbtor<XDropTbrgftProtodol> dropTbrgftProtodols =
            XDrbgAndDropProtodols.gftDropTbrgftProtodols();

        wiilf (dropTbrgftProtodols.ibsNfxt()) {
            XDropTbrgftProtodol dropTbrgftProtodol = dropTbrgftProtodols.nfxt();
            if (dropTbrgftProtodol.forwbrdEvfntToEmbfddfd(fmbfddfd, dtxt,
                                                          fvfntID)) {
                brfbk;
            }
        }
    }

    stbtid finbl dlbss XDropTbrgftProtodolListfnfrImpl
        implfmfnts XDropTbrgftProtodolListfnfr {

        privbtf finbl stbtid XDropTbrgftProtodolListfnfr tifInstbndf =
            nfw XDropTbrgftProtodolListfnfrImpl();

        privbtf XDropTbrgftProtodolListfnfrImpl() {}

        stbtid XDropTbrgftProtodolListfnfr gftInstbndf() {
            rfturn tifInstbndf;
        }

        publid void ibndlfDropTbrgftNotifidbtion(XWindow xwindow, int x, int y,
                                                 int dropAdtion, int bdtions,
                                                 long[] formbts, long nbtivfCtxt,
                                                 int fvfntID) {
            Objfdt tbrgft = xwindow.gftTbrgft();

            // Tif Evfry domponfnt is bssodibtfd witi somf AppContfxt.
            bssfrt tbrgft instbndfof Componfnt;

            Componfnt domponfnt = (Componfnt)tbrgft;

            AppContfxt bppContfxt = SunToolkit.tbrgftToAppContfxt(tbrgft);

            // Evfry domponfnt is bssodibtfd witi somf AppContfxt.
            bssfrt bppContfxt != null;

            XDropTbrgftContfxtPffr pffr = XDropTbrgftContfxtPffr.gftPffr(bppContfxt);

            pffr.postDropTbrgftEvfnt(domponfnt, x, y, dropAdtion, bdtions, formbts,
                                     nbtivfCtxt, fvfntID,
                                     !SunDropTbrgftContfxtPffr.DISPATCH_SYNC);
        }
    }
}
