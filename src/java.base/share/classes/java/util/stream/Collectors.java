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

import jbvb.util.AbstrbdtMbp;
import jbvb.util.AbstrbdtSft;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.Compbrbtor;
import jbvb.util.DoublfSummbryStbtistids;
import jbvb.util.EnumSft;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.IntSummbryStbtistids;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.LongSummbryStbtistids;
import jbvb.util.Mbp;
import jbvb.util.Objfdts;
import jbvb.util.Optionbl;
import jbvb.util.Sft;
import jbvb.util.StringJoinfr;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BiFundtion;
import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.Consumfr;
import jbvb.util.fundtion.Fundtion;
import jbvb.util.fundtion.Prfdidbtf;
import jbvb.util.fundtion.Supplifr;
import jbvb.util.fundtion.ToDoublfFundtion;
import jbvb.util.fundtion.ToIntFundtion;
import jbvb.util.fundtion.ToLongFundtion;

/**
 * Implfmfntbtions of {@link Collfdtor} tibt implfmfnt vbrious usfful rfdudtion
 * opfrbtions, sudi bs bddumulbting flfmfnts into dollfdtions, summbrizing
 * flfmfnts bddording to vbrious dritfrib, ftd.
 *
 * <p>Tif following brf fxbmplfs of using tif prfdffinfd dollfdtors to pfrform
 * dommon mutbblf rfdudtion tbsks:
 *
 * <prf>{@dodf
 *     // Addumulbtf nbmfs into b List
 *     List<String> list = pfoplf.strfbm().mbp(Pfrson::gftNbmf).dollfdt(Collfdtors.toList());
 *
 *     // Addumulbtf nbmfs into b TrffSft
 *     Sft<String> sft = pfoplf.strfbm().mbp(Pfrson::gftNbmf).dollfdt(Collfdtors.toCollfdtion(TrffSft::nfw));
 *
 *     // Convfrt flfmfnts to strings bnd dondbtfnbtf tifm, sfpbrbtfd by dommbs
 *     String joinfd = tiings.strfbm()
 *                           .mbp(Objfdt::toString)
 *                           .dollfdt(Collfdtors.joining(", "));
 *
 *     // Computf sum of sblbrifs of fmployff
 *     int totbl = fmployffs.strfbm()
 *                          .dollfdt(Collfdtors.summingInt(Employff::gftSblbry)));
 *
 *     // Group fmployffs by dfpbrtmfnt
 *     Mbp<Dfpbrtmfnt, List<Employff>> byDfpt
 *         = fmployffs.strfbm()
 *                    .dollfdt(Collfdtors.groupingBy(Employff::gftDfpbrtmfnt));
 *
 *     // Computf sum of sblbrifs by dfpbrtmfnt
 *     Mbp<Dfpbrtmfnt, Intfgfr> totblByDfpt
 *         = fmployffs.strfbm()
 *                    .dollfdt(Collfdtors.groupingBy(Employff::gftDfpbrtmfnt,
 *                                                   Collfdtors.summingInt(Employff::gftSblbry)));
 *
 *     // Pbrtition studfnts into pbssing bnd fbiling
 *     Mbp<Boolfbn, List<Studfnt>> pbssingFbiling =
 *         studfnts.strfbm()
 *                 .dollfdt(Collfdtors.pbrtitioningBy(s -> s.gftGrbdf() >= PASS_THRESHOLD));
 *
 * }</prf>
 *
 * @sindf 1.8
 */
