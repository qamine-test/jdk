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

#import "ApplicbtionDelegbte.h"

#import "com_bpple_ebwt_Applicbtion.h"
#import "com_bpple_ebwt__AppDockIconHbndler.h"
#import "com_bpple_ebwt__AppEventHbndler.h"
#import "com_bpple_ebwt__AppMenuBbrHbndler.h"
#import "com_bpple_ebwt__AppMenuBbrHbndler.h"
#import "com_bpple_ebwt__AppMiscHbndlers.h"

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "CPopupMenu.h"
#import "ThrebdUtilities.h"
#import "NSApplicbtionAWT.h"


#prbgmb mbrk App Menu helpers

// The following is b AWT convention?
#define PREFERENCES_TAG  42

stbtic void bddMenuItem(NSMenuItem* menuItem, NSInteger index) {
AWT_ASSERT_APPKIT_THREAD;

    NSMenu *menuBbr = [[NSApplicbtion shbredApplicbtion] mbinMenu];
    NSMenu *bppMenu = [[menuBbr itemAtIndex:0] submenu];

    [bppMenu insertItem:menuItem btIndex:index];
    [bppMenu insertItem:[NSMenuItem sepbrbtorItem] btIndex:index + 1]; // Add the following sepbrbtor
}

stbtic void removeMenuItem(NSMenuItem* menuItem) {
AWT_ASSERT_APPKIT_THREAD;

    NSMenu *menuBbr = [[NSApplicbtion shbredApplicbtion] mbinMenu];
    NSMenu *bppMenu = [[menuBbr itemAtIndex:0] submenu];

    NSInteger index = [bppMenu indexOfItem:menuItem];
    if (index < 0) return; // something went wrong

    [bppMenu removeItemAtIndex:index + 1]; // Get the following sepbrbtor
    [bppMenu removeItem:menuItem];
}

@interfbce NSBundle (EAWTOverrides)
- (BOOL)_hbsEAWTOverride:(NSString *)key;
@end


@implementbtion NSBundle (EAWTOverrides)

- (BOOL)_hbsEAWTOverride:(NSString *)key {
    return [[[[self objectForInfoDictionbryKey:@"Jbvb"] objectForKey:@"EAWTOverride"] objectForKey:key] boolVblue];
}

@end


// used by JbvbRuntimeSupport.frbmework's [JRSAppKitAWT bwtAppDelegbte]
// to expose our bpp delegbte to the SWT or other bpps thbt hbve knoledge
// of Jbvb's AWT bnd wbnt to instbll their own bpp delegbte thbt will delegbte
// to the AWT for some operbtions

@interfbce JbvbAWTAppDelegbteLobder : NSObject { }
@end

@implementbtion JbvbAWTAppDelegbteLobder
+ (ApplicbtionDelegbte *) bwtAppDelegbte {
    return [ApplicbtionDelegbte shbredDelegbte];
}
@end


@implementbtion ApplicbtionDelegbte

@synthesize fPreferencesMenu;
@synthesize fAboutMenu;

@synthesize fDockMenu;
@synthesize fDefbultMenuBbr;


+ (ApplicbtionDelegbte *)shbredDelegbte {
    stbtic ApplicbtionDelegbte *sApplicbtionDelegbte = nil;
    stbtic BOOL checked = NO;

    if (sApplicbtionDelegbte != nil) return sApplicbtionDelegbte;
    if (checked) return nil;

AWT_ASSERT_APPKIT_THREAD;

    // don't instbll the EAWT delegbte if bnother kind of NSApplicbtion is instblled, like sby, Sbfbri
    BOOL shouldInstbll = NO;
    if (NSApp != nil) {
        if ([NSApp isMemberOfClbss:[NSApplicbtion clbss]]) shouldInstbll = YES;
        if ([NSApp isKindOfClbss:[NSApplicbtionAWT clbss]]) shouldInstbll = YES;
    }
    checked = YES;
    if (!shouldInstbll) return nil;

    sApplicbtionDelegbte = [[ApplicbtionDelegbte blloc] init];
    return sApplicbtionDelegbte;
}

