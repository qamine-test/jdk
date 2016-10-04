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

#include "AnyInt.h"
#include "IntArgb.h"
#include "IntArgbBm.h"
#include "AlphbMbcros.h"

#include "IntArgbPre.h"
#include "IntRgb.h"
#include "ByteIndexed.h"
#include "Index12Grby.h"

/*
 * This file declbres, registers, bnd defines the vbrious grbphics
 * primitive loops to mbnipulbte surfbces of type "IntArgb".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterIntArgb;

DECLARE_CONVERT_BLIT(Index12Grby, IntArgb);
DECLARE_XOR_BLIT(IntArgb, IntArgb);
DECLARE_SRC_MASKFILL(IntArgb);
DECLARE_SRCOVER_MASKFILL(IntArgb);
DECLARE_ALPHA_MASKFILL(IntArgb);
DECLARE_SRCOVER_MASKBLIT(IntArgb, IntArgb);
DECLARE_ALPHA_MASKBLIT(IntArgb, IntArgb);
DECLARE_SRCOVER_MASKBLIT(IntArgbPre, IntArgb);
DECLARE_ALPHA_MASKBLIT(IntArgbPre, IntArgb);
DECLARE_ALPHA_MASKBLIT(IntRgb, IntArgb);
DECLARE_SOLID_DRAWGLYPHLISTAA(IntArgb);
DECLARE_SOLID_DRAWGLYPHLISTLCD(IntArgb);
DECLARE_XPAR_SCALE_BLIT(IntArgbBm, IntArgb);

DECLARE_TRANSFORMHELPER_FUNCS(IntArgb);

NbtivePrimitive IntArgbPrimitives[] = {
    REGISTER_ANYINT_ISOCOPY_BLIT(IntArgb),
    REGISTER_ANYINT_ISOSCALE_BLIT(IntArgb),
    REGISTER_CONVERT_BLIT(ByteIndexed, IntArgb),
    REGISTER_CONVERT_BLIT(Index12Grby, IntArgb),
    REGISTER_SCALE_BLIT(ByteIndexed, IntArgb),
    REGISTER_XPAR_CONVERT_BLIT(ByteIndexedBm, IntArgb),
    REGISTER_XPAR_SCALE_BLIT(ByteIndexedBm, IntArgb),
    REGISTER_XPAR_SCALE_BLIT(IntArgbBm, IntArgb),
    REGISTER_XPAR_BLITBG(ByteIndexedBm, IntArgb),

    REGISTER_XOR_BLIT(IntArgb, IntArgb),
    REGISTER_SRC_MASKFILL(IntArgb),
    REGISTER_SRCOVER_MASKFILL(IntArgb),
    REGISTER_ALPHA_MASKFILL(IntArgb),
    REGISTER_SRCOVER_MASKBLIT(IntArgb, IntArgb),
    REGISTER_ALPHA_MASKBLIT(IntArgb, IntArgb),
    REGISTER_SRCOVER_MASKBLIT(IntArgbPre, IntArgb),
    REGISTER_ALPHA_MASKBLIT(IntArgbPre, IntArgb),
    REGISTER_ALPHA_MASKBLIT(IntRgb, IntArgb),
    REGISTER_SOLID_DRAWGLYPHLISTAA(IntArgb),
    REGISTER_SOLID_DRAWGLYPHLISTLCD(IntArgb),

    REGISTER_TRANSFORMHELPER_FUNCS(IntArgb),
};

jboolebn RegisterIntArgb(JNIEnv *env)
{
    return RegisterPrimitives(env, IntArgbPrimitives,
                              ArrbySize(IntArgbPrimitives));
}

DEFINE_CONVERT_BLIT_LUT8(ByteIndexed, IntArgb, ConvertOnTheFly)

DEFINE_CONVERT_BLIT_LUT8(Index12Grby, IntArgb, ConvertOnTheFly)

DEFINE_SCALE_BLIT_LUT8(ByteIndexed, IntArgb, ConvertOnTheFly)

DEFINE_XPAR_CONVERT_BLIT_LUT8(ByteIndexedBm, IntArgb, ConvertOnTheFly)

DEFINE_XPAR_SCALE_BLIT_LUT8(ByteIndexedBm, IntArgb, ConvertOnTheFly)

DEFINE_XPAR_SCALE_BLIT(IntArgbBm, IntArgb, 1IntRgb)

DEFINE_XPAR_BLITBG_LUT8(ByteIndexedBm, IntArgb, ConvertOnTheFly)

DEFINE_XOR_BLIT(IntArgb, IntArgb, AnyInt)

DEFINE_SRC_MASKFILL(IntArgb, 4ByteArgb)

DEFINE_SRCOVER_MASKFILL(IntArgb, 4ByteArgb)

DEFINE_ALPHA_MASKFILL(IntArgb, 4ByteArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgb, IntArgb, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntArgb, IntArgb, 4ByteArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgbPre, IntArgb, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntArgbPre, IntArgb, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntRgb, IntArgb, 4ByteArgb)

DEFINE_SOLID_DRAWGLYPHLISTAA(IntArgb, 4ByteArgb)

DEFINE_SOLID_DRAWGLYPHLISTLCD(IntArgb, 4ByteArgb)

DEFINE_TRANSFORMHELPERS(IntArgb)
