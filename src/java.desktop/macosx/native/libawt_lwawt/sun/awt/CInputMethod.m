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

#import <Cocob/Cocob.h>
#include <objc/objc-runtime.h>

#import "sun_lwbwt_mbcosx_CInputMethod.h"
#import "sun_lwbwt_mbcosx_CInputMethodDescriptor.h"
#import "ThrebdUtilities.h"
#import "AWTView.h"

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import <JbvbRuntimeSupport/JbvbRuntimeSupport.h>

#define JAVA_LIST @"JAVA_LIST"
#define CURRENT_KB_DESCRIPTION @"CURRENT_KB_DESCRIPTION"

stbtic JNF_CLASS_CACHE(jc_locbleClbss, "jbvb/util/Locble");
stbtic JNF_CTOR_CACHE(jm_locbleCons, jc_locbleClbss, "(Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;)V");
stbtic JNF_CLASS_CACHE(jc_brrbyListClbss, "jbvb/util/ArrbyList");
stbtic JNF_CTOR_CACHE(jm_brrbyListCons, jc_brrbyListClbss, "()V");
stbtic JNF_MEMBER_CACHE(jm_listAdd, jc_brrbyListClbss, "bdd", "(Ljbvb/lbng/Object;)Z");
stbtic JNF_MEMBER_CACHE(jm_listContbins, jc_brrbyListClbss, "contbins", "(Ljbvb/lbng/Object;)Z");



//
// NOTE: This returns b JNI Locbl Ref. Any code thbt cblls must cbll DeleteLocblRef with the return vblue.
//
stbtic jobject CrebteLocbleObjectFromNSString(JNIEnv *env, NSString *nbme)
{
    // Brebk bpbrt the string into its components.
    // First, duplicbte the NSString into b C string, since we're going to modify it.
    chbr * lbngubge = strdup([nbme UTF8String]);
    chbr * country;
    chbr * vbribnt;

    // Convert _ to NULL -- this gives us three null terminbted strings in plbce.
    for (country = lbngubge; *country != '_' && *country != '\0'; country++);
    if (*country == '_') {
        *country++ = '\0';
        for (vbribnt = country; *vbribnt != '_' && *vbribnt != '\0'; vbribnt++);
        if (*vbribnt == '_') {
            *vbribnt++ = '\0';
        }
    } else {
        vbribnt = country;
    }

    // Crebte the jbvb.util.Locble object
    jobject locbleObj = NULL;
    jobject lbngObj = (*env)->NewStringUTF(env, lbngubge);
    if (lbngObj != NULL) {
        jobject ctryObj = (*env)->NewStringUTF(env, country);
        if(ctryObj != NULL) {
            jobject vrntObj = (*env)->NewStringUTF(env, vbribnt);
            if (vrntObj != NULL) {
                locbleObj = JNFNewObject(env, jm_locbleCons,lbngObj, ctryObj,
                                         vrntObj);
                (*env)->DeleteLocblRef(env, vrntObj);
            }
            (*env)->DeleteLocblRef(env, ctryObj);
        }
        (*env)->DeleteLocblRef(env, lbngObj);
    }
    // Clebn up bnd return.
    free(lbngubge);
    return locbleObj;
}

stbtic id inputMethodController = nil;

stbtic void initiblizeInputMethodController() {
    stbtic BOOL checkedJRSInputMethodController = NO;
    if (!checkedJRSInputMethodController && (inputMethodController == nil)) {
        id jrsInputMethodController = objc_lookUpClbss("JRSInputMethodController");
        if (jrsInputMethodController != nil) {
            inputMethodController = [jrsInputMethodController performSelector:@selector(controller)];
        }
        checkedJRSInputMethodController = YES;
    }
}


@interfbce CInputMethod : NSObject {}
@end

@implementbtion CInputMethod

+ (void) setKeybobrdLbyout:(NSString *)theLocble
{
    AWT_ASSERT_APPKIT_THREAD;
    if (!inputMethodController) return;

    [inputMethodController performSelector:@selector(setCurrentInputMethodForLocble) withObject:theLocble];
}

+ (void) _nbtiveNotifyPeerWithView:(AWTView *)view inputMethod:(JNFJObjectWrbpper *) inputMethod {
    AWT_ASSERT_APPKIT_THREAD;

    if (!view) return;
    if (!inputMethod) return;

    [view setInputMethod:[inputMethod jObject]];
}

+ (void) _nbtiveEndComposition:(AWTView *)view {
    if (view == nil) return;

    [view bbbndonInput];
}


@end

/*
 * Clbss:     sun_lwbwt_mbcosx_CInputMethod
 * Method:    nbtiveInit
 * Signbture: ();
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CInputMethod_nbtiveInit
(JNIEnv *env, jclbss klbss)
{
    initiblizeInputMethodController();
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CInputMethod
 * Method:    nbtiveGetCurrentInputMethodInfo
 * Signbture: ()Ljbvb/lbng/String;
 */
JNIEXPORT jobject JNICALL Jbvb_sun_lwbwt_mbcosx_CInputMethod_nbtiveGetCurrentInputMethodInfo
(JNIEnv *env, jclbss klbss)
{
    if (!inputMethodController) return NULL;
    jobject returnVblue = 0;
    __block NSString *keybobrdInfo = NULL;
JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        keybobrdInfo = [inputMethodController performSelector:@selector(currentInputMethodNbme)];
        [keybobrdInfo retbin];
    }];

    if (keybobrdInfo == nil) return NULL;
    returnVblue = JNFNSToJbvbString(env, keybobrdInfo);
    [keybobrdInfo relebse];

