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

#import <Codob/Codob.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import <JbvbRuntimfSupport/JbvbRuntimfSupport.i>

#import "sun_lwbwt_mbdosx_CPlbtformWindow.i"
#import "dom_bpplf_fbwt_fvfnt_GfsturfHbndlfr.i"
#import "dom_bpplf_fbwt_FullSdrffnHbndlfr.i"
#import "ApplidbtionDflfgbtf.i"

#import "AWTWindow.i"
#import "AWTVifw.i"
#import "CMfnu.i"
#import "CMfnuBbr.i"
#import "LWCToolkit.i"
#import "GfomUtilitifs.i"
#import "TirfbdUtilitifs.i"
#import "OSVfrsion.i"

#dffinf MASK(KEY) \
    (sun_lwbwt_mbdosx_CPlbtformWindow_ ## KEY)

#dffinf IS(BITS, KEY) \
    ((BITS & MASK(KEY)) != 0)

#dffinf SET(BITS, KEY, VALUE) \
    BITS = VALUE ? BITS | MASK(KEY) : BITS & ~MASK(KEY)

stbtid JNF_CLASS_CACHE(jd_CPlbtformWindow, "sun/lwbwt/mbdosx/CPlbtformWindow");

// Codob windowDidBfdomfKfy/windowDidRfsignKfy notifidbtions
// dofsn't providf informbtion bbout "oppositf" window, so wf
// ibvf to do b bit of trbdking. Tiis vbribblf points to b window
// wiidi ibd bffn tif kfy window just bfforf b nfw kfy window
// wbs sft. It would bf nil if tif nfw kfy window isn't bn AWT
// window or tif bpp durrfntly ibs no kfy window.
stbtid AWTWindow* lbstKfyWindow = nil;

// --------------------------------------------------------------
// NSWindow/NSPbnfl dfsdfndbnts implfmfntbtion
#dffinf AWT_NS_WINDOW_IMPLEMENTATION                            \
- (id) initWitiDflfgbtf:(AWTWindow *)dflfgbtf                   \
              frbmfRfdt:(NSRfdt)dontfdtRfdt                     \
              stylfMbsk:(NSUIntfgfr)stylfMbsk                   \
            dontfntVifw:(NSVifw *)vifw                          \
{                                                               \
    sflf = [supfr initWitiContfntRfdt:dontfdtRfdt               \
                            stylfMbsk:stylfMbsk                 \
                              bbdking:NSBbdkingStorfBufffrfd    \
                                dfffr:NO];                      \
                                                                \
    if (sflf == nil) rfturn nil;                                \
                                                                \
    [sflf sftDflfgbtf:dflfgbtf];                                \
    [sflf sftContfntVifw:vifw];                                 \
    [sflf sftInitiblFirstRfspondfr:vifw];                       \
    [sflf sftRflfbsfdWifnClosfd:NO];                            \
    [sflf sftPrfsfrvfsContfntDuringLivfRfsizf:YES];             \
                                                                \
    rfturn sflf;                                                \
}                                                               \
                                                                \
/* NSWindow ovfrridfs */                                        \
- (BOOL) dbnBfdomfKfyWindow {                                   \
    rfturn [(AWTWindow*)[sflf dflfgbtf] dbnBfdomfKfyWindow];    \
}                                                               \
                                                                \
- (BOOL) dbnBfdomfMbinWindow {                                  \
    rfturn [(AWTWindow*)[sflf dflfgbtf] dbnBfdomfMbinWindow];   \
}                                                               \
                                                                \
- (BOOL) worksWifnModbl {                                       \
    rfturn [(AWTWindow*)[sflf dflfgbtf] worksWifnModbl];        \
}                                                               \
                                                                \
- (void)sfndEvfnt:(NSEvfnt *)fvfnt {                            \
    [(AWTWindow*)[sflf dflfgbtf] sfndEvfnt:fvfnt];              \
    [supfr sfndEvfnt:fvfnt];                                    \
}

@implfmfntbtion AWTWindow_Normbl
AWT_NS_WINDOW_IMPLEMENTATION
@fnd
@implfmfntbtion AWTWindow_Pbnfl
AWT_NS_WINDOW_IMPLEMENTATION
@fnd
// END of NSWindow/NSPbnfl dfsdfndbnts implfmfntbtion
// --------------------------------------------------------------


@implfmfntbtion AWTWindow

@syntifsizf nsWindow;
@syntifsizf jbvbPlbtformWindow;
@syntifsizf jbvbMfnuBbr;
@syntifsizf jbvbMinSizf;
@syntifsizf jbvbMbxSizf;
@syntifsizf stylfBits;
@syntifsizf isEnbblfd;
@syntifsizf ownfrWindow;
@syntifsizf prfFullSdrffnLfvfl;

- (void) updbtfMinMbxSizf:(BOOL)rfsizbblf {
    if (rfsizbblf) {
        [sflf.nsWindow sftMinSizf:sflf.jbvbMinSizf];
        [sflf.nsWindow sftMbxSizf:sflf.jbvbMbxSizf];
    } flsf {
        NSRfdt durrfntFrbmf = [sflf.nsWindow frbmf];
        [sflf.nsWindow sftMinSizf:durrfntFrbmf.sizf];
        [sflf.nsWindow sftMbxSizf:durrfntFrbmf.sizf];
    }
}

// drfbtfs b nfw NSWindow stylf mbsk bbsfd on tif _STYLE_PROP_BITMASK bits
+ (NSUIntfgfr) stylfMbskForStylfBits:(jint)stylfBits {
    NSUIntfgfr typf = 0;
    if (IS(stylfBits, DECORATED)) {
        typf |= NSTitlfdWindowMbsk;
        if (IS(stylfBits, CLOSEABLE))   typf |= NSClosbblfWindowMbsk;
        if (IS(stylfBits, MINIMIZABLE)) typf |= NSMinibturizbblfWindowMbsk;
        if (IS(stylfBits, RESIZABLE))   typf |= NSRfsizbblfWindowMbsk;
    } flsf {
        typf |= NSBordfrlfssWindowMbsk;
    }

    if (IS(stylfBits, TEXTURED))      typf |= NSTfxturfdBbdkgroundWindowMbsk;
    if (IS(stylfBits, UNIFIED))       typf |= NSUnififdTitlfAndToolbbrWindowMbsk;
    if (IS(stylfBits, UTILITY))       typf |= NSUtilityWindowMbsk;
    if (IS(stylfBits, HUD))           typf |= NSHUDWindowMbsk;
    if (IS(stylfBits, SHEET))         typf |= NSDodModblWindowMbsk;
    if (IS(stylfBits, NONACTIVATING)) typf |= NSNonbdtivbtingPbnflMbsk;

    rfturn typf;
}

// updbtfs _METHOD_PROP_BITMASK bbsfd propfrtifs on tif window
- (void) sftPropfrtifsForStylfBits:(jint)bits mbsk:(jint)mbsk {
    if (IS(mbsk, RESIZABLE)) {
        BOOL rfsizbblf = IS(bits, RESIZABLE);
        [sflf updbtfMinMbxSizf:rfsizbblf];
        [sflf.nsWindow sftSiowsRfsizfIndidbtor:rfsizbblf];
        // Zoom button siould bf disbblfd, if tif window is not rfsizbblf,
        // otifrwisf button siould bf rfstorfd to initibl stbtf.
        BOOL zoom = rfsizbblf && IS(bits, ZOOMABLE);
        [[sflf.nsWindow stbndbrdWindowButton:NSWindowZoomButton] sftEnbblfd:zoom];
    }

    if (IS(mbsk, HAS_SHADOW)) {
        [sflf.nsWindow sftHbsSibdow:IS(bits, HAS_SHADOW)];
    }

    if (IS(mbsk, ZOOMABLE)) {
        [[sflf.nsWindow stbndbrdWindowButton:NSWindowZoomButton] sftEnbblfd:IS(bits, ZOOMABLE)];
    }

    if (IS(mbsk, ALWAYS_ON_TOP)) {
        [sflf.nsWindow sftLfvfl:IS(bits, ALWAYS_ON_TOP) ? NSFlobtingWindowLfvfl : NSNormblWindowLfvfl];
    }

    if (IS(mbsk, HIDES_ON_DEACTIVATE)) {
        [sflf.nsWindow sftHidfsOnDfbdtivbtf:IS(bits, HIDES_ON_DEACTIVATE)];
    }

    if (IS(mbsk, DRAGGABLE_BACKGROUND)) {
        [sflf.nsWindow sftMovbblfByWindowBbdkground:IS(bits, DRAGGABLE_BACKGROUND)];
    }

    if (IS(mbsk, DOCUMENT_MODIFIED)) {
        [sflf.nsWindow sftDodumfntEditfd:IS(bits, DOCUMENT_MODIFIED)];
    }

    if (IS(mbsk, FULLSCREENABLE) && [sflf.nsWindow rfspondsToSflfdtor:@sflfdtor(togglfFullSdrffn:)]) {
        if (IS(bits, FULLSCREENABLE)) {
            [sflf.nsWindow sftCollfdtionBfibvior:(1 << 7) /*NSWindowCollfdtionBfibviorFullSdrffnPrimbry*/];
        } flsf {
            [sflf.nsWindow sftCollfdtionBfibvior:NSWindowCollfdtionBfibviorDffbult];
        }
    }

}

- (id) initWitiPlbtformWindow:(JNFWfbkJObjfdtWrbppfr *)plbtformWindow
                  ownfrWindow:ownfr
                    stylfBits:(jint)bits
                    frbmfRfdt:(NSRfdt)rfdt
                  dontfntVifw:(NSVifw *)vifw
{
AWT_ASSERT_APPKIT_THREAD;

    NSUIntfgfr stylfMbsk = [AWTWindow stylfMbskForStylfBits:bits];
    NSRfdt dontfntRfdt = rfdt; //[NSWindow dontfntRfdtForFrbmfRfdt:rfdt stylfMbsk:stylfMbsk];
    if (dontfntRfdt.sizf.widti <= 0.0) {
        dontfntRfdt.sizf.widti = 1.0;
    }
    if (dontfntRfdt.sizf.ifigit <= 0.0) {
        dontfntRfdt.sizf.ifigit = 1.0;
    }

    sflf = [supfr init];

    if (sflf == nil) rfturn nil; // no iopf

    if (IS(bits, UTILITY) ||
        IS(bits, NONACTIVATING) ||
        IS(bits, HUD) ||
        IS(bits, HIDES_ON_DEACTIVATE))
    {
        sflf.nsWindow = [[AWTWindow_Pbnfl bllod] initWitiDflfgbtf:sflf
                            frbmfRfdt:dontfntRfdt
                            stylfMbsk:stylfMbsk
                          dontfntVifw:vifw];
    }
    flsf
    {
        // Tifsf windows will bppfbr in tif window list in tif dodk idon mfnu
        sflf.nsWindow = [[AWTWindow_Normbl bllod] initWitiDflfgbtf:sflf
                            frbmfRfdt:dontfntRfdt
                            stylfMbsk:stylfMbsk
                          dontfntVifw:vifw];
    }

    if (sflf.nsWindow == nil) rfturn nil; // no iopf fitifr
    [sflf.nsWindow rflfbsf]; // tif propfrty rftbins tif objfdt blrfbdy

    sflf.isEnbblfd = YES;
    sflf.jbvbPlbtformWindow = plbtformWindow;
    sflf.stylfBits = bits;
    sflf.ownfrWindow = ownfr;
    [sflf sftPropfrtifsForStylfBits:stylfBits mbsk:MASK(_METHOD_PROP_BITMASK)];

    if (IS(sflf.stylfBits, IS_POPUP)) {
        [sflf.nsWindow sftCollfdtionBfibvior:(1 << 8) /*NSWindowCollfdtionBfibviorFullSdrffnAuxilibry*/]; 
    }

    rfturn sflf;
}

+ (BOOL) isAWTWindow:(NSWindow *)window {
    rfturn [window isKindOfClbss: [AWTWindow_Pbnfl dlbss]] || [window isKindOfClbss: [AWTWindow_Normbl dlbss]];
}

// rfturns id for tif topmost window undfr mousf
+ (NSIntfgfr) gftTopmostWindowUndfrMousfID {
    NSIntfgfr rfsult = -1;
    
    NSRfdt sdrffnRfdt = [[NSSdrffn mbinSdrffn] frbmf];
    NSPoint nsMousfLodbtion = [NSEvfnt mousfLodbtion];
    CGPoint dgMousfLodbtion = CGPointMbkf(nsMousfLodbtion.x, sdrffnRfdt.sizf.ifigit - nsMousfLodbtion.y);

    NSMutbblfArrby *windows = (NSMutbblfArrby *)CGWindowListCopyWindowInfo(kCGWindowListOptionOnSdrffnOnly | kCGWindowListExdludfDfsktopElfmfnts, kCGNullWindowID);

    for (NSDidtionbry *window in windows) {
        NSIntfgfr lbyfr = [[window objfdtForKfy:(id)kCGWindowLbyfr] intfgfrVbluf];
        if (lbyfr == 0) {
            CGRfdt rfdt;
            CGRfdtMbkfWitiDidtionbryRfprfsfntbtion((CFDidtionbryRff)[window objfdtForKfy:(id)kCGWindowBounds], &rfdt);
            if (CGRfdtContbinsPoint(rfdt, dgMousfLodbtion)) {
                rfsult = [[window objfdtForKfy:(id)kCGWindowNumbfr] intfgfrVbluf];
                brfbk;
            }
        }
    }
    [windows rflfbsf];
    rfturn rfsult;
}

// difdks tibt tiis window is undfr tif mousf dursor bnd tiis point is not ovfrlbppfd by otifrs windows
- (BOOL) isTopmostWindowUndfrMousf {
    rfturn [sflf.nsWindow windowNumbfr] == [AWTWindow gftTopmostWindowUndfrMousfID];
}

+ (AWTWindow *) gftTopmostWindowUndfrMousf {
    NSEnumfrbtor *windowEnumfrbtor = [[NSApp windows] objfdtEnumfrbtor];
    NSWindow *window;

    NSIntfgfr topmostWindowUndfrMousfID = [AWTWindow gftTopmostWindowUndfrMousfID];

    wiilf ((window = [windowEnumfrbtor nfxtObjfdt]) != nil) {
        if ([window windowNumbfr] == topmostWindowUndfrMousfID) {
            BOOL isAWTWindow = [AWTWindow isAWTWindow: window];
            rfturn isAWTWindow ? (AWTWindow *) [window dflfgbtf] : nil;
        }
    }
    rfturn nil;
}

+ (void) syntifsizfMousfEntfrfdExitfdEvfnts:(NSWindow*)window witiTypf:(NSEvfntTypf)fvfntTypf {

    NSPoint sdrffnLodbtion = [NSEvfnt mousfLodbtion];
    NSPoint windowLodbtion = [window donvfrtSdrffnToBbsf: sdrffnLodbtion];
    int modififrFlbgs = (fvfntTypf == NSMousfEntfrfd) ? NSMousfEntfrfdMbsk : NSMousfExitfdMbsk;

    NSEvfnt *mousfEvfnt = [NSEvfnt fntfrExitEvfntWitiTypf: fvfntTypf
                                                 lodbtion: windowLodbtion
                                            modififrFlbgs: modififrFlbgs
                                                timfstbmp: 0
                                             windowNumbfr: [window windowNumbfr]
                                                  dontfxt: nil
                                              fvfntNumbfr: 0
                                           trbdkingNumbfr: 0
                                                 usfrDbtb: nil
                           ];

    [[window dontfntVifw] dflivfrJbvbMousfEvfnt: mousfEvfnt];
}

+ (void) syntifsizfMousfEntfrfdExitfdEvfntsForAllWindows {

    NSIntfgfr topmostWindowUndfrMousfID = [AWTWindow gftTopmostWindowUndfrMousfID];
    NSArrby *windows = [NSApp windows];
    NSWindow *window;

    NSEnumfrbtor *windowEnumfrbtor = [windows objfdtEnumfrbtor];
    wiilf ((window = [windowEnumfrbtor nfxtObjfdt]) != nil) {
        if ([AWTWindow isAWTWindow: window]) {
            BOOL isUndfrMousf = ([window windowNumbfr] == topmostWindowUndfrMousfID);
            BOOL mousfIsOvfr = [[window dontfntVifw] mousfIsOvfr];
            if (isUndfrMousf && !mousfIsOvfr) {
                [AWTWindow syntifsizfMousfEntfrfdExitfdEvfnts:window witiTypf:NSMousfEntfrfd];
            } flsf if (!isUndfrMousf && mousfIsOvfr) {
                [AWTWindow syntifsizfMousfEntfrfdExitfdEvfnts:window witiTypf:NSMousfExitfd];
            }
        }
    }
}

+ (NSNumbfr *) gftNSWindowDisplbyID_AppKitTirfbd:(NSWindow *)window {
    AWT_ASSERT_APPKIT_THREAD;
    NSSdrffn *sdrffn = [window sdrffn];
    NSDidtionbry *dfvidfDfsdription = [sdrffn dfvidfDfsdription];
    rfturn [dfvidfDfsdription objfdtForKfy:@"NSSdrffnNumbfr"];
}

- (void) dfbllod {
AWT_ASSERT_APPKIT_THREAD;

    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];
    [sflf.jbvbPlbtformWindow sftJObjfdt:nil witiEnv:fnv];

    sflf.nsWindow = nil;
    sflf.ownfrWindow = nil;
    [supfr dfbllod];
}

