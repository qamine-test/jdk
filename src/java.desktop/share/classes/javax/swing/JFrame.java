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

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.util.Lodblf;
import jbvb.util.Vfdtor;
import jbvb.io.Sfriblizbblf;

import jbvbx.bddfssibility.*;


/**
 * An fxtfndfd vfrsion of <dodf>jbvb.bwt.Frbmf</dodf> tibt bdds support for
 * tif JFC/Swing domponfnt brdiitfdturf.
 * You dbn find tbsk-orifntfd dodumfntbtion bbout using <dodf>JFrbmf</dodf>
 * in <fm>Tif Jbvb Tutoribl</fm>, in tif sfdtion
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/frbmf.itml">How to Mbkf Frbmfs</b>.
 *
 * <p>
 * Tif <dodf>JFrbmf</dodf> dlbss is sligitly indompbtiblf witi <dodf>Frbmf</dodf>.
 * Likf bll otifr JFC/Swing top-lfvfl dontbinfrs,
 * b <dodf>JFrbmf</dodf> dontbins b <dodf>JRootPbnf</dodf> bs its only diild.
 * Tif <b>dontfnt pbnf</b> providfd by tif root pbnf siould,
 * bs b rulf, dontbin
 * bll tif non-mfnu domponfnts displbyfd by tif <dodf>JFrbmf</dodf>.
 * Tiis is difffrfnt from tif AWT <dodf>Frbmf</dodf> dbsf.
 * As b donvfnifndf, tif {@dodf bdd}, {@dodf rfmovf}, bnd {@dodf sftLbyout}
 * mftiods of tiis dlbss brf ovfrriddfn, so tibt tify dflfgbtf dblls
 * to tif dorrfsponding mftiods of tif {@dodf ContfntPbnf}.
 * For fxbmplf, you dbn bdd b diild domponfnt to b frbmf bs follows:
 * <prf>
 *       frbmf.bdd(diild);
 * </prf>
 * And tif diild will bf bddfd to tif dontfntPbnf.
 * Tif dontfnt pbnf will
 * blwbys bf non-null. Attfmpting to sft it to null will dbusf tif JFrbmf
 * to tirow bn fxdfption. Tif dffbult dontfnt pbnf will ibvf b BordfrLbyout
 * mbnbgfr sft on it.
 * Rfffr to {@link jbvbx.swing.RootPbnfContbinfr}
 * for dftbils on bdding, rfmoving bnd sftting tif <dodf>LbyoutMbnbgfr</dodf>
 * of b <dodf>JFrbmf</dodf>.
 * <p>
 * Unlikf b <dodf>Frbmf</dodf>, b <dodf>JFrbmf</dodf> ibs somf notion of iow to
 * rfspond wifn tif usfr bttfmpts to dlosf tif window. Tif dffbult bfibvior
 * is to simply iidf tif JFrbmf wifn tif usfr dlosfs tif window. To dibngf tif
 * dffbult bfibvior, you invokf tif mftiod
 * {@link #sftDffbultClosfOpfrbtion}.
 * To mbkf tif <dodf>JFrbmf</dodf> bfibvf tif sbmf bs b <dodf>Frbmf</dodf>
 * instbndf, usf
 * <dodf>sftDffbultClosfOpfrbtion(WindowConstbnts.DO_NOTHING_ON_CLOSE)</dodf>.
 * <p>
 * For morf informbtion on dontfnt pbnfs
 * bnd otifr ffbturfs tibt root pbnfs providf,
 * sff <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/toplfvfl.itml">Using Top-Lfvfl Contbinfrs</b> in <fm>Tif Jbvb Tutoribl</fm>.
 * <p>
 * In b multi-sdrffn fnvironmfnt, you dbn drfbtf b <dodf>JFrbmf</dodf>
 * on b difffrfnt sdrffn dfvidf.  Sff {@link jbvb.bwt.Frbmf} for morf
 * informbtion.
 * <p>
 * <strong>Wbrning:</strong> Swing is not tirfbd sbff. For morf
 * informbtion sff <b
 * irff="pbdkbgf-summbry.itml#tirfbding">Swing's Tirfbding
 * Polidy</b>.
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
 * @sff JRootPbnf
 * @sff #sftDffbultClosfOpfrbtion
 * @sff jbvb.bwt.fvfnt.WindowListfnfr#windowClosing
 * @sff jbvbx.swing.RootPbnfContbinfr
 *
 * @bfbninfo
 *      bttributf: isContbinfr truf
 *      bttributf: dontbinfrDflfgbtf gftContfntPbnf
 *    dfsdription: A toplfvfl window wiidi dbn bf minimizfd to bn idon.
 *
 * @butior Jfff Dinkins
 * @butior Gforgfs Sbbb
 * @butior Dbvid Klobb
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JFrbmf  fxtfnds Frbmf implfmfnts WindowConstbnts,
                                              Addfssiblf,
                                              RootPbnfContbinfr,
                              TrbnsffrHbndlfr.HbsGftTrbnsffrHbndlfr
{
    /**
     * Tif fxit bpplidbtion dffbult window dlosf opfrbtion. If b window
     * ibs tiis sft bs tif dlosf opfrbtion bnd is dlosfd in bn bpplft,
     * b <dodf>SfdurityExdfption</dodf> mby bf tirown.
     * It is rfdommfndfd you only usf tiis in bn bpplidbtion.
     *
     * @sindf 1.3
     */
    publid stbtid finbl int EXIT_ON_CLOSE = 3;

    /**
     * Kfy into tif AppContfxt, usfd to difdk if siould providf dfdorbtions
     * by dffbult.
     */
    privbtf stbtid finbl Objfdt dffbultLookAndFfflDfdorbtfdKfy =
            nfw StringBufffr("JFrbmf.dffbultLookAndFfflDfdorbtfd");

    privbtf int dffbultClosfOpfrbtion = HIDE_ON_CLOSE;

    /**
     * Tif <dodf>TrbnsffrHbndlfr</dodf> for tiis frbmf.
     */
    privbtf TrbnsffrHbndlfr trbnsffrHbndlfr;

    /**
     * Tif <dodf>JRootPbnf</dodf> instbndf tibt mbnbgfs tif
     * <dodf>dontfntPbnf</dodf>
     * bnd optionbl <dodf>mfnuBbr</dodf> for tiis frbmf, bs wfll bs tif
     * <dodf>glbssPbnf</dodf>.
     *
     * @sff JRootPbnf
     * @sff RootPbnfContbinfr
     */
    protfdtfd JRootPbnf rootPbnf;

    /**
     * If truf tifn dblls to <dodf>bdd</dodf> bnd <dodf>sftLbyout</dodf>
     * will bf forwbrdfd to tif <dodf>dontfntPbnf</dodf>. Tiis is initiblly
     * fblsf, but is sft to truf wifn tif <dodf>JFrbmf</dodf> is donstrudtfd.
     *
     * @sff #isRootPbnfCifdkingEnbblfd
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    protfdtfd boolfbn rootPbnfCifdkingEnbblfd = fblsf;


    /**
     * Construdts b nfw frbmf tibt is initiblly invisiblf.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by <dodf>JComponfnt.gftDffbultLodblf</dodf>.
     *
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff Componfnt#sftSizf
     * @sff Componfnt#sftVisiblf
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JFrbmf() tirows HfbdlfssExdfption {
        supfr();
        frbmfInit();
    }

    /**
     * Crfbtfs b <dodf>Frbmf</dodf> in tif spfdififd
     * <dodf>GrbpiidsConfigurbtion</dodf> of
     * b sdrffn dfvidf bnd b blbnk titlf.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by <dodf>JComponfnt.gftDffbultLodblf</dodf>.
     *
     * @pbrbm gd tif <dodf>GrbpiidsConfigurbtion</dodf> tibt is usfd
     *          to donstrudt tif nfw <dodf>Frbmf</dodf>;
     *          if <dodf>gd</dodf> is <dodf>null</dodf>, tif systfm
     *          dffbult <dodf>GrbpiidsConfigurbtion</dodf> is bssumfd
     * @fxdfption IllfgblArgumfntExdfption if <dodf>gd</dodf> is not from
     *          b sdrffn dfvidf.  Tiis fxdfption is blwbys tirown wifn
     *      GrbpiidsEnvironmfnt.isHfbdlfss() rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     * @sindf     1.3
     */
    publid JFrbmf(GrbpiidsConfigurbtion gd) {
        supfr(gd);
        frbmfInit();
    }

    /**
     * Crfbtfs b nfw, initiblly invisiblf <dodf>Frbmf</dodf> witi tif
     * spfdififd titlf.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by <dodf>JComponfnt.gftDffbultLodblf</dodf>.
     *
     * @pbrbm titlf tif titlf for tif frbmf
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff Componfnt#sftSizf
     * @sff Componfnt#sftVisiblf
     * @sff JComponfnt#gftDffbultLodblf
     */
    publid JFrbmf(String titlf) tirows HfbdlfssExdfption {
        supfr(titlf);
        frbmfInit();
    }

    /**
     * Crfbtfs b <dodf>JFrbmf</dodf> witi tif spfdififd titlf bnd tif
     * spfdififd <dodf>GrbpiidsConfigurbtion</dodf> of b sdrffn dfvidf.
     * <p>
     * Tiis donstrudtor sfts tif domponfnt's lodblf propfrty to tif vbluf
     * rfturnfd by <dodf>JComponfnt.gftDffbultLodblf</dodf>.
     *
     * @pbrbm titlf tif titlf to bf displbyfd in tif
     *          frbmf's bordfr. A <dodf>null</dodf> vbluf is trfbtfd bs
     *          bn fmpty string, "".
     * @pbrbm gd tif <dodf>GrbpiidsConfigurbtion</dodf> tibt is usfd
     *          to donstrudt tif nfw <dodf>JFrbmf</dodf> witi;
     *          if <dodf>gd</dodf> is <dodf>null</dodf>, tif systfm
     *          dffbult <dodf>GrbpiidsConfigurbtion</dodf> is bssumfd
     * @fxdfption IllfgblArgumfntExdfption if <dodf>gd</dodf> is not from
     *          b sdrffn dfvidf.  Tiis fxdfption is blwbys tirown wifn
     *      GrbpiidsEnvironmfnt.isHfbdlfss() rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff JComponfnt#gftDffbultLodblf
     * @sindf     1.3
     */
    publid JFrbmf(String titlf, GrbpiidsConfigurbtion gd) {
        supfr(titlf, gd);
        frbmfInit();
    }

    /** Cbllfd by tif donstrudtors to init tif <dodf>JFrbmf</dodf> propfrly. */
    protfdtfd void frbmfInit() {
        fnbblfEvfnts(AWTEvfnt.KEY_EVENT_MASK | AWTEvfnt.WINDOW_EVENT_MASK);
        sftLodblf( JComponfnt.gftDffbultLodblf() );
        sftRootPbnf(drfbtfRootPbnf());
        sftBbdkground(UIMbnbgfr.gftColor("dontrol"));
        sftRootPbnfCifdkingEnbblfd(truf);
        if (JFrbmf.isDffbultLookAndFfflDfdorbtfd()) {
            boolfbn supportsWindowDfdorbtions =
            UIMbnbgfr.gftLookAndFffl().gftSupportsWindowDfdorbtions();
            if (supportsWindowDfdorbtions) {
                sftUndfdorbtfd(truf);
                gftRootPbnf().sftWindowDfdorbtionStylf(JRootPbnf.FRAME);
            }
        }
        sun.bwt.SunToolkit.difdkAndSftPolidy(tiis);
    }

    /**
     * Cbllfd by tif donstrudtor mftiods to drfbtf tif dffbult
     * <dodf>rootPbnf</dodf>.
     *
     * @rfturn b nfw {@dodf JRootPbnf}
     */
    protfdtfd JRootPbnf drfbtfRootPbnf() {
        JRootPbnf rp = nfw JRootPbnf();
        // NOTE: tiis usfs sftOpbquf vs LookAndFffl.instbllPropfrty bs tifrf
        // is NO rfbson for tif RootPbnf not to bf opbquf. For pbinting to
        // work tif dontfntPbnf must bf opbquf, tifrffor tif RootPbnf dbn
        // blso bf opbquf.
        rp.sftOpbquf(truf);
        rfturn rp;
    }

    /**
     * Prodfssfs window fvfnts oddurring on tiis domponfnt.
     * Hidfs tif window or disposfs of it, bs spfdififd by tif sftting
     * of tif <dodf>dffbultClosfOpfrbtion</dodf> propfrty.
     *
     * @pbrbm  f  tif window fvfnt
     * @sff    #sftDffbultClosfOpfrbtion
     * @sff    jbvb.bwt.Window#prodfssWindowEvfnt
     */
    protfdtfd void prodfssWindowEvfnt(WindowEvfnt f) {
        supfr.prodfssWindowEvfnt(f);

        if (f.gftID() == WindowEvfnt.WINDOW_CLOSING) {
            switdi(dffbultClosfOpfrbtion) {
              dbsf HIDE_ON_CLOSE:
                 sftVisiblf(fblsf);
                 brfbk;
              dbsf DISPOSE_ON_CLOSE:
                 disposf();
                 brfbk;
              dbsf DO_NOTHING_ON_CLOSE:
                 dffbult:
                 brfbk;
              dbsf EXIT_ON_CLOSE:
                  // Tiis nffds to mbtdi tif difdkExit dbll in
                  // sftDffbultClosfOpfrbtion
                Systfm.fxit(0);
                brfbk;
            }
        }
    }

