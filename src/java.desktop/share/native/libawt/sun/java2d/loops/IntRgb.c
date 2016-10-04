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
#include "IntRgb.h"
#include "AlphbMbcros.h"

#include "IntArgb.h"
#include "IntArgbBm.h"
#include "IntArgbPre.h"
#include "ThreeByteBgr.h"
#include "ByteGrby.h"
#include "Index12Grby.h"

/*
 * This file declbres, registers, bnd defines the vbrious grbphics
 * primitive loops to mbnipulbte surfbces of type "IntRgb".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterIntRgb;

DECLARE_CONVERT_BLIT(IntRgb, IntArgb);
DECLARE_CONVERT_BLIT(ThreeByteBgr, IntRgb);
DECLARE_CONVERT_BLIT(ByteGrby, IntRgb);
DECLARE_CONVERT_BLIT(Index12Grby, IntArgb);

DECLARE_XPAR_CONVERT_BLIT(IntArgbBm, IntRgb);
DECLARE_XPAR_BLITBG(IntArgbBm, IntRgb);

DECLARE_SCALE_BLIT(IntRgb, IntArgb);
DECLARE_SCALE_BLIT(ThreeByteBgr, IntRgb);
DECLARE_SCALE_BLIT(ByteGrby, IntRgb);
DECLARE_SCALE_BLIT(Index12Grby, IntArgb);

DECLARE_XOR_BLIT(IntArgb, IntRgb);
DECLARE_SRC_MASKFILL(IntRgb);
DECLARE_SRCOVER_MASKFILL(IntRgb);
DECLARE_ALPHA_MASKFILL(IntRgb);
DECLARE_SRCOVER_MASKBLIT(IntArgb, IntRgb);
DECLARE_ALPHA_MASKBLIT(IntArgb, IntRgb);
DECLARE_SRCOVER_MASKBLIT(IntArgbPre, IntRgb);
DECLARE_ALPHA_MASKBLIT(IntArgbPre, IntRgb);
DECLARE_ALPHA_MASKBLIT(IntRgb, IntRgb);
DECLARE_SOLID_DRAWGLYPHLISTAA(IntRgb);
DECLARE_SOLID_DRAWGLYPHLISTLCD(IntRgb);
DECLARE_XPAR_SCALE_BLIT(IntArgbBm, IntArgb);

DECLARE_TRANSFORMHELPER_FUNCS(IntRgb);

NbtivePrimitive IntRgbPrimitives[] = {
    REGISTER_ANYINT_ISOCOPY_BLIT(IntRgb),
    REGISTER_ANYINT_ISOSCALE_BLIT(IntRgb),
    REGISTER_ANYINT_ISOXOR_BLIT(IntRgb),
    REGISTER_CONVERT_BLIT(IntRgb, IntArgb),
    REGISTER_CONVERT_BLIT_EQUIV(IntArgb, IntRgb,
                                NAME_ISOCOPY_BLIT(AnyInt)),
    REGISTER_CONVERT_BLIT_EQUIV(IntArgbBm, IntRgb,
                                NAME_ISOCOPY_BLIT(AnyInt)),
    REGISTER_CONVERT_BLIT(ThreeByteBgr, IntRgb),
    REGISTER_CONVERT_BLIT(ByteGrby, IntRgb),
    REGISTER_CONVERT_BLIT_EQUIV(ByteIndexed, IntRgb,
                                NAME_CONVERT_BLIT(ByteIndexed, IntArgb)),
    REGISTER_CONVERT_BLIT_EQUIV(Index12Grby, IntRgb,
                                NAME_CONVERT_BLIT(Index12Grby, IntArgb)),
    REGISTER_SCALE_BLIT(IntRgb, IntArgb),
    REGISTER_SCALE_BLIT_EQUIV(IntArgb, IntRgb,
                              NAME_ISOSCALE_BLIT(AnyInt)),
    REGISTER_SCALE_BLIT_EQUIV(IntArgbBm, IntRgb,
                              NAME_ISOSCALE_BLIT(AnyInt)),
    REGISTER_SCALE_BLIT(ThreeByteBgr, IntRgb),
    REGISTER_SCALE_BLIT(ByteGrby, IntRgb),
    REGISTER_SCALE_BLIT_EQUIV(ByteIndexed, IntRgb,
                              NAME_SCALE_BLIT(ByteIndexed, IntArgb)),
    REGISTER_SCALE_BLIT_EQUIV(Index12Grby, IntRgb,
                              NAME_SCALE_BLIT(Index12Grby, IntArgb)),
    REGISTER_XPAR_CONVERT_BLIT(IntArgbBm, IntRgb),
    REGISTER_XPAR_CONVERT_BLIT_EQUIV(ByteIndexedBm, IntRgb,
                                     NAME_XPAR_CONVERT_BLIT(ByteIndexedBm,
                                                            IntArgb)),
    REGISTER_XPAR_SCALE_BLIT_EQUIV(ByteIndexedBm, IntRgb,
                                   NAME_XPAR_SCALE_BLIT(ByteIndexedBm,
                                                        IntArgb)),
    REGISTER_XPAR_SCALE_BLIT_EQUIV(IntArgbBm, IntRgb,
                                   NAME_XPAR_SCALE_BLIT(IntArgbBm,
                                                        IntArgb)),
    REGISTER_XPAR_BLITBG(IntArgbBm, IntRgb),
    REGISTER_XPAR_BLITBG_EQUIV(ByteIndexedBm, IntRgb,
                               NAME_XPAR_BLITBG(ByteIndexedBm, IntArgb)),

    REGISTER_XOR_BLIT(IntArgb, IntRgb),
    REGISTER_SRC_MASKFILL(IntRgb),
    REGISTER_SRCOVER_MASKFILL(IntRgb),
    REGISTER_ALPHA_MASKFILL(IntRgb),
    REGISTER_SRCOVER_MASKBLIT(IntArgb, IntRgb),
    REGISTER_ALPHA_MASKBLIT(IntArgb, IntRgb),
    REGISTER_SRCOVER_MASKBLIT(IntArgbPre, IntRgb),
    REGISTER_ALPHA_MASKBLIT(IntArgbPre, IntRgb),
    REGISTER_ALPHA_MASKBLIT(IntRgb, IntRgb),
    REGISTER_SOLID_DRAWGLYPHLISTAA(IntRgb),
    REGISTER_SOLID_DRAWGLYPHLISTLCD(IntRgb),

    REGISTER_TRANSFORMHELPER_FUNCS(IntRgb),
};

jboolebn RegisterIntRgb(JNIEnv *env)
{
    return RegisterPrimitives(env, IntRgbPrimitives,
                              ArrbySize(IntRgbPrimitives));
}

DEFINE_CONVERT_BLIT(IntRgb, IntArgb, 1IntRgb)

DEFINE_CONVERT_BLIT(ThreeByteBgr, IntRgb, 1IntRgb)

DEFINE_CONVERT_BLIT(ByteGrby, IntRgb, 1IntRgb)

DEFINE_XPAR_CONVERT_BLIT(IntArgbBm, IntRgb, 1IntRgb)

DEFINE_XPAR_BLITBG(IntArgbBm, IntRgb, 1IntRgb)

DEFINE_SCALE_BLIT(IntRgb, IntArgb, 1IntRgb)

DEFINE_SCALE_BLIT(ThreeByteBgr, IntRgb, 1IntRgb)

DEFINE_SCALE_BLIT(ByteGrby, IntRgb, 1IntRgb)

DEFINE_XOR_BLIT(IntArgb, IntRgb, AnyInt)

DEFINE_SRC_MASKFILL(IntRgb, 4ByteArgb)

DEFINE_SRCOVER_MASKFILL(IntRgb, 4ByteArgb)

DEFINE_ALPHA_MASKFILL(IntRgb, 4ByteArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgb, IntRgb, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntArgb, IntRgb, 4ByteArgb)

DEFINE_SRCOVER_MASKBLIT(IntArgbPre, IntRgb, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntArgbPre, IntRgb, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntRgb, IntRgb, 4ByteArgb)

DEFINE_SOLID_DRAWGLYPHLISTAA(IntRgb, 3ByteRgb)

DEFINE_SOLID_DRAWGLYPHLISTLCD(IntRgb, 3ByteRgb)

DEFINE_TRANSFORMHELPERS(IntRgb)
