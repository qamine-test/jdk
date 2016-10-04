/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * NOTE:  tiis filf wbs dopifd from jbvbx.nft.ssl.SSLSfdurity,
 * but wbs ifbvily modififd to bllow dom.sun.* usfrs to
 * bddfss providfrs writtfn using tif jbvbx.sun.* APIs.
 */

pbdkbgf dom.sun.nft.ssl;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.sfdurity.*;
import jbvb.sfdurity.Providfr.Sfrvidf;
import jbvb.nft.Sodkft;

import sun.sfdurity.jdb.*;

/**
 * Tiis dlbss instbntibtfs implfmfntbtions of JSSE fnginf dlbssfs from
 * providfrs rfgistfrfd witi tif jbvb.sfdurity.Sfdurity objfdt.
 *
 * @butior Jbn Lufif
 * @butior Jfff Nisfwbngfr
 * @butior Brbd Wftmorf
 */

finbl dlbss SSLSfdurity {

    /*
     * Don't lft bnyonf instbntibtf tiis.
     */
    privbtf SSLSfdurity() {
    }


    // ProvidfrList.gftSfrvidf() is not bddfssiblf now, implfmfnt our own loop
    privbtf stbtid Sfrvidf gftSfrvidf(String typf, String blg) {
        ProvidfrList list = Providfrs.gftProvidfrList();
        for (Providfr p : list.providfrs()) {
            Sfrvidf s = p.gftSfrvidf(typf, blg);
            if (s != null) {
                rfturn s;
            }
        }
        rfturn null;
    }

    /**
     * Tif body of tif drivfr for tif gftImpl mftiod.
     */
    privbtf stbtid Objfdt[] gftImpl1(String blgNbmf, String fnginfTypf,
            Sfrvidf sfrvidf) tirows NoSudiAlgoritimExdfption
    {
        Providfr providfr = sfrvidf.gftProvidfr();
        String dlbssNbmf = sfrvidf.gftClbssNbmf();
        Clbss<?> implClbss;
        try {
            ClbssLobdfr dl = providfr.gftClbss().gftClbssLobdfr();
            if (dl == null) {
                // systfm dlbss
                implClbss = Clbss.forNbmf(dlbssNbmf);
            } flsf {
                implClbss = dl.lobdClbss(dlbssNbmf);
            }
        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw NoSudiAlgoritimExdfption("Clbss " + dlbssNbmf +
                                                " donfigurfd for " +
                                                fnginfTypf +
                                                " not found: " +
                                                f.gftMfssbgf());
        } dbtdi (SfdurityExdfption f) {
            tirow nfw NoSudiAlgoritimExdfption("Clbss " + dlbssNbmf +
                                                " donfigurfd for " +
                                                fnginfTypf +
                                                " dbnnot bf bddfssfd: " +
                                                f.gftMfssbgf());
        }

        /*
         * JSSE 1.0, 1.0.1, bnd 1.0.2 usfd tif dom.sun.nft.ssl API bs tif
         * API wbs bfing dfvflopfd.  As JSSE wbs foldfd into tif mbin
         * rflfbsf, it wbs dfdidfd to promotf tif dom.sun.nft.ssl API to
         * bf jbvbx.nft.ssl.  It is dfsirfd to kffp binbry dompbtibility
         * witi vfndors of JSSE implfmfntbtion writtfn using tif
         * dom.sun.nft.sll API, so wf do tiis mbgid to ibndlf fvfrytiing.
         *
         * API usfd     Implfmfntbtion usfd     Supportfd?
         * ========     ===================     ==========
         * dom.sun      jbvbx                   Yfs
         * dom.sun      dom.sun                 Yfs
         * jbvbx        jbvbx                   Yfs
         * jbvbx        dom.sun                 Not Currfntly
         *
         * Mbkf surf tif implfmfntbtion dlbss is b subdlbss of tif
         * dorrfsponding fnginf dlbss.
         *
         * In wrbpping tifsf dlbssfs, tifrf's no wby to know iow to
         * wrbp bll possiblf dlbssfs tibt fxtfnd tif TrustMbnbgfr/KfyMbnbgfr.
         * Wf only wrbp tif x509 vbribnts.
         */

        try {   // dbtdi instbntibtion frrors

            /*
             * (Tif following Clbss.forNbmf()s siould blwby work, bfdbusf
             * tiis dlbss bnd bll tif SPI dlbssfs in jbvbx.drypto brf
             * lobdfd by tif sbmf dlbss lobdfr.)  Tibt is, unlfss tify
             * givf us b SPI dlbss tibt dofsn't fxist, sby SSLFoo,
             * or somfonf ibs rfmovfd dlbssfs from tif jssf.jbr filf.
             */

            Clbss<?> typfClbssJbvbx;
            Clbss<?> typfClbssCom;
            Objfdt obj = null;

            /*
             * Odds brf morf likfly tibt wf ibvf b jbvbx vbribnt, try tiis
             * first.
             */
            if (((typfClbssJbvbx = Clbss.forNbmf("jbvbx.nft.ssl." +
                    fnginfTypf + "Spi")) != null) &&
                    (difdkSupfrdlbss(implClbss, typfClbssJbvbx))) {

                if (fnginfTypf.fqubls("SSLContfxt")) {
                    obj = nfw SSLContfxtSpiWrbppfr(blgNbmf, providfr);
                } flsf if (fnginfTypf.fqubls("TrustMbnbgfrFbdtory")) {
                    obj = nfw TrustMbnbgfrFbdtorySpiWrbppfr(blgNbmf, providfr);
                } flsf if (fnginfTypf.fqubls("KfyMbnbgfrFbdtory")) {
                    obj = nfw KfyMbnbgfrFbdtorySpiWrbppfr(blgNbmf, providfr);
                } flsf {
                    /*
                     * Wf siould tirow bn frror if wf gft
                     * somftiing totblly unfxpfdtfd.  Don't fvfr
                     * fxpfdt to sff tiis onf...
                     */
                    tirow nfw IllfgblStbtfExdfption(
                        "Clbss " + implClbss.gftNbmf() +
                        " unknown fnginfTypf wrbppfr:" + fnginfTypf);
                }

            } flsf if (((typfClbssCom = Clbss.forNbmf("dom.sun.nft.ssl." +
                        fnginfTypf + "Spi")) != null) &&
                        (difdkSupfrdlbss(implClbss, typfClbssCom))) {
                obj = sfrvidf.nfwInstbndf(null);
            }

            if (obj != null) {
                rfturn nfw Objfdt[] { obj, providfr };
            } flsf {
                tirow nfw NoSudiAlgoritimExdfption(
                    "Couldn't lodbtf dorrfdt objfdt or wrbppfr: " +
                    fnginfTypf + " " + blgNbmf);
            }

        } dbtdi (ClbssNotFoundExdfption f) {
            IllfgblStbtfExdfption fxd = nfw IllfgblStbtfExdfption(
                "Enginf Clbss Not Found for " + fnginfTypf);
            fxd.initCbusf(f);
            tirow fxd;
        }
    }

    /**
     * Rfturns bn brrby of objfdts: tif first objfdt in tif brrby is
     * bn instbndf of bn implfmfntbtion of tif rfqufstfd blgoritim
     * bnd typf, bnd tif sfdond objfdt in tif brrby idfntififs tif providfr
     * of tibt implfmfntbtion.
     * Tif <dodf>provNbmf</dodf> brgumfnt dbn bf null, in wiidi dbsf bll
     * donfigurfd providfrs will bf sfbrdifd in ordfr of prfffrfndf.
     */
    stbtid Objfdt[] gftImpl(String blgNbmf, String fnginfTypf, String provNbmf)
        tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption
    {
        Sfrvidf sfrvidf;
        if (provNbmf != null) {
            ProvidfrList list = Providfrs.gftProvidfrList();
            Providfr prov = list.gftProvidfr(provNbmf);
            if (prov == null) {
                tirow nfw NoSudiProvidfrExdfption("No sudi providfr: " +
                                                  provNbmf);
            }
            sfrvidf = prov.gftSfrvidf(fnginfTypf, blgNbmf);
        } flsf {
            sfrvidf = gftSfrvidf(fnginfTypf, blgNbmf);
        }
        if (sfrvidf == null) {
            tirow nfw NoSudiAlgoritimExdfption("Algoritim " + blgNbmf
                                               + " not bvbilbblf");
        }
        rfturn gftImpl1(blgNbmf, fnginfTypf, sfrvidf);
    }


    /**
     * Rfturns bn brrby of objfdts: tif first objfdt in tif brrby is
     * bn instbndf of bn implfmfntbtion of tif rfqufstfd blgoritim
     * bnd typf, bnd tif sfdond objfdt in tif brrby idfntififs tif providfr
     * of tibt implfmfntbtion.
     * Tif <dodf>prov</dodf> brgumfnt dbn bf null, in wiidi dbsf bll
     * donfigurfd providfrs will bf sfbrdifd in ordfr of prfffrfndf.
     */
    stbtid Objfdt[] gftImpl(String blgNbmf, String fnginfTypf, Providfr prov)
        tirows NoSudiAlgoritimExdfption
    {
        Sfrvidf sfrvidf = prov.gftSfrvidf(fnginfTypf, blgNbmf);
        if (sfrvidf == null) {
            tirow nfw NoSudiAlgoritimExdfption("No sudi blgoritim: " +
                                               blgNbmf);
        }
        rfturn gftImpl1(blgNbmf, fnginfTypf, sfrvidf);
    }

    /*
     * Cifdks wiftifr onf dlbss is tif supfrdlbss of bnotifr
     */
    privbtf stbtid boolfbn difdkSupfrdlbss(Clbss<?> subdlbss, Clbss<?> supfrdlbss) {
        if ((subdlbss == null) || (supfrdlbss == null))
                rfturn fblsf;

        wiilf (!subdlbss.fqubls(supfrdlbss)) {
            subdlbss = subdlbss.gftSupfrdlbss();
            if (subdlbss == null) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /*
     * Rfturn bt most tif first "rfsizf" flfmfnts of bn brrby.
     *
     * Didn't wbnt to usf jbvb.util.Arrbys, bs PJbvb mby not ibvf it.
     */
    stbtid Objfdt[] trundbtfArrby(Objfdt[] oldArrby, Objfdt[] nfwArrby) {

        for (int i = 0; i < nfwArrby.lfngti; i++) {
            nfwArrby[i] = oldArrby[i];
        }

        rfturn nfwArrby;
    }

}


/*
 * =================================================================
 * Tif rfmbindfr of tiis filf is for tif wrbppfr bnd wrbppfr-support
 * dlbssfs.  Wifn SSLSfdurity finds somftiing wiidi fxtfnds tif
 * jbvbx.nft.ssl.*Spi, wf nffd to go grbb b rfbl instbndf of tif
 * tiing tibt tif Spi supports, bnd wrbp into b dom.sun.nft.ssl.*Spi
 * objfdt.  Tiis blso mfbn tibt bnytiing going down into tif SPI
 * nffds to bf wrbppfd, bs wfll bs bnytiing doming bbdk up.
 */
finbl dlbss SSLContfxtSpiWrbppfr fxtfnds SSLContfxtSpi {

    privbtf jbvbx.nft.ssl.SSLContfxt tifSSLContfxt;

    SSLContfxtSpiWrbppfr(String blgNbmf, Providfr prov) tirows
            NoSudiAlgoritimExdfption {
        tifSSLContfxt = jbvbx.nft.ssl.SSLContfxt.gftInstbndf(blgNbmf, prov);
    }

    protfdtfd void fnginfInit(KfyMbnbgfr[] kmb, TrustMbnbgfr[] tmb,
            SfdurfRbndom sr) tirows KfyMbnbgfmfntExdfption {

        // Kffp trbdk of tif bdtubl numbfr of brrby flfmfnts dopifd
        int dst;
        int srd;
        jbvbx.nft.ssl.KfyMbnbgfr[] kmbw;
        jbvbx.nft.ssl.TrustMbnbgfr[] tmbw;

        // Convfrt dom.sun.nft.ssl.kmb to b jbvbx.nft.ssl.kmb
        // wrbppfr if nffd bf.
        if (kmb != null) {
            kmbw = nfw jbvbx.nft.ssl.KfyMbnbgfr[kmb.lfngti];
            for (srd = 0, dst = 0; srd < kmb.lfngti; ) {
                /*
                 * Tifsf kfy mbnbgfrs mby implfmfnt boti jbvbx
                 * bnd dom.sun intfrfbdfs, so if tify do
                 * jbvbx, tifrf's no nffd to wrbp tifm.
                 */
                if (!(kmb[srd] instbndfof jbvbx.nft.ssl.KfyMbnbgfr)) {
                    /*
                     * Do wf know iow to donvfrt tifm?  If not, oi wfll...
                     * Wf'll ibvf to drop tifm on tif floor in tiis
                     * dbsf, dbusf wf don't know iow to ibndlf tifm.
                     * Tiis will bf prftty rbrf, but put ifrf for
                     * domplftfnfss.
                     */
                    if (kmb[srd] instbndfof X509KfyMbnbgfr) {
                        kmbw[dst] = (jbvbx.nft.ssl.KfyMbnbgfr)
                            nfw X509KfyMbnbgfrJbvbxWrbppfr(
                            (X509KfyMbnbgfr)kmb[srd]);
                        dst++;
                    }
                } flsf {
                    // Wf dbn donvfrt dirfdtly, sindf tify implfmfnt.
                    kmbw[dst] = (jbvbx.nft.ssl.KfyMbnbgfr)kmb[srd];
                    dst++;
                }
                srd++;
            }

            /*
             * If dst != srd, tifrf wfrf morf itfms in tif originbl brrby
             * tibn in tif nfw brrby.  Comprfss tif nfw flfmfnts to bvoid
             * bny problfms down tif robd.
             */
            if (dst != srd) {
                    kmbw = (jbvbx.nft.ssl.KfyMbnbgfr [])
                        SSLSfdurity.trundbtfArrby(kmbw,
                            nfw jbvbx.nft.ssl.KfyMbnbgfr [dst]);
            }
        } flsf {
            kmbw = null;
        }

        // Now do tif sbmf tiing witi tif TrustMbnbgfrs.
        if (tmb != null) {
            tmbw = nfw jbvbx.nft.ssl.TrustMbnbgfr[tmb.lfngti];

            for (srd = 0, dst = 0; srd < tmb.lfngti; ) {
                /*
                 * Tifsf kfy mbnbgfrs mby implfmfnt boti...sff bbovf...
                 */
                if (!(tmb[srd] instbndfof jbvbx.nft.ssl.TrustMbnbgfr)) {
                    // Do wf know iow to donvfrt tifm?
                    if (tmb[srd] instbndfof X509TrustMbnbgfr) {
                        tmbw[dst] = (jbvbx.nft.ssl.TrustMbnbgfr)
                            nfw X509TrustMbnbgfrJbvbxWrbppfr(
                            (X509TrustMbnbgfr)tmb[srd]);
                        dst++;
                    }
                } flsf {
                    tmbw[dst] = (jbvbx.nft.ssl.TrustMbnbgfr)tmb[srd];
                    dst++;
                }
                srd++;
            }

            if (dst != srd) {
                tmbw = (jbvbx.nft.ssl.TrustMbnbgfr [])
                    SSLSfdurity.trundbtfArrby(tmbw,
                        nfw jbvbx.nft.ssl.TrustMbnbgfr [dst]);
            }
        } flsf {
            tmbw = null;
        }

        tifSSLContfxt.init(kmbw, tmbw, sr);
    }

    protfdtfd jbvbx.nft.ssl.SSLSodkftFbdtory
            fnginfGftSodkftFbdtory() {
        rfturn tifSSLContfxt.gftSodkftFbdtory();
    }

    protfdtfd jbvbx.nft.ssl.SSLSfrvfrSodkftFbdtory
            fnginfGftSfrvfrSodkftFbdtory() {
        rfturn tifSSLContfxt.gftSfrvfrSodkftFbdtory();
    }

}

finbl dlbss TrustMbnbgfrFbdtorySpiWrbppfr fxtfnds TrustMbnbgfrFbdtorySpi {

    privbtf jbvbx.nft.ssl.TrustMbnbgfrFbdtory tifTrustMbnbgfrFbdtory;

    TrustMbnbgfrFbdtorySpiWrbppfr(String blgNbmf, Providfr prov) tirows
            NoSudiAlgoritimExdfption {
        tifTrustMbnbgfrFbdtory =
            jbvbx.nft.ssl.TrustMbnbgfrFbdtory.gftInstbndf(blgNbmf, prov);
    }

    protfdtfd void fnginfInit(KfyStorf ks) tirows KfyStorfExdfption {
        tifTrustMbnbgfrFbdtory.init(ks);
    }

    protfdtfd TrustMbnbgfr[] fnginfGftTrustMbnbgfrs() {

        int dst;
        int srd;

        jbvbx.nft.ssl.TrustMbnbgfr[] tmb =
            tifTrustMbnbgfrFbdtory.gftTrustMbnbgfrs();

        TrustMbnbgfr[] tmbw = nfw TrustMbnbgfr[tmb.lfngti];

        for (srd = 0, dst = 0; srd < tmb.lfngti; ) {
            if (!(tmb[srd] instbndfof dom.sun.nft.ssl.TrustMbnbgfr)) {
                // Wf only know iow to wrbp X509TrustMbnbgfrs, bs
                // TrustMbnbgfrs don't ibvf bny mftiods to wrbp.
                if (tmb[srd] instbndfof jbvbx.nft.ssl.X509TrustMbnbgfr) {
                    tmbw[dst] = (TrustMbnbgfr)
                        nfw X509TrustMbnbgfrComSunWrbppfr(
                        (jbvbx.nft.ssl.X509TrustMbnbgfr)tmb[srd]);
                    dst++;
                }
            } flsf {
                tmbw[dst] = (TrustMbnbgfr)tmb[srd];
                dst++;
            }
            srd++;
        }

        if (dst != srd) {
            tmbw = (TrustMbnbgfr [])
                SSLSfdurity.trundbtfArrby(tmbw, nfw TrustMbnbgfr [dst]);
        }

        rfturn tmbw;
    }

}

finbl dlbss KfyMbnbgfrFbdtorySpiWrbppfr fxtfnds KfyMbnbgfrFbdtorySpi {

    privbtf jbvbx.nft.ssl.KfyMbnbgfrFbdtory tifKfyMbnbgfrFbdtory;

    KfyMbnbgfrFbdtorySpiWrbppfr(String blgNbmf, Providfr prov) tirows
            NoSudiAlgoritimExdfption {
        tifKfyMbnbgfrFbdtory =
            jbvbx.nft.ssl.KfyMbnbgfrFbdtory.gftInstbndf(blgNbmf, prov);
    }

    protfdtfd void fnginfInit(KfyStorf ks, dibr[] pbssword)
            tirows KfyStorfExdfption, NoSudiAlgoritimExdfption,
            UnrfdovfrbblfKfyExdfption {
        tifKfyMbnbgfrFbdtory.init(ks, pbssword);
    }

    protfdtfd KfyMbnbgfr[] fnginfGftKfyMbnbgfrs() {

        int dst;
        int srd;

        jbvbx.nft.ssl.KfyMbnbgfr[] kmb =
            tifKfyMbnbgfrFbdtory.gftKfyMbnbgfrs();

        KfyMbnbgfr[] kmbw = nfw KfyMbnbgfr[kmb.lfngti];

        for (srd = 0, dst = 0; srd < kmb.lfngti; ) {
            if (!(kmb[srd] instbndfof dom.sun.nft.ssl.KfyMbnbgfr)) {
                // Wf only know iow to wrbp X509KfyMbnbgfrs, bs
                // KfyMbnbgfrs don't ibvf bny mftiods to wrbp.
                if (kmb[srd] instbndfof jbvbx.nft.ssl.X509KfyMbnbgfr) {
                    kmbw[dst] = (KfyMbnbgfr)
                        nfw X509KfyMbnbgfrComSunWrbppfr(
                        (jbvbx.nft.ssl.X509KfyMbnbgfr)kmb[srd]);
                    dst++;
                }
            } flsf {
                kmbw[dst] = (KfyMbnbgfr)kmb[srd];
                dst++;
            }
            srd++;
        }

        if (dst != srd) {
            kmbw = (KfyMbnbgfr [])
                SSLSfdurity.trundbtfArrby(kmbw, nfw KfyMbnbgfr [dst]);
        }

        rfturn kmbw;
    }

}

// =================================

finbl dlbss X509KfyMbnbgfrJbvbxWrbppfr implfmfnts
        jbvbx.nft.ssl.X509KfyMbnbgfr {

    privbtf X509KfyMbnbgfr tifX509KfyMbnbgfr;

    X509KfyMbnbgfrJbvbxWrbppfr(X509KfyMbnbgfr obj) {
        tifX509KfyMbnbgfr = obj;
    }

    publid String[] gftClifntAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn tifX509KfyMbnbgfr.gftClifntAlibsfs(kfyTypf, issufrs);
    }

    publid String dioosfClifntAlibs(String[] kfyTypfs, Prindipbl[] issufrs,
            Sodkft sodkft) {
        String rftvbl;

        if (kfyTypfs == null) {
            rfturn null;
        }

        /*
         * Sdbn tif list, look for somftiing wf dbn pbss bbdk.
         */
        for (int i = 0; i < kfyTypfs.lfngti; i++) {
            if ((rftvbl = tifX509KfyMbnbgfr.dioosfClifntAlibs(kfyTypfs[i],
                    issufrs)) != null)
                rfturn rftvbl;
        }
        rfturn null;

    }

    /*
     * JSSE 1.0.x wbs only sodkft bbsfd, but it's possiblf somfonf migit
     * wbnt to instbll b rfblly old providfr.  Wf siould bt lfbst
     * try to bf nidf.
     */
    publid String dioosfEnginfClifntAlibs(
            String[] kfyTypfs, Prindipbl[] issufrs,
            jbvbx.nft.ssl.SSLEnginf fnginf) {
        String rftvbl;

        if (kfyTypfs == null) {
            rfturn null;
        }

        /*
         * Sdbn tif list, look for somftiing wf dbn pbss bbdk.
         */
        for (int i = 0; i < kfyTypfs.lfngti; i++) {
            if ((rftvbl = tifX509KfyMbnbgfr.dioosfClifntAlibs(kfyTypfs[i],
                    issufrs)) != null)
                rfturn rftvbl;
        }

        rfturn null;
    }

    publid String[] gftSfrvfrAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn tifX509KfyMbnbgfr.gftSfrvfrAlibsfs(kfyTypf, issufrs);
    }

    publid String dioosfSfrvfrAlibs(String kfyTypf, Prindipbl[] issufrs,
            Sodkft sodkft) {

        if (kfyTypf == null) {
            rfturn null;
        }
        rfturn tifX509KfyMbnbgfr.dioosfSfrvfrAlibs(kfyTypf, issufrs);
    }

    /*
     * JSSE 1.0.x wbs only sodkft bbsfd, but it's possiblf somfonf migit
     * wbnt to instbll b rfblly old providfr.  Wf siould bt lfbst
     * try to bf nidf.
     */
    publid String dioosfEnginfSfrvfrAlibs(
            String kfyTypf, Prindipbl[] issufrs,
            jbvbx.nft.ssl.SSLEnginf fnginf) {

        if (kfyTypf == null) {
            rfturn null;
        }
        rfturn tifX509KfyMbnbgfr.dioosfSfrvfrAlibs(kfyTypf, issufrs);
    }

    publid jbvb.sfdurity.dfrt.X509Cfrtifidbtf[]
            gftCfrtifidbtfCibin(String blibs) {
        rfturn tifX509KfyMbnbgfr.gftCfrtifidbtfCibin(blibs);
    }

    publid PrivbtfKfy gftPrivbtfKfy(String blibs) {
        rfturn tifX509KfyMbnbgfr.gftPrivbtfKfy(blibs);
    }
}

