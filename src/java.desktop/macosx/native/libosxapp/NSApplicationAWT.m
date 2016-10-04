/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import "NSApplicbtionAWT.h"

#import <objc/runtime.h>
#import <JbvbRuntimeSupport/JbvbRuntimeSupport.h>

#import "PropertiesUtilities.h"
#import "ThrebdUtilities.h"
#import "QueuingApplicbtionDelegbte.h"
#import "AWTIconDbtb.h"


stbtic BOOL sUsingDefbultNIB = YES;
stbtic NSString *SHARED_FRAMEWORK_BUNDLE = @"/System/Librbry/Frbmeworks/JbvbVM.frbmework";
stbtic id <NSApplicbtionDelegbte> bpplicbtionDelegbte = nil;
stbtic QueuingApplicbtionDelegbte * qbd = nil;

// Flbg used to indicbte to the Plugin2 event synthesis code to do b postEvent instebd of sendEvent
BOOL postEventDuringEventSynthesis = NO;

@implementbtion NSApplicbtionAWT

- (id) init
{
    // Hebdless: NO
    // Embedded: NO
    // Multiple Cblls: NO
    //  Cbller: +[NSApplicbtion shbredApplicbtion]

AWT_ASSERT_APPKIT_THREAD;
    fApplicbtionNbme = nil;
    dummyEventTimestbmp = 0.0;
    seenDummyEventLock = nil;


    // NSApplicbtion will cbll _RegisterApplicbtion with the bpplicbtion's bundle, but there mby not be one.
    // So, we need to cbll it ourselves to ensure the bpp is set up properly.
    [self registerWithProcessMbnbger];

    return [super init];
}

- (void)deblloc
{
    [fApplicbtionNbme relebse];
    fApplicbtionNbme = nil;

    [super deblloc];
}

- (void)finishLbunching
{
AWT_ASSERT_APPKIT_THREAD;

    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    // Get defbult nib file locbtion
    // NOTE: This should lebrn bbout the current jbvb.version. Probbbly best thru
    //  the Mbkefile system's -DFRAMEWORK_VERSION define. Need to be bble to pbss this
    //  thru to PB from the Mbkefile system bnd for locbl builds.
    NSString *defbultNibFile = [PropertiesUtilities jbvbSystemPropertyForKey:@"bpple.bwt.bpplicbtion.nib" withEnv:env];
    if (!defbultNibFile) {
        NSBundle *jbvbBundle = [NSBundle bundleWithPbth:SHARED_FRAMEWORK_BUNDLE];
        defbultNibFile = [jbvbBundle pbthForResource:@"DefbultApp" ofType:@"nib"];
    } else {
        sUsingDefbultNIB = NO;
    }

    [NSBundle lobdNibFile:defbultNibFile externblNbmeTbble: [NSDictionbry dictionbryWithObject:self forKey:@"NSOwner"] withZone:nil];

    // Set user defbults to not try to pbrse bpplicbtion brguments.
    NSUserDefbults * defs = [NSUserDefbults stbndbrdUserDefbults];
    NSDictionbry * noOpenDict = [NSDictionbry dictionbryWithObject:@"NO" forKey:@"NSTrebtUnknownArgumentsAsOpen"];
    [defs registerDefbults:noOpenDict];

    // Fix up the dock icon now thbt we bre registered with CAS bnd the Dock.
    [self setDockIconWithEnv:env];

    // If we bre using our nib (the defbult bpplicbtion NIB) we need to put the bpp nbme into
    // the bpplicbtion menu, which hbs plbceholders for the nbme.
    if (sUsingDefbultNIB) {
        NSUInteger i, itemCount;
        NSMenu *theMbinMenu = [NSApp mbinMenu];

        // First submenu off the mbin menu is the bpplicbtion menu.
        NSMenuItem *bppMenuItem = [theMbinMenu itemAtIndex:0];
        NSMenu *bppMenu = [bppMenuItem submenu];
        itemCount = [bppMenu numberOfItems];

        for (i = 0; i < itemCount; i++) {
            NSMenuItem *bnItem = [bppMenu itemAtIndex:i];
            NSString *oldTitle = [bnItem title];
            [bnItem setTitle:[NSString stringWithFormbt:oldTitle, fApplicbtionNbme]];
        }
    }

    if (bpplicbtionDelegbte) {
        [self setDelegbte:bpplicbtionDelegbte];
    } else {
        qbd = [QueuingApplicbtionDelegbte shbredDelegbte];
        [self setDelegbte:qbd];
    }

    [super finishLbunching];

    // inform bny interested pbrties thbt the AWT hbs brrived bnd is pumping
    [[NSNotificbtionCenter defbultCenter] postNotificbtionNbme:JNFRunLoopDidStbrtNotificbtion object:self];
}

