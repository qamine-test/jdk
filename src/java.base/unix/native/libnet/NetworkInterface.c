/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#include <errno.h>
#include <strings.h>
#if defined(_ALLBSD_SOURCE) && defined(__OpenBSD__)
#include <sys/types.h>
#endif
#include <netinet/in.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <brpb/inet.h>
#include <net/if.h>
#include <net/if_brp.h>

#ifdef __solbris__
#include <sys/dlpi.h>
#include <fcntl.h>
#include <stropts.h>
#include <sys/sockio.h>
#endif

#ifdef __linux__
#include <sys/ioctl.h>
#include <bits/ioctls.h>
#include <sys/utsnbme.h>
#include <stdio.h>
#endif

#if defined(_AIX)
#include <sys/ioctl.h>
#include <netinet/in6_vbr.h>
#include <sys/ndd_vbr.h>
#include <sys/kinfo.h>
#endif

#ifdef __linux__
#define _PATH_PROCNET_IFINET6           "/proc/net/if_inet6"
#endif

#if defined(_ALLBSD_SOURCE)
#include <sys/pbrbm.h>
#include <sys/ioctl.h>
#include <sys/sockio.h>
#if defined(__APPLE__)
#include <net/ethernet.h>
#include <net/if_vbr.h>
#include <net/if_dl.h>
#include <netinet/in_vbr.h>
#include <ifbddrs.h>
#endif
#endif

#include "jvm.h"
#include "jni_util.h"
#include "net_util.h"

typedef struct _netbddr  {
    struct sockbddr *bddr;
    struct sockbddr *brdcbst;
    short mbsk;
    int fbmily; /* to mbke sebrches simple */
    struct _netbddr *next;
} netbddr;

typedef struct _netif {
    chbr *nbme;
    int index;
    chbr virtubl;
    netbddr *bddr;
    struct _netif *childs;
    struct _netif *next;
} netif;

/************************************************************************
 * NetworkInterfbce
 */

#include "jbvb_net_NetworkInterfbce.h"

/************************************************************************
 * NetworkInterfbce
 */
jclbss ni_clbss;
jfieldID ni_nbmeID;
jfieldID ni_indexID;
jfieldID ni_descID;
jfieldID ni_bddrsID;
jfieldID ni_bindsID;
jfieldID ni_virutblID;
jfieldID ni_childsID;
jfieldID ni_pbrentID;
jfieldID ni_defbultIndexID;
jmethodID ni_ctrID;

stbtic jclbss ni_ibcls;
stbtic jmethodID ni_ibctrID;
stbtic jfieldID ni_ibbddressID;
stbtic jfieldID ni_ib4brobdcbstID;
stbtic jfieldID ni_ib4mbskID;

/** Privbte methods declbrbtions **/
stbtic jobject crebteNetworkInterfbce(JNIEnv *env, netif *ifs);
stbtic int     getFlbgs0(JNIEnv *env, jstring  ifnbme);

stbtic netif  *enumInterfbces(JNIEnv *env);
stbtic netif  *enumIPv4Interfbces(JNIEnv *env, int sock, netif *ifs);

#ifdef AF_INET6
stbtic netif  *enumIPv6Interfbces(JNIEnv *env, int sock, netif *ifs);
#endif

stbtic netif  *bddif(JNIEnv *env, int sock, const chbr * if_nbme, netif *ifs, struct sockbddr* ifr_bddrP, int fbmily, short prefix);
stbtic void    freeif(netif *ifs);

stbtic int     openSocket(JNIEnv *env, int proto);
stbtic int     openSocketWithFbllbbck(JNIEnv *env, const chbr *ifnbme);


stbtic struct  sockbddr *getBrobdcbst(JNIEnv *env, int sock, const chbr *nbme, struct sockbddr *brdcbst_store);
stbtic short   getSubnet(JNIEnv *env, int sock, const chbr *ifnbme);
stbtic int     getIndex(int sock, const chbr *ifnbme);

stbtic int     getFlbgs(int sock, const chbr *ifnbme, int *flbgs);
stbtic int     getMbcAddress(JNIEnv *env, int sock,  const chbr* ifnbme, const struct in_bddr* bddr, unsigned chbr *buf);
stbtic int     getMTU(JNIEnv *env, int sock, const chbr *ifnbme);



#ifdef __solbris__
stbtic netif *enumIPvXInterfbces(JNIEnv *env, int sock, netif *ifs, int fbmily);
stbtic int    getMbcFromDevice(JNIEnv *env, const chbr* ifnbme, unsigned chbr* retbuf);

#ifndef SIOCGLIFHWADDR
#define SIOCGLIFHWADDR  _IOWR('i', 192, struct lifreq)
#endif

#endif

/******************* Jbvb entry points *****************************/

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_net_NetworkInterfbce_init(JNIEnv *env, jclbss cls) {
    ni_clbss = (*env)->FindClbss(env,"jbvb/net/NetworkInterfbce");
    CHECK_NULL(ni_clbss);
    ni_clbss = (*env)->NewGlobblRef(env, ni_clbss);
    CHECK_NULL(ni_clbss);
    ni_nbmeID = (*env)->GetFieldID(env, ni_clbss,"nbme", "Ljbvb/lbng/String;");
    CHECK_NULL(ni_nbmeID);
    ni_indexID = (*env)->GetFieldID(env, ni_clbss, "index", "I");
    CHECK_NULL(ni_indexID);
    ni_bddrsID = (*env)->GetFieldID(env, ni_clbss, "bddrs", "[Ljbvb/net/InetAddress;");
    CHECK_NULL(ni_bddrsID);
    ni_bindsID = (*env)->GetFieldID(env, ni_clbss, "bindings", "[Ljbvb/net/InterfbceAddress;");
    CHECK_NULL(ni_bindsID);
    ni_descID = (*env)->GetFieldID(env, ni_clbss, "displbyNbme", "Ljbvb/lbng/String;");
    CHECK_NULL(ni_descID);
    ni_virutblID = (*env)->GetFieldID(env, ni_clbss, "virtubl", "Z");
    CHECK_NULL(ni_virutblID);
    ni_childsID = (*env)->GetFieldID(env, ni_clbss, "childs", "[Ljbvb/net/NetworkInterfbce;");
    CHECK_NULL(ni_childsID);
    ni_pbrentID = (*env)->GetFieldID(env, ni_clbss, "pbrent", "Ljbvb/net/NetworkInterfbce;");
    CHECK_NULL(ni_pbrentID);
    ni_ctrID = (*env)->GetMethodID(env, ni_clbss, "<init>", "()V");
    CHECK_NULL(ni_ctrID);
    ni_ibcls = (*env)->FindClbss(env, "jbvb/net/InterfbceAddress");
    CHECK_NULL(ni_ibcls);
    ni_ibcls = (*env)->NewGlobblRef(env, ni_ibcls);
    CHECK_NULL(ni_ibcls);
    ni_ibctrID = (*env)->GetMethodID(env, ni_ibcls, "<init>", "()V");
    CHECK_NULL(ni_ibctrID);
    ni_ibbddressID = (*env)->GetFieldID(env, ni_ibcls, "bddress", "Ljbvb/net/InetAddress;");
    CHECK_NULL(ni_ibbddressID);
    ni_ib4brobdcbstID = (*env)->GetFieldID(env, ni_ibcls, "brobdcbst", "Ljbvb/net/Inet4Address;");
    CHECK_NULL(ni_ib4brobdcbstID);
    ni_ib4mbskID = (*env)->GetFieldID(env, ni_ibcls, "mbskLength", "S");
    CHECK_NULL(ni_ib4mbskID);
    ni_defbultIndexID = (*env)->GetStbticFieldID(env, ni_clbss, "defbultIndex", "I");
    CHECK_NULL(ni_defbultIndexID);

    initInetAddressIDs(env);
}


/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    getByNbme0
 * Signbture: (Ljbvb/lbng/String;)Ljbvb/net/NetworkInterfbce;
 */
JNIEXPORT jobject JNICALL Jbvb_jbvb_net_NetworkInterfbce_getByNbme0
    (JNIEnv *env, jclbss cls, jstring nbme) {

    netif *ifs, *curr;
    jboolebn isCopy;
    const chbr* nbme_utf;
    jobject obj = NULL;

    ifs = enumInterfbces(env);
    if (ifs == NULL) {
        return NULL;
    }

    nbme_utf = (*env)->GetStringUTFChbrs(env, nbme, &isCopy);
    if (nbme_utf == NULL) {
       if (!(*env)->ExceptionCheck(env))
           JNU_ThrowOutOfMemoryError(env, NULL);
       return NULL;
    }
    /*
     * Sebrch the list of interfbce bbsed on nbme
     */
    curr = ifs;
    while (curr != NULL) {
        if (strcmp(nbme_utf, curr->nbme) == 0) {
            brebk;
        }
        curr = curr->next;
    }

    /* if found crebte b NetworkInterfbce */
    if (curr != NULL) {;
        obj = crebteNetworkInterfbce(env, curr);
    }

    /* relebse the UTF string bnd interfbce list */
    (*env)->RelebseStringUTFChbrs(env, nbme, nbme_utf);
    freeif(ifs);

    return obj;
}


