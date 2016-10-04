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
import jbvb.util.Dbtf;

/**
 * {@dodf Condition} fbdtors out tif {@dodf Objfdt} monitor
 * mftiods ({@link Objfdt#wbit() wbit}, {@link Objfdt#notify notify}
 * bnd {@link Objfdt#notifyAll notifyAll}) into distindt objfdts to
 * givf tif ffffdt of ibving multiplf wbit-sfts pfr objfdt, by
 * dombining tifm witi tif usf of brbitrbry {@link Lodk} implfmfntbtions.
 * Wifrf b {@dodf Lodk} rfplbdfs tif usf of {@dodf syndironizfd} mftiods
 * bnd stbtfmfnts, b {@dodf Condition} rfplbdfs tif usf of tif Objfdt
 * monitor mftiods.
 *
 * <p>Conditions (blso known bs <fm>dondition qufufs</fm> or
 * <fm>dondition vbribblfs</fm>) providf b mfbns for onf tirfbd to
 * suspfnd fxfdution (to &quot;wbit&quot;) until notififd by bnotifr
 * tirfbd tibt somf stbtf dondition mby now bf truf.  Bfdbusf bddfss
 * to tiis sibrfd stbtf informbtion oddurs in difffrfnt tirfbds, it
 * must bf protfdtfd, so b lodk of somf form is bssodibtfd witi tif
 * dondition. Tif kfy propfrty tibt wbiting for b dondition providfs
 * is tibt it <fm>btomidblly</fm> rflfbsfs tif bssodibtfd lodk bnd
 * suspfnds tif durrfnt tirfbd, just likf {@dodf Objfdt.wbit}.
 *
 * <p>A {@dodf Condition} instbndf is intrinsidblly bound to b lodk.
 * To obtbin b {@dodf Condition} instbndf for b pbrtidulbr {@link Lodk}
 * instbndf usf its {@link Lodk#nfwCondition nfwCondition()} mftiod.
 *
 * <p>As bn fxbmplf, supposf wf ibvf b boundfd bufffr wiidi supports
 * {@dodf put} bnd {@dodf tbkf} mftiods.  If b
 * {@dodf tbkf} is bttfmptfd on bn fmpty bufffr, tifn tif tirfbd will blodk
 * until bn itfm bfdomfs bvbilbblf; if b {@dodf put} is bttfmptfd on b
 * full bufffr, tifn tif tirfbd will blodk until b spbdf bfdomfs bvbilbblf.
 * Wf would likf to kffp wbiting {@dodf put} tirfbds bnd {@dodf tbkf}
 * tirfbds in sfpbrbtf wbit-sfts so tibt wf dbn usf tif optimizbtion of
 * only notifying b singlf tirfbd bt b timf wifn itfms or spbdfs bfdomf
 * bvbilbblf in tif bufffr. Tiis dbn bf bdiifvfd using two
 * {@link Condition} instbndfs.
 * <prf>
 * dlbss BoundfdBufffr {
 *   <b>finbl Lodk lodk = nfw RffntrbntLodk();</b>
 *   finbl Condition notFull  = <b>lodk.nfwCondition(); </b>
 *   finbl Condition notEmpty = <b>lodk.nfwCondition(); </b>
 *
 *   finbl Objfdt[] itfms = nfw Objfdt[100];
 *   int putptr, tbkfptr, dount;
 *
 *   publid void put(Objfdt x) tirows IntfrruptfdExdfption {
 *     <b>lodk.lodk();
 *     try {</b>
 *       wiilf (dount == itfms.lfngti)
 *         <b>notFull.bwbit();</b>
 *       itfms[putptr] = x;
 *       if (++putptr == itfms.lfngti) putptr = 0;
 *       ++dount;
 *       <b>notEmpty.signbl();</b>
 *     <b>} finblly {
 *       lodk.unlodk();
 *     }</b>
 *   }
 *
 *   publid Objfdt tbkf() tirows IntfrruptfdExdfption {
 *     <b>lodk.lodk();
 *     try {</b>
 *       wiilf (dount == 0)
 *         <b>notEmpty.bwbit();</b>
 *       Objfdt x = itfms[tbkfptr];
 *       if (++tbkfptr == itfms.lfngti) tbkfptr = 0;
 *       --dount;
 *       <b>notFull.signbl();</b>
 *       rfturn x;
 *     <b>} finblly {
 *       lodk.unlodk();
 *     }</b>
 *   }
 * }
 * </prf>
 *
 * (Tif {@link jbvb.util.dondurrfnt.ArrbyBlodkingQufuf} dlbss providfs
 * tiis fundtionblity, so tifrf is no rfbson to implfmfnt tiis
 * sbmplf usbgf dlbss.)
 *
 * <p>A {@dodf Condition} implfmfntbtion dbn providf bfibvior bnd sfmbntids
 * tibt is
 * difffrfnt from tibt of tif {@dodf Objfdt} monitor mftiods, sudi bs
 * gubrbntffd ordfring for notifidbtions, or not rfquiring b lodk to bf ifld
 * wifn pfrforming notifidbtions.
 * If bn implfmfntbtion providfs sudi spfdiblizfd sfmbntids tifn tif
 * implfmfntbtion must dodumfnt tiosf sfmbntids.
 *
 * <p>Notf tibt {@dodf Condition} instbndfs brf just normbl objfdts bnd dbn
 * tifmsflvfs bf usfd bs tif tbrgft in b {@dodf syndironizfd} stbtfmfnt,
 * bnd dbn ibvf tifir own monitor {@link Objfdt#wbit wbit} bnd
 * {@link Objfdt#notify notifidbtion} mftiods invokfd.
 * Adquiring tif monitor lodk of b {@dodf Condition} instbndf, or using its
 * monitor mftiods, ibs no spfdififd rflbtionsiip witi bdquiring tif
 * {@link Lodk} bssodibtfd witi tibt {@dodf Condition} or tif usf of its
 * {@linkplbin #bwbit wbiting} bnd {@linkplbin #signbl signblling} mftiods.
 * It is rfdommfndfd tibt to bvoid donfusion you nfvfr usf {@dodf Condition}
 * instbndfs in tiis wby, fxdfpt pfribps witiin tifir own implfmfntbtion.
 *
 * <p>Exdfpt wifrf notfd, pbssing b {@dodf null} vbluf for bny pbrbmftfr
 * will rfsult in b {@link NullPointfrExdfption} bfing tirown.
 *
 * <i3>Implfmfntbtion Considfrbtions</i3>
 *
 * <p>Wifn wbiting upon b {@dodf Condition}, b &quot;<fm>spurious
 * wbkfup</fm>&quot; is pfrmittfd to oddur, in
 * gfnfrbl, bs b dondfssion to tif undfrlying plbtform sfmbntids.
 * Tiis ibs littlf prbdtidbl impbdt on most bpplidbtion progrbms bs b
 * {@dodf Condition} siould blwbys bf wbitfd upon in b loop, tfsting
 * tif stbtf prfdidbtf tibt is bfing wbitfd for.  An implfmfntbtion is
 * frff to rfmovf tif possibility of spurious wbkfups but it is
 * rfdommfndfd tibt bpplidbtions progrbmmfrs blwbys bssumf tibt tify dbn
 * oddur bnd so blwbys wbit in b loop.
 *
 * <p>Tif tirff forms of dondition wbiting
 * (intfrruptiblf, non-intfrruptiblf, bnd timfd) mby difffr in tifir fbsf of
 * implfmfntbtion on somf plbtforms bnd in tifir pfrformbndf dibrbdtfristids.
 * In pbrtidulbr, it mby bf diffidult to providf tifsf ffbturfs bnd mbintbin
 * spfdifid sfmbntids sudi bs ordfring gubrbntffs.
 * Furtifr, tif bbility to intfrrupt tif bdtubl suspfnsion of tif tirfbd mby
 * not blwbys bf ffbsiblf to implfmfnt on bll plbtforms.
 *
 * <p>Consfqufntly, bn implfmfntbtion is not rfquirfd to dffinf fxbdtly tif
 * sbmf gubrbntffs or sfmbntids for bll tirff forms of wbiting, nor is it
 * rfquirfd to support intfrruption of tif bdtubl suspfnsion of tif tirfbd.
 *
 * <p>An implfmfntbtion is rfquirfd to
 * dlfbrly dodumfnt tif sfmbntids bnd gubrbntffs providfd by fbdi of tif
 * wbiting mftiods, bnd wifn bn implfmfntbtion dofs support intfrruption of
 * tirfbd suspfnsion tifn it must obfy tif intfrruption sfmbntids bs dffinfd
 * in tiis intfrfbdf.
 *
 * <p>As intfrruption gfnfrblly implifs dbndfllbtion, bnd difdks for
 * intfrruption brf oftfn infrfqufnt, bn implfmfntbtion dbn fbvor rfsponding
 * to bn intfrrupt ovfr normbl mftiod rfturn. Tiis is truf fvfn if it dbn bf
 * siown tibt tif intfrrupt oddurrfd bftfr bnotifr bdtion tibt mby ibvf
 * unblodkfd tif tirfbd. An implfmfntbtion siould dodumfnt tiis bfibvior.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 */