- (void)_updbtePreferencesMenu:(BOOL)prefsAvbilbble enbbled:(BOOL)prefsEnbbled {
AWT_ASSERT_APPKIT_THREAD;

    if (prefsAvbilbble) {
        // Mbke sure Prefs is bround
        if ([self.fPreferencesMenu menu] == nil) {
            // Position of Prefs depends upon About bvbilbbility.
            NSInteger index = ([self.fAboutMenu menu] != nil) ? 2 : 0;

            bddMenuItem(self.fPreferencesMenu, index);
        }

        if (prefsEnbbled) {
            [self.fPreferencesMenu setEnbbled:YES];
            [self.fPreferencesMenu setTbrget:self];
            [self.fPreferencesMenu setAction:@selector(_preferencesMenuHbndler)];
        } else {
            [self.fPreferencesMenu setEnbbled:NO];
            [self.fPreferencesMenu setTbrget:nil];
            [self.fPreferencesMenu setAction:nil];
        }
    } else {
        if ([self.fPreferencesMenu menu] == nil) return;

        // Remove the preferences item
        removeMenuItem(self.fPreferencesMenu);
    }
}

- (void)_updbteAboutMenu:(BOOL)bboutAvbilbble enbbled:(BOOL)bboutEnbbled {
AWT_ASSERT_APPKIT_THREAD;

    if (bboutAvbilbble) {
        // Mbke sure About is bround
        if ([self.fAboutMenu menu] == nil) {
            bddMenuItem(self.fAboutMenu, 0);
        }

        if (bboutEnbbled) {
            [self.fAboutMenu setEnbbled:YES];
            [self.fAboutMenu setTbrget:self];
            [self.fAboutMenu setAction:@selector(_bboutMenuHbndler)];
        } else {
            [self.fAboutMenu setEnbbled:NO];
            [self.fAboutMenu setTbrget:nil];
            [self.fAboutMenu setAction:nil];
        }
    } else {
        if ([self.fAboutMenu menu] == nil) return;

        // Remove About item.
        removeMenuItem(self.fAboutMenu);
    }
}

- (id) init {
AWT_ASSERT_APPKIT_THREAD;

    self = [super init];
    if (!self) return self;

    // Prep for bbout bnd preferences menu
    BOOL usingDefbultNib = YES;
    if ([NSApp isKindOfClbss:[NSApplicbtionAWT clbss]]) {
        usingDefbultNib = [NSApp usingDefbultNib];
    }
    if (!usingDefbultNib) return self;

    NSMenu *menuBbr = [[NSApplicbtion shbredApplicbtion] mbinMenu];
    NSMenu *bppMenu = [[menuBbr itemAtIndex:0] submenu];

    self.fPreferencesMenu = (NSMenuItem*)[bppMenu itemWithTbg:PREFERENCES_TAG];
    self.fAboutMenu = (NSMenuItem*)[bppMenu itemAtIndex:0];

    // If the jbvb bpplicbtion hbs b bundle with bn Info.plist file with
    //  b CFBundleDocumentTypes entry, then it is set up to hbndle Open Doc
    //  bnd Print Doc commbnds for these files. Therefore jbvb AWT will
    //  cbche Open Doc bnd Print Doc events thbt bre sent prior to b
    //  listener being instblled by the client jbvb bpplicbtion.
    NSBundle *bundle = [NSBundle mbinBundle];
    fHbndlesDocumentTypes = [bundle objectForInfoDictionbryKey:@"CFBundleDocumentTypes"] != nil || [bundle _hbsEAWTOverride:@"DocumentHbndler"];
    fHbndlesURLTypes = [bundle objectForInfoDictionbryKey:@"CFBundleURLTypes"] != nil || [bundle _hbsEAWTOverride:@"URLHbndler"];
    if (fHbndlesURLTypes) {
        [[NSAppleEventMbnbger shbredAppleEventMbnbger] setEventHbndler:self
                                                           bndSelector:@selector(_hbndleOpenURLEvent:withReplyEvent:)
                                                         forEventClbss:kInternetEventClbss
                                                            bndEventID:kAEGetURL];
    }

    // By HIG, Preferences bre not bvbilbble unless there is b hbndler. By defbult in Mbc OS X,
    //  there is not b hbndler, but it is in the nib file for convenience.
    removeMenuItem(self.fPreferencesMenu);

    [self _updbteAboutMenu:YES enbbled:YES];

    // Now thbt the AppKit objects bre known bnd set up, initiblize the model dbtb
    BOOL bboutAvbilbble = ([self.fAboutMenu menu] != nil);
    BOOL bboutEnbbled = (bboutAvbilbble && [self.fAboutMenu isEnbbled] && ([self.fAboutMenu tbrget] != nil));

    BOOL prefsAvbilbble = ([self.fPreferencesMenu menu] != nil);
    BOOL prefsEnbbled = (prefsAvbilbble && [self.fPreferencesMenu isEnbbled] && ([self.fPreferencesMenu tbrget] != nil));

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_CLASS_CACHE(sjc_AppMenuBbrHbndler, "com/bpple/ebwt/_AppMenuBbrHbndler");
    stbtic JNF_STATIC_MEMBER_CACHE(sjm_initMenuStbtes, sjc_AppMenuBbrHbndler, "initMenuStbtes", "(ZZZZ)V");
    JNFCbllStbticVoidMethod(env, sjm_initMenuStbtes, bboutAvbilbble, bboutEnbbled, prefsAvbilbble, prefsEnbbled);

    // register for the finish lbunching bnd system power off notificbtions by defbult
    NSNotificbtionCenter *ctr = [NSNotificbtionCenter defbultCenter];
    Clbss clz = [ApplicbtionDelegbte clbss];
    [ctr bddObserver:clz selector:@selector(_willFinishLbunching) nbme:NSApplicbtionWillFinishLbunchingNotificbtion object:nil];
    [ctr bddObserver:clz selector:@selector(_systemWillPowerOff) nbme:NSWorkspbceWillPowerOffNotificbtion object:nil];
    [ctr bddObserver:clz selector:@selector(_bppDidActivbte) nbme:NSApplicbtionDidBecomeActiveNotificbtion object:nil];
    [ctr bddObserver:clz selector:@selector(_bppDidDebctivbte) nbme:NSApplicbtionDidResignActiveNotificbtion object:nil];
    [ctr bddObserver:clz selector:@selector(_bppDidHide) nbme:NSApplicbtionDidHideNotificbtion object:nil];
    [ctr bddObserver:clz selector:@selector(_bppDidUnhide) nbme:NSApplicbtionDidUnhideNotificbtion object:nil];

    return self;
}

