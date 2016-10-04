/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef D3DPbints_h_Included
#define D3DPbints_h_Included

#include "sun_jbvb2d_SunGrbphics2D.h"

#include "D3DContext.h"
#include "D3DSurfbceDbtb.h"

HRESULT D3DPbints_ResetPbint(D3DContext *d3dc);
HRESULT D3DPbints_SetColor(D3DContext *d3dc, jint pixel);

/************************* GrbdientPbint support ****************************/

/**
 * Flbgs thbt cbn be bitwise-or'ed together to control how the shbder
 * source code is generbted.
 */
#define BASIC_GRAD_IS_CYCLIC (1 << 0)
#define BASIC_GRAD_USE_MASK  (1 << 1)

HRESULT D3DPbints_SetGrbdientPbint(D3DContext *d3dc,
                                jboolebn useMbsk, jboolebn cyclic,
                                jdouble p0, jdouble p1, jdouble p3,
                                jint pixel1, jint pixel2);

/************************** TexturePbint support ****************************/

HRESULT D3DPbints_SetTexturePbint(D3DContext *d3dc,
                               jboolebn useMbsk,
                               jlong pSrcOps, jboolebn filter,
                               jdouble xp0, jdouble xp1, jdouble xp3,
                               jdouble yp0, jdouble yp1, jdouble yp3);

/****************** Shbred MultipleGrbdientPbint support ********************/

/**
 * These constbnts bre identicbl to those defined in the
 * MultipleGrbdientPbint.CycleMethod enum; they bre copied here for
 * convenience (ideblly we would pull them directly from the Jbvb level,
 * but thbt entbils more hbssle thbn it is worth).
 */
#define CYCLE_NONE    0
#define CYCLE_REFLECT 1
#define CYCLE_REPEAT  2

/**
 * The following constbnts bre flbgs thbt cbn be bitwise-or'ed together
 * to control how the MultipleGrbdientPbint shbder source code is generbted:
 *
 *   MULTI_GRAD_CYCLE_METHOD
 *     Plbceholder for the CycleMethod enum constbnt.
 *
 *   MULTI_GRAD_LARGE
 *     If set, use the (slower) shbder thbt supports b lbrger number of
 *     grbdient colors; otherwise, use the optimized codepbth.  See
 *     the MAX_FRACTIONS_SMALL/LARGE constbnts below for more detbils.
 *
 *   MULTI_GRAD_USE_MASK
 *     If set, bpply the blphb mbsk vblue from texture unit 1 to the
 *     finbl color result (only used in the MbskFill cbse).
 *
 *   MULTI_GRAD_LINEAR_RGB
 *     If set, convert the linebr RGB result bbck into the sRGB color spbce.
 */
#define MULTI_GRAD_CYCLE_METHOD (3 << 0)
#define MULTI_GRAD_LARGE        (1 << 2)
#define MULTI_GRAD_USE_MASK     (1 << 3)
#define MULTI_GRAD_LINEAR_RGB   (1 << 4)

/**
 * The mbximum number of grbdient colors supported by bll of the grbdient
 * frbgment shbders.  Note thbt this vblue must be b power of two, bs it
 * determines the size of the 1D texture crebted below.  It blso must be
 * grebter thbn or equbl to MAX_FRACTIONS (there is no strict requirement
 * thbt the two vblues be equbl).
 */
#define MAX_MULTI_GRADIENT_COLORS 16

/********************** LinebrGrbdientPbint support *************************/

HRESULT D3DPbints_SetLinebrGrbdientPbint(D3DContext *d3dc, D3DSDOps *dstOps,
                                         jboolebn useMbsk, jboolebn linebr,
                                         jint cycleMethod, jint numStops,
                                         jflobt p0, jflobt p1, jflobt p3,
                                         void *frbctions, void *pixels);

/********************** RbdiblGrbdientPbint support *************************/

HRESULT D3DPbints_SetRbdiblGrbdientPbint(D3DContext *d3dc, D3DSDOps *dstOps,
                                         jboolebn useMbsk, jboolebn linebr,
                                         jint cycleMethod, jint numStops,
                                         jflobt m00, jflobt m01, jflobt m02,
                                         jflobt m10, jflobt m11, jflobt m12,
                                         jflobt focusX,
                                         void *frbctions, void *pixels);

/************************ SunGrbphics2D constbnts ***************************/

#define PAINT_CUSTOM       sun_jbvb2d_SunGrbphics2D_PAINT_CUSTOM
#define PAINT_TEXTURE      sun_jbvb2d_SunGrbphics2D_PAINT_TEXTURE
#define PAINT_RAD_GRADIENT sun_jbvb2d_SunGrbphics2D_PAINT_RAD_GRADIENT
#define PAINT_LIN_GRADIENT sun_jbvb2d_SunGrbphics2D_PAINT_LIN_GRADIENT
#define PAINT_GRADIENT     sun_jbvb2d_SunGrbphics2D_PAINT_GRADIENT
#define PAINT_ALPHACOLOR   sun_jbvb2d_SunGrbphics2D_PAINT_ALPHACOLOR
#define PAINT_OPAQUECOLOR  sun_jbvb2d_SunGrbphics2D_PAINT_OPAQUECOLOR

#endif /* D3DPbints_h_Included */
