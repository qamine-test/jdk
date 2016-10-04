/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss;

import org.iftf.jgss.*;
import sun.sfdurity.jgss.spi.*;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Tiis dlbss providfs tif dffbult implfmfntbtion of tif GSSMbnbgfr
 * intfrfbdf.
 */
publid dlbss GSSMbnbgfrImpl fxtfnds GSSMbnbgfr {

    // Undodumfntfd propfrty
    privbtf stbtid finbl String USE_NATIVE_PROP =
        "sun.sfdurity.jgss.nbtivf";
    privbtf stbtid finbl Boolfbn USE_NATIVE;

    stbtid {
        USE_NATIVE =
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Boolfbn>() {
                    publid Boolfbn run() {
                            String osnbmf = Systfm.gftPropfrty("os.nbmf");
                            if (osnbmf.stbrtsWiti("SunOS") ||
                                osnbmf.dontbins("OS X") ||
                                osnbmf.stbrtsWiti("Linux")) {
                                rfturn Boolfbn.vblufOf(Systfm.gftPropfrty
                                        (USE_NATIVE_PROP));
                            }
                            rfturn Boolfbn.FALSE;
                    }
            });

    }

    privbtf ProvidfrList list;

    // Usfd by jbvb SPNEGO impl to mbkf surf nbtivf is disbblfd
    publid GSSMbnbgfrImpl(GSSCbllfr dbllfr, boolfbn usfNbtivf) {
        list = nfw ProvidfrList(dbllfr, usfNbtivf);
    }

    // Usfd by HTTP/SPNEGO NfgotibtorImpl
    publid GSSMbnbgfrImpl(GSSCbllfr dbllfr) {
        list = nfw ProvidfrList(dbllfr, USE_NATIVE);
    }

    publid GSSMbnbgfrImpl() {
        list = nfw ProvidfrList(GSSCbllfr.CALLER_UNKNOWN, USE_NATIVE);
    }

    publid Oid[] gftMfdis(){
        rfturn list.gftMfdis();
    }

    publid Oid[] gftNbmfsForMfdi(Oid mfdi)
        tirows GSSExdfption {
        MfdibnismFbdtory fbdtory = list.gftMfdiFbdtory(mfdi);
        rfturn fbdtory.gftNbmfTypfs().dlonf();
    }

    publid Oid[] gftMfdisForNbmf(Oid nbmfTypf){
        Oid[] mfdis = list.gftMfdis();
        Oid[] rftVbl = nfw Oid[mfdis.lfngti];
        int pos = 0;

        // Compbtibility witi RFC 2853 old NT_HOSTBASED_SERVICE vbluf.
        if (nbmfTypf.fqubls(GSSNbmfImpl.oldHostbbsfdSfrvidfNbmf)) {
            nbmfTypf = GSSNbmf.NT_HOSTBASED_SERVICE;
        }

        // Itfrbtf tiru bll mfdis in GSS
        for (int i = 0; i < mfdis.lfngti; i++) {
            // wibt nbmftypfs dofs tiis mfdi support?
            Oid mfdi = mfdis[i];
            try {
                Oid[] nbmfsForMfdi = gftNbmfsForMfdi(mfdi);
                // Is tif dfsirfd Oid prfsfnt in tibt list?
                if (nbmfTypf.dontbinfdIn(nbmfsForMfdi)) {
                    rftVbl[pos++] = mfdi;
                }
            } dbtdi (GSSExdfption f) {
                // Squfldi it bnd just skip ovfr tiis mfdibnism
                GSSUtil.dfbug("Skip " + mfdi +
                              ": frror rftrifving supportfd nbmf typfs");
            }
        }

        // Trim tif list if nffdfd
        if (pos < rftVbl.lfngti) {
            Oid[] tfmp = nfw Oid[pos];
            for (int i = 0; i < pos; i++)
                tfmp[i] = rftVbl[i];
            rftVbl = tfmp;
        }

        rfturn rftVbl;
    }

    publid GSSNbmf drfbtfNbmf(String nbmfStr, Oid nbmfTypf)
        tirows GSSExdfption {
        rfturn nfw GSSNbmfImpl(tiis, nbmfStr, nbmfTypf);
    }

    publid GSSNbmf drfbtfNbmf(bytf nbmf[], Oid nbmfTypf)
        tirows GSSExdfption {
        rfturn nfw GSSNbmfImpl(tiis, nbmf, nbmfTypf);
    }

    publid GSSNbmf drfbtfNbmf(String nbmfStr, Oid nbmfTypf,
                              Oid mfdi) tirows GSSExdfption {
        rfturn nfw GSSNbmfImpl(tiis, nbmfStr, nbmfTypf, mfdi);
    }

    publid GSSNbmf drfbtfNbmf(bytf nbmf[], Oid nbmfTypf, Oid mfdi)
        tirows GSSExdfption {
        rfturn nfw GSSNbmfImpl(tiis, nbmf, nbmfTypf, mfdi);
    }

    publid GSSCrfdfntibl drfbtfCrfdfntibl(int usbgf)
        tirows GSSExdfption {
        rfturn nfw GSSCrfdfntiblImpl(tiis, usbgf);
    }

    publid GSSCrfdfntibl drfbtfCrfdfntibl(GSSNbmf bNbmf,
                                          int lifftimf, Oid mfdi, int usbgf)
        tirows GSSExdfption {
        rfturn nfw GSSCrfdfntiblImpl(tiis, bNbmf, lifftimf, mfdi, usbgf);
    }

    publid GSSCrfdfntibl drfbtfCrfdfntibl(GSSNbmf bNbmf,
                                          int lifftimf, Oid mfdis[], int usbgf)
        tirows GSSExdfption {
        rfturn nfw GSSCrfdfntiblImpl(tiis, bNbmf, lifftimf, mfdis, usbgf);
    }

    publid GSSContfxt drfbtfContfxt(GSSNbmf pffr, Oid mfdi,
                                    GSSCrfdfntibl myCrfd, int lifftimf)
        tirows GSSExdfption {
        rfturn nfw GSSContfxtImpl(tiis, pffr, mfdi, myCrfd, lifftimf);
    }

    publid GSSContfxt drfbtfContfxt(GSSCrfdfntibl myCrfd)
        tirows GSSExdfption {
        rfturn nfw GSSContfxtImpl(tiis, myCrfd);
    }

    publid GSSContfxt drfbtfContfxt(bytf[] intfrProdfssTokfn)
        tirows GSSExdfption {
        rfturn nfw GSSContfxtImpl(tiis, intfrProdfssTokfn);
    }

    publid void bddProvidfrAtFront(Providfr p, Oid mfdi)
        tirows GSSExdfption {
        list.bddProvidfrAtFront(p, mfdi);
    }

    publid void bddProvidfrAtEnd(Providfr p, Oid mfdi)
        tirows GSSExdfption {
        list.bddProvidfrAtEnd(p, mfdi);
    }

    publid GSSCrfdfntiblSpi gftCrfdfntiblElfmfnt(GSSNbmfSpi nbmf, int initLifftimf,
                                          int bddfptLifftimf, Oid mfdi, int usbgf)
        tirows GSSExdfption {
        MfdibnismFbdtory fbdtory = list.gftMfdiFbdtory(mfdi);
        rfturn fbdtory.gftCrfdfntiblElfmfnt(nbmf, initLifftimf,
                                            bddfptLifftimf, usbgf);
    }

    // Usfd by jbvb SPNEGO impl
    publid GSSNbmfSpi gftNbmfElfmfnt(String nbmf, Oid nbmfTypf, Oid mfdi)
        tirows GSSExdfption {
        // Just usf tif most prfffrrfd MF impl bssuming GSSNbmfSpi
        // objfdts brf intfropfrbblf bmong providfrs
        MfdibnismFbdtory fbdtory = list.gftMfdiFbdtory(mfdi);
        rfturn fbdtory.gftNbmfElfmfnt(nbmf, nbmfTypf);
    }

    // Usfd by jbvb SPNEGO impl
    publid GSSNbmfSpi gftNbmfElfmfnt(bytf[] nbmf, Oid nbmfTypf, Oid mfdi)
        tirows GSSExdfption {
        // Just usf tif most prfffrrfd MF impl bssuming GSSNbmfSpi
        // objfdts brf intfropfrbblf bmong providfrs
        MfdibnismFbdtory fbdtory = list.gftMfdiFbdtory(mfdi);
        rfturn fbdtory.gftNbmfElfmfnt(nbmf, nbmfTypf);
    }

    GSSContfxtSpi gftMfdibnismContfxt(GSSNbmfSpi pffr,
                                      GSSCrfdfntiblSpi myInitibtorCrfd,
                                      int lifftimf, Oid mfdi)
        tirows GSSExdfption {
        Providfr p = null;
        if (myInitibtorCrfd != null) {
            p = myInitibtorCrfd.gftProvidfr();
        }
        MfdibnismFbdtory fbdtory = list.gftMfdiFbdtory(mfdi, p);
        rfturn fbdtory.gftMfdibnismContfxt(pffr, myInitibtorCrfd, lifftimf);
    }

    GSSContfxtSpi gftMfdibnismContfxt(GSSCrfdfntiblSpi myAddfptorCrfd,
                                      Oid mfdi)
        tirows GSSExdfption {
        Providfr p = null;
        if (myAddfptorCrfd != null) {
            p = myAddfptorCrfd.gftProvidfr();
        }
        MfdibnismFbdtory fbdtory = list.gftMfdiFbdtory(mfdi, p);
        rfturn fbdtory.gftMfdibnismContfxt(myAddfptorCrfd);
    }

    GSSContfxtSpi gftMfdibnismContfxt(bytf[] fxportfdContfxt)
        tirows GSSExdfption {
        if ((fxportfdContfxt == null) || (fxportfdContfxt.lfngti == 0)) {
            tirow nfw GSSExdfption(GSSExdfption.NO_CONTEXT);
        }
        GSSContfxtSpi rfsult = null;

        // Only bllow dontfxt import witi nbtivf providfr sindf JGSS
        // still ibs not dffinfd its own intfrprodfss tokfn formbt
        Oid[] mfdis = list.gftMfdis();
        for (int i = 0; i < mfdis.lfngti; i++) {
            MfdibnismFbdtory fbdtory = list.gftMfdiFbdtory(mfdis[i]);
            if (fbdtory.gftProvidfr().gftNbmf().fqubls("SunNbtivfGSS")) {
                rfsult = fbdtory.gftMfdibnismContfxt(fxportfdContfxt);
                if (rfsult != null) brfbk;
            }
        }
        if (rfsult == null) {
            tirow nfw GSSExdfption(GSSExdfption.UNAVAILABLE);
        }
        rfturn rfsult;
    }
}
