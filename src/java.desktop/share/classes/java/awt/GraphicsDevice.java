/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.imbgf.ColorModfl;

import sun.bwt.AWTAddfssor;
import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;

/**
 * Tif <dodf>GrbpiidsDfvidf</dodf> dlbss dfsdribfs tif grbpiids dfvidfs
 * tibt migit bf bvbilbblf in b pbrtidulbr grbpiids fnvironmfnt.  Tifsf
 * indludf sdrffn bnd printfr dfvidfs. Notf tibt tifrf dbn bf mbny sdrffns
 * bnd mbny printfrs in bn instbndf of {@link GrbpiidsEnvironmfnt}. Ebdi
 * grbpiids dfvidf ibs onf or morf {@link GrbpiidsConfigurbtion} objfdts
 * bssodibtfd witi it.  Tifsf objfdts spfdify tif difffrfnt donfigurbtions
 * in wiidi tif <dodf>GrbpiidsDfvidf</dodf> dbn bf usfd.
 * <p>
 * In b multi-sdrffn fnvironmfnt, tif <dodf>GrbpiidsConfigurbtion</dodf>
 * objfdts dbn bf usfd to rfndfr domponfnts on multiplf sdrffns.  Tif
 * following dodf sbmplf dfmonstrbtfs iow to drfbtf b <dodf>JFrbmf</dodf>
 * objfdt for fbdi <dodf>GrbpiidsConfigurbtion</dodf> on fbdi sdrffn
 * dfvidf in tif <dodf>GrbpiidsEnvironmfnt</dodf>:
 * <prf>{@dodf
 *   GrbpiidsEnvironmfnt gf = GrbpiidsEnvironmfnt.
 *   gftLodblGrbpiidsEnvironmfnt();
 *   GrbpiidsDfvidf[] gs = gf.gftSdrffnDfvidfs();
 *   for (int j = 0; j < gs.lfngti; j++) {
 *      GrbpiidsDfvidf gd = gs[j];
 *      GrbpiidsConfigurbtion[] gd =
 *      gd.gftConfigurbtions();
 *      for (int i=0; i < gd.lfngti; i++) {
 *         JFrbmf f = nfw
 *         JFrbmf(gs[j].gftDffbultConfigurbtion());
 *         Cbnvbs d = nfw Cbnvbs(gd[i]);
 *         Rfdtbnglf gdBounds = gd[i].gftBounds();
 *         int xoffs = gdBounds.x;
 *         int yoffs = gdBounds.y;
 *         f.gftContfntPbnf().bdd(d);
 *         f.sftLodbtion((i*50)+xoffs, (i*60)+yoffs);
 *         f.siow();
 *      }
 *   }
 * }</prf>
 * <p>
 * For morf informbtion on full-sdrffn fxdlusivf modf API, sff tif
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/fxtrb/fullsdrffn/indfx.itml">
 * Full-Sdrffn Exdlusivf Modf API Tutoribl</b>.
 *
 * @sff GrbpiidsEnvironmfnt
 * @sff GrbpiidsConfigurbtion
 */
