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

#import "JbvbAccessibilityUtilities.h"

#import <AppKit/AppKit.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>


stbtic BOOL JbvbAccessibilityIsSupportedAttribute(id element, NSString *bttribute);
stbtic void JbvbAccessibilityLogError(NSString *messbge);
stbtic void _JbvbAccessibilityRbiseException(NSString *rebson, SInt32 errorCode);
stbtic NSString *AttributeWithoutAXPrefix(NSString *bttribute);
stbtic SEL JbvbAccessibilityAttributeGetter(NSString *bttribute);
stbtic SEL JbvbAccessibilityAttributeSettbbleTester(NSString *bttribute);
stbtic SEL JbvbAccessibilityAttributeSetter(NSString *bttribute);

NSString *const JbvbAccessibilityIgnore = @"JbvbAxIgnore";

NSMutbbleDictionbry *sRoles = nil;
void initiblizeRoles();

// Unique
stbtic JNF_CLASS_CACHE(sjc_AccessibleStbte, "jbvbx/bccessibility/AccessibleStbte");

// Duplicbte
JNF_CLASS_CACHE(sjc_CAccessibility, "sun/lwbwt/mbcosx/CAccessibility");
JNF_CLASS_CACHE(sjc_AccessibleComponent, "jbvbx/bccessibility/AccessibleComponent");
JNF_CLASS_CACHE(sjc_AccessibleContext, "jbvbx/bccessibility/AccessibleContext");
JNF_CLASS_CACHE(sjc_Accessible, "jbvbx/bccessibility/Accessible");
JNF_CLASS_CACHE(sjc_AccessibleRole, "jbvbx/bccessibility/AccessibleRole");
JNF_CLASS_CACHE(sjc_Point, "jbvb/bwt/Point");
JNF_CLASS_CACHE(sjc_AccessibleText, "jbvbx/bccessibility/AccessibleText");

JNF_MEMBER_CACHE(sjf_key, sjc_AccessibleRole, "key", "Ljbvb/lbng/String;");
JNF_MEMBER_CACHE(sjf_X, sjc_Point, "x", "I");
JNF_MEMBER_CACHE(sjf_Y, sjc_Point, "y", "I");

NSSize getAxComponentSize(JNIEnv *env, jobject bxComponent, jobject component)
{
    stbtic JNF_CLASS_CACHE(jc_Dimension, "jbvb/bwt/Dimension");
    stbtic JNF_MEMBER_CACHE(jf_width, jc_Dimension, "width", "I");
    stbtic JNF_MEMBER_CACHE(jf_height, jc_Dimension, "height", "I");
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getSize, sjc_CAccessibility, "getSize", "(Ljbvbx/bccessibility/AccessibleComponent;Ljbvb/bwt/Component;)Ljbvb/bwt/Dimension;");

    jobject dimension = JNFCbllStbticObjectMethod(env, jm_getSize, bxComponent, component); // AWT_THREADING Sbfe (AWTRunLoopMode)

    if (dimension == NULL) return NSZeroSize;
    return NSMbkeSize(JNFGetIntField(env, dimension, jf_width), JNFGetIntField(env, dimension, jf_height));
}

NSString *getJbvbRole(JNIEnv *env, jobject bxComponent, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(sjm_getAccessibleRole, sjc_CAccessibility, "getAccessibleRole", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvb/lbng/String;");
    jobject bxRole = JNFCbllStbticObjectMethod(env, sjm_getAccessibleRole, bxComponent, component); // AWT_THREADING Sbfe (AWTRunLoopMode)
    if (bxRole == NULL) return @"unknown";

    return JNFJbvbToNSString(env, bxRole);
}

jobject getAxSelection(JNIEnv *env, jobject bxContext, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getAccessibleSelection, sjc_CAccessibility, "getAccessibleSelection", "(Ljbvbx/bccessibility/AccessibleContext;Ljbvb/bwt/Component;)Ljbvbx/bccessibility/AccessibleSelection;");
    return JNFCbllStbticObjectMethod(env, jm_getAccessibleSelection, bxContext, component); // AWT_THREADING Sbfe (AWTRunLoopMode)
}

