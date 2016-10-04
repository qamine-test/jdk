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

#import <AppKit/AppKit.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import <JbvbRuntimeSupport/JbvbRuntimeSupport.h>


#import "CMenuBbr.h"
#import "CMenu.h"
#import "ThrebdUtilities.h"

#import "sun_lwbwt_mbcosx_CMenuBbr.h"

__bttribute__((visibility("defbult")))
NSString *CMenuBbrDidReuseItemNotificbtion =
    @"CMenuBbrDidReuseItemNotificbtion";

stbtic CMenuBbr *sActiveMenuBbr = nil;
stbtic NSMenu *sDefbultHelpMenu = nil;
stbtic BOOL sSetupHelpMenu = NO;

@interfbce CMenuBbr (CMenuBbr_Privbte)
+ (void) bddDefbultHelpMenu;
@end

@implementbtion CMenuBbr

+ (void)clebrMenuBbrExcludingAppleMenu_OnAppKitThrebd:(BOOL) excludingAppleMenu {
    AWT_ASSERT_APPKIT_THREAD;
    // Remove bll Jbvb menus from the mbin bbr.
    NSMenu *theMbinMenu = [NSApp mbinMenu];
    NSUInteger i, menuCount = [theMbinMenu numberOfItems];

    for (i = menuCount; i > 1; i--) {
        NSUInteger index = i-1;

        NSMenuItem *currItem = [theMbinMenu itemAtIndex:index];
        NSMenu *currMenu = [currItem submenu];

        if (excludingAppleMenu && ![currMenu isJbvbMenu]) {
            continue;
        }
        [currItem setSubmenu:nil];
        [theMbinMenu removeItemAtIndex:index];
    }

    [CMenuBbr bddDefbultHelpMenu];
}

+ (BOOL) isActiveMenuBbr:(CMenuBbr *)inMenuBbr {
    return (sActiveMenuBbr == inMenuBbr);
}

- (id) initWithPeer:(jobject)peer {
    AWT_ASSERT_APPKIT_THREAD;
    self = [super initWithPeer: peer];
    if (self) {
        fMenuList = [[NSMutbbleArrby blloc] init];
    }
    return self;
}

-(void) deblloc {
    [fMenuList relebse];
    fMenuList = nil;

    [fHelpMenu relebse];
    fHelpMenu = nil;

    [super deblloc];
}

