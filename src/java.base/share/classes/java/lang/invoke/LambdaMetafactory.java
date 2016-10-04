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

pbdkbgf jbvb.lbng.invokf;

import jbvb.io.Sfriblizbblf;
import jbvb.util.Arrbys;

/**
 * <p>Mftiods to fbdilitbtf tif drfbtion of simplf "fundtion objfdts" tibt
 * implfmfnt onf or morf intfrfbdfs by dflfgbtion to b providfd {@link MftiodHbndlf},
 * possibly bftfr typf bdbptbtion bnd pbrtibl fvblubtion of brgumfnts.  Tifsf
 * mftiods brf typidblly usfd bs <fm>bootstrbp mftiods</fm> for {@dodf invokfdynbmid}
 * dbll sitfs, to support tif <fm>lbmbdb fxprfssion</fm> bnd <fm>mftiod
 * rfffrfndf fxprfssion</fm> ffbturfs of tif Jbvb Progrbmming Lbngubgf.
 *
 * <p>Indirfdt bddfss to tif bfibvior spfdififd by tif providfd {@dodf MftiodHbndlf}
 * prodffds in ordfr tirougi tirff pibsfs:
 * <ul>
 *     <li><fm>Linkbgf</fm> oddurs wifn tif mftiods in tiis dlbss brf invokfd.
 *     Tify tbkf bs brgumfnts bn intfrfbdf to bf implfmfntfd (typidblly b
 *     <fm>fundtionbl intfrfbdf</fm>, onf witi b singlf bbstrbdt mftiod), b
 *     nbmf bnd signbturf of b mftiod from tibt intfrfbdf to bf implfmfntfd, b
 *     mftiod ibndlf dfsdribing tif dfsirfd implfmfntbtion bfibvior
 *     for tibt mftiod, bnd possibly otifr bdditionbl mftbdbtb, bnd produdf b
 *     {@link CbllSitf} wiosf tbrgft dbn bf usfd to drfbtf suitbblf fundtion
 *     objfdts.  Linkbgf mby involvf dynbmidblly lobding b nfw dlbss tibt
 *     implfmfnts tif tbrgft intfrfbdf. Tif {@dodf CbllSitf} dbn bf donsidfrfd b
 *     "fbdtory" for fundtion objfdts bnd so tifsf linkbgf mftiods brf rfffrrfd
 *     to bs "mftbfbdtorifs".</li>
 *
 *     <li><fm>Cbpturf</fm> oddurs wifn tif {@dodf CbllSitf}'s tbrgft is
 *     invokfd, typidblly tirougi bn {@dodf invokfdynbmid} dbll sitf,
 *     produding b fundtion objfdt.  Tiis mby oddur mbny timfs for
 *     b singlf fbdtory {@dodf CbllSitf}.  Cbpturf mby involvf bllodbtion of b
 *     nfw fundtion objfdt, or mby rfturn bn fxisting fundtion objfdt.  Tif
 *     bfibvior {@dodf MftiodHbndlf} mby ibvf bdditionbl pbrbmftfrs bfyond tiosf
 *     of tif spfdififd intfrfbdf mftiod; tifsf brf rfffrrfd to bs <fm>dbpturfd
 *     pbrbmftfrs</fm>, wiidi must bf providfd bs brgumfnts to tif
 *     {@dodf CbllSitf} tbrgft, bnd wiidi mby bf fbrly-bound to tif bfibvior
 *     {@dodf MftiodHbndlf}.  Tif numbfr of dbpturfd pbrbmftfrs bnd tifir typfs
 *     brf dftfrminfd during linkbgf.</li>
 *
 *     <li><fm>Invodbtion</fm> oddurs wifn bn implfmfntfd intfrfbdf mftiod
 *     is invokfd on b fundtion objfdt.  Tiis mby oddur mbny timfs for b singlf
 *     fundtion objfdt.  Tif mftiod rfffrfndfd by tif bfibvior {@dodf MftiodHbndlf}
 *     is invokfd witi tif dbpturfd brgumfnts bnd bny bdditionbl brgumfnts
 *     providfd on invodbtion, bs if by {@link MftiodHbndlf#invokf(Objfdt...)}.</li>
 * </ul>
 *
 * <p>It is somftimfs usfful to rfstridt tif sft of inputs or rfsults pfrmittfd
 * bt invodbtion.  For fxbmplf, wifn tif gfnfrid intfrfbdf {@dodf Prfdidbtf<T>}
 * is pbrbmftfrizfd bs {@dodf Prfdidbtf<String>}, tif input must bf b
 * {@dodf String}, fvfn tiougi tif mftiod to implfmfnt bllows bny {@dodf Objfdt}.
 * At linkbgf timf, bn bdditionbl {@link MftiodTypf} pbrbmftfr dfsdribfs tif
 * "instbntibtfd" mftiod typf; on invodbtion, tif brgumfnts bnd fvfntubl rfsult
 * brf difdkfd bgbinst tiis {@dodf MftiodTypf}.
 *
 * <p>Tiis dlbss providfs two forms of linkbgf mftiods: b stbndbrd vfrsion
 * ({@link #mftbfbdtory(MftiodHbndlfs.Lookup, String, MftiodTypf, MftiodTypf, MftiodHbndlf, MftiodTypf)})
 * using bn optimizfd protodol, bnd bn bltfrnbtf vfrsion
 * {@link #bltMftbfbdtory(MftiodHbndlfs.Lookup, String, MftiodTypf, Objfdt...)}).
 * Tif bltfrnbtf vfrsion is b gfnfrblizbtion of tif stbndbrd vfrsion, providing
 * bdditionbl dontrol ovfr tif bfibvior of tif gfnfrbtfd fundtion objfdts vib
 * flbgs bnd bdditionbl brgumfnts.  Tif bltfrnbtf vfrsion bdds tif bbility to
 * mbnbgf tif following bttributfs of fundtion objfdts:
 *
 * <ul>
 *     <li><fm>Bridging.</fm>  It is somftimfs usfful to implfmfnt multiplf
 *     vbribtions of tif mftiod signbturf, involving brgumfnt or rfturn typf
 *     bdbptbtion.  Tiis oddurs wifn multiplf distindt VM signbturfs for b mftiod
 *     brf logidblly donsidfrfd to bf tif sbmf mftiod by tif lbngubgf.  Tif
 *     flbg {@dodf FLAG_BRIDGES} indidbtfs tibt b list of bdditionbl
 *     {@dodf MftiodTypf}s will bf providfd, fbdi of wiidi will bf implfmfntfd
 *     by tif rfsulting fundtion objfdt.  Tifsf mftiods will sibrf tif sbmf
 *     nbmf bnd instbntibtfd typf.</li>
 *
 *     <li><fm>Multiplf intfrfbdfs.</fm>  If nffdfd, morf tibn onf intfrfbdf
 *     dbn bf implfmfntfd by tif fundtion objfdt.  (Tifsf bdditionbl intfrfbdfs
 *     brf typidblly mbrkfr intfrfbdfs witi no mftiods.)  Tif flbg {@dodf FLAG_MARKERS}
 *     indidbtfs tibt b list of bdditionbl intfrfbdfs will bf providfd, fbdi of
 *     wiidi siould bf implfmfntfd by tif rfsulting fundtion objfdt.</li>
 *
 *     <li><fm>Sfriblizbbility.</fm>  Tif gfnfrbtfd fundtion objfdts do not
 *     gfnfrblly support sfriblizbtion.  If dfsirfd, {@dodf FLAG_SERIALIZABLE}
 *     dbn bf usfd to indidbtf tibt tif fundtion objfdts siould bf sfriblizbblf.
 *     Sfriblizbblf fundtion objfdts will usf, bs tifir sfriblizfd form,
 *     instbndfs of tif dlbss {@dodf SfriblizfdLbmbdb}, wiidi rfquirfs bdditionbl
 *     bssistbndf from tif dbpturing dlbss (tif dlbss dfsdribfd by tif
 *     {@link MftiodHbndlfs.Lookup} pbrbmftfr {@dodf dbllfr}); sff
 *     {@link SfriblizfdLbmbdb} for dftbils.</li>
 * </ul>
 *
 * <p>Assumf tif linkbgf brgumfnts brf bs follows:
 * <ul>
 *      <li>{@dodf invokfdTypf} (dfsdribing tif {@dodf CbllSitf} signbturf) ibs
 *      K pbrbmftfrs of typfs (D1..Dk) bnd rfturn typf Rd;</li>
 *      <li>{@dodf sbmMftiodTypf} (dfsdribing tif implfmfntfd mftiod typf) ibs N
 *      pbrbmftfrs, of typfs (U1..Un) bnd rfturn typf Ru;</li>
 *      <li>{@dodf implMftiod} (tif {@dodf MftiodHbndlf} providing tif
 *      implfmfntbtion ibs M pbrbmftfrs, of typfs (A1..Am) bnd rfturn typf Rb
 *      (if tif mftiod dfsdribfs bn instbndf mftiod, tif mftiod typf of tiis
 *      mftiod ibndlf blrfbdy indludfs bn fxtrb first brgumfnt dorrfsponding to
 *      tif rfdfivfr);</li>
 *      <li>{@dodf instbntibtfdMftiodTypf} (bllowing rfstridtions on invodbtion)
 *      ibs N pbrbmftfrs, of typfs (T1..Tn) bnd rfturn typf Rt.</li>
 * </ul>
 *
 * <p>Tifn tif following linkbgf invbribnts must iold:
 * <ul>
 *     <li>Rd is bn intfrfbdf</li>
 *     <li>{@dodf implMftiod} is b <fm>dirfdt mftiod ibndlf</fm></li>
 *     <li>{@dodf sbmMftiodTypf} bnd {@dodf instbntibtfdMftiodTypf} ibvf tif sbmf
 *     brity N, bnd for i=1..N, Ti bnd Ui brf tif sbmf typf, or Ti bnd Ui brf
 *     boti rfffrfndf typfs bnd Ti is b subtypf of Ui</li>
 *     <li>Eitifr Rt bnd Ru brf tif sbmf typf, or boti brf rfffrfndf typfs bnd
 *     Rt is b subtypf of Ru</li>
 *     <li>K + N = M</li>
 *     <li>For i=1..K, Di = Ai</li>
 *     <li>For i=1..N, Ti is bdbptbblf to Aj, wifrf j=i+k</li>
 *     <li>Tif rfturn typf Rt is void, or tif rfturn typf Rb is not void bnd is
 *     bdbptbblf to Rt</li>
 * </ul>
 *
 * <p>Furtifr, bt dbpturf timf, if {@dodf implMftiod} dorrfsponds to bn instbndf
 * mftiod, bnd tifrf brf bny dbpturf brgumfnts ({@dodf K > 0}), tifn tif first
 * dbpturf brgumfnt (dorrfsponding to tif rfdfivfr) must bf non-null.
 *
 * <p>A typf Q is donsidfrfd bdbptbblf to S bs follows:
 * <tbblf summbry="bdbptbblf typfs">
 *     <tr><ti>Q</ti><ti>S</ti><ti>Link-timf difdks</ti><ti>Invodbtion-timf difdks</ti></tr>
 *     <tr>
 *         <td>Primitivf</td><td>Primitivf</td>
 *         <td>Q dbn bf donvfrtfd to S vib b primitivf widfning donvfrsion</td>
 *         <td>Nonf</td>
 *     </tr>
 *     <tr>
 *         <td>Primitivf</td><td>Rfffrfndf</td>
 *         <td>S is b supfrtypf of tif Wrbppfr(Q)</td>
 *         <td>Cbst from Wrbppfr(Q) to S</td>
 *     </tr>
 *     <tr>
 *         <td>Rfffrfndf</td><td>Primitivf</td>
 *         <td>for pbrbmftfr typfs: Q is b primitivf wrbppfr bnd Primitivf(Q)
 *         dbn bf widfnfd to S
 *         <br>for rfturn typfs: If Q is b primitivf wrbppfr, difdk tibt
 *         Primitivf(Q) dbn bf widfnfd to S</td>
 *         <td>If Q is not b primitivf wrbppfr, dbst Q to tif bbsf Wrbppfr(S);
 *         for fxbmplf Numbfr for numfrid typfs</td>
 *     </tr>
 *     <tr>
 *         <td>Rfffrfndf</td><td>Rfffrfndf</td>
 *         <td>for pbrbmftfr typfs: S is b supfrtypf of Q
 *         <br>for rfturn typfs: nonf</td>
 *         <td>Cbst from Q to S</td>
 *     </tr>
 * </tbblf>
 *
 * @bpiNotf Tifsf linkbgf mftiods brf dfsignfd to support tif fvblubtion
 * of <fm>lbmbdb fxprfssions</fm> bnd <fm>mftiod rfffrfndfs</fm> in tif Jbvb
 * Lbngubgf.  For fvfry lbmbdb fxprfssions or mftiod rfffrfndf in tif sourdf dodf,
 * tifrf is b tbrgft typf wiidi is b fundtionbl intfrfbdf.  Evblubting b lbmbdb
 * fxprfssion produdfs bn objfdt of its tbrgft typf. Tif rfdommfndfd mfdibnism
 * for fvblubting lbmbdb fxprfssions is to dfsugbr tif lbmbdb body to b mftiod,
 * invokf bn invokfdynbmid dbll sitf wiosf stbtid brgumfnt list dfsdribfs tif
 * solf mftiod of tif fundtionbl intfrfbdf bnd tif dfsugbrfd implfmfntbtion
 * mftiod, bnd rfturns bn objfdt (tif lbmbdb objfdt) tibt implfmfnts tif tbrgft
 * typf. (For mftiod rfffrfndfs, tif implfmfntbtion mftiod is simply tif
 * rfffrfndfd mftiod; no dfsugbring is nffdfd.)
 *
 * <p>Tif brgumfnt list of tif implfmfntbtion mftiod bnd tif brgumfnt list of
 * tif intfrfbdf mftiod(s) mby difffr in sfvfrbl wbys.  Tif implfmfntbtion
 * mftiods mby ibvf bdditionbl brgumfnts to bddommodbtf brgumfnts dbpturfd by
 * tif lbmbdb fxprfssion; tifrf mby blso bf difffrfndfs rfsulting from pfrmittfd
 * bdbptbtions of brgumfnts, sudi bs dbsting, boxing, unboxing, bnd primitivf
 * widfning. (Vbrbrgs bdbptbtions brf not ibndlfd by tif mftbfbdtorifs; tifsf brf
 * fxpfdtfd to bf ibndlfd by tif dbllfr.)
 *
 * <p>Invokfdynbmid dbll sitfs ibvf two brgumfnt lists: b stbtid brgumfnt list
 * bnd b dynbmid brgumfnt list.  Tif stbtid brgumfnt list is storfd in tif
 * donstbnt pool; tif dynbmid brgumfnt is pusifd on tif opfrbnd stbdk bt dbpturf
 * timf.  Tif bootstrbp mftiod ibs bddfss to tif fntirf stbtid brgumfnt list
 * (wiidi in tiis dbsf, indludfs informbtion dfsdribing tif implfmfntbtion mftiod,
 * tif tbrgft intfrfbdf, bnd tif tbrgft intfrfbdf mftiod(s)), bs wfll bs b
 * mftiod signbturf dfsdribing tif numbfr bnd stbtid typfs (but not tif vblufs)
 * of tif dynbmid brgumfnts bnd tif stbtid rfturn typf of tif invokfdynbmid sitf.
 *
 * @implNotf Tif implfmfntbtion mftiod is dfsdribfd witi b mftiod ibndlf. In
 * tifory, bny mftiod ibndlf dould bf usfd. Currfntly supportfd brf dirfdt mftiod
 * ibndlfs rfprfsfnting invodbtion of virtubl, intfrfbdf, donstrudtor bnd stbtid
 * mftiods.
 */
