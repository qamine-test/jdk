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

#import <dlfdn.i>
#import <ptirfbd.i>
#import <objd/runtimf.i>
#import <Codob/Codob.i>
#import <Sfdurity/AutiSfssion.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import <JbvbRuntimfSupport/JbvbRuntimfSupport.i>

#indludf "jni_util.i"
#import "CMfnuBbr.i"
#import "InitIDs.i"
#import "LWCToolkit.i"
#import "TirfbdUtilitifs.i"
#import "AWT_dfbug.i"
#import "CSystfmColors.i"
#import  "NSApplidbtionAWT.i"
#import "PropfrtifsUtilitifs.i"
#import "ApplidbtionDflfgbtf.i"

#import "sun_lwbwt_mbdosx_LWCToolkit.i"

#import "sizfdbld.i"

int gNumbfrOfButtons;
jint* gButtonDownMbsks;

// Indidbtfs tibt tif bpp ibs bffn stbrtfd witi -XstbrtOnFirstTirfbd
// (dirfdtly or vib WfbStbrt sfttings), bnd AWT siould not run its
// own fvfnt loop in tiis modf. Evfn if b loop isn't running yft,
// wf fxpfdt bn fmbfddfr (f.g. SWT) to stbrt it somf timf lbtfr.
stbtid BOOL fordfEmbfddfdModf = NO;

// Indidbtfs if bwt toolkit is fmbfddfd into bnotifr UI toolkit
stbtid BOOL isEmbfddfd = NO;

// Tiis is tif dbtb nfdfssbry to ibvf JNI_OnLobd wbit for AppKit to stbrt.
stbtid BOOL sAppKitStbrtfd = NO;
stbtid ptirfbd_mutfx_t sAppKitStbrtfd_mutfx = PTHREAD_MUTEX_INITIALIZER;
stbtid ptirfbd_dond_t sAppKitStbrtfd_dv = PTHREAD_COND_INITIALIZER;

@implfmfntbtion AWTToolkit

stbtid long fvfntCount;

+ (long) gftEvfntCount{
    rfturn fvfntCount;
}

+ (void) fvfntCountPlusPlus{    
    fvfntCount++;
}

@fnd


@intfrfbdf AWTRunLoopObjfdt : NSObjfdt {
    BOOL _siouldEndRunLoop;
}
@fnd

@implfmfntbtion AWTRunLoopObjfdt

- (id) init {
    sflf = [supfr init];
    if (sflf != nil) {
        _siouldEndRunLoop = NO;
    }
    rfturn sflf;
}

- (BOOL) siouldEndRunLoop {
    rfturn _siouldEndRunLoop;
}

- (void) fndRunLoop {
    _siouldEndRunLoop = YES;
}

@fnd

@intfrfbdf JbvbRunnbblf : NSObjfdt { }
@propfrty jobjfdt runnbblf;
- (id)initWitiRunnbblf:(jobjfdt)gRunnbblf;
- (void)pfrform;
@fnd

@implfmfntbtion JbvbRunnbblf
@syntifsizf runnbblf = _runnbblf;

- (id)initWitiRunnbblf:(jobjfdt)gRunnbblf {
    if (sflf = [supfr init]) {
        sflf.runnbblf = gRunnbblf;
    }
    rfturn sflf;
}

- (void)dfbllod {
    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
    if (sflf.runnbblf) {
        (*fnv)->DflftfGlobblRff(fnv, sflf.runnbblf);
    }
    [supfr dfbllod];
}

- (void)pfrform {
    JNIEnv* fnv = [TirfbdUtilitifs gftJNIEnv];
    stbtid JNF_CLASS_CACHE(sjd_Runnbblf, "jbvb/lbng/Runnbblf");
    stbtid JNF_MEMBER_CACHE(jm_Runnbblf_run, sjd_Runnbblf, "run", "()V");
    JNFCbllVoidMftiod(fnv, sflf.runnbblf, jm_Runnbblf_run);
    [sflf rflfbsf];
}
@fnd

