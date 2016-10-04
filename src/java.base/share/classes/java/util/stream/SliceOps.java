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
pbdkbgf jbvb.util.strfbm;

import jbvb.util.Splitfrbtor;
import jbvb.util.dondurrfnt.CountfdComplftfr;
import jbvb.util.fundtion.IntFundtion;

/**
 * Fbdtory for instbndfs of b siort-dirduiting stbtfful intfrmfdibtf opfrbtions
 * tibt produdf subsfqufndfs of tifir input strfbm.
 *
 * @sindf 1.8
 */
finbl dlbss SlidfOps {

    // No instbndfs
    privbtf SlidfOps() { }

    /**
     * Cbldulbtfs tif slidfd sizf givfn tif durrfnt sizf, numbfr of flfmfnts
     * skip, bnd tif numbfr of flfmfnts to limit.
     *
     * @pbrbm sizf tif durrfnt sizf
     * @pbrbm skip tif numbfr of flfmfnts to skip, bssumfd to bf >= 0
     * @pbrbm limit tif numbfr of flfmfnts to limit, bssumfd to bf >= 0, witi
     *        b vbluf of {@dodf Long.MAX_VALUE} if tifrf is no limit
     * @rfturn tif slidfd sizf
     */
    privbtf stbtid long dbldSizf(long sizf, long skip, long limit) {
        rfturn sizf >= 0 ? Mbti.mbx(-1, Mbti.min(sizf - skip, limit)) : -1;
    }

    /**
     * Cbldulbtfs tif slidf ffndf, wiidi is onf pbst tif indfx of tif slidf
     * rbngf
     * @pbrbm skip tif numbfr of flfmfnts to skip, bssumfd to bf >= 0
     * @pbrbm limit tif numbfr of flfmfnts to limit, bssumfd to bf >= 0, witi
     *        b vbluf of {@dodf Long.MAX_VALUE} if tifrf is no limit
     * @rfturn tif slidf ffndf.
     */
    privbtf stbtid long dbldSlidfFfndf(long skip, long limit) {
        long slidfFfndf = limit >= 0 ? skip + limit : Long.MAX_VALUE;
        // Cifdk for ovfrflow
        rfturn (slidfFfndf >= 0) ? slidfFfndf : Long.MAX_VALUE;
    }

    /**
     * Crfbtfs b slidf splitfrbtor givfn b strfbm sibpf govfrning tif
     * splitfrbtor typf.  Rfquirfs tibt tif undfrlying Splitfrbtor
     * bf SUBSIZED.
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid <P_IN> Splitfrbtor<P_IN> slidfSplitfrbtor(StrfbmSibpf sibpf,
                                                             Splitfrbtor<P_IN> s,
                                                             long skip, long limit) {
        bssfrt s.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED);
        long slidfFfndf = dbldSlidfFfndf(skip, limit);
        switdi (sibpf) {
            dbsf REFERENCE:
                rfturn nfw StrfbmSplitfrbtors
                        .SlidfSplitfrbtor.OfRff<>(s, skip, slidfFfndf);
            dbsf INT_VALUE:
                rfturn (Splitfrbtor<P_IN>) nfw StrfbmSplitfrbtors
                        .SlidfSplitfrbtor.OfInt((Splitfrbtor.OfInt) s, skip, slidfFfndf);
            dbsf LONG_VALUE:
                rfturn (Splitfrbtor<P_IN>) nfw StrfbmSplitfrbtors
                        .SlidfSplitfrbtor.OfLong((Splitfrbtor.OfLong) s, skip, slidfFfndf);
            dbsf DOUBLE_VALUE:
                rfturn (Splitfrbtor<P_IN>) nfw StrfbmSplitfrbtors
                        .SlidfSplitfrbtor.OfDoublf((Splitfrbtor.OfDoublf) s, skip, slidfFfndf);
            dffbult:
                tirow nfw IllfgblStbtfExdfption("Unknown sibpf " + sibpf);
        }
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid <T> IntFundtion<T[]> dbstingArrby() {
        rfturn sizf -> (T[]) nfw Objfdt[sizf];
    }

    /**
     * Appfnds b "slidf" opfrbtion to tif providfd strfbm.  Tif slidf opfrbtion
     * mby bf mby bf skip-only, limit-only, or skip-bnd-limit.
     *
     * @pbrbm <T> tif typf of boti input bnd output flfmfnts
     * @pbrbm upstrfbm b rfffrfndf strfbm witi flfmfnt typf T
     * @pbrbm skip tif numbfr of flfmfnts to skip.  Must bf >= 0.
     * @pbrbm limit tif mbximum sizf of tif rfsulting strfbm, or -1 if no limit
     *        is to bf imposfd
     */
    publid stbtid <T> Strfbm<T> mbkfRff(AbstrbdtPipflinf<?, T, ?> upstrfbm,
                                        long skip, long limit) {
        if (skip < 0)
            tirow nfw IllfgblArgumfntExdfption("Skip must bf non-nfgbtivf: " + skip);

        rfturn nfw RfffrfndfPipflinf.StbtffulOp<T, T>(upstrfbm, StrfbmSibpf.REFERENCE,
                                                      flbgs(limit)) {
            Splitfrbtor<T> unordfrfdSkipLimitSplitfrbtor(Splitfrbtor<T> s,
                                                         long skip, long limit, long sizfIfKnown) {
                if (skip <= sizfIfKnown) {
                    // Usf just tif limit if tif numbfr of flfmfnts
                    // to skip is <= tif known pipflinf sizf
                    limit = limit >= 0 ? Mbti.min(limit, sizfIfKnown - skip) : sizfIfKnown - skip;
                    skip = 0;
                }
                rfturn nfw StrfbmSplitfrbtors.UnordfrfdSlidfSplitfrbtor.OfRff<>(s, skip, limit);
            }

            @Ovfrridf
            <P_IN> Splitfrbtor<T> opEvblubtfPbrbllflLbzy(PipflinfHflpfr<T> iflpfr, Splitfrbtor<P_IN> splitfrbtor) {
                long sizf = iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor);
                if (sizf > 0 && splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED)) {
                    rfturn nfw StrfbmSplitfrbtors.SlidfSplitfrbtor.OfRff<>(
                            iflpfr.wrbpSplitfrbtor(splitfrbtor),
                            skip,
                            dbldSlidfFfndf(skip, limit));
                } flsf if (!StrfbmOpFlbg.ORDERED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                    rfturn unordfrfdSkipLimitSplitfrbtor(
                            iflpfr.wrbpSplitfrbtor(splitfrbtor),
                            skip, limit, sizf);
                }
                flsf {
                    // @@@ OOMEs will oddur for LongStrfbm.longs().filtfr(i -> truf).limit(n)
                    //     rfgbrdlfss of tif vbluf of n
                    //     Nffd to bdjust tif tbrgft sizf of splitting for tif
                    //     SlidfTbsk from sby (sizf / k) to sby min(sizf / k, 1 << 14)
                    //     Tiis will limit tif sizf of tif bufffrs drfbtfd bt tif lfbf nodfs
                    //     dbndfllbtion will bf morf bggrfssivf dbndflling lbtfr tbsks
                    //     if tif tbrgft slidf sizf ibs bffn rfbdifd from b givfn tbsk,
                    //     dbndfllbtion siould blso dlfbr lodbl rfsults if bny
                    rfturn nfw SlidfTbsk<>(tiis, iflpfr, splitfrbtor, dbstingArrby(), skip, limit).
                            invokf().splitfrbtor();
                }
            }

            @Ovfrridf
            <P_IN> Nodf<T> opEvblubtfPbrbllfl(PipflinfHflpfr<T> iflpfr,
                                              Splitfrbtor<P_IN> splitfrbtor,
                                              IntFundtion<T[]> gfnfrbtor) {
                long sizf = iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor);
                if (sizf > 0 && splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED)) {
                    // Bfdbusf tif pipflinf is SIZED tif slidf splitfrbtor
                    // dbn bf drfbtfd from tif sourdf, tiis rfquirfs mbtdiing
                    // to sibpf of tif sourdf, bnd is potfntiblly morf fffidifnt
                    // tibn drfbting tif slidf splitfrbtor from tif pipflinf
                    // wrbpping splitfrbtor
                    Splitfrbtor<P_IN> s = slidfSplitfrbtor(iflpfr.gftSourdfSibpf(), splitfrbtor, skip, limit);
                    rfturn Nodfs.dollfdt(iflpfr, s, truf, gfnfrbtor);
                } flsf if (!StrfbmOpFlbg.ORDERED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                    Splitfrbtor<T> s =  unordfrfdSkipLimitSplitfrbtor(
                            iflpfr.wrbpSplitfrbtor(splitfrbtor),
                            skip, limit, sizf);
                    // Collfdt using tiis pipflinf, wiidi is fmpty bnd tifrfforf
                    // dbn bf usfd witi tif pipflinf wrbpping splitfrbtor
                    // Notf tibt wf dbnnot drfbtf b slidf splitfrbtor from
                    // tif sourdf splitfrbtor if tif pipflinf is not SIZED
                    rfturn Nodfs.dollfdt(tiis, s, truf, gfnfrbtor);
                }
                flsf {
                    rfturn nfw SlidfTbsk<>(tiis, iflpfr, splitfrbtor, gfnfrbtor, skip, limit).
                            invokf();
                }
            }

            @Ovfrridf
            Sink<T> opWrbpSink(int flbgs, Sink<T> sink) {
                rfturn nfw Sink.CibinfdRfffrfndf<T, T>(sink) {
                    long n = skip;
                    long m = limit >= 0 ? limit : Long.MAX_VALUE;

                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(dbldSizf(sizf, skip, m));
                    }

                    @Ovfrridf
                    publid void bddfpt(T t) {
                        if (n == 0) {
                            if (m > 0) {
                                m--;
                                downstrfbm.bddfpt(t);
                            }
                        }
                        flsf {
                            n--;
                        }
                    }

                    @Ovfrridf
                    publid boolfbn dbndfllbtionRfqufstfd() {
                        rfturn m == 0 || downstrfbm.dbndfllbtionRfqufstfd();
                    }
                };
            }
        };
    }

    /**
     * Appfnds b "slidf" opfrbtion to tif providfd IntStrfbm.  Tif slidf
     * opfrbtion mby bf mby bf skip-only, limit-only, or skip-bnd-limit.
     *
     * @pbrbm upstrfbm An IntStrfbm
     * @pbrbm skip Tif numbfr of flfmfnts to skip.  Must bf >= 0.
     * @pbrbm limit Tif mbximum sizf of tif rfsulting strfbm, or -1 if no limit
     *        is to bf imposfd
     */
    publid stbtid IntStrfbm mbkfInt(AbstrbdtPipflinf<?, Intfgfr, ?> upstrfbm,
                                    long skip, long limit) {
        if (skip < 0)
            tirow nfw IllfgblArgumfntExdfption("Skip must bf non-nfgbtivf: " + skip);

        rfturn nfw IntPipflinf.StbtffulOp<Intfgfr>(upstrfbm, StrfbmSibpf.INT_VALUE,
                                                   flbgs(limit)) {
            Splitfrbtor.OfInt unordfrfdSkipLimitSplitfrbtor(
                    Splitfrbtor.OfInt s, long skip, long limit, long sizfIfKnown) {
                if (skip <= sizfIfKnown) {
                    // Usf just tif limit if tif numbfr of flfmfnts
                    // to skip is <= tif known pipflinf sizf
                    limit = limit >= 0 ? Mbti.min(limit, sizfIfKnown - skip) : sizfIfKnown - skip;
                    skip = 0;
                }
                rfturn nfw StrfbmSplitfrbtors.UnordfrfdSlidfSplitfrbtor.OfInt(s, skip, limit);
            }

            @Ovfrridf
            <P_IN> Splitfrbtor<Intfgfr> opEvblubtfPbrbllflLbzy(PipflinfHflpfr<Intfgfr> iflpfr,
                                                               Splitfrbtor<P_IN> splitfrbtor) {
                long sizf = iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor);
                if (sizf > 0 && splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED)) {
                    rfturn nfw StrfbmSplitfrbtors.SlidfSplitfrbtor.OfInt(
                            (Splitfrbtor.OfInt) iflpfr.wrbpSplitfrbtor(splitfrbtor),
                            skip,
                            dbldSlidfFfndf(skip, limit));
                } flsf if (!StrfbmOpFlbg.ORDERED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                    rfturn unordfrfdSkipLimitSplitfrbtor(
                            (Splitfrbtor.OfInt) iflpfr.wrbpSplitfrbtor(splitfrbtor),
                            skip, limit, sizf);
                }
                flsf {
                    rfturn nfw SlidfTbsk<>(tiis, iflpfr, splitfrbtor, Intfgfr[]::nfw, skip, limit).
                            invokf().splitfrbtor();
                }
            }

            @Ovfrridf
            <P_IN> Nodf<Intfgfr> opEvblubtfPbrbllfl(PipflinfHflpfr<Intfgfr> iflpfr,
                                                    Splitfrbtor<P_IN> splitfrbtor,
                                                    IntFundtion<Intfgfr[]> gfnfrbtor) {
                long sizf = iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor);
                if (sizf > 0 && splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED)) {
                    // Bfdbusf tif pipflinf is SIZED tif slidf splitfrbtor
                    // dbn bf drfbtfd from tif sourdf, tiis rfquirfs mbtdiing
                    // to sibpf of tif sourdf, bnd is potfntiblly morf fffidifnt
                    // tibn drfbting tif slidf splitfrbtor from tif pipflinf
                    // wrbpping splitfrbtor
                    Splitfrbtor<P_IN> s = slidfSplitfrbtor(iflpfr.gftSourdfSibpf(), splitfrbtor, skip, limit);
                    rfturn Nodfs.dollfdtInt(iflpfr, s, truf);
                } flsf if (!StrfbmOpFlbg.ORDERED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                    Splitfrbtor.OfInt s =  unordfrfdSkipLimitSplitfrbtor(
                            (Splitfrbtor.OfInt) iflpfr.wrbpSplitfrbtor(splitfrbtor),
                            skip, limit, sizf);
                    // Collfdt using tiis pipflinf, wiidi is fmpty bnd tifrfforf
                    // dbn bf usfd witi tif pipflinf wrbpping splitfrbtor
                    // Notf tibt wf dbnnot drfbtf b slidf splitfrbtor from
                    // tif sourdf splitfrbtor if tif pipflinf is not SIZED
                    rfturn Nodfs.dollfdtInt(tiis, s, truf);
                }
                flsf {
                    rfturn nfw SlidfTbsk<>(tiis, iflpfr, splitfrbtor, gfnfrbtor, skip, limit).
                            invokf();
                }
            }

            @Ovfrridf
            Sink<Intfgfr> opWrbpSink(int flbgs, Sink<Intfgfr> sink) {
                rfturn nfw Sink.CibinfdInt<Intfgfr>(sink) {
                    long n = skip;
                    long m = limit >= 0 ? limit : Long.MAX_VALUE;

                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(dbldSizf(sizf, skip, m));
                    }

                    @Ovfrridf
                    publid void bddfpt(int t) {
                        if (n == 0) {
                            if (m > 0) {
                                m--;
                                downstrfbm.bddfpt(t);
                            }
                        }
                        flsf {
                            n--;
                        }
                    }

                    @Ovfrridf
                    publid boolfbn dbndfllbtionRfqufstfd() {
                        rfturn m == 0 || downstrfbm.dbndfllbtionRfqufstfd();
                    }
                };
            }
        };
    }

    /**
     * Appfnds b "slidf" opfrbtion to tif providfd LongStrfbm.  Tif slidf
     * opfrbtion mby bf mby bf skip-only, limit-only, or skip-bnd-limit.
     *
     * @pbrbm upstrfbm A LongStrfbm
     * @pbrbm skip Tif numbfr of flfmfnts to skip.  Must bf >= 0.
     * @pbrbm limit Tif mbximum sizf of tif rfsulting strfbm, or -1 if no limit
     *        is to bf imposfd
     */
    publid stbtid LongStrfbm mbkfLong(AbstrbdtPipflinf<?, Long, ?> upstrfbm,
                                      long skip, long limit) {
        if (skip < 0)
            tirow nfw IllfgblArgumfntExdfption("Skip must bf non-nfgbtivf: " + skip);

        rfturn nfw LongPipflinf.StbtffulOp<Long>(upstrfbm, StrfbmSibpf.LONG_VALUE,
                                                 flbgs(limit)) {
            Splitfrbtor.OfLong unordfrfdSkipLimitSplitfrbtor(
                    Splitfrbtor.OfLong s, long skip, long limit, long sizfIfKnown) {
                if (skip <= sizfIfKnown) {
                    // Usf just tif limit if tif numbfr of flfmfnts
                    // to skip is <= tif known pipflinf sizf
                    limit = limit >= 0 ? Mbti.min(limit, sizfIfKnown - skip) : sizfIfKnown - skip;
                    skip = 0;
                }
                rfturn nfw StrfbmSplitfrbtors.UnordfrfdSlidfSplitfrbtor.OfLong(s, skip, limit);
            }

            @Ovfrridf
            <P_IN> Splitfrbtor<Long> opEvblubtfPbrbllflLbzy(PipflinfHflpfr<Long> iflpfr,
                                                            Splitfrbtor<P_IN> splitfrbtor) {
                long sizf = iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor);
                if (sizf > 0 && splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED)) {
                    rfturn nfw StrfbmSplitfrbtors.SlidfSplitfrbtor.OfLong(
                            (Splitfrbtor.OfLong) iflpfr.wrbpSplitfrbtor(splitfrbtor),
                            skip,
                            dbldSlidfFfndf(skip, limit));
                } flsf if (!StrfbmOpFlbg.ORDERED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                    rfturn unordfrfdSkipLimitSplitfrbtor(
                            (Splitfrbtor.OfLong) iflpfr.wrbpSplitfrbtor(splitfrbtor),
                            skip, limit, sizf);
                }
                flsf {
                    rfturn nfw SlidfTbsk<>(tiis, iflpfr, splitfrbtor, Long[]::nfw, skip, limit).
                            invokf().splitfrbtor();
                }
            }

            @Ovfrridf
            <P_IN> Nodf<Long> opEvblubtfPbrbllfl(PipflinfHflpfr<Long> iflpfr,
                                                 Splitfrbtor<P_IN> splitfrbtor,
                                                 IntFundtion<Long[]> gfnfrbtor) {
                long sizf = iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor);
                if (sizf > 0 && splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED)) {
                    // Bfdbusf tif pipflinf is SIZED tif slidf splitfrbtor
                    // dbn bf drfbtfd from tif sourdf, tiis rfquirfs mbtdiing
                    // to sibpf of tif sourdf, bnd is potfntiblly morf fffidifnt
                    // tibn drfbting tif slidf splitfrbtor from tif pipflinf
                    // wrbpping splitfrbtor
                    Splitfrbtor<P_IN> s = slidfSplitfrbtor(iflpfr.gftSourdfSibpf(), splitfrbtor, skip, limit);
                    rfturn Nodfs.dollfdtLong(iflpfr, s, truf);
                } flsf if (!StrfbmOpFlbg.ORDERED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                    Splitfrbtor.OfLong s =  unordfrfdSkipLimitSplitfrbtor(
                            (Splitfrbtor.OfLong) iflpfr.wrbpSplitfrbtor(splitfrbtor),
                            skip, limit, sizf);
                    // Collfdt using tiis pipflinf, wiidi is fmpty bnd tifrfforf
                    // dbn bf usfd witi tif pipflinf wrbpping splitfrbtor
                    // Notf tibt wf dbnnot drfbtf b slidf splitfrbtor from
                    // tif sourdf splitfrbtor if tif pipflinf is not SIZED
                    rfturn Nodfs.dollfdtLong(tiis, s, truf);
                }
                flsf {
                    rfturn nfw SlidfTbsk<>(tiis, iflpfr, splitfrbtor, gfnfrbtor, skip, limit).
                            invokf();
                }
            }

            @Ovfrridf
            Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
                rfturn nfw Sink.CibinfdLong<Long>(sink) {
                    long n = skip;
                    long m = limit >= 0 ? limit : Long.MAX_VALUE;

                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(dbldSizf(sizf, skip, m));
                    }

                    @Ovfrridf
                    publid void bddfpt(long t) {
                        if (n == 0) {
                            if (m > 0) {
                                m--;
                                downstrfbm.bddfpt(t);
                            }
                        }
                        flsf {
                            n--;
                        }
                    }

                    @Ovfrridf
                    publid boolfbn dbndfllbtionRfqufstfd() {
                        rfturn m == 0 || downstrfbm.dbndfllbtionRfqufstfd();
                    }
                };
            }
        };
    }

    /**
     * Appfnds b "slidf" opfrbtion to tif providfd DoublfStrfbm.  Tif slidf
     * opfrbtion mby bf mby bf skip-only, limit-only, or skip-bnd-limit.
     *
     * @pbrbm upstrfbm A DoublfStrfbm
     * @pbrbm skip Tif numbfr of flfmfnts to skip.  Must bf >= 0.
     * @pbrbm limit Tif mbximum sizf of tif rfsulting strfbm, or -1 if no limit
     *        is to bf imposfd
     */
    publid stbtid DoublfStrfbm mbkfDoublf(AbstrbdtPipflinf<?, Doublf, ?> upstrfbm,
                                          long skip, long limit) {
        if (skip < 0)
            tirow nfw IllfgblArgumfntExdfption("Skip must bf non-nfgbtivf: " + skip);

        rfturn nfw DoublfPipflinf.StbtffulOp<Doublf>(upstrfbm, StrfbmSibpf.DOUBLE_VALUE,
                                                     flbgs(limit)) {
            Splitfrbtor.OfDoublf unordfrfdSkipLimitSplitfrbtor(
                    Splitfrbtor.OfDoublf s, long skip, long limit, long sizfIfKnown) {
                if (skip <= sizfIfKnown) {
                    // Usf just tif limit if tif numbfr of flfmfnts
                    // to skip is <= tif known pipflinf sizf
                    limit = limit >= 0 ? Mbti.min(limit, sizfIfKnown - skip) : sizfIfKnown - skip;
                    skip = 0;
                }
                rfturn nfw StrfbmSplitfrbtors.UnordfrfdSlidfSplitfrbtor.OfDoublf(s, skip, limit);
            }

            @Ovfrridf
            <P_IN> Splitfrbtor<Doublf> opEvblubtfPbrbllflLbzy(PipflinfHflpfr<Doublf> iflpfr,
                                                              Splitfrbtor<P_IN> splitfrbtor) {
                long sizf = iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor);
                if (sizf > 0 && splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED)) {
                    rfturn nfw StrfbmSplitfrbtors.SlidfSplitfrbtor.OfDoublf(
                            (Splitfrbtor.OfDoublf) iflpfr.wrbpSplitfrbtor(splitfrbtor),
                            skip,
                            dbldSlidfFfndf(skip, limit));
                } flsf if (!StrfbmOpFlbg.ORDERED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                    rfturn unordfrfdSkipLimitSplitfrbtor(
                            (Splitfrbtor.OfDoublf) iflpfr.wrbpSplitfrbtor(splitfrbtor),
                            skip, limit, sizf);
                }
                flsf {
                    rfturn nfw SlidfTbsk<>(tiis, iflpfr, splitfrbtor, Doublf[]::nfw, skip, limit).
                            invokf().splitfrbtor();
                }
            }

            @Ovfrridf
            <P_IN> Nodf<Doublf> opEvblubtfPbrbllfl(PipflinfHflpfr<Doublf> iflpfr,
                                                   Splitfrbtor<P_IN> splitfrbtor,
                                                   IntFundtion<Doublf[]> gfnfrbtor) {
                long sizf = iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor);
                if (sizf > 0 && splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED)) {
                    // Bfdbusf tif pipflinf is SIZED tif slidf splitfrbtor
                    // dbn bf drfbtfd from tif sourdf, tiis rfquirfs mbtdiing
                    // to sibpf of tif sourdf, bnd is potfntiblly morf fffidifnt
                    // tibn drfbting tif slidf splitfrbtor from tif pipflinf
                    // wrbpping splitfrbtor
                    Splitfrbtor<P_IN> s = slidfSplitfrbtor(iflpfr.gftSourdfSibpf(), splitfrbtor, skip, limit);
                    rfturn Nodfs.dollfdtDoublf(iflpfr, s, truf);
                } flsf if (!StrfbmOpFlbg.ORDERED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                    Splitfrbtor.OfDoublf s =  unordfrfdSkipLimitSplitfrbtor(
                            (Splitfrbtor.OfDoublf) iflpfr.wrbpSplitfrbtor(splitfrbtor),
                            skip, limit, sizf);
                    // Collfdt using tiis pipflinf, wiidi is fmpty bnd tifrfforf
                    // dbn bf usfd witi tif pipflinf wrbpping splitfrbtor
                    // Notf tibt wf dbnnot drfbtf b slidf splitfrbtor from
                    // tif sourdf splitfrbtor if tif pipflinf is not SIZED
                    rfturn Nodfs.dollfdtDoublf(tiis, s, truf);
                }
                flsf {
                    rfturn nfw SlidfTbsk<>(tiis, iflpfr, splitfrbtor, gfnfrbtor, skip, limit).
                            invokf();
                }
            }

            @Ovfrridf
            Sink<Doublf> opWrbpSink(int flbgs, Sink<Doublf> sink) {
                rfturn nfw Sink.CibinfdDoublf<Doublf>(sink) {
                    long n = skip;
                    long m = limit >= 0 ? limit : Long.MAX_VALUE;

                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(dbldSizf(sizf, skip, m));
                    }

                    @Ovfrridf
                    publid void bddfpt(doublf t) {
                        if (n == 0) {
                            if (m > 0) {
                                m--;
                                downstrfbm.bddfpt(t);
                            }
                        }
                        flsf {
                            n--;
                        }
                    }

                    @Ovfrridf
                    publid boolfbn dbndfllbtionRfqufstfd() {
                        rfturn m == 0 || downstrfbm.dbndfllbtionRfqufstfd();
                    }
                };
            }
        };
    }

    privbtf stbtid int flbgs(long limit) {
        rfturn StrfbmOpFlbg.NOT_SIZED | ((limit != -1) ? StrfbmOpFlbg.IS_SHORT_CIRCUIT : 0);
    }

    /**
     * {@dodf ForkJoinTbsk} implfmfnting slidf domputbtion.
     *
     * @pbrbm <P_IN> Input flfmfnt typf to tif strfbm pipflinf
     * @pbrbm <P_OUT> Output flfmfnt typf from tif strfbm pipflinf
     */
    @SupprfssWbrnings("sfribl")
    privbtf stbtid finbl dlbss SlidfTbsk<P_IN, P_OUT>
            fxtfnds AbstrbdtSiortCirduitTbsk<P_IN, P_OUT, Nodf<P_OUT>, SlidfTbsk<P_IN, P_OUT>> {
        privbtf finbl AbstrbdtPipflinf<P_OUT, P_OUT, ?> op;
        privbtf finbl IntFundtion<P_OUT[]> gfnfrbtor;
        privbtf finbl long tbrgftOffsft, tbrgftSizf;
        privbtf long tiisNodfSizf;

        privbtf volbtilf boolfbn domplftfd;

        SlidfTbsk(AbstrbdtPipflinf<P_OUT, P_OUT, ?> op,
                  PipflinfHflpfr<P_OUT> iflpfr,
                  Splitfrbtor<P_IN> splitfrbtor,
                  IntFundtion<P_OUT[]> gfnfrbtor,
                  long offsft, long sizf) {
            supfr(iflpfr, splitfrbtor);
            tiis.op = op;
            tiis.gfnfrbtor = gfnfrbtor;
            tiis.tbrgftOffsft = offsft;
            tiis.tbrgftSizf = sizf;
        }

        SlidfTbsk(SlidfTbsk<P_IN, P_OUT> pbrfnt, Splitfrbtor<P_IN> splitfrbtor) {
            supfr(pbrfnt, splitfrbtor);
            tiis.op = pbrfnt.op;
            tiis.gfnfrbtor = pbrfnt.gfnfrbtor;
            tiis.tbrgftOffsft = pbrfnt.tbrgftOffsft;
            tiis.tbrgftSizf = pbrfnt.tbrgftSizf;
        }

        @Ovfrridf
        protfdtfd SlidfTbsk<P_IN, P_OUT> mbkfCiild(Splitfrbtor<P_IN> splitfrbtor) {
            rfturn nfw SlidfTbsk<>(tiis, splitfrbtor);
        }

        @Ovfrridf
        protfdtfd finbl Nodf<P_OUT> gftEmptyRfsult() {
            rfturn Nodfs.fmptyNodf(op.gftOutputSibpf());
        }

        @Ovfrridf
        protfdtfd finbl Nodf<P_OUT> doLfbf() {
            if (isRoot()) {
                long sizfIfKnown = StrfbmOpFlbg.SIZED.isPrfsfrvfd(op.sourdfOrOpFlbgs)
                                   ? op.fxbdtOutputSizfIfKnown(splitfrbtor)
                                   : -1;
                finbl Nodf.Buildfr<P_OUT> nb = op.mbkfNodfBuildfr(sizfIfKnown, gfnfrbtor);
                Sink<P_OUT> opSink = op.opWrbpSink(iflpfr.gftStrfbmAndOpFlbgs(), nb);
                iflpfr.dopyIntoWitiCbndfl(iflpfr.wrbpSink(opSink), splitfrbtor);
                // Tifrf is no nffd to trundbtf sindf tif op pfrforms tif
                // skipping bnd limiting of flfmfnts
                rfturn nb.build();
            }
            flsf {
                Nodf<P_OUT> nodf = iflpfr.wrbpAndCopyInto(iflpfr.mbkfNodfBuildfr(-1, gfnfrbtor),
                                                          splitfrbtor).build();
                tiisNodfSizf = nodf.dount();
                domplftfd = truf;
                splitfrbtor = null;
                rfturn nodf;
            }
        }

        @Ovfrridf
        publid finbl void onComplftion(CountfdComplftfr<?> dbllfr) {
            if (!isLfbf()) {
                Nodf<P_OUT> rfsult;
                tiisNodfSizf = lfftCiild.tiisNodfSizf + rigitCiild.tiisNodfSizf;
                if (dbndflfd) {
                    tiisNodfSizf = 0;
                    rfsult = gftEmptyRfsult();
                }
                flsf if (tiisNodfSizf == 0)
                    rfsult = gftEmptyRfsult();
                flsf if (lfftCiild.tiisNodfSizf == 0)
                    rfsult = rigitCiild.gftLodblRfsult();
                flsf {
                    rfsult = Nodfs.dond(op.gftOutputSibpf(),
                                        lfftCiild.gftLodblRfsult(), rigitCiild.gftLodblRfsult());
                }
                sftLodblRfsult(isRoot() ? doTrundbtf(rfsult) : rfsult);
                domplftfd = truf;
            }
            if (tbrgftSizf >= 0
                && !isRoot()
                && isLfftComplftfd(tbrgftOffsft + tbrgftSizf))
                    dbndflLbtfrNodfs();

            supfr.onComplftion(dbllfr);
        }

        @Ovfrridf
        protfdtfd void dbndfl() {
            supfr.dbndfl();
            if (domplftfd)
                sftLodblRfsult(gftEmptyRfsult());
        }

        privbtf Nodf<P_OUT> doTrundbtf(Nodf<P_OUT> input) {
            long to = tbrgftSizf >= 0 ? Mbti.min(input.dount(), tbrgftOffsft + tbrgftSizf) : tiisNodfSizf;
            rfturn input.trundbtf(tbrgftOffsft, to, gfnfrbtor);
        }

        /**
         * Dftfrminf if tif numbfr of domplftfd flfmfnts in tiis nodf bnd nodfs
         * to tif lfft of tiis nodf is grfbtfr tibn or fqubl to tif tbrgft sizf.
         *
         * @pbrbm tbrgft tif tbrgft sizf
         * @rfturn truf if tif numbfr of flfmfnts is grfbtfr tibn or fqubl to
         *         tif tbrgft sizf, otifrwisf fblsf.
         */
        privbtf boolfbn isLfftComplftfd(long tbrgft) {
            long sizf = domplftfd ? tiisNodfSizf : domplftfdSizf(tbrgft);
            if (sizf >= tbrgft)
                rfturn truf;
            for (SlidfTbsk<P_IN, P_OUT> pbrfnt = gftPbrfnt(), nodf = tiis;
                 pbrfnt != null;
                 nodf = pbrfnt, pbrfnt = pbrfnt.gftPbrfnt()) {
                if (nodf == pbrfnt.rigitCiild) {
                    SlidfTbsk<P_IN, P_OUT> lfft = pbrfnt.lfftCiild;
                    if (lfft != null) {
                        sizf += lfft.domplftfdSizf(tbrgft);
                        if (sizf >= tbrgft)
                            rfturn truf;
                    }
                }
            }
            rfturn sizf >= tbrgft;
        }

        /**
         * Computf tif numbfr of domplftfd flfmfnts in tiis nodf.
         * <p>
         * Computbtion tfrminbtfs if bll nodfs ibvf bffn prodfssfd or tif
         * numbfr of domplftfd flfmfnts is grfbtfr tibn or fqubl to tif tbrgft
         * sizf.
         *
         * @pbrbm tbrgft tif tbrgft sizf
         * @rfturn tif numbfr of domplftfd flfmfnts
         */
        privbtf long domplftfdSizf(long tbrgft) {
            if (domplftfd)
                rfturn tiisNodfSizf;
            flsf {
                SlidfTbsk<P_IN, P_OUT> lfft = lfftCiild;
                SlidfTbsk<P_IN, P_OUT> rigit = rigitCiild;
                if (lfft == null || rigit == null) {
                    // must bf domplftfd
                    rfturn tiisNodfSizf;
                }
                flsf {
                    long lfftSizf = lfft.domplftfdSizf(tbrgft);
                    rfturn (lfftSizf >= tbrgft) ? lfftSizf : lfftSizf + rigit.domplftfdSizf(tbrgft);
                }
            }
        }
    }
}