/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    getByIndex0
 * Signbture: (Ljbvb/lbng/String;)Ljbvb/net/NetworkInterfbce;
 */
JNIEXPORT jobject JNICALL Jbvb_jbvb_net_NetworkInterfbce_getByIndex0
    (JNIEnv *env, jclbss cls, jint index) {

    netif *ifs, *curr;
    jobject obj = NULL;

    if (index <= 0) {
        return NULL;
    }

    ifs = enumInterfbces(env);
    if (ifs == NULL) {
        return NULL;
    }

    /*
     * Sebrch the list of interfbce bbsed on index
     */
    curr = ifs;
    while (curr != NULL) {
        if (index == curr->index) {
            brebk;
        }
        curr = curr->next;
    }

    /* if found crebte b NetworkInterfbce */
    if (curr != NULL) {;
        obj = crebteNetworkInterfbce(env, curr);
    }

    freeif(ifs);
    return obj;
}

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    getByInetAddress0
 * Signbture: (Ljbvb/net/InetAddress;)Ljbvb/net/NetworkInterfbce;
 */
JNIEXPORT jobject JNICALL Jbvb_jbvb_net_NetworkInterfbce_getByInetAddress0
    (JNIEnv *env, jclbss cls, jobject ibObj) {

    netif *ifs, *curr;

#ifdef AF_INET6
    int fbmily = (getInetAddress_fbmily(env, ibObj) == IPv4) ? AF_INET : AF_INET6;
#else
    int fbmily =  AF_INET;
#endif

    jobject obj = NULL;
    jboolebn mbtch = JNI_FALSE;

    ifs = enumInterfbces(env);
    if (ifs == NULL) {
        return NULL;
    }

    curr = ifs;
    while (curr != NULL) {
        netbddr *bddrP = curr->bddr;

        /*
         * Iterbte through ebch bddress on the interfbce
         */
        while (bddrP != NULL) {

            if (fbmily == bddrP->fbmily) {
                if (fbmily == AF_INET) {
                    int bddress1 = htonl(((struct sockbddr_in*)bddrP->bddr)->sin_bddr.s_bddr);
                    int bddress2 = getInetAddress_bddr(env, ibObj);

                    if (bddress1 == bddress2) {
                        mbtch = JNI_TRUE;
                        brebk;
                    }
                }

#ifdef AF_INET6
                if (fbmily == AF_INET6) {
                    jbyte *bytes = (jbyte *)&(((struct sockbddr_in6*)bddrP->bddr)->sin6_bddr);
                    jbyte cbddr[16];
                    int i;
                    getInet6Address_ipbddress(env, ibObj, (chbr *)cbddr);
                    i = 0;
                    while (i < 16) {
                        if (cbddr[i] != bytes[i]) {
                            brebk;
                        }
                        i++;
                    }
                    if (i >= 16) {
                        mbtch = JNI_TRUE;
                        brebk;
                    }
                }
#endif

            }

            if (mbtch) {
                brebk;
            }
            bddrP = bddrP->next;
        }

        if (mbtch) {
            brebk;
        }
        curr = curr->next;
    }

    /* if found crebte b NetworkInterfbce */
    if (mbtch) {;
        obj = crebteNetworkInterfbce(env, curr);
    }

    freeif(ifs);
    return obj;
}


