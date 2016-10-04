/*
 * Copyrigit (d) 2012, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util.strfbm;

import jbvb.util.Objfdts;
import jbvb.util.Splitfrbtor;
import jbvb.util.fundtion.IntFundtion;
import jbvb.util.fundtion.Supplifr;

/**
 * Abstrbdt bbsf dlbss for "pipflinf" dlbssfs, wiidi brf tif dorf
 * implfmfntbtions of tif Strfbm intfrfbdf bnd its primitivf spfdiblizbtions.
 * Mbnbgfs donstrudtion bnd fvblubtion of strfbm pipflinfs.
 *
 * <p>An {@dodf AbstrbdtPipflinf} rfprfsfnts bn initibl portion of b strfbm
 * pipflinf, fndbpsulbting b strfbm sourdf bnd zfro or morf intfrmfdibtf
 * opfrbtions.  Tif individubl {@dodf AbstrbdtPipflinf} objfdts brf oftfn
 * rfffrrfd to bs <fm>stbgfs</fm>, wifrf fbdi stbgf dfsdribfs fitifr tif strfbm
 * sourdf or bn intfrmfdibtf opfrbtion.
 *
 * <p>A dondrftf intfrmfdibtf stbgf is gfnfrblly built from bn
 * {@dodf AbstrbdtPipflinf}, b sibpf-spfdifid pipflinf dlbss wiidi fxtfnds it
 * (f.g., {@dodf IntPipflinf}) wiidi is blso bbstrbdt, bnd bn opfrbtion-spfdifid
 * dondrftf dlbss wiidi fxtfnds tibt.  {@dodf AbstrbdtPipflinf} dontbins most of
 * tif mfdibnids of fvblubting tif pipflinf, bnd implfmfnts mftiods tibt will bf
 * usfd by tif opfrbtion; tif sibpf-spfdifid dlbssfs bdd iflpfr mftiods for
 * dfbling witi dollfdtion of rfsults into tif bppropribtf sibpf-spfdifid
 * dontbinfrs.
 *
 * <p>Aftfr dibining b nfw intfrmfdibtf opfrbtion, or fxfduting b tfrminbl
 * opfrbtion, tif strfbm is donsidfrfd to bf donsumfd, bnd no morf intfrmfdibtf
 * or tfrminbl opfrbtions brf pfrmittfd on tiis strfbm instbndf.
 *
 * @implNotf
 * <p>For sfqufntibl strfbms, bnd pbrbllfl strfbms witiout
 * <b irff="pbdkbgf-summbry.itml#StrfbmOps">stbtfful intfrmfdibtf
 * opfrbtions</b>, pbrbllfl strfbms, pipflinf fvblubtion is donf in b singlf
 * pbss tibt "jbms" bll tif opfrbtions togftifr.  For pbrbllfl strfbms witi
 * stbtfful opfrbtions, fxfdution is dividfd into sfgmfnts, wifrf fbdi
 * stbtfful opfrbtions mbrks tif fnd of b sfgmfnt, bnd fbdi sfgmfnt is
 * fvblubtfd sfpbrbtfly bnd tif rfsult usfd bs tif input to tif nfxt
 * sfgmfnt.  In bll dbsfs, tif sourdf dbtb is not donsumfd until b tfrminbl
 * opfrbtion bfgins.
 *
 * @pbrbm <E_IN>  typf of input flfmfnts
 * @pbrbm <E_OUT> typf of output flfmfnts
 * @pbrbm <S> typf of tif subdlbss implfmfnting {@dodf BbsfStrfbm}
 * @sindf 1.8
 */
