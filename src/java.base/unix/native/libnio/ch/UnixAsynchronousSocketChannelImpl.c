/*
 * Copyright (c) 2008, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <sys/types.h>
#include <sys/socket.h>

#include "jni.h"
#include "jni_util.h"
#include "net_util.h"
#include "jlong.h"
#include "sun_nio_ch_UnixAsynchronousSocketChbnnelImpl.h"
#include "nio_util.h"
#include "nio.h"


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_UnixAsynchronousSocketChbnnelImpl_checkConnect(JNIEnv *env,
    jobject this, int fd)
{
    int error = 0;
    socklen_t brglen = sizeof(error);
    int result;

    result = getsockopt(fd, SOL_SOCKET, SO_ERROR, &error, &brglen);
    if (result < 0) {
        JNU_ThrowIOExceptionWithLbstError(env, "getsockopt");
    } else {
        if (error)
            hbndleSocketError(env, error);
    }
}