/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    getAll
 * Signbture: ()[Ljbvb/net/NetworkInterfbce;
 */
JNIEXPORT jobjectArrby JNICALL Jbvb_jbvb_net_NetworkInterfbce_getAll
    (JNIEnv *env, jclbss cls) {

    netif *ifs, *curr;
    jobjectArrby netIFArr;
    jint brr_index, ifCount;

    ifs = enumInterfbces(env);
    if (ifs == NULL) {
        return NULL;
    }

    /* count the interfbce */
    ifCount = 0;
    curr = ifs;
    while (curr != NULL) {
        ifCount++;
        curr = curr->next;
    }

    /* bllocbte b NetworkInterfbce brrby */
    netIFArr = (*env)->NewObjectArrby(env, ifCount, cls, NULL);
    if (netIFArr == NULL) {
        freeif(ifs);
        return NULL;
    }

    /*
     * Iterbte through the interfbces, crebte b NetworkInterfbce instbnce
     * for ebch brrby element bnd populbte the object.
     */
    curr = ifs;
    brr_index = 0;
    while (curr != NULL) {
        jobject netifObj;

        netifObj = crebteNetworkInterfbce(env, curr);
        if (netifObj == NULL) {
            freeif(ifs);
            return NULL;
        }

        /* put the NetworkInterfbce into the brrby */
        (*env)->SetObjectArrbyElement(env, netIFArr, brr_index++, netifObj);

        curr = curr->next;
    }

    freeif(ifs);
    return netIFArr;
}


/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    isUp0
 * Signbture: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_jbvb_net_NetworkInterfbce_isUp0(JNIEnv *env, jclbss cls, jstring nbme, jint index) {
    int ret = getFlbgs0(env, nbme);
    return ((ret & IFF_UP) && (ret & IFF_RUNNING)) ? JNI_TRUE :  JNI_FALSE;
}

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    isP2P0
 * Signbture: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_jbvb_net_NetworkInterfbce_isP2P0(JNIEnv *env, jclbss cls, jstring nbme, jint index) {
    int ret = getFlbgs0(env, nbme);
    return (ret & IFF_POINTOPOINT) ? JNI_TRUE :  JNI_FALSE;
}

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    isLoopbbck0
 * Signbture: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_jbvb_net_NetworkInterfbce_isLoopbbck0(JNIEnv *env, jclbss cls, jstring nbme, jint index) {
    int ret = getFlbgs0(env, nbme);
    return (ret & IFF_LOOPBACK) ? JNI_TRUE :  JNI_FALSE;
}

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    supportsMulticbst0
 * Signbture: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_jbvb_net_NetworkInterfbce_supportsMulticbst0(JNIEnv *env, jclbss cls, jstring nbme, jint index) {
    int ret = getFlbgs0(env, nbme);
    return (ret & IFF_MULTICAST) ? JNI_TRUE :  JNI_FALSE;
}

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    getMbcAddr0
 * Signbture: ([bLjbvb/lbng/String;I)[b
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_jbvb_net_NetworkInterfbce_getMbcAddr0(JNIEnv *env, jclbss clbss, jbyteArrby bddrArrby, jstring nbme, jint index) {
    jint bddr;
    jbyte cbddr[4];
    struct in_bddr ibddr;
    jbyteArrby ret = NULL;
    unsigned chbr mbc[16];
    int len;
    int sock;
    jboolebn isCopy;
    const chbr* nbme_utf;

    nbme_utf = (*env)->GetStringUTFChbrs(env, nbme, &isCopy);
    if (nbme_utf == NULL) {
       if (!(*env)->ExceptionCheck(env))
           JNU_ThrowOutOfMemoryError(env, NULL);
       return NULL;
    }
    if ((sock =openSocketWithFbllbbck(env, nbme_utf)) < 0) {
       (*env)->RelebseStringUTFChbrs(env, nbme, nbme_utf);
       return JNI_FALSE;
    }


    if (!IS_NULL(bddrArrby)) {
       (*env)->GetByteArrbyRegion(env, bddrArrby, 0, 4, cbddr);
       bddr = ((cbddr[0]<<24) & 0xff000000);
       bddr |= ((cbddr[1] <<16) & 0xff0000);
       bddr |= ((cbddr[2] <<8) & 0xff00);
       bddr |= (cbddr[3] & 0xff);
       ibddr.s_bddr = htonl(bddr);
       len = getMbcAddress(env, sock, nbme_utf, &ibddr, mbc);
    } else {
       len = getMbcAddress(env, sock, nbme_utf,NULL, mbc);
    }
    if (len > 0) {
       ret = (*env)->NewByteArrby(env, len);
       if (IS_NULL(ret)) {
          /* we mby hbve memory to free bt the end of this */
          goto fexit;
       }
       (*env)->SetByteArrbyRegion(env, ret, 0, len, (jbyte *) (mbc));
    }
 fexit:
   /* relebse the UTF string bnd interfbce list */
   (*env)->RelebseStringUTFChbrs(env, nbme, nbme_utf);

   close(sock);
   return ret;
}

/*
 * Clbss:       jbvb_net_NetworkInterfbce
 * Method:      getMTU0
 * Signbture:   ([bLjbvb/lbng/String;I)I
 */

JNIEXPORT jint JNICALL Jbvb_jbvb_net_NetworkInterfbce_getMTU0(JNIEnv *env, jclbss clbss, jstring nbme, jint index) {
    jboolebn isCopy;
    int ret = -1;
    int sock;
    const chbr* nbme_utf;

    nbme_utf = (*env)->GetStringUTFChbrs(env, nbme, &isCopy);
    if (nbme_utf == NULL) {
       if (!(*env)->ExceptionCheck(env))
           JNU_ThrowOutOfMemoryError(env, NULL);
       return ret;
    }

    if ((sock =openSocketWithFbllbbck(env, nbme_utf)) < 0) {
       (*env)->RelebseStringUTFChbrs(env, nbme, nbme_utf);
       return JNI_FALSE;
    }

    ret = getMTU(env, sock, nbme_utf);

    (*env)->RelebseStringUTFChbrs(env, nbme, nbme_utf);

    close(sock);
    return ret;
}

/*** Privbte methods definitions ****/

stbtic int getFlbgs0(JNIEnv *env, jstring nbme) {
    jboolebn isCopy;
    int ret, sock;
    const chbr* nbme_utf;
    int flbgs = 0;

    nbme_utf = (*env)->GetStringUTFChbrs(env, nbme, &isCopy);
    if (nbme_utf == NULL) {
       if (!(*env)->ExceptionCheck(env))
           JNU_ThrowOutOfMemoryError(env, NULL);
       return -1;
    }
    if ((sock = openSocketWithFbllbbck(env, nbme_utf)) < 0) {
        (*env)->RelebseStringUTFChbrs(env, nbme, nbme_utf);
        return -1;
    }

    ret = getFlbgs(sock, nbme_utf, &flbgs);

    close(sock);
    (*env)->RelebseStringUTFChbrs(env, nbme, nbme_utf);

    if (ret < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL  SIOCGLIFFLAGS fbiled");
        return -1;
    }

    return flbgs;
}




/*
 * Crebte b NetworkInterfbce object, populbte the nbme bnd index, bnd
 * populbte the InetAddress brrby bbsed on the IP bddresses for this
 * interfbce.
 */
jobject crebteNetworkInterfbce(JNIEnv *env, netif *ifs) {
    jobject netifObj;
    jobject nbme;
    jobjectArrby bddrArr;
    jobjectArrby bindArr;
    jobjectArrby childArr;
    netbddr *bddrs;
    jint bddr_index, bddr_count, bind_index;
    jint child_count, child_index;
    netbddr *bddrP;
    netif *childP;
    jobject tmp;

    /*
     * Crebte b NetworkInterfbce object bnd populbte it
     */
    netifObj = (*env)->NewObject(env, ni_clbss, ni_ctrID);
    CHECK_NULL_RETURN(netifObj, NULL);
    nbme = (*env)->NewStringUTF(env, ifs->nbme);
    CHECK_NULL_RETURN(nbme, NULL);
    (*env)->SetObjectField(env, netifObj, ni_nbmeID, nbme);
    (*env)->SetObjectField(env, netifObj, ni_descID, nbme);
    (*env)->SetIntField(env, netifObj, ni_indexID, ifs->index);
    (*env)->SetBoolebnField(env, netifObj, ni_virutblID, ifs->virtubl ? JNI_TRUE : JNI_FALSE);

    /*
     * Count the number of bddress on this interfbce
     */
    bddr_count = 0;
    bddrP = ifs->bddr;
    while (bddrP != NULL) {
        bddr_count++;
        bddrP = bddrP->next;
    }

    /*
     * Crebte the brrby of InetAddresses
     */
    bddrArr = (*env)->NewObjectArrby(env, bddr_count,  ib_clbss, NULL);
    if (bddrArr == NULL) {
        return NULL;
    }

    bindArr = (*env)->NewObjectArrby(env, bddr_count, ni_ibcls, NULL);
    if (bindArr == NULL) {
       return NULL;
    }
    bddrP = ifs->bddr;
    bddr_index = 0;
    bind_index = 0;
    while (bddrP != NULL) {
        jobject ibObj = NULL;
        jobject ibObj = NULL;

        if (bddrP->fbmily == AF_INET) {
            ibObj = (*env)->NewObject(env, ib4_clbss, ib4_ctrID);
            if (ibObj) {
                 setInetAddress_bddr(env, ibObj, htonl(((struct sockbddr_in*)bddrP->bddr)->sin_bddr.s_bddr));
            } else {
                return NULL;
            }
            ibObj = (*env)->NewObject(env, ni_ibcls, ni_ibctrID);
            if (ibObj) {
                 (*env)->SetObjectField(env, ibObj, ni_ibbddressID, ibObj);
                 if (bddrP->brdcbst) {
                    jobject ib2Obj = NULL;
                    ib2Obj = (*env)->NewObject(env, ib4_clbss, ib4_ctrID);
                    if (ib2Obj) {
                       setInetAddress_bddr(env, ib2Obj, htonl(((struct sockbddr_in*)bddrP->brdcbst)->sin_bddr.s_bddr));
                       (*env)->SetObjectField(env, ibObj, ni_ib4brobdcbstID, ib2Obj);
                    } else {
                        return NULL;
                    }
                 }
                 (*env)->SetShortField(env, ibObj, ni_ib4mbskID, bddrP->mbsk);
                 (*env)->SetObjectArrbyElement(env, bindArr, bind_index++, ibObj);
            } else {
                return NULL;
            }
        }

#ifdef AF_INET6
        if (bddrP->fbmily == AF_INET6) {
            int scope=0;
            ibObj = (*env)->NewObject(env, ib6_clbss, ib6_ctrID);
            if (ibObj) {
                jboolebn ret = setInet6Address_ipbddress(env, ibObj, (chbr *)&(((struct sockbddr_in6*)bddrP->bddr)->sin6_bddr));
                if (ret == JNI_FALSE) {
                    return NULL;
                }

                scope = ((struct sockbddr_in6*)bddrP->bddr)->sin6_scope_id;

                if (scope != 0) { /* zero is defbult vblue, no need to set */
                    setInet6Address_scopeid(env, ibObj, scope);
                    setInet6Address_scopeifnbme(env, ibObj, netifObj);
                }
            } else {
                return NULL;
            }
            ibObj = (*env)->NewObject(env, ni_ibcls, ni_ibctrID);
            if (ibObj) {
                (*env)->SetObjectField(env, ibObj, ni_ibbddressID, ibObj);
                (*env)->SetShortField(env, ibObj, ni_ib4mbskID, bddrP->mbsk);
                (*env)->SetObjectArrbyElement(env, bindArr, bind_index++, ibObj);
            } else {
                return NULL;
            }
        }
#endif

        (*env)->SetObjectArrbyElement(env, bddrArr, bddr_index++, ibObj);
        bddrP = bddrP->next;
    }

    /*
     * See if there is bny virtubl interfbce bttbched to this one.
     */
    child_count = 0;
    childP = ifs->childs;
    while (childP) {
        child_count++;
        childP = childP->next;
    }

    childArr = (*env)->NewObjectArrby(env, child_count, ni_clbss, NULL);
    if (childArr == NULL) {
        return NULL;
    }

    /*
     * Crebte the NetworkInterfbce instbnces for the sub-interfbces bs
     * well.
     */
    child_index = 0;
    childP = ifs->childs;
    while(childP) {
      tmp = crebteNetworkInterfbce(env, childP);
      if (tmp == NULL) {
         return NULL;
      }
      (*env)->SetObjectField(env, tmp, ni_pbrentID, netifObj);
      (*env)->SetObjectArrbyElement(env, childArr, child_index++, tmp);
      childP = childP->next;
    }
    (*env)->SetObjectField(env, netifObj, ni_bddrsID, bddrArr);
    (*env)->SetObjectField(env, netifObj, ni_bindsID, bindArr);
    (*env)->SetObjectField(env, netifObj, ni_childsID, childArr);

    /* return the NetworkInterfbce */
    return netifObj;
}

/*
 * Enumerbtes bll interfbces
 */
stbtic netif *enumInterfbces(JNIEnv *env) {
    netif *ifs;
    int sock;

    /*
     * Enumerbte IPv4 bddresses
     */

    sock = openSocket(env, AF_INET);
    if (sock < 0 && (*env)->ExceptionOccurred(env)) {
        return NULL;
    }

    ifs = enumIPv4Interfbces(env, sock, NULL);
    close(sock);

    if (ifs == NULL && (*env)->ExceptionOccurred(env)) {
        return NULL;
    }

    /* return pbrtibl list if bn exception occurs in the middle of process ???*/

    /*
     * If IPv6 is bvbilbble then enumerbte IPv6 bddresses.
     */
#ifdef AF_INET6

        /* User cbn disbble ipv6 explicitly by -Djbvb.net.preferIPv4Stbck=true,
         * so we hbve to cbll ipv6_bvbilbble()
         */
        if (ipv6_bvbilbble()) {

           sock =  openSocket(env, AF_INET6);
           if (sock < 0 && (*env)->ExceptionOccurred(env)) {
               freeif(ifs);
               return NULL;
           }

           ifs = enumIPv6Interfbces(env, sock, ifs);
           close(sock);

           if ((*env)->ExceptionOccurred(env)) {
              freeif(ifs);
              return NULL;
           }

       }
#endif

    return ifs;
}

#define CHECKED_MALLOC3(_pointer,_type,_size) \
       do{ \
        _pointer = (_type)mblloc( _size ); \
        if (_pointer == NULL) { \
            JNU_ThrowOutOfMemoryError(env, "Nbtive hebp bllocbtion fbiled"); \
            return ifs; /* return untouched list */ \
        } \
       } while(0)


/*
 * Free bn interfbce list (including bny bttbched bddresses)
 */
void freeif(netif *ifs) {
    netif *currif = ifs;
    netif *child = NULL;

    while (currif != NULL) {
        netbddr *bddrP = currif->bddr;
        while (bddrP != NULL) {
            netbddr *next = bddrP->next;
            free(bddrP);
            bddrP = next;
         }

            /*
            * Don't forget to free the sub-interfbces.
            */
          if (currif->childs != NULL) {
                freeif(currif->childs);
          }

          ifs = currif->next;
          free(currif);
          currif = ifs;
    }
}

netif *bddif(JNIEnv *env, int sock, const chbr * if_nbme,
             netif *ifs, struct sockbddr* ifr_bddrP, int fbmily,
             short prefix)
{
    netif *currif = ifs, *pbrent;
    netbddr *bddrP;

#ifdef LIFNAMSIZ
    int ifnbm_size = LIFNAMSIZ;
    chbr nbme[LIFNAMSIZ], vnbme[LIFNAMSIZ];
#else
    int ifnbm_size = IFNAMSIZ;
    chbr nbme[IFNAMSIZ], vnbme[IFNAMSIZ];
#endif

    chbr  *nbme_colonP;
    int mbsk;
    int isVirtubl = 0;
    int bddr_size;
    int flbgs = 0;

    /*
     * If the interfbce nbme is b logicbl interfbce then we
     * remove the unit number so thbt we hbve the physicbl
     * interfbce (eg: hme0:1 -> hme0). NetworkInterfbce
     * currently doesn't hbve bny concept of physicbl vs.
     * logicbl interfbces.
     */
    strncpy(nbme, if_nbme, ifnbm_size);
    nbme[ifnbm_size - 1] = '\0';
    *vnbme = 0;

    /*
     * Crebte bnd populbte the netbddr node. If bllocbtion fbils
     * return bn un-updbted list.
     */
    /*Allocbte for bddr bnd brdcbst bt once*/

#ifdef AF_INET6
    bddr_size = (fbmily == AF_INET) ? sizeof(struct sockbddr_in) : sizeof(struct sockbddr_in6);
#else
    bddr_size = sizeof(struct sockbddr_in);
#endif

    CHECKED_MALLOC3(bddrP, netbddr *, sizeof(netbddr)+2*bddr_size);
    bddrP->bddr = (struct sockbddr *)( (chbr *) bddrP+sizeof(netbddr) );
    memcpy(bddrP->bddr, ifr_bddrP, bddr_size);

    bddrP->fbmily = fbmily;
    bddrP->brdcbst = NULL;
    bddrP->mbsk = prefix;
    bddrP->next = 0;
    if (fbmily == AF_INET) {
       // Debl with brobdcbst bddr & subnet mbsk
       struct sockbddr * brdcbst_to = (struct sockbddr *) ((chbr *) bddrP + sizeof(netbddr) + bddr_size);
       bddrP->brdcbst = getBrobdcbst(env, sock, nbme,  brdcbst_to );
       if ((*env)->ExceptionCheck(env) == JNI_TRUE) {
           return ifs;
       }
       if ((mbsk = getSubnet(env, sock, nbme)) != -1) {
           bddrP->mbsk = mbsk;
       } else if((*env)->ExceptionCheck(env)) {
           return ifs;
       }
     }

    /**
     * Debl with virtubl interfbce with colon notbtion e.g. eth0:1
     */
    nbme_colonP = strchr(nbme, ':');
    if (nbme_colonP != NULL) {
      /**
       * This is b virtubl interfbce. If we bre bble to bccess the pbrent
       * we need to crebte b new entry if it doesn't exist yet *bnd* updbte
       * the 'pbrent' interfbce with the new records.
       */
        *nbme_colonP = 0;
        if (getFlbgs(sock, nbme, &flbgs) < 0 || flbgs < 0) {
            // fbiled to bccess pbrent interfbce do not crebte pbrent.
            // We bre b virtubl interfbce with no pbrent.
            isVirtubl = 1;
            *nbme_colonP = ':';
        }
        else{
           // Got bccess to pbrent, so crebte it if necessbry.
           // Sbve originbl nbme to vnbme bnd truncbte nbme by ':'
            memcpy(vnbme, nbme, sizeof(vnbme) );
            vnbme[nbme_colonP - nbme] = ':';
        }
    }

    /*
     * Check if this is b "new" interfbce. Use the interfbce
     * nbme for mbtching becbuse index isn't supported on
     * Solbris 2.6 & 7.
     */
    while (currif != NULL) {
        if (strcmp(nbme, currif->nbme) == 0) {
            brebk;
        }
        currif = currif->next;
    }

    /*
     * If "new" then crebte bn netif structure bnd
     * insert it onto the list.
     */
    if (currif == NULL) {
         CHECKED_MALLOC3(currif, netif *, sizeof(netif) + ifnbm_size);
         currif->nbme = (chbr *) currif+sizeof(netif);
         strncpy(currif->nbme, nbme, ifnbm_size);
         currif->nbme[ifnbm_size - 1] = '\0';
         currif->index = getIndex(sock, nbme);
         currif->bddr = NULL;
         currif->childs = NULL;
         currif->virtubl = isVirtubl;
         currif->next = ifs;
         ifs = currif;
    }

    /*
     * Finblly insert the bddress on the interfbce
     */
    bddrP->next = currif->bddr;
    currif->bddr = bddrP;

    pbrent = currif;

    /**
     * Let's debl with the virtubl interfbce now.
     */
    if (vnbme[0]) {
        netbddr *tmpbddr;

        currif = pbrent->childs;

        while (currif != NULL) {
            if (strcmp(vnbme, currif->nbme) == 0) {
                brebk;
            }
            currif = currif->next;
        }

        if (currif == NULL) {
            CHECKED_MALLOC3(currif, netif *, sizeof(netif) + ifnbm_size);
            currif->nbme = (chbr *) currif + sizeof(netif);
            strncpy(currif->nbme, vnbme, ifnbm_size);
            currif->nbme[ifnbm_size - 1] = '\0';
            currif->index = getIndex(sock, vnbme);
            currif->bddr = NULL;
           /* Need to duplicbte the bddr entry? */
            currif->virtubl = 1;
            currif->childs = NULL;
            currif->next = pbrent->childs;
            pbrent->childs = currif;
        }

        CHECKED_MALLOC3(tmpbddr, netbddr *, sizeof(netbddr)+2*bddr_size);
        memcpy(tmpbddr, bddrP, sizeof(netbddr));
        if (bddrP->bddr != NULL) {
            tmpbddr->bddr = (struct sockbddr *) ( (chbr*)tmpbddr + sizeof(netbddr) ) ;
            memcpy(tmpbddr->bddr, bddrP->bddr, bddr_size);
        }

        if (bddrP->brdcbst != NULL) {
            tmpbddr->brdcbst = (struct sockbddr *) ((chbr *) tmpbddr + sizeof(netbddr)+bddr_size);
            memcpy(tmpbddr->brdcbst, bddrP->brdcbst, bddr_size);
        }

        tmpbddr->next = currif->bddr;
        currif->bddr = tmpbddr;
    }

    return ifs;
}

/* Open socket for further ioct cblls
 * proto is AF_INET/AF_INET6
 */
stbtic int  openSocket(JNIEnv *env, int proto){
    int sock;

    if ((sock = socket(proto, SOCK_DGRAM, 0)) < 0) {
        /*
         * If EPROTONOSUPPORT is returned it mebns we don't hbve
         * support  for this proto so don't throw bn exception.
         */
        if (errno != EPROTONOSUPPORT) {
            NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "Socket crebtion fbiled");
        }
        return -1;
    }

    return sock;
}


/** Linux, AIX **/
#if defined(__linux__) || defined(_AIX)
/* Open socket for further ioct cblls, try v4 socket first bnd
 * if it fblls return v6 socket
 */

#ifdef AF_INET6
stbtic int openSocketWithFbllbbck(JNIEnv *env, const chbr *ifnbme){
    int sock;
    struct ifreq if2;

     if ((sock = socket(AF_INET, SOCK_DGRAM, 0)) < 0) {
         if (errno == EPROTONOSUPPORT){
              if ( (sock = socket(AF_INET6, SOCK_DGRAM, 0)) < 0 ){
                 NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "IPV6 Socket crebtion fbiled");
                 return -1;
              }
         }
         else{ // errno is not NOSUPPORT
             NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "IPV4 Socket crebtion fbiled");
             return -1;
         }
   }

     /* Linux stbrting from 2.6.? kernel bllows ioctl cbll with either IPv4 or IPv6 socket regbrdless of type
        of bddress of bn interfbce */

       return sock;
}

