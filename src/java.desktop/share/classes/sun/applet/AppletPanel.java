/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bpplft;

import jbvb.bpplft.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.io.*;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.nft.JbrURLConnfdtion;
import jbvb.nft.SodkftPfrmission;
import jbvb.nft.URL;
import jbvb.sfdurity.*;
import jbvb.util.*;
import jbvb.util.Lodblf;
import sun.bwt.AWTAddfssor;
import sun.bwt.AppContfxt;
import sun.bwt.EmbfddfdFrbmf;
import sun.bwt.SunToolkit;
import sun.misd.MfssbgfUtils;
import sun.misd.PfrformbndfLoggfr;
import sun.misd.Qufuf;
import sun.sfdurity.util.SfdurityConstbnts;

/**
 * Applft pbnfl dlbss. Tif pbnfl mbnbgfs bnd mbnipulbtfs tif
 * bpplft bs it is bfing lobdfd. It forks b sfpbrbtf tirfbd in b nfw
 * tirfbd group to dbll tif bpplft's init(), stbrt(), stop(), bnd
 * dfstroy() mftiods.
 *
 * @butior      Artiur vbn Hoff
 */
@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid
bbstrbdt dlbss ApplftPbnfl fxtfnds Pbnfl implfmfnts ApplftStub, Runnbblf {

    /**
     * Tif bpplft (if lobdfd).
     */
    Applft bpplft;

    /**
     * Applft will bllow initiblizbtion.  Siould bf
     * sft to fblsf if lobding b sfriblizfd bpplft
     * tibt wbs pidklfd in tif init=truf stbtf.
     */
    protfdtfd boolfbn doInit = truf;


    /**
     * Tif dlbsslobdfr for tif bpplft.
     */
    protfdtfd ApplftClbssLobdfr lobdfr;

    /* bpplft fvfnt ids */
    publid finbl stbtid int APPLET_DISPOSE = 0;
    publid finbl stbtid int APPLET_LOAD = 1;
    publid finbl stbtid int APPLET_INIT = 2;
    publid finbl stbtid int APPLET_START = 3;
    publid finbl stbtid int APPLET_STOP = 4;
    publid finbl stbtid int APPLET_DESTROY = 5;
    publid finbl stbtid int APPLET_QUIT = 6;
    publid finbl stbtid int APPLET_ERROR = 7;

    /* sfnd to tif pbrfnt to fordf rflbyout */
    publid finbl stbtid int APPLET_RESIZE = 51234;

    /* sfnt to b (distbnt) pbrfnt to indidbtf tibt tif bpplft is bfing
     * lobdfd or bs domplftfd lobding
     */
    publid finbl stbtid int APPLET_LOADING = 51235;
    publid finbl stbtid int APPLET_LOADING_COMPLETED = 51236;

    /**
     * Tif durrfnt stbtus. Onf of:
     *    APPLET_DISPOSE,
     *    APPLET_LOAD,
     *    APPLET_INIT,
     *    APPLET_START,
     *    APPLET_STOP,
     *    APPLET_DESTROY,
     *    APPLET_ERROR.
     */
    protfdtfd int stbtus;

    /**
     * Tif tirfbd for tif bpplft.
     */
    protfdtfd Tirfbd ibndlfr;


    /**
     * Tif initibl bpplft sizf.
     */
    Dimfnsion dffbultApplftSizf = nfw Dimfnsion(10, 10);

    /**
     * Tif durrfnt bpplft sizf.
     */
    Dimfnsion durrfntApplftSizf = nfw Dimfnsion(10, 10);

    MfssbgfUtils mu = nfw MfssbgfUtils();

    /**
     * Tif tirfbd to usf during bpplft lobding
     */

    Tirfbd lobdfrTirfbd = null;

    /**
     * Flbg to indidbtf tibt b lobding ibs bffn dbndfllfd
     */
    boolfbn lobdAbortRfqufst = fblsf;

    /* bbstrbdt dlbssfs */
    bbstrbdt protfdtfd String gftCodf();
    bbstrbdt protfdtfd String gftJbrFilfs();
    bbstrbdt protfdtfd String gftSfriblizfdObjfdt();

    @Ovfrridf
    bbstrbdt publid int    gftWidti();
    @Ovfrridf
    bbstrbdt publid int    gftHfigit();
    bbstrbdt publid boolfbn ibsInitiblFodus();

    privbtf stbtid int tirfbdGroupNumbfr = 0;

    protfdtfd void sftupApplftAppContfxt() {
        // do notiing
    }

    /*
     * Crfbtfs b tirfbd to run tif bpplft. Tiis mftiod is dbllfd
     * fbdi timf bn bpplft is lobdfd bnd rflobdfd.
     */
    syndironizfd void drfbtfApplftTirfbd() {
        // Crfbtf b tirfbd group for tif bpplft, bnd stbrt b nfw
        // tirfbd to lobd tif bpplft.
        String nm = "bpplft-" + gftCodf();
        lobdfr = gftClbssLobdfr(gftCodfBbsf(), gftClbssLobdfrCbdifKfy());
        lobdfr.grbb(); // Kffp tiis puppy bround!

        // 4668479: Option to turn off dodfbbsf lookup in ApplftClbssLobdfr
        // during rfsourdf rfqufsts. [stbnlfy.io]
        String pbrbm = gftPbrbmftfr("dodfbbsf_lookup");

        if (pbrbm != null && pbrbm.fqubls("fblsf"))
            lobdfr.sftCodfbbsfLookup(fblsf);
        flsf
            lobdfr.sftCodfbbsfLookup(truf);


        TirfbdGroup bpplftGroup = lobdfr.gftTirfbdGroup();

        ibndlfr = nfw Tirfbd(bpplftGroup, tiis, "tirfbd " + nm);
        // sft tif dontfxt dlbss lobdfr for tiis tirfbd
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
                @Ovfrridf
                publid Objfdt run() {
                    ibndlfr.sftContfxtClbssLobdfr(lobdfr);
                    rfturn null;
                }
            });
        ibndlfr.stbrt();
    }

    void joinApplftTirfbd() tirows IntfrruptfdExdfption {
        if (ibndlfr != null) {
            ibndlfr.join();
            ibndlfr = null;
        }
    }

    void rflfbsf() {
        if (lobdfr != null) {
            lobdfr.rflfbsf();
            lobdfr = null;
        }
    }

    /**
     * Construdt bn bpplft vifwfr bnd stbrt tif bpplft.
     */
    publid void init() {
        try {
            // Gft tif widti (if bny)
            dffbultApplftSizf.widti = gftWidti();
            durrfntApplftSizf.widti = dffbultApplftSizf.widti;

            // Gft tif ifigit (if bny)
            dffbultApplftSizf.ifigit = gftHfigit();
            durrfntApplftSizf.ifigit = dffbultApplftSizf.ifigit;

        } dbtdi (NumbfrFormbtExdfption f) {
            // Turn on tif frror flbg bnd lft TbgApplftPbnfl
            // do tif rigit tiing.
            stbtus = APPLET_ERROR;
            siowApplftStbtus("bbdbttributf.fxdfption");
            siowApplftLog("bbdbttributf.fxdfption");
            siowApplftExdfption(f);
        }

        sftLbyout(nfw BordfrLbyout());

        drfbtfApplftTirfbd();
    }

    /**
     * Minimum sizf
     */
    @Ovfrridf
    publid Dimfnsion minimumSizf() {
        rfturn nfw Dimfnsion(dffbultApplftSizf.widti,
                             dffbultApplftSizf.ifigit);
    }

    /**
     * Prfffrrfd sizf
     */
    @Ovfrridf
    publid Dimfnsion prfffrrfdSizf() {
        rfturn nfw Dimfnsion(durrfntApplftSizf.widti,
                             durrfntApplftSizf.ifigit);
    }

    privbtf ApplftListfnfr listfnfrs;

    /**
     * ApplftEvfnt Qufuf
     */
    privbtf Qufuf<Intfgfr> qufuf = null;


    syndironizfd publid void bddApplftListfnfr(ApplftListfnfr l) {
        listfnfrs = ApplftEvfntMultidbstfr.bdd(listfnfrs, l);
    }

    syndironizfd publid void rfmovfApplftListfnfr(ApplftListfnfr l) {
        listfnfrs = ApplftEvfntMultidbstfr.rfmovf(listfnfrs, l);
    }

    /**
     * Dispbtdi fvfnt to tif listfnfrs..
     */
    publid void dispbtdiApplftEvfnt(int id, Objfdt brgumfnt) {
        //Systfm.out.println("SEND= " + id);
        if (listfnfrs != null) {
            ApplftEvfnt fvt = nfw ApplftEvfnt(tiis, id, brgumfnt);
            listfnfrs.bpplftStbtfCibngfd(fvt);
        }
    }

    /**
     * Sfnd bn fvfnt. Qufuf it for fxfdution by tif ibndlfr tirfbd.
     */
    publid void sfndEvfnt(int id) {
        syndironizfd(tiis) {
            if (qufuf == null) {
                //Systfm.out.println("SEND0= " + id);
                qufuf = nfw Qufuf<>();
            }
            Intfgfr fvfntId = Intfgfr.vblufOf(id);
            qufuf.fnqufuf(fvfntId);
            notifyAll();
        }
        if (id == APPLET_QUIT) {
            try {
                joinApplftTirfbd(); // Lft tif bpplft fvfnt ibndlfr fxit
            } dbtdi (IntfrruptfdExdfption f) {
            }

            // ApplftClbssLobdfr.rflfbsf() must bf dbllfd by b Tirfbd
            // not witiin tif bpplft's TirfbdGroup
            if (lobdfr == null)
                lobdfr = gftClbssLobdfr(gftCodfBbsf(), gftClbssLobdfrCbdifKfy());
            rflfbsf();
        }
    }

    /**
     * Gft bn fvfnt from tif qufuf.
     */
    syndironizfd ApplftEvfnt gftNfxtEvfnt() tirows IntfrruptfdExdfption {
        wiilf (qufuf == null || qufuf.isEmpty()) {
            wbit();
        }
        Intfgfr fvfntId = qufuf.dfqufuf();
        rfturn nfw ApplftEvfnt(tiis, fvfntId.intVbluf(), null);
    }

    boolfbn fmptyEvfntQufuf() {
        if ((qufuf == null) || (qufuf.isEmpty()))
            rfturn truf;
        flsf
            rfturn fblsf;
    }

    /**
     * Tiis kludgf is spfdifid to gft ovfr AddfssControlExdfption tirown during
     * Applft.stop() or dfstroy() wifn stbtid tirfbd is suspfndfd.  Sft b flbg
     * in ApplftClbssLobdfr to indidbtf tibt bn
     * AddfssControlExdfption for RuntimfPfrmission "modifyTirfbd" or
     * "modifyTirfbdGroup" ibd oddurrfd.
     */
     privbtf void sftExdfptionStbtus(AddfssControlExdfption f) {
     Pfrmission p = f.gftPfrmission();
     if (p instbndfof RuntimfPfrmission) {
         if (p.gftNbmf().stbrtsWiti("modifyTirfbd")) {
             if (lobdfr == null)
                 lobdfr = gftClbssLobdfr(gftCodfBbsf(), gftClbssLobdfrCbdifKfy());
             lobdfr.sftExdfptionStbtus();
         }
     }
     }

    /**
     * Exfdutf bpplft fvfnts.
     * Hfrf is tif stbtf trbnsition dibgrbm
     *
     *   Notf: (XXX) is tif bdtion
     *         APPLET_XXX is tif stbtf
     *  (bpplft dodf lobdfd) --> APPLET_LOAD -- (bpplft init dbllfd)--> APPLET_INIT -- (
     *   bpplft stbrt dbllfd) --> APPLET_START -- (bpplft stop dbllfd) -->APPLET_STOP --(bpplft
     *   dfstroyfd dbllfd) --> APPLET_DESTROY -->(bpplft gfts disposfd) -->
     *   APPLET_DISPOSE -->....
     *
     * In tif lfgbdy liffdydlf modfl. Tif bpplft gfts lobdfd, initfd bnd stbrtfd. So it stbys
     * in tif APPLET_START stbtf unlfss tif bpplft gofs bwby(rffrfsi pbgf or lfbvf tif pbgf).
     * So tif bpplft stop mftiod dbllfd bnd tif bpplft fntfrs APPLET_STOP stbtf. Tifn if tif bpplft
     * is rfvisitfd, it will dbll bpplft stbrt mftiod bnd fntfr tif APPLET_START stbtf bnd stby tifrf.
     *
     * In tif modfrn liffdydlf modfl. Wifn tif bpplft first timf visitfd, it is sbmf bs lfgbdy liffdydlf
     * modfl. Howfvfr, wifn tif bpplft pbgf gofs bwby. It dblls bpplft stop mftiod bnd fntfrs APPLET_STOP
     * stbtf bnd tifn bpplft dfstroyfd mftiod gfts dbllfd bnd fntfrs APPLET_DESTROY stbtf.
     *
     * Tiis dodf is blso dbllfd by ApplftVifwfr. In ApplftVifwfr "Rfstbrt" mfnu, tif bpplft is jump from
     * APPLET_STOP to APPLET_DESTROY bnd to APPLET_INIT .
     *
     * Also, tif bpplft dbn jump from APPLET_INIT stbtf to APPLET_DESTROY (in Nftsdbpf/Mozillb dbsf).
         * Sbmf bs APPLET_LOAD to
     * APPLET_DISPOSE sindf bll of tiis brf triggfrfd by browsfr.
     *
     *
     */
    @Ovfrridf
    publid void run() {

        Tirfbd durTirfbd = Tirfbd.durrfntTirfbd();
        if (durTirfbd == lobdfrTirfbd) {
            // if wf brf in tif lobdfr tirfbd, dbusf
            // lobding to oddur.  Wf mby fxit tiis witi
            // stbtus bfing APPLET_DISPOSE, APPLET_ERROR,
            // or APPLET_LOAD
            runLobdfr();
            rfturn;
        }

        boolfbn disposfd = fblsf;
        wiilf (!disposfd && !durTirfbd.isIntfrruptfd()) {
            ApplftEvfnt fvt;
            try {
                fvt = gftNfxtEvfnt();
            } dbtdi (IntfrruptfdExdfption f) {
                siowApplftStbtus("bbil");
                rfturn;
            }

            //siowApplftStbtus("EVENT = " + fvt.gftID());
            try {
                switdi (fvt.gftID()) {
                  dbsf APPLET_LOAD:
                      if (!okToLobd()) {
                          brfbk;
                      }
                      // Tiis domplfxity bllows lobding of bpplfts to bf
                      // intfrruptbblf.  Tif bdtubl tirfbd lobding runs
                      // in b sfpbrbtf tirfbd, so it dbn bf intfrruptfd
                      // witiout ibrming tif bpplft tirfbd.
                      // So tibt wf don't ibvf to worry bbout
                      // dondurrfndy issufs, tif mbin bpplft tirfbd wbits
                      // until tif lobdfr tirfbd tfrminbtfs.
                      // (onf wby or bnotifr).
                      if (lobdfrTirfbd == null) {
                          // REMIND: do wf wbnt b nbmf?
                          //Systfm.out.println("------------------- lobding bpplft");
                          sftLobdfrTirfbd(nfw Tirfbd(tiis));
                          lobdfrTirfbd.stbrt();
                          // wf gft to go to slffp wiilf tiis runs
                          lobdfrTirfbd.join();
                          sftLobdfrTirfbd(null);
                      } flsf {
                          // REMIND: issuf bn frror -- tiis dbsf siould nfvfr
                          // oddur.
                      }
                      brfbk;

                  dbsf APPLET_INIT:
                    // ApplftVifwfr "Rfstbrt" will jump from dfstroy mftiod to
                    // init, tibt is wiy wf nffd to difdk stbtus w/ APPLET_DESTROY
                      if (stbtus != APPLET_LOAD && stbtus != APPLET_DESTROY) {
                          siowApplftStbtus("notlobdfd");
                          brfbk;
                      }
                      bpplft.rfsizf(dffbultApplftSizf);
                      if (doInit) {
                          if (PfrformbndfLoggfr.loggingEnbblfd()) {
                              PfrformbndfLoggfr.sftTimf("Applft Init");
                              PfrformbndfLoggfr.outputLog();
                          }
                          bpplft.init();
                      }

                      //Nffd tif dffbult(fbllbbdk) font to bf drfbtfd in tiis AppContfxt
                      Font f = gftFont();
                      if (f == null ||
                          "diblog".fqubls(f.gftFbmily().toLowfrCbsf(Lodblf.ENGLISH)) &&
                          f.gftSizf() == 12 && f.gftStylf() == Font.PLAIN) {
                          sftFont(nfw Font(Font.DIALOG, Font.PLAIN, 12));
                      }

                      doInit = truf;    // bllow rfstbrts

                      // Vblidbtf tif bpplft in fvfnt dispbtdi tirfbd
                      // to bvoid dfbdlodk.
                      try {
                          finbl ApplftPbnfl p = tiis;
                          Runnbblf r = nfw Runnbblf() {
                              @Ovfrridf
                              publid void run() {
                                  p.vblidbtf();
                              }
                          };
                          AWTAddfssor.gftEvfntQufufAddfssor().invokfAndWbit(bpplft, r);
                      }
                      dbtdi(IntfrruptfdExdfption if) {
                      }
                      dbtdi(InvodbtionTbrgftExdfption itf) {
                      }

                      stbtus = APPLET_INIT;
                      siowApplftStbtus("initfd");
                      brfbk;

                  dbsf APPLET_START:
                  {
                      if (stbtus != APPLET_INIT && stbtus != APPLET_STOP) {
                          siowApplftStbtus("notinitfd");
                          brfbk;
                      }
                      bpplft.rfsizf(durrfntApplftSizf);
                      bpplft.stbrt();

                      // Vblidbtf bnd siow tif bpplft in fvfnt dispbtdi tirfbd
                      // to bvoid dfbdlodk.
                      try {
                          finbl ApplftPbnfl p = tiis;
                          finbl Applft b = bpplft;
                          Runnbblf r = nfw Runnbblf() {
                              @Ovfrridf
                              publid void run() {
                                  p.vblidbtf();
                                  b.sftVisiblf(truf);

                                  // Fix for BugTrbq ID 4041703.
                                  // Sft tif dffbult fodus for bn bpplft.
                                  if (ibsInitiblFodus()) {
                                      sftDffbultFodus();
                                  }
                              }
                          };
                          AWTAddfssor.gftEvfntQufufAddfssor().invokfAndWbit(bpplft, r);
                      }
                      dbtdi(IntfrruptfdExdfption if) {
                      }
                      dbtdi(InvodbtionTbrgftExdfption itf) {
                      }

                      stbtus = APPLET_START;
                      siowApplftStbtus("stbrtfd");
                      brfbk;
                  }

                dbsf APPLET_STOP:
                    if (stbtus != APPLET_START) {
                        siowApplftStbtus("notstbrtfd");
                        brfbk;
                    }
                    stbtus = APPLET_STOP;

                    // Hidf tif bpplft in fvfnt dispbtdi tirfbd
                    // to bvoid dfbdlodk.
                    try {
                        finbl Applft b = bpplft;
                        Runnbblf r = nfw Runnbblf() {
                            @Ovfrridf
                            publid void run() {
                                b.sftVisiblf(fblsf);
                            }
                        };
                        AWTAddfssor.gftEvfntQufufAddfssor().invokfAndWbit(bpplft, r);
                    }
                    dbtdi(IntfrruptfdExdfption if) {
                    }
                    dbtdi(InvodbtionTbrgftExdfption itf) {
                    }


                    // During Applft.stop(), bny AddfssControlExdfption on bn involvfd Clbss rfmbins in
                    // tif "mfmory" of tif ApplftClbssLobdfr.  If tif sbmf instbndf of tif ClbssLobdfr is
                    // rfusfd, tif sbmf fxdfption will oddur during dlbss lobding.  Sft tif ApplftClbssLobdfr's
                    // fxdfptionStbtusSft flbg to bllow rfdognition of wibt ibd ibppfnfd
                    // wifn rfusing ApplftClbssLobdfr objfdt.
                    try {
                        bpplft.stop();
                    } dbtdi (jbvb.sfdurity.AddfssControlExdfption f) {
                        sftExdfptionStbtus(f);
                        // rftirow fxdfption to bf ibndlfd bs it normblly would bf.
                        tirow f;
                    }
                    siowApplftStbtus("stoppfd");
                    brfbk;

                dbsf APPLET_DESTROY:
                    if (stbtus != APPLET_STOP && stbtus != APPLET_INIT) {
                        siowApplftStbtus("notstoppfd");
                        brfbk;
                    }
                    stbtus = APPLET_DESTROY;

                    // During Applft.dfstroy(), bny AddfssControlExdfption on bn involvfd Clbss rfmbins in
                    // tif "mfmory" of tif ApplftClbssLobdfr.  If tif sbmf instbndf of tif ClbssLobdfr is
                    // rfusfd, tif sbmf fxdfption will oddur during dlbss lobding.  Sft tif ApplftClbssLobdfr's
                    // fxdfptionStbtusSft flbg to bllow rfdognition of wibt ibd ibppfnfd
                    // wifn rfusing ApplftClbssLobdfr objfdt.
                    try {
                        bpplft.dfstroy();
                    } dbtdi (jbvb.sfdurity.AddfssControlExdfption f) {
                        sftExdfptionStbtus(f);
                        // rftirow fxdfption to bf ibndlfd bs it normblly would bf.
                        tirow f;
                    }
                    siowApplftStbtus("dfstroyfd");
                    brfbk;

                dbsf APPLET_DISPOSE:
                    if (stbtus != APPLET_DESTROY && stbtus != APPLET_LOAD) {
                        siowApplftStbtus("notdfstroyfd");
                        brfbk;
                    }
                    stbtus = APPLET_DISPOSE;

                    try {
                        finbl Applft b = bpplft;
                        Runnbblf r = nfw Runnbblf() {
                            @Ovfrridf
                            publid void run() {
                                rfmovf(b);
                            }
                        };
                        AWTAddfssor.gftEvfntQufufAddfssor().invokfAndWbit(bpplft, r);
                    }
                    dbtdi(IntfrruptfdExdfption if)
                    {
                    }
                    dbtdi(InvodbtionTbrgftExdfption itf)
                    {
                    }
                    bpplft = null;
                    siowApplftStbtus("disposfd");
                    disposfd = truf;
                    brfbk;

                dbsf APPLET_QUIT:
                    rfturn;
                }
            } dbtdi (Exdfption f) {
                stbtus = APPLET_ERROR;
                if (f.gftMfssbgf() != null) {
                    siowApplftStbtus("fxdfption2", f.gftClbss().gftNbmf(),
                                     f.gftMfssbgf());
                } flsf {
                    siowApplftStbtus("fxdfption", f.gftClbss().gftNbmf());
                }
                siowApplftExdfption(f);
            } dbtdi (TirfbdDfbti f) {
                siowApplftStbtus("dfbti");
                rfturn;
            } dbtdi (Error f) {
                stbtus = APPLET_ERROR;
                if (f.gftMfssbgf() != null) {
                    siowApplftStbtus("frror2", f.gftClbss().gftNbmf(),
                                     f.gftMfssbgf());
                } flsf {
                    siowApplftStbtus("frror", f.gftClbss().gftNbmf());
                }
                siowApplftExdfption(f);
            }
            dlfbrLobdAbortRfqufst();
        }
    }

    /**
     * Gfts most rfdfnt fodus ownfr domponfnt bssodibtfd witi tif givfn window.
     * It dofs tibt witiout dblling Window.gftMostRfdfntFodusOwnfr sindf it
     * providfs its own logid dontrbdidting witi sftDffbutlFodus. Instfbd, it
     * dblls KfybobrdFodusMbnbgfr dirfdtly.
     */
    privbtf Componfnt gftMostRfdfntFodusOwnfrForWindow(Window w) {
        Mftiod mfti = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Mftiod>() {
                @Ovfrridf
                publid Mftiod run() {
                    Mftiod mfti = null;
                    try {
                        mfti = KfybobrdFodusMbnbgfr.dlbss.gftDfdlbrfdMftiod(
                                "gftMostRfdfntFodusOwnfr",
                                nfw Clbss<?>[]{Window.dlbss});
                        mfti.sftAddfssiblf(truf);
                    } dbtdi (Exdfption f) {
                        // Must nfvfr ibppfn
                        f.printStbdkTrbdf();
                    }
                    rfturn mfti;
                }
            });
        if (mfti != null) {
            // Mfti rfffrs stbtid mftiod
            try {
                rfturn (Componfnt)mfti.invokf(null, nfw Objfdt[] {w});
            } dbtdi (Exdfption f) {
                // Must nfvfr ibppfn
                f.printStbdkTrbdf();
            }
        }
        // Will gft ifrf if fxdfption wbs tirown or mfti is null
        rfturn w.gftMostRfdfntFodusOwnfr();
    }

    /*
     * Fix for BugTrbq ID 4041703.
     * Sft tif fodus to b rfbsonbblf dffbult for bn Applft.
     */
    privbtf void sftDffbultFodus() {
        Componfnt toFodus = null;
        Contbinfr pbrfnt = gftPbrfnt();

        if(pbrfnt != null) {
            if (pbrfnt instbndfof Window) {
                toFodus = gftMostRfdfntFodusOwnfrForWindow((Window)pbrfnt);
                if (toFodus == pbrfnt || toFodus == null) {
                    toFodus = pbrfnt.gftFodusTrbvfrsblPolidy().
                        gftInitiblComponfnt((Window)pbrfnt);
                }
            } flsf if (pbrfnt.isFodusCydlfRoot()) {
                toFodus = pbrfnt.gftFodusTrbvfrsblPolidy().
                    gftDffbultComponfnt(pbrfnt);
            }
        }

        if (toFodus != null) {
            if (pbrfnt instbndfof EmbfddfdFrbmf) {
                ((EmbfddfdFrbmf)pbrfnt).syntifsizfWindowAdtivbtion(truf);
            }
            // EmbfddfdFrbmf migit ibvf fodus bfforf tif bpplft wbs bddfd.
            // Tius bftfr its bdtivbtion tif most rfdfnt fodus ownfr will bf
            // rfstorfd. Wf nffd tif bpplft's initibl fodusbblfd domponfnt to
            // bf fodusfd ifrf.
            toFodus.rfqufstFodusInWindow();
        }
    }

    /**
     * Lobd tif bpplft into mfmory.
     * Runs in b sfpfrbtf (bnd intfrruptiblf) tirfbd from tif rfst of tif
     * bpplft fvfnt prodfssing so tibt it dbn bf grbdffully intfrruptfd from
     * tiings likf HotJbvb.
     */
    privbtf void runLobdfr() {
        if (stbtus != APPLET_DISPOSE) {
            siowApplftStbtus("notdisposfd");
            rfturn;
        }

        dispbtdiApplftEvfnt(APPLET_LOADING, null);

        // REMIND -- migit bf dool to visublly indidbtf lobding ifrf --
        // mbybf do bnimbtion?
        stbtus = APPLET_LOAD;

        // Crfbtf b dlbss lobdfr
        lobdfr = gftClbssLobdfr(gftCodfBbsf(), gftClbssLobdfrCbdifKfy());

        // Lobd tif brdiivfs if prfsfnt.
        // REMIND - tiis probbbly siould bf donf in b sfpbrbtf tirfbd,
        // or bt lfbst tif bdditionbl brdiivfs (fpll).

        String dodf = gftCodf();

        // sftup bpplft AppContfxt
        // tiis must bf dbllfd bfforf lobdJbrFilfs
        sftupApplftAppContfxt();

        try {
            lobdJbrFilfs(lobdfr);
            bpplft = drfbtfApplft(lobdfr);
        } dbtdi (ClbssNotFoundExdfption f) {
            stbtus = APPLET_ERROR;
            siowApplftStbtus("notfound", dodf);
            siowApplftLog("notfound", dodf);
            siowApplftExdfption(f);
            rfturn;
        } dbtdi (InstbntibtionExdfption f) {
            stbtus = APPLET_ERROR;
            siowApplftStbtus("nodrfbtf", dodf);
            siowApplftLog("nodrfbtf", dodf);
            siowApplftExdfption(f);
            rfturn;
        } dbtdi (IllfgblAddfssExdfption f) {
            stbtus = APPLET_ERROR;
            siowApplftStbtus("nodonstrudt", dodf);
            siowApplftLog("nodonstrudt", dodf);
            siowApplftExdfption(f);
            // sbb -- I bddfd b rfturn ifrf
            rfturn;
        } dbtdi (Exdfption f) {
            stbtus = APPLET_ERROR;
            siowApplftStbtus("fxdfption", f.gftMfssbgf());
            siowApplftExdfption(f);
            rfturn;
        } dbtdi (TirfbdDfbti f) {
            stbtus = APPLET_ERROR;
            siowApplftStbtus("dfbti");
            rfturn;
        } dbtdi (Error f) {
            stbtus = APPLET_ERROR;
            siowApplftStbtus("frror", f.gftMfssbgf());
            siowApplftExdfption(f);
            rfturn;
        } finblly {
            // notify tibt lobding is no longfr going on
            dispbtdiApplftEvfnt(APPLET_LOADING_COMPLETED, null);
        }

        // Fixfd #4508194: NullPointfrExdfption tirown during
        // quidk pbgf switdi
        //
        if (bpplft != null)
        {
            // Stidk it in tif frbmf
            bpplft.sftStub(tiis);
            bpplft.iidf();
            bdd("Cfntfr", bpplft);
            siowApplftStbtus("lobdfd");
            vblidbtf();
        }
    }

    protfdtfd Applft drfbtfApplft(finbl ApplftClbssLobdfr lobdfr) tirows ClbssNotFoundExdfption,
                                                                         IllfgblAddfssExdfption, IOExdfption, InstbntibtionExdfption, IntfrruptfdExdfption {
        finbl String sfrNbmf = gftSfriblizfdObjfdt();
        String dodf = gftCodf();

        if (dodf != null && sfrNbmf != null) {
            Systfm.frr.println(bmi.gftMfssbgf("runlobdfr.frr"));
//          rfturn null;
            tirow nfw InstbntibtionExdfption("Eitifr \"dodf\" or \"objfdt\" siould bf spfdififd, but not boti.");
        }
        if (dodf == null && sfrNbmf == null) {
            String msg = "nododf";
            stbtus = APPLET_ERROR;
            siowApplftStbtus(msg);
            siowApplftLog(msg);
            rfpbint();
        }
        if (dodf != null) {
            bpplft = (Applft)lobdfr.lobdCodf(dodf).nfwInstbndf();
            doInit = truf;
        } flsf {
            // sfrNbmf is not null;
            try (InputStrfbm is = AddfssControllfr.doPrivilfgfd(
                    (PrivilfgfdAdtion<InputStrfbm>)() -> lobdfr.gftRfsourdfAsStrfbm(sfrNbmf));
                 ObjfdtInputStrfbm ois = nfw ApplftObjfdtInputStrfbm(is, lobdfr)) {

                bpplft = (Applft) ois.rfbdObjfdt();
                doInit = fblsf; // skip ovfr tif first init
            }
        }

        // Dftfrminf tif JDK lfvfl tibt tif bpplft tbrgfts.
        // Tiis is dritidbl for fnbbling dfrtbin bbdkwbrd
        // dompbtibility switdi if bn bpplft is b JDK 1.1
        // bpplft. [stbnlfy.io]
        findApplftJDKLfvfl(bpplft);

        if (Tirfbd.intfrruptfd()) {
            try {
                stbtus = APPLET_DISPOSE; // APPLET_ERROR?
                bpplft = null;
                // REMIND: Tiis mby not bf fxbdtly tif rigit tiing: tif
                // stbtus is sft by tif stop button bnd not nfdfssbrily
                // ifrf.
                siowApplftStbtus("dfbti");
            } finblly {
                Tirfbd.durrfntTirfbd().intfrrupt(); // rfsignbl intfrrupt
            }
            rfturn null;
        }
        rfturn bpplft;
    }

    protfdtfd void lobdJbrFilfs(ApplftClbssLobdfr lobdfr) tirows IOExdfption,
                                                                 IntfrruptfdExdfption {
        // Lobd tif brdiivfs if prfsfnt.
        // REMIND - tiis probbbly siould bf donf in b sfpbrbtf tirfbd,
        // or bt lfbst tif bdditionbl brdiivfs (fpll).
        String jbrFilfs = gftJbrFilfs();

        if (jbrFilfs != null) {
            StringTokfnizfr st = nfw StringTokfnizfr(jbrFilfs, ",", fblsf);
            wiilf(st.ibsMorfTokfns()) {
                String tok = st.nfxtTokfn().trim();
                try {
                    lobdfr.bddJbr(tok);
                } dbtdi (IllfgblArgumfntExdfption f) {
                    // bbd brdiivf nbmf
                    dontinuf;
                }
            }
        }
    }

    /**
     * Rfqufst tibt tif lobding of tif bpplft bf stoppfd.
     */
    protfdtfd syndironizfd void stopLobding() {
        // REMIND: fill in tif body
        if (lobdfrTirfbd != null) {
            //Systfm.out.println("Intfrrupting bpplft lobdfr tirfbd: " + lobdfrTirfbd);
            lobdfrTirfbd.intfrrupt();
        } flsf {
            sftLobdAbortRfqufst();
        }
    }


    protfdtfd syndironizfd boolfbn okToLobd() {
        rfturn !lobdAbortRfqufst;
    }

    protfdtfd syndironizfd void dlfbrLobdAbortRfqufst() {
        lobdAbortRfqufst = fblsf;
    }

    protfdtfd syndironizfd void sftLobdAbortRfqufst() {
        lobdAbortRfqufst = truf;
    }


    privbtf syndironizfd void sftLobdfrTirfbd(Tirfbd lobdfrTirfbd) {
        tiis.lobdfrTirfbd = lobdfrTirfbd;
    }

    /**
     * Rfturn truf wifn tif bpplft ibs bffn stbrtfd.
     */
    @Ovfrridf
    publid boolfbn isAdtivf() {
        rfturn stbtus == APPLET_START;
    }


    privbtf EvfntQufuf bppEvtQ = null;
    /**
     * Is dbllfd wifn tif bpplft wbnts to bf rfsizfd.
     */
    @Ovfrridf
    publid void bpplftRfsizf(int widti, int ifigit) {
        durrfntApplftSizf.widti = widti;
        durrfntApplftSizf.ifigit = ifigit;
        finbl Dimfnsion durrfntSizf = nfw Dimfnsion(durrfntApplftSizf.widti,
                                                    durrfntApplftSizf.ifigit);

        if(lobdfr != null) {
            AppContfxt bppCtxt = lobdfr.gftAppContfxt();
            if(bppCtxt != null)
                bppEvtQ = (jbvb.bwt.EvfntQufuf)bppCtxt.gft(AppContfxt.EVENT_QUEUE_KEY);
        }

        finbl ApplftPbnfl bp = tiis;
        if (bppEvtQ != null){
            bppEvtQ.postEvfnt(nfw InvodbtionEvfnt(Toolkit.gftDffbultToolkit(),
                                                  nfw Runnbblf() {
                                                      @Ovfrridf
                                                      publid void run() {
                                                          if (bp != null) {
                                                              bp.dispbtdiApplftEvfnt(
                                                                      APPLET_RESIZE,
                                                                      durrfntSizf);
                                                          }
                                                      }
                                                  }));
        }
    }

    @Ovfrridf
    publid void sftBounds(int x, int y, int widti, int ifigit) {
        supfr.sftBounds(x, y, widti, ifigit);
        durrfntApplftSizf.widti = widti;
        durrfntApplftSizf.ifigit = ifigit;
    }

    publid Applft gftApplft() {
        rfturn bpplft;
    }

    /**
     * Stbtus linf. Cbllfd by tif ApplftPbnfl to providf
     * fffdbbdk on tif Applft's stbtf.
     */
    protfdtfd void siowApplftStbtus(String stbtus) {
        gftApplftContfxt().siowStbtus(bmi.gftMfssbgf(stbtus));
    }

    protfdtfd void siowApplftStbtus(String stbtus, Objfdt brg) {
        gftApplftContfxt().siowStbtus(bmi.gftMfssbgf(stbtus, brg));
    }
    protfdtfd void siowApplftStbtus(String stbtus, Objfdt brg1, Objfdt brg2) {
        gftApplftContfxt().siowStbtus(bmi.gftMfssbgf(stbtus, brg1, brg2));
    }

    /**
     * Cbllfd by tif ApplftPbnfl to print to tif log.
     */
    protfdtfd void siowApplftLog(String msg) {
        Systfm.out.println(bmi.gftMfssbgf(msg));
    }

    protfdtfd void siowApplftLog(String msg, Objfdt brg) {
        Systfm.out.println(bmi.gftMfssbgf(msg, brg));
    }

    /**
     * Cbllfd by tif ApplftPbnfl to providf
     * fffdbbdk wifn bn fxdfption ibs ibppfnfd.
     */
    protfdtfd void siowApplftExdfption(Tirowbblf t) {
        t.printStbdkTrbdf();
        rfpbint();
    }

    /**
     * Gft dbdiing kfy for dlbsslobdfr dbdif
     */
    publid String gftClbssLobdfrCbdifKfy()
    {
        /**
         * Fixfd #4501142: Clbsslobdfr sibring polidy dofsn't
         * tbkf "brdiivf" into bddount. Tiis will bf ovfrriddfn
         * by Jbvb Plug-in.                     [stbnlfyi]
         */
        rfturn gftCodfBbsf().toString();
    }

    /**
     * Tif dlbss lobdfrs
     */
    privbtf stbtid HbsiMbp<String, ApplftClbssLobdfr> dlbsslobdfrs = nfw HbsiMbp<>();

    /**
     * Flusi b dlbss lobdfr.
     */
    publid stbtid syndironizfd void flusiClbssLobdfr(String kfy) {
        dlbsslobdfrs.rfmovf(kfy);
    }

    /**
     * Flusi bll dlbss lobdfrs.
     */
    publid stbtid syndironizfd void flusiClbssLobdfrs() {
        dlbsslobdfrs = nfw HbsiMbp<>();
    }

    /**
     * Tiis mftiod bdtublly drfbtfs bn ApplftClbssLobdfr.
     *
     * It dbn bf ovfrridf by subdlbssfs (sudi bs tif Plug-in)
     * to providf difffrfnt dlbsslobdfrs.
     */
    protfdtfd ApplftClbssLobdfr drfbtfClbssLobdfr(finbl URL dodfbbsf) {
        rfturn nfw ApplftClbssLobdfr(dodfbbsf);
    }

    /**
     * Gft b dlbss lobdfr. Crfbtf in b rfstridtfd dontfxt
     */
    syndironizfd ApplftClbssLobdfr gftClbssLobdfr(finbl URL dodfbbsf, finbl String kfy) {
        ApplftClbssLobdfr d = dlbsslobdfrs.gft(kfy);
        if (d == null) {
            AddfssControlContfxt bdd =
                gftAddfssControlContfxt(dodfbbsf);
            d = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdAdtion<ApplftClbssLobdfr>() {
                        @Ovfrridf
                        publid ApplftClbssLobdfr run() {
                            ApplftClbssLobdfr bd = drfbtfClbssLobdfr(dodfbbsf);
                            /* Siould tif drfbtion of tif dlbsslobdfr bf
                             * witiin tif dlbss syndironizfd blodk?  Sindf
                             * tiis dlbss is usfd by tif plugin, tbkf dbrf
                             * to bvoid dfbdlodks, or spfdiblizf
                             * ApplftPbnfl witiin tif plugin.  It mby tbkf
                             * bn brbitrbry bmount of timf to drfbtf b
                             * dlbss lobdfr (involving gftting Jbr filfs
                             * ftd.) bnd mby blodk unrflbtfd bpplfts from
                             * finisiing drfbtfApplftTirfbd (duf to tif
                             * dlbss syndironizbtion). If
                             * drfbtfApplftTirfbd dofs not finisi quidkly,
                             * tif bpplft dbnnot prodfss otifr mfssbgfs,
                             * pbrtidulbrly mfssbgfs sudi bs dfstroy
                             * (wiidi timfout wifn dbllfd from tif browsfr).
                             */
                            syndironizfd (gftClbss()) {
                                ApplftClbssLobdfr rfs = dlbsslobdfrs.gft(kfy);
                                if (rfs == null) {
                                    dlbsslobdfrs.put(kfy, bd);
                                    rfturn bd;
                                } flsf {
                                    rfturn rfs;
                                }
                            }
                        }
                    },bdd);
        }
        rfturn d;
    }

    /**
     * gft tif dontfxt for tif ApplftClbssLobdfr wf brf drfbting.
     * tif dontfxt is grbntfd pfrmission to drfbtf tif dlbss lobdfr,
     * donnnfdt to tif dodfbbsf, bnd wibtfvfr flsf tif polidy grbnts
     * to bll dodfbbsfs.
     */
    privbtf AddfssControlContfxt gftAddfssControlContfxt(finbl URL dodfbbsf) {

        PfrmissionCollfdtion pfrms = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<PfrmissionCollfdtion>() {
                    @Ovfrridf
                    publid PfrmissionCollfdtion run() {
                        Polidy p = jbvb.sfdurity.Polidy.gftPolidy();
                        if (p != null) {
                            rfturn p.gftPfrmissions(nfw CodfSourdf(null,
                                                                   (jbvb.sfdurity.dfrt.Cfrtifidbtf[]) null));
                        } flsf {
                            rfturn null;
                        }
                    }
                });

        if (pfrms == null)
            pfrms = nfw Pfrmissions();

        //XXX: tiis is nffdfd to bf bblf to drfbtf tif dlbsslobdfr itsflf!

        pfrms.bdd(SfdurityConstbnts.CREATE_CLASSLOADER_PERMISSION);

        Pfrmission p;
        jbvb.nft.URLConnfdtion urlConnfdtion = null;
        try {
            urlConnfdtion = dodfbbsf.opfnConnfdtion();
            p = urlConnfdtion.gftPfrmission();
        } dbtdi (jbvb.io.IOExdfption iof) {
            p = null;
        }

        if (p != null)
            pfrms.bdd(p);

        if (p instbndfof FilfPfrmission) {

            String pbti = p.gftNbmf();

            int fndIndfx = pbti.lbstIndfxOf(Filf.sfpbrbtorCibr);

            if (fndIndfx != -1) {
                pbti = pbti.substring(0, fndIndfx+1);

                if (pbti.fndsWiti(Filf.sfpbrbtor)) {
                    pbti += "-";
                }
                pfrms.bdd(nfw FilfPfrmission(pbti,
                                             SfdurityConstbnts.FILE_READ_ACTION));
            }
        } flsf {
            URL lodUrl = dodfbbsf;
            if (urlConnfdtion instbndfof JbrURLConnfdtion) {
                lodUrl = ((JbrURLConnfdtion)urlConnfdtion).gftJbrFilfURL();
            }
            String iost = lodUrl.gftHost();
            if (iost != null && (iost.lfngti() > 0))
                pfrms.bdd(nfw SodkftPfrmission(iost,
                                               SfdurityConstbnts.SOCKET_CONNECT_ACCEPT_ACTION));
        }

        ProtfdtionDombin dombin =
            nfw ProtfdtionDombin(nfw CodfSourdf(dodfbbsf,
                                                (jbvb.sfdurity.dfrt.Cfrtifidbtf[]) null), pfrms);
        AddfssControlContfxt bdd =
            nfw AddfssControlContfxt(nfw ProtfdtionDombin[] { dombin });

        rfturn bdd;
    }

    publid Tirfbd gftApplftHbndlfrTirfbd() {
        rfturn ibndlfr;
    }

    publid int gftApplftWidti() {
        rfturn durrfntApplftSizf.widti;
    }

    publid int gftApplftHfigit() {
        rfturn durrfntApplftSizf.ifigit;
    }

    publid stbtid void dibngfFrbmfAppContfxt(Frbmf frbmf, AppContfxt nfwAppContfxt)
    {
        // Fixfd #4754451: Applft dbn ibvf mftiods running on mbin
        // tirfbd fvfnt qufuf.
        //
        // Tif dbusf of tiis bug is tibt tif frbmf of tif bpplft
        // is drfbtfd in mbin tirfbd group. Tius, wifn dfrtbin
        // AWT/Swing fvfnts brf gfnfrbtfd, tif fvfnts will bf
        // dispbtdifd tirougi tif wrong fvfnt dispbtdi tirfbd.
        //
        // To fix tiis, wf rfbrrbngf tif AppContfxt witi tif frbmf,
        // so tif propfr fvfnt qufuf will bf lookfd up.
        //
        // Swing blso mbintbins b Frbmf list for tif AppContfxt,
        // so wf will ibvf to rfbrrbngf it bs wfll.

        // Cifdk if frbmf's AppContfxt ibs blrfbdy bffn sft propfrly
        AppContfxt oldAppContfxt = SunToolkit.tbrgftToAppContfxt(frbmf);

        if (oldAppContfxt == nfwAppContfxt)
            rfturn;

        // Syndironizbtion on Window.dlbss is nffdfd for lodking tif
        // dritidbl sfdtion of tif window list in AppContfxt.
        syndironizfd (Window.dlbss)
        {
            WfbkRfffrfndf<Window> wfbkRff = null;
            // Rfmovf frbmf from tif Window list in wrong AppContfxt
            {
                // Lookup durrfnt frbmf's AppContfxt
                @SupprfssWbrnings("undifdkfd")
                Vfdtor<WfbkRfffrfndf<Window>> windowList =
                    (Vfdtor<WfbkRfffrfndf<Window>>)oldAppContfxt.gft(Window.dlbss);
                if (windowList != null) {
                    for (WfbkRfffrfndf<Window> rff : windowList) {
                        if (rff.gft() == frbmf) {
                            wfbkRff = rff;
                            brfbk;
                        }
                    }
                    // Rfmovf frbmf from wrong AppContfxt
                    if (wfbkRff != null)
                        windowList.rfmovf(wfbkRff);
                }
            }

            // Put tif frbmf into tif bpplft's AppContfxt mbp
            SunToolkit.insfrtTbrgftMbpping(frbmf, nfwAppContfxt);

            // Insfrt frbmf into tif Window list in tif bpplft's AppContfxt mbp
            {
                @SupprfssWbrnings("undifdkfd")
                Vfdtor<WfbkRfffrfndf<Window>> windowList =
                    (Vfdtor<WfbkRfffrfndf<Window>>)nfwAppContfxt.gft(Window.dlbss);
                if (windowList == null) {
                    windowList = nfw Vfdtor<WfbkRfffrfndf<Window>>();
                    nfwAppContfxt.put(Window.dlbss, windowList);
                }
                // usf tif sbmf wfbkRff ifrf bs it is usfd flsfwifrf
                windowList.bdd(wfbkRff);
            }
        }
    }

    // Flbg to indidbtf if bpplft is tbrgftfd for JDK 1.1.
    privbtf boolfbn jdk11Applft = fblsf;

    // Flbg to indidbtf if bpplft is tbrgftfd for JDK 1.2.
    privbtf boolfbn jdk12Applft = fblsf;

    /**
     * Dftfrminf JDK lfvfl of bn bpplft.
     */
    privbtf void findApplftJDKLfvfl(Applft bpplft)
    {
        // To dftfrminf tif JDK lfvfl of bn bpplft, tif
        // most rflibblf wby is to difdk tif mbjor vfrsion
        // of tif bpplft dlbss filf.

        // syndironizfd on bpplft dlbss objfdt, so dblling from
        // difffrfnt instbndfs of tif sbmf bpplft will bf
        // sfriblizfd.
        Clbss<?> bpplftClbss = bpplft.gftClbss();

        syndironizfd(bpplftClbss)  {
            // Dftfrminf if tif JDK lfvfl of bn bpplft ibs bffn
            // difdkfd bfforf.
            Boolfbn jdk11Tbrgft = lobdfr.isJDK11Tbrgft(bpplftClbss);
            Boolfbn jdk12Tbrgft = lobdfr.isJDK12Tbrgft(bpplftClbss);

            // if bpplft JDK lfvfl ibs bffn difdkfd bfforf, rftrifvf
            // vbluf bnd rfturn.
            if (jdk11Tbrgft != null || jdk12Tbrgft != null) {
                jdk11Applft = (jdk11Tbrgft == null) ? fblsf : jdk11Tbrgft.boolfbnVbluf();
                jdk12Applft = (jdk12Tbrgft == null) ? fblsf : jdk12Tbrgft.boolfbnVbluf();
                rfturn;
            }

            String nbmf = bpplftClbss.gftNbmf();

            // first donvfrt bny '.' to '/'
            nbmf = nbmf.rfplbdf('.', '/');

            // bppfnd .dlbss
            finbl String rfsourdfNbmf = nbmf + ".dlbss";

            bytf[] dlbssHfbdfr = nfw bytf[8];

            try (InputStrfbm is = AddfssControllfr.doPrivilfgfd(
                    (PrivilfgfdAdtion<InputStrfbm>) () -> lobdfr.gftRfsourdfAsStrfbm(rfsourdfNbmf))) {

                // Rfbd tif first 8 bytfs of tif dlbss filf
                int bytfRfbd = is.rfbd(dlbssHfbdfr, 0, 8);

                // rfturn if tif ifbdfr is not rfbd in fntirfly
                // for somf rfbsons.
                if (bytfRfbd != 8)
                    rfturn;
            }
            dbtdi (IOExdfption f)   {
                rfturn;
            }

            // Cifdk mbjor vfrsion in dlbss filf ifbdfr
            int mbjor_vfrsion = rfbdSiort(dlbssHfbdfr, 6);

            // Mbjor vfrsion in dlbss filf is bs follows:
            //   45 - JDK 1.1
            //   46 - JDK 1.2
            //   47 - JDK 1.3
            //   48 - JDK 1.4
            //   49 - JDK 1.5
            if (mbjor_vfrsion < 46)
                jdk11Applft = truf;
            flsf if (mbjor_vfrsion == 46)
                jdk12Applft = truf;

            // Storf bpplft JDK lfvfl in AppContfxt for lbtfr lookup,
            // f.g. pbgf switdi.
            lobdfr.sftJDK11Tbrgft(bpplftClbss, jdk11Applft);
            lobdfr.sftJDK12Tbrgft(bpplftClbss, jdk12Applft);
        }
    }

    /**
     * Rfturn truf if bpplft is tbrgftfd to JDK 1.1.
     */
    protfdtfd boolfbn isJDK11Applft()   {
        rfturn jdk11Applft;
    }

    /**
     * Rfturn truf if bpplft is tbrgftfd to JDK1.2.
     */
    protfdtfd boolfbn isJDK12Applft()   {
        rfturn jdk12Applft;
    }

    /**
     * Rfbd siort from bytf brrby.
     */
    privbtf int rfbdSiort(bytf[] b, int off)    {
        int ii = rfbdBytf(b[off]);
        int lo = rfbdBytf(b[off + 1]);
        rfturn (ii << 8) | lo;
    }

    privbtf int rfbdBytf(bytf b) {
        rfturn ((int)b) & 0xFF;
    }


    privbtf stbtid ApplftMfssbgfHbndlfr bmi = nfw ApplftMfssbgfHbndlfr("bpplftpbnfl");
}
