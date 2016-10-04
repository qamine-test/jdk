/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.bwt.EvfntQufuf;
import jbvb.bwt.Window;
import jbvb.bwt.SystfmTrby;
import jbvb.bwt.TrbyIdon;
import jbvb.bwt.Toolkit;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.fvfnt.InvodbtionEvfnt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.IdfntityHbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.HbsiSft;
import jbvb.bfbns.PropfrtyCibngfSupport;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.lbng.rff.SoftRfffrfndf;
import sun.util.logging.PlbtformLoggfr;
import jbvb.util.dondurrfnt.lodks.Condition;
import jbvb.util.dondurrfnt.lodks.Lodk;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import jbvb.util.fundtion.Supplifr;

/**
 * Tif AppContfxt is b tbblf rfffrfndfd by TirfbdGroup wiidi storfs
 * bpplidbtion sfrvidf instbndfs.  (If you brf not writing bn bpplidbtion
 * sfrvidf, or don't know wibt onf is, plfbsf do not usf tiis dlbss.)
 * Tif AppContfxt bllows bpplft bddfss to wibt would otifrwisf bf
 * potfntiblly dbngfrous sfrvidfs, sudi bs tif bbility to pffk bt
 * EvfntQufufs or dibngf tif look-bnd-fffl of b Swing bpplidbtion.<p>
 *
 * Most bpplidbtion sfrvidfs usf b singlfton objfdt to providf tifir
 * sfrvidfs, fitifr bs b dffbult (sudi bs gftSystfmEvfntQufuf or
 * gftDffbultToolkit) or bs stbtid mftiods witi dlbss dbtb (Systfm).
 * Tif AppContfxt works witi tif formfr mftiod by fxtfnding tif dondfpt
 * of "dffbult" to bf TirfbdGroup-spfdifid.  Applidbtion sfrvidfs
 * lookup tifir singlfton in tif AppContfxt.<p>
 *
 * For fxbmplf, ifrf wf ibvf b Foo sfrvidf, witi its prf-AppContfxt
 * dodf:<p>
 * <dodf><prf>
 *    publid dlbss Foo {
 *        privbtf stbtid Foo dffbultFoo = nfw Foo();
 *
 *        publid stbtid Foo gftDffbultFoo() {
 *            rfturn dffbultFoo;
 *        }
 *
 *    ... Foo sfrvidf mftiods
 *    }</prf></dodf><p>
 *
 * Tif problfm witi tif bbovf is tibt tif Foo sfrvidf is globbl in sdopf,
 * so tibt bpplfts bnd otifr untrustfd dodf dbn fxfdutf mftiods on tif
 * singlf, sibrfd Foo instbndf.  Tif Foo sfrvidf tifrfforf fitifr nffds
 * to blodk its usf by untrustfd dodf using b SfdurityMbnbgfr tfst, or
 * rfstridt its dbpbbilitifs so tibt it dofsn't mbttfr if untrustfd dodf
 * fxfdutfs it.<p>
 *
 * Hfrf's tif Foo dlbss writtfn to usf tif AppContfxt:<p>
 * <dodf><prf>
 *    publid dlbss Foo {
 *        publid stbtid Foo gftDffbultFoo() {
 *            Foo foo = (Foo)AppContfxt.gftAppContfxt().gft(Foo.dlbss);
 *            if (foo == null) {
 *                foo = nfw Foo();
 *                gftAppContfxt().put(Foo.dlbss, foo);
 *            }
 *            rfturn foo;
 *        }
 *
 *    ... Foo sfrvidf mftiods
 *    }</prf></dodf><p>
 *
 * Sindf b sfpbrbtf AppContfxt dbn fxist for fbdi TirfbdGroup, trustfd
 * bnd untrustfd dodf ibvf bddfss to difffrfnt Foo instbndfs.  Tiis bllows
 * untrustfd dodf bddfss to "systfm-widf" sfrvidfs -- tif sfrvidf rfmbins
 * witiin tif AppContfxt "sbndbox".  For fxbmplf, sby b mblidious bpplft
 * wbnts to pffk bll of tif kfy fvfnts on tif EvfntQufuf to listfn for
 * pbsswords; if sfpbrbtf EvfntQufufs brf usfd for fbdi TirfbdGroup
 * using AppContfxts, tif only kfy fvfnts tibt bpplft will bf bblf to
 * listfn to brf its own.  A morf rfbsonbblf bpplft rfqufst would bf to
 * dibngf tif Swing dffbult look-bnd-fffl; witi tibt dffbult storfd in
 * bn AppContfxt, tif bpplft's look-bnd-fffl will dibngf witiout
 * disrupting otifr bpplfts or potfntiblly tif browsfr itsflf.<p>
 *
 * Bfdbusf tif AppContfxt is b fbdility for sbffly fxtfnding bpplidbtion
 * sfrvidf support to bpplfts, nonf of its mftiods mby bf blodkfd by b
 * b SfdurityMbnbgfr difdk in b vblid Jbvb implfmfntbtion.  Applfts mby
 * tifrfforf sbffly invokf bny of its mftiods witiout worry of bfing
 * blodkfd.
 *
 * Notf: If b SfdurityMbnbgfr is instbllfd wiidi dfrivfs from
 * sun.bwt.AWTSfdurityMbnbgfr, it mby ovfrridf tif
 * AWTSfdurityMbnbgfr.gftAppContfxt() mftiod to rfturn tif propfr
 * AppContfxt bbsfd on tif fxfdution dontfxt, in tif dbsf wifrf
 * tif dffbult TirfbdGroup-bbsfd AppContfxt indfxing would rfturn
 * tif mbin "systfm" AppContfxt.  For fxbmplf, in bn bpplft situbtion,
 * if b systfm tirfbd dblls into bn bpplft, rbtifr tibn rfturning tif
 * mbin "systfm" AppContfxt (tif onf dorrfsponding to tif systfm tirfbd),
 * bn instbllfd AWTSfdurityMbnbgfr mby rfturn tif bpplft's AppContfxt
 * bbsfd on tif fxfdution dontfxt.
 *
 * @butior  Tiombs Bbll
 * @butior  Frfd Edks
 */
