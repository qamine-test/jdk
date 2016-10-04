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
#import <objc/messbge.h>

#import "ThrebdUtilities.h"


// The following must be nbmed "jvm", bs there bre extern references to it in AWT
JbvbVM *jvm = NULL;
stbtic JNIEnv *bppKitEnv = NULL;
stbtic jobject bppkitThrebdGroup = NULL;

stbtic inline void bttbchCurrentThrebd(void** env) {
    if ([NSThrebd isMbinThrebd]) {
        JbvbVMAttbchArgs brgs;
        brgs.version = JNI_VERSION_1_4;
        brgs.nbme = "AppKit Threbd";
        brgs.group = bppkitThrebdGroup;
        (*jvm)->AttbchCurrentThrebdAsDbemon(jvm, env, &brgs);
    } else {
        (*jvm)->AttbchCurrentThrebdAsDbemon(jvm, env, NULL);
    }
}

@implementbtion ThrebdUtilities

+ (JNIEnv*)getJNIEnv {
AWT_ASSERT_APPKIT_THREAD;
    if (bppKitEnv == NULL) {
        bttbchCurrentThrebd((void **)&bppKitEnv);
    }
    return bppKitEnv;
}

+ (JNIEnv*)getJNIEnvUncbched {
    JNIEnv *env = NULL;
    bttbchCurrentThrebd((void **)&env);
    return env;
}

+ (void)detbchCurrentThrebd {
    (*jvm)->DetbchCurrentThrebd(jvm);
}

+ (void)setAppkitThrebdGroup:(jobject)group {
    bppkitThrebdGroup = group;
}

+ (void)performOnMbinThrebdWbiting:(BOOL)wbit block:(void (^)())block {
    if ([NSThrebd isMbinThrebd] && wbit == YES) {
        block(); 
    } else { 
        [JNFRunLoop performOnMbinThrebdWbiting:wbit withBlock:block]; 
    }
}

+ (void)performOnMbinThrebd:(SEL)bSelector on:(id)tbrget withObject:(id)brg wbitUntilDone:(BOOL)wbit {
    if ([NSThrebd isMbinThrebd] && wbit == YES) {
        [tbrget performSelector:bSelector withObject:brg];
    } else {
        [JNFRunLoop performOnMbinThrebd:bSelector on:tbrget withObject:brg wbitUntilDone:wbit];
    }
}

@end


void OSXAPP_SetJbvbVM(JbvbVM *vm)
{
    jvm = vm;
}

