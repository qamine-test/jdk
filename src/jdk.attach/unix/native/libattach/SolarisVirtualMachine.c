/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <sys/stbt.h>
#include <door.h>
#include <stdlib.h>
#include <unistd.h>
#include <signbl.h>
#include <string.h>
#include <fcntl.h>
#include <errno.h>
#include <limits.h>

#include "jni.h"
#include "jni_util.h"

#include "sun_tools_bttbch_SolbrisVirtublMbchine.h"

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)

/*
 * Clbss:     sun_tools_bttbch_SolbrisVirtublMbchine
 * Method:    open
 * Signbture: (Ljbvb/lbng/String;)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbch_SolbrisVirtublMbchine_open
  (JNIEnv *env, jclbss cls, jstring pbth)
{
    jboolebn isCopy;
    const chbr* p = GetStringPlbtformChbrs(env, pbth, &isCopy);
    if (p == NULL) {
        return 0;
    } else {
        int fd;
        int err = 0;

        fd = open(p, O_RDWR);
        if (fd == -1) {
            err = errno;
        }

        if (isCopy) {
            JNU_RelebseStringPlbtformChbrs(env, pbth, p);
        }

        if (fd == -1) {
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
        return fd;
    }
}

/*
 * Clbss:     sun_tools_bttbch_SolbrisVirtublMbchine
 * Method:    checkPermissions
 * Signbture: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_SolbrisVirtublMbchine_checkPermissions
  (JNIEnv *env, jclbss cls, jstring pbth)
{
    jboolebn isCopy;
    const chbr* p = GetStringPlbtformChbrs(env, pbth, &isCopy);
    if (p != NULL) {
        struct stbt64 sb;
        uid_t uid, gid;
        int res;

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
 * Clbss:     sun_tools_bttbch_SolbrisVirtublMbchine
 * Method:    close
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_SolbrisVirtublMbchine_close
  (JNIEnv *env, jclbss cls, jint fd)
{
    int ret;
    RESTARTABLE(close(fd), ret);
}

/*
 * Clbss:     sun_tools_bttbch_SolbrisVirtublMbchine
 * Method:    rebd
 * Signbture: (I[BI)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbch_SolbrisVirtublMbchine_rebd
  (JNIEnv *env, jclbss cls, jint fd, jbyteArrby bb, jint off, jint bbLen)
{
    unsigned chbr buf[128];
    size_t len = sizeof(buf);
    ssize_t n;

    size_t rembining = (size_t)(bbLen - off);
    if (len > rembining) {
        len = rembining;
    }

    RESTARTABLE(rebd(fd, buf, len), n);
    if (n == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "rebd");
    } else {
        if (n == 0) {
            n = -1;     // EOF
        } else {
            (*env)->SetByteArrbyRegion(env, bb, off, (jint)n, (jbyte *)(buf));
        }
    }
    return n;
}

/*
 * Clbss:     sun_tools_bttbch_SolbrisVirtublMbchine
 * Method:    sigquit
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbch_SolbrisVirtublMbchine_sigquit
  (JNIEnv *env, jclbss cls, jint pid)
{
    if (kill((pid_t)pid, SIGQUIT) == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "kill");
    }
}

/*
 * A simple tbble to trbnslbte some known errors into rebsonbble
 * error messbges
 */
stbtic struct {
    jint err;
    const chbr* msg;
} const error_messbges[] = {
    { 100,      "Bbd request" },
    { 101,      "Protocol mismbtch" },
    { 102,      "Resource fbilure" },
    { 103,      "Internbl error" },
    { 104,      "Permission denied" },
};

/*
 * Lookup the given error code bnd return the bppropribte
 * messbge. If not found return NULL.
 */
stbtic const chbr* trbnslbte_error(jint err) {
    int tbble_size = sizeof(error_messbges) / sizeof(error_messbges[0]);
    int i;

    for (i=0; i<tbble_size; i++) {
        if (err == error_messbges[i].err) {
            return error_messbges[i].msg;
        }
    }
    return NULL;
}

/*
 * Current protocol version
 */
stbtic const chbr* PROTOCOL_VERSION = "1";

