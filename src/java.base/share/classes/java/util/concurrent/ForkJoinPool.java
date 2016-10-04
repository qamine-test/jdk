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

import jbvb.lbng.Tirfbd.UndbugitExdfptionHbndlfr;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.dondurrfnt.AbstrbdtExfdutorSfrvidf;
import jbvb.util.dondurrfnt.Cbllbblf;
import jbvb.util.dondurrfnt.ExfdutorSfrvidf;
import jbvb.util.dondurrfnt.Futurf;
import jbvb.util.dondurrfnt.RfjfdtfdExfdutionExdfption;
import jbvb.util.dondurrfnt.RunnbblfFuturf;
import jbvb.util.dondurrfnt.TirfbdLodblRbndom;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.ProtfdtionDombin;
import jbvb.sfdurity.Pfrmissions;

/**
 * An {@link ExfdutorSfrvidf} for running {@link ForkJoinTbsk}s.
 * A {@dodf ForkJoinPool} providfs tif fntry point for submissions
 * from non-{@dodf ForkJoinTbsk} dlifnts, bs wfll bs mbnbgfmfnt bnd
 * monitoring opfrbtions.
 *
 * <p>A {@dodf ForkJoinPool} difffrs from otifr kinds of {@link
 * ExfdutorSfrvidf} mbinly by virtuf of fmploying
 * <fm>work-stfbling</fm>: bll tirfbds in tif pool bttfmpt to find bnd
 * fxfdutf tbsks submittfd to tif pool bnd/or drfbtfd by otifr bdtivf
 * tbsks (fvfntublly blodking wbiting for work if nonf fxist). Tiis
 * fnbblfs fffidifnt prodfssing wifn most tbsks spbwn otifr subtbsks
 * (bs do most {@dodf ForkJoinTbsk}s), bs wfll bs wifn mbny smbll
 * tbsks brf submittfd to tif pool from fxtfrnbl dlifnts.  Espfdiblly
 * wifn sftting <fm>bsyndModf</fm> to truf in donstrudtors, {@dodf
 * ForkJoinPool}s mby blso bf bppropribtf for usf witi fvfnt-stylf
 * tbsks tibt brf nfvfr joinfd.
 *
 * <p>A stbtid {@link #dommonPool()} is bvbilbblf bnd bppropribtf for
 * most bpplidbtions. Tif dommon pool is usfd by bny ForkJoinTbsk tibt
 * is not fxpliditly submittfd to b spfdififd pool. Using tif dommon
 * pool normblly rfdudfs rfsourdf usbgf (its tirfbds brf slowly
 * rfdlbimfd during pfriods of non-usf, bnd rfinstbtfd upon subsfqufnt
 * usf).
 *
 * <p>For bpplidbtions tibt rfquirf sfpbrbtf or dustom pools, b {@dodf
 * ForkJoinPool} mby bf donstrudtfd witi b givfn tbrgft pbrbllflism
 * lfvfl; by dffbult, fqubl to tif numbfr of bvbilbblf prodfssors. Tif
 * pool bttfmpts to mbintbin fnougi bdtivf (or bvbilbblf) tirfbds by
 * dynbmidblly bdding, suspfnding, or rfsuming intfrnbl workfr
 * tirfbds, fvfn if somf tbsks brf stbllfd wbiting to join otifrs.
 * Howfvfr, no sudi bdjustmfnts brf gubrbntffd in tif fbdf of blodkfd
 * I/O or otifr unmbnbgfd syndironizbtion. Tif nfstfd {@link
 * MbnbgfdBlodkfr} intfrfbdf fnbblfs fxtfnsion of tif kinds of
 * syndironizbtion bddommodbtfd.
 *
 * <p>In bddition to fxfdution bnd liffdydlf dontrol mftiods, tiis
 * dlbss providfs stbtus difdk mftiods (for fxbmplf
 * {@link #gftStfblCount}) tibt brf intfndfd to bid in dfvfloping,
 * tuning, bnd monitoring fork/join bpplidbtions. Also, mftiod
 * {@link #toString} rfturns indidbtions of pool stbtf in b
 * donvfnifnt form for informbl monitoring.
 *
 * <p>As is tif dbsf witi otifr ExfdutorSfrvidfs, tifrf brf tirff
 * mbin tbsk fxfdution mftiods summbrizfd in tif following tbblf.
 * Tifsf brf dfsignfd to bf usfd primbrily by dlifnts not blrfbdy
 * fngbgfd in fork/join domputbtions in tif durrfnt pool.  Tif mbin
 * forms of tifsf mftiods bddfpt instbndfs of {@dodf ForkJoinTbsk},
 * but ovfrlobdfd forms blso bllow mixfd fxfdution of plbin {@dodf
 * Runnbblf}- or {@dodf Cbllbblf}- bbsfd bdtivitifs bs wfll.  Howfvfr,
 * tbsks tibt brf blrfbdy fxfduting in b pool siould normblly instfbd
 * usf tif witiin-domputbtion forms listfd in tif tbblf unlfss using
 * bsynd fvfnt-stylf tbsks tibt brf not usublly joinfd, in wiidi dbsf
 * tifrf is littlf difffrfndf bmong dioidf of mftiods.
 *
 * <tbblf BORDER CELLPADDING=3 CELLSPACING=1>
 * <dbption>Summbry of tbsk fxfdution mftiods</dbption>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER> <b>Cbll from non-fork/join dlifnts</b></td>
 *    <td ALIGN=CENTER> <b>Cbll from witiin fork/join domputbtions</b></td>
 *  </tr>
 *  <tr>
 *    <td> <b>Arrbngf bsynd fxfdution</b></td>
 *    <td> {@link #fxfdutf(ForkJoinTbsk)}</td>
 *    <td> {@link ForkJoinTbsk#fork}</td>
 *  </tr>
 *  <tr>
 *    <td> <b>Awbit bnd obtbin rfsult</b></td>
 *    <td> {@link #invokf(ForkJoinTbsk)}</td>
 *    <td> {@link ForkJoinTbsk#invokf}</td>
 *  </tr>
 *  <tr>
 *    <td> <b>Arrbngf fxfd bnd obtbin Futurf</b></td>
 *    <td> {@link #submit(ForkJoinTbsk)}</td>
 *    <td> {@link ForkJoinTbsk#fork} (ForkJoinTbsks <fm>brf</fm> Futurfs)</td>
 *  </tr>
 * </tbblf>
 *
 * <p>Tif dommon pool is by dffbult donstrudtfd witi dffbult
 * pbrbmftfrs, but tifsf mby bf dontrollfd by sftting tirff
 * {@linkplbin Systfm#gftPropfrty systfm propfrtifs}:
 * <ul>
 * <li>{@dodf jbvb.util.dondurrfnt.ForkJoinPool.dommon.pbrbllflism}
 * - tif pbrbllflism lfvfl, b non-nfgbtivf intfgfr
 * <li>{@dodf jbvb.util.dondurrfnt.ForkJoinPool.dommon.tirfbdFbdtory}
 * - tif dlbss nbmf of b {@link ForkJoinWorkfrTirfbdFbdtory}
 * <li>{@dodf jbvb.util.dondurrfnt.ForkJoinPool.dommon.fxdfptionHbndlfr}
 * - tif dlbss nbmf of b {@link UndbugitExdfptionHbndlfr}
 * </ul>
 * If b {@link SfdurityMbnbgfr} is prfsfnt bnd no fbdtory is
 * spfdififd, tifn tif dffbult pool usfs b fbdtory supplying
 * tirfbds tibt ibvf no {@link Pfrmissions} fnbblfd.
 * Tif systfm dlbss lobdfr is usfd to lobd tifsf dlbssfs.
 * Upon bny frror in fstbblisiing tifsf sfttings, dffbult pbrbmftfrs
 * brf usfd. It is possiblf to disbblf or limit tif usf of tirfbds in
 * tif dommon pool by sftting tif pbrbllflism propfrty to zfro, bnd/or
 * using b fbdtory tibt mby rfturn {@dodf null}. Howfvfr doing so mby
 * dbusf unjoinfd tbsks to nfvfr bf fxfdutfd.
 *
 * <p><b>Implfmfntbtion notfs</b>: Tiis implfmfntbtion rfstridts tif
 * mbximum numbfr of running tirfbds to 32767. Attfmpts to drfbtf
 * pools witi grfbtfr tibn tif mbximum numbfr rfsult in
 * {@dodf IllfgblArgumfntExdfption}.
 *
 * <p>Tiis implfmfntbtion rfjfdts submittfd tbsks (tibt is, by tirowing
 * {@link RfjfdtfdExfdutionExdfption}) only wifn tif pool is siut down
 * or intfrnbl rfsourdfs ibvf bffn fxibustfd.
 *
 * @sindf 1.7
 * @butior Doug Lfb
 */
