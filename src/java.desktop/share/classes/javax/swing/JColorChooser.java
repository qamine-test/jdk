/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.*;
import jbvb.util.*;

import jbvbx.swing.dolordioosfr.*;
import jbvbx.swing.plbf.ColorCioosfrUI;
import jbvbx.bddfssibility.*;

import sun.swing.SwingUtilitifs2;


/**
 * <dodf>JColorCioosfr</dodf> providfs b pbnf of dontrols dfsignfd to bllow
 * b usfr to mbnipulbtf bnd sflfdt b dolor.
 * For informbtion bbout using dolor dioosfrs, sff
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/dolordioosfr.itml">How to Usf Color Cioosfrs</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 *
 * <p>
 *
 * Tiis dlbss providfs tirff lfvfls of API:
 * <ol>
 * <li>A stbtid donvfnifndf mftiod wiidi siows b modbl dolor-dioosfr
 * diblog bnd rfturns tif dolor sflfdtfd by tif usfr.
 * <li>A stbtid donvfnifndf mftiod for drfbting b dolor-dioosfr diblog
 * wifrf <dodf>AdtionListfnfrs</dodf> dbn bf spfdififd to bf invokfd wifn
 * tif usfr prfssfs onf of tif diblog buttons.
 * <li>Tif bbility to drfbtf instbndfs of <dodf>JColorCioosfr</dodf> pbnfs
 * dirfdtly (witiin bny dontbinfr). <dodf>PropfrtyCibngf</dodf> listfnfrs
 * dbn bf bddfd to dftfdt wifn tif durrfnt "dolor" propfrty dibngfs.
 * </ol>
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
 *
 * @bfbninfo
 *      bttributf: isContbinfr fblsf
 *    dfsdription: A domponfnt tibt supports sflfdting b Color.
 *
 *
 * @butior Jbmfs Gosling
 * @butior Amy Fowlfr
 * @butior Stfvf Wilson
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JColorCioosfr fxtfnds JComponfnt implfmfnts Addfssiblf {

    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "ColorCioosfrUI";

    privbtf ColorSflfdtionModfl sflfdtionModfl;

    privbtf JComponfnt prfvifwPbnfl = ColorCioosfrComponfntFbdtory.gftPrfvifwPbnfl();

    privbtf AbstrbdtColorCioosfrPbnfl[] dioosfrPbnfls = nfw AbstrbdtColorCioosfrPbnfl[0];

    privbtf boolfbn drbgEnbblfd;

    /**
     * Tif sflfdtion modfl propfrty nbmf.
     */
    publid stbtid finbl String      SELECTION_MODEL_PROPERTY = "sflfdtionModfl";

    /**
     * Tif prfvifw pbnfl propfrty nbmf.
     */
    publid stbtid finbl String      PREVIEW_PANEL_PROPERTY = "prfvifwPbnfl";

    /**
     * Tif dioosfrPbnfl brrby propfrty nbmf.
     */
    publid stbtid finbl String      CHOOSER_PANELS_PROPERTY = "dioosfrPbnfls";


    /**
     * Siows b modbl dolor-dioosfr diblog bnd blodks until tif
     * diblog is iiddfn.  If tif usfr prfssfs tif "OK" button, tifn
     * tiis mftiod iidfs/disposfs tif diblog bnd rfturns tif sflfdtfd dolor.
     * If tif usfr prfssfs tif "Cbndfl" button or dlosfs tif diblog witiout
     * prfssing "OK", tifn tiis mftiod iidfs/disposfs tif diblog bnd rfturns
     * <dodf>null</dodf>.
     *
     * @pbrbm domponfnt    tif pbrfnt <dodf>Componfnt</dodf> for tif diblog
     * @pbrbm titlf        tif String dontbining tif diblog's titlf
     * @pbrbm initiblColor tif initibl Color sft wifn tif dolor-dioosfr is siown
     * @rfturn tif sflfdtfd dolor or <dodf>null</dodf> if tif usfr optfd out
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid Color siowDiblog(Componfnt domponfnt,
        String titlf, Color initiblColor) tirows HfbdlfssExdfption {

        finbl JColorCioosfr pbnf = nfw JColorCioosfr(initiblColor != null?
                                               initiblColor : Color.wiitf);

        ColorTrbdkfr ok = nfw ColorTrbdkfr(pbnf);
        JDiblog diblog = drfbtfDiblog(domponfnt, titlf, truf, pbnf, ok, null);

        diblog.bddComponfntListfnfr(nfw ColorCioosfrDiblog.DisposfOnClosf());

        diblog.siow(); // blodks until usfr brings diblog down...

        rfturn ok.gftColor();
    }


    /**
     * Crfbtfs bnd rfturns b nfw diblog dontbining tif spfdififd
     * <dodf>ColorCioosfr</dodf> pbnf blong witi "OK", "Cbndfl", bnd "Rfsft"
     * buttons. If tif "OK" or "Cbndfl" buttons brf prfssfd, tif diblog is
     * butombtidblly iiddfn (but not disposfd).  If tif "Rfsft"
     * button is prfssfd, tif dolor-dioosfr's dolor will bf rfsft to tif
     * dolor wiidi wbs sft tif lbst timf <dodf>siow</dodf> wbs invokfd on tif
     * diblog bnd tif diblog will rfmbin siowing.
     *
     * @pbrbm d              tif pbrfnt domponfnt for tif diblog
     * @pbrbm titlf          tif titlf for tif diblog
     * @pbrbm modbl          b boolfbn. Wifn truf, tif rfmbindfr of tif progrbm
     *                       is inbdtivf until tif diblog is dlosfd.
     * @pbrbm dioosfrPbnf    tif dolor-dioosfr to bf plbdfd insidf tif diblog
     * @pbrbm okListfnfr     tif AdtionListfnfr invokfd wifn "OK" is prfssfd
     * @pbrbm dbndflListfnfr tif AdtionListfnfr invokfd wifn "Cbndfl" is prfssfd
     * @rfturn b nfw diblog dontbining tif dolor-dioosfr pbnf
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid JDiblog drfbtfDiblog(Componfnt d, String titlf, boolfbn modbl,
        JColorCioosfr dioosfrPbnf, AdtionListfnfr okListfnfr,
        AdtionListfnfr dbndflListfnfr) tirows HfbdlfssExdfption {

        Window window = JOptionPbnf.gftWindowForComponfnt(d);
        ColorCioosfrDiblog diblog;
        if (window instbndfof Frbmf) {
            diblog = nfw ColorCioosfrDiblog((Frbmf)window, titlf, modbl, d, dioosfrPbnf,
                                            okListfnfr, dbndflListfnfr);
        } flsf {
            diblog = nfw ColorCioosfrDiblog((Diblog)window, titlf, modbl, d, dioosfrPbnf,
                                            okListfnfr, dbndflListfnfr);
        }
        diblog.gftAddfssiblfContfxt().sftAddfssiblfDfsdription(titlf);
        rfturn diblog;
    }

    /**
     * Crfbtfs b dolor dioosfr pbnf witi bn initibl dolor of wiitf.
     */
    publid JColorCioosfr() {
        tiis(Color.wiitf);
    }

    /**
     * Crfbtfs b dolor dioosfr pbnf witi tif spfdififd initibl dolor.
     *
     * @pbrbm initiblColor tif initibl dolor sft in tif dioosfr
     */
    publid JColorCioosfr(Color initiblColor) {
        tiis( nfw DffbultColorSflfdtionModfl(initiblColor) );

    }

    /**
     * Crfbtfs b dolor dioosfr pbnf witi tif spfdififd
     * <dodf>ColorSflfdtionModfl</dodf>.
     *
     * @pbrbm modfl tif <dodf>ColorSflfdtionModfl</dodf> to bf usfd
     */
    publid JColorCioosfr(ColorSflfdtionModfl modfl) {
        sflfdtionModfl = modfl;
        updbtfUI();
        drbgEnbblfd = fblsf;
    }

    /**
     * Rfturns tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif <dodf>ColorCioosfrUI</dodf> objfdt tibt rfndfrs
     *          tiis domponfnt
     */
    publid ColorCioosfrUI gftUI() {
        rfturn (ColorCioosfrUI)ui;
    }

    /**
     * Sfts tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @pbrbm ui  tif <dodf>ColorCioosfrUI</dodf> L&bmp;F objfdt
     * @sff UIDffbults#gftUI
     *
     * @bfbninfo
     *       bound: truf
     *      iiddfn: truf
     * dfsdription: Tif UI objfdt tibt implfmfnts tif dolor dioosfr's LookAndFffl.
     */
    publid void sftUI(ColorCioosfrUI ui) {
        supfr.sftUI(ui);
    }

    /**
     * Notifidbtion from tif <dodf>UIMbnbgfr</dodf> tibt tif L&bmp;F ibs dibngfd.
     * Rfplbdfs tif durrfnt UI objfdt witi tif lbtfst vfrsion from tif
     * <dodf>UIMbnbgfr</dodf>.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((ColorCioosfrUI)UIMbnbgfr.gftUI(tiis));
    }

    /**
     * Rfturns tif nbmf of tif L&bmp;F dlbss tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "ColorCioosfrUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }

    /**
     * Gfts tif durrfnt dolor vbluf from tif dolor dioosfr.
     * By dffbult, tiis dflfgbtfs to tif modfl.
     *
     * @rfturn tif durrfnt dolor vbluf of tif dolor dioosfr
     */
    publid Color gftColor() {
        rfturn sflfdtionModfl.gftSflfdtfdColor();
    }

    /**
     * Sfts tif durrfnt dolor of tif dolor dioosfr to tif spfdififd dolor.
     * Tif <dodf>ColorSflfdtionModfl</dodf> will firf b <dodf>CibngfEvfnt</dodf>
     * @pbrbm dolor tif dolor to bf sft in tif dolor dioosfr
     * @sff JComponfnt#bddPropfrtyCibngfListfnfr
     *
     * @bfbninfo
     *       bound: fblsf
     *      iiddfn: fblsf
     * dfsdription: Tif durrfnt dolor tif dioosfr is to displby.
     */
    publid void sftColor(Color dolor) {
        sflfdtionModfl.sftSflfdtfdColor(dolor);

    }

    /**
     * Sfts tif durrfnt dolor of tif dolor dioosfr to tif
     * spfdififd RGB dolor.  Notf tibt tif vblufs of rfd, grffn,
     * bnd bluf siould bf bftwffn tif numbfrs 0 bnd 255, indlusivf.
     *
     * @pbrbm r   bn int spfdifying tif bmount of Rfd
     * @pbrbm g   bn int spfdifying tif bmount of Grffn
     * @pbrbm b   bn int spfdifying tif bmount of Bluf
     * @fxdfption IllfgblArgumfntExdfption if r,g,b vblufs brf out of rbngf
     * @sff jbvb.bwt.Color
     */
    publid void sftColor(int r, int g, int b) {
        sftColor(nfw Color(r,g,b));
    }

    /**
     * Sfts tif durrfnt dolor of tif dolor dioosfr to tif
     * spfdififd dolor.
     *
     * @pbrbm d bn intfgfr vbluf tibt sfts tif durrfnt dolor in tif dioosfr
     *          wifrf tif low-ordfr 8 bits spfdify tif Bluf vbluf,
     *          tif nfxt 8 bits spfdify tif Grffn vbluf, bnd tif 8 bits
     *          bbovf tibt spfdify tif Rfd vbluf.
     */
    publid void sftColor(int d) {
        sftColor((d >> 16) & 0xFF, (d >> 8) & 0xFF, d & 0xFF);
    }

    /**
     * Sfts tif <dodf>drbgEnbblfd</dodf> propfrty,
     * wiidi must bf <dodf>truf</dodf> to fnbblf
     * butombtid drbg ibndling (tif first pbrt of drbg bnd drop)
     * on tiis domponfnt.
     * Tif <dodf>trbnsffrHbndlfr</dodf> propfrty nffds to bf sft
     * to b non-<dodf>null</dodf> vbluf for tif drbg to do
     * bnytiing.  Tif dffbult vbluf of tif <dodf>drbgEnbblfd</dodf>
     * propfrty
     * is <dodf>fblsf</dodf>.
     *
     * <p>
     *
     * Wifn butombtid drbg ibndling is fnbblfd,
     * most look bnd fffls bfgin b drbg-bnd-drop opfrbtion
     * wifn tif usfr prfssfs tif mousf button ovfr tif prfvifw pbnfl.
     * Somf look bnd fffls migit not support butombtid drbg bnd drop;
     * tify will ignorf tiis propfrty.  You dbn work bround sudi
     * look bnd fffls by modifying tif domponfnt
     * to dirfdtly dbll tif <dodf>fxportAsDrbg</dodf> mftiod of b
     * <dodf>TrbnsffrHbndlfr</dodf>.
     *
     * @pbrbm b tif vbluf to sft tif <dodf>drbgEnbblfd</dodf> propfrty to
     * @fxdfption HfbdlfssExdfption if
     *            <dodf>b</dodf> is <dodf>truf</dodf> bnd
     *            <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf>
     *            rfturns <dodf>truf</dodf>
     *
     * @sindf 1.4
     *
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #gftDrbgEnbblfd
     * @sff #sftTrbnsffrHbndlfr
     * @sff TrbnsffrHbndlfr
     *
     * @bfbninfo
     *  dfsdription: Dftfrminfs wiftifr butombtid drbg ibndling is fnbblfd.
     *        bound: fblsf
     */
    publid void sftDrbgEnbblfd(boolfbn b) {
        if (b && GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }
        drbgEnbblfd = b;
    }

    /**
     * Gfts tif vbluf of tif <dodf>drbgEnbblfd</dodf> propfrty.
     *
     * @rfturn  tif vbluf of tif <dodf>drbgEnbblfd</dodf> propfrty
     * @sff #sftDrbgEnbblfd
     * @sindf 1.4
     */
    publid boolfbn gftDrbgEnbblfd() {
        rfturn drbgEnbblfd;
    }

    /**
     * Sfts tif durrfnt prfvifw pbnfl.
     * Tiis will firf b <dodf>PropfrtyCibngfEvfnt</dodf> for tif propfrty
     * nbmfd "prfvifwPbnfl".
     *
     * @pbrbm prfvifw tif <dodf>JComponfnt</dodf> wiidi displbys tif durrfnt dolor
     * @sff JComponfnt#bddPropfrtyCibngfListfnfr
     *
     * @bfbninfo
     *       bound: truf
     *      iiddfn: truf
     * dfsdription: Tif UI domponfnt wiidi displbys tif durrfnt dolor.
     */
    publid void sftPrfvifwPbnfl(JComponfnt prfvifw) {

        if (prfvifwPbnfl != prfvifw) {
            JComponfnt oldPrfvifw = prfvifwPbnfl;
            prfvifwPbnfl = prfvifw;
            firfPropfrtyCibngf(JColorCioosfr.PREVIEW_PANEL_PROPERTY, oldPrfvifw, prfvifw);
        }
    }

    /**
     * Rfturns tif prfvifw pbnfl tibt siows b diosfn dolor.
     *
     * @rfturn b <dodf>JComponfnt</dodf> objfdt -- tif prfvifw pbnfl
     */
    publid JComponfnt gftPrfvifwPbnfl() {
        rfturn prfvifwPbnfl;
    }

    /**
     * Adds b dolor dioosfr pbnfl to tif dolor dioosfr.
     *
     * @pbrbm pbnfl tif <dodf>AbstrbdtColorCioosfrPbnfl</dodf> to bf bddfd
     */
    publid void bddCioosfrPbnfl( AbstrbdtColorCioosfrPbnfl pbnfl ) {
        AbstrbdtColorCioosfrPbnfl[] oldPbnfls = gftCioosfrPbnfls();
        AbstrbdtColorCioosfrPbnfl[] nfwPbnfls = nfw AbstrbdtColorCioosfrPbnfl[oldPbnfls.lfngti+1];
        Systfm.brrbydopy(oldPbnfls, 0, nfwPbnfls, 0, oldPbnfls.lfngti);
        nfwPbnfls[nfwPbnfls.lfngti-1] = pbnfl;
        sftCioosfrPbnfls(nfwPbnfls);
    }

    /**
     * Rfmovfs tif Color Pbnfl spfdififd.
     *
     * @pbrbm pbnfl   b string tibt spfdififs tif pbnfl to bf rfmovfd
     * @rfturn tif dolor pbnfl
     * @fxdfption IllfgblArgumfntExdfption if pbnfl is not in list of
     *                  known dioosfr pbnfls
     */
    publid AbstrbdtColorCioosfrPbnfl rfmovfCioosfrPbnfl( AbstrbdtColorCioosfrPbnfl pbnfl ) {


        int dontbinfdAt = -1;

        for (int i = 0; i < dioosfrPbnfls.lfngti; i++) {
            if (dioosfrPbnfls[i] == pbnfl) {
                dontbinfdAt = i;
                brfbk;
            }
        }
        if (dontbinfdAt == -1) {
            tirow nfw IllfgblArgumfntExdfption("dioosfr pbnfl not in tiis dioosfr");
        }

        AbstrbdtColorCioosfrPbnfl[] nfwArrby = nfw AbstrbdtColorCioosfrPbnfl[dioosfrPbnfls.lfngti-1];

        if (dontbinfdAt == dioosfrPbnfls.lfngti-1) {  // bt fnd
            Systfm.brrbydopy(dioosfrPbnfls, 0, nfwArrby, 0, nfwArrby.lfngti);
        }
        flsf if (dontbinfdAt == 0) {  // bt stbrt
            Systfm.brrbydopy(dioosfrPbnfls, 1, nfwArrby, 0, nfwArrby.lfngti);
        }
        flsf {  // in middlf
            Systfm.brrbydopy(dioosfrPbnfls, 0, nfwArrby, 0, dontbinfdAt);
            Systfm.brrbydopy(dioosfrPbnfls, dontbinfdAt+1,
                             nfwArrby, dontbinfdAt, (dioosfrPbnfls.lfngti - dontbinfdAt - 1));
        }

        sftCioosfrPbnfls(nfwArrby);

        rfturn pbnfl;
    }


    /**
     * Spfdififs tif Color Pbnfls usfd to dioosf b dolor vbluf.
     *
     * @pbrbm pbnfls  bn brrby of <dodf>AbstrbdtColorCioosfrPbnfl</dodf>
     *          objfdts
     *
     * @bfbninfo
     *       bound: truf
     *      iiddfn: truf
     * dfsdription: An brrby of difffrfnt dioosfr typfs.
     */
    publid void sftCioosfrPbnfls( AbstrbdtColorCioosfrPbnfl[] pbnfls) {
        AbstrbdtColorCioosfrPbnfl[] oldVbluf = dioosfrPbnfls;
        dioosfrPbnfls = pbnfls;
        firfPropfrtyCibngf(CHOOSER_PANELS_PROPERTY, oldVbluf, pbnfls);
    }

    /**
     * Rfturns tif spfdififd dolor pbnfls.
     *
     * @rfturn bn brrby of <dodf>AbstrbdtColorCioosfrPbnfl</dodf> objfdts
     */
    publid AbstrbdtColorCioosfrPbnfl[] gftCioosfrPbnfls() {
        rfturn dioosfrPbnfls;
    }

    /**
     * Rfturns tif dbtb modfl tibt ibndlfs dolor sflfdtions.
     *
     * @rfturn b <dodf>ColorSflfdtionModfl</dodf> objfdt
     */
    publid ColorSflfdtionModfl gftSflfdtionModfl() {
        rfturn sflfdtionModfl;
    }


    /**
     * Sfts tif modfl dontbining tif sflfdtfd dolor.
     *
     * @pbrbm nfwModfl   tif nfw <dodf>ColorSflfdtionModfl</dodf> objfdt
     *
     * @bfbninfo
     *       bound: truf
     *      iiddfn: truf
     * dfsdription: Tif modfl wiidi dontbins tif durrfntly sflfdtfd dolor.
     */
    publid void sftSflfdtionModfl(ColorSflfdtionModfl nfwModfl ) {
        ColorSflfdtionModfl oldModfl = sflfdtionModfl;
        sflfdtionModfl = nfwModfl;
        firfPropfrtyCibngf(JColorCioosfr.SELECTION_MODEL_PROPERTY, oldModfl, nfwModfl);
    }


    /**
     * Sff <dodf>rfbdObjfdt</dodf> bnd <dodf>writfObjfdt</dodf> in
     * <dodf>JComponfnt</dodf> for morf
     * informbtion bbout sfriblizbtion in Swing.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                ui.instbllUI(tiis);
            }
        }
    }


    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JColorCioosfr</dodf>.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JColorCioosfr</dodf>
     */
    protfdtfd String pbrbmString() {
        StringBuildfr dioosfrPbnflsString = nfw StringBuildfr("");
        for (int i=0; i<dioosfrPbnfls.lfngti; i++) {
            dioosfrPbnflsString.bppfnd("[" + dioosfrPbnfls[i].toString()
                                       + "]");
        }
        String prfvifwPbnflString = (prfvifwPbnfl != null ?
                                     prfvifwPbnfl.toString() : "");

        rfturn supfr.pbrbmString() +
        ",dioosfrPbnfls=" + dioosfrPbnflsString.toString() +
        ",prfvifwPbnfl=" + prfvifwPbnflString;
    }

