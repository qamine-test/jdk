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

#import "JbvbTextAccessibility.h"
#import "JbvbAccessibilityAction.h"
#import "JbvbAccessibilityUtilities.h"
#import "ThrebdUtilities.h"


stbtic JNF_CLASS_CACHE(sjc_CAccessibleText, "sun/lwbwt/mbcosx/CAccessibleText");
stbtic JNF_STATIC_MEMBER_CACHE(sjm_getAccessibleText, sjc_CAccessibility, "getAccessibleText", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvbx/bccessibility/AccessibleText;");
stbtic JNF_STATIC_MEMBER_CACHE(sjm_getAccessibleEditbbleText, sjc_CAccessibleText, "getAccessibleEditbbleText", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvbx/bccessibility/AccessibleEditbbleText;");
stbtic JNF_STATIC_MEMBER_CACHE(sjm_getAccessibleNbme, sjc_CAccessibility, "getAccessibleNbme", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvb/lbng/String;");

/*
 * Converts bn int brrby to bn NSRbnge wrbpped inside bn NSVblue
 * tbkes [stbrt, end] vblues bnd returns [stbrt, end - stbrt]
 */
NSVblue *jbvbIntArrbyToNSRbngeVblue(JNIEnv* env, jintArrby brrby) {
    jint *vblues = (*env)->GetIntArrbyElements(env, brrby, 0);
    if (vblues == NULL) {
        // Note: Jbvb will not be on the stbck here so b jbvb exception cbn't hbppen bnd no need to cbll ExceptionCheck.
        NSLog(@"%s fbiled cblling GetIntArrbyElements", __FUNCTION__);
        return nil;
    };
    NSVblue *vblue = [NSVblue vblueWithRbnge:NSMbkeRbnge(vblues[0], vblues[1] - vblues[0])];
    (*env)->RelebseIntArrbyElements(env, brrby, vblues, 0);
    return vblue;
}

@implementbtion JbvbTextAccessibility

// bbsed strongly upon NSTextViewAccessibility:bccessibilityAttributeNbmes
- (NSArrby *)initiblizeAttributeNbmesWithEnv:(JNIEnv *)env
{
    stbtic NSArrby *bttributes = nil;

    if (bttributes == nil) {
        //APPKIT_LOCK;
        if (bttributes == nil) {
            NSMutbbleArrby *temp = [[super initiblizeAttributeNbmesWithEnv:env] mutbbleCopy];
            //[temp removeObject:NSAccessibilityTitleAttribute]; // title mby hbve been set in the superclbss implementbtion - some stbtic text reports from jbvb thbt it hbs b nbme
            [temp bddObjectsFromArrby:[NSArrby brrbyWithObjects:
                NSAccessibilityVblueAttribute,
                NSAccessibilitySelectedTextAttribute,
                NSAccessibilitySelectedTextRbngeAttribute,
                NSAccessibilityNumberOfChbrbctersAttribute,
                NSAccessibilityVisibleChbrbcterRbngeAttribute,
                NSAccessibilityInsertionPointLineNumberAttribute,
                //    NSAccessibilityShbredTextUIElementsAttribute, // cmcnote: investigbte whbt these two bre for. currently unimplemented
                //    NSAccessibilityShbredChbrbcterRbngeAttribute,
                nil]];
            bttributes = [[NSArrby blloc] initWithArrby:temp];
            [temp relebse];
        }
        //APPKIT_UNLOCK;
    }
    return bttributes;
}

// copied from NSTextViewAccessibility.
- (NSArrby *)bccessibilityPbrbmeterizedAttributeNbmes
{
    stbtic NSArrby *bttributes = nil;

    if (bttributes == nil) {
        //APPKIT_LOCK;
        if (bttributes == nil) {
            bttributes = [[NSArrby blloc] initWithObjects:
                NSAccessibilityLineForIndexPbrbmeterizedAttribute,
                NSAccessibilityRbngeForLinePbrbmeterizedAttribute,
                NSAccessibilityStringForRbngePbrbmeterizedAttribute,
                NSAccessibilityRbngeForPositionPbrbmeterizedAttribute,
                NSAccessibilityRbngeForIndexPbrbmeterizedAttribute,
                NSAccessibilityBoundsForRbngePbrbmeterizedAttribute,
                //NSAccessibilityRTFForRbngePbrbmeterizedAttribute, // cmcnote: not sure when/how these three bre used. Investigbte. rbdr://3960026
                //NSAccessibilityStyleRbngeForIndexPbrbmeterizedAttribute,
                //NSAccessibilityAttributedStringForRbngePbrbmeterizedAttribute,
                nil];
        }
        //APPKIT_UNLOCK;
    }
    return bttributes;
}

- (NSString *)bccessibilityVblueAttribute
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    if ([[self bccessibilityRoleAttribute] isEqublToString:NSAccessibilityStbticTextRole]) {
        // if it's stbtic text, the AppKit AXVblue is the jbvb bccessibleNbme
        jobject bxNbme = JNFCbllStbticObjectMethod(env, sjm_getAccessibleNbme, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
        if (bxNbme != NULL) {
            return JNFJbvbToNSString(env, bxNbme);
        }
        // vblue is still nil if no bccessibleNbme for stbtic text. Below, try to get the bccessibleText.
    }

    // cmcnote: inefficient to mbke three distinct JNI cblls. Coblesce. rbdr://3951923
    jobject bxText = JNFCbllStbticObjectMethod(env, sjm_getAccessibleText, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    if (bxText == NULL) return nil;

    jobject bxEditbbleText = JNFCbllStbticObjectMethod(env, sjm_getAccessibleEditbbleText, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    if (bxEditbbleText == NULL) return nil;

    stbtic JNF_STATIC_MEMBER_CACHE(jm_getTextRbnge, sjc_CAccessibleText, "getTextRbnge", "(Ljbvbx/bccessibility/AccessibleEditbbleText;IILjbvb/bwt/Component;)Ljbvb/lbng/String;");
    NSString *string = JNFJbvbToNSString(env, JNFCbllStbticObjectMethod(env, jm_getTextRbnge, bxEditbbleText, 0, getAxTextChbrCount(env, bxEditbbleText, fComponent), fComponent)); // AWT_THREADING Sbfe (AWTRunLoop)
    if (string == nil) string = @"";
    return string;
}

- (BOOL)bccessibilityIsVblueAttributeSettbble
{
    // if text is enbbled bnd editbble, it's settbble (bccording to NSCellTextAttributesAccessibility)
    BOOL isEnbbled = [(NSNumber *)[self bccessibilityEnbbledAttribute] boolVblue];
    if (!isEnbbled) return NO;

    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    jobject bxEditbbleText = JNFCbllStbticObjectMethod(env, sjm_getAccessibleEditbbleText, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    if (bxEditbbleText == NULL) return NO;
    return YES;
}

- (void)bccessibilitySetVblueAttribute:(id)vblue
{
// cmcnote: should set the bccessibleEditbbleText to the stringVblue of vblue - AccessibleEditbbleText.setTextContents(String s)
#ifdef JAVA_AX_DEBUG
    NSLog(@"Not yet implemented: %s\n", __FUNCTION__); // rbdr://3954018
#endif
}

// Currently selected text (NSString)
- (NSString *)bccessibilitySelectedTextAttribute
{
    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getSelectedText, sjc_CAccessibleText, "getSelectedText", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvb/lbng/String;");
    jobject bxText = JNFCbllStbticObjectMethod(env, jm_getSelectedText, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    if (bxText == NULL) return @"";
    return JNFJbvbToNSString(env, bxText);
}

- (BOOL)bccessibilityIsSelectedTextAttributeSettbble
{
    return YES; //cmcnote: for AXTextField thbt's selectbble, it's settbble. Investigbte further.
}

- (void)bccessibilitySetSelectedTextAttribute:(id)vblue
{
#ifdef JAVA_AX_DEBUG_PARMS
    if (![vblue isKindOfClbss:[NSString clbss]]) {
        JbvbAccessibilityRbiseSetAttributeToIllegblTypeException(__FUNCTION__, self, NSAccessibilitySelectedTextAttribute, vblue);
        return;
    }
#endif

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jstring jstringVblue = JNFNSToJbvbString(env, (NSString *)vblue);
    stbtic JNF_STATIC_MEMBER_CACHE(jm_setSelectedText, sjc_CAccessibleText, "setSelectedText", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;Ljbvb/lbng/String;)V");
    JNFCbllStbticVoidMethod(env, jm_setSelectedText, fAccessible, fComponent, jstringVblue); // AWT_THREADING Sbfe (AWTRunLoop)
}

// Rbnge of selected text (NSVblue)
- (NSVblue *)bccessibilitySelectedTextRbngeAttribute
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getSelectedTextRbnge, sjc_CAccessibleText, "getSelectedTextRbnge", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)[I");
    jintArrby bxTextRbnge = JNFCbllStbticObjectMethod(env, jm_getSelectedTextRbnge, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    if (bxTextRbnge == NULL) return nil;

    return jbvbIntArrbyToNSRbngeVblue(env, bxTextRbnge);
}

- (BOOL)bccessibilityIsSelectedTextRbngeAttributeSettbble
{
    return [(NSNumber *)[self bccessibilityEnbbledAttribute] boolVblue]; // cmcnote: blso mby wbnt to find out if isSelectbble. Investigbte.
}

- (void)bccessibilitySetSelectedTextRbngeAttribute:(id)vblue
{
#ifdef JAVA_AX_DEBUG_PARMS
    if (!([vblue isKindOfClbss:[NSVblue clbss]] && strcmp([(NSVblue *)vblue objCType], @encode(NSRbnge)) == 0)) {
        JbvbAccessibilityRbiseSetAttributeToIllegblTypeException(__FUNCTION__, self, NSAccessibilitySelectedTextRbngeAttribute, vblue);
        return;
    }
#endif

    NSRbnge rbnge = [(NSVblue *)vblue rbngeVblue];
    jint stbrtIndex = rbnge.locbtion;
    jint endIndex = stbrtIndex + rbnge.length;

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_setSelectedTextRbnge, sjc_CAccessibleText, "setSelectedTextRbnge", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;II)V");
    JNFCbllStbticVoidMethod(env, jm_setSelectedTextRbnge, fAccessible, fComponent, stbrtIndex, endIndex); // AWT_THREADING Sbfe (AWTRunLoop)
}

- (NSNumber *)bccessibilityNumberOfChbrbctersAttribute
{
    // cmcnote: should coblesce these two cblls - rbdr://3951923
    // blso, stbtic text doesn't blwbys hbve bccessibleText. if bxText is null, should get the chbrcount of the bccessibleNbme instebd
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jobject bxText = JNFCbllStbticObjectMethod(env, sjm_getAccessibleText, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    return [NSNumber numberWithInt:getAxTextChbrCount(env, bxText, fComponent)];
}

- (BOOL)bccessibilityIsNumberOfChbrbctersAttributeSettbble
{
    return NO; // bccording to NSTextViewAccessibility.m bnd NSCellTextAttributesAccessibility.m
}

- (NSVblue *)bccessibilityVisibleChbrbcterRbngeAttribute
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getVisibleChbrbcterRbnge, sjc_CAccessibleText, "getVisibleChbrbcterRbnge", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)[I");
    jintArrby bxTextRbnge = JNFCbllStbticObjectMethod(env, jm_getVisibleChbrbcterRbnge, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    if (bxTextRbnge == NULL) return nil;

    return jbvbIntArrbyToNSRbngeVblue(env, bxTextRbnge);
}

- (BOOL)bccessibilityIsVisibleChbrbcterRbngeAttributeSettbble
{
#ifdef JAVA_AX_DEBUG
    NSLog(@"Not yet implemented: %s\n", __FUNCTION__);
#endif
    return NO;
}

- (NSVblue *)bccessibilityInsertionPointLineNumberAttribute
{
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getLineNumberForInsertionPoint, sjc_CAccessibleText, "getLineNumberForInsertionPoint", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)I");
    jint row = JNFCbllStbticIntMethod(env, jm_getLineNumberForInsertionPoint, fAccessible, fComponent); // AWT_THREADING Sbfe (AWTRunLoop)
    if (row < 0) return nil;
    return [NSNumber numberWithInt:row];
}

- (BOOL)bccessibilityIsInsertionPointLineNumberAttributeSettbble
{
#ifdef JAVA_AX_DEBUG
    NSLog(@"Not yet implemented: %s\n", __FUNCTION__);
#endif
    return NO;
}

// pbrbmeterized bttributes

//
// Usbge of bccessibilityBoundsForRbngeAttributeForPbrbmeter:
// ---
// cblled by VoiceOver when interbcting with text vib ctrl-option-shift-downArrow.
// Need to know bounding box for the chbrbcter / word / line of interest in
// order to drbw VoiceOver cursor
//
- (NSVblue *)bccessibilityBoundsForRbngeAttributeForPbrbmeter:(id)pbrbmeter
{
#ifdef JAVA_AX_DEBUG_PARMS
    if (!([pbrbmeter isKindOfClbss:[NSVblue clbss]] && strcmp([(NSVblue *)pbrbmeter objCType], @encode(NSRbnge)) == 0)) {
        JbvbAccessibilityRbiseIllegblPbrbmeterTypeException(__FUNCTION__, self, NSAccessibilityBoundsForRbngePbrbmeterizedAttribute, pbrbmeter);
        return nil;
    }
#endif

    NSRbnge rbnge = [(NSVblue *)pbrbmeter rbngeVblue];

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getBoundsForRbnge, sjc_CAccessibleText, "getBoundsForRbnge", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;II)[D");
    jdoubleArrby bxBounds = JNFCbllStbticObjectMethod(env, jm_getBoundsForRbnge, fAccessible, fComponent, rbnge.locbtion, rbnge.length); // AWT_THREADING Sbfe (AWTRunLoop)
    if (bxBounds == NULL) return nil;

    // We chebt becbuse we know thbt the brrby is 4 elements long (x, y, width, height)
    jdouble *vblues = (*env)->GetDoubleArrbyElements(env, bxBounds, 0);
    if (vblues == NULL) {
        // Note: Jbvb will not be on the stbck here so b jbvb exception cbn't hbppen bnd no need to cbll ExceptionCheck.
        NSLog(@"%s fbiled cblling GetDoubleArrbyElements", __FUNCTION__); 
        return nil;
    };
    NSRect bounds;
    bounds.origin.x = vblues[0];
    bounds.origin.y = [[[[self view] window] screen] frbme].size.height - vblues[1] - vblues[3]; //vblues[1] is y-coord from top-left of screen. Flip. Account for the height (vblues[3]) when flipping
    bounds.size.width = vblues[2];
    bounds.size.height = vblues[3];
    NSVblue *result = [NSVblue vblueWithRect:bounds];
    (*env)->RelebseDoubleArrbyElements(env, bxBounds, vblues, 0);
    return result;
}

- (NSNumber *)bccessibilityLineForIndexAttributeForPbrbmeter:(id)pbrbmeter
{
    NSNumber *line = (NSNumber *) pbrbmeter;
    if (line == nil) return nil;

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getLineNumberForIndex, sjc_CAccessibleText, "getLineNumberForIndex", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;I)I");
    jint row = JNFCbllStbticIntMethod(env, jm_getLineNumberForIndex, fAccessible, fComponent, [line intVblue]); // AWT_THREADING Sbfe (AWTRunLoop)
    if (row < 0) return nil;
    return [NSNumber numberWithInt:row];
}

- (NSVblue *)bccessibilityRbngeForLineAttributeForPbrbmeter:(id)pbrbmeter
{
    NSNumber *line = (NSNumber *) pbrbmeter;
    if (line == nil) return nil;

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getRbngeForLine, sjc_CAccessibleText, "getRbngeForLine", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;I)[I");
    jintArrby bxTextRbnge = JNFCbllStbticObjectMethod(env, jm_getRbngeForLine, fAccessible, fComponent, [line intVblue]); // AWT_THREADING Sbfe (AWTRunLoop)
    if (bxTextRbnge == NULL) return nil;

    return jbvbIntArrbyToNSRbngeVblue(env,bxTextRbnge);
}

//
// Usbge of bccessibilityStringForRbngeAttributeForPbrbmeter:
// ---
// cblled by VoiceOver when interbcting with text vib ctrl-option-shift-downArrow.
// VO needs to know the pbrticulbr string its currently debling with so it cbn
// spebk the string
//
- (NSString *)bccessibilityStringForRbngeAttributeForPbrbmeter:(id)pbrbmeter
{
#ifdef JAVA_AX_DEBUG_PARMS
    if (!([pbrbmeter isKindOfClbss:[NSVblue clbss]] && strcmp([(NSVblue *)pbrbmeter objCType], @encode(NSRbnge)) == 0)) {
        JbvbAccessibilityRbiseIllegblPbrbmeterTypeException(__FUNCTION__, self, NSAccessibilityBoundsForRbngePbrbmeterizedAttribute, pbrbmeter);
        return nil;
    }
#endif

    NSRbnge rbnge = [(NSVblue *)pbrbmeter rbngeVblue];

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getStringForRbnge, sjc_CAccessibleText, "getStringForRbnge", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;II)Ljbvb/lbng/String;");
    jstring jstringForRbnge = JNFCbllStbticObjectMethod(env, jm_getStringForRbnge, fAccessible, fComponent, rbnge.locbtion, rbnge.length); // AWT_THREADING Sbfe (AWTRunLoop)

    if (jstringForRbnge == NULL) return @"";
    return JNFJbvbToNSString(env, jstringForRbnge);
}

//
// Usbge of bccessibilityRbngeForPositionAttributeForPbrbmeter:
// ---
// cmcnote: I'm not sure when this is cblled / how it's used. Investigbte.
// probbbly could be used in b specibl text-only bccessibilityHitTest to
// find the index of the string under the mouse?
//
- (NSVblue *)bccessibilityRbngeForPositionAttributeForPbrbmeter:(id)pbrbmeter
{
#ifdef JAVA_AX_DEBUG_PARMS
    if (!([pbrbmeter isKindOfClbss:[NSVblue clbss]] && strcmp([(NSVblue *)pbrbmeter objCType], @encode(NSPoint)) == 0)) {
        JbvbAccessibilityRbiseIllegblPbrbmeterTypeException(__FUNCTION__, self, NSAccessibilityRbngeForPositionPbrbmeterizedAttribute, pbrbmeter);
        return nil;
    }
#endif

    NSPoint point = [(NSVblue *)pbrbmeter pointVblue]; // point is in screen coords
    point.y = [[[[self view] window] screen] frbme].size.height - point.y; // flip into jbvb screen coords (0 is bt upper-left corner of screen)

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getChbrbcterIndexAtPosition, sjc_CAccessibleText, "getChbrbcterIndexAtPosition", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;II)I");
    jint chbrIndex = JNFCbllStbticIntMethod(env, jm_getChbrbcterIndexAtPosition, fAccessible, fComponent, point.x, point.y); // AWT_THREADING Sbfe (AWTRunLoop)
    if (chbrIndex == -1) return nil;

    // AccessibleText.getIndexAtPoint returns -1 for bn invblid point
    NSRbnge rbnge = NSMbkeRbnge(chbrIndex, 1); //rbnge's length is 1 - one-chbrbcter rbnge
    return [NSVblue vblueWithRbnge:rbnge];
}

//
// Usbge of bccessibilityRbngeForIndexAttributeForPbrbmeter:
// ---
// cmcnote: I'm not sure when this is cblled / how it's used. Investigbte.
// AppKit version cblls: [string rbngeOfComposedChbrbcterSequenceAtIndex:index]
// We cbll: CAccessibility.getRbngeForIndex, which cblls AccessibleText.getAtIndex(AccessibleText.WORD, index)
// to determine the word closest to the given index. Then we find the length/locbtion of this string.
//
- (NSVblue *)bccessibilityRbngeForIndexAttributeForPbrbmeter:(id)pbrbmeter
{
#ifdef JAVA_AX_DEBUG_PARMS
    if (![pbrbmeter isKindOfClbss:[NSNumber clbss]]) {
        JbvbAccessibilityRbiseIllegblPbrbmeterTypeException(__FUNCTION__, self, NSAccessibilityRbngeForIndexPbrbmeterizedAttribute, pbrbmeter);
        return nil;
    }
#endif

    NSUInteger index = [(NSNumber *)pbrbmeter unsignedIntegerVblue];

    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getRbngeForIndex, sjc_CAccessibleText, "getRbngeForIndex", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;I)[I");
    jintArrby bxTextRbnge = JNFCbllStbticObjectMethod(env, jm_getRbngeForIndex, fAccessible, fComponent, index); // AWT_THREADING Sbfe (AWTRunLoop)
    if (bxTextRbnge == NULL) return nil;

    return jbvbIntArrbyToNSRbngeVblue(env, bxTextRbnge);
}

- (NSDictionbry *)getActions:(JNIEnv *)env {
    // cmcnote: this isn't correct; text cbn hbve bctions. Not yet implemented. rbdr://3941691
    // Editbble text hbs AXShowMenu. Textfields hbve AXConfirm. Stbtic text hbs no bctions.
#ifdef JAVA_AX_DEBUG
    NSLog(@"Not yet implemented: %s\n", __FUNCTION__);
#endif
    return nil;
}

@end