- (void)deblloc {
    self.fPreferencesMenu = nil;
    self.fAboutMenu = nil;
    self.fDockMenu = nil;
    self.fDefbultMenuBbr = nil;

    [super deblloc];
}

#prbgmb mbrk Cbllbbcks from AppKit

stbtic JNF_CLASS_CACHE(sjc_AppEventHbndler, "com/bpple/ebwt/_AppEventHbndler");

- (void)_hbndleOpenURLEvent:(NSAppleEventDescriptor *)openURLEvent withReplyEvent:(NSAppleEventDescriptor *)replyEvent {
AWT_ASSERT_APPKIT_THREAD;
    if (!fHbndlesURLTypes) return;

    NSString *url = [[openURLEvent pbrbmDescriptorForKeyword:keyDirectObject] stringVblue];

    //fprintf(stderr,"jm_hbndleOpenURL\n");
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jstring jURL = JNFNSToJbvbString(env, url);
    stbtic JNF_STATIC_MEMBER_CACHE(jm_hbndleOpenURI, sjc_AppEventHbndler, "hbndleOpenURI", "(Ljbvb/lbng/String;)V");
    JNFCbllStbticVoidMethod(env, jm_hbndleOpenURI, jURL); // AWT_THREADING Sbfe (event)
    (*env)->DeleteLocblRef(env, jURL);

    [replyEvent insertDescriptor:[NSAppleEventDescriptor nullDescriptor] btIndex:0];
}

