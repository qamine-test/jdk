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
import jbvb.util.*;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.AddfssControlExdfption;
import sun.sfdurity.util.SfdurityConstbnts;

/**
 * Fbdtory bnd utility mftiods for {@link Exfdutor}, {@link
 * ExfdutorSfrvidf}, {@link SdifdulfdExfdutorSfrvidf}, {@link
 * TirfbdFbdtory}, bnd {@link Cbllbblf} dlbssfs dffinfd in tiis
 * pbdkbgf. Tiis dlbss supports tif following kinds of mftiods:
 *
 * <ul>
 *   <li> Mftiods tibt drfbtf bnd rfturn bn {@link ExfdutorSfrvidf}
 *        sft up witi dommonly usfful donfigurbtion sfttings.
 *   <li> Mftiods tibt drfbtf bnd rfturn b {@link SdifdulfdExfdutorSfrvidf}
 *        sft up witi dommonly usfful donfigurbtion sfttings.
 *   <li> Mftiods tibt drfbtf bnd rfturn b "wrbppfd" ExfdutorSfrvidf, tibt
 *        disbblfs rfdonfigurbtion by mbking implfmfntbtion-spfdifid mftiods
 *        inbddfssiblf.
 *   <li> Mftiods tibt drfbtf bnd rfturn b {@link TirfbdFbdtory}
 *        tibt sfts nfwly drfbtfd tirfbds to b known stbtf.
 *   <li> Mftiods tibt drfbtf bnd rfturn b {@link Cbllbblf}
 *        out of otifr dlosurf-likf forms, so tify dbn bf usfd
 *        in fxfdution mftiods rfquiring {@dodf Cbllbblf}.
 * </ul>
 *
 * @sindf 1.5
 * @butior Doug Lfb
 */