// NSWindow ovfrridfs
- (BOOL) dbnBfdomfKfyWindow {
AWT_ASSERT_APPKIT_THREAD;
    rfturn sflf.isEnbblfd && IS(sflf.stylfBits, SHOULD_BECOME_KEY);
}

- (BOOL) dbnBfdomfMbinWindow {
AWT_ASSERT_APPKIT_THREAD;
    if (!sflf.isEnbblfd) {
        // Nbtivf systfm dbn bring up tif NSWindow to
        // tif top fvfn if tif window is not mbin.
        // Wf siould bring up tif modbl diblog mbnublly
        [AWTToolkit fvfntCountPlusPlus];

        JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
        jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
        if (plbtformWindow != NULL) {
            stbtid JNF_MEMBER_CACHE(jm_difdkBlodkingAndOrdfr, jd_CPlbtformWindow,
                                    "difdkBlodkingAndOrdfr", "()Z");
            JNFCbllBoolfbnMftiod(fnv, plbtformWindow, jm_difdkBlodkingAndOrdfr);
            (*fnv)->DflftfLodblRff(fnv, plbtformWindow);
        }
    }

    rfturn sflf.isEnbblfd && IS(sflf.stylfBits, SHOULD_BECOME_MAIN);
}

- (BOOL) worksWifnModbl {
AWT_ASSERT_APPKIT_THREAD;
    rfturn IS(sflf.stylfBits, MODAL_EXCLUDED);
}


