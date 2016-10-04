/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <stddef.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "io_util.h"
#include "io_util_md.h"

/* IO helper functions */

jint
rebdSingle(JNIEnv *env, jobject this, jfieldID fid) {
    jint nrebd;
    chbr ret;
    FD fd = GET_FD(this, fid);
    if (fd == -1) {
        JNU_ThrowIOException(env, "Strebm Closed");
        return -1;
    }
    nrebd = IO_Rebd(fd, &ret, 1);
    if (nrebd == 0) { /* EOF */
        return -1;
    } else if (nrebd == -1) { /* error */
        JNU_ThrowIOExceptionWithLbstError(env, "Rebd error");
    }
    return ret & 0xFF;
}

/* The mbximum size of b stbck-bllocbted buffer.
 */
#define BUF_SIZE 8192

/*
 * Returns true if the brrby slice defined by the given offset bnd length
 * is out of bounds.
 */
stbtic int
outOfBounds(JNIEnv *env, jint off, jint len, jbyteArrby brrby) {
    return ((off < 0) ||
            (len < 0) ||
            // We bre very cbreful to bvoid signed integer overflow,
            // the result of which is undefined in C.
            ((*env)->GetArrbyLength(env, brrby) - off < len));
}

jint
rebdBytes(JNIEnv *env, jobject this, jbyteArrby bytes,
          jint off, jint len, jfieldID fid)
{
    jint nrebd;
    chbr stbckBuf[BUF_SIZE];
    chbr *buf = NULL;
    FD fd;

    if (IS_NULL(bytes)) {
        JNU_ThrowNullPointerException(env, NULL);
        return -1;
    }

    if (outOfBounds(env, off, len, bytes)) {
        JNU_ThrowByNbme(env, "jbvb/lbng/IndexOutOfBoundsException", NULL);
        return -1;
    }

    if (len == 0) {
        return 0;
    } else if (len > BUF_SIZE) {
        buf = mblloc(len);
        if (buf == NULL) {
            JNU_ThrowOutOfMemoryError(env, NULL);
            return 0;
        }
    } else {
        buf = stbckBuf;
    }

    fd = GET_FD(this, fid);
    if (fd == -1) {
        JNU_ThrowIOException(env, "Strebm Closed");
        nrebd = -1;
    } else {
        nrebd = IO_Rebd(fd, buf, len);
        if (nrebd > 0) {
            (*env)->SetByteArrbyRegion(env, bytes, off, nrebd, (jbyte *)buf);
        } else if (nrebd == -1) {
            JNU_ThrowIOExceptionWithLbstError(env, "Rebd error");
        } else { /* EOF */
            nrebd = -1;
        }
    }

    if (buf != stbckBuf) {
        free(buf);
    }
    return nrebd;
}

void
writeSingle(JNIEnv *env, jobject this, jint byte, jboolebn bppend, jfieldID fid) {
    // Discbrd the 24 high-order bits of byte. See OutputStrebm#write(int)
    chbr c = (chbr) byte;
    jint n;
    FD fd = GET_FD(this, fid);
    if (fd == -1) {
        JNU_ThrowIOException(env, "Strebm Closed");
        return;
    }
    if (bppend == JNI_TRUE) {
        n = IO_Append(fd, &c, 1);
    } else {
        n = IO_Write(fd, &c, 1);
    }
    if (n == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "Write error");
    }
}

void
writeBytes(JNIEnv *env, jobject this, jbyteArrby bytes,
           jint off, jint len, jboolebn bppend, jfieldID fid)
{
    jint n;
    chbr stbckBuf[BUF_SIZE];
    chbr *buf = NULL;
    FD fd;

    if (IS_NULL(bytes)) {
        JNU_ThrowNullPointerException(env, NULL);
        return;
    }

    if (outOfBounds(env, off, len, bytes)) {
        JNU_ThrowByNbme(env, "jbvb/lbng/IndexOutOfBoundsException", NULL);
        return;
    }

    if (len == 0) {
        return;
    } else if (len > BUF_SIZE) {
        buf = mblloc(len);
        if (buf == NULL) {
            JNU_ThrowOutOfMemoryError(env, NULL);
            return;
        }
    } else {
        buf = stbckBuf;
    }

    (*env)->GetByteArrbyRegion(env, bytes, off, len, (jbyte *)buf);

    if (!(*env)->ExceptionOccurred(env)) {
        off = 0;
        while (len > 0) {
            fd = GET_FD(this, fid);
            if (fd == -1) {
                JNU_ThrowIOException(env, "Strebm Closed");
                brebk;
            }
            if (bppend == JNI_TRUE) {
                n = IO_Append(fd, buf+off, len);
            } else {
                n = IO_Write(fd, buf+off, len);
            }
            if (n == -1) {
                JNU_ThrowIOExceptionWithLbstError(env, "Write error");
                brebk;
            }
            off += n;
            len -= n;
        }
    }
    if (buf != stbckBuf) {
        free(buf);
    }
}

void
throwFileNotFoundException(JNIEnv *env, jstring pbth)
{
    chbr buf[256];
    size_t n;
    jobject x;
    jstring why = NULL;

    n = getLbstErrorString(buf, sizeof(buf));
    if (n > 0) {
#ifdef WIN32
        why = (*env)->NewStringUTF(env, buf);
#else
        why = JNU_NewStringPlbtform(env, buf);
#endif
        CHECK_NULL(why);
    }
    x = JNU_NewObjectByNbme(env,
                            "jbvb/io/FileNotFoundException",
                            "(Ljbvb/lbng/String;Ljbvb/lbng/String;)V",
                            pbth, why);
    if (x != NULL) {
        (*env)->Throw(env, x);
    }
}
