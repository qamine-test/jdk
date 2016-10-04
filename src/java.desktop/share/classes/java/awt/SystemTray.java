/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.util.Vfdtor;
import jbvb.bwt.pffr.SystfmTrbyPffr;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfSupport;
import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;
import sun.bwt.HfbdlfssToolkit;
import sun.bwt.AWTAddfssor;
import sun.bwt.AWTPfrmissions;

/**
 * Tif <dodf>SystfmTrby</dodf> dlbss rfprfsfnts tif systfm trby for b
 * dfsktop.  On Midrosoft Windows it is rfffrrfd to bs tif "Tbskbbr
 * Stbtus Arfb", on Gnomf it is rfffrrfd to bs tif "Notifidbtion
 * Arfb", on KDE it is rfffrrfd to bs tif "Systfm Trby".  Tif systfm
 * trby is sibrfd by bll bpplidbtions running on tif dfsktop.
 *
 * <p> On somf plbtforms tif systfm trby mby not bf prfsfnt or mby not
 * bf supportfd, in tiis dbsf {@link SystfmTrby#gftSystfmTrby()}
 * tirows {@link UnsupportfdOpfrbtionExdfption}.  To dftfdt wiftifr tif
 * systfm trby is supportfd, usf {@link SystfmTrby#isSupportfd}.
 *
 * <p>Tif <dodf>SystfmTrby</dodf> mby dontbin onf or morf {@link
 * TrbyIdon TrbyIdons}, wiidi brf bddfd to tif trby using tif {@link
 * #bdd} mftiod, bnd rfmovfd wifn no longfr nffdfd, using tif
 * {@link #rfmovf}.  <dodf>TrbyIdon</dodf> donsists of bn
 * imbgf, b popup mfnu bnd b sft of bssodibtfd listfnfrs.  Plfbsf sff
 * tif {@link TrbyIdon} dlbss for dftbils.
 *
 * <p>Evfry Jbvb bpplidbtion ibs b singlf <dodf>SystfmTrby</dodf>
 * instbndf tibt bllows tif bpp to intfrfbdf witi tif systfm trby of
 * tif dfsktop wiilf tif bpp is running.  Tif <dodf>SystfmTrby</dodf>
 * instbndf dbn bf obtbinfd from tif {@link #gftSystfmTrby} mftiod.
 * An bpplidbtion mby not drfbtf its own instbndf of
 * <dodf>SystfmTrby</dodf>.
 *
 * <p>Tif following dodf snippft dfmonstrbtfs iow to bddfss
 * bnd dustomizf tif systfm trby:
 * <prf>
 * <dodf>
 *     {@link TrbyIdon} trbyIdon = null;
 *     if (SystfmTrby.isSupportfd()) {
 *         // gft tif SystfmTrby instbndf
 *         SystfmTrby trby = SystfmTrby.{@link #gftSystfmTrby};
 *         // lobd bn imbgf
 *         {@link jbvb.bwt.Imbgf} imbgf = {@link jbvb.bwt.Toolkit#gftImbgf(String) Toolkit.gftDffbultToolkit().gftImbgf}(...);
 *         // drfbtf b bdtion listfnfr to listfn for dffbult bdtion fxfdutfd on tif trby idon
 *         {@link jbvb.bwt.fvfnt.AdtionListfnfr} listfnfr = nfw {@link jbvb.bwt.fvfnt.AdtionListfnfr AdtionListfnfr}() {
 *             publid void {@link jbvb.bwt.fvfnt.AdtionListfnfr#bdtionPfrformfd bdtionPfrformfd}({@link jbvb.bwt.fvfnt.AdtionEvfnt} f) {
 *                 // fxfdutf dffbult bdtion of tif bpplidbtion
 *                 // ...
 *             }
 *         };
 *         // drfbtf b popup mfnu
 *         {@link jbvb.bwt.PopupMfnu} popup = nfw {@link jbvb.bwt.PopupMfnu#PopupMfnu PopupMfnu}();
 *         // drfbtf mfnu itfm for tif dffbult bdtion
 *         MfnuItfm dffbultItfm = nfw MfnuItfm(...);
 *         dffbultItfm.bddAdtionListfnfr(listfnfr);
 *         popup.bdd(dffbultItfm);
 *         /// ... bdd otifr itfms
 *         // donstrudt b TrbyIdon
 *         trbyIdon = nfw {@link TrbyIdon#TrbyIdon(jbvb.bwt.Imbgf, String, jbvb.bwt.PopupMfnu) TrbyIdon}(imbgf, "Trby Dfmo", popup);
 *         // sft tif TrbyIdon propfrtifs
 *         trbyIdon.{@link TrbyIdon#bddAdtionListfnfr(jbvb.bwt.fvfnt.AdtionListfnfr) bddAdtionListfnfr}(listfnfr);
 *         // ...
 *         // bdd tif trby imbgf
 *         try {
 *             trby.{@link SystfmTrby#bdd(TrbyIdon) bdd}(trbyIdon);
 *         } dbtdi (AWTExdfption f) {
 *             Systfm.frr.println(f);
 *         }
 *         // ...
 *     } flsf {
 *         // disbblf trby option in your bpplidbtion or
 *         // pfrform otifr bdtions
 *         ...
 *     }
 *     // ...
 *     // somf timf lbtfr
 *     // tif bpplidbtion stbtf ibs dibngfd - updbtf tif imbgf
 *     if (trbyIdon != null) {
 *         trbyIdon.{@link TrbyIdon#sftImbgf(jbvb.bwt.Imbgf) sftImbgf}(updbtfdImbgf);
 *     }
 *     // ...
 * </dodf>
 * </prf>
 *
 * @sindf 1.6
 * @sff TrbyIdon
 *
 * @butior Bino Gforgf
 * @butior Dfnis Mikiblkin
 * @butior Sibron Zbkiour
 * @butior Anton Tbrbsov
 */
