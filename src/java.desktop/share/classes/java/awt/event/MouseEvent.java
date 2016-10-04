/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.fvfnt;

import jbvb.bwt.Componfnt;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Point;
import jbvb.bwt.Toolkit;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.bwt.IllfgblComponfntStbtfExdfption;
import jbvb.bwt.MousfInfo;
import sun.bwt.SunToolkit;

/**
 * An fvfnt wiidi indidbtfs tibt b mousf bdtion oddurrfd in b domponfnt.
 * A mousf bdtion is donsidfrfd to oddur in b pbrtidulbr domponfnt if bnd only
 * if tif mousf dursor is ovfr tif unobsdurfd pbrt of tif domponfnt's bounds
 * wifn tif bdtion ibppfns.
 * For ligitwfigit domponfnts, sudi bs Swing's domponfnts, mousf fvfnts
 * brf only dispbtdifd to tif domponfnt if tif mousf fvfnt typf ibs bffn
 * fnbblfd on tif domponfnt. A mousf fvfnt typf is fnbblfd by bdding tif
 * bppropribtf mousf-bbsfd {@dodf EvfntListfnfr} to tif domponfnt
 * ({@link MousfListfnfr} or {@link MousfMotionListfnfr}), or by invoking
 * {@link Componfnt#fnbblfEvfnts(long)} witi tif bppropribtf mbsk pbrbmftfr
 * ({@dodf AWTEvfnt.MOUSE_EVENT_MASK} or {@dodf AWTEvfnt.MOUSE_MOTION_EVENT_MASK}).
 * If tif mousf fvfnt typf ibs not bffn fnbblfd on tif domponfnt, tif
 * dorrfsponding mousf fvfnts brf dispbtdifd to tif first bndfstor tibt
 * ibs fnbblfd tif mousf fvfnt typf.
 *<p>
 * For fxbmplf, if b {@dodf MousfListfnfr} ibs bffn bddfd to b domponfnt, or
 * {@dodf fnbblfEvfnts(AWTEvfnt.MOUSE_EVENT_MASK)} ibs bffn invokfd, tifn bll
 * tif fvfnts dffinfd by {@dodf MousfListfnfr} brf dispbtdifd to tif domponfnt.
 * On tif otifr ibnd, if b {@dodf MousfMotionListfnfr} ibs not bffn bddfd bnd
 * {@dodf fnbblfEvfnts} ibs not bffn invokfd witi
 * {@dodf AWTEvfnt.MOUSE_MOTION_EVENT_MASK}, tifn mousf motion fvfnts brf not
 * dispbtdifd to tif domponfnt. Instfbd tif mousf motion fvfnts brf
 * dispbtdifd to tif first bndfstors tibt ibs fnbblfd mousf motion
 * fvfnts.
 * <P>
 * Tiis low-lfvfl fvfnt is gfnfrbtfd by b domponfnt objfdt for:
 * <ul>
 * <li>Mousf Evfnts
 *     <ul>
 *     <li>b mousf button is prfssfd
 *     <li>b mousf button is rflfbsfd
 *     <li>b mousf button is dlidkfd (prfssfd bnd rflfbsfd)
 *     <li>tif mousf dursor fntfrs tif unobsdurfd pbrt of domponfnt's gfomftry
 *     <li>tif mousf dursor fxits tif unobsdurfd pbrt of domponfnt's gfomftry
 *     </ul>
 * <li> Mousf Motion Evfnts
 *     <ul>
 *     <li>tif mousf is movfd
 *     <li>tif mousf is drbggfd
 *     </ul>
 * </ul>
 * <P>
 * A <dodf>MousfEvfnt</dodf> objfdt is pbssfd to fvfry
 * <dodf>MousfListfnfr</dodf>
 * or <dodf>MousfAdbptfr</dodf> objfdt wiidi is rfgistfrfd to rfdfivf
 * tif "intfrfsting" mousf fvfnts using tif domponfnt's
 * <dodf>bddMousfListfnfr</dodf> mftiod.
 * (<dodf>MousfAdbptfr</dodf> objfdts implfmfnt tif
 * <dodf>MousfListfnfr</dodf> intfrfbdf.) Ebdi sudi listfnfr objfdt
 * gfts b <dodf>MousfEvfnt</dodf> dontbining tif mousf fvfnt.
 * <P>
 * A <dodf>MousfEvfnt</dodf> objfdt is blso pbssfd to fvfry
 * <dodf>MousfMotionListfnfr</dodf> or
 * <dodf>MousfMotionAdbptfr</dodf> objfdt wiidi is rfgistfrfd to rfdfivf
 * mousf motion fvfnts using tif domponfnt's
 * <dodf>bddMousfMotionListfnfr</dodf>
 * mftiod. (<dodf>MousfMotionAdbptfr</dodf> objfdts implfmfnt tif
 * <dodf>MousfMotionListfnfr</dodf> intfrfbdf.) Ebdi sudi listfnfr objfdt
 * gfts b <dodf>MousfEvfnt</dodf> dontbining tif mousf motion fvfnt.
 * <P>
 * Wifn b mousf button is dlidkfd, fvfnts brf gfnfrbtfd bnd sfnt to tif
 * rfgistfrfd <dodf>MousfListfnfr</dodf>s.
 * Tif stbtf of modbl kfys dbn bf rftrifvfd using {@link InputEvfnt#gftModififrs}
 * bnd {@link InputEvfnt#gftModififrsEx}.
 * Tif button mbsk rfturnfd by {@link InputEvfnt#gftModififrs} rfflfdts
 * only tif button tibt dibngfd stbtf, not tif durrfnt stbtf of bll buttons.
 * (Notf: Duf to ovfrlbp in tif vblufs of ALT_MASK/BUTTON2_MASK bnd
 * META_MASK/BUTTON3_MASK, tiis is not blwbys truf for mousf fvfnts involving
 * modififr kfys).
 * To gft tif stbtf of bll buttons bnd modififr kfys, usf
 * {@link InputEvfnt#gftModififrsEx}.
 * Tif button wiidi ibs dibngfd stbtf is rfturnfd by {@link MousfEvfnt#gftButton}
 * <P>
 * For fxbmplf, if tif first mousf button is prfssfd, fvfnts brf sfnt in tif
 * following ordfr:
 * <PRE>
 *    <b   >id           </b   >   <b   >modififrs   </b   > <b   >button </b   >
 *    <dodf>MOUSE_PRESSED</dodf>:  <dodf>BUTTON1_MASK</dodf> <dodf>BUTTON1</dodf>
 *    <dodf>MOUSE_RELEASED</dodf>: <dodf>BUTTON1_MASK</dodf> <dodf>BUTTON1</dodf>
 *    <dodf>MOUSE_CLICKED</dodf>:  <dodf>BUTTON1_MASK</dodf> <dodf>BUTTON1</dodf>
 * </PRE>
 * Wifn multiplf mousf buttons brf prfssfd, fbdi prfss, rflfbsf, bnd dlidk
 * rfsults in b sfpbrbtf fvfnt.
 * <P>
 * For fxbmplf, if tif usfr prfssfs <b>button 1</b> followfd by
 * <b>button 2</b>, bnd tifn rflfbsfs tifm in tif sbmf ordfr,
 * tif following sfqufndf of fvfnts is gfnfrbtfd:
 * <PRE>
 *    <b   >id           </b   >   <b   >modififrs   </b   > <b   >button </b   >
 *    <dodf>MOUSE_PRESSED</dodf>:  <dodf>BUTTON1_MASK</dodf> <dodf>BUTTON1</dodf>
 *    <dodf>MOUSE_PRESSED</dodf>:  <dodf>BUTTON2_MASK</dodf> <dodf>BUTTON2</dodf>
 *    <dodf>MOUSE_RELEASED</dodf>: <dodf>BUTTON1_MASK</dodf> <dodf>BUTTON1</dodf>
 *    <dodf>MOUSE_CLICKED</dodf>:  <dodf>BUTTON1_MASK</dodf> <dodf>BUTTON1</dodf>
 *    <dodf>MOUSE_RELEASED</dodf>: <dodf>BUTTON2_MASK</dodf> <dodf>BUTTON2</dodf>
 *    <dodf>MOUSE_CLICKED</dodf>:  <dodf>BUTTON2_MASK</dodf> <dodf>BUTTON2</dodf>
 * </PRE>
 * If <b>button 2</b> is rflfbsfd first, tif
 * <dodf>MOUSE_RELEASED</dodf>/<dodf>MOUSE_CLICKED</dodf> pbir
 * for <dodf>BUTTON2_MASK</dodf> brrivfs first,
 * followfd by tif pbir for <dodf>BUTTON1_MASK</dodf>.
 * <p>
 * Somf fxtrb mousf buttons brf bddfd to fxtfnd tif stbndbrd sft of buttons
 * rfprfsfntfd by tif following donstbnts:{@dodf BUTTON1}, {@dodf BUTTON2}, bnd {@dodf BUTTON3}.
 * Extrb buttons ibvf no bssignfd {@dodf BUTTONx}
 * donstbnts bs wfll bs tifir button mbsks ibvf no bssignfd {@dodf BUTTONx_DOWN_MASK}
 * donstbnts. Nfvfrtiflfss, ordinbl numbfrs stbrting from 4 mby bf
 * usfd bs button numbfrs (button ids). Vblufs obtbinfd by tif
 * {@link InputEvfnt#gftMbskForButton(int) gftMbskForButton(button)} mftiod mby bf usfd
 * bs button mbsks.
 * <p>
 * {@dodf MOUSE_DRAGGED} fvfnts brf dflivfrfd to tif {@dodf Componfnt}
 * in wiidi tif mousf button wbs prfssfd until tif mousf button is rflfbsfd
 * (rfgbrdlfss of wiftifr tif mousf position is witiin tif bounds of tif
 * {@dodf Componfnt}).  Duf to plbtform-dfpfndfnt Drbg&bmp;Drop implfmfntbtions,
 * {@dodf MOUSE_DRAGGED} fvfnts mby not bf dflivfrfd during b nbtivf
 * Drbg&bmp;Drop opfrbtion.
 *
 * In b multi-sdrffn fnvironmfnt mousf drbg fvfnts brf dflivfrfd to tif
 * <dodf>Componfnt</dodf> fvfn if tif mousf position is outsidf tif bounds of tif
 * <dodf>GrbpiidsConfigurbtion</dodf> bssodibtfd witi tibt
 * <dodf>Componfnt</dodf>. Howfvfr, tif rfportfd position for mousf drbg fvfnts
 * in tiis dbsf mby difffr from tif bdtubl mousf position:
 * <ul>
 * <li>In b multi-sdrffn fnvironmfnt witiout b virtubl dfvidf:
 * <br>
 * Tif rfportfd doordinbtfs for mousf drbg fvfnts brf dlippfd to fit witiin tif
 * bounds of tif <dodf>GrbpiidsConfigurbtion</dodf> bssodibtfd witi
 * tif <dodf>Componfnt</dodf>.
 * <li>In b multi-sdrffn fnvironmfnt witi b virtubl dfvidf:
 * <br>
 * Tif rfportfd doordinbtfs for mousf drbg fvfnts brf dlippfd to fit witiin tif
 * bounds of tif virtubl dfvidf bssodibtfd witi tif <dodf>Componfnt</dodf>.
 * </ul>
 * <p>
 * An unspfdififd bfibvior will bf dbusfd if tif {@dodf id} pbrbmftfr
 * of bny pbrtidulbr {@dodf MousfEvfnt} instbndf is not
 * in tif rbngf from {@dodf MOUSE_FIRST} to {@dodf MOUSE_LAST}-1
 * ({@dodf MOUSE_WHEEL} is not bddfptbblf).
 *
 * @butior Cbrl Quinn
 *
 * @sff MousfAdbptfr
 * @sff MousfListfnfr
 * @sff MousfMotionAdbptfr
 * @sff MousfMotionListfnfr
 * @sff MousfWifflListfnfr
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/mousflistfnfr.itml">Tutoribl: Writing b Mousf Listfnfr</b>
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/mousfmotionlistfnfr.itml">Tutoribl: Writing b Mousf Motion Listfnfr</b>
 *
 * @sindf 1.1
 */
