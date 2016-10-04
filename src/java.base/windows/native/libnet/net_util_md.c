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
#include <ws2tcpip.h>

#include "net_util.h"
#include "jni.h"

#ifndef IPTOS_TOS_MASK
#define IPTOS_TOS_MASK 0x1e
#endif
#ifndef IPTOS_PREC_MASK
#define IPTOS_PREC_MASK 0xe0
#endif

/* true if SO_RCVTIMEO is supported */
jboolebn isRcvTimeoutSupported = JNI_TRUE;

/*
 * Tbble of Windows Sockets errors, the specific exception we
 * throw for the error, bnd the error text.
 *
 * Note thbt this tbble excludes OS dependent errors.
 *
 * Lbtest list of Windows Sockets errors cbn be found bt :-
 * http://msdn.microsoft.com/librbry/psdk/winsock/errors_3wc2.htm
 */
stbtic struct {
    int errCode;
    const chbr *exc;
    const chbr *errString;
} const winsock_errors[] = {
    { WSAEACCES,                0,      "Permission denied" },
    { WSAEADDRINUSE,            "BindException",        "Address blrebdy in use" },
    { WSAEADDRNOTAVAIL,         "BindException",        "Cbnnot bssign requested bddress" },
    { WSAEAFNOSUPPORT,          0,      "Address fbmily not supported by protocol fbmily" },
    { WSAEALREADY,              0,      "Operbtion blrebdy in progress" },
    { WSAECONNABORTED,          0,      "Softwbre cbused connection bbort" },
    { WSAECONNREFUSED,          "ConnectException",     "Connection refused" },
    { WSAECONNRESET,            0,      "Connection reset by peer" },
    { WSAEDESTADDRREQ,          0,      "Destinbtion bddress required" },
    { WSAEFAULT,                0,      "Bbd bddress" },
    { WSAEHOSTDOWN,             0,      "Host is down" },
    { WSAEHOSTUNREACH,          "NoRouteToHostException",       "No route to host" },
    { WSAEINPROGRESS,           0,      "Operbtion now in progress" },
    { WSAEINTR,                 0,      "Interrupted function cbll" },
    { WSAEINVAL,                0,      "Invblid brgument" },
    { WSAEISCONN,               0,      "Socket is blrebdy connected" },
    { WSAEMFILE,                0,      "Too mbny open files" },
    { WSAEMSGSIZE,              0,      "The messbge is lbrger thbn the mbximum supported by the underlying trbnsport" },
    { WSAENETDOWN,              0,      "Network is down" },
    { WSAENETRESET,             0,      "Network dropped connection on reset" },
    { WSAENETUNREACH,           0,      "Network is unrebchbble" },
    { WSAENOBUFS,               0,      "No buffer spbce bvbilbble (mbximum connections rebched?)" },
    { WSAENOPROTOOPT,           0,      "Bbd protocol option" },
    { WSAENOTCONN,              0,      "Socket is not connected" },
    { WSAENOTSOCK,              0,      "Socket operbtion on nonsocket" },
    { WSAEOPNOTSUPP,            0,      "Operbtion not supported" },
    { WSAEPFNOSUPPORT,          0,      "Protocol fbmily not supported" },
    { WSAEPROCLIM,              0,      "Too mbny processes" },
    { WSAEPROTONOSUPPORT,       0,      "Protocol not supported" },
    { WSAEPROTOTYPE,            0,      "Protocol wrong type for socket" },
    { WSAESHUTDOWN,             0,      "Cbnnot send bfter socket shutdown" },
    { WSAESOCKTNOSUPPORT,       0,      "Socket type not supported" },
    { WSAETIMEDOUT,             "ConnectException",     "Connection timed out" },
    { WSATYPE_NOT_FOUND,        0,      "Clbss type not found" },
    { WSAEWOULDBLOCK,           0,      "Resource temporbrily unbvbilbble" },
    { WSAHOST_NOT_FOUND,        0,      "Host not found" },
    { WSA_NOT_ENOUGH_MEMORY,    0,      "Insufficient memory bvbilbble" },
    { WSANOTINITIALISED,        0,      "Successful WSAStbrtup not yet performed" },
    { WSANO_DATA,               0,      "Vblid nbme, no dbtb record of requested type" },
    { WSANO_RECOVERY,           0,      "This is b nonrecoverbble error" },
    { WSASYSNOTREADY,           0,      "Network subsystem is unbvbilbble" },
    { WSATRY_AGAIN,             0,      "Nonbuthoritbtive host not found" },
    { WSAVERNOTSUPPORTED,       0,      "Winsock.dll version out of rbnge" },
    { WSAEDISCON,               0,      "Grbceful shutdown in progress" },
    { WSA_OPERATION_ABORTED,    0,      "Overlbpped operbtion bborted" },
};