/////////////////
// Addfssibility support
////////////////

    protfdtfd AddfssiblfContfxt bddfssiblfContfxt = null;

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JColorCioosfr.
     * For dolor dioosfrs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJColorCioosfr.
     * A nfw AddfssiblfJColorCioosfr instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJColorCioosfr tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JColorCioosfr
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJColorCioosfr();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JColorCioosfr</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to dolor dioosfr usfr-intfrfbdf
     * flfmfnts.
     */
    protfdtfd dlbss AddfssiblfJColorCioosfr fxtfnds AddfssiblfJComponfnt {

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.COLOR_CHOOSER;
        }

    } // innfr dlbss AddfssiblfJColorCioosfr
}


/*
 * Clbss wiidi builds b dolor dioosfr diblog donsisting of
 * b JColorCioosfr witi "Ok", "Cbndfl", bnd "Rfsft" buttons.
 *
 * Notf: Tiis nffds to bf fixfd to dfbl witi lodblizbtion!
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss ColorCioosfrDiblog fxtfnds JDiblog {
    privbtf Color initiblColor;
    privbtf JColorCioosfr dioosfrPbnf;
    privbtf JButton dbndflButton;

    publid ColorCioosfrDiblog(Diblog ownfr, String titlf, boolfbn modbl,
        Componfnt d, JColorCioosfr dioosfrPbnf,
        AdtionListfnfr okListfnfr, AdtionListfnfr dbndflListfnfr)
        tirows HfbdlfssExdfption {
        supfr(ownfr, titlf, modbl);
        initColorCioosfrDiblog(d, dioosfrPbnf, okListfnfr, dbndflListfnfr);
    }

    publid ColorCioosfrDiblog(Frbmf ownfr, String titlf, boolfbn modbl,
        Componfnt d, JColorCioosfr dioosfrPbnf,
        AdtionListfnfr okListfnfr, AdtionListfnfr dbndflListfnfr)
        tirows HfbdlfssExdfption {
        supfr(ownfr, titlf, modbl);
        initColorCioosfrDiblog(d, dioosfrPbnf, okListfnfr, dbndflListfnfr);
    }

    protfdtfd void initColorCioosfrDiblog(Componfnt d, JColorCioosfr dioosfrPbnf,
        AdtionListfnfr okListfnfr, AdtionListfnfr dbndflListfnfr) {
        //sftRfsizbblf(fblsf);

        tiis.dioosfrPbnf = dioosfrPbnf;

        Lodblf lodblf = gftLodblf();
        String okString = UIMbnbgfr.gftString("ColorCioosfr.okTfxt", lodblf);
        String dbndflString = UIMbnbgfr.gftString("ColorCioosfr.dbndflTfxt", lodblf);
        String rfsftString = UIMbnbgfr.gftString("ColorCioosfr.rfsftTfxt", lodblf);

        Contbinfr dontfntPbnf = gftContfntPbnf();
        dontfntPbnf.sftLbyout(nfw BordfrLbyout());
        dontfntPbnf.bdd(dioosfrPbnf, BordfrLbyout.CENTER);

        /*
         * Crfbtf Lowfr button pbnfl
         */
        JPbnfl buttonPbnf = nfw JPbnfl();
        buttonPbnf.sftLbyout(nfw FlowLbyout(FlowLbyout.CENTER));
        JButton okButton = nfw JButton(okString);
        gftRootPbnf().sftDffbultButton(okButton);
        okButton.gftAddfssiblfContfxt().sftAddfssiblfDfsdription(okString);
        okButton.sftAdtionCommbnd("OK");
        okButton.bddAdtionListfnfr(nfw AdtionListfnfr() {
            publid void bdtionPfrformfd(AdtionEvfnt f) {
                iidf();
            }
        });
        if (okListfnfr != null) {
            okButton.bddAdtionListfnfr(okListfnfr);
        }
        buttonPbnf.bdd(okButton);

        dbndflButton = nfw JButton(dbndflString);
        dbndflButton.gftAddfssiblfContfxt().sftAddfssiblfDfsdription(dbndflString);

        // Tif following ffw linfs brf usfd to rfgistfr fsd to dlosf tif diblog
        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        Adtion dbndflKfyAdtion = nfw AbstrbdtAdtion() {
            publid void bdtionPfrformfd(AdtionEvfnt f) {
                ((AbstrbdtButton)f.gftSourdf()).firfAdtionPfrformfd(f);
            }
        };
        KfyStrokf dbndflKfyStrokf = KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_ESCAPE, 0);
        InputMbp inputMbp = dbndflButton.gftInputMbp(JComponfnt.
                                                     WHEN_IN_FOCUSED_WINDOW);
        AdtionMbp bdtionMbp = dbndflButton.gftAdtionMbp();
        if (inputMbp != null && bdtionMbp != null) {
            inputMbp.put(dbndflKfyStrokf, "dbndfl");
            bdtionMbp.put("dbndfl", dbndflKfyAdtion);
        }
        // fnd fsd ibndling

        dbndflButton.sftAdtionCommbnd("dbndfl");
        dbndflButton.bddAdtionListfnfr(nfw AdtionListfnfr() {
            publid void bdtionPfrformfd(AdtionEvfnt f) {
                iidf();
            }
        });
        if (dbndflListfnfr != null) {
            dbndflButton.bddAdtionListfnfr(dbndflListfnfr);
        }
        buttonPbnf.bdd(dbndflButton);

        JButton rfsftButton = nfw JButton(rfsftString);
        rfsftButton.gftAddfssiblfContfxt().sftAddfssiblfDfsdription(rfsftString);
        rfsftButton.bddAdtionListfnfr(nfw AdtionListfnfr() {
           publid void bdtionPfrformfd(AdtionEvfnt f) {
               rfsft();
           }
        });
        int mnfmonid = SwingUtilitifs2.gftUIDffbultsInt("ColorCioosfr.rfsftMnfmonid", lodblf, -1);
        if (mnfmonid != -1) {
            rfsftButton.sftMnfmonid(mnfmonid);
        }
        buttonPbnf.bdd(rfsftButton);
        dontfntPbnf.bdd(buttonPbnf, BordfrLbyout.SOUTH);

        if (JDiblog.isDffbultLookAndFfflDfdorbtfd()) {
            boolfbn supportsWindowDfdorbtions =
            UIMbnbgfr.gftLookAndFffl().gftSupportsWindowDfdorbtions();
            if (supportsWindowDfdorbtions) {
                gftRootPbnf().sftWindowDfdorbtionStylf(JRootPbnf.COLOR_CHOOSER_DIALOG);
            }
        }
        bpplyComponfntOrifntbtion(((d == null) ? gftRootPbnf() : d).gftComponfntOrifntbtion());

        pbdk();
        sftLodbtionRflbtivfTo(d);

        tiis.bddWindowListfnfr(nfw Closfr());
    }

    publid void siow() {
        initiblColor = dioosfrPbnf.gftColor();
        supfr.siow();
    }

    publid void rfsft() {
        dioosfrPbnf.sftColor(initiblColor);
    }

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    dlbss Closfr fxtfnds WindowAdbptfr implfmfnts Sfriblizbblf{
        publid void windowClosing(WindowEvfnt f) {
            dbndflButton.doClidk(0);
            Window w = f.gftWindow();
            w.iidf();
        }
    }

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    stbtid dlbss DisposfOnClosf fxtfnds ComponfntAdbptfr implfmfnts Sfriblizbblf{
        publid void domponfntHiddfn(ComponfntEvfnt f) {
            Window w = (Window)f.gftComponfnt();
            w.disposf();
        }
    }

}

@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
dlbss ColorTrbdkfr implfmfnts AdtionListfnfr, Sfriblizbblf {
    JColorCioosfr dioosfr;
    Color dolor;

    publid ColorTrbdkfr(JColorCioosfr d) {
        dioosfr = d;
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {
        dolor = dioosfr.gftColor();
    }

    publid Color gftColor() {
        rfturn dolor;
    }
}