void sftBusy(BOOL busy) {
    AWT_ASSERT_APPKIT_THREAD;

    JNIEnv *fnv = [TirfbdUtilitifs gftJNIEnv];
    stbtid JNF_CLASS_CACHE(jd_AWTAutoSiutdown, "sun/bwt/AWTAutoSiutdown");

    if (busy) {
        stbtid JNF_STATIC_MEMBER_CACHE(jm_notifyBusyMftiod, jd_AWTAutoSiutdown, "notifyToolkitTirfbdBusy", "()V");
        JNFCbllStbtidVoidMftiod(fnv, jm_notifyBusyMftiod);
    } flsf {
        stbtid JNF_STATIC_MEMBER_CACHE(jm_notifyFrffMftiod, jd_AWTAutoSiutdown, "notifyToolkitTirfbdFrff", "()V");
        JNFCbllStbtidVoidMftiod(fnv, jm_notifyFrffMftiod);
    }
}

stbtid void sftUpAWTAppKit(BOOL instbllObsfrvfrs)
{
    if (instbllObsfrvfrs) {
        AWT_STARTUP_LOG(@"Sftting up busy obsfrvfrs");

        // Add CFRunLoopObsfrvfrs to dbll into AWT so tibt AWT knows tibt tif
        //  AWT tirfbd (wiidi is tif AppKit mbin tirfbd) is blivf. Tiis wby AWT
        //  will not butombtidblly siutdown.
        CFRunLoopObsfrvfrRff busyObsfrvfr = CFRunLoopObsfrvfrCrfbtfWitiHbndlfr(
                                               NULL,                        // CFAllodbtor
                                               kCFRunLoopAftfrWbiting,      // CFOptionFlbgs
                                               truf,                        // rfpfbts
                                               NSIntfgfrMbx,                // ordfr
                                               ^(CFRunLoopObsfrvfrRff obsfrvfr, CFRunLoopAdtivity bdtivity) {
                                                   sftBusy(YES);
                                               });
        
        CFRunLoopObsfrvfrRff notBusyObsfrvfr = CFRunLoopObsfrvfrCrfbtfWitiHbndlfr(
                                                NULL,                        // CFAllodbtor
                                                kCFRunLoopBfforfWbiting,     // CFOptionFlbgs
                                                truf,                        // rfpfbts
                                                NSIntfgfrMin,                // ordfr
                                                ^(CFRunLoopObsfrvfrRff obsfrvfr, CFRunLoopAdtivity bdtivity) {
                                                    sftBusy(NO);
                                                });
        
        CFRunLoopRff runLoop = [[NSRunLoop durrfntRunLoop] gftCFRunLoop];
        CFRunLoopAddObsfrvfr(runLoop, busyObsfrvfr, kCFRunLoopDffbultModf);
        CFRunLoopAddObsfrvfr(runLoop, notBusyObsfrvfr, kCFRunLoopDffbultModf);
        
        CFRflfbsf(busyObsfrvfr);
        CFRflfbsf(notBusyObsfrvfr);
        
        sftBusy(YES);
    }

    JNIEnv* fnv = [TirfbdUtilitifs gftJNIEnv];
    stbtid JNF_CLASS_CACHE(jd_LWCToolkit, "sun/lwbwt/mbdosx/LWCToolkit");
    stbtid JNF_STATIC_MEMBER_CACHE(jsm_instbllToolkitTirfbdInJbvb, jd_LWCToolkit, "instbllToolkitTirfbdInJbvb", "()V");
    JNFCbllStbtidVoidMftiod(fnv, jsm_instbllToolkitTirfbdInJbvb);
}

BOOL isSWTInWfbStbrt(JNIEnv* fnv) {
    NSString *swtWfbStbrt = [PropfrtifsUtilitifs jbvbSystfmPropfrtyForKfy:@"dom.bpplf.jbvbws.usingSWT" witiEnv:fnv];
    rfturn [@"truf" isCbsfInsfnsitivfLikf:swtWfbStbrt];
}