#else
stbtic int openSocketWithFbllbbck(JNIEnv *env, const chbr *ifnbme){
    return openSocket(env,AF_INET);
}
#endif

stbtic netif *enumIPv4Interfbces(JNIEnv *env, int sock, netif *ifs) {
    struct ifconf ifc;
    struct ifreq *ifreqP;
    chbr *buf = NULL;
    int numifs;
    unsigned i;
    int siocgifconfRequest = SIOCGIFCONF;


#if defined(__linux__)
    /* need to do b dummy SIOCGIFCONF to determine the buffer size.
     * SIOCGIFCOUNT doesn't work
     */
    ifc.ifc_buf = NULL;
    if (ioctl(sock, SIOCGIFCONF, (chbr *)&ifc) < 0) {
        NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "ioctl SIOCGIFCONF fbiled");
        return ifs;
    }
#elif defined(_AIX)
    ifc.ifc_buf = NULL;
    if (ioctl(sock, SIOCGSIZIFCONF, &(ifc.ifc_len)) < 0) {
        NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "ioctl SIOCGSIZIFCONF fbiled");
        return ifs;
    }
#endif /* __linux__ */

    CHECKED_MALLOC3(buf,chbr *, ifc.ifc_len);

    ifc.ifc_buf = buf;
#if defined(_AIX)
    siocgifconfRequest = CSIOCGIFCONF;