//    publid void sftMfnuBbr(MfnuBbr mfnu) {
//        tirow nfw IllfgblComponfntStbtfExdfption("Plfbsf usf sftJMfnuBbr() witi JFrbmf.");
//    }

    /**
     * Sfts tif opfrbtion tibt will ibppfn by dffbult wifn
     * tif usfr initibtfs b "dlosf" on tiis frbmf.
     * You must spfdify onf of tif following dioidfs:
     * <br><br>
     * <ul>
     * <li><dodf>DO_NOTHING_ON_CLOSE</dodf>
     * (dffinfd in <dodf>WindowConstbnts</dodf>):
     * Don't do bnytiing; rfquirf tif
     * progrbm to ibndlf tif opfrbtion in tif <dodf>windowClosing</dodf>
     * mftiod of b rfgistfrfd <dodf>WindowListfnfr</dodf> objfdt.
     *
     * <li><dodf>HIDE_ON_CLOSE</dodf>
     * (dffinfd in <dodf>WindowConstbnts</dodf>):
     * Autombtidblly iidf tif frbmf bftfr
     * invoking bny rfgistfrfd <dodf>WindowListfnfr</dodf>
     * objfdts.
     *
     * <li><dodf>DISPOSE_ON_CLOSE</dodf>
     * (dffinfd in <dodf>WindowConstbnts</dodf>):
     * Autombtidblly iidf bnd disposf tif
     * frbmf bftfr invoking bny rfgistfrfd <dodf>WindowListfnfr</dodf>
     * objfdts.
     *
     * <li><dodf>EXIT_ON_CLOSE</dodf>
     * (dffinfd in <dodf>JFrbmf</dodf>):
     * Exit tif bpplidbtion using tif <dodf>Systfm</dodf>
     * <dodf>fxit</dodf> mftiod.  Usf tiis only in bpplidbtions.
     * </ul>
     * <p>
     * Tif vbluf is sft to <dodf>HIDE_ON_CLOSE</dodf> by dffbult. Cibngfs
     * to tif vbluf of tiis propfrty dbusf tif firing of b propfrty
     * dibngf fvfnt, witi propfrty nbmf "dffbultClosfOpfrbtion".
     * <p>
     * <b>Notf</b>: Wifn tif lbst displbybblf window witiin tif
     * Jbvb virtubl mbdiinf (VM) is disposfd of, tif VM mby
     * tfrminbtf.  Sff <b irff="../../jbvb/bwt/dod-filfs/AWTTirfbdIssufs.itml">
     * AWT Tirfbding Issufs</b> for morf informbtion.
     *
     * @pbrbm opfrbtion tif opfrbtion wiidi siould bf pfrformfd wifn tif
     *        usfr dlosfs tif frbmf
     * @fxdfption IllfgblArgumfntExdfption if dffbultClosfOpfrbtion vbluf
     *             isn't onf of tif bbovf vblid vblufs
     * @sff #bddWindowListfnfr
     * @sff #gftDffbultClosfOpfrbtion
     * @sff WindowConstbnts
     * @tirows  SfdurityExdfption
     *        if <dodf>EXIT_ON_CLOSE</dodf> ibs bffn spfdififd bnd tif
     *        <dodf>SfdurityMbnbgfr</dodf> will
     *        not bllow tif dbllfr to invokf <dodf>Systfm.fxit</dodf>
     * @sff        jbvb.lbng.Runtimf#fxit(int)
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     *        fnum: DO_NOTHING_ON_CLOSE WindowConstbnts.DO_NOTHING_ON_CLOSE
     *              HIDE_ON_CLOSE       WindowConstbnts.HIDE_ON_CLOSE
     *              DISPOSE_ON_CLOSE    WindowConstbnts.DISPOSE_ON_CLOSE
     *              EXIT_ON_CLOSE       WindowConstbnts.EXIT_ON_CLOSE
     * dfsdription: Tif frbmf's dffbult dlosf opfrbtion.
     */
    publid void sftDffbultClosfOpfrbtion(int opfrbtion) {
        if (opfrbtion != DO_NOTHING_ON_CLOSE &&
            opfrbtion != HIDE_ON_CLOSE &&
            opfrbtion != DISPOSE_ON_CLOSE &&
            opfrbtion != EXIT_ON_CLOSE) {
            tirow nfw IllfgblArgumfntExdfption("dffbultClosfOpfrbtion must bf onf of: DO_NOTHING_ON_CLOSE, HIDE_ON_CLOSE, DISPOSE_ON_CLOSE, or EXIT_ON_CLOSE");
        }

        if (opfrbtion == EXIT_ON_CLOSE) {
            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            if (sfdurity != null) {
                sfdurity.difdkExit(0);
            }
        }
        if (tiis.dffbultClosfOpfrbtion != opfrbtion) {
            int oldVbluf = tiis.dffbultClosfOpfrbtion;
            tiis.dffbultClosfOpfrbtion = opfrbtion;
            firfPropfrtyCibngf("dffbultClosfOpfrbtion", oldVbluf, opfrbtion);
        }
    }


   /**
    * Rfturns tif opfrbtion tibt oddurs wifn tif usfr
    * initibtfs b "dlosf" on tiis frbmf.
    *
    * @rfturn bn intfgfr indidbting tif window-dlosf opfrbtion
    * @sff #sftDffbultClosfOpfrbtion
    */
    publid int gftDffbultClosfOpfrbtion() {
        rfturn dffbultClosfOpfrbtion;
    }

    /**
     * Sfts tif {@dodf trbnsffrHbndlfr} propfrty, wiidi is b mfdibnism to
     * support trbnsffr of dbtb into tiis domponfnt. Usf {@dodf null}
     * if tif domponfnt dofs not support dbtb trbnsffr opfrbtions.
     * <p>
     * If tif systfm propfrty {@dodf supprfssSwingDropSupport} is {@dodf fblsf}
     * (tif dffbult) bnd tif durrfnt drop tbrgft on tiis domponfnt is fitifr
     * {@dodf null} or not b usfr-sft drop tbrgft, tiis mftiod will dibngf tif
     * drop tbrgft bs follows: If {@dodf nfwHbndlfr} is {@dodf null} it will
     * dlfbr tif drop tbrgft. If not {@dodf null} it will instbll b nfw
     * {@dodf DropTbrgft}.
     * <p>
     * Notf: Wifn usfd witi {@dodf JFrbmf}, {@dodf TrbnsffrHbndlfr} only
     * providfs dbtb import dbpbbility, bs tif dbtb fxport rflbtfd mftiods
     * brf durrfntly typfd to {@dodf JComponfnt}.
     * <p>
     * Plfbsf sff
     * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dnd/indfx.itml">
     * How to Usf Drbg bnd Drop bnd Dbtb Trbnsffr</b>, b sfdtion in
     * <fm>Tif Jbvb Tutoribl</fm>, for morf informbtion.
     *
     * @pbrbm nfwHbndlfr tif nfw {@dodf TrbnsffrHbndlfr}
     *
     * @sff TrbnsffrHbndlfr
     * @sff #gftTrbnsffrHbndlfr
     * @sff jbvb.bwt.Componfnt#sftDropTbrgft
     * @sindf 1.6
     *
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *  dfsdription: Mfdibnism for trbnsffr of dbtb into tif domponfnt
     */
    publid void sftTrbnsffrHbndlfr(TrbnsffrHbndlfr nfwHbndlfr) {
        TrbnsffrHbndlfr oldHbndlfr = trbnsffrHbndlfr;
        trbnsffrHbndlfr = nfwHbndlfr;
        SwingUtilitifs.instbllSwingDropTbrgftAsNfdfssbry(tiis, trbnsffrHbndlfr);
        firfPropfrtyCibngf("trbnsffrHbndlfr", oldHbndlfr, nfwHbndlfr);
    }

    /**
     * Gfts tif <dodf>trbnsffrHbndlfr</dodf> propfrty.
     *
     * @rfturn tif vbluf of tif <dodf>trbnsffrHbndlfr</dodf> propfrty
     *
     * @sff TrbnsffrHbndlfr
     * @sff #sftTrbnsffrHbndlfr
     * @sindf 1.6
     */
    publid TrbnsffrHbndlfr gftTrbnsffrHbndlfr() {
        rfturn trbnsffrHbndlfr;
    }

    /**
     * Just dblls <dodf>pbint(g)</dodf>.  Tiis mftiod wbs ovfrriddfn to
     * prfvfnt bn unnfdfssbry dbll to dlfbr tif bbdkground.
     *
     * @pbrbm g tif Grbpiids dontfxt in wiidi to pbint
     */
    publid void updbtf(Grbpiids g) {
        pbint(g);
    }

   /**
    * Sfts tif mfnubbr for tiis frbmf.
    * @pbrbm mfnubbr tif mfnubbr bfing plbdfd in tif frbmf
    *
    * @sff #gftJMfnuBbr
    *
    * @bfbninfo
    *      iiddfn: truf
    * dfsdription: Tif mfnubbr for bddfssing pulldown mfnus from tiis frbmf.
    */
    publid void sftJMfnuBbr(JMfnuBbr mfnubbr) {
        gftRootPbnf().sftMfnuBbr(mfnubbr);
    }

   /**
    * Rfturns tif mfnubbr sft on tiis frbmf.
    * @rfturn tif mfnubbr for tiis frbmf
    *
    * @sff #sftJMfnuBbr
    */
    publid JMfnuBbr gftJMfnuBbr() {
        rfturn gftRootPbnf().gftMfnuBbr();
    }

    /**
     * Rfturns wiftifr dblls to <dodf>bdd</dodf> bnd
     * <dodf>sftLbyout</dodf> brf forwbrdfd to tif <dodf>dontfntPbnf</dodf>.
     *
     * @rfturn truf if <dodf>bdd</dodf> bnd <dodf>sftLbyout</dodf>
     *         brf forwbrdfd; fblsf otifrwisf
     *
     * @sff #bddImpl
     * @sff #sftLbyout
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    protfdtfd boolfbn isRootPbnfCifdkingEnbblfd() {
        rfturn rootPbnfCifdkingEnbblfd;
    }


    /**
     * Sfts wiftifr dblls to <dodf>bdd</dodf> bnd
     * <dodf>sftLbyout</dodf> brf forwbrdfd to tif <dodf>dontfntPbnf</dodf>.
     *
     * @pbrbm fnbblfd  truf if <dodf>bdd</dodf> bnd <dodf>sftLbyout</dodf>
     *        brf forwbrdfd, fblsf if tify siould opfrbtf dirfdtly on tif
     *        <dodf>JFrbmf</dodf>.
     *
     * @sff #bddImpl
     * @sff #sftLbyout
     * @sff #isRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     * @bfbninfo
     *      iiddfn: truf
     * dfsdription: Wiftifr tif bdd bnd sftLbyout mftiods brf forwbrdfd
     */
    protfdtfd void sftRootPbnfCifdkingEnbblfd(boolfbn fnbblfd) {
        rootPbnfCifdkingEnbblfd = fnbblfd;
    }


    /**
     * Adds tif spfdififd diild <dodf>Componfnt</dodf>.
     * Tiis mftiod is ovfrriddfn to donditionblly forwbrd dblls to tif
     * <dodf>dontfntPbnf</dodf>.
     * By dffbult, diildrfn brf bddfd to tif <dodf>dontfntPbnf</dodf> instfbd
     * of tif frbmf, rfffr to {@link jbvbx.swing.RootPbnfContbinfr} for
     * dftbils.
     *
     * @pbrbm domp tif domponfnt to bf fnibndfd
     * @pbrbm donstrbints tif donstrbints to bf rfspfdtfd
     * @pbrbm indfx tif indfx
     * @fxdfption IllfgblArgumfntExdfption if <dodf>indfx</dodf> is invblid
     * @fxdfption IllfgblArgumfntExdfption if bdding tif dontbinfr's pbrfnt
     *                  to itsflf
     * @fxdfption IllfgblArgumfntExdfption if bdding b window to b dontbinfr
     *
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    protfdtfd void bddImpl(Componfnt domp, Objfdt donstrbints, int indfx)
    {
        if(isRootPbnfCifdkingEnbblfd()) {
            gftContfntPbnf().bdd(domp, donstrbints, indfx);
        }
        flsf {
            supfr.bddImpl(domp, donstrbints, indfx);
        }
    }

    /**
     * Rfmovfs tif spfdififd domponfnt from tif dontbinfr. If
     * <dodf>domp</dodf> is not tif <dodf>rootPbnf</dodf>, tiis will forwbrd
     * tif dbll to tif <dodf>dontfntPbnf</dodf>. Tiis will do notiing if
     * <dodf>domp</dodf> is not b diild of tif <dodf>JFrbmf</dodf> or
     * <dodf>dontfntPbnf</dodf>.
     *
     * @pbrbm domp tif domponfnt to bf rfmovfd
     * @tirows NullPointfrExdfption if <dodf>domp</dodf> is null
     * @sff #bdd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    publid void rfmovf(Componfnt domp) {
        if (domp == rootPbnf) {
            supfr.rfmovf(domp);
        } flsf {
            gftContfntPbnf().rfmovf(domp);
        }
    }


    /**
     * Sfts tif <dodf>LbyoutMbnbgfr</dodf>.
     * Ovfrriddfn to donditionblly forwbrd tif dbll to tif
     * <dodf>dontfntPbnf</dodf>.
     * Rfffr to {@link jbvbx.swing.RootPbnfContbinfr} for
     * morf informbtion.
     *
     * @pbrbm mbnbgfr tif <dodf>LbyoutMbnbgfr</dodf>
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    publid void sftLbyout(LbyoutMbnbgfr mbnbgfr) {
        if(isRootPbnfCifdkingEnbblfd()) {
            gftContfntPbnf().sftLbyout(mbnbgfr);
        }
        flsf {
            supfr.sftLbyout(mbnbgfr);
        }
    }


    /**
     * Rfturns tif <dodf>rootPbnf</dodf> objfdt for tiis frbmf.
     * @rfturn tif <dodf>rootPbnf</dodf> propfrty
     *
     * @sff #sftRootPbnf
     * @sff RootPbnfContbinfr#gftRootPbnf
     */
    publid JRootPbnf gftRootPbnf() {
        rfturn rootPbnf;
    }


    /**
     * Sfts tif <dodf>rootPbnf</dodf> propfrty.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     * @pbrbm root tif <dodf>rootPbnf</dodf> objfdt for tiis frbmf
     *
     * @sff #gftRootPbnf
     *
     * @bfbninfo
     *   iiddfn: truf
     * dfsdription: tif RootPbnf objfdt for tiis frbmf.
     */
    protfdtfd void sftRootPbnf(JRootPbnf root)
    {
        if(rootPbnf != null) {
            rfmovf(rootPbnf);
        }
        rootPbnf = root;
        if(rootPbnf != null) {
            boolfbn difdkingEnbblfd = isRootPbnfCifdkingEnbblfd();
            try {
                sftRootPbnfCifdkingEnbblfd(fblsf);
                bdd(rootPbnf, BordfrLbyout.CENTER);
            }
            finblly {
                sftRootPbnfCifdkingEnbblfd(difdkingEnbblfd);
            }
        }
    }

    /**
     * {@inifritDod}
     */
    publid void sftIdonImbgf(Imbgf imbgf) {
        supfr.sftIdonImbgf(imbgf);
    }

    /**
     * Rfturns tif <dodf>dontfntPbnf</dodf> objfdt for tiis frbmf.
     * @rfturn tif <dodf>dontfntPbnf</dodf> propfrty
     *
     * @sff #sftContfntPbnf
     * @sff RootPbnfContbinfr#gftContfntPbnf
     */
    publid Contbinfr gftContfntPbnf() {
        rfturn gftRootPbnf().gftContfntPbnf();
    }

    /**
     * Sfts tif <dodf>dontfntPbnf</dodf> propfrty.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     * <p>
     * Swing's pbinting brdiitfdturf rfquirfs bn opbquf <dodf>JComponfnt</dodf>
     * in tif dontbinmfnt iifrbrdiy. Tiis is typidblly providfd by tif
     * dontfnt pbnf. If you rfplbdf tif dontfnt pbnf it is rfdommfndfd you
     * rfplbdf it witi bn opbquf <dodf>JComponfnt</dodf>.
     *
     * @pbrbm dontfntPbnf tif <dodf>dontfntPbnf</dodf> objfdt for tiis frbmf
     *
     * @fxdfption jbvb.bwt.IllfgblComponfntStbtfExdfption (b runtimf
     *            fxdfption) if tif dontfnt pbnf pbrbmftfr is <dodf>null</dodf>
     * @sff #gftContfntPbnf
     * @sff RootPbnfContbinfr#sftContfntPbnf
     * @sff JRootPbnf
     *
     * @bfbninfo
     *     iiddfn: truf
     *     dfsdription: Tif dlifnt brfb of tif frbmf wifrf diild
     *                  domponfnts brf normblly insfrtfd.
     */
    publid void sftContfntPbnf(Contbinfr dontfntPbnf) {
        gftRootPbnf().sftContfntPbnf(dontfntPbnf);
    }

    /**
     * Rfturns tif <dodf>lbyfrfdPbnf</dodf> objfdt for tiis frbmf.
     * @rfturn tif <dodf>lbyfrfdPbnf</dodf> propfrty
     *
     * @sff #sftLbyfrfdPbnf
     * @sff RootPbnfContbinfr#gftLbyfrfdPbnf
     */
    publid JLbyfrfdPbnf gftLbyfrfdPbnf() {
        rfturn gftRootPbnf().gftLbyfrfdPbnf();
    }

    /**
     * Sfts tif <dodf>lbyfrfdPbnf</dodf> propfrty.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     * @pbrbm lbyfrfdPbnf tif <dodf>lbyfrfdPbnf</dodf> objfdt for tiis frbmf
     *
     * @fxdfption jbvb.bwt.IllfgblComponfntStbtfExdfption (b runtimf
     *            fxdfption) if tif lbyfrfd pbnf pbrbmftfr is <dodf>null</dodf>
     * @sff #gftLbyfrfdPbnf
     * @sff RootPbnfContbinfr#sftLbyfrfdPbnf
     *
     * @bfbninfo
     *     iiddfn: truf
     *     dfsdription: Tif pbnf tibt iolds tif vbrious frbmf lbyfrs.
     */
    publid void sftLbyfrfdPbnf(JLbyfrfdPbnf lbyfrfdPbnf) {
        gftRootPbnf().sftLbyfrfdPbnf(lbyfrfdPbnf);
    }

    /**
     * Rfturns tif <dodf>glbssPbnf</dodf> objfdt for tiis frbmf.
     * @rfturn tif <dodf>glbssPbnf</dodf> propfrty
     *
     * @sff #sftGlbssPbnf
     * @sff RootPbnfContbinfr#gftGlbssPbnf
     */
    publid Componfnt gftGlbssPbnf() {
        rfturn gftRootPbnf().gftGlbssPbnf();
    }

    /**
     * Sfts tif <dodf>glbssPbnf</dodf> propfrty.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     * @pbrbm glbssPbnf tif <dodf>glbssPbnf</dodf> objfdt for tiis frbmf
     *
     * @sff #gftGlbssPbnf
     * @sff RootPbnfContbinfr#sftGlbssPbnf
     *
     * @bfbninfo
     *     iiddfn: truf
     *     dfsdription: A trbnspbrfnt pbnf usfd for mfnu rfndfring.
     */
    publid void sftGlbssPbnf(Componfnt glbssPbnf) {
        gftRootPbnf().sftGlbssPbnf(glbssPbnf);
    }

    /**
     * {@inifritDod}
     *
     * @sindf 1.6
     */
    publid Grbpiids gftGrbpiids() {
        JComponfnt.gftGrbpiidsInvokfd(tiis);
        rfturn supfr.gftGrbpiids();
    }

    /**
     * Rfpbints tif spfdififd rfdtbnglf of tiis domponfnt witiin
     * <dodf>timf</dodf> millisfdonds.  Rfffr to <dodf>RfpbintMbnbgfr</dodf>
     * for dftbils on iow tif rfpbint is ibndlfd.
     *
     * @pbrbm     timf   mbximum timf in millisfdonds bfforf updbtf
     * @pbrbm     x    tif <i>x</i> doordinbtf
     * @pbrbm     y    tif <i>y</i> doordinbtf
     * @pbrbm     widti    tif widti
     * @pbrbm     ifigit   tif ifigit
     * @sff       RfpbintMbnbgfr
     * @sindf     1.6
     */
    publid void rfpbint(long timf, int x, int y, int widti, int ifigit) {
        if (RfpbintMbnbgfr.HANDLE_TOP_LEVEL_PAINT) {
            RfpbintMbnbgfr.durrfntMbnbgfr(tiis).bddDirtyRfgion(
                              tiis, x, y, widti, ifigit);
        }
        flsf {
            supfr.rfpbint(timf, x, y, widti, ifigit);
        }
    }

    /**
     * Providfs b iint bs to wiftifr or not nfwly drfbtfd <dodf>JFrbmf</dodf>s
     * siould ibvf tifir Window dfdorbtions (sudi bs bordfrs, widgfts to
     * dlosf tif window, titlf...) providfd by tif durrfnt look
     * bnd fffl. If <dodf>dffbultLookAndFfflDfdorbtfd</dodf> is truf,
     * tif durrfnt <dodf>LookAndFffl</dodf> supports providing window
     * dfdorbtions, bnd tif durrfnt window mbnbgfr supports undfdorbtfd
     * windows, tifn nfwly drfbtfd <dodf>JFrbmf</dodf>s will ibvf tifir
     * Window dfdorbtions providfd by tif durrfnt <dodf>LookAndFffl</dodf>.
     * Otifrwisf, nfwly drfbtfd <dodf>JFrbmf</dodf>s will ibvf tifir
     * Window dfdorbtions providfd by tif durrfnt window mbnbgfr.
     * <p>
     * You dbn gft tif sbmf ffffdt on b singlf JFrbmf by doing tif following:
     * <prf>
     *    JFrbmf frbmf = nfw JFrbmf();
     *    frbmf.sftUndfdorbtfd(truf);
     *    frbmf.gftRootPbnf().sftWindowDfdorbtionStylf(JRootPbnf.FRAME);
     * </prf>
     *
     * @pbrbm dffbultLookAndFfflDfdorbtfd A iint bs to wiftifr or not durrfnt
     *        look bnd fffl siould providf window dfdorbtions
     * @sff jbvbx.swing.LookAndFffl#gftSupportsWindowDfdorbtions
     * @sindf 1.4
     */
    publid stbtid void sftDffbultLookAndFfflDfdorbtfd(boolfbn dffbultLookAndFfflDfdorbtfd) {
        if (dffbultLookAndFfflDfdorbtfd) {
            SwingUtilitifs.bppContfxtPut(dffbultLookAndFfflDfdorbtfdKfy, Boolfbn.TRUE);
        } flsf {
            SwingUtilitifs.bppContfxtPut(dffbultLookAndFfflDfdorbtfdKfy, Boolfbn.FALSE);
        }
    }


    /**
     * Rfturns truf if nfwly drfbtfd <dodf>JFrbmf</dodf>s siould ibvf tifir
     * Window dfdorbtions providfd by tif durrfnt look bnd fffl. Tiis is only
     * b iint, bs dfrtbin look bnd fffls mby not support tiis ffbturf.
     *
     * @rfturn truf if look bnd fffl siould providf Window dfdorbtions.
     * @sindf 1.4
     */
    publid stbtid boolfbn isDffbultLookAndFfflDfdorbtfd() {
        Boolfbn dffbultLookAndFfflDfdorbtfd =
            (Boolfbn) SwingUtilitifs.bppContfxtGft(dffbultLookAndFfflDfdorbtfdKfy);
        if (dffbultLookAndFfflDfdorbtfd == null) {
            dffbultLookAndFfflDfdorbtfd = Boolfbn.FALSE;
        }
        rfturn dffbultLookAndFfflDfdorbtfd.boolfbnVbluf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JFrbmf</dodf>.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JFrbmf</dodf>
     */
    protfdtfd String pbrbmString() {
        String dffbultClosfOpfrbtionString;
        if (dffbultClosfOpfrbtion == HIDE_ON_CLOSE) {
            dffbultClosfOpfrbtionString = "HIDE_ON_CLOSE";
        } flsf if (dffbultClosfOpfrbtion == DISPOSE_ON_CLOSE) {
            dffbultClosfOpfrbtionString = "DISPOSE_ON_CLOSE";
        } flsf if (dffbultClosfOpfrbtion == DO_NOTHING_ON_CLOSE) {
            dffbultClosfOpfrbtionString = "DO_NOTHING_ON_CLOSE";
        } flsf if (dffbultClosfOpfrbtion == 3) {
            dffbultClosfOpfrbtionString = "EXIT_ON_CLOSE";
        } flsf dffbultClosfOpfrbtionString = "";
        String rootPbnfString = (rootPbnf != null ?
                                 rootPbnf.toString() : "");
        String rootPbnfCifdkingEnbblfdString = (rootPbnfCifdkingEnbblfd ?
                                                "truf" : "fblsf");

        rfturn supfr.pbrbmString() +
        ",dffbultClosfOpfrbtion=" + dffbultClosfOpfrbtionString +
        ",rootPbnf=" + rootPbnfString +
        ",rootPbnfCifdkingEnbblfd=" + rootPbnfCifdkingEnbblfdString;
    }



/////////////////
// Addfssibility support
////////////////

    /**
     * Tif bddfssiblf dontfxt propfrty.
     */
    protfdtfd AddfssiblfContfxt bddfssiblfContfxt = null;

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JFrbmf.
     * For JFrbmfs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJFrbmf.
     * A nfw AddfssiblfJFrbmf instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJFrbmf tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JFrbmf
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJFrbmf();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JFrbmf</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to frbmf usfr-intfrfbdf
     * flfmfnts.
     */
    protfdtfd dlbss AddfssiblfJFrbmf fxtfnds AddfssiblfAWTFrbmf {

        // AddfssiblfContfxt mftiods
        /**
         * Gft tif bddfssiblf nbmf of tiis objfdt.
         *
         * @rfturn tif lodblizfd nbmf of tif objfdt -- dbn bf null if tiis
         * objfdt dofs not ibvf b nbmf
         */
        publid String gftAddfssiblfNbmf() {
            if (bddfssiblfNbmf != null) {
                rfturn bddfssiblfNbmf;
            } flsf {
                if (gftTitlf() == null) {
                    rfturn supfr.gftAddfssiblfNbmf();
                } flsf {
                    rfturn gftTitlf();
                }
            }
        }

        /**
         * Gft tif stbtf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dontbining tif durrfnt
         * stbtf sft of tif objfdt
         * @sff AddfssiblfStbtf
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();

            if (isRfsizbblf()) {
                stbtfs.bdd(AddfssiblfStbtf.RESIZABLE);
            }
            if (gftFodusOwnfr() != null) {
                stbtfs.bdd(AddfssiblfStbtf.ACTIVE);
            }
            // FIXME:  [[[WDW - siould blso rfturn ICONIFIED bnd ICONIFIABLE
            // if wf dbn fvfr figurf tifsf out]]]
            rfturn stbtfs;
        }
    } // innfr dlbss AddfssiblfJFrbmf
}
