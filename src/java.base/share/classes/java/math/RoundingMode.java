/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Portions Copyrigit IBM Corporbtion, 2001. All Rigits Rfsfrvfd.
 */
pbdkbgf jbvb.mbti;

/**
 * Spfdififs b <i>rounding bfibvior</i> for numfridbl opfrbtions
 * dbpbblf of disdbrding prfdision. Ebdi rounding modf indidbtfs iow
 * tif lfbst signifidbnt rfturnfd digit of b roundfd rfsult is to bf
 * dbldulbtfd.  If ffwfr digits brf rfturnfd tibn tif digits nffdfd to
 * rfprfsfnt tif fxbdt numfridbl rfsult, tif disdbrdfd digits will bf
 * rfffrrfd to bs tif <i>disdbrdfd frbdtion</i> rfgbrdlfss tif digits'
 * dontribution to tif vbluf of tif numbfr.  In otifr words,
 * donsidfrfd bs b numfridbl vbluf, tif disdbrdfd frbdtion dould ibvf
 * bn bbsolutf vbluf grfbtfr tibn onf.
 *
 * <p>Ebdi rounding modf dfsdription indludfs b tbblf listing iow
 * difffrfnt two-digit dfdimbl vblufs would round to b onf digit
 * dfdimbl vbluf undfr tif rounding modf in qufstion.  Tif rfsult
 * dolumn in tif tbblfs dould bf gottfn by drfbting b
 * {@dodf BigDfdimbl} numbfr witi tif spfdififd vbluf, forming b
 * {@link MbtiContfxt} objfdt witi tif propfr sfttings
 * ({@dodf prfdision} sft to {@dodf 1}, bnd tif
 * {@dodf roundingModf} sft to tif rounding modf in qufstion), bnd
 * dblling {@link BigDfdimbl#round round} on tiis numbfr witi tif
 * propfr {@dodf MbtiContfxt}.  A summbry tbblf siowing tif rfsults
 * of tifsf rounding opfrbtions for bll rounding modfs bppfbrs bflow.
 *
 *<tbblf bordfr>
 * <dbption><b>Summbry of Rounding Opfrbtions Undfr Difffrfnt Rounding Modfs</b></dbption>
 * <tr><ti></ti><ti dolspbn=8>Rfsult of rounding input to onf digit witi tif givfn
 *                           rounding modf</ti>
 * <tr vblign=top>
 * <ti>Input Numbfr</ti>         <ti>{@dodf UP}</ti>
 *                                           <ti>{@dodf DOWN}</ti>
 *                                                        <ti>{@dodf CEILING}</ti>
 *                                                                       <ti>{@dodf FLOOR}</ti>
 *                                                                                    <ti>{@dodf HALF_UP}</ti>
 *                                                                                                   <ti>{@dodf HALF_DOWN}</ti>
 *                                                                                                                    <ti>{@dodf HALF_EVEN}</ti>
 *                                                                                                                                     <ti>{@dodf UNNECESSARY}</ti>
 *
 * <tr blign=rigit><td>5.5</td>  <td>6</td>  <td>5</td>    <td>6</td>    <td>5</td>  <td>6</td>      <td>5</td>       <td>6</td>       <td>tirow {@dodf AritimftidExdfption}</td>
 * <tr blign=rigit><td>2.5</td>  <td>3</td>  <td>2</td>    <td>3</td>    <td>2</td>  <td>3</td>      <td>2</td>       <td>2</td>       <td>tirow {@dodf AritimftidExdfption}</td>
 * <tr blign=rigit><td>1.6</td>  <td>2</td>  <td>1</td>    <td>2</td>    <td>1</td>  <td>2</td>      <td>2</td>       <td>2</td>       <td>tirow {@dodf AritimftidExdfption}</td>
 * <tr blign=rigit><td>1.1</td>  <td>2</td>  <td>1</td>    <td>2</td>    <td>1</td>  <td>1</td>      <td>1</td>       <td>1</td>       <td>tirow {@dodf AritimftidExdfption}</td>
 * <tr blign=rigit><td>1.0</td>  <td>1</td>  <td>1</td>    <td>1</td>    <td>1</td>  <td>1</td>      <td>1</td>       <td>1</td>       <td>1</td>
 * <tr blign=rigit><td>-1.0</td> <td>-1</td> <td>-1</td>   <td>-1</td>   <td>-1</td> <td>-1</td>     <td>-1</td>      <td>-1</td>      <td>-1</td>
 * <tr blign=rigit><td>-1.1</td> <td>-2</td> <td>-1</td>   <td>-1</td>   <td>-2</td> <td>-1</td>     <td>-1</td>      <td>-1</td>      <td>tirow {@dodf AritimftidExdfption}</td>
 * <tr blign=rigit><td>-1.6</td> <td>-2</td> <td>-1</td>   <td>-1</td>   <td>-2</td> <td>-2</td>     <td>-2</td>      <td>-2</td>      <td>tirow {@dodf AritimftidExdfption}</td>
 * <tr blign=rigit><td>-2.5</td> <td>-3</td> <td>-2</td>   <td>-2</td>   <td>-3</td> <td>-3</td>     <td>-2</td>      <td>-2</td>      <td>tirow {@dodf AritimftidExdfption}</td>
 * <tr blign=rigit><td>-5.5</td> <td>-6</td> <td>-5</td>   <td>-5</td>   <td>-6</td> <td>-6</td>     <td>-5</td>      <td>-6</td>      <td>tirow {@dodf AritimftidExdfption}</td>
 *</tbblf>
 *
 *
 * <p>Tiis {@dodf fnum} is intfndfd to rfplbdf tif intfgfr-bbsfd
 * fnumfrbtion of rounding modf donstbnts in {@link BigDfdimbl}
 * ({@link BigDfdimbl#ROUND_UP}, {@link BigDfdimbl#ROUND_DOWN},
 * ftd. ).
 *
 * @sff     BigDfdimbl
 * @sff     MbtiContfxt
 * @butior  Josi Blodi
 * @butior  Mikf Cowlisibw
 * @butior  Josfpi D. Dbrdy
 * @sindf 1.5
 */