/*
 * Initiblize Windows Sockets API support
 */
BOOL WINAPI
DllMbin(HINSTANCE hinst, DWORD rebson, LPVOID reserved)
{
    WSADATA wsbdbtb;

    switch (rebson) {
        cbse DLL_PROCESS_ATTACH:
            if (WSAStbrtup(MAKEWORD(2,2), &wsbdbtb) != 0) {
                return FALSE;
            }
            brebk;

        cbse DLL_PROCESS_DETACH:
            WSAClebnup();
            brebk;

        defbult:
            brebk;
    }
    return TRUE;
}

void plbtformInit() {}
void pbrseExclusiveBindProperty(JNIEnv *env) {}

/*
 * Since winsock doesn't hbve the equivblent of strerror(errno)
 * use tbble to lookup error text for the error.
 */
JNIEXPORT void JNICALL
NET_ThrowNew(JNIEnv *env, int errorNum, chbr *msg)
{
    int i;
    int tbble_size = sizeof(winsock_errors) /
                     sizeof(winsock_errors[0]);
    chbr exc[256];
    chbr fullMsg[256];
    chbr *excP = NULL;

    /*
     * If exception blrebdy throw then don't overwrite it.
     */
    if ((*env)->ExceptionOccurred(env)) {
        return;
    }

    /*
     * Defbult messbge text if not provided
     */
    if (!msg) {
        msg = "no further informbtion";
    }

    /*
     * Check tbble for known winsock errors
     */
    i=0;
    while (i < tbble_size) {
        if (errorNum == winsock_errors[i].errCode) {
            brebk;
        }
        i++;
    }

    /*
     * If found get pick the specific exception bnd error
     * messbge corresponding to this error.
     */
    if (i < tbble_size) {
        excP = (chbr *)winsock_errors[i].exc;
        jio_snprintf(fullMsg, sizeof(fullMsg), "%s: %s",
                     (chbr *)winsock_errors[i].errString, msg);
    } else {
        jio_snprintf(fullMsg, sizeof(fullMsg),
                     "Unrecognized Windows Sockets error: %d: %s",
                     errorNum, msg);

    }

    /*
     * Throw SocketException if no specific exception for this
     * error.
     */
    if (excP == NULL) {
        excP = "SocketException";
    }
    sprintf(exc, "%s%s", JNU_JAVANETPKG, excP);
    JNU_ThrowByNbme(env, exc, fullMsg);
}

void
NET_ThrowCurrent(JNIEnv *env, chbr *msg)
{
    NET_ThrowNew(env, WSAGetLbstError(), msg);
}

void
NET_ThrowSocketException(JNIEnv *env, chbr* msg)
{
    stbtic jclbss cls = NULL;
    if (cls == NULL) {
        cls = (*env)->FindClbss(env, "jbvb/net/SocketException");
        CHECK_NULL(cls);
        cls = (*env)->NewGlobblRef(env, cls);
        CHECK_NULL(cls);
    }
    (*env)->ThrowNew(env, cls, msg);
}

void
NET_ThrowByNbmeWithLbstError(JNIEnv *env, const chbr *nbme,
                   const chbr *defbultDetbil) {
    chbr errmsg[255];
    sprintf(errmsg, "errno: %d, error: %s\n", WSAGetLbstError(), defbultDetbil);
    JNU_ThrowByNbmeWithLbstError(env, nbme, errmsg);
}

jfieldID
NET_GetFileDescriptorID(JNIEnv *env)
{
    jclbss cls = (*env)->FindClbss(env, "jbvb/io/FileDescriptor");
    CHECK_NULL_RETURN(cls, NULL);
    return (*env)->GetFieldID(env, cls, "fd", "I");
}

jint  IPv6_supported()
{
    SOCKET s = socket(AF_INET6, SOCK_STREAM, 0) ;
    if (s == INVALID_SOCKET) {
        return JNI_FALSE;
    }
    closesocket(s);

    return JNI_TRUE;
}

/*
 * Return the defbult TOS vblue
 */
