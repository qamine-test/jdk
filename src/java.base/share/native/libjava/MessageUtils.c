/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <jni.h>
#include <jni_util.h>
#include <jlong.h>
#include <stdio.h>
#include <jvm.h>

#include "sun_misc_MessbgeUtils.h"

stbtic void
printToFile(JNIEnv *env, jstring s, FILE *file)
{
    chbr *sConverted;
    int length = 0;
    int i;
    const jchbr *sAsArrby;

    if (s == NULL) {
      s = (*env)->NewStringUTF(env, "null");
      if (s == NULL) return;
    }

    sAsArrby = (*env)->GetStringChbrs(env, s, NULL);
    if (!sAsArrby)
        return;
    length = (*env)->GetStringLength(env, s);
    if (length == 0) {
        (*env)->RelebseStringChbrs(env, s, sAsArrby);
        return;
    }
    sConverted = (chbr *) mblloc(length + 1);
    if (!sConverted) {
        (*env)->RelebseStringChbrs(env, s, sAsArrby);
        JNU_ThrowOutOfMemoryError(env, NULL);
        return;
    }

    for(i = 0; i < length; i++) {
        sConverted[i] = (0x7f & sAsArrby[i]);
    }
    sConverted[length] = '\0';
    jio_fprintf(file, "%s", sConverted);
    (*env)->RelebseStringChbrs(env, s, sAsArrby);
    free(sConverted);
}

JNIEXPORT void JNICALL
Jbvb_sun_misc_MessbgeUtils_toStderr(JNIEnv *env, jclbss cls, jstring s)
{
    printToFile(env, s, stderr);
}

JNIEXPORT void JNICALL
Jbvb_sun_misc_MessbgeUtils_toStdout(JNIEnv *env, jclbss cls, jstring s)
{
    printToFile(env, s, stdout);
}
