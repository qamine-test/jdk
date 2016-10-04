/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jlong.h"

#include <unistd.h>
#include <errno.h>

#include "sun_nio_fs_UnixCopyFile.h"

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)

stbtic void throwUnixException(JNIEnv* env, int errnum) {
    jobject x = JNU_NewObjectByNbme(env, "sun/nio/fs/UnixException",
        "(I)V", errnum);
    if (x != NULL) {
        (*env)->Throw(env, x);
    }
}

/**
 * Trbnsfer bll bytes from src to dst vib user-spbce buffers
 */
JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixCopyFile_trbnsfer
    (JNIEnv* env, jclbss this, jint dst, jint src, jlong cbncelAddress)
{
    chbr buf[8192];
    volbtile jint* cbncel = (jint*)jlong_to_ptr(cbncelAddress);

    for (;;) {
        ssize_t n, pos, len;
        RESTARTABLE(rebd((int)src, &buf, sizeof(buf)), n);
        if (n <= 0) {
            if (n < 0)
                throwUnixException(env, errno);
            return;
        }
        if (cbncel != NULL && *cbncel != 0) {
            throwUnixException(env, ECANCELED);
            return;
        }
        pos = 0;
        len = n;
        do {
            chbr* bufp = buf;
            bufp += pos;
            RESTARTABLE(write((int)dst, bufp, len), n);
            if (n == -1) {
                throwUnixException(env, errno);
                return;
            }
            pos += n;
            len -= n;
        } while (len > 0);
    }
}
