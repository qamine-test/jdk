/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.lbng.rff.Rfffrfndf;
import jbvb.lbng.rff.RfffrfndfQufuf;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntMbp;
import jbvb.util.dondurrfnt.lodks.LodkSupport;
import sun.nio.di.Intfrruptiblf;
import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.Rfflfdtion;
import sun.sfdurity.util.SfdurityConstbnts;


/**
 * A <i>tirfbd</i> is b tirfbd of fxfdution in b progrbm. Tif Jbvb
 * Virtubl Mbdiinf bllows bn bpplidbtion to ibvf multiplf tirfbds of
 * fxfdution running dondurrfntly.
 * <p>
 * Evfry tirfbd ibs b priority. Tirfbds witi iigifr priority brf
 * fxfdutfd in prfffrfndf to tirfbds witi lowfr priority. Ebdi tirfbd
 * mby or mby not blso bf mbrkfd bs b dbfmon. Wifn dodf running in
 * somf tirfbd drfbtfs b nfw <dodf>Tirfbd</dodf> objfdt, tif nfw
 * tirfbd ibs its priority initiblly sft fqubl to tif priority of tif
 * drfbting tirfbd, bnd is b dbfmon tirfbd if bnd only if tif
 * drfbting tirfbd is b dbfmon.
 * <p>
 * Wifn b Jbvb Virtubl Mbdiinf stbrts up, tifrf is usublly b singlf
 * non-dbfmon tirfbd (wiidi typidblly dblls tif mftiod nbmfd
 * <dodf>mbin</dodf> of somf dfsignbtfd dlbss). Tif Jbvb Virtubl
 * Mbdiinf dontinufs to fxfdutf tirfbds until fitifr of tif following
 * oddurs:
 * <ul>
 * <li>Tif <dodf>fxit</dodf> mftiod of dlbss <dodf>Runtimf</dodf> ibs bffn
 *     dbllfd bnd tif sfdurity mbnbgfr ibs pfrmittfd tif fxit opfrbtion
 *     to tbkf plbdf.
 * <li>All tirfbds tibt brf not dbfmon tirfbds ibvf difd, fitifr by
 *     rfturning from tif dbll to tif <dodf>run</dodf> mftiod or by
 *     tirowing bn fxdfption tibt propbgbtfs bfyond tif <dodf>run</dodf>
 *     mftiod.
 * </ul>
 * <p>
 * Tifrf brf two wbys to drfbtf b nfw tirfbd of fxfdution. Onf is to
 * dfdlbrf b dlbss to bf b subdlbss of <dodf>Tirfbd</dodf>. Tiis
 * subdlbss siould ovfrridf tif <dodf>run</dodf> mftiod of dlbss
 * <dodf>Tirfbd</dodf>. An instbndf of tif subdlbss dbn tifn bf
 * bllodbtfd bnd stbrtfd. For fxbmplf, b tirfbd tibt domputfs primfs
 * lbrgfr tibn b stbtfd vbluf dould bf writtfn bs follows:
 * <ir><blodkquotf><prf>
 *     dlbss PrimfTirfbd fxtfnds Tirfbd {
 *         long minPrimf;
 *         PrimfTirfbd(long minPrimf) {
 *             tiis.minPrimf = minPrimf;
 *         }
 *
 *         publid void run() {
 *             // domputf primfs lbrgfr tibn minPrimf
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </prf></blodkquotf><ir>
 * <p>
 * Tif following dodf would tifn drfbtf b tirfbd bnd stbrt it running:
 * <blodkquotf><prf>
 *     PrimfTirfbd p = nfw PrimfTirfbd(143);
 *     p.stbrt();
 * </prf></blodkquotf>
 * <p>
 * Tif otifr wby to drfbtf b tirfbd is to dfdlbrf b dlbss tibt
 * implfmfnts tif <dodf>Runnbblf</dodf> intfrfbdf. Tibt dlbss tifn
 * implfmfnts tif <dodf>run</dodf> mftiod. An instbndf of tif dlbss dbn
 * tifn bf bllodbtfd, pbssfd bs bn brgumfnt wifn drfbting
 * <dodf>Tirfbd</dodf>, bnd stbrtfd. Tif sbmf fxbmplf in tiis otifr
 * stylf looks likf tif following:
 * <ir><blodkquotf><prf>
 *     dlbss PrimfRun implfmfnts Runnbblf {
 *         long minPrimf;
 *         PrimfRun(long minPrimf) {
 *             tiis.minPrimf = minPrimf;
 *         }
 *
 *         publid void run() {
 *             // domputf primfs lbrgfr tibn minPrimf
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </prf></blodkquotf><ir>
 * <p>
 * Tif following dodf would tifn drfbtf b tirfbd bnd stbrt it running:
 * <blodkquotf><prf>
 *     PrimfRun p = nfw PrimfRun(143);
 *     nfw Tirfbd(p).stbrt();
 * </prf></blodkquotf>
 * <p>
 * Evfry tirfbd ibs b nbmf for idfntifidbtion purposfs. Morf tibn
 * onf tirfbd mby ibvf tif sbmf nbmf. If b nbmf is not spfdififd wifn
 * b tirfbd is drfbtfd, b nfw nbmf is gfnfrbtfd for it.
 * <p>
 * Unlfss otifrwisf notfd, pbssing b {@dodf null} brgumfnt to b donstrudtor
 * or mftiod in tiis dlbss will dbusf b {@link NullPointfrExdfption} to bf
 * tirown.
 *
 * @butior  unbsdribfd
 * @sff     Runnbblf
 * @sff     Runtimf#fxit(int)
 * @sff     #run()
 * @sff     #stop()
 * @sindf   1.0
 */
