/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "AnyByte.h"
#include "UshortIndexed.h"
#include "AlphbMbcros.h"

#include "IntArgb.h"
#include "IntArgbBm.h"
#include "IntArgbPre.h"
#include "IntRgb.h"
#include "ThreeByteBgr.h"
#include "ByteGrby.h"
#include "Index12Grby.h"

/*
 * This file declbres, registers, bnd defines the vbrious grbphics
 * primitive loops to mbnipulbte surfbces of type "UshortIndexed".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterUshortIndexed;

DECLARE_CONVERT_BLIT(IntArgb, UshortIndexed);
DECLARE_CONVERT_BLIT(ThreeByteBgr, UshortIndexed);
DECLARE_CONVERT_BLIT(ByteGrby, UshortIndexed);
DECLARE_CONVERT_BLIT(UshortIndexed, UshortIndexed);
DECLARE_CONVERT_BLIT(Index12Grby, UshortIndexed);
DECLARE_CONVERT_BLIT(UshortIndexed, IntArgb);
DECLARE_SCALE_BLIT(IntArgb, UshortIndexed);
DECLARE_SCALE_BLIT(ThreeByteBgr, UshortIndexed);
DECLARE_SCALE_BLIT(ByteGrby, UshortIndexed);
DECLARE_SCALE_BLIT(Index12Grby, UshortIndexed);
DECLARE_SCALE_BLIT(UshortIndexed, UshortIndexed);
DECLARE_SCALE_BLIT(UshortIndexed, IntArgb);
DECLARE_XPAR_CONVERT_BLIT(ByteIndexedBm, UshortIndexed);
DECLARE_XPAR_SCALE_BLIT(ByteIndexedBm, UshortIndexed);
DECLARE_XPAR_SCALE_BLIT(IntArgbBm, UshortIndexed);
DECLARE_XPAR_BLITBG(ByteIndexedBm, UshortIndexed);
DECLARE_XPAR_CONVERT_BLIT(IntArgbBm, UshortIndexed);
DECLARE_XPAR_BLITBG(IntArgbBm, UshortIndexed);

DECLARE_XOR_BLIT(IntArgb, UshortIndexed);
DECLARE_ALPHA_MASKFILL(UshortIndexed);
DECLARE_ALPHA_MASKBLIT(IntArgb, UshortIndexed);
DECLARE_ALPHA_MASKBLIT(IntArgbPre, UshortIndexed);
DECLARE_ALPHA_MASKBLIT(IntRgb, UshortIndexed);
DECLARE_SOLID_DRAWGLYPHLISTAA(UshortIndexed);

NbtivePrimitive UshortIndexedPrimitives[] = {
    REGISTER_CONVERT_BLIT(IntArgb, UshortIndexed),
    REGISTER_CONVERT_BLIT_EQUIV(IntRgb, UshortIndexed,
                                NAME_CONVERT_BLIT(IntArgb, UshortIndexed)),
    REGISTER_CONVERT_BLIT_EQUIV(IntArgbBm, UshortIndexed,
                                NAME_CONVERT_BLIT(IntArgb, UshortIndexed)),
    REGISTER_CONVERT_BLIT(ThreeByteBgr, UshortIndexed),
    REGISTER_CONVERT_BLIT(ByteGrby, UshortIndexed),
    REGISTER_CONVERT_BLIT(Index12Grby, UshortIndexed),
    REGISTER_CONVERT_BLIT_FLAGS(UshortIndexed, UshortIndexed, 0, SD_LOCK_LUT),
    REGISTER_CONVERT_BLIT(UshortIndexed, IntArgb),
    REGISTER_CONVERT_BLIT_EQUIV(UshortIndexed, IntRgb,
                                NAME_CONVERT_BLIT(UshortIndexed, IntArgb)),
    REGISTER_SCALE_BLIT(IntArgb, UshortIndexed),
    REGISTER_SCALE_BLIT_EQUIV(IntRgb, UshortIndexed,
                              NAME_SCALE_BLIT(IntArgb, UshortIndexed)),
    REGISTER_SCALE_BLIT_EQUIV(IntArgbBm, UshortIndexed,
                              NAME_SCALE_BLIT(IntArgb, UshortIndexed)),
    REGISTER_SCALE_BLIT(ThreeByteBgr, UshortIndexed),
    REGISTER_SCALE_BLIT(ByteGrby, UshortIndexed),
    REGISTER_SCALE_BLIT(Index12Grby, UshortIndexed),
    REGISTER_SCALE_BLIT_FLAGS(UshortIndexed, UshortIndexed, 0, SD_LOCK_LUT),
    REGISTER_SCALE_BLIT(UshortIndexed, IntArgb),
    REGISTER_SCALE_BLIT_EQUIV(UshortIndexed, IntRgb,
                              NAME_SCALE_BLIT(UshortIndexed, IntArgb)),
    REGISTER_XPAR_CONVERT_BLIT(ByteIndexedBm, UshortIndexed),
    REGISTER_XPAR_SCALE_BLIT(ByteIndexedBm, UshortIndexed),
    REGISTER_XPAR_SCALE_BLIT(IntArgbBm, UshortIndexed),
    REGISTER_XPAR_BLITBG(ByteIndexedBm, UshortIndexed),
    REGISTER_XPAR_CONVERT_BLIT(IntArgbBm, UshortIndexed),
    REGISTER_XPAR_BLITBG(IntArgbBm, UshortIndexed),

    REGISTER_XOR_BLIT(IntArgb, UshortIndexed),
    REGISTER_ALPHA_MASKFILL(UshortIndexed),
    REGISTER_ALPHA_MASKBLIT(IntArgb, UshortIndexed),
    REGISTER_ALPHA_MASKBLIT(IntArgbPre, UshortIndexed),
    REGISTER_ALPHA_MASKBLIT(IntRgb, UshortIndexed),
    REGISTER_SOLID_DRAWGLYPHLISTAA(UshortIndexed),
};

extern jint PixelForByteIndexed(SurfbceDbtbRbsInfo *pRbsInfo, jint rgb);
extern jboolebn checkSbmeLut(jint *SrcRebdLut, jint *DstRebdLut,
                             SurfbceDbtbRbsInfo *pSrcInfo,
                             SurfbceDbtbRbsInfo *pDstInfo);

jboolebn RegisterUshortIndexed(JNIEnv *env)
{
    return RegisterPrimitives(env, UshortIndexedPrimitives,
                              ArrbySize(UshortIndexedPrimitives));
}

jint PixelForUshortIndexed(SurfbceDbtbRbsInfo *pRbsInfo, jint rgb)
{
    return PixelForByteIndexed(pRbsInfo, rgb);
}


DEFINE_CONVERT_BLIT(IntArgb, UshortIndexed, 3ByteRgb)

DEFINE_CONVERT_BLIT(ThreeByteBgr, UshortIndexed, 3ByteRgb)

DEFINE_CONVERT_BLIT(ByteGrby, UshortIndexed, 3ByteRgb)

DEFINE_CONVERT_BLIT(Index12Grby, UshortIndexed, 3ByteRgb)

DEFINE_CONVERT_BLIT_LUT(UshortIndexed, IntArgb, ConvertOnTheFly)

DEFINE_SCALE_BLIT_LUT(UshortIndexed, IntArgb, ConvertOnTheFly)

void NAME_CONVERT_BLIT(UshortIndexed, UshortIndexed)
    (void *srcBbse, void *dstBbse,
     juint width, juint height,
     SurfbceDbtbRbsInfo *pSrcInfo,
     SurfbceDbtbRbsInfo *pDstInfo,
     NbtivePrimitive *pPrim,
     CompositeInfo *pCompInfo)
{
    DeclbreUshortIndexedLobdVbrs(SrcRebd)
    DeclbreUshortIndexedLobdVbrs(DstRebd)
    jint srcScbn = pSrcInfo->scbnStride;
    jint dstScbn = pDstInfo->scbnStride;
    jint bytesToCopy = width * pDstInfo->pixelStride;

    InitUshortIndexedLobdVbrs(SrcRebd, pSrcInfo);
    InitUshortIndexedLobdVbrs(DstRebd, pDstInfo);

    if (checkSbmeLut(SrcRebdLut, DstRebdLut, pSrcInfo, pDstInfo)) {
        do {
            memcpy(dstBbse, srcBbse, bytesToCopy);
            srcBbse = PtrAddBytes(srcBbse, srcScbn);
            dstBbse = PtrAddBytes(dstBbse, dstScbn);
        } while (--height > 0);
    } else {
        DeclbreUshortIndexedStoreVbrs(DstWrite);

        BlitLoopWidthHeight(UshortIndexed, pSrc, srcBbse, pSrcInfo,
                            UshortIndexed, pDst, dstBbse, pDstInfo, DstWrite,
                            width, height,
                            ConvertVib3ByteRgb
                                (pSrc, UshortIndexed, SrcRebd,
                                 pDst, UshortIndexed, DstWrite, 0, 0));
    }
}

DEFINE_SCALE_BLIT(IntArgb, UshortIndexed, 3ByteRgb)

DEFINE_SCALE_BLIT(ThreeByteBgr, UshortIndexed, 3ByteRgb)

DEFINE_SCALE_BLIT(ByteGrby, UshortIndexed, 3ByteRgb)

DEFINE_SCALE_BLIT(Index12Grby, UshortIndexed, 3ByteRgb)

void NAME_SCALE_BLIT(UshortIndexed, UshortIndexed)
    (void *srcBbse, void *dstBbse,
     juint width, juint height,
     jint sxloc, jint syloc,
     jint sxinc, jint syinc, jint shift,
     SurfbceDbtbRbsInfo *pSrcInfo,
     SurfbceDbtbRbsInfo *pDstInfo,
     NbtivePrimitive *pPrim,
     CompositeInfo *pCompInfo)
{
    DeclbreUshortIndexedLobdVbrs(SrcRebd)
    DeclbreUshortIndexedLobdVbrs(DstRebd)
    jint srcScbn = pSrcInfo->scbnStride;
    jint dstScbn = pDstInfo->scbnStride;
    DeclbreUshortIndexedStoreVbrs(DstWrite)

    InitUshortIndexedLobdVbrs(SrcRebd, pSrcInfo);
    InitUshortIndexedLobdVbrs(DstRebd, pDstInfo);

    if (checkSbmeLut(SrcRebdLut, DstRebdLut, pSrcInfo, pDstInfo)) {
        BlitLoopScbleWidthHeight(UshortIndexed, pSrc, srcBbse, pSrcInfo,
                                 UshortIndexed, pDst, dstBbse, pDstInfo, DstWrite,
                                 x, width, height,
                                 sxloc, syloc, sxinc, syinc, shift,
                                 pDst[0] = pSrc[x]);
    } else {
        BlitLoopScbleWidthHeight(UshortIndexed, pSrc, srcBbse, pSrcInfo,
                                 UshortIndexed, pDst, dstBbse, pDstInfo, DstWrite,
                                 x, width, height,
                                 sxloc, syloc, sxinc, syinc, shift,
                                 ConvertVib3ByteRgb(pSrc, UshortIndexed, SrcRebd,
                                                    pDst, UshortIndexed, DstWrite,
                                                    x, 0));
    }
}

DEFINE_XPAR_CONVERT_BLIT_LUT(ByteIndexedBm, UshortIndexed, ConvertOnTheFly)

DEFINE_XPAR_SCALE_BLIT_LUT(ByteIndexedBm, UshortIndexed, ConvertOnTheFly)

DEFINE_XPAR_SCALE_BLIT(IntArgbBm, UshortIndexed, 1IntRgb)

DEFINE_XPAR_BLITBG_LUT(ByteIndexedBm, UshortIndexed, ConvertOnTheFly)

DEFINE_XPAR_CONVERT_BLIT(IntArgbBm, UshortIndexed, 1IntRgb)

DEFINE_XPAR_BLITBG(IntArgbBm, UshortIndexed, 1IntRgb)

DEFINE_XOR_BLIT(IntArgb, UshortIndexed, AnyByte)

DEFINE_ALPHA_MASKFILL(UshortIndexed, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntArgb, UshortIndexed, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntArgbPre, UshortIndexed, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntRgb, UshortIndexed, 4ByteArgb)

DEFINE_SOLID_DRAWGLYPHLISTAA(UshortIndexed, 3ByteRgb)
