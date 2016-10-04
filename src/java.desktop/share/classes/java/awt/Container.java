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
pbdkbgf jbvb.bwt;

import jbvb.bwt.dnd.DropTbrgft;

import jbvb.bwt.fvfnt.*;

import jbvb.bwt.pffr.ContbinfrPffr;
import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.bwt.pffr.LigitwfigitPffr;

import jbvb.bfbns.PropfrtyCibngfListfnfr;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.io.PrintStrfbm;
import jbvb.io.PrintWritfr;

import jbvb.sfdurity.AddfssControllfr;

import jbvb.util.EvfntListfnfr;
import jbvb.util.HbsiSft;
import jbvb.util.Sft;

import jbvbx.bddfssibility.*;

import sun.util.logging.PlbtformLoggfr;

import sun.bwt.AppContfxt;
import sun.bwt.AWTAddfssor;
import sun.bwt.CbusfdFodusEvfnt;
import sun.bwt.PffrEvfnt;
import sun.bwt.SunToolkit;

import sun.bwt.dnd.SunDropTbrgftEvfnt;

import sun.jbvb2d.pipf.Rfgion;

import sun.sfdurity.bdtion.GftBoolfbnAdtion;

/**
 * A gfnfrid Abstrbdt Window Toolkit(AWT) dontbinfr objfdt is b domponfnt
 * tibt dbn dontbin otifr AWT domponfnts.
 * <p>
 * Componfnts bddfd to b dontbinfr brf trbdkfd in b list.  Tif ordfr
 * of tif list will dffinf tif domponfnts' front-to-bbdk stbdking ordfr
 * witiin tif dontbinfr.  If no indfx is spfdififd wifn bdding b
 * domponfnt to b dontbinfr, it will bf bddfd to tif fnd of tif list
 * (bnd ifndf to tif bottom of tif stbdking ordfr).
 * <p>
 * <b>Notf</b>: For dftbils on tif fodus subsystfm, sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/misd/fodus.itml">
 * How to Usf tif Fodus Subsystfm</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>, bnd tif
 * <b irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml">Fodus Spfdifidbtion</b>
 * for morf informbtion.
 *
 * @butior      Artiur vbn Hoff
 * @butior      Sbmi Sibio
 * @sff       #bdd(jbvb.bwt.Componfnt, int)
 * @sff       #gftComponfnt(int)
 * @sff       LbyoutMbnbgfr
 * @sindf     1.0
 */