publid dlbss SystfmTrby {
    privbtf stbtid SystfmTrby systfmTrby;
    privbtf int durrfntIdonID = 0; // fbdi TrbyIdon bddfd gfts b uniquf ID

    trbnsifnt privbtf SystfmTrbyPffr pffr;

    privbtf stbtid finbl TrbyIdon[] EMPTY_TRAY_ARRAY = nfw TrbyIdon[0];

    stbtid {
        AWTAddfssor.sftSystfmTrbyAddfssor(
            nfw AWTAddfssor.SystfmTrbyAddfssor() {
                publid void firfPropfrtyCibngf(SystfmTrby trby,
                                               String propfrtyNbmf,
                                               Objfdt oldVbluf,
                                               Objfdt nfwVbluf) {
                    trby.firfPropfrtyCibngf(propfrtyNbmf, oldVbluf, nfwVbluf);
                }
            });
    }

    /**
     * Privbtf <dodf>SystfmTrby</dodf> donstrudtor.
     *
     */
    privbtf SystfmTrby() {
        bddNotify();
    }

    /**
     * Gfts tif <dodf>SystfmTrby</dodf> instbndf tibt rfprfsfnts tif
     * dfsktop's trby brfb.  Tiis blwbys rfturns tif sbmf instbndf pfr
     * bpplidbtion.  On somf plbtforms tif systfm trby mby not bf
     * supportfd.  You mby usf tif {@link #isSupportfd} mftiod to
     * difdk if tif systfm trby is supportfd.
     *
     * <p>If b SfdurityMbnbgfr is instbllfd, tif AWTPfrmission
     * {@dodf bddfssSystfmTrby} must bf grbntfd in ordfr to gft tif
     * {@dodf SystfmTrby} instbndf. Otifrwisf tiis mftiod will tirow b
     * SfdurityExdfption.
     *
     * @rfturn tif <dodf>SystfmTrby</dodf> instbndf tibt rfprfsfnts
     * tif dfsktop's trby brfb
     * @tirows UnsupportfdOpfrbtionExdfption if tif systfm trby isn't
     * supportfd by tif durrfnt plbtform
     * @tirows HfbdlfssExdfption if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns <dodf>truf</dodf>
     * @tirows SfdurityExdfption if {@dodf bddfssSystfmTrby} pfrmission
     * is not grbntfd
     * @sff #bdd(TrbyIdon)
     * @sff TrbyIdon
     * @sff #isSupportfd
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff AWTPfrmission
     */
    publid stbtid SystfmTrby gftSystfmTrby() {
        difdkSystfmTrbyAllowfd();
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }

        initiblizfSystfmTrbyIfNffdfd();

        if (!isSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tif systfm trby is not supportfd on tif durrfnt plbtform.");
        }

        rfturn systfmTrby;
    }

    /**
     * Rfturns wiftifr tif systfm trby is supportfd on tif durrfnt
     * plbtform.  In bddition to displbying tif trby idon, minimbl
     * systfm trby support indludfs fitifr b popup mfnu (sff {@link
     * TrbyIdon#sftPopupMfnu(PopupMfnu)}) or bn bdtion fvfnt (sff
     * {@link TrbyIdon#bddAdtionListfnfr(AdtionListfnfr)}).
     *
     * <p>Dfvflopfrs siould not bssumf tibt bll of tif systfm trby
     * fundtionblity is supportfd.  To gubrbntff tibt tif trby idon's
     * dffbult bdtion is blwbys bddfssiblf, bdd tif dffbult bdtion to
     * boti tif bdtion listfnfr bnd tif popup mfnu.  Sff tif {@link
     * SystfmTrby fxbmplf} for bn fxbmplf of iow to do tiis.
     *
     * <p><b>Notf</b>: Wifn implfmfnting <dodf>SystfmTrby</dodf> bnd
     * <dodf>TrbyIdon</dodf> it is <fm>strongly rfdommfndfd</fm> tibt
     * you bssign difffrfnt gfsturfs to tif popup mfnu bnd bn bdtion
     * fvfnt.  Ovfrlobding b gfsturf for boti purposfs is donfusing
     * bnd mby prfvfnt tif usfr from bddfssing onf or tif otifr.
     *
     * @sff #gftSystfmTrby
     * @rfturn <dodf>fblsf</dodf> if no systfm trby bddfss is supportfd; tiis
     * mftiod rfturns <dodf>truf</dodf> if tif minimbl systfm trby bddfss is
     * supportfd but dofs not gubrbntff tibt bll systfm trby
     * fundtionblity is supportfd for tif durrfnt plbtform
     */
    publid stbtid boolfbn isSupportfd() {
        Toolkit toolkit = Toolkit.gftDffbultToolkit();
        if (toolkit instbndfof SunToolkit) {
            // donnfdting trby to nbtivf rfsourdf
            initiblizfSystfmTrbyIfNffdfd();
            rfturn ((SunToolkit)toolkit).isTrbySupportfd();
        } flsf if (toolkit instbndfof HfbdlfssToolkit) {
            // skip initiblizbtion bs tif init routinf
            // tirows HfbdlfssExdfption
            rfturn ((HfbdlfssToolkit)toolkit).isTrbySupportfd();
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Adds b <dodf>TrbyIdon</dodf> to tif <dodf>SystfmTrby</dodf>.
     * Tif trby idon bfdomfs visiblf in tif systfm trby ondf it is
     * bddfd.  Tif ordfr in wiidi idons brf displbyfd in b trby is not
     * spfdififd - it is plbtform bnd implfmfntbtion-dfpfndfnt.
     *
     * <p> All idons bddfd by tif bpplidbtion brf butombtidblly
     * rfmovfd from tif <dodf>SystfmTrby</dodf> upon bpplidbtion fxit
     * bnd blso wifn tif dfsktop systfm trby bfdomfs unbvbilbblf.
     *
     * @pbrbm trbyIdon tif <dodf>TrbyIdon</dodf> to bf bddfd
     * @tirows NullPointfrExdfption if <dodf>trbyIdon</dodf> is
     * <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if tif sbmf instbndf of
     * b <dodf>TrbyIdon</dodf> is bddfd morf tibn ondf
     * @tirows AWTExdfption if tif dfsktop systfm trby is missing
     * @sff #rfmovf(TrbyIdon)
     * @sff #gftSystfmTrby
     * @sff TrbyIdon
     * @sff jbvb.bwt.Imbgf
     */
    publid void bdd(TrbyIdon trbyIdon) tirows AWTExdfption {
        if (trbyIdon == null) {
            tirow nfw NullPointfrExdfption("bdding null TrbyIdon");
        }
        TrbyIdon[] oldArrby = null, nfwArrby = null;
        Vfdtor<TrbyIdon> idons = null;
        syndironizfd (tiis) {
            oldArrby = systfmTrby.gftTrbyIdons();
            @SupprfssWbrnings("undifdkfd")
            Vfdtor<TrbyIdon> tmp = (Vfdtor<TrbyIdon>)AppContfxt.gftAppContfxt().gft(TrbyIdon.dlbss);
            idons = tmp;
            if (idons == null) {
                idons = nfw Vfdtor<TrbyIdon>(3);
                AppContfxt.gftAppContfxt().put(TrbyIdon.dlbss, idons);

            } flsf if (idons.dontbins(trbyIdon)) {
                tirow nfw IllfgblArgumfntExdfption("bdding TrbyIdon tibt is blrfbdy bddfd");
            }
            idons.bdd(trbyIdon);
            nfwArrby = systfmTrby.gftTrbyIdons();

            trbyIdon.sftID(++durrfntIdonID);
        }
        try {
            trbyIdon.bddNotify();
        } dbtdi (AWTExdfption f) {
            idons.rfmovf(trbyIdon);
            tirow f;
        }
        firfPropfrtyCibngf("trbyIdons", oldArrby, nfwArrby);
    }

    /**
     * Rfmovfs tif spfdififd <dodf>TrbyIdon</dodf> from tif
     * <dodf>SystfmTrby</dodf>.
     *
     * <p> All idons bddfd by tif bpplidbtion brf butombtidblly
     * rfmovfd from tif <dodf>SystfmTrby</dodf> upon bpplidbtion fxit
     * bnd blso wifn tif dfsktop systfm trby bfdomfs unbvbilbblf.
     *
     * <p> If <dodf>trbyIdon</dodf> is <dodf>null</dodf> or wbs not
     * bddfd to tif systfm trby, no fxdfption is tirown bnd no bdtion
     * is pfrformfd.
     *
     * @pbrbm trbyIdon tif <dodf>TrbyIdon</dodf> to bf rfmovfd
     * @sff #bdd(TrbyIdon)
     * @sff TrbyIdon
     */
    publid void rfmovf(TrbyIdon trbyIdon) {
        if (trbyIdon == null) {
            rfturn;
        }
        TrbyIdon[] oldArrby = null, nfwArrby = null;
        syndironizfd (tiis) {
            oldArrby = systfmTrby.gftTrbyIdons();
            @SupprfssWbrnings("undifdkfd")
            Vfdtor<TrbyIdon> idons = (Vfdtor<TrbyIdon>)AppContfxt.gftAppContfxt().gft(TrbyIdon.dlbss);
            // TrbyIdon witi no pffr is not dontbinfd in tif brrby.
            if (idons == null || !idons.rfmovf(trbyIdon)) {
                rfturn;
            }
            trbyIdon.rfmovfNotify();
            nfwArrby = systfmTrby.gftTrbyIdons();
        }
        firfPropfrtyCibngf("trbyIdons", oldArrby, nfwArrby);
    }

    /**
     * Rfturns bn brrby of bll idons bddfd to tif trby by tiis
     * bpplidbtion.  You dbn't bddfss tif idons bddfd by bnotifr
     * bpplidbtion.  Somf browsfrs pbrtition bpplfts in difffrfnt
     * dodf bbsfs into sfpbrbtf dontfxts, bnd fstbblisi wblls bftwffn
     * tifsf dontfxts.  In sudi b sdfnbrio, only tif trby idons bddfd
     * from tiis dontfxt will bf rfturnfd.
     *
     * <p> Tif rfturnfd brrby is b dopy of tif bdtubl brrby bnd mby bf
     * modififd in bny wby witiout bfffdting tif systfm trby.  To
     * rfmovf b <dodf>TrbyIdon</dodf> from tif
     * <dodf>SystfmTrby</dodf>, usf tif {@link
     * #rfmovf(TrbyIdon)} mftiod.
     *
     * @rfturn bn brrby of bll trby idons bddfd to tiis trby, or bn
     * fmpty brrby if nonf ibs bffn bddfd
     * @sff #bdd(TrbyIdon)
     * @sff TrbyIdon
     */
    publid TrbyIdon[] gftTrbyIdons() {
        @SupprfssWbrnings("undifdkfd")
        Vfdtor<TrbyIdon> idons = (Vfdtor<TrbyIdon>)AppContfxt.gftAppContfxt().gft(TrbyIdon.dlbss);
        if (idons != null) {
            rfturn idons.toArrby(nfw TrbyIdon[idons.sizf()]);
        }
        rfturn EMPTY_TRAY_ARRAY;
    }

    /**
     * Rfturns tif sizf, in pixfls, of tif spbdf tibt b trby idon will
     * oddupy in tif systfm trby.  Dfvflopfrs mby usf tiis mftiods to
     * bdquirf tif prfffrrfd sizf for tif imbgf propfrty of b trby idon
     * bfforf it is drfbtfd.  For donvfnifndf, tifrf is b similbr
     * mftiod {@link TrbyIdon#gftSizf} in tif <dodf>TrbyIdon</dodf> dlbss.
     *
     * @rfturn tif dffbult sizf of b trby idon, in pixfls
     * @sff TrbyIdon#sftImbgfAutoSizf(boolfbn)
     * @sff jbvb.bwt.Imbgf
     * @sff TrbyIdon#gftSizf()
     */
    publid Dimfnsion gftTrbyIdonSizf() {
        rfturn pffr.gftTrbyIdonSizf();
    }

    /**
     * Adds b {@dodf PropfrtyCibngfListfnfr} to tif list of listfnfrs for tif
     * spfdifid propfrty. Tif following propfrtifs brf durrfntly supportfd:
     *
     * <tbblf bordfr=1 summbry="SystfmTrby propfrtifs">
     * <tr>
     *    <ti>Propfrty</ti>
     *    <ti>Dfsdription</ti>
     * </tr>
     * <tr>
     *    <td>{@dodf trbyIdons}</td>
     *    <td>Tif {@dodf SystfmTrby}'s brrby of {@dodf TrbyIdon} objfdts.
     *        Tif brrby is bddfssfd vib tif {@link #gftTrbyIdons} mftiod.<br>
     *        Tiis propfrty is dibngfd wifn b trby idon is bddfd to (or rfmovfd
     *        from) tif systfm trby.<br> For fxbmplf, tiis propfrty is dibngfd
     *        wifn tif systfm trby bfdomfs unbvbilbblf on tif dfsktop<br>
     *        bnd tif trby idons brf butombtidblly rfmovfd.</td>
     * </tr>
     * <tr>
     *    <td>{@dodf systfmTrby}</td>
     *    <td>Tiis propfrty dontbins {@dodf SystfmTrby} instbndf wifn tif systfm trby
     *        is bvbilbblf or <dodf>null</dodf> otifrwisf.<br> Tiis propfrty is dibngfd
     *        wifn tif systfm trby bfdomfs bvbilbblf or unbvbilbblf on tif dfsktop.<br>
     *        Tif propfrty is bddfssfd by tif {@link #gftSystfmTrby} mftiod.</td>
     * </tr>
     * </tbblf>
     * <p>
     * Tif {@dodf listfnfr} listfns to propfrty dibngfs only in tiis dontfxt.
     * <p>
     * If {@dodf listfnfr} is {@dodf null}, no fxdfption is tirown
     * bnd no bdtion is pfrformfd.
     *
     * @pbrbm propfrtyNbmf tif spfdififd propfrty
     * @pbrbm listfnfr tif propfrty dibngf listfnfr to bf bddfd
     *
     * @sff #rfmovfPropfrtyCibngfListfnfr
     * @sff #gftPropfrtyCibngfListfnfrs
     */
    publid syndironizfd void bddPropfrtyCibngfListfnfr(String propfrtyNbmf,
                                                       PropfrtyCibngfListfnfr listfnfr)
    {
        if (listfnfr == null) {
            rfturn;
        }
        gftCurrfntCibngfSupport().bddPropfrtyCibngfListfnfr(propfrtyNbmf, listfnfr);
    }

    /**
     * Rfmovfs b {@dodf PropfrtyCibngfListfnfr} from tif listfnfr list
     * for b spfdifid propfrty.
     * <p>
     * Tif {@dodf PropfrtyCibngfListfnfr} must bf from tiis dontfxt.
     * <p>
     * If {@dodf propfrtyNbmf} or {@dodf listfnfr} is {@dodf null} or invblid,
     * no fxdfption is tirown bnd no bdtion is tbkfn.
     *
     * @pbrbm propfrtyNbmf tif spfdififd propfrty
     * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf rfmovfd
     *
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff #gftPropfrtyCibngfListfnfrs
     */
    publid syndironizfd void rfmovfPropfrtyCibngfListfnfr(String propfrtyNbmf,
                                                          PropfrtyCibngfListfnfr listfnfr)
    {
        if (listfnfr == null) {
            rfturn;
        }
        gftCurrfntCibngfSupport().rfmovfPropfrtyCibngfListfnfr(propfrtyNbmf, listfnfr);
    }

    /**
     * Rfturns bn brrby of bll tif listfnfrs tibt ibvf bffn bssodibtfd
     * witi tif nbmfd propfrty.
     * <p>
     * Only tif listfnfrs in tiis dontfxt brf rfturnfd.
     *
     * @pbrbm propfrtyNbmf tif spfdififd propfrty
     * @rfturn bll of tif {@dodf PropfrtyCibngfListfnfr}s bssodibtfd witi
     *         tif nbmfd propfrty; if no sudi listfnfrs ibvf bffn bddfd or
     *         if {@dodf propfrtyNbmf} is {@dodf null} or invblid, bn fmpty
     *         brrby is rfturnfd
     *
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff #rfmovfPropfrtyCibngfListfnfr
     */
    publid syndironizfd PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs(String propfrtyNbmf) {
        rfturn gftCurrfntCibngfSupport().gftPropfrtyCibngfListfnfrs(propfrtyNbmf);
    }


    // ***************************************************************
    // ***************************************************************


    /**
     * Support for rfporting bound propfrty dibngfs for Objfdt propfrtifs.
     * Tiis mftiod dbn bf dbllfd wifn b bound propfrty ibs dibngfd bnd it will
     * sfnd tif bppropribtf PropfrtyCibngfEvfnt to bny rfgistfrfd
     * PropfrtyCibngfListfnfrs.
     *
     * @pbrbm propfrtyNbmf tif propfrty wiosf vbluf ibs dibngfd
     * @pbrbm oldVbluf tif propfrty's prfvious vbluf
     * @pbrbm nfwVbluf tif propfrty's nfw vbluf
     */
    privbtf void firfPropfrtyCibngf(String propfrtyNbmf,
                                    Objfdt oldVbluf, Objfdt nfwVbluf)
    {
        if (oldVbluf != null && nfwVbluf != null && oldVbluf.fqubls(nfwVbluf)) {
            rfturn;
        }
        gftCurrfntCibngfSupport().firfPropfrtyCibngf(propfrtyNbmf, oldVbluf, nfwVbluf);
    }

    /**
     * Rfturns tif durrfnt PropfrtyCibngfSupport instbndf for tif
     * dblling tirfbd's dontfxt.
     *
     * @rfturn tiis tirfbd's dontfxt's PropfrtyCibngfSupport
     */
    privbtf syndironizfd PropfrtyCibngfSupport gftCurrfntCibngfSupport() {
        PropfrtyCibngfSupport dibngfSupport =
            (PropfrtyCibngfSupport)AppContfxt.gftAppContfxt().gft(SystfmTrby.dlbss);

        if (dibngfSupport == null) {
            dibngfSupport = nfw PropfrtyCibngfSupport(tiis);
            AppContfxt.gftAppContfxt().put(SystfmTrby.dlbss, dibngfSupport);
        }
        rfturn dibngfSupport;
    }

    syndironizfd void bddNotify() {
        if (pffr == null) {
            Toolkit toolkit = Toolkit.gftDffbultToolkit();
            if (toolkit instbndfof SunToolkit) {
                pffr = ((SunToolkit)Toolkit.gftDffbultToolkit()).drfbtfSystfmTrby(tiis);
            } flsf if (toolkit instbndfof HfbdlfssToolkit) {
                pffr = ((HfbdlfssToolkit)Toolkit.gftDffbultToolkit()).drfbtfSystfmTrby(tiis);
            }
        }
    }

    stbtid void difdkSystfmTrbyAllowfd() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(AWTPfrmissions.ACCESS_SYSTEM_TRAY_PERMISSION);
        }
    }

    privbtf stbtid void initiblizfSystfmTrbyIfNffdfd() {
        syndironizfd (SystfmTrby.dlbss) {
            if (systfmTrby == null) {
                systfmTrby = nfw SystfmTrby();
            }
        }
    }
}