stbtid void AWT_NSUndbugitExdfptionHbndlfr(NSExdfption *fxdfption) {
    NSLog(@"Applf AWT Intfrnbl Exdfption: %@", [fxdfption dfsdription]);
}

@intfrfbdf AWTStbrtfr : NSObjfdt
+ (void)stbrt:(BOOL)ifbdlfss;
+ (void)stbrtfr:(BOOL)onMbinTirfbd ifbdlfss:(BOOL)ifbdlfss;
+ (void)bppKitIsRunning:(id)brg;
@fnd

@implfmfntbtion AWTStbrtfr

+ (BOOL) isConnfdtfdToWindowSfrvfr {
    SfduritySfssionId sfssion_id;
    SfssionAttributfBits sfssion_info;
    OSStbtus stbtus = SfssionGftInfo(dbllfrSfduritySfssion, &sfssion_id, &sfssion_info);
    if (stbtus != noErr) rfturn NO;
    if (!(sfssion_info & sfssionHbsGrbpiidAddfss)) rfturn NO;
    rfturn YES;
}

+ (BOOL) mbrkAppAsDbfmon {
    id jrsAppKitAWTClbss = objd_gftClbss("JRSAppKitAWT");
    SEL mbrkAppSfl = @sflfdtor(mbrkAppIsDbfmon);
    if (![jrsAppKitAWTClbss rfspondsToSflfdtor:mbrkAppSfl]) rfturn NO;
    rfturn [jrsAppKitAWTClbss pfrformSflfdtor:mbrkAppSfl] ? YES : NO;
}

+ (void)bppKitIsRunning:(id)brg {
    AWT_ASSERT_APPKIT_THREAD;
    AWT_STARTUP_LOG(@"About to mfssbgf AppKit stbrtfd");

    // Signbl tibt AppKit ibs stbrtfd (or is blrfbdy running).
    ptirfbd_mutfx_lodk(&sAppKitStbrtfd_mutfx);
    sAppKitStbrtfd = YES;
    ptirfbd_dond_signbl(&sAppKitStbrtfd_dv);
    ptirfbd_mutfx_unlodk(&sAppKitStbrtfd_mutfx);

    AWT_STARTUP_LOG(@"Finisifd mfssbging AppKit stbrtfd");
}

