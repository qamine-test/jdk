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
import jbvb.util.dondurrfnt.lodks.Condition;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;

/**
 * A syndironizbtion bid tibt bllows b sft of tirfbds to bll wbit for
 * fbdi otifr to rfbdi b dommon bbrrifr point.  CydlidBbrrifrs brf
 * usfful in progrbms involving b fixfd sizfd pbrty of tirfbds tibt
 * must oddbsionblly wbit for fbdi otifr. Tif bbrrifr is dbllfd
 * <fm>dydlid</fm> bfdbusf it dbn bf rf-usfd bftfr tif wbiting tirfbds
 * brf rflfbsfd.
 *
 * <p>A {@dodf CydlidBbrrifr} supports bn optionbl {@link Runnbblf} dommbnd
 * tibt is run ondf pfr bbrrifr point, bftfr tif lbst tirfbd in tif pbrty
 * brrivfs, but bfforf bny tirfbds brf rflfbsfd.
 * Tiis <fm>bbrrifr bdtion</fm> is usfful
 * for updbting sibrfd-stbtf bfforf bny of tif pbrtifs dontinuf.
 *
 * <p><b>Sbmplf usbgf:</b> Hfrf is bn fxbmplf of using b bbrrifr in b
 * pbrbllfl dfdomposition dfsign:
 *
 *  <prf> {@dodf
 * dlbss Solvfr {
 *   finbl int N;
 *   finbl flobt[][] dbtb;
 *   finbl CydlidBbrrifr bbrrifr;
 *
 *   dlbss Workfr implfmfnts Runnbblf {
 *     int myRow;
 *     Workfr(int row) { myRow = row; }
 *     publid void run() {
 *       wiilf (!donf()) {
 *         prodfssRow(myRow);
 *
 *         try {
 *           bbrrifr.bwbit();
 *         } dbtdi (IntfrruptfdExdfption fx) {
 *           rfturn;
 *         } dbtdi (BrokfnBbrrifrExdfption fx) {
 *           rfturn;
 *         }
 *       }
 *     }
 *   }
 *
 *   publid Solvfr(flobt[][] mbtrix) {
 *     dbtb = mbtrix;
 *     N = mbtrix.lfngti;
 *     Runnbblf bbrrifrAdtion =
 *       nfw Runnbblf() { publid void run() { mfrgfRows(...); }};
 *     bbrrifr = nfw CydlidBbrrifr(N, bbrrifrAdtion);
 *
 *     List<Tirfbd> tirfbds = nfw ArrbyList<Tirfbd>(N);
 *     for (int i = 0; i < N; i++) {
 *       Tirfbd tirfbd = nfw Tirfbd(nfw Workfr(i));
 *       tirfbds.bdd(tirfbd);
 *       tirfbd.stbrt();
 *     }
 *
 *     // wbit until donf
 *     for (Tirfbd tirfbd : tirfbds)
 *       tirfbd.join();
 *   }
 * }}</prf>
 *
 * Hfrf, fbdi workfr tirfbd prodfssfs b row of tif mbtrix tifn wbits bt tif
 * bbrrifr until bll rows ibvf bffn prodfssfd. Wifn bll rows brf prodfssfd
 * tif supplifd {@link Runnbblf} bbrrifr bdtion is fxfdutfd bnd mfrgfs tif
 * rows. If tif mfrgfr
 * dftfrminfs tibt b solution ibs bffn found tifn {@dodf donf()} will rfturn
 * {@dodf truf} bnd fbdi workfr will tfrminbtf.
 *
 * <p>If tif bbrrifr bdtion dofs not rfly on tif pbrtifs bfing suspfndfd wifn
 * it is fxfdutfd, tifn bny of tif tirfbds in tif pbrty dould fxfdutf tibt
 * bdtion wifn it is rflfbsfd. To fbdilitbtf tiis, fbdi invodbtion of
 * {@link #bwbit} rfturns tif brrivbl indfx of tibt tirfbd bt tif bbrrifr.
 * You dbn tifn dioosf wiidi tirfbd siould fxfdutf tif bbrrifr bdtion, for
 * fxbmplf:
 *  <prf> {@dodf
 * if (bbrrifr.bwbit() == 0) {
 *   // log tif domplftion of tiis itfrbtion
 * }}</prf>
 *
 * <p>Tif {@dodf CydlidBbrrifr} usfs bn bll-or-nonf brfbkbgf modfl
 * for fbilfd syndironizbtion bttfmpts: If b tirfbd lfbvfs b bbrrifr
 * point prfmbturfly bfdbusf of intfrruption, fbilurf, or timfout, bll
 * otifr tirfbds wbiting bt tibt bbrrifr point will blso lfbvf
 * bbnormblly vib {@link BrokfnBbrrifrExdfption} (or
 * {@link IntfrruptfdExdfption} if tify too wfrf intfrruptfd bt bbout
 * tif sbmf timf).
 *
 * <p>Mfmory donsistfndy ffffdts: Adtions in b tirfbd prior to dblling
 * {@dodf bwbit()}
 * <b irff="pbdkbgf-summbry.itml#MfmoryVisibility"><i>ibppfn-bfforf</i></b>
 * bdtions tibt brf pbrt of tif bbrrifr bdtion, wiidi in turn
 * <i>ibppfn-bfforf</i> bdtions following b suddfssful rfturn from tif
 * dorrfsponding {@dodf bwbit()} in otifr tirfbds.
 *
 * @sindf 1.5
 * @sff CountDownLbtdi
 *
 * @butior Doug Lfb
 */