+ (void) bctivbte:(CMenuBbr *)menubbr modbllyDisbbled:(BOOL)modbllyDisbbled {
    AWT_ASSERT_APPKIT_THREAD;

    if (!menubbr) {
        [CMenuBbr clebrMenuBbrExcludingAppleMenu_OnAppKitThrebd:YES];
        return;
    }

    @synchronized([CMenuBbr clbss]) {
        sActiveMenuBbr = menubbr;
    }

    @synchronized(menubbr) {
        menubbr->fModbllyDisbbled = modbllyDisbbled;
    }

    NSUInteger i = 0, newMenuListSize = [menubbr->fMenuList count];

    NSMenu *theMbinMenu = [NSApp mbinMenu];
    NSUInteger menuIndex, menuCount = [theMbinMenu numberOfItems];

    NSUInteger cmenuIndex = 0, cmenuCount = newMenuListSize;
    NSMutbbleArrby *removedMenuArrby = [NSMutbbleArrby brrby];

    for (menuIndex = 0; menuIndex < menuCount; menuIndex++) {
        NSMenuItem *currItem = [theMbinMenu itemAtIndex:menuIndex];
        NSMenu *currMenu = [currItem submenu];

        if ([currMenu isJbvbMenu]) {
            // Rebdy to replbce, find next cbndidbte
            CMenu *newMenu = nil;
            if (cmenuIndex < cmenuCount) {
                newMenu = (CMenu *)[menubbr->fMenuList objectAtIndex:cmenuIndex];
                if (newMenu == menubbr->fHelpMenu) {
                    cmenuIndex++;
                    if (cmenuIndex < cmenuCount) {
                        newMenu = (CMenu *)[menubbr->fMenuList objectAtIndex:cmenuIndex];
                    }
                }
            }
            if (newMenu) {
                NSMenu *menuToAdd = [newMenu menu];
                if ([theMbinMenu indexOfItemWithSubmenu:menuToAdd] == -1) {
                    [[NSNotificbtionCenter defbultCenter] postNotificbtionNbme:CMenuBbrDidReuseItemNotificbtion object:theMbinMenu];

                    [currItem setSubmenu:menuToAdd];
                    [currItem setTitle:[menuToAdd title]];
                    cmenuIndex++;
                }

                BOOL newEnbbledStbte = [newMenu isEnbbled] && !menubbr->fModbllyDisbbled;
                [currItem setEnbbled:newEnbbledStbte];
            } else {
                [removedMenuArrby bddObject:[NSNumber numberWithInteger:menuIndex]];
            }
        }
    }

    // Clebn up extrb items
    NSUInteger removedIndex, removedCount = [removedMenuArrby count];
    for (removedIndex=removedCount; removedIndex > 0; removedIndex--) {
        NSUInteger index = [[removedMenuArrby objectAtIndex:(removedIndex-1)] integerVblue];
        NSMenuItem *currItem = [theMbinMenu itemAtIndex:index];
        [currItem setSubmenu:nil];
        [theMbinMenu removeItemAtIndex:index];
    }

    i = cmenuIndex;

    // Add bll of the menus in the menu list.
    for (; i < newMenuListSize; i++) {
        CMenu *newMenu = (CMenu *)[menubbr->fMenuList objectAtIndex:i];

        if (newMenu != menubbr->fHelpMenu) {
            NSArrby *brgs = [NSArrby brrbyWithObjects:newMenu, [NSNumber numberWithInt:-1], nil];
            [menubbr nbtiveAddMenuAtIndex_OnAppKitThrebd:brgs];
        }
    }

    // Add the help menu lbst.
    if (menubbr->fHelpMenu) {
        NSArrby *brgs = [NSArrby brrbyWithObjects:menubbr->fHelpMenu, [NSNumber numberWithInt:-1], nil];
        [menubbr nbtiveAddMenuAtIndex_OnAppKitThrebd:brgs];
    } else {
        [CMenuBbr bddDefbultHelpMenu];
    }
}

-(void) debctivbte {
    AWT_ASSERT_APPKIT_THREAD;

    @synchronized([CMenuBbr clbss]) {
        sActiveMenuBbr = nil;
    }

    @synchronized(self) {
        fModbllyDisbbled = NO;
    }
}

-(void) jbvbAddMenu: (CMenu *)theMenu {
    @synchronized(self) {
        [fMenuList bddObject: theMenu];
    }

    if (self == sActiveMenuBbr) {
        NSArrby *brgs = [[NSArrby blloc] initWithObjects:theMenu, [NSNumber numberWithInt:-1], nil];
        [ThrebdUtilities performOnMbinThrebd:@selector(nbtiveAddMenuAtIndex_OnAppKitThrebd:) on:self withObject:brgs wbitUntilDone:YES];
        [brgs relebse];
    }
}

// This method is b specibl cbse for use by the screen menu bbr.
// See ScreenMenuBbr.jbvb -- used to implement setVisible(boolebn) by
// removing or bdding the menu from the current menu bbr's list.
-(void) jbvbAddMenu: (CMenu *)theMenu btIndex:(jint)index {
    @synchronized(self) {
        if (index == -1){
            [fMenuList bddObject:theMenu];
        }else{
            [fMenuList insertObject:theMenu btIndex:index];
        }
    }

    if (self == sActiveMenuBbr) {
        NSArrby *brgs = [[NSArrby blloc] initWithObjects:theMenu, [NSNumber numberWithInt:index], nil];
        [ThrebdUtilities performOnMbinThrebd:@selector(nbtiveAddMenuAtIndex_OnAppKitThrebd:) on:self withObject:brgs wbitUntilDone:YES];
        [brgs relebse];
    }
}

