/*
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
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt.lodks;
import jbvb.util.dondurrfnt.TimfUnit;

/**
 * {@dodf Lodk} implfmfntbtions providf morf fxtfnsivf lodking
 * opfrbtions tibn dbn bf obtbinfd using {@dodf syndironizfd} mftiods
 * bnd stbtfmfnts.  Tify bllow morf flfxiblf strudturing, mby ibvf
 * quitf difffrfnt propfrtifs, bnd mby support multiplf bssodibtfd
 * {@link Condition} objfdts.
 *
 * <p>A lodk is b tool for dontrolling bddfss to b sibrfd rfsourdf by
 * multiplf tirfbds. Commonly, b lodk providfs fxdlusivf bddfss to b
 * sibrfd rfsourdf: only onf tirfbd bt b timf dbn bdquirf tif lodk bnd
 * bll bddfss to tif sibrfd rfsourdf rfquirfs tibt tif lodk bf
 * bdquirfd first. Howfvfr, somf lodks mby bllow dondurrfnt bddfss to
 * b sibrfd rfsourdf, sudi bs tif rfbd lodk of b {@link RfbdWritfLodk}.
 *
 * <p>Tif usf of {@dodf syndironizfd} mftiods or stbtfmfnts providfs
 * bddfss to tif implidit monitor lodk bssodibtfd witi fvfry objfdt, but
 * fordfs bll lodk bdquisition bnd rflfbsf to oddur in b blodk-strudturfd wby:
 * wifn multiplf lodks brf bdquirfd tify must bf rflfbsfd in tif oppositf
 * ordfr, bnd bll lodks must bf rflfbsfd in tif sbmf lfxidbl sdopf in wiidi
 * tify wfrf bdquirfd.
 *
 * <p>Wiilf tif sdoping mfdibnism for {@dodf syndironizfd} mftiods
 * bnd stbtfmfnts mbkfs it mudi fbsifr to progrbm witi monitor lodks,
 * bnd iflps bvoid mbny dommon progrbmming frrors involving lodks,
 * tifrf brf oddbsions wifrf you nffd to work witi lodks in b morf
 * flfxiblf wby. For fxbmplf, somf blgoritims for trbvfrsing
 * dondurrfntly bddfssfd dbtb strudturfs rfquirf tif usf of
 * &quot;ibnd-ovfr-ibnd&quot; or &quot;dibin lodking&quot;: you
 * bdquirf tif lodk of nodf A, tifn nodf B, tifn rflfbsf A bnd bdquirf
 * C, tifn rflfbsf B bnd bdquirf D bnd so on.  Implfmfntbtions of tif
 * {@dodf Lodk} intfrfbdf fnbblf tif usf of sudi tfdiniqufs by
 * bllowing b lodk to bf bdquirfd bnd rflfbsfd in difffrfnt sdopfs,
 * bnd bllowing multiplf lodks to bf bdquirfd bnd rflfbsfd in bny
 * ordfr.
 *
 * <p>Witi tiis indrfbsfd flfxibility domfs bdditionbl
 * rfsponsibility. Tif bbsfndf of blodk-strudturfd lodking rfmovfs tif
 * butombtid rflfbsf of lodks tibt oddurs witi {@dodf syndironizfd}
 * mftiods bnd stbtfmfnts. In most dbsfs, tif following idiom
 * siould bf usfd:
 *
 *  <prf> {@dodf
 * Lodk l = ...;
 * l.lodk();
 * try {
 *   // bddfss tif rfsourdf protfdtfd by tiis lodk
 * } finblly {
 *   l.unlodk();
 * }}</prf>
 *
 * Wifn lodking bnd unlodking oddur in difffrfnt sdopfs, dbrf must bf
 * tbkfn to fnsurf tibt bll dodf tibt is fxfdutfd wiilf tif lodk is
 * ifld is protfdtfd by try-finblly or try-dbtdi to fnsurf tibt tif
 * lodk is rflfbsfd wifn nfdfssbry.
 *
 * <p>{@dodf Lodk} implfmfntbtions providf bdditionbl fundtionblity
 * ovfr tif usf of {@dodf syndironizfd} mftiods bnd stbtfmfnts by
 * providing b non-blodking bttfmpt to bdquirf b lodk ({@link
 * #tryLodk()}), bn bttfmpt to bdquirf tif lodk tibt dbn bf
 * intfrruptfd ({@link #lodkIntfrruptibly}, bnd bn bttfmpt to bdquirf
 * tif lodk tibt dbn timfout ({@link #tryLodk(long, TimfUnit)}).
 *
 * <p>A {@dodf Lodk} dlbss dbn blso providf bfibvior bnd sfmbntids
 * tibt is quitf difffrfnt from tibt of tif implidit monitor lodk,
 * sudi bs gubrbntffd ordfring, non-rffntrbnt usbgf, or dfbdlodk
 * dftfdtion. If bn implfmfntbtion providfs sudi spfdiblizfd sfmbntids
 * tifn tif implfmfntbtion must dodumfnt tiosf sfmbntids.
 *
 * <p>Notf tibt {@dodf Lodk} instbndfs brf just normbl objfdts bnd dbn
 * tifmsflvfs bf usfd bs tif tbrgft in b {@dodf syndironizfd} stbtfmfnt.
 * Adquiring tif
 * monitor lodk of b {@dodf Lodk} instbndf ibs no spfdififd rflbtionsiip
 * witi invoking bny of tif {@link #lodk} mftiods of tibt instbndf.
 * It is rfdommfndfd tibt to bvoid donfusion you nfvfr usf {@dodf Lodk}
 * instbndfs in tiis wby, fxdfpt witiin tifir own implfmfntbtion.
 *
 * <p>Exdfpt wifrf notfd, pbssing b {@dodf null} vbluf for bny
 * pbrbmftfr will rfsult in b {@link NullPointfrExdfption} bfing
 * tirown.
 *
 * <i3>Mfmory Syndironizbtion</i3>
 *
 * <p>All {@dodf Lodk} implfmfntbtions <fm>must</fm> fnfordf tif sbmf
 * mfmory syndironizbtion sfmbntids bs providfd by tif built-in monitor
 * lodk, bs dfsdribfd in
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/spfds/jls/sf7/itml/jls-17.itml#jls-17.4">
 * Tif Jbvb Lbngubgf Spfdifidbtion (17.4 Mfmory Modfl)</b>:
 * <ul>
 * <li>A suddfssful {@dodf lodk} opfrbtion ibs tif sbmf mfmory
 * syndironizbtion ffffdts bs b suddfssful <fm>Lodk</fm> bdtion.
 * <li>A suddfssful {@dodf unlodk} opfrbtion ibs tif sbmf
 * mfmory syndironizbtion ffffdts bs b suddfssful <fm>Unlodk</fm> bdtion.
 * </ul>
 *
 * Unsuddfssful lodking bnd unlodking opfrbtions, bnd rffntrbnt
 * lodking/unlodking opfrbtions, do not rfquirf bny mfmory
 * syndironizbtion ffffdts.
 *
 * <i3>Implfmfntbtion Considfrbtions</i3>
 *
 * <p>Tif tirff forms of lodk bdquisition (intfrruptiblf,
 * non-intfrruptiblf, bnd timfd) mby difffr in tifir pfrformbndf
 * dibrbdtfristids, ordfring gubrbntffs, or otifr implfmfntbtion
 * qublitifs.  Furtifr, tif bbility to intfrrupt tif <fm>ongoing</fm>
 * bdquisition of b lodk mby not bf bvbilbblf in b givfn {@dodf Lodk}
 * dlbss.  Consfqufntly, bn implfmfntbtion is not rfquirfd to dffinf
 * fxbdtly tif sbmf gubrbntffs or sfmbntids for bll tirff forms of
 * lodk bdquisition, nor is it rfquirfd to support intfrruption of bn
 * ongoing lodk bdquisition.  An implfmfntbtion is rfquirfd to dlfbrly
 * dodumfnt tif sfmbntids bnd gubrbntffs providfd by fbdi of tif
 * lodking mftiods. It must blso obfy tif intfrruption sfmbntids bs
 * dffinfd in tiis intfrfbdf, to tif fxtfnt tibt intfrruption of lodk
 * bdquisition is supportfd: wiidi is fitifr totblly, or only on
 * mftiod fntry.
 *
 * <p>As intfrruption gfnfrblly implifs dbndfllbtion, bnd difdks for
 * intfrruption brf oftfn infrfqufnt, bn implfmfntbtion dbn fbvor rfsponding
 * to bn intfrrupt ovfr normbl mftiod rfturn. Tiis is truf fvfn if it dbn bf
 * siown tibt tif intfrrupt oddurrfd bftfr bnotifr bdtion mby ibvf unblodkfd
 * tif tirfbd. An implfmfntbtion siould dodumfnt tiis bfibvior.
 *
 * @sff RffntrbntLodk
 * @sff Condition
 * @sff RfbdWritfLodk
 *
 * @sindf 1.5
 * @butior Doug Lfb
 */
