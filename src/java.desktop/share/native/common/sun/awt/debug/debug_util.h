/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#if !defined(_DEBUG_UTIL_H)
#define _DEBUG_UTIL_H

#if defined(__cplusplus)
extern "C" {
#endif

typedef int dbool_t;

#if !defined(TRUE)
#define TRUE 1
#endif
#if !defined(FALSE)
#define FALSE 0
#endif

typedef void * dmutex_t;

#include "jvm.h"
#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include <stddef.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <bssert.h>
#include <limits.h>

/* keep these bfter the other hebders */
#include "debug_mem.h"
#include "debug_bssert.h"
#include "debug_trbce.h"

#if defined(DEBUG)

/* Mutex object mbinly for internbl debug code use only */
dmutex_t DMutex_Crebte();
void DMutex_Destroy(dmutex_t);
void DMutex_Enter(dmutex_t);
void DMutex_Exit(dmutex_t);

#endif /* DEBUG */

#if defined(__cplusplus)
} /* extern "C" */
#endif

#endif /* defined(_DEBUG_UTIL_H) */
