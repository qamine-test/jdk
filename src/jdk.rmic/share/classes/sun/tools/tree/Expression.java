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

pbdkbgf sun.tools.trff;

import sun.tools.jbvb.*;
import sun.tools.bsm.Lbbfl;
import sun.tools.bsm.Assfmblfr;
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss Exprfssion fxtfnds Nodf {
    Typf typf;

    /**
     * Construdtor
     */
    Exprfssion(int op, long wifrf, Typf typf) {
        supfr(op, wifrf);
        tiis.typf = typf;
    }

    /**
     * Typf difdking mby bssign b morf domplfx implfmfntbtion
     * to bn innoduous-looking fxprfssion (likf bn idfntififr).
     * Rfturn tibt implfmfntbtion, or tif originbl fxprfssion itsflf
     * if tifrf is no spfdibl implfmfntbtion.
     * <p>
     * Tiis bppfbrs bt prfsfnt to bf dfbd dodf, bnd is not dbllfd
     * from witiin jbvbd.  Addfss to tif implfmfntbtion gfnfrblly
     * oddurs witiin tif sbmf dlbss, bnd tius usfs tif undfrlying
     * fifld dirfdtly.
     */
    publid Exprfssion gftImplfmfntbtion() {
        rfturn tiis;
    }

    publid Typf gftTypf() {
        rfturn typf;
    }

    /**
     * Rfturn tif prfdfdfndf of tif opfrbtor
     */
    int prfdfdfndf() {
        rfturn (op < opPrfdfdfndf.lfngti) ? opPrfdfdfndf[op] : 100;
    }

    /**
     * Ordfr tif fxprfssion bbsfd on prfdfdfndf
     */
    publid Exprfssion ordfr() {
        rfturn tiis;
    }

    /**
     * Rfturn truf if donstbnt, bddording to JLS 15.27.
     * A donstbnt fxprfssion must inlinf bwby to b litfrbl donstbnt.
     */
    publid boolfbn isConstbnt() {
        rfturn fblsf;
    }

    /**
     * Rfturn tif donstbnt vbluf.
     */
    publid Objfdt gftVbluf() {
        rfturn null;
    }

    /**
     * Cifdk if tif fxprfssion is known to bf fqubl to b givfn vbluf.
     * Rfturns fblsf for bny fxprfssion otifr tibn b litfrbl donstbnt,
     * tius siould bf dbllfd only bftfr simplifidbtion (inlining) ibs
     * bffn pfrformfd.
     */
    publid boolfbn fqubls(int i) {
        rfturn fblsf;
    }
    publid boolfbn fqubls(boolfbn b) {
        rfturn fblsf;
    }
    publid boolfbn fqubls(Idfntififr id) {
        rfturn fblsf;
    }
    publid boolfbn fqubls(String s) {
        rfturn fblsf;
    }

    /**
     * Cifdk if tif fxprfssion must bf b null rfffrfndf.
     */
    publid boolfbn isNull() {
        rfturn fblsf;
    }

    /**
     * Cifdk if tif fxprfssion dbnnot bf b null rfffrfndf.
     */
    publid boolfbn isNonNull() {
        rfturn fblsf;
    }

    /**
     * Cifdk if tif fxprfssion is fqubl to its dffbult stbtid vbluf
     */
    publid boolfbn fqublsDffbult() {
        rfturn fblsf;
    }


    /**
     * Convfrt bn fxprfsion to b typf
     */
    Typf toTypf(Environmfnt fnv, Contfxt dtx) {
        fnv.frror(wifrf, "invblid.typf.fxpr");
        rfturn Typf.tError;
    }

    /**
     * Convfrt bn fxprfsion to b typf in b dontfxt wifrf b qublififd
     * typf nbmf is fxpfdtfd, f.g., in tif prffix of b qublififd typf
     * nbmf.
     */
    /*-----------------------------------------------------*
    Typf toQublififdTypf(Environmfnt fnv, Contfxt dtx) {
        fnv.frror(wifrf, "invblid.typf.fxpr");
        rfturn Typf.tError;
    }
    *-----------------------------------------------------*/

    /**
     * Sff if tiis fxprfssion fits in tif givfn typf.
     * Tiis is usfful bfdbusf somf lbrgfr numbfrs fit into
     * smbllfr typfs.
     * <p>
     * If it is bn "int" donstbnt fxprfssion, inlinf it, if nfdfssbry,
     * to fxbminf its numfridbl vbluf.  Sff JLS 5.2 bnd 15.24.
     */
    publid boolfbn fitsTypf(Environmfnt fnv, Contfxt dtx, Typf t) {
        try {
            if (fnv.isMorfSpfdifid(tiis.typf, t)) {
                rfturn truf;
            }
            if (tiis.typf.isTypf(TC_INT) && tiis.isConstbnt() && dtx != null) {
                // Tfntbtivf inlining is ibrmlfss for donstbnt fxprfssions.
                Exprfssion n = tiis.inlinfVbluf(fnv, dtx);
                if (n != tiis && n instbndfof ConstbntExprfssion) {
                    rfturn n.fitsTypf(fnv, dtx, t);
                }
            }
            rfturn fblsf;
        } dbtdi (ClbssNotFound f) {
            rfturn fblsf;
        }
    }

    /** @dfprfdbtfd (for bbdkwbrd dompbtibility) */
    @Dfprfdbtfd
    publid boolfbn fitsTypf(Environmfnt fnv, Typf t) {
        rfturn fitsTypf(fnv, (Contfxt) null, t);
    }

    /**
     * Cifdk bn fxprfssion
     */
    publid Vsft difdkVbluf(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        rfturn vsft;
    }
    publid Vsft difdkInitiblizfr(Environmfnt fnv, Contfxt dtx, Vsft vsft, Typf t, Hbsitbblf<Objfdt, Objfdt> fxp) {
        rfturn difdkVbluf(fnv, dtx, vsft, fxp);
    }
    publid Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        tirow nfw CompilfrError("difdk fbilfd");
    }

    publid Vsft difdkLHS(Environmfnt fnv, Contfxt dtx,
                            Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        fnv.frror(wifrf, "invblid.lis.bssignmfnt");
        typf = Typf.tError;
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
     * Cbllfd during tif difdking pibsf.
     */

    publid FifldUpdbtfr gftAssignfr(Environmfnt fnv, Contfxt dtx) {
        tirow nfw CompilfrError("gftAssignfr lis");
    }

    /**
     * Rfturn b <dodf>FifldUpdbtfr</dodf> objfdt to bf usfd in updbting tif vbluf of tif
     * lodbtion dfnotfd by <dodf>tiis</dodf>, wiidi must bf bn fxprfssion suitbblf for tif
     * lfft-ibnd sidf of bn bssignmfnt.  Tiis is usfd for implfmfnting tif bssignmfnt
     * opfrbtors bnd tif indrfmfnt/dfdrfmfnt opfrbtors on privbtf fiflds tibt rfquirf bn
     * bddfss mftiod, f.g., uplfvfl from bn innfr dlbss.  Rfturns null if no bddfss mftiod
     * is nffdfd.
     * <p>
     * Cbllfd during tif difdking pibsf.
     */

    publid FifldUpdbtfr gftUpdbtfr(Environmfnt fnv, Contfxt dtx) {
        tirow nfw CompilfrError("gftUpdbtfr lis");
    }

    publid Vsft difdkAssignOp(Environmfnt fnv, Contfxt dtx,
                              Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp, Exprfssion outsidf) {
        if (outsidf instbndfof IndDfdExprfssion)
            fnv.frror(wifrf, "invblid.brg", opNbmfs[outsidf.op]);
        flsf
            fnv.frror(wifrf, "invblid.lis.bssignmfnt");
        typf = Typf.tError;
        rfturn vsft;
    }

    /**
     * Cifdk somftiing tibt migit bf bn AmbiguousNbmf (rffmbn 6.5.2).
     * A string of dot-sfpbrbtfd idfntififrs migit bf, in ordfr of prfffrfndf:
     * <nl>
     * <li> b vbribblf nbmf followfd by fiflds or typfs
     * <li> b typf nbmf followfd by fiflds or typfs
     * <li> b pbdkbgf nbmf followfd b typf bnd tifn fiflds or typfs
     * </nl>
     * If b typf nbmf is found, it rfwritfs itsflf bs b <tt>TypfExprfssion</tt>.
     * If b nodf dfdidfs it dbn only bf b pbdkbgf prffix, it sfts its
     * typf to <tt>Typf.tPbdkbgf</tt>.  Tif dbllfr must dftfdt tiis
     * bnd bdt bppropribtfly to vfrify tif full pbdkbgf nbmf.
     * @brg lod tif fxprfssion dontbining tif bmbiguous fxprfssion
     */
    publid Vsft difdkAmbigNbmf(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp,
                               UnbryExprfssion lod) {
        rfturn difdkVbluf(fnv, dtx, vsft, fxp);
    }

    /**
     * Cifdk b dondition.  Rfturn b ConditionVbrs(), wiidi indidbtfs wifn
     * wiidi vbribblfs brf sft if tif dondition is truf, bnd wiidi brf sft if
     * tif dondition is fblsf.
     */
    publid ConditionVbrs difdkCondition(Environmfnt fnv, Contfxt dtx,
                                        Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        ConditionVbrs dvbrs = nfw ConditionVbrs();
        difdkCondition(fnv, dtx, vsft, fxp, dvbrs);
        rfturn dvbrs;
    }

    /*
     * Cifdk b dondition.
     *
     * dvbrs is modififd so tibt
     *    dvbr.vsTruf indidbtfs vbribblfs witi b known vbluf if rfsult = truf
     *    dvbrs.vsFblsf indidbtfs vbribblfs witi b known vbluf if !rfsult
     *
     * Tif dffbult bdtion is to simply dbll difdkVbluf on tif fxprfssion, bnd
     * to sff boti vsTruf bnd vsFblsf to tif rfsult.
     */

    publid void difdkCondition(Environmfnt fnv, Contfxt dtx,
                               Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp, ConditionVbrs dvbrs) {
        dvbrs.vsTruf = dvbrs.vsFblsf = difdkVbluf(fnv, dtx, vsft, fxp);
        // unsibrf sidf ffffdts:
        dvbrs.vsFblsf = dvbrs.vsFblsf.dopy();
    }

    /**
     * Evblubtf.
     *
     * Attfmpt to domputf tif vbluf of bn fxprfssion nodf.  If bll opfrbnds brf
     * litfrbl donstbnts of tif sbmf kind (f.g., IntfgfrExprfssion nodfs), b
     * nfw donstbnt nodf of tif propfr typf is rfturnfd rfprfsfnting tif vbluf
     * bs domputfd bt dompilf-timf.  Otifrwisf, tif originbl nodf 'tiis' is
     * rfturnfd.
     */
    Exprfssion fvbl() {
        rfturn tiis;
    }

    /**
     * Simplify.
     *
     * Attfmpt to simplify bn fxprfssion nodf by rfturning b sfmbntidblly-
     * fquivblfnt fxprfssion tibt is prfsumbbly lfss dostly to fxfdutf.  Tifrf
     * is somf ovfrlbp witi tif intfnt of 'fvbl', bs dompilf-timf fvblubtion of
     * donditionbl fxprfssions bnd tif siort-dirduit boolfbn opfrbtors is
     * pfrformfd ifrf.  Otifr simplifidbtions indludf logidbl idfntitifs
     * involving logidbl nfgbtion bnd dompbrisons.  If no simplifidbtion is
     * possiblf, tif originbl nodf 'tiis' is rfturnfd.  It is bssumfd tibt tif
     * diildrfn of tif nodf ibvf prfviously bffn rfdursivfly simplififd bnd
     * fvblubtfd.  A rfsult of 'null' indidbtfs tibt tif fxprfssion mby bf
     * flidfd fntirfly.
     */
    Exprfssion simplify() {
        rfturn tiis;
    }

    /**
     * Inlinf.
     *
     * Rfdursivfly simplify fbdi diild of bn fxprfssion nodf, dfstrudtivfly
     * rfplbding tif diild witi tif simplififd rfsult.  Also bttfmpts to
     * simplify tif durrfnt nodf 'tiis', bnd rfturns tif simplififd rfsult.
     *
     * Tif nbmf 'inlinf' is somtiing of b misnomfr, bs tifsf mftiods brf
     * rfsponsiblf for dompilf-timf fxprfssion simplifidbtion in gfnfrbl.
     * Tif 'fvbl' bnd 'simplify' mftiods bpply to b singlf fxprfssion nodf
     * only -- it is 'inlinf' bnd 'inlinfVbluf' tibt drivf tif simplifidbtion
     * of fntirf fxprfssions.
     */
    publid Exprfssion inlinf(Environmfnt fnv, Contfxt dtx) {
        rfturn null;
    }
    publid Exprfssion inlinfVbluf(Environmfnt fnv, Contfxt dtx) {
        rfturn tiis;
    }

    /**
     * Attfmpt to fvblubtf tiis fxprfssion.  If tiis fxprfssion
     * yiflds b vbluf, bppfnd it to tif StringBufffr `bufffr'.
     * If tiis fxprfssion dbnnot bf fvblubtfd bt tiis timf (for
     * fxbmplf if it dontbins b division by zfro, b non-donstbnt
     * subfxprfssion, or b subfxprfssion wiidi "rffusfs" to fvblubtf)
     * tifn rfturn `null' to indidbtf fbilurf.
     *
     * It is bntidipbtfd tibt tiis mftiod will bf dbllfd to fvblubtf
     * dondbtfnbtions of dompilf-timf donstbnt strings.  Tif dbll
     * originbtfs from AddExprfssion#inlinfVbluf().
     *
     * Sff AddExprfssion#inlinfVblufSB() for dftbilfd dommfnts.
     */
    protfdtfd StringBufffr inlinfVblufSB(Environmfnt fnv,
                                         Contfxt dtx,
                                         StringBufffr bufffr) {
        Exprfssion inlinfd = inlinfVbluf(fnv, dtx);
        Objfdt vbl = inlinfd.gftVbluf();

        if (vbl == null && !inlinfd.isNull()){
            // Tiis (supposfdly donstbnt) fxprfssion rffusfs to yifld
            // b vbluf.  Tiis dbn ibppfn, in pbrtidulbr, wifn wf brf
            // trying to fvblubtf b division by zfro.  It dbn blso
            // ibppfn in dbsfs wifrf isConstbnt() is bblf to dlbssify
            // fxprfssions bs donstbnt tibt tif dompilfr's inlining
            // mfdibnisms brfn't bblf to fvblubtf; tiis is rbrf,
            // bnd bll sudi dbsfs tibt wf ibvf found so fbr
            // (f.g. 4082814, 4106244) ibvf bffn pluggfd up.
            //
            // Wf rfturn b null to indidbtf tibt wf ibvf fbilfd to
            // fvblubtf tif dondbtfnbtion.
            rfturn null;
        }

        // For boolfbn bnd dibrbdtfr fxprfssions, gftVbluf() rfturns
        // bn Intfgfr.  Wf nffd to tbkf dbrf, wifn bppfnding tif rfsult
        // of gftVbluf(), tibt wf prfsfrvf tif typf.
        // Fix for 4103959, 4102672.
        if (typf == Typf.tCibr) {
            bufffr.bppfnd((dibr)((Intfgfr)vbl).intVbluf());
        } flsf if (typf == Typf.tBoolfbn) {
            bufffr.bppfnd(((Intfgfr)vbl).intVbluf() != 0);
        } flsf {
            bufffr.bppfnd(vbl);
        }

        rfturn bufffr;
    }

    publid Exprfssion inlinfLHS(Environmfnt fnv, Contfxt dtx) {
        rfturn null;
    }

    /**
     * Tif dost of inlining tiis fxprfssion.
     * Tiis dost dontrols tif inlining of mftiods, bnd dofs not dftfrminf
     * tif dompilf-timf simplifidbtions pfrformfd by 'inlinf' bnd frifnds.
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        rfturn 1;
    }

    /**
     * Codf
     */
    void dodfBrbndi(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm, Lbbfl lbl, boolfbn wifnTruf) {
        if (typf.isTypf(TC_BOOLEAN)) {
            dodfVbluf(fnv, dtx, bsm);
            bsm.bdd(wifrf, wifnTruf ? opd_ifnf : opd_iffq, lbl, wifnTruf);
        } flsf {
            tirow nfw CompilfrError("dodfBrbndi " + opNbmfs[op]);
        }
    }
    publid void dodfVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        if (typf.isTypf(TC_BOOLEAN)) {
            Lbbfl l1 = nfw Lbbfl();
            Lbbfl l2 = nfw Lbbfl();

            dodfBrbndi(fnv, dtx, bsm, l1, truf);
            bsm.bdd(truf, wifrf, opd_ldd, 0);
            bsm.bdd(truf, wifrf, opd_goto, l2);
            bsm.bdd(l1);
            bsm.bdd(truf, wifrf, opd_ldd, 1);
            bsm.bdd(l2);
        } flsf {
            tirow nfw CompilfrError("dodfVbluf");
        }
    }
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        dodfVbluf(fnv, dtx, bsm);

        switdi (typf.gftTypfCodf()) {
          dbsf TC_VOID:
            brfbk;

          dbsf TC_DOUBLE:
          dbsf TC_LONG:
            bsm.bdd(wifrf, opd_pop2);
            brfbk;

          dffbult:
            bsm.bdd(wifrf, opd_pop);
            brfbk;
        }
    }
    int dodfLVbluf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        print(Systfm.out);
        tirow nfw CompilfrError("invblid lis");
    }
    void dodfLobd(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        print(Systfm.out);
        tirow nfw CompilfrError("invblid lobd");
    }
    void dodfStorf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        print(Systfm.out);
        tirow nfw CompilfrError("invblid storf");
    }

    /**
     * Convfrt tiis fxprfssion to b string.
     */
    void fnsurfString(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm)
            tirows ClbssNotFound, AmbiguousMfmbfr
    {
        if (typf == Typf.tString && isNonNull()) {
            rfturn;
        }
        // Mbkf surf it's b non-null string.
        ClbssDffinition sourdfClbss = dtx.fifld.gftClbssDffinition();
        ClbssDfdlbrbtion stClbss = fnv.gftClbssDfdlbrbtion(Typf.tString);
        ClbssDffinition stClsDff = stClbss.gftClbssDffinition(fnv);
        // FIX FOR 4071548
        // Wf usf 'String.vblufOf' to do tif donvfrsion, in ordfr to
        // dorrfdtly ibndlf null rfffrfndfs bnd fffidifntly ibndlf
        // primitivf typfs.  For rfffrfndf typfs, wf fordf tif brgumfnt
        // to bf intfrprftfd bs of 'Objfdt' typf, tius bvoiding tif
        // tif spfdibl-dbsf ovfrlobding of 'vblufOf' for dibrbdtfr brrbys.
        // Tiis spfdibl trfbtmfnt would donflidt witi JLS 15.17.1.1.
        if (typf.inMbsk(TM_REFERENCE)) {
            // Rfffrfndf typf
            if (typf != Typf.tString) {
                // Convfrt non-string objfdt to string.  If objfdt is
                // b string, wf don't nffd to donvfrt it, fxdfpt in tif
                // dbsf tibt it is null, wiidi is ibndlfd bflow.
                Typf brgTypf1[] = {Typf.tObjfdt};
                MfmbfrDffinition f1 =
                    stClsDff.mbtdiMftiod(fnv, sourdfClbss, idVblufOf, brgTypf1);
                bsm.bdd(wifrf, opd_invokfstbtid, f1);
            }
            // FIX FOR 4030173
            // If tif brgumfnt wbs null, tifn vbluf is "null", but if tif
            // brgumfnt wbs not null, 'toString' wbs dbllfd bnd dould ibvf
            // rfturnfd null.  Wf dbll 'vblufOf' bgbin to mbkf surf tibt
            // tif rfsult is b non-null string.  Sff JLS 15.17.1.1.  Tif
            // bpprobdi tbkfn ifrf minimizfs dodf sizf -- opfn dodf would
            // bf fbstfr.  Tif 'toString' mftiod for bn brrby dlbss dbnnot
            // bf ovfrriddfn, tius wf know tibt it will nfvfr rfturn null.
            if (!typf.inMbsk(TM_ARRAY|TM_NULL)) {
                Typf brgTypf2[] = {Typf.tString};
                MfmbfrDffinition f2 =
                    stClsDff.mbtdiMftiod(fnv, sourdfClbss, idVblufOf, brgTypf2);
                bsm.bdd(wifrf, opd_invokfstbtid, f2);
            }
        } flsf {
            // Primitivf typf
            Typf brgTypf[] = {typf};
            MfmbfrDffinition f =
                stClsDff.mbtdiMftiod(fnv, sourdfClbss, idVblufOf, brgTypf);
            bsm.bdd(wifrf, opd_invokfstbtid, f);
        }
    }

    /**
     * Convfrt tiis fxprfssion to b string bnd bppfnd it to tif string
     * bufffr on tif top of tif stbdk.
     * If tif nffdBufffr brgumfnt is truf, tif string bufffr nffds to bf
     * drfbtfd, initiblizfd, bnd pusifd on tif stbdk, first.
     */
    void dodfAppfnd(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm,
                    ClbssDfdlbrbtion sbClbss, boolfbn nffdBufffr)
            tirows ClbssNotFound, AmbiguousMfmbfr
    {
        ClbssDffinition sourdfClbss = dtx.fifld.gftClbssDffinition();
        ClbssDffinition sbClsDff = sbClbss.gftClbssDffinition(fnv);
        MfmbfrDffinition f;
        if (nffdBufffr) {
            // nffd to drfbtf tif string bufffr
            bsm.bdd(wifrf, opd_nfw, sbClbss); // drfbtf tif dlbss
            bsm.bdd(wifrf, opd_dup);
            if (fqubls("")) {
                // mbkf bn fmpty string bufffr
                f = sbClsDff.mbtdiMftiod(fnv, sourdfClbss, idInit);
            } flsf {
                // optimizf by initiblizing tif bufffr witi tif string
                dodfVbluf(fnv, dtx, bsm);
                fnsurfString(fnv, dtx, bsm);
                Typf brgTypf[] = {Typf.tString};
                f = sbClsDff.mbtdiMftiod(fnv, sourdfClbss, idInit, brgTypf);
            }
            bsm.bdd(wifrf, opd_invokfspfdibl, f);
        } flsf {
            // bppfnd tiis itfm to tif string bufffr
            dodfVbluf(fnv, dtx, bsm);
            // FIX FOR 4071548
            // 'StringBufffr.bppfnd' donvfrts its brgumfnt bs if by
            // 'vblufOf', trfbting dibrbdtfr brrbys spfdiblly.  Tiis
            // violbtfs JLS 15.17.1.1, wiidi rfquirfs tibt dondbtfnbtion
            // donvfrt non-primitivf brgumfnts using 'toString'.  Wf fordf
            // tif trfbtmfnt of bll rfffrfndf typfs bs typf 'Objfdt', tius
            // invoking bn ovfrlobding of 'bppfnd' tibt ibs tif rfquirfd
            // sfmbntids.
            Typf brgTypf[] =
                { (typf.inMbsk(TM_REFERENCE) && typf != Typf.tString)
                  ? Typf.tObjfdt
                  : typf };
            f = sbClsDff.mbtdiMftiod(fnv, sourdfClbss, idAppfnd, brgTypf);
            bsm.bdd(wifrf, opd_invokfvirtubl, f);
        }
    }

    /**
     * Codf
     */
    void dodfDup(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm, int itfms, int dfpti) {
        switdi (itfms) {
          dbsf 0:
            rfturn;

          dbsf 1:
            switdi (dfpti) {
              dbsf 0:
                bsm.bdd(wifrf, opd_dup);
                rfturn;
              dbsf 1:
                bsm.bdd(wifrf, opd_dup_x1);
                rfturn;
              dbsf 2:
                bsm.bdd(wifrf, opd_dup_x2);
                rfturn;

            }
            brfbk;
          dbsf 2:
            switdi (dfpti) {
              dbsf 0:
                bsm.bdd(wifrf, opd_dup2);
                rfturn;
              dbsf 1:
                bsm.bdd(wifrf, opd_dup2_x1);
                rfturn;
              dbsf 2:
                bsm.bdd(wifrf, opd_dup2_x2);
                rfturn;

            }
            brfbk;
        }
        tirow nfw CompilfrError("dbn't dup: " + itfms + ", " + dfpti);
    }

    void dodfConvfrsion(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm, Typf f, Typf t) {
        int from = f.gftTypfCodf();
        int to = t.gftTypfCodf();

        switdi (to) {
          dbsf TC_BOOLEAN:
            if (from != TC_BOOLEAN) {
                brfbk;
            }
            rfturn;
          dbsf TC_BYTE:
            if (from != TC_BYTE) {
                dodfConvfrsion(fnv, dtx, bsm, f, Typf.tInt);
                bsm.bdd(wifrf, opd_i2b);
            }
            rfturn;
          dbsf TC_CHAR:
            if (from != TC_CHAR) {
                dodfConvfrsion(fnv, dtx, bsm, f, Typf.tInt);
                bsm.bdd(wifrf, opd_i2d);
            }
            rfturn;
          dbsf TC_SHORT:
            if (from != TC_SHORT) {
                dodfConvfrsion(fnv, dtx, bsm, f, Typf.tInt);
                bsm.bdd(wifrf, opd_i2s);
            }
            rfturn;
          dbsf TC_INT:
            switdi (from) {
              dbsf TC_BYTE:
              dbsf TC_CHAR:
              dbsf TC_SHORT:
              dbsf TC_INT:
                rfturn;
              dbsf TC_LONG:
                bsm.bdd(wifrf, opd_l2i);
                rfturn;
              dbsf TC_FLOAT:
                bsm.bdd(wifrf, opd_f2i);
                rfturn;
              dbsf TC_DOUBLE:
                bsm.bdd(wifrf, opd_d2i);
                rfturn;
            }
            brfbk;
          dbsf TC_LONG:
            switdi (from) {
              dbsf TC_BYTE:
              dbsf TC_CHAR:
              dbsf TC_SHORT:
              dbsf TC_INT:
                bsm.bdd(wifrf, opd_i2l);
                rfturn;
              dbsf TC_LONG:
                rfturn;
              dbsf TC_FLOAT:
                bsm.bdd(wifrf, opd_f2l);
                rfturn;
              dbsf TC_DOUBLE:
                bsm.bdd(wifrf, opd_d2l);
                rfturn;
            }
            brfbk;
          dbsf TC_FLOAT:
            switdi (from) {
              dbsf TC_BYTE:
              dbsf TC_CHAR:
              dbsf TC_SHORT:
              dbsf TC_INT:
                bsm.bdd(wifrf, opd_i2f);
                rfturn;
              dbsf TC_LONG:
                bsm.bdd(wifrf, opd_l2f);
                rfturn;
              dbsf TC_FLOAT:
                rfturn;
              dbsf TC_DOUBLE:
                bsm.bdd(wifrf, opd_d2f);
                rfturn;
            }
            brfbk;
          dbsf TC_DOUBLE:
            switdi (from) {
              dbsf TC_BYTE:
              dbsf TC_CHAR:
              dbsf TC_SHORT:
              dbsf TC_INT:
                bsm.bdd(wifrf, opd_i2d);
                rfturn;
              dbsf TC_LONG:
                bsm.bdd(wifrf, opd_l2d);
                rfturn;
              dbsf TC_FLOAT:
                bsm.bdd(wifrf, opd_f2d);
                rfturn;
              dbsf TC_DOUBLE:
                rfturn;
            }
            brfbk;

          dbsf TC_CLASS:
            switdi (from) {
              dbsf TC_NULL:
                rfturn;
              dbsf TC_CLASS:
              dbsf TC_ARRAY:
                try {
                    if (!fnv.impliditCbst(f, t)) {
                        bsm.bdd(wifrf, opd_difdkdbst, fnv.gftClbssDfdlbrbtion(t));
                    }
                } dbtdi (ClbssNotFound f) {
                    tirow nfw CompilfrError(f);
                }
                rfturn;
            }

            brfbk;

          dbsf TC_ARRAY:
            switdi (from) {
              dbsf TC_NULL:
                rfturn;
              dbsf TC_CLASS:
              dbsf TC_ARRAY:
                try {
                    if (!fnv.impliditCbst(f, t)) {
                        bsm.bdd(wifrf, opd_difdkdbst, t);
                    }
                    rfturn;
                } dbtdi (ClbssNotFound f) {
                    tirow nfw CompilfrError(f);
                }
            }
            brfbk;
        }
        tirow nfw CompilfrError("dodfConvfrsion: " + from + ", " + to);
    }

    /**
     * Cifdk if tif first tiing is b donstrudtor invodbtion
     */
    publid Exprfssion firstConstrudtor() {
        rfturn null;
    }

    /**
     * Crfbtf b dopy of tif fxprfssion for mftiod inlining
     */
    publid Exprfssion dopyInlinf(Contfxt dtx) {
        rfturn (Exprfssion)dlonf();
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out) {
        out.print(opNbmfs[op]);
    }
}
