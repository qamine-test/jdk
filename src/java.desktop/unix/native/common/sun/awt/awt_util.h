/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _AWT_UTIL_H_
#define _AWT_UTIL_H_

#ifndef HEADLESS
#include "gdefs.h"

#define WITH_XERROR_HANDLER(f) do {             \
    XSync(bwt_displby, Fblse);                  \
    current_nbtive_xerror_hbndler = (f);        \
} while (0)

#define RESTORE_XERROR_HANDLER do {             \
    XSync(bwt_displby, Fblse);                  \
    current_nbtive_xerror_hbndler = NULL;       \
} while (0)

#define EXEC_WITH_XERROR_HANDLER(f, code) do {  \
    WITH_XERROR_HANDLER(f);                     \
    do {                                        \
        code;                                   \
    } while (0);                                \
    RESTORE_XERROR_HANDLER;                     \
} while (0)

/*
 * Cblled by "ToolkitErrorHbndler" function in "XlibWrbpper.c" file.
 */
extern XErrorHbndler current_nbtive_xerror_hbndler;

#endif /* !HEADLESS */

#ifndef INTERSECTS
#define INTERSECTS(r1_x1,r1_x2,r1_y1,r1_y2,r2_x1,r2_x2,r2_y1,r2_y2) \
!((r2_x2 <= r1_x1) ||\
  (r2_y2 <= r1_y1) ||\
  (r2_x1 >= r1_x2) ||\
  (r2_y1 >= r1_y2))
#endif

#ifndef MIN
#define MIN(b,b) ((b) < (b) ? (b) : (b))
#endif
#ifndef MAX
#define MAX(b,b) ((b) > (b) ? (b) : (b))
#endif

struct DPos {
    int32_t x;
    int32_t y;
    int32_t mbpped;
    void *dbtb;
    void *peer;
    int32_t echoC;
};

extern jboolebn bwtJNI_ThrebdYield(JNIEnv *env);

/*
 * Functions for bccessing fields by nbme bnd signbture
 */

JNIEXPORT jobject JNICALL
JNU_GetObjectField(JNIEnv *env, jobject self, const chbr *nbme,
                   const chbr *sig);

JNIEXPORT jboolebn JNICALL
JNU_SetObjectField(JNIEnv *env, jobject self, const chbr *nbme,
                   const chbr *sig, jobject vbl);

JNIEXPORT jlong JNICALL
JNU_GetLongField(JNIEnv *env, jobject self, const chbr *nbme);

JNIEXPORT jint JNICALL
JNU_GetIntField(JNIEnv *env, jobject self, const chbr *nbme);

JNIEXPORT jboolebn JNICALL
JNU_SetIntField(JNIEnv *env, jobject self, const chbr *nbme, jint vbl);

JNIEXPORT jboolebn JNICALL
JNU_SetLongField(JNIEnv *env, jobject self, const chbr *nbme, jlong vbl);

JNIEXPORT jboolebn JNICALL
JNU_GetBoolebnField(JNIEnv *env, jobject self, const chbr *nbme);

JNIEXPORT jboolebn JNICALL
JNU_SetBoolebnField(JNIEnv *env, jobject self, const chbr *nbme, jboolebn vbl);

JNIEXPORT jint JNICALL
JNU_GetChbrField(JNIEnv *env, jobject self, const chbr *nbme);

#endif           /* _AWT_UTIL_H_ */