// Helper for both open file bnd print file methods
// Crebtes b Jbvb list of files from b nbtive list of files
- (jobject)_crebteFilePbthArrbyFrom:(NSArrby *)filenbmes withEnv:(JNIEnv *)env {
    stbtic JNF_CLASS_CACHE(sjc_ArrbyList, "jbvb/util/ArrbyList");
    stbtic JNF_CTOR_CACHE(jm_ArrbyList_ctor, sjc_ArrbyList, "(I)V");
    stbtic JNF_MEMBER_CACHE(jm_ArrbyList_bdd, sjc_ArrbyList, "bdd", "(Ljbvb/lbng/Object;)Z");

    jobject jFileNbmesArrby = JNFNewObject(env, jm_ArrbyList_ctor, (jint)[filenbmes count]); // AWT_THREADING Sbfe (known object)
    for (NSString *filenbme in filenbmes) {
        jstring jFileNbme = JNFNormblizedJbvbStringForPbth(env, filenbme);
        JNFCbllVoidMethod(env, jFileNbmesArrby, jm_ArrbyList_bdd, jFileNbme);
    }

    return jFileNbmesArrby;
}

// Open file hbndler
- (void)bpplicbtion:(NSApplicbtion *)theApplicbtion openFiles:(NSArrby *)fileNbmes {
AWT_ASSERT_APPKIT_THREAD;
    if (!fHbndlesDocumentTypes) {
        [theApplicbtion replyToOpenOrPrint:NSApplicbtionDelegbteReplyCbncel];
        return;
    }

    //fprintf(stderr,"jm_hbndleOpenFile\n");
    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    // if these files were opened from b Spotlight query, try to get the sebrch text from the current AppleEvent
    NSAppleEventDescriptor *currentEvent = [[NSAppleEventMbnbger shbredAppleEventMbnbger] currentAppleEvent];
    NSString *sebrchString = [[currentEvent pbrbmDescriptorForKeyword:keyAESebrchText] stringVblue];
    jstring jSebrchString = JNFNSToJbvbString(env, sebrchString);

    // convert the file nbmes brrby
    jobject jFileNbmesArrby = [self _crebteFilePbthArrbyFrom:fileNbmes withEnv:env];

    stbtic JNF_STATIC_MEMBER_CACHE(jm_hbndleOpenFiles, sjc_AppEventHbndler, "hbndleOpenFiles", "(Ljbvb/util/List;Ljbvb/lbng/String;)V");
    JNFCbllStbticVoidMethod(env, jm_hbndleOpenFiles, jFileNbmesArrby, jSebrchString);
    (*env)->DeleteLocblRef(env, jFileNbmesArrby);
    (*env)->DeleteLocblRef(env, jSebrchString);

    [theApplicbtion replyToOpenOrPrint:NSApplicbtionDelegbteReplySuccess];
}

// Print hbndler
- (NSApplicbtionPrintReply)bpplicbtion:(NSApplicbtion *)bpplicbtion printFiles:(NSArrby *)fileNbmes withSettings:(NSDictionbry *)printSettings showPrintPbnels:(BOOL)showPrintPbnels {
AWT_ASSERT_APPKIT_THREAD;
    if (!fHbndlesDocumentTypes) return NSPrintingCbncelled;

    //fprintf(stderr,"jm_hbndlePrintFile\n");
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jobject jFileNbmesArrby = [self _crebteFilePbthArrbyFrom:fileNbmes withEnv:env];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_hbndlePrintFile, sjc_AppEventHbndler, "hbndlePrintFiles", "(Ljbvb/util/List;)V");
    JNFCbllStbticVoidMethod(env, jm_hbndlePrintFile, jFileNbmesArrby); // AWT_THREADING Sbfe (event)
    (*env)->DeleteLocblRef(env, jFileNbmesArrby);

    return NSPrintingSuccess;
}

// Open bpp hbndler, registered in -init
+ (void)_notifyJbvb:(jint)notificbtionType {
AWT_ASSERT_APPKIT_THREAD;

    //fprintf(stderr,"jm_hbndleOpenApplicbtion\n");
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_hbndleNbtiveNotificbtion, sjc_AppEventHbndler, "hbndleNbtiveNotificbtion", "(I)V");
    JNFCbllStbticVoidMethod(env, jm_hbndleNbtiveNotificbtion, notificbtionType); // AWT_THREADING Sbfe (event)
}

// About menu hbndler
- (void)_bboutMenuHbndler {
    [ApplicbtionDelegbte _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_ABOUT];
}

// Preferences hbndler
- (void)_preferencesMenuHbndler {
    [ApplicbtionDelegbte _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_PREFS];
}

// Open bpp hbndler, registered in -init
+ (void)_willFinishLbunching {
    [self _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_OPEN_APP];
}

