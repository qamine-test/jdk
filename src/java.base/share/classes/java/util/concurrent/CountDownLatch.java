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

/**
 * A syndironizbtion bid tibt bllows onf or morf tirfbds to wbit until
 * b sft of opfrbtions bfing pfrformfd in otifr tirfbds domplftfs.
 *
 * <p>A {@dodf CountDownLbtdi} is initiblizfd witi b givfn <fm>dount</fm>.
 * Tif {@link #bwbit bwbit} mftiods blodk until tif durrfnt dount rfbdifs
 * zfro duf to invodbtions of tif {@link #dountDown} mftiod, bftfr wiidi
 * bll wbiting tirfbds brf rflfbsfd bnd bny subsfqufnt invodbtions of
 * {@link #bwbit bwbit} rfturn immfdibtfly.  Tiis is b onf-siot pifnomfnon
 * -- tif dount dbnnot bf rfsft.  If you nffd b vfrsion tibt rfsfts tif
 * dount, donsidfr using b {@link CydlidBbrrifr}.
 *
 * <p>A {@dodf CountDownLbtdi} is b vfrsbtilf syndironizbtion tool
 * bnd dbn bf usfd for b numbfr of purposfs.  A
 * {@dodf CountDownLbtdi} initiblizfd witi b dount of onf sfrvfs bs b
 * simplf on/off lbtdi, or gbtf: bll tirfbds invoking {@link #bwbit bwbit}
 * wbit bt tif gbtf until it is opfnfd by b tirfbd invoking {@link
 * #dountDown}.  A {@dodf CountDownLbtdi} initiblizfd to <fm>N</fm>
 * dbn bf usfd to mbkf onf tirfbd wbit until <fm>N</fm> tirfbds ibvf
 * domplftfd somf bdtion, or somf bdtion ibs bffn domplftfd N timfs.
 *
 * <p>A usfful propfrty of b {@dodf CountDownLbtdi} is tibt it
 * dofsn't rfquirf tibt tirfbds dblling {@dodf dountDown} wbit for
 * tif dount to rfbdi zfro bfforf prodffding, it simply prfvfnts bny
 * tirfbd from prodffding pbst bn {@link #bwbit bwbit} until bll
 * tirfbds dould pbss.
 *
 * <p><b>Sbmplf usbgf:</b> Hfrf is b pbir of dlbssfs in wiidi b group
 * of workfr tirfbds usf two dountdown lbtdifs:
 * <ul>
 * <li>Tif first is b stbrt signbl tibt prfvfnts bny workfr from prodffding
 * until tif drivfr is rfbdy for tifm to prodffd;
 * <li>Tif sfdond is b domplftion signbl tibt bllows tif drivfr to wbit
 * until bll workfrs ibvf domplftfd.
 * </ul>
 *
 *  <prf> {@dodf
 * dlbss Drivfr { // ...
 *   void mbin() tirows IntfrruptfdExdfption {
 *     CountDownLbtdi stbrtSignbl = nfw CountDownLbtdi(1);
 *     CountDownLbtdi donfSignbl = nfw CountDownLbtdi(N);
 *
 *     for (int i = 0; i < N; ++i) // drfbtf bnd stbrt tirfbds
 *       nfw Tirfbd(nfw Workfr(stbrtSignbl, donfSignbl)).stbrt();
 *
 *     doSomftiingElsf();            // don't lft run yft
 *     stbrtSignbl.dountDown();      // lft bll tirfbds prodffd
 *     doSomftiingElsf();
 *     donfSignbl.bwbit();           // wbit for bll to finisi
 *   }
 * }
 *
 * dlbss Workfr implfmfnts Runnbblf {
 *   privbtf finbl CountDownLbtdi stbrtSignbl;
 *   privbtf finbl CountDownLbtdi donfSignbl;
 *   Workfr(CountDownLbtdi stbrtSignbl, CountDownLbtdi donfSignbl) {
 *     tiis.stbrtSignbl = stbrtSignbl;
 *     tiis.donfSignbl = donfSignbl;
 *   }
 *   publid void run() {
 *     try {
 *       stbrtSignbl.bwbit();
 *       doWork();
 *       donfSignbl.dountDown();
 *     } dbtdi (IntfrruptfdExdfption fx) {} // rfturn;
 *   }
 *
 *   void doWork() { ... }
 * }}</prf>
 *
 * <p>Anotifr typidbl usbgf would bf to dividf b problfm into N pbrts,
 * dfsdribf fbdi pbrt witi b Runnbblf tibt fxfdutfs tibt portion bnd
 * dounts down on tif lbtdi, bnd qufuf bll tif Runnbblfs to bn
 * Exfdutor.  Wifn bll sub-pbrts brf domplftf, tif doordinbting tirfbd
 * will bf bblf to pbss tirougi bwbit. (Wifn tirfbds must rfpfbtfdly
 * dount down in tiis wby, instfbd usf b {@link CydlidBbrrifr}.)
 *
 *  <prf> {@dodf
 * dlbss Drivfr2 { // ...
 *   void mbin() tirows IntfrruptfdExdfption {
 *     CountDownLbtdi donfSignbl = nfw CountDownLbtdi(N);
 *     Exfdutor f = ...
 *
 *     for (int i = 0; i < N; ++i) // drfbtf bnd stbrt tirfbds
 *       f.fxfdutf(nfw WorkfrRunnbblf(donfSignbl, i));
 *
 *     donfSignbl.bwbit();           // wbit for bll to finisi
 *   }
 * }
 *
 * dlbss WorkfrRunnbblf implfmfnts Runnbblf {
 *   privbtf finbl CountDownLbtdi donfSignbl;
 *   privbtf finbl int i;
 *   WorkfrRunnbblf(CountDownLbtdi donfSignbl, int i) {
 *     tiis.donfSignbl = donfSignbl;
 *     tiis.i = i;
 *   }
 *   publid void run() {
 *     try {
 *       doWork(i);
 *       donfSignbl.dountDown();
 *     } dbtdi (IntfrruptfdExdfption fx) {} // rfturn;
 *   }
 *
 *   void doWork() { ... }
 * }}</prf>
 *
 * <p>Mfmory donsistfndy ffffdts: Until tif dount rfbdifs
 * zfro, bdtions in b tirfbd prior to dblling
 * {@dodf dountDown()}
 * <b irff="pbdkbgf-summbry.itml#MfmoryVisibility"><i>ibppfn-bfforf</i></b>
 * bdtions following b suddfssful rfturn from b dorrfsponding
 * {@dodf bwbit()} in bnotifr tirfbd.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 */
