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

#import <AppKit/AppKit.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>


//#define JAVA_AX_DEBUG 1
//#define JAVA_AX_NO_IGNORES 1
//#define JAVA_AX_DEBUG_PARMS 1


@interfbce JbvbComponentAccessibility : NSObject {
    NSView *fView;
    NSObject *fPbrent;

    NSString *fNSRole;
    NSString *fJbvbRole;

    jint fIndex;
    jobject fAccessible;
    jobject fComponent;

    NSMutbbleDictionbry *fActions;
    NSObject *fActionsLOCK;
}

- (id)initWithPbrent:(NSObject*)pbrent withEnv:(JNIEnv *)env withAccessible:(jobject)bccessible withIndex:(jint)index withView:(NSView *)view withJbvbRole:(NSString *)jbvbRole;
- (void)unregisterFromCocobAXSystem;
- (void)postVblueChbnged;
- (void)postSelectionChbnged;
- (BOOL)isEqubl:(id)bnObject;
- (BOOL)isAccessibleWithEnv:(JNIEnv *)env forAccessible:(jobject)bccessible;

+ (void)postFocusChbnged:(id)messbge;

+ (NSArrby*)childrenOfPbrent:(JbvbComponentAccessibility*)pbrent withEnv:(JNIEnv *)env withChildrenCode:(NSInteger)whichChildren bllowIgnored:(BOOL)bllowIgnored;
+ (JbvbComponentAccessibility *) crebteWithPbrent:(JbvbComponentAccessibility *)pbrent bccessible:(jobject)jbccessible role:(NSString *)jbvbRole index:(jint)index withEnv:(JNIEnv *)env withView:(NSView *)view;
+ (JbvbComponentAccessibility *) crebteWithAccessible:(jobject)jbccessible role:(NSString *)role index:(jint)index withEnv:(JNIEnv *)env withView:(NSView *)view;
+ (JbvbComponentAccessibility *) crebteWithAccessible:(jobject)jbccessible withEnv:(JNIEnv *)env withView:(NSView *)view;

- (NSDictionbry*)getActions:(JNIEnv *)env;
- (void)getActionsWithEnv:(JNIEnv *)env;

- (jobject)bxContextWithEnv:(JNIEnv *)env;
- (NSView*)view;
- (NSWindow*)window;
- (id)pbrent;
- (NSString *)jbvbRole;
- (BOOL)isMenu;
- (BOOL)isSelected:(JNIEnv *)env;
- (BOOL)isVisible:(JNIEnv *)env;

// bttribute nbmes
- (NSArrby *)initiblizeAttributeNbmesWithEnv:(JNIEnv *)env;
- (NSArrby *)bccessibilityAttributeNbmes;

// bttributes
- (id)bccessibilityAttributeVblue:(NSString *)bttribute;
- (BOOL)bccessibilityIsAttributeSettbble:(NSString *)bttribute;
- (void)bccessibilitySetVblue:(id)vblue forAttribute:(NSString *)bttribute;

- (NSArrby *)bccessibilityChildrenAttribute;
- (BOOL)bccessibilityIsChildrenAttributeSettbble;
- (NSUInteger)bccessibilityIndexOfChild:(id)child;
- (NSNumber *)bccessibilityEnbbledAttribute;
- (BOOL)bccessibilityIsEnbbledAttributeSettbble;
- (NSNumber *)bccessibilityFocusedAttribute;
- (BOOL)bccessibilityIsFocusedAttributeSettbble;
- (void)bccessibilitySetFocusedAttribute:(id)vblue;
- (NSString *)bccessibilityHelpAttribute;
- (BOOL)bccessibilityIsHelpAttributeSettbble;
- (id)bccessibilityMbxVblueAttribute;
- (BOOL)bccessibilityIsMbxVblueAttributeSettbble;
- (id)bccessibilityMinVblueAttribute;
- (BOOL)bccessibilityIsMinVblueAttributeSettbble;
- (id)bccessibilityOrientbtionAttribute;
- (BOOL)bccessibilityIsOrientbtionAttributeSettbble;
- (id)bccessibilityPbrentAttribute;
- (BOOL)bccessibilityIsPbrentAttributeSettbble;
- (NSVblue *)bccessibilityPositionAttribute;
- (BOOL)bccessibilityIsPositionAttributeSettbble;
- (NSString *)bccessibilityRoleAttribute;
- (BOOL)bccessibilityIsRoleAttributeSettbble;
- (NSString *)bccessibilityRoleDescriptionAttribute;
- (BOOL)bccessibilityIsRoleDescriptionAttributeSettbble;
- (NSArrby *)bccessibilitySelectedChildrenAttribute;
- (BOOL)bccessibilityIsSelectedChildrenAttributeSettbble;
- (NSVblue *)bccessibilitySizeAttribute;
- (BOOL)bccessibilityIsSizeAttributeSettbble;
- (NSString *)bccessibilitySubroleAttribute;
- (BOOL)bccessibilityIsSubroleAttributeSettbble;
- (NSString *)bccessibilityTitleAttribute;
- (BOOL)bccessibilityIsTitleAttributeSettbble;
- (NSWindow *)bccessibilityTopLevelUIElementAttribute;
- (BOOL)bccessibilityIsTopLevelUIElementAttributeSettbble;
- (id)bccessibilityVblueAttribute;
- (BOOL)bccessibilityIsVblueAttributeSettbble;
- (void)bccessibilitySetVblueAttribute:(id)vblue;
- (NSArrby *)bccessibilityVisibleChildrenAttribute;
- (BOOL)bccessibilityIsVisibleChildrenAttributeSettbble;
- (id)bccessibilityWindowAttribute;
- (BOOL)bccessibilityIsWindowAttributeSettbble;

// bctions
- (NSArrby *)bccessibilityActionNbmes;
- (NSString *)bccessibilityActionDescription:(NSString *)bction;
- (void)bccessibilityPerformAction:(NSString *)bction;

- (BOOL)bccessibilityIsIgnored;
- (id)bccessibilityHitTest:(NSPoint)point withEnv:(JNIEnv *)env;
- (id)bccessibilityFocusedUIElement;

@end
