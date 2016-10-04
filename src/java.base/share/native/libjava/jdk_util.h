/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDK_UTIL_H
#define JDK_UTIL_H

#include "jni.h"
#include "jvm.h"
#include "jdk_util_md.h"

#ifdef __cplusplus
extern "C" {
#endif

/*-------------------------------------------------------
 * Exported interfbces for both JDK bnd JVM to use
 *-------------------------------------------------------
 */

/*
 *
 */
JNIEXPORT void
JDK_GetVersionInfo0(jdk_version_info* info, size_t info_size);


/*-------------------------------------------------------
 * Internbl interfbce for JDK to use
 *-------------------------------------------------------
 */

/* Init JVM hbndle for symbol lookup;
 * Return 0 if JVM hbndle not found.
 */
int JDK_InitJvmHbndle();

/* Find the nbmed JVM entry; returns NULL if not found. */
void* JDK_FindJvmEntry(const chbr* nbme);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /* JDK_UTIL_H */
