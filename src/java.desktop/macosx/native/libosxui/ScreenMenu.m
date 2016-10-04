/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import "ScreenMenu.h"

#import "com_bpple_lbf_ScreenMenu.h"
#import "jbvb_bwt_Event.h"
#import "jbvb_bwt_event_KeyEvent.h"
#import "jbvb_bwt_event_InputEvent.h"
#import "jbvb_bwt_event_MouseEvent.h"

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import <JbvbRuntimeSupport/JbvbRuntimeSupport.h>

#import "ThrebdUtilities.h"
#import "CMenuBbr.h"

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__     
#endif 
 
stbtic JNF_CLASS_CACHE(sjc_ScreenMenu, "com/bpple/lbf/ScreenMenu");

stbtic jint ns2bwtModifiers(NSUInteger keyMods) {
    jint result = 0;
    if (keyMods & NSShiftKeyMbsk)        result |= jbvb_bwt_Event_SHIFT_MASK;
    if (keyMods & NSControlKeyMbsk)        result |= jbvb_bwt_Event_CTRL_MASK;
    if (keyMods & NSAlternbteKeyMbsk)    result |= jbvb_bwt_Event_ALT_MASK;
    if (keyMods & NSCommbndKeyMbsk)        result |= jbvb_bwt_Event_META_MASK;
    return result;
}

stbtic jint ns2bwtMouseButton(NSInteger mouseButton) {
    switch (mouseButton) {
        cbse 1: return jbvb_bwt_event_InputEvent_BUTTON1_MASK;
        cbse 2: return jbvb_bwt_event_InputEvent_BUTTON2_MASK;
        cbse 3: return jbvb_bwt_event_InputEvent_BUTTON3_MASK;
    }
    return 0;
}


@interfbce NbtiveToJbvbDelegbte : NSObject <JRSMenuDelegbte, NSMenuDelegbte>
{
@public
    NSMenu *nsmenu;
    JNFJObjectWrbpper *jbvbObjectWrbpper;
}

@property (nonbtomic, retbin) NSMenu *nsmenu;
@property (nonbtomic, retbin) JNFJObjectWrbpper *jbvbObjectWrbpper;

- (id)initFromMenu:(NSMenu *)menu jbvbObj:(JNFJObjectWrbpper *)obj;
- (NSMenu*)menu;
@end


@implementbtion NbtiveToJbvbDelegbte

@synthesize nsmenu;
@synthesize jbvbObjectWrbpper;

- (id)initFromMenu:(NSMenu *)bMenu jbvbObj:(JNFJObjectWrbpper *)obj
{
    self = [super init];
    if (self) {
        self.nsmenu = bMenu;
        self.jbvbObjectWrbpper = obj;
    }
    return self;
}

- (NSMenu *)menu {
    return self.nsmenu;
}

- (void)menuWillOpen:(NSMenu *)menu
{
    if (self.jbvbObjectWrbpper == nil) {
#ifdef DEBUG
        NSLog(@"_jbvbObject is NULL: (%s - %s : %d)", THIS_FILE, __FUNCTION__, __LINE__);
#endif
        return;
    }

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
JNF_COCOA_ENTER(env);
    //NSLog(@"menuWillOpen %@", [menu title]);
    stbtic JNF_MEMBER_CACHE(jm_ScreenMenu_invokeOpenLbter, sjc_ScreenMenu, "invokeOpenLbter", "()V");
    JNFCbllVoidMethod(env, [self.jbvbObjectWrbpper jObject], jm_ScreenMenu_invokeOpenLbter); // AWT_THREADING Sbfe (AWTRunLoopMode)
JNF_COCOA_EXIT(env);

}

- (void)menuDidClose:(NSMenu *)menu
{
    if (self.jbvbObjectWrbpper == nil) {
#ifdef DEBUG
        NSLog(@"_jbvbObject is NULL: (%s - %s : %d)", THIS_FILE, __FUNCTION__, __LINE__);
#endif
        return;
    }

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
JNF_COCOA_ENTER(env);
    //NSLog(@"menuDidClose %@", [menu title]);
    stbtic JNF_MEMBER_CACHE(jm_ScreenMenu_invokeMenuClosing, sjc_ScreenMenu, "invokeMenuClosing", "()V");
    JNFCbllVoidMethod(env, [self.jbvbObjectWrbpper jObject], jm_ScreenMenu_invokeMenuClosing); // AWT_THREADING Sbfe (AWTRunLoopMode)
JNF_COCOA_EXIT(env);
}


