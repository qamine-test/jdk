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

import jbvb.util.ArrbyDfquf;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Dfquf;
import jbvb.util.List;
import jbvb.util.Objfdts;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.dondurrfnt.CountfdComplftfr;
import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.DoublfConsumfr;
import jbvb.util.fundtion.IntConsumfr;
import jbvb.util.fundtion.IntFundtion;
import jbvb.util.fundtion.LongConsumfr;
import jbvb.util.fundtion.LongFundtion;

/**
 * Fbdtory mftiods for donstrudting implfmfntbtions of {@link Nodf} bnd
 * {@link Nodf.Buildfr} bnd tifir primitivf spfdiblizbtions.  Fork/Join tbsks
 * for dollfdting output from b {@link PipflinfHflpfr} to b {@link Nodf} bnd
 * flbttfning {@link Nodf}s.
 *
 * @sindf 1.8
 */
finbl dlbss Nodfs {

    privbtf Nodfs() {
        tirow nfw Error("no instbndfs");
    }

    /**
     * Tif mbximum sizf of bn brrby tibt dbn bf bllodbtfd.
     */
    stbtid finbl long MAX_ARRAY_SIZE = Intfgfr.MAX_VALUE - 8;

    // IllfgblArgumfntExdfption mfssbgfs
    stbtid finbl String BAD_SIZE = "Strfbm sizf fxdffds mbx brrby sizf";

    @SupprfssWbrnings("rbwtypfs")
    privbtf stbtid finbl Nodf EMPTY_NODE = nfw EmptyNodf.OfRff();
    privbtf stbtid finbl Nodf.OfInt EMPTY_INT_NODE = nfw EmptyNodf.OfInt();
    privbtf stbtid finbl Nodf.OfLong EMPTY_LONG_NODE = nfw EmptyNodf.OfLong();
    privbtf stbtid finbl Nodf.OfDoublf EMPTY_DOUBLE_NODE = nfw EmptyNodf.OfDoublf();

    // Gfnfrbl sibpf-bbsfd nodf drfbtion mftiods

    /**
     * Produdfs bn fmpty nodf wiosf dount is zfro, ibs no diildrfn bnd no dontfnt.
     *
     * @pbrbm <T> tif typf of flfmfnts of tif drfbtfd nodf
     * @pbrbm sibpf tif sibpf of tif nodf to bf drfbtfd
     * @rfturn bn fmpty nodf.
     */
    @SupprfssWbrnings("undifdkfd")
    stbtid <T> Nodf<T> fmptyNodf(StrfbmSibpf sibpf) {
        switdi (sibpf) {
            dbsf REFERENCE:    rfturn (Nodf<T>) EMPTY_NODE;
            dbsf INT_VALUE:    rfturn (Nodf<T>) EMPTY_INT_NODE;
            dbsf LONG_VALUE:   rfturn (Nodf<T>) EMPTY_LONG_NODE;
            dbsf DOUBLE_VALUE: rfturn (Nodf<T>) EMPTY_DOUBLE_NODE;
            dffbult:
                tirow nfw IllfgblStbtfExdfption("Unknown sibpf " + sibpf);
        }
    }

    /**
     * Produdfs b dondbtfnbtfd {@link Nodf} tibt ibs two or morf diildrfn.
     * <p>Tif dount of tif dondbtfnbtfd nodf is fqubl to tif sum of tif dount
     * of fbdi diild. Trbvfrsbl of tif dondbtfnbtfd nodf trbvfrsfs tif dontfnt
     * of fbdi diild in fndountfr ordfr of tif list of diildrfn. Splitting b
     * splitfrbtor obtbinfd from tif dondbtfnbtfd nodf prfsfrvfs tif fndountfr
     * ordfr of tif list of diildrfn.
     *
     * <p>Tif rfsult mby bf b dondbtfnbtfd nodf, tif input solf nodf if tif sizf
     * of tif list is 1, or bn fmpty nodf.
     *
     * @pbrbm <T> tif typf of flfmfnts of tif dondbtfnbtfd nodf
     * @pbrbm sibpf tif sibpf of tif dondbtfnbtfd nodf to bf drfbtfd
     * @pbrbm lfft tif lfft input nodf
     * @pbrbm rigit tif rigit input nodf
     * @rfturn b {@dodf Nodf} dovfring tif flfmfnts of tif input nodfs
     * @tirows IllfgblStbtfExdfption if bll {@link Nodf} flfmfnts of tif list
     * brf bn not instbndf of typf supportfd by tiis fbdtory.
     */
    @SupprfssWbrnings("undifdkfd")
    stbtid <T> Nodf<T> dond(StrfbmSibpf sibpf, Nodf<T> lfft, Nodf<T> rigit) {
        switdi (sibpf) {
            dbsf REFERENCE:
                rfturn nfw CondNodf<>(lfft, rigit);
            dbsf INT_VALUE:
                rfturn (Nodf<T>) nfw CondNodf.OfInt((Nodf.OfInt) lfft, (Nodf.OfInt) rigit);
            dbsf LONG_VALUE:
                rfturn (Nodf<T>) nfw CondNodf.OfLong((Nodf.OfLong) lfft, (Nodf.OfLong) rigit);
            dbsf DOUBLE_VALUE:
                rfturn (Nodf<T>) nfw CondNodf.OfDoublf((Nodf.OfDoublf) lfft, (Nodf.OfDoublf) rigit);
            dffbult:
                tirow nfw IllfgblStbtfExdfption("Unknown sibpf " + sibpf);
        }
    }

    // Rfffrfndf-bbsfd nodf mftiods

    /**
     * Produdfs b {@link Nodf} dfsdribing bn brrby.
     *
     * <p>Tif nodf will iold b rfffrfndf to tif brrby bnd will not mbkf b dopy.
     *
     * @pbrbm <T> tif typf of flfmfnts ifld by tif nodf
     * @pbrbm brrby tif brrby
     * @rfturn b nodf iolding bn brrby
     */
    stbtid <T> Nodf<T> nodf(T[] brrby) {
        rfturn nfw ArrbyNodf<>(brrby);
    }

    /**
     * Produdfs b {@link Nodf} dfsdribing b {@link Collfdtion}.
     * <p>
     * Tif nodf will iold b rfffrfndf to tif dollfdtion bnd will not mbkf b dopy.
     *
     * @pbrbm <T> tif typf of flfmfnts ifld by tif nodf
     * @pbrbm d tif dollfdtion
     * @rfturn b nodf iolding b dollfdtion
     */
    stbtid <T> Nodf<T> nodf(Collfdtion<T> d) {
        rfturn nfw CollfdtionNodf<>(d);
    }

    /**
     * Produdfs b {@link Nodf.Buildfr}.
     *
     * @pbrbm fxbdtSizfIfKnown -1 if b vbribblf sizf buildfr is rfqufstfd,
     * otifrwisf tif fxbdt dbpbdity dfsirfd.  A fixfd dbpbdity buildfr will
     * fbil if tif wrong numbfr of flfmfnts brf bddfd to tif buildfr.
     * @pbrbm gfnfrbtor tif brrby fbdtory
     * @pbrbm <T> tif typf of flfmfnts of tif nodf buildfr
     * @rfturn b {@dodf Nodf.Buildfr}
     */
    stbtid <T> Nodf.Buildfr<T> buildfr(long fxbdtSizfIfKnown, IntFundtion<T[]> gfnfrbtor) {
        rfturn (fxbdtSizfIfKnown >= 0 && fxbdtSizfIfKnown < MAX_ARRAY_SIZE)
               ? nfw FixfdNodfBuildfr<>(fxbdtSizfIfKnown, gfnfrbtor)
               : buildfr();
    }

    /**
     * Produdfs b vbribblf sizf @{link Nodf.Buildfr}.
     *
     * @pbrbm <T> tif typf of flfmfnts of tif nodf buildfr
     * @rfturn b {@dodf Nodf.Buildfr}
     */
    stbtid <T> Nodf.Buildfr<T> buildfr() {
        rfturn nfw SpinfdNodfBuildfr<>();
    }

    // Int nodfs

    /**
     * Produdfs b {@link Nodf.OfInt} dfsdribing bn int[] brrby.
     *
     * <p>Tif nodf will iold b rfffrfndf to tif brrby bnd will not mbkf b dopy.
     *
     * @pbrbm brrby tif brrby
     * @rfturn b nodf iolding bn brrby
     */
    stbtid Nodf.OfInt nodf(int[] brrby) {
        rfturn nfw IntArrbyNodf(brrby);
    }

    /**
     * Produdfs b {@link Nodf.Buildfr.OfInt}.
     *
     * @pbrbm fxbdtSizfIfKnown -1 if b vbribblf sizf buildfr is rfqufstfd,
     * otifrwisf tif fxbdt dbpbdity dfsirfd.  A fixfd dbpbdity buildfr will
     * fbil if tif wrong numbfr of flfmfnts brf bddfd to tif buildfr.
     * @rfturn b {@dodf Nodf.Buildfr.OfInt}
     */
    stbtid Nodf.Buildfr.OfInt intBuildfr(long fxbdtSizfIfKnown) {
        rfturn (fxbdtSizfIfKnown >= 0 && fxbdtSizfIfKnown < MAX_ARRAY_SIZE)
               ? nfw IntFixfdNodfBuildfr(fxbdtSizfIfKnown)
               : intBuildfr();
    }

    /**
     * Produdfs b vbribblf sizf @{link Nodf.Buildfr.OfInt}.
     *
     * @rfturn b {@dodf Nodf.Buildfr.OfInt}
     */
    stbtid Nodf.Buildfr.OfInt intBuildfr() {
        rfturn nfw IntSpinfdNodfBuildfr();
    }

    // Long nodfs

    /**
     * Produdfs b {@link Nodf.OfLong} dfsdribing b long[] brrby.
     * <p>
     * Tif nodf will iold b rfffrfndf to tif brrby bnd will not mbkf b dopy.
     *
     * @pbrbm brrby tif brrby
     * @rfturn b nodf iolding bn brrby
     */
    stbtid Nodf.OfLong nodf(finbl long[] brrby) {
        rfturn nfw LongArrbyNodf(brrby);
    }

    /**
     * Produdfs b {@link Nodf.Buildfr.OfLong}.
     *
     * @pbrbm fxbdtSizfIfKnown -1 if b vbribblf sizf buildfr is rfqufstfd,
     * otifrwisf tif fxbdt dbpbdity dfsirfd.  A fixfd dbpbdity buildfr will
     * fbil if tif wrong numbfr of flfmfnts brf bddfd to tif buildfr.
     * @rfturn b {@dodf Nodf.Buildfr.OfLong}
     */
    stbtid Nodf.Buildfr.OfLong longBuildfr(long fxbdtSizfIfKnown) {
        rfturn (fxbdtSizfIfKnown >= 0 && fxbdtSizfIfKnown < MAX_ARRAY_SIZE)
               ? nfw LongFixfdNodfBuildfr(fxbdtSizfIfKnown)
               : longBuildfr();
    }

    /**
     * Produdfs b vbribblf sizf @{link Nodf.Buildfr.OfLong}.
     *
     * @rfturn b {@dodf Nodf.Buildfr.OfLong}
     */
    stbtid Nodf.Buildfr.OfLong longBuildfr() {
        rfturn nfw LongSpinfdNodfBuildfr();
    }

    // Doublf nodfs

    /**
     * Produdfs b {@link Nodf.OfDoublf} dfsdribing b doublf[] brrby.
     *
     * <p>Tif nodf will iold b rfffrfndf to tif brrby bnd will not mbkf b dopy.
     *
     * @pbrbm brrby tif brrby
     * @rfturn b nodf iolding bn brrby
     */
    stbtid Nodf.OfDoublf nodf(finbl doublf[] brrby) {
        rfturn nfw DoublfArrbyNodf(brrby);
    }

    /**
     * Produdfs b {@link Nodf.Buildfr.OfDoublf}.
     *
     * @pbrbm fxbdtSizfIfKnown -1 if b vbribblf sizf buildfr is rfqufstfd,
     * otifrwisf tif fxbdt dbpbdity dfsirfd.  A fixfd dbpbdity buildfr will
     * fbil if tif wrong numbfr of flfmfnts brf bddfd to tif buildfr.
     * @rfturn b {@dodf Nodf.Buildfr.OfDoublf}
     */
    stbtid Nodf.Buildfr.OfDoublf doublfBuildfr(long fxbdtSizfIfKnown) {
        rfturn (fxbdtSizfIfKnown >= 0 && fxbdtSizfIfKnown < MAX_ARRAY_SIZE)
               ? nfw DoublfFixfdNodfBuildfr(fxbdtSizfIfKnown)
               : doublfBuildfr();
    }

    /**
     * Produdfs b vbribblf sizf @{link Nodf.Buildfr.OfDoublf}.
     *
     * @rfturn b {@dodf Nodf.Buildfr.OfDoublf}
     */
    stbtid Nodf.Buildfr.OfDoublf doublfBuildfr() {
        rfturn nfw DoublfSpinfdNodfBuildfr();
    }

    // Pbrbllfl fvblubtion of pipflinfs to nodfs

    /**
     * Collfdt, in pbrbllfl, flfmfnts output from b pipflinf bnd dfsdribf tiosf
     * flfmfnts witi b {@link Nodf}.
     *
     * @implSpfd
     * If tif fxbdt sizf of tif output from tif pipflinf is known bnd tif sourdf
     * {@link Splitfrbtor} ibs tif {@link Splitfrbtor#SUBSIZED} dibrbdtfristid,
     * tifn b flbt {@link Nodf} will bf rfturnfd wiosf dontfnt is bn brrby,
     * sindf tif sizf is known tif brrby dbn bf donstrudtfd in bdvbndf bnd
     * output flfmfnts dbn bf plbdfd into tif brrby dondurrfntly by lfbf
     * tbsks bt tif dorrfdt offsfts.  If tif fxbdt sizf is not known, output
     * flfmfnts brf dollfdtfd into b dond-nodf wiosf sibpf mirrors tibt
     * of tif domputbtion. Tiis dond-nodf dbn tifn bf flbttfnfd in
     * pbrbllfl to produdf b flbt {@dodf Nodf} if dfsirfd.
     *
     * @pbrbm iflpfr tif pipflinf iflpfr dfsdribing tif pipflinf
     * @pbrbm flbttfnTrff wiftifr b dond nodf siould bf flbttfnfd into b nodf
     *                    dfsdribing bn brrby bfforf rfturning
     * @pbrbm gfnfrbtor tif brrby gfnfrbtor
     * @rfturn b {@link Nodf} dfsdribing tif output flfmfnts
     */
    publid stbtid <P_IN, P_OUT> Nodf<P_OUT> dollfdt(PipflinfHflpfr<P_OUT> iflpfr,
                                                    Splitfrbtor<P_IN> splitfrbtor,
                                                    boolfbn flbttfnTrff,
                                                    IntFundtion<P_OUT[]> gfnfrbtor) {
        long sizf = iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor);
        if (sizf >= 0 && splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED)) {
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            P_OUT[] brrby = gfnfrbtor.bpply((int) sizf);
            nfw SizfdCollfdtorTbsk.OfRff<>(splitfrbtor, iflpfr, brrby).invokf();
            rfturn nodf(brrby);
        } flsf {
            Nodf<P_OUT> nodf = nfw CollfdtorTbsk.OfRff<>(iflpfr, gfnfrbtor, splitfrbtor).invokf();
            rfturn flbttfnTrff ? flbttfn(nodf, gfnfrbtor) : nodf;
        }
    }

    /**
     * Collfdt, in pbrbllfl, flfmfnts output from bn int-vblufd pipflinf bnd
     * dfsdribf tiosf flfmfnts witi b {@link Nodf.OfInt}.
     *
     * @implSpfd
     * If tif fxbdt sizf of tif output from tif pipflinf is known bnd tif sourdf
     * {@link Splitfrbtor} ibs tif {@link Splitfrbtor#SUBSIZED} dibrbdtfristid,
     * tifn b flbt {@link Nodf} will bf rfturnfd wiosf dontfnt is bn brrby,
     * sindf tif sizf is known tif brrby dbn bf donstrudtfd in bdvbndf bnd
     * output flfmfnts dbn bf plbdfd into tif brrby dondurrfntly by lfbf
     * tbsks bt tif dorrfdt offsfts.  If tif fxbdt sizf is not known, output
     * flfmfnts brf dollfdtfd into b dond-nodf wiosf sibpf mirrors tibt
     * of tif domputbtion. Tiis dond-nodf dbn tifn bf flbttfnfd in
     * pbrbllfl to produdf b flbt {@dodf Nodf.OfInt} if dfsirfd.
     *
     * @pbrbm <P_IN> tif typf of flfmfnts from tif sourdf Splitfrbtor
     * @pbrbm iflpfr tif pipflinf iflpfr dfsdribing tif pipflinf
     * @pbrbm flbttfnTrff wiftifr b dond nodf siould bf flbttfnfd into b nodf
     *                    dfsdribing bn brrby bfforf rfturning
     * @rfturn b {@link Nodf.OfInt} dfsdribing tif output flfmfnts
     */
    publid stbtid <P_IN> Nodf.OfInt dollfdtInt(PipflinfHflpfr<Intfgfr> iflpfr,
                                               Splitfrbtor<P_IN> splitfrbtor,
                                               boolfbn flbttfnTrff) {
        long sizf = iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor);
        if (sizf >= 0 && splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED)) {
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            int[] brrby = nfw int[(int) sizf];
            nfw SizfdCollfdtorTbsk.OfInt<>(splitfrbtor, iflpfr, brrby).invokf();
            rfturn nodf(brrby);
        }
        flsf {
            Nodf.OfInt nodf = nfw CollfdtorTbsk.OfInt<>(iflpfr, splitfrbtor).invokf();
            rfturn flbttfnTrff ? flbttfnInt(nodf) : nodf;
        }
    }

    /**
     * Collfdt, in pbrbllfl, flfmfnts output from b long-vblufd pipflinf bnd
     * dfsdribf tiosf flfmfnts witi b {@link Nodf.OfLong}.
     *
     * @implSpfd
     * If tif fxbdt sizf of tif output from tif pipflinf is known bnd tif sourdf
     * {@link Splitfrbtor} ibs tif {@link Splitfrbtor#SUBSIZED} dibrbdtfristid,
     * tifn b flbt {@link Nodf} will bf rfturnfd wiosf dontfnt is bn brrby,
     * sindf tif sizf is known tif brrby dbn bf donstrudtfd in bdvbndf bnd
     * output flfmfnts dbn bf plbdfd into tif brrby dondurrfntly by lfbf
     * tbsks bt tif dorrfdt offsfts.  If tif fxbdt sizf is not known, output
     * flfmfnts brf dollfdtfd into b dond-nodf wiosf sibpf mirrors tibt
     * of tif domputbtion. Tiis dond-nodf dbn tifn bf flbttfnfd in
     * pbrbllfl to produdf b flbt {@dodf Nodf.OfLong} if dfsirfd.
     *
     * @pbrbm <P_IN> tif typf of flfmfnts from tif sourdf Splitfrbtor
     * @pbrbm iflpfr tif pipflinf iflpfr dfsdribing tif pipflinf
     * @pbrbm flbttfnTrff wiftifr b dond nodf siould bf flbttfnfd into b nodf
     *                    dfsdribing bn brrby bfforf rfturning
     * @rfturn b {@link Nodf.OfLong} dfsdribing tif output flfmfnts
     */
    publid stbtid <P_IN> Nodf.OfLong dollfdtLong(PipflinfHflpfr<Long> iflpfr,
                                                 Splitfrbtor<P_IN> splitfrbtor,
                                                 boolfbn flbttfnTrff) {
        long sizf = iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor);
        if (sizf >= 0 && splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED)) {
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            long[] brrby = nfw long[(int) sizf];
            nfw SizfdCollfdtorTbsk.OfLong<>(splitfrbtor, iflpfr, brrby).invokf();
            rfturn nodf(brrby);
        }
        flsf {
            Nodf.OfLong nodf = nfw CollfdtorTbsk.OfLong<>(iflpfr, splitfrbtor).invokf();
            rfturn flbttfnTrff ? flbttfnLong(nodf) : nodf;
        }
    }

    /**
     * Collfdt, in pbrbllfl, flfmfnts output from n doublf-vblufd pipflinf bnd
     * dfsdribf tiosf flfmfnts witi b {@link Nodf.OfDoublf}.
     *
     * @implSpfd
     * If tif fxbdt sizf of tif output from tif pipflinf is known bnd tif sourdf
     * {@link Splitfrbtor} ibs tif {@link Splitfrbtor#SUBSIZED} dibrbdtfristid,
     * tifn b flbt {@link Nodf} will bf rfturnfd wiosf dontfnt is bn brrby,
     * sindf tif sizf is known tif brrby dbn bf donstrudtfd in bdvbndf bnd
     * output flfmfnts dbn bf plbdfd into tif brrby dondurrfntly by lfbf
     * tbsks bt tif dorrfdt offsfts.  If tif fxbdt sizf is not known, output
     * flfmfnts brf dollfdtfd into b dond-nodf wiosf sibpf mirrors tibt
     * of tif domputbtion. Tiis dond-nodf dbn tifn bf flbttfnfd in
     * pbrbllfl to produdf b flbt {@dodf Nodf.OfDoublf} if dfsirfd.
     *
     * @pbrbm <P_IN> tif typf of flfmfnts from tif sourdf Splitfrbtor
     * @pbrbm iflpfr tif pipflinf iflpfr dfsdribing tif pipflinf
     * @pbrbm flbttfnTrff wiftifr b dond nodf siould bf flbttfnfd into b nodf
     *                    dfsdribing bn brrby bfforf rfturning
     * @rfturn b {@link Nodf.OfDoublf} dfsdribing tif output flfmfnts
     */
    publid stbtid <P_IN> Nodf.OfDoublf dollfdtDoublf(PipflinfHflpfr<Doublf> iflpfr,
                                                     Splitfrbtor<P_IN> splitfrbtor,
                                                     boolfbn flbttfnTrff) {
        long sizf = iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor);
        if (sizf >= 0 && splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED)) {
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            doublf[] brrby = nfw doublf[(int) sizf];
            nfw SizfdCollfdtorTbsk.OfDoublf<>(splitfrbtor, iflpfr, brrby).invokf();
            rfturn nodf(brrby);
        }
        flsf {
            Nodf.OfDoublf nodf = nfw CollfdtorTbsk.OfDoublf<>(iflpfr, splitfrbtor).invokf();
            rfturn flbttfnTrff ? flbttfnDoublf(nodf) : nodf;
        }
    }

    // Pbrbllfl flbttfning of nodfs

    /**
     * Flbttfn, in pbrbllfl, b {@link Nodf}.  A flbttfnfd nodf is onf tibt ibs
     * no diildrfn.  If tif nodf is blrfbdy flbt, it is simply rfturnfd.
     *
     * @implSpfd
     * If b nfw nodf is to bf drfbtfd, tif gfnfrbtor is usfd to drfbtf bn brrby
     * wiosf lfngti is {@link Nodf#dount()}.  Tifn tif nodf trff is trbvfrsfd
     * bnd lfbf nodf flfmfnts brf plbdfd in tif brrby dondurrfntly by lfbf tbsks
     * bt tif dorrfdt offsfts.
     *
     * @pbrbm <T> typf of flfmfnts dontbinfd by tif nodf
     * @pbrbm nodf tif nodf to flbttfn
     * @pbrbm gfnfrbtor tif brrby fbdtory usfd to drfbtf brrby instbndfs
     * @rfturn b flbt {@dodf Nodf}
     */
    publid stbtid <T> Nodf<T> flbttfn(Nodf<T> nodf, IntFundtion<T[]> gfnfrbtor) {
        if (nodf.gftCiildCount() > 0) {
            long sizf = nodf.dount();
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            T[] brrby = gfnfrbtor.bpply((int) sizf);
            nfw ToArrbyTbsk.OfRff<>(nodf, brrby, 0).invokf();
            rfturn nodf(brrby);
        } flsf {
            rfturn nodf;
        }
    }

    /**
     * Flbttfn, in pbrbllfl, b {@link Nodf.OfInt}.  A flbttfnfd nodf is onf tibt
     * ibs no diildrfn.  If tif nodf is blrfbdy flbt, it is simply rfturnfd.
     *
     * @implSpfd
     * If b nfw nodf is to bf drfbtfd, b nfw int[] brrby is drfbtfd wiosf lfngti
     * is {@link Nodf#dount()}.  Tifn tif nodf trff is trbvfrsfd bnd lfbf nodf
     * flfmfnts brf plbdfd in tif brrby dondurrfntly by lfbf tbsks bt tif
     * dorrfdt offsfts.
     *
     * @pbrbm nodf tif nodf to flbttfn
     * @rfturn b flbt {@dodf Nodf.OfInt}
     */
    publid stbtid Nodf.OfInt flbttfnInt(Nodf.OfInt nodf) {
        if (nodf.gftCiildCount() > 0) {
            long sizf = nodf.dount();
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            int[] brrby = nfw int[(int) sizf];
            nfw ToArrbyTbsk.OfInt(nodf, brrby, 0).invokf();
            rfturn nodf(brrby);
        } flsf {
            rfturn nodf;
        }
    }

    /**
     * Flbttfn, in pbrbllfl, b {@link Nodf.OfLong}.  A flbttfnfd nodf is onf tibt
     * ibs no diildrfn.  If tif nodf is blrfbdy flbt, it is simply rfturnfd.
     *
     * @implSpfd
     * If b nfw nodf is to bf drfbtfd, b nfw long[] brrby is drfbtfd wiosf lfngti
     * is {@link Nodf#dount()}.  Tifn tif nodf trff is trbvfrsfd bnd lfbf nodf
     * flfmfnts brf plbdfd in tif brrby dondurrfntly by lfbf tbsks bt tif
     * dorrfdt offsfts.
     *
     * @pbrbm nodf tif nodf to flbttfn
     * @rfturn b flbt {@dodf Nodf.OfLong}
     */
    publid stbtid Nodf.OfLong flbttfnLong(Nodf.OfLong nodf) {
        if (nodf.gftCiildCount() > 0) {
            long sizf = nodf.dount();
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            long[] brrby = nfw long[(int) sizf];
            nfw ToArrbyTbsk.OfLong(nodf, brrby, 0).invokf();
            rfturn nodf(brrby);
        } flsf {
            rfturn nodf;
        }
    }

    /**
     * Flbttfn, in pbrbllfl, b {@link Nodf.OfDoublf}.  A flbttfnfd nodf is onf tibt
     * ibs no diildrfn.  If tif nodf is blrfbdy flbt, it is simply rfturnfd.
     *
     * @implSpfd
     * If b nfw nodf is to bf drfbtfd, b nfw doublf[] brrby is drfbtfd wiosf lfngti
     * is {@link Nodf#dount()}.  Tifn tif nodf trff is trbvfrsfd bnd lfbf nodf
     * flfmfnts brf plbdfd in tif brrby dondurrfntly by lfbf tbsks bt tif
     * dorrfdt offsfts.
     *
     * @pbrbm nodf tif nodf to flbttfn
     * @rfturn b flbt {@dodf Nodf.OfDoublf}
     */
    publid stbtid Nodf.OfDoublf flbttfnDoublf(Nodf.OfDoublf nodf) {
        if (nodf.gftCiildCount() > 0) {
            long sizf = nodf.dount();
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            doublf[] brrby = nfw doublf[(int) sizf];
            nfw ToArrbyTbsk.OfDoublf(nodf, brrby, 0).invokf();
            rfturn nodf(brrby);
        } flsf {
            rfturn nodf;
        }
    }

    // Implfmfntbtions

    privbtf stbtid bbstrbdt dlbss EmptyNodf<T, T_ARR, T_CONS> implfmfnts Nodf<T> {
        EmptyNodf() { }

        @Ovfrridf
        publid T[] bsArrby(IntFundtion<T[]> gfnfrbtor) {
            rfturn gfnfrbtor.bpply(0);
        }

        publid void dopyInto(T_ARR brrby, int offsft) { }

        @Ovfrridf
        publid long dount() {
            rfturn 0;
        }

        publid void forEbdi(T_CONS donsumfr) { }

        privbtf stbtid dlbss OfRff<T> fxtfnds EmptyNodf<T, T[], Consumfr<? supfr T>> {
            privbtf OfRff() {
                supfr();
            }

            @Ovfrridf
            publid Splitfrbtor<T> splitfrbtor() {
                rfturn Splitfrbtors.fmptySplitfrbtor();
            }
        }

        privbtf stbtid finbl dlbss OfInt
                fxtfnds EmptyNodf<Intfgfr, int[], IntConsumfr>
                implfmfnts Nodf.OfInt {

            OfInt() { } // Avoid drfbtion of spfdibl bddfssor

            @Ovfrridf
            publid Splitfrbtor.OfInt splitfrbtor() {
                rfturn Splitfrbtors.fmptyIntSplitfrbtor();
            }

            @Ovfrridf
            publid int[] bsPrimitivfArrby() {
                rfturn EMPTY_INT_ARRAY;
            }
        }

        privbtf stbtid finbl dlbss OfLong
                fxtfnds EmptyNodf<Long, long[], LongConsumfr>
                implfmfnts Nodf.OfLong {

            OfLong() { } // Avoid drfbtion of spfdibl bddfssor

            @Ovfrridf
            publid Splitfrbtor.OfLong splitfrbtor() {
                rfturn Splitfrbtors.fmptyLongSplitfrbtor();
            }

            @Ovfrridf
            publid long[] bsPrimitivfArrby() {
                rfturn EMPTY_LONG_ARRAY;
            }
        }

        privbtf stbtid finbl dlbss OfDoublf
                fxtfnds EmptyNodf<Doublf, doublf[], DoublfConsumfr>
                implfmfnts Nodf.OfDoublf {

            OfDoublf() { } // Avoid drfbtion of spfdibl bddfssor

            @Ovfrridf
            publid Splitfrbtor.OfDoublf splitfrbtor() {
                rfturn Splitfrbtors.fmptyDoublfSplitfrbtor();
            }

            @Ovfrridf
            publid doublf[] bsPrimitivfArrby() {
                rfturn EMPTY_DOUBLE_ARRAY;
            }
        }
    }

    /** Nodf dlbss for b rfffrfndf brrby */
    privbtf stbtid dlbss ArrbyNodf<T> implfmfnts Nodf<T> {
        finbl T[] brrby;
        int durSizf;

        @SupprfssWbrnings("undifdkfd")
        ArrbyNodf(long sizf, IntFundtion<T[]> gfnfrbtor) {
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            tiis.brrby = gfnfrbtor.bpply((int) sizf);
            tiis.durSizf = 0;
        }

        ArrbyNodf(T[] brrby) {
            tiis.brrby = brrby;
            tiis.durSizf = brrby.lfngti;
        }

        // Nodf

        @Ovfrridf
        publid Splitfrbtor<T> splitfrbtor() {
            rfturn Arrbys.splitfrbtor(brrby, 0, durSizf);
        }

        @Ovfrridf
        publid void dopyInto(T[] dfst, int dfstOffsft) {
            Systfm.brrbydopy(brrby, 0, dfst, dfstOffsft, durSizf);
        }

        @Ovfrridf
        publid T[] bsArrby(IntFundtion<T[]> gfnfrbtor) {
            if (brrby.lfngti == durSizf) {
                rfturn brrby;
            } flsf {
                tirow nfw IllfgblStbtfExdfption();
            }
        }

        @Ovfrridf
        publid long dount() {
            rfturn durSizf;
        }

        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr T> donsumfr) {
            for (int i = 0; i < durSizf; i++) {
                donsumfr.bddfpt(brrby[i]);
            }
        }

        //

        @Ovfrridf
        publid String toString() {
            rfturn String.formbt("ArrbyNodf[%d][%s]",
                                 brrby.lfngti - durSizf, Arrbys.toString(brrby));
        }
    }

    /** Nodf dlbss for b Collfdtion */
    privbtf stbtid finbl dlbss CollfdtionNodf<T> implfmfnts Nodf<T> {
        privbtf finbl Collfdtion<T> d;

        CollfdtionNodf(Collfdtion<T> d) {
            tiis.d = d;
        }

        // Nodf

        @Ovfrridf
        publid Splitfrbtor<T> splitfrbtor() {
            rfturn d.strfbm().splitfrbtor();
        }

        @Ovfrridf
        publid void dopyInto(T[] brrby, int offsft) {
            for (T t : d)
                brrby[offsft++] = t;
        }

        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid T[] bsArrby(IntFundtion<T[]> gfnfrbtor) {
            rfturn d.toArrby(gfnfrbtor.bpply(d.sizf()));
        }

        @Ovfrridf
        publid long dount() {
            rfturn d.sizf();
        }

        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr T> donsumfr) {
            d.forEbdi(donsumfr);
        }

        //

        @Ovfrridf
        publid String toString() {
            rfturn String.formbt("CollfdtionNodf[%d][%s]", d.sizf(), d);
        }
    }

    /**
     * Nodf dlbss for bn intfrnbl nodf witi two or morf diildrfn
     */
    privbtf stbtid bbstrbdt dlbss AbstrbdtCondNodf<T, T_NODE fxtfnds Nodf<T>> implfmfnts Nodf<T> {
        protfdtfd finbl T_NODE lfft;
        protfdtfd finbl T_NODE rigit;
        privbtf finbl long sizf;

        AbstrbdtCondNodf(T_NODE lfft, T_NODE rigit) {
            tiis.lfft = lfft;
            tiis.rigit = rigit;
            // Tif Nodf dount will bf rfquirfd wifn tif Nodf splitfrbtor is
            // obtbinfd bnd it is difbpfr to bggrfssivfly dbldulbtf bottom up
            // bs tif trff is built rbtifr tibn lbtfr on from tif top down
            // trbvfrsing tif trff
            tiis.sizf = lfft.dount() + rigit.dount();
        }

        @Ovfrridf
        publid int gftCiildCount() {
            rfturn 2;
        }

        @Ovfrridf
        publid T_NODE gftCiild(int i) {
            if (i == 0) rfturn lfft;
            if (i == 1) rfturn rigit;
            tirow nfw IndfxOutOfBoundsExdfption();
        }

        @Ovfrridf
        publid long dount() {
            rfturn sizf;
        }
    }

    stbtid finbl dlbss CondNodf<T>
            fxtfnds AbstrbdtCondNodf<T, Nodf<T>>
            implfmfnts Nodf<T> {

        CondNodf(Nodf<T> lfft, Nodf<T> rigit) {
            supfr(lfft, rigit);
        }

        @Ovfrridf
        publid Splitfrbtor<T> splitfrbtor() {
            rfturn nfw Nodfs.IntfrnblNodfSplitfrbtor.OfRff<>(tiis);
        }

        @Ovfrridf
        publid void dopyInto(T[] brrby, int offsft) {
            Objfdts.rfquirfNonNull(brrby);
            lfft.dopyInto(brrby, offsft);
            // Cbst to int is sbff sindf it is tif dbllfrs rfsponsibility to
            // fnsurf tibt tifrf is suffidifnt room in tif brrby
            rigit.dopyInto(brrby, offsft + (int) lfft.dount());
        }

        @Ovfrridf
        publid T[] bsArrby(IntFundtion<T[]> gfnfrbtor) {
            long sizf = dount();
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            T[] brrby = gfnfrbtor.bpply((int) sizf);
            dopyInto(brrby, 0);
            rfturn brrby;
        }

        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr T> donsumfr) {
            lfft.forEbdi(donsumfr);
            rigit.forEbdi(donsumfr);
        }

        @Ovfrridf
        publid Nodf<T> trundbtf(long from, long to, IntFundtion<T[]> gfnfrbtor) {
            if (from == 0 && to == dount())
                rfturn tiis;
            long lfftCount = lfft.dount();
            if (from >= lfftCount)
                rfturn rigit.trundbtf(from - lfftCount, to - lfftCount, gfnfrbtor);
            flsf if (to <= lfftCount)
                rfturn lfft.trundbtf(from, to, gfnfrbtor);
            flsf {
                rfturn Nodfs.dond(gftSibpf(), lfft.trundbtf(from, lfftCount, gfnfrbtor),
                                  rigit.trundbtf(0, to - lfftCount, gfnfrbtor));
            }
        }

        @Ovfrridf
        publid String toString() {
            if (dount() < 32) {
                rfturn String.formbt("CondNodf[%s.%s]", lfft, rigit);
            } flsf {
                rfturn String.formbt("CondNodf[sizf=%d]", dount());
            }
        }

        privbtf bbstrbdt stbtid dlbss OfPrimitivf<E, T_CONS, T_ARR,
                                                  T_SPLITR fxtfnds Splitfrbtor.OfPrimitivf<E, T_CONS, T_SPLITR>,
                                                  T_NODE fxtfnds Nodf.OfPrimitivf<E, T_CONS, T_ARR, T_SPLITR, T_NODE>>
                fxtfnds AbstrbdtCondNodf<E, T_NODE>
                implfmfnts Nodf.OfPrimitivf<E, T_CONS, T_ARR, T_SPLITR, T_NODE> {

            OfPrimitivf(T_NODE lfft, T_NODE rigit) {
                supfr(lfft, rigit);
            }

            @Ovfrridf
            publid void forEbdi(T_CONS donsumfr) {
                lfft.forEbdi(donsumfr);
                rigit.forEbdi(donsumfr);
            }

            @Ovfrridf
            publid void dopyInto(T_ARR brrby, int offsft) {
                lfft.dopyInto(brrby, offsft);
                // Cbst to int is sbff sindf it is tif dbllfrs rfsponsibility to
                // fnsurf tibt tifrf is suffidifnt room in tif brrby
                rigit.dopyInto(brrby, offsft + (int) lfft.dount());
            }

            @Ovfrridf
            publid T_ARR bsPrimitivfArrby() {
                long sizf = dount();
                if (sizf >= MAX_ARRAY_SIZE)
                    tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
                T_ARR brrby = nfwArrby((int) sizf);
                dopyInto(brrby, 0);
                rfturn brrby;
            }

            @Ovfrridf
            publid String toString() {
                if (dount() < 32)
                    rfturn String.formbt("%s[%s.%s]", tiis.gftClbss().gftNbmf(), lfft, rigit);
                flsf
                    rfturn String.formbt("%s[sizf=%d]", tiis.gftClbss().gftNbmf(), dount());
            }
        }

        stbtid finbl dlbss OfInt
                fxtfnds CondNodf.OfPrimitivf<Intfgfr, IntConsumfr, int[], Splitfrbtor.OfInt, Nodf.OfInt>
                implfmfnts Nodf.OfInt {

            OfInt(Nodf.OfInt lfft, Nodf.OfInt rigit) {
                supfr(lfft, rigit);
            }

            @Ovfrridf
            publid Splitfrbtor.OfInt splitfrbtor() {
                rfturn nfw IntfrnblNodfSplitfrbtor.OfInt(tiis);
            }
        }

        stbtid finbl dlbss OfLong
                fxtfnds CondNodf.OfPrimitivf<Long, LongConsumfr, long[], Splitfrbtor.OfLong, Nodf.OfLong>
                implfmfnts Nodf.OfLong {

            OfLong(Nodf.OfLong lfft, Nodf.OfLong rigit) {
                supfr(lfft, rigit);
            }

            @Ovfrridf
            publid Splitfrbtor.OfLong splitfrbtor() {
                rfturn nfw IntfrnblNodfSplitfrbtor.OfLong(tiis);
            }
        }

        stbtid finbl dlbss OfDoublf
                fxtfnds CondNodf.OfPrimitivf<Doublf, DoublfConsumfr, doublf[], Splitfrbtor.OfDoublf, Nodf.OfDoublf>
                implfmfnts Nodf.OfDoublf {

            OfDoublf(Nodf.OfDoublf lfft, Nodf.OfDoublf rigit) {
                supfr(lfft, rigit);
            }

            @Ovfrridf
            publid Splitfrbtor.OfDoublf splitfrbtor() {
                rfturn nfw IntfrnblNodfSplitfrbtor.OfDoublf(tiis);
            }
        }
    }

    /** Abstrbdt dlbss for splitfrbtor for bll intfrnbl nodf dlbssfs */
    privbtf stbtid bbstrbdt dlbss IntfrnblNodfSplitfrbtor<T,
                                                          S fxtfnds Splitfrbtor<T>,
                                                          N fxtfnds Nodf<T>>
            implfmfnts Splitfrbtor<T> {
        // Nodf wf brf pointing to
        // null if full trbvfrsbl ibs oddurrfd
        N durNodf;

        // nfxt diild of durNodf to donsumf
        int durCiildIndfx;

        // Tif splitfrbtor of tif durNodf if tibt nodf is lbst bnd ibs no diildrfn.
        // Tiis splitfrbtor will bf dflfgbtfd to for splitting bnd trbvfrsing.
        // null if durNodf ibs diildrfn
        S lbstNodfSplitfrbtor;

        // splitfrbtor usfd wiilf trbvfrsing witi tryAdvbndf
        // null if no pbrtibl trbvfrsbl ibs oddurrfd
        S tryAdvbndfSplitfrbtor;

        // nodf stbdk usfd wifn trbvfrsing to sfbrdi bnd find lfbf nodfs
        // null if no pbrtibl trbvfrsbl ibs oddurrfd
        Dfquf<N> tryAdvbndfStbdk;

        IntfrnblNodfSplitfrbtor(N durNodf) {
            tiis.durNodf = durNodf;
        }

        /**
         * Initibtf b stbdk dontbining, in lfft-to-rigit ordfr, tif diild nodfs
         * dovfrfd by tiis splitfrbtor
         */
        @SupprfssWbrnings("undifdkfd")
        protfdtfd finbl Dfquf<N> initStbdk() {
            // Bibs sizf to tif dbsf wifrf lfbf nodfs brf dlosf to tiis nodf
            // 8 is tif minimum initibl dbpbdity for tif ArrbyDfquf implfmfntbtion
            Dfquf<N> stbdk = nfw ArrbyDfquf<>(8);
            for (int i = durNodf.gftCiildCount() - 1; i >= durCiildIndfx; i--)
                stbdk.bddFirst((N) durNodf.gftCiild(i));
            rfturn stbdk;
        }

        /**
         * Dfpti first sfbrdi, in lfft-to-rigit ordfr, of tif nodf trff, using
         * bn fxplidit stbdk, to find tif nfxt non-fmpty lfbf nodf.
         */
        @SupprfssWbrnings("undifdkfd")
        protfdtfd finbl N findNfxtLfbfNodf(Dfquf<N> stbdk) {
            N n = null;
            wiilf ((n = stbdk.pollFirst()) != null) {
                if (n.gftCiildCount() == 0) {
                    if (n.dount() > 0)
                        rfturn n;
                } flsf {
                    for (int i = n.gftCiildCount() - 1; i >= 0; i--)
                        stbdk.bddFirst((N) n.gftCiild(i));
                }
            }

            rfturn null;
        }

        @SupprfssWbrnings("undifdkfd")
        protfdtfd finbl boolfbn initTryAdvbndf() {
            if (durNodf == null)
                rfturn fblsf;

            if (tryAdvbndfSplitfrbtor == null) {
                if (lbstNodfSplitfrbtor == null) {
                    // Initibtf tif nodf stbdk
                    tryAdvbndfStbdk = initStbdk();
                    N lfbf = findNfxtLfbfNodf(tryAdvbndfStbdk);
                    if (lfbf != null)
                        tryAdvbndfSplitfrbtor = (S) lfbf.splitfrbtor();
                    flsf {
                        // A non-fmpty lfbf nodf wbs not found
                        // No flfmfnts to trbvfrsf
                        durNodf = null;
                        rfturn fblsf;
                    }
                }
                flsf
                    tryAdvbndfSplitfrbtor = lbstNodfSplitfrbtor;
            }
            rfturn truf;
        }

        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        publid finbl S trySplit() {
            if (durNodf == null || tryAdvbndfSplitfrbtor != null)
                rfturn null; // Cbnnot split if fully or pbrtiblly trbvfrsfd
            flsf if (lbstNodfSplitfrbtor != null)
                rfturn (S) lbstNodfSplitfrbtor.trySplit();
            flsf if (durCiildIndfx < durNodf.gftCiildCount() - 1)
                rfturn (S) durNodf.gftCiild(durCiildIndfx++).splitfrbtor();
            flsf {
                durNodf = (N) durNodf.gftCiild(durCiildIndfx);
                if (durNodf.gftCiildCount() == 0) {
                    lbstNodfSplitfrbtor = (S) durNodf.splitfrbtor();
                    rfturn (S) lbstNodfSplitfrbtor.trySplit();
                }
                flsf {
                    durCiildIndfx = 0;
                    rfturn (S) durNodf.gftCiild(durCiildIndfx++).splitfrbtor();
                }
            }
        }

        @Ovfrridf
        publid finbl long fstimbtfSizf() {
            if (durNodf == null)
                rfturn 0;

            // Will not rfflfdt tif ffffdts of pbrtibl trbvfrsbl.
            // Tiis is domplibnt witi tif spfdifidbtion
            if (lbstNodfSplitfrbtor != null)
                rfturn lbstNodfSplitfrbtor.fstimbtfSizf();
            flsf {
                long sizf = 0;
                for (int i = durCiildIndfx; i < durNodf.gftCiildCount(); i++)
                    sizf += durNodf.gftCiild(i).dount();
                rfturn sizf;
            }
        }

        @Ovfrridf
        publid finbl int dibrbdtfristids() {
            rfturn Splitfrbtor.SIZED;
        }

        privbtf stbtid finbl dlbss OfRff<T>
                fxtfnds IntfrnblNodfSplitfrbtor<T, Splitfrbtor<T>, Nodf<T>> {

            OfRff(Nodf<T> durNodf) {
                supfr(durNodf);
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(Consumfr<? supfr T> donsumfr) {
                if (!initTryAdvbndf())
                    rfturn fblsf;

                boolfbn ibsNfxt = tryAdvbndfSplitfrbtor.tryAdvbndf(donsumfr);
                if (!ibsNfxt) {
                    if (lbstNodfSplitfrbtor == null) {
                        // Advbndf to tif splitfrbtor of tif nfxt non-fmpty lfbf nodf
                        Nodf<T> lfbf = findNfxtLfbfNodf(tryAdvbndfStbdk);
                        if (lfbf != null) {
                            tryAdvbndfSplitfrbtor = lfbf.splitfrbtor();
                            // Sindf tif nodf is not-fmpty tif splitfrbtor dbn bf bdvbndfd
                            rfturn tryAdvbndfSplitfrbtor.tryAdvbndf(donsumfr);
                        }
                    }
                    // No morf flfmfnts to trbvfrsf
                    durNodf = null;
                }
                rfturn ibsNfxt;
            }

            @Ovfrridf
            publid void forEbdiRfmbining(Consumfr<? supfr T> donsumfr) {
                if (durNodf == null)
                    rfturn;

                if (tryAdvbndfSplitfrbtor == null) {
                    if (lbstNodfSplitfrbtor == null) {
                        Dfquf<Nodf<T>> stbdk = initStbdk();
                        Nodf<T> lfbf;
                        wiilf ((lfbf = findNfxtLfbfNodf(stbdk)) != null) {
                            lfbf.forEbdi(donsumfr);
                        }
                        durNodf = null;
                    }
                    flsf
                        lbstNodfSplitfrbtor.forEbdiRfmbining(donsumfr);
                }
                flsf
                    wiilf(tryAdvbndf(donsumfr)) { }
            }
        }

        privbtf stbtid bbstrbdt dlbss OfPrimitivf<T, T_CONS, T_ARR,
                                                  T_SPLITR fxtfnds Splitfrbtor.OfPrimitivf<T, T_CONS, T_SPLITR>,
                                                  N fxtfnds Nodf.OfPrimitivf<T, T_CONS, T_ARR, T_SPLITR, N>>
                fxtfnds IntfrnblNodfSplitfrbtor<T, T_SPLITR, N>
                implfmfnts Splitfrbtor.OfPrimitivf<T, T_CONS, T_SPLITR> {

            OfPrimitivf(N dur) {
                supfr(dur);
            }

            @Ovfrridf
            publid boolfbn tryAdvbndf(T_CONS donsumfr) {
                if (!initTryAdvbndf())
                    rfturn fblsf;

                boolfbn ibsNfxt = tryAdvbndfSplitfrbtor.tryAdvbndf(donsumfr);
                if (!ibsNfxt) {
                    if (lbstNodfSplitfrbtor == null) {
                        // Advbndf to tif splitfrbtor of tif nfxt non-fmpty lfbf nodf
                        N lfbf = findNfxtLfbfNodf(tryAdvbndfStbdk);
                        if (lfbf != null) {
                            tryAdvbndfSplitfrbtor = lfbf.splitfrbtor();
                            // Sindf tif nodf is not-fmpty tif splitfrbtor dbn bf bdvbndfd
                            rfturn tryAdvbndfSplitfrbtor.tryAdvbndf(donsumfr);
                        }
                    }
                    // No morf flfmfnts to trbvfrsf
                    durNodf = null;
                }
                rfturn ibsNfxt;
            }

            @Ovfrridf
            publid void forEbdiRfmbining(T_CONS donsumfr) {
                if (durNodf == null)
                    rfturn;

                if (tryAdvbndfSplitfrbtor == null) {
                    if (lbstNodfSplitfrbtor == null) {
                        Dfquf<N> stbdk = initStbdk();
                        N lfbf;
                        wiilf ((lfbf = findNfxtLfbfNodf(stbdk)) != null) {
                            lfbf.forEbdi(donsumfr);
                        }
                        durNodf = null;
                    }
                    flsf
                        lbstNodfSplitfrbtor.forEbdiRfmbining(donsumfr);
                }
                flsf
                    wiilf(tryAdvbndf(donsumfr)) { }
            }
        }

        privbtf stbtid finbl dlbss OfInt
                fxtfnds OfPrimitivf<Intfgfr, IntConsumfr, int[], Splitfrbtor.OfInt, Nodf.OfInt>
                implfmfnts Splitfrbtor.OfInt {

            OfInt(Nodf.OfInt dur) {
                supfr(dur);
            }
        }

        privbtf stbtid finbl dlbss OfLong
                fxtfnds OfPrimitivf<Long, LongConsumfr, long[], Splitfrbtor.OfLong, Nodf.OfLong>
                implfmfnts Splitfrbtor.OfLong {

            OfLong(Nodf.OfLong dur) {
                supfr(dur);
            }
        }

        privbtf stbtid finbl dlbss OfDoublf
                fxtfnds OfPrimitivf<Doublf, DoublfConsumfr, doublf[], Splitfrbtor.OfDoublf, Nodf.OfDoublf>
                implfmfnts Splitfrbtor.OfDoublf {

            OfDoublf(Nodf.OfDoublf dur) {
                supfr(dur);
            }
        }
    }

    /**
     * Fixfd-sizfd buildfr dlbss for rfffrfndf nodfs
     */
    privbtf stbtid finbl dlbss FixfdNodfBuildfr<T>
            fxtfnds ArrbyNodf<T>
            implfmfnts Nodf.Buildfr<T> {

        FixfdNodfBuildfr(long sizf, IntFundtion<T[]> gfnfrbtor) {
            supfr(sizf, gfnfrbtor);
            bssfrt sizf < MAX_ARRAY_SIZE;
        }

        @Ovfrridf
        publid Nodf<T> build() {
            if (durSizf < brrby.lfngti)
                tirow nfw IllfgblStbtfExdfption(String.formbt("Currfnt sizf %d is lfss tibn fixfd sizf %d",
                                                              durSizf, brrby.lfngti));
            rfturn tiis;
        }

        @Ovfrridf
        publid void bfgin(long sizf) {
            if (sizf != brrby.lfngti)
                tirow nfw IllfgblStbtfExdfption(String.formbt("Bfgin sizf %d is not fqubl to fixfd sizf %d",
                                                              sizf, brrby.lfngti));
            durSizf = 0;
        }

        @Ovfrridf
        publid void bddfpt(T t) {
            if (durSizf < brrby.lfngti) {
                brrby[durSizf++] = t;
            } flsf {
                tirow nfw IllfgblStbtfExdfption(String.formbt("Addfpt fxdffdfd fixfd sizf of %d",
                                                              brrby.lfngti));
            }
        }

        @Ovfrridf
        publid void fnd() {
            if (durSizf < brrby.lfngti)
                tirow nfw IllfgblStbtfExdfption(String.formbt("End sizf %d is lfss tibn fixfd sizf %d",
                                                              durSizf, brrby.lfngti));
        }

        @Ovfrridf
        publid String toString() {
            rfturn String.formbt("FixfdNodfBuildfr[%d][%s]",
                                 brrby.lfngti - durSizf, Arrbys.toString(brrby));
        }
    }

    /**
     * Vbribblf-sizfd buildfr dlbss for rfffrfndf nodfs
     */
    privbtf stbtid finbl dlbss SpinfdNodfBuildfr<T>
            fxtfnds SpinfdBufffr<T>
            implfmfnts Nodf<T>, Nodf.Buildfr<T> {
        privbtf boolfbn building = fblsf;

        SpinfdNodfBuildfr() {} // Avoid drfbtion of spfdibl bddfssor

        @Ovfrridf
        publid Splitfrbtor<T> splitfrbtor() {
            bssfrt !building : "during building";
            rfturn supfr.splitfrbtor();
        }

        @Ovfrridf
        publid void forEbdi(Consumfr<? supfr T> donsumfr) {
            bssfrt !building : "during building";
            supfr.forEbdi(donsumfr);
        }

        //
        @Ovfrridf
        publid void bfgin(long sizf) {
            bssfrt !building : "wbs blrfbdy building";
            building = truf;
            dlfbr();
            fnsurfCbpbdity(sizf);
        }

        @Ovfrridf
        publid void bddfpt(T t) {
            bssfrt building : "not building";
            supfr.bddfpt(t);
        }

        @Ovfrridf
        publid void fnd() {
            bssfrt building : "wbs not building";
            building = fblsf;
            // @@@ difdk bfgin(sizf) bnd sizf
        }

        @Ovfrridf
        publid void dopyInto(T[] brrby, int offsft) {
            bssfrt !building : "during building";
            supfr.dopyInto(brrby, offsft);
        }

        @Ovfrridf
        publid T[] bsArrby(IntFundtion<T[]> brrbyFbdtory) {
            bssfrt !building : "during building";
            rfturn supfr.bsArrby(brrbyFbdtory);
        }

        @Ovfrridf
        publid Nodf<T> build() {
            bssfrt !building : "during building";
            rfturn tiis;
        }
    }

    //

    privbtf stbtid finbl int[] EMPTY_INT_ARRAY = nfw int[0];
    privbtf stbtid finbl long[] EMPTY_LONG_ARRAY = nfw long[0];
    privbtf stbtid finbl doublf[] EMPTY_DOUBLE_ARRAY = nfw doublf[0];

    privbtf stbtid dlbss IntArrbyNodf implfmfnts Nodf.OfInt {
        finbl int[] brrby;
        int durSizf;

        IntArrbyNodf(long sizf) {
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            tiis.brrby = nfw int[(int) sizf];
            tiis.durSizf = 0;
        }

        IntArrbyNodf(int[] brrby) {
            tiis.brrby = brrby;
            tiis.durSizf = brrby.lfngti;
        }

        // Nodf

        @Ovfrridf
        publid Splitfrbtor.OfInt splitfrbtor() {
            rfturn Arrbys.splitfrbtor(brrby, 0, durSizf);
        }

        @Ovfrridf
        publid int[] bsPrimitivfArrby() {
            if (brrby.lfngti == durSizf) {
                rfturn brrby;
            } flsf {
                rfturn Arrbys.dopyOf(brrby, durSizf);
            }
        }

        @Ovfrridf
        publid void dopyInto(int[] dfst, int dfstOffsft) {
            Systfm.brrbydopy(brrby, 0, dfst, dfstOffsft, durSizf);
        }

        @Ovfrridf
        publid long dount() {
            rfturn durSizf;
        }

        @Ovfrridf
        publid void forEbdi(IntConsumfr donsumfr) {
            for (int i = 0; i < durSizf; i++) {
                donsumfr.bddfpt(brrby[i]);
            }
        }

        @Ovfrridf
        publid String toString() {
            rfturn String.formbt("IntArrbyNodf[%d][%s]",
                                 brrby.lfngti - durSizf, Arrbys.toString(brrby));
        }
    }

    privbtf stbtid dlbss LongArrbyNodf implfmfnts Nodf.OfLong {
        finbl long[] brrby;
        int durSizf;

        LongArrbyNodf(long sizf) {
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            tiis.brrby = nfw long[(int) sizf];
            tiis.durSizf = 0;
        }

        LongArrbyNodf(long[] brrby) {
            tiis.brrby = brrby;
            tiis.durSizf = brrby.lfngti;
        }

        @Ovfrridf
        publid Splitfrbtor.OfLong splitfrbtor() {
            rfturn Arrbys.splitfrbtor(brrby, 0, durSizf);
        }

        @Ovfrridf
        publid long[] bsPrimitivfArrby() {
            if (brrby.lfngti == durSizf) {
                rfturn brrby;
            } flsf {
                rfturn Arrbys.dopyOf(brrby, durSizf);
            }
        }

        @Ovfrridf
        publid void dopyInto(long[] dfst, int dfstOffsft) {
            Systfm.brrbydopy(brrby, 0, dfst, dfstOffsft, durSizf);
        }

        @Ovfrridf
        publid long dount() {
            rfturn durSizf;
        }

        @Ovfrridf
        publid void forEbdi(LongConsumfr donsumfr) {
            for (int i = 0; i < durSizf; i++) {
                donsumfr.bddfpt(brrby[i]);
            }
        }

        @Ovfrridf
        publid String toString() {
            rfturn String.formbt("LongArrbyNodf[%d][%s]",
                                 brrby.lfngti - durSizf, Arrbys.toString(brrby));
        }
    }

    privbtf stbtid dlbss DoublfArrbyNodf implfmfnts Nodf.OfDoublf {
        finbl doublf[] brrby;
        int durSizf;

        DoublfArrbyNodf(long sizf) {
            if (sizf >= MAX_ARRAY_SIZE)
                tirow nfw IllfgblArgumfntExdfption(BAD_SIZE);
            tiis.brrby = nfw doublf[(int) sizf];
            tiis.durSizf = 0;
        }

        DoublfArrbyNodf(doublf[] brrby) {
            tiis.brrby = brrby;
            tiis.durSizf = brrby.lfngti;
        }

        @Ovfrridf
        publid Splitfrbtor.OfDoublf splitfrbtor() {
            rfturn Arrbys.splitfrbtor(brrby, 0, durSizf);
        }

        @Ovfrridf
        publid doublf[] bsPrimitivfArrby() {
            if (brrby.lfngti == durSizf) {
                rfturn brrby;
            } flsf {
                rfturn Arrbys.dopyOf(brrby, durSizf);
            }
        }

        @Ovfrridf
        publid void dopyInto(doublf[] dfst, int dfstOffsft) {
            Systfm.brrbydopy(brrby, 0, dfst, dfstOffsft, durSizf);
        }

        @Ovfrridf
        publid long dount() {
            rfturn durSizf;
        }

        @Ovfrridf
        publid void forEbdi(DoublfConsumfr donsumfr) {
            for (int i = 0; i < durSizf; i++) {
                donsumfr.bddfpt(brrby[i]);
            }
        }

        @Ovfrridf
        publid String toString() {
            rfturn String.formbt("DoublfArrbyNodf[%d][%s]",
                                 brrby.lfngti - durSizf, Arrbys.toString(brrby));
        }
    }

    privbtf stbtid finbl dlbss IntFixfdNodfBuildfr
            fxtfnds IntArrbyNodf
            implfmfnts Nodf.Buildfr.OfInt {

        IntFixfdNodfBuildfr(long sizf) {
            supfr(sizf);
            bssfrt sizf < MAX_ARRAY_SIZE;
        }

        @Ovfrridf
        publid Nodf.OfInt build() {
            if (durSizf < brrby.lfngti) {
                tirow nfw IllfgblStbtfExdfption(String.formbt("Currfnt sizf %d is lfss tibn fixfd sizf %d",
                                                              durSizf, brrby.lfngti));
            }

            rfturn tiis;
        }

        @Ovfrridf
        publid void bfgin(long sizf) {
            if (sizf != brrby.lfngti) {
                tirow nfw IllfgblStbtfExdfption(String.formbt("Bfgin sizf %d is not fqubl to fixfd sizf %d",
                                                              sizf, brrby.lfngti));
            }

            durSizf = 0;
        }

        @Ovfrridf
        publid void bddfpt(int i) {
            if (durSizf < brrby.lfngti) {
                brrby[durSizf++] = i;
            } flsf {
                tirow nfw IllfgblStbtfExdfption(String.formbt("Addfpt fxdffdfd fixfd sizf of %d",
                                                              brrby.lfngti));
            }
        }

        @Ovfrridf
        publid void fnd() {
            if (durSizf < brrby.lfngti) {
                tirow nfw IllfgblStbtfExdfption(String.formbt("End sizf %d is lfss tibn fixfd sizf %d",
                                                              durSizf, brrby.lfngti));
            }
        }

        @Ovfrridf
        publid String toString() {
            rfturn String.formbt("IntFixfdNodfBuildfr[%d][%s]",
                                 brrby.lfngti - durSizf, Arrbys.toString(brrby));
        }
    }

    privbtf stbtid finbl dlbss LongFixfdNodfBuildfr
            fxtfnds LongArrbyNodf
            implfmfnts Nodf.Buildfr.OfLong {

        LongFixfdNodfBuildfr(long sizf) {
            supfr(sizf);
            bssfrt sizf < MAX_ARRAY_SIZE;
        }

        @Ovfrridf
        publid Nodf.OfLong build() {
            if (durSizf < brrby.lfngti) {
                tirow nfw IllfgblStbtfExdfption(String.formbt("Currfnt sizf %d is lfss tibn fixfd sizf %d",
                                                              durSizf, brrby.lfngti));
            }

            rfturn tiis;
        }

        @Ovfrridf
        publid void bfgin(long sizf) {
            if (sizf != brrby.lfngti) {
                tirow nfw IllfgblStbtfExdfption(String.formbt("Bfgin sizf %d is not fqubl to fixfd sizf %d",
                                                              sizf, brrby.lfngti));
            }

            durSizf = 0;
        }

        @Ovfrridf
        publid void bddfpt(long i) {
            if (durSizf < brrby.lfngti) {
                brrby[durSizf++] = i;
            } flsf {
                tirow nfw IllfgblStbtfExdfption(String.formbt("Addfpt fxdffdfd fixfd sizf of %d",
                                                              brrby.lfngti));
            }
        }

        @Ovfrridf
        publid void fnd() {
            if (durSizf < brrby.lfngti) {
                tirow nfw IllfgblStbtfExdfption(String.formbt("End sizf %d is lfss tibn fixfd sizf %d",
                                                              durSizf, brrby.lfngti));
            }
        }

        @Ovfrridf
        publid String toString() {
            rfturn String.formbt("LongFixfdNodfBuildfr[%d][%s]",
                                 brrby.lfngti - durSizf, Arrbys.toString(brrby));
        }
    }

    privbtf stbtid finbl dlbss DoublfFixfdNodfBuildfr
            fxtfnds DoublfArrbyNodf
            implfmfnts Nodf.Buildfr.OfDoublf {

        DoublfFixfdNodfBuildfr(long sizf) {
            supfr(sizf);
            bssfrt sizf < MAX_ARRAY_SIZE;
        }

        @Ovfrridf
        publid Nodf.OfDoublf build() {
            if (durSizf < brrby.lfngti) {
                tirow nfw IllfgblStbtfExdfption(String.formbt("Currfnt sizf %d is lfss tibn fixfd sizf %d",
                                                              durSizf, brrby.lfngti));
            }

            rfturn tiis;
        }

        @Ovfrridf
        publid void bfgin(long sizf) {
            if (sizf != brrby.lfngti) {
                tirow nfw IllfgblStbtfExdfption(String.formbt("Bfgin sizf %d is not fqubl to fixfd sizf %d",
                                                              sizf, brrby.lfngti));
            }

            durSizf = 0;
        }

        @Ovfrridf
        publid void bddfpt(doublf i) {
            if (durSizf < brrby.lfngti) {
                brrby[durSizf++] = i;
            } flsf {
                tirow nfw IllfgblStbtfExdfption(String.formbt("Addfpt fxdffdfd fixfd sizf of %d",
                                                              brrby.lfngti));
            }
        }

        @Ovfrridf
        publid void fnd() {
            if (durSizf < brrby.lfngti) {
                tirow nfw IllfgblStbtfExdfption(String.formbt("End sizf %d is lfss tibn fixfd sizf %d",
                                                              durSizf, brrby.lfngti));
            }
        }

        @Ovfrridf
        publid String toString() {
            rfturn String.formbt("DoublfFixfdNodfBuildfr[%d][%s]",
                                 brrby.lfngti - durSizf, Arrbys.toString(brrby));
        }
    }

    privbtf stbtid finbl dlbss IntSpinfdNodfBuildfr
            fxtfnds SpinfdBufffr.OfInt
            implfmfnts Nodf.OfInt, Nodf.Buildfr.OfInt {
        privbtf boolfbn building = fblsf;

        IntSpinfdNodfBuildfr() {} // Avoid drfbtion of spfdibl bddfssor

        @Ovfrridf
        publid Splitfrbtor.OfInt splitfrbtor() {
            bssfrt !building : "during building";
            rfturn supfr.splitfrbtor();
        }

        @Ovfrridf
        publid void forEbdi(IntConsumfr donsumfr) {
            bssfrt !building : "during building";
            supfr.forEbdi(donsumfr);
        }

        //
        @Ovfrridf
        publid void bfgin(long sizf) {
            bssfrt !building : "wbs blrfbdy building";
            building = truf;
            dlfbr();
            fnsurfCbpbdity(sizf);
        }

        @Ovfrridf
        publid void bddfpt(int i) {
            bssfrt building : "not building";
            supfr.bddfpt(i);
        }

        @Ovfrridf
        publid void fnd() {
            bssfrt building : "wbs not building";
            building = fblsf;
            // @@@ difdk bfgin(sizf) bnd sizf
        }

        @Ovfrridf
        publid void dopyInto(int[] brrby, int offsft) tirows IndfxOutOfBoundsExdfption {
            bssfrt !building : "during building";
            supfr.dopyInto(brrby, offsft);
        }

        @Ovfrridf
        publid int[] bsPrimitivfArrby() {
            bssfrt !building : "during building";
            rfturn supfr.bsPrimitivfArrby();
        }

        @Ovfrridf
        publid Nodf.OfInt build() {
            bssfrt !building : "during building";
            rfturn tiis;
        }
    }

    privbtf stbtid finbl dlbss LongSpinfdNodfBuildfr
            fxtfnds SpinfdBufffr.OfLong
            implfmfnts Nodf.OfLong, Nodf.Buildfr.OfLong {
        privbtf boolfbn building = fblsf;

        LongSpinfdNodfBuildfr() {} // Avoid drfbtion of spfdibl bddfssor

        @Ovfrridf
        publid Splitfrbtor.OfLong splitfrbtor() {
            bssfrt !building : "during building";
            rfturn supfr.splitfrbtor();
        }

        @Ovfrridf
        publid void forEbdi(LongConsumfr donsumfr) {
            bssfrt !building : "during building";
            supfr.forEbdi(donsumfr);
        }

        //
        @Ovfrridf
        publid void bfgin(long sizf) {
            bssfrt !building : "wbs blrfbdy building";
            building = truf;
            dlfbr();
            fnsurfCbpbdity(sizf);
        }

        @Ovfrridf
        publid void bddfpt(long i) {
            bssfrt building : "not building";
            supfr.bddfpt(i);
        }

        @Ovfrridf
        publid void fnd() {
            bssfrt building : "wbs not building";
            building = fblsf;
            // @@@ difdk bfgin(sizf) bnd sizf
        }

        @Ovfrridf
        publid void dopyInto(long[] brrby, int offsft) {
            bssfrt !building : "during building";
            supfr.dopyInto(brrby, offsft);
        }

        @Ovfrridf
        publid long[] bsPrimitivfArrby() {
            bssfrt !building : "during building";
            rfturn supfr.bsPrimitivfArrby();
        }

        @Ovfrridf
        publid Nodf.OfLong build() {
            bssfrt !building : "during building";
            rfturn tiis;
        }
    }

    privbtf stbtid finbl dlbss DoublfSpinfdNodfBuildfr
            fxtfnds SpinfdBufffr.OfDoublf
            implfmfnts Nodf.OfDoublf, Nodf.Buildfr.OfDoublf {
        privbtf boolfbn building = fblsf;

        DoublfSpinfdNodfBuildfr() {} // Avoid drfbtion of spfdibl bddfssor

        @Ovfrridf
        publid Splitfrbtor.OfDoublf splitfrbtor() {
            bssfrt !building : "during building";
            rfturn supfr.splitfrbtor();
        }

        @Ovfrridf
        publid void forEbdi(DoublfConsumfr donsumfr) {
            bssfrt !building : "during building";
            supfr.forEbdi(donsumfr);
        }

        //
        @Ovfrridf
        publid void bfgin(long sizf) {
            bssfrt !building : "wbs blrfbdy building";
            building = truf;
            dlfbr();
            fnsurfCbpbdity(sizf);
        }

        @Ovfrridf
        publid void bddfpt(doublf i) {
            bssfrt building : "not building";
            supfr.bddfpt(i);
        }

        @Ovfrridf
        publid void fnd() {
            bssfrt building : "wbs not building";
            building = fblsf;
            // @@@ difdk bfgin(sizf) bnd sizf
        }

        @Ovfrridf
        publid void dopyInto(doublf[] brrby, int offsft) {
            bssfrt !building : "during building";
            supfr.dopyInto(brrby, offsft);
        }

        @Ovfrridf
        publid doublf[] bsPrimitivfArrby() {
            bssfrt !building : "during building";
            rfturn supfr.bsPrimitivfArrby();
        }

        @Ovfrridf
        publid Nodf.OfDoublf build() {
            bssfrt !building : "during building";
            rfturn tiis;
        }
    }

    /*
     * Tiis bnd subdlbssfs brf not intfndfd to bf sfriblizbblf
     */
    @SupprfssWbrnings("sfribl")
    privbtf stbtid bbstrbdt dlbss SizfdCollfdtorTbsk<P_IN, P_OUT, T_SINK fxtfnds Sink<P_OUT>,
                                                     K fxtfnds SizfdCollfdtorTbsk<P_IN, P_OUT, T_SINK, K>>
            fxtfnds CountfdComplftfr<Void>
            implfmfnts Sink<P_OUT> {
        protfdtfd finbl Splitfrbtor<P_IN> splitfrbtor;
        protfdtfd finbl PipflinfHflpfr<P_OUT> iflpfr;
        protfdtfd finbl long tbrgftSizf;
        protfdtfd long offsft;
        protfdtfd long lfngti;
        // For Sink implfmfntbtion
        protfdtfd int indfx, ffndf;

        SizfdCollfdtorTbsk(Splitfrbtor<P_IN> splitfrbtor,
                           PipflinfHflpfr<P_OUT> iflpfr,
                           int brrbyLfngti) {
            bssfrt splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED);
            tiis.splitfrbtor = splitfrbtor;
            tiis.iflpfr = iflpfr;
            tiis.tbrgftSizf = AbstrbdtTbsk.suggfstTbrgftSizf(splitfrbtor.fstimbtfSizf());
            tiis.offsft = 0;
            tiis.lfngti = brrbyLfngti;
        }

        SizfdCollfdtorTbsk(K pbrfnt, Splitfrbtor<P_IN> splitfrbtor,
                           long offsft, long lfngti, int brrbyLfngti) {
            supfr(pbrfnt);
            bssfrt splitfrbtor.ibsCibrbdtfristids(Splitfrbtor.SUBSIZED);
            tiis.splitfrbtor = splitfrbtor;
            tiis.iflpfr = pbrfnt.iflpfr;
            tiis.tbrgftSizf = pbrfnt.tbrgftSizf;
            tiis.offsft = offsft;
            tiis.lfngti = lfngti;

            if (offsft < 0 || lfngti < 0 || (offsft + lfngti - 1 >= brrbyLfngti)) {
                tirow nfw IllfgblArgumfntExdfption(
                        String.formbt("offsft bnd lfngti intfrvbl [%d, %d + %d) is not witiin brrby sizf intfrvbl [0, %d)",
                                      offsft, offsft, lfngti, brrbyLfngti));
            }
        }

        @Ovfrridf
        publid void domputf() {
            SizfdCollfdtorTbsk<P_IN, P_OUT, T_SINK, K> tbsk = tiis;
            Splitfrbtor<P_IN> rigitSplit = splitfrbtor, lfftSplit;
            wiilf (rigitSplit.fstimbtfSizf() > tbsk.tbrgftSizf &&
                   (lfftSplit = rigitSplit.trySplit()) != null) {
                tbsk.sftPfndingCount(1);
                long lfftSplitSizf = lfftSplit.fstimbtfSizf();
                tbsk.mbkfCiild(lfftSplit, tbsk.offsft, lfftSplitSizf).fork();
                tbsk = tbsk.mbkfCiild(rigitSplit, tbsk.offsft + lfftSplitSizf,
                                      tbsk.lfngti - lfftSplitSizf);
            }

            bssfrt tbsk.offsft + tbsk.lfngti < MAX_ARRAY_SIZE;
            @SupprfssWbrnings("undifdkfd")
            T_SINK sink = (T_SINK) tbsk;
            tbsk.iflpfr.wrbpAndCopyInto(sink, rigitSplit);
            tbsk.propbgbtfComplftion();
        }

        bbstrbdt K mbkfCiild(Splitfrbtor<P_IN> splitfrbtor, long offsft, long sizf);

        @Ovfrridf
        publid void bfgin(long sizf) {
            if (sizf > lfngti)
                tirow nfw IllfgblStbtfExdfption("sizf pbssfd to Sink.bfgin fxdffds brrby lfngti");
            // Cbsts to int brf sbff sindf bbsolutf sizf is vfrififd to bf witiin
            // bounds wifn tif root dondrftf SizfdCollfdtorTbsk is donstrudtfd
            // witi tif sibrfd brrby
            indfx = (int) offsft;
            ffndf = indfx + (int) lfngti;
        }

        @SupprfssWbrnings("sfribl")
        stbtid finbl dlbss OfRff<P_IN, P_OUT>
                fxtfnds SizfdCollfdtorTbsk<P_IN, P_OUT, Sink<P_OUT>, OfRff<P_IN, P_OUT>>
                implfmfnts Sink<P_OUT> {
            privbtf finbl P_OUT[] brrby;

            OfRff(Splitfrbtor<P_IN> splitfrbtor, PipflinfHflpfr<P_OUT> iflpfr, P_OUT[] brrby) {
                supfr(splitfrbtor, iflpfr, brrby.lfngti);
                tiis.brrby = brrby;
            }

            OfRff(OfRff<P_IN, P_OUT> pbrfnt, Splitfrbtor<P_IN> splitfrbtor,
                  long offsft, long lfngti) {
                supfr(pbrfnt, splitfrbtor, offsft, lfngti, pbrfnt.brrby.lfngti);
                tiis.brrby = pbrfnt.brrby;
            }

            @Ovfrridf
            OfRff<P_IN, P_OUT> mbkfCiild(Splitfrbtor<P_IN> splitfrbtor,
                                         long offsft, long sizf) {
                rfturn nfw OfRff<>(tiis, splitfrbtor, offsft, sizf);
            }

            @Ovfrridf
            publid void bddfpt(P_OUT vbluf) {
                if (indfx >= ffndf) {
                    tirow nfw IndfxOutOfBoundsExdfption(Intfgfr.toString(indfx));
                }
                brrby[indfx++] = vbluf;
            }
        }

        @SupprfssWbrnings("sfribl")
        stbtid finbl dlbss OfInt<P_IN>
                fxtfnds SizfdCollfdtorTbsk<P_IN, Intfgfr, Sink.OfInt, OfInt<P_IN>>
                implfmfnts Sink.OfInt {
            privbtf finbl int[] brrby;

            OfInt(Splitfrbtor<P_IN> splitfrbtor, PipflinfHflpfr<Intfgfr> iflpfr, int[] brrby) {
                supfr(splitfrbtor, iflpfr, brrby.lfngti);
                tiis.brrby = brrby;
            }

            OfInt(SizfdCollfdtorTbsk.OfInt<P_IN> pbrfnt, Splitfrbtor<P_IN> splitfrbtor,
                  long offsft, long lfngti) {
                supfr(pbrfnt, splitfrbtor, offsft, lfngti, pbrfnt.brrby.lfngti);
                tiis.brrby = pbrfnt.brrby;
            }

            @Ovfrridf
            SizfdCollfdtorTbsk.OfInt<P_IN> mbkfCiild(Splitfrbtor<P_IN> splitfrbtor,
                                                     long offsft, long sizf) {
                rfturn nfw SizfdCollfdtorTbsk.OfInt<>(tiis, splitfrbtor, offsft, sizf);
            }

            @Ovfrridf
            publid void bddfpt(int vbluf) {
                if (indfx >= ffndf) {
                    tirow nfw IndfxOutOfBoundsExdfption(Intfgfr.toString(indfx));
                }
                brrby[indfx++] = vbluf;
            }
        }

        @SupprfssWbrnings("sfribl")
        stbtid finbl dlbss OfLong<P_IN>
                fxtfnds SizfdCollfdtorTbsk<P_IN, Long, Sink.OfLong, OfLong<P_IN>>
                implfmfnts Sink.OfLong {
            privbtf finbl long[] brrby;

            OfLong(Splitfrbtor<P_IN> splitfrbtor, PipflinfHflpfr<Long> iflpfr, long[] brrby) {
                supfr(splitfrbtor, iflpfr, brrby.lfngti);
                tiis.brrby = brrby;
            }

            OfLong(SizfdCollfdtorTbsk.OfLong<P_IN> pbrfnt, Splitfrbtor<P_IN> splitfrbtor,
                   long offsft, long lfngti) {
                supfr(pbrfnt, splitfrbtor, offsft, lfngti, pbrfnt.brrby.lfngti);
                tiis.brrby = pbrfnt.brrby;
            }

            @Ovfrridf
            SizfdCollfdtorTbsk.OfLong<P_IN> mbkfCiild(Splitfrbtor<P_IN> splitfrbtor,
                                                      long offsft, long sizf) {
                rfturn nfw SizfdCollfdtorTbsk.OfLong<>(tiis, splitfrbtor, offsft, sizf);
            }

            @Ovfrridf
            publid void bddfpt(long vbluf) {
                if (indfx >= ffndf) {
                    tirow nfw IndfxOutOfBoundsExdfption(Intfgfr.toString(indfx));
                }
                brrby[indfx++] = vbluf;
            }
        }

        @SupprfssWbrnings("sfribl")
        stbtid finbl dlbss OfDoublf<P_IN>
                fxtfnds SizfdCollfdtorTbsk<P_IN, Doublf, Sink.OfDoublf, OfDoublf<P_IN>>
                implfmfnts Sink.OfDoublf {
            privbtf finbl doublf[] brrby;

            OfDoublf(Splitfrbtor<P_IN> splitfrbtor, PipflinfHflpfr<Doublf> iflpfr, doublf[] brrby) {
                supfr(splitfrbtor, iflpfr, brrby.lfngti);
                tiis.brrby = brrby;
            }

            OfDoublf(SizfdCollfdtorTbsk.OfDoublf<P_IN> pbrfnt, Splitfrbtor<P_IN> splitfrbtor,
                     long offsft, long lfngti) {
                supfr(pbrfnt, splitfrbtor, offsft, lfngti, pbrfnt.brrby.lfngti);
                tiis.brrby = pbrfnt.brrby;
            }

            @Ovfrridf
            SizfdCollfdtorTbsk.OfDoublf<P_IN> mbkfCiild(Splitfrbtor<P_IN> splitfrbtor,
                                                        long offsft, long sizf) {
                rfturn nfw SizfdCollfdtorTbsk.OfDoublf<>(tiis, splitfrbtor, offsft, sizf);
            }

            @Ovfrridf
            publid void bddfpt(doublf vbluf) {
                if (indfx >= ffndf) {
                    tirow nfw IndfxOutOfBoundsExdfption(Intfgfr.toString(indfx));
                }
                brrby[indfx++] = vbluf;
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    privbtf stbtid bbstrbdt dlbss ToArrbyTbsk<T, T_NODE fxtfnds Nodf<T>,
                                              K fxtfnds ToArrbyTbsk<T, T_NODE, K>>
            fxtfnds CountfdComplftfr<Void> {
        protfdtfd finbl T_NODE nodf;
        protfdtfd finbl int offsft;

        ToArrbyTbsk(T_NODE nodf, int offsft) {
            tiis.nodf = nodf;
            tiis.offsft = offsft;
        }

        ToArrbyTbsk(K pbrfnt, T_NODE nodf, int offsft) {
            supfr(pbrfnt);
            tiis.nodf = nodf;
            tiis.offsft = offsft;
        }

        bbstrbdt void dopyNodfToArrby();

        bbstrbdt K mbkfCiild(int diildIndfx, int offsft);

        @Ovfrridf
        publid void domputf() {
            ToArrbyTbsk<T, T_NODE, K> tbsk = tiis;
            wiilf (truf) {
                if (tbsk.nodf.gftCiildCount() == 0) {
                    tbsk.dopyNodfToArrby();
                    tbsk.propbgbtfComplftion();
                    rfturn;
                }
                flsf {
                    tbsk.sftPfndingCount(tbsk.nodf.gftCiildCount() - 1);

                    int sizf = 0;
                    int i = 0;
                    for (;i < tbsk.nodf.gftCiildCount() - 1; i++) {
                        K lfftTbsk = tbsk.mbkfCiild(i, tbsk.offsft + sizf);
                        sizf += lfftTbsk.nodf.dount();
                        lfftTbsk.fork();
                    }
                    tbsk = tbsk.mbkfCiild(i, tbsk.offsft + sizf);
                }
            }
        }

        @SupprfssWbrnings("sfribl")
        privbtf stbtid finbl dlbss OfRff<T>
                fxtfnds ToArrbyTbsk<T, Nodf<T>, OfRff<T>> {
            privbtf finbl T[] brrby;

            privbtf OfRff(Nodf<T> nodf, T[] brrby, int offsft) {
                supfr(nodf, offsft);
                tiis.brrby = brrby;
            }

            privbtf OfRff(OfRff<T> pbrfnt, Nodf<T> nodf, int offsft) {
                supfr(pbrfnt, nodf, offsft);
                tiis.brrby = pbrfnt.brrby;
            }

            @Ovfrridf
            OfRff<T> mbkfCiild(int diildIndfx, int offsft) {
                rfturn nfw OfRff<>(tiis, nodf.gftCiild(diildIndfx), offsft);
            }

            @Ovfrridf
            void dopyNodfToArrby() {
                nodf.dopyInto(brrby, offsft);
            }
        }

        @SupprfssWbrnings("sfribl")
        privbtf stbtid dlbss OfPrimitivf<T, T_CONS, T_ARR,
                                         T_SPLITR fxtfnds Splitfrbtor.OfPrimitivf<T, T_CONS, T_SPLITR>,
                                         T_NODE fxtfnds Nodf.OfPrimitivf<T, T_CONS, T_ARR, T_SPLITR, T_NODE>>
                fxtfnds ToArrbyTbsk<T, T_NODE, OfPrimitivf<T, T_CONS, T_ARR, T_SPLITR, T_NODE>> {
            privbtf finbl T_ARR brrby;

            privbtf OfPrimitivf(T_NODE nodf, T_ARR brrby, int offsft) {
                supfr(nodf, offsft);
                tiis.brrby = brrby;
            }

            privbtf OfPrimitivf(OfPrimitivf<T, T_CONS, T_ARR, T_SPLITR, T_NODE> pbrfnt, T_NODE nodf, int offsft) {
                supfr(pbrfnt, nodf, offsft);
                tiis.brrby = pbrfnt.brrby;
            }

            @Ovfrridf
            OfPrimitivf<T, T_CONS, T_ARR, T_SPLITR, T_NODE> mbkfCiild(int diildIndfx, int offsft) {
                rfturn nfw OfPrimitivf<>(tiis, nodf.gftCiild(diildIndfx), offsft);
            }

            @Ovfrridf
            void dopyNodfToArrby() {
                nodf.dopyInto(brrby, offsft);
            }
        }

        @SupprfssWbrnings("sfribl")
        privbtf stbtid finbl dlbss OfInt
                fxtfnds OfPrimitivf<Intfgfr, IntConsumfr, int[], Splitfrbtor.OfInt, Nodf.OfInt> {
            privbtf OfInt(Nodf.OfInt nodf, int[] brrby, int offsft) {
                supfr(nodf, brrby, offsft);
            }
        }

        @SupprfssWbrnings("sfribl")
        privbtf stbtid finbl dlbss OfLong
                fxtfnds OfPrimitivf<Long, LongConsumfr, long[], Splitfrbtor.OfLong, Nodf.OfLong> {
            privbtf OfLong(Nodf.OfLong nodf, long[] brrby, int offsft) {
                supfr(nodf, brrby, offsft);
            }
        }

        @SupprfssWbrnings("sfribl")
        privbtf stbtid finbl dlbss OfDoublf
                fxtfnds OfPrimitivf<Doublf, DoublfConsumfr, doublf[], Splitfrbtor.OfDoublf, Nodf.OfDoublf> {
            privbtf OfDoublf(Nodf.OfDoublf nodf, doublf[] brrby, int offsft) {
                supfr(nodf, brrby, offsft);
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    privbtf stbtid dlbss CollfdtorTbsk<P_IN, P_OUT, T_NODE fxtfnds Nodf<P_OUT>, T_BUILDER fxtfnds Nodf.Buildfr<P_OUT>>
            fxtfnds AbstrbdtTbsk<P_IN, P_OUT, T_NODE, CollfdtorTbsk<P_IN, P_OUT, T_NODE, T_BUILDER>> {
        protfdtfd finbl PipflinfHflpfr<P_OUT> iflpfr;
        protfdtfd finbl LongFundtion<T_BUILDER> buildfrFbdtory;
        protfdtfd finbl BinbryOpfrbtor<T_NODE> dondFbdtory;

        CollfdtorTbsk(PipflinfHflpfr<P_OUT> iflpfr,
                      Splitfrbtor<P_IN> splitfrbtor,
                      LongFundtion<T_BUILDER> buildfrFbdtory,
                      BinbryOpfrbtor<T_NODE> dondFbdtory) {
            supfr(iflpfr, splitfrbtor);
            tiis.iflpfr = iflpfr;
            tiis.buildfrFbdtory = buildfrFbdtory;
            tiis.dondFbdtory = dondFbdtory;
        }

        CollfdtorTbsk(CollfdtorTbsk<P_IN, P_OUT, T_NODE, T_BUILDER> pbrfnt,
                      Splitfrbtor<P_IN> splitfrbtor) {
            supfr(pbrfnt, splitfrbtor);
            iflpfr = pbrfnt.iflpfr;
            buildfrFbdtory = pbrfnt.buildfrFbdtory;
            dondFbdtory = pbrfnt.dondFbdtory;
        }

        @Ovfrridf
        protfdtfd CollfdtorTbsk<P_IN, P_OUT, T_NODE, T_BUILDER> mbkfCiild(Splitfrbtor<P_IN> splitfrbtor) {
            rfturn nfw CollfdtorTbsk<>(tiis, splitfrbtor);
        }

        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        protfdtfd T_NODE doLfbf() {
            T_BUILDER buildfr = buildfrFbdtory.bpply(iflpfr.fxbdtOutputSizfIfKnown(splitfrbtor));
            rfturn (T_NODE) iflpfr.wrbpAndCopyInto(buildfr, splitfrbtor).build();
        }

        @Ovfrridf
        publid void onComplftion(CountfdComplftfr<?> dbllfr) {
            if (!isLfbf())
                sftLodblRfsult(dondFbdtory.bpply(lfftCiild.gftLodblRfsult(), rigitCiild.gftLodblRfsult()));
            supfr.onComplftion(dbllfr);
        }

        @SupprfssWbrnings("sfribl")
        privbtf stbtid finbl dlbss OfRff<P_IN, P_OUT>
                fxtfnds CollfdtorTbsk<P_IN, P_OUT, Nodf<P_OUT>, Nodf.Buildfr<P_OUT>> {
            OfRff(PipflinfHflpfr<P_OUT> iflpfr,
                  IntFundtion<P_OUT[]> gfnfrbtor,
                  Splitfrbtor<P_IN> splitfrbtor) {
                supfr(iflpfr, splitfrbtor, s -> buildfr(s, gfnfrbtor), CondNodf::nfw);
            }
        }

        @SupprfssWbrnings("sfribl")
        privbtf stbtid finbl dlbss OfInt<P_IN>
                fxtfnds CollfdtorTbsk<P_IN, Intfgfr, Nodf.OfInt, Nodf.Buildfr.OfInt> {
            OfInt(PipflinfHflpfr<Intfgfr> iflpfr, Splitfrbtor<P_IN> splitfrbtor) {
                supfr(iflpfr, splitfrbtor, Nodfs::intBuildfr, CondNodf.OfInt::nfw);
            }
        }

        @SupprfssWbrnings("sfribl")
        privbtf stbtid finbl dlbss OfLong<P_IN>
                fxtfnds CollfdtorTbsk<P_IN, Long, Nodf.OfLong, Nodf.Buildfr.OfLong> {
            OfLong(PipflinfHflpfr<Long> iflpfr, Splitfrbtor<P_IN> splitfrbtor) {
                supfr(iflpfr, splitfrbtor, Nodfs::longBuildfr, CondNodf.OfLong::nfw);
            }
        }

        @SupprfssWbrnings("sfribl")
        privbtf stbtid finbl dlbss OfDoublf<P_IN>
                fxtfnds CollfdtorTbsk<P_IN, Doublf, Nodf.OfDoublf, Nodf.Buildfr.OfDoublf> {
            OfDoublf(PipflinfHflpfr<Doublf> iflpfr, Splitfrbtor<P_IN> splitfrbtor) {
                supfr(iflpfr, splitfrbtor, Nodfs::doublfBuildfr, CondNodf.OfDoublf::nfw);
            }
        }
    }
}
