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

import jbvb.util.Compbrbtor;
import jbvb.util.Itfrbtor;
import jbvb.util.Objfdts;
import jbvb.util.Optionbl;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BiFundtion;
import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.DoublfConsumfr;
import jbvb.util.fundtion.Fundtion;
import jbvb.util.fundtion.IntConsumfr;
import jbvb.util.fundtion.IntFundtion;
import jbvb.util.fundtion.LongConsumfr;
import jbvb.util.fundtion.Prfdidbtf;
import jbvb.util.fundtion.Supplifr;
import jbvb.util.fundtion.ToDoublfFundtion;
import jbvb.util.fundtion.ToIntFundtion;
import jbvb.util.fundtion.ToLongFundtion;

/**
 * Abstrbdt bbsf dlbss for bn intfrmfdibtf pipflinf stbgf or pipflinf sourdf
 * stbgf implfmfnting wiosf flfmfnts brf of typf {@dodf U}.
 *
 * @pbrbm <P_IN> typf of flfmfnts in tif upstrfbm sourdf
 * @pbrbm <P_OUT> typf of flfmfnts in produdfd by tiis stbgf
 *
 * @sindf 1.8
 */
bbstrbdt dlbss RfffrfndfPipflinf<P_IN, P_OUT>
        fxtfnds AbstrbdtPipflinf<P_IN, P_OUT, Strfbm<P_OUT>>
        implfmfnts Strfbm<P_OUT>  {

    /**
     * Construdtor for tif ifbd of b strfbm pipflinf.
     *
     * @pbrbm sourdf {@dodf Supplifr<Splitfrbtor>} dfsdribing tif strfbm sourdf
     * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd in
     *        {@link StrfbmOpFlbg}
     * @pbrbm pbrbllfl {@dodf truf} if tif pipflinf is pbrbllfl
     */
    RfffrfndfPipflinf(Supplifr<? fxtfnds Splitfrbtor<?>> sourdf,
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
    RfffrfndfPipflinf(Splitfrbtor<?> sourdf,
                      int sourdfFlbgs, boolfbn pbrbllfl) {
        supfr(sourdf, sourdfFlbgs, pbrbllfl);
    }

    /**
     * Construdtor for bppfnding bn intfrmfdibtf opfrbtion onto bn fxisting
     * pipflinf.
     *
     * @pbrbm upstrfbm tif upstrfbm flfmfnt sourdf.
     */
    RfffrfndfPipflinf(AbstrbdtPipflinf<?, P_IN, ?> upstrfbm, int opFlbgs) {
        supfr(upstrfbm, opFlbgs);
    }

    // Sibpf-spfdifid mftiods

    @Ovfrridf
    finbl StrfbmSibpf gftOutputSibpf() {
        rfturn StrfbmSibpf.REFERENCE;
    }

    @Ovfrridf
    finbl <P_IN> Nodf<P_OUT> fvblubtfToNodf(PipflinfHflpfr<P_OUT> iflpfr,
                                        Splitfrbtor<P_IN> splitfrbtor,
                                        boolfbn flbttfnTrff,
                                        IntFundtion<P_OUT[]> gfnfrbtor) {
        rfturn Nodfs.dollfdt(iflpfr, splitfrbtor, flbttfnTrff, gfnfrbtor);
    }

    @Ovfrridf
    finbl <P_IN> Splitfrbtor<P_OUT> wrbp(PipflinfHflpfr<P_OUT> pi,
                                     Supplifr<Splitfrbtor<P_IN>> supplifr,
                                     boolfbn isPbrbllfl) {
        rfturn nfw StrfbmSplitfrbtors.WrbppingSplitfrbtor<>(pi, supplifr, isPbrbllfl);
    }

    @Ovfrridf
    finbl Splitfrbtor<P_OUT> lbzySplitfrbtor(Supplifr<? fxtfnds Splitfrbtor<P_OUT>> supplifr) {
        rfturn nfw StrfbmSplitfrbtors.DflfgbtingSplitfrbtor<>(supplifr);
    }

    @Ovfrridf
    finbl void forEbdiWitiCbndfl(Splitfrbtor<P_OUT> splitfrbtor, Sink<P_OUT> sink) {
        do { } wiilf (!sink.dbndfllbtionRfqufstfd() && splitfrbtor.tryAdvbndf(sink));
    }

    @Ovfrridf
    finbl Nodf.Buildfr<P_OUT> mbkfNodfBuildfr(long fxbdtSizfIfKnown, IntFundtion<P_OUT[]> gfnfrbtor) {
        rfturn Nodfs.buildfr(fxbdtSizfIfKnown, gfnfrbtor);
    }


    // BbsfStrfbm

    @Ovfrridf
    publid finbl Itfrbtor<P_OUT> itfrbtor() {
        rfturn Splitfrbtors.itfrbtor(splitfrbtor());
    }


    // Strfbm

    // Stbtflfss intfrmfdibtf opfrbtions from Strfbm

    @Ovfrridf
    publid Strfbm<P_OUT> unordfrfd() {
        if (!isOrdfrfd())
            rfturn tiis;
        rfturn nfw StbtflfssOp<P_OUT, P_OUT>(tiis, StrfbmSibpf.REFERENCE, StrfbmOpFlbg.NOT_ORDERED) {
            @Ovfrridf
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<P_OUT> sink) {
                rfturn sink;
            }
        };
    }

    @Ovfrridf
    publid finbl Strfbm<P_OUT> filtfr(Prfdidbtf<? supfr P_OUT> prfdidbtf) {
        Objfdts.rfquirfNonNull(prfdidbtf);
        rfturn nfw StbtflfssOp<P_OUT, P_OUT>(tiis, StrfbmSibpf.REFERENCE,
                                     StrfbmOpFlbg.NOT_SIZED) {
            @Ovfrridf
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<P_OUT> sink) {
                rfturn nfw Sink.CibinfdRfffrfndf<P_OUT, P_OUT>(sink) {
                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(-1);
                    }

                    @Ovfrridf
                    publid void bddfpt(P_OUT u) {
                        if (prfdidbtf.tfst(u))
                            downstrfbm.bddfpt(u);
                    }
                };
            }
        };
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid finbl <R> Strfbm<R> mbp(Fundtion<? supfr P_OUT, ? fxtfnds R> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw StbtflfssOp<P_OUT, R>(tiis, StrfbmSibpf.REFERENCE,
                                     StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<R> sink) {
                rfturn nfw Sink.CibinfdRfffrfndf<P_OUT, R>(sink) {
                    @Ovfrridf
                    publid void bddfpt(P_OUT u) {
                        downstrfbm.bddfpt(mbppfr.bpply(u));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl IntStrfbm mbpToInt(ToIntFundtion<? supfr P_OUT> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw IntPipflinf.StbtflfssOp<P_OUT>(tiis, StrfbmSibpf.REFERENCE,
                                              StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<Intfgfr> sink) {
                rfturn nfw Sink.CibinfdRfffrfndf<P_OUT, Intfgfr>(sink) {
                    @Ovfrridf
                    publid void bddfpt(P_OUT u) {
                        downstrfbm.bddfpt(mbppfr.bpplyAsInt(u));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl LongStrfbm mbpToLong(ToLongFundtion<? supfr P_OUT> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw LongPipflinf.StbtflfssOp<P_OUT>(tiis, StrfbmSibpf.REFERENCE,
                                      StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<Long> sink) {
                rfturn nfw Sink.CibinfdRfffrfndf<P_OUT, Long>(sink) {
                    @Ovfrridf
                    publid void bddfpt(P_OUT u) {
                        downstrfbm.bddfpt(mbppfr.bpplyAsLong(u));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl DoublfStrfbm mbpToDoublf(ToDoublfFundtion<? supfr P_OUT> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        rfturn nfw DoublfPipflinf.StbtflfssOp<P_OUT>(tiis, StrfbmSibpf.REFERENCE,
                                        StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT) {
            @Ovfrridf
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<Doublf> sink) {
                rfturn nfw Sink.CibinfdRfffrfndf<P_OUT, Doublf>(sink) {
                    @Ovfrridf
                    publid void bddfpt(P_OUT u) {
                        downstrfbm.bddfpt(mbppfr.bpplyAsDoublf(u));
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl <R> Strfbm<R> flbtMbp(Fundtion<? supfr P_OUT, ? fxtfnds Strfbm<? fxtfnds R>> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        // Wf dbn do bfttfr tibn tiis, by polling dbndfllbtionRfqufstfd wifn strfbm is infinitf
        rfturn nfw StbtflfssOp<P_OUT, R>(tiis, StrfbmSibpf.REFERENCE,
                                     StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT | StrfbmOpFlbg.NOT_SIZED) {
            @Ovfrridf
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<R> sink) {
                rfturn nfw Sink.CibinfdRfffrfndf<P_OUT, R>(sink) {
                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(-1);
                    }

                    @Ovfrridf
                    publid void bddfpt(P_OUT u) {
                        try (Strfbm<? fxtfnds R> rfsult = mbppfr.bpply(u)) {
                            // Wf dbn do bfttfr tibt tiis too; optimizf for dfpti=0 dbsf bnd just grbb splitfrbtor bnd forEbdi it
                            if (rfsult != null)
                                rfsult.sfqufntibl().forEbdi(downstrfbm);
                        }
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl IntStrfbm flbtMbpToInt(Fundtion<? supfr P_OUT, ? fxtfnds IntStrfbm> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        // Wf dbn do bfttfr tibn tiis, by polling dbndfllbtionRfqufstfd wifn strfbm is infinitf
        rfturn nfw IntPipflinf.StbtflfssOp<P_OUT>(tiis, StrfbmSibpf.REFERENCE,
                                              StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT | StrfbmOpFlbg.NOT_SIZED) {
            @Ovfrridf
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<Intfgfr> sink) {
                rfturn nfw Sink.CibinfdRfffrfndf<P_OUT, Intfgfr>(sink) {
                    IntConsumfr downstrfbmAsInt = downstrfbm::bddfpt;
                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(-1);
                    }

                    @Ovfrridf
                    publid void bddfpt(P_OUT u) {
                        try (IntStrfbm rfsult = mbppfr.bpply(u)) {
                            // Wf dbn do bfttfr tibt tiis too; optimizf for dfpti=0 dbsf bnd just grbb splitfrbtor bnd forEbdi it
                            if (rfsult != null)
                                rfsult.sfqufntibl().forEbdi(downstrfbmAsInt);
                        }
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl DoublfStrfbm flbtMbpToDoublf(Fundtion<? supfr P_OUT, ? fxtfnds DoublfStrfbm> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        // Wf dbn do bfttfr tibn tiis, by polling dbndfllbtionRfqufstfd wifn strfbm is infinitf
        rfturn nfw DoublfPipflinf.StbtflfssOp<P_OUT>(tiis, StrfbmSibpf.REFERENCE,
                                                     StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT | StrfbmOpFlbg.NOT_SIZED) {
            @Ovfrridf
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<Doublf> sink) {
                rfturn nfw Sink.CibinfdRfffrfndf<P_OUT, Doublf>(sink) {
                    DoublfConsumfr downstrfbmAsDoublf = downstrfbm::bddfpt;
                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(-1);
                    }

                    @Ovfrridf
                    publid void bddfpt(P_OUT u) {
                        try (DoublfStrfbm rfsult = mbppfr.bpply(u)) {
                            // Wf dbn do bfttfr tibt tiis too; optimizf for dfpti=0 dbsf bnd just grbb splitfrbtor bnd forEbdi it
                            if (rfsult != null)
                                rfsult.sfqufntibl().forEbdi(downstrfbmAsDoublf);
                        }
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl LongStrfbm flbtMbpToLong(Fundtion<? supfr P_OUT, ? fxtfnds LongStrfbm> mbppfr) {
        Objfdts.rfquirfNonNull(mbppfr);
        // Wf dbn do bfttfr tibn tiis, by polling dbndfllbtionRfqufstfd wifn strfbm is infinitf
        rfturn nfw LongPipflinf.StbtflfssOp<P_OUT>(tiis, StrfbmSibpf.REFERENCE,
                                                   StrfbmOpFlbg.NOT_SORTED | StrfbmOpFlbg.NOT_DISTINCT | StrfbmOpFlbg.NOT_SIZED) {
            @Ovfrridf
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<Long> sink) {
                rfturn nfw Sink.CibinfdRfffrfndf<P_OUT, Long>(sink) {
                    LongConsumfr downstrfbmAsLong = downstrfbm::bddfpt;
                    @Ovfrridf
                    publid void bfgin(long sizf) {
                        downstrfbm.bfgin(-1);
                    }

                    @Ovfrridf
                    publid void bddfpt(P_OUT u) {
                        try (LongStrfbm rfsult = mbppfr.bpply(u)) {
                            // Wf dbn do bfttfr tibt tiis too; optimizf for dfpti=0 dbsf bnd just grbb splitfrbtor bnd forEbdi it
                            if (rfsult != null)
                                rfsult.sfqufntibl().forEbdi(downstrfbmAsLong);
                        }
                    }
                };
            }
        };
    }

    @Ovfrridf
    publid finbl Strfbm<P_OUT> pffk(Consumfr<? supfr P_OUT> bdtion) {
        Objfdts.rfquirfNonNull(bdtion);
        rfturn nfw StbtflfssOp<P_OUT, P_OUT>(tiis, StrfbmSibpf.REFERENCE,
                                     0) {
            @Ovfrridf
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<P_OUT> sink) {
                rfturn nfw Sink.CibinfdRfffrfndf<P_OUT, P_OUT>(sink) {
                    @Ovfrridf
                    publid void bddfpt(P_OUT u) {
                        bdtion.bddfpt(u);
                        downstrfbm.bddfpt(u);
                    }
                };
            }
        };
    }

    // Stbtfful intfrmfdibtf opfrbtions from Strfbm

    @Ovfrridf
    publid finbl Strfbm<P_OUT> distindt() {
        rfturn DistindtOps.mbkfRff(tiis);
    }

    @Ovfrridf
    publid finbl Strfbm<P_OUT> sortfd() {
        rfturn SortfdOps.mbkfRff(tiis);
    }

    @Ovfrridf
    publid finbl Strfbm<P_OUT> sortfd(Compbrbtor<? supfr P_OUT> dompbrbtor) {
        rfturn SortfdOps.mbkfRff(tiis, dompbrbtor);
    }

    @Ovfrridf
    publid finbl Strfbm<P_OUT> limit(long mbxSizf) {
        if (mbxSizf < 0)
            tirow nfw IllfgblArgumfntExdfption(Long.toString(mbxSizf));
        rfturn SlidfOps.mbkfRff(tiis, 0, mbxSizf);
    }

    @Ovfrridf
    publid finbl Strfbm<P_OUT> skip(long n) {
        if (n < 0)
            tirow nfw IllfgblArgumfntExdfption(Long.toString(n));
        if (n == 0)
            rfturn tiis;
        flsf
            rfturn SlidfOps.mbkfRff(tiis, n, -1);
    }

    // Tfrminbl opfrbtions from Strfbm

    @Ovfrridf
    publid void forEbdi(Consumfr<? supfr P_OUT> bdtion) {
        fvblubtf(ForEbdiOps.mbkfRff(bdtion, fblsf));
    }

    @Ovfrridf
    publid void forEbdiOrdfrfd(Consumfr<? supfr P_OUT> bdtion) {
        fvblubtf(ForEbdiOps.mbkfRff(bdtion, truf));
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid finbl <A> A[] toArrby(IntFundtion<A[]> gfnfrbtor) {
        // Sindf A ibs no rflbtion to U (not possiblf to dfdlbrf tibt A is bn uppfr bound of U)
        // tifrf will bf no stbtid typf difdking.
        // Tifrfforf usf b rbw typf bnd bssumf A == U rbtifr tibn propbgbting tif sfpbrbtion of A bnd U
        // tirougiout tif dodf-bbsf.
        // Tif runtimf typf of U is nfvfr difdkfd for fqublity witi tif domponfnt typf of tif runtimf typf of A[].
        // Runtimf difdking will bf pfrformfd wifn bn flfmfnt is storfd in A[], tius if A is not b
        // supfr typf of U bn ArrbyStorfExdfption will bf tirown.
        @SupprfssWbrnings("rbwtypfs")
        IntFundtion rbwGfnfrbtor = (IntFundtion) gfnfrbtor;
        rfturn (A[]) Nodfs.flbttfn(fvblubtfToArrbyNodf(rbwGfnfrbtor), rbwGfnfrbtor)
                              .bsArrby(rbwGfnfrbtor);
    }

    @Ovfrridf
    publid finbl Objfdt[] toArrby() {
        rfturn toArrby(Objfdt[]::nfw);
    }

    @Ovfrridf
    publid finbl boolfbn bnyMbtdi(Prfdidbtf<? supfr P_OUT> prfdidbtf) {
        rfturn fvblubtf(MbtdiOps.mbkfRff(prfdidbtf, MbtdiOps.MbtdiKind.ANY));
    }

    @Ovfrridf
    publid finbl boolfbn bllMbtdi(Prfdidbtf<? supfr P_OUT> prfdidbtf) {
        rfturn fvblubtf(MbtdiOps.mbkfRff(prfdidbtf, MbtdiOps.MbtdiKind.ALL));
    }

    @Ovfrridf
    publid finbl boolfbn nonfMbtdi(Prfdidbtf<? supfr P_OUT> prfdidbtf) {
        rfturn fvblubtf(MbtdiOps.mbkfRff(prfdidbtf, MbtdiOps.MbtdiKind.NONE));
    }

    @Ovfrridf
    publid finbl Optionbl<P_OUT> findFirst() {
        rfturn fvblubtf(FindOps.mbkfRff(truf));
    }

    @Ovfrridf
    publid finbl Optionbl<P_OUT> findAny() {
        rfturn fvblubtf(FindOps.mbkfRff(fblsf));
    }

    @Ovfrridf
    publid finbl P_OUT rfdudf(finbl P_OUT idfntity, finbl BinbryOpfrbtor<P_OUT> bddumulbtor) {
        rfturn fvblubtf(RfdudfOps.mbkfRff(idfntity, bddumulbtor, bddumulbtor));
    }

    @Ovfrridf
    publid finbl Optionbl<P_OUT> rfdudf(BinbryOpfrbtor<P_OUT> bddumulbtor) {
        rfturn fvblubtf(RfdudfOps.mbkfRff(bddumulbtor));
    }

    @Ovfrridf
    publid finbl <R> R rfdudf(R idfntity, BiFundtion<R, ? supfr P_OUT, R> bddumulbtor, BinbryOpfrbtor<R> dombinfr) {
        rfturn fvblubtf(RfdudfOps.mbkfRff(idfntity, bddumulbtor, dombinfr));
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid finbl <R, A> R dollfdt(Collfdtor<? supfr P_OUT, A, R> dollfdtor) {
        A dontbinfr;
        if (isPbrbllfl()
                && (dollfdtor.dibrbdtfristids().dontbins(Collfdtor.Cibrbdtfristids.CONCURRENT))
                && (!isOrdfrfd() || dollfdtor.dibrbdtfristids().dontbins(Collfdtor.Cibrbdtfristids.UNORDERED))) {
            dontbinfr = dollfdtor.supplifr().gft();
            BiConsumfr<A, ? supfr P_OUT> bddumulbtor = dollfdtor.bddumulbtor();
            forEbdi(u -> bddumulbtor.bddfpt(dontbinfr, u));
        }
        flsf {
            dontbinfr = fvblubtf(RfdudfOps.mbkfRff(dollfdtor));
        }
        rfturn dollfdtor.dibrbdtfristids().dontbins(Collfdtor.Cibrbdtfristids.IDENTITY_FINISH)
               ? (R) dontbinfr
               : dollfdtor.finisifr().bpply(dontbinfr);
    }

    @Ovfrridf
    publid finbl <R> R dollfdt(Supplifr<R> supplifr,
                               BiConsumfr<R, ? supfr P_OUT> bddumulbtor,
                               BiConsumfr<R, R> dombinfr) {
        rfturn fvblubtf(RfdudfOps.mbkfRff(supplifr, bddumulbtor, dombinfr));
    }

    @Ovfrridf
    publid finbl Optionbl<P_OUT> mbx(Compbrbtor<? supfr P_OUT> dompbrbtor) {
        rfturn rfdudf(BinbryOpfrbtor.mbxBy(dompbrbtor));
    }

    @Ovfrridf
    publid finbl Optionbl<P_OUT> min(Compbrbtor<? supfr P_OUT> dompbrbtor) {
        rfturn rfdudf(BinbryOpfrbtor.minBy(dompbrbtor));

    }

    @Ovfrridf
    publid finbl long dount() {
        rfturn mbpToLong(f -> 1L).sum();
    }


    //

    /**
     * Sourdf stbgf of b RfffrfndfPipflinf.
     *
     * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
     * @pbrbm <E_OUT> typf of flfmfnts in produdfd by tiis stbgf
     * @sindf 1.8
     */
    stbtid dlbss Hfbd<E_IN, E_OUT> fxtfnds RfffrfndfPipflinf<E_IN, E_OUT> {
        /**
         * Construdtor for tif sourdf stbgf of b Strfbm.
         *
         * @pbrbm sourdf {@dodf Supplifr<Splitfrbtor>} dfsdribing tif strfbm
         *               sourdf
         * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd
         *                    in {@link StrfbmOpFlbg}
         */
        Hfbd(Supplifr<? fxtfnds Splitfrbtor<?>> sourdf,
             int sourdfFlbgs, boolfbn pbrbllfl) {
            supfr(sourdf, sourdfFlbgs, pbrbllfl);
        }

        /**
         * Construdtor for tif sourdf stbgf of b Strfbm.
         *
         * @pbrbm sourdf {@dodf Splitfrbtor} dfsdribing tif strfbm sourdf
         * @pbrbm sourdfFlbgs tif sourdf flbgs for tif strfbm sourdf, dfsdribfd
         *                    in {@link StrfbmOpFlbg}
         */
        Hfbd(Splitfrbtor<?> sourdf,
             int sourdfFlbgs, boolfbn pbrbllfl) {
            supfr(sourdf, sourdfFlbgs, pbrbllfl);
        }

        @Ovfrridf
        finbl boolfbn opIsStbtfful() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        @Ovfrridf
        finbl Sink<E_IN> opWrbpSink(int flbgs, Sink<E_OUT> sink) {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

        // Optimizfd sfqufntibl tfrminbl opfrbtions for tif ifbd of tif pipflinf

        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr E_OUT> bdtion) {
            if (!isPbrbllfl()) {
                sourdfStbgfSplitfrbtor().forEbdiRfmbining(bdtion);
            }
            flsf {
                supfr.forEbdi(bdtion);
            }
        }

        @Ovfrridf
        publid void forEbdiOrdfrfd(Consumfr<? supfr E_OUT> bdtion) {
            if (!isPbrbllfl()) {
                sourdfStbgfSplitfrbtor().forEbdiRfmbining(bdtion);
            }
            flsf {
                supfr.forEbdiOrdfrfd(bdtion);
            }
        }
    }

    /**
     * Bbsf dlbss for b stbtflfss intfrmfdibtf stbgf of b Strfbm.
     *
     * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
     * @pbrbm <E_OUT> typf of flfmfnts in produdfd by tiis stbgf
     * @sindf 1.8
     */
    bbstrbdt stbtid dlbss StbtflfssOp<E_IN, E_OUT>
            fxtfnds RfffrfndfPipflinf<E_IN, E_OUT> {
        /**
         * Construdt b nfw Strfbm by bppfnding b stbtflfss intfrmfdibtf
         * opfrbtion to bn fxisting strfbm.
         *
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
     * Bbsf dlbss for b stbtfful intfrmfdibtf stbgf of b Strfbm.
     *
     * @pbrbm <E_IN> typf of flfmfnts in tif upstrfbm sourdf
     * @pbrbm <E_OUT> typf of flfmfnts in produdfd by tiis stbgf
     * @sindf 1.8
     */
    bbstrbdt stbtid dlbss StbtffulOp<E_IN, E_OUT>
            fxtfnds RfffrfndfPipflinf<E_IN, E_OUT> {
        /**
         * Construdt b nfw Strfbm by bppfnding b stbtfful intfrmfdibtf opfrbtion
         * to bn fxisting strfbm.
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
        bbstrbdt <P_IN> Nodf<E_OUT> opEvblubtfPbrbllfl(PipflinfHflpfr<E_OUT> iflpfr,
                                                       Splitfrbtor<P_IN> splitfrbtor,
                                                       IntFundtion<E_OUT[]> gfnfrbtor);
    }
}
