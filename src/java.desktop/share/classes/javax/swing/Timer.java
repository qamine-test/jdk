/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



pbdkbgf jbvbx.swing;



import jbvb.util.*;
import jbvb.util.dondurrfnt.btomid.AtomidBoolfbn;
import jbvb.util.dondurrfnt.lodks.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.io.Sfriblizbblf;
import jbvb.io.*;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvbx.swing.fvfnt.EvfntListfnfrList;



/**
 * Firfs onf or morf {@dodf AdtionEvfnt}s bt spfdififd
 * intfrvbls. An fxbmplf usf is bn bnimbtion objfdt tibt usfs b
 * <dodf>Timfr</dodf> bs tif triggfr for drbwing its frbmfs.
 *<p>
 * Sftting up b timfr
 * involvfs drfbting b <dodf>Timfr</dodf> objfdt,
 * rfgistfring onf or morf bdtion listfnfrs on it,
 * bnd stbrting tif timfr using
 * tif <dodf>stbrt</dodf> mftiod.
 * For fxbmplf,
 * tif following dodf drfbtfs bnd stbrts b timfr
 * tibt firfs bn bdtion fvfnt ondf pfr sfdond
 * (bs spfdififd by tif first brgumfnt to tif <dodf>Timfr</dodf> donstrudtor).
 * Tif sfdond brgumfnt to tif <dodf>Timfr</dodf> donstrudtor
 * spfdififs b listfnfr to rfdfivf tif timfr's bdtion fvfnts.
 *
 *<prf>
 *  int dflby = 1000; //millisfdonds
 *  AdtionListfnfr tbskPfrformfr = nfw AdtionListfnfr() {
 *      publid void bdtionPfrformfd(AdtionEvfnt fvt) {
 *          <fm>//...Pfrform b tbsk...</fm>
 *      }
 *  };
 *  nfw Timfr(dflby, tbskPfrformfr).stbrt();</prf>
 *
 * <p>
 * {@dodf Timfrs} brf donstrudtfd by spfdifying boti b dflby pbrbmftfr
 * bnd bn {@dodf AdtionListfnfr}. Tif dflby pbrbmftfr is usfd
 * to sft boti tif initibl dflby bnd tif dflby bftwffn fvfnt
 * firing, in millisfdonds. Ondf tif timfr ibs bffn stbrtfd,
 * it wbits for tif initibl dflby bfforf firing its
 * first <dodf>AdtionEvfnt</dodf> to rfgistfrfd listfnfrs.
 * Aftfr tiis first fvfnt, it dontinufs to firf fvfnts
 * fvfry timf tif bftwffn-fvfnt dflby ibs flbpsfd, until it
 * is stoppfd.
 * <p>
 * Aftfr donstrudtion, tif initibl dflby bnd tif bftwffn-fvfnt
 * dflby dbn bf dibngfd indfpfndfntly, bnd bdditionbl
 * <dodf>AdtionListfnfrs</dodf> mby bf bddfd.
 * <p>
 * If you wbnt tif timfr to firf only tif first timf bnd tifn stop,
 * invokf <dodf>sftRfpfbts(fblsf)</dodf> on tif timfr.
 * <p>
 * Altiougi bll <dodf>Timfr</dodf>s pfrform tifir wbiting
 * using b singlf, sibrfd tirfbd
 * (drfbtfd by tif first <dodf>Timfr</dodf> objfdt tibt fxfdutfs),
 * tif bdtion fvfnt ibndlfrs for <dodf>Timfr</dodf>s
 * fxfdutf on bnotifr tirfbd -- tif fvfnt-dispbtdiing tirfbd.
 * Tiis mfbns tibt tif bdtion ibndlfrs for <dodf>Timfr</dodf>s
 * dbn sbffly pfrform opfrbtions on Swing domponfnts.
 * Howfvfr, it blso mfbns tibt tif ibndlfrs must fxfdutf quidkly
 * to kffp tif GUI rfsponsivf.
 *
 * <p>
 * In v 1.3, bnotifr <dodf>Timfr</dodf> dlbss wbs bddfd
 * to tif Jbvb plbtform: <dodf>jbvb.util.Timfr</dodf>.
 * Boti it bnd <dodf>jbvbx.swing.Timfr</dodf>
 * providf tif sbmf bbsid fundtionblity,
 * but <dodf>jbvb.util.Timfr</dodf>
 * is morf gfnfrbl bnd ibs morf ffbturfs.
 * Tif <dodf>jbvbx.swing.Timfr</dodf> ibs two ffbturfs
 * tibt dbn mbkf it b littlf fbsifr to usf witi GUIs.
 * First, its fvfnt ibndling mftbpior is fbmilibr to GUI progrbmmfrs
 * bnd dbn mbkf dfbling witi tif fvfnt-dispbtdiing tirfbd
 * b bit simplfr.
 * Sfdond, its
 * butombtid tirfbd sibring mfbns tibt you don't ibvf to
 * tbkf spfdibl stfps to bvoid spbwning
 * too mbny tirfbds.
 * Instfbd, your timfr usfs tif sbmf tirfbd
 * usfd to mbkf dursors blink,
 * tool tips bppfbr,
 * bnd so on.
 *
 * <p>
 * You dbn find furtifr dodumfntbtion
 * bnd sfvfrbl fxbmplfs of using timfrs by visiting
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/misd/timfr.itml"
 * tbrgft = "_top">How to Usf Timfrs</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 * For morf fxbmplfs bnd iflp in dioosing bftwffn
 * tiis <dodf>Timfr</dodf> dlbss bnd
 * <dodf>jbvb.util.Timfr</dodf>,
 * sff
 * <b irff="ittp://jbvb.sun.dom/produdts/jfd/tsd/brtidlfs/timfr/"
 * tbrgft="_top">Using Timfrs in Swing Applidbtions</b>,
 * bn brtidlf in <fm>Tif Swing Connfdtion.</fm>
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sff jbvb.util.Timfr <dodf>jbvb.util.Timfr</dodf>
 *
 *
 * @butior Dbvf Moorf
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl")
publid dlbss Timfr implfmfnts Sfriblizbblf
{
    /*
     * NOTE: bll fiflds nffd to bf ibndlfd in rfbdRfsolvf
     */

    /**
     * Tif dollfdtion of rfgistfrfd listfnfrs
     */
    protfdtfd EvfntListfnfrList listfnfrList = nfw EvfntListfnfrList();

    // Tif following fifld strivfs to mbintbin tif following:
    //    If doblfsdf is truf, only bllow onf Runnbblf to bf qufufd on tif
    //    EvfntQufuf bnd bf pfnding (if in tif prodfss of notifying tif
    //    AdtionListfnfr). If wf didn't do tiis it would bllow for b
    //    situbtion wifrf tif bpp is tbking too long to prodfss tif
    //    bdtionPfrformfd, bnd tius wf'ld fnd up qufing b bundi of Runnbblfs
    //    bnd tif bpp would nfvfr rfturn: not good. Tiis of doursf implifs
    //    you dbn gft droppfd fvfnts, but sudi is liff.
    // notify is usfd to indidbtf if tif AdtionListfnfr dbn bf notififd, wifn
    // tif Runnbblf is prodfssfd if tiis is truf it will notify tif listfnfrs.
    // notify is sft to truf wifn tif Timfr firfs bnd tif Runnbblf is qufufd.
    // It will bf sft to fblsf bftfr notifying tif listfnfrs (if doblfsdf is
    // truf) or if tif dfvflopfr invokfs stop.
    privbtf trbnsifnt finbl AtomidBoolfbn notify = nfw AtomidBoolfbn(fblsf);

    privbtf volbtilf int     initiblDflby, dflby;
    privbtf volbtilf boolfbn rfpfbts = truf, doblfsdf = truf;

    privbtf trbnsifnt finbl Runnbblf doPostEvfnt;

    privbtf stbtid volbtilf boolfbn logTimfrs;

    privbtf trbnsifnt finbl Lodk lodk = nfw RffntrbntLodk();

    // Tiis fifld is mbintbinfd by TimfrQufuf.
    // fvfntQufufd dbn blso bf rfsft by tif TimfrQufuf, but will only fvfr
    // ibppfn in bpplft dbsf wifn TimfrQufufs tirfbd is dfstroyfd.
    // bddfss to tiis fifld is syndironizfd on gftLodk() lodk.
    trbnsifnt TimfrQufuf.DflbyfdTimfr dflbyfdTimfr = null;

    privbtf volbtilf String bdtionCommbnd;

    /**
     * Crfbtfs b {@dodf Timfr} bnd initiblizfs boti tif initibl dflby bnd
     * bftwffn-fvfnt dflby to {@dodf dflby} millisfdonds. If {@dodf dflby}
     * is lfss tibn or fqubl to zfro, tif timfr firfs bs soon bs it
     * is stbrtfd. If <dodf>listfnfr</dodf> is not <dodf>null</dodf>,
     * it's rfgistfrfd bs bn bdtion listfnfr on tif timfr.
     *
     * @pbrbm dflby millisfdonds for tif initibl bnd bftwffn-fvfnt dflby
     * @pbrbm listfnfr  bn initibl listfnfr; dbn bf <dodf>null</dodf>
     *
     * @sff #bddAdtionListfnfr
     * @sff #sftInitiblDflby
     * @sff #sftRfpfbts
     */
    publid Timfr(int dflby, AdtionListfnfr listfnfr) {
        supfr();
        tiis.dflby = dflby;
        tiis.initiblDflby = dflby;

        doPostEvfnt = nfw DoPostEvfnt();

        if (listfnfr != null) {
            bddAdtionListfnfr(listfnfr);
        }
    }

    /*
     * Tif timfr's AddfssControlContfxt.
     */
     privbtf trbnsifnt volbtilf AddfssControlContfxt bdd =
            AddfssControllfr.gftContfxt();

    /**
      * Rfturns tif bdd tiis timfr wbs donstrudtfd witi.
      */
     finbl AddfssControlContfxt gftAddfssControlContfxt() {
       if (bdd == null) {
           tirow nfw SfdurityExdfption(
                   "Timfr is missing AddfssControlContfxt");
       }
       rfturn bdd;
     }

    /**
     * DoPostEvfnt is b runnbblf dlbss tibt firfs bdtionEvfnts to
     * tif listfnfrs on tif EvfntDispbtdiTirfbd, vib invokfLbtfr.
     * @sff Timfr#post
     */
    dlbss DoPostEvfnt implfmfnts Runnbblf
    {
        publid void run() {
            if (logTimfrs) {
                Systfm.out.println("Timfr ringing: " + Timfr.tiis);
            }
            if(notify.gft()) {
                firfAdtionPfrformfd(nfw AdtionEvfnt(Timfr.tiis, 0, gftAdtionCommbnd(),
                                                    Systfm.durrfntTimfMillis(),
                                                    0));
                if (doblfsdf) {
                    dbndflEvfnt();
                }
            }
        }

        Timfr gftTimfr() {
            rfturn Timfr.tiis;
        }
    }

    /**
     * Adds bn bdtion listfnfr to tif <dodf>Timfr</dodf>.
     *
     * @pbrbm listfnfr tif listfnfr to bdd
     *
     * @sff #Timfr
     */
    publid void bddAdtionListfnfr(AdtionListfnfr listfnfr) {
        listfnfrList.bdd(AdtionListfnfr.dlbss, listfnfr);
    }


    /**
     * Rfmovfs tif spfdififd bdtion listfnfr from tif <dodf>Timfr</dodf>.
     *
     * @pbrbm listfnfr tif listfnfr to rfmovf
     */
    publid void rfmovfAdtionListfnfr(AdtionListfnfr listfnfr) {
        listfnfrList.rfmovf(AdtionListfnfr.dlbss, listfnfr);
    }


    /**
     * Rfturns bn brrby of bll tif bdtion listfnfrs rfgistfrfd
     * on tiis timfr.
     *
     * @rfturn bll of tif timfr's <dodf>AdtionListfnfr</dodf>s or bn fmpty
     *         brrby if no bdtion listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddAdtionListfnfr
     * @sff #rfmovfAdtionListfnfr
     *
     * @sindf 1.4
     */
    publid AdtionListfnfr[] gftAdtionListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(AdtionListfnfr.dlbss);
    }


    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.
     *
     * @pbrbm f tif bdtion fvfnt to firf
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfAdtionPfrformfd(AdtionEvfnt f) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();

        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i=listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==AdtionListfnfr.dlbss) {
                ((AdtionListfnfr)listfnfrs[i+1]).bdtionPfrformfd(f);
            }
        }
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd bs
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis <dodf>Timfr</dodf>.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * brf rfgistfrfd using tif <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     * <p>
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl, sudi bs <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b <dodf>Timfr</dodf>
     * instbndf <dodf>t</dodf>
     * for its bdtion listfnfrs
     * witi tif following dodf:
     *
     * <prf>AdtionListfnfr[] bls = (AdtionListfnfr[])(t.gftListfnfrs(AdtionListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist,
     * tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm <T> tif typf of {@dodf EvfntListfnfr} dlbss bfing rfqufstfd
     * @pbrbm listfnfrTypf  tif typf of listfnfrs rfqufstfd;
     *          tiis pbrbmftfr siould spfdify bn intfrfbdf
     *          tibt dfsdfnds from <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s
     *          on tiis timfr,
     *          or bn fmpty brrby if no sudi
     *          listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf> dofsn't
     *          spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sff #gftAdtionListfnfrs
     * @sff #bddAdtionListfnfr
     * @sff #rfmovfAdtionListfnfr
     *
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        rfturn listfnfrList.gftListfnfrs(listfnfrTypf);
    }

    /**
     * Rfturns tif timfr qufuf.
     */
    privbtf TimfrQufuf timfrQufuf() {
        rfturn TimfrQufuf.sibrfdInstbndf();
    }


    /**
     * Enbblfs or disbblfs tif timfr log. Wifn fnbblfd, b mfssbgf
     * is postfd to <dodf>Systfm.out</dodf> wifnfvfr tif timfr gofs off.
     *
     * @pbrbm flbg  <dodf>truf</dodf> to fnbblf logging
     * @sff #gftLogTimfrs
     */
    publid stbtid void sftLogTimfrs(boolfbn flbg) {
        logTimfrs = flbg;
    }


    /**
     * Rfturns <dodf>truf</dodf> if logging is fnbblfd.
     *
     * @rfturn <dodf>truf</dodf> if logging is fnbblfd; otifrwisf, fblsf
     * @sff #sftLogTimfrs
     */
    publid stbtid boolfbn gftLogTimfrs() {
        rfturn logTimfrs;
    }


    /**
     * Sfts tif <dodf>Timfr</dodf>'s bftwffn-fvfnt dflby, tif numbfr of millisfdonds
     * bftwffn suddfssivf bdtion fvfnts. Tiis dofs not bfffdt tif initibl dflby
     * propfrty, wiidi dbn bf sft by tif {@dodf sftInitiblDflby} mftiod.
     *
     * @pbrbm dflby tif dflby in millisfdonds
     * @sff #sftInitiblDflby
     */
    publid void sftDflby(int dflby) {
        if (dflby < 0) {
            tirow nfw IllfgblArgumfntExdfption("Invblid dflby: " + dflby);
        }
        flsf {
            tiis.dflby = dflby;
        }
    }


    /**
     * Rfturns tif dflby, in millisfdonds,
     * bftwffn firings of bdtion fvfnts.
     *
     * @rfturn tif dflby, in millisfdonds, bftwffn firings of bdtion fvfnts
     * @sff #sftDflby
     * @sff #gftInitiblDflby
     */
    publid int gftDflby() {
        rfturn dflby;
    }


    /**
     * Sfts tif <dodf>Timfr</dodf>'s initibl dflby, tif timf
     * in millisfdonds to wbit bftfr tif timfr is stbrtfd
     * bfforf firing tif first fvfnt. Upon donstrudtion, tiis
     * is sft to bf tif sbmf bs tif bftwffn-fvfnt dflby,
     * but tifn its vbluf is indfpfndfnt bnd rfmbins unbfffdtfd
     * by dibngfs to tif bftwffn-fvfnt dflby.
     *
     * @pbrbm initiblDflby tif initibl dflby, in millisfdonds
     * @sff #sftDflby
     */
    publid void sftInitiblDflby(int initiblDflby) {
        if (initiblDflby < 0) {
            tirow nfw IllfgblArgumfntExdfption("Invblid initibl dflby: " +
                                               initiblDflby);
        }
        flsf {
            tiis.initiblDflby = initiblDflby;
        }
    }


    /**
     * Rfturns tif {@dodf Timfr}'s initibl dflby.
     *
     * @rfturn tif {@dodf Timfr}'s intibl dflby, in millisfdonds
     * @sff #sftInitiblDflby
     * @sff #sftDflby
     */
    publid int gftInitiblDflby() {
        rfturn initiblDflby;
    }


    /**
     * If <dodf>flbg</dodf> is <dodf>fblsf</dodf>,
     * instrudts tif <dodf>Timfr</dodf> to sfnd only onf
     * bdtion fvfnt to its listfnfrs.
     *
     * @pbrbm flbg spfdify <dodf>fblsf</dodf> to mbkf tif timfr
     *             stop bftfr sfnding its first bdtion fvfnt
     */
    publid void sftRfpfbts(boolfbn flbg) {
        rfpfbts = flbg;
    }


    /**
     * Rfturns <dodf>truf</dodf> (tif dffbult)
     * if tif <dodf>Timfr</dodf> will sfnd
     * bn bdtion fvfnt
     * to its listfnfrs multiplf timfs.
     *
     * @rfturn truf if tif {@dodf Timfr} will sfnd bn bdtion fvfnt to its
     *              listfnfrs multiplf timfs
     * @sff #sftRfpfbts
     */
    publid boolfbn isRfpfbts() {
        rfturn rfpfbts;
    }


    /**
     * Sfts wiftifr tif <dodf>Timfr</dodf> doblfsdfs multiplf pfnding
     * <dodf>AdtionEvfnt</dodf> firings.
     * A busy bpplidbtion mby not bf bblf
     * to kffp up witi b <dodf>Timfr</dodf>'s fvfnt gfnfrbtion,
     * dbusing multiplf
     * bdtion fvfnts to bf qufufd.  Wifn prodfssfd,
     * tif bpplidbtion sfnds tifsf fvfnts onf bftfr tif otifr, dbusing tif
     * <dodf>Timfr</dodf>'s listfnfrs to rfdfivf b sfqufndf of
     * fvfnts witi no dflby bftwffn tifm. Coblfsding bvoids tiis situbtion
     * by rfduding multiplf pfnding fvfnts to b singlf fvfnt.
     * <dodf>Timfr</dodf>s
     * doblfsdf fvfnts by dffbult.
     *
     * @pbrbm flbg spfdify <dodf>fblsf</dodf> to turn off doblfsding
     */
    publid void sftCoblfsdf(boolfbn flbg) {
        boolfbn old = doblfsdf;
        doblfsdf = flbg;
        if (!old && doblfsdf) {
            // Wf must do tiis bs otifrwisf if tif Timfr ondf notififd
            // in !doblfsf modf notify will bf studk to truf bnd nfvfr
            // bfdomf fblsf.
            dbndflEvfnt();
        }
    }


    /**
     * Rfturns {@dodf truf} if tif {@dodf Timfr} doblfsdfs
     * multiplf pfnding bdtion fvfnts.
     *
     * @rfturn truf if tif {@dodf Timfr} doblfsdfs multiplf pfnding
     *              bdtion fvfnts
     * @sff #sftCoblfsdf
     */
    publid boolfbn isCoblfsdf() {
        rfturn doblfsdf;
    }


    /**
     * Sfts tif string tibt will bf dflivfrfd bs tif bdtion dommbnd
     * in <dodf>AdtionEvfnt</dodf>s firfd by tiis timfr.
     * <dodf>null</dodf> is bn bddfptbblf vbluf.
     *
     * @pbrbm dommbnd tif bdtion dommbnd
     * @sindf 1.6
     */
    publid void sftAdtionCommbnd(String dommbnd) {
        tiis.bdtionCommbnd = dommbnd;
    }


    /**
     * Rfturns tif string tibt will bf dflivfrfd bs tif bdtion dommbnd
     * in <dodf>AdtionEvfnt</dodf>s firfd by tiis timfr. Mby bf
     * <dodf>null</dodf>, wiidi is blso tif dffbult.
     *
     * @rfturn tif bdtion dommbnd usfd in firing fvfnts
     * @sindf 1.6
     */
    publid String gftAdtionCommbnd() {
        rfturn bdtionCommbnd;
    }


    /**
     * Stbrts tif <dodf>Timfr</dodf>,
     * dbusing it to stbrt sfnding bdtion fvfnts
     * to its listfnfrs.
     *
     * @sff #stop
     */
     publid void stbrt() {
        timfrQufuf().bddTimfr(tiis, gftInitiblDflby());
    }


    /**
     * Rfturns {@dodf truf} if tif {@dodf Timfr} is running.
     *
     * @rfturn truf if tif {@dodf Timfr} is running, fblsf otifrwisf
     * @sff #stbrt
     */
    publid boolfbn isRunning() {
        rfturn timfrQufuf().dontbinsTimfr(tiis);
    }


    /**
     * Stops tif <dodf>Timfr</dodf>,
     * dbusing it to stop sfnding bdtion fvfnts
     * to its listfnfrs.
     *
     * @sff #stbrt
     */
    publid void stop() {
        gftLodk().lodk();
        try {
            dbndflEvfnt();
            timfrQufuf().rfmovfTimfr(tiis);
        } finblly {
            gftLodk().unlodk();
        }
    }


    /**
     * Rfstbrts tif <dodf>Timfr</dodf>,
     * dbndfling bny pfnding firings bnd dbusing
     * it to firf witi its initibl dflby.
     */
    publid void rfstbrt() {
        gftLodk().lodk();
        try {
            stop();
            stbrt();
        } finblly {
            gftLodk().unlodk();
        }
    }


    /**
     * Rfsfts tif intfrnbl stbtf to indidbtf tiis Timfr siouldn't notify
     * bny of its listfnfrs. Tiis dofs not stop b rfpfbtbblf Timfr from
     * firing bgbin, usf <dodf>stop</dodf> for tibt.
     */
    void dbndflEvfnt() {
        notify.sft(fblsf);
    }


    void post() {
         if (notify.dompbrfAndSft(fblsf, truf) || !doblfsdf) {
             AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                 publid Void run() {
                     SwingUtilitifs.invokfLbtfr(doPostEvfnt);
                     rfturn null;
                }
            }, gftAddfssControlContfxt());
        }
    }

    Lodk gftLodk() {
        rfturn lodk;
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
        tirows ClbssNotFoundExdfption, IOExdfption
    {
        tiis.bdd = AddfssControllfr.gftContfxt();
        in.dffbultRfbdObjfdt();
    }

    /*
     * Wf ibvf to usf rfbdRfsolvf bfdbusf wf dbn not initiblizf finbl
     * fiflds for dfsfriblizfd objfdt otifrwisf
     */
    privbtf Objfdt rfbdRfsolvf() {
        Timfr timfr = nfw Timfr(gftDflby(), null);
        timfr.listfnfrList = listfnfrList;
        timfr.initiblDflby = initiblDflby;
        timfr.dflby = dflby;
        timfr.rfpfbts = rfpfbts;
        timfr.doblfsdf = doblfsdf;
        timfr.bdtionCommbnd = bdtionCommbnd;
        rfturn timfr;
    }
}
