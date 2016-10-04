/*
 * Copyright (c) 2000, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "Any3Byte.h"
#include "ThreeByteBgr.h"
#include "AlphbMbcros.h"

#include "IntArgb.h"
#include "IntArgbBm.h"
#include "IntArgbPre.h"
#include "IntRgb.h"
#include "ByteGrby.h"
#include "ByteIndexed.h"

/*
 * This file declbres, registers, bnd defines the vbrious grbphics
 * primitive loops to mbnipulbte surfbces of type "ThreeByteBgr".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterThreeByteBgr;

DECLARE_CONVERT_BLIT(ThreeByteBgr, IntArgb);
DECLARE_CONVERT_BLIT(IntArgb, ThreeByteBgr);
DECLARE_CONVERT_BLIT(ByteGrby, ThreeByteBgr);
DECLARE_CONVERT_BLIT(ByteIndexed, ThreeByteBgr);
DECLARE_SCALE_BLIT(ThreeByteBgr, IntArgb);
DECLARE_SCALE_BLIT(IntArgb, ThreeByteBgr);
DECLARE_SCALE_BLIT(ByteGrby, ThreeByteBgr);
DECLARE_SCALE_BLIT(ByteIndexed, ThreeByteBgr);
DECLARE_XPAR_CONVERT_BLIT(ByteIndexedBm, ThreeByteBgr);
DECLARE_XPAR_SCALE_BLIT(ByteIndexedBm, ThreeByteBgr);
DECLARE_XPAR_SCALE_BLIT(IntArgbBm, ThreeByteBgr);
DECLARE_XPAR_BLITBG(ByteIndexedBm, ThreeByteBgr);
DECLARE_XPAR_CONVERT_BLIT(IntArgbBm, ThreeByteBgr);
DECLARE_XPAR_BLITBG(IntArgbBm, ThreeByteBgr);

DECLARE_XOR_BLIT(IntArgb, ThreeByteBgr);
DECLARE_SRC_MASKFILL(ThreeByteBgr);
DECLARE_SRCOVER_MASKFILL(ThreeByteBgr);
DECLARE_ALPHA_MASKFILL(ThreeByteBgr);
DECLARE_SRCOVER_MASKBLIT(IntArgb, ThreeByteBgr);
DECLARE_ALPHA_MASKBLIT(IntArgb, ThreeByteBgr);
DECLARE_SRCOVER_MASKBLIT(IntArgbPre, ThreeByteBgr);
DECLARE_ALPHA_MASKBLIT(IntArgbPre, ThreeByteBgr);
DECLARE_ALPHA_MASKBLIT(IntRgb, ThreeByteBgr);
DECLARE_SOLID_DRAWGLYPHLISTAA(ThreeByteBgr);
DECLARE_SOLID_DRAWGLYPHLISTLCD(ThreeByteBgr);

DECLARE_TRANSFORMHELPER_FUNCS(ThreeByteBgr);

NbtivePrimitive ThreeByteBgrPrimitives[] = {
    REGISTER_ANY3BYTE_ISOCOPY_BLIT(ThreeByteBgr),
    REGISTER_ANY3BYTE_ISOSCALE_BLIT(ThreeByteBgr),
    REGISTER_ANY3BYTE_ISOXOR_BLIT(ThreeByteBgr),
    REGISTER_CONVERT_BLIT(ThreeByteBgr, IntArgb),
    REGISTER_CONVERT_BLIT(IntArgb, ThreeByteBgr),
    REGISTER_CONVERT_BLIT_EQUIV(IntRgb, ThreeByteBgr,
                                NAME_CONVERT_BLIT(IntArgb, ThreeByteBgr)),
    REGISTER_CONVERT_BLIT_EQUIV(IntArgbBm, ThreeByteBgr,
                                NAME_CONVERT_BLIT(IntArgb, ThreeByteBgr)),
    REGISTER_CONVERT_BLIT(ByteGrby, ThreeByteBgr),
    REGISTER_CONVERT_BLIT(ByteIndexed, ThreeByteBgr),
    REGISTER_SCALE_BLIT(ThreeByteBgr, IntArgb),
    REGISTER_SCALE_BLIT(IntArgb, ThreeByteBgr),
    REGISTER_SCALE_BLIT_EQUIV(IntRgb, ThreeByteBgr,
                              NAME_SCALE_BLIT(IntArgb, ThreeByteBgr)),
    REGISTER_SCALE_BLIT_EQUIV(IntArgbBm, ThreeByteBgr,
                              NAME_SCALE_BLIT(IntArgb, ThreeByteBgr)),
    REGISTER_SCALE_BLIT(ByteGrby, ThreeByteBgr),
    REGISTER_SCALE_BLIT(ByteIndexed, ThreeByteBgr),
    REGISTER_XPAR_CONVERT_BLIT(ByteIndexedBm, ThreeByteBgr),
    REGISTER_XPAR_SCALE_BLIT(ByteIndexedBm, ThreeByteBgr),
    REGISTER_XPAR_SCALE_BLIT(IntArgbBm, ThreeByteBgr),
    REGISTER_XPAR_BLITBG(ByteIndexedBm, ThreeByteBgr),
    REGISTER_XPAR_CONVERT_BLIT(IntArgbBm, ThreeByteBgr),
    REGISTER_XPAR_BLITBG(IntArgbBm, ThreeByteBgr),

    REGISTER_XOR_BLIT(IntArgb, ThreeByteBgr),
    REGISTER_SRC_MASKFILL(ThreeByteBgr),
    REGISTER_SRCOVER_MASKFILL(ThreeByteBgr),
    REGISTER_ALPHA_MASKFILL(ThreeByteBgr),
    REGISTER_SRCOVER_MASKBLIT(IntArgb, ThreeByteBgr),
    REGISTER_ALPHA_MASKBLIT(IntArgb, ThreeByteBgr),
    REGISTER_SRCOVER_MASKBLIT(IntArgbPre, ThreeByteBgr),
    REGISTER_ALPHA_MASKBLIT(IntArgbPre, ThreeByteBgr),
    REGISTER_ALPHA_MASKBLIT(IntRgb, ThreeByteBgr),
    REGISTER_SOLID_DRAWGLYPHLISTAA(ThreeByteBgr),
    REGISTER_SOLID_DRAWGLYPHLISTLCD(ThreeByteBgr),

    REGISTER_TRANSFORMHELPER_FUNCS(ThreeByteBgr),
};

jboolebn RegisterThreeByteBgr(JNIEnv *env)
{
    return RegisterPrimitives(env, ThreeByteBgrPrimitives,
                              ArrbySize(ThreeByteBgrPrimitives));
}

DEFINE_CONVERT_BLIT(ThreeByteBgr, IntArgb, 1IntArgb)

DEFINE_CONVERT_BLIT(IntArgb, ThreeByteBgr, 1IntRgb)

DEFINE_CONVERT_BLIT(ByteGrby, ThreeByteBgr, 3ByteRgb)

DEFINE_CONVERT_BLIT_LUT8(ByteIndexed, ThreeByteBgr, ConvertOnTheFly)

DEFINE_SCALE_BLIT(ThreeByteBgr, IntArgb, 1IntArgb)

DEFINE_SCALE_BLIT(IntArgb, ThreeByteBgr, 1IntRgb)

DEFINE_SCALE_BLIT(ByteGrby, ThreeByteBgr, 3ByteRgb)

DEFINE_SCALE_BLIT_LUT8(ByteIndexed, ThreeByteBgr, ConvertOnTheFly)

DEFINE_XPAR_CONVERT_BLIT_LUT8(ByteIndexedBm, ThreeByteBgr, ConvertOnTheFly)

DEFINE_XPAR_SCALE_BLIT_LUT8(ByteIndexedBm, ThreeByteBgr, ConvertOnTheFly)

DEFINE_XPAR_SCALE_BLIT(IntArgbBm, ThreeByteBgr, 1IntRgb)

DEFINE_XPAR_BLITBG_LUT8(ByteIndexedBm, ThreeByteBgr, ConvertOnTheFly)

DEFINE_XPAR_CONVERT_BLIT(IntArgbBm, ThreeByteBgr, 1IntRgb)

DEFINE_XPAR_BLITBG(IntArgbBm, ThreeByteBgr, 1IntRgb)

DEFINE_XOR_BLIT(IntArgb, ThreeByteBgr, Any3Byte)

DEFINE_SRC_MASKFILL(ThreeByteBgr, 4ByteArgb)

DEFINE_SRCOVER_MASKFILL(ThreeByteBgr, 4ByteArgb)

DEFINE_ALPHA_MASKFILL(ThreeByteBgr, 4ByteArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgb, ThreeByteBgr, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntArgb, ThreeByteBgr, 4ByteArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgbPre, ThreeByteBgr, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntArgbPre, ThreeByteBgr, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntRgb, ThreeByteBgr, 4ByteArgb)

DEFINE_SOLID_DRAWGLYPHLISTAA(ThreeByteBgr, 3ByteRgb)

DEFINE_SOLID_DRAWGLYPHLISTLCD(ThreeByteBgr, 3ByteRgb)

DEFINE_TRANSFORMHELPERS(ThreeByteBgr)
