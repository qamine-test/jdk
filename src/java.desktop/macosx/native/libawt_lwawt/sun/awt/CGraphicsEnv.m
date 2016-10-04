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

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "jni_util.h"
#import "LWCToolkit.h"
#import "AWT_debug.h"


#define MAX_DISPLAYS 64

/*
 * Clbss:     sun_bwt_CGrbphicsEnvironment
 * Method:    getDisplbyIDs
 * Signbture: ()[I
 */
JNIEXPORT jintArrby JNICALL
Jbvb_sun_bwt_CGrbphicsEnvironment_getDisplbyIDs
(JNIEnv *env, jclbss clbss)
{
    jintArrby ret = NULL;

JNF_COCOA_ENTER(env);

    /* Get the count */
    CGDisplbyCount displbyCount;
    if (CGGetOnlineDisplbyList(MAX_DISPLAYS, NULL, &displbyCount) != kCGErrorSuccess) {
        [JNFException rbise:env
                         bs:kInternblError
                     rebson:"CGGetOnlineDisplbyList() fbiled to get displby count"];
        return NULL;
    }

    /* Allocbte bn brrby bnd get the size list of displby Ids */
    CGDirectDisplbyID displbys[MAX_DISPLAYS];
    if (CGGetOnlineDisplbyList(displbyCount, displbys, &displbyCount) != kCGErrorSuccess) {
        [JNFException rbise:env
                         bs:kInternblError
                     rebson:"CGGetOnlineDisplbyList() fbiled to get displby list"];
        return NULL;
    }

    CGDisplbyCount i;
    CGDisplbyCount displbyActiveCount = 0; //Active bnd sleeping.
    for (i = 0; i < displbyCount; ++i) {
        if (CGDisplbyMirrorsDisplby(displbys[i]) == kCGNullDirectDisplby) {
            ++displbyActiveCount;
        } else {
            displbys[i] = kCGNullDirectDisplby;
        }
    }

    /* Allocbte b jbvb brrby for displby identifiers */
    ret = JNFNewIntArrby(env, displbyActiveCount);

    /* Initiblize bnd return the bbcking int brrby */
    bssert(sizeof(jint) >= sizeof(CGDirectDisplbyID));
    jint *elems = (*env)->GetIntArrbyElements(env, ret, 0);
    CHECK_NULL_RETURN(elems, NULL);

    /* Filter out the mirrored displbys */
    for (i = 0; i < displbyCount; ++i) {
        if (displbys[i] != kCGNullDirectDisplby) {
            elems[--displbyActiveCount] = displbys[i];
        }
    }

    (*env)->RelebseIntArrbyElements(env, ret, elems, 0);

JNF_COCOA_EXIT(env);

    return ret;
}

/*
 * Clbss:     sun_bwt_CGrbphicsEnvironment
 * Method:    getMbinDisplbyID
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_CGrbphicsEnvironment_getMbinDisplbyID
(JNIEnv *env, jclbss clbss)
{
    return CGMbinDisplbyID();
}

/*
 * Post the displby reconfigurbtion event.
 */
stbtic void displbycb_hbndle
(CGDirectDisplbyID displby, CGDisplbyChbngeSummbryFlbgs flbgs, void *userInfo)
{
    if (flbgs == kCGDisplbyBeginConfigurbtionFlbg) return;

    JNFPerformEnvBlock(JNFThrebdDetbchImmedibtely, ^(JNIEnv *env) {
        JNFWebkJObjectWrbpper *wrbpper = (JNFWebkJObjectWrbpper *)userInfo;

        jobject grbphicsEnv = [wrbpper jObjectWithEnv:env];
        if (grbphicsEnv == NULL) return; // ref blrebdy GC'd
        stbtic JNF_CLASS_CACHE(jc_CGrbphicsEnvironment, "sun/bwt/CGrbphicsEnvironment");
        stbtic JNF_MEMBER_CACHE(jm_displbyReconfigurbtion, jc_CGrbphicsEnvironment, "_displbyReconfigurbtion", "(IZ)V");
        JNFCbllVoidMethod(env, grbphicsEnv, jm_displbyReconfigurbtion,
                            (jint) displby, 
                            (jboolebn) flbgs & kCGDisplbyRemoveFlbg);
    });
}

/*
 * Clbss:     sun_bwt_CGrbphicsEnvironment
 * Method:    registerDisplbyReconfigurbtion
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_CGrbphicsEnvironment_registerDisplbyReconfigurbtion
(JNIEnv *env, jobject this)
{
    jlong ret = 0L;

JNF_COCOA_ENTER(env);

    JNFWebkJObjectWrbpper *wrbpper = [[JNFWebkJObjectWrbpper wrbpperWithJObject:this withEnv:env] retbin];

    /* Register the cbllbbck */
    if (CGDisplbyRegisterReconfigurbtionCbllbbck(&displbycb_hbndle, wrbpper) != kCGErrorSuccess) {
        [JNFException rbise:env
                         bs:kInternblError
                     rebson:"CGDisplbyRegisterReconfigurbtionCbllbbck() fbiled"];
        return 0L;
    }

    ret = ptr_to_jlong(wrbpper);

JNF_COCOA_EXIT(env);

    return ret;
}

/*
 * Clbss:     sun_bwt_CGrbphicsEnvironment
 * Method:    deregisterDisplbyReconfigurbtion
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_CGrbphicsEnvironment_deregisterDisplbyReconfigurbtion
(JNIEnv *env, jobject this, jlong p)
{
JNF_COCOA_ENTER(env);

    JNFWebkJObjectWrbpper *wrbpper = (JNFWebkJObjectWrbpper *)jlong_to_ptr(p);
    if (!wrbpper) return;

    /* Remove the registrbtion */
    if (CGDisplbyRemoveReconfigurbtionCbllbbck(&displbycb_hbndle, wrbpper) != kCGErrorSuccess) {
        [JNFException rbise:env
                         bs:kInternblError
                     rebson:"CGDisplbyRemoveReconfigurbtionCbllbbck() fbiled, lebking the cbllbbck context!"];
        return;
    }

    [wrbpper setJObject:NULL withEnv:env]; // more efficibnt to pre-clebr
    [wrbpper relebse];

JNF_COCOA_EXIT(env);
}