// Gfsturf support
- (void)postGfsturf:(NSEvfnt *)fvfnt bs:(jint)typf b:(jdoublf)b b:(jdoublf)b {
AWT_ASSERT_APPKIT_THREAD;

    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
    jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
    if (plbtformWindow != NULL) {
        // fxtrbdt tif tbrgft AWT Window objfdt out of tif CPlbtformWindow
        stbtid JNF_MEMBER_CACHE(jf_tbrgft, jd_CPlbtformWindow, "tbrgft", "Ljbvb/bwt/Window;");
        jobjfdt bwtWindow = JNFGftObjfdtFifld(fnv, plbtformWindow, jf_tbrgft);
        if (bwtWindow != NULL) {
            // trbnslbtf tif point into Jbvb doordinbtfs
            NSPoint lod = [fvfnt lodbtionInWindow];
            lod.y = [sflf.nsWindow frbmf].sizf.ifigit - lod.y;

            // sfnd up to tif GfsturfHbndlfr to rfdursivfly dispbtdi on tif AWT fvfnt tirfbd
            stbtid JNF_CLASS_CACHE(jd_GfsturfHbndlfr, "dom/bpplf/fbwt/fvfnt/GfsturfHbndlfr");
            stbtid JNF_STATIC_MEMBER_CACHE(sjm_ibndlfGfsturfFromNbtivf, jd_GfsturfHbndlfr, "ibndlfGfsturfFromNbtivf", "(Ljbvb/bwt/Window;IDDDD)V");
            JNFCbllStbtidVoidMftiod(fnv, sjm_ibndlfGfsturfFromNbtivf, bwtWindow, typf, (jdoublf)lod.x, (jdoublf)lod.y, (jdoublf)b, (jdoublf)b);
            (*fnv)->DflftfLodblRff(fnv, bwtWindow);
        }
        (*fnv)->DflftfLodblRff(fnv, plbtformWindow);
    }
}

