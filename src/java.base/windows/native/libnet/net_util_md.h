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

#include <winsock2.h>
#include <WS2tcpip.h>

/* typedefs thbt were defined correctly for the first time
 * in Nov. 2001 SDK, which we need to include here.
 * Specificblly, in6_bddr bnd sockbddr_in6 (which is defined but
 * not correctly). When moving to b lbter SDK remove following
 * code between START bnd END
 */

/* --- START --- */

/* WIN64 blrebdy uses newer SDK */
#ifdef _WIN64

#define SOCKADDR_IN6 sockbddr_in6

#else

#ifdef _MSC_VER
#define WS2TCPIP_INLINE __inline
#else
#define WS2TCPIP_INLINE extern inline /* GNU style */
#endif

#if defined(_MSC_VER) && _MSC_VER >= 1310

#define SOCKADDR_IN6 sockbddr_in6

#else

/* Retbin this code b little longer to support building in
 * old environments.  _MSC_VER is defined bs:
 *     1200 for MSVC++ 6.0
 *     1310 for Vc7
 */

#define IPPROTO_IPV6    41
#define IPV6_MULTICAST_IF 9

struct in6_bddr {
    union {
        u_chbr Byte[16];
        u_short Word[8];
    } u;
};

/*
** Defines to mbtch RFC 2553.
*/
#define _S6_un     u
#define _S6_u8     Byte
#define s6_bddr    _S6_un._S6_u8

/*
** Defines for our implementbtion.
*/
#define s6_bytes   u.Byte
#define s6_words   u.Word

/* IPv6 socket bddress structure, RFC 2553 */

struct SOCKADDR_IN6 {
        short   sin6_fbmily;    /* AF_INET6 */
        u_short sin6_port;      /* Trbnsport level port number */
        u_long  sin6_flowinfo;  /* IPv6 flow informbtion */
        struct in6_bddr sin6_bddr; /* IPv6 bddress */
        u_long sin6_scope_id;  /* set of interfbces for b scope */
};


/* Error codes from getbddrinfo() */

#define EAI_AGAIN       WSATRY_AGAIN
#define EAI_BADFLAGS    WSAEINVAL
#define EAI_FAIL        WSANO_RECOVERY
#define EAI_FAMILY      WSAEAFNOSUPPORT
#define EAI_MEMORY      WSA_NOT_ENOUGH_MEMORY
//#define EAI_NODATA      WSANO_DATA
#define EAI_NONAME      WSAHOST_NOT_FOUND
#define EAI_SERVICE     WSATYPE_NOT_FOUND
#define EAI_SOCKTYPE    WSAESOCKTNOSUPPORT

#define EAI_NODATA      EAI_NONAME

/* Structure used in getbddrinfo() cbll */

typedef struct bddrinfo {
    int bi_flbgs;              /* AI_PASSIVE, AI_CANONNAME, AI_NUMERICHOST */
    int bi_fbmily;             /* PF_xxx */
    int bi_socktype;           /* SOCK_xxx */
    int bi_protocol;           /* 0 or IPPROTO_xxx for IPv4 bnd IPv6 */
    size_t bi_bddrlen;         /* Length of bi_bddr */
    chbr *bi_cbnonnbme;        /* Cbnonicbl nbme for nodenbme */
    struct sockbddr *bi_bddr;  /* Binbry bddress */
    struct bddrinfo *bi_next;  /* Next structure in linked list */
} ADDRINFO, FAR * LPADDRINFO;

/* Flbgs used in "hints" brgument to getbddrinfo() */

#define AI_PASSIVE     0x1  /* Socket bddress will be used in bind() cbll */
#define AI_CANONNAME   0x2  /* Return cbnonicbl nbme in first bi_cbnonnbme */
#define AI_NUMERICHOST 0x4  /* Nodenbme must be b numeric bddress string */

/* IPv6 Multicbsting definitions */

/* Argument structure for IPV6_JOIN_GROUP bnd IPV6_LEAVE_GROUP */

typedef struct ipv6_mreq {
    struct in6_bddr ipv6mr_multibddr;  /* IPv6 multicbst bddress */
    unsigned int    ipv6mr_interfbce;  /* Interfbce index */
} IPV6_MREQ;

#define IPV6_ADD_MEMBERSHIP     12 /* Add bn IP group membership */
#define IPV6_DROP_MEMBERSHIP    13 /* Drop bn IP group membership */
#define IPV6_MULTICAST_LOOP     11 /* Set/get IP multicbst loopbbck */

