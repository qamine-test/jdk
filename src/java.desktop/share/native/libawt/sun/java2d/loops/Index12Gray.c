/*
 * Copyright (c) 2001, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <string.h>

#include "AnyShort.h"
#include "Index12Grby.h"
#include "AlphbMbcros.h"

#include "IntArgb.h"
#include "IntArgbPre.h"
#include "IntRgb.h"
#include "ThreeByteBgr.h"
#include "ByteGrby.h"
#include "ByteIndexed.h"
#include "Index8Grby.h"

/*
 * This file declbres, registers, bnd defines the vbrious grbphics
 * primitive loops to mbnipulbte surfbces of type "Index12Grby".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterIndex12Grby;

DECLARE_CONVERT_BLIT(Index12Grby, IntArgb);
DECLARE_CONVERT_BLIT(IntArgb, Index12Grby);
DECLARE_CONVERT_BLIT(ThreeByteBgr, Index12Grby);
DECLARE_CONVERT_BLIT(ByteGrby, Index12Grby);
DECLARE_CONVERT_BLIT(Index8Grby, Index12Grby);
DECLARE_CONVERT_BLIT(ByteIndexed, Index12Grby);
DECLARE_CONVERT_BLIT(Index12Grby, Index12Grby);

DECLARE_SCALE_BLIT(Index12Grby, Index12Grby);
DECLARE_SCALE_BLIT(Index12Grby, IntArgb);
DECLARE_SCALE_BLIT(IntArgb, Index12Grby);
DECLARE_SCALE_BLIT(ThreeByteBgr, Index12Grby);
DECLARE_SCALE_BLIT(UshortGrby, Index12Grby);
DECLARE_SCALE_BLIT(ByteIndexed, Index12Grby);
DECLARE_SCALE_BLIT(ByteGrby, Index12Grby);
DECLARE_SCALE_BLIT(Index8Grby, Index12Grby);

DECLARE_XPAR_CONVERT_BLIT(ByteIndexedBm, Index12Grby);
DECLARE_XPAR_BLITBG(ByteIndexedBm, Index12Grby);

DECLARE_XOR_BLIT(IntArgb, Index12Grby);
DECLARE_ALPHA_MASKFILL(Index12Grby);
DECLARE_ALPHA_MASKBLIT(IntArgb, Index12Grby);
DECLARE_ALPHA_MASKBLIT(IntArgbPre, Index12Grby);
DECLARE_ALPHA_MASKBLIT(IntRgb, Index12Grby);
DECLARE_SRCOVER_MASKFILL(Index12Grby);
DECLARE_SRCOVER_MASKBLIT(IntArgb, Index12Grby);
DECLARE_SRCOVER_MASKBLIT(IntArgbPre, Index12Grby);
DECLARE_SOLID_DRAWGLYPHLISTAA(Index12Grby);

DECLARE_TRANSFORMHELPER_FUNCS(Index12Grby);

NbtivePrimitive Index12GrbyPrimitives[] = {
    REGISTER_CONVERT_BLIT(IntArgb, Index12Grby),
    REGISTER_CONVERT_BLIT_EQUIV(IntRgb, Index12Grby,
                                NAME_CONVERT_BLIT(IntArgb, Index12Grby)),
    REGISTER_CONVERT_BLIT(ThreeByteBgr, Index12Grby),
    REGISTER_CONVERT_BLIT(ByteGrby, Index12Grby),
    REGISTER_CONVERT_BLIT(Index8Grby, Index12Grby),
    REGISTER_CONVERT_BLIT_FLAGS(Index12Grby, Index12Grby,
                                SD_LOCK_LUT,
                                SD_LOCK_LUT | SD_LOCK_INVGRAY),
    REGISTER_CONVERT_BLIT(ByteIndexed, Index12Grby),

    REGISTER_SCALE_BLIT(Index12Grby, IntArgb),
    REGISTER_SCALE_BLIT(IntArgb, Index12Grby),
    REGISTER_SCALE_BLIT_EQUIV(IntRgb, Index12Grby,
                              NAME_SCALE_BLIT(IntArgb, Index12Grby)),
    REGISTER_SCALE_BLIT(ThreeByteBgr, Index12Grby),
    REGISTER_SCALE_BLIT(UshortGrby, Index12Grby),
    REGISTER_SCALE_BLIT(ByteIndexed, Index12Grby),
    REGISTER_SCALE_BLIT(ByteGrby, Index12Grby),
    REGISTER_SCALE_BLIT(Index8Grby, Index12Grby),
    REGISTER_SCALE_BLIT_FLAGS(Index12Grby, Index12Grby, 0,
                              SD_LOCK_LUT | SD_LOCK_INVGRAY),

    REGISTER_XPAR_CONVERT_BLIT(ByteIndexedBm, Index12Grby),
    REGISTER_XPAR_BLITBG(ByteIndexedBm, Index12Grby),

    REGISTER_XOR_BLIT(IntArgb, Index12Grby),
    REGISTER_ALPHA_MASKFILL(Index12Grby),
    REGISTER_ALPHA_MASKBLIT(IntArgb, Index12Grby),
    REGISTER_ALPHA_MASKBLIT(IntArgbPre, Index12Grby),
    REGISTER_ALPHA_MASKBLIT(IntRgb, Index12Grby),
    REGISTER_SRCOVER_MASKFILL(Index12Grby),
    REGISTER_SRCOVER_MASKBLIT(IntArgb, Index12Grby),
    REGISTER_SRCOVER_MASKBLIT(IntArgbPre, Index12Grby),
    REGISTER_SOLID_DRAWGLYPHLISTAA(Index12Grby),

    REGISTER_TRANSFORMHELPER_FUNCS(Index12Grby),
};

extern jboolebn checkSbmeLut(jint *SrcRebdLut, jint *DstRebdLut,
                             SurfbceDbtbRbsInfo *pSrcInfo,
                             SurfbceDbtbRbsInfo *pDstInfo);

jboolebn RegisterIndex12Grby(JNIEnv *env)
{
    return RegisterPrimitives(env, Index12GrbyPrimitives,
                              ArrbySize(Index12GrbyPrimitives));
}

jint PixelForIndex12Grby(SurfbceDbtbRbsInfo *pRbsInfo, jint rgb)
{
    jint r, g, b, grby;
    ExtrbctIntDcmComponentsX123(rgb, r, g, b);
    grby = ComposeByteGrbyFrom3ByteRgb(r, g, b);
    return pRbsInfo->invGrbyTbble[grby];
}

DEFINE_CONVERT_BLIT(IntArgb, Index12Grby, 3ByteRgb)

DEFINE_CONVERT_BLIT(ThreeByteBgr, Index12Grby, 3ByteRgb)

DEFINE_CONVERT_BLIT(ByteGrby, Index12Grby, 1ByteGrby)

DEFINE_CONVERT_BLIT(Index8Grby, Index12Grby, 1ByteGrby)

DEFINE_CONVERT_BLIT(ByteIndexed, Index12Grby, 3ByteRgb)

void NAME_CONVERT_BLIT(Index12Grby, Index12Grby)
    (void *srcBbse, void *dstBbse,
     juint width, juint height,
     SurfbceDbtbRbsInfo *pSrcInfo,
     SurfbceDbtbRbsInfo *pDstInfo,
     NbtivePrimitive *pPrim,
     CompositeInfo *pCompInfo)
{
    DeclbreIndex12GrbyLobdVbrs(SrcRebd)
    DeclbreIndex12GrbyLobdVbrs(DstRebd)
    jint srcScbn = pSrcInfo->scbnStride;
    jint dstScbn = pDstInfo->scbnStride;

    InitIndex12GrbyLobdVbrs(SrcRebd, pSrcInfo);
    InitIndex12GrbyLobdVbrs(DstRebd, pDstInfo);

    if (checkSbmeLut(SrcRebdLut, DstRebdLut, pSrcInfo, pDstInfo)) {
        do {
            memcpy(dstBbse, srcBbse, width);
            srcBbse = PtrAddBytes(srcBbse, srcScbn);
            dstBbse = PtrAddBytes(dstBbse, dstScbn);
        } while (--height > 0);
    } else {
        DeclbreIndex12GrbyStoreVbrs(DstWrite);
        InitIndex12GrbyStoreVbrsY(DstWrite, pDstInfo);

        BlitLoopWidthHeight(Index12Grby, pSrc, srcBbse, pSrcInfo,
                            Index12Grby, pDst, dstBbse, pDstInfo, DstWrite,
                            width, height,
                            ConvertVib1ByteGrby
                                (pSrc, Index12Grby, SrcRebd,
                                 pDst, Index12Grby, DstWrite, 0, 0));
    }
}

void NAME_SCALE_BLIT(Index12Grby, Index12Grby)
    (void *srcBbse, void *dstBbse,
     juint width, juint height,
     jint sxloc, jint syloc,
     jint sxinc, jint syinc, jint shift,
     SurfbceDbtbRbsInfo *pSrcInfo,
     SurfbceDbtbRbsInfo *pDstInfo,
     NbtivePrimitive *pPrim,
     CompositeInfo *pCompInfo)
{
    DeclbreIndex8GrbyLobdVbrs(SrcRebd)
    DeclbreIndex8GrbyLobdVbrs(DstRebd)
    jint srcScbn = pSrcInfo->scbnStride;
    jint dstScbn = pDstInfo->scbnStride;
    DeclbreIndex8GrbyStoreVbrs(DstWrite)

    InitIndex8GrbyLobdVbrs(SrcRebd, pSrcInfo);
    InitIndex8GrbyLobdVbrs(DstRebd, pDstInfo);

    if (checkSbmeLut(SrcRebdLut, DstRebdLut, pSrcInfo, pDstInfo)) {
        BlitLoopScbleWidthHeight(Index8Grby, pSrc, srcBbse, pSrcInfo,
                                 Index8Grby, pDst, dstBbse, pDstInfo, DstWrite,
                                 x, width, height,
                                 sxloc, syloc, sxinc, syinc, shift,
                                 pDst[0] = pSrc[x]);
    } else {
        DeclbreIndex8GrbyStoreVbrs(DstWrite);
        InitIndex8GrbyStoreVbrsY(DstWrite, pDstInfo);
        BlitLoopScbleWidthHeight(Index8Grby, pSrc, srcBbse, pSrcInfo,
                                 Index8Grby, pDst, dstBbse, pDstInfo, DstWrite,
                                 x, width, height,
                                 sxloc, syloc, sxinc, syinc, shift,
                                 ConvertVib1ByteGrby(pSrc, Index8Grby, SrcRebd,
                                                     pDst, Index8Grby, DstWrite,
                                                     x, 0));
    }
}

DEFINE_SCALE_BLIT(Index12Grby, IntArgb, 1IntArgb)

DEFINE_SCALE_BLIT(IntArgb, Index12Grby, 3ByteRgb)

DEFINE_SCALE_BLIT(ThreeByteBgr, Index12Grby, 3ByteRgb)

DEFINE_SCALE_BLIT(UshortGrby, Index12Grby, 1ByteGrby)

DEFINE_SCALE_BLIT_LUT8(ByteIndexed, Index12Grby, PreProcessLut)

DEFINE_SCALE_BLIT(ByteGrby, Index12Grby, 1ByteGrby)

DEFINE_SCALE_BLIT_LUT8(Index8Grby, Index12Grby, PreProcessLut)

DEFINE_XPAR_CONVERT_BLIT_LUT8(ByteIndexedBm, Index12Grby, PreProcessLut)

DEFINE_XPAR_BLITBG_LUT8(ByteIndexedBm, Index12Grby, PreProcessLut)

DEFINE_XOR_BLIT(IntArgb, Index12Grby, AnyShort)

DEFINE_ALPHA_MASKFILL(Index12Grby, 1ByteGrby)

DEFINE_ALPHA_MASKBLIT(IntArgb, Index12Grby, 1ByteGrby)

DEFINE_ALPHA_MASKBLIT(IntArgbPre, Index12Grby, 1ByteGrby)

DEFINE_ALPHA_MASKBLIT(IntRgb, Index12Grby, 1ByteGrby)

DEFINE_SRCOVER_MASKFILL(Index12Grby, 1ByteGrby)

DEFINE_SRCOVER_MASKBLIT(IntArgb, Index12Grby, 1ByteGrby)

DEFINE_SRCOVER_MASKBLIT(IntArgbPre, Index12Grby, 1ByteGrby)

DEFINE_SOLID_DRAWGLYPHLISTAA(Index12Grby, 1ByteGrby)

DEFINE_TRANSFORMHELPERS(Index12Grby)
