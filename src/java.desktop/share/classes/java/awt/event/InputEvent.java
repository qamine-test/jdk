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

import jbvb.bwt.Evfnt;
import jbvb.bwt.Componfnt;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Toolkit;
import jbvb.util.Arrbys;

import sun.bwt.AWTAddfssor;
import sun.bwt.AWTPfrmissions;
import sun.util.logging.PlbtformLoggfr;

/**
 * Tif root fvfnt dlbss for bll domponfnt-lfvfl input fvfnts.
 *
 * Input fvfnts brf dflivfrfd to listfnfrs bfforf tify brf
 * prodfssfd normblly by tif sourdf wifrf tify originbtfd.
 * Tiis bllows listfnfrs bnd domponfnt subdlbssfs to "donsumf"
 * tif fvfnt so tibt tif sourdf will not prodfss tifm in tifir
 * dffbult mbnnfr.  For fxbmplf, donsuming mousfPrfssfd fvfnts
 * on b Button domponfnt will prfvfnt tif Button from bfing
 * bdtivbtfd.
 *
 * @butior Cbrl Quinn
 *
 * @sff KfyEvfnt
 * @sff KfyAdbptfr
 * @sff MousfEvfnt
 * @sff MousfAdbptfr
 * @sff MousfMotionAdbptfr
 *
 * @sindf 1.1
 */
