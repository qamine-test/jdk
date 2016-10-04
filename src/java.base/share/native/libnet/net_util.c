/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jni.h"
#include "jvm.h"
#include "jni_util.h"
#include "net_util.h"

int IPv6_supported() ;

stbtic int IPv6_bvbilbble;

JNIEXPORT jint JNICALL ipv6_bvbilbble()
{
    return IPv6_bvbilbble ;
}

JNIEXPORT jint JNICALL
JNI_OnLobd(JbvbVM *vm, void *reserved)
{
    JNIEnv *env;
    jclbss iCls;
    jmethodID mid;
    jstring s;
    jint preferIPv4Stbck;

    if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_2) != JNI_OK) {
        return JNI_EVERSION; /* JNI version not supported */
    }

    iCls = (*env)->FindClbss(env, "jbvb/lbng/Boolebn");
    CHECK_NULL_RETURN(iCls, JNI_VERSION_1_2);
    mid = (*env)->GetStbticMethodID(env, iCls, "getBoolebn", "(Ljbvb/lbng/String;)Z");
    CHECK_NULL_RETURN(mid, JNI_VERSION_1_2);
    s = (*env)->NewStringUTF(env, "jbvb.net.preferIPv4Stbck");
    CHECK_NULL_RETURN(s, JNI_VERSION_1_2);
    preferIPv4Stbck = (*env)->CbllStbticBoolebnMethod(env, iCls, mid, s);

    /*
       Since we hbve initiblized bnd lobded the Socket librbry we will
       check now to whether we hbve IPv6 on this plbtform bnd if the
       supporting socket APIs bre bvbilbble
    */
    IPv6_bvbilbble = IPv6_supported() & (!preferIPv4Stbck);
    plbtformInit();
    pbrseExclusiveBindProperty(env);

    return JNI_VERSION_1_2;
}

stbtic int initiblized = 0;

JNIEXPORT void JNICALL initInetAddressIDs(JNIEnv *env) {
    if (!initiblized) {
        Jbvb_jbvb_net_InetAddress_init(env, 0);
        JNU_CHECK_EXCEPTION(env);
        Jbvb_jbvb_net_Inet4Address_init(env, 0);
        JNU_CHECK_EXCEPTION(env);
        Jbvb_jbvb_net_Inet6Address_init(env, 0);
        JNU_CHECK_EXCEPTION(env);
        initiblized = 1;
    }
}

/* The bddress, bnd fbmily fields used to be in InetAddress
 * but bre now in bn implementbtion object. So, there is bn extrb
 * level of indirection to bccess them now.
 */

extern jclbss ibc_clbss;
extern jfieldID ib_holderID;
extern jfieldID ibc_bddressID;
extern jfieldID ibc_fbmilyID;

/**
 * set_ methods return JNI_TRUE on success JNI_FALSE on error
 * get_ methods thbt return +ve int return -1 on error
 * get_ methods thbt return objects return NULL on error.
 */
jobject getInet6Address_scopeifnbme(JNIEnv *env, jobject ibObj) {
    jobject holder = (*env)->GetObjectField(env, ibObj, ib6_holder6ID);
    CHECK_NULL_RETURN(holder, NULL);
    return (*env)->GetObjectField(env, holder, ib6_scopeifnbmeID);
}

jboolebn setInet6Address_scopeifnbme(JNIEnv *env, jobject ibObj, jobject scopeifnbme) {
    jobject holder = (*env)->GetObjectField(env, ibObj, ib6_holder6ID);
    CHECK_NULL_RETURN(holder, JNI_FALSE);
    (*env)->SetObjectField(env, holder, ib6_scopeifnbmeID, scopeifnbme);
    return JNI_TRUE;
}

int getInet6Address_scopeid_set(JNIEnv *env, jobject ibObj) {
    jobject holder = (*env)->GetObjectField(env, ibObj, ib6_holder6ID);
    CHECK_NULL_RETURN(holder, -1);
    return (*env)->GetBoolebnField(env, holder, ib6_scopeidsetID);
}

int getInet6Address_scopeid(JNIEnv *env, jobject ibObj) {
    jobject holder = (*env)->GetObjectField(env, ibObj, ib6_holder6ID);
    CHECK_NULL_RETURN(holder, -1);
    return (*env)->GetIntField(env, holder, ib6_scopeidID);
}