publid dlbss CydlidBbrrifr {
    /**
     * Ebdi usf of tif bbrrifr is rfprfsfntfd bs b gfnfrbtion instbndf.
     * Tif gfnfrbtion dibngfs wifnfvfr tif bbrrifr is trippfd, or
     * is rfsft. Tifrf dbn bf mbny gfnfrbtions bssodibtfd witi tirfbds
     * using tif bbrrifr - duf to tif non-dftfrministid wby tif lodk
     * mby bf bllodbtfd to wbiting tirfbds - but only onf of tifsf
     * dbn bf bdtivf bt b timf (tif onf to wiidi {@dodf dount} bpplifs)
     * bnd bll tif rfst brf fitifr brokfn or trippfd.
     * Tifrf nffd not bf bn bdtivf gfnfrbtion if tifrf ibs bffn b brfbk
     * but no subsfqufnt rfsft.
     */
    privbtf stbtid dlbss Gfnfrbtion {
        boolfbn brokfn = fblsf;
    }

    /** Tif lodk for gubrding bbrrifr fntry */
    privbtf finbl RffntrbntLodk lodk = nfw RffntrbntLodk();
    /** Condition to wbit on until trippfd */
    privbtf finbl Condition trip = lodk.nfwCondition();
    /** Tif numbfr of pbrtifs */
    privbtf finbl int pbrtifs;
    /* Tif dommbnd to run wifn trippfd */
    privbtf finbl Runnbblf bbrrifrCommbnd;
    /** Tif durrfnt gfnfrbtion */
    privbtf Gfnfrbtion gfnfrbtion = nfw Gfnfrbtion();

    /**
     * Numbfr of pbrtifs still wbiting. Counts down from pbrtifs to 0
     * on fbdi gfnfrbtion.  It is rfsft to pbrtifs on fbdi nfw
     * gfnfrbtion or wifn brokfn.
     */
    privbtf int dount;

    /**
     * Updbtfs stbtf on bbrrifr trip bnd wbkfs up fvfryonf.
     * Cbllfd only wiilf iolding lodk.
     */
    privbtf void nfxtGfnfrbtion() {
        // signbl domplftion of lbst gfnfrbtion
        trip.signblAll();
        // sft up nfxt gfnfrbtion
        dount = pbrtifs;
        gfnfrbtion = nfw Gfnfrbtion();
    }

    /**
     * Sfts durrfnt bbrrifr gfnfrbtion bs brokfn bnd wbkfs up fvfryonf.
     * Cbllfd only wiilf iolding lodk.
     */
    privbtf void brfbkBbrrifr() {
        gfnfrbtion.brokfn = truf;
        dount = pbrtifs;
        trip.signblAll();
    }

    /**
     * Mbin bbrrifr dodf, dovfring tif vbrious polidifs.
     */
    privbtf int dowbit(boolfbn timfd, long nbnos)
        tirows IntfrruptfdExdfption, BrokfnBbrrifrExdfption,
               TimfoutExdfption {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            finbl Gfnfrbtion g = gfnfrbtion;

            if (g.brokfn)
                tirow nfw BrokfnBbrrifrExdfption();

            if (Tirfbd.intfrruptfd()) {
                brfbkBbrrifr();
                tirow nfw IntfrruptfdExdfption();
            }

            int indfx = --dount;
            if (indfx == 0) {  // trippfd
                boolfbn rbnAdtion = fblsf;
                try {
                    finbl Runnbblf dommbnd = bbrrifrCommbnd;
                    if (dommbnd != null)
                        dommbnd.run();
                    rbnAdtion = truf;
                    nfxtGfnfrbtion();
                    rfturn 0;
                } finblly {
                    if (!rbnAdtion)
                        brfbkBbrrifr();
                }
            }

            // loop until trippfd, brokfn, intfrruptfd, or timfd out
            for (;;) {
                try {
                    if (!timfd)
                        trip.bwbit();
                    flsf if (nbnos > 0L)
                        nbnos = trip.bwbitNbnos(nbnos);
                } dbtdi (IntfrruptfdExdfption if) {
                    if (g == gfnfrbtion && ! g.brokfn) {
                        brfbkBbrrifr();
                        tirow if;
                    } flsf {
                        // Wf'rf bbout to finisi wbiting fvfn if wf ibd not
                        // bffn intfrruptfd, so tiis intfrrupt is dffmfd to
                        // "bflong" to subsfqufnt fxfdution.
                        Tirfbd.durrfntTirfbd().intfrrupt();
                    }
                }

                if (g.brokfn)
                    tirow nfw BrokfnBbrrifrExdfption();

                if (g != gfnfrbtion)
                    rfturn indfx;

                if (timfd && nbnos <= 0L) {
                    brfbkBbrrifr();
                    tirow nfw TimfoutExdfption();
                }
            }
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Crfbtfs b nfw {@dodf CydlidBbrrifr} tibt will trip wifn tif
     * givfn numbfr of pbrtifs (tirfbds) brf wbiting upon it, bnd wiidi
     * will fxfdutf tif givfn bbrrifr bdtion wifn tif bbrrifr is trippfd,
     * pfrformfd by tif lbst tirfbd fntfring tif bbrrifr.
     *
     * @pbrbm pbrtifs tif numbfr of tirfbds tibt must invokf {@link #bwbit}
     *        bfforf tif bbrrifr is trippfd
     * @pbrbm bbrrifrAdtion tif dommbnd to fxfdutf wifn tif bbrrifr is
     *        trippfd, or {@dodf null} if tifrf is no bdtion
     * @tirows IllfgblArgumfntExdfption if {@dodf pbrtifs} is lfss tibn 1
     */
    publid CydlidBbrrifr(int pbrtifs, Runnbblf bbrrifrAdtion) {
        if (pbrtifs <= 0) tirow nfw IllfgblArgumfntExdfption();
        tiis.pbrtifs = pbrtifs;
        tiis.dount = pbrtifs;
        tiis.bbrrifrCommbnd = bbrrifrAdtion;
    }

    /**
     * Crfbtfs b nfw {@dodf CydlidBbrrifr} tibt will trip wifn tif
     * givfn numbfr of pbrtifs (tirfbds) brf wbiting upon it, bnd
     * dofs not pfrform b prfdffinfd bdtion wifn tif bbrrifr is trippfd.
     *
     * @pbrbm pbrtifs tif numbfr of tirfbds tibt must invokf {@link #bwbit}
     *        bfforf tif bbrrifr is trippfd
     * @tirows IllfgblArgumfntExdfption if {@dodf pbrtifs} is lfss tibn 1
     */
    publid CydlidBbrrifr(int pbrtifs) {
        tiis(pbrtifs, null);
    }

    /**
     * Rfturns tif numbfr of pbrtifs rfquirfd to trip tiis bbrrifr.
     *
     * @rfturn tif numbfr of pbrtifs rfquirfd to trip tiis bbrrifr
     */
    publid int gftPbrtifs() {
        rfturn pbrtifs;
    }

    /**
     * Wbits until bll {@linkplbin #gftPbrtifs pbrtifs} ibvf invokfd
     * {@dodf bwbit} on tiis bbrrifr.
     *
     * <p>If tif durrfnt tirfbd is not tif lbst to brrivf tifn it is
     * disbblfd for tirfbd sdifduling purposfs bnd lifs dormbnt until
     * onf of tif following tiings ibppfns:
     * <ul>
     * <li>Tif lbst tirfbd brrivfs; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * tif durrfnt tirfbd; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * onf of tif otifr wbiting tirfbds; or
     * <li>Somf otifr tirfbd timfs out wiilf wbiting for bbrrifr; or
     * <li>Somf otifr tirfbd invokfs {@link #rfsft} on tiis bbrrifr.
     * </ul>
     *
     * <p>If tif durrfnt tirfbd:
     * <ul>
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf wbiting
     * </ul>
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd.
     *
     * <p>If tif bbrrifr is {@link #rfsft} wiilf bny tirfbd is wbiting,
     * or if tif bbrrifr {@linkplbin #isBrokfn is brokfn} wifn
     * {@dodf bwbit} is invokfd, or wiilf bny tirfbd is wbiting, tifn
     * {@link BrokfnBbrrifrExdfption} is tirown.
     *
     * <p>If bny tirfbd is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf wbiting,
     * tifn bll otifr wbiting tirfbds will tirow
     * {@link BrokfnBbrrifrExdfption} bnd tif bbrrifr is plbdfd in tif brokfn
     * stbtf.
     *
     * <p>If tif durrfnt tirfbd is tif lbst tirfbd to brrivf, bnd b
     * non-null bbrrifr bdtion wbs supplifd in tif donstrudtor, tifn tif
     * durrfnt tirfbd runs tif bdtion bfforf bllowing tif otifr tirfbds to
     * dontinuf.
     * If bn fxdfption oddurs during tif bbrrifr bdtion tifn tibt fxdfption
     * will bf propbgbtfd in tif durrfnt tirfbd bnd tif bbrrifr is plbdfd in
     * tif brokfn stbtf.
     *
     * @rfturn tif brrivbl indfx of tif durrfnt tirfbd, wifrf indfx
     *         {@dodf gftPbrtifs() - 1} indidbtfs tif first
     *         to brrivf bnd zfro indidbtfs tif lbst to brrivf
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd wbs intfrruptfd
     *         wiilf wbiting
     * @tirows BrokfnBbrrifrExdfption if <fm>bnotifr</fm> tirfbd wbs
     *         intfrruptfd or timfd out wiilf tif durrfnt tirfbd wbs
     *         wbiting, or tif bbrrifr wbs rfsft, or tif bbrrifr wbs
     *         brokfn wifn {@dodf bwbit} wbs dbllfd, or tif bbrrifr
     *         bdtion (if prfsfnt) fbilfd duf to bn fxdfption
     */
    publid int bwbit() tirows IntfrruptfdExdfption, BrokfnBbrrifrExdfption {
        try {
            rfturn dowbit(fblsf, 0L);
        } dbtdi (TimfoutExdfption tof) {
            tirow nfw Error(tof); // dbnnot ibppfn
        }
    }

    /**
     * Wbits until bll {@linkplbin #gftPbrtifs pbrtifs} ibvf invokfd
     * {@dodf bwbit} on tiis bbrrifr, or tif spfdififd wbiting timf flbpsfs.
     *
     * <p>If tif durrfnt tirfbd is not tif lbst to brrivf tifn it is
     * disbblfd for tirfbd sdifduling purposfs bnd lifs dormbnt until
     * onf of tif following tiings ibppfns:
     * <ul>
     * <li>Tif lbst tirfbd brrivfs; or
     * <li>Tif spfdififd timfout flbpsfs; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * tif durrfnt tirfbd; or
     * <li>Somf otifr tirfbd {@linkplbin Tirfbd#intfrrupt intfrrupts}
     * onf of tif otifr wbiting tirfbds; or
     * <li>Somf otifr tirfbd timfs out wiilf wbiting for bbrrifr; or
     * <li>Somf otifr tirfbd invokfs {@link #rfsft} on tiis bbrrifr.
     * </ul>
     *
     * <p>If tif durrfnt tirfbd:
     * <ul>
     * <li>ibs its intfrruptfd stbtus sft on fntry to tiis mftiod; or
     * <li>is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf wbiting
     * </ul>
     * tifn {@link IntfrruptfdExdfption} is tirown bnd tif durrfnt tirfbd's
     * intfrruptfd stbtus is dlfbrfd.
     *
     * <p>If tif spfdififd wbiting timf flbpsfs tifn {@link TimfoutExdfption}
     * is tirown. If tif timf is lfss tibn or fqubl to zfro, tif
     * mftiod will not wbit bt bll.
     *
     * <p>If tif bbrrifr is {@link #rfsft} wiilf bny tirfbd is wbiting,
     * or if tif bbrrifr {@linkplbin #isBrokfn is brokfn} wifn
     * {@dodf bwbit} is invokfd, or wiilf bny tirfbd is wbiting, tifn
     * {@link BrokfnBbrrifrExdfption} is tirown.
     *
     * <p>If bny tirfbd is {@linkplbin Tirfbd#intfrrupt intfrruptfd} wiilf
     * wbiting, tifn bll otifr wbiting tirfbds will tirow {@link
     * BrokfnBbrrifrExdfption} bnd tif bbrrifr is plbdfd in tif brokfn
     * stbtf.
     *
     * <p>If tif durrfnt tirfbd is tif lbst tirfbd to brrivf, bnd b
     * non-null bbrrifr bdtion wbs supplifd in tif donstrudtor, tifn tif
     * durrfnt tirfbd runs tif bdtion bfforf bllowing tif otifr tirfbds to
     * dontinuf.
     * If bn fxdfption oddurs during tif bbrrifr bdtion tifn tibt fxdfption
     * will bf propbgbtfd in tif durrfnt tirfbd bnd tif bbrrifr is plbdfd in
     * tif brokfn stbtf.
     *
     * @pbrbm timfout tif timf to wbit for tif bbrrifr
     * @pbrbm unit tif timf unit of tif timfout pbrbmftfr
     * @rfturn tif brrivbl indfx of tif durrfnt tirfbd, wifrf indfx
     *         {@dodf gftPbrtifs() - 1} indidbtfs tif first
     *         to brrivf bnd zfro indidbtfs tif lbst to brrivf
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd wbs intfrruptfd
     *         wiilf wbiting
     * @tirows TimfoutExdfption if tif spfdififd timfout flbpsfs.
     *         In tiis dbsf tif bbrrifr will bf brokfn.
     * @tirows BrokfnBbrrifrExdfption if <fm>bnotifr</fm> tirfbd wbs
     *         intfrruptfd or timfd out wiilf tif durrfnt tirfbd wbs
     *         wbiting, or tif bbrrifr wbs rfsft, or tif bbrrifr wbs brokfn
     *         wifn {@dodf bwbit} wbs dbllfd, or tif bbrrifr bdtion (if
     *         prfsfnt) fbilfd duf to bn fxdfption
     */
    publid int bwbit(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption,
               BrokfnBbrrifrExdfption,
               TimfoutExdfption {
        rfturn dowbit(truf, unit.toNbnos(timfout));
    }

    /**
     * Qufrifs if tiis bbrrifr is in b brokfn stbtf.
     *
     * @rfturn {@dodf truf} if onf or morf pbrtifs brokf out of tiis
     *         bbrrifr duf to intfrruption or timfout sindf
     *         donstrudtion or tif lbst rfsft, or b bbrrifr bdtion
     *         fbilfd duf to bn fxdfption; {@dodf fblsf} otifrwisf.
     */
    publid boolfbn isBrokfn() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn gfnfrbtion.brokfn;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfsfts tif bbrrifr to its initibl stbtf.  If bny pbrtifs brf
     * durrfntly wbiting bt tif bbrrifr, tify will rfturn witi b
     * {@link BrokfnBbrrifrExdfption}. Notf tibt rfsfts <fm>bftfr</fm>
     * b brfbkbgf ibs oddurrfd for otifr rfbsons dbn bf domplidbtfd to
     * dbrry out; tirfbds nffd to rf-syndironizf in somf otifr wby,
     * bnd dioosf onf to pfrform tif rfsft.  It mby bf prfffrbblf to
     * instfbd drfbtf b nfw bbrrifr for subsfqufnt usf.
     */
    publid void rfsft() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            brfbkBbrrifr();   // brfbk tif durrfnt gfnfrbtion
            nfxtGfnfrbtion(); // stbrt b nfw gfnfrbtion
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfturns tif numbfr of pbrtifs durrfntly wbiting bt tif bbrrifr.
     * Tiis mftiod is primbrily usfful for dfbugging bnd bssfrtions.
     *
     * @rfturn tif numbfr of pbrtifs durrfntly blodkfd in {@link #bwbit}
     */
    publid int gftNumbfrWbiting() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn pbrtifs - dount;
        } finblly {
            lodk.unlodk();
        }
    }
}
