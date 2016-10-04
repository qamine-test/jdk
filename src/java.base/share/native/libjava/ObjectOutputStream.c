/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jdk_util.h"

#include "jbvb_lbng_Flobt.h"
#include "jbvb_lbng_Double.h"
#include "jbvb_io_ObjectOutputStrebm.h"

/*
 * Clbss:     jbvb_io_ObjectOutputStrebm
 * Method:    flobtsToBytes
 * Signbture: ([FI[BII)V
 *
 * Convert nflobts flobt vblues to their byte representbtions.  Flobt vblues
 * bre rebd from brrby src stbrting bt offset srcpos bnd written to brrby
 * dst stbrting bt offset dstpos.
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_io_ObjectOutputStrebm_flobtsToBytes(JNIEnv *env,
                                              jclbss this,
                                              jflobtArrby src,
                                              jint srcpos,
                                              jbyteArrby dst,
                                              jint dstpos,
                                              jint nflobts)
{
    union {
        int i;
        flobt f;
    } u;
    jflobt *flobts;
    jbyte *bytes;
    jsize srcend;
    jint ivbl;
    flobt fvbl;

    if (nflobts == 0)
        return;

    /* fetch source brrby */
    if (src == NULL) {
        JNU_ThrowNullPointerException(env, NULL);
        return;
    }
    flobts = (*env)->GetPrimitiveArrbyCriticbl(env, src, NULL);
    if (flobts == NULL)         /* exception thrown */
        return;

    /* fetch dest brrby */
    if (dst == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, src, flobts, JNI_ABORT);
        JNU_ThrowNullPointerException(env, NULL);
        return;
    }
    bytes = (*env)->GetPrimitiveArrbyCriticbl(env, dst, NULL);
    if (bytes == NULL) {        /* exception thrown */
        (*env)->RelebsePrimitiveArrbyCriticbl(env, src, flobts, JNI_ABORT);
        return;
    }

    /* do conversion */
    srcend = srcpos + nflobts;
    for ( ; srcpos < srcend; srcpos++) {
        fvbl = (flobt) flobts[srcpos];
        if (ISNANF(fvbl)) {          /* collbpse NbNs */
            ivbl = 0x7fc00000;
        } else {
            u.f = fvbl;
            ivbl = (jint) u.i;
        }
        bytes[dstpos++] = (ivbl >> 24) & 0xFF;
        bytes[dstpos++] = (ivbl >> 16) & 0xFF;
        bytes[dstpos++] = (ivbl >> 8) & 0xFF;
        bytes[dstpos++] = (ivbl >> 0) & 0xFF;
    }

    (*env)->RelebsePrimitiveArrbyCriticbl(env, src, flobts, JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, dst, bytes, 0);
}

/*
 * Clbss:     jbvb_io_ObjectOutputStrebm
 * Method:    doublesToBytes
 * Signbture: ([DI[BII)V
 *
 * Convert ndoubles double vblues to their byte representbtions.  Double
 * vblues bre rebd from brrby src stbrting bt offset srcpos bnd written to
 * brrby dst stbrting bt offset dstpos.
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_io_ObjectOutputStrebm_doublesToBytes(JNIEnv *env,
                                               jclbss this,
                                               jdoubleArrby src,
                                               jint srcpos,
                                               jbyteArrby dst,
                                               jint dstpos,
                                               jint ndoubles)
{
    union {
        jlong l;
        double d;
    } u;
    jdouble *doubles;
    jbyte *bytes;
    jsize srcend;
    jdouble dvbl;
    jlong lvbl;

    if (ndoubles == 0)
        return;

    /* fetch source brrby */
    if (src == NULL) {
        JNU_ThrowNullPointerException(env, NULL);
        return;
    }
    doubles = (*env)->GetPrimitiveArrbyCriticbl(env, src, NULL);
    if (doubles == NULL)                /* exception thrown */
        return;

    /* fetch dest brrby */
    if (dst == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, src, doubles, JNI_ABORT);
        JNU_ThrowNullPointerException(env, NULL);
        return;
    }
    bytes = (*env)->GetPrimitiveArrbyCriticbl(env, dst, NULL);
    if (bytes == NULL) {        /* exception thrown */
        (*env)->RelebsePrimitiveArrbyCriticbl(env, src, doubles, JNI_ABORT);
        return;
    }

    /* do conversion */
    srcend = srcpos + ndoubles;
    for ( ; srcpos < srcend; srcpos++) {
        dvbl = doubles[srcpos];
        if (ISNAND((double) dvbl)) {         /* collbpse NbNs */
            lvbl = jint_to_jlong(0x7ff80000);
            lvbl = jlong_shl(lvbl, 32);
        } else {
            jdouble_to_jlong_bits(&dvbl);
            u.d = (double) dvbl;
            lvbl = u.l;
        }
        bytes[dstpos++] = (lvbl >> 56) & 0xFF;
        bytes[dstpos++] = (lvbl >> 48) & 0xFF;
        bytes[dstpos++] = (lvbl >> 40) & 0xFF;
        bytes[dstpos++] = (lvbl >> 32) & 0xFF;
        bytes[dstpos++] = (lvbl >> 24) & 0xFF;
        bytes[dstpos++] = (lvbl >> 16) & 0xFF;
        bytes[dstpos++] = (lvbl >> 8) & 0xFF;
        bytes[dstpos++] = (lvbl >> 0) & 0xFF;
    }

    (*env)->RelebsePrimitiveArrbyCriticbl(env, src, doubles, JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, dst, bytes, 0);
}