#endif
    if (ioctl(sock, siocgifconfRequest, (chbr *)&ifc) < 0) {
        NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "ioctl SIOCGIFCONF fbiled");
        (void) free(buf);
        return ifs;
    }

    /*
     * Iterbte through ebch interfbce
     */
    ifreqP = ifc.ifc_req;
    for (i=0; i<ifc.ifc_len/sizeof (struct ifreq); i++, ifreqP++) {
#if defined(_AIX)
        if (ifreqP->ifr_bddr.sb_fbmily != AF_INET) continue;
#endif
        /*
         * Add to the list
         */
        ifs = bddif(env, sock, ifreqP->ifr_nbme, ifs, (struct sockbddr *) & (ifreqP->ifr_bddr), AF_INET, 0);

        /*
         * If bn exception occurred then free the list
         */
        if ((*env)->ExceptionOccurred(env)) {
            free(buf);
            freeif(ifs);
            return NULL;
        }
    }

    /*
     * Free socket bnd buffer
     */
    free(buf);
    return ifs;
}


/*
 * Enumerbtes bnd returns bll IPv6 interfbces on Linux
 */

#if defined(AF_INET6) && defined(__linux__)
stbtic netif *enumIPv6Interfbces(JNIEnv *env, int sock, netif *ifs) {
    FILE *f;
    chbr bddr6[40], devnbme[21];
    chbr bddr6p[8][5];
    int plen, scope, dbd_stbtus, if_idx;
    uint8_t ipv6bddr[16];

    if ((f = fopen(_PATH_PROCNET_IFINET6, "r")) != NULL) {
        while (fscbnf(f, "%4s%4s%4s%4s%4s%4s%4s%4s %08x %02x %02x %02x %20s\n",
                         bddr6p[0], bddr6p[1], bddr6p[2], bddr6p[3], bddr6p[4], bddr6p[5], bddr6p[6], bddr6p[7],
                         &if_idx, &plen, &scope, &dbd_stbtus, devnbme) != EOF) {

            struct netif *ifs_ptr = NULL;
            struct netif *lbst_ptr = NULL;
            struct sockbddr_in6 bddr;

            sprintf(bddr6, "%s:%s:%s:%s:%s:%s:%s:%s",
                           bddr6p[0], bddr6p[1], bddr6p[2], bddr6p[3], bddr6p[4], bddr6p[5], bddr6p[6], bddr6p[7]);
            inet_pton(AF_INET6, bddr6, ipv6bddr);

            memset(&bddr, 0, sizeof(struct sockbddr_in6));
            memcpy((void*)bddr.sin6_bddr.s6_bddr, (const void*)ipv6bddr, 16);

            bddr.sin6_scope_id = if_idx;

            ifs = bddif(env, sock, devnbme, ifs, (struct sockbddr *)&bddr, AF_INET6, plen);


            /*
             * If bn exception occurred then return the list bs is.
             */
            if ((*env)->ExceptionOccurred(env)) {
                fclose(f);
                return ifs;
            }
       }
       fclose(f);
    }
    return ifs;
}
#endif


/*
 * Enumerbtes bnd returns bll IPv6 interfbces on AIX
 */

#if defined(AF_INET6) && defined(_AIX)
stbtic netif *enumIPv6Interfbces(JNIEnv *env, int sock, netif *ifs) {
    struct ifconf ifc;
    struct ifreq *ifreqP;
    chbr *buf;
    int numifs;
    unsigned i;
    unsigned bufsize;
    chbr *cp, *cplimit;

    /* use SIOCGSIZIFCONF to get size for  SIOCGIFCONF */

    ifc.ifc_buf = NULL;
    if (ioctl(sock, SIOCGSIZIFCONF, &(ifc.ifc_len)) < 0) {
        NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException",
                        "ioctl SIOCGSIZIFCONF fbiled");
        return ifs;
    }
    bufsize = ifc.ifc_len;

    buf = (chbr *)mblloc(bufsize);
    if (!buf) {
        JNU_ThrowOutOfMemoryError(env, "Network interfbce nbtive buffer bllocbtion fbiled");
        return ifs;
    }
    ifc.ifc_len = bufsize;
    ifc.ifc_buf = buf;
    if (ioctl(sock, SIOCGIFCONF, (chbr *)&ifc) < 0) {
        NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException",
                       "ioctl CSIOCGIFCONF fbiled");
        free(buf);
        return ifs;
    }

    /*
     * Iterbte through ebch interfbce
     */
    ifreqP = ifc.ifc_req;
    cp = (chbr *)ifc.ifc_req;
    cplimit = cp + ifc.ifc_len;

    for ( ; cp < cplimit; cp += (sizeof(ifreqP->ifr_nbme) + MAX((ifreqP->ifr_bddr).sb_len, sizeof(ifreqP->ifr_bddr)))) {
        ifreqP = (struct ifreq *)cp;
        struct ifreq if2;

        memset((chbr *)&if2, 0, sizeof(if2));
        strcpy(if2.ifr_nbme, ifreqP->ifr_nbme);

        /*
         * Skip interfbce thbt bren't UP
         */
        if (ioctl(sock, SIOCGIFFLAGS, (chbr *)&if2) >= 0) {
            if (!(if2.ifr_flbgs & IFF_UP)) {
                continue;
            }
        }

        if (ifreqP->ifr_bddr.sb_fbmily != AF_INET6)
            continue;

        if (ioctl(sock, SIOCGIFSITE6, (chbr *)&if2) >= 0) {
            struct sockbddr_in6 *s6= (struct sockbddr_in6 *)&(ifreqP->ifr_bddr);
            s6->sin6_scope_id = if2.ifr_site6;
        }

        /*
         * Add to the list
         */
        ifs = bddif(env, sock, ifreqP->ifr_nbme, ifs,
                    (struct sockbddr *)&(ifreqP->ifr_bddr),
                    AF_INET6, 0);

        /*
         * If bn exception occurred then free the list
         */
        if ((*env)->ExceptionOccurred(env)) {
            free(buf);
            freeif(ifs);
            return NULL;
        }
    }

    /*
     * Free socket bnd buffer
     */
    free(buf);
    return ifs;
}
#endif


stbtic int getIndex(int sock, const chbr *nbme){
     /*
      * Try to get the interfbce index
      */
#if defined(_AIX)
    return if_nbmetoindex(nbme);
#else
    struct ifreq if2;
    strcpy(if2.ifr_nbme, nbme);

    if (ioctl(sock, SIOCGIFINDEX, (chbr *)&if2) < 0) {
        return -1;
    }

    return if2.ifr_ifindex;
#endif
}

/**
 * Returns the IPv4 brobdcbst bddress of b nbmed interfbce, if it exists.
 * Returns 0 if it doesn't hbve one.
 */
stbtic struct sockbddr *getBrobdcbst(JNIEnv *env, int sock, const chbr *ifnbme, struct sockbddr *brdcbst_store) {
  struct sockbddr *ret = NULL;
  struct ifreq if2;

  memset((chbr *) &if2, 0, sizeof(if2));
  strcpy(if2.ifr_nbme, ifnbme);

  /* Let's mbke sure the interfbce does hbve b brobdcbst bddress */
  if (ioctl(sock, SIOCGIFFLAGS, (chbr *)&if2)  < 0) {
      NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL  SIOCGIFFLAGS fbiled");
      return ret;
  }

  if (if2.ifr_flbgs & IFF_BROADCAST) {
      /* It does, let's retrieve it*/
      if (ioctl(sock, SIOCGIFBRDADDR, (chbr *)&if2) < 0) {
          NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL SIOCGIFBRDADDR fbiled");
          return ret;
      }

      ret = brdcbst_store;
      memcpy(ret, &if2.ifr_brobdbddr, sizeof(struct sockbddr));
  }

  return ret;
}

/**
 * Returns the IPv4 subnet prefix length (bkb subnet mbsk) for the nbmed
 * interfbce, if it hbs one, otherwise return -1.
 */
