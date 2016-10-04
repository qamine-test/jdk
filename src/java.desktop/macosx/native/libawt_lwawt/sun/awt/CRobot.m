/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#import "jni_util.i"

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import <ApplidbtionSfrvidfs/ApplidbtionSfrvidfs.i>

#import "LWCToolkit.i"
#import "sun_lwbwt_mbdosx_CRobot.i"
#import "jbvb_bwt_fvfnt_InputEvfnt.i"
#import "sizfdbld.i"


// Stbrting numbfr for fvfnt numbfrs gfnfrbtfd by Robot.
// Applf dods don't mfntion bt bll wibt brf tif rfquirfmfnts
// for tifsf numbfrs. It sffms tibt tify must bf iigifr
// tibn fvfnt numbfrs from rfbl fvfnts, wiidi stbrt bt somf
// vbluf dlosf to zfro. Tifrf is no API for obtbining durrfnt
// fvfnt numbfr, so wf ibvf to stbrt from somf rbndom numbfr.
// 32000 bs stbrting vbluf works for mf, lft's iopf tibt it will
// work for otifrs bs wfll.
#dffinf ROBOT_EVENT_NUMBER_START 32000

#dffinf k_JAVA_ROBOT_WHEEL_COUNT 1

#if !dffinfd(kCGBitmbpBytfOrdfr32Host)
#dffinf kCGBitmbpBytfOrdfr32Host 0
#fndif

// In OS X, lfft bnd rigit mousf button sibrf tif sbmf dlidk dount.
// Tibt is, if onf stbrts dlidking tif lfft button rbpidly bnd tifn
// switdifs to tif rigit button, tifn tif dlidk dount will dontinuf
// indrfbsing, witiout dropping to 1 in bftwffn. Tif middlf button,
// iowfvfr, ibs its own dlidk dount.
// For robot, wf brfn't going to fmulbtf bll tibt domplfxity. All our
// synitftid dlidks sibrf tif sbmf dlidk dount.
stbtid int gsClidkCount;
stbtid NSTimfIntfrvbl gsLbstClidkTimf;

// Appbrfntly, for mousf up/down fvfnts wf ibvf to sft bn fvfnt numbfr
// tibt is indrfmfntfd on fbdi button prfss. Otifrwisf, strbngf tiings
// ibppfn witi z-ordfr.
stbtid int gsEvfntNumbfr;
stbtid int* gsButtonEvfntNumbfr;

stbtid inlinf CGKfyCodf GftCGKfyCodf(jint jbvbKfyCodf);

stbtid void PostMousfEvfnt(donst CGPoint point, CGMousfButton button,
                           CGEvfntTypf typf, int dlidkCount, int fvfntNumbfr);

stbtid int GftClidkCount(BOOL isDown);

