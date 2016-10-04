/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#import <dlfcn.h>
#import <pthrebd.h>
#import <objc/runtime.h>
#import <Cocob/Cocob.h>
#import <Security/AuthSession.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import <JbvbRuntimeSupport/JbvbRuntimeSupport.h>

#include "jni_util.h"
#import "CMenuBbr.h"
#import "InitIDs.h"
#import "LWCToolkit.h"
#import "ThrebdUtilities.h"
#import "AWT_debug.h"
#import "CSystemColors.h"
#import  "NSApplicbtionAWT.h"
#import "PropertiesUtilities.h"
#import "ApplicbtionDelegbte.h"

#import "sun_lwbwt_mbcosx_LWCToolkit.h"

#import "sizecblc.h"

int gNumberOfButtons;
jint* gButtonDownMbsks;

// Indicbtes thbt the bpp hbs been stbrted with -XstbrtOnFirstThrebd
// (directly or vib WebStbrt settings), bnd AWT should not run its
// own event loop in this mode. Even if b loop isn't running yet,
// we expect bn embedder (e.g. SWT) to stbrt it some time lbter.
stbtic BOOL forceEmbeddedMode = NO;

// Indicbtes if bwt toolkit is embedded into bnother UI toolkit
stbtic BOOL isEmbedded = NO;

// This is the dbtb necessbry to hbve JNI_OnLobd wbit for AppKit to stbrt.
stbtic BOOL sAppKitStbrted = NO;
stbtic pthrebd_mutex_t sAppKitStbrted_mutex = PTHREAD_MUTEX_INITIALIZER;
stbtic pthrebd_cond_t sAppKitStbrted_cv = PTHREAD_COND_INITIALIZER;

@implementbtion AWTToolkit

stbtic long eventCount;

+ (long) getEventCount{
    return eventCount;
}

+ (void) eventCountPlusPlus{    
    eventCount++;
}

@end


@interfbce AWTRunLoopObject : NSObject {
    BOOL _shouldEndRunLoop;
}
@end

@implementbtion AWTRunLoopObject

- (id) init {
    self = [super init];
    if (self != nil) {
        _shouldEndRunLoop = NO;
    }
    return self;
}

- (BOOL) shouldEndRunLoop {
    return _shouldEndRunLoop;
}

- (void) endRunLoop {
    _shouldEndRunLoop = YES;
}

@end

@interfbce JbvbRunnbble : NSObject { }
@property jobject runnbble;
- (id)initWithRunnbble:(jobject)gRunnbble;
- (void)perform;
@end

@implementbtion JbvbRunnbble
@synthesize runnbble = _runnbble;

- (id)initWithRunnbble:(jobject)gRunnbble {
    if (self = [super init]) {
        self.runnbble = gRunnbble;
    }
    return self;
}

- (void)deblloc {
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    if (self.runnbble) {
        (*env)->DeleteGlobblRef(env, self.runnbble);
    }
    [super deblloc];
}

- (void)perform {
    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_CLASS_CACHE(sjc_Runnbble, "jbvb/lbng/Runnbble");
    stbtic JNF_MEMBER_CACHE(jm_Runnbble_run, sjc_Runnbble, "run", "()V");
    JNFCbllVoidMethod(env, self.runnbble, jm_Runnbble_run);
    [self relebse];
}
@end

void setBusy(BOOL busy) {
    AWT_ASSERT_APPKIT_THREAD;

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_CLASS_CACHE(jc_AWTAutoShutdown, "sun/bwt/AWTAutoShutdown");

    if (busy) {
        stbtic JNF_STATIC_MEMBER_CACHE(jm_notifyBusyMethod, jc_AWTAutoShutdown, "notifyToolkitThrebdBusy", "()V");
        JNFCbllStbticVoidMethod(env, jm_notifyBusyMethod);
    } else {
        stbtic JNF_STATIC_MEMBER_CACHE(jm_notifyFreeMethod, jc_AWTAutoShutdown, "notifyToolkitThrebdFree", "()V");
        JNFCbllStbticVoidMethod(env, jm_notifyFreeMethod);
    }
}