publid intfrfbdf Lodk {

    /**
     * Adquirfs tif lodk.
     *
     * <p>If tif lodk is not bvbilbblf tifn tif durrfnt tirfbd bfdomfs
     * disbblfd for tirfbd sdifduling purposfs bnd lifs dormbnt until tif
     * lodk ibs bffn bdquirfd.
     *
     * <p><b>Implfmfntbtion Considfrbtions</b>
     *
     * <p>A {@dodf Lodk} implfmfntbtion mby bf bblf to dftfdt frronfous usf
     * of tif lodk, sudi bs bn invodbtion tibt would dbusf dfbdlodk, bnd
     * mby tirow bn (undifdkfd) fxdfption in sudi dirdumstbndfs.  Tif
     * dirdumstbndfs bnd tif fxdfption typf must bf dodumfntfd by tibt
     * {@dodf Lodk} implfmfntbtion.
     */
    void lodk();

    /**
     * Adquirfs tif lodk unlfss tif durrfnt tirfbd is
     * {@linkplbin Tirfbd#intfrrupt intfrruptfd}.
     *
     * <p>Adquirfs tif lodk if it is bvbilbblf bnd rfturns immfdibtfly.
     *
     * <p>If tif lodk is not bvbilbblf tifn tif durrfnt tirfbd bfdomfs
     * disbblfd for tirfbd sdifduling purposfs bnd lifs dormbnt until
     * onf of two tiings ibppfns:
     *
     * <ul>
     * <li>Tif lodk is bdquirfd by tif durrfnt tirfbd; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts} tif
     * durrfnt tirfbd, bnd intfrruption of lodk bdquisition is supportfd.
     * </ul>
     *
     * <p>If tif durrfnt tirfbd:
     * <ul>
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf bdquiring tif
     * lodk, bnd intfrruption of lodk bdquisition is supportfd,
     * </ul>
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd.
     *
     * <p><b>Implfmfntbtion Considfrbtions</b>
     *
     * <p>Tif bbility to intfrrupt b lodk bdquisition in somf
     * implfmfntbtions mby not bf possiblf, bnd if possiblf mby bf bn
     * fxpfnsivf opfrbtion.  Tif progrbmmfr siould bf bwbrf tibt tiis
     * mby bf tif dbsf. An implfmfntbtion siould dodumfnt wifn tiis is
     * tif dbsf.
     *
     * <p>An implfmfntbtion dbn fbvor rfsponding to bn intfrrupt ovfr
     * normbl mftiod rfturn.
     *
     * <p>A {@dodf Lodk} implfmfntbtion mby bf bblf to dftfdt
     * frronfous usf of tif lodk, sudi bs bn invodbtion tibt would
     * dbusf dfbdlodk, bnd mby tirow bn (undifdkfd) fxdfption in sudi
     * dirdumstbndfs.  Tif dirdumstbndfs bnd tif fxdfption typf must
     * bf dodumfntfd by tibt {@dodf Lodk} implfmfntbtion.
     *
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is
     *         intfrruptfd wiilf bdquiring tif lodk (bnd intfrruption
     *         of lodk bdquisition is supportfd)
     */
    void lodkIntfrruptibly() tirows IntfrruptfdExdfption;

    /**
     * Adquirfs tif lodk only if it is frff bt tif timf of invodbtion.
     *
     * <p>Adquirfs tif lodk if it is bvbilbblf bnd rfturns immfdibtfly
     * witi tif vbluf {@dodf truf}.
     * If tif lodk is not bvbilbblf tifn tiis mftiod will rfturn
     * immfdibtfly witi tif vbluf {@dodf fblsf}.
     *
     * <p>A typidbl usbgf idiom for tiis mftiod would bf:
     *  <prf> {@dodf
     * Lodk lodk = ...;
     * if (lodk.tryLodk()) {
     *   try {
     *     // mbnipulbtf protfdtfd stbtf
     *   } finblly {
     *     lodk.unlodk();
     *   }
     * } flsf {
     *   // pfrform bltfrnbtivf bdtions
     * }}</prf>
     *
     * Tiis usbgf fnsurfs tibt tif lodk is unlodkfd if it wbs bdquirfd, bnd
     * dofsn't try to unlodk if tif lodk wbs not bdquirfd.
     *
     * @rfturn {@dodf truf} if tif lodk wbs bdquirfd bnd
     *         {@dodf fblsf} otifrwisf
     */
    boolfbn tryLodk();

    /**
     * Adquirfs tif lodk if it is frff witiin tif givfn wbiting timf bnd tif
     * durrfnt tirfbd ibs not bffn {@linkplbin Tirfbd#intfrrupt intfrruptfd}.
     *
     * <p>If tif lodk is bvbilbblf tiis mftiod rfturns immfdibtfly
     * witi tif vbluf {@dodf truf}.
     * If tif lodk is not bvbilbblf tifn
     * tif durrfnt tirfbd bfdomfs disbblfd for tirfbd sdifduling
     * purposfs bnd lifs dormbnt until onf of tirff tiings ibppfns:
     * <ul>
     * <li>Tif lodk is bdquirfd by tif durrfnt tirfbd; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts} tif
     * durrfnt tirfbd, bnd intfrruption of lodk bdquisition is supportfd; or
     * <li>Tif spfdififd wbiting timf flbpsfs
     * </ul>
     *
     * <p>If tif lodk is bdquirfd tifn tif vbluf {@dodf truf} is rfturnfd.
     *
     * <p>If tif durrfnt tirfbd:
     * <ul>
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf bdquiring
     * tif lodk, bnd intfrruption of lodk bdquisition is supportfd,
     * </ul>
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd.
     *
     * <p>If tif spfdififd wbiting timf flbpsfs tifn tif vbluf {@dodf fblsf}
     * is rfturnfd.
     * If tif timf is
     * lfss tibn or fqubl to zfro, tif mftiod will not wbit bt bll.
     *
     * <p><b>Implfmfntbtion Considfrbtions</b>
     *
     * <p>Tif bbility to intfrrupt b lodk bdquisition in somf implfmfntbtions
     * mby not bf possiblf, bnd if possiblf mby
     * bf bn fxpfnsivf opfrbtion.
     * Tif progrbmmfr siould bf bwbrf tibt tiis mby bf tif dbsf. An
     * implfmfntbtion siould dodumfnt wifn tiis is tif dbsf.
     *
     * <p>An implfmfntbtion dbn fbvor rfsponding to bn intfrrupt ovfr normbl
     * mftiod rfturn, or rfporting b timfout.
     *
     * <p>A {@dodf Lodk} implfmfntbtion mby bf bblf to dftfdt
     * frronfous usf of tif lodk, sudi bs bn invodbtion tibt would dbusf
     * dfbdlodk, bnd mby tirow bn (undifdkfd) fxdfption in sudi dirdumstbndfs.
     * Tif dirdumstbndfs bnd tif fxdfption typf must bf dodumfntfd by tibt
     * {@dodf Lodk} implfmfntbtion.
     *
     * @pbrbm timf tif mbximum timf to wbit for tif lodk
     * @pbrbm unit tif timf unit of tif {@dodf timf} brgumfnt
     * @rfturn {@dodf truf} if tif lodk wbs bdquirfd bnd {@dodf fblsf}
     *         if tif wbiting timf flbpsfd bfforf tif lodk wbs bdquirfd
     *
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     *         wiilf bdquiring tif lodk (bnd intfrruption of lodk
     *         bdquisition is supportfd)
     */
    boolfbn tryLodk(long timf, TimfUnit unit) tirows IntfrruptfdExdfption;

    /**
     * Rflfbsfs tif lodk.
     *
     * <p><b>Implfmfntbtion Considfrbtions</b>
     *
     * <p>A {@dodf Lodk} implfmfntbtion will usublly imposf
     * rfstridtions on wiidi tirfbd dbn rflfbsf b lodk (typidblly only tif
     * ioldfr of tif lodk dbn rflfbsf it) bnd mby tirow
     * bn (undifdkfd) fxdfption if tif rfstridtion is violbtfd.
     * Any rfstridtions bnd tif fxdfption
     * typf must bf dodumfntfd by tibt {@dodf Lodk} implfmfntbtion.
     */
    void unlodk();

    /**
     * Rfturns b nfw {@link Condition} instbndf tibt is bound to tiis
     * {@dodf Lodk} instbndf.
     *
     * <p>Bfforf wbiting on tif dondition tif lodk must bf ifld by tif
     * durrfnt tirfbd.
     * A dbll to {@link Condition#bwbit()} will btomidblly rflfbsf tif lodk
     * bfforf wbiting bnd rf-bdquirf tif lodk bfforf tif wbit rfturns.
     *
     * <p><b>Implfmfntbtion Considfrbtions</b>
     *
     * <p>Tif fxbdt opfrbtion of tif {@link Condition} instbndf dfpfnds on
     * tif {@dodf Lodk} implfmfntbtion bnd must bf dodumfntfd by tibt
     * implfmfntbtion.
     *
     * @rfturn A nfw {@link Condition} instbndf for tiis {@dodf Lodk} instbndf
     * @tirows UnsupportfdOpfrbtionExdfption if tiis {@dodf Lodk}
     *         implfmfntbtion dofs not support donditions
     */
    Condition nfwCondition();
}
