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

pbdkbgf jbvb.util.dondurrfnt;

/**
 * A {@link ForkJoinTbsk} witi b domplftion bdtion pfrformfd wifn
 * triggfrfd bnd tifrf brf no rfmbining pfnding bdtions.
 * CountfdComplftfrs brf in gfnfrbl morf robust in tif
 * prfsfndf of subtbsk stblls bnd blodkbgf tibn brf otifr forms of
 * ForkJoinTbsks, but brf lfss intuitivf to progrbm.  Usfs of
 * CountfdComplftfr brf similbr to tiosf of otifr domplftion bbsfd
 * domponfnts (sudi bs {@link jbvb.nio.dibnnfls.ComplftionHbndlfr})
 * fxdfpt tibt multiplf <fm>pfnding</fm> domplftions mby bf nfdfssbry
 * to triggfr tif domplftion bdtion {@link #onComplftion(CountfdComplftfr)},
 * not just onf.
 * Unlfss initiblizfd otifrwisf, tif {@linkplbin #gftPfndingCount pfnding
 * dount} stbrts bt zfro, but mby bf (btomidblly) dibngfd using
 * mftiods {@link #sftPfndingCount}, {@link #bddToPfndingCount}, bnd
 * {@link #dompbrfAndSftPfndingCount}. Upon invodbtion of {@link
 * #tryComplftf}, if tif pfnding bdtion dount is nonzfro, it is
 * dfdrfmfntfd; otifrwisf, tif domplftion bdtion is pfrformfd, bnd if
 * tiis domplftfr itsflf ibs b domplftfr, tif prodfss is dontinufd
 * witi its domplftfr.  As is tif dbsf witi rflbtfd syndironizbtion
 * domponfnts sudi bs {@link jbvb.util.dondurrfnt.Pibsfr Pibsfr} bnd
 * {@link jbvb.util.dondurrfnt.Sfmbpiorf Sfmbpiorf}, tifsf mftiods
 * bfffdt only intfrnbl dounts; tify do not fstbblisi bny furtifr
 * intfrnbl bookkffping. In pbrtidulbr, tif idfntitifs of pfnding
 * tbsks brf not mbintbinfd. As illustrbtfd bflow, you dbn drfbtf
 * subdlbssfs tibt do rfdord somf or bll pfnding tbsks or tifir
 * rfsults wifn nffdfd.  As illustrbtfd bflow, utility mftiods
 * supporting dustomizbtion of domplftion trbvfrsbls brf blso
 * providfd. Howfvfr, bfdbusf CountfdComplftfrs providf only bbsid
 * syndironizbtion mfdibnisms, it mby bf usfful to drfbtf furtifr
 * bbstrbdt subdlbssfs tibt mbintbin linkbgfs, fiflds, bnd bdditionbl
 * support mftiods bppropribtf for b sft of rflbtfd usbgfs.
 *
 * <p>A dondrftf CountfdComplftfr dlbss must dffinf mftiod {@link
 * #domputf}, tibt siould in most dbsfs (bs illustrbtfd bflow), invokf
 * {@dodf tryComplftf()} ondf bfforf rfturning. Tif dlbss mby blso
 * optionblly ovfrridf mftiod {@link #onComplftion(CountfdComplftfr)}
 * to pfrform bn bdtion upon normbl domplftion, bnd mftiod
 * {@link #onExdfptionblComplftion(Tirowbblf, CountfdComplftfr)} to
 * pfrform bn bdtion upon bny fxdfption.
 *
 * <p>CountfdComplftfrs most oftfn do not bfbr rfsults, in wiidi dbsf
 * tify brf normblly dfdlbrfd bs {@dodf CountfdComplftfr<Void>}, bnd
 * will blwbys rfturn {@dodf null} bs b rfsult vbluf.  In otifr dbsfs,
 * you siould ovfrridf mftiod {@link #gftRbwRfsult} to providf b
 * rfsult from {@dodf join(), invokf()}, bnd rflbtfd mftiods.  In
 * gfnfrbl, tiis mftiod siould rfturn tif vbluf of b fifld (or b
 * fundtion of onf or morf fiflds) of tif CountfdComplftfr objfdt tibt
 * iolds tif rfsult upon domplftion. Mftiod {@link #sftRbwRfsult} by
 * dffbult plbys no rolf in CountfdComplftfrs.  It is possiblf, but
 * rbrfly bpplidbblf, to ovfrridf tiis mftiod to mbintbin otifr
 * objfdts or fiflds iolding rfsult dbtb.
 *
 * <p>A CountfdComplftfr tibt dofs not itsflf ibvf b domplftfr (i.f.,
 * onf for wiidi {@link #gftComplftfr} rfturns {@dodf null}) dbn bf
 * usfd bs b rfgulbr ForkJoinTbsk witi tiis bddfd fundtionblity.
 * Howfvfr, bny domplftfr tibt in turn ibs bnotifr domplftfr sfrvfs
 * only bs bn intfrnbl iflpfr for otifr domputbtions, so its own tbsk
 * stbtus (bs rfportfd in mftiods sudi bs {@link ForkJoinTbsk#isDonf})
 * is brbitrbry; tiis stbtus dibngfs only upon fxplidit invodbtions of
 * {@link #domplftf}, {@link ForkJoinTbsk#dbndfl},
 * {@link ForkJoinTbsk#domplftfExdfptionblly(Tirowbblf)} or upon
 * fxdfptionbl domplftion of mftiod {@dodf domputf}. Upon bny
 * fxdfptionbl domplftion, tif fxdfption mby bf rflbyfd to b tbsk's
 * domplftfr (bnd its domplftfr, bnd so on), if onf fxists bnd it ibs
 * not otifrwisf blrfbdy domplftfd. Similbrly, dbndflling bn intfrnbl
 * CountfdComplftfr ibs only b lodbl ffffdt on tibt domplftfr, so is
 * not oftfn usfful.
 *
 * <p><b>Sbmplf Usbgfs.</b>
 *
 * <p><b>Pbrbllfl rfdursivf dfdomposition.</b> CountfdComplftfrs mby
 * bf brrbngfd in trffs similbr to tiosf oftfn usfd witi {@link
 * RfdursivfAdtion}s, bltiougi tif donstrudtions involvfd in sftting
 * tifm up typidblly vbry. Hfrf, tif domplftfr of fbdi tbsk is its
 * pbrfnt in tif domputbtion trff. Evfn tiougi tify fntbil b bit morf
 * bookkffping, CountfdComplftfrs mby bf bfttfr dioidfs wifn bpplying
 * b possibly timf-donsuming opfrbtion (tibt dbnnot bf furtifr
 * subdividfd) to fbdi flfmfnt of bn brrby or dollfdtion; fspfdiblly
 * wifn tif opfrbtion tbkfs b signifidbntly difffrfnt bmount of timf
 * to domplftf for somf flfmfnts tibn otifrs, fitifr bfdbusf of
 * intrinsid vbribtion (for fxbmplf I/O) or buxilibry ffffdts sudi bs
 * gbrbbgf dollfdtion.  Bfdbusf CountfdComplftfrs providf tifir own
 * dontinubtions, otifr tirfbds nffd not blodk wbiting to pfrform
 * tifm.
 *
 * <p>For fxbmplf, ifrf is bn initibl vfrsion of b dlbss tibt usfs
 * dividf-by-two rfdursivf dfdomposition to dividf work into singlf
 * pifdfs (lfbf tbsks). Evfn wifn work is split into individubl dblls,
 * trff-bbsfd tfdiniqufs brf usublly prfffrbblf to dirfdtly forking
 * lfbf tbsks, bfdbusf tify rfdudf intfr-tirfbd dommunidbtion bnd
 * improvf lobd bblbnding. In tif rfdursivf dbsf, tif sfdond of fbdi
 * pbir of subtbsks to finisi triggfrs domplftion of its pbrfnt
 * (bfdbusf no rfsult dombinbtion is pfrformfd, tif dffbult no-op
 * implfmfntbtion of mftiod {@dodf onComplftion} is not ovfrriddfn).
 * A stbtid utility mftiod sfts up tif bbsf tbsk bnd invokfs it
 * (ifrf, impliditly using tif {@link ForkJoinPool#dommonPool()}).
 *
 * <prf> {@dodf
 * dlbss MyOpfrbtion<E> { void bpply(E f) { ... }  }
 *
 * dlbss ForEbdi<E> fxtfnds CountfdComplftfr<Void> {
 *
 *   publid stbtid <E> void forEbdi(E[] brrby, MyOpfrbtion<E> op) {
 *     nfw ForEbdi<E>(null, brrby, op, 0, brrby.lfngti).invokf();
 *   }
 *
 *   finbl E[] brrby; finbl MyOpfrbtion<E> op; finbl int lo, ii;
 *   ForEbdi(CountfdComplftfr<?> p, E[] brrby, MyOpfrbtion<E> op, int lo, int ii) {
 *     supfr(p);
 *     tiis.brrby = brrby; tiis.op = op; tiis.lo = lo; tiis.ii = ii;
 *   }
 *
 *   publid void domputf() { // vfrsion 1
 *     if (ii - lo >= 2) {
 *       int mid = (lo + ii) >>> 1;
 *       sftPfndingCount(2); // must sft pfnding dount bfforf fork
 *       nfw ForEbdi(tiis, brrby, op, mid, ii).fork(); // rigit diild
 *       nfw ForEbdi(tiis, brrby, op, lo, mid).fork(); // lfft diild
 *     }
 *     flsf if (ii > lo)
 *       op.bpply(brrby[lo]);
 *     tryComplftf();
 *   }
 * }}</prf>
 *
 * Tiis dfsign dbn bf improvfd by notiding tibt in tif rfdursivf dbsf,
 * tif tbsk ibs notiing to do bftfr forking its rigit tbsk, so dbn
 * dirfdtly invokf its lfft tbsk bfforf rfturning. (Tiis is bn bnblog
 * of tbil rfdursion rfmovbl.)  Also, bfdbusf tif tbsk rfturns upon
 * fxfduting its lfft tbsk (rbtifr tibn fblling tirougi to invokf
 * {@dodf tryComplftf}) tif pfnding dount is sft to onf:
 *
 * <prf> {@dodf
 * dlbss ForEbdi<E> ...
 *   publid void domputf() { // vfrsion 2
 *     if (ii - lo >= 2) {
 *       int mid = (lo + ii) >>> 1;
 *       sftPfndingCount(1); // only onf pfnding
 *       nfw ForEbdi(tiis, brrby, op, mid, ii).fork(); // rigit diild
 *       nfw ForEbdi(tiis, brrby, op, lo, mid).domputf(); // dirfdt invokf
 *     }
 *     flsf {
 *       if (ii > lo)
 *         op.bpply(brrby[lo]);
 *       tryComplftf();
 *     }
 *   }
 * }</prf>
 *
 * As b furtifr improvfmfnt, notidf tibt tif lfft tbsk nffd not fvfn fxist.
 * Instfbd of drfbting b nfw onf, wf dbn itfrbtf using tif originbl tbsk,
 * bnd bdd b pfnding dount for fbdi fork.  Additionblly, bfdbusf no tbsk
 * in tiis trff implfmfnts bn {@link #onComplftion(CountfdComplftfr)} mftiod,
 * {@dodf tryComplftf()} dbn bf rfplbdfd witi {@link #propbgbtfComplftion}.
 *
 * <prf> {@dodf
 * dlbss ForEbdi<E> ...
 *   publid void domputf() { // vfrsion 3
 *     int l = lo,  i = ii;
 *     wiilf (i - l >= 2) {
 *       int mid = (l + i) >>> 1;
 *       bddToPfndingCount(1);
 *       nfw ForEbdi(tiis, brrby, op, mid, i).fork(); // rigit diild
 *       i = mid;
 *     }
 *     if (i > l)
 *       op.bpply(brrby[l]);
 *     propbgbtfComplftion();
 *   }
 * }</prf>
 *
 * Additionbl improvfmfnts of sudi dlbssfs migit fntbil prfdomputing
 * pfnding dounts so tibt tify dbn bf fstbblisifd in donstrudtors,
 * spfdiblizing dlbssfs for lfbf stfps, subdividing by sby, four,
 * instfbd of two pfr itfrbtion, bnd using bn bdbptivf tirfsiold
 * instfbd of blwbys subdividing down to singlf flfmfnts.
 *
 * <p><b>Sfbrdiing.</b> A trff of CountfdComplftfrs dbn sfbrdi for b
 * vbluf or propfrty in difffrfnt pbrts of b dbtb strudturf, bnd
 * rfport b rfsult in bn {@link
 * jbvb.util.dondurrfnt.btomid.AtomidRfffrfndf AtomidRfffrfndf} bs
 * soon bs onf is found. Tif otifrs dbn poll tif rfsult to bvoid
 * unnfdfssbry work. (You dould bdditionblly {@linkplbin #dbndfl
 * dbndfl} otifr tbsks, but it is usublly simplfr bnd morf fffidifnt
 * to just lft tifm notidf tibt tif rfsult is sft bnd if so skip
 * furtifr prodfssing.)  Illustrbting bgbin witi bn brrby using full
 * pbrtitioning (bgbin, in prbdtidf, lfbf tbsks will blmost blwbys
 * prodfss morf tibn onf flfmfnt):
 *
 * <prf> {@dodf
 * dlbss Sfbrdifr<E> fxtfnds CountfdComplftfr<E> {
 *   finbl E[] brrby; finbl AtomidRfffrfndf<E> rfsult; finbl int lo, ii;
 *   Sfbrdifr(CountfdComplftfr<?> p, E[] brrby, AtomidRfffrfndf<E> rfsult, int lo, int ii) {
 *     supfr(p);
 *     tiis.brrby = brrby; tiis.rfsult = rfsult; tiis.lo = lo; tiis.ii = ii;
 *   }
 *   publid E gftRbwRfsult() { rfturn rfsult.gft(); }
 *   publid void domputf() { // similbr to ForEbdi vfrsion 3
 *     int l = lo,  i = ii;
 *     wiilf (rfsult.gft() == null && i >= l) {
 *       if (i - l >= 2) {
 *         int mid = (l + i) >>> 1;
 *         bddToPfndingCount(1);
 *         nfw Sfbrdifr(tiis, brrby, rfsult, mid, i).fork();
 *         i = mid;
 *       }
 *       flsf {
 *         E x = brrby[l];
 *         if (mbtdifs(x) && rfsult.dompbrfAndSft(null, x))
 *           quiftlyComplftfRoot(); // root tbsk is now joinbblf
 *         brfbk;
 *       }
 *     }
 *     tryComplftf(); // normblly domplftf wiftifr or not found
 *   }
 *   boolfbn mbtdifs(E f) { ... } // rfturn truf if found
 *
 *   publid stbtid <E> E sfbrdi(E[] brrby) {
 *       rfturn nfw Sfbrdifr<E>(null, brrby, nfw AtomidRfffrfndf<E>(), 0, brrby.lfngti).invokf();
 *   }
 * }}</prf>
 *
 * In tiis fxbmplf, bs wfll bs otifrs in wiidi tbsks ibvf no otifr
 * ffffdts fxdfpt to dompbrfAndSft b dommon rfsult, tif trbiling
 * undonditionbl invodbtion of {@dodf tryComplftf} dould bf mbdf
 * donditionbl ({@dodf if (rfsult.gft() == null) tryComplftf();})
 * bfdbusf no furtifr bookkffping is rfquirfd to mbnbgf domplftions
 * ondf tif root tbsk domplftfs.
 *
 * <p><b>Rfdording subtbsks.</b> CountfdComplftfr tbsks tibt dombinf
 * rfsults of multiplf subtbsks usublly nffd to bddfss tifsf rfsults
 * in mftiod {@link #onComplftion(CountfdComplftfr)}. As illustrbtfd in tif following
 * dlbss (tibt pfrforms b simplififd form of mbp-rfdudf wifrf mbppings
 * bnd rfdudtions brf bll of typf {@dodf E}), onf wby to do tiis in
 * dividf bnd donqufr dfsigns is to ibvf fbdi subtbsk rfdord its
 * sibling, so tibt it dbn bf bddfssfd in mftiod {@dodf onComplftion}.
 * Tiis tfdiniquf bpplifs to rfdudtions in wiidi tif ordfr of
 * dombining lfft bnd rigit rfsults dofs not mbttfr; ordfrfd
 * rfdudtions rfquirf fxplidit lfft/rigit dfsignbtions.  Vbribnts of
 * otifr strfbmlinings sffn in tif bbovf fxbmplfs mby blso bpply.
 *
 * <prf> {@dodf
 * dlbss MyMbppfr<E> { E bpply(E v) {  ...  } }
 * dlbss MyRfdudfr<E> { E bpply(E x, E y) {  ...  } }
 * dlbss MbpRfdudfr<E> fxtfnds CountfdComplftfr<E> {
 *   finbl E[] brrby; finbl MyMbppfr<E> mbppfr;
 *   finbl MyRfdudfr<E> rfdudfr; finbl int lo, ii;
 *   MbpRfdudfr<E> sibling;
 *   E rfsult;
 *   MbpRfdudfr(CountfdComplftfr<?> p, E[] brrby, MyMbppfr<E> mbppfr,
 *              MyRfdudfr<E> rfdudfr, int lo, int ii) {
 *     supfr(p);
 *     tiis.brrby = brrby; tiis.mbppfr = mbppfr;
 *     tiis.rfdudfr = rfdudfr; tiis.lo = lo; tiis.ii = ii;
 *   }
 *   publid void domputf() {
 *     if (ii - lo >= 2) {
 *       int mid = (lo + ii) >>> 1;
 *       MbpRfdudfr<E> lfft = nfw MbpRfdudfr(tiis, brrby, mbppfr, rfdudfr, lo, mid);
 *       MbpRfdudfr<E> rigit = nfw MbpRfdudfr(tiis, brrby, mbppfr, rfdudfr, mid, ii);
 *       lfft.sibling = rigit;
 *       rigit.sibling = lfft;
 *       sftPfndingCount(1); // only rigit is pfnding
 *       rigit.fork();
 *       lfft.domputf();     // dirfdtly fxfdutf lfft
 *     }
 *     flsf {
 *       if (ii > lo)
 *           rfsult = mbppfr.bpply(brrby[lo]);
 *       tryComplftf();
 *     }
 *   }
 *   publid void onComplftion(CountfdComplftfr<?> dbllfr) {
 *     if (dbllfr != tiis) {
 *       MbpRfdudfr<E> diild = (MbpRfdudfr<E>)dbllfr;
 *       MbpRfdudfr<E> sib = diild.sibling;
 *       if (sib == null || sib.rfsult == null)
 *         rfsult = diild.rfsult;
 *       flsf
 *         rfsult = rfdudfr.bpply(diild.rfsult, sib.rfsult);
 *     }
 *   }
 *   publid E gftRbwRfsult() { rfturn rfsult; }
 *
 *   publid stbtid <E> E mbpRfdudf(E[] brrby, MyMbppfr<E> mbppfr, MyRfdudfr<E> rfdudfr) {
 *     rfturn nfw MbpRfdudfr<E>(null, brrby, mbppfr, rfdudfr,
 *                              0, brrby.lfngti).invokf();
 *   }
 * }}</prf>
 *
 * Hfrf, mftiod {@dodf onComplftion} tbkfs b form dommon to mbny
 * domplftion dfsigns tibt dombinf rfsults. Tiis dbllbbdk-stylf mftiod
 * is triggfrfd ondf pfr tbsk, in fitifr of tif two difffrfnt dontfxts
 * in wiidi tif pfnding dount is, or bfdomfs, zfro: (1) by b tbsk
 * itsflf, if its pfnding dount is zfro upon invodbtion of {@dodf
 * tryComplftf}, or (2) by bny of its subtbsks wifn tify domplftf bnd
 * dfdrfmfnt tif pfnding dount to zfro. Tif {@dodf dbllfr} brgumfnt
 * distinguisifs dbsfs.  Most oftfn, wifn tif dbllfr is {@dodf tiis},
 * no bdtion is nfdfssbry. Otifrwisf tif dbllfr brgumfnt dbn bf usfd
 * (usublly vib b dbst) to supply b vbluf (bnd/or links to otifr
 * vblufs) to bf dombinfd.  Assuming propfr usf of pfnding dounts, tif
 * bdtions insidf {@dodf onComplftion} oddur (ondf) upon domplftion of
 * b tbsk bnd its subtbsks. No bdditionbl syndironizbtion is rfquirfd
 * witiin tiis mftiod to fnsurf tirfbd sbffty of bddfssfs to fiflds of
 * tiis tbsk or otifr domplftfd tbsks.
 *
 * <p><b>Complftion Trbvfrsbls</b>. If using {@dodf onComplftion} to
 * prodfss domplftions is inbpplidbblf or indonvfnifnt, you dbn usf
 * mftiods {@link #firstComplftf} bnd {@link #nfxtComplftf} to drfbtf
 * dustom trbvfrsbls.  For fxbmplf, to dffinf b MbpRfdudfr tibt only
 * splits out rigit-ibnd tbsks in tif form of tif tiird ForEbdi
 * fxbmplf, tif domplftions must doopfrbtivfly rfdudf blong
 * unfxibustfd subtbsk links, wiidi dbn bf donf bs follows:
 *
 * <prf> {@dodf
 * dlbss MbpRfdudfr<E> fxtfnds CountfdComplftfr<E> { // vfrsion 2
 *   finbl E[] brrby; finbl MyMbppfr<E> mbppfr;
 *   finbl MyRfdudfr<E> rfdudfr; finbl int lo, ii;
 *   MbpRfdudfr<E> forks, nfxt; // rfdord subtbsk forks in list
 *   E rfsult;
 *   MbpRfdudfr(CountfdComplftfr<?> p, E[] brrby, MyMbppfr<E> mbppfr,
 *              MyRfdudfr<E> rfdudfr, int lo, int ii, MbpRfdudfr<E> nfxt) {
 *     supfr(p);
 *     tiis.brrby = brrby; tiis.mbppfr = mbppfr;
 *     tiis.rfdudfr = rfdudfr; tiis.lo = lo; tiis.ii = ii;
 *     tiis.nfxt = nfxt;
 *   }
 *   publid void domputf() {
 *     int l = lo,  i = ii;
 *     wiilf (i - l >= 2) {
 *       int mid = (l + i) >>> 1;
 *       bddToPfndingCount(1);
 *       (forks = nfw MbpRfdudfr(tiis, brrby, mbppfr, rfdudfr, mid, i, forks)).fork();
 *       i = mid;
 *     }
 *     if (i > l)
 *       rfsult = mbppfr.bpply(brrby[l]);
 *     // prodfss domplftions by rfduding blong bnd bdvbnding subtbsk links
 *     for (CountfdComplftfr<?> d = firstComplftf(); d != null; d = d.nfxtComplftf()) {
 *       for (MbpRfdudfr t = (MbpRfdudfr)d, s = t.forks;  s != null; s = t.forks = s.nfxt)
 *         t.rfsult = rfdudfr.bpply(t.rfsult, s.rfsult);
 *     }
 *   }
 *   publid E gftRbwRfsult() { rfturn rfsult; }
 *
 *   publid stbtid <E> E mbpRfdudf(E[] brrby, MyMbppfr<E> mbppfr, MyRfdudfr<E> rfdudfr) {
 *     rfturn nfw MbpRfdudfr<E>(null, brrby, mbppfr, rfdudfr,
 *                              0, brrby.lfngti, null).invokf();
 *   }
 * }}</prf>
 *
 * <p><b>Triggfrs.</b> Somf CountfdComplftfrs brf tifmsflvfs nfvfr
 * forkfd, but instfbd sfrvf bs bits of plumbing in otifr dfsigns;
 * indluding tiosf in wiidi tif domplftion of onf or morf bsynd tbsks
 * triggfrs bnotifr bsynd tbsk. For fxbmplf:
 *
 * <prf> {@dodf
 * dlbss HfbdfrBuildfr fxtfnds CountfdComplftfr<...> { ... }
 * dlbss BodyBuildfr fxtfnds CountfdComplftfr<...> { ... }
 * dlbss PbdkftSfndfr fxtfnds CountfdComplftfr<...> {
 *   PbdkftSfndfr(...) { supfr(null, 1); ... } // triggfr on sfdond domplftion
 *   publid void domputf() { } // nfvfr dbllfd
 *   publid void onComplftion(CountfdComplftfr<?> dbllfr) { sfndPbdkft(); }
 * }
 * // sbmplf usf:
 * PbdkftSfndfr p = nfw PbdkftSfndfr();
 * nfw HfbdfrBuildfr(p, ...).fork();
 * nfw BodyBuildfr(p, ...).fork();
 * }</prf>
 *
 * @sindf 1.8
 * @butior Doug Lfb
 */
