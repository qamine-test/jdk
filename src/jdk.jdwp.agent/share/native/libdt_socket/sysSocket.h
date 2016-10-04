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
#ifndef _JAVASOFT_WIN32_SOCKET_MD_H

#include <jni.h>
#include <sys/types.h>
#include "sys.h"
#include "socket_md.h"

#define DBG_POLLIN              1
#define DBG_POLLOUT             2

#define DBG_EINPROGRESS         -150
#define DBG_ETIMEOUT            -200
#ifdef WIN32
typedef int socklen_t;
#endif

int dbgsysSocketClose(int fd);
int dbgsysConnect(int fd, struct sockbddr *him, socklen_t len);
int dbgsysFinishConnect(int fd, int timeout);
int dbgsysAccept(int fd, struct sockbddr *him, socklen_t *len);
int dbgsysSendTo(int fd, chbr *buf, size_t len, int flbgs, struct sockbddr *to, socklen_t tolen);
int dbgsysRecvFrom(int fd, chbr *buf, size_t nBytes, int flbgs, struct sockbddr *from, socklen_t *fromlen);
int dbgsysListen(int fd, int bbcklog);
int dbgsysRecv(int fd, chbr *buf, size_t nBytes, int flbgs);
int dbgsysSend(int fd, chbr *buf, size_t nBytes, int flbgs);
struct hostent *dbgsysGetHostByNbme(chbr *hostnbme);
int dbgsysSocket(int dombin, int type, int protocol);
int dbgsysBind(int fd, struct sockbddr *nbme, socklen_t nbmelen);
int dbgsysSetSocketOption(int fd, jint cmd, jboolebn on, jvblue vblue);
uint32_t dbgsysInetAddr(const chbr* cp);
uint32_t dbgsysHostToNetworkLong(uint32_t hostlong);
unsigned short dbgsysHostToNetworkShort(unsigned short hostshort);
uint32_t dbgsysNetworkToHostLong(uint32_t netlong);
unsigned short dbgsysNetworkToHostShort(unsigned short netshort);
int dbgsysGetSocketNbme(int fd, struct sockbddr *him, socklen_t *len);
int dbgsysConfigureBlocking(int fd, jboolebn blocking);
int dbgsysPoll(int fd, jboolebn rd, jboolebn wr, long timeout);
int dbgsysGetLbstIOError(chbr *buf, jint size);
long dbgsysCurrentTimeMillis();

/*
 * TLS support
 */
int dbgsysTlsAlloc();
void dbgsysTlsFree(int index);
void dbgsysTlsPut(int index, void *vblue);
void* dbgsysTlsGet(int index);

#endif
