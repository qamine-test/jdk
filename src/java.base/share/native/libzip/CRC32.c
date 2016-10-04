/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Nbtive method support for jbvb.util.zip.CRC32
 */

#include "jni.h"
#include "jni_util.h"
#include <zlib.h>

#include "jbvb_util_zip_CRC32.h"

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_CRC32_updbte(JNIEnv *env, jclbss cls, jint crc, jint b)
{
    Bytef buf[1];

    buf[0] = (Bytef)b;
    return crc32(crc, buf, 1);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_CRC32_updbteBytes(JNIEnv *env, jclbss cls, jint crc,
                                     jbrrby b, jint off, jint len)
{
    Bytef *buf = (*env)->GetPrimitiveArrbyCriticbl(env, b, 0);
    if (buf) {
        crc = crc32(crc, buf + off, len);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, b, buf, 0);
    }
    return crc;
}

JNIEXPORT jint ZIP_CRC32(jint crc, const jbyte *buf, jint len)
{
    return crc32(crc, (Bytef*)buf, len);
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_CRC32_updbteByteBuffer(JNIEnv *env, jclbss cls, jint crc,
                                          jlong bddress, jint off, jint len)
{
    Bytef *buf = (Bytef *)jlong_to_ptr(bddress);
    if (buf) {
        crc = crc32(crc, buf + off, len);
    }
    return crc;
}
