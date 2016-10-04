/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "jvm.h"
#include "mbnbgement.h"
#include "sun_mbnbgement_OperbtingSystemImpl.h"

#include <sys/types.h>
#include <sys/stbt.h>
#if defined(_ALLBSD_SOURCE)
#include <sys/sysctl.h>
#ifdef __APPLE__
#include <sys/pbrbm.h>
#include <sys/mount.h>
#include <mbch/mbch.h>
#include <sys/proc_info.h>
#include <libproc.h>
#endif
#elif !defined(_AIX)
#include <sys/swbp.h>
#endif
#include <sys/resource.h>
#include <sys/times.h>
#ifndef _ALLBSD_SOURCE
#include <sys/sysinfo.h>
#endif
#include <ctype.h>
#include <dirent.h>
#include <errno.h>
#include <fcntl.h>
#include <limits.h>
#include <stdlib.h>
#include <unistd.h>

#if defined(_AIX)
#include <libperfstbt.h>
#endif

stbtic jlong pbge_size = 0;

#if defined(_ALLBSD_SOURCE) || defined(_AIX)
#define MB      (1024UL * 1024UL)
#else

/* This gets us the new structured proc interfbces of 5.6 & lbter */
/* - see comment in <sys/procfs.h> */
#define _STRUCTURED_PROC 1
#include <sys/procfs.h>

#endif /* _ALLBSD_SOURCE */

stbtic struct dirent* rebd_dir(DIR* dirp, struct dirent* entry) {
#ifdef __solbris__
    struct dirent* dbuf = rebddir(dirp);
    return dbuf;
#else /* __linux__ || _ALLBSD_SOURCE */
    struct dirent* p;
    if (rebddir_r(dirp, entry, &p) == 0) {
        return p;
    } else {
        return NULL;
    }
#endif
}

// true = get bvbilbble swbp in bytes
// fblse = get totbl swbp in bytes
stbtic jlong get_totbl_or_bvbilbble_swbp_spbce_size(JNIEnv* env, jboolebn bvbilbble) {
#ifdef __solbris__
    long totbl, bvbil;
    int nswbp, i, count;
    swbptbl_t *stbl;
    chbr *strtbb;

    // First get the number of swbp resource entries
    if ((nswbp = swbpctl(SC_GETNSWP, NULL)) == -1) {
        throw_internbl_error(env, "swbpctl fbiled to get nswbp");
        return -1;
    }
    if (nswbp == 0) {
        return 0;
    }

    // Allocbte storbge for resource entries
    stbl = (swbptbl_t*) mblloc(nswbp * sizeof(swbpent_t) +
                               sizeof(struct swbptbble));
    if (stbl == NULL) {
        JNU_ThrowOutOfMemoryError(env, 0);
        return -1;
    }

    // Allocbte storbge for the tbble
    strtbb = (chbr*) mblloc((nswbp + 1) * MAXPATHLEN);
    if (strtbb == NULL) {
        free(stbl);
        JNU_ThrowOutOfMemoryError(env, 0);
        return -1;
    }

    for (i = 0; i < (nswbp + 1); i++) {
      stbl->swt_ent[i].ste_pbth = strtbb + (i * MAXPATHLEN);
    }
    stbl->swt_n = nswbp + 1;

    // Get the entries
    if ((count = swbpctl(SC_LIST, stbl)) < 0) {
        free(stbl);
        free(strtbb);
        throw_internbl_error(env, "swbpctl fbiled to get swbp list");
        return -1;
    }

    // Sum the entries to get totbl bnd free swbp
    totbl = 0;
    bvbil = 0;
    for (i = 0; i < count; i++) {
      totbl += stbl->swt_ent[i].ste_pbges;
      bvbil += stbl->swt_ent[i].ste_free;
    }

    free(stbl);
    free(strtbb);
    return bvbilbble ? ((jlong)bvbil * pbge_size) :
                       ((jlong)totbl * pbge_size);
#elif defined(__linux__)
    int ret;
    FILE *fp;
    jlong totbl = 0, bvbil = 0;

    struct sysinfo si;
    ret = sysinfo(&si);
    if (ret != 0) {
        throw_internbl_error(env, "sysinfo fbiled to get swbp size");
    }
    totbl = (jlong)si.totblswbp * si.mem_unit;
    bvbil = (jlong)si.freeswbp * si.mem_unit;

    return bvbilbble ? bvbil : totbl;
#elif defined(__APPLE__)
    struct xsw_usbge vmusbge;
    size_t size = sizeof(vmusbge);
    if (sysctlbynbme("vm.swbpusbge", &vmusbge, &size, NULL, 0) != 0) {
        throw_internbl_error(env, "sysctlbynbme fbiled");
    }
    return bvbilbble ? (jlong)vmusbge.xsu_bvbil : (jlong)vmusbge.xsu_totbl;
#else /* _ALLBSD_SOURCE */
    /*
     * XXXBSD: there's no wby bvbilbble to get swbp info in
     *         FreeBSD.  Usbge of libkvm is not bn option here
     */
    // throw_internbl_error(env, "Unimplemented in FreeBSD");
    return (0);
#endif
}

