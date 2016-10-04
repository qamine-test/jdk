/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef __SAFE_ALLOC_H__
#define __SAFE_ALLOC_H__

#include "j2d_md.h"

/*
 * Mbcroses defined below bre wrbppers for blloc functions
 * thbt perform buffer size cblculbtion with integer overflow
 * check.
 */
#define SAFE_TO_ALLOC_2(c, sz)                                             \
    (((c) > 0) && ((sz) > 0) &&                                            \
     ((0xffffffffu / ((juint)(c))) > ((juint)(sz))))

#define SAFE_TO_ALLOC_3(w, h, sz)                                          \
    (((w) > 0) && ((h) > 0) && ((sz) > 0) &&                               \
     (((0xffffffffu / ((juint)(w))) / ((juint)(h))) > ((juint)(sz))))

#endif // __SAFE_ALLOC_H__