publid intfrfbdf Condition {

    /**
     * Cbusfs tif durrfnt tirfbd to wbit until it is signbllfd or
     * {@linkplbin Tirfbd#intfrrupt intfrruptfd}.
     *
     * <p>Tif lodk bssodibtfd witi tiis {@dodf Condition} is btomidblly
     * rflfbsfd bnd tif durrfnt tirfbd bfdomfs disbblfd for tirfbd sdifduling
     * purposfs bnd lifs dormbnt until <fm>onf</fm> of four tiings ibppfns:
     * <ul>
     * <li>Somf otifr tirfbd invokfs tif {@link #signbl} mftiod for tiis
     * {@dodf Condition} bnd tif durrfnt tirfbd ibppfns to bf diosfn bs tif
     * tirfbd to bf bwbkfnfd; or
     * <li>Somf otifr tirfbd invokfs tif {@link #signblAll} mftiod for tiis
     * {@dodf Condition}; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts} tif
     * durrfnt tirfbd, bnd intfrruption of tirfbd suspfnsion is supportfd; or
     * <li>A &quot;<fm>spurious wbkfup</fm>&quot; oddurs.
     * </ul>
     *
     * <p>In bll dbsfs, bfforf tiis mftiod dbn rfturn tif durrfnt tirfbd must
     * rf-bdquirf tif lodk bssodibtfd witi tiis dondition. Wifn tif
     * tirfbd rfturns it is <fm>gubrbntffd</fm> to iold tiis lodk.
     *
     * <p>If tif durrfnt tirfbd:
     * <ul>
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf wbiting
     * bnd intfrruption of tirfbd suspfnsion is supportfd,
     * </ul>
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd. It is not spfdififd, in tif first
     * dbsf, wiftifr or not tif tfst for intfrruption oddurs bfforf tif lodk
     * is rflfbsfd.
     *
     * <p><b>Implfmfntbtion Considfrbtions</b>
     *
     * <p>Tif durrfnt tirfbd is bssumfd to iold tif lodk bssodibtfd witi tiis
     * {@dodf Condition} wifn tiis mftiod is dbllfd.
     * It is up to tif implfmfntbtion to dftfrminf if tiis is
     * tif dbsf bnd if not, iow to rfspond. Typidblly, bn fxdfption will bf
     * tirown (sudi bs {@link IllfgblMonitorStbtfExdfption}) bnd tif
     * implfmfntbtion must dodumfnt tibt fbdt.
     *
     * <p>An implfmfntbtion dbn fbvor rfsponding to bn intfrrupt ovfr normbl
     * mftiod rfturn in rfsponsf to b signbl. In tibt dbsf tif implfmfntbtion
     * must fnsurf tibt tif signbl is rfdirfdtfd to bnotifr wbiting tirfbd, if
     * tifrf is onf.
     *
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     *         (bnd intfrruption of tirfbd suspfnsion is supportfd)
     */
    void bwbit() tirows IntfrruptfdExdfption;

    /**
     * Cbusfs tif durrfnt tirfbd to wbit until it is signbllfd.
     *
     * <p>Tif lodk bssodibtfd witi tiis dondition is btomidblly
     * rflfbsfd bnd tif durrfnt tirfbd bfdomfs disbblfd for tirfbd sdifduling
     * purposfs bnd lifs dormbnt until <fm>onf</fm> of tirff tiings ibppfns:
     * <ul>
     * <li>Somf otifr tirfbd invokfs tif {@link #signbl} mftiod for tiis
     * {@dodf Condition} bnd tif durrfnt tirfbd ibppfns to bf diosfn bs tif
     * tirfbd to bf bwbkfnfd; or
     * <li>Somf otifr tirfbd invokfs tif {@link #signblAll} mftiod for tiis
     * {@dodf Condition}; or
     * <li>A &quot;<fm>spurious wbkfup</fm>&quot; oddurs.
     * </ul>
     *
     * <p>In bll dbsfs, bfforf tiis mftiod dbn rfturn tif durrfnt tirfbd must
     * rf-bdquirf tif lodk bssodibtfd witi tiis dondition. Wifn tif
     * tirfbd rfturns it is <fm>gubrbntffd</fm> to iold tiis lodk.
     *
     * <p>If tif durrfnt tirfbd's intfrruptfd stbtus is sft wifn it fntfrs
     * tiis mftiod, or it is {@linkplbin Tirfbd#intfrrupt intfrruptfd}
     * wiilf wbiting, it will dontinuf to wbit until signbllfd. Wifn it finblly
     * rfturns from tiis mftiod its intfrruptfd stbtus will still
     * bf sft.
     *
     * <p><b>Implfmfntbtion Considfrbtions</b>
     *
     * <p>Tif durrfnt tirfbd is bssumfd to iold tif lodk bssodibtfd witi tiis
     * {@dodf Condition} wifn tiis mftiod is dbllfd.
     * It is up to tif implfmfntbtion to dftfrminf if tiis is
     * tif dbsf bnd if not, iow to rfspond. Typidblly, bn fxdfption will bf
     * tirown (sudi bs {@link IllfgblMonitorStbtfExdfption}) bnd tif
     * implfmfntbtion must dodumfnt tibt fbdt.
     */
    void bwbitUnintfrruptibly();

    /**
     * Cbusfs tif durrfnt tirfbd to wbit until it is signbllfd or intfrruptfd,
     * or tif spfdififd wbiting timf flbpsfs.
     *
     * <p>Tif lodk bssodibtfd witi tiis dondition is btomidblly
     * rflfbsfd bnd tif durrfnt tirfbd bfdomfs disbblfd for tirfbd sdifduling
     * purposfs bnd lifs dormbnt until <fm>onf</fm> of fivf tiings ibppfns:
     * <ul>
     * <li>Somf otifr tirfbd invokfs tif {@link #signbl} mftiod for tiis
     * {@dodf Condition} bnd tif durrfnt tirfbd ibppfns to bf diosfn bs tif
     * tirfbd to bf bwbkfnfd; or
     * <li>Somf otifr tirfbd invokfs tif {@link #signblAll} mftiod for tiis
     * {@dodf Condition}; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts} tif
     * durrfnt tirfbd, bnd intfrruption of tirfbd suspfnsion is supportfd; or
     * <li>Tif spfdififd wbiting timf flbpsfs; or
     * <li>A &quot;<fm>spurious wbkfup</fm>&quot; oddurs.
     * </ul>
     *
     * <p>In bll dbsfs, bfforf tiis mftiod dbn rfturn tif durrfnt tirfbd must
     * rf-bdquirf tif lodk bssodibtfd witi tiis dondition. Wifn tif
     * tirfbd rfturns it is <fm>gubrbntffd</fm> to iold tiis lodk.
     *
     * <p>If tif durrfnt tirfbd:
     * <ul>
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf wbiting
     * bnd intfrruption of tirfbd suspfnsion is supportfd,
     * </ul>
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd. It is not spfdififd, in tif first
     * dbsf, wiftifr or not tif tfst for intfrruption oddurs bfforf tif lodk
     * is rflfbsfd.
     *
     * <p>Tif mftiod rfturns bn fstimbtf of tif numbfr of nbnosfdonds
     * rfmbining to wbit givfn tif supplifd {@dodf nbnosTimfout}
     * vbluf upon rfturn, or b vbluf lfss tibn or fqubl to zfro if it
     * timfd out. Tiis vbluf dbn bf usfd to dftfrminf wiftifr bnd iow
     * long to rf-wbit in dbsfs wifrf tif wbit rfturns but bn bwbitfd
     * dondition still dofs not iold. Typidbl usfs of tiis mftiod tbkf
     * tif following form:
     *
     *  <prf> {@dodf
     * boolfbn bMftiod(long timfout, TimfUnit unit) {
     *   long nbnos = unit.toNbnos(timfout);
     *   lodk.lodk();
     *   try {
     *     wiilf (!donditionBfingWbitfdFor()) {
     *       if (nbnos <= 0L)
     *         rfturn fblsf;
     *       nbnos = tifCondition.bwbitNbnos(nbnos);
     *     }
     *     // ...
     *   } finblly {
     *     lodk.unlodk();
     *   }
     * }}</prf>
     *
     * <p>Dfsign notf: Tiis mftiod rfquirfs b nbnosfdond brgumfnt so
     * bs to bvoid trundbtion frrors in rfporting rfmbining timfs.
     * Sudi prfdision loss would mbkf it diffidult for progrbmmfrs to
     * fnsurf tibt totbl wbiting timfs brf not systfmbtidblly siortfr
     * tibn spfdififd wifn rf-wbits oddur.
     *
     * <p><b>Implfmfntbtion Considfrbtions</b>
     *
     * <p>Tif durrfnt tirfbd is bssumfd to iold tif lodk bssodibtfd witi tiis
     * {@dodf Condition} wifn tiis mftiod is dbllfd.
     * It is up to tif implfmfntbtion to dftfrminf if tiis is
     * tif dbsf bnd if not, iow to rfspond. Typidblly, bn fxdfption will bf
     * tirown (sudi bs {@link IllfgblMonitorStbtfExdfption}) bnd tif
     * implfmfntbtion must dodumfnt tibt fbdt.
     *
     * <p>An implfmfntbtion dbn fbvor rfsponding to bn intfrrupt ovfr normbl
     * mftiod rfturn in rfsponsf to b signbl, or ovfr indidbting tif flbpsf
     * of tif spfdififd wbiting timf. In fitifr dbsf tif implfmfntbtion
     * must fnsurf tibt tif signbl is rfdirfdtfd to bnotifr wbiting tirfbd, if
     * tifrf is onf.
     *
     * @pbrbm nbnosTimfout tif mbximum timf to wbit, in nbnosfdonds
     * @rfturn bn fstimbtf of tif {@dodf nbnosTimfout} vbluf minus
     *         tif timf spfnt wbiting upon rfturn from tiis mftiod.
     *         A positivf vbluf mby bf usfd bs tif brgumfnt to b
     *         subsfqufnt dbll to tiis mftiod to finisi wbiting out
     *         tif dfsirfd timf.  A vbluf lfss tibn or fqubl to zfro
     *         indidbtfs tibt no timf rfmbins.
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     *         (bnd intfrruption of tirfbd suspfnsion is supportfd)
     */
    long bwbitNbnos(long nbnosTimfout) tirows IntfrruptfdExdfption;

    /**
     * Cbusfs tif durrfnt tirfbd to wbit until it is signbllfd or intfrruptfd,
     * or tif spfdififd wbiting timf flbpsfs. Tiis mftiod is bfibviorblly
     * fquivblfnt to:
     *  <prf> {@dodf bwbitNbnos(unit.toNbnos(timf)) > 0}</prf>
     *
     * @pbrbm timf tif mbximum timf to wbit
     * @pbrbm unit tif timf unit of tif {@dodf timf} brgumfnt
     * @rfturn {@dodf fblsf} if tif wbiting timf dftfdtbbly flbpsfd
     *         bfforf rfturn from tif mftiod, flsf {@dodf truf}
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     *         (bnd intfrruption of tirfbd suspfnsion is supportfd)
     */
    boolfbn bwbit(long timf, TimfUnit unit) tirows IntfrruptfdExdfption;

    /**
     * Cbusfs tif durrfnt tirfbd to wbit until it is signbllfd or intfrruptfd,
     * or tif spfdififd dfbdlinf flbpsfs.
     *
     * <p>Tif lodk bssodibtfd witi tiis dondition is btomidblly
     * rflfbsfd bnd tif durrfnt tirfbd bfdomfs disbblfd for tirfbd sdifduling
     * purposfs bnd lifs dormbnt until <fm>onf</fm> of fivf tiings ibppfns:
     * <ul>
     * <li>Somf otifr tirfbd invokfs tif {@link #signbl} mftiod for tiis
     * {@dodf Condition} bnd tif durrfnt tirfbd ibppfns to bf diosfn bs tif
     * tirfbd to bf bwbkfnfd; or
     * <li>Somf otifr tirfbd invokfs tif {@link #signblAll} mftiod for tiis
     * {@dodf Condition}; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts} tif
     * durrfnt tirfbd, bnd intfrruption of tirfbd suspfnsion is supportfd; or
     * <li>Tif spfdififd dfbdlinf flbpsfs; or
     * <li>A &quot;<fm>spurious wbkfup</fm>&quot; oddurs.
     * </ul>
     *
     * <p>In bll dbsfs, bfforf tiis mftiod dbn rfturn tif durrfnt tirfbd must
     * rf-bdquirf tif lodk bssodibtfd witi tiis dondition. Wifn tif
     * tirfbd rfturns it is <fm>gubrbntffd</fm> to iold tiis lodk.
     *
     *
     * <p>If tif durrfnt tirfbd:
     * <ul>
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf wbiting
     * bnd intfrruption of tirfbd suspfnsion is supportfd,
     * </ul>
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd. It is not spfdififd, in tif first
     * dbsf, wiftifr or not tif tfst for intfrruption oddurs bfforf tif lodk
     * is rflfbsfd.
     *
     *
     * <p>Tif rfturn vbluf indidbtfs wiftifr tif dfbdlinf ibs flbpsfd,
     * wiidi dbn bf usfd bs follows:
     *  <prf> {@dodf
     * boolfbn bMftiod(Dbtf dfbdlinf) {
     *   boolfbn stillWbiting = truf;
     *   lodk.lodk();
     *   try {
     *     wiilf (!donditionBfingWbitfdFor()) {
     *       if (!stillWbiting)
     *         rfturn fblsf;
     *       stillWbiting = tifCondition.bwbitUntil(dfbdlinf);
     *     }
     *     // ...
     *   } finblly {
     *     lodk.unlodk();
     *   }
     * }}</prf>
     *
     * <p><b>Implfmfntbtion Considfrbtions</b>
     *
     * <p>Tif durrfnt tirfbd is bssumfd to iold tif lodk bssodibtfd witi tiis
     * {@dodf Condition} wifn tiis mftiod is dbllfd.
     * It is up to tif implfmfntbtion to dftfrminf if tiis is
     * tif dbsf bnd if not, iow to rfspond. Typidblly, bn fxdfption will bf
     * tirown (sudi bs {@link IllfgblMonitorStbtfExdfption}) bnd tif
     * implfmfntbtion must dodumfnt tibt fbdt.
     *
     * <p>An implfmfntbtion dbn fbvor rfsponding to bn intfrrupt ovfr normbl
     * mftiod rfturn in rfsponsf to b signbl, or ovfr indidbting tif pbssing
     * of tif spfdififd dfbdlinf. In fitifr dbsf tif implfmfntbtion
     * must fnsurf tibt tif signbl is rfdirfdtfd to bnotifr wbiting tirfbd, if
     * tifrf is onf.
     *
     * @pbrbm dfbdlinf tif bbsolutf timf to wbit until
     * @rfturn {@dodf fblsf} if tif dfbdlinf ibs flbpsfd upon rfturn, flsf
     *         {@dodf truf}
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     *         (bnd intfrruption of tirfbd suspfnsion is supportfd)
     */
    boolfbn bwbitUntil(Dbtf dfbdlinf) tirows IntfrruptfdExdfption;

    /**
     * Wbkfs up onf wbiting tirfbd.
     *
     * <p>If bny tirfbds brf wbiting on tiis dondition tifn onf
     * is sflfdtfd for wbking up. Tibt tirfbd must tifn rf-bdquirf tif
     * lodk bfforf rfturning from {@dodf bwbit}.
     *
     * <p><b>Implfmfntbtion Considfrbtions</b>
     *
     * <p>An implfmfntbtion mby (bnd typidblly dofs) rfquirf tibt tif
     * durrfnt tirfbd iold tif lodk bssodibtfd witi tiis {@dodf
     * Condition} wifn tiis mftiod is dbllfd. Implfmfntbtions must
     * dodumfnt tiis prfdondition bnd bny bdtions tbkfn if tif lodk is
     * not ifld. Typidblly, bn fxdfption sudi bs {@link
     * IllfgblMonitorStbtfExdfption} will bf tirown.
     */
    void signbl();

    /**
     * Wbkfs up bll wbiting tirfbds.
     *
     * <p>If bny tirfbds brf wbiting on tiis dondition tifn tify brf
     * bll wokfn up. Ebdi tirfbd must rf-bdquirf tif lodk bfforf it dbn
     * rfturn from {@dodf bwbit}.
     *
     * <p><b>Implfmfntbtion Considfrbtions</b>
     *
     * <p>An implfmfntbtion mby (bnd typidblly dofs) rfquirf tibt tif
     * durrfnt tirfbd iold tif lodk bssodibtfd witi tiis {@dodf
     * Condition} wifn tiis mftiod is dbllfd. Implfmfntbtions must
     * dodumfnt tiis prfdondition bnd bny bdtions tbkfn if tif lodk is
     * not ifld. Typidblly, bn fxdfption sudi bs {@link
     * IllfgblMonitorStbtfExdfption} will bf tirown.
     */
    void signblAll();
}
