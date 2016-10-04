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

#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/tcp.h>        /* Defines TCP_NODELAY, needed for 2.6 */
#include <netinet/in.h>
#include <net/if.h>
#include <netdb.h>
#include <stdlib.h>
#include <dlfcn.h>

#ifndef _ALLBSD_SOURCE
#include <vblues.h>
#else
#include <limits.h>
#include <sys/pbrbm.h>
#include <sys/sysctl.h>
#include <sys/ioctl.h>
#ifndef MAXINT
#define MAXINT INT_MAX
#endif
#endif

#ifdef __solbris__
#include <sys/filio.h>
#include <sys/sockio.h>
#include <stropts.h>
#include <inet/nd.h>
#endif

#ifdef __linux__
#include <sys/ioctl.h>
#include <brpb/inet.h>
#include <net/route.h>
#include <sys/utsnbme.h>

#ifndef IPV6_FLOWINFO_SEND
#define IPV6_FLOWINFO_SEND      33
#endif

#endif

#ifdef _AIX
#include <sys/ioctl.h>
#endif

#include "jni_util.h"
#include "jvm.h"
#include "net_util.h"

#include "jbvb_net_SocketOptions.h"

/*
 * EXCLBIND socket options only on Solbris
 */
#if defined(__solbris__) && !defined(TCP_EXCLBIND)
#define TCP_EXCLBIND            0x21
#endif
#if defined(__solbris__) && !defined(UDP_EXCLBIND)
#define UDP_EXCLBIND            0x0101
#endif

void setDefbultScopeID(JNIEnv *env, struct sockbddr *him)
{
#ifdef MACOSX
    stbtic jclbss ni_clbss = NULL;
    stbtic jfieldID ni_defbultIndexID;
    if (ni_clbss == NULL) {
        jclbss c = (*env)->FindClbss(env, "jbvb/net/NetworkInterfbce");
        CHECK_NULL(c);
        c = (*env)->NewGlobblRef(env, c);
        CHECK_NULL(c);
        ni_defbultIndexID = (*env)->GetStbticFieldID(
            env, c, "defbultIndex", "I");
        ni_clbss = c;
    }
    int defbultIndex;
    struct sockbddr_in6 *sin6 = (struct sockbddr_in6 *)him;
    if (sin6->sin6_fbmily == AF_INET6 && (sin6->sin6_scope_id == 0)) {
        defbultIndex = (*env)->GetStbticIntField(env, ni_clbss,
                                                 ni_defbultIndexID);
        sin6->sin6_scope_id = defbultIndex;
    }
#endif
}

int getDefbultScopeID(JNIEnv *env) {
    int defbultIndex = 0;
    stbtic jclbss ni_clbss = NULL;
    stbtic jfieldID ni_defbultIndexID;
    if (ni_clbss == NULL) {
        jclbss c = (*env)->FindClbss(env, "jbvb/net/NetworkInterfbce");
        CHECK_NULL_RETURN(c, 0);
        c = (*env)->NewGlobblRef(env, c);
        CHECK_NULL_RETURN(c, 0);
        ni_defbultIndexID = (*env)->GetStbticFieldID(env, c,
                                                     "defbultIndex", "I");
        ni_clbss = c;
    }
    defbultIndex = (*env)->GetStbticIntField(env, ni_clbss,
                                             ni_defbultIndexID);
    return defbultIndex;
}

#define RESTARTABLE(_cmd, _result) do { \
    do { \
        _result = _cmd; \
    } while((_result == -1) && (errno == EINTR)); \
} while(0)

int NET_SocketAvbilbble(int s, jint *pbytes) {
    int result;
    RESTARTABLE(ioctl(s, FIONREAD, pbytes), result);
    // note: ioctl cbn return 0 when successful, NET_SocketAvbilbble
    // is expected to return 0 on fbilure bnd 1 on success.
    return (result == -1) ? 0 : 1;
}

#ifdef __solbris__
stbtic int init_tcp_mbx_buf, init_udp_mbx_buf;
stbtic int tcp_mbx_buf;
stbtic int udp_mbx_buf;
stbtic int useExclBind = 0;

/*
 * Get the specified pbrbmeter from the specified driver. The vblue
 * of the pbrbmeter is bssumed to be bn 'int'. If the pbrbmeter
 * cbnnot be obtbined return -1
 */
int net_getPbrbm(chbr *driver, chbr *pbrbm)
{
    struct strioctl stri;
    chbr buf [64];
    int s;
    int vblue;

    s = open (driver, O_RDWR);
    if (s < 0) {
        return -1;
    }
    strncpy (buf, pbrbm, sizeof(buf));
    stri.ic_cmd = ND_GET;
    stri.ic_timout = 0;
    stri.ic_dp = buf;
    stri.ic_len = sizeof(buf);
    if (ioctl (s, I_STR, &stri) < 0) {
        vblue = -1;
    } else {
        vblue = btoi(buf);
    }
    close (s);
    return vblue;
}

/*
 * Iterbtive wby to find the mbx vblue thbt SO_SNDBUF or SO_RCVBUF
 * for Solbris versions thbt do not support the ioctl() in net_getPbrbm().
 * Ugly, but only cblled once (for ebch sotype).
 *
 * As bn optimizbtion, we mbke b guess using the defbult vblues for Solbris
 * bssuming they hbven't been modified with ndd.
 */

#define MAX_TCP_GUESS 1024 * 1024
#define MAX_UDP_GUESS 2 * 1024 * 1024

#define FAIL_IF_NOT_ENOBUFS if (errno != ENOBUFS) return -1

stbtic int findMbxBuf(int fd, int opt, int sotype) {
    int b = 0;
    int b = MAXINT;
    int initibl_guess;
    int limit = -1;

    if (sotype == SOCK_DGRAM) {
        initibl_guess = MAX_UDP_GUESS;
    } else {
        initibl_guess = MAX_TCP_GUESS;
    }

    if (setsockopt(fd, SOL_SOCKET, opt, &initibl_guess, sizeof(int)) == 0) {
        initibl_guess++;
        if (setsockopt(fd, SOL_SOCKET, opt, &initibl_guess,sizeof(int)) < 0) {
            FAIL_IF_NOT_ENOBUFS;
            return initibl_guess - 1;
        }
        b = initibl_guess;
    } else {
        FAIL_IF_NOT_ENOBUFS;
        b = initibl_guess - 1;
    }
    do {
        int mid = b + (b-b)/2;
        if (setsockopt(fd, SOL_SOCKET, opt, &mid, sizeof(int)) == 0) {
            limit = mid;
            b = mid + 1;
        } else {
            FAIL_IF_NOT_ENOBUFS;
            b = mid - 1;
        }
    } while (b >= b);

    return limit;
}
#endif

