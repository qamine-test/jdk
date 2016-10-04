/*
 * Copyright (c) 2000, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "ByteIndexed.h"
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
 * primitive loops to mbnipulbte surfbces of type "ByteIndexed".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterByteIndexed;

DECLARE_CONVERT_BLIT(IntArgb, ByteIndexed);
DECLARE_CONVERT_BLIT(ThreeByteBgr, ByteIndexed);
DECLARE_CONVERT_BLIT(ByteGrby, ByteIndexed);
DECLARE_CONVERT_BLIT(ByteIndexed, ByteIndexed);
DECLARE_CONVERT_BLIT(Index12Grby, ByteIndexed);
DECLARE_SCALE_BLIT(IntArgb, ByteIndexed);
DECLARE_SCALE_BLIT(ThreeByteBgr, ByteIndexed);
DECLARE_SCALE_BLIT(ByteGrby, ByteIndexed);
DECLARE_SCALE_BLIT(Index12Grby, ByteIndexed);
DECLARE_SCALE_BLIT(ByteIndexed, ByteIndexed);
DECLARE_XPAR_CONVERT_BLIT(ByteIndexedBm, ByteIndexed);
DECLARE_XPAR_SCALE_BLIT(ByteIndexedBm, ByteIndexed);
DECLARE_XPAR_SCALE_BLIT(IntArgbBm, ByteIndexed);
DECLARE_XPAR_BLITBG(ByteIndexedBm, ByteIndexed);
DECLARE_XPAR_CONVERT_BLIT(IntArgbBm, ByteIndexed);
DECLARE_XPAR_BLITBG(IntArgbBm, ByteIndexed);

DECLARE_XOR_BLIT(IntArgb, ByteIndexed);
DECLARE_ALPHA_MASKFILL(ByteIndexed);
DECLARE_ALPHA_MASKBLIT(IntArgb, ByteIndexed);
DECLARE_ALPHA_MASKBLIT(IntArgbPre, ByteIndexed);
DECLARE_ALPHA_MASKBLIT(IntRgb, ByteIndexed);
DECLARE_SOLID_DRAWGLYPHLISTAA(ByteIndexed);

DECLARE_TRANSFORMHELPER_FUNCS(ByteIndexed);
DECLARE_TRANSFORMHELPER_FUNCS(ByteIndexedBm);

NbtivePrimitive ByteIndexedPrimitives[] = {
    REGISTER_CONVERT_BLIT(IntArgb, ByteIndexed),
    REGISTER_CONVERT_BLIT_EQUIV(IntRgb, ByteIndexed,
                                NAME_CONVERT_BLIT(IntArgb, ByteIndexed)),
    REGISTER_CONVERT_BLIT_EQUIV(IntArgbBm, ByteIndexed,
                                NAME_CONVERT_BLIT(IntArgb, ByteIndexed)),
    REGISTER_CONVERT_BLIT(ThreeByteBgr, ByteIndexed),
    REGISTER_CONVERT_BLIT(ByteGrby, ByteIndexed),
    REGISTER_CONVERT_BLIT(Index12Grby, ByteIndexed),
    REGISTER_CONVERT_BLIT_FLAGS(ByteIndexed, ByteIndexed, 0, SD_LOCK_LUT),
    REGISTER_SCALE_BLIT(IntArgb, ByteIndexed),
    REGISTER_SCALE_BLIT_EQUIV(IntRgb, ByteIndexed,
                              NAME_SCALE_BLIT(IntArgb, ByteIndexed)),
    REGISTER_SCALE_BLIT_EQUIV(IntArgbBm, ByteIndexed,
                              NAME_SCALE_BLIT(IntArgb, ByteIndexed)),
    REGISTER_SCALE_BLIT(ThreeByteBgr, ByteIndexed),
    REGISTER_SCALE_BLIT(ByteGrby, ByteIndexed),
    REGISTER_SCALE_BLIT(Index12Grby, ByteIndexed),
    REGISTER_SCALE_BLIT_FLAGS(ByteIndexed, ByteIndexed, 0, SD_LOCK_LUT),
    REGISTER_XPAR_CONVERT_BLIT(ByteIndexedBm, ByteIndexed),
    REGISTER_XPAR_SCALE_BLIT(ByteIndexedBm, ByteIndexed),
    REGISTER_XPAR_SCALE_BLIT(IntArgbBm, ByteIndexed),
    REGISTER_XPAR_BLITBG(ByteIndexedBm, ByteIndexed),
    REGISTER_XPAR_CONVERT_BLIT(IntArgbBm, ByteIndexed),
    REGISTER_XPAR_BLITBG(IntArgbBm, ByteIndexed),

    REGISTER_XOR_BLIT(IntArgb, ByteIndexed),
    REGISTER_ALPHA_MASKFILL(ByteIndexed),
    REGISTER_ALPHA_MASKBLIT(IntArgb, ByteIndexed),
    REGISTER_ALPHA_MASKBLIT(IntArgbPre, ByteIndexed),
    REGISTER_ALPHA_MASKBLIT(IntRgb, ByteIndexed),
    REGISTER_SOLID_DRAWGLYPHLISTAA(ByteIndexed),

    REGISTER_TRANSFORMHELPER_FUNCS(ByteIndexed),
    REGISTER_TRANSFORMHELPER_FUNCS(ByteIndexedBm),
};

jboolebn RegisterByteIndexed(JNIEnv *env)
{
    return RegisterPrimitives(env, ByteIndexedPrimitives,
                              ArrbySize(ByteIndexedPrimitives));
}

jint PixelForByteIndexed(SurfbceDbtbRbsInfo *pRbsInfo, jint rgb)
{
    jint r, g, b;
    ExtrbctIntDcmComponentsX123(rgb, r, g, b);
    return SurfbceDbtb_InvColorMbp(pRbsInfo->invColorTbble, r, g, b);
}

jboolebn checkSbmeLut(jint *SrcRebdLut, jint *DstRebdLut,
                      SurfbceDbtbRbsInfo *pSrcInfo,
                      SurfbceDbtbRbsInfo *pDstInfo)
{
    if (SrcRebdLut != DstRebdLut) {
        juint lutSize = pSrcInfo->lutSize;
        if (lutSize > pDstInfo->lutSize) {
            return JNI_FALSE;
        } else {
            juint i;
            for (i = 0; i < lutSize; i++) {
                if (SrcRebdLut[i] != DstRebdLut[i]) {
                    return JNI_FALSE;
                }
            }
        }
    }
    return JNI_TRUE;
}

DEFINE_CONVERT_BLIT(IntArgb, ByteIndexed, 3ByteRgb)

DEFINE_CONVERT_BLIT(ThreeByteBgr, ByteIndexed, 3ByteRgb)

DEFINE_CONVERT_BLIT(ByteGrby, ByteIndexed, 3ByteRgb)

DEFINE_CONVERT_BLIT(Index12Grby, ByteIndexed, 3ByteRgb)

void NAME_CONVERT_BLIT(ByteIndexed, ByteIndexed)
    (void *srcBbse, void *dstBbse,
     juint width, juint height,
     SurfbceDbtbRbsInfo *pSrcInfo,
     SurfbceDbtbRbsInfo *pDstInfo,
     NbtivePrimitive *pPrim,
     CompositeInfo *pCompInfo)
{
    DeclbreByteIndexedLobdVbrs(SrcRebd)
    DeclbreByteIndexedLobdVbrs(DstRebd)
    jint srcScbn = pSrcInfo->scbnStride;
    jint dstScbn = pDstInfo->scbnStride;

    InitByteIndexedLobdVbrs(SrcRebd, pSrcInfo);
    InitByteIndexedLobdVbrs(DstRebd, pDstInfo);

    if (checkSbmeLut(SrcRebdLut, DstRebdLut, pSrcInfo, pDstInfo)) {
        do {
            memcpy(dstBbse, srcBbse, width);
            srcBbse = PtrAddBytes(srcBbse, srcScbn);
            dstBbse = PtrAddBytes(dstBbse, dstScbn);
        } while (--height > 0);
    } else {
        DeclbreByteIndexedStoreVbrs(DstWrite);

        BlitLoopWidthHeight(ByteIndexed, pSrc, srcBbse, pSrcInfo,
                            ByteIndexed, pDst, dstBbse, pDstInfo, DstWrite,
                            width, height,
                            ConvertVib3ByteRgb
                                (pSrc, ByteIndexed, SrcRebd,
                                 pDst, ByteIndexed, DstWrite, 0, 0));
    }
}

DEFINE_SCALE_BLIT(IntArgb, ByteIndexed, 3ByteRgb)

DEFINE_SCALE_BLIT(ThreeByteBgr, ByteIndexed, 3ByteRgb)

DEFINE_SCALE_BLIT(ByteGrby, ByteIndexed, 3ByteRgb)

DEFINE_SCALE_BLIT(Index12Grby, ByteIndexed, 3ByteRgb)

void NAME_SCALE_BLIT(ByteIndexed, ByteIndexed)
    (void *srcBbse, void *dstBbse,
     juint width, juint height,
     jint sxloc, jint syloc,
     jint sxinc, jint syinc, jint shift,
     SurfbceDbtbRbsInfo *pSrcInfo,
     SurfbceDbtbRbsInfo *pDstInfo,
     NbtivePrimitive *pPrim,
     CompositeInfo *pCompInfo)
{
    DeclbreByteIndexedLobdVbrs(SrcRebd)
    DeclbreByteIndexedLobdVbrs(DstRebd)
    jint srcScbn = pSrcInfo->scbnStride;
    jint dstScbn = pDstInfo->scbnStride;
    DeclbreByteIndexedStoreVbrs(DstWrite)

    InitByteIndexedLobdVbrs(SrcRebd, pSrcInfo);
    InitByteIndexedLobdVbrs(DstRebd, pDstInfo);

    if (checkSbmeLut(SrcRebdLut, DstRebdLut, pSrcInfo, pDstInfo)) {
        BlitLoopScbleWidthHeight(ByteIndexed, pSrc, srcBbse, pSrcInfo,
                                 ByteIndexed, pDst, dstBbse, pDstInfo, DstWrite,
                                 x, width, height,
                                 sxloc, syloc, sxinc, syinc, shift,
                                 pDst[0] = pSrc[x]);
    } else {
        BlitLoopScbleWidthHeight(ByteIndexed, pSrc, srcBbse, pSrcInfo,
                                 ByteIndexed, pDst, dstBbse, pDstInfo, DstWrite,
                                 x, width, height,
                                 sxloc, syloc, sxinc, syinc, shift,
                                 ConvertVib3ByteRgb(pSrc, ByteIndexed, SrcRebd,
                                                    pDst, ByteIndexed, DstWrite,
                                                    x, 0));
    }
}

DEFINE_XPAR_CONVERT_BLIT_LUT8(ByteIndexedBm, ByteIndexed, ConvertOnTheFly)

DEFINE_XPAR_SCALE_BLIT_LUT8(ByteIndexedBm, ByteIndexed, ConvertOnTheFly)

DEFINE_XPAR_SCALE_BLIT(IntArgbBm, ByteIndexed, 1IntRgb)

DEFINE_XPAR_BLITBG_LUT8(ByteIndexedBm, ByteIndexed, ConvertOnTheFly)

DEFINE_XPAR_CONVERT_BLIT(IntArgbBm, ByteIndexed, 1IntRgb)

DEFINE_XPAR_BLITBG(IntArgbBm, ByteIndexed, 1IntRgb)

DEFINE_XOR_BLIT(IntArgb, ByteIndexed, AnyByte)

DEFINE_ALPHA_MASKFILL(ByteIndexed, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntArgb, ByteIndexed, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntArgbPre, ByteIndexed, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntRgb, ByteIndexed, 4ByteArgb)

DEFINE_SOLID_DRAWGLYPHLISTAA(ByteIndexed, 3ByteRgb)

DEFINE_TRANSFORMHELPERS(ByteIndexed)

DEFINE_TRANSFORMHELPERS(ByteIndexedBm)
