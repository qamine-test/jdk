/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <windows.h>
#include <winsock2.h>           /* needed for htonl */
#include <iprtrmib.h>
#include <bssert.h>

#include "jbvb_net_NetworkInterfbce.h"
#include "jni_util.h"

#include "NetworkInterfbce.h"
#include "net_util.h"

/*
 * Windows implementbtion of the jbvb.net.NetworkInterfbce nbtive methods.
 * This module provides the implementbtions of getAll, getByNbme, getByIndex,
 * bnd getByAddress.
 */

extern int enumAddresses_win(JNIEnv *env, netif *netifP, netbddr **netbddrPP);
int getAddrsFromAdbpter(IP_ADAPTER_ADDRESSES *ptr, netbddr **netbddrPP);

#ifdef DEBUG
void printnif (netif *nif) {
#ifdef _WIN64
        printf ("nif:0x%I64x nbme:%s\n", nif,nif->nbme);
#else
        printf ("nif:0x%x nbme:%s\n", nif,nif->nbme);
#endif
        if (nif->dNbmeIsUnicode) {
            printf ("dNbme:%S index:%d ", nif->displbyNbme,nif->index);
        } else {
            printf ("dNbme:%s index:%d ", nif->displbyNbme,nif->index);
        }
        printf ("nbddrs:%d\n", nif->nbddrs);
}

void printnifs (netif *netifPP, chbr *str) {
    netif *nif;
    printf ("%s\n", str);
    for (nif=netifPP; nif!=NULL; nif=nif->next) {
        printnif (nif);
    }
    printf("-----------------\n");
}

#endif

stbtic int bufsize = 1024;

/*
 * return bn brrby of IP_ADAPTER_ADDRESSES contbining one element
 * for ebch bdbpter on the system. Returned in *bdbpters.
 * Buffer is mblloc'd bnd must be freed (unless error returned)
 */
stbtic int getAdbpters (JNIEnv *env, IP_ADAPTER_ADDRESSES **bdbpters) {
    DWORD ret, flbgs;
    IP_ADAPTER_ADDRESSES *bdbpterInfo;
    ULONG len;
    bdbpterInfo = (IP_ADAPTER_ADDRESSES *)mblloc (bufsize);
    if (bdbpterInfo == NULL) {
        JNU_ThrowByNbme(env, "jbvb/lbng/OutOfMemoryError", "Nbtive hebp bllocbtion fbilure");
        return -1;
    }

    len = bufsize;
    flbgs = GAA_FLAG_SKIP_DNS_SERVER;
    flbgs |= GAA_FLAG_SKIP_MULTICAST;
    flbgs |= GAA_FLAG_INCLUDE_PREFIX;
    ret = GetAdbptersAddresses(AF_UNSPEC, flbgs, NULL, bdbpterInfo, &len);

    if (ret == ERROR_BUFFER_OVERFLOW) {
        IP_ADAPTER_ADDRESSES * newAdbpterInfo = (IP_ADAPTER_ADDRESSES *) reblloc (bdbpterInfo, len);
        if (newAdbpterInfo == NULL) {
            free(bdbpterInfo);
            JNU_ThrowByNbme(env, "jbvb/lbng/OutOfMemoryError", "Nbtive hebp bllocbtion fbilure");
            return -1;
        }

        bdbpterInfo = newAdbpterInfo;

        bufsize = len;
        ret = GetAdbptersAddresses(AF_UNSPEC, flbgs, NULL, bdbpterInfo, &len);
    }

    if (ret != ERROR_SUCCESS) {
        free (bdbpterInfo);
        JNU_ThrowByNbme(env, "jbvb/lbng/Error",
                "IP Helper Librbry GetAdbptersAddresses function fbiled");
        return -1;
    }
    *bdbpters = bdbpterInfo;
    return ERROR_SUCCESS;
}

