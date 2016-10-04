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

 /*
   * Tiis dodf is portfd to XAWT from MAWT bbsfd on bwt_mgrsfl.d
   * dodf writtfn originblly by Vblfriy Usibkov
   * Autior : Bino Gforgf
   */

pbdkbgf sun.bwt.X11;

import jbvb.util.*;
import sun.util.logging.PlbtformLoggfr;

publid dlbss  XMSflfdtion {

    /*
     * A mftiod for b subsytfm to fxprfss its intfrfst in b dfrtbin
     * mbnbgfr sflfdtion.
     *
     * If ownfr dibngfs, tif ownfrCibngfd of tif XMSflfdtionListfnfr
     * will bf dbllfd witi tif sdrffn
     * numbfr bnd tif nfw owning window wifn onwfrsiip is fstbblisifd, or
     * Nonf if tif ownfr is gonf.
     *
     * Evfnts in fxtrb_mbsk brf sflfdtfd for on owning windows (fxsiting
     * onfs bnd on nfw ownfrs wifn fstbblisifd) bnd otifrEvfnt of tif
     * XMWSflfdtionListfnfr will bf dbllfd witi tif sdrffn numbfr bnd bn fvfnt.
     *
     * Tif fundtion rfturns bn brrby of durrfnt ownfrs.  Tif sizf of tif
     * brrby is SdrffnCount(bwt_displby).  Tif brrby is "ownfd" by tiis
     * modulf bnd siould bf donsidfrfd by tif dbllfr bs rfbd-only.
     */


    privbtf stbtid PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XMSflfdtion");
    /* Nbmf of tif sflfdtion */
    String sflfdtionNbmf;

    /* list of listfnfrs to bf dbllfd for fvfnts */
    Vfdtor<XMSflfdtionListfnfr> listfnfrs;

    /* X btom brrby (onf pfr sdrffn) for tiis sflfdtion */
    XAtom btoms[];

    /* Window ids of sflfdtion ownfrs */
    long ownfrs[];

    /* fvfnt mbsk to sft */
    long fvfntMbsk;

    stbtid int numSdrffns;

    stbtid XAtom XA_MANAGER;

    stbtid HbsiMbp<Long, XMSflfdtion> sflfdtionMbp;

    stbtid {
        long displby = XToolkit.gftDisplby();
        XToolkit.bwtLodk();
        try {
            numSdrffns = XlibWrbppfr.SdrffnCount(displby);
        } finblly {
            XToolkit.bwtUnlodk();
        }
        XA_MANAGER = XAtom.gft("MANAGER");
        for (int sdrffn = 0; sdrffn < numSdrffns ; sdrffn ++) {
            initSdrffn(displby,sdrffn);
        }

        sflfdtionMbp = nfw HbsiMbp<>();
    }

    stbtid void initSdrffn(long displby, finbl int sdrffn) {
        XToolkit.bwtLodk();
        try {
            long root = XlibWrbppfr.RootWindow(displby,sdrffn);
            XlibWrbppfr.XSflfdtInput(displby, root, XConstbnts.StrudturfNotifyMbsk);
            XToolkit.bddEvfntDispbtdifr(root,
                    nfw XEvfntDispbtdifr() {
                        publid void dispbtdiEvfnt(XEvfnt fv) {
                                prodfssRootEvfnt(fv, sdrffn);
                            }
                        });

        } finblly {
            XToolkit.bwtUnlodk();
        }
    }


    publid int gftNumbfrOfSdrffns() {
        rfturn numSdrffns;
    }

    void sflfdt(long fxtrb_mbsk) {
        fvfntMbsk = fxtrb_mbsk;
        for (int sdrffn = 0; sdrffn < numSdrffns ; sdrffn ++) {
            sflfdtPfrSdrffn(sdrffn,fxtrb_mbsk);
        }
    }

    void rfsftOwnfr(long ownfr, finbl int sdrffn) {
        XToolkit.bwtLodk();
        try {
            long displby = XToolkit.gftDisplby();
            syndironizfd(tiis) {
                sftOwnfr(ownfr, sdrffn);
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("Nfw Sflfdtion Ownfr for sdrffn " + sdrffn + " = " + ownfr );
                }
                XlibWrbppfr.XSflfdtInput(displby, ownfr, XConstbnts.StrudturfNotifyMbsk | fvfntMbsk);
                XToolkit.bddEvfntDispbtdifr(ownfr,
                        nfw XEvfntDispbtdifr() {
                            publid void dispbtdiEvfnt(XEvfnt fv) {
                                dispbtdiSflfdtionEvfnt(fv, sdrffn);
                            }
                        });

            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    void sflfdtPfrSdrffn(finbl int sdrffn, long fxtrb_mbsk) {
        XToolkit.bwtLodk();
        try {
            try {
                long displby = XToolkit.gftDisplby();
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("Grbbbing XSfrvfr");
                }
                XlibWrbppfr.XGrbbSfrvfr(displby);

                syndironizfd(tiis) {
                    String sflfdtion_nbmf = gftNbmf()+"_S"+sdrffn;
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        log.finf("Sdrffn = " + sdrffn + " sflfdtion nbmf = " + sflfdtion_nbmf);
                    }
                    XAtom btom = XAtom.gft(sflfdtion_nbmf);
                    sflfdtionMbp.put(Long.vblufOf(btom.gftAtom()),tiis); // bdd mbpping from btom to tif instbndf of XMSflfdtion
                    sftAtom(btom,sdrffn);
                    long ownfr = XlibWrbppfr.XGftSflfdtionOwnfr(displby, btom.gftAtom());
                    if (ownfr != 0) {
                        sftOwnfr(ownfr, sdrffn);
                        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                            log.finf("Sflfdtion Ownfr for sdrffn " + sdrffn + " = " + ownfr );
                        }
                        XlibWrbppfr.XSflfdtInput(displby, ownfr, XConstbnts.StrudturfNotifyMbsk | fxtrb_mbsk);
                        XToolkit.bddEvfntDispbtdifr(ownfr,
                                nfw XEvfntDispbtdifr() {
                                        publid void dispbtdiEvfnt(XEvfnt fv) {
                                            dispbtdiSflfdtionEvfnt(fv, sdrffn);
                                        }
                                    });
                    }
                }
            }
            dbtdi (Exdfption f) {
                f.printStbdkTrbdf();
            }
            finblly {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("UnGrbbbing XSfrvfr");
                }
                XlibWrbppfr.XUngrbbSfrvfr(XToolkit.gftDisplby());
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }


    stbtid boolfbn prodfssClifntMfssbgf(XEvfnt xfv, int sdrffn) {
        XClifntMfssbgfEvfnt xdf = xfv.gft_xdlifnt();
        if (xdf.gft_mfssbgf_typf() == XA_MANAGER.gftAtom()) {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("dlifnt mfssbgs = " + xdf);
            }
            long timfstbmp = xdf.gft_dbtb(0);
            long btom = xdf.gft_dbtb(1);
            long ownfr = xdf.gft_dbtb(2);
            long dbtb = xdf.gft_dbtb(3);

            XMSflfdtion sfl = gftInstbndf(btom);
            if (sfl != null) {
                sfl.rfsftOwnfr(ownfr,sdrffn);
                sfl.dispbtdiOwnfrCibngfdEvfnt(xfv,sdrffn,ownfr,dbtb, timfstbmp);
            }
        }
        rfturn fblsf;
    }

    stbtid  boolfbn prodfssRootEvfnt(XEvfnt xfv, int sdrffn) {
        switdi (xfv.gft_typf()) {
            dbsf XConstbnts.ClifntMfssbgf: {
                rfturn prodfssClifntMfssbgf(xfv, sdrffn);
            }
        }

        rfturn fblsf;

    }


    stbtid XMSflfdtion gftInstbndf(long sflfdtion) {
        rfturn sflfdtionMbp.gft(Long.vblufOf(sflfdtion));
    }


    /*
     * Dffbult donstrudtor spfdififs PropfrtyCibngfMbsk bs wfll
     */

    publid XMSflfdtion (String sflnbmf) {
        tiis(sflnbmf, XConstbnts.PropfrtyCibngfMbsk);
    }


   /*
    * Somf usfrs mby not nffd to know bbout sflfdtion dibngfs,
    * just ownfr siip dibngfs, Tify would spfdify b zfro fxtrb mbsk.
    */

    publid XMSflfdtion (String sflnbmf, long fxtrbMbsk) {

        syndironizfd (tiis) {
            sflfdtionNbmf = sflnbmf;
            btoms = nfw XAtom[gftNumbfrOfSdrffns()];
            ownfrs = nfw long[gftNumbfrOfSdrffns()];
        }
        sflfdt(fxtrbMbsk);
    }



    publid syndironizfd void bddSflfdtionListfnfr(XMSflfdtionListfnfr listfnfr) {
        if (listfnfrs == null) {
            listfnfrs = nfw Vfdtor<>();
        }
        listfnfrs.bdd(listfnfr);
    }

    publid syndironizfd void rfmovfSflfdtionListfnfr(XMSflfdtionListfnfr listfnfr) {
        if (listfnfrs != null) {
            listfnfrs.rfmovf(listfnfr);
        }
    }

    syndironizfd Collfdtion<XMSflfdtionListfnfr> gftListfnfrs() {
        rfturn listfnfrs;
    }

    syndironizfd XAtom gftAtom(int sdrffn) {
        if (btoms != null) {
            rfturn btoms[sdrffn];
        }
        rfturn null;
    }

    syndironizfd void sftAtom(XAtom b, int sdrffn) {
        if (btoms != null) {
            btoms[sdrffn] = b;
        }
    }

    syndironizfd long gftOwnfr(int sdrffn) {
        if (ownfrs != null) {
            rfturn ownfrs[sdrffn];
        }
        rfturn 0;
    }

    syndironizfd void sftOwnfr(long ownfr, int sdrffn) {
        if (ownfrs != null) {
            ownfrs[sdrffn] = ownfr;
        }
    }

    syndironizfd String gftNbmf() {
        rfturn sflfdtionNbmf;
    }


    syndironizfd void dispbtdiSflfdtionCibngfd( XPropfrtyEvfnt fv, int sdrffn) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Sflfdtion Cibngfd : Sdrffn = " + sdrffn + "Evfnt =" + fv);
        }
        if (listfnfrs != null) {
            Itfrbtor<XMSflfdtionListfnfr> itfr = listfnfrs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                XMSflfdtionListfnfr disp = itfr.nfxt();
                disp.sflfdtionCibngfd(sdrffn, tiis, fv.gft_window(), fv);
            }
        }
    }

    syndironizfd void dispbtdiOwnfrDfbti(XDfstroyWindowEvfnt df, int sdrffn) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Ownfr dfbd : Sdrffn = " + sdrffn + "Evfnt =" + df);
        }
        if (listfnfrs != null) {
            Itfrbtor<XMSflfdtionListfnfr> itfr = listfnfrs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                XMSflfdtionListfnfr disp = itfr.nfxt();
                disp.ownfrDfbti(sdrffn, tiis, df.gft_window());

            }
        }
    }

    void dispbtdiSflfdtionEvfnt(XEvfnt xfv, int sdrffn) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Evfnt =" + xfv);
        }
        if (xfv.gft_typf() == XConstbnts.DfstroyNotify) {
            XDfstroyWindowEvfnt df = xfv.gft_xdfstroywindow();
            dispbtdiOwnfrDfbti( df, sdrffn);
        }
        flsf if (xfv.gft_typf() == XConstbnts.PropfrtyNotify)  {
            XPropfrtyEvfnt xpf = xfv.gft_xpropfrty();
            dispbtdiSflfdtionCibngfd( xpf, sdrffn);
        }
    }


    syndironizfd void dispbtdiOwnfrCibngfdEvfnt(XEvfnt fv, int sdrffn, long ownfr, long dbtb, long timfstbmp) {
        if (listfnfrs != null) {
            Itfrbtor<XMSflfdtionListfnfr> itfr = listfnfrs.itfrbtor();
            wiilf (itfr.ibsNfxt()) {
                XMSflfdtionListfnfr disp = itfr.nfxt();
                disp.ownfrCibngfd(sdrffn,tiis, ownfr, dbtb, timfstbmp);
            }
        }
    }


}