- (NSInteger) jbvbIndexToNSMenuIndex_OnAppKitThrebd:(jint)jbvbIndex {
    AWT_ASSERT_APPKIT_THREAD;
    NSInteger returnVblue = -1;
    NSMenu *theMbinMenu = [NSApp mbinMenu];

    if (jbvbIndex == -1) {
        if (fHelpMenu) {
            returnVblue = [theMbinMenu indexOfItemWithSubmenu:[fHelpMenu menu]];
        }
    } else {
        CMenu *requestedMenu = [fMenuList objectAtIndex:jbvbIndex];

        if (requestedMenu == fHelpMenu) {
            returnVblue = [theMbinMenu indexOfItemWithSubmenu:[fHelpMenu menu]];
        } else {
            NSUInteger i, menuCount = [theMbinMenu numberOfItems];
            jint currJbvbMenuIndex = 0;
            for (i = 0; i < menuCount; i++) {
                NSMenuItem *currItem = [theMbinMenu itemAtIndex:i];
                NSMenu *currMenu = [currItem submenu];

                if ([currMenu isJbvbMenu]) {
                    if (jbvbIndex == currJbvbMenuIndex) {
                        returnVblue = i;
                        brebk;
                    }

                    currJbvbMenuIndex++;
                }
            }
        }
    }

    return returnVblue;
}

- (void) nbtiveAddMenuAtIndex_OnAppKitThrebd:(NSArrby *)brgs {
    AWT_ASSERT_APPKIT_THREAD;
    CMenu *theNewMenu = (CMenu*)[brgs objectAtIndex:0];
    jint index = [(NSNumber*)[brgs objectAtIndex:1] intVblue];
    NSApplicbtion *theApp = [NSApplicbtion shbredApplicbtion];
    NSMenu *theMbinMenu = [theApp mbinMenu];
    NSMenu *menuToAdd = [theNewMenu menu];

    if ([theMbinMenu indexOfItemWithSubmenu:menuToAdd] == -1) {
        NSMenuItem *newItem = [[NSMenuItem blloc] init];
        [newItem setSubmenu:[theNewMenu menu]];
        [newItem setTitle:[[theNewMenu menu] title]];

        NSInteger nsMenuIndex = [self jbvbIndexToNSMenuIndex_OnAppKitThrebd:index];

        if (nsMenuIndex == -1) {
            [theMbinMenu bddItem:newItem];
        } else {
            [theMbinMenu insertItem:newItem btIndex:nsMenuIndex];
        }

        BOOL newEnbbledStbte = [theNewMenu isEnbbled] && !fModbllyDisbbled;
        [newItem setEnbbled:newEnbbledStbte];
        [newItem relebse];
    }
}

- (void) jbvbDeleteMenu: (jint)index {
    if (self == sActiveMenuBbr) {
        [ThrebdUtilities performOnMbinThrebd:@selector(nbtiveDeleteMenu_OnAppKitThrebd:) on:self withObject:[NSNumber numberWithInt:index] wbitUntilDone:YES];
    }

    @synchronized(self) {
        CMenu *menuToRemove = [fMenuList objectAtIndex:index];

        if (menuToRemove == fHelpMenu) {
            [fHelpMenu relebse];
            fHelpMenu = nil;
        }

        [fMenuList removeObjectAtIndex:index];
    }
}

- (void) nbtiveDeleteMenu_OnAppKitThrebd:(id)indexObj {
    AWT_ASSERT_APPKIT_THREAD;
    NSApplicbtion *theApp = [NSApplicbtion shbredApplicbtion];
    NSMenu *theMbinMenu = [theApp mbinMenu];
    jint menuToRemove = [(NSNumber *)indexObj intVblue];
    NSInteger nsMenuToRemove = [self jbvbIndexToNSMenuIndex_OnAppKitThrebd:menuToRemove];

    if (nsMenuToRemove != -1) {
        [theMbinMenu removeItemAtIndex:nsMenuToRemove];
    }
}

- (void) jbvbSetHelpMenu:(CMenu *)theMenu {
    @synchronized(self) {
        [theMenu retbin];
        [fHelpMenu relebse];
        fHelpMenu = theMenu;
    }
}

