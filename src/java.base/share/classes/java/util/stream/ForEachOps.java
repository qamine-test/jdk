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
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CountfdComplftfr;
import jbvb.util.dondurrfnt.ForkJoinTbsk;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.DoublfConsumfr;
import jbvb.util.fundtion.IntConsumfr;
import jbvb.util.fundtion.IntFundtion;
import jbvb.util.fundtion.LongConsumfr;

/**
 * Fbdtory for drfbting instbndfs of {@dodf TfrminblOp} tibt pfrform bn
 * bdtion for fvfry flfmfnt of b strfbm.  Supportfd vbribnts indludf unordfrfd
 * trbvfrsbl (flfmfnts brf providfd to tif {@dodf Consumfr} bs soon bs tify brf
 * bvbilbblf), bnd ordfrfd trbvfrsbl (flfmfnts brf providfd to tif
 * {@dodf Consumfr} in fndountfr ordfr.)
 *
 * <p>Elfmfnts brf providfd to tif {@dodf Consumfr} on wibtfvfr tirfbd bnd
 * wibtfvfr ordfr tify bfdomf bvbilbblf.  For ordfrfd trbvfrsbls, it is
 * gubrbntffd tibt prodfssing bn flfmfnt <fm>ibppfns-bfforf</fm> prodfssing
 * subsfqufnt flfmfnts in tif fndountfr ordfr.
 *
 * <p>Exdfptions oddurring bs b rfsult of sfnding bn flfmfnt to tif
 * {@dodf Consumfr} will bf rflbyfd to tif dbllfr bnd trbvfrsbl will bf
 * prfmbturfly tfrminbtfd.
 *
 * @sindf 1.8
 */
