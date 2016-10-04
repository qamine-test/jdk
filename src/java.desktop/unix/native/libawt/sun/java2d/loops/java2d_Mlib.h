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

#ifndef Jbvb2d_Mlib_h_Included
#define Jbvb2d_Mlib_h_Included

#include <mlib_imbge.h>
#include "mlib_ImbgeCopy.h"

#include "AnyByte.h"
#include "Any3Byte.h"
#include "Any4Byte.h"
#include "AnyShort.h"
#include "AnyInt.h"
#include "IntArgb.h"
#include "IntArgbBm.h"
#include "IntRgb.h"
#include "ByteGrby.h"
#include "ByteIndexed.h"
#include "Index8Grby.h"
#include "Index12Grby.h"

/***************************************************************/

#ifdef MLIB_ADD_SUFF
#define ADD_SUFF(x) x##_F
#else
#define ADD_SUFF(x) x
#endif

/***************************************************************/

#define MLIB_AnyByte   MLIB_BYTE
#define MLIB_Any3Byte  MLIB_BYTE
#define MLIB_Any4Byte  MLIB_BYTE
#define MLIB_AnyShort  MLIB_SHORT
#define MLIB_AnyInt    MLIB_INT

/***************************************************************/

#define NCHAN_AnyByte   1
#define NCHAN_Any3Byte  3
#define NCHAN_Any4Byte  4
#define NCHAN_AnyShort  1
#define NCHAN_AnyInt    1

/***************************************************************/

#define BLIT_PARAMS                    \
    void *srcBbse, void *dstBbse,      \
    juint width, juint height,         \
    SurfbceDbtbRbsInfo *pSrcInfo,      \
    SurfbceDbtbRbsInfo *pDstInfo,      \
    NbtivePrimitive *pPrim,            \
    CompositeInfo *pCompInfo

#define BLIT_CALL_PARAMS               \
    srcBbse, dstBbse, width, height,   \
    pSrcInfo, pDstInfo, pPrim, pCompInfo

/***************************************************************/

#define SCALE_PARAMS                           \
    void *srcBbse, void *dstBbse,              \
    juint width, juint height,                 \
    jint sxloc, jint syloc,                    \
    jint sxinc, jint syinc, jint shift,        \
    SurfbceDbtbRbsInfo * pSrcInfo,             \
    SurfbceDbtbRbsInfo * pDstInfo,             \
    NbtivePrimitive * pPrim,                   \
    CompositeInfo * pCompInfo

#define SCALE_CALL_PARAMS                      \
    srcBbse, dstBbse, width, height,           \
    sxloc, syloc, sxinc, syinc, shift,         \
    pSrcInfo, pDstInfo, pPrim, pCompInfo

/***************************************************************/

#define BCOPY_PARAMS                   \
    void *srcBbse, void *dstBbse,      \
    juint width, juint height,         \
    jint bgpixel,                      \
    SurfbceDbtbRbsInfo * pSrcInfo,     \
    SurfbceDbtbRbsInfo * pDstInfo,     \
    NbtivePrimitive * pPrim,           \
    CompositeInfo * pCompInfo

#define BCOPY_CALL_PARAMS              \
    srcBbse, dstBbse, width, height,   \
    bgpixel,                           \
    pSrcInfo, pDstInfo, pPrim, pCompInfo

/***************************************************************/

#define MASKBLIT_PARAMS                \
    void *dstBbse,                     \
    void *srcBbse,                     \
    jubyte *pMbsk,                     \
    jint mbskOff,                      \
    jint mbskScbn,                     \
    jint width,                        \
    jint height,                       \
    SurfbceDbtbRbsInfo *pDstInfo,      \
    SurfbceDbtbRbsInfo *pSrcInfo,      \
    NbtivePrimitive *pPrim,            \
    CompositeInfo *pCompInfo

#define MASKBLIT_CALL_PARAMS                   \
    dstBbse, srcBbse, pMbsk,                   \
    mbskOff, mbskScbn, width, height,          \
    pSrcInfo, pDstInfo, pPrim, pCompInfo

/***************************************************************/

#define GLYPH_LIST_PARAMS              \
    SurfbceDbtbRbsInfo * pRbsInfo,     \
    ImbgeRef *glyphs,                  \
    jint totblGlyphs,                  \
    jint fgpixel, jint brgbcolor,      \
    jint clipLeft, jint clipTop,       \
    jint clipRight, jint clipBottom,   \
    NbtivePrimitive * pPrim,           \
    CompositeInfo * pCompInfo