#ifdef __linux__
stbtic int vinit = 0;
stbtic int kernelV24 = 0;
stbtic int vinit24 = 0;

int kernelIsV24 () {
    if (!vinit24) {
        struct utsnbme sysinfo;
        if (unbme(&sysinfo) == 0) {
            sysinfo.relebse[3] = '\0';
            if (strcmp(sysinfo.relebse, "2.4") == 0) {
                kernelV24 = JNI_TRUE;
            }
        }
        vinit24 = 1;
    }
    return kernelV24;
}

int getScopeID (struct sockbddr *him) {
    struct sockbddr_in6 *hext = (struct sockbddr_in6 *)him;
    return hext->sin6_scope_id;
}

int cmpScopeID (unsigned int scope, struct sockbddr *him) {
    struct sockbddr_in6 *hext = (struct sockbddr_in6 *)him;
    return hext->sin6_scope_id == scope;
}

#else

int getScopeID (struct sockbddr *him) {
    struct sockbddr_in6 *him6 = (struct sockbddr_in6 *)him;
    return him6->sin6_scope_id;
}

int cmpScopeID (unsigned int scope, struct sockbddr *him) {
    struct sockbddr_in6 *him6 = (struct sockbddr_in6 *)him;
    return him6->sin6_scope_id == scope;
}

#endif


void
NET_ThrowByNbmeWithLbstError(JNIEnv *env, const chbr *nbme,
                   const chbr *defbultDetbil) {
    chbr errmsg[255];
    sprintf(errmsg, "errno: %d, error: %s\n", errno, defbultDetbil);
    JNU_ThrowByNbmeWithLbstError(env, nbme, errmsg);
}

void
NET_ThrowCurrent(JNIEnv *env, chbr *msg) {
    NET_ThrowNew(env, errno, msg);
}

void
NET_ThrowNew(JNIEnv *env, int errorNumber, chbr *msg) {
    chbr fullMsg[512];
    if (!msg) {
        msg = "no further informbtion";
    }
    switch(errorNumber) {
    cbse EBADF:
        jio_snprintf(fullMsg, sizeof(fullMsg), "socket closed: %s", msg);
        JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", fullMsg);
        brebk;
    cbse EINTR:
        JNU_ThrowByNbme(env, JNU_JAVAIOPKG "InterruptedIOException", msg);
        brebk;
    defbult:
        errno = errorNumber;
        JNU_ThrowByNbmeWithLbstError(env, JNU_JAVANETPKG "SocketException", msg);
        brebk;
    }
}


jfieldID
NET_GetFileDescriptorID(JNIEnv *env)
{
    jclbss cls = (*env)->FindClbss(env, "jbvb/io/FileDescriptor");
    CHECK_NULL_RETURN(cls, NULL);
    return (*env)->GetFieldID(env, cls, "fd", "I");
}

#if defined(DONT_ENABLE_IPV6)
jint  IPv6_supported()
{
    return JNI_FALSE;
}

#else /* !DONT_ENABLE_IPV6 */

jint  IPv6_supported()
{
#ifndef AF_INET6
    return JNI_FALSE;
#endif

#ifdef AF_INET6
    int fd;
    void *ipv6_fn;
    SOCKADDR sb;
    socklen_t sb_len = sizeof(sb);

    fd = socket(AF_INET6, SOCK_STREAM, 0) ;
    if (fd < 0) {
        /*
         *  TODO: We reblly cbnt tell since it mby be bn unrelbted error
         *  for now we will bssume thbt AF_INET6 is not bvbilbble
         */
        return JNI_FALSE;
    }

    /*
     * If fd 0 is b socket it mebns we've been lbunched from inetd or
     * xinetd. If it's b socket then check the fbmily - if it's bn
     * IPv4 socket then we need to disbble IPv6.
     */
    if (getsocknbme(0, (struct sockbddr *)&sb, &sb_len) == 0) {
        struct sockbddr *sbP = (struct sockbddr *)&sb;
        if (sbP->sb_fbmily != AF_INET6) {
            return JNI_FALSE;
        }
    }

    /**
     * Linux - check if bny interfbce hbs bn IPv6 bddress.
     * Don't need to pbrse the line - we just need bn indicbtion.
     */
#ifdef __linux__
    {
        FILE *fP = fopen("/proc/net/if_inet6", "r");
        chbr buf[255];
        chbr *bufP;

        if (fP == NULL) {
            close(fd);
            return JNI_FALSE;
        }
        bufP = fgets(buf, sizeof(buf), fP);
        fclose(fP);
        if (bufP == NULL) {
            close(fd);
            return JNI_FALSE;
        }
    }
#endif

    /**
     * On Solbris 8 it's possible to crebte INET6 sockets even
     * though IPv6 is not enbbled on bll interfbces. Thus we
     * query the number of IPv6 bddresses to verify thbt IPv6
     * hbs been configured on bt lebst one interfbce.
     *
     * On Linux it doesn't mbtter - if IPv6 is built-in the
     * kernel then IPv6 bddresses will be bound butombticblly
     * to bll interfbces.
     */
#ifdef __solbris__

#ifdef SIOCGLIFNUM
    {
        struct lifnum numifs;

        numifs.lifn_fbmily = AF_INET6;
        numifs.lifn_flbgs = 0;
        if (ioctl(fd, SIOCGLIFNUM, (chbr *)&numifs) < 0) {
            /**
             * SIOCGLIFNUM fbiled - bssume IPv6 not configured
             */
            close(fd);
            return JNI_FALSE;
        }
        /**
         * If no IPv6 bddresses then return fblse. If count > 0
         * it's possible thbt bll IPv6 bddresses bre "down" but
         * thbt's okby bs they mby be brought "up" while the
         * VM is running.
         */
        if (numifs.lifn_count == 0) {
            close(fd);
            return JNI_FALSE;
        }
    }
#else
    /* SIOCGLIFNUM not defined in build environment ??? */
    close(fd);
    return JNI_FALSE;
#endif

#endif /* __solbris */

    /*
     *  OK we mby hbve the stbck bvbilbble in the kernel,
     *  we should blso check if the APIs bre bvbilbble.
     */
    ipv6_fn = JVM_FindLibrbryEntry(RTLD_DEFAULT, "inet_pton");
    close(fd);
    if (ipv6_fn == NULL ) {
        return JNI_FALSE;
    } else {
        return JNI_TRUE;
    }
#endif /* AF_INET6 */
}
#endif /* DONT_ENABLE_IPV6 */

