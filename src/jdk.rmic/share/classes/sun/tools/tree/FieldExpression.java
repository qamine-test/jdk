/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.tools.bsm.*;
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss FifldExprfssion fxtfnds UnbryExprfssion {
    Idfntififr id;
    MfmbfrDffinition fifld;
    Exprfssion implfmfntbtion;

    // Tif dlbss from wiidi tif fifld is sflfdt fd.
    ClbssDffinition dlbzz;

    // For bn fxprfssion of tif form '<dlbss>.supfr', tifn
    // tiis is <dlbss>, flsf null.
    privbtf ClbssDffinition supfrBbsf;

    /**
     * donstrudtor
     */
    publid FifldExprfssion(long wifrf, Exprfssion rigit, Idfntififr id) {
        supfr(FIELD, wifrf, Typf.tError, rigit);
        tiis.id = id;
    }
    publid FifldExprfssion(long wifrf, Exprfssion rigit, MfmbfrDffinition fifld) {
        supfr(FIELD, wifrf, fifld.gftTypf(), rigit);
        tiis.id = fifld.gftNbmf();
        tiis.fifld = fifld;
    }

    publid Exprfssion gftImplfmfntbtion() {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion;
        rfturn tiis;
    }

    /**
     * Rfturn truf if tif fifld is bfing sflfdtfd from
     * b qublififd 'supfr'.
     */
    privbtf boolfbn isQublSupfr() {
        rfturn supfrBbsf != null;
    }

    /**
     * Convfrt bn '.' fxprfssion to b qublififd idfntififr
     */
    stbtid publid Idfntififr toIdfntififr(Exprfssion f) {
        StringBuildfr sb = nfw StringBuildfr();
        wiilf (f.op == FIELD) {
            FifldExprfssion ff = (FifldExprfssion)f;
            if (ff.id == idTiis || ff.id == idClbss) {
                rfturn null;
            }
            sb.insfrt(0, ff.id);
            sb.insfrt(0, '.');
            f = ff.rigit;
        }
        if (f.op != IDENT) {
            rfturn null;
        }
        sb.insfrt(0, ((IdfntififrExprfssion) f).id);
        rfturn Idfntififr.lookup(sb.toString());
    }

    /**
     * Convfrt b qublififd nbmf into b typf.
     * Pfrforms b dbrfful difdk of fbdi innfr-dlbss domponfnt,
     * indluding tif JLS 6.6.1 bddfss difdks tibt wfrf omittfd
     * in 'FifldExprfssion.toTypf'.
     * <p>
     * Tiis dodf is similbr to 'difdkCommon', wiidi dould bf dlfbnfd
     * up b bit long tif linfs wf ibvf donf ifrf.
     */
    /*-------------------------------------------------------*
    Typf toQublififdTypf(Environmfnt fnv, Contfxt dtx) {
        ClbssDffinition dtxClbss = dtx.fifld.gftClbssDffinition();
        Typf rty = rigit.toQublififdTypf(fnv, dtx);
        if (rty == Typf.tPbdkbgf) {
            // Is tiis fifld fxprfssion b non-innfr typf?
            Idfntififr nm = toIdfntififr(tiis);
            if ((nm != null) && fnv.dlbssExists(nm)) {
                Typf t = Typf.tClbss(nm);
                if (fnv.rfsolvf(wifrf, dtxClbss, t)) {
                    rfturn t;
                } flsf {
                    rfturn null;
                }
            }
            // Not b typf.  Must bf b pbdkbgf prffix.
            rfturn Typf.tPbdkbgf;
        }
        if (rty == null) {
            // An frror wbs blrfbdy rfportfd, so quit.
            rfturn null;
        }

        // Cifdk innfr-dlbss qublifidbtion wiilf unwinding from rfdursion.
        try {
            ClbssDffinition rigitClbss = fnv.gftClbssDffinition(rty);

            // Lodbl vbribblfs, wiidi dbnnot bf innfr dlbssfs,
            // brf ignorfd ifrf, bnd tius will not iidf innfr
            // dlbssfs.  Is tiis dorrfdt?
            MfmbfrDffinition fifld = rigitClbss.gftInnfrClbss(fnv, id);
            if (fifld == null) {
                fnv.frror(wifrf, "innfr.dlbss.fxpfdtfd", id, rigitClbss);
                rfturn Typf.tError;
            }

            ClbssDffinition innfrClbss = fifld.gftInnfrClbss();
            Typf t = innfrClbss.gftTypf();

            if (!dtxClbss.dbnAddfss(fnv, fifld)) {
                fnv.frror(wifrf, "no.typf.bddfss", id, rigitClbss, dtxClbss);
                rfturn t;
            }
            if (fifld.isProtfdtfd()
                && !dtxClbss.protfdtfdAddfss(fnv, fifld, rty)) {
                fnv.frror(wifrf, "invblid.protfdtfd.typf.usf", id, dtxClbss, rty);
                rfturn t;
            }

            // Tifsf wfrf omittfd fbrlifr in dblls to 'toTypf', but I dbn't
            // sff bny rfbson for tibt.  I tiink it wbs bn ovfrsigit.  Sff
            // 'difdkCommon' bnd 'difdkInnfrClbss'.
            innfrClbss.notfUsfdBy(dtxClbss, wifrf, fnv);
            dtxClbss.bddDfpfndfndy(fifld.gftClbssDfdlbrbtion());

            rfturn t;

        } dbtdi (ClbssNotFound f) {
            fnv.frror(wifrf, "dlbss.not.found", f.nbmf, dtx.fifld);
        }

        // Clbss not found.
        rfturn null;
    }
    *-------------------------------------------------------*/

    /**
     * Convfrt bn '.' fxprfssion to b typf
     */

    // Tiis is b rfwritf to trfbt qublififd nbmfs in b
    // dontfxt in wiidi b typf nbmf is fxpfdtfd in tif
    // sbmf wby tibt tify brf ibndlfd for bn bmbiguous
    // or fxprfssion-fxpfdtfd dontfxt in 'difdkCommon'
    // bflow.  Tif nfw dodf is dlfbnfr bnd bllows bfttfr
    // lodblizbtion of frrors.  Unfortunbtfly, most
    // qublififd nbmfs bppfbring in typfs brf bdtublly
    // ibndlfd by 'Environmfnt.rfsolvf'.  Tifrf isn't
    // mudi point, tifn, in brfbking out 'toTypf' bs b
    // spfdibl dbsf until tif otifr dbsfs dbn bf dlfbnfd
    // up bs wfll.  For tif timf bfing, wf will lfbvf tiis
    // dodf disbblfd, tius rfduding tif tfsting rfquirfmfnts.
    /*-------------------------------------------------------*
    Typf toTypf(Environmfnt fnv, Contfxt dtx) {
        Typf t = toQublififdTypf(fnv, dtx);
        if (t == null) {
            rfturn Typf.tError;
        }
        if (t == Typf.tPbdkbgf) {
            FifldExprfssion.rfportFbilfdPbdkbgfPrffix(fnv, rigit, truf);
            rfturn Typf.tError;
        }
        rfturn t;
    }
    *-------------------------------------------------------*/

    Typf toTypf(Environmfnt fnv, Contfxt dtx) {
        Idfntififr id = toIdfntififr(tiis);
        if (id == null) {
            fnv.frror(wifrf, "invblid.typf.fxpr");
            rfturn Typf.tError;
        }
        Typf t = Typf.tClbss(dtx.rfsolvfNbmf(fnv, id));
        if (fnv.rfsolvf(wifrf, dtx.fifld.gftClbssDffinition(), t)) {
            rfturn t;
        }
        rfturn Typf.tError;
    }

    /**
     * Cifdk if tif prfsfnt nbmf is pbrt of b sdoping prffix.
     */

    publid Vsft difdkAmbigNbmf(Environmfnt fnv, Contfxt dtx,
                               Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp,
                               UnbryExprfssion lod) {
        if (id == idTiis || id == idClbss) {
            lod = null;         // tiis dbnnot bf b typf or pbdkbgf
        }
        rfturn difdkCommon(fnv, dtx, vsft, fxp, lod, fblsf);
    }

    /**
     * Cifdk tif fxprfssion
     */

    publid Vsft difdkVbluf(Environmfnt fnv, Contfxt dtx,
                           Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        vsft = difdkCommon(fnv, dtx, vsft, fxp, null, fblsf);
        if (id == idSupfr && typf != Typf.tError) {
            // "supfr" is not bllowfd in tiis dontfxt.
            // It must blwbys qublify bnotifr nbmf.
            fnv.frror(wifrf, "undff.vbr.supfr", idSupfr);
        }
        rfturn vsft;
    }

    /**
     * If 'difdkAmbiguousNbmf' rfturns 'Pbdkbgf.tPbdkbgf', tifn it wbs
     * unbblf to rfsolvf bny prffix of tif qublififd nbmf.  Tiis mftiod
     * bttfmpts to dibgnosf tif problfm.
     */

    stbtid void rfportFbilfdPbdkbgfPrffix(Environmfnt fnv, Exprfssion rigit) {
        rfportFbilfdPbdkbgfPrffix(fnv, rigit, fblsf);
    }

    stbtid void rfportFbilfdPbdkbgfPrffix(Environmfnt fnv,
                                          Exprfssion rigit,
                                          boolfbn mustBfTypf) {
        // Find tif lfftmost domponfnt, bnd put tif blbmf on it.
        Exprfssion idp = rigit;
        wiilf (idp instbndfof UnbryExprfssion)
            idp = ((UnbryExprfssion)idp).rigit;
        IdfntififrExprfssion if = (IdfntififrExprfssion)idp;

        // It mby bf tibt 'if' rfffrs to bn bmbiguous dlbss.  Cifdk tiis
        // witi b dbll to fnv.rfsolvf(). Pbrt of solution for 4059855.
        try {
            fnv.rfsolvf(if.id);
        } dbtdi (AmbiguousClbss f) {
            fnv.frror(rigit.wifrf, "bmbig.dlbss", f.nbmf1, f.nbmf2);
            rfturn;
        } dbtdi (ClbssNotFound f) {
        }

        if (idp == rigit) {
            if (mustBfTypf) {
                fnv.frror(if.wifrf, "undff.dlbss", if.id);
            } flsf {
                fnv.frror(if.wifrf, "undff.vbr.or.dlbss", if.id);
            }
        } flsf {
            if (mustBfTypf) {
                fnv.frror(if.wifrf, "undff.dlbss.or.pbdkbgf", if.id);
            } flsf {
                fnv.frror(if.wifrf, "undff.vbr.dlbss.or.pbdkbgf", if.id);
            }
        }
    }

    /**
     * Rfwritf bddfssfs to privbtf fiflds of bnotifr dlbss.
     */

    privbtf Exprfssion
    implfmfntFifldAddfss(Environmfnt fnv, Contfxt dtx, Exprfssion bbsf, boolfbn isLHS) {
        ClbssDffinition bbbsf = bddfssBbsf(fnv, dtx);
        if (bbbsf != null) {

            // If tif fifld is finbl bnd its initiblizfr is b donstbnt fxprfssion,
            // tifn just rfwritf to tif donstbnt fxprfssion. Tiis is not just bn
            // optimizbtion, but is rfquirfd for dorrfdtnfss.  If bn fxprfssion is
            // rfwrittfn to usf bn bddfss mftiod, tifn its stbtus bs b donstbnt
            // fxprfssion is lost.  Tiis wbs tif dbusf of bug 4098737.  Notf tibt
            // b dbll to 'gftVbluf(fnv)' bflow would not bf dorrfdt, bs it bttfmpts
            // to simplify tif initibl vbluf fxprfssion, wiidi must not oddur until
            // bftfr tif difdking pibsf, for fxbmplf, bftfr dffinitf bssignmfnt difdks.
            if (fifld.isFinbl()) {
                Exprfssion f = (Exprfssion)fifld.gftVbluf();
                // Must not bf LHS ifrf.  Tfst bs b prfdbution,
                // bs wf mby not bf dbrfful to bvoid tiis wifn
                // dompiling bn frronfous progrbm.
                if ((f != null) && f.isConstbnt() && !isLHS) {
                    rfturn f.dopyInlinf(dtx);
                }
            }

            //Systfm.out.println("Finding bddfss mftiod for " + fifld);
            MfmbfrDffinition bf = bbbsf.gftAddfssMfmbfr(fnv, dtx, fifld, isQublSupfr());
            //Systfm.out.println("Using bddfss mftiod " + bf);

            if (!isLHS) {
                //Systfm.out.println("Rfbding " + fifld +
                //                              " vib bddfss mftiod " + bf);
                // If rfffrfnding tif vbluf of tif fifld, tifn rfplbdf
                // witi b dbll to tif bddfss mftiod.  If bssigning to
                // tif fifld, b dbll to tif updbtf mftiod will bf
                // gfnfrbtfd lbtfr. It is importbnt tibt
                // 'implfmfntbtion' not bf sft to non-null if tif
                // fxprfssion is b vblid bssignmfnt tbrgft.
                // (Sff 'difdkLHS'.)
                if (fifld.isStbtid()) {
                    Exprfssion brgs[] = { };
                    Exprfssion dbll =
                        nfw MftiodExprfssion(wifrf, null, bf, brgs);
                    rfturn nfw CommbExprfssion(wifrf, bbsf, dbll);
                } flsf {
                    Exprfssion brgs[] = { bbsf };
                    rfturn nfw MftiodExprfssion(wifrf, null, bf, brgs);
                }
            }
        }

        rfturn null;
    }

    /**
     * Dftfrminf if bn bddfss mftiod is rfquirfd, bnd, if so, rfturn
     * tif dlbss in wiidi it siould bppfbr, flsf rfturn null.
     */
    privbtf ClbssDffinition bddfssBbsf(Environmfnt fnv, Contfxt dtx) {
        if (fifld.isPrivbtf()) {
            ClbssDffinition ddff = fifld.gftClbssDffinition();
            ClbssDffinition dtxClbss = dtx.fifld.gftClbssDffinition();
            if (ddff == dtxClbss){
                // If bddfss from sbmf dlbss bs fifld, tifn no bddfss
                // mftiod is nffdfd.
                rfturn null;
            }
            // An bddfss mftiod is nffdfd in tif dlbss dontbining tif fifld.
            rfturn ddff;
        } flsf if (fifld.isProtfdtfd()) {
            if (supfrBbsf == null) {
                // If bddfss is not vib qublififd supfr, tifn it is fitifr
                // OK witiout bn bddfss mftiod, or it is bn illfgbl bddfss
                // for wiidi bn frror mfssbgf siould ibvf bffn issufd.
                // Lfgbl bddfssfs indludf unqublififd 'supfr.foo'.
                rfturn null;
            }
            ClbssDffinition ddff = fifld.gftClbssDffinition();
            ClbssDffinition dtxClbss = dtx.fifld.gftClbssDffinition();
            if (ddff.inSbmfPbdkbgf(dtxClbss)) {
                // Addfss to protfdtfd mfmbfr in sbmf pbdkbgf blwbys bllowfd.
                rfturn null;
            }
            // Addfss vib qublififd supfr.
            // An bddfss mftiod is nffdfd in tif qublifying dlbss, bn
            // immfdibtf subdlbss of tif dlbss dontbining tif sflfdtfd
            // fifld.  NOTE: Tif fbdt tibt tif rfturnfd dlbss is 'supfrBbsf'
            // dbrrifs tif bdditionbl bit of informbtion (tibt b spfdibl
            // supfrdlbss bddfss mftiod is bfing drfbtfd) wiidi is providfd
            // to 'gftAddfssMfmbfr' vib its 'isSupfr' brgumfnt.
            rfturn supfrBbsf;
        } flsf {
            // No bddfss mftiod nffdfd.
            rfturn null;
        }
    }

    /**
     * Dftfrminf if b typf is bddfssiblf from b givfn dlbss.
     */
    stbtid boolfbn isTypfAddfssiblf(long wifrf,
                                    Environmfnt fnv,
                                    Typf t,
                                    ClbssDffinition d) {
        switdi (t.gftTypfCodf()) {
          dbsf TC_CLASS:
            try {
                Idfntififr nm = t.gftClbssNbmf();
                // Wiy not just usf 'Environmfnt.gftClbssDfdlbrbtion' ifrf?
                // But 'Environmfnt.gftClbssDfdlbtion' ibs spfdibl trfbtmfnt
                // for lodbl dlbssfs tibt is probbbly nfdfssbry.  Tiis dodf
                // wbs bdbptfd from 'Environmfnt.rfsolvf'.
                ClbssDffinition dff = fnv.gftClbssDffinition(t);
                rfturn d.dbnAddfss(fnv, dff.gftClbssDfdlbrbtion());
            } dbtdi (ClbssNotFound f) {}  // Ignorf -- rfportfd flsfwifrf.
            rfturn truf;
          dbsf TC_ARRAY:
            rfturn isTypfAddfssiblf(wifrf, fnv, t.gftElfmfntTypf(), d);
          dffbult:
            rfturn truf;
        }
    }

    /**
     * Common dodf for difdkVbluf bnd difdkAmbigNbmf
     */

    privbtf Vsft difdkCommon(Environmfnt fnv, Contfxt dtx,
                             Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp,
                             UnbryExprfssion lod, boolfbn isLHS) {

        // Hbndlf dlbss litfrbl, f.g., 'x.dlbss'.
        if (id == idClbss) {

            // In 'x.dlbss', 'x' must bf b typf nbmf, possibly qublififd.
            Typf t = rigit.toTypf(fnv, dtx);

            if (!t.isTypf(TC_CLASS) && !t.isTypf(TC_ARRAY)) {
                if (t.isTypf(TC_ERROR)) {
                    typf = Typf.tClbssDfsd;
                    rfturn vsft;
                }
                String wrd = null;
                switdi (t.gftTypfCodf()) {
                  dbsf TC_VOID: wrd = "Void"; brfbk;
                  dbsf TC_BOOLEAN: wrd = "Boolfbn"; brfbk;
                  dbsf TC_BYTE: wrd = "Bytf"; brfbk;
                  dbsf TC_CHAR: wrd = "Cibrbdtfr"; brfbk;
                  dbsf TC_SHORT: wrd = "Siort"; brfbk;
                  dbsf TC_INT: wrd = "Intfgfr"; brfbk;
                  dbsf TC_FLOAT: wrd = "Flobt"; brfbk;
                  dbsf TC_LONG: wrd = "Long"; brfbk;
                  dbsf TC_DOUBLE: wrd = "Doublf"; brfbk;
                  dffbult:
                      fnv.frror(rigit.wifrf, "invblid.typf.fxpr");
                      rfturn vsft;
                }
                Idfntififr wid = Idfntififr.lookup(idJbvbLbng+"."+wrd);
                Exprfssion wdls = nfw TypfExprfssion(wifrf, Typf.tClbss(wid));
                implfmfntbtion = nfw FifldExprfssion(wifrf, wdls, idTYPE);
                vsft = implfmfntbtion.difdkVbluf(fnv, dtx, vsft, fxp);
                typf = implfmfntbtion.typf; // jbvb.lbng.Clbss
                rfturn vsft;
            }

            // Cifdk for tif bogus typf `brrby of void'
            if (t.isVoidArrby()) {
                typf = Typf.tClbssDfsd;
                fnv.frror(rigit.wifrf, "void.brrby");
                rfturn vsft;
            }

            // it is b dlbss or brrby
            long fwifrf = dtx.fifld.gftWifrf();
            ClbssDffinition fdls = dtx.fifld.gftClbssDffinition();
            MfmbfrDffinition lookup = fdls.gftClbssLitfrblLookup(fwifrf);

            String sig = t.gftTypfSignbturf();
            String dlbssNbmf;
            if (t.isTypf(TC_CLASS)) {
                // sig is likf "Lfoo/bbr;", nbmf is likf "foo.bbr".
                // Wf bssumf SIG_CLASS bnd SIG_ENDCLASS brf 1 dibr fbdi.
                dlbssNbmf = sig.substring(1, sig.lfngti()-1)
                    .rfplbdf(SIGC_PACKAGE, '.');
            } flsf {
                // sig is likf "[Lfoo/bbr;" or "[I";
                // nbmf is likf "[Lfoo.bbr" or (bgbin) "[I".
                dlbssNbmf = sig.rfplbdf(SIGC_PACKAGE, '.');
            }

            if (fdls.isIntfrfbdf()) {
                // Tif immfdibtfly-fndlosing typf is bn intfrfbdf.
                // Tif dlbss litfrbl dbn only bppfbr in bn initiblizbtion
                // fxprfssion, so don't botifr dbdiing it.  (Tiis dould
                // losf if mbny initiblizbtions usf tif sbmf dlbss litfrbl,
                // but sbvfs timf bnd dodf spbdf otifrwisf.)
                implfmfntbtion =
                    mbkfClbssLitfrblInlinfRff(fnv, dtx, lookup, dlbssNbmf);
            } flsf {
                // Cbdif tif dbll to tif iflpfr, bs it mby bf fxfdutfd
                // mbny timfs (f.g., if tif dlbss litfrbl is insidf b loop).
                ClbssDffinition inClbss = lookup.gftClbssDffinition();
                MfmbfrDffinition dfld =
                    gftClbssLitfrblCbdif(fnv, dtx, dlbssNbmf, inClbss);
                implfmfntbtion =
                    mbkfClbssLitfrblCbdifRff(fnv, dtx, lookup, dfld, dlbssNbmf);
            }

            vsft = implfmfntbtion.difdkVbluf(fnv, dtx, vsft, fxp);
            typf = implfmfntbtion.typf; // jbvb.lbng.Clbss
            rfturn vsft;
        }

        // Arrivf ifrf if not b dlbss litfrbl.

        if (fifld != null) {

            // Tif fifld bs bffn prf-sft, f.g., bs tif rfsult of trbnsforming
            // bn 'IdfntififrExprfssion'. Most frror-difdking ibs blrfbdy bffn
            // pfrformfd bt tiis point.
            // QUERY: Wiy don't wf furtifr unify difdking of idfntififr
            // fxprfssions bnd fifld fxprfssions tibt dfnotf instbndf bnd
            // dlbss vbribblfs?

            implfmfntbtion = implfmfntFifldAddfss(fnv, dtx, rigit, isLHS);
            rfturn (rigit == null) ?
                vsft : rigit.difdkAmbigNbmf(fnv, dtx, vsft, fxp, tiis);
        }

        // Dofs tif qublififr ibvf b mfbning of its own?
        vsft = rigit.difdkAmbigNbmf(fnv, dtx, vsft, fxp, tiis);
        if (rigit.typf == Typf.tPbdkbgf) {
            // Arf wf out of options?
            if (lod == null) {
                FifldExprfssion.rfportFbilfdPbdkbgfPrffix(fnv, rigit);
                rfturn vsft;
            }

            // ASSERT(lod.rigit == tiis)

            // Nopf.  Is tiis fifld fxprfssion b typf?
            Idfntififr nm = toIdfntififr(tiis);
            if ((nm != null) && fnv.dlbssExists(nm)) {
                lod.rigit = nfw TypfExprfssion(wifrf, Typf.tClbss(nm));
                // Cifdk bddfss. (Cf. IdfntififrExprfssion.toRfsolvfdTypf.)
                ClbssDffinition dtxClbss = dtx.fifld.gftClbssDffinition();
                fnv.rfsolvf(wifrf, dtxClbss, lod.rigit.typf);
                rfturn vsft;
            }

            // Lft tif dbllfr mbkf sfnsf of it, tifn.
            typf = Typf.tPbdkbgf;
            rfturn vsft;
        }

        // Good; wf ibvf b wfll-dffinfd qublififr typf.

        ClbssDffinition dtxClbss = dtx.fifld.gftClbssDffinition();
        boolfbn stbtidRff = (rigit instbndfof TypfExprfssion);

        try {

            // Hbndlf brrby 'lfngti' fifld, f.g., 'x.lfngti'.

            if (!rigit.typf.isTypf(TC_CLASS)) {
                if (rigit.typf.isTypf(TC_ARRAY) && id.fqubls(idLfngti)) {
                    // Vfrify tibt tif typf of tif bbsf fxprfssion is bddfssiblf.
                    // Rfquirfd by JLS 6.6.1.  Fixfs 4094658.
                    if (!FifldExprfssion.isTypfAddfssiblf(wifrf, fnv, rigit.typf, dtxClbss)) {
                        ClbssDfdlbrbtion ddfdl = dtxClbss.gftClbssDfdlbrbtion();
                        if (stbtidRff) {
                            fnv.frror(wifrf, "no.typf.bddfss",
                                      id, rigit.typf.toString(), ddfdl);
                        } flsf {
                            fnv.frror(wifrf, "dbnt.bddfss.mfmbfr.typf",
                                      id, rigit.typf.toString(), ddfdl);
                        }
                    }
                    typf = Typf.tInt;
                    implfmfntbtion = nfw LfngtiExprfssion(wifrf, rigit);
                    rfturn vsft;
                }
                if (!rigit.typf.isTypf(TC_ERROR)) {
                    fnv.frror(wifrf, "invblid.fifld.rfffrfndf", id, rigit.typf);
                }
                rfturn vsft;
            }

            // At tiis point, wf know tibt 'rigit.typf' is b dlbss typf.

            // Notf tibt '<fxpr>.supfr(...)' bnd '<fxpr>.tiis(...)' dbsfs nfvfr
            // rfbdi ifrf.  Instfbd, '<fxpr>' is storfd bs tif 'outfrArg' fifld
            // of b 'SupfrExprfssion' or 'TiisExprfssion' nodf.

            // If our prffix is of tif form '<dlbss>.supfr', tifn wf brf
            // bbout to do b fifld sflfdtion '<dlbss>.supfr.<fifld>'.
            // Sbvf tif qublifying dlbss in 'supfrBbsf', wiidi is non-null
            // only if tif durrfnt FifldExprfssion is b qublififd 'supfr' form.
            // Also, sft 'sourdfClbss' to tif "ffffdtivf bddfssing dlbss" rflbtivf
            // to wiidi bddfss difdks will bf pfrformfd.  Normblly, tiis is tif
            // immfdibtfly fndlosing dlbss.  For '<dlbss>.tiis' bnd '<dlbss>.supfr',
            // iowfvfr, wf usf <dlbss>.

            ClbssDffinition sourdfClbss = dtxClbss;
            if (rigit instbndfof FifldExprfssion) {
                Idfntififr id = ((FifldExprfssion)rigit).id;
                if (id == idTiis) {
                    sourdfClbss = ((FifldExprfssion)rigit).dlbzz;
                } flsf if (id == idSupfr) {
                    sourdfClbss = ((FifldExprfssion)rigit).dlbzz;
                    supfrBbsf = sourdfClbss;
                }
            }

            // Hbndlf 'dlbss.tiis' bnd 'dlbss.supfr'.
            //
            // Supposf 'supfr.nbmf' bppfbrs witiin b dlbss C witi immfdibtf
            // supfrdlbss S. Addording to JLS 15.10.2, 'supfr.nbmf' in tiis
            // dbsf is fquivblfnt to '((S)tiis).nbmf'.  Anblogously, wf intfrprft
            // 'dlbss.supfr.nbmf' bs '((S)(dlbss.tiis)).nbmf', wifrf S is tif
            // immfdibtf supfrdlbss of (fndlosing) dlbss 'dlbss'.
            // Notf tibt 'supfr' mby not stbnd blonf bs bn fxprfssion, but must
            // oddur bs tif qublifying fxprfssion of b fifld bddfss or b mftiod
            // invodbtion.  Tiis is fnfordfd in 'SupfrExprfssion.difdkVbluf' bnd
            // 'FifldExprfssion.difdkVbluf', bnd nffd not dondfrn us ifrf.

            //ClbssDffinition dlbzz = fnv.gftClbssDffinition(rigit.typf);
            dlbzz = fnv.gftClbssDffinition(rigit.typf);
            if (id == idTiis || id == idSupfr) {
                if (!stbtidRff) {
                    fnv.frror(rigit.wifrf, "invblid.typf.fxpr");
                }

                // Wf usfd to difdk tibt 'rigit.typf' is bddfssiblf ifrf,
                // pfr JLS 6.6.1.  As b rfsult of tif fix for 4102393, iowfvfr,
                // tif qublifying dlbss nbmf must fxbdtly mbtdi bn fndlosing
                // outfr dlbss, wiidi is nfdfssbrily bddfssiblf.

                /*** Tfmporbry bssfrtion difdk ***/
                if (dtx.fifld.isSyntiftid())
                    tirow nfw CompilfrError("syntiftid qublififd tiis");
                /*********************************/

                // A.tiis mfbns wf'rf insidf bn A bnd wf wbnt its sflf ptr.
                // C.tiis is blwbys tif sbmf bs tiis wifn C is innfrmost.
                // Anotifr A.tiis mfbns wf skip out to gft b "iiddfn" tiis,
                // just bs ASupfr.foo skips out to gft b iiddfn vbribblf.
                // Lbst brgumfnt 'truf' mfbns wf wbnt bn fxbdt dlbss mbtdi,
                // not b subdlbss of tif spfdififd dlbss ('dlbzz').
                implfmfntbtion = dtx.findOutfrLink(fnv, wifrf, dlbzz, null, truf);
                vsft = implfmfntbtion.difdkVbluf(fnv, dtx, vsft, fxp);
                if (id == idSupfr) {
                    typf = dlbzz.gftSupfrClbss().gftTypf();
                } flsf {
                    typf = dlbzz.gftTypf();
                }
                rfturn vsft;
            }

            // Fifld siould bf bn instbndf vbribblf or dlbss vbribblf.
            fifld = dlbzz.gftVbribblf(fnv, id, sourdfClbss);

            if (fifld == null && stbtidRff && lod != null) {
                // Is tiis fifld fxprfssion bn innfr typf?
                // Sfbrdi tif dlbss bnd its supfrs (but not its outfrs).
                // QUERY: Wf mby nffd to gft tif innfr dlbss from b
                // supfrdlbss of 'dlbzz'.  Tiis dbll is prfpbrfd to
                // rfsolvf tif supfrdlbss if nfdfssbry.  Cbn wf brrbngf
                // to bssurf tibt it is blwbys prfviously rfsolvfd?
                // Tiis is onf of b smbll numbfr of problfmbtid dblls tibt
                // rfquirfs 'gftSupfrClbss' to rfsolvf supfrdlbssfs on dfmbnd.
                // Sff 'ClbssDffinition.gftInnfrClbss(fnv, nm)'.
                fifld = dlbzz.gftInnfrClbss(fnv, id);
                if (fifld != null) {
                    rfturn difdkInnfrClbss(fnv, dtx, vsft, fxp, lod);
                }
            }

            // If not b vbribblf rfffrfndf, dibgnosf frror if nbmf is
            // tibt of b mftiod.

            if (fifld == null) {
                if ((fifld = dlbzz.findAnyMftiod(fnv, id)) != null) {
                    fnv.frror(wifrf, "invblid.fifld",
                              id, fifld.gftClbssDfdlbrbtion());
                } flsf {
                    fnv.frror(wifrf, "no.sudi.fifld", id, dlbzz);
                }
                rfturn vsft;
            }

            // At tiis point, wf ibvf idfntififd b vblid fifld.

            // Rfquirfd by JLS 6.6.1.  Fixfs 4094658.
            if (!FifldExprfssion.isTypfAddfssiblf(wifrf, fnv, rigit.typf, sourdfClbss)) {
                ClbssDfdlbrbtion ddfdl = sourdfClbss.gftClbssDfdlbrbtion();
                if (stbtidRff) {
                    fnv.frror(wifrf, "no.typf.bddfss",
                              id, rigit.typf.toString(), ddfdl);
                } flsf {
                    fnv.frror(wifrf, "dbnt.bddfss.mfmbfr.typf",
                              id, rigit.typf.toString(), ddfdl);
                }
            }

            typf = fifld.gftTypf();

            if (!sourdfClbss.dbnAddfss(fnv, fifld)) {
                fnv.frror(wifrf, "no.fifld.bddfss",
                          id, dlbzz, sourdfClbss.gftClbssDfdlbrbtion());
                rfturn vsft;
            }

            if (stbtidRff && !fifld.isStbtid()) {
                // 'Clbss.fifld' is not lfgbl wifn fifld is not stbtid;
                // sff JLS 15.13.1.  Tiis dbsf wbs pfrmittfd by jbvbd
                // prior to 1.2; stbtid rffs wfrf silfntly dibngfd to
                // bf dynbmid bddfss of tif form 'tiis.fifld'.
                fnv.frror(wifrf, "no.stbtid.fifld.bddfss", id, dlbzz);
                rfturn vsft;
            } flsf {
                // Rfwritf bddfss to usf bn bddfss mftiod if nfdfssbry.
                implfmfntbtion = implfmfntFifldAddfss(fnv, dtx, rigit, isLHS);
            }

            // Cifdk for invblid bddfss to protfdtfd fifld.
            if (fifld.isProtfdtfd()
                && !(rigit instbndfof SupfrExprfssion
                     // Extfnsion of JLS 6.6.2 for qublififd 'supfr'.
                     || (rigit instbndfof FifldExprfssion &&
                         ((FifldExprfssion)rigit).id == idSupfr))
                && !sourdfClbss.protfdtfdAddfss(fnv, fifld, rigit.typf)) {
                fnv.frror(wifrf, "invblid.protfdtfd.fifld.usf",
                          fifld.gftNbmf(), fifld.gftClbssDfdlbrbtion(),
                          rigit.typf);
                rfturn vsft;
            }

            if ((!fifld.isStbtid()) &&
                (rigit.op == THIS) && !vsft.tfstVbr(dtx.gftTiisNumbfr())) {
                fnv.frror(wifrf, "bddfss.inst.bfforf.supfr", id);
            }

            if (fifld.rfportDfprfdbtfd(fnv)) {
                fnv.frror(wifrf, "wbrn."+"fifld.is.dfprfdbtfd",
                          id, fifld.gftClbssDffinition());
            }

            // Wifn b pbdkbgf-privbtf dlbss dffinfs publid or protfdtfd
            // mfmbfrs, tiosf mfmbfrs mby somftimfs bf bddfssfd from
            // outsidf of tif pbdkbgf in publid subdlbssfs.  In tifsf
            // dbsfs, wf nffd to mbssbgf tif gftFifld to rfffr to
            // to bn bddfssiblf subdlbss rbtifr tibn tif pbdkbgf-privbtf
            // pbrfnt dlbss.  Pbrt of fix for 4135692.

            // Find out if tif dlbss wiidi dontbins tiis fifld
            // rfffrfndf ibs bddfss to tif dlbss wiidi dfdlbrfs tif
            // publid or protfdtfd fifld.
            if (sourdfClbss == dtxClbss) {
                ClbssDffinition dfdlbrfr = fifld.gftClbssDffinition();
                if (dfdlbrfr.isPbdkbgfPrivbtf() &&
                    !dfdlbrfr.gftNbmf().gftQublififr()
                    .fqubls(sourdfClbss.gftNbmf().gftQublififr())) {

                    //Systfm.out.println("Tif bddfss of mfmbfr " +
                    //             fifld + " dfdlbrfd in dlbss " +
                    //             dfdlbrfr +
                    //             " is not bllowfd by tif VM from dlbss  " +
                    //             dtxClbss +
                    //             ".  Rfplbding witi bn bddfss of dlbss " +
                    //             dlbzz);

                    // Wf dbnnot mbkf tiis bddfss bt tif VM lfvfl.
                    // Construdt b mfmbfr wiidi will stbnd for tiis
                    // fifld in dtxClbss bnd sft `fifld' to rfffr to it.
                    fifld =
                        MfmbfrDffinition.mbkfProxyMfmbfr(fifld, dlbzz, fnv);
                }
            }

            sourdfClbss.bddDfpfndfndy(fifld.gftClbssDfdlbrbtion());

        } dbtdi (ClbssNotFound f) {
            fnv.frror(wifrf, "dlbss.not.found", f.nbmf, dtx.fifld);

        } dbtdi (AmbiguousMfmbfr f) {
            fnv.frror(wifrf, "bmbig.fifld",
                      id, f.fifld1.gftClbssDfdlbrbtion(), f.fifld2.gftClbssDfdlbrbtion());
        }
        rfturn vsft;
    }

    /**
     * Rfturn b <dodf>FifldUpdbtfr</dodf> objfdt to bf usfd in updbting tif
     * vbluf of tif lodbtion dfnotfd by <dodf>tiis</dodf>, wiidi must bf bn
     * fxprfssion suitbblf for tif lfft-ibnd sidf of bn bssignmfnt.
     * Tiis is usfd for implfmfnting bssignmfnts to privbtf fiflds for wiidi
     * bn bddfss mftiod is rfquirfd.  Rfturns null if no bddfss mftiod is
     * nffdfd, in wiidi dbsf tif bssignmfnt is ibndlfd in tif usubl wby, by
     * dirfdt bddfss.  Only simplf bssignmfnt fxprfssions brf ibndlfd ifrf
     * Assignmfnt opfrbtors bnd prf/post indrfmfnt/dfdrfmfnt opfrbtors brf
     * brf ibndlfd by 'gftUpdbtfr' bflow.
     * <p>
     * Must bf dbllfd bftfr 'difdkVbluf', flsf 'rigit' will bf invblid.
     */


    publid FifldUpdbtfr gftAssignfr(Environmfnt fnv, Contfxt dtx) {
        if (fifld == null) {
            // Fifld dbn lfgitimbtfly bf null if tif fifld nbmf wbs
            // undffinfd, in wiidi dbsf bn frror wbs rfportfd, but
            // no vbluf for 'fifld' is bvbilbblf.
            //   tirow nfw CompilfrError("gftAssignfr");
            rfturn null;
        }
        ClbssDffinition bbbsf = bddfssBbsf(fnv, dtx);
        if (bbbsf != null) {
            MfmbfrDffinition sfttfr = bbbsf.gftUpdbtfMfmbfr(fnv, dtx, fifld, isQublSupfr());
            // It mby not bf nfdfssbry to dopy 'rigit' ifrf.
            Exprfssion bbsf = (rigit == null) ? null : rigit.dopyInlinf(dtx);
            // Crfbtfd 'FifldUpdbtfr' ibs no gfttfr mftiod.
            rfturn nfw FifldUpdbtfr(wifrf, fifld, bbsf, null, sfttfr);
        }
        rfturn null;
    }

    /**
     * Rfturn b <dodf>FifldUpdbtfr</dodf> objfdt to bf usfd in updbting tif
     * vbluf of tif lodbtion dfnotfd by <dodf>tiis</dodf>, wiidi must bf bn
     * fxprfssion suitbblf for tif lfft-ibnd sidf of bn bssignmfnt.  Tiis is
     * usfd for implfmfnting tif bssignmfnt opfrbtors bnd tif indrfmfnt bnd
     * dfdrfmfnt opfrbtors on privbtf fiflds tibt brf bddfssfd from bnotifr
     * dlbss, f.g, uplfvfl from bn innfr dlbss. Rfturns null if no bddfss
     * mftiod is nffdfd.
     * <p>
     * Must bf dbllfd bftfr 'difdkVbluf', flsf 'rigit' will bf invblid.
     */

    publid FifldUpdbtfr gftUpdbtfr(Environmfnt fnv, Contfxt dtx) {
        if (fifld == null) {
            // Fifld dbn lfgitimbtfly bf null if tif fifld nbmf wbs
            // undffinfd, in wiidi dbsf bn frror wbs rfportfd, but
            // no vbluf for 'fifld' is bvbilbblf.
            //   tirow nfw CompilfrError("gftUpdbtfr");
            rfturn null;
        }
        ClbssDffinition bbbsf = bddfssBbsf(fnv, dtx);
        if (bbbsf != null) {
            MfmbfrDffinition gfttfr = bbbsf.gftAddfssMfmbfr(fnv, dtx, fifld, isQublSupfr());
            MfmbfrDffinition sfttfr = bbbsf.gftUpdbtfMfmbfr(fnv, dtx, fifld, isQublSupfr());
            // It mby not bf nfdfssbry to dopy 'rigit' ifrf.
            Exprfssion bbsf = (rigit == null) ? null : rigit.dopyInlinf(dtx);
            rfturn nfw FifldUpdbtfr(wifrf, fifld, bbsf, gfttfr, sfttfr);
        }
        rfturn null;
    }

    /**
     * Tiis fifld fxprfssion is bn innfr dlbss rfffrfndf.
     * Finisi difdking it.
     */
    privbtf Vsft difdkInnfrClbss(Environmfnt fnv, Contfxt dtx,
                                 Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp,
                                 UnbryExprfssion lod) {
        ClbssDffinition innfr = fifld.gftInnfrClbss();
        typf = innfr.gftTypf();

        if (!innfr.isTopLfvfl()) {
            fnv.frror(wifrf, "innfr.stbtid.rff", innfr.gftNbmf());
        }

        Exprfssion tf = nfw TypfExprfssion(wifrf, typf);

        // difdk bddfss
        ClbssDffinition dtxClbss = dtx.fifld.gftClbssDffinition();
        try {
            if (!dtxClbss.dbnAddfss(fnv, fifld)) {
                ClbssDffinition dlbzz = fnv.gftClbssDffinition(rigit.typf);
                //fnv.frror(wifrf, "no.typf.bddfss",
                //          id, dlbzz, dtx.fifld.gftClbssDfdlbrbtion());
                fnv.frror(wifrf, "no.typf.bddfss",
                          id, dlbzz, dtxClbss.gftClbssDfdlbrbtion());
                rfturn vsft;
            }

            if (fifld.isProtfdtfd()
                && !(rigit instbndfof SupfrExprfssion
                     // Extfnsion of JLS 6.6.2 for qublififd 'supfr'.
                     || (rigit instbndfof FifldExprfssion &&
                         ((FifldExprfssion)rigit).id == idSupfr))
                && !dtxClbss.protfdtfdAddfss(fnv, fifld, rigit.typf)){
                fnv.frror(wifrf, "invblid.protfdtfd.fifld.usf",
                          fifld.gftNbmf(), fifld.gftClbssDfdlbrbtion(),
                          rigit.typf);
                rfturn vsft;
            }

            innfr.notfUsfdBy(dtxClbss, wifrf, fnv);

        } dbtdi (ClbssNotFound f) {
            fnv.frror(wifrf, "dlbss.not.found", f.nbmf, dtx.fifld);
        }

        dtxClbss.bddDfpfndfndy(fifld.gftClbssDfdlbrbtion());
        if (lod == null)
            // Complbin bbout b frff-flobting typf nbmf.
            rfturn tf.difdkVbluf(fnv, dtx, vsft, fxp);
        lod.rigit = tf;
        rfturn vsft;
    }

    /**
     * Cifdk tif fxprfssion if it bppfbrs on tif LHS of bn bssignmfnt
     */
    publid Vsft difdkLHS(Environmfnt fnv, Contfxt dtx,
                         Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        boolfbn ibdFifld = (fifld != null);

        //difdkVbluf(fnv, dtx, vsft, fxp);
        difdkCommon(fnv, dtx, vsft, fxp, null, truf);

        // If 'implfmfntbtion' is sft to b non-null vbluf, tifn tif
        // fifld fxprfssion dofs not dfnotf bn bssignbblf lodbtion,
        // f.g., tif 'lfngti' fifld of bn brrby.
        if (implfmfntbtion != null) {
            // Tiis just rfports bn frror bnd rfdovfrs.
            rfturn supfr.difdkLHS(fnv, dtx, vsft, fxp);
        }

        if (fifld != null && fifld.isFinbl() && !ibdFifld) {
            if (fifld.isBlbnkFinbl()) {
                if (fifld.isStbtid()) {
                    if (rigit != null) {
                        fnv.frror(wifrf, "qublififd.stbtid.finbl.bssign");
                    }
                    // Continuf witi difdking bnyiow.
                    // In fbdt, it would bf fbsy to bllow tiis dbsf.
                } flsf {
                    if ((rigit != null) && (rigit.op != THIS)) {
                        fnv.frror(wifrf, "bbd.qublififd.finbl.bssign", fifld.gftNbmf());
                        // Tif bdtubl instbndf dould bf bnywifrf, so don't
                        // dontinuf witi difdking tif dffinitf bssignmfnt stbtus.
                        rfturn vsft;
                    }
                }
                vsft = difdkFinblAssign(fnv, dtx, vsft, wifrf, fifld);
            } flsf {
                fnv.frror(wifrf, "bssign.to.finbl", id);
            }
        }
        rfturn vsft;
    }

    /**
     * Cifdk tif fxprfssion if it bppfbrs on tif LHS of bn op= fxprfssion
     */
    publid Vsft difdkAssignOp(Environmfnt fnv, Contfxt dtx,
                              Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp, Exprfssion outsidf) {

        //difdkVbluf(fnv, dtx, vsft, fxp);
        difdkCommon(fnv, dtx, vsft, fxp, null, truf);

        // If 'implfmfntbtion' is sft to b non-null vbluf, tifn tif
        // fifld fxprfssion dofs not dfnotf bn bssignbblf lodbtion,
        // f.g., tif 'lfngti' fifld of bn brrby.
        if (implfmfntbtion != null) {
            rfturn supfr.difdkLHS(fnv, dtx, vsft, fxp);
        }
        if (fifld != null && fifld.isFinbl()) {
            fnv.frror(wifrf, "bssign.to.finbl", id);
        }
        rfturn vsft;
    }

    /**
     * Tifrf is b simplf bssignmfnt bfing mbdf to tif givfn finbl fifld.
     * Tif fifld wbs nbmfd fitifr by b simplf nbmf or by bn blmost-simplf
     * fxprfssion of tif form "tiis.v".
     * Cifdk if tiis is b lfgbl bssignmfnt.
     * <p>
     * Blbnk finbl vbribblfs dbn bf sft in initiblizfrs or donstrudtor
     * bodifs.  In bll dbsfs tifrf must bf dffinitf singlf bssignmfnt.
     * (All instbndf bnd instbndf vbribblf initiblizfrs bnd fbdi
     * donstrudtor body brf trfbtfd bs if dondbtfnbtfd for tif purposfs
     * of tiis difdk.  Assignmfnt to "tiis.x" is trfbtfd bs b dffinitf
     * bssignmfnt to tif simplf nbmf "x" wiidi nbmfs tif instbndf vbribblf.)
     */

    publid stbtid Vsft difdkFinblAssign(Environmfnt fnv, Contfxt dtx,
                                        Vsft vsft, long wifrf,
                                        MfmbfrDffinition fifld) {
        if (fifld.isBlbnkFinbl()
            && fifld.gftClbssDffinition() == dtx.fifld.gftClbssDffinition()) {
            int numbfr = dtx.gftFifldNumbfr(fifld);
            if (numbfr >= 0 && vsft.tfstVbrUnbssignfd(numbfr)) {
                // dffinitf singlf bssignmfnt
                vsft = vsft.bddVbr(numbfr);
            } flsf {
                // it is b blbnk finbl in tiis dlbss, but not bssignbblf
                Idfntififr id = fifld.gftNbmf();
                fnv.frror(wifrf, "bssign.to.blbnk.finbl", id);
            }
        } flsf {
            // givf tif gfnfrid frror mfssbgf
            Idfntififr id = fifld.gftNbmf();
            fnv.frror(wifrf, "bssign.to.finbl", id);
        }
        rfturn vsft;
    }

    privbtf stbtid MfmbfrDffinition gftClbssLitfrblCbdif(Environmfnt fnv,
                                                         Contfxt dtx,
                                                         String dlbssNbmf,
                                                         ClbssDffinition d) {
        // Givfn b dlbss nbmf, look for b stbtid fifld to dbdif it.
        //      dlbssNbmf       lnbmf
        //      pkg.Foo         dlbss$pkg$Foo
        //      [Lpkg.Foo;      brrby$Lpkg$Foo
        //      [[Lpkg.Foo;     brrby$$Lpkg$Foo
        //      [I              brrby$I
        //      [[I             brrby$$I
        String lnbmf;
        if (!dlbssNbmf.stbrtsWiti(SIG_ARRAY)) {
            lnbmf = prffixClbss + dlbssNbmf.rfplbdf('.', '$');
        } flsf {
            lnbmf = prffixArrby + dlbssNbmf.substring(1);
            lnbmf = lnbmf.rfplbdf(SIGC_ARRAY, '$'); // [[[I => brrby$$$I
            if (dlbssNbmf.fndsWiti(SIG_ENDCLASS)) {
                // [Lpkg.Foo; => brrby$Lpkg$Foo
                lnbmf = lnbmf.substring(0, lnbmf.lfngti() - 1);
                lnbmf = lnbmf.rfplbdf('.', '$');
            }
            // flsf [I => brrby$I or somf sudi; lnbmf is blrfbdy OK
        }
        Idfntififr fnbmf = Idfntififr.lookup(lnbmf);

        // Tif dlbss to put tif dbdif in is now givfn bs bn brgumfnt.
        //
        // ClbssDffinition d = dtx.fifld.gftClbssDffinition();
        // wiilf (d.isInnfrClbss()) {
        //     d = d.gftOutfrClbss();

        MfmbfrDffinition dfld;
        try {
            dfld = d.gftVbribblf(fnv, fnbmf, d);
        } dbtdi (ClbssNotFound ff) {
            rfturn null;
        } dbtdi (AmbiguousMfmbfr ff) {
            rfturn null;
        }

        // Ignorf inifritfd fifld.  Ebdi top-lfvfl dlbss
        // dontbining b givfn dlbss litfrbl must ibvf its own dopy,
        // boti for rfbsons of binbry dompbtibility bnd to prfvfnt
        // bddfss violbtions siould tif supfrdlbss bf in bnotifr
        // pbdkbgf.  Pbrt of fix 4106051.
        if (dfld != null && dfld.gftClbssDffinition() == d) {
            rfturn dfld;
        }

        // Sindf fbdi dlbss now ibs its own dopy, wf migit bs wfll
        // tigitfn up tif bddfss to privbtf (prfviously dffbult).
        // Pbrt of fix for 4106051.
        // ** Tfmporbrily rftrbdt tiis, bs it tidklfs 4098316.
        rfturn fnv.mbkfMfmbfrDffinition(fnv, d.gftWifrf(),
                                        d, null,
                                        M_STATIC | M_SYNTHETIC, // M_PRIVATE,
                                        Typf.tClbssDfsd, fnbmf,
                                        null, null, null);
    }

    privbtf Exprfssion mbkfClbssLitfrblCbdifRff(Environmfnt fnv, Contfxt dtx,
                                                MfmbfrDffinition lookup,
                                                MfmbfrDffinition dfld,
                                                String dlbssNbmf) {
        Exprfssion ddls = nfw TypfExprfssion(wifrf,
                                             dfld.gftClbssDffinition()
                                             .gftTypf());
        Exprfssion dbdif = nfw FifldExprfssion(wifrf, ddls, dfld);
        Exprfssion dbdifOK =
            nfw NotEqublExprfssion(wifrf, dbdif.dopyInlinf(dtx),
                                   nfw NullExprfssion(wifrf));
        Exprfssion ldls =
            nfw TypfExprfssion(wifrf, lookup.gftClbssDffinition() .gftTypf());
        Exprfssion nbmf = nfw StringExprfssion(wifrf, dlbssNbmf);
        Exprfssion nbmfbrg[] = { nbmf };
        Exprfssion sftCbdif = nfw MftiodExprfssion(wifrf, ldls,
                                                   lookup, nbmfbrg);
        sftCbdif = nfw AssignExprfssion(wifrf, dbdif.dopyInlinf(dtx),
                                        sftCbdif);
        rfturn nfw ConditionblExprfssion(wifrf, dbdifOK, dbdif, sftCbdif);
    }

    privbtf Exprfssion mbkfClbssLitfrblInlinfRff(Environmfnt fnv, Contfxt dtx,
                                                 MfmbfrDffinition lookup,
                                                 String dlbssNbmf) {
        Exprfssion ldls =
            nfw TypfExprfssion(wifrf, lookup.gftClbssDffinition().gftTypf());
        Exprfssion nbmf = nfw StringExprfssion(wifrf, dlbssNbmf);
        Exprfssion nbmfbrg[] = { nbmf };
        Exprfssion gftClbss = nfw MftiodExprfssion(wifrf, ldls,
                                                   lookup, nbmfbrg);
        rfturn gftClbss;
    }


    /**
     * Cifdk if donstbnt:  Will it inlinf bwby?
     */
    publid boolfbn isConstbnt() {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion.isConstbnt();
        if ((fifld != null)
            && (rigit == null || rigit instbndfof TypfExprfssion
                || (rigit.op == THIS && rigit.wifrf == wifrf))) {
            rfturn fifld.isConstbnt();
        }
        rfturn fblsf;
    }

    /**
     * Inlinf
     */
    publid Exprfssion inlinf(Environmfnt fnv, Contfxt dtx) {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion.inlinf(fnv, dtx);
        // A fifld fxprfssion mby ibvf tif sidf ffffdt of dbusing
        // b NullPointfrExdfption, so fvblubtf it fvfn tiougi
        // tif vbluf is not nffdfd.  Similbrly, stbtid fifld dfrfffrfndfs
        // mby dbusf dlbss initiblizbtion, so tify mustn't bf omittfd
        // fitifr.
        //
        // Howfvfr, NullPointfrExdfption dbn't ibppfn bnd initiblizbtion must
        // blrfbdy ibvf oddurrfd if you brf dotting into 'tiis'.  So
        // bllow fiflds of 'tiis' to bf fliminbtfd bs b spfdibl dbsf.
        Exprfssion f = inlinfVbluf(fnv, dtx);
        if (f instbndfof FifldExprfssion) {
            FifldExprfssion ff = (FifldExprfssion) f;
            if ((ff.rigit != null) && (ff.rigit.op==THIS))
                rfturn null;
            // It siould bf possiblf to split tiis into two difdks: onf using
            // isNonNull() for non-stbtids bnd b difffrfnt difdk for stbtids.
            // Tibt would mbkf tif inlining sligitly lfss donsfrvbtivf by
            // bllowing, for fxbmplf, dotting into String donstbnts.
            }
        rfturn f;
    }
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion.inlinfVbluf(fnv, dtx);
        try {
            if (fifld == null) {
                rfturn tiis;
            }

            if (fifld.isFinbl()) {
                Exprfssion f = (Exprfssion)fifld.gftVbluf(fnv);
                if ((f != null) && f.isConstbnt()) {
                    // rfmovf bogus linf-numbfr info
                    f = f.dopyInlinf(dtx);
                    f.wifrf = wifrf;
                    rfturn nfw CommbExprfssion(wifrf, rigit, f).inlinfVbluf(fnv, dtx);
                }
            }

            if (rigit != null) {
                if (fifld.isStbtid()) {
                    Exprfssion f = rigit.inlinf(fnv, dtx);
                    rigit = null;
                    if (f != null) {
                        rfturn nfw CommbExprfssion(wifrf, f, tiis);
                    }
                } flsf {
                    rigit = rigit.inlinfVbluf(fnv, dtx);
                }
            }
            rfturn tiis;

        } dbtdi (ClbssNotFound f) {
            tirow nfw CompilfrError(f);
        }
    }
    publid Exprfssion inlinfLHS(Environmfnt fnv, Contfxt dtx) {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion.inlinfLHS(fnv, dtx);
        if (rigit != null) {
            if (fifld.isStbtid()) {
                Exprfssion f = rigit.inlinf(fnv, dtx);
                rigit = null;
                if (f != null) {
                    rfturn nfw CommbExprfssion(wifrf, f, tiis);
                }
            } flsf {
                rigit = rigit.inlinfVbluf(fnv, dtx);
            }
        }
        rfturn tiis;
    }

    publid Exprfssion dopyInlinf(Contfxt dtx) {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion.dopyInlinf(dtx);
        rfturn supfr.dopyInlinf(dtx);
    }

    /**
     * Tif dost of inlining tiis fxprfssion
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        if (implfmfntbtion != null)
            rfturn implfmfntbtion.dostInlinf(tirfsi, fnv, dtx);
        if (dtx == null) {
            rfturn 3 + ((rigit == null) ? 0
                                        : rigit.dostInlinf(tirfsi, fnv, dtx));
        }
        // dtxClbss is tif durrfnt dlbss trying to inlinf tiis mftiod
        ClbssDffinition dtxClbss = dtx.fifld.gftClbssDffinition();
        try {
            // Wf only bllow tif inlining if tif durrfnt dlbss dbn bddfss
            // tif fifld, tif fifld's dlbss, bnd rigit's dfdlbrfd typf.
            if (    dtxClbss.pfrmitInlinfdAddfss(fnv, fifld.gftClbssDfdlbrbtion())
                 && dtxClbss.pfrmitInlinfdAddfss(fnv, fifld)) {
                if (rigit == null) {
                    rfturn 3;
                } flsf {
                    ClbssDfdlbrbtion rt = fnv.gftClbssDfdlbrbtion(rigit.typf);
                    if (dtxClbss.pfrmitInlinfdAddfss(fnv, rt)) {
                        rfturn 3 + rigit.dostInlinf(tirfsi, fnv, dtx);
                    }
                }
            }
        } dbtdi (ClbssNotFound f) {
        }
        rfturn tirfsi;
    }

    /**
     * Codf
     */
    int dodfLVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        if (implfmfntbtion != null)
            tirow nfw CompilfrError("dodfLVbluf");
        if (fifld.isStbtid()) {
            if (rigit != null) {
                rigit.dodf(fnv, dtx, bsm);
                rfturn 1;
            }
            rfturn 0;
        }
        rigit.dodfVbluf(fnv, dtx, bsm);
        rfturn 1;
    }
    void dodfLobd(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        if (fifld == null) {
            tirow nfw CompilfrError("siould not bf null");
        }
        if (fifld.isStbtid()) {
            bsm.bdd(wifrf, opd_gftstbtid, fifld);
        } flsf {
            bsm.bdd(wifrf, opd_gftfifld, fifld);
        }
    }
    void dodfStorf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        if (fifld.isStbtid()) {
            bsm.bdd(wifrf, opd_putstbtid, fifld);
        } flsf {
            bsm.bdd(wifrf, opd_putfifld, fifld);
        }
    }

    publid void dodfVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        dodfLVbluf(fnv, dtx, bsm);
        dodfLobd(fnv, dtx, bsm);
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out) {
        out.print("(");
        if (rigit != null) {
            rigit.print(out);
        } flsf {
            out.print("<fmpty>");
        }
        out.print("." + id + ")");
        if (implfmfntbtion != null) {
            out.print("/IMPL=");
            implfmfntbtion.print(out);
        }
    }
}
