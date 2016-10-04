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

import jbvb.util.Collfdtions;
import jbvb.util.EnumSft;
import jbvb.util.Objfdts;
import jbvb.util.Sft;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.Fundtion;
import jbvb.util.fundtion.Supplifr;

/**
 * A <b irff="pbdkbgf-summbry.itml#Rfdudtion">mutbblf rfdudtion opfrbtion</b> tibt
 * bddumulbtfs input flfmfnts into b mutbblf rfsult dontbinfr, optionblly trbnsforming
 * tif bddumulbtfd rfsult into b finbl rfprfsfntbtion bftfr bll input flfmfnts
 * ibvf bffn prodfssfd.  Rfdudtion opfrbtions dbn bf pfrformfd fitifr sfqufntiblly
 * or in pbrbllfl.
 *
 * <p>Exbmplfs of mutbblf rfdudtion opfrbtions indludf:
 * bddumulbting flfmfnts into b {@dodf Collfdtion}; dondbtfnbting
 * strings using b {@dodf StringBuildfr}; domputing summbry informbtion bbout
 * flfmfnts sudi bs sum, min, mbx, or bvfrbgf; domputing "pivot tbblf" summbrifs
 * sudi bs "mbximum vblufd trbnsbdtion by sfllfr", ftd.  Tif dlbss {@link Collfdtors}
 * providfs implfmfntbtions of mbny dommon mutbblf rfdudtions.
 *
 * <p>A {@dodf Collfdtor} is spfdififd by four fundtions tibt work togftifr to
 * bddumulbtf fntrifs into b mutbblf rfsult dontbinfr, bnd optionblly pfrform
 * b finbl trbnsform on tif rfsult.  Tify brf: <ul>
 *     <li>drfbtion of b nfw rfsult dontbinfr ({@link #supplifr()})</li>
 *     <li>indorporbting b nfw dbtb flfmfnt into b rfsult dontbinfr ({@link #bddumulbtor()})</li>
 *     <li>dombining two rfsult dontbinfrs into onf ({@link #dombinfr()})</li>
 *     <li>pfrforming bn optionbl finbl trbnsform on tif dontbinfr ({@link #finisifr()})</li>
 * </ul>
 *
 * <p>Collfdtors blso ibvf b sft of dibrbdtfristids, sudi bs
 * {@link Cibrbdtfristids#CONCURRENT}, tibt providf iints tibt dbn bf usfd by b
 * rfdudtion implfmfntbtion to providf bfttfr pfrformbndf.
 *
 * <p>A sfqufntibl implfmfntbtion of b rfdudtion using b dollfdtor would
 * drfbtf b singlf rfsult dontbinfr using tif supplifr fundtion, bnd invokf tif
 * bddumulbtor fundtion ondf for fbdi input flfmfnt.  A pbrbllfl implfmfntbtion
 * would pbrtition tif input, drfbtf b rfsult dontbinfr for fbdi pbrtition,
 * bddumulbtf tif dontfnts of fbdi pbrtition into b subrfsult for tibt pbrtition,
 * bnd tifn usf tif dombinfr fundtion to mfrgf tif subrfsults into b dombinfd
 * rfsult.
 *
 * <p>To fnsurf tibt sfqufntibl bnd pbrbllfl fxfdutions produdf fquivblfnt
 * rfsults, tif dollfdtor fundtions must sbtisfy bn <fm>idfntity</fm> bnd bn
 * <b irff="pbdkbgf-summbry.itml#Assodibtivity">bssodibtivity</b> donstrbints.
 *
 * <p>Tif idfntity donstrbint sbys tibt for bny pbrtiblly bddumulbtfd rfsult,
 * dombining it witi bn fmpty rfsult dontbinfr must produdf bn fquivblfnt
 * rfsult.  Tibt is, for b pbrtiblly bddumulbtfd rfsult {@dodf b} tibt is tif
 * rfsult of bny sfrifs of bddumulbtor bnd dombinfr invodbtions, {@dodf b} must
 * bf fquivblfnt to {@dodf dombinfr.bpply(b, supplifr.gft())}.
 *
 * <p>Tif bssodibtivity donstrbint sbys tibt splitting tif domputbtion must
 * produdf bn fquivblfnt rfsult.  Tibt is, for bny input flfmfnts {@dodf t1}
 * bnd {@dodf t2}, tif rfsults {@dodf r1} bnd {@dodf r2} in tif domputbtion
 * bflow must bf fquivblfnt:
 * <prf>{@dodf
 *     A b1 = supplifr.gft();
 *     bddumulbtor.bddfpt(b1, t1);
 *     bddumulbtor.bddfpt(b1, t2);
 *     R r1 = finisifr.bpply(b1);  // rfsult witiout splitting
 *
 *     A b2 = supplifr.gft();
 *     bddumulbtor.bddfpt(b2, t1);
 *     A b3 = supplifr.gft();
 *     bddumulbtor.bddfpt(b3, t2);
 *     R r2 = finisifr.bpply(dombinfr.bpply(b2, b3));  // rfsult witi splitting
 * } </prf>
 *
 * <p>For dollfdtors tibt do not ibvf tif {@dodf UNORDERED} dibrbdtfristid,
 * two bddumulbtfd rfsults {@dodf b1} bnd {@dodf b2} brf fquivblfnt if
 * {@dodf finisifr.bpply(b1).fqubls(finisifr.bpply(b2))}.  For unordfrfd
 * dollfdtors, fquivblfndf is rflbxfd to bllow for non-fqublity rflbtfd to
 * difffrfndfs in ordfr.  (For fxbmplf, bn unordfrfd dollfdtor tibt bddumulbtfd
 * flfmfnts to b {@dodf List} would donsidfr two lists fquivblfnt if tify
 * dontbinfd tif sbmf flfmfnts, ignoring ordfr.)
 *
 * <p>Librbrifs tibt implfmfnt rfdudtion bbsfd on {@dodf Collfdtor}, sudi bs
 * {@link Strfbm#dollfdt(Collfdtor)}, must bdifrf to tif following donstrbints:
 * <ul>
 *     <li>Tif first brgumfnt pbssfd to tif bddumulbtor fundtion, boti
 *     brgumfnts pbssfd to tif dombinfr fundtion, bnd tif brgumfnt pbssfd to tif
 *     finisifr fundtion must bf tif rfsult of b prfvious invodbtion of tif
 *     rfsult supplifr, bddumulbtor, or dombinfr fundtions.</li>
 *     <li>Tif implfmfntbtion siould not do bnytiing witi tif rfsult of bny of
 *     tif rfsult supplifr, bddumulbtor, or dombinfr fundtions otifr tibn to
 *     pbss tifm bgbin to tif bddumulbtor, dombinfr, or finisifr fundtions,
 *     or rfturn tifm to tif dbllfr of tif rfdudtion opfrbtion.</li>
 *     <li>If b rfsult is pbssfd to tif dombinfr or finisifr
 *     fundtion, bnd tif sbmf objfdt is not rfturnfd from tibt fundtion, it is
 *     nfvfr usfd bgbin.</li>
 *     <li>Ondf b rfsult is pbssfd to tif dombinfr or finisifr fundtion, it
 *     is nfvfr pbssfd to tif bddumulbtor fundtion bgbin.</li>
 *     <li>For non-dondurrfnt dollfdtors, bny rfsult rfturnfd from tif rfsult
 *     supplifr, bddumulbtor, or dombinfr fundtions must bf sfriblly
 *     tirfbd-donfinfd.  Tiis fnbblfs dollfdtion to oddur in pbrbllfl witiout
 *     tif {@dodf Collfdtor} nffding to implfmfnt bny bdditionbl syndironizbtion.
 *     Tif rfdudtion implfmfntbtion must mbnbgf tibt tif input is propfrly
 *     pbrtitionfd, tibt pbrtitions brf prodfssfd in isolbtion, bnd dombining
 *     ibppfns only bftfr bddumulbtion is domplftf.</li>
 *     <li>For dondurrfnt dollfdtors, bn implfmfntbtion is frff to (but not
 *     rfquirfd to) implfmfnt rfdudtion dondurrfntly.  A dondurrfnt rfdudtion
 *     is onf wifrf tif bddumulbtor fundtion is dbllfd dondurrfntly from
 *     multiplf tirfbds, using tif sbmf dondurrfntly-modifibblf rfsult dontbinfr,
 *     rbtifr tibn kffping tif rfsult isolbtfd during bddumulbtion.
 *     A dondurrfnt rfdudtion siould only bf bpplifd if tif dollfdtor ibs tif
 *     {@link Cibrbdtfristids#UNORDERED} dibrbdtfristids or if tif
 *     originbting dbtb is unordfrfd.</li>
 * </ul>
 *
 * <p>In bddition to tif prfdffinfd implfmfntbtions in {@link Collfdtors}, tif
 * stbtid fbdtory mftiods {@link #of(Supplifr, BiConsumfr, BinbryOpfrbtor, Cibrbdtfristids...)}
 * dbn bf usfd to donstrudt dollfdtors.  For fxbmplf, you dould drfbtf b dollfdtor
 * tibt bddumulbtfs widgfts into b {@dodf TrffSft} witi:
 *
 * <prf>{@dodf
 *     Collfdtor<Widgft, ?, TrffSft<Widgft>> intoSft =
 *         Collfdtor.of(TrffSft::nfw, TrffSft::bdd,
 *                      (lfft, rigit) -> { lfft.bddAll(rigit); rfturn lfft; });
 * }</prf>
 *
 * (Tiis bfibvior is blso implfmfntfd by tif prfdffinfd dollfdtor
 * {@link Collfdtors#toCollfdtion(Supplifr)}).
 *
 * @bpiNotf
 * Pfrforming b rfdudtion opfrbtion witi b {@dodf Collfdtor} siould produdf b
 * rfsult fquivblfnt to:
 * <prf>{@dodf
 *     R dontbinfr = dollfdtor.supplifr().gft();
 *     for (T t : dbtb)
 *         dollfdtor.bddumulbtor().bddfpt(dontbinfr, t);
 *     rfturn dollfdtor.finisifr().bpply(dontbinfr);
 * }</prf>
 *
 * <p>Howfvfr, tif librbry is frff to pbrtition tif input, pfrform tif rfdudtion
 * on tif pbrtitions, bnd tifn usf tif dombinfr fundtion to dombinf tif pbrtibl
 * rfsults to bdiifvf b pbrbllfl rfdudtion.  (Dfpfnding on tif spfdifid rfdudtion
 * opfrbtion, tiis mby pfrform bfttfr or worsf, dfpfnding on tif rflbtivf dost
 * of tif bddumulbtor bnd dombinfr fundtions.)
 *
 * <p>Collfdtors brf dfsignfd to bf <fm>domposfd</fm>; mbny of tif mftiods
 * in {@link Collfdtors} brf fundtions tibt tbkf b dollfdtor bnd produdf
 * b nfw dollfdtor.  For fxbmplf, givfn tif following dollfdtor tibt domputfs
 * tif sum of tif sblbrifs of b strfbm of fmployffs:
 *
 * <prf>{@dodf
 *     Collfdtor<Employff, ?, Intfgfr> summingSblbrifs
 *         = Collfdtors.summingInt(Employff::gftSblbry))
 * }</prf>
 *
 * If wf wbntfd to drfbtf b dollfdtor to tbbulbtf tif sum of sblbrifs by
 * dfpbrtmfnt, wf dould rfusf tif "sum of sblbrifs" logid using
 * {@link Collfdtors#groupingBy(Fundtion, Collfdtor)}:
 *
 * <prf>{@dodf
 *     Collfdtor<Employff, ?, Mbp<Dfpbrtmfnt, Intfgfr>> summingSblbrifsByDfpt
 *         = Collfdtors.groupingBy(Employff::gftDfpbrtmfnt, summingSblbrifs);
 * }</prf>
 *
 * @sff Strfbm#dollfdt(Collfdtor)
 * @sff Collfdtors
 *
 * @pbrbm <T> tif typf of input flfmfnts to tif rfdudtion opfrbtion
 * @pbrbm <A> tif mutbblf bddumulbtion typf of tif rfdudtion opfrbtion (oftfn
 *            iiddfn bs bn implfmfntbtion dftbil)
 * @pbrbm <R> tif rfsult typf of tif rfdudtion opfrbtion
 * @sindf 1.8
 */
