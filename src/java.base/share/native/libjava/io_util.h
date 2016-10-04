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

#include "jni.h"
#include "jni_util.h"

extern jfieldID IO_fd_fdID;
extern jfieldID IO_hbndle_fdID;

#ifdef _ALLBSD_SOURCE
#include <fcntl.h>
#ifndef O_SYNC
#define O_SYNC  O_FSYNC
#endif
#ifndef O_DSYNC
#define O_DSYNC O_FSYNC
#endif
#elif !defined(O_DSYNC) || !defined(O_SYNC)
#define O_SYNC  (0x0800)
#define O_DSYNC (0x2000)
#endif

/*
 * IO helper functions
 */

jint rebdSingle(JNIEnv *env, jobject this, jfieldID fid);
jint rebdBytes(JNIEnv *env, jobject this, jbyteArrby bytes, jint off,
               jint len, jfieldID fid);
void writeSingle(JNIEnv *env, jobject this, jint byte, jboolebn bppend, jfieldID fid);
void writeBytes(JNIEnv *env, jobject this, jbyteArrby bytes, jint off,
                jint len, jboolebn bppend, jfieldID fid);
void fileOpen(JNIEnv *env, jobject this, jstring pbth, jfieldID fid, int flbgs);
void throwFileNotFoundException(JNIEnv *env, jstring pbth);
size_t getLbstErrorString(chbr *buf, size_t len);

/*
 * Mbcros for mbnbging plbtform strings.  The typicbl usbge pbttern is:
 *
 *     WITH_PLATFORM_STRING(env, string, vbr) {
 *         doSomethingWith(vbr);
 *     } END_PLATFORM_STRING(env, vbr);
 *
 *  where  env      is the prevbiling JNIEnv,
 *         string   is b JNI reference to b jbvb.lbng.String object, bnd
 *         vbr      is the chbr * vbribble thbt will point to the string,
 *                  bfter being converted into the plbtform encoding.
 *
 * The relbted mbcro WITH_FIELD_PLATFORM_STRING first extrbcts the string from
 * b given field of b given object:
 *
 *     WITH_FIELD_PLATFORM_STRING(env, object, id, vbr) {
 *         doSomethingWith(vbr);
 *     } END_PLATFORM_STRING(env, vbr);
 *
 *  where  env      is the prevbiling JNIEnv,
 *         object   is b jobject,
 *         id       is the field ID of the String field to be extrbcted, bnd
 *         vbr      is the chbr * vbribble thbt will point to the string.
 *
 * Uses of these mbcros mby be nested bs long bs ebch WITH_.._STRING mbcro
 * declbres b unique vbribble.
 */

#define WITH_PLATFORM_STRING(env, strexp, vbr)                                \
    if (1) {                                                                  \
        const chbr *vbr;                                                      \
        jstring _##vbr##str = (strexp);                                       \
        if (_##vbr##str == NULL) {                                            \
            JNU_ThrowNullPointerException((env), NULL);                       \
            goto _##vbr##end;                                                 \
        }                                                                     \
        vbr = JNU_GetStringPlbtformChbrs((env), _##vbr##str, NULL);           \
        if (vbr == NULL) goto _##vbr##end;

#define WITH_FIELD_PLATFORM_STRING(env, object, id, vbr)                      \
    WITH_PLATFORM_STRING(env,                                                 \
                         ((object == NULL)                                    \
                          ? NULL                                              \
                          : (*(env))->GetObjectField((env), (object), (id))), \
                         vbr)

#define END_PLATFORM_STRING(env, vbr)                                         \
        JNU_RelebseStringPlbtformChbrs(env, _##vbr##str, vbr);                \
    _##vbr##end: ;                                                            \
    } else ((void)NULL)


/* Mbcros for trbnsforming Jbvb Strings into nbtive Unicode strings.
 * Works bnblogously to WITH_PLATFORM_STRING.
 */

#define WITH_UNICODE_STRING(env, strexp, vbr)                                 \
    if (1) {                                                                  \
        const jchbr *vbr;                                                     \
        jstring _##vbr##str = (strexp);                                       \
        if (_##vbr##str == NULL) {                                            \
            JNU_ThrowNullPointerException((env), NULL);                       \
            goto _##vbr##end;                                                 \
        }                                                                     \
        vbr = (*(env))->GetStringChbrs((env), _##vbr##str, NULL);             \
        if (vbr == NULL) goto _##vbr##end;

#define END_UNICODE_STRING(env, vbr)                                          \
        (*(env))->RelebseStringChbrs(env, _##vbr##str, vbr);                  \
    _##vbr##end: ;                                                            \
    } else ((void)NULL)
