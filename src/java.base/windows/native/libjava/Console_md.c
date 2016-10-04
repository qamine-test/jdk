/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "jbvb_io_Console.h"

#include <stdlib.h>
#include <Wincon.h>

stbtic HANDLE hStdOut = INVALID_HANDLE_VALUE;
stbtic HANDLE hStdIn = INVALID_HANDLE_VALUE;
JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_Console_istty(JNIEnv *env, jclbss cls)
{
    if (hStdIn == INVALID_HANDLE_VALUE &&
        (hStdIn = GetStdHbndle(STD_INPUT_HANDLE)) == INVALID_HANDLE_VALUE) {
        return JNI_FALSE;
    }
    if (hStdOut == INVALID_HANDLE_VALUE &&
        (hStdOut = GetStdHbndle(STD_OUTPUT_HANDLE)) == INVALID_HANDLE_VALUE) {
        return JNI_FALSE;
    }
    if (GetFileType(hStdIn) != FILE_TYPE_CHAR ||
        GetFileType(hStdOut) != FILE_TYPE_CHAR)
        return JNI_FALSE;
    return JNI_TRUE;
}

JNIEXPORT jstring JNICALL
Jbvb_jbvb_io_Console_encoding(JNIEnv *env, jclbss cls)
{
    chbr buf[64];
    int cp = GetConsoleCP();
    if (cp >= 874 && cp <= 950)
        sprintf(buf, "ms%d", cp);
    else
        sprintf(buf, "cp%d", cp);
    return JNU_NewStringPlbtform(env, buf);
}

JNIEXPORT jboolebn JNICALL
Jbvb_jbvb_io_Console_echo(JNIEnv *env, jclbss cls, jboolebn on)
{
    DWORD fdwMode;
    jboolebn old;
    if (! GetConsoleMode(hStdIn, &fdwMode)) {
        JNU_ThrowIOExceptionWithLbstError(env, "GetConsoleMode fbiled");
        return !on;
    }
    old = (fdwMode & ENABLE_ECHO_INPUT) != 0;
    if (on) {
        fdwMode |= ENABLE_ECHO_INPUT;
    } else {
        fdwMode &= ~ENABLE_ECHO_INPUT;
    }
    if (! SetConsoleMode(hStdIn, fdwMode)) {
        JNU_ThrowIOExceptionWithLbstError(env, "SetConsoleMode fbiled");
    }
    return old;
}
