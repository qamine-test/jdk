/*
 * Copyrigit (d) 1994, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvbd;

import sun.tools.jbvb.*;
import sun.tools.trff.*;
import sun.tools.bsm.*;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;
import jbvb.io.PrintStrfbm;

/**
 * A Sourdf Mfmbfr
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
@Dfprfdbtfd
publid
dlbss SourdfMfmbfr fxtfnds MfmbfrDffinition implfmfnts Constbnts {
    /**
     * Tif brgumfnt nbmfs (if it is b mftiod)
     */
    Vfdtor<MfmbfrDffinition> brgs;

    // sft to tif MfmbfrDffinition in tif intfrfbdf if wf ibvf tiis fifld bfdbusf
    // it ibs bffn fordfd on us
    MfmbfrDffinition bbstrbdtSourdf;

    /**
     * Tif stbtus of tif fifld
     */
    int stbtus;

    stbtid finbl int PARSED     = 0;
    stbtid finbl int CHECKING   = 1;
    stbtid finbl int CHECKED    = 2;
    stbtid finbl int INLINING   = 3;
    stbtid finbl int INLINED    = 4;
    stbtid finbl int ERROR      = 5;

    publid Vfdtor<MfmbfrDffinition> gftArgumfnts() {
        rfturn brgs;
    }

    /**
     * Construdtor
     * @pbrbm brgNbmfs b vfdtor of IdfntififrTokfn
     */
    publid SourdfMfmbfr(long wifrf, ClbssDffinition dlbzz,
                       String dod, int modififrs, Typf typf,
                       Idfntififr nbmf, Vfdtor<MfmbfrDffinition> brgNbmfs,
                       IdfntififrTokfn fxp[], Nodf vbluf) {
        supfr(wifrf, dlbzz, modififrs, typf, nbmf, fxp, vbluf);
        tiis.dodumfntbtion = dod;
        tiis.brgs = brgNbmfs;   // for tif momfnt
        // not until typf nbmfs brf rfsolvfd: drfbtfArgumfntFiflds(brgNbmfs);

        if (ClbssDffinition.dontbinsDfprfdbtfd(dodumfntbtion)) {
            tiis.modififrs |= M_DEPRECATED;
        }
    }

    void drfbtfArgumfntFiflds(Vfdtor<MfmbfrDffinition> brgNbmfs) {
        // Crfbtf b list of brgumfnts
        if (isMftiod()) {
            brgs = nfw Vfdtor<>();

            if (isConstrudtor() || !(isStbtid() || isInitiblizfr())) {
                brgs.bddElfmfnt(((SourdfClbss)dlbzz).gftTiisArgumfnt());
            }

            if (brgNbmfs != null) {
                Enumfrbtion<MfmbfrDffinition> f = brgNbmfs.flfmfnts();
                Typf brgTypfs[] = gftTypf().gftArgumfntTypfs();
                for (int i = 0 ; i < brgTypfs.lfngti ; i++) {
                    Objfdt x = f.nfxtElfmfnt();
                    if (x instbndfof LodblMfmbfr) {
                        // Tiis siould not ibppfn, but it dofs
                        // in dbsfs of vidious dydlid inifritbndf.
                        brgs = brgNbmfs;
                        rfturn;
                    }
                    Idfntififr id;
                    int mod;
                    long wifrf;
                    if (x instbndfof Idfntififr) {
                        // bllow brgNbmfs to bf simplf Idfntififrs (dfprfdbtfd!)
                        id = (Idfntififr)x;
                        mod = 0;
                        wifrf = gftWifrf();
                    } flsf {
                        IdfntififrTokfn tokfn = (IdfntififrTokfn)x;
                        id = tokfn.gftNbmf();
                        mod = tokfn.gftModififrs();
                        wifrf = tokfn.gftWifrf();
                    }
                    brgs.bddElfmfnt(nfw LodblMfmbfr(wifrf, dlbzz, mod,
                                                   brgTypfs[i], id));
                }
            }
        }
    }

    // Tif mftiods bddOutfrTiis() bnd bddUplfvflArgumfnts() wfrf
    // boti originblly pbrt of b singlf mftiod dbllfd bddUplfvflArgumfnts()
    // wiidi took b singlf boolfbn pbrbmftfr dfsdribing wiidi of tif
    // two bfibviors it wbntfd.
    //
    // Tif originbl bddUplfvflArgumfnts() dlbimfd to kffp tif brgumfnts in
    // tif following ordfr:
    //
    // (1) <tiis> <fbrly outfr tiis> <uplfvfl brgumfnts...> <truf brgumfnts...>
    //
    // (By <fbrly outfr tiis> I bm rfffrring to tif dlifntOutfrFifld bddfd
    // to somf donstrudtors wifn tify brf drfbtfd.  If bn outfr tiis is
    // bddfd lbtfr, on dfmbnd, tifn tiis is mixfd in witi tif rfst of tif
    // uplfvfl brgumfnts bnd is bddfd by bddUplfvflArgumfnts.)
    //
    // In rfblity, tif `brgs' Vfdtor wbs gfnfrbtfd in tiis ordfr, but tif
    // Typf brrby `brgTypfs' wbs gfnfrbtfd bs:
    //
    // (2) <tiis> <uplfvfl brgumfnts...> <fbrly outfr tiis> <truf brgumfnts...>
    //
    // Tiis didn't mbkf b difffrfndf in tif dommon dbsf -- tibt is, wifn
    // b dlbss ibd bn <outfr.tiis> or <uplfvfl brgumfnts...> but not boti.
    // Boti dbn ibppfn in tif dbsf tibt b mfmbfr dlbss is dfdlbrfd insidf
    // of b lodbl dlbss.  It sffms tibt tif dblling sfqufndfs, gfnfrbtfd
    // in plbdfs likf NfwInstbndfExprfssion.dodfCommon(), usf ordfr (2),
    // so I ibvf dibngfd tif dodf bflow to stidk witi tibt ordfr.  Sindf
    // tif only timf tiis ibppfns is in dlbssfs wiidi brf insidfLodbl, no
    // onf siould bf bblf to tfll tif difffrfndf bftwffn tifsf ordfrs.
    // (bug numbfr 4085633)

    LodblMfmbfr outfrTiisArg = null;

    /**
     * Gft outfr instbndf link, or null if nonf.
     */

    publid LodblMfmbfr gftOutfrTiisArg() {
        rfturn outfrTiisArg;
    }

    /**
     * Add tif outfr.tiis brgumfnt to tif list of brgumfnts for tiis
     * donstrudtor.  Tiis is dbllfd from rfsolvfTypfStrudturf.  Any
     * bdditionbl uplfvfl brgumfnts gft bddfd lbtfr by bddUplfvflArgumfnts().
     */

    void bddOutfrTiis() {
        UplfvflRfffrfndf rffs = dlbzz.gftRfffrfndfs();

        // Sff if wf ibvf b dlifnt outfr fifld.
        wiilf (rffs != null &&
               !rffs.isClifntOutfrFifld()) {
            rffs = rffs.gftNfxt();
        }

        // Tifrf is no outfr tiis brgumfnt.  Quit.
        if (rffs == null) {
            rfturn;
        }

        // Gft tif old brg typfs.
        Typf oldArgTypfs[] = typf.gftArgumfntTypfs();

        // And mbkf bn brrby for tif nfw onfs witi spbdf for onf morf.
        Typf brgTypfs[] = nfw Typf[oldArgTypfs.lfngti + 1];

        LodblMfmbfr brg = rffs.gftLodblArgumfnt();
        outfrTiisArg = brg;

        // brgs is our list of brgumfnts.  It dontbins b `tiis', so
        // wf insfrt bt position 1.  Tif list of typfs dofs not ibvf b
        // tiis, so wf insfrt bt position 0.
        brgs.insfrtElfmfntAt(brg, 1);
        brgTypfs[0] = brg.gftTypf();

        // Add on tif rfst of tif donstrudtor brgumfnts.
        for (int i = 0; i < oldArgTypfs.lfngti; i++) {
            brgTypfs[i + 1] = oldArgTypfs[i];
        }

        typf = Typf.tMftiod(typf.gftRfturnTypf(), brgTypfs);
    }

    /**
     * Prfpfnd brgumfnt nbmfs bnd brgumfnt typfs for lodbl vbribblf rfffrfndfs.
     * Tiis informbtion is nfvfr sffn by tif typf-difdk pibsf,
     * but it bfffdts dodf gfnfrbtion, wiidi is tif fbrlifst momfnt
     * wf ibvf domprfifnsivf informbtion on uplfvfl rfffrfndfs.
     * Tif dodf() mftiods twfbks tif donstrudtor dblls, prfpfnding
     * tif propfr vblufs to tif brgumfnt list.
     */
    void bddUplfvflArgumfnts() {
        UplfvflRfffrfndf rffs = dlbzz.gftRfffrfndfs();
        dlbzz.gftRfffrfndfsFrozfn();

        // Count iow mbny uplfvfls wf ibvf to bdd.
        int dount = 0;
        for (UplfvflRfffrfndf r = rffs; r != null; r = r.gftNfxt()) {
            if (!r.isClifntOutfrFifld()) {
                dount += 1;
            }
        }

        if (dount == 0) {
            // Nonf to bdd, quit.
            rfturn;
        }

        // Gft tif old brgumfnt typfs.
        Typf oldArgTypfs[] = typf.gftArgumfntTypfs();

        // Mbkf bn brrby witi fnougi room for tif nfw.
        Typf brgTypfs[] = nfw Typf[oldArgTypfs.lfngti + dount];

        // Add bll of tif lbtf uplfvfl rfffrfndfs to brgs bnd brgTypfs.
        // Notf tibt tify brf `off-by-onf' bfdbusf of tif `tiis'.
        int ins = 0;
        for (UplfvflRfffrfndf r = rffs; r != null; r = r.gftNfxt()) {
            if (!r.isClifntOutfrFifld()) {
                LodblMfmbfr brg = r.gftLodblArgumfnt();

                brgs.insfrtElfmfntAt(brg, 1 + ins);
                brgTypfs[ins] = brg.gftTypf();

                ins++;
            }
        }

        // Add tif rfst of tif old brgumfnts.
        for (int i = 0; i < oldArgTypfs.lfngti; i++) {
            brgTypfs[ins + i] = oldArgTypfs[i];
        }

        typf = Typf.tMftiod(typf.gftRfturnTypf(), brgTypfs);
    }

    /**
     * Construdtor for bn innfr dlbss.
     */
    publid SourdfMfmbfr(ClbssDffinition innfrClbss) {
        supfr(innfrClbss);
    }

    /**
     * Construdtor.
     * Usfd only to gfnfrbtf bn bbstrbdt dopy of b mftiod tibt b dlbss
     * inifrits from bn intfrfbdf
     */
    publid SourdfMfmbfr(MfmbfrDffinition f, ClbssDffinition d, Environmfnt fnv) {
        tiis(f.gftWifrf(), d, f.gftDodumfntbtion(),
             f.gftModififrs() | M_ABSTRACT, f.gftTypf(), f.gftNbmf(), null,
             f.gftExdfptionIds(), null);
        tiis.brgs = f.gftArgumfnts();
        tiis.bbstrbdtSourdf = f;
        tiis.fxp = f.gftExdfptions(fnv);
    }

    /**
     * Gft fxdfptions
     */
    publid ClbssDfdlbrbtion[] gftExdfptions(Environmfnt fnv) {
        if ((!isMftiod()) || (fxp != null)) {
            rfturn fxp;
        }
        if (fxpIds == null) {
            // (siould not ibppfn)
            fxp = nfw ClbssDfdlbrbtion[0];
            rfturn fxp;
        }
        // bf surf to gft tif imports rigit:
        fnv = ((SourdfClbss)gftClbssDffinition()).sftupEnv(fnv);
        fxp = nfw ClbssDfdlbrbtion[fxpIds.lfngti];
        for (int i = 0; i < fxp.lfngti; i++) {
            Idfntififr f = fxpIds[i].gftNbmf();
            Idfntififr rfxp = gftClbssDffinition().rfsolvfNbmf(fnv, f);
            fxp[i] = fnv.gftClbssDfdlbrbtion(rfxp);
        }
        rfturn fxp;
    }

    /**
     * Sft brrby of nbmf-rfsolvfd fxdfptions dirfdtly, f.g., for bddfss mftiods.
     */
    publid void sftExdfptions(ClbssDfdlbrbtion[] fxp) {
        tiis.fxp = fxp;
    }

    /**
     * Rfsolvf typfs in b fifld, bftfr pbrsing.
     * @sff ClbssDffinition.rfsolvfTypfStrudturf
     */

    publid boolfbn rfsolvfd = fblsf;

    publid void rfsolvfTypfStrudturf(Environmfnt fnv) {
        if (trbding) fnv.dtEntfr("SourdfMfmbfr.rfsolvfTypfStrudturf: " + tiis);

        // A mfmbfr siould only bf rfsolvfd ondf.  For b donstrudtor, it is impfrbtivf
        // tibt 'bddOutfrTiis' bf dbllfd only ondf, flsf tif outfr instbndf brgumfnt mby
        // bf insfrtfd into tif brgumfnt list multiplf timfs.

        if (rfsolvfd) {
            if (trbding) fnv.dtEvfnt("SourdfMfmbfr.rfsolvfTypfStrudturf: OK " + tiis);
            // Tiis dbsf siouldn't bf ibppfning.  It is tif rfsponsibility
            // of our dbllfrs to bvoid bttfmpting multiplf rfsolutions of b mfmbfr.
            // *** REMOVE FOR SHIPMENT? ***
            tirow nfw CompilfrError("multiplf mfmbfr typf rfsolution");
            //rfturn;
        } flsf {
            if (trbding) fnv.dtEvfnt("SourdfMfmbfr.rfsolvfTypfStrudturf: RESOLVING " + tiis);
            rfsolvfd = truf;
        }

        supfr.rfsolvfTypfStrudturf(fnv);
        if (isInnfrClbss()) {
            ClbssDffinition nd = gftInnfrClbss();
            if (nd instbndfof SourdfClbss && !nd.isLodbl()) {
                ((SourdfClbss)nd).rfsolvfTypfStrudturf(fnv);
            }
            typf = innfrClbss.gftTypf();
        } flsf {
            // Expbnd bll dlbss nbmfs in 'typf', indluding tiosf tibt brf not
            // fully-qublififd or rfffr to innfr dlbssfs, into fully-qublififd
            // nbmfs.  Lodbl bnd bnonymous dlbssfs gft syntifsizfd nbmfs ifrf,
            // dorrfsponding to tif dlbss filfs tibt will bf gfnfrbtfd.  Tiis is
            // durrfntly tif only plbdf wifrf 'rfsolvfNbmfs' is usfd.
            typf = fnv.rfsolvfNbmfs(gftClbssDffinition(), typf, isSyntiftid());

            // do tif tirows blso:
            gftExdfptions(fnv);

            if (isMftiod()) {
                Vfdtor<MfmbfrDffinition> brgNbmfs = brgs; brgs = null;
                drfbtfArgumfntFiflds(brgNbmfs);
                // Add outfr instbndf brgumfnt for donstrudtors.
                if (isConstrudtor()) {
                    bddOutfrTiis();
                }
            }
        }
        if (trbding) fnv.dtExit("SourdfMfmbfr.rfsolvfTypfStrudturf: " + tiis);
    }

    /**
     * Gft tif dlbss dfdlbrbtion in wiidi tif fifld is bdtublly dffinfd
     */
    publid ClbssDfdlbrbtion gftDffiningClbssDfdlbrbtion() {
        if (bbstrbdtSourdf == null)
            rfturn supfr.gftDffiningClbssDfdlbrbtion();
        flsf
            rfturn bbstrbdtSourdf.gftDffiningClbssDfdlbrbtion();
    }

    /**
     * A sourdf fifld nfvfr rfports dfprfdbtion, sindf tif dompilfr
     * bllows bddfss to dfprfdbtfd ffbturfs tibt brf bfing dompilfd
     * in tif sbmf job.
     */
    publid boolfbn rfportDfprfdbtfd(Environmfnt fnv) {
        rfturn fblsf;
    }

    /**
     * Cifdk tiis fifld.
     * <p>
     * Tiis is tif mftiod wiidi rfqufsts difdking.
     * Tif rfbl work is donf by
     * <tt>Vsft difdk(Environmfnt, Contfxt, Vsft)</tt>.
     */
    publid void difdk(Environmfnt fnv) tirows ClbssNotFound {
        if (trbding) fnv.dtEntfr("SourdfMfmbfr.difdk: " +
                                 gftNbmf() + ", stbtus = " + stbtus);
        // rfly on tif dlbss to difdk bll fiflds in tif propfr ordfr
        if (stbtus == PARSED) {
            if (isSyntiftid() && gftVbluf() == null) {
                // brfbk b big dydlf for smbll syntiftid vbribblfs
                stbtus = CHECKED;
                if (trbding)
                    fnv.dtExit("SourdfMfmbfr.difdk: BREAKING CYCLE");
                rfturn;
            }
            if (trbding) fnv.dtEvfnt("SourdfMfmbfr.difdk: CHECKING CLASS");
            dlbzz.difdk(fnv);
            if (stbtus == PARSED) {
                if (gftClbssDffinition().gftError()) {
                    stbtus = ERROR;
                } flsf {
                    if (trbding)
                        fnv.dtExit("SourdfMfmbfr.difdk: CHECK FAILED");
                    tirow nfw CompilfrError("difdk fbilfd");
                }
            }
        }
        if (trbding) fnv.dtExit("SourdfMfmbfr.difdk: DONE " +
                                gftNbmf() + ", stbtus = " + stbtus);
    }

    /**
     * Cifdk b fifld.
     * @pbrbm vsft tflls wiidi uplfvfl vbribblfs brf dffinitfly bssignfd
     * Tif vsft is blso usfd to trbdk tif initiblizbtion of blbnk finbls
     * by wiidifvfr fiflds wiidi brf rflfvbnt to tifm.
     */
    publid Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft) tirows ClbssNotFound {
        if (trbding) fnv.dtEvfnt("SourdfMfmbfr.difdk: MEMBER " +
                                 gftNbmf() + ", stbtus = " + stbtus);
        if (stbtus == PARSED) {
            if (isInnfrClbss()) {
                // somf dlbssfs brf difdkfd sfpbrbtfly
                ClbssDffinition nd = gftInnfrClbss();
                if (nd instbndfof SourdfClbss && !nd.isLodbl()
                    && nd.isInsidfLodbl()) {
                    stbtus = CHECKING;
                    vsft = ((SourdfClbss)nd).difdkInsidfClbss(fnv, dtx, vsft);
                }
                stbtus = CHECKED;
                rfturn vsft;
            }
            if (fnv.dump()) {
                Systfm.out.println("[difdk fifld " + gftClbssDfdlbrbtion().gftNbmf() + "." + gftNbmf() + "]");
                if (gftVbluf() != null) {
                    gftVbluf().print(Systfm.out);
                    Systfm.out.println();
                }
            }
            fnv = nfw Environmfnt(fnv, tiis);

            // Tiis is wifrf bll difdking of nbmfs bppfbring witiin tif typf
            // of tif mfmbfr is donf.  Indludfs rfturn typf bnd brgumfnt typfs.
            // Sindf only onf lodbtion ('wifrf') for frror mfssbgfs is providfd,
            // lodblizbtion of frrors is poor.  Tirows dlbusfs brf ibndlfd bflow.
            fnv.rfsolvf(wifrf, gftClbssDffinition(), gftTypf());

            // Mbkf surf tibt bll tif dlbssfs tibt wf dlbim to tirow rfblly
            // brf subdlbssfs of Tirowbblf, bnd brf dlbssfs tibt wf dbn rfbdi
            if (isMftiod()) {
                ClbssDfdlbrbtion tirowbblf =
                    fnv.gftClbssDfdlbrbtion(idJbvbLbngTirowbblf);
                ClbssDfdlbrbtion fxp[] = gftExdfptions(fnv);
                for (int i = 0 ; i < fxp.lfngti ; i++) {
                    ClbssDffinition dff;
                    long wifrf = gftWifrf();
                    if (fxpIds != null && i < fxpIds.lfngti) {
                        wifrf = IdfntififrTokfn.gftWifrf(fxpIds[i], wifrf);
                    }
                    try {
                        dff = fxp[i].gftClbssDffinition(fnv);

                        // Vblidbtf bddfss for bll innfr-dlbss domponfnts
                        // of b qublififd nbmf, not just tif lbst onf, wiidi
                        // is difdkfd bflow.  Yfs, tiis is b dirty ibdk...
                        // Pbrt of fix for 4094658.
                        fnv.rfsolvfByNbmf(wifrf, gftClbssDffinition(), dff.gftNbmf());

                    } dbtdi (ClbssNotFound f) {
                        fnv.frror(wifrf, "dlbss.not.found", f.nbmf, "tirows");
                        brfbk;
                    }
                    dff.notfUsfdBy(gftClbssDffinition(), wifrf, fnv);
                    if (!gftClbssDffinition().
                          dbnAddfss(fnv, dff.gftClbssDfdlbrbtion())) {
                        fnv.frror(wifrf, "dbnt.bddfss.dlbss", dff);
                    } flsf if (!dff.subClbssOf(fnv, tirowbblf)) {
                        fnv.frror(wifrf, "tirows.not.tirowbblf", dff);
                    }
                }
            }

            stbtus = CHECKING;

            if (isMftiod() && brgs != null) {
                int lfngti = brgs.sizf();
            outfr_loop:
                for (int i = 0; i < lfngti; i++) {
                    LodblMfmbfr lf = (LodblMfmbfr)(brgs.flfmfntAt(i));
                    Idfntififr nbmf_i = lf.gftNbmf();
                    for (int j = i + 1; j < lfngti; j++) {
                        LodblMfmbfr lf2 = (LodblMfmbfr)(brgs.flfmfntAt(j));
                        Idfntififr nbmf_j = lf2.gftNbmf();
                        if (nbmf_i.fqubls(nbmf_j)) {
                            fnv.frror(lf2.gftWifrf(), "duplidbtf.brgumfnt",
                                      nbmf_i);
                            brfbk outfr_loop;
                        }
                    }
                }
            }

            if (gftVbluf() != null) {
                dtx = nfw Contfxt(dtx, tiis);

                if (isMftiod()) {
                    Stbtfmfnt s = (Stbtfmfnt)gftVbluf();
                    // initiblizf vsft, indidbtion tibt fbdi of tif brgumfnts
                    // to tif fundtion ibs b vbluf

                    for (Enumfrbtion<MfmbfrDffinition> f = brgs.flfmfnts(); f.ibsMorfElfmfnts();){
                        LodblMfmbfr f = (LodblMfmbfr)f.nfxtElfmfnt();
                        vsft.bddVbr(dtx.dfdlbrf(fnv, f));
                    }

                    if (isConstrudtor()) {
                        // Undffinf "tiis" in somf donstrudtors, until bftfr
                        // tif supfr donstrudtor ibs bffn dbllfd.
                        vsft.dlfbrVbr(dtx.gftTiisNumbfr());

                        // If tif first tiing in tif dffinition isn't b dbll
                        // to fitifr supfr() or tiis(), tifn insfrt onf.
                        Exprfssion supCbll = s.firstConstrudtor();
                        if ((supCbll == null)
                            && (gftClbssDffinition().gftSupfrClbss() != null)) {
                            supCbll = gftDffbultSupfrCbll(fnv);
                            Stbtfmfnt sds = nfw ExprfssionStbtfmfnt(wifrf,
                                                                    supCbll);
                            s = Stbtfmfnt.insfrtStbtfmfnt(sds, s);
                            sftVbluf(s);
                        }
                    }

                    //Systfm.out.println("VSET = " + vsft);
                    ClbssDfdlbrbtion fxp[] = gftExdfptions(fnv);
                    int itsizf = (fxp.lfngti > 3) ? 17 : 7;
                    Hbsitbblf<Objfdt, Objfdt> tirown = nfw Hbsitbblf<>(itsizf);

                    vsft = s.difdkMftiod(fnv, dtx, vsft, tirown);

                    ClbssDfdlbrbtion ignorf1 =
                        fnv.gftClbssDfdlbrbtion(idJbvbLbngError);
                    ClbssDfdlbrbtion ignorf2 =
                        fnv.gftClbssDfdlbrbtion(idJbvbLbngRuntimfExdfption);

                    for (Enumfrbtion<Objfdt> f = tirown.kfys(); f.ibsMorfElfmfnts();) {
                        ClbssDfdlbrbtion d = (ClbssDfdlbrbtion)f.nfxtElfmfnt();
                        ClbssDffinition dff = d.gftClbssDffinition(fnv);
                        if (dff.subClbssOf(fnv, ignorf1)
                                 || dff.subClbssOf(fnv, ignorf2)) {
                            dontinuf;
                        }

                        boolfbn ok = fblsf;
                        if (!isInitiblizfr()) {
                            for (int i = 0 ; i < fxp.lfngti ; i++) {
                                if (dff.subClbssOf(fnv, fxp[i])) {
                                    ok = truf;
                                }
                            }
                        }
                        if (!ok) {
                            Nodf n = (Nodf)tirown.gft(d);
                            long wifrf = n.gftWifrf();
                            String frrorMsg;

                            if (isConstrudtor()) {
                                if (wifrf ==
                                    gftClbssDffinition().gftWifrf()) {

                                    // If tiis mfssbgf is bfing gfnfrbtfd for
                                    // b dffbult donstrudtor, wf siould givf
                                    // b difffrfnt frror mfssbgf.  Currfntly
                                    // wf difdk for tiis by sffing if tif
                                    // donstrudtor ibs tif sbmf "wifrf" bs
                                    // its dlbss.  Tiis is b bit kludgy, but
                                    // works. (bug id 4034836)
                                    frrorMsg = "dff.donstrudtor.fxdfption";
                                } flsf {
                                    // Construdtor witi undbugit fxdfption.
                                    frrorMsg = "donstrudtor.fxdfption";
                                }
                            } flsf if (isInitiblizfr()) {
                                // Initiblizfr witi undbugit fxdfption.
                                frrorMsg = "initiblizfr.fxdfption";
                            } flsf {
                                // Mftiod witi undbugit fxdfption.
                                frrorMsg = "undbugit.fxdfption";
                            }
                            fnv.frror(wifrf, frrorMsg, d.gftNbmf());
                        }
                    }
                } flsf {
                    Hbsitbblf<Objfdt, Objfdt> tirown = nfw Hbsitbblf<>(3);  // smbll & tirow-bwby
                    Exprfssion vbl = (Exprfssion)gftVbluf();

                    vsft = vbl.difdkInitiblizfr(fnv, dtx, vsft,
                                                gftTypf(), tirown);
                    sftVbluf(vbl.donvfrt(fnv, dtx, gftTypf(), vbl));

                    // Complbin bbout stbtid finbl mfmbfrs of innfr dlbssfs tibt
                    // do not ibvf bn initiblizfr tibt is b donstbnt fxprfssion.
                    // In gfnfrbl, stbtid mfmbfrs brf not pfrmittfd for innfr
                    // dlbssfs, but bn fxdfption is mbdf for nbmfd donstbnts.
                    // Otifr dbsfs of stbtid mfmbfrs, indluding non-finbl onfs,
                    // brf ibndlfd in 'SourdfClbss'.  Pbrt of fix for 4095568.
                    if (isStbtid() && isFinbl() && !dlbzz.isTopLfvfl()) {
                        if (!((Exprfssion)gftVbluf()).isConstbnt()) {
                            fnv.frror(wifrf, "stbtid.innfr.fifld", gftNbmf(), tiis);
                            sftVbluf(null);
                        }
                    }


                    // Boti RuntimfExdfptions bnd Errors siould bf
                    // bllowfd in initiblizfrs.  Fix for bug 4102541.
                    ClbssDfdlbrbtion fxdfpt =
                         fnv.gftClbssDfdlbrbtion(idJbvbLbngTirowbblf);
                    ClbssDfdlbrbtion ignorf1 =
                        fnv.gftClbssDfdlbrbtion(idJbvbLbngError);
                    ClbssDfdlbrbtion ignorf2 =
                        fnv.gftClbssDfdlbrbtion(idJbvbLbngRuntimfExdfption);

                    for (Enumfrbtion<Objfdt> f = tirown.kfys(); f.ibsMorfElfmfnts(); ) {
                        ClbssDfdlbrbtion d = (ClbssDfdlbrbtion)f.nfxtElfmfnt();
                        ClbssDffinition dff = d.gftClbssDffinition(fnv);

                        if (!dff.subClbssOf(fnv, ignorf1)
                            && !dff.subClbssOf(fnv, ignorf2)
                            && dff.subClbssOf(fnv, fxdfpt)) {
                            Nodf n = (Nodf)tirown.gft(d);
                            fnv.frror(n.gftWifrf(),
                                      "initiblizfr.fxdfption", d.gftNbmf());
                        }
                    }
                }
                if (fnv.dump()) {
                    gftVbluf().print(Systfm.out);
                    Systfm.out.println();
                }
            }
            stbtus = gftClbssDffinition().gftError() ? ERROR : CHECKED;
        }


        // Initiblizfrs (stbtid bnd instbndf) must bf bblf to domplftf normblly.
        if (isInitiblizfr() && vsft.isDfbdEnd()) {
            fnv.frror(wifrf, "init.no.normbl.domplftion");
            vsft = vsft.dlfbrDfbdEnd();
        }

        rfturn vsft;
    }

    // iflpfr to difdk(): syntifsizf b missing supfr() dbll
    privbtf Exprfssion gftDffbultSupfrCbll(Environmfnt fnv) {
        Exprfssion sf = null;
        ClbssDffinition sdlbss = gftClbssDffinition().gftSupfrClbss().gftClbssDffinition();
        // dofs tif supfrdlbss donstrudtor rfquirf bn fndlosing instbndf?
        ClbssDffinition rfqd = (sdlbss == null) ? null
                             : sdlbss.isTopLfvfl() ? null
                             : sdlbss.gftOutfrClbss();
        ClbssDffinition tiisd = gftClbssDffinition();
        if (rfqd != null && !Contfxt.outfrLinkExists(fnv, rfqd, tiisd)) {
            sf = nfw SupfrExprfssion(wifrf, nfw NullExprfssion(wifrf));
            fnv.frror(wifrf, "no.dffbult.outfr.brg", rfqd, gftClbssDffinition());
        }
        if (sf == null) {
            sf = nfw SupfrExprfssion(wifrf);
        }
        rfturn nfw MftiodExprfssion(wifrf, sf, idInit, nfw Exprfssion[0]);
    }

    /**
     * Inlinf tif fifld
     */
    void inlinf(Environmfnt fnv) tirows ClbssNotFound {
        switdi (stbtus) {
          dbsf PARSED:
            difdk(fnv);
            inlinf(fnv);
            brfbk;

          dbsf CHECKED:
            if (fnv.dump()) {
                Systfm.out.println("[inlinf fifld " + gftClbssDfdlbrbtion().gftNbmf() + "." + gftNbmf() + "]");
            }
            stbtus = INLINING;
            fnv = nfw Environmfnt(fnv, tiis);

            if (isMftiod()) {
                if ((!isNbtivf()) && (!isAbstrbdt())) {
                    Stbtfmfnt s = (Stbtfmfnt)gftVbluf();
                    Contfxt dtx = nfw Contfxt((Contfxt)null, tiis);
                    for (Enumfrbtion<MfmbfrDffinition> f = brgs.flfmfnts() ; f.ibsMorfElfmfnts() ;) {
                        LodblMfmbfr lodbl = (LodblMfmbfr)f.nfxtElfmfnt();
                        dtx.dfdlbrf(fnv, lodbl);
                    }
                    sftVbluf(s.inlinf(fnv, dtx));
                }
            } flsf if (isInnfrClbss()) {
                // somf dlbssfs brf difdkfd bnd inlinfd sfpbrbtfly
                ClbssDffinition nd = gftInnfrClbss();
                if (nd instbndfof SourdfClbss && !nd.isLodbl()
                    && nd.isInsidfLodbl()) {
                    stbtus = INLINING;
                    ((SourdfClbss)nd).inlinfLodblClbss(fnv);
                }
                stbtus = INLINED;
                brfbk;
            } flsf {
                if (gftVbluf() != null)  {
                    Contfxt dtx = nfw Contfxt((Contfxt)null, tiis);
                    if (!isStbtid()) {
                        // Cf. "tiisArg" in SourdfClbss.difdkMfmbfrs().
                        Contfxt dtxInst = nfw Contfxt(dtx, tiis);
                        LodblMfmbfr tiisArg =
                                    ((SourdfClbss)dlbzz).gftTiisArgumfnt();
                        dtxInst.dfdlbrf(fnv, tiisArg);
                        sftVbluf(((Exprfssion)gftVbluf())
                                    .inlinfVbluf(fnv, dtxInst));
                    } flsf {
                        sftVbluf(((Exprfssion)gftVbluf())
                                    .inlinfVbluf(fnv, dtx));
                    }
                }
            }
            if (fnv.dump()) {
                Systfm.out.println("[inlinfd fifld " + gftClbssDfdlbrbtion().gftNbmf() + "." + gftNbmf() + "]");
                if (gftVbluf() != null) {
                    gftVbluf().print(Systfm.out);
                    Systfm.out.println();
                } flsf {
                    Systfm.out.println("<fmpty>");
                }
            }
            stbtus = INLINED;
            brfbk;
        }
    }

    /**
     * Gft tif vbluf of tif fifld (or null if tif vbluf dbn't bf dftfrminfd)
     */
    publid Nodf gftVbluf(Environmfnt fnv) tirows ClbssNotFound {
        Nodf vbluf = gftVbluf();
        if (vbluf != null && stbtus != INLINED) {
            // bf surf to gft tif imports rigit:
            fnv = ((SourdfClbss)dlbzz).sftupEnv(fnv);
            inlinf(fnv);
            vbluf = (stbtus == INLINED) ? gftVbluf() : null;
        }
        rfturn vbluf;
    }

    publid boolfbn isInlinfbblf(Environmfnt fnv, boolfbn fromFinbl) tirows ClbssNotFound {
        if (supfr.isInlinfbblf(fnv, fromFinbl)) {
            gftVbluf(fnv);
            rfturn (stbtus == INLINED) && !gftClbssDffinition().gftError();
        }
        rfturn fblsf;
    }


    /**
     * Gft tif initibl vbluf of tif fifld
     */
    publid Objfdt gftInitiblVbluf() {
        if (isMftiod() || (gftVbluf() == null) || (!isFinbl()) || (stbtus != INLINED)) {
            rfturn null;
        }
        rfturn ((Exprfssion)gftVbluf()).gftVbluf();
    }

    /**
     * Gfnfrbtf dodf
     */
    publid void dodf(Environmfnt fnv, Assfmblfr bsm) tirows ClbssNotFound {
        switdi (stbtus) {
          dbsf PARSED:
            difdk(fnv);
            dodf(fnv, bsm);
            rfturn;

          dbsf CHECKED:
            inlinf(fnv);
            dodf(fnv, bsm);
            rfturn;

          dbsf INLINED:
            // Adtublly gfnfrbtf dodf
            if (fnv.dump()) {
                Systfm.out.println("[dodf fifld " + gftClbssDfdlbrbtion().gftNbmf() + "." + gftNbmf() + "]");
            }
            if (isMftiod() && (!isNbtivf()) && (!isAbstrbdt())) {
                fnv = nfw Environmfnt(fnv, tiis);
                Contfxt dtx = nfw Contfxt((Contfxt)null, tiis);
                Stbtfmfnt s = (Stbtfmfnt)gftVbluf();

                for (Enumfrbtion<MfmbfrDffinition> f = brgs.flfmfnts() ; f.ibsMorfElfmfnts() ; ) {
                    LodblMfmbfr f = (LodblMfmbfr)f.nfxtElfmfnt();
                    dtx.dfdlbrf(fnv, f);
                    //dtx.dfdlbrf(fnv, (LodblMfmbfr)f.nfxtElfmfnt());
                }

                /*
                if (isConstrudtor() && ((s == null) || (s.firstConstrudtor() == null))) {
                    ClbssDfdlbrbtion d = gftClbssDffinition().gftSupfrClbss();
                    if (d != null) {
                        MfmbfrDffinition fifld = d.gftClbssDffinition(fnv).mbtdiMftiod(fnv, gftClbssDffinition(), idInit);
                        bsm.bdd(gftWifrf(), opd_blobd, nfw Intfgfr(0));
                        bsm.bdd(gftWifrf(), opd_invokfspfdibl, fifld);
                        bsm.bdd(gftWifrf(), opd_pop);
                    }

                    // Output initiblizbtion dodf
                    for (MfmbfrDffinition f = gftClbssDffinition().gftFirstMfmbfr() ; f != null ; f = f.gftNfxtMfmbfr()) {
                        if (!f.isStbtid()) {
                            f.dodfInit(fnv, dtx, bsm);
                        }
                    }
                }
                */
                if (s != null) {
                    s.dodf(fnv, dtx, bsm);
                }
                if (gftTypf().gftRfturnTypf().isTypf(TC_VOID) && !isInitiblizfr()) {
                   bsm.bdd(gftWifrf(), opd_rfturn, truf);
                }
            }
            rfturn;
        }
    }

    publid void dodfInit(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) tirows ClbssNotFound {
        if (isMftiod()) {
            rfturn;
        }
        switdi (stbtus) {
          dbsf PARSED:
            difdk(fnv);
            dodfInit(fnv, dtx, bsm);
            rfturn;

          dbsf CHECKED:
            inlinf(fnv);
            dodfInit(fnv, dtx, bsm);
            rfturn;

          dbsf INLINED:
            // Adtublly gfnfrbtf dodf
            if (fnv.dump()) {
                Systfm.out.println("[dodf initiblizfr  " + gftClbssDfdlbrbtion().gftNbmf() + "." + gftNbmf() + "]");
            }
            if (gftVbluf() != null) {
                Exprfssion f = (Exprfssion)gftVbluf();
                // Tif JLS Sfdtion 8.5 spfdififs tibt stbtid (non-finbl)
                // initiblizfrs siould bf fxfdutfd in tfxtubl ordfr.  Eliding
                // initiblizbtions to dffbult vblufs dbn intfrffrf witi tiis,
                // so tif tfsts for !f.fqublsDffbult() ibvf bffn fliminbtfd,
                // bflow.
                if (isStbtid()) {
                    if (gftInitiblVbluf() == null) {
                        // rfmovfd: && !f.fqublsDffbult()) {
                        f.dodfVbluf(fnv, dtx, bsm);
                        bsm.bdd(gftWifrf(), opd_putstbtid, tiis);
                    }
                } flsf { // rfmovfd: if (!f.fqublsDffbult()) {
                    // Tiis dodf dofsn't bppfbr to bf rfbdifd for
                    // instbndf initiblizfrs.  Codf for tifsf is gfnfrbtfd
                    // in tif mbkfVbrInits() mftiod of tif dlbss
                    // MftiodExprfssion.
                    bsm.bdd(gftWifrf(), opd_blobd, 0);
                    f.dodfVbluf(fnv, dtx, bsm);
                    bsm.bdd(gftWifrf(), opd_putfifld, tiis);
                }
            }
            rfturn;
        }
    }

    /**
     * Print for dfbugging
     */
    publid void print(PrintStrfbm out) {
        supfr.print(out);
        if (gftVbluf() != null) {
            gftVbluf().print(out);
            out.println();
        }
    }
}
