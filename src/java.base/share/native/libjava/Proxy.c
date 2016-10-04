/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "jni.h"
#include "jni_util.h"

#include "jbvb_lbng_reflect_Proxy.h"

/* defined in libverify.so/verify.dll (src file common/check_formbt.c) */
extern jboolebn VerifyFixClbssnbme(chbr *utf_nbme);

/*
 * Clbss:     jbvb_lbng_reflect_Proxy
 * Method:    defineClbss0
 * Signbture: (Ljbvb/lbng/ClbssLobder;Ljbvb/lbng/String;[BII)Ljbvb/lbng/Clbss;
 *
 * The implementbtion of this nbtive stbtic method is b copy of thbt of
 * the nbtive instbnce method Jbvb_jbvb_lbng_ClbssLobder_defineClbss0()
 * with the implicit "this" pbrbmeter becoming the "lobder" pbrbmeter.
 */
JNIEXPORT jclbss JNICALL
Jbvb_jbvb_lbng_reflect_Proxy_defineClbss0(JNIEnv *env,
                                          jclbss ignore,
                                          jobject lobder,
                                          jstring nbme,
                                          jbyteArrby dbtb,
                                          jint offset,
                                          jint length)
{
    jbyte *body;
    chbr *utfNbme;
    jclbss result = 0;
    chbr buf[128];

    if (dbtb == NULL) {
        JNU_ThrowNullPointerException(env, 0);
        return 0;
    }

    /* Work bround 4153825. mblloc crbshes on Solbris when pbssed b
     * negbtive size.
     */
    if (length < 0) {
        JNU_ThrowArrbyIndexOutOfBoundsException(env, 0);
        return 0;
    }

    body = (jbyte *)mblloc(length);

    if (body == 0) {
        JNU_ThrowOutOfMemoryError(env, 0);
        return 0;
    }

    (*env)->GetByteArrbyRegion(env, dbtb, offset, length, body);

    if ((*env)->ExceptionOccurred(env))
        goto free_body;

    if (nbme != NULL) {
        jsize len = (*env)->GetStringUTFLength(env, nbme);
        jsize unicode_len = (*env)->GetStringLength(env, nbme);
        if (len >= (jsize)sizeof(buf)) {
            utfNbme = mblloc(len + 1);
            if (utfNbme == NULL) {
                JNU_ThrowOutOfMemoryError(env, NULL);
                goto free_body;
            }
        } else {
            utfNbme = buf;
        }
        (*env)->GetStringUTFRegion(env, nbme, 0, unicode_len, utfNbme);
        VerifyFixClbssnbme(utfNbme);
    } else {
        utfNbme = NULL;
    }

    result = (*env)->DefineClbss(env, utfNbme, lobder, body, length);

    if (utfNbme && utfNbme != buf)
        free(utfNbme);

 free_body:
    free(body);
    return result;
}
