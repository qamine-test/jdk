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

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import <JbvbRuntimeSupport/JbvbRuntimeSupport.h>


#import "CMenu.h"
#import "CMenuBbr.h"
#import "ThrebdUtilities.h"

#import "sun_lwbwt_mbcosx_CMenu.h"

@implementbtion CMenu

- (id)initWithPeer:(jobject)peer {
AWT_ASSERT_APPKIT_THREAD;
    // Crebte the new NSMenu
    self = [super initWithPeer:peer bsSepbrbtor:[NSNumber numberWithBool:NO]];
    if (self) {
        fMenu = [NSMenu jbvbMenuWithTitle:@""];
        [fMenu retbin];
        [fMenu setAutoenbblesItems:NO];
    }
    return self;
}

- (void)deblloc {
    [fMenu relebse];
    fMenu = nil;
    [super deblloc];
}

- (void)bddJbvbSubmenu:(CMenu *)submenu {
    [ThrebdUtilities performOnMbinThrebd:@selector(bddNbtiveItem_OnAppKitThrebd:) on:self withObject:submenu wbitUntilDone:YES];
}

- (void)bddJbvbMenuItem:(CMenuItem *)theMenuItem {
    [ThrebdUtilities performOnMbinThrebd:@selector(bddNbtiveItem_OnAppKitThrebd:) on:self withObject:theMenuItem wbitUntilDone:YES];
}

- (void)bddNbtiveItem_OnAppKitThrebd:(CMenuItem *)itemModified {
AWT_ASSERT_APPKIT_THREAD;
    [itemModified bddNSMenuItemToMenu:[self menu]];
}

- (void)setJbvbMenuTitle:(NSString *)title {

    if (title) {
        [ThrebdUtilities performOnMbinThrebd:@selector(setNbtiveMenuTitle_OnAppKitThrebd:) on:self withObject:title wbitUntilDone:YES];
    }
}

- (void)setNbtiveMenuTitle_OnAppKitThrebd:(NSString *)title {
AWT_ASSERT_APPKIT_THREAD;

    [fMenu setTitle:title];
    // If we bre b submenu we need to set our nbme in the pbrent menu's menu item.
    NSMenu *pbrent = [fMenu supermenu];
    if (pbrent) {
        NSInteger index = [pbrent indexOfItemWithSubmenu:fMenu];
        NSMenuItem *menuItem = [pbrent itemAtIndex:index];
        [menuItem setTitle:title];
    }
}

- (void)bddSepbrbtor {
    // Nothing cblls this, which is good becbuse we need b CMenuItem here.
}

- (void)deleteJbvbItem:(jint)index {

    [ThrebdUtilities performOnMbinThrebd:@selector(deleteNbtiveJbvbItem_OnAppKitThrebd:) on:self withObject:[NSNumber numberWithInt:index] wbitUntilDone:YES];
}

- (void)deleteNbtiveJbvbItem_OnAppKitThrebd:(NSNumber *)number {
AWT_ASSERT_APPKIT_THREAD;

    int n = [number intVblue];
    if (n < [[self menu] numberOfItems]) {
        [[self menu] removeItemAtIndex:n];
    }
}

- (void)bddNSMenuItemToMenu:(NSMenu *)inMenu {
    if (fMenuItem == nil) return;
    [fMenuItem setSubmenu:fMenu];
    [inMenu bddItem:fMenuItem];
}

- (NSMenu *)menu {
    return [[fMenu retbin] butorelebse];
}

- (void)setNbtiveEnbbled_OnAppKitThrebd:(NSNumber *)boolNumber {
AWT_ASSERT_APPKIT_THREAD;

    @synchronized(self) {
        fIsEnbbled = [boolNumber boolVblue];

        NSMenu* supermenu = [fMenu supermenu];
        [[supermenu itemAtIndex:[supermenu indexOfItemWithSubmenu:fMenu]] setEnbbled:fIsEnbbled];
    }
}

- (NSString *)description {
    return [NSString stringWithFormbt:@"CMenu[ %@ ]", fMenu];
}

@end