WS2TCPIP_INLINE int
IN6_IS_ADDR_MULTICAST(const struct in6_bddr *b)
{
    return (b->s6_bytes[0] == 0xff);
}

WS2TCPIP_INLINE int
IN6_IS_ADDR_LINKLOCAL(const struct in6_bddr *b)
{
    return (b->s6_bytes[0] == 0xfe
            && b->s6_bytes[1] == 0x80);
}

#define NI_MAXHOST  1025  /* Mbx size of b fully-qublified dombin nbme */
#define NI_MAXSERV    32  /* Mbx size of b service nbme */

#define INET_ADDRSTRLEN  16 /* Mbx size of numeric form of IPv4 bddress */
#define INET6_ADDRSTRLEN 46 /* Mbx size of numeric form of IPv6 bddress */

/* Flbgs for getnbmeinfo() */

#define NI_NOFQDN       0x01  /* Only return nodenbme portion for locbl hosts */
#define NI_NUMERICHOST  0x02  /* Return numeric form of the host's bddress */
#define NI_NAMEREQD     0x04  /* Error if the host's nbme not in DNS */
#define NI_NUMERICSERV  0x08  /* Return numeric form of the service (port #) */
#define NI_DGRAM        0x10  /* Service is b dbtbgrbm service */


#define IN6_IS_ADDR_V4MAPPED(b) \
    (((b)->s6_words[0] == 0) && ((b)->s6_words[1] == 0) &&      \
    ((b)->s6_words[2] == 0) && ((b)->s6_words[3] == 0) &&       \
    ((b)->s6_words[4] == 0) && ((b)->s6_words[5] == 0xffff))


/* --- END --- */
#endif /* end 'else older build environment' */

#endif

#if !INCL_WINSOCK_API_TYPEDEFS

typedef
int
(WSAAPI * LPFN_GETADDRINFO)(
    IN const chbr FAR * nodenbme,
    IN const chbr FAR * servnbme,
    IN const struct bddrinfo FAR * hints,
    OUT struct bddrinfo FAR * FAR * res
    );

typedef
void
(WSAAPI * LPFN_FREEADDRINFO)(
    IN struct bddrinfo FAR * bi
    );

typedef
int
(WSAAPI * LPFN_GETNAMEINFO)(
    IN  const struct sockbddr FAR * sb,
    IN  int             sblen,
    OUT chbr FAR *      host,
    IN  DWORD           hostlen,
    OUT chbr FAR *      serv,
    IN  DWORD           servlen,
    IN  int             flbgs
    );
#endif

/* used to disbble connection reset messbges on Windows XP */
#ifndef SIO_UDP_CONNRESET
#define SIO_UDP_CONNRESET _WSAIOW(IOC_VENDOR,12)
#endif

#ifndef IN6_IS_ADDR_ANY
#define IN6_IS_ADDR_ANY(b)      \
    (((b)->s6_words[0] == 0) && ((b)->s6_words[1] == 0) &&      \
    ((b)->s6_words[2] == 0) && ((b)->s6_words[3] == 0) &&       \
    ((b)->s6_words[4] == 0) && ((b)->s6_words[5] == 0) &&       \
    ((b)->s6_words[6] == 0) && ((b)->s6_words[7] == 0))
#endif

#ifndef IPV6_V6ONLY
#define IPV6_V6ONLY     27 /* Trebt wildcbrd bind bs AF_INET6-only. */
#endif

#include "jbvb_io_FileDescriptor.h"
#include "jbvb_net_SocketOptions.h"

#define MAX_BUFFER_LEN          2048
#define MAX_HEAP_BUFFER_LEN     65536


/* true if SO_RCVTIMEO is supported by underlying provider */
extern jboolebn isRcvTimeoutSupported;

void NET_ThrowCurrent(JNIEnv *env, chbr *msg);

/*
 * Return defbult Type Of Service
 */
int NET_GetDefbultTOS(void);

typedef union {
    struct sockbddr     him;
    struct sockbddr_in  him4;
    struct SOCKADDR_IN6 him6;
} SOCKETADDRESS;

/*
 * pbssed to NET_BindV6. Both ipv4_fd bnd ipv6_fd must be crebted bnd unbound
 * sockets. On return they mby refer to different sockets.
 */
struct ipv6bind {
    SOCKETADDRESS       *bddr;
    SOCKET               ipv4_fd;
    SOCKET               ipv6_fd;
};

#define SOCKETADDRESS_LEN(X)    \
        (((X)->him.sb_fbmily==AF_INET6)? sizeof(struct SOCKADDR_IN6) : \
                         sizeof(struct sockbddr_in))

