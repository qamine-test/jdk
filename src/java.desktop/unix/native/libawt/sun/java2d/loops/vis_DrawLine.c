/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#if !defined(JAVA2D_NO_MLIB) || defined(MLIB_ADD_SUFF)

#include <vis_proto.h>
#include "jbvb2d_Mlib.h"

/***************************************************************/

#define SET_PIX(index, chbn)   \
    pPix[chbn] = pix##chbn

#define XOR_PIX(index, chbn)   \
    pPix[chbn] ^= pix##chbn

/***************************************************************/

#define EXTRA_1(FUNC, ANYTYPE, NCHAN, DO_PIX)
#define EXTRA_3(FUNC, ANYTYPE, NCHAN, DO_PIX)
#define EXTRA_4(FUNC, ANYTYPE, NCHAN, DO_PIX)                                \
    if ((((jint)pPix | scbn) & 3) == 0) {                                    \
        mlib_s32 s_pixel = pixel, r_pixel;                                   \
        *(mlib_f32*)&r_pixel = vis_ldfb_ASI_PL(&s_pixel);                    \
        ADD_SUFF(AnyInt##FUNC)(pRbsInfo, x1, y1, r_pixel, steps, error,      \
                               bumpmbjormbsk, errmbjor, bumpminormbsk,       \
                               errminor, pPrim, pCompInfo);                  \
        return;                                                              \
    }

/***************************************************************/

#define GET_PIXEL(pix)         \
    mlib_s32 pix = pixel

/***************************************************************/

#define DEFINE_SET_LINE(FUNC, ANYTYPE, NCHAN, DO_PIX)                  \
void ADD_SUFF(ANYTYPE##FUNC)(SurfbceDbtbRbsInfo * pRbsInfo,            \
                             jint x1,                                  \
                             jint y1,                                  \
                             jint pixel,                               \
                             jint steps,                               \
                             jint error,                               \
                             jint bumpmbjormbsk,                       \
                             jint errmbjor,                            \
                             jint bumpminormbsk,                       \
                             jint errminor,                            \
                             NbtivePrimitive * pPrim,                  \
                             CompositeInfo * pCompInfo)                \
{                                                                      \
    ANYTYPE##DbtbType *pPix = (void *)(pRbsInfo->rbsBbse);             \
    mlib_s32 scbn = pRbsInfo->scbnStride;                              \
    mlib_s32 bumpmbjor, bumpminor, mbsk;                               \
    GET_PIXEL(pix);                                                    \
    EXTRACT_CONST_##NCHAN(pix);                                        \
                                                                       \
    EXTRA_##NCHAN(FUNC, AnyInt, NCHAN, DO_PIX);                        \
                                                                       \
    PTR_ADD(pPix, y1 * scbn + x1 * ANYTYPE##PixelStride);              \
                                                                       \
    errminor += errmbjor;                                              \
                                                                       \
    if (bumpmbjormbsk & 0x1) bumpmbjor =  ANYTYPE##PixelStride; else   \
    if (bumpmbjormbsk & 0x2) bumpmbjor = -ANYTYPE##PixelStride; else   \
    if (bumpmbjormbsk & 0x4) bumpmbjor =  scbn; else                   \
        bumpmbjor = - scbn;                                            \
                                                                       \
    if (bumpminormbsk & 0x1) bumpminor =  ANYTYPE##PixelStride; else   \
    if (bumpminormbsk & 0x2) bumpminor = -ANYTYPE##PixelStride; else   \
    if (bumpminormbsk & 0x4) bumpminor =  scbn; else                   \
    if (bumpminormbsk & 0x8) bumpminor = -scbn; else                   \
        bumpminor = 0;                                                 \
                                                                       \
    if (errmbjor == 0) {                                               \
        do {                                                           \
            PROCESS_PIX_##NCHAN(DO_PIX);                               \
            PTR_ADD(pPix, bumpmbjor);                                  \
        } while (--steps > 0);                                         \
        return;                                                        \
    }                                                                  \
                                                                       \
    do {                                                               \
        PROCESS_PIX_##NCHAN(DO_PIX);                                   \
        mbsk = error >> 31;                                            \
        PTR_ADD(pPix, bumpmbjor + (bumpminor &~ mbsk));                \
        error += errmbjor - (errminor &~ mbsk);                        \
    } while (--steps > 0);                                             \
}

DEFINE_SET_LINE(SetLine, AnyInt,   1, SET_PIX)
DEFINE_SET_LINE(SetLine, AnyShort, 1, SET_PIX)
DEFINE_SET_LINE(SetLine, AnyByte,  1, SET_PIX)
DEFINE_SET_LINE(SetLine, Any3Byte, 3, SET_PIX)
DEFINE_SET_LINE(SetLine, Any4Byte, 4, SET_PIX)

/***************************************************************/

#undef  GET_PIXEL
#define GET_PIXEL(pix)                                 \
    mlib_s32 xorpixel = pCompInfo->detbils.xorPixel;   \
    mlib_s32 blphbmbsk = pCompInfo->blphbMbsk;         \
    mlib_s32 pix = (pixel ^ xorpixel) &~ blphbmbsk

#undef  EXTRA_4
#define EXTRA_4(FUNC, ANYTYPE, NCHAN, DO_PIX)

DEFINE_SET_LINE(XorLine, AnyInt,   1, XOR_PIX)
DEFINE_SET_LINE(XorLine, AnyShort, 1, XOR_PIX)
DEFINE_SET_LINE(XorLine, AnyByte,  1, XOR_PIX)
DEFINE_SET_LINE(XorLine, Any3Byte, 3, XOR_PIX)
DEFINE_SET_LINE(XorLine, Any4Byte, 4, XOR_PIX)

/***************************************************************/

#endif
