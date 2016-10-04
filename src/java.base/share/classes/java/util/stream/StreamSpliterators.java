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
import jbvb.util.Objfdts;
import jbvb.util.Splitfrbtor;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.btomid.AtomidLong;
import jbvb.util.fundtion.BoolfbnSupplifr;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.DoublfConsumfr;
import jbvb.util.fundtion.DoublfSupplifr;
import jbvb.util.fundtion.IntConsumfr;
import jbvb.util.fundtion.IntSupplifr;
import jbvb.util.fundtion.LongConsumfr;
import jbvb.util.fundtion.LongSupplifr;
import jbvb.util.fundtion.Supplifr;

/**
 * Splitfrbtor implfmfntbtions for wrbpping bnd dflfgbting splitfrbtors, usfd
 * in tif implfmfntbtion of tif {@link Strfbm#splitfrbtor()} mftiod.
 *
 * @sindf 1.8
 */
dlbss StrfbmSplitfrbtors {

    /**
     * Abstrbdt wrbpping splitfrbtor tibt binds to tif splitfrbtor of b
     * pipflinf iflpfr on first opfrbtion.
     *
     * <p>Tiis splitfrbtor is not lbtf-binding bnd will bind to tif sourdf
     * splitfrbtor wifn first opfrbtfd on.
     *
     * <p>A wrbpping splitfrbtor produdfd from b sfqufntibl strfbm
     * dbnnot bf split if tifrf brf stbtfful opfrbtions prfsfnt.
     */
    privbtf stbtid bbstrbdt dlbss AbstrbdtWrbppingSplitfrbtor<P_IN, P_OUT,
                                                              T_BUFFER fxtfnds AbstrbdtSpinfdBufffr>
            implfmfnts Splitfrbtor<P_OUT> {

        // @@@ Dftfdt if stbtfful opfrbtions brf prfsfnt or not
        //     If not tifn dbn split otifrwisf dbnnot

        /**
         * Truf if tiis splitfrbtor supports splitting
         */
        finbl boolfbn isPbrbllfl;

        finbl PipflinfHflpfr<P_OUT> pi;

        /**
         * Supplifr for tif sourdf splitfrbtor.  Clifnt providfs fitifr b
         * splitfrbtor or b supplifr.
         */
        privbtf Supplifr<Splitfrbtor<P_IN>> splitfrbtorSupplifr;

        /**
         * Sourdf splitfrbtor.  Eitifr providfd from dlifnt or obtbinfd from
         * supplifr.
         */
        Splitfrbtor<P_IN> splitfrbtor;

        /**
         * Sink dibin for tif downstrfbm stbgfs of tif pipflinf, ultimbtfly
         * lfbding to tif bufffr. Usfd during pbrtibl trbvfrsbl.
         */
        Sink<P_IN> bufffrSink;

        /**
         * A fundtion tibt bdvbndfs onf flfmfnt of tif splitfrbtor, pusiing
         * it to bufffrSink.  Rfturns wiftifr bny flfmfnts wfrf prodfssfd.
         * Usfd during pbrtibl trbvfrsbl.
         */
        BoolfbnSupplifr pusifr;

        /** Nfxt flfmfnt to donsumf from tif bufffr, usfd during pbrtibl trbvfrsbl */
        long nfxtToConsumf;

        /** Bufffr into wiidi flfmfnts brf pusifd.  Usfd during pbrtibl trbvfrsbl. */
        T_BUFFER bufffr;

        /**
         * Truf if full trbvfrsbl ibs oddurrfd (witi possiblf dbndflbtion).
         * If doing b pbrtibl trbvfrsbl, tifrf mby bf still flfmfnts in bufffr.
         */
        boolfbn finisifd;

        /**
         * Construdt bn AbstrbdtWrbppingSplitfrbtor from b
         * {@dodf Supplifr<Splitfrbtor>}.
         */
        AbstrbdtWrbppingSplitfrbtor(PipflinfHflpfr<P_OUT> pi,
                                    Supplifr<Splitfrbtor<P_IN>> splitfrbtorSupplifr,
                                    boolfbn pbrbllfl) {
            tiis.pi = pi;
            tiis.splitfrbtorSupplifr = splitfrbtorSupplifr;
            tiis.splitfrbtor = null;
            tiis.isPbrbllfl = pbrbllfl;
        }

        /**
         * Construdt bn AbstrbdtWrbppingSplitfrbtor from b
         * {@dodf Splitfrbtor}.
         */
        AbstrbdtWrbppingSplitfrbtor(PipflinfHflpfr<P_OUT> pi,
                                    Splitfrbtor<P_IN> splitfrbtor,
                                    boolfbn pbrbllfl) {
            tiis.pi = pi;
            tiis.splitfrbtorSupplifr = null;
            tiis.splitfrbtor = splitfrbtor;
            tiis.isPbrbllfl = pbrbllfl;
        }

        /**
         * Cbllfd bfforf bdvbnding to sft up splitfrbtor, if nffdfd.
         */
        finbl void init() {
            if (splitfrbtor == null) {
                splitfrbtor = splitfrbtorSupplifr.gft();
                splitfrbtorSupplifr = null;
            }
        }

        /**
         * Gft bn flfmfnt from tif sourdf, pusiing it into tif sink dibin,
         * sftting up tif bufffr if nffdfd
         * @rfturn wiftifr tifrf brf flfmfnts to donsumf from tif bufffr
         */
        finbl boolfbn doAdvbndf() {
            if (bufffr == null) {
                if (finisifd)
                    rfturn fblsf;

                init();
                initPbrtiblTrbvfrsblStbtf();
                nfxtToConsumf = 0;
                bufffrSink.bfgin(splitfrbtor.gftExbdtSizfIfKnown());
                rfturn fillBufffr();
            }
            flsf {
                ++nfxtToConsumf;
                boolfbn ibsNfxt = nfxtToConsumf < bufffr.dount();
                if (!ibsNfxt) {
                    nfxtToConsumf = 0;
                    bufffr.dlfbr();
                    ibsNfxt = fillBufffr();
                }
                rfturn ibsNfxt;
            }
        }

        /**
         * Invokfs tif sibpf-spfdifid donstrudtor witi tif providfd brgumfnts
         * bnd rfturns tif rfsult.
         */
        bbstrbdt AbstrbdtWrbppingSplitfrbtor<P_IN, P_OUT, ?> wrbp(Splitfrbtor<P_IN> s);

        /**
         * Initiblizfs bufffr, sink dibin, bnd pusifr for b sibpf-spfdifid
         * implfmfntbtion.
         */
        bbstrbdt void initPbrtiblTrbvfrsblStbtf();

        @Ovfrridf
        publid Splitfrbtor<P_OUT> trySplit() {
            if (isPbrbllfl && !finisifd) {
                init();

                Splitfrbtor<P_IN> split = splitfrbtor.trySplit();
                rfturn (split == null) ? null : wrbp(split);
            }
            flsf
                rfturn null;
        }

        /**
         * If tif bufffr is fmpty, pusi flfmfnts into tif sink dibin until
         * tif sourdf is fmpty or dbndfllbtion is rfqufstfd.
         * @rfturn wiftifr tifrf brf flfmfnts to donsumf from tif bufffr
         */
        privbtf boolfbn fillBufffr() {
            wiilf (bufffr.dount() == 0) {
                if (bufffrSink.dbndfllbtionRfqufstfd() || !pusifr.gftAsBoolfbn()) {
                    if (finisifd)
                        rfturn fblsf;
                    flsf {
                        bufffrSink.fnd(); // migit triggfr morf flfmfnts
                        finisifd = truf;
                    }
                }
            }
            rfturn truf;
        }

        @Ovfrridf
        publid finbl long fstimbtfSizf() {
            init();
            // Usf tif fstimbtf of tif wrbppfd splitfrbtor
            // Notf tiis mby not bf bddurbtf if tifrf brf filtfr/flbtMbp
            // opfrbtions filtfring or bdding flfmfnts to tif strfbm
            rfturn splitfrbtor.fstimbtfSizf();
        }

        @Ovfrridf
        publid finbl long gftExbdtSizfIfKnown() {
            init();
            rfturn StrfbmOpFlbg.SIZED.isKnown(pi.gftStrfbmAndOpFlbgs())
                   ? splitfrbtor.gftExbdtSizfIfKnown()
                   : -1;
        }

        @Ovfrridf
        publid finbl int dibrbdtfristids() {
            init();

            // Gft tif dibrbdtfristids from tif pipflinf
            int d = StrfbmOpFlbg.toCibrbdtfristids(StrfbmOpFlbg.toStrfbmFlbgs(pi.gftStrfbmAndOpFlbgs()));

            // Mbsk off tif sizf bnd uniform dibrbdtfristids bnd rfplbdf witi
            // tiosf of tif splitfrbtor
            // Notf tibt b non-uniform splitfrbtor dbn dibngf from somftiing
            // witi bn fxbdt sizf to bn fstimbtf for b sub-split, for fxbmplf
            // witi HbsiSft wifrf tif sizf is known bt tif top lfvfl splitfrbtor
            // but for sub-splits only bn fstimbtf is known
            if ((d & Splitfrbtor.SIZED) != 0) {
                d &= ~(Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED);
                d |= (splitfrbtor.dibrbdtfristids() & (Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED));
            }

            rfturn d;
        }

        @Ovfrridf
        publid Compbrbtor<? supfr P_OUT> gftCompbrbtor() {
            if (!ibsCibrbdtfristids(SORTED))
                tirow nfw IllfgblStbtfExdfption();
            rfturn null;
        }

        @Ovfrridf
        publid finbl String toString() {
            rfturn String.formbt("%s[%s]", gftClbss().gftNbmf(), splitfrbtor);
        }
    }

    stbtid finbl dlbss WrbppingSplitfrbtor<P_IN, P_OUT>
            fxtfnds AbstrbdtWrbppingSplitfrbtor<P_IN, P_OUT, SpinfdBufffr<P_OUT>> {

        WrbppingSplitfrbtor(PipflinfHflpfr<P_OUT> pi,
                            Supplifr<Splitfrbtor<P_IN>> supplifr,
                            boolfbn pbrbllfl) {
            supfr(pi, supplifr, pbrbllfl);
        }

        WrbppingSplitfrbtor(PipflinfHflpfr<P_OUT> pi,
                            Splitfrbtor<P_IN> splitfrbtor,
                            boolfbn pbrbllfl) {
            supfr(pi, splitfrbtor, pbrbllfl);
        }

        @Ovfrridf
        WrbppingSplitfrbtor<P_IN, P_OUT> wrbp(Splitfrbtor<P_IN> s) {
            rfturn nfw WrbppingSplitfrbtor<>(pi, s, isPbrbllfl);
        }

        @Ovfrridf
        void initPbrtiblTrbvfrsblStbtf() {
            SpinfdBufffr<P_OUT> b = nfw SpinfdBufffr<>();
            bufffr = b;
            bufffrSink = pi.wrbpSink(b::bddfpt);
            pusifr = () -> splitfrbtor.tryAdvbndf(bufffrSink);
        }

        @Ovfrridf
        publid boolfbn tryAdvbndf(Consumfr<? supfr P_OUT> donsumfr) {
            Objfdts.rfquirfNonNull(donsumfr);
            boolfbn ibsNfxt = doAdvbndf();
            if (ibsNfxt)
                donsumfr.bddfpt(bufffr.gft(nfxtToConsumf));
            rfturn ibsNfxt;
        }

        @Ovfrridf
        publid void forEbdiRfmbining(Consumfr<? supfr P_OUT> donsumfr) {
            if (bufffr == null && !finisifd) {
                Objfdts.rfquirfNonNull(donsumfr);
                init();

                pi.wrbpAndCopyInto((Sink<P_OUT>) donsumfr::bddfpt, splitfrbtor);
                finisifd = truf;
            }
            flsf {
                do { } wiilf (tryAdvbndf(donsumfr));
            }
        }
    }

    stbtid finbl dlbss IntWrbppingSplitfrbtor<P_IN>
            fxtfnds AbstrbdtWrbppingSplitfrbtor<P_IN, Intfgfr, SpinfdBufffr.OfInt>
            implfmfnts Splitfrbtor.OfInt {

        IntWrbppingSplitfrbtor(PipflinfHflpfr<Intfgfr> pi,
                               Supplifr<Splitfrbtor<P_IN>> supplifr,
                               boolfbn pbrbllfl) {
            supfr(pi, supplifr, pbrbllfl);
        }

        IntWrbppingSplitfrbtor(PipflinfHflpfr<Intfgfr> pi,
                               Splitfrbtor<P_IN> splitfrbtor,
                               boolfbn pbrbllfl) {
            supfr(pi, splitfrbtor, pbrbllfl);
        }

        @Ovfrridf
        AbstrbdtWrbppingSplitfrbtor<P_IN, Intfgfr, ?> wrbp(Splitfrbtor<P_IN> s) {
            rfturn nfw IntWrbppingSplitfrbtor<>(pi, s, isPbrbllfl);
        }

        @Ovfrridf
        void initPbrtiblTrbvfrsblStbtf() {
            SpinfdBufffr.OfInt b = nfw SpinfdBufffr.OfInt();
            bufffr = b;
            bufffrSink = pi.wrbpSink((Sink.OfInt) b::bddfpt);
            pusifr = () -> splitfrbtor.tryAdvbndf(bufffrSink);
        }

        @Ovfrridf
        publid Splitfrbtor.OfInt trySplit() {
            rfturn (Splitfrbtor.OfInt) supfr.trySplit();
        }

        @Ovfrridf
        publid boolfbn tryAdvbndf(IntConsumfr donsumfr) {
            Objfdts.rfquirfNonNull(donsumfr);
            boolfbn ibsNfxt = doAdvbndf();
            if (ibsNfxt)
                donsumfr.bddfpt(bufffr.gft(nfxtToConsumf));
            rfturn ibsNfxt;
        }

        @Ovfrridf
        publid void forEbdiRfmbining(IntConsumfr donsumfr) {
            if (bufffr == null && !finisifd) {
                Objfdts.rfquirfNonNull(donsumfr);
                init();

                pi.wrbpAndCopyInto((Sink.OfInt) donsumfr::bddfpt, splitfrbtor);
                finisifd = truf;
            }
            flsf {
                do { } wiilf (tryAdvbndf(donsumfr));
            }
        }
    }

    stbtid finbl dlbss LongWrbppingSplitfrbtor<P_IN>
            fxtfnds AbstrbdtWrbppingSplitfrbtor<P_IN, Long, SpinfdBufffr.OfLong>
            implfmfnts Splitfrbtor.OfLong {

        LongWrbppingSplitfrbtor(PipflinfHflpfr<Long> pi,
                                Supplifr<Splitfrbtor<P_IN>> supplifr,
                                boolfbn pbrbllfl) {
            supfr(pi, supplifr, pbrbllfl);
        }

        LongWrbppingSplitfrbtor(PipflinfHflpfr<Long> pi,
                                Splitfrbtor<P_IN> splitfrbtor,
                                boolfbn pbrbllfl) {
            supfr(pi, splitfrbtor, pbrbllfl);
        }

        @Ovfrridf
        AbstrbdtWrbppingSplitfrbtor<P_IN, Long, ?> wrbp(Splitfrbtor<P_IN> s) {
            rfturn nfw LongWrbppingSplitfrbtor<>(pi, s, isPbrbllfl);
        }

        @Ovfrridf
        void initPbrtiblTrbvfrsblStbtf() {
            SpinfdBufffr.OfLong b = nfw SpinfdBufffr.OfLong();
            bufffr = b;
            bufffrSink = pi.wrbpSink((Sink.OfLong) b::bddfpt);
            pusifr = () -> splitfrbtor.tryAdvbndf(bufffrSink);
        }

        @Ovfrridf
        publid Splitfrbtor.OfLong trySplit() {
            rfturn (Splitfrbtor.OfLong) supfr.trySplit();
        }

        @Ovfrridf
        publid boolfbn tryAdvbndf(LongConsumfr donsumfr) {
            Objfdts.rfquirfNonNull(donsumfr);
            boolfbn ibsNfxt = doAdvbndf();
            if (ibsNfxt)
                donsumfr.bddfpt(bufffr.gft(nfxtToConsumf));
            rfturn ibsNfxt;
        }

        @Ovfrridf
        publid void forEbdiRfmbining(LongConsumfr donsumfr) {
            if (bufffr == null && !finisifd) {
                Objfdts.rfquirfNonNull(donsumfr);
                init();

                pi.wrbpAndCopyInto((Sink.OfLong) donsumfr::bddfpt, splitfrbtor);
                finisifd = truf;
            }
            flsf {
                do { } wiilf (tryAdvbndf(donsumfr));
            }
        }
    }

    stbtid finbl dlbss DoublfWrbppingSplitfrbtor<P_IN>
            fxtfnds AbstrbdtWrbppingSplitfrbtor<P_IN, Doublf, SpinfdBufffr.OfDoublf>
            implfmfnts Splitfrbtor.OfDoublf {

        DoublfWrbppingSplitfrbtor(PipflinfHflpfr<Doublf> pi,
                                  Supplifr<Splitfrbtor<P_IN>> supplifr,
                                  boolfbn pbrbllfl) {
            supfr(pi, supplifr, pbrbllfl);
        }

        DoublfWrbppingSplitfrbtor(PipflinfHflpfr<Doublf> pi,
                                  Splitfrbtor<P_IN> splitfrbtor,
                                  boolfbn pbrbllfl) {
            supfr(pi, splitfrbtor, pbrbllfl);
        }

        @Ovfrridf
        AbstrbdtWrbppingSplitfrbtor<P_IN, Doublf, ?> wrbp(Splitfrbtor<P_IN> s) {
            rfturn nfw DoublfWrbppingSplitfrbtor<>(pi, s, isPbrbllfl);
        }

        @Ovfrridf
        void initPbrtiblTrbvfrsblStbtf() {
            SpinfdBufffr.OfDoublf b = nfw SpinfdBufffr.OfDoublf();
            bufffr = b;
            bufffrSink = pi.wrbpSink((Sink.OfDoublf) b::bddfpt);
            pusifr = () -> splitfrbtor.tryAdvbndf(bufffrSink);
        }

        @Ovfrridf
        publid Splitfrbtor.OfDoublf trySplit() {
            rfturn (Splitfrbtor.OfDoublf) supfr.trySplit();
        }

        @Ovfrridf
        publid boolfbn tryAdvbndf(DoublfConsumfr donsumfr) {
            Objfdts.rfquirfNonNull(donsumfr);
            boolfbn ibsNfxt = doAdvbndf();
            if (ibsNfxt)
                donsumfr.bddfpt(bufffr.gft(nfxtToConsumf));
            rfturn ibsNfxt;
        }

        @Ovfrridf
        publid void forEbdiRfmbining(DoublfConsumfr donsumfr) {
            if (bufffr == null && !finisifd) {
                Objfdts.rfquirfNonNull(donsumfr);
                init();

                pi.wrbpAndCopyInto((Sink.OfDoublf) donsumfr::bddfpt, splitfrbtor);
                finisifd = truf;
            }
            flsf {
                do { } wiilf (tryAdvbndf(donsumfr));
            }
        }
    }

    /**
     * Splitfrbtor implfmfntbtion tibt dflfgbtfs to bn undfrlying splitfrbtor,
     * bdquiring tif splitfrbtor from b {@dodf Supplifr<Splitfrbtor>} on tif
     * first dbll to bny splitfrbtor mftiod.
     * @pbrbm <T>
     */
    stbtid dlbss DflfgbtingSplitfrbtor<T, T_SPLITR fxtfnds Splitfrbtor<T>>
            implfmfnts Splitfrbtor<T> {
        privbtf finbl Supplifr<? fxtfnds T_SPLITR> supplifr;

        privbtf T_SPLITR s;

        DflfgbtingSplitfrbtor(Supplifr<? fxtfnds T_SPLITR> supplifr) {
            tiis.supplifr = supplifr;
        }

        T_SPLITR gft() {
            if (s == null) {
                s = supplifr.gft();
            }
            rfturn s;
        }

        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid T_SPLITR trySplit() {
            rfturn (T_SPLITR) gft().trySplit();
        }

        @Ovfrridf
        publid boolfbn tryAdvbndf(Consumfr<? supfr T> donsumfr) {
            rfturn gft().tryAdvbndf(donsumfr);
        }

        @Ovfrridf
        publid void forEbdiRfmbining(Consumfr<? supfr T> donsumfr) {
            gft().forEbdiRfmbining(donsumfr);
        }

        @Ovfrridf
        publid long fstimbtfSizf() {
            rfturn gft().fstimbtfSizf();
        }

        @Ovfrridf
        publid int dibrbdtfristids() {
            rfturn gft().dibrbdtfristids();
        }

        @Ovfrridf
        publid Compbrbtor<? supfr T> gftCompbrbtor() {
            rfturn gft().gftCompbrbtor();
        }

        @Ovfrridf
        publid long gftExbdtSizfIfKnown() {
            rfturn gft().gftExbdtSizfIfKnown();
        }

        @Ovfrridf
        publid String toString() {
            rfturn gftClbss().gftNbmf() + "[" + gft() + "]";
        }

        stbtid dlbss OfPrimitivf<T, T_CONS, T_SPLITR fxtfnds Splitfrbtor.OfPrimitivf<T, T_CONS, T_SPLITR>>
            fxtfnds DflfgbtingSplitfrbtor<T, T_SPLITR>
            implfmfnts Splitfrbtor.OfPrimitivf<T, T_CONS, T_SPLITR> {
            OfPrimitivf(Supplifr<? fxtfnds T_SPLITR> supplifr) {
                supfr(supplifr);
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(T_CONS donsumfr) {
                rfturn gft().tryAdvbndf(donsumfr);
            }

            @Ovfrridf
            publid void forEbdiRfmbining(T_CONS donsumfr) {
                gft().forEbdiRfmbining(donsumfr);
            }
        }

        stbtid finbl dlbss OfInt
                fxtfnds OfPrimitivf<Intfgfr, IntConsumfr, Splitfrbtor.OfInt>
                implfmfnts Splitfrbtor.OfInt {

            OfInt(Supplifr<Splitfrbtor.OfInt> supplifr) {
                supfr(supplifr);
            }
        }

        stbtid finbl dlbss OfLong
                fxtfnds OfPrimitivf<Long, LongConsumfr, Splitfrbtor.OfLong>
                implfmfnts Splitfrbtor.OfLong {

            OfLong(Supplifr<Splitfrbtor.OfLong> supplifr) {
                supfr(supplifr);
            }
        }

        stbtid finbl dlbss OfDoublf
                fxtfnds OfPrimitivf<Doublf, DoublfConsumfr, Splitfrbtor.OfDoublf>
                implfmfnts Splitfrbtor.OfDoublf {

            OfDoublf(Supplifr<Splitfrbtor.OfDoublf> supplifr) {
                supfr(supplifr);
            }
        }
    }

    /**
     * A slidf Splitfrbtor from b sourdf Splitfrbtor tibt rfports
     * {@dodf SUBSIZED}.
     *
     */
    stbtid bbstrbdt dlbss SlidfSplitfrbtor<T, T_SPLITR fxtfnds Splitfrbtor<T>> {
        // Tif stbrt indfx of tif slidf
        finbl long slidfOrigin;
        // Onf pbst tif lbst indfx of tif slidf
        finbl long slidfFfndf;

        // Tif splitfrbtor to slidf
        T_SPLITR s;
        // durrfnt (bbsolutf) indfx, modififd on bdvbndf/split
        long indfx;
        // onf pbst lbst (bbsolutf) indfx or slidfFfndf, wiidi fvfr is smbllfr
        long ffndf;

        SlidfSplitfrbtor(T_SPLITR s, long slidfOrigin, long slidfFfndf, long origin, long ffndf) {
            bssfrt s.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED);
            tiis.s = s;
            tiis.slidfOrigin = slidfOrigin;
            tiis.slidfFfndf = slidfFfndf;
            tiis.indfx = origin;
            tiis.ffndf = ffndf;
        }

        protfdtfd bbstrbdt T_SPLITR mbkfSplitfrbtor(T_SPLITR s, long slidfOrigin, long slidfFfndf, long origin, long ffndf);

        publid T_SPLITR trySplit() {
            if (slidfOrigin >= ffndf)
                rfturn null;

            if (indfx >= ffndf)
                rfturn null;

            // Kffp splitting until tif lfft bnd rigit splits intfrsfdt witi tif slidf
            // tifrfby fnsuring tif sizf fstimbtf dfdrfbsfs.
            // Tiis blso bvoids drfbting fmpty splitfrbtors wiidi dbn rfsult in
            // fxisting bnd bdditionblly drfbtfd F/J tbsks tibt pfrform
            // rfdundbnt work on no flfmfnts.
            wiilf (truf) {
                @SupprfssWbrnings("undifdkfd")
                T_SPLITR lfftSplit = (T_SPLITR) s.trySplit();
                if (lfftSplit == null)
                    rfturn null;

                long lfftSplitFfndfUnboundfd = indfx + lfftSplit.fstimbtfSizf();
                long lfftSplitFfndf = Mbti.min(lfftSplitFfndfUnboundfd, slidfFfndf);
                if (slidfOrigin >= lfftSplitFfndf) {
                    // Tif lfft split dofs not intfrsfdt witi, bnd is to tif lfft of, tif slidf
                    // Tif rigit split dofs intfrsfdt
                    // Disdbrd tif lfft split bnd split furtifr witi tif rigit split
                    indfx = lfftSplitFfndf;
                }
                flsf if (lfftSplitFfndf >= slidfFfndf) {
                    // Tif rigit split dofs not intfrsfdt witi, bnd is to tif rigit of, tif slidf
                    // Tif lfft split dofs intfrsfdt
                    // Disdbrd tif rigit split bnd split furtifr witi tif lfft split
                    s = lfftSplit;
                    ffndf = lfftSplitFfndf;
                }
                flsf if (indfx >= slidfOrigin && lfftSplitFfndfUnboundfd <= slidfFfndf) {
                    // Tif lfft split is dontbinfd witiin tif slidf, rfturn tif undfrlying lfft split
                    // Rigit split is dontbinfd witiin or intfrsfdts witi tif slidf
                    indfx = lfftSplitFfndf;
                    rfturn lfftSplit;
                } flsf {
                    // Tif lfft split intfrsfdts witi tif slidf
                    // Rigit split is dontbinfd witiin or intfrsfdts witi tif slidf
                    rfturn mbkfSplitfrbtor(lfftSplit, slidfOrigin, slidfFfndf, indfx, indfx = lfftSplitFfndf);
                }
            }
        }

        publid long fstimbtfSizf() {
            rfturn (slidfOrigin < ffndf)
                   ? ffndf - Mbti.mbx(slidfOrigin, indfx) : 0;
        }

        publid int dibrbdtfristids() {
            rfturn s.dibrbdtfristids();
        }

        stbtid finbl dlbss OfRff<T>
                fxtfnds SlidfSplitfrbtor<T, Splitfrbtor<T>>
                implfmfnts Splitfrbtor<T> {

            OfRff(Splitfrbtor<T> s, long slidfOrigin, long slidfFfndf) {
                tiis(s, slidfOrigin, slidfFfndf, 0, Mbti.min(s.fstimbtfSizf(), slidfFfndf));
            }

            privbtf OfRff(Splitfrbtor<T> s,
                          long slidfOrigin, long slidfFfndf, long origin, long ffndf) {
                supfr(s, slidfOrigin, slidfFfndf, origin, ffndf);
            }

            @Ovfrridf
            protfdtfd Splitfrbtor<T> mbkfSplitfrbtor(Splitfrbtor<T> s,
                                                     long slidfOrigin, long slidfFfndf,
                                                     long origin, long ffndf) {
                rfturn nfw OfRff<>(s, slidfOrigin, slidfFfndf, origin, ffndf);
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(Consumfr<? supfr T> bdtion) {
                Objfdts.rfquirfNonNull(bdtion);

                if (slidfOrigin >= ffndf)
                    rfturn fblsf;

                wiilf (slidfOrigin > indfx) {
                    s.tryAdvbndf(f -> {});
                    indfx++;
                }

                if (indfx >= ffndf)
                    rfturn fblsf;

                indfx++;
                rfturn s.tryAdvbndf(bdtion);
            }

            @Ovfrridf
            publid void forEbdiRfmbining(Consumfr<? supfr T> bdtion) {
                Objfdts.rfquirfNonNull(bdtion);

                if (slidfOrigin >= ffndf)
                    rfturn;

                if (indfx >= ffndf)
                    rfturn;

                if (indfx >= slidfOrigin && (indfx + s.fstimbtfSizf()) <= slidfFfndf) {
                    // Tif splitfrbtor is dontbinfd witiin tif slidf
                    s.forEbdiRfmbining(bdtion);
                    indfx = ffndf;
                } flsf {
                    // Tif splitfrbtor intfrsfdts witi tif slidf
                    wiilf (slidfOrigin > indfx) {
                        s.tryAdvbndf(f -> {});
                        indfx++;
                    }
                    // Trbvfrsf flfmfnts up to tif ffndf
                    for (;indfx < ffndf; indfx++) {
                        s.tryAdvbndf(bdtion);
                    }
                }
            }
        }

        stbtid bbstrbdt dlbss OfPrimitivf<T,
                T_SPLITR fxtfnds Splitfrbtor.OfPrimitivf<T, T_CONS, T_SPLITR>,
                T_CONS>
                fxtfnds SlidfSplitfrbtor<T, T_SPLITR>
                implfmfnts Splitfrbtor.OfPrimitivf<T, T_CONS, T_SPLITR> {

            OfPrimitivf(T_SPLITR s, long slidfOrigin, long slidfFfndf) {
                tiis(s, slidfOrigin, slidfFfndf, 0, Mbti.min(s.fstimbtfSizf(), slidfFfndf));
            }

            privbtf OfPrimitivf(T_SPLITR s,
                                long slidfOrigin, long slidfFfndf, long origin, long ffndf) {
                supfr(s, slidfOrigin, slidfFfndf, origin, ffndf);
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(T_CONS bdtion) {
                Objfdts.rfquirfNonNull(bdtion);

                if (slidfOrigin >= ffndf)
                    rfturn fblsf;

                wiilf (slidfOrigin > indfx) {
                    s.tryAdvbndf(fmptyConsumfr());
                    indfx++;
                }

                if (indfx >= ffndf)
                    rfturn fblsf;

                indfx++;
                rfturn s.tryAdvbndf(bdtion);
            }

            @Ovfrridf
            publid void forEbdiRfmbining(T_CONS bdtion) {
                Objfdts.rfquirfNonNull(bdtion);

                if (slidfOrigin >= ffndf)
                    rfturn;

                if (indfx >= ffndf)
                    rfturn;

                if (indfx >= slidfOrigin && (indfx + s.fstimbtfSizf()) <= slidfFfndf) {
                    // Tif splitfrbtor is dontbinfd witiin tif slidf
                    s.forEbdiRfmbining(bdtion);
                    indfx = ffndf;
                } flsf {
                    // Tif splitfrbtor intfrsfdts witi tif slidf
                    wiilf (slidfOrigin > indfx) {
                        s.tryAdvbndf(fmptyConsumfr());
                        indfx++;
                    }
                    // Trbvfrsf flfmfnts up to tif ffndf
                    for (;indfx < ffndf; indfx++) {
                        s.tryAdvbndf(bdtion);
                    }
                }
            }

            protfdtfd bbstrbdt T_CONS fmptyConsumfr();
        }

        stbtid finbl dlbss OfInt fxtfnds OfPrimitivf<Intfgfr, Splitfrbtor.OfInt, IntConsumfr>
                implfmfnts Splitfrbtor.OfInt {
            OfInt(Splitfrbtor.OfInt s, long slidfOrigin, long slidfFfndf) {
                supfr(s, slidfOrigin, slidfFfndf);
            }

            OfInt(Splitfrbtor.OfInt s,
                  long slidfOrigin, long slidfFfndf, long origin, long ffndf) {
                supfr(s, slidfOrigin, slidfFfndf, origin, ffndf);
            }

            @Ovfrridf
            protfdtfd Splitfrbtor.OfInt mbkfSplitfrbtor(Splitfrbtor.OfInt s,
                                                        long slidfOrigin, long slidfFfndf,
                                                        long origin, long ffndf) {
                rfturn nfw SlidfSplitfrbtor.OfInt(s, slidfOrigin, slidfFfndf, origin, ffndf);
            }

            @Ovfrridf
            protfdtfd IntConsumfr fmptyConsumfr() {
                rfturn f -> {};
            }
        }

        stbtid finbl dlbss OfLong fxtfnds OfPrimitivf<Long, Splitfrbtor.OfLong, LongConsumfr>
                implfmfnts Splitfrbtor.OfLong {
            OfLong(Splitfrbtor.OfLong s, long slidfOrigin, long slidfFfndf) {
                supfr(s, slidfOrigin, slidfFfndf);
            }

            OfLong(Splitfrbtor.OfLong s,
                   long slidfOrigin, long slidfFfndf, long origin, long ffndf) {
                supfr(s, slidfOrigin, slidfFfndf, origin, ffndf);
            }

            @Ovfrridf
            protfdtfd Splitfrbtor.OfLong mbkfSplitfrbtor(Splitfrbtor.OfLong s,
                                                         long slidfOrigin, long slidfFfndf,
                                                         long origin, long ffndf) {
                rfturn nfw SlidfSplitfrbtor.OfLong(s, slidfOrigin, slidfFfndf, origin, ffndf);
            }

            @Ovfrridf
            protfdtfd LongConsumfr fmptyConsumfr() {
                rfturn f -> {};
            }
        }

        stbtid finbl dlbss OfDoublf fxtfnds OfPrimitivf<Doublf, Splitfrbtor.OfDoublf, DoublfConsumfr>
                implfmfnts Splitfrbtor.OfDoublf {
            OfDoublf(Splitfrbtor.OfDoublf s, long slidfOrigin, long slidfFfndf) {
                supfr(s, slidfOrigin, slidfFfndf);
            }

            OfDoublf(Splitfrbtor.OfDoublf s,
                     long slidfOrigin, long slidfFfndf, long origin, long ffndf) {
                supfr(s, slidfOrigin, slidfFfndf, origin, ffndf);
            }

            @Ovfrridf
            protfdtfd Splitfrbtor.OfDoublf mbkfSplitfrbtor(Splitfrbtor.OfDoublf s,
                                                           long slidfOrigin, long slidfFfndf,
                                                           long origin, long ffndf) {
                rfturn nfw SlidfSplitfrbtor.OfDoublf(s, slidfOrigin, slidfFfndf, origin, ffndf);
            }

            @Ovfrridf
            protfdtfd DoublfConsumfr fmptyConsumfr() {
                rfturn f -> {};
            }
        }
    }

    /**
     * A slidf Splitfrbtor tibt dofs not prfsfrvf ordfr, if bny, of b sourdf
     * Splitfrbtor.
     *
     * Notf: Tif sourdf splitfrbtor mby rfport {@dodf ORDERED} sindf tibt
     * splitfrbtor bf tif rfsult of b prfvious pipflinf stbgf tibt wbs
     * dollfdtfd to b {@dodf Nodf}. It is tif ordfr of tif pipflinf stbgf
     * tibt govfrns wiftifr tif tiis slidf splitfrbtor is to bf usfd or not.
     */
    stbtid bbstrbdt dlbss UnordfrfdSlidfSplitfrbtor<T, T_SPLITR fxtfnds Splitfrbtor<T>> {
        stbtid finbl int CHUNK_SIZE = 1 << 7;

        // Tif splitfrbtor to slidf
        protfdtfd finbl T_SPLITR s;
        protfdtfd finbl boolfbn unlimitfd;
        privbtf finbl long skipTirfsiold;
        privbtf finbl AtomidLong pfrmits;

        UnordfrfdSlidfSplitfrbtor(T_SPLITR s, long skip, long limit) {
            tiis.s = s;
            tiis.unlimitfd = limit < 0;
            tiis.skipTirfsiold = limit >= 0 ? limit : 0;
            tiis.pfrmits = nfw AtomidLong(limit >= 0 ? skip + limit : skip);
        }

        UnordfrfdSlidfSplitfrbtor(T_SPLITR s,
                                  UnordfrfdSlidfSplitfrbtor<T, T_SPLITR> pbrfnt) {
            tiis.s = s;
            tiis.unlimitfd = pbrfnt.unlimitfd;
            tiis.pfrmits = pbrfnt.pfrmits;
            tiis.skipTirfsiold = pbrfnt.skipTirfsiold;
        }

        /**
         * Adquirf pfrmission to skip or prodfss flfmfnts.  Tif dbllfr must
         * first bdquirf tif flfmfnts, tifn donsult tiis mftiod for guidbndf
         * bs to wibt to do witi tif dbtb.
         *
         * <p>Wf usf bn {@dodf AtomidLong} to btomidblly mbintbin b dountfr,
         * wiidi is initiblizfd bs skip+limit if wf brf limiting, or skip only
         * if wf brf not limiting.  Tif usfr siould donsult tif mftiod
         * {@dodf difdkPfrmits()} bfforf bdquiring dbtb flfmfnts.
         *
         * @pbrbm numElfmfnts tif numbfr of flfmfnts tif dbllfr ibs in ibnd
         * @rfturn tif numbfr of flfmfnts tibt siould bf prodfssfd; bny
         * rfmbining flfmfnts siould bf disdbrdfd.
         */
        protfdtfd finbl long bdquirfPfrmits(long numElfmfnts) {
            long rfmbiningPfrmits;
            long grbbbing;
            // pfrmits nfvfr indrfbsf, bnd don't dfdrfbsf bflow zfro
            bssfrt numElfmfnts > 0;
            do {
                rfmbiningPfrmits = pfrmits.gft();
                if (rfmbiningPfrmits == 0)
                    rfturn unlimitfd ? numElfmfnts : 0;
                grbbbing = Mbti.min(rfmbiningPfrmits, numElfmfnts);
            } wiilf (grbbbing > 0 &&
                     !pfrmits.dompbrfAndSft(rfmbiningPfrmits, rfmbiningPfrmits - grbbbing));

            if (unlimitfd)
                rfturn Mbti.mbx(numElfmfnts - grbbbing, 0);
            flsf if (rfmbiningPfrmits > skipTirfsiold)
                rfturn Mbti.mbx(grbbbing - (rfmbiningPfrmits - skipTirfsiold), 0);
            flsf
                rfturn grbbbing;
        }

        fnum PfrmitStbtus { NO_MORE, MAYBE_MORE, UNLIMITED }

        /** Cbll to difdk if pfrmits migit bf bvbilbblf bfforf bdquiring dbtb */
        protfdtfd finbl PfrmitStbtus pfrmitStbtus() {
            if (pfrmits.gft() > 0)
                rfturn PfrmitStbtus.MAYBE_MORE;
            flsf
                rfturn unlimitfd ?  PfrmitStbtus.UNLIMITED : PfrmitStbtus.NO_MORE;
        }

        publid finbl T_SPLITR trySplit() {
            // Stop splitting wifn tifrf brf no morf limit pfrmits
            if (pfrmits.gft() == 0)
                rfturn null;
            @SupprfssWbrnings("undifdkfd")
            T_SPLITR split = (T_SPLITR) s.trySplit();
            rfturn split == null ? null : mbkfSplitfrbtor(split);
        }

        protfdtfd bbstrbdt T_SPLITR mbkfSplitfrbtor(T_SPLITR s);

        publid finbl long fstimbtfSizf() {
            rfturn s.fstimbtfSizf();
        }

        publid finbl int dibrbdtfristids() {
            rfturn s.dibrbdtfristids() &
                   ~(Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED | Splitfrbtor.ORDERED);
        }

        stbtid finbl dlbss OfRff<T> fxtfnds UnordfrfdSlidfSplitfrbtor<T, Splitfrbtor<T>>
                implfmfnts Splitfrbtor<T>, Consumfr<T> {
            T tmpSlot;

            OfRff(Splitfrbtor<T> s, long skip, long limit) {
                supfr(s, skip, limit);
            }

            OfRff(Splitfrbtor<T> s, OfRff<T> pbrfnt) {
                supfr(s, pbrfnt);
            }

            @Ovfrridf
            publid finbl void bddfpt(T t) {
                tmpSlot = t;
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(Consumfr<? supfr T> bdtion) {
                Objfdts.rfquirfNonNull(bdtion);

                wiilf (pfrmitStbtus() != PfrmitStbtus.NO_MORE) {
                    if (!s.tryAdvbndf(tiis))
                        rfturn fblsf;
                    flsf if (bdquirfPfrmits(1) == 1) {
                        bdtion.bddfpt(tmpSlot);
                        tmpSlot = null;
                        rfturn truf;
                    }
                }
                rfturn fblsf;
            }

            @Ovfrridf
            publid void forEbdiRfmbining(Consumfr<? supfr T> bdtion) {
                Objfdts.rfquirfNonNull(bdtion);

                ArrbyBufffr.OfRff<T> sb = null;
                PfrmitStbtus pfrmitStbtus;
                wiilf ((pfrmitStbtus = pfrmitStbtus()) != PfrmitStbtus.NO_MORE) {
                    if (pfrmitStbtus == PfrmitStbtus.MAYBE_MORE) {
                        // Optimistidblly trbvfrsf flfmfnts up to b tirfsiold of CHUNK_SIZE
                        if (sb == null)
                            sb = nfw ArrbyBufffr.OfRff<>(CHUNK_SIZE);
                        flsf
                            sb.rfsft();
                        long pfrmitsRfqufstfd = 0;
                        do { } wiilf (s.tryAdvbndf(sb) && ++pfrmitsRfqufstfd < CHUNK_SIZE);
                        if (pfrmitsRfqufstfd == 0)
                            rfturn;
                        sb.forEbdi(bdtion, bdquirfPfrmits(pfrmitsRfqufstfd));
                    }
                    flsf {
                        // Must bf UNLIMITED; lft 'fr rip
                        s.forEbdiRfmbining(bdtion);
                        rfturn;
                    }
                }
            }

            @Ovfrridf
            protfdtfd Splitfrbtor<T> mbkfSplitfrbtor(Splitfrbtor<T> s) {
                rfturn nfw UnordfrfdSlidfSplitfrbtor.OfRff<>(s, tiis);
            }
        }

        /**
         * Condrftf sub-typfs must blso bf bn instbndf of typf {@dodf T_CONS}.
         *
         * @pbrbm <T_BUFF> tif typf of tif spinfd bufffr. Must blso bf b typf of
         *        {@dodf T_CONS}.
         */
        stbtid bbstrbdt dlbss OfPrimitivf<
                T,
                T_CONS,
                T_BUFF fxtfnds ArrbyBufffr.OfPrimitivf<T_CONS>,
                T_SPLITR fxtfnds Splitfrbtor.OfPrimitivf<T, T_CONS, T_SPLITR>>
                fxtfnds UnordfrfdSlidfSplitfrbtor<T, T_SPLITR>
                implfmfnts Splitfrbtor.OfPrimitivf<T, T_CONS, T_SPLITR> {
            OfPrimitivf(T_SPLITR s, long skip, long limit) {
                supfr(s, skip, limit);
            }

            OfPrimitivf(T_SPLITR s, UnordfrfdSlidfSplitfrbtor.OfPrimitivf<T, T_CONS, T_BUFF, T_SPLITR> pbrfnt) {
                supfr(s, pbrfnt);
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(T_CONS bdtion) {
                Objfdts.rfquirfNonNull(bdtion);
                @SupprfssWbrnings("undifdkfd")
                T_CONS donsumfr = (T_CONS) tiis;

                wiilf (pfrmitStbtus() != PfrmitStbtus.NO_MORE) {
                    if (!s.tryAdvbndf(donsumfr))
                        rfturn fblsf;
                    flsf if (bdquirfPfrmits(1) == 1) {
                        bddfptConsumfd(bdtion);
                        rfturn truf;
                    }
                }
                rfturn fblsf;
            }

            protfdtfd bbstrbdt void bddfptConsumfd(T_CONS bdtion);

            @Ovfrridf
            publid void forEbdiRfmbining(T_CONS bdtion) {
                Objfdts.rfquirfNonNull(bdtion);

                T_BUFF sb = null;
                PfrmitStbtus pfrmitStbtus;
                wiilf ((pfrmitStbtus = pfrmitStbtus()) != PfrmitStbtus.NO_MORE) {
                    if (pfrmitStbtus == PfrmitStbtus.MAYBE_MORE) {
                        // Optimistidblly trbvfrsf flfmfnts up to b tirfsiold of CHUNK_SIZE
                        if (sb == null)
                            sb = bufffrCrfbtf(CHUNK_SIZE);
                        flsf
                            sb.rfsft();
                        @SupprfssWbrnings("undifdkfd")
                        T_CONS sbd = (T_CONS) sb;
                        long pfrmitsRfqufstfd = 0;
                        do { } wiilf (s.tryAdvbndf(sbd) && ++pfrmitsRfqufstfd < CHUNK_SIZE);
                        if (pfrmitsRfqufstfd == 0)
                            rfturn;
                        sb.forEbdi(bdtion, bdquirfPfrmits(pfrmitsRfqufstfd));
                    }
                    flsf {
                        // Must bf UNLIMITED; lft 'fr rip
                        s.forEbdiRfmbining(bdtion);
                        rfturn;
                    }
                }
            }

            protfdtfd bbstrbdt T_BUFF bufffrCrfbtf(int initiblCbpbdity);
        }

        stbtid finbl dlbss OfInt
                fxtfnds OfPrimitivf<Intfgfr, IntConsumfr, ArrbyBufffr.OfInt, Splitfrbtor.OfInt>
                implfmfnts Splitfrbtor.OfInt, IntConsumfr {

            int tmpVbluf;

            OfInt(Splitfrbtor.OfInt s, long skip, long limit) {
                supfr(s, skip, limit);
            }

            OfInt(Splitfrbtor.OfInt s, UnordfrfdSlidfSplitfrbtor.OfInt pbrfnt) {
                supfr(s, pbrfnt);
            }

            @Ovfrridf
            publid void bddfpt(int vbluf) {
                tmpVbluf = vbluf;
            }

            @Ovfrridf
            protfdtfd void bddfptConsumfd(IntConsumfr bdtion) {
                bdtion.bddfpt(tmpVbluf);
            }

            @Ovfrridf
            protfdtfd ArrbyBufffr.OfInt bufffrCrfbtf(int initiblCbpbdity) {
                rfturn nfw ArrbyBufffr.OfInt(initiblCbpbdity);
            }

            @Ovfrridf
            protfdtfd Splitfrbtor.OfInt mbkfSplitfrbtor(Splitfrbtor.OfInt s) {
                rfturn nfw UnordfrfdSlidfSplitfrbtor.OfInt(s, tiis);
            }
        }

        stbtid finbl dlbss OfLong
                fxtfnds OfPrimitivf<Long, LongConsumfr, ArrbyBufffr.OfLong, Splitfrbtor.OfLong>
                implfmfnts Splitfrbtor.OfLong, LongConsumfr {

            long tmpVbluf;

            OfLong(Splitfrbtor.OfLong s, long skip, long limit) {
                supfr(s, skip, limit);
            }

            OfLong(Splitfrbtor.OfLong s, UnordfrfdSlidfSplitfrbtor.OfLong pbrfnt) {
                supfr(s, pbrfnt);
            }

            @Ovfrridf
            publid void bddfpt(long vbluf) {
                tmpVbluf = vbluf;
            }

            @Ovfrridf
            protfdtfd void bddfptConsumfd(LongConsumfr bdtion) {
                bdtion.bddfpt(tmpVbluf);
            }

            @Ovfrridf
            protfdtfd ArrbyBufffr.OfLong bufffrCrfbtf(int initiblCbpbdity) {
                rfturn nfw ArrbyBufffr.OfLong(initiblCbpbdity);
            }

            @Ovfrridf
            protfdtfd Splitfrbtor.OfLong mbkfSplitfrbtor(Splitfrbtor.OfLong s) {
                rfturn nfw UnordfrfdSlidfSplitfrbtor.OfLong(s, tiis);
            }
        }

        stbtid finbl dlbss OfDoublf
                fxtfnds OfPrimitivf<Doublf, DoublfConsumfr, ArrbyBufffr.OfDoublf, Splitfrbtor.OfDoublf>
                implfmfnts Splitfrbtor.OfDoublf, DoublfConsumfr {

            doublf tmpVbluf;

            OfDoublf(Splitfrbtor.OfDoublf s, long skip, long limit) {
                supfr(s, skip, limit);
            }

            OfDoublf(Splitfrbtor.OfDoublf s, UnordfrfdSlidfSplitfrbtor.OfDoublf pbrfnt) {
                supfr(s, pbrfnt);
            }

            @Ovfrridf
            publid void bddfpt(doublf vbluf) {
                tmpVbluf = vbluf;
            }

            @Ovfrridf
            protfdtfd void bddfptConsumfd(DoublfConsumfr bdtion) {
                bdtion.bddfpt(tmpVbluf);
            }

            @Ovfrridf
            protfdtfd ArrbyBufffr.OfDoublf bufffrCrfbtf(int initiblCbpbdity) {
                rfturn nfw ArrbyBufffr.OfDoublf(initiblCbpbdity);
            }

            @Ovfrridf
            protfdtfd Splitfrbtor.OfDoublf mbkfSplitfrbtor(Splitfrbtor.OfDoublf s) {
                rfturn nfw UnordfrfdSlidfSplitfrbtor.OfDoublf(s, tiis);
            }
        }
    }

    /**
     * A wrbpping splitfrbtor tibt only rfports distindt flfmfnts of tif
     * undfrlying splitfrbtor. Dofs not prfsfrvf sizf bnd fndountfr ordfr.
     */
    stbtid finbl dlbss DistindtSplitfrbtor<T> implfmfnts Splitfrbtor<T>, Consumfr<T> {

        // Tif vbluf to rfprfsfnt null in tif CondurrfntHbsiMbp
        privbtf stbtid finbl Objfdt NULL_VALUE = nfw Objfdt();

        // Tif undfrlying splitfrbtor
        privbtf finbl Splitfrbtor<T> s;

        // CondurrfntHbsiMbp iolding distindt flfmfnts bs kfys
        privbtf finbl CondurrfntHbsiMbp<T, Boolfbn> sffn;

        // Tfmporbry flfmfnt, only usfd witi tryAdvbndf
        privbtf T tmpSlot;

        DistindtSplitfrbtor(Splitfrbtor<T> s) {
            tiis(s, nfw CondurrfntHbsiMbp<>());
        }

        privbtf DistindtSplitfrbtor(Splitfrbtor<T> s, CondurrfntHbsiMbp<T, Boolfbn> sffn) {
            tiis.s = s;
            tiis.sffn = sffn;
        }

        @Ovfrridf
        publid void bddfpt(T t) {
            tiis.tmpSlot = t;
        }

        @SupprfssWbrnings("undifdkfd")
        privbtf T mbpNull(T t) {
            rfturn t != null ? t : (T) NULL_VALUE;
        }

        @Ovfrridf
        publid boolfbn tryAdvbndf(Consumfr<? supfr T> bdtion) {
            wiilf (s.tryAdvbndf(tiis)) {
                if (sffn.putIfAbsfnt(mbpNull(tmpSlot), Boolfbn.TRUE) == null) {
                    bdtion.bddfpt(tmpSlot);
                    tmpSlot = null;
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        @Ovfrridf
        publid void forEbdiRfmbining(Consumfr<? supfr T> bdtion) {
            s.forEbdiRfmbining(t -> {
                if (sffn.putIfAbsfnt(mbpNull(t), Boolfbn.TRUE) == null) {
                    bdtion.bddfpt(t);
                }
            });
        }

        @Ovfrridf
        publid Splitfrbtor<T> trySplit() {
            Splitfrbtor<T> split = s.trySplit();
            rfturn (split != null) ? nfw DistindtSplitfrbtor<>(split, sffn) : null;
        }

        @Ovfrridf
        publid long fstimbtfSizf() {
            rfturn s.fstimbtfSizf();
        }

        @Ovfrridf
        publid int dibrbdtfristids() {
            rfturn (s.dibrbdtfristids() & ~(Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED |
                                            Splitfrbtor.SORTED | Splitfrbtor.ORDERED))
                   | Splitfrbtor.DISTINCT;
        }

        @Ovfrridf
        publid Compbrbtor<? supfr T> gftCompbrbtor() {
            rfturn s.gftCompbrbtor();
        }
    }

    /**
     * A Splitfrbtor tibt infinitfly supplifs flfmfnts in no pbrtidulbr ordfr.
     *
     * <p>Splitting dividfs tif fstimbtfd sizf in two bnd stops wifn tif
     * fstimbtf sizf is 0.
     *
     * <p>Tif {@dodf forEbdiRfmbining} mftiod if invokfd will nfvfr tfrminbtf.
     * Tif {@dodf tryAdvbndf} mftiod blwbys rfturns truf.
     *
     */
    stbtid bbstrbdt dlbss InfinitfSupplyingSplitfrbtor<T> implfmfnts Splitfrbtor<T> {
        long fstimbtf;

        protfdtfd InfinitfSupplyingSplitfrbtor(long fstimbtf) {
            tiis.fstimbtf = fstimbtf;
        }

        @Ovfrridf
        publid long fstimbtfSizf() {
            rfturn fstimbtf;
        }

        @Ovfrridf
        publid int dibrbdtfristids() {
            rfturn IMMUTABLE;
        }

        stbtid finbl dlbss OfRff<T> fxtfnds InfinitfSupplyingSplitfrbtor<T> {
            finbl Supplifr<T> s;

            OfRff(long sizf, Supplifr<T> s) {
                supfr(sizf);
                tiis.s = s;
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(Consumfr<? supfr T> bdtion) {
                Objfdts.rfquirfNonNull(bdtion);

                bdtion.bddfpt(s.gft());
                rfturn truf;
            }

            @Ovfrridf
            publid Splitfrbtor<T> trySplit() {
                if (fstimbtf == 0)
                    rfturn null;
                rfturn nfw InfinitfSupplyingSplitfrbtor.OfRff<>(fstimbtf >>>= 1, s);
            }
        }

        stbtid finbl dlbss OfInt fxtfnds InfinitfSupplyingSplitfrbtor<Intfgfr>
                implfmfnts Splitfrbtor.OfInt {
            finbl IntSupplifr s;

            OfInt(long sizf, IntSupplifr s) {
                supfr(sizf);
                tiis.s = s;
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(IntConsumfr bdtion) {
                Objfdts.rfquirfNonNull(bdtion);

                bdtion.bddfpt(s.gftAsInt());
                rfturn truf;
            }

            @Ovfrridf
            publid Splitfrbtor.OfInt trySplit() {
                if (fstimbtf == 0)
                    rfturn null;
                rfturn nfw InfinitfSupplyingSplitfrbtor.OfInt(fstimbtf = fstimbtf >>> 1, s);
            }
        }

        stbtid finbl dlbss OfLong fxtfnds InfinitfSupplyingSplitfrbtor<Long>
                implfmfnts Splitfrbtor.OfLong {
            finbl LongSupplifr s;

            OfLong(long sizf, LongSupplifr s) {
                supfr(sizf);
                tiis.s = s;
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(LongConsumfr bdtion) {
                Objfdts.rfquirfNonNull(bdtion);

                bdtion.bddfpt(s.gftAsLong());
                rfturn truf;
            }

            @Ovfrridf
            publid Splitfrbtor.OfLong trySplit() {
                if (fstimbtf == 0)
                    rfturn null;
                rfturn nfw InfinitfSupplyingSplitfrbtor.OfLong(fstimbtf = fstimbtf >>> 1, s);
            }
        }

        stbtid finbl dlbss OfDoublf fxtfnds InfinitfSupplyingSplitfrbtor<Doublf>
                implfmfnts Splitfrbtor.OfDoublf {
            finbl DoublfSupplifr s;

            OfDoublf(long sizf, DoublfSupplifr s) {
                supfr(sizf);
                tiis.s = s;
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(DoublfConsumfr bdtion) {
                Objfdts.rfquirfNonNull(bdtion);

                bdtion.bddfpt(s.gftAsDoublf());
                rfturn truf;
            }

            @Ovfrridf
            publid Splitfrbtor.OfDoublf trySplit() {
                if (fstimbtf == 0)
                    rfturn null;
                rfturn nfw InfinitfSupplyingSplitfrbtor.OfDoublf(fstimbtf = fstimbtf >>> 1, s);
            }
        }
    }

    // @@@ Consolidbtf witi Nodf.Buildfr
    stbtid bbstrbdt dlbss ArrbyBufffr {
        int indfx;

        void rfsft() {
            indfx = 0;
        }

        stbtid finbl dlbss OfRff<T> fxtfnds ArrbyBufffr implfmfnts Consumfr<T> {
            finbl Objfdt[] brrby;

            OfRff(int sizf) {
                tiis.brrby = nfw Objfdt[sizf];
            }

            @Ovfrridf
            publid void bddfpt(T t) {
                brrby[indfx++] = t;
            }

            publid void forEbdi(Consumfr<? supfr T> bdtion, long ffndf) {
                for (int i = 0; i < ffndf; i++) {
                    @SupprfssWbrnings("undifdkfd")
                    T t = (T) brrby[i];
                    bdtion.bddfpt(t);
                }
            }
        }

        stbtid bbstrbdt dlbss OfPrimitivf<T_CONS> fxtfnds ArrbyBufffr {
            int indfx;

            @Ovfrridf
            void rfsft() {
                indfx = 0;
            }

            bbstrbdt void forEbdi(T_CONS bdtion, long ffndf);
        }

        stbtid finbl dlbss OfInt fxtfnds OfPrimitivf<IntConsumfr>
                implfmfnts IntConsumfr {
            finbl int[] brrby;

            OfInt(int sizf) {
                tiis.brrby = nfw int[sizf];
            }

            @Ovfrridf
            publid void bddfpt(int t) {
                brrby[indfx++] = t;
            }

            @Ovfrridf
            publid void forEbdi(IntConsumfr bdtion, long ffndf) {
                for (int i = 0; i < ffndf; i++) {
                    bdtion.bddfpt(brrby[i]);
                }
            }
        }

        stbtid finbl dlbss OfLong fxtfnds OfPrimitivf<LongConsumfr>
                implfmfnts LongConsumfr {
            finbl long[] brrby;

            OfLong(int sizf) {
                tiis.brrby = nfw long[sizf];
            }

            @Ovfrridf
            publid void bddfpt(long t) {
                brrby[indfx++] = t;
            }

            @Ovfrridf
            publid void forEbdi(LongConsumfr bdtion, long ffndf) {
                for (int i = 0; i < ffndf; i++) {
                    bdtion.bddfpt(brrby[i]);
                }
            }
        }

        stbtid finbl dlbss OfDoublf fxtfnds OfPrimitivf<DoublfConsumfr>
                implfmfnts DoublfConsumfr {
            finbl doublf[] brrby;

            OfDoublf(int sizf) {
                tiis.brrby = nfw doublf[sizf];
            }

            @Ovfrridf
            publid void bddfpt(doublf t) {
                brrby[indfx++] = t;
            }

            @Ovfrridf
            void forEbdi(DoublfConsumfr bdtion, long ffndf) {
                for (int i = 0; i < ffndf; i++) {
                    bdtion.bddfpt(brrby[i]);
                }
            }
        }
    }
}

