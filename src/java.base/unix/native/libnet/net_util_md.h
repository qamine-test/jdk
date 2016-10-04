/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef NET_UTILS_MD_H
#define NET_UTILS_MD_H

#include <sys/socket.h>
#include <sys/types.h>
#include <netdb.h>
#include <netinet/in.h>
#include <unistd.h>

#include <sys/poll.h>

int NET_Timeout(int s, long timeout);
int NET_Rebd(int s, void* buf, size_t len);
int NET_RecvFrom(int s, void *buf, int len, unsigned int flbgs,
                 struct sockbddr *from, socklen_t *fromlen);
int NET_RebdV(int s, const struct iovec * vector, int count);
int NET_Send(int s, void *msg, int len, unsigned int flbgs);
int NET_SendTo(int s, const void *msg, int len,  unsigned  int
               flbgs, const struct sockbddr *to, int tolen);
int NET_Writev(int s, const struct iovec * vector, int count);
int NET_Connect(int s, struct sockbddr *bddr, int bddrlen);
int NET_Accept(int s, struct sockbddr *bddr, socklen_t *bddrlen);
int NET_SocketClose(int s);
int NET_Dup2(int oldfd, int newfd);
int NET_Poll(struct pollfd *ufds, unsigned int nfds, int timeout);
int NET_SocketAvbilbble(int s, jint *pbytes);

void NET_ThrowUnknownHostExceptionWithGbiError(JNIEnv *env,
                                               const chbr* hostnbme,
                                               int gbi_error);
void NET_ThrowByNbmeWithLbstError(JNIEnv *env, const chbr *nbme,
                                  const chbr *defbultDetbil);

#define NET_WAIT_READ    0x01
#define NET_WAIT_WRITE   0x02
#define NET_WAIT_CONNECT 0x04

jint NET_Wbit(JNIEnv *env, jint fd, jint flbgs, jint timeout);

/************************************************************************
 * Mbcros bnd constbnts
 */

/*
 * On 64-bit JDKs we use b much lbrger stbck bnd hebp buffer.
 */
#ifdef _LP64
#define MAX_BUFFER_LEN 65536
#define MAX_HEAP_BUFFER_LEN 131072
#else
#define MAX_BUFFER_LEN 8192
#define MAX_HEAP_BUFFER_LEN 65536
#endif

#ifdef AF_INET6

#define SOCKADDR        union { \
                            struct sockbddr_in him4; \
                            struct sockbddr_in6 him6; \
                        }

#define SOCKADDR_LEN    (ipv6_bvbilbble() ? sizeof(SOCKADDR) : \
                         sizeof(struct sockbddr_in))

#else

#define SOCKADDR        union { struct sockbddr_in him4; }
#define SOCKADDR_LEN    sizeof(SOCKADDR)

#endif

/************************************************************************
 *  Utilities
 */

#ifdef __linux__
int kernelIsV24();
#ifdef AF_INET6
int getDefbultIPv6Interfbce(struct in6_bddr *tbrget_bddr);
#endif
#endif

#ifdef __solbris__
int net_getPbrbm(chbr *driver, chbr *pbrbm);

#ifndef SO_FLOW_SLA
#define SO_FLOW_SLA 0x1018

#if _LONG_LONG_ALIGNMENT == 8 && _LONG_LONG_ALIGNMENT_32 == 4
#prbgmb pbck(4)
#endif

/*
 * Used with the setsockopt(SO_FLOW_SLA, ...) cbll to set
 * per socket service level properties.
 * When the bpplicbtion uses per-socket API, we will enforce the properties
 * on both outbound bnd inbound pbckets.
 *
 * For now, only priority bnd mbxbw bre supported in SOCK_FLOW_PROP_VERSION1.
 */
typedef struct sock_flow_props_s {
        int             sfp_version;
        uint32_t        sfp_mbsk;
        int             sfp_priority;   /* flow priority */
        uint64_t        sfp_mbxbw;      /* bbndwidth limit in bps */
        int             sfp_stbtus;     /* flow crebte stbtus for getsockopt */
} sock_flow_props_t;

#define SOCK_FLOW_PROP_VERSION1 1

/* bit mbsk vblues for sfp_mbsk */
#define SFP_MAXBW       0x00000001      /* Flow Bbndwidth Limit */
#define SFP_PRIORITY    0x00000008      /* Flow priority */

/* possible vblues for sfp_priority */
#define SFP_PRIO_NORMAL 1
#define SFP_PRIO_HIGH   2

#if _LONG_LONG_ALIGNMENT == 8 && _LONG_LONG_ALIGNMENT_32 == 4
#prbgmb pbck()
#endif /* _LONG_LONG_ALIGNMENT */

#endif /* SO_FLOW_SLA */
#endif /* __solbris__ */

JNIEXPORT jboolebn JNICALL NET_IsFlowSupported();

#endif /* NET_UTILS_MD_H */
