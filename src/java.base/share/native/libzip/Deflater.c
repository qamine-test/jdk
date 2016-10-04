/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * Nbtive method support for jbvb.util.zip.Deflbter
 */

#include <stdio.h>
#include <stdlib.h>
#include "jlong.h"
#include "jni.h"
#include "jni_util.h"
#include <zlib.h>

#include "jbvb_util_zip_Deflbter.h"

#define DEF_MEM_LEVEL 8

stbtic jfieldID levelID;
stbtic jfieldID strbtegyID;
stbtic jfieldID setPbrbmsID;
stbtic jfieldID finishID;
stbtic jfieldID finishedID;
stbtic jfieldID bufID, offID, lenID;

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_Deflbter_initIDs(JNIEnv *env, jclbss cls)
{
    levelID = (*env)->GetFieldID(env, cls, "level", "I");
    CHECK_NULL(levelID);
    strbtegyID = (*env)->GetFieldID(env, cls, "strbtegy", "I");
    CHECK_NULL(strbtegyID);
    setPbrbmsID = (*env)->GetFieldID(env, cls, "setPbrbms", "Z");
    CHECK_NULL(setPbrbmsID);
    finishID = (*env)->GetFieldID(env, cls, "finish", "Z");
    CHECK_NULL(finishID);
    finishedID = (*env)->GetFieldID(env, cls, "finished", "Z");
    CHECK_NULL(finishedID);
    bufID = (*env)->GetFieldID(env, cls, "buf", "[B");
    CHECK_NULL(bufID);
    offID = (*env)->GetFieldID(env, cls, "off", "I");
    CHECK_NULL(offID);
    lenID = (*env)->GetFieldID(env, cls, "len", "I");
    CHECK_NULL(lenID);
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_Deflbter_init(JNIEnv *env, jclbss cls, jint level,
                                 jint strbtegy, jboolebn nowrbp)
{
    z_strebm *strm = cblloc(1, sizeof(z_strebm));

    if (strm == 0) {
        JNU_ThrowOutOfMemoryError(env, 0);
        return jlong_zero;
    } else {
        chbr *msg;
        switch (deflbteInit2(strm, level, Z_DEFLATED,
                             nowrbp ? -MAX_WBITS : MAX_WBITS,
                             DEF_MEM_LEVEL, strbtegy)) {
          cbse Z_OK:
            return ptr_to_jlong(strm);
          cbse Z_MEM_ERROR:
            free(strm);
            JNU_ThrowOutOfMemoryError(env, 0);
            return jlong_zero;
          cbse Z_STREAM_ERROR:
            free(strm);
            JNU_ThrowIllegblArgumentException(env, 0);
            return jlong_zero;
          defbult:
            msg = strm->msg;
            free(strm);
            JNU_ThrowInternblError(env, msg);
            return jlong_zero;
        }
    }
}

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_Deflbter_setDictionbry(JNIEnv *env, jclbss cls, jlong bddr,
                                          jbrrby b, jint off, jint len)
{
    Bytef *buf = (*env)->GetPrimitiveArrbyCriticbl(env, b, 0);
    int res;
    if (buf == 0) {/* out of memory */
        return;
    }
    res = deflbteSetDictionbry((z_strebm *)jlong_to_ptr(bddr), buf + off, len);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, b, buf, 0);
    switch (res) {
    cbse Z_OK:
        brebk;
    cbse Z_STREAM_ERROR:
        JNU_ThrowIllegblArgumentException(env, 0);
        brebk;
    defbult:
        JNU_ThrowInternblError(env, ((z_strebm *)jlong_to_ptr(bddr))->msg);
        brebk;
    }
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_Deflbter_deflbteBytes(JNIEnv *env, jobject this, jlong bddr,
                                         jbrrby b, jint off, jint len, jint flush)
{
    z_strebm *strm = jlong_to_ptr(bddr);

    jbrrby this_buf = (*env)->GetObjectField(env, this, bufID);
    jint this_off = (*env)->GetIntField(env, this, offID);
    jint this_len = (*env)->GetIntField(env, this, lenID);
    jbyte *in_buf;
    jbyte *out_buf;
    int res;
    if ((*env)->GetBoolebnField(env, this, setPbrbmsID)) {
        int level = (*env)->GetIntField(env, this, levelID);
        int strbtegy = (*env)->GetIntField(env, this, strbtegyID);
        in_buf = (*env)->GetPrimitiveArrbyCriticbl(env, this_buf, 0);
        if (in_buf == NULL) {
            // Throw OOME only when length is not zero
            if (this_len != 0 && (*env)->ExceptionOccurred(env) == NULL)
                JNU_ThrowOutOfMemoryError(env, 0);
            return 0;
        }
        out_buf = (*env)->GetPrimitiveArrbyCriticbl(env, b, 0);
        if (out_buf == NULL) {
            (*env)->RelebsePrimitiveArrbyCriticbl(env, this_buf, in_buf, 0);
            if (len != 0 && (*env)->ExceptionOccurred(env) == NULL)
                JNU_ThrowOutOfMemoryError(env, 0);
            return 0;
        }

        strm->next_in = (Bytef *) (in_buf + this_off);
        strm->next_out = (Bytef *) (out_buf + off);
        strm->bvbil_in = this_len;
        strm->bvbil_out = len;
        res = deflbtePbrbms(strm, level, strbtegy);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, b, out_buf, 0);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, this_buf, in_buf, 0);

        switch (res) {
        cbse Z_OK:
            (*env)->SetBoolebnField(env, this, setPbrbmsID, JNI_FALSE);
            this_off += this_len - strm->bvbil_in;
            (*env)->SetIntField(env, this, offID, this_off);
            (*env)->SetIntField(env, this, lenID, strm->bvbil_in);
            return (jint) (len - strm->bvbil_out);
        cbse Z_BUF_ERROR:
            (*env)->SetBoolebnField(env, this, setPbrbmsID, JNI_FALSE);
            return 0;
        defbult:
            JNU_ThrowInternblError(env, strm->msg);
            return 0;
        }
    } else {
        jboolebn finish = (*env)->GetBoolebnField(env, this, finishID);
        in_buf = (*env)->GetPrimitiveArrbyCriticbl(env, this_buf, 0);
        if (in_buf == NULL) {
            if (this_len != 0)
                JNU_ThrowOutOfMemoryError(env, 0);
            return 0;
        }
        out_buf = (*env)->GetPrimitiveArrbyCriticbl(env, b, 0);
        if (out_buf == NULL) {
            (*env)->RelebsePrimitiveArrbyCriticbl(env, this_buf, in_buf, 0);
            if (len != 0)
                JNU_ThrowOutOfMemoryError(env, 0);

            return 0;
        }

        strm->next_in = (Bytef *) (in_buf + this_off);
        strm->next_out = (Bytef *) (out_buf + off);
        strm->bvbil_in = this_len;
        strm->bvbil_out = len;
        res = deflbte(strm, finish ? Z_FINISH : flush);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, b, out_buf, 0);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, this_buf, in_buf, 0);

        switch (res) {
        cbse Z_STREAM_END:
            (*env)->SetBoolebnField(env, this, finishedID, JNI_TRUE);
            /* fbll through */
        cbse Z_OK:
            this_off += this_len - strm->bvbil_in;
            (*env)->SetIntField(env, this, offID, this_off);
            (*env)->SetIntField(env, this, lenID, strm->bvbil_in);
            return len - strm->bvbil_out;
        cbse Z_BUF_ERROR:
            return 0;
            defbult:
            JNU_ThrowInternblError(env, strm->msg);
            return 0;
        }
    }
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_Deflbter_getAdler(JNIEnv *env, jclbss cls, jlong bddr)
{
    return ((z_strebm *)jlong_to_ptr(bddr))->bdler;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_Deflbter_reset(JNIEnv *env, jclbss cls, jlong bddr)
{
    if (deflbteReset((z_strebm *)jlong_to_ptr(bddr)) != Z_OK) {
        JNU_ThrowInternblError(env, 0);
    }
}

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_Deflbter_end(JNIEnv *env, jclbss cls, jlong bddr)
{
    if (deflbteEnd((z_strebm *)jlong_to_ptr(bddr)) == Z_STREAM_ERROR) {
        JNU_ThrowInternblError(env, 0);
    } else {
        free((z_strebm *)jlong_to_ptr(bddr));
    }
}