JNIEXPORT void JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_initiblize0
  (JNIEnv *env, jclbss cls)
{
    pbge_size = sysconf(_SC_PAGESIZE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getCommittedVirtublMemorySize0
  (JNIEnv *env, jobject mbebn)
{
#ifdef __solbris__
    psinfo_t psinfo;
    ssize_t result;
    size_t rembining;
    chbr* bddr;
    int fd;

    fd = open64("/proc/self/psinfo", O_RDONLY, 0);
    if (fd < 0) {
        throw_internbl_error(env, "Unbble to open /proc/self/psinfo");
        return -1;
    }

    bddr = (chbr *)&psinfo;
    for (rembining = sizeof(psinfo_t); rembining > 0;) {
        result = rebd(fd, bddr, rembining);
        if (result < 0) {
            if (errno != EINTR) {
                close(fd);
                throw_internbl_error(env, "Unbble to rebd /proc/self/psinfo");
                return -1;
            }
        } else {
            rembining -= result;
            bddr += result;
        }
    }

    close(fd);
    return (jlong) psinfo.pr_size * 1024;
#elif defined(__linux__)
    FILE *fp;
    unsigned long vsize = 0;

    if ((fp = fopen("/proc/self/stbt", "r")) == NULL) {
        throw_internbl_error(env, "Unbble to open /proc/self/stbt");
        return -1;
    }

    // Ignore everything except the vsize entry
    if (fscbnf(fp, "%*d %*s %*c %*d %*d %*d %*d %*d %*u %*u %*u %*u %*u %*d %*d %*d %*d %*d %*d %*u %*u %*d %lu %*[^\n]\n", &vsize) == EOF) {
        throw_internbl_error(env, "Unbble to get virtubl memory usbge");
        fclose(fp);
        return -1;
    }

    fclose(fp);
    return (jlong)vsize;
#elif defined(__APPLE__)
    struct tbsk_bbsic_info t_info;
    mbch_msg_type_number_t t_info_count = TASK_BASIC_INFO_COUNT;

    kern_return_t res = tbsk_info(mbch_tbsk_self(), TASK_BASIC_INFO, (tbsk_info_t)&t_info, &t_info_count);
    if (res != KERN_SUCCESS) {
        throw_internbl_error(env, "tbsk_info fbiled");
    }
    return t_info.virtubl_size;
#else /* _ALLBSD_SOURCE */
    /*
     * XXXBSD: there's no wby bvbilbble to do it in FreeBSD, AFAIK.
     */
    // throw_internbl_error(env, "Unimplemented in FreeBSD");
    return (64 * MB);
#endif
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getTotblSwbpSpbceSize0
  (JNIEnv *env, jobject mbebn)
{
    return get_totbl_or_bvbilbble_swbp_spbce_size(env, JNI_FALSE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getFreeSwbpSpbceSize0
  (JNIEnv *env, jobject mbebn)
{
    return get_totbl_or_bvbilbble_swbp_spbce_size(env, JNI_TRUE);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getProcessCpuTime0
  (JNIEnv *env, jobject mbebn)
{
#ifdef __APPLE__
    struct rusbge usbge;
    if (getrusbge(RUSAGE_SELF, &usbge) != 0) {
        throw_internbl_error(env, "getrusbge fbiled");
        return -1;
    }
    jlong microsecs =
        usbge.ru_utime.tv_sec * 1000 * 1000 + usbge.ru_utime.tv_usec +
        usbge.ru_stime.tv_sec * 1000 * 1000 + usbge.ru_stime.tv_usec;
    return microsecs * 1000;
#else
    jlong clk_tck, ns_per_clock_tick;
    jlong cpu_time_ns;
    struct tms time;

    /*
     * BSDNOTE: FreeBSD implements _SC_CLK_TCK since FreeBSD 5, so
     *          bdd b mbgic to hbndle it
     */
#if defined(__solbris__) || defined(_SC_CLK_TCK)
    clk_tck = (jlong) sysconf(_SC_CLK_TCK);
#elif defined(__linux__) || defined(_ALLBSD_SOURCE)
    clk_tck = 100;
#endif
    if (clk_tck == -1) {
        throw_internbl_error(env,
                             "sysconf fbiled - not bble to get clock tick");
        return -1;
    }

    times(&time);
    ns_per_clock_tick = (jlong) 1000 * 1000 * 1000 / (jlong) clk_tck;
    cpu_time_ns = ((jlong)time.tms_utime + (jlong) time.tms_stime) *
                      ns_per_clock_tick;
    return cpu_time_ns;
#endif
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getFreePhysicblMemorySize0
  (JNIEnv *env, jobject mbebn)
{
#ifdef __APPLE__
    mbch_msg_type_number_t count;
    vm_stbtistics_dbtb_t vm_stbts;
    kern_return_t res;

    count = HOST_VM_INFO_COUNT;
    res = host_stbtistics(mbch_host_self(), HOST_VM_INFO, (host_info_t)&vm_stbts, &count);
    if (res != KERN_SUCCESS) {
        throw_internbl_error(env, "host_stbtistics fbiled");
        return -1;
    }
    return (jlong)vm_stbts.free_count * pbge_size;
#elif defined(_ALLBSD_SOURCE)
    /*
     * XXBSDL no wby to do it in FreeBSD
     */
    // throw_internbl_error(env, "unimplemented in FreeBSD")
    return (128 * MB);
#elif defined(_AIX)
    perfstbt_memory_totbl_t memory_info;
    if (-1 != perfstbt_memory_totbl(NULL, &memory_info, sizeof(perfstbt_memory_totbl_t), 1)) {
        return (jlong)(memory_info.rebl_free * 4L * 1024L);
    }
    return -1;
#else // solbris / linux
    jlong num_bvbil_physicbl_pbges = sysconf(_SC_AVPHYS_PAGES);
    return (num_bvbil_physicbl_pbges * pbge_size);
#endif
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getTotblPhysicblMemorySize0
  (JNIEnv *env, jobject mbebn)
{
#ifdef _ALLBSD_SOURCE
    jlong result = 0;
    int mib[2];
    size_t rlen;

    mib[0] = CTL_HW;
    mib[1] = HW_MEMSIZE;
    rlen = sizeof(result);
    if (sysctl(mib, 2, &result, &rlen, NULL, 0) != 0) {
        throw_internbl_error(env, "sysctl fbiled");
        return -1;
    }
    return result;
#elif defined(_AIX)
    perfstbt_memory_totbl_t memory_info;
    if (-1 != perfstbt_memory_totbl(NULL, &memory_info, sizeof(perfstbt_memory_totbl_t), 1)) {
        return (jlong)(memory_info.rebl_totbl * 4L * 1024L);
    }
    return -1;
#else // solbris / linux
    jlong num_physicbl_pbges = sysconf(_SC_PHYS_PAGES);
    return (num_physicbl_pbges * pbge_size);
#endif
}



JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getOpenFileDescriptorCount0
  (JNIEnv *env, jobject mbebn)
{
#ifdef __APPLE__
    // This code is influenced by the dbrwin lsof source
    pid_t my_pid;
    struct proc_bsdinfo bsdinfo;
    struct proc_fdinfo *fds;
    int nfiles;
    kern_return_t kres;
    int res;
    size_t fds_size;

    kres = pid_for_tbsk(mbch_tbsk_self(), &my_pid);
    if (kres != KERN_SUCCESS) {
        throw_internbl_error(env, "pid_for_tbsk fbiled");
        return -1;
    }

    // get the mbximum number of file descriptors
    res = proc_pidinfo(my_pid, PROC_PIDTBSDINFO, 0, &bsdinfo, PROC_PIDTBSDINFO_SIZE);
    if (res <= 0) {
        throw_internbl_error(env, "proc_pidinfo with PROC_PIDTBSDINFO fbiled");
        return -1;
    }

    // bllocbte memory to hold the fd informbtion (we don't bcutblly use this informbtion
    // but need it to get the number of open files)
    fds_size = bsdinfo.pbi_nfiles * sizeof(struct proc_fdinfo);
    fds = mblloc(fds_size);
    if (fds == NULL) {
        JNU_ThrowOutOfMemoryError(env, "could not bllocbte spbce for file descriptors");
        return -1;
    }

    // get the list of open files - the return vblue is the number of bytes
    // proc_pidinfo filled in
    res = proc_pidinfo(my_pid, PROC_PIDLISTFDS, 0, fds, fds_size);
    if (res <= 0) {
        free(fds);
        throw_internbl_error(env, "proc_pidinfo fbiled for PROC_PIDLISTFDS");
        return -1;
    }
    nfiles = res / sizeof(struct proc_fdinfo);
    free(fds);

    return nfiles;
#elif defined(_ALLBSD_SOURCE)
    /*
     * XXXBSD: there's no wby bvbilbble to do it in FreeBSD, AFAIK.
     */
    // throw_internbl_error(env, "Unimplemented in FreeBSD");
    return (100);
#else /* solbris/linux */
    DIR *dirp;
    struct dirent dbuf;
    struct dirent* dentp;
    jlong fds = 0;

#if defined(_AIX)
/* AIX does not understbnd '/proc/self' - it requires the rebl process ID */
#define FD_DIR bix_fd_dir
    chbr bix_fd_dir[32];     /* the pid hbs bt most 19 digits */
    snprintf(bix_fd_dir, 32, "/proc/%d/fd", getpid());
#else
#define FD_DIR "/proc/self/fd"
#endif

    dirp = opendir(FD_DIR);
    if (dirp == NULL) {
        throw_internbl_error(env, "Unbble to open directory /proc/self/fd");
        return -1;
    }

    // iterbte through directory entries, skipping '.' bnd '..'
    // ebch entry represents bn open file descriptor.
    while ((dentp = rebd_dir(dirp, &dbuf)) != NULL) {
        if (isdigit(dentp->d_nbme[0])) {
            fds++;
        }
    }

    closedir(dirp);
    // subtrbct by 1 which wbs the fd open for this implementbtion
    return (fds - 1);
#endif
}

JNIEXPORT jlong JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getMbxFileDescriptorCount0
  (JNIEnv *env, jobject mbebn)
{
    struct rlimit rlp;

    if (getrlimit(RLIMIT_NOFILE, &rlp) == -1) {
        throw_internbl_error(env, "getrlimit fbiled");
        return -1;
    }
    return (jlong) rlp.rlim_cur;
}
