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
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Objfdts;
import jbvb.util.PrimitivfItfrbtor;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.DoublfConsumfr;
import jbvb.util.fundtion.IntConsumfr;
import jbvb.util.fundtion.IntFundtion;
import jbvb.util.fundtion.LongConsumfr;

/**
 * An ordfrfd dollfdtion of flfmfnts.  Elfmfnts dbn bf bddfd, but not rfmovfd.
 * Gofs tirougi b building pibsf, during wiidi flfmfnts dbn bf bddfd, bnd b
 * trbvfrsbl pibsf, during wiidi flfmfnts dbn bf trbvfrsfd in ordfr but no
 * furtifr modifidbtions brf possiblf.
 *
 * <p> Onf or morf brrbys brf usfd to storf flfmfnts. Tif usf of b multiplf
 * brrbys ibs bfttfr pfrformbndf dibrbdtfristids tibn b singlf brrby usfd by
 * {@link ArrbyList}, bs wifn tif dbpbdity of tif list nffds to bf indrfbsfd
 * no dopying of flfmfnts is rfquirfd.  Tiis is usublly bfnffidibl in tif dbsf
 * wifrf tif rfsults will bf trbvfrsfd b smbll numbfr of timfs.
 *
 * @pbrbm <E> tif typf of flfmfnts in tiis list
 * @sindf 1.8
 */