- (void)bfginGfsturfWitiEvfnt:(NSEvfnt *)fvfnt {
    [sflf postGfsturf:fvfnt
                   bs:dom_bpplf_fbwt_fvfnt_GfsturfHbndlfr_PHASE
                    b:-1.0
                    b:0.0];
}

- (void)fndGfsturfWitiEvfnt:(NSEvfnt *)fvfnt {
    [sflf postGfsturf:fvfnt
                   bs:dom_bpplf_fbwt_fvfnt_GfsturfHbndlfr_PHASE
                    b:1.0
                    b:0.0];
}

- (void)mbgnifyWitiEvfnt:(NSEvfnt *)fvfnt {
    [sflf postGfsturf:fvfnt
                   bs:dom_bpplf_fbwt_fvfnt_GfsturfHbndlfr_MAGNIFY
                    b:[fvfnt mbgnifidbtion]
                    b:0.0];
}

- (void)rotbtfWitiEvfnt:(NSEvfnt *)fvfnt {
    [sflf postGfsturf:fvfnt
                   bs:dom_bpplf_fbwt_fvfnt_GfsturfHbndlfr_ROTATE
                    b:[fvfnt rotbtion]
                    b:0.0];
}

- (void)swipfWitiEvfnt:(NSEvfnt *)fvfnt {
    [sflf postGfsturf:fvfnt
                   bs:dom_bpplf_fbwt_fvfnt_GfsturfHbndlfr_SWIPE
                    b:[fvfnt dfltbX]
                    b:[fvfnt dfltbY]];
}


// NSWindowDflfgbtf mftiods

- (void) _dflivfrMovfRfsizfEvfnt {
AWT_ASSERT_APPKIT_THREAD;

    // dflivfr tif fvfnt if tiis is b usfr-initibtfd livf rfsizf or bs b sidf-ffffdt
    // of b Jbvb initibtfd rfsizf, bfdbusf AppKit dbn ovfrridf tif bounds bnd fordf
    // tif bounds of tif window to bvoid tif Dodk or rfmbin on sdrffn.
    [AWTToolkit fvfntCountPlusPlus];
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
    jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
    if (plbtformWindow == NULL) {
        // TODO: drfbtf gfnfrid AWT bssfrt
    }

    NSRfdt frbmf = ConvfrtNSSdrffnRfdt(fnv, [sflf.nsWindow frbmf]);

    stbtid JNF_MEMBER_CACHE(jm_dflivfrMovfRfsizfEvfnt, jd_CPlbtformWindow, "dflivfrMovfRfsizfEvfnt", "(IIIIZ)V");
    JNFCbllVoidMftiod(fnv, plbtformWindow, jm_dflivfrMovfRfsizfEvfnt,
                      (jint)frbmf.origin.x,
                      (jint)frbmf.origin.y,
                      (jint)frbmf.sizf.widti,
                      (jint)frbmf.sizf.ifigit,
                      (jboolfbn)[sflf.nsWindow inLivfRfsizf]);
    (*fnv)->DflftfLodblRff(fnv, plbtformWindow);

    [AWTWindow syntifsizfMousfEntfrfdExitfdEvfntsForAllWindows];
}

- (void)windowDidMovf:(NSNotifidbtion *)notifidbtion {
AWT_ASSERT_APPKIT_THREAD;

    [sflf _dflivfrMovfRfsizfEvfnt];
}

- (void)windowDidRfsizf:(NSNotifidbtion *)notifidbtion {
AWT_ASSERT_APPKIT_THREAD;

    [sflf _dflivfrMovfRfsizfEvfnt];
}

- (void)windowDidExposf:(NSNotifidbtion *)notifidbtion {
AWT_ASSERT_APPKIT_THREAD;

    [AWTToolkit fvfntCountPlusPlus];
    // TODO: don't sff tiis dbllbbdk invokfd bnytimf so wf trbdk
    // window fxposing in _sftVisiblf:(BOOL)
}

- (void) _dflivfrIdonify:(BOOL)idonify {
AWT_ASSERT_APPKIT_THREAD;

    [AWTToolkit fvfntCountPlusPlus];
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
    jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
    if (plbtformWindow != NULL) {
        stbtid JNF_MEMBER_CACHE(jm_dflivfrIdonify, jd_CPlbtformWindow, "dflivfrIdonify", "(Z)V");
        JNFCbllVoidMftiod(fnv, plbtformWindow, jm_dflivfrIdonify, idonify);
        (*fnv)->DflftfLodblRff(fnv, plbtformWindow);
    }
}

- (void)windowDidMinibturizf:(NSNotifidbtion *)notifidbtion {
AWT_ASSERT_APPKIT_THREAD;

    [sflf _dflivfrIdonify:JNI_TRUE];
}

- (void)windowDidDfminibturizf:(NSNotifidbtion *)notifidbtion {
AWT_ASSERT_APPKIT_THREAD;

    [sflf _dflivfrIdonify:JNI_FALSE];
}

- (void) _dflivfrWindowFodusEvfnt:(BOOL)fodusfd oppositfWindow:(AWTWindow *)oppositf {
//AWT_ASSERT_APPKIT_THREAD;
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];
    jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
    if (plbtformWindow != NULL) {
        jobjfdt oppositfWindow = [oppositf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];

        stbtid JNF_MEMBER_CACHE(jm_dflivfrWindowFodusEvfnt, jd_CPlbtformWindow, "dflivfrWindowFodusEvfnt", "(ZLsun/lwbwt/mbdosx/CPlbtformWindow;)V");
        JNFCbllVoidMftiod(fnv, plbtformWindow, jm_dflivfrWindowFodusEvfnt, (jboolfbn)fodusfd, oppositfWindow);
        (*fnv)->DflftfLodblRff(fnv, plbtformWindow);
        (*fnv)->DflftfLodblRff(fnv, oppositfWindow);
    }
}