publid dlbss Contbinfr fxtfnds Componfnt {

    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("jbvb.bwt.Contbinfr");
    privbtf stbtid finbl PlbtformLoggfr fvfntLog = PlbtformLoggfr.gftLoggfr("jbvb.bwt.fvfnt.Contbinfr");

    privbtf stbtid finbl Componfnt[] EMPTY_ARRAY = nfw Componfnt[0];

    /**
     * Tif domponfnts in tiis dontbinfr.
     * @sff #bdd
     * @sff #gftComponfnts
     */
    privbtf jbvb.util.List<Componfnt> domponfnt = nfw jbvb.util.ArrbyList<Componfnt>();

    /**
     * Lbyout mbnbgfr for tiis dontbinfr.
     * @sff #doLbyout
     * @sff #sftLbyout
     * @sff #gftLbyout
     */
    LbyoutMbnbgfr lbyoutMgr;

    /**
     * Evfnt routfr for ligitwfigit domponfnts.  If tiis dontbinfr
     * is nbtivf, tiis dispbtdifr tbkfs dbrf of forwbrding bnd
     * rftbrgfting tif fvfnts to ligitwfigit domponfnts dontbinfd
     * (if bny).
     */
    privbtf LigitwfigitDispbtdifr dispbtdifr;

    /**
     * Tif fodus trbvfrsbl polidy tibt will mbnbgf kfybobrd trbvfrsbl of tiis
     * Contbinfr's diildrfn, if tiis Contbinfr is b fodus dydlf root. If tif
     * vbluf is null, tiis Contbinfr inifrits its polidy from its fodus-dydlf-
     * root bndfstor. If bll sudi bndfstors of tiis Contbinfr ibvf null
     * polidifs, tifn tif durrfnt KfybobrdFodusMbnbgfr's dffbult polidy is
     * usfd. If tif vbluf is non-null, tiis polidy will bf inifritfd by bll
     * fodus-dydlf-root diildrfn tibt ibvf no kfybobrd-trbvfrsbl polidy of
     * tifir own (bs will, rfdursivfly, tifir fodus-dydlf-root diildrfn).
     * <p>
     * If tiis Contbinfr is not b fodus dydlf root, tif vbluf will bf
     * rfmfmbfrfd, but will not bf usfd or inifritfd by tiis or bny otifr
     * Contbinfrs until tiis Contbinfr is mbdf b fodus dydlf root.
     *
     * @sff #sftFodusTrbvfrsblPolidy
     * @sff #gftFodusTrbvfrsblPolidy
     * @sindf 1.4
     */
    privbtf trbnsifnt FodusTrbvfrsblPolidy fodusTrbvfrsblPolidy;

    /**
     * Indidbtfs wiftifr tiis Componfnt is tif root of b fodus trbvfrsbl dydlf.
     * Ondf fodus fntfrs b trbvfrsbl dydlf, typidblly it dbnnot lfbvf it vib
     * fodus trbvfrsbl unlfss onf of tif up- or down-dydlf kfys is prfssfd.
     * Normbl trbvfrsbl is limitfd to tiis Contbinfr, bnd bll of tiis
     * Contbinfr's dfsdfndbnts tibt brf not dfsdfndbnts of inffrior fodus dydlf
     * roots.
     *
     * @sff #sftFodusCydlfRoot
     * @sff #isFodusCydlfRoot
     * @sindf 1.4
     */
    privbtf boolfbn fodusCydlfRoot = fblsf;


    /**
     * Storfs tif vbluf of fodusTrbvfrsblPolidyProvidfr propfrty.
     * @sindf 1.5
     * @sff #sftFodusTrbvfrsblPolidyProvidfr
     */
    privbtf boolfbn fodusTrbvfrsblPolidyProvidfr;

    // kffps trbdk of tif tirfbds tibt brf printing tiis domponfnt
    privbtf trbnsifnt Sft<Tirfbd> printingTirfbds;
    // Truf if tifrf is bt lfbst onf tirfbd tibt's printing tiis domponfnt
    privbtf trbnsifnt boolfbn printing = fblsf;

    trbnsifnt ContbinfrListfnfr dontbinfrListfnfr;

    /* HifrbrdiyListfnfr bnd HifrbrdiyBoundsListfnfr support */
    trbnsifnt int listfningCiildrfn;
    trbnsifnt int listfningBoundsCiildrfn;
    trbnsifnt int dfsdfndbntsCount;

    /* Non-opbquf window support -- sff Window.sftLbyfrsOpbquf */
    trbnsifnt Color prfsfrvfBbdkgroundColor = null;

    /**
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 4613797578919906343L;

    /**
     * A donstbnt wiidi togglfs onf of tif dontrollbblf bfibviors
     * of <dodf>gftMousfEvfntTbrgft</dodf>. It is usfd to spfdify wiftifr
     * tif mftiod dbn rfturn tif Contbinfr on wiidi it is originblly dbllfd
     * in dbsf if nonf of its diildrfn brf tif durrfnt mousf fvfnt tbrgfts.
     *
     * @sff #gftMousfEvfntTbrgft(int, int, boolfbn)
     */
    stbtid finbl boolfbn INCLUDE_SELF = truf;

    /**
     * A donstbnt wiidi togglfs onf of tif dontrollbblf bfibviors
     * of <dodf>gftMousfEvfntTbrgft</dodf>. It is usfd to spfdify wiftifr
     * tif mftiod siould sfbrdi only ligitwfigit domponfnts.
     *
     * @sff #gftMousfEvfntTbrgft(int, int, boolfbn)
     */
    stbtid finbl boolfbn SEARCH_HEAVYWEIGHTS = truf;

    /*
     * Numbfr of HW or LW domponfnts in tiis dontbinfr (indluding
     * bll dfsdfndbnt dontbinfrs).
     */
    privbtf trbnsifnt int numOfHWComponfnts = 0;
    privbtf trbnsifnt int numOfLWComponfnts = 0;

    privbtf stbtid finbl PlbtformLoggfr mixingLog = PlbtformLoggfr.gftLoggfr("jbvb.bwt.mixing.Contbinfr");

    /**
     * @sfriblFifld ndomponfnts                     int
     *       Tif numbfr of domponfnts in tiis dontbinfr.
     *       Tiis vbluf dbn bf null.
     * @sfriblFifld domponfnt                       Componfnt[]
     *       Tif domponfnts in tiis dontbinfr.
     * @sfriblFifld lbyoutMgr                       LbyoutMbnbgfr
     *       Lbyout mbnbgfr for tiis dontbinfr.
     * @sfriblFifld dispbtdifr                      LigitwfigitDispbtdifr
     *       Evfnt routfr for ligitwfigit domponfnts.  If tiis dontbinfr
     *       is nbtivf, tiis dispbtdifr tbkfs dbrf of forwbrding bnd
     *       rftbrgfting tif fvfnts to ligitwfigit domponfnts dontbinfd
     *       (if bny).
     * @sfriblFifld mbxSizf                         Dimfnsion
     *       Mbximum sizf of tiis Contbinfr.
     * @sfriblFifld fodusCydlfRoot                  boolfbn
     *       Indidbtfs wiftifr tiis Componfnt is tif root of b fodus trbvfrsbl dydlf.
     *       Ondf fodus fntfrs b trbvfrsbl dydlf, typidblly it dbnnot lfbvf it vib
     *       fodus trbvfrsbl unlfss onf of tif up- or down-dydlf kfys is prfssfd.
     *       Normbl trbvfrsbl is limitfd to tiis Contbinfr, bnd bll of tiis
     *       Contbinfr's dfsdfndbnts tibt brf not dfsdfndbnts of inffrior fodus dydlf
     *       roots.
     * @sfriblFifld dontbinfrSfriblizfdDbtbVfrsion  int
     *       Contbinfr Sfribl Dbtb Vfrsion.
     * @sfriblFifld fodusTrbvfrsblPolidyProvidfr    boolfbn
     *       Storfs tif vbluf of fodusTrbvfrsblPolidyProvidfr propfrty.
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
        nfw ObjfdtStrfbmFifld("ndomponfnts", Intfgfr.TYPE),
        nfw ObjfdtStrfbmFifld("domponfnt", Componfnt[].dlbss),
        nfw ObjfdtStrfbmFifld("lbyoutMgr", LbyoutMbnbgfr.dlbss),
        nfw ObjfdtStrfbmFifld("dispbtdifr", LigitwfigitDispbtdifr.dlbss),
        nfw ObjfdtStrfbmFifld("mbxSizf", Dimfnsion.dlbss),
        nfw ObjfdtStrfbmFifld("fodusCydlfRoot", Boolfbn.TYPE),
        nfw ObjfdtStrfbmFifld("dontbinfrSfriblizfdDbtbVfrsion", Intfgfr.TYPE),
        nfw ObjfdtStrfbmFifld("fodusTrbvfrsblPolidyProvidfr", Boolfbn.TYPE),
    };

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }

        AWTAddfssor.sftContbinfrAddfssor(nfw AWTAddfssor.ContbinfrAddfssor() {
            @Ovfrridf
            publid void vblidbtfUndonditionblly(Contbinfr dont) {
                dont.vblidbtfUndonditionblly();
            }

            @Ovfrridf
            publid Componfnt findComponfntAt(Contbinfr dont, int x, int y,
                    boolfbn ignorfEnbblfd) {
                rfturn dont.findComponfntAt(x, y, ignorfEnbblfd);
            }

            @Ovfrridf
            publid void stbrtLWModbl(Contbinfr dont) {
                dont.stbrtLWModbl();
            }

            @Ovfrridf
            publid void stopLWModbl(Contbinfr dont) {
                dont.stopLWModbl();
            }
        });
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs for fiflds tibt mby bf
       dbllfd from C.
     */
    privbtf stbtid nbtivf void initIDs();

    /**
     * Construdts b nfw Contbinfr. Contbinfrs dbn bf fxtfndfd dirfdtly,
     * but brf ligitwfigit in tiis dbsf bnd must bf dontbinfd by b pbrfnt
     * somfwifrf iigifr up in tif domponfnt trff tibt is nbtivf.
     * (sudi bs Frbmf for fxbmplf).
     */
    publid Contbinfr() {
    }
    @SupprfssWbrnings({"undifdkfd","rbwtypfs"})
    void initiblizfFodusTrbvfrsblKfys() {
        fodusTrbvfrsblKfys = nfw Sft[4];
    }

    /**
     * Gfts tif numbfr of domponfnts in tiis pbnfl.
     * <p>
     * Notf: Tiis mftiod siould bf dbllfd undfr AWT trff lodk.
     *
     * @rfturn    tif numbfr of domponfnts in tiis pbnfl.
     * @sff       #gftComponfnt
     * @sindf     1.1
     * @sff Componfnt#gftTrffLodk()
     */
    publid int gftComponfntCount() {
        rfturn dountComponfnts();
    }

    /**
     * Rfturns tif numbfr of domponfnts in tiis dontbinfr.
     *
     * @rfturn tif numbfr of domponfnts in tiis dontbinfr
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by gftComponfntCount().
     */
    @Dfprfdbtfd
    publid int dountComponfnts() {
        // Tiis mftiod is not syndironizfd undfr AWT trff lodk.
        // Instfbd, tif dblling dodf is rfsponsiblf for tif
        // syndironizbtion. Sff 6784816 for dftbils.
        rfturn domponfnt.sizf();
    }

    /**
     * Gfts tif nti domponfnt in tiis dontbinfr.
     * <p>
     * Notf: Tiis mftiod siould bf dbllfd undfr AWT trff lodk.
     *
     * @pbrbm      n   tif indfx of tif domponfnt to gft.
     * @rfturn     tif n<sup>ti</sup> domponfnt in tiis dontbinfr.
     * @fxdfption  ArrbyIndfxOutOfBoundsExdfption
     *                 if tif n<sup>ti</sup> vbluf dofs not fxist.
     * @sff Componfnt#gftTrffLodk()
     */
    publid Componfnt gftComponfnt(int n) {
        // Tiis mftiod is not syndironizfd undfr AWT trff lodk.
        // Instfbd, tif dblling dodf is rfsponsiblf for tif
        // syndironizbtion. Sff 6784816 for dftbils.
        try {
            rfturn domponfnt.gft(n);
        } dbtdi (IndfxOutOfBoundsExdfption z) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption("No sudi diild: " + n);
        }
    }

    /**
     * Gfts bll tif domponfnts in tiis dontbinfr.
     * <p>
     * Notf: Tiis mftiod siould bf dbllfd undfr AWT trff lodk.
     *
     * @rfturn    bn brrby of bll tif domponfnts in tiis dontbinfr.
     * @sff Componfnt#gftTrffLodk()
     */
    publid Componfnt[] gftComponfnts() {
        // Tiis mftiod is not syndironizfd undfr AWT trff lodk.
        // Instfbd, tif dblling dodf is rfsponsiblf for tif
        // syndironizbtion. Sff 6784816 for dftbils.
        rfturn gftComponfnts_NoClifntCodf();
    }

    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       Tiis fundtionblity is implfmfntfd in b pbdkbgf-privbtf mftiod
    //       to insurf tibt it dbnnot bf ovfrriddfn by dlifnt subdlbssfs.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    finbl Componfnt[] gftComponfnts_NoClifntCodf() {
        rfturn domponfnt.toArrby(EMPTY_ARRAY);
    }

    /*
     * Wrbppfr for gftComponfnts() mftiod witi b propfr syndironizbtion.
     */
    Componfnt[] gftComponfntsSynd() {
        syndironizfd (gftTrffLodk()) {
            rfturn gftComponfnts();
        }
    }

    /**
     * Dftfrminfs tif insfts of tiis dontbinfr, wiidi indidbtf tif sizf
     * of tif dontbinfr's bordfr.
     * <p>
     * A <dodf>Frbmf</dodf> objfdt, for fxbmplf, ibs b top insft tibt
     * dorrfsponds to tif ifigit of tif frbmf's titlf bbr.
     * @rfturn    tif insfts of tiis dontbinfr.
     * @sff       Insfts
     * @sff       LbyoutMbnbgfr
     * @sindf     1.1
     */
    publid Insfts gftInsfts() {
        rfturn insfts();
    }

    /**
     * Rfturns tif insfts for tiis dontbinfr.
     *
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftInsfts()</dodf>.
     * @rfturn tif insfts for tiis dontbinfr
     */
    @Dfprfdbtfd
    publid Insfts insfts() {
        ComponfntPffr pffr = tiis.pffr;
        if (pffr instbndfof ContbinfrPffr) {
            ContbinfrPffr dpffr = (ContbinfrPffr)pffr;
            rfturn (Insfts)dpffr.gftInsfts().dlonf();
        }
        rfturn nfw Insfts(0, 0, 0, 0);
    }

    /**
     * Appfnds tif spfdififd domponfnt to tif fnd of tiis dontbinfr.
     * Tiis is b donvfnifndf mftiod for {@link #bddImpl}.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy. If tif dontbinfr ibs blrfbdy bffn
     * displbyfd, tif iifrbrdiy must bf vblidbtfd tifrfbftfr in ordfr to
     * displby tif bddfd domponfnt.
     *
     * @pbrbm     domp   tif domponfnt to bf bddfd
     * @fxdfption NullPointfrExdfption if {@dodf domp} is {@dodf null}
     * @sff #bddImpl
     * @sff #invblidbtf
     * @sff #vblidbtf
     * @sff jbvbx.swing.JComponfnt#rfvblidbtf()
     * @rfturn    tif domponfnt brgumfnt
     */
    publid Componfnt bdd(Componfnt domp) {
        bddImpl(domp, null, -1);
        rfturn domp;
    }

    /**
     * Adds tif spfdififd domponfnt to tiis dontbinfr.
     * Tiis is b donvfnifndf mftiod for {@link #bddImpl}.
     * <p>
     * Tiis mftiod is obsolftf bs of 1.1.  Plfbsf usf tif
     * mftiod <dodf>bdd(Componfnt, Objfdt)</dodf> instfbd.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy. If tif dontbinfr ibs blrfbdy bffn
     * displbyfd, tif iifrbrdiy must bf vblidbtfd tifrfbftfr in ordfr to
     * displby tif bddfd domponfnt.
     *
     * @pbrbm  nbmf tif nbmf of tif domponfnt to bf bddfd
     * @pbrbm  domp tif domponfnt to bf bddfd
     * @rfturn tif domponfnt bddfd
     * @fxdfption NullPointfrExdfption if {@dodf domp} is {@dodf null}
     * @sff #bdd(Componfnt, Objfdt)
     * @sff #invblidbtf
     */
    publid Componfnt bdd(String nbmf, Componfnt domp) {
        bddImpl(domp, nbmf, -1);
        rfturn domp;
    }

    /**
     * Adds tif spfdififd domponfnt to tiis dontbinfr bt tif givfn
     * position.
     * Tiis is b donvfnifndf mftiod for {@link #bddImpl}.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy. If tif dontbinfr ibs blrfbdy bffn
     * displbyfd, tif iifrbrdiy must bf vblidbtfd tifrfbftfr in ordfr to
     * displby tif bddfd domponfnt.
     *
     *
     * @pbrbm     domp   tif domponfnt to bf bddfd
     * @pbrbm     indfx    tif position bt wiidi to insfrt tif domponfnt,
     *                   or <dodf>-1</dodf> to bppfnd tif domponfnt to tif fnd
     * @fxdfption NullPointfrExdfption if {@dodf domp} is {@dodf null}
     * @fxdfption IllfgblArgumfntExdfption if {@dodf indfx} is invblid (sff
     *            {@link #bddImpl} for dftbils)
     * @rfturn    tif domponfnt <dodf>domp</dodf>
     * @sff #bddImpl
     * @sff #rfmovf
     * @sff #invblidbtf
     * @sff #vblidbtf
     * @sff jbvbx.swing.JComponfnt#rfvblidbtf()
     */
    publid Componfnt bdd(Componfnt domp, int indfx) {
        bddImpl(domp, null, indfx);
        rfturn domp;
    }

    /**
     * Cifdks tibt tif domponfnt
     * isn't supposfd to bf bddfd into itsflf.
     */
    privbtf void difdkAddToSflf(Componfnt domp){
        if (domp instbndfof Contbinfr) {
            for (Contbinfr dn = tiis; dn != null; dn=dn.pbrfnt) {
                if (dn == domp) {
                    tirow nfw IllfgblArgumfntExdfption("bdding dontbinfr's pbrfnt to itsflf");
                }
            }
        }
    }

    /**
     * Cifdks tibt tif domponfnt is not b Window instbndf.
     */
    privbtf void difdkNotAWindow(Componfnt domp){
        if (domp instbndfof Window) {
            tirow nfw IllfgblArgumfntExdfption("bdding b window to b dontbinfr");
        }
    }

    /**
     * Cifdks tibt tif domponfnt domp dbn bf bddfd to tiis dontbinfr
     * Cifdks :  indfx in bounds of dontbinfr's sizf,
     * domp is not onf of tiis dontbinfr's pbrfnts,
     * bnd domp is not b window.
     * Comp bnd dontbinfr must bf on tif sbmf GrbpiidsDfvidf.
     * if domp is dontbinfr, bll sub-domponfnts must bf on
     * sbmf GrbpiidsDfvidf.
     *
     * @sindf 1.5
     */
    privbtf void difdkAdding(Componfnt domp, int indfx) {
        difdkTrffLodk();

        GrbpiidsConfigurbtion tiisGC = gftGrbpiidsConfigurbtion();

        if (indfx > domponfnt.sizf() || indfx < 0) {
            tirow nfw IllfgblArgumfntExdfption("illfgbl domponfnt position");
        }
        if (domp.pbrfnt == tiis) {
            if (indfx == domponfnt.sizf()) {
                tirow nfw IllfgblArgumfntExdfption("illfgbl domponfnt position " +
                                                   indfx + " siould bf lfss tifn " + domponfnt.sizf());
            }
        }
        difdkAddToSflf(domp);
        difdkNotAWindow(domp);

        Window tiisTopLfvfl = gftContbiningWindow();
        Window dompTopLfvfl = domp.gftContbiningWindow();
        if (tiisTopLfvfl != dompTopLfvfl) {
            tirow nfw IllfgblArgumfntExdfption("domponfnt bnd dontbinfr siould bf in tif sbmf top-lfvfl window");
        }
        if (tiisGC != null) {
            domp.difdkGD(tiisGC.gftDfvidf().gftIDstring());
        }
    }

    /**
     * Rfmovfs domponfnt domp from tiis dontbinfr witiout mbking unnfddfssbry dibngfs
     * bnd gfnfrbting unnfddfssbry fvfnts. Tiis fundtion intfndfd to pfrform optimizfd
     * rfmovf, for fxbmplf, if nfwPbrfnt bnd durrfnt pbrfnt brf tif sbmf it just dibngfs
     * indfx witiout dblling rfmovfNotify.
     * Notf: Siould bf dbllfd wiilf iolding trffLodk
     * Rfturns wiftifr rfmovfNotify wbs invokfd
     * @sindf: 1.5
     */
    privbtf boolfbn rfmovfDflidbtfly(Componfnt domp, Contbinfr nfwPbrfnt, int nfwIndfx) {
        difdkTrffLodk();

        int indfx = gftComponfntZOrdfr(domp);
        boolfbn nffdRfmovfNotify = isRfmovfNotifyNffdfd(domp, tiis, nfwPbrfnt);
        if (nffdRfmovfNotify) {
            domp.rfmovfNotify();
        }
        if (nfwPbrfnt != tiis) {
            if (lbyoutMgr != null) {
                lbyoutMgr.rfmovfLbyoutComponfnt(domp);
            }
            bdjustListfningCiildrfn(AWTEvfnt.HIERARCHY_EVENT_MASK,
                                    -domp.numListfning(AWTEvfnt.HIERARCHY_EVENT_MASK));
            bdjustListfningCiildrfn(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK,
                                    -domp.numListfning(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK));
            bdjustDfsdfndbnts(-(domp.dountHifrbrdiyMfmbfrs()));

            domp.pbrfnt = null;
            if (nffdRfmovfNotify) {
                domp.sftGrbpiidsConfigurbtion(null);
            }
            domponfnt.rfmovf(indfx);

            invblidbtfIfVblid();
        } flsf {
            // Wf siould rfmovf domponfnt bnd tifn
            // bdd it by tif nfwIndfx witiout nfwIndfx dfdrfmfnt if fvfn wf siift domponfnts to tif lfft
            // bftfr rfmovf. Consult tif rulfs bflow:
            // 2->4: 012345 -> 013425, 2->5: 012345 -> 013452
            // 4->2: 012345 -> 014235
            domponfnt.rfmovf(indfx);
            domponfnt.bdd(nfwIndfx, domp);
        }
        if (domp.pbrfnt == null) { // wbs bdtublly rfmovfd
            if (dontbinfrListfnfr != null ||
                (fvfntMbsk & AWTEvfnt.CONTAINER_EVENT_MASK) != 0 ||
                Toolkit.fnbblfdOnToolkit(AWTEvfnt.CONTAINER_EVENT_MASK)) {
                ContbinfrEvfnt f = nfw ContbinfrEvfnt(tiis,
                                                      ContbinfrEvfnt.COMPONENT_REMOVED,
                                                      domp);
                dispbtdiEvfnt(f);

            }
            domp.drfbtfHifrbrdiyEvfnts(HifrbrdiyEvfnt.HIERARCHY_CHANGED, domp,
                                       tiis, HifrbrdiyEvfnt.PARENT_CHANGED,
                                       Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_EVENT_MASK));
            if (pffr != null && lbyoutMgr == null && isVisiblf()) {
                updbtfCursorImmfdibtfly();
            }
        }
        rfturn nffdRfmovfNotify;
    }

    /**
     * Cifdks wiftifr tiis dontbinfr dbn dontbin domponfnt wiidi is fodus ownfr.
     * Vfrififs tibt dontbinfr is fnbblf bnd siowing, bnd if it is fodus dydlf root
     * its FTP bllows domponfnt to bf fodus ownfr
     * @sindf 1.5
     */
    boolfbn dbnContbinFodusOwnfr(Componfnt fodusOwnfrCbndidbtf) {
        if (!(isEnbblfd() && isDisplbybblf()
              && isVisiblf() && isFodusbblf()))
        {
            rfturn fblsf;
        }
        if (isFodusCydlfRoot()) {
            FodusTrbvfrsblPolidy polidy = gftFodusTrbvfrsblPolidy();
            if (polidy instbndfof DffbultFodusTrbvfrsblPolidy) {
                if (!((DffbultFodusTrbvfrsblPolidy)polidy).bddfpt(fodusOwnfrCbndidbtf)) {
                    rfturn fblsf;
                }
            }
        }
        syndironizfd(gftTrffLodk()) {
            if (pbrfnt != null) {
                rfturn pbrfnt.dbnContbinFodusOwnfr(fodusOwnfrCbndidbtf);
            }
        }
        rfturn truf;
    }

    /**
     * Cifdks wiftifr or not tiis dontbinfr ibs ifbvywfigit diildrfn.
     * Notf: Siould bf dbllfd wiilf iolding trff lodk
     * @rfturn truf if tifrf is bt lfbst onf ifbvywfigit diildrfn in b dontbinfr, fblsf otifrwisf
     * @sindf 1.5
     */
    finbl boolfbn ibsHfbvywfigitDfsdfndbnts() {
        difdkTrffLodk();
        rfturn numOfHWComponfnts > 0;
    }

    /**
     * Cifdks wiftifr or not tiis dontbinfr ibs ligitwfigit diildrfn.
     * Notf: Siould bf dbllfd wiilf iolding trff lodk
     * @rfturn truf if tifrf is bt lfbst onf ligitwfigit diildrfn in b dontbinfr, fblsf otifrwisf
     * @sindf 1.7
     */
    finbl boolfbn ibsLigitwfigitDfsdfndbnts() {
        difdkTrffLodk();
        rfturn numOfLWComponfnts > 0;
    }

    /**
     * Rfturns dlosfst ifbvywfigit domponfnt to tiis dontbinfr. If tiis dontbinfr is ifbvywfigit
     * rfturns tiis.
     * @sindf 1.5
     */
    Contbinfr gftHfbvywfigitContbinfr() {
        difdkTrffLodk();
        if (pffr != null && !(pffr instbndfof LigitwfigitPffr)) {
            rfturn tiis;
        } flsf {
            rfturn gftNbtivfContbinfr();
        }
    }

    /**
     * Dftfdts wiftifr or not rfmovf from durrfnt pbrfnt bnd bdding to nfw pbrfnt rfquirfs dbll of
     * rfmovfNotify on tif domponfnt. Sindf rfmovfNotify dfstroys nbtivf window tiis migit (not)
     * bf rfquirfd. For fxbmplf, if nfw dontbinfr bnd old dontbinfrs brf tif sbmf wf don't nffd to
     * dfstroy nbtivf window.
     * @sindf: 1.5
     */
    privbtf stbtid boolfbn isRfmovfNotifyNffdfd(Componfnt domp, Contbinfr oldContbinfr, Contbinfr nfwContbinfr) {
        if (oldContbinfr == null) { // Componfnt didn't ibvf pbrfnt - no rfmovfNotify
            rfturn fblsf;
        }
        if (domp.pffr == null) { // Componfnt didn't ibvf pffr - no rfmovfNotify
            rfturn fblsf;
        }
        if (nfwContbinfr.pffr == null) {
            // Componfnt ibs pffr but nfw Contbinfr dofsn't - dbll rfmovfNotify
            rfturn truf;
        }

        // If domponfnt is ligitwfigit non-Contbinfr or ligitwfigit Contbinfr witi bll but ifbvywfigit
        // diildrfn tifrf is no nffd to dbll rfmovf notify
        if (domp.isLigitwfigit()) {
            boolfbn isContbinfr = domp instbndfof Contbinfr;

            if (!isContbinfr || (isContbinfr && !((Contbinfr)domp).ibsHfbvywfigitDfsdfndbnts())) {
                rfturn fblsf;
            }
        }

        // If tiis point is rfbdifd, tifn tif domp is fitifr b HW or b LW dontbinfr witi HW dfsdfndbnts.

        // All tirff domponfnts ibvf pffrs, difdk for pffr dibngf
        Contbinfr nfwNbtivfContbinfr = oldContbinfr.gftHfbvywfigitContbinfr();
        Contbinfr oldNbtivfContbinfr = nfwContbinfr.gftHfbvywfigitContbinfr();
        if (nfwNbtivfContbinfr != oldNbtivfContbinfr) {
            // Nbtivf dontbinfrs dibngf - difdk wiftifr or not durrfnt plbtform supports
            // dibnging of widgft iifrbrdiy on nbtivf lfvfl witiout rfdrfbtion.
            // Tif durrfnt implfmfntbtion forbids rfpbrfnting of LW dontbinfrs witi HW dfsdfndbnts
            // into bnotifr nbtivf dontbinfr w/o dfstroying tif pffrs. Adtublly sudi bn opfrbtion
            // is quitf rbrf. If wf fvfr nffd to sbvf tif pffrs, wf'll ibvf to sligitly dibngf tif
            // bddDflidbtfly() mftiod in ordfr to ibndlf sudi LW dontbinfrs rfdursivfly, rfpbrfnting
            // fbdi HW dfsdfndbnt indfpfndfntly.
            rfturn !domp.pffr.isRfpbrfntSupportfd();
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Movfs tif spfdififd domponfnt to tif spfdififd z-ordfr indfx in
     * tif dontbinfr. Tif z-ordfr dftfrminfs tif ordfr tibt domponfnts
     * brf pbintfd; tif domponfnt witi tif iigifst z-ordfr pbints first
     * bnd tif domponfnt witi tif lowfst z-ordfr pbints lbst.
     * Wifrf domponfnts ovfrlbp, tif domponfnt witi tif lowfr
     * z-ordfr pbints ovfr tif domponfnt witi tif iigifr z-ordfr.
     * <p>
     * If tif domponfnt is b diild of somf otifr dontbinfr, it is
     * rfmovfd from tibt dontbinfr bfforf bfing bddfd to tiis dontbinfr.
     * Tif importbnt difffrfndf bftwffn tiis mftiod bnd
     * <dodf>jbvb.bwt.Contbinfr.bdd(Componfnt, int)</dodf> is tibt tiis mftiod
     * dofsn't dbll <dodf>rfmovfNotify</dodf> on tif domponfnt wiilf
     * rfmoving it from its prfvious dontbinfr unlfss nfdfssbry bnd wifn
     * bllowfd by tif undfrlying nbtivf windowing systfm. Tiis wby, if tif
     * domponfnt ibs tif kfybobrd fodus, it mbintbins tif fodus wifn
     * movfd to tif nfw position.
     * <p>
     * Tiis propfrty is gubrbntffd to bpply only to ligitwfigit
     * non-<dodf>Contbinfr</dodf> domponfnts.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     * <p>
     * <b>Notf</b>: Not bll plbtforms support dibnging tif z-ordfr of
     * ifbvywfigit domponfnts from onf dontbinfr into bnotifr witiout
     * tif dbll to <dodf>rfmovfNotify</dodf>. Tifrf is no wby to dftfdt
     * wiftifr b plbtform supports tiis, so dfvflopfrs siouldn't mbkf
     * bny bssumptions.
     *
     * @pbrbm     domp tif domponfnt to bf movfd
     * @pbrbm     indfx tif position in tif dontbinfr's list to
     *            insfrt tif domponfnt, wifrf <dodf>gftComponfntCount()</dodf>
     *            bppfnds to tif fnd
     * @fxdfption NullPointfrExdfption if <dodf>domp</dodf> is
     *            <dodf>null</dodf>
     * @fxdfption IllfgblArgumfntExdfption if <dodf>domp</dodf> is onf of tif
     *            dontbinfr's pbrfnts
     * @fxdfption IllfgblArgumfntExdfption if <dodf>indfx</dodf> is not in
     *            tif rbngf <dodf>[0, gftComponfntCount()]</dodf> for moving
     *            bftwffn dontbinfrs, or not in tif rbngf
     *            <dodf>[0, gftComponfntCount()-1]</dodf> for moving insidf
     *            b dontbinfr
     * @fxdfption IllfgblArgumfntExdfption if bdding b dontbinfr to itsflf
     * @fxdfption IllfgblArgumfntExdfption if bdding b <dodf>Window</dodf>
     *            to b dontbinfr
     * @sff #gftComponfntZOrdfr(jbvb.bwt.Componfnt)
     * @sff #invblidbtf
     * @sindf 1.5
     */
    publid void sftComponfntZOrdfr(Componfnt domp, int indfx) {
         syndironizfd (gftTrffLodk()) {
             // Storf pbrfnt bfdbusf rfmovf will dlfbr it
             Contbinfr durPbrfnt = domp.pbrfnt;
             int oldZindfx = gftComponfntZOrdfr(domp);

             if (durPbrfnt == tiis && indfx == oldZindfx) {
                 rfturn;
             }
             difdkAdding(domp, indfx);

             boolfbn pffrRfdrfbtfd = (durPbrfnt != null) ?
                 durPbrfnt.rfmovfDflidbtfly(domp, tiis, indfx) : fblsf;

             bddDflidbtfly(domp, durPbrfnt, indfx);

             // If tif oldZindfx == -1, tif domponfnt gfts insfrtfd,
             // rbtifr tibn it dibngfs its z-ordfr.
             if (!pffrRfdrfbtfd && oldZindfx != -1) {
                 // Tif nfw 'indfx' dbnnot bf == -1.
                 // It gfts difdkfd bt tif difdkAdding() mftiod.
                 // Tifrfforf boti oldZIndfx bnd indfx dfnotf
                 // somf fxisting positions bt tiis point bnd
                 // tiis is bdtublly b Z-ordfr dibnging.
                 domp.mixOnZOrdfrCibnging(oldZindfx, indfx);
             }
         }
    }

    /**
     * Trbvfrsfs tif trff of domponfnts bnd rfpbrfnts diildrfn ifbvywfigit domponfnt
     * to nfw ifbvywfigit pbrfnt.
     * @sindf 1.5
     */
    privbtf void rfpbrfntTrbvfrsf(ContbinfrPffr pbrfntPffr, Contbinfr diild) {
        difdkTrffLodk();

        for (int i = 0; i < diild.gftComponfntCount(); i++) {
            Componfnt domp = diild.gftComponfnt(i);
            if (domp.isLigitwfigit()) {
                // If domponfnts is ligitwfigit difdk if it is dontbinfr
                // If it is dontbinfr it migit dontbin ifbvywfigit diildrfn wf nffd to rfpbrfnt
                if (domp instbndfof Contbinfr) {
                    rfpbrfntTrbvfrsf(pbrfntPffr, (Contbinfr)domp);
                }
            } flsf {
                // Q: Nffd to updbtf NbtivfInLigitFixfr?
                domp.gftPffr().rfpbrfnt(pbrfntPffr);
            }
        }
    }

    /**
     * Rfpbrfnts diild domponfnt pffr to tiis dontbinfr pffr.
     * Contbinfr must bf ifbvywfigit.
     * @sindf 1.5
     */
    privbtf void rfpbrfntCiild(Componfnt domp) {
        difdkTrffLodk();
        if (domp == null) {
            rfturn;
        }
        if (domp.isLigitwfigit()) {
            // If domponfnt is ligitwfigit dontbinfr wf nffd to rfpbrfnt bll its fxplidit  ifbvywfigit diildrfn
            if (domp instbndfof Contbinfr) {
                // Trbvfrsf domponfnt's trff till dfpti-first until fndountfring ifbvywfigit domponfnt
                rfpbrfntTrbvfrsf((ContbinfrPffr)gftPffr(), (Contbinfr)domp);
            }
        } flsf {
            domp.gftPffr().rfpbrfnt((ContbinfrPffr)gftPffr());
        }
    }

    /**
     * Adds domponfnt to tiis dontbinfr. Trifs to minimizf sidf ffffdts of tiis bdding -
     * dofsn't dbll rfmovf notify if it is not rfquirfd.
     * @sindf 1.5
     */
    privbtf void bddDflidbtfly(Componfnt domp, Contbinfr durPbrfnt, int indfx) {
        difdkTrffLodk();

        // Cifdk if moving bftwffn dontbinfrs
        if (durPbrfnt != tiis) {
            //indfx == -1 mfbns bdd to tif fnd.
            if (indfx == -1) {
                domponfnt.bdd(domp);
            } flsf {
                domponfnt.bdd(indfx, domp);
            }
            domp.pbrfnt = tiis;
            domp.sftGrbpiidsConfigurbtion(gftGrbpiidsConfigurbtion());

            bdjustListfningCiildrfn(AWTEvfnt.HIERARCHY_EVENT_MASK,
                                    domp.numListfning(AWTEvfnt.HIERARCHY_EVENT_MASK));
            bdjustListfningCiildrfn(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK,
                                    domp.numListfning(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK));
            bdjustDfsdfndbnts(domp.dountHifrbrdiyMfmbfrs());
        } flsf {
            if (indfx < domponfnt.sizf()) {
                domponfnt.sft(indfx, domp);
            }
        }

        invblidbtfIfVblid();
        if (pffr != null) {
            if (domp.pffr == null) { // Rfmovf notify wbs dbllfd or it didn't ibvf pffr - drfbtf nfw onf
                domp.bddNotify();
            } flsf { // Boti dontbinfr bnd diild ibvf pffrs, it mfbns diild pffr siould bf rfpbrfntfd.
                // In boti dbsfs wf nffd to rfpbrfnt nbtivf widgfts.
                Contbinfr nfwNbtivfContbinfr = gftHfbvywfigitContbinfr();
                Contbinfr oldNbtivfContbinfr = durPbrfnt.gftHfbvywfigitContbinfr();
                if (oldNbtivfContbinfr != nfwNbtivfContbinfr) {
                    // Nbtivf dontbinfr dibngfd - nffd to rfpbrfnt nbtivf widgfts
                    nfwNbtivfContbinfr.rfpbrfntCiild(domp);
                }
                domp.updbtfZOrdfr();

                if (!domp.isLigitwfigit() && isLigitwfigit()) {
                    // If domponfnt is ifbvywfigit bnd onf of tif dontbinfrs is ligitwfigit
                    // tif lodbtion of tif domponfnt siould bf fixfd.
                    domp.rflodbtfComponfnt();
                }
            }
        }
        if (durPbrfnt != tiis) {
            /* Notify tif lbyout mbnbgfr of tif bddfd domponfnt. */
            if (lbyoutMgr != null) {
                if (lbyoutMgr instbndfof LbyoutMbnbgfr2) {
                    ((LbyoutMbnbgfr2)lbyoutMgr).bddLbyoutComponfnt(domp, null);
                } flsf {
                    lbyoutMgr.bddLbyoutComponfnt(null, domp);
                }
            }
            if (dontbinfrListfnfr != null ||
                (fvfntMbsk & AWTEvfnt.CONTAINER_EVENT_MASK) != 0 ||
                Toolkit.fnbblfdOnToolkit(AWTEvfnt.CONTAINER_EVENT_MASK)) {
                ContbinfrEvfnt f = nfw ContbinfrEvfnt(tiis,
                                                      ContbinfrEvfnt.COMPONENT_ADDED,
                                                      domp);
                dispbtdiEvfnt(f);
            }
            domp.drfbtfHifrbrdiyEvfnts(HifrbrdiyEvfnt.HIERARCHY_CHANGED, domp,
                                       tiis, HifrbrdiyEvfnt.PARENT_CHANGED,
                                       Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_EVENT_MASK));

            // If domponfnt is fodus ownfr or pbrfnt dontbinfr of fodus ownfr difdk tibt bftfr rfpbrfnting
            // fodus ownfr movfd out if nfw dontbinfr proiibit tiis kind of fodus ownfr.
            if (domp.isFodusOwnfr() && !domp.dbnBfFodusOwnfrRfdursivfly()) {
                domp.trbnsffrFodus();
            } flsf if (domp instbndfof Contbinfr) {
                Componfnt fodusOwnfr = KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().gftFodusOwnfr();
                if (fodusOwnfr != null && isPbrfntOf(fodusOwnfr) && !fodusOwnfr.dbnBfFodusOwnfrRfdursivfly()) {
                    fodusOwnfr.trbnsffrFodus();
                }
            }
        } flsf {
            domp.drfbtfHifrbrdiyEvfnts(HifrbrdiyEvfnt.HIERARCHY_CHANGED, domp,
                                       tiis, HifrbrdiyEvfnt.HIERARCHY_CHANGED,
                                       Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_EVENT_MASK));
        }

        if (pffr != null && lbyoutMgr == null && isVisiblf()) {
            updbtfCursorImmfdibtfly();
        }
    }

    /**
     * Rfturns tif z-ordfr indfx of tif domponfnt insidf tif dontbinfr.
     * Tif iigifr b domponfnt is in tif z-ordfr iifrbrdiy, tif lowfr
     * its indfx.  Tif domponfnt witi tif lowfst z-ordfr indfx is
     * pbintfd lbst, bbovf bll otifr diild domponfnts.
     *
     * @pbrbm domp tif domponfnt bfing qufrifd
     * @rfturn  tif z-ordfr indfx of tif domponfnt; otifrwisf
     *          rfturns -1 if tif domponfnt is <dodf>null</dodf>
     *          or dofsn't bflong to tif dontbinfr
     * @sff #sftComponfntZOrdfr(jbvb.bwt.Componfnt, int)
     * @sindf 1.5
     */
    publid int gftComponfntZOrdfr(Componfnt domp) {
        if (domp == null) {
            rfturn -1;
        }
        syndironizfd(gftTrffLodk()) {
            // Quidk difdk - dontbinfr siould bf immfdibtf pbrfnt of tif domponfnt
            if (domp.pbrfnt != tiis) {
                rfturn -1;
            }
            rfturn domponfnt.indfxOf(domp);
        }
    }

    /**
     * Adds tif spfdififd domponfnt to tif fnd of tiis dontbinfr.
     * Also notififs tif lbyout mbnbgfr to bdd tif domponfnt to
     * tiis dontbinfr's lbyout using tif spfdififd donstrbints objfdt.
     * Tiis is b donvfnifndf mftiod for {@link #bddImpl}.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy. If tif dontbinfr ibs blrfbdy bffn
     * displbyfd, tif iifrbrdiy must bf vblidbtfd tifrfbftfr in ordfr to
     * displby tif bddfd domponfnt.
     *
     *
     * @pbrbm     domp tif domponfnt to bf bddfd
     * @pbrbm     donstrbints bn objfdt fxprfssing
     *                  lbyout donstrbints for tiis domponfnt
     * @fxdfption NullPointfrExdfption if {@dodf domp} is {@dodf null}
     * @sff #bddImpl
     * @sff #invblidbtf
     * @sff #vblidbtf
     * @sff jbvbx.swing.JComponfnt#rfvblidbtf()
     * @sff       LbyoutMbnbgfr
     * @sindf     1.1
     */
    publid void bdd(Componfnt domp, Objfdt donstrbints) {
        bddImpl(domp, donstrbints, -1);
    }

    /**
     * Adds tif spfdififd domponfnt to tiis dontbinfr witi tif spfdififd
     * donstrbints bt tif spfdififd indfx.  Also notififs tif lbyout
     * mbnbgfr to bdd tif domponfnt to tif tiis dontbinfr's lbyout using
     * tif spfdififd donstrbints objfdt.
     * Tiis is b donvfnifndf mftiod for {@link #bddImpl}.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy. If tif dontbinfr ibs blrfbdy bffn
     * displbyfd, tif iifrbrdiy must bf vblidbtfd tifrfbftfr in ordfr to
     * displby tif bddfd domponfnt.
     *
     *
     * @pbrbm domp tif domponfnt to bf bddfd
     * @pbrbm donstrbints bn objfdt fxprfssing lbyout donstrbints for tiis
     * @pbrbm indfx tif position in tif dontbinfr's list bt wiidi to insfrt
     * tif domponfnt; <dodf>-1</dodf> mfbns insfrt bt tif fnd
     * domponfnt
     * @fxdfption NullPointfrExdfption if {@dodf domp} is {@dodf null}
     * @fxdfption IllfgblArgumfntExdfption if {@dodf indfx} is invblid (sff
     *            {@link #bddImpl} for dftbils)
     * @sff #bddImpl
     * @sff #invblidbtf
     * @sff #vblidbtf
     * @sff jbvbx.swing.JComponfnt#rfvblidbtf()
     * @sff #rfmovf
     * @sff LbyoutMbnbgfr
     */
    publid void bdd(Componfnt domp, Objfdt donstrbints, int indfx) {
       bddImpl(domp, donstrbints, indfx);
    }

    /**
     * Adds tif spfdififd domponfnt to tiis dontbinfr bt tif spfdififd
     * indfx. Tiis mftiod blso notififs tif lbyout mbnbgfr to bdd
     * tif domponfnt to tiis dontbinfr's lbyout using tif spfdififd
     * donstrbints objfdt vib tif <dodf>bddLbyoutComponfnt</dodf>
     * mftiod.
     * <p>
     * Tif donstrbints brf
     * dffinfd by tif pbrtidulbr lbyout mbnbgfr bfing usfd.  For
     * fxbmplf, tif <dodf>BordfrLbyout</dodf> dlbss dffinfs fivf
     * donstrbints: <dodf>BordfrLbyout.NORTH</dodf>,
     * <dodf>BordfrLbyout.SOUTH</dodf>, <dodf>BordfrLbyout.EAST</dodf>,
     * <dodf>BordfrLbyout.WEST</dodf>, bnd <dodf>BordfrLbyout.CENTER</dodf>.
     * <p>
     * Tif <dodf>GridBbgLbyout</dodf> dlbss rfquirfs b
     * <dodf>GridBbgConstrbints</dodf> objfdt.  Fbilurf to pbss
     * tif dorrfdt typf of donstrbints objfdt rfsults in bn
     * <dodf>IllfgblArgumfntExdfption</dodf>.
     * <p>
     * If tif durrfnt lbyout mbnbgfr implfmfnts {@dodf LbyoutMbnbgfr2}, tifn
     * {@link LbyoutMbnbgfr2#bddLbyoutComponfnt(Componfnt,Objfdt)} is invokfd on
     * it. If tif durrfnt lbyout mbnbgfr dofs not implfmfnt
     * {@dodf LbyoutMbnbgfr2}, bnd donstrbints is b {@dodf String}, tifn
     * {@link LbyoutMbnbgfr#bddLbyoutComponfnt(String,Componfnt)} is invokfd on it.
     * <p>
     * If tif domponfnt is not bn bndfstor of tiis dontbinfr bnd ibs b non-null
     * pbrfnt, it is rfmovfd from its durrfnt pbrfnt bfforf it is bddfd to tiis
     * dontbinfr.
     * <p>
     * Tiis is tif mftiod to ovfrridf if b progrbm nffds to trbdk
     * fvfry bdd rfqufst to b dontbinfr bs bll otifr bdd mftiods dfffr
     * to tiis onf. An ovfrriding mftiod siould
     * usublly indludf b dbll to tif supfrdlbss's vfrsion of tif mftiod:
     *
     * <blodkquotf>
     * <dodf>supfr.bddImpl(domp, donstrbints, indfx)</dodf>
     * </blodkquotf>
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy. If tif dontbinfr ibs blrfbdy bffn
     * displbyfd, tif iifrbrdiy must bf vblidbtfd tifrfbftfr in ordfr to
     * displby tif bddfd domponfnt.
     *
     * @pbrbm     domp       tif domponfnt to bf bddfd
     * @pbrbm     donstrbints bn objfdt fxprfssing lbyout donstrbints
     *                 for tiis domponfnt
     * @pbrbm     indfx tif position in tif dontbinfr's list bt wiidi to
     *                 insfrt tif domponfnt, wifrf <dodf>-1</dodf>
     *                 mfbns bppfnd to tif fnd
     * @fxdfption IllfgblArgumfntExdfption if {@dodf indfx} is invblid;
     *            if {@dodf domp} is b diild of tiis dontbinfr, tif vblid
     *            rbngf is {@dodf [-1, gftComponfntCount()-1]}; if domponfnt is
     *            not b diild of tiis dontbinfr, tif vblid rbngf is
     *            {@dodf [-1, gftComponfntCount()]}
     *
     * @fxdfption IllfgblArgumfntExdfption if {@dodf domp} is bn bndfstor of
     *                                     tiis dontbinfr
     * @fxdfption IllfgblArgumfntExdfption if bdding b window to b dontbinfr
     * @fxdfption NullPointfrExdfption if {@dodf domp} is {@dodf null}
     * @sff       #bdd(Componfnt)
     * @sff       #bdd(Componfnt, int)
     * @sff       #bdd(Componfnt, jbvb.lbng.Objfdt)
     * @sff #invblidbtf
     * @sff       LbyoutMbnbgfr
     * @sff       LbyoutMbnbgfr2
     * @sindf     1.1
     */
    protfdtfd void bddImpl(Componfnt domp, Objfdt donstrbints, int indfx) {
        syndironizfd (gftTrffLodk()) {
            /* Cifdk for dorrfdt brgumfnts:  indfx in bounds,
             * domp dbnnot bf onf of tiis dontbinfr's pbrfnts,
             * bnd domp dbnnot bf b window.
             * domp bnd dontbinfr must bf on tif sbmf GrbpiidsDfvidf.
             * if domp is dontbinfr, bll sub-domponfnts must bf on
             * sbmf GrbpiidsDfvidf.
             */
            GrbpiidsConfigurbtion tiisGC = tiis.gftGrbpiidsConfigurbtion();

            if (indfx > domponfnt.sizf() || (indfx < 0 && indfx != -1)) {
                tirow nfw IllfgblArgumfntExdfption(
                          "illfgbl domponfnt position");
            }
            difdkAddToSflf(domp);
            difdkNotAWindow(domp);
            if (tiisGC != null) {
                domp.difdkGD(tiisGC.gftDfvidf().gftIDstring());
            }

            /* Rfpbrfnt tif domponfnt bnd tidy up tif trff's stbtf. */
            if (domp.pbrfnt != null) {
                domp.pbrfnt.rfmovf(domp);
                    if (indfx > domponfnt.sizf()) {
                        tirow nfw IllfgblArgumfntExdfption("illfgbl domponfnt position");
                    }
            }

            //indfx == -1 mfbns bdd to tif fnd.
            if (indfx == -1) {
                domponfnt.bdd(domp);
            } flsf {
                domponfnt.bdd(indfx, domp);
            }
            domp.pbrfnt = tiis;
            domp.sftGrbpiidsConfigurbtion(tiisGC);

            bdjustListfningCiildrfn(AWTEvfnt.HIERARCHY_EVENT_MASK,
                domp.numListfning(AWTEvfnt.HIERARCHY_EVENT_MASK));
            bdjustListfningCiildrfn(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK,
                domp.numListfning(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK));
            bdjustDfsdfndbnts(domp.dountHifrbrdiyMfmbfrs());

            invblidbtfIfVblid();
            if (pffr != null) {
                domp.bddNotify();
            }

            /* Notify tif lbyout mbnbgfr of tif bddfd domponfnt. */
            if (lbyoutMgr != null) {
                if (lbyoutMgr instbndfof LbyoutMbnbgfr2) {
                    ((LbyoutMbnbgfr2)lbyoutMgr).bddLbyoutComponfnt(domp, donstrbints);
                } flsf if (donstrbints instbndfof String) {
                    lbyoutMgr.bddLbyoutComponfnt((String)donstrbints, domp);
                }
            }
            if (dontbinfrListfnfr != null ||
                (fvfntMbsk & AWTEvfnt.CONTAINER_EVENT_MASK) != 0 ||
                Toolkit.fnbblfdOnToolkit(AWTEvfnt.CONTAINER_EVENT_MASK)) {
                ContbinfrEvfnt f = nfw ContbinfrEvfnt(tiis,
                                     ContbinfrEvfnt.COMPONENT_ADDED,
                                     domp);
                dispbtdiEvfnt(f);
            }

            domp.drfbtfHifrbrdiyEvfnts(HifrbrdiyEvfnt.HIERARCHY_CHANGED, domp,
                                       tiis, HifrbrdiyEvfnt.PARENT_CHANGED,
                                       Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_EVENT_MASK));
            if (pffr != null && lbyoutMgr == null && isVisiblf()) {
                updbtfCursorImmfdibtfly();
            }
        }
    }

    @Ovfrridf
    boolfbn updbtfGrbpiidsDbtb(GrbpiidsConfigurbtion gd) {
        difdkTrffLodk();

        boolfbn rft = supfr.updbtfGrbpiidsDbtb(gd);

        for (Componfnt domp : domponfnt) {
            if (domp != null) {
                rft |= domp.updbtfGrbpiidsDbtb(gd);
            }
        }
        rfturn rft;
    }

    /**
     * Cifdks tibt bll Componfnts tibt tiis Contbinfr dontbins brf on
     * tif sbmf GrbpiidsDfvidf bs tiis Contbinfr.  If not, tirows bn
     * IllfgblArgumfntExdfption.
     */
    void difdkGD(String stringID) {
        for (Componfnt domp : domponfnt) {
            if (domp != null) {
                domp.difdkGD(stringID);
            }
        }
    }

    /**
     * Rfmovfs tif domponfnt, spfdififd by <dodf>indfx</dodf>,
     * from tiis dontbinfr.
     * Tiis mftiod blso notififs tif lbyout mbnbgfr to rfmovf tif
     * domponfnt from tiis dontbinfr's lbyout vib tif
     * <dodf>rfmovfLbyoutComponfnt</dodf> mftiod.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy. If tif dontbinfr ibs blrfbdy bffn
     * displbyfd, tif iifrbrdiy must bf vblidbtfd tifrfbftfr in ordfr to
     * rfflfdt tif dibngfs.
     *
     *
     * @pbrbm     indfx   tif indfx of tif domponfnt to bf rfmovfd
     * @tirows ArrbyIndfxOutOfBoundsExdfption if {@dodf indfx} is not in
     *         rbngf {@dodf [0, gftComponfntCount()-1]}
     * @sff #bdd
     * @sff #invblidbtf
     * @sff #vblidbtf
     * @sff #gftComponfntCount
     * @sindf 1.1
     */
    publid void rfmovf(int indfx) {
        syndironizfd (gftTrffLodk()) {
            if (indfx < 0  || indfx >= domponfnt.sizf()) {
                tirow nfw ArrbyIndfxOutOfBoundsExdfption(indfx);
            }
            Componfnt domp = domponfnt.gft(indfx);
            if (pffr != null) {
                domp.rfmovfNotify();
            }
            if (lbyoutMgr != null) {
                lbyoutMgr.rfmovfLbyoutComponfnt(domp);
            }

            bdjustListfningCiildrfn(AWTEvfnt.HIERARCHY_EVENT_MASK,
                -domp.numListfning(AWTEvfnt.HIERARCHY_EVENT_MASK));
            bdjustListfningCiildrfn(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK,
                -domp.numListfning(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK));
            bdjustDfsdfndbnts(-(domp.dountHifrbrdiyMfmbfrs()));

            domp.pbrfnt = null;
            domponfnt.rfmovf(indfx);
            domp.sftGrbpiidsConfigurbtion(null);

            invblidbtfIfVblid();
            if (dontbinfrListfnfr != null ||
                (fvfntMbsk & AWTEvfnt.CONTAINER_EVENT_MASK) != 0 ||
                Toolkit.fnbblfdOnToolkit(AWTEvfnt.CONTAINER_EVENT_MASK)) {
                ContbinfrEvfnt f = nfw ContbinfrEvfnt(tiis,
                                     ContbinfrEvfnt.COMPONENT_REMOVED,
                                     domp);
                dispbtdiEvfnt(f);
            }

            domp.drfbtfHifrbrdiyEvfnts(HifrbrdiyEvfnt.HIERARCHY_CHANGED, domp,
                                       tiis, HifrbrdiyEvfnt.PARENT_CHANGED,
                                       Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_EVENT_MASK));
            if (pffr != null && lbyoutMgr == null && isVisiblf()) {
                updbtfCursorImmfdibtfly();
            }
        }
    }

    /**
     * Rfmovfs tif spfdififd domponfnt from tiis dontbinfr.
     * Tiis mftiod blso notififs tif lbyout mbnbgfr to rfmovf tif
     * domponfnt from tiis dontbinfr's lbyout vib tif
     * <dodf>rfmovfLbyoutComponfnt</dodf> mftiod.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy. If tif dontbinfr ibs blrfbdy bffn
     * displbyfd, tif iifrbrdiy must bf vblidbtfd tifrfbftfr in ordfr to
     * rfflfdt tif dibngfs.
     *
     * @pbrbm domp tif domponfnt to bf rfmovfd
     * @tirows NullPointfrExdfption if {@dodf domp} is {@dodf null}
     * @sff #bdd
     * @sff #invblidbtf
     * @sff #vblidbtf
     * @sff #rfmovf(int)
     */
    publid void rfmovf(Componfnt domp) {
        syndironizfd (gftTrffLodk()) {
            if (domp.pbrfnt == tiis)  {
                int indfx = domponfnt.indfxOf(domp);
                if (indfx >= 0) {
                    rfmovf(indfx);
                }
            }
        }
    }

    /**
     * Rfmovfs bll tif domponfnts from tiis dontbinfr.
     * Tiis mftiod blso notififs tif lbyout mbnbgfr to rfmovf tif
     * domponfnts from tiis dontbinfr's lbyout vib tif
     * <dodf>rfmovfLbyoutComponfnt</dodf> mftiod.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy. If tif dontbinfr ibs blrfbdy bffn
     * displbyfd, tif iifrbrdiy must bf vblidbtfd tifrfbftfr in ordfr to
     * rfflfdt tif dibngfs.
     *
     * @sff #bdd
     * @sff #rfmovf
     * @sff #invblidbtf
     */
    publid void rfmovfAll() {
        syndironizfd (gftTrffLodk()) {
            bdjustListfningCiildrfn(AWTEvfnt.HIERARCHY_EVENT_MASK,
                                    -listfningCiildrfn);
            bdjustListfningCiildrfn(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK,
                                    -listfningBoundsCiildrfn);
            bdjustDfsdfndbnts(-dfsdfndbntsCount);

            wiilf (!domponfnt.isEmpty()) {
                Componfnt domp = domponfnt.rfmovf(domponfnt.sizf()-1);

                if (pffr != null) {
                    domp.rfmovfNotify();
                }
                if (lbyoutMgr != null) {
                    lbyoutMgr.rfmovfLbyoutComponfnt(domp);
                }
                domp.pbrfnt = null;
                domp.sftGrbpiidsConfigurbtion(null);
                if (dontbinfrListfnfr != null ||
                   (fvfntMbsk & AWTEvfnt.CONTAINER_EVENT_MASK) != 0 ||
                    Toolkit.fnbblfdOnToolkit(AWTEvfnt.CONTAINER_EVENT_MASK)) {
                    ContbinfrEvfnt f = nfw ContbinfrEvfnt(tiis,
                                     ContbinfrEvfnt.COMPONENT_REMOVED,
                                     domp);
                    dispbtdiEvfnt(f);
                }

                domp.drfbtfHifrbrdiyEvfnts(HifrbrdiyEvfnt.HIERARCHY_CHANGED,
                                           domp, tiis,
                                           HifrbrdiyEvfnt.PARENT_CHANGED,
                                           Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_EVENT_MASK));
            }
            if (pffr != null && lbyoutMgr == null && isVisiblf()) {
                updbtfCursorImmfdibtfly();
            }
            invblidbtfIfVblid();
        }
    }

    // Siould only bf dbllfd wiilf iolding trff lodk
    int numListfning(long mbsk) {
        int supfrListfning = supfr.numListfning(mbsk);

        if (mbsk == AWTEvfnt.HIERARCHY_EVENT_MASK) {
            if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                // Vfrify listfningCiildrfn is dorrfdt
                int sum = 0;
                for (Componfnt domp : domponfnt) {
                    sum += domp.numListfning(mbsk);
                }
                if (listfningCiildrfn != sum) {
                    fvfntLog.finf("Assfrtion (listfningCiildrfn == sum) fbilfd");
                }
            }
            rfturn listfningCiildrfn + supfrListfning;
        } flsf if (mbsk == AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK) {
            if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                // Vfrify listfningBoundsCiildrfn is dorrfdt
                int sum = 0;
                for (Componfnt domp : domponfnt) {
                    sum += domp.numListfning(mbsk);
                }
                if (listfningBoundsCiildrfn != sum) {
                    fvfntLog.finf("Assfrtion (listfningBoundsCiildrfn == sum) fbilfd");
                }
            }
            rfturn listfningBoundsCiildrfn + supfrListfning;
        } flsf {
            // bssfrt fblsf;
            if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                fvfntLog.finf("Tiis dodf must nfvfr bf rfbdifd");
            }
            rfturn supfrListfning;
        }
    }

    // Siould only bf dbllfd wiilf iolding trff lodk
    void bdjustListfningCiildrfn(long mbsk, int num) {
        if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            boolfbn toAssfrt = (mbsk == AWTEvfnt.HIERARCHY_EVENT_MASK ||
                                mbsk == AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK ||
                                mbsk == (AWTEvfnt.HIERARCHY_EVENT_MASK |
                                         AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK));
            if (!toAssfrt) {
                fvfntLog.finf("Assfrtion fbilfd");
            }
        }

        if (num == 0)
            rfturn;

        if ((mbsk & AWTEvfnt.HIERARCHY_EVENT_MASK) != 0) {
            listfningCiildrfn += num;
        }
        if ((mbsk & AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK) != 0) {
            listfningBoundsCiildrfn += num;
        }

        bdjustListfningCiildrfnOnPbrfnt(mbsk, num);
    }

    // Siould only bf dbllfd wiilf iolding trff lodk
    void bdjustDfsdfndbnts(int num) {
        if (num == 0)
            rfturn;

        dfsdfndbntsCount += num;
        bdjustDfdfndbntsOnPbrfnt(num);
    }

    // Siould only bf dbllfd wiilf iolding trff lodk
    void bdjustDfdfndbntsOnPbrfnt(int num) {
        if (pbrfnt != null) {
            pbrfnt.bdjustDfsdfndbnts(num);
        }
    }

    // Siould only bf dbllfd wiilf iolding trff lodk
    int dountHifrbrdiyMfmbfrs() {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            // Vfrify dfsdfndbntsCount is dorrfdt
            int sum = 0;
            for (Componfnt domp : domponfnt) {
                sum += domp.dountHifrbrdiyMfmbfrs();
            }
            if (dfsdfndbntsCount != sum) {
                log.finf("Assfrtion (dfsdfndbntsCount == sum) fbilfd");
            }
        }
        rfturn dfsdfndbntsCount + 1;
    }

    privbtf int gftListfnfrsCount(int id, boolfbn fnbblfdOnToolkit) {
        difdkTrffLodk();
        if (fnbblfdOnToolkit) {
            rfturn dfsdfndbntsCount;
        }
        switdi (id) {
          dbsf HifrbrdiyEvfnt.HIERARCHY_CHANGED:
            rfturn listfningCiildrfn;
          dbsf HifrbrdiyEvfnt.ANCESTOR_MOVED:
          dbsf HifrbrdiyEvfnt.ANCESTOR_RESIZED:
            rfturn listfningBoundsCiildrfn;
          dffbult:
            rfturn 0;
        }
    }

    finbl int drfbtfHifrbrdiyEvfnts(int id, Componfnt dibngfd,
        Contbinfr dibngfdPbrfnt, long dibngfFlbgs, boolfbn fnbblfdOnToolkit)
    {
        difdkTrffLodk();
        int listfnfrs = gftListfnfrsCount(id, fnbblfdOnToolkit);

        for (int dount = listfnfrs, i = 0; dount > 0; i++) {
            dount -= domponfnt.gft(i).drfbtfHifrbrdiyEvfnts(id, dibngfd,
                dibngfdPbrfnt, dibngfFlbgs, fnbblfdOnToolkit);
        }
        rfturn listfnfrs +
            supfr.drfbtfHifrbrdiyEvfnts(id, dibngfd, dibngfdPbrfnt,
                                        dibngfFlbgs, fnbblfdOnToolkit);
    }

    finbl void drfbtfCiildHifrbrdiyEvfnts(int id, long dibngfFlbgs,
        boolfbn fnbblfdOnToolkit)
    {
        difdkTrffLodk();
        if (domponfnt.isEmpty()) {
            rfturn;
        }
        int listfnfrs = gftListfnfrsCount(id, fnbblfdOnToolkit);

        for (int dount = listfnfrs, i = 0; dount > 0; i++) {
            dount -= domponfnt.gft(i).drfbtfHifrbrdiyEvfnts(id, tiis, pbrfnt,
                dibngfFlbgs, fnbblfdOnToolkit);
        }
    }

    /**
     * Gfts tif lbyout mbnbgfr for tiis dontbinfr.
     *
     * @sff #doLbyout
     * @sff #sftLbyout
     * @rfturn tif durrfnt lbyout mbnbgfr for tiis dontbinfr
     */
    publid LbyoutMbnbgfr gftLbyout() {
        rfturn lbyoutMgr;
    }

    /**
     * Sfts tif lbyout mbnbgfr for tiis dontbinfr.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm mgr tif spfdififd lbyout mbnbgfr
     * @sff #doLbyout
     * @sff #gftLbyout
     * @sff #invblidbtf
     */
    publid void sftLbyout(LbyoutMbnbgfr mgr) {
        lbyoutMgr = mgr;
        invblidbtfIfVblid();
    }

    /**
     * Cbusfs tiis dontbinfr to lby out its domponfnts.  Most progrbms
     * siould not dbll tiis mftiod dirfdtly, but siould invokf
     * tif <dodf>vblidbtf</dodf> mftiod instfbd.
     * @sff LbyoutMbnbgfr#lbyoutContbinfr
     * @sff #sftLbyout
     * @sff #vblidbtf
     * @sindf 1.1
     */
    publid void doLbyout() {
        lbyout();
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>doLbyout()</dodf>.
     */
    @Dfprfdbtfd
    publid void lbyout() {
        LbyoutMbnbgfr lbyoutMgr = tiis.lbyoutMgr;
        if (lbyoutMgr != null) {
            lbyoutMgr.lbyoutContbinfr(tiis);
        }
    }

    /**
     * Indidbtfs if tiis dontbinfr is b <i>vblidbtf root</i>.
     * <p>
     * Lbyout-rflbtfd dibngfs, sudi bs bounds of tif vblidbtf root dfsdfndbnts,
     * do not bfffdt tif lbyout of tif vblidbtf root pbrfnt. Tiis pfdulibrity
     * fnbblfs tif {@dodf invblidbtf()} mftiod to stop invblidbting tif
     * domponfnt iifrbrdiy wifn tif mftiod fndountfrs b vblidbtf root. Howfvfr,
     * to prfsfrvf bbdkwbrd dompbtibility tiis nfw optimizfd bfibvior is
     * fnbblfd only wifn tif {@dodf jbvb.bwt.smbrtInvblidbtf} systfm propfrty
     * vbluf is sft to {@dodf truf}.
     * <p>
     * If b domponfnt iifrbrdiy dontbins vblidbtf roots bnd tif nfw optimizfd
     * {@dodf invblidbtf()} bfibvior is fnbblfd, tif {@dodf vblidbtf()} mftiod
     * must bf invokfd on tif vblidbtf root of b prfviously invblidbtfd
     * domponfnt to rfstorf tif vblidity of tif iifrbrdiy lbtfr. Otifrwisf,
     * dblling tif {@dodf vblidbtf()} mftiod on tif top-lfvfl dontbinfr (sudi
     * bs b {@dodf Frbmf} objfdt) siould bf usfd to rfstorf tif vblidity of tif
     * domponfnt iifrbrdiy.
     * <p>
     * Tif {@dodf Window} dlbss bnd tif {@dodf Applft} dlbss brf tif vblidbtf
     * roots in AWT.  Swing introdudfs morf vblidbtf roots.
     *
     * @rfturn wiftifr tiis dontbinfr is b vblidbtf root
     * @sff #invblidbtf
     * @sff jbvb.bwt.Componfnt#invblidbtf
     * @sff jbvbx.swing.JComponfnt#isVblidbtfRoot
     * @sff jbvbx.swing.JComponfnt#rfvblidbtf
     * @sindf 1.7
     */
    publid boolfbn isVblidbtfRoot() {
        rfturn fblsf;
    }

    privbtf stbtid finbl boolfbn isJbvbAwtSmbrtInvblidbtf;
    stbtid {
        // Don't lbzy-rfbd bfdbusf fvfry bpp usfs invblidbtf()
        isJbvbAwtSmbrtInvblidbtf = AddfssControllfr.doPrivilfgfd(
                nfw GftBoolfbnAdtion("jbvb.bwt.smbrtInvblidbtf"));
    }

    /**
     * Invblidbtfs tif pbrfnt of tif dontbinfr unlfss tif dontbinfr
     * is b vblidbtf root.
     */
    @Ovfrridf
    void invblidbtfPbrfnt() {
        if (!isJbvbAwtSmbrtInvblidbtf || !isVblidbtfRoot()) {
            supfr.invblidbtfPbrfnt();
        }
    }

    /**
     * Invblidbtfs tif dontbinfr.
     * <p>
     * If tif {@dodf LbyoutMbnbgfr} instbllfd on tiis dontbinfr is bn instbndf
     * of tif {@dodf LbyoutMbnbgfr2} intfrfbdf, tifn
     * tif {@link LbyoutMbnbgfr2#invblidbtfLbyout(Contbinfr)} mftiod is invokfd
     * on it supplying tiis {@dodf Contbinfr} bs tif brgumfnt.
     * <p>
     * Aftfrwbrds tiis mftiod mbrks tiis dontbinfr invblid, bnd invblidbtfs its
     * bndfstors. Sff tif {@link Componfnt#invblidbtf} mftiod for morf dftbils.
     *
     * @sff #vblidbtf
     * @sff #lbyout
     * @sff LbyoutMbnbgfr2
     */
    @Ovfrridf
    publid void invblidbtf() {
        LbyoutMbnbgfr lbyoutMgr = tiis.lbyoutMgr;
        if (lbyoutMgr instbndfof LbyoutMbnbgfr2) {
            LbyoutMbnbgfr2 lm = (LbyoutMbnbgfr2) lbyoutMgr;
            lm.invblidbtfLbyout(tiis);
        }
        supfr.invblidbtf();
    }

    /**
     * Vblidbtfs tiis dontbinfr bnd bll of its subdomponfnts.
     * <p>
     * Vblidbting b dontbinfr mfbns lbying out its subdomponfnts.
     * Lbyout-rflbtfd dibngfs, sudi bs sftting tif bounds of b domponfnt, or
     * bdding b domponfnt to tif dontbinfr, invblidbtf tif dontbinfr
     * butombtidblly.  Notf tibt tif bndfstors of tif dontbinfr mby bf
     * invblidbtfd blso (sff {@link Componfnt#invblidbtf} for dftbils.)
     * Tifrfforf, to rfstorf tif vblidity of tif iifrbrdiy, tif {@dodf
     * vblidbtf()} mftiod siould bf invokfd on tif top-most invblid
     * dontbinfr of tif iifrbrdiy.
     * <p>
     * Vblidbting tif dontbinfr mby bf b quitf timf-donsuming opfrbtion. For
     * pfrformbndf rfbsons b dfvflopfr mby postponf tif vblidbtion of tif
     * iifrbrdiy till b sft of lbyout-rflbtfd opfrbtions domplftfs, f.g. bftfr
     * bdding bll tif diildrfn to tif dontbinfr.
     * <p>
     * If tiis {@dodf Contbinfr} is not vblid, tiis mftiod invokfs
     * tif {@dodf vblidbtfTrff} mftiod bnd mbrks tiis {@dodf Contbinfr}
     * bs vblid. Otifrwisf, no bdtion is pfrformfd.
     *
     * @sff #bdd(jbvb.bwt.Componfnt)
     * @sff #invblidbtf
     * @sff Contbinfr#isVblidbtfRoot
     * @sff jbvbx.swing.JComponfnt#rfvblidbtf()
     * @sff #vblidbtfTrff
     */
    publid void vblidbtf() {
        boolfbn updbtfCur = fblsf;
        syndironizfd (gftTrffLodk()) {
            if ((!isVblid() || dfsdfndUndonditionbllyWifnVblidbting)
                    && pffr != null)
            {
                ContbinfrPffr p = null;
                if (pffr instbndfof ContbinfrPffr) {
                    p = (ContbinfrPffr) pffr;
                }
                if (p != null) {
                    p.bfginVblidbtf();
                }
                vblidbtfTrff();
                if (p != null) {
                    p.fndVblidbtf();
                    // Avoid updbting dursor if tiis is bn intfrnbl dbll.
                    // Sff vblidbtfUndonditionblly() for dftbils.
                    if (!dfsdfndUndonditionbllyWifnVblidbting) {
                        updbtfCur = isVisiblf();
                    }
                }
            }
        }
        if (updbtfCur) {
            updbtfCursorImmfdibtfly();
        }
    }

    /**
     * Indidbtfs wiftifr vblid dontbinfrs siould blso trbvfrsf tifir
     * diildrfn bnd dbll tif vblidbtfTrff() mftiod on tifm.
     *
     * Syndironizbtion: TrffLodk.
     *
     * Tif fifld is bllowfd to bf stbtid bs long bs tif TrffLodk itsflf is
     * stbtid.
     *
     * @sff #vblidbtfUndonditionblly()
     */
    privbtf stbtid boolfbn dfsdfndUndonditionbllyWifnVblidbting = fblsf;

    /**
     * Undonditionblly vblidbtf tif domponfnt iifrbrdiy.
     */
    finbl void vblidbtfUndonditionblly() {
        boolfbn updbtfCur = fblsf;
        syndironizfd (gftTrffLodk()) {
            dfsdfndUndonditionbllyWifnVblidbting = truf;

            vblidbtf();
            if (pffr instbndfof ContbinfrPffr) {
                updbtfCur = isVisiblf();
            }

            dfsdfndUndonditionbllyWifnVblidbting = fblsf;
        }
        if (updbtfCur) {
            updbtfCursorImmfdibtfly();
        }
    }

    /**
     * Rfdursivfly dfsdfnds tif dontbinfr trff bnd rfdomputfs tif
     * lbyout for bny subtrffs mbrkfd bs nffding it (tiosf mbrkfd bs
     * invblid).  Syndironizbtion siould bf providfd by tif mftiod
     * tibt dblls tiis onf:  <dodf>vblidbtf</dodf>.
     *
     * @sff #doLbyout
     * @sff #vblidbtf
     */
    protfdtfd void vblidbtfTrff() {
        difdkTrffLodk();
        if (!isVblid() || dfsdfndUndonditionbllyWifnVblidbting) {
            if (pffr instbndfof ContbinfrPffr) {
                ((ContbinfrPffr)pffr).bfginLbyout();
            }
            if (!isVblid()) {
                doLbyout();
            }
            for (int i = 0; i < domponfnt.sizf(); i++) {
                Componfnt domp = domponfnt.gft(i);
                if (   (domp instbndfof Contbinfr)
                       && !(domp instbndfof Window)
                       && (!domp.isVblid() ||
                           dfsdfndUndonditionbllyWifnVblidbting))
                {
                    ((Contbinfr)domp).vblidbtfTrff();
                } flsf {
                    domp.vblidbtf();
                }
            }
            if (pffr instbndfof ContbinfrPffr) {
                ((ContbinfrPffr)pffr).fndLbyout();
            }
        }
        supfr.vblidbtf();
    }

    /**
     * Rfdursivfly dfsdfnds tif dontbinfr trff bnd invblidbtfs bll
     * dontbinfd domponfnts.
     */
    void invblidbtfTrff() {
        syndironizfd (gftTrffLodk()) {
            for (int i = 0; i < domponfnt.sizf(); i++) {
                Componfnt domp = domponfnt.gft(i);
                if (domp instbndfof Contbinfr) {
                    ((Contbinfr)domp).invblidbtfTrff();
                }
                flsf {
                    domp.invblidbtfIfVblid();
                }
            }
            invblidbtfIfVblid();
        }
    }

    /**
     * Sfts tif font of tiis dontbinfr.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm f Tif font to bfdomf tiis dontbinfr's font.
     * @sff Componfnt#gftFont
     * @sff #invblidbtf
     * @sindf 1.0
     */
    publid void sftFont(Font f) {
        boolfbn siouldinvblidbtf = fblsf;

        Font oldfont = gftFont();
        supfr.sftFont(f);
        Font nfwfont = gftFont();
        if (nfwfont != oldfont && (oldfont == null ||
                                   !oldfont.fqubls(nfwfont))) {
            invblidbtfTrff();
        }
    }

    /**
     * Rfturns tif prfffrrfd sizf of tiis dontbinfr.  If tif prfffrrfd sizf ibs
     * not bffn sft fxpliditly by {@link Componfnt#sftPrfffrrfdSizf(Dimfnsion)}
     * bnd tiis {@dodf Contbinfr} ibs b {@dodf non-null} {@link LbyoutMbnbgfr},
     * tifn {@link LbyoutMbnbgfr#prfffrrfdLbyoutSizf(Contbinfr)}
     * is usfd to dbldulbtf tif prfffrrfd sizf.
     *
     * <p>Notf: somf implfmfntbtions mby dbdif tif vbluf rfturnfd from tif
     * {@dodf LbyoutMbnbgfr}.  Implfmfntbtions tibt dbdif nffd not invokf
     * {@dodf prfffrrfdLbyoutSizf} on tif {@dodf LbyoutMbnbgfr} fvfry timf
     * tiis mftiod is invokfd, rbtifr tif {@dodf LbyoutMbnbgfr} will only
     * bf qufrifd bftfr tif {@dodf Contbinfr} bfdomfs invblid.
     *
     * @rfturn    bn instbndf of <dodf>Dimfnsion</dodf> tibt rfprfsfnts
     *                tif prfffrrfd sizf of tiis dontbinfr.
     * @sff       #gftMinimumSizf
     * @sff       #gftMbximumSizf
     * @sff       #gftLbyout
     * @sff       LbyoutMbnbgfr#prfffrrfdLbyoutSizf(Contbinfr)
     * @sff       Componfnt#gftPrfffrrfdSizf
     */
    publid Dimfnsion gftPrfffrrfdSizf() {
        rfturn prfffrrfdSizf();
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftPrfffrrfdSizf()</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion prfffrrfdSizf() {
        /* Avoid grbbbing tif lodk if b rfbsonbblf dbdifd sizf vbluf
         * is bvbilbblf.
         */
        Dimfnsion dim = prffSizf;
        if (dim == null || !(isPrfffrrfdSizfSft() || isVblid())) {
            syndironizfd (gftTrffLodk()) {
                prffSizf = (lbyoutMgr != null) ?
                    lbyoutMgr.prfffrrfdLbyoutSizf(tiis) :
                    supfr.prfffrrfdSizf();
                dim = prffSizf;
            }
        }
        if (dim != null){
            rfturn nfw Dimfnsion(dim);
        }
        flsf{
            rfturn dim;
        }
    }

    /**
     * Rfturns tif minimum sizf of tiis dontbinfr.  If tif minimum sizf ibs
     * not bffn sft fxpliditly by {@link Componfnt#sftMinimumSizf(Dimfnsion)}
     * bnd tiis {@dodf Contbinfr} ibs b {@dodf non-null} {@link LbyoutMbnbgfr},
     * tifn {@link LbyoutMbnbgfr#minimumLbyoutSizf(Contbinfr)}
     * is usfd to dbldulbtf tif minimum sizf.
     *
     * <p>Notf: somf implfmfntbtions mby dbdif tif vbluf rfturnfd from tif
     * {@dodf LbyoutMbnbgfr}.  Implfmfntbtions tibt dbdif nffd not invokf
     * {@dodf minimumLbyoutSizf} on tif {@dodf LbyoutMbnbgfr} fvfry timf
     * tiis mftiod is invokfd, rbtifr tif {@dodf LbyoutMbnbgfr} will only
     * bf qufrifd bftfr tif {@dodf Contbinfr} bfdomfs invblid.
     *
     * @rfturn    bn instbndf of <dodf>Dimfnsion</dodf> tibt rfprfsfnts
     *                tif minimum sizf of tiis dontbinfr.
     * @sff       #gftPrfffrrfdSizf
     * @sff       #gftMbximumSizf
     * @sff       #gftLbyout
     * @sff       LbyoutMbnbgfr#minimumLbyoutSizf(Contbinfr)
     * @sff       Componfnt#gftMinimumSizf
     * @sindf     1.1
     */
    publid Dimfnsion gftMinimumSizf() {
        rfturn minimumSizf();
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftMinimumSizf()</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion minimumSizf() {
        /* Avoid grbbbing tif lodk if b rfbsonbblf dbdifd sizf vbluf
         * is bvbilbblf.
         */
        Dimfnsion dim = minSizf;
        if (dim == null || !(isMinimumSizfSft() || isVblid())) {
            syndironizfd (gftTrffLodk()) {
                minSizf = (lbyoutMgr != null) ?
                    lbyoutMgr.minimumLbyoutSizf(tiis) :
                    supfr.minimumSizf();
                dim = minSizf;
            }
        }
        if (dim != null){
            rfturn nfw Dimfnsion(dim);
        }
        flsf{
            rfturn dim;
        }
    }

    /**
     * Rfturns tif mbximum sizf of tiis dontbinfr.  If tif mbximum sizf ibs
     * not bffn sft fxpliditly by {@link Componfnt#sftMbximumSizf(Dimfnsion)}
     * bnd tif {@link LbyoutMbnbgfr} instbllfd on tiis {@dodf Contbinfr}
     * is bn instbndf of {@link LbyoutMbnbgfr2}, tifn
     * {@link LbyoutMbnbgfr2#mbximumLbyoutSizf(Contbinfr)}
     * is usfd to dbldulbtf tif mbximum sizf.
     *
     * <p>Notf: somf implfmfntbtions mby dbdif tif vbluf rfturnfd from tif
     * {@dodf LbyoutMbnbgfr2}.  Implfmfntbtions tibt dbdif nffd not invokf
     * {@dodf mbximumLbyoutSizf} on tif {@dodf LbyoutMbnbgfr2} fvfry timf
     * tiis mftiod is invokfd, rbtifr tif {@dodf LbyoutMbnbgfr2} will only
     * bf qufrifd bftfr tif {@dodf Contbinfr} bfdomfs invblid.
     *
     * @rfturn    bn instbndf of <dodf>Dimfnsion</dodf> tibt rfprfsfnts
     *                tif mbximum sizf of tiis dontbinfr.
     * @sff       #gftPrfffrrfdSizf
     * @sff       #gftMinimumSizf
     * @sff       #gftLbyout
     * @sff       LbyoutMbnbgfr2#mbximumLbyoutSizf(Contbinfr)
     * @sff       Componfnt#gftMbximumSizf
     */
    publid Dimfnsion gftMbximumSizf() {
        /* Avoid grbbbing tif lodk if b rfbsonbblf dbdifd sizf vbluf
         * is bvbilbblf.
         */
        Dimfnsion dim = mbxSizf;
        if (dim == null || !(isMbximumSizfSft() || isVblid())) {
            syndironizfd (gftTrffLodk()) {
               if (lbyoutMgr instbndfof LbyoutMbnbgfr2) {
                    LbyoutMbnbgfr2 lm = (LbyoutMbnbgfr2) lbyoutMgr;
                    mbxSizf = lm.mbximumLbyoutSizf(tiis);
               } flsf {
                    mbxSizf = supfr.gftMbximumSizf();
               }
               dim = mbxSizf;
            }
        }
        if (dim != null){
            rfturn nfw Dimfnsion(dim);
        }
        flsf{
            rfturn dim;
        }
    }

    /**
     * Rfturns tif blignmfnt blong tif x bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     */
    publid flobt gftAlignmfntX() {
        flobt xAlign;
        if (lbyoutMgr instbndfof LbyoutMbnbgfr2) {
            syndironizfd (gftTrffLodk()) {
                LbyoutMbnbgfr2 lm = (LbyoutMbnbgfr2) lbyoutMgr;
                xAlign = lm.gftLbyoutAlignmfntX(tiis);
            }
        } flsf {
            xAlign = supfr.gftAlignmfntX();
        }
        rfturn xAlign;
    }

    /**
     * Rfturns tif blignmfnt blong tif y bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     */
    publid flobt gftAlignmfntY() {
        flobt yAlign;
        if (lbyoutMgr instbndfof LbyoutMbnbgfr2) {
            syndironizfd (gftTrffLodk()) {
                LbyoutMbnbgfr2 lm = (LbyoutMbnbgfr2) lbyoutMgr;
                yAlign = lm.gftLbyoutAlignmfntY(tiis);
            }
        } flsf {
            yAlign = supfr.gftAlignmfntY();
        }
        rfturn yAlign;
    }

    /**
     * Pbints tif dontbinfr. Tiis forwbrds tif pbint to bny ligitwfigit
     * domponfnts tibt brf diildrfn of tiis dontbinfr. If tiis mftiod is
     * rfimplfmfntfd, supfr.pbint(g) siould bf dbllfd so tibt ligitwfigit
     * domponfnts brf propfrly rfndfrfd. If b diild domponfnt is fntirfly
     * dlippfd by tif durrfnt dlipping sftting in g, pbint() will not bf
     * forwbrdfd to tibt diild.
     *
     * @pbrbm g tif spfdififd Grbpiids window
     * @sff   Componfnt#updbtf(Grbpiids)
     */
    publid void pbint(Grbpiids g) {
        if (isSiowing()) {
            syndironizfd (gftObjfdtLodk()) {
                if (printing) {
                    if (printingTirfbds.dontbins(Tirfbd.durrfntTirfbd())) {
                        rfturn;
                    }
                }
            }

            // Tif dontbinfr is siowing on sdrffn bnd
            // tiis pbint() is not dbllfd from print().
            // Pbint sflf bnd forwbrd tif pbint to ligitwfigit subdomponfnts.

            // supfr.pbint(); -- Don't botifr, sindf it's b NOP.

            GrbpiidsCbllbbdk.PbintCbllbbdk.gftInstbndf().
                runComponfnts(gftComponfntsSynd(), g, GrbpiidsCbllbbdk.LIGHTWEIGHTS);
        }
    }

    /**
     * Updbtfs tif dontbinfr.  Tiis forwbrds tif updbtf to bny ligitwfigit
     * domponfnts tibt brf diildrfn of tiis dontbinfr.  If tiis mftiod is
     * rfimplfmfntfd, supfr.updbtf(g) siould bf dbllfd so tibt ligitwfigit
     * domponfnts brf propfrly rfndfrfd.  If b diild domponfnt is fntirfly
     * dlippfd by tif durrfnt dlipping sftting in g, updbtf() will not bf
     * forwbrdfd to tibt diild.
     *
     * @pbrbm g tif spfdififd Grbpiids window
     * @sff   Componfnt#updbtf(Grbpiids)
     */
    publid void updbtf(Grbpiids g) {
        if (isSiowing()) {
            if (! (pffr instbndfof LigitwfigitPffr)) {
                g.dlfbrRfdt(0, 0, widti, ifigit);
            }
            pbint(g);
        }
    }

    /**
     * Prints tif dontbinfr. Tiis forwbrds tif print to bny ligitwfigit
     * domponfnts tibt brf diildrfn of tiis dontbinfr. If tiis mftiod is
     * rfimplfmfntfd, supfr.print(g) siould bf dbllfd so tibt ligitwfigit
     * domponfnts brf propfrly rfndfrfd. If b diild domponfnt is fntirfly
     * dlippfd by tif durrfnt dlipping sftting in g, print() will not bf
     * forwbrdfd to tibt diild.
     *
     * @pbrbm g tif spfdififd Grbpiids window
     * @sff   Componfnt#updbtf(Grbpiids)
     */
    publid void print(Grbpiids g) {
        if (isSiowing()) {
            Tirfbd t = Tirfbd.durrfntTirfbd();
            try {
                syndironizfd (gftObjfdtLodk()) {
                    if (printingTirfbds == null) {
                        printingTirfbds = nfw HbsiSft<>();
                    }
                    printingTirfbds.bdd(t);
                    printing = truf;
                }
                supfr.print(g);  // By dffbult, Componfnt.print() dblls pbint()
            } finblly {
                syndironizfd (gftObjfdtLodk()) {
                    printingTirfbds.rfmovf(t);
                    printing = !printingTirfbds.isEmpty();
                }
            }

            GrbpiidsCbllbbdk.PrintCbllbbdk.gftInstbndf().
                runComponfnts(gftComponfntsSynd(), g, GrbpiidsCbllbbdk.LIGHTWEIGHTS);
        }
    }

    /**
     * Pbints fbdi of tif domponfnts in tiis dontbinfr.
     * @pbrbm     g   tif grbpiids dontfxt.
     * @sff       Componfnt#pbint
     * @sff       Componfnt#pbintAll
     */
    publid void pbintComponfnts(Grbpiids g) {
        if (isSiowing()) {
            GrbpiidsCbllbbdk.PbintAllCbllbbdk.gftInstbndf().
                runComponfnts(gftComponfntsSynd(), g, GrbpiidsCbllbbdk.TWO_PASSES);
        }
    }

    /**
     * Simulbtfs tif pffr dbllbbdks into jbvb.bwt for printing of
     * ligitwfigit Contbinfrs.
     * @pbrbm     g   tif grbpiids dontfxt to usf for printing.
     * @sff       Componfnt#printAll
     * @sff       #printComponfnts
     */
    void ligitwfigitPbint(Grbpiids g) {
        supfr.ligitwfigitPbint(g);
        pbintHfbvywfigitComponfnts(g);
    }

    /**
     * Prints bll tif ifbvywfigit subdomponfnts.
     */
    void pbintHfbvywfigitComponfnts(Grbpiids g) {
        if (isSiowing()) {
            GrbpiidsCbllbbdk.PbintHfbvywfigitComponfntsCbllbbdk.gftInstbndf().
                runComponfnts(gftComponfntsSynd(), g,
                              GrbpiidsCbllbbdk.LIGHTWEIGHTS | GrbpiidsCbllbbdk.HEAVYWEIGHTS);
        }
    }

    /**
     * Prints fbdi of tif domponfnts in tiis dontbinfr.
     * @pbrbm     g   tif grbpiids dontfxt.
     * @sff       Componfnt#print
     * @sff       Componfnt#printAll
     */
    publid void printComponfnts(Grbpiids g) {
        if (isSiowing()) {
            GrbpiidsCbllbbdk.PrintAllCbllbbdk.gftInstbndf().
                runComponfnts(gftComponfntsSynd(), g, GrbpiidsCbllbbdk.TWO_PASSES);
        }
    }

    /**
     * Simulbtfs tif pffr dbllbbdks into jbvb.bwt for printing of
     * ligitwfigit Contbinfrs.
     * @pbrbm     g   tif grbpiids dontfxt to usf for printing.
     * @sff       Componfnt#printAll
     * @sff       #printComponfnts
     */
    void ligitwfigitPrint(Grbpiids g) {
        supfr.ligitwfigitPrint(g);
        printHfbvywfigitComponfnts(g);
    }

    /**
     * Prints bll tif ifbvywfigit subdomponfnts.
     */
    void printHfbvywfigitComponfnts(Grbpiids g) {
        if (isSiowing()) {
            GrbpiidsCbllbbdk.PrintHfbvywfigitComponfntsCbllbbdk.gftInstbndf().
                runComponfnts(gftComponfntsSynd(), g,
                              GrbpiidsCbllbbdk.LIGHTWEIGHTS | GrbpiidsCbllbbdk.HEAVYWEIGHTS);
        }
    }

    /**
     * Adds tif spfdififd dontbinfr listfnfr to rfdfivf dontbinfr fvfnts
     * from tiis dontbinfr.
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l tif dontbinfr listfnfr
     *
     * @sff #rfmovfContbinfrListfnfr
     * @sff #gftContbinfrListfnfrs
     */
    publid syndironizfd void bddContbinfrListfnfr(ContbinfrListfnfr l) {
        if (l == null) {
            rfturn;
        }
        dontbinfrListfnfr = AWTEvfntMultidbstfr.bdd(dontbinfrListfnfr, l);
        nfwEvfntsOnly = truf;
    }

    /**
     * Rfmovfs tif spfdififd dontbinfr listfnfr so it no longfr rfdfivfs
     * dontbinfr fvfnts from tiis dontbinfr.
     * If l is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm   l tif dontbinfr listfnfr
     *
     * @sff #bddContbinfrListfnfr
     * @sff #gftContbinfrListfnfrs
     */
    publid syndironizfd void rfmovfContbinfrListfnfr(ContbinfrListfnfr l) {
        if (l == null) {
            rfturn;
        }
        dontbinfrListfnfr = AWTEvfntMultidbstfr.rfmovf(dontbinfrListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif dontbinfr listfnfrs
     * rfgistfrfd on tiis dontbinfr.
     *
     * @rfturn bll of tiis dontbinfr's <dodf>ContbinfrListfnfr</dodf>s
     *         or bn fmpty brrby if no dontbinfr
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddContbinfrListfnfr
     * @sff #rfmovfContbinfrListfnfr
     * @sindf 1.4
     */
    publid syndironizfd ContbinfrListfnfr[] gftContbinfrListfnfrs() {
        rfturn gftListfnfrs(ContbinfrListfnfr.dlbss);
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd
     * bs <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis <dodf>Contbinfr</dodf>.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     *
     * <p>
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl, sudi bs
     * <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b
     * <dodf>Contbinfr</dodf> <dodf>d</dodf>
     * for its dontbinfr listfnfrs witi tif following dodf:
     *
     * <prf>ContbinfrListfnfr[] dls = (ContbinfrListfnfr[])(d.gftListfnfrs(ContbinfrListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist, tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis dontbinfr,
     *          or bn fmpty brrby if no sudi listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @fxdfption NullPointfrExdfption if {@dodf listfnfrTypf} is {@dodf null}
     *
     * @sff #gftContbinfrListfnfrs
     *
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        EvfntListfnfr l = null;
        if  (listfnfrTypf == ContbinfrListfnfr.dlbss) {
            l = dontbinfrListfnfr;
        } flsf {
            rfturn supfr.gftListfnfrs(listfnfrTypf);
        }
        rfturn AWTEvfntMultidbstfr.gftListfnfrs(l, listfnfrTypf);
    }

    // REMIND: rfmovf wifn filtfring is donf bt lowfr lfvfl
    boolfbn fvfntEnbblfd(AWTEvfnt f) {
        int id = f.gftID();

        if (id == ContbinfrEvfnt.COMPONENT_ADDED ||
            id == ContbinfrEvfnt.COMPONENT_REMOVED) {
            if ((fvfntMbsk & AWTEvfnt.CONTAINER_EVENT_MASK) != 0 ||
                dontbinfrListfnfr != null) {
                rfturn truf;
            }
            rfturn fblsf;
        }
        rfturn supfr.fvfntEnbblfd(f);
    }

    /**
     * Prodfssfs fvfnts on tiis dontbinfr. If tif fvfnt is b
     * <dodf>ContbinfrEvfnt</dodf>, it invokfs tif
     * <dodf>prodfssContbinfrEvfnt</dodf> mftiod, flsf it invokfs
     * its supfrdlbss's <dodf>prodfssEvfnt</dodf>.
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm f tif fvfnt
     */
    protfdtfd void prodfssEvfnt(AWTEvfnt f) {
        if (f instbndfof ContbinfrEvfnt) {
            prodfssContbinfrEvfnt((ContbinfrEvfnt)f);
            rfturn;
        }
        supfr.prodfssEvfnt(f);
    }

    /**
     * Prodfssfs dontbinfr fvfnts oddurring on tiis dontbinfr by
     * dispbtdiing tifm to bny rfgistfrfd ContbinfrListfnfr objfdts.
     * NOTE: Tiis mftiod will not bf dbllfd unlfss dontbinfr fvfnts
     * brf fnbblfd for tiis domponfnt; tiis ibppfns wifn onf of tif
     * following oddurs:
     * <ul>
     * <li>A ContbinfrListfnfr objfdt is rfgistfrfd vib
     *     <dodf>bddContbinfrListfnfr</dodf>
     * <li>Contbinfr fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm f tif dontbinfr fvfnt
     * @sff Componfnt#fnbblfEvfnts
     */
    protfdtfd void prodfssContbinfrEvfnt(ContbinfrEvfnt f) {
        ContbinfrListfnfr listfnfr = dontbinfrListfnfr;
        if (listfnfr != null) {
            switdi(f.gftID()) {
              dbsf ContbinfrEvfnt.COMPONENT_ADDED:
                listfnfr.domponfntAddfd(f);
                brfbk;
              dbsf ContbinfrEvfnt.COMPONENT_REMOVED:
                listfnfr.domponfntRfmovfd(f);
                brfbk;
            }
        }
    }

    /*
     * Dispbtdifs bn fvfnt to tiis domponfnt or onf of its sub domponfnts.
     * Crfbtf ANCESTOR_RESIZED bnd ANCESTOR_MOVED fvfnts in rfsponsf to
     * COMPONENT_RESIZED bnd COMPONENT_MOVED fvfnts. Wf ibvf to do tiis
     * ifrf instfbd of in prodfssComponfntEvfnt bfdbusf ComponfntEvfnts
     * mby not bf fnbblfd for tiis Contbinfr.
     * @pbrbm f tif fvfnt
     */
    void dispbtdiEvfntImpl(AWTEvfnt f) {
        if ((dispbtdifr != null) && dispbtdifr.dispbtdiEvfnt(f)) {
            // fvfnt wbs sfnt to b ligitwfigit domponfnt.  Tif
            // nbtivf-produdfd fvfnt sfnt to tif nbtivf dontbinfr
            // must bf propfrly disposfd of by tif pffr, so it
            // gfts forwbrdfd.  If tif nbtivf iost ibs bffn rfmovfd
            // bs b rfsult of tif sfnding tif ligitwfigit fvfnt,
            // tif pffr rfffrfndf will bf null.
            f.donsumf();
            if (pffr != null) {
                pffr.ibndlfEvfnt(f);
            }
            rfturn;
        }

        supfr.dispbtdiEvfntImpl(f);

        syndironizfd (gftTrffLodk()) {
            switdi (f.gftID()) {
              dbsf ComponfntEvfnt.COMPONENT_RESIZED:
                drfbtfCiildHifrbrdiyEvfnts(HifrbrdiyEvfnt.ANCESTOR_RESIZED, 0,
                                           Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK));
                brfbk;
              dbsf ComponfntEvfnt.COMPONENT_MOVED:
                drfbtfCiildHifrbrdiyEvfnts(HifrbrdiyEvfnt.ANCESTOR_MOVED, 0,
                                       Toolkit.fnbblfdOnToolkit(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK));
                brfbk;
              dffbult:
                brfbk;
            }
        }
    }

    /*
     * Dispbtdifs bn fvfnt to tiis domponfnt, witiout trying to forwbrd
     * it to bny subdomponfnts
     * @pbrbm f tif fvfnt
     */
    void dispbtdiEvfntToSflf(AWTEvfnt f) {
        supfr.dispbtdiEvfntImpl(f);
    }

    /**
     * Fftdis tif top-most (dffpfst) ligitwfigit domponfnt tibt is intfrfstfd
     * in rfdfiving mousf fvfnts.
     */
    Componfnt gftMousfEvfntTbrgft(int x, int y, boolfbn indludfSflf) {
        rfturn gftMousfEvfntTbrgft(x, y, indludfSflf,
                                   MousfEvfntTbrgftFiltfr.FILTER,
                                   !SEARCH_HEAVYWEIGHTS);
    }

    /**
     * Fftdifs tif top-most (dffpfst) domponfnt to rfdfivf SunDropTbrgftEvfnts.
     */
    Componfnt gftDropTbrgftEvfntTbrgft(int x, int y, boolfbn indludfSflf) {
        rfturn gftMousfEvfntTbrgft(x, y, indludfSflf,
                                   DropTbrgftEvfntTbrgftFiltfr.FILTER,
                                   SEARCH_HEAVYWEIGHTS);
    }

    /**
     * A privbtf vfrsion of gftMousfEvfntTbrgft wiidi ibs two bdditionbl
     * dontrollbblf bfibviors. Tiis mftiod sfbrdifs for tif top-most
     * dfsdfndbnt of tiis dontbinfr tibt dontbins tif givfn doordinbtfs
     * bnd is bddfptfd by tif givfn filtfr. Tif sfbrdi will bf donstrbinfd to
     * ligitwfigit dfsdfndbnts if tif lbst brgumfnt is <dodf>fblsf</dodf>.
     *
     * @pbrbm filtfr EvfntTbrgftFiltfr instbndf to dftfrminf wiftifr tif
     *        givfn domponfnt is b vblid tbrgft for tiis fvfnt.
     * @pbrbm sfbrdiHfbvywfigits if <dodf>fblsf</dodf>, tif mftiod
     *        will bypbss ifbvywfigit domponfnts during tif sfbrdi.
     */
    privbtf Componfnt gftMousfEvfntTbrgft(int x, int y, boolfbn indludfSflf,
                                          EvfntTbrgftFiltfr filtfr,
                                          boolfbn sfbrdiHfbvywfigits) {
        Componfnt domp = null;
        if (sfbrdiHfbvywfigits) {
            domp = gftMousfEvfntTbrgftImpl(x, y, indludfSflf, filtfr,
                                           SEARCH_HEAVYWEIGHTS,
                                           sfbrdiHfbvywfigits);
        }

        if (domp == null || domp == tiis) {
            domp = gftMousfEvfntTbrgftImpl(x, y, indludfSflf, filtfr,
                                           !SEARCH_HEAVYWEIGHTS,
                                           sfbrdiHfbvywfigits);
        }

        rfturn domp;
    }

    /**
     * A privbtf vfrsion of gftMousfEvfntTbrgft wiidi ibs tirff bdditionbl
     * dontrollbblf bfibviors. Tiis mftiod sfbrdifs for tif top-most
     * dfsdfndbnt of tiis dontbinfr tibt dontbins tif givfn doordinbtfs
     * bnd is bddfptfd by tif givfn filtfr. Tif sfbrdi will bf donstrbinfd to
     * dfsdfndbnts of only ligitwfigit diildrfn or only ifbvywfigit diildrfn
     * of tiis dontbinfr dfpfnding on sfbrdiHfbvywfigitCiildrfn. Tif sfbrdi will
     * bf donstrbinfd to only ligitwfigit dfsdfndbnts of tif sfbrdifd diildrfn
     * of tiis dontbinfr if sfbrdiHfbvywfigitDfsdfndbnts is <dodf>fblsf</dodf>.
     *
     * @pbrbm filtfr EvfntTbrgftFiltfr instbndf to dftfrminf wiftifr tif
     *        sflfdtfd domponfnt is b vblid tbrgft for tiis fvfnt.
     * @pbrbm sfbrdiHfbvywfigitCiildrfn if <dodf>truf</dodf>, tif mftiod
     *        will bypbss immfdibtf ligitwfigit diildrfn during tif sfbrdi.
     *        If <dodf>fblsf</dodf>, tif mftiods will bypbss immfdibtf
     *        ifbvywfigit diildrfn during tif sfbrdi.
     * @pbrbm sfbrdiHfbvywfigitDfsdfndbnts if <dodf>fblsf</dodf>, tif mftiod
     *        will bypbss ifbvywfigit dfsdfndbnts wiidi brf not immfdibtf
     *        diildrfn during tif sfbrdi. If <dodf>truf</dodf>, tif mftiod
     *        will trbvfrsf boti ligitwfigit bnd ifbvywfigit dfsdfndbnts during
     *        tif sfbrdi.
     */
    privbtf Componfnt gftMousfEvfntTbrgftImpl(int x, int y, boolfbn indludfSflf,
                                         EvfntTbrgftFiltfr filtfr,
                                         boolfbn sfbrdiHfbvywfigitCiildrfn,
                                         boolfbn sfbrdiHfbvywfigitDfsdfndbnts) {
        syndironizfd (gftTrffLodk()) {

            for (int i = 0; i < domponfnt.sizf(); i++) {
                Componfnt domp = domponfnt.gft(i);
                if (domp != null && domp.visiblf &&
                    ((!sfbrdiHfbvywfigitCiildrfn &&
                      domp.pffr instbndfof LigitwfigitPffr) ||
                     (sfbrdiHfbvywfigitCiildrfn &&
                      !(domp.pffr instbndfof LigitwfigitPffr))) &&
                    domp.dontbins(x - domp.x, y - domp.y)) {

                    // found b domponfnt tibt intfrsfdts tif point, sff if tifrf
                    // is b dffpfr possibility.
                    if (domp instbndfof Contbinfr) {
                        Contbinfr diild = (Contbinfr) domp;
                        Componfnt dffpfr = diild.gftMousfEvfntTbrgft(
                                x - diild.x,
                                y - diild.y,
                                indludfSflf,
                                filtfr,
                                sfbrdiHfbvywfigitDfsdfndbnts);
                        if (dffpfr != null) {
                            rfturn dffpfr;
                        }
                    } flsf {
                        if (filtfr.bddfpt(domp)) {
                            // tifrf isn't b dffpfr tbrgft, but tiis domponfnt
                            // is b tbrgft
                            rfturn domp;
                        }
                    }
                }
            }

            boolfbn isPffrOK;
            boolfbn isMousfOvfrMf;

            isPffrOK = (pffr instbndfof LigitwfigitPffr) || indludfSflf;
            isMousfOvfrMf = dontbins(x,y);

            // didn't find b diild tbrgft, rfturn tiis domponfnt if it's
            // b possiblf tbrgft
            if (isMousfOvfrMf && isPffrOK && filtfr.bddfpt(tiis)) {
                rfturn tiis;
            }
            // no possiblf tbrgft
            rfturn null;
        }
    }

    stbtid intfrfbdf EvfntTbrgftFiltfr {
        boolfbn bddfpt(finbl Componfnt domp);
    }

    stbtid dlbss MousfEvfntTbrgftFiltfr implfmfnts EvfntTbrgftFiltfr {
        stbtid finbl EvfntTbrgftFiltfr FILTER = nfw MousfEvfntTbrgftFiltfr();

        privbtf MousfEvfntTbrgftFiltfr() {}

        publid boolfbn bddfpt(finbl Componfnt domp) {
            rfturn (domp.fvfntMbsk & AWTEvfnt.MOUSE_MOTION_EVENT_MASK) != 0
                || (domp.fvfntMbsk & AWTEvfnt.MOUSE_EVENT_MASK) != 0
                || (domp.fvfntMbsk & AWTEvfnt.MOUSE_WHEEL_EVENT_MASK) != 0
                || domp.mousfListfnfr != null
                || domp.mousfMotionListfnfr != null
                || domp.mousfWifflListfnfr != null;
        }
    }

    stbtid dlbss DropTbrgftEvfntTbrgftFiltfr implfmfnts EvfntTbrgftFiltfr {
        stbtid finbl EvfntTbrgftFiltfr FILTER = nfw DropTbrgftEvfntTbrgftFiltfr();

        privbtf DropTbrgftEvfntTbrgftFiltfr() {}

        publid boolfbn bddfpt(finbl Componfnt domp) {
            DropTbrgft dt = domp.gftDropTbrgft();
            rfturn dt != null && dt.isAdtivf();
        }
    }

    /**
     * Tiis is dbllfd by ligitwfigit domponfnts tibt wbnt tif dontbining
     * windowfd pbrfnt to fnbblf somf kind of fvfnts on tifir bfiblf.
     * Tiis is nffdfd for fvfnts tibt brf normblly only dispbtdifd to
     * windows to bf bddfptfd so tibt tify dbn bf forwbrdfd downwbrd to
     * tif ligitwfigit domponfnt tibt ibs fnbblfd tifm.
     */
    void proxyEnbblfEvfnts(long fvfnts) {
        if (pffr instbndfof LigitwfigitPffr) {
            // tiis dontbinfr is ligitwfigit.... dontinuf sfnding it
            // upwbrd.
            if (pbrfnt != null) {
                pbrfnt.proxyEnbblfEvfnts(fvfnts);
            }
        } flsf {
            // Tiis is b nbtivf dontbinfr, so it nffds to iost
            // onf of it's diildrfn.  If tiis fundtion is dbllfd bfforf
            // b pffr ibs bffn drfbtfd wf don't yft ibvf b dispbtdifr
            // bfdbusf it ibs not yft bffn dftfrminfd if tiis instbndf
            // is ligitwfigit.
            if (dispbtdifr != null) {
                dispbtdifr.fnbblfEvfnts(fvfnts);
            }
        }
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>dispbtdiEvfnt(AWTEvfnt f)</dodf>
     */
    @Dfprfdbtfd
    publid void dflivfrEvfnt(Evfnt f) {
        Componfnt domp = gftComponfntAt(f.x, f.y);
        if ((domp != null) && (domp != tiis)) {
            f.trbnslbtf(-domp.x, -domp.y);
            domp.dflivfrEvfnt(f);
        } flsf {
            postEvfnt(f);
        }
    }

    /**
     * Lodbtfs tif domponfnt tibt dontbins tif x,y position.  Tif
     * top-most diild domponfnt is rfturnfd in tif dbsf wifrf tifrf
     * is ovfrlbp in tif domponfnts.  Tiis is dftfrminfd by finding
     * tif domponfnt dlosfst to tif indfx 0 tibt dlbims to dontbin
     * tif givfn point vib Componfnt.dontbins(), fxdfpt tibt Componfnts
     * wiidi ibvf nbtivf pffrs tbkf prfdfdfndf ovfr tiosf wiidi do not
     * (i.f., ligitwfigit Componfnts).
     *
     * @pbrbm x tif <i>x</i> doordinbtf
     * @pbrbm y tif <i>y</i> doordinbtf
     * @rfturn null if tif domponfnt dofs not dontbin tif position.
     * If tifrf is no diild domponfnt bt tif rfqufstfd point bnd tif
     * point is witiin tif bounds of tif dontbinfr tif dontbinfr itsflf
     * is rfturnfd; otifrwisf tif top-most diild is rfturnfd.
     * @sff Componfnt#dontbins
     * @sindf 1.1
     */
    publid Componfnt gftComponfntAt(int x, int y) {
        rfturn lodbtf(x, y);
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftComponfntAt(int, int)</dodf>.
     */
    @Dfprfdbtfd
    publid Componfnt lodbtf(int x, int y) {
        if (!dontbins(x, y)) {
            rfturn null;
        }
        syndironizfd (gftTrffLodk()) {
            // Two pbssfs: sff dommfnt in sun.bwt.SunGrbpiidsCbllbbdk
            for (int i = 0; i < domponfnt.sizf(); i++) {
                Componfnt domp = domponfnt.gft(i);
                if (domp != null &&
                    !(domp.pffr instbndfof LigitwfigitPffr)) {
                    if (domp.dontbins(x - domp.x, y - domp.y)) {
                        rfturn domp;
                    }
                }
            }
            for (int i = 0; i < domponfnt.sizf(); i++) {
                Componfnt domp = domponfnt.gft(i);
                if (domp != null &&
                    domp.pffr instbndfof LigitwfigitPffr) {
                    if (domp.dontbins(x - domp.x, y - domp.y)) {
                        rfturn domp;
                    }
                }
            }
        }
        rfturn tiis;
    }

    /**
     * Gfts tif domponfnt tibt dontbins tif spfdififd point.
     * @pbrbm      p   tif point.
     * @rfturn     rfturns tif domponfnt tibt dontbins tif point,
     *                 or <dodf>null</dodf> if tif domponfnt dofs
     *                 not dontbin tif point.
     * @sff        Componfnt#dontbins
     * @sindf      1.1
     */
    publid Componfnt gftComponfntAt(Point p) {
        rfturn gftComponfntAt(p.x, p.y);
    }

    /**
     * Rfturns tif position of tif mousf pointfr in tiis <dodf>Contbinfr</dodf>'s
     * doordinbtf spbdf if tif <dodf>Contbinfr</dodf> is undfr tif mousf pointfr,
     * otifrwisf rfturns <dodf>null</dodf>.
     * Tiis mftiod is similbr to {@link Componfnt#gftMousfPosition()} witi tif fxdfption
     * tibt it dbn tbkf tif <dodf>Contbinfr</dodf>'s diildrfn into bddount.
     * If <dodf>bllowCiildrfn</dodf> is <dodf>fblsf</dodf>, tiis mftiod will rfturn
     * b non-null vbluf only if tif mousf pointfr is bbovf tif <dodf>Contbinfr</dodf>
     * dirfdtly, not bbovf tif pbrt obsdurfd by diildrfn.
     * If <dodf>bllowCiildrfn</dodf> is <dodf>truf</dodf>, tiis mftiod rfturns
     * b non-null vbluf if tif mousf pointfr is bbovf <dodf>Contbinfr</dodf> or bny
     * of its dfsdfndbnts.
     *
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss() rfturns truf
     * @pbrbm     bllowCiildrfn truf if diildrfn siould bf tbkfn into bddount
     * @sff       Componfnt#gftMousfPosition
     * @rfturn    mousf doordinbtfs rflbtivf to tiis <dodf>Componfnt</dodf>, or null
     * @sindf     1.5
     */
    publid Point gftMousfPosition(boolfbn bllowCiildrfn) tirows HfbdlfssExdfption {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }
        PointfrInfo pi = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<PointfrInfo>() {
                publid PointfrInfo run() {
                    rfturn MousfInfo.gftPointfrInfo();
                }
            }
        );
        syndironizfd (gftTrffLodk()) {
            Componfnt inTifSbmfWindow = findUndfrMousfInWindow(pi);
            if (isSbmfOrAndfstorOf(inTifSbmfWindow, bllowCiildrfn)) {
                rfturn  pointRflbtivfToComponfnt(pi.gftLodbtion());
            }
            rfturn null;
        }
    }

    boolfbn isSbmfOrAndfstorOf(Componfnt domp, boolfbn bllowCiildrfn) {
        rfturn tiis == domp || (bllowCiildrfn && isPbrfntOf(domp));
    }

    /**
     * Lodbtfs tif visiblf diild domponfnt tibt dontbins tif spfdififd
     * position.  Tif top-most diild domponfnt is rfturnfd in tif dbsf
     * wifrf tifrf is ovfrlbp in tif domponfnts.  If tif dontbining diild
     * domponfnt is b Contbinfr, tiis mftiod will dontinuf sfbrdiing for
     * tif dffpfst nfstfd diild domponfnt.  Componfnts wiidi brf not
     * visiblf brf ignorfd during tif sfbrdi.<p>
     *
     * Tif findComponfntAt mftiod is difffrfnt from gftComponfntAt in
     * tibt gftComponfntAt only sfbrdifs tif Contbinfr's immfdibtf
     * diildrfn; if tif dontbining domponfnt is b Contbinfr,
     * findComponfntAt will sfbrdi tibt diild to find b nfstfd domponfnt.
     *
     * @pbrbm x tif <i>x</i> doordinbtf
     * @pbrbm y tif <i>y</i> doordinbtf
     * @rfturn null if tif domponfnt dofs not dontbin tif position.
     * If tifrf is no diild domponfnt bt tif rfqufstfd point bnd tif
     * point is witiin tif bounds of tif dontbinfr tif dontbinfr itsflf
     * is rfturnfd.
     * @sff Componfnt#dontbins
     * @sff #gftComponfntAt
     * @sindf 1.2
     */
    publid Componfnt findComponfntAt(int x, int y) {
        rfturn findComponfntAt(x, y, truf);
    }

    /**
     * Privbtf vfrsion of findComponfntAt wiidi ibs b dontrollbblf
     * bfibvior. Sftting 'ignorfEnbblfd' to 'fblsf' bypbssfs disbblfd
     * Componfnts during tif sfbrdi. Tiis bfibvior is usfd by tif
     * ligitwfigit dursor support in sun.bwt.GlobblCursorMbnbgfr.
     *
     * Tif bddition of tiis ffbturf is tfmporbry, pfnding tif
     * bdoption of nfw, publid API wiidi fxports tiis ffbturf.
     */
    finbl Componfnt findComponfntAt(int x, int y, boolfbn ignorfEnbblfd) {
        syndironizfd (gftTrffLodk()) {
            if (isRfdursivflyVisiblf()){
                rfturn findComponfntAtImpl(x, y, ignorfEnbblfd);
            }
        }
        rfturn null;
    }

    finbl Componfnt findComponfntAtImpl(int x, int y, boolfbn ignorfEnbblfd){
        difdkTrffLodk();

        if (!(dontbins(x, y) && visiblf && (ignorfEnbblfd || fnbblfd))) {
            rfturn null;
        }

        // Two pbssfs: sff dommfnt in sun.bwt.SunGrbpiidsCbllbbdk
        for (int i = 0; i < domponfnt.sizf(); i++) {
            Componfnt domp = domponfnt.gft(i);
            if (domp != null &&
                !(domp.pffr instbndfof LigitwfigitPffr)) {
                if (domp instbndfof Contbinfr) {
                    domp = ((Contbinfr)domp).findComponfntAtImpl(x - domp.x,
                                                                 y - domp.y,
                                                                 ignorfEnbblfd);
                } flsf {
                    domp = domp.gftComponfntAt(x - domp.x, y - domp.y);
                }
                if (domp != null && domp.visiblf &&
                    (ignorfEnbblfd || domp.fnbblfd))
                {
                    rfturn domp;
                }
            }
        }
        for (int i = 0; i < domponfnt.sizf(); i++) {
            Componfnt domp = domponfnt.gft(i);
            if (domp != null &&
                domp.pffr instbndfof LigitwfigitPffr) {
                if (domp instbndfof Contbinfr) {
                    domp = ((Contbinfr)domp).findComponfntAtImpl(x - domp.x,
                                                                 y - domp.y,
                                                                 ignorfEnbblfd);
                } flsf {
                    domp = domp.gftComponfntAt(x - domp.x, y - domp.y);
                }
                if (domp != null && domp.visiblf &&
                    (ignorfEnbblfd || domp.fnbblfd))
                {
                    rfturn domp;
                }
            }
        }

        rfturn tiis;
    }

    /**
     * Lodbtfs tif visiblf diild domponfnt tibt dontbins tif spfdififd
     * point.  Tif top-most diild domponfnt is rfturnfd in tif dbsf
     * wifrf tifrf is ovfrlbp in tif domponfnts.  If tif dontbining diild
     * domponfnt is b Contbinfr, tiis mftiod will dontinuf sfbrdiing for
     * tif dffpfst nfstfd diild domponfnt.  Componfnts wiidi brf not
     * visiblf brf ignorfd during tif sfbrdi.<p>
     *
     * Tif findComponfntAt mftiod is difffrfnt from gftComponfntAt in
     * tibt gftComponfntAt only sfbrdifs tif Contbinfr's immfdibtf
     * diildrfn; if tif dontbining domponfnt is b Contbinfr,
     * findComponfntAt will sfbrdi tibt diild to find b nfstfd domponfnt.
     *
     * @pbrbm      p   tif point.
     * @rfturn null if tif domponfnt dofs not dontbin tif position.
     * If tifrf is no diild domponfnt bt tif rfqufstfd point bnd tif
     * point is witiin tif bounds of tif dontbinfr tif dontbinfr itsflf
     * is rfturnfd.
     * @tirows NullPointfrExdfption if {@dodf p} is {@dodf null}
     * @sff Componfnt#dontbins
     * @sff #gftComponfntAt
     * @sindf 1.2
     */
    publid Componfnt findComponfntAt(Point p) {
        rfturn findComponfntAt(p.x, p.y);
    }

    /**
     * Mbkfs tiis Contbinfr displbybblf by donnfdting it to
     * b nbtivf sdrffn rfsourdf.  Mbking b dontbinfr displbybblf will
     * dbusf bll of its diildrfn to bf mbdf displbybblf.
     * Tiis mftiod is dbllfd intfrnblly by tif toolkit bnd siould
     * not bf dbllfd dirfdtly by progrbms.
     * @sff Componfnt#isDisplbybblf
     * @sff #rfmovfNotify
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            // bddNotify() on tif diildrfn mby dbusf proxy fvfnt fnbbling
            // on tiis instbndf, so wf first dbll supfr.bddNotify() bnd
            // possibly drfbtf bn ligitwfigit fvfnt dispbtdifr bfforf dblling
            // bddNotify() on tif diildrfn wiidi mby bf ligitwfigit.
            supfr.bddNotify();
            if (! (pffr instbndfof LigitwfigitPffr)) {
                dispbtdifr = nfw LigitwfigitDispbtdifr(tiis);
            }

            // Wf siouldn't usf itfrbtor bfdbusf of tif Swing mfnu
            // implfmfntbtion spfdifids:
            // tif mfnu is bfing bssignfd bs b diild to JLbyfrfdPbnf
            // instfbd of pbrtidulbr domponfnt so blwbys bfffdt
            // dollfdtion of domponfnt if mfnu is bfdoming siown or iiddfn.
            for (int i = 0; i < domponfnt.sizf(); i++) {
                domponfnt.gft(i).bddNotify();
            }
        }
    }

    /**
     * Mbkfs tiis Contbinfr undisplbybblf by rfmoving its donnfdtion
     * to its nbtivf sdrffn rfsourdf.  Mbking b dontbinfr undisplbybblf
     * will dbusf bll of its diildrfn to bf mbdf undisplbybblf.
     * Tiis mftiod is dbllfd by tif toolkit intfrnblly bnd siould
     * not bf dbllfd dirfdtly by progrbms.
     * @sff Componfnt#isDisplbybblf
     * @sff #bddNotify
     */
    publid void rfmovfNotify() {
        syndironizfd (gftTrffLodk()) {
            // Wf siouldn't usf itfrbtor bfdbusf of tif Swing mfnu
            // implfmfntbtion spfdifids:
            // tif mfnu is bfing bssignfd bs b diild to JLbyfrfdPbnf
            // instfbd of pbrtidulbr domponfnt so blwbys bfffdt
            // dollfdtion of domponfnt if mfnu is bfdoming siown or iiddfn.
            for (int i = domponfnt.sizf()-1 ; i >= 0 ; i--) {
                Componfnt domp = domponfnt.gft(i);
                if (domp != null) {
                    // Fix for 6607170.
                    // Wf wbnt to supprfss fodus dibngf on disposbl
                    // of tif fodusfd domponfnt. But bfdbusf of fodus
                    // is bsyndironous, wf siould supprfss fodus dibngf
                    // on fvfry domponfnt in dbsf it rfdfivfs nbtivf fodus
                    // in tif prodfss of disposbl.
                    domp.sftAutoFodusTrbnsffrOnDisposbl(fblsf);
                    domp.rfmovfNotify();
                    domp.sftAutoFodusTrbnsffrOnDisposbl(truf);
                 }
             }
            // If somf of tif diildrfn ibd fodus bfforf disposbl tifn it still ibs.
            // Auto-trbnsffr fodus to tif nfxt (or prfvious) domponfnt if buto-trbnsffr
            // is fnbblfd.
            if (dontbinsFodus() && KfybobrdFodusMbnbgfr.isAutoFodusTrbnsffrEnbblfdFor(tiis)) {
                if (!trbnsffrFodus(fblsf)) {
                    trbnsffrFodusBbdkwbrd(truf);
                }
            }
            if ( dispbtdifr != null ) {
                dispbtdifr.disposf();
                dispbtdifr = null;
            }
            supfr.rfmovfNotify();
        }
    }

    /**
     * Cifdks if tif domponfnt is dontbinfd in tif domponfnt iifrbrdiy of
     * tiis dontbinfr.
     * @pbrbm d tif domponfnt
     * @rfturn     <dodf>truf</dodf> if it is bn bndfstor;
     *             <dodf>fblsf</dodf> otifrwisf.
     * @sindf      1.1
     */
    publid boolfbn isAndfstorOf(Componfnt d) {
        Contbinfr p;
        if (d == null || ((p = d.gftPbrfnt()) == null)) {
            rfturn fblsf;
        }
        wiilf (p != null) {
            if (p == tiis) {
                rfturn truf;
            }
            p = p.gftPbrfnt();
        }
        rfturn fblsf;
    }

    /*
     * Tif following dodf wbs bddfd to support modbl JIntfrnblFrbmfs
     * Unfortunbtfly tiis dodf ibs to bf bddfd ifrf so tibt wf dbn gft bddfss to
     * somf privbtf AWT dlbssfs likf SfqufndfdEvfnt.
     *
     * Tif nbtivf dontbinfr of tif LW domponfnt ibs tiis fifld sft
     * to tfll it tibt it siould blodk Mousf fvfnts for bll LW
     * diildrfn fxdfpt for tif modbl domponfnt.
     *
     * In tif dbsf of nfstfd Modbl domponfnts, wf storf tif prfvious
     * modbl domponfnt in tif nfw modbl domponfnts vbluf of modblComp;
     */

    trbnsifnt Componfnt modblComp;
    trbnsifnt AppContfxt modblAppContfxt;

    privbtf void stbrtLWModbl() {
        // Storf tif bpp dontfxt on wiidi tiis domponfnt is bfing siown.
        // Evfnt dispbtdi tirfbd of tiis bpp dontfxt will bf slffping until
        // wf wbkf it by bny fvfnt from iidfAndDisposfHbndlfr().
        modblAppContfxt = AppContfxt.gftAppContfxt();

        // kffp tif KfyEvfnts from bfing dispbtdifd
        // until tif fodus ibs bffn trbnsffrfd
        long timf = Toolkit.gftEvfntQufuf().gftMostRfdfntKfyEvfntTimf();
        Componfnt prfdidtfdFodusOwnfr = (Componfnt.isInstbndfOf(tiis, "jbvbx.swing.JIntfrnblFrbmf")) ? ((jbvbx.swing.JIntfrnblFrbmf)(tiis)).gftMostRfdfntFodusOwnfr() : null;
        if (prfdidtfdFodusOwnfr != null) {
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                fnqufufKfyEvfnts(timf, prfdidtfdFodusOwnfr);
        }
        // Wf ibvf two mfdibnisms for blodking: 1. If wf'rf on tif
        // EvfntDispbtdiTirfbd, stbrt b nfw fvfnt pump. 2. If wf'rf
        // on bny otifr tirfbd, dbll wbit() on tif trfflodk.
        finbl Contbinfr nbtivfContbinfr;
        syndironizfd (gftTrffLodk()) {
            nbtivfContbinfr = gftHfbvywfigitContbinfr();
            if (nbtivfContbinfr.modblComp != null) {
                tiis.modblComp =  nbtivfContbinfr.modblComp;
                nbtivfContbinfr.modblComp = tiis;
                rfturn;
            }
            flsf {
                nbtivfContbinfr.modblComp = tiis;
            }
        }

        Runnbblf pumpEvfntsForHifrbrdiy = () -> {
            EvfntDispbtdiTirfbd dispbtdiTirfbd = (EvfntDispbtdiTirfbd)Tirfbd.durrfntTirfbd();
            dispbtdiTirfbd.pumpEvfntsForHifrbrdiy(() -> nbtivfContbinfr.modblComp != null,
                    Contbinfr.tiis);
        };

        if (EvfntQufuf.isDispbtdiTirfbd()) {
            SfqufndfdEvfnt durrfntSfqufndfdEvfnt =
                KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                gftCurrfntSfqufndfdEvfnt();
            if (durrfntSfqufndfdEvfnt != null) {
                durrfntSfqufndfdEvfnt.disposf();
            }

            pumpEvfntsForHifrbrdiy.run();
        } flsf {
            syndironizfd (gftTrffLodk()) {
                Toolkit.gftEvfntQufuf().
                    postEvfnt(nfw PffrEvfnt(tiis,
                                pumpEvfntsForHifrbrdiy,
                                PffrEvfnt.PRIORITY_EVENT));
                wiilf (nbtivfContbinfr.modblComp != null)
                {
                    try {
                        gftTrffLodk().wbit();
                    } dbtdi (IntfrruptfdExdfption f) {
                        brfbk;
                    }
                }
            }
        }
        if (prfdidtfdFodusOwnfr != null) {
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                dfqufufKfyEvfnts(timf, prfdidtfdFodusOwnfr);
        }
    }

    privbtf void stopLWModbl() {
        syndironizfd (gftTrffLodk()) {
            if (modblAppContfxt != null) {
                Contbinfr nbtivfContbinfr = gftHfbvywfigitContbinfr();
                if(nbtivfContbinfr != null) {
                    if (tiis.modblComp !=  null) {
                        nbtivfContbinfr.modblComp = tiis.modblComp;
                        tiis.modblComp = null;
                        rfturn;
                    }
                    flsf {
                        nbtivfContbinfr.modblComp = null;
                    }
                }
                // Wbkf up fvfnt dispbtdi tirfbd on wiidi tif diblog wbs
                // initiblly siown
                SunToolkit.postEvfnt(modblAppContfxt,
                        nfw PffrEvfnt(tiis,
                                nfw WbkingRunnbblf(),
                                PffrEvfnt.PRIORITY_EVENT));
            }
            EvfntQufuf.invokfLbtfr(nfw WbkingRunnbblf());
            gftTrffLodk().notifyAll();
        }
    }

    finbl stbtid dlbss WbkingRunnbblf implfmfnts Runnbblf {
        publid void run() {
        }
    }

    /* End of JOptionPbnf support dodf */

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis <dodf>Contbinfr</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn    tif pbrbmftfr string of tiis dontbinfr
     */
    protfdtfd String pbrbmString() {
        String str = supfr.pbrbmString();
        LbyoutMbnbgfr lbyoutMgr = tiis.lbyoutMgr;
        if (lbyoutMgr != null) {
            str += ",lbyout=" + lbyoutMgr.gftClbss().gftNbmf();
        }
        rfturn str;
    }

    /**
     * Prints b listing of tiis dontbinfr to tif spfdififd output
     * strfbm. Tif listing stbrts bt tif spfdififd indfntbtion.
     * <p>
     * Tif immfdibtf diildrfn of tif dontbinfr brf printfd witi
     * bn indfntbtion of <dodf>indfnt+1</dodf>.  Tif diildrfn
     * of tiosf diildrfn brf printfd bt <dodf>indfnt+2</dodf>
     * bnd so on.
     *
     * @pbrbm    out      b print strfbm
     * @pbrbm    indfnt   tif numbfr of spbdfs to indfnt
     * @tirows   NullPointfrExdfption if {@dodf out} is {@dodf null}
     * @sff      Componfnt#list(jbvb.io.PrintStrfbm, int)
     * @sindf    1.0
     */
    publid void list(PrintStrfbm out, int indfnt) {
        supfr.list(out, indfnt);
        syndironizfd(gftTrffLodk()) {
            for (int i = 0; i < domponfnt.sizf(); i++) {
                Componfnt domp = domponfnt.gft(i);
                if (domp != null) {
                    domp.list(out, indfnt+1);
                }
            }
        }
    }

    /**
     * Prints out b list, stbrting bt tif spfdififd indfntbtion,
     * to tif spfdififd print writfr.
     * <p>
     * Tif immfdibtf diildrfn of tif dontbinfr brf printfd witi
     * bn indfntbtion of <dodf>indfnt+1</dodf>.  Tif diildrfn
     * of tiosf diildrfn brf printfd bt <dodf>indfnt+2</dodf>
     * bnd so on.
     *
     * @pbrbm    out      b print writfr
     * @pbrbm    indfnt   tif numbfr of spbdfs to indfnt
     * @tirows   NullPointfrExdfption if {@dodf out} is {@dodf null}
     * @sff      Componfnt#list(jbvb.io.PrintWritfr, int)
     * @sindf    1.1
     */
    publid void list(PrintWritfr out, int indfnt) {
        supfr.list(out, indfnt);
        syndironizfd(gftTrffLodk()) {
            for (int i = 0; i < domponfnt.sizf(); i++) {
                Componfnt domp = domponfnt.gft(i);
                if (domp != null) {
                    domp.list(out, indfnt+1);
                }
            }
        }
    }

    /**
     * Sfts tif fodus trbvfrsbl kfys for b givfn trbvfrsbl opfrbtion for tiis
     * Contbinfr.
     * <p>
     * Tif dffbult vblufs for b Contbinfr's fodus trbvfrsbl kfys brf
     * implfmfntbtion-dfpfndfnt. Sun rfdommfnds tibt bll implfmfntbtions for b
     * pbrtidulbr nbtivf plbtform usf tif sbmf dffbult vblufs. Tif
     * rfdommfndbtions for Windows bnd Unix brf listfd bflow. Tifsf
     * rfdommfndbtions brf usfd in tif Sun AWT implfmfntbtions.
     *
     * <tbblf bordfr=1 summbry="Rfdommfndfd dffbult vblufs for b Contbinfr's fodus trbvfrsbl kfys">
     * <tr>
     *    <ti>Idfntififr</ti>
     *    <ti>Mfbning</ti>
     *    <ti>Dffbult</ti>
     * </tr>
     * <tr>
     *    <td>KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS</td>
     *    <td>Normbl forwbrd kfybobrd trbvfrsbl</td>
     *    <td>TAB on KEY_PRESSED, CTRL-TAB on KEY_PRESSED</td>
     * </tr>
     * <tr>
     *    <td>KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS</td>
     *    <td>Normbl rfvfrsf kfybobrd trbvfrsbl</td>
     *    <td>SHIFT-TAB on KEY_PRESSED, CTRL-SHIFT-TAB on KEY_PRESSED</td>
     * </tr>
     * <tr>
     *    <td>KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS</td>
     *    <td>Go up onf fodus trbvfrsbl dydlf</td>
     *    <td>nonf</td>
     * </tr>
     * <tr>
     *    <td>KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS<td>
     *    <td>Go down onf fodus trbvfrsbl dydlf</td>
     *    <td>nonf</td>
     * </tr>
     * </tbblf>
     *
     * To disbblf b trbvfrsbl kfy, usf bn fmpty Sft; Collfdtions.EMPTY_SET is
     * rfdommfndfd.
     * <p>
     * Using tif AWTKfyStrokf API, dlifnt dodf dbn spfdify on wiidi of two
     * spfdifid KfyEvfnts, KEY_PRESSED or KEY_RELEASED, tif fodus trbvfrsbl
     * opfrbtion will oddur. Rfgbrdlfss of wiidi KfyEvfnt is spfdififd,
     * iowfvfr, bll KfyEvfnts rflbtfd to tif fodus trbvfrsbl kfy, indluding tif
     * bssodibtfd KEY_TYPED fvfnt, will bf donsumfd, bnd will not bf dispbtdifd
     * to bny Contbinfr. It is b runtimf frror to spfdify b KEY_TYPED fvfnt bs
     * mbpping to b fodus trbvfrsbl opfrbtion, or to mbp tif sbmf fvfnt to
     * multiplf dffbult fodus trbvfrsbl opfrbtions.
     * <p>
     * If b vbluf of null is spfdififd for tif Sft, tiis Contbinfr inifrits tif
     * Sft from its pbrfnt. If bll bndfstors of tiis Contbinfr ibvf null
     * spfdififd for tif Sft, tifn tif durrfnt KfybobrdFodusMbnbgfr's dffbult
     * Sft is usfd.
     * <p>
     * Tiis mftiod mby tirow b {@dodf ClbssCbstExdfption} if bny {@dodf Objfdt}
     * in {@dodf kfystrokfs} is not bn {@dodf AWTKfyStrokf}.
     *
     * @pbrbm id onf of KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS, or
     *        KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS
     * @pbrbm kfystrokfs tif Sft of AWTKfyStrokf for tif spfdififd opfrbtion
     * @sff #gftFodusTrbvfrsblKfys
     * @sff KfybobrdFodusMbnbgfr#FORWARD_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#BACKWARD_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#UP_CYCLE_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#DOWN_CYCLE_TRAVERSAL_KEYS
     * @tirows IllfgblArgumfntExdfption if id is not onf of
     *         KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS, or
     *         KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS, or if kfystrokfs
     *         dontbins null, or if bny kfystrokf rfprfsfnts b KEY_TYPED fvfnt,
     *         or if bny kfystrokf blrfbdy mbps to bnotifr fodus trbvfrsbl
     *         opfrbtion for tiis Contbinfr
     * @sindf 1.4
     * @bfbninfo
     *       bound: truf
     */
    publid void sftFodusTrbvfrsblKfys(int id,
                                      Sft<? fxtfnds AWTKfyStrokf> kfystrokfs)
    {
        if (id < 0 || id >= KfybobrdFodusMbnbgfr.TRAVERSAL_KEY_LENGTH) {
            tirow nfw IllfgblArgumfntExdfption("invblid fodus trbvfrsbl kfy idfntififr");
        }

        // Don't dbll supfr.sftFodusTrbvfrsblKfy. Tif Componfnt pbrbmftfr difdk
        // dofs not bllow DOWN_CYCLE_TRAVERSAL_KEYS, but wf do.
        sftFodusTrbvfrsblKfys_NoIDCifdk(id, kfystrokfs);
    }

    /**
     * Rfturns tif Sft of fodus trbvfrsbl kfys for b givfn trbvfrsbl opfrbtion
     * for tiis Contbinfr. (Sff
     * <dodf>sftFodusTrbvfrsblKfys</dodf> for b full dfsdription of fbdi kfy.)
     * <p>
     * If b Sft of trbvfrsbl kfys ibs not bffn fxpliditly dffinfd for tiis
     * Contbinfr, tifn tiis Contbinfr's pbrfnt's Sft is rfturnfd. If no Sft
     * ibs bffn fxpliditly dffinfd for bny of tiis Contbinfr's bndfstors, tifn
     * tif durrfnt KfybobrdFodusMbnbgfr's dffbult Sft is rfturnfd.
     *
     * @pbrbm id onf of KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS, or
     *        KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS
     * @rfturn tif Sft of AWTKfyStrokfs for tif spfdififd opfrbtion. Tif Sft
     *         will bf unmodifibblf, bnd mby bf fmpty. null will nfvfr bf
     *         rfturnfd.
     * @sff #sftFodusTrbvfrsblKfys
     * @sff KfybobrdFodusMbnbgfr#FORWARD_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#BACKWARD_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#UP_CYCLE_TRAVERSAL_KEYS
     * @sff KfybobrdFodusMbnbgfr#DOWN_CYCLE_TRAVERSAL_KEYS
     * @tirows IllfgblArgumfntExdfption if id is not onf of
     *         KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
     *         KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS, or
     *         KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS
     * @sindf 1.4
     */
    publid Sft<AWTKfyStrokf> gftFodusTrbvfrsblKfys(int id) {
        if (id < 0 || id >= KfybobrdFodusMbnbgfr.TRAVERSAL_KEY_LENGTH) {
            tirow nfw IllfgblArgumfntExdfption("invblid fodus trbvfrsbl kfy idfntififr");
        }

        // Don't dbll supfr.gftFodusTrbvfrsblKfy. Tif Componfnt pbrbmftfr difdk
        // dofs not bllow DOWN_CYCLE_TRAVERSAL_KEY, but wf do.
        rfturn gftFodusTrbvfrsblKfys_NoIDCifdk(id);
    }

    /**
     * Rfturns wiftifr tif Sft of fodus trbvfrsbl kfys for tif givfn fodus
     * trbvfrsbl opfrbtion ibs bffn fxpliditly dffinfd for tiis Contbinfr. If
     * tiis mftiod rfturns <dodf>fblsf</dodf>, tiis Contbinfr is inifriting tif
     * Sft from bn bndfstor, or from tif durrfnt KfybobrdFodusMbnbgfr.
     *
     * @pbrbm id onf of KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS, or
     *        KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS
     * @rfturn <dodf>truf</dodf> if tif tif Sft of fodus trbvfrsbl kfys for tif
     *         givfn fodus trbvfrsbl opfrbtion ibs bffn fxpliditly dffinfd for
     *         tiis Componfnt; <dodf>fblsf</dodf> otifrwisf.
     * @tirows IllfgblArgumfntExdfption if id is not onf of
     *         KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
     *        KfybobrdFodusMbnbgfr.UP_CYCLE_TRAVERSAL_KEYS, or
     *        KfybobrdFodusMbnbgfr.DOWN_CYCLE_TRAVERSAL_KEYS
     * @sindf 1.4
     */
    publid boolfbn brfFodusTrbvfrsblKfysSft(int id) {
        if (id < 0 || id >= KfybobrdFodusMbnbgfr.TRAVERSAL_KEY_LENGTH) {
            tirow nfw IllfgblArgumfntExdfption("invblid fodus trbvfrsbl kfy idfntififr");
        }

        rfturn (fodusTrbvfrsblKfys != null && fodusTrbvfrsblKfys[id] != null);
    }

    /**
     * Rfturns wiftifr tif spfdififd Contbinfr is tif fodus dydlf root of tiis
     * Contbinfr's fodus trbvfrsbl dydlf. Ebdi fodus trbvfrsbl dydlf ibs only
     * b singlf fodus dydlf root bnd fbdi Contbinfr wiidi is not b fodus dydlf
     * root bflongs to only b singlf fodus trbvfrsbl dydlf. Contbinfrs wiidi
     * brf fodus dydlf roots bflong to two dydlfs: onf rootfd bt tif Contbinfr
     * itsflf, bnd onf rootfd bt tif Contbinfr's nfbrfst fodus-dydlf-root
     * bndfstor. Tiis mftiod will rfturn <dodf>truf</dodf> for boti sudi
     * Contbinfrs in tiis dbsf.
     *
     * @pbrbm dontbinfr tif Contbinfr to bf tfstfd
     * @rfturn <dodf>truf</dodf> if tif spfdififd Contbinfr is b fodus-dydlf-
     *         root of tiis Contbinfr; <dodf>fblsf</dodf> otifrwisf
     * @sff #isFodusCydlfRoot()
     * @sindf 1.4
     */
    publid boolfbn isFodusCydlfRoot(Contbinfr dontbinfr) {
        if (isFodusCydlfRoot() && dontbinfr == tiis) {
            rfturn truf;
        } flsf {
            rfturn supfr.isFodusCydlfRoot(dontbinfr);
        }
    }

    privbtf Contbinfr findTrbvfrsblRoot() {
        // I potfntiblly ibvf two roots, mysflf bnd my root pbrfnt
        // If I bm tif durrfnt root, tifn usf mf
        // If nonf of my pbrfnts brf roots, tifn usf mf
        // If my root pbrfnt is tif durrfnt root, tifn usf my root pbrfnt
        // If nfitifr I nor my root pbrfnt is tif durrfnt root, tifn
        // usf my root pbrfnt (b gufss)

        Contbinfr durrfntFodusCydlfRoot = KfybobrdFodusMbnbgfr.
            gftCurrfntKfybobrdFodusMbnbgfr().gftCurrfntFodusCydlfRoot();
        Contbinfr root;

        if (durrfntFodusCydlfRoot == tiis) {
            root = tiis;
        } flsf {
            root = gftFodusCydlfRootAndfstor();
            if (root == null) {
                root = tiis;
            }
        }

        if (root != durrfntFodusCydlfRoot) {
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                sftGlobblCurrfntFodusCydlfRootPriv(root);
        }
        rfturn root;
    }

    finbl boolfbn dontbinsFodus() {
        finbl Componfnt fodusOwnfr = KfybobrdFodusMbnbgfr.
            gftCurrfntKfybobrdFodusMbnbgfr().gftFodusOwnfr();
        rfturn isPbrfntOf(fodusOwnfr);
    }

    /**
     * Cifdk if tiis domponfnt is tif diild of tiis dontbinfr or its diildrfn.
     * Notf: tiis fundtion bdquirfs trffLodk
     * Notf: tiis fundtion trbvfrsfs diildrfn trff only in onf Window.
     * @pbrbm domp b domponfnt in tfst, must not bf null
     */
    privbtf boolfbn isPbrfntOf(Componfnt domp) {
        syndironizfd(gftTrffLodk()) {
            wiilf (domp != null && domp != tiis && !(domp instbndfof Window)) {
                domp = domp.gftPbrfnt();
            }
            rfturn (domp == tiis);
        }
    }

    void dlfbrMostRfdfntFodusOwnfrOnHidf() {
        boolfbn rfsft = fblsf;
        Window window = null;

        syndironizfd (gftTrffLodk()) {
            window = gftContbiningWindow();
            if (window != null) {
                Componfnt domp = KfybobrdFodusMbnbgfr.gftMostRfdfntFodusOwnfr(window);
                rfsft = ((domp == tiis) || isPbrfntOf(domp));
                // Tiis syndironizfd siould blwbys bf tif sfdond in b pbir
                // (trff lodk, KfybobrdFodusMbnbgfr.dlbss)
                syndironizfd(KfybobrdFodusMbnbgfr.dlbss) {
                    Componfnt storfdComp = window.gftTfmporbryLostComponfnt();
                    if (isPbrfntOf(storfdComp) || storfdComp == tiis) {
                        window.sftTfmporbryLostComponfnt(null);
                    }
                }
            }
        }

        if (rfsft) {
            KfybobrdFodusMbnbgfr.sftMostRfdfntFodusOwnfr(window, null);
        }
    }

    void dlfbrCurrfntFodusCydlfRootOnHidf() {
        KfybobrdFodusMbnbgfr kfm =
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr();
        Contbinfr dont = kfm.gftCurrfntFodusCydlfRoot();

        if (dont == tiis || isPbrfntOf(dont)) {
            kfm.sftGlobblCurrfntFodusCydlfRootPriv(null);
        }
    }

    @Ovfrridf
    void dlfbrLigitwfigitDispbtdifrOnRfmovf(Componfnt rfmovfdComponfnt) {
        if (dispbtdifr != null) {
            dispbtdifr.rfmovfRfffrfndfs(rfmovfdComponfnt);
        } flsf {
            //It is b Ligitwfigit Contbinfr, siould dlfbr pbrfnt`s Dispbtdifr
            supfr.dlfbrLigitwfigitDispbtdifrOnRfmovf(rfmovfdComponfnt);
        }
    }

    finbl Contbinfr gftTrbvfrsblRoot() {
        if (isFodusCydlfRoot()) {
            rfturn findTrbvfrsblRoot();
        }

        rfturn supfr.gftTrbvfrsblRoot();
    }

    /**
     * Sfts tif fodus trbvfrsbl polidy tibt will mbnbgf kfybobrd trbvfrsbl of
     * tiis Contbinfr's diildrfn, if tiis Contbinfr is b fodus dydlf root. If
     * tif brgumfnt is null, tiis Contbinfr inifrits its polidy from its fodus-
     * dydlf-root bndfstor. If tif brgumfnt is non-null, tiis polidy will bf
     * inifritfd by bll fodus-dydlf-root diildrfn tibt ibvf no kfybobrd-
     * trbvfrsbl polidy of tifir own (bs will, rfdursivfly, tifir fodus-dydlf-
     * root diildrfn).
     * <p>
     * If tiis Contbinfr is not b fodus dydlf root, tif polidy will bf
     * rfmfmbfrfd, but will not bf usfd or inifritfd by tiis or bny otifr
     * Contbinfrs until tiis Contbinfr is mbdf b fodus dydlf root.
     *
     * @pbrbm polidy tif nfw fodus trbvfrsbl polidy for tiis Contbinfr
     * @sff #gftFodusTrbvfrsblPolidy
     * @sff #sftFodusCydlfRoot
     * @sff #isFodusCydlfRoot
     * @sindf 1.4
     * @bfbninfo
     *       bound: truf
     */
    publid void sftFodusTrbvfrsblPolidy(FodusTrbvfrsblPolidy polidy) {
        FodusTrbvfrsblPolidy oldPolidy;
        syndironizfd (tiis) {
            oldPolidy = tiis.fodusTrbvfrsblPolidy;
            tiis.fodusTrbvfrsblPolidy = polidy;
        }
        firfPropfrtyCibngf("fodusTrbvfrsblPolidy", oldPolidy, polidy);
    }

    /**
     * Rfturns tif fodus trbvfrsbl polidy tibt will mbnbgf kfybobrd trbvfrsbl
     * of tiis Contbinfr's diildrfn, or null if tiis Contbinfr is not b fodus
     * dydlf root. If no trbvfrsbl polidy ibs bffn fxpliditly sft for tiis
     * Contbinfr, tifn tiis Contbinfr's fodus-dydlf-root bndfstor's polidy is
     * rfturnfd.
     *
     * @rfturn tiis Contbinfr's fodus trbvfrsbl polidy, or null if tiis
     *         Contbinfr is not b fodus dydlf root.
     * @sff #sftFodusTrbvfrsblPolidy
     * @sff #sftFodusCydlfRoot
     * @sff #isFodusCydlfRoot
     * @sindf 1.4
     */
    publid FodusTrbvfrsblPolidy gftFodusTrbvfrsblPolidy() {
        if (!isFodusTrbvfrsblPolidyProvidfr() && !isFodusCydlfRoot()) {
            rfturn null;
        }

        FodusTrbvfrsblPolidy polidy = tiis.fodusTrbvfrsblPolidy;
        if (polidy != null) {
            rfturn polidy;
        }

        Contbinfr rootAndfstor = gftFodusCydlfRootAndfstor();
        if (rootAndfstor != null) {
            rfturn rootAndfstor.gftFodusTrbvfrsblPolidy();
        } flsf {
            rfturn KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                gftDffbultFodusTrbvfrsblPolidy();
        }
    }

    /**
     * Rfturns wiftifr tif fodus trbvfrsbl polidy ibs bffn fxpliditly sft for
     * tiis Contbinfr. If tiis mftiod rfturns <dodf>fblsf</dodf>, tiis
     * Contbinfr will inifrit its fodus trbvfrsbl polidy from bn bndfstor.
     *
     * @rfturn <dodf>truf</dodf> if tif fodus trbvfrsbl polidy ibs bffn
     *         fxpliditly sft for tiis Contbinfr; <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.4
     */
    publid boolfbn isFodusTrbvfrsblPolidySft() {
        rfturn (fodusTrbvfrsblPolidy != null);
    }

    /**
     * Sfts wiftifr tiis Contbinfr is tif root of b fodus trbvfrsbl dydlf. Ondf
     * fodus fntfrs b trbvfrsbl dydlf, typidblly it dbnnot lfbvf it vib fodus
     * trbvfrsbl unlfss onf of tif up- or down-dydlf kfys is prfssfd. Normbl
     * trbvfrsbl is limitfd to tiis Contbinfr, bnd bll of tiis Contbinfr's
     * dfsdfndbnts tibt brf not dfsdfndbnts of inffrior fodus dydlf roots. Notf
     * tibt b FodusTrbvfrsblPolidy mby bfnd tifsf rfstridtions, iowfvfr. For
     * fxbmplf, ContbinfrOrdfrFodusTrbvfrsblPolidy supports implidit down-dydlf
     * trbvfrsbl.
     * <p>
     * Tif bltfrnbtivf wby to spfdify tif trbvfrsbl ordfr of tiis Contbinfr's
     * diildrfn is to mbkf tiis Contbinfr b
     * <b irff="dod-filfs/FodusSpfd.itml#FodusTrbvfrsblPolidyProvidfrs">fodus trbvfrsbl polidy providfr</b>.
     *
     * @pbrbm fodusCydlfRoot indidbtfs wiftifr tiis Contbinfr is tif root of b
     *        fodus trbvfrsbl dydlf
     * @sff #isFodusCydlfRoot()
     * @sff #sftFodusTrbvfrsblPolidy
     * @sff #gftFodusTrbvfrsblPolidy
     * @sff ContbinfrOrdfrFodusTrbvfrsblPolidy
     * @sff #sftFodusTrbvfrsblPolidyProvidfr
     * @sindf 1.4
     * @bfbninfo
     *       bound: truf
     */
    publid void sftFodusCydlfRoot(boolfbn fodusCydlfRoot) {
        boolfbn oldFodusCydlfRoot;
        syndironizfd (tiis) {
            oldFodusCydlfRoot = tiis.fodusCydlfRoot;
            tiis.fodusCydlfRoot = fodusCydlfRoot;
        }
        firfPropfrtyCibngf("fodusCydlfRoot", oldFodusCydlfRoot,
                           fodusCydlfRoot);
    }

    /**
     * Rfturns wiftifr tiis Contbinfr is tif root of b fodus trbvfrsbl dydlf.
     * Ondf fodus fntfrs b trbvfrsbl dydlf, typidblly it dbnnot lfbvf it vib
     * fodus trbvfrsbl unlfss onf of tif up- or down-dydlf kfys is prfssfd.
     * Normbl trbvfrsbl is limitfd to tiis Contbinfr, bnd bll of tiis
     * Contbinfr's dfsdfndbnts tibt brf not dfsdfndbnts of inffrior fodus
     * dydlf roots. Notf tibt b FodusTrbvfrsblPolidy mby bfnd tifsf
     * rfstridtions, iowfvfr. For fxbmplf, ContbinfrOrdfrFodusTrbvfrsblPolidy
     * supports implidit down-dydlf trbvfrsbl.
     *
     * @rfturn wiftifr tiis Contbinfr is tif root of b fodus trbvfrsbl dydlf
     * @sff #sftFodusCydlfRoot
     * @sff #sftFodusTrbvfrsblPolidy
     * @sff #gftFodusTrbvfrsblPolidy
     * @sff ContbinfrOrdfrFodusTrbvfrsblPolidy
     * @sindf 1.4
     */
    publid boolfbn isFodusCydlfRoot() {
        rfturn fodusCydlfRoot;
    }

    /**
     * Sfts wiftifr tiis dontbinfr will bf usfd to providf fodus
     * trbvfrsbl polidy. Contbinfr witi tiis propfrty bs
     * <dodf>truf</dodf> will bf usfd to bdquirf fodus trbvfrsbl polidy
     * instfbd of dlosfst fodus dydlf root bndfstor.
     * @pbrbm providfr indidbtfs wiftifr tiis dontbinfr will bf usfd to
     *                providf fodus trbvfrsbl polidy
     * @sff #sftFodusTrbvfrsblPolidy
     * @sff #gftFodusTrbvfrsblPolidy
     * @sff #isFodusTrbvfrsblPolidyProvidfr
     * @sindf 1.5
     * @bfbninfo
     *        bound: truf
     */
    publid finbl void sftFodusTrbvfrsblPolidyProvidfr(boolfbn providfr) {
        boolfbn oldProvidfr;
        syndironizfd(tiis) {
            oldProvidfr = fodusTrbvfrsblPolidyProvidfr;
            fodusTrbvfrsblPolidyProvidfr = providfr;
        }
        firfPropfrtyCibngf("fodusTrbvfrsblPolidyProvidfr", oldProvidfr, providfr);
    }

    /**
     * Rfturns wiftifr tiis dontbinfr providfs fodus trbvfrsbl
     * polidy. If tiis propfrty is sft to <dodf>truf</dodf> tifn wifn
     * kfybobrd fodus mbnbgfr sfbrdifs dontbinfr iifrbrdiy for fodus
     * trbvfrsbl polidy bnd fndountfrs tiis dontbinfr bfforf bny otifr
     * dontbinfr witi tiis propfrty bs truf or fodus dydlf roots tifn
     * its fodus trbvfrsbl polidy will bf usfd instfbd of fodus dydlf
     * root's polidy.
     * @sff #sftFodusTrbvfrsblPolidy
     * @sff #gftFodusTrbvfrsblPolidy
     * @sff #sftFodusCydlfRoot
     * @sff #sftFodusTrbvfrsblPolidyProvidfr
     * @rfturn <dodf>truf</dodf> if tiis dontbinfr providfs fodus trbvfrsbl
     *         polidy, <dodf>fblsf</dodf> otifrwisf
     * @sindf 1.5
     * @bfbninfo
     *        bound: truf
     */
    publid finbl boolfbn isFodusTrbvfrsblPolidyProvidfr() {
        rfturn fodusTrbvfrsblPolidyProvidfr;
    }

    /**
     * Trbnsffrs tif fodus down onf fodus trbvfrsbl dydlf. If tiis Contbinfr is
     * b fodus dydlf root, tifn tif fodus ownfr is sft to tiis Contbinfr's
     * dffbult Componfnt to fodus, bnd tif durrfnt fodus dydlf root is sft to
     * tiis Contbinfr. If tiis Contbinfr is not b fodus dydlf root, tifn no
     * fodus trbvfrsbl opfrbtion oddurs.
     *
     * @sff       Componfnt#rfqufstFodus()
     * @sff       #isFodusCydlfRoot
     * @sff       #sftFodusCydlfRoot
     * @sindf     1.4
     */
    publid void trbnsffrFodusDownCydlf() {
        if (isFodusCydlfRoot()) {
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                sftGlobblCurrfntFodusCydlfRootPriv(tiis);
            Componfnt toFodus = gftFodusTrbvfrsblPolidy().
                gftDffbultComponfnt(tiis);
            if (toFodus != null) {
                toFodus.rfqufstFodus(CbusfdFodusEvfnt.Cbusf.TRAVERSAL_DOWN);
            }
        }
    }

    void prfProdfssKfyEvfnt(KfyEvfnt f) {
        Contbinfr pbrfnt = tiis.pbrfnt;
        if (pbrfnt != null) {
            pbrfnt.prfProdfssKfyEvfnt(f);
        }
    }

    void postProdfssKfyEvfnt(KfyEvfnt f) {
        Contbinfr pbrfnt = tiis.pbrfnt;
        if (pbrfnt != null) {
            pbrfnt.postProdfssKfyEvfnt(f);
        }
    }

    boolfbn postsOldMousfEvfnts() {
        rfturn truf;
    }

    /**
     * Sfts tif <dodf>ComponfntOrifntbtion</dodf> propfrty of tiis dontbinfr
     * bnd bll domponfnts dontbinfd witiin it.
     * <p>
     * Tiis mftiod dibngfs lbyout-rflbtfd informbtion, bnd tifrfforf,
     * invblidbtfs tif domponfnt iifrbrdiy.
     *
     * @pbrbm o tif nfw domponfnt orifntbtion of tiis dontbinfr bnd
     *        tif domponfnts dontbinfd witiin it.
     * @fxdfption NullPointfrExdfption if <dodf>orifntbtion</dodf> is null.
     * @sff Componfnt#sftComponfntOrifntbtion
     * @sff Componfnt#gftComponfntOrifntbtion
     * @sff #invblidbtf
     * @sindf 1.4
     */
    publid void bpplyComponfntOrifntbtion(ComponfntOrifntbtion o) {
        supfr.bpplyComponfntOrifntbtion(o);
        syndironizfd (gftTrffLodk()) {
            for (int i = 0; i < domponfnt.sizf(); i++) {
                Componfnt domp = domponfnt.gft(i);
                domp.bpplyComponfntOrifntbtion(o);
            }
        }
    }

    /**
     * Adds b PropfrtyCibngfListfnfr to tif listfnfr list. Tif listfnfr is
     * rfgistfrfd for bll bound propfrtifs of tiis dlbss, indluding tif
     * following:
     * <ul>
     *    <li>tiis Contbinfr's font ("font")</li>
     *    <li>tiis Contbinfr's bbdkground dolor ("bbdkground")</li>
     *    <li>tiis Contbinfr's forfground dolor ("forfground")</li>
     *    <li>tiis Contbinfr's fodusbbility ("fodusbblf")</li>
     *    <li>tiis Contbinfr's fodus trbvfrsbl kfys fnbblfd stbtf
     *        ("fodusTrbvfrsblKfysEnbblfd")</li>
     *    <li>tiis Contbinfr's Sft of FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdFodusTrbvfrsblKfys")</li>
     *    <li>tiis Contbinfr's Sft of BACKWARD_TRAVERSAL_KEYS
     *        ("bbdkwbrdFodusTrbvfrsblKfys")</li>
     *    <li>tiis Contbinfr's Sft of UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCydlfFodusTrbvfrsblKfys")</li>
     *    <li>tiis Contbinfr's Sft of DOWN_CYCLE_TRAVERSAL_KEYS
     *        ("downCydlfFodusTrbvfrsblKfys")</li>
     *    <li>tiis Contbinfr's fodus trbvfrsbl polidy ("fodusTrbvfrsblPolidy")
     *        </li>
     *    <li>tiis Contbinfr's fodus-dydlf-root stbtf ("fodusCydlfRoot")</li>
     * </ul>
     * Notf tibt if tiis Contbinfr is inifriting b bound propfrty, tifn no
     * fvfnt will bf firfd in rfsponsf to b dibngf in tif inifritfd propfrty.
     * <p>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm    listfnfr  tif PropfrtyCibngfListfnfr to bf bddfd
     *
     * @sff Componfnt#rfmovfPropfrtyCibngfListfnfr
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.lbng.String,jbvb.bfbns.PropfrtyCibngfListfnfr)
     */
    publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        supfr.bddPropfrtyCibngfListfnfr(listfnfr);
    }

    /**
     * Adds b PropfrtyCibngfListfnfr to tif listfnfr list for b spfdifid
     * propfrty. Tif spfdififd propfrty mby bf usfr-dffinfd, or onf of tif
     * following dffbults:
     * <ul>
     *    <li>tiis Contbinfr's font ("font")</li>
     *    <li>tiis Contbinfr's bbdkground dolor ("bbdkground")</li>
     *    <li>tiis Contbinfr's forfground dolor ("forfground")</li>
     *    <li>tiis Contbinfr's fodusbbility ("fodusbblf")</li>
     *    <li>tiis Contbinfr's fodus trbvfrsbl kfys fnbblfd stbtf
     *        ("fodusTrbvfrsblKfysEnbblfd")</li>
     *    <li>tiis Contbinfr's Sft of FORWARD_TRAVERSAL_KEYS
     *        ("forwbrdFodusTrbvfrsblKfys")</li>
     *    <li>tiis Contbinfr's Sft of BACKWARD_TRAVERSAL_KEYS
     *        ("bbdkwbrdFodusTrbvfrsblKfys")</li>
     *    <li>tiis Contbinfr's Sft of UP_CYCLE_TRAVERSAL_KEYS
     *        ("upCydlfFodusTrbvfrsblKfys")</li>
     *    <li>tiis Contbinfr's Sft of DOWN_CYCLE_TRAVERSAL_KEYS
     *        ("downCydlfFodusTrbvfrsblKfys")</li>
     *    <li>tiis Contbinfr's fodus trbvfrsbl polidy ("fodusTrbvfrsblPolidy")
     *        </li>
     *    <li>tiis Contbinfr's fodus-dydlf-root stbtf ("fodusCydlfRoot")</li>
     *    <li>tiis Contbinfr's fodus-trbvfrsbl-polidy-providfr stbtf("fodusTrbvfrsblPolidyProvidfr")</li>
     *    <li>tiis Contbinfr's fodus-trbvfrsbl-polidy-providfr stbtf("fodusTrbvfrsblPolidyProvidfr")</li>
     * </ul>
     * Notf tibt if tiis Contbinfr is inifriting b bound propfrty, tifn no
     * fvfnt will bf firfd in rfsponsf to b dibngf in tif inifritfd propfrty.
     * <p>
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm propfrtyNbmf onf of tif propfrty nbmfs listfd bbovf
     * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf bddfd
     *
     * @sff #bddPropfrtyCibngfListfnfr(jbvb.bfbns.PropfrtyCibngfListfnfr)
     * @sff Componfnt#rfmovfPropfrtyCibngfListfnfr
     */
    publid void bddPropfrtyCibngfListfnfr(String propfrtyNbmf,
                                          PropfrtyCibngfListfnfr listfnfr) {
        supfr.bddPropfrtyCibngfListfnfr(propfrtyNbmf, listfnfr);
    }

    // Sfriblizbtion support. A Contbinfr is rfsponsiblf for rfstoring tif
    // pbrfnt fiflds of its domponfnt diildrfn.

    /**
     * Contbinfr Sfribl Dbtb Vfrsion.
     */
    privbtf int dontbinfrSfriblizfdDbtbVfrsion = 1;

    /**
     * Sfriblizfs tiis <dodf>Contbinfr</dodf> to tif spfdififd
     * <dodf>ObjfdtOutputStrfbm</dodf>.
     * <ul>
     *    <li>Writfs dffbult sfriblizbblf fiflds to tif strfbm.</li>
     *    <li>Writfs b list of sfriblizbblf ContbinfrListfnfr(s) bs optionbl
     *        dbtb. Tif non-sfriblizbblf ContbinfrListnfr(s) brf dftfdtfd bnd
     *        no bttfmpt is mbdf to sfriblizf tifm.</li>
     *    <li>Writf tiis Contbinfr's FodusTrbvfrsblPolidy if bnd only if it
     *        is Sfriblizbblf; otifrwisf, <dodf>null</dodf> is writtfn.</li>
     * </ul>
     *
     * @pbrbm s tif <dodf>ObjfdtOutputStrfbm</dodf> to writf
     * @sfriblDbtb <dodf>null</dodf> tfrminbtfd sfqufndf of 0 or morf pbirs;
     *   tif pbir donsists of b <dodf>String</dodf> bnd <dodf>Objfdt</dodf>;
     *   tif <dodf>String</dodf> indidbtfs tif typf of objfdt bnd
     *   is onf of tif following:
     *   <dodf>dontbinfrListfnfrK</dodf> indidbting bn
     *     <dodf>ContbinfrListfnfr</dodf> objfdt;
     *   tif <dodf>Contbinfr</dodf>'s <dodf>FodusTrbvfrsblPolidy</dodf>,
     *     or <dodf>null</dodf>
     *
     * @sff AWTEvfntMultidbstfr#sbvf(jbvb.io.ObjfdtOutputStrfbm, jbvb.lbng.String, jbvb.util.EvfntListfnfr)
     * @sff Contbinfr#dontbinfrListfnfrK
     * @sff #rfbdObjfdt(ObjfdtInputStrfbm)
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        ObjfdtOutputStrfbm.PutFifld f = s.putFiflds();
        f.put("ndomponfnts", domponfnt.sizf());
        f.put("domponfnt", gftComponfntsSynd());
        f.put("lbyoutMgr", lbyoutMgr);
        f.put("dispbtdifr", dispbtdifr);
        f.put("mbxSizf", mbxSizf);
        f.put("fodusCydlfRoot", fodusCydlfRoot);
        f.put("dontbinfrSfriblizfdDbtbVfrsion", dontbinfrSfriblizfdDbtbVfrsion);
        f.put("fodusTrbvfrsblPolidyProvidfr", fodusTrbvfrsblPolidyProvidfr);
        s.writfFiflds();

        AWTEvfntMultidbstfr.sbvf(s, dontbinfrListfnfrK, dontbinfrListfnfr);
        s.writfObjfdt(null);

        if (fodusTrbvfrsblPolidy instbndfof jbvb.io.Sfriblizbblf) {
            s.writfObjfdt(fodusTrbvfrsblPolidy);
        } flsf {
            s.writfObjfdt(null);
        }
    }

    /**
     * Dfsfriblizfs tiis <dodf>Contbinfr</dodf> from tif spfdififd
     * <dodf>ObjfdtInputStrfbm</dodf>.
     * <ul>
     *    <li>Rfbds dffbult sfriblizbblf fiflds from tif strfbm.</li>
     *    <li>Rfbds b list of sfriblizbblf ContbinfrListfnfr(s) bs optionbl
     *        dbtb. If tif list is null, no Listfnfrs brf instbllfd.</li>
     *    <li>Rfbds tiis Contbinfr's FodusTrbvfrsblPolidy, wiidi mby bf null,
     *        bs optionbl dbtb.</li>
     * </ul>
     *
     * @pbrbm s tif <dodf>ObjfdtInputStrfbm</dodf> to rfbd
     * @sfribl
     * @sff #bddContbinfrListfnfr
     * @sff #writfObjfdt(ObjfdtOutputStrfbm)
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption
    {
        ObjfdtInputStrfbm.GftFifld f = s.rfbdFiflds();
        Componfnt [] tmpComponfnt = (Componfnt[])f.gft("domponfnt", EMPTY_ARRAY);
        int ndomponfnts = (Intfgfr) f.gft("ndomponfnts", 0);
        domponfnt = nfw jbvb.util.ArrbyList<Componfnt>(ndomponfnts);
        for (int i = 0; i < ndomponfnts; ++i) {
            domponfnt.bdd(tmpComponfnt[i]);
        }
        lbyoutMgr = (LbyoutMbnbgfr)f.gft("lbyoutMgr", null);
        dispbtdifr = (LigitwfigitDispbtdifr)f.gft("dispbtdifr", null);
        // Old strfbm. Dofsn't dontbin mbxSizf bmong Componfnt's fiflds.
        if (mbxSizf == null) {
            mbxSizf = (Dimfnsion)f.gft("mbxSizf", null);
        }
        fodusCydlfRoot = f.gft("fodusCydlfRoot", fblsf);
        dontbinfrSfriblizfdDbtbVfrsion = f.gft("dontbinfrSfriblizfdDbtbVfrsion", 1);
        fodusTrbvfrsblPolidyProvidfr = f.gft("fodusTrbvfrsblPolidyProvidfr", fblsf);
        jbvb.util.List<Componfnt> domponfnt = tiis.domponfnt;
        for(Componfnt domp : domponfnt) {
            domp.pbrfnt = tiis;
            bdjustListfningCiildrfn(AWTEvfnt.HIERARCHY_EVENT_MASK,
                                    domp.numListfning(AWTEvfnt.HIERARCHY_EVENT_MASK));
            bdjustListfningCiildrfn(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK,
                                    domp.numListfning(AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK));
            bdjustDfsdfndbnts(domp.dountHifrbrdiyMfmbfrs());
        }

        Objfdt kfyOrNull;
        wiilf(null != (kfyOrNull = s.rfbdObjfdt())) {
            String kfy = ((String)kfyOrNull).intfrn();

            if (dontbinfrListfnfrK == kfy) {
                bddContbinfrListfnfr((ContbinfrListfnfr)(s.rfbdObjfdt()));
            } flsf {
                // skip vbluf for unrfdognizfd kfy
                s.rfbdObjfdt();
            }
        }

        try {
            Objfdt polidy = s.rfbdObjfdt();
            if (polidy instbndfof FodusTrbvfrsblPolidy) {
                fodusTrbvfrsblPolidy = (FodusTrbvfrsblPolidy)polidy;
            }
        } dbtdi (jbvb.io.OptionblDbtbExdfption f) {
            // JDK 1.1/1.2/1.3 instbndfs will not ibvf tiis optionbl dbtb.
            // f.fof will bf truf to indidbtf tibt tifrf is no morf dbtb
            // bvbilbblf for tiis objfdt. If f.fof is not truf, tirow tif
            // fxdfption bs it migit ibvf bffn dbusfd by rfbsons unrflbtfd to
            // fodusTrbvfrsblPolidy.

            if (!f.fof) {
                tirow f;
            }
        }
    }

    /*
     * --- Addfssibility Support ---
     */

    /**
     * Innfr dlbss of Contbinfr usfd to providf dffbult support for
     * bddfssibility.  Tiis dlbss is not mfbnt to bf usfd dirfdtly by
     * bpplidbtion dfvflopfrs, but is instfbd mfbnt only to bf
     * subdlbssfd by dontbinfr dfvflopfrs.
     * <p>
     * Tif dlbss usfd to obtbin tif bddfssiblf rolf for tiis objfdt,
     * bs wfll bs implfmfnting mbny of tif mftiods in tif
     * AddfssiblfContbinfr intfrfbdf.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTContbinfr fxtfnds AddfssiblfAWTComponfnt {

        /**
         * JDK1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = 5081320404842566097L;

        /**
         * Rfturns tif numbfr of bddfssiblf diildrfn in tif objfdt.  If bll
         * of tif diildrfn of tiis objfdt implfmfnt <dodf>Addfssiblf</dodf>,
         * tifn tiis mftiod siould rfturn tif numbfr of diildrfn of tiis objfdt.
         *
         * @rfturn tif numbfr of bddfssiblf diildrfn in tif objfdt
         */
        publid int gftAddfssiblfCiildrfnCount() {
            rfturn Contbinfr.tiis.gftAddfssiblfCiildrfnCount();
        }

        /**
         * Rfturns tif nti <dodf>Addfssiblf</dodf> diild of tif objfdt.
         *
         * @pbrbm i zfro-bbsfd indfx of diild
         * @rfturn tif nti <dodf>Addfssiblf</dodf> diild of tif objfdt
         */
        publid Addfssiblf gftAddfssiblfCiild(int i) {
            rfturn Contbinfr.tiis.gftAddfssiblfCiild(i);
        }

        /**
         * Rfturns tif <dodf>Addfssiblf</dodf> diild, if onf fxists,
         * dontbinfd bt tif lodbl doordinbtf <dodf>Point</dodf>.
         *
         * @pbrbm p tif point dffining tif top-lfft dornfr of tif
         *    <dodf>Addfssiblf</dodf>, givfn in tif doordinbtf spbdf
         *    of tif objfdt's pbrfnt
         * @rfturn tif <dodf>Addfssiblf</dodf>, if it fxists,
         *    bt tif spfdififd lodbtion; flsf <dodf>null</dodf>
         */
        publid Addfssiblf gftAddfssiblfAt(Point p) {
            rfturn Contbinfr.tiis.gftAddfssiblfAt(p);
        }

        /**
         * Numbfr of PropfrtyCibngfListfnfr objfdts rfgistfrfd. It's usfd
         * to bdd/rfmovf ContbinfrListfnfr to trbdk tbrgft Contbinfr's stbtf.
         */
        privbtf volbtilf trbnsifnt int propfrtyListfnfrsCount = 0;

        /**
         * Tif ibndlfr to firf {@dodf PropfrtyCibngf}
         * wifn diildrfn brf bddfd or rfmovfd
         */
        protfdtfd ContbinfrListfnfr bddfssiblfContbinfrHbndlfr = null;

        /**
         * Firf <dodf>PropfrtyCibngf</dodf> listfnfr, if onf is rfgistfrfd,
         * wifn diildrfn brf bddfd or rfmovfd.
         * @sindf 1.3
         */
        protfdtfd dlbss AddfssiblfContbinfrHbndlfr
            implfmfnts ContbinfrListfnfr {
            publid void domponfntAddfd(ContbinfrEvfnt f) {
                Componfnt d = f.gftCiild();
                if (d != null && d instbndfof Addfssiblf) {
                    AddfssiblfAWTContbinfr.tiis.firfPropfrtyCibngf(
                        AddfssiblfContfxt.ACCESSIBLE_CHILD_PROPERTY,
                        null, ((Addfssiblf) d).gftAddfssiblfContfxt());
                }
            }
            publid void domponfntRfmovfd(ContbinfrEvfnt f) {
                Componfnt d = f.gftCiild();
                if (d != null && d instbndfof Addfssiblf) {
                    AddfssiblfAWTContbinfr.tiis.firfPropfrtyCibngf(
                        AddfssiblfContfxt.ACCESSIBLE_CHILD_PROPERTY,
                        ((Addfssiblf) d).gftAddfssiblfContfxt(), null);
                }
            }
        }

        /**
         * Adds b PropfrtyCibngfListfnfr to tif listfnfr list.
         *
         * @pbrbm listfnfr  tif PropfrtyCibngfListfnfr to bf bddfd
         */
        publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
            if (bddfssiblfContbinfrHbndlfr == null) {
                bddfssiblfContbinfrHbndlfr = nfw AddfssiblfContbinfrHbndlfr();
            }
            if (propfrtyListfnfrsCount++ == 0) {
                Contbinfr.tiis.bddContbinfrListfnfr(bddfssiblfContbinfrHbndlfr);
            }
            supfr.bddPropfrtyCibngfListfnfr(listfnfr);
        }

        /**
         * Rfmovf b PropfrtyCibngfListfnfr from tif listfnfr list.
         * Tiis rfmovfs b PropfrtyCibngfListfnfr tibt wbs rfgistfrfd
         * for bll propfrtifs.
         *
         * @pbrbm listfnfr tif PropfrtyCibngfListfnfr to bf rfmovfd
         */
        publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
            if (--propfrtyListfnfrsCount == 0) {
                Contbinfr.tiis.rfmovfContbinfrListfnfr(bddfssiblfContbinfrHbndlfr);
            }
            supfr.rfmovfPropfrtyCibngfListfnfr(listfnfr);
        }

    } // innfr dlbss AddfssiblfAWTContbinfr

    /**
     * Rfturns tif <dodf>Addfssiblf</dodf> diild dontbinfd bt tif lodbl
     * doordinbtf <dodf>Point</dodf>, if onf fxists.  Otifrwisf
     * rfturns <dodf>null</dodf>.
     *
     * @pbrbm p tif point dffining tif top-lfft dornfr of tif
     *    <dodf>Addfssiblf</dodf>, givfn in tif doordinbtf spbdf
     *    of tif objfdt's pbrfnt
     * @rfturn tif <dodf>Addfssiblf</dodf> bt tif spfdififd lodbtion,
     *    if it fxists; otifrwisf <dodf>null</dodf>
     */
    Addfssiblf gftAddfssiblfAt(Point p) {
        syndironizfd (gftTrffLodk()) {
            if (tiis instbndfof Addfssiblf) {
                Addfssiblf b = (Addfssiblf)tiis;
                AddfssiblfContfxt bd = b.gftAddfssiblfContfxt();
                if (bd != null) {
                    AddfssiblfComponfnt bdmp;
                    Point lodbtion;
                    int ndiildrfn = bd.gftAddfssiblfCiildrfnCount();
                    for (int i=0; i < ndiildrfn; i++) {
                        b = bd.gftAddfssiblfCiild(i);
                        if ((b != null)) {
                            bd = b.gftAddfssiblfContfxt();
                            if (bd != null) {
                                bdmp = bd.gftAddfssiblfComponfnt();
                                if ((bdmp != null) && (bdmp.isSiowing())) {
                                    lodbtion = bdmp.gftLodbtion();
                                    Point np = nfw Point(p.x-lodbtion.x,
                                                         p.y-lodbtion.y);
                                    if (bdmp.dontbins(np)){
                                        rfturn b;
                                    }
                                }
                            }
                        }
                    }
                }
                rfturn (Addfssiblf)tiis;
            } flsf {
                Componfnt rft = tiis;
                if (!tiis.dontbins(p.x,p.y)) {
                    rft = null;
                } flsf {
                    int ndomponfnts = tiis.gftComponfntCount();
                    for (int i=0; i < ndomponfnts; i++) {
                        Componfnt domp = tiis.gftComponfnt(i);
                        if ((domp != null) && domp.isSiowing()) {
                            Point lodbtion = domp.gftLodbtion();
                            if (domp.dontbins(p.x-lodbtion.x,p.y-lodbtion.y)) {
                                rft = domp;
                            }
                        }
                    }
                }
                if (rft instbndfof Addfssiblf) {
                    rfturn (Addfssiblf) rft;
                }
            }
            rfturn null;
        }
    }

    /**
     * Rfturns tif numbfr of bddfssiblf diildrfn in tif objfdt.  If bll
     * of tif diildrfn of tiis objfdt implfmfnt <dodf>Addfssiblf</dodf>,
     * tifn tiis mftiod siould rfturn tif numbfr of diildrfn of tiis objfdt.
     *
     * @rfturn tif numbfr of bddfssiblf diildrfn in tif objfdt
     */
    int gftAddfssiblfCiildrfnCount() {
        syndironizfd (gftTrffLodk()) {
            int dount = 0;
            Componfnt[] diildrfn = tiis.gftComponfnts();
            for (int i = 0; i < diildrfn.lfngti; i++) {
                if (diildrfn[i] instbndfof Addfssiblf) {
                    dount++;
                }
            }
            rfturn dount;
        }
    }

    /**
     * Rfturns tif nti <dodf>Addfssiblf</dodf> diild of tif objfdt.
     *
     * @pbrbm i zfro-bbsfd indfx of diild
     * @rfturn tif nti <dodf>Addfssiblf</dodf> diild of tif objfdt
     */
    Addfssiblf gftAddfssiblfCiild(int i) {
        syndironizfd (gftTrffLodk()) {
            Componfnt[] diildrfn = tiis.gftComponfnts();
            int dount = 0;
            for (int j = 0; j < diildrfn.lfngti; j++) {
                if (diildrfn[j] instbndfof Addfssiblf) {
                    if (dount == i) {
                        rfturn (Addfssiblf) diildrfn[j];
                    } flsf {
                        dount++;
                    }
                }
            }
            rfturn null;
        }
    }

    // ************************** MIXING CODE *******************************

    finbl void indrfbsfComponfntCount(Componfnt d) {
        syndironizfd (gftTrffLodk()) {
            if (!d.isDisplbybblf()) {
                tirow nfw IllfgblStbtfExdfption(
                    "Pffr dofs not fxist wiilf invoking tif indrfbsfComponfntCount() mftiod"
                );
            }

            int bddHW = 0;
            int bddLW = 0;

            if (d instbndfof Contbinfr) {
                bddLW = ((Contbinfr)d).numOfLWComponfnts;
                bddHW = ((Contbinfr)d).numOfHWComponfnts;
            }
            if (d.isLigitwfigit()) {
                bddLW++;
            } flsf {
                bddHW++;
            }

            for (Contbinfr dont = tiis; dont != null; dont = dont.gftContbinfr()) {
                dont.numOfLWComponfnts += bddLW;
                dont.numOfHWComponfnts += bddHW;
            }
        }
    }

    finbl void dfdrfbsfComponfntCount(Componfnt d) {
        syndironizfd (gftTrffLodk()) {
            if (!d.isDisplbybblf()) {
                tirow nfw IllfgblStbtfExdfption(
                    "Pffr dofs not fxist wiilf invoking tif dfdrfbsfComponfntCount() mftiod"
                );
            }

            int subHW = 0;
            int subLW = 0;

            if (d instbndfof Contbinfr) {
                subLW = ((Contbinfr)d).numOfLWComponfnts;
                subHW = ((Contbinfr)d).numOfHWComponfnts;
            }
            if (d.isLigitwfigit()) {
                subLW++;
            } flsf {
                subHW++;
            }

            for (Contbinfr dont = tiis; dont != null; dont = dont.gftContbinfr()) {
                dont.numOfLWComponfnts -= subLW;
                dont.numOfHWComponfnts -= subHW;
            }
        }
    }

    privbtf int gftTopmostComponfntIndfx() {
        difdkTrffLodk();
        if (gftComponfntCount() > 0) {
            rfturn 0;
        }
        rfturn -1;
    }

    privbtf int gftBottommostComponfntIndfx() {
        difdkTrffLodk();
        if (gftComponfntCount() > 0) {
            rfturn gftComponfntCount() - 1;
        }
        rfturn -1;
    }

    /*
     * Tiis mftiod is ovfrridfn to ibndlf opbquf diildrfn in non-opbquf
     * dontbinfrs.
     */
    @Ovfrridf
    finbl Rfgion gftOpbqufSibpf() {
        difdkTrffLodk();
        if (isLigitwfigit() && isNonOpbqufForMixing()
                && ibsLigitwfigitDfsdfndbnts())
        {
            Rfgion s = Rfgion.EMPTY_REGION;
            for (int indfx = 0; indfx < gftComponfntCount(); indfx++) {
                Componfnt d = gftComponfnt(indfx);
                if (d.isLigitwfigit() && d.isSiowing()) {
                    s = s.gftUnion(d.gftOpbqufSibpf());
                }
            }
            rfturn s.gftIntfrsfdtion(gftNormblSibpf());
        }
        rfturn supfr.gftOpbqufSibpf();
    }

    finbl void rfdursivfSubtrbdtAndApplySibpf(Rfgion sibpf) {
        rfdursivfSubtrbdtAndApplySibpf(sibpf, gftTopmostComponfntIndfx(), gftBottommostComponfntIndfx());
    }

    finbl void rfdursivfSubtrbdtAndApplySibpf(Rfgion sibpf, int fromZordfr) {
        rfdursivfSubtrbdtAndApplySibpf(sibpf, fromZordfr, gftBottommostComponfntIndfx());
    }

    finbl void rfdursivfSubtrbdtAndApplySibpf(Rfgion sibpf, int fromZordfr, int toZordfr) {
        difdkTrffLodk();
        if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            mixingLog.finf("tiis = " + tiis +
                "; sibpf=" + sibpf + "; fromZ=" + fromZordfr + "; toZ=" + toZordfr);
        }
        if (fromZordfr == -1) {
            rfturn;
        }
        if (sibpf.isEmpty()) {
            rfturn;
        }
        // An invblid dontbinfr witi not-null lbyout siould bf ignorfd
        // by tif mixing dodf, tif dontbinfr will bf vblidbtfd lbtfr
        // bnd tif mixing dodf will bf fxfdutfd lbtfr.
        if (gftLbyout() != null && !isVblid()) {
            rfturn;
        }
        for (int indfx = fromZordfr; indfx <= toZordfr; indfx++) {
            Componfnt domp = gftComponfnt(indfx);
            if (!domp.isLigitwfigit()) {
                domp.subtrbdtAndApplySibpf(sibpf);
            } flsf if (domp instbndfof Contbinfr &&
                    ((Contbinfr)domp).ibsHfbvywfigitDfsdfndbnts() && domp.isSiowing()) {
                ((Contbinfr)domp).rfdursivfSubtrbdtAndApplySibpf(sibpf);
            }
        }
    }

    finbl void rfdursivfApplyCurrfntSibpf() {
        rfdursivfApplyCurrfntSibpf(gftTopmostComponfntIndfx(), gftBottommostComponfntIndfx());
    }

    finbl void rfdursivfApplyCurrfntSibpf(int fromZordfr) {
        rfdursivfApplyCurrfntSibpf(fromZordfr, gftBottommostComponfntIndfx());
    }

    finbl void rfdursivfApplyCurrfntSibpf(int fromZordfr, int toZordfr) {
        difdkTrffLodk();
        if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            mixingLog.finf("tiis = " + tiis +
                "; fromZ=" + fromZordfr + "; toZ=" + toZordfr);
        }
        if (fromZordfr == -1) {
            rfturn;
        }
        // An invblid dontbinfr witi not-null lbyout siould bf ignorfd
        // by tif mixing dodf, tif dontbinfr will bf vblidbtfd lbtfr
        // bnd tif mixing dodf will bf fxfdutfd lbtfr.
        if (gftLbyout() != null && !isVblid()) {
            rfturn;
        }
        for (int indfx = fromZordfr; indfx <= toZordfr; indfx++) {
            Componfnt domp = gftComponfnt(indfx);
            if (!domp.isLigitwfigit()) {
                domp.bpplyCurrfntSibpf();
            }
            if (domp instbndfof Contbinfr &&
                    ((Contbinfr)domp).ibsHfbvywfigitDfsdfndbnts()) {
                ((Contbinfr)domp).rfdursivfApplyCurrfntSibpf();
            }
        }
    }

    privbtf void rfdursivfSiowHfbvywfigitCiildrfn() {
        if (!ibsHfbvywfigitDfsdfndbnts() || !isVisiblf()) {
            rfturn;
        }
        for (int indfx = 0; indfx < gftComponfntCount(); indfx++) {
            Componfnt domp = gftComponfnt(indfx);
            if (domp.isLigitwfigit()) {
                if  (domp instbndfof Contbinfr) {
                    ((Contbinfr)domp).rfdursivfSiowHfbvywfigitCiildrfn();
                }
            } flsf {
                if (domp.isVisiblf()) {
                    ComponfntPffr pffr = domp.gftPffr();
                    if (pffr != null) {
                        pffr.sftVisiblf(truf);
                    }
                }
            }
        }
    }

    privbtf void rfdursivfHidfHfbvywfigitCiildrfn() {
        if (!ibsHfbvywfigitDfsdfndbnts()) {
            rfturn;
        }
        for (int indfx = 0; indfx < gftComponfntCount(); indfx++) {
            Componfnt domp = gftComponfnt(indfx);
            if (domp.isLigitwfigit()) {
                if  (domp instbndfof Contbinfr) {
                    ((Contbinfr)domp).rfdursivfHidfHfbvywfigitCiildrfn();
                }
            } flsf {
                if (domp.isVisiblf()) {
                    ComponfntPffr pffr = domp.gftPffr();
                    if (pffr != null) {
                        pffr.sftVisiblf(fblsf);
                    }
                }
            }
        }
    }

    privbtf void rfdursivfRflodbtfHfbvywfigitCiildrfn(Point origin) {
        for (int indfx = 0; indfx < gftComponfntCount(); indfx++) {
            Componfnt domp = gftComponfnt(indfx);
            if (domp.isLigitwfigit()) {
                if  (domp instbndfof Contbinfr &&
                        ((Contbinfr)domp).ibsHfbvywfigitDfsdfndbnts())
                {
                    finbl Point nfwOrigin = nfw Point(origin);
                    nfwOrigin.trbnslbtf(domp.gftX(), domp.gftY());
                    ((Contbinfr)domp).rfdursivfRflodbtfHfbvywfigitCiildrfn(nfwOrigin);
                }
            } flsf {
                ComponfntPffr pffr = domp.gftPffr();
                if (pffr != null) {
                    pffr.sftBounds(origin.x + domp.gftX(), origin.y + domp.gftY(),
                            domp.gftWidti(), domp.gftHfigit(),
                            ComponfntPffr.SET_LOCATION);
                }
            }
        }
    }

    /**
     * Cifdks if tif dontbinfr bnd its dirfdt ligitwfigit dontbinfrs brf
     * visiblf.
     *
     * Considfr tif ifbvywfigit dontbinfr iidfs or siows tif HW dfsdfndbnts
     * butombtidblly. Tifrfforf wf dbrf of LW dontbinfrs' visibility only.
     *
     * Tiis mftiod MUST bf invokfd undfr tif TrffLodk.
     */
    finbl boolfbn isRfdursivflyVisiblfUpToHfbvywfigitContbinfr() {
        if (!isLigitwfigit()) {
            rfturn truf;
        }

        for (Contbinfr dont = tiis;
                dont != null && dont.isLigitwfigit();
                dont = dont.gftContbinfr())
        {
            if (!dont.isVisiblf()) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    @Ovfrridf
    void mixOnSiowing() {
        syndironizfd (gftTrffLodk()) {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis);
            }

            boolfbn isLigitwfigit = isLigitwfigit();

            if (isLigitwfigit && isRfdursivflyVisiblfUpToHfbvywfigitContbinfr()) {
                rfdursivfSiowHfbvywfigitCiildrfn();
            }

            if (!isMixingNffdfd()) {
                rfturn;
            }

            if (!isLigitwfigit || (isLigitwfigit && ibsHfbvywfigitDfsdfndbnts())) {
                rfdursivfApplyCurrfntSibpf();
            }

            supfr.mixOnSiowing();
        }
    }

    @Ovfrridf
    void mixOnHiding(boolfbn isLigitwfigit) {
        syndironizfd (gftTrffLodk()) {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis +
                        "; isLigitwfigit=" + isLigitwfigit);
            }
            if (isLigitwfigit) {
                rfdursivfHidfHfbvywfigitCiildrfn();
            }
            supfr.mixOnHiding(isLigitwfigit);
        }
    }

    @Ovfrridf
    void mixOnRfsibping() {
        syndironizfd (gftTrffLodk()) {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis);
            }

            boolfbn isMixingNffdfd = isMixingNffdfd();

            if (isLigitwfigit() && ibsHfbvywfigitDfsdfndbnts()) {
                finbl Point origin = nfw Point(gftX(), gftY());
                for (Contbinfr dont = gftContbinfr();
                        dont != null && dont.isLigitwfigit();
                        dont = dont.gftContbinfr())
                {
                    origin.trbnslbtf(dont.gftX(), dont.gftY());
                }

                rfdursivfRflodbtfHfbvywfigitCiildrfn(origin);

                if (!isMixingNffdfd) {
                    rfturn;
                }

                rfdursivfApplyCurrfntSibpf();
            }

            if (!isMixingNffdfd) {
                rfturn;
            }

            supfr.mixOnRfsibping();
        }
    }

    @Ovfrridf
    void mixOnZOrdfrCibnging(int oldZordfr, int nfwZordfr) {
        syndironizfd (gftTrffLodk()) {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis +
                    "; oldZ=" + oldZordfr + "; nfwZ=" + nfwZordfr);
            }

            if (!isMixingNffdfd()) {
                rfturn;
            }

            boolfbn bfdbmfHigifr = nfwZordfr < oldZordfr;

            if (bfdbmfHigifr && isLigitwfigit() && ibsHfbvywfigitDfsdfndbnts()) {
                rfdursivfApplyCurrfntSibpf();
            }
            supfr.mixOnZOrdfrCibnging(oldZordfr, nfwZordfr);
        }
    }

    @Ovfrridf
    void mixOnVblidbting() {
        syndironizfd (gftTrffLodk()) {
            if (mixingLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                mixingLog.finf("tiis = " + tiis);
            }

            if (!isMixingNffdfd()) {
                rfturn;
            }

            if (ibsHfbvywfigitDfsdfndbnts()) {
                rfdursivfApplyCurrfntSibpf();
            }

            if (isLigitwfigit() && isNonOpbqufForMixing()) {
                subtrbdtAndApplySibpfBflowMf();
            }

            supfr.mixOnVblidbting();
        }
    }

    // ****************** END OF MIXING CODE ********************************
}


