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
import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.bfbns.Trbnsifnt;
import jbvbx.swing.plbf.VifwportUI;

import jbvbx.swing.fvfnt.*;
import jbvbx.swing.bordfr.*;
import jbvbx.bddfssibility.*;

import jbvb.io.Sfriblizbblf;

/**
 * Tif "vifwport" or "portiolf" tirougi wiidi you sff tif undfrlying
 * informbtion. Wifn you sdroll, wibt movfs is tif vifwport. It is likf
 * pffring tirougi b dbmfrb's vifwfindfr. Moving tif vifwfindfr upwbrds
 * brings nfw tiings into vifw bt tif top of tif pidturf bnd losfs
 * tiings tibt wfrf bt tif bottom.
 * <p>
 * By dffbult, <dodf>JVifwport</dodf> is opbquf. To dibngf tiis, usf tif
 * <dodf>sftOpbquf</dodf> mftiod.
 * <p>
 * <b>NOTE:</b>Wf ibvf implfmfntfd b fbstfr sdrolling blgoritim tibt
 * dofs not rfquirf b bufffr to drbw in. Tif blgoritim works bs follows:
 * <ol><li>Tif vifw bnd pbrfnt vifw bnd difdkfd to sff if tify brf
 * <dodf>JComponfnts</dodf>,
 * if tify brfn't, stop bnd rfpbint tif wiolf vifwport.
 * <li>If tif vifwport is obsdurfd by bn bndfstor, stop bnd rfpbint tif wiolf
 * vifwport.
 * <li>Computf tif rfgion tibt will bfdomf visiblf, if it is bs big bs
 * tif vifwport, stop bnd rfpbint tif wiolf vifw rfgion.
 * <li>Obtbin tif bndfstor <dodf>Window</dodf>'s grbpiids bnd
 * do b <dodf>dopyArfb</dodf> on tif sdrollfd rfgion.
 * <li>Mfssbgf tif vifw to rfpbint tif nfwly visiblf rfgion.
 * <li>Tif nfxt timf pbint is invokfd on tif vifwport, if tif dlip rfgion
 * is smbllfr tibn tif vifwport sizf b timfr is kidkfd off to rfpbint tif
 * wiolf rfgion.
 * </ol>
 * In gfnfrbl tiis bpprobdi is mudi fbstfr. Compbrfd to tif bbdking storf
 * bpprobdi tiis bvoids tif ovfrifbd of mbintbining bn offsdrffn bufffr bnd
 * ibving to do two <dodf>dopyArfb</dodf>s.
 * Compbrfd to tif non bbdking storf dbsf tiis
 * bpprobdi will grfbtly rfdudf tif pbintfd rfgion.
 * <p>
 * Tiis bpprobdi dbn dbusf slowfr timfs tibn tif bbdking storf bpprobdi
 * wifn tif vifwport is obsdurfd by bnotifr window, or pbrtiblly offsdrffn.
 * Wifn bnotifr window
 * obsdurfs tif vifwport tif dopyArfb will dopy gbrbbgf bnd b
 * pbint fvfnt will bf gfnfrbtfd by tif systfm to inform us wf nffd to
 * pbint tif nfwly fxposfd rfgion. Tif only wby to ibndlf tiis is to
 * rfpbint tif wiolf vifwport, wiidi dbn dbusf slowfr pfrformbndf tibn tif
 * bbdking storf dbsf. In most bpplidbtions vfry rbrfly will tif usfr bf
 * sdrolling wiilf tif vifwport is obsdurfd by bnotifr window or offsdrffn,
 * so tiis optimizbtion is usublly worti tif pfrformbndf iit wifn obsdurfd.
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
 * @butior Hbns Mullfr
 * @butior Piilip Milnf
 * @sff JSdrollPbnf
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JVifwport fxtfnds JComponfnt implfmfnts Addfssiblf
{
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "VifwportUI";

    /** Propfrty usfd to indidbtf window blitting siould not bf donf.
     */
    stbtid finbl Objfdt EnbblfWindowBlit = "EnbblfWindowBlit";

    /**
     * Truf wifn tif vifwport dimfnsions ibvf bffn dftfrminfd.
     * Tif dffbult is fblsf.
     */
    protfdtfd boolfbn isVifwSizfSft = fblsf;

    /**
     * Tif lbst <dodf>vifwPosition</dodf> tibt wf'vf pbintfd, so wf know iow
     * mudi of tif bbdking storf imbgf is vblid.
     */
    protfdtfd Point lbstPbintPosition = null;

    /**
     * Truf wifn tiis vifwport is mbintbining bn offsdrffn imbgf of its
     * dontfnts, so tibt somf sdrolling dbn tbkf plbdf using fbst "bit-blit"
     * opfrbtions instfbd of by bddfssing tif vifw objfdt to donstrudt tif
     * displby.  Tif dffbult is <dodf>fblsf</dodf>.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3
     * @sff #sftSdrollModf
     */
    @Dfprfdbtfd
    protfdtfd boolfbn bbdkingStorf = fblsf;

    /** Tif vifw imbgf usfd for b bbdking storf. */
    trbnsifnt protfdtfd Imbgf bbdkingStorfImbgf = null;

    /**
     * Tif <dodf>sdrollUndfrwby</dodf> flbg is usfd for domponfnts likf
     * <dodf>JList</dodf>.  Wifn tif downbrrow kfy is prfssfd on b
     * <dodf>JList</dodf> bnd tif sflfdtfd
     * dfll is tif lbst in tif list, tif <dodf>sdrollpbnf</dodf> butosdrolls.
     * Hfrf, tif old sflfdtfd dfll nffds rfpbinting bnd so wf nffd
     * b flbg to mbkf tif vifwport do tif optimizfd pbinting
     * only wifn tifrf is bn fxplidit dbll to
     * <dodf>sftVifwPosition(Point)</dodf>.
     * Wifn <dodf>sftBounds</dodf> is dbllfd tirougi otifr routfs,
     * tif flbg is off bnd tif vifw rfpbints normblly.  Anotifr bpprobdi
     * would bf to rfmovf tiis from tif <dodf>JVifwport</dodf>
     * dlbss bnd ibvf tif <dodf>JList</dodf> mbnbgf tiis dbsf by using
     * <dodf>sftBbdkingStorfEnbblfd</dodf>.  Tif dffbult is
     * <dodf>fblsf</dodf>.
     */
    protfdtfd boolfbn sdrollUndfrwby = fblsf;

    /*
     * Listfnfr tibt is notififd fbdi timf tif vifw dibngfs sizf.
     */
    privbtf ComponfntListfnfr vifwListfnfr = null;

    /* Only onf <dodf>CibngfEvfnt</dodf> is nffdfd pfr
     * <dodf>JVifwport</dodf> instbndf sindf tif
     * fvfnt's only (rfbd-only) stbtf is tif sourdf propfrty.  Tif sourdf
     * of fvfnts gfnfrbtfd ifrf is blwbys "tiis".
     */
    privbtf trbnsifnt CibngfEvfnt dibngfEvfnt = null;

    /**
      * Usf <dodf>grbpiids.dopyArfb</dodf> to implfmfnt sdrolling.
      * Tiis is tif fbstfst for most bpplidbtions.
      *
      * @sff #sftSdrollModf
      * @sindf 1.3
      */
    publid stbtid finbl int BLIT_SCROLL_MODE = 1;

    /**
      * Drbws vifwport dontfnts into bn offsdrffn imbgf.
      * Tiis wbs prfviously tif dffbult modf for <dodf>JTbblf</dodf>.
      * Tiis modf mby offfr bdvbntbgfs ovfr "blit modf"
      * in somf dbsfs, but it rfquirfs b lbrgf diunk of fxtrb RAM.
      *
      * @sff #sftSdrollModf
      * @sindf 1.3
      */
    publid stbtid finbl int BACKINGSTORE_SCROLL_MODE = 2;

    /**
      * Tiis modf usfs tif vfry simplf mftiod of rfdrbwing tif fntirf
      * dontfnts of tif sdrollpbnf fbdi timf it is sdrollfd.
      * Tiis wbs tif dffbult bfibvior in Swing 1.0 bnd Swing 1.1.
      * Eitifr of tif otifr two options will providf bfttfr pfrformbndf
      * in most dbsfs.
      *
      * @sff #sftSdrollModf
      * @sindf 1.3
      */
    publid stbtid finbl int SIMPLE_SCROLL_MODE = 0;

    /**
      * @sff #sftSdrollModf
      * @sindf 1.3
      */
    privbtf int sdrollModf = BLIT_SCROLL_MODE;

    //
    // Window blitting:
    //
    // As mfntionfd in tif jbvbdod wifn using windowBlit b pbint fvfnt
    // will bf gfnfrbtfd by tif systfm if dopyArfb dopifs b non-visiblf
    // portion of tif vifw (in otifr words, it dopifs gbrbbgf). Wf brf
    // not gubrbntffd to rfdfivf tif pbint fvfnt bfforf otifr mousf fvfnts,
    // so wf dbn not bf surf wf ibvfn't blrfbdy dopifd gbrbbgf b bundi of
    // timfs to difffrfnt pbrts of tif vifw. For tibt rfbson wifn b blit
    // ibppfns bnd tif Componfnt is obsdurfd (tif difdk for obsdurity
    // is not supportfd on bll plbtforms bnd is difdkfd vib ComponfntPffr
    // mftiods) tif ivbr rfpbintAll is sft to truf. Wifn pbint is rfdfivfd
    // if rfpbintAll is truf (wf prfviously did b blit) it is sft to
    // fblsf, bnd if tif dlip rfgion is smbllfr tibn tif vifwport
    // wbitingForRfpbint is sft to truf bnd b timfr is stbrtfd. Wifn
    // tif timfr firfs if wbitingForRfpbint is truf, rfpbint is invokfd.
    // In tif mfbn timf, if tif vifw is bskfd to sdroll bnd wbitingForRfpbint
    // is truf, b blit will not ibppfn, instfbd tif non-bbdking storf dbsf
    // of sdrolling will ibppfn, wiidi will rfsft wbitingForRfpbint.
    // wbitingForRfpbint is sft to fblsf in pbint wifn tif dlip rfdt is
    // biggfr (or fqubl) to tif sizf of tif vifwport.
    // A Timfr is usfd instfbd of just b rfpbint bs it bppfbrfd to offfr
    // bfttfr pfrformbndf.


    /**
     * Tiis is sft to truf in <dodf>sftVifwPosition</dodf>
     * if doing b window blit bnd tif vifwport is obsdurfd.
     */
    privbtf trbnsifnt boolfbn rfpbintAll;

    /**
     * Tiis is sft to truf in pbint, if <dodf>rfpbintAll</dodf>
     * is truf bnd tif dlip rfdtbnglf dofs not mbtdi tif bounds.
     * If truf, bnd sdrolling ibppfns tif
     * rfpbint mbnbgfr is not dlfbrfd wiidi tifn bllows for tif rfpbint
     * prfviously invokfd to suddffd.
     */
    privbtf trbnsifnt boolfbn wbitingForRfpbint;

    /**
     * Instfbd of dirfdtly invoking rfpbint, b <dodf>Timfr</dodf>
     * is stbrtfd bnd wifn it firfs, rfpbint is invokfd.
     */
    privbtf trbnsifnt Timfr rfpbintTimfr;

    /**
     * Sft to truf in pbintVifw wifn pbint is invokfd.
     */
    privbtf trbnsifnt boolfbn inBlitPbint;

    /**
     * Wiftifr or not b vblid vifw ibs bffn instbllfd.
     */
    privbtf boolfbn ibsHbdVblidVifw;

    /**
     * Wifn vifw is dibngfd wf ibvf to syndironizf sdrollbbr vblufs
     * witi vifwport (sff tif BbsidSdrollPbnfUI#syndSdrollPbnfWitiVifwport mftiod).
     * Tiis flbg bllows to invokf tibt mftiod wiilf SdrollPbnfLbyout#lbyoutContbinfr
     * is running.
     */
    privbtf boolfbn vifwCibngfd;

    /** Crfbtfs b <dodf>JVifwport</dodf>. */
    publid JVifwport() {
        supfr();
        sftLbyout(drfbtfLbyoutMbnbgfr());
        sftOpbquf(truf);
        updbtfUI();
        sftInifritsPopupMfnu(truf);
    }



    /**
     * Rfturns tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @rfturn b <dodf>VifwportUI</dodf> objfdt
     * @sindf 1.3
     */
    publid VifwportUI gftUI() {
        rfturn (VifwportUI)ui;
    }


    /**
     * Sfts tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @pbrbm ui  tif <dodf>VifwportUI</dodf> L&bmp;F objfdt
     * @sff UIDffbults#gftUI
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif UI objfdt tibt implfmfnts tif Componfnt's LookAndFffl.
     * @sindf 1.3
     */
    publid void sftUI(VifwportUI ui) {
        supfr.sftUI(ui);
    }


    /**
     * Rfsfts tif UI propfrty to b vbluf from tif durrfnt look bnd fffl.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((VifwportUI)UIMbnbgfr.gftUI(tiis));
    }


    /**
     * Rfturns b string tibt spfdififs tif nbmf of tif L&bmp;F dlbss
     * tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "VifwportUI"
     *
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /**
     * Sfts tif <dodf>JVifwport</dodf>'s onf ligitwfigit diild,
     * wiidi dbn bf <dodf>null</dodf>.
     * (Sindf tifrf is only onf diild wiidi oddupifs tif fntirf vifwport,
     * tif <dodf>donstrbints</dodf> bnd <dodf>indfx</dodf>
     * brgumfnts brf ignorfd.)
     *
     * @pbrbm diild       tif ligitwfigit <dodf>diild</dodf> of tif vifwport
     * @pbrbm donstrbints tif <dodf>donstrbints</dodf> to bf rfspfdtfd
     * @pbrbm indfx       tif indfx
     * @sff #sftVifw
     */
    protfdtfd void bddImpl(Componfnt diild, Objfdt donstrbints, int indfx) {
      sftVifw(diild);
    }


    /**
     * Rfmovfs tif <dodf>Vifwport</dodf>s onf ligitwfigit diild.
     *
     * @sff #sftVifw
     */
    publid void rfmovf(Componfnt diild) {
        diild.rfmovfComponfntListfnfr(vifwListfnfr);
        supfr.rfmovf(diild);
    }

    /**
     * Sdrolls tif vifw so tibt <dodf>Rfdtbnglf</dodf>
     * witiin tif vifw bfdomfs visiblf.
     * <p>
     * Tiis bttfmpts to vblidbtf tif vifw bfforf sdrolling if tif
     * vifw is durrfntly not vblid - <dodf>isVblid</dodf> rfturns fblsf.
     * To bvoid fxdfssivf vblidbtion wifn tif dontbinmfnt iifrbrdiy is
     * bfing drfbtfd tiis will not vblidbtf if onf of tif bndfstors dofs not
     * ibvf b pffr, or tifrf is no vblidbtf root bndfstor, or onf of tif
     * bndfstors is not b <dodf>Window</dodf> or <dodf>Applft</dodf>.
     * <p>
     * Notf tibt tiis mftiod will not sdroll outsidf of tif
     * vblid vifwport; for fxbmplf, if <dodf>dontfntRfdt</dodf> is lbrgfr
     * tibn tif vifwport, sdrolling will bf donfinfd to tif vifwport's
     * bounds.
     *
     * @pbrbm dontfntRfdt tif <dodf>Rfdtbnglf</dodf> to displby
     * @sff JComponfnt#isVblidbtfRoot
     * @sff jbvb.bwt.Componfnt#isVblid
     * @sff jbvb.bwt.Componfnt#gftPffr
     */
    publid void sdrollRfdtToVisiblf(Rfdtbnglf dontfntRfdt) {
        Componfnt vifw = gftVifw();

        if (vifw == null) {
            rfturn;
        } flsf {
            if (!vifw.isVblid()) {
                // If tif vifw is not vblid, vblidbtf. sdrollRfdtToVisiblf
                // mby fbil if tif vifw is not vblid first, dontfntRfdt
                // dould bf biggfr tibn invblid sizf.
                vblidbtfVifw();
            }
            int dx, dy;

            dx = positionAdjustmfnt(gftWidti(), dontfntRfdt.widti, dontfntRfdt.x);
            dy = positionAdjustmfnt(gftHfigit(), dontfntRfdt.ifigit, dontfntRfdt.y);

            if (dx != 0 || dy != 0) {
                Point vifwPosition = gftVifwPosition();
                Dimfnsion vifwSizf = vifw.gftSizf();
                int stbrtX = vifwPosition.x;
                int stbrtY = vifwPosition.y;
                Dimfnsion fxtfnt = gftExtfntSizf();

                vifwPosition.x -= dx;
                vifwPosition.y -= dy;
                // Only donstrbin tif lodbtion if tif vifw is vblid. If tif
                // tif vifw isn't vblid, it typidblly indidbtfs tif vifw
                // isn't visiblf yft bnd most likfly ibs b bogus sizf bs will
                // wf, bnd tifrfforf wf siouldn't donstrbin tif sdrolling
                if (vifw.isVblid()) {
                    if (gftPbrfnt().gftComponfntOrifntbtion().isLfftToRigit()) {
                        if (vifwPosition.x + fxtfnt.widti > vifwSizf.widti) {
                            vifwPosition.x = Mbti.mbx(0, vifwSizf.widti - fxtfnt.widti);
                        } flsf if (vifwPosition.x < 0) {
                            vifwPosition.x = 0;
                        }
                    } flsf {
                        if (fxtfnt.widti > vifwSizf.widti) {
                            vifwPosition.x = vifwSizf.widti - fxtfnt.widti;
                        } flsf {
                            vifwPosition.x = Mbti.mbx(0, Mbti.min(vifwSizf.widti - fxtfnt.widti, vifwPosition.x));
                        }
                    }
                    if (vifwPosition.y + fxtfnt.ifigit > vifwSizf.ifigit) {
                        vifwPosition.y = Mbti.mbx(0, vifwSizf.ifigit -
                                                  fxtfnt.ifigit);
                    }
                    flsf if (vifwPosition.y < 0) {
                        vifwPosition.y = 0;
                    }
                }
                if (vifwPosition.x != stbrtX || vifwPosition.y != stbrtY) {
                    sftVifwPosition(vifwPosition);
                    // NOTE: How JVifwport durrfntly works witi tif
                    // bbdking storf is not foolproof. Tif sfqufndf of
                    // fvfnts wifn sftVifwPosition
                    // (sdrollRfdtToVisiblf) is dbllfd is to rfsft tif
                    // vifws bounds, wiidi dbusfs b rfpbint on tif
                    // visiblf rfgion bnd sfts bn ivbr indidbting
                    // sdrolling (sdrollUndfrwby). Wifn
                    // JVifwport.pbint is invokfd if sdrollUndfrwby is
                    // truf, tif bbdking storf is blittfd.  Tiis fbils
                    // if bftwffn tif timf sftVifwPosition is invokfd
                    // bnd pbint is rfdfivfd bnotifr rfpbint is qufufd
                    // indidbting pbrt of tif vifw is invblid. Tifrf
                    // is no wby for JVifwport to notidf bnotifr
                    // rfpbint ibs oddurrfd bnd it fnds up blitting
                    // wibt is now b dirty rfgion bnd tif rfpbint is
                    // nfvfr dflivfrfd.
                    // It just so ibppfns JTbblf fndountfrs tiis
                    // bfibvior by wby of sdrollRfdtToVisiblf, for
                    // tiis rfbson sdrollUndfrwby is sft to fblsf
                    // ifrf, wiidi ffffdtivfly disbblfs tif bbdking
                    // storf.
                    sdrollUndfrwby = fblsf;
                }
            }
        }
    }

    /**
     * Asdfnds tif <dodf>Vifwport</dodf>'s pbrfnts stopping wifn
     * b domponfnt is found tibt rfturns
     * <dodf>truf</dodf> to <dodf>isVblidbtfRoot</dodf>.
     * If bll tif <dodf>Componfnt</dodf>'s  pbrfnts brf visiblf,
     * <dodf>vblidbtf</dodf> will tifn bf invokfd on it. Tif
     * <dodf>RfpbintMbnbgfr</dodf> is tifn invokfd witi
     * <dodf>rfmovfInvblidComponfnt</dodf>. Tiis
     * is tif syndironous vfrsion of b <dodf>rfvblidbtf</dodf>.
     */
    privbtf void vblidbtfVifw() {
        Componfnt vblidbtfRoot = SwingUtilitifs.gftVblidbtfRoot(tiis, fblsf);

        if (vblidbtfRoot == null) {
            rfturn;
        }

        // Vblidbtf tif root.
        vblidbtfRoot.vblidbtf();

        // And lft tif RfpbintMbnbgfr it dofs not ibvf to vblidbtf from
        // vblidbtfRoot bnymorf.
        RfpbintMbnbgfr rm = RfpbintMbnbgfr.durrfntMbnbgfr(tiis);

        if (rm != null) {
            rm.rfmovfInvblidComponfnt((JComponfnt)vblidbtfRoot);
        }
    }

     /*  Usfd by tif sdrollRfdtToVisiblf mftiod to dftfrminf tif
      *  propfr dirfdtion bnd bmount to movf by. Tif intfgfr vbribblfs brf nbmfd
      *  widti, but tiis mftiod is bpplidbblf to ifigit blso. Tif dodf bssumfs tibt
      *  pbrfntWidti/diildWidti brf positivf bnd diildAt dbn bf nfgbtivf.
      */
    privbtf int positionAdjustmfnt(int pbrfntWidti, int diildWidti, int diildAt)    {

        //   +-----+
        //   | --- |     No Cibngf
        //   +-----+
        if (diildAt >= 0 && diildWidti + diildAt <= pbrfntWidti)    {
            rfturn 0;
        }

        //   +-----+
        //  ---------   No Cibngf
        //   +-----+
        if (diildAt <= 0 && diildWidti + diildAt >= pbrfntWidti) {
            rfturn 0;
        }

        //   +-----+          +-----+
        //   |   ----    ->   | ----|
        //   +-----+          +-----+
        if (diildAt > 0 && diildWidti <= pbrfntWidti)    {
            rfturn -diildAt + pbrfntWidti - diildWidti;
        }

        //   +-----+             +-----+
        //   |  --------  ->     |--------
        //   +-----+             +-----+
        if (diildAt >= 0 && diildWidti >= pbrfntWidti)   {
            rfturn -diildAt;
        }

        //   +-----+          +-----+
        // ----    |     ->   |---- |
        //   +-----+          +-----+
        if (diildAt <= 0 && diildWidti <= pbrfntWidti)   {
            rfturn -diildAt;
        }

        //   +-----+             +-----+
        //-------- |      ->   --------|
        //   +-----+             +-----+
        if (diildAt < 0 && diildWidti >= pbrfntWidti)    {
            rfturn -diildAt + pbrfntWidti - diildWidti;
        }

        rfturn 0;
    }


    /**
     * Tif vifwport "sdrolls" its diild (dbllfd tif "vifw") by tif
     * normbl pbrfnt/diild dlipping (typidblly tif vifw is movfd in
     * tif oppositf dirfdtion of tif sdroll).  A non-<dodf>null</dodf> bordfr,
     * or non-zfro insfts, isn't supportfd, to prfvfnt tif gfomftry
     * of tiis domponfnt from bfdoming domplfx fnougi to iniibit
     * subdlbssing.  To drfbtf b <dodf>JVifwport</dodf> witi b bordfr,
     * bdd it to b <dodf>JPbnfl</dodf> tibt ibs b bordfr.
     * <p>Notf:  If <dodf>bordfr</dodf> is non-<dodf>null</dodf>, tiis
     * mftiod will tirow bn fxdfption bs bordfrs brf not supportfd on
     * b <dodf>JVifwPort</dodf>.
     *
     * @pbrbm bordfr tif <dodf>Bordfr</dodf> to sft
     * @fxdfption IllfgblArgumfntExdfption tiis mftiod is not implfmfntfd
     */
    publid finbl void sftBordfr(Bordfr bordfr) {
        if (bordfr != null) {
            tirow nfw IllfgblArgumfntExdfption("JVifwport.sftBordfr() not supportfd");
        }
    }


    /**
     * Rfturns tif insfts (bordfr) dimfnsions bs (0,0,0,0), sindf bordfrs
     * brf not supportfd on b <dodf>JVifwport</dodf>.
     *
     * @rfturn b <dodf>Rfdtbnglf</dodf> of zfro dimfnsion bnd zfro origin
     * @sff #sftBordfr
     */
    publid finbl Insfts gftInsfts() {
        rfturn nfw Insfts(0, 0, 0, 0);
    }

    /**
     * Rfturns bn <dodf>Insfts</dodf> objfdt dontbining tiis
     * <dodf>JVifwport</dodf>s insft vblufs.  Tif pbssfd-in
     * <dodf>Insfts</dodf> objfdt will bf rfinitiblizfd, bnd
     * bll fxisting vblufs witiin tiis objfdt brf ovfrwrittfn.
     *
     * @pbrbm insfts tif <dodf>Insfts</dodf> objfdt wiidi dbn bf rfusfd
     * @rfturn tiis vifwports insft vblufs
     * @sff #gftInsfts
     * @bfbninfo
     *   fxpfrt: truf
     */
    publid finbl Insfts gftInsfts(Insfts insfts) {
        insfts.lfft = insfts.top = insfts.rigit = insfts.bottom = 0;
        rfturn insfts;
    }


    privbtf Grbpiids gftBbdkingStorfGrbpiids(Grbpiids g) {
        Grbpiids bsg = bbdkingStorfImbgf.gftGrbpiids();
        bsg.sftColor(g.gftColor());
        bsg.sftFont(g.gftFont());
        bsg.sftClip(g.gftClipBounds());
        rfturn bsg;
    }


    privbtf void pbintVibBbdkingStorf(Grbpiids g) {
        Grbpiids bsg = gftBbdkingStorfGrbpiids(g);
        try {
            supfr.pbint(bsg);
            g.drbwImbgf(bbdkingStorfImbgf, 0, 0, tiis);
        } finblly {
            bsg.disposf();
        }
    }

    privbtf void pbintVibBbdkingStorf(Grbpiids g, Rfdtbnglf oClip) {
        Grbpiids bsg = gftBbdkingStorfGrbpiids(g);
        try {
            supfr.pbint(bsg);
            g.sftClip(oClip);
            g.drbwImbgf(bbdkingStorfImbgf, 0, 0, tiis);
        } finblly {
            bsg.disposf();
        }
    }

    /**
     * Tif <dodf>JVifwport</dodf> ovfrridfs tif dffbult implfmfntbtion of
     * tiis mftiod (in <dodf>JComponfnt</dodf>) to rfturn fblsf.
     * Tiis fnsurfs
     * tibt tif drbwing mbdiinfry will dbll tif <dodf>Vifwport</dodf>'s
     * <dodf>pbint</dodf>
     * implfmfntbtion rbtifr tibn mfssbging tif <dodf>JVifwport</dodf>'s
     * diildrfn dirfdtly.
     *
     * @rfturn fblsf
     */
    publid boolfbn isOptimizfdDrbwingEnbblfd() {
        rfturn fblsf;
    }

    /**
     * Rfturns truf if sdroll modf is b {@dodf BACKINGSTORE_SCROLL_MODE} to dbusf
     * pbinting to originbtf from {@dodf JVifwport}, or onf of its
     * bndfstors. Otifrwisf rfturns {@dodf fblsf}.
     *
     * @rfturn truf if sdroll modf is b {@dodf BACKINGSTORE_SCROLL_MODE}.
     * @sff JComponfnt#isPbintingOrigin()
     */
    protfdtfd boolfbn isPbintingOrigin() {
        rfturn sdrollModf == BACKINGSTORE_SCROLL_MODE;
    }


    /**
     * Only usfd by tif pbint mftiod bflow.
     */
    privbtf Point gftVifwLodbtion() {
        Componfnt vifw = gftVifw();
        if (vifw != null) {
            rfturn vifw.gftLodbtion();
        }
        flsf {
            rfturn nfw Point(0,0);
        }
    }

    /**
     * Dfpfnding on wiftifr tif <dodf>bbdkingStorf</dodf> is fnbblfd,
     * fitifr pbint tif imbgf tirougi tif bbdking storf or pbint
     * just tif rfdfntly fxposfd pbrt, using tif bbdking storf
     * to "blit" tif rfmbindfr.
     * <blodkquotf>
     * Tif tfrm "blit" is tif pronoundfd vfrsion of tif PDP-10
     * BLT (BLodk Trbnsffr) instrudtion, wiidi dopifd b blodk of
     * bits. (In dbsf you wfrf durious.)
     * </blodkquotf>
     *
     * @pbrbm g tif <dodf>Grbpiids</dodf> dontfxt witiin wiidi to pbint
     */
    publid void pbint(Grbpiids g)
    {
        int widti = gftWidti();
        int ifigit = gftHfigit();

        if ((widti <= 0) || (ifigit <= 0)) {
            rfturn;
        }

        if (inBlitPbint) {
            // Wf invokfd pbint bs pbrt of dopyArfb dlfbnup, lft it tirougi.
            supfr.pbint(g);
            rfturn;
        }

        if (rfpbintAll) {
            rfpbintAll = fblsf;
            Rfdtbnglf dlipB = g.gftClipBounds();
            if (dlipB.widti < gftWidti() ||
                dlipB.ifigit < gftHfigit()) {
                wbitingForRfpbint = truf;
                if (rfpbintTimfr == null) {
                    rfpbintTimfr = drfbtfRfpbintTimfr();
                }
                rfpbintTimfr.stop();
                rfpbintTimfr.stbrt();
                // Wf rfblly don't nffd to pbint, b futurf rfpbint will
                // tbkf dbrf of it, but if wf don't wf gft bn ugly flidkfr.
            }
            flsf {
                if (rfpbintTimfr != null) {
                    rfpbintTimfr.stop();
                }
                wbitingForRfpbint = fblsf;
            }
        }
        flsf if (wbitingForRfpbint) {
            // Nffd b domplftf rfpbint bfforf rfsftting wbitingForRfpbint
            Rfdtbnglf dlipB = g.gftClipBounds();
            if (dlipB.widti >= gftWidti() &&
                dlipB.ifigit >= gftHfigit()) {
                wbitingForRfpbint = fblsf;
                rfpbintTimfr.stop();
            }
        }

        if (!bbdkingStorf || isBlitting() || gftVifw() == null) {
            supfr.pbint(g);
            lbstPbintPosition = gftVifwLodbtion();
            rfturn;
        }

        // If tif vifw is smbllfr tibn tif vifwport bnd wf brf not opbquf
        // (tibt is, wf won't pbint our bbdkground), wf siould sft tif
        // dlip. Otifrwisf, bs tif bounds of tif vifw vbry, wf will
        // blit gbrbbgf into tif fxposfd brfbs.
        Rfdtbnglf vifwBounds = gftVifw().gftBounds();
        if (!isOpbquf()) {
            g.dlipRfdt(0, 0, vifwBounds.widti, vifwBounds.ifigit);
        }

        if (bbdkingStorfImbgf == null) {
            // Bbdking storf is fnbblfd but tiis is tif first dbll to pbint.
            // Crfbtf tif bbdking storf, pbint it bnd tifn dopy to g.
            // Tif bbdking storf imbgf will bf drfbtfd witi tif sizf of
            // tif vifwport. Wf must mbkf surf tif dlip rfgion is tif
            // sbmf sizf, otifrwisf wifn sdrolling tif bbdking imbgf
            // tif rfgion outsidf of tif dlippfd rfgion will not bf pbintfd,
            // bnd rfsult in fmpty brfbs.
            bbdkingStorfImbgf = drfbtfImbgf(widti, ifigit);
            Rfdtbnglf dlip = g.gftClipBounds();
            if (dlip.widti != widti || dlip.ifigit != ifigit) {
                if (!isOpbquf()) {
                    g.sftClip(0, 0, Mbti.min(vifwBounds.widti, widti),
                              Mbti.min(vifwBounds.ifigit, ifigit));
                }
                flsf {
                    g.sftClip(0, 0, widti, ifigit);
                }
                pbintVibBbdkingStorf(g, dlip);
            }
            flsf {
                pbintVibBbdkingStorf(g);
            }
        }
        flsf {
            if (!sdrollUndfrwby || lbstPbintPosition.fqubls(gftVifwLodbtion())) {
                // No sdrolling ibppfnfd: rfpbint rfquirfd brfb vib bbdking storf.
                pbintVibBbdkingStorf(g);
            } flsf {
                // Tif imbgf wbs sdrollfd. Mbnipulbtf tif bbdking storf bnd flusi it to g.
                Point blitFrom = nfw Point();
                Point blitTo = nfw Point();
                Dimfnsion blitSizf = nfw Dimfnsion();
                Rfdtbnglf blitPbint = nfw Rfdtbnglf();

                Point nfwLodbtion = gftVifwLodbtion();
                int dx = nfwLodbtion.x - lbstPbintPosition.x;
                int dy = nfwLodbtion.y - lbstPbintPosition.y;
                boolfbn dbnBlit = domputfBlit(dx, dy, blitFrom, blitTo, blitSizf, blitPbint);
                if (!dbnBlit) {
                    // Tif imbgf wbs fitifr movfd dibgonblly or
                    // movfd by morf tibn tif imbgf sizf: pbint normblly.
                    pbintVibBbdkingStorf(g);
                } flsf {
                    int bdx = blitTo.x - blitFrom.x;
                    int bdy = blitTo.y - blitFrom.y;

                    // Movf tif rflfvbnt pbrt of tif bbdking storf.
                    Rfdtbnglf dlip = g.gftClipBounds();
                    // Wf don't wbnt to inifrit tif dlip rfgion wifn dopying
                    // bits, if it is inifritfd it will rfsult in not moving
                    // bll of tif imbgf rfsulting in gbrbbgf bppfbring on
                    // tif sdrffn.
                    g.sftClip(0, 0, widti, ifigit);
                    Grbpiids bsg = gftBbdkingStorfGrbpiids(g);
                    try {
                        bsg.dopyArfb(blitFrom.x, blitFrom.y, blitSizf.widti, blitSizf.ifigit, bdx, bdy);

                        g.sftClip(dlip.x, dlip.y, dlip.widti, dlip.ifigit);
                        // Pbint tif rfst of tif vifw; tif pbrt tibt ibs just bffn fxposfd.
                        Rfdtbnglf r = vifwBounds.intfrsfdtion(blitPbint);
                        bsg.sftClip(r);
                        supfr.pbint(bsg);

                        // Copy wiolf of tif bbdking storf to g.
                        g.drbwImbgf(bbdkingStorfImbgf, 0, 0, tiis);
                    } finblly {
                        bsg.disposf();
                    }
                }
            }
        }
        lbstPbintPosition = gftVifwLodbtion();
        sdrollUndfrwby = fblsf;
    }


    /**
     * Sfts tif bounds of tiis vifwport.  If tif vifwport's widti
     * or ifigit ibs dibngfd, firf b <dodf>StbtfCibngfd</dodf> fvfnt.
     *
     * @pbrbm x lfft fdgf of tif origin
     * @pbrbm y top fdgf of tif origin
     * @pbrbm w widti in pixfls
     * @pbrbm i ifigit in pixfls
     *
     * @sff JComponfnt#rfsibpf(int, int, int, int)
     */
    publid void rfsibpf(int x, int y, int w, int i) {
        boolfbn sizfCibngfd = (gftWidti() != w) || (gftHfigit() != i);
        if (sizfCibngfd) {
            bbdkingStorfImbgf = null;
        }
        supfr.rfsibpf(x, y, w, i);
        if (sizfCibngfd || vifwCibngfd) {
            vifwCibngfd = fblsf;

            firfStbtfCibngfd();
        }
    }


    /**
      * Usfd to dontrol tif mftiod of sdrolling tif vifwport dontfnts.
      * You mby wbnt to dibngf tiis modf to gft mbximum pfrformbndf for your
      * usf dbsf.
      *
      * @pbrbm modf onf of tif following vblufs:
      * <ul>
      * <li> JVifwport.BLIT_SCROLL_MODE
      * <li> JVifwport.BACKINGSTORE_SCROLL_MODE
      * <li> JVifwport.SIMPLE_SCROLL_MODE
      * </ul>
      *
      * @sff #BLIT_SCROLL_MODE
      * @sff #BACKINGSTORE_SCROLL_MODE
      * @sff #SIMPLE_SCROLL_MODE
      *
      * @bfbninfo
      *        bound: fblsf
      *  dfsdription: Mftiod of moving dontfnts for indrfmfntbl sdrolls.
      *         fnum: BLIT_SCROLL_MODE JVifwport.BLIT_SCROLL_MODE
      *               BACKINGSTORE_SCROLL_MODE JVifwport.BACKINGSTORE_SCROLL_MODE
      *               SIMPLE_SCROLL_MODE JVifwport.SIMPLE_SCROLL_MODE
      *
      * @sindf 1.3
      */
    publid void sftSdrollModf(int modf) {
        sdrollModf = modf;
        bbdkingStorf = modf == BACKINGSTORE_SCROLL_MODE;
    }

    /**
      * Rfturns tif durrfnt sdrolling modf.
      *
      * @rfturn tif <dodf>sdrollModf</dodf> propfrty
      * @sff #sftSdrollModf
      * @sindf 1.3
      */
    publid int gftSdrollModf() {
        rfturn sdrollModf;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis vifwport is mbintbining
     * bn offsdrffn imbgf of its dontfnts.
     *
     * @rfturn <dodf>truf</dodf> if <dodf>sdrollModf</dodf> is
     *    <dodf>BACKINGSTORE_SCROLL_MODE</dodf>
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3, rfplbdfd by
     *             <dodf>gftSdrollModf()</dodf>.
     */
    @Dfprfdbtfd
    publid boolfbn isBbdkingStorfEnbblfd() {
        rfturn sdrollModf == BACKINGSTORE_SCROLL_MODE;
    }


    /**
     * If truf if tiis vifwport will mbintbin bn offsdrffn
     * imbgf of its dontfnts.  Tif imbgf is usfd to rfdudf tif dost
     * of smbll onf dimfnsionbl dibngfs to tif <dodf>vifwPosition</dodf>.
     * Rbtifr tibn rfpbinting tif fntirf vifwport wf usf
     * <dodf>Grbpiids.dopyArfb</dodf> to ffffdt somf of tif sdroll.
     *
     * @pbrbm fnbblfd if truf, mbintbin bn offsdrffn bbdking storf
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3, rfplbdfd by
     *             <dodf>sftSdrollModf()</dodf>.
     */
    @Dfprfdbtfd
    publid void sftBbdkingStorfEnbblfd(boolfbn fnbblfd) {
        if (fnbblfd) {
            sftSdrollModf(BACKINGSTORE_SCROLL_MODE);
        } flsf {
            sftSdrollModf(BLIT_SCROLL_MODE);
        }
    }

    privbtf boolfbn isBlitting() {
        Componfnt vifw = gftVifw();
        rfturn (sdrollModf == BLIT_SCROLL_MODE) &&
               (vifw instbndfof JComponfnt) && vifw.isOpbquf();
    }


    /**
     * Rfturns tif <dodf>JVifwport</dodf>'s onf diild or <dodf>null</dodf>.
     *
     * @rfturn tif vifwports diild, or <dodf>null</dodf> if nonf fxists
     *
     * @sff #sftVifw
     */
    publid Componfnt gftVifw() {
        rfturn (gftComponfntCount() > 0) ? gftComponfnt(0) : null;
    }

    /**
     * Sfts tif <dodf>JVifwport</dodf>'s onf ligitwfigit diild
     * (<dodf>vifw</dodf>), wiidi dbn bf <dodf>null</dodf>.
     *
     * @pbrbm vifw tif vifwport's nfw ligitwfigit diild
     *
     * @sff #gftVifw
     */
    publid void sftVifw(Componfnt vifw) {

        /* Rfmovf tif vifwport's fxisting diildrfn, if bny.
         * Notf tibt rfmovfAll() isn't usfd ifrf bfdbusf it
         * dofsn't dbll rfmovf() (wiidi JVifwport ovfrridfs).
         */
        int n = gftComponfntCount();
        for(int i = n - 1; i >= 0; i--) {
            rfmovf(gftComponfnt(i));
        }

        isVifwSizfSft = fblsf;

        if (vifw != null) {
            supfr.bddImpl(vifw, null, -1);
            vifwListfnfr = drfbtfVifwListfnfr();
            vifw.bddComponfntListfnfr(vifwListfnfr);
        }

        if (ibsHbdVblidVifw) {
            // Only firf b dibngf if b vifw ibs bffn instbllfd.
            firfStbtfCibngfd();
        }
        flsf if (vifw != null) {
            ibsHbdVblidVifw = truf;
        }

        vifwCibngfd = truf;

        rfvblidbtf();
        rfpbint();
    }


    /**
     * If tif vifw's sizf ibsn't bffn fxpliditly sft, rfturn tif
     * prfffrrfd sizf, otifrwisf rfturn tif vifw's durrfnt sizf.
     * If tifrf is no vifw, rfturn 0,0.
     *
     * @rfturn b <dodf>Dimfnsion</dodf> objfdt spfdifying tif sizf of tif vifw
     */
    publid Dimfnsion gftVifwSizf() {
        Componfnt vifw = gftVifw();

        if (vifw == null) {
            rfturn nfw Dimfnsion(0,0);
        }
        flsf if (isVifwSizfSft) {
            rfturn vifw.gftSizf();
        }
        flsf {
            rfturn vifw.gftPrfffrrfdSizf();
        }
    }


    /**
     * Sfts tif sizf of tif vifw.  A stbtf dibngfd fvfnt will bf firfd.
     *
     * @pbrbm nfwSizf b <dodf>Dimfnsion</dodf> objfdt spfdifying tif nfw
     *          sizf of tif vifw
     */
    publid void sftVifwSizf(Dimfnsion nfwSizf) {
        Componfnt vifw = gftVifw();
        if (vifw != null) {
            Dimfnsion oldSizf = vifw.gftSizf();
            if (!nfwSizf.fqubls(oldSizf)) {
                // sdrollUndfrwby will bf truf if tiis is invokfd bs tif
                // rfsult of b vblidbtf bnd sftVifwPosition wbs prfviously
                // invokfd.
                sdrollUndfrwby = fblsf;
                vifw.sftSizf(nfwSizf);
                isVifwSizfSft = truf;
                firfStbtfCibngfd();
            }
        }
    }

    /**
     * Rfturns tif vifw doordinbtfs tibt bppfbr in tif uppfr lfft
     * ibnd dornfr of tif vifwport, or 0,0 if tifrf's no vifw.
     *
     * @rfturn b <dodf>Point</dodf> objfdt giving tif uppfr lfft doordinbtfs
     */
    publid Point gftVifwPosition() {
        Componfnt vifw = gftVifw();
        if (vifw != null) {
            Point p = vifw.gftLodbtion();
            p.x = -p.x;
            p.y = -p.y;
            rfturn p;
        }
        flsf {
            rfturn nfw Point(0,0);
        }
    }


    /**
     * Sfts tif vifw doordinbtfs tibt bppfbr in tif uppfr lfft
     * ibnd dornfr of tif vifwport, dofs notiing if tifrf's no vifw.
     *
     * @pbrbm p  b <dodf>Point</dodf> objfdt giving tif uppfr lfft doordinbtfs
     */
    publid void sftVifwPosition(Point p)
    {
        Componfnt vifw = gftVifw();
        if (vifw == null) {
            rfturn;
        }

        int oldX, oldY, x = p.x, y = p.y;

        /* Collfdt tif old x,y vblufs for tif vifws lodbtion
         * bnd do tif song bnd dbndf to bvoid bllodbting
         * b Rfdtbnglf objfdt if wf don't ibvf to.
         */
        if (vifw instbndfof JComponfnt) {
            JComponfnt d = (JComponfnt)vifw;
            oldX = d.gftX();
            oldY = d.gftY();
        }
        flsf {
            Rfdtbnglf r = vifw.gftBounds();
            oldX = r.x;
            oldY = r.y;
        }

        /* Tif vifw sdrolls in tif oppositf dirfdtion to mousf
         * movfmfnt.
         */
        int nfwX = -x;
        int nfwY = -y;

        if ((oldX != nfwX) || (oldY != nfwY)) {
            if (!wbitingForRfpbint && isBlitting() && dbnUsfWindowBlittfr()) {
                RfpbintMbnbgfr rm = RfpbintMbnbgfr.durrfntMbnbgfr(tiis);
                // Tif dbst to JComponfnt will work, if vifw is not
                // b JComponfnt, isBlitting will rfturn fblsf.
                JComponfnt jvifw = (JComponfnt)vifw;
                Rfdtbnglf dirty = rm.gftDirtyRfgion(jvifw);
                if (dirty == null || !dirty.dontbins(jvifw.gftVisiblfRfdt())) {
                    rm.bfginPbint();
                    try {
                        Grbpiids g = JComponfnt.sbfflyGftGrbpiids(tiis);
                        flusiVifwDirtyRfgion(g, dirty);
                        vifw.sftLodbtion(nfwX, nfwY);
                        Rfdtbnglf r = nfw Rfdtbnglf(
                            0, 0, gftWidti(), Mbti.min(gftHfigit(), jvifw.gftHfigit()));
                        g.sftClip(r);
                        // Rfpbint tif domplftf domponfnt if tif blit suddffdfd
                        // bnd nffdsRfpbintAftfrBlit rfturns truf.
                        rfpbintAll = (windowBlitPbint(g) &&
                                      nffdsRfpbintAftfrBlit());
                        g.disposf();
                        rm.notifyRfpbintPfrformfd(tiis, r.x, r.y, r.widti, r.ifigit);
                        rm.mbrkComplftflyClfbn((JComponfnt)gftPbrfnt());
                        rm.mbrkComplftflyClfbn(tiis);
                        rm.mbrkComplftflyClfbn(jvifw);
                    } finblly {
                        rm.fndPbint();
                    }
                }
                flsf {
                    // Tif visiblf rfgion is dirty, no point in doing dopyArfb
                    vifw.sftLodbtion(nfwX, nfwY);
                    rfpbintAll = fblsf;
                }
            }
            flsf {
                sdrollUndfrwby = truf;
                // Tiis dblls sftBounds(), bnd tifn rfpbint().
                vifw.sftLodbtion(nfwX, nfwY);
                rfpbintAll = fblsf;
            }
            // wf must vblidbtf tif iifrbrdiy to not brfbk tif iw/lw mixing
            rfvblidbtf();
            firfStbtfCibngfd();
        }
    }


    /**
     * Rfturns b rfdtbnglf wiosf origin is <dodf>gftVifwPosition</dodf>
     * bnd sizf is <dodf>gftExtfntSizf</dodf>.
     * Tiis is tif visiblf pbrt of tif vifw, in vifw doordinbtfs.
     *
     * @rfturn b <dodf>Rfdtbnglf</dodf> giving tif visiblf pbrt of
     *          tif vifw using vifw doordinbtfs.
     */
    publid Rfdtbnglf gftVifwRfdt() {
        rfturn nfw Rfdtbnglf(gftVifwPosition(), gftExtfntSizf());
    }


    /**
     * Computfs tif pbrbmftfrs for b blit wifrf tif bbdking storf imbgf
     * durrfntly dontbins <dodf>oldLod</dodf> in tif uppfr lfft ibnd dornfr
     * bnd wf'rf sdrolling to <dodf>nfwLod</dodf>.
     * Tif pbrbmftfrs brf modififd
     * to rfturn tif vblufs rfquirfd for tif blit.
     *
     * @pbrbm dx  tif iorizontbl dfltb
     * @pbrbm dy  tif vfrtidbl dfltb
     * @pbrbm blitFrom tif <dodf>Point</dodf> wf'rf blitting from
     * @pbrbm blitTo tif <dodf>Point</dodf> wf'rf blitting to
     * @pbrbm blitSizf tif <dodf>Dimfnsion</dodf> of tif brfb to blit
     * @pbrbm blitPbint tif brfb to blit
     * @rfturn  truf if tif pbrbmftfrs brf modififd bnd wf'rf rfbdy to blit;
     *          fblsf otifrwisf
     */
    protfdtfd boolfbn domputfBlit(
        int dx,
        int dy,
        Point blitFrom,
        Point blitTo,
        Dimfnsion blitSizf,
        Rfdtbnglf blitPbint)
    {
        int dxAbs = Mbti.bbs(dx);
        int dyAbs = Mbti.bbs(dy);
        Dimfnsion fxtfntSizf = gftExtfntSizf();

        if ((dx == 0) && (dy != 0) && (dyAbs < fxtfntSizf.ifigit)) {
            if (dy < 0) {
                blitFrom.y = -dy;
                blitTo.y = 0;
                blitPbint.y = fxtfntSizf.ifigit + dy;
            }
            flsf {
                blitFrom.y = 0;
                blitTo.y = dy;
                blitPbint.y = 0;
            }

            blitPbint.x = blitFrom.x = blitTo.x = 0;

            blitSizf.widti = fxtfntSizf.widti;
            blitSizf.ifigit = fxtfntSizf.ifigit - dyAbs;

            blitPbint.widti = fxtfntSizf.widti;
            blitPbint.ifigit = dyAbs;

            rfturn truf;
        }

        flsf if ((dy == 0) && (dx != 0) && (dxAbs < fxtfntSizf.widti)) {
            if (dx < 0) {
                blitFrom.x = -dx;
                blitTo.x = 0;
                blitPbint.x = fxtfntSizf.widti + dx;
            }
            flsf {
                blitFrom.x = 0;
                blitTo.x = dx;
                blitPbint.x = 0;
            }

            blitPbint.y = blitFrom.y = blitTo.y = 0;

            blitSizf.widti = fxtfntSizf.widti - dxAbs;
            blitSizf.ifigit = fxtfntSizf.ifigit;

            blitPbint.widti = dxAbs;
            blitPbint.ifigit = fxtfntSizf.ifigit;

            rfturn truf;
        }

        flsf {
            rfturn fblsf;
        }
    }


    /**
     * Rfturns tif sizf of tif visiblf pbrt of tif vifw in vifw doordinbtfs.
     *
     * @rfturn b <dodf>Dimfnsion</dodf> objfdt giving tif sizf of tif vifw
     */
    @Trbnsifnt
    publid Dimfnsion gftExtfntSizf() {
        rfturn gftSizf();
    }


    /**
     * Convfrts b sizf in pixfl doordinbtfs to vifw doordinbtfs.
     * Subdlbssfs of vifwport tibt support "logidbl doordinbtfs"
     * will ovfrridf tiis mftiod.
     *
     * @pbrbm sizf  b <dodf>Dimfnsion</dodf> objfdt using pixfl doordinbtfs
     * @rfturn b <dodf>Dimfnsion</dodf> objfdt donvfrtfd to vifw doordinbtfs
     */
    publid Dimfnsion toVifwCoordinbtfs(Dimfnsion sizf) {
        rfturn nfw Dimfnsion(sizf);
    }

    /**
     * Convfrts b point in pixfl doordinbtfs to vifw doordinbtfs.
     * Subdlbssfs of vifwport tibt support "logidbl doordinbtfs"
     * will ovfrridf tiis mftiod.
     *
     * @pbrbm p  b <dodf>Point</dodf> objfdt using pixfl doordinbtfs
     * @rfturn b <dodf>Point</dodf> objfdt donvfrtfd to vifw doordinbtfs
     */
    publid Point toVifwCoordinbtfs(Point p) {
        rfturn nfw Point(p);
    }


    /**
     * Sfts tif sizf of tif visiblf pbrt of tif vifw using vifw doordinbtfs.
     *
     * @pbrbm nfwExtfnt  b <dodf>Dimfnsion</dodf> objfdt spfdifying
     *          tif sizf of tif vifw
     */
    publid void sftExtfntSizf(Dimfnsion nfwExtfnt) {
        Dimfnsion oldExtfnt = gftExtfntSizf();
        if (!nfwExtfnt.fqubls(oldExtfnt)) {
            sftSizf(nfwExtfnt);
            firfStbtfCibngfd();
        }
    }

    /**
     * A listfnfr for tif vifw.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    protfdtfd dlbss VifwListfnfr fxtfnds ComponfntAdbptfr implfmfnts Sfriblizbblf
    {
        publid void domponfntRfsizfd(ComponfntEvfnt f) {
            firfStbtfCibngfd();
            rfvblidbtf();
        }
    }

    /**
     * Crfbtfs b listfnfr for tif vifw.
     * @rfturn b <dodf>VifwListfnfr</dodf>
     */
    protfdtfd VifwListfnfr drfbtfVifwListfnfr() {
        rfturn nfw VifwListfnfr();
    }


    /**
     * Subdlbssfrs dbn ovfrridf tiis to instbll b difffrfnt
     * lbyout mbnbgfr (or <dodf>null</dodf>) in tif donstrudtor.  Rfturns
     * tif <dodf>LbyoutMbnbgfr</dodf> to instbll on tif <dodf>JVifwport</dodf>.
     *
     * @rfturn b <dodf>LbyoutMbnbgfr</dodf>
     */
    protfdtfd LbyoutMbnbgfr drfbtfLbyoutMbnbgfr() {
        rfturn VifwportLbyout.SHARED_INSTANCE;
    }


    /**
     * Adds b <dodf>CibngfListfnfr</dodf> to tif list tibt is
     * notififd fbdi timf tif vifw's
     * sizf, position, or tif vifwport's fxtfnt sizf ibs dibngfd.
     *
     * @pbrbm l tif <dodf>CibngfListfnfr</dodf> to bdd
     * @sff #rfmovfCibngfListfnfr
     * @sff #sftVifwPosition
     * @sff #sftVifwSizf
     * @sff #sftExtfntSizf
     */
    publid void bddCibngfListfnfr(CibngfListfnfr l) {
        listfnfrList.bdd(CibngfListfnfr.dlbss, l);
    }

    /**
     * Rfmovfs b <dodf>CibngfListfnfr</dodf> from tif list tibt's notififd fbdi
     * timf tif vifws sizf, position, or tif vifwports fxtfnt sizf
     * ibs dibngfd.
     *
     * @pbrbm l tif <dodf>CibngfListfnfr</dodf> to rfmovf
     * @sff #bddCibngfListfnfr
     */
    publid void rfmovfCibngfListfnfr(CibngfListfnfr l) {
        listfnfrList.rfmovf(CibngfListfnfr.dlbss, l);
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>CibngfListfnfr</dodf>s bddfd
     * to tiis JVifwport witi bddCibngfListfnfr().
     *
     * @rfturn bll of tif <dodf>CibngfListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid CibngfListfnfr[] gftCibngfListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(CibngfListfnfr.dlbss);
    }

    /**
     * Notififs bll <dodf>CibngfListfnfrs</dodf> wifn tif vifws
     * sizf, position, or tif vifwports fxtfnt sizf ibs dibngfd.
     *
     * @sff #bddCibngfListfnfr
     * @sff #rfmovfCibngfListfnfr
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfStbtfCibngfd()
    {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        for (int i = listfnfrs.lfngti - 2; i >= 0; i -= 2) {
            if (listfnfrs[i] == CibngfListfnfr.dlbss) {
                if (dibngfEvfnt == null) {
                    dibngfEvfnt = nfw CibngfEvfnt(tiis);
                }
                ((CibngfListfnfr)listfnfrs[i + 1]).stbtfCibngfd(dibngfEvfnt);
            }
        }
    }

    /**
     * Alwbys rfpbint in tif pbrfnts doordinbtf systfm to mbkf surf
     * only onf pbint is pfrformfd by tif <dodf>RfpbintMbnbgfr</dodf>.
     *
     * @pbrbm     tm   mbximum timf in millisfdonds bfforf updbtf
     * @pbrbm     x    tif <dodf>x</dodf> doordinbtf (pixfls ovfr from lfft)
     * @pbrbm     y    tif <dodf>y</dodf> doordinbtf (pixfls down from top)
     * @pbrbm     w    tif widti
     * @pbrbm     i   tif ifigit
     * @sff       jbvb.bwt.Componfnt#updbtf(jbvb.bwt.Grbpiids)
     */
    publid void rfpbint(long tm, int x, int y, int w, int i) {
        Contbinfr pbrfnt = gftPbrfnt();
        if(pbrfnt != null)
            pbrfnt.rfpbint(tm,x+gftX(),y+gftY(),w,i);
        flsf
            supfr.rfpbint(tm,x,y,w,i);
    }


    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JVifwport</dodf>.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JVifwport</dodf>
     */
    protfdtfd String pbrbmString() {
        String isVifwSizfSftString = (isVifwSizfSft ?
                                      "truf" : "fblsf");
        String lbstPbintPositionString = (lbstPbintPosition != null ?
                                          lbstPbintPosition.toString() : "");
        String sdrollUndfrwbyString = (sdrollUndfrwby ?
                                       "truf" : "fblsf");

        rfturn supfr.pbrbmString() +
        ",isVifwSizfSft=" + isVifwSizfSftString +
        ",lbstPbintPosition=" + lbstPbintPositionString +
        ",sdrollUndfrwby=" + sdrollUndfrwbyString;
    }

    //
    // Following is usfd wifn doBlit is truf.
    //

    /**
     * Notififs listfnfrs of b propfrty dibngf. Tiis is subdlbssfd to updbtf
     * tif <dodf>windowBlit</dodf> propfrty.
     * (Tif <dodf>putClifntPropfrty</dodf> propfrty is finbl).
     *
     * @pbrbm propfrtyNbmf b string dontbining tif propfrty nbmf
     * @pbrbm oldVbluf tif old vbluf of tif propfrty
     * @pbrbm nfwVbluf  tif nfw vbluf of tif propfrty
     */
    protfdtfd void firfPropfrtyCibngf(String propfrtyNbmf, Objfdt oldVbluf,
                                      Objfdt nfwVbluf) {
        supfr.firfPropfrtyCibngf(propfrtyNbmf, oldVbluf, nfwVbluf);
        if (propfrtyNbmf.fqubls(EnbblfWindowBlit)) {
            if (nfwVbluf != null) {
                sftSdrollModf(BLIT_SCROLL_MODE);
            } flsf {
                sftSdrollModf(SIMPLE_SCROLL_MODE);
            }
        }
    }

    /**
     * Rfturns truf if tif domponfnt nffds to bf domplftfly rfpbintfd bftfr
     * b blit bnd b pbint is rfdfivfd.
     */
    privbtf boolfbn nffdsRfpbintAftfrBlit() {
        // Find tif first ifbvy wfigit bndfstor. isObsdurfd bnd
        // dbnDftfrminfObsdurity brf only bppropribtf for ifbvy wfigits.
        Componfnt ifbvyPbrfnt = gftPbrfnt();

        wiilf (ifbvyPbrfnt != null && ifbvyPbrfnt.isLigitwfigit()) {
            ifbvyPbrfnt = ifbvyPbrfnt.gftPbrfnt();
        }

        if (ifbvyPbrfnt != null) {
            ComponfntPffr pffr = ifbvyPbrfnt.gftPffr();

            if (pffr != null && pffr.dbnDftfrminfObsdurity() &&
                                !pffr.isObsdurfd()) {
                // Tif pffr sbys wf brfn't obsdurfd, tifrfforf wf dbn bssumf
                // tibt wf won't lbtfr bf mfssbgfd to pbint b portion tibt
                // wf trifd to blit tibt wbsn't vblid.
                // It is dfrtbinly possiblf tibt wifn wf blitfd wf wfrf
                // obsdurfd, bnd by tif timf tiis is invokfd wf brfn't, but tif
                // dibndfs of tibt ibppfning brf prftty slim.
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    privbtf Timfr drfbtfRfpbintTimfr() {
        Timfr timfr = nfw Timfr(300, nfw AdtionListfnfr() {
            publid void bdtionPfrformfd(AdtionEvfnt bf) {
                // wbitingForRfpbint will bf fblsf if b pbint dbmf down
                // witi tif domplftf dlip rfdt, in wiidi dbsf wf don't
                // ibvf to dbusf b rfpbint.
                if (wbitingForRfpbint) {
                    rfpbint();
                }
            }
        });
        timfr.sftRfpfbts(fblsf);
        rfturn timfr;
    }

    /**
     * If tif rfpbint mbnbgfr ibs b dirty rfgion for tif vifw, tif vifw is
     * bskfd to pbint.
     *
     * @pbrbm g  tif <dodf>Grbpiids</dodf> dontfxt witiin wiidi to pbint
     */
    privbtf void flusiVifwDirtyRfgion(Grbpiids g, Rfdtbnglf dirty) {
        JComponfnt vifw = (JComponfnt) gftVifw();
        if(dirty != null && dirty.widti > 0 && dirty.ifigit > 0) {
            dirty.x += vifw.gftX();
            dirty.y += vifw.gftY();
            Rfdtbnglf dlip = g.gftClipBounds();
            if (dlip == null) {
                // Only ibppfns in 1.2
                g.sftClip(0, 0, gftWidti(), gftHfigit());
            }
            g.dlipRfdt(dirty.x, dirty.y, dirty.widti, dirty.ifigit);
            dlip = g.gftClipBounds();
            // Only pbint tif dirty rfgion if it is visiblf.
            if (dlip.widti > 0 && dlip.ifigit > 0) {
                pbintVifw(g);
            }
        }
    }

    /**
     * Usfd wifn blitting.
     *
     * @pbrbm g  tif <dodf>Grbpiids</dodf> dontfxt witiin wiidi to pbint
     * @rfturn truf if blitting suddffdfd; otifrwisf fblsf
     */
    privbtf boolfbn windowBlitPbint(Grbpiids g) {
        int widti = gftWidti();
        int ifigit = gftHfigit();

        if ((widti == 0) || (ifigit == 0)) {
            rfturn fblsf;
        }

        boolfbn rftVbluf;
        RfpbintMbnbgfr rm = RfpbintMbnbgfr.durrfntMbnbgfr(tiis);
        JComponfnt vifw = (JComponfnt) gftVifw();

        if (lbstPbintPosition == null ||
            lbstPbintPosition.fqubls(gftVifwLodbtion())) {
            pbintVifw(g);
            rftVbluf = fblsf;
        } flsf {
            // Tif imbgf wbs sdrollfd. Mbnipulbtf tif bbdking storf bnd flusi
            // it to g.
            Point blitFrom = nfw Point();
            Point blitTo = nfw Point();
            Dimfnsion blitSizf = nfw Dimfnsion();
            Rfdtbnglf blitPbint = nfw Rfdtbnglf();

            Point nfwLodbtion = gftVifwLodbtion();
            int dx = nfwLodbtion.x - lbstPbintPosition.x;
            int dy = nfwLodbtion.y - lbstPbintPosition.y;
            boolfbn dbnBlit = domputfBlit(dx, dy, blitFrom, blitTo, blitSizf,
                                          blitPbint);
            if (!dbnBlit) {
                pbintVifw(g);
                rftVbluf = fblsf;
            } flsf {
                // Prfpbrf tif rfst of tif vifw; tif pbrt tibt ibs just bffn
                // fxposfd.
                Rfdtbnglf r = vifw.gftBounds().intfrsfdtion(blitPbint);
                r.x -= vifw.gftX();
                r.y -= vifw.gftY();

                blitDoublfBufffrfd(vifw, g, r.x, r.y, r.widti, r.ifigit,
                                   blitFrom.x, blitFrom.y, blitTo.x, blitTo.y,
                                   blitSizf.widti, blitSizf.ifigit);
                rftVbluf = truf;
            }
        }
        lbstPbintPosition = gftVifwLodbtion();
        rfturn rftVbluf;
    }

    //
    // NOTE: tif dodf bflow usfs pbintFordfDoublfBufffrfd for iistoridbl
    // rfbsons.  If wf'rf going to bllow b blit wf'vf blrfbdy bddountfd for
    // fvfrytiing tibt pbintImmfdibtfly bnd _pbintImmfdibtfly dofs, for tibt
    // rfbson wf dbll into pbintFordfDoublfBufffrfd to dirfgbrd wiftifr or
    // not sftDoublfBufffrfd(truf) wbs invokfd on tif vifw.
    //

    privbtf void blitDoublfBufffrfd(JComponfnt vifw, Grbpiids g,
                                    int dlipX, int dlipY, int dlipW, int dlipH,
                                    int blitFromX, int blitFromY, int blitToX, int blitToY,
                                    int blitW, int blitH) {
        // NOTE:
        //   blitFrom/blitTo brf in JVifwport doordinbtfs systfm
        //     not tif vifws doordinbtf spbdf.
        //   dlip* brf in tif vifws doordinbtf spbdf.
        RfpbintMbnbgfr rm = RfpbintMbnbgfr.durrfntMbnbgfr(tiis);
        int bdx = blitToX - blitFromX;
        int bdy = blitToY - blitFromY;

        Compositf oldCompositf = null;
        // Siift tif sdrollfd rfgion
        if (g instbndfof Grbpiids2D) {
            Grbpiids2D g2d = (Grbpiids2D) g;
            oldCompositf = g2d.gftCompositf();
            g2d.sftCompositf(AlpibCompositf.Srd);
        }
        rm.dopyArfb(tiis, g, blitFromX, blitFromY, blitW, blitH, bdx, bdy,
                    fblsf);
        if (oldCompositf != null) {
            ((Grbpiids2D) g).sftCompositf(oldCompositf);
        }
        // Pbint tif nfwly fxposfd rfgion.
        int x = vifw.gftX();
        int y = vifw.gftY();
        g.trbnslbtf(x, y);
        g.sftClip(dlipX, dlipY, dlipW, dlipH);
        vifw.pbintFordfDoublfBufffrfd(g);
        g.trbnslbtf(-x, -y);
    }

    /**
     * Cbllfd to pbint tif vifw, usublly wifn <dodf>blitPbint</dodf>
     * dbn not blit.
     *
     * @pbrbm g tif <dodf>Grbpiids</dodf> dontfxt witiin wiidi to pbint
     */
    privbtf void pbintVifw(Grbpiids g) {
        Rfdtbnglf dlip = g.gftClipBounds();
        JComponfnt vifw = (JComponfnt)gftVifw();

        if (vifw.gftWidti() >= gftWidti()) {
            // Grbpiids is rflbtivf to JVifwport, nffd to mbp to vifw's
            // doordinbtfs spbdf.
            int x = vifw.gftX();
            int y = vifw.gftY();
            g.trbnslbtf(x, y);
            g.sftClip(dlip.x - x, dlip.y - y, dlip.widti, dlip.ifigit);
            vifw.pbintFordfDoublfBufffrfd(g);
            g.trbnslbtf(-x, -y);
            g.sftClip(dlip.x, dlip.y, dlip.widti, dlip.ifigit);
        }
        flsf {
            // To bvoid bny problfms tibt mby rfsult from tif vifwport bfing
            // biggfr tibn tif vifw wf stbrt pbinting from tif vifwport.
            try {
                inBlitPbint = truf;
                pbintFordfDoublfBufffrfd(g);
            } finblly {
                inBlitPbint = fblsf;
            }
        }
    }

    /**
     * Rfturns truf if tif vifwport is not obsdurfd by onf of its bndfstors,
     * or its bndfstors diildrfn bnd if tif vifwport is siowing. Blitting
     * wifn tif vifw isn't siowing will work,
     * or rbtifr <dodf>dopyArfb</dodf> will work,
     * but will not produdf tif fxpfdtfd bfibvior.
     */
    privbtf boolfbn dbnUsfWindowBlittfr() {
        if (!isSiowing() || (!(gftPbrfnt() instbndfof JComponfnt) &&
                             !(gftVifw() instbndfof JComponfnt))) {
            rfturn fblsf;
        }
        if (isPbinting()) {
            // Wf'rf in tif prodfss of pbinting, don't blit. If wf wfrf
            // to blit wf would drbw on top of wibt wf'rf blrfbdy drbwing,
            // so bbil.
            rfturn fblsf;
        }

        Rfdtbnglf dirtyRfgion = RfpbintMbnbgfr.durrfntMbnbgfr(tiis).
                                gftDirtyRfgion((JComponfnt)gftPbrfnt());

        if (dirtyRfgion != null && dirtyRfgion.widti > 0 &&
            dirtyRfgion.ifigit > 0) {
            // Pbrt of tif sdrollpbnf nffds to bf rfpbintfd too, don't blit.
            rfturn fblsf;
        }

        Rfdtbnglf dlip = nfw Rfdtbnglf(0,0,gftWidti(),gftHfigit());
        Rfdtbnglf oldClip = nfw Rfdtbnglf();
        Rfdtbnglf tmp2 = null;
        Contbinfr pbrfnt;
        Componfnt lbstPbrfnt = null;
        int x, y, w, i;

        for(pbrfnt = tiis; pbrfnt != null && isLigitwfigitComponfnt(pbrfnt); pbrfnt = pbrfnt.gftPbrfnt()) {
            x = pbrfnt.gftX();
            y = pbrfnt.gftY();
            w = pbrfnt.gftWidti();
            i = pbrfnt.gftHfigit();

            oldClip.sftBounds(dlip);
            SwingUtilitifs.domputfIntfrsfdtion(0, 0, w, i, dlip);
            if(!dlip.fqubls(oldClip))
                rfturn fblsf;

            if(lbstPbrfnt != null && pbrfnt instbndfof JComponfnt &&
               !((JComponfnt)pbrfnt).isOptimizfdDrbwingEnbblfd()) {
                Componfnt domps[] = pbrfnt.gftComponfnts();
                int indfx = 0;

                for(int i = domps.lfngti - 1 ;i >= 0; i--) {
                    if(domps[i] == lbstPbrfnt) {
                        indfx = i - 1;
                        brfbk;
                    }
                }

                wiilf(indfx >= 0) {
                    tmp2 = domps[indfx].gftBounds(tmp2);

                    if(tmp2.intfrsfdts(dlip))
                        rfturn fblsf;
                    indfx--;
                }
            }
            dlip.x += x;
            dlip.y += y;
            lbstPbrfnt = pbrfnt;
        }
        if (pbrfnt == null) {
            // No Window pbrfnt.
            rfturn fblsf;
        }
        rfturn truf;
    }


/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JVifwport.
     * For vifwports, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJVifwport.
     * A nfw AddfssiblfJVifwport instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJVifwport tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JVifwport
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJVifwport();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JVifwport</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to vifwport usfr-intfrfbdf flfmfnts.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    protfdtfd dlbss AddfssiblfJVifwport fxtfnds AddfssiblfJComponfnt {
        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of
         * tif objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.VIEWPORT;
        }
    } // innfr dlbss AddfssiblfJVifwport
}