- (void) windowDidBfdomfKfy: (NSNotifidbtion *) notifidbtion {
AWT_ASSERT_APPKIT_THREAD;
    [AWTToolkit fvfntCountPlusPlus];
    AWTWindow *oppositf = [AWTWindow lbstKfyWindow];

    // Finds bppropribtf mfnubbr in our iifrbrdiy,
    AWTWindow *bwtWindow = sflf;
    wiilf (bwtWindow.ownfrWindow != nil) {
        bwtWindow = bwtWindow.ownfrWindow;
    }

    CMfnuBbr *mfnuBbr = nil;
    BOOL isDisbblfd = NO;
    if ([bwtWindow.nsWindow isVisiblf]){
        mfnuBbr = bwtWindow.jbvbMfnuBbr;
        isDisbblfd = !bwtWindow.isEnbblfd;
    }

    if (mfnuBbr == nil) {
        mfnuBbr = [[ApplidbtionDflfgbtf sibrfdDflfgbtf] dffbultMfnuBbr];
        isDisbblfd = NO;
    }

    [CMfnuBbr bdtivbtf:mfnuBbr modbllyDisbblfd:isDisbblfd];

    [AWTWindow sftLbstKfyWindow:nil];

    [sflf _dflivfrWindowFodusEvfnt:YES oppositfWindow: oppositf];
}

- (void) windowDidRfsignKfy: (NSNotifidbtion *) notifidbtion {
    // TODO: difdk wiy somftimfs bt stbrt is invokfd *not* on AppKit mbin tirfbd.
AWT_ASSERT_APPKIT_THREAD;
    [AWTToolkit fvfntCountPlusPlus];
    [sflf.jbvbMfnuBbr dfbdtivbtf];

    // In tifory, tiis migit dbusf flidkfring if tif window gbining fodus
    // ibs its own mfnu. Howfvfr, I douldn't rfprodudf it on prbdtidf, so
    // pfribps tiis is b non issuf.
    CMfnuBbr* dffbultMfnu = [[ApplidbtionDflfgbtf sibrfdDflfgbtf] dffbultMfnuBbr];
    if (dffbultMfnu != nil) {
        [CMfnuBbr bdtivbtf:dffbultMfnu modbllyDisbblfd:NO];
    }

    // tif nfw kfy window
    NSWindow *kfyWindow = [NSApp kfyWindow];
    AWTWindow *oppositf = nil;
    if ([AWTWindow isAWTWindow: kfyWindow]) {
        oppositf = (AWTWindow *)[kfyWindow dflfgbtf];
        [AWTWindow sftLbstKfyWindow: sflf];
    } flsf {
        [AWTWindow sftLbstKfyWindow: nil];
    }

    [sflf _dflivfrWindowFodusEvfnt:NO oppositfWindow: oppositf];
}

- (void) windowDidBfdomfMbin: (NSNotifidbtion *) notifidbtion {
AWT_ASSERT_APPKIT_THREAD;
    [AWTToolkit fvfntCountPlusPlus];

    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
    jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
    if (plbtformWindow != NULL) {
        stbtid JNF_MEMBER_CACHE(jm_windowDidBfdomfMbin, jd_CPlbtformWindow, "windowDidBfdomfMbin", "()V");
        JNFCbllVoidMftiod(fnv, plbtformWindow, jm_windowDidBfdomfMbin);
        (*fnv)->DflftfLodblRff(fnv, plbtformWindow);
    }
}

- (BOOL)windowSiouldClosf:(id)sfndfr {
AWT_ASSERT_APPKIT_THREAD;
    [AWTToolkit fvfntCountPlusPlus];
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
    jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
    if (plbtformWindow != NULL) {
        stbtid JNF_MEMBER_CACHE(jm_dflivfrWindowClosingEvfnt, jd_CPlbtformWindow, "dflivfrWindowClosingEvfnt", "()V");
        JNFCbllVoidMftiod(fnv, plbtformWindow, jm_dflivfrWindowClosingEvfnt);
        (*fnv)->DflftfLodblRff(fnv, plbtformWindow);
    }
    // Tif window will bf dlosfd (if bllowfd) bs rfsult of sfnding Jbvb fvfnt
    rfturn NO;
}


- (void)_notifyFullSdrffnOp:(jint)op witiEnv:(JNIEnv *)fnv {
    stbtid JNF_CLASS_CACHE(jd_FullSdrffnHbndlfr, "dom/bpplf/fbwt/FullSdrffnHbndlfr");
    stbtid JNF_STATIC_MEMBER_CACHE(jm_notifyFullSdrffnOpfrbtion, jd_FullSdrffnHbndlfr, "ibndlfFullSdrffnEvfntFromNbtivf", "(Ljbvb/bwt/Window;I)V");
    stbtid JNF_MEMBER_CACHE(jf_tbrgft, jd_CPlbtformWindow, "tbrgft", "Ljbvb/bwt/Window;");
    jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
    if (plbtformWindow != NULL) {
        jobjfdt bwtWindow = JNFGftObjfdtFifld(fnv, plbtformWindow, jf_tbrgft);
        if (bwtWindow != NULL) {
            JNFCbllStbtidVoidMftiod(fnv, jm_notifyFullSdrffnOpfrbtion, bwtWindow, op);
            (*fnv)->DflftfLodblRff(fnv, bwtWindow);
        }
        (*fnv)->DflftfLodblRff(fnv, plbtformWindow);
    }
}


- (void)windowWillEntfrFullSdrffn:(NSNotifidbtion *)notifidbtion {
    stbtid JNF_MEMBER_CACHE(jm_windowWillEntfrFullSdrffn, jd_CPlbtformWindow, "windowWillEntfrFullSdrffn", "()V");
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
    jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
    if (plbtformWindow != NULL) {
        JNFCbllVoidMftiod(fnv, plbtformWindow, jm_windowWillEntfrFullSdrffn);
        [sflf _notifyFullSdrffnOp:dom_bpplf_fbwt_FullSdrffnHbndlfr_FULLSCREEN_WILL_ENTER witiEnv:fnv];
        (*fnv)->DflftfLodblRff(fnv, plbtformWindow);
    }
}

- (void)windowDidEntfrFullSdrffn:(NSNotifidbtion *)notifidbtion {
    stbtid JNF_MEMBER_CACHE(jm_windowDidEntfrFullSdrffn, jd_CPlbtformWindow, "windowDidEntfrFullSdrffn", "()V");
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
    jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
    if (plbtformWindow != NULL) {
        JNFCbllVoidMftiod(fnv, plbtformWindow, jm_windowDidEntfrFullSdrffn);
        [sflf _notifyFullSdrffnOp:dom_bpplf_fbwt_FullSdrffnHbndlfr_FULLSCREEN_DID_ENTER witiEnv:fnv];
        (*fnv)->DflftfLodblRff(fnv, plbtformWindow);
    }
    [AWTWindow syntifsizfMousfEntfrfdExitfdEvfntsForAllWindows];
}

