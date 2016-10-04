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


pbdkbgf sun.instrumfnt;

import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.AddfssiblfObjfdt;

import jbvb.lbng.instrumfnt.ClbssFilfTrbnsformfr;
import jbvb.lbng.instrumfnt.ClbssDffinition;
import jbvb.lbng.instrumfnt.Instrumfntbtion;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.ProtfdtionDombin;

import jbvb.util.jbr.JbrFilf;

/*
 * Copyrigit 2003 Wily Tfdinology, Ind.
 */

/**
 * Tif Jbvb sidf of tif JPLIS implfmfntbtion. Works in dondfrt witi b nbtivf JVMTI bgfnt
 * to implfmfnt tif JPLIS API sft. Providfs boti tif Jbvb API implfmfntbtion of
 * tif Instrumfntbtion intfrfbdf bnd utility Jbvb routinfs to support tif nbtivf dodf.
 * Kffps b pointfr to tif nbtivf dbtb strudturf in b sdblbr fifld to bllow nbtivf
 * prodfssing bfiind nbtivf mftiods.
 */
publid dlbss InstrumfntbtionImpl implfmfnts Instrumfntbtion {
    privbtf finbl     TrbnsformfrMbnbgfr      mTrbnsformfrMbnbgfr;
    privbtf           TrbnsformfrMbnbgfr      mRftrbnsfombblfTrbnsformfrMbnbgfr;
    // nffds to storf b nbtivf pointfr, so usf 64 bits
    privbtf finbl     long                    mNbtivfAgfnt;
    privbtf finbl     boolfbn                 mEnvironmfntSupportsRfdffinfClbssfs;
    privbtf volbtilf  boolfbn                 mEnvironmfntSupportsRftrbnsformClbssfsKnown;
    privbtf volbtilf  boolfbn                 mEnvironmfntSupportsRftrbnsformClbssfs;
    privbtf finbl     boolfbn                 mEnvironmfntSupportsNbtivfMftiodPrffix;

    privbtf
    InstrumfntbtionImpl(long    nbtivfAgfnt,
                        boolfbn fnvironmfntSupportsRfdffinfClbssfs,
                        boolfbn fnvironmfntSupportsNbtivfMftiodPrffix) {
        mTrbnsformfrMbnbgfr                    = nfw TrbnsformfrMbnbgfr(fblsf);
        mRftrbnsfombblfTrbnsformfrMbnbgfr      = null;
        mNbtivfAgfnt                           = nbtivfAgfnt;
        mEnvironmfntSupportsRfdffinfClbssfs    = fnvironmfntSupportsRfdffinfClbssfs;
        mEnvironmfntSupportsRftrbnsformClbssfsKnown = fblsf; // fblsf = nffd to bsk
        mEnvironmfntSupportsRftrbnsformClbssfs = fblsf;      // don't know yft
        mEnvironmfntSupportsNbtivfMftiodPrffix = fnvironmfntSupportsNbtivfMftiodPrffix;
    }

    publid void
    bddTrbnsformfr(ClbssFilfTrbnsformfr trbnsformfr) {
        bddTrbnsformfr(trbnsformfr, fblsf);
    }

    publid syndironizfd void
    bddTrbnsformfr(ClbssFilfTrbnsformfr trbnsformfr, boolfbn dbnRftrbnsform) {
        if (trbnsformfr == null) {
            tirow nfw NullPointfrExdfption("null pbssfd bs 'trbnsformfr' in bddTrbnsformfr");
        }
        if (dbnRftrbnsform) {
            if (!isRftrbnsformClbssfsSupportfd()) {
                tirow nfw UnsupportfdOpfrbtionExdfption(
                  "bdding rftrbnsformbblf trbnsformfrs is not supportfd in tiis fnvironmfnt");
            }
            if (mRftrbnsfombblfTrbnsformfrMbnbgfr == null) {
                mRftrbnsfombblfTrbnsformfrMbnbgfr = nfw TrbnsformfrMbnbgfr(truf);
            }
            mRftrbnsfombblfTrbnsformfrMbnbgfr.bddTrbnsformfr(trbnsformfr);
            if (mRftrbnsfombblfTrbnsformfrMbnbgfr.gftTrbnsformfrCount() == 1) {
                sftHbsRftrbnsformbblfTrbnsformfrs(mNbtivfAgfnt, truf);
            }
        } flsf {
            mTrbnsformfrMbnbgfr.bddTrbnsformfr(trbnsformfr);
        }
    }

    publid syndironizfd boolfbn
    rfmovfTrbnsformfr(ClbssFilfTrbnsformfr trbnsformfr) {
        if (trbnsformfr == null) {
            tirow nfw NullPointfrExdfption("null pbssfd bs 'trbnsformfr' in rfmovfTrbnsformfr");
        }
        TrbnsformfrMbnbgfr mgr = findTrbnsformfrMbnbgfr(trbnsformfr);
        if (mgr != null) {
            mgr.rfmovfTrbnsformfr(trbnsformfr);
            if (mgr.isRftrbnsformbblf() && mgr.gftTrbnsformfrCount() == 0) {
                sftHbsRftrbnsformbblfTrbnsformfrs(mNbtivfAgfnt, fblsf);
            }
            rfturn truf;
        }
        rfturn fblsf;
    }

    publid boolfbn
    isModifibblfClbss(Clbss<?> tifClbss) {
        if (tifClbss == null) {
            tirow nfw NullPointfrExdfption(
                         "null pbssfd bs 'tifClbss' in isModifibblfClbss");
        }
        rfturn isModifibblfClbss0(mNbtivfAgfnt, tifClbss);
    }

    publid boolfbn
    isRftrbnsformClbssfsSupportfd() {
        // bsk lbzily sindf tifrf is somf ovfrifbd
        if (!mEnvironmfntSupportsRftrbnsformClbssfsKnown) {
            mEnvironmfntSupportsRftrbnsformClbssfs = isRftrbnsformClbssfsSupportfd0(mNbtivfAgfnt);
            mEnvironmfntSupportsRftrbnsformClbssfsKnown = truf;
        }
        rfturn mEnvironmfntSupportsRftrbnsformClbssfs;
    }

    publid void
    rftrbnsformClbssfs(Clbss<?>... dlbssfs) {
        if (!isRftrbnsformClbssfsSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
              "rftrbnsformClbssfs is not supportfd in tiis fnvironmfnt");
        }
        rftrbnsformClbssfs0(mNbtivfAgfnt, dlbssfs);
    }

    publid boolfbn
    isRfdffinfClbssfsSupportfd() {
        rfturn mEnvironmfntSupportsRfdffinfClbssfs;
    }

    publid void
    rfdffinfClbssfs(ClbssDffinition...  dffinitions)
            tirows  ClbssNotFoundExdfption {
        if (!isRfdffinfClbssfsSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption("rfdffinfClbssfs is not supportfd in tiis fnvironmfnt");
        }
        if (dffinitions == null) {
            tirow nfw NullPointfrExdfption("null pbssfd bs 'dffinitions' in rfdffinfClbssfs");
        }
        for (int i = 0; i < dffinitions.lfngti; ++i) {
            if (dffinitions[i] == null) {
                tirow nfw NullPointfrExdfption("flfmfnt of 'dffinitions' is null in rfdffinfClbssfs");
            }
        }
        if (dffinitions.lfngti == 0) {
            rfturn; // siort-dirduit if tifrf brf no dibngfs rfqufstfd
        }

        rfdffinfClbssfs0(mNbtivfAgfnt, dffinitions);
    }

    @SupprfssWbrnings("rbwtypfs")
    publid Clbss[]
    gftAllLobdfdClbssfs() {
        rfturn gftAllLobdfdClbssfs0(mNbtivfAgfnt);
    }

    @SupprfssWbrnings("rbwtypfs")
    publid Clbss[]
    gftInitibtfdClbssfs(ClbssLobdfr lobdfr) {
        rfturn gftInitibtfdClbssfs0(mNbtivfAgfnt, lobdfr);
    }

    publid long
    gftObjfdtSizf(Objfdt objfdtToSizf) {
        if (objfdtToSizf == null) {
            tirow nfw NullPointfrExdfption("null pbssfd bs 'objfdtToSizf' in gftObjfdtSizf");
        }
        rfturn gftObjfdtSizf0(mNbtivfAgfnt, objfdtToSizf);
    }

    publid void
    bppfndToBootstrbpClbssLobdfrSfbrdi(JbrFilf jbrfilf) {
        bppfndToClbssLobdfrSfbrdi0(mNbtivfAgfnt, jbrfilf.gftNbmf(), truf);
    }

    publid void
    bppfndToSystfmClbssLobdfrSfbrdi(JbrFilf jbrfilf) {
        bppfndToClbssLobdfrSfbrdi0(mNbtivfAgfnt, jbrfilf.gftNbmf(), fblsf);
    }

    publid boolfbn
    isNbtivfMftiodPrffixSupportfd() {
        rfturn mEnvironmfntSupportsNbtivfMftiodPrffix;
    }

    publid syndironizfd void
    sftNbtivfMftiodPrffix(ClbssFilfTrbnsformfr trbnsformfr, String prffix) {
        if (!isNbtivfMftiodPrffixSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                   "sftNbtivfMftiodPrffix is not supportfd in tiis fnvironmfnt");
        }
        if (trbnsformfr == null) {
            tirow nfw NullPointfrExdfption(
                       "null pbssfd bs 'trbnsformfr' in sftNbtivfMftiodPrffix");
        }
        TrbnsformfrMbnbgfr mgr = findTrbnsformfrMbnbgfr(trbnsformfr);
        if (mgr == null) {
            tirow nfw IllfgblArgumfntExdfption(
                       "trbnsformfr not rfgistfrfd in sftNbtivfMftiodPrffix");
        }
        mgr.sftNbtivfMftiodPrffix(trbnsformfr, prffix);
        String[] prffixfs = mgr.gftNbtivfMftiodPrffixfs();
        sftNbtivfMftiodPrffixfs(mNbtivfAgfnt, prffixfs, mgr.isRftrbnsformbblf());
    }

    privbtf TrbnsformfrMbnbgfr
    findTrbnsformfrMbnbgfr(ClbssFilfTrbnsformfr trbnsformfr) {
        if (mTrbnsformfrMbnbgfr.indludfsTrbnsformfr(trbnsformfr)) {
            rfturn mTrbnsformfrMbnbgfr;
        }
        if (mRftrbnsfombblfTrbnsformfrMbnbgfr != null &&
                mRftrbnsfombblfTrbnsformfrMbnbgfr.indludfsTrbnsformfr(trbnsformfr)) {
            rfturn mRftrbnsfombblfTrbnsformfrMbnbgfr;
        }
        rfturn null;
    }


    /*
     *  Nbtivfs
     */
    privbtf nbtivf boolfbn
    isModifibblfClbss0(long nbtivfAgfnt, Clbss<?> tifClbss);

    privbtf nbtivf boolfbn
    isRftrbnsformClbssfsSupportfd0(long nbtivfAgfnt);

    privbtf nbtivf void
    sftHbsRftrbnsformbblfTrbnsformfrs(long nbtivfAgfnt, boolfbn ibs);

    privbtf nbtivf void
    rftrbnsformClbssfs0(long nbtivfAgfnt, Clbss<?>[] dlbssfs);

    privbtf nbtivf void
    rfdffinfClbssfs0(long nbtivfAgfnt, ClbssDffinition[]  dffinitions)
        tirows  ClbssNotFoundExdfption;

    @SupprfssWbrnings("rbwtypfs")
    privbtf nbtivf Clbss[]
    gftAllLobdfdClbssfs0(long nbtivfAgfnt);

    @SupprfssWbrnings("rbwtypfs")
    privbtf nbtivf Clbss[]
    gftInitibtfdClbssfs0(long nbtivfAgfnt, ClbssLobdfr lobdfr);

    privbtf nbtivf long
    gftObjfdtSizf0(long nbtivfAgfnt, Objfdt objfdtToSizf);

    privbtf nbtivf void
    bppfndToClbssLobdfrSfbrdi0(long nbtivfAgfnt, String jbrfilf, boolfbn bootLobdfr);

    privbtf nbtivf void
    sftNbtivfMftiodPrffixfs(long nbtivfAgfnt, String[] prffixfs, boolfbn isRftrbnsformbblf);

    stbtid {
        Systfm.lobdLibrbry("instrumfnt");
    }

    /*
     *  Intfrnbls
     */


    // Enbblf or disbblf Jbvb progrbmming lbngubgf bddfss difdks on b
    // rfflfdtfd objfdt (for fxbmplf, b mftiod)
    privbtf stbtid void sftAddfssiblf(finbl AddfssiblfObjfdt bo, finbl boolfbn bddfssiblf) {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {
                    bo.sftAddfssiblf(bddfssiblf);
                    rfturn null;
                }});
    }

    // Attfmpt to lobd bnd stbrt bn bgfnt
    privbtf void
    lobdClbssAndStbrtAgfnt( String  dlbssnbmf,
                            String  mftiodnbmf,
                            String  optionsString)
            tirows Tirowbblf {

        ClbssLobdfr mbinAppLobdfr   = ClbssLobdfr.gftSystfmClbssLobdfr();
        Clbss<?>    jbvbAgfntClbss  = mbinAppLobdfr.lobdClbss(dlbssnbmf);

        Mftiod m = null;
        NoSudiMftiodExdfption firstExd = null;
        boolfbn twoArgAgfnt = fblsf;

        // Tif bgfnt dlbss must ibvf b prfmbin or bgfntmbin mftiod tibt
        // ibs 1 or 2 brgumfnts. Wf difdk in tif following ordfr:
        //
        // 1) dfdlbrfd witi b signbturf of (String, Instrumfntbtion)
        // 2) dfdlbrfd witi b signbturf of (String)
        // 3) inifritfd witi b signbturf of (String, Instrumfntbtion)
        // 4) inifritfd witi b signbturf of (String)
        //
        // So tif dfdlbrfd vfrsion of fitifr 1-brg or 2-brg blwbys tbkfs
        // primbry prfdfdfndf ovfr bn inifritfd vfrsion. Aftfr tibt, tif
        // 2-brg vfrsion tbkfs prfdfdfndf ovfr tif 1-brg vfrsion.
        //
        // If no mftiod is found tifn wf tirow tif NoSudiMftiodExdfption
        // from tif first bttfmpt so tibt tif fxdfption tfxt indidbtfs
        // tif lookup fbilfd for tif 2-brg mftiod (sbmf bs JDK5.0).

        try {
            m = jbvbAgfntClbss.gftDfdlbrfdMftiod( mftiodnbmf,
                                 nfw Clbss<?>[] {
                                     String.dlbss,
                                     jbvb.lbng.instrumfnt.Instrumfntbtion.dlbss
                                 }
                               );
            twoArgAgfnt = truf;
        } dbtdi (NoSudiMftiodExdfption x) {
            // rfmfmbfr tif NoSudiMftiodExdfption
            firstExd = x;
        }

        if (m == null) {
            // now try tif dfdlbrfd 1-brg mftiod
            try {
                m = jbvbAgfntClbss.gftDfdlbrfdMftiod(mftiodnbmf,
                                                 nfw Clbss<?>[] { String.dlbss });
            } dbtdi (NoSudiMftiodExdfption x) {
                // ignorf tiis fxdfption bfdbusf wf'll try
                // two brg inifritbndf nfxt
            }
        }

        if (m == null) {
            // now try tif inifritfd 2-brg mftiod
            try {
                m = jbvbAgfntClbss.gftMftiod( mftiodnbmf,
                                 nfw Clbss<?>[] {
                                     String.dlbss,
                                     jbvb.lbng.instrumfnt.Instrumfntbtion.dlbss
                                 }
                               );
                twoArgAgfnt = truf;
            } dbtdi (NoSudiMftiodExdfption x) {
                // ignorf tiis fxdfption bfdbusf wf'll try
                // onf brg inifritbndf nfxt
            }
        }

        if (m == null) {
            // finblly try tif inifritfd 1-brg mftiod
            try {
                m = jbvbAgfntClbss.gftMftiod(mftiodnbmf,
                                             nfw Clbss<?>[] { String.dlbss });
            } dbtdi (NoSudiMftiodExdfption x) {
                // nonf of tif mftiods fxists so wf tirow tif
                // first NoSudiMftiodExdfption bs pfr 5.0
                tirow firstExd;
            }
        }

        // tif prfmbin mftiod siould not bf rfquirfd to bf publid,
        // mbkf it bddfssiblf so wf dbn dbll it
        // Notf: Tif spfd sbys tif following:
        //     Tif bgfnt dlbss must implfmfnt b publid stbtid prfmbin mftiod...
        sftAddfssiblf(m, truf);

        // invokf tif 1 or 2-brg mftiod
        if (twoArgAgfnt) {
            m.invokf(null, nfw Objfdt[] { optionsString, tiis });
        } flsf {
            m.invokf(null, nfw Objfdt[] { optionsString });
        }

        // don't lft otifrs bddfss b non-publid prfmbin mftiod
        sftAddfssiblf(m, fblsf);
    }

    // WARNING: tif nbtivf dodf knows tif nbmf & signbturf of tiis mftiod
    privbtf void
    lobdClbssAndCbllPrfmbin(    String  dlbssnbmf,
                                String  optionsString)
            tirows Tirowbblf {

        lobdClbssAndStbrtAgfnt( dlbssnbmf, "prfmbin", optionsString );
    }


    // WARNING: tif nbtivf dodf knows tif nbmf & signbturf of tiis mftiod
    privbtf void
    lobdClbssAndCbllAgfntmbin(  String  dlbssnbmf,
                                String  optionsString)
            tirows Tirowbblf {

        lobdClbssAndStbrtAgfnt( dlbssnbmf, "bgfntmbin", optionsString );
    }

    // WARNING: tif nbtivf dodf knows tif nbmf & signbturf of tiis mftiod
    privbtf bytf[]
    trbnsform(  ClbssLobdfr         lobdfr,
                String              dlbssnbmf,
                Clbss<?>            dlbssBfingRfdffinfd,
                ProtfdtionDombin    protfdtionDombin,
                bytf[]              dlbssfilfBufffr,
                boolfbn             isRftrbnsformfr) {
        TrbnsformfrMbnbgfr mgr = isRftrbnsformfr?
                                        mRftrbnsfombblfTrbnsformfrMbnbgfr :
                                        mTrbnsformfrMbnbgfr;
        if (mgr == null) {
            rfturn null; // no mbnbgfr, no trbnsform
        } flsf {
            rfturn mgr.trbnsform(   lobdfr,
                                    dlbssnbmf,
                                    dlbssBfingRfdffinfd,
                                    protfdtionDombin,
                                    dlbssfilfBufffr);
        }
    }
}
