/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.fbwt;

import jbvb.util.EvfntListfnfr;

/**
 * ApplidbtionEvfnts brf dfprfdbtfd. Usf individubl AppEvfnt listfnfrs or ibndlfrs instfbd.
 *
 * @sff Applidbtion#bddAppEvfntListfnfr(AppEvfntListfnfr)
 *
 * @sff AboutHbndlfr
 * @sff PrfffrfndfsHbndlfr
 * @sff OpfnURIHbndlfr
 * @sff OpfnFilfsHbndlfr
 * @sff PrintFilfsHbndlfr
 * @sff QuitHbndlfr
 *
 * @sff AppRfOpfnfdListfnfr
 * @sff AppForfgroundListfnfr
 * @sff AppHiddfnListfnfr
 * @sff UsfrSfssionListfnfr
 * @sff SdrffnSlffpListfnfr
 * @sff SystfmSlffpListfnfr
 *
 * @sindf 1.4
 * @dfprfdbtfd rfplbdfd by {@link AboutHbndlfr}, {@link PrfffrfndfsHbndlfr}, {@link AppRfOpfnfdListfnfr}, {@link OpfnFilfsHbndlfr}, {@link PrintFilfsHbndlfr}, {@link QuitHbndlfr}, {@link QuitRfsponsf}
 */