publid dlbss MousfEvfnt fxtfnds InputEvfnt {

    /**
     * Tif first numbfr in tif rbngf of ids usfd for mousf fvfnts.
     */
    publid stbtid finbl int MOUSE_FIRST         = 500;

    /**
     * Tif lbst numbfr in tif rbngf of ids usfd for mousf fvfnts.
     */
    publid stbtid finbl int MOUSE_LAST          = 507;

    /**
     * Tif "mousf dlidkfd" fvfnt. Tiis <dodf>MousfEvfnt</dodf>
     * oddurs wifn b mousf button is prfssfd bnd rflfbsfd.
     */
    publid stbtid finbl int MOUSE_CLICKED = MOUSE_FIRST;

    /**
     * Tif "mousf prfssfd" fvfnt. Tiis <dodf>MousfEvfnt</dodf>
     * oddurs wifn b mousf button is pusifd down.
     */
    publid stbtid finbl int MOUSE_PRESSED = 1 + MOUSE_FIRST; //Evfnt.MOUSE_DOWN

    /**
     * Tif "mousf rflfbsfd" fvfnt. Tiis <dodf>MousfEvfnt</dodf>
     * oddurs wifn b mousf button is lft up.
     */
    publid stbtid finbl int MOUSE_RELEASED = 2 + MOUSE_FIRST; //Evfnt.MOUSE_UP

    /**
     * Tif "mousf movfd" fvfnt. Tiis <dodf>MousfEvfnt</dodf>
     * oddurs wifn tif mousf position dibngfs.
     */
    publid stbtid finbl int MOUSE_MOVED = 3 + MOUSE_FIRST; //Evfnt.MOUSE_MOVE

    /**
     * Tif "mousf fntfrfd" fvfnt. Tiis <dodf>MousfEvfnt</dodf>
     * oddurs wifn tif mousf dursor fntfrs tif unobsdurfd pbrt of domponfnt's
     * gfomftry.
     */
    publid stbtid finbl int MOUSE_ENTERED = 4 + MOUSE_FIRST; //Evfnt.MOUSE_ENTER

    /**
     * Tif "mousf fxitfd" fvfnt. Tiis <dodf>MousfEvfnt</dodf>
     * oddurs wifn tif mousf dursor fxits tif unobsdurfd pbrt of domponfnt's
     * gfomftry.
     */
    publid stbtid finbl int MOUSE_EXITED = 5 + MOUSE_FIRST; //Evfnt.MOUSE_EXIT

    /**
     * Tif "mousf drbggfd" fvfnt. Tiis <dodf>MousfEvfnt</dodf>
     * oddurs wifn tif mousf position dibngfs wiilf b mousf button is prfssfd.
     */
    publid stbtid finbl int MOUSE_DRAGGED = 6 + MOUSE_FIRST; //Evfnt.MOUSE_DRAG

    /**
     * Tif "mousf wiffl" fvfnt.  Tiis is tif only <dodf>MousfWifflEvfnt</dodf>.
     * It oddurs wifn b mousf fquippfd witi b wiffl ibs its wiffl rotbtfd.
     * @sindf 1.4
     */
    publid stbtid finbl int MOUSE_WHEEL = 7 + MOUSE_FIRST;

    /**
     * Indidbtfs no mousf buttons; usfd by {@link #gftButton}.
     * @sindf 1.4
     */
    publid stbtid finbl int NOBUTTON = 0;

    /**
     * Indidbtfs mousf button #1; usfd by {@link #gftButton}.
     * @sindf 1.4
     */
    publid stbtid finbl int BUTTON1 = 1;

    /**
     * Indidbtfs mousf button #2; usfd by {@link #gftButton}.
     * @sindf 1.4
     */
    publid stbtid finbl int BUTTON2 = 2;

    /**
     * Indidbtfs mousf button #3; usfd by {@link #gftButton}.
     * @sindf 1.4
     */
    publid stbtid finbl int BUTTON3 = 3;

    /**
     * Tif mousf fvfnt's x doordinbtf.
     * Tif x vbluf is rflbtivf to tif domponfnt tibt firfd tif fvfnt.
     *
     * @sfribl
     * @sff #gftX()
     */
    int x;

    /**
     * Tif mousf fvfnt's y doordinbtf.
     * Tif y vbluf is rflbtivf to tif domponfnt tibt firfd tif fvfnt.
     *
     * @sfribl
     * @sff #gftY()
     */
    int y;

    /**
     * Tif mousf fvfnt's x bbsolutf doordinbtf.
     * In b virtubl dfvidf multi-sdrffn fnvironmfnt in wiidi tif
     * dfsktop brfb dould spbn multiplf piysidbl sdrffn dfvidfs,
     * tiis doordinbtf is rflbtivf to tif virtubl doordinbtf systfm.
     * Otifrwisf, tiis doordinbtf is rflbtivf to tif doordinbtf systfm
     * bssodibtfd witi tif Componfnt's GrbpiidsConfigurbtion.
     *
     * @sfribl
   */
    privbtf int xAbs;

    /**
     * Tif mousf fvfnt's y bbsolutf doordinbtf.
     * In b virtubl dfvidf multi-sdrffn fnvironmfnt in wiidi tif
     * dfsktop brfb dould spbn multiplf piysidbl sdrffn dfvidfs,
     * tiis doordinbtf is rflbtivf to tif virtubl doordinbtf systfm.
     * Otifrwisf, tiis doordinbtf is rflbtivf to tif doordinbtf systfm
     * bssodibtfd witi tif Componfnt's GrbpiidsConfigurbtion.
     *
     * @sfribl
     */
    privbtf int yAbs;

    /**
     * Indidbtfs tif numbfr of quidk donsfdutivf dlidks of
     * b mousf button.
     * dlidkCount will bf vblid for only tirff mousf fvfnts :<BR>
     * <dodf>MOUSE_CLICKED</dodf>,
     * <dodf>MOUSE_PRESSED</dodf> bnd
     * <dodf>MOUSE_RELEASED</dodf>.
     * For tif bbovf, tif <dodf>dlidkCount</dodf> will bf bt lfbst 1.
     * For bll otifr fvfnts tif dount will bf 0.
     *
     * @sfribl
     * @sff #gftClidkCount()
     */
    int dlidkCount;

    /**
     * Indidbtfs wiidi, if bny, of tif mousf buttons ibs dibngfd stbtf.
     *
     * Tif vblid vblufs brf rbngfd from 0 to tif vbluf rfturnfd by tif
     * {@link jbvb.bwt.MousfInfo#gftNumbfrOfButtons() MousfInfo.gftNumbfrOfButtons()} mftiod.
     * Tiis rbngf blrfbdy indludfs donstbnts {@dodf NOBUTTON}, {@dodf BUTTON1},
     * {@dodf BUTTON2}, bnd {@dodf BUTTON3}
     * if tifsf buttons brf prfsfnt. So it is bllowfd to usf tifsf donstbnts too.
     * For fxbmplf, for b mousf witi two buttons tiis fifld mby dontbin tif following vblufs:
     * <ul>
     * <li> 0 ({@dodf NOBUTTON})
     * <li> 1 ({@dodf BUTTON1})
     * <li> 2 ({@dodf BUTTON2})
     * </ul>
     * If b mousf ibs 5 buttons, tiis fifld mby dontbin tif following vblufs:
     * <ul>
     * <li> 0 ({@dodf NOBUTTON})
     * <li> 1 ({@dodf BUTTON1})
     * <li> 2 ({@dodf BUTTON2})
     * <li> 3 ({@dodf BUTTON3})
     * <li> 4
     * <li> 5
     * </ul>
     * If support for fxtfndfd mousf buttons is {@link Toolkit#brfExtrbMousfButtonsEnbblfd()} disbblfd by Jbvb
     * tifn tif fifld mby not dontbin tif vbluf lbrgfr tibn {@dodf BUTTON3}.
     * @sfribl
     * @sff #gftButton()
     * @sff jbvb.bwt.Toolkit#brfExtrbMousfButtonsEnbblfd()
     */
    int button;

    /**
     * A propfrty usfd to indidbtf wiftifr b Popup Mfnu
     * siould bppfbr  witi b dfrtbin gfsturfs.
     * If <dodf>popupTriggfr</dodf> = <dodf>fblsf</dodf>,
     * no popup mfnu siould bppfbr.  If it is <dodf>truf</dodf>
     * tifn b popup mfnu siould bppfbr.
     *
     * @sfribl
     * @sff jbvb.bwt.PopupMfnu
     * @sff #isPopupTriggfr()
     */
    boolfbn popupTriggfr = fblsf;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -991214153494842848L;

    /**
     * A numbfr of buttons bvbilbblf on tif mousf bt tif {@dodf Toolkit} mbdiinfry stbrtup.
     */
    privbtf stbtid int dbdifdNumbfrOfButtons;

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        NbtivfLibLobdfr.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
        finbl Toolkit tk = Toolkit.gftDffbultToolkit();
        if (tk instbndfof SunToolkit) {
            dbdifdNumbfrOfButtons = ((SunToolkit)tk).gftNumbfrOfButtons();
        } flsf {
            //It's fxpfdtfd tibt somf toolkits (Hfbdlfss,
            //wibtfvfr bfsidfs SunToolkit) dould blso opfrbtf.
            dbdifdNumbfrOfButtons = 3;
        }
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs for fiflds tibt mby bf
     *  bddfssfd from C.
     */
    privbtf stbtid nbtivf void initIDs();

    /**
     * Rfturns tif bbsolutf x, y position of tif fvfnt.
     * In b virtubl dfvidf multi-sdrffn fnvironmfnt in wiidi tif
     * dfsktop brfb dould spbn multiplf piysidbl sdrffn dfvidfs,
     * tifsf doordinbtfs brf rflbtivf to tif virtubl doordinbtf systfm.
     * Otifrwisf, tifsf doordinbtfs brf rflbtivf to tif doordinbtf systfm
     * bssodibtfd witi tif Componfnt's GrbpiidsConfigurbtion.
     *
     * @rfturn b <dodf>Point</dodf> objfdt dontbining tif bbsolutf  x
     *  bnd y doordinbtfs.
     *
     * @sff jbvb.bwt.GrbpiidsConfigurbtion
     * @sindf 1.6
     */
    publid Point gftLodbtionOnSdrffn(){
      rfturn nfw Point(xAbs, yAbs);
    }

    /**
     * Rfturns tif bbsolutf iorizontbl x position of tif fvfnt.
     * In b virtubl dfvidf multi-sdrffn fnvironmfnt in wiidi tif
     * dfsktop brfb dould spbn multiplf piysidbl sdrffn dfvidfs,
     * tiis doordinbtf is rflbtivf to tif virtubl doordinbtf systfm.
     * Otifrwisf, tiis doordinbtf is rflbtivf to tif doordinbtf systfm
     * bssodibtfd witi tif Componfnt's GrbpiidsConfigurbtion.
     *
     * @rfturn x  bn intfgfr indidbting bbsolutf iorizontbl position.
     *
     * @sff jbvb.bwt.GrbpiidsConfigurbtion
     * @sindf 1.6
     */
    publid int gftXOnSdrffn() {
        rfturn xAbs;
    }

    /**
     * Rfturns tif bbsolutf vfrtidbl y position of tif fvfnt.
     * In b virtubl dfvidf multi-sdrffn fnvironmfnt in wiidi tif
     * dfsktop brfb dould spbn multiplf piysidbl sdrffn dfvidfs,
     * tiis doordinbtf is rflbtivf to tif virtubl doordinbtf systfm.
     * Otifrwisf, tiis doordinbtf is rflbtivf to tif doordinbtf systfm
     * bssodibtfd witi tif Componfnt's GrbpiidsConfigurbtion.
     *
     * @rfturn y  bn intfgfr indidbting bbsolutf vfrtidbl position.
     *
     * @sff jbvb.bwt.GrbpiidsConfigurbtion
     * @sindf 1.6
     */
    publid int gftYOnSdrffn() {
        rfturn yAbs;
    }

    /**
     * Construdts b <dodf>MousfEvfnt</dodf> objfdt witi tif
     * spfdififd sourdf domponfnt,
     * typf, timf, modififrs, doordinbtfs, dlidk dount, popupTriggfr flbg,
     * bnd button numbfr.
     * <p>
     * Crfbting bn invblid fvfnt (sudi
     * bs by using morf tibn onf of tif old _MASKs, or modififr/button
     * vblufs wiidi don't mbtdi) rfsults in unspfdififd bfibvior.
     * An invodbtion of tif form
     * <tt>MousfEvfnt(sourdf, id, wifn, modififrs, x, y, dlidkCount, popupTriggfr, button)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     * <tt> {@link #MousfEvfnt(Componfnt, int, long, int, int, int,
     * int, int, int, boolfbn, int) MousfEvfnt}(sourdf, id, wifn, modififrs,
     * x, y, xAbs, yAbs, dlidkCount, popupTriggfr, button)</tt>
     * wifrf xAbs bnd yAbs dffinfs bs sourdf's lodbtion on sdrffn plus
     * rflbtivf doordinbtfs x bnd y.
     * xAbs bnd yAbs brf sft to zfro if tif sourdf is not siowing.
     * Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf       Tif <dodf>Componfnt</dodf> tibt originbtfd tif fvfnt
     * @pbrbm id              An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link MousfEvfnt}
     * @pbrbm wifn         A long intfgfr tibt givfs tif timf tif fvfnt oddurrfd.
     *                     Pbssing nfgbtivf or zfro vbluf
     *                     is not rfdommfndfd
     * @pbrbm modififrs    b modififr mbsk dfsdribing tif modififr kfys bnd mousf
     *                     buttons (for fxbmplf, siift, dtrl, blt, bnd mftb) tibt
     *                     brf down during tif fvfnt.
     *                     Only fxtfndfd modififrs brf bllowfd to bf usfd bs b
     *                     vbluf for tiis pbrbmftfr (sff tif {@link InputEvfnt#gftModififrsEx}
     *                     dlbss for tif dfsdription of fxtfndfd modififrs).
     *                     Pbssing nfgbtivf pbrbmftfr
     *                     is not rfdommfndfd.
     *                     Zfro vbluf mfbns tibt no modififrs wfrf pbssfd
     * @pbrbm x            Tif iorizontbl x doordinbtf for tif mousf lodbtion.
     *                       It is bllowfd to pbss nfgbtivf vblufs
     * @pbrbm y            Tif vfrtidbl y doordinbtf for tif mousf lodbtion.
     *                       It is bllowfd to pbss nfgbtivf vblufs
     * @pbrbm dlidkCount   Tif numbfr of mousf dlidks bssodibtfd witi fvfnt.
     *                       Pbssing nfgbtivf vbluf
     *                       is not rfdommfndfd
     * @pbrbm popupTriggfr A boolfbn tibt fqubls {@dodf truf} if tiis fvfnt
     *                     is b triggfr for b popup mfnu
     * @pbrbm button       An intfgfr tibt indidbtfs, wiidi of tif mousf buttons ibs
     *                     dibngfd its stbtf.
     * Tif following rulfs brf bpplifd to tiis pbrbmftfr:
     * <ul>
     * <li>If support for tif fxtfndfd mousf buttons is
     * {@link Toolkit#brfExtrbMousfButtonsEnbblfd() disbblfd} by Jbvb
     * tifn it is bllowfd to drfbtf {@dodf MousfEvfnt} objfdts only witi tif stbndbrd buttons:
     * {@dodf NOBUTTON}, {@dodf BUTTON1}, {@dodf BUTTON2}, bnd
     * {@dodf BUTTON3}.
     * <li> If support for tif fxtfndfd mousf buttons is
     * {@link Toolkit#brfExtrbMousfButtonsEnbblfd() fnbblfd} by Jbvb
     * tifn it is bllowfd to drfbtf {@dodf MousfEvfnt} objfdts witi
     * tif stbndbrd buttons.
     * In dbsf tif support for fxtfndfd mousf buttons is
     * {@link Toolkit#brfExtrbMousfButtonsEnbblfd() fnbblfd} by Jbvb, tifn
     * in bddition to tif stbndbrd buttons, {@dodf MousfEvfnt} objfdts dbn bf drfbtfd
     * using buttons from tif rbngf stbrting from 4 to
     * {@link jbvb.bwt.MousfInfo#gftNumbfrOfButtons() MousfInfo.gftNumbfrOfButtons()}
     * if tif mousf ibs morf tibn tirff buttons.
     * </ul>
     * @tirows IllfgblArgumfntExdfption if {@dodf button} is lfss tifn zfro
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @tirows IllfgblArgumfntExdfption if {@dodf button} is grfbtfr tifn BUTTON3 bnd tif support for fxtfndfd mousf buttons is
     *                                  {@link Toolkit#brfExtrbMousfButtonsEnbblfd() disbblfd} by Jbvb
     * @tirows IllfgblArgumfntExdfption if {@dodf button} is grfbtfr tifn tif
     *                                  {@link jbvb.bwt.MousfInfo#gftNumbfrOfButtons() durrfnt numbfr of buttons} bnd tif support
     *                                  for fxtfndfd mousf buttons is {@link Toolkit#brfExtrbMousfButtonsEnbblfd() fnbblfd}
     *                                  by Jbvb
     * @tirows IllfgblArgumfntExdfption if bn invblid <dodf>button</dodf>
     *            vbluf is pbssfd in
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftSourdf()
     * @sff #gftID()
     * @sff #gftWifn()
     * @sff #gftModififrs()
     * @sff #gftX()
     * @sff #gftY()
     * @sff #gftClidkCount()
     * @sff #isPopupTriggfr()
     * @sff #gftButton()
     * @sindf 1.4
     */
    publid MousfEvfnt(Componfnt sourdf, int id, long wifn, int modififrs,
                      int x, int y, int dlidkCount, boolfbn popupTriggfr,
                      int button)
    {
        tiis(sourdf, id, wifn, modififrs, x, y, 0, 0, dlidkCount, popupTriggfr, button);
        Point fvfntLodbtionOnSdrffn = nfw Point(0, 0);
        try {
          fvfntLodbtionOnSdrffn = sourdf.gftLodbtionOnSdrffn();
          tiis.xAbs = fvfntLodbtionOnSdrffn.x + x;
          tiis.yAbs = fvfntLodbtionOnSdrffn.y + y;
        } dbtdi (IllfgblComponfntStbtfExdfption f){
          tiis.xAbs = 0;
          tiis.yAbs = 0;
        }
    }

    /**
     * Construdts b <dodf>MousfEvfnt</dodf> objfdt witi tif
     * spfdififd sourdf domponfnt,
     * typf, modififrs, doordinbtfs, dlidk dount, bnd popupTriggfr flbg.
     * An invodbtion of tif form
     * <tt>MousfEvfnt(sourdf, id, wifn, modififrs, x, y, dlidkCount, popupTriggfr)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     * <tt> {@link #MousfEvfnt(Componfnt, int, long, int, int, int,
     * int, int, int, boolfbn, int) MousfEvfnt}(sourdf, id, wifn, modififrs,
     * x, y, xAbs, yAbs, dlidkCount, popupTriggfr, MousfEvfnt.NOBUTTON)</tt>
     * wifrf xAbs bnd yAbs dffinfs bs sourdf's lodbtion on sdrffn plus
     * rflbtivf doordinbtfs x bnd y.
     * xAbs bnd yAbs brf sft to zfro if tif sourdf is not siowing.
     * Tiis mftiod tirows bn <dodf>IllfgblArgumfntExdfption</dodf>
     * if <dodf>sourdf</dodf> is <dodf>null</dodf>.
     *
     * @pbrbm sourdf       Tif <dodf>Componfnt</dodf> tibt originbtfd tif fvfnt
     * @pbrbm id              An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link MousfEvfnt}
     * @pbrbm wifn         A long intfgfr tibt givfs tif timf tif fvfnt oddurrfd.
     *                     Pbssing nfgbtivf or zfro vbluf
     *                     is not rfdommfndfd
     * @pbrbm modififrs    b modififr mbsk dfsdribing tif modififr kfys bnd mousf
     *                     buttons (for fxbmplf, siift, dtrl, blt, bnd mftb) tibt
     *                     brf down during tif fvfnt.
     *                     Only fxtfndfd modififrs brf bllowfd to bf usfd bs b
     *                     vbluf for tiis pbrbmftfr (sff tif {@link InputEvfnt#gftModififrsEx}
     *                     dlbss for tif dfsdription of fxtfndfd modififrs).
     *                     Pbssing nfgbtivf pbrbmftfr
     *                     is not rfdommfndfd.
     *                     Zfro vbluf mfbns tibt no modififrs wfrf pbssfd
     * @pbrbm x            Tif iorizontbl x doordinbtf for tif mousf lodbtion.
     *                       It is bllowfd to pbss nfgbtivf vblufs
     * @pbrbm y            Tif vfrtidbl y doordinbtf for tif mousf lodbtion.
     *                       It is bllowfd to pbss nfgbtivf vblufs
     * @pbrbm dlidkCount   Tif numbfr of mousf dlidks bssodibtfd witi fvfnt.
     *                       Pbssing nfgbtivf vbluf
     *                       is not rfdommfndfd
     * @pbrbm popupTriggfr A boolfbn tibt fqubls {@dodf truf} if tiis fvfnt
     *                     is b triggfr for b popup mfnu
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftSourdf()
     * @sff #gftID()
     * @sff #gftWifn()
     * @sff #gftModififrs()
     * @sff #gftX()
     * @sff #gftY()
     * @sff #gftClidkCount()
     * @sff #isPopupTriggfr()
     */
     publid MousfEvfnt(Componfnt sourdf, int id, long wifn, int modififrs,
                      int x, int y, int dlidkCount, boolfbn popupTriggfr) {
        tiis(sourdf, id, wifn, modififrs, x, y, dlidkCount, popupTriggfr, NOBUTTON);
     }


    /* if tif button is bn fxtrb button bnd it is rflfbsfd or dlidkfd tifn in Xsystfm its stbtf
       is not modififd. Exdludf tiis button numbfr from ExtModififrs mbsk.*/
    trbnsifnt privbtf boolfbn siouldExdludfButtonFromExtModififrs = fblsf;

    /**
     * {@inifritDod}
     */
    publid int gftModififrsEx() {
        int tmpModififrs = modififrs;
        if (siouldExdludfButtonFromExtModififrs) {
            tmpModififrs &= ~(InputEvfnt.gftMbskForButton(gftButton()));
        }
        rfturn tmpModififrs & ~JDK_1_3_MODIFIERS;
    }

    /**
     * Construdts b <dodf>MousfEvfnt</dodf> objfdt witi tif
     * spfdififd sourdf domponfnt,
     * typf, timf, modififrs, doordinbtfs, bbsolutf doordinbtfs, dlidk dount, popupTriggfr flbg,
     * bnd button numbfr.
     * <p>
     * Crfbting bn invblid fvfnt (sudi
     * bs by using morf tibn onf of tif old _MASKs, or modififr/button
     * vblufs wiidi don't mbtdi) rfsults in unspfdififd bfibvior.
     * Evfn if indonsistfnt vblufs for rflbtivf bnd bbsolutf doordinbtfs brf
     * pbssfd to tif donstrudtor, tif mousf fvfnt instbndf is still
     * drfbtfd bnd no fxdfption is tirown.
     * Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf       Tif <dodf>Componfnt</dodf> tibt originbtfd tif fvfnt
     * @pbrbm id              An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link MousfEvfnt}
     * @pbrbm wifn         A long intfgfr tibt givfs tif timf tif fvfnt oddurrfd.
     *                     Pbssing nfgbtivf or zfro vbluf
     *                     is not rfdommfndfd
     * @pbrbm modififrs    b modififr mbsk dfsdribing tif modififr kfys bnd mousf
     *                     buttons (for fxbmplf, siift, dtrl, blt, bnd mftb) tibt
     *                     brf down during tif fvfnt.
     *                     Only fxtfndfd modififrs brf bllowfd to bf usfd bs b
     *                     vbluf for tiis pbrbmftfr (sff tif {@link InputEvfnt#gftModififrsEx}
     *                     dlbss for tif dfsdription of fxtfndfd modififrs).
     *                     Pbssing nfgbtivf pbrbmftfr
     *                     is not rfdommfndfd.
     *                     Zfro vbluf mfbns tibt no modififrs wfrf pbssfd
     * @pbrbm x            Tif iorizontbl x doordinbtf for tif mousf lodbtion.
     *                       It is bllowfd to pbss nfgbtivf vblufs
     * @pbrbm y            Tif vfrtidbl y doordinbtf for tif mousf lodbtion.
     *                       It is bllowfd to pbss nfgbtivf vblufs
     * @pbrbm xAbs           Tif bbsolutf iorizontbl x doordinbtf for tif mousf lodbtion
     *                       It is bllowfd to pbss nfgbtivf vblufs
     * @pbrbm yAbs           Tif bbsolutf vfrtidbl y doordinbtf for tif mousf lodbtion
     *                       It is bllowfd to pbss nfgbtivf vblufs
     * @pbrbm dlidkCount   Tif numbfr of mousf dlidks bssodibtfd witi fvfnt.
     *                       Pbssing nfgbtivf vbluf
     *                       is not rfdommfndfd
     * @pbrbm popupTriggfr A boolfbn tibt fqubls {@dodf truf} if tiis fvfnt
     *                     is b triggfr for b popup mfnu
     * @pbrbm button       An intfgfr tibt indidbtfs, wiidi of tif mousf buttons ibs
     *                     dibngfd its stbtf.
     * Tif following rulfs brf bpplifd to tiis pbrbmftfr:
     * <ul>
     * <li>If support for tif fxtfndfd mousf buttons is
     * {@link Toolkit#brfExtrbMousfButtonsEnbblfd() disbblfd} by Jbvb
     * tifn it is bllowfd to drfbtf {@dodf MousfEvfnt} objfdts only witi tif stbndbrd buttons:
     * {@dodf NOBUTTON}, {@dodf BUTTON1}, {@dodf BUTTON2}, bnd
     * {@dodf BUTTON3}.
     * <li> If support for tif fxtfndfd mousf buttons is
     * {@link Toolkit#brfExtrbMousfButtonsEnbblfd() fnbblfd} by Jbvb
     * tifn it is bllowfd to drfbtf {@dodf MousfEvfnt} objfdts witi
     * tif stbndbrd buttons.
     * In dbsf tif support for fxtfndfd mousf buttons is
     * {@link Toolkit#brfExtrbMousfButtonsEnbblfd() fnbblfd} by Jbvb, tifn
     * in bddition to tif stbndbrd buttons, {@dodf MousfEvfnt} objfdts dbn bf drfbtfd
     * using buttons from tif rbngf stbrting from 4 to
     * {@link jbvb.bwt.MousfInfo#gftNumbfrOfButtons() MousfInfo.gftNumbfrOfButtons()}
     * if tif mousf ibs morf tibn tirff buttons.
     * </ul>
     * @tirows IllfgblArgumfntExdfption if {@dodf button} is lfss tifn zfro
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @tirows IllfgblArgumfntExdfption if {@dodf button} is grfbtfr tifn BUTTON3 bnd tif support for fxtfndfd mousf buttons is
     *                                  {@link Toolkit#brfExtrbMousfButtonsEnbblfd() disbblfd} by Jbvb
     * @tirows IllfgblArgumfntExdfption if {@dodf button} is grfbtfr tifn tif
     *                                  {@link jbvb.bwt.MousfInfo#gftNumbfrOfButtons() durrfnt numbfr of buttons} bnd tif support
     *                                  for fxtfndfd mousf buttons is {@link Toolkit#brfExtrbMousfButtonsEnbblfd() fnbblfd}
     *                                  by Jbvb
     * @tirows IllfgblArgumfntExdfption if bn invblid <dodf>button</dodf>
     *            vbluf is pbssfd in
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftSourdf()
     * @sff #gftID()
     * @sff #gftWifn()
     * @sff #gftModififrs()
     * @sff #gftX()
     * @sff #gftY()
     * @sff #gftXOnSdrffn()
     * @sff #gftYOnSdrffn()
     * @sff #gftClidkCount()
     * @sff #isPopupTriggfr()
     * @sff #gftButton()
     * @sff #button
     * @sff Toolkit#brfExtrbMousfButtonsEnbblfd()
     * @sff jbvb.bwt.MousfInfo#gftNumbfrOfButtons()
     * @sff InputEvfnt#gftMbskForButton(int)
     * @sindf 1.6
     */
    publid MousfEvfnt(Componfnt sourdf, int id, long wifn, int modififrs,
                      int x, int y, int xAbs, int yAbs,
                      int dlidkCount, boolfbn popupTriggfr, int button)
    {
        supfr(sourdf, id, wifn, modififrs);
        tiis.x = x;
        tiis.y = y;
        tiis.xAbs = xAbs;
        tiis.yAbs = yAbs;
        tiis.dlidkCount = dlidkCount;
        tiis.popupTriggfr = popupTriggfr;
        if (button < NOBUTTON){
            tirow nfw IllfgblArgumfntExdfption("Invblid button vbluf :" + button);
        }
        if (button > BUTTON3) {
            if (!Toolkit.gftDffbultToolkit().brfExtrbMousfButtonsEnbblfd()){
                tirow nfw IllfgblArgumfntExdfption("Extrb mousf fvfnts brf disbblfd " + button);
            } flsf {
                if (button > dbdifdNumbfrOfButtons) {
                    tirow nfw IllfgblArgumfntExdfption("Nonfxistfnt button " + button);
                }
            }
            // XToolkit: fxtrb buttons brf not rfporting bbout tifir stbtf dorrfdtly.
            // Bfing prfssfd tify rfport tif stbtf=0 boti on tif prfss bnd on tif rflfbsf.
            // For 1-3 buttons tif stbtf vbluf fqubls zfro on prfss bnd non-zfro on rflfbsf.
            // Otifr modififrs likf Siift, ALT ftd sffm rfport wfll witi fxtrb buttons.
            // Tif problfm rfvfbls bs follows: onf button is prfssfd bnd tifn bnotifr button is prfssfd bnd rflfbsfd.
            // So, tif gftModififrsEx() would not bf zfro duf to b first button bnd wf will skip tiis modififr.
            // Tiis mby ibvf to bf movfd into tif pffr dodf instfbd if possiblf.

            if (gftModififrsEx() != 0) { //Tifrf is bt lfbst onf morf button in b prfssfd stbtf.
                if (id == MousfEvfnt.MOUSE_RELEASED || id == MousfEvfnt.MOUSE_CLICKED){
                    siouldExdludfButtonFromExtModififrs = truf;
                }
            }
        }

        tiis.button = button;

        if ((gftModififrs() != 0) && (gftModififrsEx() == 0)) {
            sftNfwModififrs();
        } flsf if ((gftModififrs() == 0) &&
                   (gftModififrsEx() != 0 || button != NOBUTTON) &&
                   (button <= BUTTON3))
        {
            sftOldModififrs();
        }
    }

    /**
     * Rfturns tif iorizontbl x position of tif fvfnt rflbtivf to tif
     * sourdf domponfnt.
     *
     * @rfturn x  bn intfgfr indidbting iorizontbl position rflbtivf to
     *            tif domponfnt
     */
    publid int gftX() {
        rfturn x;
    }

    /**
     * Rfturns tif vfrtidbl y position of tif fvfnt rflbtivf to tif
     * sourdf domponfnt.
     *
     * @rfturn y  bn intfgfr indidbting vfrtidbl position rflbtivf to
     *            tif domponfnt
     */
    publid int gftY() {
        rfturn y;
    }

    /**
     * Rfturns tif x,y position of tif fvfnt rflbtivf to tif sourdf domponfnt.
     *
     * @rfturn b <dodf>Point</dodf> objfdt dontbining tif x bnd y doordinbtfs
     *         rflbtivf to tif sourdf domponfnt
     *
     */
    publid Point gftPoint() {
        int x;
        int y;
        syndironizfd (tiis) {
            x = tiis.x;
            y = tiis.y;
        }
        rfturn nfw Point(x, y);
    }

    /**
     * Trbnslbtfs tif fvfnt's doordinbtfs to b nfw position
     * by bdding spfdififd <dodf>x</dodf> (iorizontbl) bnd <dodf>y</dodf>
     * (vfrtidbl) offsfts.
     *
     * @pbrbm x tif iorizontbl x vbluf to bdd to tif durrfnt x
     *          doordinbtf position
     * @pbrbm y tif vfrtidbl y vbluf to bdd to tif durrfnt y
                doordinbtf position
     */
    publid syndironizfd void trbnslbtfPoint(int x, int y) {
        tiis.x += x;
        tiis.y += y;
    }

    /**
     * Rfturns tif numbfr of mousf dlidks bssodibtfd witi tiis fvfnt.
     *
     * @rfturn intfgfr vbluf for tif numbfr of dlidks
     */
    publid int gftClidkCount() {
        rfturn dlidkCount;
    }

    /**
     * Rfturns wiidi, if bny, of tif mousf buttons ibs dibngfd stbtf.
     * Tif rfturnfd vbluf is rbngfd
     * from 0 to tif {@link jbvb.bwt.MousfInfo#gftNumbfrOfButtons() MousfInfo.gftNumbfrOfButtons()}
     * vbluf.
     * Tif rfturnfd vbluf indludfs bt lfbst tif following donstbnts:
     * <ul>
     * <li> {@dodf NOBUTTON}
     * <li> {@dodf BUTTON1}
     * <li> {@dodf BUTTON2}
     * <li> {@dodf BUTTON3}
     * </ul>
     * It is bllowfd to usf tiosf donstbnts to dompbrf witi tif rfturnfd button numbfr in tif bpplidbtion.
     * For fxbmplf,
     * <prf>
     * if (bnEvfnt.gftButton() == MousfEvfnt.BUTTON1) {
     * </prf>
     * In pbrtidulbr, for b mousf witi onf, two, or tirff buttons tiis mftiod mby rfturn tif following vblufs:
     * <ul>
     * <li> 0 ({@dodf NOBUTTON})
     * <li> 1 ({@dodf BUTTON1})
     * <li> 2 ({@dodf BUTTON2})
     * <li> 3 ({@dodf BUTTON3})
     * </ul>
     * Button numbfrs grfbtfr tifn {@dodf BUTTON3} ibvf no donstbnt idfntififr. So if b mousf witi fivf buttons is
     * instbllfd, tiis mftiod mby rfturn tif following vblufs:
     * <ul>
     * <li> 0 ({@dodf NOBUTTON})
     * <li> 1 ({@dodf BUTTON1})
     * <li> 2 ({@dodf BUTTON2})
     * <li> 3 ({@dodf BUTTON3})
     * <li> 4
     * <li> 5
     * </ul>
     * <p>
     * Notf: If support for fxtfndfd mousf buttons is {@link Toolkit#brfExtrbMousfButtonsEnbblfd() disbblfd} by Jbvb
     * tifn tif AWT fvfnt subsystfm dofs not produdf mousf fvfnts for tif fxtfndfd mousf
     * buttons. So it is not fxpfdtfd tibt tiis mftiod rfturns bnytiing fxdfpt {@dodf NOBUTTON}, {@dodf BUTTON1},
     * {@dodf BUTTON2}, {@dodf BUTTON3}.
     *
     * @rfturn onf of tif vblufs from 0 to {@link jbvb.bwt.MousfInfo#gftNumbfrOfButtons() MousfInfo.gftNumbfrOfButtons()}
     *         if support for tif fxtfndfd mousf buttons is {@link Toolkit#brfExtrbMousfButtonsEnbblfd() fnbblfd} by Jbvb.
     *         Tibt rbngf indludfs {@dodf NOBUTTON}, {@dodf BUTTON1}, {@dodf BUTTON2}, {@dodf BUTTON3};
     *         <br>
     *         {@dodf NOBUTTON}, {@dodf BUTTON1}, {@dodf BUTTON2} or {@dodf BUTTON3}
     *         if support for tif fxtfndfd mousf buttons is {@link Toolkit#brfExtrbMousfButtonsEnbblfd() disbblfd} by Jbvb
     * @sindf 1.4
     * @sff Toolkit#brfExtrbMousfButtonsEnbblfd()
     * @sff jbvb.bwt.MousfInfo#gftNumbfrOfButtons()
     * @sff #MousfEvfnt(Componfnt, int, long, int, int, int, int, int, int, boolfbn, int)
     * @sff InputEvfnt#gftMbskForButton(int)
     */
    publid int gftButton() {
        rfturn button;
    }

    /**
     * Rfturns wiftifr or not tiis mousf fvfnt is tif popup mfnu
     * triggfr fvfnt for tif plbtform.
     * <p><b>Notf</b>: Popup mfnus brf triggfrfd difffrfntly
     * on difffrfnt systfms. Tifrfforf, <dodf>isPopupTriggfr</dodf>
     * siould bf difdkfd in boti <dodf>mousfPrfssfd</dodf>
     * bnd <dodf>mousfRflfbsfd</dodf>
     * for propfr dross-plbtform fundtionblity.
     *
     * @rfturn boolfbn, truf if tiis fvfnt is tif popup mfnu triggfr
     *         for tiis plbtform
     */
    publid boolfbn isPopupTriggfr() {
        rfturn popupTriggfr;
    }

    /**
     * Rfturns b <dodf>String</dodf> instbndf dfsdribing tif modififr kfys bnd
     * mousf buttons tibt wfrf down during tif fvfnt, sudi bs "Siift",
     * or "Ctrl+Siift". Tifsf strings dbn bf lodblizfd by dibnging
     * tif <dodf>bwt.propfrtifs</dodf> filf.
     * <p>
     * Notf tibt tif <dodf>InputEvfnt.ALT_MASK</dodf> bnd
     * <dodf>InputEvfnt.BUTTON2_MASK</dodf> ibvf fqubl vblufs,
     * so tif "Alt" string is rfturnfd for boti modififrs.  Likfwisf,
     * tif <dodf>InputEvfnt.META_MASK</dodf> bnd
     * <dodf>InputEvfnt.BUTTON3_MASK</dodf> ibvf fqubl vblufs,
     * so tif "Mftb" string is rfturnfd for boti modififrs.
     * <p>
     * Notf tibt pbssing nfgbtivf pbrbmftfr is indorrfdt,
     * bnd will dbusf tif rfturning bn unspfdififd string.
     * Zfro pbrbmftfr mfbns tibt no modififrs wfrf pbssfd bnd will
     * dbusf tif rfturning bn fmpty string.
     *
     * @pbrbm modififrs A modififr mbsk dfsdribing tif modififr kfys bnd
     *                  mousf buttons tibt wfrf down during tif fvfnt
     * @rfturn string   string tfxt dfsdription of tif dombinbtion of modififr
     *                  kfys bnd mousf buttons tibt wfrf down during tif fvfnt
     * @sff InputEvfnt#gftModififrsExTfxt(int)
     * @sindf 1.4
     */
    publid stbtid String gftMousfModififrsTfxt(int modififrs) {
        StringBuildfr buf = nfw StringBuildfr();
        if ((modififrs & InputEvfnt.ALT_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.blt", "Alt"));
            buf.bppfnd("+");
        }
        if ((modififrs & InputEvfnt.META_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.mftb", "Mftb"));
            buf.bppfnd("+");
        }
        if ((modififrs & InputEvfnt.CTRL_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.dontrol", "Ctrl"));
            buf.bppfnd("+");
        }
        if ((modififrs & InputEvfnt.SHIFT_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.siift", "Siift"));
            buf.bppfnd("+");
        }
        if ((modififrs & InputEvfnt.ALT_GRAPH_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.bltGrbpi", "Alt Grbpi"));
            buf.bppfnd("+");
        }
        if ((modififrs & InputEvfnt.BUTTON1_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.button1", "Button1"));
            buf.bppfnd("+");
        }
        if ((modififrs & InputEvfnt.BUTTON2_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.button2", "Button2"));
            buf.bppfnd("+");
        }
        if ((modififrs & InputEvfnt.BUTTON3_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.button3", "Button3"));
            buf.bppfnd("+");
        }

        int mbsk;

        // TODO: bdd b toolkit fifld tibt iolds b numbfr of button on tif mousf.
        // As tif mftiod gftMousfModififrsTfxt() is stbtid bnd obtbin
        // bn intfgfr bs b pbrbmftfr tifn wf mby not rfstridt tiis witi tif numbfr
        // of buttons instbllfd on tif mousf.
        // It's b tfmporbry solution. Wf nffd to somfiow iold tif numbfr of buttons somfwifrf flsf.
        for (int i = 1; i <= dbdifdNumbfrOfButtons; i++){
            mbsk = InputEvfnt.gftMbskForButton(i);
            if ((modififrs & mbsk) != 0 &&
                buf.indfxOf(Toolkit.gftPropfrty("AWT.button"+i, "Button"+i)) == -1) //1,2,3 buttons mby blrfbdy bf tifrf; so don't duplidbtf it.
            {
                buf.bppfnd(Toolkit.gftPropfrty("AWT.button"+i, "Button"+i));
                buf.bppfnd("+");
            }
        }

        if (buf.lfngti() > 0) {
            buf.sftLfngti(buf.lfngti()-1); // rfmovf trbiling '+'
        }
        rfturn buf.toString();
    }

    /**
     * Rfturns b pbrbmftfr string idfntifying tiis fvfnt.
     * Tiis mftiod is usfful for fvfnt-logging bnd for dfbugging.
     *
     * @rfturn b string idfntifying tif fvfnt bnd its bttributfs
     */
    publid String pbrbmString() {
        StringBuildfr str = nfw StringBuildfr(80);

        switdi(id) {
          dbsf MOUSE_PRESSED:
              str.bppfnd("MOUSE_PRESSED");
              brfbk;
          dbsf MOUSE_RELEASED:
              str.bppfnd("MOUSE_RELEASED");
              brfbk;
          dbsf MOUSE_CLICKED:
              str.bppfnd("MOUSE_CLICKED");
              brfbk;
          dbsf MOUSE_ENTERED:
              str.bppfnd("MOUSE_ENTERED");
              brfbk;
          dbsf MOUSE_EXITED:
              str.bppfnd("MOUSE_EXITED");
              brfbk;
          dbsf MOUSE_MOVED:
              str.bppfnd("MOUSE_MOVED");
              brfbk;
          dbsf MOUSE_DRAGGED:
              str.bppfnd("MOUSE_DRAGGED");
              brfbk;
          dbsf MOUSE_WHEEL:
              str.bppfnd("MOUSE_WHEEL");
              brfbk;
           dffbult:
              str.bppfnd("unknown typf");
        }

        // (x,y) doordinbtfs
        str.bppfnd(",(").bppfnd(x).bppfnd(",").bppfnd(y).bppfnd(")");
        str.bppfnd(",bbsolutf(").bppfnd(xAbs).bppfnd(",").bppfnd(yAbs).bppfnd(")");

        if (id != MOUSE_DRAGGED && id != MOUSE_MOVED){
            str.bppfnd(",button=").bppfnd(gftButton());
        }

        if (gftModififrs() != 0) {
            str.bppfnd(",modififrs=").bppfnd(gftMousfModififrsTfxt(modififrs));
        }

        if (gftModififrsEx() != 0) {
            //Using plbin "modififrs" ifrf dofs siow bn fxdludfd fxtfndfd buttons in tif string fvfnt rfprfsfntbtion.
            //gftModififrsEx() solvfs tif problfm.
            str.bppfnd(",fxtModififrs=").bppfnd(gftModififrsExTfxt(gftModififrsEx()));
        }

        str.bppfnd(",dlidkCount=").bppfnd(dlidkCount);

        rfturn str.toString();
    }

    /**
     * Sfts nfw modififrs by tif old onfs.
     * Also sfts button.
     */
    privbtf void sftNfwModififrs() {
        if ((modififrs & BUTTON1_MASK) != 0) {
            modififrs |= BUTTON1_DOWN_MASK;
        }
        if ((modififrs & BUTTON2_MASK) != 0) {
            modififrs |= BUTTON2_DOWN_MASK;
        }
        if ((modififrs & BUTTON3_MASK) != 0) {
            modififrs |= BUTTON3_DOWN_MASK;
        }
        if (id == MOUSE_PRESSED
            || id == MOUSE_RELEASED
            || id == MOUSE_CLICKED)
        {
            if ((modififrs & BUTTON1_MASK) != 0) {
                button = BUTTON1;
                modififrs &= ~BUTTON2_MASK & ~BUTTON3_MASK;
                if (id != MOUSE_PRESSED) {
                    modififrs &= ~BUTTON1_DOWN_MASK;
                }
            } flsf if ((modififrs & BUTTON2_MASK) != 0) {
                button = BUTTON2;
                modififrs &= ~BUTTON1_MASK & ~BUTTON3_MASK;
                if (id != MOUSE_PRESSED) {
                    modififrs &= ~BUTTON2_DOWN_MASK;
                }
            } flsf if ((modififrs & BUTTON3_MASK) != 0) {
                button = BUTTON3;
                modififrs &= ~BUTTON1_MASK & ~BUTTON2_MASK;
                if (id != MOUSE_PRESSED) {
                    modififrs &= ~BUTTON3_DOWN_MASK;
                }
            }
        }
        if ((modififrs & InputEvfnt.ALT_MASK) != 0) {
            modififrs |= InputEvfnt.ALT_DOWN_MASK;
        }
        if ((modififrs & InputEvfnt.META_MASK) != 0) {
            modififrs |= InputEvfnt.META_DOWN_MASK;
        }
        if ((modififrs & InputEvfnt.SHIFT_MASK) != 0) {
            modififrs |= InputEvfnt.SHIFT_DOWN_MASK;
        }
        if ((modififrs & InputEvfnt.CTRL_MASK) != 0) {
            modififrs |= InputEvfnt.CTRL_DOWN_MASK;
        }
        if ((modififrs & InputEvfnt.ALT_GRAPH_MASK) != 0) {
            modififrs |= InputEvfnt.ALT_GRAPH_DOWN_MASK;
        }
    }

    /**
     * Sfts old modififrs by tif nfw onfs.
     */
    privbtf void sftOldModififrs() {
        if (id == MOUSE_PRESSED
            || id == MOUSE_RELEASED
            || id == MOUSE_CLICKED)
        {
            switdi(button) {
            dbsf BUTTON1:
                modififrs |= BUTTON1_MASK;
                brfbk;
            dbsf BUTTON2:
                modififrs |= BUTTON2_MASK;
                brfbk;
            dbsf BUTTON3:
                modififrs |= BUTTON3_MASK;
                brfbk;
            }
        } flsf {
            if ((modififrs & BUTTON1_DOWN_MASK) != 0) {
                modififrs |= BUTTON1_MASK;
            }
            if ((modififrs & BUTTON2_DOWN_MASK) != 0) {
                modififrs |= BUTTON2_MASK;
            }
            if ((modififrs & BUTTON3_DOWN_MASK) != 0) {
                modififrs |= BUTTON3_MASK;
            }
        }
        if ((modififrs & ALT_DOWN_MASK) != 0) {
            modififrs |= ALT_MASK;
        }
        if ((modififrs & META_DOWN_MASK) != 0) {
            modififrs |= META_MASK;
        }
        if ((modififrs & SHIFT_DOWN_MASK) != 0) {
            modififrs |= SHIFT_MASK;
        }
        if ((modififrs & CTRL_DOWN_MASK) != 0) {
            modififrs |= CTRL_MASK;
        }
        if ((modififrs & ALT_GRAPH_DOWN_MASK) != 0) {
            modififrs |= ALT_GRAPH_MASK;
        }
    }

    /**
     * Sfts nfw modififrs by tif old onfs.
     * @sfribl
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();
        if (gftModififrs() != 0 && gftModififrsEx() == 0) {
            sftNfwModififrs();
        }
    }
}