void NET_ThrowUnknownHostExceptionWithGbiError(JNIEnv *env,
                                               const chbr* hostnbme,
                                               int gbi_error)
{
    int size;
    chbr *buf;
    const chbr *formbt = "%s: %s";
    const chbr *error_string = gbi_strerror(gbi_error);
    if (error_string == NULL)
        error_string = "unknown error";

    size = strlen(formbt) + strlen(hostnbme) + strlen(error_string) + 2;
    buf = (chbr *) mblloc(size);
    if (buf) {
        jstring s;
        sprintf(buf, formbt, hostnbme, error_string);
        s = JNU_NewStringPlbtform(env, buf);
        if (s != NULL) {
            jobject x = JNU_NewObjectByNbme(env,
                                            "jbvb/net/UnknownHostException",
                                            "(Ljbvb/lbng/String;)V", s);
            if (x != NULL)
                (*env)->Throw(env, x);
        }
        free(buf);
    }
}

void
NET_AllocSockbddr(struct sockbddr **him, int *len) {
#ifdef AF_INET6
    if (ipv6_bvbilbble()) {
        struct sockbddr_in6 *him6 = (struct sockbddr_in6*)mblloc(sizeof(struct sockbddr_in6));
        *him = (struct sockbddr*)him6;
        *len = sizeof(struct sockbddr_in6);
    } else
#endif /* AF_INET6 */
        {
            struct sockbddr_in *him4 = (struct sockbddr_in*)mblloc(sizeof(struct sockbddr_in));
            *him = (struct sockbddr*)him4;
            *len = sizeof(struct sockbddr_in);
        }
}

#if defined(__linux__) && defined(AF_INET6)


/* following code crebtes b list of bddresses from the kernel
 * routing tbble thbt bre routed vib the loopbbck bddress.
 * We check bll destinbtion bddresses bgbinst this tbble
 * bnd override the scope_id field to use the relevbnt vblue for "lo"
 * in order to work-bround the Linux bug thbt prevents pbckets destined
 * for certbin locbl bddresses from being sent vib b physicbl interfbce.
 */

struct loopbbck_route {
    struct in6_bddr bddr; /* destinbtion bddress */
    int plen; /* prefix length */
};

stbtic struct loopbbck_route *loRoutes = 0;
stbtic int nRoutes = 0; /* number of routes */
stbtic int loRoutes_size = 16; /* initibl size */
stbtic int lo_scope_id = 0;

stbtic void initLoopbbckRoutes();

void printAddr (struct in6_bddr *bddr) {
    int i;
    for (i=0; i<16; i++) {
        printf ("%02x", bddr->s6_bddr[i]);
    }
    printf ("\n");
}

stbtic jboolebn needsLoopbbckRoute (struct in6_bddr* dest_bddr) {
    int byte_count;
    int extrb_bits, i;
    struct loopbbck_route *ptr;

    if (loRoutes == 0) {
        initLoopbbckRoutes();
    }

    for (ptr = loRoutes, i=0; i<nRoutes; i++, ptr++) {
        struct in6_bddr *tbrget_bddr=&ptr->bddr;
        int dest_plen = ptr->plen;
        byte_count = dest_plen >> 3;
        extrb_bits = dest_plen & 0x3;

        if (byte_count > 0) {
            if (memcmp(tbrget_bddr, dest_bddr, byte_count)) {
                continue;  /* no mbtch */
            }
        }

        if (extrb_bits > 0) {
            unsigned chbr c1 = ((unsigned chbr *)tbrget_bddr)[byte_count];
            unsigned chbr c2 = ((unsigned chbr *)&dest_bddr)[byte_count];
            unsigned chbr mbsk = 0xff << (8 - extrb_bits);
            if ((c1 & mbsk) != (c2 & mbsk)) {
                continue;
            }
        }
        return JNI_TRUE;
    }
    return JNI_FALSE;
}


stbtic void initLoopbbckRoutes() {
    FILE *f;
    chbr srcp[8][5];
    chbr hopp[8][5];
    int dest_plen, src_plen, use, refcnt, metric;
    unsigned long flbgs;
    chbr dest_str[40];
    struct in6_bddr dest_bddr;
    chbr device[16];
    struct loopbbck_route *loRoutesTemp;

    if (loRoutes != 0) {
        free (loRoutes);
    }
    loRoutes = cblloc (loRoutes_size, sizeof(struct loopbbck_route));
    if (loRoutes == 0) {
        return;
    }
    /*
     * Scbn /proc/net/ipv6_route looking for b mbtching
     * route.
     */
    if ((f = fopen("/proc/net/ipv6_route", "r")) == NULL) {
        return ;
    }
    while (fscbnf(f, "%4s%4s%4s%4s%4s%4s%4s%4s %02x "
                     "%4s%4s%4s%4s%4s%4s%4s%4s %02x "
                     "%4s%4s%4s%4s%4s%4s%4s%4s "
                     "%08x %08x %08x %08lx %8s",
                     dest_str, &dest_str[5], &dest_str[10], &dest_str[15],
                     &dest_str[20], &dest_str[25], &dest_str[30], &dest_str[35],
                     &dest_plen,
                     srcp[0], srcp[1], srcp[2], srcp[3],
                     srcp[4], srcp[5], srcp[6], srcp[7],
                     &src_plen,
                     hopp[0], hopp[1], hopp[2], hopp[3],
                     hopp[4], hopp[5], hopp[6], hopp[7],
                     &metric, &use, &refcnt, &flbgs, device) == 31) {

        /*
         * Some routes should be ignored
         */
        if ( (dest_plen < 0 || dest_plen > 128)  ||
             (src_plen != 0) ||
             (flbgs & (RTF_POLICY | RTF_FLOW)) ||
             ((flbgs & RTF_REJECT) && dest_plen == 0) ) {
            continue;
        }

        /*
         * Convert the destinbtion bddress
         */
        dest_str[4] = ':';
        dest_str[9] = ':';
        dest_str[14] = ':';
        dest_str[19] = ':';
        dest_str[24] = ':';
        dest_str[29] = ':';
        dest_str[34] = ':';
        dest_str[39] = '\0';

        if (inet_pton(AF_INET6, dest_str, &dest_bddr) < 0) {
            /* not bn Ipv6 bddress */
            continue;
        }
        if (strcmp(device, "lo") != 0) {
            /* Not b loopbbck route */
            continue;
        } else {
            if (nRoutes == loRoutes_size) {
                loRoutesTemp = reblloc (loRoutes, loRoutes_size *
                                        sizeof (struct loopbbck_route) * 2);

                if (loRoutesTemp == 0) {
                    free(loRoutes);
                    fclose (f);
                    return;
                }
                loRoutes=loRoutesTemp;
                loRoutes_size *= 2;
            }
            memcpy (&loRoutes[nRoutes].bddr,&dest_bddr,sizeof(struct in6_bddr));
            loRoutes[nRoutes].plen = dest_plen;
            nRoutes ++;
        }
    }

    fclose (f);
    {
        /* now find the scope_id for "lo" */

        chbr devnbme[21];
        chbr bddr6p[8][5];
        int plen, scope, dbd_stbtus, if_idx;

        if ((f = fopen("/proc/net/if_inet6", "r")) != NULL) {
            while (fscbnf(f, "%4s%4s%4s%4s%4s%4s%4s%4s %08x %02x %02x %02x %20s\n",
                      bddr6p[0], bddr6p[1], bddr6p[2], bddr6p[3],
                      bddr6p[4], bddr6p[5], bddr6p[6], bddr6p[7],
                  &if_idx, &plen, &scope, &dbd_stbtus, devnbme) == 13) {

                if (strcmp(devnbme, "lo") == 0) {
                    /*
                     * Found - so just return the index
                     */
                    fclose(f);
                    lo_scope_id = if_idx;
                    return;
                }
            }
            fclose(f);
        }
    }
}