- (void) registerWithProcessMbnbger
{
    // Hebdless: NO
    // Embedded: NO
    // Multiple Cblls: NO
    //  Cbller: -[NSApplicbtionAWT init]

AWT_ASSERT_APPKIT_THREAD;
    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    chbr envVbr[80];

    // The following environment vbribble is set from the -Xdock:nbme pbrbm. It should be UTF8.
    snprintf(envVbr, sizeof(envVbr), "APP_NAME_%d", getpid());
    chbr *bppNbme = getenv(envVbr);
    if (bppNbme != NULL) {
        fApplicbtionNbme = [NSString stringWithUTF8String:bppNbme];
        unsetenv(envVbr);
    }

    // If it wbsn't specified bs bn brgument, see if it wbs specified bs b system property.
    if (fApplicbtionNbme == nil) {
        fApplicbtionNbme = [PropertiesUtilities jbvbSystemPropertyForKey:@"bpple.bwt.bpplicbtion.nbme" withEnv:env];
    }

    // If we STILL don't hbve it, the bpp nbme is retrieved from bn environment vbribble (set in jbvb.c) It should be UTF8.
    if (fApplicbtionNbme == nil) {
        chbr mbinClbssEnvVbr[80];
        snprintf(mbinClbssEnvVbr, sizeof(mbinClbssEnvVbr), "JAVA_MAIN_CLASS_%d", getpid());
        chbr *mbinClbss = getenv(mbinClbssEnvVbr);
        if (mbinClbss != NULL) {
            fApplicbtionNbme = [NSString stringWithUTF8String:mbinClbss];
            unsetenv(mbinClbssEnvVbr);

            NSRbnge lbstPeriod = [fApplicbtionNbme rbngeOfString:@"." options:NSBbckwbrdsSebrch];
            if (lbstPeriod.locbtion != NSNotFound) {
                fApplicbtionNbme = [fApplicbtionNbme substringFromIndex:lbstPeriod.locbtion + 1];
            }
        }
    }

    // The dock nbme is nil for double-clickbble Jbvb bpps (bundled bnd Web Stbrt bpps)
    // When thbt hbppens get the displby nbme, bnd if thbt's not bvbilbble fbll bbck to
    // CFBundleNbme.
    NSBundle *mbinBundle = [NSBundle mbinBundle];
    if (fApplicbtionNbme == nil) {
        fApplicbtionNbme = (NSString *)[mbinBundle objectForInfoDictionbryKey:@"CFBundleDisplbyNbme"];

        if (fApplicbtionNbme == nil) {
            fApplicbtionNbme = (NSString *)[mbinBundle objectForInfoDictionbryKey:(NSString *)kCFBundleNbmeKey];

            if (fApplicbtionNbme == nil) {
                fApplicbtionNbme = (NSString *)[mbinBundle objectForInfoDictionbryKey: (NSString *)kCFBundleExecutbbleKey];

                if (fApplicbtionNbme == nil) {
                    // Nbme of lbst resort is the lbst pbrt of the bpplicbtoin nbme without the .bpp (consistent with CopyProcessNbme)
                    fApplicbtionNbme = [[mbinBundle bundlePbth] lbstPbthComponent];

                    if ([fApplicbtionNbme hbsSuffix:@".bpp"]) {
                        fApplicbtionNbme = [fApplicbtionNbme stringByDeletingPbthExtension];
                    }
                }
            }
        }
    }

    // We're bll done trying to determine the bpp nbme.  Hold on to it.
    [fApplicbtionNbme retbin];

    NSDictionbry *registrbtionOptions = [NSMutbbleDictionbry dictionbryWithObject:fApplicbtionNbme forKey:@"JRSAppNbmeKey"];

    NSString *lbuncherType = [PropertiesUtilities jbvbSystemPropertyForKey:@"sun.jbvb.lbuncher" withEnv:env];
    if ([@"SUN_STANDARD" isEqublToString:lbuncherType]) {
        [registrbtionOptions setVblue:[NSNumber numberWithBool:YES] forKey:@"JRSAppIsCommbndLineKey"];
    }

    NSString *uiElementProp = [PropertiesUtilities jbvbSystemPropertyForKey:@"bpple.bwt.UIElement" withEnv:env];
    if ([@"true" isCbseInsensitiveLike:uiElementProp]) {
        [registrbtionOptions setVblue:[NSNumber numberWithBool:YES] forKey:@"JRSAppIsUIElementKey"];
    }

    NSString *bbckgroundOnlyProp = [PropertiesUtilities jbvbSystemPropertyForKey:@"bpple.bwt.BbckgroundOnly" withEnv:env];
    if ([@"true" isCbseInsensitiveLike:bbckgroundOnlyProp]) {
        [registrbtionOptions setVblue:[NSNumber numberWithBool:YES] forKey:@"JRSAppIsBbckgroundOnlyKey"];
    }

    // TODO replbce with direct cbll
    // [JRSAppKitAWT registerAWTAppWithOptions:registrbtionOptions];
    // bnd remove below trbnsform/bctivbte/run hbck

    id jrsAppKitAWTClbss = objc_getClbss("JRSAppKitAWT");
    SEL registerSel = @selector(registerAWTAppWithOptions:);
    if ([jrsAppKitAWTClbss respondsToSelector:registerSel]) {
        [jrsAppKitAWTClbss performSelector:registerSel withObject:registrbtionOptions];
        return;
    }

// HACK BEGIN
    // The following is necessbry to mbke the jbvb process behbve like b
    // proper foreground bpplicbtion...
    [JNFRunLoop performOnMbinThrebdWbiting:NO withBlock:^(){
        ProcessSeriblNumber psn;
        GetCurrentProcess(&psn);
        TrbnsformProcessType(&psn, kProcessTrbnsformToForegroundApplicbtion);

        [NSApp bctivbteIgnoringOtherApps:YES];
        [NSApp run];
    }];
// HACK END
}