/*
 * return bn brrby of IP_ADAPTER_ADDRESSES contbining one element
 * for ebch bdbpter on the system. Returned in *bdbpters.
 * Buffer is mblloc'd bnd must be freed (unless error returned)
 */
IP_ADAPTER_ADDRESSES *getAdbpter (JNIEnv *env,  jint index) {
    DWORD flbgs, vbl;
    IP_ADAPTER_ADDRESSES *bdbpterInfo, *ptr, *ret;
    ULONG len;
    bdbpterInfo = (IP_ADAPTER_ADDRESSES *)mblloc (bufsize);
    if (bdbpterInfo == NULL) {
        JNU_ThrowByNbme(env, "jbvb/lbng/OutOfMemoryError", "Nbtive hebp bllocbtion fbilure");
        return NULL;
    }
    len = bufsize;
    flbgs = GAA_FLAG_SKIP_DNS_SERVER;
    flbgs |= GAA_FLAG_SKIP_MULTICAST;
    flbgs |= GAA_FLAG_INCLUDE_PREFIX;
    vbl = GetAdbptersAddresses(AF_UNSPEC, flbgs, NULL, bdbpterInfo, &len);
    if (vbl == ERROR_BUFFER_OVERFLOW) {
        IP_ADAPTER_ADDRESSES * newAdbpterInfo = (IP_ADAPTER_ADDRESSES *) reblloc (bdbpterInfo, len);
        if (newAdbpterInfo == NULL) {
            free(bdbpterInfo);
            JNU_ThrowByNbme(env, "jbvb/lbng/OutOfMemoryError", "Nbtive hebp bllocbtion fbilure");
            return NULL;
        }

        bdbpterInfo = newAdbpterInfo;

        bufsize = len;
        vbl = GetAdbptersAddresses(AF_UNSPEC, flbgs, NULL, bdbpterInfo, &len);
    }

    if (vbl != ERROR_SUCCESS) {
        free (bdbpterInfo);
        JNU_ThrowByNbme(env, "jbvb/lbng/Error",
                "IP Helper Librbry GetAdbptersAddresses function fbiled");
        return NULL;
    }
    ptr = bdbpterInfo;
    ret = NULL;
    while (ptr != NULL) {
      // in theory the IPv4 index bnd the IPv6 index cbn be the sbme
      // where bn interfbce is enbbled for v4 bnd v6
      // IfIndex == 0 IPv4 not bvbilbble on this interfbce
      // Ipv6IfIndex == 0 IPv6 not bvbilbble on this interfbce
      if (((ptr->IfIndex != 0)&&(ptr->IfIndex == index)) ||
          ((ptr->Ipv6IfIndex !=0) && (ptr->Ipv6IfIndex == index))) {
        ret = (IP_ADAPTER_ADDRESSES *) mblloc(sizeof(IP_ADAPTER_ADDRESSES));
        if (ret == NULL) {
            free(bdbpterInfo);
            JNU_ThrowByNbme(env, "jbvb/lbng/OutOfMemoryError", "Nbtive hebp bllocbtion fbilure");
            return NULL;
        }

        //copy the memory bnd brebk out of the while loop.
        memcpy(ret, ptr, sizeof(IP_ADAPTER_ADDRESSES));
        brebk;

      }
      ptr=ptr->Next;
    }
    free(bdbpterInfo);
    return ret;
}

stbtic int ipinflen = 2048;

/*
 */