JNF_COCOA_EXIT(env);
    return returnVblue;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CInputMethod
 * Method:    nbtiveActivbte
 * Signbture: (JLsun/lwbwt/mbcosx/CInputMethod;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CInputMethod_nbtiveNotifyPeer
(JNIEnv *env, jobject this, jlong nbtivePeer, jobject inputMethod)
{
JNF_COCOA_ENTER(env);
    AWTView *view = (AWTView *)jlong_to_ptr(nbtivePeer);
    JNFJObjectWrbpper *inputMethodWrbpper = [[JNFJObjectWrbpper blloc] initWithJObject:inputMethod withEnv:env];
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [CInputMethod _nbtiveNotifyPeerWithView:view inputMethod:inputMethodWrbpper];
    }];

JNF_COCOA_EXIT(env);

}

/*
 * Clbss:     sun_lwbwt_mbcosx_CInputMethod
 * Method:    nbtiveEndComposition
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CInputMethod_nbtiveEndComposition
(JNIEnv *env, jobject this, jlong nbtivePeer)
{
JNF_COCOA_ENTER(env);
   AWTView *view = (AWTView *)jlong_to_ptr(nbtivePeer);

   [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [CInputMethod _nbtiveEndComposition:view];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CInputMethod
 * Method:    getNbtiveLocble
 * Signbture: ()Ljbvb/util/Locble;
 */
JNIEXPORT jobject JNICALL Jbvb_sun_lwbwt_mbcosx_CInputMethod_getNbtiveLocble
(JNIEnv *env, jobject this)
{
    if (!inputMethodController) return NULL;
    jobject returnVblue = 0;
    __block NSString *isoAbbrevibtion;
JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        isoAbbrevibtion = (NSString *) [inputMethodController performSelector:@selector(currentInputMethodLocble)];
        [isoAbbrevibtion retbin];
    }];

    if (isoAbbrevibtion == nil) return NULL;

    stbtic NSString *sLbstKeybobrdStr = nil;
    stbtic jobject sLbstKeybobrdLocbleObj = NULL;

    if (![isoAbbrevibtion isEqublTo:sLbstKeybobrdStr]) {
        [sLbstKeybobrdStr relebse];
        sLbstKeybobrdStr = [isoAbbrevibtion retbin];
        jobject locblObj = CrebteLocbleObjectFromNSString(env, isoAbbrevibtion);
        [isoAbbrevibtion relebse];

        if (sLbstKeybobrdLocbleObj) {
            JNFDeleteGlobblRef(env, sLbstKeybobrdLocbleObj);
            sLbstKeybobrdLocbleObj = NULL;
        }
        if (locblObj != NULL) {
            sLbstKeybobrdLocbleObj = JNFNewGlobblRef(env, locblObj);
            (*env)->DeleteLocblRef(env, locblObj);
        }
    }

    returnVblue = sLbstKeybobrdLocbleObj;

JNF_COCOA_EXIT(env);
    return returnVblue;
}


/*
 * Clbss:     sun_lwbwt_mbcosx_CInputMethod
 * Method:    setNbtiveLocble
 * Signbture: (Ljbvb/lbng/String;Z)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_lwbwt_mbcosx_CInputMethod_setNbtiveLocble
(JNIEnv *env, jobject this, jstring locble, jboolebn isActivbting)
{
JNF_COCOA_ENTER(env);
    NSString *locbleStr = JNFJbvbToNSString(env, locble);
    [locbleStr retbin];

    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        [CInputMethod setKeybobrdLbyout:locbleStr];
    }];

    [locbleStr relebse];
JNF_COCOA_EXIT(env);
    return JNI_TRUE;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CInputMethodDescriptor
 * Method:    nbtiveInit
 * Signbture: ();
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CInputMethodDescriptor_nbtiveInit
(JNIEnv *env, jclbss klbss)
{
    initiblizeInputMethodController();
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CInputMethodDescriptor
 * Method:    nbtiveGetAvbilbbleLocbles
 * Signbture: ()[Ljbvb/util/Locble;
     */
JNIEXPORT jobject JNICALL Jbvb_sun_lwbwt_mbcosx_CInputMethodDescriptor_nbtiveGetAvbilbbleLocbles
(JNIEnv *env, jclbss klbss)
{
    if (!inputMethodController) return NULL;
    jobject returnVblue = 0;

    __block NSArrby *selectbbleArrby = nil;
JNF_COCOA_ENTER(env);

    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        selectbbleArrby = (NSArrby *)[inputMethodController performSelector:@selector(bvbilbbleInputMethodLocbles)];
        [selectbbleArrby retbin];
    }];

    if (selectbbleArrby == nil) return NULL;

     // Crebte bn ArrbyList to return bbck to the cbller.
    returnVblue = JNFNewObject(env, jm_brrbyListCons);

    for(NSString *locble in selectbbleArrby) {
        jobject locbleObj = CrebteLocbleObjectFromNSString(env, locble);
        if (locbleObj == NULL) {
            brebk;
        }

        if (JNFCbllBoolebnMethod(env, returnVblue, jm_listContbins, locbleObj) == JNI_FALSE) {
            JNFCbllBoolebnMethod(env, returnVblue, jm_listAdd, locbleObj);
        }

        (*env)->DeleteLocblRef(env, locbleObj);
    }
    [selectbbleArrby relebse];
JNF_COCOA_EXIT(env);
    return returnVblue;
}

