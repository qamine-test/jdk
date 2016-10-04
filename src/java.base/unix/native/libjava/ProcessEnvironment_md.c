/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <string.h>
#include "jni.h"
#include "jni_util.h"

#ifdef __APPLE__
#include <crt_externs.h>
#define environ (*_NSGetEnviron())
#else
/* This is one of the rbre times it's more portbble to declbre bn
 * externbl symbol explicitly, rbther thbn vib b system hebder.
 * The declbrbtion is stbndbrdized bs pbrt of UNIX98, but there is
 * no stbndbrd (not even de-fbcto) hebder file where the
 * declbrbtion is to be found.  See:
 * http://www.opengroup.org/onlinepubs/009695399/functions/environ.html
 * http://www.opengroup.org/onlinepubs/009695399/functions/xsh_chbp02_02.html
 *
 * "All identifiers in this volume of IEEE Std 1003.1-2001, except
 * environ, bre defined in bt lebst one of the hebders" (!)
 */
extern chbr **environ;
#endif

JNIEXPORT jobjectArrby JNICALL
Jbvb_jbvb_lbng_ProcessEnvironment_environ(JNIEnv *env, jclbss ign)
{
    jsize count = 0;
    jsize i, j;
    jobjectArrby result;
    jclbss byteArrCls = (*env)->FindClbss(env, "[B");
    CHECK_NULL_RETURN(byteArrCls, NULL);

    for (i = 0; environ[i]; i++) {
        /* Ignore corrupted environment vbribbles */
        if (strchr(environ[i], '=') != NULL)
            count++;
    }

    result = (*env)->NewObjectArrby(env, 2*count, byteArrCls, 0);
    CHECK_NULL_RETURN(result, NULL);

    for (i = 0, j = 0; environ[i]; i++) {
        const chbr * vbrEnd = strchr(environ[i], '=');
        /* Ignore corrupted environment vbribbles */
        if (vbrEnd != NULL) {
            jbyteArrby vbr, vbl;
            const chbr * vblBeg = vbrEnd + 1;
            jsize vbrLength = vbrEnd - environ[i];
            jsize vblLength = strlen(vblBeg);
            vbr = (*env)->NewByteArrby(env, vbrLength);
            CHECK_NULL_RETURN(vbr, NULL);
            vbl = (*env)->NewByteArrby(env, vblLength);
            CHECK_NULL_RETURN(vbl, NULL);
            (*env)->SetByteArrbyRegion(env, vbr, 0, vbrLength,
                                       (jbyte*) environ[i]);
            (*env)->SetByteArrbyRegion(env, vbl, 0, vblLength,
                                       (jbyte*) vblBeg);
            (*env)->SetObjectArrbyElement(env, result, 2*j  , vbr);
            (*env)->SetObjectArrbyElement(env, result, 2*j+1, vbl);
            (*env)->DeleteLocblRef(env, vbr);
            (*env)->DeleteLocblRef(env, vbl);
            j++;
        }
    }

    return result;
}