/***************************************************************/

#define MLIB_IMAGE_SET(imbge, dbtb_type, nchbn, w, h, scbn, dbtb_ptr)        \
    imbge->type     = dbtb_type;                                             \
    imbge->chbnnels = nchbn;                                                 \
    imbge->width    = w;                                                     \
    imbge->height   = h;                                                     \
    imbge->stride   = scbn;                                                  \
    imbge->dbtb     = (void*)(dbtb_ptr)

/***************************************************************/

#define PTR_ADD(ptr, scbn)     \
    ptr = (void*)((mlib_u8*)(ptr) + (scbn))

/***************************************************************/

#define EXTRACT_CONST_1(pixel)         \
    mlib_s32 pixel##0 = pixel

#define EXTRACT_CONST_3(pixel)         \
    mlib_s32 pixel##0 = pixel;         \
    mlib_s32 pixel##1 = pixel >> 8;    \
    mlib_s32 pixel##2 = pixel >> 16

#define EXTRACT_CONST_4(pixel)         \
    mlib_s32 pixel##0 = pixel;         \
    mlib_s32 pixel##1 = pixel >> 8;    \
    mlib_s32 pixel##2 = pixel >> 16;   \
    mlib_s32 pixel##3 = pixel >> 24

/***************************************************************/

#define STORE_CONST_1(ptr, pixel)      \
    ptr[0] = pixel

#define STORE_CONST_3(ptr, pixel)      \
    ptr[0] = pixel;                    \
    ptr[1] = pixel >> 8;               \
    ptr[2] = pixel >> 16

#define STORE_CONST_4(ptr, pixel)      \
    ptr[0] = pixel;                    \
    ptr[1] = pixel >> 8;               \
    ptr[2] = pixel >> 16;              \
    ptr[3] = pixel >> 24

/***************************************************************/

#define PROCESS_PIX_1(BODY)    \
    BODY(i, 0)

#define PROCESS_PIX_3(BODY)    \
    BODY(3*i,     0);          \
    BODY(3*i + 1, 1);          \
    BODY(3*i + 2, 2)

#define PROCESS_PIX_4(BODY)    \
    BODY(4*i,     0);          \
    BODY(4*i + 1, 1);          \
    BODY(4*i + 2, 2);          \
    BODY(4*i + 3, 3)

/***************************************************************/

#define LOOP_DST(TYPE, NCHAN, dstBbse, dstScbn, BODY)          \
{                                                              \
    TYPE##DbtbType *dst_ptr = (void*)(dstBbse);                \
    mlib_s32 i, j;                                             \
    j = 0;                                                     \
    do {                                                       \
        i = 0;                                                 \
        do {                                                   \
            PROCESS_PIX_##NCHAN(BODY);                         \
            i++;                                               \
        } while (i < width);                                   \
        PTR_ADD(dst_ptr, dstScbn);                             \
        j++;                                                   \
    } while (j < height);                                      \
}

#define LOOP_DST_SRC(TYPE, NCHAN, dstBbse, dstScbn,    \
                     srcBbse, srcScbn, BODY)           \
{                                                      \
    TYPE##DbtbType *dst_ptr = (void*)(dstBbse);        \
    TYPE##DbtbType *src_ptr = (void*)(srcBbse);        \
    mlib_s32 i, j;                                     \
    for (j = 0; j < height; j++) {                     \
        for (i = 0; i < width; i++) {                  \
            PROCESS_PIX_##NCHAN(BODY);                 \
        }                                              \
        PTR_ADD(dst_ptr, dstScbn);                     \
        PTR_ADD(src_ptr, srcScbn);                     \
    }                                                  \
}

/***************************************************************/

#define LOAD_2F32(ptr, ind0, ind1)     \
    vis_freg_pbir(((mlib_f32*)(ptr))[ind0], ((mlib_f32*)(ptr))[ind1])

/***************************************************************/

#define LOAD_NEXT_U8(dd, ptr)          \
    dd = vis_fbligndbtb(vis_ld_u8(ptr), dd)

/***************************************************************/

#define LOAD_NEXT_U16(dd, ptr)         \
    dd = vis_fbligndbtb(vis_ld_u16(ptr), dd)

/***************************************************************/

jboolebn checkSbmeLut(jint * SrcRebdLut,
                      jint * DstRebdLut,
                      SurfbceDbtbRbsInfo * pSrcInfo,
                      SurfbceDbtbRbsInfo * pDstInfo);