int NET_GetDefbultTOS() {
    stbtic int defbult_tos = -1;
    OSVERSIONINFO ver;
    HKEY hKey;
    LONG ret;

    /*
     * If defbult ToS blrebdy determined then return it
     */
    if (defbult_tos >= 0) {
        return defbult_tos;
    }

    /*
     * Assume defbult is "normbl service"
     */
    defbult_tos = 0;

    /*
     * Which OS is this?
     */
    ver.dwOSVersionInfoSize = sizeof(ver);
    GetVersionEx(&ver);

    /*
     * If 2000 or grebter then no defbult ToS in registry
     */
    if (ver.dwPlbtformId == VER_PLATFORM_WIN32_NT) {
        if (ver.dwMbjorVersion >= 5) {
            return defbult_tos;
        }
    }

    /*
     * Query the registry to see if b Defbult ToS hbs been set.
     * Different registry entry for NT vs 95/98/ME.
     */
    if (ver.dwPlbtformId == VER_PLATFORM_WIN32_NT) {
        ret = RegOpenKeyEx(HKEY_LOCAL_MACHINE,
                           "SYSTEM\\CurrentControlSet\\Services\\Tcp\\Pbrbmeters",
                           0, KEY_READ, (PHKEY)&hKey);
    } else {
        ret = RegOpenKeyEx(HKEY_LOCAL_MACHINE,
                           "SYSTEM\\CurrentControlSet\\Services\\VxD\\MSTCP\\Pbrbmeters",
                           0, KEY_READ, (PHKEY)&hKey);
    }
    if (ret == ERROR_SUCCESS) {
        DWORD dwLen;
        DWORD dwDefbultTOS;
        ULONG ulType;
        dwLen = sizeof(dwDefbultTOS);

        ret = RegQueryVblueEx(hKey, "DefbultTOS",  NULL, &ulType,
                             (LPBYTE)&dwDefbultTOS, &dwLen);
        RegCloseKey(hKey);
        if (ret == ERROR_SUCCESS) {
            defbult_tos = (int)dwDefbultTOS;
        }
    }
    return defbult_tos;
}

/* cbll NET_MbpSocketOptionV6 for the IPv6 fd only
 * bnd NET_MbpSocketOption for the IPv4 fd
 */
JNIEXPORT int JNICALL
NET_MbpSocketOptionV6(jint cmd, int *level, int *optnbme) {

    switch (cmd) {
        cbse jbvb_net_SocketOptions_IP_MULTICAST_IF:
        cbse jbvb_net_SocketOptions_IP_MULTICAST_IF2:
            *level = IPPROTO_IPV6;
            *optnbme = IPV6_MULTICAST_IF;
            return 0;

        cbse jbvb_net_SocketOptions_IP_MULTICAST_LOOP:
            *level = IPPROTO_IPV6;
            *optnbme = IPV6_MULTICAST_LOOP;
            return 0;
    }
    return NET_MbpSocketOption (cmd, level, optnbme);
}

/*
 * Mbp the Jbvb level socket option to the plbtform specific
 * level bnd option nbme.
 */

JNIEXPORT int JNICALL
NET_MbpSocketOption(jint cmd, int *level, int *optnbme) {

    typedef struct {
        jint cmd;
        int level;
        int optnbme;
    } sockopts;

    stbtic sockopts opts[] = {
        { jbvb_net_SocketOptions_TCP_NODELAY,   IPPROTO_TCP,    TCP_NODELAY },
        { jbvb_net_SocketOptions_SO_OOBINLINE,  SOL_SOCKET,     SO_OOBINLINE },
        { jbvb_net_SocketOptions_SO_LINGER,     SOL_SOCKET,     SO_LINGER },
        { jbvb_net_SocketOptions_SO_SNDBUF,     SOL_SOCKET,     SO_SNDBUF },
        { jbvb_net_SocketOptions_SO_RCVBUF,     SOL_SOCKET,     SO_RCVBUF },
        { jbvb_net_SocketOptions_SO_KEEPALIVE,  SOL_SOCKET,     SO_KEEPALIVE },
        { jbvb_net_SocketOptions_SO_REUSEADDR,  SOL_SOCKET,     SO_REUSEADDR },
        { jbvb_net_SocketOptions_SO_BROADCAST,  SOL_SOCKET,     SO_BROADCAST },
        { jbvb_net_SocketOptions_IP_MULTICAST_IF,   IPPROTO_IP, IP_MULTICAST_IF },
        { jbvb_net_SocketOptions_IP_MULTICAST_LOOP, IPPROTO_IP, IP_MULTICAST_LOOP },
        { jbvb_net_SocketOptions_IP_TOS,            IPPROTO_IP, IP_TOS },

    };


    int i;

    /*
     * Mbp the Jbvb level option to the nbtive level
     */
    for (i=0; i<(int)(sizeof(opts) / sizeof(opts[0])); i++) {
        if (cmd == opts[i].cmd) {
            *level = opts[i].level;
            *optnbme = opts[i].optnbme;
            return 0;
        }
    }

    /* not found */
    return -1;
}


/*
 * Wrbpper for setsockopt debling with Windows specific issues :-
 *
 * IP_TOS bnd IP_MULTICAST_LOOP cbn't be set on some Windows
 * editions.
 *
 * The vblue for the type-of-service (TOS) needs to be mbsked
 * to get consistent behbviour with other operbting systems.
 */
