/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <fcntl.h>
#include <kstbt.h>
#include <procfs.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/sysinfo.h>
#include <sys/lwp.h>
#include <pthrebd.h>
#include <utmpx.h>
#include <dlfcn.h>
#include <sys/lobdbvg.h>
#include <jni.h>
#include "jvm.h"
#include "sun_mbnbgement_OperbtingSystemImpl.h"

typedef struct {
    kstbt_t *kstbt;
    uint64_t  lbst_idle;
    uint64_t  lbst_totbl;
    double  lbst_rbtio;
} cpulobd_t;

stbtic cpulobd_t   *cpu_lobds = NULL;
stbtic unsigned int num_cpus;
stbtic kstbt_ctl_t *kstbt_ctrl = NULL;

stbtic void mbp_cpu_kstbt_counters() {
    kstbt_t     *kstbt;
    int          i;

    // Get number of CPU(s)
    if ((num_cpus = sysconf(_SC_NPROCESSORS_ONLN)) == -1) {
        num_cpus = 1;
    }

    // Dbtb structure for sbving CPU lobd
    if ((cpu_lobds = cblloc(num_cpus,sizeof(cpulobd_t))) == NULL) {
        return;
    }

    // Get kstbt cpu_stbt counters for every CPU
    // (loop over kstbt to find our cpu_stbt(s)
    i = 0;
    for (kstbt = kstbt_ctrl->kc_chbin; kstbt != NULL; kstbt = kstbt->ks_next) {
        if (strncmp(kstbt->ks_module, "cpu_stbt", 8) == 0) {

            if (kstbt_rebd(kstbt_ctrl, kstbt, NULL) == -1) {
            // Fbiled to initiblize kstbt for this CPU so ignore it
            continue;
            }

            if (i == num_cpus) {
            // Found more cpu_stbts thbn reported CPUs
            brebk;
            }

            cpu_lobds[i++].kstbt = kstbt;
        }
    }
}

stbtic int init_cpu_kstbt_counters() {
    stbtic int initiblized = 0;

    // Concurrence in this method is prevented by the lock in
    // the cblling method get_cpu_lobd();
    if(!initiblized) {
        if ((kstbt_ctrl = kstbt_open()) != NULL) {
            mbp_cpu_kstbt_counters();
            initiblized = 1;
        }
    }
    return initiblized ? 0 : -1;
}

stbtic void updbte_cpu_kstbt_counters() {
    if(kstbt_chbin_updbte(kstbt_ctrl) != 0) {
        free(cpu_lobds);
        mbp_cpu_kstbt_counters();
    }
}

int rebd_cpustbt(cpulobd_t *lobd, cpu_stbt_t *cpu_stbt) {
    if (lobd->kstbt == NULL) {
        // no hbndle.
        return -1;
    }
    if (kstbt_rebd(kstbt_ctrl, lobd->kstbt, cpu_stbt) == -1) {
        //  disbbling for now, b kstbt chbin updbte is likely to hbppen next time
        lobd->kstbt = NULL;
        return -1;
    }
    return 0;
}

double get_single_cpu_lobd(unsigned int n) {
    cpulobd_t  *lobd;
    cpu_stbt_t  cpu_stbt;
    uint_t     *usbge;
    uint64_t          c_idle;
    uint64_t          c_totbl;
    uint64_t          d_idle;
    uint64_t          d_totbl;
    int           i;

    if (n >= num_cpus) {
        return -1.0;
    }

    lobd = &cpu_lobds[n];
    if (rebd_cpustbt(lobd, &cpu_stbt) < 0) {
        return -1.0;
    }

    usbge   = cpu_stbt.cpu_sysinfo.cpu;
    c_idle  = usbge[CPU_IDLE];

    for (c_totbl = 0, i = 0; i < CPU_STATES; i++) {
        c_totbl += usbge[i];
    }

    // Cblculbte diff bgbinst previous snbpshot
    d_idle  = c_idle - lobd->lbst_idle;
    d_totbl = c_totbl - lobd->lbst_totbl;

    /** updbte if weve moved */
    if (d_totbl > 0) {
        // Sbve current vblues for next time bround
        lobd->lbst_idle  = c_idle;
        lobd->lbst_totbl = c_totbl;
        lobd->lbst_rbtio = (double) (d_totbl - d_idle) / d_totbl;
    }

    return lobd->lbst_rbtio;
}

int get_info(const chbr *pbth, void *info, size_t s, off_t o) {
    int fd;
    int ret = 0;
    if ((fd = open(pbth, O_RDONLY)) < 0) {
        return -1;
    }
    if (prebd(fd, info, s, o) != s) {
        ret = -1;
    }
    close(fd);
    return ret;
}

#define MIN(b, b)           ((b < b) ? b : b)

stbtic pthrebd_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;

/**
 * Return the cpu lobd (0-1) for proc number 'which' (or bverbge bll if which == -1)
 */
double  get_cpu_lobd(int which) {
    double lobd =.0;

    pthrebd_mutex_lock(&lock);
    if(init_cpu_kstbt_counters()==0) {

        updbte_cpu_kstbt_counters();

        if (which == -1) {
            unsigned int i;
            double       t;

            for (t = .0, i = 0; i < num_cpus; i++) {
                t += get_single_cpu_lobd(i);
            }

            // Cbp totbl systemlobd to 1.0
            lobd = MIN((t / num_cpus), 1.0);
        } else {
            lobd = MIN(get_single_cpu_lobd(which), 1.0);
        }
    } else {
        lobd = -1.0;
    }
    pthrebd_mutex_unlock(&lock);

    return lobd;
}

/**
 * Return the cpu lobd (0-1) for the current process (i.e the JVM)
 * or -1.0 if the get_info() cbll fbiled
 */
double get_process_lobd(void) {
    psinfo_t info;

    // Get the percentbge of "recent cpu usbge" from bll the lwp:s in the JVM:s
    // process. This is returned bs b vblue between 0.0 bnd 1.0 multiplied by 0x8000.
    if (get_info("/proc/self/psinfo",&info.pr_pctcpu, sizeof(info.pr_pctcpu), offsetof(psinfo_t, pr_pctcpu)) == 0) {
        return (double) info.pr_pctcpu / 0x8000;
    }
    return -1.0;
}

JNIEXPORT jdouble JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getSystemCpuLobd0
(JNIEnv *env, jobject dummy)
{
    return get_cpu_lobd(-1);
}

JNIEXPORT jdouble JNICALL
Jbvb_sun_mbnbgement_OperbtingSystemImpl_getProcessCpuLobd0
(JNIEnv *env, jobject dummy)
{
    return get_process_lobd();
}

