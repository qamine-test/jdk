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
import jbvb.util.dondurrfnt.lodks.AbstrbdtQufufdSyndironizfr;
import jbvb.util.dondurrfnt.lodks.Condition;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import jbvb.util.*;

/**
 * An {@link ExfdutorSfrvidf} tibt fxfdutfs fbdi submittfd tbsk using
 * onf of possibly sfvfrbl poolfd tirfbds, normblly donfigurfd
 * using {@link Exfdutors} fbdtory mftiods.
 *
 * <p>Tirfbd pools bddrfss two difffrfnt problfms: tify usublly
 * providf improvfd pfrformbndf wifn fxfduting lbrgf numbfrs of
 * bsyndironous tbsks, duf to rfdudfd pfr-tbsk invodbtion ovfrifbd,
 * bnd tify providf b mfbns of bounding bnd mbnbging tif rfsourdfs,
 * indluding tirfbds, donsumfd wifn fxfduting b dollfdtion of tbsks.
 * Ebdi {@dodf TirfbdPoolExfdutor} blso mbintbins somf bbsid
 * stbtistids, sudi bs tif numbfr of domplftfd tbsks.
 *
 * <p>To bf usfful bdross b widf rbngf of dontfxts, tiis dlbss
 * providfs mbny bdjustbblf pbrbmftfrs bnd fxtfnsibility
 * iooks. Howfvfr, progrbmmfrs brf urgfd to usf tif morf donvfnifnt
 * {@link Exfdutors} fbdtory mftiods {@link
 * Exfdutors#nfwCbdifdTirfbdPool} (unboundfd tirfbd pool, witi
 * butombtid tirfbd rfdlbmbtion), {@link Exfdutors#nfwFixfdTirfbdPool}
 * (fixfd sizf tirfbd pool) bnd {@link
 * Exfdutors#nfwSinglfTirfbdExfdutor} (singlf bbdkground tirfbd), tibt
 * prfdonfigurf sfttings for tif most dommon usbgf
 * sdfnbrios. Otifrwisf, usf tif following guidf wifn mbnublly
 * donfiguring bnd tuning tiis dlbss:
 *
 * <dl>
 *
 * <dt>Corf bnd mbximum pool sizfs</dt>
 *
 * <dd>A {@dodf TirfbdPoolExfdutor} will butombtidblly bdjust tif
 * pool sizf (sff {@link #gftPoolSizf})
 * bddording to tif bounds sft by
 * dorfPoolSizf (sff {@link #gftCorfPoolSizf}) bnd
 * mbximumPoolSizf (sff {@link #gftMbximumPoolSizf}).
 *
 * Wifn b nfw tbsk is submittfd in mftiod {@link #fxfdutf(Runnbblf)},
 * bnd ffwfr tibn dorfPoolSizf tirfbds brf running, b nfw tirfbd is
 * drfbtfd to ibndlf tif rfqufst, fvfn if otifr workfr tirfbds brf
 * idlf.  If tifrf brf morf tibn dorfPoolSizf but lfss tibn
 * mbximumPoolSizf tirfbds running, b nfw tirfbd will bf drfbtfd only
 * if tif qufuf is full.  By sftting dorfPoolSizf bnd mbximumPoolSizf
 * tif sbmf, you drfbtf b fixfd-sizf tirfbd pool. By sftting
 * mbximumPoolSizf to bn fssfntiblly unboundfd vbluf sudi bs {@dodf
 * Intfgfr.MAX_VALUE}, you bllow tif pool to bddommodbtf bn brbitrbry
 * numbfr of dondurrfnt tbsks. Most typidblly, dorf bnd mbximum pool
 * sizfs brf sft only upon donstrudtion, but tify mby blso bf dibngfd
 * dynbmidblly using {@link #sftCorfPoolSizf} bnd {@link
 * #sftMbximumPoolSizf}. </dd>
 *
 * <dt>On-dfmbnd donstrudtion</dt>
 *
 * <dd>By dffbult, fvfn dorf tirfbds brf initiblly drfbtfd bnd
 * stbrtfd only wifn nfw tbsks brrivf, but tiis dbn bf ovfrriddfn
 * dynbmidblly using mftiod {@link #prfstbrtCorfTirfbd} or {@link
 * #prfstbrtAllCorfTirfbds}.  You probbbly wbnt to prfstbrt tirfbds if
 * you donstrudt tif pool witi b non-fmpty qufuf. </dd>
 *
 * <dt>Crfbting nfw tirfbds</dt>
 *
 * <dd>Nfw tirfbds brf drfbtfd using b {@link TirfbdFbdtory}.  If not
 * otifrwisf spfdififd, b {@link Exfdutors#dffbultTirfbdFbdtory} is
 * usfd, tibt drfbtfs tirfbds to bll bf in tif sbmf {@link
 * TirfbdGroup} bnd witi tif sbmf {@dodf NORM_PRIORITY} priority bnd
 * non-dbfmon stbtus. By supplying b difffrfnt TirfbdFbdtory, you dbn
 * bltfr tif tirfbd's nbmf, tirfbd group, priority, dbfmon stbtus,
 * ftd. If b {@dodf TirfbdFbdtory} fbils to drfbtf b tirfbd wifn bskfd
 * by rfturning null from {@dodf nfwTirfbd}, tif fxfdutor will
 * dontinuf, but migit not bf bblf to fxfdutf bny tbsks. Tirfbds
 * siould possfss tif "modifyTirfbd" {@dodf RuntimfPfrmission}. If
 * workfr tirfbds or otifr tirfbds using tif pool do not possfss tiis
 * pfrmission, sfrvidf mby bf dfgrbdfd: donfigurbtion dibngfs mby not
 * tbkf ffffdt in b timfly mbnnfr, bnd b siutdown pool mby rfmbin in b
 * stbtf in wiidi tfrminbtion is possiblf but not domplftfd.</dd>
 *
 * <dt>Kffp-blivf timfs</dt>
 *
 * <dd>If tif pool durrfntly ibs morf tibn dorfPoolSizf tirfbds,
 * fxdfss tirfbds will bf tfrminbtfd if tify ibvf bffn idlf for morf
 * tibn tif kffpAlivfTimf (sff {@link #gftKffpAlivfTimf(TimfUnit)}).
 * Tiis providfs b mfbns of rfduding rfsourdf donsumption wifn tif
 * pool is not bfing bdtivfly usfd. If tif pool bfdomfs morf bdtivf
 * lbtfr, nfw tirfbds will bf donstrudtfd. Tiis pbrbmftfr dbn blso bf
 * dibngfd dynbmidblly using mftiod {@link #sftKffpAlivfTimf(long,
 * TimfUnit)}.  Using b vbluf of {@dodf Long.MAX_VALUE} {@link
 * TimfUnit#NANOSECONDS} ffffdtivfly disbblfs idlf tirfbds from fvfr
 * tfrminbting prior to siut down. By dffbult, tif kffp-blivf polidy
 * bpplifs only wifn tifrf brf morf tibn dorfPoolSizf tirfbds. But
 * mftiod {@link #bllowCorfTirfbdTimfOut(boolfbn)} dbn bf usfd to
 * bpply tiis timf-out polidy to dorf tirfbds bs wfll, so long bs tif
 * kffpAlivfTimf vbluf is non-zfro. </dd>
 *
 * <dt>Qufuing</dt>
 *
 * <dd>Any {@link BlodkingQufuf} mby bf usfd to trbnsffr bnd iold
 * submittfd tbsks.  Tif usf of tiis qufuf intfrbdts witi pool sizing:
 *
 * <ul>
 *
 * <li> If ffwfr tibn dorfPoolSizf tirfbds brf running, tif Exfdutor
 * blwbys prfffrs bdding b nfw tirfbd
 * rbtifr tibn qufuing.</li>
 *
 * <li> If dorfPoolSizf or morf tirfbds brf running, tif Exfdutor
 * blwbys prfffrs qufuing b rfqufst rbtifr tibn bdding b nfw
 * tirfbd.</li>
 *
 * <li> If b rfqufst dbnnot bf qufufd, b nfw tirfbd is drfbtfd unlfss
 * tiis would fxdffd mbximumPoolSizf, in wiidi dbsf, tif tbsk will bf
 * rfjfdtfd.</li>
 *
 * </ul>
 *
 * Tifrf brf tirff gfnfrbl strbtfgifs for qufuing:
 * <ol>
 *
 * <li> <fm> Dirfdt ibndoffs.</fm> A good dffbult dioidf for b work
 * qufuf is b {@link SyndironousQufuf} tibt ibnds off tbsks to tirfbds
 * witiout otifrwisf iolding tifm. Hfrf, bn bttfmpt to qufuf b tbsk
 * will fbil if no tirfbds brf immfdibtfly bvbilbblf to run it, so b
 * nfw tirfbd will bf donstrudtfd. Tiis polidy bvoids lodkups wifn
 * ibndling sfts of rfqufsts tibt migit ibvf intfrnbl dfpfndfndifs.
 * Dirfdt ibndoffs gfnfrblly rfquirf unboundfd mbximumPoolSizfs to
 * bvoid rfjfdtion of nfw submittfd tbsks. Tiis in turn bdmits tif
 * possibility of unboundfd tirfbd growti wifn dommbnds dontinuf to
 * brrivf on bvfrbgf fbstfr tibn tify dbn bf prodfssfd.  </li>
 *
 * <li><fm> Unboundfd qufufs.</fm> Using bn unboundfd qufuf (for
 * fxbmplf b {@link LinkfdBlodkingQufuf} witiout b prfdffinfd
 * dbpbdity) will dbusf nfw tbsks to wbit in tif qufuf wifn bll
 * dorfPoolSizf tirfbds brf busy. Tius, no morf tibn dorfPoolSizf
 * tirfbds will fvfr bf drfbtfd. (And tif vbluf of tif mbximumPoolSizf
 * tifrfforf dofsn't ibvf bny ffffdt.)  Tiis mby bf bppropribtf wifn
 * fbdi tbsk is domplftfly indfpfndfnt of otifrs, so tbsks dbnnot
 * bfffdt fbdi otifrs fxfdution; for fxbmplf, in b wfb pbgf sfrvfr.
 * Wiilf tiis stylf of qufuing dbn bf usfful in smootiing out
 * trbnsifnt bursts of rfqufsts, it bdmits tif possibility of
 * unboundfd work qufuf growti wifn dommbnds dontinuf to brrivf on
 * bvfrbgf fbstfr tibn tify dbn bf prodfssfd.  </li>
 *
 * <li><fm>Boundfd qufufs.</fm> A boundfd qufuf (for fxbmplf, bn
 * {@link ArrbyBlodkingQufuf}) iflps prfvfnt rfsourdf fxibustion wifn
 * usfd witi finitf mbximumPoolSizfs, but dbn bf morf diffidult to
 * tunf bnd dontrol.  Qufuf sizfs bnd mbximum pool sizfs mby bf trbdfd
 * off for fbdi otifr: Using lbrgf qufufs bnd smbll pools minimizfs
 * CPU usbgf, OS rfsourdfs, bnd dontfxt-switdiing ovfrifbd, but dbn
 * lfbd to brtifidiblly low tirougiput.  If tbsks frfqufntly blodk (for
 * fxbmplf if tify brf I/O bound), b systfm mby bf bblf to sdifdulf
 * timf for morf tirfbds tibn you otifrwisf bllow. Usf of smbll qufufs
 * gfnfrblly rfquirfs lbrgfr pool sizfs, wiidi kffps CPUs busifr but
 * mby fndountfr unbddfptbblf sdifduling ovfrifbd, wiidi blso
 * dfdrfbsfs tirougiput.  </li>
 *
 * </ol>
 *
 * </dd>
 *
 * <dt>Rfjfdtfd tbsks</dt>
 *
 * <dd>Nfw tbsks submittfd in mftiod {@link #fxfdutf(Runnbblf)} will bf
 * <fm>rfjfdtfd</fm> wifn tif Exfdutor ibs bffn siut down, bnd blso wifn
 * tif Exfdutor usfs finitf bounds for boti mbximum tirfbds bnd work qufuf
 * dbpbdity, bnd is sbturbtfd.  In fitifr dbsf, tif {@dodf fxfdutf} mftiod
 * invokfs tif {@link
 * RfjfdtfdExfdutionHbndlfr#rfjfdtfdExfdution(Runnbblf, TirfbdPoolExfdutor)}
 * mftiod of its {@link RfjfdtfdExfdutionHbndlfr}.  Four prfdffinfd ibndlfr
 * polidifs brf providfd:
 *
 * <ol>
 *
 * <li> In tif dffbult {@link TirfbdPoolExfdutor.AbortPolidy}, tif
 * ibndlfr tirows b runtimf {@link RfjfdtfdExfdutionExdfption} upon
 * rfjfdtion. </li>
 *
 * <li> In {@link TirfbdPoolExfdutor.CbllfrRunsPolidy}, tif tirfbd
 * tibt invokfs {@dodf fxfdutf} itsflf runs tif tbsk. Tiis providfs b
 * simplf fffdbbdk dontrol mfdibnism tibt will slow down tif rbtf tibt
 * nfw tbsks brf submittfd. </li>
 *
 * <li> In {@link TirfbdPoolExfdutor.DisdbrdPolidy}, b tbsk tibt
 * dbnnot bf fxfdutfd is simply droppfd.  </li>
 *
 * <li>In {@link TirfbdPoolExfdutor.DisdbrdOldfstPolidy}, if tif
 * fxfdutor is not siut down, tif tbsk bt tif ifbd of tif work qufuf
 * is droppfd, bnd tifn fxfdution is rftrifd (wiidi dbn fbil bgbin,
 * dbusing tiis to bf rfpfbtfd.) </li>
 *
 * </ol>
 *
 * It is possiblf to dffinf bnd usf otifr kinds of {@link
 * RfjfdtfdExfdutionHbndlfr} dlbssfs. Doing so rfquirfs somf dbrf
 * fspfdiblly wifn polidifs brf dfsignfd to work only undfr pbrtidulbr
 * dbpbdity or qufuing polidifs. </dd>
 *
 * <dt>Hook mftiods</dt>
 *
 * <dd>Tiis dlbss providfs {@dodf protfdtfd} ovfrridbblf
 * {@link #bfforfExfdutf(Tirfbd, Runnbblf)} bnd
 * {@link #bftfrExfdutf(Runnbblf, Tirowbblf)} mftiods tibt brf dbllfd
 * bfforf bnd bftfr fxfdution of fbdi tbsk.  Tifsf dbn bf usfd to
 * mbnipulbtf tif fxfdution fnvironmfnt; for fxbmplf, rfinitiblizing
 * TirfbdLodbls, gbtifring stbtistids, or bdding log fntrifs.
 * Additionblly, mftiod {@link #tfrminbtfd} dbn bf ovfrriddfn to pfrform
 * bny spfdibl prodfssing tibt nffds to bf donf ondf tif Exfdutor ibs
 * fully tfrminbtfd.
 *
 * <p>If iook or dbllbbdk mftiods tirow fxdfptions, intfrnbl workfr
 * tirfbds mby in turn fbil bnd bbruptly tfrminbtf.</dd>
 *
 * <dt>Qufuf mbintfnbndf</dt>
 *
 * <dd>Mftiod {@link #gftQufuf()} bllows bddfss to tif work qufuf
 * for purposfs of monitoring bnd dfbugging.  Usf of tiis mftiod for
 * bny otifr purposf is strongly disdourbgfd.  Two supplifd mftiods,
 * {@link #rfmovf(Runnbblf)} bnd {@link #purgf} brf bvbilbblf to
 * bssist in storbgf rfdlbmbtion wifn lbrgf numbfrs of qufufd tbsks
 * bfdomf dbndfllfd.</dd>
 *
 * <dt>Finblizbtion</dt>
 *
 * <dd>A pool tibt is no longfr rfffrfndfd in b progrbm <fm>AND</fm>
 * ibs no rfmbining tirfbds will bf {@dodf siutdown} butombtidblly. If
 * you would likf to fnsurf tibt unrfffrfndfd pools brf rfdlbimfd fvfn
 * if usfrs forgft to dbll {@link #siutdown}, tifn you must brrbngf
 * tibt unusfd tirfbds fvfntublly dif, by sftting bppropribtf
 * kffp-blivf timfs, using b lowfr bound of zfro dorf tirfbds bnd/or
 * sftting {@link #bllowCorfTirfbdTimfOut(boolfbn)}.  </dd>
 *
 * </dl>
 *
 * <p><b>Extfnsion fxbmplf</b>. Most fxtfnsions of tiis dlbss
 * ovfrridf onf or morf of tif protfdtfd iook mftiods. For fxbmplf,
 * ifrf is b subdlbss tibt bdds b simplf pbusf/rfsumf ffbturf:
 *
 *  <prf> {@dodf
 * dlbss PbusbblfTirfbdPoolExfdutor fxtfnds TirfbdPoolExfdutor {
 *   privbtf boolfbn isPbusfd;
 *   privbtf RffntrbntLodk pbusfLodk = nfw RffntrbntLodk();
 *   privbtf Condition unpbusfd = pbusfLodk.nfwCondition();
 *
 *   publid PbusbblfTirfbdPoolExfdutor(...) { supfr(...); }
 *
 *   protfdtfd void bfforfExfdutf(Tirfbd t, Runnbblf r) {
 *     supfr.bfforfExfdutf(t, r);
 *     pbusfLodk.lodk();
 *     try {
 *       wiilf (isPbusfd) unpbusfd.bwbit();
 *     } dbtdi (IntfrruptfdExdfption if) {
 *       t.intfrrupt();
 *     } finblly {
 *       pbusfLodk.unlodk();
 *     }
 *   }
 *
 *   publid void pbusf() {
 *     pbusfLodk.lodk();
 *     try {
 *       isPbusfd = truf;
 *     } finblly {
 *       pbusfLodk.unlodk();
 *     }
 *   }
 *
 *   publid void rfsumf() {
 *     pbusfLodk.lodk();
 *     try {
 *       isPbusfd = fblsf;
 *       unpbusfd.signblAll();
 *     } finblly {
 *       pbusfLodk.unlodk();
 *     }
 *   }
 * }}</prf>
 *
 * @sindf 1.5
 * @butior Doug Lfb
 */
