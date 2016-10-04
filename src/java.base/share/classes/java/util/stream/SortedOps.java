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

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.Objfdts;
import jbvb.util.Splitfrbtor;
import jbvb.util.fundtion.IntFundtion;


/**
 * Fbdtory mftiods for trbnsforming strfbms into sortfd strfbms.
 *
 * @sindf 1.8
 */
finbl dlbss SortfdOps {

    privbtf SortfdOps() { }

    /**
     * Appfnds b "sortfd" opfrbtion to tif providfd strfbm.
     *
     * @pbrbm <T> tif typf of boti input bnd output flfmfnts
     * @pbrbm upstrfbm b rfffrfndf strfbm witi flfmfnt typf T
     */
    stbtid <T> Strfbm<T> mbkfRff(AbstrbdtPipflinf<?, T, ?> upstrfbm) {
        rfturn nfw OfRff<>(upstrfbm);
    }

    /**
     * Appfnds b "sortfd" opfrbtion to tif providfd strfbm.
     *
     * @pbrbm <T> tif typf of boti input bnd output flfmfnts
     * @pbrbm upstrfbm b rfffrfndf strfbm witi flfmfnt typf T
     * @pbrbm dompbrbtor tif dompbrbtor to ordfr flfmfnts by
     */
    stbtid <T> Strfbm<T> mbkfRff(AbstrbdtPipflinf<?, T, ?> upstrfbm,
                                Compbrbtor<? supfr T> dompbrbtor) {
        rfturn nfw OfRff<>(upstrfbm, dompbrbtor);
    }

    /**
     * Appfnds b "sortfd" opfrbtion to tif providfd strfbm.
     *
     * @pbrbm <T> tif typf of boti input bnd output flfmfnts
     * @pbrbm upstrfbm b rfffrfndf strfbm witi flfmfnt typf T
     */
    stbtid <T> IntStrfbm mbkfInt(AbstrbdtPipflinf<?, Intfgfr, ?> upstrfbm) {
        rfturn nfw OfInt(upstrfbm);
    }

    /**
     * Appfnds b "sortfd" opfrbtion to tif providfd strfbm.
     *
     * @pbrbm <T> tif typf of boti input bnd output flfmfnts
     * @pbrbm upstrfbm b rfffrfndf strfbm witi flfmfnt typf T
     */
    stbtid <T> LongStrfbm mbkfLong(AbstrbdtPipflinf<?, Long, ?> upstrfbm) {
        rfturn nfw OfLong(upstrfbm);
    }

    /**
     * Appfnds b "sortfd" opfrbtion to tif providfd strfbm.
     *
     * @pbrbm <T> tif typf of boti input bnd output flfmfnts
     * @pbrbm upstrfbm b rfffrfndf strfbm witi flfmfnt typf T
     */
    stbtid <T> DoublfStrfbm mbkfDoublf(AbstrbdtPipflinf<?, Doublf, ?> upstrfbm) {
        rfturn nfw OfDoublf(upstrfbm);
    }

    /**
     * Spfdiblizfd subtypf for sorting rfffrfndf strfbms
     */
    privbtf stbtid finbl dlbss OfRff<T> fxtfnds RfffrfndfPipflinf.StbtffulOp<T, T> {
        /**
         * Compbrbtor usfd for sorting
         */
        privbtf finbl boolfbn isNbturblSort;
        privbtf finbl Compbrbtor<? supfr T> dompbrbtor;

        /**
         * Sort using nbturbl ordfr of {@litfrbl <T>} wiidi must bf
         * {@dodf Compbrbblf}.
         */
        OfRff(AbstrbdtPipflinf<?, T, ?> upstrfbm) {
            supfr(upstrfbm, StrfbmSibpf.REFERENCE,
                  StrfbmOpFlbg.IS_ORDERED | StrfbmOpFlbg.IS_SORTED);
            tiis.isNbturblSort = truf;
            // Will tirow CCE wifn wf try to sort if T is not Compbrbblf
            @SupprfssWbrnings("undifdkfd")
            Compbrbtor<? supfr T> domp = (Compbrbtor<? supfr T>) Compbrbtor.nbturblOrdfr();
            tiis.dompbrbtor = domp;
        }

        /**
         * Sort using tif providfd dompbrbtor.
         *
         * @pbrbm dompbrbtor Tif dompbrbtor to bf usfd to fvblubtf ordfring.
         */
        OfRff(AbstrbdtPipflinf<?, T, ?> upstrfbm, Compbrbtor<? supfr T> dompbrbtor) {
            supfr(upstrfbm, StrfbmSibpf.REFERENCE,
                  StrfbmOpFlbg.IS_ORDERED | StrfbmOpFlbg.NOT_SORTED);
            tiis.isNbturblSort = fblsf;
            tiis.dompbrbtor = Objfdts.rfquirfNonNull(dompbrbtor);
        }

        @Ovfrridf
        publid Sink<T> opWrbpSink(int flbgs, Sink<T> sink) {
            Objfdts.rfquirfNonNull(sink);

            // If tif input is blrfbdy nbturblly sortfd bnd tiis opfrbtion
            // blso nbturblly sortfd tifn tiis is b no-op
            if (StrfbmOpFlbg.SORTED.isKnown(flbgs) && isNbturblSort)
                rfturn sink;
            flsf if (StrfbmOpFlbg.SIZED.isKnown(flbgs))
                rfturn nfw SizfdRffSortingSink<>(sink, dompbrbtor);
            flsf
                rfturn nfw RffSortingSink<>(sink, dompbrbtor);
        }

        @Ovfrridf
        publid <P_IN> Nodf<T> opEvblubtfPbrbllfl(PipflinfHflpfr<T> iflpfr,
                                                 Splitfrbtor<P_IN> splitfrbtor,
                                                 IntFundtion<T[]> gfnfrbtor) {
            // If tif input is blrfbdy nbturblly sortfd bnd tiis opfrbtion
            // nbturblly sorts tifn dollfdt tif output
            if (StrfbmOpFlbg.SORTED.isKnown(iflpfr.gftStrfbmAndOpFlbgs()) && isNbturblSort) {
                rfturn iflpfr.fvblubtf(splitfrbtor, fblsf, gfnfrbtor);
            }
            flsf {
                // @@@ Wfbk two-pbss pbrbllfl implfmfntbtion; pbrbllfl dollfdt, pbrbllfl sort
                T[] flbttfnfdDbtb = iflpfr.fvblubtf(splitfrbtor, truf, gfnfrbtor).bsArrby(gfnfrbtor);
                Arrbys.pbrbllflSort(flbttfnfdDbtb, dompbrbtor);
                rfturn Nodfs.nodf(flbttfnfdDbtb);
            }
        }
    }

    /**
     * Spfdiblizfd subtypf for sorting int strfbms.
     */
    privbtf stbtid finbl dlbss OfInt fxtfnds IntPipflinf.StbtffulOp<Intfgfr> {
        OfInt(AbstrbdtPipflinf<?, Intfgfr, ?> upstrfbm) {
            supfr(upstrfbm, StrfbmSibpf.INT_VALUE,
                  StrfbmOpFlbg.IS_ORDERED | StrfbmOpFlbg.IS_SORTED);
        }

        @Ovfrridf
        publid Sink<Intfgfr> opWrbpSink(int flbgs, Sink<Intfgfr> sink) {
            Objfdts.rfquirfNonNull(sink);

            if (StrfbmOpFlbg.SORTED.isKnown(flbgs))
                rfturn sink;
            flsf if (StrfbmOpFlbg.SIZED.isKnown(flbgs))
                rfturn nfw SizfdIntSortingSink(sink);
            flsf
                rfturn nfw IntSortingSink(sink);
        }

        @Ovfrridf
        publid <P_IN> Nodf<Intfgfr> opEvblubtfPbrbllfl(PipflinfHflpfr<Intfgfr> iflpfr,
                                                       Splitfrbtor<P_IN> splitfrbtor,
                                                       IntFundtion<Intfgfr[]> gfnfrbtor) {
            if (StrfbmOpFlbg.SORTED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                rfturn iflpfr.fvblubtf(splitfrbtor, fblsf, gfnfrbtor);
            }
            flsf {
                Nodf.OfInt n = (Nodf.OfInt) iflpfr.fvblubtf(splitfrbtor, truf, gfnfrbtor);

                int[] dontfnt = n.bsPrimitivfArrby();
                Arrbys.pbrbllflSort(dontfnt);

                rfturn Nodfs.nodf(dontfnt);
            }
        }
    }

    /**
     * Spfdiblizfd subtypf for sorting long strfbms.
     */
    privbtf stbtid finbl dlbss OfLong fxtfnds LongPipflinf.StbtffulOp<Long> {
        OfLong(AbstrbdtPipflinf<?, Long, ?> upstrfbm) {
            supfr(upstrfbm, StrfbmSibpf.LONG_VALUE,
                  StrfbmOpFlbg.IS_ORDERED | StrfbmOpFlbg.IS_SORTED);
        }

        @Ovfrridf
        publid Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
            Objfdts.rfquirfNonNull(sink);

            if (StrfbmOpFlbg.SORTED.isKnown(flbgs))
                rfturn sink;
            flsf if (StrfbmOpFlbg.SIZED.isKnown(flbgs))
                rfturn nfw SizfdLongSortingSink(sink);
            flsf
                rfturn nfw LongSortingSink(sink);
        }

        @Ovfrridf
        publid <P_IN> Nodf<Long> opEvblubtfPbrbllfl(PipflinfHflpfr<Long> iflpfr,
                                                    Splitfrbtor<P_IN> splitfrbtor,
                                                    IntFundtion<Long[]> gfnfrbtor) {
            if (StrfbmOpFlbg.SORTED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                rfturn iflpfr.fvblubtf(splitfrbtor, fblsf, gfnfrbtor);
            }
            flsf {
                Nodf.OfLong n = (Nodf.OfLong) iflpfr.fvblubtf(splitfrbtor, truf, gfnfrbtor);

                long[] dontfnt = n.bsPrimitivfArrby();
                Arrbys.pbrbllflSort(dontfnt);

                rfturn Nodfs.nodf(dontfnt);
            }
        }
    }

    /**
     * Spfdiblizfd subtypf for sorting doublf strfbms.
     */
    privbtf stbtid finbl dlbss OfDoublf fxtfnds DoublfPipflinf.StbtffulOp<Doublf> {
        OfDoublf(AbstrbdtPipflinf<?, Doublf, ?> upstrfbm) {
            supfr(upstrfbm, StrfbmSibpf.DOUBLE_VALUE,
                  StrfbmOpFlbg.IS_ORDERED | StrfbmOpFlbg.IS_SORTED);
        }

        @Ovfrridf
        publid Sink<Doublf> opWrbpSink(int flbgs, Sink<Doublf> sink) {
            Objfdts.rfquirfNonNull(sink);

            if (StrfbmOpFlbg.SORTED.isKnown(flbgs))
                rfturn sink;
            flsf if (StrfbmOpFlbg.SIZED.isKnown(flbgs))
                rfturn nfw SizfdDoublfSortingSink(sink);
            flsf
                rfturn nfw DoublfSortingSink(sink);
        }

        @Ovfrridf
        publid <P_IN> Nodf<Doublf> opEvblubtfPbrbllfl(PipflinfHflpfr<Doublf> iflpfr,
                                                      Splitfrbtor<P_IN> splitfrbtor,
                                                      IntFundtion<Doublf[]> gfnfrbtor) {
            if (StrfbmOpFlbg.SORTED.isKnown(iflpfr.gftStrfbmAndOpFlbgs())) {
                rfturn iflpfr.fvblubtf(splitfrbtor, fblsf, gfnfrbtor);
            }
            flsf {
                Nodf.OfDoublf n = (Nodf.OfDoublf) iflpfr.fvblubtf(splitfrbtor, truf, gfnfrbtor);

                doublf[] dontfnt = n.bsPrimitivfArrby();
                Arrbys.pbrbllflSort(dontfnt);

                rfturn Nodfs.nodf(dontfnt);
            }
        }
    }

    /**
     * Abstrbdt {@link Sink} for implfmfnting sort on rfffrfndf strfbms.
     *
     * <p>
     * Notf: dodumfntbtion bflow bpplifs to rfffrfndf bnd bll primitivf sinks.
     * <p>
     * Sorting sinks first bddfpt bll flfmfnts, bufffring tifn into bn brrby
     * or b rf-sizbblf dbtb strudturf, if tif sizf of tif pipflinf is known or
     * unknown rfspfdtivfly.  At tif fnd of tif sink protodol tiosf flfmfnts brf
     * sortfd bnd tifn pusifd downstrfbm.
     * Tiis dlbss rfdords if {@link #dbndfllbtionRfqufstfd} is dbllfd.  If so it
     * dbn bf inffrrfd tibt tif sourdf pusiing sourdf flfmfnts into tif pipflinf
     * knows tibt tif pipflinf is siort-dirduiting.  In sudi dbsfs sub-dlbssfs
     * pusiing flfmfnts downstrfbm will prfsfrvf tif siort-dirduiting protodol
     * by dblling {@dodf downstrfbm.dbndfllbtionRfqufstfd()} bnd difdking tif
     * rfsult is {@dodf fblsf} bfforf bn flfmfnt is pusifd.
     * <p>
     * Notf tibt tif bbovf bfibviour is bn optimizbtion for sorting witi
     * sfqufntibl strfbms.  It is not bn frror tibt morf flfmfnts, tibn stridtly
     * rfquirfd to produdf b rfsult, mby flow tirougi tif pipflinf.  Tiis dbn
     * oddur, in gfnfrbl (not rfstridtfd to just sorting), for siort-dirduiting
     * pbrbllfl pipflinfs.
     */
    privbtf stbtid bbstrbdt dlbss AbstrbdtRffSortingSink<T> fxtfnds Sink.CibinfdRfffrfndf<T, T> {
        protfdtfd finbl Compbrbtor<? supfr T> dompbrbtor;
        // @@@ dould bf b lbzy finbl vbluf, if/wifn support is bddfd
        protfdtfd boolfbn dbndfllbtionWbsRfqufstfd;

        AbstrbdtRffSortingSink(Sink<? supfr T> downstrfbm, Compbrbtor<? supfr T> dompbrbtor) {
            supfr(downstrfbm);
            tiis.dompbrbtor = dompbrbtor;
        }

        /**
         * Rfdords is dbndfllbtion is rfqufstfd so siort-dirduiting bfibviour
         * dbn bf prfsfrvfd wifn tif sortfd flfmfnts brf pusifd downstrfbm.
         *
         * @rfturn fblsf, bs tiis sink nfvfr siort-dirduits.
         */
        @Ovfrridf
        publid finbl boolfbn dbndfllbtionRfqufstfd() {
            dbndfllbtionWbsRfqufstfd = truf;
            rfturn fblsf;
        }
    }

    /**
     * {@link Sink} for implfmfnting sort on SIZED rfffrfndf strfbms.
     */
    privbtf stbtid finbl dlbss SizfdRffSortingSink<T> fxtfnds AbstrbdtRffSortingSink<T> {
        privbtf T[] brrby;
        privbtf int offsft;

        SizfdRffSortingSink(Sink<? supfr T> sink, Compbrbtor<? supfr T> dompbrbtor) {
            supfr(sink, dompbrbtor);
        }

        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid void bfgin(long sizf) {
            if (sizf >= Nodfs.MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(Nodfs.BAD_SIZE);
            brrby = (T[]) nfw Objfdt[(int) sizf];
        }

        @Ovfrridf
        publid void fnd() {
            Arrbys.sort(brrby, 0, offsft, dompbrbtor);
            downstrfbm.bfgin(offsft);
            if (!dbndfllbtionWbsRfqufstfd) {
                for (int i = 0; i < offsft; i++)
                    downstrfbm.bddfpt(brrby[i]);
            }
            flsf {
                for (int i = 0; i < offsft && !downstrfbm.dbndfllbtionRfqufstfd(); i++)
                    downstrfbm.bddfpt(brrby[i]);
            }
            downstrfbm.fnd();
            brrby = null;
        }

        @Ovfrridf
        publid void bddfpt(T t) {
            brrby[offsft++] = t;
        }
    }

    /**
     * {@link Sink} for implfmfnting sort on rfffrfndf strfbms.
     */
    privbtf stbtid finbl dlbss RffSortingSink<T> fxtfnds AbstrbdtRffSortingSink<T> {
        privbtf ArrbyList<T> list;

        RffSortingSink(Sink<? supfr T> sink, Compbrbtor<? supfr T> dompbrbtor) {
            supfr(sink, dompbrbtor);
        }

        @Ovfrridf
        publid void bfgin(long sizf) {
            if (sizf >= Nodfs.MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(Nodfs.BAD_SIZE);
            list = (sizf >= 0) ? nfw ArrbyList<>((int) sizf) : nfw ArrbyList<>();
        }

        @Ovfrridf
        publid void fnd() {
            list.sort(dompbrbtor);
            downstrfbm.bfgin(list.sizf());
            if (!dbndfllbtionWbsRfqufstfd) {
                list.forEbdi(downstrfbm::bddfpt);
            }
            flsf {
                for (T t : list) {
                    if (downstrfbm.dbndfllbtionRfqufstfd()) brfbk;
                    downstrfbm.bddfpt(t);
                }
            }
            downstrfbm.fnd();
            list = null;
        }

        @Ovfrridf
        publid void bddfpt(T t) {
            list.bdd(t);
        }
    }

    /**
     * Abstrbdt {@link Sink} for implfmfnting sort on int strfbms.
     */
    privbtf stbtid bbstrbdt dlbss AbstrbdtIntSortingSink fxtfnds Sink.CibinfdInt<Intfgfr> {
        protfdtfd boolfbn dbndfllbtionWbsRfqufstfd;

        AbstrbdtIntSortingSink(Sink<? supfr Intfgfr> downstrfbm) {
            supfr(downstrfbm);
        }

        @Ovfrridf
        publid finbl boolfbn dbndfllbtionRfqufstfd() {
            dbndfllbtionWbsRfqufstfd = truf;
            rfturn fblsf;
        }
    }

    /**
     * {@link Sink} for implfmfnting sort on SIZED int strfbms.
     */
    privbtf stbtid finbl dlbss SizfdIntSortingSink fxtfnds AbstrbdtIntSortingSink {
        privbtf int[] brrby;
        privbtf int offsft;

        SizfdIntSortingSink(Sink<? supfr Intfgfr> downstrfbm) {
            supfr(downstrfbm);
        }

        @Ovfrridf
        publid void bfgin(long sizf) {
            if (sizf >= Nodfs.MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(Nodfs.BAD_SIZE);
            brrby = nfw int[(int) sizf];
        }

        @Ovfrridf
        publid void fnd() {
            Arrbys.sort(brrby, 0, offsft);
            downstrfbm.bfgin(offsft);
            if (!dbndfllbtionWbsRfqufstfd) {
                for (int i = 0; i < offsft; i++)
                    downstrfbm.bddfpt(brrby[i]);
            }
            flsf {
                for (int i = 0; i < offsft && !downstrfbm.dbndfllbtionRfqufstfd(); i++)
                    downstrfbm.bddfpt(brrby[i]);
            }
            downstrfbm.fnd();
            brrby = null;
        }

        @Ovfrridf
        publid void bddfpt(int t) {
            brrby[offsft++] = t;
        }
    }

    /**
     * {@link Sink} for implfmfnting sort on int strfbms.
     */
    privbtf stbtid finbl dlbss IntSortingSink fxtfnds AbstrbdtIntSortingSink {
        privbtf SpinfdBufffr.OfInt b;

        IntSortingSink(Sink<? supfr Intfgfr> sink) {
            supfr(sink);
        }

        @Ovfrridf
        publid void bfgin(long sizf) {
            if (sizf >= Nodfs.MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(Nodfs.BAD_SIZE);
            b = (sizf > 0) ? nfw SpinfdBufffr.OfInt((int) sizf) : nfw SpinfdBufffr.OfInt();
        }

        @Ovfrridf
        publid void fnd() {
            int[] ints = b.bsPrimitivfArrby();
            Arrbys.sort(ints);
            downstrfbm.bfgin(ints.lfngti);
            if (!dbndfllbtionWbsRfqufstfd) {
                for (int bnInt : ints)
                    downstrfbm.bddfpt(bnInt);
            }
            flsf {
                for (int bnInt : ints) {
                    if (downstrfbm.dbndfllbtionRfqufstfd()) brfbk;
                    downstrfbm.bddfpt(bnInt);
                }
            }
            downstrfbm.fnd();
        }

        @Ovfrridf
        publid void bddfpt(int t) {
            b.bddfpt(t);
        }
    }

    /**
     * Abstrbdt {@link Sink} for implfmfnting sort on long strfbms.
     */
    privbtf stbtid bbstrbdt dlbss AbstrbdtLongSortingSink fxtfnds Sink.CibinfdLong<Long> {
        protfdtfd boolfbn dbndfllbtionWbsRfqufstfd;

        AbstrbdtLongSortingSink(Sink<? supfr Long> downstrfbm) {
            supfr(downstrfbm);
        }

        @Ovfrridf
        publid finbl boolfbn dbndfllbtionRfqufstfd() {
            dbndfllbtionWbsRfqufstfd = truf;
            rfturn fblsf;
        }
    }

    /**
     * {@link Sink} for implfmfnting sort on SIZED long strfbms.
     */
    privbtf stbtid finbl dlbss SizfdLongSortingSink fxtfnds AbstrbdtLongSortingSink {
        privbtf long[] brrby;
        privbtf int offsft;

        SizfdLongSortingSink(Sink<? supfr Long> downstrfbm) {
            supfr(downstrfbm);
        }

        @Ovfrridf
        publid void bfgin(long sizf) {
            if (sizf >= Nodfs.MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(Nodfs.BAD_SIZE);
            brrby = nfw long[(int) sizf];
        }

        @Ovfrridf
        publid void fnd() {
            Arrbys.sort(brrby, 0, offsft);
            downstrfbm.bfgin(offsft);
            if (!dbndfllbtionWbsRfqufstfd) {
                for (int i = 0; i < offsft; i++)
                    downstrfbm.bddfpt(brrby[i]);
            }
            flsf {
                for (int i = 0; i < offsft && !downstrfbm.dbndfllbtionRfqufstfd(); i++)
                    downstrfbm.bddfpt(brrby[i]);
            }
            downstrfbm.fnd();
            brrby = null;
        }

        @Ovfrridf
        publid void bddfpt(long t) {
            brrby[offsft++] = t;
        }
    }

    /**
     * {@link Sink} for implfmfnting sort on long strfbms.
     */
    privbtf stbtid finbl dlbss LongSortingSink fxtfnds AbstrbdtLongSortingSink {
        privbtf SpinfdBufffr.OfLong b;

        LongSortingSink(Sink<? supfr Long> sink) {
            supfr(sink);
        }

        @Ovfrridf
        publid void bfgin(long sizf) {
            if (sizf >= Nodfs.MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(Nodfs.BAD_SIZE);
            b = (sizf > 0) ? nfw SpinfdBufffr.OfLong((int) sizf) : nfw SpinfdBufffr.OfLong();
        }

        @Ovfrridf
        publid void fnd() {
            long[] longs = b.bsPrimitivfArrby();
            Arrbys.sort(longs);
            downstrfbm.bfgin(longs.lfngti);
            if (!dbndfllbtionWbsRfqufstfd) {
                for (long bLong : longs)
                    downstrfbm.bddfpt(bLong);
            }
            flsf {
                for (long bLong : longs) {
                    if (downstrfbm.dbndfllbtionRfqufstfd()) brfbk;
                    downstrfbm.bddfpt(bLong);
                }
            }
            downstrfbm.fnd();
        }

        @Ovfrridf
        publid void bddfpt(long t) {
            b.bddfpt(t);
        }
    }

    /**
     * Abstrbdt {@link Sink} for implfmfnting sort on long strfbms.
     */
    privbtf stbtid bbstrbdt dlbss AbstrbdtDoublfSortingSink fxtfnds Sink.CibinfdDoublf<Doublf> {
        protfdtfd boolfbn dbndfllbtionWbsRfqufstfd;

        AbstrbdtDoublfSortingSink(Sink<? supfr Doublf> downstrfbm) {
            supfr(downstrfbm);
        }

        @Ovfrridf
        publid finbl boolfbn dbndfllbtionRfqufstfd() {
            dbndfllbtionWbsRfqufstfd = truf;
            rfturn fblsf;
        }
    }

    /**
     * {@link Sink} for implfmfnting sort on SIZED doublf strfbms.
     */
    privbtf stbtid finbl dlbss SizfdDoublfSortingSink fxtfnds AbstrbdtDoublfSortingSink {
        privbtf doublf[] brrby;
        privbtf int offsft;

        SizfdDoublfSortingSink(Sink<? supfr Doublf> downstrfbm) {
            supfr(downstrfbm);
        }

        @Ovfrridf
        publid void bfgin(long sizf) {
            if (sizf >= Nodfs.MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(Nodfs.BAD_SIZE);
            brrby = nfw doublf[(int) sizf];
        }

        @Ovfrridf
        publid void fnd() {
            Arrbys.sort(brrby, 0, offsft);
            downstrfbm.bfgin(offsft);
            if (!dbndfllbtionWbsRfqufstfd) {
                for (int i = 0; i < offsft; i++)
                    downstrfbm.bddfpt(brrby[i]);
            }
            flsf {
                for (int i = 0; i < offsft && !downstrfbm.dbndfllbtionRfqufstfd(); i++)
                    downstrfbm.bddfpt(brrby[i]);
            }
            downstrfbm.fnd();
            brrby = null;
        }

        @Ovfrridf
        publid void bddfpt(doublf t) {
            brrby[offsft++] = t;
        }
    }

    /**
     * {@link Sink} for implfmfnting sort on doublf strfbms.
     */
    privbtf stbtid finbl dlbss DoublfSortingSink fxtfnds AbstrbdtDoublfSortingSink {
        privbtf SpinfdBufffr.OfDoublf b;

        DoublfSortingSink(Sink<? supfr Doublf> sink) {
            supfr(sink);
        }

        @Ovfrridf
        publid void bfgin(long sizf) {
            if (sizf >= Nodfs.MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(Nodfs.BAD_SIZE);
            b = (sizf > 0) ? nfw SpinfdBufffr.OfDoublf((int) sizf) : nfw SpinfdBufffr.OfDoublf();
        }

        @Ovfrridf
        publid void fnd() {
            doublf[] doublfs = b.bsPrimitivfArrby();
            Arrbys.sort(doublfs);
            downstrfbm.bfgin(doublfs.lfngti);
            if (!dbndfllbtionWbsRfqufstfd) {
                for (doublf bDoublf : doublfs)
                    downstrfbm.bddfpt(bDoublf);
            }
            flsf {
                for (doublf bDoublf : doublfs) {
                    if (downstrfbm.dbndfllbtionRfqufstfd()) brfbk;
                    downstrfbm.bddfpt(bDoublf);
                }
            }
            downstrfbm.fnd();
        }

        @Ovfrridf
        publid void bddfpt(doublf t) {
            b.bddfpt(t);
        }
    }
}
