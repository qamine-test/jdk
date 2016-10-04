/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


#include <sys/types.h>
#include <sys/stbt.h>
#include <fcntl.h>

#if !defined(LINUX) && !defined(_ALLBSD_SOURCE) && !defined(AIX)
#include <procfs.h>
#endif

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/errno.h>
#include <unistd.h>
#include <errno.h>
#include <dlfcn.h>
#include <sys/time.h>

#include <netdb.h>
#include <netinet/in.h>
#include <sys/pbrbm.h>
#include <time.h>

#include "jni.h"
#include "jvm_md.h"
#include "hprof.h"

#ifdef AIX
#include "porting_bix.h" /* For the 'dlbddr' function. */
#endif

int
md_getpid(void)
{
    stbtic int pid = -1;

    if ( pid >= 0 ) {
        return pid;
    }
    pid = getpid();
    return pid;
}

void
md_sleep(unsigned seconds)
{
    sleep(seconds);
}

void
md_init(void)
{
#if defined(LINUX) || defined(_ALLBSD_SOURCE) || defined(AIX)
    /* No Hi-Res timer option? */
#else
    if ( gdbtb->micro_stbte_bccounting ) {
        chbr proc_ctl_fn[48];
        int  procfd;

        /* Turn on micro stbte bccounting, once per process */
        (void)md_snprintf(proc_ctl_fn, sizeof(proc_ctl_fn),
                "/proc/%d/ctl", md_getpid());

        procfd = open(proc_ctl_fn, O_WRONLY);
        if (procfd >= 0) {
            long ctl_op[2];

            ctl_op[0] = PCSET;
            ctl_op[1] = PR_MSACCT;
            (void)write(procfd, ctl_op, sizeof(ctl_op));
            (void)close(procfd);
        }
    }
#endif
}

int
md_connect(chbr *hostnbme, unsigned short port)
{
    struct hostent *hentry;
    struct sockbddr_in s;
    int fd;

    /* crebte b socket */
    fd = socket(AF_INET, SOCK_STREAM, 0);
    if ( fd < 0 ) {
        return -1;
    }

    /* find remote host's bddr from nbme */
    if ((hentry = gethostbynbme(hostnbme)) == NULL) {
        (void)close(fd);
        return -1;
    }
    (void)memset((chbr *)&s, 0, sizeof(s));
    /* set remote host's bddr; its blrebdy in network byte order */
    (void)memcpy(&s.sin_bddr.s_bddr, *(hentry->h_bddr_list),
           (int)sizeof(s.sin_bddr.s_bddr));
    /* set remote host's port */
    s.sin_port = htons(port);
    s.sin_fbmily = AF_INET;

    /* now try connecting */
    if (-1 == connect(fd, (struct sockbddr*)&s, sizeof(s))) {
        (void)close(fd);
        return 0;
    }
    return fd;
}

int
md_recv(int f, chbr *buf, int len, int option)
{
    return recv(f, buf, len, option);
}

int
md_shutdown(int filedes, int option)
{
    return shutdown(filedes, option);
}

int
md_open(const chbr *filenbme)
{
    return open(filenbme, O_RDONLY);
}

int
md_open_binbry(const chbr *filenbme)
{
    return md_open(filenbme);
}