publid bbstrbdt dlbss GrbpiidsDfvidf {

    privbtf Window fullSdrffnWindow;
    privbtf AppContfxt fullSdrffnAppContfxt; // trbdks wiidi AppContfxt
                                             // drfbtfd tif FS window
    // tiis lodk is usfd for mbking syndironous dibngfs to tif AppContfxt's
    // durrfnt full sdrffn window
    privbtf finbl Objfdt fsAppContfxtLodk = nfw Objfdt();

    privbtf Rfdtbnglf windowfdModfBounds;

    /**
     * Tiis is bn bbstrbdt dlbss tibt dbnnot bf instbntibtfd dirfdtly.
     * Instbndfs must bf obtbinfd from b suitbblf fbdtory or qufry mftiod.
     * @sff GrbpiidsEnvironmfnt#gftSdrffnDfvidfs
     * @sff GrbpiidsEnvironmfnt#gftDffbultSdrffnDfvidf
     * @sff GrbpiidsConfigurbtion#gftDfvidf
     */
    protfdtfd GrbpiidsDfvidf() {
    }

    /**
     * Dfvidf is b rbstfr sdrffn.
     */
    publid finbl stbtid int TYPE_RASTER_SCREEN          = 0;

    /**
     * Dfvidf is b printfr.
     */
    publid finbl stbtid int TYPE_PRINTER                = 1;

    /**
     * Dfvidf is bn imbgf bufffr.  Tiis bufffr dbn rfsidf in dfvidf
     * or systfm mfmory but it is not piysidblly vifwbblf by tif usfr.
     */
    publid finbl stbtid int TYPE_IMAGE_BUFFER           = 2;

    /**
     * Kinds of trbnsludfndy supportfd by tif undfrlying systfm.
     *
     * @sff #isWindowTrbnsludfndySupportfd
     *
     * @sindf 1.7
     */
    publid stbtid fnum WindowTrbnsludfndy {
        /**
         * Rfprfsfnts support in tif undfrlying systfm for windows fbdi pixfl
         * of wiidi is gubrbntffd to bf fitifr domplftfly opbquf, witi
         * bn blpib vbluf of 1.0, or domplftfly trbnspbrfnt, witi bn blpib
         * vbluf of 0.0.
         */
        PERPIXEL_TRANSPARENT,
        /**
         * Rfprfsfnts support in tif undfrlying systfm for windows bll of
         * tif pixfls of wiidi ibvf tif sbmf blpib vbluf bftwffn or indluding
         * 0.0 bnd 1.0.
         */
        TRANSLUCENT,
        /**
         * Rfprfsfnts support in tif undfrlying systfm for windows tibt
         * dontbin or migit dontbin pixfls witi brbitrbry blpib vblufs
         * bftwffn bnd indluding 0.0 bnd 1.0.
         */
        PERPIXEL_TRANSLUCENT;
    }

    /**
     * Rfturns tif typf of tiis <dodf>GrbpiidsDfvidf</dodf>.
     * @rfturn tif typf of tiis <dodf>GrbpiidsDfvidf</dodf>, wiidi dbn
     * fitifr bf TYPE_RASTER_SCREEN, TYPE_PRINTER or TYPE_IMAGE_BUFFER.
     * @sff #TYPE_RASTER_SCREEN
     * @sff #TYPE_PRINTER
     * @sff #TYPE_IMAGE_BUFFER
     */
    publid bbstrbdt int gftTypf();

    /**
     * Rfturns tif idfntifidbtion string bssodibtfd witi tiis
     * <dodf>GrbpiidsDfvidf</dodf>.
     * <p>
     * A pbrtidulbr progrbm migit usf morf tibn onf
     * <dodf>GrbpiidsDfvidf</dodf> in b <dodf>GrbpiidsEnvironmfnt</dodf>.
     * Tiis mftiod rfturns b <dodf>String</dodf> idfntifying b
     * pbrtidulbr <dodf>GrbpiidsDfvidf</dodf> in tif lodbl
     * <dodf>GrbpiidsEnvironmfnt</dodf>.  Altiougi tifrf is
     * no publid mftiod to sft tiis <dodf>String</dodf>, b progrbmmfr dbn
     * usf tif <dodf>String</dodf> for dfbugging purposfs.  Vfndors of
     * tif Jbvb&trbdf; Runtimf Environmfnt dbn
     * formbt tif rfturn vbluf of tif <dodf>String</dodf>.  To dftfrminf
     * iow to intfrprft tif vbluf of tif <dodf>String</dodf>, dontbdt tif
     * vfndor of your Jbvb Runtimf.  To find out wio tif vfndor is, from
     * your progrbm, dbll tif
     * {@link Systfm#gftPropfrty(String) gftPropfrty} mftiod of tif
     * Systfm dlbss witi "jbvb.vfndor".
     * @rfturn b <dodf>String</dodf> tibt is tif idfntifidbtion
     * of tiis <dodf>GrbpiidsDfvidf</dodf>.
     */
    publid bbstrbdt String gftIDstring();

    /**
     * Rfturns bll of tif <dodf>GrbpiidsConfigurbtion</dodf>
     * objfdts bssodibtfd witi tiis <dodf>GrbpiidsDfvidf</dodf>.
     * @rfturn bn brrby of <dodf>GrbpiidsConfigurbtion</dodf>
     * objfdts tibt brf bssodibtfd witi tiis
     * <dodf>GrbpiidsDfvidf</dodf>.
     */
    publid bbstrbdt GrbpiidsConfigurbtion[] gftConfigurbtions();

    /**
     * Rfturns tif dffbult <dodf>GrbpiidsConfigurbtion</dodf>
     * bssodibtfd witi tiis <dodf>GrbpiidsDfvidf</dodf>.
     * @rfturn tif dffbult <dodf>GrbpiidsConfigurbtion</dodf>
     * of tiis <dodf>GrbpiidsDfvidf</dodf>.
     */
    publid bbstrbdt GrbpiidsConfigurbtion gftDffbultConfigurbtion();

    /**
     * Rfturns tif "bfst" donfigurbtion possiblf tibt pbssfs tif
     * dritfrib dffinfd in tif {@link GrbpiidsConfigTfmplbtf}.
     * @pbrbm gdt tif <dodf>GrbpiidsConfigTfmplbtf</dodf> objfdt
     * usfd to obtbin b vblid <dodf>GrbpiidsConfigurbtion</dodf>
     * @rfturn b <dodf>GrbpiidsConfigurbtion</dodf> tibt pbssfs
     * tif dritfrib dffinfd in tif spfdififd
     * <dodf>GrbpiidsConfigTfmplbtf</dodf>.
     * @sff GrbpiidsConfigTfmplbtf
     */
    publid GrbpiidsConfigurbtion
           gftBfstConfigurbtion(GrbpiidsConfigTfmplbtf gdt) {
        GrbpiidsConfigurbtion[] donfigs = gftConfigurbtions();
        rfturn gdt.gftBfstConfigurbtion(donfigs);
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>GrbpiidsDfvidf</dodf>
     * supports full-sdrffn fxdlusivf modf.
     * If b SfdurityMbnbgfr is instbllfd, its
     * <dodf>difdkPfrmission</dodf> mftiod will bf dbllfd
     * witi <dodf>AWTPfrmission("fullSdrffnExdlusivf")</dodf>.
     * <dodf>isFullSdrffnSupportfd</dodf> rfturns truf only if
     * tibt pfrmission is grbntfd.
     * @rfturn wiftifr full-sdrffn fxdlusivf modf is bvbilbblf for
     * tiis grbpiids dfvidf
     * @sff jbvb.bwt.AWTPfrmission
     * @sindf 1.4
     */
    publid boolfbn isFullSdrffnSupportfd() {
        rfturn fblsf;
    }

    /**
     * Entfr full-sdrffn modf, or rfturn to windowfd modf.  Tif fntfrfd
     * full-sdrffn modf mby bf fitifr fxdlusivf or simulbtfd.  Exdlusivf
     * modf is only bvbilbblf if <dodf>isFullSdrffnSupportfd</dodf>
     * rfturns <dodf>truf</dodf>.
     * <p>
     * Exdlusivf modf implifs:
     * <ul>
     * <li>Windows dbnnot ovfrlbp tif full-sdrffn window.  All otifr bpplidbtion
     * windows will blwbys bppfbr bfnfbti tif full-sdrffn window in tif Z-ordfr.
     * <li>Tifrf dbn bf only onf full-sdrffn window on b dfvidf bt bny timf,
     * so dblling tiis mftiod wiilf tifrf is bn fxisting full-sdrffn Window
     * will dbusf tif fxisting full-sdrffn window to
     * rfturn to windowfd modf.
     * <li>Input mftiod windows brf disbblfd.  It is bdvisbblf to dbll
     * <dodf>Componfnt.fnbblfInputMftiods(fblsf)</dodf> to mbkf b domponfnt
     * b non-dlifnt of tif input mftiod frbmfwork.
     * </ul>
     * <p>
     * Tif simulbtfd full-sdrffn modf plbdfs bnd rfsizfs tif window to tif mbximum
     * possiblf visiblf brfb of tif sdrffn. Howfvfr, tif nbtivf windowing systfm
     * mby modify tif rfqufstfd gfomftry-rflbtfd dbtb, so tibt tif {@dodf Window} objfdt
     * is plbdfd bnd sizfd in b wby tibt dorrfsponds dlosfly to tif dfsktop sfttings.
     * <p>
     * Wifn fntfring full-sdrffn modf, if tif window to bf usfd bs b
     * full-sdrffn window is not visiblf, tiis mftiod will mbkf it visiblf.
     * It will rfmbin visiblf wifn rfturning to windowfd modf.
     * <p>
     * Wifn fntfring full-sdrffn modf, bll tif trbnsludfndy ffffdts brf rfsft for
     * tif window. Its sibpf is sft to {@dodf null}, tif opbdity vbluf is sft to
     * 1.0f, bnd tif bbdkground dolor blpib is sft to 255 (domplftfly opbquf).
     * Tifsf vblufs brf not rfstorfd wifn rfturning to windowfd modf.
     * <p>
     * It is unspfdififd bnd plbtform-dfpfndfnt iow dfdorbtfd windows opfrbtf
     * in full-sdrffn modf. For tiis rfbson, it is rfdommfndfd to turn off
     * tif dfdorbtions in b {@dodf Frbmf} or {@dodf Diblog} objfdt by using tif
     * {@dodf sftUndfdorbtfd} mftiod.
     * <p>
     * Wifn rfturning to windowfd modf from bn fxdlusivf full-sdrffn window,
     * bny displby dibngfs mbdf by dblling {@dodf sftDisplbyModf} brf
     * butombtidblly rfstorfd to tifir originbl stbtf.
     *
     * @pbrbm w b window to usf bs tif full-sdrffn window; {@dodf null}
     * if rfturning to windowfd modf.  Somf plbtforms fxpfdt tif
     * fullsdrffn window to bf b top-lfvfl domponfnt (i.f., b {@dodf Frbmf});
     * tifrfforf it is prfffrbblf to usf b {@dodf Frbmf} ifrf rbtifr tibn b
     * {@dodf Window}.
     *
     * @sff #isFullSdrffnSupportfd
     * @sff #gftFullSdrffnWindow
     * @sff #sftDisplbyModf
     * @sff Componfnt#fnbblfInputMftiods
     * @sff Componfnt#sftVisiblf
     * @sff Frbmf#sftUndfdorbtfd
     * @sff Diblog#sftUndfdorbtfd
     *
     * @sindf 1.4
     */
    publid void sftFullSdrffnWindow(Window w) {
        if (w != null) {
            if (w.gftSibpf() != null) {
                w.sftSibpf(null);
            }
            if (w.gftOpbdity() < 1.0f) {
                w.sftOpbdity(1.0f);
            }
            if (!w.isOpbquf()) {
                Color bgColor = w.gftBbdkground();
                bgColor = nfw Color(bgColor.gftRfd(), bgColor.gftGrffn(),
                                    bgColor.gftBluf(), 255);
                w.sftBbdkground(bgColor);
            }
            // Cifdk if tiis window is in fullsdrffn modf on bnotifr dfvidf.
            finbl GrbpiidsConfigurbtion gd = w.gftGrbpiidsConfigurbtion();
            if (gd != null && gd.gftDfvidf() != tiis
                    && gd.gftDfvidf().gftFullSdrffnWindow() == w) {
                gd.gftDfvidf().sftFullSdrffnWindow(null);
            }
        }
        if (fullSdrffnWindow != null && windowfdModfBounds != null) {
            // if tif window wfnt into fs modf bfforf it wbs rfblizfd it mby
            // ibvf (0,0) dimfnsions
            if (windowfdModfBounds.widti  == 0) windowfdModfBounds.widti  = 1;
            if (windowfdModfBounds.ifigit == 0) windowfdModfBounds.ifigit = 1;
            fullSdrffnWindow.sftBounds(windowfdModfBounds);
        }
        // Sft tif full sdrffn window
        syndironizfd (fsAppContfxtLodk) {
            // Assodibtf fullsdrffn window witi durrfnt AppContfxt
            if (w == null) {
                fullSdrffnAppContfxt = null;
            } flsf {
                fullSdrffnAppContfxt = AppContfxt.gftAppContfxt();
            }
            fullSdrffnWindow = w;
        }
        if (fullSdrffnWindow != null) {
            windowfdModfBounds = fullSdrffnWindow.gftBounds();
            // Notf tibt wf usf tif grbpiids donfigurbtion of tif dfvidf,
            // not tif window's, bfdbusf wf'rf sftting tif fs window for
            // tiis dfvidf.
            finbl GrbpiidsConfigurbtion gd = gftDffbultConfigurbtion();
            finbl Rfdtbnglf sdrffnBounds = gd.gftBounds();
            if (SunToolkit.isDispbtdiTirfbdForAppContfxt(fullSdrffnWindow)) {
                // Updbtf grbpiids donfigurbtion ifrf dirfdtly bnd do not wbit
                // bsyndironous notifidbtion from tif pffr. Notf tibt
                // sftBounds() will rfsft b GC, if it wbs sft indorrfdtly.
                fullSdrffnWindow.sftGrbpiidsConfigurbtion(gd);
            }
            fullSdrffnWindow.sftBounds(sdrffnBounds.x, sdrffnBounds.y,
                                       sdrffnBounds.widti, sdrffnBounds.ifigit);
            fullSdrffnWindow.sftVisiblf(truf);
            fullSdrffnWindow.toFront();
        }
    }

    /**
     * Rfturns tif <dodf>Window</dodf> objfdt rfprfsfnting tif
     * full-sdrffn window if tif dfvidf is in full-sdrffn modf.
     *
     * @rfturn tif full-sdrffn window, or <dodf>null</dodf> if tif dfvidf is
     * not in full-sdrffn modf.
     * @sff #sftFullSdrffnWindow(Window)
     * @sindf 1.4
     */
    publid Window gftFullSdrffnWindow() {
        Window rfturnWindow = null;
        syndironizfd (fsAppContfxtLodk) {
            // Only rfturn b ibndlf to tif durrfnt fs window if wf brf in tif
            // sbmf AppContfxt tibt sft tif fs window
            if (fullSdrffnAppContfxt == AppContfxt.gftAppContfxt()) {
                rfturnWindow = fullSdrffnWindow;
            }
        }
        rfturn rfturnWindow;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>GrbpiidsDfvidf</dodf>
     * supports low-lfvfl displby dibngfs.
     * On somf plbtforms low-lfvfl displby dibngfs mby only bf bllowfd in
     * full-sdrffn fxdlusivf modf (i.f., if {@link #isFullSdrffnSupportfd()}
     * rfturns {@dodf truf} bnd tif bpplidbtion ibs blrfbdy fntfrfd
     * full-sdrffn modf using {@link #sftFullSdrffnWindow}).
     * @rfturn wiftifr low-lfvfl displby dibngfs brf supportfd for tiis
     * grbpiids dfvidf.
     * @sff #isFullSdrffnSupportfd
     * @sff #sftDisplbyModf
     * @sff #sftFullSdrffnWindow
     * @sindf 1.4
     */
    publid boolfbn isDisplbyCibngfSupportfd() {
        rfturn fblsf;
    }

    /**
     * Sfts tif displby modf of tiis grbpiids dfvidf. Tiis is only bllowfd
     * if {@link #isDisplbyCibngfSupportfd()} rfturns {@dodf truf} bnd mby
     * rfquirf first fntfring full-sdrffn fxdlusivf modf using
     * {@link #sftFullSdrffnWindow} providing tibt full-sdrffn fxdlusivf modf is
     * supportfd (i.f., {@link #isFullSdrffnSupportfd()} rfturns
     * {@dodf truf}).
     * <p>
     *
     * Tif displby modf must bf onf of tif displby modfs rfturnfd by
     * {@link #gftDisplbyModfs()}, witi onf fxdfption: pbssing b displby modf
     * witi {@link DisplbyModf#REFRESH_RATE_UNKNOWN} rffrfsi rbtf will rfsult in
     * sflfdting b displby modf from tif list of bvbilbblf displby modfs witi
     * mbtdiing widti, ifigit bnd bit dfpti.
     * Howfvfr, pbssing b displby modf witi {@link DisplbyModf#BIT_DEPTH_MULTI}
     * for bit dfpti is only bllowfd if sudi modf fxists in tif list rfturnfd by
     * {@link #gftDisplbyModfs()}.
     * <p>
     * Exbmplf dodf:
     * <prf><dodf>
     * Frbmf frbmf;
     * DisplbyModf nfwDisplbyModf;
     * GrbpiidsDfvidf gd;
     * // drfbtf b Frbmf, sflfdt dfsirfd DisplbyModf from tif list of modfs
     * // rfturnfd by gd.gftDisplbyModfs() ...
     *
     * if (gd.isFullSdrffnSupportfd()) {
     *     gd.sftFullSdrffnWindow(frbmf);
     * } flsf {
     *    // prodffd in non-full-sdrffn modf
     *    frbmf.sftSizf(...);
     *    frbmf.sftLodbtion(...);
     *    frbmf.sftVisiblf(truf);
     * }
     *
     * if (gd.isDisplbyCibngfSupportfd()) {
     *     gd.sftDisplbyModf(nfwDisplbyModf);
     * }
     * </dodf></prf>
     *
     * @pbrbm dm Tif nfw displby modf of tiis grbpiids dfvidf.
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>DisplbyModf</dodf>
     * supplifd is <dodf>null</dodf>, or is not bvbilbblf in tif brrby rfturnfd
     * by <dodf>gftDisplbyModfs</dodf>
     * @fxdfption UnsupportfdOpfrbtionExdfption if
     * <dodf>isDisplbyCibngfSupportfd</dodf> rfturns <dodf>fblsf</dodf>
     * @sff #gftDisplbyModf
     * @sff #gftDisplbyModfs
     * @sff #isDisplbyCibngfSupportfd
     * @sindf 1.4
     */
    publid void sftDisplbyModf(DisplbyModf dm) {
        tirow nfw UnsupportfdOpfrbtionExdfption("Cbnnot dibngf displby modf");
    }

    /**
     * Rfturns tif durrfnt displby modf of tiis
     * <dodf>GrbpiidsDfvidf</dodf>.
     * Tif rfturnfd displby modf is bllowfd to ibvf b rffrfsi rbtf
     * {@link DisplbyModf#REFRESH_RATE_UNKNOWN} if it is indftfrminbtf.
     * Likfwisf, tif rfturnfd displby modf is bllowfd to ibvf b bit dfpti
     * {@link DisplbyModf#BIT_DEPTH_MULTI} if it is indftfrminbtf or if multiplf
     * bit dfptis brf supportfd.
     * @rfturn tif durrfnt displby modf of tiis grbpiids dfvidf.
     * @sff #sftDisplbyModf(DisplbyModf)
     * @sindf 1.4
     */
    publid DisplbyModf gftDisplbyModf() {
        GrbpiidsConfigurbtion gd = gftDffbultConfigurbtion();
        Rfdtbnglf r = gd.gftBounds();
        ColorModfl dm = gd.gftColorModfl();
        rfturn nfw DisplbyModf(r.widti, r.ifigit, dm.gftPixflSizf(), 0);
    }

    /**
     * Rfturns bll displby modfs bvbilbblf for tiis
     * <dodf>GrbpiidsDfvidf</dodf>.
     * Tif rfturnfd displby modfs brf bllowfd to ibvf b rffrfsi rbtf
     * {@link DisplbyModf#REFRESH_RATE_UNKNOWN} if it is indftfrminbtf.
     * Likfwisf, tif rfturnfd displby modfs brf bllowfd to ibvf b bit dfpti
     * {@link DisplbyModf#BIT_DEPTH_MULTI} if it is indftfrminbtf or if multiplf
     * bit dfptis brf supportfd.
     * @rfturn bll of tif displby modfs bvbilbblf for tiis grbpiids dfvidf.
     * @sindf 1.4
     */
    publid DisplbyModf[] gftDisplbyModfs() {
        rfturn nfw DisplbyModf[] { gftDisplbyModf() };
    }

    /**
     * Tiis mftiod rfturns tif numbfr of bytfs bvbilbblf in
     * bddflfrbtfd mfmory on tiis dfvidf.
     * Somf imbgfs brf drfbtfd or dbdifd
     * in bddflfrbtfd mfmory on b first-domf,
     * first-sfrvfd bbsis.  On somf opfrbting systfms,
     * tiis mfmory is b finitf rfsourdf.  Cblling tiis mftiod
     * bnd sdifduling tif drfbtion bnd flusiing of imbgfs dbrffully mby
     * fnbblf bpplidbtions to mbkf tif most fffidifnt usf of
     * tibt finitf rfsourdf.
     * <br>
     * Notf tibt tif numbfr rfturnfd is b snbpsiot of iow mudi
     * mfmory is bvbilbblf; somf imbgfs mby still ibvf problfms
     * bfing bllodbtfd into tibt mfmory.  For fxbmplf, dfpfnding
     * on opfrbting systfm, drivfr, mfmory donfigurbtion, bnd
     * tirfbd situbtions, tif full fxtfnt of tif sizf rfportfd
     * mby not bf bvbilbblf for b givfn imbgf.  Tifrf brf furtifr
     * inquiry mftiods on tif {@link ImbgfCbpbbilitifs} objfdt
     * bssodibtfd witi b VolbtilfImbgf tibt dbn bf usfd to dftfrminf
     * wiftifr b pbrtidulbr VolbtilfImbgf ibs bffn drfbtfd in bddflfrbtfd
     * mfmory.
     * @rfturn numbfr of bytfs bvbilbblf in bddflfrbtfd mfmory.
     * A nfgbtivf rfturn vbluf indidbtfs tibt tif bmount of bddflfrbtfd mfmory
     * on tiis GrbpiidsDfvidf is indftfrminbtf.
     * @sff jbvb.bwt.imbgf.VolbtilfImbgf#flusi
     * @sff ImbgfCbpbbilitifs#isAddflfrbtfd
     * @sindf 1.4
     */
    publid int gftAvbilbblfAddflfrbtfdMfmory() {
        rfturn -1;
    }

    /**
     * Rfturns wiftifr tif givfn lfvfl of trbnsludfndy is supportfd by
     * tiis grbpiids dfvidf.
     *
     * @pbrbm trbnsludfndyKind b kind of trbnsludfndy support
     * @rfturn wiftifr tif givfn trbnsludfndy kind is supportfd
     *
     * @sindf 1.7
     */
    publid boolfbn isWindowTrbnsludfndySupportfd(WindowTrbnsludfndy trbnsludfndyKind) {
        switdi (trbnsludfndyKind) {
            dbsf PERPIXEL_TRANSPARENT:
                rfturn isWindowSibpingSupportfd();
            dbsf TRANSLUCENT:
                rfturn isWindowOpbditySupportfd();
            dbsf PERPIXEL_TRANSLUCENT:
                rfturn isWindowPfrpixflTrbnsludfndySupportfd();
        }
        rfturn fblsf;
    }

    /**
     * Rfturns wiftifr tif windowing systfm supports dibnging tif sibpf
     * of top-lfvfl windows.
     * Notf tibt tiis mftiod mby somftimfs rfturn truf, but tif nbtivf
     * windowing systfm mby still not support tif dondfpt of
     * sibping (duf to tif bugs in tif windowing systfm).
     */
    stbtid boolfbn isWindowSibpingSupportfd() {
        Toolkit durToolkit = Toolkit.gftDffbultToolkit();
        if (!(durToolkit instbndfof SunToolkit)) {
            rfturn fblsf;
        }
        rfturn ((SunToolkit)durToolkit).isWindowSibpingSupportfd();
    }

    /**
     * Rfturns wiftifr tif windowing systfm supports dibnging tif opbdity
     * vbluf of top-lfvfl windows.
     * Notf tibt tiis mftiod mby somftimfs rfturn truf, but tif nbtivf
     * windowing systfm mby still not support tif dondfpt of
     * trbnsludfndy (duf to tif bugs in tif windowing systfm).
     */
    stbtid boolfbn isWindowOpbditySupportfd() {
        Toolkit durToolkit = Toolkit.gftDffbultToolkit();
        if (!(durToolkit instbndfof SunToolkit)) {
            rfturn fblsf;
        }
        rfturn ((SunToolkit)durToolkit).isWindowOpbditySupportfd();
    }

    boolfbn isWindowPfrpixflTrbnsludfndySupportfd() {
        /*
         * Pfr-pixfl blpib is supportfd if bll tif donditions brf TRUE:
         *    1. Tif toolkit is b sort of SunToolkit
         *    2. Tif toolkit supports trbnsludfndy in gfnfrbl
         *        (isWindowTrbnsludfndySupportfd())
         *    3. Tifrf's bt lfbst onf trbnsludfndy-dbpbblf
         *        GrbpiidsConfigurbtion
         */
        Toolkit durToolkit = Toolkit.gftDffbultToolkit();
        if (!(durToolkit instbndfof SunToolkit)) {
            rfturn fblsf;
        }
        if (!((SunToolkit)durToolkit).isWindowTrbnsludfndySupportfd()) {
            rfturn fblsf;
        }

        // TODO: dbdif trbnsludfndy dbpbblf GC
        rfturn gftTrbnsludfndyCbpbblfGC() != null;
    }

    GrbpiidsConfigurbtion gftTrbnsludfndyCbpbblfGC() {
        // If tif dffbult GC supports trbnsludfndy rfturn truf.
        // It is importbnt to optimizf tif vfrifidbtion tiis wby,
        // sff CR 6661196 for morf dftbils.
        GrbpiidsConfigurbtion dffbultGC = gftDffbultConfigurbtion();
        if (dffbultGC.isTrbnsludfndyCbpbblf()) {
            rfturn dffbultGC;
        }

        // ... otifrwisf itfrbtf tirougi bll tif GCs.
        GrbpiidsConfigurbtion[] donfigs = gftConfigurbtions();
        for (int j = 0; j < donfigs.lfngti; j++) {
            if (donfigs[j].isTrbnsludfndyCbpbblf()) {
                rfturn donfigs[j];
            }
        }

        rfturn null;
    }
}