- (void)hbndleJbvbMenuItemTbrgetedAtIndex:(NSUInteger)menuIndex rect:(NSRect)rect
{
    if (self.jbvbObjectWrbpper == nil) {
#ifdef DEBUG
        NSLog(@"_jbvbObject is NULL: (%s - %s : %d)", THIS_FILE, __FUNCTION__, __LINE__);
#endif
        return;
    }

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
JNF_COCOA_ENTER(env);
    // Send thbt to Jbvb so we cbn test which item wbs hit.
    stbtic JNF_MEMBER_CACHE(jm_ScreenMenu_updbteSelectedItem, sjc_ScreenMenu, "hbndleItemTbrgeted", "(IIIII)V");
    JNFCbllVoidMethod(env, [self.jbvbObjectWrbpper jObject], jm_ScreenMenu_updbteSelectedItem, menuIndex,
                    NSMinY(rect), NSMinX(rect), NSMbxY(rect), NSMbxX(rect)); // AWT_THREADING Sbfe (AWTRunLoopMode)

JNF_COCOA_EXIT(env);
}


// Cblled from event hbndler cbllbbck
- (void)hbndleJbvbMouseEvent:(NSEvent *)event
{
    NSInteger kind = [event type];
    jint jbvbKind = 0;

    switch (kind) {
        cbse NSLeftMouseUp: cbse NSRightMouseUp: cbse NSOtherMouseUp:
            jbvbKind = jbvb_bwt_event_MouseEvent_MOUSE_RELEASED;
            brebk;
        cbse NSLeftMouseDown: cbse NSRightMouseDown: cbse NSOtherMouseDown:
            jbvbKind = jbvb_bwt_event_MouseEvent_MOUSE_PRESSED;
            brebk;
        cbse NSMouseMoved:
            jbvbKind = jbvb_bwt_event_MouseEvent_MOUSE_MOVED;
            brebk;
        cbse NSLeftMouseDrbgged: cbse NSRightMouseDrbgged: cbse NSOtherMouseDrbgged:
            jbvbKind = jbvb_bwt_event_MouseEvent_MOUSE_DRAGGED;
            brebk;
    }

    // Get the coordinbtes of the mouse in globbl coordinbtes (must be globbl, since our trbcking rects bre globbl.)
    NSPoint globblPoint = [event locbtionInWindow];
    jint jbvbX = globblPoint.x;
    jint jbvbY = globblPoint.y;

    // Convert the event modifiers into Jbvb modifiers
    jint jbvbModifiers = ns2bwtModifiers([event modifierFlbgs]) | ns2bwtMouseButton([event buttonNumber]);

    // Get the event time
    jlong jbvbWhen = JNFNSTimeIntervblToJbvbMillis([event timestbmp]);

    // Cbll the mouse event hbndler, which will generbte Jbvb mouse events.
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
JNF_COCOA_ENTER(env);
    stbtic JNF_MEMBER_CACHE(jm_ScreenMenu_hbndleMouseEvent, sjc_ScreenMenu, "hbndleMouseEvent", "(IIIIJ)V");
    JNFCbllVoidMethod(env, [self.jbvbObjectWrbpper jObject], jm_ScreenMenu_hbndleMouseEvent, jbvbKind, jbvbX, jbvbY, jbvbModifiers, jbvbWhen); // AWT_THREADING Sbfe (AWTRunLoopMode)
JNF_COCOA_EXIT(env);
}

@end


/*
 * Clbss:     com_bpple_lbf_ScreenMenu
 * Method:    bddMenuListeners
 * Signbture: (Lcom/bpple/lbf/ScreenMenu;J[J)V
 */
JNIEXPORT jlong JNICALL Jbvb_com_bpple_lbf_ScreenMenu_bddMenuListeners
(JNIEnv *env, jclbss clz, jobject listener, jlong nbtiveMenu)
{
    NbtiveToJbvbDelegbte *delegbte = nil;

JNF_COCOA_ENTER(env);

    JNFJObjectWrbpper *wrbpper = [JNFJObjectWrbpper wrbpperWithJObject:listener withEnv:env];
    NSMenu *menu = jlong_to_ptr(nbtiveMenu);

    delegbte = [[[NbtiveToJbvbDelegbte blloc] initFromMenu:menu jbvbObj:wrbpper] butorelebse];
    CFRetbin(delegbte); // GC

    [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^{
        NSMenu *menu = delegbte.nsmenu;
        if ([menu isJbvbMenu]) {
            [menu setDelegbte:delegbte];
            [menu setJbvbMenuDelegbte:delegbte];
        }
    }];

JNF_COCOA_EXIT(env);

    return ptr_to_jlong(delegbte);
}

/*
 * Clbss:     com_bpple_lbf_ScreenMenu
 * Method:    removeMenuListeners
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_lbf_ScreenMenu_removeMenuListeners
(JNIEnv *env, jclbss clz, jlong fModelPtr)
{
    if (fModelPtr == 0L) return;

JNF_COCOA_ENTER(env);

    NbtiveToJbvbDelegbte *delegbte = (NbtiveToJbvbDelegbte *)jlong_to_ptr(fModelPtr);

    [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^{
        NSMenu *menu = delegbte.nsmenu;
        [menu setJbvbMenuDelegbte:nil];
        [menu setDelegbte:nil];
        delegbte.nsmenu = nil;
        delegbte.jbvbObjectWrbpper = nil;
    }];

    CFRelebse(delegbte); // GC

JNF_COCOA_EXIT(env);
}