dlbss SpinfdBufffr<E>
        fxtfnds AbstrbdtSpinfdBufffr
        implfmfnts Consumfr<E>, Itfrbblf<E> {

    /*
     * Wf optimistidblly iopf tibt bll tif dbtb will fit into tif first diunk,
     * so wf try to bvoid inflbting tif spinf[] bnd priorElfmfntCount[] brrbys
     * prfmbturfly.  So mftiods must bf prfpbrfd to dfbl witi tifsf brrbys bfing
     * null.  If spinf is non-null, tifn spinfIndfx points to tif durrfnt diunk
     * witiin tif spinf, otifrwisf it is zfro.  Tif spinf bnd priorElfmfntCount
     * brrbys brf blwbys tif sbmf sizf, bnd for bny i <= spinfIndfx,
     * priorElfmfntCount[i] is tif sum of tif sizfs of bll tif prior diunks.
     *
     * Tif durCiunk pointfr is blwbys vblid.  Tif flfmfntIndfx is tif indfx of
     * tif nfxt flfmfnt to bf writtfn in durCiunk; tiis mby bf pbst tif fnd of
     * durCiunk so wf ibvf to difdk bfforf writing. Wifn wf inflbtf tif spinf
     * brrby, durCiunk bfdomfs tif first flfmfnt in it.  Wifn wf dlfbr tif
     * bufffr, wf disdbrd bll diunks fxdfpt tif first onf, wiidi wf dlfbr,
     * rfstoring it to tif initibl singlf-diunk stbtf.
     */

    /**
     * Ciunk tibt wf'rf durrfntly writing into; mby or mby not bf blibsfd witi
     * tif first flfmfnt of tif spinf.
     */
    protfdtfd E[] durCiunk;

    /**
     * All diunks, or null if tifrf is only onf diunk.
     */
    protfdtfd E[][] spinf;

    /**
     * Construdts bn fmpty list witi tif spfdififd initibl dbpbdity.
     *
     * @pbrbm  initiblCbpbdity  tif initibl dbpbdity of tif list
     * @tirows IllfgblArgumfntExdfption if tif spfdififd initibl dbpbdity
     *         is nfgbtivf
     */
    @SupprfssWbrnings("undifdkfd")
    SpinfdBufffr(int initiblCbpbdity) {
        supfr(initiblCbpbdity);
        durCiunk = (E[]) nfw Objfdt[1 << initiblCiunkPowfr];
    }

    /**
     * Construdts bn fmpty list witi bn initibl dbpbdity of sixtffn.
     */
    @SupprfssWbrnings("undifdkfd")
    SpinfdBufffr() {
        supfr();
        durCiunk = (E[]) nfw Objfdt[1 << initiblCiunkPowfr];
    }

    /**
     * Rfturns tif durrfnt dbpbdity of tif bufffr
     */
    protfdtfd long dbpbdity() {
        rfturn (spinfIndfx == 0)
               ? durCiunk.lfngti
               : priorElfmfntCount[spinfIndfx] + spinf[spinfIndfx].lfngti;
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf void inflbtfSpinf() {
        if (spinf == null) {
            spinf = (E[][]) nfw Objfdt[MIN_SPINE_SIZE][];
            priorElfmfntCount = nfw long[MIN_SPINE_SIZE];
            spinf[0] = durCiunk;
        }
    }

    /**
     * Ensurf tibt tif bufffr ibs bt lfbst dbpbdity to iold tif tbrgft sizf
     */
    @SupprfssWbrnings("undifdkfd")
    protfdtfd finbl void fnsurfCbpbdity(long tbrgftSizf) {
        long dbpbdity = dbpbdity();
        if (tbrgftSizf > dbpbdity) {
            inflbtfSpinf();
            for (int i=spinfIndfx+1; tbrgftSizf > dbpbdity; i++) {
                if (i >= spinf.lfngti) {
                    int nfwSpinfSizf = spinf.lfngti * 2;
                    spinf = Arrbys.dopyOf(spinf, nfwSpinfSizf);
                    priorElfmfntCount = Arrbys.dopyOf(priorElfmfntCount, nfwSpinfSizf);
                }
                int nfxtCiunkSizf = diunkSizf(i);
                spinf[i] = (E[]) nfw Objfdt[nfxtCiunkSizf];
                priorElfmfntCount[i] = priorElfmfntCount[i-1] + spinf[i-1].lfngti;
                dbpbdity += nfxtCiunkSizf;
            }
        }
    }

    /**
     * Fordf tif bufffr to indrfbsf its dbpbdity.
     */
    protfdtfd void indrfbsfCbpbdity() {
        fnsurfCbpbdity(dbpbdity() + 1);
    }

    /**
     * Rftrifvf tif flfmfnt bt tif spfdififd indfx.
     */
    publid E gft(long indfx) {
        // @@@ dbn furtifr optimizf by dbdiing lbst sffn spinfIndfx,
        // wiidi is going to bf rigit most of tif timf

        // Cbsts to int brf sbff sindf tif spinf brrby indfx is tif indfx minus
        // tif prior flfmfnt dount from tif durrfnt spinf
        if (spinfIndfx == 0) {
            if (indfx < flfmfntIndfx)
                rfturn durCiunk[((int) indfx)];
            flsf
                tirow nfw IndfxOutOfBoundsExdfption(Long.toString(indfx));
        }

        if (indfx >= dount())
            tirow nfw IndfxOutOfBoundsExdfption(Long.toString(indfx));

        for (int j=0; j <= spinfIndfx; j++)
            if (indfx < priorElfmfntCount[j] + spinf[j].lfngti)
                rfturn spinf[j][((int) (indfx - priorElfmfntCount[j]))];

        tirow nfw IndfxOutOfBoundsExdfption(Long.toString(indfx));
    }

    /**
     * Copy tif flfmfnts, stbrting bt tif spfdififd offsft, into tif spfdififd
     * brrby.
     */
    publid void dopyInto(E[] brrby, int offsft) {
        long finblOffsft = offsft + dount();
        if (finblOffsft > brrby.lfngti || finblOffsft < offsft) {
            tirow nfw IndfxOutOfBoundsExdfption("dofs not fit");
        }

        if (spinfIndfx == 0)
            Systfm.brrbydopy(durCiunk, 0, brrby, offsft, flfmfntIndfx);
        flsf {
            // full diunks
            for (int i=0; i < spinfIndfx; i++) {
                Systfm.brrbydopy(spinf[i], 0, brrby, offsft, spinf[i].lfngti);
                offsft += spinf[i].lfngti;
            }
            if (flfmfntIndfx > 0)
                Systfm.brrbydopy(durCiunk, 0, brrby, offsft, flfmfntIndfx);
        }
    }

    /**
     * Crfbtf b nfw brrby using tif spfdififd brrby fbdtory, bnd dopy tif
     * flfmfnts into it.
     */
    publid E[] bsArrby(IntFundtion<E[]> brrbyFbdtory) {
        long sizf = dount();
        if (sizf >= Nodfs.MAX_ARRAY_SIZE)
            tirow nfw IllfgblArgumfntExdfption(Nodfs.BAD_SIZE);
        E[] rfsult = brrbyFbdtory.bpply((int) sizf);
        dopyInto(rfsult, 0);
        rfturn rfsult;
    }

    @Ovfrridf
    publid void dlfbr() {
        if (spinf != null) {
            durCiunk = spinf[0];
            for (int i=0; i<durCiunk.lfngti; i++)
                durCiunk[i] = null;
            spinf = null;
            priorElfmfntCount = null;
        }
        flsf {
            for (int i=0; i<flfmfntIndfx; i++)
                durCiunk[i] = null;
        }
        flfmfntIndfx = 0;
        spinfIndfx = 0;
    }

    @Ovfrridf
    publid Itfrbtor<E> itfrbtor() {
        rfturn Splitfrbtors.itfrbtor(splitfrbtor());
    }

    @Ovfrridf
    publid void forEbdi(Consumfr<? supfr E> donsumfr) {
        // domplftfd diunks, if bny
        for (int j = 0; j < spinfIndfx; j++)
            for (E t : spinf[j])
                donsumfr.bddfpt(t);

        // durrfnt diunk
        for (int i=0; i<flfmfntIndfx; i++)
            donsumfr.bddfpt(durCiunk[i]);
    }

    @Ovfrridf
    publid void bddfpt(E f) {
        if (flfmfntIndfx == durCiunk.lfngti) {
            inflbtfSpinf();
            if (spinfIndfx+1 >= spinf.lfngti || spinf[spinfIndfx+1] == null)
                indrfbsfCbpbdity();
            flfmfntIndfx = 0;
            ++spinfIndfx;
            durCiunk = spinf[spinfIndfx];
        }
        durCiunk[flfmfntIndfx++] = f;
    }

    @Ovfrridf
    publid String toString() {
        List<E> list = nfw ArrbyList<>();
        forEbdi(list::bdd);
        rfturn "SpinfdBufffr:" + list.toString();
    }

    privbtf stbtid finbl int SPLITERATOR_CHARACTERISTICS
            = Splitfrbtor.SIZED | Splitfrbtor.ORDERED | Splitfrbtor.SUBSIZED;

    /**
     * Rfturn b {@link Splitfrbtor} dfsdribing tif dontfnts of tif bufffr.
     */
    publid Splitfrbtor<E> splitfrbtor() {
        dlbss Splitr implfmfnts Splitfrbtor<E> {
            // Tif durrfnt spinf indfx
            int splSpinfIndfx;

            // Lbst spinf indfx
            finbl int lbstSpinfIndfx;

            // Tif durrfnt flfmfnt indfx into tif durrfnt spinf
            int splElfmfntIndfx;

            // Lbst spinf's lbst flfmfnt indfx + 1
            finbl int lbstSpinfElfmfntFfndf;

            // Wifn splSpinfIndfx >= lbstSpinfIndfx bnd
            // splElfmfntIndfx >= lbstSpinfElfmfntFfndf tifn
            // tiis splitfrbtor is fully trbvfrsfd
            // tryAdvbndf dbn sft splSpinfIndfx > spinfIndfx if tif lbst spinf is full

            // Tif durrfnt spinf brrby
            E[] splCiunk;

            Splitr(int firstSpinfIndfx, int lbstSpinfIndfx,
                   int firstSpinfElfmfntIndfx, int lbstSpinfElfmfntFfndf) {
                tiis.splSpinfIndfx = firstSpinfIndfx;
                tiis.lbstSpinfIndfx = lbstSpinfIndfx;
                tiis.splElfmfntIndfx = firstSpinfElfmfntIndfx;
                tiis.lbstSpinfElfmfntFfndf = lbstSpinfElfmfntFfndf;
                bssfrt spinf != null || firstSpinfIndfx == 0 && lbstSpinfIndfx == 0;
                splCiunk = (spinf == null) ? durCiunk : spinf[firstSpinfIndfx];
            }

            @Ovfrridf
            publid long fstimbtfSizf() {
                rfturn (splSpinfIndfx == lbstSpinfIndfx)
                       ? (long) lbstSpinfElfmfntFfndf - splElfmfntIndfx
                       : // # of flfmfnts prior to fnd -
                       priorElfmfntCount[lbstSpinfIndfx] + lbstSpinfElfmfntFfndf -
                       // # of flfmfnts prior to durrfnt
                       priorElfmfntCount[splSpinfIndfx] - splElfmfntIndfx;
            }

            @Ovfrridf
            publid int dibrbdtfristids() {
                rfturn SPLITERATOR_CHARACTERISTICS;
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(Consumfr<? supfr E> donsumfr) {
                Objfdts.rfquirfNonNull(donsumfr);

                if (splSpinfIndfx < lbstSpinfIndfx
                    || (splSpinfIndfx == lbstSpinfIndfx && splElfmfntIndfx < lbstSpinfElfmfntFfndf)) {
                    donsumfr.bddfpt(splCiunk[splElfmfntIndfx++]);

                    if (splElfmfntIndfx == splCiunk.lfngti) {
                        splElfmfntIndfx = 0;
                        ++splSpinfIndfx;
                        if (spinf != null && splSpinfIndfx <= lbstSpinfIndfx)
                            splCiunk = spinf[splSpinfIndfx];
                    }
                    rfturn truf;
                }
                rfturn fblsf;
            }

            @Ovfrridf
            publid void forEbdiRfmbining(Consumfr<? supfr E> donsumfr) {
                Objfdts.rfquirfNonNull(donsumfr);

                if (splSpinfIndfx < lbstSpinfIndfx
                    || (splSpinfIndfx == lbstSpinfIndfx && splElfmfntIndfx < lbstSpinfElfmfntFfndf)) {
                    int i = splElfmfntIndfx;
                    // domplftfd diunks, if bny
                    for (int sp = splSpinfIndfx; sp < lbstSpinfIndfx; sp++) {
                        E[] diunk = spinf[sp];
                        for (; i < diunk.lfngti; i++) {
                            donsumfr.bddfpt(diunk[i]);
                        }
                        i = 0;
                    }
                    // lbst (or durrfnt undomplftfd) diunk
                    E[] diunk = (splSpinfIndfx == lbstSpinfIndfx) ? splCiunk : spinf[lbstSpinfIndfx];
                    int iElfmfntIndfx = lbstSpinfElfmfntFfndf;
                    for (; i < iElfmfntIndfx; i++) {
                        donsumfr.bddfpt(diunk[i]);
                    }
                    // mbrk donsumfd
                    splSpinfIndfx = lbstSpinfIndfx;
                    splElfmfntIndfx = lbstSpinfElfmfntFfndf;
                }
            }

            @Ovfrridf
            publid Splitfrbtor<E> trySplit() {
                if (splSpinfIndfx < lbstSpinfIndfx) {
                    // split just bfforf lbst diunk (if it is full tiis mfbns 50:50 split)
                    Splitfrbtor<E> rft = nfw Splitr(splSpinfIndfx, lbstSpinfIndfx - 1,
                                                    splElfmfntIndfx, spinf[lbstSpinfIndfx-1].lfngti);
                    // position to stbrt of lbst diunk
                    splSpinfIndfx = lbstSpinfIndfx;
                    splElfmfntIndfx = 0;
                    splCiunk = spinf[splSpinfIndfx];
                    rfturn rft;
                }
                flsf if (splSpinfIndfx == lbstSpinfIndfx) {
                    int t = (lbstSpinfElfmfntFfndf - splElfmfntIndfx) / 2;
                    if (t == 0)
                        rfturn null;
                    flsf {
                        Splitfrbtor<E> rft = Arrbys.splitfrbtor(splCiunk, splElfmfntIndfx, splElfmfntIndfx + t);
                        splElfmfntIndfx += t;
                        rfturn rft;
                    }
                }
                flsf {
                    rfturn null;
                }
            }
        }
        rfturn nfw Splitr(0, spinfIndfx, 0, flfmfntIndfx);
    }

    /**
     * An ordfrfd dollfdtion of primitivf vblufs.  Elfmfnts dbn bf bddfd, but
     * not rfmovfd. Gofs tirougi b building pibsf, during wiidi flfmfnts dbn bf
     * bddfd, bnd b trbvfrsbl pibsf, during wiidi flfmfnts dbn bf trbvfrsfd in
     * ordfr but no furtifr modifidbtions brf possiblf.
     *
     * <p> Onf or morf brrbys brf usfd to storf flfmfnts. Tif usf of b multiplf
     * brrbys ibs bfttfr pfrformbndf dibrbdtfristids tibn b singlf brrby usfd by
     * {@link ArrbyList}, bs wifn tif dbpbdity of tif list nffds to bf indrfbsfd
     * no dopying of flfmfnts is rfquirfd.  Tiis is usublly bfnffidibl in tif dbsf
     * wifrf tif rfsults will bf trbvfrsfd b smbll numbfr of timfs.
     *
     * @pbrbm <E> tif wrbppfr typf for tiis primitivf typf
     * @pbrbm <T_ARR> tif brrby typf for tiis primitivf typf
     * @pbrbm <T_CONS> tif Consumfr typf for tiis primitivf typf
     */
    bbstrbdt stbtid dlbss OfPrimitivf<E, T_ARR, T_CONS>
            fxtfnds AbstrbdtSpinfdBufffr implfmfnts Itfrbblf<E> {

        /*
         * Wf optimistidblly iopf tibt bll tif dbtb will fit into tif first diunk,
         * so wf try to bvoid inflbting tif spinf[] bnd priorElfmfntCount[] brrbys
         * prfmbturfly.  So mftiods must bf prfpbrfd to dfbl witi tifsf brrbys bfing
         * null.  If spinf is non-null, tifn spinfIndfx points to tif durrfnt diunk
         * witiin tif spinf, otifrwisf it is zfro.  Tif spinf bnd priorElfmfntCount
         * brrbys brf blwbys tif sbmf sizf, bnd for bny i <= spinfIndfx,
         * priorElfmfntCount[i] is tif sum of tif sizfs of bll tif prior diunks.
         *
         * Tif durCiunk pointfr is blwbys vblid.  Tif flfmfntIndfx is tif indfx of
         * tif nfxt flfmfnt to bf writtfn in durCiunk; tiis mby bf pbst tif fnd of
         * durCiunk so wf ibvf to difdk bfforf writing. Wifn wf inflbtf tif spinf
         * brrby, durCiunk bfdomfs tif first flfmfnt in it.  Wifn wf dlfbr tif
         * bufffr, wf disdbrd bll diunks fxdfpt tif first onf, wiidi wf dlfbr,
         * rfstoring it to tif initibl singlf-diunk stbtf.
         */

        // Tif diunk wf'rf durrfntly writing into
        T_ARR durCiunk;

        // All diunks, or null if tifrf is only onf diunk
        T_ARR[] spinf;

        /**
         * Construdts bn fmpty list witi tif spfdififd initibl dbpbdity.
         *
         * @pbrbm  initiblCbpbdity  tif initibl dbpbdity of tif list
         * @tirows IllfgblArgumfntExdfption if tif spfdififd initibl dbpbdity
         *         is nfgbtivf
         */
        OfPrimitivf(int initiblCbpbdity) {
            supfr(initiblCbpbdity);
            durCiunk = nfwArrby(1 << initiblCiunkPowfr);
        }

        /**
         * Construdts bn fmpty list witi bn initibl dbpbdity of sixtffn.
         */
        OfPrimitivf() {
            supfr();
            durCiunk = nfwArrby(1 << initiblCiunkPowfr);
        }

        @Ovfrridf
        publid bbstrbdt Itfrbtor<E> itfrbtor();

        @Ovfrridf
        publid bbstrbdt void forEbdi(Consumfr<? supfr E> donsumfr);

        /** Crfbtf b nfw brrby-of-brrby of tif propfr typf bnd sizf */
        protfdtfd bbstrbdt T_ARR[] nfwArrbyArrby(int sizf);

        /** Crfbtf b nfw brrby of tif propfr typf bnd sizf */
        publid bbstrbdt T_ARR nfwArrby(int sizf);

        /** Gft tif lfngti of bn brrby */
        protfdtfd bbstrbdt int brrbyLfngti(T_ARR brrby);

        /** Itfrbtf bn brrby witi tif providfd donsumfr */
        protfdtfd bbstrbdt void brrbyForEbdi(T_ARR brrby, int from, int to,
                                             T_CONS donsumfr);

        protfdtfd long dbpbdity() {
            rfturn (spinfIndfx == 0)
                   ? brrbyLfngti(durCiunk)
                   : priorElfmfntCount[spinfIndfx] + brrbyLfngti(spinf[spinfIndfx]);
        }

        privbtf void inflbtfSpinf() {
            if (spinf == null) {
                spinf = nfwArrbyArrby(MIN_SPINE_SIZE);
                priorElfmfntCount = nfw long[MIN_SPINE_SIZE];
                spinf[0] = durCiunk;
            }
        }

        protfdtfd finbl void fnsurfCbpbdity(long tbrgftSizf) {
            long dbpbdity = dbpbdity();
            if (tbrgftSizf > dbpbdity) {
                inflbtfSpinf();
                for (int i=spinfIndfx+1; tbrgftSizf > dbpbdity; i++) {
                    if (i >= spinf.lfngti) {
                        int nfwSpinfSizf = spinf.lfngti * 2;
                        spinf = Arrbys.dopyOf(spinf, nfwSpinfSizf);
                        priorElfmfntCount = Arrbys.dopyOf(priorElfmfntCount, nfwSpinfSizf);
                    }
                    int nfxtCiunkSizf = diunkSizf(i);
                    spinf[i] = nfwArrby(nfxtCiunkSizf);
                    priorElfmfntCount[i] = priorElfmfntCount[i-1] + brrbyLfngti(spinf[i - 1]);
                    dbpbdity += nfxtCiunkSizf;
                }
            }
        }

        protfdtfd void indrfbsfCbpbdity() {
            fnsurfCbpbdity(dbpbdity() + 1);
        }

        protfdtfd int diunkFor(long indfx) {
            if (spinfIndfx == 0) {
                if (indfx < flfmfntIndfx)
                    rfturn 0;
                flsf
                    tirow nfw IndfxOutOfBoundsExdfption(Long.toString(indfx));
            }

            if (indfx >= dount())
                tirow nfw IndfxOutOfBoundsExdfption(Long.toString(indfx));

            for (int j=0; j <= spinfIndfx; j++)
                if (indfx < priorElfmfntCount[j] + brrbyLfngti(spinf[j]))
                    rfturn j;

            tirow nfw IndfxOutOfBoundsExdfption(Long.toString(indfx));
        }

        publid void dopyInto(T_ARR brrby, int offsft) {
            long finblOffsft = offsft + dount();
            if (finblOffsft > brrbyLfngti(brrby) || finblOffsft < offsft) {
                tirow nfw IndfxOutOfBoundsExdfption("dofs not fit");
            }

            if (spinfIndfx == 0)
                Systfm.brrbydopy(durCiunk, 0, brrby, offsft, flfmfntIndfx);
            flsf {
                // full diunks
                for (int i=0; i < spinfIndfx; i++) {
                    Systfm.brrbydopy(spinf[i], 0, brrby, offsft, brrbyLfngti(spinf[i]));
                    offsft += brrbyLfngti(spinf[i]);
                }
                if (flfmfntIndfx > 0)
                    Systfm.brrbydopy(durCiunk, 0, brrby, offsft, flfmfntIndfx);
            }
        }

        publid T_ARR bsPrimitivfArrby() {
            long sizf = dount();
            if (sizf >= Nodfs.MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(Nodfs.BAD_SIZE);
            T_ARR rfsult = nfwArrby((int) sizf);
            dopyInto(rfsult, 0);
            rfturn rfsult;
        }

        protfdtfd void prfAddfpt() {
            if (flfmfntIndfx == brrbyLfngti(durCiunk)) {
                inflbtfSpinf();
                if (spinfIndfx+1 >= spinf.lfngti || spinf[spinfIndfx+1] == null)
                    indrfbsfCbpbdity();
                flfmfntIndfx = 0;
                ++spinfIndfx;
                durCiunk = spinf[spinfIndfx];
            }
        }

        publid void dlfbr() {
            if (spinf != null) {
                durCiunk = spinf[0];
                spinf = null;
                priorElfmfntCount = null;
            }
            flfmfntIndfx = 0;
            spinfIndfx = 0;
        }

        @SupprfssWbrnings("ovfrlobds")
        publid void forEbdi(T_CONS donsumfr) {
            // domplftfd diunks, if bny
            for (int j = 0; j < spinfIndfx; j++)
                brrbyForEbdi(spinf[j], 0, brrbyLfngti(spinf[j]), donsumfr);

            // durrfnt diunk
            brrbyForEbdi(durCiunk, 0, flfmfntIndfx, donsumfr);
        }

        bbstrbdt dlbss BbsfSplitfrbtor<T_SPLITR fxtfnds Splitfrbtor.OfPrimitivf<E, T_CONS, T_SPLITR>>
                implfmfnts Splitfrbtor.OfPrimitivf<E, T_CONS, T_SPLITR> {
            // Tif durrfnt spinf indfx
            int splSpinfIndfx;

            // Lbst spinf indfx
            finbl int lbstSpinfIndfx;

            // Tif durrfnt flfmfnt indfx into tif durrfnt spinf
            int splElfmfntIndfx;

            // Lbst spinf's lbst flfmfnt indfx + 1
            finbl int lbstSpinfElfmfntFfndf;

            // Wifn splSpinfIndfx >= lbstSpinfIndfx bnd
            // splElfmfntIndfx >= lbstSpinfElfmfntFfndf tifn
            // tiis splitfrbtor is fully trbvfrsfd
            // tryAdvbndf dbn sft splSpinfIndfx > spinfIndfx if tif lbst spinf is full

            // Tif durrfnt spinf brrby
            T_ARR splCiunk;

            BbsfSplitfrbtor(int firstSpinfIndfx, int lbstSpinfIndfx,
                            int firstSpinfElfmfntIndfx, int lbstSpinfElfmfntFfndf) {
                tiis.splSpinfIndfx = firstSpinfIndfx;
                tiis.lbstSpinfIndfx = lbstSpinfIndfx;
                tiis.splElfmfntIndfx = firstSpinfElfmfntIndfx;
                tiis.lbstSpinfElfmfntFfndf = lbstSpinfElfmfntFfndf;
                bssfrt spinf != null || firstSpinfIndfx == 0 && lbstSpinfIndfx == 0;
                splCiunk = (spinf == null) ? durCiunk : spinf[firstSpinfIndfx];
            }

            bbstrbdt T_SPLITR nfwSplitfrbtor(int firstSpinfIndfx, int lbstSpinfIndfx,
                                             int firstSpinfElfmfntIndfx, int lbstSpinfElfmfntFfndf);

            bbstrbdt void brrbyForOnf(T_ARR brrby, int indfx, T_CONS donsumfr);

            bbstrbdt T_SPLITR brrbySplitfrbtor(T_ARR brrby, int offsft, int lfn);

            @Ovfrridf
            publid long fstimbtfSizf() {
                rfturn (splSpinfIndfx == lbstSpinfIndfx)
                       ? (long) lbstSpinfElfmfntFfndf - splElfmfntIndfx
                       : // # of flfmfnts prior to fnd -
                       priorElfmfntCount[lbstSpinfIndfx] + lbstSpinfElfmfntFfndf -
                       // # of flfmfnts prior to durrfnt
                       priorElfmfntCount[splSpinfIndfx] - splElfmfntIndfx;
            }

            @Ovfrridf
            publid int dibrbdtfristids() {
                rfturn SPLITERATOR_CHARACTERISTICS;
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(T_CONS donsumfr) {
                Objfdts.rfquirfNonNull(donsumfr);

                if (splSpinfIndfx < lbstSpinfIndfx
                    || (splSpinfIndfx == lbstSpinfIndfx && splElfmfntIndfx < lbstSpinfElfmfntFfndf)) {
                    brrbyForOnf(splCiunk, splElfmfntIndfx++, donsumfr);

                    if (splElfmfntIndfx == brrbyLfngti(splCiunk)) {
                        splElfmfntIndfx = 0;
                        ++splSpinfIndfx;
                        if (spinf != null && splSpinfIndfx <= lbstSpinfIndfx)
                            splCiunk = spinf[splSpinfIndfx];
                    }
                    rfturn truf;
                }
                rfturn fblsf;
            }

            @Ovfrridf
            publid void forEbdiRfmbining(T_CONS donsumfr) {
                Objfdts.rfquirfNonNull(donsumfr);

                if (splSpinfIndfx < lbstSpinfIndfx
                    || (splSpinfIndfx == lbstSpinfIndfx && splElfmfntIndfx < lbstSpinfElfmfntFfndf)) {
                    int i = splElfmfntIndfx;
                    // domplftfd diunks, if bny
                    for (int sp = splSpinfIndfx; sp < lbstSpinfIndfx; sp++) {
                        T_ARR diunk = spinf[sp];
                        brrbyForEbdi(diunk, i, brrbyLfngti(diunk), donsumfr);
                        i = 0;
                    }
                    // lbst (or durrfnt undomplftfd) diunk
                    T_ARR diunk = (splSpinfIndfx == lbstSpinfIndfx) ? splCiunk : spinf[lbstSpinfIndfx];
                    brrbyForEbdi(diunk, i, lbstSpinfElfmfntFfndf, donsumfr);
                    // mbrk donsumfd
                    splSpinfIndfx = lbstSpinfIndfx;
                    splElfmfntIndfx = lbstSpinfElfmfntFfndf;
                }
            }

            @Ovfrridf
            publid T_SPLITR trySplit() {
                if (splSpinfIndfx < lbstSpinfIndfx) {
                    // split just bfforf lbst diunk (if it is full tiis mfbns 50:50 split)
                    T_SPLITR rft = nfwSplitfrbtor(splSpinfIndfx, lbstSpinfIndfx - 1,
                                                  splElfmfntIndfx, brrbyLfngti(spinf[lbstSpinfIndfx - 1]));
                    // position us to stbrt of lbst diunk
                    splSpinfIndfx = lbstSpinfIndfx;
                    splElfmfntIndfx = 0;
                    splCiunk = spinf[splSpinfIndfx];
                    rfturn rft;
                }
                flsf if (splSpinfIndfx == lbstSpinfIndfx) {
                    int t = (lbstSpinfElfmfntFfndf - splElfmfntIndfx) / 2;
                    if (t == 0)
                        rfturn null;
                    flsf {
                        T_SPLITR rft = brrbySplitfrbtor(splCiunk, splElfmfntIndfx, t);
                        splElfmfntIndfx += t;
                        rfturn rft;
                    }
                }
                flsf {
                    rfturn null;
                }
            }
        }
    }

    /**
     * An ordfrfd dollfdtion of {@dodf int} vblufs.
     */
    stbtid dlbss OfInt fxtfnds SpinfdBufffr.OfPrimitivf<Intfgfr, int[], IntConsumfr>
            implfmfnts IntConsumfr {
        OfInt() { }

        OfInt(int initiblCbpbdity) {
            supfr(initiblCbpbdity);
        }

        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr Intfgfr> donsumfr) {
            if (donsumfr instbndfof IntConsumfr) {
                forEbdi((IntConsumfr) donsumfr);
            }
            flsf {
                if (Tripwirf.ENABLED)
                    Tripwirf.trip(gftClbss(), "{0} dblling SpinfdBufffr.OfInt.forEbdi(Consumfr)");
                splitfrbtor().forEbdiRfmbining(donsumfr);
            }
        }

        @Ovfrridf
        protfdtfd int[][] nfwArrbyArrby(int sizf) {
            rfturn nfw int[sizf][];
        }

        @Ovfrridf
        publid int[] nfwArrby(int sizf) {
            rfturn nfw int[sizf];
        }

        @Ovfrridf
        protfdtfd int brrbyLfngti(int[] brrby) {
            rfturn brrby.lfngti;
        }

        @Ovfrridf
        protfdtfd void brrbyForEbdi(int[] brrby,
                                    int from, int to,
                                    IntConsumfr donsumfr) {
            for (int i = from; i < to; i++)
                donsumfr.bddfpt(brrby[i]);
        }

        @Ovfrridf
        publid void bddfpt(int i) {
            prfAddfpt();
            durCiunk[flfmfntIndfx++] = i;
        }

        publid int gft(long indfx) {
            // Cbsts to int brf sbff sindf tif spinf brrby indfx is tif indfx minus
            // tif prior flfmfnt dount from tif durrfnt spinf
            int di = diunkFor(indfx);
            if (spinfIndfx == 0 && di == 0)
                rfturn durCiunk[(int) indfx];
            flsf
                rfturn spinf[di][(int) (indfx - priorElfmfntCount[di])];
        }

        @Ovfrridf
        publid PrimitivfItfrbtor.OfInt itfrbtor() {
            rfturn Splitfrbtors.itfrbtor(splitfrbtor());
        }

        publid Splitfrbtor.OfInt splitfrbtor() {
            dlbss Splitr fxtfnds BbsfSplitfrbtor<Splitfrbtor.OfInt>
                    implfmfnts Splitfrbtor.OfInt {
                Splitr(int firstSpinfIndfx, int lbstSpinfIndfx,
                       int firstSpinfElfmfntIndfx, int lbstSpinfElfmfntFfndf) {
                    supfr(firstSpinfIndfx, lbstSpinfIndfx,
                          firstSpinfElfmfntIndfx, lbstSpinfElfmfntFfndf);
                }

                @Ovfrridf
                Splitr nfwSplitfrbtor(int firstSpinfIndfx, int lbstSpinfIndfx,
                                      int firstSpinfElfmfntIndfx, int lbstSpinfElfmfntFfndf) {
                    rfturn nfw Splitr(firstSpinfIndfx, lbstSpinfIndfx,
                                      firstSpinfElfmfntIndfx, lbstSpinfElfmfntFfndf);
                }

                @Ovfrridf
                void brrbyForOnf(int[] brrby, int indfx, IntConsumfr donsumfr) {
                    donsumfr.bddfpt(brrby[indfx]);
                }

                @Ovfrridf
                Splitfrbtor.OfInt brrbySplitfrbtor(int[] brrby, int offsft, int lfn) {
                    rfturn Arrbys.splitfrbtor(brrby, offsft, offsft+lfn);
                }
            }
            rfturn nfw Splitr(0, spinfIndfx, 0, flfmfntIndfx);
        }

        @Ovfrridf
        publid String toString() {
            int[] brrby = bsPrimitivfArrby();
            if (brrby.lfngti < 200) {
                rfturn String.formbt("%s[lfngti=%d, diunks=%d]%s",
                                     gftClbss().gftSimplfNbmf(), brrby.lfngti,
                                     spinfIndfx, Arrbys.toString(brrby));
            }
            flsf {
                int[] brrby2 = Arrbys.dopyOf(brrby, 200);
                rfturn String.formbt("%s[lfngti=%d, diunks=%d]%s...",
                                     gftClbss().gftSimplfNbmf(), brrby.lfngti,
                                     spinfIndfx, Arrbys.toString(brrby2));
            }
        }
    }

    /**
     * An ordfrfd dollfdtion of {@dodf long} vblufs.
     */
    stbtid dlbss OfLong fxtfnds SpinfdBufffr.OfPrimitivf<Long, long[], LongConsumfr>
            implfmfnts LongConsumfr {
        OfLong() { }

        OfLong(int initiblCbpbdity) {
            supfr(initiblCbpbdity);
        }

        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr Long> donsumfr) {
            if (donsumfr instbndfof LongConsumfr) {
                forEbdi((LongConsumfr) donsumfr);
            }
            flsf {
                if (Tripwirf.ENABLED)
                    Tripwirf.trip(gftClbss(), "{0} dblling SpinfdBufffr.OfLong.forEbdi(Consumfr)");
                splitfrbtor().forEbdiRfmbining(donsumfr);
            }
        }

        @Ovfrridf
        protfdtfd long[][] nfwArrbyArrby(int sizf) {
            rfturn nfw long[sizf][];
        }

        @Ovfrridf
        publid long[] nfwArrby(int sizf) {
            rfturn nfw long[sizf];
        }

        @Ovfrridf
        protfdtfd int brrbyLfngti(long[] brrby) {
            rfturn brrby.lfngti;
        }

        @Ovfrridf
        protfdtfd void brrbyForEbdi(long[] brrby,
                                    int from, int to,
                                    LongConsumfr donsumfr) {
            for (int i = from; i < to; i++)
                donsumfr.bddfpt(brrby[i]);
        }

        @Ovfrridf
        publid void bddfpt(long i) {
            prfAddfpt();
            durCiunk[flfmfntIndfx++] = i;
        }

        publid long gft(long indfx) {
            // Cbsts to int brf sbff sindf tif spinf brrby indfx is tif indfx minus
            // tif prior flfmfnt dount from tif durrfnt spinf
            int di = diunkFor(indfx);
            if (spinfIndfx == 0 && di == 0)
                rfturn durCiunk[(int) indfx];
            flsf
                rfturn spinf[di][(int) (indfx - priorElfmfntCount[di])];
        }

        @Ovfrridf
        publid PrimitivfItfrbtor.OfLong itfrbtor() {
            rfturn Splitfrbtors.itfrbtor(splitfrbtor());
        }


        publid Splitfrbtor.OfLong splitfrbtor() {
            dlbss Splitr fxtfnds BbsfSplitfrbtor<Splitfrbtor.OfLong>
                    implfmfnts Splitfrbtor.OfLong {
                Splitr(int firstSpinfIndfx, int lbstSpinfIndfx,
                       int firstSpinfElfmfntIndfx, int lbstSpinfElfmfntFfndf) {
                    supfr(firstSpinfIndfx, lbstSpinfIndfx,
                          firstSpinfElfmfntIndfx, lbstSpinfElfmfntFfndf);
                }

                @Ovfrridf
                Splitr nfwSplitfrbtor(int firstSpinfIndfx, int lbstSpinfIndfx,
                                      int firstSpinfElfmfntIndfx, int lbstSpinfElfmfntFfndf) {
                    rfturn nfw Splitr(firstSpinfIndfx, lbstSpinfIndfx,
                                      firstSpinfElfmfntIndfx, lbstSpinfElfmfntFfndf);
                }

                @Ovfrridf
                void brrbyForOnf(long[] brrby, int indfx, LongConsumfr donsumfr) {
                    donsumfr.bddfpt(brrby[indfx]);
                }

                @Ovfrridf
                Splitfrbtor.OfLong brrbySplitfrbtor(long[] brrby, int offsft, int lfn) {
                    rfturn Arrbys.splitfrbtor(brrby, offsft, offsft+lfn);
                }
            }
            rfturn nfw Splitr(0, spinfIndfx, 0, flfmfntIndfx);
        }

        @Ovfrridf
        publid String toString() {
            long[] brrby = bsPrimitivfArrby();
            if (brrby.lfngti < 200) {
                rfturn String.formbt("%s[lfngti=%d, diunks=%d]%s",
                                     gftClbss().gftSimplfNbmf(), brrby.lfngti,
                                     spinfIndfx, Arrbys.toString(brrby));
            }
            flsf {
                long[] brrby2 = Arrbys.dopyOf(brrby, 200);
                rfturn String.formbt("%s[lfngti=%d, diunks=%d]%s...",
                                     gftClbss().gftSimplfNbmf(), brrby.lfngti,
                                     spinfIndfx, Arrbys.toString(brrby2));
            }
        }
    }

    /**
     * An ordfrfd dollfdtion of {@dodf doublf} vblufs.
     */
    stbtid dlbss OfDoublf
            fxtfnds SpinfdBufffr.OfPrimitivf<Doublf, doublf[], DoublfConsumfr>
            implfmfnts DoublfConsumfr {
        OfDoublf() { }

        OfDoublf(int initiblCbpbdity) {
            supfr(initiblCbpbdity);
        }

        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr Doublf> donsumfr) {
            if (donsumfr instbndfof DoublfConsumfr) {
                forEbdi((DoublfConsumfr) donsumfr);
            }
            flsf {
                if (Tripwirf.ENABLED)
                    Tripwirf.trip(gftClbss(), "{0} dblling SpinfdBufffr.OfDoublf.forEbdi(Consumfr)");
                splitfrbtor().forEbdiRfmbining(donsumfr);
            }
        }

        @Ovfrridf
        protfdtfd doublf[][] nfwArrbyArrby(int sizf) {
            rfturn nfw doublf[sizf][];
        }

        @Ovfrridf
        publid doublf[] nfwArrby(int sizf) {
            rfturn nfw doublf[sizf];
        }

        @Ovfrridf
        protfdtfd int brrbyLfngti(doublf[] brrby) {
            rfturn brrby.lfngti;
        }

        @Ovfrridf
        protfdtfd void brrbyForEbdi(doublf[] brrby,
                                    int from, int to,
                                    DoublfConsumfr donsumfr) {
            for (int i = from; i < to; i++)
                donsumfr.bddfpt(brrby[i]);
        }

        @Ovfrridf
        publid void bddfpt(doublf i) {
            prfAddfpt();
            durCiunk[flfmfntIndfx++] = i;
        }

        publid doublf gft(long indfx) {
            // Cbsts to int brf sbff sindf tif spinf brrby indfx is tif indfx minus
            // tif prior flfmfnt dount from tif durrfnt spinf
            int di = diunkFor(indfx);
            if (spinfIndfx == 0 && di == 0)
                rfturn durCiunk[(int) indfx];
            flsf
                rfturn spinf[di][(int) (indfx - priorElfmfntCount[di])];
        }

        @Ovfrridf
        publid PrimitivfItfrbtor.OfDoublf itfrbtor() {
            rfturn Splitfrbtors.itfrbtor(splitfrbtor());
        }

        publid Splitfrbtor.OfDoublf splitfrbtor() {
            dlbss Splitr fxtfnds BbsfSplitfrbtor<Splitfrbtor.OfDoublf>
                    implfmfnts Splitfrbtor.OfDoublf {
                Splitr(int firstSpinfIndfx, int lbstSpinfIndfx,
                       int firstSpinfElfmfntIndfx, int lbstSpinfElfmfntFfndf) {
                    supfr(firstSpinfIndfx, lbstSpinfIndfx,
                          firstSpinfElfmfntIndfx, lbstSpinfElfmfntFfndf);
                }

                @Ovfrridf
                Splitr nfwSplitfrbtor(int firstSpinfIndfx, int lbstSpinfIndfx,
                                      int firstSpinfElfmfntIndfx, int lbstSpinfElfmfntFfndf) {
                    rfturn nfw Splitr(firstSpinfIndfx, lbstSpinfIndfx,
                                      firstSpinfElfmfntIndfx, lbstSpinfElfmfntFfndf);
                }

                @Ovfrridf
                void brrbyForOnf(doublf[] brrby, int indfx, DoublfConsumfr donsumfr) {
                    donsumfr.bddfpt(brrby[indfx]);
                }

                @Ovfrridf
                Splitfrbtor.OfDoublf brrbySplitfrbtor(doublf[] brrby, int offsft, int lfn) {
                    rfturn Arrbys.splitfrbtor(brrby, offsft, offsft+lfn);
                }
            }
            rfturn nfw Splitr(0, spinfIndfx, 0, flfmfntIndfx);
        }

        @Ovfrridf
        publid String toString() {
            doublf[] brrby = bsPrimitivfArrby();
            if (brrby.lfngti < 200) {
                rfturn String.formbt("%s[lfngti=%d, diunks=%d]%s",
                                     gftClbss().gftSimplfNbmf(), brrby.lfngti,
                                     spinfIndfx, Arrbys.toString(brrby));
            }
            flsf {
                doublf[] brrby2 = Arrbys.dopyOf(brrby, 200);
                rfturn String.formbt("%s[lfngti=%d, diunks=%d]%s...",
                                     gftClbss().gftSimplfNbmf(), brrby.lfngti,
                                     spinfIndfx, Arrbys.toString(brrby2));
            }
        }
    }
}