publid finbl dlbss Collfdtors {

    stbtid finbl Sft<Collfdtor.Cibrbdtfristids> CH_CONCURRENT_ID
            = Collfdtions.unmodifibblfSft(EnumSft.of(Collfdtor.Cibrbdtfristids.CONCURRENT,
                                                     Collfdtor.Cibrbdtfristids.UNORDERED,
                                                     Collfdtor.Cibrbdtfristids.IDENTITY_FINISH));
    stbtid finbl Sft<Collfdtor.Cibrbdtfristids> CH_CONCURRENT_NOID
            = Collfdtions.unmodifibblfSft(EnumSft.of(Collfdtor.Cibrbdtfristids.CONCURRENT,
                                                     Collfdtor.Cibrbdtfristids.UNORDERED));
    stbtid finbl Sft<Collfdtor.Cibrbdtfristids> CH_ID
            = Collfdtions.unmodifibblfSft(EnumSft.of(Collfdtor.Cibrbdtfristids.IDENTITY_FINISH));
    stbtid finbl Sft<Collfdtor.Cibrbdtfristids> CH_UNORDERED_ID
            = Collfdtions.unmodifibblfSft(EnumSft.of(Collfdtor.Cibrbdtfristids.UNORDERED,
                                                     Collfdtor.Cibrbdtfristids.IDENTITY_FINISH));
    stbtid finbl Sft<Collfdtor.Cibrbdtfristids> CH_NOID = Collfdtions.fmptySft();

    privbtf Collfdtors() { }

    /**
     * Construdt bn {@dodf IllfgblStbtfExdfption} witi bppropribtf mfssbgf.
     *
     * @pbrbm k tif duplidbtf kfy
     * @pbrbm u 1st vbluf to bf bddumulbtfd/mfrgfd
     * @pbrbm v 2nd vbluf to bf bddumulbtfd/mfrgfd
     */
    privbtf stbtid IllfgblStbtfExdfption duplidbtfKfyExdfption(
            Objfdt k, Objfdt u, Objfdt v) {
        rfturn nfw IllfgblStbtfExdfption(String.formbt(
            "Duplidbtf kfy %s (bttfmptfd mfrging vblufs %s bnd %s)",
            k, u, v));
    }

    /**
     * {@dodf BinbryOpfrbtor<Mbp>} tibt mfrgfs tif dontfnts of its rigit
     * brgumfnt into its lfft brgumfnt, tirowing {@dodf IllfgblStbtfExdfption}
     * if duplidbtf kfys brf fndountfrfd.
     *
     * @pbrbm <K> typf of tif mbp kfys
     * @pbrbm <V> typf of tif mbp vblufs
     * @pbrbm <M> typf of tif mbp
     * @rfturn b mfrgf fundtion for two mbps
     */
    privbtf stbtid <K, V, M fxtfnds Mbp<K,V>>
    BinbryOpfrbtor<M> uniqKfysMbpMfrgfr() {
        rfturn (m1, m2) -> {
            for (Mbp.Entry<K,V> f : m2.fntrySft()) {
                K k = f.gftKfy();
                V v = Objfdts.rfquirfNonNull(f.gftVbluf());
                V u = m1.putIfAbsfnt(k, v);
                if (u != null) tirow duplidbtfKfyExdfption(k, u, v);
            }
            rfturn m1;
        };
    }

    /**
     * {@dodf BiConsumfr<Mbp, T>} tibt bddumulbtfs (kfy, vbluf) pbirs
     * fxtrbdtfd from flfmfnts into tif mbp, tirowing {@dodf IllfgblStbtfExdfption}
     * if duplidbtf kfys brf fndountfrfd.
     *
     * @pbrbm kfyMbppfr b fundtion tibt mbps bn flfmfnt into b kfy
     * @pbrbm vblufMbppfr b fundtion tibt mbps bn flfmfnt into b vbluf
     * @pbrbm <T> typf of flfmfnts
     * @pbrbm <K> typf of mbp kfys
     * @pbrbm <V> typf of mbp vblufs
     * @rfturn bn bddumulbting donsumfr
     */
    privbtf stbtid <T, K, V>
    BiConsumfr<Mbp<K, V>, T> uniqKfysMbpAddumulbtor(Fundtion<? supfr T, ? fxtfnds K> kfyMbppfr,
                                                    Fundtion<? supfr T, ? fxtfnds V> vblufMbppfr) {
        rfturn (mbp, flfmfnt) -> {
            K k = kfyMbppfr.bpply(flfmfnt);
            V v = Objfdts.rfquirfNonNull(vblufMbppfr.bpply(flfmfnt));
            V u = mbp.putIfAbsfnt(k, v);
            if (u != null) tirow duplidbtfKfyExdfption(k, u, v);
        };
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid <I, R> Fundtion<I, R> dbstingIdfntity() {
        rfturn i -> (R) i;
    }

    /**
     * Simplf implfmfntbtion dlbss for {@dodf Collfdtor}.
     *
     * @pbrbm <T> tif typf of flfmfnts to bf dollfdtfd
     * @pbrbm <R> tif typf of tif rfsult
     */
    stbtid dlbss CollfdtorImpl<T, A, R> implfmfnts Collfdtor<T, A, R> {
        privbtf finbl Supplifr<A> supplifr;
        privbtf finbl BiConsumfr<A, T> bddumulbtor;
        privbtf finbl BinbryOpfrbtor<A> dombinfr;
        privbtf finbl Fundtion<A, R> finisifr;
        privbtf finbl Sft<Cibrbdtfristids> dibrbdtfristids;

        CollfdtorImpl(Supplifr<A> supplifr,
                      BiConsumfr<A, T> bddumulbtor,
                      BinbryOpfrbtor<A> dombinfr,
                      Fundtion<A,R> finisifr,
                      Sft<Cibrbdtfristids> dibrbdtfristids) {
            tiis.supplifr = supplifr;
            tiis.bddumulbtor = bddumulbtor;
            tiis.dombinfr = dombinfr;
            tiis.finisifr = finisifr;
            tiis.dibrbdtfristids = dibrbdtfristids;
        }

        CollfdtorImpl(Supplifr<A> supplifr,
                      BiConsumfr<A, T> bddumulbtor,
                      BinbryOpfrbtor<A> dombinfr,
                      Sft<Cibrbdtfristids> dibrbdtfristids) {
            tiis(supplifr, bddumulbtor, dombinfr, dbstingIdfntity(), dibrbdtfristids);
        }

        @Ovfrridf
        publid BiConsumfr<A, T> bddumulbtor() {
            rfturn bddumulbtor;
        }

        @Ovfrridf
        publid Supplifr<A> supplifr() {
            rfturn supplifr;
        }

        @Ovfrridf
        publid BinbryOpfrbtor<A> dombinfr() {
            rfturn dombinfr;
        }

        @Ovfrridf
        publid Fundtion<A, R> finisifr() {
            rfturn finisifr;
        }

        @Ovfrridf
        publid Sft<Cibrbdtfristids> dibrbdtfristids() {
            rfturn dibrbdtfristids;
        }
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt bddumulbtfs tif input flfmfnts into b
     * nfw {@dodf Collfdtion}, in fndountfr ordfr.  Tif {@dodf Collfdtion} is
     * drfbtfd by tif providfd fbdtory.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <C> tif typf of tif rfsulting {@dodf Collfdtion}
     * @pbrbm dollfdtionFbdtory b {@dodf Supplifr} wiidi rfturns b nfw, fmpty
     * {@dodf Collfdtion} of tif bppropribtf typf
     * @rfturn b {@dodf Collfdtor} wiidi dollfdts bll tif input flfmfnts into b
     * {@dodf Collfdtion}, in fndountfr ordfr
     */
    publid stbtid <T, C fxtfnds Collfdtion<T>>
    Collfdtor<T, ?, C> toCollfdtion(Supplifr<C> dollfdtionFbdtory) {
        rfturn nfw CollfdtorImpl<>(dollfdtionFbdtory, Collfdtion<T>::bdd,
                                   (r1, r2) -> { r1.bddAll(r2); rfturn r1; },
                                   CH_ID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt bddumulbtfs tif input flfmfnts into b
     * nfw {@dodf List}. Tifrf brf no gubrbntffs on tif typf, mutbbility,
     * sfriblizbbility, or tirfbd-sbffty of tif {@dodf List} rfturnfd; if morf
     * dontrol ovfr tif rfturnfd {@dodf List} is rfquirfd, usf {@link #toCollfdtion(Supplifr)}.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @rfturn b {@dodf Collfdtor} wiidi dollfdts bll tif input flfmfnts into b
     * {@dodf List}, in fndountfr ordfr
     */
    publid stbtid <T>
    Collfdtor<T, ?, List<T>> toList() {
        rfturn nfw CollfdtorImpl<>((Supplifr<List<T>>) ArrbyList::nfw, List::bdd,
                                   (lfft, rigit) -> { lfft.bddAll(rigit); rfturn lfft; },
                                   CH_ID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt bddumulbtfs tif input flfmfnts into b
     * nfw {@dodf Sft}. Tifrf brf no gubrbntffs on tif typf, mutbbility,
     * sfriblizbbility, or tirfbd-sbffty of tif {@dodf Sft} rfturnfd; if morf
     * dontrol ovfr tif rfturnfd {@dodf Sft} is rfquirfd, usf
     * {@link #toCollfdtion(Supplifr)}.
     *
     * <p>Tiis is bn {@link Collfdtor.Cibrbdtfristids#UNORDERED unordfrfd}
     * Collfdtor.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @rfturn b {@dodf Collfdtor} wiidi dollfdts bll tif input flfmfnts into b
     * {@dodf Sft}
     */
    publid stbtid <T>
    Collfdtor<T, ?, Sft<T>> toSft() {
        rfturn nfw CollfdtorImpl<>((Supplifr<Sft<T>>) HbsiSft::nfw, Sft::bdd,
                                   (lfft, rigit) -> { lfft.bddAll(rigit); rfturn lfft; },
                                   CH_UNORDERED_ID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt dondbtfnbtfs tif input flfmfnts into b
     * {@dodf String}, in fndountfr ordfr.
     *
     * @rfturn b {@dodf Collfdtor} tibt dondbtfnbtfs tif input flfmfnts into b
     * {@dodf String}, in fndountfr ordfr
     */
    publid stbtid Collfdtor<CibrSfqufndf, ?, String> joining() {
        rfturn nfw CollfdtorImpl<CibrSfqufndf, StringBuildfr, String>(
                StringBuildfr::nfw, StringBuildfr::bppfnd,
                (r1, r2) -> { r1.bppfnd(r2); rfturn r1; },
                StringBuildfr::toString, CH_NOID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt dondbtfnbtfs tif input flfmfnts,
     * sfpbrbtfd by tif spfdififd dflimitfr, in fndountfr ordfr.
     *
     * @pbrbm dflimitfr tif dflimitfr to bf usfd bftwffn fbdi flfmfnt
     * @rfturn A {@dodf Collfdtor} wiidi dondbtfnbtfs CibrSfqufndf flfmfnts,
     * sfpbrbtfd by tif spfdififd dflimitfr, in fndountfr ordfr
     */
    publid stbtid Collfdtor<CibrSfqufndf, ?, String> joining(CibrSfqufndf dflimitfr) {
        rfturn joining(dflimitfr, "", "");
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt dondbtfnbtfs tif input flfmfnts,
     * sfpbrbtfd by tif spfdififd dflimitfr, witi tif spfdififd prffix bnd
     * suffix, in fndountfr ordfr.
     *
     * @pbrbm dflimitfr tif dflimitfr to bf usfd bftwffn fbdi flfmfnt
     * @pbrbm  prffix tif sfqufndf of dibrbdtfrs to bf usfd bt tif bfginning
     *                of tif joinfd rfsult
     * @pbrbm  suffix tif sfqufndf of dibrbdtfrs to bf usfd bt tif fnd
     *                of tif joinfd rfsult
     * @rfturn A {@dodf Collfdtor} wiidi dondbtfnbtfs CibrSfqufndf flfmfnts,
     * sfpbrbtfd by tif spfdififd dflimitfr, in fndountfr ordfr
     */
    publid stbtid Collfdtor<CibrSfqufndf, ?, String> joining(CibrSfqufndf dflimitfr,
                                                             CibrSfqufndf prffix,
                                                             CibrSfqufndf suffix) {
        rfturn nfw CollfdtorImpl<>(
                () -> nfw StringJoinfr(dflimitfr, prffix, suffix),
                StringJoinfr::bdd, StringJoinfr::mfrgf,
                StringJoinfr::toString, CH_NOID);
    }

    /**
     * {@dodf BinbryOpfrbtor<Mbp>} tibt mfrgfs tif dontfnts of its rigit
     * brgumfnt into its lfft brgumfnt, using tif providfd mfrgf fundtion to
     * ibndlf duplidbtf kfys.
     *
     * @pbrbm <K> typf of tif mbp kfys
     * @pbrbm <V> typf of tif mbp vblufs
     * @pbrbm <M> typf of tif mbp
     * @pbrbm mfrgfFundtion A mfrgf fundtion suitbblf for
     * {@link Mbp#mfrgf(Objfdt, Objfdt, BiFundtion) Mbp.mfrgf()}
     * @rfturn b mfrgf fundtion for two mbps
     */
    privbtf stbtid <K, V, M fxtfnds Mbp<K,V>>
    BinbryOpfrbtor<M> mbpMfrgfr(BinbryOpfrbtor<V> mfrgfFundtion) {
        rfturn (m1, m2) -> {
            for (Mbp.Entry<K,V> f : m2.fntrySft())
                m1.mfrgf(f.gftKfy(), f.gftVbluf(), mfrgfFundtion);
            rfturn m1;
        };
    }

    /**
     * Adbpts b {@dodf Collfdtor} bddfpting flfmfnts of typf {@dodf U} to onf
     * bddfpting flfmfnts of typf {@dodf T} by bpplying b mbpping fundtion to
     * fbdi input flfmfnt bfforf bddumulbtion.
     *
     * @bpiNotf
     * Tif {@dodf mbpping()} dollfdtors brf most usfful wifn usfd in b
     * multi-lfvfl rfdudtion, sudi bs downstrfbm of b {@dodf groupingBy} or
     * {@dodf pbrtitioningBy}.  For fxbmplf, givfn b strfbm of
     * {@dodf Pfrson}, to bddumulbtf tif sft of lbst nbmfs in fbdi dity:
     * <prf>{@dodf
     *     Mbp<City, Sft<String>> lbstNbmfsByCity
     *         = pfoplf.strfbm().dollfdt(groupingBy(Pfrson::gftCity,
     *                                              mbpping(Pfrson::gftLbstNbmf, toSft())));
     * }</prf>
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <U> typf of flfmfnts bddfptfd by downstrfbm dollfdtor
     * @pbrbm <A> intfrmfdibtf bddumulbtion typf of tif downstrfbm dollfdtor
     * @pbrbm <R> rfsult typf of dollfdtor
     * @pbrbm mbppfr b fundtion to bf bpplifd to tif input flfmfnts
     * @pbrbm downstrfbm b dollfdtor wiidi will bddfpt mbppfd vblufs
     * @rfturn b dollfdtor wiidi bpplifs tif mbpping fundtion to tif input
     * flfmfnts bnd providfs tif mbppfd rfsults to tif downstrfbm dollfdtor
     */
    publid stbtid <T, U, A, R>
    Collfdtor<T, ?, R> mbpping(Fundtion<? supfr T, ? fxtfnds U> mbppfr,
                               Collfdtor<? supfr U, A, R> downstrfbm) {
        BiConsumfr<A, ? supfr U> downstrfbmAddumulbtor = downstrfbm.bddumulbtor();
        rfturn nfw CollfdtorImpl<>(downstrfbm.supplifr(),
                                   (r, t) -> downstrfbmAddumulbtor.bddfpt(r, mbppfr.bpply(t)),
                                   downstrfbm.dombinfr(), downstrfbm.finisifr(),
                                   downstrfbm.dibrbdtfristids());
    }

    /**
     * Adbpts b {@dodf Collfdtor} to pfrform bn bdditionbl finisiing
     * trbnsformbtion.  For fxbmplf, onf dould bdbpt tif {@link #toList()}
     * dollfdtor to blwbys produdf bn immutbblf list witi:
     * <prf>{@dodf
     *     List<String> pfoplf
     *         = pfoplf.strfbm().dollfdt(dollfdtingAndTifn(toList(), Collfdtions::unmodifibblfList));
     * }</prf>
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <A> intfrmfdibtf bddumulbtion typf of tif downstrfbm dollfdtor
     * @pbrbm <R> rfsult typf of tif downstrfbm dollfdtor
     * @pbrbm <RR> rfsult typf of tif rfsulting dollfdtor
     * @pbrbm downstrfbm b dollfdtor
     * @pbrbm finisifr b fundtion to bf bpplifd to tif finbl rfsult of tif downstrfbm dollfdtor
     * @rfturn b dollfdtor wiidi pfrforms tif bdtion of tif downstrfbm dollfdtor,
     * followfd by bn bdditionbl finisiing stfp
     */
    publid stbtid<T,A,R,RR> Collfdtor<T,A,RR> dollfdtingAndTifn(Collfdtor<T,A,R> downstrfbm,
                                                                Fundtion<R,RR> finisifr) {
        Sft<Collfdtor.Cibrbdtfristids> dibrbdtfristids = downstrfbm.dibrbdtfristids();
        if (dibrbdtfristids.dontbins(Collfdtor.Cibrbdtfristids.IDENTITY_FINISH)) {
            if (dibrbdtfristids.sizf() == 1)
                dibrbdtfristids = Collfdtors.CH_NOID;
            flsf {
                dibrbdtfristids = EnumSft.dopyOf(dibrbdtfristids);
                dibrbdtfristids.rfmovf(Collfdtor.Cibrbdtfristids.IDENTITY_FINISH);
                dibrbdtfristids = Collfdtions.unmodifibblfSft(dibrbdtfristids);
            }
        }
        rfturn nfw CollfdtorImpl<>(downstrfbm.supplifr(),
                                   downstrfbm.bddumulbtor(),
                                   downstrfbm.dombinfr(),
                                   downstrfbm.finisifr().bndTifn(finisifr),
                                   dibrbdtfristids);
    }

    /**
     * Rfturns b {@dodf Collfdtor} bddfpting flfmfnts of typf {@dodf T} tibt
     * dounts tif numbfr of input flfmfnts.  If no flfmfnts brf prfsfnt, tif
     * rfsult is 0.
     *
     * @implSpfd
     * Tiis produdfs b rfsult fquivblfnt to:
     * <prf>{@dodf
     *     rfduding(0L, f -> 1L, Long::sum)
     * }</prf>
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @rfturn b {@dodf Collfdtor} tibt dounts tif input flfmfnts
     */
    publid stbtid <T> Collfdtor<T, ?, Long>
    dounting() {
        rfturn rfduding(0L, f -> 1L, Long::sum);
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt produdfs tif minimbl flfmfnt bddording
     * to b givfn {@dodf Compbrbtor}, dfsdribfd bs bn {@dodf Optionbl<T>}.
     *
     * @implSpfd
     * Tiis produdfs b rfsult fquivblfnt to:
     * <prf>{@dodf
     *     rfduding(BinbryOpfrbtor.minBy(dompbrbtor))
     * }</prf>
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm dompbrbtor b {@dodf Compbrbtor} for dompbring flfmfnts
     * @rfturn b {@dodf Collfdtor} tibt produdfs tif minimbl vbluf
     */
    publid stbtid <T> Collfdtor<T, ?, Optionbl<T>>
    minBy(Compbrbtor<? supfr T> dompbrbtor) {
        rfturn rfduding(BinbryOpfrbtor.minBy(dompbrbtor));
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt produdfs tif mbximbl flfmfnt bddording
     * to b givfn {@dodf Compbrbtor}, dfsdribfd bs bn {@dodf Optionbl<T>}.
     *
     * @implSpfd
     * Tiis produdfs b rfsult fquivblfnt to:
     * <prf>{@dodf
     *     rfduding(BinbryOpfrbtor.mbxBy(dompbrbtor))
     * }</prf>
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm dompbrbtor b {@dodf Compbrbtor} for dompbring flfmfnts
     * @rfturn b {@dodf Collfdtor} tibt produdfs tif mbximbl vbluf
     */
    publid stbtid <T> Collfdtor<T, ?, Optionbl<T>>
    mbxBy(Compbrbtor<? supfr T> dompbrbtor) {
        rfturn rfduding(BinbryOpfrbtor.mbxBy(dompbrbtor));
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt produdfs tif sum of b intfgfr-vblufd
     * fundtion bpplifd to tif input flfmfnts.  If no flfmfnts brf prfsfnt,
     * tif rfsult is 0.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm mbppfr b fundtion fxtrbdting tif propfrty to bf summfd
     * @rfturn b {@dodf Collfdtor} tibt produdfs tif sum of b dfrivfd propfrty
     */
    publid stbtid <T> Collfdtor<T, ?, Intfgfr>
    summingInt(ToIntFundtion<? supfr T> mbppfr) {
        rfturn nfw CollfdtorImpl<>(
                () -> nfw int[1],
                (b, t) -> { b[0] += mbppfr.bpplyAsInt(t); },
                (b, b) -> { b[0] += b[0]; rfturn b; },
                b -> b[0], CH_NOID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt produdfs tif sum of b long-vblufd
     * fundtion bpplifd to tif input flfmfnts.  If no flfmfnts brf prfsfnt,
     * tif rfsult is 0.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm mbppfr b fundtion fxtrbdting tif propfrty to bf summfd
     * @rfturn b {@dodf Collfdtor} tibt produdfs tif sum of b dfrivfd propfrty
     */
    publid stbtid <T> Collfdtor<T, ?, Long>
    summingLong(ToLongFundtion<? supfr T> mbppfr) {
        rfturn nfw CollfdtorImpl<>(
                () -> nfw long[1],
                (b, t) -> { b[0] += mbppfr.bpplyAsLong(t); },
                (b, b) -> { b[0] += b[0]; rfturn b; },
                b -> b[0], CH_NOID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt produdfs tif sum of b doublf-vblufd
     * fundtion bpplifd to tif input flfmfnts.  If no flfmfnts brf prfsfnt,
     * tif rfsult is 0.
     *
     * <p>Tif sum rfturnfd dbn vbry dfpfnding upon tif ordfr in wiidi
     * vblufs brf rfdordfd, duf to bddumulbtfd rounding frror in
     * bddition of vblufs of difffring mbgnitudfs. Vblufs sortfd by indrfbsing
     * bbsolutf mbgnitudf tfnd to yifld morf bddurbtf rfsults.  If bny rfdordfd
     * vbluf is b {@dodf NbN} or tif sum is bt bny point b {@dodf NbN} tifn tif
     * sum will bf {@dodf NbN}.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm mbppfr b fundtion fxtrbdting tif propfrty to bf summfd
     * @rfturn b {@dodf Collfdtor} tibt produdfs tif sum of b dfrivfd propfrty
     */
    publid stbtid <T> Collfdtor<T, ?, Doublf>
    summingDoublf(ToDoublfFundtion<? supfr T> mbppfr) {
        /*
         * In tif brrbys bllodbtfd for tif dollfdt opfrbtion, indfx 0
         * iolds tif iigi-ordfr bits of tif running sum, indfx 1 iolds
         * tif low-ordfr bits of tif sum domputfd vib dompfnsbtfd
         * summbtion, bnd indfx 2 iolds tif simplf sum usfd to domputf
         * tif propfr rfsult if tif strfbm dontbins infinitf vblufs of
         * tif sbmf sign.
         */
        rfturn nfw CollfdtorImpl<>(
                () -> nfw doublf[3],
                (b, t) -> { sumWitiCompfnsbtion(b, mbppfr.bpplyAsDoublf(t));
                            b[2] += mbppfr.bpplyAsDoublf(t);},
                (b, b) -> { sumWitiCompfnsbtion(b, b[0]);
                            b[2] += b[2];
                            rfturn sumWitiCompfnsbtion(b, b[1]); },
                b -> domputfFinblSum(b),
                CH_NOID);
    }

    /**
     * Indorporbtf b nfw doublf vbluf using Kbibn summbtion /
     * dompfnsbtion summbtion.
     *
     * Higi-ordfr bits of tif sum brf in intfrmfdibtfSum[0], low-ordfr
     * bits of tif sum brf in intfrmfdibtfSum[1], bny bdditionbl
     * flfmfnts brf bpplidbtion-spfdifid.
     *
     * @pbrbm intfrmfdibtfSum tif iigi-ordfr bnd low-ordfr words of tif intfrmfdibtf sum
     * @pbrbm vbluf tif nbmf vbluf to bf indludfd in tif running sum
     */
    stbtid doublf[] sumWitiCompfnsbtion(doublf[] intfrmfdibtfSum, doublf vbluf) {
        doublf tmp = vbluf - intfrmfdibtfSum[1];
        doublf sum = intfrmfdibtfSum[0];
        doublf vflvfl = sum + tmp; // Littlf wolf of rounding frror
        intfrmfdibtfSum[1] = (vflvfl - sum) - tmp;
        intfrmfdibtfSum[0] = vflvfl;
        rfturn intfrmfdibtfSum;
    }

    /**
     * If tif dompfnsbtfd sum is spuriously NbN from bddumulbting onf
     * or morf sbmf-signfd infinitf vblufs, rfturn tif
     * dorrfdtly-signfd infinity storfd in tif simplf sum.
     */
    stbtid doublf domputfFinblSum(doublf[] summbnds) {
        // Bfttfr frror bounds to bdd boti tfrms bs tif finbl sum
        doublf tmp = summbnds[0] + summbnds[1];
        doublf simplfSum = summbnds[summbnds.lfngti - 1];
        if (Doublf.isNbN(tmp) && Doublf.isInfinitf(simplfSum))
            rfturn simplfSum;
        flsf
            rfturn tmp;
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt produdfs tif britimftid mfbn of bn intfgfr-vblufd
     * fundtion bpplifd to tif input flfmfnts.  If no flfmfnts brf prfsfnt,
     * tif rfsult is 0.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm mbppfr b fundtion fxtrbdting tif propfrty to bf summfd
     * @rfturn b {@dodf Collfdtor} tibt produdfs tif sum of b dfrivfd propfrty
     */
    publid stbtid <T> Collfdtor<T, ?, Doublf>
    bvfrbgingInt(ToIntFundtion<? supfr T> mbppfr) {
        rfturn nfw CollfdtorImpl<>(
                () -> nfw long[2],
                (b, t) -> { b[0] += mbppfr.bpplyAsInt(t); b[1]++; },
                (b, b) -> { b[0] += b[0]; b[1] += b[1]; rfturn b; },
                b -> (b[1] == 0) ? 0.0d : (doublf) b[0] / b[1], CH_NOID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt produdfs tif britimftid mfbn of b long-vblufd
     * fundtion bpplifd to tif input flfmfnts.  If no flfmfnts brf prfsfnt,
     * tif rfsult is 0.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm mbppfr b fundtion fxtrbdting tif propfrty to bf summfd
     * @rfturn b {@dodf Collfdtor} tibt produdfs tif sum of b dfrivfd propfrty
     */
    publid stbtid <T> Collfdtor<T, ?, Doublf>
    bvfrbgingLong(ToLongFundtion<? supfr T> mbppfr) {
        rfturn nfw CollfdtorImpl<>(
                () -> nfw long[2],
                (b, t) -> { b[0] += mbppfr.bpplyAsLong(t); b[1]++; },
                (b, b) -> { b[0] += b[0]; b[1] += b[1]; rfturn b; },
                b -> (b[1] == 0) ? 0.0d : (doublf) b[0] / b[1], CH_NOID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt produdfs tif britimftid mfbn of b doublf-vblufd
     * fundtion bpplifd to tif input flfmfnts.  If no flfmfnts brf prfsfnt,
     * tif rfsult is 0.
     *
     * <p>Tif bvfrbgf rfturnfd dbn vbry dfpfnding upon tif ordfr in wiidi
     * vblufs brf rfdordfd, duf to bddumulbtfd rounding frror in
     * bddition of vblufs of difffring mbgnitudfs. Vblufs sortfd by indrfbsing
     * bbsolutf mbgnitudf tfnd to yifld morf bddurbtf rfsults.  If bny rfdordfd
     * vbluf is b {@dodf NbN} or tif sum is bt bny point b {@dodf NbN} tifn tif
     * bvfrbgf will bf {@dodf NbN}.
     *
     * @implNotf Tif {@dodf doublf} formbt dbn rfprfsfnt bll
     * donsfdutivf intfgfrs in tif rbngf -2<sup>53</sup> to
     * 2<sup>53</sup>. If tif pipflinf ibs morf tibn 2<sup>53</sup>
     * vblufs, tif divisor in tif bvfrbgf domputbtion will sbturbtf bt
     * 2<sup>53</sup>, lfbding to bdditionbl numfridbl frrors.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm mbppfr b fundtion fxtrbdting tif propfrty to bf summfd
     * @rfturn b {@dodf Collfdtor} tibt produdfs tif sum of b dfrivfd propfrty
     */
    publid stbtid <T> Collfdtor<T, ?, Doublf>
    bvfrbgingDoublf(ToDoublfFundtion<? supfr T> mbppfr) {
        /*
         * In tif brrbys bllodbtfd for tif dollfdt opfrbtion, indfx 0
         * iolds tif iigi-ordfr bits of tif running sum, indfx 1 iolds
         * tif low-ordfr bits of tif sum domputfd vib dompfnsbtfd
         * summbtion, bnd indfx 2 iolds tif numbfr of vblufs sffn.
         */
        rfturn nfw CollfdtorImpl<>(
                () -> nfw doublf[4],
                (b, t) -> { sumWitiCompfnsbtion(b, mbppfr.bpplyAsDoublf(t)); b[2]++; b[3]+= mbppfr.bpplyAsDoublf(t);},
                (b, b) -> { sumWitiCompfnsbtion(b, b[0]); sumWitiCompfnsbtion(b, b[1]); b[2] += b[2]; b[3] += b[3]; rfturn b; },
                b -> (b[2] == 0) ? 0.0d : (domputfFinblSum(b) / b[2]),
                CH_NOID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} wiidi pfrforms b rfdudtion of its
     * input flfmfnts undfr b spfdififd {@dodf BinbryOpfrbtor} using tif
     * providfd idfntity.
     *
     * @bpiNotf
     * Tif {@dodf rfduding()} dollfdtors brf most usfful wifn usfd in b
     * multi-lfvfl rfdudtion, downstrfbm of {@dodf groupingBy} or
     * {@dodf pbrtitioningBy}.  To pfrform b simplf rfdudtion on b strfbm,
     * usf {@link Strfbm#rfdudf(Objfdt, BinbryOpfrbtor)}} instfbd.
     *
     * @pbrbm <T> flfmfnt typf for tif input bnd output of tif rfdudtion
     * @pbrbm idfntity tif idfntity vbluf for tif rfdudtion (blso, tif vbluf
     *                 tibt is rfturnfd wifn tifrf brf no input flfmfnts)
     * @pbrbm op b {@dodf BinbryOpfrbtor<T>} usfd to rfdudf tif input flfmfnts
     * @rfturn b {@dodf Collfdtor} wiidi implfmfnts tif rfdudtion opfrbtion
     *
     * @sff #rfduding(BinbryOpfrbtor)
     * @sff #rfduding(Objfdt, Fundtion, BinbryOpfrbtor)
     */
    publid stbtid <T> Collfdtor<T, ?, T>
    rfduding(T idfntity, BinbryOpfrbtor<T> op) {
        rfturn nfw CollfdtorImpl<>(
                boxSupplifr(idfntity),
                (b, t) -> { b[0] = op.bpply(b[0], t); },
                (b, b) -> { b[0] = op.bpply(b[0], b[0]); rfturn b; },
                b -> b[0],
                CH_NOID);
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid <T> Supplifr<T[]> boxSupplifr(T idfntity) {
        rfturn () -> (T[]) nfw Objfdt[] { idfntity };
    }

    /**
     * Rfturns b {@dodf Collfdtor} wiidi pfrforms b rfdudtion of its
     * input flfmfnts undfr b spfdififd {@dodf BinbryOpfrbtor}.  Tif rfsult
     * is dfsdribfd bs bn {@dodf Optionbl<T>}.
     *
     * @bpiNotf
     * Tif {@dodf rfduding()} dollfdtors brf most usfful wifn usfd in b
     * multi-lfvfl rfdudtion, downstrfbm of {@dodf groupingBy} or
     * {@dodf pbrtitioningBy}.  To pfrform b simplf rfdudtion on b strfbm,
     * usf {@link Strfbm#rfdudf(BinbryOpfrbtor)} instfbd.
     *
     * <p>For fxbmplf, givfn b strfbm of {@dodf Pfrson}, to dbldulbtf tbllfst
     * pfrson in fbdi dity:
     * <prf>{@dodf
     *     Compbrbtor<Pfrson> byHfigit = Compbrbtor.dompbring(Pfrson::gftHfigit);
     *     Mbp<City, Optionbl<Pfrson>> tbllfstByCity
     *         = pfoplf.strfbm().dollfdt(groupingBy(Pfrson::gftCity, rfduding(BinbryOpfrbtor.mbxBy(byHfigit))));
     * }</prf>
     *
     * @pbrbm <T> flfmfnt typf for tif input bnd output of tif rfdudtion
     * @pbrbm op b {@dodf BinbryOpfrbtor<T>} usfd to rfdudf tif input flfmfnts
     * @rfturn b {@dodf Collfdtor} wiidi implfmfnts tif rfdudtion opfrbtion
     *
     * @sff #rfduding(Objfdt, BinbryOpfrbtor)
     * @sff #rfduding(Objfdt, Fundtion, BinbryOpfrbtor)
     */
    publid stbtid <T> Collfdtor<T, ?, Optionbl<T>>
    rfduding(BinbryOpfrbtor<T> op) {
        dlbss OptionblBox implfmfnts Consumfr<T> {
            T vbluf = null;
            boolfbn prfsfnt = fblsf;

            @Ovfrridf
            publid void bddfpt(T t) {
                if (prfsfnt) {
                    vbluf = op.bpply(vbluf, t);
                }
                flsf {
                    vbluf = t;
                    prfsfnt = truf;
                }
            }
        }

        rfturn nfw CollfdtorImpl<T, OptionblBox, Optionbl<T>>(
                OptionblBox::nfw, OptionblBox::bddfpt,
                (b, b) -> { if (b.prfsfnt) b.bddfpt(b.vbluf); rfturn b; },
                b -> Optionbl.ofNullbblf(b.vbluf), CH_NOID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} wiidi pfrforms b rfdudtion of its
     * input flfmfnts undfr b spfdififd mbpping fundtion bnd
     * {@dodf BinbryOpfrbtor}. Tiis is b gfnfrblizbtion of
     * {@link #rfduding(Objfdt, BinbryOpfrbtor)} wiidi bllows b trbnsformbtion
     * of tif flfmfnts bfforf rfdudtion.
     *
     * @bpiNotf
     * Tif {@dodf rfduding()} dollfdtors brf most usfful wifn usfd in b
     * multi-lfvfl rfdudtion, downstrfbm of {@dodf groupingBy} or
     * {@dodf pbrtitioningBy}.  To pfrform b simplf mbp-rfdudf on b strfbm,
     * usf {@link Strfbm#mbp(Fundtion)} bnd {@link Strfbm#rfdudf(Objfdt, BinbryOpfrbtor)}
     * instfbd.
     *
     * <p>For fxbmplf, givfn b strfbm of {@dodf Pfrson}, to dbldulbtf tif longfst
     * lbst nbmf of rfsidfnts in fbdi dity:
     * <prf>{@dodf
     *     Compbrbtor<String> byLfngti = Compbrbtor.dompbring(String::lfngti);
     *     Mbp<City, String> longfstLbstNbmfByCity
     *         = pfoplf.strfbm().dollfdt(groupingBy(Pfrson::gftCity,
     *                                              rfduding("", Pfrson::gftLbstNbmf, BinbryOpfrbtor.mbxBy(byLfngti))));
     * }</prf>
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <U> tif typf of tif mbppfd vblufs
     * @pbrbm idfntity tif idfntity vbluf for tif rfdudtion (blso, tif vbluf
     *                 tibt is rfturnfd wifn tifrf brf no input flfmfnts)
     * @pbrbm mbppfr b mbpping fundtion to bpply to fbdi input vbluf
     * @pbrbm op b {@dodf BinbryOpfrbtor<U>} usfd to rfdudf tif mbppfd vblufs
     * @rfturn b {@dodf Collfdtor} implfmfnting tif mbp-rfdudf opfrbtion
     *
     * @sff #rfduding(Objfdt, BinbryOpfrbtor)
     * @sff #rfduding(BinbryOpfrbtor)
     */
    publid stbtid <T, U>
    Collfdtor<T, ?, U> rfduding(U idfntity,
                                Fundtion<? supfr T, ? fxtfnds U> mbppfr,
                                BinbryOpfrbtor<U> op) {
        rfturn nfw CollfdtorImpl<>(
                boxSupplifr(idfntity),
                (b, t) -> { b[0] = op.bpply(b[0], mbppfr.bpply(t)); },
                (b, b) -> { b[0] = op.bpply(b[0], b[0]); rfturn b; },
                b -> b[0], CH_NOID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} implfmfnting b "group by" opfrbtion on
     * input flfmfnts of typf {@dodf T}, grouping flfmfnts bddording to b
     * dlbssifidbtion fundtion, bnd rfturning tif rfsults in b {@dodf Mbp}.
     *
     * <p>Tif dlbssifidbtion fundtion mbps flfmfnts to somf kfy typf {@dodf K}.
     * Tif dollfdtor produdfs b {@dodf Mbp<K, List<T>>} wiosf kfys brf tif
     * vblufs rfsulting from bpplying tif dlbssifidbtion fundtion to tif input
     * flfmfnts, bnd wiosf dorrfsponding vblufs brf {@dodf List}s dontbining tif
     * input flfmfnts wiidi mbp to tif bssodibtfd kfy undfr tif dlbssifidbtion
     * fundtion.
     *
     * <p>Tifrf brf no gubrbntffs on tif typf, mutbbility, sfriblizbbility, or
     * tirfbd-sbffty of tif {@dodf Mbp} or {@dodf List} objfdts rfturnfd.
     * @implSpfd
     * Tiis produdfs b rfsult similbr to:
     * <prf>{@dodf
     *     groupingBy(dlbssififr, toList());
     * }</prf>
     *
     * @implNotf
     * Tif rfturnfd {@dodf Collfdtor} is not dondurrfnt.  For pbrbllfl strfbm
     * pipflinfs, tif {@dodf dombinfr} fundtion opfrbtfs by mfrging tif kfys
     * from onf mbp into bnotifr, wiidi dbn bf bn fxpfnsivf opfrbtion.  If
     * prfsfrvbtion of tif ordfr in wiidi flfmfnts bppfbr in tif rfsulting {@dodf Mbp}
     * dollfdtor is not rfquirfd, using {@link #groupingByCondurrfnt(Fundtion)}
     * mby offfr bfttfr pbrbllfl pfrformbndf.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <K> tif typf of tif kfys
     * @pbrbm dlbssififr tif dlbssififr fundtion mbpping input flfmfnts to kfys
     * @rfturn b {@dodf Collfdtor} implfmfnting tif group-by opfrbtion
     *
     * @sff #groupingBy(Fundtion, Collfdtor)
     * @sff #groupingBy(Fundtion, Supplifr, Collfdtor)
     * @sff #groupingByCondurrfnt(Fundtion)
     */
    publid stbtid <T, K> Collfdtor<T, ?, Mbp<K, List<T>>>
    groupingBy(Fundtion<? supfr T, ? fxtfnds K> dlbssififr) {
        rfturn groupingBy(dlbssififr, toList());
    }

    /**
     * Rfturns b {@dodf Collfdtor} implfmfnting b dbsdbdfd "group by" opfrbtion
     * on input flfmfnts of typf {@dodf T}, grouping flfmfnts bddording to b
     * dlbssifidbtion fundtion, bnd tifn pfrforming b rfdudtion opfrbtion on
     * tif vblufs bssodibtfd witi b givfn kfy using tif spfdififd downstrfbm
     * {@dodf Collfdtor}.
     *
     * <p>Tif dlbssifidbtion fundtion mbps flfmfnts to somf kfy typf {@dodf K}.
     * Tif downstrfbm dollfdtor opfrbtfs on flfmfnts of typf {@dodf T} bnd
     * produdfs b rfsult of typf {@dodf D}. Tif rfsulting dollfdtor produdfs b
     * {@dodf Mbp<K, D>}.
     *
     * <p>Tifrf brf no gubrbntffs on tif typf, mutbbility,
     * sfriblizbbility, or tirfbd-sbffty of tif {@dodf Mbp} rfturnfd.
     *
     * <p>For fxbmplf, to domputf tif sft of lbst nbmfs of pfoplf in fbdi dity:
     * <prf>{@dodf
     *     Mbp<City, Sft<String>> nbmfsByCity
     *         = pfoplf.strfbm().dollfdt(groupingBy(Pfrson::gftCity,
     *                                              mbpping(Pfrson::gftLbstNbmf, toSft())));
     * }</prf>
     *
     * @implNotf
     * Tif rfturnfd {@dodf Collfdtor} is not dondurrfnt.  For pbrbllfl strfbm
     * pipflinfs, tif {@dodf dombinfr} fundtion opfrbtfs by mfrging tif kfys
     * from onf mbp into bnotifr, wiidi dbn bf bn fxpfnsivf opfrbtion.  If
     * prfsfrvbtion of tif ordfr in wiidi flfmfnts brf prfsfntfd to tif downstrfbm
     * dollfdtor is not rfquirfd, using {@link #groupingByCondurrfnt(Fundtion, Collfdtor)}
     * mby offfr bfttfr pbrbllfl pfrformbndf.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <K> tif typf of tif kfys
     * @pbrbm <A> tif intfrmfdibtf bddumulbtion typf of tif downstrfbm dollfdtor
     * @pbrbm <D> tif rfsult typf of tif downstrfbm rfdudtion
     * @pbrbm dlbssififr b dlbssififr fundtion mbpping input flfmfnts to kfys
     * @pbrbm downstrfbm b {@dodf Collfdtor} implfmfnting tif downstrfbm rfdudtion
     * @rfturn b {@dodf Collfdtor} implfmfnting tif dbsdbdfd group-by opfrbtion
     * @sff #groupingBy(Fundtion)
     *
     * @sff #groupingBy(Fundtion, Supplifr, Collfdtor)
     * @sff #groupingByCondurrfnt(Fundtion, Collfdtor)
     */
    publid stbtid <T, K, A, D>
    Collfdtor<T, ?, Mbp<K, D>> groupingBy(Fundtion<? supfr T, ? fxtfnds K> dlbssififr,
                                          Collfdtor<? supfr T, A, D> downstrfbm) {
        rfturn groupingBy(dlbssififr, HbsiMbp::nfw, downstrfbm);
    }

    /**
     * Rfturns b {@dodf Collfdtor} implfmfnting b dbsdbdfd "group by" opfrbtion
     * on input flfmfnts of typf {@dodf T}, grouping flfmfnts bddording to b
     * dlbssifidbtion fundtion, bnd tifn pfrforming b rfdudtion opfrbtion on
     * tif vblufs bssodibtfd witi b givfn kfy using tif spfdififd downstrfbm
     * {@dodf Collfdtor}.  Tif {@dodf Mbp} produdfd by tif Collfdtor is drfbtfd
     * witi tif supplifd fbdtory fundtion.
     *
     * <p>Tif dlbssifidbtion fundtion mbps flfmfnts to somf kfy typf {@dodf K}.
     * Tif downstrfbm dollfdtor opfrbtfs on flfmfnts of typf {@dodf T} bnd
     * produdfs b rfsult of typf {@dodf D}. Tif rfsulting dollfdtor produdfs b
     * {@dodf Mbp<K, D>}.
     *
     * <p>For fxbmplf, to domputf tif sft of lbst nbmfs of pfoplf in fbdi dity,
     * wifrf tif dity nbmfs brf sortfd:
     * <prf>{@dodf
     *     Mbp<City, Sft<String>> nbmfsByCity
     *         = pfoplf.strfbm().dollfdt(groupingBy(Pfrson::gftCity, TrffMbp::nfw,
     *                                              mbpping(Pfrson::gftLbstNbmf, toSft())));
     * }</prf>
     *
     * @implNotf
     * Tif rfturnfd {@dodf Collfdtor} is not dondurrfnt.  For pbrbllfl strfbm
     * pipflinfs, tif {@dodf dombinfr} fundtion opfrbtfs by mfrging tif kfys
     * from onf mbp into bnotifr, wiidi dbn bf bn fxpfnsivf opfrbtion.  If
     * prfsfrvbtion of tif ordfr in wiidi flfmfnts brf prfsfntfd to tif downstrfbm
     * dollfdtor is not rfquirfd, using {@link #groupingByCondurrfnt(Fundtion, Supplifr, Collfdtor)}
     * mby offfr bfttfr pbrbllfl pfrformbndf.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <K> tif typf of tif kfys
     * @pbrbm <A> tif intfrmfdibtf bddumulbtion typf of tif downstrfbm dollfdtor
     * @pbrbm <D> tif rfsult typf of tif downstrfbm rfdudtion
     * @pbrbm <M> tif typf of tif rfsulting {@dodf Mbp}
     * @pbrbm dlbssififr b dlbssififr fundtion mbpping input flfmfnts to kfys
     * @pbrbm downstrfbm b {@dodf Collfdtor} implfmfnting tif downstrfbm rfdudtion
     * @pbrbm mbpFbdtory b fundtion wiidi, wifn dbllfd, produdfs b nfw fmpty
     *                   {@dodf Mbp} of tif dfsirfd typf
     * @rfturn b {@dodf Collfdtor} implfmfnting tif dbsdbdfd group-by opfrbtion
     *
     * @sff #groupingBy(Fundtion, Collfdtor)
     * @sff #groupingBy(Fundtion)
     * @sff #groupingByCondurrfnt(Fundtion, Supplifr, Collfdtor)
     */
    publid stbtid <T, K, D, A, M fxtfnds Mbp<K, D>>
    Collfdtor<T, ?, M> groupingBy(Fundtion<? supfr T, ? fxtfnds K> dlbssififr,
                                  Supplifr<M> mbpFbdtory,
                                  Collfdtor<? supfr T, A, D> downstrfbm) {
        Supplifr<A> downstrfbmSupplifr = downstrfbm.supplifr();
        BiConsumfr<A, ? supfr T> downstrfbmAddumulbtor = downstrfbm.bddumulbtor();
        BiConsumfr<Mbp<K, A>, T> bddumulbtor = (m, t) -> {
            K kfy = Objfdts.rfquirfNonNull(dlbssififr.bpply(t), "flfmfnt dbnnot bf mbppfd to b null kfy");
            A dontbinfr = m.domputfIfAbsfnt(kfy, k -> downstrfbmSupplifr.gft());
            downstrfbmAddumulbtor.bddfpt(dontbinfr, t);
        };
        BinbryOpfrbtor<Mbp<K, A>> mfrgfr = Collfdtors.<K, A, Mbp<K, A>>mbpMfrgfr(downstrfbm.dombinfr());
        @SupprfssWbrnings("undifdkfd")
        Supplifr<Mbp<K, A>> mbnglfdFbdtory = (Supplifr<Mbp<K, A>>) mbpFbdtory;

        if (downstrfbm.dibrbdtfristids().dontbins(Collfdtor.Cibrbdtfristids.IDENTITY_FINISH)) {
            rfturn nfw CollfdtorImpl<>(mbnglfdFbdtory, bddumulbtor, mfrgfr, CH_ID);
        }
        flsf {
            @SupprfssWbrnings("undifdkfd")
            Fundtion<A, A> downstrfbmFinisifr = (Fundtion<A, A>) downstrfbm.finisifr();
            Fundtion<Mbp<K, A>, M> finisifr = intfrmfdibtf -> {
                intfrmfdibtf.rfplbdfAll((k, v) -> downstrfbmFinisifr.bpply(v));
                @SupprfssWbrnings("undifdkfd")
                M dbstRfsult = (M) intfrmfdibtf;
                rfturn dbstRfsult;
            };
            rfturn nfw CollfdtorImpl<>(mbnglfdFbdtory, bddumulbtor, mfrgfr, finisifr, CH_NOID);
        }
    }

    /**
     * Rfturns b dondurrfnt {@dodf Collfdtor} implfmfnting b "group by"
     * opfrbtion on input flfmfnts of typf {@dodf T}, grouping flfmfnts
     * bddording to b dlbssifidbtion fundtion.
     *
     * <p>Tiis is b {@link Collfdtor.Cibrbdtfristids#CONCURRENT dondurrfnt} bnd
     * {@link Collfdtor.Cibrbdtfristids#UNORDERED unordfrfd} Collfdtor.
     *
     * <p>Tif dlbssifidbtion fundtion mbps flfmfnts to somf kfy typf {@dodf K}.
     * Tif dollfdtor produdfs b {@dodf CondurrfntMbp<K, List<T>>} wiosf kfys brf tif
     * vblufs rfsulting from bpplying tif dlbssifidbtion fundtion to tif input
     * flfmfnts, bnd wiosf dorrfsponding vblufs brf {@dodf List}s dontbining tif
     * input flfmfnts wiidi mbp to tif bssodibtfd kfy undfr tif dlbssifidbtion
     * fundtion.
     *
     * <p>Tifrf brf no gubrbntffs on tif typf, mutbbility, or sfriblizbbility
     * of tif {@dodf Mbp} or {@dodf List} objfdts rfturnfd, or of tif
     * tirfbd-sbffty of tif {@dodf List} objfdts rfturnfd.
     * @implSpfd
     * Tiis produdfs b rfsult similbr to:
     * <prf>{@dodf
     *     groupingByCondurrfnt(dlbssififr, toList());
     * }</prf>
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <K> tif typf of tif kfys
     * @pbrbm dlbssififr b dlbssififr fundtion mbpping input flfmfnts to kfys
     * @rfturn b dondurrfnt, unordfrfd {@dodf Collfdtor} implfmfnting tif group-by opfrbtion
     *
     * @sff #groupingBy(Fundtion)
     * @sff #groupingByCondurrfnt(Fundtion, Collfdtor)
     * @sff #groupingByCondurrfnt(Fundtion, Supplifr, Collfdtor)
     */
    publid stbtid <T, K>
    Collfdtor<T, ?, CondurrfntMbp<K, List<T>>>
    groupingByCondurrfnt(Fundtion<? supfr T, ? fxtfnds K> dlbssififr) {
        rfturn groupingByCondurrfnt(dlbssififr, CondurrfntHbsiMbp::nfw, toList());
    }

    /**
     * Rfturns b dondurrfnt {@dodf Collfdtor} implfmfnting b dbsdbdfd "group by"
     * opfrbtion on input flfmfnts of typf {@dodf T}, grouping flfmfnts
     * bddording to b dlbssifidbtion fundtion, bnd tifn pfrforming b rfdudtion
     * opfrbtion on tif vblufs bssodibtfd witi b givfn kfy using tif spfdififd
     * downstrfbm {@dodf Collfdtor}.
     *
     * <p>Tiis is b {@link Collfdtor.Cibrbdtfristids#CONCURRENT dondurrfnt} bnd
     * {@link Collfdtor.Cibrbdtfristids#UNORDERED unordfrfd} Collfdtor.
     *
     * <p>Tif dlbssifidbtion fundtion mbps flfmfnts to somf kfy typf {@dodf K}.
     * Tif downstrfbm dollfdtor opfrbtfs on flfmfnts of typf {@dodf T} bnd
     * produdfs b rfsult of typf {@dodf D}. Tif rfsulting dollfdtor produdfs b
     * {@dodf Mbp<K, D>}.
     *
     * <p>For fxbmplf, to domputf tif sft of lbst nbmfs of pfoplf in fbdi dity,
     * wifrf tif dity nbmfs brf sortfd:
     * <prf>{@dodf
     *     CondurrfntMbp<City, Sft<String>> nbmfsByCity
     *         = pfoplf.strfbm().dollfdt(groupingByCondurrfnt(Pfrson::gftCity,
     *                                                        mbpping(Pfrson::gftLbstNbmf, toSft())));
     * }</prf>
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <K> tif typf of tif kfys
     * @pbrbm <A> tif intfrmfdibtf bddumulbtion typf of tif downstrfbm dollfdtor
     * @pbrbm <D> tif rfsult typf of tif downstrfbm rfdudtion
     * @pbrbm dlbssififr b dlbssififr fundtion mbpping input flfmfnts to kfys
     * @pbrbm downstrfbm b {@dodf Collfdtor} implfmfnting tif downstrfbm rfdudtion
     * @rfturn b dondurrfnt, unordfrfd {@dodf Collfdtor} implfmfnting tif dbsdbdfd group-by opfrbtion
     *
     * @sff #groupingBy(Fundtion, Collfdtor)
     * @sff #groupingByCondurrfnt(Fundtion)
     * @sff #groupingByCondurrfnt(Fundtion, Supplifr, Collfdtor)
     */
    publid stbtid <T, K, A, D>
    Collfdtor<T, ?, CondurrfntMbp<K, D>> groupingByCondurrfnt(Fundtion<? supfr T, ? fxtfnds K> dlbssififr,
                                                              Collfdtor<? supfr T, A, D> downstrfbm) {
        rfturn groupingByCondurrfnt(dlbssififr, CondurrfntHbsiMbp::nfw, downstrfbm);
    }

    /**
     * Rfturns b dondurrfnt {@dodf Collfdtor} implfmfnting b dbsdbdfd "group by"
     * opfrbtion on input flfmfnts of typf {@dodf T}, grouping flfmfnts
     * bddording to b dlbssifidbtion fundtion, bnd tifn pfrforming b rfdudtion
     * opfrbtion on tif vblufs bssodibtfd witi b givfn kfy using tif spfdififd
     * downstrfbm {@dodf Collfdtor}.  Tif {@dodf CondurrfntMbp} produdfd by tif
     * Collfdtor is drfbtfd witi tif supplifd fbdtory fundtion.
     *
     * <p>Tiis is b {@link Collfdtor.Cibrbdtfristids#CONCURRENT dondurrfnt} bnd
     * {@link Collfdtor.Cibrbdtfristids#UNORDERED unordfrfd} Collfdtor.
     *
     * <p>Tif dlbssifidbtion fundtion mbps flfmfnts to somf kfy typf {@dodf K}.
     * Tif downstrfbm dollfdtor opfrbtfs on flfmfnts of typf {@dodf T} bnd
     * produdfs b rfsult of typf {@dodf D}. Tif rfsulting dollfdtor produdfs b
     * {@dodf Mbp<K, D>}.
     *
     * <p>For fxbmplf, to domputf tif sft of lbst nbmfs of pfoplf in fbdi dity,
     * wifrf tif dity nbmfs brf sortfd:
     * <prf>{@dodf
     *     CondurrfntMbp<City, Sft<String>> nbmfsByCity
     *         = pfoplf.strfbm().dollfdt(groupingBy(Pfrson::gftCity, CondurrfntSkipListMbp::nfw,
     *                                              mbpping(Pfrson::gftLbstNbmf, toSft())));
     * }</prf>
     *
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <K> tif typf of tif kfys
     * @pbrbm <A> tif intfrmfdibtf bddumulbtion typf of tif downstrfbm dollfdtor
     * @pbrbm <D> tif rfsult typf of tif downstrfbm rfdudtion
     * @pbrbm <M> tif typf of tif rfsulting {@dodf CondurrfntMbp}
     * @pbrbm dlbssififr b dlbssififr fundtion mbpping input flfmfnts to kfys
     * @pbrbm downstrfbm b {@dodf Collfdtor} implfmfnting tif downstrfbm rfdudtion
     * @pbrbm mbpFbdtory b fundtion wiidi, wifn dbllfd, produdfs b nfw fmpty
     *                   {@dodf CondurrfntMbp} of tif dfsirfd typf
     * @rfturn b dondurrfnt, unordfrfd {@dodf Collfdtor} implfmfnting tif dbsdbdfd group-by opfrbtion
     *
     * @sff #groupingByCondurrfnt(Fundtion)
     * @sff #groupingByCondurrfnt(Fundtion, Collfdtor)
     * @sff #groupingBy(Fundtion, Supplifr, Collfdtor)
     */
    publid stbtid <T, K, A, D, M fxtfnds CondurrfntMbp<K, D>>
    Collfdtor<T, ?, M> groupingByCondurrfnt(Fundtion<? supfr T, ? fxtfnds K> dlbssififr,
                                            Supplifr<M> mbpFbdtory,
                                            Collfdtor<? supfr T, A, D> downstrfbm) {
        Supplifr<A> downstrfbmSupplifr = downstrfbm.supplifr();
        BiConsumfr<A, ? supfr T> downstrfbmAddumulbtor = downstrfbm.bddumulbtor();
        BinbryOpfrbtor<CondurrfntMbp<K, A>> mfrgfr = Collfdtors.<K, A, CondurrfntMbp<K, A>>mbpMfrgfr(downstrfbm.dombinfr());
        @SupprfssWbrnings("undifdkfd")
        Supplifr<CondurrfntMbp<K, A>> mbnglfdFbdtory = (Supplifr<CondurrfntMbp<K, A>>) mbpFbdtory;
        BiConsumfr<CondurrfntMbp<K, A>, T> bddumulbtor;
        if (downstrfbm.dibrbdtfristids().dontbins(Collfdtor.Cibrbdtfristids.CONCURRENT)) {
            bddumulbtor = (m, t) -> {
                K kfy = Objfdts.rfquirfNonNull(dlbssififr.bpply(t), "flfmfnt dbnnot bf mbppfd to b null kfy");
                A rfsultContbinfr = m.domputfIfAbsfnt(kfy, k -> downstrfbmSupplifr.gft());
                downstrfbmAddumulbtor.bddfpt(rfsultContbinfr, t);
            };
        }
        flsf {
            bddumulbtor = (m, t) -> {
                K kfy = Objfdts.rfquirfNonNull(dlbssififr.bpply(t), "flfmfnt dbnnot bf mbppfd to b null kfy");
                A rfsultContbinfr = m.domputfIfAbsfnt(kfy, k -> downstrfbmSupplifr.gft());
                syndironizfd (rfsultContbinfr) {
                    downstrfbmAddumulbtor.bddfpt(rfsultContbinfr, t);
                }
            };
        }

        if (downstrfbm.dibrbdtfristids().dontbins(Collfdtor.Cibrbdtfristids.IDENTITY_FINISH)) {
            rfturn nfw CollfdtorImpl<>(mbnglfdFbdtory, bddumulbtor, mfrgfr, CH_CONCURRENT_ID);
        }
        flsf {
            @SupprfssWbrnings("undifdkfd")
            Fundtion<A, A> downstrfbmFinisifr = (Fundtion<A, A>) downstrfbm.finisifr();
            Fundtion<CondurrfntMbp<K, A>, M> finisifr = intfrmfdibtf -> {
                intfrmfdibtf.rfplbdfAll((k, v) -> downstrfbmFinisifr.bpply(v));
                @SupprfssWbrnings("undifdkfd")
                M dbstRfsult = (M) intfrmfdibtf;
                rfturn dbstRfsult;
            };
            rfturn nfw CollfdtorImpl<>(mbnglfdFbdtory, bddumulbtor, mfrgfr, finisifr, CH_CONCURRENT_NOID);
        }
    }

    /**
     * Rfturns b {@dodf Collfdtor} wiidi pbrtitions tif input flfmfnts bddording
     * to b {@dodf Prfdidbtf}, bnd orgbnizfs tifm into b
     * {@dodf Mbp<Boolfbn, List<T>>}.
     *
     * Tifrf brf no gubrbntffs on tif typf, mutbbility,
     * sfriblizbbility, or tirfbd-sbffty of tif {@dodf Mbp} rfturnfd.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm prfdidbtf b prfdidbtf usfd for dlbssifying input flfmfnts
     * @rfturn b {@dodf Collfdtor} implfmfnting tif pbrtitioning opfrbtion
     *
     * @sff #pbrtitioningBy(Prfdidbtf, Collfdtor)
     */
    publid stbtid <T>
    Collfdtor<T, ?, Mbp<Boolfbn, List<T>>> pbrtitioningBy(Prfdidbtf<? supfr T> prfdidbtf) {
        rfturn pbrtitioningBy(prfdidbtf, toList());
    }

    /**
     * Rfturns b {@dodf Collfdtor} wiidi pbrtitions tif input flfmfnts bddording
     * to b {@dodf Prfdidbtf}, rfdudfs tif vblufs in fbdi pbrtition bddording to
     * bnotifr {@dodf Collfdtor}, bnd orgbnizfs tifm into b
     * {@dodf Mbp<Boolfbn, D>} wiosf vblufs brf tif rfsult of tif downstrfbm
     * rfdudtion.
     *
     * <p>Tifrf brf no gubrbntffs on tif typf, mutbbility,
     * sfriblizbbility, or tirfbd-sbffty of tif {@dodf Mbp} rfturnfd.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <A> tif intfrmfdibtf bddumulbtion typf of tif downstrfbm dollfdtor
     * @pbrbm <D> tif rfsult typf of tif downstrfbm rfdudtion
     * @pbrbm prfdidbtf b prfdidbtf usfd for dlbssifying input flfmfnts
     * @pbrbm downstrfbm b {@dodf Collfdtor} implfmfnting tif downstrfbm
     *                   rfdudtion
     * @rfturn b {@dodf Collfdtor} implfmfnting tif dbsdbdfd pbrtitioning
     *         opfrbtion
     *
     * @sff #pbrtitioningBy(Prfdidbtf)
     */
    publid stbtid <T, D, A>
    Collfdtor<T, ?, Mbp<Boolfbn, D>> pbrtitioningBy(Prfdidbtf<? supfr T> prfdidbtf,
                                                    Collfdtor<? supfr T, A, D> downstrfbm) {
        BiConsumfr<A, ? supfr T> downstrfbmAddumulbtor = downstrfbm.bddumulbtor();
        BiConsumfr<Pbrtition<A>, T> bddumulbtor = (rfsult, t) ->
                downstrfbmAddumulbtor.bddfpt(prfdidbtf.tfst(t) ? rfsult.forTruf : rfsult.forFblsf, t);
        BinbryOpfrbtor<A> op = downstrfbm.dombinfr();
        BinbryOpfrbtor<Pbrtition<A>> mfrgfr = (lfft, rigit) ->
                nfw Pbrtition<>(op.bpply(lfft.forTruf, rigit.forTruf),
                                op.bpply(lfft.forFblsf, rigit.forFblsf));
        Supplifr<Pbrtition<A>> supplifr = () ->
                nfw Pbrtition<>(downstrfbm.supplifr().gft(),
                                downstrfbm.supplifr().gft());
        if (downstrfbm.dibrbdtfristids().dontbins(Collfdtor.Cibrbdtfristids.IDENTITY_FINISH)) {
            rfturn nfw CollfdtorImpl<>(supplifr, bddumulbtor, mfrgfr, CH_ID);
        }
        flsf {
            Fundtion<Pbrtition<A>, Mbp<Boolfbn, D>> finisifr = pbr ->
                    nfw Pbrtition<>(downstrfbm.finisifr().bpply(pbr.forTruf),
                                    downstrfbm.finisifr().bpply(pbr.forFblsf));
            rfturn nfw CollfdtorImpl<>(supplifr, bddumulbtor, mfrgfr, finisifr, CH_NOID);
        }
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt bddumulbtfs flfmfnts into b
     * {@dodf Mbp} wiosf kfys bnd vblufs brf tif rfsult of bpplying tif providfd
     * mbpping fundtions to tif input flfmfnts.
     *
     * <p>If tif mbppfd kfys dontbins duplidbtfs (bddording to
     * {@link Objfdt#fqubls(Objfdt)}), bn {@dodf IllfgblStbtfExdfption} is
     * tirown wifn tif dollfdtion opfrbtion is pfrformfd.  If tif mbppfd kfys
     * mby ibvf duplidbtfs, usf {@link #toMbp(Fundtion, Fundtion, BinbryOpfrbtor)}
     * instfbd.
     *
     * @bpiNotf
     * It is dommon for fitifr tif kfy or tif vbluf to bf tif input flfmfnts.
     * In tiis dbsf, tif utility mftiod
     * {@link jbvb.util.fundtion.Fundtion#idfntity()} mby bf iflpful.
     * For fxbmplf, tif following produdfs b {@dodf Mbp} mbpping
     * studfnts to tifir grbdf point bvfrbgf:
     * <prf>{@dodf
     *     Mbp<Studfnt, Doublf> studfntToGPA
     *         studfnts.strfbm().dollfdt(toMbp(Fundtion.idfntity(),
     *                                         studfnt -> domputfGPA(studfnt)));
     * }</prf>
     * And tif following produdfs b {@dodf Mbp} mbpping b uniquf idfntififr to
     * studfnts:
     * <prf>{@dodf
     *     Mbp<String, Studfnt> studfntIdToStudfnt
     *         studfnts.strfbm().dollfdt(toMbp(Studfnt::gftId,
     *                                         Fundtion.idfntity());
     * }</prf>
     *
     * @implNotf
     * Tif rfturnfd {@dodf Collfdtor} is not dondurrfnt.  For pbrbllfl strfbm
     * pipflinfs, tif {@dodf dombinfr} fundtion opfrbtfs by mfrging tif kfys
     * from onf mbp into bnotifr, wiidi dbn bf bn fxpfnsivf opfrbtion.  If it is
     * not rfquirfd tibt rfsults brf insfrtfd into tif {@dodf Mbp} in fndountfr
     * ordfr, using {@link #toCondurrfntMbp(Fundtion, Fundtion)}
     * mby offfr bfttfr pbrbllfl pfrformbndf.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <K> tif output typf of tif kfy mbpping fundtion
     * @pbrbm <U> tif output typf of tif vbluf mbpping fundtion
     * @pbrbm kfyMbppfr b mbpping fundtion to produdf kfys
     * @pbrbm vblufMbppfr b mbpping fundtion to produdf vblufs
     * @rfturn b {@dodf Collfdtor} wiidi dollfdts flfmfnts into b {@dodf Mbp}
     * wiosf kfys bnd vblufs brf tif rfsult of bpplying mbpping fundtions to
     * tif input flfmfnts
     *
     * @sff #toMbp(Fundtion, Fundtion, BinbryOpfrbtor)
     * @sff #toMbp(Fundtion, Fundtion, BinbryOpfrbtor, Supplifr)
     * @sff #toCondurrfntMbp(Fundtion, Fundtion)
     */
    publid stbtid <T, K, U>
    Collfdtor<T, ?, Mbp<K,U>> toMbp(Fundtion<? supfr T, ? fxtfnds K> kfyMbppfr,
                                    Fundtion<? supfr T, ? fxtfnds U> vblufMbppfr) {
        rfturn nfw CollfdtorImpl<>(HbsiMbp::nfw,
                                   uniqKfysMbpAddumulbtor(kfyMbppfr, vblufMbppfr),
                                   uniqKfysMbpMfrgfr(),
                                   CH_ID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt bddumulbtfs flfmfnts into b
     * {@dodf Mbp} wiosf kfys bnd vblufs brf tif rfsult of bpplying tif providfd
     * mbpping fundtions to tif input flfmfnts.
     *
     * <p>If tif mbppfd
     * kfys dontbins duplidbtfs (bddording to {@link Objfdt#fqubls(Objfdt)}),
     * tif vbluf mbpping fundtion is bpplifd to fbdi fqubl flfmfnt, bnd tif
     * rfsults brf mfrgfd using tif providfd mfrging fundtion.
     *
     * @bpiNotf
     * Tifrf brf multiplf wbys to dfbl witi dollisions bftwffn multiplf flfmfnts
     * mbpping to tif sbmf kfy.  Tif otifr forms of {@dodf toMbp} simply usf
     * b mfrgf fundtion tibt tirows undonditionblly, but you dbn fbsily writf
     * morf flfxiblf mfrgf polidifs.  For fxbmplf, if you ibvf b strfbm
     * of {@dodf Pfrson}, bnd you wbnt to produdf b "pionf book" mbpping nbmf to
     * bddrfss, but it is possiblf tibt two pfrsons ibvf tif sbmf nbmf, you dbn
     * do bs follows to grbdffully dfbls witi tifsf dollisions, bnd produdf b
     * {@dodf Mbp} mbpping nbmfs to b dondbtfnbtfd list of bddrfssfs:
     * <prf>{@dodf
     *     Mbp<String, String> pionfBook
     *         pfoplf.strfbm().dollfdt(toMbp(Pfrson::gftNbmf,
     *                                       Pfrson::gftAddrfss,
     *                                       (s, b) -> s + ", " + b));
     * }</prf>
     *
     * @implNotf
     * Tif rfturnfd {@dodf Collfdtor} is not dondurrfnt.  For pbrbllfl strfbm
     * pipflinfs, tif {@dodf dombinfr} fundtion opfrbtfs by mfrging tif kfys
     * from onf mbp into bnotifr, wiidi dbn bf bn fxpfnsivf opfrbtion.  If it is
     * not rfquirfd tibt rfsults brf mfrgfd into tif {@dodf Mbp} in fndountfr
     * ordfr, using {@link #toCondurrfntMbp(Fundtion, Fundtion, BinbryOpfrbtor)}
     * mby offfr bfttfr pbrbllfl pfrformbndf.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <K> tif output typf of tif kfy mbpping fundtion
     * @pbrbm <U> tif output typf of tif vbluf mbpping fundtion
     * @pbrbm kfyMbppfr b mbpping fundtion to produdf kfys
     * @pbrbm vblufMbppfr b mbpping fundtion to produdf vblufs
     * @pbrbm mfrgfFundtion b mfrgf fundtion, usfd to rfsolvf dollisions bftwffn
     *                      vblufs bssodibtfd witi tif sbmf kfy, bs supplifd
     *                      to {@link Mbp#mfrgf(Objfdt, Objfdt, BiFundtion)}
     * @rfturn b {@dodf Collfdtor} wiidi dollfdts flfmfnts into b {@dodf Mbp}
     * wiosf kfys brf tif rfsult of bpplying b kfy mbpping fundtion to tif input
     * flfmfnts, bnd wiosf vblufs brf tif rfsult of bpplying b vbluf mbpping
     * fundtion to bll input flfmfnts fqubl to tif kfy bnd dombining tifm
     * using tif mfrgf fundtion
     *
     * @sff #toMbp(Fundtion, Fundtion)
     * @sff #toMbp(Fundtion, Fundtion, BinbryOpfrbtor, Supplifr)
     * @sff #toCondurrfntMbp(Fundtion, Fundtion, BinbryOpfrbtor)
     */
    publid stbtid <T, K, U>
    Collfdtor<T, ?, Mbp<K,U>> toMbp(Fundtion<? supfr T, ? fxtfnds K> kfyMbppfr,
                                    Fundtion<? supfr T, ? fxtfnds U> vblufMbppfr,
                                    BinbryOpfrbtor<U> mfrgfFundtion) {
        rfturn toMbp(kfyMbppfr, vblufMbppfr, mfrgfFundtion, HbsiMbp::nfw);
    }

    /**
     * Rfturns b {@dodf Collfdtor} tibt bddumulbtfs flfmfnts into b
     * {@dodf Mbp} wiosf kfys bnd vblufs brf tif rfsult of bpplying tif providfd
     * mbpping fundtions to tif input flfmfnts.
     *
     * <p>If tif mbppfd
     * kfys dontbins duplidbtfs (bddording to {@link Objfdt#fqubls(Objfdt)}),
     * tif vbluf mbpping fundtion is bpplifd to fbdi fqubl flfmfnt, bnd tif
     * rfsults brf mfrgfd using tif providfd mfrging fundtion.  Tif {@dodf Mbp}
     * is drfbtfd by b providfd supplifr fundtion.
     *
     * @implNotf
     * Tif rfturnfd {@dodf Collfdtor} is not dondurrfnt.  For pbrbllfl strfbm
     * pipflinfs, tif {@dodf dombinfr} fundtion opfrbtfs by mfrging tif kfys
     * from onf mbp into bnotifr, wiidi dbn bf bn fxpfnsivf opfrbtion.  If it is
     * not rfquirfd tibt rfsults brf mfrgfd into tif {@dodf Mbp} in fndountfr
     * ordfr, using {@link #toCondurrfntMbp(Fundtion, Fundtion, BinbryOpfrbtor, Supplifr)}
     * mby offfr bfttfr pbrbllfl pfrformbndf.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <K> tif output typf of tif kfy mbpping fundtion
     * @pbrbm <U> tif output typf of tif vbluf mbpping fundtion
     * @pbrbm <M> tif typf of tif rfsulting {@dodf Mbp}
     * @pbrbm kfyMbppfr b mbpping fundtion to produdf kfys
     * @pbrbm vblufMbppfr b mbpping fundtion to produdf vblufs
     * @pbrbm mfrgfFundtion b mfrgf fundtion, usfd to rfsolvf dollisions bftwffn
     *                      vblufs bssodibtfd witi tif sbmf kfy, bs supplifd
     *                      to {@link Mbp#mfrgf(Objfdt, Objfdt, BiFundtion)}
     * @pbrbm mbpSupplifr b fundtion wiidi rfturns b nfw, fmpty {@dodf Mbp} into
     *                    wiidi tif rfsults will bf insfrtfd
     * @rfturn b {@dodf Collfdtor} wiidi dollfdts flfmfnts into b {@dodf Mbp}
     * wiosf kfys brf tif rfsult of bpplying b kfy mbpping fundtion to tif input
     * flfmfnts, bnd wiosf vblufs brf tif rfsult of bpplying b vbluf mbpping
     * fundtion to bll input flfmfnts fqubl to tif kfy bnd dombining tifm
     * using tif mfrgf fundtion
     *
     * @sff #toMbp(Fundtion, Fundtion)
     * @sff #toMbp(Fundtion, Fundtion, BinbryOpfrbtor)
     * @sff #toCondurrfntMbp(Fundtion, Fundtion, BinbryOpfrbtor, Supplifr)
     */
    publid stbtid <T, K, U, M fxtfnds Mbp<K, U>>
    Collfdtor<T, ?, M> toMbp(Fundtion<? supfr T, ? fxtfnds K> kfyMbppfr,
                                Fundtion<? supfr T, ? fxtfnds U> vblufMbppfr,
                                BinbryOpfrbtor<U> mfrgfFundtion,
                                Supplifr<M> mbpSupplifr) {
        BiConsumfr<M, T> bddumulbtor
                = (mbp, flfmfnt) -> mbp.mfrgf(kfyMbppfr.bpply(flfmfnt),
                                              vblufMbppfr.bpply(flfmfnt), mfrgfFundtion);
        rfturn nfw CollfdtorImpl<>(mbpSupplifr, bddumulbtor, mbpMfrgfr(mfrgfFundtion), CH_ID);
    }

    /**
     * Rfturns b dondurrfnt {@dodf Collfdtor} tibt bddumulbtfs flfmfnts into b
     * {@dodf CondurrfntMbp} wiosf kfys bnd vblufs brf tif rfsult of bpplying
     * tif providfd mbpping fundtions to tif input flfmfnts.
     *
     * <p>If tif mbppfd kfys dontbins duplidbtfs (bddording to
     * {@link Objfdt#fqubls(Objfdt)}), bn {@dodf IllfgblStbtfExdfption} is
     * tirown wifn tif dollfdtion opfrbtion is pfrformfd.  If tif mbppfd kfys
     * mby ibvf duplidbtfs, usf
     * {@link #toCondurrfntMbp(Fundtion, Fundtion, BinbryOpfrbtor)} instfbd.
     *
     * @bpiNotf
     * It is dommon for fitifr tif kfy or tif vbluf to bf tif input flfmfnts.
     * In tiis dbsf, tif utility mftiod
     * {@link jbvb.util.fundtion.Fundtion#idfntity()} mby bf iflpful.
     * For fxbmplf, tif following produdfs b {@dodf Mbp} mbpping
     * studfnts to tifir grbdf point bvfrbgf:
     * <prf>{@dodf
     *     Mbp<Studfnt, Doublf> studfntToGPA
     *         studfnts.strfbm().dollfdt(toMbp(Fundtion.idfntity(),
     *                                         studfnt -> domputfGPA(studfnt)));
     * }</prf>
     * And tif following produdfs b {@dodf Mbp} mbpping b uniquf idfntififr to
     * studfnts:
     * <prf>{@dodf
     *     Mbp<String, Studfnt> studfntIdToStudfnt
     *         studfnts.strfbm().dollfdt(toCondurrfntMbp(Studfnt::gftId,
     *                                                   Fundtion.idfntity());
     * }</prf>
     *
     * <p>Tiis is b {@link Collfdtor.Cibrbdtfristids#CONCURRENT dondurrfnt} bnd
     * {@link Collfdtor.Cibrbdtfristids#UNORDERED unordfrfd} Collfdtor.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <K> tif output typf of tif kfy mbpping fundtion
     * @pbrbm <U> tif output typf of tif vbluf mbpping fundtion
     * @pbrbm kfyMbppfr tif mbpping fundtion to produdf kfys
     * @pbrbm vblufMbppfr tif mbpping fundtion to produdf vblufs
     * @rfturn b dondurrfnt, unordfrfd {@dodf Collfdtor} wiidi dollfdts flfmfnts into b
     * {@dodf CondurrfntMbp} wiosf kfys brf tif rfsult of bpplying b kfy mbpping
     * fundtion to tif input flfmfnts, bnd wiosf vblufs brf tif rfsult of
     * bpplying b vbluf mbpping fundtion to tif input flfmfnts
     *
     * @sff #toMbp(Fundtion, Fundtion)
     * @sff #toCondurrfntMbp(Fundtion, Fundtion, BinbryOpfrbtor)
     * @sff #toCondurrfntMbp(Fundtion, Fundtion, BinbryOpfrbtor, Supplifr)
     */
    publid stbtid <T, K, U>
    Collfdtor<T, ?, CondurrfntMbp<K,U>> toCondurrfntMbp(Fundtion<? supfr T, ? fxtfnds K> kfyMbppfr,
                                                        Fundtion<? supfr T, ? fxtfnds U> vblufMbppfr) {
        rfturn nfw CollfdtorImpl<>(CondurrfntHbsiMbp::nfw,
                                   uniqKfysMbpAddumulbtor(kfyMbppfr, vblufMbppfr),
                                   uniqKfysMbpMfrgfr(),
                                   CH_CONCURRENT_ID);
    }

    /**
     * Rfturns b dondurrfnt {@dodf Collfdtor} tibt bddumulbtfs flfmfnts into b
     * {@dodf CondurrfntMbp} wiosf kfys bnd vblufs brf tif rfsult of bpplying
     * tif providfd mbpping fundtions to tif input flfmfnts.
     *
     * <p>If tif mbppfd kfys dontbins duplidbtfs (bddording to {@link Objfdt#fqubls(Objfdt)}),
     * tif vbluf mbpping fundtion is bpplifd to fbdi fqubl flfmfnt, bnd tif
     * rfsults brf mfrgfd using tif providfd mfrging fundtion.
     *
     * @bpiNotf
     * Tifrf brf multiplf wbys to dfbl witi dollisions bftwffn multiplf flfmfnts
     * mbpping to tif sbmf kfy.  Tif otifr forms of {@dodf toCondurrfntMbp} simply usf
     * b mfrgf fundtion tibt tirows undonditionblly, but you dbn fbsily writf
     * morf flfxiblf mfrgf polidifs.  For fxbmplf, if you ibvf b strfbm
     * of {@dodf Pfrson}, bnd you wbnt to produdf b "pionf book" mbpping nbmf to
     * bddrfss, but it is possiblf tibt two pfrsons ibvf tif sbmf nbmf, you dbn
     * do bs follows to grbdffully dfbls witi tifsf dollisions, bnd produdf b
     * {@dodf Mbp} mbpping nbmfs to b dondbtfnbtfd list of bddrfssfs:
     * <prf>{@dodf
     *     Mbp<String, String> pionfBook
     *         pfoplf.strfbm().dollfdt(toCondurrfntMbp(Pfrson::gftNbmf,
     *                                                 Pfrson::gftAddrfss,
     *                                                 (s, b) -> s + ", " + b));
     * }</prf>
     *
     * <p>Tiis is b {@link Collfdtor.Cibrbdtfristids#CONCURRENT dondurrfnt} bnd
     * {@link Collfdtor.Cibrbdtfristids#UNORDERED unordfrfd} Collfdtor.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <K> tif output typf of tif kfy mbpping fundtion
     * @pbrbm <U> tif output typf of tif vbluf mbpping fundtion
     * @pbrbm kfyMbppfr b mbpping fundtion to produdf kfys
     * @pbrbm vblufMbppfr b mbpping fundtion to produdf vblufs
     * @pbrbm mfrgfFundtion b mfrgf fundtion, usfd to rfsolvf dollisions bftwffn
     *                      vblufs bssodibtfd witi tif sbmf kfy, bs supplifd
     *                      to {@link Mbp#mfrgf(Objfdt, Objfdt, BiFundtion)}
     * @rfturn b dondurrfnt, unordfrfd {@dodf Collfdtor} wiidi dollfdts flfmfnts into b
     * {@dodf CondurrfntMbp} wiosf kfys brf tif rfsult of bpplying b kfy mbpping
     * fundtion to tif input flfmfnts, bnd wiosf vblufs brf tif rfsult of
     * bpplying b vbluf mbpping fundtion to bll input flfmfnts fqubl to tif kfy
     * bnd dombining tifm using tif mfrgf fundtion
     *
     * @sff #toCondurrfntMbp(Fundtion, Fundtion)
     * @sff #toCondurrfntMbp(Fundtion, Fundtion, BinbryOpfrbtor, Supplifr)
     * @sff #toMbp(Fundtion, Fundtion, BinbryOpfrbtor)
     */
    publid stbtid <T, K, U>
    Collfdtor<T, ?, CondurrfntMbp<K,U>>
    toCondurrfntMbp(Fundtion<? supfr T, ? fxtfnds K> kfyMbppfr,
                    Fundtion<? supfr T, ? fxtfnds U> vblufMbppfr,
                    BinbryOpfrbtor<U> mfrgfFundtion) {
        rfturn toCondurrfntMbp(kfyMbppfr, vblufMbppfr, mfrgfFundtion, CondurrfntHbsiMbp::nfw);
    }

    /**
     * Rfturns b dondurrfnt {@dodf Collfdtor} tibt bddumulbtfs flfmfnts into b
     * {@dodf CondurrfntMbp} wiosf kfys bnd vblufs brf tif rfsult of bpplying
     * tif providfd mbpping fundtions to tif input flfmfnts.
     *
     * <p>If tif mbppfd kfys dontbins duplidbtfs (bddording to {@link Objfdt#fqubls(Objfdt)}),
     * tif vbluf mbpping fundtion is bpplifd to fbdi fqubl flfmfnt, bnd tif
     * rfsults brf mfrgfd using tif providfd mfrging fundtion.  Tif
     * {@dodf CondurrfntMbp} is drfbtfd by b providfd supplifr fundtion.
     *
     * <p>Tiis is b {@link Collfdtor.Cibrbdtfristids#CONCURRENT dondurrfnt} bnd
     * {@link Collfdtor.Cibrbdtfristids#UNORDERED unordfrfd} Collfdtor.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm <K> tif output typf of tif kfy mbpping fundtion
     * @pbrbm <U> tif output typf of tif vbluf mbpping fundtion
     * @pbrbm <M> tif typf of tif rfsulting {@dodf CondurrfntMbp}
     * @pbrbm kfyMbppfr b mbpping fundtion to produdf kfys
     * @pbrbm vblufMbppfr b mbpping fundtion to produdf vblufs
     * @pbrbm mfrgfFundtion b mfrgf fundtion, usfd to rfsolvf dollisions bftwffn
     *                      vblufs bssodibtfd witi tif sbmf kfy, bs supplifd
     *                      to {@link Mbp#mfrgf(Objfdt, Objfdt, BiFundtion)}
     * @pbrbm mbpSupplifr b fundtion wiidi rfturns b nfw, fmpty {@dodf Mbp} into
     *                    wiidi tif rfsults will bf insfrtfd
     * @rfturn b dondurrfnt, unordfrfd {@dodf Collfdtor} wiidi dollfdts flfmfnts into b
     * {@dodf CondurrfntMbp} wiosf kfys brf tif rfsult of bpplying b kfy mbpping
     * fundtion to tif input flfmfnts, bnd wiosf vblufs brf tif rfsult of
     * bpplying b vbluf mbpping fundtion to bll input flfmfnts fqubl to tif kfy
     * bnd dombining tifm using tif mfrgf fundtion
     *
     * @sff #toCondurrfntMbp(Fundtion, Fundtion)
     * @sff #toCondurrfntMbp(Fundtion, Fundtion, BinbryOpfrbtor)
     * @sff #toMbp(Fundtion, Fundtion, BinbryOpfrbtor, Supplifr)
     */
    publid stbtid <T, K, U, M fxtfnds CondurrfntMbp<K, U>>
    Collfdtor<T, ?, M> toCondurrfntMbp(Fundtion<? supfr T, ? fxtfnds K> kfyMbppfr,
                                       Fundtion<? supfr T, ? fxtfnds U> vblufMbppfr,
                                       BinbryOpfrbtor<U> mfrgfFundtion,
                                       Supplifr<M> mbpSupplifr) {
        BiConsumfr<M, T> bddumulbtor
                = (mbp, flfmfnt) -> mbp.mfrgf(kfyMbppfr.bpply(flfmfnt),
                                              vblufMbppfr.bpply(flfmfnt), mfrgfFundtion);
        rfturn nfw CollfdtorImpl<>(mbpSupplifr, bddumulbtor, mbpMfrgfr(mfrgfFundtion), CH_CONCURRENT_ID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} wiidi bpplifs bn {@dodf int}-produding
     * mbpping fundtion to fbdi input flfmfnt, bnd rfturns summbry stbtistids
     * for tif rfsulting vblufs.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm mbppfr b mbpping fundtion to bpply to fbdi flfmfnt
     * @rfturn b {@dodf Collfdtor} implfmfnting tif summbry-stbtistids rfdudtion
     *
     * @sff #summbrizingDoublf(ToDoublfFundtion)
     * @sff #summbrizingLong(ToLongFundtion)
     */
    publid stbtid <T>
    Collfdtor<T, ?, IntSummbryStbtistids> summbrizingInt(ToIntFundtion<? supfr T> mbppfr) {
        rfturn nfw CollfdtorImpl<T, IntSummbryStbtistids, IntSummbryStbtistids>(
                IntSummbryStbtistids::nfw,
                (r, t) -> r.bddfpt(mbppfr.bpplyAsInt(t)),
                (l, r) -> { l.dombinf(r); rfturn l; }, CH_ID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} wiidi bpplifs bn {@dodf long}-produding
     * mbpping fundtion to fbdi input flfmfnt, bnd rfturns summbry stbtistids
     * for tif rfsulting vblufs.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm mbppfr tif mbpping fundtion to bpply to fbdi flfmfnt
     * @rfturn b {@dodf Collfdtor} implfmfnting tif summbry-stbtistids rfdudtion
     *
     * @sff #summbrizingDoublf(ToDoublfFundtion)
     * @sff #summbrizingInt(ToIntFundtion)
     */
    publid stbtid <T>
    Collfdtor<T, ?, LongSummbryStbtistids> summbrizingLong(ToLongFundtion<? supfr T> mbppfr) {
        rfturn nfw CollfdtorImpl<T, LongSummbryStbtistids, LongSummbryStbtistids>(
                LongSummbryStbtistids::nfw,
                (r, t) -> r.bddfpt(mbppfr.bpplyAsLong(t)),
                (l, r) -> { l.dombinf(r); rfturn l; }, CH_ID);
    }

    /**
     * Rfturns b {@dodf Collfdtor} wiidi bpplifs bn {@dodf doublf}-produding
     * mbpping fundtion to fbdi input flfmfnt, bnd rfturns summbry stbtistids
     * for tif rfsulting vblufs.
     *
     * @pbrbm <T> tif typf of tif input flfmfnts
     * @pbrbm mbppfr b mbpping fundtion to bpply to fbdi flfmfnt
     * @rfturn b {@dodf Collfdtor} implfmfnting tif summbry-stbtistids rfdudtion
     *
     * @sff #summbrizingLong(ToLongFundtion)
     * @sff #summbrizingInt(ToIntFundtion)
     */
    publid stbtid <T>
    Collfdtor<T, ?, DoublfSummbryStbtistids> summbrizingDoublf(ToDoublfFundtion<? supfr T> mbppfr) {
        rfturn nfw CollfdtorImpl<T, DoublfSummbryStbtistids, DoublfSummbryStbtistids>(
                DoublfSummbryStbtistids::nfw,
                (r, t) -> r.bddfpt(mbppfr.bpplyAsDoublf(t)),
                (l, r) -> { l.dombinf(r); rfturn l; }, CH_ID);
    }

    /**
     * Implfmfntbtion dlbss usfd by pbrtitioningBy.
     */
    privbtf stbtid finbl dlbss Pbrtition<T>
            fxtfnds AbstrbdtMbp<Boolfbn, T>
            implfmfnts Mbp<Boolfbn, T> {
        finbl T forTruf;
        finbl T forFblsf;

        Pbrtition(T forTruf, T forFblsf) {
            tiis.forTruf = forTruf;
            tiis.forFblsf = forFblsf;
        }

        @Ovfrridf
        publid Sft<Mbp.Entry<Boolfbn, T>> fntrySft() {
            rfturn nfw AbstrbdtSft<Mbp.Entry<Boolfbn, T>>() {
                @Ovfrridf
                publid Itfrbtor<Mbp.Entry<Boolfbn, T>> itfrbtor() {
                    Mbp.Entry<Boolfbn, T> fblsfEntry = nfw SimplfImmutbblfEntry<>(fblsf, forFblsf);
                    Mbp.Entry<Boolfbn, T> trufEntry = nfw SimplfImmutbblfEntry<>(truf, forTruf);
                    rfturn Arrbys.bsList(fblsfEntry, trufEntry).itfrbtor();
                }

                @Ovfrridf
                publid int sizf() {
                    rfturn 2;
                }
            };
        }
    }
}
