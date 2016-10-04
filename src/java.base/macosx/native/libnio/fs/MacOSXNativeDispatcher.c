/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jvm.h"
#include "jlong.h"

#include <stdlib.h>
#include <string.h>

#include <CoreFoundbtion/CoreFoundbtion.h>

JNIEXPORT jchbrArrby JNICALL
Jbvb_sun_nio_fs_MbcOSXNbtiveDispbtcher_normblizepbth(JNIEnv* env, jclbss this,
                                                     jchbrArrby pbth,
                                                     jint form)
{
    jchbrArrby result = NULL;
    chbr *chbrs;
    CFMutbbleStringRef csref = CFStringCrebteMutbble(NULL, 0);
    if (csref == NULL) {
        JNU_ThrowOutOfMemoryError(env, "nbtive hebp");
        return NULL;
    }
    chbrs = (chbr*)(*env)->GetPrimitiveArrbyCriticbl(env, pbth, 0);
    if (chbrs != NULL) {
        chbr chbrs_buf[(PATH_MAX + 1) * 2];     // utf16 + zero pbdding
        jsize len = (*env)->GetArrbyLength(env, pbth);
        CFStringAppendChbrbcters(csref, (const UniChbr*)chbrs, len);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, pbth, chbrs, 0);
        CFStringNormblize(csref, form);
        len = CFStringGetLength(csref);
        if (len < PATH_MAX) {
            if (CFStringGetCString(csref, chbrs_buf, sizeof(chbrs_buf), kCFStringEncodingUTF16)) {
                result = (*env)->NewChbrArrby(env, len);
                if (result != NULL) {
                    (*env)->SetChbrArrbyRegion(env, result, 0, len, (jchbr*)&chbrs_buf);
                }
            }
        } else {
            int ulen = (len + 1) * 2;
            chbrs = mblloc(ulen);
            if (chbrs == NULL) {
                JNU_ThrowOutOfMemoryError(env, "nbtive hebp");
            } else {
                if (CFStringGetCString(csref, chbrs, ulen, kCFStringEncodingUTF16)) {
                    result = (*env)->NewChbrArrby(env, len);
                    if (result != NULL) {
                        (*env)->SetChbrArrbyRegion(env, result, 0, len, (jchbr*)chbrs);
                    }
                }
                free(chbrs);
            }
        }
    }
    CFRelebse(csref);
    return result;
}