stbtid void
CrfbtfJbvbExdfption(JNIEnv* fnv, CGError frr)
{
    // Tirow b jbvb fxdfption indidbting wibt is wrong.
    NSString* s = [NSString stringWitiFormbt:@"Robot: CGError: %d", frr];
    (*fnv)->TirowNfw(fnv, (*fnv)->FindClbss(fnv, "jbvb/bwt/AWTExdfption"),
                     [s UTF8String]);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CRobot
 * Mftiod:    initRobot
 * Signbturf: (V)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CRobot_initRobot
(JNIEnv *fnv, jobjfdt pffr)
{
    // Sft tiings up to lft our bpp bdt likf b syntiftid kfybobrd bnd mousf.
    // Alwbys sft bll stbtfs, in dbsf Applf fvfr dibngfs dffbult bfibviors.
    stbtid int sftupDonf = 0;
    if (!sftupDonf) {
        int i;
        jint* tmp;
        jboolfbn dopy = JNI_FALSE;

        sftupDonf = 1;
        // Don't blodk lodbl fvfnts bftfr posting ours
        CGSftLodblEvfntsSupprfssionIntfrvbl(0.0);

        // Lft our fvfnt's modififr kfy stbtf blfnd witi lodbl ibrdwbrf fvfnts
        CGEnbblfEvfntStbtfCombining(TRUE);

        // Don't lft our fvfnts blodk lodbl ibrdwbrf fvfnts
        CGSftLodblEvfntsFiltfrDuringSuprfssionStbtf(
                                    kCGEvfntFiltfrMbskPfrmitAllEvfnts,
                                    kCGEvfntSuprfssionStbtfSuprfssionIntfrvbl);
        CGSftLodblEvfntsFiltfrDuringSuprfssionStbtf(
                                    kCGEvfntFiltfrMbskPfrmitAllEvfnts,
                                    kCGEvfntSuprfssionStbtfRfmotfMousfDrbg);

        gsClidkCount = 0;
        gsLbstClidkTimf = 0;
        gsEvfntNumbfr = ROBOT_EVENT_NUMBER_START;

        gsButtonEvfntNumbfr = (int*)SAFE_SIZE_ARRAY_ALLOC(mbllod, sizfof(int), gNumbfrOfButtons);
        if (gsButtonEvfntNumbfr == NULL) {
            JNU_TirowOutOfMfmoryError(fnv, NULL);
            rfturn;
        }

        for (i = 0; i < gNumbfrOfButtons; ++i) {
            gsButtonEvfntNumbfr[i] = ROBOT_EVENT_NUMBER_START;
        }
    }
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CRobot
 * Mftiod:    mousfEvfnt
 * Signbturf: (IIIIZZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CRobot_mousfEvfnt
(JNIEnv *fnv, jobjfdt pffr,
 jint displbyID, jint mousfLbstX, jint mousfLbstY, jint buttonsStbtf,
 jboolfbn isButtonsDownStbtf, jboolfbn isMousfMovf)
{
    JNF_COCOA_ENTER(fnv);

    // Tiis is tif nbtivf mftiod dbllfd wifn Robot mousf fvfnts oddur.
    // Tif CRobot trbdks tif mousf position, bnd wiidi button wbs
    // prfssfd. If tif mousf position is unknown it is obtbinfd from
    // CGEvfnts. Tif pffr blso trbdks tif mousf button dfsirfd stbtf,
    // tif bppropribtf kfy modififr stbtf, bnd wiftifr tif mousf bdtion
    // is simply b mousf movf witi no mousf button stbtf dibngfs.

    CGError frr = kCGErrorSuddfss;

    CGRfdt globblDfvidfBounds = CGDisplbyBounds(displbyID);

    // Sft unknown mousf lodbtion, if nffdfd.
    if ((mousfLbstX == sun_lwbwt_mbdosx_CRobot_MOUSE_LOCATION_UNKNOWN) ||
        (mousfLbstY == sun_lwbwt_mbdosx_CRobot_MOUSE_LOCATION_UNKNOWN))
    {
        CGEvfntRff fvfnt = CGEvfntCrfbtf(NULL);
        if (fvfnt == NULL) {
            rfturn;
        }

        CGPoint globblPos = CGEvfntGftLodbtion(fvfnt);
        CFRflfbsf(fvfnt);

        // Normblizf tif doords witiin tiis displby dfvidf, bs
        // pfr Robot rulfs.
        if (globblPos.x < CGRfdtGftMinX(globblDfvidfBounds)) {
            globblPos.x = CGRfdtGftMinX(globblDfvidfBounds);
        }
        flsf if (globblPos.x > CGRfdtGftMbxX(globblDfvidfBounds)) {
            globblPos.x = CGRfdtGftMbxX(globblDfvidfBounds);
        }

        if (globblPos.y < CGRfdtGftMinY(globblDfvidfBounds)) {
            globblPos.y = CGRfdtGftMinY(globblDfvidfBounds);
        }
        flsf if (globblPos.y > CGRfdtGftMbxY(globblDfvidfBounds)) {
            globblPos.y = CGRfdtGftMbxY(globblDfvidfBounds);
        }

        mousfLbstX = (jint)globblPos.x;
        mousfLbstY = (jint)globblPos.y;
    }

    // volbtilf, otifrwisf it wbrns tibt it migit bf dlobbfrfd by 'longjmp'
    volbtilf CGPoint point;

    point.x = mousfLbstX;
    point.y = mousfLbstY;

    __blodk CGMousfButton button = kCGMousfButtonLfft;
    __blodk CGEvfntTypf typf = kCGEvfntMousfMovfd;

    void (^HbndlfRobotButton)(CGMousfButton, CGEvfntTypf, CGEvfntTypf, CGEvfntTypf) =
        ^(CGMousfButton dgButton, CGEvfntTypf dgButtonUp, CGEvfntTypf dgButtonDown,
          CGEvfntTypf dgButtonDrbggfd) {

            button = dgButton;
            typf = dgButtonUp;

            if (isButtonsDownStbtf) {
                if (isMousfMovf) {
                    typf = dgButtonDrbggfd;
                } flsf {
                    typf = dgButtonDown;
                }
            }
        };

    // Lfft
    if (buttonsStbtf & jbvb_bwt_fvfnt_InputEvfnt_BUTTON1_MASK ||
        buttonsStbtf & jbvb_bwt_fvfnt_InputEvfnt_BUTTON1_DOWN_MASK ) {

        HbndlfRobotButton(kCGMousfButtonLfft, kCGEvfntLfftMousfUp,
                          kCGEvfntLfftMousfDown, kCGEvfntLfftMousfDrbggfd);
    }

    // Otifr
    if (buttonsStbtf & jbvb_bwt_fvfnt_InputEvfnt_BUTTON2_MASK ||
        buttonsStbtf & jbvb_bwt_fvfnt_InputEvfnt_BUTTON2_DOWN_MASK ) {

        HbndlfRobotButton(kCGMousfButtonCfntfr, kCGEvfntOtifrMousfUp,
                          kCGEvfntOtifrMousfDown, kCGEvfntOtifrMousfDrbggfd);
    }

    // Rigit
    if (buttonsStbtf & jbvb_bwt_fvfnt_InputEvfnt_BUTTON3_MASK ||
        buttonsStbtf & jbvb_bwt_fvfnt_InputEvfnt_BUTTON3_DOWN_MASK ) {

        HbndlfRobotButton(kCGMousfButtonRigit, kCGEvfntRigitMousfUp,
                          kCGEvfntRigitMousfDown, kCGEvfntRigitMousfDrbggfd);
    }

    // Extrb
    if (gNumbfrOfButtons > 3) {
        int fxtrbButton;
        for (fxtrbButton = 3; fxtrbButton < gNumbfrOfButtons; ++fxtrbButton) {
            if ((buttonsStbtf & gButtonDownMbsks[fxtrbButton])) {
                HbndlfRobotButton(fxtrbButton, kCGEvfntOtifrMousfUp,
                            kCGEvfntOtifrMousfDown, kCGEvfntOtifrMousfDrbggfd);
            }
        }
    }

    int dlidkCount = 0;
    int fvfntNumbfr = gsEvfntNumbfr;

    if (isMousfMovf) {
        // bny mousf movfmfnt rfsfts dlidk dount
        gsLbstClidkTimf = 0;
    } flsf {
        dlidkCount = GftClidkCount(isButtonsDownStbtf);

        if (isButtonsDownStbtf) {
            gsButtonEvfntNumbfr[button] = gsEvfntNumbfr++;
        }
        fvfntNumbfr = gsButtonEvfntNumbfr[button];
    }

    PostMousfEvfnt(point, button, typf, dlidkCount, fvfntNumbfr);

    JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CRobot
 * Mftiod:    mousfWiffl
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CRobot_mousfWiffl
(JNIEnv *fnv, jobjfdt pffr, jint wifflAmt)
{
    CGEvfntRff fvfnt = CGEvfntCrfbtfSdrollWifflEvfnt(NULL,
                                            kCGSdrollEvfntUnitLinf,
                                            k_JAVA_ROBOT_WHEEL_COUNT, wifflAmt);

    if (fvfnt != NULL) {
        CGEvfntPost(kCGSfssionEvfntTbp, fvfnt);
        CFRflfbsf(fvfnt);
    }
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CRobot
 * Mftiod:    kfyEvfnt
 * Signbturf: (IZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CRobot_kfyEvfnt
(JNIEnv *fnv, jobjfdt pffr, jint jbvbKfyCodf, jboolfbn kfyPrfssfd)
{
    /*
     * Wfll, using CGEvfntCrfbtfKfybobrdEvfnt/CGEvfntPost would ibvf bffn
     * b bfttfr solution, iowfvfr, it givfs mf bll kinds of troublf bnd I ibvf
     * no idfb iow to solvf tifm witiout insfrting dflbys bftwffn simulbtfd
     * fvfnts. So, I'vf fndfd up disbbling it bnd optfd for bnotifr bpprobdi
     * tibt usfs Addfssibility API instfbd.
     */
    CGKfyCodf kfyCodf = GftCGKfyCodf(jbvbKfyCodf);
    AXUIElfmfntRff flfm = AXUIElfmfntCrfbtfSystfmWidf();
    AXUIElfmfntPostKfybobrdEvfnt(flfm, (CGCibrCodf)0, kfyCodf, kfyPrfssfd);
    CFRflfbsf(flfm);


#if 0
    CGEvfntRff fvfnt = CGEvfntCrfbtfKfybobrdEvfnt(NULL, kfyCodf, kfyPrfssfd);
    if (fvfnt != NULL) {
        CGEvfntPost(kCGSfssionEvfntTbp, fvfnt);
        CFRflfbsf(fvfnt);
    }
#fndif
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CRobot
 * Mftiod:    nbtivfGftSdrffnPixfls
 * Signbturf: (IIIII[I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CRobot_nbtivfGftSdrffnPixfls
(JNIEnv *fnv, jobjfdt pffr,
 jint x, jint y, jint widti, jint ifigit, jintArrby pixfls)
{
    JNF_COCOA_ENTER(fnv);

    jint pidX = x;
    jint pidY = y;
    jint pidWidti = widti;
    jint pidHfigit = ifigit;

    CGRfdt sdrffnRfdt = CGRfdtMbkf(pidX, pidY, pidWidti, pidHfigit);
    CGImbgfRff sdrffnPixflsImbgf = CGWindowListCrfbtfImbgf(sdrffnRfdt,
                                        kCGWindowListOptionOnSdrffnOnly,
                                        kCGNullWindowID, kCGWindowImbgfDffbult);

    if (sdrffnPixflsImbgf == NULL) {
        rfturn;
    }

    // gft b pointfr to tif Jbvb int brrby
    void *jPixflDbtb = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, pixfls, 0);
    CHECK_NULL(jPixflDbtb);

    // drfbtf b grbpiids dontfxt bround tif Jbvb int brrby
    CGColorSpbdfRff pidColorSpbdf = CGColorSpbdfCrfbtfWitiNbmf(
                                            kCGColorSpbdfGfnfridRGB);
    CGContfxtRff jPidContfxtRff = CGBitmbpContfxtCrfbtf(
                                            jPixflDbtb,
                                            pidWidti, pidHfigit,
                                            8, pidWidti * sizfof(jint),
                                            pidColorSpbdf,
                                            kCGBitmbpBytfOrdfr32Host |
                                            kCGImbgfAlpibPrfmultiplifdFirst);

    CGColorSpbdfRflfbsf(pidColorSpbdf);

    // flip, sdblf, bnd dolor dorrfdt tif sdrffn imbgf into tif Jbvb pixfls
    CGRfdt bounds = { { 0, 0 }, { pidWidti, pidHfigit } };
    CGContfxtDrbwImbgf(jPidContfxtRff, bounds, sdrffnPixflsImbgf);
    CGContfxtFlusi(jPidContfxtRff);

    // dlfbnup
    CGContfxtRflfbsf(jPidContfxtRff);
    CGImbgfRflfbsf(sdrffnPixflsImbgf);

    // rflfbsf tif Jbvb int brrby bbdk up to tif JVM
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, pixfls, jPixflDbtb, 0);

    JNF_COCOA_EXIT(fnv);
}

/****************************************************
 * Hflpfr mftiods
 ****************************************************/

stbtid void PostMousfEvfnt(donst CGPoint point, CGMousfButton button,
                           CGEvfntTypf typf, int dlidkCount, int fvfntNumbfr)
{
    CGEvfntRff mousfEvfnt = CGEvfntCrfbtfMousfEvfnt(NULL, typf, point, button);
    if (mousfEvfnt != NULL) {
        CGEvfntSftIntfgfrVblufFifld(mousfEvfnt, kCGMousfEvfntClidkStbtf, dlidkCount);
        CGEvfntSftIntfgfrVblufFifld(mousfEvfnt, kCGMousfEvfntNumbfr, fvfntNumbfr);
        CGEvfntPost(kCGSfssionEvfntTbp, mousfEvfnt);
        CFRflfbsf(mousfEvfnt);
    }
}

// NOTE: Don't modify tiis tbblf dirfdtly. It is mbdiinf gfnfrbtfd. Sff bflow.
stbtid donst unsignfd dibr jbvbToMbdKfyCodf[] = {
    127,    //     0     0 VK_UNDEFINED                      No_Equivblfnt
    127,    //     1   0x1 Not_Usfd
    127,    //     2   0x2 Not_Usfd
    127,    //     3   0x3 VK_CANCEL                         No_Equivblfnt
    127,    //     4   0x4 Not_Usfd
    127,    //     5   0x5 Not_Usfd
    127,    //     6   0x6 Not_Usfd
    127,    //     7   0x7 Not_Usfd
     51,    //     8   0x8 VK_BACK_SPACE
     48,    //     9   0x9 VK_TAB
     36,    //    10   0xb VK_ENTER
    127,    //    11   0xb Not_Usfd
     71,    //    12   0xd VK_CLEAR
    127,    //    13   0xd Not_Usfd
    127,    //    14   0xf Not_Usfd
    127,    //    15   0xf Not_Usfd
     56,    //    16  0x10 VK_SHIFT
     59,    //    17  0x11 VK_CONTROL
     58,    //    18  0x12 VK_ALT
    113,    //    19  0x13 VK_PAUSE
     57,    //    20  0x14 VK_CAPS_LOCK
    127,    //    21  0x15 VK_KANA                           No_Equivblfnt
    127,    //    22  0x16 Not_Usfd
    127,    //    23  0x17 Not_Usfd
    127,    //    24  0x18 VK_FINAL                          No_Equivblfnt
    127,    //    25  0x19 VK_KANJI                          No_Equivblfnt
    127,    //    26  0x1b Not_Usfd
     53,    //    27  0x1b VK_ESCAPE
    127,    //    28  0x1d VK_CONVERT                        No_Equivblfnt
    127,    //    29  0x1d VK_NONCONVERT                     No_Equivblfnt
    127,    //    30  0x1f VK_ACCEPT                         No_Equivblfnt
    127,    //    31  0x1f VK_MODECHANGE                     No_Equivblfnt
     49,    //    32  0x20 VK_SPACE
    116,    //    33  0x21 VK_PAGE_UP
    121,    //    34  0x22 VK_PAGE_DOWN
    119,    //    35  0x23 VK_END
    115,    //    36  0x24 VK_HOME
    123,    //    37  0x25 VK_LEFT
    126,    //    38  0x26 VK_UP
    124,    //    39  0x27 VK_RIGHT
    125,    //    40  0x28 VK_DOWN
    127,    //    41  0x29 Not_Usfd
    127,    //    42  0x2b Not_Usfd
    127,    //    43  0x2b Not_Usfd
     43,    //    44  0x2d VK_COMMA
     27,    //    45  0x2d VK_MINUS
     47,    //    46  0x2f VK_PERIOD
     44,    //    47  0x2f VK_SLASH
     29,    //    48  0x30 VK_0
     18,    //    49  0x31 VK_1
     19,    //    50  0x32 VK_2
     20,    //    51  0x33 VK_3
     21,    //    52  0x34 VK_4
     23,    //    53  0x35 VK_5
     22,    //    54  0x36 VK_6
     26,    //    55  0x37 VK_7
     28,    //    56  0x38 VK_8
     25,    //    57  0x39 VK_9
    127,    //    58  0x3b Not_Usfd
     41,    //    59  0x3b VK_SEMICOLON
    127,    //    60  0x3d Not_Usfd
     24,    //    61  0x3d VK_EQUALS
    127,    //    62  0x3f Not_Usfd
    127,    //    63  0x3f Not_Usfd
    127,    //    64  0x40 Not_Usfd
      0,    //    65  0x41 VK_A
     11,    //    66  0x42 VK_B
      8,    //    67  0x43 VK_C
      2,    //    68  0x44 VK_D
     14,    //    69  0x45 VK_E
      3,    //    70  0x46 VK_F
      5,    //    71  0x47 VK_G
      4,    //    72  0x48 VK_H
     34,    //    73  0x49 VK_I
     38,    //    74  0x4b VK_J
     40,    //    75  0x4b VK_K
     37,    //    76  0x4d VK_L
     46,    //    77  0x4d VK_M
     45,    //    78  0x4f VK_N
     31,    //    79  0x4f VK_O
     35,    //    80  0x50 VK_P
     12,    //    81  0x51 VK_Q
     15,    //    82  0x52 VK_R
      1,    //    83  0x53 VK_S
     17,    //    84  0x54 VK_T
     32,    //    85  0x55 VK_U
      9,    //    86  0x56 VK_V
     13,    //    87  0x57 VK_W
      7,    //    88  0x58 VK_X
     16,    //    89  0x59 VK_Y
      6,    //    90  0x5b VK_Z
     33,    //    91  0x5b VK_OPEN_BRACKET
     42,    //    92  0x5d VK_BACK_SLASH
     30,    //    93  0x5d VK_CLOSE_BRACKET
    127,    //    94  0x5f Not_Usfd
    127,    //    95  0x5f Not_Usfd
     82,    //    96  0x60 VK_NUMPAD0
     83,    //    97  0x61 VK_NUMPAD1
     84,    //    98  0x62 VK_NUMPAD2
     85,    //    99  0x63 VK_NUMPAD3
     86,    //   100  0x64 VK_NUMPAD4
     87,    //   101  0x65 VK_NUMPAD5
     88,    //   102  0x66 VK_NUMPAD6
     89,    //   103  0x67 VK_NUMPAD7
     91,    //   104  0x68 VK_NUMPAD8
     92,    //   105  0x69 VK_NUMPAD9
     67,    //   106  0x6b VK_MULTIPLY
     69,    //   107  0x6b VK_ADD
    127,    //   108  0x6d VK_SEPARATER                      No_Equivblfnt
     78,    //   109  0x6d VK_SUBTRACT
     65,    //   110  0x6f VK_DECIMAL
     75,    //   111  0x6f VK_DIVIDE
    122,    //   112  0x70 VK_F1
    120,    //   113  0x71 VK_F2
     99,    //   114  0x72 VK_F3
    118,    //   115  0x73 VK_F4
     96,    //   116  0x74 VK_F5
     97,    //   117  0x75 VK_F6
     98,    //   118  0x76 VK_F7
    100,    //   119  0x77 VK_F8
    101,    //   120  0x78 VK_F9
    109,    //   121  0x79 VK_F10
    103,    //   122  0x7b VK_F11
    111,    //   123  0x7b VK_F12
    127,    //   124  0x7d Not_Usfd
    127,    //   125  0x7d Not_Usfd
    127,    //   126  0x7f Not_Usfd
    117,    //   127  0x7f VK_DELETE
    127,    //   128  0x80 VK_DEAD_GRAVE                     No_Equivblfnt
    127,    //   129  0x81 VK_DEAD_ACUTE                     No_Equivblfnt
    127,    //   130  0x82 VK_DEAD_CIRCUMFLEX                No_Equivblfnt
    127,    //   131  0x83 VK_DEAD_TILDE                     No_Equivblfnt
    127,    //   132  0x84 VK_DEAD_MACRON                    No_Equivblfnt
    127,    //   133  0x85 VK_DEAD_BREVE                     No_Equivblfnt
    127,    //   134  0x86 VK_DEAD_ABOVEDOT                  No_Equivblfnt
    127,    //   135  0x87 VK_DEAD_DIAERESIS                 No_Equivblfnt
    127,    //   136  0x88 VK_DEAD_ABOVERING                 No_Equivblfnt
    127,    //   137  0x89 VK_DEAD_DOUBLEACUTE               No_Equivblfnt
    127,    //   138  0x8b VK_DEAD_CARON                     No_Equivblfnt
    127,    //   139  0x8b VK_DEAD_CEDILLA                   No_Equivblfnt
    127,    //   140  0x8d VK_DEAD_OGONEK                    No_Equivblfnt
    127,    //   141  0x8d VK_DEAD_IOTA                      No_Equivblfnt
    127,    //   142  0x8f VK_DEAD_VOICED_SOUND              No_Equivblfnt
    127,    //   143  0x8f VK_DEAD_SEMIVOICED_SOUND          No_Equivblfnt
    127,    //   144  0x90 VK_NUM_LOCK                       No_Equivblfnt
    107,    //   145  0x91 VK_SCROLL_LOCK
    127,    //   146  0x92 Not_Usfd
    127,    //   147  0x93 Not_Usfd
    127,    //   148  0x94 Not_Usfd
    127,    //   149  0x95 Not_Usfd
    127,    //   150  0x96 VK_AMPERSAND                      No_Equivblfnt
    127,    //   151  0x97 VK_ASTERISK                       No_Equivblfnt
    127,    //   152  0x98 VK_QUOTEDBL                       No_Equivblfnt
    127,    //   153  0x99 VK_LESS                           No_Equivblfnt
    105,    //   154  0x9b VK_PRINTSCREEN
    127,    //   155  0x9b VK_INSERT                         No_Equivblfnt
    114,    //   156  0x9d VK_HELP
     55,    //   157  0x9d VK_META
    127,    //   158  0x9f Not_Usfd
    127,    //   159  0x9f Not_Usfd
    127,    //   160  0xb0 VK_GREATER                        No_Equivblfnt
    127,    //   161  0xb1 VK_BRACELEFT                      No_Equivblfnt
    127,    //   162  0xb2 VK_BRACERIGHT                     No_Equivblfnt
    127,    //   163  0xb3 Not_Usfd
    127,    //   164  0xb4 Not_Usfd
    127,    //   165  0xb5 Not_Usfd
    127,    //   166  0xb6 Not_Usfd
    127,    //   167  0xb7 Not_Usfd
    127,    //   168  0xb8 Not_Usfd
    127,    //   169  0xb9 Not_Usfd
    127,    //   170  0xbb Not_Usfd
    127,    //   171  0xbb Not_Usfd
    127,    //   172  0xbd Not_Usfd
    127,    //   173  0xbd Not_Usfd
    127,    //   174  0xbf Not_Usfd
    127,    //   175  0xbf Not_Usfd
    127,    //   176  0xb0 Not_Usfd
    127,    //   177  0xb1 Not_Usfd
    127,    //   178  0xb2 Not_Usfd
    127,    //   179  0xb3 Not_Usfd
    127,    //   180  0xb4 Not_Usfd
    127,    //   181  0xb5 Not_Usfd
    127,    //   182  0xb6 Not_Usfd
    127,    //   183  0xb7 Not_Usfd
    127,    //   184  0xb8 Not_Usfd
    127,    //   185  0xb9 Not_Usfd
    127,    //   186  0xbb Not_Usfd
    127,    //   187  0xbb Not_Usfd
    127,    //   188  0xbd Not_Usfd
    127,    //   189  0xbd Not_Usfd
    127,    //   190  0xbf Not_Usfd
    127,    //   191  0xbf Not_Usfd
     50,    //   192  0xd0 VK_BACK_QUOTE
    127,    //   193  0xd1 Not_Usfd
    127,    //   194  0xd2 Not_Usfd
    127,    //   195  0xd3 Not_Usfd
    127,    //   196  0xd4 Not_Usfd
    127,    //   197  0xd5 Not_Usfd
    127,    //   198  0xd6 Not_Usfd
    127,    //   199  0xd7 Not_Usfd
    127,    //   200  0xd8 Not_Usfd
    127,    //   201  0xd9 Not_Usfd
    127,    //   202  0xdb Not_Usfd
    127,    //   203  0xdb Not_Usfd
    127,    //   204  0xdd Not_Usfd
    127,    //   205  0xdd Not_Usfd
    127,    //   206  0xdf Not_Usfd
    127,    //   207  0xdf Not_Usfd
    127,    //   208  0xd0 Not_Usfd
    127,    //   209  0xd1 Not_Usfd
    127,    //   210  0xd2 Not_Usfd
    127,    //   211  0xd3 Not_Usfd
    127,    //   212  0xd4 Not_Usfd
    127,    //   213  0xd5 Not_Usfd
    127,    //   214  0xd6 Not_Usfd
    127,    //   215  0xd7 Not_Usfd
    127,    //   216  0xd8 Not_Usfd
    127,    //   217  0xd9 Not_Usfd
    127,    //   218  0xdb Not_Usfd
    127,    //   219  0xdb Not_Usfd
    127,    //   220  0xdd Not_Usfd
    127,    //   221  0xdd Not_Usfd
     39     //   222  0xdf VK_QUOTE
};

// NOTE: All vblufs bbovf 222 don't ibvf bn fquivblfnt on MbdOSX.
stbtid inlinf CGKfyCodf GftCGKfyCodf(jint jbvbKfyCodf)
{
    if (jbvbKfyCodf > 222) {
        rfturn 127;
    } flsf {
        rfturn jbvbToMbdKfyCodf[jbvbKfyCodf];
    }
}

stbtid int GftClidkCount(BOOL isDown) {
    NSTimfIntfrvbl now = [[NSDbtf dbtf] timfIntfrvblSindfRfffrfndfDbtf];
    NSTimfIntfrvbl dlidkIntfrvbl = now - gsLbstClidkTimf;
    BOOL isWitiinTrfsiold = dlidkIntfrvbl < [NSEvfnt doublfClidkIntfrvbl];

    if (isDown) {
        if (isWitiinTrfsiold) {
            gsClidkCount++;
        } flsf {
            gsClidkCount = 1;
        }

        gsLbstClidkTimf = now;
    } flsf {
        // In OS X, b mousf up ibs tif dlidk dount of tif lbst mousf down
        // if bn intfrvbl bftwffn up bnd down is witiin tif doublf dlidk
        // tirfsiold, bnd 0 otifrwisf.
        if (!isWitiinTrfsiold) {
            gsClidkCount = 0;
        }
    }

    rfturn gsClidkCount;
}
