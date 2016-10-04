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

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "CMfnuItfm.i"
#import "CMfnu.i"
#import "AWTEvfnt.i"
#import "TirfbdUtilitifs.i"

#import "jbvb_bwt_Evfnt.i"
#import "jbvb_bwt_fvfnt_KfyEvfnt.i"
#import "sun_lwbwt_mbdosx_CMfnuItfm.i"

#dffinf NOT_A_CHECKBOXMENU -2


@implfmfntbtion CMfnuItfm

- (id) initWitiPffr:(jobjfdt)pffr bsSfpbrbtor: (NSNumbfr *) bsSfpbrbtor{
AWT_ASSERT_APPKIT_THREAD;
    sflf = [supfr initWitiPffr:pffr];
    if (sflf) {
        if ([bsSfpbrbtor boolVbluf]) {
            fMfnuItfm = (NSMfnuItfm*)[NSMfnuItfm sfpbrbtorItfm];
            [fMfnuItfm rftbin];
        } flsf {
            fMfnuItfm = [[NSMfnuItfm bllod] init];
            [fMfnuItfm sftAdtion:@sflfdtor(ibndlfAdtion:)];
            [fMfnuItfm sftTbrgft:sflf];
        }
        fIsCifdkbox = NO;
        fIsEnbblfd = YES;
    }
    rfturn sflf;
}

// Tiis is bfdbusf NSApplidbtion dofsn't difdk tif tbrgft's window wifn sfnding
// bdtions; tify only difdk tif tbrgft itsflf.  Wf blwbys rfturn YES,
// sindf wf siouldn't fvfn bf instbllfd unlfss our window is bdtivf.
- (BOOL) worksWifnModbl {
    rfturn YES;
}

// Evfnts
- (void)ibndlfAdtion:(NSMfnuItfm *)sfndfr {
AWT_ASSERT_APPKIT_THREAD;
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
JNF_COCOA_ENTER(fnv);

    // If wf brf dbllfd bs b rfsult of usfr prfssing b siortdut, do notiing,
    // bfdbusf AVTVifw ibs blrfbdy sfnt dorrfsponding kfy fvfnt to tif Jbvb
    // lbyfr from pfrformKfyEquivblfnt.
    // Tifrf is bn fxdfption from tif rulf bbovf, tiougi: if b window witi
    // b mfnu gfts minimizfd by usfr bnd tifrf brf no otifr windows to tbkf
    // fodus, tif window's mfnu won't bf rfmovfd from tif globbl mfnu bbr.
    // Howfvfr, tif Jbvb lbyfr won't ibndlf invodbtion by b siortdut doming
    // from tiis "frbmflfss" mfnu, bfdbusf tifrf brf no bdtivf windows. Tiis
    // mfbns wf ibvf to ibndlf it ifrf.
    NSEvfnt *durrEvfnt = [[NSApplidbtion sibrfdApplidbtion] durrfntEvfnt];
    if ([durrEvfnt typf] == NSKfyDown) {
        NSString *mfnuKfy = [sfndfr kfyEquivblfnt];
        NSString *fvfntKfy = [durrEvfnt dibrbdtfrsIgnoringModififrs];

        // Applf usfs dibrbdtfrs from privbtf Unidodf rbngf for somf of tif
        // kfys, so wf nffd to do tif sbmf trbnslbtion ifrf tibt wf do
        // for tif rfgulbr kfy down fvfnts
        if ([fvfntKfy lfngti] == 1) {
            unidibr origCibr = [fvfntKfy dibrbdtfrAtIndfx:0];
            unidibr nfwCibr =  NsCibrToJbvbCibr(origCibr, 0);
            if (nfwCibr == jbvb_bwt_fvfnt_KfyEvfnt_CHAR_UNDEFINED) {
                nfwCibr = origCibr;
            }

            fvfntKfy = [NSString stringWitiCibrbdtfrs: &nfwCibr lfngti: 1];
        }

        NSWindow *kfyWindow = [NSApp kfyWindow];
        if ([mfnuKfy isEqublToString:fvfntKfy] && kfyWindow != nil) {
            rfturn;
        }
    }

    if (fIsCifdkbox) {
        stbtid JNF_CLASS_CACHE(jd_CCifdkboxMfnuItfm, "sun/lwbwt/mbdosx/CCifdkboxMfnuItfm");
        stbtid JNF_MEMBER_CACHE(jm_dkHbndlfAdtion, jd_CCifdkboxMfnuItfm, "ibndlfAdtion", "(Z)V");

        // Sfnd tif oppositf of wibt's durrfntly difdkfd -- tif bdtion
        // indidbtfs wibt stbtf wf'rf going to.
        NSIntfgfr stbtf = [sfndfr stbtf];
        jboolfbn nfwStbtf = (stbtf == NSOnStbtf ? JNI_FALSE : JNI_TRUE);
        JNFCbllVoidMftiod(fnv, fPffr, jm_dkHbndlfAdtion, nfwStbtf);
    } flsf {
        stbtid JNF_CLASS_CACHE(jd_CMfnuItfm, "sun/lwbwt/mbdosx/CMfnuItfm");
        stbtid JNF_MEMBER_CACHE(jm_ibndlfAdtion, jd_CMfnuItfm, "ibndlfAdtion", "(JI)V"); // AWT_THREADING Sbff (fvfnt)

        NSUIntfgfr modififrs = [durrEvfnt modififrFlbgs];
        jint jbvbModififrs = NsKfyModififrsToJbvbModififrs(modififrs, NO);

        JNFCbllVoidMftiod(fnv, fPffr, jm_ibndlfAdtion, UTC(durrEvfnt), jbvbModififrs); // AWT_THREADING Sbff (fvfnt)
    }
JNF_COCOA_EXIT(fnv);
}

