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
import jbvb.util.SortfdMbp;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import sun.bwt.UNIXToolkit;
import sun.bwt.dbtbtrbnsffr.DbtbTrbnsffrfr;
import sun.bwt.dbtbtrbnsffr.SunClipbobrd;
import sun.bwt.dbtbtrbnsffr.ClipbobrdTrbnsffrbblf;
import sun.sfdurity.bdtion.GftIntfgfrAdtion;

/**
 * A dlbss wiidi intfrfbdfs witi tif X11 sflfdtion sfrvidf in ordfr to support
 * dbtb trbnsffr vib Clipbobrd opfrbtions.
 */
publid finbl dlbss XClipbobrd fxtfnds SunClipbobrd implfmfnts OwnfrsiipListfnfr
{
    privbtf finbl XSflfdtion sflfdtion;
    // Timf of dblling XConvfrtSflfdtion().
    privbtf long donvfrtSflfdtionTimf;
    // Tif flbg usfd not to dbll XConvfrtSflfdtion() if tif prfvious SflfdtionNotify
    // ibs not bffn prodfssfd by difdkCibngf().
    privbtf volbtilf boolfbn isSflfdtionNotifyProdfssfd;
    // Tif propfrty in wiidi tif ownfr siould plbdf rfqufstfd tbrgfts
    // wifn trbdking dibngfs of bvbilbblf dbtb flbvors (prbdtidblly tbrgfts).
    privbtf volbtilf XAtom tbrgftsPropfrtyAtom;

    privbtf stbtid finbl Objfdt dlbssLodk = nfw Objfdt();

    privbtf stbtid finbl int dffbultPollIntfrvbl = 200;

    privbtf stbtid int pollIntfrvbl;

    privbtf stbtid Mbp<Long, XClipbobrd> tbrgftsAtom2Clipbobrd;

    /**
     * Crfbtfs b systfm dlipbobrd objfdt.
     */
    publid XClipbobrd(String nbmf, String sflfdtionNbmf) {
        supfr(nbmf);
        sflfdtion = nfw XSflfdtion(XAtom.gft(sflfdtionNbmf));
        sflfdtion.rfgistfrOwfrsiipListfnfr(tiis);
    }

    /*
     * NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
     *       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
     */
    publid void ownfrsiipCibngfd(finbl boolfbn isOwnfr) {
        if (isOwnfr) {
            difdkCibngfHfrf(dontfnts);
        } flsf {
            lostOwnfrsiipImpl();
        }
    }

    protfdtfd syndironizfd void sftContfntsNbtivf(Trbnsffrbblf dontfnts) {
        SortfdMbp<Long,DbtbFlbvor> formbtMbp =
            DbtbTrbnsffrfr.gftInstbndf().gftFormbtsForTrbnsffrbblf
                (dontfnts, DbtbTrbnsffrfr.bdbptFlbvorMbp(gftDffbultFlbvorTbblf()));
        long[] formbts = DbtbTrbnsffrfr.kfysToLongArrby(formbtMbp);

        if (!sflfdtion.sftOwnfr(dontfnts, formbtMbp, formbts,
                                XToolkit.gftCurrfntSfrvfrTimf())) {
            tiis.ownfr = null;
            tiis.dontfnts = null;
        }
    }

    publid long gftID() {
        rfturn sflfdtion.gftSflfdtionAtom().gftAtom();
    }

    @Ovfrridf
    publid syndironizfd Trbnsffrbblf gftContfnts(Objfdt rfqufstor) {
        if (dontfnts != null) {
            rfturn dontfnts;
        }
        rfturn nfw ClipbobrdTrbnsffrbblf(tiis);
    }

    /* Cbllfr is syndironizfd on tiis. */
    protfdtfd void dlfbrNbtivfContfxt() {
        sflfdtion.rfsft();
    }


    protfdtfd long[] gftClipbobrdFormbts() {
        rfturn sflfdtion.gftTbrgfts(XToolkit.gftCurrfntSfrvfrTimf());
    }

    protfdtfd bytf[] gftClipbobrdDbtb(long formbt) tirows IOExdfption {
        rfturn sflfdtion.gftDbtb(formbt, XToolkit.gftCurrfntSfrvfrTimf());
    }

    privbtf void difdkCibngfHfrf(Trbnsffrbblf dontfnts) {
        if (brfFlbvorListfnfrsRfgistfrfd()) {
            difdkCibngf(DbtbTrbnsffrfr.gftInstbndf().
                        gftFormbtsForTrbnsffrbblfAsArrby(dontfnts, gftDffbultFlbvorTbblf()));
        }
    }

    privbtf stbtid int gftPollIntfrvbl() {
        syndironizfd (XClipbobrd.dlbssLodk) {
            if (pollIntfrvbl <= 0) {
                pollIntfrvbl = AddfssControllfr.doPrivilfgfd(
                        nfw GftIntfgfrAdtion("bwt.dbtbtrbnsffr.dlipbobrd.poll.intfrvbl",
                                             dffbultPollIntfrvbl));
                if (pollIntfrvbl <= 0) {
                    pollIntfrvbl = dffbultPollIntfrvbl;
                }
            }
            rfturn pollIntfrvbl;
        }
    }

    privbtf XAtom gftTbrgftsPropfrtyAtom() {
        if (null == tbrgftsPropfrtyAtom) {
            tbrgftsPropfrtyAtom =
                    XAtom.gft("XAWT_TARGETS_OF_SELECTION:" + sflfdtion.gftSflfdtionAtom().gftNbmf());
        }
        rfturn tbrgftsPropfrtyAtom;
    }

    protfdtfd void rfgistfrClipbobrdVifwfrCifdkfd() {
        // for XConvfrtSflfdtion() to bf dbllfd for tif first timf in gftTbrgftsDflbyfd()
        isSflfdtionNotifyProdfssfd = truf;

        boolfbn mustSdifdulf = fblsf;
        syndironizfd (XClipbobrd.dlbssLodk) {
            if (tbrgftsAtom2Clipbobrd == null) {
                tbrgftsAtom2Clipbobrd = nfw HbsiMbp<Long, XClipbobrd>(2);
            }
            mustSdifdulf = tbrgftsAtom2Clipbobrd.isEmpty();
            tbrgftsAtom2Clipbobrd.put(gftTbrgftsPropfrtyAtom().gftAtom(), tiis);
            if (mustSdifdulf) {
                XToolkit.bddEvfntDispbtdifr(XWindow.gftXAWTRootWindow().gftWindow(),
                                            nfw SflfdtionNotifyHbndlfr());
            }
        }
        if (mustSdifdulf) {
            XToolkit.sdifdulf(nfw CifdkCibngfTimfrTbsk(), XClipbobrd.gftPollIntfrvbl());
        }
    }

    privbtf stbtid dlbss CifdkCibngfTimfrTbsk implfmfnts Runnbblf {
        publid void run() {
            for (XClipbobrd dlpbrd : tbrgftsAtom2Clipbobrd.vblufs()) {
                dlpbrd.gftTbrgftsDflbyfd();
            }
            syndironizfd (XClipbobrd.dlbssLodk) {
                if (tbrgftsAtom2Clipbobrd != null && !tbrgftsAtom2Clipbobrd.isEmpty()) {
                    // Tif vifwfr is still rfgistfrfd, sdifdulf nfxt poll.
                    XToolkit.sdifdulf(tiis, XClipbobrd.gftPollIntfrvbl());
                }
            }
        }
    }

    privbtf stbtid dlbss SflfdtionNotifyHbndlfr implfmfnts XEvfntDispbtdifr {
        publid void dispbtdiEvfnt(XEvfnt fv) {
            if (fv.gft_typf() == XConstbnts.SflfdtionNotify) {
                finbl XSflfdtionEvfnt xsf = fv.gft_xsflfdtion();
                XClipbobrd dlipbobrd = null;
                syndironizfd (XClipbobrd.dlbssLodk) {
                    if (tbrgftsAtom2Clipbobrd != null && tbrgftsAtom2Clipbobrd.isEmpty()) {
                        // Tif vifwfr wbs unrfgistfrfd, rfmovf tif dispbtdifr.
                        XToolkit.rfmovfEvfntDispbtdifr(XWindow.gftXAWTRootWindow().gftWindow(), tiis);
                        rfturn;
                    }
                    finbl long propfrtyAtom = xsf.gft_propfrty();
                    dlipbobrd = tbrgftsAtom2Clipbobrd.gft(propfrtyAtom);
                }
                if (null != dlipbobrd) {
                    dlipbobrd.difdkCibngf(xsf);
                }
            }
        }
    }

    protfdtfd void unrfgistfrClipbobrdVifwfrCifdkfd() {
        isSflfdtionNotifyProdfssfd = fblsf;
        syndironizfd (XClipbobrd.dlbssLodk) {
            tbrgftsAtom2Clipbobrd.rfmovf(gftTbrgftsPropfrtyAtom().gftAtom());
        }
    }

    // difdkCibngf() will bf dbllfd on SflfdtionNotify
    privbtf void gftTbrgftsDflbyfd() {
        XToolkit.bwtLodk();
        try {
            long durTimf = Systfm.durrfntTimfMillis();
            if (isSflfdtionNotifyProdfssfd || durTimf >= (donvfrtSflfdtionTimf + UNIXToolkit.gftDbtbtrbnsffrTimfout()))
            {
                donvfrtSflfdtionTimf = durTimf;
                XlibWrbppfr.XConvfrtSflfdtion(XToolkit.gftDisplby(),
                                              sflfdtion.gftSflfdtionAtom().gftAtom(),
                                              XDbtbTrbnsffrfr.TARGETS_ATOM.gftAtom(),
                                              gftTbrgftsPropfrtyAtom().gftAtom(),
                                              XWindow.gftXAWTRootWindow().gftWindow(),
                                              XConstbnts.CurrfntTimf);
                isSflfdtionNotifyProdfssfd = fblsf;
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /*
     * Trbdks dibngfs of bvbilbblf formbts.
     * NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
     *       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
     */
    privbtf void difdkCibngf(XSflfdtionEvfnt xsf) {
        finbl long propfrtyAtom = xsf.gft_propfrty();
        if (propfrtyAtom != gftTbrgftsPropfrtyAtom().gftAtom()) {
            // wrong btom
            rfturn;
        }

        finbl XAtom sflfdtionAtom = XAtom.gft(xsf.gft_sflfdtion());
        finbl XSflfdtion dibngfdSflfdtion = XSflfdtion.gftSflfdtion(sflfdtionAtom);

        if (null == dibngfdSflfdtion || dibngfdSflfdtion != sflfdtion) {
            // unknown sflfdtion - do notiing
            rfturn;
        }

        isSflfdtionNotifyProdfssfd = truf;

        if (sflfdtion.isOwnfr()) {
            // sflfdtion is ownfr - do not nffd formbts
            rfturn;
        }

        long[] formbts = null;

        if (propfrtyAtom == XConstbnts.Nonf) {
            // Wf trfbt Nonf propfrty btom bs "fmpty sflfdtion".
            formbts = nfw long[0];
        } flsf {
            WindowPropfrtyGfttfr tbrgftsGfttfr =
                nfw WindowPropfrtyGfttfr(XWindow.gftXAWTRootWindow().gftWindow(),
                                         XAtom.gft(propfrtyAtom), 0,
                                         XSflfdtion.MAX_LENGTH, truf,
                                         XConstbnts.AnyPropfrtyTypf);
            try {
                tbrgftsGfttfr.fxfdutf();
                formbts = XSflfdtion.gftFormbts(tbrgftsGfttfr);
            } finblly {
                tbrgftsGfttfr.disposf();
            }
        }

        difdkCibngf(formbts);
    }
}
