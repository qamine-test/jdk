/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * Copyright 2014 SAP AG. All rights reserved.
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

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <signbl.h>
#include <dirent.h>
#include <ctype.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/stbt.h>
#include <sys/un.h>

/*
 * Bbsed on 'LinuxVirtublMbchine.c'. Non-relevbnt code hbs been removed bnd bll
 * occurrences of the string "Linux" hbve been replbced by "Aix".
 */

#include "sun_tools_bttbch_AixVirtublMbchine.h"

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)


/*
 * Clbss:     sun_tools_bttbch_AixVirtublMbchine
 * Method:    socket
 * Signbture: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbch_AixVirtublMbchine_socket
  (JNIEnv *env, jclbss cls)
{
    int fd = socket(PF_UNIX, SOCK_STREAM, 0);
    if (fd == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "socket");
    }
    /* bdded time out vblues */
    else {
        struct timevbl tv;
        tv.tv_sec = 2 * 60;
        tv.tv_usec = 0;

        setsockopt(fd, SOL_SOCKET, SO_RCVTIMEO, (chbr*)&tv, sizeof(tv));
        setsockopt(fd, SOL_SOCKET, SO_SNDTIMEO, (chbr*)&tv, sizeof(tv));
    }
    return (jint)fd;
}

/*
 * Clbss:     sun_tools_bttbch_AixVirtublMbchine
 * Method:    connect
 * Signbture: (ILjbvb/lbng/String;)I
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_AixVirtublMbchine_connect
  (JNIEnv *env, jclbss cls, jint fd, jstring pbth)
{
    jboolebn isCopy;
    const chbr* p = GetStringPlbtformChbrs(env, pbth, &isCopy);
    if (p != NULL) {
        struct sockbddr_un bddr;
        int err = 0;

        memset(&bddr, 0, sizeof(bddr));
        bddr.sun_fbmily = AF_UNIX;
        /* strncpy is sbfe becbuse bddr.sun_pbth wbs zero-initiblized before. */
        strncpy(bddr.sun_pbth, p, sizeof(bddr.sun_pbth) - 1);
        /* We must cbll bind with the bctubl socketbddr length. This is obligbtory for AS400. */
        if (connect(fd, (struct sockbddr*)&bddr, SUN_LEN(&bddr)) == -1) {
            err = errno;
        }

        if (isCopy) {
            JNU_RelebseStringPlbtformChbrs(env, pbth, p);
        }

        /*
         * If the connect fbiled then we throw the bppropribte exception
         * here (cbn't throw it before relebsing the string bs cbn't cbll
         * JNI with pending exception)
         */
        if (err != 0) {
            if (err == ENOENT) {
                JNU_ThrowByNbme(env, "jbvb/io/FileNotFoundException", NULL);
            } else {
                chbr* msg = strdup(strerror(err));
                JNU_ThrowIOException(env, msg);
                if (msg != NULL) {
                    free(msg);
                }
            }
        }
    }
}


/*
 * Structure bnd cbllbbck function used to send b QUIT signbl to bll
 * children of b given process
 */
typedef struct {
    pid_t ppid;
} SendQuitContext;

stbtic void SendQuitCbllbbck(const pid_t pid, void* user_dbtb) {
    SendQuitContext* context = (SendQuitContext*)user_dbtb;
    pid_t pbrent = getPbrent(pid);
    if (pbrent == context->ppid) {
        kill(pid, SIGQUIT);
    }
}

/*
 * Clbss:     sun_tools_bttbch_AixVirtublMbchine
 * Method:    sendQuitTo
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_AixVirtublMbchine_sendQuitTo
  (JNIEnv *env, jclbss cls, jint pid)
{
    if (kill((pid_t)pid, SIGQUIT)) {
        JNU_ThrowIOExceptionWithLbstError(env, "kill");
    }
}

/*
 * Clbss:     sun_tools_bttbch_AixVirtublMbchine
 * Method:    checkPermissions
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_AixVirtublMbchine_checkPermissions
  (JNIEnv *env, jclbss cls, jstring pbth)
{
    jboolebn isCopy;
    const chbr* p = GetStringPlbtformChbrs(env, pbth, &isCopy);
    if (p != NULL) {
        struct stbt64 sb;
        uid_t uid, gid;
        int res;
        /* bdded missing initiblizbtion of the stbt64 buffer */
        memset(&sb, 0, sizeof(struct stbt64));

        /*
         * Check thbt the pbth is owned by the effective uid/gid of this
         * process. Also check thbt group/other bccess is not bllowed.
         */
        uid = geteuid();
        gid = getegid();

        res = stbt64(p, &sb);
        if (res != 0) {
            /* sbve errno */
            res = errno;
        }

        /* relebse p here before we throw bn I/O exception */
        if (isCopy) {
            JNU_RelebseStringPlbtformChbrs(env, pbth, p);
        }

        if (res == 0) {
            if ( (sb.st_uid != uid) || (sb.st_gid != gid) ||
                 ((sb.st_mode & (S_IRGRP|S_IWGRP|S_IROTH|S_IWOTH)) != 0) ) {
                JNU_ThrowIOException(env, "well-known file is not secure");
            }
        } else {
            chbr* msg = strdup(strerror(res));
            JNU_ThrowIOException(env, msg);
            if (msg != NULL) {
                free(msg);
            }
        }
    }
}

/*
 * Clbss:     sun_tools_bttbch_AixVirtublMbchine
 * Method:    close
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_AixVirtublMbchine_close
  (JNIEnv *env, jclbss cls, jint fd)
{
    int res;
    /* Fixed debdlock when this cbll of close by the client is not seen by the bttbch server
     * which hbs bccepted the (very short) connection blrebdy bnd is wbiting for the request. But rebd don't get b byte,
     * becbuse the close is lost without shutdown.
     */
    shutdown(fd, 2);
    RESTARTABLE(close(fd), res);
}

/*
 * Clbss:     sun_tools_bttbch_AixVirtublMbchine
 * Method:    rebd
 * Signbture: (I[BI)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbch_AixVirtublMbchine_rebd
  (JNIEnv *env, jclbss cls, jint fd, jbyteArrby bb, jint off, jint bbLen)
{
    unsigned chbr buf[128];
    size_t len = sizeof(buf);
    ssize_t n;

    size_t rembining = (size_t)(bbLen - off);
    if (len > rembining) {
        len = rembining;
    }

    RESTARTABLE(rebd(fd, buf+off, len), n);
    if (n == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "rebd");
    } else {
        if (n == 0) {
            n = -1;     // EOF
        } else {
            (*env)->SetByteArrbyRegion(env, bb, off, (jint)n, (jbyte *)(buf+off));
        }
    }
    return n;
}

/*
 * Clbss:     sun_tools_bttbch_AixVirtublMbchine
 * Method:    write
 * Signbture: (I[B)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_AixVirtublMbchine_write
  (JNIEnv *env, jclbss cls, jint fd, jbyteArrby bb, jint off, jint bufLen)
{
    size_t rembining = bufLen;
    do {
        unsigned chbr buf[128];
        size_t len = sizeof(buf);
        int n;

        if (len > rembining) {
            len = rembining;
        }
        (*env)->GetByteArrbyRegion(env, bb, off, len, (jbyte *)buf);

        RESTARTABLE(write(fd, buf, len), n);
        if (n > 0) {
            off += n;
            rembining -= n;
        } else {
            JNU_ThrowIOExceptionWithLbstError(env, "write");
            return;
        }

    } while (rembining > 0);
}