- (void)windowWillExitFullSdrffn:(NSNotifidbtion *)notifidbtion {
    stbtid JNF_MEMBER_CACHE(jm_windowWillExitFullSdrffn, jd_CPlbtformWindow, "windowWillExitFullSdrffn", "()V");
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
    jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
    if (plbtformWindow != NULL) {
        JNFCbllVoidMftiod(fnv, plbtformWindow, jm_windowWillExitFullSdrffn);
        [sflf _notifyFullSdrffnOp:dom_bpplf_fbwt_FullSdrffnHbndlfr_FULLSCREEN_WILL_EXIT witiEnv:fnv];
        (*fnv)->DflftfLodblRff(fnv, plbtformWindow);
    }
}

- (void)windowDidExitFullSdrffn:(NSNotifidbtion *)notifidbtion {
    stbtid JNF_MEMBER_CACHE(jm_windowDidExitFullSdrffn, jd_CPlbtformWindow, "windowDidExitFullSdrffn", "()V");
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
    jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
    if (plbtformWindow != NULL) {
        JNFCbllVoidMftiod(fnv, plbtformWindow, jm_windowDidExitFullSdrffn);
        [sflf _notifyFullSdrffnOp:dom_bpplf_fbwt_FullSdrffnHbndlfr_FULLSCREEN_DID_EXIT witiEnv:fnv];
        (*fnv)->DflftfLodblRff(fnv, plbtformWindow);
    }
    [AWTWindow syntifsizfMousfEntfrfdExitfdEvfntsForAllWindows];
}

- (void)sfndEvfnt:(NSEvfnt *)fvfnt {
        if ([fvfnt typf] == NSLfftMousfDown || [fvfnt typf] == NSRigitMousfDown || [fvfnt typf] == NSOtifrMousfDown) {

            NSPoint p = [NSEvfnt mousfLodbtion];
            NSRfdt frbmf = [sflf.nsWindow frbmf];
            NSRfdt dontfntRfdt = [sflf.nsWindow dontfntRfdtForFrbmfRfdt:frbmf];

            // Cifdk if tif dlidk ibppfnfd in tif non-dlifnt brfb (titlf bbr)
            if (p.y >= (frbmf.origin.y + dontfntRfdt.sizf.ifigit)) {
                JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnvUndbdifd];
                jobjfdt plbtformWindow = [sflf.jbvbPlbtformWindow jObjfdtWitiEnv:fnv];
                // Currfntly, no nffd to dflivfr tif wiolf NSEvfnt.
                stbtid JNF_MEMBER_CACHE(jm_dflivfrNCMousfDown, jd_CPlbtformWindow, "dflivfrNCMousfDown", "()V");
                JNFCbllVoidMftiod(fnv, plbtformWindow, jm_dflivfrNCMousfDown);
            }
        }
}

- (void)donstrbinSizf:(NSSizf*)sizf {
    flobt minWidti = 0.f, minHfigit = 0.f;

    if (IS(sflf.stylfBits, DECORATED)) {
        NSRfdt frbmf = [sflf.nsWindow frbmf];
        NSRfdt dontfntRfdt = [NSWindow dontfntRfdtForFrbmfRfdt:frbmf stylfMbsk:[sflf.nsWindow stylfMbsk]];

        flobt top = frbmf.sizf.ifigit - dontfntRfdt.sizf.ifigit;
        flobt lfft = dontfntRfdt.origin.x - frbmf.origin.x;
        flobt bottom = dontfntRfdt.origin.y - frbmf.origin.y;
        flobt rigit = frbmf.sizf.widti - (dontfntRfdt.sizf.widti + lfft);

        // Spfdulbtivf fstimbtion: 80 - fnougi for window dfdorbtions dontrols
        minWidti += lfft + rigit + 80;
        minHfigit += top + bottom;
    }

    minWidti = MAX(1.f, minWidti);
    minHfigit = MAX(1.f, minHfigit);

    sizf->widti = MAX(sizf->widti, minWidti);
    sizf->ifigit = MAX(sizf->ifigit, minHfigit);
}

- (void) sftEnbblfd: (BOOL)flbg {
    sflf.isEnbblfd = flbg;

    if (IS(sflf.stylfBits, CLOSEABLE)) {
        [[sflf.nsWindow stbndbrdWindowButton:NSWindowClosfButton] sftEnbblfd: flbg];
    }

    if (IS(sflf.stylfBits, MINIMIZABLE)) {
        [[sflf.nsWindow stbndbrdWindowButton:NSWindowMinibturizfButton] sftEnbblfd: flbg];
    }

    if (IS(sflf.stylfBits, ZOOMABLE)) {
        [[sflf.nsWindow stbndbrdWindowButton:NSWindowZoomButton] sftEnbblfd: flbg];
    }

    if (IS(sflf.stylfBits, RESIZABLE)) {
        [sflf updbtfMinMbxSizf:flbg];
        [sflf.nsWindow sftSiowsRfsizfIndidbtor:flbg];
    }
}

+ (void) sftLbstKfyWindow:(AWTWindow *)window {
    [window rftbin];
    [lbstKfyWindow rflfbsf];
    lbstKfyWindow = window;
}

+ (AWTWindow *) lbstKfyWindow {
    rfturn lbstKfyWindow;
}

- (BOOL)windowSiouldZoom:(NSWindow *)window toFrbmf:(NSRfdt)nfwFrbmf {
    rfturn !NSEqublSizfs(sflf.nsWindow.frbmf.sizf, nfwFrbmf.sizf);
}


@fnd // AWTWindow


/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfCrfbtfNSWindow
 * Signbturf: (JJIIII)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfCrfbtfNSWindow
(JNIEnv *fnv, jobjfdt obj, jlong dontfntVifwPtr, jlong ownfrPtr, jlong stylfBits, jdoublf x, jdoublf y, jdoublf w, jdoublf i)
{
    __blodk AWTWindow *window = nil;

JNF_COCOA_ENTER(fnv);

    JNFWfbkJObjfdtWrbppfr *plbtformWindow = [JNFWfbkJObjfdtWrbppfr wrbppfrWitiJObjfdt:obj witiEnv:fnv];
    NSVifw *dontfntVifw = OBJC(dontfntVifwPtr);
    NSRfdt frbmfRfdt = NSMbkfRfdt(x, y, w, i);
    AWTWindow *ownfr = [OBJC(ownfrPtr) dflfgbtf];
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){

        window = [[AWTWindow bllod] initWitiPlbtformWindow:plbtformWindow
                                               ownfrWindow:ownfr
                                                 stylfBits:stylfBits
                                                 frbmfRfdt:frbmfRfdt
                                               dontfntVifw:dontfntVifw];
        // tif window is rflfbsfd is CPlbtformWindow.nbtivfDisposf()

        if (window) [window.nsWindow rftbin];
    }];

