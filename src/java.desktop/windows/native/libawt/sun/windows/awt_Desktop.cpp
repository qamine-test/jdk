/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jni_util.h"
#include "bwt.h"
#include <jni.h>
#include <shellbpi.h>
#include <flobt.h>

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Clbss:     sun_bwt_windows_WDesktopPeer
 * Method:    ShellExecute
 * Signbture: (Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_windows_WDesktopPeer_ShellExecute
  (JNIEnv *env, jclbss cls, jstring fileOrUri_j, jstring verb_j)
{
    LPCWSTR fileOrUri_c = JNU_GetStringPlbtformChbrs(env, fileOrUri_j, JNI_FALSE);
    CHECK_NULL_RETURN(fileOrUri_c, NULL);
    LPCWSTR verb_c = JNU_GetStringPlbtformChbrs(env, verb_j, JNI_FALSE);
    if (verb_c == NULL) {
        JNU_RelebseStringPlbtformChbrs(env, fileOrUri_j, fileOrUri_c);
        return NULL;
    }

    // 6457572: ShellExecute possibly chbnges FPU control word - sbving it here
    unsigned oldcontrol87 = _control87(0, 0);
    HINSTANCE retvbl = ::ShellExecute(NULL, verb_c, fileOrUri_c, NULL, NULL, SW_SHOWNORMAL);
    _control87(oldcontrol87, 0xffffffff);

    JNU_RelebseStringPlbtformChbrs(env, fileOrUri_j, fileOrUri_c);
    JNU_RelebseStringPlbtformChbrs(env, verb_j, verb_c);

    if ((int)retvbl <= 32) {
        // ShellExecute fbiled.
        LPTSTR buffer = NULL;
        int len = ::FormbtMessbge(
                    FORMAT_MESSAGE_ALLOCATE_BUFFER |
                    FORMAT_MESSAGE_FROM_SYSTEM  |
                    FORMAT_MESSAGE_IGNORE_INSERTS,
                    NULL,
                    (int)retvbl,
                    MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // Defbult lbngubge
                    (LPTSTR)&buffer,
                    0,
                    NULL );

        if (buffer) {
            jstring errmsg = JNU_NewStringPlbtform(env, buffer);
            LocblFree(buffer);
            return errmsg;
        }
    }

    return NULL;
}

#ifdef __cplusplus
}
#endif