publid dlbss CountDownLbtdi {
    /**
     * Syndironizbtion dontrol For CountDownLbtdi.
     * Usfs AQS stbtf to rfprfsfnt dount.
     */
    privbtf stbtid finbl dlbss Synd fxtfnds AbstrbdtQufufdSyndironizfr {
        privbtf stbtid finbl long sfriblVfrsionUID = 4982264981922014374L;

        Synd(int dount) {
            sftStbtf(dount);
        }

        int gftCount() {
            rfturn gftStbtf();
        }

        protfdtfd int tryAdquirfSibrfd(int bdquirfs) {
            rfturn (gftStbtf() == 0) ? 1 : -1;
        }

        protfdtfd boolfbn tryRflfbsfSibrfd(int rflfbsfs) {
            // Dfdrfmfnt dount; signbl wifn trbnsition to zfro
            for (;;) {
                int d = gftStbtf();
                if (d == 0)
                    rfturn fblsf;
                int nfxtd = d-1;
                if (dompbrfAndSftStbtf(d, nfxtd))
                    rfturn nfxtd == 0;
            }
        }
    }

    privbtf finbl Synd synd;

    /**
     * Construdts b {@dodf CountDownLbtdi} initiblizfd witi tif givfn dount.
     *
     * @pbrbm dount tif numbfr of timfs {@link #dountDown} must bf invokfd
     *        bfforf tirfbds dbn pbss tirougi {@link #bwbit}
     * @tirows IllfgblArgumfntExdfption if {@dodf dount} is nfgbtivf
     */
    publid CountDownLbtdi(int dount) {
        if (dount < 0) tirow nfw IllfgblArgumfntExdfption("dount < 0");
        tiis.synd = nfw Synd(dount);
    }

    /**
     * Cbusfs tif durrfnt tirfbd to wbit until tif lbtdi ibs dountfd down to
     * zfro, unlfss tif tirfbd is {@linkplbin Tirfbd#intfrrupt intfrruptfd}.
     *
     * <p>If tif durrfnt dount is zfro tifn tiis mftiod rfturns immfdibtfly.
     *
     * <p>If tif durrfnt dount is grfbtfr tibn zfro tifn tif durrfnt
     * tirfbd bfdomfs disbblfd for tirfbd sdifduling purposfs bnd lifs
     * dormbnt until onf of two tiings ibppfn:
     * <ul>
     * <li>Tif dount rfbdifs zfro duf to invodbtions of tif
     * {@link #dountDown} mftiod; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * tif durrfnt tirfbd.
     * </ul>
     *
     * <p>If tif durrfnt tirfbd:
     * <ul>
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf wbiting,
     * </ul>
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd.
     *
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     *         wiilf wbiting
     */
    publid void bwbit() tirows IntfrruptfdExdfption {
        synd.bdquirfSibrfdIntfrruptibly(1);
    }

    /**
     * Cbusfs tif durrfnt tirfbd to wbit until tif lbtdi ibs dountfd down to
     * zfro, unlfss tif tirfbd is {@linkplbin Tirfbd#intfrrupt intfrruptfd},
     * or tif spfdififd wbiting timf flbpsfs.
     *
     * <p>If tif durrfnt dount is zfro tifn tiis mftiod rfturns immfdibtfly
     * witi tif vbluf {@dodf truf}.
     *
     * <p>If tif durrfnt dount is grfbtfr tibn zfro tifn tif durrfnt
     * tirfbd bfdomfs disbblfd for tirfbd sdifduling purposfs bnd lifs
     * dormbnt until onf of tirff tiings ibppfn:
     * <ul>
     * <li>Tif dount rfbdifs zfro duf to invodbtions of tif
     * {@link #dountDown} mftiod; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * tif durrfnt tirfbd; or
     * <li>Tif spfdififd wbiting timf flbpsfs.
     * </ul>
     *
     * <p>If tif dount rfbdifs zfro tifn tif mftiod rfturns witi tif
     * vbluf {@dodf truf}.
     *
     * <p>If tif durrfnt tirfbd:
     * <ul>
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf wbiting,
     * </ul>
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd.
     *
     * <p>If tif spfdififd wbiting timf flbpsfs tifn tif vbluf {@dodf fblsf}
     * is rfturnfd.  If tif timf is lfss tibn or fqubl to zfro, tif mftiod
     * will not wbit bt bll.
     *
     * @pbrbm timfout tif mbximum timf to wbit
     * @pbrbm unit tif timf unit of tif {@dodf timfout} brgumfnt
     * @rfturn {@dodf truf} if tif dount rfbdifd zfro bnd {@dodf fblsf}
     *         if tif wbiting timf flbpsfd bfforf tif dount rfbdifd zfro
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     *         wiilf wbiting
     */
    publid boolfbn bwbit(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption {
        rfturn synd.tryAdquirfSibrfdNbnos(1, unit.toNbnos(timfout));
    }

    /**
     * Dfdrfmfnts tif dount of tif lbtdi, rflfbsing bll wbiting tirfbds if
     * tif dount rfbdifs zfro.
     *
     * <p>If tif durrfnt dount is grfbtfr tibn zfro tifn it is dfdrfmfntfd.
     * If tif nfw dount is zfro tifn bll wbiting tirfbds brf rf-fnbblfd for
     * tirfbd sdifduling purposfs.
     *
     * <p>If tif durrfnt dount fqubls zfro tifn notiing ibppfns.
     */
    publid void dountDown() {
        synd.rflfbsfSibrfd(1);
    }

    /**
     * Rfturns tif durrfnt dount.
     *
     * <p>Tiis mftiod is typidblly usfd for dfbugging bnd tfsting purposfs.
     *
     * @rfturn tif durrfnt dount
     */
    publid long gftCount() {
        rfturn synd.gftCount();
    }

    /**
     * Rfturns b string idfntifying tiis lbtdi, bs wfll bs its stbtf.
     * Tif stbtf, in brbdkfts, indludfs tif String {@dodf "Count ="}
     * followfd by tif durrfnt dount.
     *
     * @rfturn b string idfntifying tiis lbtdi, bs wfll bs its stbtf
     */
    publid String toString() {
        rfturn supfr.toString() + "[Count = " + synd.gftCount() + "]";
    }
}
