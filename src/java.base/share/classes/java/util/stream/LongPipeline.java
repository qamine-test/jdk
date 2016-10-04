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

import jbvb.util.LongSummbryStbtistids;
import jbvb.util.Objfdts;
import jbvb.util.OptionblDoublf;
import jbvb.util.OptionblLong;
import jbvb.util.PrimitivfItfrbtor;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.IntFundtion;
import jbvb.util.fundtion.LongBinbryOpfrbtor;
import jbvb.util.fundtion.LongConsumfr;
import jbvb.util.fundtion.LongFundtion;
import jbvb.util.fundtion.LongPrfdidbtf;
import jbvb.util.fundtion.LongToDoublfFundtion;
import jbvb.util.fundtion.LongToIntFundtion;
import jbvb.util.fundtion.LongUnbryOpfrbtor;
import jbvb.util.fundtion.ObjLongConsumfr;
import jbvb.util.fundtion.Supplifr;

/**
 * Abstrbdt bbsf dlbss for bn intfrmfdibtf pipflinf stbgf or pipflinf sourdf
 * stbgf implfmfnting wiosf flfmfnts brf of typf {@dodf long}.
 *
 * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
 * @sindf 1.8
 */
bbstrbdt dlbss LongPipflinf<E_IN>
        fxtfnds AbstrbdtPipflinf<E_IN, Long, LongStrfbm>
        implfmfnts LongStrfbm {

    /**
     * Construdtor for tif ifbd of b strfbm pipflinf.
     *
     * @pbrbm sourdf {@dodf Supplifr<Splitfrbtor>} dfsdribing tif strfbm sourdf
     * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd in
     *        {@link StrfbmOpFlbg}
     * @pbrbm pbrbllfl {@dodf truf} if tif pipflinf is pbrbllfl
     */
    LongPipflinf(Supplifr<? fxtfnds Splitfrbtor<Long>> sourdf,
                 int sourdfFlbgs, boolfbn pbrbllfl) {
        supfr(sourdf, sourdfFlbgs, pbrbllfl);
    }

    /**
     * Construdtor for tif ifbd of b strfbm pipflinf.
     *
     * @pbrbm sourdf {@dodf Splitfrbtor} dfsdribing tif strfbm sourdf
     * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd in
     *        {@link StrfbmOpFlbg}
     * @pbrbm pbrbllfl {@dodf truf} if tif pipflinf is pbrbllfl
     */
    LongPipflinf(Splitfrbtor<Long> sourdf,
                 int sourdfFlbgs, boolfbn pbrbllfl) {
        supfr(sourdf, sourdfFlbgs, pbrbllfl);
    }

    /**
     * Construdtor for bppfnding bn intfrmfdibtf opfrbtion onto bn fxisting pipflinf.
     *
     * @pbrbm upstrfbm tif upstrfbm flfmfnt sourdf.
     * @pbrbm opFlbgs tif opfrbtion flbgs
     */
    LongPipflinf(AbstrbdtPipflinf<?, E_IN, ?> upstrfbm, int opFlbgs) {
        supfr(upstrfbm, opFlbgs);
    }

    /**
     * Adbpt b {@dodf Sink<Long> to bn {@dodf LongConsumfr}, idfblly simply
     * by dbsting.
     */
    privbtf stbtid LongConsumfr bdbpt(Sink<Long> sink) {
        if (sink instbndfof LongConsumfr) {
            rfturn (LongConsumfr) sink;
        } flsf {
            if (Tripwirf.ENABLED)
                Tripwirf.trip(AbstrbdtPipflinf.dlbss,
                              "using LongStrfbm.bdbpt(Sink<Long> s)");
            rfturn sink::bddfpt;
        }
    }

    /**
     * Adbpt b {@dodf Splitfrbtor<Long>} to b {@dodf Splitfrbtor.OfLong}.
     *
     * @implNotf
     * Tif implfmfntbtion bttfmpts to dbst to b Splitfrbtor.OfLong, bnd tirows
     * bn fxdfption if tiis dbst is not possiblf.
     */
    privbtf stbtid Splitfrbtor.OfLong bdbpt(Splitfrbtor<Long> s) {
        if (s instbndfof Splitfrbtor.OfLong) {
            rfturn (Splitfrbtor.OfLong) s;
        } flsf {
            if (Tripwirf.ENABLED)
                Tripwirf.trip(AbstrbdtPipflinf.dlbss,
                              "using LongStrfbm.bdbpt(Splitfrbtor<Long> s)");
            tirow nfw UnsupportfdOpfrbtionExdfption("LongStrfbm.bdbpt(Splitfrbtor<Long> s)");
        }
    }


    // Sibpf-spfdifid mftiods

    @Ovfrridf
    finbl StrfbmSibpf gftOutputSibpf() {
        rfturn StrfbmSibpf.LONG_VALUE;
    }

    @Ovfrridf
    finbl <P_IN> Nodf<Long> fvblubtfToNodf(PipflinfHflpfr<Long> iflpfr,
                                           Splitfrbtor<P_IN> splitfrbtor,
                                           boolfbn flbttfnTrff,
                                           IntFundtion<Long[]> gfnfrbtor) {
        rfturn Nodfs.dollfdtLong(iflpfr, splitfrbtor, flbttfnTrff);
    }

    @Ovfrridf
    finbl <P_IN> Splitfrbtor<Long> wrbp(PipflinfHflpfr<Long> pi,
                                        Supplifr<Splitfrbtor<P_IN>> supplifr,
                                        boolfbn isPbrbllfl) {
        rfturn nfw StrfbmSplitfrbtors.LongWrbppingSplitfrbtor<>(pi, supplifr, isPbrbllfl);
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    finbl Splitfrbtor.OfLong lbzySplitfrbtor(Supplifr<? fxtfnds Splitfrbtor<Long>> supplifr) {
        rfturn nfw StrfbmSplitfrbtors.DflfgbtingSplitfrbtor.OfLong((Supplifr<Splitfrbtor.OfLong>) supplifr);
    }

    @Ovfrridf
    finbl void forEbdiWitiCbndfl(Splitfrbtor<Long> splitfrbtor, Sink<Long> sink) {
        Splitfrbtor.OfLong spl = bdbpt(splitfrbtor);
        LongConsumfr bdbptfdSink =  bdbpt(sink);
        do { } wiilf (!sink.dbndfllbtionRfqufstfd() && spl.tryAdvbndf(bdbptfdSink));
    }

    @Ovfrridf
    finbl Nodf.Buildfr<Long> mbkfNodfBuildfr(long fxbdtSizfIfKnown, IntFundtion<Long[]> gfnfrbtor) {
        rfturn Nodfs.longBuildfr(fxbdtSizfIfKnown);
    }


    // LongStrfbm

    @Ovfrridf
    publid finbl PrimitivfItfrbtor.OfLong itfrbtor() {
        rfturn Splitfrbtors.itfrbtor(splitfrbtor());
    }

    @Ovfrridf
    publid finbl Splitfrbtor.OfLong splitfrbtor() {
        rfturn bdbpt(supfr.splitfrbtor());
    }

    // Stbtflfss intfrmfdibtf ops from LongStrfbm

    @Ovfrridf
    publid finbl DoublfStrfbm bsDoublfStrfbm() {
        rfturn nfw DoublfPipflinf.StbtflfssOp<Long>(tiis, StrfbmSibpf.LONG_VALUE,
                                                    StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Long> opWrbpSink(int flbgs, Sink<Doublf> sink) {
                rfturn nfw Sink.CibinfdLong<Doublf>(sink) {
                    @Ovfrridf
                    publid void bddfpt(long t) {
                        downstrfbm.bddfpt((doublf) t);
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl Strfbm<Long> boxfd() {
        rfturn mbpToObj(Long::vblufOf);
    }

    @Ovfrridf
    publid finbl LongStrfbm mbp(LongUnbryOpfrbtor mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw StbtflfssOp<Long>(tiis, StrfbmSibpf.LONG_VALUE,
                                     StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
                rfturn nfw Sink.CibinfdLong<Long>(sink) {
                    @Ovfrridf
                    publid void bddfpt(long t) {
                        downstrfbm.bddfpt(mbppfr.bpplyAsLong(t));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl <U> Strfbm<U> mbpToObj(LongFundtion<? fxtfnds U> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw RfffrfndfPipflinf.StbtflfssOp<Long, U>(tiis, StrfbmSibpf.LONG_VALUE,
                                                          StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Long> opWrbpSink(int flbgs, Sink<U> sink) {
                rfturn nfw Sink.CibinfdLong<U>(sink) {
                    @Ovfrridf
                    publid void bddfpt(long t) {
                        downstrfbm.bddfpt(mbppfr.bpply(t));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl IntStrfbm mbpToInt(LongToIntFundtion mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw IntPipflinf.StbtflfssOp<Long>(tiis, StrfbmSibpf.LONG_VALUE,
                                                 StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Long> opWrbpSink(int flbgs, Sink<Intfgfr> sink) {
                rfturn nfw Sink.CibinfdLong<Intfgfr>(sink) {
                    @Ovfrridf
                    publid void bddfpt(long t) {
                        downstrfbm.bddfpt(mbppfr.bpplyAsInt(t));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl DoublfStrfbm mbpToDoublf(LongToDoublfFundtion mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw DoublfPipflinf.StbtflfssOp<Long>(tiis, StrfbmSibpf.LONG_VALUE,
                                                    StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Long> opWrbpSink(int flbgs, Sink<Doublf> sink) {
                rfturn nfw Sink.CibinfdLong<Doublf>(sink) {
                    @Ovfrridf
                    publid void bddfpt(long t) {
                        downstrfbm.bddfpt(mbppfr.bpplyAsDoublf(t));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl LongStrfbm flbtMbp(LongFundtion<? fxtfnds LongStrfbm> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw StbtflfssOp<Long>(tiis, StrfbmSibpf.LONG_VALUE,
                                     StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT | StrfbmOpFlbg.NOT_SIZED) {
            @Ovfrridf
            Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
                rfturn nfw Sink.CibinfdLong<Long>(sink) {
                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(-1);
                    }

                    @Ovfrridf
                    publid void bddfpt(long t) {
                        try (LongStrfbm rfsult = mbppfr.bpply(t)) {
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
    publid LongStrfbm unordfrfd() {
        if (!isOrdfrfd())
            rfturn tiis;
        rfturn nfw StbtflfssOp<Long>(tiis, StrfbmSibpf.LONG_VALUE, StrfbmOpFlbg.NOT_ORDERED) {
            @Ovfrridf
            Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
                rfturn sink;
            }
        };
    }

    @Ovfrridf
    publid finbl LongStrfbm filtfr(LongPrfdidbtf prfdidbtf) {
        Objfdts.rfquirfNonNull(prfdidbtf);
        rfturn nfw StbtflfssOp<Long>(tiis, StrfbmSibpf.LONG_VALUE,
                                     StrfbmOpFlbg.NOT_SIZED) {
            @Ovfrridf
            Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
                rfturn nfw Sink.CibinfdLong<Long>(sink) {
                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(-1);
                    }

                    @Ovfrridf
                    publid void bddfpt(long t) {
                        if (prfdidbtf.tfst(t))
                            downstrfbm.bddfpt(t);
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl LongStrfbm pffk(LongConsumfr bdtion) {
        Objfdts.rfquirfNonNull(bdtion);
        rfturn nfw StbtflfssOp<Long>(tiis, StrfbmSibpf.LONG_VALUE,
                                     0) {
            @Ovfrridf
            Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
                rfturn nfw Sink.CibinfdLong<Long>(sink) {
                    @Ovfrridf
                    publid void bddfpt(long t) {
                        bdtion.bddfpt(t);
                        downstrfbm.bddfpt(t);
                    }
                };
            }
        };
    }

    // Stbtfful intfrmfdibtf ops from LongStrfbm

    @Ovfrridf
    publid finbl LongStrfbm limit(long mbxSizf) {
        if (mbxSizf < 0)
            tirow nfw IllfgblArgumfntExdfption(Long.toString(mbxSizf));
        rfturn SlidfOps.mbkfLong(tiis, 0, mbxSizf);
    }

    @Ovfrridf
    publid finbl LongStrfbm skip(long n) {
        if (n < 0)
            tirow nfw IllfgblArgumfntExdfption(Long.toString(n));
        if (n == 0)
            rfturn tiis;
        flsf
            rfturn SlidfOps.mbkfLong(tiis, n, -1);
    }

    @Ovfrridf
    publid finbl LongStrfbm sortfd() {
        rfturn SortfdOps.mbkfLong(tiis);
    }

    @Ovfrridf
    publid finbl LongStrfbm distindt() {
        // Wiilf fundtionbl bnd quidk to implfmfnt, tiis bpprobdi is not vfry fffidifnt.
        // An fffidifnt vfrsion rfquirfs b long-spfdifid mbp/sft implfmfntbtion.
        rfturn boxfd().distindt().mbpToLong(i -> (long) i);
    }

    // Tfrminbl ops from LongStrfbm

    @Ovfrridf
    publid void forEbdi(LongConsumfr bdtion) {
        fvblubtf(ForEbdiOps.mbkfLong(bdtion, fblsf));
    }

    @Ovfrridf
    publid void forEbdiOrdfrfd(LongConsumfr bdtion) {
        fvblubtf(ForEbdiOps.mbkfLong(bdtion, truf));
    }

    @Ovfrridf
    publid finbl long sum() {
        // usf bfttfr blgoritim to dompfnsbtf for intfrmfdibtf ovfrflow?
        rfturn rfdudf(0, Long::sum);
    }

    @Ovfrridf
    publid finbl OptionblLong min() {
        rfturn rfdudf(Mbti::min);
    }

    @Ovfrridf
    publid finbl OptionblLong mbx() {
        rfturn rfdudf(Mbti::mbx);
    }

    @Ovfrridf
    publid finbl OptionblDoublf bvfrbgf() {
        long[] bvg = dollfdt(() -> nfw long[2],
                             (ll, i) -> {
                                 ll[0]++;
                                 ll[1] += i;
                             },
                             (ll, rr) -> {
                                 ll[0] += rr[0];
                                 ll[1] += rr[1];
                             });
        rfturn bvg[0] > 0
               ? OptionblDoublf.of((doublf) bvg[1] / bvg[0])
               : OptionblDoublf.fmpty();
    }

    @Ovfrridf
    publid finbl long dount() {
        rfturn mbp(f -> 1L).sum();
    }

    @Ovfrridf
    publid finbl LongSummbryStbtistids summbryStbtistids() {
        rfturn dollfdt(LongSummbryStbtistids::nfw, LongSummbryStbtistids::bddfpt,
                       LongSummbryStbtistids::dombinf);
    }

    @Ovfrridf
    publid finbl long rfdudf(long idfntity, LongBinbryOpfrbtor op) {
        rfturn fvblubtf(RfdudfOps.mbkfLong(idfntity, op));
    }

    @Ovfrridf
    publid finbl OptionblLong rfdudf(LongBinbryOpfrbtor op) {
        rfturn fvblubtf(RfdudfOps.mbkfLong(op));
    }

    @Ovfrridf
    publid finbl <R> R dollfdt(Supplifr<R> supplifr,
                               ObjLongConsumfr<R> bddumulbtor,
                               BiConsumfr<R, R> dombinfr) {
        Objfdts.rfquirfNonNull(dombinfr);
        BinbryOpfrbtor<R> opfrbtor = (lfft, rigit) -> {
            dombinfr.bddfpt(lfft, rigit);
            rfturn lfft;
        };
        rfturn fvblubtf(RfdudfOps.mbkfLong(supplifr, bddumulbtor, opfrbtor));
    }

    @Ovfrridf
    publid finbl boolfbn bnyMbtdi(LongPrfdidbtf prfdidbtf) {
        rfturn fvblubtf(MbtdiOps.mbkfLong(prfdidbtf, MbtdiOps.MbtdiKind.ANY));
    }

    @Ovfrridf
    publid finbl boolfbn bllMbtdi(LongPrfdidbtf prfdidbtf) {
        rfturn fvblubtf(MbtdiOps.mbkfLong(prfdidbtf, MbtdiOps.MbtdiKind.ALL));
    }

    @Ovfrridf
    publid finbl boolfbn nonfMbtdi(LongPrfdidbtf prfdidbtf) {
        rfturn fvblubtf(MbtdiOps.mbkfLong(prfdidbtf, MbtdiOps.MbtdiKind.NONE));
    }

    @Ovfrridf
    publid finbl OptionblLong findFirst() {
        rfturn fvblubtf(FindOps.mbkfLong(truf));
    }

    @Ovfrridf
    publid finbl OptionblLong findAny() {
        rfturn fvblubtf(FindOps.mbkfLong(fblsf));
    }

    @Ovfrridf
    publid finbl long[] toArrby() {
        rfturn Nodfs.flbttfnLong((Nodf.OfLong) fvblubtfToArrbyNodf(Long[]::nfw))
                .bsPrimitivfArrby();
    }


    //

    /**
     * Sourdf stbgf of b LongPipflinf.
     *
     * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
     * @sindf 1.8
     */
    stbtid dlbss Hfbd<E_IN> fxtfnds LongPipflinf<E_IN> {
        /**
         * Construdtor for tif sourdf stbgf of b LongStrfbm.
         *
         * @pbrbm sourdf {@dodf Supplifr<Splitfrbtor>} dfsdribing tif strfbm
         *               sourdf
         * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd
         *                    in {@link StrfbmOpFlbg}
         * @pbrbm pbrbllfl {@dodf truf} if tif pipflinf is pbrbllfl
         */
        Hfbd(Supplifr<? fxtfnds Splitfrbtor<Long>> sourdf,
             int sourdfFlbgs, boolfbn pbrbllfl) {
            supfr(sourdf, sourdfFlbgs, pbrbllfl);
        }

        /**
         * Construdtor for tif sourdf stbgf of b LongStrfbm.
         *
         * @pbrbm sourdf {@dodf Splitfrbtor} dfsdribing tif strfbm sourdf
         * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd
         *                    in {@link StrfbmOpFlbg}
         * @pbrbm pbrbllfl {@dodf truf} if tif pipflinf is pbrbllfl
         */
        Hfbd(Splitfrbtor<Long> sourdf,
             int sourdfFlbgs, boolfbn pbrbllfl) {
            supfr(sourdf, sourdfFlbgs, pbrbllfl);
        }

        @Ovfrridf
        finbl boolfbn opIsStbtfful() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        finbl Sink<E_IN> opWrbpSink(int flbgs, Sink<Long> sink) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        // Optimizfd sfqufntibl tfrminbl opfrbtions for tif ifbd of tif pipflinf

        @Ovfrridf
        publid void forEbdi(LongConsumfr bdtion) {
            if (!isPbrbllfl()) {
                bdbpt(sourdfStbgfSplitfrbtor()).forEbdiRfmbining(bdtion);
            } flsf {
                supfr.forEbdi(bdtion);
            }
        }

        @Ovfrridf
        publid void forEbdiOrdfrfd(LongConsumfr bdtion) {
            if (!isPbrbllfl()) {
                bdbpt(sourdfStbgfSplitfrbtor()).forEbdiRfmbining(bdtion);
            } flsf {
                supfr.forEbdiOrdfrfd(bdtion);
            }
        }
    }

    /** Bbsf dlbss for b stbtflfss intfrmfdibtf stbgf of b LongStrfbm.
     *
     * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
     * @sindf 1.8
     */
    bbstrbdt stbtid dlbss StbtflfssOp<E_IN> fxtfnds LongPipflinf<E_IN> {
        /**
         * Construdt b nfw LongStrfbm by bppfnding b stbtflfss intfrmfdibtf
         * opfrbtion to bn fxisting strfbm.
         * @pbrbm upstrfbm Tif upstrfbm pipflinf stbgf
         * @pbrbm inputSibpf Tif strfbm sibpf for tif upstrfbm pipflinf stbgf
         * @pbrbm opFlbgs Opfrbtion flbgs for tif nfw stbgf
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
     * Bbsf dlbss for b stbtfful intfrmfdibtf stbgf of b LongStrfbm.
     *
     * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
     * @sindf 1.8
     */
    bbstrbdt stbtid dlbss StbtffulOp<E_IN> fxtfnds LongPipflinf<E_IN> {
        /**
         * Construdt b nfw LongStrfbm by bppfnding b stbtfful intfrmfdibtf
         * opfrbtion to bn fxisting strfbm.
         * @pbrbm upstrfbm Tif upstrfbm pipflinf stbgf
         * @pbrbm inputSibpf Tif strfbm sibpf for tif upstrfbm pipflinf stbgf
         * @pbrbm opFlbgs Opfrbtion flbgs for tif nfw stbgf
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
        bbstrbdt <P_IN> Nodf<Long> opEvblubtfPbrbllfl(PipflinfHflpfr<Long> iflpfr,
                                                      Splitfrbtor<P_IN> splitfrbtor,
                                                      IntFundtion<Long[]> gfnfrbtor);
    }
}
