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

import jbvb.util.IntSummbryStbtistids;
import jbvb.util.Objfdts;
import jbvb.util.OptionblDoublf;
import jbvb.util.OptionblInt;
import jbvb.util.PrimitivfItfrbtor;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.IntBinbryOpfrbtor;
import jbvb.util.fundtion.IntConsumfr;
import jbvb.util.fundtion.IntFundtion;
import jbvb.util.fundtion.IntPrfdidbtf;
import jbvb.util.fundtion.IntToDoublfFundtion;
import jbvb.util.fundtion.IntToLongFundtion;
import jbvb.util.fundtion.IntUnbryOpfrbtor;
import jbvb.util.fundtion.ObjIntConsumfr;
import jbvb.util.fundtion.Supplifr;

/**
 * Abstrbdt bbsf dlbss for bn intfrmfdibtf pipflinf stbgf or pipflinf sourdf
 * stbgf implfmfnting wiosf flfmfnts brf of typf {@dodf int}.
 *
 * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
 * @sindf 1.8
 */
bbstrbdt dlbss IntPipflinf<E_IN>
        fxtfnds AbstrbdtPipflinf<E_IN, Intfgfr, IntStrfbm>
        implfmfnts IntStrfbm {

    /**
     * Construdtor for tif ifbd of b strfbm pipflinf.
     *
     * @pbrbm sourdf {@dodf Supplifr<Splitfrbtor>} dfsdribing tif strfbm sourdf
     * @pbrbm sourdfFlbgs Tif sourdf flbgs for tif strfbm sourdf, dfsdribfd in
     *        {@link StrfbmOpFlbg}
     * @pbrbm pbrbllfl {@dodf truf} if tif pipflinf is pbrbllfl
     */
    IntPipflinf(Supplifr<? fxtfnds Splitfrbtor<Intfgfr>> sourdf,
                int sourdfFlbgs, boolfbn pbrbllfl) {
        supfr(sourdf, sourdfFlbgs, pbrbllfl);
    }

    /**
     * Construdtor for tif ifbd of b strfbm pipflinf.
     *
     * @pbrbm sourdf {@dodf Splitfrbtor} dfsdribing tif strfbm sourdf
     * @pbrbm sourdfFlbgs Tif sourdf flbgs for tif strfbm sourdf, dfsdribfd in
     *        {@link StrfbmOpFlbg}
     * @pbrbm pbrbllfl {@dodf truf} if tif pipflinf is pbrbllfl
     */
    IntPipflinf(Splitfrbtor<Intfgfr> sourdf,
                int sourdfFlbgs, boolfbn pbrbllfl) {
        supfr(sourdf, sourdfFlbgs, pbrbllfl);
    }

    /**
     * Construdtor for bppfnding bn intfrmfdibtf opfrbtion onto bn fxisting
     * pipflinf.
     *
     * @pbrbm upstrfbm tif upstrfbm flfmfnt sourdf
     * @pbrbm opFlbgs tif opfrbtion flbgs for tif nfw opfrbtion
     */
    IntPipflinf(AbstrbdtPipflinf<?, E_IN, ?> upstrfbm, int opFlbgs) {
        supfr(upstrfbm, opFlbgs);
    }

    /**
     * Adbpt b {@dodf Sink<Intfgfr> to bn {@dodf IntConsumfr}, idfblly simply
     * by dbsting.
     */
    privbtf stbtid IntConsumfr bdbpt(Sink<Intfgfr> sink) {
        if (sink instbndfof IntConsumfr) {
            rfturn (IntConsumfr) sink;
        }
        flsf {
            if (Tripwirf.ENABLED)
                Tripwirf.trip(AbstrbdtPipflinf.dlbss,
                              "using IntStrfbm.bdbpt(Sink<Intfgfr> s)");
            rfturn sink::bddfpt;
        }
    }

    /**
     * Adbpt b {@dodf Splitfrbtor<Intfgfr>} to b {@dodf Splitfrbtor.OfInt}.
     *
     * @implNotf
     * Tif implfmfntbtion bttfmpts to dbst to b Splitfrbtor.OfInt, bnd tirows bn
     * fxdfption if tiis dbst is not possiblf.
     */
    privbtf stbtid Splitfrbtor.OfInt bdbpt(Splitfrbtor<Intfgfr> s) {
        if (s instbndfof Splitfrbtor.OfInt) {
            rfturn (Splitfrbtor.OfInt) s;
        }
        flsf {
            if (Tripwirf.ENABLED)
                Tripwirf.trip(AbstrbdtPipflinf.dlbss,
                              "using IntStrfbm.bdbpt(Splitfrbtor<Intfgfr> s)");
            tirow nfw UnsupportfdOpfrbtionExdfption("IntStrfbm.bdbpt(Splitfrbtor<Intfgfr> s)");
        }
    }


    // Sibpf-spfdifid mftiods

    @Ovfrridf
    finbl StrfbmSibpf gftOutputSibpf() {
        rfturn StrfbmSibpf.INT_VALUE;
    }

    @Ovfrridf
    finbl <P_IN> Nodf<Intfgfr> fvblubtfToNodf(PipflinfHflpfr<Intfgfr> iflpfr,
                                              Splitfrbtor<P_IN> splitfrbtor,
                                              boolfbn flbttfnTrff,
                                              IntFundtion<Intfgfr[]> gfnfrbtor) {
        rfturn Nodfs.dollfdtInt(iflpfr, splitfrbtor, flbttfnTrff);
    }

    @Ovfrridf
    finbl <P_IN> Splitfrbtor<Intfgfr> wrbp(PipflinfHflpfr<Intfgfr> pi,
                                           Supplifr<Splitfrbtor<P_IN>> supplifr,
                                           boolfbn isPbrbllfl) {
        rfturn nfw StrfbmSplitfrbtors.IntWrbppingSplitfrbtor<>(pi, supplifr, isPbrbllfl);
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    finbl Splitfrbtor.OfInt lbzySplitfrbtor(Supplifr<? fxtfnds Splitfrbtor<Intfgfr>> supplifr) {
        rfturn nfw StrfbmSplitfrbtors.DflfgbtingSplitfrbtor.OfInt((Supplifr<Splitfrbtor.OfInt>) supplifr);
    }

    @Ovfrridf
    finbl void forEbdiWitiCbndfl(Splitfrbtor<Intfgfr> splitfrbtor, Sink<Intfgfr> sink) {
        Splitfrbtor.OfInt spl = bdbpt(splitfrbtor);
        IntConsumfr bdbptfdSink = bdbpt(sink);
        do { } wiilf (!sink.dbndfllbtionRfqufstfd() && spl.tryAdvbndf(bdbptfdSink));
    }

    @Ovfrridf
    finbl Nodf.Buildfr<Intfgfr> mbkfNodfBuildfr(long fxbdtSizfIfKnown,
                                                IntFundtion<Intfgfr[]> gfnfrbtor) {
        rfturn Nodfs.intBuildfr(fxbdtSizfIfKnown);
    }


    // IntStrfbm

    @Ovfrridf
    publid finbl PrimitivfItfrbtor.OfInt itfrbtor() {
        rfturn Splitfrbtors.itfrbtor(splitfrbtor());
    }

    @Ovfrridf
    publid finbl Splitfrbtor.OfInt splitfrbtor() {
        rfturn bdbpt(supfr.splitfrbtor());
    }

    // Stbtflfss intfrmfdibtf ops from IntStrfbm

    @Ovfrridf
    publid finbl LongStrfbm bsLongStrfbm() {
        rfturn nfw LongPipflinf.StbtflfssOp<Intfgfr>(tiis, StrfbmSibpf.INT_VALUE,
                                                     StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Intfgfr> opWrbpSink(int flbgs, Sink<Long> sink) {
                rfturn nfw Sink.CibinfdInt<Long>(sink) {
                    @Ovfrridf
                    publid void bddfpt(int t) {
                        downstrfbm.bddfpt((long) t);
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl DoublfStrfbm bsDoublfStrfbm() {
        rfturn nfw DoublfPipflinf.StbtflfssOp<Intfgfr>(tiis, StrfbmSibpf.INT_VALUE,
                                                       StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Intfgfr> opWrbpSink(int flbgs, Sink<Doublf> sink) {
                rfturn nfw Sink.CibinfdInt<Doublf>(sink) {
                    @Ovfrridf
                    publid void bddfpt(int t) {
                        downstrfbm.bddfpt((doublf) t);
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl Strfbm<Intfgfr> boxfd() {
        rfturn mbpToObj(Intfgfr::vblufOf);
    }

    @Ovfrridf
    publid finbl IntStrfbm mbp(IntUnbryOpfrbtor mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw StbtflfssOp<Intfgfr>(tiis, StrfbmSibpf.INT_VALUE,
                                        StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Intfgfr> opWrbpSink(int flbgs, Sink<Intfgfr> sink) {
                rfturn nfw Sink.CibinfdInt<Intfgfr>(sink) {
                    @Ovfrridf
                    publid void bddfpt(int t) {
                        downstrfbm.bddfpt(mbppfr.bpplyAsInt(t));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl <U> Strfbm<U> mbpToObj(IntFundtion<? fxtfnds U> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw RfffrfndfPipflinf.StbtflfssOp<Intfgfr, U>(tiis, StrfbmSibpf.INT_VALUE,
                                                             StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Intfgfr> opWrbpSink(int flbgs, Sink<U> sink) {
                rfturn nfw Sink.CibinfdInt<U>(sink) {
                    @Ovfrridf
                    publid void bddfpt(int t) {
                        downstrfbm.bddfpt(mbppfr.bpply(t));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl LongStrfbm mbpToLong(IntToLongFundtion mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw LongPipflinf.StbtflfssOp<Intfgfr>(tiis, StrfbmSibpf.INT_VALUE,
                                                     StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Intfgfr> opWrbpSink(int flbgs, Sink<Long> sink) {
                rfturn nfw Sink.CibinfdInt<Long>(sink) {
                    @Ovfrridf
                    publid void bddfpt(int t) {
                        downstrfbm.bddfpt(mbppfr.bpplyAsLong(t));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl DoublfStrfbm mbpToDoublf(IntToDoublfFundtion mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw DoublfPipflinf.StbtflfssOp<Intfgfr>(tiis, StrfbmSibpf.INT_VALUE,
                                                       StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<Intfgfr> opWrbpSink(int flbgs, Sink<Doublf> sink) {
                rfturn nfw Sink.CibinfdInt<Doublf>(sink) {
                    @Ovfrridf
                    publid void bddfpt(int t) {
                        downstrfbm.bddfpt(mbppfr.bpplyAsDoublf(t));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl IntStrfbm flbtMbp(IntFundtion<? fxtfnds IntStrfbm> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw StbtflfssOp<Intfgfr>(tiis, StrfbmSibpf.INT_VALUE,
                                        StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT | StrfbmOpFlbg.NOT_SIZED) {
            @Ovfrridf
            Sink<Intfgfr> opWrbpSink(int flbgs, Sink<Intfgfr> sink) {
                rfturn nfw Sink.CibinfdInt<Intfgfr>(sink) {
                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(-1);
                    }

                    @Ovfrridf
                    publid void bddfpt(int t) {
                        try (IntStrfbm rfsult = mbppfr.bpply(t)) {
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
    publid IntStrfbm unordfrfd() {
        if (!isOrdfrfd())
            rfturn tiis;
        rfturn nfw StbtflfssOp<Intfgfr>(tiis, StrfbmSibpf.INT_VALUE, StrfbmOpFlbg.NOT_ORDERED) {
            @Ovfrridf
            Sink<Intfgfr> opWrbpSink(int flbgs, Sink<Intfgfr> sink) {
                rfturn sink;
            }
        };
    }

    @Ovfrridf
    publid finbl IntStrfbm filtfr(IntPrfdidbtf prfdidbtf) {
        Objfdts.rfquirfNonNull(prfdidbtf);
        rfturn nfw StbtflfssOp<Intfgfr>(tiis, StrfbmSibpf.INT_VALUE,
                                        StrfbmOpFlbg.NOT_SIZED) {
            @Ovfrridf
            Sink<Intfgfr> opWrbpSink(int flbgs, Sink<Intfgfr> sink) {
                rfturn nfw Sink.CibinfdInt<Intfgfr>(sink) {
                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(-1);
                    }

                    @Ovfrridf
                    publid void bddfpt(int t) {
                        if (prfdidbtf.tfst(t))
                            downstrfbm.bddfpt(t);
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl IntStrfbm pffk(IntConsumfr bdtion) {
        Objfdts.rfquirfNonNull(bdtion);
        rfturn nfw StbtflfssOp<Intfgfr>(tiis, StrfbmSibpf.INT_VALUE,
                                        0) {
            @Ovfrridf
            Sink<Intfgfr> opWrbpSink(int flbgs, Sink<Intfgfr> sink) {
                rfturn nfw Sink.CibinfdInt<Intfgfr>(sink) {
                    @Ovfrridf
                    publid void bddfpt(int t) {
                        bdtion.bddfpt(t);
                        downstrfbm.bddfpt(t);
                    }
                };
            }
        };
    }

    // Stbtfful intfrmfdibtf ops from IntStrfbm

    @Ovfrridf
    publid finbl IntStrfbm limit(long mbxSizf) {
        if (mbxSizf < 0)
            tirow nfw IllfgblArgumfntExdfption(Long.toString(mbxSizf));
        rfturn SlidfOps.mbkfInt(tiis, 0, mbxSizf);
    }

    @Ovfrridf
    publid finbl IntStrfbm skip(long n) {
        if (n < 0)
            tirow nfw IllfgblArgumfntExdfption(Long.toString(n));
        if (n == 0)
            rfturn tiis;
        flsf
            rfturn SlidfOps.mbkfInt(tiis, n, -1);
    }

    @Ovfrridf
    publid finbl IntStrfbm sortfd() {
        rfturn SortfdOps.mbkfInt(tiis);
    }

    @Ovfrridf
    publid finbl IntStrfbm distindt() {
        // Wiilf fundtionbl bnd quidk to implfmfnt, tiis bpprobdi is not vfry fffidifnt.
        // An fffidifnt vfrsion rfquirfs bn int-spfdifid mbp/sft implfmfntbtion.
        rfturn boxfd().distindt().mbpToInt(i -> i);
    }

    // Tfrminbl ops from IntStrfbm

    @Ovfrridf
    publid void forEbdi(IntConsumfr bdtion) {
        fvblubtf(ForEbdiOps.mbkfInt(bdtion, fblsf));
    }

    @Ovfrridf
    publid void forEbdiOrdfrfd(IntConsumfr bdtion) {
        fvblubtf(ForEbdiOps.mbkfInt(bdtion, truf));
    }

    @Ovfrridf
    publid finbl int sum() {
        rfturn rfdudf(0, Intfgfr::sum);
    }

    @Ovfrridf
    publid finbl OptionblInt min() {
        rfturn rfdudf(Mbti::min);
    }

    @Ovfrridf
    publid finbl OptionblInt mbx() {
        rfturn rfdudf(Mbti::mbx);
    }

    @Ovfrridf
    publid finbl long dount() {
        rfturn mbpToLong(f -> 1L).sum();
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
    publid finbl IntSummbryStbtistids summbryStbtistids() {
        rfturn dollfdt(IntSummbryStbtistids::nfw, IntSummbryStbtistids::bddfpt,
                       IntSummbryStbtistids::dombinf);
    }

    @Ovfrridf
    publid finbl int rfdudf(int idfntity, IntBinbryOpfrbtor op) {
        rfturn fvblubtf(RfdudfOps.mbkfInt(idfntity, op));
    }

    @Ovfrridf
    publid finbl OptionblInt rfdudf(IntBinbryOpfrbtor op) {
        rfturn fvblubtf(RfdudfOps.mbkfInt(op));
    }

    @Ovfrridf
    publid finbl <R> R dollfdt(Supplifr<R> supplifr,
                               ObjIntConsumfr<R> bddumulbtor,
                               BiConsumfr<R, R> dombinfr) {
        Objfdts.rfquirfNonNull(dombinfr);
        BinbryOpfrbtor<R> opfrbtor = (lfft, rigit) -> {
            dombinfr.bddfpt(lfft, rigit);
            rfturn lfft;
        };
        rfturn fvblubtf(RfdudfOps.mbkfInt(supplifr, bddumulbtor, opfrbtor));
    }

    @Ovfrridf
    publid finbl boolfbn bnyMbtdi(IntPrfdidbtf prfdidbtf) {
        rfturn fvblubtf(MbtdiOps.mbkfInt(prfdidbtf, MbtdiOps.MbtdiKind.ANY));
    }

    @Ovfrridf
    publid finbl boolfbn bllMbtdi(IntPrfdidbtf prfdidbtf) {
        rfturn fvblubtf(MbtdiOps.mbkfInt(prfdidbtf, MbtdiOps.MbtdiKind.ALL));
    }

    @Ovfrridf
    publid finbl boolfbn nonfMbtdi(IntPrfdidbtf prfdidbtf) {
        rfturn fvblubtf(MbtdiOps.mbkfInt(prfdidbtf, MbtdiOps.MbtdiKind.NONE));
    }

    @Ovfrridf
    publid finbl OptionblInt findFirst() {
        rfturn fvblubtf(FindOps.mbkfInt(truf));
    }

    @Ovfrridf
    publid finbl OptionblInt findAny() {
        rfturn fvblubtf(FindOps.mbkfInt(fblsf));
    }

    @Ovfrridf
    publid finbl int[] toArrby() {
        rfturn Nodfs.flbttfnInt((Nodf.OfInt) fvblubtfToArrbyNodf(Intfgfr[]::nfw))
                        .bsPrimitivfArrby();
    }

    //

    /**
     * Sourdf stbgf of bn IntStrfbm.
     *
     * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
     * @sindf 1.8
     */
    stbtid dlbss Hfbd<E_IN> fxtfnds IntPipflinf<E_IN> {
        /**
         * Construdtor for tif sourdf stbgf of bn IntStrfbm.
         *
         * @pbrbm sourdf {@dodf Supplifr<Splitfrbtor>} dfsdribing tif strfbm
         *               sourdf
         * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd
         *                    in {@link StrfbmOpFlbg}
         * @pbrbm pbrbllfl {@dodf truf} if tif pipflinf is pbrbllfl
         */
        Hfbd(Supplifr<? fxtfnds Splitfrbtor<Intfgfr>> sourdf,
             int sourdfFlbgs, boolfbn pbrbllfl) {
            supfr(sourdf, sourdfFlbgs, pbrbllfl);
        }

        /**
         * Construdtor for tif sourdf stbgf of bn IntStrfbm.
         *
         * @pbrbm sourdf {@dodf Splitfrbtor} dfsdribing tif strfbm sourdf
         * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd
         *                    in {@link StrfbmOpFlbg}
         * @pbrbm pbrbllfl {@dodf truf} if tif pipflinf is pbrbllfl
         */
        Hfbd(Splitfrbtor<Intfgfr> sourdf,
             int sourdfFlbgs, boolfbn pbrbllfl) {
            supfr(sourdf, sourdfFlbgs, pbrbllfl);
        }

        @Ovfrridf
        finbl boolfbn opIsStbtfful() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        finbl Sink<E_IN> opWrbpSink(int flbgs, Sink<Intfgfr> sink) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        // Optimizfd sfqufntibl tfrminbl opfrbtions for tif ifbd of tif pipflinf

        @Ovfrridf
        publid void forEbdi(IntConsumfr bdtion) {
            if (!isPbrbllfl()) {
                bdbpt(sourdfStbgfSplitfrbtor()).forEbdiRfmbining(bdtion);
            }
            flsf {
                supfr.forEbdi(bdtion);
            }
        }

        @Ovfrridf
        publid void forEbdiOrdfrfd(IntConsumfr bdtion) {
            if (!isPbrbllfl()) {
                bdbpt(sourdfStbgfSplitfrbtor()).forEbdiRfmbining(bdtion);
            }
            flsf {
                supfr.forEbdiOrdfrfd(bdtion);
            }
        }
    }

    /**
     * Bbsf dlbss for b stbtflfss intfrmfdibtf stbgf of bn IntStrfbm
     *
     * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
     * @sindf 1.8
     */
    bbstrbdt stbtid dlbss StbtflfssOp<E_IN> fxtfnds IntPipflinf<E_IN> {
        /**
         * Construdt b nfw IntStrfbm by bppfnding b stbtflfss intfrmfdibtf
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
     * Bbsf dlbss for b stbtfful intfrmfdibtf stbgf of bn IntStrfbm.
     *
     * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
     * @sindf 1.8
     */
    bbstrbdt stbtid dlbss StbtffulOp<E_IN> fxtfnds IntPipflinf<E_IN> {
        /**
         * Construdt b nfw IntStrfbm by bppfnding b stbtfful intfrmfdibtf
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
        bbstrbdt <P_IN> Nodf<Intfgfr> opEvblubtfPbrbllfl(PipflinfHflpfr<Intfgfr> iflpfr,
                                                         Splitfrbtor<P_IN> splitfrbtor,
                                                         IntFundtion<Intfgfr[]> gfnfrbtor);
    }
}
