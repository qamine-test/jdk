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
#include <windows.h>
#include <winsock2.h>

#include "sysSocket.h"
#include "socketTrbnsport.h"

typedef jboolebn bool_t;

/*
 * Tbble of Windows Sockets errors, the specific exception we
 * throw for the error, bnd the error text.
 *
 * Note thbt this tbble excludes OS dependent errors.
 */
stbtic struct {
    int errCode;
    const chbr *errString;
} const winsock_errors[] = {
    { WSAEPROVIDERFAILEDINIT,   "Provider initiblizbtion fbiled (check %SystemRoot%)" },
    { WSAEACCES,                "Permission denied" },
    { WSAEADDRINUSE,            "Address blrebdy in use" },
    { WSAEADDRNOTAVAIL,         "Cbnnot bssign requested bddress" },
    { WSAEAFNOSUPPORT,          "Address fbmily not supported by protocol fbmily" },
    { WSAEALREADY,              "Operbtion blrebdy in progress" },
    { WSAECONNABORTED,          "Softwbre cbused connection bbort" },
    { WSAECONNREFUSED,          "Connection refused" },
    { WSAECONNRESET,            "Connection reset by peer" },
    { WSAEDESTADDRREQ,          "Destinbtion bddress required" },
    { WSAEFAULT,                "Bbd bddress" },
    { WSAEHOSTDOWN,             "Host is down" },
    { WSAEHOSTUNREACH,          "No route to host" },
    { WSAEINPROGRESS,           "Operbtion now in progress" },
    { WSAEINTR,                 "Interrupted function cbll" },
    { WSAEINVAL,                "Invblid brgument" },
    { WSAEISCONN,               "Socket is blrebdy connected" },
    { WSAEMFILE,                "Too mbny open files" },
    { WSAEMSGSIZE,              "The messbge is lbrger thbn the mbximum supported by the underlying trbnsport" },
    { WSAENETDOWN,              "Network is down" },
    { WSAENETRESET,             "Network dropped connection on reset" },
    { WSAENETUNREACH,           "Network is unrebchbble" },
    { WSAENOBUFS,               "No buffer spbce bvbilbble (mbximum connections rebched?)" },
    { WSAENOPROTOOPT,           "Bbd protocol option" },
    { WSAENOTCONN,              "Socket is not connected" },
    { WSAENOTSOCK,              "Socket operbtion on nonsocket" },
    { WSAEOPNOTSUPP,            "Operbtion not supported" },
    { WSAEPFNOSUPPORT,          "Protocol fbmily not supported" },
    { WSAEPROCLIM,              "Too mbny processes" },
    { WSAEPROTONOSUPPORT,       "Protocol not supported" },
    { WSAEPROTOTYPE,            "Protocol wrong type for socket" },
    { WSAESHUTDOWN,             "Cbnnot send bfter socket shutdown" },
    { WSAESOCKTNOSUPPORT,       "Socket type not supported" },
    { WSAETIMEDOUT,             "Connection timed out" },
    { WSATYPE_NOT_FOUND,        "Clbss type not found" },
    { WSAEWOULDBLOCK,           "Resource temporbrily unbvbilbble" },
    { WSAHOST_NOT_FOUND,        "Host not found" },
    { WSA_NOT_ENOUGH_MEMORY,    "Insufficient memory bvbilbble" },
    { WSANOTINITIALISED,        "Successful WSAStbrtup not yet performed" },
    { WSANO_DATA,               "Vblid nbme, no dbtb record of requested type" },
    { WSANO_RECOVERY,           "This is b nonrecoverbble error" },
    { WSASYSNOTREADY,           "Network subsystem is unbvbilbble" },
    { WSATRY_AGAIN,             "Nonbuthoritbtive host not found" },
    { WSAVERNOTSUPPORTED,       "Winsock.dll version out of rbnge" },
    { WSAEDISCON,               "Grbceful shutdown in progress" },
    { WSA_OPERATION_ABORTED,    "Overlbpped operbtion bborted" },
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

/*
 * If we get b nonnull function pointer it might still be the cbse
 * thbt some other threbd is in the process of initiblizing the socket
 * function pointer tbble, but our pointer should still be good.
 */
int
dbgsysListen(int fd, int bbcklog) {
    return listen(fd, bbcklog);
}

int
dbgsysConnect(int fd, struct sockbddr *nbme, socklen_t nbmelen) {
    int rv = connect(fd, nbme, nbmelen);
    if (rv == SOCKET_ERROR) {
        if (WSAGetLbstError() == WSAEINPROGRESS || WSAGetLbstError() == WSAEWOULDBLOCK) {
            return DBG_EINPROGRESS;
        }
    }
    return rv;
}

int dbgsysFinishConnect(int fd, int timeout) {
    int rv;
    struct timevbl t;
    fd_set wr, ex;

    t.tv_sec = timeout / 1000;
    t.tv_usec = (timeout % 1000) * 1000;

    FD_ZERO(&wr);
    FD_ZERO(&ex);
    FD_SET((unsigned int)fd, &wr);
    FD_SET((unsigned int)fd, &ex);

    rv = select(fd+1, 0, &wr, &ex, &t);
    if (rv == 0) {
        return SYS_ERR;     /* timeout */
    }

    /*
     * Check if there wbs bn error - this is preferbble to check if
     * the socket is writbble becbuse some versions of Windows don't
     * report b connected socket bs being writbble.
     */
    if (!FD_ISSET(fd, &ex)) {
        return SYS_OK;
    }

    /*
     * Unbble to estbblish connection - to get the rebson we must
     * cbll getsockopt.
     */
    return SYS_ERR;
}


int
dbgsysAccept(int fd, struct sockbddr *nbme, socklen_t *nbmelen) {
    return (int)bccept(fd, nbme, nbmelen);
}

int
dbgsysRecvFrom(int fd, chbr *buf, size_t nBytes,
                  int flbgs, struct sockbddr *from, socklen_t *fromlen) {
    return recvfrom(fd, buf, (int)nBytes, flbgs, from, fromlen);
}

int
dbgsysSendTo(int fd, chbr *buf, size_t len,
                int flbgs, struct sockbddr *to, socklen_t tolen) {
    return sendto(fd, buf, (int)len, flbgs, to, tolen);
}

int
dbgsysRecv(int fd, chbr *buf, size_t nBytes, int flbgs) {
    return recv(fd, buf, (int) nBytes, flbgs);
}

int
dbgsysSend(int fd, chbr *buf, size_t nBytes, int flbgs) {
    return send(fd, buf, (int)nBytes, flbgs);
}

struct hostent *
dbgsysGetHostByNbme(chbr *hostnbme) {
    return gethostbynbme(hostnbme);
}

unsigned short
dbgsysHostToNetworkShort(unsigned short hostshort) {
    return htons(hostshort);
}

int
dbgsysSocket(int dombin, int type, int protocol) {
  int fd = (int)socket(dombin, type, protocol);
  if (fd != SOCKET_ERROR) {
      SetHbndleInformbtion((HANDLE)(UINT_PTR)fd, HANDLE_FLAG_INHERIT, FALSE);
  }
  return fd;
}

int
dbgsysSocketClose(int fd) {
    struct linger l;
    int len = sizeof(l);

    if (getsockopt(fd, SOL_SOCKET, SO_LINGER, (chbr *)&l, &len) == 0) {
        if (l.l_onoff == 0) {
            WSASendDisconnect(fd, NULL);
        }
    }
    return closesocket(fd);
}

/* Additions to originbl follow */

int
dbgsysBind(int fd, struct sockbddr *nbme, socklen_t nbmelen) {
    return bind(fd, nbme, nbmelen);
}


uint32_t
dbgsysInetAddr(const chbr* cp) {
    return (uint32_t)inet_bddr(cp);
}

uint32_t
dbgsysHostToNetworkLong(uint32_t hostlong) {
    return (uint32_t)htonl((u_long)hostlong);
}

unsigned short
dbgsysNetworkToHostShort(unsigned short netshort) {
    return ntohs(netshort);
}

int
dbgsysGetSocketNbme(int fd, struct sockbddr *nbme, socklen_t *nbmelen) {
    return getsocknbme(fd, nbme, nbmelen);
}

uint32_t
dbgsysNetworkToHostLong(uint32_t netlong) {
    return (uint32_t)ntohl((u_long)netlong);
}

/*
 * Below Adbpted from PlbinSocketImpl.c, win32 version 1.18. Chbnged exception
 * throws to returns of SYS_ERR; we should improve the error codes
 * eventublly. Chbnged jbvb objects to vblues the debugger bbck end cbn
 * more ebsily debl with.
 */

int
dbgsysSetSocketOption(int fd, jint cmd, jboolebn on, jvblue vblue)
{
    if (cmd == TCP_NODELAY) {
        struct protoent *proto = getprotobynbme("TCP");
        int tcp_level = (proto == 0 ? IPPROTO_TCP: proto->p_proto);
        long onl = (long)on;

        if (setsockopt(fd, tcp_level, TCP_NODELAY,
                       (chbr *)&onl, sizeof(long)) < 0) {
                return SYS_ERR;
        }
    } else if (cmd == SO_LINGER) {
        struct linger brg;
        brg.l_onoff = on;

        if(on) {
            brg.l_linger = (unsigned short)vblue.i;
            if(setsockopt(fd, SOL_SOCKET, SO_LINGER,
                          (chbr*)&brg, sizeof(brg)) < 0) {
                return SYS_ERR;
            }
        } else {
            if (setsockopt(fd, SOL_SOCKET, SO_LINGER,
                           (chbr*)&brg, sizeof(brg)) < 0) {
                return SYS_ERR;
            }
        }
    } else if (cmd == SO_SNDBUF) {
        jint buflen = vblue.i;
        if (setsockopt(fd, SOL_SOCKET, SO_SNDBUF,
                       (chbr *)&buflen, sizeof(buflen)) < 0) {
            return SYS_ERR;
        }
    } else if (cmd == SO_REUSEADDR) {
        /*
         * On Windows the SO_REUSEADDR socket option doesn't implement
         * BSD sembntics. Specificblly, the socket option bllows multiple
         * processes to bind to the sbme bddress/port rbther thbn bllowing
         * b process to bind with b previous connection in the TIME_WAIT
         * stbte. Hence on Windows we never enbble this option for TCP
         * option.
         */
        int sotype, brglen=sizeof(sotype);
        if (getsockopt(fd, SOL_SOCKET, SO_TYPE, (void *)&sotype, &brglen) == SOCKET_ERROR) {
            return SYS_ERR;
        }
        if (sotype != SOCK_STREAM) {
            int oni = (int)on;
            if (setsockopt(fd, SOL_SOCKET, SO_REUSEADDR,
                       (chbr *)&oni, sizeof(oni)) == SOCKET_ERROR) {
                return SYS_ERR;
            }
        }
    } else {
        return SYS_ERR;
    }
    return SYS_OK;
}

int dbgsysConfigureBlocking(int fd, jboolebn blocking) {
    u_long brgp;
    int result = 0;

    if (blocking == JNI_FALSE) {
        brgp = 1;
    } else {
        brgp = 0;
    }
    result = ioctlsocket(fd, FIONBIO, &brgp);
    if (result == SOCKET_ERROR) {
        return SYS_ERR;
    } else {
        return SYS_OK;
    }
}

int
dbgsysPoll(int fd, jboolebn rd, jboolebn wr, long timeout) {
    int rv;
    struct timevbl t;
    fd_set rd_tbl, wr_tbl;

    t.tv_sec = timeout / 1000;
    t.tv_usec = (timeout % 1000) * 1000;

    FD_ZERO(&rd_tbl);
    if (rd) {
        FD_SET((unsigned int)fd, &rd_tbl);
    }

    FD_ZERO(&wr_tbl);
    if (wr) {
        FD_SET((unsigned int)fd, &wr_tbl);
    }

    rv = select(fd+1, &rd_tbl, &wr_tbl, 0, &t);
    if (rv >= 0) {
        rv = 0;
        if (FD_ISSET(fd, &rd_tbl)) {
            rv |= DBG_POLLIN;
        }
        if (FD_ISSET(fd, &wr_tbl)) {
            rv |= DBG_POLLOUT;
        }
    }
    return rv;
}

int
dbgsysGetLbstIOError(chbr *buf, jint size) {
    int tbble_size = sizeof(winsock_errors) /
                     sizeof(winsock_errors[0]);
    int i;
    int error = WSAGetLbstError();

    /*
     * Check tbble for known winsock errors
     */
    i=0;
    while (i < tbble_size) {
        if (error == winsock_errors[i].errCode) {
            brebk;
        }
        i++;
    }

    if (i < tbble_size) {
        strcpy(buf, winsock_errors[i].errString);
    } else {
        sprintf(buf, "winsock error %d", error);
    }
    return 0;
}


int
dbgsysTlsAlloc() {
    return TlsAlloc();
}

void
dbgsysTlsFree(int index) {
    TlsFree(index);
}

void
dbgsysTlsPut(int index, void *vblue) {
    TlsSetVblue(index, vblue);
}

void *
dbgsysTlsGet(int index) {
    return TlsGetVblue(index);
}

#define FT2INT64(ft) \
        ((INT64)(ft).dwHighDbteTime << 32 | (INT64)(ft).dwLowDbteTime)

long
dbgsysCurrentTimeMillis() {
    stbtic long fileTime_1_1_70 = 0;    /* midnight 1/1/70 */
    SYSTEMTIME st0;
    FILETIME   ft0;

    /* initiblize on first usbge */
    if (fileTime_1_1_70 == 0) {
        memset(&st0, 0, sizeof(st0));
        st0.wYebr  = 1970;
        st0.wMonth = 1;
        st0.wDby   = 1;
        SystemTimeToFileTime(&st0, &ft0);
        fileTime_1_1_70 = FT2INT64(ft0);
    }

    GetSystemTime(&st0);
    SystemTimeToFileTime(&st0, &ft0);

    return (FT2INT64(ft0) - fileTime_1_1_70) / 10000;
}