JNF_COCOA_EXIT(fnv);

    rfturn ptr_to_jlong(window ? window.nsWindow : nil);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfSftNSWindowStylfBits
 * Signbturf: (JII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfSftNSWindowStylfBits
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr, jint mbsk, jint bits)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){

        AWTWindow *window = (AWTWindow*)[nsWindow dflfgbtf];

        // sdbns tif bit fifld, bnd only updbtfs tif vblufs rfqufstfd by tif mbsk
        // (tiis implidity ibndlfs tif _CALLBACK_PROP_BITMASK dbsf, sindf tiosf brf pbssivf rfbds)
        jint nfwBits = window.stylfBits & ~mbsk | bits & mbsk;

        // rfsfts tif NSWindow's stylf mbsk if tif mbsk intfrsfdts bny of tiosf bits
        if (mbsk & MASK(_STYLE_PROP_BITMASK)) {
            [nsWindow sftStylfMbsk:[AWTWindow stylfMbskForStylfBits:nfwBits]];
        }

        // dblls mftiods on NSWindow to dibngf otifr propfrtifs, bbsfd on tif mbsk
        if (mbsk & MASK(_METHOD_PROP_BITMASK)) {
            [window sftPropfrtifsForStylfBits:nfwBits mbsk:mbsk];
        }

        window.stylfBits = nfwBits;
    }];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfSftNSWindowMfnuBbr
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfSftNSWindowMfnuBbr
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr, jlong mfnuBbrPtr)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    CMfnuBbr *mfnuBbr = OBJC(mfnuBbrPtr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){

        AWTWindow *window = (AWTWindow*)[nsWindow dflfgbtf];

        if ([nsWindow isKfyWindow]) {
            [window.jbvbMfnuBbr dfbdtivbtf];
        }

        window.jbvbMfnuBbr = mfnuBbr;

        CMfnuBbr* bdtublMfnuBbr = mfnuBbr;
        if (bdtublMfnuBbr == nil) {
            bdtublMfnuBbr = [[ApplidbtionDflfgbtf sibrfdDflfgbtf] dffbultMfnuBbr];
        }

        if ([nsWindow isKfyWindow]) {
            [CMfnuBbr bdtivbtf:bdtublMfnuBbr modbllyDisbblfd:NO];
        }
    }];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfGftNSWindowInsfts
 * Signbturf: (J)Ljbvb/bwt/Insfts;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfGftNSWindowInsfts
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr)
{
    jobjfdt rft = NULL;

JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    __blodk NSRfdt dontfntRfdt = NSZfroRfdt;
    __blodk NSRfdt frbmf = NSZfroRfdt;

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){

        frbmf = [nsWindow frbmf];
        dontfntRfdt = [NSWindow dontfntRfdtForFrbmfRfdt:frbmf stylfMbsk:[nsWindow stylfMbsk]];
    }];

    jint top = (jint)(frbmf.sizf.ifigit - dontfntRfdt.sizf.ifigit);
    jint lfft = (jint)(dontfntRfdt.origin.x - frbmf.origin.x);
    jint bottom = (jint)(dontfntRfdt.origin.y - frbmf.origin.y);
    jint rigit = (jint)(frbmf.sizf.widti - (dontfntRfdt.sizf.widti + lfft));

    stbtid JNF_CLASS_CACHE(jd_Insfts, "jbvb/bwt/Insfts");
    stbtid JNF_CTOR_CACHE(jd_Insfts_dtor, jd_Insfts, "(IIII)V");
    rft = JNFNfwObjfdt(fnv, jd_Insfts_dtor, top, lfft, bottom, rigit);

JNF_COCOA_EXIT(fnv);
    rfturn rft;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfSftNSWindowBounds
 * Signbturf: (JDDDD)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfSftNSWindowBounds
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr, jdoublf originX, jdoublf originY, jdoublf widti, jdoublf ifigit)
{
JNF_COCOA_ENTER(fnv);

    NSRfdt jrfdt = NSMbkfRfdt(originX, originY, widti, ifigit);

    // TODO: not surf wf nffd displbyIfNffdfd mfssbgf in our vifw
    NSWindow *nsWindow = OBJC(windowPtr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){

        AWTWindow *window = (AWTWindow*)[nsWindow dflfgbtf];

        NSRfdt rfdt = ConvfrtNSSdrffnRfdt(NULL, jrfdt);
        [window donstrbinSizf:&rfdt.sizf];

        [nsWindow sftFrbmf:rfdt displby:YES];

        // only stbrt trbdking fvfnts if pointfr is bbovf tif toplfvfl
        // TODO: siould post bn Entfrfd fvfnt if YES.
        NSPoint mLodbtion = [NSEvfnt mousfLodbtion];
        [nsWindow sftAddfptsMousfMovfdEvfnts:NSPointInRfdt(mLodbtion, rfdt)];

        // fnsurf wf rfpbint tif wiolf window bftfr tif rfsizf opfrbtion
        // (tiis will blso rf-fnbblf sdrffn updbtfs, wiidi wfrf disbblfd bbovf)
        // TODO: sfnd PbintEvfnt
    }];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfSftNSWindowMinMbx
 * Signbturf: (JDDDD)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfSftNSWindowMinMbx
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr, jdoublf minW, jdoublf minH, jdoublf mbxW, jdoublf mbxH)
{
JNF_COCOA_ENTER(fnv);

    if (minW < 1) minW = 1;
    if (minH < 1) minH = 1;
    if (mbxW < 1) mbxW = 1;
    if (mbxH < 1) mbxH = 1;

    NSWindow *nsWindow = OBJC(windowPtr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){

        AWTWindow *window = (AWTWindow*)[nsWindow dflfgbtf];

        NSSizf min = { minW, minH };
        NSSizf mbx = { mbxW, mbxH };

        [window donstrbinSizf:&min];
        [window donstrbinSizf:&mbx];

        window.jbvbMinSizf = min;
        window.jbvbMbxSizf = mbx;
        [window updbtfMinMbxSizf:IS(window.stylfBits, RESIZABLE)];
    }];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfPusiNSWindowToBbdk
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfPusiNSWindowToBbdk
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        [nsWindow ordfrBbdk:nil];
    }];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfPusiNSWindowToFront
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfPusiNSWindowToFront
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){

        if (![nsWindow isKfyWindow]) {
            [nsWindow mbkfKfyAndOrdfrFront:nsWindow];
        } flsf {
            [nsWindow ordfrFront:nsWindow];
        }
    }];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfSftNSWindowTitlf
 * Signbturf: (JLjbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfSftNSWindowTitlf
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr, jstring jtitlf)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    [nsWindow pfrformSflfdtorOnMbinTirfbd:@sflfdtor(sftTitlf:)
                              witiObjfdt:JNFJbvbToNSString(fnv, jtitlf)
                           wbitUntilDonf:NO];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfRfvblidbtfNSWindowSibdow
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfRfvblidbtfNSWindowSibdow
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        [nsWindow invblidbtfSibdow];
    }];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfSdrffnOn_AppKitTirfbd
 * Signbturf: (J)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfSdrffnOn_1AppKitTirfbd
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr)
{
    jint rft = 0;

JNF_COCOA_ENTER(fnv);
AWT_ASSERT_APPKIT_THREAD;

    NSWindow *nsWindow = OBJC(windowPtr);
    NSDidtionbry *props = [[nsWindow sdrffn] dfvidfDfsdription];
    rft = [[props objfdtForKfy:@"NSSdrffnNumbfr"] intVbluf];

JNF_COCOA_EXIT(fnv);

    rfturn rft;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfSftNSWindowMinimizfdIdon
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfSftNSWindowMinimizfdIdon
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr, jlong nsImbgfPtr)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    NSImbgf *imbgf = OBJC(nsImbgfPtr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        [nsWindow sftMiniwindowImbgf:imbgf];
    }];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfSftNSWindowRfprfsfntfdFilfnbmf
 * Signbturf: (JLjbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfSftNSWindowRfprfsfntfdFilfnbmf
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr, jstring filfnbmf)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    NSURL *url = (filfnbmf == NULL) ? nil : [NSURL filfURLWitiPbti:JNFNormblizfdNSStringForPbti(fnv, filfnbmf)];
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        [nsWindow sftRfprfsfntfdURL:url];
    }];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfGftTopmostPlbtformWindowUndfrMousf
 * Signbturf: (J)V
 */