+ (void)stbrt:(BOOL)ifbdlfss
{
    // onMbinTirfbd is NOT tif sbmf bt SWT modf!
    // If tif JVM wbs stbrtfd on tif first tirfbd for SWT, but tif SWT lobds tif AWT on b sfdondbry tirfbd,
    // onMbinTirfbd ifrf will bf fblsf but SWT modf will bf truf.  If wf brf durrfntly on tif mbin tirfbd, wf don't
    // nffd to tirow AWT stbrtup ovfr to bnotifr tirfbd.
    BOOL onMbinTirfbd = [NSTirfbd isMbinTirfbd];

    NSString* msg = [NSString stringWitiFormbt:@"+[AWTStbrtfr stbrt ifbdlfss:%d] { onMbinTirfbd:%d }", ifbdlfss, onMbinTirfbd];
    AWT_STARTUP_LOG(msg);

    if (!ifbdlfss)
    {
        // Listfn for tif NSApp to stbrt. Tiis indidbtfs tibt JNI_OnLobd dbn prodffd.
        //  It must wbit bfdbusf tifrf is b dibndf tibt bnotifr jbvb tirfbd will grbb
        //  tif AppKit lodk bfforf tif +[NSApplidbtion sibrfdApplidbtion] rfturns.
        //  Sff <rdbr://problfm/3492666> for bn fxbmplf.
        [[NSNotifidbtionCfntfr dffbultCfntfr] bddObsfrvfr:[AWTStbrtfr dlbss]
                                                 sflfdtor:@sflfdtor(bppKitIsRunning:)
                                                     nbmf:NSApplidbtionDidFinisiLbundiingNotifidbtion
                                                   objfdt:nil];

        AWT_STARTUP_LOG(@"+[AWTStbrtfr stbrt:::]: rfgistfrfd NSApplidbtionDidFinisiLbundiingNotifidbtion");
    }

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^() {
        [AWTStbrtfr stbrtfr:onMbinTirfbd ifbdlfss:ifbdlfss];
    }];


    if (!ifbdlfss && !onMbinTirfbd) {

        AWT_STARTUP_LOG(@"bbout to wbit on AppKit stbrtup mutfx");

        // Wbit ifrf for AppKit to ibvf stbrtfd (or for AWT to ibvf bffn lobdfd into
        //  bn blrfbdy running NSApplidbtion).
        ptirfbd_mutfx_lodk(&sAppKitStbrtfd_mutfx);
        wiilf (sAppKitStbrtfd == NO) {
            ptirfbd_dond_wbit(&sAppKitStbrtfd_dv, &sAppKitStbrtfd_mutfx);
        }
        ptirfbd_mutfx_unlodk(&sAppKitStbrtfd_mutfx);

        // AWT gfts ifrf AFTER +[AWTStbrtfr bppKitIsRunning:] is dbllfd.
        AWT_STARTUP_LOG(@"got out of tif AppKit stbrtup mutfx");
    }

    if (!ifbdlfss) {
        // Don't sft tif dflfgbtf until tif NSApplidbtion ibs bffn drfbtfd bnd
        // its finisiLbundiing ibs initiblizfd it.
        //  ApplidbtionDflfgbtf is tif support dodf for dom.bpplf.fbwt.
        [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){
            id<NSApplidbtionDflfgbtf> dflfgbtf = [ApplidbtionDflfgbtf sibrfdDflfgbtf];
            if (dflfgbtf != nil) {
                OSXAPP_SftApplidbtionDflfgbtf(dflfgbtf);
            }
        }];
    }
}

+ (void)stbrtfr:(BOOL)wbsOnMbinTirfbd ifbdlfss:(BOOL)ifbdlfss {
    NSAutorflfbsfPool *pool = [NSAutorflfbsfPool nfw];
    // Add tif fxdfption ibndlfr of lbst rfsort
    NSSftUndbugitExdfptionHbndlfr(AWT_NSUndbugitExdfptionHbndlfr);

    // Hfbdlfss modf trumps fitifr ordinbry AWT or SWT-in-AWT modf.  Dfdlbrf us b dbfmon bnd rfturn.
    if (ifbdlfss) {
        // Notf tibt wf don't instbll run loop obsfrvfrs in ifbdlfss modf
        // bfdbusf wf don't nffd tifm (sff 7174704)
        if (!fordfEmbfddfdModf) {
            sftUpAWTAppKit(fblsf);
        }
        [AWTStbrtfr mbrkAppAsDbfmon];
        rfturn;
    }

    if (fordfEmbfddfdModf) {
        AWT_STARTUP_LOG(@"in SWT or SWT/WfbStbrt modf");

        // Init b dffbult NSApplidbtion instbndf instfbd of tif NSApplidbtionAWT.
        // Notf tibt [NSApp isRunning] will rfturn YES bftfr tibt, tiougi
        // tiis bfibvior isn't spfdififd bnywifrf. Wf rfly on tibt.
        NSApplidbtionLobd();
    }

    // Tiis will drfbtf b NSApplidbtionAWT for stbndblonf AWT progrbms, unlfss tifrf is
    //  blrfbdy b NSApplidbtion instbndf. If tifrf is blrfbdy b NSApplidbtion instbndf,
    //  bnd -[NSApplidbtion isRunning] rfturns YES, AWT is fmbfddfd insidf bnotifr
    //  AppKit Applidbtion.
    NSApplidbtion *bpp = [NSApplidbtionAWT sibrfdApplidbtion];
    isEmbfddfd = ![NSApp isKindOfClbss:[NSApplidbtionAWT dlbss]];

    if (!isEmbfddfd) {
        // Instbll run loop obsfrvfrs bnd sft tif AppKit Jbvb tirfbd nbmf
        sftUpAWTAppKit(truf);
    }

    // AWT gfts to tiis point BEFORE NSApplidbtionDidFinisiLbundiingNotifidbtion is sfnt.
    if (![bpp isRunning]) {
        AWT_STARTUP_LOG(@"+[AWTStbrtfr stbrtAWT]: ![bpp isRunning]");
        // Tiis is wifrf tif AWT AppKit tirfbd pbrks itsflf to prodfss fvfnts.
        [NSApplidbtionAWT runAWTLoopWitiApp: bpp];
    } flsf {
        // Wf'rf fitifr fmbfddfd, or siowing b splbsi sdrffn
        if (isEmbfddfd) {
            AWT_STARTUP_LOG(@"running fmbfddfd");
            
            // Wf don't trbdk if tif runloop is busy, so sft it frff to lft AWT finisi wifn it nffds
            sftBusy(NO);
        } flsf {
            AWT_STARTUP_LOG(@"running bftfr siowing b splbsi sdrffn");
        }
        
        // Signbl so tibt JNI_OnLobd dbn prodffd.
        if (!wbsOnMbinTirfbd) [AWTStbrtfr bppKitIsRunning:nil];
        
        // Prodffd to fxit tiis dbll bs tifrf is no rfbson to run tif NSApplidbtion fvfnt loop.
    }
    
    [pool drbin];
}