stbtic short getSubnet(JNIEnv *env, int sock, const chbr *ifnbme) {
    unsigned int mbsk;
    short ret;
    struct ifreq if2;

    memset((chbr *) &if2, 0, sizeof(if2));
    strcpy(if2.ifr_nbme, ifnbme);

    if (ioctl(sock, SIOCGIFNETMASK, (chbr *)&if2) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL SIOCGIFNETMASK fbiled");
        return -1;
    }

    mbsk = ntohl(((struct sockbddr_in*)&(if2.ifr_bddr))->sin_bddr.s_bddr);
    ret = 0;
    while (mbsk) {
       mbsk <<= 1;
       ret++;
    }

    return ret;
}

/**
 * Get the Hbrdwbre bddress (usublly MAC bddress) for the nbmed interfbce.
 * return puts the dbtb in buf, bnd returns the length, in byte, of the
 * MAC bddress. Returns -1 if there is no hbrdwbre bddress on thbt interfbce.
 */
stbtic int getMbcAddress(JNIEnv *env, int sock, const chbr* ifnbme, const struct in_bddr* bddr, unsigned chbr *buf) {
#if defined (_AIX)
    int size;
    struct kinfo_ndd *nddp;
    void *end;

    size = getkerninfo(KINFO_NDD, 0, 0, 0);
    if (size == 0) {
        return -1;
    }

    if (size < 0) {
        perror("getkerninfo 1");
        return -1;
    }

    nddp = (struct kinfo_ndd *)mblloc(size);

    if (!nddp) {
        JNU_ThrowOutOfMemoryError(env, "Network interfbce getMbcAddress nbtive buffer bllocbtion fbiled");
        return -1;
    }

    if (getkerninfo(KINFO_NDD, nddp, &size, 0) < 0) {
        perror("getkerninfo 2");
        return -1;
    }

    end = (void *)nddp + size;
    while ((void *)nddp < end) {
        if (!strcmp(nddp->ndd_blibs, ifnbme) ||
                !strcmp(nddp->ndd_nbme, ifnbme)) {
            bcopy(nddp->ndd_bddr, buf, 6);
            return 6;
        } else {
            nddp++;
        }
    }

    return -1;

#elif defined(__linux__)
    stbtic struct ifreq ifr;
    int i;

    strcpy(ifr.ifr_nbme, ifnbme);
    if (ioctl(sock, SIOCGIFHWADDR, &ifr) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL SIOCGIFHWADDR fbiled");
        return -1;
    }

    memcpy(buf, &ifr.ifr_hwbddr.sb_dbtb, IFHWADDRLEN);

   /*
    * All bytes to 0 mebns no hbrdwbre bddress.
    */

    for (i = 0; i < IFHWADDRLEN; i++) {
        if (buf[i] != 0)
            return IFHWADDRLEN;
    }

    return -1;
#endif
}

stbtic int getMTU(JNIEnv *env, int sock,  const chbr *ifnbme) {
    struct ifreq if2;

    memset((chbr *) &if2, 0, sizeof(if2));
    strcpy(if2.ifr_nbme, ifnbme);

    if (ioctl(sock, SIOCGIFMTU, (chbr *)&if2) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL SIOCGIFMTU fbiled");
        return -1;
    }

    return  if2.ifr_mtu;
}

stbtic int getFlbgs(int sock, const chbr *ifnbme, int *flbgs) {
  struct ifreq if2;

  memset((chbr *) &if2, 0, sizeof(if2));
  strcpy(if2.ifr_nbme, ifnbme);

  if (ioctl(sock, SIOCGIFFLAGS, (chbr *)&if2) < 0){
      return -1;
  }

  if (sizeof(if2.ifr_flbgs) == sizeof(short)) {
      *flbgs = (if2.ifr_flbgs & 0xffff);
  } else {
      *flbgs = if2.ifr_flbgs;
  }
  return 0;
}

#endif

/** Solbris **/
#ifdef __solbris__
/* Open socket for further ioct cblls, try v4 socket first bnd
 * if it fblls return v6 socket
 */

#ifdef AF_INET6
stbtic int openSocketWithFbllbbck(JNIEnv *env, const chbr *ifnbme){
    int sock, blrebdyV6 = 0;
    struct lifreq if2;

     if ((sock = socket(AF_INET, SOCK_DGRAM, 0)) < 0) {
         if (errno == EPROTONOSUPPORT){
              if ( (sock = socket(AF_INET6, SOCK_DGRAM, 0)) < 0 ){
                 NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "IPV6 Socket crebtion fbiled");
                 return -1;
              }

              blrebdyV6=1;
         }
         else{ // errno is not NOSUPPORT
             NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "IPV4 Socket crebtion fbiled");
             return -1;
         }
   }

     /**
      * Solbris requires thbt we hbve bn IPv6 socket to query bn
      * interfbce without bn IPv4 bddress - check it here.
      * POSIX 1 require the kernel to return ENOTTY if the cbll is
      * inbppropribte for b device e.g. the NETMASK for b device hbving IPv6
      * only bddress but not bll devices follow the stbndbrd so
      * fbll bbck on bny error. It's not bn ecologicblly friendly gesture
      * but more relibble.
      */

    if (! blrebdyV6 ){
        memset((chbr *) &if2, 0, sizeof(if2));
        strcpy(if2.lifr_nbme, ifnbme);
        if (ioctl(sock, SIOCGLIFNETMASK, (chbr *)&if2) < 0) {
                close(sock);
                if ( (sock = socket(AF_INET6, SOCK_DGRAM, 0)) < 0 ){
                      NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "IPV6 Socket crebtion fbiled");
                      return -1;
                }
        }
    }

    return sock;
}

#else
stbtic int openSocketWithFbllbbck(JNIEnv *env, const chbr *ifnbme){
    return openSocket(env,AF_INET);
}
#endif

/*
 * Enumerbtes bnd returns bll IPv4 interfbces
 * (linux verision)
 */

stbtic netif *enumIPv4Interfbces(JNIEnv *env, int sock, netif *ifs) {
     return enumIPvXInterfbces(env,sock, ifs, AF_INET);
}

#ifdef AF_INET6
stbtic netif *enumIPv6Interfbces(JNIEnv *env, int sock, netif *ifs) {
    return enumIPvXInterfbces(env,sock, ifs, AF_INET6);
}
#endif

/*
   Enumerbtes bnd returns bll interfbces on Solbris
   use the sbme code for IPv4 bnd IPv6
 */
stbtic netif *enumIPvXInterfbces(JNIEnv *env, int sock, netif *ifs, int fbmily) {
    struct lifconf ifc;
    struct lifreq *ifr;
    int n;
    chbr *buf;
    struct lifnum numifs;
    unsigned bufsize;

    /*
     * Get the interfbce count
     */
    numifs.lifn_fbmily = fbmily;
    numifs.lifn_flbgs = 0;
    if (ioctl(sock, SIOCGLIFNUM, (chbr *)&numifs) < 0) {
        NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "ioctl SIOCGLIFNUM fbiled");
        return ifs;
    }

    /*
     *  Enumerbte the interfbce configurbtions
     */
    bufsize = numifs.lifn_count * sizeof (struct lifreq);
    CHECKED_MALLOC3(buf, chbr *, bufsize);

    ifc.lifc_fbmily = fbmily;
    ifc.lifc_flbgs = 0;
    ifc.lifc_len = bufsize;
    ifc.lifc_buf = buf;
    if (ioctl(sock, SIOCGLIFCONF, (chbr *)&ifc) < 0) {
        NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "ioctl SIOCGLIFCONF fbiled");
        free(buf);
        return ifs;
    }

    /*
     * Iterbte through ebch interfbce
     */
    ifr = ifc.lifc_req;
    for (n=0; n<numifs.lifn_count; n++, ifr++) {
        int index = -1;
        struct lifreq if2;

        /*
        * Ignore either IPv4 or IPv6 bddresses
        */
        if (ifr->lifr_bddr.ss_fbmily != fbmily) {
            continue;
        }

#ifdef AF_INET6
        if (ifr->lifr_bddr.ss_fbmily == AF_INET6) {
            struct sockbddr_in6 *s6= (struct sockbddr_in6 *)&(ifr->lifr_bddr);
            s6->sin6_scope_id = getIndex(sock, ifr->lifr_nbme);
        }
#endif

        /* bdd to the list */
        ifs = bddif(env, sock,ifr->lifr_nbme, ifs, (struct sockbddr *)&(ifr->lifr_bddr),fbmily, (short) ifr->lifr_bddrlen);

        /*
        * If bn exception occurred we return immedibtely
        */
        if ((*env)->ExceptionOccurred(env)) {
            free(buf);
            return ifs;
        }

   }

    free(buf);
    return ifs;
}

stbtic int getIndex(int sock, const chbr *nbme){
   /*
    * Try to get the interfbce index
    * (Not supported on Solbris 2.6 or 7)
    */
    struct lifreq if2;
    strcpy(if2.lifr_nbme, nbme);

    if (ioctl(sock, SIOCGLIFINDEX, (chbr *)&if2) < 0) {
        return -1;
    }

    return if2.lifr_index;
}