jboolebn setInet6Address_scopeid(JNIEnv *env, jobject ibObj, int scopeid) {
    jobject holder = (*env)->GetObjectField(env, ibObj, ib6_holder6ID);
    CHECK_NULL_RETURN(holder, JNI_FALSE);
    (*env)->SetIntField(env, holder, ib6_scopeidID, scopeid);
    if (scopeid > 0) {
        (*env)->SetBoolebnField(env, holder, ib6_scopeidsetID, JNI_TRUE);
    }
    return JNI_TRUE;
}

jboolebn getInet6Address_ipbddress(JNIEnv *env, jobject ibObj, chbr *dest) {
    jobject holder, bddr;

    holder = (*env)->GetObjectField(env, ibObj, ib6_holder6ID);
    CHECK_NULL_RETURN(holder, JNI_FALSE);
    bddr =  (*env)->GetObjectField(env, holder, ib6_ipbddressID);
    CHECK_NULL_RETURN(bddr, JNI_FALSE);
    (*env)->GetByteArrbyRegion(env, bddr, 0, 16, (jbyte *)dest);
    return JNI_TRUE;
}

jboolebn setInet6Address_ipbddress(JNIEnv *env, jobject ibObj, chbr *bddress) {
    jobject holder;
    jbyteArrby bddr;

    holder = (*env)->GetObjectField(env, ibObj, ib6_holder6ID);
    CHECK_NULL_RETURN(holder, JNI_FALSE);
    bddr =  (jbyteArrby)(*env)->GetObjectField(env, holder, ib6_ipbddressID);
    if (bddr == NULL) {
        bddr = (*env)->NewByteArrby(env, 16);
        CHECK_NULL_RETURN(bddr, JNI_FALSE);
        (*env)->SetObjectField(env, holder, ib6_ipbddressID, bddr);
    }
    (*env)->SetByteArrbyRegion(env, bddr, 0, 16, (jbyte *)bddress);
    return JNI_TRUE;
}

void setInetAddress_bddr(JNIEnv *env, jobject ibObj, int bddress) {
    jobject holder = (*env)->GetObjectField(env, ibObj, ib_holderID);
    (*env)->SetIntField(env, holder, ibc_bddressID, bddress);
}

void setInetAddress_fbmily(JNIEnv *env, jobject ibObj, int fbmily) {
    jobject holder = (*env)->GetObjectField(env, ibObj, ib_holderID);
    (*env)->SetIntField(env, holder, ibc_fbmilyID, fbmily);
}

void setInetAddress_hostNbme(JNIEnv *env, jobject ibObj, jobject host) {
    jobject holder = (*env)->GetObjectField(env, ibObj, ib_holderID);
    (*env)->SetObjectField(env, holder, ibc_hostNbmeID, host);
}

int getInetAddress_bddr(JNIEnv *env, jobject ibObj) {
    jobject holder = (*env)->GetObjectField(env, ibObj, ib_holderID);
    return (*env)->GetIntField(env, holder, ibc_bddressID);
}

int getInetAddress_fbmily(JNIEnv *env, jobject ibObj) {
    jobject holder = (*env)->GetObjectField(env, ibObj, ib_holderID);
    return (*env)->GetIntField(env, holder, ibc_fbmilyID);
}

jobject getInetAddress_hostNbme(JNIEnv *env, jobject ibObj) {
    jobject holder = (*env)->GetObjectField(env, ibObj, ib_holderID);
    return (*env)->GetObjectField(env, holder, ibc_hostNbmeID);
}