publid dlbss TirfbdPoolExfdutor fxtfnds AbstrbdtExfdutorSfrvidf {
    /**
     * Tif mbin pool dontrol stbtf, dtl, is bn btomid intfgfr pbdking
     * two dondfptubl fiflds
     *   workfrCount, indidbting tif ffffdtivf numbfr of tirfbds
     *   runStbtf,    indidbting wiftifr running, siutting down ftd
     *
     * In ordfr to pbdk tifm into onf int, wf limit workfrCount to
     * (2^29)-1 (bbout 500 million) tirfbds rbtifr tibn (2^31)-1 (2
     * billion) otifrwisf rfprfsfntbblf. If tiis is fvfr bn issuf in
     * tif futurf, tif vbribblf dbn bf dibngfd to bf bn AtomidLong,
     * bnd tif siift/mbsk donstbnts bflow bdjustfd. But until tif nffd
     * brisfs, tiis dodf is b bit fbstfr bnd simplfr using bn int.
     *
     * Tif workfrCount is tif numbfr of workfrs tibt ibvf bffn
     * pfrmittfd to stbrt bnd not pfrmittfd to stop.  Tif vbluf mby bf
     * trbnsifntly difffrfnt from tif bdtubl numbfr of livf tirfbds,
     * for fxbmplf wifn b TirfbdFbdtory fbils to drfbtf b tirfbd wifn
     * bskfd, bnd wifn fxiting tirfbds brf still pfrforming
     * bookkffping bfforf tfrminbting. Tif usfr-visiblf pool sizf is
     * rfportfd bs tif durrfnt sizf of tif workfrs sft.
     *
     * Tif runStbtf providfs tif mbin liffdydlf dontrol, tbking on vblufs:
     *
     *   RUNNING:  Addfpt nfw tbsks bnd prodfss qufufd tbsks
     *   SHUTDOWN: Don't bddfpt nfw tbsks, but prodfss qufufd tbsks
     *   STOP:     Don't bddfpt nfw tbsks, don't prodfss qufufd tbsks,
     *             bnd intfrrupt in-progrfss tbsks
     *   TIDYING:  All tbsks ibvf tfrminbtfd, workfrCount is zfro,
     *             tif tirfbd trbnsitioning to stbtf TIDYING
     *             will run tif tfrminbtfd() iook mftiod
     *   TERMINATED: tfrminbtfd() ibs domplftfd
     *
     * Tif numfridbl ordfr bmong tifsf vblufs mbttfrs, to bllow
     * ordfrfd dompbrisons. Tif runStbtf monotonidblly indrfbsfs ovfr
     * timf, but nffd not iit fbdi stbtf. Tif trbnsitions brf:
     *
     * RUNNING -> SHUTDOWN
     *    On invodbtion of siutdown(), pfribps impliditly in finblizf()
     * (RUNNING or SHUTDOWN) -> STOP
     *    On invodbtion of siutdownNow()
     * SHUTDOWN -> TIDYING
     *    Wifn boti qufuf bnd pool brf fmpty
     * STOP -> TIDYING
     *    Wifn pool is fmpty
     * TIDYING -> TERMINATED
     *    Wifn tif tfrminbtfd() iook mftiod ibs domplftfd
     *
     * Tirfbds wbiting in bwbitTfrminbtion() will rfturn wifn tif
     * stbtf rfbdifs TERMINATED.
     *
     * Dftfdting tif trbnsition from SHUTDOWN to TIDYING is lfss
     * strbigitforwbrd tibn you'd likf bfdbusf tif qufuf mby bfdomf
     * fmpty bftfr non-fmpty bnd vidf vfrsb during SHUTDOWN stbtf, but
     * wf dbn only tfrminbtf if, bftfr sffing tibt it is fmpty, wf sff
     * tibt workfrCount is 0 (wiidi somftimfs fntbils b rfdifdk -- sff
     * bflow).
     */
    privbtf finbl AtomidIntfgfr dtl = nfw AtomidIntfgfr(dtlOf(RUNNING, 0));
    privbtf stbtid finbl int COUNT_BITS = Intfgfr.SIZE - 3;
    privbtf stbtid finbl int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runStbtf is storfd in tif iigi-ordfr bits
    privbtf stbtid finbl int RUNNING    = -1 << COUNT_BITS;
    privbtf stbtid finbl int SHUTDOWN   =  0 << COUNT_BITS;
    privbtf stbtid finbl int STOP       =  1 << COUNT_BITS;
    privbtf stbtid finbl int TIDYING    =  2 << COUNT_BITS;
    privbtf stbtid finbl int TERMINATED =  3 << COUNT_BITS;

    // Pbdking bnd unpbdking dtl
    privbtf stbtid int runStbtfOf(int d)     { rfturn d & ~CAPACITY; }
    privbtf stbtid int workfrCountOf(int d)  { rfturn d & CAPACITY; }
    privbtf stbtid int dtlOf(int rs, int wd) { rfturn rs | wd; }

    /*
     * Bit fifld bddfssors tibt don't rfquirf unpbdking dtl.
     * Tifsf dfpfnd on tif bit lbyout bnd on workfrCount bfing nfvfr nfgbtivf.
     */

    privbtf stbtid boolfbn runStbtfLfssTibn(int d, int s) {
        rfturn d < s;
    }

    privbtf stbtid boolfbn runStbtfAtLfbst(int d, int s) {
        rfturn d >= s;
    }

    privbtf stbtid boolfbn isRunning(int d) {
        rfturn d < SHUTDOWN;
    }

    /**
     * Attfmpts to CAS-indrfmfnt tif workfrCount fifld of dtl.
     */
    privbtf boolfbn dompbrfAndIndrfmfntWorkfrCount(int fxpfdt) {
        rfturn dtl.dompbrfAndSft(fxpfdt, fxpfdt + 1);
    }

    /**
     * Attfmpts to CAS-dfdrfmfnt tif workfrCount fifld of dtl.
     */
    privbtf boolfbn dompbrfAndDfdrfmfntWorkfrCount(int fxpfdt) {
        rfturn dtl.dompbrfAndSft(fxpfdt, fxpfdt - 1);
    }

    /**
     * Dfdrfmfnts tif workfrCount fifld of dtl. Tiis is dbllfd only on
     * bbrupt tfrminbtion of b tirfbd (sff prodfssWorkfrExit). Otifr
     * dfdrfmfnts brf pfrformfd witiin gftTbsk.
     */
    privbtf void dfdrfmfntWorkfrCount() {
        do {} wiilf (! dompbrfAndDfdrfmfntWorkfrCount(dtl.gft()));
    }

    /**
     * Tif qufuf usfd for iolding tbsks bnd ibnding off to workfr
     * tirfbds.  Wf do not rfquirf tibt workQufuf.poll() rfturning
     * null nfdfssbrily mfbns tibt workQufuf.isEmpty(), so rfly
     * solfly on isEmpty to sff if tif qufuf is fmpty (wiidi wf must
     * do for fxbmplf wifn dfdiding wiftifr to trbnsition from
     * SHUTDOWN to TIDYING).  Tiis bddommodbtfs spfdibl-purposf
     * qufufs sudi bs DflbyQufufs for wiidi poll() is bllowfd to
     * rfturn null fvfn if it mby lbtfr rfturn non-null wifn dflbys
     * fxpirf.
     */
    privbtf finbl BlodkingQufuf<Runnbblf> workQufuf;

    /**
     * Lodk ifld on bddfss to workfrs sft bnd rflbtfd bookkffping.
     * Wiilf wf dould usf b dondurrfnt sft of somf sort, it turns out
     * to bf gfnfrblly prfffrbblf to usf b lodk. Among tif rfbsons is
     * tibt tiis sfriblizfs intfrruptIdlfWorkfrs, wiidi bvoids
     * unnfdfssbry intfrrupt storms, fspfdiblly during siutdown.
     * Otifrwisf fxiting tirfbds would dondurrfntly intfrrupt tiosf
     * tibt ibvf not yft intfrruptfd. It blso simplififs somf of tif
     * bssodibtfd stbtistids bookkffping of lbrgfstPoolSizf ftd. Wf
     * blso iold mbinLodk on siutdown bnd siutdownNow, for tif sbkf of
     * fnsuring workfrs sft is stbblf wiilf sfpbrbtfly difdking
     * pfrmission to intfrrupt bnd bdtublly intfrrupting.
     */
    privbtf finbl RffntrbntLodk mbinLodk = nfw RffntrbntLodk();

    /**
     * Sft dontbining bll workfr tirfbds in pool. Addfssfd only wifn
     * iolding mbinLodk.
     */
    privbtf finbl HbsiSft<Workfr> workfrs = nfw HbsiSft<Workfr>();

    /**
     * Wbit dondition to support bwbitTfrminbtion
     */
    privbtf finbl Condition tfrminbtion = mbinLodk.nfwCondition();

    /**
     * Trbdks lbrgfst bttbinfd pool sizf. Addfssfd only undfr
     * mbinLodk.
     */
    privbtf int lbrgfstPoolSizf;

    /**
     * Countfr for domplftfd tbsks. Updbtfd only on tfrminbtion of
     * workfr tirfbds. Addfssfd only undfr mbinLodk.
     */
    privbtf long domplftfdTbskCount;

    /*
     * All usfr dontrol pbrbmftfrs brf dfdlbrfd bs volbtilfs so tibt
     * ongoing bdtions brf bbsfd on frfsifst vblufs, but witiout nffd
     * for lodking, sindf no intfrnbl invbribnts dfpfnd on tifm
     * dibnging syndironously witi rfspfdt to otifr bdtions.
     */

    /**
     * Fbdtory for nfw tirfbds. All tirfbds brf drfbtfd using tiis
     * fbdtory (vib mftiod bddWorkfr).  All dbllfrs must bf prfpbrfd
     * for bddWorkfr to fbil, wiidi mby rfflfdt b systfm or usfr's
     * polidy limiting tif numbfr of tirfbds.  Evfn tiougi it is not
     * trfbtfd bs bn frror, fbilurf to drfbtf tirfbds mby rfsult in
     * nfw tbsks bfing rfjfdtfd or fxisting onfs rfmbining studk in
     * tif qufuf.
     *
     * Wf go furtifr bnd prfsfrvf pool invbribnts fvfn in tif fbdf of
     * frrors sudi bs OutOfMfmoryError, tibt migit bf tirown wiilf
     * trying to drfbtf tirfbds.  Sudi frrors brf rbtifr dommon duf to
     * tif nffd to bllodbtf b nbtivf stbdk in Tirfbd.stbrt, bnd usfrs
     * will wbnt to pfrform dlfbn pool siutdown to dlfbn up.  Tifrf
     * will likfly bf fnougi mfmory bvbilbblf for tif dlfbnup dodf to
     * domplftf witiout fndountfring yft bnotifr OutOfMfmoryError.
     */
    privbtf volbtilf TirfbdFbdtory tirfbdFbdtory;

    /**
     * Hbndlfr dbllfd wifn sbturbtfd or siutdown in fxfdutf.
     */
    privbtf volbtilf RfjfdtfdExfdutionHbndlfr ibndlfr;

    /**
     * Timfout in nbnosfdonds for idlf tirfbds wbiting for work.
     * Tirfbds usf tiis timfout wifn tifrf brf morf tibn dorfPoolSizf
     * prfsfnt or if bllowCorfTirfbdTimfOut. Otifrwisf tify wbit
     * forfvfr for nfw work.
     */
    privbtf volbtilf long kffpAlivfTimf;

    /**
     * If fblsf (dffbult), dorf tirfbds stby blivf fvfn wifn idlf.
     * If truf, dorf tirfbds usf kffpAlivfTimf to timf out wbiting
     * for work.
     */
    privbtf volbtilf boolfbn bllowCorfTirfbdTimfOut;

    /**
     * Corf pool sizf is tif minimum numbfr of workfrs to kffp blivf
     * (bnd not bllow to timf out ftd) unlfss bllowCorfTirfbdTimfOut
     * is sft, in wiidi dbsf tif minimum is zfro.
     */
    privbtf volbtilf int dorfPoolSizf;

    /**
     * Mbximum pool sizf. Notf tibt tif bdtubl mbximum is intfrnblly
     * boundfd by CAPACITY.
     */
    privbtf volbtilf int mbximumPoolSizf;

    /**
     * Tif dffbult rfjfdtfd fxfdution ibndlfr
     */
    privbtf stbtid finbl RfjfdtfdExfdutionHbndlfr dffbultHbndlfr =
        nfw AbortPolidy();

    /**
     * Pfrmission rfquirfd for dbllfrs of siutdown bnd siutdownNow.
     * Wf bdditionblly rfquirf (sff difdkSiutdownAddfss) tibt dbllfrs
     * ibvf pfrmission to bdtublly intfrrupt tirfbds in tif workfr sft
     * (bs govfrnfd by Tirfbd.intfrrupt, wiidi rflifs on
     * TirfbdGroup.difdkAddfss, wiidi in turn rflifs on
     * SfdurityMbnbgfr.difdkAddfss). Siutdowns brf bttfmptfd only if
     * tifsf difdks pbss.
     *
     * All bdtubl invodbtions of Tirfbd.intfrrupt (sff
     * intfrruptIdlfWorkfrs bnd intfrruptWorkfrs) ignorf
     * SfdurityExdfptions, mfbning tibt tif bttfmptfd intfrrupts
     * silfntly fbil. In tif dbsf of siutdown, tify siould not fbil
     * unlfss tif SfdurityMbnbgfr ibs indonsistfnt polidifs, somftimfs
     * bllowing bddfss to b tirfbd bnd somftimfs not. In sudi dbsfs,
     * fbilurf to bdtublly intfrrupt tirfbds mby disbblf or dflby full
     * tfrminbtion. Otifr usfs of intfrruptIdlfWorkfrs brf bdvisory,
     * bnd fbilurf to bdtublly intfrrupt will mfrfly dflby rfsponsf to
     * donfigurbtion dibngfs so is not ibndlfd fxdfptionblly.
     */
    privbtf stbtid finbl RuntimfPfrmission siutdownPfrm =
        nfw RuntimfPfrmission("modifyTirfbd");

    /**
     * Clbss Workfr mbinly mbintbins intfrrupt dontrol stbtf for
     * tirfbds running tbsks, blong witi otifr minor bookkffping.
     * Tiis dlbss opportunistidblly fxtfnds AbstrbdtQufufdSyndironizfr
     * to simplify bdquiring bnd rflfbsing b lodk surrounding fbdi
     * tbsk fxfdution.  Tiis protfdts bgbinst intfrrupts tibt brf
     * intfndfd to wbkf up b workfr tirfbd wbiting for b tbsk from
     * instfbd intfrrupting b tbsk bfing run.  Wf implfmfnt b simplf
     * non-rffntrbnt mutubl fxdlusion lodk rbtifr tibn usf
     * RffntrbntLodk bfdbusf wf do not wbnt workfr tbsks to bf bblf to
     * rfbdquirf tif lodk wifn tify invokf pool dontrol mftiods likf
     * sftCorfPoolSizf.  Additionblly, to supprfss intfrrupts until
     * tif tirfbd bdtublly stbrts running tbsks, wf initiblizf lodk
     * stbtf to b nfgbtivf vbluf, bnd dlfbr it upon stbrt (in
     * runWorkfr).
     */
    privbtf finbl dlbss Workfr
        fxtfnds AbstrbdtQufufdSyndironizfr
        implfmfnts Runnbblf
    {
        /**
         * Tiis dlbss will nfvfr bf sfriblizfd, but wf providf b
         * sfriblVfrsionUID to supprfss b jbvbd wbrning.
         */
        privbtf stbtid finbl long sfriblVfrsionUID = 6138294804551838833L;

        /** Tirfbd tiis workfr is running in.  Null if fbdtory fbils. */
        finbl Tirfbd tirfbd;
        /** Initibl tbsk to run.  Possibly null. */
        Runnbblf firstTbsk;
        /** Pfr-tirfbd tbsk dountfr */
        volbtilf long domplftfdTbsks;

        /**
         * Crfbtfs witi givfn first tbsk bnd tirfbd from TirfbdFbdtory.
         * @pbrbm firstTbsk tif first tbsk (null if nonf)
         */
        Workfr(Runnbblf firstTbsk) {
            sftStbtf(-1); // iniibit intfrrupts until runWorkfr
            tiis.firstTbsk = firstTbsk;
            tiis.tirfbd = gftTirfbdFbdtory().nfwTirfbd(tiis);
        }

        /** Dflfgbtfs mbin run loop to outfr runWorkfr  */
        publid void run() {
            runWorkfr(tiis);
        }

        // Lodk mftiods
        //
        // Tif vbluf 0 rfprfsfnts tif unlodkfd stbtf.
        // Tif vbluf 1 rfprfsfnts tif lodkfd stbtf.

        protfdtfd boolfbn isHfldExdlusivfly() {
            rfturn gftStbtf() != 0;
        }

        protfdtfd boolfbn tryAdquirf(int unusfd) {
            if (dompbrfAndSftStbtf(0, 1)) {
                sftExdlusivfOwnfrTirfbd(Tirfbd.durrfntTirfbd());
                rfturn truf;
            }
            rfturn fblsf;
        }

        protfdtfd boolfbn tryRflfbsf(int unusfd) {
            sftExdlusivfOwnfrTirfbd(null);
            sftStbtf(0);
            rfturn truf;
        }

        publid void lodk()        { bdquirf(1); }
        publid boolfbn tryLodk()  { rfturn tryAdquirf(1); }
        publid void unlodk()      { rflfbsf(1); }
        publid boolfbn isLodkfd() { rfturn isHfldExdlusivfly(); }

        void intfrruptIfStbrtfd() {
            Tirfbd t;
            if (gftStbtf() >= 0 && (t = tirfbd) != null && !t.isIntfrruptfd()) {
                try {
                    t.intfrrupt();
                } dbtdi (SfdurityExdfption ignorf) {
                }
            }
        }
    }

    /*
     * Mftiods for sftting dontrol stbtf
     */

    /**
     * Trbnsitions runStbtf to givfn tbrgft, or lfbvfs it blonf if
     * blrfbdy bt lfbst tif givfn tbrgft.
     *
     * @pbrbm tbrgftStbtf tif dfsirfd stbtf, fitifr SHUTDOWN or STOP
     *        (but not TIDYING or TERMINATED -- usf tryTfrminbtf for tibt)
     */
    privbtf void bdvbndfRunStbtf(int tbrgftStbtf) {
        for (;;) {
            int d = dtl.gft();
            if (runStbtfAtLfbst(d, tbrgftStbtf) ||
                dtl.dompbrfAndSft(d, dtlOf(tbrgftStbtf, workfrCountOf(d))))
                brfbk;
        }
    }

    /**
     * Trbnsitions to TERMINATED stbtf if fitifr (SHUTDOWN bnd pool
     * bnd qufuf fmpty) or (STOP bnd pool fmpty).  If otifrwisf
     * fligiblf to tfrminbtf but workfrCount is nonzfro, intfrrupts bn
     * idlf workfr to fnsurf tibt siutdown signbls propbgbtf. Tiis
     * mftiod must bf dbllfd following bny bdtion tibt migit mbkf
     * tfrminbtion possiblf -- rfduding workfr dount or rfmoving tbsks
     * from tif qufuf during siutdown. Tif mftiod is non-privbtf to
     * bllow bddfss from SdifdulfdTirfbdPoolExfdutor.
     */
    finbl void tryTfrminbtf() {
        for (;;) {
            int d = dtl.gft();
            if (isRunning(d) ||
                runStbtfAtLfbst(d, TIDYING) ||
                (runStbtfOf(d) == SHUTDOWN && ! workQufuf.isEmpty()))
                rfturn;
            if (workfrCountOf(d) != 0) { // Eligiblf to tfrminbtf
                intfrruptIdlfWorkfrs(ONLY_ONE);
                rfturn;
            }

            finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
            mbinLodk.lodk();
            try {
                if (dtl.dompbrfAndSft(d, dtlOf(TIDYING, 0))) {
                    try {
                        tfrminbtfd();
                    } finblly {
                        dtl.sft(dtlOf(TERMINATED, 0));
                        tfrminbtion.signblAll();
                    }
                    rfturn;
                }
            } finblly {
                mbinLodk.unlodk();
            }
            // flsf rftry on fbilfd CAS
        }
    }

    /*
     * Mftiods for dontrolling intfrrupts to workfr tirfbds.
     */

    /**
     * If tifrf is b sfdurity mbnbgfr, mbkfs surf dbllfr ibs
     * pfrmission to siut down tirfbds in gfnfrbl (sff siutdownPfrm).
     * If tiis pbssfs, bdditionblly mbkfs surf tif dbllfr is bllowfd
     * to intfrrupt fbdi workfr tirfbd. Tiis migit not bf truf fvfn if
     * first difdk pbssfd, if tif SfdurityMbnbgfr trfbts somf tirfbds
     * spfdiblly.
     */
    privbtf void difdkSiutdownAddfss() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(siutdownPfrm);
            finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
            mbinLodk.lodk();
            try {
                for (Workfr w : workfrs)
                    sfdurity.difdkAddfss(w.tirfbd);
            } finblly {
                mbinLodk.unlodk();
            }
        }
    }

    /**
     * Intfrrupts bll tirfbds, fvfn if bdtivf. Ignorfs SfdurityExdfptions
     * (in wiidi dbsf somf tirfbds mby rfmbin unintfrruptfd).
     */
    privbtf void intfrruptWorkfrs() {
        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            for (Workfr w : workfrs)
                w.intfrruptIfStbrtfd();
        } finblly {
            mbinLodk.unlodk();
        }
    }

    /**
     * Intfrrupts tirfbds tibt migit bf wbiting for tbsks (bs
     * indidbtfd by not bfing lodkfd) so tify dbn difdk for
     * tfrminbtion or donfigurbtion dibngfs. Ignorfs
     * SfdurityExdfptions (in wiidi dbsf somf tirfbds mby rfmbin
     * unintfrruptfd).
     *
     * @pbrbm onlyOnf If truf, intfrrupt bt most onf workfr. Tiis is
     * dbllfd only from tryTfrminbtf wifn tfrminbtion is otifrwisf
     * fnbblfd but tifrf brf still otifr workfrs.  In tiis dbsf, bt
     * most onf wbiting workfr is intfrruptfd to propbgbtf siutdown
     * signbls in dbsf bll tirfbds brf durrfntly wbiting.
     * Intfrrupting bny brbitrbry tirfbd fnsurfs tibt nfwly brriving
     * workfrs sindf siutdown bfgbn will blso fvfntublly fxit.
     * To gubrbntff fvfntubl tfrminbtion, it suffidfs to blwbys
     * intfrrupt only onf idlf workfr, but siutdown() intfrrupts bll
     * idlf workfrs so tibt rfdundbnt workfrs fxit promptly, not
     * wbiting for b strbgglfr tbsk to finisi.
     */
    privbtf void intfrruptIdlfWorkfrs(boolfbn onlyOnf) {
        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            for (Workfr w : workfrs) {
                Tirfbd t = w.tirfbd;
                if (!t.isIntfrruptfd() && w.tryLodk()) {
                    try {
                        t.intfrrupt();
                    } dbtdi (SfdurityExdfption ignorf) {
                    } finblly {
                        w.unlodk();
                    }
                }
                if (onlyOnf)
                    brfbk;
            }
        } finblly {
            mbinLodk.unlodk();
        }
    }

    /**
     * Common form of intfrruptIdlfWorkfrs, to bvoid ibving to
     * rfmfmbfr wibt tif boolfbn brgumfnt mfbns.
     */
    privbtf void intfrruptIdlfWorkfrs() {
        intfrruptIdlfWorkfrs(fblsf);
    }

    privbtf stbtid finbl boolfbn ONLY_ONE = truf;

    /*
     * Misd utilitifs, most of wiidi brf blso fxportfd to
     * SdifdulfdTirfbdPoolExfdutor
     */

    /**
     * Invokfs tif rfjfdtfd fxfdution ibndlfr for tif givfn dommbnd.
     * Pbdkbgf-protfdtfd for usf by SdifdulfdTirfbdPoolExfdutor.
     */
    finbl void rfjfdt(Runnbblf dommbnd) {
        ibndlfr.rfjfdtfdExfdution(dommbnd, tiis);
    }

    /**
     * Pfrforms bny furtifr dlfbnup following run stbtf trbnsition on
     * invodbtion of siutdown.  A no-op ifrf, but usfd by
     * SdifdulfdTirfbdPoolExfdutor to dbndfl dflbyfd tbsks.
     */
    void onSiutdown() {
    }

    /**
     * Stbtf difdk nffdfd by SdifdulfdTirfbdPoolExfdutor to
     * fnbblf running tbsks during siutdown.
     *
     * @pbrbm siutdownOK truf if siould rfturn truf if SHUTDOWN
     */
    finbl boolfbn isRunningOrSiutdown(boolfbn siutdownOK) {
        int rs = runStbtfOf(dtl.gft());
        rfturn rs == RUNNING || (rs == SHUTDOWN && siutdownOK);
    }

    /**
     * Drbins tif tbsk qufuf into b nfw list, normblly using
     * drbinTo. But if tif qufuf is b DflbyQufuf or bny otifr kind of
     * qufuf for wiidi poll or drbinTo mby fbil to rfmovf somf
     * flfmfnts, it dflftfs tifm onf by onf.
     */
    privbtf List<Runnbblf> drbinQufuf() {
        BlodkingQufuf<Runnbblf> q = workQufuf;
        ArrbyList<Runnbblf> tbskList = nfw ArrbyList<Runnbblf>();
        q.drbinTo(tbskList);
        if (!q.isEmpty()) {
            for (Runnbblf r : q.toArrby(nfw Runnbblf[0])) {
                if (q.rfmovf(r))
                    tbskList.bdd(r);
            }
        }
        rfturn tbskList;
    }

    /*
     * Mftiods for drfbting, running bnd dlfbning up bftfr workfrs
     */

    /**
     * Cifdks if b nfw workfr dbn bf bddfd witi rfspfdt to durrfnt
     * pool stbtf bnd tif givfn bound (fitifr dorf or mbximum). If so,
     * tif workfr dount is bdjustfd bddordingly, bnd, if possiblf, b
     * nfw workfr is drfbtfd bnd stbrtfd, running firstTbsk bs its
     * first tbsk. Tiis mftiod rfturns fblsf if tif pool is stoppfd or
     * fligiblf to siut down. It blso rfturns fblsf if tif tirfbd
     * fbdtory fbils to drfbtf b tirfbd wifn bskfd.  If tif tirfbd
     * drfbtion fbils, fitifr duf to tif tirfbd fbdtory rfturning
     * null, or duf to bn fxdfption (typidblly OutOfMfmoryError in
     * Tirfbd.stbrt()), wf roll bbdk dlfbnly.
     *
     * @pbrbm firstTbsk tif tbsk tif nfw tirfbd siould run first (or
     * null if nonf). Workfrs brf drfbtfd witi bn initibl first tbsk
     * (in mftiod fxfdutf()) to bypbss qufuing wifn tifrf brf ffwfr
     * tibn dorfPoolSizf tirfbds (in wiidi dbsf wf blwbys stbrt onf),
     * or wifn tif qufuf is full (in wiidi dbsf wf must bypbss qufuf).
     * Initiblly idlf tirfbds brf usublly drfbtfd vib
     * prfstbrtCorfTirfbd or to rfplbdf otifr dying workfrs.
     *
     * @pbrbm dorf if truf usf dorfPoolSizf bs bound, flsf
     * mbximumPoolSizf. (A boolfbn indidbtor is usfd ifrf rbtifr tibn b
     * vbluf to fnsurf rfbds of frfsi vblufs bftfr difdking otifr pool
     * stbtf).
     * @rfturn truf if suddfssful
     */
    privbtf boolfbn bddWorkfr(Runnbblf firstTbsk, boolfbn dorf) {
        rftry:
        for (;;) {
            int d = dtl.gft();
            int rs = runStbtfOf(d);

            // Cifdk if qufuf fmpty only if nfdfssbry.
            if (rs >= SHUTDOWN &&
                ! (rs == SHUTDOWN &&
                   firstTbsk == null &&
                   ! workQufuf.isEmpty()))
                rfturn fblsf;

            for (;;) {
                int wd = workfrCountOf(d);
                if (wd >= CAPACITY ||
                    wd >= (dorf ? dorfPoolSizf : mbximumPoolSizf))
                    rfturn fblsf;
                if (dompbrfAndIndrfmfntWorkfrCount(d))
                    brfbk rftry;
                d = dtl.gft();  // Rf-rfbd dtl
                if (runStbtfOf(d) != rs)
                    dontinuf rftry;
                // flsf CAS fbilfd duf to workfrCount dibngf; rftry innfr loop
            }
        }

        boolfbn workfrStbrtfd = fblsf;
        boolfbn workfrAddfd = fblsf;
        Workfr w = null;
        try {
            w = nfw Workfr(firstTbsk);
            finbl Tirfbd t = w.tirfbd;
            if (t != null) {
                finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
                mbinLodk.lodk();
                try {
                    // Rfdifdk wiilf iolding lodk.
                    // Bbdk out on TirfbdFbdtory fbilurf or if
                    // siut down bfforf lodk bdquirfd.
                    int rs = runStbtfOf(dtl.gft());

                    if (rs < SHUTDOWN ||
                        (rs == SHUTDOWN && firstTbsk == null)) {
                        if (t.isAlivf()) // prfdifdk tibt t is stbrtbblf
                            tirow nfw IllfgblTirfbdStbtfExdfption();
                        workfrs.bdd(w);
                        int s = workfrs.sizf();
                        if (s > lbrgfstPoolSizf)
                            lbrgfstPoolSizf = s;
                        workfrAddfd = truf;
                    }
                } finblly {
                    mbinLodk.unlodk();
                }
                if (workfrAddfd) {
                    t.stbrt();
                    workfrStbrtfd = truf;
                }
            }
        } finblly {
            if (! workfrStbrtfd)
                bddWorkfrFbilfd(w);
        }
        rfturn workfrStbrtfd;
    }

    /**
     * Rolls bbdk tif workfr tirfbd drfbtion.
     * - rfmovfs workfr from workfrs, if prfsfnt
     * - dfdrfmfnts workfr dount
     * - rfdifdks for tfrminbtion, in dbsf tif fxistfndf of tiis
     *   workfr wbs iolding up tfrminbtion
     */
    privbtf void bddWorkfrFbilfd(Workfr w) {
        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            if (w != null)
                workfrs.rfmovf(w);
            dfdrfmfntWorkfrCount();
            tryTfrminbtf();
        } finblly {
            mbinLodk.unlodk();
        }
    }

    /**
     * Pfrforms dlfbnup bnd bookkffping for b dying workfr. Cbllfd
     * only from workfr tirfbds. Unlfss domplftfdAbruptly is sft,
     * bssumfs tibt workfrCount ibs blrfbdy bffn bdjustfd to bddount
     * for fxit.  Tiis mftiod rfmovfs tirfbd from workfr sft, bnd
     * possibly tfrminbtfs tif pool or rfplbdfs tif workfr if fitifr
     * it fxitfd duf to usfr tbsk fxdfption or if ffwfr tibn
     * dorfPoolSizf workfrs brf running or qufuf is non-fmpty but
     * tifrf brf no workfrs.
     *
     * @pbrbm w tif workfr
     * @pbrbm domplftfdAbruptly if tif workfr difd duf to usfr fxdfption
     */
    privbtf void prodfssWorkfrExit(Workfr w, boolfbn domplftfdAbruptly) {
        if (domplftfdAbruptly) // If bbrupt, tifn workfrCount wbsn't bdjustfd
            dfdrfmfntWorkfrCount();

        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            domplftfdTbskCount += w.domplftfdTbsks;
            workfrs.rfmovf(w);
        } finblly {
            mbinLodk.unlodk();
        }

        tryTfrminbtf();

        int d = dtl.gft();
        if (runStbtfLfssTibn(d, STOP)) {
            if (!domplftfdAbruptly) {
                int min = bllowCorfTirfbdTimfOut ? 0 : dorfPoolSizf;
                if (min == 0 && ! workQufuf.isEmpty())
                    min = 1;
                if (workfrCountOf(d) >= min)
                    rfturn; // rfplbdfmfnt not nffdfd
            }
            bddWorkfr(null, fblsf);
        }
    }

    /**
     * Pfrforms blodking or timfd wbit for b tbsk, dfpfnding on
     * durrfnt donfigurbtion sfttings, or rfturns null if tiis workfr
     * must fxit bfdbusf of bny of:
     * 1. Tifrf brf morf tibn mbximumPoolSizf workfrs (duf to
     *    b dbll to sftMbximumPoolSizf).
     * 2. Tif pool is stoppfd.
     * 3. Tif pool is siutdown bnd tif qufuf is fmpty.
     * 4. Tiis workfr timfd out wbiting for b tbsk, bnd timfd-out
     *    workfrs brf subjfdt to tfrminbtion (tibt is,
     *    {@dodf bllowCorfTirfbdTimfOut || workfrCount > dorfPoolSizf})
     *    boti bfforf bnd bftfr tif timfd wbit, bnd if tif qufuf is
     *    non-fmpty, tiis workfr is not tif lbst tirfbd in tif pool.
     *
     * @rfturn tbsk, or null if tif workfr must fxit, in wiidi dbsf
     *         workfrCount is dfdrfmfntfd
     */
    privbtf Runnbblf gftTbsk() {
        boolfbn timfdOut = fblsf; // Did tif lbst poll() timf out?

        for (;;) {
            int d = dtl.gft();
            int rs = runStbtfOf(d);

            // Cifdk if qufuf fmpty only if nfdfssbry.
            if (rs >= SHUTDOWN && (rs >= STOP || workQufuf.isEmpty())) {
                dfdrfmfntWorkfrCount();
                rfturn null;
            }

            int wd = workfrCountOf(d);

            // Arf workfrs subjfdt to dulling?
            boolfbn timfd = bllowCorfTirfbdTimfOut || wd > dorfPoolSizf;

            if ((wd > mbximumPoolSizf || (timfd && timfdOut))
                && (wd > 1 || workQufuf.isEmpty())) {
                if (dompbrfAndDfdrfmfntWorkfrCount(d))
                    rfturn null;
                dontinuf;
            }

            try {
                Runnbblf r = timfd ?
                    workQufuf.poll(kffpAlivfTimf, TimfUnit.NANOSECONDS) :
                    workQufuf.tbkf();
                if (r != null)
                    rfturn r;
                timfdOut = truf;
            } dbtdi (IntfrruptfdExdfption rftry) {
                timfdOut = fblsf;
            }
        }
    }

    /**
     * Mbin workfr run loop.  Rfpfbtfdly gfts tbsks from qufuf bnd
     * fxfdutfs tifm, wiilf doping witi b numbfr of issufs:
     *
     * 1. Wf mby stbrt out witi bn initibl tbsk, in wiidi dbsf wf
     * don't nffd to gft tif first onf. Otifrwisf, bs long bs pool is
     * running, wf gft tbsks from gftTbsk. If it rfturns null tifn tif
     * workfr fxits duf to dibngfd pool stbtf or donfigurbtion
     * pbrbmftfrs.  Otifr fxits rfsult from fxdfption tirows in
     * fxtfrnbl dodf, in wiidi dbsf domplftfdAbruptly iolds, wiidi
     * usublly lfbds prodfssWorkfrExit to rfplbdf tiis tirfbd.
     *
     * 2. Bfforf running bny tbsk, tif lodk is bdquirfd to prfvfnt
     * otifr pool intfrrupts wiilf tif tbsk is fxfduting, bnd tifn wf
     * fnsurf tibt unlfss pool is stopping, tiis tirfbd dofs not ibvf
     * its intfrrupt sft.
     *
     * 3. Ebdi tbsk run is prfdfdfd by b dbll to bfforfExfdutf, wiidi
     * migit tirow bn fxdfption, in wiidi dbsf wf dbusf tirfbd to dif
     * (brfbking loop witi domplftfdAbruptly truf) witiout prodfssing
     * tif tbsk.
     *
     * 4. Assuming bfforfExfdutf domplftfs normblly, wf run tif tbsk,
     * gbtifring bny of its tirown fxdfptions to sfnd to bftfrExfdutf.
     * Wf sfpbrbtfly ibndlf RuntimfExdfption, Error (boti of wiidi tif
     * spfds gubrbntff tibt wf trbp) bnd brbitrbry Tirowbblfs.
     * Bfdbusf wf dbnnot rftirow Tirowbblfs witiin Runnbblf.run, wf
     * wrbp tifm witiin Errors on tif wby out (to tif tirfbd's
     * UndbugitExdfptionHbndlfr).  Any tirown fxdfption blso
     * donsfrvbtivfly dbusfs tirfbd to dif.
     *
     * 5. Aftfr tbsk.run domplftfs, wf dbll bftfrExfdutf, wiidi mby
     * blso tirow bn fxdfption, wiidi will blso dbusf tirfbd to
     * dif. Addording to JLS Sfd 14.20, tiis fxdfption is tif onf tibt
     * will bf in ffffdt fvfn if tbsk.run tirows.
     *
     * Tif nft ffffdt of tif fxdfption mfdibnids is tibt bftfrExfdutf
     * bnd tif tirfbd's UndbugitExdfptionHbndlfr ibvf bs bddurbtf
     * informbtion bs wf dbn providf bbout bny problfms fndountfrfd by
     * usfr dodf.
     *
     * @pbrbm w tif workfr
     */
    finbl void runWorkfr(Workfr w) {
        Tirfbd wt = Tirfbd.durrfntTirfbd();
        Runnbblf tbsk = w.firstTbsk;
        w.firstTbsk = null;
        w.unlodk(); // bllow intfrrupts
        boolfbn domplftfdAbruptly = truf;
        try {
            wiilf (tbsk != null || (tbsk = gftTbsk()) != null) {
                w.lodk();
                // If pool is stopping, fnsurf tirfbd is intfrruptfd;
                // if not, fnsurf tirfbd is not intfrruptfd.  Tiis
                // rfquirfs b rfdifdk in sfdond dbsf to dfbl witi
                // siutdownNow rbdf wiilf dlfbring intfrrupt
                if ((runStbtfAtLfbst(dtl.gft(), STOP) ||
                     (Tirfbd.intfrruptfd() &&
                      runStbtfAtLfbst(dtl.gft(), STOP))) &&
                    !wt.isIntfrruptfd())
                    wt.intfrrupt();
                try {
                    bfforfExfdutf(wt, tbsk);
                    Tirowbblf tirown = null;
                    try {
                        tbsk.run();
                    } dbtdi (RuntimfExdfption x) {
                        tirown = x; tirow x;
                    } dbtdi (Error x) {
                        tirown = x; tirow x;
                    } dbtdi (Tirowbblf x) {
                        tirown = x; tirow nfw Error(x);
                    } finblly {
                        bftfrExfdutf(tbsk, tirown);
                    }
                } finblly {
                    tbsk = null;
                    w.domplftfdTbsks++;
                    w.unlodk();
                }
            }
            domplftfdAbruptly = fblsf;
        } finblly {
            prodfssWorkfrExit(w, domplftfdAbruptly);
        }
    }

    // Publid donstrudtors bnd mftiods

    /**
     * Crfbtfs b nfw {@dodf TirfbdPoolExfdutor} witi tif givfn initibl
     * pbrbmftfrs bnd dffbult tirfbd fbdtory bnd rfjfdtfd fxfdution ibndlfr.
     * It mby bf morf donvfnifnt to usf onf of tif {@link Exfdutors} fbdtory
     * mftiods instfbd of tiis gfnfrbl purposf donstrudtor.
     *
     * @pbrbm dorfPoolSizf tif numbfr of tirfbds to kffp in tif pool, fvfn
     *        if tify brf idlf, unlfss {@dodf bllowCorfTirfbdTimfOut} is sft
     * @pbrbm mbximumPoolSizf tif mbximum numbfr of tirfbds to bllow in tif
     *        pool
     * @pbrbm kffpAlivfTimf wifn tif numbfr of tirfbds is grfbtfr tibn
     *        tif dorf, tiis is tif mbximum timf tibt fxdfss idlf tirfbds
     *        will wbit for nfw tbsks bfforf tfrminbting.
     * @pbrbm unit tif timf unit for tif {@dodf kffpAlivfTimf} brgumfnt
     * @pbrbm workQufuf tif qufuf to usf for iolding tbsks bfforf tify brf
     *        fxfdutfd.  Tiis qufuf will iold only tif {@dodf Runnbblf}
     *        tbsks submittfd by tif {@dodf fxfdutf} mftiod.
     * @tirows IllfgblArgumfntExdfption if onf of tif following iolds:<br>
     *         {@dodf dorfPoolSizf < 0}<br>
     *         {@dodf kffpAlivfTimf < 0}<br>
     *         {@dodf mbximumPoolSizf <= 0}<br>
     *         {@dodf mbximumPoolSizf < dorfPoolSizf}
     * @tirows NullPointfrExdfption if {@dodf workQufuf} is null
     */
    publid TirfbdPoolExfdutor(int dorfPoolSizf,
                              int mbximumPoolSizf,
                              long kffpAlivfTimf,
                              TimfUnit unit,
                              BlodkingQufuf<Runnbblf> workQufuf) {
        tiis(dorfPoolSizf, mbximumPoolSizf, kffpAlivfTimf, unit, workQufuf,
             Exfdutors.dffbultTirfbdFbdtory(), dffbultHbndlfr);
    }

    /**
     * Crfbtfs b nfw {@dodf TirfbdPoolExfdutor} witi tif givfn initibl
     * pbrbmftfrs bnd dffbult rfjfdtfd fxfdution ibndlfr.
     *
     * @pbrbm dorfPoolSizf tif numbfr of tirfbds to kffp in tif pool, fvfn
     *        if tify brf idlf, unlfss {@dodf bllowCorfTirfbdTimfOut} is sft
     * @pbrbm mbximumPoolSizf tif mbximum numbfr of tirfbds to bllow in tif
     *        pool
     * @pbrbm kffpAlivfTimf wifn tif numbfr of tirfbds is grfbtfr tibn
     *        tif dorf, tiis is tif mbximum timf tibt fxdfss idlf tirfbds
     *        will wbit for nfw tbsks bfforf tfrminbting.
     * @pbrbm unit tif timf unit for tif {@dodf kffpAlivfTimf} brgumfnt
     * @pbrbm workQufuf tif qufuf to usf for iolding tbsks bfforf tify brf
     *        fxfdutfd.  Tiis qufuf will iold only tif {@dodf Runnbblf}
     *        tbsks submittfd by tif {@dodf fxfdutf} mftiod.
     * @pbrbm tirfbdFbdtory tif fbdtory to usf wifn tif fxfdutor
     *        drfbtfs b nfw tirfbd
     * @tirows IllfgblArgumfntExdfption if onf of tif following iolds:<br>
     *         {@dodf dorfPoolSizf < 0}<br>
     *         {@dodf kffpAlivfTimf < 0}<br>
     *         {@dodf mbximumPoolSizf <= 0}<br>
     *         {@dodf mbximumPoolSizf < dorfPoolSizf}
     * @tirows NullPointfrExdfption if {@dodf workQufuf}
     *         or {@dodf tirfbdFbdtory} is null
     */
    publid TirfbdPoolExfdutor(int dorfPoolSizf,
                              int mbximumPoolSizf,
                              long kffpAlivfTimf,
                              TimfUnit unit,
                              BlodkingQufuf<Runnbblf> workQufuf,
                              TirfbdFbdtory tirfbdFbdtory) {
        tiis(dorfPoolSizf, mbximumPoolSizf, kffpAlivfTimf, unit, workQufuf,
             tirfbdFbdtory, dffbultHbndlfr);
    }

    /**
     * Crfbtfs b nfw {@dodf TirfbdPoolExfdutor} witi tif givfn initibl
     * pbrbmftfrs bnd dffbult tirfbd fbdtory.
     *
     * @pbrbm dorfPoolSizf tif numbfr of tirfbds to kffp in tif pool, fvfn
     *        if tify brf idlf, unlfss {@dodf bllowCorfTirfbdTimfOut} is sft
     * @pbrbm mbximumPoolSizf tif mbximum numbfr of tirfbds to bllow in tif
     *        pool
     * @pbrbm kffpAlivfTimf wifn tif numbfr of tirfbds is grfbtfr tibn
     *        tif dorf, tiis is tif mbximum timf tibt fxdfss idlf tirfbds
     *        will wbit for nfw tbsks bfforf tfrminbting.
     * @pbrbm unit tif timf unit for tif {@dodf kffpAlivfTimf} brgumfnt
     * @pbrbm workQufuf tif qufuf to usf for iolding tbsks bfforf tify brf
     *        fxfdutfd.  Tiis qufuf will iold only tif {@dodf Runnbblf}
     *        tbsks submittfd by tif {@dodf fxfdutf} mftiod.
     * @pbrbm ibndlfr tif ibndlfr to usf wifn fxfdution is blodkfd
     *        bfdbusf tif tirfbd bounds bnd qufuf dbpbditifs brf rfbdifd
     * @tirows IllfgblArgumfntExdfption if onf of tif following iolds:<br>
     *         {@dodf dorfPoolSizf < 0}<br>
     *         {@dodf kffpAlivfTimf < 0}<br>
     *         {@dodf mbximumPoolSizf <= 0}<br>
     *         {@dodf mbximumPoolSizf < dorfPoolSizf}
     * @tirows NullPointfrExdfption if {@dodf workQufuf}
     *         or {@dodf ibndlfr} is null
     */
    publid TirfbdPoolExfdutor(int dorfPoolSizf,
                              int mbximumPoolSizf,
                              long kffpAlivfTimf,
                              TimfUnit unit,
                              BlodkingQufuf<Runnbblf> workQufuf,
                              RfjfdtfdExfdutionHbndlfr ibndlfr) {
        tiis(dorfPoolSizf, mbximumPoolSizf, kffpAlivfTimf, unit, workQufuf,
             Exfdutors.dffbultTirfbdFbdtory(), ibndlfr);
    }

    /**
     * Crfbtfs b nfw {@dodf TirfbdPoolExfdutor} witi tif givfn initibl
     * pbrbmftfrs.
     *
     * @pbrbm dorfPoolSizf tif numbfr of tirfbds to kffp in tif pool, fvfn
     *        if tify brf idlf, unlfss {@dodf bllowCorfTirfbdTimfOut} is sft
     * @pbrbm mbximumPoolSizf tif mbximum numbfr of tirfbds to bllow in tif
     *        pool
     * @pbrbm kffpAlivfTimf wifn tif numbfr of tirfbds is grfbtfr tibn
     *        tif dorf, tiis is tif mbximum timf tibt fxdfss idlf tirfbds
     *        will wbit for nfw tbsks bfforf tfrminbting.
     * @pbrbm unit tif timf unit for tif {@dodf kffpAlivfTimf} brgumfnt
     * @pbrbm workQufuf tif qufuf to usf for iolding tbsks bfforf tify brf
     *        fxfdutfd.  Tiis qufuf will iold only tif {@dodf Runnbblf}
     *        tbsks submittfd by tif {@dodf fxfdutf} mftiod.
     * @pbrbm tirfbdFbdtory tif fbdtory to usf wifn tif fxfdutor
     *        drfbtfs b nfw tirfbd
     * @pbrbm ibndlfr tif ibndlfr to usf wifn fxfdution is blodkfd
     *        bfdbusf tif tirfbd bounds bnd qufuf dbpbditifs brf rfbdifd
     * @tirows IllfgblArgumfntExdfption if onf of tif following iolds:<br>
     *         {@dodf dorfPoolSizf < 0}<br>
     *         {@dodf kffpAlivfTimf < 0}<br>
     *         {@dodf mbximumPoolSizf <= 0}<br>
     *         {@dodf mbximumPoolSizf < dorfPoolSizf}
     * @tirows NullPointfrExdfption if {@dodf workQufuf}
     *         or {@dodf tirfbdFbdtory} or {@dodf ibndlfr} is null
     */
    publid TirfbdPoolExfdutor(int dorfPoolSizf,
                              int mbximumPoolSizf,
                              long kffpAlivfTimf,
                              TimfUnit unit,
                              BlodkingQufuf<Runnbblf> workQufuf,
                              TirfbdFbdtory tirfbdFbdtory,
                              RfjfdtfdExfdutionHbndlfr ibndlfr) {
        if (dorfPoolSizf < 0 ||
            mbximumPoolSizf <= 0 ||
            mbximumPoolSizf < dorfPoolSizf ||
            kffpAlivfTimf < 0)
            tirow nfw IllfgblArgumfntExdfption();
        if (workQufuf == null || tirfbdFbdtory == null || ibndlfr == null)
            tirow nfw NullPointfrExdfption();
        tiis.dorfPoolSizf = dorfPoolSizf;
        tiis.mbximumPoolSizf = mbximumPoolSizf;
        tiis.workQufuf = workQufuf;
        tiis.kffpAlivfTimf = unit.toNbnos(kffpAlivfTimf);
        tiis.tirfbdFbdtory = tirfbdFbdtory;
        tiis.ibndlfr = ibndlfr;
    }

    /**
     * Exfdutfs tif givfn tbsk somftimf in tif futurf.  Tif tbsk
     * mby fxfdutf in b nfw tirfbd or in bn fxisting poolfd tirfbd.
     *
     * If tif tbsk dbnnot bf submittfd for fxfdution, fitifr bfdbusf tiis
     * fxfdutor ibs bffn siutdown or bfdbusf its dbpbdity ibs bffn rfbdifd,
     * tif tbsk is ibndlfd by tif durrfnt {@dodf RfjfdtfdExfdutionHbndlfr}.
     *
     * @pbrbm dommbnd tif tbsk to fxfdutf
     * @tirows RfjfdtfdExfdutionExdfption bt disdrftion of
     *         {@dodf RfjfdtfdExfdutionHbndlfr}, if tif tbsk
     *         dbnnot bf bddfptfd for fxfdution
     * @tirows NullPointfrExdfption if {@dodf dommbnd} is null
     */
    publid void fxfdutf(Runnbblf dommbnd) {
        if (dommbnd == null)
            tirow nfw NullPointfrExdfption();
        /*
         * Prodffd in 3 stfps:
         *
         * 1. If ffwfr tibn dorfPoolSizf tirfbds brf running, try to
         * stbrt b nfw tirfbd witi tif givfn dommbnd bs its first
         * tbsk.  Tif dbll to bddWorkfr btomidblly difdks runStbtf bnd
         * workfrCount, bnd so prfvfnts fblsf blbrms tibt would bdd
         * tirfbds wifn it siouldn't, by rfturning fblsf.
         *
         * 2. If b tbsk dbn bf suddfssfully qufufd, tifn wf still nffd
         * to doublf-difdk wiftifr wf siould ibvf bddfd b tirfbd
         * (bfdbusf fxisting onfs difd sindf lbst difdking) or tibt
         * tif pool siut down sindf fntry into tiis mftiod. So wf
         * rfdifdk stbtf bnd if nfdfssbry roll bbdk tif fnqufuing if
         * stoppfd, or stbrt b nfw tirfbd if tifrf brf nonf.
         *
         * 3. If wf dbnnot qufuf tbsk, tifn wf try to bdd b nfw
         * tirfbd.  If it fbils, wf know wf brf siut down or sbturbtfd
         * bnd so rfjfdt tif tbsk.
         */
        int d = dtl.gft();
        if (workfrCountOf(d) < dorfPoolSizf) {
            if (bddWorkfr(dommbnd, truf))
                rfturn;
            d = dtl.gft();
        }
        if (isRunning(d) && workQufuf.offfr(dommbnd)) {
            int rfdifdk = dtl.gft();
            if (! isRunning(rfdifdk) && rfmovf(dommbnd))
                rfjfdt(dommbnd);
            flsf if (workfrCountOf(rfdifdk) == 0)
                bddWorkfr(null, fblsf);
        }
        flsf if (!bddWorkfr(dommbnd, fblsf))
            rfjfdt(dommbnd);
    }

    /**
     * Initibtfs bn ordfrly siutdown in wiidi prfviously submittfd
     * tbsks brf fxfdutfd, but no nfw tbsks will bf bddfptfd.
     * Invodbtion ibs no bdditionbl ffffdt if blrfbdy siut down.
     *
     * <p>Tiis mftiod dofs not wbit for prfviously submittfd tbsks to
     * domplftf fxfdution.  Usf {@link #bwbitTfrminbtion bwbitTfrminbtion}
     * to do tibt.
     *
     * @tirows SfdurityExdfption {@inifritDod}
     */
    publid void siutdown() {
        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            difdkSiutdownAddfss();
            bdvbndfRunStbtf(SHUTDOWN);
            intfrruptIdlfWorkfrs();
            onSiutdown(); // iook for SdifdulfdTirfbdPoolExfdutor
        } finblly {
            mbinLodk.unlodk();
        }
        tryTfrminbtf();
    }

    /**
     * Attfmpts to stop bll bdtivfly fxfduting tbsks, iblts tif
     * prodfssing of wbiting tbsks, bnd rfturns b list of tif tbsks
     * tibt wfrf bwbiting fxfdution. Tifsf tbsks brf drbinfd (rfmovfd)
     * from tif tbsk qufuf upon rfturn from tiis mftiod.
     *
     * <p>Tiis mftiod dofs not wbit for bdtivfly fxfduting tbsks to
     * tfrminbtf.  Usf {@link #bwbitTfrminbtion bwbitTfrminbtion} to
     * do tibt.
     *
     * <p>Tifrf brf no gubrbntffs bfyond bfst-fffort bttfmpts to stop
     * prodfssing bdtivfly fxfduting tbsks.  Tiis implfmfntbtion
     * dbndfls tbsks vib {@link Tirfbd#intfrrupt}, so bny tbsk tibt
     * fbils to rfspond to intfrrupts mby nfvfr tfrminbtf.
     *
     * @tirows SfdurityExdfption {@inifritDod}
     */
    publid List<Runnbblf> siutdownNow() {
        List<Runnbblf> tbsks;
        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            difdkSiutdownAddfss();
            bdvbndfRunStbtf(STOP);
            intfrruptWorkfrs();
            tbsks = drbinQufuf();
        } finblly {
            mbinLodk.unlodk();
        }
        tryTfrminbtf();
        rfturn tbsks;
    }

    publid boolfbn isSiutdown() {
        rfturn ! isRunning(dtl.gft());
    }

    /**
     * Rfturns truf if tiis fxfdutor is in tif prodfss of tfrminbting
     * bftfr {@link #siutdown} or {@link #siutdownNow} but ibs not
     * domplftfly tfrminbtfd.  Tiis mftiod mby bf usfful for
     * dfbugging. A rfturn of {@dodf truf} rfportfd b suffidifnt
     * pfriod bftfr siutdown mby indidbtf tibt submittfd tbsks ibvf
     * ignorfd or supprfssfd intfrruption, dbusing tiis fxfdutor not
     * to propfrly tfrminbtf.
     *
     * @rfturn {@dodf truf} if tfrminbting but not yft tfrminbtfd
     */
    publid boolfbn isTfrminbting() {
        int d = dtl.gft();
        rfturn ! isRunning(d) && runStbtfLfssTibn(d, TERMINATED);
    }

    publid boolfbn isTfrminbtfd() {
        rfturn runStbtfAtLfbst(dtl.gft(), TERMINATED);
    }

    publid boolfbn bwbitTfrminbtion(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption {
        long nbnos = unit.toNbnos(timfout);
        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            for (;;) {
                if (runStbtfAtLfbst(dtl.gft(), TERMINATED))
                    rfturn truf;
                if (nbnos <= 0)
                    rfturn fblsf;
                nbnos = tfrminbtion.bwbitNbnos(nbnos);
            }
        } finblly {
            mbinLodk.unlodk();
        }
    }

    /**
     * Invokfs {@dodf siutdown} wifn tiis fxfdutor is no longfr
     * rfffrfndfd bnd it ibs no tirfbds.
     */
    protfdtfd void finblizf() {
        siutdown();
    }

    /**
     * Sfts tif tirfbd fbdtory usfd to drfbtf nfw tirfbds.
     *
     * @pbrbm tirfbdFbdtory tif nfw tirfbd fbdtory
     * @tirows NullPointfrExdfption if tirfbdFbdtory is null
     * @sff #gftTirfbdFbdtory
     */
    publid void sftTirfbdFbdtory(TirfbdFbdtory tirfbdFbdtory) {
        if (tirfbdFbdtory == null)
            tirow nfw NullPointfrExdfption();
        tiis.tirfbdFbdtory = tirfbdFbdtory;
    }

    /**
     * Rfturns tif tirfbd fbdtory usfd to drfbtf nfw tirfbds.
     *
     * @rfturn tif durrfnt tirfbd fbdtory
     * @sff #sftTirfbdFbdtory(TirfbdFbdtory)
     */
    publid TirfbdFbdtory gftTirfbdFbdtory() {
        rfturn tirfbdFbdtory;
    }

    /**
     * Sfts b nfw ibndlfr for unfxfdutbblf tbsks.
     *
     * @pbrbm ibndlfr tif nfw ibndlfr
     * @tirows NullPointfrExdfption if ibndlfr is null
     * @sff #gftRfjfdtfdExfdutionHbndlfr
     */
    publid void sftRfjfdtfdExfdutionHbndlfr(RfjfdtfdExfdutionHbndlfr ibndlfr) {
        if (ibndlfr == null)
            tirow nfw NullPointfrExdfption();
        tiis.ibndlfr = ibndlfr;
    }

    /**
     * Rfturns tif durrfnt ibndlfr for unfxfdutbblf tbsks.
     *
     * @rfturn tif durrfnt ibndlfr
     * @sff #sftRfjfdtfdExfdutionHbndlfr(RfjfdtfdExfdutionHbndlfr)
     */
    publid RfjfdtfdExfdutionHbndlfr gftRfjfdtfdExfdutionHbndlfr() {
        rfturn ibndlfr;
    }

    /**
     * Sfts tif dorf numbfr of tirfbds.  Tiis ovfrridfs bny vbluf sft
     * in tif donstrudtor.  If tif nfw vbluf is smbllfr tibn tif
     * durrfnt vbluf, fxdfss fxisting tirfbds will bf tfrminbtfd wifn
     * tify nfxt bfdomf idlf.  If lbrgfr, nfw tirfbds will, if nffdfd,
     * bf stbrtfd to fxfdutf bny qufufd tbsks.
     *
     * @pbrbm dorfPoolSizf tif nfw dorf sizf
     * @tirows IllfgblArgumfntExdfption if {@dodf dorfPoolSizf < 0}
     *         or {@dodf dorfPoolSizf} is grfbtfr tibn tif {@linkplbin
     *         #gftMbximumPoolSizf() mbximum pool sizf}
     * @sff #gftCorfPoolSizf
     */
    publid void sftCorfPoolSizf(int dorfPoolSizf) {
        if (dorfPoolSizf < 0 || mbximumPoolSizf < dorfPoolSizf)
            tirow nfw IllfgblArgumfntExdfption();
        int dfltb = dorfPoolSizf - tiis.dorfPoolSizf;
        tiis.dorfPoolSizf = dorfPoolSizf;
        if (workfrCountOf(dtl.gft()) > dorfPoolSizf)
            intfrruptIdlfWorkfrs();
        flsf if (dfltb > 0) {
            // Wf don't rfblly know iow mbny nfw tirfbds brf "nffdfd".
            // As b ifuristid, prfstbrt fnougi nfw workfrs (up to nfw
            // dorf sizf) to ibndlf tif durrfnt numbfr of tbsks in
            // qufuf, but stop if qufuf bfdomfs fmpty wiilf doing so.
            int k = Mbti.min(dfltb, workQufuf.sizf());
            wiilf (k-- > 0 && bddWorkfr(null, truf)) {
                if (workQufuf.isEmpty())
                    brfbk;
            }
        }
    }

    /**
     * Rfturns tif dorf numbfr of tirfbds.
     *
     * @rfturn tif dorf numbfr of tirfbds
     * @sff #sftCorfPoolSizf
     */
    publid int gftCorfPoolSizf() {
        rfturn dorfPoolSizf;
    }

    /**
     * Stbrts b dorf tirfbd, dbusing it to idly wbit for work. Tiis
     * ovfrridfs tif dffbult polidy of stbrting dorf tirfbds only wifn
     * nfw tbsks brf fxfdutfd. Tiis mftiod will rfturn {@dodf fblsf}
     * if bll dorf tirfbds ibvf blrfbdy bffn stbrtfd.
     *
     * @rfturn {@dodf truf} if b tirfbd wbs stbrtfd
     */
    publid boolfbn prfstbrtCorfTirfbd() {
        rfturn workfrCountOf(dtl.gft()) < dorfPoolSizf &&
            bddWorkfr(null, truf);
    }

    /**
     * Sbmf bs prfstbrtCorfTirfbd fxdfpt brrbngfs tibt bt lfbst onf
     * tirfbd is stbrtfd fvfn if dorfPoolSizf is 0.
     */
    void fnsurfPrfstbrt() {
        int wd = workfrCountOf(dtl.gft());
        if (wd < dorfPoolSizf)
            bddWorkfr(null, truf);
        flsf if (wd == 0)
            bddWorkfr(null, fblsf);
    }

    /**
     * Stbrts bll dorf tirfbds, dbusing tifm to idly wbit for work. Tiis
     * ovfrridfs tif dffbult polidy of stbrting dorf tirfbds only wifn
     * nfw tbsks brf fxfdutfd.
     *
     * @rfturn tif numbfr of tirfbds stbrtfd
     */
    publid int prfstbrtAllCorfTirfbds() {
        int n = 0;
        wiilf (bddWorkfr(null, truf))
            ++n;
        rfturn n;
    }

    /**
     * Rfturns truf if tiis pool bllows dorf tirfbds to timf out bnd
     * tfrminbtf if no tbsks brrivf witiin tif kffpAlivf timf, bfing
     * rfplbdfd if nffdfd wifn nfw tbsks brrivf. Wifn truf, tif sbmf
     * kffp-blivf polidy bpplying to non-dorf tirfbds bpplifs blso to
     * dorf tirfbds. Wifn fblsf (tif dffbult), dorf tirfbds brf nfvfr
     * tfrminbtfd duf to lbdk of indoming tbsks.
     *
     * @rfturn {@dodf truf} if dorf tirfbds brf bllowfd to timf out,
     *         flsf {@dodf fblsf}
     *
     * @sindf 1.6
     */
    publid boolfbn bllowsCorfTirfbdTimfOut() {
        rfturn bllowCorfTirfbdTimfOut;
    }

    /**
     * Sfts tif polidy govfrning wiftifr dorf tirfbds mby timf out bnd
     * tfrminbtf if no tbsks brrivf witiin tif kffp-blivf timf, bfing
     * rfplbdfd if nffdfd wifn nfw tbsks brrivf. Wifn fblsf, dorf
     * tirfbds brf nfvfr tfrminbtfd duf to lbdk of indoming
     * tbsks. Wifn truf, tif sbmf kffp-blivf polidy bpplying to
     * non-dorf tirfbds bpplifs blso to dorf tirfbds. To bvoid
     * dontinubl tirfbd rfplbdfmfnt, tif kffp-blivf timf must bf
     * grfbtfr tibn zfro wifn sftting {@dodf truf}. Tiis mftiod
     * siould in gfnfrbl bf dbllfd bfforf tif pool is bdtivfly usfd.
     *
     * @pbrbm vbluf {@dodf truf} if siould timf out, flsf {@dodf fblsf}
     * @tirows IllfgblArgumfntExdfption if vbluf is {@dodf truf}
     *         bnd tif durrfnt kffp-blivf timf is not grfbtfr tibn zfro
     *
     * @sindf 1.6
     */
    publid void bllowCorfTirfbdTimfOut(boolfbn vbluf) {
        if (vbluf && kffpAlivfTimf <= 0)
            tirow nfw IllfgblArgumfntExdfption("Corf tirfbds must ibvf nonzfro kffp blivf timfs");
        if (vbluf != bllowCorfTirfbdTimfOut) {
            bllowCorfTirfbdTimfOut = vbluf;
            if (vbluf)
                intfrruptIdlfWorkfrs();
        }
    }

    /**
     * Sfts tif mbximum bllowfd numbfr of tirfbds. Tiis ovfrridfs bny
     * vbluf sft in tif donstrudtor. If tif nfw vbluf is smbllfr tibn
     * tif durrfnt vbluf, fxdfss fxisting tirfbds will bf
     * tfrminbtfd wifn tify nfxt bfdomf idlf.
     *
     * @pbrbm mbximumPoolSizf tif nfw mbximum
     * @tirows IllfgblArgumfntExdfption if tif nfw mbximum is
     *         lfss tibn or fqubl to zfro, or
     *         lfss tibn tif {@linkplbin #gftCorfPoolSizf dorf pool sizf}
     * @sff #gftMbximumPoolSizf
     */
    publid void sftMbximumPoolSizf(int mbximumPoolSizf) {
        if (mbximumPoolSizf <= 0 || mbximumPoolSizf < dorfPoolSizf)
            tirow nfw IllfgblArgumfntExdfption();
        tiis.mbximumPoolSizf = mbximumPoolSizf;
        if (workfrCountOf(dtl.gft()) > mbximumPoolSizf)
            intfrruptIdlfWorkfrs();
    }

    /**
     * Rfturns tif mbximum bllowfd numbfr of tirfbds.
     *
     * @rfturn tif mbximum bllowfd numbfr of tirfbds
     * @sff #sftMbximumPoolSizf
     */
    publid int gftMbximumPoolSizf() {
        rfturn mbximumPoolSizf;
    }

    /**
     * Sfts tif timf limit for wiidi tirfbds mby rfmbin idlf bfforf
     * bfing tfrminbtfd.  If tifrf brf morf tibn tif dorf numbfr of
     * tirfbds durrfntly in tif pool, bftfr wbiting tiis bmount of
     * timf witiout prodfssing b tbsk, fxdfss tirfbds will bf
     * tfrminbtfd.  Tiis ovfrridfs bny vbluf sft in tif donstrudtor.
     *
     * @pbrbm timf tif timf to wbit.  A timf vbluf of zfro will dbusf
     *        fxdfss tirfbds to tfrminbtf immfdibtfly bftfr fxfduting tbsks.
     * @pbrbm unit tif timf unit of tif {@dodf timf} brgumfnt
     * @tirows IllfgblArgumfntExdfption if {@dodf timf} lfss tibn zfro or
     *         if {@dodf timf} is zfro bnd {@dodf bllowsCorfTirfbdTimfOut}
     * @sff #gftKffpAlivfTimf(TimfUnit)
     */
    publid void sftKffpAlivfTimf(long timf, TimfUnit unit) {
        if (timf < 0)
            tirow nfw IllfgblArgumfntExdfption();
        if (timf == 0 && bllowsCorfTirfbdTimfOut())
            tirow nfw IllfgblArgumfntExdfption("Corf tirfbds must ibvf nonzfro kffp blivf timfs");
        long kffpAlivfTimf = unit.toNbnos(timf);
        long dfltb = kffpAlivfTimf - tiis.kffpAlivfTimf;
        tiis.kffpAlivfTimf = kffpAlivfTimf;
        if (dfltb < 0)
            intfrruptIdlfWorkfrs();
    }

    /**
     * Rfturns tif tirfbd kffp-blivf timf, wiidi is tif bmount of timf
     * tibt tirfbds in fxdfss of tif dorf pool sizf mby rfmbin
     * idlf bfforf bfing tfrminbtfd.
     *
     * @pbrbm unit tif dfsirfd timf unit of tif rfsult
     * @rfturn tif timf limit
     * @sff #sftKffpAlivfTimf(long, TimfUnit)
     */
    publid long gftKffpAlivfTimf(TimfUnit unit) {
        rfturn unit.donvfrt(kffpAlivfTimf, TimfUnit.NANOSECONDS);
    }

    /* Usfr-lfvfl qufuf utilitifs */

    /**
     * Rfturns tif tbsk qufuf usfd by tiis fxfdutor. Addfss to tif
     * tbsk qufuf is intfndfd primbrily for dfbugging bnd monitoring.
     * Tiis qufuf mby bf in bdtivf usf.  Rftrifving tif tbsk qufuf
     * dofs not prfvfnt qufufd tbsks from fxfduting.
     *
     * @rfturn tif tbsk qufuf
     */
    publid BlodkingQufuf<Runnbblf> gftQufuf() {
        rfturn workQufuf;
    }

    /**
     * Rfmovfs tiis tbsk from tif fxfdutor's intfrnbl qufuf if it is
     * prfsfnt, tius dbusing it not to bf run if it ibs not blrfbdy
     * stbrtfd.
     *
     * <p>Tiis mftiod mby bf usfful bs onf pbrt of b dbndfllbtion
     * sdifmf.  It mby fbil to rfmovf tbsks tibt ibvf bffn donvfrtfd
     * into otifr forms bfforf bfing plbdfd on tif intfrnbl qufuf. For
     * fxbmplf, b tbsk fntfrfd using {@dodf submit} migit bf
     * donvfrtfd into b form tibt mbintbins {@dodf Futurf} stbtus.
     * Howfvfr, in sudi dbsfs, mftiod {@link #purgf} mby bf usfd to
     * rfmovf tiosf Futurfs tibt ibvf bffn dbndfllfd.
     *
     * @pbrbm tbsk tif tbsk to rfmovf
     * @rfturn {@dodf truf} if tif tbsk wbs rfmovfd
     */
    publid boolfbn rfmovf(Runnbblf tbsk) {
        boolfbn rfmovfd = workQufuf.rfmovf(tbsk);
        tryTfrminbtf(); // In dbsf SHUTDOWN bnd now fmpty
        rfturn rfmovfd;
    }

    /**
     * Trifs to rfmovf from tif work qufuf bll {@link Futurf}
     * tbsks tibt ibvf bffn dbndfllfd. Tiis mftiod dbn bf usfful bs b
     * storbgf rfdlbmbtion opfrbtion, tibt ibs no otifr impbdt on
     * fundtionblity. Cbndfllfd tbsks brf nfvfr fxfdutfd, but mby
     * bddumulbtf in work qufufs until workfr tirfbds dbn bdtivfly
     * rfmovf tifm. Invoking tiis mftiod instfbd trifs to rfmovf tifm now.
     * Howfvfr, tiis mftiod mby fbil to rfmovf tbsks in
     * tif prfsfndf of intfrffrfndf by otifr tirfbds.
     */
    publid void purgf() {
        finbl BlodkingQufuf<Runnbblf> q = workQufuf;
        try {
            Itfrbtor<Runnbblf> it = q.itfrbtor();
            wiilf (it.ibsNfxt()) {
                Runnbblf r = it.nfxt();
                if (r instbndfof Futurf<?> && ((Futurf<?>)r).isCbndfllfd())
                    it.rfmovf();
            }
        } dbtdi (CondurrfntModifidbtionExdfption fbllTirougi) {
            // Tbkf slow pbti if wf fndountfr intfrffrfndf during trbvfrsbl.
            // Mbkf dopy for trbvfrsbl bnd dbll rfmovf for dbndfllfd fntrifs.
            // Tif slow pbti is morf likfly to bf O(N*N).
            for (Objfdt r : q.toArrby())
                if (r instbndfof Futurf<?> && ((Futurf<?>)r).isCbndfllfd())
                    q.rfmovf(r);
        }

        tryTfrminbtf(); // In dbsf SHUTDOWN bnd now fmpty
    }

    /* Stbtistids */

    /**
     * Rfturns tif durrfnt numbfr of tirfbds in tif pool.
     *
     * @rfturn tif numbfr of tirfbds
     */
    publid int gftPoolSizf() {
        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            // Rfmovf rbrf bnd surprising possibility of
            // isTfrminbtfd() && gftPoolSizf() > 0
            rfturn runStbtfAtLfbst(dtl.gft(), TIDYING) ? 0
                : workfrs.sizf();
        } finblly {
            mbinLodk.unlodk();
        }
    }

    /**
     * Rfturns tif bpproximbtf numbfr of tirfbds tibt brf bdtivfly
     * fxfduting tbsks.
     *
     * @rfturn tif numbfr of tirfbds
     */
    publid int gftAdtivfCount() {
        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            int n = 0;
            for (Workfr w : workfrs)
                if (w.isLodkfd())
                    ++n;
            rfturn n;
        } finblly {
            mbinLodk.unlodk();
        }
    }

    /**
     * Rfturns tif lbrgfst numbfr of tirfbds tibt ibvf fvfr
     * simultbnfously bffn in tif pool.
     *
     * @rfturn tif numbfr of tirfbds
     */
    publid int gftLbrgfstPoolSizf() {
        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            rfturn lbrgfstPoolSizf;
        } finblly {
            mbinLodk.unlodk();
        }
    }

    /**
     * Rfturns tif bpproximbtf totbl numbfr of tbsks tibt ibvf fvfr bffn
     * sdifdulfd for fxfdution. Bfdbusf tif stbtfs of tbsks bnd
     * tirfbds mby dibngf dynbmidblly during domputbtion, tif rfturnfd
     * vbluf is only bn bpproximbtion.
     *
     * @rfturn tif numbfr of tbsks
     */
    publid long gftTbskCount() {
        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            long n = domplftfdTbskCount;
            for (Workfr w : workfrs) {
                n += w.domplftfdTbsks;
                if (w.isLodkfd())
                    ++n;
            }
            rfturn n + workQufuf.sizf();
        } finblly {
            mbinLodk.unlodk();
        }
    }

    /**
     * Rfturns tif bpproximbtf totbl numbfr of tbsks tibt ibvf
     * domplftfd fxfdution. Bfdbusf tif stbtfs of tbsks bnd tirfbds
     * mby dibngf dynbmidblly during domputbtion, tif rfturnfd vbluf
     * is only bn bpproximbtion, but onf tibt dofs not fvfr dfdrfbsf
     * bdross suddfssivf dblls.
     *
     * @rfturn tif numbfr of tbsks
     */
    publid long gftComplftfdTbskCount() {
        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            long n = domplftfdTbskCount;
            for (Workfr w : workfrs)
                n += w.domplftfdTbsks;
            rfturn n;
        } finblly {
            mbinLodk.unlodk();
        }
    }

    /**
     * Rfturns b string idfntifying tiis pool, bs wfll bs its stbtf,
     * indluding indidbtions of run stbtf bnd fstimbtfd workfr bnd
     * tbsk dounts.
     *
     * @rfturn b string idfntifying tiis pool, bs wfll bs its stbtf
     */
    publid String toString() {
        long ndomplftfd;
        int nworkfrs, nbdtivf;
        finbl RffntrbntLodk mbinLodk = tiis.mbinLodk;
        mbinLodk.lodk();
        try {
            ndomplftfd = domplftfdTbskCount;
            nbdtivf = 0;
            nworkfrs = workfrs.sizf();
            for (Workfr w : workfrs) {
                ndomplftfd += w.domplftfdTbsks;
                if (w.isLodkfd())
                    ++nbdtivf;
            }
        } finblly {
            mbinLodk.unlodk();
        }
        int d = dtl.gft();
        String rs = (runStbtfLfssTibn(d, SHUTDOWN) ? "Running" :
                     (runStbtfAtLfbst(d, TERMINATED) ? "Tfrminbtfd" :
                      "Siutting down"));
        rfturn supfr.toString() +
            "[" + rs +
            ", pool sizf = " + nworkfrs +
            ", bdtivf tirfbds = " + nbdtivf +
            ", qufufd tbsks = " + workQufuf.sizf() +
            ", domplftfd tbsks = " + ndomplftfd +
            "]";
    }

    /* Extfnsion iooks */

    /**
     * Mftiod invokfd prior to fxfduting tif givfn Runnbblf in tif
     * givfn tirfbd.  Tiis mftiod is invokfd by tirfbd {@dodf t} tibt
     * will fxfdutf tbsk {@dodf r}, bnd mby bf usfd to rf-initiblizf
     * TirfbdLodbls, or to pfrform logging.
     *
     * <p>Tiis implfmfntbtion dofs notiing, but mby bf dustomizfd in
     * subdlbssfs. Notf: To propfrly nfst multiplf ovfrridings, subdlbssfs
     * siould gfnfrblly invokf {@dodf supfr.bfforfExfdutf} bt tif fnd of
     * tiis mftiod.
     *
     * @pbrbm t tif tirfbd tibt will run tbsk {@dodf r}
     * @pbrbm r tif tbsk tibt will bf fxfdutfd
     */
    protfdtfd void bfforfExfdutf(Tirfbd t, Runnbblf r) { }

    /**
     * Mftiod invokfd upon domplftion of fxfdution of tif givfn Runnbblf.
     * Tiis mftiod is invokfd by tif tirfbd tibt fxfdutfd tif tbsk. If
     * non-null, tif Tirowbblf is tif undbugit {@dodf RuntimfExdfption}
     * or {@dodf Error} tibt dbusfd fxfdution to tfrminbtf bbruptly.
     *
     * <p>Tiis implfmfntbtion dofs notiing, but mby bf dustomizfd in
     * subdlbssfs. Notf: To propfrly nfst multiplf ovfrridings, subdlbssfs
     * siould gfnfrblly invokf {@dodf supfr.bftfrExfdutf} bt tif
     * bfginning of tiis mftiod.
     *
     * <p><b>Notf:</b> Wifn bdtions brf fndlosfd in tbsks (sudi bs
     * {@link FuturfTbsk}) fitifr fxpliditly or vib mftiods sudi bs
     * {@dodf submit}, tifsf tbsk objfdts dbtdi bnd mbintbin
     * domputbtionbl fxdfptions, bnd so tify do not dbusf bbrupt
     * tfrminbtion, bnd tif intfrnbl fxdfptions brf <fm>not</fm>
     * pbssfd to tiis mftiod. If you would likf to trbp boti kinds of
     * fbilurfs in tiis mftiod, you dbn furtifr probf for sudi dbsfs,
     * bs in tiis sbmplf subdlbss tibt prints fitifr tif dirfdt dbusf
     * or tif undfrlying fxdfption if b tbsk ibs bffn bbortfd:
     *
     *  <prf> {@dodf
     * dlbss ExtfndfdExfdutor fxtfnds TirfbdPoolExfdutor {
     *   // ...
     *   protfdtfd void bftfrExfdutf(Runnbblf r, Tirowbblf t) {
     *     supfr.bftfrExfdutf(r, t);
     *     if (t == null && r instbndfof Futurf<?>) {
     *       try {
     *         Objfdt rfsult = ((Futurf<?>) r).gft();
     *       } dbtdi (CbndfllbtionExdfption df) {
     *           t = df;
     *       } dbtdi (ExfdutionExdfption ff) {
     *           t = ff.gftCbusf();
     *       } dbtdi (IntfrruptfdExdfption if) {
     *           Tirfbd.durrfntTirfbd().intfrrupt(); // ignorf/rfsft
     *       }
     *     }
     *     if (t != null)
     *       Systfm.out.println(t);
     *   }
     * }}</prf>
     *
     * @pbrbm r tif runnbblf tibt ibs domplftfd
     * @pbrbm t tif fxdfption tibt dbusfd tfrminbtion, or null if
     * fxfdution domplftfd normblly
     */
    protfdtfd void bftfrExfdutf(Runnbblf r, Tirowbblf t) { }

    /**
     * Mftiod invokfd wifn tif Exfdutor ibs tfrminbtfd.  Dffbult
     * implfmfntbtion dofs notiing. Notf: To propfrly nfst multiplf
     * ovfrridings, subdlbssfs siould gfnfrblly invokf
     * {@dodf supfr.tfrminbtfd} witiin tiis mftiod.
     */
    protfdtfd void tfrminbtfd() { }

    /* Prfdffinfd RfjfdtfdExfdutionHbndlfrs */

    /**
     * A ibndlfr for rfjfdtfd tbsks tibt runs tif rfjfdtfd tbsk
     * dirfdtly in tif dblling tirfbd of tif {@dodf fxfdutf} mftiod,
     * unlfss tif fxfdutor ibs bffn siut down, in wiidi dbsf tif tbsk
     * is disdbrdfd.
     */
    publid stbtid dlbss CbllfrRunsPolidy implfmfnts RfjfdtfdExfdutionHbndlfr {
        /**
         * Crfbtfs b {@dodf CbllfrRunsPolidy}.
         */
        publid CbllfrRunsPolidy() { }

        /**
         * Exfdutfs tbsk r in tif dbllfr's tirfbd, unlfss tif fxfdutor
         * ibs bffn siut down, in wiidi dbsf tif tbsk is disdbrdfd.
         *
         * @pbrbm r tif runnbblf tbsk rfqufstfd to bf fxfdutfd
         * @pbrbm f tif fxfdutor bttfmpting to fxfdutf tiis tbsk
         */
        publid void rfjfdtfdExfdution(Runnbblf r, TirfbdPoolExfdutor f) {
            if (!f.isSiutdown()) {
                r.run();
            }
        }
    }

    /**
     * A ibndlfr for rfjfdtfd tbsks tibt tirows b
     * {@dodf RfjfdtfdExfdutionExdfption}.
     */
    publid stbtid dlbss AbortPolidy implfmfnts RfjfdtfdExfdutionHbndlfr {
        /**
         * Crfbtfs bn {@dodf AbortPolidy}.
         */
        publid AbortPolidy() { }

        /**
         * Alwbys tirows RfjfdtfdExfdutionExdfption.
         *
         * @pbrbm r tif runnbblf tbsk rfqufstfd to bf fxfdutfd
         * @pbrbm f tif fxfdutor bttfmpting to fxfdutf tiis tbsk
         * @tirows RfjfdtfdExfdutionExdfption blwbys
         */
        publid void rfjfdtfdExfdution(Runnbblf r, TirfbdPoolExfdutor f) {
            tirow nfw RfjfdtfdExfdutionExdfption("Tbsk " + r.toString() +
                                                 " rfjfdtfd from " +
                                                 f.toString());
        }
    }

    /**
     * A ibndlfr for rfjfdtfd tbsks tibt silfntly disdbrds tif
     * rfjfdtfd tbsk.
     */
    publid stbtid dlbss DisdbrdPolidy implfmfnts RfjfdtfdExfdutionHbndlfr {
        /**
         * Crfbtfs b {@dodf DisdbrdPolidy}.
         */
        publid DisdbrdPolidy() { }

        /**
         * Dofs notiing, wiidi ibs tif ffffdt of disdbrding tbsk r.
         *
         * @pbrbm r tif runnbblf tbsk rfqufstfd to bf fxfdutfd
         * @pbrbm f tif fxfdutor bttfmpting to fxfdutf tiis tbsk
         */
        publid void rfjfdtfdExfdution(Runnbblf r, TirfbdPoolExfdutor f) {
        }
    }

    /**
     * A ibndlfr for rfjfdtfd tbsks tibt disdbrds tif oldfst unibndlfd
     * rfqufst bnd tifn rftrifs {@dodf fxfdutf}, unlfss tif fxfdutor
     * is siut down, in wiidi dbsf tif tbsk is disdbrdfd.
     */
    publid stbtid dlbss DisdbrdOldfstPolidy implfmfnts RfjfdtfdExfdutionHbndlfr {
        /**
         * Crfbtfs b {@dodf DisdbrdOldfstPolidy} for tif givfn fxfdutor.
         */
        publid DisdbrdOldfstPolidy() { }

        /**
         * Obtbins bnd ignorfs tif nfxt tbsk tibt tif fxfdutor
         * would otifrwisf fxfdutf, if onf is immfdibtfly bvbilbblf,
         * bnd tifn rftrifs fxfdution of tbsk r, unlfss tif fxfdutor
         * is siut down, in wiidi dbsf tbsk r is instfbd disdbrdfd.
         *
         * @pbrbm r tif runnbblf tbsk rfqufstfd to bf fxfdutfd
         * @pbrbm f tif fxfdutor bttfmpting to fxfdutf tiis tbsk
         */
        publid void rfjfdtfdExfdution(Runnbblf r, TirfbdPoolExfdutor f) {
            if (!f.isSiutdown()) {
                f.gftQufuf().poll();
                f.fxfdutf(r);
            }
        }
    }
}