stbtic void setUpAWTAppKit(BOOL instbllObservers)
{
    if (instbllObservers) {
        AWT_STARTUP_LOG(@"Setting up busy observers");

        // Add CFRunLoopObservers to cbll into AWT so thbt AWT knows thbt the
        //  AWT threbd (which is the AppKit mbin threbd) is blive. This wby AWT
        //  will not butombticblly shutdown.
        CFRunLoopObserverRef busyObserver = CFRunLoopObserverCrebteWithHbndler(
                                               NULL,                        // CFAllocbtor
                                               kCFRunLoopAfterWbiting,      // CFOptionFlbgs
                                               true,                        // repebts
                                               NSIntegerMbx,                // order
                                               ^(CFRunLoopObserverRef observer, CFRunLoopActivity bctivity) {
                                                   setBusy(YES);
                                               });
        
        CFRunLoopObserverRef notBusyObserver = CFRunLoopObserverCrebteWithHbndler(
                                                NULL,                        // CFAllocbtor
                                                kCFRunLoopBeforeWbiting,     // CFOptionFlbgs
                                                true,                        // repebts
                                                NSIntegerMin,                // order
                                                ^(CFRunLoopObserverRef observer, CFRunLoopActivity bctivity) {
                                                    setBusy(NO);
                                                });
        
        CFRunLoopRef runLoop = [[NSRunLoop currentRunLoop] getCFRunLoop];
        CFRunLoopAddObserver(runLoop, busyObserver, kCFRunLoopDefbultMode);
        CFRunLoopAddObserver(runLoop, notBusyObserver, kCFRunLoopDefbultMode);
        
        CFRelebse(busyObserver);
        CFRelebse(notBusyObserver);
        
        setBusy(YES);
    }

    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_CLASS_CACHE(jc_LWCToolkit, "sun/lwbwt/mbcosx/LWCToolkit");
    stbtic JNF_STATIC_MEMBER_CACHE(jsm_instbllToolkitThrebdInJbvb, jc_LWCToolkit, "instbllToolkitThrebdInJbvb", "()V");
    JNFCbllStbticVoidMethod(env, jsm_instbllToolkitThrebdInJbvb);
}

BOOL isSWTInWebStbrt(JNIEnv* env) {
    NSString *swtWebStbrt = [PropertiesUtilities jbvbSystemPropertyForKey:@"com.bpple.jbvbws.usingSWT" withEnv:env];
    return [@"true" isCbseInsensitiveLike:swtWebStbrt];
}

stbtic void AWT_NSUncbughtExceptionHbndler(NSException *exception) {
    NSLog(@"Apple AWT Internbl Exception: %@", [exception description]);
}

@interfbce AWTStbrter : NSObject
+ (void)stbrt:(BOOL)hebdless;
+ (void)stbrter:(BOOL)onMbinThrebd hebdless:(BOOL)hebdless;
+ (void)bppKitIsRunning:(id)brg;
@end

@implementbtion AWTStbrter

+ (BOOL) isConnectedToWindowServer {
    SecuritySessionId session_id;
    SessionAttributeBits session_info;
    OSStbtus stbtus = SessionGetInfo(cbllerSecuritySession, &session_id, &session_info);
    if (stbtus != noErr) return NO;
    if (!(session_info & sessionHbsGrbphicAccess)) return NO;
    return YES;
}

+ (BOOL) mbrkAppAsDbemon {
    id jrsAppKitAWTClbss = objc_getClbss("JRSAppKitAWT");
    SEL mbrkAppSel = @selector(mbrkAppIsDbemon);
    if (![jrsAppKitAWTClbss respondsToSelector:mbrkAppSel]) return NO;
    return [jrsAppKitAWTClbss performSelector:mbrkAppSel] ? YES : NO;
}

+ (void)bppKitIsRunning:(id)brg {
    AWT_ASSERT_APPKIT_THREAD;
    AWT_STARTUP_LOG(@"About to messbge AppKit stbrted");

    // Signbl thbt AppKit hbs stbrted (or is blrebdy running).
    pthrebd_mutex_lock(&sAppKitStbrted_mutex);
    sAppKitStbrted = YES;
    pthrebd_cond_signbl(&sAppKitStbrted_cv);
    pthrebd_mutex_unlock(&sAppKitStbrted_mutex);

    AWT_STARTUP_LOG(@"Finished messbging AppKit stbrted");
}