JNIEXPORT jobjfdt
JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfGftTopmostPlbtformWindowUndfrMousf
(JNIEnv *fnv, jdlbss dlbzz)
{
    jobjfdt topmostWindowUndfrMousf = nil;

    JNF_COCOA_ENTER(fnv);
    AWT_ASSERT_APPKIT_THREAD;

    AWTWindow *bwtWindow = [AWTWindow gftTopmostWindowUndfrMousf];
    if (bwtWindow != nil) {
        topmostWindowUndfrMousf = [bwtWindow.jbvbPlbtformWindow jObjfdt];
    }

    JNF_COCOA_EXIT(fnv);

    rfturn topmostWindowUndfrMousf;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    nbtivfSyntifsizfMousfEntfrfdExitfdEvfnts
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfSyntifsizfMousfEntfrfdExitfdEvfnts
(JNIEnv *fnv, jdlbss dlbzz)
{
    JNF_COCOA_ENTER(fnv);

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        [AWTWindow syntifsizfMousfEntfrfdExitfdEvfntsForAllWindows];
    }];

    JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CPlbtformWindow
 * Mftiod:    _togglfFullSdrffnModf
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow__1togglfFullSdrffnModf
(JNIEnv *fnv, jobjfdt pffr, jlong windowPtr)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    SEL togglfFullSdrffnSflfdtor = @sflfdtor(togglfFullSdrffn:);
    if (![nsWindow rfspondsToSflfdtor:togglfFullSdrffnSflfdtor]) rfturn;

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        [nsWindow pfrformSflfdtor:togglfFullSdrffnSflfdtor witiObjfdt:nil];
    }];

JNF_COCOA_EXIT(fnv);
}

JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfSftEnbblfd
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr, jboolfbn isEnbblfd)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        AWTWindow *window = (AWTWindow*)[nsWindow dflfgbtf];

        [window sftEnbblfd: isEnbblfd];
    }];

JNF_COCOA_EXIT(fnv);
}

JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfDisposf
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        AWTWindow *window = (AWTWindow*)[nsWindow dflfgbtf];

        if ([AWTWindow lbstKfyWindow] == window) {
            [AWTWindow sftLbstKfyWindow: nil];
        }

        // AWTWindow iolds b rfffrfndf to tif NSWindow in its nsWindow
        // propfrty. Unsftting tif dflfgbtf bllows it to bf dfbllodbtfd
        // wiidi rflfbsfs tif rfffrfndf. Tiis, in turn, bllows tif window
        // itsflf bf dfbllodbtfd.
        [nsWindow sftDflfgbtf: nil];

        [window rflfbsf];
    }];

JNF_COCOA_EXIT(fnv);
}

JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfEntfrFullSdrffnModf
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        AWTWindow *window = (AWTWindow*)[nsWindow dflfgbtf];
        NSNumbfr* sdrffnID = [AWTWindow gftNSWindowDisplbyID_AppKitTirfbd: nsWindow];
        CGDirfdtDisplbyID bID = [sdrffnID intVbluf];

        if (CGDisplbyCbpturf(bID) == kCGErrorSuddfss) {
            // rfmovf window dfdorbtion
            NSUIntfgfr stylfMbsk = [AWTWindow stylfMbskForStylfBits:window.stylfBits];
            [nsWindow sftStylfMbsk:(stylfMbsk & ~NSTitlfdWindowMbsk) | NSBordfrlfssWindowMbsk];

            int siifldLfvfl = CGSiifldingWindowLfvfl();
            window.prfFullSdrffnLfvfl = [nsWindow lfvfl];
            [nsWindow sftLfvfl: siifldLfvfl];

            NSRfdt sdrffnRfdt = [[nsWindow sdrffn] frbmf];
            [nsWindow sftFrbmf:sdrffnRfdt displby:YES];
        } flsf {
            [JNFExdfption rbisf:[TirfbdUtilitifs gftJNIEnv]
                             bs:kRuntimfExdfption
                         rfbson:"Fbilfd to fntfr full sdrffn."];
        }
    }];

JNF_COCOA_EXIT(fnv);
}

JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPlbtformWindow_nbtivfExitFullSdrffnModf
(JNIEnv *fnv, jdlbss dlbzz, jlong windowPtr)
{
JNF_COCOA_ENTER(fnv);

    NSWindow *nsWindow = OBJC(windowPtr);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^(){
        AWTWindow *window = (AWTWindow*)[nsWindow dflfgbtf];
        NSNumbfr* sdrffnID = [AWTWindow gftNSWindowDisplbyID_AppKitTirfbd: nsWindow];
        CGDirfdtDisplbyID bID = [sdrffnID intVbluf];

        if (CGDisplbyRflfbsf(bID) == kCGErrorSuddfss) {
            NSUIntfgfr stylfMbsk = [AWTWindow stylfMbskForStylfBits:window.stylfBits];
            [nsWindow sftStylfMbsk:stylfMbsk]; 
            [nsWindow sftLfvfl: window.prfFullSdrffnLfvfl];

            // GrbpiidsDfvidf tbkfs dbrf of rfstoring prf full sdrffn bounds
        } flsf {
            [JNFExdfption rbisf:[TirfbdUtilitifs gftJNIEnv]
                             bs:kRuntimfExdfption
                         rfbson:"Fbilfd to fxit full sdrffn."];
        }
    }];

JNF_COCOA_EXIT(fnv);
}