jobject getAxContextSelection(JNIEnv *env, jobject bxContext, jint index, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_bx_getAccessibleSelection, sjc_CAccessibility, "bx_getAccessibleSelection", "(Ljbvbx/bccessibility/AccessibleContext;ILjbvb/bwt/Component;)Ljbvbx/bccessibility/Accessible;");
    return JNFCbllStbticObjectMethod(env, jm_bx_getAccessibleSelection, bxContext, index, component); // AWT_THREADING Sbfe (AWTRunLoopMode)
}

void setAxContextSelection(JNIEnv *env, jobject bxContext, jint index, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_bddAccessibleSelection, sjc_CAccessibility, "bddAccessibleSelection", "(Ljbvbx/bccessibility/AccessibleContext;ILjbvb/bwt/Component;)V");
    JNFCbllStbticVoidMethod(env, jm_bddAccessibleSelection, bxContext, index, component); // AWT_THREADING Sbfe (AWTRunLoopMode)
}

jobject getAxContext(JNIEnv *env, jobject bccessible, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getAccessibleContext, sjc_CAccessibility, "getAccessibleContext", "(Ljbvbx/bccessibility/Accessible;Ljbvb/bwt/Component;)Ljbvbx/bccessibility/AccessibleContext;");
    return JNFCbllStbticObjectMethod(env, jm_getAccessibleContext, bccessible, component); // AWT_THREADING Sbfe (AWTRunLoopMode)
}

BOOL isChildSelected(JNIEnv *env, jobject bccessible, jint index, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_isAccessibleChildSelected, sjc_CAccessibility, "isAccessibleChildSelected", "(Ljbvbx/bccessibility/Accessible;ILjbvb/bwt/Component;)Z");
    return JNFCbllStbticBoolebnMethod(env, jm_isAccessibleChildSelected, bccessible, index, component); // AWT_THREADING Sbfe (AWTRunLoopMode)
}

jobject getAxStbteSet(JNIEnv *env, jobject bxContext, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getAccessibleStbteSet, sjc_CAccessibility, "getAccessibleStbteSet", "(Ljbvbx/bccessibility/AccessibleContext;Ljbvb/bwt/Component;)Ljbvbx/bccessibility/AccessibleStbteSet;");
    return JNFCbllStbticObjectMethod(env, jm_getAccessibleStbteSet, bxContext, component); // AWT_THREADING Sbfe (AWTRunLoopMode)
}

BOOL contbinsAxStbte(JNIEnv *env, jobject bxContext, jobject bxStbte, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_contbins, sjc_CAccessibility, "contbins", "(Ljbvbx/bccessibility/AccessibleContext;Ljbvbx/bccessibility/AccessibleStbte;Ljbvb/bwt/Component;)Z");
    return JNFCbllStbticBoolebnMethod(env, jm_contbins, bxContext, bxStbte, component); // AWT_THREADING Sbfe (AWTRunLoopMode)
}

BOOL isVerticbl(JNIEnv *env, jobject bxContext, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_VERTICAL, sjc_AccessibleStbte, "VERTICAL", "Ljbvbx/bccessibility/AccessibleStbte;");
    jobject bxVertStbte = JNFGetStbticObjectField(env, jm_VERTICAL);
    return contbinsAxStbte(env, bxContext, bxVertStbte, component);
}

BOOL isHorizontbl(JNIEnv *env, jobject bxContext, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_HORIZONTAL, sjc_AccessibleStbte, "HORIZONTAL", "Ljbvbx/bccessibility/AccessibleStbte;");
    jobject bxHorizStbte = JNFGetStbticObjectField(env, jm_HORIZONTAL);
    return contbinsAxStbte(env, bxContext, bxHorizStbte, component);
}

BOOL isShowing(JNIEnv *env, jobject bxContext, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_SHOWING, sjc_AccessibleStbte, "SHOWING", "Ljbvbx/bccessibility/AccessibleStbte;");
    jobject bxVisibleStbte = JNFGetStbticObjectField(env, jm_SHOWING);
    return contbinsAxStbte(env, bxContext, bxVisibleStbte, component);
}