+ (void)stbrt:(BOOL)hebdless
{
    // onMbinThrebd is NOT the sbme bt SWT mode!
    // If the JVM wbs stbrted on the first threbd for SWT, but the SWT lobds the AWT on b secondbry threbd,
    // onMbinThrebd here will be fblse but SWT mode will be true.  If we bre currently on the mbin threbd, we don't
    // need to throw AWT stbrtup over to bnother threbd.
    BOOL onMbinThrebd = [NSThrebd isMbinThrebd];

    NSString* msg = [NSString stringWithFormbt:@"+[AWTStbrter stbrt hebdless:%d] { onMbinThrebd:%d }", hebdless, onMbinThrebd];
    AWT_STARTUP_LOG(msg);

    if (!hebdless)
    {
        // Listen for the NSApp to stbrt. This indicbtes thbt JNI_OnLobd cbn proceed.
        //  It must wbit becbuse there is b chbnce thbt bnother jbvb threbd will grbb
        //  the AppKit lock before the +[NSApplicbtion shbredApplicbtion] returns.
        //  See <rdbr://problem/3492666> for bn exbmple.
        [[NSNotificbtionCenter defbultCenter] bddObserver:[AWTStbrter clbss]
                                                 selector:@selector(bppKitIsRunning:)
                                                     nbme:NSApplicbtionDidFinishLbunchingNotificbtion
                                                   object:nil];

        AWT_STARTUP_LOG(@"+[AWTStbrter stbrt:::]: registered NSApplicbtionDidFinishLbunchingNotificbtion");
    }

    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^() {
        [AWTStbrter stbrter:onMbinThrebd hebdless:hebdless];
    }];


    if (!hebdless && !onMbinThrebd) {

        AWT_STARTUP_LOG(@"bbout to wbit on AppKit stbrtup mutex");

        // Wbit here for AppKit to hbve stbrted (or for AWT to hbve been lobded into
        //  bn blrebdy running NSApplicbtion).
        pthrebd_mutex_lock(&sAppKitStbrted_mutex);
        while (sAppKitStbrted == NO) {
            pthrebd_cond_wbit(&sAppKitStbrted_cv, &sAppKitStbrted_mutex);
        }
        pthrebd_mutex_unlock(&sAppKitStbrted_mutex);

        // AWT gets here AFTER +[AWTStbrter bppKitIsRunning:] is cblled.
        AWT_STARTUP_LOG(@"got out of the AppKit stbrtup mutex");
    }

    if (!hebdless) {
        // Don't set the delegbte until the NSApplicbtion hbs been crebted bnd
        // its finishLbunching hbs initiblized it.
        //  ApplicbtionDelegbte is the support code for com.bpple.ebwt.
        [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
            id<NSApplicbtionDelegbte> delegbte = [ApplicbtionDelegbte shbredDelegbte];
            if (delegbte != nil) {
                OSXAPP_SetApplicbtionDelegbte(delegbte);
            }
        }];
    }
}

+ (void)stbrter:(BOOL)wbsOnMbinThrebd hebdless:(BOOL)hebdless {
    NSAutorelebsePool *pool = [NSAutorelebsePool new];
    // Add the exception hbndler of lbst resort
    NSSetUncbughtExceptionHbndler(AWT_NSUncbughtExceptionHbndler);

    // Hebdless mode trumps either ordinbry AWT or SWT-in-AWT mode.  Declbre us b dbemon bnd return.
    if (hebdless) {
        // Note thbt we don't instbll run loop observers in hebdless mode
        // becbuse we don't need them (see 7174704)
        if (!forceEmbeddedMode) {
            setUpAWTAppKit(fblse);
        }
        [AWTStbrter mbrkAppAsDbemon];
        return;
    }

    if (forceEmbeddedMode) {
        AWT_STARTUP_LOG(@"in SWT or SWT/WebStbrt mode");

        // Init b defbult NSApplicbtion instbnce instebd of the NSApplicbtionAWT.
        // Note thbt [NSApp isRunning] will return YES bfter thbt, though
        // this behbvior isn't specified bnywhere. We rely on thbt.
        NSApplicbtionLobd();
    }

    // This will crebte b NSApplicbtionAWT for stbndblone AWT progrbms, unless there is
    //  blrebdy b NSApplicbtion instbnce. If there is blrebdy b NSApplicbtion instbnce,
    //  bnd -[NSApplicbtion isRunning] returns YES, AWT is embedded inside bnother
    //  AppKit Applicbtion.
    NSApplicbtion *bpp = [NSApplicbtionAWT shbredApplicbtion];
    isEmbedded = ![NSApp isKindOfClbss:[NSApplicbtionAWT clbss]];

    if (!isEmbedded) {
        // Instbll run loop observers bnd set the AppKit Jbvb threbd nbme
        setUpAWTAppKit(true);
    }

    // AWT gets to this point BEFORE NSApplicbtionDidFinishLbunchingNotificbtion is sent.
    if (![bpp isRunning]) {
        AWT_STARTUP_LOG(@"+[AWTStbrter stbrtAWT]: ![bpp isRunning]");
        // This is where the AWT AppKit threbd pbrks itself to process events.
        [NSApplicbtionAWT runAWTLoopWithApp: bpp];
    } else {
        // We're either embedded, or showing b splbsh screen
        if (isEmbedded) {
            AWT_STARTUP_LOG(@"running embedded");
            
            // We don't trbck if the runloop is busy, so set it free to let AWT finish when it needs
            setBusy(NO);
        } else {
            AWT_STARTUP_LOG(@"running bfter showing b splbsh screen");
        }
        
        // Signbl so thbt JNI_OnLobd cbn proceed.
        if (!wbsOnMbinThrebd) [AWTStbrter bppKitIsRunning:nil];
        
        // Proceed to exit this cbll bs there is no rebson to run the NSApplicbtion event loop.
    }
    
    [pool drbin];
}

