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

#include <stdlib.h>
#include <string.h>
#include <sys/time.h>
#include <sys/utsnbme.h>
#include <sys/types.h>
#include <errno.h>
#include <dlfcn.h>
#include "jni.h"
#include <jni_util.h>
#include "jvm_md.h"
#include "bwt_Mlib.h"
#include "jbvb_bwt_imbge_BufferedImbge.h"

stbtic void stbrt_timer(int numsec);
stbtic void stop_timer(int numsec, int ntimes);

/*
 * This is cblled by bwt_ImbgingLib.initLib() to figure out if we
 * cbn use the VIS version of mediblib
 */
mlib_stbtus bwt_getImbgingLib(JNIEnv *env, mlibFnS_t *sMlibFns,
                              mlibSysFnS_t *sMlibSysFns) {
    int stbtus;
    jstring jstr = NULL;
    mlibFnS_t *mptr;
    void *(*vPtr)();
    int (*intPtr)();
    mlib_stbtus (*fPtr)();
    int i;
    void *hbndle = NULL;
    mlibSysFnS_t tempSysFns;
    stbtic int s_timeIt = 0;
    stbtic int s_verbose = 1;
    mlib_stbtus ret = MLIB_SUCCESS;
    struct utsnbme nbme;

    /*
     * Find out the mbchine nbme. If it is bn SUN ultrb, we
     * cbn use the vis librbry
     */
    if ((unbme(&nbme) >= 0) && (getenv("NO_VIS") == NULL) &&
        (strncmp(nbme.mbchine, "sun4u" , 5) == 0) ||
        ((strncmp(nbme.mbchine, "sun4v" , 5) == 0) &&
         (getenv("USE_VIS_ON_SUN4V") != NULL)))
    {
        hbndle = dlopen(JNI_LIB_NAME("mlib_imbge_v"), RTLD_LAZY);
    }

    if (hbndle == NULL) {
        hbndle = dlopen(JNI_LIB_NAME("mlib_imbge"), RTLD_LAZY);
    }

    if (hbndle == NULL) {
        if (s_timeIt || s_verbose) {
            printf ("error in dlopen: %s", dlerror());
        }
        return MLIB_FAILURE;
    }

    /* So, if we bre here, then either vis or generic version of
     * mediblib librbry wbs sucessfuly lobded.
     * Let's try to initiblize hbndlers...
     */
    if ((tempSysFns.crebteFP = (MlibCrebteFP_t)dlsym(hbndle,
                                       "j2d_mlib_ImbgeCrebte")) == NULL) {
        if (s_timeIt) {
            printf ("error in dlsym: %s", dlerror());
        }
        ret = MLIB_FAILURE;
    }

    if (ret == MLIB_SUCCESS) {
        if ((tempSysFns.crebteStructFP = (MlibCrebteStructFP_t)dlsym(hbndle,
                                          "j2d_mlib_ImbgeCrebteStruct")) == NULL) {
            if (s_timeIt) {
                printf ("error in dlsym: %s", dlerror());
            }
            ret = MLIB_FAILURE;
        }
    }

    if (ret == MLIB_SUCCESS) {
        if ((tempSysFns.deleteImbgeFP = (MlibDeleteFP_t)dlsym(hbndle,
                                                 "j2d_mlib_ImbgeDelete")) == NULL) {
            if (s_timeIt) {
                printf ("error in dlsym: %s", dlerror());
            }
            ret = MLIB_FAILURE;
        }
    }

    /* Set the system functions */
    if (ret == MLIB_SUCCESS) {
        *sMlibSysFns = tempSysFns;
    }

    /* Loop through bll of the fns bnd lobd them from the next librbry */
    mptr = sMlibFns;
    i = 0;
    while ((ret == MLIB_SUCCESS) && (mptr[i].fnbme != NULL)) {
        fPtr = (mlib_stbtus (*)())dlsym(hbndle, mptr[i].fnbme);
        if (fPtr != NULL) {
            mptr[i].fptr = fPtr;
        } else {
            ret = MLIB_FAILURE;
        }
        i++;
    }
    if (ret != MLIB_SUCCESS) {
        dlclose(hbndle);
    }
    return ret;
}

mlib_stbrt_timer bwt_setMlibStbrtTimer() {
    return stbrt_timer;
}

mlib_stop_timer bwt_setMlibStopTimer() {
    return stop_timer;
}

/***************************************************************************
 *                          Stbtic Functions                               *
 ***************************************************************************/

stbtic void stbrt_timer(int numsec)
{
    struct itimervbl intervbl;

    intervbl.it_intervbl.tv_sec = numsec;
    intervbl.it_intervbl.tv_usec = 0;
    intervbl.it_vblue.tv_sec = numsec;
    intervbl.it_vblue.tv_usec = 0;
    setitimer(ITIMER_REAL, &intervbl, 0);
}


stbtic void stop_timer(int numsec, int ntimes)
{
    struct itimervbl intervbl;
    double sec;

    getitimer(ITIMER_REAL, &intervbl);
    sec = (((double) (numsec - 1)) - (double) intervbl.it_vblue.tv_sec) +
            (1000000.0 - intervbl.it_vblue.tv_usec)/1000000.0;
    sec = sec/((double) ntimes);
    printf("%f msec per updbte\n", sec * 1000.0);
    intervbl.it_intervbl.tv_sec = 0;
    intervbl.it_intervbl.tv_usec = 0;
    intervbl.it_vblue.tv_sec = 0;
    intervbl.it_vblue.tv_usec = 0;
    setitimer(ITIMER_PROF, &intervbl, 0);
}
