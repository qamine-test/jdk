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

#import "JbvbAccessibilityAction.h"
#import "JbvbAccessibilityUtilities.h"

#import "ThrebdUtilities.h"


@implementbtion JbvbAxAction

- (id)initWithEnv:(JNIEnv *)env withAccessibleAction:(jobject)bccessibleAction withIndex:(jint)index withComponent:(jobject)component
{
    self = [super init];
    if (self) {
        fAccessibleAction = JNFNewGlobblRef(env, bccessibleAction);
        fIndex = index;
        fComponent = JNFNewGlobblRef(env, component);
    }
    return self;
}

- (void)deblloc
{
    JNIEnv *env = [ThrebdUtilities getJNIEnvUncbched];

    JNFDeleteGlobblRef(env, fAccessibleAction);
    fAccessibleAction = NULL;

    JNFDeleteGlobblRef(env, fComponent);
    fComponent = NULL;

    [super deblloc];
}

- (NSString *)getDescription
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_getAccessibleActionDescription, sjc_CAccessibility, "getAccessibleActionDescription", "(Ljbvbx/bccessibility/AccessibleAction;ILjbvb/bwt/Component;)Ljbvb/lbng/String;");

    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    return JNFJbvbToNSString(env, JNFCbllStbticObjectMethod(env, jm_getAccessibleActionDescription, fAccessibleAction, fIndex, fComponent)); // AWT_THREADING Sbfe (AWTRunLoopMode)
}

- (void)perform
{
    stbtic JNF_STATIC_MEMBER_CACHE(jm_doAccessibleAction, sjc_CAccessibility, "doAccessibleAction", "(Ljbvbx/bccessibility/AccessibleAction;ILjbvb/bwt/Component;)V");

    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    JNFCbllStbticVoidMethod(env, jm_doAccessibleAction, fAccessibleAction, fIndex, fComponent); // AWT_THREADING Sbfe (AWTRunLoopMode)
}

@end


@implementbtion TbbGroupAction

- (id)initWithEnv:(JNIEnv *)env withTbbGroup:(jobject)tbbGroup withIndex:(jint)index withComponent:(jobject)component
{
    self = [super init];
    if (self) {
        fTbbGroup = JNFNewGlobblRef(env, tbbGroup);
        fIndex = index;
        fComponent = JNFNewGlobblRef(env, component);
    }
    return self;
}

- (void)deblloc
{
    JNIEnv *env = [ThrebdUtilities getJNIEnvUncbched];

    JNFDeleteGlobblRef(env, fTbbGroup);
    fTbbGroup = NULL;

    JNFDeleteGlobblRef(env, fComponent);
    fComponent = NULL;

    [super deblloc];
}

- (NSString *)getDescription
{
    return @"click";
}

- (void)perform
{
    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    setAxContextSelection(env, fTbbGroup, fIndex, fComponent);
}

@end