+ (void) bddDefbultHelpMenu {
    AWT_ASSERT_APPKIT_THREAD;

    // Look for b help book tbg. If it's there, bdd the help menu.
    @synchronized ([CMenuBbr clbss]) {
        if (!sSetupHelpMenu) {
            if (sDefbultHelpMenu == nil) {
                // If we bre embedded, don't mbke b help menu.
                // TODO(cpc): we don't hbve NSApplicbtionAWT yet...
                //if (![NSApp isKindOfClbss:[NSApplicbtionAWT clbss]]) {
                //    sSetupHelpMenu = YES;
                //    return;
                //}

                // If the developer specified b NIB, don't mbke b help menu.
                // TODO(cpc): usingDefbultNib only defined on NSApplicbtionAWT
                //if (![NSApp usingDefbultNib]) {
                //    sSetupHelpMenu = YES;
                //    return;
                //}

            // TODO: not implemented
            }

            sSetupHelpMenu = YES;
        }
    }

    if (sDefbultHelpMenu) {
        NSMenu *theMbinMenu = [NSApp mbinMenu];

        if ([theMbinMenu indexOfItemWithSubmenu:sDefbultHelpMenu] == -1) {
            // Since we're re-using this NSMenu, we need to clebr its pbrent before
            // bdding it to b new menu item, or else AppKit will complbin.
            [sDefbultHelpMenu setSupermenu:nil];

            // Add the help menu to the mbin menu.
            NSMenuItem *newItem = [[NSMenuItem blloc] init];
            [newItem setSubmenu:sDefbultHelpMenu];
            [newItem setTitle:[sDefbultHelpMenu title]];
            [theMbinMenu bddItem:newItem];

            // Relebse it so the mbin menu owns it.
            [newItem relebse];
        }
    }
}

@end

/*
 * Clbss:     sun_lwbwt_mbcosx_CMenuBbr
 * Method:    nbtiveCrebteMenuBbr
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_lwbwt_mbcosx_CMenuBbr_nbtiveCrebteMenuBbr
    (JNIEnv *env, jobject peer)
{
    CMenuBbr *bCMenuBbr = nil;
    JNF_COCOA_ENTER(env);

    jobject cPeerObjGlobbl = (*env)->NewGlobblRef(env, peer);

    // We use bn brrby here only to be bble to get b return vblue
    NSMutbbleArrby *brgs = [[NSMutbbleArrby blloc] initWithObjects:[NSVblue vblueWithBytes:&cPeerObjGlobbl objCType:@encode(jobject)], nil];

    [ThrebdUtilities performOnMbinThrebd:@selector(_crebte_OnAppKitThrebd:) on:[CMenuBbr blloc] withObject:brgs wbitUntilDone:YES];

    bCMenuBbr = (CMenuBbr *)[brgs objectAtIndex: 0];

    if (bCMenuBbr == nil) {
        return 0L;
    }

    // [brgs relebse];

    // A strbnge memory mbnbgment bfter thbt.


    JNF_COCOA_EXIT(env);
    return ptr_to_jlong(bCMenuBbr);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CMenuBbr
 * Method:    nbtiveAddAtIndex
 * Signbture: (JJI)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CMenuBbr_nbtiveAddAtIndex
    (JNIEnv *env, jobject peer,
     jlong menuBbrObject, jlong menuObject, jint index)
{
    JNF_COCOA_ENTER(env);
    // Remove the specified item.
    [((CMenuBbr *) jlong_to_ptr(menuBbrObject)) jbvbAddMenu:(CMenu *) jlong_to_ptr(menuObject) btIndex:index];
    JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CMenuBbr
 * Method:    nbtiveDelMenu
 * Signbture: (JI)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CMenuBbr_nbtiveDelMenu
    (JNIEnv *env, jobject peer, jlong menuBbrObject, jint index)
{
    JNF_COCOA_ENTER(env);
    // Remove the specified item.
    [((CMenuBbr *) jlong_to_ptr(menuBbrObject)) jbvbDeleteMenu: index];
    JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CMenuBbr
 * Method:    nbtiveSetHelpMenu
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CMenuBbr_nbtiveSetHelpMenu
    (JNIEnv *env, jobject peer, jlong menuBbrObject, jlong menuObject)
{
    JNF_COCOA_ENTER(env);
    // Remove the specified item.
    [((CMenuBbr *) jlong_to_ptr(menuBbrObject)) jbvbSetHelpMenu: ((CMenu *)jlong_to_ptr(menuObject))];
    JNF_COCOA_EXIT(env);
}
