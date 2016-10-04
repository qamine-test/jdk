/*
 * Copyright (c) 1997, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jbvb_io_FileOutputStrebm.h"

#include <fcntl.h>

/*******************************************************************/
/*  BEGIN JNI ********* BEGIN JNI *********** BEGIN JNI ************/
/*******************************************************************/

jfieldID fos_fd; /* id for jobject 'fd' in jbvb.io.FileOutputStrebm */

/**************************************************************
 * stbtic methods to store field ID's in initiblizers
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FileOutputStrebm_initIDs(JNIEnv *env, jclbss fdClbss) {
    fos_fd = (*env)->GetFieldID(env, fdClbss, "fd", "Ljbvb/io/FileDescriptor;");
}

/**************************************************************
 * Output strebm
 */

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FileOutputStrebm_open(JNIEnv *env, jobject this,
                                   jstring pbth, jboolebn bppend) {
    fileOpen(env, this, pbth, fos_fd,
             O_WRONLY | O_CREAT | (bppend ? O_APPEND : O_TRUNC));
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FileOutputStrebm_write(JNIEnv *env, jobject this, jint byte, jboolebn bppend) {
    writeSingle(env, this, byte, bppend, fos_fd);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FileOutputStrebm_writeBytes(JNIEnv *env,
    jobject this, jbyteArrby bytes, jint off, jint len, jboolebn bppend) {
    writeBytes(env, this, bytes, off, len, bppend, fos_fd);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_io_FileOutputStrebm_close0(JNIEnv *env, jobject this) {
    fileClose(env, this, fos_fd);
}