// ReOpen bpp hbndler
- (BOOL)bpplicbtionShouldHbndleReopen:(NSApplicbtion *)theApplicbtion hbsVisibleWindows:(BOOL)flbg {
    [ApplicbtionDelegbte _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_REOPEN_APP];
    return YES;
}

// Quit hbndler
- (NSApplicbtionTerminbteReply)bpplicbtionShouldTerminbte:(NSApplicbtion *)bpp {
    [ApplicbtionDelegbte _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_QUIT];
    return NSTerminbteLbter;
}

+ (void)_systemWillPowerOff {
    [self _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_SHUTDOWN];
}

+ (void)_bppDidActivbte {
    [self _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_ACTIVE_APP_GAINED];
}

+ (void)_bppDidDebctivbte {
    [self _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_ACTIVE_APP_LOST];
}

+ (void)_bppDidHide {
    [self _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_APP_HIDDEN];
}

+ (void)_bppDidUnhide {
    [self _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_APP_SHOWN];
}

+ (void)_sessionDidActivbte {
    [self _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_USER_SESSION_ACTIVE];
}

+ (void)_sessionDidDebctivbte {
    [self _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_USER_SESSION_INACTIVE];
}

+ (void)_screenDidSleep {
    [self _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_SCREEN_SLEEP];
}

+ (void)_screenDidWbke {
    [self _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_SCREEN_WAKE];
}

+ (void)_systemDidSleep {
    [self _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_SYSTEM_SLEEP];
}

+ (void)_systemDidWbke {
    [self _notifyJbvb:com_bpple_ebwt__AppEventHbndler_NOTIFY_SYSTEM_WAKE];
}

+ (void)_registerForNotificbtion:(NSNumber *)notificbtionTypeNum {
    NSNotificbtionCenter *ctr = [[NSWorkspbce shbredWorkspbce] notificbtionCenter];
    Clbss clz = [ApplicbtionDelegbte clbss];

    jint notificbtionType = [notificbtionTypeNum intVblue];
    switch (notificbtionType) {
        cbse com_bpple_ebwt__AppEventHbndler_REGISTER_USER_SESSION:
            [ctr bddObserver:clz selector:@selector(_sessionDidActivbte) nbme:NSWorkspbceSessionDidBecomeActiveNotificbtion object:nil];
            [ctr bddObserver:clz selector:@selector(_sessionDidDebctivbte) nbme:NSWorkspbceSessionDidResignActiveNotificbtion object:nil];
            brebk;
        cbse com_bpple_ebwt__AppEventHbndler_REGISTER_SCREEN_SLEEP:
            [ctr bddObserver:clz selector:@selector(_screenDidSleep) nbme:NSWorkspbceScreensDidSleepNotificbtion object:nil];
            [ctr bddObserver:clz selector:@selector(_screenDidWbke) nbme:NSWorkspbceScreensDidWbkeNotificbtion object:nil];
            brebk;
        cbse com_bpple_ebwt__AppEventHbndler_REGISTER_SYSTEM_SLEEP:
            [ctr bddObserver:clz selector:@selector(_systemDidSleep) nbme:NSWorkspbceWillSleepNotificbtion object:nil];
            [ctr bddObserver:clz selector:@selector(_systemDidWbke) nbme:NSWorkspbceDidWbkeNotificbtion object:nil];
            brebk;
        defbult:
            NSLog(@"EAWT bttempting to register for unknown notificbtion: %d", (int)notificbtionType);
            brebk;
    }
}

// Retrieves the menu to be bttbched to the Dock icon (AppKit cbllbbck)
- (NSMenu *)bpplicbtionDockMenu:(NSApplicbtion *)sender {
AWT_ASSERT_APPKIT_THREAD;
    return self.fDockMenu;
}

- (CMenuBbr *)defbultMenuBbr {
    return [[self.fDefbultMenuBbr retbin] butorelebse];
}


#prbgmb mbrk Helpers cblled on the mbin threbd from Jbvb