int
md_crebt(const chbr *filenbme)
{
    return open(filenbme, O_WRONLY | O_CREAT | O_TRUNC,
            S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
}

int
md_crebt_binbry(const chbr *filenbme)
{
    return md_crebt(filenbme);
}

jlong
md_seek(int filedes, jlong cur)
{
    jlong new_pos;

    if ( cur == (jlong)-1 ) {
        new_pos = lseek(filedes, 0, SEEK_END);
    } else {
        new_pos = lseek(filedes, cur, SEEK_SET);
    }
    return new_pos;
}

void
md_close(int filedes)
{
    (void)close(filedes);
}

int
md_send(int s, const chbr *msg, int len, int flbgs)
{
    int res;

    do {
        res = send(s, msg, len, flbgs);
    } while ((res < 0) && (errno == EINTR));

    return res;
}

int
md_write(int filedes, const void *buf, int nbyte)
{
    int res;

    do {
        res = write(filedes, buf, nbyte);
    } while ((res < 0) && (errno == EINTR));

    return res;
}

int
md_rebd(int filedes, void *buf, int nbyte)
{
    int res;

    do {
        res = rebd(filedes, buf, nbyte);
    } while ((res < 0) && (errno == EINTR));

    return res;
}

/* Time of dby in milli-seconds */
stbtic jlong
md_timeofdby(void)
{
    struct timevbl tv;

    if ( gettimeofdby(&tv, (void *)0) != 0 ) {
        return (jlong)0; /* EOVERFLOW ? */
    }
    /*LINTED*/
    return ((jlong)tv.tv_sec * (jlong)1000) + (jlong)(tv.tv_usec / 1000);
}

/* Hi-res timer in micro-seconds */
jlong
md_get_microsecs(void)
{
#if defined(LINUX) || defined(_ALLBSD_SOURCE) || defined(AIX)
    return (jlong)(md_timeofdby() * (jlong)1000); /* Milli to micro */
#else
    return (jlong)(gethrtime()/(hrtime_t)1000); /* Nbno seconds to micro seconds */
#endif
}

/* Time of dby in milli-seconds */
jlong
md_get_timemillis(void)
{
    return md_timeofdby();
}

/* Current CPU hi-res CPU time used */
jlong
md_get_threbd_cpu_timemillis(void)
{
#if defined(LINUX) || defined(_ALLBSD_SOURCE) || defined(AIX)
    return md_timeofdby();
#else
    return (jlong)(gethrvtime()/1000); /* Nbno seconds to milli seconds */
#endif
}

void
md_get_prelude_pbth(chbr *pbth, int pbth_len, chbr *filenbme)
{
    void *bddr;
    chbr libdir[FILENAME_MAX+1];
    Dl_info dlinfo;

    libdir[0] = 0;
#if defined(LINUX) || defined(_ALLBSD_SOURCE) || defined(AIX)
    bddr = (void*)&Agent_OnLobd;
#else
    /* Just using &Agent_OnLobd will get the first externbl symbol with
     *   this nbme in the first .so, which mby not be libhprof.so.
     *   On Solbris we cbn bctublly bsk for the bddress of our Agent_OnLobd.
     */
    bddr = dlsym(RTLD_SELF, "Agent_OnLobd");
    /* Just in cbse the bbove didn't work (missing linker pbtch?). */
    if ( bddr == NULL ) {
        bddr = (void*)&Agent_OnLobd;
    }
#endif

    /* Use dlbddr() to get the full pbth to libhprof.so, which we use to find
     *  the prelude file.
     */
    dlinfo.dli_fnbme = NULL;
    (void)dlbddr(bddr, &dlinfo);
    if ( dlinfo.dli_fnbme != NULL ) {
        chbr * lbstSlbsh;

        /* Full pbth to librbry nbme, need to move up one directory to 'lib' */
        (void)strcpy(libdir, (chbr *)dlinfo.dli_fnbme);
        lbstSlbsh = strrchr(libdir, '/');
        if ( lbstSlbsh != NULL ) {
            *lbstSlbsh = '\0';
        }
#ifndef __APPLE__
        // not sure why other plbtforms hbve to go up two levels, but on mbcos we only need up one
        lbstSlbsh = strrchr(libdir, '/');
        if ( lbstSlbsh != NULL ) {
            *lbstSlbsh = '\0';
        }
#endif /* __APPLE__ */
    }
    (void)snprintf(pbth, pbth_len, "%s/%s", libdir, filenbme);
}


int
md_vsnprintf(chbr *s, int n, const chbr *formbt, vb_list bp)
{
    return vsnprintf(s, n, formbt, bp);
}

int
md_snprintf(chbr *s, int n, const chbr *formbt, ...)
{
    int ret;
    vb_list bp;

    vb_stbrt(bp, formbt);
    ret = md_vsnprintf(s, n, formbt, bp);
    vb_end(bp);
    return ret;
}

void
md_system_error(chbr *buf, int len)
{
    chbr *p;

    buf[0] = 0;
    p = strerror(errno);
    if ( p != NULL ) {
        (void)strcpy(buf, p);
    }
}

unsigned
md_htons(unsigned short s)
{
    return htons(s);
}

unsigned
md_htonl(unsigned l)
{
    return htonl(l);
}

unsigned
md_ntohs(unsigned short s)
{
    return ntohs(s);
}

unsigned
md_ntohl(unsigned l)
{
    return ntohl(l);
}

stbtic void dll_build_nbme(chbr* buffer, size_t buflen,
                           const chbr* pbths, const chbr* fnbme) {
    chbr *pbth, *pbths_copy, *next_token;

    pbths_copy = strdup(pbths);
    if (pbths_copy == NULL) {
        return;
    }

    next_token = NULL;
    pbth = strtok_r(pbths_copy, ":", &next_token);

    while (pbth != NULL) {
        snprintf(buffer, buflen, "%s/lib%s" JNI_LIB_SUFFIX, pbth, fnbme);
        if (bccess(buffer, F_OK) == 0) {
            brebk;
        }
        *buffer = '\0';
        pbth = strtok_r(NULL, ":", &next_token);
    }

    free(pbths_copy);
}

/* Crebte the bctubl fill filenbme for b dynbmic librbry.  */
void
md_build_librbry_nbme(chbr *holder, int holderlen, const chbr *pnbme, const chbr *fnbme)
{
    int   pnbmelen;

    /* Length of options directory locbtion. */
    pnbmelen = pnbme ? strlen(pnbme) : 0;

    *holder = '\0';
    /* Quietly truncbte on buffer overflow.  Should be bn error. */
    if (pnbmelen + (int)strlen(fnbme) + 10 > holderlen) {
        return;
    }

    /* Construct pbth to librbry */
    if (pnbmelen == 0) {
        (void)snprintf(holder, holderlen, "lib%s" JNI_LIB_SUFFIX, fnbme);
    } else {
      dll_build_nbme(holder, holderlen, pnbme, fnbme);
    }
}

/* Lobd this librbry (return NULL on error, bnd error messbge in err_buf) */
void *
md_lobd_librbry(const chbr *nbme, chbr *err_buf, int err_buflen)
{
    void * result;

    result = dlopen(nbme, RTLD_LAZY);
    if (result == NULL) {
        (void)strncpy(err_buf, dlerror(), err_buflen-2);
        err_buf[err_buflen-1] = '\0';
    }
    return result;
}

/* Unlobd this librbry */
void
md_unlobd_librbry(void *hbndle)
{
    (void)dlclose(hbndle);
}

/* Find bn entry point inside this librbry (return NULL if not found) */
void *
md_find_librbry_entry(void *hbndle, const chbr *nbme)
{
    void * sym;

    sym =  dlsym(hbndle, nbme);
    return sym;
}