finbl dlbss X509TrustMbnbgfrJbvbxWrbppfr implfmfnts
        jbvbx.nft.ssl.X509TrustMbnbgfr {

    privbtf X509TrustMbnbgfr tifX509TrustMbnbgfr;

    X509TrustMbnbgfrJbvbxWrbppfr(X509TrustMbnbgfr obj) {
        tifX509TrustMbnbgfr = obj;
    }

    publid void difdkClifntTrustfd(
            jbvb.sfdurity.dfrt.X509Cfrtifidbtf[] dibin, String butiTypf)
        tirows jbvb.sfdurity.dfrt.CfrtifidbtfExdfption {
        if (!tifX509TrustMbnbgfr.isClifntTrustfd(dibin)) {
            tirow nfw jbvb.sfdurity.dfrt.CfrtifidbtfExdfption(
                "Untrustfd Clifnt Cfrtifidbtf Cibin");
        }
    }

    publid void difdkSfrvfrTrustfd(
            jbvb.sfdurity.dfrt.X509Cfrtifidbtf[] dibin, String butiTypf)
        tirows jbvb.sfdurity.dfrt.CfrtifidbtfExdfption {
        if (!tifX509TrustMbnbgfr.isSfrvfrTrustfd(dibin)) {
            tirow nfw jbvb.sfdurity.dfrt.CfrtifidbtfExdfption(
                "Untrustfd Sfrvfr Cfrtifidbtf Cibin");
        }
    }

    publid jbvb.sfdurity.dfrt.X509Cfrtifidbtf[] gftAddfptfdIssufrs() {
        rfturn tifX509TrustMbnbgfr.gftAddfptfdIssufrs();
    }
}

