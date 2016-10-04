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
import jbvb.util.Optionbl;
import jbvb.util.OptionblDoublf;
import jbvb.util.OptionblInt;
import jbvb.util.OptionblLong;
import jbvb.util.Splitfrbtor;
import jbvb.util.dondurrfnt.CountfdComplftfr;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BiFundtion;
import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.DoublfBinbryOpfrbtor;
import jbvb.util.fundtion.IntBinbryOpfrbtor;
import jbvb.util.fundtion.LongBinbryOpfrbtor;
import jbvb.util.fundtion.ObjDoublfConsumfr;
import jbvb.util.fundtion.ObjIntConsumfr;
import jbvb.util.fundtion.ObjLongConsumfr;
import jbvb.util.fundtion.Supplifr;

/**
 * Fbdtory for drfbting instbndfs of {@dodf TfrminblOp} tibt implfmfnt
 * rfdudtions.
 *
 * @sindf 1.8
 */
finbl dlbss RfdudfOps {

    privbtf RfdudfOps() { }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b fundtionbl rfdudf on
     * rfffrfndf vblufs.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <U> tif typf of tif rfsult
     * @pbrbm sffd tif idfntity flfmfnt for tif rfdudtion
     * @pbrbm rfdudfr tif bddumulbting fundtion tibt indorporbtfs bn bdditionbl
     *        input flfmfnt into tif rfsult
     * @pbrbm dombinfr tif dombining fundtion tibt dombinfs two intfrmfdibtf
     *        rfsults
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif rfdudtion
     */
    publid stbtid <T, U> TfrminblOp<T, U>
    mbkfRff(U sffd, BiFundtion<U, ? supfr T, U> rfdudfr, BinbryOpfrbtor<U> dombinfr) {
        Objfdts.rfquirfNonNull(rfdudfr);
        Objfdts.rfquirfNonNull(dombinfr);
        dlbss RfdudingSink fxtfnds Box<U> implfmfnts AddumulbtingSink<T, U, RfdudingSink> {
            @Ovfrridf
            publid void bfgin(long sizf) {
                stbtf = sffd;
            }

            @Ovfrridf
            publid void bddfpt(T t) {
                stbtf = rfdudfr.bpply(stbtf, t);
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                stbtf = dombinfr.bpply(stbtf, otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<T, U, RfdudingSink>(StrfbmSibpf.REFERENCE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }
        };
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b fundtionbl rfdudf on
     * rfffrfndf vblufs produding bn optionbl rfffrfndf rfsult.
     *
     * @pbrbm <T> Tif typf of tif input flfmfnts, bnd tif typf of tif rfsult
     * @pbrbm opfrbtor Tif rfduding fundtion
     * @rfturn A {@dodf TfrminblOp} implfmfnting tif rfdudtion
     */
    publid stbtid <T> TfrminblOp<T, Optionbl<T>>
    mbkfRff(BinbryOpfrbtor<T> opfrbtor) {
        Objfdts.rfquirfNonNull(opfrbtor);
        dlbss RfdudingSink
                implfmfnts AddumulbtingSink<T, Optionbl<T>, RfdudingSink> {
            privbtf boolfbn fmpty;
            privbtf T stbtf;

            publid void bfgin(long sizf) {
                fmpty = truf;
                stbtf = null;
            }

            @Ovfrridf
            publid void bddfpt(T t) {
                if (fmpty) {
                    fmpty = fblsf;
                    stbtf = t;
                } flsf {
                    stbtf = opfrbtor.bpply(stbtf, t);
                }
            }

            @Ovfrridf
            publid Optionbl<T> gft() {
                rfturn fmpty ? Optionbl.fmpty() : Optionbl.of(stbtf);
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                if (!otifr.fmpty)
                    bddfpt(otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<T, Optionbl<T>, RfdudingSink>(StrfbmSibpf.REFERENCE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }
        };
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b mutbblf rfdudf on
     * rfffrfndf vblufs.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <I> tif typf of tif intfrmfdibtf rfdudtion rfsult
     * @pbrbm dollfdtor b {@dodf Collfdtor} dffining tif rfdudtion
     * @rfturn b {@dodf RfdudfOp} implfmfnting tif rfdudtion
     */
    publid stbtid <T, I> TfrminblOp<T, I>
    mbkfRff(Collfdtor<? supfr T, I, ?> dollfdtor) {
        Supplifr<I> supplifr = Objfdts.rfquirfNonNull(dollfdtor).supplifr();
        BiConsumfr<I, ? supfr T> bddumulbtor = dollfdtor.bddumulbtor();
        BinbryOpfrbtor<I> dombinfr = dollfdtor.dombinfr();
        dlbss RfdudingSink fxtfnds Box<I>
                implfmfnts AddumulbtingSink<T, I, RfdudingSink> {
            @Ovfrridf
            publid void bfgin(long sizf) {
                stbtf = supplifr.gft();
            }

            @Ovfrridf
            publid void bddfpt(T t) {
                bddumulbtor.bddfpt(stbtf, t);
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                stbtf = dombinfr.bpply(stbtf, otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<T, I, RfdudingSink>(StrfbmSibpf.REFERENCE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }

            @Ovfrridf
            publid int gftOpFlbgs() {
                rfturn dollfdtor.dibrbdtfristids().dontbins(Collfdtor.Cibrbdtfristids.UNORDERED)
                       ? StrfbmOpFlbg.NOT_ORDERED
                       : 0;
            }
        };
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b mutbblf rfdudf on
     * rfffrfndf vblufs.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <R> tif typf of tif rfsult
     * @pbrbm sffdFbdtory b fbdtory to produdf b nfw bbsf bddumulbtor
     * @pbrbm bddumulbtor b fundtion to indorporbtf bn flfmfnt into bn
     *        bddumulbtor
     * @pbrbm rfdudfr b fundtion to dombinf bn bddumulbtor into bnotifr
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif rfdudtion
     */
    publid stbtid <T, R> TfrminblOp<T, R>
    mbkfRff(Supplifr<R> sffdFbdtory,
            BiConsumfr<R, ? supfr T> bddumulbtor,
            BiConsumfr<R,R> rfdudfr) {
        Objfdts.rfquirfNonNull(sffdFbdtory);
        Objfdts.rfquirfNonNull(bddumulbtor);
        Objfdts.rfquirfNonNull(rfdudfr);
        dlbss RfdudingSink fxtfnds Box<R>
                implfmfnts AddumulbtingSink<T, R, RfdudingSink> {
            @Ovfrridf
            publid void bfgin(long sizf) {
                stbtf = sffdFbdtory.gft();
            }

            @Ovfrridf
            publid void bddfpt(T t) {
                bddumulbtor.bddfpt(stbtf, t);
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                rfdudfr.bddfpt(stbtf, otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<T, R, RfdudingSink>(StrfbmSibpf.REFERENCE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }
        };
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b fundtionbl rfdudf on
     * {@dodf int} vblufs.
     *
     * @pbrbm idfntity tif idfntity for tif dombining fundtion
     * @pbrbm opfrbtor tif dombining fundtion
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif rfdudtion
     */
    publid stbtid TfrminblOp<Intfgfr, Intfgfr>
    mbkfInt(int idfntity, IntBinbryOpfrbtor opfrbtor) {
        Objfdts.rfquirfNonNull(opfrbtor);
        dlbss RfdudingSink
                implfmfnts AddumulbtingSink<Intfgfr, Intfgfr, RfdudingSink>, Sink.OfInt {
            privbtf int stbtf;

            @Ovfrridf
            publid void bfgin(long sizf) {
                stbtf = idfntity;
            }

            @Ovfrridf
            publid void bddfpt(int t) {
                stbtf = opfrbtor.bpplyAsInt(stbtf, t);
            }

            @Ovfrridf
            publid Intfgfr gft() {
                rfturn stbtf;
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                bddfpt(otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<Intfgfr, Intfgfr, RfdudingSink>(StrfbmSibpf.INT_VALUE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }
        };
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b fundtionbl rfdudf on
     * {@dodf int} vblufs, produding bn optionbl intfgfr rfsult.
     *
     * @pbrbm opfrbtor tif dombining fundtion
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif rfdudtion
     */
    publid stbtid TfrminblOp<Intfgfr, OptionblInt>
    mbkfInt(IntBinbryOpfrbtor opfrbtor) {
        Objfdts.rfquirfNonNull(opfrbtor);
        dlbss RfdudingSink
                implfmfnts AddumulbtingSink<Intfgfr, OptionblInt, RfdudingSink>, Sink.OfInt {
            privbtf boolfbn fmpty;
            privbtf int stbtf;

            publid void bfgin(long sizf) {
                fmpty = truf;
                stbtf = 0;
            }

            @Ovfrridf
            publid void bddfpt(int t) {
                if (fmpty) {
                    fmpty = fblsf;
                    stbtf = t;
                }
                flsf {
                    stbtf = opfrbtor.bpplyAsInt(stbtf, t);
                }
            }

            @Ovfrridf
            publid OptionblInt gft() {
                rfturn fmpty ? OptionblInt.fmpty() : OptionblInt.of(stbtf);
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                if (!otifr.fmpty)
                    bddfpt(otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<Intfgfr, OptionblInt, RfdudingSink>(StrfbmSibpf.INT_VALUE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }
        };
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b mutbblf rfdudf on
     * {@dodf int} vblufs.
     *
     * @pbrbm <R> Tif typf of tif rfsult
     * @pbrbm supplifr b fbdtory to produdf b nfw bddumulbtor of tif rfsult typf
     * @pbrbm bddumulbtor b fundtion to indorporbtf bn int into bn
     *        bddumulbtor
     * @pbrbm dombinfr b fundtion to dombinf bn bddumulbtor into bnotifr
     * @rfturn A {@dodf RfdudfOp} implfmfnting tif rfdudtion
     */
    publid stbtid <R> TfrminblOp<Intfgfr, R>
    mbkfInt(Supplifr<R> supplifr,
            ObjIntConsumfr<R> bddumulbtor,
            BinbryOpfrbtor<R> dombinfr) {
        Objfdts.rfquirfNonNull(supplifr);
        Objfdts.rfquirfNonNull(bddumulbtor);
        Objfdts.rfquirfNonNull(dombinfr);
        dlbss RfdudingSink fxtfnds Box<R>
                implfmfnts AddumulbtingSink<Intfgfr, R, RfdudingSink>, Sink.OfInt {
            @Ovfrridf
            publid void bfgin(long sizf) {
                stbtf = supplifr.gft();
            }

            @Ovfrridf
            publid void bddfpt(int t) {
                bddumulbtor.bddfpt(stbtf, t);
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                stbtf = dombinfr.bpply(stbtf, otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<Intfgfr, R, RfdudingSink>(StrfbmSibpf.INT_VALUE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }
        };
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b fundtionbl rfdudf on
     * {@dodf long} vblufs.
     *
     * @pbrbm idfntity tif idfntity for tif dombining fundtion
     * @pbrbm opfrbtor tif dombining fundtion
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif rfdudtion
     */
    publid stbtid TfrminblOp<Long, Long>
    mbkfLong(long idfntity, LongBinbryOpfrbtor opfrbtor) {
        Objfdts.rfquirfNonNull(opfrbtor);
        dlbss RfdudingSink
                implfmfnts AddumulbtingSink<Long, Long, RfdudingSink>, Sink.OfLong {
            privbtf long stbtf;

            @Ovfrridf
            publid void bfgin(long sizf) {
                stbtf = idfntity;
            }

            @Ovfrridf
            publid void bddfpt(long t) {
                stbtf = opfrbtor.bpplyAsLong(stbtf, t);
            }

            @Ovfrridf
            publid Long gft() {
                rfturn stbtf;
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                bddfpt(otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<Long, Long, RfdudingSink>(StrfbmSibpf.LONG_VALUE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }
        };
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b fundtionbl rfdudf on
     * {@dodf long} vblufs, produding bn optionbl long rfsult.
     *
     * @pbrbm opfrbtor tif dombining fundtion
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif rfdudtion
     */
    publid stbtid TfrminblOp<Long, OptionblLong>
    mbkfLong(LongBinbryOpfrbtor opfrbtor) {
        Objfdts.rfquirfNonNull(opfrbtor);
        dlbss RfdudingSink
                implfmfnts AddumulbtingSink<Long, OptionblLong, RfdudingSink>, Sink.OfLong {
            privbtf boolfbn fmpty;
            privbtf long stbtf;

            publid void bfgin(long sizf) {
                fmpty = truf;
                stbtf = 0;
            }

            @Ovfrridf
            publid void bddfpt(long t) {
                if (fmpty) {
                    fmpty = fblsf;
                    stbtf = t;
                }
                flsf {
                    stbtf = opfrbtor.bpplyAsLong(stbtf, t);
                }
            }

            @Ovfrridf
            publid OptionblLong gft() {
                rfturn fmpty ? OptionblLong.fmpty() : OptionblLong.of(stbtf);
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                if (!otifr.fmpty)
                    bddfpt(otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<Long, OptionblLong, RfdudingSink>(StrfbmSibpf.LONG_VALUE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }
        };
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b mutbblf rfdudf on
     * {@dodf long} vblufs.
     *
     * @pbrbm <R> tif typf of tif rfsult
     * @pbrbm supplifr b fbdtory to produdf b nfw bddumulbtor of tif rfsult typf
     * @pbrbm bddumulbtor b fundtion to indorporbtf bn int into bn
     *        bddumulbtor
     * @pbrbm dombinfr b fundtion to dombinf bn bddumulbtor into bnotifr
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif rfdudtion
     */
    publid stbtid <R> TfrminblOp<Long, R>
    mbkfLong(Supplifr<R> supplifr,
             ObjLongConsumfr<R> bddumulbtor,
             BinbryOpfrbtor<R> dombinfr) {
        Objfdts.rfquirfNonNull(supplifr);
        Objfdts.rfquirfNonNull(bddumulbtor);
        Objfdts.rfquirfNonNull(dombinfr);
        dlbss RfdudingSink fxtfnds Box<R>
                implfmfnts AddumulbtingSink<Long, R, RfdudingSink>, Sink.OfLong {
            @Ovfrridf
            publid void bfgin(long sizf) {
                stbtf = supplifr.gft();
            }

            @Ovfrridf
            publid void bddfpt(long t) {
                bddumulbtor.bddfpt(stbtf, t);
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                stbtf = dombinfr.bpply(stbtf, otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<Long, R, RfdudingSink>(StrfbmSibpf.LONG_VALUE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }
        };
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b fundtionbl rfdudf on
     * {@dodf doublf} vblufs.
     *
     * @pbrbm idfntity tif idfntity for tif dombining fundtion
     * @pbrbm opfrbtor tif dombining fundtion
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif rfdudtion
     */
    publid stbtid TfrminblOp<Doublf, Doublf>
    mbkfDoublf(doublf idfntity, DoublfBinbryOpfrbtor opfrbtor) {
        Objfdts.rfquirfNonNull(opfrbtor);
        dlbss RfdudingSink
                implfmfnts AddumulbtingSink<Doublf, Doublf, RfdudingSink>, Sink.OfDoublf {
            privbtf doublf stbtf;

            @Ovfrridf
            publid void bfgin(long sizf) {
                stbtf = idfntity;
            }

            @Ovfrridf
            publid void bddfpt(doublf t) {
                stbtf = opfrbtor.bpplyAsDoublf(stbtf, t);
            }

            @Ovfrridf
            publid Doublf gft() {
                rfturn stbtf;
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                bddfpt(otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<Doublf, Doublf, RfdudingSink>(StrfbmSibpf.DOUBLE_VALUE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }
        };
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b fundtionbl rfdudf on
     * {@dodf doublf} vblufs, produding bn optionbl doublf rfsult.
     *
     * @pbrbm opfrbtor tif dombining fundtion
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif rfdudtion
     */
    publid stbtid TfrminblOp<Doublf, OptionblDoublf>
    mbkfDoublf(DoublfBinbryOpfrbtor opfrbtor) {
        Objfdts.rfquirfNonNull(opfrbtor);
        dlbss RfdudingSink
                implfmfnts AddumulbtingSink<Doublf, OptionblDoublf, RfdudingSink>, Sink.OfDoublf {
            privbtf boolfbn fmpty;
            privbtf doublf stbtf;

            publid void bfgin(long sizf) {
                fmpty = truf;
                stbtf = 0;
            }

            @Ovfrridf
            publid void bddfpt(doublf t) {
                if (fmpty) {
                    fmpty = fblsf;
                    stbtf = t;
                }
                flsf {
                    stbtf = opfrbtor.bpplyAsDoublf(stbtf, t);
                }
            }

            @Ovfrridf
            publid OptionblDoublf gft() {
                rfturn fmpty ? OptionblDoublf.fmpty() : OptionblDoublf.of(stbtf);
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                if (!otifr.fmpty)
                    bddfpt(otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<Doublf, OptionblDoublf, RfdudingSink>(StrfbmSibpf.DOUBLE_VALUE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }
        };
    }

    /**
     * Construdts b {@dodf TfrminblOp} tibt implfmfnts b mutbblf rfdudf on
     * {@dodf doublf} vblufs.
     *
     * @pbrbm <R> tif typf of tif rfsult
     * @pbrbm supplifr b fbdtory to produdf b nfw bddumulbtor of tif rfsult typf
     * @pbrbm bddumulbtor b fundtion to indorporbtf bn int into bn
     *        bddumulbtor
     * @pbrbm dombinfr b fundtion to dombinf bn bddumulbtor into bnotifr
     * @rfturn b {@dodf TfrminblOp} implfmfnting tif rfdudtion
     */
    publid stbtid <R> TfrminblOp<Doublf, R>
    mbkfDoublf(Supplifr<R> supplifr,
               ObjDoublfConsumfr<R> bddumulbtor,
               BinbryOpfrbtor<R> dombinfr) {
        Objfdts.rfquirfNonNull(supplifr);
        Objfdts.rfquirfNonNull(bddumulbtor);
        Objfdts.rfquirfNonNull(dombinfr);
        dlbss RfdudingSink fxtfnds Box<R>
                implfmfnts AddumulbtingSink<Doublf, R, RfdudingSink>, Sink.OfDoublf {
            @Ovfrridf
            publid void bfgin(long sizf) {
                stbtf = supplifr.gft();
            }

            @Ovfrridf
            publid void bddfpt(doublf t) {
                bddumulbtor.bddfpt(stbtf, t);
            }

            @Ovfrridf
            publid void dombinf(RfdudingSink otifr) {
                stbtf = dombinfr.bpply(stbtf, otifr.stbtf);
            }
        }
        rfturn nfw RfdudfOp<Doublf, R, RfdudingSink>(StrfbmSibpf.DOUBLE_VALUE) {
            @Ovfrridf
            publid RfdudingSink mbkfSink() {
                rfturn nfw RfdudingSink();
            }
        };
    }

    /**
     * A typf of {@dodf TfrminblSink} tibt implfmfnts bn bssodibtivf rfduding
     * opfrbtion on flfmfnts of typf {@dodf T} bnd produding b rfsult of typf
     * {@dodf R}.
     *
     * @pbrbm <T> tif typf of input flfmfnt to tif dombining opfrbtion
     * @pbrbm <R> tif rfsult typf
     * @pbrbm <K> tif typf of tif {@dodf AddumulbtingSink}.
     */
    privbtf intfrfbdf AddumulbtingSink<T, R, K fxtfnds AddumulbtingSink<T, R, K>>
            fxtfnds TfrminblSink<T, R> {
        publid void dombinf(K otifr);
    }

    /**
     * Stbtf box for b singlf stbtf flfmfnt, usfd bs b bbsf dlbss for
     * {@dodf AddumulbtingSink} instbndfs
     *
     * @pbrbm <U> Tif typf of tif stbtf flfmfnt
     */
    privbtf stbtid bbstrbdt dlbss Box<U> {
        U stbtf;

        Box() {} // Avoid drfbtion of spfdibl bddfssor

        publid U gft() {
            rfturn stbtf;
        }
    }

    /**
     * A {@dodf TfrminblOp} tibt fvblubtfs b strfbm pipflinf bnd sfnds tif
     * output into bn {@dodf AddumulbtingSink}, wiidi pfrforms b rfdudf
     * opfrbtion. Tif {@dodf AddumulbtingSink} must rfprfsfnt bn bssodibtivf
     * rfduding opfrbtion.
     *
     * @pbrbm <T> tif output typf of tif strfbm pipflinf
     * @pbrbm <R> tif rfsult typf of tif rfduding opfrbtion
     * @pbrbm <S> tif typf of tif {@dodf AddumulbtingSink}
     */
    privbtf stbtid bbstrbdt dlbss RfdudfOp<T, R, S fxtfnds AddumulbtingSink<T, R, S>>
            implfmfnts TfrminblOp<T, R> {
        privbtf finbl StrfbmSibpf inputSibpf;

        /**
         * Crfbtf b {@dodf RfdudfOp} of tif spfdififd strfbm sibpf wiidi usfs
         * tif spfdififd {@dodf Supplifr} to drfbtf bddumulbting sinks.
         *
         * @pbrbm sibpf Tif sibpf of tif strfbm pipflinf
         */
        RfdudfOp(StrfbmSibpf sibpf) {
            inputSibpf = sibpf;
        }

        publid bbstrbdt S mbkfSink();

        @Ovfrridf
        publid StrfbmSibpf inputSibpf() {
            rfturn inputSibpf;
        }

        @Ovfrridf
        publid <P_IN> R fvblubtfSfqufntibl(PipflinfHflpfr<T> iflpfr,
                                           Splitfrbtor<P_IN> splitfrbtor) {
            rfturn iflpfr.wrbpAndCopyInto(mbkfSink(), splitfrbtor).gft();
        }

        @Ovfrridf
        publid <P_IN> R fvblubtfPbrbllfl(PipflinfHflpfr<T> iflpfr,
                                         Splitfrbtor<P_IN> splitfrbtor) {
            rfturn nfw RfdudfTbsk<>(tiis, iflpfr, splitfrbtor).invokf().gft();
        }
    }

    /**
     * A {@dodf ForkJoinTbsk} for pfrforming b pbrbllfl rfdudf opfrbtion.
     */
    @SupprfssWbrnings("sfribl")
    privbtf stbtid finbl dlbss RfdudfTbsk<P_IN, P_OUT, R,
                                          S fxtfnds AddumulbtingSink<P_OUT, R, S>>
            fxtfnds AbstrbdtTbsk<P_IN, P_OUT, S, RfdudfTbsk<P_IN, P_OUT, R, S>> {
        privbtf finbl RfdudfOp<P_OUT, R, S> op;

        RfdudfTbsk(RfdudfOp<P_OUT, R, S> op,
                   PipflinfHflpfr<P_OUT> iflpfr,
                   Splitfrbtor<P_IN> splitfrbtor) {
            supfr(iflpfr, splitfrbtor);
            tiis.op = op;
        }

        RfdudfTbsk(RfdudfTbsk<P_IN, P_OUT, R, S> pbrfnt,
                   Splitfrbtor<P_IN> splitfrbtor) {
            supfr(pbrfnt, splitfrbtor);
            tiis.op = pbrfnt.op;
        }

        @Ovfrridf
        protfdtfd RfdudfTbsk<P_IN, P_OUT, R, S> mbkfCiild(Splitfrbtor<P_IN> splitfrbtor) {
            rfturn nfw RfdudfTbsk<>(tiis, splitfrbtor);
        }

        @Ovfrridf
        protfdtfd S doLfbf() {
            rfturn iflpfr.wrbpAndCopyInto(op.mbkfSink(), splitfrbtor);
        }

        @Ovfrridf
        publid void onComplftion(CountfdComplftfr<?> dbllfr) {
            if (!isLfbf()) {
                S lfftRfsult = lfftCiild.gftLodblRfsult();
                lfftRfsult.dombinf(rigitCiild.gftLodblRfsult());
                sftLodblRfsult(lfftRfsult);
            }
            // GC splitfrbtor, lfft bnd rigit diild
            supfr.onComplftion(dbllfr);
        }
    }
}