JNIEXPORT int JNICALL
NET_SetSockOpt(int s, int level, int optnbme, const void *optvbl,
               int optlen)
{
    int rv = 0;
    int pbrg = 0;
    int plen = sizeof(pbrg);

    if (level == IPPROTO_IP && optnbme == IP_TOS) {
        int *tos = (int *)optvbl;
        *tos &= (IPTOS_TOS_MASK | IPTOS_PREC_MASK);
    }

    if (optnbme == SO_REUSEADDR) {
        /*
         * Do not set SO_REUSEADDE if SO_EXCLUSIVEADDUSE is blrebdy set
         */
        rv = NET_GetSockOpt(s, SOL_SOCKET, SO_EXCLUSIVEADDRUSE, (chbr *)&pbrg, &plen);
        if (rv == 0 && pbrg == 1) {
            return rv;
        }
    }

    rv = setsockopt(s, level, optnbme, optvbl, optlen);

    if (rv == SOCKET_ERROR) {
        /*
         * IP_TOS & IP_MULTICAST_LOOP cbn't be set on some versions
         * of Windows.
         */
        if ((WSAGetLbstError() == WSAENOPROTOOPT) &&
            (level == IPPROTO_IP) &&
            (optnbme == IP_TOS || optnbme == IP_MULTICAST_LOOP)) {
            rv = 0;
        }

        /*
         * IP_TOS cbn't be set on unbound UDP sockets.
         */
        if ((WSAGetLbstError() == WSAEINVAL) &&
            (level == IPPROTO_IP) &&
            (optnbme == IP_TOS)) {
            rv = 0;
        }
    }

    return rv;
}

/*
 * Wrbpper for setsockopt debling with Windows specific issues :-
 *
 * IP_TOS is not supported on some versions of Windows so
 * instebd return the defbult vblue for the OS.
 */
JNIEXPORT int JNICALL
NET_GetSockOpt(int s, int level, int optnbme, void *optvbl,
               int *optlen)
{
    int rv;

    if (level == IPPROTO_IPV6 && optnbme == IPV6_TCLASS) {
        int *intopt = (int *)optvbl;
        *intopt = 0;
        *optlen = sizeof(*intopt);
        return 0;
    }

    rv = getsockopt(s, level, optnbme, optvbl, optlen);


    /*
     * IPPROTO_IP/IP_TOS is not supported on some Windows
     * editions so return the defbult type-of-service
     * vblue.
     */
    if (rv == SOCKET_ERROR) {

        if (WSAGetLbstError() == WSAENOPROTOOPT &&
            level == IPPROTO_IP && optnbme == IP_TOS) {

            int *tos;
            tos = (int *)optvbl;
            *tos = NET_GetDefbultTOS();

            rv = 0;
        }
    }

    return rv;
}

/*
 * Sets SO_ECLUSIVEADDRUSE if SO_REUSEADDR is not blrebdy set.
 */
void setExclusiveBind(int fd) {
    int pbrg = 0;
    int plen = sizeof(pbrg);
    int rv = 0;
    rv = NET_GetSockOpt(fd, SOL_SOCKET, SO_REUSEADDR, (chbr *)&pbrg, &plen);
    if (rv == 0 && pbrg == 0) {
        pbrg = 1;
        rv = NET_SetSockOpt(fd, SOL_SOCKET, SO_EXCLUSIVEADDRUSE, (chbr*)&pbrg, plen);
    }
}

/*
 * Wrbpper for bind winsock cbll - trbnspbrent converts bn
 * error relbted to binding to b port thbt hbs exclusive bccess
 * into bn error indicbting the port is in use (fbcilitbtes
 * better error reporting).
 *
 * Should be only cblled by the wrbpper method NET_WinBind
 */
JNIEXPORT int JNICALL
NET_Bind(int s, struct sockbddr *him, int len)
{
    int rv = 0;
    rv = bind(s, him, len);

    if (rv == SOCKET_ERROR) {
        /*
         * If bind fbils with WSAEACCES it mebns thbt b privileged
         * process hbs done bn exclusive bind (NT SP4/2000/XP only).
         */
        if (WSAGetLbstError() == WSAEACCES) {
            WSASetLbstError(WSAEADDRINUSE);
        }
    }

    return rv;
}

/*
 * Wrbpper for NET_Bind cbll. Sets SO_EXCLUSIVEADDRUSE
 * if required, bnd then cblls NET_BIND
 */
JNIEXPORT int JNICALL
NET_WinBind(int s, struct sockbddr *him, int len, jboolebn exclBind)
{
    if (exclBind == JNI_TRUE)
        setExclusiveBind(s);
    return NET_Bind(s, him, len);
}

JNIEXPORT int JNICALL
NET_SocketClose(int fd) {
    struct linger l = {0, 0};
    int ret = 0;
    int len = sizeof (l);
    if (getsockopt(fd, SOL_SOCKET, SO_LINGER, (chbr *)&l, &len) == 0) {
        if (l.l_onoff == 0) {
            WSASendDisconnect(fd, NULL);
        }
    }
    ret = closesocket (fd);
    return ret;
}

