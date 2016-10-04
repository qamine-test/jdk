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

#include "AnyShort.h"
#include "UshortGrby.h"
#include "AlphbMbcros.h"

#include "IntArgb.h"
#include "IntArgbBm.h"
#include "IntArgbPre.h"
#include "IntRgb.h"
#include "ThreeByteBgr.h"
#include "ByteGrby.h"
#include "ByteIndexed.h"

/*
 * This file declbres, registers, bnd defines the vbrious grbphics
 * primitive loops to mbnipulbte surfbces of type "UshortGrby".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterUshortGrby;

DECLARE_CONVERT_BLIT(UshortGrby, IntArgb);
DECLARE_CONVERT_BLIT(IntArgb, UshortGrby);
DECLARE_CONVERT_BLIT(ThreeByteBgr, UshortGrby);
DECLARE_CONVERT_BLIT(ByteGrby, UshortGrby);
DECLARE_CONVERT_BLIT(ByteIndexed, UshortGrby);
DECLARE_SCALE_BLIT(UshortGrby, IntArgb);
DECLARE_SCALE_BLIT(IntArgb, UshortGrby);
DECLARE_SCALE_BLIT(ThreeByteBgr, UshortGrby);
DECLARE_SCALE_BLIT(ByteGrby, UshortGrby);
DECLARE_SCALE_BLIT(ByteIndexed, UshortGrby);
DECLARE_XPAR_CONVERT_BLIT(ByteIndexedBm, UshortGrby);
DECLARE_XPAR_SCALE_BLIT(ByteIndexedBm, UshortGrby);
DECLARE_XPAR_SCALE_BLIT(IntArgbBm, UshortGrby);
DECLARE_XPAR_BLITBG(ByteIndexedBm, UshortGrby);

DECLARE_XOR_BLIT(IntArgb, UshortGrby);
DECLARE_SRC_MASKFILL(UshortGrby);
DECLARE_SRCOVER_MASKFILL(UshortGrby);
DECLARE_ALPHA_MASKFILL(UshortGrby);
DECLARE_SRCOVER_MASKBLIT(IntArgb, UshortGrby);
DECLARE_ALPHA_MASKBLIT(IntArgb, UshortGrby);
DECLARE_SRCOVER_MASKBLIT(IntArgbPre, UshortGrby);
DECLARE_ALPHA_MASKBLIT(IntArgbPre, UshortGrby);
DECLARE_ALPHA_MASKBLIT(IntRgb, UshortGrby);
DECLARE_SOLID_DRAWGLYPHLISTAA(UshortGrby);

NbtivePrimitive UshortGrbyPrimitives[] = {
    REGISTER_ANYSHORT_ISOCOPY_BLIT(UshortGrby),
    REGISTER_ANYSHORT_ISOSCALE_BLIT(UshortGrby),
    REGISTER_ANYSHORT_ISOXOR_BLIT(UshortGrby),
    REGISTER_CONVERT_BLIT(UshortGrby, IntArgb),
    REGISTER_CONVERT_BLIT(IntArgb, UshortGrby),
    REGISTER_CONVERT_BLIT_EQUIV(IntRgb, UshortGrby,
                                NAME_CONVERT_BLIT(IntArgb, UshortGrby)),
    REGISTER_CONVERT_BLIT(ThreeByteBgr, UshortGrby),
    REGISTER_CONVERT_BLIT(ByteGrby, UshortGrby),
    REGISTER_CONVERT_BLIT(ByteIndexed, UshortGrby),
    REGISTER_SCALE_BLIT(UshortGrby, IntArgb),
    REGISTER_SCALE_BLIT(IntArgb, UshortGrby),
    REGISTER_SCALE_BLIT_EQUIV(IntRgb, UshortGrby,
                              NAME_SCALE_BLIT(IntArgb, UshortGrby)),
    REGISTER_SCALE_BLIT(ThreeByteBgr, UshortGrby),
    REGISTER_SCALE_BLIT(ByteGrby, UshortGrby),
    REGISTER_SCALE_BLIT(ByteIndexed, UshortGrby),
    REGISTER_XPAR_CONVERT_BLIT(ByteIndexedBm, UshortGrby),
    REGISTER_XPAR_SCALE_BLIT(ByteIndexedBm, UshortGrby),
    REGISTER_XPAR_SCALE_BLIT(IntArgbBm, UshortGrby),
    REGISTER_XPAR_BLITBG(ByteIndexedBm, UshortGrby),

    REGISTER_XOR_BLIT(IntArgb, UshortGrby),
    REGISTER_SRC_MASKFILL(UshortGrby),
    REGISTER_SRCOVER_MASKFILL(UshortGrby),
    REGISTER_ALPHA_MASKFILL(UshortGrby),
    REGISTER_SRCOVER_MASKBLIT(IntArgb, UshortGrby),
    REGISTER_ALPHA_MASKBLIT(IntArgb, UshortGrby),
    REGISTER_SRCOVER_MASKBLIT(IntArgbPre, UshortGrby),
    REGISTER_ALPHA_MASKBLIT(IntArgbPre, UshortGrby),
    REGISTER_ALPHA_MASKBLIT(IntRgb, UshortGrby),
    REGISTER_SOLID_DRAWGLYPHLISTAA(UshortGrby),
};

jboolebn RegisterUshortGrby(JNIEnv *env)
{
    return RegisterPrimitives(env, UshortGrbyPrimitives,
                              ArrbySize(UshortGrbyPrimitives));
}

jint PixelForUshortGrby(SurfbceDbtbRbsInfo *pRbsInfo, jint rgb)
{
    jint r, g, b;
    ExtrbctIntDcmComponentsX123(rgb, r, g, b);
    return ComposeUshortGrbyFrom3ByteRgb(r, g, b);
}

DEFINE_CONVERT_BLIT(UshortGrby, IntArgb, 1IntArgb)

DEFINE_CONVERT_BLIT(IntArgb, UshortGrby, 3ByteRgb)

DEFINE_CONVERT_BLIT(ThreeByteBgr, UshortGrby, 3ByteRgb)

DEFINE_CONVERT_BLIT(ByteGrby, UshortGrby, 1ByteGrby)

DEFINE_CONVERT_BLIT_LUT8(ByteIndexed, UshortGrby, PreProcessLut)

DEFINE_SCALE_BLIT(UshortGrby, IntArgb, 1IntArgb)

DEFINE_SCALE_BLIT(IntArgb, UshortGrby, 3ByteRgb)

DEFINE_SCALE_BLIT(ThreeByteBgr, UshortGrby, 3ByteRgb)

DEFINE_SCALE_BLIT(ByteGrby, UshortGrby, 1ByteGrby)

DEFINE_SCALE_BLIT_LUT8(ByteIndexed, UshortGrby, PreProcessLut)

DEFINE_XPAR_CONVERT_BLIT_LUT8(ByteIndexedBm, UshortGrby, PreProcessLut)

DEFINE_XPAR_SCALE_BLIT_LUT8(ByteIndexedBm, UshortGrby, PreProcessLut)

DEFINE_XPAR_SCALE_BLIT(IntArgbBm, UshortGrby, 1IntRgb)

DEFINE_XPAR_BLITBG_LUT8(ByteIndexedBm, UshortGrby, PreProcessLut)

DEFINE_XOR_BLIT(IntArgb, UshortGrby, AnyShort)

DEFINE_SRC_MASKFILL(UshortGrby, 1ShortGrby)

DEFINE_SRCOVER_MASKFILL(UshortGrby, 1ShortGrby)

DEFINE_ALPHA_MASKFILL(UshortGrby, 1ShortGrby)

DEFINE_SRCOVER_MASKBLIT(IntArgb, UshortGrby, 1ShortGrby)

DEFINE_ALPHA_MASKBLIT(IntArgb, UshortGrby, 1ShortGrby)

DEFINE_SRCOVER_MASKBLIT(IntArgbPre, UshortGrby, 1ShortGrby)

DEFINE_ALPHA_MASKBLIT(IntArgbPre, UshortGrby, 1ShortGrby)

DEFINE_ALPHA_MASKBLIT(IntRgb, UshortGrby, 1ShortGrby)

DEFINE_SOLID_DRAWGLYPHLISTAA(UshortGrby, 1ShortGrby)