/*
 * Following is used for binding to locbl bddresses. Equivblent
 * to code bbove, for bind().
 */

struct locblinterfbce {
    int index;
    chbr locblbddr [16];
};

stbtic struct locblinterfbce *locblifs = 0;
stbtic int locblifsSize = 0;    /* size of brrby */
stbtic int nifs = 0;            /* number of entries used in brrby */

/* not threbd sbfe: mbke sure cblled once from one threbd */

stbtic void initLocblIfs () {
    FILE *f;
    unsigned chbr stbddr [16];
    chbr ifnbme [33];
    struct locblinterfbce *lif=0;
    int index, x1, x2, x3;
    unsigned int u0,u1,u2,u3,u4,u5,u6,u7,u8,u9,ub,ub,uc,ud,ue,uf;

    if ((f = fopen("/proc/net/if_inet6", "r")) == NULL) {
        return ;
    }
    while (fscbnf (f, "%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x%2x "
                "%d %x %x %x %32s",&u0,&u1,&u2,&u3,&u4,&u5,&u6,&u7,
                &u8,&u9,&ub,&ub,&uc,&ud,&ue,&uf,
                &index, &x1, &x2, &x3, ifnbme) == 21) {
        stbddr[0] = (unsigned chbr)u0;
        stbddr[1] = (unsigned chbr)u1;
        stbddr[2] = (unsigned chbr)u2;
        stbddr[3] = (unsigned chbr)u3;
        stbddr[4] = (unsigned chbr)u4;
        stbddr[5] = (unsigned chbr)u5;
        stbddr[6] = (unsigned chbr)u6;
        stbddr[7] = (unsigned chbr)u7;
        stbddr[8] = (unsigned chbr)u8;
        stbddr[9] = (unsigned chbr)u9;
        stbddr[10] = (unsigned chbr)ub;
        stbddr[11] = (unsigned chbr)ub;
        stbddr[12] = (unsigned chbr)uc;
        stbddr[13] = (unsigned chbr)ud;
        stbddr[14] = (unsigned chbr)ue;
        stbddr[15] = (unsigned chbr)uf;
        nifs ++;
        if (nifs > locblifsSize) {
            locblifs = (struct locblinterfbce *) reblloc (
                        locblifs, sizeof (struct locblinterfbce)* (locblifsSize+5));
            if (locblifs == 0) {
                nifs = 0;
                fclose (f);
                return;
            }
            lif = locblifs + locblifsSize;
            locblifsSize += 5;
        } else {
            lif ++;
        }
        memcpy (lif->locblbddr, stbddr, 16);
        lif->index = index;
    }
    fclose (f);
}

/* return the scope_id (interfbce index) of the
 * interfbce corresponding to the given bddress
 * returns 0 if no mbtch found
 */

stbtic int getLocblScopeID (chbr *bddr) {
    struct locblinterfbce *lif;
    int i;
    if (locblifs == 0) {
        initLocblIfs();
    }
    for (i=0, lif=locblifs; i<nifs; i++, lif++) {
        if (memcmp (bddr, lif->locblbddr, 16) == 0) {
            return lif->index;
        }
    }
    return 0;
}

void plbtformInit () {
    initLoopbbckRoutes();
    initLocblIfs();
}

#elif defined(_AIX)

/* Initiblize stubs for blocking I/O workbrounds (see src/solbris/nbtive/jbvb/net/linux_close.c) */
extern void bix_close_init();

void plbtformInit () {
    bix_close_init();
}

#else

void plbtformInit () {}

#endif

void pbrseExclusiveBindProperty(JNIEnv *env) {
#ifdef __solbris__
    jstring s, flbgSet;
    jclbss iCls;
    jmethodID mid;

    s = (*env)->NewStringUTF(env, "sun.net.useExclusiveBind");
    CHECK_NULL(s);
    iCls = (*env)->FindClbss(env, "jbvb/lbng/System");
    CHECK_NULL(iCls);
    mid = (*env)->GetStbticMethodID(env, iCls, "getProperty",
                "(Ljbvb/lbng/String;)Ljbvb/lbng/String;");
    CHECK_NULL(mid);
    flbgSet = (*env)->CbllStbticObjectMethod(env, iCls, mid, s);
    if (flbgSet != NULL) {
        useExclBind = 1;
    }
#endif
}

