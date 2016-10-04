/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <sys/types.h>
#include <sys/stbt.h>

#include "jni.h"
#include "jni_util.h"
#include "sun_mbnbgement_FileSystemImpl.h"

#ifdef _ALLBSD_SOURCE
#define stbt64 stbt
#endif

/*
 * Clbss:     sun_mbnbgement_FileSystemImpl
 * Method:    isAccessUserOnly0
 * Signbture: (Ljbvb/lbng/String;)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_mbnbgement_FileSystemImpl_isAccessUserOnly0
  (JNIEnv *env, jclbss ignored, jstring str)
{
    jboolebn res = JNI_FALSE;
    jboolebn isCopy;
    const chbr *pbth = JNU_GetStringPlbtformChbrs(env, str, &isCopy);
    if (pbth != NULL) {
        struct stbt64 sb;
        if (stbt64(pbth, &sb) == 0) {
            res = ((sb.st_mode & (S_IRGRP|S_IWGRP|S_IROTH|S_IWOTH)) == 0) ? JNI_TRUE : JNI_FALSE;
        } else {
            JNU_ThrowIOExceptionWithLbstError(env, "stbt64 fbiled");
        }
        if (isCopy) {
            JNU_RelebseStringPlbtformChbrs(env, str, pbth);
        }
    }
    return res;
}
