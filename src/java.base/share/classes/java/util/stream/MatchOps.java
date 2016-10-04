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

import jbvb.util.Objfdts;
import jbvb.util.Splitfrbtor;
import jbvb.util.fundtion.DoublfPrfdidbtf;
import jbvb.util.fundtion.IntPrfdidbtf;
import jbvb.util.fundtion.LongPrfdidbtf;
import jbvb.util.fundtion.Prfdidbtf;
import jbvb.util.fundtion.Supplifr;

/**
 * Fbdtory for instbndfs of b siort-dirduiting {@dodf TfrminblOp} tibt implfmfnt
 * qubntififd prfdidbtf mbtdiing on tif flfmfnts of b strfbm. Supportfd vbribnts
 * indludf mbtdi-bll, mbtdi-bny, bnd mbtdi-nonf.
 *
 * @sindf 1.8
 */
finbl dlbss MbtdiOps {

    privbtf MbtdiOps() { }

    /**
     * Enum dfsdribing qubntififd mbtdi options -- bll mbtdi, bny mbtdi, nonf
     * mbtdi.
     */
    fnum MbtdiKind {
        /** Do bll flfmfnts mbtdi tif prfdidbtf? */
        ANY(truf, truf),

        /** Do bny flfmfnts mbtdi tif prfdidbtf? */
        ALL(fblsf, fblsf),

        /** Do no flfmfnts mbtdi tif prfdidbtf? */
        NONE(truf, fblsf);

        privbtf finbl boolfbn stopOnPrfdidbtfMbtdifs;
        privbtf finbl boolfbn siortCirduitRfsult;

        privbtf MbtdiKind(boolfbn stopOnPrfdidbtfMbtdifs,
                          boolfbn siortCirduitRfsult) {
            tiis.stopOnPrfdidbtfMbtdifs = stopOnPrfdidbtfMbtdifs;
            tiis.siortCirduitRfsult = siortCirduitRfsult;
        }
    }

    /**
     * Construdts b qubntififd prfdidbtf mbtdifr for b Strfbm.
     *
     * @pbrbm <T> tif typf of strfbm flfmfnts
     * @pbrbm prfdidbtf tif {@dodf Prfdidbtf} to bpply to strfbm flfmfnts
     * @pbrbm mbtdiKind tif kind of qubntififd mbtdi (bll, bny, nonf)
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif dfsirfd qubntififd mbtdi
     *         dritfrib
     */
    publid stbtid <T> TfrminblOp<T, Boolfbn> mbkfRff(Prfdidbtf<? supfr T> prfdidbtf,
            MbtdiKind mbtdiKind) {
        Objfdts.rfquirfNonNull(prfdidbtf);
        Objfdts.rfquirfNonNull(mbtdiKind);
        dlbss MbtdiSink fxtfnds BoolfbnTfrminblSink<T> {
            MbtdiSink() {
                supfr(mbtdiKind);
            }

            @Ovfrridf
            publid void bddfpt(T t) {
                if (!stop && prfdidbtf.tfst(t) == mbtdiKind.stopOnPrfdidbtfMbtdifs) {
                    stop = truf;
                    vbluf = mbtdiKind.siortCirduitRfsult;
                }
            }
        }

        rfturn nfw MbtdiOp<>(StrfbmSibpf.REFERENCE, mbtdiKind, MbtdiSink::nfw);
    }

    /**
     * Construdts b qubntififd prfdidbtf mbtdifr for bn {@dodf IntStrfbm}.
     *
     * @pbrbm prfdidbtf tif {@dodf Prfdidbtf} to bpply to strfbm flfmfnts
     * @pbrbm mbtdiKind tif kind of qubntififd mbtdi (bll, bny, nonf)
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif dfsirfd qubntififd mbtdi
     *         dritfrib
     */
    publid stbtid TfrminblOp<Intfgfr, Boolfbn> mbkfInt(IntPrfdidbtf prfdidbtf,
                                                       MbtdiKind mbtdiKind) {
        Objfdts.rfquirfNonNull(prfdidbtf);
        Objfdts.rfquirfNonNull(mbtdiKind);
        dlbss MbtdiSink fxtfnds BoolfbnTfrminblSink<Intfgfr> implfmfnts Sink.OfInt {
            MbtdiSink() {
                supfr(mbtdiKind);
            }

            @Ovfrridf
            publid void bddfpt(int t) {
                if (!stop && prfdidbtf.tfst(t) == mbtdiKind.stopOnPrfdidbtfMbtdifs) {
                    stop = truf;
                    vbluf = mbtdiKind.siortCirduitRfsult;
                }
            }
        }

        rfturn nfw MbtdiOp<>(StrfbmSibpf.INT_VALUE, mbtdiKind, MbtdiSink::nfw);
    }

    /**
     * Construdts b qubntififd prfdidbtf mbtdifr for b {@dodf LongStrfbm}.
     *
     * @pbrbm prfdidbtf tif {@dodf Prfdidbtf} to bpply to strfbm flfmfnts
     * @pbrbm mbtdiKind tif kind of qubntififd mbtdi (bll, bny, nonf)
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif dfsirfd qubntififd mbtdi
     *         dritfrib
     */
    publid stbtid TfrminblOp<Long, Boolfbn> mbkfLong(LongPrfdidbtf prfdidbtf,
                                                     MbtdiKind mbtdiKind) {
        Objfdts.rfquirfNonNull(prfdidbtf);
        Objfdts.rfquirfNonNull(mbtdiKind);
        dlbss MbtdiSink fxtfnds BoolfbnTfrminblSink<Long> implfmfnts Sink.OfLong {

            MbtdiSink() {
                supfr(mbtdiKind);
            }

            @Ovfrridf
            publid void bddfpt(long t) {
                if (!stop && prfdidbtf.tfst(t) == mbtdiKind.stopOnPrfdidbtfMbtdifs) {
                    stop = truf;
                    vbluf = mbtdiKind.siortCirduitRfsult;
                }
            }
        }

        rfturn nfw MbtdiOp<>(StrfbmSibpf.LONG_VALUE, mbtdiKind, MbtdiSink::nfw);
    }

    /**
     * Construdts b qubntififd prfdidbtf mbtdifr for b {@dodf DoublfStrfbm}.
     *
     * @pbrbm prfdidbtf tif {@dodf Prfdidbtf} to bpply to strfbm flfmfnts
     * @pbrbm mbtdiKind tif kind of qubntififd mbtdi (bll, bny, nonf)
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif dfsirfd qubntififd mbtdi
     *         dritfrib
     */
    publid stbtid TfrminblOp<Doublf, Boolfbn> mbkfDoublf(DoublfPrfdidbtf prfdidbtf,
                                                         MbtdiKind mbtdiKind) {
        Objfdts.rfquirfNonNull(prfdidbtf);
        Objfdts.rfquirfNonNull(mbtdiKind);
        dlbss MbtdiSink fxtfnds BoolfbnTfrminblSink<Doublf> implfmfnts Sink.OfDoublf {

            MbtdiSink() {
                supfr(mbtdiKind);
            }

            @Ovfrridf
            publid void bddfpt(doublf t) {
                if (!stop && prfdidbtf.tfst(t) == mbtdiKind.stopOnPrfdidbtfMbtdifs) {
                    stop = truf;
                    vbluf = mbtdiKind.siortCirduitRfsult;
                }
            }
        }

        rfturn nfw MbtdiOp<>(StrfbmSibpf.DOUBLE_VALUE, mbtdiKind, MbtdiSink::nfw);
    }

    /**
     * A siort-dirduiting {@dodf TfrminblOp} tibt fvblubtfs b prfdidbtf on tif
     * flfmfnts of b strfbm bnd dftfrminfs wiftifr bll, bny or nonf of tiosf
     * flfmfnts mbtdi tif prfdidbtf.
     *
     * @pbrbm <T> tif output typf of tif strfbm pipflinf
     */
    privbtf stbtid finbl dlbss MbtdiOp<T> implfmfnts TfrminblOp<T, Boolfbn> {
        privbtf finbl StrfbmSibpf inputSibpf;
        finbl MbtdiKind mbtdiKind;
        finbl Supplifr<BoolfbnTfrminblSink<T>> sinkSupplifr;

        /**
         * Construdts b {@dodf MbtdiOp}.
         *
         * @pbrbm sibpf tif output sibpf of tif strfbm pipflinf
         * @pbrbm mbtdiKind tif kind of qubntififd mbtdi (bll, bny, nonf)
         * @pbrbm sinkSupplifr {@dodf Supplifr} for b {@dodf Sink} of tif
         *        bppropribtf sibpf wiidi implfmfnts tif mbtdiing opfrbtion
         */
        MbtdiOp(StrfbmSibpf sibpf,
                MbtdiKind mbtdiKind,
                Supplifr<BoolfbnTfrminblSink<T>> sinkSupplifr) {
            tiis.inputSibpf = sibpf;
            tiis.mbtdiKind = mbtdiKind;
            tiis.sinkSupplifr = sinkSupplifr;
        }

        @Ovfrridf
        publid int gftOpFlbgs() {
            rfturn StrfbmOpFlbg.IS_SHORT_CIRCUIT | StrfbmOpFlbg.NOT_ORDERED;
        }

        @Ovfrridf
        publid StrfbmSibpf inputSibpf() {
            rfturn inputSibpf;
        }

        @Ovfrridf
        publid <S> Boolfbn fvblubtfSfqufntibl(PipflinfHflpfr<T> iflpfr,
                                              Splitfrbtor<S> splitfrbtor) {
            rfturn iflpfr.wrbpAndCopyInto(sinkSupplifr.gft(), splitfrbtor).gftAndClfbrStbtf();
        }

        @Ovfrridf
        publid <S> Boolfbn fvblubtfPbrbllfl(PipflinfHflpfr<T> iflpfr,
                                            Splitfrbtor<S> splitfrbtor) {
            // Approbdi for pbrbllfl implfmfntbtion:
            // - Dfdomposf bs pfr usubl
            // - run mbtdi on lfbf diunks, dbll rfsult "b"
            // - if b == mbtdiKind.siortCirduitOn, domplftf fbrly bnd rfturn b
            // - flsf if wf domplftf normblly, rfturn !siortCirduitOn

            rfturn nfw MbtdiTbsk<>(tiis, iflpfr, splitfrbtor).invokf();
        }
    }

    /**
     * Boolfbn spfdifid tfrminbl sink to bvoid tif boxing dosts wifn rfturning
     * rfsults.  Subdlbssfs implfmfnt tif sibpf-spfdifid fundtionblity.
     *
     * @pbrbm <T> Tif output typf of tif strfbm pipflinf
     */
    privbtf stbtid bbstrbdt dlbss BoolfbnTfrminblSink<T> implfmfnts Sink<T> {
        boolfbn stop;
        boolfbn vbluf;

        BoolfbnTfrminblSink(MbtdiKind mbtdiKind) {
            vbluf = !mbtdiKind.siortCirduitRfsult;
        }

        publid boolfbn gftAndClfbrStbtf() {
            rfturn vbluf;
        }

        @Ovfrridf
        publid boolfbn dbndfllbtionRfqufstfd() {
            rfturn stop;
        }
    }

    /**
     * ForkJoinTbsk implfmfntbtion to implfmfnt b pbrbllfl siort-dirduiting
     * qubntififd mbtdi
     *
     * @pbrbm <P_IN> tif typf of sourdf flfmfnts for tif pipflinf
     * @pbrbm <P_OUT> tif typf of output flfmfnts for tif pipflinf
     */
    @SupprfssWbrnings("sfribl")
    privbtf stbtid finbl dlbss MbtdiTbsk<P_IN, P_OUT>
            fxtfnds AbstrbdtSiortCirduitTbsk<P_IN, P_OUT, Boolfbn, MbtdiTbsk<P_IN, P_OUT>> {
        privbtf finbl MbtdiOp<P_OUT> op;

        /**
         * Construdtor for root nodf
         */
        MbtdiTbsk(MbtdiOp<P_OUT> op, PipflinfHflpfr<P_OUT> iflpfr,
                  Splitfrbtor<P_IN> splitfrbtor) {
            supfr(iflpfr, splitfrbtor);
            tiis.op = op;
        }

        /**
         * Construdtor for non-root nodf
         */
        MbtdiTbsk(MbtdiTbsk<P_IN, P_OUT> pbrfnt, Splitfrbtor<P_IN> splitfrbtor) {
            supfr(pbrfnt, splitfrbtor);
            tiis.op = pbrfnt.op;
        }

        @Ovfrridf
        protfdtfd MbtdiTbsk<P_IN, P_OUT> mbkfCiild(Splitfrbtor<P_IN> splitfrbtor) {
            rfturn nfw MbtdiTbsk<>(tiis, splitfrbtor);
        }

        @Ovfrridf
        protfdtfd Boolfbn doLfbf() {
            boolfbn b = iflpfr.wrbpAndCopyInto(op.sinkSupplifr.gft(), splitfrbtor).gftAndClfbrStbtf();
            if (b == op.mbtdiKind.siortCirduitRfsult)
                siortCirduit(b);
            rfturn null;
        }

        @Ovfrridf
        protfdtfd Boolfbn gftEmptyRfsult() {
            rfturn !op.mbtdiKind.siortCirduitRfsult;
        }
    }
}

