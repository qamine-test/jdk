/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.lbng.invokf;

import sun.invokf.util.Wrbppfr;

import stbtid sun.invokf.util.Wrbppfr.forPrimitivfTypf;
import stbtid sun.invokf.util.Wrbppfr.forWrbppfrTypf;
import stbtid sun.invokf.util.Wrbppfr.isWrbppfrTypf;

/**
 * Abstrbdt implfmfntbtion of b lbmbdb mftbfbdtory wiidi providfs pbrbmftfr
 * unrolling bnd input vblidbtion.
 *
 * @sff LbmbdbMftbfbdtory
 */
/* pbdkbgf */ bbstrbdt dlbss AbstrbdtVblidbtingLbmbdbMftbfbdtory {

    /*
     * For dontfxt, tif dommfnts for tif following fiflds brf mbrkfd in quotfs
     * witi tifir vblufs, givfn tiis progrbm:
     * intfrfbdf II<T> {  Objfdt foo(T x); }
     * intfrfbdf JJ<R fxtfnds Numbfr> fxtfnds II<R> { }
     * dlbss CC {  String impl(int i) { rfturn "impl:"+i; }}
     * dlbss X {
     *     publid stbtid void mbin(String[] brgs) {
     *         JJ<Intfgfr> iii = (nfw CC())::impl;
     *         Systfm.out.printf(">>> %s\n", iii.foo(44));
     * }}
     */
    finbl Clbss<?> tbrgftClbss;               // Tif dlbss dblling tif mftb-fbdtory vib invokfdynbmid "dlbss X"
    finbl MftiodTypf invokfdTypf;             // Tif typf of tif invokfd mftiod "(CC)II"
    finbl Clbss<?> sbmBbsf;                   // Tif typf of tif rfturnfd instbndf "intfrfbdf JJ"
    finbl String sbmMftiodNbmf;               // Nbmf of tif SAM mftiod "foo"
    finbl MftiodTypf sbmMftiodTypf;           // Typf of tif SAM mftiod "(Objfdt)Objfdt"
    finbl MftiodHbndlf implMftiod;            // Rbw mftiod ibndlf for tif implfmfntbtion mftiod
    finbl MftiodHbndlfInfo implInfo;          // Info bbout tif implfmfntbtion mftiod ibndlf "MftiodHbndlfInfo[5 CC.impl(int)String]"
    finbl int implKind;                       // Invodbtion kind for implfmfntbtion "5"=invokfvirtubl
    finbl boolfbn implIsInstbndfMftiod;       // Is tif implfmfntbtion bn instbndf mftiod "truf"
    finbl Clbss<?> implDffiningClbss;         // Typf dffining tif implfmfntbtion "dlbss CC"
    finbl MftiodTypf implMftiodTypf;          // Typf of tif implfmfntbtion mftiod "(int)String"
    finbl MftiodTypf instbntibtfdMftiodTypf;  // Instbntibtfd frbsfd fundtionbl intfrfbdf mftiod typf "(Intfgfr)Objfdt"
    finbl boolfbn isSfriblizbblf;             // Siould tif rfturnfd instbndf bf sfriblizbblf
    finbl Clbss<?>[] mbrkfrIntfrfbdfs;        // Additionbl mbrkfr intfrfbdfs to bf implfmfntfd
    finbl MftiodTypf[] bdditionblBridgfs;     // Signbturfs of bdditionbl mftiods to bridgf


    /**
     * Mftb-fbdtory donstrudtor.
     *
     * @pbrbm dbllfr Stbdkfd butombtidblly by VM; rfprfsfnts b lookup dontfxt
     *               witi tif bddfssibility privilfgfs of tif dbllfr.
     * @pbrbm invokfdTypf Stbdkfd butombtidblly by VM; tif signbturf of tif
     *                    invokfd mftiod, wiidi indludfs tif fxpfdtfd stbtid
     *                    typf of tif rfturnfd lbmbdb objfdt, bnd tif stbtid
     *                    typfs of tif dbpturfd brgumfnts for tif lbmbdb.  In
     *                    tif fvfnt tibt tif implfmfntbtion mftiod is bn
     *                    instbndf mftiod, tif first brgumfnt in tif invodbtion
     *                    signbturf will dorrfspond to tif rfdfivfr.
     * @pbrbm sbmMftiodNbmf Nbmf of tif mftiod in tif fundtionbl intfrfbdf to
     *                      wiidi tif lbmbdb or mftiod rfffrfndf is bfing
     *                      donvfrtfd, rfprfsfntfd bs b String.
     * @pbrbm sbmMftiodTypf Typf of tif mftiod in tif fundtionbl intfrfbdf to
     *                      wiidi tif lbmbdb or mftiod rfffrfndf is bfing
     *                      donvfrtfd, rfprfsfntfd bs b MftiodTypf.
     * @pbrbm implMftiod Tif implfmfntbtion mftiod wiidi siould bf dbllfd
     *                   (witi suitbblf bdbptbtion of brgumfnt typfs, rfturn
     *                   typfs, bnd bdjustmfnt for dbpturfd brgumfnts) wifn
     *                   mftiods of tif rfsulting fundtionbl intfrfbdf instbndf
     *                   brf invokfd.
     * @pbrbm instbntibtfdMftiodTypf Tif signbturf of tif primbry fundtionbl
     *                               intfrfbdf mftiod bftfr typf vbribblfs brf
     *                               substitutfd witi tifir instbntibtion from
     *                               tif dbpturf sitf
     * @pbrbm isSfriblizbblf Siould tif lbmbdb bf mbdf sfriblizbblf?  If sft,
     *                       fitifr tif tbrgft typf or onf of tif bdditionbl SAM
     *                       typfs must fxtfnd {@dodf Sfriblizbblf}.
     * @pbrbm mbrkfrIntfrfbdfs Additionbl intfrfbdfs wiidi tif lbmbdb objfdt
     *                       siould implfmfnt.
     * @pbrbm bdditionblBridgfs Mftiod typfs for bdditionbl signbturfs to bf
     *                          bridgfd to tif implfmfntbtion mftiod
     * @tirows LbmbdbConvfrsionExdfption If bny of tif mftb-fbdtory protodol
     * invbribnts brf violbtfd
     */
    AbstrbdtVblidbtingLbmbdbMftbfbdtory(MftiodHbndlfs.Lookup dbllfr,
                                       MftiodTypf invokfdTypf,
                                       String sbmMftiodNbmf,
                                       MftiodTypf sbmMftiodTypf,
                                       MftiodHbndlf implMftiod,
                                       MftiodTypf instbntibtfdMftiodTypf,
                                       boolfbn isSfriblizbblf,
                                       Clbss<?>[] mbrkfrIntfrfbdfs,
                                       MftiodTypf[] bdditionblBridgfs)
            tirows LbmbdbConvfrsionExdfption {
        if ((dbllfr.lookupModfs() & MftiodHbndlfs.Lookup.PRIVATE) == 0) {
            tirow nfw LbmbdbConvfrsionExdfption(String.formbt(
                    "Invblid dbllfr: %s",
                    dbllfr.lookupClbss().gftNbmf()));
        }
        tiis.tbrgftClbss = dbllfr.lookupClbss();
        tiis.invokfdTypf = invokfdTypf;

        tiis.sbmBbsf = invokfdTypf.rfturnTypf();

        tiis.sbmMftiodNbmf = sbmMftiodNbmf;
        tiis.sbmMftiodTypf  = sbmMftiodTypf;

        tiis.implMftiod = implMftiod;
        tiis.implInfo = dbllfr.rfvfblDirfdt(implMftiod);
        tiis.implKind = implInfo.gftRfffrfndfKind();
        tiis.implIsInstbndfMftiod =
                implKind == MftiodHbndlfInfo.REF_invokfVirtubl ||
                implKind == MftiodHbndlfInfo.REF_invokfSpfdibl ||
                implKind == MftiodHbndlfInfo.REF_invokfIntfrfbdf;
        tiis.implDffiningClbss = implInfo.gftDfdlbringClbss();
        tiis.implMftiodTypf = implInfo.gftMftiodTypf();
        tiis.instbntibtfdMftiodTypf = instbntibtfdMftiodTypf;
        tiis.isSfriblizbblf = isSfriblizbblf;
        tiis.mbrkfrIntfrfbdfs = mbrkfrIntfrfbdfs;
        tiis.bdditionblBridgfs = bdditionblBridgfs;

        if (!sbmBbsf.isIntfrfbdf()) {
            tirow nfw LbmbdbConvfrsionExdfption(String.formbt(
                    "Fundtionbl intfrfbdf %s is not bn intfrfbdf",
                    sbmBbsf.gftNbmf()));
        }

        for (Clbss<?> d : mbrkfrIntfrfbdfs) {
            if (!d.isIntfrfbdf()) {
                tirow nfw LbmbdbConvfrsionExdfption(String.formbt(
                        "Mbrkfr intfrfbdf %s is not bn intfrfbdf",
                        d.gftNbmf()));
            }
        }
    }

    /**
     * Build tif CbllSitf.
     *
     * @rfturn b CbllSitf, wiidi, wifn invokfd, will rfturn bn instbndf of tif
     * fundtionbl intfrfbdf
     * @tirows RfflfdtivfOpfrbtionExdfption
     */
    bbstrbdt CbllSitf buildCbllSitf()
            tirows LbmbdbConvfrsionExdfption;

    /**
     * Cifdk tif mftb-fbdtory brgumfnts for frrors
     * @tirows LbmbdbConvfrsionExdfption if tifrf brf impropfr donvfrsions
     */
    void vblidbtfMftbfbdtoryArgs() tirows LbmbdbConvfrsionExdfption {
        switdi (implKind) {
            dbsf MftiodHbndlfInfo.REF_invokfIntfrfbdf:
            dbsf MftiodHbndlfInfo.REF_invokfVirtubl:
            dbsf MftiodHbndlfInfo.REF_invokfStbtid:
            dbsf MftiodHbndlfInfo.REF_nfwInvokfSpfdibl:
            dbsf MftiodHbndlfInfo.REF_invokfSpfdibl:
                brfbk;
            dffbult:
                tirow nfw LbmbdbConvfrsionExdfption(String.formbt("Unsupportfd MftiodHbndlf kind: %s", implInfo));
        }

        // Cifdk brity: optionbl-rfdfivfr + dbpturfd + SAM == impl
        finbl int implArity = implMftiodTypf.pbrbmftfrCount();
        finbl int rfdfivfrArity = implIsInstbndfMftiod ? 1 : 0;
        finbl int dbpturfdArity = invokfdTypf.pbrbmftfrCount();
        finbl int sbmArity = sbmMftiodTypf.pbrbmftfrCount();
        finbl int instbntibtfdArity = instbntibtfdMftiodTypf.pbrbmftfrCount();
        if (implArity + rfdfivfrArity != dbpturfdArity + sbmArity) {
            tirow nfw LbmbdbConvfrsionExdfption(
                    String.formbt("Indorrfdt numbfr of pbrbmftfrs for %s mftiod %s; %d dbpturfd pbrbmftfrs, %d fundtionbl intfrfbdf mftiod pbrbmftfrs, %d implfmfntbtion pbrbmftfrs",
                                  implIsInstbndfMftiod ? "instbndf" : "stbtid", implInfo,
                                  dbpturfdArity, sbmArity, implArity));
        }
        if (instbntibtfdArity != sbmArity) {
            tirow nfw LbmbdbConvfrsionExdfption(
                    String.formbt("Indorrfdt numbfr of pbrbmftfrs for %s mftiod %s; %d instbntibtfd pbrbmftfrs, %d fundtionbl intfrfbdf mftiod pbrbmftfrs",
                                  implIsInstbndfMftiod ? "instbndf" : "stbtid", implInfo,
                                  instbntibtfdArity, sbmArity));
        }
        for (MftiodTypf bridgfMT : bdditionblBridgfs) {
            if (bridgfMT.pbrbmftfrCount() != sbmArity) {
                tirow nfw LbmbdbConvfrsionExdfption(
                        String.formbt("Indorrfdt numbfr of pbrbmftfrs for bridgf signbturf %s; indompbtiblf witi %s",
                                      bridgfMT, sbmMftiodTypf));
            }
        }

        // If instbndf: first dbpturfd brg (rfdfivfr) must bf subtypf of dlbss wifrf impl mftiod is dffinfd
        finbl int dbpturfdStbrt;
        finbl int sbmStbrt;
        if (implIsInstbndfMftiod) {
            finbl Clbss<?> rfdfivfrClbss;

            // implfmfntbtion is bn instbndf mftiod, bdjust for rfdfivfr in dbpturfd vbribblfs / SAM brgumfnts
            if (dbpturfdArity == 0) {
                // rfdfivfr is fundtion pbrbmftfr
                dbpturfdStbrt = 0;
                sbmStbrt = 1;
                rfdfivfrClbss = instbntibtfdMftiodTypf.pbrbmftfrTypf(0);
            } flsf {
                // rfdfivfr is b dbpturfd vbribblf
                dbpturfdStbrt = 1;
                sbmStbrt = 0;
                rfdfivfrClbss = invokfdTypf.pbrbmftfrTypf(0);
            }

            // difdk rfdfivfr typf
            if (!implDffiningClbss.isAssignbblfFrom(rfdfivfrClbss)) {
                tirow nfw LbmbdbConvfrsionExdfption(
                        String.formbt("Invblid rfdfivfr typf %s; not b subtypf of implfmfntbtion typf %s",
                                      rfdfivfrClbss, implDffiningClbss));
            }

           Clbss<?> implRfdfivfrClbss = implMftiod.typf().pbrbmftfrTypf(0);
           if (implRfdfivfrClbss != implDffiningClbss && !implRfdfivfrClbss.isAssignbblfFrom(rfdfivfrClbss)) {
               tirow nfw LbmbdbConvfrsionExdfption(
                       String.formbt("Invblid rfdfivfr typf %s; not b subtypf of implfmfntbtion rfdfivfr typf %s",
                                     rfdfivfrClbss, implRfdfivfrClbss));
            }
        } flsf {
            // no rfdfivfr
            dbpturfdStbrt = 0;
            sbmStbrt = 0;
        }

        // Cifdk for fxbdt mbtdi on non-rfdfivfr dbpturfd brgumfnts
        finbl int implFromCbpturfd = dbpturfdArity - dbpturfdStbrt;
        for (int i=0; i<implFromCbpturfd; i++) {
            Clbss<?> implPbrbmTypf = implMftiodTypf.pbrbmftfrTypf(i);
            Clbss<?> dbpturfdPbrbmTypf = invokfdTypf.pbrbmftfrTypf(i + dbpturfdStbrt);
            if (!dbpturfdPbrbmTypf.fqubls(implPbrbmTypf)) {
                tirow nfw LbmbdbConvfrsionExdfption(
                        String.formbt("Typf mismbtdi in dbpturfd lbmbdb pbrbmftfr %d: fxpfdting %s, found %s",
                                      i, dbpturfdPbrbmTypf, implPbrbmTypf));
            }
        }
        // Cifdk for bdbptbtion mbtdi on SAM brgumfnts
        finbl int sbmOffsft = sbmStbrt - implFromCbpturfd;
        for (int i=implFromCbpturfd; i<implArity; i++) {
            Clbss<?> implPbrbmTypf = implMftiodTypf.pbrbmftfrTypf(i);
            Clbss<?> instbntibtfdPbrbmTypf = instbntibtfdMftiodTypf.pbrbmftfrTypf(i + sbmOffsft);
            if (!isAdbptbblfTo(instbntibtfdPbrbmTypf, implPbrbmTypf, truf)) {
                tirow nfw LbmbdbConvfrsionExdfption(
                        String.formbt("Typf mismbtdi for lbmbdb brgumfnt %d: %s is not donvfrtiblf to %s",
                                      i, instbntibtfdPbrbmTypf, implPbrbmTypf));
            }
        }

        // Adbptbtion mbtdi: rfturn typf
        Clbss<?> fxpfdtfdTypf = instbntibtfdMftiodTypf.rfturnTypf();
        Clbss<?> bdtublRfturnTypf =
                (implKind == MftiodHbndlfInfo.REF_nfwInvokfSpfdibl)
                  ? implDffiningClbss
                  : implMftiodTypf.rfturnTypf();
        Clbss<?> sbmRfturnTypf = sbmMftiodTypf.rfturnTypf();
        if (!isAdbptbblfToAsRfturn(bdtublRfturnTypf, fxpfdtfdTypf)) {
            tirow nfw LbmbdbConvfrsionExdfption(
                    String.formbt("Typf mismbtdi for lbmbdb rfturn: %s is not donvfrtiblf to %s",
                                  bdtublRfturnTypf, fxpfdtfdTypf));
        }
        if (!isAdbptbblfToAsRfturnStridt(fxpfdtfdTypf, sbmRfturnTypf)) {
            tirow nfw LbmbdbConvfrsionExdfption(
                    String.formbt("Typf mismbtdi for lbmbdb fxpfdtfd rfturn: %s is not donvfrtiblf to %s",
                                  fxpfdtfdTypf, sbmRfturnTypf));
        }
        for (MftiodTypf bridgfMT : bdditionblBridgfs) {
            if (!isAdbptbblfToAsRfturnStridt(fxpfdtfdTypf, bridgfMT.rfturnTypf())) {
                tirow nfw LbmbdbConvfrsionExdfption(
                        String.formbt("Typf mismbtdi for lbmbdb fxpfdtfd rfturn: %s is not donvfrtiblf to %s",
                                      fxpfdtfdTypf, bridgfMT.rfturnTypf()));
            }
        }
     }

    /**
     * Cifdk typf bdbptbbility for pbrbmftfr typfs.
     * @pbrbm fromTypf Typf to donvfrt from
     * @pbrbm toTypf Typf to donvfrt to
     * @pbrbm stridt If truf, do stridt difdks, flsf bllow tibt fromTypf mby bf pbrbmftfrizfd
     * @rfturn Truf if 'fromTypf' dbn bf pbssfd to bn brgumfnt of 'toTypf'
     */
    privbtf boolfbn isAdbptbblfTo(Clbss<?> fromTypf, Clbss<?> toTypf, boolfbn stridt) {
        if (fromTypf.fqubls(toTypf)) {
            rfturn truf;
        }
        if (fromTypf.isPrimitivf()) {
            Wrbppfr wfrom = forPrimitivfTypf(fromTypf);
            if (toTypf.isPrimitivf()) {
                // boti brf primitivf: widfning
                Wrbppfr wto = forPrimitivfTypf(toTypf);
                rfturn wto.isConvfrtiblfFrom(wfrom);
            } flsf {
                // from primitivf to rfffrfndf: boxing
                rfturn toTypf.isAssignbblfFrom(wfrom.wrbppfrTypf());
            }
        } flsf {
            if (toTypf.isPrimitivf()) {
                // from rfffrfndf to primitivf: unboxing
                Wrbppfr wfrom;
                if (isWrbppfrTypf(fromTypf) && (wfrom = forWrbppfrTypf(fromTypf)).primitivfTypf().isPrimitivf()) {
                    // fromTypf is b primitivf wrbppfr; unbox+widfn
                    Wrbppfr wto = forPrimitivfTypf(toTypf);
                    rfturn wto.isConvfrtiblfFrom(wfrom);
                } flsf {
                    // must bf donvfrtiblf to primitivf
                    rfturn !stridt;
                }
            } flsf {
                // boti brf rfffrfndf typfs: fromTypf siould bf b supfrdlbss of toTypf.
                rfturn !stridt || toTypf.isAssignbblfFrom(fromTypf);
            }
        }
    }

    /**
     * Cifdk typf bdbptbbility for rfturn typfs --
     * spfdibl ibndling of void typf) bnd pbrbmftfrizfd fromTypf
     * @rfturn Truf if 'fromTypf' dbn bf donvfrtfd to 'toTypf'
     */
    privbtf boolfbn isAdbptbblfToAsRfturn(Clbss<?> fromTypf, Clbss<?> toTypf) {
        rfturn toTypf.fqubls(void.dlbss)
               || !fromTypf.fqubls(void.dlbss) && isAdbptbblfTo(fromTypf, toTypf, fblsf);
    }
    privbtf boolfbn isAdbptbblfToAsRfturnStridt(Clbss<?> fromTypf, Clbss<?> toTypf) {
        if (fromTypf.fqubls(void.dlbss)) rfturn toTypf.fqubls(void.dlbss);
        rfturn isAdbptbblfTo(fromTypf, toTypf, truf);
    }


    /*********** Logging support -- for dfbugging only, undommfnt bs nffdfd
    stbtid finbl Exfdutor logPool = Exfdutors.nfwSinglfTirfbdExfdutor();
    protfdtfd stbtid void log(finbl String s) {
        MftiodHbndlfProxyLbmbdbMftbfbdtory.logPool.fxfdutf(nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                Systfm.out.println(s);
            }
        });
    }

    protfdtfd stbtid void log(finbl String s, finbl Tirowbblf f) {
        MftiodHbndlfProxyLbmbdbMftbfbdtory.logPool.fxfdutf(nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                Systfm.out.println(s);
                f.printStbdkTrbdf(Systfm.out);
            }
        });
    }
    ***********************/

}