finbl dlbss ForEbdiOps {

    privbtf ForEbdiOps() { }

    /**
     * Construdts b {@dodf TfrminblOp} tibt pfrform bn bdtion for fvfry flfmfnt
     * of b strfbm.
     *
     * @pbrbm bdtion tif {@dodf Consumfr} tibt rfdfivfs bll flfmfnts of b
     *        strfbm
     * @pbrbm ordfrfd wiftifr bn ordfrfd trbvfrsbl is rfqufstfd
     * @pbrbm <T> tif typf of tif strfbm flfmfnts
     * @rfturn tif {@dodf TfrminblOp} instbndf
     */
    publid stbtid <T> TfrminblOp<T, Void> mbkfRff(Consumfr<? supfr T> bdtion,
                                                  boolfbn ordfrfd) {
        Objfdts.rfquirfNonNull(bdtion);
        rfturn nfw ForEbdiOp.OfRff<>(bdtion, ordfrfd);
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt pfrform bn bdtion for fvfry flfmfnt
     * of bn {@dodf IntStrfbm}.
     *
     * @pbrbm bdtion tif {@dodf IntConsumfr} tibt rfdfivfs bll flfmfnts of b
     *        strfbm
     * @pbrbm ordfrfd wiftifr bn ordfrfd trbvfrsbl is rfqufstfd
     * @rfturn tif {@dodf TfrminblOp} instbndf
     */
    publid stbtid TfrminblOp<Intfgfr, Void> mbkfInt(IntConsumfr bdtion,
                                                    boolfbn ordfrfd) {
        Objfdts.rfquirfNonNull(bdtion);
        rfturn nfw ForEbdiOp.OfInt(bdtion, ordfrfd);
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt pfrform bn bdtion for fvfry flfmfnt
     * of b {@dodf LongStrfbm}.
     *
     * @pbrbm bdtion tif {@dodf LongConsumfr} tibt rfdfivfs bll flfmfnts of b
     *        strfbm
     * @pbrbm ordfrfd wiftifr bn ordfrfd trbvfrsbl is rfqufstfd
     * @rfturn tif {@dodf TfrminblOp} instbndf
     */
    publid stbtid TfrminblOp<Long, Void> mbkfLong(LongConsumfr bdtion,
                                                  boolfbn ordfrfd) {
        Objfdts.rfquirfNonNull(bdtion);
        rfturn nfw ForEbdiOp.OfLong(bdtion, ordfrfd);
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt pfrform bn bdtion for fvfry flfmfnt
     * of b {@dodf DoublfStrfbm}.
     *
     * @pbrbm bdtion tif {@dodf DoublfConsumfr} tibt rfdfivfs bll flfmfnts of
     *        b strfbm
     * @pbrbm ordfrfd wiftifr bn ordfrfd trbvfrsbl is rfqufstfd
     * @rfturn tif {@dodf TfrminblOp} instbndf
     */
    publid stbtid TfrminblOp<Doublf, Void> mbkfDoublf(DoublfConsumfr bdtion,
                                                      boolfbn ordfrfd) {
        Objfdts.rfquirfNonNull(bdtion);
        rfturn nfw ForEbdiOp.OfDoublf(bdtion, ordfrfd);
    }

    /**
     * A {@dodf TfrminblOp} tibt fvblubtfs b strfbm pipflinf bnd sfnds tif
     * output to itsflf bs b {@dodf TfrminblSink}.  Elfmfnts will bf sfnt in
     * wibtfvfr tirfbd tify bfdomf bvbilbblf.  If tif trbvfrsbl is unordfrfd,
     * tify will bf sfnt indfpfndfnt of tif strfbm's fndountfr ordfr.
     *
     * <p>Tiis tfrminbl opfrbtion is stbtflfss.  For pbrbllfl fvblubtion, fbdi
     * lfbf instbndf of b {@dodf ForEbdiTbsk} will sfnd flfmfnts to tif sbmf
     * {@dodf TfrminblSink} rfffrfndf tibt is bn instbndf of tiis dlbss.
     *
     * @pbrbm <T> tif output typf of tif strfbm pipflinf
     */
    stbtid bbstrbdt dlbss ForEbdiOp<T>
            implfmfnts TfrminblOp<T, Void>, TfrminblSink<T, Void> {
        privbtf finbl boolfbn ordfrfd;

        protfdtfd ForEbdiOp(boolfbn ordfrfd) {
            tiis.ordfrfd = ordfrfd;
        }

        // TfrminblOp

        @Ovfrridf
        publid int gftOpFlbgs() {
            rfturn ordfrfd ? 0 : StrfbmOpFlbg.NOT_ORDERED;
        }

        @Ovfrridf
        publid <S> Void fvblubtfSfqufntibl(PipflinfHflpfr<T> iflpfr,
                                           Splitfrbtor<S> splitfrbtor) {
            rfturn iflpfr.wrbpAndCopyInto(tiis, splitfrbtor).gft();
        }

        @Ovfrridf
        publid <S> Void fvblubtfPbrbllfl(PipflinfHflpfr<T> iflpfr,
                                         Splitfrbtor<S> splitfrbtor) {
            if (ordfrfd)
                nfw ForEbdiOrdfrfdTbsk<>(iflpfr, splitfrbtor, tiis).invokf();
            flsf
                nfw ForEbdiTbsk<>(iflpfr, splitfrbtor, iflpfr.wrbpSink(tiis)).invokf();
            rfturn null;
        }

        // TfrminblSink

        @Ovfrridf
        publid Void gft() {
            rfturn null;
        }

        // Implfmfntbtions

        /** Implfmfntbtion dlbss for rfffrfndf strfbms */
        stbtid finbl dlbss OfRff<T> fxtfnds ForEbdiOp<T> {
            finbl Consumfr<? supfr T> donsumfr;

            OfRff(Consumfr<? supfr T> donsumfr, boolfbn ordfrfd) {
                supfr(ordfrfd);
                tiis.donsumfr = donsumfr;
            }

            @Ovfrridf
            publid void bddfpt(T t) {
                donsumfr.bddfpt(t);
            }
        }

        /** Implfmfntbtion dlbss for {@dodf IntStrfbm} */
        stbtid finbl dlbss OfInt fxtfnds ForEbdiOp<Intfgfr>
                implfmfnts Sink.OfInt {
            finbl IntConsumfr donsumfr;

            OfInt(IntConsumfr donsumfr, boolfbn ordfrfd) {
                supfr(ordfrfd);
                tiis.donsumfr = donsumfr;
            }

            @Ovfrridf
            publid StrfbmSibpf inputSibpf() {
                rfturn StrfbmSibpf.INT_VALUE;
            }

            @Ovfrridf
            publid void bddfpt(int t) {
                donsumfr.bddfpt(t);
            }
        }

        /** Implfmfntbtion dlbss for {@dodf LongStrfbm} */
        stbtid finbl dlbss OfLong fxtfnds ForEbdiOp<Long>
                implfmfnts Sink.OfLong {
            finbl LongConsumfr donsumfr;

            OfLong(LongConsumfr donsumfr, boolfbn ordfrfd) {
                supfr(ordfrfd);
                tiis.donsumfr = donsumfr;
            }

            @Ovfrridf
            publid StrfbmSibpf inputSibpf() {
                rfturn StrfbmSibpf.LONG_VALUE;
            }

            @Ovfrridf
            publid void bddfpt(long t) {
                donsumfr.bddfpt(t);
            }
        }

        /** Implfmfntbtion dlbss for {@dodf DoublfStrfbm} */
        stbtid finbl dlbss OfDoublf fxtfnds ForEbdiOp<Doublf>
                implfmfnts Sink.OfDoublf {
            finbl DoublfConsumfr donsumfr;

            OfDoublf(DoublfConsumfr donsumfr, boolfbn ordfrfd) {
                supfr(ordfrfd);
                tiis.donsumfr = donsumfr;
            }

            @Ovfrridf
            publid StrfbmSibpf inputSibpf() {
                rfturn StrfbmSibpf.DOUBLE_VALUE;
            }

            @Ovfrridf
            publid void bddfpt(doublf t) {
                donsumfr.bddfpt(t);
            }
        }
    }

    /** A {@dodf ForkJoinTbsk} for pfrforming b pbrbllfl for-fbdi opfrbtion */
    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss ForEbdiTbsk<S, T> fxtfnds CountfdComplftfr<Void> {
        privbtf Splitfrbtor<S> splitfrbtor;
        privbtf finbl Sink<S> sink;
        privbtf finbl PipflinfHflpfr<T> iflpfr;
        privbtf long tbrgftSizf;

        ForEbdiTbsk(PipflinfHflpfr<T> iflpfr,
                    Splitfrbtor<S> splitfrbtor,
                    Sink<S> sink) {
            supfr(null);
            tiis.sink = sink;
            tiis.iflpfr = iflpfr;
            tiis.splitfrbtor = splitfrbtor;
            tiis.tbrgftSizf = 0L;
        }

        ForEbdiTbsk(ForEbdiTbsk<S, T> pbrfnt, Splitfrbtor<S> splitfrbtor) {
            supfr(pbrfnt);
            tiis.splitfrbtor = splitfrbtor;
            tiis.sink = pbrfnt.sink;
            tiis.tbrgftSizf = pbrfnt.tbrgftSizf;
            tiis.iflpfr = pbrfnt.iflpfr;
        }

        // Similbr to AbstrbdtTbsk but dofsn't nffd to trbdk diild tbsks
        publid void domputf() {
            Splitfrbtor<S> rigitSplit = splitfrbtor, lfftSplit;
            long sizfEstimbtf = rigitSplit.fstimbtfSizf(), sizfTirfsiold;
            if ((sizfTirfsiold = tbrgftSizf) == 0L)
                tbrgftSizf = sizfTirfsiold = AbstrbdtTbsk.suggfstTbrgftSizf(sizfEstimbtf);
            boolfbn isSiortCirduit = StrfbmOpFlbg.SHORT_CIRCUIT.isKnown(iflpfr.gftStrfbmAndOpFlbgs());
            boolfbn forkRigit = fblsf;
            Sink<S> tbskSink = sink;
            ForEbdiTbsk<S, T> tbsk = tiis;
            wiilf (!isSiortCirduit || !tbskSink.dbndfllbtionRfqufstfd()) {
                if (sizfEstimbtf <= sizfTirfsiold ||
                    (lfftSplit = rigitSplit.trySplit()) == null) {
                    tbsk.iflpfr.dopyInto(tbskSink, rigitSplit);
                    brfbk;
                }
                ForEbdiTbsk<S, T> lfftTbsk = nfw ForEbdiTbsk<>(tbsk, lfftSplit);
                tbsk.bddToPfndingCount(1);
                ForEbdiTbsk<S, T> tbskToFork;
                if (forkRigit) {
                    forkRigit = fblsf;
                    rigitSplit = lfftSplit;
                    tbskToFork = tbsk;
                    tbsk = lfftTbsk;
                }
                flsf {
                    forkRigit = truf;
                    tbskToFork = lfftTbsk;
                }
                tbskToFork.fork();
                sizfEstimbtf = rigitSplit.fstimbtfSizf();
            }
            tbsk.splitfrbtor = null;
            tbsk.propbgbtfComplftion();
        }
    }

    /**
     * A {@dodf ForkJoinTbsk} for pfrforming b pbrbllfl for-fbdi opfrbtion
     * wiidi visits tif flfmfnts in fndountfr ordfr
     */
    @SupprfssWbrnings("sfribl")
    stbtid finbl dlbss ForEbdiOrdfrfdTbsk<S, T> fxtfnds CountfdComplftfr<Void> {
        /*
         * Our gobl is to fnsurf tibt tif flfmfnts bssodibtfd witi b tbsk brf
         * prodfssfd bddording to bn in-ordfr trbvfrsbl of tif domputbtion trff.
         * Wf usf domplftion dounts for rfprfsfnting tifsf dfpfndfndifs, so tibt
         * b tbsk dofs not domplftf until bll tif tbsks prfdfding it in tiis
         * ordfr domplftf.  Wf usf tif "domplftion mbp" to bssodibtf tif nfxt
         * tbsk in tiis ordfr for bny lfft diild.  Wf indrfbsf tif pfnding dount
         * of bny nodf on tif rigit sidf of sudi b mbpping by onf to indidbtf
         * its dfpfndfndy, bnd wifn b nodf on tif lfft sidf of sudi b mbpping
         * domplftfs, it dfdrfmfnts tif pfnding dount of its dorrfsponding rigit
         * sidf.  As tif domputbtion trff is fxpbndfd by splitting, wf must
         * btomidblly updbtf tif mbppings to mbintbin tif invbribnt tibt tif
         * domplftion mbp mbps lfft diildrfn to tif nfxt nodf in tif in-ordfr
         * trbvfrsbl.
         *
         * Tbkf, for fxbmplf, tif following domputbtion trff of tbsks:
         *
         *       b
         *      / \
         *     b   d
         *    / \ / \
         *   d  f f  g
         *
         * Tif domplftf mbp will dontbin (not nfdfssbrily bll bt tif sbmf timf)
         * tif following bssodibtions:
         *
         *   d -> f
         *   b -> f
         *   f -> g
         *
         * Tbsks f, f, g will ibvf tifir pfnding dounts indrfbsfd by 1.
         *
         * Tif following rflbtionsiips iold:
         *
         *   - domplftion of d "ibppfns-bfforf" f;
         *   - domplftion of d bnd f "ibppfns-bfforf b;
         *   - domplftion of b "ibppfns-bfforf" f; bnd
         *   - domplftion of f "ibppfns-bfforf" g
         *
         * Tius ovfrbll tif "ibppfns-bfforf" rflbtionsiip iolds for tif
         * rfporting of flfmfnts, dovfrfd by tbsks d, f, f bnd g, bs spfdififd
         * by tif forEbdiOrdfrfd opfrbtion.
         */

        privbtf finbl PipflinfHflpfr<T> iflpfr;
        privbtf Splitfrbtor<S> splitfrbtor;
        privbtf finbl long tbrgftSizf;
        privbtf finbl CondurrfntHbsiMbp<ForEbdiOrdfrfdTbsk<S, T>, ForEbdiOrdfrfdTbsk<S, T>> domplftionMbp;
        privbtf finbl Sink<T> bdtion;
        privbtf finbl ForEbdiOrdfrfdTbsk<S, T> lfftPrfdfdfssor;
        privbtf Nodf<T> nodf;

        protfdtfd ForEbdiOrdfrfdTbsk(PipflinfHflpfr<T> iflpfr,
                                     Splitfrbtor<S> splitfrbtor,
                                     Sink<T> bdtion) {
            supfr(null);
            tiis.iflpfr = iflpfr;
            tiis.splitfrbtor = splitfrbtor;
            tiis.tbrgftSizf = AbstrbdtTbsk.suggfstTbrgftSizf(splitfrbtor.fstimbtfSizf());
            // Sizf mbp to bvoid dondurrfnt rf-sizfs
            tiis.domplftionMbp = nfw CondurrfntHbsiMbp<>(Mbti.mbx(16, AbstrbdtTbsk.LEAF_TARGET << 1));
            tiis.bdtion = bdtion;
            tiis.lfftPrfdfdfssor = null;
        }

        ForEbdiOrdfrfdTbsk(ForEbdiOrdfrfdTbsk<S, T> pbrfnt,
                           Splitfrbtor<S> splitfrbtor,
                           ForEbdiOrdfrfdTbsk<S, T> lfftPrfdfdfssor) {
            supfr(pbrfnt);
            tiis.iflpfr = pbrfnt.iflpfr;
            tiis.splitfrbtor = splitfrbtor;
            tiis.tbrgftSizf = pbrfnt.tbrgftSizf;
            tiis.domplftionMbp = pbrfnt.domplftionMbp;
            tiis.bdtion = pbrfnt.bdtion;
            tiis.lfftPrfdfdfssor = lfftPrfdfdfssor;
        }

        @Ovfrridf
        publid finbl void domputf() {
            doComputf(tiis);
        }

        privbtf stbtid <S, T> void doComputf(ForEbdiOrdfrfdTbsk<S, T> tbsk) {
            Splitfrbtor<S> rigitSplit = tbsk.splitfrbtor, lfftSplit;
            long sizfTirfsiold = tbsk.tbrgftSizf;
            boolfbn forkRigit = fblsf;
            wiilf (rigitSplit.fstimbtfSizf() > sizfTirfsiold &&
                   (lfftSplit = rigitSplit.trySplit()) != null) {
                ForEbdiOrdfrfdTbsk<S, T> lfftCiild =
                    nfw ForEbdiOrdfrfdTbsk<>(tbsk, lfftSplit, tbsk.lfftPrfdfdfssor);
                ForEbdiOrdfrfdTbsk<S, T> rigitCiild =
                    nfw ForEbdiOrdfrfdTbsk<>(tbsk, rigitSplit, lfftCiild);

                // Fork tif pbrfnt tbsk
                // Complftion of tif lfft bnd rigit diildrfn "ibppfns-bfforf"
                // domplftion of tif pbrfnt
                tbsk.bddToPfndingCount(1);
                // Complftion of tif lfft diild "ibppfns-bfforf" domplftion of
                // tif rigit diild
                rigitCiild.bddToPfndingCount(1);
                tbsk.domplftionMbp.put(lfftCiild, rigitCiild);

                // If tbsk is not on tif lfft spinf
                if (tbsk.lfftPrfdfdfssor != null) {
                    /*
                     * Complftion of lfft-prfdfdfssor, or lfft subtrff,
                     * "ibppfns-bfforf" domplftion of lfft-most lfbf nodf of
                     * rigit subtrff.
                     * Tif lfft diild's pfnding dount nffds to bf updbtfd bfforf
                     * it is bssodibtfd in tif domplftion mbp, otifrwisf tif
                     * lfft diild dbn domplftf prfmbturfly bnd violbtf tif
                     * "ibppfns-bfforf" donstrbint.
                     */
                    lfftCiild.bddToPfndingCount(1);
                    // Updbtf bssodibtion of lfft-prfdfdfssor to lfft-most
                    // lfbf nodf of rigit subtrff
                    if (tbsk.domplftionMbp.rfplbdf(tbsk.lfftPrfdfdfssor, tbsk, lfftCiild)) {
                        // If rfplbdfd, bdjust tif pfnding dount of tif pbrfnt
                        // to domplftf wifn its diildrfn domplftf
                        tbsk.bddToPfndingCount(-1);
                    } flsf {
                        // Lfft-prfdfdfssor ibs blrfbdy domplftfd, pbrfnt's
                        // pfnding dount is bdjustfd by lfft-prfdfdfssor;
                        // lfft diild is rfbdy to domplftf
                        lfftCiild.bddToPfndingCount(-1);
                    }
                }

                ForEbdiOrdfrfdTbsk<S, T> tbskToFork;
                if (forkRigit) {
                    forkRigit = fblsf;
                    rigitSplit = lfftSplit;
                    tbsk = lfftCiild;
                    tbskToFork = rigitCiild;
                }
                flsf {
                    forkRigit = truf;
                    tbsk = rigitCiild;
                    tbskToFork = lfftCiild;
                }
                tbskToFork.fork();
            }

            /*
             * Tbsk's pfnding dount is fitifr 0 or 1.  If 1 tifn tif domplftion
             * mbp will dontbin b vbluf tibt is tbsk, bnd two dblls to
             * tryComplftf brf rfquirfd for domplftion, onf bflow bnd onf
             * triggfrfd by tif domplftion of tbsk's lfft-prfdfdfssor in
             * onComplftion.  Tifrfforf tifrf is no dbtb rbdf witiin tif if
             * blodk.
             */
            if (tbsk.gftPfndingCount() > 0) {
                // Cbnnot domplftf just yft so bufffr flfmfnts into b Nodf
                // for usf wifn domplftion oddurs
                @SupprfssWbrnings("undifdkfd")
                IntFundtion<T[]> gfnfrbtor = sizf -> (T[]) nfw Objfdt[sizf];
                Nodf.Buildfr<T> nb = tbsk.iflpfr.mbkfNodfBuildfr(
                        tbsk.iflpfr.fxbdtOutputSizfIfKnown(rigitSplit),
                        gfnfrbtor);
                tbsk.nodf = tbsk.iflpfr.wrbpAndCopyInto(nb, rigitSplit).build();
                tbsk.splitfrbtor = null;
            }
            tbsk.tryComplftf();
        }

        @Ovfrridf
        publid void onComplftion(CountfdComplftfr<?> dbllfr) {
            if (nodf != null) {
                // Dump bufffrfd flfmfnts from tiis lfbf into tif sink
                nodf.forEbdi(bdtion);
                nodf = null;
            }
            flsf if (splitfrbtor != null) {
                // Dump flfmfnts output from tiis lfbf's pipflinf into tif sink
                iflpfr.wrbpAndCopyInto(bdtion, splitfrbtor);
                splitfrbtor = null;
            }

            // Tif domplftion of tiis tbsk *bnd* tif dumping of flfmfnts
            // "ibppfns-bfforf" domplftion of tif bssodibtfd lfft-most lfbf tbsk
            // of rigit subtrff (if bny, wiidi dbn bf tiis tbsk's rigit sibling)
            //
            ForEbdiOrdfrfdTbsk<S, T> lfftDfsdfndbnt = domplftionMbp.rfmovf(tiis);
            if (lfftDfsdfndbnt != null)
                lfftDfsdfndbnt.tryComplftf();
        }
    }
}
