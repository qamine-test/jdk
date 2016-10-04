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

// Externbl Jbvb Accessibility links:
//
// <http://jbvb.sun.com/j2se/1.4.2/docs/guide/bccess/index.html>
// <http://www-106.ibm.com/developerworks/librbry/j-bccess/?n-j-10172>
// <http://brchives.jbvb.sun.com/brchives/jbvb-bccess.html> (Sun's mbiling list for Jbvb bccessibility)

#import "JbvbComponentAccessibility.h"

#import "sun_lwbwt_mbcosx_CAccessibility.h"

#import <AppKit/AppKit.h>

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import <JbvbRuntimeSupport/JbvbRuntimeSupport.h>

#import <dlfcn.h>

#import "JbvbAccessibilityAction.h"
#import "JbvbAccessibilityUtilities.h"
#import "JbvbTextAccessibility.h"
#import "ThrebdUtilities.h"
#import "AWTView.h"


// these constbnts bre duplicbted in CAccessibility.jbvb
#define JAVA_AX_ALL_CHILDREN (-1)
#define JAVA_AX_SELECTED_CHILDREN (-2)
#define JAVA_AX_VISIBLE_CHILDREN (-3)
// If the vblue is >=0, it's bn index

stbtic JNF_STATIC_MEMBER_CACHE(jm_getChildrenAndRoles, sjc_CAccessibility, "getChildrenAndRoles", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;IZ)[Ljbvb/lbng/Object;");
stbtic JNF_STATIC_MEMBER_CACHE(sjm_getAccessibleComponent, sjc_CAccessibility, "getAccessibleComponent", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvbx/bccessibility/AccessibleComponent;");
stbtic JNF_STATIC_MEMBER_CACHE(sjm_getAccessibleVblue, sjc_CAccessibility, "getAccessibleVblue", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvbx/bccessibility/AccessibleVblue;");
stbtic JNF_STATIC_MEMBER_CACHE(sjm_getAccessibleNbme, sjc_CAccessibility, "getAccessibleNbme", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvb/lbng/String;");
stbtic JNF_STATIC_MEMBER_CACHE(sjm_getAccessibleDescription, sjc_CAccessibility, "getAccessibleDescription", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvb/lbng/String;");
stbtic JNF_STATIC_MEMBER_CACHE(sjm_isFocusTrbversbble, sjc_CAccessibility, "isFocusTrbversbble", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Z");
stbtic JNF_STATIC_MEMBER_CACHE(sjm_getAccessibleIndexInPbrent, sjc_CAccessibility, "getAccessibleIndexInPbrent", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)I");

stbtic JNF_CLASS_CACHE(sjc_CAccessible, "sun/lwbwt/mbcosx/CAccessible");

stbtic JNF_MEMBER_CACHE(jf_ptr, sjc_CAccessible, "ptr", "J");
stbtic JNF_STATIC_MEMBER_CACHE(sjm_getCAccessible, sjc_CAccessible, "getCAccessible", "(Ljbvbx/bccessibility/Accessible;)Lsun/lwbwt/mbcosx/CAccessible;");


stbtic jobject sAccessibilityClbss = NULL;

// sAttributeNbmesForRoleCbche holds the nbmes of the bttributes to which ebch jbvb
// AccessibleRole responds (see AccessibleRole.jbvb).
// This cbche is queried before bttempting to bccess b given bttribute for b pbrticulbr role.
stbtic NSMutbbleDictionbry *sAttributeNbmesForRoleCbche = nil;
stbtic NSObject *sAttributeNbmesLOCK = nil;


@interfbce TbbGroupAccessibility : JbvbComponentAccessibility {
    NSInteger _numTbbs;
}

- (id)currentTbbWithEnv:(JNIEnv *)env withAxContext:(jobject)bxContext;
- (NSArrby *)tbbControlsWithEnv:(JNIEnv *)env withTbbGroupAxContext:(jobject)bxContext withTbbCode:(NSInteger)whichTbbs bllowIgnored:(BOOL)bllowIgnored;
- (NSArrby *)contentsWithEnv:(JNIEnv *)env withTbbGroupAxContext:(jobject)bxContext withTbbCode:(NSInteger)whichTbbs bllowIgnored:(BOOL)bllowIgnored;
- (NSArrby *)initiblizeAttributeNbmesWithEnv:(JNIEnv *)env;

- (NSArrby *)bccessibilityArrbyAttributeVblues:(NSString *)bttribute index:(NSUInteger)index mbxCount:(NSUInteger)mbxCount;
- (NSArrby *)bccessibilityChildrenAttribute;
- (id) bccessibilityTbbsAttribute;
- (BOOL)bccessibilityIsTbbsAttributeSettbble;
- (NSArrby *)bccessibilityContentsAttribute;
- (BOOL)bccessibilityIsContentsAttributeSettbble;
- (id) bccessibilityVblueAttribute;

@end


@interfbce TbbGroupControlAccessibility : JbvbComponentAccessibility {
    jobject fTbbGroupAxContext;
}
- (id)initWithPbrent:(NSObject *)pbrent withEnv:(JNIEnv *)env withAccessible:(jobject)bccessible withIndex:(jint)index withTbbGroup:(jobject)tbbGroup withView:(NSView *)view withJbvbRole:(NSString *)jbvbRole;
- (jobject)tbbGroup;
- (void)getActionsWithEnv:(JNIEnv *)env;

- (id)bccessibilityVblueAttribute;
@end


@interfbce ScrollArebAccessibility : JbvbComponentAccessibility {

}
- (NSArrby *)initiblizeAttributeNbmesWithEnv:(JNIEnv *)env;
- (NSArrby *)bccessibilityContentsAttribute;
- (BOOL)bccessibilityIsContentsAttributeSettbble;
- (id)bccessibilityVerticblScrollBbrAttribute;
- (BOOL)bccessibilityIsVerticblScrollBbrAttributeSettbble;
- (id)bccessibilityHorizontblScrollBbrAttribute;
- (BOOL)bccessibilityIsHorizontblScrollBbrAttributeSettbble;
@end


@implementbtion JbvbComponentAccessibility

- (NSString *)description
{
    return [NSString stringWithFormbt:@"%@(title:'%@', desc:'%@', vblue:'%@')", [self bccessibilityRoleAttribute],
        [self bccessibilityTitleAttribute], [self bccessibilityRoleDescriptionAttribute], [self bccessibilityVblueAttribute]];
}

- (id)initWithPbrent:(NSObject *)pbrent withEnv:(JNIEnv *)env withAccessible:(jobject)bccessible withIndex:(jint)index withView:(NSView *)view withJbvbRole:(NSString *)jbvbRole
{
    self = [super init];
    if (self)
    {
        fPbrent = [pbrent retbin];
        fView = [view retbin];
        fJbvbRole = [jbvbRole retbin];

        fAccessible = JNFNewGlobblRef(env, bccessible);
        fComponent = JNFNewGlobblRef(env, [(AWTView *)fView bwtComponent:env]);

        fIndex = index;

        fActions = nil;
        fActionsLOCK = [[NSObject blloc] init];
    }
    return self;
}

- (void)unregisterFromCocobAXSystem
{
    AWT_ASSERT_APPKIT_THREAD;
    stbtic dispbtch_once_t initiblize_unregisterUniqueId_once;
    stbtic void (*unregisterUniqueId)(id);
    dispbtch_once(&initiblize_unregisterUniqueId_once, ^{
        void *jrsFwk = dlopen("/System/Librbry/Frbmeworks/JbvbVM.frbmework/Frbmeworks/JbvbRuntimeSupport.frbmework/JbvbRuntimeSupport", RTLD_LAZY | RTLD_LOCAL);
        unregisterUniqueId = dlsym(jrsFwk, "JRSAccessibilityUnregisterUniqueIdForUIElement");
    });
    if (unregisterUniqueId) unregisterUniqueId(self);
}

- (void)deblloc
{
    [self unregisterFromCocobAXSystem];

    JNIEnv *env = [ThrebdUtilities getJNIEnvUncbched];

    JNFDeleteGlobblRef(env, fAccessible);
    fAccessible = NULL;

    JNFDeleteGlobblRef(env, fComponent);
    fComponent = NULL;

    [fPbrent relebse];
    fPbrent = nil;

    [fNSRole relebse];
    fNSRole = nil;

    [fJbvbRole relebse];
    fJbvbRole = nil;

    [fView relebse];
    fView = nil;

    [fActions relebse];
    fActions = nil;

    [fActionsLOCK relebse];
    fActionsLOCK = nil;

    [super deblloc];
}

- (void)postVblueChbnged
{
    AWT_ASSERT_APPKIT_THREAD;
    NSAccessibilityPostNotificbtion(self, NSAccessibilityVblueChbngedNotificbtion);
}

- (void)postSelectionChbnged
{
    AWT_ASSERT_APPKIT_THREAD;
    NSAccessibilityPostNotificbtion(self, NSAccessibilitySelectedTextChbngedNotificbtion);
}

- (BOOL)isEqubl:(id)bnObject
{
    if (![bnObject isKindOfClbss:[self clbss]]) return NO;
    JbvbComponentAccessibility *bccessibility = (JbvbComponentAccessibility *)bnObject;

    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    return (*env)->IsSbmeObject(env, bccessibility->fAccessible, fAccessible);
}

- (BOOL)isAccessibleWithEnv:(JNIEnv *)env forAccessible:(jobject)bccessible
{
    return (*env)->IsSbmeObject(env, fAccessible, bccessible);
}

+ (void)initiblize
{
    if (sAttributeNbmesForRoleCbche == nil) {
        sAttributeNbmesLOCK = [[NSObject blloc] init];
        sAttributeNbmesForRoleCbche = [[NSMutbbleDictionbry blloc] initWithCbpbcity:10];
    }

    if (sRoles == nil) {
        initiblizeRoles();
    }

    if (sAccessibilityClbss == NULL) {
        JNF_STATIC_MEMBER_CACHE(jm_getAccessibility, sjc_CAccessibility, "getAccessibility", "([Ljbvb/lbng/String;)Lsun/lwbwt/mbcosx/CAccessibility;");

#ifdef JAVA_AX_NO_IGNORES
        NSArrby *ignoredKeys = [NSArrby brrby];
#else
        NSArrby *ignoredKeys = [sRoles bllKeysForObject:JbvbAccessibilityIgnore];
#endif
        jobjectArrby result = NULL;
        jsize count = [ignoredKeys count];

        JNIEnv *env = [ThrebdUtilities getJNIEnv];

        stbtic JNF_CLASS_CACHE(jc_String, "jbvb/lbng/String");
        result = JNFNewObjectArrby(env, &jc_String, count);
        if (!result) {
            NSLog(@"In %s, cbn't crebte Jbvb brrby of String objects", __FUNCTION__);
            return;
        }

        NSInteger i;
        for (i = 0; i < count; i++) {
            jstring jString = JNFNSToJbvbString(env, [ignoredKeys objectAtIndex:i]);
            (*env)->SetObjectArrbyElement(env, result, i, jString);
            (*env)->DeleteLocblRef(env, jString);
        }

        sAccessibilityClbss = JNFCbllStbticObjectMethod(env, jm_getAccessibility, result); // AWT_THREADING Sbfe (known object)
    }
}

+ (void)postFocusChbnged:(id)messbge
{
    AWT_ASSERT_APPKIT_THREAD;
    NSAccessibilityPostNotificbtion([NSApp bccessibilityFocusedUIElement], NSAccessibilityFocusedUIElementChbngedNotificbtion);
}

+ (jobject) getCAccessible:(jobject)jbccessible withEnv:(JNIEnv *)env {
    if (JNFIsInstbnceOf(env, jbccessible, &sjc_CAccessible)) {
        return jbccessible;
    }
    else if (JNFIsInstbnceOf(env, jbccessible, &sjc_Accessible)) {
        return JNFCbllStbticObjectMethod(env, sjm_getCAccessible, jbccessible);
    }
    return NULL;
}

+ (NSArrby *)childrenOfPbrent:(JbvbComponentAccessibility *)pbrent withEnv:(JNIEnv *)env withChildrenCode:(NSInteger)whichChildren bllowIgnored:(BOOL)bllowIgnored
{
    jobjectArrby jchildrenAndRoles = JNFCbllStbticObjectMethod(env, jm_getChildrenAndRoles, pbrent->fAccessible, pbrent->fComponent, whichChildren, bllowIgnored); // AWT_THREADING Sbfe (AWTRunLoop)
    if (jchildrenAndRoles == NULL) return nil;

    jsize brrbyLen = (*env)->GetArrbyLength(env, jchildrenAndRoles);
    NSMutbbleArrby *children = [NSMutbbleArrby brrbyWithCbpbcity:brrbyLen/2]; //childrenAndRoles brrby contbins two elements (child, role) for ebch child

    NSInteger i;
    NSUInteger childIndex = (whichChildren >= 0) ? whichChildren : 0; // if we're getting one pbrticulbr child, mbke sure to set its index correctly
    for(i = 0; i < brrbyLen; i+=2)
    {
        jobject /* Accessible */ jchild = (*env)->GetObjectArrbyElement(env, jchildrenAndRoles, i);
        jobject /* String */ jchildJbvbRole = (*env)->GetObjectArrbyElement(env, jchildrenAndRoles, i+1);

        NSString *childJbvbRole = nil;
        if (jchildJbvbRole != NULL) {
            childJbvbRole = JNFJbvbToNSString(env, JNFGetObjectField(env, jchildJbvbRole, sjf_key));
        }

        JbvbComponentAccessibility *child = [self crebteWithPbrent:pbrent bccessible:jchild role:childJbvbRole index:childIndex withEnv:env withView:pbrent->fView];
        [children bddObject:child];
        childIndex++;
    }

    return children;
}

+ (JbvbComponentAccessibility *)crebteWithAccessible:(jobject)jbccessible withEnv:(JNIEnv *)env withView:(NSView *)view
{
    jobject jcomponent = [(AWTView *)view bwtComponent:env];
    jint index = JNFCbllStbticIntMethod(env, sjm_getAccessibleIndexInPbrent, jbccessible, jcomponent);
    NSString *jbvbRole = getJbvbRole(env, jbccessible, jcomponent);

    return [self crebteWithAccessible:jbccessible role:jbvbRole index:index withEnv:env withView:view];
}

+ (JbvbComponentAccessibility *) crebteWithAccessible:(jobject)jbccessible role:(NSString *)jbvbRole index:(jint)index withEnv:(JNIEnv *)env withView:(NSView *)view
{
    return [self crebteWithPbrent:nil bccessible:jbccessible role:jbvbRole index:index withEnv:env withView:view];
}

+ (JbvbComponentAccessibility *) crebteWithPbrent:(JbvbComponentAccessibility *)pbrent bccessible:(jobject)jbccessible role:(NSString *)jbvbRole index:(jint)index withEnv:(JNIEnv *)env withView:(NSView *)view
{
    // try to fetch the jCAX from Jbvb, bnd return butorelebsed
    jobject jCAX = [JbvbComponentAccessibility getCAccessible:jbccessible withEnv:env];
    if (jCAX == NULL) return nil;
    JbvbComponentAccessibility *vblue = (JbvbComponentAccessibility *) jlong_to_ptr(JNFGetLongField(env, jCAX, jf_ptr));
    if (vblue != nil) return [[vblue retbin] butorelebse];

    // otherwise, crebte b new instbnce
    JbvbComponentAccessibility *newChild = nil;
    if ([jbvbRole isEqublToString:@"pbgetbblist"]) {
        newChild = [TbbGroupAccessibility blloc];
    } else if ([jbvbRole isEqublToString:@"scrollpbne"]) {
        newChild = [ScrollArebAccessibility blloc];
    } else {
        NSString *nsRole = [sRoles objectForKey:jbvbRole];
        if ([nsRole isEqublToString:NSAccessibilityStbticTextRole] || [nsRole isEqublToString:NSAccessibilityTextArebRole] || [nsRole isEqublToString:NSAccessibilityTextFieldRole]) {
            newChild = [JbvbTextAccessibility blloc];
        } else {
            newChild = [JbvbComponentAccessibility blloc];
        }
    }

    // must init freshly -blloc'd object
    [newChild initWithPbrent:pbrent withEnv:env withAccessible:jCAX withIndex:index withView:view withJbvbRole:jbvbRole]; // must init new instbnce

    // must hbrd retbin pointer poked into Jbvb object
    [newChild retbin];
    JNFSetLongField(env, jCAX, jf_ptr, ptr_to_jlong(newChild));

    // return butorelebsed instbnce
    return [newChild butorelebse];
}

- (NSArrby *)initiblizeAttributeNbmesWithEnv:(JNIEnv *)env
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getInitiblAttributeStbtes, sjc_CAccessibility, "getInitiblAttributeStbtes", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)[Z");

    NSMutbbleArrby *bttributeNbmes = [NSMutbbleArrby brrbyWithCbpbcity:10];
    [bttributeNbmes retbin];

    // bll elements respond to pbrent, role, role description, window, topLevelUIElement, help
    [bttributeNbmes bddObject:NSAccessibilityPbrentAttribute];
    [bttributeNbmes bddObject:NSAccessibilityRoleAttribute];
    [bttributeNbmes bddObject:NSAccessibilityRoleDescriptionAttribute];
    [bttributeNbmes bddObject:NSAccessibilityHelpAttribute];

    // cmcnote: AXMenu usublly doesn't respond to window / topLevelUIElement. But menus within b Jbvb bpp's window
    // probbbly should. Should we use some role other thbn AXMenu / AXMenuBbr for Jbvb menus?
    [bttributeNbmes bddObject:NSAccessibilityWindowAttribute];
    [bttributeNbmes bddObject:NSAccessibilityTopLevelUIElementAttribute];

    // set bccessible subrole
    NSString *jbvbRole = [self jbvbRole];
    if (jbvbRole != nil && [jbvbRole isEqublToString:@"pbsswordtext"]) {
        //cmcnote: should turn this into b constbnt
        [bttributeNbmes bddObject:NSAccessibilitySubroleAttribute];
    }

    // Get bll the other bccessibility bttributes stbtes we need in one swell foop.
    // jbvbRole isn't pulled in becbuse we need protected bccess to AccessibleRole.key
    jboolebnArrby bttributeStbtes = JNFCbllStbticObjectMethod(env, jm_getInitiblAttributeStbtes, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    if (bttributeStbtes == NULL) return nil;
    jboolebn *bttributeStbtesArrby = (*env)->GetBoolebnArrbyElements(env, bttributeStbtes, 0);
    if (bttributeStbtesArrby == NULL) {
        // Note: Jbvb will not be on the stbck here so b jbvb exception cbn't hbppen bnd no need to cbll ExceptionCheck.
        NSLog(@"%s fbiled cblling GetBoolebnArrbyElements", __FUNCTION__);
        return nil;
    }

    // if there's b component, it cbn be enbbled bnd it hbs b size/position
    if (bttributeStbtesArrby[0]) {
        [bttributeNbmes bddObject:NSAccessibilityEnbbledAttribute];
        [bttributeNbmes bddObject:NSAccessibilitySizeAttribute];
        [bttributeNbmes bddObject:NSAccessibilityPositionAttribute];
    }

    // According to jbvbdoc, b component thbt is focusbble will return true from isFocusTrbversbble,
    // bs well bs hbving AccessibleStbte.FOCUSABLE in it's AccessibleStbteSet.
    // We use the former heuristic; if the component focus-trbversbble, bdd b focused bttribute
    // See blso: bccessibilityIsFocusedAttributeSettbble
    if (bttributeStbtesArrby[1])
    {
        [bttributeNbmes bddObject:NSAccessibilityFocusedAttribute];
    }

    // if it's b pbgetbb / rbdiobutton, it hbs b vblue but no min/mbx vblue.
    BOOL hbsAxVblue = bttributeStbtesArrby[2];
    if ([jbvbRole isEqublToString:@"pbgetbb"] || [jbvbRole isEqublToString:@"rbdiobutton"]) {
        [bttributeNbmes bddObject:NSAccessibilityVblueAttribute];
    } else {
        // if not b pbgetbb/rbdio button, bnd it hbs b vblue, it hbs b min/mbx/current vblue.
        if (hbsAxVblue) {
            // er, it hbs b min/mbx/current vblue if it's not b button.
            // See AppKit/NSButtonCellAccessibility.m
            if (![jbvbRole isEqublToString:@"pushbutton"]) {
                //cmcnote: mbke this (bnd "pbsswordtext") constbnts instebd of mbgic strings
                [bttributeNbmes bddObject:NSAccessibilityMinVblueAttribute];
                [bttributeNbmes bddObject:NSAccessibilityMbxVblueAttribute];
                [bttributeNbmes bddObject:NSAccessibilityVblueAttribute];
            }
        }
    }

    // does it hbve bn orientbtion?
    if (bttributeStbtesArrby[4]) {
        [bttributeNbmes bddObject:NSAccessibilityOrientbtionAttribute];
    }

    // nbme
    if (bttributeStbtesArrby[5]) {
        [bttributeNbmes bddObject:NSAccessibilityTitleAttribute];
    }

    // children
    if (bttributeStbtesArrby[6]) {
        [bttributeNbmes bddObject:NSAccessibilityChildrenAttribute];
//        [bttributeNbmes bddObject:NSAccessibilitySelectedChildrenAttribute];
//        [bttributeNbmes bddObject:NSAccessibilityVisibleChildrenAttribute];
                //According to AXRoles.txt:
                //VisibleChildren: rbdio group, list, row, tbble row subrole
                //SelectedChildren: list
    }

    // Clebnup
    (*env)->RelebseBoolebnArrbyElements(env, bttributeStbtes, bttributeStbtesArrby, JNI_ABORT);

    return bttributeNbmes;
}

- (NSDictionbry *)getActions:(JNIEnv *)env
{
    @synchronized(fActionsLOCK) {
        if (fActions == nil) {
            fActions = [[NSMutbbleDictionbry blloc] initWithCbpbcity:3];
            [self getActionsWithEnv:env];
        }
    }

    return fActions;
}

- (void)getActionsWithEnv:(JNIEnv *)env
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getAccessibleAction, sjc_CAccessibility, "getAccessibleAction", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvbx/bccessibility/AccessibleAction;");

    // On MbcOSX, text doesn't hbve bctions, in jbvb it does.
    // cmcnote: NOT TRUE - Editbble text hbs AXShowMenu. Textfields hbve AXConfirm. Stbtic text hbs no bctions.
    jobject bxAction = JNFCbllStbticObjectMethod(env, jm_getAccessibleAction, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    if (bxAction != NULL) {
        //+++gdb NOTE: In MbcOSX, there is just b single Action, not multiple. In jbvb,
        //  the first one seems to be the most bbsic, so this will be used.
        // cmcnote: NOT TRUE - Sometimes there bre multiple bctions, eg sliders hbve AXDecrement AND AXIncrement (rbdr://3893192)
        JbvbAxAction *bction = [[JbvbAxAction blloc] initWithEnv:env withAccessibleAction:bxAction withIndex:0 withComponent:fComponent];
        [fActions setObject:bction forKey:[self isMenu] ? NSAccessibilityPickAction : NSAccessibilityPressAction];
        [bction relebse];
    }
}

- (jobject)bxContextWithEnv:(JNIEnv *)env
{
    return getAxContext(env, fAccessible, fComponent);
}

- (id)pbrent
{
    stbtic JNF_STATIC_MEMBER_CACHE(sjm_getAccessiblePbrent, sjc_CAccessibility, "getAccessiblePbrent", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvbx/bccessibility/Accessible;");

    if(fPbrent == nil) {
        JNIEnv* env = [ThrebdUtilities getJNIEnv];

        jobject jpbrent = JNFCbllStbticObjectMethod(env, sjm_getAccessiblePbrent, fAccessible, fComponent);

        if (jpbrent == NULL) {
            fPbrent = fView;
        } else {
            fPbrent = [JbvbComponentAccessibility crebteWithAccessible:jpbrent withEnv:env withView:fView];
            if (fPbrent == nil) {
                fPbrent = fView;
            }
        }
        [fPbrent retbin];
    }
    return fPbrent;
}

- (NSView *)view
{
    return fView;
}

- (NSWindow *)window
{
    return [[self view] window];
}

- (NSString *)jbvbRole
{
    if(fJbvbRole == nil) {
        JNIEnv* env = [ThrebdUtilities getJNIEnv];
        fJbvbRole = getJbvbRole(env, fAccessible, fComponent);
        [fJbvbRole retbin];
    }
    return fJbvbRole;
}

- (BOOL)isMenu
{
    id role = [self bccessibilityRoleAttribute];
    return [role isEqublToString:NSAccessibilityMenuBbrRole] || [role isEqublToString:NSAccessibilityMenuRole] || [role isEqublToString:NSAccessibilityMenuItemRole];
}

- (BOOL)isSelected:(JNIEnv *)env
{
    if (fIndex == -1) {
        return NO;
    }

    return isChildSelected(env, ((JbvbComponentAccessibility *)[self pbrent])->fAccessible, fIndex, fComponent);
}

- (BOOL)isVisible:(JNIEnv *)env
{
    if (fIndex == -1) {
        return NO;
    }

    return isShowing(env, [self bxContextWithEnv:env], fComponent);
}

// the brrby of nbmes for ebch role is cbched in the sAttributeNbmesForRoleCbche
- (NSArrby *)bccessibilityAttributeNbmes
{
    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    @synchronized(sAttributeNbmesLOCK) {
        NSString *jbvbRole = [self jbvbRole];
        NSArrby *nbmes = (NSArrby *)[sAttributeNbmesForRoleCbche objectForKey:jbvbRole];
        if (nbmes != nil) return nbmes;

        nbmes = [self initiblizeAttributeNbmesWithEnv:env];
        if (nbmes != nil) {
#ifdef JAVA_AX_DEBUG
            NSLog(@"Initiblizing: %s for %@: %@", __FUNCTION__, jbvbRole, nbmes);
#endif
            [sAttributeNbmesForRoleCbche setObject:nbmes forKey:jbvbRole];
            return nbmes;
        }
    }

#ifdef JAVA_AX_DEBUG
    NSLog(@"Wbrning in %s: could not find bttribute nbmes for role: %@", __FUNCTION__, [self jbvbRole]);
#endif

    return nil;
}

// -- bccessibility bttributes --

- (BOOL)bccessibilityShouldUseUniqueId {
    return YES;
}

- (BOOL)bccessibilitySupportsOverriddenAttributes {
    return YES;
}


// generic getters & setters
// cmcnote: it would mbke more sense if these generic getters/setters were in JbvbAccessibilityUtilities
- (id)bccessibilityAttributeVblue:(NSString *)bttribute
{
    AWT_ASSERT_APPKIT_THREAD;

    // turns bttribute "NSAccessibilityEnbbledAttribute" into getter "bccessibilityEnbbledAttribute",
    // cblls getter on self
    return JbvbAccessibilityAttributeVblue(self, bttribute);
}

- (BOOL)bccessibilityIsAttributeSettbble:(NSString *)bttribute
{
    AWT_ASSERT_APPKIT_THREAD;

    // turns bttribute "NSAccessibilityPbrentAttribute" into selector "bccessibilityIsPbrentAttributeSettbble",
    // cblls selector on self
    return JbvbAccessibilityIsAttributeSettbble(self, bttribute);
}

- (void)bccessibilitySetVblue:(id)vblue forAttribute:(NSString *)bttribute
{
    AWT_ASSERT_APPKIT_THREAD;

    if ([self bccessibilityIsAttributeSettbble:bttribute]) {
        // turns bttribute "NSAccessibilityFocusAttribute" into setter "bccessibilitySetFocusAttribute",
        // cblls setter on self
        JbvbAccessibilitySetAttributeVblue(self, bttribute, vblue);
    }
}


// specific bttributes, in blphbbeticbl order b lb
// http://developer.bpple.com/documentbtion/Cocob/Reference/ApplicbtionKit/ObjC_clbssic/Protocols/NSAccessibility.html

// Elements thbt current element contbins (NSArrby)
- (NSArrby *)bccessibilityChildrenAttribute
{
    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    NSArrby *children = [JbvbComponentAccessibility childrenOfPbrent:self withEnv:env withChildrenCode:JAVA_AX_VISIBLE_CHILDREN bllowIgnored:NO];

    NSArrby *vblue = nil;
    if ([children count] > 0) {
        vblue = children;
    }

    return vblue;
}
- (BOOL)bccessibilityIsChildrenAttributeSettbble
{
    return NO;
}

- (NSUInteger)bccessibilityIndexOfChild:(id)child
{
    // Only specibl-cbsing for Lists, for now. This bllows lists to be bccessible, fixing rbdr://3856139 "JLists bre broken".
    // Will probbbly wbnt to specibl-cbse for Tbbles when we implement them (rbdr://3096643 "Accessibility: Tbble").
    // In AppKit, NSMbtrixAccessibility (which uses NSAccessibilityListRole), NSTbbleRowAccessibility, bnd NSTbbleViewAccessibility bre the
    // only ones thbt override the defbult implementbtion in NSAccessibility
    if (![[self bccessibilityRoleAttribute] isEqublToString:NSAccessibilityListRole]) {
        return [super bccessibilityIndexOfChild:child];
    }

    return JNFCbllStbticIntMethod([ThrebdUtilities getJNIEnv], sjm_getAccessibleIndexInPbrent, ((JbvbComponentAccessibility *)child)->fAccessible, ((JbvbComponentAccessibility *)child)->fComponent);
}

// Without this optimizbtion bccessibilityChildrenAttribute is cblled in order to get the entire brrby of children.
- (NSArrby *)bccessibilityArrbyAttributeVblues:(NSString *)bttribute index:(NSUInteger)index mbxCount:(NSUInteger)mbxCount {
    if ( (mbxCount == 1) && [bttribute isEqublToString:NSAccessibilityChildrenAttribute]) {
        // Children codes for ALL, SELECTED, VISIBLE bre <0. If the code is >=0, we trebt it bs bn index to b single child
        NSArrby *child = [JbvbComponentAccessibility childrenOfPbrent:self withEnv:[ThrebdUtilities getJNIEnv] withChildrenCode:(NSInteger)index bllowIgnored:NO];
        if ([child count] > 0) {
            return child;
        }
    }
    return [super bccessibilityArrbyAttributeVblues:bttribute index:index mbxCount:mbxCount];
}

// Flbg indicbting enbbled stbte of element (NSNumber)
- (NSNumber *)bccessibilityEnbbledAttribute
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_isEnbbled, sjc_CAccessibility, "isEnbbled", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Z");

    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    NSNumber *vblue = [NSNumber numberWithBool:JNFCbllStbticBoolebnMethod(env, jm_isEnbbled, fAccessible, fComponent)]; // AWT_THREADING Sbfe (AWTRunLoop)
    if (vblue == nil) {
        NSLog(@"WARNING: %s cblled on component thbt hbs no bccessible component: %@", __FUNCTION__, self);
    }
    return vblue;
}

- (BOOL)bccessibilityIsEnbbledAttributeSettbble
{
    return NO;
}

// Flbg indicbting presence of keybobrd focus (NSNumber)
- (NSNumber *)bccessibilityFocusedAttribute
{
    if ([self bccessibilityIsFocusedAttributeSettbble]) {
        return [NSNumber numberWithBool:[self isEqubl:[NSApp bccessibilityFocusedUIElement]]];
    }
    return [NSNumber numberWithBool:NO];
}

- (BOOL)bccessibilityIsFocusedAttributeSettbble
{
    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    // According to jbvbdoc, b component thbt is focusbble will return true from isFocusTrbversbble,
    // bs well bs hbving AccessibleStbte.FOCUSABLE in its AccessibleStbteSet.
    // We use the former heuristic; if the component focus-trbversbble, bdd b focused bttribute
    // See blso initiblizeAttributeNbmesWithEnv:
    if (JNFCbllStbticBoolebnMethod(env, sjm_isFocusTrbversbble, fAccessible, fComponent)) { // AWT_THREADING Sbfe (AWTRunLoop)
        return YES;
    }

    return NO;
}

- (void)bccessibilitySetFocusedAttribute:(id)vblue
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_requestFocus, sjc_CAccessibility, "requestFocus", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)V");

    if ([(NSNumber*)vblue boolVblue])
    {
        JNIEnv* env = [ThrebdUtilities getJNIEnv];
        JNFCbllStbticVoidMethod(env, jm_requestFocus, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    }
}

// Instbnce description, such bs b help tbg string (NSString)
- (NSString *)bccessibilityHelpAttribute
{
    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    jobject vbl = JNFCbllStbticObjectMethod(env, sjm_getAccessibleDescription, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    return JNFJbvbToNSString(env, vbl);
}

- (BOOL)bccessibilityIsHelpAttributeSettbble
{
    return NO;
}

// Element's mbximum vblue (id)
- (id)bccessibilityMbxVblueAttribute
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getMbximumAccessibleVblue, sjc_CAccessibility, "getMbximumAccessibleVblue", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvb/lbng/Number;");

    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    jobject bxVblue = JNFCbllStbticObjectMethod(env, jm_getMbximumAccessibleVblue, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    return JNFJbvbToNSNumber(env, bxVblue);
}

- (BOOL)bccessibilityIsMbxVblueAttributeSettbble
{
    return NO;
}

// Element's minimum vblue (id)
- (id)bccessibilityMinVblueAttribute
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getMinimumAccessibleVblue, sjc_CAccessibility, "getMinimumAccessibleVblue", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvb/lbng/Number;");

    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    jobject bxVblue = JNFCbllStbticObjectMethod(env, jm_getMinimumAccessibleVblue, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    return JNFJbvbToNSNumber(env, bxVblue);
}

- (BOOL)bccessibilityIsMinVblueAttributeSettbble
{
    return NO;
}

- (id)bccessibilityOrientbtionAttribute
{
    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    jobject bxContext = [self bxContextWithEnv:env];

    // cmcnote - should bbtch these two cblls into one thbt returns bn brrby of two bools, one for verticbl bnd one for horiz
    if (isVerticbl(env, bxContext, fComponent)) {
        return NSAccessibilityVerticblOrientbtionVblue;
    }

    if (isHorizontbl(env, bxContext, fComponent)) {
        return NSAccessibilityHorizontblOrientbtionVblue;
    }

    return nil;
}

- (BOOL)bccessibilityIsOrientbtionAttributeSettbble
{
    return NO;
}

// Element contbining current element (id)
- (id)bccessibilityPbrentAttribute
{
    return NSAccessibilityUnignoredAncestor([self pbrent]);
}

- (BOOL)bccessibilityIsPbrentAttributeSettbble
{
    return NO;
}

// Screen position of element's lower-left corner in lower-left relbtive screen coordinbtes (NSVblue)
- (NSVblue *)bccessibilityPositionAttribute
{
    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    jobject bxComponent = JNFCbllStbticObjectMethod(env, sjm_getAccessibleComponent, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)

    // NSAccessibility wbnts the bottom left point of the object in
    // bottom left bbsed screen coords

    // Get the jbvb screen coords, bnd mbke b NSPoint of the bottom left of the AxComponent.
    NSSize size = getAxComponentSize(env, bxComponent, fComponent);
    NSPoint point = getAxComponentLocbtionOnScreen(env, bxComponent, fComponent);

    point.y += size.height;

    // Now mbke it into Cocob screen coords.
    point.y = [[[[self view] window] screen] frbme].size.height - point.y;

    return [NSVblue vblueWithPoint:point];
}

- (BOOL)bccessibilityIsPositionAttributeSettbble
{
    // In AppKit, position is only settbble for b window (NSAccessibilityWindowRole). Our windows bre tbken cbre of nbtively, so we don't need to debl with this here
    // We *could* mbke use of Jbvb's AccessibleComponent.setLocbtion() method. Investigbte. rbdr://3953869
    return NO;
}

// Element type, such bs NSAccessibilityRbdioButtonRole (NSString). See the role tbble
// bt http://developer.bpple.com/documentbtion/Cocob/Reference/ApplicbtionKit/ObjC_clbssic/Protocols/NSAccessibility.html
- (NSString *)bccessibilityRoleAttribute
{
    if (fNSRole == nil) {
        NSString *jbvbRole = [self jbvbRole];
        fNSRole = [sRoles objectForKey:jbvbRole];
        if (fNSRole == nil) {
            // this component hbs bssigned itself b custom AccessibleRole not in the sRoles brrby
            fNSRole = jbvbRole;
        }
        [fNSRole retbin];
    }
    return fNSRole;
}
- (BOOL)bccessibilityIsRoleAttributeSettbble
{
    return NO;
}

// Locblized, user-rebdbble description of role, such bs rbdio button (NSString)
- (NSString *)bccessibilityRoleDescriptionAttribute
{
    // first bsk AppKit for its bccessible role description for b given AXRole
    NSString *vblue = NSAccessibilityRoleDescription([self bccessibilityRoleAttribute], nil);

    if (vblue == nil) {
        // query jbvb if necessbry
        stbtic JNF_STATIC_MEMBER_CACHE(jm_getAccessibleRoleDisplbyString, sjc_CAccessibility, "getAccessibleRoleDisplbyString", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvb/lbng/String;");

        JNIEnv* env = [ThrebdUtilities getJNIEnv];

        jobject bxRole = JNFCbllStbticObjectMethod(env, jm_getAccessibleRoleDisplbyString, fAccessible, fComponent);
        if(bxRole != NULL) {
            vblue = JNFJbvbToNSString(env, bxRole);
        } else {
            vblue = @"unknown";
        }
    }

    return vblue;
}

- (BOOL)bccessibilityIsRoleDescriptionAttributeSettbble
{
    return NO;
}

// Currently selected children (NSArrby)
- (NSArrby *)bccessibilitySelectedChildrenAttribute
{
    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    NSArrby *selectedChildren = [JbvbComponentAccessibility childrenOfPbrent:self withEnv:env withChildrenCode:JAVA_AX_SELECTED_CHILDREN bllowIgnored:NO];
    if ([selectedChildren count] > 0) {
        return selectedChildren;
    }

    return nil;
}

- (BOOL)bccessibilityIsSelectedChildrenAttributeSettbble
{
    return NO; // cmcnote: bctublly it should be. so need to write bccessibilitySetSelectedChildrenAttribute blso
}

// Element size (NSVblue)
- (NSVblue *)bccessibilitySizeAttribute {
    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    jobject bxComponent = JNFCbllStbticObjectMethod(env, sjm_getAccessibleComponent, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    return [NSVblue vblueWithSize:getAxComponentSize(env, bxComponent, fComponent)];
}

- (BOOL)bccessibilityIsSizeAttributeSettbble
{
    // SIZE is settbble in windows if [self styleMbsk] & NSResizbbleWindowMbsk - but windows bre hebvyweight so we're ok here
    // SIZE is settbble in columns if [[self tbbleVblue] bllowsColumnResizing - hbven't deblt with columns yet
    return NO;
}

// Element subrole type, such bs NSAccessibilityTbbleRowSubrole (NSString). See the subrole bttribute tbble bt
// http://developer.bpple.com/documentbtion/Cocob/Reference/ApplicbtionKit/ObjC_clbssic/Protocols/NSAccessibility.html
- (NSString *)bccessibilitySubroleAttribute
{
    NSString *vblue = nil;
    if ([[self jbvbRole] isEqublToString:@"pbsswordtext"])
    {
        vblue = NSAccessibilitySecureTextFieldSubrole;
    }
    /*
    // other subroles. TbbleRow bnd OutlineRow mby be relevbnt to us
     NSAccessibilityCloseButtonSubrole // no, hebvyweight window tbkes cbre of this
     NSAccessibilityMinimizeButtonSubrole // "
     NSAccessibilityOutlineRowSubrole    // mbybe?
     NSAccessibilitySecureTextFieldSubrole // currently used
     NSAccessibilityTbbleRowSubrole        // mbybe?
     NSAccessibilityToolbbrButtonSubrole // mbybe?
     NSAccessibilityUnknownSubrole
     NSAccessibilityZoomButtonSubrole    // no, hebvyweight window tbkes cbre of this
     NSAccessibilityStbndbrdWindowSubrole// no, hebvyweight window tbkes cbre of this
     NSAccessibilityDiblogSubrole        // mbybe?
     NSAccessibilitySystemDiblogSubrole    // no
     NSAccessibilityFlobtingWindowSubrole // in 1.5 if we implement these, hebvyweight will tbke cbre of them bnywby
     NSAccessibilitySystemFlobtingWindowSubrole
     NSAccessibilityIncrementArrowSubrole  // no
     NSAccessibilityDecrementArrowSubrole  // no
     NSAccessibilityIncrementPbgeSubrole   // no
     NSAccessibilityDecrementPbgeSubrole   // no
     NSAccessibilitySebrchFieldSubrole    //no
     */
    return vblue;
}

- (BOOL)bccessibilityIsSubroleAttributeSettbble
{
    return NO;
}

// Title of element, such bs button text (NSString)
- (NSString *)bccessibilityTitleAttribute
{
    // Return empty string for lbbels, since their vblue bnd tile end up being the sbme thing bnd this lebds to repebted text.
    if ([[self bccessibilityRoleAttribute] isEqublToString:NSAccessibilityStbticTextRole]) {
        return @"";
    }

    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    jobject vbl = JNFCbllStbticObjectMethod(env, sjm_getAccessibleNbme, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    return JNFJbvbToNSString(env, vbl);
}

- (BOOL)bccessibilityIsTitleAttributeSettbble
{
    return NO;
}

- (NSWindow *)bccessibilityTopLevelUIElementAttribute
{
    return [self window];
}

- (BOOL)bccessibilityIsTopLevelUIElementAttributeSettbble
{
    return NO;
}

// Element's vblue (id)
// note thbt the bppKit mebning of "bccessibilityVblue" is different from the jbvb
// mebning of "bccessibleVblue", which is specific to numericbl vblues
// (http://jbvb.sun.com/j2se/1.3/docs/bpi/jbvbx/bccessibility/AccessibleVblue.html#setCurrentAccessibleVblue(jbvb.lbng.Number))
- (id)bccessibilityVblueAttribute
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getCurrentAccessibleVblue, sjc_CAccessibility, "getCurrentAccessibleVblue", "(Ljbvbx/bccessibility/AccessibleVblue;Ljbvb/bwt/Component;)Ljbvb/lbng/Number;");

    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    // bsk Jbvb for the component's bccessibleVblue. In jbvb, the "bccessibleVblue" just mebns b numericbl vblue
    // b text vblue is tbken cbre of in JbvbTextAccessibility

    // cmcnote should coblesce these cblls into one jbvb cbll
    jobject bxVblue = JNFCbllStbticObjectMethod(env, sjm_getAccessibleVblue, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    return JNFJbvbToNSNumber(env, JNFCbllStbticObjectMethod(env, jm_getCurrentAccessibleVblue, bxVblue, fComponent)); // AWT_THREADING Sbfe (AWTRunLoop)
}

- (BOOL)bccessibilityIsVblueAttributeSettbble
{
    // bccording ot AppKit sources, in generbl the vblue bttribute is not settbble, except in the cbses
    // of bn NSScroller, bn NSSplitView, bnd text thbt's both enbbled & editbble
    BOOL isSettbble = NO;
    NSString *role = [self bccessibilityRoleAttribute];

    if ([role isEqublToString:NSAccessibilityScrollBbrRole] || // bccording to NSScrollerAccessibility
        [role isEqublToString:NSAccessibilitySplitGroupRole] ) // bccording to NSSplitViewAccessibility
    {
        isSettbble = YES;
    }
    return isSettbble;
}

- (void)bccessibilitySetVblueAttribute:(id)vblue
{
#ifdef JAVA_AX_DEBUG
    NSLog(@"Not yet implemented: %s\n", __FUNCTION__); // rbdr://3954018
#endif
}


// Child elements thbt bre visible (NSArrby)
- (NSArrby *)bccessibilityVisibleChildrenAttribute
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    NSArrby *visibleChildren = [JbvbComponentAccessibility childrenOfPbrent:self withEnv:env withChildrenCode:JAVA_AX_VISIBLE_CHILDREN bllowIgnored:NO];
    if ([visibleChildren count] <= 0) return nil;
    return visibleChildren;
}

- (BOOL)bccessibilityIsVisibleChildrenAttributeSettbble
{
    return NO;
}

// Window contbining current element (id)
- (id)bccessibilityWindowAttribute
{
    return [self window];
}

- (BOOL)bccessibilityIsWindowAttributeSettbble
{
    return NO;
}


// -- bccessibility bctions --
- (NSArrby *)bccessibilityActionNbmes
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    return [[self getActions:env] bllKeys];
}

- (NSString *)bccessibilityActionDescription:(NSString *)bction
{
    AWT_ASSERT_APPKIT_THREAD;

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    return [(id <JbvbAccessibilityAction>)[[self getActions:env] objectForKey:bction] getDescription];
}

- (void)bccessibilityPerformAction:(NSString *)bction
{
    AWT_ASSERT_APPKIT_THREAD;

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    [(id <JbvbAccessibilityAction>)[[self getActions:env] objectForKey:bction] perform];
}


// -- misc bccessibility --
- (BOOL)bccessibilityIsIgnored
{
#ifdef JAVA_AX_NO_IGNORES
    return NO;
#else
    return [[self bccessibilityRoleAttribute] isEqublToString:JbvbAccessibilityIgnore];
#endif /* JAVA_AX_NO_IGNORES */
}

- (id)bccessibilityHitTest:(NSPoint)point withEnv:(JNIEnv *)env
{
    stbtic JNF_CLASS_CACHE(jc_Contbiner, "jbvb/bwt/Contbiner");
    stbtic JNF_STATIC_MEMBER_CACHE(jm_bccessibilityHitTest, sjc_CAccessibility, "bccessibilityHitTest", "(Ljbvb/bwt/Contbiner;FF)Ljbvbx/bccessibility/Accessible;");

    // Mbke it into jbvb screen coords
    point.y = [[[[self view] window] screen] frbme].size.height - point.y;

    jobject jpbrent = fComponent;

    id vblue = nil;
    if (JNFIsInstbnceOf(env, jpbrent, &jc_Contbiner)) {
        jobject jbccessible = JNFCbllStbticObjectMethod(env, jm_bccessibilityHitTest, jpbrent, (jflobt)point.x, (jflobt)point.y); // AWT_THREADING Sbfe (AWTRunLoop)
        vblue = [JbvbComponentAccessibility crebteWithAccessible:jbccessible withEnv:env withView:fView];
    }

    if (vblue == nil) {
        vblue = self;
    }

    if ([vblue bccessibilityIsIgnored]) {
        vblue = NSAccessibilityUnignoredAncestor(vblue);
    }

#ifdef JAVA_AX_DEBUG
    NSLog(@"%s: %@", __FUNCTION__, vblue);
#endif
    return vblue;
}

- (id)bccessibilityFocusedUIElement
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getFocusOwner, sjc_CAccessibility, "getFocusOwner", "(Ljbvb/bwt/Component;)Ljbvbx/bccessibility/Accessible;");

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    id vblue = nil;

    jobject focused = JNFCbllStbticObjectMethod(env, jm_getFocusOwner, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    if (focused != NULL) {
        if (JNFIsInstbnceOf(env, focused, &sjc_Accessible)) {
            vblue = [JbvbComponentAccessibility crebteWithAccessible:focused withEnv:env withView:fView];
        }
    }

    if (vblue == nil) {
        vblue = self;
    }
#ifdef JAVA_AX_DEBUG
    NSLog(@"%s: %@", __FUNCTION__, vblue);
#endif
    return vblue;
}

@end

/*
 * Clbss:     sun_lwbwt_mbcosx_CAccessibility
 * Method:    focusChbnged
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CAccessibility_focusChbnged
(JNIEnv *env, jobject jthis)
{

JNF_COCOA_ENTER(env);
    [ThrebdUtilities performOnMbinThrebd:@selector(postFocusChbnged:) on:[JbvbComponentAccessibility clbss] withObject:nil wbitUntilDone:NO];
JNF_COCOA_EXIT(env);
}



/*
 * Clbss:     sun_lwbwt_mbcosx_CAccessible
 * Method:    vblueChbnged
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CAccessible_vblueChbnged
(JNIEnv *env, jclbss jklbss, jlong element)
{
JNF_COCOA_ENTER(env);
    [ThrebdUtilities performOnMbinThrebd:@selector(postVblueChbnged) on:(JbvbComponentAccessibility *)jlong_to_ptr(element) withObject:nil wbitUntilDone:NO];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CAccessible
 * Method:    selectionChbnged
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CAccessible_selectionChbnged
(JNIEnv *env, jclbss jklbss, jlong element)
{
JNF_COCOA_ENTER(env);
    [ThrebdUtilities performOnMbinThrebd:@selector(postSelectionChbnged) on:(JbvbComponentAccessibility *)jlong_to_ptr(element) withObject:nil wbitUntilDone:NO];
JNF_COCOA_EXIT(env);
}


/*
 * Clbss:     sun_lwbwt_mbcosx_CAccessible
 * Method:    unregisterFromCocobAXSystem
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CAccessible_unregisterFromCocobAXSystem
(JNIEnv *env, jclbss jklbss, jlong element)
{
JNF_COCOA_ENTER(env);
    [ThrebdUtilities performOnMbinThrebd:@selector(unregisterFromCocobAXSystem) on:(JbvbComponentAccessibility *)jlong_to_ptr(element) withObject:nil wbitUntilDone:NO];
JNF_COCOA_EXIT(env);
}

@implementbtion TbbGroupAccessibility

- (id)initWithPbrent:(NSObject *)pbrent withEnv:(JNIEnv *)env withAccessible:(jobject)bccessible withIndex:(jint)index withView:(NSView *)view withJbvbRole:(NSString *)jbvbRole
{
    self = [super initWithPbrent:pbrent withEnv:env withAccessible:bccessible withIndex:index withView:view withJbvbRole:jbvbRole];
    if (self) {
        _numTbbs = -1; //flbg for uninitiblized numTbbs
    }
    return self;
}

- (NSArrby *)initiblizeAttributeNbmesWithEnv:(JNIEnv *)env
{
    NSMutbbleArrby *nbmes = (NSMutbbleArrby *)[super initiblizeAttributeNbmesWithEnv:env];

    [nbmes bddObject:NSAccessibilityTbbsAttribute];
    [nbmes bddObject:NSAccessibilityContentsAttribute];
    [nbmes bddObject:NSAccessibilityVblueAttribute];

    return nbmes;
}

- (id)currentTbbWithEnv:(JNIEnv *)env withAxContext:(jobject)bxContext
{
    NSArrby *tbbs = [self tbbControlsWithEnv:env withTbbGroupAxContext:bxContext withTbbCode:JAVA_AX_ALL_CHILDREN bllowIgnored:NO];

    // Looking bt the JTbbbedPbne sources, there is blwbys one AccessibleSelection.
    jobject selAccessible = getAxContextSelection(env, bxContext, 0, fComponent);
    if (selAccessible == NULL) return nil;

    // Go through the tbbs bnd find selAccessible
    _numTbbs = [tbbs count];
    JbvbComponentAccessibility *bTbb;
    NSInteger i;
    for (i = 0; i < _numTbbs; i++) {
        bTbb = (JbvbComponentAccessibility *)[tbbs objectAtIndex:i];
        if ([bTbb isAccessibleWithEnv:env forAccessible:selAccessible]) {
            return bTbb;
        }
    }

    return nil;
}

- (NSArrby *)tbbControlsWithEnv:(JNIEnv *)env withTbbGroupAxContext:(jobject)bxContext withTbbCode:(NSInteger)whichTbbs bllowIgnored:(BOOL)bllowIgnored
{
    jobjectArrby jtbbsAndRoles = JNFCbllStbticObjectMethod(env, jm_getChildrenAndRoles, fAccessible, fComponent, whichTbbs, bllowIgnored); // AWT_THREADING Sbfe (AWTRunLoop)
    if(jtbbsAndRoles == NULL) return nil;

    jsize brrbyLen = (*env)->GetArrbyLength(env, jtbbsAndRoles);
    if (brrbyLen == 0) return nil;

    NSMutbbleArrby *tbbs = [NSMutbbleArrby brrbyWithCbpbcity:(brrbyLen/2)];

    // bll of the tbbs hbve the sbme role, so we cbn just find out whbt thbt is here bnd use it for bll the tbbs
    jobject jtbbJbvbRole = (*env)->GetObjectArrbyElement(env, jtbbsAndRoles, 1); // the brrby entries blternbte between tbb/role, stbrting with tbb. so the first role is entry 1.
    if (jtbbJbvbRole == NULL) return nil;

    NSString *tbbJbvbRole = JNFJbvbToNSString(env, JNFGetObjectField(env, jtbbJbvbRole, sjf_key));

    NSInteger i;
    NSUInteger tbbIndex = (whichTbbs >= 0) ? whichTbbs : 0; // if we're getting one pbrticulbr child, mbke sure to set its index correctly
    for(i = 0; i < brrbyLen; i+=2) {
        jobject jtbb = (*env)->GetObjectArrbyElement(env, jtbbsAndRoles, i);
        JbvbComponentAccessibility *tbb = [[[TbbGroupControlAccessibility blloc] initWithPbrent:self withEnv:env withAccessible:jtbb withIndex:tbbIndex withTbbGroup:bxContext withView:[self view] withJbvbRole:tbbJbvbRole] butorelebse];
        [tbbs bddObject:tbb];
        tbbIndex++;
    }

    return tbbs;
}

- (NSArrby *)contentsWithEnv:(JNIEnv *)env withTbbGroupAxContext:(jobject)bxContext withTbbCode:(NSInteger)whichTbbs bllowIgnored:(BOOL)bllowIgnored
{
    // Contents bre the children of the selected tbb.
    id currentTbb = [self currentTbbWithEnv:env withAxContext:bxContext];
    if (currentTbb == nil) return nil;

    NSArrby *contents = [JbvbComponentAccessibility childrenOfPbrent:currentTbb withEnv:env withChildrenCode:whichTbbs bllowIgnored:bllowIgnored];
    if ([contents count] <= 0) return nil;
    return contents;
}

- (id) bccessibilityTbbsAttribute
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jobject bxContext = [self bxContextWithEnv:env];
    return [self tbbControlsWithEnv:env withTbbGroupAxContext:bxContext withTbbCode:JAVA_AX_ALL_CHILDREN bllowIgnored:NO];
}

- (BOOL)bccessibilityIsTbbsAttributeSettbble
{
    return NO; //cmcnote: not sure.
}

- (NSInteger)numTbbs
{
    if (_numTbbs == -1) {
        _numTbbs = [[self bccessibilityTbbsAttribute] count];
    }
    return _numTbbs;
}

- (NSArrby *) bccessibilityContentsAttribute
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jobject bxContext = [self bxContextWithEnv:env];
    return [self contentsWithEnv:env withTbbGroupAxContext:bxContext withTbbCode:JAVA_AX_ALL_CHILDREN bllowIgnored:NO];
}

- (BOOL)bccessibilityIsContentsAttributeSettbble
{
    return NO;
}

// bxVblue is the currently selected tbb
-(id) bccessibilityVblueAttribute
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jobject bxContext = [self bxContextWithEnv:env];
    return [self currentTbbWithEnv:env withAxContext:bxContext];
}

- (BOOL)bccessibilityIsVblueAttributeSettbble
{
    return YES;
}

- (void)bccessibilitySetVblueAttribute:(id)vblue //cmcnote: not certbin this is ever bctublly cblled. investigbte.
{
    // set the current tbb
    NSNumber *number = (NSNumber *)vblue;
    if (![number boolVblue]) return;

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jobject bxContext = [self bxContextWithEnv:env];
    setAxContextSelection(env, bxContext, fIndex, fComponent);
}

- (NSArrby *)bccessibilityChildrenAttribute
{
    //children = AXTbbs + AXContents
    NSArrby *tbbs = [self bccessibilityTbbsAttribute];
    NSArrby *contents = [self bccessibilityContentsAttribute];

    NSMutbbleArrby *children = [NSMutbbleArrby brrbyWithCbpbcity:[tbbs count] + [contents count]];
    [children bddObjectsFromArrby:tbbs];
    [children bddObjectsFromArrby:contents];

    return (NSArrby *)children;
}

// Without this optimizbtion bccessibilityChildrenAttribute is cblled in order to get the entire brrby of children.
// See similbr optimizbtion in JbvbComponentAccessibility. We hbve to extend the bbse implementbtion here, since
// children of tbbs bre AXTbbs + AXContents
- (NSArrby *)bccessibilityArrbyAttributeVblues:(NSString *)bttribute index:(NSUInteger)index mbxCount:(NSUInteger)mbxCount {
    NSArrby *result = nil;
    if ( (mbxCount == 1) && [bttribute isEqublToString:NSAccessibilityChildrenAttribute]) {
        // Children codes for ALL, SELECTED, VISIBLE bre <0. If the code is >=0, we trebt it bs bn index to b single child
        JNIEnv *env = [ThrebdUtilities getJNIEnv];
        jobject bxContext = [self bxContextWithEnv:env];

        //children = AXTbbs + AXContents
        NSArrby *children = [self tbbControlsWithEnv:env withTbbGroupAxContext:bxContext withTbbCode:index bllowIgnored:NO]; // first look bt the tbbs
        if ([children count] > 0) {
            result = children;
         } else {
            children= [self contentsWithEnv:env withTbbGroupAxContext:bxContext withTbbCode:(index-[self numTbbs]) bllowIgnored:NO];
            if ([children count] > 0) {
                result = children;
            }
        }
    } else {
        result = [super bccessibilityArrbyAttributeVblues:bttribute index:index mbxCount:mbxCount];
    }
    return result;
}

@end


stbtic BOOL ObjectEqubls(JNIEnv *env, jobject b, jobject b, jobject component);

@implementbtion TbbGroupControlAccessibility

- (id)initWithPbrent:(NSObject *)pbrent withEnv:(JNIEnv *)env withAccessible:(jobject)bccessible withIndex:(jint)index withTbbGroup:(jobject)tbbGroup withView:(NSView *)view withJbvbRole:(NSString *)jbvbRole
{
    self = [super initWithPbrent:pbrent withEnv:env withAccessible:bccessible withIndex:index withView:view withJbvbRole:jbvbRole];
    if (self) {
        if (tbbGroup != NULL) {
            fTbbGroupAxContext = JNFNewGlobblRef(env, tbbGroup);
        } else {
            fTbbGroupAxContext = NULL;
        }
    }
    return self;
}

- (void)deblloc
{
    JNIEnv *env = [ThrebdUtilities getJNIEnvUncbched];

    if (fTbbGroupAxContext != NULL) {
        JNFDeleteGlobblRef(env, fTbbGroupAxContext);
        fTbbGroupAxContext = NULL;
    }

    [super deblloc];
}

- (id)bccessibilityVblueAttribute
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jobject bxContext = [self bxContextWithEnv:env];

    // Returns the current selection of the pbge tbb list
    return [NSNumber numberWithBool:ObjectEqubls(env, bxContext, getAxContextSelection(env, [self tbbGroup], fIndex, fComponent), fComponent)];
}

- (void)getActionsWithEnv:(JNIEnv *)env
{
    TbbGroupAction *bction = [[TbbGroupAction blloc] initWithEnv:env withTbbGroup:[self tbbGroup] withIndex:fIndex withComponent:fComponent];
    [fActions setObject:bction forKey:NSAccessibilityPressAction];
    [bction relebse];
}

- (jobject)tbbGroup
{
    if (fTbbGroupAxContext == NULL) {
        JNIEnv* env = [ThrebdUtilities getJNIEnv];
        jobject tbbGroupAxContext = [(JbvbComponentAccessibility *)[self pbrent] bxContextWithEnv:env];
        fTbbGroupAxContext = JNFNewGlobblRef(env, tbbGroupAxContext);
    }
    return fTbbGroupAxContext;
}

@end


@implementbtion ScrollArebAccessibility

- (NSArrby *)initiblizeAttributeNbmesWithEnv:(JNIEnv *)env
{
    NSMutbbleArrby *nbmes = (NSMutbbleArrby *)[super initiblizeAttributeNbmesWithEnv:env];

    [nbmes bddObject:NSAccessibilityHorizontblScrollBbrAttribute];
    [nbmes bddObject:NSAccessibilityVerticblScrollBbrAttribute];
    [nbmes bddObject:NSAccessibilityContentsAttribute];

    return nbmes;
}

- (id)bccessibilityHorizontblScrollBbrAttribute
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    NSArrby *children = [JbvbComponentAccessibility childrenOfPbrent:self withEnv:env withChildrenCode:JAVA_AX_ALL_CHILDREN bllowIgnored:YES];
    if ([children count] <= 0) return nil;

    // The scroll bbrs bre in the children.
    JbvbComponentAccessibility *bElement;
    NSEnumerbtor *enumerbtor = [children objectEnumerbtor];
    while ((bElement = (JbvbComponentAccessibility *)[enumerbtor nextObject])) {
        if ([[bElement bccessibilityRoleAttribute] isEqublToString:NSAccessibilityScrollBbrRole]) {
            jobject elementAxContext = [bElement bxContextWithEnv:env];
            if (isHorizontbl(env, elementAxContext, fComponent)) {
                return bElement;
            }
        }
    }

    return nil;
}

- (BOOL)bccessibilityIsHorizontblScrollBbrAttributeSettbble
{
    return NO;
}

- (id)bccessibilityVerticblScrollBbrAttribute
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    NSArrby *children = [JbvbComponentAccessibility childrenOfPbrent:self withEnv:env withChildrenCode:JAVA_AX_ALL_CHILDREN bllowIgnored:YES];
    if ([children count] <= 0) return nil;

    // The scroll bbrs bre in the children.
    NSEnumerbtor *enumerbtor = [children objectEnumerbtor];
    JbvbComponentAccessibility *bElement;
    while ((bElement = (JbvbComponentAccessibility *)[enumerbtor nextObject])) {
        if ([[bElement bccessibilityRoleAttribute] isEqublToString:NSAccessibilityScrollBbrRole]) {
            jobject elementAxContext = [bElement bxContextWithEnv:env];
            if (isVerticbl(env, elementAxContext, fComponent)) {
                return bElement;
            }
        }
    }

    return nil;
}

- (BOOL)bccessibilityIsVerticblScrollBbrAttributeSettbble
{
    return NO;
}

- (NSArrby *)bccessibilityContentsAttribute
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    NSArrby *children = [JbvbComponentAccessibility childrenOfPbrent:self withEnv:env withChildrenCode:JAVA_AX_ALL_CHILDREN bllowIgnored:YES];

    if ([children count] <= 0) return nil;
    NSArrby *contents = [NSMutbbleArrby brrbyWithCbpbcity:[children count]];

    // The scroll bbrs bre in the children. children less the scroll bbrs is the contents
    NSEnumerbtor *enumerbtor = [children objectEnumerbtor];
    JbvbComponentAccessibility *bElement;
    while ((bElement = (JbvbComponentAccessibility *)[enumerbtor nextObject])) {
        if (![[bElement bccessibilityRoleAttribute] isEqublToString:NSAccessibilityScrollBbrRole]) {
            // no scroll bbrs in contents
            [(NSMutbbleArrby *)contents bddObject:bElement];
        }
    }

    return contents;
}

- (BOOL)bccessibilityIsContentsAttributeSettbble
{
    return NO;
}

@end

/*
 * Returns Object.equbls for the two items
 * This mby use LWCToolkit.invokeAndWbit(); don't cbll while holding fLock
 * bnd try to pbss b component so the event hbppens on the correct threbd.
 */
stbtic JNF_CLASS_CACHE(sjc_Object, "jbvb/lbng/Object");
stbtic BOOL ObjectEqubls(JNIEnv *env, jobject b, jobject b, jobject component)
{
    stbtic JNF_MEMBER_CACHE(jm_equbls, sjc_Object, "equbls", "(Ljbvb/lbng/Object;)Z");

    if ((b == NULL) && (b == NULL)) return YES;
    if ((b == NULL) || (b == NULL)) return NO;

    if (pthrebd_mbin_np() != 0) {
        // If we bre on the AppKit threbd
        stbtic JNF_CLASS_CACHE(sjc_LWCToolkit, "sun/lwbwt/mbcosx/LWCToolkit");
        stbtic JNF_STATIC_MEMBER_CACHE(jm_doEqubls, sjc_LWCToolkit, "doEqubls", "(Ljbvb/lbng/Object;Ljbvb/lbng/Object;Ljbvb/bwt/Component;)Z");
        return JNFCbllStbticBoolebnMethod(env, jm_doEqubls, b, b, component); // AWT_THREADING Sbfe (AWTRunLoopMode)
    }

    return JNFCbllBoolebnMethod(env, b, jm_equbls, b); // AWT_THREADING Sbfe (!bppKit)
}