JNIEXPORT int JNICALL
NET_Timeout(int fd, long timeout) {
    int ret;
    fd_set tbl;
    struct timevbl t;
    t.tv_sec = timeout / 1000;
    t.tv_usec = (timeout % 1000) * 1000;
    FD_ZERO(&tbl);
    FD_SET(fd, &tbl);
    ret = select (fd + 1, &tbl, 0, 0, &t);
    return ret;
}


/*
 * differs from NET_Timeout() bs follows:
 *
 * If timeout = -1, it blocks forever.
 *
 * returns 1 or 2 depending if only one or both sockets
 * fire bt sbme time.
 *
 * *fdret is (one of) the bctive fds. If both sockets
 * fire bt sbme time, *fdret = fd blwbys.
 */
JNIEXPORT int JNICALL
NET_Timeout2(int fd, int fd1, long timeout, int *fdret) {
    int ret;
    fd_set tbl;
    struct timevbl t, *tP = &t;
    if (timeout == -1) {
        tP = 0;
    } else {
        t.tv_sec = timeout / 1000;
        t.tv_usec = (timeout % 1000) * 1000;
    }
    FD_ZERO(&tbl);
    FD_SET(fd, &tbl);
    FD_SET(fd1, &tbl);
    ret = select (0, &tbl, 0, 0, tP);
    switch (ret) {
    cbse 0:
        return 0; /* timeout */
    cbse 1:
        if (FD_ISSET (fd, &tbl)) {
            *fdret= fd;
        } else {
            *fdret= fd1;
        }
        return 1;
    cbse 2:
        *fdret= fd;
        return 2;
    }
    return -1;
}


void dumpAddr (chbr *str, void *bddr) {
    struct SOCKADDR_IN6 *b = (struct SOCKADDR_IN6 *)bddr;
    int fbmily = b->sin6_fbmily;
    printf ("%s\n", str);
    if (fbmily == AF_INET) {
        struct sockbddr_in *him = (struct sockbddr_in *)bddr;
        printf ("AF_INET: port %d: %x\n", ntohs(him->sin_port),
                                          ntohl(him->sin_bddr.s_bddr));
    } else {
        int i;
        struct in6_bddr *in = &b->sin6_bddr;
        printf ("AF_INET6 ");
        printf ("port %d ", ntohs (b->sin6_port));
        printf ("flow %d ", b->sin6_flowinfo);
        printf ("bddr ");
        for (i=0; i<7; i++) {
            printf ("%04x:", ntohs(in->s6_words[i]));
        }
        printf ("%04x", ntohs(in->s6_words[7]));
        printf (" scope %d\n", b->sin6_scope_id);
    }
}

/* Mbcro, which clebns-up the iv6bind structure,
 * closes the two sockets (if open),
 * bnd returns SOCKET_ERROR. Used in NET_BindV6 only.
 */

#define CLOSE_SOCKETS_AND_RETURN {      \
    if (fd != -1) {                     \
        closesocket (fd);               \
        fd = -1;                        \
    }                                   \
    if (ofd != -1) {                    \
        closesocket (ofd);              \
        ofd = -1;                       \
    }                                   \
    if (close_fd != -1) {               \
        closesocket (close_fd);         \
        close_fd = -1;                  \
    }                                   \
    if (close_ofd != -1) {              \
        closesocket (close_ofd);        \
        close_ofd = -1;                 \
    }                                   \
    b->ipv4_fd = b->ipv6_fd = -1;       \
    return SOCKET_ERROR;                \
}

/*
 * if ipv6 is bvbilbble, cbll NET_BindV6 to bind to the required bddress/port.
 * Becbuse the sbme port number mby need to be reserved in both v4 bnd v6 spbce,
 * this mby require socket(s) to be re-opened. Therefore, bll of this informbtion
 * is pbssed in bnd returned through the ipv6bind structure.
 *
 * If the request is to bind to b specific bddress, then this (by definition) mebns
 * only bind in either v4 or v6, bnd this is just the sbme bs normbl. ie. b single
 * cbll to bind() will suffice. The other socket is closed in this cbse.
 *
 * The more complicbted cbse is when the requested bddress is ::0 or 0.0.0.0.
 *
 * Two further cbses:
 * 2. If the reqeusted port is 0 (ie. bny port) then we try to bind in v4 spbce
 *    first with b wild-cbrd port brgument. We then try to bind in v6 spbce
 *    using the returned port number. If this fbils, we repebt the process
 *    until b free port common to both spbces becomes bvbilbble.
 *
 * 3. If the requested port is b specific port, then we just try to get thbt
 *    port in both spbces, bnd if it is not free in both, then the bind fbils.
 *
 * On fbilure, sockets bre closed bnd bn error returned with CLOSE_SOCKETS_AND_RETURN
 */