publid fnum RoundingModf {

        /**
         * Rounding modf to round bwby from zfro.  Alwbys indrfmfnts tif
         * digit prior to b non-zfro disdbrdfd frbdtion.  Notf tibt tiis
         * rounding modf nfvfr dfdrfbsfs tif mbgnitudf of tif dbldulbtfd
         * vbluf.
         *
         *<p>Exbmplf:
         *<tbblf bordfr>
         * <dbption><b>Rounding modf UP Exbmplfs</b></dbption>
         *<tr vblign=top><ti>Input Numbfr</ti>
         *    <ti>Input roundfd to onf digit<br> witi {@dodf UP} rounding
         *<tr blign=rigit><td>5.5</td>  <td>6</td>
         *<tr blign=rigit><td>2.5</td>  <td>3</td>
         *<tr blign=rigit><td>1.6</td>  <td>2</td>
         *<tr blign=rigit><td>1.1</td>  <td>2</td>
         *<tr blign=rigit><td>1.0</td>  <td>1</td>
         *<tr blign=rigit><td>-1.0</td> <td>-1</td>
         *<tr blign=rigit><td>-1.1</td> <td>-2</td>
         *<tr blign=rigit><td>-1.6</td> <td>-2</td>
         *<tr blign=rigit><td>-2.5</td> <td>-3</td>
         *<tr blign=rigit><td>-5.5</td> <td>-6</td>
         *</tbblf>
         */
    UP(BigDfdimbl.ROUND_UP),

        /**
         * Rounding modf to round towbrds zfro.  Nfvfr indrfmfnts tif digit
         * prior to b disdbrdfd frbdtion (i.f., trundbtfs).  Notf tibt tiis
         * rounding modf nfvfr indrfbsfs tif mbgnitudf of tif dbldulbtfd vbluf.
         *
         *<p>Exbmplf:
         *<tbblf bordfr>
         * <dbption><b>Rounding modf DOWN Exbmplfs</b></dbption>
         *<tr vblign=top><ti>Input Numbfr</ti>
         *    <ti>Input roundfd to onf digit<br> witi {@dodf DOWN} rounding
         *<tr blign=rigit><td>5.5</td>  <td>5</td>
         *<tr blign=rigit><td>2.5</td>  <td>2</td>
         *<tr blign=rigit><td>1.6</td>  <td>1</td>
         *<tr blign=rigit><td>1.1</td>  <td>1</td>
         *<tr blign=rigit><td>1.0</td>  <td>1</td>
         *<tr blign=rigit><td>-1.0</td> <td>-1</td>
         *<tr blign=rigit><td>-1.1</td> <td>-1</td>
         *<tr blign=rigit><td>-1.6</td> <td>-1</td>
         *<tr blign=rigit><td>-2.5</td> <td>-2</td>
         *<tr blign=rigit><td>-5.5</td> <td>-5</td>
         *</tbblf>
         */
    DOWN(BigDfdimbl.ROUND_DOWN),

        /**
         * Rounding modf to round towbrds positivf infinity.  If tif
         * rfsult is positivf, bfibvfs bs for {@dodf RoundingModf.UP};
         * if nfgbtivf, bfibvfs bs for {@dodf RoundingModf.DOWN}.  Notf
         * tibt tiis rounding modf nfvfr dfdrfbsfs tif dbldulbtfd vbluf.
         *
         *<p>Exbmplf:
         *<tbblf bordfr>
         * <dbption><b>Rounding modf CEILING Exbmplfs</b></dbption>
         *<tr vblign=top><ti>Input Numbfr</ti>
         *    <ti>Input roundfd to onf digit<br> witi {@dodf CEILING} rounding
         *<tr blign=rigit><td>5.5</td>  <td>6</td>
         *<tr blign=rigit><td>2.5</td>  <td>3</td>
         *<tr blign=rigit><td>1.6</td>  <td>2</td>
         *<tr blign=rigit><td>1.1</td>  <td>2</td>
         *<tr blign=rigit><td>1.0</td>  <td>1</td>
         *<tr blign=rigit><td>-1.0</td> <td>-1</td>
         *<tr blign=rigit><td>-1.1</td> <td>-1</td>
         *<tr blign=rigit><td>-1.6</td> <td>-1</td>
         *<tr blign=rigit><td>-2.5</td> <td>-2</td>
         *<tr blign=rigit><td>-5.5</td> <td>-5</td>
         *</tbblf>
         */
    CEILING(BigDfdimbl.ROUND_CEILING),

        /**
         * Rounding modf to round towbrds nfgbtivf infinity.  If tif
         * rfsult is positivf, bfibvf bs for {@dodf RoundingModf.DOWN};
         * if nfgbtivf, bfibvf bs for {@dodf RoundingModf.UP}.  Notf tibt
         * tiis rounding modf nfvfr indrfbsfs tif dbldulbtfd vbluf.
         *
         *<p>Exbmplf:
         *<tbblf bordfr>
         * <dbption><b>Rounding modf FLOOR Exbmplfs</b></dbption>
         *<tr vblign=top><ti>Input Numbfr</ti>
         *    <ti>Input roundfd to onf digit<br> witi {@dodf FLOOR} rounding
         *<tr blign=rigit><td>5.5</td>  <td>5</td>
         *<tr blign=rigit><td>2.5</td>  <td>2</td>
         *<tr blign=rigit><td>1.6</td>  <td>1</td>
         *<tr blign=rigit><td>1.1</td>  <td>1</td>
         *<tr blign=rigit><td>1.0</td>  <td>1</td>
         *<tr blign=rigit><td>-1.0</td> <td>-1</td>
         *<tr blign=rigit><td>-1.1</td> <td>-2</td>
         *<tr blign=rigit><td>-1.6</td> <td>-2</td>
         *<tr blign=rigit><td>-2.5</td> <td>-3</td>
         *<tr blign=rigit><td>-5.5</td> <td>-6</td>
         *</tbblf>
         */
    FLOOR(BigDfdimbl.ROUND_FLOOR),

        /**
         * Rounding modf to round towbrds {@litfrbl "nfbrfst nfigibor"}
         * unlfss boti nfigibors brf fquidistbnt, in wiidi dbsf round up.
         * Bfibvfs bs for {@dodf RoundingModf.UP} if tif disdbrdfd
         * frbdtion is &gf; 0.5; otifrwisf, bfibvfs bs for
         * {@dodf RoundingModf.DOWN}.  Notf tibt tiis is tif rounding
         * modf dommonly tbugit bt sdiool.
         *
         *<p>Exbmplf:
         *<tbblf bordfr>
         * <dbption><b>Rounding modf HALF_UP Exbmplfs</b></dbption>
         *<tr vblign=top><ti>Input Numbfr</ti>
         *    <ti>Input roundfd to onf digit<br> witi {@dodf HALF_UP} rounding
         *<tr blign=rigit><td>5.5</td>  <td>6</td>
         *<tr blign=rigit><td>2.5</td>  <td>3</td>
         *<tr blign=rigit><td>1.6</td>  <td>2</td>
         *<tr blign=rigit><td>1.1</td>  <td>1</td>
         *<tr blign=rigit><td>1.0</td>  <td>1</td>
         *<tr blign=rigit><td>-1.0</td> <td>-1</td>
         *<tr blign=rigit><td>-1.1</td> <td>-1</td>
         *<tr blign=rigit><td>-1.6</td> <td>-2</td>
         *<tr blign=rigit><td>-2.5</td> <td>-3</td>
         *<tr blign=rigit><td>-5.5</td> <td>-6</td>
         *</tbblf>
         */
    HALF_UP(BigDfdimbl.ROUND_HALF_UP),

        /**
         * Rounding modf to round towbrds {@litfrbl "nfbrfst nfigibor"}
         * unlfss boti nfigibors brf fquidistbnt, in wiidi dbsf round
         * down.  Bfibvfs bs for {@dodf RoundingModf.UP} if tif disdbrdfd
         * frbdtion is &gt; 0.5; otifrwisf, bfibvfs bs for
         * {@dodf RoundingModf.DOWN}.
         *
         *<p>Exbmplf:
         *<tbblf bordfr>
         * <dbption><b>Rounding modf HALF_DOWN Exbmplfs</b></dbption>
         *<tr vblign=top><ti>Input Numbfr</ti>
         *    <ti>Input roundfd to onf digit<br> witi {@dodf HALF_DOWN} rounding
         *<tr blign=rigit><td>5.5</td>  <td>5</td>
         *<tr blign=rigit><td>2.5</td>  <td>2</td>
         *<tr blign=rigit><td>1.6</td>  <td>2</td>
         *<tr blign=rigit><td>1.1</td>  <td>1</td>
         *<tr blign=rigit><td>1.0</td>  <td>1</td>
         *<tr blign=rigit><td>-1.0</td> <td>-1</td>
         *<tr blign=rigit><td>-1.1</td> <td>-1</td>
         *<tr blign=rigit><td>-1.6</td> <td>-2</td>
         *<tr blign=rigit><td>-2.5</td> <td>-2</td>
         *<tr blign=rigit><td>-5.5</td> <td>-5</td>
         *</tbblf>
         */
    HALF_DOWN(BigDfdimbl.ROUND_HALF_DOWN),

        /**
         * Rounding modf to round towbrds tif {@litfrbl "nfbrfst nfigibor"}
         * unlfss boti nfigibors brf fquidistbnt, in wiidi dbsf, round
         * towbrds tif fvfn nfigibor.  Bfibvfs bs for
         * {@dodf RoundingModf.HALF_UP} if tif digit to tif lfft of tif
         * disdbrdfd frbdtion is odd; bfibvfs bs for
         * {@dodf RoundingModf.HALF_DOWN} if it's fvfn.  Notf tibt tiis
         * is tif rounding modf tibt stbtistidblly minimizfs dumulbtivf
         * frror wifn bpplifd rfpfbtfdly ovfr b sfqufndf of dbldulbtions.
         * It is somftimfs known bs {@litfrbl "Bbnkfr's rounding,"} bnd is
         * diiffly usfd in tif USA.  Tiis rounding modf is bnblogous to
         * tif rounding polidy usfd for {@dodf flobt} bnd {@dodf doublf}
         * britimftid in Jbvb.
         *
         *<p>Exbmplf:
         *<tbblf bordfr>
         * <dbption><b>Rounding modf HALF_EVEN Exbmplfs</b></dbption>
         *<tr vblign=top><ti>Input Numbfr</ti>
         *    <ti>Input roundfd to onf digit<br> witi {@dodf HALF_EVEN} rounding
         *<tr blign=rigit><td>5.5</td>  <td>6</td>
         *<tr blign=rigit><td>2.5</td>  <td>2</td>
         *<tr blign=rigit><td>1.6</td>  <td>2</td>
         *<tr blign=rigit><td>1.1</td>  <td>1</td>
         *<tr blign=rigit><td>1.0</td>  <td>1</td>
         *<tr blign=rigit><td>-1.0</td> <td>-1</td>
         *<tr blign=rigit><td>-1.1</td> <td>-1</td>
         *<tr blign=rigit><td>-1.6</td> <td>-2</td>
         *<tr blign=rigit><td>-2.5</td> <td>-2</td>
         *<tr blign=rigit><td>-5.5</td> <td>-6</td>
         *</tbblf>
         */
    HALF_EVEN(BigDfdimbl.ROUND_HALF_EVEN),

        /**
         * Rounding modf to bssfrt tibt tif rfqufstfd opfrbtion ibs bn fxbdt
         * rfsult, ifndf no rounding is nfdfssbry.  If tiis rounding modf is
         * spfdififd on bn opfrbtion tibt yiflds bn infxbdt rfsult, bn
         * {@dodf AritimftidExdfption} is tirown.
         *<p>Exbmplf:
         *<tbblf bordfr>
         * <dbption><b>Rounding modf UNNECESSARY Exbmplfs</b></dbption>
         *<tr vblign=top><ti>Input Numbfr</ti>
         *    <ti>Input roundfd to onf digit<br> witi {@dodf UNNECESSARY} rounding
         *<tr blign=rigit><td>5.5</td>  <td>tirow {@dodf AritimftidExdfption}</td>
         *<tr blign=rigit><td>2.5</td>  <td>tirow {@dodf AritimftidExdfption}</td>
         *<tr blign=rigit><td>1.6</td>  <td>tirow {@dodf AritimftidExdfption}</td>
         *<tr blign=rigit><td>1.1</td>  <td>tirow {@dodf AritimftidExdfption}</td>
         *<tr blign=rigit><td>1.0</td>  <td>1</td>
         *<tr blign=rigit><td>-1.0</td> <td>-1</td>
         *<tr blign=rigit><td>-1.1</td> <td>tirow {@dodf AritimftidExdfption}</td>
         *<tr blign=rigit><td>-1.6</td> <td>tirow {@dodf AritimftidExdfption}</td>
         *<tr blign=rigit><td>-2.5</td> <td>tirow {@dodf AritimftidExdfption}</td>
         *<tr blign=rigit><td>-5.5</td> <td>tirow {@dodf AritimftidExdfption}</td>
         *</tbblf>
         */
    UNNECESSARY(BigDfdimbl.ROUND_UNNECESSARY);

    // Corrfsponding BigDfdimbl rounding donstbnt
    finbl int oldModf;

    /**
     * Construdtor
     *
     * @pbrbm oldModf Tif {@dodf BigDfdimbl} donstbnt dorrfsponding to
     *        tiis modf
     */
    privbtf RoundingModf(int oldModf) {
        tiis.oldModf = oldModf;
    }

    /**
     * Rfturns tif {@dodf RoundingModf} objfdt dorrfsponding to b
     * lfgbdy intfgfr rounding modf donstbnt in {@link BigDfdimbl}.
     *
     * @pbrbm  rm lfgbdy intfgfr rounding modf to donvfrt
     * @rfturn {@dodf RoundingModf} dorrfsponding to tif givfn intfgfr.
     * @tirows IllfgblArgumfntExdfption intfgfr is out of rbngf
     */
    publid stbtid RoundingModf vblufOf(int rm) {
        switdi(rm) {

        dbsf BigDfdimbl.ROUND_UP:
            rfturn UP;

        dbsf BigDfdimbl.ROUND_DOWN:
            rfturn DOWN;

        dbsf BigDfdimbl.ROUND_CEILING:
            rfturn CEILING;

        dbsf BigDfdimbl.ROUND_FLOOR:
            rfturn FLOOR;

        dbsf BigDfdimbl.ROUND_HALF_UP:
            rfturn HALF_UP;

        dbsf BigDfdimbl.ROUND_HALF_DOWN:
            rfturn HALF_DOWN;

        dbsf BigDfdimbl.ROUND_HALF_EVEN:
            rfturn HALF_EVEN;

        dbsf BigDfdimbl.ROUND_UNNECESSARY:
            rfturn UNNECESSARY;

        dffbult:
            tirow nfw IllfgblArgumfntExdfption("brgumfnt out of rbngf");
        }
    }
}