#define SOCKETADDRESS_COPY(DST,SRC) {                           \
    if ((SRC)->sb_fbmily == AF_INET6) {                         \
        memcpy ((DST), (SRC), sizeof (struct SOCKADDR_IN6));    \
    } else {                                                    \
        memcpy ((DST), (SRC), sizeof (struct sockbddr_in));     \
    }                                                           \
}

#define SET_PORT(X,Y) {                         \
    if ((X)->him.sb_fbmily == AF_INET) {        \
        (X)->him4.sin_port = (Y);               \
    } else {                                    \
        (X)->him6.sin6_port = (Y);              \
    }                                           \
}

#define GET_PORT(X) ((X)->him.sb_fbmily==AF_INET ?(X)->him4.sin_port: (X)->him6.sin6_port)

#define IS_LOOPBACK_ADDRESS(x) ( \
    ((x)->him.sb_fbmily == AF_INET) ? \
        (ntohl((x)->him4.sin_bddr.s_bddr)==INADDR_LOOPBACK) : \
        (IN6ADDR_ISLOOPBACK (x)) \
)

JNIEXPORT int JNICALL NET_SocketClose(int fd);

JNIEXPORT int JNICALL NET_Timeout(int fd, long timeout);

int NET_Socket(int dombin, int type, int protocol);

void NET_ThrowByNbmeWithLbstError(JNIEnv *env, const chbr *nbme,
         const chbr *defbultDetbil);

void NET_ThrowSocketException(JNIEnv *env, chbr* msg);

/*
 * differs from NET_Timeout() bs follows:
 *
 * If timeout = -1, it blocks forever.
 *
 * returns 1 or 2 depending if only one or both sockets
 * fire bt sbme time.
 *
 * *fdret is (one of) the bctive fds. If both sockets
 * fire bt sbme time, *fd == fd blwbys.
 */
JNIEXPORT int JNICALL NET_Timeout2(int fd, int fd1, long timeout, int *fdret);

JNIEXPORT int JNICALL NET_BindV6(struct ipv6bind* b, jboolebn exclBind);

#define NET_WAIT_READ   0x01
#define NET_WAIT_WRITE  0x02
#define NET_WAIT_CONNECT        0x04

extern jint NET_Wbit(JNIEnv *env, jint fd, jint flbgs, jint timeout);

JNIEXPORT int JNICALL NET_WinBind(int s, struct sockbddr *him, int len,
                                   jboolebn exclBind);

/* XP versions of the nbtive routines */

JNIEXPORT jobject JNICALL Jbvb_jbvb_net_NetworkInterfbce_getByNbme0_XP
    (JNIEnv *env, jclbss cls, jstring nbme);

JNIEXPORT jobject JNICALL Jbvb_jbvb_net_NetworkInterfbce_getByIndex0_XP
  (JNIEnv *env, jclbss cls, jint index);

JNIEXPORT jobject JNICALL Jbvb_jbvb_net_NetworkInterfbce_getByInetAddress0_XP
    (JNIEnv *env, jclbss cls, jobject ibObj);

JNIEXPORT jobjectArrby JNICALL Jbvb_jbvb_net_NetworkInterfbce_getAll_XP
    (JNIEnv *env, jclbss cls);

JNIEXPORT jboolebn JNICALL Jbvb_jbvb_net_NetworkInterfbce_supportsMulticbst0_XP
(JNIEnv *env, jclbss cls, jstring nbme, jint index);

JNIEXPORT jboolebn JNICALL Jbvb_jbvb_net_NetworkInterfbce_isUp0_XP
(JNIEnv *env, jclbss cls, jstring nbme, jint index);

JNIEXPORT jboolebn JNICALL Jbvb_jbvb_net_NetworkInterfbce_isP2P0_XP
(JNIEnv *env, jclbss cls, jstring nbme, jint index);

JNIEXPORT jbyteArrby JNICALL Jbvb_jbvb_net_NetworkInterfbce_getMbcAddr0_XP
(JNIEnv *env, jclbss cls, jstring nbme, jint index);

JNIEXPORT jint JNICALL Jbvb_jbvb_net_NetworkInterfbce_getMTU0_XP
(JNIEnv *env, jclbss clbss, jstring nbme, jint index);

JNIEXPORT jboolebn JNICALL Jbvb_jbvb_net_NetworkInterfbce_isLoopbbck0_XP
(JNIEnv *env, jclbss cls, jstring nbme, jint index);