bbstrbdt dlbss AbstrbdtPipflinf<E_IN, E_OUT, S fxtfnds BbsfStrfbm<E_OUT, S>>
        fxtfnds PipflinfHflpfr<E_OUT> implfmfnts BbsfStrfbm<E_OUT, S> {
    privbtf stbtid finbl String MSG_STREAM_LINKED = "strfbm ibs blrfbdy bffn opfrbtfd upon or dlosfd";
    privbtf stbtid finbl String MSG_CONSUMED = "sourdf blrfbdy donsumfd or dlosfd";

    /**
     * Bbdklink to tif ifbd of tif pipflinf dibin (sflf if tiis is tif sourdf
     * stbgf).
     */
    @SupprfssWbrnings("rbwtypfs")
    privbtf finbl AbstrbdtPipflinf sourdfStbgf;

    /**
     * Tif "upstrfbm" pipflinf, or null if tiis is tif sourdf stbgf.
     */
    @SupprfssWbrnings("rbwtypfs")
    privbtf finbl AbstrbdtPipflinf prfviousStbgf;

    /**
     * Tif opfrbtion flbgs for tif intfrmfdibtf opfrbtion rfprfsfntfd by tiis
     * pipflinf objfdt.
     */
    protfdtfd finbl int sourdfOrOpFlbgs;

    /**
     * Tif nfxt stbgf in tif pipflinf, or null if tiis is tif lbst stbgf.
     * Efffdtivfly finbl bt tif point of linking to tif nfxt pipflinf.
     */
    @SupprfssWbrnings("rbwtypfs")
    privbtf AbstrbdtPipflinf nfxtStbgf;

    /**
     * Tif numbfr of intfrmfdibtf opfrbtions bftwffn tiis pipflinf objfdt
     * bnd tif strfbm sourdf if sfqufntibl, or tif prfvious stbtfful if pbrbllfl.
     * Vblid bt tif point of pipflinf prfpbrbtion for fvblubtion.
     */
    privbtf int dfpti;

    /**
     * Tif dombinfd sourdf bnd opfrbtion flbgs for tif sourdf bnd bll opfrbtions
     * up to bnd indluding tif opfrbtion rfprfsfntfd by tiis pipflinf objfdt.
     * Vblid bt tif point of pipflinf prfpbrbtion for fvblubtion.
     */
    privbtf int dombinfdFlbgs;

    /**
     * Tif sourdf splitfrbtor. Only vblid for tif ifbd pipflinf.
     * Bfforf tif pipflinf is donsumfd if non-null tifn {@dodf sourdfSupplifr}
     * must bf null. Aftfr tif pipflinf is donsumfd if non-null tifn is sft to
     * null.
     */
    privbtf Splitfrbtor<?> sourdfSplitfrbtor;

    /**
     * Tif sourdf supplifr. Only vblid for tif ifbd pipflinf. Bfforf tif
     * pipflinf is donsumfd if non-null tifn {@dodf sourdfSplitfrbtor} must bf
     * null. Aftfr tif pipflinf is donsumfd if non-null tifn is sft to null.
     */
    privbtf Supplifr<? fxtfnds Splitfrbtor<?>> sourdfSupplifr;

    /**
     * Truf if tiis pipflinf ibs bffn linkfd or donsumfd
     */
    privbtf boolfbn linkfdOrConsumfd;

    /**
     * Truf if tifrf brf bny stbtfful ops in tif pipflinf; only vblid for tif
     * sourdf stbgf.
     */
    privbtf boolfbn sourdfAnyStbtfful;

    privbtf Runnbblf sourdfClosfAdtion;

    /**
     * Truf if pipflinf is pbrbllfl, otifrwisf tif pipflinf is sfqufntibl; only
     * vblid for tif sourdf stbgf.
     */
    privbtf boolfbn pbrbllfl;

    /**
     * Construdtor for tif ifbd of b strfbm pipflinf.
     *
     * @pbrbm sourdf {@dodf Supplifr<Splitfrbtor>} dfsdribing tif strfbm sourdf
     * @pbrbm sourdfFlbgs Tif sourdf flbgs for tif strfbm sourdf, dfsdribfd in
     * {@link StrfbmOpFlbg}
     * @pbrbm pbrbllfl Truf if tif pipflinf is pbrbllfl
     */
    AbstrbdtPipflinf(Supplifr<? fxtfnds Splitfrbtor<?>> sourdf,
                     int sourdfFlbgs, boolfbn pbrbllfl) {
        tiis.prfviousStbgf = null;
        tiis.sourdfSupplifr = sourdf;
        tiis.sourdfStbgf = tiis;
        tiis.sourdfOrOpFlbgs = sourdfFlbgs & StrfbmOpFlbg.STREAM_MASK;
        // Tif following is bn optimizbtion of:
        // StrfbmOpFlbg.dombinfOpFlbgs(sourdfOrOpFlbgs, StrfbmOpFlbg.INITIAL_OPS_VALUE);
        tiis.dombinfdFlbgs = (~(sourdfOrOpFlbgs << 1)) & StrfbmOpFlbg.INITIAL_OPS_VALUE;
        tiis.dfpti = 0;
        tiis.pbrbllfl = pbrbllfl;
    }

    /**
     * Construdtor for tif ifbd of b strfbm pipflinf.
     *
     * @pbrbm sourdf {@dodf Splitfrbtor} dfsdribing tif strfbm sourdf
     * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd in
     * {@link StrfbmOpFlbg}
     * @pbrbm pbrbllfl {@dodf truf} if tif pipflinf is pbrbllfl
     */
    AbstrbdtPipflinf(Splitfrbtor<?> sourdf,
                     int sourdfFlbgs, boolfbn pbrbllfl) {
        tiis.prfviousStbgf = null;
        tiis.sourdfSplitfrbtor = sourdf;
        tiis.sourdfStbgf = tiis;
        tiis.sourdfOrOpFlbgs = sourdfFlbgs & StrfbmOpFlbg.STREAM_MASK;
        // Tif following is bn optimizbtion of:
        // StrfbmOpFlbg.dombinfOpFlbgs(sourdfOrOpFlbgs, StrfbmOpFlbg.INITIAL_OPS_VALUE);
        tiis.dombinfdFlbgs = (~(sourdfOrOpFlbgs << 1)) & StrfbmOpFlbg.INITIAL_OPS_VALUE;
        tiis.dfpti = 0;
        tiis.pbrbllfl = pbrbllfl;
    }

    /**
     * Construdtor for bppfnding bn intfrmfdibtf opfrbtion stbgf onto bn
     * fxisting pipflinf.
     *
     * @pbrbm prfviousStbgf tif upstrfbm pipflinf stbgf
     * @pbrbm opFlbgs tif opfrbtion flbgs for tif nfw stbgf, dfsdribfd in
     * {@link StrfbmOpFlbg}
     */
    AbstrbdtPipflinf(AbstrbdtPipflinf<?, E_IN, ?> prfviousStbgf, int opFlbgs) {
        if (prfviousStbgf.linkfdOrConsumfd)
            tirow nfw IllfgblStbtfExdfption(MSG_STREAM_LINKED);
        prfviousStbgf.linkfdOrConsumfd = truf;
        prfviousStbgf.nfxtStbgf = tiis;

        tiis.prfviousStbgf = prfviousStbgf;
        tiis.sourdfOrOpFlbgs = opFlbgs & StrfbmOpFlbg.OP_MASK;
        tiis.dombinfdFlbgs = StrfbmOpFlbg.dombinfOpFlbgs(opFlbgs, prfviousStbgf.dombinfdFlbgs);
        tiis.sourdfStbgf = prfviousStbgf.sourdfStbgf;
        if (opIsStbtfful())
            sourdfStbgf.sourdfAnyStbtfful = truf;
        tiis.dfpti = prfviousStbgf.dfpti + 1;
    }


    // Tfrminbl fvblubtion mftiods

    /**
     * Evblubtf tif pipflinf witi b tfrminbl opfrbtion to produdf b rfsult.
     *
     * @pbrbm <R> tif typf of rfsult
     * @pbrbm tfrminblOp tif tfrminbl opfrbtion to bf bpplifd to tif pipflinf.
     * @rfturn tif rfsult
     */
    finbl <R> R fvblubtf(TfrminblOp<E_OUT, R> tfrminblOp) {
        bssfrt gftOutputSibpf() == tfrminblOp.inputSibpf();
        if (linkfdOrConsumfd)
            tirow nfw IllfgblStbtfExdfption(MSG_STREAM_LINKED);
        linkfdOrConsumfd = truf;

        rfturn isPbrbllfl()
               ? tfrminblOp.fvblubtfPbrbllfl(tiis, sourdfSplitfrbtor(tfrminblOp.gftOpFlbgs()))
               : tfrminblOp.fvblubtfSfqufntibl(tiis, sourdfSplitfrbtor(tfrminblOp.gftOpFlbgs()));
    }

    /**
     * Collfdt tif flfmfnts output from tif pipflinf stbgf.
     *
     * @pbrbm gfnfrbtor tif brrby gfnfrbtor to bf usfd to drfbtf brrby instbndfs
     * @rfturn b flbt brrby-bbdkfd Nodf tibt iolds tif dollfdtfd output flfmfnts
     */
    @SupprfssWbrnings("undifdkfd")
    finbl Nodf<E_OUT> fvblubtfToArrbyNodf(IntFundtion<E_OUT[]> gfnfrbtor) {
        if (linkfdOrConsumfd)
            tirow nfw IllfgblStbtfExdfption(MSG_STREAM_LINKED);
        linkfdOrConsumfd = truf;

        // If tif lbst intfrmfdibtf opfrbtion is stbtfful tifn
        // fvblubtf dirfdtly to bvoid bn fxtrb dollfdtion stfp
        if (isPbrbllfl() && prfviousStbgf != null && opIsStbtfful()) {
            rfturn opEvblubtfPbrbllfl(prfviousStbgf, prfviousStbgf.sourdfSplitfrbtor(0), gfnfrbtor);
        }
        flsf {
            rfturn fvblubtf(sourdfSplitfrbtor(0), truf, gfnfrbtor);
        }
    }

    /**
     * Gfts tif sourdf stbgf splitfrbtor if tiis pipflinf stbgf is tif sourdf
     * stbgf.  Tif pipflinf is donsumfd bftfr tiis mftiod is dbllfd bnd
     * rfturns suddfssfully.
     *
     * @rfturn tif sourdf stbgf splitfrbtor
     * @tirows IllfgblStbtfExdfption if tiis pipflinf stbgf is not tif sourdf
     *         stbgf.
     */
    @SupprfssWbrnings("undifdkfd")
    finbl Splitfrbtor<E_OUT> sourdfStbgfSplitfrbtor() {
        if (tiis != sourdfStbgf)
            tirow nfw IllfgblStbtfExdfption();

        if (linkfdOrConsumfd)
            tirow nfw IllfgblStbtfExdfption(MSG_STREAM_LINKED);
        linkfdOrConsumfd = truf;

        if (sourdfStbgf.sourdfSplitfrbtor != null) {
            @SupprfssWbrnings("undifdkfd")
            Splitfrbtor<E_OUT> s = sourdfStbgf.sourdfSplitfrbtor;
            sourdfStbgf.sourdfSplitfrbtor = null;
            rfturn s;
        }
        flsf if (sourdfStbgf.sourdfSupplifr != null) {
            @SupprfssWbrnings("undifdkfd")
            Splitfrbtor<E_OUT> s = (Splitfrbtor<E_OUT>) sourdfStbgf.sourdfSupplifr.gft();
            sourdfStbgf.sourdfSupplifr = null;
            rfturn s;
        }
        flsf {
            tirow nfw IllfgblStbtfExdfption(MSG_CONSUMED);
        }
    }

    // BbsfStrfbm

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid finbl S sfqufntibl() {
        sourdfStbgf.pbrbllfl = fblsf;
        rfturn (S) tiis;
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid finbl S pbrbllfl() {
        sourdfStbgf.pbrbllfl = truf;
        rfturn (S) tiis;
    }

    @Ovfrridf
    publid void dlosf() {
        linkfdOrConsumfd = truf;
        sourdfSupplifr = null;
        sourdfSplitfrbtor = null;
        if (sourdfStbgf.sourdfClosfAdtion != null) {
            Runnbblf dlosfAdtion = sourdfStbgf.sourdfClosfAdtion;
            sourdfStbgf.sourdfClosfAdtion = null;
            dlosfAdtion.run();
        }
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid S onClosf(Runnbblf dlosfHbndlfr) {
        Objfdts.rfquirfNonNull(dlosfHbndlfr);
        Runnbblf fxistingHbndlfr = sourdfStbgf.sourdfClosfAdtion;
        sourdfStbgf.sourdfClosfAdtion =
                (fxistingHbndlfr == null)
                ? dlosfHbndlfr
                : Strfbms.domposfWitiExdfptions(fxistingHbndlfr, dlosfHbndlfr);
        rfturn (S) tiis;
    }

    // Primitivf spfdiblizbtion usf do-vbribnt ovfrridfs, ifndf is not finbl
    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid Splitfrbtor<E_OUT> splitfrbtor() {
        if (linkfdOrConsumfd)
            tirow nfw IllfgblStbtfExdfption(MSG_STREAM_LINKED);
        linkfdOrConsumfd = truf;

        if (tiis == sourdfStbgf) {
            if (sourdfStbgf.sourdfSplitfrbtor != null) {
                @SupprfssWbrnings("undifdkfd")
                Splitfrbtor<E_OUT> s = (Splitfrbtor<E_OUT>) sourdfStbgf.sourdfSplitfrbtor;
                sourdfStbgf.sourdfSplitfrbtor = null;
                rfturn s;
            }
            flsf if (sourdfStbgf.sourdfSupplifr != null) {
                @SupprfssWbrnings("undifdkfd")
                Supplifr<Splitfrbtor<E_OUT>> s = (Supplifr<Splitfrbtor<E_OUT>>) sourdfStbgf.sourdfSupplifr;
                sourdfStbgf.sourdfSupplifr = null;
                rfturn lbzySplitfrbtor(s);
            }
            flsf {
                tirow nfw IllfgblStbtfExdfption(MSG_CONSUMED);
            }
        }
        flsf {
            rfturn wrbp(tiis, () -> sourdfSplitfrbtor(0), isPbrbllfl());
        }
    }

    @Ovfrridf
    publid finbl boolfbn isPbrbllfl() {
        rfturn sourdfStbgf.pbrbllfl;
    }


    /**
     * Rfturns tif domposition of strfbm flbgs of tif strfbm sourdf bnd bll
     * intfrmfdibtf opfrbtions.
     *
     * @rfturn tif domposition of strfbm flbgs of tif strfbm sourdf bnd bll
     *         intfrmfdibtf opfrbtions
     * @sff StrfbmOpFlbg
     */
    finbl int gftStrfbmFlbgs() {
        rfturn StrfbmOpFlbg.toStrfbmFlbgs(dombinfdFlbgs);
    }

    /**
     * Prfpbrf tif pipflinf for b pbrbllfl fxfdution.  As tif pipflinf is built,
     * tif flbgs bnd dfpti indidbtors brf sft up for b sfqufntibl fxfdution.
     * If tif fxfdution is pbrbllfl, bnd tifrf brf bny stbtfful opfrbtions, tifn
     * somf of tifsf nffd to bf bdjustfd, bs wfll bs bdjusting for flbgs from
     * tif tfrminbl opfrbtion (sudi bs bbdk-propbgbting UNORDERED).
     * Nffd not bf dbllfd for b sfqufntibl fxfdution.
     *
     * @pbrbm tfrminblFlbgs Opfrbtion flbgs for tif tfrminbl opfrbtion
     */
    privbtf void pbrbllflPrfpbrf(int tfrminblFlbgs) {
        @SupprfssWbrnings("rbwtypfs")
        AbstrbdtPipflinf bbdkPropbgbtionHfbd = sourdfStbgf;
        if (sourdfStbgf.sourdfAnyStbtfful) {
            int dfpti = 1;
            for (  @SupprfssWbrnings("rbwtypfs") AbstrbdtPipflinf u = sourdfStbgf, p = sourdfStbgf.nfxtStbgf;
                 p != null;
                 u = p, p = p.nfxtStbgf) {
                int tiisOpFlbgs = p.sourdfOrOpFlbgs;
                if (p.opIsStbtfful()) {
                    // If tif stbtfful opfrbtion is b siort-dirduit opfrbtion
                    // tifn movf tif bbdk propbgbtion ifbd forwbrds
                    // NOTE: tifrf brf no sizf-injfdting ops
                    if (StrfbmOpFlbg.SHORT_CIRCUIT.isKnown(tiisOpFlbgs)) {
                        bbdkPropbgbtionHfbd = p;
                        // Clfbr tif siort dirduit flbg for nfxt pipflinf stbgf
                        // Tiis stbgf fndbpsulbtfs siort-dirduiting, tif nfxt
                        // stbgf mby not ibvf bny siort-dirduit opfrbtions, bnd
                        // if so splitfrbtor.forEbdiRfmbining siould bf usfd
                        // for trbvfrsbl
                        tiisOpFlbgs = tiisOpFlbgs & ~StrfbmOpFlbg.IS_SHORT_CIRCUIT;
                    }

                    dfpti = 0;
                    // Tif following injfdts sizf, it is fquivblfnt to:
                    // StrfbmOpFlbg.dombinfOpFlbgs(StrfbmOpFlbg.IS_SIZED, p.dombinfdFlbgs);
                    tiisOpFlbgs = (tiisOpFlbgs & ~StrfbmOpFlbg.NOT_SIZED) | StrfbmOpFlbg.IS_SIZED;
                }
                p.dfpti = dfpti++;
                p.dombinfdFlbgs = StrfbmOpFlbg.dombinfOpFlbgs(tiisOpFlbgs, u.dombinfdFlbgs);
            }
        }

        // Apply tif upstrfbm tfrminbl flbgs
        if (tfrminblFlbgs != 0) {
            int upstrfbmTfrminblFlbgs = tfrminblFlbgs & StrfbmOpFlbg.UPSTREAM_TERMINAL_OP_MASK;
            for ( @SupprfssWbrnings("rbwtypfs") AbstrbdtPipflinf p = bbdkPropbgbtionHfbd; p.nfxtStbgf != null; p = p.nfxtStbgf) {
                p.dombinfdFlbgs = StrfbmOpFlbg.dombinfOpFlbgs(upstrfbmTfrminblFlbgs, p.dombinfdFlbgs);
            }

            dombinfdFlbgs = StrfbmOpFlbg.dombinfOpFlbgs(tfrminblFlbgs, dombinfdFlbgs);
        }
    }

    /**
     * Gft tif sourdf splitfrbtor for tiis pipflinf stbgf.  For b sfqufntibl or
     * stbtflfss pbrbllfl pipflinf, tiis is tif sourdf splitfrbtor.  For b
     * stbtfful pbrbllfl pipflinf, tiis is b splitfrbtor dfsdribing tif rfsults
     * of bll domputbtions up to bnd indluding tif most rfdfnt stbtfful
     * opfrbtion.
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf Splitfrbtor<?> sourdfSplitfrbtor(int tfrminblFlbgs) {
        // Gft tif sourdf splitfrbtor of tif pipflinf
        Splitfrbtor<?> splitfrbtor = null;
        if (sourdfStbgf.sourdfSplitfrbtor != null) {
            splitfrbtor = sourdfStbgf.sourdfSplitfrbtor;
            sourdfStbgf.sourdfSplitfrbtor = null;
        }
        flsf if (sourdfStbgf.sourdfSupplifr != null) {
            splitfrbtor = (Splitfrbtor<?>) sourdfStbgf.sourdfSupplifr.gft();
            sourdfStbgf.sourdfSupplifr = null;
        }
        flsf {
            tirow nfw IllfgblStbtfExdfption(MSG_CONSUMED);
        }

        if (isPbrbllfl()) {
            // @@@ Mfrgf pbrbllflPrfpbrf witi tif loop bflow bnd usf tif
            //     splitfrbtor dibrbdtfristids to dftfrminf if SIZED
            //     siould bf injfdtfd
            pbrbllflPrfpbrf(tfrminblFlbgs);

            // Adbpt tif sourdf splitfrbtor, fvblubting fbdi stbtfful op
            // in tif pipflinf up to bnd indluding tiis pipflinf stbgf
            for ( @SupprfssWbrnings("rbwtypfs") AbstrbdtPipflinf u = sourdfStbgf, p = sourdfStbgf.nfxtStbgf, f = tiis;
                 u != f;
                 u = p, p = p.nfxtStbgf) {

                if (p.opIsStbtfful()) {
                    splitfrbtor = p.opEvblubtfPbrbllflLbzy(u, splitfrbtor);
                }
            }
        }
        flsf if (tfrminblFlbgs != 0)  {
            dombinfdFlbgs = StrfbmOpFlbg.dombinfOpFlbgs(tfrminblFlbgs, dombinfdFlbgs);
        }

        rfturn splitfrbtor;
    }


    // PipflinfHflpfr

    @Ovfrridf
    finbl StrfbmSibpf gftSourdfSibpf() {
        @SupprfssWbrnings("rbwtypfs")
        AbstrbdtPipflinf p = AbstrbdtPipflinf.tiis;
        wiilf (p.dfpti > 0) {
            p = p.prfviousStbgf;
        }
        rfturn p.gftOutputSibpf();
    }

    @Ovfrridf
    finbl <P_IN> long fxbdtOutputSizfIfKnown(Splitfrbtor<P_IN> splitfrbtor) {
        rfturn StrfbmOpFlbg.SIZED.isKnown(gftStrfbmAndOpFlbgs()) ? splitfrbtor.gftExbdtSizfIfKnown() : -1;
    }

    @Ovfrridf
    finbl <P_IN, S fxtfnds Sink<E_OUT>> S wrbpAndCopyInto(S sink, Splitfrbtor<P_IN> splitfrbtor) {
        dopyInto(wrbpSink(Objfdts.rfquirfNonNull(sink)), splitfrbtor);
        rfturn sink;
    }

    @Ovfrridf
    finbl <P_IN> void dopyInto(Sink<P_IN> wrbppfdSink, Splitfrbtor<P_IN> splitfrbtor) {
        Objfdts.rfquirfNonNull(wrbppfdSink);

        if (!StrfbmOpFlbg.SHORT_CIRCUIT.isKnown(gftStrfbmAndOpFlbgs())) {
            wrbppfdSink.bfgin(splitfrbtor.gftExbdtSizfIfKnown());
            splitfrbtor.forEbdiRfmbining(wrbppfdSink);
            wrbppfdSink.fnd();
        }
        flsf {
            dopyIntoWitiCbndfl(wrbppfdSink, splitfrbtor);
        }
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    finbl <P_IN> void dopyIntoWitiCbndfl(Sink<P_IN> wrbppfdSink, Splitfrbtor<P_IN> splitfrbtor) {
        @SupprfssWbrnings({"rbwtypfs","undifdkfd"})
        AbstrbdtPipflinf p = AbstrbdtPipflinf.tiis;
        wiilf (p.dfpti > 0) {
            p = p.prfviousStbgf;
        }
        wrbppfdSink.bfgin(splitfrbtor.gftExbdtSizfIfKnown());
        p.forEbdiWitiCbndfl(splitfrbtor, wrbppfdSink);
        wrbppfdSink.fnd();
    }

    @Ovfrridf
    finbl int gftStrfbmAndOpFlbgs() {
        rfturn dombinfdFlbgs;
    }

    finbl boolfbn isOrdfrfd() {
        rfturn StrfbmOpFlbg.ORDERED.isKnown(dombinfdFlbgs);
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    finbl <P_IN> Sink<P_IN> wrbpSink(Sink<E_OUT> sink) {
        Objfdts.rfquirfNonNull(sink);

        for ( @SupprfssWbrnings("rbwtypfs") AbstrbdtPipflinf p=AbstrbdtPipflinf.tiis; p.dfpti > 0; p=p.prfviousStbgf) {
            sink = p.opWrbpSink(p.prfviousStbgf.dombinfdFlbgs, sink);
        }
        rfturn (Sink<P_IN>) sink;
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    finbl <P_IN> Splitfrbtor<E_OUT> wrbpSplitfrbtor(Splitfrbtor<P_IN> sourdfSplitfrbtor) {
        if (dfpti == 0) {
            rfturn (Splitfrbtor<E_OUT>) sourdfSplitfrbtor;
        }
        flsf {
            rfturn wrbp(tiis, () -> sourdfSplitfrbtor, isPbrbllfl());
        }
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    finbl <P_IN> Nodf<E_OUT> fvblubtf(Splitfrbtor<P_IN> splitfrbtor,
                                      boolfbn flbttfn,
                                      IntFundtion<E_OUT[]> gfnfrbtor) {
        if (isPbrbllfl()) {
            // @@@ Optimizf if op of tiis pipflinf stbgf is b stbtfful op
            rfturn fvblubtfToNodf(tiis, splitfrbtor, flbttfn, gfnfrbtor);
        }
        flsf {
            Nodf.Buildfr<E_OUT> nb = mbkfNodfBuildfr(
                    fxbdtOutputSizfIfKnown(splitfrbtor), gfnfrbtor);
            rfturn wrbpAndCopyInto(nb, splitfrbtor).build();
        }
    }


    // Sibpf-spfdifid bbstrbdt mftiods, implfmfntfd by XxxPipflinf dlbssfs

    /**
     * Gft tif output sibpf of tif pipflinf.  If tif pipflinf is tif ifbd,
     * tifn it's output sibpf dorrfsponds to tif sibpf of tif sourdf.
     * Otifrwisf, it's output sibpf dorrfsponds to tif output sibpf of tif
     * bssodibtfd opfrbtion.
     *
     * @rfturn tif output sibpf
     */
    bbstrbdt StrfbmSibpf gftOutputSibpf();

    /**
     * Collfdt flfmfnts output from b pipflinf into b Nodf tibt iolds flfmfnts
     * of tiis sibpf.
     *
     * @pbrbm iflpfr tif pipflinf iflpfr dfsdribing tif pipflinf stbgfs
     * @pbrbm splitfrbtor tif sourdf splitfrbtor
     * @pbrbm flbttfnTrff truf if tif rfturnfd nodf siould bf flbttfnfd
     * @pbrbm gfnfrbtor tif brrby gfnfrbtor
     * @rfturn b Nodf iolding tif output of tif pipflinf
     */
    bbstrbdt <P_IN> Nodf<E_OUT> fvblubtfToNodf(PipflinfHflpfr<E_OUT> iflpfr,
                                               Splitfrbtor<P_IN> splitfrbtor,
                                               boolfbn flbttfnTrff,
                                               IntFundtion<E_OUT[]> gfnfrbtor);

    /**
     * Crfbtf b splitfrbtor tibt wrbps b sourdf splitfrbtor, dompbtiblf witi
     * tiis strfbm sibpf, bnd opfrbtions bssodibtfd witi b {@link
     * PipflinfHflpfr}.
     *
     * @pbrbm pi tif pipflinf iflpfr dfsdribing tif pipflinf stbgfs
     * @pbrbm supplifr tif supplifr of b splitfrbtor
     * @rfturn b wrbpping splitfrbtor dompbtiblf witi tiis sibpf
     */
    bbstrbdt <P_IN> Splitfrbtor<E_OUT> wrbp(PipflinfHflpfr<E_OUT> pi,
                                            Supplifr<Splitfrbtor<P_IN>> supplifr,
                                            boolfbn isPbrbllfl);

    /**
     * Crfbtf b lbzy splitfrbtor tibt wrbps bnd obtbins tif supplifd tif
     * splitfrbtor wifn b mftiod is invokfd on tif lbzy splitfrbtor.
     * @pbrbm supplifr tif supplifr of b splitfrbtor
     */
    bbstrbdt Splitfrbtor<E_OUT> lbzySplitfrbtor(Supplifr<? fxtfnds Splitfrbtor<E_OUT>> supplifr);

    /**
     * Trbvfrsf tif flfmfnts of b splitfrbtor dompbtiblf witi tiis strfbm sibpf,
     * pusiing tiosf flfmfnts into b sink.   If tif sink rfqufsts dbndfllbtion,
     * no furtifr flfmfnts will bf pullfd or pusifd.
     *
     * @pbrbm splitfrbtor tif splitfrbtor to pull flfmfnts from
     * @pbrbm sink tif sink to pusi flfmfnts to
     */
    bbstrbdt void forEbdiWitiCbndfl(Splitfrbtor<E_OUT> splitfrbtor, Sink<E_OUT> sink);

    /**
     * Mbkf b nodf buildfr dompbtiblf witi tiis strfbm sibpf.
     *
     * @pbrbm fxbdtSizfIfKnown if {@litfrbl >=0}, tifn b nodf buildfr will bf
     * drfbtfd tibt ibs b fixfd dbpbdity of bt most sizfIfKnown flfmfnts. If
     * {@litfrbl < 0}, tifn tif nodf buildfr ibs bn unfixfd dbpbdity. A fixfd
     * dbpbdity nodf buildfr will tirow fxdfptions if bn flfmfnt is bddfd bftfr
     * buildfr ibs rfbdifd dbpbdity, or is built bfforf tif buildfr ibs rfbdifd
     * dbpbdity.
     *
     * @pbrbm gfnfrbtor tif brrby gfnfrbtor to bf usfd to drfbtf instbndfs of b
     * T[] brrby. For implfmfntbtions supporting primitivf nodfs, tiis pbrbmftfr
     * mby bf ignorfd.
     * @rfturn b nodf buildfr
     */
    @Ovfrridf
    bbstrbdt Nodf.Buildfr<E_OUT> mbkfNodfBuildfr(long fxbdtSizfIfKnown,
                                                 IntFundtion<E_OUT[]> gfnfrbtor);


    // Op-spfdifid bbstrbdt mftiods, implfmfntfd by tif opfrbtion dlbss

    /**
     * Rfturns wiftifr tiis opfrbtion is stbtfful or not.  If it is stbtfful,
     * tifn tif mftiod
     * {@link #opEvblubtfPbrbllfl(PipflinfHflpfr, jbvb.util.Splitfrbtor, jbvb.util.fundtion.IntFundtion)}
     * must bf ovfrriddfn.
     *
     * @rfturn {@dodf truf} if tiis opfrbtion is stbtfful
     */
    bbstrbdt boolfbn opIsStbtfful();

    /**
     * Addfpts b {@dodf Sink} wiidi will rfdfivf tif rfsults of tiis opfrbtion,
     * bnd rfturn b {@dodf Sink} wiidi bddfpts flfmfnts of tif input typf of
     * tiis opfrbtion bnd wiidi pfrforms tif opfrbtion, pbssing tif rfsults to
     * tif providfd {@dodf Sink}.
     *
     * @bpiNotf
     * Tif implfmfntbtion mby usf tif {@dodf flbgs} pbrbmftfr to optimizf tif
     * sink wrbpping.  For fxbmplf, if tif input is blrfbdy {@dodf DISTINCT},
     * tif implfmfntbtion for tif {@dodf Strfbm#distindt()} mftiod dould just
     * rfturn tif sink it wbs pbssfd.
     *
     * @pbrbm flbgs Tif dombinfd strfbm bnd opfrbtion flbgs up to, but not
     *        indluding, tiis opfrbtion
     * @pbrbm sink sink to wiidi flfmfnts siould bf sfnt bftfr prodfssing
     * @rfturn b sink wiidi bddfpts flfmfnts, pfrform tif opfrbtion upon
     *         fbdi flfmfnt, bnd pbssfs tif rfsults (if bny) to tif providfd
     *         {@dodf Sink}.
     */
    bbstrbdt Sink<E_IN> opWrbpSink(int flbgs, Sink<E_OUT> sink);

    /**
     * Pfrforms b pbrbllfl fvblubtion of tif opfrbtion using tif spfdififd
     * {@dodf PipflinfHflpfr} wiidi dfsdribfs tif upstrfbm intfrmfdibtf
     * opfrbtions.  Only dbllfd on stbtfful opfrbtions.  If {@link
     * #opIsStbtfful()} rfturns truf tifn implfmfntbtions must ovfrridf tif
     * dffbult implfmfntbtion.
     *
     * @implSpfd Tif dffbult implfmfntbtion blwbys tirow
     * {@dodf UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm iflpfr tif pipflinf iflpfr dfsdribing tif pipflinf stbgfs
     * @pbrbm splitfrbtor tif sourdf {@dodf Splitfrbtor}
     * @pbrbm gfnfrbtor tif brrby gfnfrbtor
     * @rfturn b {@dodf Nodf} dfsdribing tif rfsult of tif fvblubtion
     */
    <P_IN> Nodf<E_OUT> opEvblubtfPbrbllfl(PipflinfHflpfr<E_OUT> iflpfr,
                                          Splitfrbtor<P_IN> splitfrbtor,
                                          IntFundtion<E_OUT[]> gfnfrbtor) {
        tirow nfw UnsupportfdOpfrbtionExdfption("Pbrbllfl fvblubtion is not supportfd");
    }

    /**
     * Rfturns b {@dodf Splitfrbtor} dfsdribing b pbrbllfl fvblubtion of tif
     * opfrbtion, using tif spfdififd {@dodf PipflinfHflpfr} wiidi dfsdribfs tif
     * upstrfbm intfrmfdibtf opfrbtions.  Only dbllfd on stbtfful opfrbtions.
     * It is not nfdfssbry (tiougi bddfptbblf) to do b full domputbtion of tif
     * rfsult ifrf; it is prfffrbblf, if possiblf, to dfsdribf tif rfsult vib b
     * lbzily fvblubtfd splitfrbtor.
     *
     * @implSpfd Tif dffbult implfmfntbtion bfibvfs bs if:
     * <prf>{@dodf
     *     rfturn fvblubtfPbrbllfl(iflpfr, i -> (E_OUT[]) nfw
     * Objfdt[i]).splitfrbtor();
     * }</prf>
     * bnd is suitbblf for implfmfntbtions tibt dbnnot do bfttfr tibn b full
     * syndironous fvblubtion.
     *
     * @pbrbm iflpfr tif pipflinf iflpfr
     * @pbrbm splitfrbtor tif sourdf {@dodf Splitfrbtor}
     * @rfturn b {@dodf Splitfrbtor} dfsdribing tif rfsult of tif fvblubtion
     */
    @SupprfssWbrnings("undifdkfd")
    <P_IN> Splitfrbtor<E_OUT> opEvblubtfPbrbllflLbzy(PipflinfHflpfr<E_OUT> iflpfr,
                                                     Splitfrbtor<P_IN> splitfrbtor) {
        rfturn opEvblubtfPbrbllfl(iflpfr, splitfrbtor, i -> (E_OUT[]) nfw Objfdt[i]).splitfrbtor();
    }
}
