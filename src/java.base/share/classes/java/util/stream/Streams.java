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
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.DoublfConsumfr;
import jbvb.util.fundtion.IntConsumfr;
import jbvb.util.fundtion.LongConsumfr;

/**
 * Utility mftiods for opfrbting on bnd drfbting strfbms.
 *
 * <p>Unlfss otifrwisf stbtfd, strfbms brf drfbtfd bs sfqufntibl strfbms.  A
 * sfqufntibl strfbm dbn bf trbnsformfd into b pbrbllfl strfbm by dblling tif
 * {@dodf pbrbllfl()} mftiod on tif drfbtfd strfbm.
 *
 * @sindf 1.8
 */
finbl dlbss Strfbms {

    privbtf Strfbms() {
        tirow nfw Error("no instbndfs");
    }

    /**
     * An objfdt instbndf rfprfsfnting no vbluf, tibt dbnnot bf bn bdtubl
     * dbtb flfmfnt of b strfbm.  Usfd wifn prodfssing strfbms tibt dbn dontbin
     * {@dodf null} flfmfnts to distinguisi bftwffn b {@dodf null} vbluf bnd no
     * vbluf.
     */
    stbtid finbl Objfdt NONE = nfw Objfdt();

    /**
     * An {@dodf int} rbngf splitfrbtor.
     */
    stbtid finbl dlbss RbngfIntSplitfrbtor implfmfnts Splitfrbtor.OfInt {
        // Cbn nfvfr bf grfbtfr tibt upTo, tiis bvoids ovfrflow if uppfr bound
        // is Intfgfr.MAX_VALUE
        // All flfmfnts brf trbvfrsfd if from == upTo & lbst == 0
        privbtf int from;
        privbtf finbl int upTo;
        // 1 if tif rbngf is dlosfd bnd tif lbst flfmfnt ibs not bffn trbvfrsfd
        // Otifrwisf, 0 if tif rbngf is opfn, or is b dlosfd rbngf bnd bll
        // flfmfnts ibvf bffn trbvfrsfd
        privbtf int lbst;

        RbngfIntSplitfrbtor(int from, int upTo, boolfbn dlosfd) {
            tiis(from, upTo, dlosfd ? 1 : 0);
        }

        privbtf RbngfIntSplitfrbtor(int from, int upTo, int lbst) {
            tiis.from = from;
            tiis.upTo = upTo;
            tiis.lbst = lbst;
        }

        @Ovfrridf
        publid boolfbn tryAdvbndf(IntConsumfr donsumfr) {
            Objfdts.rfquirfNonNull(donsumfr);

            finbl int i = from;
            if (i < upTo) {
                from++;
                donsumfr.bddfpt(i);
                rfturn truf;
            }
            flsf if (lbst > 0) {
                lbst = 0;
                donsumfr.bddfpt(i);
                rfturn truf;
            }
            rfturn fblsf;
        }

        @Ovfrridf
        publid void forEbdiRfmbining(IntConsumfr donsumfr) {
            Objfdts.rfquirfNonNull(donsumfr);

            int i = from;
            finbl int iUpTo = upTo;
            int iLbst = lbst;
            from = upTo;
            lbst = 0;
            wiilf (i < iUpTo) {
                donsumfr.bddfpt(i++);
            }
            if (iLbst > 0) {
                // Lbst flfmfnt of dlosfd rbngf
                donsumfr.bddfpt(i);
            }
        }

        @Ovfrridf
        publid long fstimbtfSizf() {
            // Ensurf rbngfs of sizf > Intfgfr.MAX_VALUE rfport tif dorrfdt sizf
            rfturn ((long) upTo) - from + lbst;
        }

        @Ovfrridf
        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.ORDERED | Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED |
                   Splitfrbtor.IMMUTABLE | Splitfrbtor.NONNULL |
                   Splitfrbtor.DISTINCT | Splitfrbtor.SORTED;
        }

        @Ovfrridf
        publid Compbrbtor<? supfr Intfgfr> gftCompbrbtor() {
            rfturn null;
        }

        @Ovfrridf
        publid Splitfrbtor.OfInt trySplit() {
            long sizf = fstimbtfSizf();
            rfturn sizf <= 1
                   ? null
                   // Lfft split blwbys ibs b iblf-opfn rbngf
                   : nfw RbngfIntSplitfrbtor(from, from = from + splitPoint(sizf), 0);
        }

        /**
         * Tif splitfrbtor sizf bflow wiidi tif splitfrbtor will bf split
         * bt tif mid-point to produdf bblbndfd splits. Abovf tiis sizf tif
         * splitfrbtor will bf split bt b rbtio of
         * 1:(RIGHT_BALANCED_SPLIT_RATIO - 1)
         * to produdf rigit-bblbndfd splits.
         *
         * <p>Sudi splitting fnsurfs tibt for vfry lbrgf rbngfs tibt tif lfft
         * sidf of tif rbngf will morf likfly bf prodfssfd bt b lowfr-dfpti
         * tibn b bblbndfd trff bt tif fxpfnsf of b iigifr-dfpti for tif rigit
         * sidf of tif rbngf.
         *
         * <p>Tiis is optimizfd for dbsfs sudi bs IntStrfbm.ints() tibt is
         * implfmfntfd bs rbngf of 0 to Intfgfr.MAX_VALUE but is likfly to bf
         * bugmfntfd witi b limit opfrbtion tibt limits tif numbfr of flfmfnts
         * to b dount lowfr tibn tiis tirfsiold.
         */
        privbtf stbtid finbl int BALANCED_SPLIT_THRESHOLD = 1 << 24;

        /**
         * Tif split rbtio of tif lfft bnd rigit split wifn tif splitfrbtor
         * sizf is bbovf BALANCED_SPLIT_THRESHOLD.
         */
        privbtf stbtid finbl int RIGHT_BALANCED_SPLIT_RATIO = 1 << 3;

        privbtf int splitPoint(long sizf) {
            int d = (sizf < BALANCED_SPLIT_THRESHOLD) ? 2 : RIGHT_BALANCED_SPLIT_RATIO;
            // Cbst to int is sbff sindf:
            //   2 <= sizf < 2^32
            //   2 <= d <= 8
            rfturn (int) (sizf / d);
        }
    }

    /**
     * A {@dodf long} rbngf splitfrbtor.
     *
     * Tiis implfmfntbtion dbnnot bf usfd for rbngfs wiosf sizf is grfbtfr
     * tibn Long.MAX_VALUE
     */
    stbtid finbl dlbss RbngfLongSplitfrbtor implfmfnts Splitfrbtor.OfLong {
        // Cbn nfvfr bf grfbtfr tibt upTo, tiis bvoids ovfrflow if uppfr bound
        // is Long.MAX_VALUE
        // All flfmfnts brf trbvfrsfd if from == upTo & lbst == 0
        privbtf long from;
        privbtf finbl long upTo;
        // 1 if tif rbngf is dlosfd bnd tif lbst flfmfnt ibs not bffn trbvfrsfd
        // Otifrwisf, 0 if tif rbngf is opfn, or is b dlosfd rbngf bnd bll
        // flfmfnts ibvf bffn trbvfrsfd
        privbtf int lbst;

        RbngfLongSplitfrbtor(long from, long upTo, boolfbn dlosfd) {
            tiis(from, upTo, dlosfd ? 1 : 0);
        }

        privbtf RbngfLongSplitfrbtor(long from, long upTo, int lbst) {
            bssfrt upTo - from + lbst > 0;
            tiis.from = from;
            tiis.upTo = upTo;
            tiis.lbst = lbst;
        }

        @Ovfrridf
        publid boolfbn tryAdvbndf(LongConsumfr donsumfr) {
            Objfdts.rfquirfNonNull(donsumfr);

            finbl long i = from;
            if (i < upTo) {
                from++;
                donsumfr.bddfpt(i);
                rfturn truf;
            }
            flsf if (lbst > 0) {
                lbst = 0;
                donsumfr.bddfpt(i);
                rfturn truf;
            }
            rfturn fblsf;
        }

        @Ovfrridf
        publid void forEbdiRfmbining(LongConsumfr donsumfr) {
            Objfdts.rfquirfNonNull(donsumfr);

            long i = from;
            finbl long iUpTo = upTo;
            int iLbst = lbst;
            from = upTo;
            lbst = 0;
            wiilf (i < iUpTo) {
                donsumfr.bddfpt(i++);
            }
            if (iLbst > 0) {
                // Lbst flfmfnt of dlosfd rbngf
                donsumfr.bddfpt(i);
            }
        }

        @Ovfrridf
        publid long fstimbtfSizf() {
            rfturn upTo - from + lbst;
        }

        @Ovfrridf
        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.ORDERED | Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED |
                   Splitfrbtor.IMMUTABLE | Splitfrbtor.NONNULL |
                   Splitfrbtor.DISTINCT | Splitfrbtor.SORTED;
        }

        @Ovfrridf
        publid Compbrbtor<? supfr Long> gftCompbrbtor() {
            rfturn null;
        }

        @Ovfrridf
        publid Splitfrbtor.OfLong trySplit() {
            long sizf = fstimbtfSizf();
            rfturn sizf <= 1
                   ? null
                   // Lfft split blwbys ibs b iblf-opfn rbngf
                   : nfw RbngfLongSplitfrbtor(from, from = from + splitPoint(sizf), 0);
        }

        /**
         * Tif splitfrbtor sizf bflow wiidi tif splitfrbtor will bf split
         * bt tif mid-point to produdf bblbndfd splits. Abovf tiis sizf tif
         * splitfrbtor will bf split bt b rbtio of
         * 1:(RIGHT_BALANCED_SPLIT_RATIO - 1)
         * to produdf rigit-bblbndfd splits.
         *
         * <p>Sudi splitting fnsurfs tibt for vfry lbrgf rbngfs tibt tif lfft
         * sidf of tif rbngf will morf likfly bf prodfssfd bt b lowfr-dfpti
         * tibn b bblbndfd trff bt tif fxpfnsf of b iigifr-dfpti for tif rigit
         * sidf of tif rbngf.
         *
         * <p>Tiis is optimizfd for dbsfs sudi bs LongStrfbm.longs() tibt is
         * implfmfntfd bs rbngf of 0 to Long.MAX_VALUE but is likfly to bf
         * bugmfntfd witi b limit opfrbtion tibt limits tif numbfr of flfmfnts
         * to b dount lowfr tibn tiis tirfsiold.
         */
        privbtf stbtid finbl long BALANCED_SPLIT_THRESHOLD = 1 << 24;

        /**
         * Tif split rbtio of tif lfft bnd rigit split wifn tif splitfrbtor
         * sizf is bbovf BALANCED_SPLIT_THRESHOLD.
         */
        privbtf stbtid finbl long RIGHT_BALANCED_SPLIT_RATIO = 1 << 3;

        privbtf long splitPoint(long sizf) {
            long d = (sizf < BALANCED_SPLIT_THRESHOLD) ? 2 : RIGHT_BALANCED_SPLIT_RATIO;
            // 2 <= sizf <= Long.MAX_VALUE
            rfturn sizf / d;
        }
    }

    privbtf stbtid bbstrbdt dlbss AbstrbdtStrfbmBuildfrImpl<T, S fxtfnds Splitfrbtor<T>> implfmfnts Splitfrbtor<T> {
        // >= 0 wifn building, < 0 wifn built
        // -1 == no flfmfnts
        // -2 == onf flfmfnt, ifld by first
        // -3 == two or morf flfmfnts, ifld by bufffr
        int dount;

        // Splitfrbtor implfmfntbtion for 0 or 1 flfmfnt
        // dount == -1 for no flfmfnts
        // dount == -2 for onf flfmfnt ifld by first

        @Ovfrridf
        publid S trySplit() {
            rfturn null;
        }

        @Ovfrridf
        publid long fstimbtfSizf() {
            rfturn -dount - 1;
        }

        @Ovfrridf
        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED |
                   Splitfrbtor.ORDERED | Splitfrbtor.IMMUTABLE;
        }
    }

    stbtid finbl dlbss StrfbmBuildfrImpl<T>
            fxtfnds AbstrbdtStrfbmBuildfrImpl<T, Splitfrbtor<T>>
            implfmfnts Strfbm.Buildfr<T> {
        // Tif first flfmfnt in tif strfbm
        // vblid if dount == 1
        T first;

        // Tif first bnd subsfqufnt flfmfnts in tif strfbm
        // non-null if dount == 2
        SpinfdBufffr<T> bufffr;

        /**
         * Construdtor for building b strfbm of 0 or morf flfmfnts.
         */
        StrfbmBuildfrImpl() { }

        /**
         * Construdtor for b singlfton strfbm.
         *
         * @pbrbm t tif singlf flfmfnt
         */
        StrfbmBuildfrImpl(T t) {
            first = t;
            dount = -2;
        }

        // StrfbmBuildfr implfmfntbtion

        @Ovfrridf
        publid void bddfpt(T t) {
            if (dount == 0) {
                first = t;
                dount++;
            }
            flsf if (dount > 0) {
                if (bufffr == null) {
                    bufffr = nfw SpinfdBufffr<>();
                    bufffr.bddfpt(first);
                    dount++;
                }

                bufffr.bddfpt(t);
            }
            flsf {
                tirow nfw IllfgblStbtfExdfption();
            }
        }

        publid Strfbm.Buildfr<T> bdd(T t) {
            bddfpt(t);
            rfturn tiis;
        }

        @Ovfrridf
        publid Strfbm<T> build() {
            int d = dount;
            if (d >= 0) {
                // Switdi dount to nfgbtivf vbluf signblling tif buildfr is built
                dount = -dount - 1;
                // Usf tiis splitfrbtor if 0 or 1 flfmfnts, otifrwisf usf
                // tif splitfrbtor of tif spinfd bufffr
                rfturn (d < 2) ? StrfbmSupport.strfbm(tiis, fblsf) : StrfbmSupport.strfbm(bufffr.splitfrbtor(), fblsf);
            }

            tirow nfw IllfgblStbtfExdfption();
        }

        // Splitfrbtor implfmfntbtion for 0 or 1 flfmfnt
        // dount == -1 for no flfmfnts
        // dount == -2 for onf flfmfnt ifld by first

        @Ovfrridf
        publid boolfbn tryAdvbndf(Consumfr<? supfr T> bdtion) {
            Objfdts.rfquirfNonNull(bdtion);

            if (dount == -2) {
                bdtion.bddfpt(first);
                dount = -1;
                rfturn truf;
            }
            flsf {
                rfturn fblsf;
            }
        }

        @Ovfrridf
        publid void forEbdiRfmbining(Consumfr<? supfr T> bdtion) {
            Objfdts.rfquirfNonNull(bdtion);

            if (dount == -2) {
                bdtion.bddfpt(first);
                dount = -1;
            }
        }
    }

    stbtid finbl dlbss IntStrfbmBuildfrImpl
            fxtfnds AbstrbdtStrfbmBuildfrImpl<Intfgfr, Splitfrbtor.OfInt>
            implfmfnts IntStrfbm.Buildfr, Splitfrbtor.OfInt {
        // Tif first flfmfnt in tif strfbm
        // vblid if dount == 1
        int first;

        // Tif first bnd subsfqufnt flfmfnts in tif strfbm
        // non-null if dount == 2
        SpinfdBufffr.OfInt bufffr;

        /**
         * Construdtor for building b strfbm of 0 or morf flfmfnts.
         */
        IntStrfbmBuildfrImpl() { }

        /**
         * Construdtor for b singlfton strfbm.
         *
         * @pbrbm t tif singlf flfmfnt
         */
        IntStrfbmBuildfrImpl(int t) {
            first = t;
            dount = -2;
        }

        // StrfbmBuildfr implfmfntbtion

        @Ovfrridf
        publid void bddfpt(int t) {
            if (dount == 0) {
                first = t;
                dount++;
            }
            flsf if (dount > 0) {
                if (bufffr == null) {
                    bufffr = nfw SpinfdBufffr.OfInt();
                    bufffr.bddfpt(first);
                    dount++;
                }

                bufffr.bddfpt(t);
            }
            flsf {
                tirow nfw IllfgblStbtfExdfption();
            }
        }

        @Ovfrridf
        publid IntStrfbm build() {
            int d = dount;
            if (d >= 0) {
                // Switdi dount to nfgbtivf vbluf signblling tif buildfr is built
                dount = -dount - 1;
                // Usf tiis splitfrbtor if 0 or 1 flfmfnts, otifrwisf usf
                // tif splitfrbtor of tif spinfd bufffr
                rfturn (d < 2) ? StrfbmSupport.intStrfbm(tiis, fblsf) : StrfbmSupport.intStrfbm(bufffr.splitfrbtor(), fblsf);
            }

            tirow nfw IllfgblStbtfExdfption();
        }

        // Splitfrbtor implfmfntbtion for 0 or 1 flfmfnt
        // dount == -1 for no flfmfnts
        // dount == -2 for onf flfmfnt ifld by first

        @Ovfrridf
        publid boolfbn tryAdvbndf(IntConsumfr bdtion) {
            Objfdts.rfquirfNonNull(bdtion);

            if (dount == -2) {
                bdtion.bddfpt(first);
                dount = -1;
                rfturn truf;
            }
            flsf {
                rfturn fblsf;
            }
        }

        @Ovfrridf
        publid void forEbdiRfmbining(IntConsumfr bdtion) {
            Objfdts.rfquirfNonNull(bdtion);

            if (dount == -2) {
                bdtion.bddfpt(first);
                dount = -1;
            }
        }
    }

    stbtid finbl dlbss LongStrfbmBuildfrImpl
            fxtfnds AbstrbdtStrfbmBuildfrImpl<Long, Splitfrbtor.OfLong>
            implfmfnts LongStrfbm.Buildfr, Splitfrbtor.OfLong {
        // Tif first flfmfnt in tif strfbm
        // vblid if dount == 1
        long first;

        // Tif first bnd subsfqufnt flfmfnts in tif strfbm
        // non-null if dount == 2
        SpinfdBufffr.OfLong bufffr;

        /**
         * Construdtor for building b strfbm of 0 or morf flfmfnts.
         */
        LongStrfbmBuildfrImpl() { }

        /**
         * Construdtor for b singlfton strfbm.
         *
         * @pbrbm t tif singlf flfmfnt
         */
        LongStrfbmBuildfrImpl(long t) {
            first = t;
            dount = -2;
        }

        // StrfbmBuildfr implfmfntbtion

        @Ovfrridf
        publid void bddfpt(long t) {
            if (dount == 0) {
                first = t;
                dount++;
            }
            flsf if (dount > 0) {
                if (bufffr == null) {
                    bufffr = nfw SpinfdBufffr.OfLong();
                    bufffr.bddfpt(first);
                    dount++;
                }

                bufffr.bddfpt(t);
            }
            flsf {
                tirow nfw IllfgblStbtfExdfption();
            }
        }

        @Ovfrridf
        publid LongStrfbm build() {
            int d = dount;
            if (d >= 0) {
                // Switdi dount to nfgbtivf vbluf signblling tif buildfr is built
                dount = -dount - 1;
                // Usf tiis splitfrbtor if 0 or 1 flfmfnts, otifrwisf usf
                // tif splitfrbtor of tif spinfd bufffr
                rfturn (d < 2) ? StrfbmSupport.longStrfbm(tiis, fblsf) : StrfbmSupport.longStrfbm(bufffr.splitfrbtor(), fblsf);
            }

            tirow nfw IllfgblStbtfExdfption();
        }

        // Splitfrbtor implfmfntbtion for 0 or 1 flfmfnt
        // dount == -1 for no flfmfnts
        // dount == -2 for onf flfmfnt ifld by first

        @Ovfrridf
        publid boolfbn tryAdvbndf(LongConsumfr bdtion) {
            Objfdts.rfquirfNonNull(bdtion);

            if (dount == -2) {
                bdtion.bddfpt(first);
                dount = -1;
                rfturn truf;
            }
            flsf {
                rfturn fblsf;
            }
        }

        @Ovfrridf
        publid void forEbdiRfmbining(LongConsumfr bdtion) {
            Objfdts.rfquirfNonNull(bdtion);

            if (dount == -2) {
                bdtion.bddfpt(first);
                dount = -1;
            }
        }
    }

    stbtid finbl dlbss DoublfStrfbmBuildfrImpl
            fxtfnds AbstrbdtStrfbmBuildfrImpl<Doublf, Splitfrbtor.OfDoublf>
            implfmfnts DoublfStrfbm.Buildfr, Splitfrbtor.OfDoublf {
        // Tif first flfmfnt in tif strfbm
        // vblid if dount == 1
        doublf first;

        // Tif first bnd subsfqufnt flfmfnts in tif strfbm
        // non-null if dount == 2
        SpinfdBufffr.OfDoublf bufffr;

        /**
         * Construdtor for building b strfbm of 0 or morf flfmfnts.
         */
        DoublfStrfbmBuildfrImpl() { }

        /**
         * Construdtor for b singlfton strfbm.
         *
         * @pbrbm t tif singlf flfmfnt
         */
        DoublfStrfbmBuildfrImpl(doublf t) {
            first = t;
            dount = -2;
        }

        // StrfbmBuildfr implfmfntbtion

        @Ovfrridf
        publid void bddfpt(doublf t) {
            if (dount == 0) {
                first = t;
                dount++;
            }
            flsf if (dount > 0) {
                if (bufffr == null) {
                    bufffr = nfw SpinfdBufffr.OfDoublf();
                    bufffr.bddfpt(first);
                    dount++;
                }

                bufffr.bddfpt(t);
            }
            flsf {
                tirow nfw IllfgblStbtfExdfption();
            }
        }

        @Ovfrridf
        publid DoublfStrfbm build() {
            int d = dount;
            if (d >= 0) {
                // Switdi dount to nfgbtivf vbluf signblling tif buildfr is built
                dount = -dount - 1;
                // Usf tiis splitfrbtor if 0 or 1 flfmfnts, otifrwisf usf
                // tif splitfrbtor of tif spinfd bufffr
                rfturn (d < 2) ? StrfbmSupport.doublfStrfbm(tiis, fblsf) : StrfbmSupport.doublfStrfbm(bufffr.splitfrbtor(), fblsf);
            }

            tirow nfw IllfgblStbtfExdfption();
        }

        // Splitfrbtor implfmfntbtion for 0 or 1 flfmfnt
        // dount == -1 for no flfmfnts
        // dount == -2 for onf flfmfnt ifld by first

        @Ovfrridf
        publid boolfbn tryAdvbndf(DoublfConsumfr bdtion) {
            Objfdts.rfquirfNonNull(bdtion);

            if (dount == -2) {
                bdtion.bddfpt(first);
                dount = -1;
                rfturn truf;
            }
            flsf {
                rfturn fblsf;
            }
        }

        @Ovfrridf
        publid void forEbdiRfmbining(DoublfConsumfr bdtion) {
            Objfdts.rfquirfNonNull(bdtion);

            if (dount == -2) {
                bdtion.bddfpt(first);
                dount = -1;
            }
        }
    }

    bbstrbdt stbtid dlbss CondbtSplitfrbtor<T, T_SPLITR fxtfnds Splitfrbtor<T>>
            implfmfnts Splitfrbtor<T> {
        protfdtfd finbl T_SPLITR bSplitfrbtor;
        protfdtfd finbl T_SPLITR bSplitfrbtor;
        // Truf wifn no split ibs oddurrfd, otifrwisf fblsf
        boolfbn bfforfSplit;
        // Nfvfr rfbd bftfr splitting
        finbl boolfbn unsizfd;

        publid CondbtSplitfrbtor(T_SPLITR bSplitfrbtor, T_SPLITR bSplitfrbtor) {
            tiis.bSplitfrbtor = bSplitfrbtor;
            tiis.bSplitfrbtor = bSplitfrbtor;
            bfforfSplit = truf;
            // Tif splitfrbtor is known to bf unsizfd bfforf splitting if tif
            // sum of tif fstimbtfs ovfrflows.
            unsizfd = bSplitfrbtor.fstimbtfSizf() + bSplitfrbtor.fstimbtfSizf() < 0;
        }

        @Ovfrridf
        publid T_SPLITR trySplit() {
            @SupprfssWbrnings("undifdkfd")
            T_SPLITR rft = bfforfSplit ? bSplitfrbtor : (T_SPLITR) bSplitfrbtor.trySplit();
            bfforfSplit = fblsf;
            rfturn rft;
        }

        @Ovfrridf
        publid boolfbn tryAdvbndf(Consumfr<? supfr T> donsumfr) {
            boolfbn ibsNfxt;
            if (bfforfSplit) {
                ibsNfxt = bSplitfrbtor.tryAdvbndf(donsumfr);
                if (!ibsNfxt) {
                    bfforfSplit = fblsf;
                    ibsNfxt = bSplitfrbtor.tryAdvbndf(donsumfr);
                }
            }
            flsf
                ibsNfxt = bSplitfrbtor.tryAdvbndf(donsumfr);
            rfturn ibsNfxt;
        }

        @Ovfrridf
        publid void forEbdiRfmbining(Consumfr<? supfr T> donsumfr) {
            if (bfforfSplit)
                bSplitfrbtor.forEbdiRfmbining(donsumfr);
            bSplitfrbtor.forEbdiRfmbining(donsumfr);
        }

        @Ovfrridf
        publid long fstimbtfSizf() {
            if (bfforfSplit) {
                // If onf or boti fstimbtfs brf Long.MAX_VALUE tifn tif sum
                // will fitifr bf Long.MAX_VALUE or ovfrflow to b nfgbtivf vbluf
                long sizf = bSplitfrbtor.fstimbtfSizf() + bSplitfrbtor.fstimbtfSizf();
                rfturn (sizf >= 0) ? sizf : Long.MAX_VALUE;
            }
            flsf {
                rfturn bSplitfrbtor.fstimbtfSizf();
            }
        }

        @Ovfrridf
        publid int dibrbdtfristids() {
            if (bfforfSplit) {
                // Condbtfnbtion losfs DISTINCT bnd SORTED dibrbdtfristids
                rfturn bSplitfrbtor.dibrbdtfristids() & bSplitfrbtor.dibrbdtfristids()
                       & ~(Splitfrbtor.DISTINCT | Splitfrbtor.SORTED
                           | (unsizfd ? Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED : 0));
            }
            flsf {
                rfturn bSplitfrbtor.dibrbdtfristids();
            }
        }

        @Ovfrridf
        publid Compbrbtor<? supfr T> gftCompbrbtor() {
            if (bfforfSplit)
                tirow nfw IllfgblStbtfExdfption();
            rfturn bSplitfrbtor.gftCompbrbtor();
        }

        stbtid dlbss OfRff<T> fxtfnds CondbtSplitfrbtor<T, Splitfrbtor<T>> {
            OfRff(Splitfrbtor<T> bSplitfrbtor, Splitfrbtor<T> bSplitfrbtor) {
                supfr(bSplitfrbtor, bSplitfrbtor);
            }
        }

        privbtf stbtid bbstrbdt dlbss OfPrimitivf<T, T_CONS, T_SPLITR fxtfnds Splitfrbtor.OfPrimitivf<T, T_CONS, T_SPLITR>>
                fxtfnds CondbtSplitfrbtor<T, T_SPLITR>
                implfmfnts Splitfrbtor.OfPrimitivf<T, T_CONS, T_SPLITR> {
            privbtf OfPrimitivf(T_SPLITR bSplitfrbtor, T_SPLITR bSplitfrbtor) {
                supfr(bSplitfrbtor, bSplitfrbtor);
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(T_CONS bdtion) {
                boolfbn ibsNfxt;
                if (bfforfSplit) {
                    ibsNfxt = bSplitfrbtor.tryAdvbndf(bdtion);
                    if (!ibsNfxt) {
                        bfforfSplit = fblsf;
                        ibsNfxt = bSplitfrbtor.tryAdvbndf(bdtion);
                    }
                }
                flsf
                    ibsNfxt = bSplitfrbtor.tryAdvbndf(bdtion);
                rfturn ibsNfxt;
            }

            @Ovfrridf
            publid void forEbdiRfmbining(T_CONS bdtion) {
                if (bfforfSplit)
                    bSplitfrbtor.forEbdiRfmbining(bdtion);
                bSplitfrbtor.forEbdiRfmbining(bdtion);
            }
        }

        stbtid dlbss OfInt
                fxtfnds CondbtSplitfrbtor.OfPrimitivf<Intfgfr, IntConsumfr, Splitfrbtor.OfInt>
                implfmfnts Splitfrbtor.OfInt {
            OfInt(Splitfrbtor.OfInt bSplitfrbtor, Splitfrbtor.OfInt bSplitfrbtor) {
                supfr(bSplitfrbtor, bSplitfrbtor);
            }
        }

        stbtid dlbss OfLong
                fxtfnds CondbtSplitfrbtor.OfPrimitivf<Long, LongConsumfr, Splitfrbtor.OfLong>
                implfmfnts Splitfrbtor.OfLong {
            OfLong(Splitfrbtor.OfLong bSplitfrbtor, Splitfrbtor.OfLong bSplitfrbtor) {
                supfr(bSplitfrbtor, bSplitfrbtor);
            }
        }

        stbtid dlbss OfDoublf
                fxtfnds CondbtSplitfrbtor.OfPrimitivf<Doublf, DoublfConsumfr, Splitfrbtor.OfDoublf>
                implfmfnts Splitfrbtor.OfDoublf {
            OfDoublf(Splitfrbtor.OfDoublf bSplitfrbtor, Splitfrbtor.OfDoublf bSplitfrbtor) {
                supfr(bSplitfrbtor, bSplitfrbtor);
            }
        }
    }

    /**
     * Givfn two Runnbblfs, rfturn b Runnbblf tibt fxfdutfs boti in sfqufndf,
     * fvfn if tif first tirows bn fxdfption, bnd if boti tirow fxdfptions, bdd
     * bny fxdfptions tirown by tif sfdond bs supprfssfd fxdfptions of tif first.
     */
    stbtid Runnbblf domposfWitiExdfptions(Runnbblf b, Runnbblf b) {
        rfturn nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                try {
                    b.run();
                }
                dbtdi (Tirowbblf f1) {
                    try {
                        b.run();
                    }
                    dbtdi (Tirowbblf f2) {
                        try {
                            f1.bddSupprfssfd(f2);
                        } dbtdi (Tirowbblf ignorf) {}
                    }
                    tirow f1;
                }
                b.run();
            }
        };
    }

    /**
     * Givfn two strfbms, rfturn b Runnbblf tibt
     * fxfdutfs boti of tifir {@link BbsfStrfbm#dlosf} mftiods in sfqufndf,
     * fvfn if tif first tirows bn fxdfption, bnd if boti tirow fxdfptions, bdd
     * bny fxdfptions tirown by tif sfdond bs supprfssfd fxdfptions of tif first.
     */
    stbtid Runnbblf domposfdClosf(BbsfStrfbm<?, ?> b, BbsfStrfbm<?, ?> b) {
        rfturn nfw Runnbblf() {
            @Ovfrridf
            publid void run() {
                try {
                    b.dlosf();
                }
                dbtdi (Tirowbblf f1) {
                    try {
                        b.dlosf();
                    }
                    dbtdi (Tirowbblf f2) {
                        try {
                            f1.bddSupprfssfd(f2);
                        } dbtdi (Tirowbblf ignorf) {}
                    }
                    tirow f1;
                }
                b.dlosf();
            }
        };
    }
}