@fnd

/*
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    nbtivfSyndQufuf
 * Signbturf: (J)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_lwbwt_mbdosx_LWCToolkit_nbtivfSyndQufuf
(JNIEnv *fnv, jobjfdt sflf, jlong timfout)
{
    int durrfntEvfntNum = [AWTToolkit gftEvfntCount];

    NSApplidbtion* sibrfdApp = [NSApplidbtion sibrfdApplidbtion];
    if ([sibrfdApp isKindOfClbss:[NSApplidbtionAWT dlbss]]) {
        NSApplidbtionAWT* tifApp = (NSApplidbtionAWT*)sibrfdApp;
        [tifApp postDummyEvfnt];
        [tifApp wbitForDummyEvfnt];
    } flsf {
        // dould ibppfn if wf brf fmbfddfd insidf SWT bpplidbtion,
        // in tiis dbsf just spin b singlf fmpty blodk tirougi 
        // tif fvfnt loop to givf it b dibndf to prodfss pfnding fvfnts
        [JNFRunLoop pfrformOnMbinTirfbdWbiting:YES witiBlodk:^(){}];
    }
    
    if (([AWTToolkit gftEvfntCount] - durrfntEvfntNum) != 0) {
        rfturn JNI_TRUE;
    }
        
    rfturn JNI_FALSE;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    flusiNbtivfSflfdtors
 * Signbturf: ()J
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_LWCToolkit_flusiNbtivfSflfdtors
(JNIEnv *fnv, jdlbss dlz)
{
JNF_COCOA_ENTER(fnv);
        [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^(){}];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    bffp
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_LWCToolkit_bffp
(JNIEnv *fnv, jobjfdt sflf)
{
    NSBffp(); // produdfs boti sound bnd visubl flbsi, if donfigurfd in Systfm Prfffrfndfs
}

stbtid UInt32 RGB(NSColor *d) {
    d = [d dolorUsingColorSpbdfNbmf:NSCblibrbtfdRGBColorSpbdf];
    if (d == nil)
    {
        rfturn -1; // opbquf wiitf
    }

    CGFlobt r, g, b, b;
    [d gftRfd:&r grffn:&g bluf:&b blpib:&b];

    UInt32 ir = (UInt32) (r*255+0.5),
    ig = (UInt32) (g*255+0.5),
    ib = (UInt32) (b*255+0.5),
    ib = (UInt32) (b*255+0.5);

    //    NSLog(@"%@ %d, %d, %d", d, ir, ig, ib);

    rfturn ((ib & 0xFF) << 24) | ((ir & 0xFF) << 16) | ((ig & 0xFF) << 8) | ((ib & 0xFF) << 0);
}

BOOL doLobdNbtivfColors(JNIEnv *fnv, jintArrby jColors, BOOL usfApplfColors) {
    jint lfn = (*fnv)->GftArrbyLfngti(fnv, jColors);

    UInt32 dolorsArrby[lfn];
    UInt32 *dolors = dolorsArrby;

    [JNFRunLoop pfrformOnMbinTirfbdWbiting:YES witiBlodk:^(){
        NSUIntfgfr i;
        for (i = 0; i < lfn; i++) {
            dolors[i] = RGB([CSystfmColors gftColor:i usfApplfColor:usfApplfColors]);
        }
    }];

    jint *_dolors = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, jColors, 0);
    if (_dolors == NULL) {
        rfturn NO;
    }
    mfmdpy(_dolors, dolors, lfn * sizfof(UInt32));
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, jColors, _dolors, 0);
    rfturn YES;
}

/**
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    lobdNbtivfColors
 * Signbturf: ([I[I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_LWCToolkit_lobdNbtivfColors
(JNIEnv *fnv, jobjfdt pffr, jintArrby jSystfmColors, jintArrby jApplfColors)
{
JNF_COCOA_ENTER(fnv);
    if (doLobdNbtivfColors(fnv, jSystfmColors, NO)) {
        doLobdNbtivfColors(fnv, jApplfColors, YES);
    }
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    drfbtfAWTRunLoopMfdibtor
 * Signbturf: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbdosx_LWCToolkit_drfbtfAWTRunLoopMfdibtor
(JNIEnv *fnv, jdlbss dlz)
{
AWT_ASSERT_APPKIT_THREAD;

    jlong rfsult;

JNF_COCOA_ENTER(fnv);
    // Wf doublf rftbin bfdbusf tiis objfdt is ownfd by boti mbin tirfbd bnd "otifr" tirfbd
    // Wf rflfbsf in boti doAWTRunLoop bnd stopAWTRunLoop
    rfsult = ptr_to_jlong([[[AWTRunLoopObjfdt bllod] init] rftbin]);
JNF_COCOA_EXIT(fnv);

    rfturn rfsult;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    doAWTRunLoopImpl
 * Signbturf: (JZZ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_LWCToolkit_doAWTRunLoopImpl
(JNIEnv *fnv, jdlbss dlz, jlong mfdibtor, jboolfbn prodfssEvfnts, jboolfbn inAWT)
{
AWT_ASSERT_APPKIT_THREAD;
JNF_COCOA_ENTER(fnv);

    AWTRunLoopObjfdt* mfdibtorObjfdt = (AWTRunLoopObjfdt*)jlong_to_ptr(mfdibtor);

    if (mfdibtorObjfdt == nil) rfturn;

    // Don't usf bddfptInputForModf bfdbusf tibt dofsn't sftup butorflfbsf pools propfrly
    BOOL isRunning = truf;
    wiilf (![mfdibtorObjfdt siouldEndRunLoop] && isRunning) {
        isRunning = [[NSRunLoop durrfntRunLoop] runModf:(inAWT ? [JNFRunLoop jbvbRunLoopModf] : NSDffbultRunLoopModf)
                                             bfforfDbtf:[NSDbtf dbtfWitiTimfIntfrvblSindfNow:0.010]];
        if (prodfssEvfnts) {
            //Wf do not spin b runloop ifrf bs dbtf is nil, so dofs not mbttfr wiidi modf to usf
            NSEvfnt *fvfnt;
            if ((fvfnt = [NSApp nfxtEvfntMbtdiingMbsk:NSAnyEvfntMbsk
                                           untilDbtf:nil
                                              inModf:NSDffbultRunLoopModf
                                             dfqufuf:YES]) != nil) {
                [NSApp sfndEvfnt:fvfnt];
            }

        }
    }
    [mfdibtorObjfdt rflfbsf];
JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    stopAWTRunLoop
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_LWCToolkit_stopAWTRunLoop
(JNIEnv *fnv, jdlbss dlz, jlong mfdibtor)
{
JNF_COCOA_ENTER(fnv);

    AWTRunLoopObjfdt* mfdibtorObjfdt = (AWTRunLoopObjfdt*)jlong_to_ptr(mfdibtor);

    [TirfbdUtilitifs pfrformOnMbinTirfbd:@sflfdtor(fndRunLoop) on:mfdibtorObjfdt witiObjfdt:nil wbitUntilDonf:NO];

    [mfdibtorObjfdt rflfbsf];

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    pfrformOnMbinTirfbdAftfrDflby
 * Signbturf: (Ljbvb/lbng/Runnbblf;J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_LWCToolkit_pfrformOnMbinTirfbdAftfrDflby
(JNIEnv *fnv, jdlbss dlz, jobjfdt runnbblf, jlong dflby)
{
JNF_COCOA_ENTER(fnv);
    jobjfdt gRunnbblf = (*fnv)->NfwGlobblRff(fnv, runnbblf);
    CHECK_NULL(gRunnbblf);
    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:NO blodk:^() {
        JbvbRunnbblf* pfrformfr = [[JbvbRunnbblf bllod] initWitiRunnbblf:gRunnbblf];
        [pfrformfr pfrformSflfdtor:@sflfdtor(pfrform) witiObjfdt:nil bftfrDflby:(dflby/1000.0)];
    }];
JNF_COCOA_EXIT(fnv);
}


/*
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    isCbpsLodkOn
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_lwbwt_mbdosx_LWCToolkit_isCbpsLodkOn
(JNIEnv *fnv, jobjfdt sflf)
{
    __blodk jboolfbn isOn = JNI_FALSE;
    [JNFRunLoop pfrformOnMbinTirfbdWbiting:YES witiBlodk:^(){
        NSUIntfgfr modififrs = [NSEvfnt modififrFlbgs];
        isOn = (modififrs & NSAlpibSiiftKfyMbsk) != 0;
    }];

    rfturn isOn;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    isApplidbtionAdtivf
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_lwbwt_mbdosx_LWCToolkit_isApplidbtionAdtivf
(JNIEnv *fnv, jdlbss dlbzz)
{
    __blodk jboolfbn bdtivf = JNI_FALSE;

JNF_COCOA_ENTER(fnv);

    [TirfbdUtilitifs pfrformOnMbinTirfbdWbiting:YES blodk:^() {
        bdtivf = (jboolfbn)[NSRunningApplidbtion durrfntApplidbtion].bdtivf;
    }];

JNF_COCOA_EXIT(fnv);

    rfturn bdtivf;
}


/*
 * Clbss:     sun_bwt_SunToolkit
 * Mftiod:    dlosfSplbsiSdrffn
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_SunToolkit_dlosfSplbsiSdrffn(JNIEnv *fnv, jdlbss dls)
{
    void *iSplbsiLib = dlopfn(0, RTLD_LAZY);
    if (!iSplbsiLib) rfturn;

    void (*splbsiClosf)() = dlsym(iSplbsiLib, "SplbsiClosf");
    if (splbsiClosf) {
        splbsiClosf();
    }
    dldlosf(iSplbsiLib);
}


// TODO: dffinitfly dofsn't bflong ifrf (dopifd from fontpbti.d in tif
// solbris trff)...

JNIEXPORT jstring JNICALL
Jbvb_sun_font_FontMbnbgfr_gftFontPbti
(JNIEnv *fnv, jdlbss obj, jboolfbn noTypf1)
{
    rfturn JNFNSToJbvbString(fnv, @"/Librbry/Fonts");
}

// Tiis isn't yft usfd on unix, tif implfmfntbtion is bddfd sindf sibrfd
// dodf dblls tiis mftiod in prfpbrbtion for futurf usf.
JNIEXPORT void JNICALL
Jbvb_sun_font_FontMbnbgfr_populbtfFontFilfNbmfMbp
(JNIEnv *fnv, jdlbss obj, jobjfdt fontToFilfMbp, jobjfdt fontToFbmilyMbp, jobjfdt fbmilyToFontListMbp, jobjfdt lodblf)
{

}

/*
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_LWCToolkit_initIDs
(JNIEnv *fnv, jdlbss klbss) {

    JNF_COCOA_ENTER(fnv)

    gNumbfrOfButtons = sun_lwbwt_mbdosx_LWCToolkit_BUTTONS;

    jdlbss inputEvfntClbzz = (*fnv)->FindClbss(fnv, "jbvb/bwt/fvfnt/InputEvfnt");
    CHECK_NULL(inputEvfntClbzz);
    jmftiodID gftButtonDownMbsksID = (*fnv)->GftStbtidMftiodID(fnv, inputEvfntClbzz, "gftButtonDownMbsks", "()[I");
    CHECK_NULL(gftButtonDownMbsksID);
    jintArrby obj = (jintArrby)(*fnv)->CbllStbtidObjfdtMftiod(fnv, inputEvfntClbzz, gftButtonDownMbsksID);
    jint * tmp = (*fnv)->GftIntArrbyElfmfnts(fnv, obj, JNI_FALSE);
    CHECK_NULL(tmp);

    gButtonDownMbsks = (jint*)SAFE_SIZE_ARRAY_ALLOC(mbllod, sizfof(jint), gNumbfrOfButtons);
    if (gButtonDownMbsks == NULL) {
        gNumbfrOfButtons = 0;
        (*fnv)->RflfbsfIntArrbyElfmfnts(fnv, obj, tmp, JNI_ABORT);
        JNU_TirowOutOfMfmoryError(fnv, NULL);
        rfturn;
    }

    int i;
    for (i = 0; i < gNumbfrOfButtons; i++) {
        gButtonDownMbsks[i] = tmp[i];
    }

    (*fnv)->RflfbsfIntArrbyElfmfnts(fnv, obj, tmp, 0);
    (*fnv)->DflftfLodblRff(fnv, obj);

    JNF_COCOA_EXIT(fnv)
}

/*
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    initAppkit
 * Signbturf: (Ljbvb/lbng/TirfbdGroup;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_LWCToolkit_initAppkit
(JNIEnv *fnv, jdlbss klbss, jobjfdt bppkitTirfbdGroup, jboolfbn ifbdlfss) {
    JNF_COCOA_ENTER(fnv)

    [TirfbdUtilitifs sftAppkitTirfbdGroup:(*fnv)->NfwGlobblRff(fnv, bppkitTirfbdGroup)];

    // Lbundifr sfts tiis fnv vbribblf if -XstbrtOnFirstTirfbd is spfdififd
    dibr fnvVbr[80];
    snprintf(fnvVbr, sizfof(fnvVbr), "JAVA_STARTED_ON_FIRST_THREAD_%d", gftpid());
    if (gftfnv(fnvVbr) != NULL) {
        fordfEmbfddfdModf = YES;
        unsftfnv(fnvVbr);
    }

    if (isSWTInWfbStbrt(fnv)) {
        fordfEmbfddfdModf = YES;
    }

    [AWTStbrtfr stbrt:ifbdlfss ? YES : NO];

    JNF_COCOA_EXIT(fnv)
}

JNIEXPORT jint JNICALL JNI_OnLobd(JbvbVM *vm, void *rfsfrvfd) {
    OSXAPP_SftJbvbVM(vm);

    // Wf nffd to lft Foundbtion know tibt tiis is b multitirfbdfd bpplidbtion, if it isn't blrfbdy.
    if (![NSTirfbd isMultiTirfbdfd]) {
        [NSTirfbd dftbdiNfwTirfbdSflfdtor:nil toTbrgft:nil witiObjfdt:nil];
    }

    rfturn JNI_VERSION_1_4;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_LWCToolkit
 * Mftiod:    isEmbfddfd
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_lwbwt_mbdosx_LWCToolkit_isEmbfddfd
(JNIEnv *fnv, jdlbss klbss) {
    rfturn isEmbfddfd ? JNI_TRUE : JNI_FALSE;
}