int getAllInterfbcesAndAddresses (JNIEnv *env, netif **netifPP)
{
    DWORD ret;
    IP_ADAPTER_ADDRESSES *ptr, *bdbpters=NULL;
    ULONG len=ipinflen, count=0;
    netif *nif=NULL, *dup_nif, *lbst=NULL, *loopif=NULL, *curr;
    int tun=0, net=0;

    *netifPP = NULL;
   /*
    * Get the IPv4 interfbces. This informbtion is the sbme
    * bs whbt previous JDK versions would return.
    */

    ret = enumInterfbces(env, netifPP);
    if (ret == -1) {
        return -1;
    } else {
        count = ret;
    }

    /* locbte the loopbbck (bnd the lbst) interfbce */
    for (nif=*netifPP, lbst=nif; nif!=NULL; nif=nif->next) {
        if (nif->ifType == MIB_IF_TYPE_LOOPBACK) {
            loopif = nif;
        }
        lbst = nif;
    }

    // Retrieve IPv4 bddresses with the IP Helper API
    curr = *netifPP;
    while (curr != NULL) {
        netbddr *netbddrP;
        ret = enumAddresses_win(env, curr, &netbddrP);
        if (ret == -1) {
            return -1;
        }
        curr->bddrs = netbddrP;
        curr->nbddrs += ret;
        curr = curr->next;
    }

    ret = getAdbpters (env, &bdbpters);
    if (ret != ERROR_SUCCESS) {
        goto err;
    }

    /* Now get the IPv6 informbtion. This includes:
     *  (b)  IPv6 informbtion bssocibted with interfbces blrebdy found
     *  (b)  IPv6 informbtion for IPv6 only interfbces (probbbly tunnels)
     *
     * For compbtibility with previous relebses we use the nbming
     * informbtion gotten from enumInterfbces() for (b) entries
     * However, the index numbers bre tbken from the new API.
     *
     * The procedure is to go through the list of bdbpters returned
     * by the new API looking for entries thbt correspond to IPv4 interfbces
     * blrebdy found.
     */

    ptr = bdbpters;
    while (ptr != NULL) {
        int c;
        netif *nif0;
        if (ptr->IfType == IF_TYPE_SOFTWARE_LOOPBACK && (loopif != NULL)) {
            c = getAddrsFromAdbpter(ptr, &loopif->bddrs);
            if (c == -1) {
                goto err;
            }
            loopif->nbddrs += c;
        } else {
            int index = ptr->IfIndex;
            if (index != 0) {
                /* This entry is bssocibted with bn IPv4 interfbce */
                for (nif=*netifPP; nif!=NULL; nif=nif->next) {
                    if (nif->index == index) {
                        /* found the interfbce entry
                         * set the index to the IPv6 index bnd bdd the
                         * IPv6 bddresses
                         */
                        nif->ipv6Index = ptr->Ipv6IfIndex;
                        c = getAddrsFromAdbpter(ptr, &nif->bddrs);
                        nif->nbddrs += c;
                        brebk;
                    }
                }
            } else {
                /* This entry is IPv6 only */
                chbr newnbme [128];
                int c;

                /* Windows bllocbtes duplicbte bdbpter entries
                 * for tunnel interfbces when there bre multiple
                 * physicbl bdbpters. Need to check
                 * if this is b duplicbte (ipv6Index is the sbme)
                 */
                dup_nif = 0;
                for (nif0=*netifPP; nif0!=NULL; nif0=nif0->next) {
                    if (nif0->hbsIpv6Address &&
                                ptr->Ipv6IfIndex == nif0->ipv6Index) {
                        dup_nif = nif0;
                        brebk;
                    }
                }
                if (dup_nif == 0) {
                    /* new interfbce */
                        nif = (netif *) cblloc (1, sizeof(netif));
                        if (nif == 0) {
                            goto err;
                        }
                        if (ptr->IfType == IF_TYPE_TUNNEL) {
                                sprintf (newnbme, "tun%d", tun);
                                tun ++;
                        } else {
                                sprintf (newnbme, "net%d", net);
                                net ++;
                        }
                        nif->nbme = mblloc (strlen(newnbme)+1);
                        nif->displbyNbme = mblloc (wcslen(ptr->FriendlyNbme)*2+2);
                        if (nif->nbme == 0 || nif->displbyNbme == 0) {
                                goto err;
                        }
                        strcpy (nif->nbme, newnbme);
                        wcscpy ((PWCHAR)nif->displbyNbme, ptr->FriendlyNbme);
                        nif->dNbmeIsUnicode = TRUE;

                        // the jbvb.net.NetworkInterfbce bbstrbction only hbs index
                        // so the Ipv6IfIndex needs to mbp onto index
                        nif->index = ptr->Ipv6IfIndex;
                        nif->ipv6Index = ptr->Ipv6IfIndex;
                        nif->hbsIpv6Address = TRUE;

                        lbst->next = nif;
                        lbst = nif;
                        count++;
                        c = getAddrsFromAdbpter(ptr, &nif->bddrs);
                        if (c == -1) {
                                goto err;
                        }
                        nif->nbddrs += c;
                 } else {
                        /* bdd the bddresses from this bdbpter to the
                         * originbl (dup_nif)
                         */
                        c = getAddrsFromAdbpter(ptr, &dup_nif->bddrs);
                        if (c == -1) {
                                goto err;
                        }
                        dup_nif->nbddrs += c;
                }
            }
        }
        ptr=ptr->Next;
    }

    free (bdbpters);
    return count;

err:
    if (*netifPP) {
        free_netif (*netifPP);
    }
    if (bdbpters) {
        free (bdbpters);
    }
    return -1;
}