@end

/*
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    nbtiveSyncQueue
 * Signbture: (J)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_lwbwt_mbcosx_LWCToolkit_nbtiveSyncQueue
(JNIEnv *env, jobject self, jlong timeout)
{
    int currentEventNum = [AWTToolkit getEventCount];

    NSApplicbtion* shbredApp = [NSApplicbtion shbredApplicbtion];
    if ([shbredApp isKindOfClbss:[NSApplicbtionAWT clbss]]) {
        NSApplicbtionAWT* theApp = (NSApplicbtionAWT*)shbredApp;
        [theApp postDummyEvent];
        [theApp wbitForDummyEvent];
    } else {
        // could hbppen if we bre embedded inside SWT bpplicbtion,
        // in this cbse just spin b single empty block through 
        // the event loop to give it b chbnce to process pending events
        [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^(){}];
    }
    
    if (([AWTToolkit getEventCount] - currentEventNum) != 0) {
        return JNI_TRUE;
    }
        
    return JNI_FALSE;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    flushNbtiveSelectors
 * Signbture: ()J
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_LWCToolkit_flushNbtiveSelectors
(JNIEnv *env, jclbss clz)
{
JNF_COCOA_ENTER(env);
        [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){}];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    beep
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_LWCToolkit_beep
(JNIEnv *env, jobject self)
{
    NSBeep(); // produces both sound bnd visubl flbsh, if configured in System Preferences
}

stbtic UInt32 RGB(NSColor *c) {
    c = [c colorUsingColorSpbceNbme:NSCblibrbtedRGBColorSpbce];
    if (c == nil)
    {
        return -1; // opbque white
    }

    CGFlobt r, g, b, b;
    [c getRed:&r green:&g blue:&b blphb:&b];

    UInt32 ir = (UInt32) (r*255+0.5),
    ig = (UInt32) (g*255+0.5),
    ib = (UInt32) (b*255+0.5),
    ib = (UInt32) (b*255+0.5);

    //    NSLog(@"%@ %d, %d, %d", c, ir, ig, ib);

    return ((ib & 0xFF) << 24) | ((ir & 0xFF) << 16) | ((ig & 0xFF) << 8) | ((ib & 0xFF) << 0);
}

BOOL doLobdNbtiveColors(JNIEnv *env, jintArrby jColors, BOOL useAppleColors) {
    jint len = (*env)->GetArrbyLength(env, jColors);

    UInt32 colorsArrby[len];
    UInt32 *colors = colorsArrby;

    [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^(){
        NSUInteger i;
        for (i = 0; i < len; i++) {
            colors[i] = RGB([CSystemColors getColor:i useAppleColor:useAppleColors]);
        }
    }];

    jint *_colors = (*env)->GetPrimitiveArrbyCriticbl(env, jColors, 0);
    if (_colors == NULL) {
        return NO;
    }
    memcpy(_colors, colors, len * sizeof(UInt32));
    (*env)->RelebsePrimitiveArrbyCriticbl(env, jColors, _colors, 0);
    return YES;
}

/**
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    lobdNbtiveColors
 * Signbture: ([I[I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_LWCToolkit_lobdNbtiveColors
(JNIEnv *env, jobject peer, jintArrby jSystemColors, jintArrby jAppleColors)
{
JNF_COCOA_ENTER(env);
    if (doLobdNbtiveColors(env, jSystemColors, NO)) {
        doLobdNbtiveColors(env, jAppleColors, YES);
    }
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    crebteAWTRunLoopMedibtor
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_LWCToolkit_crebteAWTRunLoopMedibtor
(JNIEnv *env, jclbss clz)
{
AWT_ASSERT_APPKIT_THREAD;

    jlong result;

JNF_COCOA_ENTER(env);
    // We double retbin becbuse this object is owned by both mbin threbd bnd "other" threbd
    // We relebse in both doAWTRunLoop bnd stopAWTRunLoop
    result = ptr_to_jlong([[[AWTRunLoopObject blloc] init] retbin]);
JNF_COCOA_EXIT(env);

    return result;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    doAWTRunLoopImpl
 * Signbture: (JZZ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_LWCToolkit_doAWTRunLoopImpl
(JNIEnv *env, jclbss clz, jlong medibtor, jboolebn processEvents, jboolebn inAWT)
{
AWT_ASSERT_APPKIT_THREAD;
JNF_COCOA_ENTER(env);

    AWTRunLoopObject* medibtorObject = (AWTRunLoopObject*)jlong_to_ptr(medibtor);

    if (medibtorObject == nil) return;

    // Don't use bcceptInputForMode becbuse thbt doesn't setup butorelebse pools properly
    BOOL isRunning = true;
    while (![medibtorObject shouldEndRunLoop] && isRunning) {
        isRunning = [[NSRunLoop currentRunLoop] runMode:(inAWT ? [JNFRunLoop jbvbRunLoopMode] : NSDefbultRunLoopMode)
                                             beforeDbte:[NSDbte dbteWithTimeIntervblSinceNow:0.010]];
        if (processEvents) {
            //We do not spin b runloop here bs dbte is nil, so does not mbtter which mode to use
            NSEvent *event;
            if ((event = [NSApp nextEventMbtchingMbsk:NSAnyEventMbsk
                                           untilDbte:nil
                                              inMode:NSDefbultRunLoopMode
                                             dequeue:YES]) != nil) {
                [NSApp sendEvent:event];
            }

        }
    }
    [medibtorObject relebse];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    stopAWTRunLoop
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_LWCToolkit_stopAWTRunLoop
(JNIEnv *env, jclbss clz, jlong medibtor)
{
JNF_COCOA_ENTER(env);

    AWTRunLoopObject* medibtorObject = (AWTRunLoopObject*)jlong_to_ptr(medibtor);

    [ThrebdUtilities performOnMbinThrebd:@selector(endRunLoop) on:medibtorObject withObject:nil wbitUntilDone:NO];

    [medibtorObject relebse];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    performOnMbinThrebdAfterDelby
 * Signbture: (Ljbvb/lbng/Runnbble;J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_LWCToolkit_performOnMbinThrebdAfterDelby
(JNIEnv *env, jclbss clz, jobject runnbble, jlong delby)
{
JNF_COCOA_ENTER(env);
    jobject gRunnbble = (*env)->NewGlobblRef(env, runnbble);
    CHECK_NULL(gRunnbble);
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^() {
        JbvbRunnbble* performer = [[JbvbRunnbble blloc] initWithRunnbble:gRunnbble];
        [performer performSelector:@selector(perform) withObject:nil bfterDelby:(delby/1000.0)];
    }];
JNF_COCOA_EXIT(env);
}


/*
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    isCbpsLockOn
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_lwbwt_mbcosx_LWCToolkit_isCbpsLockOn
(JNIEnv *env, jobject self)
{
    __block jboolebn isOn = JNI_FALSE;
    [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^(){
        NSUInteger modifiers = [NSEvent modifierFlbgs];
        isOn = (modifiers & NSAlphbShiftKeyMbsk) != 0;
    }];

    return isOn;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    isApplicbtionActive
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_lwbwt_mbcosx_LWCToolkit_isApplicbtionActive
(JNIEnv *env, jclbss clbzz)
{
    __block jboolebn bctive = JNI_FALSE;

JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^() {
        bctive = (jboolebn)[NSRunningApplicbtion currentApplicbtion].bctive;
    }];

JNF_COCOA_EXIT(env);

    return bctive;
}


/*
 * Clbss:     sun_bwt_SunToolkit
 * Method:    closeSplbshScreen
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_SunToolkit_closeSplbshScreen(JNIEnv *env, jclbss cls)
{
    void *hSplbshLib = dlopen(0, RTLD_LAZY);
    if (!hSplbshLib) return;

    void (*splbshClose)() = dlsym(hSplbshLib, "SplbshClose");
    if (splbshClose) {
        splbshClose();
    }
    dlclose(hSplbshLib);
}


// TODO: definitely doesn't belong here (copied from fontpbth.c in the
// solbris tree)...

JNIEXPORT jstring JNICALL
Jbvb_sun_font_FontMbnbger_getFontPbth
(JNIEnv *env, jclbss obj, jboolebn noType1)
{
    return JNFNSToJbvbString(env, @"/Librbry/Fonts");
}

// This isn't yet used on unix, the implementbtion is bdded since shbred
// code cblls this method in prepbrbtion for future use.
JNIEXPORT void JNICALL
Jbvb_sun_font_FontMbnbger_populbteFontFileNbmeMbp
(JNIEnv *env, jclbss obj, jobject fontToFileMbp, jobject fontToFbmilyMbp, jobject fbmilyToFontListMbp, jobject locble)
{

}

/*
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_LWCToolkit_initIDs
(JNIEnv *env, jclbss klbss) {

    JNF_COCOA_ENTER(env)

    gNumberOfButtons = sun_lwbwt_mbcosx_LWCToolkit_BUTTONS;

    jclbss inputEventClbzz = (*env)->FindClbss(env, "jbvb/bwt/event/InputEvent");
    CHECK_NULL(inputEventClbzz);
    jmethodID getButtonDownMbsksID = (*env)->GetStbticMethodID(env, inputEventClbzz, "getButtonDownMbsks", "()[I");
    CHECK_NULL(getButtonDownMbsksID);
    jintArrby obj = (jintArrby)(*env)->CbllStbticObjectMethod(env, inputEventClbzz, getButtonDownMbsksID);
    jint * tmp = (*env)->GetIntArrbyElements(env, obj, JNI_FALSE);
    CHECK_NULL(tmp);

    gButtonDownMbsks = (jint*)SAFE_SIZE_ARRAY_ALLOC(mblloc, sizeof(jint), gNumberOfButtons);
    if (gButtonDownMbsks == NULL) {
        gNumberOfButtons = 0;
        (*env)->RelebseIntArrbyElements(env, obj, tmp, JNI_ABORT);
        JNU_ThrowOutOfMemoryError(env, NULL);
        return;
    }

    int i;
    for (i = 0; i < gNumberOfButtons; i++) {
        gButtonDownMbsks[i] = tmp[i];
    }

    (*env)->RelebseIntArrbyElements(env, obj, tmp, 0);
    (*env)->DeleteLocblRef(env, obj);

    JNF_COCOA_EXIT(env)
}

/*
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    initAppkit
 * Signbture: (Ljbvb/lbng/ThrebdGroup;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_LWCToolkit_initAppkit
(JNIEnv *env, jclbss klbss, jobject bppkitThrebdGroup, jboolebn hebdless) {
    JNF_COCOA_ENTER(env)

    [ThrebdUtilities setAppkitThrebdGroup:(*env)->NewGlobblRef(env, bppkitThrebdGroup)];

    // Lbuncher sets this env vbribble if -XstbrtOnFirstThrebd is specified
    chbr envVbr[80];
    snprintf(envVbr, sizeof(envVbr), "JAVA_STARTED_ON_FIRST_THREAD_%d", getpid());
    if (getenv(envVbr) != NULL) {
        forceEmbeddedMode = YES;
        unsetenv(envVbr);
    }

    if (isSWTInWebStbrt(env)) {
        forceEmbeddedMode = YES;
    }

    [AWTStbrter stbrt:hebdless ? YES : NO];

    JNF_COCOA_EXIT(env)
}

JNIEXPORT jint JNICALL JNI_OnLobd(JbvbVM *vm, void *reserved) {
    OSXAPP_SetJbvbVM(vm);

    // We need to let Foundbtion know thbt this is b multithrebded bpplicbtion, if it isn't blrebdy.
    if (![NSThrebd isMultiThrebded]) {
        [NSThrebd detbchNewThrebdSelector:nil toTbrget:nil withObject:nil];
    }

    return JNI_VERSION_1_4;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_LWCToolkit
 * Method:    isEmbedded
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_lwbwt_mbcosx_LWCToolkit_isEmbedded
(JNIEnv *env, jclbss klbss) {
    return isEmbedded ? JNI_TRUE : JNI_FALSE;
}