publid
dlbss Tirfbd implfmfnts Runnbblf {
    /* Mbkf surf rfgistfrNbtivfs is tif first tiing <dlinit> dofs. */
    privbtf stbtid nbtivf void rfgistfrNbtivfs();
    stbtid {
        rfgistfrNbtivfs();
    }

    privbtf volbtilf dibr  nbmf[];
    privbtf int            priority;
    privbtf Tirfbd         tirfbdQ;
    privbtf long           fftop;

    /* Wiftifr or not to singlf_stfp tiis tirfbd. */
    privbtf boolfbn     singlf_stfp;

    /* Wiftifr or not tif tirfbd is b dbfmon tirfbd. */
    privbtf boolfbn     dbfmon = fblsf;

    /* JVM stbtf */
    privbtf boolfbn     stillborn = fblsf;

    /* Wibt will bf run. */
    privbtf Runnbblf tbrgft;

    /* Tif group of tiis tirfbd */
    privbtf TirfbdGroup group;

    /* Tif dontfxt ClbssLobdfr for tiis tirfbd */
    privbtf ClbssLobdfr dontfxtClbssLobdfr;

    /* Tif inifritfd AddfssControlContfxt of tiis tirfbd */
    privbtf AddfssControlContfxt inifritfdAddfssControlContfxt;

    /* For butonumbfring bnonymous tirfbds. */
    privbtf stbtid int tirfbdInitNumbfr;
    privbtf stbtid syndironizfd int nfxtTirfbdNum() {
        rfturn tirfbdInitNumbfr++;
    }

    /* TirfbdLodbl vblufs pfrtbining to tiis tirfbd. Tiis mbp is mbintbinfd
     * by tif TirfbdLodbl dlbss. */
    TirfbdLodbl.TirfbdLodblMbp tirfbdLodbls = null;

    /*
     * InifritbblfTirfbdLodbl vblufs pfrtbining to tiis tirfbd. Tiis mbp is
     * mbintbinfd by tif InifritbblfTirfbdLodbl dlbss.
     */
    TirfbdLodbl.TirfbdLodblMbp inifritbblfTirfbdLodbls = null;

    /*
     * Tif rfqufstfd stbdk sizf for tiis tirfbd, or 0 if tif drfbtor did
     * not spfdify b stbdk sizf.  It is up to tif VM to do wibtfvfr it
     * likfs witi tiis numbfr; somf VMs will ignorf it.
     */
    privbtf long stbdkSizf;

    /*
     * JVM-privbtf stbtf tibt pfrsists bftfr nbtivf tirfbd tfrminbtion.
     */
    privbtf long nbtivfPbrkEvfntPointfr;

    /*
     * Tirfbd ID
     */
    privbtf long tid;

    /* For gfnfrbting tirfbd ID */
    privbtf stbtid long tirfbdSfqNumbfr;

    /* Jbvb tirfbd stbtus for tools,
     * initiblizfd to indidbtf tirfbd 'not yft stbrtfd'
     */

    privbtf volbtilf int tirfbdStbtus = 0;


    privbtf stbtid syndironizfd long nfxtTirfbdID() {
        rfturn ++tirfbdSfqNumbfr;
    }

    /**
     * Tif brgumfnt supplifd to tif durrfnt dbll to
     * jbvb.util.dondurrfnt.lodks.LodkSupport.pbrk.
     * Sft by (privbtf) jbvb.util.dondurrfnt.lodks.LodkSupport.sftBlodkfr
     * Addfssfd using jbvb.util.dondurrfnt.lodks.LodkSupport.gftBlodkfr
     */
    volbtilf Objfdt pbrkBlodkfr;

    /* Tif objfdt in wiidi tiis tirfbd is blodkfd in bn intfrruptiblf I/O
     * opfrbtion, if bny.  Tif blodkfr's intfrrupt mftiod siould bf invokfd
     * bftfr sftting tiis tirfbd's intfrrupt stbtus.
     */
    privbtf volbtilf Intfrruptiblf blodkfr;
    privbtf finbl Objfdt blodkfrLodk = nfw Objfdt();

    /* Sft tif blodkfr fifld; invokfd vib sun.misd.SibrfdSfdrfts from jbvb.nio dodf
     */
    void blodkfdOn(Intfrruptiblf b) {
        syndironizfd (blodkfrLodk) {
            blodkfr = b;
        }
    }

    /**
     * Tif minimum priority tibt b tirfbd dbn ibvf.
     */
    publid finbl stbtid int MIN_PRIORITY = 1;

   /**
     * Tif dffbult priority tibt is bssignfd to b tirfbd.
     */
    publid finbl stbtid int NORM_PRIORITY = 5;

    /**
     * Tif mbximum priority tibt b tirfbd dbn ibvf.
     */
    publid finbl stbtid int MAX_PRIORITY = 10;

    /**
     * Rfturns b rfffrfndf to tif durrfntly fxfduting tirfbd objfdt.
     *
     * @rfturn  tif durrfntly fxfduting tirfbd.
     */
    publid stbtid nbtivf Tirfbd durrfntTirfbd();

    /**
     * A iint to tif sdifdulfr tibt tif durrfnt tirfbd is willing to yifld
     * its durrfnt usf of b prodfssor. Tif sdifdulfr is frff to ignorf tiis
     * iint.
     *
     * <p> Yifld is b ifuristid bttfmpt to improvf rflbtivf progrfssion
     * bftwffn tirfbds tibt would otifrwisf ovfr-utilisf b CPU. Its usf
     * siould bf dombinfd witi dftbilfd profiling bnd bfndimbrking to
     * fnsurf tibt it bdtublly ibs tif dfsirfd ffffdt.
     *
     * <p> It is rbrfly bppropribtf to usf tiis mftiod. It mby bf usfful
     * for dfbugging or tfsting purposfs, wifrf it mby iflp to rfprodudf
     * bugs duf to rbdf donditions. It mby blso bf usfful wifn dfsigning
     * dondurrfndy dontrol donstrudts sudi bs tif onfs in tif
     * {@link jbvb.util.dondurrfnt.lodks} pbdkbgf.
     */
    publid stbtid nbtivf void yifld();

    /**
     * Cbusfs tif durrfntly fxfduting tirfbd to slffp (tfmporbrily dfbsf
     * fxfdution) for tif spfdififd numbfr of millisfdonds, subjfdt to
     * tif prfdision bnd bddurbdy of systfm timfrs bnd sdifdulfrs. Tif tirfbd
     * dofs not losf ownfrsiip of bny monitors.
     *
     * @pbrbm  millis
     *         tif lfngti of timf to slffp in millisfdonds
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif vbluf of {@dodf millis} is nfgbtivf
     *
     * @tirows  IntfrruptfdExdfption
     *          if bny tirfbd ibs intfrruptfd tif durrfnt tirfbd. Tif
     *          <i>intfrruptfd stbtus</i> of tif durrfnt tirfbd is
     *          dlfbrfd wifn tiis fxdfption is tirown.
     */
    publid stbtid nbtivf void slffp(long millis) tirows IntfrruptfdExdfption;

    /**
     * Cbusfs tif durrfntly fxfduting tirfbd to slffp (tfmporbrily dfbsf
     * fxfdution) for tif spfdififd numbfr of millisfdonds plus tif spfdififd
     * numbfr of nbnosfdonds, subjfdt to tif prfdision bnd bddurbdy of systfm
     * timfrs bnd sdifdulfrs. Tif tirfbd dofs not losf ownfrsiip of bny
     * monitors.
     *
     * @pbrbm  millis
     *         tif lfngti of timf to slffp in millisfdonds
     *
     * @pbrbm  nbnos
     *         {@dodf 0-999999} bdditionbl nbnosfdonds to slffp
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif vbluf of {@dodf millis} is nfgbtivf, or tif vbluf of
     *          {@dodf nbnos} is not in tif rbngf {@dodf 0-999999}
     *
     * @tirows  IntfrruptfdExdfption
     *          if bny tirfbd ibs intfrruptfd tif durrfnt tirfbd. Tif
     *          <i>intfrruptfd stbtus</i> of tif durrfnt tirfbd is
     *          dlfbrfd wifn tiis fxdfption is tirown.
     */
    publid stbtid void slffp(long millis, int nbnos)
    tirows IntfrruptfdExdfption {
        if (millis < 0) {
            tirow nfw IllfgblArgumfntExdfption("timfout vbluf is nfgbtivf");
        }

        if (nbnos < 0 || nbnos > 999999) {
            tirow nfw IllfgblArgumfntExdfption(
                                "nbnosfdond timfout vbluf out of rbngf");
        }

        if (nbnos >= 500000 || (nbnos != 0 && millis == 0)) {
            millis++;
        }

        slffp(millis);
    }

    /**
     * Initiblizfs b Tirfbd witi tif durrfnt AddfssControlContfxt.
     * @sff #init(TirfbdGroup,Runnbblf,String,long,AddfssControlContfxt)
     */
    privbtf void init(TirfbdGroup g, Runnbblf tbrgft, String nbmf,
                      long stbdkSizf) {
        init(g, tbrgft, nbmf, stbdkSizf, null);
    }

    /**
     * Initiblizfs b Tirfbd.
     *
     * @pbrbm g tif Tirfbd group
     * @pbrbm tbrgft tif objfdt wiosf run() mftiod gfts dbllfd
     * @pbrbm nbmf tif nbmf of tif nfw Tirfbd
     * @pbrbm stbdkSizf tif dfsirfd stbdk sizf for tif nfw tirfbd, or
     *        zfro to indidbtf tibt tiis pbrbmftfr is to bf ignorfd.
     * @pbrbm bdd tif AddfssControlContfxt to inifrit, or
     *            AddfssControllfr.gftContfxt() if null
     */
    privbtf void init(TirfbdGroup g, Runnbblf tbrgft, String nbmf,
                      long stbdkSizf, AddfssControlContfxt bdd) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("nbmf dbnnot bf null");
        }

        tiis.nbmf = nbmf.toCibrArrby();

        Tirfbd pbrfnt = durrfntTirfbd();
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (g == null) {
            /* Dftfrminf if it's bn bpplft or not */

            /* If tifrf is b sfdurity mbnbgfr, bsk tif sfdurity mbnbgfr
               wibt to do. */
            if (sfdurity != null) {
                g = sfdurity.gftTirfbdGroup();
            }

            /* If tif sfdurity dofsn't ibvf b strong opinion of tif mbttfr
               usf tif pbrfnt tirfbd group. */
            if (g == null) {
                g = pbrfnt.gftTirfbdGroup();
            }
        }

        /* difdkAddfss rfgbrdlfss of wiftifr or not tirfbdgroup is
           fxpliditly pbssfd in. */
        g.difdkAddfss();

        /*
         * Do wf ibvf tif rfquirfd pfrmissions?
         */
        if (sfdurity != null) {
            if (isCCLOvfrriddfn(gftClbss())) {
                sfdurity.difdkPfrmission(SUBCLASS_IMPLEMENTATION_PERMISSION);
            }
        }

        g.bddUnstbrtfd();

        tiis.group = g;
        tiis.dbfmon = pbrfnt.isDbfmon();
        tiis.priority = pbrfnt.gftPriority();
        if (sfdurity == null || isCCLOvfrriddfn(pbrfnt.gftClbss()))
            tiis.dontfxtClbssLobdfr = pbrfnt.gftContfxtClbssLobdfr();
        flsf
            tiis.dontfxtClbssLobdfr = pbrfnt.dontfxtClbssLobdfr;
        tiis.inifritfdAddfssControlContfxt =
                bdd != null ? bdd : AddfssControllfr.gftContfxt();
        tiis.tbrgft = tbrgft;
        sftPriority(priority);
        if (pbrfnt.inifritbblfTirfbdLodbls != null)
            tiis.inifritbblfTirfbdLodbls =
                TirfbdLodbl.drfbtfInifritfdMbp(pbrfnt.inifritbblfTirfbdLodbls);
        /* Stbsi tif spfdififd stbdk sizf in dbsf tif VM dbrfs */
        tiis.stbdkSizf = stbdkSizf;

        /* Sft tirfbd ID */
        tid = nfxtTirfbdID();
    }

    /**
     * Tirows ClonfNotSupportfdExdfption bs b Tirfbd dbn not bf mfbningfully
     * dlonfd. Construdt b nfw Tirfbd instfbd.
     *
     * @tirows  ClonfNotSupportfdExdfption
     *          blwbys
     */
    @Ovfrridf
    protfdtfd Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        tirow nfw ClonfNotSupportfdExdfption();
    }

    /**
     * Allodbtfs b nfw {@dodf Tirfbd} objfdt. Tiis donstrudtor ibs tif sbmf
     * ffffdt bs {@linkplbin #Tirfbd(TirfbdGroup,Runnbblf,String) Tirfbd}
     * {@dodf (null, null, gnbmf)}, wifrf {@dodf gnbmf} is b nfwly gfnfrbtfd
     * nbmf. Autombtidblly gfnfrbtfd nbmfs brf of tif form
     * {@dodf "Tirfbd-"+}<i>n</i>, wifrf <i>n</i> is bn intfgfr.
     */
    publid Tirfbd() {
        init(null, null, "Tirfbd-" + nfxtTirfbdNum(), 0);
    }

    /**
     * Allodbtfs b nfw {@dodf Tirfbd} objfdt. Tiis donstrudtor ibs tif sbmf
     * ffffdt bs {@linkplbin #Tirfbd(TirfbdGroup,Runnbblf,String) Tirfbd}
     * {@dodf (null, tbrgft, gnbmf)}, wifrf {@dodf gnbmf} is b nfwly gfnfrbtfd
     * nbmf. Autombtidblly gfnfrbtfd nbmfs brf of tif form
     * {@dodf "Tirfbd-"+}<i>n</i>, wifrf <i>n</i> is bn intfgfr.
     *
     * @pbrbm  tbrgft
     *         tif objfdt wiosf {@dodf run} mftiod is invokfd wifn tiis tirfbd
     *         is stbrtfd. If {@dodf null}, tiis dlbssfs {@dodf run} mftiod dofs
     *         notiing.
     */
    publid Tirfbd(Runnbblf tbrgft) {
        init(null, tbrgft, "Tirfbd-" + nfxtTirfbdNum(), 0);
    }

    /**
     * Crfbtfs b nfw Tirfbd tibt inifrits tif givfn AddfssControlContfxt.
     * Tiis is not b publid donstrudtor.
     */
    Tirfbd(Runnbblf tbrgft, AddfssControlContfxt bdd) {
        init(null, tbrgft, "Tirfbd-" + nfxtTirfbdNum(), 0, bdd);
    }

    /**
     * Allodbtfs b nfw {@dodf Tirfbd} objfdt. Tiis donstrudtor ibs tif sbmf
     * ffffdt bs {@linkplbin #Tirfbd(TirfbdGroup,Runnbblf,String) Tirfbd}
     * {@dodf (group, tbrgft, gnbmf)} ,wifrf {@dodf gnbmf} is b nfwly gfnfrbtfd
     * nbmf. Autombtidblly gfnfrbtfd nbmfs brf of tif form
     * {@dodf "Tirfbd-"+}<i>n</i>, wifrf <i>n</i> is bn intfgfr.
     *
     * @pbrbm  group
     *         tif tirfbd group. If {@dodf null} bnd tifrf is b sfdurity
     *         mbnbgfr, tif group is dftfrminfd by {@linkplbin
     *         SfdurityMbnbgfr#gftTirfbdGroup SfdurityMbnbgfr.gftTirfbdGroup()}.
     *         If tifrf is not b sfdurity mbnbgfr or {@dodf
     *         SfdurityMbnbgfr.gftTirfbdGroup()} rfturns {@dodf null}, tif group
     *         is sft to tif durrfnt tirfbd's tirfbd group.
     *
     * @pbrbm  tbrgft
     *         tif objfdt wiosf {@dodf run} mftiod is invokfd wifn tiis tirfbd
     *         is stbrtfd. If {@dodf null}, tiis tirfbd's run mftiod is invokfd.
     *
     * @tirows  SfdurityExdfption
     *          if tif durrfnt tirfbd dbnnot drfbtf b tirfbd in tif spfdififd
     *          tirfbd group
     */
    publid Tirfbd(TirfbdGroup group, Runnbblf tbrgft) {
        init(group, tbrgft, "Tirfbd-" + nfxtTirfbdNum(), 0);
    }

    /**
     * Allodbtfs b nfw {@dodf Tirfbd} objfdt. Tiis donstrudtor ibs tif sbmf
     * ffffdt bs {@linkplbin #Tirfbd(TirfbdGroup,Runnbblf,String) Tirfbd}
     * {@dodf (null, null, nbmf)}.
     *
     * @pbrbm   nbmf
     *          tif nbmf of tif nfw tirfbd
     */
    publid Tirfbd(String nbmf) {
        init(null, null, nbmf, 0);
    }

    /**
     * Allodbtfs b nfw {@dodf Tirfbd} objfdt. Tiis donstrudtor ibs tif sbmf
     * ffffdt bs {@linkplbin #Tirfbd(TirfbdGroup,Runnbblf,String) Tirfbd}
     * {@dodf (group, null, nbmf)}.
     *
     * @pbrbm  group
     *         tif tirfbd group. If {@dodf null} bnd tifrf is b sfdurity
     *         mbnbgfr, tif group is dftfrminfd by {@linkplbin
     *         SfdurityMbnbgfr#gftTirfbdGroup SfdurityMbnbgfr.gftTirfbdGroup()}.
     *         If tifrf is not b sfdurity mbnbgfr or {@dodf
     *         SfdurityMbnbgfr.gftTirfbdGroup()} rfturns {@dodf null}, tif group
     *         is sft to tif durrfnt tirfbd's tirfbd group.
     *
     * @pbrbm  nbmf
     *         tif nbmf of tif nfw tirfbd
     *
     * @tirows  SfdurityExdfption
     *          if tif durrfnt tirfbd dbnnot drfbtf b tirfbd in tif spfdififd
     *          tirfbd group
     */
    publid Tirfbd(TirfbdGroup group, String nbmf) {
        init(group, null, nbmf, 0);
    }

    /**
     * Allodbtfs b nfw {@dodf Tirfbd} objfdt. Tiis donstrudtor ibs tif sbmf
     * ffffdt bs {@linkplbin #Tirfbd(TirfbdGroup,Runnbblf,String) Tirfbd}
     * {@dodf (null, tbrgft, nbmf)}.
     *
     * @pbrbm  tbrgft
     *         tif objfdt wiosf {@dodf run} mftiod is invokfd wifn tiis tirfbd
     *         is stbrtfd. If {@dodf null}, tiis tirfbd's run mftiod is invokfd.
     *
     * @pbrbm  nbmf
     *         tif nbmf of tif nfw tirfbd
     */
    publid Tirfbd(Runnbblf tbrgft, String nbmf) {
        init(null, tbrgft, nbmf, 0);
    }

    /**
     * Allodbtfs b nfw {@dodf Tirfbd} objfdt so tibt it ibs {@dodf tbrgft}
     * bs its run objfdt, ibs tif spfdififd {@dodf nbmf} bs its nbmf,
     * bnd bflongs to tif tirfbd group rfffrrfd to by {@dodf group}.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, its
     * {@link SfdurityMbnbgfr#difdkAddfss(TirfbdGroup) difdkAddfss}
     * mftiod is invokfd witi tif TirfbdGroup bs its brgumfnt.
     *
     * <p>In bddition, its {@dodf difdkPfrmission} mftiod is invokfd witi
     * tif {@dodf RuntimfPfrmission("fnbblfContfxtClbssLobdfrOvfrridf")}
     * pfrmission wifn invokfd dirfdtly or indirfdtly by tif donstrudtor
     * of b subdlbss wiidi ovfrridfs tif {@dodf gftContfxtClbssLobdfr}
     * or {@dodf sftContfxtClbssLobdfr} mftiods.
     *
     * <p>Tif priority of tif nfwly drfbtfd tirfbd is sft fqubl to tif
     * priority of tif tirfbd drfbting it, tibt is, tif durrfntly running
     * tirfbd. Tif mftiod {@linkplbin #sftPriority sftPriority} mby bf
     * usfd to dibngf tif priority to b nfw vbluf.
     *
     * <p>Tif nfwly drfbtfd tirfbd is initiblly mbrkfd bs bfing b dbfmon
     * tirfbd if bnd only if tif tirfbd drfbting it is durrfntly mbrkfd
     * bs b dbfmon tirfbd. Tif mftiod {@linkplbin #sftDbfmon sftDbfmon}
     * mby bf usfd to dibngf wiftifr or not b tirfbd is b dbfmon.
     *
     * @pbrbm  group
     *         tif tirfbd group. If {@dodf null} bnd tifrf is b sfdurity
     *         mbnbgfr, tif group is dftfrminfd by {@linkplbin
     *         SfdurityMbnbgfr#gftTirfbdGroup SfdurityMbnbgfr.gftTirfbdGroup()}.
     *         If tifrf is not b sfdurity mbnbgfr or {@dodf
     *         SfdurityMbnbgfr.gftTirfbdGroup()} rfturns {@dodf null}, tif group
     *         is sft to tif durrfnt tirfbd's tirfbd group.
     *
     * @pbrbm  tbrgft
     *         tif objfdt wiosf {@dodf run} mftiod is invokfd wifn tiis tirfbd
     *         is stbrtfd. If {@dodf null}, tiis tirfbd's run mftiod is invokfd.
     *
     * @pbrbm  nbmf
     *         tif nbmf of tif nfw tirfbd
     *
     * @tirows  SfdurityExdfption
     *          if tif durrfnt tirfbd dbnnot drfbtf b tirfbd in tif spfdififd
     *          tirfbd group or dbnnot ovfrridf tif dontfxt dlbss lobdfr mftiods.
     */
    publid Tirfbd(TirfbdGroup group, Runnbblf tbrgft, String nbmf) {
        init(group, tbrgft, nbmf, 0);
    }

    /**
     * Allodbtfs b nfw {@dodf Tirfbd} objfdt so tibt it ibs {@dodf tbrgft}
     * bs its run objfdt, ibs tif spfdififd {@dodf nbmf} bs its nbmf,
     * bnd bflongs to tif tirfbd group rfffrrfd to by {@dodf group}, bnd ibs
     * tif spfdififd <i>stbdk sizf</i>.
     *
     * <p>Tiis donstrudtor is idfntidbl to {@link
     * #Tirfbd(TirfbdGroup,Runnbblf,String)} witi tif fxdfption of tif fbdt
     * tibt it bllows tif tirfbd stbdk sizf to bf spfdififd.  Tif stbdk sizf
     * is tif bpproximbtf numbfr of bytfs of bddrfss spbdf tibt tif virtubl
     * mbdiinf is to bllodbtf for tiis tirfbd's stbdk.  <b>Tif ffffdt of tif
     * {@dodf stbdkSizf} pbrbmftfr, if bny, is iigily plbtform dfpfndfnt.</b>
     *
     * <p>On somf plbtforms, spfdifying b iigifr vbluf for tif
     * {@dodf stbdkSizf} pbrbmftfr mby bllow b tirfbd to bdiifvf grfbtfr
     * rfdursion dfpti bfforf tirowing b {@link StbdkOvfrflowError}.
     * Similbrly, spfdifying b lowfr vbluf mby bllow b grfbtfr numbfr of
     * tirfbds to fxist dondurrfntly witiout tirowing bn {@link
     * OutOfMfmoryError} (or otifr intfrnbl frror).  Tif dftbils of
     * tif rflbtionsiip bftwffn tif vbluf of tif <tt>stbdkSizf</tt> pbrbmftfr
     * bnd tif mbximum rfdursion dfpti bnd dondurrfndy lfvfl brf
     * plbtform-dfpfndfnt.  <b>On somf plbtforms, tif vbluf of tif
     * {@dodf stbdkSizf} pbrbmftfr mby ibvf no ffffdt wibtsofvfr.</b>
     *
     * <p>Tif virtubl mbdiinf is frff to trfbt tif {@dodf stbdkSizf}
     * pbrbmftfr bs b suggfstion.  If tif spfdififd vbluf is unrfbsonbbly low
     * for tif plbtform, tif virtubl mbdiinf mby instfbd usf somf
     * plbtform-spfdifid minimum vbluf; if tif spfdififd vbluf is unrfbsonbbly
     * iigi, tif virtubl mbdiinf mby instfbd usf somf plbtform-spfdifid
     * mbximum.  Likfwisf, tif virtubl mbdiinf is frff to round tif spfdififd
     * vbluf up or down bs it sffs fit (or to ignorf it domplftfly).
     *
     * <p>Spfdifying b vbluf of zfro for tif {@dodf stbdkSizf} pbrbmftfr will
     * dbusf tiis donstrudtor to bfibvf fxbdtly likf tif
     * {@dodf Tirfbd(TirfbdGroup, Runnbblf, String)} donstrudtor.
     *
     * <p><i>Duf to tif plbtform-dfpfndfnt nbturf of tif bfibvior of tiis
     * donstrudtor, fxtrfmf dbrf siould bf fxfrdisfd in its usf.
     * Tif tirfbd stbdk sizf nfdfssbry to pfrform b givfn domputbtion will
     * likfly vbry from onf JRE implfmfntbtion to bnotifr.  In ligit of tiis
     * vbribtion, dbrfful tuning of tif stbdk sizf pbrbmftfr mby bf rfquirfd,
     * bnd tif tuning mby nffd to bf rfpfbtfd for fbdi JRE implfmfntbtion on
     * wiidi bn bpplidbtion is to run.</i>
     *
     * <p>Implfmfntbtion notf: Jbvb plbtform implfmfntfrs brf fndourbgfd to
     * dodumfnt tifir implfmfntbtion's bfibvior witi rfspfdt to tif
     * {@dodf stbdkSizf} pbrbmftfr.
     *
     *
     * @pbrbm  group
     *         tif tirfbd group. If {@dodf null} bnd tifrf is b sfdurity
     *         mbnbgfr, tif group is dftfrminfd by {@linkplbin
     *         SfdurityMbnbgfr#gftTirfbdGroup SfdurityMbnbgfr.gftTirfbdGroup()}.
     *         If tifrf is not b sfdurity mbnbgfr or {@dodf
     *         SfdurityMbnbgfr.gftTirfbdGroup()} rfturns {@dodf null}, tif group
     *         is sft to tif durrfnt tirfbd's tirfbd group.
     *
     * @pbrbm  tbrgft
     *         tif objfdt wiosf {@dodf run} mftiod is invokfd wifn tiis tirfbd
     *         is stbrtfd. If {@dodf null}, tiis tirfbd's run mftiod is invokfd.
     *
     * @pbrbm  nbmf
     *         tif nbmf of tif nfw tirfbd
     *
     * @pbrbm  stbdkSizf
     *         tif dfsirfd stbdk sizf for tif nfw tirfbd, or zfro to indidbtf
     *         tibt tiis pbrbmftfr is to bf ignorfd.
     *
     * @tirows  SfdurityExdfption
     *          if tif durrfnt tirfbd dbnnot drfbtf b tirfbd in tif spfdififd
     *          tirfbd group
     *
     * @sindf 1.4
     */
    publid Tirfbd(TirfbdGroup group, Runnbblf tbrgft, String nbmf,
                  long stbdkSizf) {
        init(group, tbrgft, nbmf, stbdkSizf);
    }

    /**
     * Cbusfs tiis tirfbd to bfgin fxfdution; tif Jbvb Virtubl Mbdiinf
     * dblls tif <dodf>run</dodf> mftiod of tiis tirfbd.
     * <p>
     * Tif rfsult is tibt two tirfbds brf running dondurrfntly: tif
     * durrfnt tirfbd (wiidi rfturns from tif dbll to tif
     * <dodf>stbrt</dodf> mftiod) bnd tif otifr tirfbd (wiidi fxfdutfs its
     * <dodf>run</dodf> mftiod).
     * <p>
     * It is nfvfr lfgbl to stbrt b tirfbd morf tibn ondf.
     * In pbrtidulbr, b tirfbd mby not bf rfstbrtfd ondf it ibs domplftfd
     * fxfdution.
     *
     * @fxdfption  IllfgblTirfbdStbtfExdfption  if tif tirfbd wbs blrfbdy
     *               stbrtfd.
     * @sff        #run()
     * @sff        #stop()
     */
    publid syndironizfd void stbrt() {
        /**
         * Tiis mftiod is not invokfd for tif mbin mftiod tirfbd or "systfm"
         * group tirfbds drfbtfd/sft up by tif VM. Any nfw fundtionblity bddfd
         * to tiis mftiod in tif futurf mby ibvf to blso bf bddfd to tif VM.
         *
         * A zfro stbtus vbluf dorrfsponds to stbtf "NEW".
         */
        if (tirfbdStbtus != 0)
            tirow nfw IllfgblTirfbdStbtfExdfption();

        /* Notify tif group tibt tiis tirfbd is bbout to bf stbrtfd
         * so tibt it dbn bf bddfd to tif group's list of tirfbds
         * bnd tif group's unstbrtfd dount dbn bf dfdrfmfntfd. */
        group.bdd(tiis);

        boolfbn stbrtfd = fblsf;
        try {
            stbrt0();
            stbrtfd = truf;
        } finblly {
            try {
                if (!stbrtfd) {
                    group.tirfbdStbrtFbilfd(tiis);
                }
            } dbtdi (Tirowbblf ignorf) {
                /* do notiing. If stbrt0 tirfw b Tirowbblf tifn
                  it will bf pbssfd up tif dbll stbdk */
            }
        }
    }

    privbtf nbtivf void stbrt0();

    /**
     * If tiis tirfbd wbs donstrudtfd using b sfpbrbtf
     * <dodf>Runnbblf</dodf> run objfdt, tifn tibt
     * <dodf>Runnbblf</dodf> objfdt's <dodf>run</dodf> mftiod is dbllfd;
     * otifrwisf, tiis mftiod dofs notiing bnd rfturns.
     * <p>
     * Subdlbssfs of <dodf>Tirfbd</dodf> siould ovfrridf tiis mftiod.
     *
     * @sff     #stbrt()
     * @sff     #stop()
     * @sff     #Tirfbd(TirfbdGroup, Runnbblf, String)
     */
    @Ovfrridf
    publid void run() {
        if (tbrgft != null) {
            tbrgft.run();
        }
    }

    /**
     * Tiis mftiod is dbllfd by tif systfm to givf b Tirfbd
     * b dibndf to dlfbn up bfforf it bdtublly fxits.
     */
    privbtf void fxit() {
        if (group != null) {
            group.tirfbdTfrminbtfd(tiis);
            group = null;
        }
        /* Aggrfssivfly null out bll rfffrfndf fiflds: sff bug 4006245 */
        tbrgft = null;
        /* Spffd tif rflfbsf of somf of tifsf rfsourdfs */
        tirfbdLodbls = null;
        inifritbblfTirfbdLodbls = null;
        inifritfdAddfssControlContfxt = null;
        blodkfr = null;
        undbugitExdfptionHbndlfr = null;
    }

    /**
     * Fordfs tif tirfbd to stop fxfduting.
     * <p>
     * If tifrf is b sfdurity mbnbgfr instbllfd, its <dodf>difdkAddfss</dodf>
     * mftiod is dbllfd witi <dodf>tiis</dodf>
     * bs its brgumfnt. Tiis mby rfsult in b
     * <dodf>SfdurityExdfption</dodf> bfing rbisfd (in tif durrfnt tirfbd).
     * <p>
     * If tiis tirfbd is difffrfnt from tif durrfnt tirfbd (tibt is, tif durrfnt
     * tirfbd is trying to stop b tirfbd otifr tibn itsflf), tif
     * sfdurity mbnbgfr's <dodf>difdkPfrmission</dodf> mftiod (witi b
     * <dodf>RuntimfPfrmission("stopTirfbd")</dodf> brgumfnt) is dbllfd in
     * bddition.
     * Agbin, tiis mby rfsult in tirowing b
     * <dodf>SfdurityExdfption</dodf> (in tif durrfnt tirfbd).
     * <p>
     * Tif tirfbd rfprfsfntfd by tiis tirfbd is fordfd to stop wibtfvfr
     * it is doing bbnormblly bnd to tirow b nfwly drfbtfd
     * <dodf>TirfbdDfbti</dodf> objfdt bs bn fxdfption.
     * <p>
     * It is pfrmittfd to stop b tirfbd tibt ibs not yft bffn stbrtfd.
     * If tif tirfbd is fvfntublly stbrtfd, it immfdibtfly tfrminbtfs.
     * <p>
     * An bpplidbtion siould not normblly try to dbtdi
     * <dodf>TirfbdDfbti</dodf> unlfss it must do somf fxtrbordinbry
     * dlfbnup opfrbtion (notf tibt tif tirowing of
     * <dodf>TirfbdDfbti</dodf> dbusfs <dodf>finblly</dodf> dlbusfs of
     * <dodf>try</dodf> stbtfmfnts to bf fxfdutfd bfforf tif tirfbd
     * offidiblly difs).  If b <dodf>dbtdi</dodf> dlbusf dbtdifs b
     * <dodf>TirfbdDfbti</dodf> objfdt, it is importbnt to rftirow tif
     * objfdt so tibt tif tirfbd bdtublly difs.
     * <p>
     * Tif top-lfvfl frror ibndlfr tibt rfbdts to otifrwisf undbugit
     * fxdfptions dofs not print out b mfssbgf or otifrwisf notify tif
     * bpplidbtion if tif undbugit fxdfption is bn instbndf of
     * <dodf>TirfbdDfbti</dodf>.
     *
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd dbnnot
     *               modify tiis tirfbd.
     * @sff        #intfrrupt()
     * @sff        #difdkAddfss()
     * @sff        #run()
     * @sff        #stbrt()
     * @sff        TirfbdDfbti
     * @sff        TirfbdGroup#undbugitExdfption(Tirfbd,Tirowbblf)
     * @sff        SfdurityMbnbgfr#difdkAddfss(Tirfbd)
     * @sff        SfdurityMbnbgfr#difdkPfrmission
     * @dfprfdbtfd Tiis mftiod is inifrfntly unsbff.  Stopping b tirfbd witi
     *       Tirfbd.stop dbusfs it to unlodk bll of tif monitors tibt it
     *       ibs lodkfd (bs b nbturbl donsfqufndf of tif undifdkfd
     *       <dodf>TirfbdDfbti</dodf> fxdfption propbgbting up tif stbdk).  If
     *       bny of tif objfdts prfviously protfdtfd by tifsf monitors wfrf in
     *       bn indonsistfnt stbtf, tif dbmbgfd objfdts bfdomf visiblf to
     *       otifr tirfbds, potfntiblly rfsulting in brbitrbry bfibvior.  Mbny
     *       usfs of <dodf>stop</dodf> siould bf rfplbdfd by dodf tibt simply
     *       modififs somf vbribblf to indidbtf tibt tif tbrgft tirfbd siould
     *       stop running.  Tif tbrgft tirfbd siould difdk tiis vbribblf
     *       rfgulbrly, bnd rfturn from its run mftiod in bn ordfrly fbsiion
     *       if tif vbribblf indidbtfs tibt it is to stop running.  If tif
     *       tbrgft tirfbd wbits for long pfriods (on b dondition vbribblf,
     *       for fxbmplf), tif <dodf>intfrrupt</dodf> mftiod siould bf usfd to
     *       intfrrupt tif wbit.
     *       For morf informbtion, sff
     *       <b irff="{@dodRoot}/../tfdinotfs/guidfs/dondurrfndy/tirfbdPrimitivfDfprfdbtion.itml">Wiy
     *       brf Tirfbd.stop, Tirfbd.suspfnd bnd Tirfbd.rfsumf Dfprfdbtfd?</b>.
     */
    @Dfprfdbtfd
    publid finbl void stop() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            difdkAddfss();
            if (tiis != Tirfbd.durrfntTirfbd()) {
                sfdurity.difdkPfrmission(SfdurityConstbnts.STOP_THREAD_PERMISSION);
            }
        }
        // A zfro stbtus vbluf dorrfsponds to "NEW", it dbn't dibngf to
        // not-NEW bfdbusf wf iold tif lodk.
        if (tirfbdStbtus != 0) {
            rfsumf(); // Wbkf up tirfbd if it wbs suspfndfd; no-op otifrwisf
        }

        // Tif VM dbn ibndlf bll tirfbd stbtfs
        stop0(nfw TirfbdDfbti());
    }

    /**
     * Tirows {@dodf UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm obj ignorfd
     *
     * @dfprfdbtfd Tiis mftiod wbs originblly dfsignfd to fordf b tirfbd to stop
     *        bnd tirow b givfn {@dodf Tirowbblf} bs bn fxdfption. It wbs
     *        inifrfntly unsbff (sff {@link #stop()} for dftbils), bnd furtifrmorf
     *        dould bf usfd to gfnfrbtf fxdfptions tibt tif tbrgft tirfbd wbs
     *        not prfpbrfd to ibndlf.
     *        For morf informbtion, sff
     *        <b irff="{@dodRoot}/../tfdinotfs/guidfs/dondurrfndy/tirfbdPrimitivfDfprfdbtion.itml">Wiy
     *        brf Tirfbd.stop, Tirfbd.suspfnd bnd Tirfbd.rfsumf Dfprfdbtfd?</b>.
     */
    @Dfprfdbtfd
    publid finbl syndironizfd void stop(Tirowbblf obj) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Intfrrupts tiis tirfbd.
     *
     * <p> Unlfss tif durrfnt tirfbd is intfrrupting itsflf, wiidi is
     * blwbys pfrmittfd, tif {@link #difdkAddfss() difdkAddfss} mftiod
     * of tiis tirfbd is invokfd, wiidi mby dbusf b {@link
     * SfdurityExdfption} to bf tirown.
     *
     * <p> If tiis tirfbd is blodkfd in bn invodbtion of tif {@link
     * Objfdt#wbit() wbit()}, {@link Objfdt#wbit(long) wbit(long)}, or {@link
     * Objfdt#wbit(long, int) wbit(long, int)} mftiods of tif {@link Objfdt}
     * dlbss, or of tif {@link #join()}, {@link #join(long)}, {@link
     * #join(long, int)}, {@link #slffp(long)}, or {@link #slffp(long, int)},
     * mftiods of tiis dlbss, tifn its intfrrupt stbtus will bf dlfbrfd bnd it
     * will rfdfivf bn {@link IntfrruptfdExdfption}.
     *
     * <p> If tiis tirfbd is blodkfd in bn I/O opfrbtion upon bn {@link
     * jbvb.nio.dibnnfls.IntfrruptiblfCibnnfl IntfrruptiblfCibnnfl}
     * tifn tif dibnnfl will bf dlosfd, tif tirfbd's intfrrupt
     * stbtus will bf sft, bnd tif tirfbd will rfdfivf b {@link
     * jbvb.nio.dibnnfls.ClosfdByIntfrruptExdfption}.
     *
     * <p> If tiis tirfbd is blodkfd in b {@link jbvb.nio.dibnnfls.Sflfdtor}
     * tifn tif tirfbd's intfrrupt stbtus will bf sft bnd it will rfturn
     * immfdibtfly from tif sflfdtion opfrbtion, possibly witi b non-zfro
     * vbluf, just bs if tif sflfdtor's {@link
     * jbvb.nio.dibnnfls.Sflfdtor#wbkfup wbkfup} mftiod wfrf invokfd.
     *
     * <p> If nonf of tif prfvious donditions iold tifn tiis tirfbd's intfrrupt
     * stbtus will bf sft. </p>
     *
     * <p> Intfrrupting b tirfbd tibt is not blivf nffd not ibvf bny ffffdt.
     *
     * @tirows  SfdurityExdfption
     *          if tif durrfnt tirfbd dbnnot modify tiis tirfbd
     *
     * @rfvisfd 6.0
     * @spfd JSR-51
     */
    publid void intfrrupt() {
        if (tiis != Tirfbd.durrfntTirfbd())
            difdkAddfss();

        syndironizfd (blodkfrLodk) {
            Intfrruptiblf b = blodkfr;
            if (b != null) {
                intfrrupt0();           // Just to sft tif intfrrupt flbg
                b.intfrrupt(tiis);
                rfturn;
            }
        }
        intfrrupt0();
    }

    /**
     * Tfsts wiftifr tif durrfnt tirfbd ibs bffn intfrruptfd.  Tif
     * <i>intfrruptfd stbtus</i> of tif tirfbd is dlfbrfd by tiis mftiod.  In
     * otifr words, if tiis mftiod wfrf to bf dbllfd twidf in suddfssion, tif
     * sfdond dbll would rfturn fblsf (unlfss tif durrfnt tirfbd wfrf
     * intfrruptfd bgbin, bftfr tif first dbll ibd dlfbrfd its intfrruptfd
     * stbtus bnd bfforf tif sfdond dbll ibd fxbminfd it).
     *
     * <p>A tirfbd intfrruption ignorfd bfdbusf b tirfbd wbs not blivf
     * bt tif timf of tif intfrrupt will bf rfflfdtfd by tiis mftiod
     * rfturning fblsf.
     *
     * @rfturn  <dodf>truf</dodf> if tif durrfnt tirfbd ibs bffn intfrruptfd;
     *          <dodf>fblsf</dodf> otifrwisf.
     * @sff #isIntfrruptfd()
     * @rfvisfd 6.0
     */
    publid stbtid boolfbn intfrruptfd() {
        rfturn durrfntTirfbd().isIntfrruptfd(truf);
    }

    /**
     * Tfsts wiftifr tiis tirfbd ibs bffn intfrruptfd.  Tif <i>intfrruptfd
     * stbtus</i> of tif tirfbd is unbfffdtfd by tiis mftiod.
     *
     * <p>A tirfbd intfrruption ignorfd bfdbusf b tirfbd wbs not blivf
     * bt tif timf of tif intfrrupt will bf rfflfdtfd by tiis mftiod
     * rfturning fblsf.
     *
     * @rfturn  <dodf>truf</dodf> if tiis tirfbd ibs bffn intfrruptfd;
     *          <dodf>fblsf</dodf> otifrwisf.
     * @sff     #intfrruptfd()
     * @rfvisfd 6.0
     */
    publid boolfbn isIntfrruptfd() {
        rfturn isIntfrruptfd(fblsf);
    }

    /**
     * Tfsts if somf Tirfbd ibs bffn intfrruptfd.  Tif intfrruptfd stbtf
     * is rfsft or not bbsfd on tif vbluf of ClfbrIntfrruptfd tibt is
     * pbssfd.
     */
    privbtf nbtivf boolfbn isIntfrruptfd(boolfbn ClfbrIntfrruptfd);

    /**
     * Tirows {@link NoSudiMftiodError}.
     *
     * @dfprfdbtfd Tiis mftiod wbs originblly dfsignfd to dfstroy tiis
     *     tirfbd witiout bny dlfbnup. Any monitors it ifld would ibvf
     *     rfmbinfd lodkfd. Howfvfr, tif mftiod wbs nfvfr implfmfntfd.
     *     If it wfrf to bf implfmfntfd, it would bf dfbdlodk-pronf in
     *     mudi tif mbnnfr of {@link #suspfnd}. If tif tbrgft tirfbd ifld
     *     b lodk protfdting b dritidbl systfm rfsourdf wifn it wbs
     *     dfstroyfd, no tirfbd dould fvfr bddfss tiis rfsourdf bgbin.
     *     If bnotifr tirfbd fvfr bttfmptfd to lodk tiis rfsourdf, dfbdlodk
     *     would rfsult. Sudi dfbdlodks typidblly mbniffst tifmsflvfs bs
     *     "frozfn" prodfssfs. For morf informbtion, sff
     *     <b irff="{@dodRoot}/../tfdinotfs/guidfs/dondurrfndy/tirfbdPrimitivfDfprfdbtion.itml">
     *     Wiy brf Tirfbd.stop, Tirfbd.suspfnd bnd Tirfbd.rfsumf Dfprfdbtfd?</b>.
     * @tirows NoSudiMftiodError blwbys
     */
    @Dfprfdbtfd
    publid void dfstroy() {
        tirow nfw NoSudiMftiodError();
    }

    /**
     * Tfsts if tiis tirfbd is blivf. A tirfbd is blivf if it ibs
     * bffn stbrtfd bnd ibs not yft difd.
     *
     * @rfturn  <dodf>truf</dodf> if tiis tirfbd is blivf;
     *          <dodf>fblsf</dodf> otifrwisf.
     */
    publid finbl nbtivf boolfbn isAlivf();

    /**
     * Suspfnds tiis tirfbd.
     * <p>
     * First, tif <dodf>difdkAddfss</dodf> mftiod of tiis tirfbd is dbllfd
     * witi no brgumfnts. Tiis mby rfsult in tirowing b
     * <dodf>SfdurityExdfption </dodf>(in tif durrfnt tirfbd).
     * <p>
     * If tif tirfbd is blivf, it is suspfndfd bnd mbkfs no furtifr
     * progrfss unlfss bnd until it is rfsumfd.
     *
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd dbnnot modify
     *               tiis tirfbd.
     * @sff #difdkAddfss
     * @dfprfdbtfd   Tiis mftiod ibs bffn dfprfdbtfd, bs it is
     *   inifrfntly dfbdlodk-pronf.  If tif tbrgft tirfbd iolds b lodk on tif
     *   monitor protfdting b dritidbl systfm rfsourdf wifn it is suspfndfd, no
     *   tirfbd dbn bddfss tiis rfsourdf until tif tbrgft tirfbd is rfsumfd. If
     *   tif tirfbd tibt would rfsumf tif tbrgft tirfbd bttfmpts to lodk tiis
     *   monitor prior to dblling <dodf>rfsumf</dodf>, dfbdlodk rfsults.  Sudi
     *   dfbdlodks typidblly mbniffst tifmsflvfs bs "frozfn" prodfssfs.
     *   For morf informbtion, sff
     *   <b irff="{@dodRoot}/../tfdinotfs/guidfs/dondurrfndy/tirfbdPrimitivfDfprfdbtion.itml">Wiy
     *   brf Tirfbd.stop, Tirfbd.suspfnd bnd Tirfbd.rfsumf Dfprfdbtfd?</b>.
     */
    @Dfprfdbtfd
    publid finbl void suspfnd() {
        difdkAddfss();
        suspfnd0();
    }

    /**
     * Rfsumfs b suspfndfd tirfbd.
     * <p>
     * First, tif <dodf>difdkAddfss</dodf> mftiod of tiis tirfbd is dbllfd
     * witi no brgumfnts. Tiis mby rfsult in tirowing b
     * <dodf>SfdurityExdfption</dodf> (in tif durrfnt tirfbd).
     * <p>
     * If tif tirfbd is blivf but suspfndfd, it is rfsumfd bnd is
     * pfrmittfd to mbkf progrfss in its fxfdution.
     *
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd dbnnot modify tiis
     *               tirfbd.
     * @sff        #difdkAddfss
     * @sff        #suspfnd()
     * @dfprfdbtfd Tiis mftiod fxists solfly for usf witi {@link #suspfnd},
     *     wiidi ibs bffn dfprfdbtfd bfdbusf it is dfbdlodk-pronf.
     *     For morf informbtion, sff
     *     <b irff="{@dodRoot}/../tfdinotfs/guidfs/dondurrfndy/tirfbdPrimitivfDfprfdbtion.itml">Wiy
     *     brf Tirfbd.stop, Tirfbd.suspfnd bnd Tirfbd.rfsumf Dfprfdbtfd?</b>.
     */
    @Dfprfdbtfd
    publid finbl void rfsumf() {
        difdkAddfss();
        rfsumf0();
    }

    /**
     * Cibngfs tif priority of tiis tirfbd.
     * <p>
     * First tif <dodf>difdkAddfss</dodf> mftiod of tiis tirfbd is dbllfd
     * witi no brgumfnts. Tiis mby rfsult in tirowing b
     * <dodf>SfdurityExdfption</dodf>.
     * <p>
     * Otifrwisf, tif priority of tiis tirfbd is sft to tif smbllfr of
     * tif spfdififd <dodf>nfwPriority</dodf> bnd tif mbximum pfrmittfd
     * priority of tif tirfbd's tirfbd group.
     *
     * @pbrbm nfwPriority priority to sft tiis tirfbd to
     * @fxdfption  IllfgblArgumfntExdfption  If tif priority is not in tif
     *               rbngf <dodf>MIN_PRIORITY</dodf> to
     *               <dodf>MAX_PRIORITY</dodf>.
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd dbnnot modify
     *               tiis tirfbd.
     * @sff        #gftPriority
     * @sff        #difdkAddfss()
     * @sff        #gftTirfbdGroup()
     * @sff        #MAX_PRIORITY
     * @sff        #MIN_PRIORITY
     * @sff        TirfbdGroup#gftMbxPriority()
     */
    publid finbl void sftPriority(int nfwPriority) {
        TirfbdGroup g;
        difdkAddfss();
        if (nfwPriority > MAX_PRIORITY || nfwPriority < MIN_PRIORITY) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        if((g = gftTirfbdGroup()) != null) {
            if (nfwPriority > g.gftMbxPriority()) {
                nfwPriority = g.gftMbxPriority();
            }
            sftPriority0(priority = nfwPriority);
        }
    }

    /**
     * Rfturns tiis tirfbd's priority.
     *
     * @rfturn  tiis tirfbd's priority.
     * @sff     #sftPriority
     */
    publid finbl int gftPriority() {
        rfturn priority;
    }

    /**
     * Cibngfs tif nbmf of tiis tirfbd to bf fqubl to tif brgumfnt
     * <dodf>nbmf</dodf>.
     * <p>
     * First tif <dodf>difdkAddfss</dodf> mftiod of tiis tirfbd is dbllfd
     * witi no brgumfnts. Tiis mby rfsult in tirowing b
     * <dodf>SfdurityExdfption</dodf>.
     *
     * @pbrbm      nbmf   tif nfw nbmf for tiis tirfbd.
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd dbnnot modify tiis
     *               tirfbd.
     * @sff        #gftNbmf
     * @sff        #difdkAddfss()
     */
    publid finbl syndironizfd void sftNbmf(String nbmf) {
        difdkAddfss();
        tiis.nbmf = nbmf.toCibrArrby();
        if (tirfbdStbtus != 0) {
            sftNbtivfNbmf(nbmf);
        }
    }

    /**
     * Rfturns tiis tirfbd's nbmf.
     *
     * @rfturn  tiis tirfbd's nbmf.
     * @sff     #sftNbmf(String)
     */
    publid finbl String gftNbmf() {
        rfturn nfw String(nbmf, truf);
    }

    /**
     * Rfturns tif tirfbd group to wiidi tiis tirfbd bflongs.
     * Tiis mftiod rfturns null if tiis tirfbd ibs difd
     * (bffn stoppfd).
     *
     * @rfturn  tiis tirfbd's tirfbd group.
     */
    publid finbl TirfbdGroup gftTirfbdGroup() {
        rfturn group;
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of bdtivf tirfbds in tif durrfnt
     * tirfbd's {@linkplbin jbvb.lbng.TirfbdGroup tirfbd group} bnd its
     * subgroups. Rfdursivfly itfrbtfs ovfr bll subgroups in tif durrfnt
     * tirfbd's tirfbd group.
     *
     * <p> Tif vbluf rfturnfd is only bn fstimbtf bfdbusf tif numbfr of
     * tirfbds mby dibngf dynbmidblly wiilf tiis mftiod trbvfrsfs intfrnbl
     * dbtb strudturfs, bnd migit bf bfffdtfd by tif prfsfndf of dfrtbin
     * systfm tirfbds. Tiis mftiod is intfndfd primbrily for dfbugging
     * bnd monitoring purposfs.
     *
     * @rfturn  bn fstimbtf of tif numbfr of bdtivf tirfbds in tif durrfnt
     *          tirfbd's tirfbd group bnd in bny otifr tirfbd group tibt
     *          ibs tif durrfnt tirfbd's tirfbd group bs bn bndfstor
     */
    publid stbtid int bdtivfCount() {
        rfturn durrfntTirfbd().gftTirfbdGroup().bdtivfCount();
    }

    /**
     * Copifs into tif spfdififd brrby fvfry bdtivf tirfbd in tif durrfnt
     * tirfbd's tirfbd group bnd its subgroups. Tiis mftiod simply
     * invokfs tif {@link jbvb.lbng.TirfbdGroup#fnumfrbtf(Tirfbd[])}
     * mftiod of tif durrfnt tirfbd's tirfbd group.
     *
     * <p> An bpplidbtion migit usf tif {@linkplbin #bdtivfCount bdtivfCount}
     * mftiod to gft bn fstimbtf of iow big tif brrby siould bf, iowfvfr
     * <i>if tif brrby is too siort to iold bll tif tirfbds, tif fxtrb tirfbds
     * brf silfntly ignorfd.</i>  If it is dritidbl to obtbin fvfry bdtivf
     * tirfbd in tif durrfnt tirfbd's tirfbd group bnd its subgroups, tif
     * invokfr siould vfrify tibt tif rfturnfd int vbluf is stridtly lfss
     * tibn tif lfngti of {@dodf tbrrby}.
     *
     * <p> Duf to tif inifrfnt rbdf dondition in tiis mftiod, it is rfdommfndfd
     * tibt tif mftiod only bf usfd for dfbugging bnd monitoring purposfs.
     *
     * @pbrbm  tbrrby
     *         bn brrby into wiidi to put tif list of tirfbds
     *
     * @rfturn  tif numbfr of tirfbds put into tif brrby
     *
     * @tirows  SfdurityExdfption
     *          if {@link jbvb.lbng.TirfbdGroup#difdkAddfss} dftfrminfs tibt
     *          tif durrfnt tirfbd dbnnot bddfss its tirfbd group
     */
    publid stbtid int fnumfrbtf(Tirfbd tbrrby[]) {
        rfturn durrfntTirfbd().gftTirfbdGroup().fnumfrbtf(tbrrby);
    }

    /**
     * Counts tif numbfr of stbdk frbmfs in tiis tirfbd. Tif tirfbd must
     * bf suspfndfd.
     *
     * @rfturn     tif numbfr of stbdk frbmfs in tiis tirfbd.
     * @fxdfption  IllfgblTirfbdStbtfExdfption  if tiis tirfbd is not
     *             suspfndfd.
     * @dfprfdbtfd Tif dffinition of tiis dbll dfpfnds on {@link #suspfnd},
     *             wiidi is dfprfdbtfd.  Furtifr, tif rfsults of tiis dbll
     *             wfrf nfvfr wfll-dffinfd.
     */
    @Dfprfdbtfd
    publid nbtivf int dountStbdkFrbmfs();

    /**
     * Wbits bt most {@dodf millis} millisfdonds for tiis tirfbd to
     * dif. A timfout of {@dodf 0} mfbns to wbit forfvfr.
     *
     * <p> Tiis implfmfntbtion usfs b loop of {@dodf tiis.wbit} dblls
     * donditionfd on {@dodf tiis.isAlivf}. As b tirfbd tfrminbtfs tif
     * {@dodf tiis.notifyAll} mftiod is invokfd. It is rfdommfndfd tibt
     * bpplidbtions not usf {@dodf wbit}, {@dodf notify}, or
     * {@dodf notifyAll} on {@dodf Tirfbd} instbndfs.
     *
     * @pbrbm  millis
     *         tif timf to wbit in millisfdonds
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif vbluf of {@dodf millis} is nfgbtivf
     *
     * @tirows  IntfrruptfdExdfption
     *          if bny tirfbd ibs intfrruptfd tif durrfnt tirfbd. Tif
     *          <i>intfrruptfd stbtus</i> of tif durrfnt tirfbd is
     *          dlfbrfd wifn tiis fxdfption is tirown.
     */
    publid finbl syndironizfd void join(long millis)
    tirows IntfrruptfdExdfption {
        long bbsf = Systfm.durrfntTimfMillis();
        long now = 0;

        if (millis < 0) {
            tirow nfw IllfgblArgumfntExdfption("timfout vbluf is nfgbtivf");
        }

        if (millis == 0) {
            wiilf (isAlivf()) {
                wbit(0);
            }
        } flsf {
            wiilf (isAlivf()) {
                long dflby = millis - now;
                if (dflby <= 0) {
                    brfbk;
                }
                wbit(dflby);
                now = Systfm.durrfntTimfMillis() - bbsf;
            }
        }
    }

    /**
     * Wbits bt most {@dodf millis} millisfdonds plus
     * {@dodf nbnos} nbnosfdonds for tiis tirfbd to dif.
     *
     * <p> Tiis implfmfntbtion usfs b loop of {@dodf tiis.wbit} dblls
     * donditionfd on {@dodf tiis.isAlivf}. As b tirfbd tfrminbtfs tif
     * {@dodf tiis.notifyAll} mftiod is invokfd. It is rfdommfndfd tibt
     * bpplidbtions not usf {@dodf wbit}, {@dodf notify}, or
     * {@dodf notifyAll} on {@dodf Tirfbd} instbndfs.
     *
     * @pbrbm  millis
     *         tif timf to wbit in millisfdonds
     *
     * @pbrbm  nbnos
     *         {@dodf 0-999999} bdditionbl nbnosfdonds to wbit
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif vbluf of {@dodf millis} is nfgbtivf, or tif vbluf
     *          of {@dodf nbnos} is not in tif rbngf {@dodf 0-999999}
     *
     * @tirows  IntfrruptfdExdfption
     *          if bny tirfbd ibs intfrruptfd tif durrfnt tirfbd. Tif
     *          <i>intfrruptfd stbtus</i> of tif durrfnt tirfbd is
     *          dlfbrfd wifn tiis fxdfption is tirown.
     */
    publid finbl syndironizfd void join(long millis, int nbnos)
    tirows IntfrruptfdExdfption {

        if (millis < 0) {
            tirow nfw IllfgblArgumfntExdfption("timfout vbluf is nfgbtivf");
        }

        if (nbnos < 0 || nbnos > 999999) {
            tirow nfw IllfgblArgumfntExdfption(
                                "nbnosfdond timfout vbluf out of rbngf");
        }

        if (nbnos >= 500000 || (nbnos != 0 && millis == 0)) {
            millis++;
        }

        join(millis);
    }

    /**
     * Wbits for tiis tirfbd to dif.
     *
     * <p> An invodbtion of tiis mftiod bfibvfs in fxbdtly tif sbmf
     * wby bs tif invodbtion
     *
     * <blodkquotf>
     * {@linkplbin #join(long) join}{@dodf (0)}
     * </blodkquotf>
     *
     * @tirows  IntfrruptfdExdfption
     *          if bny tirfbd ibs intfrruptfd tif durrfnt tirfbd. Tif
     *          <i>intfrruptfd stbtus</i> of tif durrfnt tirfbd is
     *          dlfbrfd wifn tiis fxdfption is tirown.
     */
    publid finbl void join() tirows IntfrruptfdExdfption {
        join(0);
    }

    /**
     * Prints b stbdk trbdf of tif durrfnt tirfbd to tif stbndbrd frror strfbm.
     * Tiis mftiod is usfd only for dfbugging.
     *
     * @sff     Tirowbblf#printStbdkTrbdf()
     */
    publid stbtid void dumpStbdk() {
        nfw Exdfption("Stbdk trbdf").printStbdkTrbdf();
    }

    /**
     * Mbrks tiis tirfbd bs fitifr b {@linkplbin #isDbfmon dbfmon} tirfbd
     * or b usfr tirfbd. Tif Jbvb Virtubl Mbdiinf fxits wifn tif only
     * tirfbds running brf bll dbfmon tirfbds.
     *
     * <p> Tiis mftiod must bf invokfd bfforf tif tirfbd is stbrtfd.
     *
     * @pbrbm  on
     *         if {@dodf truf}, mbrks tiis tirfbd bs b dbfmon tirfbd
     *
     * @tirows  IllfgblTirfbdStbtfExdfption
     *          if tiis tirfbd is {@linkplbin #isAlivf blivf}
     *
     * @tirows  SfdurityExdfption
     *          if {@link #difdkAddfss} dftfrminfs tibt tif durrfnt
     *          tirfbd dbnnot modify tiis tirfbd
     */
    publid finbl void sftDbfmon(boolfbn on) {
        difdkAddfss();
        if (isAlivf()) {
            tirow nfw IllfgblTirfbdStbtfExdfption();
        }
        dbfmon = on;
    }

    /**
     * Tfsts if tiis tirfbd is b dbfmon tirfbd.
     *
     * @rfturn  <dodf>truf</dodf> if tiis tirfbd is b dbfmon tirfbd;
     *          <dodf>fblsf</dodf> otifrwisf.
     * @sff     #sftDbfmon(boolfbn)
     */
    publid finbl boolfbn isDbfmon() {
        rfturn dbfmon;
    }

    /**
     * Dftfrminfs if tif durrfntly running tirfbd ibs pfrmission to
     * modify tiis tirfbd.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkAddfss</dodf> mftiod
     * is dbllfd witi tiis tirfbd bs its brgumfnt. Tiis mby rfsult in
     * tirowing b <dodf>SfdurityExdfption</dodf>.
     *
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd is not bllowfd to
     *               bddfss tiis tirfbd.
     * @sff        SfdurityMbnbgfr#difdkAddfss(Tirfbd)
     */
    publid finbl void difdkAddfss() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkAddfss(tiis);
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis tirfbd, indluding tif
     * tirfbd's nbmf, priority, bnd tirfbd group.
     *
     * @rfturn  b string rfprfsfntbtion of tiis tirfbd.
     */
    publid String toString() {
        TirfbdGroup group = gftTirfbdGroup();
        if (group != null) {
            rfturn "Tirfbd[" + gftNbmf() + "," + gftPriority() + "," +
                           group.gftNbmf() + "]";
        } flsf {
            rfturn "Tirfbd[" + gftNbmf() + "," + gftPriority() + "," +
                            "" + "]";
        }
    }

    /**
     * Rfturns tif dontfxt ClbssLobdfr for tiis Tirfbd. Tif dontfxt
     * ClbssLobdfr is providfd by tif drfbtor of tif tirfbd for usf
     * by dodf running in tiis tirfbd wifn lobding dlbssfs bnd rfsourdfs.
     * If not {@linkplbin #sftContfxtClbssLobdfr sft}, tif dffbult is tif
     * ClbssLobdfr dontfxt of tif pbrfnt Tirfbd. Tif dontfxt ClbssLobdfr of tif
     * primordibl tirfbd is typidblly sft to tif dlbss lobdfr usfd to lobd tif
     * bpplidbtion.
     *
     * <p>If b sfdurity mbnbgfr is prfsfnt, bnd tif invokfr's dlbss lobdfr is not
     * {@dodf null} bnd is not tif sbmf bs or bn bndfstor of tif dontfxt dlbss
     * lobdfr, tifn tiis mftiod invokfs tif sfdurity mbnbgfr's {@link
     * SfdurityMbnbgfr#difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission}
     * mftiod witi b {@link RuntimfPfrmission RuntimfPfrmission}{@dodf
     * ("gftClbssLobdfr")} pfrmission to vfrify tibt rftrifvbl of tif dontfxt
     * dlbss lobdfr is pfrmittfd.
     *
     * @rfturn  tif dontfxt ClbssLobdfr for tiis Tirfbd, or {@dodf null}
     *          indidbting tif systfm dlbss lobdfr (or, fbiling tibt, tif
     *          bootstrbp dlbss lobdfr)
     *
     * @tirows  SfdurityExdfption
     *          if tif durrfnt tirfbd dbnnot gft tif dontfxt ClbssLobdfr
     *
     * @sindf 1.2
     */
    @CbllfrSfnsitivf
    publid ClbssLobdfr gftContfxtClbssLobdfr() {
        if (dontfxtClbssLobdfr == null)
            rfturn null;
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            ClbssLobdfr.difdkClbssLobdfrPfrmission(dontfxtClbssLobdfr,
                                                   Rfflfdtion.gftCbllfrClbss());
        }
        rfturn dontfxtClbssLobdfr;
    }

    /**
     * Sfts tif dontfxt ClbssLobdfr for tiis Tirfbd. Tif dontfxt
     * ClbssLobdfr dbn bf sft wifn b tirfbd is drfbtfd, bnd bllows
     * tif drfbtor of tif tirfbd to providf tif bppropribtf dlbss lobdfr,
     * tirougi {@dodf gftContfxtClbssLobdfr}, to dodf running in tif tirfbd
     * wifn lobding dlbssfs bnd rfsourdfs.
     *
     * <p>If b sfdurity mbnbgfr is prfsfnt, its {@link
     * SfdurityMbnbgfr#difdkPfrmission(jbvb.sfdurity.Pfrmission) difdkPfrmission}
     * mftiod is invokfd witi b {@link RuntimfPfrmission RuntimfPfrmission}{@dodf
     * ("sftContfxtClbssLobdfr")} pfrmission to sff if sftting tif dontfxt
     * ClbssLobdfr is pfrmittfd.
     *
     * @pbrbm  dl
     *         tif dontfxt ClbssLobdfr for tiis Tirfbd, or null  indidbting tif
     *         systfm dlbss lobdfr (or, fbiling tibt, tif bootstrbp dlbss lobdfr)
     *
     * @tirows  SfdurityExdfption
     *          if tif durrfnt tirfbd dbnnot sft tif dontfxt ClbssLobdfr
     *
     * @sindf 1.2
     */
    publid void sftContfxtClbssLobdfr(ClbssLobdfr dl) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw RuntimfPfrmission("sftContfxtClbssLobdfr"));
        }
        dontfxtClbssLobdfr = dl;
    }

    /**
     * Rfturns <tt>truf</tt> if bnd only if tif durrfnt tirfbd iolds tif
     * monitor lodk on tif spfdififd objfdt.
     *
     * <p>Tiis mftiod is dfsignfd to bllow b progrbm to bssfrt tibt
     * tif durrfnt tirfbd blrfbdy iolds b spfdififd lodk:
     * <prf>
     *     bssfrt Tirfbd.ioldsLodk(obj);
     * </prf>
     *
     * @pbrbm  obj tif objfdt on wiidi to tfst lodk ownfrsiip
     * @tirows NullPointfrExdfption if obj is <tt>null</tt>
     * @rfturn <tt>truf</tt> if tif durrfnt tirfbd iolds tif monitor lodk on
     *         tif spfdififd objfdt.
     * @sindf 1.4
     */
    publid stbtid nbtivf boolfbn ioldsLodk(Objfdt obj);

    privbtf stbtid finbl StbdkTrbdfElfmfnt[] EMPTY_STACK_TRACE
        = nfw StbdkTrbdfElfmfnt[0];

    /**
     * Rfturns bn brrby of stbdk trbdf flfmfnts rfprfsfnting tif stbdk dump
     * of tiis tirfbd.  Tiis mftiod will rfturn b zfro-lfngti brrby if
     * tiis tirfbd ibs not stbrtfd, ibs stbrtfd but ibs not yft bffn
     * sdifdulfd to run by tif systfm, or ibs tfrminbtfd.
     * If tif rfturnfd brrby is of non-zfro lfngti tifn tif first flfmfnt of
     * tif brrby rfprfsfnts tif top of tif stbdk, wiidi is tif most rfdfnt
     * mftiod invodbtion in tif sfqufndf.  Tif lbst flfmfnt of tif brrby
     * rfprfsfnts tif bottom of tif stbdk, wiidi is tif lfbst rfdfnt mftiod
     * invodbtion in tif sfqufndf.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, bnd tiis tirfbd is not
     * tif durrfnt tirfbd, tifn tif sfdurity mbnbgfr's
     * <tt>difdkPfrmission</tt> mftiod is dbllfd witi b
     * <tt>RuntimfPfrmission("gftStbdkTrbdf")</tt> pfrmission
     * to sff if it's ok to gft tif stbdk trbdf.
     *
     * <p>Somf virtubl mbdiinfs mby, undfr somf dirdumstbndfs, omit onf
     * or morf stbdk frbmfs from tif stbdk trbdf.  In tif fxtrfmf dbsf,
     * b virtubl mbdiinf tibt ibs no stbdk trbdf informbtion dondfrning
     * tiis tirfbd is pfrmittfd to rfturn b zfro-lfngti brrby from tiis
     * mftiod.
     *
     * @rfturn bn brrby of <tt>StbdkTrbdfElfmfnt</tt>,
     * fbdi rfprfsfnts onf stbdk frbmf.
     *
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        <tt>difdkPfrmission</tt> mftiod dofsn't bllow
     *        gftting tif stbdk trbdf of tirfbd.
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff RuntimfPfrmission
     * @sff Tirowbblf#gftStbdkTrbdf
     *
     * @sindf 1.5
     */
    publid StbdkTrbdfElfmfnt[] gftStbdkTrbdf() {
        if (tiis != Tirfbd.durrfntTirfbd()) {
            // difdk for gftStbdkTrbdf pfrmission
            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            if (sfdurity != null) {
                sfdurity.difdkPfrmission(
                    SfdurityConstbnts.GET_STACK_TRACE_PERMISSION);
            }
            // optimizbtion so wf do not dbll into tif vm for tirfbds tibt
            // ibvf not yft stbrtfd or ibvf tfrminbtfd
            if (!isAlivf()) {
                rfturn EMPTY_STACK_TRACE;
            }
            StbdkTrbdfElfmfnt[][] stbdkTrbdfArrby = dumpTirfbds(nfw Tirfbd[] {tiis});
            StbdkTrbdfElfmfnt[] stbdkTrbdf = stbdkTrbdfArrby[0];
            // b tirfbd tibt wbs blivf during tif prfvious isAlivf dbll mby ibvf
            // sindf tfrminbtfd, tifrfforf not ibving b stbdktrbdf.
            if (stbdkTrbdf == null) {
                stbdkTrbdf = EMPTY_STACK_TRACE;
            }
            rfturn stbdkTrbdf;
        } flsf {
            // Don't nffd JVM iflp for durrfnt tirfbd
            rfturn (nfw Exdfption()).gftStbdkTrbdf();
        }
    }

    /**
     * Rfturns b mbp of stbdk trbdfs for bll livf tirfbds.
     * Tif mbp kfys brf tirfbds bnd fbdi mbp vbluf is bn brrby of
     * <tt>StbdkTrbdfElfmfnt</tt> tibt rfprfsfnts tif stbdk dump
     * of tif dorrfsponding <tt>Tirfbd</tt>.
     * Tif rfturnfd stbdk trbdfs brf in tif formbt spfdififd for
     * tif {@link #gftStbdkTrbdf gftStbdkTrbdf} mftiod.
     *
     * <p>Tif tirfbds mby bf fxfduting wiilf tiis mftiod is dbllfd.
     * Tif stbdk trbdf of fbdi tirfbd only rfprfsfnts b snbpsiot bnd
     * fbdi stbdk trbdf mby bf obtbinfd bt difffrfnt timf.  A zfro-lfngti
     * brrby will bf rfturnfd in tif mbp vbluf if tif virtubl mbdiinf ibs
     * no stbdk trbdf informbtion bbout b tirfbd.
     *
     * <p>If tifrf is b sfdurity mbnbgfr, tifn tif sfdurity mbnbgfr's
     * <tt>difdkPfrmission</tt> mftiod is dbllfd witi b
     * <tt>RuntimfPfrmission("gftStbdkTrbdf")</tt> pfrmission bs wfll bs
     * <tt>RuntimfPfrmission("modifyTirfbdGroup")</tt> pfrmission
     * to sff if it is ok to gft tif stbdk trbdf of bll tirfbds.
     *
     * @rfturn b <tt>Mbp</tt> from <tt>Tirfbd</tt> to bn brrby of
     * <tt>StbdkTrbdfElfmfnt</tt> tibt rfprfsfnts tif stbdk trbdf of
     * tif dorrfsponding tirfbd.
     *
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        <tt>difdkPfrmission</tt> mftiod dofsn't bllow
     *        gftting tif stbdk trbdf of tirfbd.
     * @sff #gftStbdkTrbdf
     * @sff SfdurityMbnbgfr#difdkPfrmission
     * @sff RuntimfPfrmission
     * @sff Tirowbblf#gftStbdkTrbdf
     *
     * @sindf 1.5
     */
    publid stbtid Mbp<Tirfbd, StbdkTrbdfElfmfnt[]> gftAllStbdkTrbdfs() {
        // difdk for gftStbdkTrbdf pfrmission
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(
                SfdurityConstbnts.GET_STACK_TRACE_PERMISSION);
            sfdurity.difdkPfrmission(
                SfdurityConstbnts.MODIFY_THREADGROUP_PERMISSION);
        }

        // Gft b snbpsiot of tif list of bll tirfbds
        Tirfbd[] tirfbds = gftTirfbds();
        StbdkTrbdfElfmfnt[][] trbdfs = dumpTirfbds(tirfbds);
        Mbp<Tirfbd, StbdkTrbdfElfmfnt[]> m = nfw HbsiMbp<>(tirfbds.lfngti);
        for (int i = 0; i < tirfbds.lfngti; i++) {
            StbdkTrbdfElfmfnt[] stbdkTrbdf = trbdfs[i];
            if (stbdkTrbdf != null) {
                m.put(tirfbds[i], stbdkTrbdf);
            }
            // flsf tfrminbtfd so wf don't put it in tif mbp
        }
        rfturn m;
    }


    privbtf stbtid finbl RuntimfPfrmission SUBCLASS_IMPLEMENTATION_PERMISSION =
                    nfw RuntimfPfrmission("fnbblfContfxtClbssLobdfrOvfrridf");

    /** dbdif of subdlbss sfdurity budit rfsults */
    /* Rfplbdf witi CondurrfntRfffrfndfHbsiMbp wifn/if it bppfbrs in b futurf
     * rflfbsf */
    privbtf stbtid dlbss Cbdifs {
        /** dbdif of subdlbss sfdurity budit rfsults */
        stbtid finbl CondurrfntMbp<WfbkClbssKfy,Boolfbn> subdlbssAudits =
            nfw CondurrfntHbsiMbp<>();

        /** qufuf for WfbkRfffrfndfs to buditfd subdlbssfs */
        stbtid finbl RfffrfndfQufuf<Clbss<?>> subdlbssAuditsQufuf =
            nfw RfffrfndfQufuf<>();
    }

    /**
     * Vfrififs tibt tiis (possibly subdlbss) instbndf dbn bf donstrudtfd
     * witiout violbting sfdurity donstrbints: tif subdlbss must not ovfrridf
     * sfdurity-sfnsitivf non-finbl mftiods, or flsf tif
     * "fnbblfContfxtClbssLobdfrOvfrridf" RuntimfPfrmission is difdkfd.
     */
    privbtf stbtid boolfbn isCCLOvfrriddfn(Clbss<?> dl) {
        if (dl == Tirfbd.dlbss)
            rfturn fblsf;

        prodfssQufuf(Cbdifs.subdlbssAuditsQufuf, Cbdifs.subdlbssAudits);
        WfbkClbssKfy kfy = nfw WfbkClbssKfy(dl, Cbdifs.subdlbssAuditsQufuf);
        Boolfbn rfsult = Cbdifs.subdlbssAudits.gft(kfy);
        if (rfsult == null) {
            rfsult = Boolfbn.vblufOf(buditSubdlbss(dl));
            Cbdifs.subdlbssAudits.putIfAbsfnt(kfy, rfsult);
        }

        rfturn rfsult.boolfbnVbluf();
    }

    /**
     * Pfrforms rfflfdtivf difdks on givfn subdlbss to vfrify tibt it dofsn't
     * ovfrridf sfdurity-sfnsitivf non-finbl mftiods.  Rfturns truf if tif
     * subdlbss ovfrridfs bny of tif mftiods, fblsf otifrwisf.
     */
    privbtf stbtid boolfbn buditSubdlbss(finbl Clbss<?> subdl) {
        Boolfbn rfsult = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Boolfbn>() {
                publid Boolfbn run() {
                    for (Clbss<?> dl = subdl;
                         dl != Tirfbd.dlbss;
                         dl = dl.gftSupfrdlbss())
                    {
                        try {
                            dl.gftDfdlbrfdMftiod("gftContfxtClbssLobdfr", nfw Clbss<?>[0]);
                            rfturn Boolfbn.TRUE;
                        } dbtdi (NoSudiMftiodExdfption fx) {
                        }
                        try {
                            Clbss<?>[] pbrbms = {ClbssLobdfr.dlbss};
                            dl.gftDfdlbrfdMftiod("sftContfxtClbssLobdfr", pbrbms);
                            rfturn Boolfbn.TRUE;
                        } dbtdi (NoSudiMftiodExdfption fx) {
                        }
                    }
                    rfturn Boolfbn.FALSE;
                }
            }
        );
        rfturn rfsult.boolfbnVbluf();
    }

    privbtf nbtivf stbtid StbdkTrbdfElfmfnt[][] dumpTirfbds(Tirfbd[] tirfbds);
    privbtf nbtivf stbtid Tirfbd[] gftTirfbds();

    /**
     * Rfturns tif idfntififr of tiis Tirfbd.  Tif tirfbd ID is b positivf
     * <tt>long</tt> numbfr gfnfrbtfd wifn tiis tirfbd wbs drfbtfd.
     * Tif tirfbd ID is uniquf bnd rfmbins undibngfd during its lifftimf.
     * Wifn b tirfbd is tfrminbtfd, tiis tirfbd ID mby bf rfusfd.
     *
     * @rfturn tiis tirfbd's ID.
     * @sindf 1.5
     */
    publid long gftId() {
        rfturn tid;
    }

    /**
     * A tirfbd stbtf.  A tirfbd dbn bf in onf of tif following stbtfs:
     * <ul>
     * <li>{@link #NEW}<br>
     *     A tirfbd tibt ibs not yft stbrtfd is in tiis stbtf.
     *     </li>
     * <li>{@link #RUNNABLE}<br>
     *     A tirfbd fxfduting in tif Jbvb virtubl mbdiinf is in tiis stbtf.
     *     </li>
     * <li>{@link #BLOCKED}<br>
     *     A tirfbd tibt is blodkfd wbiting for b monitor lodk
     *     is in tiis stbtf.
     *     </li>
     * <li>{@link #WAITING}<br>
     *     A tirfbd tibt is wbiting indffinitfly for bnotifr tirfbd to
     *     pfrform b pbrtidulbr bdtion is in tiis stbtf.
     *     </li>
     * <li>{@link #TIMED_WAITING}<br>
     *     A tirfbd tibt is wbiting for bnotifr tirfbd to pfrform bn bdtion
     *     for up to b spfdififd wbiting timf is in tiis stbtf.
     *     </li>
     * <li>{@link #TERMINATED}<br>
     *     A tirfbd tibt ibs fxitfd is in tiis stbtf.
     *     </li>
     * </ul>
     *
     * <p>
     * A tirfbd dbn bf in only onf stbtf bt b givfn point in timf.
     * Tifsf stbtfs brf virtubl mbdiinf stbtfs wiidi do not rfflfdt
     * bny opfrbting systfm tirfbd stbtfs.
     *
     * @sindf   1.5
     * @sff #gftStbtf
     */
    publid fnum Stbtf {
        /**
         * Tirfbd stbtf for b tirfbd wiidi ibs not yft stbrtfd.
         */
        NEW,

        /**
         * Tirfbd stbtf for b runnbblf tirfbd.  A tirfbd in tif runnbblf
         * stbtf is fxfduting in tif Jbvb virtubl mbdiinf but it mby
         * bf wbiting for otifr rfsourdfs from tif opfrbting systfm
         * sudi bs prodfssor.
         */
        RUNNABLE,

        /**
         * Tirfbd stbtf for b tirfbd blodkfd wbiting for b monitor lodk.
         * A tirfbd in tif blodkfd stbtf is wbiting for b monitor lodk
         * to fntfr b syndironizfd blodk/mftiod or
         * rffntfr b syndironizfd blodk/mftiod bftfr dblling
         * {@link Objfdt#wbit() Objfdt.wbit}.
         */
        BLOCKED,

        /**
         * Tirfbd stbtf for b wbiting tirfbd.
         * A tirfbd is in tif wbiting stbtf duf to dblling onf of tif
         * following mftiods:
         * <ul>
         *   <li>{@link Objfdt#wbit() Objfdt.wbit} witi no timfout</li>
         *   <li>{@link #join() Tirfbd.join} witi no timfout</li>
         *   <li>{@link LodkSupport#pbrk() LodkSupport.pbrk}</li>
         * </ul>
         *
         * <p>A tirfbd in tif wbiting stbtf is wbiting for bnotifr tirfbd to
         * pfrform b pbrtidulbr bdtion.
         *
         * For fxbmplf, b tirfbd tibt ibs dbllfd <tt>Objfdt.wbit()</tt>
         * on bn objfdt is wbiting for bnotifr tirfbd to dbll
         * <tt>Objfdt.notify()</tt> or <tt>Objfdt.notifyAll()</tt> on
         * tibt objfdt. A tirfbd tibt ibs dbllfd <tt>Tirfbd.join()</tt>
         * is wbiting for b spfdififd tirfbd to tfrminbtf.
         */
        WAITING,

        /**
         * Tirfbd stbtf for b wbiting tirfbd witi b spfdififd wbiting timf.
         * A tirfbd is in tif timfd wbiting stbtf duf to dblling onf of
         * tif following mftiods witi b spfdififd positivf wbiting timf:
         * <ul>
         *   <li>{@link #slffp Tirfbd.slffp}</li>
         *   <li>{@link Objfdt#wbit(long) Objfdt.wbit} witi timfout</li>
         *   <li>{@link #join(long) Tirfbd.join} witi timfout</li>
         *   <li>{@link LodkSupport#pbrkNbnos LodkSupport.pbrkNbnos}</li>
         *   <li>{@link LodkSupport#pbrkUntil LodkSupport.pbrkUntil}</li>
         * </ul>
         */
        TIMED_WAITING,

        /**
         * Tirfbd stbtf for b tfrminbtfd tirfbd.
         * Tif tirfbd ibs domplftfd fxfdution.
         */
        TERMINATED;
    }

    /**
     * Rfturns tif stbtf of tiis tirfbd.
     * Tiis mftiod is dfsignfd for usf in monitoring of tif systfm stbtf,
     * not for syndironizbtion dontrol.
     *
     * @rfturn tiis tirfbd's stbtf.
     * @sindf 1.5
     */
    publid Stbtf gftStbtf() {
        // gft durrfnt tirfbd stbtf
        rfturn sun.misd.VM.toTirfbdStbtf(tirfbdStbtus);
    }

    // Addfd in JSR-166

    /**
     * Intfrfbdf for ibndlfrs invokfd wifn b <tt>Tirfbd</tt> bbruptly
     * tfrminbtfs duf to bn undbugit fxdfption.
     * <p>Wifn b tirfbd is bbout to tfrminbtf duf to bn undbugit fxdfption
     * tif Jbvb Virtubl Mbdiinf will qufry tif tirfbd for its
     * <tt>UndbugitExdfptionHbndlfr</tt> using
     * {@link #gftUndbugitExdfptionHbndlfr} bnd will invokf tif ibndlfr's
     * <tt>undbugitExdfption</tt> mftiod, pbssing tif tirfbd bnd tif
     * fxdfption bs brgumfnts.
     * If b tirfbd ibs not ibd its <tt>UndbugitExdfptionHbndlfr</tt>
     * fxpliditly sft, tifn its <tt>TirfbdGroup</tt> objfdt bdts bs its
     * <tt>UndbugitExdfptionHbndlfr</tt>. If tif <tt>TirfbdGroup</tt> objfdt
     * ibs no
     * spfdibl rfquirfmfnts for dfbling witi tif fxdfption, it dbn forwbrd
     * tif invodbtion to tif {@linkplbin #gftDffbultUndbugitExdfptionHbndlfr
     * dffbult undbugit fxdfption ibndlfr}.
     *
     * @sff #sftDffbultUndbugitExdfptionHbndlfr
     * @sff #sftUndbugitExdfptionHbndlfr
     * @sff TirfbdGroup#undbugitExdfption
     * @sindf 1.5
     */
    @FundtionblIntfrfbdf
    publid intfrfbdf UndbugitExdfptionHbndlfr {
        /**
         * Mftiod invokfd wifn tif givfn tirfbd tfrminbtfs duf to tif
         * givfn undbugit fxdfption.
         * <p>Any fxdfption tirown by tiis mftiod will bf ignorfd by tif
         * Jbvb Virtubl Mbdiinf.
         * @pbrbm t tif tirfbd
         * @pbrbm f tif fxdfption
         */
        void undbugitExdfption(Tirfbd t, Tirowbblf f);
    }

    // null unlfss fxpliditly sft
    privbtf volbtilf UndbugitExdfptionHbndlfr undbugitExdfptionHbndlfr;

    // null unlfss fxpliditly sft
    privbtf stbtid volbtilf UndbugitExdfptionHbndlfr dffbultUndbugitExdfptionHbndlfr;

    /**
     * Sft tif dffbult ibndlfr invokfd wifn b tirfbd bbruptly tfrminbtfs
     * duf to bn undbugit fxdfption, bnd no otifr ibndlfr ibs bffn dffinfd
     * for tibt tirfbd.
     *
     * <p>Undbugit fxdfption ibndling is dontrollfd first by tif tirfbd, tifn
     * by tif tirfbd's {@link TirfbdGroup} objfdt bnd finblly by tif dffbult
     * undbugit fxdfption ibndlfr. If tif tirfbd dofs not ibvf bn fxplidit
     * undbugit fxdfption ibndlfr sft, bnd tif tirfbd's tirfbd group
     * (indluding pbrfnt tirfbd groups)  dofs not spfdiblizf its
     * <tt>undbugitExdfption</tt> mftiod, tifn tif dffbult ibndlfr's
     * <tt>undbugitExdfption</tt> mftiod will bf invokfd.
     * <p>By sftting tif dffbult undbugit fxdfption ibndlfr, bn bpplidbtion
     * dbn dibngf tif wby in wiidi undbugit fxdfptions brf ibndlfd (sudi bs
     * logging to b spfdifid dfvidf, or filf) for tiosf tirfbds tibt would
     * blrfbdy bddfpt wibtfvfr &quot;dffbult&quot; bfibvior tif systfm
     * providfd.
     *
     * <p>Notf tibt tif dffbult undbugit fxdfption ibndlfr siould not usublly
     * dfffr to tif tirfbd's <tt>TirfbdGroup</tt> objfdt, bs tibt dould dbusf
     * infinitf rfdursion.
     *
     * @pbrbm fi tif objfdt to usf bs tif dffbult undbugit fxdfption ibndlfr.
     * If <tt>null</tt> tifn tifrf is no dffbult ibndlfr.
     *
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr is prfsfnt bnd it
     *         dfnifs <tt>{@link RuntimfPfrmission}
     *         (&quot;sftDffbultUndbugitExdfptionHbndlfr&quot;)</tt>
     *
     * @sff #sftUndbugitExdfptionHbndlfr
     * @sff #gftUndbugitExdfptionHbndlfr
     * @sff TirfbdGroup#undbugitExdfption
     * @sindf 1.5
     */
    publid stbtid void sftDffbultUndbugitExdfptionHbndlfr(UndbugitExdfptionHbndlfr fi) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(
                nfw RuntimfPfrmission("sftDffbultUndbugitExdfptionHbndlfr")
                    );
        }

         dffbultUndbugitExdfptionHbndlfr = fi;
     }

    /**
     * Rfturns tif dffbult ibndlfr invokfd wifn b tirfbd bbruptly tfrminbtfs
     * duf to bn undbugit fxdfption. If tif rfturnfd vbluf is <tt>null</tt>,
     * tifrf is no dffbult.
     * @sindf 1.5
     * @sff #sftDffbultUndbugitExdfptionHbndlfr
     * @rfturn tif dffbult undbugit fxdfption ibndlfr for bll tirfbds
     */
    publid stbtid UndbugitExdfptionHbndlfr gftDffbultUndbugitExdfptionHbndlfr(){
        rfturn dffbultUndbugitExdfptionHbndlfr;
    }

    /**
     * Rfturns tif ibndlfr invokfd wifn tiis tirfbd bbruptly tfrminbtfs
     * duf to bn undbugit fxdfption. If tiis tirfbd ibs not ibd bn
     * undbugit fxdfption ibndlfr fxpliditly sft tifn tiis tirfbd's
     * <tt>TirfbdGroup</tt> objfdt is rfturnfd, unlfss tiis tirfbd
     * ibs tfrminbtfd, in wiidi dbsf <tt>null</tt> is rfturnfd.
     * @sindf 1.5
     * @rfturn tif undbugit fxdfption ibndlfr for tiis tirfbd
     */
    publid UndbugitExdfptionHbndlfr gftUndbugitExdfptionHbndlfr() {
        rfturn undbugitExdfptionHbndlfr != null ?
            undbugitExdfptionHbndlfr : group;
    }

    /**
     * Sft tif ibndlfr invokfd wifn tiis tirfbd bbruptly tfrminbtfs
     * duf to bn undbugit fxdfption.
     * <p>A tirfbd dbn tbkf full dontrol of iow it rfsponds to undbugit
     * fxdfptions by ibving its undbugit fxdfption ibndlfr fxpliditly sft.
     * If no sudi ibndlfr is sft tifn tif tirfbd's <tt>TirfbdGroup</tt>
     * objfdt bdts bs its ibndlfr.
     * @pbrbm fi tif objfdt to usf bs tiis tirfbd's undbugit fxdfption
     * ibndlfr. If <tt>null</tt> tifn tiis tirfbd ibs no fxplidit ibndlfr.
     * @tirows  SfdurityExdfption  if tif durrfnt tirfbd is not bllowfd to
     *          modify tiis tirfbd.
     * @sff #sftDffbultUndbugitExdfptionHbndlfr
     * @sff TirfbdGroup#undbugitExdfption
     * @sindf 1.5
     */
    publid void sftUndbugitExdfptionHbndlfr(UndbugitExdfptionHbndlfr fi) {
        difdkAddfss();
        undbugitExdfptionHbndlfr = fi;
    }

    /**
     * Dispbtdi bn undbugit fxdfption to tif ibndlfr. Tiis mftiod is
     * intfndfd to bf dbllfd only by tif JVM.
     */
    privbtf void dispbtdiUndbugitExdfption(Tirowbblf f) {
        gftUndbugitExdfptionHbndlfr().undbugitExdfption(tiis, f);
    }

    /**
     * Rfmovfs from tif spfdififd mbp bny kfys tibt ibvf bffn fnqufufd
     * on tif spfdififd rfffrfndf qufuf.
     */
    stbtid void prodfssQufuf(RfffrfndfQufuf<Clbss<?>> qufuf,
                             CondurrfntMbp<? fxtfnds
                             WfbkRfffrfndf<Clbss<?>>, ?> mbp)
    {
        Rfffrfndf<? fxtfnds Clbss<?>> rff;
        wiilf((rff = qufuf.poll()) != null) {
            mbp.rfmovf(rff);
        }
    }

    /**
     *  Wfbk kfy for Clbss objfdts.
     **/
    stbtid dlbss WfbkClbssKfy fxtfnds WfbkRfffrfndf<Clbss<?>> {
        /**
         * sbvfd vbluf of tif rfffrfnt's idfntity ibsi dodf, to mbintbin
         * b donsistfnt ibsi dodf bftfr tif rfffrfnt ibs bffn dlfbrfd
         */
        privbtf finbl int ibsi;

        /**
         * Crfbtf b nfw WfbkClbssKfy to tif givfn objfdt, rfgistfrfd
         * witi b qufuf.
         */
        WfbkClbssKfy(Clbss<?> dl, RfffrfndfQufuf<Clbss<?>> rffQufuf) {
            supfr(dl, rffQufuf);
            ibsi = Systfm.idfntityHbsiCodf(dl);
        }

        /**
         * Rfturns tif idfntity ibsi dodf of tif originbl rfffrfnt.
         */
        @Ovfrridf
        publid int ibsiCodf() {
            rfturn ibsi;
        }

        /**
         * Rfturns truf if tif givfn objfdt is tiis idfntidbl
         * WfbkClbssKfy instbndf, or, if tiis objfdt's rfffrfnt ibs not
         * bffn dlfbrfd, if tif givfn objfdt is bnotifr WfbkClbssKfy
         * instbndf witi tif idfntidbl non-null rfffrfnt bs tiis onf.
         */
        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (obj == tiis)
                rfturn truf;

            if (obj instbndfof WfbkClbssKfy) {
                Objfdt rfffrfnt = gft();
                rfturn (rfffrfnt != null) &&
                       (rfffrfnt == ((WfbkClbssKfy) obj).gft());
            } flsf {
                rfturn fblsf;
            }
        }
    }


    // Tif following tirff initiblly uninitiblizfd fiflds brf fxdlusivfly
    // mbnbgfd by dlbss jbvb.util.dondurrfnt.TirfbdLodblRbndom. Tifsf
    // fiflds brf usfd to build tif iigi-pfrformbndf PRNGs in tif
    // dondurrfnt dodf, bnd wf dbn not risk bddidfntbl fblsf sibring.
    // Hfndf, tif fiflds brf isolbtfd witi @Contfndfd.

    /** Tif durrfnt sffd for b TirfbdLodblRbndom */
    @sun.misd.Contfndfd("tlr")
    long tirfbdLodblRbndomSffd;

    /** Probf ibsi vbluf; nonzfro if tirfbdLodblRbndomSffd initiblizfd */
    @sun.misd.Contfndfd("tlr")
    int tirfbdLodblRbndomProbf;

    /** Sfdondbry sffd isolbtfd from publid TirfbdLodblRbndom sfqufndf */
    @sun.misd.Contfndfd("tlr")
    int tirfbdLodblRbndomSfdondbrySffd;

    /* Somf privbtf iflpfr mftiods */
    privbtf nbtivf void sftPriority0(int nfwPriority);
    privbtf nbtivf void stop0(Objfdt o);
    privbtf nbtivf void suspfnd0();
    privbtf nbtivf void rfsumf0();
    privbtf nbtivf void intfrrupt0();
    privbtf nbtivf void sftNbtivfNbmf(String nbmf);
}
