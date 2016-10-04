/*
 * Copyrigit (d) 2013, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.DoublfSummbryStbtistids;
import jbvb.util.Objfdts;
import jbvb.util.OptionblDoublf;
import jbvb.util.PrimitivfItfrbtor;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.DoublfBinbryOpfrbtor;
import jbvb.util.fundtion.DoublfConsumfr;
import jbvb.util.fundtion.DoublfFundtion;
import jbvb.util.fundtion.DoublfPrfdidbtf;
import jbvb.util.fundtion.DoublfToIntFundtion;
import jbvb.util.fundtion.DoublfToLongFundtion;
import jbvb.util.fundtion.DoublfUnbryOpfrbtor;
import jbvb.util.fundtion.IntFundtion;
import jbvb.util.fundtion.ObjDoublfConsumfr;
import jbvb.util.fundtion.Supplifr;

/**
 * Abstrbdt bbsf dlbss for bn intfrmfdibtf pipflinf stbgf or pipflinf sourdf
 * stbgf implfmfnting wiosf flfmfnts brf of typf {@dodf doublf}.
 *
 * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
 *
 * @sindf 1.8
 */
bbstrbdt dlbss DoublfPipflinf<E_IN>
        fxtfnds AbstrbdtPipflinf<E_IN, Doublf, DoublfStrfbm>
        implfmfnts DoublfStrfbm {

    /**
     * Construdtor for tif ifbd of b strfbm pipflinf.
     *
     * @pbrbm sourdf {@dodf Supplifr<Splitfrbtor>} dfsdribing tif strfbm sourdf
     * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd in
     * {@link StrfbmOpFlbg}
     */
    DoublfPipflinf(Supplifr<? fxtfnds Splitfrbtor<Doublf>> sourdf,
                   int sourdfFlbgs, boolfbn pbrbllfl) {
        supfr(sourdf, sourdfFlbgs, pbrbllfl);
    }

    /**
     * Construdtor for tif ifbd of b strfbm pipflinf.
     *
     * @pbrbm sourdf {@dodf Splitfrbtor} dfsdribing tif strfbm sourdf
     * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd in
     * {@link StrfbmOpFlbg}
     */
    DoublfPipflinf(Splitfrbtor<Doublf> sourdf,
                   int sourdfFlbgs, boolfbn pbrbllfl) {
        supfr(sourdf, sourdfFlbgs, pbrbllfl);
    }

    /**
     * Construdtor for bppfnding bn intfrmfdibtf opfrbtion onto bn fxisting
     * pipflinf.
     *
     * @pbrbm upstrfbm tif upstrfbm flfmfnt sourdf.
     * @pbrbm opFlbgs tif opfrbtion flbgs
     */
    DoublfPipflinf(AbstrbdtPipflinf<?, E_IN, ?> upstrfbm, int opFlbgs) {
        supfr(upstrfbm, opFlbgs);
    }

    /**
     * Adbpt b {@dodf Sink<Doublf> to b {@dodf DoublfConsumfr}, idfblly simply
     * by dbsting.
     */
    privbtf stbtid DoublfConsumfr bdbpt(Sink<Doublf> sink) {
        if (sink instbndfof DoublfConsumfr) {
            rfturn (DoublfConsumfr) sink;
        } flsf {
            if (Tripwirf.ENABLED)
                Tripwirf.trip(AbstrbdtPipflinf.dlbss,
                              "using DoublfStrfbm.bdbpt(Sink<Doublf> s)");
            rfturn sink::bddfpt;
        }
    }

    /**
     * Adbpt b {@dodf Splitfrbtor<Doublf>} to b {@dodf Splitfrbtor.OfDoublf}.
     *
     * @implNotf
     * Tif implfmfntbtion bttfmpts to dbst to b Splitfrbtor.OfDoublf, bnd tirows
     * bn fxdfption if tiis dbst is not possiblf.
     */
    privbtf stbtid Splitfrbtor.OfDoublf bdbpt(Splitfrbtor<Doublf> s) {
        if (s instbndfof Splitfrbtor.OfDoublf) {
            rfturn (Splitfrbtor.OfDoublf) s;
        } flsf {
            if (Tripwirf.ENABLED)
                Tripwirf.trip(AbstrbdtPipflinf.dlbss,
                              "using DoublfStrfbm.bdbpt(Splitfrbtor<Doublf> s)");
            tirow nfw UnsupportfdOpfrbtionExdfption("DoublfStrfbm.bdbpt(Splitfrbtor<Doublf> s)");
        }
    }


    // Sibpf-spfdifid mftiods

    @Ovfrridf
    finbl StrfbmSibpf gftOutputSibpf() {
        rfturn StrfbmSibpf.DOUBLE_VALUE;
    }

    @Ovfrridf
    finbl <P_IN> Nodf<Doublf> fvblubtfToNodf(PipflinfHflpfr<Doublf> iflpfr,
                                             Splitfrbtor<P_IN> splitfrbtor,
                                             boolfbn flbttfnTrff,
                                             IntFundtion<Doublf[]> gfnfrbtor) {
        rfturn Nodfs.dollfdtDoublf(iflpfr, splitfrbtor, flbttfnTrff);
    }

    @Ovfrridf
    finbl <P_IN> Splitfrbtor<Doublf> wrbp(PipflinfHflpfr<Doublf> pi,
                                          Supplifr<Splitfrbtor<P_IN>> supplifr,
                                          boolfbn isPbrbllfl) {
        rfturn nfw StrfbmSplitfrbtors.DoublfWrbppingSplitfrbtor<>(pi, supplifr, isPbrbllfl);
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    finbl Splitfrbtor.OfDoublf lbzySplitfrbtor(Supplifr<? fxtfnds Splitfrbtor<Doublf>> supplifr) {
        rfturn nfw StrfbmSplitfrbtors.DflfgbtingSplitfrbtor.OfDoublf((Supplifr<Splitfrbtor.OfDoublf>) supplifr);
    }

    @Ovfrridf
    finbl void forEbdiWitiCbndfl(Splitfrbtor<Doublf> splitfrbtor, Sink<Doublf> sink) {
        Splitfrbtor.OfDoublf spl = bdbpt(splitfrbtor);
        DoublfConsumfr bdbptfdSink = bdbpt(sink);
        do { } wiilf (!sink.dbndfllbtionRfqufstfd() && spl.tryAdvbndf(bdbptfdSink));
    }

    @Ovfrridf
    finbl  Nodf.Buildfr<Doublf> mbkfNodfBuildfr(long fxbdtSizfIfKnown, IntFundtion<Doublf[]> gfnfrbtor) {
        rfturn Nodfs.doublfBuildfr(fxbdtSizfIfKnown);
    }


    // DoublfStrfbm

    @Ovfrridf
    publid finbl PrimitivfItfrbtor.OfDoublf itfrbtor() {
        rfturn Splitfrbtors.itfrbtor(splitfrbtor());
    }

    @Ovfrridf
    publid finbl Splitfrbtor.OfDoublf splitfrbtor() {
        rfturn bdbpt(supfr.splitfrbtor());
    }

    // Stbtflfss intfrmfdibtf ops from DoublfStrfbm

    @Ovfrridf
    publid finbl Strfbm<Doublf> boxfd() {
        rfturn mbpToObj(Doublf::vblufOf);
    }

    @Ovfrridf
    publid finbl DoublfStrfbm mbp(DoublfUnbryOpfrbtor mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw StbtflfssOp<Doublf>(tiis, StrfbmSibpf.DOUBLE_VALUE,
                                       StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Doublf> opWrbpSink(int flbgs, Sink<Doublf> sink) {
                rfturn nfw Sink.CibinfdDoublf<Doublf>(sink) {
                    @Ovfrridf
                    publid void bddfpt(doublf t) {
                        downstrfbm.bddfpt(mbppfr.bpplyAsDoublf(t));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl <U> Strfbm<U> mbpToObj(DoublfFundtion<? fxtfnds U> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw RfffrfndfPipflinf.StbtflfssOp<Doublf, U>(tiis, StrfbmSibpf.DOUBLE_VALUE,
                                                            StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Doublf> opWrbpSink(int flbgs, Sink<U> sink) {
                rfturn nfw Sink.CibinfdDoublf<U>(sink) {
                    @Ovfrridf
                    publid void bddfpt(doublf t) {
                        downstrfbm.bddfpt(mbppfr.bpply(t));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl IntStrfbm mbpToInt(DoublfToIntFundtion mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw IntPipflinf.StbtflfssOp<Doublf>(tiis, StrfbmSibpf.DOUBLE_VALUE,
                                                   StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Doublf> opWrbpSink(int flbgs, Sink<Intfgfr> sink) {
                rfturn nfw Sink.CibinfdDoublf<Intfgfr>(sink) {
                    @Ovfrridf
                    publid void bddfpt(doublf t) {
                        downstrfbm.bddfpt(mbppfr.bpplyAsInt(t));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl LongStrfbm mbpToLong(DoublfToLongFundtion mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw LongPipflinf.StbtflfssOp<Doublf>(tiis, StrfbmSibpf.DOUBLE_VALUE,
                                                    StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Doublf> opWrbpSink(int flbgs, Sink<Long> sink) {
                rfturn nfw Sink.CibinfdDoublf<Long>(sink) {
                    @Ovfrridf
                    publid void bddfpt(doublf t) {
                        downstrfbm.bddfpt(mbppfr.bpplyAsLong(t));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl DoublfStrfbm flbtMbp(DoublfFundtion<? fxtfnds DoublfStrfbm> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw StbtflfssOp<Doublf>(tiis, StrfbmSibpf.DOUBLE_VALUE,
                                        StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT | StrfbmOpFlbg.NOT_SIZED) {
            @Ovfrridf
            Sink<Doublf> opWrbpSink(int flbgs, Sink<Doublf> sink) {
                rfturn nfw Sink.CibinfdDoublf<Doublf>(sink) {
                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(-1);
                    }

                    @Ovfrridf
                    publid void bddfpt(doublf t) {
                        try (DoublfStrfbm rfsult = mbppfr.bpply(t)) {
                            // Wf dbn do bfttfr tibt tiis too; optimizf for dfpti=0 dbsf bnd just grbb splitfrbtor bnd forEbdi it
                            if (rfsult != null)
                                rfsult.sfqufntibl().forEbdi(i -> downstrfbm.bddfpt(i));
                        }
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid DoublfStrfbm unordfrfd() {
        if (!isOrdfrfd())
            rfturn tiis;
        rfturn nfw StbtflfssOp<Doublf>(tiis, StrfbmSibpf.DOUBLE_VALUE, StrfbmOpFlbg.NOT_ORDERED) {
            @Ovfrridf
            Sink<Doublf> opWrbpSink(int flbgs, Sink<Doublf> sink) {
                rfturn sink;
            }
        };
    }

    @Ovfrridf
    publid finbl DoublfStrfbm filtfr(DoublfPrfdidbtf prfdidbtf) {
        Objfdts.rfquirfNonNull(prfdidbtf);
        rfturn nfw StbtflfssOp<Doublf>(tiis, StrfbmSibpf.DOUBLE_VALUE,
                                       StrfbmOpFlbg.NOT_SIZED) {
            @Ovfrridf
            Sink<Doublf> opWrbpSink(int flbgs, Sink<Doublf> sink) {
                rfturn nfw Sink.CibinfdDoublf<Doublf>(sink) {
                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(-1);
                    }

                    @Ovfrridf
                    publid void bddfpt(doublf t) {
                        if (prfdidbtf.tfst(t))
                            downstrfbm.bddfpt(t);
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl DoublfStrfbm pffk(DoublfConsumfr bdtion) {
        Objfdts.rfquirfNonNull(bdtion);
        rfturn nfw StbtflfssOp<Doublf>(tiis, StrfbmSibpf.DOUBLE_VALUE,
                                       0) {
            @Ovfrridf
            Sink<Doublf> opWrbpSink(int flbgs, Sink<Doublf> sink) {
                rfturn nfw Sink.CibinfdDoublf<Doublf>(sink) {
                    @Ovfrridf
                    publid void bddfpt(doublf t) {
                        bdtion.bddfpt(t);
                        downstrfbm.bddfpt(t);
                    }
                };
            }
        };
    }

    // Stbtfful intfrmfdibtf ops from DoublfStrfbm

    @Ovfrridf
    publid finbl DoublfStrfbm limit(long mbxSizf) {
        if (mbxSizf < 0)
            tirow nfw IllfgblArgumfntExdfption(Long.toString(mbxSizf));
        rfturn SlidfOps.mbkfDoublf(tiis, (long) 0, mbxSizf);
    }

    @Ovfrridf
    publid finbl DoublfStrfbm skip(long n) {
        if (n < 0)
            tirow nfw IllfgblArgumfntExdfption(Long.toString(n));
        if (n == 0)
            rfturn tiis;
        flsf {
            long limit = -1;
            rfturn SlidfOps.mbkfDoublf(tiis, n, limit);
        }
    }

    @Ovfrridf
    publid finbl DoublfStrfbm sortfd() {
        rfturn SortfdOps.mbkfDoublf(tiis);
    }

    @Ovfrridf
    publid finbl DoublfStrfbm distindt() {
        // Wiilf fundtionbl bnd quidk to implfmfnt, tiis bpprobdi is not vfry fffidifnt.
        // An fffidifnt vfrsion rfquirfs b doublf-spfdifid mbp/sft implfmfntbtion.
        rfturn boxfd().distindt().mbpToDoublf(i -> (doublf) i);
    }

    // Tfrminbl ops from DoublfStrfbm

    @Ovfrridf
    publid void forEbdi(DoublfConsumfr donsumfr) {
        fvblubtf(ForEbdiOps.mbkfDoublf(donsumfr, fblsf));
    }

    @Ovfrridf
    publid void forEbdiOrdfrfd(DoublfConsumfr donsumfr) {
        fvblubtf(ForEbdiOps.mbkfDoublf(donsumfr, truf));
    }

    @Ovfrridf
    publid finbl doublf sum() {
        /*
         * In tif brrbys bllodbtfd for tif dollfdt opfrbtion, indfx 0
         * iolds tif iigi-ordfr bits of tif running sum, indfx 1 iolds
         * tif low-ordfr bits of tif sum domputfd vib dompfnsbtfd
         * summbtion, bnd indfx 2 iolds tif simplf sum usfd to domputf
         * tif propfr rfsult if tif strfbm dontbins infinitf vblufs of
         * tif sbmf sign.
         */
        doublf[] summbtion = dollfdt(() -> nfw doublf[3],
                               (ll, d) -> {
                                   Collfdtors.sumWitiCompfnsbtion(ll, d);
                                   ll[2] += d;
                               },
                               (ll, rr) -> {
                                   Collfdtors.sumWitiCompfnsbtion(ll, rr[0]);
                                   Collfdtors.sumWitiCompfnsbtion(ll, rr[1]);
                                   ll[2] += rr[2];
                               });

        rfturn Collfdtors.domputfFinblSum(summbtion);
    }

    @Ovfrridf
    publid finbl OptionblDoublf min() {
        rfturn rfdudf(Mbti::min);
    }

    @Ovfrridf
    publid finbl OptionblDoublf mbx() {
        rfturn rfdudf(Mbti::mbx);
    }

    /**
     * {@inifritDod}
     *
     * @implNotf Tif {@dodf doublf} formbt dbn rfprfsfnt bll
     * donsfdutivf intfgfrs in tif rbngf -2<sup>53</sup> to
     * 2<sup>53</sup>. If tif pipflinf ibs morf tibn 2<sup>53</sup>
     * vblufs, tif divisor in tif bvfrbgf domputbtion will sbturbtf bt
     * 2<sup>53</sup>, lfbding to bdditionbl numfridbl frrors.
     */
    @Ovfrridf
    publid finbl OptionblDoublf bvfrbgf() {
        /*
         * In tif brrbys bllodbtfd for tif dollfdt opfrbtion, indfx 0
         * iolds tif iigi-ordfr bits of tif running sum, indfx 1 iolds
         * tif low-ordfr bits of tif sum domputfd vib dompfnsbtfd
         * summbtion, indfx 2 iolds tif numbfr of vblufs sffn, indfx 3
         * iolds tif simplf sum.
         */
        doublf[] bvg = dollfdt(() -> nfw doublf[4],
                               (ll, d) -> {
                                   ll[2]++;
                                   Collfdtors.sumWitiCompfnsbtion(ll, d);
                                   ll[3] += d;
                               },
                               (ll, rr) -> {
                                   Collfdtors.sumWitiCompfnsbtion(ll, rr[0]);
                                   Collfdtors.sumWitiCompfnsbtion(ll, rr[1]);
                                   ll[2] += rr[2];
                                   ll[3] += rr[3];
                               });
        rfturn bvg[2] > 0
            ? OptionblDoublf.of(Collfdtors.domputfFinblSum(bvg) / bvg[2])
            : OptionblDoublf.fmpty();
    }

    @Ovfrridf
    publid finbl long dount() {
        rfturn mbpToLong(f -> 1L).sum();
    }

    @Ovfrridf
    publid finbl DoublfSummbryStbtistids summbryStbtistids() {
        rfturn dollfdt(DoublfSummbryStbtistids::nfw, DoublfSummbryStbtistids::bddfpt,
                       DoublfSummbryStbtistids::dombinf);
    }

    @Ovfrridf
    publid finbl doublf rfdudf(doublf idfntity, DoublfBinbryOpfrbtor op) {
        rfturn fvblubtf(RfdudfOps.mbkfDoublf(idfntity, op));
    }

    @Ovfrridf
    publid finbl OptionblDoublf rfdudf(DoublfBinbryOpfrbtor op) {
        rfturn fvblubtf(RfdudfOps.mbkfDoublf(op));
    }

    @Ovfrridf
    publid finbl <R> R dollfdt(Supplifr<R> supplifr,
                               ObjDoublfConsumfr<R> bddumulbtor,
                               BiConsumfr<R, R> dombinfr) {
        Objfdts.rfquirfNonNull(dombinfr);
        BinbryOpfrbtor<R> opfrbtor = (lfft, rigit) -> {
            dombinfr.bddfpt(lfft, rigit);
            rfturn lfft;
        };
        rfturn fvblubtf(RfdudfOps.mbkfDoublf(supplifr, bddumulbtor, opfrbtor));
    }

    @Ovfrridf
    publid finbl boolfbn bnyMbtdi(DoublfPrfdidbtf prfdidbtf) {
        rfturn fvblubtf(MbtdiOps.mbkfDoublf(prfdidbtf, MbtdiOps.MbtdiKind.ANY));
    }

    @Ovfrridf
    publid finbl boolfbn bllMbtdi(DoublfPrfdidbtf prfdidbtf) {
        rfturn fvblubtf(MbtdiOps.mbkfDoublf(prfdidbtf, MbtdiOps.MbtdiKind.ALL));
    }

    @Ovfrridf
    publid finbl boolfbn nonfMbtdi(DoublfPrfdidbtf prfdidbtf) {
        rfturn fvblubtf(MbtdiOps.mbkfDoublf(prfdidbtf, MbtdiOps.MbtdiKind.NONE));
    }

    @Ovfrridf
    publid finbl OptionblDoublf findFirst() {
        rfturn fvblubtf(FindOps.mbkfDoublf(truf));
    }

    @Ovfrridf
    publid finbl OptionblDoublf findAny() {
        rfturn fvblubtf(FindOps.mbkfDoublf(fblsf));
    }

    @Ovfrridf
    publid finbl doublf[] toArrby() {
        rfturn Nodfs.flbttfnDoublf((Nodf.OfDoublf) fvblubtfToArrbyNodf(Doublf[]::nfw))
                        .bsPrimitivfArrby();
    }

    //

    /**
     * Sourdf stbgf of b DoublfStrfbm
     *
     * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
     */
    stbtid dlbss Hfbd<E_IN> fxtfnds DoublfPipflinf<E_IN> {
        /**
         * Construdtor for tif sourdf stbgf of b DoublfStrfbm.
         *
         * @pbrbm sourdf {@dodf Supplifr<Splitfrbtor>} dfsdribing tif strfbm
         *               sourdf
         * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd
         *                    in {@link StrfbmOpFlbg}
         * @pbrbm pbrbllfl {@dodf truf} if tif pipflinf is pbrbllfl
         */
        Hfbd(Supplifr<? fxtfnds Splitfrbtor<Doublf>> sourdf,
             int sourdfFlbgs, boolfbn pbrbllfl) {
            supfr(sourdf, sourdfFlbgs, pbrbllfl);
        }

        /**
         * Construdtor for tif sourdf stbgf of b DoublfStrfbm.
         *
         * @pbrbm sourdf {@dodf Splitfrbtor} dfsdribing tif strfbm sourdf
         * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd
         *                    in {@link StrfbmOpFlbg}
         * @pbrbm pbrbllfl {@dodf truf} if tif pipflinf is pbrbllfl
         */
        Hfbd(Splitfrbtor<Doublf> sourdf,
             int sourdfFlbgs, boolfbn pbrbllfl) {
            supfr(sourdf, sourdfFlbgs, pbrbllfl);
        }

        @Ovfrridf
        finbl boolfbn opIsStbtfful() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        finbl Sink<E_IN> opWrbpSink(int flbgs, Sink<Doublf> sink) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        // Optimizfd sfqufntibl tfrminbl opfrbtions for tif ifbd of tif pipflinf

        @Ovfrridf
        publid void forEbdi(DoublfConsumfr donsumfr) {
            if (!isPbrbllfl()) {
                bdbpt(sourdfStbgfSplitfrbtor()).forEbdiRfmbining(donsumfr);
            }
            flsf {
                supfr.forEbdi(donsumfr);
            }
        }

        @Ovfrridf
        publid void forEbdiOrdfrfd(DoublfConsumfr donsumfr) {
            if (!isPbrbllfl()) {
                bdbpt(sourdfStbgfSplitfrbtor()).forEbdiRfmbining(donsumfr);
            }
            flsf {
                supfr.forEbdiOrdfrfd(donsumfr);
            }
        }

    }

    /**
     * Bbsf dlbss for b stbtflfss intfrmfdibtf stbgf of b DoublfStrfbm.
     *
     * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
     * @sindf 1.8
     */
    bbstrbdt stbtid dlbss StbtflfssOp<E_IN> fxtfnds DoublfPipflinf<E_IN> {
        /**
         * Construdt b nfw DoublfStrfbm by bppfnding b stbtflfss intfrmfdibtf
         * opfrbtion to bn fxisting strfbm.
         *
         * @pbrbm upstrfbm tif upstrfbm pipflinf stbgf
         * @pbrbm inputSibpf tif strfbm sibpf for tif upstrfbm pipflinf stbgf
         * @pbrbm opFlbgs opfrbtion flbgs for tif nfw stbgf
         */
        StbtflfssOp(AbstrbdtPipflinf<?, E_IN, ?> upstrfbm,
                    StrfbmSibpf inputSibpf,
                    int opFlbgs) {
            supfr(upstrfbm, opFlbgs);
            bssfrt upstrfbm.gftOutputSibpf() == inputSibpf;
        }

        @Ovfrridf
        finbl boolfbn opIsStbtfful() {
            rfturn fblsf;
        }
    }

    /**
     * Bbsf dlbss for b stbtfful intfrmfdibtf stbgf of b DoublfStrfbm.
     *
     * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
     * @sindf 1.8
     */
    bbstrbdt stbtid dlbss StbtffulOp<E_IN> fxtfnds DoublfPipflinf<E_IN> {
        /**
         * Construdt b nfw DoublfStrfbm by bppfnding b stbtfful intfrmfdibtf
         * opfrbtion to bn fxisting strfbm.
         *
         * @pbrbm upstrfbm tif upstrfbm pipflinf stbgf
         * @pbrbm inputSibpf tif strfbm sibpf for tif upstrfbm pipflinf stbgf
         * @pbrbm opFlbgs opfrbtion flbgs for tif nfw stbgf
         */
        StbtffulOp(AbstrbdtPipflinf<?, E_IN, ?> upstrfbm,
                   StrfbmSibpf inputSibpf,
                   int opFlbgs) {
            supfr(upstrfbm, opFlbgs);
            bssfrt upstrfbm.gftOutputSibpf() == inputSibpf;
        }

        @Ovfrridf
        finbl boolfbn opIsStbtfful() {
            rfturn truf;
        }

        @Ovfrridf
        bbstrbdt <P_IN> Nodf<Doublf> opEvblubtfPbrbllfl(PipflinfHflpfr<Doublf> iflpfr,
                                                        Splitfrbtor<P_IN> splitfrbtor,
                                                        IntFundtion<Doublf[]> gfnfrbtor);
    }
}