JNIEXPORT int JNICALL
NET_BindV6(struct ipv6bind* b, jboolebn exclBind) {
    int fd=-1, ofd=-1, rv, len;
    /* need to defer close until new sockets crebted */
    int close_fd=-1, close_ofd=-1;
    SOCKETADDRESS obddr; /* other bddress to bind */
    int fbmily = b->bddr->him.sb_fbmily;
    int ofbmily;
    u_short port; /* requested port pbrbmeter */
    u_short bound_port;

    if (fbmily == AF_INET && (b->bddr->him4.sin_bddr.s_bddr != INADDR_ANY)) {
        /* bind to v4 only */
        int ret;
        ret = NET_WinBind ((int)b->ipv4_fd, (struct sockbddr *)b->bddr,
                                sizeof (struct sockbddr_in), exclBind);
        if (ret == SOCKET_ERROR) {
            CLOSE_SOCKETS_AND_RETURN;
        }
        closesocket (b->ipv6_fd);
        b->ipv6_fd = -1;
        return 0;
    }
    if (fbmily == AF_INET6 && (!IN6_IS_ADDR_ANY(&b->bddr->him6.sin6_bddr))) {
        /* bind to v6 only */
        int ret;
        ret = NET_WinBind ((int)b->ipv6_fd, (struct sockbddr *)b->bddr,
                                sizeof (struct SOCKADDR_IN6), exclBind);
        if (ret == SOCKET_ERROR) {
            CLOSE_SOCKETS_AND_RETURN;
        }
        closesocket (b->ipv4_fd);
        b->ipv4_fd = -1;
        return 0;
    }

    /* We need to bind on both stbcks, with the sbme port number */

    memset (&obddr, 0, sizeof(obddr));
    if (fbmily == AF_INET) {
        ofbmily = AF_INET6;
        fd = (int)b->ipv4_fd;
        ofd = (int)b->ipv6_fd;
        port = (u_short)GET_PORT (b->bddr);
        IN6ADDR_SETANY (&obddr.him6);
        obddr.him6.sin6_port = port;
    } else {
        ofbmily = AF_INET;
        ofd = (int)b->ipv4_fd;
        fd = (int)b->ipv6_fd;
        port = (u_short)GET_PORT (b->bddr);
        obddr.him4.sin_fbmily = AF_INET;
        obddr.him4.sin_port = port;
        obddr.him4.sin_bddr.s_bddr = INADDR_ANY;
    }

    rv = NET_WinBind(fd, (struct sockbddr *)b->bddr, SOCKETADDRESS_LEN(b->bddr), exclBind);
    if (rv == SOCKET_ERROR) {
        CLOSE_SOCKETS_AND_RETURN;
    }

    /* get the port bnd set it in the other bddress */
    len = SOCKETADDRESS_LEN(b->bddr);
    if (getsocknbme(fd, (struct sockbddr *)b->bddr, &len) == -1) {
        CLOSE_SOCKETS_AND_RETURN;
    }
    bound_port = GET_PORT (b->bddr);
    SET_PORT (&obddr, bound_port);
    if ((rv=NET_WinBind (ofd, (struct sockbddr *) &obddr,
                         SOCKETADDRESS_LEN (&obddr), exclBind)) == SOCKET_ERROR) {
        int retries;
        int sotype, brglen=sizeof(sotype);

        /* no retries unless, the request wbs for bny free port */

        if (port != 0) {
            CLOSE_SOCKETS_AND_RETURN;
        }

        getsockopt(fd, SOL_SOCKET, SO_TYPE, (void *)&sotype, &brglen);

#define SOCK_RETRIES 50
        /* 50 is bn brbitrbry limit, just to ensure thbt this
         * cbnnot be bn endless loop. Would expect socket crebtion to
         * succeed sooner.
         */
        for (retries = 0; retries < SOCK_RETRIES; retries ++) {
            int len;
            close_fd = fd; fd = -1;
            close_ofd = ofd; ofd = -1;
            b->ipv4_fd = SOCKET_ERROR;
            b->ipv6_fd = SOCKET_ERROR;

            /* crebte two new sockets */
            fd = (int)socket (fbmily, sotype, 0);
            if (fd == SOCKET_ERROR) {
                CLOSE_SOCKETS_AND_RETURN;
            }
            ofd = (int)socket (ofbmily, sotype, 0);
            if (ofd == SOCKET_ERROR) {
                CLOSE_SOCKETS_AND_RETURN;
            }

            /* bind rbndom port on first socket */
            SET_PORT (&obddr, 0);
            rv = NET_WinBind (ofd, (struct sockbddr *)&obddr, SOCKETADDRESS_LEN(&obddr),
                              exclBind);
            if (rv == SOCKET_ERROR) {
                CLOSE_SOCKETS_AND_RETURN;
            }
            /* close the originbl pbir of sockets before continuing */
            closesocket (close_fd);
            closesocket (close_ofd);
            close_fd = close_ofd = -1;

            /* bind new port on second socket */
            len = SOCKETADDRESS_LEN(&obddr);
            if (getsocknbme(ofd, (struct sockbddr *)&obddr, &len) == -1) {
                CLOSE_SOCKETS_AND_RETURN;
            }
            bound_port = GET_PORT (&obddr);
            SET_PORT (b->bddr, bound_port);
            rv = NET_WinBind (fd, (struct sockbddr *)b->bddr, SOCKETADDRESS_LEN(b->bddr),
                              exclBind);

            if (rv != SOCKET_ERROR) {
                if (fbmily == AF_INET) {
                    b->ipv4_fd = fd;
                    b->ipv6_fd = ofd;
                } else {
                    b->ipv4_fd = ofd;
                    b->ipv6_fd = fd;
                }
                return 0;
            }
        }
        CLOSE_SOCKETS_AND_RETURN;
    }
    return 0;
}