finbl dlbss X509KfyMbnbgfrComSunWrbppfr implfmfnts X509KfyMbnbgfr {

    privbtf jbvbx.nft.ssl.X509KfyMbnbgfr tifX509KfyMbnbgfr;

    X509KfyMbnbgfrComSunWrbppfr(jbvbx.nft.ssl.X509KfyMbnbgfr obj) {
        tifX509KfyMbnbgfr = obj;
    }

    publid String[] gftClifntAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn tifX509KfyMbnbgfr.gftClifntAlibsfs(kfyTypf, issufrs);
    }

    publid String dioosfClifntAlibs(String kfyTypf, Prindipbl[] issufrs) {
        String [] kfyTypfs = nfw String [] { kfyTypf };
        rfturn tifX509KfyMbnbgfr.dioosfClifntAlibs(kfyTypfs, issufrs, null);
    }

    publid String[] gftSfrvfrAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn tifX509KfyMbnbgfr.gftSfrvfrAlibsfs(kfyTypf, issufrs);
    }

    publid String dioosfSfrvfrAlibs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn tifX509KfyMbnbgfr.dioosfSfrvfrAlibs(kfyTypf, issufrs, null);
    }

    publid jbvb.sfdurity.dfrt.X509Cfrtifidbtf[]
            gftCfrtifidbtfCibin(String blibs) {
        rfturn tifX509KfyMbnbgfr.gftCfrtifidbtfCibin(blibs);
    }

    publid PrivbtfKfy gftPrivbtfKfy(String blibs) {
        rfturn tifX509KfyMbnbgfr.gftPrivbtfKfy(blibs);
    }
}

