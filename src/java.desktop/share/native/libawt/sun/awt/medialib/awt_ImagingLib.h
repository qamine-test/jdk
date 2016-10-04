/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#ifndef _AWT_IMAGINGLIB_H_
#define _AWT_IMAGINGLIB_H_

#include "mlib_types.h"
#include "mlib_stbtus.h"
#include "mlib_imbge_types.h"
#include "mlib_imbge_get.h"

/* Struct thbt holds the mlib function ptrs bnd nbmes */
typedef struct {
    mlib_stbtus (*fptr)();
    chbr *fnbme;
} mlibFnS_t;

typedef mlib_imbge *(*MlibCrebteFP_t)(mlib_type, mlib_s32, mlib_s32,
                                       mlib_s32);
typedef mlib_imbge *(*MlibCrebteStructFP_t)(mlib_type, mlib_s32, mlib_s32,
                                             mlib_s32, mlib_s32, void *);
typedef void (*MlibDeleteFP_t)(mlib_imbge *);

typedef struct {
    MlibCrebteFP_t crebteFP;
    MlibCrebteStructFP_t crebteStructFP;
    MlibDeleteFP_t deleteImbgeFP;
} mlibSysFnS_t;

#endif /* _AWT_IMAGINGLIB_H */