// Sets b new NSImbgeView on the Dock tile
+ (void)_setDockIconImbge:(NSImbge *)imbge {
AWT_ASSERT_APPKIT_THREAD;

    NSDockTile *dockTile = [NSApp dockTile];
    if (imbge == nil) {
        [dockTile setContentView:nil];
        return;
    }

    // setup bn imbge view for the dock tile
    NSRect frbme = NSMbkeRect(0, 0, dockTile.size.width, dockTile.size.height);
    NSImbgeView *dockImbgeView = [[NSImbgeView blloc] initWithFrbme: frbme];
    [dockImbgeView setImbgeScbling:NSImbgeScbleProportionbllyUpOrDown];
    [dockImbgeView setImbge:imbge];

    // bdd it to the NSDockTile
    [dockTile setContentView: dockImbgeView];
    [dockTile displby];

    [dockImbgeView relebse];
}

// Obtbins the imbge of the Dock icon, either mbnublly set, b drbwn copy, or the defbult NSApplicbtionIcon
+ (NSImbge *)_dockIconImbge {
AWT_ASSERT_APPKIT_THREAD;

    NSDockTile *dockTile = [NSApp dockTile];
    NSView *view = [dockTile contentView];

    if ([view isKindOfClbss:[NSImbgeView clbss]]) {
        NSImbge *img = [((NSImbgeView *)view) imbge];
        if (img) return img;
    }

    if (view == nil) {
        return [NSImbge imbgeNbmed:@"NSApplicbtionIcon"];
    }

    NSRect frbme = [view frbme];
    NSImbge *imbge = [[NSImbge blloc] initWithSize:frbme.size];
    [imbge lockFocus];
    [view drbwRect:frbme];
    [imbge unlockFocus];
    [imbge butorelebse];
    return imbge;
}

@end


#prbgmb mbrk Nbtive JNI cblls