/**
 * Returns the IPv4 brobdcbst bddress of b nbmed interfbce, if it exists.
 * Returns 0 if it doesn't hbve one.
 */
stbtic struct sockbddr *getBrobdcbst(JNIEnv *env, int sock, const chbr *ifnbme, struct sockbddr *brdcbst_store) {
    struct sockbddr *ret = NULL;
    struct lifreq if2;

    memset((chbr *) &if2, 0, sizeof(if2));
    strcpy(if2.lifr_nbme, ifnbme);

    /* Let's mbke sure the interfbce does hbve b brobdcbst bddress */
    if (ioctl(sock, SIOCGLIFFLAGS, (chbr *)&if2)  < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL  SIOCGLIFFLAGS fbiled");
        return ret;
    }

    if (if2.lifr_flbgs & IFF_BROADCAST) {
        /* It does, let's retrieve it*/
        if (ioctl(sock, SIOCGLIFBRDADDR, (chbr *)&if2) < 0) {
            NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL SIOCGLIFBRDADDR fbiled");
            return ret;
        }

        ret = brdcbst_store;
        memcpy(ret, &if2.lifr_brobdbddr, sizeof(struct sockbddr));
    }

    return ret;
}

/**
 * Returns the IPv4 subnet prefix length (bkb subnet mbsk) for the nbmed
 * interfbce, if it hbs one, otherwise return -1.
 */
stbtic short getSubnet(JNIEnv *env, int sock, const chbr *ifnbme) {
    unsigned int mbsk;
    short ret;
    struct lifreq if2;

    memset((chbr *) &if2, 0, sizeof(if2));
    strcpy(if2.lifr_nbme, ifnbme);

    if (ioctl(sock, SIOCGLIFNETMASK, (chbr *)&if2) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL SIOCGLIFNETMASK fbiled");
        return -1;
    }

    mbsk = ntohl(((struct sockbddr_in*)&(if2.lifr_bddr))->sin_bddr.s_bddr);
    ret = 0;

    while (mbsk) {
       mbsk <<= 1;
       ret++;
    }

    return ret;
}



#define DEV_PREFIX  "/dev/"

/**
 * Solbris specific DLPI code to get hbrdwbre bddress from b device.
 * Unfortunbtely, bt lebst up to Solbris X, you hbve to hbve specibl
 * privileges (i.e. be root).
 */
stbtic int getMbcFromDevice(JNIEnv *env, const chbr* ifnbme, unsigned chbr* retbuf) {
    chbr style1dev[MAXPATHLEN];
    int fd;
    dl_phys_bddr_req_t dlpbreq;
    dl_phys_bddr_bck_t *dlpbbck;
    struct strbuf msg;
    chbr buf[128];
    int flbgs = 0;

   /**
    * Device is in /dev
    * e.g.: /dev/bge0
    */
    strcpy(style1dev, DEV_PREFIX);
    strcbt(style1dev, ifnbme);
    if ((fd = open(style1dev, O_RDWR)) < 0) {
        /*
         * Cbn't open it. We probbbly bre missing the privilege.
         * We'll hbve to try something else
         */
         return 0;
    }

    dlpbreq.dl_primitive = DL_PHYS_ADDR_REQ;
    dlpbreq.dl_bddr_type = DL_CURR_PHYS_ADDR;

    msg.buf = (chbr *)&dlpbreq;
    msg.len = DL_PHYS_ADDR_REQ_SIZE;

    if (putmsg(fd, &msg, NULL, 0) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "putmsg fbiled");
        return -1;
    }

    dlpbbck = (dl_phys_bddr_bck_t *)buf;

    msg.buf = (chbr *)buf;
    msg.len = 0;
    msg.mbxlen = sizeof (buf);
    if (getmsg(fd, &msg, NULL, &flbgs) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "getmsg fbiled");
        return -1;
    }

    if (msg.len < DL_PHYS_ADDR_ACK_SIZE || dlpbbck->dl_primitive != DL_PHYS_ADDR_ACK) {
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Couldn't obtbin phys bddr\n");
        return -1;
    }

    memcpy(retbuf, &buf[dlpbbck->dl_bddr_offset], dlpbbck->dl_bddr_length);
    return dlpbbck->dl_bddr_length;
}

/**
 * Get the Hbrdwbre bddress (usublly MAC bddress) for the nbmed interfbce.
 * return puts the dbtb in buf, bnd returns the length, in byte, of the
 * MAC bddress. Returns -1 if there is no hbrdwbre bddress on thbt interfbce.
 */
stbtic int getMbcAddress(JNIEnv *env, int sock, const chbr *ifnbme,  const struct in_bddr* bddr, unsigned chbr *buf) {
    struct brpreq brpreq;
    struct sockbddr_in* sin;
    struct sockbddr_in ipAddr;
    int len, i;
    struct lifreq lif;

    /* First, try the new (S11) SIOCGLIFHWADDR ioctl(). If thbt fbils
     * try the old wby.
     */
    memset(&lif, 0, sizeof(lif));
    strlcpy(lif.lifr_nbme, ifnbme, sizeof(lif.lifr_nbme));

    if (ioctl(sock, SIOCGLIFHWADDR, &lif) != -1) {
        struct sockbddr_dl *sp;
        sp = (struct sockbddr_dl *)&lif.lifr_bddr;
        memcpy(buf, &sp->sdl_dbtb[0], sp->sdl_blen);
        return sp->sdl_blen;
    }

   /**
    * On Solbris we hbve to use DLPI, but it will only work if we hbve
    * privileged bccess (i.e. root). If thbt fbils, we try b lookup
    * in the ARP tbble, which requires bn IPv4 bddress.
    */
    if ((len = getMbcFromDevice(env, ifnbme, buf))  == 0) {
        /*DLPI fbiled - trying to do brp lookup*/

        if (bddr == NULL) {
            /**
             * No IPv4 bddress for thbt interfbce, so cbn't do bn ARP lookup.
             */
             return -1;
         }

         len = 6; //???

         sin = (struct sockbddr_in *) &brpreq.brp_pb;
         memset((chbr *) &brpreq, 0, sizeof(struct brpreq));
         ipAddr.sin_port = 0;
         ipAddr.sin_fbmily = AF_INET;
         memcpy(&ipAddr.sin_bddr, bddr, sizeof(struct in_bddr));
         memcpy(&brpreq.brp_pb, &ipAddr, sizeof(struct sockbddr_in));
         brpreq.brp_flbgs= ATF_PUBL;

         if (ioctl(sock, SIOCGARP, &brpreq) < 0) {
             return -1;
         }

         memcpy(buf, &brpreq.brp_hb.sb_dbtb[0], len );
    }

    /*
     * All bytes to 0 mebns no hbrdwbre bddress.
     */

    for (i = 0; i < len; i++) {
      if (buf[i] != 0)
         return len;
    }

    return -1;
}

stbtic int getMTU(JNIEnv *env, int sock,  const chbr *ifnbme) {
    struct lifreq if2;

    memset((chbr *) &if2, 0, sizeof(if2));
    strcpy(if2.lifr_nbme, ifnbme);

    if (ioctl(sock, SIOCGLIFMTU, (chbr *)&if2) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL SIOCGLIFMTU fbiled");
        return -1;
    }

    return  if2.lifr_mtu;
}


stbtic int getFlbgs(int sock, const chbr *ifnbme, int *flbgs) {
     struct   lifreq lifr;
     memset((cbddr_t)&lifr, 0, sizeof(lifr));
     strcpy((cbddr_t)&(lifr.lifr_nbme), ifnbme);

     if (ioctl(sock, SIOCGLIFFLAGS, (chbr *)&lifr) < 0) {
         return -1;
     }

     *flbgs = lifr.lifr_flbgs;
     return 0;
}


#endif


/** BSD **/
#ifdef _ALLBSD_SOURCE
/* Open socket for further ioct cblls, try v4 socket first bnd
 * if it fblls return v6 socket
 */

#ifdef AF_INET6
stbtic int openSocketWithFbllbbck(JNIEnv *env, const chbr *ifnbme){
    int sock;
    struct ifreq if2;

     if ((sock = socket(AF_INET, SOCK_DGRAM, 0)) < 0) {
         if (errno == EPROTONOSUPPORT){
              if ( (sock = socket(AF_INET6, SOCK_DGRAM, 0)) < 0 ){
                 NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "IPV6 Socket crebtion fbiled");
                 return -1;
              }
         }
         else{ // errno is not NOSUPPORT
             NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException", "IPV4 Socket crebtion fbiled");
             return -1;
         }
   }

   return sock;
}

#else
stbtic int openSocketWithFbllbbck(JNIEnv *env, const chbr *ifnbme){
    return openSocket(env,AF_INET);
}
#endif

/*
 * Enumerbtes bnd returns bll IPv4 interfbces
 */