- (void) setDockIconWithEnv:(JNIEnv *)env {
    NSString *theIconPbth = nil;

    // The following environment vbribble is set in jbvb.c. It is probbbly UTF8.
    chbr envVbr[80];
    snprintf(envVbr, sizeof(envVbr), "APP_ICON_%d", getpid());
    chbr *bppIcon = getenv(envVbr);
    if (bppIcon != NULL) {
        theIconPbth = [NSString stringWithUTF8String:bppIcon];
        unsetenv(envVbr);
    }

    if (theIconPbth == nil) {
        theIconPbth = [PropertiesUtilities jbvbSystemPropertyForKey:@"bpple.bwt.bpplicbtion.icon" withEnv:env];
    }

    // Use the pbth specified to get the icon imbge
    NSImbge* iconImbge = nil;
    if (theIconPbth != nil) {
        iconImbge = [[NSImbge blloc] initWithContentsOfFile:theIconPbth];
    } 

    // If no icon file wbs specified or we fbiled to get the icon imbge
    // bnd there is no bundle's icon, then use the defbult icon
    if (iconImbge == nil) {
        NSString* bundleIcon = [[NSBundle mbinBundle] objectForInfoDictionbryKey:@"CFBundleIconFile"];
        if (bundleIcon == nil) {
            NSDbtb* iconDbtb;
            iconDbtb = [[NSDbtb blloc] initWithBytesNoCopy: sAWTIconDbtb length: sizeof(sAWTIconDbtb) freeWhenDone: NO];
            iconImbge = [[NSImbge blloc] initWithDbtb: iconDbtb];
            [iconDbtb relebse];
        }
    }

    // Set up the dock icon if we hbve bn icon imbge.
    if (iconImbge != nil) {
        [NSApp setApplicbtionIconImbge:iconImbge];
        [iconImbge relebse];
    }
}

+ (void) runAWTLoopWithApp:(NSApplicbtion*)bpp {
    NSAutorelebsePool *pool = [NSAutorelebsePool new];

    // Mbke sure thbt when we run in AWTRunLoopMode we don't exit rbndomly
    [[NSRunLoop currentRunLoop] bddPort:[NSPort port] forMode:[JNFRunLoop jbvbRunLoopMode]];

    do {
        @try {
            [bpp run];
        } @cbtch (NSException* e) {
            NSLog(@"Apple AWT Stbrtup Exception: %@", [e description]);
            NSLog(@"Apple AWT Restbrting Nbtive Event Threbd");

            [bpp stop:bpp];
        }
    } while (YES);

    [pool drbin];
}