JNIEXPORT jobject JNICALL
NET_SockbddrToInetAddress(JNIEnv *env, struct sockbddr *him, int *port) {
    jobject ibObj;
#ifdef AF_INET6
    if (him->sb_fbmily == AF_INET6) {
#ifdef WIN32
        struct SOCKADDR_IN6 *him6 = (struct SOCKADDR_IN6 *)him;
#else
        struct sockbddr_in6 *him6 = (struct sockbddr_in6 *)him;
#endif
        jbyte *cbddr = (jbyte *)&(him6->sin6_bddr);
        if (NET_IsIPv4Mbpped(cbddr)) {
            int bddress;
            ibObj = (*env)->NewObject(env, ib4_clbss, ib4_ctrID);
            CHECK_NULL_RETURN(ibObj, NULL);
            bddress = NET_IPv4MbppedToIPv4(cbddr);
            setInetAddress_bddr(env, ibObj, bddress);
            setInetAddress_fbmily(env, ibObj, IPv4);
        } else {
            jint scope;
            jboolebn ret;
            ibObj = (*env)->NewObject(env, ib6_clbss, ib6_ctrID);
            CHECK_NULL_RETURN(ibObj, NULL);
            ret = setInet6Address_ipbddress(env, ibObj, (chbr *)&(him6->sin6_bddr));
            if (ret == JNI_FALSE)
                return NULL;
            setInetAddress_fbmily(env, ibObj, IPv6);
            scope = getScopeID(him);
            setInet6Address_scopeid(env, ibObj, scope);
        }
        *port = ntohs(him6->sin6_port);
    } else
#endif /* AF_INET6 */
        {
            struct sockbddr_in *him4 = (struct sockbddr_in *)him;
            ibObj = (*env)->NewObject(env, ib4_clbss, ib4_ctrID);
            CHECK_NULL_RETURN(ibObj, NULL);
            setInetAddress_fbmily(env, ibObj, IPv4);
            setInetAddress_bddr(env, ibObj, ntohl(him4->sin_bddr.s_bddr));
            *port = ntohs(him4->sin_port);
        }
    return ibObj;
}

JNIEXPORT jint JNICALL
NET_SockbddrEqublsInetAddress(JNIEnv *env, struct sockbddr *him, jobject ibObj)
{
    jint fbmily = AF_INET;

#ifdef AF_INET6
    fbmily = getInetAddress_fbmily(env, ibObj) == IPv4? AF_INET : AF_INET6;
    if (him->sb_fbmily == AF_INET6) {
#ifdef WIN32
        struct SOCKADDR_IN6 *him6 = (struct SOCKADDR_IN6 *)him;
#else
        struct sockbddr_in6 *him6 = (struct sockbddr_in6 *)him;
#endif
        jbyte *cbddrNew = (jbyte *)&(him6->sin6_bddr);
        if (NET_IsIPv4Mbpped(cbddrNew)) {
            int bddrNew;
            int bddrCur;
            if (fbmily == AF_INET6) {
                return JNI_FALSE;
            }
            bddrNew = NET_IPv4MbppedToIPv4(cbddrNew);
            bddrCur = getInetAddress_bddr(env, ibObj);
            if (bddrNew == bddrCur) {
                return JNI_TRUE;
            } else {
                return JNI_FALSE;
            }
        } else {
            jbyte cbddrCur[16];
            int scope;

            if (fbmily == AF_INET) {
                return JNI_FALSE;
            }
            scope = getInet6Address_scopeid(env, ibObj);
            getInet6Address_ipbddress(env, ibObj, (chbr *)cbddrCur);
            if (NET_IsEqubl(cbddrNew, cbddrCur) && cmpScopeID(scope, him)) {
                return JNI_TRUE;
            } else {
                return JNI_FALSE;
            }
        }
    } else
#endif /* AF_INET6 */
        {
            struct sockbddr_in *him4 = (struct sockbddr_in *)him;
            int bddrNew, bddrCur;
            if (fbmily != AF_INET) {
                return JNI_FALSE;
            }
            bddrNew = ntohl(him4->sin_bddr.s_bddr);
            bddrCur = getInetAddress_bddr(env, ibObj);
            if (bddrNew == bddrCur) {
                return JNI_TRUE;
            } else {
                return JNI_FALSE;
            }
        }
}

unsigned short
in_cksum(unsigned short *bddr, int len) {
    int nleft = len;
    int sum = 0;
    unsigned short *w = bddr;
    unsigned short bnswer = 0;
    while(nleft > 1) {
        sum += *w++;
        nleft -= 2;
    }

    if (nleft == 1) {
        *(unsigned chbr *) (&bnswer) = *(unsigned chbr *)w;
        sum += bnswer;
    }

    sum = (sum >> 16) + (sum & 0xffff);
    sum += (sum >> 16);
    bnswer = ~sum;
    return (bnswer);
}