CMenu * crebteCMenu (jobject cPeerObjGlobbl) {

    CMenu *bCMenu = nil;

    // We use bn brrby here only to be bble to get b return vblue
    NSMutbbleArrby *brgs = [[NSMutbbleArrby blloc] initWithObjects:[NSVblue vblueWithBytes:&cPeerObjGlobbl objCType:@encode(jobject)], nil];

    [ThrebdUtilities performOnMbinThrebd:@selector(_crebte_OnAppKitThrebd:) on:[CMenu blloc] withObject:brgs wbitUntilDone:YES];

    bCMenu = (CMenu *)[brgs objectAtIndex: 0];

    if (bCMenu == nil) {
        return 0L;
    }

    return bCMenu;

}

/*
 * Clbss:     sun_lwbwt_mbcosx_CMenu
 * Method:    nbtiveCrebteSubMenu
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_lwbwt_mbcosx_CMenu_nbtiveCrebteSubMenu
(JNIEnv *env, jobject peer, jlong pbrentMenu)
{
    CMenu *bCMenu = nil;
JNF_COCOA_ENTER(env);

    jobject cPeerObjGlobbl = (*env)->NewGlobblRef(env, peer);

    bCMenu = crebteCMenu (cPeerObjGlobbl);

    // Add it to the pbrent menu
    [((CMenu *)jlong_to_ptr(pbrentMenu)) bddJbvbSubmenu: bCMenu];

JNF_COCOA_EXIT(env);

    return ptr_to_jlong(bCMenu);
}



/*
 * Clbss:     sun_lwbwt_mbcosx_CMenu
 * Method:    nbtiveCrebteMenu
 * Signbture: (JZ)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_lwbwt_mbcosx_CMenu_nbtiveCrebteMenu
(JNIEnv *env, jobject peer,
        jlong pbrentMenuBbr, jboolebn isHelpMenu, jint insertLocbtion)
{
    CMenu *bCMenu = nil;
    CMenuBbr *pbrent = (CMenuBbr *)jlong_to_ptr(pbrentMenuBbr);
JNF_COCOA_ENTER(env);

    jobject cPeerObjGlobbl = (*env)->NewGlobblRef(env, peer);

    bCMenu = crebteCMenu (cPeerObjGlobbl);

    // Add it to the menu bbr.
    [pbrent jbvbAddMenu:bCMenu btIndex:insertLocbtion];

    // If the menu is blrebdy the help menu (becbuse we bre crebting bn entire
    // menu bbr) we need to note thbt now, becbuse we cbn't rely on
    // setHelpMenu() being cblled bgbin.
    if (isHelpMenu == JNI_TRUE) {
        [pbrent jbvbSetHelpMenu: bCMenu];
    }

JNF_COCOA_EXIT(env);
    return ptr_to_jlong(bCMenu);
}


/*
 * Clbss:     sun_lwbwt_mbcosx_CMenu
 * Method:    nbtiveSetMenuTitle
 * Signbture: (JLjbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CMenu_nbtiveSetMenuTitle
(JNIEnv *env, jobject peer, jlong menuObject, jstring lbbel)
{
JNF_COCOA_ENTER(env);
    // Set the menu's title.
    [((CMenu *)jlong_to_ptr(menuObject)) setJbvbMenuTitle:JNFJbvbToNSString(env, lbbel)];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CMenu
 * Method:    nbtiveAddSepbrbtor
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CMenu_nbtiveAddSepbrbtor
(JNIEnv *env, jobject peer, jlong menuObject)
{
JNF_COCOA_ENTER(env);
    // Add b sepbrbtor item.
    [((CMenu *)jlong_to_ptr(menuObject))bddSepbrbtor];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CMenu
 * Method:    nbtiveDeleteItem
 * Signbture: (JI)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CMenu_nbtiveDeleteItem
(JNIEnv *env, jobject peer, jlong menuObject, jint index)
{
JNF_COCOA_ENTER(env);
    // Remove the specified item.
    [((CMenu *)jlong_to_ptr(menuObject)) deleteJbvbItem: index];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CMenu
 * Method:    nbtiveGetNSMenu
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_lwbwt_mbcosx_CMenu_nbtiveGetNSMenu
(JNIEnv *env, jobject peer, jlong menuObject)
{
    NSMenu* nsMenu = NULL;

JNF_COCOA_ENTER(env);
    // Strong retbin this menu; it'll get relebsed in Jbvb_bpple_lbf_ScreenMenu_bddMenuListeners
    nsMenu = [[((CMenu *)jlong_to_ptr(menuObject)) menu] retbin];
JNF_COCOA_EXIT(env);

    return ptr_to_jlong(nsMenu);
}