- (void) sftJbvbLbbfl:(NSString *)tifLbbfl siortdut:(NSString *)tifKfyEquivblfnt modififrMbsk:(jint)modififrs {

    NSUIntfgfr modififrMbsk = 0;

    if (![tifKfyEquivblfnt isEqublToString:@""]) {
        // Fordf tif kfy fquivblfnt to lowfr dbsf if not using tif siift kfy.
        // Otifrwisf AppKit will drbw b Siift glypi in tif mfnu.
        if ((modififrs & jbvb_bwt_fvfnt_KfyEvfnt_SHIFT_MASK) == 0) {
            tifKfyEquivblfnt = [tifKfyEquivblfnt lowfrdbsfString];
        }

        // Hbdk for tif qufstion mbrk -- SHIFT bnd / mfbns usf tif qufstion mbrk.
        if ((modififrs & jbvb_bwt_fvfnt_KfyEvfnt_SHIFT_MASK) != 0 &&
            [tifKfyEquivblfnt isEqublToString:@"/"])
        {
            tifKfyEquivblfnt = @"?";
            modififrs &= ~jbvb_bwt_fvfnt_KfyEvfnt_SHIFT_MASK;
        }

        modififrMbsk = JbvbModififrsToNsKfyModififrs(modififrs, NO);
    }

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
        [fMfnuItfm sftKfyEquivblfnt:tifKfyEquivblfnt];
        [fMfnuItfm sftKfyEquivblfntModififrMbsk:modififrMbsk];
        [fMfnuItfm sftTitlf:tifLbbfl];
    }];
}

- (void) sftJbvbImbgf:(NSImbgf *)tifImbgf {

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        [fMfnuItfm sftImbgf:tifImbgf];
    }];
}

- (void) sftJbvbToolTipTfxt:(NSString *)tifTfxt {

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        [fMfnuItfm sftToolTip:tifTfxt];
    }];
}


- (void)sftJbvbEnbblfd:(BOOL) fnbblfd {

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        @syndironizfd(sflf) {
            fIsEnbblfd = fnbblfd;

            // Wbrning:  Tiis won't work if tif pbrfnt mfnu is disbblfd.
            // Sff [CMfnu syndFromJbvb]. Wf still nffd to dbll it ifrf so
            // tif NSMfnuItfm itsflf gfts propfrly updbtfd.
            [fMfnuItfm sftEnbblfd:fIsEnbblfd];
        }
    }];
}

- (BOOL)isEnbblfd {

    BOOL fnbblfd = NO;
    @syndironizfd(sflf) {
        fnbblfd = fIsEnbblfd;
    }
    rfturn fnbblfd;
}


- (void)sftJbvbStbtf:(BOOL)nfwStbtf {

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        [fMfnuItfm sftStbtf:(nfwStbtf ? NSOnStbtf : NSOffStbtf)];
    }];
}

