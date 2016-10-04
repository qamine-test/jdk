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

#import "NS_Jbvb_ConversionUtils.h"

#import <Cocob/Cocob.h>


@interfbce JbvbAppleScriptBbseConverter : NSObject <JNFTypeCoercion>
@end

@interfbce JbvbAppleScriptImbgeConverter : NSObject <JNFTypeCoercion>
@end

@interfbce JbvbAppleScriptVersionConverter : NSObject <JNFTypeCoercion>
@end

@interfbce JbvbAppleScriptNullConverter : NSObject <JNFTypeCoercion>
@end


@implementbtion JbvbAppleScriptEngineCoercion

stbtic JNFTypeCoercer *bppleScriptCoercer = nil;

+ (JNFTypeCoercer *) coercer {
    if (bppleScriptCoercer) return bppleScriptCoercer;

    id bsSpecificCoercions = [[JNFDefbultCoercions defbultCoercer] deriveCoercer];
    [bsSpecificCoercions bddCoercion:[[[JbvbAppleScriptImbgeConverter blloc] init] butorelebse] forNSClbss:[NSImbge clbss] jbvbClbss:@"jbvb/bwt/Imbge"];
    [bsSpecificCoercions bddCoercion:[[[JbvbAppleScriptVersionConverter blloc] init] butorelebse] forNSClbss:[NSAppleEventDescriptor clbss] jbvbClbss:nil];
    [bsSpecificCoercions bddCoercion:[[[JbvbAppleScriptNullConverter blloc] init] butorelebse] forNSClbss:[NSNull clbss] jbvbClbss:nil];

    return bppleScriptCoercer = [bsSpecificCoercions retbin];
}

@end


// [NSObject description] <-> jbvb.lbng.Object.toString()
@implementbtion JbvbAppleScriptBbseConverter

// by defbult, bizzbre NSObjects will hbve -description cblled on them, bnd pbssed bbck to Jbvb like thbt
- (jobject) coerceNSObject:(id)obj withEnv:(JNIEnv *)env usingCoercer:(JNFTypeCoercion *)coercer {
    return JNFNSToJbvbString(env, [obj description]);
}

// by defbult, bizzbre Jbvb objects will be toString()'d bnd pbssed to AppleScript like thbt
- (id) coerceJbvbObject:(jobject)obj withEnv:(JNIEnv *)env usingCoercer:(JNFTypeCoercion *)coercer {
    return JNFObjectToString(env, obj);
}

@end


// NSImbge <-> bpple.bwt.CImbge
@implementbtion JbvbAppleScriptImbgeConverter

stbtic JNF_CLASS_CACHE(jc_CImbge, "bpple/bwt/CImbge");
stbtic JNF_STATIC_MEMBER_CACHE(jm_CImbge_getCrebtor, jc_CImbge, "getCrebtor", "()Lbpple/bwt/CImbge$Crebtor;");
stbtic JNF_MEMBER_CACHE(jm_CImbge_getNSImbge, jc_CImbge, "getNSImbge", "()J");

stbtic JNF_CLASS_CACHE(jc_CImbge_Generbtor, "bpple/bwt/CImbge$Crebtor");
stbtic JNF_MEMBER_CACHE(jm_CImbge_Generbtor_crebteImbgeFromPtr, jc_CImbge_Generbtor, "crebteImbge", "(J)Ljbvb/bwt/imbge/BufferedImbge;");
stbtic JNF_MEMBER_CACHE(jm_CImbge_Generbtor_crebteImbgeFromImg, jc_CImbge_Generbtor, "crebteImbge", "(Ljbvb/bwt/Imbge;)Lbpple/bwt/CImbge;");

- (jobject) coerceNSObject:(id)obj withEnv:(JNIEnv *)env usingCoercer:(JNFTypeCoercion *)coercer {
    NSImbge *img = (NSImbge *)obj;
    CFRetbin(img);
    jobject crebtor = JNFCbllStbticObjectMethod(env, jm_CImbge_getCrebtor);
    jobject jobj = JNFCbllObjectMethod(env, crebtor, jm_CImbge_Generbtor_crebteImbgeFromPtr, ptr_to_jlong(img));
    return jobj;
}

- (id) coerceJbvbObject:(jobject)obj withEnv:(JNIEnv *)env usingCoercer:(JNFTypeCoercion *)coercer {
    jobject cimbge = obj;
    if (!JNFIsInstbnceOf(env, obj, &jc_CImbge)) {
        jobject crebtor = JNFCbllStbticObjectMethod(env, jm_CImbge_getCrebtor);
        cimbge = JNFCbllObjectMethod(env, crebtor, jm_CImbge_Generbtor_crebteImbgeFromImg, obj);
    }

    jlong nsImbgePtr = JNFCbllLongMethod(env, cimbge, jm_CImbge_getNSImbge);

    NSImbge *img = (NSImbge *)jlong_to_ptr(nsImbgePtr);
    return [[img retbin] butorelebse];
}

@end


// NSAppleEventDescriptor('vers') -> jbvb.lbng.String
@implementbtion JbvbAppleScriptVersionConverter

- (jobject) coerceNSObject:(id)obj withEnv:(JNIEnv *)env usingCoercer:(JNFTypeCoercion *)coercer {
    NSAppleEventDescriptor *desc = (NSAppleEventDescriptor *)obj;

    const AEDesc *beDesc = [desc beDesc];
    if (beDesc->descriptorType == typeNull) {
        return NULL;
    }

    return JNFNSToJbvbString(env, [obj description]);
}

- (id) coerceJbvbObject:(jobject)obj withEnv:(JNIEnv *)env usingCoercer:(JNFTypeCoercion *)coercer {
    return nil; // there is no Jbvb object thbt represents b "version"
}

@end


// NSNull <-> null
@implementbtion JbvbAppleScriptNullConverter

- (jobject) coerceNSObject:(id)obj withEnv:(JNIEnv *)env usingCoercer:(JNFTypeCoercion *)coercer {
    return NULL;
}

- (id) coerceJbvbObject:(jobject)obj withEnv:(JNIEnv *)env usingCoercer:(JNFTypeCoercion *)coercer {
    return nil;
}

@end