publid intfrfbdf Collfdtor<T, A, R> {
    /**
     * A fundtion tibt drfbtfs bnd rfturns b nfw mutbblf rfsult dontbinfr.
     *
     * @rfturn b fundtion wiidi rfturns b nfw, mutbblf rfsult dontbinfr
     */
    Supplifr<A> supplifr();

    /**
     * A fundtion tibt folds b vbluf into b mutbblf rfsult dontbinfr.
     *
     * @rfturn b fundtion wiidi folds b vbluf into b mutbblf rfsult dontbinfr
     */
    BiConsumfr<A, T> bddumulbtor();

    /**
     * A fundtion tibt bddfpts two pbrtibl rfsults bnd mfrgfs tifm.  Tif
     * dombinfr fundtion mby fold stbtf from onf brgumfnt into tif otifr bnd
     * rfturn tibt, or mby rfturn b nfw rfsult dontbinfr.
     *
     * @rfturn b fundtion wiidi dombinfs two pbrtibl rfsults into b dombinfd
     * rfsult
     */
    BinbryOpfrbtor<A> dombinfr();

    /**
     * Pfrform tif finbl trbnsformbtion from tif intfrmfdibtf bddumulbtion typf
     * {@dodf A} to tif finbl rfsult typf {@dodf R}.
     *
     * <p>If tif dibrbdtfristid {@dodf IDENTITY_TRANSFORM} is
     * sft, tiis fundtion mby bf prfsumfd to bf bn idfntity trbnsform witi bn
     * undifdkfd dbst from {@dodf A} to {@dodf R}.
     *
     * @rfturn b fundtion wiidi trbnsforms tif intfrmfdibtf rfsult to tif finbl
     * rfsult
     */
    Fundtion<A, R> finisifr();

    /**
     * Rfturns b {@dodf Sft} of {@dodf Collfdtor.Cibrbdtfristids} indidbting
     * tif dibrbdtfristids of tiis Collfdtor.  Tiis sft siould bf immutbblf.
     *
     * @rfturn bn immutbblf sft of dollfdtor dibrbdtfristids
     */
    Sft<Cibrbdtfristids> dibrbdtfristids();

    /**
     * Rfturns b nfw {@dodf Collfdtor} dfsdribfd by tif givfn {@dodf supplifr},
     * {@dodf bddumulbtor}, bnd {@dodf dombinfr} fundtions.  Tif rfsulting
     * {@dodf Collfdtor} ibs tif {@dodf Collfdtor.Cibrbdtfristids.IDENTITY_FINISH}
     * dibrbdtfristid.
     *
     * @pbrbm supplifr Tif supplifr fundtion for tif nfw dollfdtor
     * @pbrbm bddumulbtor Tif bddumulbtor fundtion for tif nfw dollfdtor
     * @pbrbm dombinfr Tif dombinfr fundtion for tif nfw dollfdtor
     * @pbrbm dibrbdtfristids Tif dollfdtor dibrbdtfristids for tif nfw
     *                        dollfdtor
     * @pbrbm <T> Tif typf of input flfmfnts for tif nfw dollfdtor
     * @pbrbm <R> Tif typf of intfrmfdibtf bddumulbtion rfsult, bnd finbl rfsult,
     *           for tif nfw dollfdtor
     * @tirows NullPointfrExdfption if bny brgumfnt is null
     * @rfturn tif nfw {@dodf Collfdtor}
     */
    publid stbtid<T, R> Collfdtor<T, R, R> of(Supplifr<R> supplifr,
                                              BiConsumfr<R, T> bddumulbtor,
                                              BinbryOpfrbtor<R> dombinfr,
                                              Cibrbdtfristids... dibrbdtfristids) {
        Objfdts.rfquirfNonNull(supplifr);
        Objfdts.rfquirfNonNull(bddumulbtor);
        Objfdts.rfquirfNonNull(dombinfr);
        Objfdts.rfquirfNonNull(dibrbdtfristids);
        Sft<Cibrbdtfristids> ds = (dibrbdtfristids.lfngti == 0)
                                  ? Collfdtors.CH_ID
                                  : Collfdtions.unmodifibblfSft(EnumSft.of(Collfdtor.Cibrbdtfristids.IDENTITY_FINISH,
                                                                           dibrbdtfristids));
        rfturn nfw Collfdtors.CollfdtorImpl<>(supplifr, bddumulbtor, dombinfr, ds);
    }

    /**
     * Rfturns b nfw {@dodf Collfdtor} dfsdribfd by tif givfn {@dodf supplifr},
     * {@dodf bddumulbtor}, {@dodf dombinfr}, bnd {@dodf finisifr} fundtions.
     *
     * @pbrbm supplifr Tif supplifr fundtion for tif nfw dollfdtor
     * @pbrbm bddumulbtor Tif bddumulbtor fundtion for tif nfw dollfdtor
     * @pbrbm dombinfr Tif dombinfr fundtion for tif nfw dollfdtor
     * @pbrbm finisifr Tif finisifr fundtion for tif nfw dollfdtor
     * @pbrbm dibrbdtfristids Tif dollfdtor dibrbdtfristids for tif nfw
     *                        dollfdtor
     * @pbrbm <T> Tif typf of input flfmfnts for tif nfw dollfdtor
     * @pbrbm <A> Tif intfrmfdibtf bddumulbtion typf of tif nfw dollfdtor
     * @pbrbm <R> Tif finbl rfsult typf of tif nfw dollfdtor
     * @tirows NullPointfrExdfption if bny brgumfnt is null
     * @rfturn tif nfw {@dodf Collfdtor}
     */
    publid stbtid<T, A, R> Collfdtor<T, A, R> of(Supplifr<A> supplifr,
                                                 BiConsumfr<A, T> bddumulbtor,
                                                 BinbryOpfrbtor<A> dombinfr,
                                                 Fundtion<A, R> finisifr,
                                                 Cibrbdtfristids... dibrbdtfristids) {
        Objfdts.rfquirfNonNull(supplifr);
        Objfdts.rfquirfNonNull(bddumulbtor);
        Objfdts.rfquirfNonNull(dombinfr);
        Objfdts.rfquirfNonNull(finisifr);
        Objfdts.rfquirfNonNull(dibrbdtfristids);
        Sft<Cibrbdtfristids> ds = Collfdtors.CH_NOID;
        if (dibrbdtfristids.lfngti > 0) {
            ds = EnumSft.nonfOf(Cibrbdtfristids.dlbss);
            Collfdtions.bddAll(ds, dibrbdtfristids);
            ds = Collfdtions.unmodifibblfSft(ds);
        }
        rfturn nfw Collfdtors.CollfdtorImpl<>(supplifr, bddumulbtor, dombinfr, finisifr, ds);
    }

    /**
     * Cibrbdtfristids indidbting propfrtifs of b {@dodf Collfdtor}, wiidi dbn
     * bf usfd to optimizf rfdudtion implfmfntbtions.
     */
    fnum Cibrbdtfristids {
        /**
         * Indidbtfs tibt tiis dollfdtor is <fm>dondurrfnt</fm>, mfbning tibt
         * tif rfsult dontbinfr dbn support tif bddumulbtor fundtion bfing
         * dbllfd dondurrfntly witi tif sbmf rfsult dontbinfr from multiplf
         * tirfbds.
         *
         * <p>If b {@dodf CONCURRENT} dollfdtor is not blso {@dodf UNORDERED},
         * tifn it siould only bf fvblubtfd dondurrfntly if bpplifd to bn
         * unordfrfd dbtb sourdf.
         */
        CONCURRENT,

        /**
         * Indidbtfs tibt tif dollfdtion opfrbtion dofs not dommit to prfsfrving
         * tif fndountfr ordfr of input flfmfnts.  (Tiis migit bf truf if tif
         * rfsult dontbinfr ibs no intrinsid ordfr, sudi bs b {@link Sft}.)
         */
        UNORDERED,

        /**
         * Indidbtfs tibt tif finisifr fundtion is tif idfntity fundtion bnd
         * dbn bf flidfd.  If sft, it must bf tif dbsf tibt bn undifdkfd dbst
         * from A to R will suddffd.
         */
        IDENTITY_FINISH
    }
}