/**
 * Clbss to mbnbgf tif dispbtdiing of MousfEvfnts to tif ligitwfigit dfsdfndbnts
 * bnd SunDropTbrgftEvfnts to boti ligitwfigit bnd ifbvywfigit dfsdfndbnts
 * dontbinfd by b nbtivf dontbinfr.
 *
 * NOTE: tif dlbss nbmf is not bppropribtf bnymorf, but wf dbnnot dibngf it
 * bfdbusf wf must kffp sfriblizbtion dompbtibility.
 *
 * @butior Timotiy Prinzing
 */
dlbss LigitwfigitDispbtdifr implfmfnts jbvb.io.Sfriblizbblf, AWTEvfntListfnfr {

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 5184291520170872969L;
    /*
     * Our own mousf fvfnt for wifn wf'rf drbggfd ovfr from bnotifr iw
     * dontbinfr
     */
    privbtf stbtid finbl int  LWD_MOUSE_DRAGGED_OVER = 1500;

    privbtf stbtid finbl PlbtformLoggfr fvfntLog = PlbtformLoggfr.gftLoggfr("jbvb.bwt.fvfnt.LigitwfigitDispbtdifr");

    LigitwfigitDispbtdifr(Contbinfr nbtivfContbinfr) {
        tiis.nbtivfContbinfr = nbtivfContbinfr;
        mousfEvfntTbrgft = null;
        fvfntMbsk = 0;
    }

    /*
     * Clfbn up bny rfsourdfs bllodbtfd wifn dispbtdifr wbs drfbtfd;
     * siould bf dbllfd from Contbinfr.rfmovfNotify
     */
    void disposf() {
        //Systfm.out.println("Disposing lw dispbtdifr");
        stopListfningForOtifrDrbgs();
        mousfEvfntTbrgft = null;
        tbrgftLbstEntfrfd = null;
        tbrgftLbstEntfrfdDT = null;
    }

    /**
     * Enbblfs fvfnts to subdomponfnts.
     */
    void fnbblfEvfnts(long fvfnts) {
        fvfntMbsk |= fvfnts;
    }

    /**
     * Dispbtdifs bn fvfnt to b sub-domponfnt if nfdfssbry, bnd
     * rfturns wiftifr or not tif fvfnt wbs forwbrdfd to b
     * sub-domponfnt.
     *
     * @pbrbm f tif fvfnt
     */
    boolfbn dispbtdiEvfnt(AWTEvfnt f) {
        boolfbn rft = fblsf;

        /*
         * Fix for BugTrbq Id 4389284.
         * Dispbtdi SunDropTbrgftEvfnts rfgbrdlfss of fvfntMbsk vbluf.
         * Do not updbtf dursor on dispbtdiing SunDropTbrgftEvfnts.
         */
        if (f instbndfof SunDropTbrgftEvfnt) {

            SunDropTbrgftEvfnt sddf = (SunDropTbrgftEvfnt) f;
            rft = prodfssDropTbrgftEvfnt(sddf);

        } flsf {
            if (f instbndfof MousfEvfnt && (fvfntMbsk & MOUSE_MASK) != 0) {
                MousfEvfnt mf = (MousfEvfnt) f;
                rft = prodfssMousfEvfnt(mf);
            }

            if (f.gftID() == MousfEvfnt.MOUSE_MOVED) {
                nbtivfContbinfr.updbtfCursorImmfdibtfly();
            }
        }

        rfturn rft;
    }

    /* Tiis mftiod ffffdtivfly rfturns wiftifr or not b mousf button wbs down
     * just BEFORE tif fvfnt ibppfnfd.  A bfttfr mftiod nbmf migit bf
     * wbsAMousfButtonDownBfforfTiisEvfnt().
     */
    privbtf boolfbn isMousfGrbb(MousfEvfnt f) {
        int modififrs = f.gftModififrsEx();

        if(f.gftID() == MousfEvfnt.MOUSE_PRESSED
            || f.gftID() == MousfEvfnt.MOUSE_RELEASED)
        {
            switdi (f.gftButton()) {
            dbsf MousfEvfnt.BUTTON1:
                modififrs ^= InputEvfnt.BUTTON1_DOWN_MASK;
                brfbk;
            dbsf MousfEvfnt.BUTTON2:
                modififrs ^= InputEvfnt.BUTTON2_DOWN_MASK;
                brfbk;
            dbsf MousfEvfnt.BUTTON3:
                modififrs ^= InputEvfnt.BUTTON3_DOWN_MASK;
                brfbk;
            }
        }
        /* modififrs now bs just bfforf fvfnt */
        rfturn ((modififrs & (InputEvfnt.BUTTON1_DOWN_MASK
                              | InputEvfnt.BUTTON2_DOWN_MASK
                              | InputEvfnt.BUTTON3_DOWN_MASK)) != 0);
    }

    /**
     * Tiis mftiod bttfmpts to distributf b mousf fvfnt to b ligitwfigit
     * domponfnt.  It trifs to bvoid doing bny unnfdfssbry probfs down
     * into tif domponfnt trff to minimizf tif ovfrifbd of dftfrmining
     * wifrf to routf tif fvfnt, sindf mousf movfmfnt fvfnts tfnd to
     * domf in lbrgf bnd frfqufnt bmounts.
     */
    privbtf boolfbn prodfssMousfEvfnt(MousfEvfnt f) {
        int id = f.gftID();
        Componfnt mousfOvfr =   // sfnsitivf to mousf fvfnts
            nbtivfContbinfr.gftMousfEvfntTbrgft(f.gftX(), f.gftY(),
                                                Contbinfr.INCLUDE_SELF);

        trbdkMousfEntfrExit(mousfOvfr, f);

    // 4508327 : MOUSE_CLICKED siould only go to tif rfdipifnt of
    // tif bddompbnying MOUSE_PRESSED, so don't rfsft mousfEvfntTbrgft on b
    // MOUSE_CLICKED.
    if (!isMousfGrbb(f) && id != MousfEvfnt.MOUSE_CLICKED) {
            mousfEvfntTbrgft = (mousfOvfr != nbtivfContbinfr) ? mousfOvfr: null;
            isClfbnfd = fblsf;
        }

        if (mousfEvfntTbrgft != null) {
            switdi (id) {
            dbsf MousfEvfnt.MOUSE_ENTERED:
            dbsf MousfEvfnt.MOUSE_EXITED:
                brfbk;
            dbsf MousfEvfnt.MOUSE_PRESSED:
                rftbrgftMousfEvfnt(mousfEvfntTbrgft, id, f);
                brfbk;
        dbsf MousfEvfnt.MOUSE_RELEASED:
            rftbrgftMousfEvfnt(mousfEvfntTbrgft, id, f);
        brfbk;
        dbsf MousfEvfnt.MOUSE_CLICKED:
        // 4508327: MOUSE_CLICKED siould nfvfr bf dispbtdifd to b Componfnt
        // otifr tibn tibt wiidi rfdfivfd tif MOUSE_PRESSED fvfnt.  If tif
        // mousf is now ovfr b difffrfnt Componfnt, don't dispbtdi tif fvfnt.
        // Tif prfvious fix for b similbr problfm wbs bssodibtfd witi bug
        // 4155217.
        if (mousfOvfr == mousfEvfntTbrgft) {
            rftbrgftMousfEvfnt(mousfOvfr, id, f);
        }
        brfbk;
            dbsf MousfEvfnt.MOUSE_MOVED:
                rftbrgftMousfEvfnt(mousfEvfntTbrgft, id, f);
                brfbk;
        dbsf MousfEvfnt.MOUSE_DRAGGED:
            if (isMousfGrbb(f)) {
                rftbrgftMousfEvfnt(mousfEvfntTbrgft, id, f);
            }
                brfbk;
        dbsf MousfEvfnt.MOUSE_WHEEL:
            // Tiis mby sfnd it somfwifrf tibt dofsn't ibvf MousfWifflEvfnts
            // fnbblfd.  In tiis dbsf, Componfnt.dispbtdiEvfntImpl() will
            // rftbrgft tif fvfnt to b pbrfnt tibt DOES ibvf tif fvfnts fnbblfd.
            if (fvfntLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST) && (mousfOvfr != null)) {
                fvfntLog.finfst("rftbrgfting mousf wiffl to " +
                                mousfOvfr.gftNbmf() + ", " +
                                mousfOvfr.gftClbss());
            }
            rftbrgftMousfEvfnt(mousfOvfr, id, f);
        brfbk;
            }
        //Consuming of wiffl fvfnts is implfmfntfd in "rftbrgftMousfEvfnt".
        if (id != MousfEvfnt.MOUSE_WHEEL) {
            f.donsumf();
        }
    } flsf if (isClfbnfd && id != MousfEvfnt.MOUSE_WHEEL) {
        //Aftfr mousfEvfntTbrgft wbs rfmovfd bnd dlfbnfd siould donsumf bll fvfnts
        //until nfw mousfEvfntTbrgft is found
        f.donsumf();
    }
    rfturn f.isConsumfd();
    }

    privbtf boolfbn prodfssDropTbrgftEvfnt(SunDropTbrgftEvfnt f) {
        int id = f.gftID();
        int x = f.gftX();
        int y = f.gftY();

        /*
         * Fix for BugTrbq ID 4395290.
         * It is possiblf tibt SunDropTbrgftEvfnt's Point is outsidf of tif
         * nbtivf dontbinfr bounds. In tiis dbsf wf trundbtf doordinbtfs.
         */
        if (!nbtivfContbinfr.dontbins(x, y)) {
            finbl Dimfnsion d = nbtivfContbinfr.gftSizf();
            if (d.widti <= x) {
                x = d.widti - 1;
            } flsf if (x < 0) {
                x = 0;
            }
            if (d.ifigit <= y) {
                y = d.ifigit - 1;
            } flsf if (y < 0) {
                y = 0;
            }
        }
        Componfnt mousfOvfr =   // not nfdfssbrily sfnsitivf to mousf fvfnts
            nbtivfContbinfr.gftDropTbrgftEvfntTbrgft(x, y,
                                                     Contbinfr.INCLUDE_SELF);
        trbdkMousfEntfrExit(mousfOvfr, f);

        if (mousfOvfr != nbtivfContbinfr && mousfOvfr != null) {
            switdi (id) {
            dbsf SunDropTbrgftEvfnt.MOUSE_ENTERED:
            dbsf SunDropTbrgftEvfnt.MOUSE_EXITED:
                brfbk;
            dffbult:
                rftbrgftMousfEvfnt(mousfOvfr, id, f);
                f.donsumf();
                brfbk;
            }
        }
        rfturn f.isConsumfd();
    }

    /*
     * Gfnfrbtfs dnd fntfr/fxit fvfnts bs mousf movfs ovfr lw domponfnts
     * @pbrbm tbrgftOvfr       Tbrgft mousf is ovfr (indluding nbtivf dontbinfr)
     * @pbrbm f                SunDropTbrgft mousf fvfnt in nbtivf dontbinfr
     */
    privbtf void trbdkDropTbrgftEntfrExit(Componfnt tbrgftOvfr, MousfEvfnt f) {
        int id = f.gftID();
        if (id == MousfEvfnt.MOUSE_ENTERED && isMousfDTInNbtivfContbinfr) {
            // Tiis dbn ibppfn if b ligitwfigit domponfnt wiidi initibtfd tif
            // drbg ibs bn bssodibtfd drop tbrgft. MOUSE_ENTERED domfs wifn tif
            // mousf is in tif nbtivf dontbinfr blrfbdy. To propbgbtf tiis fvfnt
            // propfrly wf siould null out tbrgftLbstEntfrfd.
            tbrgftLbstEntfrfdDT = null;
        } flsf if (id == MousfEvfnt.MOUSE_ENTERED) {
            isMousfDTInNbtivfContbinfr = truf;
        } flsf if (id == MousfEvfnt.MOUSE_EXITED) {
            isMousfDTInNbtivfContbinfr = fblsf;
        }
        tbrgftLbstEntfrfdDT = rftbrgftMousfEntfrExit(tbrgftOvfr, f,
                                                     tbrgftLbstEntfrfdDT,
                                                     isMousfDTInNbtivfContbinfr);
    }

    /*
     * Gfnfrbtfs fntfr/fxit fvfnts bs mousf movfs ovfr lw domponfnts
     * @pbrbm tbrgftOvfr        Tbrgft mousf is ovfr (indluding nbtivf dontbinfr)
     * @pbrbm f                 Mousf fvfnt in nbtivf dontbinfr
     */
    privbtf void trbdkMousfEntfrExit(Componfnt tbrgftOvfr, MousfEvfnt f) {
        if (f instbndfof SunDropTbrgftEvfnt) {
            trbdkDropTbrgftEntfrExit(tbrgftOvfr, f);
            rfturn;
        }
        int id = f.gftID();

        if ( id != MousfEvfnt.MOUSE_EXITED &&
             id != MousfEvfnt.MOUSE_DRAGGED &&
             id != LWD_MOUSE_DRAGGED_OVER &&
                !isMousfInNbtivfContbinfr) {
            // bny fvfnt but bn fxit or drbg mfbns wf'rf in tif nbtivf dontbinfr
            isMousfInNbtivfContbinfr = truf;
            stbrtListfningForOtifrDrbgs();
        } flsf if (id == MousfEvfnt.MOUSE_EXITED) {
            isMousfInNbtivfContbinfr = fblsf;
            stopListfningForOtifrDrbgs();
        }
        tbrgftLbstEntfrfd = rftbrgftMousfEntfrExit(tbrgftOvfr, f,
                                                   tbrgftLbstEntfrfd,
                                                   isMousfInNbtivfContbinfr);
    }

    privbtf Componfnt rftbrgftMousfEntfrExit(Componfnt tbrgftOvfr, MousfEvfnt f,
                                             Componfnt lbstEntfrfd,
                                             boolfbn inNbtivfContbinfr) {
        int id = f.gftID();
        Componfnt tbrgftEntfr = inNbtivfContbinfr ? tbrgftOvfr : null;

        if (lbstEntfrfd != tbrgftEntfr) {
            if (lbstEntfrfd != null) {
                rftbrgftMousfEvfnt(lbstEntfrfd, MousfEvfnt.MOUSE_EXITED, f);
            }
            if (id == MousfEvfnt.MOUSE_EXITED) {
                // donsumf nbtivf fxit fvfnt if wf gfnfrbtf onf
                f.donsumf();
            }

            if (tbrgftEntfr != null) {
                rftbrgftMousfEvfnt(tbrgftEntfr, MousfEvfnt.MOUSE_ENTERED, f);
            }
            if (id == MousfEvfnt.MOUSE_ENTERED) {
                // donsumf nbtivf fntfr fvfnt if wf gfnfrbtf onf
                f.donsumf();
            }
        }
        rfturn tbrgftEntfr;
    }

    /*
     * Listfns to globbl mousf drbg fvfnts so fvfn drbgs originbting
     * from otifr ifbvywfigit dontbinfrs will gfnfrbtf fntfr/fxit
     * fvfnts in tiis dontbinfr
     */
    privbtf void stbrtListfningForOtifrDrbgs() {
        //Systfm.out.println("Adding AWTEvfntListfnfr");
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {
                    nbtivfContbinfr.gftToolkit().bddAWTEvfntListfnfr(
                        LigitwfigitDispbtdifr.tiis,
                        AWTEvfnt.MOUSE_EVENT_MASK |
                        AWTEvfnt.MOUSE_MOTION_EVENT_MASK);
                    rfturn null;
                }
            }
        );
    }

    privbtf void stopListfningForOtifrDrbgs() {
        //Systfm.out.println("Rfmoving AWTEvfntListfnfr");
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {
                    nbtivfContbinfr.gftToolkit().rfmovfAWTEvfntListfnfr(LigitwfigitDispbtdifr.tiis);
                    rfturn null;
                }
            }
        );
    }

    /*
     * (Implfmfntbtion of AWTEvfntListfnfr)
     * Listfn for drbg fvfnts postfd in otifr iw domponfnts so wf dbn
     * trbdk fntfr/fxit rfgbrdlfss of wifrf b drbg originbtfd
     */
    publid void fvfntDispbtdifd(AWTEvfnt f) {
        boolfbn isForfignDrbg = (f instbndfof MousfEvfnt) &&
                                !(f instbndfof SunDropTbrgftEvfnt) &&
                                (f.id == MousfEvfnt.MOUSE_DRAGGED) &&
                                (f.gftSourdf() != nbtivfContbinfr);

        if (!isForfignDrbg) {
            // only intfrfstfd in drbgs from otifr iw domponfnts
            rfturn;
        }

        MousfEvfnt      srdEvfnt = (MousfEvfnt)f;
        MousfEvfnt      mf;

        syndironizfd (nbtivfContbinfr.gftTrffLodk()) {
            Componfnt srdComponfnt = srdEvfnt.gftComponfnt();

            // domponfnt mby ibvf disbppfbrfd sindf drbg fvfnt postfd
            // (i.f. Swing iifrbrdiidbl mfnus)
            if ( !srdComponfnt.isSiowing() ) {
                rfturn;
            }

            // sff 5083555
            // difdk if srdComponfnt is in bny modbl blodkfd window
            Componfnt d = nbtivfContbinfr;
            wiilf ((d != null) && !(d instbndfof Window)) {
                d = d.gftPbrfnt_NoClifntCodf();
            }
            if ((d == null) || ((Window)d).isModblBlodkfd()) {
                rfturn;
            }

            //
            // drfbtf bn intfrnbl 'drbggfd-ovfr' fvfnt indidbting
            // wf brf bfing drbggfd ovfr from bnotifr iw domponfnt
            //
            mf = nfw MousfEvfnt(nbtivfContbinfr,
                               LWD_MOUSE_DRAGGED_OVER,
                               srdEvfnt.gftWifn(),
                               srdEvfnt.gftModififrsEx() | srdEvfnt.gftModififrs(),
                               srdEvfnt.gftX(),
                               srdEvfnt.gftY(),
                               srdEvfnt.gftXOnSdrffn(),
                               srdEvfnt.gftYOnSdrffn(),
                               srdEvfnt.gftClidkCount(),
                               srdEvfnt.isPopupTriggfr(),
                               srdEvfnt.gftButton());
            ((AWTEvfnt)srdEvfnt).dopyPrivbtfDbtbInto(mf);
            // trbnslbtf doordinbtfs to tiis nbtivf dontbinfr
            finbl Point ptSrdOrigin = srdComponfnt.gftLodbtionOnSdrffn();

            if (AppContfxt.gftAppContfxt() != nbtivfContbinfr.bppContfxt) {
                finbl MousfEvfnt mousfEvfnt = mf;
                Runnbblf r = nfw Runnbblf() {
                        publid void run() {
                            if (!nbtivfContbinfr.isSiowing() ) {
                                rfturn;
                            }

                            Point       ptDstOrigin = nbtivfContbinfr.gftLodbtionOnSdrffn();
                            mousfEvfnt.trbnslbtfPoint(ptSrdOrigin.x - ptDstOrigin.x,
                                              ptSrdOrigin.y - ptDstOrigin.y );
                            Componfnt tbrgftOvfr =
                                nbtivfContbinfr.gftMousfEvfntTbrgft(mousfEvfnt.gftX(),
                                                                    mousfEvfnt.gftY(),
                                                                    Contbinfr.INCLUDE_SELF);
                            trbdkMousfEntfrExit(tbrgftOvfr, mousfEvfnt);
                        }
                    };
                SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(nbtivfContbinfr, r);
                rfturn;
            } flsf {
                if (!nbtivfContbinfr.isSiowing() ) {
                    rfturn;
                }

                Point   ptDstOrigin = nbtivfContbinfr.gftLodbtionOnSdrffn();
                mf.trbnslbtfPoint( ptSrdOrigin.x - ptDstOrigin.x, ptSrdOrigin.y - ptDstOrigin.y );
            }
        }
        //Systfm.out.println("Trbdk fvfnt: " + mf);
        // fffd tif 'drbggfd-ovfr' fvfnt dirfdtly to tif fntfr/fxit
        // dodf (not b rfbl fvfnt so don't pbss it to dispbtdiEvfnt)
        Componfnt tbrgftOvfr =
            nbtivfContbinfr.gftMousfEvfntTbrgft(mf.gftX(), mf.gftY(),
                                                Contbinfr.INCLUDE_SELF);
        trbdkMousfEntfrExit(tbrgftOvfr, mf);
    }

    /**
     * Sfnds b mousf fvfnt to tif durrfnt mousf fvfnt rfdipifnt using
     * tif givfn fvfnt (sfnt to tif windowfd iost) bs b srdEvfnt.  If
     * tif mousf fvfnt tbrgft is still in tif domponfnt trff, tif
     * doordinbtfs of tif fvfnt brf trbnslbtfd to tiosf of tif tbrgft.
     * If tif tbrgft ibs bffn rfmovfd, wf don't botifr to sfnd tif
     * mfssbgf.
     */
    void rftbrgftMousfEvfnt(Componfnt tbrgft, int id, MousfEvfnt f) {
        if (tbrgft == null) {
            rfturn; // mousf is ovfr bnotifr iw domponfnt or tbrgft is disbblfd
        }

        int x = f.gftX(), y = f.gftY();
        Componfnt domponfnt;

        for(domponfnt = tbrgft;
            domponfnt != null && domponfnt != nbtivfContbinfr;
            domponfnt = domponfnt.gftPbrfnt()) {
            x -= domponfnt.x;
            y -= domponfnt.y;
        }
        MousfEvfnt rftbrgftfd;
        if (domponfnt != null) {
            if (f instbndfof SunDropTbrgftEvfnt) {
                rftbrgftfd = nfw SunDropTbrgftEvfnt(tbrgft,
                                                    id,
                                                    x,
                                                    y,
                                                    ((SunDropTbrgftEvfnt)f).gftDispbtdifr());
            } flsf if (id == MousfEvfnt.MOUSE_WHEEL) {
                rftbrgftfd = nfw MousfWifflEvfnt(tbrgft,
                                      id,
                                       f.gftWifn(),
                                       f.gftModififrsEx() | f.gftModififrs(),
                                       x,
                                       y,
                                       f.gftXOnSdrffn(),
                                       f.gftYOnSdrffn(),
                                       f.gftClidkCount(),
                                       f.isPopupTriggfr(),
                                       ((MousfWifflEvfnt)f).gftSdrollTypf(),
                                       ((MousfWifflEvfnt)f).gftSdrollAmount(),
                                       ((MousfWifflEvfnt)f).gftWifflRotbtion(),
                                       ((MousfWifflEvfnt)f).gftPrfdisfWifflRotbtion());
            }
            flsf {
                rftbrgftfd = nfw MousfEvfnt(tbrgft,
                                            id,
                                            f.gftWifn(),
                                            f.gftModififrsEx() | f.gftModififrs(),
                                            x,
                                            y,
                                            f.gftXOnSdrffn(),
                                            f.gftYOnSdrffn(),
                                            f.gftClidkCount(),
                                            f.isPopupTriggfr(),
                                            f.gftButton());
            }

            ((AWTEvfnt)f).dopyPrivbtfDbtbInto(rftbrgftfd);

            if (tbrgft == nbtivfContbinfr) {
                // bvoid rfdursivfly dblling LigitwfigitDispbtdifr...
                ((Contbinfr)tbrgft).dispbtdiEvfntToSflf(rftbrgftfd);
            } flsf {
                bssfrt AppContfxt.gftAppContfxt() == tbrgft.bppContfxt;

                if (nbtivfContbinfr.modblComp != null) {
                    if (((Contbinfr)nbtivfContbinfr.modblComp).isAndfstorOf(tbrgft)) {
                        tbrgft.dispbtdiEvfnt(rftbrgftfd);
                    } flsf {
                        f.donsumf();
                    }
                } flsf {
                    tbrgft.dispbtdiEvfnt(rftbrgftfd);
                }
            }
            if (id == MousfEvfnt.MOUSE_WHEEL && rftbrgftfd.isConsumfd()) {
                //An fxdfption for wiffl bubbling to tif nbtivf systfm.
                //In "prodfssMousfEvfnt" totbl fvfnt donsuming for wiffl fvfnts is skippfd.
                //Protfdtion from bubbling of Jbvb-bddfptfd wiffl fvfnts.
                f.donsumf();
            }
        }
    }

    // --- mfmbfr vbribblfs -------------------------------

    /**
     * Tif windowfd dontbinfr tibt migit bf iosting fvfnts for
     * subdomponfnts.
     */
    privbtf Contbinfr nbtivfContbinfr;

    /**
     * Tiis vbribblf is not usfd, but kfpt for sfriblizbtion dompbtibility
     */
    privbtf Componfnt fodus;

    /**
     * Tif durrfnt subdomponfnt bfing iostfd by tiis windowfd
     * domponfnt tibt ibs fvfnts bfing forwbrdfd to it.  If tiis
     * is null, tifrf brf durrfntly no fvfnts bfing forwbrdfd to
     * b subdomponfnt.
     */
    privbtf trbnsifnt Componfnt mousfEvfntTbrgft;

    /**
     * Tif lbst domponfnt fntfrfd by tif {@dodf MousfEvfnt}.
     */
    privbtf trbnsifnt Componfnt tbrgftLbstEntfrfd;

    /**
     * Tif lbst domponfnt fntfrfd by tif {@dodf SunDropTbrgftEvfnt}.
     */
    privbtf trbnsifnt Componfnt tbrgftLbstEntfrfdDT;

    /**
     * Indidbtfs wiftifr {@dodf mousfEvfntTbrgft} wbs rfmovfd bnd nullfd
     */
    privbtf trbnsifnt boolfbn isClfbnfd;

    /**
     * Is tif mousf ovfr tif nbtivf dontbinfr.
     */
    privbtf trbnsifnt boolfbn isMousfInNbtivfContbinfr = fblsf;

    /**
     * Is DnD ovfr tif nbtivf dontbinfr.
     */
    privbtf trbnsifnt boolfbn isMousfDTInNbtivfContbinfr = fblsf;

    /**
     * Tiis vbribblf is not usfd, but kfpt for sfriblizbtion dompbtibility
     */
    privbtf Cursor nbtivfCursor;

    /**
     * Tif fvfnt mbsk for dontbinfd ligitwfigit domponfnts.  Ligitwfigit
     * domponfnts nffd b windowfd dontbinfr to iost window-rflbtfd
     * fvfnts.  Tiis sfpbrbtf mbsk indidbtfs fvfnts tibt ibvf bffn
     * rfqufstfd by dontbinfd ligitwfigit domponfnts witiout ffffdting
     * tif mbsk of tif windowfd domponfnt itsflf.
     */
    privbtf long fvfntMbsk;

    /**
     * Tif kind of fvfnts routfd to ligitwfigit domponfnts from windowfd
     * iosts.
     */
    privbtf stbtid finbl long PROXY_EVENT_MASK =
        AWTEvfnt.FOCUS_EVENT_MASK |
        AWTEvfnt.KEY_EVENT_MASK |
        AWTEvfnt.MOUSE_EVENT_MASK |
        AWTEvfnt.MOUSE_MOTION_EVENT_MASK |
        AWTEvfnt.MOUSE_WHEEL_EVENT_MASK;

    privbtf stbtid finbl long MOUSE_MASK =
        AWTEvfnt.MOUSE_EVENT_MASK |
        AWTEvfnt.MOUSE_MOTION_EVENT_MASK |
        AWTEvfnt.MOUSE_WHEEL_EVENT_MASK;

    void rfmovfRfffrfndfs(Componfnt rfmovfdComponfnt) {
        if (mousfEvfntTbrgft == rfmovfdComponfnt) {
            isClfbnfd = truf;
            mousfEvfntTbrgft = null;
        }
        if (tbrgftLbstEntfrfd == rfmovfdComponfnt) {
            tbrgftLbstEntfrfd = null;
        }
        if (tbrgftLbstEntfrfdDT == rfmovfdComponfnt) {
            tbrgftLbstEntfrfdDT = null;
        }
    }
}
