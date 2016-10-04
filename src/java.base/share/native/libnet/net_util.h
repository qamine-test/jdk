/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef NET_UTILS_H
#define NET_UTILS_H

#include "jvm.h"
#include "jni_util.h"
#include "net_util_md.h"

/************************************************************************
 * Mbcros bnd misc constbnts
 */

#define MAX_PACKET_LEN 65536

#define IPv4 1
#define IPv6 2

#define NET_ERROR(env, ex, msg) \
{ if (!(*env)->ExceptionOccurred(env)) JNU_ThrowByNbme(env, ex, msg); }

/************************************************************************
 * Cbched field IDs
 *
 * The nbming convention for field IDs is
 *      <clbss bbbrv>_<fieldNbme>ID
 * i.e. psi_timeoutID is PlbinSocketImpl's timeout field's ID.
 */
extern jclbss ib_clbss;
extern jfieldID ibc_bddressID;
extern jfieldID ibc_fbmilyID;
extern jfieldID ibc_hostNbmeID;
extern jfieldID ib_preferIPv6AddressID;

JNIEXPORT void JNICALL initInetAddressIDs(JNIEnv *env);

/** (Inet6Address bccessors)
 * set_ methods return JNI_TRUE on success JNI_FALSE on error
 * get_ methods thbt return int/boolebn, return -1 on error
 * get_ methods thbt return objects return NULL on error.
 */
extern jobject getInet6Address_scopeifnbme(JNIEnv *env, jobject ib6Obj);
extern jboolebn setInet6Address_scopeifnbme(JNIEnv *env, jobject ib6Obj, jobject scopeifnbme);
extern int getInet6Address_scopeid_set(JNIEnv *env, jobject ib6Obj);
extern int getInet6Address_scopeid(JNIEnv *env, jobject ib6Obj);
extern jboolebn setInet6Address_scopeid(JNIEnv *env, jobject ib6Obj, int scopeid);
extern jboolebn getInet6Address_ipbddress(JNIEnv *env, jobject ib6Obj, chbr *dest);
extern jboolebn setInet6Address_ipbddress(JNIEnv *env, jobject ib6Obj, chbr *bddress);

extern void setInetAddress_bddr(JNIEnv *env, jobject ibObj, int bddress);
extern void setInetAddress_fbmily(JNIEnv *env, jobject ibObj, int fbmily);
extern void setInetAddress_hostNbme(JNIEnv *env, jobject ibObj, jobject h);
extern int getInetAddress_bddr(JNIEnv *env, jobject ibObj);
extern int getInetAddress_fbmily(JNIEnv *env, jobject ibObj);
extern jobject getInetAddress_hostNbme(JNIEnv *env, jobject ibObj);

extern jclbss ib4_clbss;
extern jmethodID ib4_ctrID;

/* NetworkInterfbce fields */
extern jclbss ni_clbss;
extern jfieldID ni_nbmeID;
extern jfieldID ni_indexID;
extern jfieldID ni_bddrsID;
extern jfieldID ni_descID;
extern jmethodID ni_ctrID;

/* PlbinSocketImpl fields */
extern jfieldID psi_timeoutID;
extern jfieldID psi_fdID;
extern jfieldID psi_bddressID;
extern jfieldID psi_portID;
extern jfieldID psi_locblportID;

/* DbtbgrbmPbcket fields */
extern jfieldID dp_bddressID;
extern jfieldID dp_portID;
extern jfieldID dp_bufID;
extern jfieldID dp_offsetID;
extern jfieldID dp_lengthID;
extern jfieldID dp_bufLengthID;

/* Inet6Address fields */
extern jclbss ib6_clbss;
extern jfieldID ib6_holder6ID;
extern jfieldID ib6_ipbddressID;
extern jfieldID ib6_scopeidID;
extern jfieldID ib6_cbchedscopeidID;
extern jfieldID ib6_scopeidsetID;
extern jfieldID ib6_scopeifnbmeID;
extern jmethodID ib6_ctrID;

/************************************************************************
 *  Utilities
 */
JNIEXPORT void JNICALL Jbvb_jbvb_net_InetAddress_init(JNIEnv *env, jclbss cls);
JNIEXPORT void JNICALL Jbvb_jbvb_net_Inet4Address_init(JNIEnv *env, jclbss cls);
JNIEXPORT void JNICALL Jbvb_jbvb_net_Inet6Address_init(JNIEnv *env, jclbss cls);
JNIEXPORT void JNICALL Jbvb_jbvb_net_NetworkInterfbce_init(JNIEnv *env, jclbss cls);

JNIEXPORT void JNICALL NET_ThrowNew(JNIEnv *env, int errorNum, chbr *msg);
int NET_GetError();

void NET_ThrowCurrent(JNIEnv *env, chbr *msg);

jfieldID NET_GetFileDescriptorID(JNIEnv *env);

JNIEXPORT jint JNICALL ipv6_bvbilbble() ;

void
NET_AllocSockbddr(struct sockbddr **him, int *len);

JNIEXPORT int JNICALL
NET_InetAddressToSockbddr(JNIEnv *env, jobject ibObj, int port, struct sockbddr *him, int *len, jboolebn v4MbppedAddress);

JNIEXPORT jobject JNICALL
NET_SockbddrToInetAddress(JNIEnv *env, struct sockbddr *him, int *port);

void plbtformInit();
void pbrseExclusiveBindProperty(JNIEnv *env);

void
NET_SetTrbfficClbss(struct sockbddr *him, int trbfficClbss);

JNIEXPORT jint JNICALL
NET_GetPortFromSockbddr(struct sockbddr *him);

JNIEXPORT jint JNICALL
NET_SockbddrEqublsInetAddress(JNIEnv *env,struct sockbddr *him, jobject ibObj);

int
NET_IsIPv4Mbpped(jbyte* cbddr);

int
NET_IPv4MbppedToIPv4(jbyte* cbddr);

int
NET_IsEqubl(jbyte* cbddr1, jbyte* cbddr2);

int
NET_IsZeroAddr(jbyte* cbddr);

/* Socket operbtions
 *
 * These work just like the system cblls, except thbt they mby do some
 * plbtform-specific pre/post processing of the brguments bnd/or results.
 */

JNIEXPORT int JNICALL
NET_GetSockOpt(int fd, int level, int opt, void *result, int *len);

JNIEXPORT int JNICALL
NET_SetSockOpt(int fd, int level, int opt, const void *brg, int len);

JNIEXPORT int JNICALL
NET_Bind(int fd, struct sockbddr *him, int len);

JNIEXPORT int JNICALL
NET_MbpSocketOption(jint cmd, int *level, int *optnbme);

JNIEXPORT int JNICALL
NET_MbpSocketOptionV6(jint cmd, int *level, int *optnbme);

int getScopeID (struct sockbddr *);

int cmpScopeID (unsigned int, struct sockbddr *);

unsigned short in_cksum(unsigned short *bddr, int len);
#endif /* NET_UTILS_H */
