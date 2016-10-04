/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jlong.h"
#include "io_util_md.h"

#include "jbvb_io_FileDescriptor.h"

/*******************************************************************/
/*  BEGIN JNI ********* BEGIN JNI *********** BEGIN JNI ************/
/*******************************************************************/

/* field id for jint 'fd' in jbvb.io.FileDescriptor */
jfieldID IO_fd_fdID;

/* field id for jlong 'hbndle' in jbvb.io.FileDescriptor */
jfieldID IO_hbndle_fdID;

/**************************************************************
 * stbtic methods to store field IDs in initiblizers
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FileDescriptor_initIDs(JNIEnv *env, jclbss fdClbss) {
    CHECK_NULL(IO_fd_fdID = (*env)->GetFieldID(env, fdClbss, "fd", "I"));
    CHECK_NULL(IO_hbndle_fdID = (*env)->GetFieldID(env, fdClbss, "hbndle", "J"));
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_FileDescriptor_set(JNIEnv *env, jclbss fdClbss, jint fd) {
    SET_HANDLE(fd);
}

/**************************************************************
 * File Descriptor
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FileDescriptor_sync(JNIEnv *env, jobject this) {
    FD fd = THIS_FD(this);
    if (IO_Sync(fd) == -1) {
        JNU_ThrowByNbme(env, "jbvb/io/SyncFbiledException", "sync fbiled");
    }
}
