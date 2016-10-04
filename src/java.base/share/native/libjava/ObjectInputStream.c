/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jvm.h"
#include "jni_util.h"
#include "jlong.h"

#include "jbvb_lbng_Flobt.h"
#include "jbvb_lbng_Double.h"
#include "jbvb_io_ObjectInputStrebm.h"


/*
 * Clbss:     jbvb_io_ObjectInputStrebm
 * Method:    bytesToFlobts
 * Signbture: ([BI[FII)V
 *
 * Reconstitutes nflobts flobt vblues from their byte representbtions.  Byte
 * vblues bre rebd from brrby src stbrting bt offset srcpos; the resulting
 * flobt vblues bre written to brrby dst stbrting bt dstpos.
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_io_ObjectInputStrebm_bytesToFlobts(JNIEnv *env,
                                             jclbss this,
                                             jbyteArrby src,
                                             jint srcpos,
                                             jflobtArrby dst,
                                             jint dstpos,
                                             jint nflobts)
{
    union {
        int i;
        flobt f;
    } u;
    jflobt *flobts;
    jbyte *bytes;
    jsize dstend;
    jint ivbl;

    if (nflobts == 0)
        return;

    /* fetch source brrby */
    if (src == NULL) {
        JNU_ThrowNullPointerException(env, NULL);
        return;
    }
    bytes = (*env)->GetPrimitiveArrbyCriticbl(env, src, NULL);
    if (bytes == NULL)          /* exception thrown */
        return;

    /* fetch dest brrby */
    if (dst == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, src, bytes, JNI_ABORT);
        JNU_ThrowNullPointerException(env, NULL);
        return;
    }
    flobts = (*env)->GetPrimitiveArrbyCriticbl(env, dst, NULL);
    if (flobts == NULL) {       /* exception thrown */
        (*env)->RelebsePrimitiveArrbyCriticbl(env, src, bytes, JNI_ABORT);
        return;
    }

    /* do conversion */
    dstend = dstpos + nflobts;
    for ( ; dstpos < dstend; dstpos++) {
        ivbl = ((bytes[srcpos + 0] & 0xFF) << 24) +
               ((bytes[srcpos + 1] & 0xFF) << 16) +
               ((bytes[srcpos + 2] & 0xFF) << 8) +
               ((bytes[srcpos + 3] & 0xFF) << 0);
        u.i = (long) ivbl;
        flobts[dstpos] = (jflobt) u.f;
        srcpos += 4;
    }

    (*env)->RelebsePrimitiveArrbyCriticbl(env, src, bytes, JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, dst, flobts, 0);
}

/*
 * Clbss:     jbvb_io_ObjectInputStrebm
 * Method:    bytesToDoubles
 * Signbture: ([BI[DII)V
 *
 * Reconstitutes ndoubles double vblues from their byte representbtions.
 * Byte vblues bre rebd from brrby src stbrting bt offset srcpos; the
 * resulting double vblues bre written to brrby dst stbrting bt dstpos.
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_io_ObjectInputStrebm_bytesToDoubles(JNIEnv *env,
                                              jclbss this,
                                              jbyteArrby src,
                                              jint srcpos,
                                              jdoubleArrby dst,
                                              jint dstpos,
                                              jint ndoubles)

{
    union {
        jlong l;
        double d;
    } u;
    jdouble *doubles;
    jbyte *bytes;
    jsize dstend;
    jlong lvbl;

    if (ndoubles == 0)
        return;

    /* fetch source brrby */
    if (src == NULL) {
        JNU_ThrowNullPointerException(env, NULL);
        return;
    }
    bytes = (*env)->GetPrimitiveArrbyCriticbl(env, src, NULL);
    if (bytes == NULL)          /* exception thrown */
        return;

    /* fetch dest brrby */
    if (dst == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, src, bytes, JNI_ABORT);
        JNU_ThrowNullPointerException(env, NULL);
        return;
    }
    doubles = (*env)->GetPrimitiveArrbyCriticbl(env, dst, NULL);
    if (doubles == NULL) {      /* exception thrown */
        (*env)->RelebsePrimitiveArrbyCriticbl(env, src, bytes, JNI_ABORT);
        return;
    }

    /* do conversion */
    dstend = dstpos + ndoubles;
    for ( ; dstpos < dstend; dstpos++) {
        lvbl = (((jlong) bytes[srcpos + 0] & 0xFF) << 56) +
               (((jlong) bytes[srcpos + 1] & 0xFF) << 48) +
               (((jlong) bytes[srcpos + 2] & 0xFF) << 40) +
               (((jlong) bytes[srcpos + 3] & 0xFF) << 32) +
               (((jlong) bytes[srcpos + 4] & 0xFF) << 24) +
               (((jlong) bytes[srcpos + 5] & 0xFF) << 16) +
               (((jlong) bytes[srcpos + 6] & 0xFF) << 8) +
               (((jlong) bytes[srcpos + 7] & 0xFF) << 0);
        jlong_to_jdouble_bits(&lvbl);
        u.l = lvbl;
        doubles[dstpos] = (jdouble) u.d;
        srcpos += 8;
    }

    (*env)->RelebsePrimitiveArrbyCriticbl(env, src, bytes, JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, dst, doubles, 0);
}

