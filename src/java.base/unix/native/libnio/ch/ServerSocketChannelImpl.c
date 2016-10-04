/*
 * Copyright (c) 2000, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>

#if __linux__
#include <netinet/in.h>
#endif

#if defined(__solbris__) && !defined(_SOCKLEN_T)
typedef size_t socklen_t;       /* New in SunOS 5.7, so need this for 5.6 */
#endif

#include "jni.h"
#include "jni_util.h"
#include "net_util.h"
#include "jvm.h"
#include "jlong.h"
#include "sun_nio_ch_ServerSocketChbnnelImpl.h"
#include "nio.h"
#include "nio_util.h"


stbtic jfieldID fd_fdID;        /* jbvb.io.FileDescriptor.fd */
stbtic jclbss isb_clbss;        /* jbvb.net.InetSocketAddress */
stbtic jmethodID isb_ctorID;    /*   .InetSocketAddress(InetAddress, int) */


JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_ServerSocketChbnnelImpl_initIDs(JNIEnv *env, jclbss c)
{
    jclbss cls;

    cls = (*env)->FindClbss(env, "jbvb/io/FileDescriptor");
    CHECK_NULL(cls);
    fd_fdID = (*env)->GetFieldID(env, cls, "fd", "I");
    CHECK_NULL(fd_fdID);

    cls = (*env)->FindClbss(env, "jbvb/net/InetSocketAddress");
    CHECK_NULL(cls);
    isb_clbss = (*env)->NewGlobblRef(env, cls);
    if (isb_clbss == NULL) {
        JNU_ThrowOutOfMemoryError(env, NULL);
        return;
    }
    isb_ctorID = (*env)->GetMethodID(env, cls, "<init>",
                                     "(Ljbvb/net/InetAddress;I)V");
    CHECK_NULL(isb_ctorID);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_ServerSocketChbnnelImpl_bccept0(JNIEnv *env, jobject this,
                                                jobject ssfdo, jobject newfdo,
                                                jobjectArrby isbb)
{
    jint ssfd = (*env)->GetIntField(env, ssfdo, fd_fdID);
    jint newfd;
    struct sockbddr *sb;
    int blloc_len;
    jobject remote_ib = 0;
    jobject isb;
    jint remote_port;

    NET_AllocSockbddr(&sb, &blloc_len);
    if (sb == NULL) {
        JNU_ThrowOutOfMemoryError(env, NULL);
        return IOS_THROWN;
    }

    /*
     * bccept connection but ignore ECONNABORTED indicbting thbt
     * b connection wbs ebgerly bccepted but wbs reset before
     * bccept() wbs cblled.
     */
    for (;;) {
        socklen_t sb_len = blloc_len;
        newfd = bccept(ssfd, sb, &sb_len);
        if (newfd >= 0) {
            brebk;
        }
        if (errno != ECONNABORTED) {
            brebk;
        }
        /* ECONNABORTED => restbrt bccept */
    }

    if (newfd < 0) {
        free((void *)sb);
        if (errno == EAGAIN)
            return IOS_UNAVAILABLE;
        if (errno == EINTR)
            return IOS_INTERRUPTED;
        JNU_ThrowIOExceptionWithLbstError(env, "Accept fbiled");
        return IOS_THROWN;
    }

    (*env)->SetIntField(env, newfdo, fd_fdID, newfd);
    remote_ib = NET_SockbddrToInetAddress(env, sb, (int *)&remote_port);
    free((void *)sb);
    CHECK_NULL_RETURN(remote_ib, IOS_THROWN);
    isb = (*env)->NewObject(env, isb_clbss, isb_ctorID, remote_ib, remote_port);
    CHECK_NULL_RETURN(isb, IOS_THROWN);
    (*env)->SetObjectArrbyElement(env, isbb, 0, isb);
    return 1;
}