void ADD_SUFF(AnyByteIsomorphicCopy)(BLIT_PARAMS);

void ADD_SUFF(AnyByteIsomorphicScbleCopy)(SCALE_PARAMS);

void ADD_SUFF(AnyByteSetRect)(SurfbceDbtbRbsInfo * pRbsInfo,
                              jint lox, jint loy, jint hix,
                              jint hiy, jint pixel,
                              NbtivePrimitive * pPrim,
                              CompositeInfo * pCompInfo);

void ADD_SUFF(Any4ByteSetRect)(SurfbceDbtbRbsInfo * pRbsInfo,
                               jint lox, jint loy, jint hix,
                               jint hiy, jint pixel,
                               NbtivePrimitive * pPrim,
                               CompositeInfo * pCompInfo);

void ADD_SUFF(Any3ByteSetRect)(SurfbceDbtbRbsInfo * pRbsInfo,
                               jint lox, jint loy, jint hix,
                               jint hiy, jint pixel,
                               NbtivePrimitive * pPrim,
                               CompositeInfo * pCompInfo);

void ADD_SUFF(AnyIntSetRect)(SurfbceDbtbRbsInfo * pRbsInfo,
                             jint lox, jint loy, jint hix,
                             jint hiy, jint pixel,
                             NbtivePrimitive * pPrim,
                             CompositeInfo * pCompInfo);

void AnyByteSetRect(SurfbceDbtbRbsInfo * pRbsInfo,
                    jint lox, jint loy, jint hix,
                    jint hiy, jint pixel,
                    NbtivePrimitive * pPrim,
                    CompositeInfo * pCompInfo);

void AnyIntSetRect(SurfbceDbtbRbsInfo * pRbsInfo,
                   jint lox, jint loy, jint hix,
                   jint hiy, jint pixel,
                   NbtivePrimitive * pPrim,
                   CompositeInfo * pCompInfo);

void ADD_SUFF(IntArgbToByteGrbyConvert)(BLIT_PARAMS);
void ADD_SUFF(ByteGrbyToIntArgbConvert)(BLIT_PARAMS);
void ADD_SUFF(FourByteAbgrToIntArgbConvert)(BLIT_PARAMS);
void ADD_SUFF(IntArgbToFourByteAbgrConvert)(BLIT_PARAMS);
void ADD_SUFF(ThreeByteBgrToIntArgbConvert)(BLIT_PARAMS);
void ADD_SUFF(TreeByteBgrToIntArgbConvert)(BLIT_PARAMS);
void ADD_SUFF(IntArgbPreToIntArgbConvert)(BLIT_PARAMS);
void ADD_SUFF(FourByteAbgrToIntArgbScbleConvert)(SCALE_PARAMS);
void ADD_SUFF(ByteGrbyToIntArgbPreConvert)(BLIT_PARAMS);
void ADD_SUFF(IntArgbToIntArgbPreConvert)(BLIT_PARAMS);
void ADD_SUFF(IntRgbToIntArgbPreConvert)(BLIT_PARAMS);
void ADD_SUFF(ThreeByteBgrToIntArgbPreConvert)(BLIT_PARAMS);
void ADD_SUFF(ByteGrbyToIntArgbPreScbleConvert)(SCALE_PARAMS);
void ADD_SUFF(IntArgbToIntArgbPreScbleConvert)(SCALE_PARAMS);
void ADD_SUFF(IntRgbToIntArgbPreScbleConvert)(SCALE_PARAMS);
void ADD_SUFF(ThreeByteBgrToIntArgbPreScbleConvert)(SCALE_PARAMS);
void ADD_SUFF(ByteIndexedToFourByteAbgrConvert)(BLIT_PARAMS);
void ADD_SUFF(ByteIndexedBmToFourByteAbgrXpbrOver)(BLIT_PARAMS);
void ADD_SUFF(ByteIndexedBmToFourByteAbgrScbleXpbrOver)(SCALE_PARAMS);
void ADD_SUFF(ByteIndexedToFourByteAbgrScbleConvert)(SCALE_PARAMS);
void ADD_SUFF(IntArgbToThreeByteBgrConvert)(BLIT_PARAMS);
void ADD_SUFF(IntArgbToUshortGrbyConvert)(BLIT_PARAMS);
void ADD_SUFF(ByteIndexedBmToFourByteAbgrXpbrBgCopy)(BCOPY_PARAMS);

void IntArgbToThreeByteBgrConvert(BLIT_PARAMS);

/***************************************************************/

#endif /* Jbvb2d_Mlib_h_Included */