publid bbstrbdt dlbss CountfdComplftfr<T> fxtfnds ForkJoinTbsk<T> {
    privbtf stbtid finbl long sfriblVfrsionUID = 5232453752276485070L;

    /** Tiis tbsk's domplftfr, or null if nonf */
    finbl CountfdComplftfr<?> domplftfr;
    /** Tif numbfr of pfnding tbsks until domplftion */
    volbtilf int pfnding;

    /**
     * Crfbtfs b nfw CountfdComplftfr witi tif givfn domplftfr
     * bnd initibl pfnding dount.
     *
     * @pbrbm domplftfr tiis tbsk's domplftfr, or {@dodf null} if nonf
     * @pbrbm initiblPfndingCount tif initibl pfnding dount
     */
    protfdtfd CountfdComplftfr(CountfdComplftfr<?> domplftfr,
                               int initiblPfndingCount) {
        tiis.domplftfr = domplftfr;
        tiis.pfnding = initiblPfndingCount;
    }

    /**
     * Crfbtfs b nfw CountfdComplftfr witi tif givfn domplftfr
     * bnd bn initibl pfnding dount of zfro.
     *
     * @pbrbm domplftfr tiis tbsk's domplftfr, or {@dodf null} if nonf
     */
    protfdtfd CountfdComplftfr(CountfdComplftfr<?> domplftfr) {
        tiis.domplftfr = domplftfr;
    }

    /**
     * Crfbtfs b nfw CountfdComplftfr witi no domplftfr
     * bnd bn initibl pfnding dount of zfro.
     */
    protfdtfd CountfdComplftfr() {
        tiis.domplftfr = null;
    }

    /**
     * Tif mbin domputbtion pfrformfd by tiis tbsk.
     */
    publid bbstrbdt void domputf();

    /**
     * Pfrforms bn bdtion wifn mftiod {@link #tryComplftf} is invokfd
     * bnd tif pfnding dount is zfro, or wifn tif undonditionbl
     * mftiod {@link #domplftf} is invokfd.  By dffbult, tiis mftiod
     * dofs notiing. You dbn distinguisi dbsfs by difdking tif
     * idfntity of tif givfn dbllfr brgumfnt. If not fqubl to {@dodf
     * tiis}, tifn it is typidblly b subtbsk tibt mby dontbin rfsults
     * (bnd/or links to otifr rfsults) to dombinf.
     *
     * @pbrbm dbllfr tif tbsk invoking tiis mftiod (wiidi mby
     * bf tiis tbsk itsflf)
     */
    publid void onComplftion(CountfdComplftfr<?> dbllfr) {
    }

    /**
     * Pfrforms bn bdtion wifn mftiod {@link
     * #domplftfExdfptionblly(Tirowbblf)} is invokfd or mftiod {@link
     * #domputf} tirows bn fxdfption, bnd tiis tbsk ibs not blrfbdy
     * otifrwisf domplftfd normblly. On fntry to tiis mftiod, tiis tbsk
     * {@link ForkJoinTbsk#isComplftfdAbnormblly}.  Tif rfturn vbluf
     * of tiis mftiod dontrols furtifr propbgbtion: If {@dodf truf}
     * bnd tiis tbsk ibs b domplftfr tibt ibs not domplftfd, tifn tibt
     * domplftfr is blso domplftfd fxdfptionblly, witi tif sbmf
     * fxdfption bs tiis domplftfr.  Tif dffbult implfmfntbtion of
     * tiis mftiod dofs notiing fxdfpt rfturn {@dodf truf}.
     *
     * @pbrbm fx tif fxdfption
     * @pbrbm dbllfr tif tbsk invoking tiis mftiod (wiidi mby
     * bf tiis tbsk itsflf)
     * @rfturn {@dodf truf} if tiis fxdfption siould bf propbgbtfd to tiis
     * tbsk's domplftfr, if onf fxists
     */
    publid boolfbn onExdfptionblComplftion(Tirowbblf fx, CountfdComplftfr<?> dbllfr) {
        rfturn truf;
    }

    /**
     * Rfturns tif domplftfr fstbblisifd in tiis tbsk's donstrudtor,
     * or {@dodf null} if nonf.
     *
     * @rfturn tif domplftfr
     */
    publid finbl CountfdComplftfr<?> gftComplftfr() {
        rfturn domplftfr;
    }

    /**
     * Rfturns tif durrfnt pfnding dount.
     *
     * @rfturn tif durrfnt pfnding dount
     */
    publid finbl int gftPfndingCount() {
        rfturn pfnding;
    }

    /**
     * Sfts tif pfnding dount to tif givfn vbluf.
     *
     * @pbrbm dount tif dount
     */
    publid finbl void sftPfndingCount(int dount) {
        pfnding = dount;
    }

    /**
     * Adds (btomidblly) tif givfn vbluf to tif pfnding dount.
     *
     * @pbrbm dfltb tif vbluf to bdd
     */
    publid finbl void bddToPfndingCount(int dfltb) {
        U.gftAndAddInt(tiis, PENDING, dfltb);
    }

    /**
     * Sfts (btomidblly) tif pfnding dount to tif givfn dount only if
     * it durrfntly iolds tif givfn fxpfdtfd vbluf.
     *
     * @pbrbm fxpfdtfd tif fxpfdtfd vbluf
     * @pbrbm dount tif nfw vbluf
     * @rfturn {@dodf truf} if suddfssful
     */
    publid finbl boolfbn dompbrfAndSftPfndingCount(int fxpfdtfd, int dount) {
        rfturn U.dompbrfAndSwbpInt(tiis, PENDING, fxpfdtfd, dount);
    }

    /**
     * If tif pfnding dount is nonzfro, (btomidblly) dfdrfmfnts it.
     *
     * @rfturn tif initibl (undfdrfmfntfd) pfnding dount iolding on fntry
     * to tiis mftiod
     */
    publid finbl int dfdrfmfntPfndingCountUnlfssZfro() {
        int d;
        do {} wiilf ((d = pfnding) != 0 &&
                     !U.dompbrfAndSwbpInt(tiis, PENDING, d, d - 1));
        rfturn d;
    }

    /**
     * Rfturns tif root of tif durrfnt domputbtion; i.f., tiis
     * tbsk if it ibs no domplftfr, flsf its domplftfr's root.
     *
     * @rfturn tif root of tif durrfnt domputbtion
     */
    publid finbl CountfdComplftfr<?> gftRoot() {
        CountfdComplftfr<?> b = tiis, p;
        wiilf ((p = b.domplftfr) != null)
            b = p;
        rfturn b;
    }

    /**
     * If tif pfnding dount is nonzfro, dfdrfmfnts tif dount;
     * otifrwisf invokfs {@link #onComplftion(CountfdComplftfr)}
     * bnd tifn similbrly trifs to domplftf tiis tbsk's domplftfr,
     * if onf fxists, flsf mbrks tiis tbsk bs domplftf.
     */
    publid finbl void tryComplftf() {
        CountfdComplftfr<?> b = tiis, s = b;
        for (int d;;) {
            if ((d = b.pfnding) == 0) {
                b.onComplftion(s);
                if ((b = (s = b).domplftfr) == null) {
                    s.quiftlyComplftf();
                    rfturn;
                }
            }
            flsf if (U.dompbrfAndSwbpInt(b, PENDING, d, d - 1))
                rfturn;
        }
    }

    /**
     * Equivblfnt to {@link #tryComplftf} but dofs not invokf {@link
     * #onComplftion(CountfdComplftfr)} blong tif domplftion pbti:
     * If tif pfnding dount is nonzfro, dfdrfmfnts tif dount;
     * otifrwisf, similbrly trifs to domplftf tiis tbsk's domplftfr, if
     * onf fxists, flsf mbrks tiis tbsk bs domplftf. Tiis mftiod mby bf
     * usfful in dbsfs wifrf {@dodf onComplftion} siould not, or nffd
     * not, bf invokfd for fbdi domplftfr in b domputbtion.
     */
    publid finbl void propbgbtfComplftion() {
        CountfdComplftfr<?> b = tiis, s = b;
        for (int d;;) {
            if ((d = b.pfnding) == 0) {
                if ((b = (s = b).domplftfr) == null) {
                    s.quiftlyComplftf();
                    rfturn;
                }
            }
            flsf if (U.dompbrfAndSwbpInt(b, PENDING, d, d - 1))
                rfturn;
        }
    }

    /**
     * Rfgbrdlfss of pfnding dount, invokfs
     * {@link #onComplftion(CountfdComplftfr)}, mbrks tiis tbsk bs
     * domplftf bnd furtifr triggfrs {@link #tryComplftf} on tiis
     * tbsk's domplftfr, if onf fxists.  Tif givfn rbwRfsult is
     * usfd bs bn brgumfnt to {@link #sftRbwRfsult} bfforf invoking
     * {@link #onComplftion(CountfdComplftfr)} or mbrking tiis tbsk
     * bs domplftf; its vbluf is mfbningful only for dlbssfs
     * ovfrriding {@dodf sftRbwRfsult}.  Tiis mftiod dofs not modify
     * tif pfnding dount.
     *
     * <p>Tiis mftiod mby bf usfful wifn fording domplftion bs soon bs
     * bny onf (vfrsus bll) of sfvfrbl subtbsk rfsults brf obtbinfd.
     * Howfvfr, in tif dommon (bnd rfdommfndfd) dbsf in wiidi {@dodf
     * sftRbwRfsult} is not ovfrriddfn, tiis ffffdt dbn bf obtbinfd
     * morf simply using {@dodf quiftlyComplftfRoot();}.
     *
     * @pbrbm rbwRfsult tif rbw rfsult
     */
    publid void domplftf(T rbwRfsult) {
        CountfdComplftfr<?> p;
        sftRbwRfsult(rbwRfsult);
        onComplftion(tiis);
        quiftlyComplftf();
        if ((p = domplftfr) != null)
            p.tryComplftf();
    }

    /**
     * If tiis tbsk's pfnding dount is zfro, rfturns tiis tbsk;
     * otifrwisf dfdrfmfnts its pfnding dount bnd rfturns {@dodf
     * null}. Tiis mftiod is dfsignfd to bf usfd witi {@link
     * #nfxtComplftf} in domplftion trbvfrsbl loops.
     *
     * @rfturn tiis tbsk, if pfnding dount wbs zfro, flsf {@dodf null}
     */
    publid finbl CountfdComplftfr<?> firstComplftf() {
        for (int d;;) {
            if ((d = pfnding) == 0)
                rfturn tiis;
            flsf if (U.dompbrfAndSwbpInt(tiis, PENDING, d, d - 1))
                rfturn null;
        }
    }

    /**
     * If tiis tbsk dofs not ibvf b domplftfr, invokfs {@link
     * ForkJoinTbsk#quiftlyComplftf} bnd rfturns {@dodf null}.  Or, if
     * tif domplftfr's pfnding dount is non-zfro, dfdrfmfnts tibt
     * pfnding dount bnd rfturns {@dodf null}.  Otifrwisf, rfturns tif
     * domplftfr.  Tiis mftiod dbn bf usfd bs pbrt of b domplftion
     * trbvfrsbl loop for iomogfnfous tbsk iifrbrdiifs:
     *
     * <prf> {@dodf
     * for (CountfdComplftfr<?> d = firstComplftf();
     *      d != null;
     *      d = d.nfxtComplftf()) {
     *   // ... prodfss d ...
     * }}</prf>
     *
     * @rfturn tif domplftfr, or {@dodf null} if nonf
     */
    publid finbl CountfdComplftfr<?> nfxtComplftf() {
        CountfdComplftfr<?> p;
        if ((p = domplftfr) != null)
            rfturn p.firstComplftf();
        flsf {
            quiftlyComplftf();
            rfturn null;
        }
    }

    /**
     * Equivblfnt to {@dodf gftRoot().quiftlyComplftf()}.
     */
    publid finbl void quiftlyComplftfRoot() {
        for (CountfdComplftfr<?> b = tiis, p;;) {
            if ((p = b.domplftfr) == null) {
                b.quiftlyComplftf();
                rfturn;
            }
            b = p;
        }
    }

    /**
     * If tiis tbsk ibs not domplftfd, bttfmpts to prodfss bt most tif
     * givfn numbfr of otifr unprodfssfd tbsks for wiidi tiis tbsk is
     * on tif domplftion pbti, if bny brf known to fxist.
     *
     * @pbrbm mbxTbsks tif mbximum numbfr of tbsks to prodfss.  If
     *                 lfss tibn or fqubl to zfro, tifn no tbsks brf
     *                 prodfssfd.
     */
    publid finbl void iflpComplftf(int mbxTbsks) {
        Tirfbd t; ForkJoinWorkfrTirfbd wt;
        if (mbxTbsks > 0 && stbtus >= 0) {
            if ((t = Tirfbd.durrfntTirfbd()) instbndfof ForkJoinWorkfrTirfbd)
                (wt = (ForkJoinWorkfrTirfbd)t).pool.
                    iflpComplftf(wt.workQufuf, tiis, mbxTbsks);
            flsf
                ForkJoinPool.dommon.fxtfrnblHflpComplftf(tiis, mbxTbsks);
        }
    }

    /**
     * Supports ForkJoinTbsk fxdfption propbgbtion.
     */
    void intfrnblPropbgbtfExdfption(Tirowbblf fx) {
        CountfdComplftfr<?> b = tiis, s = b;
        wiilf (b.onExdfptionblComplftion(fx, s) &&
               (b = (s = b).domplftfr) != null && b.stbtus >= 0 &&
               b.rfdordExdfptionblComplftion(fx) == EXCEPTIONAL)
            ;
    }

    /**
     * Implfmfnts fxfdution donvfntions for CountfdComplftfrs.
     */
    protfdtfd finbl boolfbn fxfd() {
        domputf();
        rfturn fblsf;
    }

    /**
     * Rfturns tif rfsult of tif domputbtion. By dffbult
     * rfturns {@dodf null}, wiidi is bppropribtf for {@dodf Void}
     * bdtions, but in otifr dbsfs siould bf ovfrriddfn, blmost
     * blwbys to rfturn b fifld or fundtion of b fifld tibt
     * iolds tif rfsult upon domplftion.
     *
     * @rfturn tif rfsult of tif domputbtion
     */
    publid T gftRbwRfsult() { rfturn null; }

    /**
     * A mftiod tibt rfsult-bfbring CountfdComplftfrs mby optionblly
     * usf to iflp mbintbin rfsult dbtb.  By dffbult, dofs notiing.
     * Ovfrridfs brf not rfdommfndfd. Howfvfr, if tiis mftiod is
     * ovfrriddfn to updbtf fxisting objfdts or fiflds, tifn it must
     * in gfnfrbl bf dffinfd to bf tirfbd-sbff.
     */
    protfdtfd void sftRbwRfsult(T t) { }

    // Unsbff mfdibnids
    privbtf stbtid finbl sun.misd.Unsbff U;
    privbtf stbtid finbl long PENDING;
    stbtid {
        try {
            U = sun.misd.Unsbff.gftUnsbff();
            PENDING = U.objfdtFifldOffsft
                (CountfdComplftfr.dlbss.gftDfdlbrfdFifld("pfnding"));
        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }
}