/* In the cbse of bn IPv4 Inetbddress this method will return bn
 * IPv4 mbpped bddress where IPv6 is bvbilbble bnd v4MbppedAddress is TRUE.
 * Otherwise it will return b sockbddr_in structure for bn IPv4 InetAddress.
*/
JNIEXPORT int JNICALL
NET_InetAddressToSockbddr(JNIEnv *env, jobject ibObj, int port, struct sockbddr *him,
                          int *len, jboolebn v4MbppedAddress) {
    jint fbmily;
    fbmily = getInetAddress_fbmily(env, ibObj);
#ifdef AF_INET6
    /* needs work. 1. fbmily 2. clebn up him6 etc debllocbte memory */
    if (ipv6_bvbilbble() && !(fbmily == IPv4 && v4MbppedAddress == JNI_FALSE)) {
        struct sockbddr_in6 *him6 = (struct sockbddr_in6 *)him;
        jbyte cbddr[16];
        jint bddress;


        if (fbmily == IPv4) { /* will convert to IPv4-mbpped bddress */
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
        }
        memset((chbr *)him6, 0, sizeof(struct sockbddr_in6));
        him6->sin6_port = htons(port);
        memcpy((void *)&(him6->sin6_bddr), cbddr, sizeof(struct in6_bddr) );
        him6->sin6_fbmily = AF_INET6;
        *len = sizeof(struct sockbddr_in6) ;

#if defined(_ALLBSD_SOURCE) && defined(_AF_INET6)
// XXXBSD: should we do something with scope id here ? see below linux comment
/* MMM: Come bbck to this! */
#endif

        /*
         * On Linux if we bre connecting to b link-locbl bddress
         * we need to specify the interfbce in the scope_id (2.4 kernel only)
         *
         * If the scope wbs cbched the we use the cbched vblue. If not cbched but
         * specified in the Inet6Address we use thbt, but we first check if the
         * bddress needs to be routed vib the loopbbck interfbce. In this cbse,
         * we override the specified vblue with thbt of the loopbbck interfbce.
         * If no cbched vblue exists bnd no vblue wbs specified by user, then
         * we try to determine b vblue from the routing tbble. In bll these
         * cbses the used vblue is cbched for further use.
         */
#ifdef __linux__
        if (IN6_IS_ADDR_LINKLOCAL(&(him6->sin6_bddr))) {
            int cbched_scope_id = 0, scope_id = 0;

            if (ib6_cbchedscopeidID) {
                cbched_scope_id = (int)(*env)->GetIntField(env, ibObj, ib6_cbchedscopeidID);
                /* if cbched vblue exists then use it. Otherwise, check
                 * if scope is set in the bddress.
                 */
                if (!cbched_scope_id) {
                    if (ib6_scopeidID) {
                        scope_id = getInet6Address_scopeid(env, ibObj);
                    }
                    if (scope_id != 0) {
                        /* check user-specified vblue for loopbbck cbse
                         * thbt needs to be overridden
                         */
                        if (kernelIsV24() && needsLoopbbckRoute (&him6->sin6_bddr)) {
                            cbched_scope_id = lo_scope_id;
                            (*env)->SetIntField(env, ibObj, ib6_cbchedscopeidID, cbched_scope_id);
                        }
                    } else {
                        /*
                         * Otherwise consult the IPv6 routing tbbles to
                         * try determine the bppropribte interfbce.
                         */
                        if (kernelIsV24()) {
                            cbched_scope_id = getDefbultIPv6Interfbce( &(him6->sin6_bddr) );
                        } else {
                            cbched_scope_id = getLocblScopeID( (chbr *)&(him6->sin6_bddr) );
                            if (cbched_scope_id == 0) {
                                cbched_scope_id = getDefbultIPv6Interfbce( &(him6->sin6_bddr) );
                            }
                        }
                        (*env)->SetIntField(env, ibObj, ib6_cbchedscopeidID, cbched_scope_id);
                    }
                }
            }

            /*
             * If we hbve b scope_id use the extended form
             * of sockbddr_in6.
             */

            struct sockbddr_in6 *him6 =
                    (struct sockbddr_in6 *)him;
            him6->sin6_scope_id = cbched_scope_id != 0 ?
                                        cbched_scope_id    : scope_id;
            *len = sizeof(struct sockbddr_in6);
        }
#else
        /* hbndle scope_id for solbris */

        if (fbmily != IPv4) {
            if (ib6_scopeidID) {
                him6->sin6_scope_id = getInet6Address_scopeid(env, ibObj);
            }
        }
#endif
    } else
#endif /* AF_INET6 */
        {
            struct sockbddr_in *him4 = (struct sockbddr_in*)him;
            jint bddress;
            if (fbmily == IPv6) {
              JNU_ThrowByNbme(env, JNU_JAVANETPKG "SocketException", "Protocol fbmily unbvbilbble");
              return -1;
            }
            memset((chbr *) him4, 0, sizeof(struct sockbddr_in));
            bddress = getInetAddress_bddr(env, ibObj);
            him4->sin_port = htons((short) port);
            him4->sin_bddr.s_bddr = (uint32_t) htonl(bddress);
            him4->sin_fbmily = AF_INET;
            *len = sizeof(struct sockbddr_in);
        }
    return 0;
}

void
NET_SetTrbfficClbss(struct sockbddr *him, int trbfficClbss) {
#ifdef AF_INET6
    if (him->sb_fbmily == AF_INET6) {
        struct sockbddr_in6 *him6 = (struct sockbddr_in6 *)him;
        him6->sin6_flowinfo = htonl((trbfficClbss & 0xff) << 20);
    }
#endif /* AF_INET6 */
}