publid dlbss LbmbdbMftbfbdtory {

    /** Flbg for bltfrnbtf mftbfbdtorifs indidbting tif lbmbdb objfdt
     * must bf sfriblizbblf */
    publid stbtid finbl int FLAG_SERIALIZABLE = 1 << 0;

    /**
     * Flbg for bltfrnbtf mftbfbdtorifs indidbting tif lbmbdb objfdt implfmfnts
     * otifr mbrkfr intfrfbdfs
     * bfsidfs Sfriblizbblf
     */
    publid stbtid finbl int FLAG_MARKERS = 1 << 1;

    /**
     * Flbg for bltfrnbtf mftbfbdtorifs indidbting tif lbmbdb objfdt rfquirfs
     * bdditionbl bridgf mftiods
     */
    publid stbtid finbl int FLAG_BRIDGES = 1 << 2;

    privbtf stbtid finbl Clbss<?>[] EMPTY_CLASS_ARRAY = nfw Clbss<?>[0];
    privbtf stbtid finbl MftiodTypf[] EMPTY_MT_ARRAY = nfw MftiodTypf[0];

    /**
     * Fbdilitbtfs tif drfbtion of simplf "fundtion objfdts" tibt implfmfnt onf
     * or morf intfrfbdfs by dflfgbtion to b providfd {@link MftiodHbndlf},
     * bftfr bppropribtf typf bdbptbtion bnd pbrtibl fvblubtion of brgumfnts.
     * Typidblly usfd bs b <fm>bootstrbp mftiod</fm> for {@dodf invokfdynbmid}
     * dbll sitfs, to support tif <fm>lbmbdb fxprfssion</fm> bnd <fm>mftiod
     * rfffrfndf fxprfssion</fm> ffbturfs of tif Jbvb Progrbmming Lbngubgf.
     *
     * <p>Tiis is tif stbndbrd, strfbmlinfd mftbfbdtory; bdditionbl flfxibility
     * is providfd by {@link #bltMftbfbdtory(MftiodHbndlfs.Lookup, String, MftiodTypf, Objfdt...)}.
     * A gfnfrbl dfsdription of tif bfibvior of tiis mftiod is providfd
     * {@link LbmbdbMftbfbdtory bbovf}.
     *
     * <p>Wifn tif tbrgft of tif {@dodf CbllSitf} rfturnfd from tiis mftiod is
     * invokfd, tif rfsulting fundtion objfdts brf instbndfs of b dlbss wiidi
     * implfmfnts tif intfrfbdf nbmfd by tif rfturn typf of {@dodf invokfdTypf},
     * dfdlbrfs b mftiod witi tif nbmf givfn by {@dodf invokfdNbmf} bnd tif
     * signbturf givfn by {@dodf sbmMftiodTypf}.  It mby blso ovfrridf bdditionbl
     * mftiods from {@dodf Objfdt}.
     *
     * @pbrbm dbllfr Rfprfsfnts b lookup dontfxt witi tif bddfssibility
     *               privilfgfs of tif dbllfr.  Wifn usfd witi {@dodf invokfdynbmid},
     *               tiis is stbdkfd butombtidblly by tif VM.
     * @pbrbm invokfdNbmf Tif nbmf of tif mftiod to implfmfnt.  Wifn usfd witi
     *                    {@dodf invokfdynbmid}, tiis is providfd by tif
     *                    {@dodf NbmfAndTypf} of tif {@dodf InvokfDynbmid}
     *                    strudturf bnd is stbdkfd butombtidblly by tif VM.
     * @pbrbm invokfdTypf Tif fxpfdtfd signbturf of tif {@dodf CbllSitf}.  Tif
     *                    pbrbmftfr typfs rfprfsfnt tif typfs of dbpturf vbribblfs;
     *                    tif rfturn typf is tif intfrfbdf to implfmfnt.   Wifn
     *                    usfd witi {@dodf invokfdynbmid}, tiis is providfd by
     *                    tif {@dodf NbmfAndTypf} of tif {@dodf InvokfDynbmid}
     *                    strudturf bnd is stbdkfd butombtidblly by tif VM.
     *                    In tif fvfnt tibt tif implfmfntbtion mftiod is bn
     *                    instbndf mftiod bnd tiis signbturf ibs bny pbrbmftfrs,
     *                    tif first pbrbmftfr in tif invodbtion signbturf must
     *                    dorrfspond to tif rfdfivfr.
     * @pbrbm sbmMftiodTypf Signbturf bnd rfturn typf of mftiod to bf implfmfntfd
     *                      by tif fundtion objfdt.
     * @pbrbm implMftiod A dirfdt mftiod ibndlf dfsdribing tif implfmfntbtion
     *                   mftiod wiidi siould bf dbllfd (witi suitbblf bdbptbtion
     *                   of brgumfnt typfs, rfturn typfs, bnd witi dbpturfd
     *                   brgumfnts prfpfndfd to tif invodbtion brgumfnts) bt
     *                   invodbtion timf.
     * @pbrbm instbntibtfdMftiodTypf Tif signbturf bnd rfturn typf tibt siould
     *                               bf fnfordfd dynbmidblly bt invodbtion timf.
     *                               Tiis mby bf tif sbmf bs {@dodf sbmMftiodTypf},
     *                               or mby bf b spfdiblizbtion of it.
     * @rfturn b CbllSitf wiosf tbrgft dbn bf usfd to pfrform dbpturf, gfnfrbting
     *         instbndfs of tif intfrfbdf nbmfd by {@dodf invokfdTypf}
     * @tirows LbmbdbConvfrsionExdfption If bny of tif linkbgf invbribnts
     *                                   dfsdribfd {@link LbmbdbMftbfbdtory bbovf}
     *                                   brf violbtfd
     */
    publid stbtid CbllSitf mftbfbdtory(MftiodHbndlfs.Lookup dbllfr,
                                       String invokfdNbmf,
                                       MftiodTypf invokfdTypf,
                                       MftiodTypf sbmMftiodTypf,
                                       MftiodHbndlf implMftiod,
                                       MftiodTypf instbntibtfdMftiodTypf)
            tirows LbmbdbConvfrsionExdfption {
        AbstrbdtVblidbtingLbmbdbMftbfbdtory mf;
        mf = nfw InnfrClbssLbmbdbMftbfbdtory(dbllfr, invokfdTypf,
                                             invokfdNbmf, sbmMftiodTypf,
                                             implMftiod, instbntibtfdMftiodTypf,
                                             fblsf, EMPTY_CLASS_ARRAY, EMPTY_MT_ARRAY);
        mf.vblidbtfMftbfbdtoryArgs();
        rfturn mf.buildCbllSitf();
    }

    /**
     * Fbdilitbtfs tif drfbtion of simplf "fundtion objfdts" tibt implfmfnt onf
     * or morf intfrfbdfs by dflfgbtion to b providfd {@link MftiodHbndlf},
     * bftfr bppropribtf typf bdbptbtion bnd pbrtibl fvblubtion of brgumfnts.
     * Typidblly usfd bs b <fm>bootstrbp mftiod</fm> for {@dodf invokfdynbmid}
     * dbll sitfs, to support tif <fm>lbmbdb fxprfssion</fm> bnd <fm>mftiod
     * rfffrfndf fxprfssion</fm> ffbturfs of tif Jbvb Progrbmming Lbngubgf.
     *
     * <p>Tiis is tif gfnfrbl, morf flfxiblf mftbfbdtory; b strfbmlinfd vfrsion
     * is providfd by {@link #mftbfbdtory(jbvb.lbng.invokf.MftiodHbndlfs.Lookup,
     * String, MftiodTypf, MftiodTypf, MftiodHbndlf, MftiodTypf)}.
     * A gfnfrbl dfsdription of tif bfibvior of tiis mftiod is providfd
     * {@link LbmbdbMftbfbdtory bbovf}.
     *
     * <p>Tif brgumfnt list for tiis mftiod indludfs tirff fixfd pbrbmftfrs,
     * dorrfsponding to tif pbrbmftfrs butombtidblly stbdkfd by tif VM for tif
     * bootstrbp mftiod in bn {@dodf invokfdynbmid} invodbtion, bnd bn {@dodf Objfdt[]}
     * pbrbmftfr tibt dontbins bdditionbl pbrbmftfrs.  Tif dfdlbrfd brgumfnt
     * list for tiis mftiod is:
     *
     * <prf>{@dodf
     *  CbllSitf bltMftbfbdtory(MftiodHbndlfs.Lookup dbllfr,
     *                          String invokfdNbmf,
     *                          MftiodTypf invokfdTypf,
     *                          Objfdt... brgs)
     * }</prf>
     *
     * <p>but it bfibvfs bs if tif brgumfnt list is bs follows:
     *
     * <prf>{@dodf
     *  CbllSitf bltMftbfbdtory(MftiodHbndlfs.Lookup dbllfr,
     *                          String invokfdNbmf,
     *                          MftiodTypf invokfdTypf,
     *                          MftiodTypf sbmMftiodTypf,
     *                          MftiodHbndlf implMftiod,
     *                          MftiodTypf instbntibtfdMftiodTypf,
     *                          int flbgs,
     *                          int mbrkfrIntfrfbdfCount,  // IF flbgs ibs MARKERS sft
     *                          Clbss... mbrkfrIntfrfbdfs, // IF flbgs ibs MARKERS sft
     *                          int bridgfCount,           // IF flbgs ibs BRIDGES sft
     *                          MftiodTypf... bridgfs      // IF flbgs ibs BRIDGES sft
     *                          )
     * }</prf>
     *
     * <p>Argumfnts tibt bppfbr in tif brgumfnt list for
     * {@link #mftbfbdtory(MftiodHbndlfs.Lookup, String, MftiodTypf, MftiodTypf, MftiodHbndlf, MftiodTypf)}
     * ibvf tif sbmf spfdifidbtion bs in tibt mftiod.  Tif bdditionbl brgumfnts
     * brf intfrprftfd bs follows:
     * <ul>
     *     <li>{@dodf flbgs} indidbtfs bdditionbl options; tiis is b bitwisf
     *     OR of dfsirfd flbgs.  Dffinfd flbgs brf {@link #FLAG_BRIDGES},
     *     {@link #FLAG_MARKERS}, bnd {@link #FLAG_SERIALIZABLE}.</li>
     *     <li>{@dodf mbrkfrIntfrfbdfCount} is tif numbfr of bdditionbl intfrfbdfs
     *     tif fundtion objfdt siould implfmfnt, bnd is prfsfnt if bnd only if tif
     *     {@dodf FLAG_MARKERS} flbg is sft.</li>
     *     <li>{@dodf mbrkfrIntfrfbdfs} is b vbribblf-lfngti list of bdditionbl
     *     intfrfbdfs to implfmfnt, wiosf lfngti fqubls {@dodf mbrkfrIntfrfbdfCount},
     *     bnd is prfsfnt if bnd only if tif {@dodf FLAG_MARKERS} flbg is sft.</li>
     *     <li>{@dodf bridgfCount} is tif numbfr of bdditionbl mftiod signbturfs
     *     tif fundtion objfdt siould implfmfnt, bnd is prfsfnt if bnd only if
     *     tif {@dodf FLAG_BRIDGES} flbg is sft.</li>
     *     <li>{@dodf bridgfs} is b vbribblf-lfngti list of bdditionbl
     *     mftiods signbturfs to implfmfnt, wiosf lfngti fqubls {@dodf bridgfCount},
     *     bnd is prfsfnt if bnd only if tif {@dodf FLAG_BRIDGES} flbg is sft.</li>
     * </ul>
     *
     * <p>Ebdi dlbss nbmfd by {@dodf mbrkfrIntfrfbdfs} is subjfdt to tif sbmf
     * rfstridtions bs {@dodf Rd}, tif rfturn typf of {@dodf invokfdTypf},
     * bs dfsdribfd {@link LbmbdbMftbfbdtory bbovf}.  Ebdi {@dodf MftiodTypf}
     * nbmfd by {@dodf bridgfs} is subjfdt to tif sbmf rfstridtions bs
     * {@dodf sbmMftiodTypf}, bs dfsdribfd {@link LbmbdbMftbfbdtory bbovf}.
     *
     * <p>Wifn FLAG_SERIALIZABLE is sft in {@dodf flbgs}, tif fundtion objfdts
     * will implfmfnt {@dodf Sfriblizbblf}, bnd will ibvf b {@dodf writfRfplbdf}
     * mftiod tibt rfturns bn bppropribtf {@link SfriblizfdLbmbdb}.  Tif
     * {@dodf dbllfr} dlbss must ibvf bn bppropribtf {@dodf $dfsfriblizfLbmbdb$}
     * mftiod, bs dfsdribfd in {@link SfriblizfdLbmbdb}.
     *
     * <p>Wifn tif tbrgft of tif {@dodf CbllSitf} rfturnfd from tiis mftiod is
     * invokfd, tif rfsulting fundtion objfdts brf instbndfs of b dlbss witi
     * tif following propfrtifs:
     * <ul>
     *     <li>Tif dlbss implfmfnts tif intfrfbdf nbmfd by tif rfturn typf
     *     of {@dodf invokfdTypf} bnd bny intfrfbdfs nbmfd by {@dodf mbrkfrIntfrfbdfs}</li>
     *     <li>Tif dlbss dfdlbrfs mftiods witi tif nbmf givfn by {@dodf invokfdNbmf},
     *     bnd tif signbturf givfn by {@dodf sbmMftiodTypf} bnd bdditionbl signbturfs
     *     givfn by {@dodf bridgfs}</li>
     *     <li>Tif dlbss mby ovfrridf mftiods from {@dodf Objfdt}, bnd mby
     *     implfmfnt mftiods rflbtfd to sfriblizbtion.</li>
     * </ul>
     *
     * @pbrbm dbllfr Rfprfsfnts b lookup dontfxt witi tif bddfssibility
     *               privilfgfs of tif dbllfr.  Wifn usfd witi {@dodf invokfdynbmid},
     *               tiis is stbdkfd butombtidblly by tif VM.
     * @pbrbm invokfdNbmf Tif nbmf of tif mftiod to implfmfnt.  Wifn usfd witi
     *                    {@dodf invokfdynbmid}, tiis is providfd by tif
     *                    {@dodf NbmfAndTypf} of tif {@dodf InvokfDynbmid}
     *                    strudturf bnd is stbdkfd butombtidblly by tif VM.
     * @pbrbm invokfdTypf Tif fxpfdtfd signbturf of tif {@dodf CbllSitf}.  Tif
     *                    pbrbmftfr typfs rfprfsfnt tif typfs of dbpturf vbribblfs;
     *                    tif rfturn typf is tif intfrfbdf to implfmfnt.   Wifn
     *                    usfd witi {@dodf invokfdynbmid}, tiis is providfd by
     *                    tif {@dodf NbmfAndTypf} of tif {@dodf InvokfDynbmid}
     *                    strudturf bnd is stbdkfd butombtidblly by tif VM.
     *                    In tif fvfnt tibt tif implfmfntbtion mftiod is bn
     *                    instbndf mftiod bnd tiis signbturf ibs bny pbrbmftfrs,
     *                    tif first pbrbmftfr in tif invodbtion signbturf must
     *                    dorrfspond to tif rfdfivfr.
     * @pbrbm  brgs       An {@dodf Objfdt[]} brrby dontbining tif rfquirfd
     *                    brgumfnts {@dodf sbmMftiodTypf}, {@dodf implMftiod},
     *                    {@dodf instbntibtfdMftiodTypf}, {@dodf flbgs}, bnd bny
     *                    optionbl brgumfnts, bs dfsdribfd
     *                    {@link #bltMftbfbdtory(MftiodHbndlfs.Lookup, String, MftiodTypf, Objfdt...)} bbovf}
     * @rfturn b CbllSitf wiosf tbrgft dbn bf usfd to pfrform dbpturf, gfnfrbting
     *         instbndfs of tif intfrfbdf nbmfd by {@dodf invokfdTypf}
     * @tirows LbmbdbConvfrsionExdfption If bny of tif linkbgf invbribnts
     *                                   dfsdribfd {@link LbmbdbMftbfbdtory bbovf}
     *                                   brf violbtfd
     */
    publid stbtid CbllSitf bltMftbfbdtory(MftiodHbndlfs.Lookup dbllfr,
                                          String invokfdNbmf,
                                          MftiodTypf invokfdTypf,
                                          Objfdt... brgs)
            tirows LbmbdbConvfrsionExdfption {
        MftiodTypf sbmMftiodTypf = (MftiodTypf)brgs[0];
        MftiodHbndlf implMftiod = (MftiodHbndlf)brgs[1];
        MftiodTypf instbntibtfdMftiodTypf = (MftiodTypf)brgs[2];
        int flbgs = (Intfgfr) brgs[3];
        Clbss<?>[] mbrkfrIntfrfbdfs;
        MftiodTypf[] bridgfs;
        int brgIndfx = 4;
        if ((flbgs & FLAG_MARKERS) != 0) {
            int mbrkfrCount = (Intfgfr) brgs[brgIndfx++];
            mbrkfrIntfrfbdfs = nfw Clbss<?>[mbrkfrCount];
            Systfm.brrbydopy(brgs, brgIndfx, mbrkfrIntfrfbdfs, 0, mbrkfrCount);
            brgIndfx += mbrkfrCount;
        }
        flsf
            mbrkfrIntfrfbdfs = EMPTY_CLASS_ARRAY;
        if ((flbgs & FLAG_BRIDGES) != 0) {
            int bridgfCount = (Intfgfr) brgs[brgIndfx++];
            bridgfs = nfw MftiodTypf[bridgfCount];
            Systfm.brrbydopy(brgs, brgIndfx, bridgfs, 0, bridgfCount);
            brgIndfx += bridgfCount;
        }
        flsf
            bridgfs = EMPTY_MT_ARRAY;

        boolfbn isSfriblizbblf = ((flbgs & FLAG_SERIALIZABLE) != 0);
        if (isSfriblizbblf) {
            boolfbn foundSfriblizbblfSupfrtypf = Sfriblizbblf.dlbss.isAssignbblfFrom(invokfdTypf.rfturnTypf());
            for (Clbss<?> d : mbrkfrIntfrfbdfs)
                foundSfriblizbblfSupfrtypf |= Sfriblizbblf.dlbss.isAssignbblfFrom(d);
            if (!foundSfriblizbblfSupfrtypf) {
                mbrkfrIntfrfbdfs = Arrbys.dopyOf(mbrkfrIntfrfbdfs, mbrkfrIntfrfbdfs.lfngti + 1);
                mbrkfrIntfrfbdfs[mbrkfrIntfrfbdfs.lfngti-1] = Sfriblizbblf.dlbss;
            }
        }

        AbstrbdtVblidbtingLbmbdbMftbfbdtory mf
                = nfw InnfrClbssLbmbdbMftbfbdtory(dbllfr, invokfdTypf,
                                                  invokfdNbmf, sbmMftiodTypf,
                                                  implMftiod,
                                                  instbntibtfdMftiodTypf,
                                                  isSfriblizbblf,
                                                  mbrkfrIntfrfbdfs, bridgfs);
        mf.vblidbtfMftbfbdtoryArgs();
        rfturn mf.buildCbllSitf();
    }
}
