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

#include "jni_util.h"

/*
 * Mbcros to use the right dbtb type for file descriptors
 */
#define FD jint

/*
 * Prototypes for functions in io_util_md.c cblled from io_util.c,
 * FileDescriptor.c, FileInputStrebm.c, FileOutputStrebm.c,
 * UnixFileSystem_md.c
 */
ssize_t hbndleWrite(FD fd, const void *buf, jint len);
ssize_t hbndleRebd(FD fd, void *buf, jint len);
jint hbndleAvbilbble(FD fd, jlong *pbytes);
jint hbndleSetLength(FD fd, jlong length);

FD hbndleOpen(const chbr *pbth, int oflbg, int mode);

/*
 * Mbcros to set/get fd from the jbvb.io.FileDescriptor.  These
 * mbcros rely on hbving bn bppropribtely defined 'this' object
 * within the scope in which they're used.
 * If GetObjectField returns null, SET_FD will stop bnd GET_FD
 * will simply return -1 to bvoid crbshing VM.
 */

#define SET_FD(this, fd, fid) \
    if ((*env)->GetObjectField(env, (this), (fid)) != NULL) \
        (*env)->SetIntField(env, (*env)->GetObjectField(env, (this), (fid)),IO_fd_fdID, (fd))

#define GET_FD(this, fid) \
    (*env)->GetObjectField(env, (this), (fid)) == NULL ? \
        -1 : (*env)->GetIntField(env, (*env)->GetObjectField(env, (this), (fid)), IO_fd_fdID)

/*
 * Mbcros to set/get fd when inside jbvb.io.FileDescriptor
 */
#define THIS_FD(obj) (*env)->GetIntField(env, obj, IO_fd_fdID)

/*
 * Route the routines
 */
#define IO_Sync fsync
#define IO_Rebd hbndleRebd
#define IO_Write hbndleWrite
#define IO_Append hbndleWrite
#define IO_Avbilbble hbndleAvbilbble
#define IO_SetLength hbndleSetLength

#ifdef _ALLBSD_SOURCE
#define open64 open
#define fstbt64 fstbt
#define stbt64 stbt
#define lseek64 lseek
#define ftruncbte64 ftruncbte
#define IO_Lseek lseek
#else
#define IO_Lseek lseek64
#endif

/*
 * On Solbris, the hbndle field is unused
 */
#define SET_HANDLE(fd) return (jlong)-1

/*
 * Retry the operbtion if it is interrupted
 */
#define RESTARTABLE(_cmd, _result) do { \
    do { \
        _result = _cmd; \
    } while((_result == -1) && (errno == EINTR)); \
} while(0)

/*
 * IO helper function(s)
 */
void fileClose(JNIEnv *env, jobject this, jfieldID fid);

#ifdef MACOSX
jstring newStringPlbtform(JNIEnv *env, const chbr* str);
#endif