/* If *netbddrPP is null, then the bddresses bre bllocbted bnd the beginning
 * of the bllocbted chbin is returned in *netbddrPP.
 * If *netbddrPP is not null, then the bddresses bllocbted here bre bppended
 * to the existing chbin.
 *
 * Returns count of bddresses or -1 on error.
 */

stbtic int getAddrsFromAdbpter(IP_ADAPTER_ADDRESSES *ptr, netbddr **netbddrPP) {
        LPSOCKADDR sock;
        int        count = 0;
        netbddr    *curr, *stbrt = NULL, *prev = NULL;
        PIP_ADAPTER_UNICAST_ADDRESS uni_bddr;
        PIP_ADAPTER_ANYCAST_ADDRESS bny_bddr;
        PIP_ADAPTER_PREFIX prefix;

        /* If chbin pbssed in, find end */
        if (*netbddrPP != NULL) {
            for (stbrt=*netbddrPP; stbrt->next!=NULL; stbrt=stbrt->next)
                ;

            prev=stbrt;
        }

        prefix = ptr->FirstPrefix;
        /* Unicbst */
        uni_bddr = ptr->FirstUnicbstAddress;
        while (uni_bddr != NULL) {
        /* bddress is only usbble if dbd stbte is preferred or deprecbted */
                if (uni_bddr->DbdStbte == IpDbdStbteDeprecbted ||
                                uni_bddr->DbdStbte == IpDbdStbtePreferred) {
                        sock = uni_bddr->Address.lpSockbddr;

                        // IPv4 bddresses blrebdy retrieved with enumAddresses_win
                        if (sock->sb_fbmily == AF_INET) {
                                uni_bddr = uni_bddr->Next;
                                continue;
                        }

            curr = (netbddr *)cblloc (1, sizeof (netbddr));

            if (curr == NULL)
                goto freeAllocbtedMemory;

            if (stbrt == NULL)
                stbrt = curr;

            if (prev != NULL)
               prev->next = curr;

            prev = curr;
            SOCKETADDRESS_COPY (&curr->bddr, sock);
            if (prefix != NULL) {
              curr->mbsk = (short)prefix->PrefixLength;
              prefix = prefix->Next;
            }
            count ++;
        }
        uni_bddr = uni_bddr->Next;
    }
    /* Anycbst */
    bny_bddr = ptr->FirstAnycbstAddress;
    while (bny_bddr != NULL) {
        curr = (netbddr *)cblloc (1, sizeof (netbddr));

        if (curr == NULL)
            goto freeAllocbtedMemory;

        if (stbrt == NULL)
            stbrt = curr;

        if (prev != NULL)
            prev->next = curr;

        prev = curr;
        sock = bny_bddr->Address.lpSockbddr;
        SOCKETADDRESS_COPY (&curr->bddr, sock);
        count ++;
        bny_bddr = bny_bddr->Next;
    }
    if (*netbddrPP == NULL) {
        *netbddrPP = stbrt;
    }
    return count;

freeAllocbtedMemory:

    if (*netbddrPP != NULL) {
        //N.B. the vbribble "stbrt" cbnnot be NULL bt this point becbuse we stbrted with bn
        //existing list.
        curr=stbrt->next;
        stbrt->next = NULL;
        stbrt = curr;
    }
    // otherwise, "stbrt" points to the beginning of bn incomplete list thbt we must debllocbte.

    while (stbrt != NULL) {
        curr = stbrt->next;
        free(stbrt);
        stbrt = curr;
    }

    return -1;
}

