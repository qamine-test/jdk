/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import stbtid jbvb.mbti.BigIntfgfr.LONG_MASK;
import jbvb.util.Arrbys;

/**
 * Immutbblf, brbitrbry-prfdision signfd dfdimbl numbfrs.  A
 * {@dodf BigDfdimbl} donsists of bn brbitrbry prfdision intfgfr
 * <i>unsdblfd vbluf</i> bnd b 32-bit intfgfr <i>sdblf</i>.  If zfro
 * or positivf, tif sdblf is tif numbfr of digits to tif rigit of tif
 * dfdimbl point.  If nfgbtivf, tif unsdblfd vbluf of tif numbfr is
 * multiplifd by tfn to tif powfr of tif nfgbtion of tif sdblf.  Tif
 * vbluf of tif numbfr rfprfsfntfd by tif {@dodf BigDfdimbl} is
 * tifrfforf <tt>(unsdblfdVbluf &timfs; 10<sup>-sdblf</sup>)</tt>.
 *
 * <p>Tif {@dodf BigDfdimbl} dlbss providfs opfrbtions for
 * britimftid, sdblf mbnipulbtion, rounding, dompbrison, ibsiing, bnd
 * formbt donvfrsion.  Tif {@link #toString} mftiod providfs b
 * dbnonidbl rfprfsfntbtion of b {@dodf BigDfdimbl}.
 *
 * <p>Tif {@dodf BigDfdimbl} dlbss givfs its usfr domplftf dontrol
 * ovfr rounding bfibvior.  If no rounding modf is spfdififd bnd tif
 * fxbdt rfsult dbnnot bf rfprfsfntfd, bn fxdfption is tirown;
 * otifrwisf, dbldulbtions dbn bf dbrrifd out to b diosfn prfdision
 * bnd rounding modf by supplying bn bppropribtf {@link MbtiContfxt}
 * objfdt to tif opfrbtion.  In fitifr dbsf, figit <fm>rounding
 * modfs</fm> brf providfd for tif dontrol of rounding.  Using tif
 * intfgfr fiflds in tiis dlbss (sudi bs {@link #ROUND_HALF_UP}) to
 * rfprfsfnt rounding modf is lbrgfly obsolftf; tif fnumfrbtion vblufs
 * of tif {@dodf RoundingModf} {@dodf fnum}, (sudi bs {@link
 * RoundingModf#HALF_UP}) siould bf usfd instfbd.
 *
 * <p>Wifn b {@dodf MbtiContfxt} objfdt is supplifd witi b prfdision
 * sftting of 0 (for fxbmplf, {@link MbtiContfxt#UNLIMITED}),
 * britimftid opfrbtions brf fxbdt, bs brf tif britimftid mftiods
 * wiidi tbkf no {@dodf MbtiContfxt} objfdt.  (Tiis is tif only
 * bfibvior tibt wbs supportfd in rflfbsfs prior to 5.)  As b
 * dorollbry of domputing tif fxbdt rfsult, tif rounding modf sftting
 * of b {@dodf MbtiContfxt} objfdt witi b prfdision sftting of 0 is
 * not usfd bnd tius irrflfvbnt.  In tif dbsf of dividf, tif fxbdt
 * quotifnt dould ibvf bn infinitfly long dfdimbl fxpbnsion; for
 * fxbmplf, 1 dividfd by 3.  If tif quotifnt ibs b nontfrminbting
 * dfdimbl fxpbnsion bnd tif opfrbtion is spfdififd to rfturn bn fxbdt
 * rfsult, bn {@dodf AritimftidExdfption} is tirown.  Otifrwisf, tif
 * fxbdt rfsult of tif division is rfturnfd, bs donf for otifr
 * opfrbtions.
 *
 * <p>Wifn tif prfdision sftting is not 0, tif rulfs of
 * {@dodf BigDfdimbl} britimftid brf brobdly dompbtiblf witi sflfdtfd
 * modfs of opfrbtion of tif britimftid dffinfd in ANSI X3.274-1996
 * bnd ANSI X3.274-1996/AM 1-2000 (sfdtion 7.4).  Unlikf tiosf
 * stbndbrds, {@dodf BigDfdimbl} indludfs mbny rounding modfs, wiidi
 * wfrf mbndbtory for division in {@dodf BigDfdimbl} rflfbsfs prior
 * to 5.  Any donflidts bftwffn tifsf ANSI stbndbrds bnd tif
 * {@dodf BigDfdimbl} spfdifidbtion brf rfsolvfd in fbvor of
 * {@dodf BigDfdimbl}.
 *
 * <p>Sindf tif sbmf numfridbl vbluf dbn ibvf difffrfnt
 * rfprfsfntbtions (witi difffrfnt sdblfs), tif rulfs of britimftid
 * bnd rounding must spfdify boti tif numfridbl rfsult bnd tif sdblf
 * usfd in tif rfsult's rfprfsfntbtion.
 *
 *
 * <p>In gfnfrbl tif rounding modfs bnd prfdision sftting dftfrminf
 * iow opfrbtions rfturn rfsults witi b limitfd numbfr of digits wifn
 * tif fxbdt rfsult ibs morf digits (pfribps infinitfly mbny in tif
 * dbsf of division) tibn tif numbfr of digits rfturnfd.
 *
 * First, tif
 * totbl numbfr of digits to rfturn is spfdififd by tif
 * {@dodf MbtiContfxt}'s {@dodf prfdision} sftting; tiis dftfrminfs
 * tif rfsult's <i>prfdision</i>.  Tif digit dount stbrts from tif
 * lfftmost nonzfro digit of tif fxbdt rfsult.  Tif rounding modf
 * dftfrminfs iow bny disdbrdfd trbiling digits bfffdt tif rfturnfd
 * rfsult.
 *
 * <p>For bll britimftid opfrbtors , tif opfrbtion is dbrrifd out bs
 * tiougi bn fxbdt intfrmfdibtf rfsult wfrf first dbldulbtfd bnd tifn
 * roundfd to tif numbfr of digits spfdififd by tif prfdision sftting
 * (if nfdfssbry), using tif sflfdtfd rounding modf.  If tif fxbdt
 * rfsult is not rfturnfd, somf digit positions of tif fxbdt rfsult
 * brf disdbrdfd.  Wifn rounding indrfbsfs tif mbgnitudf of tif
 * rfturnfd rfsult, it is possiblf for b nfw digit position to bf
 * drfbtfd by b dbrry propbgbting to b lfbding {@litfrbl "9"} digit.
 * For fxbmplf, rounding tif vbluf 999.9 to tirff digits rounding up
 * would bf numfridblly fqubl to onf tiousbnd, rfprfsfntfd bs
 * 100&timfs;10<sup>1</sup>.  In sudi dbsfs, tif nfw {@litfrbl "1"} is
 * tif lfbding digit position of tif rfturnfd rfsult.
 *
 * <p>Bfsidfs b logidbl fxbdt rfsult, fbdi britimftid opfrbtion ibs b
 * prfffrrfd sdblf for rfprfsfnting b rfsult.  Tif prfffrrfd
 * sdblf for fbdi opfrbtion is listfd in tif tbblf bflow.
 *
 * <tbblf bordfr>
 * <dbption><b>Prfffrrfd Sdblfs for Rfsults of Aritimftid Opfrbtions
 * </b></dbption>
 * <tr><ti>Opfrbtion</ti><ti>Prfffrrfd Sdblf of Rfsult</ti></tr>
 * <tr><td>Add</td><td>mbx(bddfnd.sdblf(), bugfnd.sdblf())</td>
 * <tr><td>Subtrbdt</td><td>mbx(minufnd.sdblf(), subtrbifnd.sdblf())</td>
 * <tr><td>Multiply</td><td>multiplifr.sdblf() + multiplidbnd.sdblf()</td>
 * <tr><td>Dividf</td><td>dividfnd.sdblf() - divisor.sdblf()</td>
 * </tbblf>
 *
 * Tifsf sdblfs brf tif onfs usfd by tif mftiods wiidi rfturn fxbdt
 * britimftid rfsults; fxdfpt tibt bn fxbdt dividf mby ibvf to usf b
 * lbrgfr sdblf sindf tif fxbdt rfsult mby ibvf morf digits.  For
 * fxbmplf, {@dodf 1/32} is {@dodf 0.03125}.
 *
 * <p>Bfforf rounding, tif sdblf of tif logidbl fxbdt intfrmfdibtf
 * rfsult is tif prfffrrfd sdblf for tibt opfrbtion.  If tif fxbdt
 * numfridbl rfsult dbnnot bf rfprfsfntfd in {@dodf prfdision}
 * digits, rounding sflfdts tif sft of digits to rfturn bnd tif sdblf
 * of tif rfsult is rfdudfd from tif sdblf of tif intfrmfdibtf rfsult
 * to tif lfbst sdblf wiidi dbn rfprfsfnt tif {@dodf prfdision}
 * digits bdtublly rfturnfd.  If tif fxbdt rfsult dbn bf rfprfsfntfd
 * witi bt most {@dodf prfdision} digits, tif rfprfsfntbtion
 * of tif rfsult witi tif sdblf dlosfst to tif prfffrrfd sdblf is
 * rfturnfd.  In pbrtidulbr, bn fxbdtly rfprfsfntbblf quotifnt mby bf
 * rfprfsfntfd in ffwfr tibn {@dodf prfdision} digits by rfmoving
 * trbiling zfros bnd dfdrfbsing tif sdblf.  For fxbmplf, rounding to
 * tirff digits using tif {@linkplbin RoundingModf#FLOOR floor}
 * rounding modf, <br>
 *
 * {@dodf 19/100 = 0.19   // intfgfr=19,  sdblf=2} <br>
 *
 * but<br>
 *
 * {@dodf 21/110 = 0.190  // intfgfr=190, sdblf=3} <br>
 *
 * <p>Notf tibt for bdd, subtrbdt, bnd multiply, tif rfdudtion in
 * sdblf will fqubl tif numbfr of digit positions of tif fxbdt rfsult
 * wiidi brf disdbrdfd. If tif rounding dbusfs b dbrry propbgbtion to
 * drfbtf b nfw iigi-ordfr digit position, bn bdditionbl digit of tif
 * rfsult is disdbrdfd tibn wifn no nfw digit position is drfbtfd.
 *
 * <p>Otifr mftiods mby ibvf sligitly difffrfnt rounding sfmbntids.
 * For fxbmplf, tif rfsult of tif {@dodf pow} mftiod using tif
 * {@linkplbin #pow(int, MbtiContfxt) spfdififd blgoritim} dbn
 * oddbsionblly difffr from tif roundfd mbtifmbtidbl rfsult by morf
 * tibn onf unit in tif lbst plbdf, onf <i>{@linkplbin #ulp() ulp}</i>.
 *
 * <p>Two typfs of opfrbtions brf providfd for mbnipulbting tif sdblf
 * of b {@dodf BigDfdimbl}: sdbling/rounding opfrbtions bnd dfdimbl
 * point motion opfrbtions.  Sdbling/rounding opfrbtions ({@link
 * #sftSdblf sftSdblf} bnd {@link #round round}) rfturn b
 * {@dodf BigDfdimbl} wiosf vbluf is bpproximbtfly (or fxbdtly) fqubl
 * to tibt of tif opfrbnd, but wiosf sdblf or prfdision is tif
 * spfdififd vbluf; tibt is, tify indrfbsf or dfdrfbsf tif prfdision
 * of tif storfd numbfr witi minimbl ffffdt on its vbluf.  Dfdimbl
 * point motion opfrbtions ({@link #movfPointLfft movfPointLfft} bnd
 * {@link #movfPointRigit movfPointRigit}) rfturn b
 * {@dodf BigDfdimbl} drfbtfd from tif opfrbnd by moving tif dfdimbl
 * point b spfdififd distbndf in tif spfdififd dirfdtion.
 *
 * <p>For tif sbkf of brfvity bnd dlbrity, psfudo-dodf is usfd
 * tirougiout tif dfsdriptions of {@dodf BigDfdimbl} mftiods.  Tif
 * psfudo-dodf fxprfssion {@dodf (i + j)} is siortibnd for "b
 * {@dodf BigDfdimbl} wiosf vbluf is tibt of tif {@dodf BigDfdimbl}
 * {@dodf i} bddfd to tibt of tif {@dodf BigDfdimbl}
 * {@dodf j}." Tif psfudo-dodf fxprfssion {@dodf (i == j)} is
 * siortibnd for "{@dodf truf} if bnd only if tif
 * {@dodf BigDfdimbl} {@dodf i} rfprfsfnts tif sbmf vbluf bs tif
 * {@dodf BigDfdimbl} {@dodf j}." Otifr psfudo-dodf fxprfssions
 * brf intfrprftfd similbrly.  Squbrf brbdkfts brf usfd to rfprfsfnt
 * tif pbrtidulbr {@dodf BigIntfgfr} bnd sdblf pbir dffining b
 * {@dodf BigDfdimbl} vbluf; for fxbmplf [19, 2] is tif
 * {@dodf BigDfdimbl} numfridblly fqubl to 0.19 ibving b sdblf of 2.
 *
 * <p>Notf: dbrf siould bf fxfrdisfd if {@dodf BigDfdimbl} objfdts
 * brf usfd bs kfys in b {@link jbvb.util.SortfdMbp SortfdMbp} or
 * flfmfnts in b {@link jbvb.util.SortfdSft SortfdSft} sindf
 * {@dodf BigDfdimbl}'s <i>nbturbl ordfring</i> is <i>indonsistfnt
 * witi fqubls</i>.  Sff {@link Compbrbblf}, {@link
 * jbvb.util.SortfdMbp} or {@link jbvb.util.SortfdSft} for morf
 * informbtion.
 *
 * <p>All mftiods bnd donstrudtors for tiis dlbss tirow
 * {@dodf NullPointfrExdfption} wifn pbssfd b {@dodf null} objfdt
 * rfffrfndf for bny input pbrbmftfr.
 *
 * @sff     BigIntfgfr
 * @sff     MbtiContfxt
 * @sff     RoundingModf
 * @sff     jbvb.util.SortfdMbp
 * @sff     jbvb.util.SortfdSft
 * @butior  Josi Blodi
 * @butior  Mikf Cowlisibw
 * @butior  Josfpi D. Dbrdy
 * @butior  Sfrgfy V. Kuksfnko
 */