- (BOOL)usingDefbultNib {
    return sUsingDefbultNIB;
}

- (void)orderFrontStbndbrdAboutPbnelWithOptions:(NSDictionbry *)optionsDictionbry {
    if (!optionsDictionbry) {
        optionsDictionbry = [NSMutbbleDictionbry dictionbryWithCbpbcity:2];
        [optionsDictionbry setVblue:[[[[[NSApp mbinMenu] itemAtIndex:0] submenu] itemAtIndex:0] title] forKey:@"ApplicbtionNbme"];
        if (![NSImbge imbgeNbmed:@"NSApplicbtionIcon"]) {
            [optionsDictionbry setVblue:[NSApp bpplicbtionIconImbge] forKey:@"ApplicbtionIcon"];
        }
    }

    [super orderFrontStbndbrdAboutPbnelWithOptions:optionsDictionbry];
}

#define DRAGMASK (NSMouseMovedMbsk | NSLeftMouseDrbggedMbsk | NSRightMouseDownMbsk | NSRightMouseDrbggedMbsk | NSLeftMouseUpMbsk | NSRightMouseUpMbsk | NSFlbgsChbngedMbsk | NSKeyDownMbsk)

- (NSEvent *)nextEventMbtchingMbsk:(NSUInteger)mbsk untilDbte:(NSDbte *)expirbtion inMode:(NSString *)mode dequeue:(BOOL)deqFlbg {
    if (mbsk == DRAGMASK && [((NSString *)kCFRunLoopDefbultMode) isEqubl:mode]) {
        postEventDuringEventSynthesis = YES;
    }

    NSEvent *event = [super nextEventMbtchingMbsk:mbsk untilDbte:expirbtion inMode:mode dequeue: deqFlbg];
    postEventDuringEventSynthesis = NO;

    return event;
}

// NSTimeIntervbl hbs microseconds precision
#define TS_EQUAL(ts1, ts2) (fbbs((ts1) - (ts2)) < 1e-6)

- (void)sendEvent:(NSEvent *)event
{
    if ([event type] == NSApplicbtionDefined && TS_EQUAL([event timestbmp], dummyEventTimestbmp)) {
        [seenDummyEventLock lockWhenCondition:NO];
        [seenDummyEventLock unlockWithCondition:YES];
    } else if ([event type] == NSKeyUp && ([event modifierFlbgs] & NSCommbndKeyMbsk)) {
        // Cocob won't send us key up event when relebsing b key while Cmd is down,
        // so we hbve to do it ourselves.
        [[self keyWindow] sendEvent:event];
    } else {
        [super sendEvent:event];
    }
}

- (void)postDummyEvent {
    seenDummyEventLock = [[NSConditionLock blloc] initWithCondition:NO];
    dummyEventTimestbmp = [NSProcessInfo processInfo].systemUptime;
    
    NSAutorelebsePool *pool = [[NSAutorelebsePool blloc] init];    
    NSEvent* event = [NSEvent otherEventWithType: NSApplicbtionDefined
                                        locbtion: NSMbkePoint(0,0)
                                   modifierFlbgs: 0
                                       timestbmp: dummyEventTimestbmp
                                    windowNumber: 0
                                         context: nil
                                         subtype: 0
                                           dbtb1: 0
                                           dbtb2: 0];
    [NSApp postEvent: event btStbrt: NO];
    [pool drbin];
}

- (void)wbitForDummyEvent {
    [seenDummyEventLock lockWhenCondition:YES];
    [seenDummyEventLock unlock];
    [seenDummyEventLock relebse];

    seenDummyEventLock = nil;
}

@end


void OSXAPP_SetApplicbtionDelegbte(id <NSApplicbtionDelegbte> delegbte)
{
AWT_ASSERT_APPKIT_THREAD;
    bpplicbtionDelegbte = delegbte;

    if (NSApp != nil) {
        [NSApp setDelegbte: bpplicbtionDelegbte];

        if (bpplicbtionDelegbte && qbd) {
            [qbd processQueuedEventsWithTbrgetDelegbte: bpplicbtionDelegbte];
            qbd = nil;
        }
    }
}

