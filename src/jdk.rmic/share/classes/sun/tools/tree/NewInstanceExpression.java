/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.trff;

import sun.tools.jbvb.*;
import sun.tools.bsm.Assfmblfr;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss NfwInstbndfExprfssion fxtfnds NbryExprfssion {
    MfmbfrDffinition fifld;
    Exprfssion outfrArg;
    ClbssDffinition body;

    // Addfss mftiod for donstrudtor, if nffdfd.
    MfmbfrDffinition implMftiod = null;

    /**
     * Construdtor
     */
    publid NfwInstbndfExprfssion(long wifrf, Exprfssion rigit, Exprfssion brgs[]) {
        supfr(NEWINSTANCE, wifrf, Typf.tError, rigit, brgs);
    }
    publid NfwInstbndfExprfssion(long wifrf, Exprfssion rigit,
                                 Exprfssion brgs[],
                                 Exprfssion outfrArg, ClbssDffinition body) {
        tiis(wifrf, rigit, brgs);
        tiis.outfrArg = outfrArg;
        tiis.body = body;
    }

    /**
     * From tif "nfw" in bn fxprfssion of tif form outfr.nfw InnfrCls(...),
     * rfturn tif "outfr" fxprfssion, or null if tifrf is nonf.
     */
    publid Exprfssion gftOutfrArg() {
        rfturn outfrArg;
    }

    int prfdfdfndf() {
        rfturn 100;
    }

    publid Exprfssion ordfr() {
        // bdt likf b mftiod or fifld rfffrfndf fxprfssion:
        if (outfrArg != null && opPrfdfdfndf[FIELD] > outfrArg.prfdfdfndf()) {
            UnbryExprfssion f = (UnbryExprfssion)outfrArg;
            outfrArg = f.rigit;
            f.rigit = ordfr();
            rfturn f;
        }
        rfturn tiis;
    }

    /**
     * Cifdk fxprfssion typf
     */
    publid Vsft difdkVbluf(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        // Wibt typf?
        ClbssDffinition dff = null;

        Exprfssion blrfbdyCifdkfd = null;

        try {
            if (outfrArg != null) {
                vsft = outfrArg.difdkVbluf(fnv, dtx, vsft, fxp);

                // Rfmfmbfr tif fxprfssion tibt wf blrfbdy difdkfd
                // so tibt wf don't bttfmpt to difdk it bgbin wifn
                // it bppfbrs bs bn brgumfnt to tif donstrudtor.
                // Fix for 4030426.
                blrfbdyCifdkfd = outfrArg;

                // Cifdk outfrArg bnd tif typf nbmf togftifr.
                Idfntififr typfNbmf = FifldExprfssion.toIdfntififr(rigit);

                // Addording to tif innfr dlbssfs spfd, tif typf nbmf in b
                // qublififd 'nfw' fxprfssion must bf b singlf idfntififr.
                if (typfNbmf != null && typfNbmf.isQublififd()) {
                    fnv.frror(wifrf, "unqublififd.nbmf.rfquirfd", typfNbmf);
                }

                if (typfNbmf == null || !outfrArg.typf.isTypf(TC_CLASS)) {
                    if (!outfrArg.typf.isTypf(TC_ERROR)) {
                        fnv.frror(wifrf, "invblid.fifld.rfffrfndf",
                                  idNfw, outfrArg.typf);
                    }
                    outfrArg = null;
                } flsf {
                    // Don't pfrform difdks on domponfnts of qublififd nbmf
                    // ('gftQublififdClbssDffinition'), bfdbusf b qublififd
                    // nbmf is illfgbl in tiis dontfxt, bnd will ibvf prfviously
                    // bffn rfportfd bs bn frror.
                    ClbssDffinition od = fnv.gftClbssDffinition(outfrArg.typf);
                    Idfntififr nm = od.rfsolvfInnfrClbss(fnv, typfNbmf);
                    rigit = nfw TypfExprfssion(rigit.wifrf, Typf.tClbss(nm));
                    // Cifdk bddfss dirfdtly, sindf wf'rf not dblling toTypf().
                    fnv.rfsolvf(rigit.wifrf, dtx.fifld.gftClbssDffinition(),
                                rigit.typf);
                    // bnd fbll tirougi to fnv.gftClbssDffinition() bflow
                }
            }

            if (!(rigit instbndfof TypfExprfssion)) {
                // Tif dbll to 'toTypf' siould pfrform domponfnt bddfss difdks.
                rigit = nfw TypfExprfssion(rigit.wifrf, rigit.toTypf(fnv, dtx));
            }

            if (rigit.typf.isTypf(TC_CLASS))
                dff = fnv.gftClbssDffinition(rigit.typf);
        } dbtdi (AmbiguousClbss ff) {
            fnv.frror(wifrf, "bmbig.dlbss", ff.nbmf1, ff.nbmf2);
        } dbtdi (ClbssNotFound ff) {
            fnv.frror(wifrf, "dlbss.not.found", ff.nbmf, dtx.fifld);
        }

        Typf t = rigit.typf;
        boolfbn ibsErrors = t.isTypf(TC_ERROR);

        if (!t.isTypf(TC_CLASS)) {
            if (!ibsErrors) {
                fnv.frror(wifrf, "invblid.brg.typf", t, opNbmfs[op]);
                ibsErrors = truf;
            }
        }

        // If wf fbilfd to find b dlbss or b dlbss wbs bmbiguous, dff
        // mby bf null.  Bbil out.  Tiis bllows us to rfport multiplf
        // unfound or bmbiguous dlbssfs rbtifr tibn tripping ovfr bn
        // intfrnbl dompilfr frror.
        if (dff == null) {
            typf = Typf.tError;
            rfturn vsft;
        }

        // Add bn fxtrb brgumfnt, mbybf.
        Exprfssion brgs[] = tiis.brgs;
        brgs = NfwInstbndfExprfssion.
                insfrtOutfrLink(fnv, dtx, wifrf, dff, outfrArg, brgs);
        if (brgs.lfngti > tiis.brgs.lfngti)
            outfrArg = brgs[0]; // rfdopy tif difdkfd brg
        flsf if (outfrArg != null)
            // flsf sft it to void (mbybf it ibs b sidf-ffffdt)
            outfrArg = nfw CommbExprfssion(outfrArg.wifrf, outfrArg, null);

        // Composf b list of brgumfnt typfs
        Typf brgTypfs[] = nfw Typf[brgs.lfngti];

        for (int i = 0 ; i < brgs.lfngti ; i++) {
            // Don't difdk 'outfrArg' bgbin. Fix for 4030426.
            if (brgs[i] != blrfbdyCifdkfd) {
                vsft = brgs[i].difdkVbluf(fnv, dtx, vsft, fxp);
            }
            brgTypfs[i] = brgs[i].typf;
            ibsErrors = ibsErrors || brgTypfs[i].isTypf(TC_ERROR);
        }

        try {
            // Cifdk if tifrf brf bny typf frrors in tif brgumfnts
            if (ibsErrors) {
                typf = Typf.tError;
                rfturn vsft;
            }


            // Gft tif sourdf dlbss tibt tiis dfdlbrbtion bppfbrs in.
            ClbssDffinition sourdfClbss = dtx.fifld.gftClbssDffinition();

            ClbssDfdlbrbtion d = fnv.gftClbssDfdlbrbtion(t);

            // If tiis is bn bnonymous dlbss, ibndlf it spfdiblly now.
            if (body != null) {
                // Tif durrfnt pbdkbgf.
                Idfntififr pbdkbgfNbmf = sourdfClbss.gftNbmf().gftQublififr();

                // Tiis is bn bnonymous dlbss.
                ClbssDffinition supfrDff = null;
                if (dff.isIntfrfbdf()) {
                    // For intfrfbdfs, our supfrdlbss is jbvb.lbng.Objfdt.
                    // Wf dould just bssumf tibt jbvb.lbng.Objfdt ibs
                    // onf donstrudtor witi no brgumfnts in tif dodf
                    // tibt follows, but wf don't.  Tiis wby, if Objfdt
                    // grows b nfw donstrudtor (unlikfly) tifn tif
                    // dompilfr siould ibndlf it.
                    supfrDff = fnv.gftClbssDffinition(idJbvbLbngObjfdt);
                } flsf {
                    // Otifrwisf, dff is bdtublly our supfrdlbss.
                    supfrDff = dff;
                }
                // Try to find b mbtdiing donstrudtor in our supfrdlbss.
                MfmbfrDffinition donstrudtor =
                    supfrDff.mbtdiAnonConstrudtor(fnv, pbdkbgfNbmf, brgTypfs);
                if (donstrudtor != null) {
                    // Wf'vf found onf.  Prodfss tif body.
                    //
                    // Notf tibt wf brf pbssing in tif donstrudtors' brgumfnt
                    // typfs, rbtifr tibn tif brgumfnt typfs of tif bdtubl
                    // fxprfssions, to difdkLodblClbss().  Prfviously,
                    // tif fxprfssion typfs wfrf pbssfd in.  Tiis dould
                    // lfbd to troublf wifn onf of tif brgumfnt typfs wbs
                    // tif spfdibl intfrnbl typf tNull.  (bug 4054689).
                    if (trbding)
                        fnv.dtEvfnt(
                              "NfwInstbndfExprfssion.difdkVbluf: ANON CLASS " +
                              body + " SUPER " + dff);
                    vsft = body.difdkLodblClbss(fnv, dtx, vsft,
                                                dff, brgs,
                                                donstrudtor.gftTypf()
                                                .gftArgumfntTypfs());

                    // Sft t to bf tif truf typf of tiis fxprfssion.
                    // (bug 4102056).
                    t = body.gftClbssDfdlbrbtion().gftTypf();

                    dff = body;
                }
            } flsf {
                // Cifdk if it is bn intfrfbdf
                if (dff.isIntfrfbdf()) {
                    fnv.frror(wifrf, "nfw.intf", d);
                    rfturn vsft;
                }

                // Cifdk for bbstrbdt dlbss
                if (dff.mustBfAbstrbdt(fnv)) {
                    fnv.frror(wifrf, "nfw.bbstrbdt", d);
                    rfturn vsft;
                }
            }

            // Gft tif donstrudtor tibt tif "nfw" fxprfssion siould dbll.
            fifld = dff.mbtdiMftiod(fnv, sourdfClbss, idInit, brgTypfs);

            // Rfport bn frror if tifrf is no mbtdiing donstrudtor.
            if (fifld == null) {
                MfmbfrDffinition bnyInit = dff.findAnyMftiod(fnv, idInit);
                if (bnyInit != null &&
                    nfw MftiodExprfssion(wifrf, rigit, bnyInit, brgs)
                        .dibgnosfMismbtdi(fnv, brgs, brgTypfs))
                    rfturn vsft;
                String sig = d.gftNbmf().gftNbmf().toString();
                sig = Typf.tMftiod(Typf.tError, brgTypfs).typfString(sig, fblsf, fblsf);
                fnv.frror(wifrf, "unmbtdifd.donstr", sig, d);
                rfturn vsft;
            }

            if (fifld.isPrivbtf()) {
                ClbssDffinition ddff = fifld.gftClbssDffinition();
                if (ddff != sourdfClbss) {
                    // Usf bddfss mftiod.
                    implMftiod = ddff.gftAddfssMfmbfr(fnv, dtx, fifld, fblsf);
                }
            }

            // Cifdk for bbstrbdt bnonymous dlbss
            if (dff.mustBfAbstrbdt(fnv)) {
                fnv.frror(wifrf, "nfw.bbstrbdt", d);
                rfturn vsft;
            }

            if (fifld.rfportDfprfdbtfd(fnv)) {
                fnv.frror(wifrf, "wbrn.donstr.is.dfprfdbtfd",
                          fifld, fifld.gftClbssDffinition());
            }

            // Addording to JLS 6.6.2, b protfdtfd donstrudtor mby bf bddfssfd
            // by b dlbss instbndf drfbtion fxprfssion only from witiin tif
            // pbdkbgf in wiidi it is dffinfd.
            if (fifld.isProtfdtfd() &&
                !(sourdfClbss.gftNbmf().gftQublififr().fqubls(
                   fifld.gftClbssDfdlbrbtion().gftNbmf().gftQublififr()))) {
                fnv.frror(wifrf, "invblid.protfdtfd.donstrudtor.usf",
                          sourdfClbss);
            }

        } dbtdi (ClbssNotFound ff) {
            fnv.frror(wifrf, "dlbss.not.found", ff.nbmf, opNbmfs[op]);
            rfturn vsft;

        } dbtdi (AmbiguousMfmbfr ff) {
            fnv.frror(wifrf, "bmbig.donstr", ff.fifld1, ff.fifld2);
            rfturn vsft;
        }

        // Cbst brgumfnts
        brgTypfs = fifld.gftTypf().gftArgumfntTypfs();
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            brgs[i] = donvfrt(fnv, dtx, brgTypfs[i], brgs[i]);
        }
        if (brgs.lfngti > tiis.brgs.lfngti) {
            outfrArg = brgs[0]; // rfdopy tif difdkfd brg
            // mbintbin bn bddurbtf trff
            for (int i = 1 ; i < brgs.lfngti ; i++) {
                tiis.brgs[i-1] = brgs[i];
            }
        }

        // Tirow tif dfdlbrfd fxdfptions.
        ClbssDfdlbrbtion fxdfptions[] = fifld.gftExdfptions(fnv);
        for (int i = 0 ; i < fxdfptions.lfngti ; i++) {
            if (fxp.gft(fxdfptions[i]) == null) {
                fxp.put(fxdfptions[i], tiis);
            }
        }

        typf = t;

        rfturn vsft;
    }

    /**
     * Givfn b list of brgumfnts for b donstrudtor,
     * rfturn b possibly modififd list wiidi indludfs tif iiddfn
     * brgumfnt wiidi initiblizfs tif uplfvfl sflf pointfr.
     * @brg dff tif dlbss wiidi pfribps dontbins bn outfr link.
     * @brg outfrArg if non-null, bn fxplidit lodbtion in wiidi to donstrudt.
     */
    publid stbtid Exprfssion[] insfrtOutfrLink(Environmfnt fnv, Contfxt dtx,
                                               long wifrf, ClbssDffinition dff,
                                               Exprfssion outfrArg,
                                               Exprfssion brgs[]) {
        if (!dff.isTopLfvfl() && !dff.isLodbl()) {
            Exprfssion brgs2[] = nfw Exprfssion[1+brgs.lfngti];
            Systfm.brrbydopy(brgs, 0, brgs2, 1, brgs.lfngti);
            try {
                if (outfrArg == null)
                    outfrArg = dtx.findOutfrLink(fnv, wifrf,
                                                 dff.findAnyMftiod(fnv, idInit));
            } dbtdi (ClbssNotFound f) {
                // dif somfwifrf flsf
            }
            brgs2[0] = outfrArg;
            brgs = brgs2;
        }
        rfturn brgs;
    }

    /**
     * Cifdk void fxprfssion
     */
    publid Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        rfturn difdkVbluf(fnv, dtx, vsft, fxp);
    }

    /**
     * Inlinf
     */
    finbl int MAXINLINECOST = Stbtfmfnt.MAXINLINECOST;

    publid Exprfssion dopyInlinf(Contfxt dtx) {
        NfwInstbndfExprfssion f = (NfwInstbndfExprfssion)supfr.dopyInlinf(dtx);
        if (outfrArg != null) {
            f.outfrArg = outfrArg.dopyInlinf(dtx);
        }
        rfturn f;
    }

    Exprfssion inlinfNfwInstbndf(Environmfnt fnv, Contfxt dtx, Stbtfmfnt s) {
        if (fnv.dump()) {
            Systfm.out.println("INLINE NEW INSTANCE " + fifld + " in " + dtx.fifld);
        }
        LodblMfmbfr v[] = LodblMfmbfr.dopyArgumfnts(dtx, fifld);
        Stbtfmfnt body[] = nfw Stbtfmfnt[v.lfngti + 2];

        int o = 1;
        if (outfrArg != null && !outfrArg.typf.isTypf(TC_VOID)) {
            o = 2;
            body[1] = nfw VbrDfdlbrbtionStbtfmfnt(wifrf, v[1], outfrArg);
        } flsf if (outfrArg != null) {
            body[0] = nfw ExprfssionStbtfmfnt(wifrf, outfrArg);
        }
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            body[i+o] = nfw VbrDfdlbrbtionStbtfmfnt(wifrf, v[i+o], brgs[i]);
        }
        //Systfm.out.print("BEFORE:"); s.print(Systfm.out); Systfm.out.println();
        body[body.lfngti - 1] = (s != null) ? s.dopyInlinf(dtx, fblsf) : null;
        //Systfm.out.print("COPY:"); body[body.lfngti - 1].print(Systfm.out); Systfm.out.println();
        //Systfm.out.print("AFTER:"); s.print(Systfm.out); Systfm.out.println();
        LodblMfmbfr.donfWitiArgumfnts(dtx, v);

        rfturn nfw InlinfNfwInstbndfExprfssion(wifrf, typf, fifld, nfw CompoundStbtfmfnt(wifrf, body)).inlinf(fnv, dtx);
    }

    publid Exprfssion inlinf(Environmfnt fnv, Contfxt dtx) {
        rfturn inlinfVbluf(fnv, dtx);
    }
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        if (body != null) {
            body.inlinfLodblClbss(fnv);
        }
        ClbssDffinition rffd = fifld.gftClbssDffinition();
        UplfvflRfffrfndf r = rffd.gftRfffrfndfsFrozfn();
        if (r != null) {
            r.willCodfArgumfnts(fnv, dtx);
        }
        //rigit = rigit.inlinfVbluf(fnv, dtx);

        try {
            if (outfrArg != null) {
                if (outfrArg.typf.isTypf(TC_VOID))
                    outfrArg = outfrArg.inlinf(fnv, dtx);
                flsf
                    outfrArg = outfrArg.inlinfVbluf(fnv, dtx);
            }
            for (int i = 0 ; i < brgs.lfngti ; i++) {
                brgs[i] = brgs[i].inlinfVbluf(fnv, dtx);
            }
            // Tiis 'fblsf' tibt fy put in is infxplidbblf to mf
            // tif dfdision to not inlinf nfw instbndf fxprfssions
            // siould bf rfvisitfd.  - dps
            if (fblsf && fnv.opt() && fifld.isInlinfbblf(fnv, fblsf) &&
                (!dtx.fifld.isInitiblizfr()) && dtx.fifld.isMftiod() &&
                (dtx.gftInlinfMfmbfrContfxt(fifld) == null)) {
                Stbtfmfnt s = (Stbtfmfnt)fifld.gftVbluf(fnv);
                if ((s == null)
                    || (s.dostInlinf(MAXINLINECOST, fnv, dtx) < MAXINLINECOST))  {
                    rfturn inlinfNfwInstbndf(fnv, dtx, s);
                }
            }
        } dbtdi (ClbssNotFound f) {
            tirow nfw CompilfrError(f);
        }
        if (outfrArg != null && outfrArg.typf.isTypf(TC_VOID)) {
            Exprfssion f = outfrArg;
            outfrArg = null;
            rfturn nfw CommbExprfssion(wifrf, f, tiis);
        }
        rfturn tiis;
    }

    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        if (body != null) {
            rfturn tirfsi;      // don't dopy dlbssfs...
        }
        if (dtx == null) {
            rfturn 2 + supfr.dostInlinf(tirfsi, fnv, dtx);
        }
        // sourdfClbss is tif durrfnt dlbss trying to inlinf tiis mftiod
        ClbssDffinition sourdfClbss = dtx.fifld.gftClbssDffinition();
        try {
            // Wf only bllow tif inlining if tif durrfnt dlbss dbn bddfss
            // tif fifld bnd tif fifld's dlbss;
            if (    sourdfClbss.pfrmitInlinfdAddfss(fnv, fifld.gftClbssDfdlbrbtion())
                 && sourdfClbss.pfrmitInlinfdAddfss(fnv, fifld)) {
                rfturn 2 + supfr.dostInlinf(tirfsi, fnv, dtx);
            }
        } dbtdi (ClbssNotFound f) {
        }
        rfturn tirfsi;
    }


    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        dodfCommon(fnv, dtx, bsm, fblsf);
    }
    publid void dodfVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        dodfCommon(fnv, dtx, bsm, truf);
    }
    @SupprfssWbrnings("fblltirougi")
    privbtf void dodfCommon(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm,
                            boolfbn forVbluf) {
        bsm.bdd(wifrf, opd_nfw, fifld.gftClbssDfdlbrbtion());
        if (forVbluf) {
            bsm.bdd(wifrf, opd_dup);
        }

        ClbssDffinition rffd = fifld.gftClbssDffinition();
        UplfvflRfffrfndf r = rffd.gftRfffrfndfsFrozfn();

        if (r != null) {
            r.dodfArgumfnts(fnv, dtx, bsm, wifrf, fifld);
        }

        if (outfrArg != null) {
            outfrArg.dodfVbluf(fnv, dtx, bsm);
            switdi (outfrArg.op) {
            dbsf THIS:
            dbsf SUPER:
            dbsf NEW:
                // gubrbntffd non-null
                brfbk;
            dbsf FIELD: {
                MfmbfrDffinition f = ((FifldExprfssion)outfrArg).fifld;
                if (f != null && f.isNfvfrNull()) {
                    brfbk;
                }
                // flsf fbll tirougi:
            }
            dffbult:
                // Tfst for nullity by invoking somf trivibl opfrbtion
                // tibt dbn tirow b NullPointfrExdfption.
                try {
                    ClbssDffinition d = fnv.gftClbssDffinition(idJbvbLbngObjfdt);
                    MfmbfrDffinition gftd = d.gftFirstMbtdi(idGftClbss);
                    bsm.bdd(wifrf, opd_dup);
                    bsm.bdd(wifrf, opd_invokfvirtubl, gftd);
                    bsm.bdd(wifrf, opd_pop);
                } dbtdi (ClbssNotFound f) {
                }
            }
        }

        if (implMftiod != null) {
            // Construdtor dbll will bf vib bn bddfss mftiod.
            // Pbss 'null' bs tif vbluf of tif dummy brgumfnt.
            bsm.bdd(wifrf, opd_bdonst_null);
        }

        for (int i = 0 ; i < brgs.lfngti ; i++) {
            brgs[i].dodfVbluf(fnv, dtx, bsm);
        }
        bsm.bdd(wifrf, opd_invokfspfdibl,
                ((implMftiod != null) ? implMftiod : fifld));
    }
}