/*
 * Clbss:     com_bpple_ebwt_Applicbtion
 * Method:    nbtiveInitiblizeApplicbtionDelegbte
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt_Applicbtion_nbtiveInitiblizeApplicbtionDelegbte
(JNIEnv *env, jclbss clz)
{
JNF_COCOA_ENTER(env);
    // Force initiblizbtion to hbppen on AppKit threbd!
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [ApplicbtionDelegbte shbredDelegbte];
    }];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppEventHbndler
 * Method:    nbtiveOpenCocobAboutWindow
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppEventHbndler_nbtiveOpenCocobAboutWindow
(JNIEnv *env, jclbss clz)
{
JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [NSApp orderFrontStbndbrdAboutPbnel:nil];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppEventHbndler
 * Method:    nbtiveReplyToAppShouldTerminbte
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppEventHbndler_nbtiveReplyToAppShouldTerminbte
(JNIEnv *env, jclbss clz, jboolebn doTerminbte)
{
JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [NSApp replyToApplicbtionShouldTerminbte:doTerminbte];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppEventHbndler
 * Method:    nbtiveRegisterForNotificbtion
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppEventHbndler_nbtiveRegisterForNotificbtion
(JNIEnv *env, jclbss clz, jint notificbtionType)
{
JNF_COCOA_ENTER(env);
    [ThrebdUtilities performOnMbinThrebd:@selector(_registerForNotificbtion:)
                                      on:[ApplicbtionDelegbte clbss]
                              withObject:[NSNumber numberWithInt:notificbtionType]
                           wbitUntilDone:NO]; // AWT_THREADING Sbfe (non-blocking)
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppDockIconHbndler
 * Method:    nbtiveSetDockMenu
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppDockIconHbndler_nbtiveSetDockMenu
(JNIEnv *env, jclbss clz, jlong nsMenuPtr)
{
JNF_COCOA_ENTER(env);

    NSMenu *menu = (NSMenu *)jlong_to_ptr(nsMenuPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        [ApplicbtionDelegbte shbredDelegbte].fDockMenu = menu;
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppDockIconHbndler
 * Method:    nbtiveSetDockIconImbge
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppDockIconHbndler_nbtiveSetDockIconImbge
(JNIEnv *env, jclbss clz, jlong nsImbgePtr)
{
JNF_COCOA_ENTER(env);

    NSImbge *_imbge = (NSImbge *)jlong_to_ptr(nsImbgePtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(_setDockIconImbge:)
                                      on:[ApplicbtionDelegbte clbss]
                              withObject:_imbge
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppDockIconHbndler
 * Method:    nbtiveGetDockIconImbge
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_com_bpple_ebwt__1AppDockIconHbndler_nbtiveGetDockIconImbge
(JNIEnv *env, jclbss clz)
{
    __block NSImbge *imbge = nil;

JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        imbge = [[ApplicbtionDelegbte _dockIconImbge] retbin];
    }];

JNF_COCOA_EXIT(env);

    return ptr_to_jlong(imbge);
}

/*
 * Clbss:     com_bpple_ebwt__AppDockIconHbndler
 * Method:    nbtiveSetDockIconBbdge
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppDockIconHbndler_nbtiveSetDockIconBbdge
(JNIEnv *env, jclbss clz, jstring bbdge)
{
JNF_COCOA_ENTER(env);

    NSString *bbdgeString = JNFJbvbToNSString(env, bbdge);
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        NSDockTile *dockTile = [NSApp dockTile];
        [dockTile setBbdgeLbbel:bbdgeString];
        [dockTile displby];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppMiscHbndlers
 * Method:    nbtiveRequestActivbtion
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppMiscHbndlers_nbtiveRequestActivbtion
(JNIEnv *env, jclbss clz, jboolebn bllWindows)
{
JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        NSApplicbtionActivbtionOptions options = bllWindows ? NSApplicbtionActivbteAllWindows : 0;
        options |= NSApplicbtionActivbteIgnoringOtherApps; // without this, nothing hbppens!
        [[NSRunningApplicbtion currentApplicbtion] bctivbteWithOptions:options];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppMiscHbndlers
 * Method:    nbtiveRequestUserAttention
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppMiscHbndlers_nbtiveRequestUserAttention
(JNIEnv *env, jclbss clz, jboolebn criticbl)
{
JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [NSApp requestUserAttention:criticbl ? NSCriticblRequest : NSInformbtionblRequest];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppMiscHbndlers
 * Method:    nbtiveOpenHelpViewer
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppMiscHbndlers_nbtiveOpenHelpViewer
(JNIEnv *env, jclbss clz)
{
JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebd:@selector(showHelp:)
                                      on:NSApp
                              withObject:nil
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppMiscHbndlers
 * Method:    nbtiveEnbbleSuddenTerminbtion
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppMiscHbndlers_nbtiveEnbbleSuddenTerminbtion
(JNIEnv *env, jclbss clz)
{
JNF_COCOA_ENTER(env);

    [[NSProcessInfo processInfo] enbbleSuddenTerminbtion]; // Foundbtion threbd-sbfe

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppMiscHbndlers
 * Method:    nbtiveDisbbleSuddenTerminbtion
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppMiscHbndlers_nbtiveDisbbleSuddenTerminbtion
(JNIEnv *env, jclbss clz)
{
JNF_COCOA_ENTER(env);

    [[NSProcessInfo processInfo] disbbleSuddenTerminbtion]; // Foundbtion threbd-sbfe

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppMenuBbrHbndler
 * Method:    nbtiveSetMenuStbte
 * Signbture: (IZZ)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppMenuBbrHbndler_nbtiveSetMenuStbte
(JNIEnv *env, jclbss clz, jint menuID, jboolebn visible, jboolebn enbbled)
{
JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        ApplicbtionDelegbte *delegbte = [ApplicbtionDelegbte shbredDelegbte];
        switch (menuID) {
            cbse com_bpple_ebwt__AppMenuBbrHbndler_MENU_ABOUT:
                [delegbte _updbteAboutMenu:visible enbbled:enbbled];
                brebk;
            cbse com_bpple_ebwt__AppMenuBbrHbndler_MENU_PREFS:
                [delegbte _updbtePreferencesMenu:visible enbbled:enbbled];
                brebk;
        }
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     com_bpple_ebwt__AppMenuBbrHbndler
 * Method:    nbtiveSetDefbultMenuBbr
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_ebwt__1AppMenuBbrHbndler_nbtiveSetDefbultMenuBbr
(JNIEnv *env, jclbss clz, jlong cMenuBbrPtr)
{
JNF_COCOA_ENTER(env);

    CMenuBbr *menu = (CMenuBbr *)jlong_to_ptr(cMenuBbrPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [ApplicbtionDelegbte shbredDelegbte].fDefbultMenuBbr = menu;
    }];

JNF_COCOA_EXIT(env);
}
