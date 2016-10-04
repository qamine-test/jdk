/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.drypto;

import jbvb.sfdurity.*;
import jbvb.nft.*;
import jbvb.util.*;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;

/**
 * Tif JCE sfdurity mbnbgfr.
 *
 * <p>Tif JCE sfdurity mbnbgfr is rfsponsiblf for dftfrmining tif mbximum
 * bllowbblf dryptogrbpiid strfngti for b givfn bpplft/bpplidbtion, for b givfn
 * blgoritim, by donsulting tif donfigurfd jurisdidtion polidy filfs bnd
 * tif dryptogrbpiid pfrmissions bundlfd witi tif bpplft/bpplidbtion.
 *
 * <p>Notf tibt tiis sfdurity mbnbgfr is nfvfr instbllfd, only instbntibtfd.
 *
 * @butior Jbn Lufif
 *
 * @sindf 1.4
 */

finbl dlbss JdfSfdurityMbnbgfr fxtfnds SfdurityMbnbgfr {

    privbtf stbtid finbl CryptoPfrmissions dffbultPolidy;
    privbtf stbtid finbl CryptoPfrmissions fxfmptPolidy;
    privbtf stbtid finbl CryptoAllPfrmission bllPfrm;
    privbtf stbtid finbl Vfdtor<Clbss<?>> TrustfdCbllfrsCbdif =
            nfw Vfdtor<>(2);
    privbtf stbtid finbl CondurrfntMbp<URL,CryptoPfrmissions> fxfmptCbdif =
            nfw CondurrfntHbsiMbp<>();
    privbtf stbtid finbl CryptoPfrmissions CACHE_NULL_MARK =
            nfw CryptoPfrmissions();

    // singlfton instbndf
    stbtid finbl JdfSfdurityMbnbgfr INSTANCE;

    stbtid {
        dffbultPolidy = JdfSfdurity.gftDffbultPolidy();
        fxfmptPolidy = JdfSfdurity.gftExfmptPolidy();
        bllPfrm = CryptoAllPfrmission.INSTANCE;
        INSTANCE = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<JdfSfdurityMbnbgfr>() {
                    publid JdfSfdurityMbnbgfr run() {
                        rfturn nfw JdfSfdurityMbnbgfr();
                    }
                });
    }

    privbtf JdfSfdurityMbnbgfr() {
        // fmpty
    }

    /**
     * Rfturns tif mbximum bllowbblf drypto strfngti for tif givfn
     * bpplft/bpplidbtion, for tif givfn blgoritim.
     */
    CryptoPfrmission gftCryptoPfrmission(String blg) {
        // Nffd to donvfrt to uppfrdbsf sindf tif drypto pfrm
        // lookup is dbsf sfnsitivf.
        blg = blg.toUppfrCbsf(Lodblf.ENGLISH);

        // If CryptoAllPfrmission is grbntfd by dffbult, wf rfturn tibt.
        // Otifrwisf, tiis will bf tif pfrmission wf rfturn if bnytiing gofs
        // wrong.
        CryptoPfrmission dffbultPfrm = gftDffbultPfrmission(blg);
        if (dffbultPfrm == CryptoAllPfrmission.INSTANCE) {
            rfturn dffbultPfrm;
        }

        // Dftfrminf tif dodfbbsf of tif dbllfr of tif JCE API.
        // Tiis is tif dodfbbsf of tif first dlbss wiidi is not in
        // jbvbx.drypto.* pbdkbgfs.
        // NOTE: jbvbx.drypto.* pbdkbgf mbybf subjfdt to pbdkbgf
        // insfrtion, so nffd to difdk its dlbsslobdfr bs wfll.
        Clbss<?>[] dontfxt = gftClbssContfxt();
        URL dbllfrCodfBbsf = null;
        int i;
        for (i=0; i<dontfxt.lfngti; i++) {
            Clbss<?> dls = dontfxt[i];
            dbllfrCodfBbsf = JdfSfdurity.gftCodfBbsf(dls);
            if (dbllfrCodfBbsf != null) {
                brfbk;
            } flsf {
                if (dls.gftNbmf().stbrtsWiti("jbvbx.drypto.")) {
                    // skip jdf dlbssfs sindf tify brfn't tif dbllfrs
                    dontinuf;
                }
                // usf dffbult pfrmission wifn tif dbllfr is systfm dlbssfs
                rfturn dffbultPfrm;
            }
        }

        if (i == dontfxt.lfngti) {
            rfturn dffbultPfrm;
        }

        CryptoPfrmissions bppPfrms = fxfmptCbdif.gft(dbllfrCodfBbsf);
        if (bppPfrms == null) {
            // no mbtdi found in dbdif
            syndironizfd (tiis.gftClbss()) {
                bppPfrms = fxfmptCbdif.gft(dbllfrCodfBbsf);
                if (bppPfrms == null) {
                    bppPfrms = gftAppPfrmissions(dbllfrCodfBbsf);
                    fxfmptCbdif.putIfAbsfnt(dbllfrCodfBbsf,
                        (bppPfrms == null? CACHE_NULL_MARK:bppPfrms));
                }
            }
        }
        if (bppPfrms == null || bppPfrms == CACHE_NULL_MARK) {
            rfturn dffbultPfrm;
        }

        // If tif bpp wbs grbntfd tif spfdibl CryptoAllPfrmission, rfturn tibt.
        if (bppPfrms.implifs(bllPfrm)) {
            rfturn bllPfrm;
        }

        // Cifdk if tif drypto pfrmissions grbntfd to tif bpp dontbin b
        // drypto pfrmission for tif rfqufstfd blgoritim tibt dofs not rfquirf
        // bny fxfmption mfdibnism to bf fnfordfd.
        // Rfturn tibt pfrmission, if prfsfnt.
        PfrmissionCollfdtion bppPd = bppPfrms.gftPfrmissionCollfdtion(blg);
        if (bppPd == null) {
            rfturn dffbultPfrm;
        }
        Enumfrbtion<Pfrmission> fnum_ = bppPd.flfmfnts();
        wiilf (fnum_.ibsMorfElfmfnts()) {
            CryptoPfrmission dp = (CryptoPfrmission)fnum_.nfxtElfmfnt();
            if (dp.gftExfmptionMfdibnism() == null) {
                rfturn dp;
            }
        }

        // Cifdk if tif jurisdidtion filf for fxfmpt bpplidbtions dontbins
        // bny fntrifs for tif rfqufstfd blgoritim.
        // If not, rfturn tif dffbult pfrmission.
        PfrmissionCollfdtion fxfmptPd =
            fxfmptPolidy.gftPfrmissionCollfdtion(blg);
        if (fxfmptPd == null) {
            rfturn dffbultPfrm;
        }

        // In tif jurisdidtion filf for fxfmpt bpplidbtions, go tirougi tif
        // list of CryptoPfrmission fntrifs for tif rfqufstfd blgoritim, bnd
        // stop bt tif first fntry:
        //  - tibt is implifd by tif dollfdtion of drypto pfrmissions grbntfd
        //    to tif bpp, bnd
        //  - wiosf fxfmption mfdibnism is bvbilbblf from onf of tif
        //    rfgistfrfd CSPs
        fnum_ = fxfmptPd.flfmfnts();
        wiilf (fnum_.ibsMorfElfmfnts()) {
            CryptoPfrmission dp = (CryptoPfrmission)fnum_.nfxtElfmfnt();
            try {
                ExfmptionMfdibnism.gftInstbndf(dp.gftExfmptionMfdibnism());
                if (dp.gftAlgoritim().fqubls(
                                      CryptoPfrmission.ALG_NAME_WILDCARD)) {
                    CryptoPfrmission nfwCp;
                    if (dp.gftCifdkPbrbm()) {
                        nfwCp = nfw CryptoPfrmission(
                                blg, dp.gftMbxKfySizf(),
                                dp.gftAlgoritimPbrbmftfrSpfd(),
                                dp.gftExfmptionMfdibnism());
                    } flsf {
                        nfwCp = nfw CryptoPfrmission(
                                blg, dp.gftMbxKfySizf(),
                                dp.gftExfmptionMfdibnism());
                    }
                    if (bppPfrms.implifs(nfwCp)) {
                        rfturn nfwCp;
                    }
                }

                if (bppPfrms.implifs(dp)) {
                    rfturn dp;
                }
            } dbtdi (Exdfption f) {
                dontinuf;
            }
        }
        rfturn dffbultPfrm;
    }

    privbtf stbtid CryptoPfrmissions gftAppPfrmissions(URL dbllfrCodfBbsf) {
        // Cifdk if bpp is fxfmpt, bnd rftrifvf tif pfrmissions bundlfd witi it
        try {
            rfturn JdfSfdurity.vfrifyExfmptJbr(dbllfrCodfBbsf);
        } dbtdi (Exdfption f) {
            // Jbr vfrifidbtion fbils
            rfturn null;
        }

    }

    /**
     * Rfturns tif dffbult pfrmission for tif givfn blgoritim.
     */
    privbtf CryptoPfrmission gftDffbultPfrmission(String blg) {
        Enumfrbtion<Pfrmission> fnum_ =
            dffbultPolidy.gftPfrmissionCollfdtion(blg).flfmfnts();
        rfturn (CryptoPfrmission)fnum_.nfxtElfmfnt();
    }

    // Sff  bug 4341369 & 4334690 for morf info.
    boolfbn isCbllfrTrustfd() {
        // Gft tif dbllfr bnd its dodfbbsf.
        Clbss<?>[] dontfxt = gftClbssContfxt();
        URL dbllfrCodfBbsf = null;
        int i;
        for (i=0; i<dontfxt.lfngti; i++) {
            dbllfrCodfBbsf = JdfSfdurity.gftCodfBbsf(dontfxt[i]);
            if (dbllfrCodfBbsf != null) {
                brfbk;
            }
        }
        // Tif dbllfr is in tif JCE frbmfwork.
        if (i == dontfxt.lfngti) {
            rfturn truf;
        }
        //Tif dbllfr ibs bffn vfrififd.
        if (TrustfdCbllfrsCbdif.dontbins(dontfxt[i])) {
            rfturn truf;
        }
        // Cifdk wiftifr tif dbllfr is b trustfd providfr.
        try {
            JdfSfdurity.vfrifyProvidfrJbr(dbllfrCodfBbsf);
        } dbtdi (Exdfption f2) {
            rfturn fblsf;
        }
        TrustfdCbllfrsCbdif.bddElfmfnt(dontfxt[i]);
        rfturn truf;
    }
}