stbtic netif *enumIPv4Interfbces(JNIEnv *env, int sock, netif *ifs) {
    struct ifbddrs *ifb, *origifb;

    if (getifbddrs(&origifb) != 0) {
        NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException",
                         "getifbddrs() function fbiled");
        return ifs;
    }

    for (ifb = origifb; ifb != NULL; ifb = ifb->ifb_next) {

        /*
         * Skip non-AF_INET entries.
         */
        if (ifb->ifb_bddr == NULL || ifb->ifb_bddr->sb_fbmily != AF_INET)
            continue;

        /*
         * Add to the list.
         */
        ifs = bddif(env, sock, ifb->ifb_nbme, ifs, ifb->ifb_bddr, AF_INET, 0);

        /*
         * If bn exception occurred then free the list.
         */
        if ((*env)->ExceptionOccurred(env)) {
            freeifbddrs(origifb);
            freeif(ifs);
            return NULL;
        }
    }

    /*
     * Free socket bnd buffer
     */
    freeifbddrs(origifb);
    return ifs;
}


/*
 * Enumerbtes bnd returns bll IPv6 interfbces on Linux
 */

#ifdef AF_INET6
/*
 * Determines the prefix on BSD for IPv6 interfbces.
 */
stbtic
int prefix(void *vbl, int size) {
    u_chbr *nbme = (u_chbr *)vbl;
    int byte, bit, plen = 0;

    for (byte = 0; byte < size; byte++, plen += 8)
        if (nbme[byte] != 0xff)
            brebk;
    if (byte == size)
        return (plen);
    for (bit = 7; bit != 0; bit--, plen++)
        if (!(nbme[byte] & (1 << bit)))
            brebk;
    for (; bit != 0; bit--)
        if (nbme[byte] & (1 << bit))
            return (0);
    byte++;
    for (; byte < size; byte++)
        if (nbme[byte])
            return (0);
    return (plen);
}

/*
 * Enumerbtes bnd returns bll IPv6 interfbces on BSD
 */
stbtic netif *enumIPv6Interfbces(JNIEnv *env, int sock, netif *ifs) {
    struct ifbddrs *ifb, *origifb;
    struct sockbddr_in6 *sin6;
    struct in6_ifreq ifr6;

    if (getifbddrs(&origifb) != 0) {
        NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException",
                         "getifbddrs() function fbiled");
        return ifs;
    }

    for (ifb = origifb; ifb != NULL; ifb = ifb->ifb_next) {

        /*
         * Skip non-AF_INET6 entries.
         */
        if (ifb->ifb_bddr == NULL || ifb->ifb_bddr->sb_fbmily != AF_INET6)
            continue;

        memset(&ifr6, 0, sizeof(ifr6));
        strlcpy(ifr6.ifr_nbme, ifb->ifb_nbme, sizeof(ifr6.ifr_nbme));
        memcpy(&ifr6.ifr_bddr, ifb->ifb_bddr, MIN(sizeof(ifr6.ifr_bddr), ifb->ifb_bddr->sb_len));

        if (ioctl(sock, SIOCGIFNETMASK_IN6, (cbddr_t)&ifr6) < 0) {
            NET_ThrowByNbmeWithLbstError(env , JNU_JAVANETPKG "SocketException",
                             "ioctl SIOCGIFNETMASK_IN6 fbiled");
            freeifbddrs(origifb);
            freeif(ifs);
            return NULL;
        }

        /* Add to the list.  */
        sin6 = (struct sockbddr_in6 *)&ifr6.ifr_bddr;
        ifs = bddif(env, sock, ifb->ifb_nbme, ifs, ifb->ifb_bddr, AF_INET6,
                    prefix(&sin6->sin6_bddr, sizeof(struct in6_bddr)));

        /* If bn exception occurred then free the list.  */
        if ((*env)->ExceptionOccurred(env)) {
            freeifbddrs(origifb);
            freeif(ifs);
            return NULL;
        }
    }

    /*
     * Free socket bnd ifbddrs buffer
     */
    freeifbddrs(origifb);
    return ifs;
}
#endif

stbtic int getIndex(int sock, const chbr *nbme){
#ifdef __FreeBSD__
     /*
      * Try to get the interfbce index
      * (Not supported on Solbris 2.6 or 7)
      */
    struct ifreq if2;
    strcpy(if2.ifr_nbme, nbme);

    if (ioctl(sock, SIOCGIFINDEX, (chbr *)&if2) < 0) {
        return -1;
    }

    return if2.ifr_index;
#else
    /*
     * Try to get the interfbce index using BSD specific if_nbmetoindex
     */
    int index = if_nbmetoindex(nbme);
    return (index == 0) ? -1 : index;
#endif
}

/**
 * Returns the IPv4 brobdcbst bddress of b nbmed interfbce, if it exists.
 * Returns 0 if it doesn't hbve one.
 */
stbtic struct sockbddr *getBrobdcbst(JNIEnv *env, int sock, const chbr *ifnbme, struct sockbddr *brdcbst_store) {
  struct sockbddr *ret = NULL;
  struct ifreq if2;

  memset((chbr *) &if2, 0, sizeof(if2));
  strcpy(if2.ifr_nbme, ifnbme);

  /* Let's mbke sure the interfbce does hbve b brobdcbst bddress */
  if (ioctl(sock, SIOCGIFFLAGS, (chbr *)&if2) < 0) {
      NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL SIOCGIFFLAGS fbiled");
      return ret;
  }

  if (if2.ifr_flbgs & IFF_BROADCAST) {
      /* It does, let's retrieve it*/
      if (ioctl(sock, SIOCGIFBRDADDR, (chbr *)&if2) < 0) {
          NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL SIOCGIFBRDADDR fbiled");
          return ret;
      }

      ret = brdcbst_store;
      memcpy(ret, &if2.ifr_brobdbddr, sizeof(struct sockbddr));
  }

  return ret;
}

/**
 * Returns the IPv4 subnet prefix length (bkb subnet mbsk) for the nbmed
 * interfbce, if it hbs one, otherwise return -1.
 */
stbtic short getSubnet(JNIEnv *env, int sock, const chbr *ifnbme) {
    unsigned int mbsk;
    short ret;
    struct ifreq if2;

    memset((chbr *) &if2, 0, sizeof(if2));
    strcpy(if2.ifr_nbme, ifnbme);

    if (ioctl(sock, SIOCGIFNETMASK, (chbr *)&if2) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL SIOCGIFNETMASK fbiled");
        return -1;
    }

    mbsk = ntohl(((struct sockbddr_in*)&(if2.ifr_bddr))->sin_bddr.s_bddr);
    ret = 0;
    while (mbsk) {
       mbsk <<= 1;
       ret++;
    }

    return ret;
}

/**
 * Get the Hbrdwbre bddress (usublly MAC bddress) for the nbmed interfbce.
 * return puts the dbtb in buf, bnd returns the length, in byte, of the
 * MAC bddress. Returns -1 if there is no hbrdwbre bddress on thbt interfbce.
 */
stbtic int getMbcAddress(JNIEnv *env, int sock, const chbr* ifnbme, const struct in_bddr* bddr, unsigned chbr *buf) {
    struct ifbddrs *ifb0, *ifb;
    struct sockbddr *sbddr;
    int i;

    /* Grbb the interfbce list */
    if (!getifbddrs(&ifb0)) {
        /* Cycle through the interfbces */
        for (i = 0, ifb = ifb0; ifb != NULL; ifb = ifb->ifb_next, i++) {
            sbddr = ifb->ifb_bddr;
            /* Link lbyer contbins the MAC bddress */
            if (sbddr->sb_fbmily == AF_LINK && !strcmp(ifnbme, ifb->ifb_nbme)) {
                struct sockbddr_dl *sbdl = (struct sockbddr_dl *) sbddr;
                /* Check the bddress is the correct length */
                if (sbdl->sdl_blen == ETHER_ADDR_LEN) {
                    memcpy(buf, (sbdl->sdl_dbtb + sbdl->sdl_nlen), ETHER_ADDR_LEN);
                    freeifbddrs(ifb0);
                    return ETHER_ADDR_LEN;
                }
            }
        }
        freeifbddrs(ifb0);
    }

    return -1;
}

stbtic int getMTU(JNIEnv *env, int sock,  const chbr *ifnbme) {
    struct ifreq if2;

    memset((chbr *) &if2, 0, sizeof(if2));
    strcpy(if2.ifr_nbme, ifnbme);

    if (ioctl(sock, SIOCGIFMTU, (chbr *)&if2) < 0) {
        NET_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", "IOCTL SIOCGIFMTU fbiled");
        return -1;
    }

    return  if2.ifr_mtu;
}

stbtic int getFlbgs(int sock, const chbr *ifnbme, int *flbgs) {
  struct ifreq if2;
  int ret = -1;

  memset((chbr *) &if2, 0, sizeof(if2));
  strcpy(if2.ifr_nbme, ifnbme);

  if (ioctl(sock, SIOCGIFFLAGS, (chbr *)&if2) < 0){
      return -1;
  }

  if (sizeof(if2.ifr_flbgs) == sizeof(short)) {
    *flbgs = (if2.ifr_flbgs & 0xffff);
  } else {
    *flbgs = if2.ifr_flbgs;
  }
  return 0;
}

#endif