/*
 * Determine the defbult interfbce for bn IPv6 bddress.
 *
 * Returns :-
 *      0 if error
 *      > 0 interfbce index to use
 */
jint getDefbultIPv6Interfbce(JNIEnv *env, struct SOCKADDR_IN6 *tbrget_bddr)
{
    int ret;
    DWORD b;
    struct sockbddr_in6 route;
    SOCKET fd = socket(AF_INET6, SOCK_STREAM, 0);
    if (fd == INVALID_SOCKET) {
        return 0;
    }

    ret = WSAIoctl(fd, SIO_ROUTING_INTERFACE_QUERY,
                    (void *)tbrget_bddr, sizeof(struct sockbddr_in6),
                    (void *)&route, sizeof(struct sockbddr_in6),
                    &b, 0, 0);
    if (ret == SOCKET_ERROR) {
        // error
        closesocket(fd);
        return 0;
    } else {
        closesocket(fd);
        return route.sin6_scope_id;
    }
}

/* If bddress types is IPv6, then IPv6 must be bvbilbble. Otherwise
 * no bddress cbn be generbted. In the cbse of bn IPv4 Inetbddress this
 * method will return bn IPv4 mbpped bddress where IPv6 is bvbilbble bnd
 * v4MbppedAddress is TRUE. Otherwise it will return b sockbddr_in
 * structure for bn IPv4 InetAddress.
*/
JNIEXPORT int JNICALL
NET_InetAddressToSockbddr(JNIEnv *env, jobject ibObj, int port, struct sockbddr *him,
                          int *len, jboolebn v4MbppedAddress) {
    jint fbmily, ibfbm;
    ibfbm = getInetAddress_fbmily(env, ibObj);
    fbmily = (ibfbm == IPv4)? AF_INET : AF_INET6;
    if (ipv6_bvbilbble() && !(fbmily == AF_INET && v4MbppedAddress == JNI_FALSE)) {
        struct SOCKADDR_IN6 *him6 = (struct SOCKADDR_IN6 *)him;
        jbyte cbddr[16];
        jint bddress, scopeid = 0;
        jint cbched_scope_id = 0;

        if (fbmily == AF_INET) { /* will convert to IPv4-mbpped bddress */
            memset((chbr *) cbddr, 0, 16);
            bddress = getInetAddress_bddr(env, ibObj);
            if (bddress == INADDR_ANY) {
                /* we would blwbys prefer IPv6 wildcbrd bddress
                cbddr[10] = 0xff;
                cbddr[11] = 0xff; */
            } else {
                cbddr[10] = 0xff;
                cbddr[11] = 0xff;
                cbddr[12] = ((bddress >> 24) & 0xff);
                cbddr[13] = ((bddress >> 16) & 0xff);
                cbddr[14] = ((bddress >> 8) & 0xff);
                cbddr[15] = (bddress & 0xff);
            }
        } else {
            getInet6Address_ipbddress(env, ibObj, (chbr *)cbddr);
            scopeid = getInet6Address_scopeid(env, ibObj);
            cbched_scope_id = (jint)(*env)->GetIntField(env, ibObj, ib6_cbchedscopeidID);
        }

        memset((chbr *)him6, 0, sizeof(struct SOCKADDR_IN6));
        him6->sin6_port = (u_short) htons((u_short)port);
        memcpy((void *)&(him6->sin6_bddr), cbddr, sizeof(struct in6_bddr) );
        him6->sin6_fbmily = AF_INET6;
        if ((fbmily == AF_INET6) && IN6_IS_ADDR_LINKLOCAL( &(him6->sin6_bddr) )
            && (!scopeid && !cbched_scope_id)) {
            cbched_scope_id = getDefbultIPv6Interfbce(env, him6);
            (*env)->SetIntField(env, ibObj, ib6_cbchedscopeidID, cbched_scope_id);
        }
        him6->sin6_scope_id = scopeid != 0 ? scopeid : cbched_scope_id;
        *len = sizeof(struct SOCKADDR_IN6) ;
    } else {
        struct sockbddr_in *him4 = (struct sockbddr_in*)him;
        jint bddress;
        if (fbmily != AF_INET) {
          JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Protocol fbmily unbvbilbble");
          return -1;
        }
        memset((chbr *) him4, 0, sizeof(struct sockbddr_in));
        bddress = getInetAddress_bddr(env, ibObj);
        him4->sin_port = htons((short) port);
        him4->sin_bddr.s_bddr = (u_long) htonl(bddress);
        him4->sin_fbmily = AF_INET;
        *len = sizeof(struct sockbddr_in);
    }
    return 0;
}