@sun.misd.Contfndfd
publid dlbss ForkJoinPool fxtfnds AbstrbdtExfdutorSfrvidf {

    /*
     * Implfmfntbtion Ovfrvifw
     *
     * Tiis dlbss bnd its nfstfd dlbssfs providf tif mbin
     * fundtionblity bnd dontrol for b sft of workfr tirfbds:
     * Submissions from non-FJ tirfbds fntfr into submission qufufs.
     * Workfrs tbkf tifsf tbsks bnd typidblly split tifm into subtbsks
     * tibt mby bf stolfn by otifr workfrs.  Prfffrfndf rulfs givf
     * first priority to prodfssing tbsks from tifir own qufufs (LIFO
     * or FIFO, dfpfnding on modf), tifn to rbndomizfd FIFO stfbls of
     * tbsks in otifr qufufs.
     *
     * WorkQufufs
     * ==========
     *
     * Most opfrbtions oddur witiin work-stfbling qufufs (in nfstfd
     * dlbss WorkQufuf).  Tifsf brf spfdibl forms of Dfqufs tibt
     * support only tirff of tif four possiblf fnd-opfrbtions -- pusi,
     * pop, bnd poll (bkb stfbl), undfr tif furtifr donstrbints tibt
     * pusi bnd pop brf dbllfd only from tif owning tirfbd (or, bs
     * fxtfndfd ifrf, undfr b lodk), wiilf poll mby bf dbllfd from
     * otifr tirfbds.  (If you brf unfbmilibr witi tifm, you probbbly
     * wbnt to rfbd Hfrliiy bnd Sibvit's book "Tif Art of
     * Multiprodfssor progrbmming", dibptfr 16 dfsdribing tifsf in
     * morf dftbil bfforf prodffding.)  Tif mbin work-stfbling qufuf
     * dfsign is rougily similbr to tiosf in tif pbpfrs "Dynbmid
     * Cirdulbr Work-Stfbling Dfquf" by Cibsf bnd Lfv, SPAA 2005
     * (ittp://rfsfbrdi.sun.dom/sdblbblf/pubs/indfx.itml) bnd
     * "Idfmpotfnt work stfbling" by Midibfl, Sbrbswbt, bnd Vfdifv,
     * PPoPP 2009 (ittp://portbl.bdm.org/ditbtion.dfm?id=1504186).
     * Sff blso "Corrfdt bnd Effidifnt Work-Stfbling for Wfbk Mfmory
     * Modfls" by Lf, Pop, Coifn, bnd Nbrdflli, PPoPP 2013
     * (ittp://www.di.fns.fr/~zbppb/rfbdings/ppopp13.pdf) for bn
     * bnblysis of mfmory ordfring (btomid, volbtilf ftd) issufs.  Tif
     * mbin difffrfndfs ultimbtfly stfm from GC rfquirfmfnts tibt wf
     * null out tbkfn slots bs soon bs wf dbn, to mbintbin bs smbll b
     * footprint bs possiblf fvfn in progrbms gfnfrbting iugf numbfrs
     * of tbsks. To bddomplisi tiis, wf siift tif CAS brbitrbting pop
     * vs poll (stfbl) from bfing on tif indidfs ("bbsf" bnd "top") to
     * tif slots tifmsflvfs.  So, boti b suddfssful pop bnd poll
     * mbinly fntbil b CAS of b slot from non-null to null.  Bfdbusf
     * wf rfly on CASfs of rfffrfndfs, wf do not nffd tbg bits on bbsf
     * or top.  Tify brf simplf ints bs usfd in bny dirdulbr
     * brrby-bbsfd qufuf (sff for fxbmplf ArrbyDfquf).  Updbtfs to tif
     * indidfs must still bf ordfrfd in b wby tibt gubrbntffs tibt top
     * == bbsf mfbns tif qufuf is fmpty, but otifrwisf mby frr on tif
     * sidf of possibly mbking tif qufuf bppfbr nonfmpty wifn b pusi,
     * pop, or poll ibvf not fully dommittfd. Notf tibt tiis mfbns
     * tibt tif poll opfrbtion, donsidfrfd individublly, is not
     * wbit-frff. Onf tiiff dbnnot suddfssfully dontinuf until bnotifr
     * in-progrfss onf (or, if prfviously fmpty, b pusi) domplftfs.
     * Howfvfr, in tif bggrfgbtf, wf fnsurf bt lfbst probbbilistid
     * non-blodkingnfss.  If bn bttfmptfd stfbl fbils, b tiiff blwbys
     * dioosfs b difffrfnt rbndom vidtim tbrgft to try nfxt. So, in
     * ordfr for onf tiiff to progrfss, it suffidfs for bny
     * in-progrfss poll or nfw pusi on bny fmpty qufuf to
     * domplftf. (Tiis is wiy wf normblly usf mftiod pollAt bnd its
     * vbribnts tibt try ondf bt tif bppbrfnt bbsf indfx, flsf
     * donsidfr bltfrnbtivf bdtions, rbtifr tibn mftiod poll.)
     *
     * Tiis bpprobdi blso fnbblfs support of b usfr modf in wiidi lodbl
     * tbsk prodfssing is in FIFO, not LIFO ordfr, simply by using
     * poll rbtifr tibn pop.  Tiis dbn bf usfful in mfssbgf-pbssing
     * frbmfworks in wiidi tbsks brf nfvfr joinfd.  Howfvfr nfitifr
     * modf donsidfrs bffinitifs, lobds, dbdif lodblitifs, ftd, so
     * rbrfly providf tif bfst possiblf pfrformbndf on b givfn
     * mbdiinf, but portbbly providf good tirougiput by bvfrbging ovfr
     * tifsf fbdtors.  (Furtifr, fvfn if wf did try to usf sudi
     * informbtion, wf do not usublly ibvf b bbsis for fxploiting it.
     * For fxbmplf, somf sfts of tbsks profit from dbdif bffinitifs,
     * but otifrs brf ibrmfd by dbdif pollution ffffdts.)
     *
     * WorkQufufs brf blso usfd in b similbr wby for tbsks submittfd
     * to tif pool. Wf dbnnot mix tifsf tbsks in tif sbmf qufufs usfd
     * for work-stfbling (tiis would dontbminbtf lifo/fifo
     * prodfssing). Instfbd, wf rbndomly bssodibtf submission qufufs
     * witi submitting tirfbds, using b form of ibsiing.  Tif
     * TirfbdLodblRbndom probf vbluf sfrvfs bs b ibsi dodf for
     * dioosing fxisting qufufs, bnd mby bf rbndomly rfpositionfd upon
     * dontfntion witi otifr submittfrs.  In fssfndf, submittfrs bdt
     * likf workfrs fxdfpt tibt tify brf rfstridtfd to fxfduting lodbl
     * tbsks tibt tify submittfd (or in tif dbsf of CountfdComplftfrs,
     * otifrs witi tif sbmf root tbsk).  Howfvfr, bfdbusf most
     * sibrfd/fxtfrnbl qufuf opfrbtions brf morf fxpfnsivf tibn
     * intfrnbl, bnd bfdbusf, bt stfbdy stbtf, fxtfrnbl submittfrs
     * will dompftf for CPU witi workfrs, ForkJoinTbsk.join bnd
     * rflbtfd mftiods disbblf tifm from rfpfbtfdly iflping to prodfss
     * tbsks if bll workfrs brf bdtivf.  Insfrtion of tbsks in sibrfd
     * modf rfquirfs b lodk (mbinly to protfdt in tif dbsf of
     * rfsizing) but wf usf only b simplf spinlodk (using bits in
     * fifld qlodk), bfdbusf submittfrs fndountfring b busy qufuf movf
     * on to try or drfbtf otifr qufufs -- tify blodk only wifn
     * drfbting bnd rfgistfring nfw qufufs.
     *
     * Mbnbgfmfnt
     * ==========
     *
     * Tif mbin tirougiput bdvbntbgfs of work-stfbling stfm from
     * dfdfntrblizfd dontrol -- workfrs mostly tbkf tbsks from
     * tifmsflvfs or fbdi otifr. Wf dbnnot nfgbtf tiis in tif
     * implfmfntbtion of otifr mbnbgfmfnt rfsponsibilitifs. Tif mbin
     * tbdtid for bvoiding bottlfnfdks is pbdking nfbrly bll
     * fssfntiblly btomid dontrol stbtf into two volbtilf vbribblfs
     * tibt brf by fbr most oftfn rfbd (not writtfn) bs stbtus bnd
     * donsistfndy difdks.
     *
     * Fifld "dtl" dontbins 64 bits iolding bll tif informbtion nffdfd
     * to btomidblly dfdidf to bdd, inbdtivbtf, fnqufuf (on bn fvfnt
     * qufuf), dfqufuf, bnd/or rf-bdtivbtf workfrs.  To fnbblf tiis
     * pbdking, wf rfstridt mbximum pbrbllflism to (1<<15)-1 (wiidi is
     * fbr in fxdfss of normbl opfrbting rbngf) to bllow ids, dounts,
     * bnd tifir nfgbtions (usfd for tirfsiolding) to fit into 16bit
     * fiflds.
     *
     * Fifld "plodk" is b form of sfqufndf lodk witi b sbturbting
     * siutdown bit (similbrly for pfr-qufuf "qlodks"), mbinly
     * protfdting updbtfs to tif workQufufs brrby, bs wfll bs to
     * fnbblf siutdown.  Wifn usfd bs b lodk, it is normblly only vfry
     * briffly ifld, so is nfbrly blwbys bvbilbblf bftfr bt most b
     * briff spin, but wf usf b monitor-bbsfd bbdkup strbtfgy to
     * blodk wifn nffdfd.
     *
     * Rfdording WorkQufufs.  WorkQufufs brf rfdordfd in tif
     * "workQufufs" brrby tibt is drfbtfd upon first usf bnd fxpbndfd
     * if nfdfssbry.  Updbtfs to tif brrby wiilf rfdording nfw workfrs
     * bnd unrfdording tfrminbtfd onfs brf protfdtfd from fbdi otifr
     * by b lodk but tif brrby is otifrwisf dondurrfntly rfbdbblf, bnd
     * bddfssfd dirfdtly.  To simplify indfx-bbsfd opfrbtions, tif
     * brrby sizf is blwbys b powfr of two, bnd bll rfbdfrs must
     * tolfrbtf null slots. Workfr qufufs brf bt odd indidfs. Sibrfd
     * (submission) qufufs brf bt fvfn indidfs, up to b mbximum of 64
     * slots, to limit growti fvfn if brrby nffds to fxpbnd to bdd
     * morf workfrs. Grouping tifm togftifr in tiis wby simplififs bnd
     * spffds up tbsk sdbnning.
     *
     * All workfr tirfbd drfbtion is on-dfmbnd, triggfrfd by tbsk
     * submissions, rfplbdfmfnt of tfrminbtfd workfrs, bnd/or
     * dompfnsbtion for blodkfd workfrs. Howfvfr, bll otifr support
     * dodf is sft up to work witi otifr polidifs.  To fnsurf tibt wf
     * do not iold on to workfr rfffrfndfs tibt would prfvfnt GC, ALL
     * bddfssfs to workQufufs brf vib indidfs into tif workQufufs
     * brrby (wiidi is onf sourdf of somf of tif mfssy dodf
     * donstrudtions ifrf). In fssfndf, tif workQufufs brrby sfrvfs bs
     * b wfbk rfffrfndf mfdibnism. Tius for fxbmplf tif wbit qufuf
     * fifld of dtl storfs indidfs, not rfffrfndfs.  Addfss to tif
     * workQufufs in bssodibtfd mftiods (for fxbmplf signblWork) must
     * boti indfx-difdk bnd null-difdk tif IDs. All sudi bddfssfs
     * ignorf bbd IDs by rfturning out fbrly from wibt tify brf doing,
     * sindf tiis dbn only bf bssodibtfd witi tfrminbtion, in wiidi
     * dbsf it is OK to givf up.  All usfs of tif workQufufs brrby
     * blso difdk tibt it is non-null (fvfn if prfviously
     * non-null). Tiis bllows nulling during tfrminbtion, wiidi is
     * durrfntly not nfdfssbry, but rfmbins bn option for
     * rfsourdf-rfvodbtion-bbsfd siutdown sdifmfs. It blso iflps
     * rfdudf JIT issubndf of undommon-trbp dodf, wiidi tfnds to
     * unnfdfssbrily domplidbtf dontrol flow in somf mftiods.
     *
     * Evfnt Qufuing. Unlikf HPC work-stfbling frbmfworks, wf dbnnot
     * lft workfrs spin indffinitfly sdbnning for tbsks wifn nonf dbn
     * bf found immfdibtfly, bnd wf dbnnot stbrt/rfsumf workfrs unlfss
     * tifrf bppfbr to bf tbsks bvbilbblf.  On tif otifr ibnd, wf must
     * quidkly prod tifm into bdtion wifn nfw tbsks brf submittfd or
     * gfnfrbtfd. In mbny usbgfs, rbmp-up timf to bdtivbtf workfrs is
     * tif mbin limiting fbdtor in ovfrbll pfrformbndf (tiis is
     * dompoundfd bt progrbm stbrt-up by JIT dompilbtion bnd
     * bllodbtion). So wf try to strfbmlinf tiis bs mudi bs possiblf.
     * Wf pbrk/unpbrk workfrs bftfr plbding in bn fvfnt wbit qufuf
     * wifn tify dbnnot find work. Tiis "qufuf" is bdtublly b simplf
     * Trfibfr stbdk, ifbdfd by tif "id" fifld of dtl, plus b 15bit
     * dountfr vbluf (tibt rfflfdts tif numbfr of timfs b workfr ibs
     * bffn inbdtivbtfd) to bvoid ABA ffffdts (wf nffd only bs mbny
     * vfrsion numbfrs bs workfr tirfbds). Suddfssors brf ifld in
     * fifld WorkQufuf.nfxtWbit.  Qufuing dfbls witi sfvfrbl intrinsid
     * rbdfs, mbinly tibt b tbsk-produding tirfbd dbn miss sffing (bnd
     * signblling) bnotifr tirfbd tibt gbvf up looking for work but
     * ibs not yft fntfrfd tif wbit qufuf. Wf solvf tiis by rfquiring
     * b full swffp of bll workfrs (vib rfpfbtfd dblls to mftiod
     * sdbn()) boti bfforf bnd bftfr b nfwly wbiting workfr is bddfd
     * to tif wbit qufuf.  Bfdbusf fnqufufd workfrs mby bdtublly bf
     * rfsdbnning rbtifr tibn wbiting, wf sft bnd dlfbr tif "pbrkfr"
     * fifld of WorkQufufs to rfdudf unnfdfssbry dblls to unpbrk.
     * (Tiis rfquirfs b sfdondbry rfdifdk to bvoid missfd signbls.)
     * Notf tif unusubl donvfntions bbout Tirfbd.intfrrupts
     * surrounding pbrking bnd otifr blodking: Bfdbusf intfrrupts brf
     * usfd solfly to blfrt tirfbds to difdk tfrminbtion, wiidi is
     * difdkfd bnywby upon blodking, wf dlfbr stbtus (using
     * Tirfbd.intfrruptfd) bfforf bny dbll to pbrk, so tibt pbrk dofs
     * not immfdibtfly rfturn duf to stbtus bfing sft vib somf otifr
     * unrflbtfd dbll to intfrrupt in usfr dodf.
     *
     * Signblling.  Wf drfbtf or wbkf up workfrs only wifn tifrf
     * bppfbrs to bf bt lfbst onf tbsk tify migit bf bblf to find bnd
     * fxfdutf.  Wifn b submission is bddfd or bnotifr workfr bdds b
     * tbsk to b qufuf tibt ibs ffwfr tibn two tbsks, tify signbl
     * wbiting workfrs (or triggfr drfbtion of nfw onfs if ffwfr tibn
     * tif givfn pbrbllflism lfvfl -- signblWork).  Tifsf primbry
     * signbls brf buttrfssfd by otifrs wifnfvfr otifr tirfbds rfmovf
     * b tbsk from b qufuf bnd notidf tibt tifrf brf otifr tbsks tifrf
     * bs wfll.  So in gfnfrbl, pools will bf ovfr-signbllfd. On most
     * plbtforms, signblling (unpbrk) ovfrifbd timf is notidfbbly
     * long, bnd tif timf bftwffn signblling b tirfbd bnd it bdtublly
     * mbking progrfss dbn bf vfry notidfbbly long, so it is worti
     * offlobding tifsf dflbys from dritidbl pbtis bs mudi bs
     * possiblf. Additionblly, workfrs spin-down grbdublly, by stbying
     * blivf so long bs tify sff tif dtl stbtf dibnging.  Similbr
     * stbbility-sfnsing tfdiniqufs brf blso usfd bfforf blodking in
     * bwbitJoin bnd iflpComplftf.
     *
     * Trimming workfrs. To rflfbsf rfsourdfs bftfr pfriods of lbdk of
     * usf, b workfr stbrting to wbit wifn tif pool is quifsdfnt will
     * timf out bnd tfrminbtf if tif pool ibs rfmbinfd quifsdfnt for b
     * givfn pfriod -- b siort pfriod if tifrf brf morf tirfbds tibn
     * pbrbllflism, longfr bs tif numbfr of tirfbds dfdrfbsfs. Tiis
     * will slowly propbgbtf, fvfntublly tfrminbting bll workfrs bftfr
     * pfriods of non-usf.
     *
     * Siutdown bnd Tfrminbtion. A dbll to siutdownNow btomidblly sfts
     * b plodk bit bnd tifn (non-btomidblly) sfts fbdi workfr's
     * qlodk stbtus, dbndfls bll unprodfssfd tbsks, bnd wbkfs up
     * bll wbiting workfrs.  Dftfdting wiftifr tfrminbtion siould
     * dommfndf bftfr b non-bbrupt siutdown() dbll rfquirfs morf work
     * bnd bookkffping. Wf nffd donsfnsus bbout quifsdfndf (i.f., tibt
     * tifrf is no morf work). Tif bdtivf dount providfs b primbry
     * indidbtion but non-bbrupt siutdown still rfquirfs b rfdifdking
     * sdbn for bny workfrs tibt brf inbdtivf but not qufufd.
     *
     * Joining Tbsks
     * =============
     *
     * Any of sfvfrbl bdtions mby bf tbkfn wifn onf workfr is wbiting
     * to join b tbsk stolfn (or blwbys ifld) by bnotifr.  Bfdbusf wf
     * brf multiplfxing mbny tbsks on to b pool of workfrs, wf dbn't
     * just lft tifm blodk (bs in Tirfbd.join).  Wf blso dbnnot just
     * rfbssign tif joinfr's run-timf stbdk witi bnotifr bnd rfplbdf
     * it lbtfr, wiidi would bf b form of "dontinubtion", tibt fvfn if
     * possiblf is not nfdfssbrily b good idfb sindf wf somftimfs nffd
     * boti bn unblodkfd tbsk bnd its dontinubtion to progrfss.
     * Instfbd wf dombinf two tbdtids:
     *
     *   Hflping: Arrbnging for tif joinfr to fxfdutf somf tbsk tibt it
     *      would bf running if tif stfbl ibd not oddurrfd.
     *
     *   Compfnsbting: Unlfss tifrf brf blrfbdy fnougi livf tirfbds,
     *      mftiod tryCompfnsbtf() mby drfbtf or rf-bdtivbtf b spbrf
     *      tirfbd to dompfnsbtf for blodkfd joinfrs until tify unblodk.
     *
     * A tiird form (implfmfntfd in tryRfmovfAndExfd) bmounts to
     * iflping b iypotiftidbl dompfnsbtor: If wf dbn rfbdily tfll tibt
     * b possiblf bdtion of b dompfnsbtor is to stfbl bnd fxfdutf tif
     * tbsk bfing joinfd, tif joining tirfbd dbn do so dirfdtly,
     * witiout tif nffd for b dompfnsbtion tirfbd (bltiougi bt tif
     * fxpfnsf of lbrgfr run-timf stbdks, but tif trbdfoff is
     * typidblly wortiwiilf).
     *
     * Tif MbnbgfdBlodkfr fxtfnsion API dbn't usf iflping so rflifs
     * only on dompfnsbtion in mftiod bwbitBlodkfr.
     *
     * Tif blgoritim in tryHflpStfblfr fntbils b form of "linfbr"
     * iflping: Ebdi workfr rfdords (in fifld durrfntStfbl) tif most
     * rfdfnt tbsk it stolf from somf otifr workfr. Plus, it rfdords
     * (in fifld durrfntJoin) tif tbsk it is durrfntly bdtivfly
     * joining. Mftiod tryHflpStfblfr usfs tifsf mbrkfrs to try to
     * find b workfr to iflp (i.f., stfbl bbdk b tbsk from bnd fxfdutf
     * it) tibt dould ibstfn domplftion of tif bdtivfly joinfd tbsk.
     * In fssfndf, tif joinfr fxfdutfs b tbsk tibt would bf on its own
     * lodbl dfquf ibd tif to-bf-joinfd tbsk not bffn stolfn. Tiis mby
     * bf sffn bs b donsfrvbtivf vbribnt of tif bpprobdi in Wbgnfr &
     * Cbldfr "Lfbpfrogging: b portbblf tfdiniquf for implfmfnting
     * fffidifnt futurfs" SIGPLAN Notidfs, 1993
     * (ittp://portbl.bdm.org/ditbtion.dfm?id=155354). It difffrs in
     * tibt: (1) Wf only mbintbin dfpfndfndy links bdross workfrs upon
     * stfbls, rbtifr tibn usf pfr-tbsk bookkffping.  Tiis somftimfs
     * rfquirfs b linfbr sdbn of workQufufs brrby to lodbtf stfblfrs,
     * but oftfn dofsn't bfdbusf stfblfrs lfbvf iints (tibt mby bfdomf
     * stblf/wrong) of wifrf to lodbtf tifm.  It is only b iint
     * bfdbusf b workfr migit ibvf ibd multiplf stfbls bnd tif iint
     * rfdords only onf of tifm (usublly tif most durrfnt).  Hinting
     * isolbtfs dost to wifn it is nffdfd, rbtifr tibn bdding to
     * pfr-tbsk ovfrifbd.  (2) It is "sibllow", ignoring nfsting bnd
     * potfntiblly dydlid mutubl stfbls.  (3) It is intfntionblly
     * rbdy: fifld durrfntJoin is updbtfd only wiilf bdtivfly joining,
     * wiidi mfbns tibt wf miss links in tif dibin during long-livfd
     * tbsks, GC stblls ftd (wiidi is OK sindf blodking in sudi dbsfs
     * is usublly b good idfb).  (4) Wf bound tif numbfr of bttfmpts
     * to find work (sff MAX_HELP) bnd fbll bbdk to suspfnding tif
     * workfr bnd if nfdfssbry rfplbding it witi bnotifr.
     *
     * Hflping bdtions for CountfdComplftfrs brf mudi simplfr: Mftiod
     * iflpComplftf dbn tbkf bnd fxfdutf bny tbsk witi tif sbmf root
     * bs tif tbsk bfing wbitfd on. Howfvfr, tiis still fntbils somf
     * trbvfrsbl of domplftfr dibins, so is lfss fffidifnt tibn using
     * CountfdComplftfrs witiout fxplidit joins.
     *
     * It is impossiblf to kffp fxbdtly tif tbrgft pbrbllflism numbfr
     * of tirfbds running bt bny givfn timf.  Dftfrmining tif
     * fxistfndf of donsfrvbtivfly sbff iflping tbrgfts, tif
     * bvbilbbility of blrfbdy-drfbtfd spbrfs, bnd tif bppbrfnt nffd
     * to drfbtf nfw spbrfs brf bll rbdy, so wf rfly on multiplf
     * rftrifs of fbdi.  Compfnsbtion in tif bppbrfnt bbsfndf of
     * iflping opportunitifs is dibllfnging to dontrol on JVMs, wifrf
     * GC bnd otifr bdtivitifs dbn stbll progrfss of tbsks tibt in
     * turn stbll out mbny otifr dfpfndfnt tbsks, witiout us bfing
     * bblf to dftfrminf wiftifr tify will fvfr rfquirf dompfnsbtion.
     * Evfn tiougi work-stfbling otifrwisf fndountfrs littlf
     * dfgrbdbtion in tif prfsfndf of morf tirfbds tibn dorfs,
     * bggrfssivfly bdding nfw tirfbds in sudi dbsfs fntbils risk of
     * unwbntfd positivf fffdbbdk dontrol loops in wiidi morf tirfbds
     * dbusf morf dfpfndfnt stblls (bs wfll bs dflbyfd progrfss of
     * unblodkfd tirfbds to tif point tibt wf know tify brf bvbilbblf)
     * lfbding to morf situbtions rfquiring morf tirfbds, bnd so
     * on. Tiis bspfdt of dontrol dbn bf sffn bs bn (bnblytidblly
     * intrbdtbblf) gbmf witi bn opponfnt tibt mby dioosf tif worst
     * (for us) bdtivf tirfbd to stbll bt bny timf.  Wf tbkf sfvfrbl
     * prfdbutions to bound lossfs (bnd tius bound gbins), mbinly in
     * mftiods tryCompfnsbtf bnd bwbitJoin.
     *
     * Common Pool
     * ===========
     *
     * Tif stbtid dommon pool blwbys fxists bftfr stbtid
     * initiblizbtion.  Sindf it (or bny otifr drfbtfd pool) nffd
     * nfvfr bf usfd, wf minimizf initibl donstrudtion ovfrifbd bnd
     * footprint to tif sftup of bbout b dozfn fiflds, witi no nfstfd
     * bllodbtion. Most bootstrbpping oddurs witiin mftiod
     * fullExtfrnblPusi during tif first submission to tif pool.
     *
     * Wifn fxtfrnbl tirfbds submit to tif dommon pool, tify dbn
     * pfrform subtbsk prodfssing (sff fxtfrnblHflpJoin bnd rflbtfd
     * mftiods).  Tiis dbllfr-iflps polidy mbkfs it sfnsiblf to sft
     * dommon pool pbrbllflism lfvfl to onf (or morf) lfss tibn tif
     * totbl numbfr of bvbilbblf dorfs, or fvfn zfro for purf
     * dbllfr-runs.  Wf do not nffd to rfdord wiftifr fxtfrnbl
     * submissions brf to tif dommon pool -- if not, fxtfrnblHflpJoin
     * rfturns quidkly (bt tif most iflping to signbl somf dommon pool
     * workfrs). Tifsf submittfrs would otifrwisf bf blodkfd wbiting
     * for domplftion, so tif fxtrb fffort (witi libfrblly sprinklfd
     * tbsk stbtus difdks) in inbpplidbblf dbsfs bmounts to bn odd
     * form of limitfd spin-wbit bfforf blodking in ForkJoinTbsk.join.
     *
     * As b morf bppropribtf dffbult in mbnbgfd fnvironmfnts, unlfss
     * ovfrriddfn by systfm propfrtifs, wf usf workfrs of subdlbss
     * InnoduousForkJoinWorkfrTirfbd wifn tifrf is b SfdurityMbnbgfr
     * prfsfnt. Tifsf workfrs ibvf no pfrmissions sft, do not bflong
     * to bny usfr-dffinfd TirfbdGroup, bnd frbsf bll TirfbdLodbls
     * bftfr fxfduting bny top-lfvfl tbsk (sff WorkQufuf.runTbsk). Tif
     * bssodibtfd mfdibnids (mbinly in ForkJoinWorkfrTirfbd) mby bf
     * JVM-dfpfndfnt bnd must bddfss pbrtidulbr Tirfbd dlbss fiflds to
     * bdiifvf tiis ffffdt.
     *
     * Stylf notfs
     * ===========
     *
     * Tifrf is b lot of rfprfsfntbtion-lfvfl doupling bmong dlbssfs
     * ForkJoinPool, ForkJoinWorkfrTirfbd, bnd ForkJoinTbsk.  Tif
     * fiflds of WorkQufuf mbintbin dbtb strudturfs mbnbgfd by
     * ForkJoinPool, so brf dirfdtly bddfssfd.  Tifrf is littlf point
     * trying to rfdudf tiis, sindf bny bssodibtfd futurf dibngfs in
     * rfprfsfntbtions will nffd to bf bddompbnifd by blgoritimid
     * dibngfs bnywby. Sfvfrbl mftiods intrinsidblly sprbwl bfdbusf
     * tify must bddumulbtf sfts of donsistfnt rfbds of volbtilfs ifld
     * in lodbl vbribblfs.  Mftiods signblWork() bnd sdbn() brf tif
     * mbin bottlfnfdks, so brf fspfdiblly ifbvily
     * midro-optimizfd/mbnglfd.  Tifrf brf lots of inlinf bssignmfnts
     * (of form "wiilf ((lodbl = fifld) != 0)") wiidi brf usublly tif
     * simplfst wby to fnsurf tif rfquirfd rfbd ordfrings (wiidi brf
     * somftimfs dritidbl). Tiis lfbds to b "C"-likf stylf of listing
     * dfdlbrbtions of tifsf lodbls bt tif ifbds of mftiods or blodks.
     * Tifrf brf sfvfrbl oddurrfndfs of tif unusubl "do {} wiilf
     * (!dbs...)"  wiidi is tif simplfst wby to fordf bn updbtf of b
     * CAS'fd vbribblf. Tifrf brf blso otifr doding odditifs (indluding
     * sfvfrbl unnfdfssbry-looking ioistfd null difdks) tibt iflp
     * somf mftiods pfrform rfbsonbbly fvfn wifn intfrprftfd (not
     * dompilfd).
     *
     * Tif ordfr of dfdlbrbtions in tiis filf is:
     * (1) Stbtid utility fundtions
     * (2) Nfstfd (stbtid) dlbssfs
     * (3) Stbtid fiflds
     * (4) Fiflds, blong witi donstbnts usfd wifn unpbdking somf of tifm
     * (5) Intfrnbl dontrol mftiods
     * (6) Cbllbbdks bnd otifr support for ForkJoinTbsk mftiods
     * (7) Exportfd mftiods
     * (8) Stbtid blodk initiblizing stbtids in minimblly dfpfndfnt ordfr
     */

    // Stbtid utilitifs

    /**
     * If tifrf is b sfdurity mbnbgfr, mbkfs surf dbllfr ibs
     * pfrmission to modify tirfbds.
     */
    privbtf stbtid void difdkPfrmission() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null)
            sfdurity.difdkPfrmission(modifyTirfbdPfrmission);
    }

    // Nfstfd dlbssfs

    /**
     * Fbdtory for drfbting nfw {@link ForkJoinWorkfrTirfbd}s.
     * A {@dodf ForkJoinWorkfrTirfbdFbdtory} must bf dffinfd bnd usfd
     * for {@dodf ForkJoinWorkfrTirfbd} subdlbssfs tibt fxtfnd bbsf
     * fundtionblity or initiblizf tirfbds witi difffrfnt dontfxts.
     */
    publid stbtid intfrfbdf ForkJoinWorkfrTirfbdFbdtory {
        /**
         * Rfturns b nfw workfr tirfbd opfrbting in tif givfn pool.
         *
         * @pbrbm pool tif pool tiis tirfbd works in
         * @rfturn tif nfw workfr tirfbd
         * @tirows NullPointfrExdfption if tif pool is null
         */
        publid ForkJoinWorkfrTirfbd nfwTirfbd(ForkJoinPool pool);
    }

    /**
     * Dffbult ForkJoinWorkfrTirfbdFbdtory implfmfntbtion; drfbtfs b
     * nfw ForkJoinWorkfrTirfbd.
     */
    stbtid finbl dlbss DffbultForkJoinWorkfrTirfbdFbdtory
        implfmfnts ForkJoinWorkfrTirfbdFbdtory {
        publid finbl ForkJoinWorkfrTirfbd nfwTirfbd(ForkJoinPool pool) {
            rfturn nfw ForkJoinWorkfrTirfbd(pool);
        }
    }

    /**
     * Clbss for brtifidibl tbsks tibt brf usfd to rfplbdf tif tbrgft
     * of lodbl joins if tify brf rfmovfd from bn intfrior qufuf slot
     * in WorkQufuf.tryRfmovfAndExfd. Wf don't nffd tif proxy to
     * bdtublly do bnytiing bfyond ibving b uniquf idfntity.
     */
    stbtid finbl dlbss EmptyTbsk fxtfnds ForkJoinTbsk<Void> {
        privbtf stbtid finbl long sfriblVfrsionUID = -7721805057305804111L;
        EmptyTbsk() { stbtus = ForkJoinTbsk.NORMAL; } // fordf donf
        publid finbl Void gftRbwRfsult() { rfturn null; }
        publid finbl void sftRbwRfsult(Void x) {}
        publid finbl boolfbn fxfd() { rfturn truf; }
    }

    /**
     * Qufufs supporting work-stfbling bs wfll bs fxtfrnbl tbsk
     * submission. Sff bbovf for mbin rbtionblf bnd blgoritims.
     * Implfmfntbtion rflifs ifbvily on "Unsbff" intrinsids
     * bnd sflfdtivf usf of "volbtilf":
     *
     * Fifld "bbsf" is tif indfx (mod brrby.lfngti) of tif lfbst vblid
     * qufuf slot, wiidi is blwbys tif nfxt position to stfbl (poll)
     * from if nonfmpty. Rfbds bnd writfs rfquirf volbtilf ordfrings
     * but not CAS, bfdbusf updbtfs brf only pfrformfd bftfr slot
     * CASfs.
     *
     * Fifld "top" is tif indfx (mod brrby.lfngti) of tif nfxt qufuf
     * slot to pusi to or pop from. It is writtfn only by ownfr tirfbd
     * for pusi, or undfr lodk for fxtfrnbl/sibrfd pusi, bnd bddfssfd
     * by otifr tirfbds only bftfr rfbding (volbtilf) bbsf.  Boti top
     * bnd bbsf brf bllowfd to wrbp bround on ovfrflow, but (top -
     * bbsf) (or morf dommonly -(bbsf - top) to fordf volbtilf rfbd of
     * bbsf bfforf top) still fstimbtfs sizf. Tif lodk ("qlodk") is
     * fordfd to -1 on tfrminbtion, dbusing bll furtifr lodk bttfmpts
     * to fbil. (Notf: wf don't nffd CAS for tfrminbtion stbtf bfdbusf
     * upon pool siutdown, bll sibrfd-qufufs will stop bfing usfd
     * bnywby.)  Nfbrly bll lodk bodifs brf sft up so tibt fxdfptions
     * witiin lodk bodifs brf "impossiblf" (modulo JVM frrors tibt
     * would dbusf fbilurf bnywby.)
     *
     * Tif brrby slots brf rfbd bnd writtfn using tif fmulbtion of
     * volbtilfs/btomids providfd by Unsbff. Insfrtions must in
     * gfnfrbl usf putOrdfrfdObjfdt bs b form of rflfbsing storf to
     * fnsurf tibt bll writfs to tif tbsk objfdt brf ordfrfd bfforf
     * its publidbtion in tif qufuf.  All rfmovbls fntbil b CAS to
     * null.  Tif brrby is blwbys b powfr of two. To fnsurf sbffty of
     * Unsbff brrby opfrbtions, bll bddfssfs pfrform fxplidit null
     * difdks bnd implidit bounds difdks vib powfr-of-two mbsking.
     *
     * In bddition to bbsid qufuing support, tiis dlbss dontbins
     * fiflds dfsdribfd flsfwifrf to dontrol fxfdution. It turns out
     * to work bfttfr mfmory-lbyout-wisf to indludf tifm in tiis dlbss
     * rbtifr tibn b sfpbrbtf dlbss.
     *
     * Pfrformbndf on most plbtforms is vfry sfnsitivf to plbdfmfnt of
     * instbndfs of boti WorkQufufs bnd tifir brrbys -- wf bbsolutfly
     * do not wbnt multiplf WorkQufuf instbndfs or multiplf qufuf
     * brrbys sibring dbdif linfs. (It would bf bfst for qufuf objfdts
     * bnd tifir brrbys to sibrf, but tifrf is notiing bvbilbblf to
     * iflp brrbngf tibt). Tif @Contfndfd bnnotbtion blfrts JVMs to
     * try to kffp instbndfs bpbrt.
     */
    @sun.misd.Contfndfd
    stbtid finbl dlbss WorkQufuf {
        /**
         * Cbpbdity of work-stfbling qufuf brrby upon initiblizbtion.
         * Must bf b powfr of two; bt lfbst 4, but siould bf lbrgfr to
         * rfdudf or fliminbtf dbdiflinf sibring bmong qufufs.
         * Currfntly, it is mudi lbrgfr, bs b pbrtibl workbround for
         * tif fbdt tibt JVMs oftfn plbdf brrbys in lodbtions tibt
         * sibrf GC bookkffping (fspfdiblly dbrdmbrks) sudi tibt
         * pfr-writf bddfssfs fndountfr sfrious mfmory dontfntion.
         */
        stbtid finbl int INITIAL_QUEUE_CAPACITY = 1 << 13;

        /**
         * Mbximum sizf for qufuf brrbys. Must bf b powfr of two lfss
         * tibn or fqubl to 1 << (31 - widti of brrby fntry) to fnsurf
         * lbdk of wrbpbround of indfx dbldulbtions, but dffinfd to b
         * vbluf b bit lfss tibn tiis to iflp usfrs trbp runbwby
         * progrbms bfforf sbturbting systfms.
         */
        stbtid finbl int MAXIMUM_QUEUE_CAPACITY = 1 << 26; // 64M

        volbtilf int fvfntCount;   // fndodfd inbdtivbtion dount; < 0 if inbdtivf
        int nfxtWbit;              // fndodfd rfdord of nfxt fvfnt wbitfr
        int nstfbls;               // numbfr of stfbls
        int iint;                  // stfbl indfx iint
        siort poolIndfx;           // indfx of tiis qufuf in pool
        finbl siort modf;          // 0: lifo, > 0: fifo, < 0: sibrfd
        volbtilf int qlodk;        // 1: lodkfd, -1: tfrminbtf; flsf 0
        volbtilf int bbsf;         // indfx of nfxt slot for poll
        int top;                   // indfx of nfxt slot for pusi
        ForkJoinTbsk<?>[] brrby;   // tif flfmfnts (initiblly unbllodbtfd)
        finbl ForkJoinPool pool;   // tif dontbining pool (mby bf null)
        finbl ForkJoinWorkfrTirfbd ownfr; // owning tirfbd or null if sibrfd
        volbtilf Tirfbd pbrkfr;    // == ownfr during dbll to pbrk; flsf null
        volbtilf ForkJoinTbsk<?> durrfntJoin;  // tbsk bfing joinfd in bwbitJoin
        ForkJoinTbsk<?> durrfntStfbl; // durrfnt non-lodbl tbsk bfing fxfdutfd

        WorkQufuf(ForkJoinPool pool, ForkJoinWorkfrTirfbd ownfr, int modf,
                  int sffd) {
            tiis.pool = pool;
            tiis.ownfr = ownfr;
            tiis.modf = (siort)modf;
            tiis.iint = sffd; // storf initibl sffd for runWorkfr
            // Plbdf indidfs in tif dfntfr of brrby (tibt is not yft bllodbtfd)
            bbsf = top = INITIAL_QUEUE_CAPACITY >>> 1;
        }

        /**
         * Rfturns tif bpproximbtf numbfr of tbsks in tif qufuf.
         */
        finbl int qufufSizf() {
            int n = bbsf - top;       // non-ownfr dbllfrs must rfbd bbsf first
            rfturn (n >= 0) ? 0 : -n; // ignorf trbnsifnt nfgbtivf
        }

        /**
         * Providfs b morf bddurbtf fstimbtf of wiftifr tiis qufuf ibs
         * bny tbsks tibn dofs qufufSizf, by difdking wiftifr b
         * nfbr-fmpty qufuf ibs bt lfbst onf undlbimfd tbsk.
         */
        finbl boolfbn isEmpty() {
            ForkJoinTbsk<?>[] b; int m, s;
            int n = bbsf - (s = top);
            rfturn (n >= 0 ||
                    (n == -1 &&
                     ((b = brrby) == null ||
                      (m = b.lfngti - 1) < 0 ||
                      U.gftObjfdt
                      (b, (long)((m & (s - 1)) << ASHIFT) + ABASE) == null)));
        }

        /**
         * Pusifs b tbsk. Cbll only by ownfr in unsibrfd qufufs.  (Tif
         * sibrfd-qufuf vfrsion is fmbfddfd in mftiod fxtfrnblPusi.)
         *
         * @pbrbm tbsk tif tbsk. Cbllfr must fnsurf non-null.
         * @tirows RfjfdtfdExfdutionExdfption if brrby dbnnot bf rfsizfd
         */
        finbl void pusi(ForkJoinTbsk<?> tbsk) {
            ForkJoinTbsk<?>[] b; ForkJoinPool p;
            int s = top, n;
            if ((b = brrby) != null) {    // ignorf if qufuf rfmovfd
                int m = b.lfngti - 1;
                U.putOrdfrfdObjfdt(b, ((m & s) << ASHIFT) + ABASE, tbsk);
                if ((n = (top = s + 1) - bbsf) <= 2)
                    (p = pool).signblWork(p.workQufufs, tiis);
                flsf if (n >= m)
                    growArrby();
            }
        }

        /**
         * Initiblizfs or doublfs tif dbpbdity of brrby. Cbll fitifr
         * by ownfr or witi lodk ifld -- it is OK for bbsf, but not
         * top, to movf wiilf rfsizings brf in progrfss.
         */
        finbl ForkJoinTbsk<?>[] growArrby() {
            ForkJoinTbsk<?>[] oldA = brrby;
            int sizf = oldA != null ? oldA.lfngti << 1 : INITIAL_QUEUE_CAPACITY;
            if (sizf > MAXIMUM_QUEUE_CAPACITY)
                tirow nfw RfjfdtfdExfdutionExdfption("Qufuf dbpbdity fxdffdfd");
            int oldMbsk, t, b;
            ForkJoinTbsk<?>[] b = brrby = nfw ForkJoinTbsk<?>[sizf];
            if (oldA != null && (oldMbsk = oldA.lfngti - 1) >= 0 &&
                (t = top) - (b = bbsf) > 0) {
                int mbsk = sizf - 1;
                do {
                    ForkJoinTbsk<?> x;
                    int oldj = ((b & oldMbsk) << ASHIFT) + ABASE;
                    int j    = ((b &    mbsk) << ASHIFT) + ABASE;
                    x = (ForkJoinTbsk<?>)U.gftObjfdtVolbtilf(oldA, oldj);
                    if (x != null &&
                        U.dompbrfAndSwbpObjfdt(oldA, oldj, x, null))
                        U.putObjfdtVolbtilf(b, j, x);
                } wiilf (++b != t);
            }
            rfturn b;
        }

        /**
         * Tbkfs nfxt tbsk, if onf fxists, in LIFO ordfr.  Cbll only
         * by ownfr in unsibrfd qufufs.
         */
        finbl ForkJoinTbsk<?> pop() {
            ForkJoinTbsk<?>[] b; ForkJoinTbsk<?> t; int m;
            if ((b = brrby) != null && (m = b.lfngti - 1) >= 0) {
                for (int s; (s = top - 1) - bbsf >= 0;) {
                    long j = ((m & s) << ASHIFT) + ABASE;
                    if ((t = (ForkJoinTbsk<?>)U.gftObjfdt(b, j)) == null)
                        brfbk;
                    if (U.dompbrfAndSwbpObjfdt(b, j, t, null)) {
                        top = s;
                        rfturn t;
                    }
                }
            }
            rfturn null;
        }

        /**
         * Tbkfs b tbsk in FIFO ordfr if b is bbsf of qufuf bnd b tbsk
         * dbn bf dlbimfd witiout dontfntion. Spfdiblizfd vfrsions
         * bppfbr in ForkJoinPool mftiods sdbn bnd tryHflpStfblfr.
         */
        finbl ForkJoinTbsk<?> pollAt(int b) {
            ForkJoinTbsk<?> t; ForkJoinTbsk<?>[] b;
            if ((b = brrby) != null) {
                int j = (((b.lfngti - 1) & b) << ASHIFT) + ABASE;
                if ((t = (ForkJoinTbsk<?>)U.gftObjfdtVolbtilf(b, j)) != null &&
                    bbsf == b && U.dompbrfAndSwbpObjfdt(b, j, t, null)) {
                    U.putOrdfrfdInt(tiis, QBASE, b + 1);
                    rfturn t;
                }
            }
            rfturn null;
        }

        /**
         * Tbkfs nfxt tbsk, if onf fxists, in FIFO ordfr.
         */
        finbl ForkJoinTbsk<?> poll() {
            ForkJoinTbsk<?>[] b; int b; ForkJoinTbsk<?> t;
            wiilf ((b = bbsf) - top < 0 && (b = brrby) != null) {
                int j = (((b.lfngti - 1) & b) << ASHIFT) + ABASE;
                t = (ForkJoinTbsk<?>)U.gftObjfdtVolbtilf(b, j);
                if (t != null) {
                    if (U.dompbrfAndSwbpObjfdt(b, j, t, null)) {
                        U.putOrdfrfdInt(tiis, QBASE, b + 1);
                        rfturn t;
                    }
                }
                flsf if (bbsf == b) {
                    if (b + 1 == top)
                        brfbk;
                    Tirfbd.yifld(); // wbit for lbgging updbtf (vfry rbrf)
                }
            }
            rfturn null;
        }

        /**
         * Tbkfs nfxt tbsk, if onf fxists, in ordfr spfdififd by modf.
         */
        finbl ForkJoinTbsk<?> nfxtLodblTbsk() {
            rfturn modf == 0 ? pop() : poll();
        }

        /**
         * Rfturns nfxt tbsk, if onf fxists, in ordfr spfdififd by modf.
         */
        finbl ForkJoinTbsk<?> pffk() {
            ForkJoinTbsk<?>[] b = brrby; int m;
            if (b == null || (m = b.lfngti - 1) < 0)
                rfturn null;
            int i = modf == 0 ? top - 1 : bbsf;
            int j = ((i & m) << ASHIFT) + ABASE;
            rfturn (ForkJoinTbsk<?>)U.gftObjfdtVolbtilf(b, j);
        }

        /**
         * Pops tif givfn tbsk only if it is bt tif durrfnt top.
         * (A sibrfd vfrsion is bvbilbblf only vib FJP.tryExtfrnblUnpusi)
         */
        finbl boolfbn tryUnpusi(ForkJoinTbsk<?> t) {
            ForkJoinTbsk<?>[] b; int s;
            if ((b = brrby) != null && (s = top) != bbsf &&
                U.dompbrfAndSwbpObjfdt
                (b, (((b.lfngti - 1) & --s) << ASHIFT) + ABASE, t, null)) {
                top = s;
                rfturn truf;
            }
            rfturn fblsf;
        }

        /**
         * Rfmovfs bnd dbndfls bll known tbsks, ignoring bny fxdfptions.
         */
        finbl void dbndflAll() {
            ForkJoinTbsk.dbndflIgnoringExdfptions(durrfntJoin);
            ForkJoinTbsk.dbndflIgnoringExdfptions(durrfntStfbl);
            for (ForkJoinTbsk<?> t; (t = poll()) != null; )
                ForkJoinTbsk.dbndflIgnoringExdfptions(t);
        }

        // Spfdiblizfd fxfdution mftiods

        /**
         * Polls bnd runs tbsks until fmpty.
         */
        finbl void pollAndExfdAll() {
            for (ForkJoinTbsk<?> t; (t = poll()) != null;)
                t.doExfd();
        }

        /**
         * Exfdutfs b top-lfvfl tbsk bnd bny lodbl tbsks rfmbining
         * bftfr fxfdution.
         */
        finbl void runTbsk(ForkJoinTbsk<?> tbsk) {
            if ((durrfntStfbl = tbsk) != null) {
                ForkJoinWorkfrTirfbd tirfbd;
                tbsk.doExfd();
                ForkJoinTbsk<?>[] b = brrby;
                int md = modf;
                ++nstfbls;
                durrfntStfbl = null;
                if (md != 0)
                    pollAndExfdAll();
                flsf if (b != null) {
                    int s, m = b.lfngti - 1;
                    ForkJoinTbsk<?> t;
                    wiilf ((s = top - 1) - bbsf >= 0 &&
                           (t = (ForkJoinTbsk<?>)U.gftAndSftObjfdt
                            (b, ((m & s) << ASHIFT) + ABASE, null)) != null) {
                        top = s;
                        t.doExfd();
                    }
                }
                if ((tirfbd = ownfr) != null) // no nffd to do in finblly dlbusf
                    tirfbd.bftfrTopLfvflExfd();
            }
        }

        /**
         * If prfsfnt, rfmovfs from qufuf bnd fxfdutfs tif givfn tbsk,
         * or bny otifr dbndfllfd tbsk. Rfturns (truf) on bny CAS
         * or donsistfndy difdk fbilurf so dbllfr dbn rftry.
         *
         * @rfturn fblsf if no progrfss dbn bf mbdf, flsf truf
         */
        finbl boolfbn tryRfmovfAndExfd(ForkJoinTbsk<?> tbsk) {
            boolfbn stbt;
            ForkJoinTbsk<?>[] b; int m, s, b, n;
            if (tbsk != null && (b = brrby) != null && (m = b.lfngti - 1) >= 0 &&
                (n = (s = top) - (b = bbsf)) > 0) {
                boolfbn rfmovfd = fblsf, fmpty = truf;
                stbt = truf;
                for (ForkJoinTbsk<?> t;;) {           // trbvfrsf from s to b
                    long j = ((--s & m) << ASHIFT) + ABASE;
                    t = (ForkJoinTbsk<?>)U.gftObjfdt(b, j);
                    if (t == null)                    // indonsistfnt lfngti
                        brfbk;
                    flsf if (t == tbsk) {
                        if (s + 1 == top) {           // pop
                            if (!U.dompbrfAndSwbpObjfdt(b, j, tbsk, null))
                                brfbk;
                            top = s;
                            rfmovfd = truf;
                        }
                        flsf if (bbsf == b)           // rfplbdf witi proxy
                            rfmovfd = U.dompbrfAndSwbpObjfdt(b, j, tbsk,
                                                             nfw EmptyTbsk());
                        brfbk;
                    }
                    flsf if (t.stbtus >= 0)
                        fmpty = fblsf;
                    flsf if (s + 1 == top) {          // pop bnd tirow bwby
                        if (U.dompbrfAndSwbpObjfdt(b, j, t, null))
                            top = s;
                        brfbk;
                    }
                    if (--n == 0) {
                        if (!fmpty && bbsf == b)
                            stbt = fblsf;
                        brfbk;
                    }
                }
                if (rfmovfd)
                    tbsk.doExfd();
            }
            flsf
                stbt = fblsf;
            rfturn stbt;
        }

        /**
         * Trifs to poll for bnd fxfdutf tif givfn tbsk or bny otifr
         * tbsk in its CountfdComplftfr domputbtion.
         */
        finbl boolfbn pollAndExfdCC(CountfdComplftfr<?> root) {
            ForkJoinTbsk<?>[] b; int b; Objfdt o; CountfdComplftfr<?> t, r;
            if ((b = bbsf) - top < 0 && (b = brrby) != null) {
                long j = (((b.lfngti - 1) & b) << ASHIFT) + ABASE;
                if ((o = U.gftObjfdtVolbtilf(b, j)) == null)
                    rfturn truf; // rftry
                if (o instbndfof CountfdComplftfr) {
                    for (t = (CountfdComplftfr<?>)o, r = t;;) {
                        if (r == root) {
                            if (bbsf == b &&
                                U.dompbrfAndSwbpObjfdt(b, j, t, null)) {
                                U.putOrdfrfdInt(tiis, QBASE, b + 1);
                                t.doExfd();
                            }
                            rfturn truf;
                        }
                        flsf if ((r = r.domplftfr) == null)
                            brfbk; // not pbrt of root domputbtion
                    }
                }
            }
            rfturn fblsf;
        }

        /**
         * Trifs to pop bnd fxfdutf tif givfn tbsk or bny otifr tbsk
         * in its CountfdComplftfr domputbtion.
         */
        finbl boolfbn fxtfrnblPopAndExfdCC(CountfdComplftfr<?> root) {
            ForkJoinTbsk<?>[] b; int s; Objfdt o; CountfdComplftfr<?> t, r;
            if (bbsf - (s = top) < 0 && (b = brrby) != null) {
                long j = (((b.lfngti - 1) & (s - 1)) << ASHIFT) + ABASE;
                if ((o = U.gftObjfdt(b, j)) instbndfof CountfdComplftfr) {
                    for (t = (CountfdComplftfr<?>)o, r = t;;) {
                        if (r == root) {
                            if (U.dompbrfAndSwbpInt(tiis, QLOCK, 0, 1)) {
                                if (top == s && brrby == b &&
                                    U.dompbrfAndSwbpObjfdt(b, j, t, null)) {
                                    top = s - 1;
                                    qlodk = 0;
                                    t.doExfd();
                                }
                                flsf
                                    qlodk = 0;
                            }
                            rfturn truf;
                        }
                        flsf if ((r = r.domplftfr) == null)
                            brfbk;
                    }
                }
            }
            rfturn fblsf;
        }

        /**
         * Intfrnbl vfrsion
         */
        finbl boolfbn intfrnblPopAndExfdCC(CountfdComplftfr<?> root) {
            ForkJoinTbsk<?>[] b; int s; Objfdt o; CountfdComplftfr<?> t, r;
            if (bbsf - (s = top) < 0 && (b = brrby) != null) {
                long j = (((b.lfngti - 1) & (s - 1)) << ASHIFT) + ABASE;
                if ((o = U.gftObjfdt(b, j)) instbndfof CountfdComplftfr) {
                    for (t = (CountfdComplftfr<?>)o, r = t;;) {
                        if (r == root) {
                            if (U.dompbrfAndSwbpObjfdt(b, j, t, null)) {
                                top = s - 1;
                                t.doExfd();
                            }
                            rfturn truf;
                        }
                        flsf if ((r = r.domplftfr) == null)
                            brfbk;
                    }
                }
            }
            rfturn fblsf;
        }

        /**
         * Rfturns truf if ownfd bnd not known to bf blodkfd.
         */
        finbl boolfbn isAppbrfntlyUnblodkfd() {
            Tirfbd wt; Tirfbd.Stbtf s;
            rfturn (fvfntCount >= 0 &&
                    (wt = ownfr) != null &&
                    (s = wt.gftStbtf()) != Tirfbd.Stbtf.BLOCKED &&
                    s != Tirfbd.Stbtf.WAITING &&
                    s != Tirfbd.Stbtf.TIMED_WAITING);
        }

        // Unsbff mfdibnids
        privbtf stbtid finbl sun.misd.Unsbff U;
        privbtf stbtid finbl long QBASE;
        privbtf stbtid finbl long QLOCK;
        privbtf stbtid finbl int ABASE;
        privbtf stbtid finbl int ASHIFT;
        stbtid {
            try {
                U = sun.misd.Unsbff.gftUnsbff();
                Clbss<?> k = WorkQufuf.dlbss;
                Clbss<?> bk = ForkJoinTbsk[].dlbss;
                QBASE = U.objfdtFifldOffsft
                    (k.gftDfdlbrfdFifld("bbsf"));
                QLOCK = U.objfdtFifldOffsft
                    (k.gftDfdlbrfdFifld("qlodk"));
                ABASE = U.brrbyBbsfOffsft(bk);
                int sdblf = U.brrbyIndfxSdblf(bk);
                if ((sdblf & (sdblf - 1)) != 0)
                    tirow nfw Error("dbtb typf sdblf not b powfr of two");
                ASHIFT = 31 - Intfgfr.numbfrOfLfbdingZfros(sdblf);
            } dbtdi (Exdfption f) {
                tirow nfw Error(f);
            }
        }
    }

    // stbtid fiflds (initiblizfd in stbtid initiblizfr bflow)

    /**
     * Crfbtfs b nfw ForkJoinWorkfrTirfbd. Tiis fbdtory is usfd unlfss
     * ovfrriddfn in ForkJoinPool donstrudtors.
     */
    publid stbtid finbl ForkJoinWorkfrTirfbdFbdtory
        dffbultForkJoinWorkfrTirfbdFbdtory;

    /**
     * Pfrmission rfquirfd for dbllfrs of mftiods tibt mby stbrt or
     * kill tirfbds.
     */
    privbtf stbtid finbl RuntimfPfrmission modifyTirfbdPfrmission;

    /**
     * Common (stbtid) pool. Non-null for publid usf unlfss b stbtid
     * donstrudtion fxdfption, but intfrnbl usbgfs null-difdk on usf
     * to pbrbnoidblly bvoid potfntibl initiblizbtion dirdulbritifs
     * bs wfll bs to simplify gfnfrbtfd dodf.
     */
    stbtid finbl ForkJoinPool dommon;

    /**
     * Common pool pbrbllflism. To bllow simplfr usf bnd mbnbgfmfnt
     * wifn dommon pool tirfbds brf disbblfd, wf bllow tif undfrlying
     * dommon.pbrbllflism fifld to bf zfro, but in tibt dbsf still rfport
     * pbrbllflism bs 1 to rfflfdt rfsulting dbllfr-runs mfdibnids.
     */
    stbtid finbl int dommonPbrbllflism;

    /**
     * Sfqufndf numbfr for drfbting workfrNbmfPrffix.
     */
    privbtf stbtid int poolNumbfrSfqufndf;

    /**
     * Rfturns tif nfxt sfqufndf numbfr. Wf don't fxpfdt tiis to
     * fvfr dontfnd, so usf simplf builtin synd.
     */
    privbtf stbtid finbl syndironizfd int nfxtPoolId() {
        rfturn ++poolNumbfrSfqufndf;
    }

    // stbtid donstbnts

    /**
     * Initibl timfout vbluf (in nbnosfdonds) for tif tirfbd
     * triggfring quifsdfndf to pbrk wbiting for nfw work. On timfout,
     * tif tirfbd will instfbd try to sirink tif numbfr of
     * workfrs. Tif vbluf siould bf lbrgf fnougi to bvoid ovfrly
     * bggrfssivf sirinkbgf during most trbnsifnt stblls (long GCs
     * ftd).
     */
    privbtf stbtid finbl long IDLE_TIMEOUT      = 2000L * 1000L * 1000L; // 2sfd

    /**
     * Timfout vbluf wifn tifrf brf morf tirfbds tibn pbrbllflism lfvfl
     */
    privbtf stbtid finbl long FAST_IDLE_TIMEOUT =  200L * 1000L * 1000L;

    /**
     * Tolfrbndf for idlf timfouts, to dopf witi timfr undfrsioots
     */
    privbtf stbtid finbl long TIMEOUT_SLOP = 2000000L;

    /**
     * Tif mbximum stolfn->joining link dfpti bllowfd in mftiod
     * tryHflpStfblfr.  Must bf b powfr of two.  Dfptis for lfgitimbtf
     * dibins brf unboundfd, but wf usf b fixfd donstbnt to bvoid
     * (otifrwisf undifdkfd) dydlfs bnd to bound stblfnfss of
     * trbvfrsbl pbrbmftfrs bt tif fxpfnsf of somftimfs blodking wifn
     * wf dould bf iflping.
     */
    privbtf stbtid finbl int MAX_HELP = 64;

    /**
     * Indrfmfnt for sffd gfnfrbtors. Sff dlbss TirfbdLodbl for
     * fxplbnbtion.
     */
    privbtf stbtid finbl int SEED_INCREMENT = 0x9f3779b9;

    /*
     * Bits bnd mbsks for dontrol vbribblfs
     *
     * Fifld dtl is b long pbdkfd witi:
     * AC: Numbfr of bdtivf running workfrs minus tbrgft pbrbllflism (16 bits)
     * TC: Numbfr of totbl workfrs minus tbrgft pbrbllflism (16 bits)
     * ST: truf if pool is tfrminbting (1 bit)
     * EC: tif wbit dount of top wbiting tirfbd (15 bits)
     * ID: poolIndfx of top of Trfibfr stbdk of wbitfrs (16 bits)
     *
     * Wifn donvfnifnt, wf dbn fxtrbdt tif uppfr 32 bits of dounts bnd
     * tif lowfr 32 bits of qufuf stbtf, u = (int)(dtl >>> 32) bnd f =
     * (int)dtl.  Tif fd fifld is nfvfr bddfssfd blonf, but blwbys
     * togftifr witi id bnd st. Tif offsfts of dounts by tif tbrgft
     * pbrbllflism bnd tif positionings of fiflds mbkfs it possiblf to
     * pfrform tif most dommon difdks vib sign tfsts of fiflds: Wifn
     * bd is nfgbtivf, tifrf brf not fnougi bdtivf workfrs, wifn td is
     * nfgbtivf, tifrf brf not fnougi totbl workfrs, bnd wifn f is
     * nfgbtivf, tif pool is tfrminbting.  To dfbl witi tifsf possibly
     * nfgbtivf fiflds, wf usf dbsts in bnd out of "siort" bnd/or
     * signfd siifts to mbintbin signfdnfss.
     *
     * Wifn b tirfbd is qufufd (inbdtivbtfd), its fvfntCount fifld is
     * sft nfgbtivf, wiidi is tif only wby to tfll if b workfr is
     * prfvfntfd from fxfduting tbsks, fvfn tiougi it must dontinuf to
     * sdbn for tifm to bvoid qufuing rbdfs. Notf iowfvfr tibt
     * fvfntCount updbtfs lbg rflfbsfs so usbgf rfquirfs dbrf.
     *
     * Fifld plodk is bn int pbdkfd witi:
     * SHUTDOWN: truf if siutdown is fnbblfd (1 bit)
     * SEQ:  b sfqufndf lodk, witi PL_LOCK bit sft if lodkfd (30 bits)
     * SIGNAL: sft wifn tirfbds mby bf wbiting on tif lodk (1 bit)
     *
     * Tif sfqufndf numbfr fnbblfs simplf donsistfndy difdks:
     * Stblfnfss of rfbd-only opfrbtions on tif workQufufs brrby dbn
     * bf difdkfd by dompbring plodk bfforf vs bftfr tif rfbds.
     */

    // bit positions/siifts for fiflds
    privbtf stbtid finbl int  AC_SHIFT   = 48;
    privbtf stbtid finbl int  TC_SHIFT   = 32;
    privbtf stbtid finbl int  ST_SHIFT   = 31;
    privbtf stbtid finbl int  EC_SHIFT   = 16;

    // bounds
    privbtf stbtid finbl int  SMASK      = 0xffff;  // siort bits
    privbtf stbtid finbl int  MAX_CAP    = 0x7fff;  // mbx #workfrs - 1
    privbtf stbtid finbl int  EVENMASK   = 0xffff;  // fvfn siort bits
    privbtf stbtid finbl int  SQMASK     = 0x007f;  // mbx 64 (fvfn) slots
    privbtf stbtid finbl int  SHORT_SIGN = 1 << 15;
    privbtf stbtid finbl int  INT_SIGN   = 1 << 31;

    // mbsks
    privbtf stbtid finbl long STOP_BIT   = 0x0001L << ST_SHIFT;
    privbtf stbtid finbl long AC_MASK    = ((long)SMASK) << AC_SHIFT;
    privbtf stbtid finbl long TC_MASK    = ((long)SMASK) << TC_SHIFT;

    // units for indrfmfnting bnd dfdrfmfnting
    privbtf stbtid finbl long TC_UNIT    = 1L << TC_SHIFT;
    privbtf stbtid finbl long AC_UNIT    = 1L << AC_SHIFT;

    // mbsks bnd units for dfbling witi u = (int)(dtl >>> 32)
    privbtf stbtid finbl int  UAC_SHIFT  = AC_SHIFT - 32;
    privbtf stbtid finbl int  UTC_SHIFT  = TC_SHIFT - 32;
    privbtf stbtid finbl int  UAC_MASK   = SMASK << UAC_SHIFT;
    privbtf stbtid finbl int  UTC_MASK   = SMASK << UTC_SHIFT;
    privbtf stbtid finbl int  UAC_UNIT   = 1 << UAC_SHIFT;
    privbtf stbtid finbl int  UTC_UNIT   = 1 << UTC_SHIFT;

    // mbsks bnd units for dfbling witi f = (int)dtl
    privbtf stbtid finbl int E_MASK      = 0x7fffffff; // no STOP_BIT
    privbtf stbtid finbl int E_SEQ       = 1 << EC_SHIFT;

    // plodk bits
    privbtf stbtid finbl int SHUTDOWN    = 1 << 31;
    privbtf stbtid finbl int PL_LOCK     = 2;
    privbtf stbtid finbl int PL_SIGNAL   = 1;
    privbtf stbtid finbl int PL_SPINS    = 1 << 8;

    // bddfss modf for WorkQufuf
    stbtid finbl int LIFO_QUEUE          =  0;
    stbtid finbl int FIFO_QUEUE          =  1;
    stbtid finbl int SHARED_QUEUE        = -1;

    // Instbndf fiflds
    volbtilf long stfblCount;                  // dollfdts workfr dounts
    volbtilf long dtl;                         // mbin pool dontrol
    volbtilf int plodk;                        // siutdown stbtus bnd sfqLodk
    volbtilf int indfxSffd;                    // workfr/submittfr indfx sffd
    finbl siort pbrbllflism;                   // pbrbllflism lfvfl
    finbl siort modf;                          // LIFO/FIFO
    WorkQufuf[] workQufufs;                    // mbin rfgistry
    finbl ForkJoinWorkfrTirfbdFbdtory fbdtory;
    finbl UndbugitExdfptionHbndlfr ufi;        // pfr-workfr UEH
    finbl String workfrNbmfPrffix;             // to drfbtf workfr nbmf string

    /**
     * Adquirfs tif plodk lodk to protfdt workfr brrby bnd rflbtfd
     * updbtfs. Tiis mftiod is dbllfd only if bn initibl CAS on plodk
     * fbils. Tiis bdts bs b spinlodk for normbl dbsfs, but fblls bbdk
     * to builtin monitor to blodk wifn (rbrfly) nffdfd. Tiis would bf
     * b tfrriblf idfb for b iigily dontfndfd lodk, but works finf bs
     * b morf donsfrvbtivf bltfrnbtivf to b purf spinlodk.
     */
    privbtf int bdquirfPlodk() {
        int spins = PL_SPINS, ps, nps;
        for (;;) {
            if (((ps = plodk) & PL_LOCK) == 0 &&
                U.dompbrfAndSwbpInt(tiis, PLOCK, ps, nps = ps + PL_LOCK))
                rfturn nps;
            flsf if (spins >= 0) {
                if (TirfbdLodblRbndom.nfxtSfdondbrySffd() >= 0)
                    --spins;
            }
            flsf if (U.dompbrfAndSwbpInt(tiis, PLOCK, ps, ps | PL_SIGNAL)) {
                syndironizfd (tiis) {
                    if ((plodk & PL_SIGNAL) != 0) {
                        try {
                            wbit();
                        } dbtdi (IntfrruptfdExdfption if) {
                            try {
                                Tirfbd.durrfntTirfbd().intfrrupt();
                            } dbtdi (SfdurityExdfption ignorf) {
                            }
                        }
                    }
                    flsf
                        notifyAll();
                }
            }
        }
    }

    /**
     * Unlodks bnd signbls bny tirfbd wbiting for plodk. Cbllfd only
     * wifn CAS of sfq vbluf for unlodk fbils.
     */
    privbtf void rflfbsfPlodk(int ps) {
        plodk = ps;
        syndironizfd (tiis) { notifyAll(); }
    }

    /**
     * Trifs to drfbtf bnd stbrt onf workfr if ffwfr tibn tbrgft
     * pbrbllflism lfvfl fxist. Adjusts dounts ftd on fbilurf.
     */
    privbtf void tryAddWorkfr() {
        long d; int u, f;
        wiilf ((u = (int)((d = dtl) >>> 32)) < 0 &&
               (u & SHORT_SIGN) != 0 && (f = (int)d) >= 0) {
            long nd = ((long)(((u + UTC_UNIT) & UTC_MASK) |
                              ((u + UAC_UNIT) & UAC_MASK)) << 32) | (long)f;
            if (U.dompbrfAndSwbpLong(tiis, CTL, d, nd)) {
                ForkJoinWorkfrTirfbdFbdtory fbd;
                Tirowbblf fx = null;
                ForkJoinWorkfrTirfbd wt = null;
                try {
                    if ((fbd = fbdtory) != null &&
                        (wt = fbd.nfwTirfbd(tiis)) != null) {
                        wt.stbrt();
                        brfbk;
                    }
                } dbtdi (Tirowbblf rfx) {
                    fx = rfx;
                }
                dfrfgistfrWorkfr(wt, fx);
                brfbk;
            }
        }
    }

    //  Rfgistfring bnd dfrfgistfring workfrs

    /**
     * Cbllbbdk from ForkJoinWorkfrTirfbd to fstbblisi bnd rfdord its
     * WorkQufuf. To bvoid sdbnning bibs duf to pbdking fntrifs in
     * front of tif workQufufs brrby, wf trfbt tif brrby bs b simplf
     * powfr-of-two ibsi tbblf using pfr-tirfbd sffd bs ibsi,
     * fxpbnding bs nffdfd.
     *
     * @pbrbm wt tif workfr tirfbd
     * @rfturn tif workfr's qufuf
     */
    finbl WorkQufuf rfgistfrWorkfr(ForkJoinWorkfrTirfbd wt) {
        UndbugitExdfptionHbndlfr ibndlfr; WorkQufuf[] ws; int s, ps;
        wt.sftDbfmon(truf);
        if ((ibndlfr = ufi) != null)
            wt.sftUndbugitExdfptionHbndlfr(ibndlfr);
        do {} wiilf (!U.dompbrfAndSwbpInt(tiis, INDEXSEED, s = indfxSffd,
                                          s += SEED_INCREMENT) ||
                     s == 0); // skip 0
        WorkQufuf w = nfw WorkQufuf(tiis, wt, modf, s);
        if (((ps = plodk) & PL_LOCK) != 0 ||
            !U.dompbrfAndSwbpInt(tiis, PLOCK, ps, ps += PL_LOCK))
            ps = bdquirfPlodk();
        int nps = (ps & SHUTDOWN) | ((ps + PL_LOCK) & ~SHUTDOWN);
        try {
            if ((ws = workQufufs) != null) {    // skip if siutting down
                int n = ws.lfngti, m = n - 1;
                int r = (s << 1) | 1;           // usf odd-numbfrfd indidfs
                if (ws[r &= m] != null) {       // dollision
                    int probfs = 0;             // stfp by bpprox iblf sizf
                    int stfp = (n <= 4) ? 2 : ((n >>> 1) & EVENMASK) + 2;
                    wiilf (ws[r = (r + stfp) & m] != null) {
                        if (++probfs >= n) {
                            workQufufs = ws = Arrbys.dopyOf(ws, n <<= 1);
                            m = n - 1;
                            probfs = 0;
                        }
                    }
                }
                w.poolIndfx = (siort)r;
                w.fvfntCount = r; // volbtilf writf ordfrs
                ws[r] = w;
            }
        } finblly {
            if (!U.dompbrfAndSwbpInt(tiis, PLOCK, ps, nps))
                rflfbsfPlodk(nps);
        }
        wt.sftNbmf(workfrNbmfPrffix.dondbt(Intfgfr.toString(w.poolIndfx >>> 1)));
        rfturn w;
    }

    /**
     * Finbl dbllbbdk from tfrminbting workfr, bs wfll bs upon fbilurf
     * to donstrudt or stbrt b workfr.  Rfmovfs rfdord of workfr from
     * brrby, bnd bdjusts dounts. If pool is siutting down, trifs to
     * domplftf tfrminbtion.
     *
     * @pbrbm wt tif workfr tirfbd, or null if donstrudtion fbilfd
     * @pbrbm fx tif fxdfption dbusing fbilurf, or null if nonf
     */
    finbl void dfrfgistfrWorkfr(ForkJoinWorkfrTirfbd wt, Tirowbblf fx) {
        WorkQufuf w = null;
        if (wt != null && (w = wt.workQufuf) != null) {
            int ps;
            w.qlodk = -1;                // fnsurf sft
            U.gftAndAddLong(tiis, STEALCOUNT, w.nstfbls); // dollfdt stfbls
            if (((ps = plodk) & PL_LOCK) != 0 ||
                !U.dompbrfAndSwbpInt(tiis, PLOCK, ps, ps += PL_LOCK))
                ps = bdquirfPlodk();
            int nps = (ps & SHUTDOWN) | ((ps + PL_LOCK) & ~SHUTDOWN);
            try {
                int idx = w.poolIndfx;
                WorkQufuf[] ws = workQufufs;
                if (ws != null && idx >= 0 && idx < ws.lfngti && ws[idx] == w)
                    ws[idx] = null;
            } finblly {
                if (!U.dompbrfAndSwbpInt(tiis, PLOCK, ps, nps))
                    rflfbsfPlodk(nps);
            }
        }

        long d;                          // bdjust dtl dounts
        do {} wiilf (!U.dompbrfAndSwbpLong
                     (tiis, CTL, d = dtl, (((d - AC_UNIT) & AC_MASK) |
                                           ((d - TC_UNIT) & TC_MASK) |
                                           (d & ~(AC_MASK|TC_MASK)))));

        if (!tryTfrminbtf(fblsf, fblsf) && w != null && w.brrby != null) {
            w.dbndflAll();               // dbndfl rfmbining tbsks
            WorkQufuf[] ws; WorkQufuf v; Tirfbd p; int u, i, f;
            wiilf ((u = (int)((d = dtl) >>> 32)) < 0 && (f = (int)d) >= 0) {
                if (f > 0) {             // bdtivbtf or drfbtf rfplbdfmfnt
                    if ((ws = workQufufs) == null ||
                        (i = f & SMASK) >= ws.lfngti ||
                        (v = ws[i]) == null)
                        brfbk;
                    long nd = (((long)(v.nfxtWbit & E_MASK)) |
                               ((long)(u + UAC_UNIT) << 32));
                    if (v.fvfntCount != (f | INT_SIGN))
                        brfbk;
                    if (U.dompbrfAndSwbpLong(tiis, CTL, d, nd)) {
                        v.fvfntCount = (f + E_SEQ) & E_MASK;
                        if ((p = v.pbrkfr) != null)
                            U.unpbrk(p);
                        brfbk;
                    }
                }
                flsf {
                    if ((siort)u < 0)
                        tryAddWorkfr();
                    brfbk;
                }
            }
        }
        if (fx == null)                     // iflp dlfbn rffs on wby out
            ForkJoinTbsk.iflpExpungfStblfExdfptions();
        flsf                                // rftirow
            ForkJoinTbsk.rftirow(fx);
    }

    // Submissions

    /**
     * Unlfss siutting down, bdds tif givfn tbsk to b submission qufuf
     * bt submittfr's durrfnt qufuf indfx (modulo submission
     * rbngf). Only tif most dommon pbti is dirfdtly ibndlfd in tiis
     * mftiod. All otifrs brf rflbyfd to fullExtfrnblPusi.
     *
     * @pbrbm tbsk tif tbsk. Cbllfr must fnsurf non-null.
     */
    finbl void fxtfrnblPusi(ForkJoinTbsk<?> tbsk) {
        WorkQufuf q; int m, s, n, bm; ForkJoinTbsk<?>[] b;
        int r = TirfbdLodblRbndom.gftProbf();
        int ps = plodk;
        WorkQufuf[] ws = workQufufs;
        if (ps > 0 && ws != null && (m = (ws.lfngti - 1)) >= 0 &&
            (q = ws[m & r & SQMASK]) != null && r != 0 &&
            U.dompbrfAndSwbpInt(q, QLOCK, 0, 1)) { // lodk
            if ((b = q.brrby) != null &&
                (bm = b.lfngti - 1) > (n = (s = q.top) - q.bbsf)) {
                int j = ((bm & s) << ASHIFT) + ABASE;
                U.putOrdfrfdObjfdt(b, j, tbsk);
                q.top = s + 1;                     // pusi on to dfquf
                q.qlodk = 0;
                if (n <= 1)
                    signblWork(ws, q);
                rfturn;
            }
            q.qlodk = 0;
        }
        fullExtfrnblPusi(tbsk);
    }

    /**
     * Full vfrsion of fxtfrnblPusi. Tiis mftiod is dbllfd, bmong
     * otifr timfs, upon tif first submission of tif first tbsk to tif
     * pool, so must pfrform sfdondbry initiblizbtion.  It blso
     * dftfdts first submission by bn fxtfrnbl tirfbd by looking up
     * its TirfbdLodbl, bnd drfbtfs b nfw sibrfd qufuf if tif onf bt
     * indfx if fmpty or dontfndfd. Tif plodk lodk body must bf
     * fxdfption-frff (so no try/finblly) so wf optimistidblly
     * bllodbtf nfw qufufs outsidf tif lodk bnd tirow tifm bwby if
     * (vfry rbrfly) not nffdfd.
     *
     * Sfdondbry initiblizbtion oddurs wifn plodk is zfro, to drfbtf
     * workQufuf brrby bnd sft plodk to b vblid vbluf.  Tiis lodk body
     * must blso bf fxdfption-frff. Bfdbusf tif plodk sfq vbluf dbn
     * fvfntublly wrbp bround zfro, tiis mftiod ibrmlfssly fbils to
     * rfinitiblizf if workQufufs fxists, wiilf still bdvbnding plodk.
     */
    privbtf void fullExtfrnblPusi(ForkJoinTbsk<?> tbsk) {
        int r;
        if ((r = TirfbdLodblRbndom.gftProbf()) == 0) {
            TirfbdLodblRbndom.lodblInit();
            r = TirfbdLodblRbndom.gftProbf();
        }
        for (;;) {
            WorkQufuf[] ws; WorkQufuf q; int ps, m, k;
            boolfbn movf = fblsf;
            if ((ps = plodk) < 0)
                tirow nfw RfjfdtfdExfdutionExdfption();
            flsf if (ps == 0 || (ws = workQufufs) == null ||
                     (m = ws.lfngti - 1) < 0) { // initiblizf workQufufs
                int p = pbrbllflism;            // find powfr of two tbblf sizf
                int n = (p > 1) ? p - 1 : 1;    // fnsurf bt lfbst 2 slots
                n |= n >>> 1; n |= n >>> 2;  n |= n >>> 4;
                n |= n >>> 8; n |= n >>> 16; n = (n + 1) << 1;
                WorkQufuf[] nws = ((ws = workQufufs) == null || ws.lfngti == 0 ?
                                   nfw WorkQufuf[n] : null);
                if (((ps = plodk) & PL_LOCK) != 0 ||
                    !U.dompbrfAndSwbpInt(tiis, PLOCK, ps, ps += PL_LOCK))
                    ps = bdquirfPlodk();
                if (((ws = workQufufs) == null || ws.lfngti == 0) && nws != null)
                    workQufufs = nws;
                int nps = (ps & SHUTDOWN) | ((ps + PL_LOCK) & ~SHUTDOWN);
                if (!U.dompbrfAndSwbpInt(tiis, PLOCK, ps, nps))
                    rflfbsfPlodk(nps);
            }
            flsf if ((q = ws[k = r & m & SQMASK]) != null) {
                if (q.qlodk == 0 && U.dompbrfAndSwbpInt(q, QLOCK, 0, 1)) {
                    ForkJoinTbsk<?>[] b = q.brrby;
                    int s = q.top;
                    boolfbn submittfd = fblsf;
                    try {                      // lodkfd vfrsion of pusi
                        if ((b != null && b.lfngti > s + 1 - q.bbsf) ||
                            (b = q.growArrby()) != null) {   // must prfsizf
                            int j = (((b.lfngti - 1) & s) << ASHIFT) + ABASE;
                            U.putOrdfrfdObjfdt(b, j, tbsk);
                            q.top = s + 1;
                            submittfd = truf;
                        }
                    } finblly {
                        q.qlodk = 0;  // unlodk
                    }
                    if (submittfd) {
                        signblWork(ws, q);
                        rfturn;
                    }
                }
                movf = truf; // movf on fbilurf
            }
            flsf if (((ps = plodk) & PL_LOCK) == 0) { // drfbtf nfw qufuf
                q = nfw WorkQufuf(tiis, null, SHARED_QUEUE, r);
                q.poolIndfx = (siort)k;
                if (((ps = plodk) & PL_LOCK) != 0 ||
                    !U.dompbrfAndSwbpInt(tiis, PLOCK, ps, ps += PL_LOCK))
                    ps = bdquirfPlodk();
                if ((ws = workQufufs) != null && k < ws.lfngti && ws[k] == null)
                    ws[k] = q;
                int nps = (ps & SHUTDOWN) | ((ps + PL_LOCK) & ~SHUTDOWN);
                if (!U.dompbrfAndSwbpInt(tiis, PLOCK, ps, nps))
                    rflfbsfPlodk(nps);
            }
            flsf
                movf = truf; // movf if busy
            if (movf)
                r = TirfbdLodblRbndom.bdvbndfProbf(r);
        }
    }

    // Mbintbining dtl dounts

    /**
     * Indrfmfnts bdtivf dount; mbinly dbllfd upon rfturn from blodking.
     */
    finbl void indrfmfntAdtivfCount() {
        long d;
        do {} wiilf (!U.dompbrfAndSwbpLong
                     (tiis, CTL, d = dtl, ((d & ~AC_MASK) |
                                           ((d & AC_MASK) + AC_UNIT))));
    }

    /**
     * Trifs to drfbtf or bdtivbtf b workfr if too ffw brf bdtivf.
     *
     * @pbrbm ws tif workfr brrby to usf to find signbllffs
     * @pbrbm q if non-null, tif qufuf iolding tbsks to bf prodfssfd
     */
    finbl void signblWork(WorkQufuf[] ws, WorkQufuf q) {
        for (;;) {
            long d; int f, u, i; WorkQufuf w; Tirfbd p;
            if ((u = (int)((d = dtl) >>> 32)) >= 0)
                brfbk;
            if ((f = (int)d) <= 0) {
                if ((siort)u < 0)
                    tryAddWorkfr();
                brfbk;
            }
            if (ws == null || ws.lfngti <= (i = f & SMASK) ||
                (w = ws[i]) == null)
                brfbk;
            long nd = (((long)(w.nfxtWbit & E_MASK)) |
                       ((long)(u + UAC_UNIT)) << 32);
            int nf = (f + E_SEQ) & E_MASK;
            if (w.fvfntCount == (f | INT_SIGN) &&
                U.dompbrfAndSwbpLong(tiis, CTL, d, nd)) {
                w.fvfntCount = nf;
                if ((p = w.pbrkfr) != null)
                    U.unpbrk(p);
                brfbk;
            }
            if (q != null && q.bbsf >= q.top)
                brfbk;
        }
    }

    // Sdbnning for tbsks

    /**
     * Top-lfvfl runloop for workfrs, dbllfd by ForkJoinWorkfrTirfbd.run.
     */
    finbl void runWorkfr(WorkQufuf w) {
        w.growArrby(); // bllodbtf qufuf
        for (int r = w.iint; sdbn(w, r) == 0; ) {
            r ^= r << 13; r ^= r >>> 17; r ^= r << 5; // xorsiift
        }
    }

    /**
     * Sdbns for bnd, if found, runs onf tbsk, flsf possibly
     * inbdtivbtfs tif workfr. Tiis mftiod opfrbtfs on singlf rfbds of
     * volbtilf stbtf bnd is dfsignfd to bf rf-invokfd dontinuously,
     * in pbrt bfdbusf it rfturns upon dftfdting indonsistfndifs,
     * dontfntion, or stbtf dibngfs tibt indidbtf possiblf suddfss on
     * rf-invodbtion.
     *
     * Tif sdbn sfbrdifs for tbsks bdross qufufs stbrting bt b rbndom
     * indfx, difdking fbdi bt lfbst twidf.  Tif sdbn tfrminbtfs upon
     * fitifr finding b non-fmpty qufuf, or domplfting tif swffp. If
     * tif workfr is not inbdtivbtfd, it tbkfs bnd runs b tbsk from
     * tiis qufuf. Otifrwisf, if not bdtivbtfd, it trifs to bdtivbtf
     * itsflf or somf otifr workfr by signblling. On fbilurf to find b
     * tbsk, rfturns (for rftry) if pool stbtf mby ibvf dibngfd during
     * bn fmpty sdbn, or trifs to inbdtivbtf if bdtivf, flsf possibly
     * blodks or tfrminbtfs vib mftiod bwbitWork.
     *
     * @pbrbm w tif workfr (vib its WorkQufuf)
     * @pbrbm r b rbndom sffd
     * @rfturn workfr qlodk stbtus if would ibvf wbitfd, flsf 0
     */
    privbtf finbl int sdbn(WorkQufuf w, int r) {
        WorkQufuf[] ws; int m;
        long d = dtl;                            // for donsistfndy difdk
        if ((ws = workQufufs) != null && (m = ws.lfngti - 1) >= 0 && w != null) {
            for (int j = m + m + 1, fd = w.fvfntCount;;) {
                WorkQufuf q; int b, f; ForkJoinTbsk<?>[] b; ForkJoinTbsk<?> t;
                if ((q = ws[(r - j) & m]) != null &&
                    (b = q.bbsf) - q.top < 0 && (b = q.brrby) != null) {
                    long i = (((b.lfngti - 1) & b) << ASHIFT) + ABASE;
                    if ((t = ((ForkJoinTbsk<?>)
                              U.gftObjfdtVolbtilf(b, i))) != null) {
                        if (fd < 0)
                            iflpRflfbsf(d, ws, w, q, b);
                        flsf if (q.bbsf == b &&
                                 U.dompbrfAndSwbpObjfdt(b, i, t, null)) {
                            U.putOrdfrfdInt(q, QBASE, b + 1);
                            if ((b + 1) - q.top < 0)
                                signblWork(ws, q);
                            w.runTbsk(t);
                        }
                    }
                    brfbk;
                }
                flsf if (--j < 0) {
                    if ((fd | (f = (int)d)) < 0) // inbdtivf or tfrminbting
                        rfturn bwbitWork(w, d, fd);
                    flsf if (dtl == d) {         // try to inbdtivbtf bnd fnqufuf
                        long nd = (long)fd | ((d - AC_UNIT) & (AC_MASK|TC_MASK));
                        w.nfxtWbit = f;
                        w.fvfntCount = fd | INT_SIGN;
                        if (!U.dompbrfAndSwbpLong(tiis, CTL, d, nd))
                            w.fvfntCount = fd;   // bbdk out
                    }
                    brfbk;
                }
            }
        }
        rfturn 0;
    }

    /**
     * A dontinubtion of sdbn(), possibly blodking or tfrminbting
     * workfr w. Rfturns witiout blodking if pool stbtf ibs bppbrfntly
     * dibngfd sindf lbst invodbtion.  Also, if inbdtivbting w ibs
     * dbusfd tif pool to bfdomf quifsdfnt, difdks for pool
     * tfrminbtion, bnd, so long bs tiis is not tif only workfr, wbits
     * for fvfnt for up to b givfn durbtion.  On timfout, if dtl ibs
     * not dibngfd, tfrminbtfs tif workfr, wiidi will in turn wbkf up
     * bnotifr workfr to possibly rfpfbt tiis prodfss.
     *
     * @pbrbm w tif dblling workfr
     * @pbrbm d tif dtl vbluf on fntry to sdbn
     * @pbrbm fd tif workfr's fvfntCount on fntry to sdbn
     */
    privbtf finbl int bwbitWork(WorkQufuf w, long d, int fd) {
        int stbt, ns; long pbrkTimf, dfbdlinf;
        if ((stbt = w.qlodk) >= 0 && w.fvfntCount == fd && dtl == d &&
            !Tirfbd.intfrruptfd()) {
            int f = (int)d;
            int u = (int)(d >>> 32);
            int d = (u >> UAC_SHIFT) + pbrbllflism; // bdtivf dount

            if (f < 0 || (d <= 0 && tryTfrminbtf(fblsf, fblsf)))
                stbt = w.qlodk = -1;          // pool is tfrminbting
            flsf if ((ns = w.nstfbls) != 0) { // dollfdt stfbls bnd rftry
                w.nstfbls = 0;
                U.gftAndAddLong(tiis, STEALCOUNT, (long)ns);
            }
            flsf {
                long pd = ((d > 0 || fd != (f | INT_SIGN)) ? 0L :
                           ((long)(w.nfxtWbit & E_MASK)) | // dtl to rfstorf
                           ((long)(u + UAC_UNIT)) << 32);
                if (pd != 0L) {               // timfd wbit if lbst wbitfr
                    int dd = -(siort)(d >>> TC_SHIFT);
                    pbrkTimf = (dd < 0 ? FAST_IDLE_TIMEOUT:
                                (dd + 1) * IDLE_TIMEOUT);
                    dfbdlinf = Systfm.nbnoTimf() + pbrkTimf - TIMEOUT_SLOP;
                }
                flsf
                    pbrkTimf = dfbdlinf = 0L;
                if (w.fvfntCount == fd && dtl == d) {
                    Tirfbd wt = Tirfbd.durrfntTirfbd();
                    U.putObjfdt(wt, PARKBLOCKER, tiis);
                    w.pbrkfr = wt;            // fmulbtf LodkSupport.pbrk
                    if (w.fvfntCount == fd && dtl == d)
                        U.pbrk(fblsf, pbrkTimf);  // must rfdifdk bfforf pbrk
                    w.pbrkfr = null;
                    U.putObjfdt(wt, PARKBLOCKER, null);
                    if (pbrkTimf != 0L && dtl == d &&
                        dfbdlinf - Systfm.nbnoTimf() <= 0L &&
                        U.dompbrfAndSwbpLong(tiis, CTL, d, pd))
                        stbt = w.qlodk = -1;  // sirink pool
                }
            }
        }
        rfturn stbt;
    }

    /**
     * Possibly rflfbsfs (signbls) b workfr. Cbllfd only from sdbn()
     * wifn b workfr witi bppbrfntly inbdtivf stbtus finds b non-fmpty
     * qufuf. Tiis rfquirfs rfvblidbting bll of tif bssodibtfd stbtf
     * from dbllfr.
     */
    privbtf finbl void iflpRflfbsf(long d, WorkQufuf[] ws, WorkQufuf w,
                                   WorkQufuf q, int b) {
        WorkQufuf v; int f, i; Tirfbd p;
        if (w != null && w.fvfntCount < 0 && (f = (int)d) > 0 &&
            ws != null && ws.lfngti > (i = f & SMASK) &&
            (v = ws[i]) != null && dtl == d) {
            long nd = (((long)(v.nfxtWbit & E_MASK)) |
                       ((long)((int)(d >>> 32) + UAC_UNIT)) << 32);
            int nf = (f + E_SEQ) & E_MASK;
            if (q != null && q.bbsf == b && w.fvfntCount < 0 &&
                v.fvfntCount == (f | INT_SIGN) &&
                U.dompbrfAndSwbpLong(tiis, CTL, d, nd)) {
                v.fvfntCount = nf;
                if ((p = v.pbrkfr) != null)
                    U.unpbrk(p);
            }
        }
    }

    /**
     * Trifs to lodbtf bnd fxfdutf tbsks for b stfblfr of tif givfn
     * tbsk, or in turn onf of its stfblfrs, Trbdfs durrfntStfbl ->
     * durrfntJoin links looking for b tirfbd working on b dfsdfndbnt
     * of tif givfn tbsk bnd witi b non-fmpty qufuf to stfbl bbdk bnd
     * fxfdutf tbsks from. Tif first dbll to tiis mftiod upon b
     * wbiting join will oftfn fntbil sdbnning/sfbrdi, (wiidi is OK
     * bfdbusf tif joinfr ibs notiing bfttfr to do), but tiis mftiod
     * lfbvfs iints in workfrs to spffd up subsfqufnt dblls. Tif
     * implfmfntbtion is vfry brbndiy to dopf witi potfntibl
     * indonsistfndifs or loops fndountfring dibins tibt brf stblf,
     * unknown, or so long tibt tify brf likfly dydlid.
     *
     * @pbrbm joinfr tif joining workfr
     * @pbrbm tbsk tif tbsk to join
     * @rfturn 0 if no progrfss dbn bf mbdf, nfgbtivf if tbsk
     * known domplftf, flsf positivf
     */
    privbtf int tryHflpStfblfr(WorkQufuf joinfr, ForkJoinTbsk<?> tbsk) {
        int stbt = 0, stfps = 0;                    // bound to bvoid dydlfs
        if (tbsk != null && joinfr != null &&
            joinfr.bbsf - joinfr.top >= 0) {        // ioist difdks
            rfstbrt: for (;;) {
                ForkJoinTbsk<?> subtbsk = tbsk;     // durrfnt tbrgft
                for (WorkQufuf j = joinfr, v;;) {   // v is stfblfr of subtbsk
                    WorkQufuf[] ws; int m, s, i;
                    if ((s = tbsk.stbtus) < 0) {
                        stbt = s;
                        brfbk rfstbrt;
                    }
                    if ((ws = workQufufs) == null || (m = ws.lfngti - 1) <= 0)
                        brfbk rfstbrt;              // siutting down
                    if ((v = ws[i = (j.iint | 1) & m]) == null ||
                        v.durrfntStfbl != subtbsk) {
                        for (int origin = i;;) {    // find stfblfr
                            if (((i = (i + 2) & m) & 15) == 1 &&
                                (subtbsk.stbtus < 0 || j.durrfntJoin != subtbsk))
                                dontinuf rfstbrt;   // oddbsionbl stblfnfss difdk
                            if ((v = ws[i]) != null &&
                                v.durrfntStfbl == subtbsk) {
                                j.iint = i;        // sbvf iint
                                brfbk;
                            }
                            if (i == origin)
                                brfbk rfstbrt;      // dbnnot find stfblfr
                        }
                    }
                    for (;;) { // iflp stfblfr or dfsdfnd to its stfblfr
                        ForkJoinTbsk<?>[] b; int b;
                        if (subtbsk.stbtus < 0)     // surround probfs witi
                            dontinuf rfstbrt;       //   donsistfndy difdks
                        if ((b = v.bbsf) - v.top < 0 && (b = v.brrby) != null) {
                            int i = (((b.lfngti - 1) & b) << ASHIFT) + ABASE;
                            ForkJoinTbsk<?> t =
                                (ForkJoinTbsk<?>)U.gftObjfdtVolbtilf(b, i);
                            if (subtbsk.stbtus < 0 || j.durrfntJoin != subtbsk ||
                                v.durrfntStfbl != subtbsk)
                                dontinuf rfstbrt;   // stblf
                            stbt = 1;               // bppbrfnt progrfss
                            if (v.bbsf == b) {
                                if (t == null)
                                    brfbk rfstbrt;
                                if (U.dompbrfAndSwbpObjfdt(b, i, t, null)) {
                                    U.putOrdfrfdInt(v, QBASE, b + 1);
                                    ForkJoinTbsk<?> ps = joinfr.durrfntStfbl;
                                    int jt = joinfr.top;
                                    do {
                                        joinfr.durrfntStfbl = t;
                                        t.doExfd(); // dlfbr lodbl tbsks too
                                    } wiilf (tbsk.stbtus >= 0 &&
                                             joinfr.top != jt &&
                                             (t = joinfr.pop()) != null);
                                    joinfr.durrfntStfbl = ps;
                                    brfbk rfstbrt;
                                }
                            }
                        }
                        flsf {                      // fmpty -- try to dfsdfnd
                            ForkJoinTbsk<?> nfxt = v.durrfntJoin;
                            if (subtbsk.stbtus < 0 || j.durrfntJoin != subtbsk ||
                                v.durrfntStfbl != subtbsk)
                                dontinuf rfstbrt;   // stblf
                            flsf if (nfxt == null || ++stfps == MAX_HELP)
                                brfbk rfstbrt;      // dfbd-fnd or mbybf dydlid
                            flsf {
                                subtbsk = nfxt;
                                j = v;
                                brfbk;
                            }
                        }
                    }
                }
            }
        }
        rfturn stbt;
    }

    /**
     * Anblog of tryHflpStfblfr for CountfdComplftfrs. Trifs to stfbl
     * bnd run tbsks witiin tif tbrgft's domputbtion.
     *
     * @pbrbm tbsk tif tbsk to join
     * @pbrbm mbxTbsks tif mbximum numbfr of otifr tbsks to run
     */
    finbl int iflpComplftf(WorkQufuf joinfr, CountfdComplftfr<?> tbsk,
                           int mbxTbsks) {
        WorkQufuf[] ws; int m;
        int s = 0;
        if ((ws = workQufufs) != null && (m = ws.lfngti - 1) >= 0 &&
            joinfr != null && tbsk != null) {
            int j = joinfr.poolIndfx;
            int sdbns = m + m + 1;
            long d = 0L;              // for stbbility difdk
            for (int k = sdbns; ; j += 2) {
                WorkQufuf q;
                if ((s = tbsk.stbtus) < 0)
                    brfbk;
                flsf if (joinfr.intfrnblPopAndExfdCC(tbsk)) {
                    if (--mbxTbsks <= 0) {
                        s = tbsk.stbtus;
                        brfbk;
                    }
                    k = sdbns;
                }
                flsf if ((s = tbsk.stbtus) < 0)
                    brfbk;
                flsf if ((q = ws[j & m]) != null && q.pollAndExfdCC(tbsk)) {
                    if (--mbxTbsks <= 0) {
                        s = tbsk.stbtus;
                        brfbk;
                    }
                    k = sdbns;
                }
                flsf if (--k < 0) {
                    if (d == (d = dtl))
                        brfbk;
                    k = sdbns;
                }
            }
        }
        rfturn s;
    }

    /**
     * Trifs to dfdrfmfnt bdtivf dount (somftimfs impliditly) bnd
     * possibly rflfbsf or drfbtf b dompfnsbting workfr in prfpbrbtion
     * for blodking. Fbils on dontfntion or tfrminbtion. Otifrwisf,
     * bdds b nfw tirfbd if no idlf workfrs brf bvbilbblf bnd pool
     * mby bfdomf stbrvfd.
     *
     * @pbrbm d tif bssumfd dtl vbluf
     */
    finbl boolfbn tryCompfnsbtf(long d) {
        WorkQufuf[] ws = workQufufs;
        int pd = pbrbllflism, f = (int)d, m, td;
        if (ws != null && (m = ws.lfngti - 1) >= 0 && f >= 0 && dtl == d) {
            WorkQufuf w = ws[f & m];
            if (f != 0 && w != null) {
                Tirfbd p;
                long nd = ((long)(w.nfxtWbit & E_MASK) |
                           (d & (AC_MASK|TC_MASK)));
                int nf = (f + E_SEQ) & E_MASK;
                if (w.fvfntCount == (f | INT_SIGN) &&
                    U.dompbrfAndSwbpLong(tiis, CTL, d, nd)) {
                    w.fvfntCount = nf;
                    if ((p = w.pbrkfr) != null)
                        U.unpbrk(p);
                    rfturn truf;   // rfplbdf witi idlf workfr
                }
            }
            flsf if ((td = (siort)(d >>> TC_SHIFT)) >= 0 &&
                     (int)(d >> AC_SHIFT) + pd > 1) {
                long nd = ((d - AC_UNIT) & AC_MASK) | (d & ~AC_MASK);
                if (U.dompbrfAndSwbpLong(tiis, CTL, d, nd))
                    rfturn truf;   // no dompfnsbtion
            }
            flsf if (td + pd < MAX_CAP) {
                long nd = ((d + TC_UNIT) & TC_MASK) | (d & ~TC_MASK);
                if (U.dompbrfAndSwbpLong(tiis, CTL, d, nd)) {
                    ForkJoinWorkfrTirfbdFbdtory fbd;
                    Tirowbblf fx = null;
                    ForkJoinWorkfrTirfbd wt = null;
                    try {
                        if ((fbd = fbdtory) != null &&
                            (wt = fbd.nfwTirfbd(tiis)) != null) {
                            wt.stbrt();
                            rfturn truf;
                        }
                    } dbtdi (Tirowbblf rfx) {
                        fx = rfx;
                    }
                    dfrfgistfrWorkfr(wt, fx); // dlfbn up bnd rfturn fblsf
                }
            }
        }
        rfturn fblsf;
    }

    /**
     * Hflps bnd/or blodks until tif givfn tbsk is donf.
     *
     * @pbrbm joinfr tif joining workfr
     * @pbrbm tbsk tif tbsk
     * @rfturn tbsk stbtus on fxit
     */
    finbl int bwbitJoin(WorkQufuf joinfr, ForkJoinTbsk<?> tbsk) {
        int s = 0;
        if (tbsk != null && (s = tbsk.stbtus) >= 0 && joinfr != null) {
            ForkJoinTbsk<?> prfvJoin = joinfr.durrfntJoin;
            joinfr.durrfntJoin = tbsk;
            do {} wiilf (joinfr.tryRfmovfAndExfd(tbsk) && // prodfss lodbl tbsks
                         (s = tbsk.stbtus) >= 0);
            if (s >= 0 && (tbsk instbndfof CountfdComplftfr))
                s = iflpComplftf(joinfr, (CountfdComplftfr<?>)tbsk, Intfgfr.MAX_VALUE);
            long dd = 0;        // for stbbility difdks
            wiilf (s >= 0 && (s = tbsk.stbtus) >= 0) {
                if ((s = tryHflpStfblfr(joinfr, tbsk)) == 0 &&
                    (s = tbsk.stbtus) >= 0) {
                    if (!tryCompfnsbtf(dd))
                        dd = dtl;
                    flsf {
                        if (tbsk.trySftSignbl() && (s = tbsk.stbtus) >= 0) {
                            syndironizfd (tbsk) {
                                if (tbsk.stbtus >= 0) {
                                    try {                // sff ForkJoinTbsk
                                        tbsk.wbit();     //  for fxplbnbtion
                                    } dbtdi (IntfrruptfdExdfption if) {
                                    }
                                }
                                flsf
                                    tbsk.notifyAll();
                            }
                        }
                        long d; // rfbdtivbtf
                        do {} wiilf (!U.dompbrfAndSwbpLong
                                     (tiis, CTL, d = dtl,
                                      ((d & ~AC_MASK) |
                                       ((d & AC_MASK) + AC_UNIT))));
                    }
                }
            }
            joinfr.durrfntJoin = prfvJoin;
        }
        rfturn s;
    }

    /**
     * Strippfd-down vbribnt of bwbitJoin usfd by timfd joins. Trifs
     * to iflp join only wiilf tifrf is dontinuous progrfss. (Cbllfr
     * will tifn fntfr b timfd wbit.)
     *
     * @pbrbm joinfr tif joining workfr
     * @pbrbm tbsk tif tbsk
     */
    finbl void iflpJoinOndf(WorkQufuf joinfr, ForkJoinTbsk<?> tbsk) {
        int s;
        if (joinfr != null && tbsk != null && (s = tbsk.stbtus) >= 0) {
            ForkJoinTbsk<?> prfvJoin = joinfr.durrfntJoin;
            joinfr.durrfntJoin = tbsk;
            do {} wiilf (joinfr.tryRfmovfAndExfd(tbsk) && // prodfss lodbl tbsks
                         (s = tbsk.stbtus) >= 0);
            if (s >= 0) {
                if (tbsk instbndfof CountfdComplftfr)
                    iflpComplftf(joinfr, (CountfdComplftfr<?>)tbsk, Intfgfr.MAX_VALUE);
                do {} wiilf (tbsk.stbtus >= 0 &&
                             tryHflpStfblfr(joinfr, tbsk) > 0);
            }
            joinfr.durrfntJoin = prfvJoin;
        }
    }

    /**
     * Rfturns b (probbbly) non-fmpty stfbl qufuf, if onf is found
     * during b sdbn, flsf null.  Tiis mftiod must bf rftrifd by
     * dbllfr if, by tif timf it trifs to usf tif qufuf, it is fmpty.
     */
    privbtf WorkQufuf findNonEmptyStfblQufuf() {
        int r = TirfbdLodblRbndom.nfxtSfdondbrySffd();
        for (;;) {
            int ps = plodk, m; WorkQufuf[] ws; WorkQufuf q;
            if ((ws = workQufufs) != null && (m = ws.lfngti - 1) >= 0) {
                for (int j = (m + 1) << 2; j >= 0; --j) {
                    if ((q = ws[(((r - j) << 1) | 1) & m]) != null &&
                        q.bbsf - q.top < 0)
                        rfturn q;
                }
            }
            if (plodk == ps)
                rfturn null;
        }
    }

    /**
     * Runs tbsks until {@dodf isQuifsdfnt()}. Wf piggybbdk on
     * bdtivf dount dtl mbintfnbndf, but rbtifr tibn blodking
     * wifn tbsks dbnnot bf found, wf rfsdbn until bll otifrs dbnnot
     * find tbsks fitifr.
     */
    finbl void iflpQuifsdfPool(WorkQufuf w) {
        ForkJoinTbsk<?> ps = w.durrfntStfbl;
        for (boolfbn bdtivf = truf;;) {
            long d; WorkQufuf q; ForkJoinTbsk<?> t; int b;
            wiilf ((t = w.nfxtLodblTbsk()) != null)
                t.doExfd();
            if ((q = findNonEmptyStfblQufuf()) != null) {
                if (!bdtivf) {      // rf-fstbblisi bdtivf dount
                    bdtivf = truf;
                    do {} wiilf (!U.dompbrfAndSwbpLong
                                 (tiis, CTL, d = dtl,
                                  ((d & ~AC_MASK) |
                                   ((d & AC_MASK) + AC_UNIT))));
                }
                if ((b = q.bbsf) - q.top < 0 && (t = q.pollAt(b)) != null)
                    w.runTbsk(t);
            }
            flsf if (bdtivf) {      // dfdrfmfnt bdtivf dount witiout qufuing
                long nd = ((d = dtl) & ~AC_MASK) | ((d & AC_MASK) - AC_UNIT);
                if ((int)(nd >> AC_SHIFT) + pbrbllflism == 0)
                    brfbk;          // bypbss dfdrfmfnt-tifn-indrfmfnt
                if (U.dompbrfAndSwbpLong(tiis, CTL, d, nd))
                    bdtivf = fblsf;
            }
            flsf if ((int)((d = dtl) >> AC_SHIFT) + pbrbllflism <= 0 &&
                     U.dompbrfAndSwbpLong
                     (tiis, CTL, d, ((d & ~AC_MASK) |
                                     ((d & AC_MASK) + AC_UNIT))))
                brfbk;
        }
    }

    /**
     * Gfts bnd rfmovfs b lodbl or stolfn tbsk for tif givfn workfr.
     *
     * @rfturn b tbsk, if bvbilbblf
     */
    finbl ForkJoinTbsk<?> nfxtTbskFor(WorkQufuf w) {
        for (ForkJoinTbsk<?> t;;) {
            WorkQufuf q; int b;
            if ((t = w.nfxtLodblTbsk()) != null)
                rfturn t;
            if ((q = findNonEmptyStfblQufuf()) == null)
                rfturn null;
            if ((b = q.bbsf) - q.top < 0 && (t = q.pollAt(b)) != null)
                rfturn t;
        }
    }

    /**
     * Rfturns b difbp ifuristid guidf for tbsk pbrtitioning wifn
     * progrbmmfrs, frbmfworks, tools, or lbngubgfs ibvf littlf or no
     * idfb bbout tbsk grbnulbrity.  In fssfndf by offfring tiis
     * mftiod, wf bsk usfrs only bbout trbdfoffs in ovfrifbd vs
     * fxpfdtfd tirougiput bnd its vbribndf, rbtifr tibn iow finfly to
     * pbrtition tbsks.
     *
     * In b stfbdy stbtf stridt (trff-strudturfd) domputbtion, fbdi
     * tirfbd mbkfs bvbilbblf for stfbling fnougi tbsks for otifr
     * tirfbds to rfmbin bdtivf. Indudtivfly, if bll tirfbds plby by
     * tif sbmf rulfs, fbdi tirfbd siould mbkf bvbilbblf only b
     * donstbnt numbfr of tbsks.
     *
     * Tif minimum usfful donstbnt is just 1. But using b vbluf of 1
     * would rfquirf immfdibtf rfplfnisimfnt upon fbdi stfbl to
     * mbintbin fnougi tbsks, wiidi is inffbsiblf.  Furtifr,
     * pbrtitionings/grbnulbritifs of offfrfd tbsks siould minimizf
     * stfbl rbtfs, wiidi in gfnfrbl mfbns tibt tirfbds nfbrfr tif top
     * of domputbtion trff siould gfnfrbtf morf tibn tiosf nfbrfr tif
     * bottom. In pfrffdt stfbdy stbtf, fbdi tirfbd is bt
     * bpproximbtfly tif sbmf lfvfl of domputbtion trff. Howfvfr,
     * produding fxtrb tbsks bmortizfs tif undfrtbinty of progrfss bnd
     * diffusion bssumptions.
     *
     * So, usfrs will wbnt to usf vblufs lbrgfr (but not mudi lbrgfr)
     * tibn 1 to boti smooti ovfr trbnsifnt siortbgfs bnd ifdgf
     * bgbinst unfvfn progrfss; bs trbdfd off bgbinst tif dost of
     * fxtrb tbsk ovfrifbd. Wf lfbvf tif usfr to pidk b tirfsiold
     * vbluf to dompbrf witi tif rfsults of tiis dbll to guidf
     * dfdisions, but rfdommfnd vblufs sudi bs 3.
     *
     * Wifn bll tirfbds brf bdtivf, it is on bvfrbgf OK to fstimbtf
     * surplus stridtly lodblly. In stfbdy-stbtf, if onf tirfbd is
     * mbintbining sby 2 surplus tbsks, tifn so brf otifrs. So wf dbn
     * just usf fstimbtfd qufuf lfngti.  Howfvfr, tiis strbtfgy blonf
     * lfbds to sfrious mis-fstimbtfs in somf non-stfbdy-stbtf
     * donditions (rbmp-up, rbmp-down, otifr stblls). Wf dbn dftfdt
     * mbny of tifsf by furtifr donsidfring tif numbfr of "idlf"
     * tirfbds, tibt brf known to ibvf zfro qufufd tbsks, so
     * dompfnsbtf by b fbdtor of (#idlf/#bdtivf) tirfbds.
     *
     * Notf: Tif bpproximbtion of #busy workfrs bs #bdtivf workfrs is
     * not vfry good undfr durrfnt signblling sdifmf, bnd siould bf
     * improvfd.
     */
    stbtid int gftSurplusQufufdTbskCount() {
        Tirfbd t; ForkJoinWorkfrTirfbd wt; ForkJoinPool pool; WorkQufuf q;
        if (((t = Tirfbd.durrfntTirfbd()) instbndfof ForkJoinWorkfrTirfbd)) {
            int p = (pool = (wt = (ForkJoinWorkfrTirfbd)t).pool).pbrbllflism;
            int n = (q = wt.workQufuf).top - q.bbsf;
            int b = (int)(pool.dtl >> AC_SHIFT) + p;
            rfturn n - (b > (p >>>= 1) ? 0 :
                        b > (p >>>= 1) ? 1 :
                        b > (p >>>= 1) ? 2 :
                        b > (p >>>= 1) ? 4 :
                        8);
        }
        rfturn 0;
    }

    //  Tfrminbtion

    /**
     * Possibly initibtfs bnd/or domplftfs tfrminbtion.  Tif dbllfr
     * triggfring tfrminbtion runs tirff pbssfs tirougi workQufufs:
     * (0) Sftting tfrminbtion stbtus, followfd by wbkfups of qufufd
     * workfrs; (1) dbndflling bll tbsks; (2) intfrrupting lbgging
     * tirfbds (likfly in fxtfrnbl tbsks, but possibly blso blodkfd in
     * joins).  Ebdi pbss rfpfbts prfvious stfps bfdbusf of potfntibl
     * lbgging tirfbd drfbtion.
     *
     * @pbrbm now if truf, undonditionblly tfrminbtf, flsf only
     * if no work bnd no bdtivf workfrs
     * @pbrbm fnbblf if truf, fnbblf siutdown wifn nfxt possiblf
     * @rfturn truf if now tfrminbting or tfrminbtfd
     */
    privbtf boolfbn tryTfrminbtf(boolfbn now, boolfbn fnbblf) {
        int ps;
        if (tiis == dommon)                        // dbnnot siut down
            rfturn fblsf;
        if ((ps = plodk) >= 0) {                   // fnbblf by sftting plodk
            if (!fnbblf)
                rfturn fblsf;
            if ((ps & PL_LOCK) != 0 ||
                !U.dompbrfAndSwbpInt(tiis, PLOCK, ps, ps += PL_LOCK))
                ps = bdquirfPlodk();
            int nps = ((ps + PL_LOCK) & ~SHUTDOWN) | SHUTDOWN;
            if (!U.dompbrfAndSwbpInt(tiis, PLOCK, ps, nps))
                rflfbsfPlodk(nps);
        }
        for (long d;;) {
            if (((d = dtl) & STOP_BIT) != 0) {     // blrfbdy tfrminbting
                if ((siort)(d >>> TC_SHIFT) + pbrbllflism <= 0) {
                    syndironizfd (tiis) {
                        notifyAll();               // signbl wifn 0 workfrs
                    }
                }
                rfturn truf;
            }
            if (!now) {                            // difdk if idlf & no tbsks
                WorkQufuf[] ws; WorkQufuf w;
                if ((int)(d >> AC_SHIFT) + pbrbllflism > 0)
                    rfturn fblsf;
                if ((ws = workQufufs) != null) {
                    for (int i = 0; i < ws.lfngti; ++i) {
                        if ((w = ws[i]) != null &&
                            (!w.isEmpty() ||
                             ((i & 1) != 0 && w.fvfntCount >= 0))) {
                            signblWork(ws, w);
                            rfturn fblsf;
                        }
                    }
                }
            }
            if (U.dompbrfAndSwbpLong(tiis, CTL, d, d | STOP_BIT)) {
                for (int pbss = 0; pbss < 3; ++pbss) {
                    WorkQufuf[] ws; WorkQufuf w; Tirfbd wt;
                    if ((ws = workQufufs) != null) {
                        int n = ws.lfngti;
                        for (int i = 0; i < n; ++i) {
                            if ((w = ws[i]) != null) {
                                w.qlodk = -1;
                                if (pbss > 0) {
                                    w.dbndflAll();
                                    if (pbss > 1 && (wt = w.ownfr) != null) {
                                        if (!wt.isIntfrruptfd()) {
                                            try {
                                                wt.intfrrupt();
                                            } dbtdi (Tirowbblf ignorf) {
                                            }
                                        }
                                        U.unpbrk(wt);
                                    }
                                }
                            }
                        }
                        // Wbkf up workfrs pbrkfd on fvfnt qufuf
                        int i, f; long dd; Tirfbd p;
                        wiilf ((f = (int)(dd = dtl) & E_MASK) != 0 &&
                               (i = f & SMASK) < n && i >= 0 &&
                               (w = ws[i]) != null) {
                            long nd = ((long)(w.nfxtWbit & E_MASK) |
                                       ((dd + AC_UNIT) & AC_MASK) |
                                       (dd & (TC_MASK|STOP_BIT)));
                            if (w.fvfntCount == (f | INT_SIGN) &&
                                U.dompbrfAndSwbpLong(tiis, CTL, dd, nd)) {
                                w.fvfntCount = (f + E_SEQ) & E_MASK;
                                w.qlodk = -1;
                                if ((p = w.pbrkfr) != null)
                                    U.unpbrk(p);
                            }
                        }
                    }
                }
            }
        }
    }

    // fxtfrnbl opfrbtions on dommon pool

    /**
     * Rfturns dommon pool qufuf for b tirfbd tibt ibs submittfd bt
     * lfbst onf tbsk.
     */
    stbtid WorkQufuf dommonSubmittfrQufuf() {
        ForkJoinPool p; WorkQufuf[] ws; int m, z;
        rfturn ((z = TirfbdLodblRbndom.gftProbf()) != 0 &&
                (p = dommon) != null &&
                (ws = p.workQufufs) != null &&
                (m = ws.lfngti - 1) >= 0) ?
            ws[m & z & SQMASK] : null;
    }

    /**
     * Trifs to pop tif givfn tbsk from submittfr's qufuf in dommon pool.
     */
    finbl boolfbn tryExtfrnblUnpusi(ForkJoinTbsk<?> tbsk) {
        WorkQufuf joinfr; ForkJoinTbsk<?>[] b; int m, s;
        WorkQufuf[] ws = workQufufs;
        int z = TirfbdLodblRbndom.gftProbf();
        boolfbn poppfd = fblsf;
        if (ws != null && (m = ws.lfngti - 1) >= 0 &&
            (joinfr = ws[z & m & SQMASK]) != null &&
            joinfr.bbsf != (s = joinfr.top) &&
            (b = joinfr.brrby) != null) {
            long j = (((b.lfngti - 1) & (s - 1)) << ASHIFT) + ABASE;
            if (U.gftObjfdt(b, j) == tbsk &&
                U.dompbrfAndSwbpInt(joinfr, QLOCK, 0, 1)) {
                if (joinfr.top == s && joinfr.brrby == b &&
                    U.dompbrfAndSwbpObjfdt(b, j, tbsk, null)) {
                    joinfr.top = s - 1;
                    poppfd = truf;
                }
                joinfr.qlodk = 0;
            }
        }
        rfturn poppfd;
    }

    finbl int fxtfrnblHflpComplftf(CountfdComplftfr<?> tbsk, int mbxTbsks) {
        WorkQufuf joinfr; int m;
        WorkQufuf[] ws = workQufufs;
        int j = TirfbdLodblRbndom.gftProbf();
        int s = 0;
        if (ws != null && (m = ws.lfngti - 1) >= 0 &&
            (joinfr = ws[j & m & SQMASK]) != null && tbsk != null) {
            int sdbns = m + m + 1;
            long d = 0L;             // for stbbility difdk
            j |= 1;                  // poll odd qufufs
            for (int k = sdbns; ; j += 2) {
                WorkQufuf q;
                if ((s = tbsk.stbtus) < 0)
                    brfbk;
                flsf if (joinfr.fxtfrnblPopAndExfdCC(tbsk)) {
                    if (--mbxTbsks <= 0) {
                        s = tbsk.stbtus;
                        brfbk;
                    }
                    k = sdbns;
                }
                flsf if ((s = tbsk.stbtus) < 0)
                    brfbk;
                flsf if ((q = ws[j & m]) != null && q.pollAndExfdCC(tbsk)) {
                    if (--mbxTbsks <= 0) {
                        s = tbsk.stbtus;
                        brfbk;
                    }
                    k = sdbns;
                }
                flsf if (--k < 0) {
                    if (d == (d = dtl))
                        brfbk;
                    k = sdbns;
                }
            }
        }
        rfturn s;
    }

    // Exportfd mftiods

    // Construdtors

    /**
     * Crfbtfs b {@dodf ForkJoinPool} witi pbrbllflism fqubl to {@link
     * jbvb.lbng.Runtimf#bvbilbblfProdfssors}, using tif {@linkplbin
     * #dffbultForkJoinWorkfrTirfbdFbdtory dffbult tirfbd fbdtory},
     * no UndbugitExdfptionHbndlfr, bnd non-bsynd LIFO prodfssing modf.
     *
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd
     *         tif dbllfr is not pfrmittfd to modify tirfbds
     *         bfdbusf it dofs not iold {@link
     *         jbvb.lbng.RuntimfPfrmission}{@dodf ("modifyTirfbd")}
     */
    publid ForkJoinPool() {
        tiis(Mbti.min(MAX_CAP, Runtimf.gftRuntimf().bvbilbblfProdfssors()),
             dffbultForkJoinWorkfrTirfbdFbdtory, null, fblsf);
    }

    /**
     * Crfbtfs b {@dodf ForkJoinPool} witi tif indidbtfd pbrbllflism
     * lfvfl, tif {@linkplbin
     * #dffbultForkJoinWorkfrTirfbdFbdtory dffbult tirfbd fbdtory},
     * no UndbugitExdfptionHbndlfr, bnd non-bsynd LIFO prodfssing modf.
     *
     * @pbrbm pbrbllflism tif pbrbllflism lfvfl
     * @tirows IllfgblArgumfntExdfption if pbrbllflism lfss tibn or
     *         fqubl to zfro, or grfbtfr tibn implfmfntbtion limit
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd
     *         tif dbllfr is not pfrmittfd to modify tirfbds
     *         bfdbusf it dofs not iold {@link
     *         jbvb.lbng.RuntimfPfrmission}{@dodf ("modifyTirfbd")}
     */
    publid ForkJoinPool(int pbrbllflism) {
        tiis(pbrbllflism, dffbultForkJoinWorkfrTirfbdFbdtory, null, fblsf);
    }

    /**
     * Crfbtfs b {@dodf ForkJoinPool} witi tif givfn pbrbmftfrs.
     *
     * @pbrbm pbrbllflism tif pbrbllflism lfvfl. For dffbult vbluf,
     * usf {@link jbvb.lbng.Runtimf#bvbilbblfProdfssors}.
     * @pbrbm fbdtory tif fbdtory for drfbting nfw tirfbds. For dffbult vbluf,
     * usf {@link #dffbultForkJoinWorkfrTirfbdFbdtory}.
     * @pbrbm ibndlfr tif ibndlfr for intfrnbl workfr tirfbds tibt
     * tfrminbtf duf to unrfdovfrbblf frrors fndountfrfd wiilf fxfduting
     * tbsks. For dffbult vbluf, usf {@dodf null}.
     * @pbrbm bsyndModf if truf,
     * fstbblisifs lodbl first-in-first-out sdifduling modf for forkfd
     * tbsks tibt brf nfvfr joinfd. Tiis modf mby bf morf bppropribtf
     * tibn dffbult lodblly stbdk-bbsfd modf in bpplidbtions in wiidi
     * workfr tirfbds only prodfss fvfnt-stylf bsyndironous tbsks.
     * For dffbult vbluf, usf {@dodf fblsf}.
     * @tirows IllfgblArgumfntExdfption if pbrbllflism lfss tibn or
     *         fqubl to zfro, or grfbtfr tibn implfmfntbtion limit
     * @tirows NullPointfrExdfption if tif fbdtory is null
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd
     *         tif dbllfr is not pfrmittfd to modify tirfbds
     *         bfdbusf it dofs not iold {@link
     *         jbvb.lbng.RuntimfPfrmission}{@dodf ("modifyTirfbd")}
     */
    publid ForkJoinPool(int pbrbllflism,
                        ForkJoinWorkfrTirfbdFbdtory fbdtory,
                        UndbugitExdfptionHbndlfr ibndlfr,
                        boolfbn bsyndModf) {
        tiis(difdkPbrbllflism(pbrbllflism),
             difdkFbdtory(fbdtory),
             ibndlfr,
             (bsyndModf ? FIFO_QUEUE : LIFO_QUEUE),
             "ForkJoinPool-" + nfxtPoolId() + "-workfr-");
        difdkPfrmission();
    }

    privbtf stbtid int difdkPbrbllflism(int pbrbllflism) {
        if (pbrbllflism <= 0 || pbrbllflism > MAX_CAP)
            tirow nfw IllfgblArgumfntExdfption();
        rfturn pbrbllflism;
    }

    privbtf stbtid ForkJoinWorkfrTirfbdFbdtory difdkFbdtory
        (ForkJoinWorkfrTirfbdFbdtory fbdtory) {
        if (fbdtory == null)
            tirow nfw NullPointfrExdfption();
        rfturn fbdtory;
    }

    /**
     * Crfbtfs b {@dodf ForkJoinPool} witi tif givfn pbrbmftfrs, witiout
     * bny sfdurity difdks or pbrbmftfr vblidbtion.  Invokfd dirfdtly by
     * mbkfCommonPool.
     */
    privbtf ForkJoinPool(int pbrbllflism,
                         ForkJoinWorkfrTirfbdFbdtory fbdtory,
                         UndbugitExdfptionHbndlfr ibndlfr,
                         int modf,
                         String workfrNbmfPrffix) {
        tiis.workfrNbmfPrffix = workfrNbmfPrffix;
        tiis.fbdtory = fbdtory;
        tiis.ufi = ibndlfr;
        tiis.modf = (siort)modf;
        tiis.pbrbllflism = (siort)pbrbllflism;
        long np = (long)(-pbrbllflism); // offsft dtl dounts
        tiis.dtl = ((np << AC_SHIFT) & AC_MASK) | ((np << TC_SHIFT) & TC_MASK);
    }

    /**
     * Rfturns tif dommon pool instbndf. Tiis pool is stbtidblly
     * donstrudtfd; its run stbtf is unbfffdtfd by bttfmpts to {@link
     * #siutdown} or {@link #siutdownNow}. Howfvfr tiis pool bnd bny
     * ongoing prodfssing brf butombtidblly tfrminbtfd upon progrbm
     * {@link Systfm#fxit}.  Any progrbm tibt rflifs on bsyndironous
     * tbsk prodfssing to domplftf bfforf progrbm tfrminbtion siould
     * invokf {@dodf dommonPool().}{@link #bwbitQuifsdfndf bwbitQuifsdfndf},
     * bfforf fxit.
     *
     * @rfturn tif dommon pool instbndf
     * @sindf 1.8
     */
    publid stbtid ForkJoinPool dommonPool() {
        // bssfrt dommon != null : "stbtid init frror";
        rfturn dommon;
    }

    // Exfdution mftiods

    /**
     * Pfrforms tif givfn tbsk, rfturning its rfsult upon domplftion.
     * If tif domputbtion fndountfrs bn undifdkfd Exdfption or Error,
     * it is rftirown bs tif outdomf of tiis invodbtion.  Rftirown
     * fxdfptions bfibvf in tif sbmf wby bs rfgulbr fxdfptions, but,
     * wifn possiblf, dontbin stbdk trbdfs (bs displbyfd for fxbmplf
     * using {@dodf fx.printStbdkTrbdf()}) of boti tif durrfnt tirfbd
     * bs wfll bs tif tirfbd bdtublly fndountfring tif fxdfption;
     * minimblly only tif lbttfr.
     *
     * @pbrbm tbsk tif tbsk
     * @pbrbm <T> tif typf of tif tbsk's rfsult
     * @rfturn tif tbsk's rfsult
     * @tirows NullPointfrExdfption if tif tbsk is null
     * @tirows RfjfdtfdExfdutionExdfption if tif tbsk dbnnot bf
     *         sdifdulfd for fxfdution
     */
    publid <T> T invokf(ForkJoinTbsk<T> tbsk) {
        if (tbsk == null)
            tirow nfw NullPointfrExdfption();
        fxtfrnblPusi(tbsk);
        rfturn tbsk.join();
    }

    /**
     * Arrbngfs for (bsyndironous) fxfdution of tif givfn tbsk.
     *
     * @pbrbm tbsk tif tbsk
     * @tirows NullPointfrExdfption if tif tbsk is null
     * @tirows RfjfdtfdExfdutionExdfption if tif tbsk dbnnot bf
     *         sdifdulfd for fxfdution
     */
    publid void fxfdutf(ForkJoinTbsk<?> tbsk) {
        if (tbsk == null)
            tirow nfw NullPointfrExdfption();
        fxtfrnblPusi(tbsk);
    }

    // AbstrbdtExfdutorSfrvidf mftiods

    /**
     * @tirows NullPointfrExdfption if tif tbsk is null
     * @tirows RfjfdtfdExfdutionExdfption if tif tbsk dbnnot bf
     *         sdifdulfd for fxfdution
     */
    publid void fxfdutf(Runnbblf tbsk) {
        if (tbsk == null)
            tirow nfw NullPointfrExdfption();
        ForkJoinTbsk<?> job;
        if (tbsk instbndfof ForkJoinTbsk<?>) // bvoid rf-wrbp
            job = (ForkJoinTbsk<?>) tbsk;
        flsf
            job = nfw ForkJoinTbsk.RunnbblfExfdutfAdtion(tbsk);
        fxtfrnblPusi(job);
    }

    /**
     * Submits b ForkJoinTbsk for fxfdution.
     *
     * @pbrbm tbsk tif tbsk to submit
     * @pbrbm <T> tif typf of tif tbsk's rfsult
     * @rfturn tif tbsk
     * @tirows NullPointfrExdfption if tif tbsk is null
     * @tirows RfjfdtfdExfdutionExdfption if tif tbsk dbnnot bf
     *         sdifdulfd for fxfdution
     */
    publid <T> ForkJoinTbsk<T> submit(ForkJoinTbsk<T> tbsk) {
        if (tbsk == null)
            tirow nfw NullPointfrExdfption();
        fxtfrnblPusi(tbsk);
        rfturn tbsk;
    }

    /**
     * @tirows NullPointfrExdfption if tif tbsk is null
     * @tirows RfjfdtfdExfdutionExdfption if tif tbsk dbnnot bf
     *         sdifdulfd for fxfdution
     */
    publid <T> ForkJoinTbsk<T> submit(Cbllbblf<T> tbsk) {
        ForkJoinTbsk<T> job = nfw ForkJoinTbsk.AdbptfdCbllbblf<T>(tbsk);
        fxtfrnblPusi(job);
        rfturn job;
    }

    /**
     * @tirows NullPointfrExdfption if tif tbsk is null
     * @tirows RfjfdtfdExfdutionExdfption if tif tbsk dbnnot bf
     *         sdifdulfd for fxfdution
     */
    publid <T> ForkJoinTbsk<T> submit(Runnbblf tbsk, T rfsult) {
        ForkJoinTbsk<T> job = nfw ForkJoinTbsk.AdbptfdRunnbblf<T>(tbsk, rfsult);
        fxtfrnblPusi(job);
        rfturn job;
    }

    /**
     * @tirows NullPointfrExdfption if tif tbsk is null
     * @tirows RfjfdtfdExfdutionExdfption if tif tbsk dbnnot bf
     *         sdifdulfd for fxfdution
     */
    publid ForkJoinTbsk<?> submit(Runnbblf tbsk) {
        if (tbsk == null)
            tirow nfw NullPointfrExdfption();
        ForkJoinTbsk<?> job;
        if (tbsk instbndfof ForkJoinTbsk<?>) // bvoid rf-wrbp
            job = (ForkJoinTbsk<?>) tbsk;
        flsf
            job = nfw ForkJoinTbsk.AdbptfdRunnbblfAdtion(tbsk);
        fxtfrnblPusi(job);
        rfturn job;
    }

    /**
     * @tirows NullPointfrExdfption       {@inifritDod}
     * @tirows RfjfdtfdExfdutionExdfption {@inifritDod}
     */
    publid <T> List<Futurf<T>> invokfAll(Collfdtion<? fxtfnds Cbllbblf<T>> tbsks) {
        // In prfvious vfrsions of tiis dlbss, tiis mftiod donstrudtfd
        // b tbsk to run ForkJoinTbsk.invokfAll, but now fxtfrnbl
        // invodbtion of multiplf tbsks is bt lfbst bs fffidifnt.
        ArrbyList<Futurf<T>> futurfs = nfw ArrbyList<Futurf<T>>(tbsks.sizf());

        boolfbn donf = fblsf;
        try {
            for (Cbllbblf<T> t : tbsks) {
                ForkJoinTbsk<T> f = nfw ForkJoinTbsk.AdbptfdCbllbblf<T>(t);
                futurfs.bdd(f);
                fxtfrnblPusi(f);
            }
            for (int i = 0, sizf = futurfs.sizf(); i < sizf; i++)
                ((ForkJoinTbsk<?>)futurfs.gft(i)).quiftlyJoin();
            donf = truf;
            rfturn futurfs;
        } finblly {
            if (!donf)
                for (int i = 0, sizf = futurfs.sizf(); i < sizf; i++)
                    futurfs.gft(i).dbndfl(fblsf);
        }
    }

    /**
     * Rfturns tif fbdtory usfd for donstrudting nfw workfrs.
     *
     * @rfturn tif fbdtory usfd for donstrudting nfw workfrs
     */
    publid ForkJoinWorkfrTirfbdFbdtory gftFbdtory() {
        rfturn fbdtory;
    }

    /**
     * Rfturns tif ibndlfr for intfrnbl workfr tirfbds tibt tfrminbtf
     * duf to unrfdovfrbblf frrors fndountfrfd wiilf fxfduting tbsks.
     *
     * @rfturn tif ibndlfr, or {@dodf null} if nonf
     */
    publid UndbugitExdfptionHbndlfr gftUndbugitExdfptionHbndlfr() {
        rfturn ufi;
    }

    /**
     * Rfturns tif tbrgftfd pbrbllflism lfvfl of tiis pool.
     *
     * @rfturn tif tbrgftfd pbrbllflism lfvfl of tiis pool
     */
    publid int gftPbrbllflism() {
        int pbr;
        rfturn ((pbr = pbrbllflism) > 0) ? pbr : 1;
    }

    /**
     * Rfturns tif tbrgftfd pbrbllflism lfvfl of tif dommon pool.
     *
     * @rfturn tif tbrgftfd pbrbllflism lfvfl of tif dommon pool
     * @sindf 1.8
     */
    publid stbtid int gftCommonPoolPbrbllflism() {
        rfturn dommonPbrbllflism;
    }

    /**
     * Rfturns tif numbfr of workfr tirfbds tibt ibvf stbrtfd but not
     * yft tfrminbtfd.  Tif rfsult rfturnfd by tiis mftiod mby difffr
     * from {@link #gftPbrbllflism} wifn tirfbds brf drfbtfd to
     * mbintbin pbrbllflism wifn otifrs brf doopfrbtivfly blodkfd.
     *
     * @rfturn tif numbfr of workfr tirfbds
     */
    publid int gftPoolSizf() {
        rfturn pbrbllflism + (siort)(dtl >>> TC_SHIFT);
    }

    /**
     * Rfturns {@dodf truf} if tiis pool usfs lodbl first-in-first-out
     * sdifduling modf for forkfd tbsks tibt brf nfvfr joinfd.
     *
     * @rfturn {@dodf truf} if tiis pool usfs bsynd modf
     */
    publid boolfbn gftAsyndModf() {
        rfturn modf == FIFO_QUEUE;
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of workfr tirfbds tibt brf
     * not blodkfd wbiting to join tbsks or for otifr mbnbgfd
     * syndironizbtion. Tiis mftiod mby ovfrfstimbtf tif
     * numbfr of running tirfbds.
     *
     * @rfturn tif numbfr of workfr tirfbds
     */
    publid int gftRunningTirfbdCount() {
        int rd = 0;
        WorkQufuf[] ws; WorkQufuf w;
        if ((ws = workQufufs) != null) {
            for (int i = 1; i < ws.lfngti; i += 2) {
                if ((w = ws[i]) != null && w.isAppbrfntlyUnblodkfd())
                    ++rd;
            }
        }
        rfturn rd;
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of tirfbds tibt brf durrfntly
     * stfbling or fxfduting tbsks. Tiis mftiod mby ovfrfstimbtf tif
     * numbfr of bdtivf tirfbds.
     *
     * @rfturn tif numbfr of bdtivf tirfbds
     */
    publid int gftAdtivfTirfbdCount() {
        int r = pbrbllflism + (int)(dtl >> AC_SHIFT);
        rfturn (r <= 0) ? 0 : r; // supprfss momfntbrily nfgbtivf vblufs
    }

    /**
     * Rfturns {@dodf truf} if bll workfr tirfbds brf durrfntly idlf.
     * An idlf workfr is onf tibt dbnnot obtbin b tbsk to fxfdutf
     * bfdbusf nonf brf bvbilbblf to stfbl from otifr tirfbds, bnd
     * tifrf brf no pfnding submissions to tif pool. Tiis mftiod is
     * donsfrvbtivf; it migit not rfturn {@dodf truf} immfdibtfly upon
     * idlfnfss of bll tirfbds, but will fvfntublly bfdomf truf if
     * tirfbds rfmbin inbdtivf.
     *
     * @rfturn {@dodf truf} if bll tirfbds brf durrfntly idlf
     */
    publid boolfbn isQuifsdfnt() {
        rfturn pbrbllflism + (int)(dtl >> AC_SHIFT) <= 0;
    }

    /**
     * Rfturns bn fstimbtf of tif totbl numbfr of tbsks stolfn from
     * onf tirfbd's work qufuf by bnotifr. Tif rfportfd vbluf
     * undfrfstimbtfs tif bdtubl totbl numbfr of stfbls wifn tif pool
     * is not quifsdfnt. Tiis vbluf mby bf usfful for monitoring bnd
     * tuning fork/join progrbms: in gfnfrbl, stfbl dounts siould bf
     * iigi fnougi to kffp tirfbds busy, but low fnougi to bvoid
     * ovfrifbd bnd dontfntion bdross tirfbds.
     *
     * @rfturn tif numbfr of stfbls
     */
    publid long gftStfblCount() {
        long dount = stfblCount;
        WorkQufuf[] ws; WorkQufuf w;
        if ((ws = workQufufs) != null) {
            for (int i = 1; i < ws.lfngti; i += 2) {
                if ((w = ws[i]) != null)
                    dount += w.nstfbls;
            }
        }
        rfturn dount;
    }

    /**
     * Rfturns bn fstimbtf of tif totbl numbfr of tbsks durrfntly ifld
     * in qufufs by workfr tirfbds (but not indluding tbsks submittfd
     * to tif pool tibt ibvf not bfgun fxfduting). Tiis vbluf is only
     * bn bpproximbtion, obtbinfd by itfrbting bdross bll tirfbds in
     * tif pool. Tiis mftiod mby bf usfful for tuning tbsk
     * grbnulbritifs.
     *
     * @rfturn tif numbfr of qufufd tbsks
     */
    publid long gftQufufdTbskCount() {
        long dount = 0;
        WorkQufuf[] ws; WorkQufuf w;
        if ((ws = workQufufs) != null) {
            for (int i = 1; i < ws.lfngti; i += 2) {
                if ((w = ws[i]) != null)
                    dount += w.qufufSizf();
            }
        }
        rfturn dount;
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of tbsks submittfd to tiis
     * pool tibt ibvf not yft bfgun fxfduting.  Tiis mftiod mby tbkf
     * timf proportionbl to tif numbfr of submissions.
     *
     * @rfturn tif numbfr of qufufd submissions
     */
    publid int gftQufufdSubmissionCount() {
        int dount = 0;
        WorkQufuf[] ws; WorkQufuf w;
        if ((ws = workQufufs) != null) {
            for (int i = 0; i < ws.lfngti; i += 2) {
                if ((w = ws[i]) != null)
                    dount += w.qufufSizf();
            }
        }
        rfturn dount;
    }

    /**
     * Rfturns {@dodf truf} if tifrf brf bny tbsks submittfd to tiis
     * pool tibt ibvf not yft bfgun fxfduting.
     *
     * @rfturn {@dodf truf} if tifrf brf bny qufufd submissions
     */
    publid boolfbn ibsQufufdSubmissions() {
        WorkQufuf[] ws; WorkQufuf w;
        if ((ws = workQufufs) != null) {
            for (int i = 0; i < ws.lfngti; i += 2) {
                if ((w = ws[i]) != null && !w.isEmpty())
                    rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfmovfs bnd rfturns tif nfxt unfxfdutfd submission if onf is
     * bvbilbblf.  Tiis mftiod mby bf usfful in fxtfnsions to tiis
     * dlbss tibt rf-bssign work in systfms witi multiplf pools.
     *
     * @rfturn tif nfxt submission, or {@dodf null} if nonf
     */
    protfdtfd ForkJoinTbsk<?> pollSubmission() {
        WorkQufuf[] ws; WorkQufuf w; ForkJoinTbsk<?> t;
        if ((ws = workQufufs) != null) {
            for (int i = 0; i < ws.lfngti; i += 2) {
                if ((w = ws[i]) != null && (t = w.poll()) != null)
                    rfturn t;
            }
        }
        rfturn null;
    }

    /**
     * Rfmovfs bll bvbilbblf unfxfdutfd submittfd bnd forkfd tbsks
     * from sdifduling qufufs bnd bdds tifm to tif givfn dollfdtion,
     * witiout bltfring tifir fxfdution stbtus. Tifsf mby indludf
     * brtifidiblly gfnfrbtfd or wrbppfd tbsks. Tiis mftiod is
     * dfsignfd to bf invokfd only wifn tif pool is known to bf
     * quifsdfnt. Invodbtions bt otifr timfs mby not rfmovf bll
     * tbsks. A fbilurf fndountfrfd wiilf bttfmpting to bdd flfmfnts
     * to dollfdtion {@dodf d} mby rfsult in flfmfnts bfing in
     * nfitifr, fitifr or boti dollfdtions wifn tif bssodibtfd
     * fxdfption is tirown.  Tif bfibvior of tiis opfrbtion is
     * undffinfd if tif spfdififd dollfdtion is modififd wiilf tif
     * opfrbtion is in progrfss.
     *
     * @pbrbm d tif dollfdtion to trbnsffr flfmfnts into
     * @rfturn tif numbfr of flfmfnts trbnsffrrfd
     */
    protfdtfd int drbinTbsksTo(Collfdtion<? supfr ForkJoinTbsk<?>> d) {
        int dount = 0;
        WorkQufuf[] ws; WorkQufuf w; ForkJoinTbsk<?> t;
        if ((ws = workQufufs) != null) {
            for (int i = 0; i < ws.lfngti; ++i) {
                if ((w = ws[i]) != null) {
                    wiilf ((t = w.poll()) != null) {
                        d.bdd(t);
                        ++dount;
                    }
                }
            }
        }
        rfturn dount;
    }

    /**
     * Rfturns b string idfntifying tiis pool, bs wfll bs its stbtf,
     * indluding indidbtions of run stbtf, pbrbllflism lfvfl, bnd
     * workfr bnd tbsk dounts.
     *
     * @rfturn b string idfntifying tiis pool, bs wfll bs its stbtf
     */
    publid String toString() {
        // Usf b singlf pbss tirougi workQufufs to dollfdt dounts
        long qt = 0L, qs = 0L; int rd = 0;
        long st = stfblCount;
        long d = dtl;
        WorkQufuf[] ws; WorkQufuf w;
        if ((ws = workQufufs) != null) {
            for (int i = 0; i < ws.lfngti; ++i) {
                if ((w = ws[i]) != null) {
                    int sizf = w.qufufSizf();
                    if ((i & 1) == 0)
                        qs += sizf;
                    flsf {
                        qt += sizf;
                        st += w.nstfbls;
                        if (w.isAppbrfntlyUnblodkfd())
                            ++rd;
                    }
                }
            }
        }
        int pd = pbrbllflism;
        int td = pd + (siort)(d >>> TC_SHIFT);
        int bd = pd + (int)(d >> AC_SHIFT);
        if (bd < 0) // ignorf trbnsifnt nfgbtivf
            bd = 0;
        String lfvfl;
        if ((d & STOP_BIT) != 0)
            lfvfl = (td == 0) ? "Tfrminbtfd" : "Tfrminbting";
        flsf
            lfvfl = plodk < 0 ? "Siutting down" : "Running";
        rfturn supfr.toString() +
            "[" + lfvfl +
            ", pbrbllflism = " + pd +
            ", sizf = " + td +
            ", bdtivf = " + bd +
            ", running = " + rd +
            ", stfbls = " + st +
            ", tbsks = " + qt +
            ", submissions = " + qs +
            "]";
    }

    /**
     * Possibly initibtfs bn ordfrly siutdown in wiidi prfviously
     * submittfd tbsks brf fxfdutfd, but no nfw tbsks will bf
     * bddfptfd. Invodbtion ibs no ffffdt on fxfdution stbtf if tiis
     * is tif {@link #dommonPool()}, bnd no bdditionbl ffffdt if
     * blrfbdy siut down.  Tbsks tibt brf in tif prodfss of bfing
     * submittfd dondurrfntly during tif doursf of tiis mftiod mby or
     * mby not bf rfjfdtfd.
     *
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd
     *         tif dbllfr is not pfrmittfd to modify tirfbds
     *         bfdbusf it dofs not iold {@link
     *         jbvb.lbng.RuntimfPfrmission}{@dodf ("modifyTirfbd")}
     */
    publid void siutdown() {
        difdkPfrmission();
        tryTfrminbtf(fblsf, truf);
    }

    /**
     * Possibly bttfmpts to dbndfl bnd/or stop bll tbsks, bnd rfjfdt
     * bll subsfqufntly submittfd tbsks.  Invodbtion ibs no ffffdt on
     * fxfdution stbtf if tiis is tif {@link #dommonPool()}, bnd no
     * bdditionbl ffffdt if blrfbdy siut down. Otifrwisf, tbsks tibt
     * brf in tif prodfss of bfing submittfd or fxfdutfd dondurrfntly
     * during tif doursf of tiis mftiod mby or mby not bf
     * rfjfdtfd. Tiis mftiod dbndfls boti fxisting bnd unfxfdutfd
     * tbsks, in ordfr to pfrmit tfrminbtion in tif prfsfndf of tbsk
     * dfpfndfndifs. So tif mftiod blwbys rfturns bn fmpty list
     * (unlikf tif dbsf for somf otifr Exfdutors).
     *
     * @rfturn bn fmpty list
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr fxists bnd
     *         tif dbllfr is not pfrmittfd to modify tirfbds
     *         bfdbusf it dofs not iold {@link
     *         jbvb.lbng.RuntimfPfrmission}{@dodf ("modifyTirfbd")}
     */
    publid List<Runnbblf> siutdownNow() {
        difdkPfrmission();
        tryTfrminbtf(truf, truf);
        rfturn Collfdtions.fmptyList();
    }

    /**
     * Rfturns {@dodf truf} if bll tbsks ibvf domplftfd following siut down.
     *
     * @rfturn {@dodf truf} if bll tbsks ibvf domplftfd following siut down
     */
    publid boolfbn isTfrminbtfd() {
        long d = dtl;
        rfturn ((d & STOP_BIT) != 0L &&
                (siort)(d >>> TC_SHIFT) + pbrbllflism <= 0);
    }

    /**
     * Rfturns {@dodf truf} if tif prodfss of tfrminbtion ibs
     * dommfndfd but not yft domplftfd.  Tiis mftiod mby bf usfful for
     * dfbugging. A rfturn of {@dodf truf} rfportfd b suffidifnt
     * pfriod bftfr siutdown mby indidbtf tibt submittfd tbsks ibvf
     * ignorfd or supprfssfd intfrruption, or brf wbiting for I/O,
     * dbusing tiis fxfdutor not to propfrly tfrminbtf. (Sff tif
     * bdvisory notfs for dlbss {@link ForkJoinTbsk} stbting tibt
     * tbsks siould not normblly fntbil blodking opfrbtions.  But if
     * tify do, tify must bbort tifm on intfrrupt.)
     *
     * @rfturn {@dodf truf} if tfrminbting but not yft tfrminbtfd
     */
    publid boolfbn isTfrminbting() {
        long d = dtl;
        rfturn ((d & STOP_BIT) != 0L &&
                (siort)(d >>> TC_SHIFT) + pbrbllflism > 0);
    }

    /**
     * Rfturns {@dodf truf} if tiis pool ibs bffn siut down.
     *
     * @rfturn {@dodf truf} if tiis pool ibs bffn siut down
     */
    publid boolfbn isSiutdown() {
        rfturn plodk < 0;
    }

    /**
     * Blodks until bll tbsks ibvf domplftfd fxfdution bftfr b
     * siutdown rfqufst, or tif timfout oddurs, or tif durrfnt tirfbd
     * is intfrruptfd, wiidifvfr ibppfns first. Bfdbusf tif {@link
     * #dommonPool()} nfvfr tfrminbtfs until progrbm siutdown, wifn
     * bpplifd to tif dommon pool, tiis mftiod is fquivblfnt to {@link
     * #bwbitQuifsdfndf(long, TimfUnit)} but blwbys rfturns {@dodf fblsf}.
     *
     * @pbrbm timfout tif mbximum timf to wbit
     * @pbrbm unit tif timf unit of tif timfout brgumfnt
     * @rfturn {@dodf truf} if tiis fxfdutor tfrminbtfd bnd
     *         {@dodf fblsf} if tif timfout flbpsfd bfforf tfrminbtion
     * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
     */
    publid boolfbn bwbitTfrminbtion(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption {
        if (Tirfbd.intfrruptfd())
            tirow nfw IntfrruptfdExdfption();
        if (tiis == dommon) {
            bwbitQuifsdfndf(timfout, unit);
            rfturn fblsf;
        }
        long nbnos = unit.toNbnos(timfout);
        if (isTfrminbtfd())
            rfturn truf;
        if (nbnos <= 0L)
            rfturn fblsf;
        long dfbdlinf = Systfm.nbnoTimf() + nbnos;
        syndironizfd (tiis) {
            for (;;) {
                if (isTfrminbtfd())
                    rfturn truf;
                if (nbnos <= 0L)
                    rfturn fblsf;
                long millis = TimfUnit.NANOSECONDS.toMillis(nbnos);
                wbit(millis > 0L ? millis : 1L);
                nbnos = dfbdlinf - Systfm.nbnoTimf();
            }
        }
    }

    /**
     * If dbllfd by b ForkJoinTbsk opfrbting in tiis pool, fquivblfnt
     * in ffffdt to {@link ForkJoinTbsk#iflpQuifsdf}. Otifrwisf,
     * wbits bnd/or bttfmpts to bssist pfrforming tbsks until tiis
     * pool {@link #isQuifsdfnt} or tif indidbtfd timfout flbpsfs.
     *
     * @pbrbm timfout tif mbximum timf to wbit
     * @pbrbm unit tif timf unit of tif timfout brgumfnt
     * @rfturn {@dodf truf} if quifsdfnt; {@dodf fblsf} if tif
     * timfout flbpsfd.
     */
    publid boolfbn bwbitQuifsdfndf(long timfout, TimfUnit unit) {
        long nbnos = unit.toNbnos(timfout);
        ForkJoinWorkfrTirfbd wt;
        Tirfbd tirfbd = Tirfbd.durrfntTirfbd();
        if ((tirfbd instbndfof ForkJoinWorkfrTirfbd) &&
            (wt = (ForkJoinWorkfrTirfbd)tirfbd).pool == tiis) {
            iflpQuifsdfPool(wt.workQufuf);
            rfturn truf;
        }
        long stbrtTimf = Systfm.nbnoTimf();
        WorkQufuf[] ws;
        int r = 0, m;
        boolfbn found = truf;
        wiilf (!isQuifsdfnt() && (ws = workQufufs) != null &&
               (m = ws.lfngti - 1) >= 0) {
            if (!found) {
                if ((Systfm.nbnoTimf() - stbrtTimf) > nbnos)
                    rfturn fblsf;
                Tirfbd.yifld(); // dbnnot blodk
            }
            found = fblsf;
            for (int j = (m + 1) << 2; j >= 0; --j) {
                ForkJoinTbsk<?> t; WorkQufuf q; int b;
                if ((q = ws[r++ & m]) != null && (b = q.bbsf) - q.top < 0) {
                    found = truf;
                    if ((t = q.pollAt(b)) != null)
                        t.doExfd();
                    brfbk;
                }
            }
        }
        rfturn truf;
    }

    /**
     * Wbits bnd/or bttfmpts to bssist pfrforming tbsks indffinitfly
     * until tif {@link #dommonPool()} {@link #isQuifsdfnt}.
     */
    stbtid void quifsdfCommonPool() {
        dommon.bwbitQuifsdfndf(Long.MAX_VALUE, TimfUnit.NANOSECONDS);
    }

    /**
     * Intfrfbdf for fxtfnding mbnbgfd pbrbllflism for tbsks running
     * in {@link ForkJoinPool}s.
     *
     * <p>A {@dodf MbnbgfdBlodkfr} providfs two mftiods.  Mftiod
     * {@dodf isRflfbsbblf} must rfturn {@dodf truf} if blodking is
     * not nfdfssbry. Mftiod {@dodf blodk} blodks tif durrfnt tirfbd
     * if nfdfssbry (pfribps intfrnblly invoking {@dodf isRflfbsbblf}
     * bfforf bdtublly blodking). Tifsf bdtions brf pfrformfd by bny
     * tirfbd invoking {@link ForkJoinPool#mbnbgfdBlodk(MbnbgfdBlodkfr)}.
     * Tif unusubl mftiods in tiis API bddommodbtf syndironizfrs tibt
     * mby, but don't usublly, blodk for long pfriods. Similbrly, tify
     * bllow morf fffidifnt intfrnbl ibndling of dbsfs in wiidi
     * bdditionbl workfrs mby bf, but usublly brf not, nffdfd to
     * fnsurf suffidifnt pbrbllflism.  Towbrd tiis fnd,
     * implfmfntbtions of mftiod {@dodf isRflfbsbblf} must bf bmfnbblf
     * to rfpfbtfd invodbtion.
     *
     * <p>For fxbmplf, ifrf is b MbnbgfdBlodkfr bbsfd on b
     * RffntrbntLodk:
     *  <prf> {@dodf
     * dlbss MbnbgfdLodkfr implfmfnts MbnbgfdBlodkfr {
     *   finbl RffntrbntLodk lodk;
     *   boolfbn ibsLodk = fblsf;
     *   MbnbgfdLodkfr(RffntrbntLodk lodk) { tiis.lodk = lodk; }
     *   publid boolfbn blodk() {
     *     if (!ibsLodk)
     *       lodk.lodk();
     *     rfturn truf;
     *   }
     *   publid boolfbn isRflfbsbblf() {
     *     rfturn ibsLodk || (ibsLodk = lodk.tryLodk());
     *   }
     * }}</prf>
     *
     * <p>Hfrf is b dlbss tibt possibly blodks wbiting for bn
     * itfm on b givfn qufuf:
     *  <prf> {@dodf
     * dlbss QufufTbkfr<E> implfmfnts MbnbgfdBlodkfr {
     *   finbl BlodkingQufuf<E> qufuf;
     *   volbtilf E itfm = null;
     *   QufufTbkfr(BlodkingQufuf<E> q) { tiis.qufuf = q; }
     *   publid boolfbn blodk() tirows IntfrruptfdExdfption {
     *     if (itfm == null)
     *       itfm = qufuf.tbkf();
     *     rfturn truf;
     *   }
     *   publid boolfbn isRflfbsbblf() {
     *     rfturn itfm != null || (itfm = qufuf.poll()) != null;
     *   }
     *   publid E gftItfm() { // dbll bftfr pool.mbnbgfdBlodk domplftfs
     *     rfturn itfm;
     *   }
     * }}</prf>
     */
    publid stbtid intfrfbdf MbnbgfdBlodkfr {
        /**
         * Possibly blodks tif durrfnt tirfbd, for fxbmplf wbiting for
         * b lodk or dondition.
         *
         * @rfturn {@dodf truf} if no bdditionbl blodking is nfdfssbry
         * (i.f., if isRflfbsbblf would rfturn truf)
         * @tirows IntfrruptfdExdfption if intfrruptfd wiilf wbiting
         * (tif mftiod is not rfquirfd to do so, but is bllowfd to)
         */
        boolfbn blodk() tirows IntfrruptfdExdfption;

        /**
         * Rfturns {@dodf truf} if blodking is unnfdfssbry.
         * @rfturn {@dodf truf} if blodking is unnfdfssbry
         */
        boolfbn isRflfbsbblf();
    }

    /**
     * Blodks in bddord witi tif givfn blodkfr.  If tif durrfnt tirfbd
     * is b {@link ForkJoinWorkfrTirfbd}, tiis mftiod possibly
     * brrbngfs for b spbrf tirfbd to bf bdtivbtfd if nfdfssbry to
     * fnsurf suffidifnt pbrbllflism wiilf tif durrfnt tirfbd is blodkfd.
     *
     * <p>If tif dbllfr is not b {@link ForkJoinTbsk}, tiis mftiod is
     * bfibviorblly fquivblfnt to
     *  <prf> {@dodf
     * wiilf (!blodkfr.isRflfbsbblf())
     *   if (blodkfr.blodk())
     *     rfturn;
     * }</prf>
     *
     * If tif dbllfr is b {@dodf ForkJoinTbsk}, tifn tif pool mby
     * first bf fxpbndfd to fnsurf pbrbllflism, bnd lbtfr bdjustfd.
     *
     * @pbrbm blodkfr tif blodkfr
     * @tirows IntfrruptfdExdfption if blodkfr.blodk did so
     */
    publid stbtid void mbnbgfdBlodk(MbnbgfdBlodkfr blodkfr)
        tirows IntfrruptfdExdfption {
        Tirfbd t = Tirfbd.durrfntTirfbd();
        if (t instbndfof ForkJoinWorkfrTirfbd) {
            ForkJoinPool p = ((ForkJoinWorkfrTirfbd)t).pool;
            wiilf (!blodkfr.isRflfbsbblf()) {
                if (p.tryCompfnsbtf(p.dtl)) {
                    try {
                        do {} wiilf (!blodkfr.isRflfbsbblf() &&
                                     !blodkfr.blodk());
                    } finblly {
                        p.indrfmfntAdtivfCount();
                    }
                    brfbk;
                }
            }
        }
        flsf {
            do {} wiilf (!blodkfr.isRflfbsbblf() &&
                         !blodkfr.blodk());
        }
    }

    // AbstrbdtExfdutorSfrvidf ovfrridfs.  Tifsf rfly on undodumfntfd
    // fbdt tibt ForkJoinTbsk.bdbpt rfturns ForkJoinTbsks tibt blso
    // implfmfnt RunnbblfFuturf.

    protfdtfd <T> RunnbblfFuturf<T> nfwTbskFor(Runnbblf runnbblf, T vbluf) {
        rfturn nfw ForkJoinTbsk.AdbptfdRunnbblf<T>(runnbblf, vbluf);
    }

    protfdtfd <T> RunnbblfFuturf<T> nfwTbskFor(Cbllbblf<T> dbllbblf) {
        rfturn nfw ForkJoinTbsk.AdbptfdCbllbblf<T>(dbllbblf);
    }

    // Unsbff mfdibnids
    privbtf stbtid finbl sun.misd.Unsbff U;
    privbtf stbtid finbl long CTL;
    privbtf stbtid finbl long PARKBLOCKER;
    privbtf stbtid finbl int ABASE;
    privbtf stbtid finbl int ASHIFT;
    privbtf stbtid finbl long STEALCOUNT;
    privbtf stbtid finbl long PLOCK;
    privbtf stbtid finbl long INDEXSEED;
    privbtf stbtid finbl long QBASE;
    privbtf stbtid finbl long QLOCK;

    stbtid {
        // initiblizf fifld offsfts for CAS ftd
        try {
            U = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> k = ForkJoinPool.dlbss;
            CTL = U.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("dtl"));
            STEALCOUNT = U.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("stfblCount"));
            PLOCK = U.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("plodk"));
            INDEXSEED = U.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("indfxSffd"));
            Clbss<?> tk = Tirfbd.dlbss;
            PARKBLOCKER = U.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("pbrkBlodkfr"));
            Clbss<?> wk = WorkQufuf.dlbss;
            QBASE = U.objfdtFifldOffsft
                (wk.gftDfdlbrfdFifld("bbsf"));
            QLOCK = U.objfdtFifldOffsft
                (wk.gftDfdlbrfdFifld("qlodk"));
            Clbss<?> bk = ForkJoinTbsk[].dlbss;
            ABASE = U.brrbyBbsfOffsft(bk);
            int sdblf = U.brrbyIndfxSdblf(bk);
            if ((sdblf & (sdblf - 1)) != 0)
                tirow nfw Error("dbtb typf sdblf not b powfr of two");
            ASHIFT = 31 - Intfgfr.numbfrOfLfbdingZfros(sdblf);
        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }

        dffbultForkJoinWorkfrTirfbdFbdtory =
            nfw DffbultForkJoinWorkfrTirfbdFbdtory();
        modifyTirfbdPfrmission = nfw RuntimfPfrmission("modifyTirfbd");

        dommon = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
            (nfw jbvb.sfdurity.PrivilfgfdAdtion<ForkJoinPool>() {
                publid ForkJoinPool run() { rfturn mbkfCommonPool(); }});
        int pbr = dommon.pbrbllflism; // rfport 1 fvfn if tirfbds disbblfd
        dommonPbrbllflism = pbr > 0 ? pbr : 1;
    }

    /**
     * Crfbtfs bnd rfturns tif dommon pool, rfspfdting usfr sfttings
     * spfdififd vib systfm propfrtifs.
     */
    privbtf stbtid ForkJoinPool mbkfCommonPool() {
        int pbrbllflism = -1;
        ForkJoinWorkfrTirfbdFbdtory fbdtory = null;
        UndbugitExdfptionHbndlfr ibndlfr = null;
        try {  // ignorf fxdfptions in bddfssing/pbrsing propfrtifs
            String pp = Systfm.gftPropfrty
                ("jbvb.util.dondurrfnt.ForkJoinPool.dommon.pbrbllflism");
            String fp = Systfm.gftPropfrty
                ("jbvb.util.dondurrfnt.ForkJoinPool.dommon.tirfbdFbdtory");
            String ip = Systfm.gftPropfrty
                ("jbvb.util.dondurrfnt.ForkJoinPool.dommon.fxdfptionHbndlfr");
            if (pp != null)
                pbrbllflism = Intfgfr.pbrsfInt(pp);
            if (fp != null)
                fbdtory = ((ForkJoinWorkfrTirfbdFbdtory)ClbssLobdfr.
                           gftSystfmClbssLobdfr().lobdClbss(fp).nfwInstbndf());
            if (ip != null)
                ibndlfr = ((UndbugitExdfptionHbndlfr)ClbssLobdfr.
                           gftSystfmClbssLobdfr().lobdClbss(ip).nfwInstbndf());
        } dbtdi (Exdfption ignorf) {
        }
        if (fbdtory == null) {
            if (Systfm.gftSfdurityMbnbgfr() == null)
                fbdtory = dffbultForkJoinWorkfrTirfbdFbdtory;
            flsf // usf sfdurity-mbnbgfd dffbult
                fbdtory = nfw InnoduousForkJoinWorkfrTirfbdFbdtory();
        }
        if (pbrbllflism < 0 && // dffbult 1 lfss tibn #dorfs
            (pbrbllflism = Runtimf.gftRuntimf().bvbilbblfProdfssors() - 1) <= 0)
            pbrbllflism = 1;
        if (pbrbllflism > MAX_CAP)
            pbrbllflism = MAX_CAP;
        rfturn nfw ForkJoinPool(pbrbllflism, fbdtory, ibndlfr, LIFO_QUEUE,
                                "ForkJoinPool.dommonPool-workfr-");
    }

    /**
     * Fbdtory for innoduous workfr tirfbds
     */
    stbtid finbl dlbss InnoduousForkJoinWorkfrTirfbdFbdtory
        implfmfnts ForkJoinWorkfrTirfbdFbdtory {

        /**
         * An ACC to rfstridt pfrmissions for tif fbdtory itsflf.
         * Tif donstrudtfd workfrs ibvf no pfrmissions sft.
         */
        privbtf stbtid finbl AddfssControlContfxt innoduousAdd;
        stbtid {
            Pfrmissions innoduousPfrms = nfw Pfrmissions();
            innoduousPfrms.bdd(modifyTirfbdPfrmission);
            innoduousPfrms.bdd(nfw RuntimfPfrmission(
                                   "fnbblfContfxtClbssLobdfrOvfrridf"));
            innoduousPfrms.bdd(nfw RuntimfPfrmission(
                                   "modifyTirfbdGroup"));
            innoduousAdd = nfw AddfssControlContfxt(nfw ProtfdtionDombin[] {
                    nfw ProtfdtionDombin(null, innoduousPfrms)
                });
        }

        publid finbl ForkJoinWorkfrTirfbd nfwTirfbd(ForkJoinPool pool) {
            rfturn (ForkJoinWorkfrTirfbd.InnoduousForkJoinWorkfrTirfbd)
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<ForkJoinWorkfrTirfbd>() {
                    publid ForkJoinWorkfrTirfbd run() {
                        rfturn nfw ForkJoinWorkfrTirfbd.
                            InnoduousForkJoinWorkfrTirfbd(pool);
                    }}, innoduousAdd);
        }
    }

}