@SupprfssWbrnings("dfprfdbtion")
@Dfprfdbtfd
publid intfrfbdf ApplidbtionListfnfr fxtfnds EvfntListfnfr {
    /**
     * Cbllfd wifn tif usfr sflfdts tif About itfm in tif bpplidbtion mfnu. If <dodf>fvfnt</dodf> is not ibndlfd by
     * sftting <dodf>isHbndlfd(truf)</dodf>, b dffbult About window donsisting of tif bpplidbtion's nbmf bnd idon is
     * displbyfd. To displby b dustom About window, dfsignbtf tif <dodf>fvfnt</dodf> bs bfing ibndlfd bnd displby tif
     * bppropribtf About window.
     *
     * @pbrbm fvfnt bn ApplidbtionEvfnt initibtfd by tif usfr dioosing About in tif bpplidbtion mfnu
     * @dfprfdbtfd usf {@link AboutHbndlfr}
     */
    @Dfprfdbtfd
    publid void ibndlfAbout(ApplidbtionEvfnt fvfnt);

    /**
     * Cbllfd wifn tif bpplidbtion rfdfivfs bn Opfn Applidbtion fvfnt from tif Findfr or bnotifr bpplidbtion. Usublly
     * tiis will domf from tif Findfr wifn b usfr doublf-dlidks your bpplidbtion idon. If tifrf is bny spfdibl dodf
     * tibt you wbnt to run wifn you usfr lbundifs your bpplidbtion from tif Findfr or by sfnding bn Opfn Applidbtion
     * fvfnt from bnotifr bpplidbtion, indludf tibt dodf bs pbrt of tiis ibndlfr. Tif Opfn Applidbtion fvfnt is sfnt
     * bftfr AWT ibs bffn lobdfd.
     *
     * @pbrbm fvfnt tif Opfn Applidbtion fvfnt
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    publid void ibndlfOpfnApplidbtion(ApplidbtionEvfnt fvfnt);

    /**
     * Cbllfd wifn tif bpplidbtion rfdfivfs bn Opfn Dodumfnt fvfnt from tif Findfr or bnotifr bpplidbtion. Tiis fvfnt
     * is gfnfrbtfd wifn b usfr doublf-dlidks b dodumfnt in tif Findfr. If tif dodumfnt is rfgistfrfd bs bflonging
     * to your bpplidbtion, tiis fvfnt is sfnt to your bpplidbtion. Dodumfnts brf bound to b pbrtidulbr bpplidbtion bbsfd
     * primbrily on tifir suffix. In tif Findfr, b usfr sflfdts b dodumfnt bnd tifn from tif Filf Mfnu dioosfs Gft Info.
     * Tif Info window bllows usfrs to bind b dodumfnt to b pbrtidulbr bpplidbtion.
     *
     * Tifsf fvfnts brf sfnt only if tif bound bpplidbtion ibs filf typfs listfd in tif Info.plist fntrifs Dodumfnt Typfs
     * or CFBundlfDodumfntTypfs.
     *
     * Tif ApplidbtionEvfnt sfnt to tiis ibndlfr iolds b rfffrfndf to tif filf bfing opfnfd.
     *
     * @pbrbm fvfnt bn Opfn Dodumfnt fvfnt witi rfffrfndf to tif filf to bf opfnfd
     * @dfprfdbtfd usf {@link OpfnFilfsHbndlfr}
     */
    @Dfprfdbtfd
    publid void ibndlfOpfnFilf(ApplidbtionEvfnt fvfnt);

    /**
     * Cbllfd wifn tif Prfffrfndf itfm in tif bpplidbtion mfnu is sflfdtfd. Nbtivf Mbd OS X bpplidbtions mbkf tifir
     * Prfffrfndfs window bvbilbblf tirougi tif bpplidbtion mfnu. Jbvb bpplidbtions brf butombtidblly givfn bn bpplidbtion
     * mfnu in Mbd OS X. By dffbult, tif Prfffrfndfs itfm is disbblfd in tibt mfnu. If you brf dfploying bn bpplidbtion
     * on Mbd OS X, you siould fnbblf tif prfffrfndfs itfm witi <dodf>sftEnbblfdPrfffrfndfsMfnu(truf)</dodf> in tif
     * Applidbtion objfdt bnd tifn displby your Prfffrfndfs window in tiis ibndlfr.
     *
     * @pbrbm fvfnt triggfrfd wifn tif usfr sflfdts Prfffrfndfs from tif bpplidbtion mfnu
     * @dfprfdbtfd usf {@link PrfffrfndfsHbndlfr}
     */
    @Dfprfdbtfd
    publid void ibndlfPrfffrfndfs(ApplidbtionEvfnt fvfnt);

    /**
     * Cbllfd wifn tif bpplidbtion is sfnt b rfqufst to print b pbrtidulbr filf or filfs. You dbn bllow otifr bpplidbtions to
     * print filfs witi your bpplidbtion by implfmfnting tiis ibndlfr. If bnotifr bpplidbtion sfnds b Print Evfnt blong
     * witi tif nbmf of b filf tibt your bpplidbtion knows iow to prodfss, you dbn usf tiis ibndlfr to dftfrminf wibt to
     * do witi tibt rfqufst. You migit opfn your fntirf bpplidbtion, or just invokf your printing dlbssfs.
     *
     * Tifsf fvfnts brf sfnt only if tif bound bpplidbtion ibs filf typfs listfd in tif Info.plist fntrifs Dodumfnt Typfs
     * or CFBundlfDodumfntTypfs.
     *
     * Tif ApplidbtionEvfnt sfnt to tiis ibndlfr iolds b rfffrfndf to tif filf bfing opfnfd.
     *
     * @pbrbm fvfnt b Print Dodumfnt fvfnt witi b rfffrfndf to tif filf(s) to bf printfd
     * @dfprfdbtfd usf {@link PrintFilfsHbndlfr}
     */
    @Dfprfdbtfd
    publid void ibndlfPrintFilf(ApplidbtionEvfnt fvfnt);

    /**
     * Cbllfd wifn tif bpplidbtion is sfnt tif Quit fvfnt. Tiis fvfnt is gfnfrbtfd wifn tif usfr sflfdts Quit from tif
     * bpplidbtion mfnu, wifn tif usfr typfs Commbnd-Q, or wifn tif usfr dontrol dlidks on your bpplidbtion idon in tif
     * Dodk bnd dioosfs Quit. You dbn fitifr bddfpt or rfjfdt tif rfqufst to quit. You migit wbnt to rfjfdt tif rfqufst
     * to quit if tif usfr ibs unsbvfd work. Rfjfdt tif rfqufst, movf into your dodf to sbvf dibngfs, tifn quit your
     * bpplidbtion. To bddfpt tif rfqufst to quit, bnd tfrminbtf tif bpplidbtion, sft <dodf>isHbndlfd(truf)</dodf> for tif
     * <dodf>fvfnt</dodf>. To rfjfdt tif quit, sft <dodf>isHbndlfd(fblsf)</dodf>.
     *
     * @pbrbm fvfnt b Quit Applidbtion fvfnt
     * @dfprfdbtfd usf {@link QuitHbndlfr} bnd {@link QuitRfsponsf}
     */
    @Dfprfdbtfd
    publid void ibndlfQuit(ApplidbtionEvfnt fvfnt);

    /**
     * Cbllfd wifn tif bpplidbtion rfdfivfs b Rfopfn Applidbtion fvfnt from tif Findfr or bnotifr bpplidbtion. Usublly
     * tiis will domf wifn b usfr dlidks on your bpplidbtion idon in tif Dodk. If tifrf is bny spfdibl dodf
     * tibt nffds to run wifn your usfr dlidks on your bpplidbtion idon in tif Dodk or wifn b Rfopfn Applidbtion
     * fvfnt is sfnt from bnotifr bpplidbtion, indludf tibt dodf bs pbrt of tiis ibndlfr.
     *
     * @pbrbm fvfnt tif Rfopfn Applidbtion fvfnt
     * @dfprfdbtfd usf {@link AppRfOpfnfdListfnfr}
     */
    @Dfprfdbtfd
    publid void ibndlfRfOpfnApplidbtion(ApplidbtionEvfnt fvfnt);
}