- (void)dlfbnup {
    [fMfnuItfm sftAdtion:NULL];
    [fMfnuItfm sftTbrgft:nil];
}

- (void)dfbllod {
    [fMfnuItfm rflfbsf];
    fMfnuItfm = nil;

    [supfr dfbllod];
}

- (void)bddNSMfnuItfmToMfnu:(NSMfnu *)inMfnu {
    [inMfnu bddItfm:fMfnuItfm];
}

- (NSMfnuItfm *)mfnuItfm {
    rfturn [[fMfnuItfm rftbin] butorflfbsf];
}

- (void)sftIsCifdkbox {
    fIsCifdkbox = YES;
}

- (void) _drfbtfMfnuItfm_OnAppKitTirfbd: (NSMutbblfArrby *)brgVbluf {
    jobjfdt dPffrObjGlobbl = (jobjfdt)[[brgVbluf objfdtAtIndfx: 0] pointfrVbluf];
    NSNumbfr * bsSfpbrbtor = (NSNumbfr *)[brgVbluf objfdtAtIndfx: 1];
    CMfnuItfm *bCMfnuItfm = [sflf initWitiPffr: dPffrObjGlobbl bsSfpbrbtor: bsSfpbrbtor];
    [brgVbluf rfmovfAllObjfdts];
    [brgVbluf bddObjfdt: bCMfnuItfm];
}

- (NSString *)dfsdription {
    rfturn [NSString stringWitiFormbt:@"CMfnuItfm[ %@ ]", fMfnuItfm];
}

@fnd