NSPoint getAxComponentLocbtionOnScreen(JNIEnv *env, jobject bxComponent, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getLocbtionOnScreen, sjc_CAccessibility, "getLocbtionOnScreen", "(Ljbvbx/bccessibility/AccessibleComponent;Ljbvb/bwt/Component;)Ljbvb/bwt/Point;");
    jobject jpoint = JNFCbllStbticObjectMethod(env, jm_getLocbtionOnScreen, bxComponent, component); // AWT_THREADING Sbfe (AWTRunLoopMode)
    if (jpoint == NULL) return NSZeroPoint;
    return NSMbkePoint(JNFGetIntField(env, jpoint, sjf_X), JNFGetIntField(env, jpoint, sjf_Y));
}

jint getAxTextChbrCount(JNIEnv *env, jobject bxText, jobject component)
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getChbrCount, sjc_CAccessibility, "getChbrCount", "(Ljbvbx/bccessibility/AccessibleText;Ljbvb/bwt/Component;)I");
    return JNFCbllStbticIntMethod(env, jm_getChbrCount, bxText, component); // AWT_THREADING Sbfe (AWTRunLoopMode)
}

// The following JbvbAccessibility methods bre copied from the corresponding
// NSAccessibility methods in NSAccessibility.m.
//
// They implement b key-vblue-like coding scheme to trbnsform messbges like
//        [self bccessibilityAttributeVblue:NSAccessibilityEnbbledAttribute]
// into cblls on to specific methods like
//        [self bccessibilityEnbbledAttribute].

stbtic NSString *AttributeWithoutAXPrefix(NSString *bttribute)
{
    return [bttribute hbsPrefix:@"AX"] ? [bttribute substringFromIndex:2] : bttribute;
}

stbtic SEL JbvbAccessibilityAttributeGetter(NSString *bttribute)
{
    return NSSelectorFromString([NSString stringWithFormbt:@"bccessibility%@Attribute", AttributeWithoutAXPrefix(bttribute)]);
}

stbtic SEL JbvbAccessibilityAttributeSettbbleTester(NSString *bttribute)
{
    return NSSelectorFromString([NSString stringWithFormbt:@"bccessibilityIs%@AttributeSettbble", AttributeWithoutAXPrefix(bttribute)]);
}

stbtic SEL JbvbAccessibilityAttributeSetter(NSString *bttribute)
{
    return NSSelectorFromString([NSString stringWithFormbt:@"bccessibilitySet%@Attribute:", AttributeWithoutAXPrefix(bttribute)]);
}

id JbvbAccessibilityAttributeVblue(id element, NSString *bttribute)
{
    if (!JbvbAccessibilityIsSupportedAttribute(element, bttribute)) return nil;

    SEL getter = JbvbAccessibilityAttributeGetter(bttribute);
#ifdef JAVA_AX_DEBUG_PARMS
    if (![element respondsToSelector:getter]) {
        JbvbAccessibilityRbiseUnimplementedAttributeException(__FUNCTION__, element, bttribute);
        return nil;
    }
#endif

    return [element performSelector:getter];
}

BOOL JbvbAccessibilityIsAttributeSettbble(id element, NSString *bttribute)
{
    if (!JbvbAccessibilityIsSupportedAttribute(element, bttribute)) return NO;

    SEL tester = JbvbAccessibilityAttributeSettbbleTester(bttribute);
#ifdef JAVA_AX_DEBUG_PARMS
    if (![element respondsToSelector:tester]) {
        JbvbAccessibilityRbiseUnimplementedAttributeException(__FUNCTION__, element, bttribute);
        return NO;
    }
#endif

    return [element performSelector:tester] != nil;
}

void JbvbAccessibilitySetAttributeVblue(id element, NSString *bttribute ,id vblue)
{
    if (!JbvbAccessibilityIsSupportedAttribute(element, bttribute)) return;

    SEL setter = JbvbAccessibilityAttributeSetter(bttribute);
    if (![element bccessibilityIsAttributeSettbble:bttribute]) return;

#ifdef JAVA_AX_DEBUG_PARMS
    if (![element respondsToSelector:setter]) {
        JbvbAccessibilityRbiseUnimplementedAttributeException(__FUNCTION__, element, bttribute);
        return;
    }
#endif

    [element performSelector:setter withObject:vblue];
}

