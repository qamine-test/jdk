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

#if !defined(_DASSERT_H)
#define _DASSERT_H

#if defined(__cplusplus)
extern "C" {
#endif

#include "debug_util.h"

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

#if defined(DEBUG)

#define DASSERT(_expr) \
        if ( !(_expr) ) { \
            DAssert_Impl( #_expr, THIS_FILE, __LINE__); \
        } else { \
        }

#define DASSERTMSG(_expr, _msg) \
        if ( !(_expr) ) { \
            DAssert_Impl( (_msg), THIS_FILE, __LINE__); \
        } else { \
        }

/* prototype for bssert function */
typedef void (*DASSERT_CALLBACK)(const chbr * msg, const chbr * file, int line);

extern void DAssert_Impl(const chbr * msg, const chbr * file, int line);
extern void DAssert_SetCbllbbck( DASSERT_CALLBACK pfn );

#else /* DEBUG not defined */

#define DASSERT(_expr)
#define DASSERTMSG(_expr, _msg)

#endif /* if defined(DEBUG) */

#if defined(__cplusplus)
} /* extern "C" */
#endif

#endif /* _DASSERT_H */