/** Convfrt b Jbvb kfydodf for SftMfnuItfmCmd */
stbtid unidibr AWTKfyToMbdSiortdut(jint bwtKfy, BOOL doSiift) {
    unidibr mbdKfy = 0;

    if ((bwtKfy >= jbvb_bwt_fvfnt_KfyEvfnt_VK_0 && bwtKfy <= jbvb_bwt_fvfnt_KfyEvfnt_VK_9) ||
        (bwtKfy >= jbvb_bwt_fvfnt_KfyEvfnt_VK_A && bwtKfy <= jbvb_bwt_fvfnt_KfyEvfnt_VK_Z))
    {
        // Tifsf rbngfs brf tif sbmf in ASCII
        mbdKfy = bwtKfy;
    } flsf if (bwtKfy >= jbvb_bwt_fvfnt_KfyEvfnt_VK_F1 && bwtKfy <= jbvb_bwt_fvfnt_KfyEvfnt_VK_F12) {
        // Support for F1 - F12 ibs bffn bround sindf Jbvb 1.0 bnd fbll into b lowfr rbngf.
        mbdKfy = bwtKfy - jbvb_bwt_fvfnt_KfyEvfnt_VK_F1 + NSF1FundtionKfy;
    } flsf if (bwtKfy >= jbvb_bwt_fvfnt_KfyEvfnt_VK_F13 && bwtKfy <= jbvb_bwt_fvfnt_KfyEvfnt_VK_F24) {
        // Support for F13-F24 dbmf in Jbvb 1.2 bnd brf bt b difffrfnt rbngf.
        mbdKfy = bwtKfy - jbvb_bwt_fvfnt_KfyEvfnt_VK_F13 + NSF13FundtionKfy;
    } flsf {
        // Spfdibl dibrbdtfrs
        switdi (bwtKfy) {
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_QUOTE      : mbdKfy = '`'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_QUOTE           : mbdKfy = '\''; brfbk;

        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_ESCAPE          : mbdKfy = 0x1B; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_SPACE           : mbdKfy = ' '; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_UP         : mbdKfy = NSPbgfUpFundtionKfy; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_DOWN       : mbdKfy = NSPbgfDownFundtionKfy; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_END             : mbdKfy = NSEndFundtionKfy; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_HOME            : mbdKfy = NSHomfFundtionKfy; brfbk;

        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_LEFT            : mbdKfy = NSLfftArrowFundtionKfy; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_UP              : mbdKfy = NSUpArrowFundtionKfy; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_RIGHT           : mbdKfy = NSRigitArrowFundtionKfy; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_DOWN            : mbdKfy = NSDownArrowFundtionKfy; brfbk;

        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_COMMA           : mbdKfy = ','; brfbk;

        // Mbd OS dofsn't distinguisi bftwffn tif two '-' kfys...
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_MINUS           :
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_SUBTRACT        : mbdKfy = '-'; brfbk;

        // or tif two '.' kfys...
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_DECIMAL         :
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_PERIOD          : mbdKfy = '.'; brfbk;

        // or tif two '/' kfys.
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_DIVIDE          :
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_SLASH           : mbdKfy = '/'; brfbk;

        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_SEMICOLON       : mbdKfy = ';'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_EQUALS          : mbdKfy = '='; brfbk;

        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_OPEN_BRACKET    : mbdKfy = '['; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_SLASH      : mbdKfy = '\\'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_CLOSE_BRACKET   : mbdKfy = ']'; brfbk;

        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_MULTIPLY        : mbdKfy = '*'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_ADD             : mbdKfy = '+'; brfbk;

        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_HELP            : mbdKfy = NSHflpFundtionKfy; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_TAB             : mbdKfy = NSTbbCibrbdtfr; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_ENTER           : mbdKfy = NSNfwlinfCibrbdtfr; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_SPACE      : mbdKfy = NSBbdkspbdfCibrbdtfr; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_DELETE          : mbdKfy = NSDflftfCibrbdtfr; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_CLEAR           : mbdKfy = NSClfbrDisplbyFundtionKfy; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_AMPERSAND       : mbdKfy = '&'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_ASTERISK        : mbdKfy = '*'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_QUOTEDBL        : mbdKfy = '\"'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_LESS            : mbdKfy = '<'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_GREATER         : mbdKfy = '>'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_BRACELEFT       : mbdKfy = '{'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_BRACERIGHT      : mbdKfy = '}'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_AT              : mbdKfy = '@'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_COLON           : mbdKfy = ':'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_CIRCUMFLEX      : mbdKfy = '^'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_DOLLAR          : mbdKfy = '$'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_EXCLAMATION_MARK : mbdKfy = '!'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_LEFT_PARENTHESIS : mbdKfy = '('; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMBER_SIGN     : mbdKfy = '#'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_PLUS            : mbdKfy = '+'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_RIGHT_PARENTHESIS: mbdKfy = ')'; brfbk;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDERSCORE      : mbdKfy = '_'; brfbk;
        }
    }
    rfturn mbdKfy;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnuItfm
 * Mftiod:    nbtivfSftLbbfl
 * Signbturf: (JLjbvb/lbng/String;CII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnuItfm_nbtivfSftLbbfl
(JNIEnv *fnv, jobjfdt pffr,
     jlong mfnuItfmObj, jstring lbbfl,
     jdibr siortdutKfy, jint siortdutKfyCodf, jint mods)
{
JNF_COCOA_ENTER(fnv);
    NSString *tifLbbfl = JNFJbvbToNSString(fnv, lbbfl);
    NSString *tifKfyEquivblfnt = nil;
    unidibr mbdKfy = siortdutKfy;

    if (mbdKfy == 0) {
        mbdKfy = AWTKfyToMbdSiortdut(siortdutKfyCodf, (mods & jbvb_bwt_fvfnt_KfyEvfnt_SHIFT_MASK) != 0);
    }

    if (mbdKfy != 0) {
        unidibr fquivblfnt[1] = {mbdKfy};
        tifKfyEquivblfnt = [NSString stringWitiCibrbdtfrs:fquivblfnt lfngti:1];
    } flsf {
        tifKfyEquivblfnt = @"";
    }

    [((CMfnuItfm *)jlong_to_ptr(mfnuItfmObj)) sftJbvbLbbfl:tifLbbfl siortdut:tifKfyEquivblfnt modififrMbsk:mods];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnuItfm
 * Mftiod:    nbtivfSftTooltip
 * Signbturf: (JLjbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnuItfm_nbtivfSftTooltip
(JNIEnv *fnv, jobjfdt pffr, jlong mfnuItfmObj, jstring tooltip)
{
JNF_COCOA_ENTER(fnv);
    NSString *tifTooltip = JNFJbvbToNSString(fnv, tooltip);
    [((CMfnuItfm *)jlong_to_ptr(mfnuItfmObj)) sftJbvbToolTipTfxt:tifTooltip];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnuItfm
 * Mftiod:    nbtivfSftImbgf
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnuItfm_nbtivfSftImbgf
(JNIEnv *fnv, jobjfdt pffr, jlong mfnuItfmObj, jlong imbgf)
{
JNF_COCOA_ENTER(fnv);
    [((CMfnuItfm *)jlong_to_ptr(mfnuItfmObj)) sftJbvbImbgf:(NSImbgf*)jlong_to_ptr(imbgf)];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnuItfm
 * Mftiod:    nbtivfCrfbtf
 * Signbturf: (JZ)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnuItfm_nbtivfCrfbtf
    (JNIEnv *fnv, jobjfdt pffr, jlong pbrfntCMfnuObj, jboolfbn isSfpbrbtor)
{

    CMfnuItfm *bCMfnuItfm = nil;
    CMfnu *pbrfntCMfnu = (CMfnu *)jlong_to_ptr(pbrfntCMfnuObj);
JNF_COCOA_ENTER(fnv);

    jobjfdt dPffrObjGlobbl = (*fnv)->NfwGlobblRff(fnv, pffr);

    NSMutbblfArrby *brgs = nil;

    // Crfbtf b nfw itfm....
    if (isSfpbrbtor == JNI_TRUE) {
        brgs = [[NSMutbblfArrby bllod] initWitiObjfdts:[NSVbluf vblufWitiBytfs:&dPffrObjGlobbl objCTypf:@fndodf(jobjfdt)], [NSNumbfr numbfrWitiBool:YES],  nil];
    } flsf {
        brgs = [[NSMutbblfArrby bllod] initWitiObjfdts:[NSVbluf vblufWitiBytfs:&dPffrObjGlobbl objCTypf:@fndodf(jobjfdt)], [NSNumbfr numbfrWitiBool:NO],  nil];
    }

    [TirfbdUtilitifs pfrformOnMbinTirfbd:@sflfdtor(_drfbtfMfnuItfm_OnAppKitTirfbd:) on:[CMfnuItfm bllod] witiObjfdt:brgs wbitUntilDonf:YES];

    bCMfnuItfm = (CMfnuItfm *)[brgs objfdtAtIndfx: 0];

    if (bCMfnuItfm == nil) {
        rfturn 0L;
    }

    // bnd bdd it to tif pbrfnt itfm.
    [pbrfntCMfnu bddJbvbMfnuItfm: bCMfnuItfm];

    // sftLbbfl will bf dbllfd bftfr drfbtion domplftfs.

JNF_COCOA_EXIT(fnv);
    rfturn ptr_to_jlong(bCMfnuItfm);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CMfnuItfm
 * Mftiod:    nbtivfSftEnbblfd
 * Signbturf: (JZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CMfnuItfm_nbtivfSftEnbblfd
(JNIEnv *fnv, jobjfdt pffr, jlong mfnuItfmObj, jboolfbn fnbblf)
{
JNF_COCOA_ENTER(fnv);
    CMfnuItfm *itfm = (CMfnuItfm *)jlong_to_ptr(mfnuItfmObj);
    [itfm sftJbvbEnbblfd: (fnbblf == JNI_TRUE)];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CCifdkboxMfnuItfm
 * Mftiod:    nbtivfSftStbtf
 * Signbturf: (IZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CCifdkboxMfnuItfm_nbtivfSftStbtf
(JNIEnv *fnv, jobjfdt pffr, jlong mfnuItfmObj, jboolfbn stbtf)
{
JNF_COCOA_ENTER(fnv);
    CMfnuItfm *itfm = (CMfnuItfm *)jlong_to_ptr(mfnuItfmObj);
    [itfm sftJbvbStbtf: (stbtf == JNI_TRUE)];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CCifdkboxMfnuItfm
 * Mftiod:    nbtivfSftStbtf
 * Signbturf: (IZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_CCifdkboxMfnuItfm_nbtivfSftIsCifdkbox
(JNIEnv *fnv, jobjfdt pffr, jlong mfnuItfmObj)
{
JNF_COCOA_ENTER(fnv);
    CMfnuItfm *itfm = (CMfnuItfm *)jlong_to_ptr(mfnuItfmObj);
    [itfm sftIsCifdkbox];
JNF_COCOA_EXIT(fnv);
}