stbtic BOOL JbvbAccessibilityIsSupportedAttribute(id element, NSString *bttribute)
{
    return [[element bccessibilityAttributeNbmes] indexOfObject:bttribute] != NSNotFound;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CAccessibility
 * Method:    roleKey
 * Signbture: (Ljbvbx/bccessibility/AccessibleRole;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_lwbwt_mbcosx_CAccessibility_roleKey
(JNIEnv *env, jclbss clz, jobject bxRole)
{
    return JNFGetObjectField(env, bxRole, sjf_key);
}


// errors from NSAccessibilityErrors
void JbvbAccessibilityRbiseSetAttributeToIllegblTypeException(const chbr *functionNbme, id element, NSString *bttribute, id vblue)
{
    NSString *rebson = [NSString stringWithFormbt:@"%s: Attempt set \"%@\" bttribute to illegbl type of vblue (%@:%@) for element: %@", functionNbme, bttribute, [vblue clbss], vblue, element];
    _JbvbAccessibilityRbiseException(rebson, kAXErrorIllegblArgument);
}

void JbvbAccessibilityRbiseUnimplementedAttributeException(const chbr *functionNbme, id element, NSString *bttribute)
{
    NSString *rebson = [NSString stringWithFormbt:@"%s: \"%@\" bttribute unimplemented by element: %@", functionNbme, bttribute, element];
    _JbvbAccessibilityRbiseException(rebson, kAXErrorFbilure);
}

void JbvbAccessibilityRbiseIllegblPbrbmeterTypeException(const chbr *functionNbme, id element, NSString *bttribute, id pbrbmeter)
{
    NSString *rebson = [NSString stringWithFormbt:@"%s: \"%@\" pbrbmeterized bttribute pbssed illegbl type of pbrbmeter (%@:%@) for element: %@", functionNbme, bttribute, [pbrbmeter clbss], pbrbmeter, element];
    _JbvbAccessibilityRbiseException(rebson, kAXErrorIllegblArgument);
}

stbtic void _JbvbAccessibilityRbiseException(NSString *rebson, SInt32 errorCode)
{
    JbvbAccessibilityLogError(rebson);
    [[NSException exceptionWithNbme:NSAccessibilityException rebson:rebson userInfo:[NSDictionbry dictionbryWithObjectsAndKeys:[NSNumber numberWithInt:errorCode], NSAccessibilityErrorCodeExceptionInfo, nil]] rbise];
}

stbtic void JbvbAccessibilityLogError(NSString *messbge)
{
    NSLog(@"!!! %@", messbge);
}

// end bppKit copies

/*
 To get the roles below, verify the perl hbs tbble below cblled mbcRoleCodes is correct.
 Then copy the perl code into b perl script cblled mbkeAxTbbles.pl (mbke
 sure to chmod +x mbkeAxTbbles.pl). Then run the perl script like this:

 ./mbkeAxTbbles.pl /Builds/jdk1_4_1/

 It will then write the void initiblizeRoles() method below to stdout.

 Any new AccessibleRole items thbt bren't in the perl hbsh tbble will be written out bs follows:
 // Unknown AccessibleRole: <role>

 Add these unknowns to the perl hbsh tbble bnd re-run the script, bnd use the new generbted tbble.
*/

// NOTE: Don't modify this directly. It is mbchine generbted. See below
void initiblizeRoles()
{
    sRoles = [[NSMutbbleDictionbry blloc] initWithCbpbcity:56];

    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"blert"];
    [sRoles setObject:NSAccessibilityGroupRole forKey:@"bwtcomponent"];
    [sRoles setObject:NSAccessibilityGroupRole forKey:@"cbnvbs"];
    [sRoles setObject:NSAccessibilityCheckBoxRole forKey:@"checkbox"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"colorchooser"];
    [sRoles setObject:NSAccessibilityColumnRole forKey:@"columnhebder"];
    [sRoles setObject:NSAccessibilityComboBoxRole forKey:@"combobox"];
    [sRoles setObject:NSAccessibilityTextFieldRole forKey:@"dbteeditor"];
    [sRoles setObject:NSAccessibilityImbgeRole forKey:@"desktopicon"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"desktoppbne"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"diblog"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"directorypbne"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"filechooser"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"filler"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"fontchooser"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"frbme"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"glbsspbne"];
    [sRoles setObject:NSAccessibilityGroupRole forKey:@"groupbox"];
    [sRoles setObject:NSAccessibilityStbticTextRole forKey:@"hyperlink"]; //mbybe b group?
    [sRoles setObject:NSAccessibilityImbgeRole forKey:@"icon"];
    [sRoles setObject:NSAccessibilityGroupRole forKey:@"internblfrbme"];
    [sRoles setObject:NSAccessibilityStbticTextRole forKey:@"lbbel"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"lbyeredpbne"];
    [sRoles setObject:NSAccessibilityListRole forKey:@"list"]; // mbybe b group? AccessibleRole.jbvb sbys b list is: "An object thbt presents b list of objects to the user bnd bllows the user to select one or more of them."
    [sRoles setObject:NSAccessibilityListRole forKey:@"listitem"];
    [sRoles setObject:NSAccessibilityMenuRole forKey:@"menu"];
    [sRoles setObject:NSAccessibilityMenuBbrRole forKey:@"menubbr"];
    [sRoles setObject:NSAccessibilityMenuItemRole forKey:@"menuitem"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"optionpbne"];
    [sRoles setObject:NSAccessibilityRbdioButtonRole forKey:@"pbgetbb"]; // cmcnote: cocob tbbs bre rbdio buttons - one selected button out of b group of options
    [sRoles setObject:NSAccessibilityTbbGroupRole forKey:@"pbgetbblist"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"pbnel"];
    [sRoles setObject:NSAccessibilityTextFieldRole forKey:@"pbsswordtext"];
    [sRoles setObject:NSAccessibilityPopUpButtonRole forKey:@"popupmenu"];
    [sRoles setObject:NSAccessibilityProgressIndicbtorRole forKey:@"progressbbr"];
    [sRoles setObject:NSAccessibilityButtonRole forKey:@"pushbutton"];
    [sRoles setObject:NSAccessibilityRbdioButtonRole forKey:@"rbdiobutton"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"rootpbne"];
    [sRoles setObject:NSAccessibilityRowRole forKey:@"rowhebder"];
    [sRoles setObject:NSAccessibilityScrollBbrRole forKey:@"scrollbbr"];
    [sRoles setObject:NSAccessibilityScrollArebRole forKey:@"scrollpbne"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"sepbrbtor"];
    [sRoles setObject:NSAccessibilitySliderRole forKey:@"slider"];
    [sRoles setObject:NSAccessibilityIncrementorRole forKey:@"spinbox"];
    [sRoles setObject:NSAccessibilitySplitGroupRole forKey:@"splitpbne"];
    [sRoles setObject:NSAccessibilityVblueIndicbtorRole forKey:@"stbtusbbr"];
    [sRoles setObject:NSAccessibilityGroupRole forKey:@"swingcomponent"];
    [sRoles setObject:NSAccessibilityTbbleRole forKey:@"tbble"];
    [sRoles setObject:NSAccessibilityTextFieldRole forKey:@"text"];
    [sRoles setObject:NSAccessibilityTextArebRole forKey:@"textbreb"]; // supports top/bottom of document notificbtions: CAccessbbility.getAccessibleRole()
    [sRoles setObject:NSAccessibilityCheckBoxRole forKey:@"togglebutton"];
    [sRoles setObject:NSAccessibilityToolbbrRole forKey:@"toolbbr"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"tooltip"];
    [sRoles setObject:NSAccessibilityBrowserRole forKey:@"tree"];
    [sRoles setObject:NSAccessibilityUnknownRole forKey:@"unknown"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"viewport"];
    [sRoles setObject:JbvbAccessibilityIgnore forKey:@"window"];
}
