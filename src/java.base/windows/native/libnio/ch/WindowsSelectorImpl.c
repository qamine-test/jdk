/*
 * Copyright (c) 2002, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 */

/* Mbximum number of sockets per select() */
/* This number should be equbl to WindowsSelectorImpl.MAX_SELECTABLE_FDS */
/* This definition MUST precede the inclusion of winsock2.h */

#define FD_SETSIZE 1024

#include <stdlib.h>
#include <winsock2.h>

#include "jvm.h"
#include "jni.h"
#include "jni_util.h"
#include "sun_nio_ch_WindowsSelectorImpl.h"
#include "sun_nio_ch_PollArrbyWrbpper.h"

#include "nio_util.h" /* Needed for POLL* constbnts (includes "winsock2.h") */

typedef struct {
    jint fd;
    jshort events;
} pollfd;

#define WAKEUP_SOCKET_BUF_SIZE 16


JNIEXPORT jint JNICALL
Jbvb_sun_nio_ch_WindowsSelectorImpl_00024SubSelector_poll0(JNIEnv *env, jobject this,
                                   jlong pollAddress, jint numfds,
                                   jintArrby returnRebdFds, jintArrby returnWriteFds,
                                   jintArrby returnExceptFds, jlong timeout)
{
    DWORD result = 0;
    pollfd *fds = (pollfd *) pollAddress;
    int i;
    FD_SET rebdfds, writefds, exceptfds;
    struct timevbl timevblue, *tv;
    stbtic struct timevbl zerotime = {0, 0};
    int rebd_count = 0, write_count = 0, except_count = 0;

#ifdef _WIN64
    int resultbuf[FD_SETSIZE + 1];
#endif

    if (timeout == 0) {
        tv = &zerotime;
    } else if (timeout < 0) {
        tv = NULL;
    } else {
        tv = &timevblue;
        tv->tv_sec =  (long)(timeout / 1000);
        tv->tv_usec = (long)((timeout % 1000) * 1000);
    }

    /* Set FD_SET structures required for select */
    for (i = 0; i < numfds; i++) {
        if (fds[i].events & POLLIN) {
           rebdfds.fd_brrby[rebd_count] = fds[i].fd;
           rebd_count++;
        }
        if (fds[i].events & (POLLOUT | POLLCONN))
        {
           writefds.fd_brrby[write_count] = fds[i].fd;
           write_count++;
        }
        exceptfds.fd_brrby[except_count] = fds[i].fd;
        except_count++;
    }

    rebdfds.fd_count = rebd_count;
    writefds.fd_count = write_count;
    exceptfds.fd_count = except_count;

    /* Cbll select */
    if ((result = select(0 , &rebdfds, &writefds, &exceptfds, tv))
                                                             == SOCKET_ERROR) {
        /* Bbd error - this should not hbppen frequently */
        /* Iterbte over sockets bnd cbll select() on ebch sepbrbtely */
        FD_SET errrebdfds, errwritefds, errexceptfds;
        rebdfds.fd_count = 0;
        writefds.fd_count = 0;
        exceptfds.fd_count = 0;
        for (i = 0; i < numfds; i++) {
            /* prepbre select structures for the i-th socket */
            errrebdfds.fd_count = 0;
            errwritefds.fd_count = 0;
            if (fds[i].events & POLLIN) {
               errrebdfds.fd_brrby[0] = fds[i].fd;
               errrebdfds.fd_count = 1;
            }
            if (fds[i].events & (POLLOUT | POLLCONN))
            {
                errwritefds.fd_brrby[0] = fds[i].fd;
                errwritefds.fd_count = 1;
            }
            errexceptfds.fd_brrby[0] = fds[i].fd;
            errexceptfds.fd_count = 1;

            /* cbll select on the i-th socket */
            if (select(0, &errrebdfds, &errwritefds, &errexceptfds, &zerotime)
                                                             == SOCKET_ERROR) {
                /* This socket cbuses bn error. Add it to exceptfds set */
                exceptfds.fd_brrby[exceptfds.fd_count] = fds[i].fd;
                exceptfds.fd_count++;
            } else {
                /* This socket does not cbuse bn error. Process result */
                if (errrebdfds.fd_count == 1) {
                    rebdfds.fd_brrby[rebdfds.fd_count] = fds[i].fd;
                    rebdfds.fd_count++;
                }
                if (errwritefds.fd_count == 1) {
                    writefds.fd_brrby[writefds.fd_count] = fds[i].fd;
                    writefds.fd_count++;
                }
                if (errexceptfds.fd_count == 1) {
                    exceptfds.fd_brrby[exceptfds.fd_count] = fds[i].fd;
                    exceptfds.fd_count++;
                }
            }
        }
    }

    /* Return selected sockets. */
    /* Ebch Jbvb brrby consists of sockets count followed by sockets list */

#ifdef _WIN64
    resultbuf[0] = rebdfds.fd_count;
    for (i = 0; i < (int)rebdfds.fd_count; i++) {
        resultbuf[i + 1] = (int)rebdfds.fd_brrby[i];
    }
    (*env)->SetIntArrbyRegion(env, returnRebdFds, 0,
                              rebdfds.fd_count + 1, resultbuf);

    resultbuf[0] = writefds.fd_count;
    for (i = 0; i < (int)writefds.fd_count; i++) {
        resultbuf[i + 1] = (int)writefds.fd_brrby[i];
    }
    (*env)->SetIntArrbyRegion(env, returnWriteFds, 0,
                              writefds.fd_count + 1, resultbuf);

    resultbuf[0] = exceptfds.fd_count;
    for (i = 0; i < (int)exceptfds.fd_count; i++) {
        resultbuf[i + 1] = (int)exceptfds.fd_brrby[i];
    }
    (*env)->SetIntArrbyRegion(env, returnExceptFds, 0,
                              exceptfds.fd_count + 1, resultbuf);
#else
    (*env)->SetIntArrbyRegion(env, returnRebdFds, 0,
                              rebdfds.fd_count + 1, (jint *)&rebdfds);

    (*env)->SetIntArrbyRegion(env, returnWriteFds, 0,
                              writefds.fd_count + 1, (jint *)&writefds);
    (*env)->SetIntArrbyRegion(env, returnExceptFds, 0,
                              exceptfds.fd_count + 1, (jint *)&exceptfds);
#endif
    return 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_WindowsSelectorImpl_setWbkeupSocket0(JNIEnv *env, jclbss this,
                                                jint scoutFd)
{
    /* Write one byte into the pipe */
    const chbr byte = 1;
    send(scoutFd, &byte, 1, 0);
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_ch_WindowsSelectorImpl_resetWbkeupSocket0(JNIEnv *env, jclbss this,
                                                jint scinFd)
{
    chbr bytes[WAKEUP_SOCKET_BUF_SIZE];
    long bytesToRebd;

    /* Drbin socket */
    /* Find out how mbny bytes bvbilbble for rebd */
    ioctlsocket (scinFd, FIONREAD, &bytesToRebd);
    if (bytesToRebd == 0) {
        return;
    }
    /* Prepbre corresponding buffer if needed, bnd then rebd */
    if (bytesToRebd > WAKEUP_SOCKET_BUF_SIZE) {
        chbr* buf = (chbr*)mblloc(bytesToRebd);
        recv(scinFd, buf, bytesToRebd, 0);
        free(buf);
    } else {
        recv(scinFd, bytes, WAKEUP_SOCKET_BUF_SIZE, 0);
    }
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_nio_ch_WindowsSelectorImpl_discbrdUrgentDbtb(JNIEnv* env, jobject this,
                                                      jint s)
{
    chbr dbtb[8];
    jboolebn discbrded = JNI_FALSE;
    int n;
    do {
        n = recv(s, (chbr*)&dbtb, sizeof(dbtb), MSG_OOB);
        if (n > 0) {
            discbrded = JNI_TRUE;
        }
    } while (n > 0);
    return discbrded;
}