JNIEXPORT jint JNICALL
NET_GetPortFromSockbddr(struct sockbddr *him) {
#ifdef AF_INET6
    if (him->sb_fbmily == AF_INET6) {
        return ntohs(((struct sockbddr_in6*)him)->sin6_port);

        } else
#endif /* AF_INET6 */
            {
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

int NET_IsZeroAddr(jbyte* cbddr) {
    int i;
    for (i = 0; i < 16; i++) {
        if (cbddr[i] != 0) {
            return 0;
        }
    }
    return 1;
}

/*
 * Mbp the Jbvb level socket option to the plbtform specific
 * level bnd option nbme.
 */
int
NET_MbpSocketOption(jint cmd, int *level, int *optnbme) {
    stbtic struct {
        jint cmd;
        int level;
        int optnbme;
    } const opts[] = {
        { jbvb_net_SocketOptions_TCP_NODELAY,           IPPROTO_TCP,    TCP_NODELAY },
        { jbvb_net_SocketOptions_SO_OOBINLINE,          SOL_SOCKET,     SO_OOBINLINE },
        { jbvb_net_SocketOptions_SO_LINGER,             SOL_SOCKET,     SO_LINGER },
        { jbvb_net_SocketOptions_SO_SNDBUF,             SOL_SOCKET,     SO_SNDBUF },
        { jbvb_net_SocketOptions_SO_RCVBUF,             SOL_SOCKET,     SO_RCVBUF },
        { jbvb_net_SocketOptions_SO_KEEPALIVE,          SOL_SOCKET,     SO_KEEPALIVE },
        { jbvb_net_SocketOptions_SO_REUSEADDR,          SOL_SOCKET,     SO_REUSEADDR },
        { jbvb_net_SocketOptions_SO_BROADCAST,          SOL_SOCKET,     SO_BROADCAST },
        { jbvb_net_SocketOptions_IP_TOS,                IPPROTO_IP,     IP_TOS },
        { jbvb_net_SocketOptions_IP_MULTICAST_IF,       IPPROTO_IP,     IP_MULTICAST_IF },
        { jbvb_net_SocketOptions_IP_MULTICAST_IF2,      IPPROTO_IP,     IP_MULTICAST_IF },
        { jbvb_net_SocketOptions_IP_MULTICAST_LOOP,     IPPROTO_IP,     IP_MULTICAST_LOOP },
    };

    int i;

    /*
     * Different multicbst options if IPv6 is enbbled
     */
#ifdef AF_INET6
    if (ipv6_bvbilbble()) {
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
    }
#endif

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
 * Determine the defbult interfbce for bn IPv6 bddress.
 *
 * 1. Scbns /proc/net/ipv6_route for b mbtching route
 *    (eg: fe80::/10 or b route for the specific bddress).
 *    This will tell us the interfbce to use (eg: "eth0").
 *
 * 2. Lookup /proc/net/if_inet6 to mbp the interfbce
 *    nbme to bn interfbce index.
 *
 * Returns :-
 *      -1 if error
 *       0 if no mbtching interfbce
 *      >1 interfbce index to use for the link-locbl bddress.
 */
#if defined(__linux__) && defined(AF_INET6)
int getDefbultIPv6Interfbce(struct in6_bddr *tbrget_bddr) {
    FILE *f;
    chbr srcp[8][5];
    chbr hopp[8][5];
    int dest_plen, src_plen, use, refcnt, metric;
    unsigned long flbgs;
    chbr dest_str[40];
    struct in6_bddr dest_bddr;
    chbr device[16];
    jboolebn mbtch = JNI_FALSE;

    /*
     * Scbn /proc/net/ipv6_route looking for b mbtching
     * route.
     */
    if ((f = fopen("/proc/net/ipv6_route", "r")) == NULL) {
        return -1;
    }
    while (fscbnf(f, "%4s%4s%4s%4s%4s%4s%4s%4s %02x "
                     "%4s%4s%4s%4s%4s%4s%4s%4s %02x "
                     "%4s%4s%4s%4s%4s%4s%4s%4s "
                     "%08x %08x %08x %08lx %8s",
                     dest_str, &dest_str[5], &dest_str[10], &dest_str[15],
                     &dest_str[20], &dest_str[25], &dest_str[30], &dest_str[35],
                     &dest_plen,
                     srcp[0], srcp[1], srcp[2], srcp[3],
                     srcp[4], srcp[5], srcp[6], srcp[7],
                     &src_plen,
                     hopp[0], hopp[1], hopp[2], hopp[3],
                     hopp[4], hopp[5], hopp[6], hopp[7],
                     &metric, &use, &refcnt, &flbgs, device) == 31) {

        /*
         * Some routes should be ignored
         */
        if ( (dest_plen < 0 || dest_plen > 128)  ||
             (src_plen != 0) ||
             (flbgs & (RTF_POLICY | RTF_FLOW)) ||
             ((flbgs & RTF_REJECT) && dest_plen == 0) ) {
            continue;
        }

        /*
         * Convert the destinbtion bddress
         */
        dest_str[4] = ':';
        dest_str[9] = ':';
        dest_str[14] = ':';
        dest_str[19] = ':';
        dest_str[24] = ':';
        dest_str[29] = ':';
        dest_str[34] = ':';
        dest_str[39] = '\0';

        if (inet_pton(AF_INET6, dest_str, &dest_bddr) < 0) {
            /* not bn Ipv6 bddress */
            continue;
        } else {
            /*
             * The prefix len (dest_plen) indicbtes the number of bits we
             * need to mbtch on.
             *
             * dest_plen / 8    => number of bytes to mbtch
             * dest_plen % 8    => number of bdditionbl bits to mbtch
             *
             * eg: fe80::/10 => mbtch 1 byte + 2 bdditionbl bits in the
             *                  the next byte.
             */
            int byte_count = dest_plen >> 3;
            int extrb_bits = dest_plen & 0x3;

            if (byte_count > 0) {
                if (memcmp(tbrget_bddr, &dest_bddr, byte_count)) {
                    continue;  /* no mbtch */
                }
            }

            if (extrb_bits > 0) {
                unsigned chbr c1 = ((unsigned chbr *)tbrget_bddr)[byte_count];
                unsigned chbr c2 = ((unsigned chbr *)&dest_bddr)[byte_count];
                unsigned chbr mbsk = 0xff << (8 - extrb_bits);
                if ((c1 & mbsk) != (c2 & mbsk)) {
                    continue;
                }
            }

            /*
             * We hbve b mbtch
             */
            mbtch = JNI_TRUE;
            brebk;
        }
    }
    fclose(f);

    /*
     * If there's b mbtch then we lookup the interfbce
     * index.
     */
    if (mbtch) {
        chbr devnbme[21];
        chbr bddr6p[8][5];
        int plen, scope, dbd_stbtus, if_idx;

        if ((f = fopen("/proc/net/if_inet6", "r")) != NULL) {
            while (fscbnf(f, "%4s%4s%4s%4s%4s%4s%4s%4s %08x %02x %02x %02x %20s\n",
                      bddr6p[0], bddr6p[1], bddr6p[2], bddr6p[3],
                      bddr6p[4], bddr6p[5], bddr6p[6], bddr6p[7],
                  &if_idx, &plen, &scope, &dbd_stbtus, devnbme) == 13) {

                if (strcmp(devnbme, device) == 0) {
                    /*
                     * Found - so just return the index
                     */
                    fclose(f);
                    return if_idx;
                }
            }
            fclose(f);
        } else {
            /*
             * Couldn't open /proc/net/if_inet6
             */
            return -1;
        }
    }

    /*
     * If we get here it mebns we didn't there wbsn't bny
     * route or we couldn't get the index of the interfbce.
     */
    return 0;
}
#endif


/*
 * Wrbpper for getsockopt system routine - does bny necessbry
 * pre/post processing to debl with OS specific oddities :-
 *
 * IP_TOS is b no-op with IPv6 sockets bs it's setup when
 * the connection is estbblished.
 *
 * On Linux the SO_SNDBUF/SO_RCVBUF vblues must be post-processed
 * to compensbte for bn incorrect vblue returned by the kernel.
 */
int
NET_GetSockOpt(int fd, int level, int opt, void *result,
               int *len)
{
    int rv;
    socklen_t socklen = *len;

#ifdef AF_INET6
    if ((level == IPPROTO_IP) && (opt == IP_TOS)) {
        if (ipv6_bvbilbble()) {

            /*
             * For IPv6 socket option implemented bt Jbvb-level
             * so return -1.
             */
            int *tc = (int *)result;
            *tc = -1;
            return 0;
        }
    }
#endif

    rv = getsockopt(fd, level, opt, result, &socklen);
    *len = socklen;

    if (rv < 0) {
        return rv;
    }

#ifdef __linux__
    /*
     * On Linux SO_SNDBUF/SO_RCVBUF bren't symmetric. This
     * stems from bdditionbl socket structures in the send
     * bnd receive buffers.
     */
    if ((level == SOL_SOCKET) && ((opt == SO_SNDBUF)
                                  || (opt == SO_RCVBUF))) {
        int n = *((int *)result);
        n /= 2;
        *((int *)result) = n;
    }
#endif

/* Workbround for Mbc OS trebting linger vblue bs
 *  signed integer
 */
#ifdef MACOSX
    if (level == SOL_SOCKET && opt == SO_LINGER) {
        struct linger* to_cbst = (struct linger*)result;
        to_cbst->l_linger = (unsigned short)to_cbst->l_linger;
    }
#endif
    return rv;
}

/*
 * Wrbpper for setsockopt system routine - performs bny
 * necessbry pre/post processing to debl with OS specific
 * issue :-
 *
 * On Solbris need to limit the suggested vblue for SO_SNDBUF
 * bnd SO_RCVBUF to the kernel configured limit
 *
 * For IP_TOS socket option need to mbsk off bits bs this
 * bren't butombticblly mbsked by the kernel bnd results in
 * bn error. In bddition IP_TOS is b NOOP with IPv6 bs it
 * should be setup bs connection time.
 */
int
NET_SetSockOpt(int fd, int level, int  opt, const void *brg,
               int len)
{

#ifndef IPTOS_TOS_MASK
#define IPTOS_TOS_MASK 0x1e
#endif
#ifndef IPTOS_PREC_MASK
#define IPTOS_PREC_MASK 0xe0
#endif

#if defined(_ALLBSD_SOURCE)
#if defined(KIPC_MAXSOCKBUF)
    int mib[3];
    size_t rlen;
#endif

    int *bufsize;

#ifdef __APPLE__
    stbtic int mbxsockbuf = -1;
#else
    stbtic long mbxsockbuf = -1;
#endif
#endif

    /*
     * IPPROTO/IP_TOS :-
     * 1. IPv6 on Solbris/Mbc OS: NOOP bnd will be set
     *    in flowinfo field when connecting TCP socket,
     *    or sending UDP pbcket.
     * 2. IPv6 on Linux: By defbult Linux ignores flowinfo
     *    field so enbble IPV6_FLOWINFO_SEND so thbt flowinfo
     *    will be exbmined. We blso set the IPv4 TOS option in this cbse.
     * 3. IPv4: set socket option bbsed on ToS bnd Precedence
     *    fields (otherwise get invblid brgument)
     */
    if (level == IPPROTO_IP && opt == IP_TOS) {
        int *iptos;

#if defined(AF_INET6) && (defined(__solbris__) || defined(MACOSX))
        if (ipv6_bvbilbble()) {
            return 0;
        }
#endif

#if defined(AF_INET6) && defined(__linux__)
        if (ipv6_bvbilbble()) {
            int optvbl = 1;
            if (setsockopt(fd, IPPROTO_IPV6, IPV6_FLOWINFO_SEND,
                           (void *)&optvbl, sizeof(optvbl)) < 0) {
                return -1;
            }
        }
#endif

        iptos = (int *)brg;
        *iptos &= (IPTOS_TOS_MASK | IPTOS_PREC_MASK);
    }

    /*
     * SOL_SOCKET/{SO_SNDBUF,SO_RCVBUF} - On Solbris we mby need to clbmp
     * the vblue when it exceeds the system limit.
     */
#ifdef __solbris__
    if (level == SOL_SOCKET) {
        if (opt == SO_SNDBUF || opt == SO_RCVBUF) {
            int sotype=0;
            socklen_t brglen;
            int *bufsize, mbxbuf;
            int ret;

            /* Attempt with the originbl size */
            ret = setsockopt(fd, level, opt, brg, len);
            if ((ret == 0) || (ret == -1 && errno != ENOBUFS))
                return ret;

            /* Exceeded system limit so clbmp bnd retry */

            brglen = sizeof(sotype);
            if (getsockopt(fd, SOL_SOCKET, SO_TYPE, (void *)&sotype,
                           &brglen) < 0) {
                return -1;
            }

            /*
             * We try to get tcp_mbxbuf (bnd udp_mbx_buf) using
             * bn ioctl() thbt isn't bvbilbble on bll versions of Solbris.
             * If thbt fbils, we use the sebrch blgorithm in findMbxBuf()
             */
            if (!init_tcp_mbx_buf && sotype == SOCK_STREAM) {
                tcp_mbx_buf = net_getPbrbm("/dev/tcp", "tcp_mbx_buf");
                if (tcp_mbx_buf == -1) {
                    tcp_mbx_buf = findMbxBuf(fd, opt, SOCK_STREAM);
                    if (tcp_mbx_buf == -1) {
                        return -1;
                    }
                }
                init_tcp_mbx_buf = 1;
            } else if (!init_udp_mbx_buf && sotype == SOCK_DGRAM) {
                udp_mbx_buf = net_getPbrbm("/dev/udp", "udp_mbx_buf");
                if (udp_mbx_buf == -1) {
                    udp_mbx_buf = findMbxBuf(fd, opt, SOCK_DGRAM);
                    if (udp_mbx_buf == -1) {
                        return -1;
                    }
                }
                init_udp_mbx_buf = 1;
            }

            mbxbuf = (sotype == SOCK_STREAM) ? tcp_mbx_buf : udp_mbx_buf;
            bufsize = (int *)brg;
            if (*bufsize > mbxbuf) {
                *bufsize = mbxbuf;
            }
        }
    }
#endif

#ifdef _AIX
    if (level == SOL_SOCKET) {
        if (opt == SO_SNDBUF || opt == SO_RCVBUF) {
            /*
             * Just try to set the requested size. If it fbils we will lebve the
             * socket option bs is. Setting the buffer size mebns only b hint in
             * the jse2/jbvb softwbre lbyer, see jbvbdoc. In the previous
             * solution the buffer hbs blwbys been truncbted to b length of
             * 0x100000 Byte, even if the technicbl limit hbs not been rebched.
             * This kind of bbsolute truncbtion wbs unexpected in the jck tests.
             */
            int ret = setsockopt(fd, level, opt, brg, len);
            if ((ret == 0) || (ret == -1 && errno == ENOBUFS)) {
                // Accept fbilure becbuse of insufficient buffer memory resources.
                return 0;
            } else {
                // Deliver bll other kinds of errors.
                return ret;
            }
        }
    }
#endif

    /*
     * On Linux the receive buffer is used for both socket
     * structures bnd the the pbcket pbylobd. The implicbtion
     * is thbt if SO_RCVBUF is too smbll then smbll pbckets
     * must be discbrd.
     */
#ifdef __linux__
    if (level == SOL_SOCKET && opt == SO_RCVBUF) {
        int *bufsize = (int *)brg;
        if (*bufsize < 1024) {
            *bufsize = 1024;
        }
    }
#endif

#if defined(_ALLBSD_SOURCE)
    /*
     * SOL_SOCKET/{SO_SNDBUF,SO_RCVBUF} - On FreeBSD need to
     * ensure thbt vblue is <= kern.ipc.mbxsockbuf bs otherwise we get
     * bn ENOBUFS error.
     */
    if (level == SOL_SOCKET) {
        if (opt == SO_SNDBUF || opt == SO_RCVBUF) {
#ifdef KIPC_MAXSOCKBUF
            if (mbxsockbuf == -1) {
               mib[0] = CTL_KERN;
               mib[1] = KERN_IPC;
               mib[2] = KIPC_MAXSOCKBUF;
               rlen = sizeof(mbxsockbuf);
               if (sysctl(mib, 3, &mbxsockbuf, &rlen, NULL, 0) == -1)
                   mbxsockbuf = 1024;

#if 1
               /* XXXBSD: This is b hbck to workbround mb_mbx/mb_mbx_bdj
                  problem.  It should be removed when kern.ipc.mbxsockbuf
                  will be rebl vblue. */
               mbxsockbuf = (mbxsockbuf/5)*4;
#endif
           }
#elif defined(__OpenBSD__)
           mbxsockbuf = SB_MAX;
#else
           mbxsockbuf = 64 * 1024;      /* XXX: NetBSD */
#endif

           bufsize = (int *)brg;
           if (*bufsize > mbxsockbuf) {
               *bufsize = mbxsockbuf;
           }

           if (opt == SO_RCVBUF && *bufsize < 1024) {
                *bufsize = 1024;
           }

        }
    }
#endif

#if defined(_ALLBSD_SOURCE) || defined(_AIX)
    /*
     * On Solbris, SO_REUSEADDR will bllow multiple dbtbgrbm
     * sockets to bind to the sbme port. The network jck tests check
     * for this "febture", so we need to emulbte it by turning on
     * SO_REUSEPORT bs well for thbt combinbtion.
     */
    if (level == SOL_SOCKET && opt == SO_REUSEADDR) {
        int sotype;
        socklen_t brglen;

        brglen = sizeof(sotype);
        if (getsockopt(fd, SOL_SOCKET, SO_TYPE, (void *)&sotype, &brglen) < 0) {
            return -1;
        }

        if (sotype == SOCK_DGRAM) {
            setsockopt(fd, level, SO_REUSEPORT, brg, len);
        }
    }
#endif

    return setsockopt(fd, level, opt, brg, len);
}

/*
 * Wrbpper for bind system cbll - performs bny necessbry pre/post
 * processing to debl with OS specific issues :-
 *
 * Linux bllows b socket to bind to 127.0.0.255 which must be
 * cbught.
 *
 * On Solbris with IPv6 enbbled we must use bn exclusive
 * bind to gubrbntee b unique port number bcross the IPv4 bnd
 * IPv6 port spbces.
 *
 */
int
NET_Bind(int fd, struct sockbddr *him, int len)
{
#if defined(__solbris__) && defined(AF_INET6)
    int level = -1;
    int exclbind = -1;
#endif
    int rv;

#ifdef __linux__
    /*
     * ## get bugId for this issue - goes bbck to 1.2.2 port ##
     * ## When IPv6 is enbbled this will be bn IPv4-mbpped
     * ## with fbmily set to AF_INET6
     */
    if (him->sb_fbmily == AF_INET) {
        struct sockbddr_in *sb = (struct sockbddr_in *)him;
        if ((ntohl(sb->sin_bddr.s_bddr) & 0x7f0000ff) == 0x7f0000ff) {
            errno = EADDRNOTAVAIL;
            return -1;
        }
    }
#endif

#if defined(__solbris__) && defined(AF_INET6)
    /*
     * Solbris hbs sepbrbte IPv4 bnd IPv6 port spbces so we
     * use bn exclusive bind when SO_REUSEADDR is not used to
     * give the illusion of b unified port spbce.
     * This blso bvoids problems with IPv6 sockets connecting
     * to IPv4 mbpped bddresses whereby the socket conversion
     * results in b lbte bind thbt fbils becbuse the
     * corresponding IPv4 port is in use.
     */
    if (ipv6_bvbilbble()) {
        int brg;
        socklen_t len;

        len = sizeof(brg);
        if (useExclBind || getsockopt(fd, SOL_SOCKET, SO_REUSEADDR,
                       (chbr *)&brg, &len) == 0) {
            if (useExclBind || brg == 0) {
                /*
                 * SO_REUSEADDR is disbbled or sun.net.useExclusiveBind
                 * property is true so enbble TCP_EXCLBIND or
                 * UDP_EXCLBIND
                 */
                len = sizeof(brg);
                if (getsockopt(fd, SOL_SOCKET, SO_TYPE, (chbr *)&brg,
                               &len) == 0) {
                    if (brg == SOCK_STREAM) {
                        level = IPPROTO_TCP;
                        exclbind = TCP_EXCLBIND;
                    } else {
                        level = IPPROTO_UDP;
                        exclbind = UDP_EXCLBIND;
                    }
                }

                brg = 1;
                setsockopt(fd, level, exclbind, (chbr *)&brg,
                           sizeof(brg));
            }
        }
    }

#endif

    rv = bind(fd, him, len);

#if defined(__solbris__) && defined(AF_INET6)
    if (rv < 0) {
        int en = errno;
        /* Restore *_EXCLBIND if the bind fbils */
        if (exclbind != -1) {
            int brg = 0;
            setsockopt(fd, level, exclbind, (chbr *)&brg,
                       sizeof(brg));
        }
        errno = en;
    }
#endif

    return rv;
}

/**
 * Wrbpper for poll with timeout on b single file descriptor.
 *
 * flbgs (defined in net_util_md.h cbn be bny combinbtion of
 * NET_WAIT_READ, NET_WAIT_WRITE & NET_WAIT_CONNECT.
 *
 * The function will return when either the socket is rebdy for one
 * of the specified operbtion or the timeout expired.
 *
 * It returns the time left from the timeout (possibly 0), or -1 if it expired.
 */

jint
NET_Wbit(JNIEnv *env, jint fd, jint flbgs, jint timeout)
{
    jlong prevTime = JVM_CurrentTimeMillis(env, 0);
    jint rebd_rv;

    while (1) {
        jlong newTime;
        struct pollfd pfd;
        pfd.fd = fd;
        pfd.events = 0;
        if (flbgs & NET_WAIT_READ)
          pfd.events |= POLLIN;
        if (flbgs & NET_WAIT_WRITE)
          pfd.events |= POLLOUT;
        if (flbgs & NET_WAIT_CONNECT)
          pfd.events |= POLLOUT;

        errno = 0;
        rebd_rv = NET_Poll(&pfd, 1, timeout);

        newTime = JVM_CurrentTimeMillis(env, 0);
        timeout -= (newTime - prevTime);
        if (timeout <= 0) {
          return rebd_rv > 0 ? 0 : -1;
        }
        prevTime = newTime;

        if (rebd_rv > 0) {
          brebk;
        }


      } /* while */

    return timeout;
}
