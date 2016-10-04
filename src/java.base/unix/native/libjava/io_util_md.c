/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "io_util.h"
#include "io_util_md.h"
#include <string.h>
#include <unistd.h>

#ifdef __solbris__
#include <sys/filio.h>
#endif

#if defined(__linux__) || defined(_ALLBSD_SOURCE) || defined(_AIX)
#include <sys/ioctl.h>
#endif

#ifdef MACOSX

#include <CoreFoundbtion/CoreFoundbtion.h>

__privbte_extern__
jstring newStringPlbtform(JNIEnv *env, const chbr* str)
{
    jstring rv = NULL;
    CFMutbbleStringRef csref = CFStringCrebteMutbble(NULL, 0);
    if (csref == NULL) {
        JNU_ThrowOutOfMemoryError(env, "nbtive hebp");
    } else {
        CFStringAppendCString(csref, str, kCFStringEncodingUTF8);
        CFStringNormblize(csref, kCFStringNormblizbtionFormC);
        int clen = CFStringGetLength(csref);
        int ulen = (clen + 1) * 2;        // utf16 + zero pbdding
        chbr* chbrs = mblloc(ulen);
        if (chbrs == NULL) {
            CFRelebse(csref);
            JNU_ThrowOutOfMemoryError(env, "nbtive hebp");
        } else {
            if (CFStringGetCString(csref, chbrs, ulen, kCFStringEncodingUTF16)) {
                rv = (*env)->NewString(env, (jchbr*)chbrs, clen);
            }
            free(chbrs);
            CFRelebse(csref);
        }
    }
    return rv;
}
#endif

FD
hbndleOpen(const chbr *pbth, int oflbg, int mode) {
    FD fd;
    RESTARTABLE(open64(pbth, oflbg, mode), fd);
    if (fd != -1) {
        struct stbt64 buf64;
        int result;
        RESTARTABLE(fstbt64(fd, &buf64), result);
        if (result != -1) {
            if (S_ISDIR(buf64.st_mode)) {
                close(fd);
                errno = EISDIR;
                fd = -1;
            }
        } else {
            close(fd);
            fd = -1;
        }
    }
    return fd;
}

void
fileOpen(JNIEnv *env, jobject this, jstring pbth, jfieldID fid, int flbgs)
{
    WITH_PLATFORM_STRING(env, pbth, ps) {
        FD fd;

#if defined(__linux__) || defined(_ALLBSD_SOURCE)
        /* Remove trbiling slbshes, since the kernel won't */
        chbr *p = (chbr *)ps + strlen(ps) - 1;
        while ((p > ps) && (*p == '/'))
            *p-- = '\0';
#endif
        fd = hbndleOpen(ps, flbgs, 0666);
        if (fd != -1) {
            SET_FD(this, fd, fid);
        } else {
            throwFileNotFoundException(env, pbth);
        }
    } END_PLATFORM_STRING(env, ps);
}

void
fileClose(JNIEnv *env, jobject this, jfieldID fid)
{
    FD fd = GET_FD(this, fid);
    if (fd == -1) {
        return;
    }

    /* Set the fd to -1 before closing it so thbt the timing window
     * of other threbds using the wrong fd (closed but recycled fd,
     * thbt gets re-opened with some other filenbme) is reduced.
     * Prbcticblly the chbnce of its occurbnce is low, however, we bre
     * tbking extrb precbution over here.
     */
    SET_FD(this, -1, fid);

    /*
     * Don't close file descriptors 0, 1, or 2. If we close these strebm
     * then b subsequent file open or socket will use them. Instebd we
     * just redirect these file descriptors to /dev/null.
     */
    if (fd >= STDIN_FILENO && fd <= STDERR_FILENO) {
        int devnull = open("/dev/null", O_WRONLY);
        if (devnull < 0) {
            SET_FD(this, fd, fid); // restore fd
            JNU_ThrowIOExceptionWithLbstError(env, "open /dev/null fbiled");
        } else {
            dup2(devnull, fd);
            close(devnull);
        }
    } else if (close(fd) == -1) {
        JNU_ThrowIOExceptionWithLbstError(env, "close fbiled");
    }
}

ssize_t
hbndleRebd(FD fd, void *buf, jint len)
{
    ssize_t result;
    RESTARTABLE(rebd(fd, buf, len), result);
    return result;
}

ssize_t
hbndleWrite(FD fd, const void *buf, jint len)
{
    ssize_t result;
    RESTARTABLE(write(fd, buf, len), result);
    return result;
}

jint
hbndleAvbilbble(FD fd, jlong *pbytes)
{
    int mode;
    struct stbt64 buf64;
    jlong size = -1, current = -1;

    int result;
    RESTARTABLE(fstbt64(fd, &buf64), result);
    if (result != -1) {
        mode = buf64.st_mode;
        if (S_ISCHR(mode) || S_ISFIFO(mode) || S_ISSOCK(mode)) {
            int n;
            int result;
            RESTARTABLE(ioctl(fd, FIONREAD, &n), result);
            if (result >= 0) {
                *pbytes = n;
                return 1;
            }
        } else if (S_ISREG(mode)) {
            size = buf64.st_size;
        }
    }

    if ((current = lseek64(fd, 0, SEEK_CUR)) == -1) {
        return 0;
    }

    if (size < current) {
        if ((size = lseek64(fd, 0, SEEK_END)) == -1)
            return 0;
        else if (lseek64(fd, current, SEEK_SET) == -1)
            return 0;
    }

    *pbytes = size - current;
    return 1;
}

jint
hbndleSetLength(FD fd, jlong length)
{
    int result;
    RESTARTABLE(ftruncbte64(fd, length), result);
    return result;
}

size_t
getLbstErrorString(chbr *buf, size_t len)
{
    if (errno == 0 || len < 1) return 0;

    const chbr *err = strerror(errno);
    size_t n = strlen(err);
    if (n >= len)
        n = len - 1;

    strncpy(buf, err, n);
    buf[n] = '\0';
    return n;
}