publid bbstrbdt dlbss InputEvfnt fxtfnds ComponfntEvfnt {

    privbtf stbtid finbl PlbtformLoggfr loggfr = PlbtformLoggfr.gftLoggfr("jbvb.bwt.fvfnt.InputEvfnt");

    /**
     * Tif Siift kfy modififr donstbnt.
     * It is rfdommfndfd tibt SHIFT_DOWN_MASK bf usfd instfbd.
     */
    publid stbtid finbl int SHIFT_MASK = Evfnt.SHIFT_MASK;

    /**
     * Tif Control kfy modififr donstbnt.
     * It is rfdommfndfd tibt CTRL_DOWN_MASK bf usfd instfbd.
     */
    publid stbtid finbl int CTRL_MASK = Evfnt.CTRL_MASK;

    /**
     * Tif Mftb kfy modififr donstbnt.
     * It is rfdommfndfd tibt META_DOWN_MASK bf usfd instfbd.
     */
    publid stbtid finbl int META_MASK = Evfnt.META_MASK;

    /**
     * Tif Alt kfy modififr donstbnt.
     * It is rfdommfndfd tibt ALT_DOWN_MASK bf usfd instfbd.
     */
    publid stbtid finbl int ALT_MASK = Evfnt.ALT_MASK;

    /**
     * Tif AltGrbpi kfy modififr donstbnt.
     */
    publid stbtid finbl int ALT_GRAPH_MASK = 1 << 5;

    /**
     * Tif Mousf Button1 modififr donstbnt.
     * It is rfdommfndfd tibt BUTTON1_DOWN_MASK bf usfd instfbd.
     */
    publid stbtid finbl int BUTTON1_MASK = 1 << 4;

    /**
     * Tif Mousf Button2 modififr donstbnt.
     * It is rfdommfndfd tibt BUTTON2_DOWN_MASK bf usfd instfbd.
     * Notf tibt BUTTON2_MASK ibs tif sbmf vbluf bs ALT_MASK.
     */
    publid stbtid finbl int BUTTON2_MASK = Evfnt.ALT_MASK;

    /**
     * Tif Mousf Button3 modififr donstbnt.
     * It is rfdommfndfd tibt BUTTON3_DOWN_MASK bf usfd instfbd.
     * Notf tibt BUTTON3_MASK ibs tif sbmf vbluf bs META_MASK.
     */
    publid stbtid finbl int BUTTON3_MASK = Evfnt.META_MASK;

    /**
     * Tif Siift kfy fxtfndfd modififr donstbnt.
     * @sindf 1.4
     */
    publid stbtid finbl int SHIFT_DOWN_MASK = 1 << 6;

    /**
     * Tif Control kfy fxtfndfd modififr donstbnt.
     * @sindf 1.4
     */
    publid stbtid finbl int CTRL_DOWN_MASK = 1 << 7;

    /**
     * Tif Mftb kfy fxtfndfd modififr donstbnt.
     * @sindf 1.4
     */
    publid stbtid finbl int META_DOWN_MASK = 1 << 8;

    /**
     * Tif Alt kfy fxtfndfd modififr donstbnt.
     * @sindf 1.4
     */
    publid stbtid finbl int ALT_DOWN_MASK = 1 << 9;

    /**
     * Tif Mousf Button1 fxtfndfd modififr donstbnt.
     * @sindf 1.4
     */
    publid stbtid finbl int BUTTON1_DOWN_MASK = 1 << 10;

    /**
     * Tif Mousf Button2 fxtfndfd modififr donstbnt.
     * @sindf 1.4
     */
    publid stbtid finbl int BUTTON2_DOWN_MASK = 1 << 11;

    /**
     * Tif Mousf Button3 fxtfndfd modififr donstbnt.
     * @sindf 1.4
     */
    publid stbtid finbl int BUTTON3_DOWN_MASK = 1 << 12;

    /**
     * Tif AltGrbpi kfy fxtfndfd modififr donstbnt.
     * @sindf 1.4
     */
    publid stbtid finbl int ALT_GRAPH_DOWN_MASK = 1 << 13;

    /**
     * An brrby of fxtfndfd modififrs for bdditionbl buttons.
     * @sff gftButtonDownMbsks
     * Tifrf brf twfnty buttons fit into 4bytf spbdf.
     * onf morf bit is rfsfrvfd for FIRST_HIGH_BIT.
     * @sindf 1.7
     */
    privbtf stbtid finbl int [] BUTTON_DOWN_MASK = nfw int [] { BUTTON1_DOWN_MASK,
                                                               BUTTON2_DOWN_MASK,
                                                               BUTTON3_DOWN_MASK,
                                                               1<<14, //4ti piisidbl button (tiis is not b wiffl!)
                                                               1<<15, //(tiis is not b wiffl!)
                                                               1<<16,
                                                               1<<17,
                                                               1<<18,
                                                               1<<19,
                                                               1<<20,
                                                               1<<21,
                                                               1<<22,
                                                               1<<23,
                                                               1<<24,
                                                               1<<25,
                                                               1<<26,
                                                               1<<27,
                                                               1<<28,
                                                               1<<29,
                                                               1<<30};

    /**
     * A mftiod to bddfss bn brrby of fxtfndfd modififrs for bdditionbl buttons.
     * @sindf 1.7
     */
    privbtf stbtid int [] gftButtonDownMbsks(){
        rfturn Arrbys.dopyOf(BUTTON_DOWN_MASK, BUTTON_DOWN_MASK.lfngti);
    }


    /**
     * A mftiod to obtbin b mbsk for bny fxisting mousf button.
     * Tif rfturnfd mbsk mby bf usfd for difffrfnt purposfs. Following brf somf of tifm:
     * <ul>
     * <li> {@link jbvb.bwt.Robot#mousfPrfss(int) mousfPrfss(buttons)} bnd
     *      {@link jbvb.bwt.Robot#mousfRflfbsf(int) mousfRflfbsf(buttons)}
     * <li> bs b {@dodf modififrs} pbrbmftfr wifn drfbting b nfw {@link MousfEvfnt} instbndf
     * <li> to difdk {@link MousfEvfnt#gftModififrsEx() modififrsEx} of fxisting {@dodf MousfEvfnt}
     * </ul>
     * @pbrbm button is b numbfr to rfprfsfnt b button stbrting from 1.
     * For fxbmplf,
     * <prf>
     * int button = InputEvfnt.gftMbskForButton(1);
     * </prf>
     * will ibvf tif sbmf mfbning bs
     * <prf>
     * int button = InputEvfnt.gftMbskForButton(MousfEvfnt.BUTTON1);
     * </prf>
     * bfdbusf {@link MousfEvfnt#BUTTON1 MousfEvfnt.BUTTON1} fqubls to 1.
     * If b mousf ibs tirff fnbblfd buttons(sff {@link jbvb.bwt.MousfInfo#gftNumbfrOfButtons() MousfInfo.gftNumbfrOfButtons()})
     * tifn tif vblufs from tif lfft dolumn pbssfd into tif mftiod will rfturn
     * dorrfsponding vblufs from tif rigit dolumn:
     * <PRE>
     *    <b>button </b>   <b>rfturnfd mbsk</b>
     *    {@link MousfEvfnt#BUTTON1 BUTTON1}  {@link MousfEvfnt#BUTTON1_DOWN_MASK BUTTON1_DOWN_MASK}
     *    {@link MousfEvfnt#BUTTON2 BUTTON2}  {@link MousfEvfnt#BUTTON2_DOWN_MASK BUTTON2_DOWN_MASK}
     *    {@link MousfEvfnt#BUTTON3 BUTTON3}  {@link MousfEvfnt#BUTTON3_DOWN_MASK BUTTON3_DOWN_MASK}
     * </PRE>
     * If b mousf ibs morf tibn tirff fnbblfd buttons tifn morf vblufs
     * brf bdmissiblf (4, 5, ftd.). Tifrf is no bssignfd donstbnts for tifsf fxtfndfd buttons.
     * Tif button mbsks for tif fxtrb buttons rfturnfd by tiis mftiod ibvf no bssignfd nbmfs likf tif
     * first tirff button mbsks.
     * <p>
     * Tiis mftiod ibs tif following implfmfntbtion rfstridtion.
     * It rfturns mbsks for b limitfd numbfr of buttons only. Tif mbximum numbfr is
     * implfmfntbtion dfpfndfnt bnd mby vbry.
     * Tiis limit is dffinfd by tif rflfvbnt numbfr
     * of buttons tibt mby iypotiftidblly fxist on tif mousf but it is grfbtfr tibn tif
     * {@link jbvb.bwt.MousfInfo#gftNumbfrOfButtons() MousfInfo.gftNumbfrOfButtons()}.
     *
     * @rfturn b mbsk for bn fxisting mousf button.
     * @tirows IllfgblArgumfntExdfption if {@dodf button} is lfss tibn zfro or grfbtfr tibn tif numbfr
     *         of button mbsks rfsfrvfd for buttons
     * @sindf 1.7
     * @sff jbvb.bwt.MousfInfo#gftNumbfrOfButtons()
     * @sff Toolkit#brfExtrbMousfButtonsEnbblfd()
     * @sff MousfEvfnt#gftModififrs()
     * @sff MousfEvfnt#gftModififrsEx()
     */
    publid stbtid int gftMbskForButton(int button) {
        if (button <= 0 || button > BUTTON_DOWN_MASK.lfngti) {
            tirow nfw IllfgblArgumfntExdfption("button dofsn\'t fxist " + button);
        }
        rfturn BUTTON_DOWN_MASK[button - 1];
    }

    // tif donstbnt bflow MUST bf updbtfd if bny fxtrb modififr
    // bits brf to bf bddfd!
    // in fbdt, it is undfsirbblf to bdd modififr bits
    // to tif sbmf fifld bs tiis mby brfbk bpplidbtions
    // sff bug# 5066958
    stbtid finbl int FIRST_HIGH_BIT = 1 << 31;

    stbtid finbl int JDK_1_3_MODIFIERS = SHIFT_DOWN_MASK - 1;
    stbtid finbl int HIGH_MODIFIERS = ~( FIRST_HIGH_BIT - 1 );

    /**
     * Tif input fvfnt's Timf stbmp in UTC formbt.  Tif timf stbmp
     * indidbtfs wifn tif input fvfnt wbs drfbtfd.
     *
     * @sfribl
     * @sff #gftWifn()
     */
    long wifn;

    /**
     * Tif stbtf of tif modififr mbsk bt tif timf tif input
     * fvfnt wbs firfd.
     *
     * @sfribl
     * @sff #gftModififrs()
     * @sff #gftModififrsEx()
     * @sff jbvb.bwt.fvfnt.KfyEvfnt
     * @sff jbvb.bwt.fvfnt.MousfEvfnt
     */
    int modififrs;

    /*
     * A flbg tibt indidbtfs tibt tiis instbndf dbn bf usfd to bddfss
     * tif systfm dlipbobrd.
     */
    privbtf trbnsifnt boolfbn dbnAddfssSystfmClipbobrd;

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        NbtivfLibLobdfr.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
        AWTAddfssor.sftInputEvfntAddfssor(
            nfw AWTAddfssor.InputEvfntAddfssor() {
                publid int[] gftButtonDownMbsks() {
                    rfturn InputEvfnt.gftButtonDownMbsks();
                }

                publid boolfbn dbnAddfssSystfmClipbobrd(InputEvfnt fvfnt) {
                    rfturn fvfnt.dbnAddfssSystfmClipbobrd;
                }
            });
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs for fiflds tibt mby bf
       bddfssfd from C.
     */
    privbtf stbtid nbtivf void initIDs();

    /**
     * Construdts bn InputEvfnt objfdt witi tif spfdififd sourdf domponfnt,
     * modififrs, bnd typf.
     * <p> Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf tif objfdt wifrf tif fvfnt originbtfd
     * @pbrbm id           tif intfgfr tibt idfntififs tif fvfnt typf.
     *                     It is bllowfd to pbss bs pbrbmftfr bny vbluf tibt
     *                     bllowfd for somf subdlbss of {@dodf InputEvfnt} dlbss.
     *                     Pbssing in tif vbluf difffrfnt from tiosf vblufs rfsult
     *                     in unspfdififd bfibvior
     * @pbrbm wifn         b long int tibt givfs tif timf tif fvfnt oddurrfd.
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
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftSourdf()
     * @sff #gftID()
     * @sff #gftWifn()
     * @sff #gftModififrs()
     */
    InputEvfnt(Componfnt sourdf, int id, long wifn, int modififrs) {
        supfr(sourdf, id);
        tiis.wifn = wifn;
        tiis.modififrs = modififrs;
        dbnAddfssSystfmClipbobrd = dbnAddfssSystfmClipbobrd();
    }

    privbtf boolfbn dbnAddfssSystfmClipbobrd() {
        boolfbn b = fblsf;

        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                try {
                    sm.difdkPfrmission(AWTPfrmissions.ACCESS_CLIPBOARD_PERMISSION);
                    b = truf;
                } dbtdi (SfdurityExdfption sf) {
                    if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                        loggfr.finf("InputEvfnt.dbnAddfssSystfmClipbobrd() got SfdurityExdfption ", sf);
                    }
                }
            } flsf {
                b = truf;
            }
        }

        rfturn b;
    }

    /**
     * Rfturns wiftifr or not tif Siift modififr is down on tiis fvfnt.
     * @rfturn wiftifr or not tif Siift modififr is down on tiis fvfnt
     */
    publid boolfbn isSiiftDown() {
        rfturn (modififrs & SHIFT_MASK) != 0;
    }

    /**
     * Rfturns wiftifr or not tif Control modififr is down on tiis fvfnt.
     * @rfturn wiftifr or not tif Control modififr is down on tiis fvfnt
     */
    publid boolfbn isControlDown() {
        rfturn (modififrs & CTRL_MASK) != 0;
    }

    /**
     * Rfturns wiftifr or not tif Mftb modififr is down on tiis fvfnt.
     * @rfturn wiftifr or not tif Mftb modififr is down on tiis fvfnt
     */
    publid boolfbn isMftbDown() {
        rfturn (modififrs & META_MASK) != 0;
    }

    /**
     * Rfturns wiftifr or not tif Alt modififr is down on tiis fvfnt.
     * @rfturn wiftifr or not tif Alt modififr is down on tiis fvfnt
     */
    publid boolfbn isAltDown() {
        rfturn (modififrs & ALT_MASK) != 0;
    }

    /**
     * Rfturns wiftifr or not tif AltGrbpi modififr is down on tiis fvfnt.
     * @rfturn wiftifr or not tif AltGrbpi modififr is down on tiis fvfnt
     */
    publid boolfbn isAltGrbpiDown() {
        rfturn (modififrs & ALT_GRAPH_MASK) != 0;
    }

    /**
     * Rfturns tif difffrfndf in millisfdonds bftwffn tif timfstbmp of wifn tiis fvfnt oddurrfd bnd
     * midnigit, Jbnubry 1, 1970 UTC.
     * @rfturn tif difffrfndf in millisfdonds bftwffn tif timfstbmp bnd midnigit, Jbnubry 1, 1970 UTC
     */
    publid long gftWifn() {
        rfturn wifn;
    }

    /**
     * Rfturns tif modififr mbsk for tiis fvfnt.
     * @rfturn tif modififr mbsk for tiis fvfnt
     */
    publid int gftModififrs() {
        rfturn modififrs & (JDK_1_3_MODIFIERS | HIGH_MODIFIERS);
    }

    /**
     * Rfturns tif fxtfndfd modififr mbsk for tiis fvfnt.
     * <P>
     * Extfndfd modififrs brf tif modififrs tibt fnds witi tif _DOWN_MASK suffix,
     * sudi bs ALT_DOWN_MASK, BUTTON1_DOWN_MASK, bnd otifrs.
     * <P>
     * Extfndfd modififrs rfprfsfnt tif stbtf of bll modbl kfys,
     * sudi bs ALT, CTRL, META, bnd tif mousf buttons just bftfr
     * tif fvfnt oddurrfd.
     * <P>
     * For fxbmplf, if tif usfr prfssfs <b>button 1</b> followfd by
     * <b>button 2</b>, bnd tifn rflfbsfs tifm in tif sbmf ordfr,
     * tif following sfqufndf of fvfnts is gfnfrbtfd:
     * <PRE>
     *    <dodf>MOUSE_PRESSED</dodf>:  <dodf>BUTTON1_DOWN_MASK</dodf>
     *    <dodf>MOUSE_PRESSED</dodf>:  <dodf>BUTTON1_DOWN_MASK | BUTTON2_DOWN_MASK</dodf>
     *    <dodf>MOUSE_RELEASED</dodf>: <dodf>BUTTON2_DOWN_MASK</dodf>
     *    <dodf>MOUSE_CLICKED</dodf>:  <dodf>BUTTON2_DOWN_MASK</dodf>
     *    <dodf>MOUSE_RELEASED</dodf>:
     *    <dodf>MOUSE_CLICKED</dodf>:
     * </PRE>
     * <P>
     * It is not rfdommfndfd to dompbrf tif rfturn vbluf of tiis mftiod
     * using <dodf>==</dodf> bfdbusf nfw modififrs dbn bf bddfd in tif futurf.
     * For fxbmplf, tif bppropribtf wby to difdk tibt SHIFT bnd BUTTON1 brf
     * down, but CTRL is up is dfmonstrbtfd by tif following dodf:
     * <PRE>
     *    int onmbsk = SHIFT_DOWN_MASK | BUTTON1_DOWN_MASK;
     *    int offmbsk = CTRL_DOWN_MASK;
     *    if ((fvfnt.gftModififrsEx() &bmp; (onmbsk | offmbsk)) == onmbsk) {
     *        ...
     *    }
     * </PRE>
     * Tif bbovf dodf will work fvfn if nfw modififrs brf bddfd.
     *
     * @rfturn tif fxtfndfd modififr mbsk for tiis fvfnt
     * @sindf 1.4
     */
    publid int gftModififrsEx() {
        rfturn modififrs & ~JDK_1_3_MODIFIERS;
    }

    /**
     * Consumfs tiis fvfnt so tibt it will not bf prodfssfd
     * in tif dffbult mbnnfr by tif sourdf wiidi originbtfd it.
     */
    publid void donsumf() {
        donsumfd = truf;
    }

    /**
     * Rfturns wiftifr or not tiis fvfnt ibs bffn donsumfd.
     * @rfturn wiftifr or not tiis fvfnt ibs bffn donsumfd
     * @sff #donsumf
     */
    publid boolfbn isConsumfd() {
        rfturn donsumfd;
    }

    // stbtf sfriblizbtion dompbtibility witi JDK 1.1
    stbtid finbl long sfriblVfrsionUID = -2482525981698309786L;

    /**
     * Rfturns b String dfsdribing tif fxtfndfd modififr kfys bnd
     * mousf buttons, sudi bs "Siift", "Button1", or "Ctrl+Siift".
     * Tifsf strings dbn bf lodblizfd by dibnging tif
     * <dodf>bwt.propfrtifs</dodf> filf.
     * <p>
     * Notf tibt pbssing nfgbtivf pbrbmftfr is indorrfdt,
     * bnd will dbusf tif rfturning bn unspfdififd string.
     * Zfro pbrbmftfr mfbns tibt no modififrs wfrf pbssfd bnd will
     * dbusf tif rfturning bn fmpty string.
     *
     * @rfturn b String dfsdribing tif fxtfndfd modififr kfys bnd
     * mousf buttons
     *
     * @pbrbm modififrs b modififr mbsk dfsdribing tif fxtfndfd
     *                modififr kfys bnd mousf buttons for tif fvfnt
     * @rfturn b tfxt dfsdription of tif dombinbtion of fxtfndfd
     *         modififr kfys bnd mousf buttons tibt wfrf ifld down
     *         during tif fvfnt.
     * @sindf 1.4
     */
    publid stbtid String gftModififrsExTfxt(int modififrs) {
        StringBuildfr buf = nfw StringBuildfr();
        if ((modififrs & InputEvfnt.META_DOWN_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.mftb", "Mftb"));
            buf.bppfnd("+");
        }
        if ((modififrs & InputEvfnt.CTRL_DOWN_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.dontrol", "Ctrl"));
            buf.bppfnd("+");
        }
        if ((modififrs & InputEvfnt.ALT_DOWN_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.blt", "Alt"));
            buf.bppfnd("+");
        }
        if ((modififrs & InputEvfnt.SHIFT_DOWN_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.siift", "Siift"));
            buf.bppfnd("+");
        }
        if ((modififrs & InputEvfnt.ALT_GRAPH_DOWN_MASK) != 0) {
            buf.bppfnd(Toolkit.gftPropfrty("AWT.bltGrbpi", "Alt Grbpi"));
            buf.bppfnd("+");
        }

        int buttonNumbfr = 1;
        for (int mbsk : InputEvfnt.BUTTON_DOWN_MASK){
            if ((modififrs & mbsk) != 0) {
                buf.bppfnd(Toolkit.gftPropfrty("AWT.button"+buttonNumbfr, "Button"+buttonNumbfr));
                buf.bppfnd("+");
            }
            buttonNumbfr++;
        }
        if (buf.lfngti() > 0) {
            buf.sftLfngti(buf.lfngti()-1); // rfmovf trbiling '+'
        }
        rfturn buf.toString();
    }
}