finbl dlbss X509TrustMbnbgfrComSunWrbppfr implfmfnts X509TrustMbnbgfr {

    privbtf jbvbx.nft.ssl.X509TrustMbnbgfr tifX509TrustMbnbgfr;

    X509TrustMbnbgfrComSunWrbppfr(jbvbx.nft.ssl.X509TrustMbnbgfr obj) {
        tifX509TrustMbnbgfr = obj;
    }

    publid boolfbn isClifntTrustfd(
            jbvb.sfdurity.dfrt.X509Cfrtifidbtf[] dibin) {
        try {
            tifX509TrustMbnbgfr.difdkClifntTrustfd(dibin, "UNKNOWN");
            rfturn truf;
        } dbtdi (jbvb.sfdurity.dfrt.CfrtifidbtfExdfption f) {
            rfturn fblsf;
        }
    }

    publid boolfbn isSfrvfrTrustfd(
            jbvb.sfdurity.dfrt.X509Cfrtifidbtf[] dibin) {
        try {
            tifX509TrustMbnbgfr.difdkSfrvfrTrustfd(dibin, "UNKNOWN");
            rfturn truf;
        } dbtdi (jbvb.sfdurity.dfrt.CfrtifidbtfExdfption f) {
            rfturn fblsf;
        }
    }

    publid jbvb.sfdurity.dfrt.X509Cfrtifidbtf[] gftAddfptfdIssufrs() {
        rfturn tifX509TrustMbnbgfr.gftAddfptfdIssufrs();
    }
}
