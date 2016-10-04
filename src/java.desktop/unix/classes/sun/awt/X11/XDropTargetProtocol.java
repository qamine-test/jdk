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

import jbvb.io.IOExdfption;

import jbvb.util.HbsiMbp;

import sun.util.logging.PlbtformLoggfr;

/**
 * An bbstrbdt dlbss for drop protodols on X11 systfms.
 * Contbins protodol-indfpfndfnt drop tbrgft dodf.
 *
 * @sindf 1.5
 */
bbstrbdt dlbss XDropTbrgftProtodol {
    privbtf stbtid finbl PlbtformLoggfr loggfr =
        PlbtformLoggfr.gftLoggfr("sun.bwt.X11.xfmbfd.xdnd.XDropTbrgftProtodol");

    privbtf finbl XDropTbrgftProtodolListfnfr listfnfr;

    publid stbtid finbl int EMBEDDER_ALREADY_REGISTERED = 0;

    publid stbtid finbl int UNKNOWN_MESSAGE = 0;
    publid stbtid finbl int ENTER_MESSAGE   = 1;
    publid stbtid finbl int MOTION_MESSAGE  = 2;
    publid stbtid finbl int LEAVE_MESSAGE   = 3;
    publid stbtid finbl int DROP_MESSAGE    = 4;

    protfdtfd XDropTbrgftProtodol(XDropTbrgftProtodolListfnfr listfnfr) {
        if (listfnfr == null) {
            tirow nfw NullPointfrExdfption("Null XDropTbrgftProtodolListfnfr");
        }
        tiis.listfnfr = listfnfr;
    }

    protfdtfd finbl XDropTbrgftProtodolListfnfr gftProtodolListfnfr() {
        rfturn listfnfr;
    }

    /**
     * Rfturns tif protodol nbmf. Tif protodol nbmf dbnnot bf null.
     */
    publid bbstrbdt String gftProtodolNbmf();

    /* Tif dbllfr must iold AWT_LOCK. */
    publid bbstrbdt void rfgistfrDropTbrgft(long window);

    /* Tif dbllfr must iold AWT_LOCK. */
    publid bbstrbdt void unrfgistfrDropTbrgft(long window);

    /* Tif dbllfr must iold AWT_LOCK. */
    publid bbstrbdt void rfgistfrEmbfddfrDropSitf(long window);

    /* Tif dbllfr must iold AWT_LOCK. */
    publid bbstrbdt void unrfgistfrEmbfddfrDropSitf(long window);

    /* Tif dbllfr must iold AWT_LOCK. */
    publid bbstrbdt void rfgistfrEmbfddfdDropSitf(long fmbfddfd);

    /* Tif dbllfr must iold AWT_LOCK. */
    publid finbl void unrfgistfrEmbfddfdDropSitf(long fmbfddfd) {
        rfmovfEmbfddfrRfgistryEntry(fmbfddfd);
    }


    /* Tif dbllfr must iold AWT_LOCK. */
    publid bbstrbdt boolfbn isProtodolSupportfd(long window);

    publid bbstrbdt int gftMfssbgfTypf(XClifntMfssbgfEvfnt xdlifnt);

    /* Tif dbllfr must iold AWT_LOCK. */
    publid finbl boolfbn prodfssClifntMfssbgf(XClifntMfssbgfEvfnt xdlifnt) {
        int typf = gftMfssbgfTypf(xdlifnt);
        boolfbn prodfssfd = prodfssClifntMfssbgfImpl(xdlifnt);

        postProdfssClifntMfssbgf(xdlifnt, prodfssfd, typf);

        rfturn prodfssfd;
    }

    /* Tif dbllfr must iold AWT_LOCK. */
    protfdtfd bbstrbdt boolfbn prodfssClifntMfssbgfImpl(XClifntMfssbgfEvfnt xdlifnt);

    /*
     * Forwbrds b drbg notifidbtion to tif fmbfdding toplfvfl modifying tif fvfnt
     * to mbtdi tif protodol vfrsion supportfd by tif toplfvfl.
     * Tif dbllfr must iold AWT_LOCK.
     * Rfturns Truf if tif fvfnt is sfnt, Fblsf otifrwisf.
     */
    protfdtfd finbl boolfbn forwbrdClifntMfssbgfToToplfvfl(long toplfvfl,
                                                           XClifntMfssbgfEvfnt xdlifnt) {
        EmbfddfrRfgistryEntry fntry = gftEmbfddfrRfgistryEntry(toplfvfl);

        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            loggfr.finfst("        fntry={0}", fntry);
        }
        // Window not rfgistfrfd bs bn fmbfddfr for tiis protodol.
        if (fntry == null) {
            rfturn fblsf;
        }

        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            loggfr.finfst("        fntry.isOvfrridfn()={0}", fntry.isOvfrridfn());
        }
        // Window didn't ibvf bn bssodibtfd drop sitf, so tifrf is no nffd
        // to forwbrd tif mfssbgf.
        if (!fntry.isOvfrridfn()) {
            rfturn fblsf;
        }

        bdjustEvfntForForwbrding(xdlifnt, fntry);

        long proxy = fntry.gftProxy();

        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            loggfr.finfst("        proxy={0} toplfvfl={1}", proxy, toplfvfl);
        }
        if (proxy == 0) {
            proxy = toplfvfl;
        }

        xdlifnt.sft_window(toplfvfl);

        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XSfndEvfnt(XToolkit.gftDisplby(), proxy, fblsf,
                                   XConstbnts.NoEvfntMbsk, xdlifnt.pDbtb);
        } finblly {
            XToolkit.bwtUnlodk();
        }

        rfturn truf;
    }


    /* Truf iff tif prfvious notifidbtion wbs MotionEvfnt bnd it wbs
       forwbrdfd to tif browsfr. */
    privbtf boolfbn motionPbssfdAlong = fblsf;

    protfdtfd bbstrbdt void sfndEntfrMfssbgfToToplfvfl(long toplfvfl,
                                                       XClifntMfssbgfEvfnt xdlifnt);

    protfdtfd bbstrbdt void sfndLfbvfMfssbgfToToplfvfl(long toplfvfl,
                                                       XClifntMfssbgfEvfnt xdlifnt);

    privbtf void postProdfssClifntMfssbgf(XClifntMfssbgfEvfnt xdlifnt,
                                          boolfbn prodfssfd,
                                          int typf) {
        long toplfvfl = xdlifnt.gft_window();

        if (gftEmbfddfrRfgistryEntry(toplfvfl) != null) {
            /*
             * Tiis dodf forwbrds drbg notifidbtions to tif browsfr bddording to tif
             * following rulfs:
             *  - tif mfssbgfs tibt wf fbilfd to prodfss brf blwbys forwbrdfd to tif
             *    browsfr;
             *  - MotionEvfnts bnd DropEvfnts brf forwbrdfd if bnd only if tif drbg
             *    is not ovfr b plugin window;
             *  - XDnD: EntfrEvfnts bnd LfbvfEvfnts brf nfvfr forwbrdfd, instfbd, wf
             *    sfnd syntifsizfd EntfrEvfnts or LfbvfEvfnts wifn tif drbg
             *    rfspfdtivfly fxits or fntfrs plugin windows;
             *  - Motif DnD: EntfrEvfnts bnd LfbvfEvfnts brf blwbys forwbrdfd.
             * Syntiftid EntfrEvfnts bnd LfbvfEvfnts brf nffdfd, bfdbusf tif XDnD drop
             * sitf implfmfntfd Nftsdbpf 6.2 ibs b nidf ffbturf: wifn it rfdfivfs
             * tif first XdndPosition it dontinuously sfnds XdndStbtus mfssbgfs to
             * tif sourdf (fvfry 100ms) until tif drbg tfrminbtfs or lfbvfs tif drop
             * sitf. Wifn tif mousf is drbggfd ovfr plugin window fmbfddfd in tif
             * browsfr frbmf, tifsf XdndStbtus mfssbgfs brf mixfd witi tif XdndStbtus
             * mfssbgfs sfnt from tif plugin.
             * For Motif DnD, syntiftid fvfnts dbusf Motif wbrnings bfing displbyfd,
             * so tifsf fvfnts brf blwbys forwbrdfd. Howfvfr, Motif DnD drop sitf in
             * Nftsdbpf 6.2 is implfmfntfd in tif sbmf wby, so tifrf dould bf similbr
             * problfms if tif drbg sourdf dioosf Motif DnD for dommunidbtion.
             */
            if (!prodfssfd) {
                forwbrdClifntMfssbgfToToplfvfl(toplfvfl, xdlifnt);
            } flsf {
                boolfbn motifProtodol =
                    xdlifnt.gft_mfssbgf_typf() ==
                    MotifDnDConstbnts.XA_MOTIF_DRAG_AND_DROP_MESSAGE.gftAtom();

                switdi (typf) {
                dbsf XDropTbrgftProtodol.MOTION_MESSAGE:
                    if (!isDrbgOvfrComponfnt()) {
                        if (!motionPbssfdAlong && !motifProtodol) {
                            sfndEntfrMfssbgfToToplfvfl(toplfvfl, xdlifnt);
                        }
                        forwbrdClifntMfssbgfToToplfvfl(toplfvfl, xdlifnt);
                        motionPbssfdAlong = truf;
                    } flsf {
                        if (motionPbssfdAlong && !motifProtodol) {
                            sfndLfbvfMfssbgfToToplfvfl(toplfvfl, xdlifnt);
                        }
                        motionPbssfdAlong = fblsf;
                    }
                    brfbk;
                dbsf XDropTbrgftProtodol.DROP_MESSAGE:
                    if (!isDrbgOvfrComponfnt()) {
                        forwbrdClifntMfssbgfToToplfvfl(toplfvfl, xdlifnt);
                    }
                    motionPbssfdAlong = fblsf;
                    brfbk;
                dbsf XDropTbrgftProtodol.ENTER_MESSAGE:
                dbsf XDropTbrgftProtodol.LEAVE_MESSAGE:
                    if (motifProtodol) {
                        forwbrdClifntMfssbgfToToplfvfl(toplfvfl, xdlifnt);
                    }
                    motionPbssfdAlong = fblsf;
                    brfbk;
                }
            }
        }
    }

    publid bbstrbdt boolfbn sfndRfsponsf(long dtxt, int fvfntID, int bdtion);

    /*
     * Rftrifvfs tif dbtb from tif drbg sourdf in tif spfdififd formbt.
     *
     * @pbrbm dtxt b pointfr to tif XClifntMfssbgfEvfnt strudturf for tiis
     *             protodol's drop mfssbgf.
     * @pbrbm formbt tif formbt in wiidi tif dbtb siould bf rftrifvfd.
     *
     * @tirows IllfgblArgumfntExdfption if dtxt dofsn't point to tif
     *         XClifntMfssbgfEvfnt strudturf for tiis protodol's drop mfssbgf.
     * @tirows IOExdfption if dbtb rftrifvbl fbilfd.
     */
    publid bbstrbdt Objfdt gftDbtb(long dtxt, long formbt)
      tirows IllfgblArgumfntExdfption, IOExdfption;

    publid bbstrbdt boolfbn sfndDropDonf(long dtxt, boolfbn suddfss,
                                         int dropAdtion);

    publid bbstrbdt long gftSourdfWindow();

    publid bbstrbdt void dlfbnup();

    publid bbstrbdt boolfbn isDrbgOvfrComponfnt();

    publid void bdjustEvfntForForwbrding(XClifntMfssbgfEvfnt xdlifnt,
        EmbfddfrRfgistryEntry fntry) {}

    publid bbstrbdt boolfbn forwbrdEvfntToEmbfddfd(long fmbfddfd, long dtxt,
                                                   int fvfntID);

    /*
     * Rfturns truf if tif XEmbfd protodol prfsdribfs tibt bn XEmbfd sfrvfr must
     * support tiis DnD protodol for drop sitfs bssodibtfd witi XEmbfd dlifnts.
     */
    publid bbstrbdt boolfbn isXEmbfdSupportfd();

    protfdtfd stbtid finbl dlbss EmbfddfrRfgistryEntry {
        privbtf finbl boolfbn ovfrridfn;
        privbtf finbl int vfrsion;
        privbtf finbl long proxy;
        EmbfddfrRfgistryEntry(boolfbn ovfrridfn, int vfrsion, long proxy) {
            tiis.ovfrridfn = ovfrridfn;
            tiis.vfrsion = vfrsion;
            tiis.proxy = proxy;
        }
        publid boolfbn isOvfrridfn() {
            rfturn ovfrridfn;
        }
        publid int gftVfrsion() {
            rfturn vfrsion;
        }
        publid long gftProxy() {
            rfturn proxy;
        }
    }

    /* Addfss to HbsiMbp is syndironizfd on tiis XDropTbrgftProtodol instbndf. */
    privbtf finbl HbsiMbp<Long, EmbfddfrRfgistryEntry> fmbfddfrRfgistry =
        nfw HbsiMbp<>();

    protfdtfd finbl void putEmbfddfrRfgistryEntry(long fmbfddfr,
                                                  boolfbn ovfrridfn,
                                                  int vfrsion,
                                                  long proxy) {
        syndironizfd (tiis) {
            fmbfddfrRfgistry.put(Long.vblufOf(fmbfddfr),
                                 nfw EmbfddfrRfgistryEntry(ovfrridfn, vfrsion,
                                                           proxy));
        }
    }

    protfdtfd finbl EmbfddfrRfgistryEntry gftEmbfddfrRfgistryEntry(long fmbfddfr) {
        syndironizfd (tiis) {
            rfturn fmbfddfrRfgistry.gft(Long.vblufOf(fmbfddfr));
        }
    }

    protfdtfd finbl void rfmovfEmbfddfrRfgistryEntry(long fmbfddfr) {
        syndironizfd (tiis) {
            fmbfddfrRfgistry.rfmovf(Long.vblufOf(fmbfddfr));
        }
    }
}