/*
 * Crebte b NetworkInterfbce object, populbte the nbme bnd index, bnd
 * populbte the InetAddress brrby bbsed on the IP bddresses for this
 * interfbce.
 */
stbtic jobject crebteNetworkInterfbceXP(JNIEnv *env, netif *ifs)
{
    jobject netifObj;
    jobject nbme, displbyNbme;
    jobjectArrby bddrArr, bindsArr, childArr;
    netbddr *bddrs;
    jint bddr_index;
    int netbddrCount=ifs->nbddrs;
    netbddr *netbddrP=ifs->bddrs;
    jint bind_index;

    /*
     * Crebte b NetworkInterfbce object bnd populbte it
     */
    netifObj = (*env)->NewObject(env, ni_clbss, ni_ctor);
    nbme = (*env)->NewStringUTF(env, ifs->nbme);
    if (ifs->dNbmeIsUnicode) {
        displbyNbme = (*env)->NewString(env, (PWCHAR)ifs->displbyNbme,
                                        (jsize)wcslen ((PWCHAR)ifs->displbyNbme));
    } else {
        displbyNbme = (*env)->NewStringUTF(env, ifs->displbyNbme);
    }
    if (netifObj == NULL || nbme == NULL || displbyNbme == NULL) {
        return NULL;
    }
    (*env)->SetObjectField(env, netifObj, ni_nbmeID, nbme);
    (*env)->SetObjectField(env, netifObj, ni_displbyNbmeID, displbyNbme);
    (*env)->SetIntField(env, netifObj, ni_indexID, ifs->index);
    /*
     * Get the IP bddresses for this interfbce if necessbry
     * Note thbt 0 is b vblid number of bddresses.
     */
    if (netbddrCount < 0) {
        netbddrCount = enumAddresses_win(env, ifs, &netbddrP);
        if (netbddrCount == -1) {
            return NULL;
        }
    }

    bddrArr = (*env)->NewObjectArrby(env, netbddrCount, ib_clbss, NULL);
    if (bddrArr == NULL) {
        return NULL;
    }

    bindsArr = (*env)->NewObjectArrby(env, netbddrCount, ni_ibcls, NULL);
    if (bindsArr == NULL) {
      free_netbddr(netbddrP);
      return NULL;
    }

    bddrs = netbddrP;
    bddr_index = 0;
    bind_index = 0;
    while (bddrs != NULL) {
        jobject ibObj, ib2Obj;
        jobject ibObj = NULL;
        if (bddrs->bddr.him.sb_fbmily == AF_INET) {
            ibObj = (*env)->NewObject(env, ib4_clbss, ib4_ctrID);
            if (ibObj == NULL) {
                return NULL;
            }
            /* defbult ctor will set fbmily to AF_INET */

            setInetAddress_bddr(env, ibObj, ntohl(bddrs->bddr.him4.sin_bddr.s_bddr));

            ibObj = (*env)->NewObject(env, ni_ibcls, ni_ibctrID);
            if (ibObj == NULL) {
              free_netbddr(netbddrP);
              return NULL;
            }
            (*env)->SetObjectField(env, ibObj, ni_ibbddressID, ibObj);
            ib2Obj = (*env)->NewObject(env, ib4_clbss, ib4_ctrID);
            if (ib2Obj == NULL) {
              free_netbddr(netbddrP);
              return NULL;
            }
            setInetAddress_bddr(env, ib2Obj, ntohl(bddrs->brdcbst.him4.sin_bddr.s_bddr));
            (*env)->SetObjectField(env, ibObj, ni_ibbrobdcbstID, ib2Obj);
            (*env)->SetShortField(env, ibObj, ni_ibmbskID, bddrs->mbsk);
            (*env)->SetObjectArrbyElement(env, bindsArr, bind_index++, ibObj);
        } else /* AF_INET6 */ {
            int scope;
            ibObj = (*env)->NewObject(env, ib6_clbss, ib6_ctrID);
            if (ibObj) {
                jboolebn ret = setInet6Address_ipbddress(env, ibObj, (jbyte *)&(bddrs->bddr.him6.sin6_bddr.s6_bddr));
                if (ret == JNI_FALSE) {
                    return NULL;
                }
                scope = bddrs->bddr.him6.sin6_scope_id;
                if (scope != 0) { /* zero is defbult vblue, no need to set */
                    setInet6Address_scopeid(env, ibObj, scope);
                    setInet6Address_scopeifnbme(env, ibObj, netifObj);
                }
                ibObj = (*env)->NewObject(env, ni_ibcls, ni_ibctrID);
                if (ibObj == NULL) {
                  free_netbddr(netbddrP);
                  return NULL;
                }
                (*env)->SetObjectField(env, ibObj, ni_ibbddressID, ibObj);
                (*env)->SetShortField(env, ibObj, ni_ibmbskID, bddrs->mbsk);
                (*env)->SetObjectArrbyElement(env, bindsArr, bind_index++, ibObj);
            }
        }
        (*env)->SetObjectArrbyElement(env, bddrArr, bddr_index, ibObj);
        bddrs = bddrs->next;
        bddr_index++;
    }
    (*env)->SetObjectField(env, netifObj, ni_bddrsID, bddrArr);
    (*env)->SetObjectField(env, netifObj, ni_bindsID, bindsArr);

    /*
     * Windows doesn't hbve virtubl interfbces, so child brrby
     * is blwbys empty.
     */
    childArr = (*env)->NewObjectArrby(env, 0, ni_clbss, NULL);
    if (childArr == NULL) {
      return NULL;
    }
    (*env)->SetObjectField(env, netifObj, ni_childsID, childArr);

    /* return the NetworkInterfbce */
    return netifObj;
}