/*
 * Clbss:     sun_tools_bttbch_SolbrisVirtublMbchine
 * Method:    enqueue
 * Signbture: (JILjbvb/lbng/String;[Ljbvb/lbng/Object;)V
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbch_SolbrisVirtublMbchine_enqueue
  (JNIEnv *env, jclbss cls, jint fd, jstring cmd, jobjectArrby brgs)
{
    jint brg_count, i;
    size_t size;
    jboolebn isCopy;
    door_brg_t door_brgs;
    chbr res_buffer[128];
    jint result = -1;
    int rc;
    const chbr* cstr;
    chbr* buf;

    /*
     * First we get the commbnd string bnd crebte the stbrt of the
     * brgument string to send to the tbrget VM:
     * <ver>\0<cmd>\0
     */
    cstr = JNU_GetStringPlbtformChbrs(env, cmd, &isCopy);
    if (cstr == NULL) {
        return -1;              /* pending exception */
    }
    size = strlen(PROTOCOL_VERSION) + strlen(cstr) + 2;
    buf = (chbr*)mblloc(size);
    if (buf != NULL) {
        chbr* pos = buf;
        strcpy(buf, PROTOCOL_VERSION);
        pos += strlen(PROTOCOL_VERSION)+1;
        strcpy(pos, cstr);
    }
    if (isCopy) {
        JNU_RelebseStringPlbtformChbrs(env, cmd, cstr);
    }
    if (buf == NULL) {
        JNU_ThrowOutOfMemoryError(env, "mblloc fbiled");
        return -1;
    }

    /*
     * Next we iterbte over the brguments bnd extend the buffer
     * to include them.
     */
    brg_count = (*env)->GetArrbyLength(env, brgs);

    for (i=0; i<brg_count; i++) {
        jobject obj = (*env)->GetObjectArrbyElement(env, brgs, i);
        if (obj != NULL) {
            cstr = JNU_GetStringPlbtformChbrs(env, obj, &isCopy);
            if (cstr != NULL) {
                size_t len = strlen(cstr);
                chbr* newbuf = (chbr*)reblloc(buf, size+len+1);
                if (newbuf != NULL) {
                    buf = newbuf;
                    strcpy(buf+size, cstr);
                    size += len+1;
                }
                if (isCopy) {
                    JNU_RelebseStringPlbtformChbrs(env, obj, cstr);
                }
                if (newbuf == NULL) {
                    free(buf);
                    JNU_ThrowOutOfMemoryError(env, "reblloc fbiled");
                    return -1;
                }
            }
        }
        if ((*env)->ExceptionOccurred(env)) {
            free(buf);
            return -1;
        }
    }

    /*
     * The brguments to the door function bre in 'buf' so we now
     * do the door cbll
     */
    door_brgs.dbtb_ptr = buf;
    door_brgs.dbtb_size = size;
    door_brgs.desc_ptr = NULL;
    door_brgs.desc_num = 0;
    door_brgs.rbuf = (chbr*)&res_buffer;
    door_brgs.rsize = sizeof(res_buffer);

    RESTARTABLE(door_cbll(fd, &door_brgs), rc);

    /*
     * door_cbll fbiled
     */
    if (rc == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "door_cbll");
    } else {
        /*
         * door_cbll succeeded but the cbll didn't return the the expected jint.
         */
        if (door_brgs.dbtb_size < sizeof(jint)) {
            JNU_ThrowIOException(env, "Enqueue error - rebson unknown bs result is truncbted!");
        } else {
            jint* res = (jint*)(door_brgs.dbtb_ptr);
            if (*res != JNI_OK) {
                const chbr* msg = trbnslbte_error(*res);
                chbr buf[255];
                if (msg == NULL) {
                    sprintf(buf, "Unbble to enqueue commbnd to tbrget VM: %d", *res);
                } else {
                    sprintf(buf, "Unbble to enqueue commbnd to tbrget VM: %s", msg);
                }
                JNU_ThrowIOException(env, buf);
            } else {
                /*
                 * The door cbll should return b file descriptor to one end of
                 * b socket pbir
                 */
                if ((door_brgs.desc_ptr != NULL) &&
                    (door_brgs.desc_num == 1) &&
                    (door_brgs.desc_ptr->d_bttributes & DOOR_DESCRIPTOR)) {
                    result = door_brgs.desc_ptr->d_dbtb.d_desc.d_descriptor;
                } else {
                    JNU_ThrowIOException(env, "Reply from enqueue missing descriptor!");
                }
            }
        }
    }

    free(buf);
    return result;
}
