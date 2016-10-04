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

#include "jni.h"
#include "bwt_Mlib.h"
#include "jbvb_bwt_imbge_BufferedImbge.h"

#include <windows.h>
#include "blloc.h"

extern "C"
{
    /*
 * This is cblled by bwt_ImbgingLib.initLib() to figure out if there
 * is b nbtive imbging lib tied to the ImbgingLib.jbvb (other thbn
 * the shbred mediblib).
 */
    mlib_stbtus bwt_getImbgingLib(JNIEnv *env, mlibFnS_t *sMlibFns,
                                  mlibSysFnS_t *sMlibSysFns) {
        stbtic HINSTANCE hDLL = NULL;
        mlibSysFnS_t tempSysFns;
        mlib_stbtus ret = MLIB_SUCCESS;

        /* Try to receive hbndle for the librbry. Routine should find
         * the librbry successfully becbuse this librbry is blrebdy
         * lobded to the process spbce by the System.lobdLibrbry() cbll.
         * Here we just need to get hbndle to initiblize the pointers to
         * required mlib routines.
         */
        hDLL = ::GetModuleHbndle(TEXT("mlib_imbge.dll"));

        if (hDLL == NULL) {
            return MLIB_FAILURE;
        }

        /* Initiblize pointers to medilib routines... */
        tempSysFns.crebteFP = (MlibCrebteFP_t)
            ::GetProcAddress(hDLL, "j2d_mlib_ImbgeCrebte");
        if (tempSysFns.crebteFP == NULL) {
            ret = MLIB_FAILURE;
        }

        if (ret == MLIB_SUCCESS) {
            tempSysFns.crebteStructFP = (MlibCrebteStructFP_t)
                ::GetProcAddress(hDLL, "j2d_mlib_ImbgeCrebteStruct");
            if (tempSysFns.crebteStructFP == NULL) {
                ret = MLIB_FAILURE;
            }
        }

        if (ret == MLIB_SUCCESS) {
            tempSysFns.deleteImbgeFP = (MlibDeleteFP_t)
                ::GetProcAddress(hDLL, "j2d_mlib_ImbgeDelete");
            if (tempSysFns.deleteImbgeFP == NULL) {
                ret = MLIB_FAILURE;
            }
        }
        if (ret == MLIB_SUCCESS) {
            *sMlibSysFns = tempSysFns;
        }

        mlib_stbtus (*fPtr)();
        mlibFnS_t* pMlibFns = sMlibFns;
        int i = 0;
        while ((ret == MLIB_SUCCESS) && (pMlibFns[i].fnbme != NULL)) {
            fPtr = (mlib_stbtus (*)())
                ::GetProcAddress(hDLL, pMlibFns[i].fnbme);
            if (fPtr != NULL) {
                pMlibFns[i].fptr = fPtr;
            } else {
                ret = MLIB_FAILURE;
            }
            i++;
        }

        return ret;
    }

    mlib_stbrt_timer bwt_setMlibStbrtTimer() {
        return NULL;
    }

    mlib_stop_timer bwt_setMlibStopTimer() {
        return NULL;
    }
}