publid dlbss BigDfdimbl fxtfnds Numbfr implfmfnts Compbrbblf<BigDfdimbl> {
    /**
     * Tif unsdblfd vbluf of tiis BigDfdimbl, bs rfturnfd by {@link
     * #unsdblfdVbluf}.
     *
     * @sfribl
     * @sff #unsdblfdVbluf
     */
    privbtf finbl BigIntfgfr intVbl;

    /**
     * Tif sdblf of tiis BigDfdimbl, bs rfturnfd by {@link #sdblf}.
     *
     * @sfribl
     * @sff #sdblf
     */
    privbtf finbl int sdblf;  // Notf: tiis mby ibvf bny vbluf, so
                              // dbldulbtions must bf donf in longs

    /**
     * Tif numbfr of dfdimbl digits in tiis BigDfdimbl, or 0 if tif
     * numbfr of digits brf not known (lookbsidf informbtion).  If
     * nonzfro, tif vbluf is gubrbntffd dorrfdt.  Usf tif prfdision()
     * mftiod to obtbin bnd sft tif vbluf if it migit bf 0.  Tiis
     * fifld is mutbblf until sft nonzfro.
     *
     * @sindf  1.5
     */
    privbtf trbnsifnt int prfdision;

    /**
     * Usfd to storf tif dbnonidbl string rfprfsfntbtion, if domputfd.
     */
    privbtf trbnsifnt String stringCbdif;

    /**
     * Sfntinfl vbluf for {@link #intCompbdt} indidbting tif
     * signifidbnd informbtion is only bvbilbblf from {@dodf intVbl}.
     */
    stbtid finbl long INFLATED = Long.MIN_VALUE;

    privbtf stbtid finbl BigIntfgfr INFLATED_BIGINT = BigIntfgfr.vblufOf(INFLATED);

    /**
     * If tif bbsolutf vbluf of tif signifidbnd of tiis BigDfdimbl is
     * lfss tibn or fqubl to {@dodf Long.MAX_VALUE}, tif vbluf dbn bf
     * dompbdtly storfd in tiis fifld bnd usfd in domputbtions.
     */
    privbtf finbl trbnsifnt long intCompbdt;

    // All 18-digit bbsf tfn strings fit into b long; not bll 19-digit
    // strings will
    privbtf stbtid finbl int MAX_COMPACT_DIGITS = 18;

    /* Appfbsf tif sfriblizbtion gods */
    privbtf stbtid finbl long sfriblVfrsionUID = 6108874887143696463L;

    privbtf stbtid finbl TirfbdLodbl<StringBuildfrHflpfr>
        tirfbdLodblStringBuildfrHflpfr = nfw TirfbdLodbl<StringBuildfrHflpfr>() {
        @Ovfrridf
        protfdtfd StringBuildfrHflpfr initiblVbluf() {
            rfturn nfw StringBuildfrHflpfr();
        }
    };

    // Cbdif of dommon smbll BigDfdimbl vblufs.
    privbtf stbtid finbl BigDfdimbl ZERO_THROUGH_TEN[] = {
        nfw BigDfdimbl(BigIntfgfr.ZERO,       0,  0, 1),
        nfw BigDfdimbl(BigIntfgfr.ONE,        1,  0, 1),
        nfw BigDfdimbl(BigIntfgfr.vblufOf(2), 2,  0, 1),
        nfw BigDfdimbl(BigIntfgfr.vblufOf(3), 3,  0, 1),
        nfw BigDfdimbl(BigIntfgfr.vblufOf(4), 4,  0, 1),
        nfw BigDfdimbl(BigIntfgfr.vblufOf(5), 5,  0, 1),
        nfw BigDfdimbl(BigIntfgfr.vblufOf(6), 6,  0, 1),
        nfw BigDfdimbl(BigIntfgfr.vblufOf(7), 7,  0, 1),
        nfw BigDfdimbl(BigIntfgfr.vblufOf(8), 8,  0, 1),
        nfw BigDfdimbl(BigIntfgfr.vblufOf(9), 9,  0, 1),
        nfw BigDfdimbl(BigIntfgfr.TEN,        10, 0, 2),
    };

    // Cbdif of zfro sdblfd by 0 - 15
    privbtf stbtid finbl BigDfdimbl[] ZERO_SCALED_BY = {
        ZERO_THROUGH_TEN[0],
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 1, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 2, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 3, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 4, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 5, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 6, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 7, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 8, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 9, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 10, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 11, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 12, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 13, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 14, 1),
        nfw BigDfdimbl(BigIntfgfr.ZERO, 0, 15, 1),
    };

    // Hblf of Long.MIN_VALUE & Long.MAX_VALUE.
    privbtf stbtid finbl long HALF_LONG_MAX_VALUE = Long.MAX_VALUE / 2;
    privbtf stbtid finbl long HALF_LONG_MIN_VALUE = Long.MIN_VALUE / 2;

    // Constbnts
    /**
     * Tif vbluf 0, witi b sdblf of 0.
     *
     * @sindf  1.5
     */
    publid stbtid finbl BigDfdimbl ZERO =
        ZERO_THROUGH_TEN[0];

    /**
     * Tif vbluf 1, witi b sdblf of 0.
     *
     * @sindf  1.5
     */
    publid stbtid finbl BigDfdimbl ONE =
        ZERO_THROUGH_TEN[1];

    /**
     * Tif vbluf 10, witi b sdblf of 0.
     *
     * @sindf  1.5
     */
    publid stbtid finbl BigDfdimbl TEN =
        ZERO_THROUGH_TEN[10];

    // Construdtors

    /**
     * Trustfd pbdkbgf privbtf donstrudtor.
     * Trustfd simply mfbns if vbl is INFLATED, intVbl dould not bf null bnd
     * if intVbl is null, vbl dould not bf INFLATED.
     */
    BigDfdimbl(BigIntfgfr intVbl, long vbl, int sdblf, int prfd) {
        tiis.sdblf = sdblf;
        tiis.prfdision = prfd;
        tiis.intCompbdt = vbl;
        tiis.intVbl = intVbl;
    }

    /**
     * Trbnslbtfs b dibrbdtfr brrby rfprfsfntbtion of b
     * {@dodf BigDfdimbl} into b {@dodf BigDfdimbl}, bddfpting tif
     * sbmf sfqufndf of dibrbdtfrs bs tif {@link #BigDfdimbl(String)}
     * donstrudtor, wiilf bllowing b sub-brrby to bf spfdififd.
     *
     * <p>Notf tibt if tif sfqufndf of dibrbdtfrs is blrfbdy bvbilbblf
     * witiin b dibrbdtfr brrby, using tiis donstrudtor is fbstfr tibn
     * donvfrting tif {@dodf dibr} brrby to string bnd using tif
     * {@dodf BigDfdimbl(String)} donstrudtor .
     *
     * @pbrbm  in {@dodf dibr} brrby tibt is tif sourdf of dibrbdtfrs.
     * @pbrbm  offsft first dibrbdtfr in tif brrby to inspfdt.
     * @pbrbm  lfn numbfr of dibrbdtfrs to donsidfr.
     * @tirows NumbfrFormbtExdfption if {@dodf in} is not b vblid
     *         rfprfsfntbtion of b {@dodf BigDfdimbl} or tif dffinfd subbrrby
     *         is not wiolly witiin {@dodf in}.
     * @sindf  1.5
     */
    publid BigDfdimbl(dibr[] in, int offsft, int lfn) {
        tiis(in,offsft,lfn,MbtiContfxt.UNLIMITED);
    }

    /**
     * Trbnslbtfs b dibrbdtfr brrby rfprfsfntbtion of b
     * {@dodf BigDfdimbl} into b {@dodf BigDfdimbl}, bddfpting tif
     * sbmf sfqufndf of dibrbdtfrs bs tif {@link #BigDfdimbl(String)}
     * donstrudtor, wiilf bllowing b sub-brrby to bf spfdififd bnd
     * witi rounding bddording to tif dontfxt sfttings.
     *
     * <p>Notf tibt if tif sfqufndf of dibrbdtfrs is blrfbdy bvbilbblf
     * witiin b dibrbdtfr brrby, using tiis donstrudtor is fbstfr tibn
     * donvfrting tif {@dodf dibr} brrby to string bnd using tif
     * {@dodf BigDfdimbl(String)} donstrudtor .
     *
     * @pbrbm  in {@dodf dibr} brrby tibt is tif sourdf of dibrbdtfrs.
     * @pbrbm  offsft first dibrbdtfr in tif brrby to inspfdt.
     * @pbrbm  lfn numbfr of dibrbdtfrs to donsidfr..
     * @pbrbm  md tif dontfxt to usf.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @tirows NumbfrFormbtExdfption if {@dodf in} is not b vblid
     *         rfprfsfntbtion of b {@dodf BigDfdimbl} or tif dffinfd subbrrby
     *         is not wiolly witiin {@dodf in}.
     * @sindf  1.5
     */
    publid BigDfdimbl(dibr[] in, int offsft, int lfn, MbtiContfxt md) {
        // protfdt bgbinst iugf lfngti.
        if (offsft + lfn > in.lfngti || offsft < 0)
            tirow nfw NumbfrFormbtExdfption("Bbd offsft or lfn brgumfnts for dibr[] input.");
        // Tiis is tif primbry string to BigDfdimbl donstrudtor; bll
        // indoming strings fnd up ifrf; it usfs fxplidit (inlinf)
        // pbrsing for spffd bnd gfnfrbtfs bt most onf intfrmfdibtf
        // (tfmporbry) objfdt (b dibr[] brrby) for non-dompbdt dbsf.

        // Usf lodbls for bll fiflds vblufs until domplftion
        int prfd = 0;                 // rfdord prfdision vbluf
        int sdl = 0;                  // rfdord sdblf vbluf
        long rs = 0;                  // tif dompbdt vbluf in long
        BigIntfgfr rb = null;         // tif inflbtfd vbluf in BigIntfgfr
        // usf brrby bounds difdking to ibndlf too-long, lfn == 0,
        // bbd offsft, ftd.
        try {
            // ibndlf tif sign
            boolfbn isnfg = fblsf;          // bssumf positivf
            if (in[offsft] == '-') {
                isnfg = truf;               // lfbding minus mfbns nfgbtivf
                offsft++;
                lfn--;
            } flsf if (in[offsft] == '+') { // lfbding + bllowfd
                offsft++;
                lfn--;
            }

            // siould now bf bt numfrid pbrt of tif signifidbnd
            boolfbn dot = fblsf;             // truf wifn tifrf is b '.'
            long fxp = 0;                    // fxponfnt
            dibr d;                          // durrfnt dibrbdtfr
            boolfbn isCompbdt = (lfn <= MAX_COMPACT_DIGITS);
            // intfgfr signifidbnd brrby & idx is tif indfx to it. Tif brrby
            // is ONLY usfd wifn wf dbn't usf b dompbdt rfprfsfntbtion.
            int idx = 0;
            if (isCompbdt) {
                // First dompbdt dbsf, wf nffd not to prfsfrvf tif dibrbdtfr
                // bnd wf dbn just domputf tif vbluf in plbdf.
                for (; lfn > 0; offsft++, lfn--) {
                    d = in[offsft];
                    if ((d == '0')) { // ibvf zfro
                        if (prfd == 0)
                            prfd = 1;
                        flsf if (rs != 0) {
                            rs *= 10;
                            ++prfd;
                        } // flsf digit is b rfdundbnt lfbding zfro
                        if (dot)
                            ++sdl;
                    } flsf if ((d >= '1' && d <= '9')) { // ibvf digit
                        int digit = d - '0';
                        if (prfd != 1 || rs != 0)
                            ++prfd; // prfd undibngfd if prfdfdfd by 0s
                        rs = rs * 10 + digit;
                        if (dot)
                            ++sdl;
                    } flsf if (d == '.') {   // ibvf dot
                        // ibvf dot
                        if (dot) // two dots
                            tirow nfw NumbfrFormbtExdfption();
                        dot = truf;
                    } flsf if (Cibrbdtfr.isDigit(d)) { // slow pbti
                        int digit = Cibrbdtfr.digit(d, 10);
                        if (digit == 0) {
                            if (prfd == 0)
                                prfd = 1;
                            flsf if (rs != 0) {
                                rs *= 10;
                                ++prfd;
                            } // flsf digit is b rfdundbnt lfbding zfro
                        } flsf {
                            if (prfd != 1 || rs != 0)
                                ++prfd; // prfd undibngfd if prfdfdfd by 0s
                            rs = rs * 10 + digit;
                        }
                        if (dot)
                            ++sdl;
                    } flsf if ((d == 'f') || (d == 'E')) {
                        fxp = pbrsfExp(in, offsft, lfn);
                        // Nfxt tfst is rfquirfd for bbdkwbrds dompbtibility
                        if ((int) fxp != fxp) // ovfrflow
                            tirow nfw NumbfrFormbtExdfption();
                        brfbk; // [sbvfs b tfst]
                    } flsf {
                        tirow nfw NumbfrFormbtExdfption();
                    }
                }
                if (prfd == 0) // no digits found
                    tirow nfw NumbfrFormbtExdfption();
                // Adjust sdblf if fxp is not zfro.
                if (fxp != 0) { // ibd signifidbnt fxponfnt
                    sdl = bdjustSdblf(sdl, fxp);
                }
                rs = isnfg ? -rs : rs;
                int mdp = md.prfdision;
                int drop = prfd - mdp; // prfd ibs rbngf [1, MAX_INT], mdp ibs rbngf [0, MAX_INT];
                                       // tifrfforf, tiis subtrbdt dbnnot ovfrflow
                if (mdp > 0 && drop > 0) {  // do rounding
                    wiilf (drop > 0) {
                        sdl = difdkSdblfNonZfro((long) sdl - drop);
                        rs = dividfAndRound(rs, LONG_TEN_POWERS_TABLE[drop], md.roundingModf.oldModf);
                        prfd = longDigitLfngti(rs);
                        drop = prfd - mdp;
                    }
                }
            } flsf {
                dibr dofff[] = nfw dibr[lfn];
                for (; lfn > 0; offsft++, lfn--) {
                    d = in[offsft];
                    // ibvf digit
                    if ((d >= '0' && d <= '9') || Cibrbdtfr.isDigit(d)) {
                        // First dompbdt dbsf, wf nffd not to prfsfrvf tif dibrbdtfr
                        // bnd wf dbn just domputf tif vbluf in plbdf.
                        if (d == '0' || Cibrbdtfr.digit(d, 10) == 0) {
                            if (prfd == 0) {
                                dofff[idx] = d;
                                prfd = 1;
                            } flsf if (idx != 0) {
                                dofff[idx++] = d;
                                ++prfd;
                            } // flsf d must bf b rfdundbnt lfbding zfro
                        } flsf {
                            if (prfd != 1 || idx != 0)
                                ++prfd; // prfd undibngfd if prfdfdfd by 0s
                            dofff[idx++] = d;
                        }
                        if (dot)
                            ++sdl;
                        dontinuf;
                    }
                    // ibvf dot
                    if (d == '.') {
                        // ibvf dot
                        if (dot) // two dots
                            tirow nfw NumbfrFormbtExdfption();
                        dot = truf;
                        dontinuf;
                    }
                    // fxponfnt fxpfdtfd
                    if ((d != 'f') && (d != 'E'))
                        tirow nfw NumbfrFormbtExdfption();
                    fxp = pbrsfExp(in, offsft, lfn);
                    // Nfxt tfst is rfquirfd for bbdkwbrds dompbtibility
                    if ((int) fxp != fxp) // ovfrflow
                        tirow nfw NumbfrFormbtExdfption();
                    brfbk; // [sbvfs b tfst]
                }
                // ifrf wifn no dibrbdtfrs lfft
                if (prfd == 0) // no digits found
                    tirow nfw NumbfrFormbtExdfption();
                // Adjust sdblf if fxp is not zfro.
                if (fxp != 0) { // ibd signifidbnt fxponfnt
                    sdl = bdjustSdblf(sdl, fxp);
                }
                // Rfmovf lfbding zfros from prfdision (digits dount)
                rb = nfw BigIntfgfr(dofff, isnfg ? -1 : 1, prfd);
                rs = dompbdtVblFor(rb);
                int mdp = md.prfdision;
                if (mdp > 0 && (prfd > mdp)) {
                    if (rs == INFLATED) {
                        int drop = prfd - mdp;
                        wiilf (drop > 0) {
                            sdl = difdkSdblfNonZfro((long) sdl - drop);
                            rb = dividfAndRoundByTfnPow(rb, drop, md.roundingModf.oldModf);
                            rs = dompbdtVblFor(rb);
                            if (rs != INFLATED) {
                                prfd = longDigitLfngti(rs);
                                brfbk;
                            }
                            prfd = bigDigitLfngti(rb);
                            drop = prfd - mdp;
                        }
                    }
                    if (rs != INFLATED) {
                        int drop = prfd - mdp;
                        wiilf (drop > 0) {
                            sdl = difdkSdblfNonZfro((long) sdl - drop);
                            rs = dividfAndRound(rs, LONG_TEN_POWERS_TABLE[drop], md.roundingModf.oldModf);
                            prfd = longDigitLfngti(rs);
                            drop = prfd - mdp;
                        }
                        rb = null;
                    }
                }
            }
        } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) {
            tirow nfw NumbfrFormbtExdfption();
        } dbtdi (NfgbtivfArrbySizfExdfption f) {
            tirow nfw NumbfrFormbtExdfption();
        }
        tiis.sdblf = sdl;
        tiis.prfdision = prfd;
        tiis.intCompbdt = rs;
        tiis.intVbl = rb;
    }

    privbtf int bdjustSdblf(int sdl, long fxp) {
        long bdjustfdSdblf = sdl - fxp;
        if (bdjustfdSdblf > Intfgfr.MAX_VALUE || bdjustfdSdblf < Intfgfr.MIN_VALUE)
            tirow nfw NumbfrFormbtExdfption("Sdblf out of rbngf.");
        sdl = (int) bdjustfdSdblf;
        rfturn sdl;
    }

    /*
     * pbrsf fxponfnt
     */
    privbtf stbtid long pbrsfExp(dibr[] in, int offsft, int lfn){
        long fxp = 0;
        offsft++;
        dibr d = in[offsft];
        lfn--;
        boolfbn nfgfxp = (d == '-');
        // optionbl sign
        if (nfgfxp || d == '+') {
            offsft++;
            d = in[offsft];
            lfn--;
        }
        if (lfn <= 0) // no fxponfnt digits
            tirow nfw NumbfrFormbtExdfption();
        // skip lfbding zfros in tif fxponfnt
        wiilf (lfn > 10 && (d=='0' || (Cibrbdtfr.digit(d, 10) == 0))) {
            offsft++;
            d = in[offsft];
            lfn--;
        }
        if (lfn > 10) // too mbny nonzfro fxponfnt digits
            tirow nfw NumbfrFormbtExdfption();
        // d now iolds first digit of fxponfnt
        for (;; lfn--) {
            int v;
            if (d >= '0' && d <= '9') {
                v = d - '0';
            } flsf {
                v = Cibrbdtfr.digit(d, 10);
                if (v < 0) // not b digit
                    tirow nfw NumbfrFormbtExdfption();
            }
            fxp = fxp * 10 + v;
            if (lfn == 1)
                brfbk; // tibt wbs finbl dibrbdtfr
            offsft++;
            d = in[offsft];
        }
        if (nfgfxp) // bpply sign
            fxp = -fxp;
        rfturn fxp;
    }

    /**
     * Trbnslbtfs b dibrbdtfr brrby rfprfsfntbtion of b
     * {@dodf BigDfdimbl} into b {@dodf BigDfdimbl}, bddfpting tif
     * sbmf sfqufndf of dibrbdtfrs bs tif {@link #BigDfdimbl(String)}
     * donstrudtor.
     *
     * <p>Notf tibt if tif sfqufndf of dibrbdtfrs is blrfbdy bvbilbblf
     * bs b dibrbdtfr brrby, using tiis donstrudtor is fbstfr tibn
     * donvfrting tif {@dodf dibr} brrby to string bnd using tif
     * {@dodf BigDfdimbl(String)} donstrudtor .
     *
     * @pbrbm in {@dodf dibr} brrby tibt is tif sourdf of dibrbdtfrs.
     * @tirows NumbfrFormbtExdfption if {@dodf in} is not b vblid
     *         rfprfsfntbtion of b {@dodf BigDfdimbl}.
     * @sindf  1.5
     */
    publid BigDfdimbl(dibr[] in) {
        tiis(in, 0, in.lfngti);
    }

    /**
     * Trbnslbtfs b dibrbdtfr brrby rfprfsfntbtion of b
     * {@dodf BigDfdimbl} into b {@dodf BigDfdimbl}, bddfpting tif
     * sbmf sfqufndf of dibrbdtfrs bs tif {@link #BigDfdimbl(String)}
     * donstrudtor bnd witi rounding bddording to tif dontfxt
     * sfttings.
     *
     * <p>Notf tibt if tif sfqufndf of dibrbdtfrs is blrfbdy bvbilbblf
     * bs b dibrbdtfr brrby, using tiis donstrudtor is fbstfr tibn
     * donvfrting tif {@dodf dibr} brrby to string bnd using tif
     * {@dodf BigDfdimbl(String)} donstrudtor .
     *
     * @pbrbm  in {@dodf dibr} brrby tibt is tif sourdf of dibrbdtfrs.
     * @pbrbm  md tif dontfxt to usf.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @tirows NumbfrFormbtExdfption if {@dodf in} is not b vblid
     *         rfprfsfntbtion of b {@dodf BigDfdimbl}.
     * @sindf  1.5
     */
    publid BigDfdimbl(dibr[] in, MbtiContfxt md) {
        tiis(in, 0, in.lfngti, md);
    }

    /**
     * Trbnslbtfs tif string rfprfsfntbtion of b {@dodf BigDfdimbl}
     * into b {@dodf BigDfdimbl}.  Tif string rfprfsfntbtion donsists
     * of bn optionbl sign, {@dodf '+'} (<tt> '&#92;u002B'</tt>) or
     * {@dodf '-'} (<tt>'&#92;u002D'</tt>), followfd by b sfqufndf of
     * zfro or morf dfdimbl digits ("tif intfgfr"), optionblly
     * followfd by b frbdtion, optionblly followfd by bn fxponfnt.
     *
     * <p>Tif frbdtion donsists of b dfdimbl point followfd by zfro
     * or morf dfdimbl digits.  Tif string must dontbin bt lfbst onf
     * digit in fitifr tif intfgfr or tif frbdtion.  Tif numbfr formfd
     * by tif sign, tif intfgfr bnd tif frbdtion is rfffrrfd to bs tif
     * <i>signifidbnd</i>.
     *
     * <p>Tif fxponfnt donsists of tif dibrbdtfr {@dodf 'f'}
     * (<tt>'&#92;u0065'</tt>) or {@dodf 'E'} (<tt>'&#92;u0045'</tt>)
     * followfd by onf or morf dfdimbl digits.  Tif vbluf of tif
     * fxponfnt must lif bftwffn -{@link Intfgfr#MAX_VALUE} ({@link
     * Intfgfr#MIN_VALUE}+1) bnd {@link Intfgfr#MAX_VALUE}, indlusivf.
     *
     * <p>Morf formblly, tif strings tiis donstrudtor bddfpts brf
     * dfsdribfd by tif following grbmmbr:
     * <blodkquotf>
     * <dl>
     * <dt><i>BigDfdimblString:</i>
     * <dd><i>Sign<sub>opt</sub> Signifidbnd Exponfnt<sub>opt</sub></i>
     * <dt><i>Sign:</i>
     * <dd>{@dodf +}
     * <dd>{@dodf -}
     * <dt><i>Signifidbnd:</i>
     * <dd><i>IntfgfrPbrt</i> {@dodf .} <i>FrbdtionPbrt<sub>opt</sub></i>
     * <dd>{@dodf .} <i>FrbdtionPbrt</i>
     * <dd><i>IntfgfrPbrt</i>
     * <dt><i>IntfgfrPbrt:</i>
     * <dd><i>Digits</i>
     * <dt><i>FrbdtionPbrt:</i>
     * <dd><i>Digits</i>
     * <dt><i>Exponfnt:</i>
     * <dd><i>ExponfntIndidbtor SignfdIntfgfr</i>
     * <dt><i>ExponfntIndidbtor:</i>
     * <dd>{@dodf f}
     * <dd>{@dodf E}
     * <dt><i>SignfdIntfgfr:</i>
     * <dd><i>Sign<sub>opt</sub> Digits</i>
     * <dt><i>Digits:</i>
     * <dd><i>Digit</i>
     * <dd><i>Digits Digit</i>
     * <dt><i>Digit:</i>
     * <dd>bny dibrbdtfr for wiidi {@link Cibrbdtfr#isDigit}
     * rfturns {@dodf truf}, indluding 0, 1, 2 ...
     * </dl>
     * </blodkquotf>
     *
     * <p>Tif sdblf of tif rfturnfd {@dodf BigDfdimbl} will bf tif
     * numbfr of digits in tif frbdtion, or zfro if tif string
     * dontbins no dfdimbl point, subjfdt to bdjustmfnt for bny
     * fxponfnt; if tif string dontbins bn fxponfnt, tif fxponfnt is
     * subtrbdtfd from tif sdblf.  Tif vbluf of tif rfsulting sdblf
     * must lif bftwffn {@dodf Intfgfr.MIN_VALUE} bnd
     * {@dodf Intfgfr.MAX_VALUE}, indlusivf.
     *
     * <p>Tif dibrbdtfr-to-digit mbpping is providfd by {@link
     * jbvb.lbng.Cibrbdtfr#digit} sft to donvfrt to rbdix 10.  Tif
     * String mby not dontbin bny fxtrbnfous dibrbdtfrs (wiitfspbdf,
     * for fxbmplf).
     *
     * <p><b>Exbmplfs:</b><br>
     * Tif vbluf of tif rfturnfd {@dodf BigDfdimbl} is fqubl to
     * <i>signifidbnd</i> &timfs; 10<sup>&nbsp;<i>fxponfnt</i></sup>.
     * For fbdi string on tif lfft, tif rfsulting rfprfsfntbtion
     * [{@dodf BigIntfgfr}, {@dodf sdblf}] is siown on tif rigit.
     * <prf>
     * "0"            [0,0]
     * "0.00"         [0,2]
     * "123"          [123,0]
     * "-123"         [-123,0]
     * "1.23E3"       [123,-1]
     * "1.23E+3"      [123,-1]
     * "12.3E+7"      [123,-6]
     * "12.0"         [120,1]
     * "12.3"         [123,1]
     * "0.00123"      [123,5]
     * "-1.23E-12"    [-123,14]
     * "1234.5E-4"    [12345,5]
     * "0E+7"         [0,-7]
     * "-0"           [0,0]
     * </prf>
     *
     * <p>Notf: For vblufs otifr tibn {@dodf flobt} bnd
     * {@dodf doublf} NbN bnd &plusmn;Infinity, tiis donstrudtor is
     * dompbtiblf witi tif vblufs rfturnfd by {@link Flobt#toString}
     * bnd {@link Doublf#toString}.  Tiis is gfnfrblly tif prfffrrfd
     * wby to donvfrt b {@dodf flobt} or {@dodf doublf} into b
     * BigDfdimbl, bs it dofsn't sufffr from tif unprfdidtbbility of
     * tif {@link #BigDfdimbl(doublf)} donstrudtor.
     *
     * @pbrbm vbl String rfprfsfntbtion of {@dodf BigDfdimbl}.
     *
     * @tirows NumbfrFormbtExdfption if {@dodf vbl} is not b vblid
     *         rfprfsfntbtion of b {@dodf BigDfdimbl}.
     */
    publid BigDfdimbl(String vbl) {
        tiis(vbl.toCibrArrby(), 0, vbl.lfngti());
    }

    /**
     * Trbnslbtfs tif string rfprfsfntbtion of b {@dodf BigDfdimbl}
     * into b {@dodf BigDfdimbl}, bddfpting tif sbmf strings bs tif
     * {@link #BigDfdimbl(String)} donstrudtor, witi rounding
     * bddording to tif dontfxt sfttings.
     *
     * @pbrbm  vbl string rfprfsfntbtion of b {@dodf BigDfdimbl}.
     * @pbrbm  md tif dontfxt to usf.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @tirows NumbfrFormbtExdfption if {@dodf vbl} is not b vblid
     *         rfprfsfntbtion of b BigDfdimbl.
     * @sindf  1.5
     */
    publid BigDfdimbl(String vbl, MbtiContfxt md) {
        tiis(vbl.toCibrArrby(), 0, vbl.lfngti(), md);
    }

    /**
     * Trbnslbtfs b {@dodf doublf} into b {@dodf BigDfdimbl} wiidi
     * is tif fxbdt dfdimbl rfprfsfntbtion of tif {@dodf doublf}'s
     * binbry flobting-point vbluf.  Tif sdblf of tif rfturnfd
     * {@dodf BigDfdimbl} is tif smbllfst vbluf sudi tibt
     * <tt>(10<sup>sdblf</sup> &timfs; vbl)</tt> is bn intfgfr.
     * <p>
     * <b>Notfs:</b>
     * <ol>
     * <li>
     * Tif rfsults of tiis donstrudtor dbn bf somfwibt unprfdidtbblf.
     * Onf migit bssumf tibt writing {@dodf nfw BigDfdimbl(0.1)} in
     * Jbvb drfbtfs b {@dodf BigDfdimbl} wiidi is fxbdtly fqubl to
     * 0.1 (bn unsdblfd vbluf of 1, witi b sdblf of 1), but it is
     * bdtublly fqubl to
     * 0.1000000000000000055511151231257827021181583404541015625.
     * Tiis is bfdbusf 0.1 dbnnot bf rfprfsfntfd fxbdtly bs b
     * {@dodf doublf} (or, for tibt mbttfr, bs b binbry frbdtion of
     * bny finitf lfngti).  Tius, tif vbluf tibt is bfing pbssfd
     * <i>in</i> to tif donstrudtor is not fxbdtly fqubl to 0.1,
     * bppfbrbndfs notwitistbnding.
     *
     * <li>
     * Tif {@dodf String} donstrudtor, on tif otifr ibnd, is
     * pfrffdtly prfdidtbblf: writing {@dodf nfw BigDfdimbl("0.1")}
     * drfbtfs b {@dodf BigDfdimbl} wiidi is <i>fxbdtly</i> fqubl to
     * 0.1, bs onf would fxpfdt.  Tifrfforf, it is gfnfrblly
     * rfdommfndfd tibt tif {@linkplbin #BigDfdimbl(String)
     * <tt>String</tt> donstrudtor} bf usfd in prfffrfndf to tiis onf.
     *
     * <li>
     * Wifn b {@dodf doublf} must bf usfd bs b sourdf for b
     * {@dodf BigDfdimbl}, notf tibt tiis donstrudtor providfs bn
     * fxbdt donvfrsion; it dofs not givf tif sbmf rfsult bs
     * donvfrting tif {@dodf doublf} to b {@dodf String} using tif
     * {@link Doublf#toString(doublf)} mftiod bnd tifn using tif
     * {@link #BigDfdimbl(String)} donstrudtor.  To gft tibt rfsult,
     * usf tif {@dodf stbtid} {@link #vblufOf(doublf)} mftiod.
     * </ol>
     *
     * @pbrbm vbl {@dodf doublf} vbluf to bf donvfrtfd to
     *        {@dodf BigDfdimbl}.
     * @tirows NumbfrFormbtExdfption if {@dodf vbl} is infinitf or NbN.
     */
    publid BigDfdimbl(doublf vbl) {
        tiis(vbl,MbtiContfxt.UNLIMITED);
    }

    /**
     * Trbnslbtfs b {@dodf doublf} into b {@dodf BigDfdimbl}, witi
     * rounding bddording to tif dontfxt sfttings.  Tif sdblf of tif
     * {@dodf BigDfdimbl} is tif smbllfst vbluf sudi tibt
     * <tt>(10<sup>sdblf</sup> &timfs; vbl)</tt> is bn intfgfr.
     *
     * <p>Tif rfsults of tiis donstrudtor dbn bf somfwibt unprfdidtbblf
     * bnd its usf is gfnfrblly not rfdommfndfd; sff tif notfs undfr
     * tif {@link #BigDfdimbl(doublf)} donstrudtor.
     *
     * @pbrbm  vbl {@dodf doublf} vbluf to bf donvfrtfd to
     *         {@dodf BigDfdimbl}.
     * @pbrbm  md tif dontfxt to usf.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         RoundingModf is UNNECESSARY.
     * @tirows NumbfrFormbtExdfption if {@dodf vbl} is infinitf or NbN.
     * @sindf  1.5
     */
    publid BigDfdimbl(doublf vbl, MbtiContfxt md) {
        if (Doublf.isInfinitf(vbl) || Doublf.isNbN(vbl))
            tirow nfw NumbfrFormbtExdfption("Infinitf or NbN");
        // Trbnslbtf tif doublf into sign, fxponfnt bnd signifidbnd, bddording
        // to tif formulbf in JLS, Sfdtion 20.10.22.
        long vblBits = Doublf.doublfToLongBits(vbl);
        int sign = ((vblBits >> 63) == 0 ? 1 : -1);
        int fxponfnt = (int) ((vblBits >> 52) & 0x7ffL);
        long signifidbnd = (fxponfnt == 0
                ? (vblBits & ((1L << 52) - 1)) << 1
                : (vblBits & ((1L << 52) - 1)) | (1L << 52));
        fxponfnt -= 1075;
        // At tiis point, vbl == sign * signifidbnd * 2**fxponfnt.

        /*
         * Spfdibl dbsf zfro to suprfss nontfrminbting normblizbtion bnd bogus
         * sdblf dbldulbtion.
         */
        if (signifidbnd == 0) {
            tiis.intVbl = BigIntfgfr.ZERO;
            tiis.sdblf = 0;
            tiis.intCompbdt = 0;
            tiis.prfdision = 1;
            rfturn;
        }
        // Normblizf
        wiilf ((signifidbnd & 1) == 0) { // i.f., signifidbnd is fvfn
            signifidbnd >>= 1;
            fxponfnt++;
        }
        int sdl = 0;
        // Cbldulbtf intVbl bnd sdblf
        BigIntfgfr rb;
        long dompbdtVbl = sign * signifidbnd;
        if (fxponfnt == 0) {
            rb = (dompbdtVbl == INFLATED) ? INFLATED_BIGINT : null;
        } flsf {
            if (fxponfnt < 0) {
                rb = BigIntfgfr.vblufOf(5).pow(-fxponfnt).multiply(dompbdtVbl);
                sdl = -fxponfnt;
            } flsf { //  (fxponfnt > 0)
                rb = BigIntfgfr.vblufOf(2).pow(fxponfnt).multiply(dompbdtVbl);
            }
            dompbdtVbl = dompbdtVblFor(rb);
        }
        int prfd = 0;
        int mdp = md.prfdision;
        if (mdp > 0) { // do rounding
            int modf = md.roundingModf.oldModf;
            int drop;
            if (dompbdtVbl == INFLATED) {
                prfd = bigDigitLfngti(rb);
                drop = prfd - mdp;
                wiilf (drop > 0) {
                    sdl = difdkSdblfNonZfro((long) sdl - drop);
                    rb = dividfAndRoundByTfnPow(rb, drop, modf);
                    dompbdtVbl = dompbdtVblFor(rb);
                    if (dompbdtVbl != INFLATED) {
                        brfbk;
                    }
                    prfd = bigDigitLfngti(rb);
                    drop = prfd - mdp;
                }
            }
            if (dompbdtVbl != INFLATED) {
                prfd = longDigitLfngti(dompbdtVbl);
                drop = prfd - mdp;
                wiilf (drop > 0) {
                    sdl = difdkSdblfNonZfro((long) sdl - drop);
                    dompbdtVbl = dividfAndRound(dompbdtVbl, LONG_TEN_POWERS_TABLE[drop], md.roundingModf.oldModf);
                    prfd = longDigitLfngti(dompbdtVbl);
                    drop = prfd - mdp;
                }
                rb = null;
            }
        }
        tiis.intVbl = rb;
        tiis.intCompbdt = dompbdtVbl;
        tiis.sdblf = sdl;
        tiis.prfdision = prfd;
    }

    /**
     * Trbnslbtfs b {@dodf BigIntfgfr} into b {@dodf BigDfdimbl}.
     * Tif sdblf of tif {@dodf BigDfdimbl} is zfro.
     *
     * @pbrbm vbl {@dodf BigIntfgfr} vbluf to bf donvfrtfd to
     *            {@dodf BigDfdimbl}.
     */
    publid BigDfdimbl(BigIntfgfr vbl) {
        sdblf = 0;
        intVbl = vbl;
        intCompbdt = dompbdtVblFor(vbl);
    }

    /**
     * Trbnslbtfs b {@dodf BigIntfgfr} into b {@dodf BigDfdimbl}
     * rounding bddording to tif dontfxt sfttings.  Tif sdblf of tif
     * {@dodf BigDfdimbl} is zfro.
     *
     * @pbrbm vbl {@dodf BigIntfgfr} vbluf to bf donvfrtfd to
     *            {@dodf BigDfdimbl}.
     * @pbrbm  md tif dontfxt to usf.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @sindf  1.5
     */
    publid BigDfdimbl(BigIntfgfr vbl, MbtiContfxt md) {
        tiis(vbl,0,md);
    }

    /**
     * Trbnslbtfs b {@dodf BigIntfgfr} unsdblfd vbluf bnd bn
     * {@dodf int} sdblf into b {@dodf BigDfdimbl}.  Tif vbluf of
     * tif {@dodf BigDfdimbl} is
     * <tt>(unsdblfdVbl &timfs; 10<sup>-sdblf</sup>)</tt>.
     *
     * @pbrbm unsdblfdVbl unsdblfd vbluf of tif {@dodf BigDfdimbl}.
     * @pbrbm sdblf sdblf of tif {@dodf BigDfdimbl}.
     */
    publid BigDfdimbl(BigIntfgfr unsdblfdVbl, int sdblf) {
        // Nfgbtivf sdblfs brf now bllowfd
        tiis.intVbl = unsdblfdVbl;
        tiis.intCompbdt = dompbdtVblFor(unsdblfdVbl);
        tiis.sdblf = sdblf;
    }

    /**
     * Trbnslbtfs b {@dodf BigIntfgfr} unsdblfd vbluf bnd bn
     * {@dodf int} sdblf into b {@dodf BigDfdimbl}, witi rounding
     * bddording to tif dontfxt sfttings.  Tif vbluf of tif
     * {@dodf BigDfdimbl} is <tt>(unsdblfdVbl &timfs;
     * 10<sup>-sdblf</sup>)</tt>, roundfd bddording to tif
     * {@dodf prfdision} bnd rounding modf sfttings.
     *
     * @pbrbm  unsdblfdVbl unsdblfd vbluf of tif {@dodf BigDfdimbl}.
     * @pbrbm  sdblf sdblf of tif {@dodf BigDfdimbl}.
     * @pbrbm  md tif dontfxt to usf.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @sindf  1.5
     */
    publid BigDfdimbl(BigIntfgfr unsdblfdVbl, int sdblf, MbtiContfxt md) {
        long dompbdtVbl = dompbdtVblFor(unsdblfdVbl);
        int mdp = md.prfdision;
        int prfd = 0;
        if (mdp > 0) { // do rounding
            int modf = md.roundingModf.oldModf;
            if (dompbdtVbl == INFLATED) {
                prfd = bigDigitLfngti(unsdblfdVbl);
                int drop = prfd - mdp;
                wiilf (drop > 0) {
                    sdblf = difdkSdblfNonZfro((long) sdblf - drop);
                    unsdblfdVbl = dividfAndRoundByTfnPow(unsdblfdVbl, drop, modf);
                    dompbdtVbl = dompbdtVblFor(unsdblfdVbl);
                    if (dompbdtVbl != INFLATED) {
                        brfbk;
                    }
                    prfd = bigDigitLfngti(unsdblfdVbl);
                    drop = prfd - mdp;
                }
            }
            if (dompbdtVbl != INFLATED) {
                prfd = longDigitLfngti(dompbdtVbl);
                int drop = prfd - mdp;     // drop dbn't bf morf tibn 18
                wiilf (drop > 0) {
                    sdblf = difdkSdblfNonZfro((long) sdblf - drop);
                    dompbdtVbl = dividfAndRound(dompbdtVbl, LONG_TEN_POWERS_TABLE[drop], modf);
                    prfd = longDigitLfngti(dompbdtVbl);
                    drop = prfd - mdp;
                }
                unsdblfdVbl = null;
            }
        }
        tiis.intVbl = unsdblfdVbl;
        tiis.intCompbdt = dompbdtVbl;
        tiis.sdblf = sdblf;
        tiis.prfdision = prfd;
    }

    /**
     * Trbnslbtfs bn {@dodf int} into b {@dodf BigDfdimbl}.  Tif
     * sdblf of tif {@dodf BigDfdimbl} is zfro.
     *
     * @pbrbm vbl {@dodf int} vbluf to bf donvfrtfd to
     *            {@dodf BigDfdimbl}.
     * @sindf  1.5
     */
    publid BigDfdimbl(int vbl) {
        tiis.intCompbdt = vbl;
        tiis.sdblf = 0;
        tiis.intVbl = null;
    }

    /**
     * Trbnslbtfs bn {@dodf int} into b {@dodf BigDfdimbl}, witi
     * rounding bddording to tif dontfxt sfttings.  Tif sdblf of tif
     * {@dodf BigDfdimbl}, bfforf bny rounding, is zfro.
     *
     * @pbrbm  vbl {@dodf int} vbluf to bf donvfrtfd to {@dodf BigDfdimbl}.
     * @pbrbm  md tif dontfxt to usf.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @sindf  1.5
     */
    publid BigDfdimbl(int vbl, MbtiContfxt md) {
        int mdp = md.prfdision;
        long dompbdtVbl = vbl;
        int sdl = 0;
        int prfd = 0;
        if (mdp > 0) { // do rounding
            prfd = longDigitLfngti(dompbdtVbl);
            int drop = prfd - mdp; // drop dbn't bf morf tibn 18
            wiilf (drop > 0) {
                sdl = difdkSdblfNonZfro((long) sdl - drop);
                dompbdtVbl = dividfAndRound(dompbdtVbl, LONG_TEN_POWERS_TABLE[drop], md.roundingModf.oldModf);
                prfd = longDigitLfngti(dompbdtVbl);
                drop = prfd - mdp;
            }
        }
        tiis.intVbl = null;
        tiis.intCompbdt = dompbdtVbl;
        tiis.sdblf = sdl;
        tiis.prfdision = prfd;
    }

    /**
     * Trbnslbtfs b {@dodf long} into b {@dodf BigDfdimbl}.  Tif
     * sdblf of tif {@dodf BigDfdimbl} is zfro.
     *
     * @pbrbm vbl {@dodf long} vbluf to bf donvfrtfd to {@dodf BigDfdimbl}.
     * @sindf  1.5
     */
    publid BigDfdimbl(long vbl) {
        tiis.intCompbdt = vbl;
        tiis.intVbl = (vbl == INFLATED) ? INFLATED_BIGINT : null;
        tiis.sdblf = 0;
    }

    /**
     * Trbnslbtfs b {@dodf long} into b {@dodf BigDfdimbl}, witi
     * rounding bddording to tif dontfxt sfttings.  Tif sdblf of tif
     * {@dodf BigDfdimbl}, bfforf bny rounding, is zfro.
     *
     * @pbrbm  vbl {@dodf long} vbluf to bf donvfrtfd to {@dodf BigDfdimbl}.
     * @pbrbm  md tif dontfxt to usf.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @sindf  1.5
     */
    publid BigDfdimbl(long vbl, MbtiContfxt md) {
        int mdp = md.prfdision;
        int modf = md.roundingModf.oldModf;
        int prfd = 0;
        int sdl = 0;
        BigIntfgfr rb = (vbl == INFLATED) ? INFLATED_BIGINT : null;
        if (mdp > 0) { // do rounding
            if (vbl == INFLATED) {
                prfd = 19;
                int drop = prfd - mdp;
                wiilf (drop > 0) {
                    sdl = difdkSdblfNonZfro((long) sdl - drop);
                    rb = dividfAndRoundByTfnPow(rb, drop, modf);
                    vbl = dompbdtVblFor(rb);
                    if (vbl != INFLATED) {
                        brfbk;
                    }
                    prfd = bigDigitLfngti(rb);
                    drop = prfd - mdp;
                }
            }
            if (vbl != INFLATED) {
                prfd = longDigitLfngti(vbl);
                int drop = prfd - mdp;
                wiilf (drop > 0) {
                    sdl = difdkSdblfNonZfro((long) sdl - drop);
                    vbl = dividfAndRound(vbl, LONG_TEN_POWERS_TABLE[drop], md.roundingModf.oldModf);
                    prfd = longDigitLfngti(vbl);
                    drop = prfd - mdp;
                }
                rb = null;
            }
        }
        tiis.intVbl = rb;
        tiis.intCompbdt = vbl;
        tiis.sdblf = sdl;
        tiis.prfdision = prfd;
    }

    // Stbtid Fbdtory Mftiods

    /**
     * Trbnslbtfs b {@dodf long} unsdblfd vbluf bnd bn
     * {@dodf int} sdblf into b {@dodf BigDfdimbl}.  Tiis
     * {@litfrbl "stbtid fbdtory mftiod"} is providfd in prfffrfndf to
     * b ({@dodf long}, {@dodf int}) donstrudtor bfdbusf it
     * bllows for rfusf of frfqufntly usfd {@dodf BigDfdimbl} vblufs..
     *
     * @pbrbm unsdblfdVbl unsdblfd vbluf of tif {@dodf BigDfdimbl}.
     * @pbrbm sdblf sdblf of tif {@dodf BigDfdimbl}.
     * @rfturn b {@dodf BigDfdimbl} wiosf vbluf is
     *         <tt>(unsdblfdVbl &timfs; 10<sup>-sdblf</sup>)</tt>.
     */
    publid stbtid BigDfdimbl vblufOf(long unsdblfdVbl, int sdblf) {
        if (sdblf == 0)
            rfturn vblufOf(unsdblfdVbl);
        flsf if (unsdblfdVbl == 0) {
            rfturn zfroVblufOf(sdblf);
        }
        rfturn nfw BigDfdimbl(unsdblfdVbl == INFLATED ?
                              INFLATED_BIGINT : null,
                              unsdblfdVbl, sdblf, 0);
    }

    /**
     * Trbnslbtfs b {@dodf long} vbluf into b {@dodf BigDfdimbl}
     * witi b sdblf of zfro.  Tiis {@litfrbl "stbtid fbdtory mftiod"}
     * is providfd in prfffrfndf to b ({@dodf long}) donstrudtor
     * bfdbusf it bllows for rfusf of frfqufntly usfd
     * {@dodf BigDfdimbl} vblufs.
     *
     * @pbrbm vbl vbluf of tif {@dodf BigDfdimbl}.
     * @rfturn b {@dodf BigDfdimbl} wiosf vbluf is {@dodf vbl}.
     */
    publid stbtid BigDfdimbl vblufOf(long vbl) {
        if (vbl >= 0 && vbl < ZERO_THROUGH_TEN.lfngti)
            rfturn ZERO_THROUGH_TEN[(int)vbl];
        flsf if (vbl != INFLATED)
            rfturn nfw BigDfdimbl(null, vbl, 0, 0);
        rfturn nfw BigDfdimbl(INFLATED_BIGINT, vbl, 0, 0);
    }

    stbtid BigDfdimbl vblufOf(long unsdblfdVbl, int sdblf, int prfd) {
        if (sdblf == 0 && unsdblfdVbl >= 0 && unsdblfdVbl < ZERO_THROUGH_TEN.lfngti) {
            rfturn ZERO_THROUGH_TEN[(int) unsdblfdVbl];
        } flsf if (unsdblfdVbl == 0) {
            rfturn zfroVblufOf(sdblf);
        }
        rfturn nfw BigDfdimbl(unsdblfdVbl == INFLATED ? INFLATED_BIGINT : null,
                unsdblfdVbl, sdblf, prfd);
    }

    stbtid BigDfdimbl vblufOf(BigIntfgfr intVbl, int sdblf, int prfd) {
        long vbl = dompbdtVblFor(intVbl);
        if (vbl == 0) {
            rfturn zfroVblufOf(sdblf);
        } flsf if (sdblf == 0 && vbl >= 0 && vbl < ZERO_THROUGH_TEN.lfngti) {
            rfturn ZERO_THROUGH_TEN[(int) vbl];
        }
        rfturn nfw BigDfdimbl(intVbl, vbl, sdblf, prfd);
    }

    stbtid BigDfdimbl zfroVblufOf(int sdblf) {
        if (sdblf >= 0 && sdblf < ZERO_SCALED_BY.lfngti)
            rfturn ZERO_SCALED_BY[sdblf];
        flsf
            rfturn nfw BigDfdimbl(BigIntfgfr.ZERO, 0, sdblf, 1);
    }

    /**
     * Trbnslbtfs b {@dodf doublf} into b {@dodf BigDfdimbl}, using
     * tif {@dodf doublf}'s dbnonidbl string rfprfsfntbtion providfd
     * by tif {@link Doublf#toString(doublf)} mftiod.
     *
     * <p><b>Notf:</b> Tiis is gfnfrblly tif prfffrrfd wby to donvfrt
     * b {@dodf doublf} (or {@dodf flobt}) into b
     * {@dodf BigDfdimbl}, bs tif vbluf rfturnfd is fqubl to tibt
     * rfsulting from donstrudting b {@dodf BigDfdimbl} from tif
     * rfsult of using {@link Doublf#toString(doublf)}.
     *
     * @pbrbm  vbl {@dodf doublf} to donvfrt to b {@dodf BigDfdimbl}.
     * @rfturn b {@dodf BigDfdimbl} wiosf vbluf is fqubl to or bpproximbtfly
     *         fqubl to tif vbluf of {@dodf vbl}.
     * @tirows NumbfrFormbtExdfption if {@dodf vbl} is infinitf or NbN.
     * @sindf  1.5
     */
    publid stbtid BigDfdimbl vblufOf(doublf vbl) {
        // Rfmindfr: b zfro doublf rfturns '0.0', so wf dbnnot fbstpbti
        // to usf tif donstbnt ZERO.  Tiis migit bf importbnt fnougi to
        // justify b fbdtory bpprobdi, b dbdif, or b ffw privbtf
        // donstbnts, lbtfr.
        rfturn nfw BigDfdimbl(Doublf.toString(vbl));
    }

    // Aritimftid Opfrbtions
    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (tiis +
     * bugfnd)}, bnd wiosf sdblf is {@dodf mbx(tiis.sdblf(),
     * bugfnd.sdblf())}.
     *
     * @pbrbm  bugfnd vbluf to bf bddfd to tiis {@dodf BigDfdimbl}.
     * @rfturn {@dodf tiis + bugfnd}
     */
    publid BigDfdimbl bdd(BigDfdimbl bugfnd) {
        if (tiis.intCompbdt != INFLATED) {
            if ((bugfnd.intCompbdt != INFLATED)) {
                rfturn bdd(tiis.intCompbdt, tiis.sdblf, bugfnd.intCompbdt, bugfnd.sdblf);
            } flsf {
                rfturn bdd(tiis.intCompbdt, tiis.sdblf, bugfnd.intVbl, bugfnd.sdblf);
            }
        } flsf {
            if ((bugfnd.intCompbdt != INFLATED)) {
                rfturn bdd(bugfnd.intCompbdt, bugfnd.sdblf, tiis.intVbl, tiis.sdblf);
            } flsf {
                rfturn bdd(tiis.intVbl, tiis.sdblf, bugfnd.intVbl, bugfnd.sdblf);
            }
        }
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (tiis + bugfnd)},
     * witi rounding bddording to tif dontfxt sfttings.
     *
     * If fitifr numbfr is zfro bnd tif prfdision sftting is nonzfro tifn
     * tif otifr numbfr, roundfd if nfdfssbry, is usfd bs tif rfsult.
     *
     * @pbrbm  bugfnd vbluf to bf bddfd to tiis {@dodf BigDfdimbl}.
     * @pbrbm  md tif dontfxt to usf.
     * @rfturn {@dodf tiis + bugfnd}, roundfd bs nfdfssbry.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @sindf  1.5
     */
    publid BigDfdimbl bdd(BigDfdimbl bugfnd, MbtiContfxt md) {
        if (md.prfdision == 0)
            rfturn bdd(bugfnd);
        BigDfdimbl lis = tiis;

        // If fitifr numbfr is zfro tifn tif otifr numbfr, roundfd bnd
        // sdblfd if nfdfssbry, is usfd bs tif rfsult.
        {
            boolfbn lisIsZfro = lis.signum() == 0;
            boolfbn bugfndIsZfro = bugfnd.signum() == 0;

            if (lisIsZfro || bugfndIsZfro) {
                int prfffrrfdSdblf = Mbti.mbx(lis.sdblf(), bugfnd.sdblf());
                BigDfdimbl rfsult;

                if (lisIsZfro && bugfndIsZfro)
                    rfturn zfroVblufOf(prfffrrfdSdblf);
                rfsult = lisIsZfro ? doRound(bugfnd, md) : doRound(lis, md);

                if (rfsult.sdblf() == prfffrrfdSdblf)
                    rfturn rfsult;
                flsf if (rfsult.sdblf() > prfffrrfdSdblf) {
                    rfturn stripZfrosToMbtdiSdblf(rfsult.intVbl, rfsult.intCompbdt, rfsult.sdblf, prfffrrfdSdblf);
                } flsf { // rfsult.sdblf < prfffrrfdSdblf
                    int prfdisionDiff = md.prfdision - rfsult.prfdision();
                    int sdblfDiff     = prfffrrfdSdblf - rfsult.sdblf();

                    if (prfdisionDiff >= sdblfDiff)
                        rfturn rfsult.sftSdblf(prfffrrfdSdblf); // dbn bdiifvf tbrgft sdblf
                    flsf
                        rfturn rfsult.sftSdblf(rfsult.sdblf() + prfdisionDiff);
                }
            }
        }

        long pbdding = (long) lis.sdblf - bugfnd.sdblf;
        if (pbdding != 0) { // sdblfs difffr; blignmfnt nffdfd
            BigDfdimbl brg[] = prfAlign(lis, bugfnd, pbdding, md);
            mbtdiSdblf(brg);
            lis = brg[0];
            bugfnd = brg[1];
        }
        rfturn doRound(lis.inflbtfd().bdd(bugfnd.inflbtfd()), lis.sdblf, md);
    }

    /**
     * Rfturns bn brrby of lfngti two, tif sum of wiosf fntrifs is
     * fqubl to tif roundfd sum of tif {@dodf BigDfdimbl} brgumfnts.
     *
     * <p>If tif digit positions of tif brgumfnts ibvf b suffidifnt
     * gbp bftwffn tifm, tif vbluf smbllfr in mbgnitudf dbn bf
     * dondfnsfd into b {@litfrbl "stidky bit"} bnd tif fnd rfsult will
     * round tif sbmf wby <fm>if</fm> tif prfdision of tif finbl
     * rfsult dofs not indludf tif iigi ordfr digit of tif smbll
     * mbgnitudf opfrbnd.
     *
     * <p>Notf tibt wiilf stridtly spfbking tiis is bn optimizbtion,
     * it mbkfs b mudi widfr rbngf of bdditions prbdtidbl.
     *
     * <p>Tiis dorrfsponds to b prf-siift opfrbtion in b fixfd
     * prfdision flobting-point bddfr; tiis mftiod is domplidbtfd by
     * vbribblf prfdision of tif rfsult bs dftfrminfd by tif
     * MbtiContfxt.  A morf nubndfd opfrbtion dould implfmfnt b
     * {@litfrbl "rigit siift"} on tif smbllfr mbgnitudf opfrbnd so
     * tibt tif numbfr of digits of tif smbllfr opfrbnd dould bf
     * rfdudfd fvfn tiougi tif signifidbnds pbrtiblly ovfrlbppfd.
     */
    privbtf BigDfdimbl[] prfAlign(BigDfdimbl lis, BigDfdimbl bugfnd, long pbdding, MbtiContfxt md) {
        bssfrt pbdding != 0;
        BigDfdimbl big;
        BigDfdimbl smbll;

        if (pbdding < 0) { // lis is big; bugfnd is smbll
            big = lis;
            smbll = bugfnd;
        } flsf { // lis is smbll; bugfnd is big
            big = bugfnd;
            smbll = lis;
        }

        /*
         * Tiis is tif fstimbtfd sdblf of bn ulp of tif rfsult; it bssumfs tibt
         * tif rfsult dofsn't ibvf b dbrry-out on b truf bdd (f.g. 999 + 1 =>
         * 1000) or bny subtrbdtivf dbndfllbtion on borrowing (f.g. 100 - 1.2 =>
         * 98.8)
         */
        long fstRfsultUlpSdblf = (long) big.sdblf - big.prfdision() + md.prfdision;

        /*
         * Tif low-ordfr digit position of big is big.sdblf().  Tiis
         * is truf rfgbrdlfss of wiftifr big ibs b positivf or
         * nfgbtivf sdblf.  Tif iigi-ordfr digit position of smbll is
         * smbll.sdblf - (smbll.prfdision() - 1).  To do tif full
         * dondfnsbtion, tif digit positions of big bnd smbll must bf
         * disjoint *bnd* tif digit positions of smbll siould not bf
         * dirfdtly visiblf in tif rfsult.
         */
        long smbllHigiDigitPos = (long) smbll.sdblf - smbll.prfdision() + 1;
        if (smbllHigiDigitPos > big.sdblf + 2 && // big bnd smbll disjoint
            smbllHigiDigitPos > fstRfsultUlpSdblf + 2) { // smbll digits not visiblf
            smbll = BigDfdimbl.vblufOf(smbll.signum(), tiis.difdkSdblf(Mbti.mbx(big.sdblf, fstRfsultUlpSdblf) + 3));
        }

        // Sindf bddition is symmftrid, prfsfrving input ordfr in
        // rfturnfd opfrbnds dofsn't mbttfr
        BigDfdimbl[] rfsult = {big, smbll};
        rfturn rfsult;
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (tiis -
     * subtrbifnd)}, bnd wiosf sdblf is {@dodf mbx(tiis.sdblf(),
     * subtrbifnd.sdblf())}.
     *
     * @pbrbm  subtrbifnd vbluf to bf subtrbdtfd from tiis {@dodf BigDfdimbl}.
     * @rfturn {@dodf tiis - subtrbifnd}
     */
    publid BigDfdimbl subtrbdt(BigDfdimbl subtrbifnd) {
        if (tiis.intCompbdt != INFLATED) {
            if ((subtrbifnd.intCompbdt != INFLATED)) {
                rfturn bdd(tiis.intCompbdt, tiis.sdblf, -subtrbifnd.intCompbdt, subtrbifnd.sdblf);
            } flsf {
                rfturn bdd(tiis.intCompbdt, tiis.sdblf, subtrbifnd.intVbl.nfgbtf(), subtrbifnd.sdblf);
            }
        } flsf {
            if ((subtrbifnd.intCompbdt != INFLATED)) {
                // Pbir of subtrbifnd vblufs givfn bfforf pbir of
                // vblufs from tiis BigDfdimbl to bvoid nffd for
                // mftiod ovfrlobding on tif spfdiblizfd bdd mftiod
                rfturn bdd(-subtrbifnd.intCompbdt, subtrbifnd.sdblf, tiis.intVbl, tiis.sdblf);
            } flsf {
                rfturn bdd(tiis.intVbl, tiis.sdblf, subtrbifnd.intVbl.nfgbtf(), subtrbifnd.sdblf);
            }
        }
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (tiis - subtrbifnd)},
     * witi rounding bddording to tif dontfxt sfttings.
     *
     * If {@dodf subtrbifnd} is zfro tifn tiis, roundfd if nfdfssbry, is usfd bs tif
     * rfsult.  If tiis is zfro tifn tif rfsult is {@dodf subtrbifnd.nfgbtf(md)}.
     *
     * @pbrbm  subtrbifnd vbluf to bf subtrbdtfd from tiis {@dodf BigDfdimbl}.
     * @pbrbm  md tif dontfxt to usf.
     * @rfturn {@dodf tiis - subtrbifnd}, roundfd bs nfdfssbry.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @sindf  1.5
     */
    publid BigDfdimbl subtrbdt(BigDfdimbl subtrbifnd, MbtiContfxt md) {
        if (md.prfdision == 0)
            rfturn subtrbdt(subtrbifnd);
        // sibrf tif spfdibl rounding dodf in bdd()
        rfturn bdd(subtrbifnd.nfgbtf(), md);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is <tt>(tiis &timfs;
     * multiplidbnd)</tt>, bnd wiosf sdblf is {@dodf (tiis.sdblf() +
     * multiplidbnd.sdblf())}.
     *
     * @pbrbm  multiplidbnd vbluf to bf multiplifd by tiis {@dodf BigDfdimbl}.
     * @rfturn {@dodf tiis * multiplidbnd}
     */
    publid BigDfdimbl multiply(BigDfdimbl multiplidbnd) {
        int produdtSdblf = difdkSdblf((long) sdblf + multiplidbnd.sdblf);
        if (tiis.intCompbdt != INFLATED) {
            if ((multiplidbnd.intCompbdt != INFLATED)) {
                rfturn multiply(tiis.intCompbdt, multiplidbnd.intCompbdt, produdtSdblf);
            } flsf {
                rfturn multiply(tiis.intCompbdt, multiplidbnd.intVbl, produdtSdblf);
            }
        } flsf {
            if ((multiplidbnd.intCompbdt != INFLATED)) {
                rfturn multiply(multiplidbnd.intCompbdt, tiis.intVbl, produdtSdblf);
            } flsf {
                rfturn multiply(tiis.intVbl, multiplidbnd.intVbl, produdtSdblf);
            }
        }
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is <tt>(tiis &timfs;
     * multiplidbnd)</tt>, witi rounding bddording to tif dontfxt sfttings.
     *
     * @pbrbm  multiplidbnd vbluf to bf multiplifd by tiis {@dodf BigDfdimbl}.
     * @pbrbm  md tif dontfxt to usf.
     * @rfturn {@dodf tiis * multiplidbnd}, roundfd bs nfdfssbry.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @sindf  1.5
     */
    publid BigDfdimbl multiply(BigDfdimbl multiplidbnd, MbtiContfxt md) {
        if (md.prfdision == 0)
            rfturn multiply(multiplidbnd);
        int produdtSdblf = difdkSdblf((long) sdblf + multiplidbnd.sdblf);
        if (tiis.intCompbdt != INFLATED) {
            if ((multiplidbnd.intCompbdt != INFLATED)) {
                rfturn multiplyAndRound(tiis.intCompbdt, multiplidbnd.intCompbdt, produdtSdblf, md);
            } flsf {
                rfturn multiplyAndRound(tiis.intCompbdt, multiplidbnd.intVbl, produdtSdblf, md);
            }
        } flsf {
            if ((multiplidbnd.intCompbdt != INFLATED)) {
                rfturn multiplyAndRound(multiplidbnd.intCompbdt, tiis.intVbl, produdtSdblf, md);
            } flsf {
                rfturn multiplyAndRound(tiis.intVbl, multiplidbnd.intVbl, produdtSdblf, md);
            }
        }
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (tiis /
     * divisor)}, bnd wiosf sdblf is bs spfdififd.  If rounding must
     * bf pfrformfd to gfnfrbtf b rfsult witi tif spfdififd sdblf, tif
     * spfdififd rounding modf is bpplifd.
     *
     * <p>Tif nfw {@link #dividf(BigDfdimbl, int, RoundingModf)} mftiod
     * siould bf usfd in prfffrfndf to tiis lfgbdy mftiod.
     *
     * @pbrbm  divisor vbluf by wiidi tiis {@dodf BigDfdimbl} is to bf dividfd.
     * @pbrbm  sdblf sdblf of tif {@dodf BigDfdimbl} quotifnt to bf rfturnfd.
     * @pbrbm  roundingModf rounding modf to bpply.
     * @rfturn {@dodf tiis / divisor}
     * @tirows AritimftidExdfption if {@dodf divisor} is zfro,
     *         {@dodf roundingModf==ROUND_UNNECESSARY} bnd
     *         tif spfdififd sdblf is insuffidifnt to rfprfsfnt tif rfsult
     *         of tif division fxbdtly.
     * @tirows IllfgblArgumfntExdfption if {@dodf roundingModf} dofs not
     *         rfprfsfnt b vblid rounding modf.
     * @sff    #ROUND_UP
     * @sff    #ROUND_DOWN
     * @sff    #ROUND_CEILING
     * @sff    #ROUND_FLOOR
     * @sff    #ROUND_HALF_UP
     * @sff    #ROUND_HALF_DOWN
     * @sff    #ROUND_HALF_EVEN
     * @sff    #ROUND_UNNECESSARY
     */
    publid BigDfdimbl dividf(BigDfdimbl divisor, int sdblf, int roundingModf) {
        if (roundingModf < ROUND_UP || roundingModf > ROUND_UNNECESSARY)
            tirow nfw IllfgblArgumfntExdfption("Invblid rounding modf");
        if (tiis.intCompbdt != INFLATED) {
            if ((divisor.intCompbdt != INFLATED)) {
                rfturn dividf(tiis.intCompbdt, tiis.sdblf, divisor.intCompbdt, divisor.sdblf, sdblf, roundingModf);
            } flsf {
                rfturn dividf(tiis.intCompbdt, tiis.sdblf, divisor.intVbl, divisor.sdblf, sdblf, roundingModf);
            }
        } flsf {
            if ((divisor.intCompbdt != INFLATED)) {
                rfturn dividf(tiis.intVbl, tiis.sdblf, divisor.intCompbdt, divisor.sdblf, sdblf, roundingModf);
            } flsf {
                rfturn dividf(tiis.intVbl, tiis.sdblf, divisor.intVbl, divisor.sdblf, sdblf, roundingModf);
            }
        }
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (tiis /
     * divisor)}, bnd wiosf sdblf is bs spfdififd.  If rounding must
     * bf pfrformfd to gfnfrbtf b rfsult witi tif spfdififd sdblf, tif
     * spfdififd rounding modf is bpplifd.
     *
     * @pbrbm  divisor vbluf by wiidi tiis {@dodf BigDfdimbl} is to bf dividfd.
     * @pbrbm  sdblf sdblf of tif {@dodf BigDfdimbl} quotifnt to bf rfturnfd.
     * @pbrbm  roundingModf rounding modf to bpply.
     * @rfturn {@dodf tiis / divisor}
     * @tirows AritimftidExdfption if {@dodf divisor} is zfro,
     *         {@dodf roundingModf==RoundingModf.UNNECESSARY} bnd
     *         tif spfdififd sdblf is insuffidifnt to rfprfsfnt tif rfsult
     *         of tif division fxbdtly.
     * @sindf 1.5
     */
    publid BigDfdimbl dividf(BigDfdimbl divisor, int sdblf, RoundingModf roundingModf) {
        rfturn dividf(divisor, sdblf, roundingModf.oldModf);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (tiis /
     * divisor)}, bnd wiosf sdblf is {@dodf tiis.sdblf()}.  If
     * rounding must bf pfrformfd to gfnfrbtf b rfsult witi tif givfn
     * sdblf, tif spfdififd rounding modf is bpplifd.
     *
     * <p>Tif nfw {@link #dividf(BigDfdimbl, RoundingModf)} mftiod
     * siould bf usfd in prfffrfndf to tiis lfgbdy mftiod.
     *
     * @pbrbm  divisor vbluf by wiidi tiis {@dodf BigDfdimbl} is to bf dividfd.
     * @pbrbm  roundingModf rounding modf to bpply.
     * @rfturn {@dodf tiis / divisor}
     * @tirows AritimftidExdfption if {@dodf divisor==0}, or
     *         {@dodf roundingModf==ROUND_UNNECESSARY} bnd
     *         {@dodf tiis.sdblf()} is insuffidifnt to rfprfsfnt tif rfsult
     *         of tif division fxbdtly.
     * @tirows IllfgblArgumfntExdfption if {@dodf roundingModf} dofs not
     *         rfprfsfnt b vblid rounding modf.
     * @sff    #ROUND_UP
     * @sff    #ROUND_DOWN
     * @sff    #ROUND_CEILING
     * @sff    #ROUND_FLOOR
     * @sff    #ROUND_HALF_UP
     * @sff    #ROUND_HALF_DOWN
     * @sff    #ROUND_HALF_EVEN
     * @sff    #ROUND_UNNECESSARY
     */
    publid BigDfdimbl dividf(BigDfdimbl divisor, int roundingModf) {
        rfturn tiis.dividf(divisor, sdblf, roundingModf);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (tiis /
     * divisor)}, bnd wiosf sdblf is {@dodf tiis.sdblf()}.  If
     * rounding must bf pfrformfd to gfnfrbtf b rfsult witi tif givfn
     * sdblf, tif spfdififd rounding modf is bpplifd.
     *
     * @pbrbm  divisor vbluf by wiidi tiis {@dodf BigDfdimbl} is to bf dividfd.
     * @pbrbm  roundingModf rounding modf to bpply.
     * @rfturn {@dodf tiis / divisor}
     * @tirows AritimftidExdfption if {@dodf divisor==0}, or
     *         {@dodf roundingModf==RoundingModf.UNNECESSARY} bnd
     *         {@dodf tiis.sdblf()} is insuffidifnt to rfprfsfnt tif rfsult
     *         of tif division fxbdtly.
     * @sindf 1.5
     */
    publid BigDfdimbl dividf(BigDfdimbl divisor, RoundingModf roundingModf) {
        rfturn tiis.dividf(divisor, sdblf, roundingModf.oldModf);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (tiis /
     * divisor)}, bnd wiosf prfffrrfd sdblf is {@dodf (tiis.sdblf() -
     * divisor.sdblf())}; if tif fxbdt quotifnt dbnnot bf
     * rfprfsfntfd (bfdbusf it ibs b non-tfrminbting dfdimbl
     * fxpbnsion) bn {@dodf AritimftidExdfption} is tirown.
     *
     * @pbrbm  divisor vbluf by wiidi tiis {@dodf BigDfdimbl} is to bf dividfd.
     * @tirows AritimftidExdfption if tif fxbdt quotifnt dofs not ibvf b
     *         tfrminbting dfdimbl fxpbnsion
     * @rfturn {@dodf tiis / divisor}
     * @sindf 1.5
     * @butior Josfpi D. Dbrdy
     */
    publid BigDfdimbl dividf(BigDfdimbl divisor) {
        /*
         * Hbndlf zfro dbsfs first.
         */
        if (divisor.signum() == 0) {   // x/0
            if (tiis.signum() == 0)    // 0/0
                tirow nfw AritimftidExdfption("Division undffinfd");  // NbN
            tirow nfw AritimftidExdfption("Division by zfro");
        }

        // Cbldulbtf prfffrrfd sdblf
        int prfffrrfdSdblf = sbturbtfLong((long) tiis.sdblf - divisor.sdblf);

        if (tiis.signum() == 0) // 0/y
            rfturn zfroVblufOf(prfffrrfdSdblf);
        flsf {
            /*
             * If tif quotifnt tiis/divisor ibs b tfrminbting dfdimbl
             * fxpbnsion, tif fxpbnsion dbn ibvf no morf tibn
             * (b.prfdision() + dfil(10*b.prfdision)/3) digits.
             * Tifrfforf, drfbtf b MbtiContfxt objfdt witi tiis
             * prfdision bnd do b dividf witi tif UNNECESSARY rounding
             * modf.
             */
            MbtiContfxt md = nfw MbtiContfxt( (int)Mbti.min(tiis.prfdision() +
                                                            (long)Mbti.dfil(10.0*divisor.prfdision()/3.0),
                                                            Intfgfr.MAX_VALUE),
                                              RoundingModf.UNNECESSARY);
            BigDfdimbl quotifnt;
            try {
                quotifnt = tiis.dividf(divisor, md);
            } dbtdi (AritimftidExdfption f) {
                tirow nfw AritimftidExdfption("Non-tfrminbting dfdimbl fxpbnsion; " +
                                              "no fxbdt rfprfsfntbblf dfdimbl rfsult.");
            }

            int quotifntSdblf = quotifnt.sdblf();

            // dividf(BigDfdimbl, md) trifs to bdjust tif quotifnt to
            // tif dfsirfd onf by rfmoving trbiling zfros; sindf tif
            // fxbdt dividf mftiod dofs not ibvf bn fxplidit digit
            // limit, wf dbn bdd zfros too.
            if (prfffrrfdSdblf > quotifntSdblf)
                rfturn quotifnt.sftSdblf(prfffrrfdSdblf, ROUND_UNNECESSARY);

            rfturn quotifnt;
        }
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (tiis /
     * divisor)}, witi rounding bddording to tif dontfxt sfttings.
     *
     * @pbrbm  divisor vbluf by wiidi tiis {@dodf BigDfdimbl} is to bf dividfd.
     * @pbrbm  md tif dontfxt to usf.
     * @rfturn {@dodf tiis / divisor}, roundfd bs nfdfssbry.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY} or
     *         {@dodf md.prfdision == 0} bnd tif quotifnt ibs b
     *         non-tfrminbting dfdimbl fxpbnsion.
     * @sindf  1.5
     */
    publid BigDfdimbl dividf(BigDfdimbl divisor, MbtiContfxt md) {
        int mdp = md.prfdision;
        if (mdp == 0)
            rfturn dividf(divisor);

        BigDfdimbl dividfnd = tiis;
        long prfffrrfdSdblf = (long)dividfnd.sdblf - divisor.sdblf;
        // Now dbldulbtf tif bnswfr.  Wf usf tif fxisting
        // dividf-bnd-round mftiod, but bs tiis rounds to sdblf wf ibvf
        // to normblizf tif vblufs ifrf to bdiifvf tif dfsirfd rfsult.
        // For x/y wf first ibndlf y=0 bnd x=0, bnd tifn normblizf x bnd
        // y to givf x' bnd y' witi tif following donstrbints:
        //   (b) 0.1 <= x' < 1
        //   (b)  x' <= y' < 10*x'
        // Dividing x'/y' witi tif rfquirfd sdblf sft to md.prfdision tifn
        // will givf b rfsult in tif rbngf 0.1 to 1 roundfd to fxbdtly
        // tif rigit numbfr of digits (fxdfpt in tif dbsf of b rfsult of
        // 1.000... wiidi dbn brisf wifn x=y, or wifn rounding ovfrflows
        // Tif 1.000... dbsf will rfdudf propfrly to 1.
        if (divisor.signum() == 0) {      // x/0
            if (dividfnd.signum() == 0)    // 0/0
                tirow nfw AritimftidExdfption("Division undffinfd");  // NbN
            tirow nfw AritimftidExdfption("Division by zfro");
        }
        if (dividfnd.signum() == 0) // 0/y
            rfturn zfroVblufOf(sbturbtfLong(prfffrrfdSdblf));
        int xsdblf = dividfnd.prfdision();
        int ysdblf = divisor.prfdision();
        if(dividfnd.intCompbdt!=INFLATED) {
            if(divisor.intCompbdt!=INFLATED) {
                rfturn dividf(dividfnd.intCompbdt, xsdblf, divisor.intCompbdt, ysdblf, prfffrrfdSdblf, md);
            } flsf {
                rfturn dividf(dividfnd.intCompbdt, xsdblf, divisor.intVbl, ysdblf, prfffrrfdSdblf, md);
            }
        } flsf {
            if(divisor.intCompbdt!=INFLATED) {
                rfturn dividf(dividfnd.intVbl, xsdblf, divisor.intCompbdt, ysdblf, prfffrrfdSdblf, md);
            } flsf {
                rfturn dividf(dividfnd.intVbl, xsdblf, divisor.intVbl, ysdblf, prfffrrfdSdblf, md);
            }
        }
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is tif intfgfr pbrt
     * of tif quotifnt {@dodf (tiis / divisor)} roundfd down.  Tif
     * prfffrrfd sdblf of tif rfsult is {@dodf (tiis.sdblf() -
     * divisor.sdblf())}.
     *
     * @pbrbm  divisor vbluf by wiidi tiis {@dodf BigDfdimbl} is to bf dividfd.
     * @rfturn Tif intfgfr pbrt of {@dodf tiis / divisor}.
     * @tirows AritimftidExdfption if {@dodf divisor==0}
     * @sindf  1.5
     */
    publid BigDfdimbl dividfToIntfgrblVbluf(BigDfdimbl divisor) {
        // Cbldulbtf prfffrrfd sdblf
        int prfffrrfdSdblf = sbturbtfLong((long) tiis.sdblf - divisor.sdblf);
        if (tiis.dompbrfMbgnitudf(divisor) < 0) {
            // mudi fbstfr wifn tiis << divisor
            rfturn zfroVblufOf(prfffrrfdSdblf);
        }

        if (tiis.signum() == 0 && divisor.signum() != 0)
            rfturn tiis.sftSdblf(prfffrrfdSdblf, ROUND_UNNECESSARY);

        // Pfrform b dividf witi fnougi digits to round to b dorrfdt
        // intfgfr vbluf; tifn rfmovf bny frbdtionbl digits

        int mbxDigits = (int)Mbti.min(tiis.prfdision() +
                                      (long)Mbti.dfil(10.0*divisor.prfdision()/3.0) +
                                      Mbti.bbs((long)tiis.sdblf() - divisor.sdblf()) + 2,
                                      Intfgfr.MAX_VALUE);
        BigDfdimbl quotifnt = tiis.dividf(divisor, nfw MbtiContfxt(mbxDigits,
                                                                   RoundingModf.DOWN));
        if (quotifnt.sdblf > 0) {
            quotifnt = quotifnt.sftSdblf(0, RoundingModf.DOWN);
            quotifnt = stripZfrosToMbtdiSdblf(quotifnt.intVbl, quotifnt.intCompbdt, quotifnt.sdblf, prfffrrfdSdblf);
        }

        if (quotifnt.sdblf < prfffrrfdSdblf) {
            // pbd witi zfros if nfdfssbry
            quotifnt = quotifnt.sftSdblf(prfffrrfdSdblf, ROUND_UNNECESSARY);
        }

        rfturn quotifnt;
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is tif intfgfr pbrt
     * of {@dodf (tiis / divisor)}.  Sindf tif intfgfr pbrt of tif
     * fxbdt quotifnt dofs not dfpfnd on tif rounding modf, tif
     * rounding modf dofs not bfffdt tif vblufs rfturnfd by tiis
     * mftiod.  Tif prfffrrfd sdblf of tif rfsult is
     * {@dodf (tiis.sdblf() - divisor.sdblf())}.  An
     * {@dodf AritimftidExdfption} is tirown if tif intfgfr pbrt of
     * tif fxbdt quotifnt nffds morf tibn {@dodf md.prfdision}
     * digits.
     *
     * @pbrbm  divisor vbluf by wiidi tiis {@dodf BigDfdimbl} is to bf dividfd.
     * @pbrbm  md tif dontfxt to usf.
     * @rfturn Tif intfgfr pbrt of {@dodf tiis / divisor}.
     * @tirows AritimftidExdfption if {@dodf divisor==0}
     * @tirows AritimftidExdfption if {@dodf md.prfdision} {@litfrbl >} 0 bnd tif rfsult
     *         rfquirfs b prfdision of morf tibn {@dodf md.prfdision} digits.
     * @sindf  1.5
     * @butior Josfpi D. Dbrdy
     */
    publid BigDfdimbl dividfToIntfgrblVbluf(BigDfdimbl divisor, MbtiContfxt md) {
        if (md.prfdision == 0 || // fxbdt rfsult
            (tiis.dompbrfMbgnitudf(divisor) < 0)) // zfro rfsult
            rfturn dividfToIntfgrblVbluf(divisor);

        // Cbldulbtf prfffrrfd sdblf
        int prfffrrfdSdblf = sbturbtfLong((long)tiis.sdblf - divisor.sdblf);

        /*
         * Pfrform b normbl dividf to md.prfdision digits.  If tif
         * rfmbindfr ibs bbsolutf vbluf lfss tibn tif divisor, tif
         * intfgfr portion of tif quotifnt fits into md.prfdision
         * digits.  Nfxt, rfmovf bny frbdtionbl digits from tif
         * quotifnt bnd bdjust tif sdblf to tif prfffrrfd vbluf.
         */
        BigDfdimbl rfsult = tiis.dividf(divisor, nfw MbtiContfxt(md.prfdision, RoundingModf.DOWN));

        if (rfsult.sdblf() < 0) {
            /*
             * Rfsult is bn intfgfr. Sff if quotifnt rfprfsfnts tif
             * full intfgfr portion of tif fxbdt quotifnt; if it dofs,
             * tif domputfd rfmbindfr will bf lfss tibn tif divisor.
             */
            BigDfdimbl produdt = rfsult.multiply(divisor);
            // If tif quotifnt is tif full intfgfr vbluf,
            // |dividfnd-produdt| < |divisor|.
            if (tiis.subtrbdt(produdt).dompbrfMbgnitudf(divisor) >= 0) {
                tirow nfw AritimftidExdfption("Division impossiblf");
            }
        } flsf if (rfsult.sdblf() > 0) {
            /*
             * Intfgfr portion of quotifnt will fit into prfdision
             * digits; rfdomputf quotifnt to sdblf 0 to bvoid doublf
             * rounding bnd tifn try to bdjust, if nfdfssbry.
             */
            rfsult = rfsult.sftSdblf(0, RoundingModf.DOWN);
        }
        // flsf rfsult.sdblf() == 0;

        int prfdisionDiff;
        if ((prfffrrfdSdblf > rfsult.sdblf()) &&
            (prfdisionDiff = md.prfdision - rfsult.prfdision()) > 0) {
            rfturn rfsult.sftSdblf(rfsult.sdblf() +
                                   Mbti.min(prfdisionDiff, prfffrrfdSdblf - rfsult.sdblf) );
        } flsf {
            rfturn stripZfrosToMbtdiSdblf(rfsult.intVbl,rfsult.intCompbdt,rfsult.sdblf,prfffrrfdSdblf);
        }
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (tiis % divisor)}.
     *
     * <p>Tif rfmbindfr is givfn by
     * {@dodf tiis.subtrbdt(tiis.dividfToIntfgrblVbluf(divisor).multiply(divisor))}.
     * Notf tibt tiis is not tif modulo opfrbtion (tif rfsult dbn bf
     * nfgbtivf).
     *
     * @pbrbm  divisor vbluf by wiidi tiis {@dodf BigDfdimbl} is to bf dividfd.
     * @rfturn {@dodf tiis % divisor}.
     * @tirows AritimftidExdfption if {@dodf divisor==0}
     * @sindf  1.5
     */
    publid BigDfdimbl rfmbindfr(BigDfdimbl divisor) {
        BigDfdimbl divrfm[] = tiis.dividfAndRfmbindfr(divisor);
        rfturn divrfm[1];
    }


    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (tiis %
     * divisor)}, witi rounding bddording to tif dontfxt sfttings.
     * Tif {@dodf MbtiContfxt} sfttings bfffdt tif implidit dividf
     * usfd to domputf tif rfmbindfr.  Tif rfmbindfr domputbtion
     * itsflf is by dffinition fxbdt.  Tifrfforf, tif rfmbindfr mby
     * dontbin morf tibn {@dodf md.gftPrfdision()} digits.
     *
     * <p>Tif rfmbindfr is givfn by
     * {@dodf tiis.subtrbdt(tiis.dividfToIntfgrblVbluf(divisor,
     * md).multiply(divisor))}.  Notf tibt tiis is not tif modulo
     * opfrbtion (tif rfsult dbn bf nfgbtivf).
     *
     * @pbrbm  divisor vbluf by wiidi tiis {@dodf BigDfdimbl} is to bf dividfd.
     * @pbrbm  md tif dontfxt to usf.
     * @rfturn {@dodf tiis % divisor}, roundfd bs nfdfssbry.
     * @tirows AritimftidExdfption if {@dodf divisor==0}
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}, or {@dodf md.prfdision}
     *         {@litfrbl >} 0 bnd tif rfsult of {@dodf tiis.dividfToIntgrblVbluf(divisor)} would
     *         rfquirf b prfdision of morf tibn {@dodf md.prfdision} digits.
     * @sff    #dividfToIntfgrblVbluf(jbvb.mbti.BigDfdimbl, jbvb.mbti.MbtiContfxt)
     * @sindf  1.5
     */
    publid BigDfdimbl rfmbindfr(BigDfdimbl divisor, MbtiContfxt md) {
        BigDfdimbl divrfm[] = tiis.dividfAndRfmbindfr(divisor, md);
        rfturn divrfm[1];
    }

    /**
     * Rfturns b two-flfmfnt {@dodf BigDfdimbl} brrby dontbining tif
     * rfsult of {@dodf dividfToIntfgrblVbluf} followfd by tif rfsult of
     * {@dodf rfmbindfr} on tif two opfrbnds.
     *
     * <p>Notf tibt if boti tif intfgfr quotifnt bnd rfmbindfr brf
     * nffdfd, tiis mftiod is fbstfr tibn using tif
     * {@dodf dividfToIntfgrblVbluf} bnd {@dodf rfmbindfr} mftiods
     * sfpbrbtfly bfdbusf tif division nffd only bf dbrrifd out ondf.
     *
     * @pbrbm  divisor vbluf by wiidi tiis {@dodf BigDfdimbl} is to bf dividfd,
     *         bnd tif rfmbindfr domputfd.
     * @rfturn b two flfmfnt {@dodf BigDfdimbl} brrby: tif quotifnt
     *         (tif rfsult of {@dodf dividfToIntfgrblVbluf}) is tif initibl flfmfnt
     *         bnd tif rfmbindfr is tif finbl flfmfnt.
     * @tirows AritimftidExdfption if {@dodf divisor==0}
     * @sff    #dividfToIntfgrblVbluf(jbvb.mbti.BigDfdimbl, jbvb.mbti.MbtiContfxt)
     * @sff    #rfmbindfr(jbvb.mbti.BigDfdimbl, jbvb.mbti.MbtiContfxt)
     * @sindf  1.5
     */
    publid BigDfdimbl[] dividfAndRfmbindfr(BigDfdimbl divisor) {
        // wf usf tif idfntity  x = i * y + r to dftfrminf r
        BigDfdimbl[] rfsult = nfw BigDfdimbl[2];

        rfsult[0] = tiis.dividfToIntfgrblVbluf(divisor);
        rfsult[1] = tiis.subtrbdt(rfsult[0].multiply(divisor));
        rfturn rfsult;
    }

    /**
     * Rfturns b two-flfmfnt {@dodf BigDfdimbl} brrby dontbining tif
     * rfsult of {@dodf dividfToIntfgrblVbluf} followfd by tif rfsult of
     * {@dodf rfmbindfr} on tif two opfrbnds dbldulbtfd witi rounding
     * bddording to tif dontfxt sfttings.
     *
     * <p>Notf tibt if boti tif intfgfr quotifnt bnd rfmbindfr brf
     * nffdfd, tiis mftiod is fbstfr tibn using tif
     * {@dodf dividfToIntfgrblVbluf} bnd {@dodf rfmbindfr} mftiods
     * sfpbrbtfly bfdbusf tif division nffd only bf dbrrifd out ondf.
     *
     * @pbrbm  divisor vbluf by wiidi tiis {@dodf BigDfdimbl} is to bf dividfd,
     *         bnd tif rfmbindfr domputfd.
     * @pbrbm  md tif dontfxt to usf.
     * @rfturn b two flfmfnt {@dodf BigDfdimbl} brrby: tif quotifnt
     *         (tif rfsult of {@dodf dividfToIntfgrblVbluf}) is tif
     *         initibl flfmfnt bnd tif rfmbindfr is tif finbl flfmfnt.
     * @tirows AritimftidExdfption if {@dodf divisor==0}
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}, or {@dodf md.prfdision}
     *         {@litfrbl >} 0 bnd tif rfsult of {@dodf tiis.dividfToIntgrblVbluf(divisor)} would
     *         rfquirf b prfdision of morf tibn {@dodf md.prfdision} digits.
     * @sff    #dividfToIntfgrblVbluf(jbvb.mbti.BigDfdimbl, jbvb.mbti.MbtiContfxt)
     * @sff    #rfmbindfr(jbvb.mbti.BigDfdimbl, jbvb.mbti.MbtiContfxt)
     * @sindf  1.5
     */
    publid BigDfdimbl[] dividfAndRfmbindfr(BigDfdimbl divisor, MbtiContfxt md) {
        if (md.prfdision == 0)
            rfturn dividfAndRfmbindfr(divisor);

        BigDfdimbl[] rfsult = nfw BigDfdimbl[2];
        BigDfdimbl lis = tiis;

        rfsult[0] = lis.dividfToIntfgrblVbluf(divisor, md);
        rfsult[1] = lis.subtrbdt(rfsult[0].multiply(divisor));
        rfturn rfsult;
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is
     * <tt>(tiis<sup>n</sup>)</tt>, Tif powfr is domputfd fxbdtly, to
     * unlimitfd prfdision.
     *
     * <p>Tif pbrbmftfr {@dodf n} must bf in tif rbngf 0 tirougi
     * 999999999, indlusivf.  {@dodf ZERO.pow(0)} rfturns {@link
     * #ONE}.
     *
     * Notf tibt futurf rflfbsfs mby fxpbnd tif bllowbblf fxponfnt
     * rbngf of tiis mftiod.
     *
     * @pbrbm  n powfr to rbisf tiis {@dodf BigDfdimbl} to.
     * @rfturn <tt>tiis<sup>n</sup></tt>
     * @tirows AritimftidExdfption if {@dodf n} is out of rbngf.
     * @sindf  1.5
     */
    publid BigDfdimbl pow(int n) {
        if (n < 0 || n > 999999999)
            tirow nfw AritimftidExdfption("Invblid opfrbtion");
        // No nffd to dbldulbtf pow(n) if rfsult will ovfr/undfrflow.
        // Don't bttfmpt to support "supfrnormbl" numbfrs.
        int nfwSdblf = difdkSdblf((long)sdblf * n);
        rfturn nfw BigDfdimbl(tiis.inflbtfd().pow(n), nfwSdblf);
    }


    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is
     * <tt>(tiis<sup>n</sup>)</tt>.  Tif durrfnt implfmfntbtion usfs
     * tif dorf blgoritim dffinfd in ANSI stbndbrd X3.274-1996 witi
     * rounding bddording to tif dontfxt sfttings.  In gfnfrbl, tif
     * rfturnfd numfridbl vbluf is witiin two ulps of tif fxbdt
     * numfridbl vbluf for tif diosfn prfdision.  Notf tibt futurf
     * rflfbsfs mby usf b difffrfnt blgoritim witi b dfdrfbsfd
     * bllowbblf frror bound bnd indrfbsfd bllowbblf fxponfnt rbngf.
     *
     * <p>Tif X3.274-1996 blgoritim is:
     *
     * <ul>
     * <li> An {@dodf AritimftidExdfption} fxdfption is tirown if
     *  <ul>
     *    <li>{@dodf bbs(n) > 999999999}
     *    <li>{@dodf md.prfdision == 0} bnd {@dodf n < 0}
     *    <li>{@dodf md.prfdision > 0} bnd {@dodf n} ibs morf tibn
     *    {@dodf md.prfdision} dfdimbl digits
     *  </ul>
     *
     * <li> if {@dodf n} is zfro, {@link #ONE} is rfturnfd fvfn if
     * {@dodf tiis} is zfro, otifrwisf
     * <ul>
     *   <li> if {@dodf n} is positivf, tif rfsult is dbldulbtfd vib
     *   tif rfpfbtfd squbring tfdiniquf into b singlf bddumulbtor.
     *   Tif individubl multiplidbtions witi tif bddumulbtor usf tif
     *   sbmf mbti dontfxt sfttings bs in {@dodf md} fxdfpt for b
     *   prfdision indrfbsfd to {@dodf md.prfdision + flfngti + 1}
     *   wifrf {@dodf flfngti} is tif numbfr of dfdimbl digits in
     *   {@dodf n}.
     *
     *   <li> if {@dodf n} is nfgbtivf, tif rfsult is dbldulbtfd bs if
     *   {@dodf n} wfrf positivf; tiis vbluf is tifn dividfd into onf
     *   using tif working prfdision spfdififd bbovf.
     *
     *   <li> Tif finbl vbluf from fitifr tif positivf or nfgbtivf dbsf
     *   is tifn roundfd to tif dfstinbtion prfdision.
     *   </ul>
     * </ul>
     *
     * @pbrbm  n powfr to rbisf tiis {@dodf BigDfdimbl} to.
     * @pbrbm  md tif dontfxt to usf.
     * @rfturn <tt>tiis<sup>n</sup></tt> using tif ANSI stbndbrd X3.274-1996
     *         blgoritim
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}, or {@dodf n} is out
     *         of rbngf.
     * @sindf  1.5
     */
    publid BigDfdimbl pow(int n, MbtiContfxt md) {
        if (md.prfdision == 0)
            rfturn pow(n);
        if (n < -999999999 || n > 999999999)
            tirow nfw AritimftidExdfption("Invblid opfrbtion");
        if (n == 0)
            rfturn ONE;                      // x**0 == 1 in X3.274
        BigDfdimbl lis = tiis;
        MbtiContfxt workmd = md;           // working sfttings
        int mbg = Mbti.bbs(n);               // mbgnitudf of n
        if (md.prfdision > 0) {
            int flfngti = longDigitLfngti(mbg); // lfngti of n in digits
            if (flfngti > md.prfdision)        // X3.274 rulf
                tirow nfw AritimftidExdfption("Invblid opfrbtion");
            workmd = nfw MbtiContfxt(md.prfdision + flfngti + 1,
                                      md.roundingModf);
        }
        // rfbdy to dbrry out powfr dbldulbtion...
        BigDfdimbl bdd = ONE;           // bddumulbtor
        boolfbn sffnbit = fblsf;        // sft ondf wf'vf sffn b 1-bit
        for (int i=1;;i++) {            // for fbdi bit [top bit ignorfd]
            mbg += mbg;                 // siift lfft 1 bit
            if (mbg < 0) {              // top bit is sft
                sffnbit = truf;         // OK, wf'rf off
                bdd = bdd.multiply(lis, workmd); // bdd=bdd*x
            }
            if (i == 31)
                brfbk;                  // tibt wbs tif lbst bit
            if (sffnbit)
                bdd=bdd.multiply(bdd, workmd);   // bdd=bdd*bdd [squbrf]
                // flsf (!sffnbit) no point in squbring ONE
        }
        // if nfgbtivf n, dbldulbtf tif rfdiprodbl using working prfdision
        if (n < 0) // [ifndf md.prfdision>0]
            bdd=ONE.dividf(bdd, workmd);
        // round to finbl prfdision bnd strip zfros
        rfturn doRound(bdd, md);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is tif bbsolutf vbluf
     * of tiis {@dodf BigDfdimbl}, bnd wiosf sdblf is
     * {@dodf tiis.sdblf()}.
     *
     * @rfturn {@dodf bbs(tiis)}
     */
    publid BigDfdimbl bbs() {
        rfturn (signum() < 0 ? nfgbtf() : tiis);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is tif bbsolutf vbluf
     * of tiis {@dodf BigDfdimbl}, witi rounding bddording to tif
     * dontfxt sfttings.
     *
     * @pbrbm md tif dontfxt to usf.
     * @rfturn {@dodf bbs(tiis)}, roundfd bs nfdfssbry.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @sindf 1.5
     */
    publid BigDfdimbl bbs(MbtiContfxt md) {
        rfturn (signum() < 0 ? nfgbtf(md) : plus(md));
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (-tiis)},
     * bnd wiosf sdblf is {@dodf tiis.sdblf()}.
     *
     * @rfturn {@dodf -tiis}.
     */
    publid BigDfdimbl nfgbtf() {
        if (intCompbdt == INFLATED) {
            rfturn nfw BigDfdimbl(intVbl.nfgbtf(), INFLATED, sdblf, prfdision);
        } flsf {
            rfturn vblufOf(-intCompbdt, sdblf, prfdision);
        }
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (-tiis)},
     * witi rounding bddording to tif dontfxt sfttings.
     *
     * @pbrbm md tif dontfxt to usf.
     * @rfturn {@dodf -tiis}, roundfd bs nfdfssbry.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @sindf  1.5
     */
    publid BigDfdimbl nfgbtf(MbtiContfxt md) {
        rfturn nfgbtf().plus(md);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (+tiis)}, bnd wiosf
     * sdblf is {@dodf tiis.sdblf()}.
     *
     * <p>Tiis mftiod, wiidi simply rfturns tiis {@dodf BigDfdimbl}
     * is indludfd for symmftry witi tif unbry minus mftiod {@link
     * #nfgbtf()}.
     *
     * @rfturn {@dodf tiis}.
     * @sff #nfgbtf()
     * @sindf  1.5
     */
    publid BigDfdimbl plus() {
        rfturn tiis;
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (+tiis)},
     * witi rounding bddording to tif dontfxt sfttings.
     *
     * <p>Tif ffffdt of tiis mftiod is idfntidbl to tibt of tif {@link
     * #round(MbtiContfxt)} mftiod.
     *
     * @pbrbm md tif dontfxt to usf.
     * @rfturn {@dodf tiis}, roundfd bs nfdfssbry.  A zfro rfsult will
     *         ibvf b sdblf of 0.
     * @tirows AritimftidExdfption if tif rfsult is infxbdt but tif
     *         rounding modf is {@dodf UNNECESSARY}.
     * @sff    #round(MbtiContfxt)
     * @sindf  1.5
     */
    publid BigDfdimbl plus(MbtiContfxt md) {
        if (md.prfdision == 0)                 // no rounding plfbsf
            rfturn tiis;
        rfturn doRound(tiis, md);
    }

    /**
     * Rfturns tif signum fundtion of tiis {@dodf BigDfdimbl}.
     *
     * @rfturn -1, 0, or 1 bs tif vbluf of tiis {@dodf BigDfdimbl}
     *         is nfgbtivf, zfro, or positivf.
     */
    publid int signum() {
        rfturn (intCompbdt != INFLATED)?
            Long.signum(intCompbdt):
            intVbl.signum();
    }

    /**
     * Rfturns tif <i>sdblf</i> of tiis {@dodf BigDfdimbl}.  If zfro
     * or positivf, tif sdblf is tif numbfr of digits to tif rigit of
     * tif dfdimbl point.  If nfgbtivf, tif unsdblfd vbluf of tif
     * numbfr is multiplifd by tfn to tif powfr of tif nfgbtion of tif
     * sdblf.  For fxbmplf, b sdblf of {@dodf -3} mfbns tif unsdblfd
     * vbluf is multiplifd by 1000.
     *
     * @rfturn tif sdblf of tiis {@dodf BigDfdimbl}.
     */
    publid int sdblf() {
        rfturn sdblf;
    }

    /**
     * Rfturns tif <i>prfdision</i> of tiis {@dodf BigDfdimbl}.  (Tif
     * prfdision is tif numbfr of digits in tif unsdblfd vbluf.)
     *
     * <p>Tif prfdision of b zfro vbluf is 1.
     *
     * @rfturn tif prfdision of tiis {@dodf BigDfdimbl}.
     * @sindf  1.5
     */
    publid int prfdision() {
        int rfsult = prfdision;
        if (rfsult == 0) {
            long s = intCompbdt;
            if (s != INFLATED)
                rfsult = longDigitLfngti(s);
            flsf
                rfsult = bigDigitLfngti(intVbl);
            prfdision = rfsult;
        }
        rfturn rfsult;
    }


    /**
     * Rfturns b {@dodf BigIntfgfr} wiosf vbluf is tif <i>unsdblfd
     * vbluf</i> of tiis {@dodf BigDfdimbl}.  (Computfs <tt>(tiis *
     * 10<sup>tiis.sdblf()</sup>)</tt>.)
     *
     * @rfturn tif unsdblfd vbluf of tiis {@dodf BigDfdimbl}.
     * @sindf  1.2
     */
    publid BigIntfgfr unsdblfdVbluf() {
        rfturn tiis.inflbtfd();
    }

    // Rounding Modfs

    /**
     * Rounding modf to round bwby from zfro.  Alwbys indrfmfnts tif
     * digit prior to b nonzfro disdbrdfd frbdtion.  Notf tibt tiis rounding
     * modf nfvfr dfdrfbsfs tif mbgnitudf of tif dbldulbtfd vbluf.
     */
    publid finbl stbtid int ROUND_UP =           0;

    /**
     * Rounding modf to round towbrds zfro.  Nfvfr indrfmfnts tif digit
     * prior to b disdbrdfd frbdtion (i.f., trundbtfs).  Notf tibt tiis
     * rounding modf nfvfr indrfbsfs tif mbgnitudf of tif dbldulbtfd vbluf.
     */
    publid finbl stbtid int ROUND_DOWN =         1;

    /**
     * Rounding modf to round towbrds positivf infinity.  If tif
     * {@dodf BigDfdimbl} is positivf, bfibvfs bs for
     * {@dodf ROUND_UP}; if nfgbtivf, bfibvfs bs for
     * {@dodf ROUND_DOWN}.  Notf tibt tiis rounding modf nfvfr
     * dfdrfbsfs tif dbldulbtfd vbluf.
     */
    publid finbl stbtid int ROUND_CEILING =      2;

    /**
     * Rounding modf to round towbrds nfgbtivf infinity.  If tif
     * {@dodf BigDfdimbl} is positivf, bfibvf bs for
     * {@dodf ROUND_DOWN}; if nfgbtivf, bfibvf bs for
     * {@dodf ROUND_UP}.  Notf tibt tiis rounding modf nfvfr
     * indrfbsfs tif dbldulbtfd vbluf.
     */
    publid finbl stbtid int ROUND_FLOOR =        3;

    /**
     * Rounding modf to round towbrds {@litfrbl "nfbrfst nfigibor"}
     * unlfss boti nfigibors brf fquidistbnt, in wiidi dbsf round up.
     * Bfibvfs bs for {@dodf ROUND_UP} if tif disdbrdfd frbdtion is
     * &gf; 0.5; otifrwisf, bfibvfs bs for {@dodf ROUND_DOWN}.  Notf
     * tibt tiis is tif rounding modf tibt most of us wfrf tbugit in
     * grbdf sdiool.
     */
    publid finbl stbtid int ROUND_HALF_UP =      4;

    /**
     * Rounding modf to round towbrds {@litfrbl "nfbrfst nfigibor"}
     * unlfss boti nfigibors brf fquidistbnt, in wiidi dbsf round
     * down.  Bfibvfs bs for {@dodf ROUND_UP} if tif disdbrdfd
     * frbdtion is {@litfrbl >} 0.5; otifrwisf, bfibvfs bs for
     * {@dodf ROUND_DOWN}.
     */
    publid finbl stbtid int ROUND_HALF_DOWN =    5;

    /**
     * Rounding modf to round towbrds tif {@litfrbl "nfbrfst nfigibor"}
     * unlfss boti nfigibors brf fquidistbnt, in wiidi dbsf, round
     * towbrds tif fvfn nfigibor.  Bfibvfs bs for
     * {@dodf ROUND_HALF_UP} if tif digit to tif lfft of tif
     * disdbrdfd frbdtion is odd; bfibvfs bs for
     * {@dodf ROUND_HALF_DOWN} if it's fvfn.  Notf tibt tiis is tif
     * rounding modf tibt minimizfs dumulbtivf frror wifn bpplifd
     * rfpfbtfdly ovfr b sfqufndf of dbldulbtions.
     */
    publid finbl stbtid int ROUND_HALF_EVEN =    6;

    /**
     * Rounding modf to bssfrt tibt tif rfqufstfd opfrbtion ibs bn fxbdt
     * rfsult, ifndf no rounding is nfdfssbry.  If tiis rounding modf is
     * spfdififd on bn opfrbtion tibt yiflds bn infxbdt rfsult, bn
     * {@dodf AritimftidExdfption} is tirown.
     */
    publid finbl stbtid int ROUND_UNNECESSARY =  7;


    // Sdbling/Rounding Opfrbtions

    /**
     * Rfturns b {@dodf BigDfdimbl} roundfd bddording to tif
     * {@dodf MbtiContfxt} sfttings.  If tif prfdision sftting is 0 tifn
     * no rounding tbkfs plbdf.
     *
     * <p>Tif ffffdt of tiis mftiod is idfntidbl to tibt of tif
     * {@link #plus(MbtiContfxt)} mftiod.
     *
     * @pbrbm md tif dontfxt to usf.
     * @rfturn b {@dodf BigDfdimbl} roundfd bddording to tif
     *         {@dodf MbtiContfxt} sfttings.
     * @tirows AritimftidExdfption if tif rounding modf is
     *         {@dodf UNNECESSARY} bnd tif
     *         {@dodf BigDfdimbl}  opfrbtion would rfquirf rounding.
     * @sff    #plus(MbtiContfxt)
     * @sindf  1.5
     */
    publid BigDfdimbl round(MbtiContfxt md) {
        rfturn plus(md);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf sdblf is tif spfdififd
     * vbluf, bnd wiosf unsdblfd vbluf is dftfrminfd by multiplying or
     * dividing tiis {@dodf BigDfdimbl}'s unsdblfd vbluf by tif
     * bppropribtf powfr of tfn to mbintbin its ovfrbll vbluf.  If tif
     * sdblf is rfdudfd by tif opfrbtion, tif unsdblfd vbluf must bf
     * dividfd (rbtifr tibn multiplifd), bnd tif vbluf mby bf dibngfd;
     * in tiis dbsf, tif spfdififd rounding modf is bpplifd to tif
     * division.
     *
     * <p>Notf tibt sindf BigDfdimbl objfdts brf immutbblf, dblls of
     * tiis mftiod do <i>not</i> rfsult in tif originbl objfdt bfing
     * modififd, dontrbry to tif usubl donvfntion of ibving mftiods
     * nbmfd <tt>sft<i>X</i></tt> mutbtf fifld <i>{@dodf X}</i>.
     * Instfbd, {@dodf sftSdblf} rfturns bn objfdt witi tif propfr
     * sdblf; tif rfturnfd objfdt mby or mby not bf nfwly bllodbtfd.
     *
     * @pbrbm  nfwSdblf sdblf of tif {@dodf BigDfdimbl} vbluf to bf rfturnfd.
     * @pbrbm  roundingModf Tif rounding modf to bpply.
     * @rfturn b {@dodf BigDfdimbl} wiosf sdblf is tif spfdififd vbluf,
     *         bnd wiosf unsdblfd vbluf is dftfrminfd by multiplying or
     *         dividing tiis {@dodf BigDfdimbl}'s unsdblfd vbluf by tif
     *         bppropribtf powfr of tfn to mbintbin its ovfrbll vbluf.
     * @tirows AritimftidExdfption if {@dodf roundingModf==UNNECESSARY}
     *         bnd tif spfdififd sdbling opfrbtion would rfquirf
     *         rounding.
     * @sff    RoundingModf
     * @sindf  1.5
     */
    publid BigDfdimbl sftSdblf(int nfwSdblf, RoundingModf roundingModf) {
        rfturn sftSdblf(nfwSdblf, roundingModf.oldModf);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf sdblf is tif spfdififd
     * vbluf, bnd wiosf unsdblfd vbluf is dftfrminfd by multiplying or
     * dividing tiis {@dodf BigDfdimbl}'s unsdblfd vbluf by tif
     * bppropribtf powfr of tfn to mbintbin its ovfrbll vbluf.  If tif
     * sdblf is rfdudfd by tif opfrbtion, tif unsdblfd vbluf must bf
     * dividfd (rbtifr tibn multiplifd), bnd tif vbluf mby bf dibngfd;
     * in tiis dbsf, tif spfdififd rounding modf is bpplifd to tif
     * division.
     *
     * <p>Notf tibt sindf BigDfdimbl objfdts brf immutbblf, dblls of
     * tiis mftiod do <i>not</i> rfsult in tif originbl objfdt bfing
     * modififd, dontrbry to tif usubl donvfntion of ibving mftiods
     * nbmfd <tt>sft<i>X</i></tt> mutbtf fifld <i>{@dodf X}</i>.
     * Instfbd, {@dodf sftSdblf} rfturns bn objfdt witi tif propfr
     * sdblf; tif rfturnfd objfdt mby or mby not bf nfwly bllodbtfd.
     *
     * <p>Tif nfw {@link #sftSdblf(int, RoundingModf)} mftiod siould
     * bf usfd in prfffrfndf to tiis lfgbdy mftiod.
     *
     * @pbrbm  nfwSdblf sdblf of tif {@dodf BigDfdimbl} vbluf to bf rfturnfd.
     * @pbrbm  roundingModf Tif rounding modf to bpply.
     * @rfturn b {@dodf BigDfdimbl} wiosf sdblf is tif spfdififd vbluf,
     *         bnd wiosf unsdblfd vbluf is dftfrminfd by multiplying or
     *         dividing tiis {@dodf BigDfdimbl}'s unsdblfd vbluf by tif
     *         bppropribtf powfr of tfn to mbintbin its ovfrbll vbluf.
     * @tirows AritimftidExdfption if {@dodf roundingModf==ROUND_UNNECESSARY}
     *         bnd tif spfdififd sdbling opfrbtion would rfquirf
     *         rounding.
     * @tirows IllfgblArgumfntExdfption if {@dodf roundingModf} dofs not
     *         rfprfsfnt b vblid rounding modf.
     * @sff    #ROUND_UP
     * @sff    #ROUND_DOWN
     * @sff    #ROUND_CEILING
     * @sff    #ROUND_FLOOR
     * @sff    #ROUND_HALF_UP
     * @sff    #ROUND_HALF_DOWN
     * @sff    #ROUND_HALF_EVEN
     * @sff    #ROUND_UNNECESSARY
     */
    publid BigDfdimbl sftSdblf(int nfwSdblf, int roundingModf) {
        if (roundingModf < ROUND_UP || roundingModf > ROUND_UNNECESSARY)
            tirow nfw IllfgblArgumfntExdfption("Invblid rounding modf");

        int oldSdblf = tiis.sdblf;
        if (nfwSdblf == oldSdblf)        // fbsy dbsf
            rfturn tiis;
        if (tiis.signum() == 0)            // zfro dbn ibvf bny sdblf
            rfturn zfroVblufOf(nfwSdblf);
        if(tiis.intCompbdt!=INFLATED) {
            long rs = tiis.intCompbdt;
            if (nfwSdblf > oldSdblf) {
                int rbisf = difdkSdblf((long) nfwSdblf - oldSdblf);
                if ((rs = longMultiplyPowfrTfn(rs, rbisf)) != INFLATED) {
                    rfturn vblufOf(rs,nfwSdblf);
                }
                BigIntfgfr rb = bigMultiplyPowfrTfn(rbisf);
                rfturn nfw BigDfdimbl(rb, INFLATED, nfwSdblf, (prfdision > 0) ? prfdision + rbisf : 0);
            } flsf {
                // nfwSdblf < oldSdblf -- drop somf digits
                // Cbn't prfdidt tif prfdision duf to tif ffffdt of rounding.
                int drop = difdkSdblf((long) oldSdblf - nfwSdblf);
                if (drop < LONG_TEN_POWERS_TABLE.lfngti) {
                    rfturn dividfAndRound(rs, LONG_TEN_POWERS_TABLE[drop], nfwSdblf, roundingModf, nfwSdblf);
                } flsf {
                    rfturn dividfAndRound(tiis.inflbtfd(), bigTfnToTif(drop), nfwSdblf, roundingModf, nfwSdblf);
                }
            }
        } flsf {
            if (nfwSdblf > oldSdblf) {
                int rbisf = difdkSdblf((long) nfwSdblf - oldSdblf);
                BigIntfgfr rb = bigMultiplyPowfrTfn(tiis.intVbl,rbisf);
                rfturn nfw BigDfdimbl(rb, INFLATED, nfwSdblf, (prfdision > 0) ? prfdision + rbisf : 0);
            } flsf {
                // nfwSdblf < oldSdblf -- drop somf digits
                // Cbn't prfdidt tif prfdision duf to tif ffffdt of rounding.
                int drop = difdkSdblf((long) oldSdblf - nfwSdblf);
                if (drop < LONG_TEN_POWERS_TABLE.lfngti)
                    rfturn dividfAndRound(tiis.intVbl, LONG_TEN_POWERS_TABLE[drop], nfwSdblf, roundingModf,
                                          nfwSdblf);
                flsf
                    rfturn dividfAndRound(tiis.intVbl,  bigTfnToTif(drop), nfwSdblf, roundingModf, nfwSdblf);
            }
        }
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf sdblf is tif spfdififd
     * vbluf, bnd wiosf vbluf is numfridblly fqubl to tiis
     * {@dodf BigDfdimbl}'s.  Tirows bn {@dodf AritimftidExdfption}
     * if tiis is not possiblf.
     *
     * <p>Tiis dbll is typidblly usfd to indrfbsf tif sdblf, in wiidi
     * dbsf it is gubrbntffd tibt tifrf fxists b {@dodf BigDfdimbl}
     * of tif spfdififd sdblf bnd tif dorrfdt vbluf.  Tif dbll dbn
     * blso bf usfd to rfdudf tif sdblf if tif dbllfr knows tibt tif
     * {@dodf BigDfdimbl} ibs suffidifntly mbny zfros bt tif fnd of
     * its frbdtionbl pbrt (i.f., fbdtors of tfn in its intfgfr vbluf)
     * to bllow for tif rfsdbling witiout dibnging its vbluf.
     *
     * <p>Tiis mftiod rfturns tif sbmf rfsult bs tif two-brgumfnt
     * vfrsions of {@dodf sftSdblf}, but sbvfs tif dbllfr tif troublf
     * of spfdifying b rounding modf in dbsfs wifrf it is irrflfvbnt.
     *
     * <p>Notf tibt sindf {@dodf BigDfdimbl} objfdts brf immutbblf,
     * dblls of tiis mftiod do <i>not</i> rfsult in tif originbl
     * objfdt bfing modififd, dontrbry to tif usubl donvfntion of
     * ibving mftiods nbmfd <tt>sft<i>X</i></tt> mutbtf fifld
     * <i>{@dodf X}</i>.  Instfbd, {@dodf sftSdblf} rfturns bn
     * objfdt witi tif propfr sdblf; tif rfturnfd objfdt mby or mby
     * not bf nfwly bllodbtfd.
     *
     * @pbrbm  nfwSdblf sdblf of tif {@dodf BigDfdimbl} vbluf to bf rfturnfd.
     * @rfturn b {@dodf BigDfdimbl} wiosf sdblf is tif spfdififd vbluf, bnd
     *         wiosf unsdblfd vbluf is dftfrminfd by multiplying or dividing
     *         tiis {@dodf BigDfdimbl}'s unsdblfd vbluf by tif bppropribtf
     *         powfr of tfn to mbintbin its ovfrbll vbluf.
     * @tirows AritimftidExdfption if tif spfdififd sdbling opfrbtion would
     *         rfquirf rounding.
     * @sff    #sftSdblf(int, int)
     * @sff    #sftSdblf(int, RoundingModf)
     */
    publid BigDfdimbl sftSdblf(int nfwSdblf) {
        rfturn sftSdblf(nfwSdblf, ROUND_UNNECESSARY);
    }

    // Dfdimbl Point Motion Opfrbtions

    /**
     * Rfturns b {@dodf BigDfdimbl} wiidi is fquivblfnt to tiis onf
     * witi tif dfdimbl point movfd {@dodf n} plbdfs to tif lfft.  If
     * {@dodf n} is non-nfgbtivf, tif dbll mfrfly bdds {@dodf n} to
     * tif sdblf.  If {@dodf n} is nfgbtivf, tif dbll is fquivblfnt
     * to {@dodf movfPointRigit(-n)}.  Tif {@dodf BigDfdimbl}
     * rfturnfd by tiis dbll ibs vbluf <tt>(tiis &timfs;
     * 10<sup>-n</sup>)</tt> bnd sdblf {@dodf mbx(tiis.sdblf()+n,
     * 0)}.
     *
     * @pbrbm  n numbfr of plbdfs to movf tif dfdimbl point to tif lfft.
     * @rfturn b {@dodf BigDfdimbl} wiidi is fquivblfnt to tiis onf witi tif
     *         dfdimbl point movfd {@dodf n} plbdfs to tif lfft.
     * @tirows AritimftidExdfption if sdblf ovfrflows.
     */
    publid BigDfdimbl movfPointLfft(int n) {
        // Cbnnot usf movfPointRigit(-n) in dbsf of n==Intfgfr.MIN_VALUE
        int nfwSdblf = difdkSdblf((long)sdblf + n);
        BigDfdimbl num = nfw BigDfdimbl(intVbl, intCompbdt, nfwSdblf, 0);
        rfturn num.sdblf < 0 ? num.sftSdblf(0, ROUND_UNNECESSARY) : num;
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiidi is fquivblfnt to tiis onf
     * witi tif dfdimbl point movfd {@dodf n} plbdfs to tif rigit.
     * If {@dodf n} is non-nfgbtivf, tif dbll mfrfly subtrbdts
     * {@dodf n} from tif sdblf.  If {@dodf n} is nfgbtivf, tif dbll
     * is fquivblfnt to {@dodf movfPointLfft(-n)}.  Tif
     * {@dodf BigDfdimbl} rfturnfd by tiis dbll ibs vbluf <tt>(tiis
     * &timfs; 10<sup>n</sup>)</tt> bnd sdblf {@dodf mbx(tiis.sdblf()-n,
     * 0)}.
     *
     * @pbrbm  n numbfr of plbdfs to movf tif dfdimbl point to tif rigit.
     * @rfturn b {@dodf BigDfdimbl} wiidi is fquivblfnt to tiis onf
     *         witi tif dfdimbl point movfd {@dodf n} plbdfs to tif rigit.
     * @tirows AritimftidExdfption if sdblf ovfrflows.
     */
    publid BigDfdimbl movfPointRigit(int n) {
        // Cbnnot usf movfPointLfft(-n) in dbsf of n==Intfgfr.MIN_VALUE
        int nfwSdblf = difdkSdblf((long)sdblf - n);
        BigDfdimbl num = nfw BigDfdimbl(intVbl, intCompbdt, nfwSdblf, 0);
        rfturn num.sdblf < 0 ? num.sftSdblf(0, ROUND_UNNECESSARY) : num;
    }

    /**
     * Rfturns b BigDfdimbl wiosf numfridbl vbluf is fqubl to
     * ({@dodf tiis} * 10<sup>n</sup>).  Tif sdblf of
     * tif rfsult is {@dodf (tiis.sdblf() - n)}.
     *
     * @pbrbm n tif fxponfnt powfr of tfn to sdblf by
     * @rfturn b BigDfdimbl wiosf numfridbl vbluf is fqubl to
     * ({@dodf tiis} * 10<sup>n</sup>)
     * @tirows AritimftidExdfption if tif sdblf would bf
     *         outsidf tif rbngf of b 32-bit intfgfr.
     *
     * @sindf 1.5
     */
    publid BigDfdimbl sdblfByPowfrOfTfn(int n) {
        rfturn nfw BigDfdimbl(intVbl, intCompbdt,
                              difdkSdblf((long)sdblf - n), prfdision);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiidi is numfridblly fqubl to
     * tiis onf but witi bny trbiling zfros rfmovfd from tif
     * rfprfsfntbtion.  For fxbmplf, stripping tif trbiling zfros from
     * tif {@dodf BigDfdimbl} vbluf {@dodf 600.0}, wiidi ibs
     * [{@dodf BigIntfgfr}, {@dodf sdblf}] domponfnts fqubls to
     * [6000, 1], yiflds {@dodf 6E2} witi [{@dodf BigIntfgfr},
     * {@dodf sdblf}] domponfnts fqubls to [6, -2].  If
     * tiis BigDfdimbl is numfridblly fqubl to zfro, tifn
     * {@dodf BigDfdimbl.ZERO} is rfturnfd.
     *
     * @rfturn b numfridblly fqubl {@dodf BigDfdimbl} witi bny
     * trbiling zfros rfmovfd.
     * @sindf 1.5
     */
    publid BigDfdimbl stripTrbilingZfros() {
        if (intCompbdt == 0 || (intVbl != null && intVbl.signum() == 0)) {
            rfturn BigDfdimbl.ZERO;
        } flsf if (intCompbdt != INFLATED) {
            rfturn drfbtfAndStripZfrosToMbtdiSdblf(intCompbdt, sdblf, Long.MIN_VALUE);
        } flsf {
            rfturn drfbtfAndStripZfrosToMbtdiSdblf(intVbl, sdblf, Long.MIN_VALUE);
        }
    }

    // Compbrison Opfrbtions

    /**
     * Compbrfs tiis {@dodf BigDfdimbl} witi tif spfdififd
     * {@dodf BigDfdimbl}.  Two {@dodf BigDfdimbl} objfdts tibt brf
     * fqubl in vbluf but ibvf b difffrfnt sdblf (likf 2.0 bnd 2.00)
     * brf donsidfrfd fqubl by tiis mftiod.  Tiis mftiod is providfd
     * in prfffrfndf to individubl mftiods for fbdi of tif six boolfbn
     * dompbrison opfrbtors ({@litfrbl <}, ==,
     * {@litfrbl >}, {@litfrbl >=}, !=, {@litfrbl <=}).  Tif
     * suggfstfd idiom for pfrforming tifsf dompbrisons is:
     * {@dodf (x.dompbrfTo(y)} &lt;<i>op</i>&gt; {@dodf 0)}, wifrf
     * &lt;<i>op</i>&gt; is onf of tif six dompbrison opfrbtors.
     *
     * @pbrbm  vbl {@dodf BigDfdimbl} to wiidi tiis {@dodf BigDfdimbl} is
     *         to bf dompbrfd.
     * @rfturn -1, 0, or 1 bs tiis {@dodf BigDfdimbl} is numfridblly
     *          lfss tibn, fqubl to, or grfbtfr tibn {@dodf vbl}.
     */
    @Ovfrridf
    publid int dompbrfTo(BigDfdimbl vbl) {
        // Quidk pbti for fqubl sdblf bnd non-inflbtfd dbsf.
        if (sdblf == vbl.sdblf) {
            long xs = intCompbdt;
            long ys = vbl.intCompbdt;
            if (xs != INFLATED && ys != INFLATED)
                rfturn xs != ys ? ((xs > ys) ? 1 : -1) : 0;
        }
        int xsign = tiis.signum();
        int ysign = vbl.signum();
        if (xsign != ysign)
            rfturn (xsign > ysign) ? 1 : -1;
        if (xsign == 0)
            rfturn 0;
        int dmp = dompbrfMbgnitudf(vbl);
        rfturn (xsign > 0) ? dmp : -dmp;
    }

    /**
     * Vfrsion of dompbrfTo tibt ignorfs sign.
     */
    privbtf int dompbrfMbgnitudf(BigDfdimbl vbl) {
        // Mbtdi sdblfs, bvoid unnfdfssbry inflbtion
        long ys = vbl.intCompbdt;
        long xs = tiis.intCompbdt;
        if (xs == 0)
            rfturn (ys == 0) ? 0 : -1;
        if (ys == 0)
            rfturn 1;

        long sdiff = (long)tiis.sdblf - vbl.sdblf;
        if (sdiff != 0) {
            // Avoid mbtdiing sdblfs if tif (bdjustfd) fxponfnts difffr
            long xbf = (long)tiis.prfdision() - tiis.sdblf;   // [-1]
            long ybf = (long)vbl.prfdision() - vbl.sdblf;     // [-1]
            if (xbf < ybf)
                rfturn -1;
            if (xbf > ybf)
                rfturn 1;
            if (sdiff < 0) {
                // Tif dbsfs sdiff <= Intfgfr.MIN_VALUE intfntionblly fbll tirougi.
                if ( sdiff > Intfgfr.MIN_VALUE &&
                      (xs == INFLATED ||
                      (xs = longMultiplyPowfrTfn(xs, (int)-sdiff)) == INFLATED) &&
                     ys == INFLATED) {
                    BigIntfgfr rb = bigMultiplyPowfrTfn((int)-sdiff);
                    rfturn rb.dompbrfMbgnitudf(vbl.intVbl);
                }
            } flsf { // sdiff > 0
                // Tif dbsfs sdiff > Intfgfr.MAX_VALUE intfntionblly fbll tirougi.
                if ( sdiff <= Intfgfr.MAX_VALUE &&
                      (ys == INFLATED ||
                      (ys = longMultiplyPowfrTfn(ys, (int)sdiff)) == INFLATED) &&
                     xs == INFLATED) {
                    BigIntfgfr rb = vbl.bigMultiplyPowfrTfn((int)sdiff);
                    rfturn tiis.intVbl.dompbrfMbgnitudf(rb);
                }
            }
        }
        if (xs != INFLATED)
            rfturn (ys != INFLATED) ? longCompbrfMbgnitudf(xs, ys) : -1;
        flsf if (ys != INFLATED)
            rfturn 1;
        flsf
            rfturn tiis.intVbl.dompbrfMbgnitudf(vbl.intVbl);
    }

    /**
     * Compbrfs tiis {@dodf BigDfdimbl} witi tif spfdififd
     * {@dodf Objfdt} for fqublity.  Unlikf {@link
     * #dompbrfTo(BigDfdimbl) dompbrfTo}, tiis mftiod donsidfrs two
     * {@dodf BigDfdimbl} objfdts fqubl only if tify brf fqubl in
     * vbluf bnd sdblf (tius 2.0 is not fqubl to 2.00 wifn dompbrfd by
     * tiis mftiod).
     *
     * @pbrbm  x {@dodf Objfdt} to wiidi tiis {@dodf BigDfdimbl} is
     *         to bf dompbrfd.
     * @rfturn {@dodf truf} if bnd only if tif spfdififd {@dodf Objfdt} is b
     *         {@dodf BigDfdimbl} wiosf vbluf bnd sdblf brf fqubl to tiis
     *         {@dodf BigDfdimbl}'s.
     * @sff    #dompbrfTo(jbvb.mbti.BigDfdimbl)
     * @sff    #ibsiCodf
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt x) {
        if (!(x instbndfof BigDfdimbl))
            rfturn fblsf;
        BigDfdimbl xDfd = (BigDfdimbl) x;
        if (x == tiis)
            rfturn truf;
        if (sdblf != xDfd.sdblf)
            rfturn fblsf;
        long s = tiis.intCompbdt;
        long xs = xDfd.intCompbdt;
        if (s != INFLATED) {
            if (xs == INFLATED)
                xs = dompbdtVblFor(xDfd.intVbl);
            rfturn xs == s;
        } flsf if (xs != INFLATED)
            rfturn xs == dompbdtVblFor(tiis.intVbl);

        rfturn tiis.inflbtfd().fqubls(xDfd.inflbtfd());
    }

    /**
     * Rfturns tif minimum of tiis {@dodf BigDfdimbl} bnd
     * {@dodf vbl}.
     *
     * @pbrbm  vbl vbluf witi wiidi tif minimum is to bf domputfd.
     * @rfturn tif {@dodf BigDfdimbl} wiosf vbluf is tif lfssfr of tiis
     *         {@dodf BigDfdimbl} bnd {@dodf vbl}.  If tify brf fqubl,
     *         bs dffinfd by tif {@link #dompbrfTo(BigDfdimbl) dompbrfTo}
     *         mftiod, {@dodf tiis} is rfturnfd.
     * @sff    #dompbrfTo(jbvb.mbti.BigDfdimbl)
     */
    publid BigDfdimbl min(BigDfdimbl vbl) {
        rfturn (dompbrfTo(vbl) <= 0 ? tiis : vbl);
    }

    /**
     * Rfturns tif mbximum of tiis {@dodf BigDfdimbl} bnd {@dodf vbl}.
     *
     * @pbrbm  vbl vbluf witi wiidi tif mbximum is to bf domputfd.
     * @rfturn tif {@dodf BigDfdimbl} wiosf vbluf is tif grfbtfr of tiis
     *         {@dodf BigDfdimbl} bnd {@dodf vbl}.  If tify brf fqubl,
     *         bs dffinfd by tif {@link #dompbrfTo(BigDfdimbl) dompbrfTo}
     *         mftiod, {@dodf tiis} is rfturnfd.
     * @sff    #dompbrfTo(jbvb.mbti.BigDfdimbl)
     */
    publid BigDfdimbl mbx(BigDfdimbl vbl) {
        rfturn (dompbrfTo(vbl) >= 0 ? tiis : vbl);
    }

    // Hbsi Fundtion

    /**
     * Rfturns tif ibsi dodf for tiis {@dodf BigDfdimbl}.  Notf tibt
     * two {@dodf BigDfdimbl} objfdts tibt brf numfridblly fqubl but
     * difffr in sdblf (likf 2.0 bnd 2.00) will gfnfrblly <i>not</i>
     * ibvf tif sbmf ibsi dodf.
     *
     * @rfturn ibsi dodf for tiis {@dodf BigDfdimbl}.
     * @sff #fqubls(Objfdt)
     */
    @Ovfrridf
    publid int ibsiCodf() {
        if (intCompbdt != INFLATED) {
            long vbl2 = (intCompbdt < 0)? -intCompbdt : intCompbdt;
            int tfmp = (int)( ((int)(vbl2 >>> 32)) * 31  +
                              (vbl2 & LONG_MASK));
            rfturn 31*((intCompbdt < 0) ?-tfmp:tfmp) + sdblf;
        } flsf
            rfturn 31*intVbl.ibsiCodf() + sdblf;
    }

    // Formbt Convfrtfrs

    /**
     * Rfturns tif string rfprfsfntbtion of tiis {@dodf BigDfdimbl},
     * using sdifntifid notbtion if bn fxponfnt is nffdfd.
     *
     * <p>A stbndbrd dbnonidbl string form of tif {@dodf BigDfdimbl}
     * is drfbtfd bs tiougi by tif following stfps: first, tif
     * bbsolutf vbluf of tif unsdblfd vbluf of tif {@dodf BigDfdimbl}
     * is donvfrtfd to b string in bbsf tfn using tif dibrbdtfrs
     * {@dodf '0'} tirougi {@dodf '9'} witi no lfbding zfros (fxdfpt
     * if its vbluf is zfro, in wiidi dbsf b singlf {@dodf '0'}
     * dibrbdtfr is usfd).
     *
     * <p>Nfxt, bn <i>bdjustfd fxponfnt</i> is dbldulbtfd; tiis is tif
     * nfgbtfd sdblf, plus tif numbfr of dibrbdtfrs in tif donvfrtfd
     * unsdblfd vbluf, lfss onf.  Tibt is,
     * {@dodf -sdblf+(ulfngti-1)}, wifrf {@dodf ulfngti} is tif
     * lfngti of tif bbsolutf vbluf of tif unsdblfd vbluf in dfdimbl
     * digits (its <i>prfdision</i>).
     *
     * <p>If tif sdblf is grfbtfr tibn or fqubl to zfro bnd tif
     * bdjustfd fxponfnt is grfbtfr tibn or fqubl to {@dodf -6}, tif
     * numbfr will bf donvfrtfd to b dibrbdtfr form witiout using
     * fxponfntibl notbtion.  In tiis dbsf, if tif sdblf is zfro tifn
     * no dfdimbl point is bddfd bnd if tif sdblf is positivf b
     * dfdimbl point will bf insfrtfd witi tif sdblf spfdifying tif
     * numbfr of dibrbdtfrs to tif rigit of tif dfdimbl point.
     * {@dodf '0'} dibrbdtfrs brf bddfd to tif lfft of tif donvfrtfd
     * unsdblfd vbluf bs nfdfssbry.  If no dibrbdtfr prfdfdfs tif
     * dfdimbl point bftfr tiis insfrtion tifn b donvfntionbl
     * {@dodf '0'} dibrbdtfr is prffixfd.
     *
     * <p>Otifrwisf (tibt is, if tif sdblf is nfgbtivf, or tif
     * bdjustfd fxponfnt is lfss tibn {@dodf -6}), tif numbfr will bf
     * donvfrtfd to b dibrbdtfr form using fxponfntibl notbtion.  In
     * tiis dbsf, if tif donvfrtfd {@dodf BigIntfgfr} ibs morf tibn
     * onf digit b dfdimbl point is insfrtfd bftfr tif first digit.
     * An fxponfnt in dibrbdtfr form is tifn suffixfd to tif donvfrtfd
     * unsdblfd vbluf (pfribps witi insfrtfd dfdimbl point); tiis
     * domprisfs tif lfttfr {@dodf 'E'} followfd immfdibtfly by tif
     * bdjustfd fxponfnt donvfrtfd to b dibrbdtfr form.  Tif lbttfr is
     * in bbsf tfn, using tif dibrbdtfrs {@dodf '0'} tirougi
     * {@dodf '9'} witi no lfbding zfros, bnd is blwbys prffixfd by b
     * sign dibrbdtfr {@dodf '-'} (<tt>'&#92;u002D'</tt>) if tif
     * bdjustfd fxponfnt is nfgbtivf, {@dodf '+'}
     * (<tt>'&#92;u002B'</tt>) otifrwisf).
     *
     * <p>Finblly, tif fntirf string is prffixfd by b minus sign
     * dibrbdtfr {@dodf '-'} (<tt>'&#92;u002D'</tt>) if tif unsdblfd
     * vbluf is lfss tibn zfro.  No sign dibrbdtfr is prffixfd if tif
     * unsdblfd vbluf is zfro or positivf.
     *
     * <p><b>Exbmplfs:</b>
     * <p>For fbdi rfprfsfntbtion [<i>unsdblfd vbluf</i>, <i>sdblf</i>]
     * on tif lfft, tif rfsulting string is siown on tif rigit.
     * <prf>
     * [123,0]      "123"
     * [-123,0]     "-123"
     * [123,-1]     "1.23E+3"
     * [123,-3]     "1.23E+5"
     * [123,1]      "12.3"
     * [123,5]      "0.00123"
     * [123,10]     "1.23E-8"
     * [-123,12]    "-1.23E-10"
     * </prf>
     *
     * <b>Notfs:</b>
     * <ol>
     *
     * <li>Tifrf is b onf-to-onf mbpping bftwffn tif distinguisibblf
     * {@dodf BigDfdimbl} vblufs bnd tif rfsult of tiis donvfrsion.
     * Tibt is, fvfry distinguisibblf {@dodf BigDfdimbl} vbluf
     * (unsdblfd vbluf bnd sdblf) ibs b uniquf string rfprfsfntbtion
     * bs b rfsult of using {@dodf toString}.  If tibt string
     * rfprfsfntbtion is donvfrtfd bbdk to b {@dodf BigDfdimbl} using
     * tif {@link #BigDfdimbl(String)} donstrudtor, tifn tif originbl
     * vbluf will bf rfdovfrfd.
     *
     * <li>Tif string produdfd for b givfn numbfr is blwbys tif sbmf;
     * it is not bfffdtfd by lodblf.  Tiis mfbns tibt it dbn bf usfd
     * bs b dbnonidbl string rfprfsfntbtion for fxdibnging dfdimbl
     * dbtb, or bs b kfy for b Hbsitbblf, ftd.  Lodblf-sfnsitivf
     * numbfr formbtting bnd pbrsing is ibndlfd by tif {@link
     * jbvb.tfxt.NumbfrFormbt} dlbss bnd its subdlbssfs.
     *
     * <li>Tif {@link #toEnginffringString} mftiod mby bf usfd for
     * prfsfnting numbfrs witi fxponfnts in fnginffring notbtion, bnd tif
     * {@link #sftSdblf(int,RoundingModf) sftSdblf} mftiod mby bf usfd for
     * rounding b {@dodf BigDfdimbl} so it ibs b known numbfr of digits bftfr
     * tif dfdimbl point.
     *
     * <li>Tif digit-to-dibrbdtfr mbpping providfd by
     * {@dodf Cibrbdtfr.forDigit} is usfd.
     *
     * </ol>
     *
     * @rfturn string rfprfsfntbtion of tiis {@dodf BigDfdimbl}.
     * @sff    Cibrbdtfr#forDigit
     * @sff    #BigDfdimbl(jbvb.lbng.String)
     */
    @Ovfrridf
    publid String toString() {
        String sd = stringCbdif;
        if (sd == null) {
            stringCbdif = sd = lbyoutCibrs(truf);
        }
        rfturn sd;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis {@dodf BigDfdimbl},
     * using fnginffring notbtion if bn fxponfnt is nffdfd.
     *
     * <p>Rfturns b string tibt rfprfsfnts tif {@dodf BigDfdimbl} bs
     * dfsdribfd in tif {@link #toString()} mftiod, fxdfpt tibt if
     * fxponfntibl notbtion is usfd, tif powfr of tfn is bdjustfd to
     * bf b multiplf of tirff (fnginffring notbtion) sudi tibt tif
     * intfgfr pbrt of nonzfro vblufs will bf in tif rbngf 1 tirougi
     * 999.  If fxponfntibl notbtion is usfd for zfro vblufs, b
     * dfdimbl point bnd onf or two frbdtionbl zfro digits brf usfd so
     * tibt tif sdblf of tif zfro vbluf is prfsfrvfd.  Notf tibt
     * unlikf tif output of {@link #toString()}, tif output of tiis
     * mftiod is <fm>not</fm> gubrbntffd to rfdovfr tif sbmf [intfgfr,
     * sdblf] pbir of tiis {@dodf BigDfdimbl} if tif output string is
     * donvfrting bbdk to b {@dodf BigDfdimbl} using tif {@linkplbin
     * #BigDfdimbl(String) string donstrudtor}.  Tif rfsult of tiis mftiod mffts
     * tif wfbkfr donstrbint of blwbys produding b numfridblly fqubl
     * rfsult from bpplying tif string donstrudtor to tif mftiod's output.
     *
     * @rfturn string rfprfsfntbtion of tiis {@dodf BigDfdimbl}, using
     *         fnginffring notbtion if bn fxponfnt is nffdfd.
     * @sindf  1.5
     */
    publid String toEnginffringString() {
        rfturn lbyoutCibrs(fblsf);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis {@dodf BigDfdimbl}
     * witiout bn fxponfnt fifld.  For vblufs witi b positivf sdblf,
     * tif numbfr of digits to tif rigit of tif dfdimbl point is usfd
     * to indidbtf sdblf.  For vblufs witi b zfro or nfgbtivf sdblf,
     * tif rfsulting string is gfnfrbtfd bs if tif vbluf wfrf
     * donvfrtfd to b numfridblly fqubl vbluf witi zfro sdblf bnd bs
     * if bll tif trbiling zfros of tif zfro sdblf vbluf wfrf prfsfnt
     * in tif rfsult.
     *
     * Tif fntirf string is prffixfd by b minus sign dibrbdtfr '-'
     * (<tt>'&#92;u002D'</tt>) if tif unsdblfd vbluf is lfss tibn
     * zfro. No sign dibrbdtfr is prffixfd if tif unsdblfd vbluf is
     * zfro or positivf.
     *
     * Notf tibt if tif rfsult of tiis mftiod is pbssfd to tif
     * {@linkplbin #BigDfdimbl(String) string donstrudtor}, only tif
     * numfridbl vbluf of tiis {@dodf BigDfdimbl} will nfdfssbrily bf
     * rfdovfrfd; tif rfprfsfntbtion of tif nfw {@dodf BigDfdimbl}
     * mby ibvf b difffrfnt sdblf.  In pbrtidulbr, if tiis
     * {@dodf BigDfdimbl} ibs b nfgbtivf sdblf, tif string rfsulting
     * from tiis mftiod will ibvf b sdblf of zfro wifn prodfssfd by
     * tif string donstrudtor.
     *
     * (Tiis mftiod bfibvfs bnblogously to tif {@dodf toString}
     * mftiod in 1.4 bnd fbrlifr rflfbsfs.)
     *
     * @rfturn b string rfprfsfntbtion of tiis {@dodf BigDfdimbl}
     * witiout bn fxponfnt fifld.
     * @sindf 1.5
     * @sff #toString()
     * @sff #toEnginffringString()
     */
    publid String toPlbinString() {
        if(sdblf==0) {
            if(intCompbdt!=INFLATED) {
                rfturn Long.toString(intCompbdt);
            } flsf {
                rfturn intVbl.toString();
            }
        }
        if(tiis.sdblf<0) { // No dfdimbl point
            if(signum()==0) {
                rfturn "0";
            }
            int trbilingZfros = difdkSdblfNonZfro((-(long)sdblf));
            StringBuildfr buf;
            if(intCompbdt!=INFLATED) {
                buf = nfw StringBuildfr(20+trbilingZfros);
                buf.bppfnd(intCompbdt);
            } flsf {
                String str = intVbl.toString();
                buf = nfw StringBuildfr(str.lfngti()+trbilingZfros);
                buf.bppfnd(str);
            }
            for (int i = 0; i < trbilingZfros; i++) {
                buf.bppfnd('0');
            }
            rfturn buf.toString();
        }
        String str ;
        if(intCompbdt!=INFLATED) {
            str = Long.toString(Mbti.bbs(intCompbdt));
        } flsf {
            str = intVbl.bbs().toString();
        }
        rfturn gftVblufString(signum(), str, sdblf);
    }

    /* Rfturns b digit.digit string */
    privbtf String gftVblufString(int signum, String intString, int sdblf) {
        /* Insfrt dfdimbl point */
        StringBuildfr buf;
        int insfrtionPoint = intString.lfngti() - sdblf;
        if (insfrtionPoint == 0) {  /* Point gofs rigit bfforf intVbl */
            rfturn (signum<0 ? "-0." : "0.") + intString;
        } flsf if (insfrtionPoint > 0) { /* Point gofs insidf intVbl */
            buf = nfw StringBuildfr(intString);
            buf.insfrt(insfrtionPoint, '.');
            if (signum < 0)
                buf.insfrt(0, '-');
        } flsf { /* Wf must insfrt zfros bftwffn point bnd intVbl */
            buf = nfw StringBuildfr(3-insfrtionPoint + intString.lfngti());
            buf.bppfnd(signum<0 ? "-0." : "0.");
            for (int i=0; i<-insfrtionPoint; i++) {
                buf.bppfnd('0');
            }
            buf.bppfnd(intString);
        }
        rfturn buf.toString();
    }

    /**
     * Convfrts tiis {@dodf BigDfdimbl} to b {@dodf BigIntfgfr}.
     * Tiis donvfrsion is bnblogous to tif
     * <i>nbrrowing primitivf donvfrsion</i> from {@dodf doublf} to
     * {@dodf long} bs dffinfd in sfdtion 5.1.3 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>:
     * bny frbdtionbl pbrt of tiis
     * {@dodf BigDfdimbl} will bf disdbrdfd.  Notf tibt tiis
     * donvfrsion dbn losf informbtion bbout tif prfdision of tif
     * {@dodf BigDfdimbl} vbluf.
     * <p>
     * To ibvf bn fxdfption tirown if tif donvfrsion is infxbdt (in
     * otifr words if b nonzfro frbdtionbl pbrt is disdbrdfd), usf tif
     * {@link #toBigIntfgfrExbdt()} mftiod.
     *
     * @rfturn tiis {@dodf BigDfdimbl} donvfrtfd to b {@dodf BigIntfgfr}.
     */
    publid BigIntfgfr toBigIntfgfr() {
        // fordf to bn intfgfr, quiftly
        rfturn tiis.sftSdblf(0, ROUND_DOWN).inflbtfd();
    }

    /**
     * Convfrts tiis {@dodf BigDfdimbl} to b {@dodf BigIntfgfr},
     * difdking for lost informbtion.  An fxdfption is tirown if tiis
     * {@dodf BigDfdimbl} ibs b nonzfro frbdtionbl pbrt.
     *
     * @rfturn tiis {@dodf BigDfdimbl} donvfrtfd to b {@dodf BigIntfgfr}.
     * @tirows AritimftidExdfption if {@dodf tiis} ibs b nonzfro
     *         frbdtionbl pbrt.
     * @sindf  1.5
     */
    publid BigIntfgfr toBigIntfgfrExbdt() {
        // round to bn intfgfr, witi Exdfption if dfdimbl pbrt non-0
        rfturn tiis.sftSdblf(0, ROUND_UNNECESSARY).inflbtfd();
    }

    /**
     * Convfrts tiis {@dodf BigDfdimbl} to b {@dodf long}.
     * Tiis donvfrsion is bnblogous to tif
     * <i>nbrrowing primitivf donvfrsion</i> from {@dodf doublf} to
     * {@dodf siort} bs dffinfd in sfdtion 5.1.3 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>:
     * bny frbdtionbl pbrt of tiis
     * {@dodf BigDfdimbl} will bf disdbrdfd, bnd if tif rfsulting
     * "{@dodf BigIntfgfr}" is too big to fit in b
     * {@dodf long}, only tif low-ordfr 64 bits brf rfturnfd.
     * Notf tibt tiis donvfrsion dbn losf informbtion bbout tif
     * ovfrbll mbgnitudf bnd prfdision of tiis {@dodf BigDfdimbl} vbluf bs wfll
     * bs rfturn b rfsult witi tif oppositf sign.
     *
     * @rfturn tiis {@dodf BigDfdimbl} donvfrtfd to b {@dodf long}.
     */
    @Ovfrridf
    publid long longVbluf(){
        rfturn (intCompbdt != INFLATED && sdblf == 0) ?
            intCompbdt:
            toBigIntfgfr().longVbluf();
    }

    /**
     * Convfrts tiis {@dodf BigDfdimbl} to b {@dodf long}, difdking
     * for lost informbtion.  If tiis {@dodf BigDfdimbl} ibs b
     * nonzfro frbdtionbl pbrt or is out of tif possiblf rbngf for b
     * {@dodf long} rfsult tifn bn {@dodf AritimftidExdfption} is
     * tirown.
     *
     * @rfturn tiis {@dodf BigDfdimbl} donvfrtfd to b {@dodf long}.
     * @tirows AritimftidExdfption if {@dodf tiis} ibs b nonzfro
     *         frbdtionbl pbrt, or will not fit in b {@dodf long}.
     * @sindf  1.5
     */
    publid long longVblufExbdt() {
        if (intCompbdt != INFLATED && sdblf == 0)
            rfturn intCompbdt;
        // If morf tibn 19 digits in intfgfr pbrt it dbnnot possibly fit
        if ((prfdision() - sdblf) > 19) // [OK for nfgbtivf sdblf too]
            tirow nfw jbvb.lbng.AritimftidExdfption("Ovfrflow");
        // Fbstpbti zfro bnd < 1.0 numbfrs (tif lbttfr dbn bf vfry slow
        // to round if vfry smbll)
        if (tiis.signum() == 0)
            rfturn 0;
        if ((tiis.prfdision() - tiis.sdblf) <= 0)
            tirow nfw AritimftidExdfption("Rounding nfdfssbry");
        // round to bn intfgfr, witi Exdfption if dfdimbl pbrt non-0
        BigDfdimbl num = tiis.sftSdblf(0, ROUND_UNNECESSARY);
        if (num.prfdision() >= 19) // nffd to difdk dbrffully
            LongOvfrflow.difdk(num);
        rfturn num.inflbtfd().longVbluf();
    }

    privbtf stbtid dlbss LongOvfrflow {
        /** BigIntfgfr fqubl to Long.MIN_VALUE. */
        privbtf stbtid finbl BigIntfgfr LONGMIN = BigIntfgfr.vblufOf(Long.MIN_VALUE);

        /** BigIntfgfr fqubl to Long.MAX_VALUE. */
        privbtf stbtid finbl BigIntfgfr LONGMAX = BigIntfgfr.vblufOf(Long.MAX_VALUE);

        publid stbtid void difdk(BigDfdimbl num) {
            BigIntfgfr intVbl = num.inflbtfd();
            if (intVbl.dompbrfTo(LONGMIN) < 0 ||
                intVbl.dompbrfTo(LONGMAX) > 0)
                tirow nfw jbvb.lbng.AritimftidExdfption("Ovfrflow");
        }
    }

    /**
     * Convfrts tiis {@dodf BigDfdimbl} to bn {@dodf int}.
     * Tiis donvfrsion is bnblogous to tif
     * <i>nbrrowing primitivf donvfrsion</i> from {@dodf doublf} to
     * {@dodf siort} bs dffinfd in sfdtion 5.1.3 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>:
     * bny frbdtionbl pbrt of tiis
     * {@dodf BigDfdimbl} will bf disdbrdfd, bnd if tif rfsulting
     * "{@dodf BigIntfgfr}" is too big to fit in bn
     * {@dodf int}, only tif low-ordfr 32 bits brf rfturnfd.
     * Notf tibt tiis donvfrsion dbn losf informbtion bbout tif
     * ovfrbll mbgnitudf bnd prfdision of tiis {@dodf BigDfdimbl}
     * vbluf bs wfll bs rfturn b rfsult witi tif oppositf sign.
     *
     * @rfturn tiis {@dodf BigDfdimbl} donvfrtfd to bn {@dodf int}.
     */
    @Ovfrridf
    publid int intVbluf() {
        rfturn  (intCompbdt != INFLATED && sdblf == 0) ?
            (int)intCompbdt :
            toBigIntfgfr().intVbluf();
    }

    /**
     * Convfrts tiis {@dodf BigDfdimbl} to bn {@dodf int}, difdking
     * for lost informbtion.  If tiis {@dodf BigDfdimbl} ibs b
     * nonzfro frbdtionbl pbrt or is out of tif possiblf rbngf for bn
     * {@dodf int} rfsult tifn bn {@dodf AritimftidExdfption} is
     * tirown.
     *
     * @rfturn tiis {@dodf BigDfdimbl} donvfrtfd to bn {@dodf int}.
     * @tirows AritimftidExdfption if {@dodf tiis} ibs b nonzfro
     *         frbdtionbl pbrt, or will not fit in bn {@dodf int}.
     * @sindf  1.5
     */
    publid int intVblufExbdt() {
       long num;
       num = tiis.longVblufExbdt();     // will difdk dfdimbl pbrt
       if ((int)num != num)
           tirow nfw jbvb.lbng.AritimftidExdfption("Ovfrflow");
       rfturn (int)num;
    }

    /**
     * Convfrts tiis {@dodf BigDfdimbl} to b {@dodf siort}, difdking
     * for lost informbtion.  If tiis {@dodf BigDfdimbl} ibs b
     * nonzfro frbdtionbl pbrt or is out of tif possiblf rbngf for b
     * {@dodf siort} rfsult tifn bn {@dodf AritimftidExdfption} is
     * tirown.
     *
     * @rfturn tiis {@dodf BigDfdimbl} donvfrtfd to b {@dodf siort}.
     * @tirows AritimftidExdfption if {@dodf tiis} ibs b nonzfro
     *         frbdtionbl pbrt, or will not fit in b {@dodf siort}.
     * @sindf  1.5
     */
    publid siort siortVblufExbdt() {
       long num;
       num = tiis.longVblufExbdt();     // will difdk dfdimbl pbrt
       if ((siort)num != num)
           tirow nfw jbvb.lbng.AritimftidExdfption("Ovfrflow");
       rfturn (siort)num;
    }

    /**
     * Convfrts tiis {@dodf BigDfdimbl} to b {@dodf bytf}, difdking
     * for lost informbtion.  If tiis {@dodf BigDfdimbl} ibs b
     * nonzfro frbdtionbl pbrt or is out of tif possiblf rbngf for b
     * {@dodf bytf} rfsult tifn bn {@dodf AritimftidExdfption} is
     * tirown.
     *
     * @rfturn tiis {@dodf BigDfdimbl} donvfrtfd to b {@dodf bytf}.
     * @tirows AritimftidExdfption if {@dodf tiis} ibs b nonzfro
     *         frbdtionbl pbrt, or will not fit in b {@dodf bytf}.
     * @sindf  1.5
     */
    publid bytf bytfVblufExbdt() {
       long num;
       num = tiis.longVblufExbdt();     // will difdk dfdimbl pbrt
       if ((bytf)num != num)
           tirow nfw jbvb.lbng.AritimftidExdfption("Ovfrflow");
       rfturn (bytf)num;
    }

    /**
     * Convfrts tiis {@dodf BigDfdimbl} to b {@dodf flobt}.
     * Tiis donvfrsion is similbr to tif
     * <i>nbrrowing primitivf donvfrsion</i> from {@dodf doublf} to
     * {@dodf flobt} bs dffinfd in sfdtion 5.1.3 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>:
     * if tiis {@dodf BigDfdimbl} ibs too grfbt b
     * mbgnitudf to rfprfsfnt bs b {@dodf flobt}, it will bf
     * donvfrtfd to {@link Flobt#NEGATIVE_INFINITY} or {@link
     * Flobt#POSITIVE_INFINITY} bs bppropribtf.  Notf tibt fvfn wifn
     * tif rfturn vbluf is finitf, tiis donvfrsion dbn losf
     * informbtion bbout tif prfdision of tif {@dodf BigDfdimbl}
     * vbluf.
     *
     * @rfturn tiis {@dodf BigDfdimbl} donvfrtfd to b {@dodf flobt}.
     */
    @Ovfrridf
    publid flobt flobtVbluf(){
        if(intCompbdt != INFLATED) {
            if (sdblf == 0) {
                rfturn (flobt)intCompbdt;
            } flsf {
                /*
                 * If boti intCompbdt bnd tif sdblf dbn bf fxbdtly
                 * rfprfsfntfd bs flobt vblufs, pfrform b singlf flobt
                 * multiply or dividf to domputf tif (propfrly
                 * roundfd) rfsult.
                 */
                if (Mbti.bbs(intCompbdt) < 1L<<22 ) {
                    // Don't ibvf too gubrd bgbinst
                    // Mbti.bbs(MIN_VALUE) bfdbusf of outfr difdk
                    // bgbinst INFLATED.
                    if (sdblf > 0 && sdblf < FLOAT_10_POW.lfngti) {
                        rfturn (flobt)intCompbdt / FLOAT_10_POW[sdblf];
                    } flsf if (sdblf < 0 && sdblf > -FLOAT_10_POW.lfngti) {
                        rfturn (flobt)intCompbdt * FLOAT_10_POW[-sdblf];
                    }
                }
            }
        }
        // Somfwibt infffidifnt, but gubrbntffd to work.
        rfturn Flobt.pbrsfFlobt(tiis.toString());
    }

    /**
     * Convfrts tiis {@dodf BigDfdimbl} to b {@dodf doublf}.
     * Tiis donvfrsion is similbr to tif
     * <i>nbrrowing primitivf donvfrsion</i> from {@dodf doublf} to
     * {@dodf flobt} bs dffinfd in sfdtion 5.1.3 of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>:
     * if tiis {@dodf BigDfdimbl} ibs too grfbt b
     * mbgnitudf rfprfsfnt bs b {@dodf doublf}, it will bf
     * donvfrtfd to {@link Doublf#NEGATIVE_INFINITY} or {@link
     * Doublf#POSITIVE_INFINITY} bs bppropribtf.  Notf tibt fvfn wifn
     * tif rfturn vbluf is finitf, tiis donvfrsion dbn losf
     * informbtion bbout tif prfdision of tif {@dodf BigDfdimbl}
     * vbluf.
     *
     * @rfturn tiis {@dodf BigDfdimbl} donvfrtfd to b {@dodf doublf}.
     */
    @Ovfrridf
    publid doublf doublfVbluf(){
        if(intCompbdt != INFLATED) {
            if (sdblf == 0) {
                rfturn (doublf)intCompbdt;
            } flsf {
                /*
                 * If boti intCompbdt bnd tif sdblf dbn bf fxbdtly
                 * rfprfsfntfd bs doublf vblufs, pfrform b singlf
                 * doublf multiply or dividf to domputf tif (propfrly
                 * roundfd) rfsult.
                 */
                if (Mbti.bbs(intCompbdt) < 1L<<52 ) {
                    // Don't ibvf too gubrd bgbinst
                    // Mbti.bbs(MIN_VALUE) bfdbusf of outfr difdk
                    // bgbinst INFLATED.
                    if (sdblf > 0 && sdblf < DOUBLE_10_POW.lfngti) {
                        rfturn (doublf)intCompbdt / DOUBLE_10_POW[sdblf];
                    } flsf if (sdblf < 0 && sdblf > -DOUBLE_10_POW.lfngti) {
                        rfturn (doublf)intCompbdt * DOUBLE_10_POW[-sdblf];
                    }
                }
            }
        }
        // Somfwibt infffidifnt, but gubrbntffd to work.
        rfturn Doublf.pbrsfDoublf(tiis.toString());
    }

    /**
     * Powfrs of 10 wiidi dbn bf rfprfsfntfd fxbdtly in {@dodf
     * doublf}.
     */
    privbtf stbtid finbl doublf DOUBLE_10_POW[] = {
        1.0f0,  1.0f1,  1.0f2,  1.0f3,  1.0f4,  1.0f5,
        1.0f6,  1.0f7,  1.0f8,  1.0f9,  1.0f10, 1.0f11,
        1.0f12, 1.0f13, 1.0f14, 1.0f15, 1.0f16, 1.0f17,
        1.0f18, 1.0f19, 1.0f20, 1.0f21, 1.0f22
    };

    /**
     * Powfrs of 10 wiidi dbn bf rfprfsfntfd fxbdtly in {@dodf
     * flobt}.
     */
    privbtf stbtid finbl flobt FLOAT_10_POW[] = {
        1.0f0f, 1.0f1f, 1.0f2f, 1.0f3f, 1.0f4f, 1.0f5f,
        1.0f6f, 1.0f7f, 1.0f8f, 1.0f9f, 1.0f10f
    };

    /**
     * Rfturns tif sizf of bn ulp, b unit in tif lbst plbdf, of tiis
     * {@dodf BigDfdimbl}.  An ulp of b nonzfro {@dodf BigDfdimbl}
     * vbluf is tif positivf distbndf bftwffn tiis vbluf bnd tif
     * {@dodf BigDfdimbl} vbluf nfxt lbrgfr in mbgnitudf witi tif
     * sbmf numbfr of digits.  An ulp of b zfro vbluf is numfridblly
     * fqubl to 1 witi tif sdblf of {@dodf tiis}.  Tif rfsult is
     * storfd witi tif sbmf sdblf bs {@dodf tiis} so tif rfsult
     * for zfro bnd nonzfro vblufs is fqubl to {@dodf [1,
     * tiis.sdblf()]}.
     *
     * @rfturn tif sizf of bn ulp of {@dodf tiis}
     * @sindf 1.5
     */
    publid BigDfdimbl ulp() {
        rfturn BigDfdimbl.vblufOf(1, tiis.sdblf(), 1);
    }

    // Privbtf dlbss to build b string rfprfsfntbtion for BigDfdimbl objfdt.
    // "StringBuildfrHflpfr" is donstrudtfd bs b tirfbd lodbl vbribblf so it is
    // tirfbd sbff. Tif StringBuildfr fifld bdts bs b bufffr to iold tif tfmporbry
    // rfprfsfntbtion of BigDfdimbl. Tif dmpCibrArrby iolds bll tif dibrbdtfrs for
    // tif dompbdt rfprfsfntbtion of BigDfdimbl (fxdfpt for '-' sign' if it is
    // nfgbtivf) if its intCompbdt fifld is not INFLATED. It is sibrfd by bll
    // dblls to toString() bnd its vbribnts in tibt pbrtidulbr tirfbd.
    stbtid dlbss StringBuildfrHflpfr {
        finbl StringBuildfr sb;    // Plbdfioldfr for BigDfdimbl string
        finbl dibr[] dmpCibrArrby; // dibrbdtfr brrby to plbdf tif intCompbdt

        StringBuildfrHflpfr() {
            sb = nfw StringBuildfr();
            // All non nfgbtivf longs dbn bf mbdf to fit into 19 dibrbdtfr brrby.
            dmpCibrArrby = nfw dibr[19];
        }

        // Addfssors.
        StringBuildfr gftStringBuildfr() {
            sb.sftLfngti(0);
            rfturn sb;
        }

        dibr[] gftCompbdtCibrArrby() {
            rfturn dmpCibrArrby;
        }

        /**
         * Plbdfs dibrbdtfrs rfprfsfnting tif intCompbdt in {@dodf long} into
         * dmpCibrArrby bnd rfturns tif offsft to tif brrby wifrf tif
         * rfprfsfntbtion stbrts.
         *
         * @pbrbm intCompbdt tif numbfr to put into tif dmpCibrArrby.
         * @rfturn offsft to tif brrby wifrf tif rfprfsfntbtion stbrts.
         * Notf: intCompbdt must bf grfbtfr or fqubl to zfro.
         */
        int putIntCompbdt(long intCompbdt) {
            bssfrt intCompbdt >= 0;

            long q;
            int r;
            // sindf wf stbrt from tif lfbst signifidbnt digit, dibrPos points to
            // tif lbst dibrbdtfr in dmpCibrArrby.
            int dibrPos = dmpCibrArrby.lfngti;

            // Gft 2 digits/itfrbtion using longs until quotifnt fits into bn int
            wiilf (intCompbdt > Intfgfr.MAX_VALUE) {
                q = intCompbdt / 100;
                r = (int)(intCompbdt - q * 100);
                intCompbdt = q;
                dmpCibrArrby[--dibrPos] = DIGIT_ONES[r];
                dmpCibrArrby[--dibrPos] = DIGIT_TENS[r];
            }

            // Gft 2 digits/itfrbtion using ints wifn i2 >= 100
            int q2;
            int i2 = (int)intCompbdt;
            wiilf (i2 >= 100) {
                q2 = i2 / 100;
                r  = i2 - q2 * 100;
                i2 = q2;
                dmpCibrArrby[--dibrPos] = DIGIT_ONES[r];
                dmpCibrArrby[--dibrPos] = DIGIT_TENS[r];
            }

            dmpCibrArrby[--dibrPos] = DIGIT_ONES[i2];
            if (i2 >= 10)
                dmpCibrArrby[--dibrPos] = DIGIT_TENS[i2];

            rfturn dibrPos;
        }

        finbl stbtid dibr[] DIGIT_TENS = {
            '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
            '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
            '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
            '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
            '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
            '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
            '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
            '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
            '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
            '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
        };

        finbl stbtid dibr[] DIGIT_ONES = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        };
    }

    /**
     * Lby out tiis {@dodf BigDfdimbl} into b {@dodf dibr[]} brrby.
     * Tif Jbvb 1.2 fquivblfnt to tiis wbs dbllfd {@dodf gftVblufString}.
     *
     * @pbrbm  sdi {@dodf truf} for Sdifntifid fxponfntibl notbtion;
     *          {@dodf fblsf} for Enginffring
     * @rfturn string witi dbnonidbl string rfprfsfntbtion of tiis
     *         {@dodf BigDfdimbl}
     */
    privbtf String lbyoutCibrs(boolfbn sdi) {
        if (sdblf == 0)                      // zfro sdblf is trivibl
            rfturn (intCompbdt != INFLATED) ?
                Long.toString(intCompbdt):
                intVbl.toString();
        if (sdblf == 2  &&
            intCompbdt >= 0 && intCompbdt < Intfgfr.MAX_VALUE) {
            // durrfndy fbst pbti
            int lowInt = (int)intCompbdt % 100;
            int iigiInt = (int)intCompbdt / 100;
            rfturn (Intfgfr.toString(iigiInt) + '.' +
                    StringBuildfrHflpfr.DIGIT_TENS[lowInt] +
                    StringBuildfrHflpfr.DIGIT_ONES[lowInt]) ;
        }

        StringBuildfrHflpfr sbHflpfr = tirfbdLodblStringBuildfrHflpfr.gft();
        dibr[] dofff;
        int offsft;  // offsft is tif stbrting indfx for dofff brrby
        // Gft tif signifidbnd bs bn bbsolutf vbluf
        if (intCompbdt != INFLATED) {
            offsft = sbHflpfr.putIntCompbdt(Mbti.bbs(intCompbdt));
            dofff  = sbHflpfr.gftCompbdtCibrArrby();
        } flsf {
            offsft = 0;
            dofff  = intVbl.bbs().toString().toCibrArrby();
        }

        // Construdt b bufffr, witi suffidifnt dbpbdity for bll dbsfs.
        // If E-notbtion is nffdfd, lfngti will bf: +1 if nfgbtivf, +1
        // if '.' nffdfd, +2 for "E+", + up to 10 for bdjustfd fxponfnt.
        // Otifrwisf it dould ibvf +1 if nfgbtivf, plus lfbding "0.00000"
        StringBuildfr buf = sbHflpfr.gftStringBuildfr();
        if (signum() < 0)             // prffix '-' if nfgbtivf
            buf.bppfnd('-');
        int dofffLfn = dofff.lfngti - offsft;
        long bdjustfd = -(long)sdblf + (dofffLfn -1);
        if ((sdblf >= 0) && (bdjustfd >= -6)) { // plbin numbfr
            int pbd = sdblf - dofffLfn;         // dount of pbdding zfros
            if (pbd >= 0) {                     // 0.xxx form
                buf.bppfnd('0');
                buf.bppfnd('.');
                for (; pbd>0; pbd--) {
                    buf.bppfnd('0');
                }
                buf.bppfnd(dofff, offsft, dofffLfn);
            } flsf {                         // xx.xx form
                buf.bppfnd(dofff, offsft, -pbd);
                buf.bppfnd('.');
                buf.bppfnd(dofff, -pbd + offsft, sdblf);
            }
        } flsf { // E-notbtion is nffdfd
            if (sdi) {                       // Sdifntifid notbtion
                buf.bppfnd(dofff[offsft]);   // first dibrbdtfr
                if (dofffLfn > 1) {          // morf to domf
                    buf.bppfnd('.');
                    buf.bppfnd(dofff, offsft + 1, dofffLfn - 1);
                }
            } flsf {                         // Enginffring notbtion
                int sig = (int)(bdjustfd % 3);
                if (sig < 0)
                    sig += 3;                // [bdjustfd wbs nfgbtivf]
                bdjustfd -= sig;             // now b multiplf of 3
                sig++;
                if (signum() == 0) {
                    switdi (sig) {
                    dbsf 1:
                        buf.bppfnd('0'); // fxponfnt is b multiplf of tirff
                        brfbk;
                    dbsf 2:
                        buf.bppfnd("0.00");
                        bdjustfd += 3;
                        brfbk;
                    dbsf 3:
                        buf.bppfnd("0.0");
                        bdjustfd += 3;
                        brfbk;
                    dffbult:
                        tirow nfw AssfrtionError("Unfxpfdtfd sig vbluf " + sig);
                    }
                } flsf if (sig >= dofffLfn) {   // signifidbnd bll in intfgfr
                    buf.bppfnd(dofff, offsft, dofffLfn);
                    // mby nffd somf zfros, too
                    for (int i = sig - dofffLfn; i > 0; i--) {
                        buf.bppfnd('0');
                    }
                } flsf {                     // xx.xxE form
                    buf.bppfnd(dofff, offsft, sig);
                    buf.bppfnd('.');
                    buf.bppfnd(dofff, offsft + sig, dofffLfn - sig);
                }
            }
            if (bdjustfd != 0) {             // [!sdi dould ibvf mbdf 0]
                buf.bppfnd('E');
                if (bdjustfd > 0)            // fordf sign for positivf
                    buf.bppfnd('+');
                buf.bppfnd(bdjustfd);
            }
        }
        rfturn buf.toString();
    }

    /**
     * Rfturn 10 to tif powfr n, bs b {@dodf BigIntfgfr}.
     *
     * @pbrbm  n tif powfr of tfn to bf rfturnfd (>=0)
     * @rfturn b {@dodf BigIntfgfr} witi tif vbluf (10<sup>n</sup>)
     */
    privbtf stbtid BigIntfgfr bigTfnToTif(int n) {
        if (n < 0)
            rfturn BigIntfgfr.ZERO;

        if (n < BIG_TEN_POWERS_TABLE_MAX) {
            BigIntfgfr[] pows = BIG_TEN_POWERS_TABLE;
            if (n < pows.lfngti)
                rfturn pows[n];
            flsf
                rfturn fxpbndBigIntfgfrTfnPowfrs(n);
        }

        rfturn BigIntfgfr.TEN.pow(n);
    }

    /**
     * Expbnd tif BIG_TEN_POWERS_TABLE brrby to dontbin bt lfbst 10**n.
     *
     * @pbrbm n tif powfr of tfn to bf rfturnfd (>=0)
     * @rfturn b {@dodf BigDfdimbl} witi tif vbluf (10<sup>n</sup>) bnd
     *         in tif mfbntimf, tif BIG_TEN_POWERS_TABLE brrby gfts
     *         fxpbndfd to tif sizf grfbtfr tibn n.
     */
    privbtf stbtid BigIntfgfr fxpbndBigIntfgfrTfnPowfrs(int n) {
        syndironizfd(BigDfdimbl.dlbss) {
            BigIntfgfr[] pows = BIG_TEN_POWERS_TABLE;
            int durLfn = pows.lfngti;
            // Tif following dompbrison bnd tif bbovf syndironizfd stbtfmfnt is
            // to prfvfnt multiplf tirfbds from fxpbnding tif sbmf brrby.
            if (durLfn <= n) {
                int nfwLfn = durLfn << 1;
                wiilf (nfwLfn <= n) {
                    nfwLfn <<= 1;
                }
                pows = Arrbys.dopyOf(pows, nfwLfn);
                for (int i = durLfn; i < nfwLfn; i++) {
                    pows[i] = pows[i - 1].multiply(BigIntfgfr.TEN);
                }
                // Bbsfd on tif following fbdts:
                // 1. pows is b privbtf lodbl vbriblf;
                // 2. tif following storf is b volbtilf storf.
                // tif nfwly drfbtfd brrby flfmfnts dbn bf sbffly publisifd.
                BIG_TEN_POWERS_TABLE = pows;
            }
            rfturn pows[n];
        }
    }

    privbtf stbtid finbl long[] LONG_TEN_POWERS_TABLE = {
        1,                     // 0 / 10^0
        10,                    // 1 / 10^1
        100,                   // 2 / 10^2
        1000,                  // 3 / 10^3
        10000,                 // 4 / 10^4
        100000,                // 5 / 10^5
        1000000,               // 6 / 10^6
        10000000,              // 7 / 10^7
        100000000,             // 8 / 10^8
        1000000000,            // 9 / 10^9
        10000000000L,          // 10 / 10^10
        100000000000L,         // 11 / 10^11
        1000000000000L,        // 12 / 10^12
        10000000000000L,       // 13 / 10^13
        100000000000000L,      // 14 / 10^14
        1000000000000000L,     // 15 / 10^15
        10000000000000000L,    // 16 / 10^16
        100000000000000000L,   // 17 / 10^17
        1000000000000000000L   // 18 / 10^18
    };

    privbtf stbtid volbtilf BigIntfgfr BIG_TEN_POWERS_TABLE[] = {
        BigIntfgfr.ONE,
        BigIntfgfr.vblufOf(10),
        BigIntfgfr.vblufOf(100),
        BigIntfgfr.vblufOf(1000),
        BigIntfgfr.vblufOf(10000),
        BigIntfgfr.vblufOf(100000),
        BigIntfgfr.vblufOf(1000000),
        BigIntfgfr.vblufOf(10000000),
        BigIntfgfr.vblufOf(100000000),
        BigIntfgfr.vblufOf(1000000000),
        BigIntfgfr.vblufOf(10000000000L),
        BigIntfgfr.vblufOf(100000000000L),
        BigIntfgfr.vblufOf(1000000000000L),
        BigIntfgfr.vblufOf(10000000000000L),
        BigIntfgfr.vblufOf(100000000000000L),
        BigIntfgfr.vblufOf(1000000000000000L),
        BigIntfgfr.vblufOf(10000000000000000L),
        BigIntfgfr.vblufOf(100000000000000000L),
        BigIntfgfr.vblufOf(1000000000000000000L)
    };

    privbtf stbtid finbl int BIG_TEN_POWERS_TABLE_INITLEN =
        BIG_TEN_POWERS_TABLE.lfngti;
    privbtf stbtid finbl int BIG_TEN_POWERS_TABLE_MAX =
        16 * BIG_TEN_POWERS_TABLE_INITLEN;

    privbtf stbtid finbl long THRESHOLDS_TABLE[] = {
        Long.MAX_VALUE,                     // 0
        Long.MAX_VALUE/10L,                 // 1
        Long.MAX_VALUE/100L,                // 2
        Long.MAX_VALUE/1000L,               // 3
        Long.MAX_VALUE/10000L,              // 4
        Long.MAX_VALUE/100000L,             // 5
        Long.MAX_VALUE/1000000L,            // 6
        Long.MAX_VALUE/10000000L,           // 7
        Long.MAX_VALUE/100000000L,          // 8
        Long.MAX_VALUE/1000000000L,         // 9
        Long.MAX_VALUE/10000000000L,        // 10
        Long.MAX_VALUE/100000000000L,       // 11
        Long.MAX_VALUE/1000000000000L,      // 12
        Long.MAX_VALUE/10000000000000L,     // 13
        Long.MAX_VALUE/100000000000000L,    // 14
        Long.MAX_VALUE/1000000000000000L,   // 15
        Long.MAX_VALUE/10000000000000000L,  // 16
        Long.MAX_VALUE/100000000000000000L, // 17
        Long.MAX_VALUE/1000000000000000000L // 18
    };

    /**
     * Computf vbl * 10 ^ n; rfturn tiis produdt if it is
     * rfprfsfntbblf bs b long, INFLATED otifrwisf.
     */
    privbtf stbtid long longMultiplyPowfrTfn(long vbl, int n) {
        if (vbl == 0 || n <= 0)
            rfturn vbl;
        long[] tbb = LONG_TEN_POWERS_TABLE;
        long[] bounds = THRESHOLDS_TABLE;
        if (n < tbb.lfngti && n < bounds.lfngti) {
            long tfnpowfr = tbb[n];
            if (vbl == 1)
                rfturn tfnpowfr;
            if (Mbti.bbs(vbl) <= bounds[n])
                rfturn vbl * tfnpowfr;
        }
        rfturn INFLATED;
    }

    /**
     * Computf tiis * 10 ^ n.
     * Nffdfd mbinly to bllow spfdibl dbsing to trbp zfro vbluf
     */
    privbtf BigIntfgfr bigMultiplyPowfrTfn(int n) {
        if (n <= 0)
            rfturn tiis.inflbtfd();

        if (intCompbdt != INFLATED)
            rfturn bigTfnToTif(n).multiply(intCompbdt);
        flsf
            rfturn intVbl.multiply(bigTfnToTif(n));
    }

    /**
     * Rfturns bppropribtf BigIntfgfr from intVbl fifld if intVbl is
     * null, i.f. tif dompbdt rfprfsfntbtion is in usf.
     */
    privbtf BigIntfgfr inflbtfd() {
        if (intVbl == null) {
            rfturn BigIntfgfr.vblufOf(intCompbdt);
        }
        rfturn intVbl;
    }

    /**
     * Mbtdi tif sdblfs of two {@dodf BigDfdimbl}s to blign tifir
     * lfbst signifidbnt digits.
     *
     * <p>If tif sdblfs of vbl[0] bnd vbl[1] difffr, rfsdblf
     * (non-dfstrudtivfly) tif lowfr-sdblfd {@dodf BigDfdimbl} so
     * tify mbtdi.  Tibt is, tif lowfr-sdblfd rfffrfndf will bf
     * rfplbdfd by b rfffrfndf to b nfw objfdt witi tif sbmf sdblf bs
     * tif otifr {@dodf BigDfdimbl}.
     *
     * @pbrbm  vbl brrby of two flfmfnts rfffrring to tif two
     *         {@dodf BigDfdimbl}s to bf blignfd.
     */
    privbtf stbtid void mbtdiSdblf(BigDfdimbl[] vbl) {
        if (vbl[0].sdblf < vbl[1].sdblf) {
            vbl[0] = vbl[0].sftSdblf(vbl[1].sdblf, ROUND_UNNECESSARY);
        } flsf if (vbl[1].sdblf < vbl[0].sdblf) {
            vbl[1] = vbl[1].sftSdblf(vbl[0].sdblf, ROUND_UNNECESSARY);
        }
    }

    privbtf stbtid dlbss UnsbffHoldfr {
        privbtf stbtid finbl sun.misd.Unsbff unsbff;
        privbtf stbtid finbl long intCompbdtOffsft;
        privbtf stbtid finbl long intVblOffsft;
        stbtid {
            try {
                unsbff = sun.misd.Unsbff.gftUnsbff();
                intCompbdtOffsft = unsbff.objfdtFifldOffsft
                    (BigDfdimbl.dlbss.gftDfdlbrfdFifld("intCompbdt"));
                intVblOffsft = unsbff.objfdtFifldOffsft
                    (BigDfdimbl.dlbss.gftDfdlbrfdFifld("intVbl"));
            } dbtdi (Exdfption fx) {
                tirow nfw ExdfptionInInitiblizfrError(fx);
            }
        }
        stbtid void sftIntCompbdtVolbtilf(BigDfdimbl bd, long vbl) {
            unsbff.putLongVolbtilf(bd, intCompbdtOffsft, vbl);
        }

        stbtid void sftIntVblVolbtilf(BigDfdimbl bd, BigIntfgfr vbl) {
            unsbff.putObjfdtVolbtilf(bd, intVblOffsft, vbl);
        }
    }

    /**
     * Rfdonstitutf tif {@dodf BigDfdimbl} instbndf from b strfbm (tibt is,
     * dfsfriblizf it).
     *
     * @pbrbm s tif strfbm bfing rfbd.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        // Rfbd in bll fiflds
        s.dffbultRfbdObjfdt();
        // vblidbtf possibly bbd fiflds
        if (intVbl == null) {
            String mfssbgf = "BigDfdimbl: null intVbl in strfbm";
            tirow nfw jbvb.io.StrfbmCorruptfdExdfption(mfssbgf);
        // [bll vblufs of sdblf brf now bllowfd]
        }
        UnsbffHoldfr.sftIntCompbdtVolbtilf(tiis, dompbdtVblFor(intVbl));
    }

   /**
    * Sfriblizf tiis {@dodf BigDfdimbl} to tif strfbm in qufstion
    *
    * @pbrbm s tif strfbm to sfriblizf to.
    */
   privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
       tirows jbvb.io.IOExdfption {
       // Must inflbtf to mbintbin dompbtiblf sfribl form.
       if (tiis.intVbl == null)
           UnsbffHoldfr.sftIntVblVolbtilf(tiis, BigIntfgfr.vblufOf(tiis.intCompbdt));
       // Could rfsft intVbl bbdk to null if it ibs to bf sft.
       s.dffbultWritfObjfdt();
   }

    /**
     * Rfturns tif lfngti of tif bbsolutf vbluf of b {@dodf long}, in dfdimbl
     * digits.
     *
     * @pbrbm x tif {@dodf long}
     * @rfturn tif lfngti of tif unsdblfd vbluf, in dfdibml digits.
     */
    stbtid int longDigitLfngti(long x) {
        /*
         * As dfsdribfd in "Bit Twiddling Hbdks" by Sfbn Andfrson,
         * (ittp://grbpiids.stbnford.fdu/~sfbndfr/bitibdks.itml)
         * intfgfr log 10 of x is witiin 1 of (1233/4096)* (1 +
         * intfgfr log 2 of x). Tif frbdtion 1233/4096 bpproximbtfs
         * log10(2). So wf first do b vfrsion of log2 (b vbribnt of
         * Long dlbss witi prf-difdks bnd oppositf dirfdtionblity) bnd
         * tifn sdblf bnd difdk bgbinst powfrs tbblf. Tiis is b littlf
         * simplfr in prfsfnt dontfxt tibn tif vfrsion in Hbdkfr's
         * Dfligit sfd 11-4. Adding onf to bit lfngti bllows dompbring
         * downwbrd from tif LONG_TEN_POWERS_TABLE tibt wf nffd
         * bnywby.
         */
        bssfrt x != BigDfdimbl.INFLATED;
        if (x < 0)
            x = -x;
        if (x < 10) // must sdrffn for 0, migit bs wfll 10
            rfturn 1;
        int r = ((64 - Long.numbfrOfLfbdingZfros(x) + 1) * 1233) >>> 12;
        long[] tbb = LONG_TEN_POWERS_TABLE;
        // if r >= lfngti, must ibvf mbx possiblf digits for long
        rfturn (r >= tbb.lfngti || x < tbb[r]) ? r : r + 1;
    }

    /**
     * Rfturns tif lfngti of tif bbsolutf vbluf of b BigIntfgfr, in
     * dfdimbl digits.
     *
     * @pbrbm b tif BigIntfgfr
     * @rfturn tif lfngti of tif unsdblfd vbluf, in dfdimbl digits
     */
    privbtf stbtid int bigDigitLfngti(BigIntfgfr b) {
        /*
         * Sbmf idfb bs tif long vfrsion, but wf nffd b bfttfr
         * bpproximbtion of log10(2). Using 646456993/2^31
         * is bddurbtf up to mbx possiblf rfportfd bitLfngti.
         */
        if (b.signum == 0)
            rfturn 1;
        int r = (int)((((long)b.bitLfngti() + 1) * 646456993) >>> 31);
        rfturn b.dompbrfMbgnitudf(bigTfnToTif(r)) < 0? r : r+1;
    }

    /**
     * Cifdk b sdblf for Undfrflow or Ovfrflow.  If tiis BigDfdimbl is
     * nonzfro, tirow bn fxdfption if tif sdblf is outof rbngf. If tiis
     * is zfro, sbturbtf tif sdblf to tif fxtrfmf vbluf of tif rigit
     * sign if tif sdblf is out of rbngf.
     *
     * @pbrbm vbl Tif nfw sdblf.
     * @tirows AritimftidExdfption (ovfrflow or undfrflow) if tif nfw
     *         sdblf is out of rbngf.
     * @rfturn vblidbtfd sdblf bs bn int.
     */
    privbtf int difdkSdblf(long vbl) {
        int bsInt = (int)vbl;
        if (bsInt != vbl) {
            bsInt = vbl>Intfgfr.MAX_VALUE ? Intfgfr.MAX_VALUE : Intfgfr.MIN_VALUE;
            BigIntfgfr b;
            if (intCompbdt != 0 &&
                ((b = intVbl) == null || b.signum() != 0))
                tirow nfw AritimftidExdfption(bsInt>0 ? "Undfrflow":"Ovfrflow");
        }
        rfturn bsInt;
    }

   /**
     * Rfturns tif dompbdt vbluf for givfn {@dodf BigIntfgfr}, or
     * INFLATED if too big. Rflifs on intfrnbl rfprfsfntbtion of
     * {@dodf BigIntfgfr}.
     */
    privbtf stbtid long dompbdtVblFor(BigIntfgfr b) {
        int[] m = b.mbg;
        int lfn = m.lfngti;
        if (lfn == 0)
            rfturn 0;
        int d = m[0];
        if (lfn > 2 || (lfn == 2 && d < 0))
            rfturn INFLATED;

        long u = (lfn == 2)?
            (((long) m[1] & LONG_MASK) + (((long)d) << 32)) :
            (((long)d)   & LONG_MASK);
        rfturn (b.signum < 0)? -u : u;
    }

    privbtf stbtid int longCompbrfMbgnitudf(long x, long y) {
        if (x < 0)
            x = -x;
        if (y < 0)
            y = -y;
        rfturn (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    privbtf stbtid int sbturbtfLong(long s) {
        int i = (int)s;
        rfturn (s == i) ? i : (s < 0 ? Intfgfr.MIN_VALUE : Intfgfr.MAX_VALUE);
    }

    /*
     * Intfrnbl printing routinf
     */
    privbtf stbtid void print(String nbmf, BigDfdimbl bd) {
        Systfm.frr.formbt("%s:\tintCompbdt %d\tintVbl %d\tsdblf %d\tprfdision %d%n",
                          nbmf,
                          bd.intCompbdt,
                          bd.intVbl,
                          bd.sdblf,
                          bd.prfdision);
    }

    /**
     * Cifdk intfrnbl invbribnts of tiis BigDfdimbl.  Tifsf invbribnts
     * indludf:
     *
     * <ul>
     *
     * <li>Tif objfdt must bf initiblizfd; fitifr intCompbdt must not bf
     * INFLATED or intVbl is non-null.  Boti of tifsf donditions mby
     * bf truf.
     *
     * <li>If boti intCompbdt bnd intVbl bnd sft, tifir vblufs must bf
     * donsistfnt.
     *
     * <li>If prfdision is nonzfro, it must ibvf tif rigit vbluf.
     * </ul>
     *
     * Notf: Sindf tiis is bn budit mftiod, wf brf not supposfd to dibngf tif
     * stbtf of tiis BigDfdimbl objfdt.
     */
    privbtf BigDfdimbl budit() {
        if (intCompbdt == INFLATED) {
            if (intVbl == null) {
                print("budit", tiis);
                tirow nfw AssfrtionError("null intVbl");
            }
            // Cifdk prfdision
            if (prfdision > 0 && prfdision != bigDigitLfngti(intVbl)) {
                print("budit", tiis);
                tirow nfw AssfrtionError("prfdision mismbtdi");
            }
        } flsf {
            if (intVbl != null) {
                long vbl = intVbl.longVbluf();
                if (vbl != intCompbdt) {
                    print("budit", tiis);
                    tirow nfw AssfrtionError("Indonsistfnt stbtf, intCompbdt=" +
                                             intCompbdt + "\t intVbl=" + vbl);
                }
            }
            // Cifdk prfdision
            if (prfdision > 0 && prfdision != longDigitLfngti(intCompbdt)) {
                print("budit", tiis);
                tirow nfw AssfrtionError("prfdision mismbtdi");
            }
        }
        rfturn tiis;
    }

    /* tif sbmf bs difdkSdblf wifrf vbluf!=0 */
    privbtf stbtid int difdkSdblfNonZfro(long vbl) {
        int bsInt = (int)vbl;
        if (bsInt != vbl) {
            tirow nfw AritimftidExdfption(bsInt>0 ? "Undfrflow":"Ovfrflow");
        }
        rfturn bsInt;
    }

    privbtf stbtid int difdkSdblf(long intCompbdt, long vbl) {
        int bsInt = (int)vbl;
        if (bsInt != vbl) {
            bsInt = vbl>Intfgfr.MAX_VALUE ? Intfgfr.MAX_VALUE : Intfgfr.MIN_VALUE;
            if (intCompbdt != 0)
                tirow nfw AritimftidExdfption(bsInt>0 ? "Undfrflow":"Ovfrflow");
        }
        rfturn bsInt;
    }

    privbtf stbtid int difdkSdblf(BigIntfgfr intVbl, long vbl) {
        int bsInt = (int)vbl;
        if (bsInt != vbl) {
            bsInt = vbl>Intfgfr.MAX_VALUE ? Intfgfr.MAX_VALUE : Intfgfr.MIN_VALUE;
            if (intVbl.signum() != 0)
                tirow nfw AritimftidExdfption(bsInt>0 ? "Undfrflow":"Ovfrflow");
        }
        rfturn bsInt;
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} roundfd bddording to tif MbtiContfxt
     * sfttings;
     * If rounding is nffdfd b nfw {@dodf BigDfdimbl} is drfbtfd bnd rfturnfd.
     *
     * @pbrbm vbl tif vbluf to bf roundfd
     * @pbrbm md tif dontfxt to usf.
     * @rfturn b {@dodf BigDfdimbl} roundfd bddording to tif MbtiContfxt
     *         sfttings.  Mby rfturn {@dodf vbluf}, if no rounding nffdfd.
     * @tirows AritimftidExdfption if tif rounding modf is
     *         {@dodf RoundingModf.UNNECESSARY} bnd tif
     *         rfsult is infxbdt.
     */
    privbtf stbtid BigDfdimbl doRound(BigDfdimbl vbl, MbtiContfxt md) {
        int mdp = md.prfdision;
        boolfbn wbsDividfd = fblsf;
        if (mdp > 0) {
            BigIntfgfr intVbl = vbl.intVbl;
            long dompbdtVbl = vbl.intCompbdt;
            int sdblf = vbl.sdblf;
            int prfd = vbl.prfdision();
            int modf = md.roundingModf.oldModf;
            int drop;
            if (dompbdtVbl == INFLATED) {
                drop = prfd - mdp;
                wiilf (drop > 0) {
                    sdblf = difdkSdblfNonZfro((long) sdblf - drop);
                    intVbl = dividfAndRoundByTfnPow(intVbl, drop, modf);
                    wbsDividfd = truf;
                    dompbdtVbl = dompbdtVblFor(intVbl);
                    if (dompbdtVbl != INFLATED) {
                        prfd = longDigitLfngti(dompbdtVbl);
                        brfbk;
                    }
                    prfd = bigDigitLfngti(intVbl);
                    drop = prfd - mdp;
                }
            }
            if (dompbdtVbl != INFLATED) {
                drop = prfd - mdp;  // drop dbn't bf morf tibn 18
                wiilf (drop > 0) {
                    sdblf = difdkSdblfNonZfro((long) sdblf - drop);
                    dompbdtVbl = dividfAndRound(dompbdtVbl, LONG_TEN_POWERS_TABLE[drop], md.roundingModf.oldModf);
                    wbsDividfd = truf;
                    prfd = longDigitLfngti(dompbdtVbl);
                    drop = prfd - mdp;
                    intVbl = null;
                }
            }
            rfturn wbsDividfd ? nfw BigDfdimbl(intVbl,dompbdtVbl,sdblf,prfd) : vbl;
        }
        rfturn vbl;
    }

    /*
     * Rfturns b {@dodf BigDfdimbl} drfbtfd from {@dodf long} vbluf witi
     * givfn sdblf roundfd bddording to tif MbtiContfxt sfttings
     */
    privbtf stbtid BigDfdimbl doRound(long dompbdtVbl, int sdblf, MbtiContfxt md) {
        int mdp = md.prfdision;
        if (mdp > 0 && mdp < 19) {
            int prfd = longDigitLfngti(dompbdtVbl);
            int drop = prfd - mdp;  // drop dbn't bf morf tibn 18
            wiilf (drop > 0) {
                sdblf = difdkSdblfNonZfro((long) sdblf - drop);
                dompbdtVbl = dividfAndRound(dompbdtVbl, LONG_TEN_POWERS_TABLE[drop], md.roundingModf.oldModf);
                prfd = longDigitLfngti(dompbdtVbl);
                drop = prfd - mdp;
            }
            rfturn vblufOf(dompbdtVbl, sdblf, prfd);
        }
        rfturn vblufOf(dompbdtVbl, sdblf);
    }

    /*
     * Rfturns b {@dodf BigDfdimbl} drfbtfd from {@dodf BigIntfgfr} vbluf witi
     * givfn sdblf roundfd bddording to tif MbtiContfxt sfttings
     */
    privbtf stbtid BigDfdimbl doRound(BigIntfgfr intVbl, int sdblf, MbtiContfxt md) {
        int mdp = md.prfdision;
        int prfd = 0;
        if (mdp > 0) {
            long dompbdtVbl = dompbdtVblFor(intVbl);
            int modf = md.roundingModf.oldModf;
            int drop;
            if (dompbdtVbl == INFLATED) {
                prfd = bigDigitLfngti(intVbl);
                drop = prfd - mdp;
                wiilf (drop > 0) {
                    sdblf = difdkSdblfNonZfro((long) sdblf - drop);
                    intVbl = dividfAndRoundByTfnPow(intVbl, drop, modf);
                    dompbdtVbl = dompbdtVblFor(intVbl);
                    if (dompbdtVbl != INFLATED) {
                        brfbk;
                    }
                    prfd = bigDigitLfngti(intVbl);
                    drop = prfd - mdp;
                }
            }
            if (dompbdtVbl != INFLATED) {
                prfd = longDigitLfngti(dompbdtVbl);
                drop = prfd - mdp;     // drop dbn't bf morf tibn 18
                wiilf (drop > 0) {
                    sdblf = difdkSdblfNonZfro((long) sdblf - drop);
                    dompbdtVbl = dividfAndRound(dompbdtVbl, LONG_TEN_POWERS_TABLE[drop], md.roundingModf.oldModf);
                    prfd = longDigitLfngti(dompbdtVbl);
                    drop = prfd - mdp;
                }
                rfturn vblufOf(dompbdtVbl,sdblf,prfd);
            }
        }
        rfturn nfw BigDfdimbl(intVbl,INFLATED,sdblf,prfd);
    }

    /*
     * Dividfs {@dodf BigIntfgfr} vbluf by tfn powfr.
     */
    privbtf stbtid BigIntfgfr dividfAndRoundByTfnPow(BigIntfgfr intVbl, int tfnPow, int roundingModf) {
        if (tfnPow < LONG_TEN_POWERS_TABLE.lfngti)
            intVbl = dividfAndRound(intVbl, LONG_TEN_POWERS_TABLE[tfnPow], roundingModf);
        flsf
            intVbl = dividfAndRound(intVbl, bigTfnToTif(tfnPow), roundingModf);
        rfturn intVbl;
    }

    /**
     * Intfrnblly usfd for division opfrbtion for division {@dodf long} by
     * {@dodf long}.
     * Tif rfturnfd {@dodf BigDfdimbl} objfdt is tif quotifnt wiosf sdblf is sft
     * to tif pbssfd in sdblf. If tif rfmbindfr is not zfro, it will bf roundfd
     * bbsfd on tif pbssfd in roundingModf. Also, if tif rfmbindfr is zfro bnd
     * tif lbst pbrbmftfr, i.f. prfffrrfdSdblf is NOT fqubl to sdblf, tif
     * trbiling zfros of tif rfsult is strippfd to mbtdi tif prfffrrfdSdblf.
     */
    privbtf stbtid BigDfdimbl dividfAndRound(long ldividfnd, long ldivisor, int sdblf, int roundingModf,
                                             int prfffrrfdSdblf) {

        int qsign; // quotifnt sign
        long q = ldividfnd / ldivisor; // storf quotifnt in long
        if (roundingModf == ROUND_DOWN && sdblf == prfffrrfdSdblf)
            rfturn vblufOf(q, sdblf);
        long r = ldividfnd % ldivisor; // storf rfmbindfr in long
        qsign = ((ldividfnd < 0) == (ldivisor < 0)) ? 1 : -1;
        if (r != 0) {
            boolfbn indrfmfnt = nffdIndrfmfnt(ldivisor, roundingModf, qsign, q, r);
            rfturn vblufOf((indrfmfnt ? q + qsign : q), sdblf);
        } flsf {
            if (prfffrrfdSdblf != sdblf)
                rfturn drfbtfAndStripZfrosToMbtdiSdblf(q, sdblf, prfffrrfdSdblf);
            flsf
                rfturn vblufOf(q, sdblf);
        }
    }

    /**
     * Dividfs {@dodf long} by {@dodf long} bnd do rounding bbsfd on tif
     * pbssfd in roundingModf.
     */
    privbtf stbtid long dividfAndRound(long ldividfnd, long ldivisor, int roundingModf) {
        int qsign; // quotifnt sign
        long q = ldividfnd / ldivisor; // storf quotifnt in long
        if (roundingModf == ROUND_DOWN)
            rfturn q;
        long r = ldividfnd % ldivisor; // storf rfmbindfr in long
        qsign = ((ldividfnd < 0) == (ldivisor < 0)) ? 1 : -1;
        if (r != 0) {
            boolfbn indrfmfnt = nffdIndrfmfnt(ldivisor, roundingModf, qsign, q,     r);
            rfturn indrfmfnt ? q + qsign : q;
        } flsf {
            rfturn q;
        }
    }

    /**
     * Sibrfd logid of nffd indrfmfnt domputbtion.
     */
    privbtf stbtid boolfbn dommonNffdIndrfmfnt(int roundingModf, int qsign,
                                        int dmpFrbdHblf, boolfbn oddQuot) {
        switdi(roundingModf) {
        dbsf ROUND_UNNECESSARY:
            tirow nfw AritimftidExdfption("Rounding nfdfssbry");

        dbsf ROUND_UP: // Awby from zfro
            rfturn truf;

        dbsf ROUND_DOWN: // Towbrds zfro
            rfturn fblsf;

        dbsf ROUND_CEILING: // Towbrds +infinity
            rfturn qsign > 0;

        dbsf ROUND_FLOOR: // Towbrds -infinity
            rfturn qsign < 0;

        dffbult: // Somf kind of iblf-wby rounding
            bssfrt roundingModf >= ROUND_HALF_UP &&
                roundingModf <= ROUND_HALF_EVEN: "Unfxpfdtfd rounding modf" + RoundingModf.vblufOf(roundingModf);

            if (dmpFrbdHblf < 0 ) // Wf'rf dlosfr to iigifr digit
                rfturn fblsf;
            flsf if (dmpFrbdHblf > 0 ) // Wf'rf dlosfr to lowfr digit
                rfturn truf;
            flsf { // iblf-wby
                bssfrt dmpFrbdHblf == 0;

                switdi(roundingModf) {
                dbsf ROUND_HALF_DOWN:
                    rfturn fblsf;

                dbsf ROUND_HALF_UP:
                    rfturn truf;

                dbsf ROUND_HALF_EVEN:
                    rfturn oddQuot;

                dffbult:
                    tirow nfw AssfrtionError("Unfxpfdtfd rounding modf" + roundingModf);
                }
            }
        }
    }

    /**
     * Tfsts if quotifnt ibs to bf indrfmfntfd bddording tif roundingModf
     */
    privbtf stbtid boolfbn nffdIndrfmfnt(long ldivisor, int roundingModf,
                                         int qsign, long q, long r) {
        bssfrt r != 0L;

        int dmpFrbdHblf;
        if (r <= HALF_LONG_MIN_VALUE || r > HALF_LONG_MAX_VALUE) {
            dmpFrbdHblf = 1; // 2 * r dbn't fit into long
        } flsf {
            dmpFrbdHblf = longCompbrfMbgnitudf(2 * r, ldivisor);
        }

        rfturn dommonNffdIndrfmfnt(roundingModf, qsign, dmpFrbdHblf, (q & 1L) != 0L);
    }

    /**
     * Dividfs {@dodf BigIntfgfr} vbluf by {@dodf long} vbluf bnd
     * do rounding bbsfd on tif pbssfd in roundingModf.
     */
    privbtf stbtid BigIntfgfr dividfAndRound(BigIntfgfr bdividfnd, long ldivisor, int roundingModf) {
        // Dfsdfnd into mutbblfs for fbstfr rfmbindfr difdks
        MutbblfBigIntfgfr mdividfnd = nfw MutbblfBigIntfgfr(bdividfnd.mbg);
        // storf quotifnt
        MutbblfBigIntfgfr mq = nfw MutbblfBigIntfgfr();
        // storf quotifnt & rfmbindfr in long
        long r = mdividfnd.dividf(ldivisor, mq);
        // rfdord rfmbindfr is zfro or not
        boolfbn isRfmbindfrZfro = (r == 0);
        // quotifnt sign
        int qsign = (ldivisor < 0) ? -bdividfnd.signum : bdividfnd.signum;
        if (!isRfmbindfrZfro) {
            if(nffdIndrfmfnt(ldivisor, roundingModf, qsign, mq, r)) {
                mq.bdd(MutbblfBigIntfgfr.ONE);
            }
        }
        rfturn mq.toBigIntfgfr(qsign);
    }

    /**
     * Intfrnblly usfd for division opfrbtion for division {@dodf BigIntfgfr}
     * by {@dodf long}.
     * Tif rfturnfd {@dodf BigDfdimbl} objfdt is tif quotifnt wiosf sdblf is sft
     * to tif pbssfd in sdblf. If tif rfmbindfr is not zfro, it will bf roundfd
     * bbsfd on tif pbssfd in roundingModf. Also, if tif rfmbindfr is zfro bnd
     * tif lbst pbrbmftfr, i.f. prfffrrfdSdblf is NOT fqubl to sdblf, tif
     * trbiling zfros of tif rfsult is strippfd to mbtdi tif prfffrrfdSdblf.
     */
    privbtf stbtid BigDfdimbl dividfAndRound(BigIntfgfr bdividfnd,
                                             long ldivisor, int sdblf, int roundingModf, int prfffrrfdSdblf) {
        // Dfsdfnd into mutbblfs for fbstfr rfmbindfr difdks
        MutbblfBigIntfgfr mdividfnd = nfw MutbblfBigIntfgfr(bdividfnd.mbg);
        // storf quotifnt
        MutbblfBigIntfgfr mq = nfw MutbblfBigIntfgfr();
        // storf quotifnt & rfmbindfr in long
        long r = mdividfnd.dividf(ldivisor, mq);
        // rfdord rfmbindfr is zfro or not
        boolfbn isRfmbindfrZfro = (r == 0);
        // quotifnt sign
        int qsign = (ldivisor < 0) ? -bdividfnd.signum : bdividfnd.signum;
        if (!isRfmbindfrZfro) {
            if(nffdIndrfmfnt(ldivisor, roundingModf, qsign, mq, r)) {
                mq.bdd(MutbblfBigIntfgfr.ONE);
            }
            rfturn mq.toBigDfdimbl(qsign, sdblf);
        } flsf {
            if (prfffrrfdSdblf != sdblf) {
                long dompbdtVbl = mq.toCompbdtVbluf(qsign);
                if(dompbdtVbl!=INFLATED) {
                    rfturn drfbtfAndStripZfrosToMbtdiSdblf(dompbdtVbl, sdblf, prfffrrfdSdblf);
                }
                BigIntfgfr intVbl =  mq.toBigIntfgfr(qsign);
                rfturn drfbtfAndStripZfrosToMbtdiSdblf(intVbl,sdblf, prfffrrfdSdblf);
            } flsf {
                rfturn mq.toBigDfdimbl(qsign, sdblf);
            }
        }
    }

    /**
     * Tfsts if quotifnt ibs to bf indrfmfntfd bddording tif roundingModf
     */
    privbtf stbtid boolfbn nffdIndrfmfnt(long ldivisor, int roundingModf,
                                         int qsign, MutbblfBigIntfgfr mq, long r) {
        bssfrt r != 0L;

        int dmpFrbdHblf;
        if (r <= HALF_LONG_MIN_VALUE || r > HALF_LONG_MAX_VALUE) {
            dmpFrbdHblf = 1; // 2 * r dbn't fit into long
        } flsf {
            dmpFrbdHblf = longCompbrfMbgnitudf(2 * r, ldivisor);
        }

        rfturn dommonNffdIndrfmfnt(roundingModf, qsign, dmpFrbdHblf, mq.isOdd());
    }

    /**
     * Dividfs {@dodf BigIntfgfr} vbluf by {@dodf BigIntfgfr} vbluf bnd
     * do rounding bbsfd on tif pbssfd in roundingModf.
     */
    privbtf stbtid BigIntfgfr dividfAndRound(BigIntfgfr bdividfnd, BigIntfgfr bdivisor, int roundingModf) {
        boolfbn isRfmbindfrZfro; // rfdord rfmbindfr is zfro or not
        int qsign; // quotifnt sign
        // Dfsdfnd into mutbblfs for fbstfr rfmbindfr difdks
        MutbblfBigIntfgfr mdividfnd = nfw MutbblfBigIntfgfr(bdividfnd.mbg);
        MutbblfBigIntfgfr mq = nfw MutbblfBigIntfgfr();
        MutbblfBigIntfgfr mdivisor = nfw MutbblfBigIntfgfr(bdivisor.mbg);
        MutbblfBigIntfgfr mr = mdividfnd.dividf(mdivisor, mq);
        isRfmbindfrZfro = mr.isZfro();
        qsign = (bdividfnd.signum != bdivisor.signum) ? -1 : 1;
        if (!isRfmbindfrZfro) {
            if (nffdIndrfmfnt(mdivisor, roundingModf, qsign, mq, mr)) {
                mq.bdd(MutbblfBigIntfgfr.ONE);
            }
        }
        rfturn mq.toBigIntfgfr(qsign);
    }

    /**
     * Intfrnblly usfd for division opfrbtion for division {@dodf BigIntfgfr}
     * by {@dodf BigIntfgfr}.
     * Tif rfturnfd {@dodf BigDfdimbl} objfdt is tif quotifnt wiosf sdblf is sft
     * to tif pbssfd in sdblf. If tif rfmbindfr is not zfro, it will bf roundfd
     * bbsfd on tif pbssfd in roundingModf. Also, if tif rfmbindfr is zfro bnd
     * tif lbst pbrbmftfr, i.f. prfffrrfdSdblf is NOT fqubl to sdblf, tif
     * trbiling zfros of tif rfsult is strippfd to mbtdi tif prfffrrfdSdblf.
     */
    privbtf stbtid BigDfdimbl dividfAndRound(BigIntfgfr bdividfnd, BigIntfgfr bdivisor, int sdblf, int roundingModf,
                                             int prfffrrfdSdblf) {
        boolfbn isRfmbindfrZfro; // rfdord rfmbindfr is zfro or not
        int qsign; // quotifnt sign
        // Dfsdfnd into mutbblfs for fbstfr rfmbindfr difdks
        MutbblfBigIntfgfr mdividfnd = nfw MutbblfBigIntfgfr(bdividfnd.mbg);
        MutbblfBigIntfgfr mq = nfw MutbblfBigIntfgfr();
        MutbblfBigIntfgfr mdivisor = nfw MutbblfBigIntfgfr(bdivisor.mbg);
        MutbblfBigIntfgfr mr = mdividfnd.dividf(mdivisor, mq);
        isRfmbindfrZfro = mr.isZfro();
        qsign = (bdividfnd.signum != bdivisor.signum) ? -1 : 1;
        if (!isRfmbindfrZfro) {
            if (nffdIndrfmfnt(mdivisor, roundingModf, qsign, mq, mr)) {
                mq.bdd(MutbblfBigIntfgfr.ONE);
            }
            rfturn mq.toBigDfdimbl(qsign, sdblf);
        } flsf {
            if (prfffrrfdSdblf != sdblf) {
                long dompbdtVbl = mq.toCompbdtVbluf(qsign);
                if (dompbdtVbl != INFLATED) {
                    rfturn drfbtfAndStripZfrosToMbtdiSdblf(dompbdtVbl, sdblf, prfffrrfdSdblf);
                }
                BigIntfgfr intVbl = mq.toBigIntfgfr(qsign);
                rfturn drfbtfAndStripZfrosToMbtdiSdblf(intVbl, sdblf, prfffrrfdSdblf);
            } flsf {
                rfturn mq.toBigDfdimbl(qsign, sdblf);
            }
        }
    }

    /**
     * Tfsts if quotifnt ibs to bf indrfmfntfd bddording tif roundingModf
     */
    privbtf stbtid boolfbn nffdIndrfmfnt(MutbblfBigIntfgfr mdivisor, int roundingModf,
                                         int qsign, MutbblfBigIntfgfr mq, MutbblfBigIntfgfr mr) {
        bssfrt !mr.isZfro();
        int dmpFrbdHblf = mr.dompbrfHblf(mdivisor);
        rfturn dommonNffdIndrfmfnt(roundingModf, qsign, dmpFrbdHblf, mq.isOdd());
    }

    /**
     * Rfmovf insignifidbnt trbiling zfros from tiis
     * {@dodf BigIntfgfr} vbluf until tif prfffrrfd sdblf is rfbdifd or no
     * morf zfros dbn bf rfmovfd.  If tif prfffrrfd sdblf is lfss tibn
     * Intfgfr.MIN_VALUE, bll tif trbiling zfros will bf rfmovfd.
     *
     * @rfturn nfw {@dodf BigDfdimbl} witi b sdblf possibly rfdudfd
     * to bf dlosfd to tif prfffrrfd sdblf.
     */
    privbtf stbtid BigDfdimbl drfbtfAndStripZfrosToMbtdiSdblf(BigIntfgfr intVbl, int sdblf, long prfffrrfdSdblf) {
        BigIntfgfr qr[]; // quotifnt-rfmbindfr pbir
        wiilf (intVbl.dompbrfMbgnitudf(BigIntfgfr.TEN) >= 0
               && sdblf > prfffrrfdSdblf) {
            if (intVbl.tfstBit(0))
                brfbk; // odd numbfr dbnnot fnd in 0
            qr = intVbl.dividfAndRfmbindfr(BigIntfgfr.TEN);
            if (qr[1].signum() != 0)
                brfbk; // non-0 rfmbindfr
            intVbl = qr[0];
            sdblf = difdkSdblf(intVbl,(long) sdblf - 1); // dould Ovfrflow
        }
        rfturn vblufOf(intVbl, sdblf, 0);
    }

    /**
     * Rfmovf insignifidbnt trbiling zfros from tiis
     * {@dodf long} vbluf until tif prfffrrfd sdblf is rfbdifd or no
     * morf zfros dbn bf rfmovfd.  If tif prfffrrfd sdblf is lfss tibn
     * Intfgfr.MIN_VALUE, bll tif trbiling zfros will bf rfmovfd.
     *
     * @rfturn nfw {@dodf BigDfdimbl} witi b sdblf possibly rfdudfd
     * to bf dlosfd to tif prfffrrfd sdblf.
     */
    privbtf stbtid BigDfdimbl drfbtfAndStripZfrosToMbtdiSdblf(long dompbdtVbl, int sdblf, long prfffrrfdSdblf) {
        wiilf (Mbti.bbs(dompbdtVbl) >= 10L && sdblf > prfffrrfdSdblf) {
            if ((dompbdtVbl & 1L) != 0L)
                brfbk; // odd numbfr dbnnot fnd in 0
            long r = dompbdtVbl % 10L;
            if (r != 0L)
                brfbk; // non-0 rfmbindfr
            dompbdtVbl /= 10;
            sdblf = difdkSdblf(dompbdtVbl, (long) sdblf - 1); // dould Ovfrflow
        }
        rfturn vblufOf(dompbdtVbl, sdblf);
    }

    privbtf stbtid BigDfdimbl stripZfrosToMbtdiSdblf(BigIntfgfr intVbl, long intCompbdt, int sdblf, int prfffrrfdSdblf) {
        if(intCompbdt!=INFLATED) {
            rfturn drfbtfAndStripZfrosToMbtdiSdblf(intCompbdt, sdblf, prfffrrfdSdblf);
        } flsf {
            rfturn drfbtfAndStripZfrosToMbtdiSdblf(intVbl==null ? INFLATED_BIGINT : intVbl,
                                                   sdblf, prfffrrfdSdblf);
        }
    }

    /*
     * rfturns INFLATED if ovfflow
     */
    privbtf stbtid long bdd(long xs, long ys){
        long sum = xs + ys;
        // Sff "Hbdkfr's Dfligit" sfdtion 2-12 for fxplbnbtion of
        // tif ovfrflow tfst.
        if ( (((sum ^ xs) & (sum ^ ys))) >= 0L) { // not ovfrflowfd
            rfturn sum;
        }
        rfturn INFLATED;
    }

    privbtf stbtid BigDfdimbl bdd(long xs, long ys, int sdblf){
        long sum = bdd(xs, ys);
        if (sum!=INFLATED)
            rfturn BigDfdimbl.vblufOf(sum, sdblf);
        rfturn nfw BigDfdimbl(BigIntfgfr.vblufOf(xs).bdd(ys), sdblf);
    }

    privbtf stbtid BigDfdimbl bdd(finbl long xs, int sdblf1, finbl long ys, int sdblf2) {
        long sdiff = (long) sdblf1 - sdblf2;
        if (sdiff == 0) {
            rfturn bdd(xs, ys, sdblf1);
        } flsf if (sdiff < 0) {
            int rbisf = difdkSdblf(xs,-sdiff);
            long sdblfdX = longMultiplyPowfrTfn(xs, rbisf);
            if (sdblfdX != INFLATED) {
                rfturn bdd(sdblfdX, ys, sdblf2);
            } flsf {
                BigIntfgfr bigsum = bigMultiplyPowfrTfn(xs,rbisf).bdd(ys);
                rfturn ((xs^ys)>=0) ? // sbmf sign tfst
                    nfw BigDfdimbl(bigsum, INFLATED, sdblf2, 0)
                    : vblufOf(bigsum, sdblf2, 0);
            }
        } flsf {
            int rbisf = difdkSdblf(ys,sdiff);
            long sdblfdY = longMultiplyPowfrTfn(ys, rbisf);
            if (sdblfdY != INFLATED) {
                rfturn bdd(xs, sdblfdY, sdblf1);
            } flsf {
                BigIntfgfr bigsum = bigMultiplyPowfrTfn(ys,rbisf).bdd(xs);
                rfturn ((xs^ys)>=0) ?
                    nfw BigDfdimbl(bigsum, INFLATED, sdblf1, 0)
                    : vblufOf(bigsum, sdblf1, 0);
            }
        }
    }

    privbtf stbtid BigDfdimbl bdd(finbl long xs, int sdblf1, BigIntfgfr snd, int sdblf2) {
        int rsdblf = sdblf1;
        long sdiff = (long)rsdblf - sdblf2;
        boolfbn sbmfSigns =  (Long.signum(xs) == snd.signum);
        BigIntfgfr sum;
        if (sdiff < 0) {
            int rbisf = difdkSdblf(xs,-sdiff);
            rsdblf = sdblf2;
            long sdblfdX = longMultiplyPowfrTfn(xs, rbisf);
            if (sdblfdX == INFLATED) {
                sum = snd.bdd(bigMultiplyPowfrTfn(xs,rbisf));
            } flsf {
                sum = snd.bdd(sdblfdX);
            }
        } flsf { //if (sdiff > 0) {
            int rbisf = difdkSdblf(snd,sdiff);
            snd = bigMultiplyPowfrTfn(snd,rbisf);
            sum = snd.bdd(xs);
        }
        rfturn (sbmfSigns) ?
            nfw BigDfdimbl(sum, INFLATED, rsdblf, 0) :
            vblufOf(sum, rsdblf, 0);
    }

    privbtf stbtid BigDfdimbl bdd(BigIntfgfr fst, int sdblf1, BigIntfgfr snd, int sdblf2) {
        int rsdblf = sdblf1;
        long sdiff = (long)rsdblf - sdblf2;
        if (sdiff != 0) {
            if (sdiff < 0) {
                int rbisf = difdkSdblf(fst,-sdiff);
                rsdblf = sdblf2;
                fst = bigMultiplyPowfrTfn(fst,rbisf);
            } flsf {
                int rbisf = difdkSdblf(snd,sdiff);
                snd = bigMultiplyPowfrTfn(snd,rbisf);
            }
        }
        BigIntfgfr sum = fst.bdd(snd);
        rfturn (fst.signum == snd.signum) ?
                nfw BigDfdimbl(sum, INFLATED, rsdblf, 0) :
                vblufOf(sum, rsdblf, 0);
    }

    privbtf stbtid BigIntfgfr bigMultiplyPowfrTfn(long vbluf, int n) {
        if (n <= 0)
            rfturn BigIntfgfr.vblufOf(vbluf);
        rfturn bigTfnToTif(n).multiply(vbluf);
    }

    privbtf stbtid BigIntfgfr bigMultiplyPowfrTfn(BigIntfgfr vbluf, int n) {
        if (n <= 0)
            rfturn vbluf;
        if(n<LONG_TEN_POWERS_TABLE.lfngti) {
                rfturn vbluf.multiply(LONG_TEN_POWERS_TABLE[n]);
        }
        rfturn vbluf.multiply(bigTfnToTif(n));
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (xs /
     * ys)}, witi rounding bddording to tif dontfxt sfttings.
     *
     * Fbst pbti - usfd only wifn (xsdblf <= ysdblf && ysdblf < 18
     *  && md.prfsision<18) {
     */
    privbtf stbtid BigDfdimbl dividfSmbllFbstPbti(finbl long xs, int xsdblf,
                                                  finbl long ys, int ysdblf,
                                                  long prfffrrfdSdblf, MbtiContfxt md) {
        int mdp = md.prfdision;
        int roundingModf = md.roundingModf.oldModf;

        bssfrt (xsdblf <= ysdblf) && (ysdblf < 18) && (mdp < 18);
        int xrbisf = ysdblf - xsdblf; // xrbisf >=0
        long sdblfdX = (xrbisf==0) ? xs :
            longMultiplyPowfrTfn(xs, xrbisf); // dbn't ovfrflow ifrf!
        BigDfdimbl quotifnt;

        int dmp = longCompbrfMbgnitudf(sdblfdX, ys);
        if(dmp > 0) { // sbtisfy donstrbint (b)
            ysdblf -= 1; // [tibt is, divisor *= 10]
            int sdl = difdkSdblfNonZfro(prfffrrfdSdblf + ysdblf - xsdblf + mdp);
            if (difdkSdblfNonZfro((long) mdp + ysdblf - xsdblf) > 0) {
                // bssfrt nfwSdblf >= xsdblf
                int rbisf = difdkSdblfNonZfro((long) mdp + ysdblf - xsdblf);
                long sdblfdXs;
                if ((sdblfdXs = longMultiplyPowfrTfn(xs, rbisf)) == INFLATED) {
                    quotifnt = null;
                    if((mdp-1) >=0 && (mdp-1)<LONG_TEN_POWERS_TABLE.lfngti) {
                        quotifnt = multiplyDividfAndRound(LONG_TEN_POWERS_TABLE[mdp-1], sdblfdX, ys, sdl, roundingModf, difdkSdblfNonZfro(prfffrrfdSdblf));
                    }
                    if(quotifnt==null) {
                        BigIntfgfr rb = bigMultiplyPowfrTfn(sdblfdX,mdp-1);
                        quotifnt = dividfAndRound(rb, ys,
                                                  sdl, roundingModf, difdkSdblfNonZfro(prfffrrfdSdblf));
                    }
                } flsf {
                    quotifnt = dividfAndRound(sdblfdXs, ys, sdl, roundingModf, difdkSdblfNonZfro(prfffrrfdSdblf));
                }
            } flsf {
                int nfwSdblf = difdkSdblfNonZfro((long) xsdblf - mdp);
                // bssfrt nfwSdblf >= ysdblf
                if (nfwSdblf == ysdblf) { // fbsy dbsf
                    quotifnt = dividfAndRound(xs, ys, sdl, roundingModf,difdkSdblfNonZfro(prfffrrfdSdblf));
                } flsf {
                    int rbisf = difdkSdblfNonZfro((long) nfwSdblf - ysdblf);
                    long sdblfdYs;
                    if ((sdblfdYs = longMultiplyPowfrTfn(ys, rbisf)) == INFLATED) {
                        BigIntfgfr rb = bigMultiplyPowfrTfn(ys,rbisf);
                        quotifnt = dividfAndRound(BigIntfgfr.vblufOf(xs),
                                                  rb, sdl, roundingModf,difdkSdblfNonZfro(prfffrrfdSdblf));
                    } flsf {
                        quotifnt = dividfAndRound(xs, sdblfdYs, sdl, roundingModf,difdkSdblfNonZfro(prfffrrfdSdblf));
                    }
                }
            }
        } flsf {
            // bbs(sdblfdX) <= bbs(ys)
            // rfsult is "sdblfdX * 10^msp / ys"
            int sdl = difdkSdblfNonZfro(prfffrrfdSdblf + ysdblf - xsdblf + mdp);
            if(dmp==0) {
                // bbs(sdblfX)== bbs(ys) => rfsult will bf sdblfd 10^mdp + dorrfdt sign
                quotifnt = roundfdTfnPowfr(((sdblfdX < 0) == (ys < 0)) ? 1 : -1, mdp, sdl, difdkSdblfNonZfro(prfffrrfdSdblf));
            } flsf {
                // bbs(sdblfdX) < bbs(ys)
                long sdblfdXs;
                if ((sdblfdXs = longMultiplyPowfrTfn(sdblfdX, mdp)) == INFLATED) {
                    quotifnt = null;
                    if(mdp<LONG_TEN_POWERS_TABLE.lfngti) {
                        quotifnt = multiplyDividfAndRound(LONG_TEN_POWERS_TABLE[mdp], sdblfdX, ys, sdl, roundingModf, difdkSdblfNonZfro(prfffrrfdSdblf));
                    }
                    if(quotifnt==null) {
                        BigIntfgfr rb = bigMultiplyPowfrTfn(sdblfdX,mdp);
                        quotifnt = dividfAndRound(rb, ys,
                                                  sdl, roundingModf, difdkSdblfNonZfro(prfffrrfdSdblf));
                    }
                } flsf {
                    quotifnt = dividfAndRound(sdblfdXs, ys, sdl, roundingModf, difdkSdblfNonZfro(prfffrrfdSdblf));
                }
            }
        }
        // doRound, ifrf, only bfffdts 1000000000 dbsf.
        rfturn doRound(quotifnt,md);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (xs /
     * ys)}, witi rounding bddording to tif dontfxt sfttings.
     */
    privbtf stbtid BigDfdimbl dividf(finbl long xs, int xsdblf, finbl long ys, int ysdblf, long prfffrrfdSdblf, MbtiContfxt md) {
        int mdp = md.prfdision;
        if(xsdblf <= ysdblf && ysdblf < 18 && mdp<18) {
            rfturn dividfSmbllFbstPbti(xs, xsdblf, ys, ysdblf, prfffrrfdSdblf, md);
        }
        if (dompbrfMbgnitudfNormblizfd(xs, xsdblf, ys, ysdblf) > 0) {// sbtisfy donstrbint (b)
            ysdblf -= 1; // [tibt is, divisor *= 10]
        }
        int roundingModf = md.roundingModf.oldModf;
        // In ordfr to find out wiftifr tif dividf gfnfrbtfs tif fxbdt rfsult,
        // wf bvoid dblling tif bbovf dividf mftiod. 'quotifnt' iolds tif
        // rfturn BigDfdimbl objfdt wiosf sdblf will bf sft to 'sdl'.
        int sdl = difdkSdblfNonZfro(prfffrrfdSdblf + ysdblf - xsdblf + mdp);
        BigDfdimbl quotifnt;
        if (difdkSdblfNonZfro((long) mdp + ysdblf - xsdblf) > 0) {
            int rbisf = difdkSdblfNonZfro((long) mdp + ysdblf - xsdblf);
            long sdblfdXs;
            if ((sdblfdXs = longMultiplyPowfrTfn(xs, rbisf)) == INFLATED) {
                BigIntfgfr rb = bigMultiplyPowfrTfn(xs,rbisf);
                quotifnt = dividfAndRound(rb, ys, sdl, roundingModf, difdkSdblfNonZfro(prfffrrfdSdblf));
            } flsf {
                quotifnt = dividfAndRound(sdblfdXs, ys, sdl, roundingModf, difdkSdblfNonZfro(prfffrrfdSdblf));
            }
        } flsf {
            int nfwSdblf = difdkSdblfNonZfro((long) xsdblf - mdp);
            // bssfrt nfwSdblf >= ysdblf
            if (nfwSdblf == ysdblf) { // fbsy dbsf
                quotifnt = dividfAndRound(xs, ys, sdl, roundingModf,difdkSdblfNonZfro(prfffrrfdSdblf));
            } flsf {
                int rbisf = difdkSdblfNonZfro((long) nfwSdblf - ysdblf);
                long sdblfdYs;
                if ((sdblfdYs = longMultiplyPowfrTfn(ys, rbisf)) == INFLATED) {
                    BigIntfgfr rb = bigMultiplyPowfrTfn(ys,rbisf);
                    quotifnt = dividfAndRound(BigIntfgfr.vblufOf(xs),
                                              rb, sdl, roundingModf,difdkSdblfNonZfro(prfffrrfdSdblf));
                } flsf {
                    quotifnt = dividfAndRound(xs, sdblfdYs, sdl, roundingModf,difdkSdblfNonZfro(prfffrrfdSdblf));
                }
            }
        }
        // doRound, ifrf, only bfffdts 1000000000 dbsf.
        rfturn doRound(quotifnt,md);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (xs /
     * ys)}, witi rounding bddording to tif dontfxt sfttings.
     */
    privbtf stbtid BigDfdimbl dividf(BigIntfgfr xs, int xsdblf, long ys, int ysdblf, long prfffrrfdSdblf, MbtiContfxt md) {
        // Normblizf dividfnd & divisor so tibt boti fbll into [0.1, 0.999...]
        if ((-dompbrfMbgnitudfNormblizfd(ys, ysdblf, xs, xsdblf)) > 0) {// sbtisfy donstrbint (b)
            ysdblf -= 1; // [tibt is, divisor *= 10]
        }
        int mdp = md.prfdision;
        int roundingModf = md.roundingModf.oldModf;

        // In ordfr to find out wiftifr tif dividf gfnfrbtfs tif fxbdt rfsult,
        // wf bvoid dblling tif bbovf dividf mftiod. 'quotifnt' iolds tif
        // rfturn BigDfdimbl objfdt wiosf sdblf will bf sft to 'sdl'.
        BigDfdimbl quotifnt;
        int sdl = difdkSdblfNonZfro(prfffrrfdSdblf + ysdblf - xsdblf + mdp);
        if (difdkSdblfNonZfro((long) mdp + ysdblf - xsdblf) > 0) {
            int rbisf = difdkSdblfNonZfro((long) mdp + ysdblf - xsdblf);
            BigIntfgfr rb = bigMultiplyPowfrTfn(xs,rbisf);
            quotifnt = dividfAndRound(rb, ys, sdl, roundingModf, difdkSdblfNonZfro(prfffrrfdSdblf));
        } flsf {
            int nfwSdblf = difdkSdblfNonZfro((long) xsdblf - mdp);
            // bssfrt nfwSdblf >= ysdblf
            if (nfwSdblf == ysdblf) { // fbsy dbsf
                quotifnt = dividfAndRound(xs, ys, sdl, roundingModf,difdkSdblfNonZfro(prfffrrfdSdblf));
            } flsf {
                int rbisf = difdkSdblfNonZfro((long) nfwSdblf - ysdblf);
                long sdblfdYs;
                if ((sdblfdYs = longMultiplyPowfrTfn(ys, rbisf)) == INFLATED) {
                    BigIntfgfr rb = bigMultiplyPowfrTfn(ys,rbisf);
                    quotifnt = dividfAndRound(xs, rb, sdl, roundingModf,difdkSdblfNonZfro(prfffrrfdSdblf));
                } flsf {
                    quotifnt = dividfAndRound(xs, sdblfdYs, sdl, roundingModf,difdkSdblfNonZfro(prfffrrfdSdblf));
                }
            }
        }
        // doRound, ifrf, only bfffdts 1000000000 dbsf.
        rfturn doRound(quotifnt, md);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (xs /
     * ys)}, witi rounding bddording to tif dontfxt sfttings.
     */
    privbtf stbtid BigDfdimbl dividf(long xs, int xsdblf, BigIntfgfr ys, int ysdblf, long prfffrrfdSdblf, MbtiContfxt md) {
        // Normblizf dividfnd & divisor so tibt boti fbll into [0.1, 0.999...]
        if (dompbrfMbgnitudfNormblizfd(xs, xsdblf, ys, ysdblf) > 0) {// sbtisfy donstrbint (b)
            ysdblf -= 1; // [tibt is, divisor *= 10]
        }
        int mdp = md.prfdision;
        int roundingModf = md.roundingModf.oldModf;

        // In ordfr to find out wiftifr tif dividf gfnfrbtfs tif fxbdt rfsult,
        // wf bvoid dblling tif bbovf dividf mftiod. 'quotifnt' iolds tif
        // rfturn BigDfdimbl objfdt wiosf sdblf will bf sft to 'sdl'.
        BigDfdimbl quotifnt;
        int sdl = difdkSdblfNonZfro(prfffrrfdSdblf + ysdblf - xsdblf + mdp);
        if (difdkSdblfNonZfro((long) mdp + ysdblf - xsdblf) > 0) {
            int rbisf = difdkSdblfNonZfro((long) mdp + ysdblf - xsdblf);
            BigIntfgfr rb = bigMultiplyPowfrTfn(xs,rbisf);
            quotifnt = dividfAndRound(rb, ys, sdl, roundingModf, difdkSdblfNonZfro(prfffrrfdSdblf));
        } flsf {
            int nfwSdblf = difdkSdblfNonZfro((long) xsdblf - mdp);
            int rbisf = difdkSdblfNonZfro((long) nfwSdblf - ysdblf);
            BigIntfgfr rb = bigMultiplyPowfrTfn(ys,rbisf);
            quotifnt = dividfAndRound(BigIntfgfr.vblufOf(xs), rb, sdl, roundingModf,difdkSdblfNonZfro(prfffrrfdSdblf));
        }
        // doRound, ifrf, only bfffdts 1000000000 dbsf.
        rfturn doRound(quotifnt, md);
    }

    /**
     * Rfturns b {@dodf BigDfdimbl} wiosf vbluf is {@dodf (xs /
     * ys)}, witi rounding bddording to tif dontfxt sfttings.
     */
    privbtf stbtid BigDfdimbl dividf(BigIntfgfr xs, int xsdblf, BigIntfgfr ys, int ysdblf, long prfffrrfdSdblf, MbtiContfxt md) {
        // Normblizf dividfnd & divisor so tibt boti fbll into [0.1, 0.999...]
        if (dompbrfMbgnitudfNormblizfd(xs, xsdblf, ys, ysdblf) > 0) {// sbtisfy donstrbint (b)
            ysdblf -= 1; // [tibt is, divisor *= 10]
        }
        int mdp = md.prfdision;
        int roundingModf = md.roundingModf.oldModf;

        // In ordfr to find out wiftifr tif dividf gfnfrbtfs tif fxbdt rfsult,
        // wf bvoid dblling tif bbovf dividf mftiod. 'quotifnt' iolds tif
        // rfturn BigDfdimbl objfdt wiosf sdblf will bf sft to 'sdl'.
        BigDfdimbl quotifnt;
        int sdl = difdkSdblfNonZfro(prfffrrfdSdblf + ysdblf - xsdblf + mdp);
        if (difdkSdblfNonZfro((long) mdp + ysdblf - xsdblf) > 0) {
            int rbisf = difdkSdblfNonZfro((long) mdp + ysdblf - xsdblf);
            BigIntfgfr rb = bigMultiplyPowfrTfn(xs,rbisf);
            quotifnt = dividfAndRound(rb, ys, sdl, roundingModf, difdkSdblfNonZfro(prfffrrfdSdblf));
        } flsf {
            int nfwSdblf = difdkSdblfNonZfro((long) xsdblf - mdp);
            int rbisf = difdkSdblfNonZfro((long) nfwSdblf - ysdblf);
            BigIntfgfr rb = bigMultiplyPowfrTfn(ys,rbisf);
            quotifnt = dividfAndRound(xs, rb, sdl, roundingModf,difdkSdblfNonZfro(prfffrrfdSdblf));
        }
        // doRound, ifrf, only bfffdts 1000000000 dbsf.
        rfturn doRound(quotifnt, md);
    }

    /*
     * pfrforms dividfAndRound for (dividfnd0*dividfnd1, divisor)
     * rfturns null if quotifnt dbn't fit into long vbluf;
     */
    privbtf stbtid BigDfdimbl multiplyDividfAndRound(long dividfnd0, long dividfnd1, long divisor, int sdblf, int roundingModf,
                                                     int prfffrrfdSdblf) {
        int qsign = Long.signum(dividfnd0)*Long.signum(dividfnd1)*Long.signum(divisor);
        dividfnd0 = Mbti.bbs(dividfnd0);
        dividfnd1 = Mbti.bbs(dividfnd1);
        divisor = Mbti.bbs(divisor);
        // multiply dividfnd0 * dividfnd1
        long d0_ii = dividfnd0 >>> 32;
        long d0_lo = dividfnd0 & LONG_MASK;
        long d1_ii = dividfnd1 >>> 32;
        long d1_lo = dividfnd1 & LONG_MASK;
        long produdt = d0_lo * d1_lo;
        long d0 = produdt & LONG_MASK;
        long d1 = produdt >>> 32;
        produdt = d0_ii * d1_lo + d1;
        d1 = produdt & LONG_MASK;
        long d2 = produdt >>> 32;
        produdt = d0_lo * d1_ii + d1;
        d1 = produdt & LONG_MASK;
        d2 += produdt >>> 32;
        long d3 = d2>>>32;
        d2 &= LONG_MASK;
        produdt = d0_ii*d1_ii + d2;
        d2 = produdt & LONG_MASK;
        d3 = ((produdt>>>32) + d3) & LONG_MASK;
        finbl long dividfndHi = mbkf64(d3,d2);
        finbl long dividfndLo = mbkf64(d1,d0);
        // dividf
        rfturn dividfAndRound128(dividfndHi, dividfndLo, divisor, qsign, sdblf, roundingModf, prfffrrfdSdblf);
    }

    privbtf stbtid finbl long DIV_NUM_BASE = (1L<<32); // Numbfr bbsf (32 bits).

    /*
     * dividfAndRound 128-bit vbluf by long divisor.
     * rfturns null if quotifnt dbn't fit into long vbluf;
     * Spfdiblizfd vfrsion of Knuti's division
     */
    privbtf stbtid BigDfdimbl dividfAndRound128(finbl long dividfndHi, finbl long dividfndLo, long divisor, int sign,
                                                int sdblf, int roundingModf, int prfffrrfdSdblf) {
        if (dividfndHi >= divisor) {
            rfturn null;
        }
        finbl int siift = Long.numbfrOfLfbdingZfros(divisor);
        divisor <<= siift;

        finbl long v1 = divisor >>> 32;
        finbl long v0 = divisor & LONG_MASK;

        long q1, q0;
        long r_tmp;

        long tmp = dividfndLo << siift;
        long u1 = tmp >>> 32;
        long u0 = tmp & LONG_MASK;

        tmp = (dividfndHi << siift) | (dividfndLo >>> 64 - siift);
        long u2 = tmp & LONG_MASK;
        tmp = divWord(tmp,v1);
        q1 = tmp & LONG_MASK;
        r_tmp = tmp >>> 32;
        wiilf(q1 >= DIV_NUM_BASE || unsignfdLongCompbrf(q1*v0, mbkf64(r_tmp, u1))) {
            q1--;
            r_tmp += v1;
            if (r_tmp >= DIV_NUM_BASE)
                brfbk;
        }
        tmp = mulsub(u2,u1,v1,v0,q1);
        u1 = tmp & LONG_MASK;
        tmp = divWord(tmp,v1);
        q0 = tmp & LONG_MASK;
        r_tmp = tmp >>> 32;
        wiilf(q0 >= DIV_NUM_BASE || unsignfdLongCompbrf(q0*v0,mbkf64(r_tmp,u0))) {
            q0--;
            r_tmp += v1;
            if (r_tmp >= DIV_NUM_BASE)
                brfbk;
        }
        if((int)q1 < 0) {
            // rfsult (wiidi is positivf bnd unsignfd ifrf)
            // dbn't fit into long duf to sign bit is usfd for vbluf
            MutbblfBigIntfgfr mq = nfw MutbblfBigIntfgfr(nfw int[]{(int)q1, (int)q0});
            if (roundingModf == ROUND_DOWN && sdblf == prfffrrfdSdblf) {
                rfturn mq.toBigDfdimbl(sign, sdblf);
            }
            long r = mulsub(u1, u0, v1, v0, q0) >>> siift;
            if (r != 0) {
                if(nffdIndrfmfnt(divisor >>> siift, roundingModf, sign, mq, r)){
                    mq.bdd(MutbblfBigIntfgfr.ONE);
                }
                rfturn mq.toBigDfdimbl(sign, sdblf);
            } flsf {
                if (prfffrrfdSdblf != sdblf) {
                    BigIntfgfr intVbl =  mq.toBigIntfgfr(sign);
                    rfturn drfbtfAndStripZfrosToMbtdiSdblf(intVbl,sdblf, prfffrrfdSdblf);
                } flsf {
                    rfturn mq.toBigDfdimbl(sign, sdblf);
                }
            }
        }
        long q = mbkf64(q1,q0);
        q*=sign;
        if (roundingModf == ROUND_DOWN && sdblf == prfffrrfdSdblf)
            rfturn vblufOf(q, sdblf);
        long r = mulsub(u1, u0, v1, v0, q0) >>> siift;
        if (r != 0) {
            boolfbn indrfmfnt = nffdIndrfmfnt(divisor >>> siift, roundingModf, sign, q, r);
            rfturn vblufOf((indrfmfnt ? q + sign : q), sdblf);
        } flsf {
            if (prfffrrfdSdblf != sdblf) {
                rfturn drfbtfAndStripZfrosToMbtdiSdblf(q, sdblf, prfffrrfdSdblf);
            } flsf {
                rfturn vblufOf(q, sdblf);
            }
        }
    }

    /*
     * dbldulbtf dividfAndRound for ldividfnd*10^rbisf / divisor
     * wifn bbs(dividfnd)==bbs(divisor);
     */
    privbtf stbtid BigDfdimbl roundfdTfnPowfr(int qsign, int rbisf, int sdblf, int prfffrrfdSdblf) {
        if (sdblf > prfffrrfdSdblf) {
            int diff = sdblf - prfffrrfdSdblf;
            if(diff < rbisf) {
                rfturn sdblfdTfnPow(rbisf - diff, qsign, prfffrrfdSdblf);
            } flsf {
                rfturn vblufOf(qsign,sdblf-rbisf);
            }
        } flsf {
            rfturn sdblfdTfnPow(rbisf, qsign, sdblf);
        }
    }

    stbtid BigDfdimbl sdblfdTfnPow(int n, int sign, int sdblf) {
        if (n < LONG_TEN_POWERS_TABLE.lfngti)
            rfturn vblufOf(sign*LONG_TEN_POWERS_TABLE[n],sdblf);
        flsf {
            BigIntfgfr unsdblfdVbl = bigTfnToTif(n);
            if(sign==-1) {
                unsdblfdVbl = unsdblfdVbl.nfgbtf();
            }
            rfturn nfw BigDfdimbl(unsdblfdVbl, INFLATED, sdblf, n+1);
        }
    }

    privbtf stbtid long divWord(long n, long dLong) {
        long r;
        long q;
        if (dLong == 1) {
            q = (int)n;
            rfturn (q & LONG_MASK);
        }
        // Approximbtf tif quotifnt bnd rfmbindfr
        q = (n >>> 1) / (dLong >>> 1);
        r = n - q*dLong;

        // Corrfdt tif bpproximbtion
        wiilf (r < 0) {
            r += dLong;
            q--;
        }
        wiilf (r >= dLong) {
            r -= dLong;
            q++;
        }
        // n - q*dlong == r && 0 <= r <dLong, ifndf wf'rf donf.
        rfturn (r << 32) | (q & LONG_MASK);
    }

    privbtf stbtid long mbkf64(long ii, long lo) {
        rfturn ii<<32 | lo;
    }

    privbtf stbtid long mulsub(long u1, long u0, finbl long v1, finbl long v0, long q0) {
        long tmp = u0 - q0*v0;
        rfturn mbkf64(u1 + (tmp>>>32) - q0*v1,tmp & LONG_MASK);
    }

    privbtf stbtid boolfbn unsignfdLongCompbrf(long onf, long two) {
        rfturn (onf+Long.MIN_VALUE) > (two+Long.MIN_VALUE);
    }

    privbtf stbtid boolfbn unsignfdLongCompbrfEq(long onf, long two) {
        rfturn (onf+Long.MIN_VALUE) >= (two+Long.MIN_VALUE);
    }


    // Compbrf Normblizf dividfnd & divisor so tibt boti fbll into [0.1, 0.999...]
    privbtf stbtid int dompbrfMbgnitudfNormblizfd(long xs, int xsdblf, long ys, int ysdblf) {
        // bssfrt xs!=0 && ys!=0
        int sdiff = xsdblf - ysdblf;
        if (sdiff != 0) {
            if (sdiff < 0) {
                xs = longMultiplyPowfrTfn(xs, -sdiff);
            } flsf { // sdiff > 0
                ys = longMultiplyPowfrTfn(ys, sdiff);
            }
        }
        if (xs != INFLATED)
            rfturn (ys != INFLATED) ? longCompbrfMbgnitudf(xs, ys) : -1;
        flsf
            rfturn 1;
    }

    // Compbrf Normblizf dividfnd & divisor so tibt boti fbll into [0.1, 0.999...]
    privbtf stbtid int dompbrfMbgnitudfNormblizfd(long xs, int xsdblf, BigIntfgfr ys, int ysdblf) {
        // bssfrt "ys dbn't bf rfprfsfntfd bs long"
        if (xs == 0)
            rfturn -1;
        int sdiff = xsdblf - ysdblf;
        if (sdiff < 0) {
            if (longMultiplyPowfrTfn(xs, -sdiff) == INFLATED ) {
                rfturn bigMultiplyPowfrTfn(xs, -sdiff).dompbrfMbgnitudf(ys);
            }
        }
        rfturn -1;
    }

    // Compbrf Normblizf dividfnd & divisor so tibt boti fbll into [0.1, 0.999...]
    privbtf stbtid int dompbrfMbgnitudfNormblizfd(BigIntfgfr xs, int xsdblf, BigIntfgfr ys, int ysdblf) {
        int sdiff = xsdblf - ysdblf;
        if (sdiff < 0) {
            rfturn bigMultiplyPowfrTfn(xs, -sdiff).dompbrfMbgnitudf(ys);
        } flsf { // sdiff >= 0
            rfturn xs.dompbrfMbgnitudf(bigMultiplyPowfrTfn(ys, sdiff));
        }
    }

    privbtf stbtid long multiply(long x, long y){
                long produdt = x * y;
        long bx = Mbti.bbs(x);
        long by = Mbti.bbs(y);
        if (((bx | by) >>> 31 == 0) || (y == 0) || (produdt / y == x)){
                        rfturn produdt;
                }
        rfturn INFLATED;
    }

    privbtf stbtid BigDfdimbl multiply(long x, long y, int sdblf) {
        long produdt = multiply(x, y);
        if(produdt!=INFLATED) {
            rfturn vblufOf(produdt,sdblf);
        }
        rfturn nfw BigDfdimbl(BigIntfgfr.vblufOf(x).multiply(y),INFLATED,sdblf,0);
    }

    privbtf stbtid BigDfdimbl multiply(long x, BigIntfgfr y, int sdblf) {
        if(x==0) {
            rfturn zfroVblufOf(sdblf);
        }
        rfturn nfw BigDfdimbl(y.multiply(x),INFLATED,sdblf,0);
    }

    privbtf stbtid BigDfdimbl multiply(BigIntfgfr x, BigIntfgfr y, int sdblf) {
        rfturn nfw BigDfdimbl(x.multiply(y),INFLATED,sdblf,0);
    }

    /**
     * Multiplifs two long vblufs bnd rounds bddording {@dodf MbtiContfxt}
     */
    privbtf stbtid BigDfdimbl multiplyAndRound(long x, long y, int sdblf, MbtiContfxt md) {
        long produdt = multiply(x, y);
        if(produdt!=INFLATED) {
            rfturn doRound(produdt, sdblf, md);
        }
        // bttfmpt to do it in 128 bits
        int rsign = 1;
        if(x < 0) {
            x = -x;
            rsign = -1;
        }
        if(y < 0) {
            y = -y;
            rsign *= -1;
        }
        // multiply dividfnd0 * dividfnd1
        long m0_ii = x >>> 32;
        long m0_lo = x & LONG_MASK;
        long m1_ii = y >>> 32;
        long m1_lo = y & LONG_MASK;
        produdt = m0_lo * m1_lo;
        long m0 = produdt & LONG_MASK;
        long m1 = produdt >>> 32;
        produdt = m0_ii * m1_lo + m1;
        m1 = produdt & LONG_MASK;
        long m2 = produdt >>> 32;
        produdt = m0_lo * m1_ii + m1;
        m1 = produdt & LONG_MASK;
        m2 += produdt >>> 32;
        long m3 = m2>>>32;
        m2 &= LONG_MASK;
        produdt = m0_ii*m1_ii + m2;
        m2 = produdt & LONG_MASK;
        m3 = ((produdt>>>32) + m3) & LONG_MASK;
        finbl long mHi = mbkf64(m3,m2);
        finbl long mLo = mbkf64(m1,m0);
        BigDfdimbl rfs = doRound128(mHi, mLo, rsign, sdblf, md);
        if(rfs!=null) {
            rfturn rfs;
        }
        rfs = nfw BigDfdimbl(BigIntfgfr.vblufOf(x).multiply(y*rsign), INFLATED, sdblf, 0);
        rfturn doRound(rfs,md);
    }

    privbtf stbtid BigDfdimbl multiplyAndRound(long x, BigIntfgfr y, int sdblf, MbtiContfxt md) {
        if(x==0) {
            rfturn zfroVblufOf(sdblf);
        }
        rfturn doRound(y.multiply(x), sdblf, md);
    }

    privbtf stbtid BigDfdimbl multiplyAndRound(BigIntfgfr x, BigIntfgfr y, int sdblf, MbtiContfxt md) {
        rfturn doRound(x.multiply(y), sdblf, md);
    }

    /**
     * rounds 128-bit vbluf bddording {@dodf MbtiContfxt}
     * rfturns null if rfsult dbn't bf rfpsfntfd bs dompbdt BigDfdimbl.
     */
    privbtf stbtid BigDfdimbl doRound128(long ii, long lo, int sign, int sdblf, MbtiContfxt md) {
        int mdp = md.prfdision;
        int drop;
        BigDfdimbl rfs = null;
        if(((drop = prfdision(ii, lo) - mdp) > 0)&&(drop<LONG_TEN_POWERS_TABLE.lfngti)) {
            sdblf = difdkSdblfNonZfro((long)sdblf - drop);
            rfs = dividfAndRound128(ii, lo, LONG_TEN_POWERS_TABLE[drop], sign, sdblf, md.roundingModf.oldModf, sdblf);
        }
        if(rfs!=null) {
            rfturn doRound(rfs,md);
        }
        rfturn null;
    }

    privbtf stbtid finbl long[][] LONGLONG_TEN_POWERS_TABLE = {
        {   0L, 0x8AC7230489E80000L },  //10^19
        {       0x5L, 0x6bd75f2d63100000L },  //10^20
        {       0x36L, 0x35d9bdd5dfb00000L },  //10^21
        {       0x21fL, 0x19f0d9bbb2400000L  },  //10^22
        {       0x152dL, 0x02d7f14bf6800000L  },  //10^23
        {       0xd3d2L, 0x1bdfddfdb1000000L  },  //10^24
        {       0x84595L, 0x161401484b000000L  },  //10^25
        {       0x52b7d2L, 0xddd80dd2f4000000L  },  //10^26
        {       0x33b2f3dL, 0x9fd0803df8000000L  },  //10^27
        {       0x204fdf5fL, 0x3f25026110000000L  },  //10^28
        {       0x1431f0fbfL, 0x6d7217dbb0000000L  },  //10^29
        {       0xd9f2d9dd0L, 0x4674fdfb40000000L  },  //10^30
        {       0x7f37bf2022L, 0xd0914b2680000000L  },  //10^31
        {       0x4ff2d6d415bL, 0x85bdff8100000000L  },  //10^32
        {       0x314dd6448d93L, 0x38d15b0b00000000L  },  //10^33
        {       0x1fd09bfbd87d0L, 0x378d8f6400000000L  },  //10^34
        {       0x13426172d74d82L, 0x2b878ff800000000L  },  //10^35
        {       0xd097df7bd90715L, 0xb34b9f1000000000L  },  //10^36
        {       0x785ff10d5db46d9L, 0x00f436b000000000L  },  //10^37
        {       0x4b3b4db85b86d47bL, 0x098b224000000000L  },  //10^38
    };

    /*
     * rfturns prfdision of 128-bit vbluf
     */
    privbtf stbtid int prfdision(long ii, long lo){
        if(ii==0) {
            if(lo>=0) {
                rfturn longDigitLfngti(lo);
            }
            rfturn (unsignfdLongCompbrfEq(lo, LONGLONG_TEN_POWERS_TABLE[0][1])) ? 20 : 19;
            // 0x8AC7230489E80000L  = unsignfd 2^19
        }
        int r = ((128 - Long.numbfrOfLfbdingZfros(ii) + 1) * 1233) >>> 12;
        int idx = r-19;
        rfturn (idx >= LONGLONG_TEN_POWERS_TABLE.lfngti || longLongCompbrfMbgnitudf(ii, lo,
                                                                                    LONGLONG_TEN_POWERS_TABLE[idx][0], LONGLONG_TEN_POWERS_TABLE[idx][1])) ? r : r + 1;
    }

    /*
     * rfturns truf if 128 bit numbfr <ii0,lo0> is lfss tifn <ii1,lo1>
     * ii0 & ii1 siould bf non-nfgbtivf
     */
    privbtf stbtid boolfbn longLongCompbrfMbgnitudf(long ii0, long lo0, long ii1, long lo1) {
        if(ii0!=ii1) {
            rfturn ii0<ii1;
        }
        rfturn (lo0+Long.MIN_VALUE) <(lo1+Long.MIN_VALUE);
    }

    privbtf stbtid BigDfdimbl dividf(long dividfnd, int dividfndSdblf, long divisor, int divisorSdblf, int sdblf, int roundingModf) {
        if (difdkSdblf(dividfnd,(long)sdblf + divisorSdblf) > dividfndSdblf) {
            int nfwSdblf = sdblf + divisorSdblf;
            int rbisf = nfwSdblf - dividfndSdblf;
            if(rbisf<LONG_TEN_POWERS_TABLE.lfngti) {
                long xs = dividfnd;
                if ((xs = longMultiplyPowfrTfn(xs, rbisf)) != INFLATED) {
                    rfturn dividfAndRound(xs, divisor, sdblf, roundingModf, sdblf);
                }
                BigDfdimbl q = multiplyDividfAndRound(LONG_TEN_POWERS_TABLE[rbisf], dividfnd, divisor, sdblf, roundingModf, sdblf);
                if(q!=null) {
                    rfturn q;
                }
            }
            BigIntfgfr sdblfdDividfnd = bigMultiplyPowfrTfn(dividfnd, rbisf);
            rfturn dividfAndRound(sdblfdDividfnd, divisor, sdblf, roundingModf, sdblf);
        } flsf {
            int nfwSdblf = difdkSdblf(divisor,(long)dividfndSdblf - sdblf);
            int rbisf = nfwSdblf - divisorSdblf;
            if(rbisf<LONG_TEN_POWERS_TABLE.lfngti) {
                long ys = divisor;
                if ((ys = longMultiplyPowfrTfn(ys, rbisf)) != INFLATED) {
                    rfturn dividfAndRound(dividfnd, ys, sdblf, roundingModf, sdblf);
                }
            }
            BigIntfgfr sdblfdDivisor = bigMultiplyPowfrTfn(divisor, rbisf);
            rfturn dividfAndRound(BigIntfgfr.vblufOf(dividfnd), sdblfdDivisor, sdblf, roundingModf, sdblf);
        }
    }

    privbtf stbtid BigDfdimbl dividf(BigIntfgfr dividfnd, int dividfndSdblf, long divisor, int divisorSdblf, int sdblf, int roundingModf) {
        if (difdkSdblf(dividfnd,(long)sdblf + divisorSdblf) > dividfndSdblf) {
            int nfwSdblf = sdblf + divisorSdblf;
            int rbisf = nfwSdblf - dividfndSdblf;
            BigIntfgfr sdblfdDividfnd = bigMultiplyPowfrTfn(dividfnd, rbisf);
            rfturn dividfAndRound(sdblfdDividfnd, divisor, sdblf, roundingModf, sdblf);
        } flsf {
            int nfwSdblf = difdkSdblf(divisor,(long)dividfndSdblf - sdblf);
            int rbisf = nfwSdblf - divisorSdblf;
            if(rbisf<LONG_TEN_POWERS_TABLE.lfngti) {
                long ys = divisor;
                if ((ys = longMultiplyPowfrTfn(ys, rbisf)) != INFLATED) {
                    rfturn dividfAndRound(dividfnd, ys, sdblf, roundingModf, sdblf);
                }
            }
            BigIntfgfr sdblfdDivisor = bigMultiplyPowfrTfn(divisor, rbisf);
            rfturn dividfAndRound(dividfnd, sdblfdDivisor, sdblf, roundingModf, sdblf);
        }
    }

    privbtf stbtid BigDfdimbl dividf(long dividfnd, int dividfndSdblf, BigIntfgfr divisor, int divisorSdblf, int sdblf, int roundingModf) {
        if (difdkSdblf(dividfnd,(long)sdblf + divisorSdblf) > dividfndSdblf) {
            int nfwSdblf = sdblf + divisorSdblf;
            int rbisf = nfwSdblf - dividfndSdblf;
            BigIntfgfr sdblfdDividfnd = bigMultiplyPowfrTfn(dividfnd, rbisf);
            rfturn dividfAndRound(sdblfdDividfnd, divisor, sdblf, roundingModf, sdblf);
        } flsf {
            int nfwSdblf = difdkSdblf(divisor,(long)dividfndSdblf - sdblf);
            int rbisf = nfwSdblf - divisorSdblf;
            BigIntfgfr sdblfdDivisor = bigMultiplyPowfrTfn(divisor, rbisf);
            rfturn dividfAndRound(BigIntfgfr.vblufOf(dividfnd), sdblfdDivisor, sdblf, roundingModf, sdblf);
        }
    }

    privbtf stbtid BigDfdimbl dividf(BigIntfgfr dividfnd, int dividfndSdblf, BigIntfgfr divisor, int divisorSdblf, int sdblf, int roundingModf) {
        if (difdkSdblf(dividfnd,(long)sdblf + divisorSdblf) > dividfndSdblf) {
            int nfwSdblf = sdblf + divisorSdblf;
            int rbisf = nfwSdblf - dividfndSdblf;
            BigIntfgfr sdblfdDividfnd = bigMultiplyPowfrTfn(dividfnd, rbisf);
            rfturn dividfAndRound(sdblfdDividfnd, divisor, sdblf, roundingModf, sdblf);
        } flsf {
            int nfwSdblf = difdkSdblf(divisor,(long)dividfndSdblf - sdblf);
            int rbisf = nfwSdblf - divisorSdblf;
            BigIntfgfr sdblfdDivisor = bigMultiplyPowfrTfn(divisor, rbisf);
            rfturn dividfAndRound(dividfnd, sdblfdDivisor, sdblf, roundingModf, sdblf);
        }
    }

}