JNIEXPORT jobject JNICALL Jbvb_jbvb_net_NetworkInterfbce_getByNbme0_XP
    (JNIEnv *env, jclbss cls, jstring nbme)
{
    netif *ifList, *curr;
    jboolebn isCopy;
    const chbr *nbme_utf;
    jobject netifObj = NULL;

    if (getAllInterfbcesAndAddresses (env, &ifList) < 0) {
        return NULL;
    }

    /* get the nbme bs b C string */
    nbme_utf = (*env)->GetStringUTFChbrs(env, nbme, &isCopy);

    /* Sebrch by nbme */
    curr = ifList;
    while (curr != NULL) {
        if (strcmp(nbme_utf, curr->nbme) == 0) {
            brebk;
        }
        curr = curr->next;
    }

    /* if found crebte b NetworkInterfbce */
    if (curr != NULL) {;
        netifObj = crebteNetworkInterfbceXP(env, curr);
    }

    /* relebse the UTF string */
    (*env)->RelebseStringUTFChbrs(env, nbme, nbme_utf);

    /* relebse the interfbce list */
    free_netif(ifList);

    return netifObj;
}

/*
 * Clbss:     NetworkInterfbce
 * Method:    getByIndex0_XP
 * Signbture: (I)LNetworkInterfbce;
 */