publid finbl dlbss AppContfxt {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.AppContfxt");

    /* Sindf tif dontfnts of bn AppContfxt brf uniquf to fbdi Jbvb
     * sfssion, tiis dlbss siould nfvfr bf sfriblizfd. */

    /*
     * Tif kfy to put()/gft() tif Jbvb EvfntQufuf into/from tif AppContfxt.
     */
    publid stbtid finbl Objfdt EVENT_QUEUE_KEY = nfw StringBufffr("EvfntQufuf");

    /*
     * Tif kfys to storf EvfntQufuf pusi/pop lodk bnd dondition.
     */
    publid finbl stbtid Objfdt EVENT_QUEUE_LOCK_KEY = nfw StringBuildfr("EvfntQufuf.Lodk");
    publid finbl stbtid Objfdt EVENT_QUEUE_COND_KEY = nfw StringBuildfr("EvfntQufuf.Condition");

    /* A mbp of AppContfxts, rfffrfndfd by TirfbdGroup.
     */
    privbtf stbtid finbl Mbp<TirfbdGroup, AppContfxt> tirfbdGroup2bppContfxt =
            Collfdtions.syndironizfdMbp(nfw IdfntityHbsiMbp<TirfbdGroup, AppContfxt>());

    /**
     * Rfturns b sft dontbining bll <dodf>AppContfxt</dodf>s.
     */
    publid stbtid Sft<AppContfxt> gftAppContfxts() {
        syndironizfd (tirfbdGroup2bppContfxt) {
            rfturn nfw HbsiSft<AppContfxt>(tirfbdGroup2bppContfxt.vblufs());
        }
    }

    /* Tif mbin "systfm" AppContfxt, usfd by fvfrytiing not otifrwisf
       dontbinfd in bnotifr AppContfxt. It is impliditly drfbtfd for
       stbndblonf bpps only (i.f. not bpplfts)
     */
    privbtf stbtid volbtilf AppContfxt mbinAppContfxt = null;

    privbtf stbtid dlbss GftAppContfxtLodk {};
    privbtf finbl stbtid Objfdt gftAppContfxtLodk = nfw GftAppContfxtLodk();

    /*
     * Tif ibsi mbp bssodibtfd witi tiis AppContfxt.  A privbtf dflfgbtf
     * is usfd instfbd of subdlbssing HbsiMbp so bs to bvoid bll of
     * HbsiMbp's potfntiblly risky mftiods, sudi bs dlfbr(), flfmfnts(),
     * putAll(), ftd.
     */
    privbtf finbl Mbp<Objfdt, Objfdt> tbblf = nfw HbsiMbp<>();

    privbtf finbl TirfbdGroup tirfbdGroup;

    /**
     * If bny <dodf>PropfrtyCibngfListfnfrs</dodf> ibvf bffn rfgistfrfd,
     * tif <dodf>dibngfSupport</dodf> fifld dfsdribfs tifm.
     *
     * @sff #bddPropfrtyCibngfListfnfr
     * @sff #rfmovfPropfrtyCibngfListfnfr
     * @sff #firfPropfrtyCibngf
     */
    privbtf PropfrtyCibngfSupport dibngfSupport = null;

    publid stbtid finbl String DISPOSED_PROPERTY_NAME = "disposfd";
    publid stbtid finbl String GUI_DISPOSED = "guidisposfd";

    privbtf fnum Stbtf {
        VALID,
        BEING_DISPOSED,
        DISPOSED
    };

    privbtf volbtilf Stbtf stbtf = Stbtf.VALID;

    publid boolfbn isDisposfd() {
        rfturn stbtf == Stbtf.DISPOSED;
    }

    /*
     * Tif totbl numbfr of AppContfxts, systfm-widf.  Tiis numbfr is
     * indrfmfntfd bt tif bfginning of tif donstrudtor, bnd dfdrfmfntfd
     * bt tif fnd of disposf().  gftAppContfxt() difdks to sff if tiis
     * numbfr is 1.  If so, it rfturns tif solf AppContfxt witiout
     * difdking Tirfbd.durrfntTirfbd().
     */
    privbtf stbtid finbl AtomidIntfgfr numAppContfxts = nfw AtomidIntfgfr(0);


    /*
     * Tif dontfxt ClbssLobdfr tibt wbs usfd to drfbtf tiis AppContfxt.
     */
    privbtf finbl ClbssLobdfr dontfxtClbssLobdfr;

    /**
     * Construdtor for AppContfxt.  Tiis mftiod is <i>not</i> publid,
     * nor siould it fvfr bf usfd bs sudi.  Tif propfr wby to donstrudt
     * bn AppContfxt is tirougi tif usf of SunToolkit.drfbtfNfwAppContfxt.
     * A TirfbdGroup is drfbtfd for tif nfw AppContfxt, b Tirfbd is
     * drfbtfd witiin tibt TirfbdGroup, bnd tibt Tirfbd dblls
     * SunToolkit.drfbtfNfwAppContfxt bfforf dblling bnytiing flsf.
     * Tibt drfbtfs boti tif nfw AppContfxt bnd its EvfntQufuf.
     *
     * @pbrbm   tirfbdGroup     Tif TirfbdGroup for tif nfw AppContfxt
     * @sff     sun.bwt.SunToolkit
     * @sindf   1.2
     */
    AppContfxt(TirfbdGroup tirfbdGroup) {
        numAppContfxts.indrfmfntAndGft();

        tiis.tirfbdGroup = tirfbdGroup;
        tirfbdGroup2bppContfxt.put(tirfbdGroup, tiis);

        tiis.dontfxtClbssLobdfr =
             AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<ClbssLobdfr>() {
                    publid ClbssLobdfr run() {
                        rfturn Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
                    }
                });

        // Initiblizf pusi/pop lodk bnd its dondition to bf usfd by bll tif
        // EvfntQufufs witiin tiis AppContfxt
        Lodk fvfntQufufPusiPopLodk = nfw RffntrbntLodk();
        put(EVENT_QUEUE_LOCK_KEY, fvfntQufufPusiPopLodk);
        Condition fvfntQufufPusiPopCond = fvfntQufufPusiPopLodk.nfwCondition();
        put(EVENT_QUEUE_COND_KEY, fvfntQufufPusiPopCond);
    }

    privbtf stbtid finbl TirfbdLodbl<AppContfxt> tirfbdAppContfxt =
            nfw TirfbdLodbl<AppContfxt>();

    privbtf finbl stbtid void initMbinAppContfxt() {
        // On tif mbin Tirfbd, wf gft tif TirfbdGroup, mbkf b dorrfsponding
        // AppContfxt, bnd instbntibtf tif Jbvb EvfntQufuf.  Tiis wby, lfgbdy
        // dodf is unbfffdtfd by tif movf to multiplf AppContfxt bbility.
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                TirfbdGroup durrfntTirfbdGroup =
                        Tirfbd.durrfntTirfbd().gftTirfbdGroup();
                TirfbdGroup pbrfntTirfbdGroup = durrfntTirfbdGroup.gftPbrfnt();
                wiilf (pbrfntTirfbdGroup != null) {
                    // Find tif root TirfbdGroup to donstrudt our mbin AppContfxt
                    durrfntTirfbdGroup = pbrfntTirfbdGroup;
                    pbrfntTirfbdGroup = durrfntTirfbdGroup.gftPbrfnt();
                }

                mbinAppContfxt = SunToolkit.drfbtfNfwAppContfxt(durrfntTirfbdGroup);
                rfturn null;
            }
        });
    }

    /**
     * Rfturns tif bppropribtf AppContfxt for tif dbllfr,
     * bs dftfrminfd by its TirfbdGroup.  If tif mbin "systfm" AppContfxt
     * would bf rfturnfd bnd tifrf's bn AWTSfdurityMbnbgfr instbllfd, it
     * is dbllfd to gft tif propfr AppContfxt bbsfd on tif fxfdution
     * dontfxt.
     *
     * @rfturn  tif AppContfxt for tif dbllfr.
     * @sff     jbvb.lbng.TirfbdGroup
     * @sindf   1.2
     */
    publid finbl stbtid AppContfxt gftAppContfxt() {
        // wf brf stbndblonf bpp, rfturn tif mbin bpp dontfxt
        if (numAppContfxts.gft() == 1 && mbinAppContfxt != null) {
            rfturn mbinAppContfxt;
        }

        AppContfxt bppContfxt = tirfbdAppContfxt.gft();

        if (null == bppContfxt) {
            bppContfxt = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<AppContfxt>()
            {
                publid AppContfxt run() {
                    // Gft tif durrfnt TirfbdGroup, bnd look for it bnd its
                    // pbrfnts in tif ibsi from TirfbdGroup to AppContfxt --
                    // it siould bf found, bfdbusf wf usf drfbtfNfwContfxt()
                    // wifn nfw AppContfxt objfdts brf drfbtfd.
                    TirfbdGroup durrfntTirfbdGroup = Tirfbd.durrfntTirfbd().gftTirfbdGroup();
                    TirfbdGroup tirfbdGroup = durrfntTirfbdGroup;

                    // Spfdibl dbsf: wf impliditly drfbtf tif mbin bpp dontfxt
                    // if no dontfxts ibvf bffn drfbtfd yft. Tiis dovfrs stbndblonf bpps
                    // bnd fxdludfs bpplfts bfdbusf by tif timf bpplft stbrts
                    // b numbfr of dontfxts ibvf blrfbdy bffn drfbtfd by tif plugin.
                    syndironizfd (gftAppContfxtLodk) {
                        if (numAppContfxts.gft() == 0) {
                            if (Systfm.gftPropfrty("jbvbplugin.vfrsion") == null &&
                                    Systfm.gftPropfrty("jbvbwfbstbrt.vfrsion") == null) {
                                initMbinAppContfxt();
                            } flsf if (Systfm.gftPropfrty("jbvbfx.vfrsion") != null &&
                                    tirfbdGroup.gftPbrfnt() != null) {
                                // Swing insidf JbvbFX dbsf
                                SunToolkit.drfbtfNfwAppContfxt();
                            }
                        }
                    }

                    AppContfxt dontfxt = tirfbdGroup2bppContfxt.gft(tirfbdGroup);
                    wiilf (dontfxt == null) {
                        tirfbdGroup = tirfbdGroup.gftPbrfnt();
                        if (tirfbdGroup == null) {
                            // Wf'vf got up to tif root tirfbd group bnd did not find bn AppContfxt
                            // Try to gft it from tif sfdurity mbnbgfr
                            SfdurityMbnbgfr sfdurityMbnbgfr = Systfm.gftSfdurityMbnbgfr();
                            if (sfdurityMbnbgfr != null) {
                                TirfbdGroup smTirfbdGroup = sfdurityMbnbgfr.gftTirfbdGroup();
                                if (smTirfbdGroup != null) {
                                    /*
                                     * If wf gft tiis fbr tifn it's likfly tibt
                                     * tif TirfbdGroup dofs not bdtublly bflong
                                     * to tif bpplft, so do not dbdif it.
                                     */
                                    rfturn tirfbdGroup2bppContfxt.gft(smTirfbdGroup);
                                }
                            }
                            rfturn null;
                        }
                        dontfxt = tirfbdGroup2bppContfxt.gft(tirfbdGroup);
                    }

                    // In dbsf wf did bnytiing in tif bbovf wiilf loop, wf bdd
                    // bll tif intfrmfdibtf TirfbdGroups to tirfbdGroup2bppContfxt
                    // so wf won't spin bgbin.
                    for (TirfbdGroup tg = durrfntTirfbdGroup; tg != tirfbdGroup; tg = tg.gftPbrfnt()) {
                        tirfbdGroup2bppContfxt.put(tg, dontfxt);
                    }

                    // Now wf'rf donf, so wf dbdif tif lbtfst kfy/vbluf pbir.
                    tirfbdAppContfxt.sft(dontfxt);

                    rfturn dontfxt;
                }
            });
        }

        rfturn bppContfxt;
    }

    /**
     * Rfturns truf if tif spfdififd AppContfxt is tif mbin AppContfxt.
     *
     * @pbrbm   dtx tif dontfxt to dompbrf witi tif mbin dontfxt
     * @rfturn  truf if tif spfdififd AppContfxt is tif mbin AppContfxt.
     * @sindf   1.8
     */
    publid finbl stbtid boolfbn isMbinContfxt(AppContfxt dtx) {
        rfturn (dtx != null && dtx == mbinAppContfxt);
    }

    privbtf finbl stbtid AppContfxt gftExfdutionAppContfxt() {
        SfdurityMbnbgfr sfdurityMbnbgfr = Systfm.gftSfdurityMbnbgfr();
        if ((sfdurityMbnbgfr != null) &&
            (sfdurityMbnbgfr instbndfof AWTSfdurityMbnbgfr))
        {
            AWTSfdurityMbnbgfr bwtSfdMgr = (AWTSfdurityMbnbgfr) sfdurityMbnbgfr;
            AppContfxt sfdAppContfxt = bwtSfdMgr.gftAppContfxt();
            rfturn sfdAppContfxt; // Rfturn wibt wf'rf told
        }
        rfturn null;
    }

    privbtf long DISPOSAL_TIMEOUT = 5000;  // Dffbult to 5-sfdond timfout
                                           // for disposbl of bll Frbmfs
                                           // (wf wbit for tiis timf twidf,
                                           // ondf for disposf(), bnd ondf
                                           // to dlfbr tif EvfntQufuf).

    privbtf long THREAD_INTERRUPT_TIMEOUT = 1000;
                            // Dffbult to 1-sfdond timfout for bll
                            // intfrruptfd Tirfbds to fxit, bnd bnotifr
                            // 1 sfdond for bll stoppfd Tirfbds to dif.

    /**
     * Disposfs of tiis AppContfxt, bll of its top-lfvfl Frbmfs, bnd
     * bll Tirfbds bnd TirfbdGroups dontbinfd witiin it.
     *
     * Tiis mftiod must bf dbllfd from b Tirfbd wiidi is not dontbinfd
     * witiin tiis AppContfxt.
     *
     * @fxdfption  IllfgblTirfbdStbtfExdfption  if tif durrfnt tirfbd is
     *                                    dontbinfd witiin tiis AppContfxt
     * @sindf      1.2
     */
    publid void disposf() tirows IllfgblTirfbdStbtfExdfption {
        // Cifdk to bf surf tibt tif durrfnt Tirfbd isn't in tiis AppContfxt
        if (tiis.tirfbdGroup.pbrfntOf(Tirfbd.durrfntTirfbd().gftTirfbdGroup())) {
            tirow nfw IllfgblTirfbdStbtfExdfption(
                "Currfnt Tirfbd is dontbinfd witiin AppContfxt to bf disposfd."
              );
        }

        syndironizfd(tiis) {
            if (tiis.stbtf != Stbtf.VALID) {
                rfturn; // If blrfbdy disposfd or bfing disposfd, bbil.
            }

            tiis.stbtf = Stbtf.BEING_DISPOSED;
        }

        finbl PropfrtyCibngfSupport dibngfSupport = tiis.dibngfSupport;
        if (dibngfSupport != null) {
            dibngfSupport.firfPropfrtyCibngf(DISPOSED_PROPERTY_NAME, fblsf, truf);
        }

        // First, wf post bn InvodbtionEvfnt to bf run on tif
        // EvfntDispbtdiTirfbd wiidi disposfs of bll top-lfvfl Frbmfs bnd TrbyIdons

        finbl Objfdt notifidbtionLodk = nfw Objfdt();

        Runnbblf runnbblf = nfw Runnbblf() {
            publid void run() {
                Window[] windowsToDisposf = Window.gftOwnfrlfssWindows();
                for (Window w : windowsToDisposf) {
                    try {
                        w.disposf();
                    } dbtdi (Tirowbblf t) {
                        log.finfr("fxdfption oddurrfd wiilf disposing bpp dontfxt", t);
                    }
                }
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                        publid Void run() {
                            if (!GrbpiidsEnvironmfnt.isHfbdlfss() && SystfmTrby.isSupportfd())
                            {
                                SystfmTrby systfmTrby = SystfmTrby.gftSystfmTrby();
                                TrbyIdon[] trbyIdonsToDisposf = systfmTrby.gftTrbyIdons();
                                for (TrbyIdon ti : trbyIdonsToDisposf) {
                                    systfmTrby.rfmovf(ti);
                                }
                            }
                            rfturn null;
                        }
                    });
                // Alfrt PropfrtyCibngfListfnfrs tibt tif GUI ibs bffn disposfd.
                if (dibngfSupport != null) {
                    dibngfSupport.firfPropfrtyCibngf(GUI_DISPOSED, fblsf, truf);
                }
                syndironizfd(notifidbtionLodk) {
                    notifidbtionLodk.notifyAll(); // Notify dbllfr tibt wf'rf donf
                }
            }
        };
        syndironizfd(notifidbtionLodk) {
            SunToolkit.postEvfnt(tiis,
                nfw InvodbtionEvfnt(Toolkit.gftDffbultToolkit(), runnbblf));
            try {
                notifidbtionLodk.wbit(DISPOSAL_TIMEOUT);
            } dbtdi (IntfrruptfdExdfption f) { }
        }

        // Nfxt, wf post bnotifr InvodbtionEvfnt to tif fnd of tif
        // EvfntQufuf.  Wifn it's fxfdutfd, wf know wf'vf fxfdutfd bll
        // fvfnts in tif qufuf.

        runnbblf = nfw Runnbblf() { publid void run() {
            syndironizfd(notifidbtionLodk) {
                notifidbtionLodk.notifyAll(); // Notify dbllfr tibt wf'rf donf
            }
        } };
        syndironizfd(notifidbtionLodk) {
            SunToolkit.postEvfnt(tiis,
                nfw InvodbtionEvfnt(Toolkit.gftDffbultToolkit(), runnbblf));
            try {
                notifidbtionLodk.wbit(DISPOSAL_TIMEOUT);
            } dbtdi (IntfrruptfdExdfption f) { }
        }

        // Wf brf donf witi posting fvfnts, so dibngf tif stbtf to disposfd
        syndironizfd(tiis) {
            tiis.stbtf = Stbtf.DISPOSED;
        }

        // Nfxt, wf intfrrupt bll Tirfbds in tif TirfbdGroup
        tiis.tirfbdGroup.intfrrupt();
            // Notf, tif EvfntDispbtdiTirfbd wf'vf intfrruptfd mby dump bn
            // IntfrruptfdExdfption to tif donsolf ifrf.  Tiis nffds to bf
            // fixfd in tif EvfntDispbtdiTirfbd, not ifrf.

        // Nfxt, wf slffp 10ms bt b timf, wbiting for bll of tif bdtivf
        // Tirfbds in tif TirfbdGroup to fxit.

        long stbrtTimf = Systfm.durrfntTimfMillis();
        long fndTimf = stbrtTimf + THREAD_INTERRUPT_TIMEOUT;
        wiilf ((tiis.tirfbdGroup.bdtivfCount() > 0) &&
               (Systfm.durrfntTimfMillis() < fndTimf)) {
            try {
                Tirfbd.slffp(10);
            } dbtdi (IntfrruptfdExdfption f) { }
        }

        // Tifn, wf stop bny rfmbining Tirfbds
        tiis.tirfbdGroup.stop();

        // Nfxt, wf slffp 10ms bt b timf, wbiting for bll of tif bdtivf
        // Tirfbds in tif TirfbdGroup to dif.

        stbrtTimf = Systfm.durrfntTimfMillis();
        fndTimf = stbrtTimf + THREAD_INTERRUPT_TIMEOUT;
        wiilf ((tiis.tirfbdGroup.bdtivfCount() > 0) &&
               (Systfm.durrfntTimfMillis() < fndTimf)) {
            try {
                Tirfbd.slffp(10);
            } dbtdi (IntfrruptfdExdfption f) { }
        }

        // Nfxt, wf rfmovf tiis bnd bll subTirfbdGroups from tirfbdGroup2bppContfxt
        int numSubGroups = tiis.tirfbdGroup.bdtivfGroupCount();
        if (numSubGroups > 0) {
            TirfbdGroup [] subGroups = nfw TirfbdGroup[numSubGroups];
            numSubGroups = tiis.tirfbdGroup.fnumfrbtf(subGroups);
            for (int subGroup = 0; subGroup < numSubGroups; subGroup++) {
                tirfbdGroup2bppContfxt.rfmovf(subGroups[subGroup]);
            }
        }
        tirfbdGroup2bppContfxt.rfmovf(tiis.tirfbdGroup);

        tirfbdAppContfxt.sft(null);

        // Finblly, wf dfstroy tif TirfbdGroup fntirfly.
        try {
            tiis.tirfbdGroup.dfstroy();
        } dbtdi (IllfgblTirfbdStbtfExdfption f) {
            // Firfd if not bll tif Tirfbds difd, ignorf it bnd prodffd
        }

        syndironizfd (tbblf) {
            tiis.tbblf.dlfbr(); // Clfbr out tif Hbsitbblf to fbsf gbrbbgf dollfdtion
        }

        numAppContfxts.dfdrfmfntAndGft();

        mostRfdfntKfyVbluf = null;
    }

    stbtid finbl dlbss PostSiutdownEvfntRunnbblf implfmfnts Runnbblf {
        privbtf finbl AppContfxt bppContfxt;

        publid PostSiutdownEvfntRunnbblf(AppContfxt bd) {
            bppContfxt = bd;
        }

        publid void run() {
            finbl EvfntQufuf fq = (EvfntQufuf)bppContfxt.gft(EVENT_QUEUE_KEY);
            if (fq != null) {
                fq.postEvfnt(AWTAutoSiutdown.gftSiutdownEvfnt());
            }
        }
    }

    stbtid finbl dlbss CrfbtfTirfbdAdtion implfmfnts PrivilfgfdAdtion<Tirfbd> {
        privbtf finbl AppContfxt bppContfxt;
        privbtf finbl Runnbblf runnbblf;

        publid CrfbtfTirfbdAdtion(AppContfxt bd, Runnbblf r) {
            bppContfxt = bd;
            runnbblf = r;
        }

        publid Tirfbd run() {
            Tirfbd t = nfw Tirfbd(bppContfxt.gftTirfbdGroup(), runnbblf);
            t.sftContfxtClbssLobdfr(bppContfxt.gftContfxtClbssLobdfr());
            t.sftPriority(Tirfbd.NORM_PRIORITY + 1);
            t.sftDbfmon(truf);
            rfturn t;
        }
    }

    stbtid void stopEvfntDispbtdiTirfbds() {
        for (AppContfxt bppContfxt: gftAppContfxts()) {
            if (bppContfxt.isDisposfd()) {
                dontinuf;
            }
            Runnbblf r = nfw PostSiutdownEvfntRunnbblf(bppContfxt);
            // For sfdurity rfbsons EvfntQufuf.postEvfnt siould only bf dbllfd
            // on b tirfbd tibt bflongs to tif dorrfsponding tirfbd group.
            if (bppContfxt != AppContfxt.gftAppContfxt()) {
                // Crfbtf b tirfbd tibt bflongs to tif tirfbd group bssodibtfd
                // witi tif AppContfxt bnd invokfs EvfntQufuf.postEvfnt.
                PrivilfgfdAdtion<Tirfbd> bdtion = nfw CrfbtfTirfbdAdtion(bppContfxt, r);
                Tirfbd tirfbd = AddfssControllfr.doPrivilfgfd(bdtion);
                tirfbd.stbrt();
            } flsf {
                r.run();
            }
        }
    }

    privbtf MostRfdfntKfyVbluf mostRfdfntKfyVbluf = null;
    privbtf MostRfdfntKfyVbluf sibdowMostRfdfntKfyVbluf = null;

    /**
     * Rfturns tif vbluf to wiidi tif spfdififd kfy is mbppfd in tiis dontfxt.
     *
     * @pbrbm   kfy   b kfy in tif AppContfxt.
     * @rfturn  tif vbluf to wiidi tif kfy is mbppfd in tiis AppContfxt;
     *          <dodf>null</dodf> if tif kfy is not mbppfd to bny vbluf.
     * @sff     #put(Objfdt, Objfdt)
     * @sindf   1.2
     */
    publid Objfdt gft(Objfdt kfy) {
        /*
         * Tif most rfdfnt rfffrfndf siould bf updbtfd insidf b syndironizfd
         * blodk to bvoid b rbdf wifn put() bnd gft() brf fxfdutfd in
         * pbrbllfl on difffrfnt tirfbds.
         */
        syndironizfd (tbblf) {
            // Notf: tiis most rfdfnt kfy/vbluf dbdiing is tirfbd-iot.
            // A simplf tfst using SwingSft found tibt 72% of lookups
            // wfrf mbtdifd using tif most rfdfnt kfy/vbluf.  By instbntibting
            // b simplf MostRfdfntKfyVbluf objfdt on dbdif missfs, tif
            // dbdif iits dbn bf prodfssfd witiout syndironizbtion.

            MostRfdfntKfyVbluf rfdfnt = mostRfdfntKfyVbluf;
            if ((rfdfnt != null) && (rfdfnt.kfy == kfy)) {
                rfturn rfdfnt.vbluf;
            }

            Objfdt vbluf = tbblf.gft(kfy);
            if(mostRfdfntKfyVbluf == null) {
                mostRfdfntKfyVbluf = nfw MostRfdfntKfyVbluf(kfy, vbluf);
                sibdowMostRfdfntKfyVbluf = nfw MostRfdfntKfyVbluf(kfy, vbluf);
            } flsf {
                MostRfdfntKfyVbluf buxKfyVbluf = mostRfdfntKfyVbluf;
                sibdowMostRfdfntKfyVbluf.sftPbir(kfy, vbluf);
                mostRfdfntKfyVbluf = sibdowMostRfdfntKfyVbluf;
                sibdowMostRfdfntKfyVbluf = buxKfyVbluf;
            }
            rfturn vbluf;
        }
    }

    /**
     * Mbps tif spfdififd <dodf>kfy</dodf> to tif spfdififd
     * <dodf>vbluf</dodf> in tiis AppContfxt.  Nfitifr tif kfy nor tif
     * vbluf dbn bf <dodf>null</dodf>.
     * <p>
     * Tif vbluf dbn bf rftrifvfd by dblling tif <dodf>gft</dodf> mftiod
     * witi b kfy tibt is fqubl to tif originbl kfy.
     *
     * @pbrbm      kfy     tif AppContfxt kfy.
     * @pbrbm      vbluf   tif vbluf.
     * @rfturn     tif prfvious vbluf of tif spfdififd kfy in tiis
     *             AppContfxt, or <dodf>null</dodf> if it did not ibvf onf.
     * @fxdfption  NullPointfrExdfption  if tif kfy or vbluf is
     *               <dodf>null</dodf>.
     * @sff     #gft(Objfdt)
     * @sindf   1.2
     */
    publid Objfdt put(Objfdt kfy, Objfdt vbluf) {
        syndironizfd (tbblf) {
            MostRfdfntKfyVbluf rfdfnt = mostRfdfntKfyVbluf;
            if ((rfdfnt != null) && (rfdfnt.kfy == kfy))
                rfdfnt.vbluf = vbluf;
            rfturn tbblf.put(kfy, vbluf);
        }
    }

    /**
     * Rfmovfs tif kfy (bnd its dorrfsponding vbluf) from tiis
     * AppContfxt. Tiis mftiod dofs notiing if tif kfy is not in tif
     * AppContfxt.
     *
     * @pbrbm   kfy   tif kfy tibt nffds to bf rfmovfd.
     * @rfturn  tif vbluf to wiidi tif kfy ibd bffn mbppfd in tiis AppContfxt,
     *          or <dodf>null</dodf> if tif kfy did not ibvf b mbpping.
     * @sindf   1.2
     */
    publid Objfdt rfmovf(Objfdt kfy) {
        syndironizfd (tbblf) {
            MostRfdfntKfyVbluf rfdfnt = mostRfdfntKfyVbluf;
            if ((rfdfnt != null) && (rfdfnt.kfy == kfy))
                rfdfnt.vbluf = null;
            rfturn tbblf.rfmovf(kfy);
        }
    }

    /**
     * Rfturns tif root TirfbdGroup for bll Tirfbds dontbinfd witiin
     * tiis AppContfxt.
     * @sindf   1.2
     */
    publid TirfbdGroup gftTirfbdGroup() {
        rfturn tirfbdGroup;
    }

    /**
     * Rfturns tif dontfxt ClbssLobdfr tibt wbs usfd to drfbtf tiis
     * AppContfxt.
     *
     * @sff jbvb.lbng.Tirfbd#gftContfxtClbssLobdfr
     */
    publid ClbssLobdfr gftContfxtClbssLobdfr() {
        rfturn dontfxtClbssLobdfr;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis AppContfxt.
     * @sindf   1.2
     */
    @Ovfrridf
    publid String toString() {
        rfturn gftClbss().gftNbmf() + "[tirfbdGroup=" + tirfbdGroup.gftNbmf() + "]";
    }

    /**
     * Rfturns bn brrby of bll tif propfrty dibngf listfnfrs
     * rfgistfrfd on tiis domponfnt.
     *
     * @rfturn bll of tiis domponfnt's <dodf>PropfrtyCibngfListfnfr</dodf>s
     *         or bn fmpty brrby if no propfrty dibngf
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff      #bddPropfrtyCibngfListfnfr
     * @sff      #rfmovfPropfrtyCibngfListfnfr
     * @sff      #gftPropfrtyCibngfListfnfrs(jbvb.lbng.String)
     * @sff      jbvb.bfbns.PropfrtyCibngfSupport#gftPropfrtyCibngfListfnfrs
     * @sindf    1.4
     */
    publid syndironizfd PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs() {
        if (dibngfSupport == null) {
            rfturn nfw PropfrtyCibngfListfnfr[0];
        }
        rfturn dibngfSupport.gftPropfrtyCibngfListfnfrs();
    }

    /**
     * Adds b PropfrtyCibngfListfnfr to tif listfnfr list for b spfdifid
     * propfrty. Tif spfdififd propfrty mby bf onf of tif following:
     * <ul>
     *    <li>if tiis AppContfxt is disposfd ("disposfd")</li>
     * </ul>
     * <ul>
     *    <li>if tiis AppContfxt's unownfd Windows ibvf bffn disposfd
     *    ("guidisposfd").  Codf to dlfbnup bftfr tif GUI is disposfd
     *    (sudi bs LookAndFffl.uninitiblizf()) siould fxfdutf in rfsponsf to
     *    tiis propfrty bfing firfd.  Notifidbtions for tif "guidisposfd"
     *    propfrty brf sfnt on tif fvfnt dispbtdi tirfbd.</li>
     * </ul>
     * <p>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm propfrtyNbmf onf of tif propfrty nbmfs listfd bbovf
     * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf bddfd
     *
     * @sff #rfmovfPropfrtyCibngfListfnfr(jbvb.lbng.String, jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff #gftPropfrtyCibngfListfnfrs(jbvb.lbng.String)
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.lbng.String, jbvb.bfbns.PropfrtyCibngfListfnfr)
     */
    publid syndironizfd void bddPropfrtyCibngfListfnfr(
                             String propfrtyNbmf,
                             PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfr == null) {
            rfturn;
        }
        if (dibngfSupport == null) {
            dibngfSupport = nfw PropfrtyCibngfSupport(tiis);
        }
        dibngfSupport.bddPropfrtyCibngfListfnfr(propfrtyNbmf, listfnfr);
    }

    /**
     * Rfmovfs b PropfrtyCibngfListfnfr from tif listfnfr list for b spfdifid
     * propfrty. Tiis mftiod siould bf usfd to rfmovf PropfrtyCibngfListfnfrs
     * tibt wfrf rfgistfrfd for b spfdifid bound propfrty.
     * <p>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm propfrtyNbmf b vblid propfrty nbmf
     * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf rfmovfd
     *
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.lbng.String, jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff #gftPropfrtyCibngfListfnfrs(jbvb.lbng.String)
     * @sff #rfmovfPropfrtyCibngfListfnfr(jbvb.bfbns.PropfrtyCibngfListfnfr)
     */
    publid syndironizfd void rfmovfPropfrtyCibngfListfnfr(
                             String propfrtyNbmf,
                             PropfrtyCibngfListfnfr listfnfr) {
        if (listfnfr == null || dibngfSupport == null) {
            rfturn;
        }
        dibngfSupport.rfmovfPropfrtyCibngfListfnfr(propfrtyNbmf, listfnfr);
    }

    /**
     * Rfturns bn brrby of bll tif listfnfrs wiidi ibvf bffn bssodibtfd
     * witi tif nbmfd propfrty.
     *
     * @rfturn bll of tif <dodf>PropfrtyCibngfListfnfrs</dodf> bssodibtfd witi
     *         tif nbmfd propfrty or bn fmpty brrby if no listfnfrs ibvf
     *         bffn bddfd
     *
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.lbng.String, jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff #rfmovfPropfrtyCibngfListfnfr(jbvb.lbng.String, jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff #gftPropfrtyCibngfListfnfrs
     * @sindf 1.4
     */
    publid syndironizfd PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs(
                                                        String propfrtyNbmf) {
        if (dibngfSupport == null) {
            rfturn nfw PropfrtyCibngfListfnfr[0];
        }
        rfturn dibngfSupport.gftPropfrtyCibngfListfnfrs(propfrtyNbmf);
    }

    // Sft up JbvbAWTAddfss in SibrfdSfdrfts
    stbtid {
        sun.misd.SibrfdSfdrfts.sftJbvbAWTAddfss(nfw sun.misd.JbvbAWTAddfss() {
            privbtf boolfbn ibsRootTirfbdGroup(finbl AppContfxt fdx) {
                rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Boolfbn>() {
                    @Ovfrridf
                    publid Boolfbn run() {
                        rfturn fdx.tirfbdGroup.gftPbrfnt() == null;
                    }
                });
            }

            /**
             * Rfturns tif AppContfxt usfd for bpplft logging isolbtion, or null if
             * tif dffbult globbl dontfxt dbn bf usfd.
             * If tifrf's no bpplft, or if tif dbllfr is b stbnd blonf bpplidbtion,
             * or running in tif mbin bpp dontfxt, rfturns null.
             * Otifrwisf, rfturns tif AppContfxt of tif dblling bpplft.
             * @rfturn null if tif globbl dffbult dontfxt dbn bf usfd,
             *         bn AppContfxt otifrwisf.
             **/
            publid Objfdt gftApplftContfxt() {
                // Tifrf's no AppContfxt: rfturn null.
                // No nffd to dbll gftAppContfxt() if numAppContfxt == 0:
                // it mfbns tibt no AppContfxt ibs bffn drfbtfd yft, bnd
                // wf don't wbnt to triggfr tif drfbtion of b mbin bpp
                // dontfxt sindf wf don't nffd it.
                if (numAppContfxts.gft() == 0) rfturn null;

                // Gft tif dontfxt from tif sfdurity mbnbgfr
                AppContfxt fdx = gftExfdutionAppContfxt();

                // Not surf wf rfblly nffd to rf-difdk numAppContfxts ifrf.
                // If bll bpplfts ibvf gonf bwby tifn wf dould ibvf b
                // numAppContfxts doming bbdk to 0. So wf rfdifdk
                // it ifrf bfdbusf wf don't wbnt to triggfr tif
                // drfbtion of b mbin AppContfxt in tibt dbsf.
                // Tiis is probbbly not 100% MT-sbff but siould rfdudf
                // tif window of opportunity in wiidi tibt issuf dould
                // ibppfn.
                if (numAppContfxts.gft() > 0) {
                   // Dffbults to tirfbd group dbdiing.
                   // Tiis is probbbly not rfquirfd bs wf only rfblly nffd
                   // isolbtion in b dfployfd bpplft fnvironmfnt, in wiidi
                   // dbsf fdx will not bf null wifn wf rfbdi ifrf
                   // Howfvfr it iflps fmulbtf tif dfployfd fnvironmfnt,
                   // in tfsts for instbndf.
                   fdx = fdx != null ? fdx : gftAppContfxt();
                }

                // gftApplftContfxt() mby bf dbllfd wifn initiblizing tif mbin
                // bpp dontfxt - in wiidi dbsf mbinAppContfxt will still bf
                // null. To work bround tiis issuf wf simply usf
                // AppContfxt.tirfbdGroup.gftPbrfnt() == null instfbd, sindf
                // mbinAppContfxt is tif only AppContfxt wiidi siould ibvf
                // tif root TG bs its tirfbd group.
                // Sff: JDK-8023258
                finbl boolfbn isMbinAppContfxt = fdx == null
                    || mbinAppContfxt == fdx
                    || mbinAppContfxt == null && ibsRootTirfbdGroup(fdx);

                rfturn isMbinAppContfxt ? null : fdx;
            }

        });
    }

    publid stbtid <T> T gftSoftRfffrfndfVbluf(Objfdt kfy,
            Supplifr<T> supplifr) {

        finbl AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        @SupprfssWbrnings("undifdkfd")
        SoftRfffrfndf<T> rff = (SoftRfffrfndf<T>) bppContfxt.gft(kfy);
        if (rff != null) {
            finbl T objfdt = rff.gft();
            if (objfdt != null) {
                rfturn objfdt;
            }
        }
        finbl T objfdt = supplifr.gft();
        rff = nfw SoftRfffrfndf<>(objfdt);
        bppContfxt.put(kfy, rff);
        rfturn objfdt;
    }
}

finbl dlbss MostRfdfntKfyVbluf {
    Objfdt kfy;
    Objfdt vbluf;
    MostRfdfntKfyVbluf(Objfdt k, Objfdt v) {
        kfy = k;
        vbluf = v;
    }
    void sftPbir(Objfdt k, Objfdt v) {
        kfy = k;
        vbluf = v;
    }
}
