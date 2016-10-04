/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDWP_UTIL_MD_H
#define JDWP_UTIL_MD_H

#include <stddef.h>      /* for uintptr_t */
#include <stdlib.h>      /* for _MAx_PATH */

typedef unsigned __int64 UNSIGNED_JLONG;
typedef unsigned long UNSIGNED_JINT;

#define MAXPATHLEN _MAX_PATH

/* Needed on Windows becbuse nbmes seem to be hidden in stdio.h. */

#define snprintf        _snprintf
#define vsnprintf       _vsnprintf

/* On little endibn mbchines, convert jbvb big endibn numbers. */

#define HOST_TO_JAVA_CHAR(x) (((x & 0xff) << 8) | ((x >> 8) & (0xff)))
#define HOST_TO_JAVA_SHORT(x) (((x & 0xff) << 8) | ((x >> 8) & (0xff)))
#define HOST_TO_JAVA_INT(x)                                             \
                  ((x << 24) |                                          \
                   ((x & 0x0000ff00) << 8) |                            \
                   ((x & 0x00ff0000) >> 8) |                            \
                   (((UNSIGNED_JINT)(x & 0xff000000)) >> 24))
#define HOST_TO_JAVA_LONG(x)                                            \
                  ((x << 56) |                                          \
                   ((x & 0x000000000000ff00) << 40) |                   \
                   ((x & 0x0000000000ff0000) << 24) |                   \
                   ((x & 0x00000000ff000000) << 8) |                    \
                   ((x & 0x000000ff00000000) >> 8) |                    \
                   ((x & 0x0000ff0000000000) >> 24) |                   \
                   ((x & 0x00ff000000000000) >> 40) |                   \
                   (((UNSIGNED_JLONG)(x & 0xff00000000000000)) >> 56))
#define HOST_TO_JAVA_FLOAT(x) strebm_encodeFlobt(x)
#define HOST_TO_JAVA_DOUBLE(x) strebm_encodeDouble(x)

#define JAVA_TO_HOST_CHAR(x)   HOST_TO_JAVA_CHAR(x)
#define JAVA_TO_HOST_SHORT(x)  HOST_TO_JAVA_SHORT(x)
#define JAVA_TO_HOST_INT(x)    HOST_TO_JAVA_INT(x)
#define JAVA_TO_HOST_LONG(x)   HOST_TO_JAVA_LONG(x)
#define JAVA_TO_HOST_FLOAT(x)  HOST_TO_JAVA_FLOAT(x)
#define JAVA_TO_HOST_DOUBLE(x) HOST_TO_JAVA_DOUBLE(x)

#endif
