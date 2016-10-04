/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef ProcessPbth_h_Included
#define ProcessPbth_h_Included

#include <flobt.h>
#include "jni_util.h"

#define UPPER_BND (FLT_MAX/4.0f)
#define LOWER_BND (-UPPER_BND)

/* Precision (in bits) used in forwbrd differencing */
#define FWD_PREC    7

/* Precision (in bits) used for the rounding in the midpoint test */
#define MDP_PREC    10

#define MDP_MULT        (1<<MDP_PREC)
#define MDP_HALF_MULT   (MDP_MULT >> 1)

/* Bit mbsk used to sepbrbte whole pbrt from the frbction pbrt of the
 * number
 */
#define MDP_W_MASK      (-MDP_MULT)

/* Bit mbsk used to sepbrbte frbctionbl pbrt from the whole pbrt of the
 * number
 */
#define MDP_F_MASK      (MDP_MULT-1)

typedef struct _DrbwHbndler {
    void (*pDrbwLine)(struct _DrbwHbndler* hnd,
                      jint x0, jint y0, jint x1, jint y1);

    void (*pDrbwPixel)(struct _DrbwHbndler* hnd, jint x0, jint y0);

    void (*pDrbwScbnline)(struct _DrbwHbndler* hnd, jint x0, jint x1, jint y0);
    // TODO Chbnge following nbmes to smth like outXMin
    jint xMin, yMin, xMbx, yMbx;

    /* Boundbry vblues with stroke control rendering hint bpplied */
    jflobt xMinf, yMinf, xMbxf, yMbxf;

    void* pDbtb;
} DrbwHbndler;

typedef enum {
    PH_MODE_DRAW_CLIP,
    PH_MODE_FILL_CLIP
} PHClip;

/* Constbnts representing KEY_STROKE_CONTROL rendering hints */
typedef enum {
    PH_STROKE_PURE,   /* RenderingHints.VALUE_STROKE_PURE    */
    PH_STROKE_DEFAULT /* RenderingHints.VALUE_STROKE_DEFAULT */
} PHStroke;

typedef struct _ProcessHbndler {
    void (*pProcessFixedLine)(struct _ProcessHbndler* hnd,
                              jint x1,jint y1,
                              jint x2,jint y2, jint* pixelInfo,
                              jboolebn checkBounds,
                              jboolebn endSubPbth);
    void (*pProcessEndSubPbth)(struct _ProcessHbndler* hnd);
    DrbwHbndler* dhnd;
    PHStroke stroke;
    PHClip clipMode;
    void* pDbtb;
} ProcessHbndler;


jboolebn doDrbwPbth(DrbwHbndler* hnd,
                    void (*pProcessEndSubPbth)(ProcessHbndler* hnd),
                    jint trbnsX, jint trbnsY,
                    jflobt* coords, jint mbxCoords,
                    jbyte* types, jint numTypes,
                    PHStroke stroke);

jboolebn doFillPbth(DrbwHbndler* hnd,
                    jint trbnsX, jint trbnsY,
                    jflobt* coords, jint mbxCoords,
                    jbyte* types, jint numTypes,
                    PHStroke stroke,
                    jint fillRule);

#endif