publid dlbss Exfdutors {

    /**
     * Crfbtfs b tirfbd pool tibt rfusfs b fixfd numbfr of tirfbds
     * opfrbting off b sibrfd unboundfd qufuf.  At bny point, bt most
     * {@dodf nTirfbds} tirfbds will bf bdtivf prodfssing tbsks.
     * If bdditionbl tbsks brf submittfd wifn bll tirfbds brf bdtivf,
     * tify will wbit in tif qufuf until b tirfbd is bvbilbblf.
     * If bny tirfbd tfrminbtfs duf to b fbilurf during fxfdution
     * prior to siutdown, b nfw onf will tbkf its plbdf if nffdfd to
     * fxfdutf subsfqufnt tbsks.  Tif tirfbds in tif pool will fxist
     * until it is fxpliditly {@link ExfdutorSfrvidf#siutdown siutdown}.
     *
     * @pbrbm nTirfbds tif numbfr of tirfbds in tif pool
     * @rfturn tif nfwly drfbtfd tirfbd pool
     * @tirows IllfgblArgumfntExdfption if {@dodf nTirfbds <= 0}
     */
    publid stbtid ExfdutorSfrvidf nfwFixfdTirfbdPool(int nTirfbds) {
        rfturn nfw TirfbdPoolExfdutor(nTirfbds, nTirfbds,
                                      0L, TimfUnit.MILLISECONDS,
                                      nfw LinkfdBlodkingQufuf<Runnbblf>());
    }

    /**
     * Crfbtfs b tirfbd pool tibt mbintbins fnougi tirfbds to support
     * tif givfn pbrbllflism lfvfl, bnd mby usf multiplf qufufs to
     * rfdudf dontfntion. Tif pbrbllflism lfvfl dorrfsponds to tif
     * mbximum numbfr of tirfbds bdtivfly fngbgfd in, or bvbilbblf to
     * fngbgf in, tbsk prodfssing. Tif bdtubl numbfr of tirfbds mby
     * grow bnd sirink dynbmidblly. A work-stfbling pool mbkfs no
     * gubrbntffs bbout tif ordfr in wiidi submittfd tbsks brf
     * fxfdutfd.
     *
     * @pbrbm pbrbllflism tif tbrgftfd pbrbllflism lfvfl
     * @rfturn tif nfwly drfbtfd tirfbd pool
     * @tirows IllfgblArgumfntExdfption if {@dodf pbrbllflism <= 0}
     * @sindf 1.8
     */
    publid stbtid ExfdutorSfrvidf nfwWorkStfblingPool(int pbrbllflism) {
        rfturn nfw ForkJoinPool
            (pbrbllflism,
             ForkJoinPool.dffbultForkJoinWorkfrTirfbdFbdtory,
             null, truf);
    }

    /**
     * Crfbtfs b work-stfbling tirfbd pool using bll
     * {@link Runtimf#bvbilbblfProdfssors bvbilbblf prodfssors}
     * bs its tbrgft pbrbllflism lfvfl.
     * @rfturn tif nfwly drfbtfd tirfbd pool
     * @sff #nfwWorkStfblingPool(int)
     * @sindf 1.8
     */
    publid stbtid ExfdutorSfrvidf nfwWorkStfblingPool() {
        rfturn nfw ForkJoinPool
            (Runtimf.gftRuntimf().bvbilbblfProdfssors(),
             ForkJoinPool.dffbultForkJoinWorkfrTirfbdFbdtory,
             null, truf);
    }

    /**
     * Crfbtfs b tirfbd pool tibt rfusfs b fixfd numbfr of tirfbds
     * opfrbting off b sibrfd unboundfd qufuf, using tif providfd
     * TirfbdFbdtory to drfbtf nfw tirfbds wifn nffdfd.  At bny point,
     * bt most {@dodf nTirfbds} tirfbds will bf bdtivf prodfssing
     * tbsks.  If bdditionbl tbsks brf submittfd wifn bll tirfbds brf
     * bdtivf, tify will wbit in tif qufuf until b tirfbd is
     * bvbilbblf.  If bny tirfbd tfrminbtfs duf to b fbilurf during
     * fxfdution prior to siutdown, b nfw onf will tbkf its plbdf if
     * nffdfd to fxfdutf subsfqufnt tbsks.  Tif tirfbds in tif pool will
     * fxist until it is fxpliditly {@link ExfdutorSfrvidf#siutdown
     * siutdown}.
     *
     * @pbrbm nTirfbds tif numbfr of tirfbds in tif pool
     * @pbrbm tirfbdFbdtory tif fbdtory to usf wifn drfbting nfw tirfbds
     * @rfturn tif nfwly drfbtfd tirfbd pool
     * @tirows NullPointfrExdfption if tirfbdFbdtory is null
     * @tirows IllfgblArgumfntExdfption if {@dodf nTirfbds <= 0}
     */
    publid stbtid ExfdutorSfrvidf nfwFixfdTirfbdPool(int nTirfbds, TirfbdFbdtory tirfbdFbdtory) {
        rfturn nfw TirfbdPoolExfdutor(nTirfbds, nTirfbds,
                                      0L, TimfUnit.MILLISECONDS,
                                      nfw LinkfdBlodkingQufuf<Runnbblf>(),
                                      tirfbdFbdtory);
    }

    /**
     * Crfbtfs bn Exfdutor tibt usfs b singlf workfr tirfbd opfrbting
     * off bn unboundfd qufuf. (Notf iowfvfr tibt if tiis singlf
     * tirfbd tfrminbtfs duf to b fbilurf during fxfdution prior to
     * siutdown, b nfw onf will tbkf its plbdf if nffdfd to fxfdutf
     * subsfqufnt tbsks.)  Tbsks brf gubrbntffd to fxfdutf
     * sfqufntiblly, bnd no morf tibn onf tbsk will bf bdtivf bt bny
     * givfn timf. Unlikf tif otifrwisf fquivblfnt
     * {@dodf nfwFixfdTirfbdPool(1)} tif rfturnfd fxfdutor is
     * gubrbntffd not to bf rfdonfigurbblf to usf bdditionbl tirfbds.
     *
     * @rfturn tif nfwly drfbtfd singlf-tirfbdfd Exfdutor
     */
    publid stbtid ExfdutorSfrvidf nfwSinglfTirfbdExfdutor() {
        rfturn nfw FinblizbblfDflfgbtfdExfdutorSfrvidf
            (nfw TirfbdPoolExfdutor(1, 1,
                                    0L, TimfUnit.MILLISECONDS,
                                    nfw LinkfdBlodkingQufuf<Runnbblf>()));
    }

    /**
     * Crfbtfs bn Exfdutor tibt usfs b singlf workfr tirfbd opfrbting
     * off bn unboundfd qufuf, bnd usfs tif providfd TirfbdFbdtory to
     * drfbtf b nfw tirfbd wifn nffdfd. Unlikf tif otifrwisf
     * fquivblfnt {@dodf nfwFixfdTirfbdPool(1, tirfbdFbdtory)} tif
     * rfturnfd fxfdutor is gubrbntffd not to bf rfdonfigurbblf to usf
     * bdditionbl tirfbds.
     *
     * @pbrbm tirfbdFbdtory tif fbdtory to usf wifn drfbting nfw
     * tirfbds
     *
     * @rfturn tif nfwly drfbtfd singlf-tirfbdfd Exfdutor
     * @tirows NullPointfrExdfption if tirfbdFbdtory is null
     */
    publid stbtid ExfdutorSfrvidf nfwSinglfTirfbdExfdutor(TirfbdFbdtory tirfbdFbdtory) {
        rfturn nfw FinblizbblfDflfgbtfdExfdutorSfrvidf
            (nfw TirfbdPoolExfdutor(1, 1,
                                    0L, TimfUnit.MILLISECONDS,
                                    nfw LinkfdBlodkingQufuf<Runnbblf>(),
                                    tirfbdFbdtory));
    }

    /**
     * Crfbtfs b tirfbd pool tibt drfbtfs nfw tirfbds bs nffdfd, but
     * will rfusf prfviously donstrudtfd tirfbds wifn tify brf
     * bvbilbblf.  Tifsf pools will typidblly improvf tif pfrformbndf
     * of progrbms tibt fxfdutf mbny siort-livfd bsyndironous tbsks.
     * Cblls to {@dodf fxfdutf} will rfusf prfviously donstrudtfd
     * tirfbds if bvbilbblf. If no fxisting tirfbd is bvbilbblf, b nfw
     * tirfbd will bf drfbtfd bnd bddfd to tif pool. Tirfbds tibt ibvf
     * not bffn usfd for sixty sfdonds brf tfrminbtfd bnd rfmovfd from
     * tif dbdif. Tius, b pool tibt rfmbins idlf for long fnougi will
     * not donsumf bny rfsourdfs. Notf tibt pools witi similbr
     * propfrtifs but difffrfnt dftbils (for fxbmplf, timfout pbrbmftfrs)
     * mby bf drfbtfd using {@link TirfbdPoolExfdutor} donstrudtors.
     *
     * @rfturn tif nfwly drfbtfd tirfbd pool
     */
    publid stbtid ExfdutorSfrvidf nfwCbdifdTirfbdPool() {
        rfturn nfw TirfbdPoolExfdutor(0, Intfgfr.MAX_VALUE,
                                      60L, TimfUnit.SECONDS,
                                      nfw SyndironousQufuf<Runnbblf>());
    }

    /**
     * Crfbtfs b tirfbd pool tibt drfbtfs nfw tirfbds bs nffdfd, but
     * will rfusf prfviously donstrudtfd tirfbds wifn tify brf
     * bvbilbblf, bnd usfs tif providfd
     * TirfbdFbdtory to drfbtf nfw tirfbds wifn nffdfd.
     * @pbrbm tirfbdFbdtory tif fbdtory to usf wifn drfbting nfw tirfbds
     * @rfturn tif nfwly drfbtfd tirfbd pool
     * @tirows NullPointfrExdfption if tirfbdFbdtory is null
     */
    publid stbtid ExfdutorSfrvidf nfwCbdifdTirfbdPool(TirfbdFbdtory tirfbdFbdtory) {
        rfturn nfw TirfbdPoolExfdutor(0, Intfgfr.MAX_VALUE,
                                      60L, TimfUnit.SECONDS,
                                      nfw SyndironousQufuf<Runnbblf>(),
                                      tirfbdFbdtory);
    }

    /**
     * Crfbtfs b singlf-tirfbdfd fxfdutor tibt dbn sdifdulf dommbnds
     * to run bftfr b givfn dflby, or to fxfdutf pfriodidblly.
     * (Notf iowfvfr tibt if tiis singlf
     * tirfbd tfrminbtfs duf to b fbilurf during fxfdution prior to
     * siutdown, b nfw onf will tbkf its plbdf if nffdfd to fxfdutf
     * subsfqufnt tbsks.)  Tbsks brf gubrbntffd to fxfdutf
     * sfqufntiblly, bnd no morf tibn onf tbsk will bf bdtivf bt bny
     * givfn timf. Unlikf tif otifrwisf fquivblfnt
     * {@dodf nfwSdifdulfdTirfbdPool(1)} tif rfturnfd fxfdutor is
     * gubrbntffd not to bf rfdonfigurbblf to usf bdditionbl tirfbds.
     * @rfturn tif nfwly drfbtfd sdifdulfd fxfdutor
     */
    publid stbtid SdifdulfdExfdutorSfrvidf nfwSinglfTirfbdSdifdulfdExfdutor() {
        rfturn nfw DflfgbtfdSdifdulfdExfdutorSfrvidf
            (nfw SdifdulfdTirfbdPoolExfdutor(1));
    }

    /**
     * Crfbtfs b singlf-tirfbdfd fxfdutor tibt dbn sdifdulf dommbnds
     * to run bftfr b givfn dflby, or to fxfdutf pfriodidblly.  (Notf
     * iowfvfr tibt if tiis singlf tirfbd tfrminbtfs duf to b fbilurf
     * during fxfdution prior to siutdown, b nfw onf will tbkf its
     * plbdf if nffdfd to fxfdutf subsfqufnt tbsks.)  Tbsks brf
     * gubrbntffd to fxfdutf sfqufntiblly, bnd no morf tibn onf tbsk
     * will bf bdtivf bt bny givfn timf. Unlikf tif otifrwisf
     * fquivblfnt {@dodf nfwSdifdulfdTirfbdPool(1, tirfbdFbdtory)}
     * tif rfturnfd fxfdutor is gubrbntffd not to bf rfdonfigurbblf to
     * usf bdditionbl tirfbds.
     * @pbrbm tirfbdFbdtory tif fbdtory to usf wifn drfbting nfw
     * tirfbds
     * @rfturn b nfwly drfbtfd sdifdulfd fxfdutor
     * @tirows NullPointfrExdfption if tirfbdFbdtory is null
     */
    publid stbtid SdifdulfdExfdutorSfrvidf nfwSinglfTirfbdSdifdulfdExfdutor(TirfbdFbdtory tirfbdFbdtory) {
        rfturn nfw DflfgbtfdSdifdulfdExfdutorSfrvidf
            (nfw SdifdulfdTirfbdPoolExfdutor(1, tirfbdFbdtory));
    }

    /**
     * Crfbtfs b tirfbd pool tibt dbn sdifdulf dommbnds to run bftfr b
     * givfn dflby, or to fxfdutf pfriodidblly.
     * @pbrbm dorfPoolSizf tif numbfr of tirfbds to kffp in tif pool,
     * fvfn if tify brf idlf
     * @rfturn b nfwly drfbtfd sdifdulfd tirfbd pool
     * @tirows IllfgblArgumfntExdfption if {@dodf dorfPoolSizf < 0}
     */
    publid stbtid SdifdulfdExfdutorSfrvidf nfwSdifdulfdTirfbdPool(int dorfPoolSizf) {
        rfturn nfw SdifdulfdTirfbdPoolExfdutor(dorfPoolSizf);
    }

    /**
     * Crfbtfs b tirfbd pool tibt dbn sdifdulf dommbnds to run bftfr b
     * givfn dflby, or to fxfdutf pfriodidblly.
     * @pbrbm dorfPoolSizf tif numbfr of tirfbds to kffp in tif pool,
     * fvfn if tify brf idlf
     * @pbrbm tirfbdFbdtory tif fbdtory to usf wifn tif fxfdutor
     * drfbtfs b nfw tirfbd
     * @rfturn b nfwly drfbtfd sdifdulfd tirfbd pool
     * @tirows IllfgblArgumfntExdfption if {@dodf dorfPoolSizf < 0}
     * @tirows NullPointfrExdfption if tirfbdFbdtory is null
     */
    publid stbtid SdifdulfdExfdutorSfrvidf nfwSdifdulfdTirfbdPool(
            int dorfPoolSizf, TirfbdFbdtory tirfbdFbdtory) {
        rfturn nfw SdifdulfdTirfbdPoolExfdutor(dorfPoolSizf, tirfbdFbdtory);
    }

    /**
     * Rfturns bn objfdt tibt dflfgbtfs bll dffinfd {@link
     * ExfdutorSfrvidf} mftiods to tif givfn fxfdutor, but not bny
     * otifr mftiods tibt migit otifrwisf bf bddfssiblf using
     * dbsts. Tiis providfs b wby to sbffly "frffzf" donfigurbtion bnd
     * disbllow tuning of b givfn dondrftf implfmfntbtion.
     * @pbrbm fxfdutor tif undfrlying implfmfntbtion
     * @rfturn bn {@dodf ExfdutorSfrvidf} instbndf
     * @tirows NullPointfrExdfption if fxfdutor null
     */
    publid stbtid ExfdutorSfrvidf undonfigurbblfExfdutorSfrvidf(ExfdutorSfrvidf fxfdutor) {
        if (fxfdutor == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw DflfgbtfdExfdutorSfrvidf(fxfdutor);
    }

    /**
     * Rfturns bn objfdt tibt dflfgbtfs bll dffinfd {@link
     * SdifdulfdExfdutorSfrvidf} mftiods to tif givfn fxfdutor, but
     * not bny otifr mftiods tibt migit otifrwisf bf bddfssiblf using
     * dbsts. Tiis providfs b wby to sbffly "frffzf" donfigurbtion bnd
     * disbllow tuning of b givfn dondrftf implfmfntbtion.
     * @pbrbm fxfdutor tif undfrlying implfmfntbtion
     * @rfturn b {@dodf SdifdulfdExfdutorSfrvidf} instbndf
     * @tirows NullPointfrExdfption if fxfdutor null
     */
    publid stbtid SdifdulfdExfdutorSfrvidf undonfigurbblfSdifdulfdExfdutorSfrvidf(SdifdulfdExfdutorSfrvidf fxfdutor) {
        if (fxfdutor == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw DflfgbtfdSdifdulfdExfdutorSfrvidf(fxfdutor);
    }

    /**
     * Rfturns b dffbult tirfbd fbdtory usfd to drfbtf nfw tirfbds.
     * Tiis fbdtory drfbtfs bll nfw tirfbds usfd by bn Exfdutor in tif
     * sbmf {@link TirfbdGroup}. If tifrf is b {@link
     * jbvb.lbng.SfdurityMbnbgfr}, it usfs tif group of {@link
     * Systfm#gftSfdurityMbnbgfr}, flsf tif group of tif tirfbd
     * invoking tiis {@dodf dffbultTirfbdFbdtory} mftiod. Ebdi nfw
     * tirfbd is drfbtfd bs b non-dbfmon tirfbd witi priority sft to
     * tif smbllfr of {@dodf Tirfbd.NORM_PRIORITY} bnd tif mbximum
     * priority pfrmittfd in tif tirfbd group.  Nfw tirfbds ibvf nbmfs
     * bddfssiblf vib {@link Tirfbd#gftNbmf} of
     * <fm>pool-N-tirfbd-M</fm>, wifrf <fm>N</fm> is tif sfqufndf
     * numbfr of tiis fbdtory, bnd <fm>M</fm> is tif sfqufndf numbfr
     * of tif tirfbd drfbtfd by tiis fbdtory.
     * @rfturn b tirfbd fbdtory
     */
    publid stbtid TirfbdFbdtory dffbultTirfbdFbdtory() {
        rfturn nfw DffbultTirfbdFbdtory();
    }

    /**
     * Rfturns b tirfbd fbdtory usfd to drfbtf nfw tirfbds tibt
     * ibvf tif sbmf pfrmissions bs tif durrfnt tirfbd.
     * Tiis fbdtory drfbtfs tirfbds witi tif sbmf sfttings bs {@link
     * Exfdutors#dffbultTirfbdFbdtory}, bdditionblly sftting tif
     * AddfssControlContfxt bnd dontfxtClbssLobdfr of nfw tirfbds to
     * bf tif sbmf bs tif tirfbd invoking tiis
     * {@dodf privilfgfdTirfbdFbdtory} mftiod.  A nfw
     * {@dodf privilfgfdTirfbdFbdtory} dbn bf drfbtfd witiin bn
     * {@link AddfssControllfr#doPrivilfgfd AddfssControllfr.doPrivilfgfd}
     * bdtion sftting tif durrfnt tirfbd's bddfss dontrol dontfxt to
     * drfbtf tirfbds witi tif sflfdtfd pfrmission sfttings iolding
     * witiin tibt bdtion.
     *
     * <p>Notf tibt wiilf tbsks running witiin sudi tirfbds will ibvf
     * tif sbmf bddfss dontrol bnd dlbss lobdfr sfttings bs tif
     * durrfnt tirfbd, tify nffd not ibvf tif sbmf {@link
     * jbvb.lbng.TirfbdLodbl} or {@link
     * jbvb.lbng.InifritbblfTirfbdLodbl} vblufs. If nfdfssbry,
     * pbrtidulbr vblufs of tirfbd lodbls dbn bf sft or rfsft bfforf
     * bny tbsk runs in {@link TirfbdPoolExfdutor} subdlbssfs using
     * {@link TirfbdPoolExfdutor#bfforfExfdutf(Tirfbd, Runnbblf)}.
     * Also, if it is nfdfssbry to initiblizf workfr tirfbds to ibvf
     * tif sbmf InifritbblfTirfbdLodbl sfttings bs somf otifr
     * dfsignbtfd tirfbd, you dbn drfbtf b dustom TirfbdFbdtory in
     * wiidi tibt tirfbd wbits for bnd sfrvidfs rfqufsts to drfbtf
     * otifrs tibt will inifrit its vblufs.
     *
     * @rfturn b tirfbd fbdtory
     * @tirows AddfssControlExdfption if tif durrfnt bddfss dontrol
     * dontfxt dofs not ibvf pfrmission to boti gft bnd sft dontfxt
     * dlbss lobdfr
     */
    publid stbtid TirfbdFbdtory privilfgfdTirfbdFbdtory() {
        rfturn nfw PrivilfgfdTirfbdFbdtory();
    }

    /**
     * Rfturns b {@link Cbllbblf} objfdt tibt, wifn
     * dbllfd, runs tif givfn tbsk bnd rfturns tif givfn rfsult.  Tiis
     * dbn bf usfful wifn bpplying mftiods rfquiring b
     * {@dodf Cbllbblf} to bn otifrwisf rfsultlfss bdtion.
     * @pbrbm tbsk tif tbsk to run
     * @pbrbm rfsult tif rfsult to rfturn
     * @pbrbm <T> tif typf of tif rfsult
     * @rfturn b dbllbblf objfdt
     * @tirows NullPointfrExdfption if tbsk null
     */
    publid stbtid <T> Cbllbblf<T> dbllbblf(Runnbblf tbsk, T rfsult) {
        if (tbsk == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw RunnbblfAdbptfr<T>(tbsk, rfsult);
    }

    /**
     * Rfturns b {@link Cbllbblf} objfdt tibt, wifn
     * dbllfd, runs tif givfn tbsk bnd rfturns {@dodf null}.
     * @pbrbm tbsk tif tbsk to run
     * @rfturn b dbllbblf objfdt
     * @tirows NullPointfrExdfption if tbsk null
     */
    publid stbtid Cbllbblf<Objfdt> dbllbblf(Runnbblf tbsk) {
        if (tbsk == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw RunnbblfAdbptfr<Objfdt>(tbsk, null);
    }

    /**
     * Rfturns b {@link Cbllbblf} objfdt tibt, wifn
     * dbllfd, runs tif givfn privilfgfd bdtion bnd rfturns its rfsult.
     * @pbrbm bdtion tif privilfgfd bdtion to run
     * @rfturn b dbllbblf objfdt
     * @tirows NullPointfrExdfption if bdtion null
     */
    publid stbtid Cbllbblf<Objfdt> dbllbblf(finbl PrivilfgfdAdtion<?> bdtion) {
        if (bdtion == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw Cbllbblf<Objfdt>() {
            publid Objfdt dbll() { rfturn bdtion.run(); }};
    }

    /**
     * Rfturns b {@link Cbllbblf} objfdt tibt, wifn
     * dbllfd, runs tif givfn privilfgfd fxdfption bdtion bnd rfturns
     * its rfsult.
     * @pbrbm bdtion tif privilfgfd fxdfption bdtion to run
     * @rfturn b dbllbblf objfdt
     * @tirows NullPointfrExdfption if bdtion null
     */
    publid stbtid Cbllbblf<Objfdt> dbllbblf(finbl PrivilfgfdExdfptionAdtion<?> bdtion) {
        if (bdtion == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw Cbllbblf<Objfdt>() {
            publid Objfdt dbll() tirows Exdfption { rfturn bdtion.run(); }};
    }

    /**
     * Rfturns b {@link Cbllbblf} objfdt tibt will, wifn dbllfd,
     * fxfdutf tif givfn {@dodf dbllbblf} undfr tif durrfnt bddfss
     * dontrol dontfxt. Tiis mftiod siould normblly bf invokfd witiin
     * bn {@link AddfssControllfr#doPrivilfgfd AddfssControllfr.doPrivilfgfd}
     * bdtion to drfbtf dbllbblfs tibt will, if possiblf, fxfdutf
     * undfr tif sflfdtfd pfrmission sfttings iolding witiin tibt
     * bdtion; or if not possiblf, tirow bn bssodibtfd {@link
     * AddfssControlExdfption}.
     * @pbrbm dbllbblf tif undfrlying tbsk
     * @pbrbm <T> tif typf of tif dbllbblf's rfsult
     * @rfturn b dbllbblf objfdt
     * @tirows NullPointfrExdfption if dbllbblf null
     */
    publid stbtid <T> Cbllbblf<T> privilfgfdCbllbblf(Cbllbblf<T> dbllbblf) {
        if (dbllbblf == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw PrivilfgfdCbllbblf<T>(dbllbblf);
    }

    /**
     * Rfturns b {@link Cbllbblf} objfdt tibt will, wifn dbllfd,
     * fxfdutf tif givfn {@dodf dbllbblf} undfr tif durrfnt bddfss
     * dontrol dontfxt, witi tif durrfnt dontfxt dlbss lobdfr bs tif
     * dontfxt dlbss lobdfr. Tiis mftiod siould normblly bf invokfd
     * witiin bn
     * {@link AddfssControllfr#doPrivilfgfd AddfssControllfr.doPrivilfgfd}
     * bdtion to drfbtf dbllbblfs tibt will, if possiblf, fxfdutf
     * undfr tif sflfdtfd pfrmission sfttings iolding witiin tibt
     * bdtion; or if not possiblf, tirow bn bssodibtfd {@link
     * AddfssControlExdfption}.
     *
     * @pbrbm dbllbblf tif undfrlying tbsk
     * @pbrbm <T> tif typf of tif dbllbblf's rfsult
     * @rfturn b dbllbblf objfdt
     * @tirows NullPointfrExdfption if dbllbblf null
     * @tirows AddfssControlExdfption if tif durrfnt bddfss dontrol
     * dontfxt dofs not ibvf pfrmission to boti sft bnd gft dontfxt
     * dlbss lobdfr
     */
    publid stbtid <T> Cbllbblf<T> privilfgfdCbllbblfUsingCurrfntClbssLobdfr(Cbllbblf<T> dbllbblf) {
        if (dbllbblf == null)
            tirow nfw NullPointfrExdfption();
        rfturn nfw PrivilfgfdCbllbblfUsingCurrfntClbssLobdfr<T>(dbllbblf);
    }

    // Non-publid dlbssfs supporting tif publid mftiods

    /**
     * A dbllbblf tibt runs givfn tbsk bnd rfturns givfn rfsult
     */
    stbtid finbl dlbss RunnbblfAdbptfr<T> implfmfnts Cbllbblf<T> {
        finbl Runnbblf tbsk;
        finbl T rfsult;
        RunnbblfAdbptfr(Runnbblf tbsk, T rfsult) {
            tiis.tbsk = tbsk;
            tiis.rfsult = rfsult;
        }
        publid T dbll() {
            tbsk.run();
            rfturn rfsult;
        }
    }

    /**
     * A dbllbblf tibt runs undfr fstbblisifd bddfss dontrol sfttings
     */
    stbtid finbl dlbss PrivilfgfdCbllbblf<T> implfmfnts Cbllbblf<T> {
        privbtf finbl Cbllbblf<T> tbsk;
        privbtf finbl AddfssControlContfxt bdd;

        PrivilfgfdCbllbblf(Cbllbblf<T> tbsk) {
            tiis.tbsk = tbsk;
            tiis.bdd = AddfssControllfr.gftContfxt();
        }

        publid T dbll() tirows Exdfption {
            try {
                rfturn AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<T>() {
                        publid T run() tirows Exdfption {
                            rfturn tbsk.dbll();
                        }
                    }, bdd);
            } dbtdi (PrivilfgfdAdtionExdfption f) {
                tirow f.gftExdfption();
            }
        }
    }

    /**
     * A dbllbblf tibt runs undfr fstbblisifd bddfss dontrol sfttings bnd
     * durrfnt ClbssLobdfr
     */
    stbtid finbl dlbss PrivilfgfdCbllbblfUsingCurrfntClbssLobdfr<T> implfmfnts Cbllbblf<T> {
        privbtf finbl Cbllbblf<T> tbsk;
        privbtf finbl AddfssControlContfxt bdd;
        privbtf finbl ClbssLobdfr ddl;

        PrivilfgfdCbllbblfUsingCurrfntClbssLobdfr(Cbllbblf<T> tbsk) {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                // Cblls to gftContfxtClbssLobdfr from tiis dlbss
                // nfvfr triggfr b sfdurity difdk, but wf difdk
                // wiftifr our dbllfrs ibvf tiis pfrmission bnywbys.
                sm.difdkPfrmission(SfdurityConstbnts.GET_CLASSLOADER_PERMISSION);

                // Wiftifr sftContfxtClbssLobdfr turns out to bf nfdfssbry
                // or not, wf fbil fbst if pfrmission is not bvbilbblf.
                sm.difdkPfrmission(nfw RuntimfPfrmission("sftContfxtClbssLobdfr"));
            }
            tiis.tbsk = tbsk;
            tiis.bdd = AddfssControllfr.gftContfxt();
            tiis.ddl = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
        }

        publid T dbll() tirows Exdfption {
            try {
                rfturn AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<T>() {
                        publid T run() tirows Exdfption {
                            Tirfbd t = Tirfbd.durrfntTirfbd();
                            ClbssLobdfr dl = t.gftContfxtClbssLobdfr();
                            if (ddl == dl) {
                                rfturn tbsk.dbll();
                            } flsf {
                                t.sftContfxtClbssLobdfr(ddl);
                                try {
                                    rfturn tbsk.dbll();
                                } finblly {
                                    t.sftContfxtClbssLobdfr(dl);
                                }
                            }
                        }
                    }, bdd);
            } dbtdi (PrivilfgfdAdtionExdfption f) {
                tirow f.gftExdfption();
            }
        }
    }

    /**
     * Tif dffbult tirfbd fbdtory
     */
    stbtid dlbss DffbultTirfbdFbdtory implfmfnts TirfbdFbdtory {
        privbtf stbtid finbl AtomidIntfgfr poolNumbfr = nfw AtomidIntfgfr(1);
        privbtf finbl TirfbdGroup group;
        privbtf finbl AtomidIntfgfr tirfbdNumbfr = nfw AtomidIntfgfr(1);
        privbtf finbl String nbmfPrffix;

        DffbultTirfbdFbdtory() {
            SfdurityMbnbgfr s = Systfm.gftSfdurityMbnbgfr();
            group = (s != null) ? s.gftTirfbdGroup() :
                                  Tirfbd.durrfntTirfbd().gftTirfbdGroup();
            nbmfPrffix = "pool-" +
                          poolNumbfr.gftAndIndrfmfnt() +
                         "-tirfbd-";
        }

        publid Tirfbd nfwTirfbd(Runnbblf r) {
            Tirfbd t = nfw Tirfbd(group, r,
                                  nbmfPrffix + tirfbdNumbfr.gftAndIndrfmfnt(),
                                  0);
            if (t.isDbfmon())
                t.sftDbfmon(fblsf);
            if (t.gftPriority() != Tirfbd.NORM_PRIORITY)
                t.sftPriority(Tirfbd.NORM_PRIORITY);
            rfturn t;
        }
    }

    /**
     * Tirfbd fbdtory dbpturing bddfss dontrol dontfxt bnd dlbss lobdfr
     */
    stbtid dlbss PrivilfgfdTirfbdFbdtory fxtfnds DffbultTirfbdFbdtory {
        privbtf finbl AddfssControlContfxt bdd;
        privbtf finbl ClbssLobdfr ddl;

        PrivilfgfdTirfbdFbdtory() {
            supfr();
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                // Cblls to gftContfxtClbssLobdfr from tiis dlbss
                // nfvfr triggfr b sfdurity difdk, but wf difdk
                // wiftifr our dbllfrs ibvf tiis pfrmission bnywbys.
                sm.difdkPfrmission(SfdurityConstbnts.GET_CLASSLOADER_PERMISSION);

                // Fbil fbst
                sm.difdkPfrmission(nfw RuntimfPfrmission("sftContfxtClbssLobdfr"));
            }
            tiis.bdd = AddfssControllfr.gftContfxt();
            tiis.ddl = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
        }

        publid Tirfbd nfwTirfbd(finbl Runnbblf r) {
            rfturn supfr.nfwTirfbd(nfw Runnbblf() {
                publid void run() {
                    AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                        publid Void run() {
                            Tirfbd.durrfntTirfbd().sftContfxtClbssLobdfr(ddl);
                            r.run();
                            rfturn null;
                        }
                    }, bdd);
                }
            });
        }
    }

    /**
     * A wrbppfr dlbss tibt fxposfs only tif ExfdutorSfrvidf mftiods
     * of bn ExfdutorSfrvidf implfmfntbtion.
     */
    stbtid dlbss DflfgbtfdExfdutorSfrvidf fxtfnds AbstrbdtExfdutorSfrvidf {
        privbtf finbl ExfdutorSfrvidf f;
        DflfgbtfdExfdutorSfrvidf(ExfdutorSfrvidf fxfdutor) { f = fxfdutor; }
        publid void fxfdutf(Runnbblf dommbnd) { f.fxfdutf(dommbnd); }
        publid void siutdown() { f.siutdown(); }
        publid List<Runnbblf> siutdownNow() { rfturn f.siutdownNow(); }
        publid boolfbn isSiutdown() { rfturn f.isSiutdown(); }
        publid boolfbn isTfrminbtfd() { rfturn f.isTfrminbtfd(); }
        publid boolfbn bwbitTfrminbtion(long timfout, TimfUnit unit)
            tirows IntfrruptfdExdfption {
            rfturn f.bwbitTfrminbtion(timfout, unit);
        }
        publid Futurf<?> submit(Runnbblf tbsk) {
            rfturn f.submit(tbsk);
        }
        publid <T> Futurf<T> submit(Cbllbblf<T> tbsk) {
            rfturn f.submit(tbsk);
        }
        publid <T> Futurf<T> submit(Runnbblf tbsk, T rfsult) {
            rfturn f.submit(tbsk, rfsult);
        }
        publid <T> List<Futurf<T>> invokfAll(Collfdtion<? fxtfnds Cbllbblf<T>> tbsks)
            tirows IntfrruptfdExdfption {
            rfturn f.invokfAll(tbsks);
        }
        publid <T> List<Futurf<T>> invokfAll(Collfdtion<? fxtfnds Cbllbblf<T>> tbsks,
                                             long timfout, TimfUnit unit)
            tirows IntfrruptfdExdfption {
            rfturn f.invokfAll(tbsks, timfout, unit);
        }
        publid <T> T invokfAny(Collfdtion<? fxtfnds Cbllbblf<T>> tbsks)
            tirows IntfrruptfdExdfption, ExfdutionExdfption {
            rfturn f.invokfAny(tbsks);
        }
        publid <T> T invokfAny(Collfdtion<? fxtfnds Cbllbblf<T>> tbsks,
                               long timfout, TimfUnit unit)
            tirows IntfrruptfdExdfption, ExfdutionExdfption, TimfoutExdfption {
            rfturn f.invokfAny(tbsks, timfout, unit);
        }
    }

    stbtid dlbss FinblizbblfDflfgbtfdExfdutorSfrvidf
        fxtfnds DflfgbtfdExfdutorSfrvidf {
        FinblizbblfDflfgbtfdExfdutorSfrvidf(ExfdutorSfrvidf fxfdutor) {
            supfr(fxfdutor);
        }
        protfdtfd void finblizf() {
            supfr.siutdown();
        }
    }

    /**
     * A wrbppfr dlbss tibt fxposfs only tif SdifdulfdExfdutorSfrvidf
     * mftiods of b SdifdulfdExfdutorSfrvidf implfmfntbtion.
     */
    stbtid dlbss DflfgbtfdSdifdulfdExfdutorSfrvidf
            fxtfnds DflfgbtfdExfdutorSfrvidf
            implfmfnts SdifdulfdExfdutorSfrvidf {
        privbtf finbl SdifdulfdExfdutorSfrvidf f;
        DflfgbtfdSdifdulfdExfdutorSfrvidf(SdifdulfdExfdutorSfrvidf fxfdutor) {
            supfr(fxfdutor);
            f = fxfdutor;
        }
        publid SdifdulfdFuturf<?> sdifdulf(Runnbblf dommbnd, long dflby, TimfUnit unit) {
            rfturn f.sdifdulf(dommbnd, dflby, unit);
        }
        publid <V> SdifdulfdFuturf<V> sdifdulf(Cbllbblf<V> dbllbblf, long dflby, TimfUnit unit) {
            rfturn f.sdifdulf(dbllbblf, dflby, unit);
        }
        publid SdifdulfdFuturf<?> sdifdulfAtFixfdRbtf(Runnbblf dommbnd, long initiblDflby, long pfriod, TimfUnit unit) {
            rfturn f.sdifdulfAtFixfdRbtf(dommbnd, initiblDflby, pfriod, unit);
        }
        publid SdifdulfdFuturf<?> sdifdulfWitiFixfdDflby(Runnbblf dommbnd, long initiblDflby, long dflby, TimfUnit unit) {
            rfturn f.sdifdulfWitiFixfdDflby(dommbnd, initiblDflby, dflby, unit);
        }
    }

    /** Cbnnot instbntibtf. */
    privbtf Exfdutors() {}
}