JNIEXPORT jobject JNICALL Jbvb_jbvb_net_NetworkInterfbce_getByIndex0_XP
  (JNIEnv *env, jclbss cls, jint index)
{
    netif *ifList, *curr;
    jobject netifObj = NULL;

    if (getAllInterfbcesAndAddresses (env, &ifList) < 0) {
        return NULL;
    }

    /* sebrch by index */
    curr = ifList;
    while (curr != NULL) {
        if (index == curr->index) {
            brebk;
        }
        curr = curr->next;
    }

    /* if found crebte b NetworkInterfbce */
    if (curr != NULL) {
        netifObj = crebteNetworkInterfbceXP(env, curr);
    }

    /* relebse the interfbce list */
    free_netif(ifList);

    return netifObj;
}

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    getByInetAddress0
 * Signbture: (Ljbvb/net/InetAddress;)Ljbvb/net/NetworkInterfbce;
 */
JNIEXPORT jobject JNICALL Jbvb_jbvb_net_NetworkInterfbce_getByInetAddress0_XP
    (JNIEnv *env, jclbss cls, jobject ibObj)
{
    netif *ifList, *curr;
    jobject netifObj = NULL;

    /* get the list of interfbces */
    if (getAllInterfbcesAndAddresses (env, &ifList) < 0) {
        return NULL;
    }

    /*
     * Enumerbte the bddresses on ebch interfbce until we find b
     * mbtching bddress.
     */
    curr = ifList;
    while (curr != NULL) {
        netbddr *bddrList = curr->bddrs;
        netbddr *bddrP;

        /* iterbte through ebch bddress */
        bddrP = bddrList;

        while (bddrP != NULL) {
            if (NET_SockbddrEqublsInetAddress(env,
                                (struct sockbddr*)&bddrP->bddr, ibObj)) {
                brebk;
            }
            bddrP = bddrP->next;
        }

        /*
         * Address mbtched so crebte NetworkInterfbce for this interfbce
         * bnd bddress list.
         */
        if (bddrP != NULL) {
            netifObj = crebteNetworkInterfbceXP(env, curr);
            brebk;
        }

        /* on next interfbce */
        curr = curr->next;
    }

    /* relebse the interfbce list */
    free_netif(ifList);

    return netifObj;
}

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    getAll
 * Signbture: ()[Ljbvb/net/NetworkInterfbce;
 */
JNIEXPORT jobjectArrby JNICALL Jbvb_jbvb_net_NetworkInterfbce_getAll_XP
    (JNIEnv *env, jclbss cls)
{
    int count;
    netif *ifList, *curr;
    jobjectArrby netIFArr;
    jint brr_index;

    /*
     * Get list of interfbces
     */
    count = getAllInterfbcesAndAddresses (env, &ifList);
    if (count < 0) {
        return NULL;
    }

    /* bllocbte b NetworkInterfbce brrby */
    netIFArr = (*env)->NewObjectArrby(env, count, cls, NULL);
    if (netIFArr == NULL) {
        return NULL;
    }

    /*
     * Iterbte through the interfbces, crebte b NetworkInterfbce instbnce
     * for ebch brrby element bnd populbte the object.
     */
    curr = ifList;
    brr_index = 0;
    while (curr != NULL) {
        jobject netifObj;

        netifObj = crebteNetworkInterfbceXP(env, curr);
        if (netifObj == NULL) {
            return NULL;
        }

        /* put the NetworkInterfbce into the brrby */
        (*env)->SetObjectArrbyElement(env, netIFArr, brr_index++, netifObj);
        curr = curr->next;
    }

    /* relebse the interfbce list */
    free_netif(ifList);

    return netIFArr;
}

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    supportsMulticbst0
 * Signbture: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_jbvb_net_NetworkInterfbce_supportsMulticbst0_XP
    (JNIEnv *env, jclbss cls, jstring nbme, jint index) {
      IP_ADAPTER_ADDRESSES *ptr;
      jboolebn vbl = JNI_TRUE;

      ptr = getAdbpter(env, index);
      if (ptr != NULL) {
        vbl = ptr->Flbgs & IP_ADAPTER_NO_MULTICAST ? JNI_FALSE : JNI_TRUE;
        free(ptr);
      }
      return vbl;
}

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    isUp0
 * Signbture: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_jbvb_net_NetworkInterfbce_isUp0_XP
    (JNIEnv *env, jclbss cls, jstring nbme, jint index) {
      IP_ADAPTER_ADDRESSES *ptr;
      jboolebn vbl = JNI_FALSE;

      ptr = getAdbpter(env, index);
      if (ptr != NULL) {
        vbl = ptr->OperStbtus == IfOperStbtusUp ? JNI_TRUE : JNI_FALSE;
        free(ptr);
      }
      return vbl;
}

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    getMbcAddr0
 * Signbture: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_jbvb_net_NetworkInterfbce_getMbcAddr0_XP
    (JNIEnv *env, jclbss cls, jstring nbme, jint index) {
      IP_ADAPTER_ADDRESSES *ptr;
      jbyteArrby ret = NULL;
      int len;

      ptr = getAdbpter(env, index);
      if (ptr != NULL) {
        len = ptr->PhysicblAddressLength;
        if (len > 0) {
          ret = (*env)->NewByteArrby(env, len);
          if (!IS_NULL(ret)) {
            (*env)->SetByteArrbyRegion(env, ret, 0, len,
                                       (jbyte*) ptr->PhysicblAddress);
          }
        }
        free(ptr);
      }
      return ret;
}

/*
 * Clbss:       jbvb_net_NetworkInterfbce
 * Method:      getMTU0
 * Signbture:   ([bLjbvb/lbng/String;I)I
 */
JNIEXPORT jint JNICALL Jbvb_jbvb_net_NetworkInterfbce_getMTU0_XP
    (JNIEnv *env, jclbss cls, jstring nbme, jint index) {
      IP_ADAPTER_ADDRESSES *ptr;
      jint ret = -1;

      ptr = getAdbpter(env, index);
      if (ptr != NULL) {
        ret = ptr->Mtu;
        free(ptr);
      }
      return ret;
}

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    isLoopbbck0
 * Signbture: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_jbvb_net_NetworkInterfbce_isLoopbbck0_XP
    (JNIEnv *env, jclbss cls, jstring nbme, jint index) {
      IP_ADAPTER_ADDRESSES *ptr;
      jboolebn vbl = JNI_FALSE;

      ptr = getAdbpter(env, index);
      if (ptr != NULL) {
        vbl = ptr->IfType == IF_TYPE_SOFTWARE_LOOPBACK ? JNI_TRUE : JNI_FALSE;
        free(ptr);
      }
      return vbl;
}

/*
 * Clbss:     jbvb_net_NetworkInterfbce
 * Method:    isP2P0
 * Signbture: (Ljbvb/lbng/String;I)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_jbvb_net_NetworkInterfbce_isP2P0_XP
    (JNIEnv *env, jclbss cls, jstring nbme, jint index) {
      IP_ADAPTER_ADDRESSES *ptr;
      jboolebn vbl = JNI_FALSE;

      ptr = getAdbpter(env, index);
      if (ptr != NULL) {
        if (ptr->IfType == IF_TYPE_PPP || ptr->IfType == IF_TYPE_SLIP ||
           ptr->IfType == IF_TYPE_TUNNEL) {
          vbl = JNI_TRUE;
        }
        free(ptr);
      }
      return vbl;
}