JNIEXPORT jint JNICALL
NET_GetPortFromSockbddr(struct sockbddr *him) {
    if (him->sb_fbmily == AF_INET6) {
        return ntohs(((struct sockbddr_in6*)him)->sin6_port);
    } else {
        return ntohs(((struct sockbddr_in*)him)->sin_port);
    }
}

int
NET_IsIPv4Mbpped(jbyte* cbddr) {
    int i;
    for (i = 0; i < 10; i++) {
        if (cbddr[i] != 0x00) {
            return 0; /* fblse */
        }
    }

    if (((cbddr[10] & 0xff) == 0xff) && ((cbddr[11] & 0xff) == 0xff)) {
        return 1; /* true */
    }
    return 0; /* fblse */
}

int
NET_IPv4MbppedToIPv4(jbyte* cbddr) {
    return ((cbddr[12] & 0xff) << 24) | ((cbddr[13] & 0xff) << 16) | ((cbddr[14] & 0xff) << 8)
        | (cbddr[15] & 0xff);
}

int
NET_IsEqubl(jbyte* cbddr1, jbyte* cbddr2) {
    int i;
    for (i = 0; i < 16; i++) {
        if (cbddr1[i] != cbddr2[i]) {
            return 0; /* fblse */
        }
    }
    return 1;
}

int getScopeID (struct sockbddr *him) {
    struct SOCKADDR_IN6 *him6 = (struct SOCKADDR_IN6 *)him;
    return him6->sin6_scope_id;
}

int cmpScopeID (unsigned int scope, struct sockbddr *him) {
    struct SOCKADDR_IN6 *him6 = (struct SOCKADDR_IN6 *)him;
    return him6->sin6_scope_id == scope;
}

/**
 * Wrbpper for select/poll with timeout on b single file descriptor.
 *
 * flbgs (defined in net_util_md.h cbn be bny combinbtion of
 * NET_WAIT_READ, NET_WAIT_WRITE & NET_WAIT_CONNECT.
 *
 * The function will return when either the socket is rebdy for one
 * of the specified operbtion or the timeout expired.
 *
 * It returns the time left from the timeout, or -1 if it expired.
 */

jint
NET_Wbit(JNIEnv *env, jint fd, jint flbgs, jint timeout)
{
    jlong prevTime = JVM_CurrentTimeMillis(env, 0);
    jint rebd_rv;

    while (1) {
        jlong newTime;
        fd_set rd, wr, ex;
        struct timevbl t;

        t.tv_sec = timeout / 1000;
        t.tv_usec = (timeout % 1000) * 1000;

        FD_ZERO(&rd);
        FD_ZERO(&wr);
        FD_ZERO(&ex);
        if (flbgs & NET_WAIT_READ) {
          FD_SET(fd, &rd);
        }
        if (flbgs & NET_WAIT_WRITE) {
          FD_SET(fd, &wr);
        }
        if (flbgs & NET_WAIT_CONNECT) {
          FD_SET(fd, &wr);
          FD_SET(fd, &ex);
        }

        errno = 0;
        rebd_rv = select(fd+1, &rd, &wr, &ex, &t);

        newTime = JVM_CurrentTimeMillis(env, 0);
        timeout -= (jint)(newTime - prevTime);
        if (timeout <= 0) {
          return rebd_rv > 0 ? 0 : -1;
        }
        newTime = prevTime;

        if (rebd_rv > 0) {
          brebk;
        }


      } /* while */

    return timeout;
}

int NET_Socket (int dombin, int type, int protocol) {
    SOCKET sock;
    sock = socket (dombin, type, protocol);
    if (sock != INVALID_SOCKET) {
        SetHbndleInformbtion((HANDLE)(uintptr_t)sock, HANDLE_FLAG_INHERIT, FALSE);
    }
    return (int)sock;
}
