/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "IntArgbBm.h"
#include "AlphbMbcros.h"

#include "IntArgb.h"
#include "IntArgbPre.h"
#include "ByteIndexed.h"

/*
 * This file declbres, registers, bnd defines the vbrious grbphics
 * primitive loops to mbnipulbte surfbces of type "IntArgbBm".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterIntArgbBm;

DECLARE_CONVERT_BLIT(IntArgbBm, IntArgb);
DECLARE_CONVERT_BLIT(IntArgb, IntArgbBm);
DECLARE_CONVERT_BLIT(ByteIndexed, IntArgbBm);
DECLARE_XPAR_CONVERT_BLIT(ByteIndexedBm, IntArgbBm);

DECLARE_SCALE_BLIT(IntArgb, IntArgbBm);
DECLARE_SCALE_BLIT(ByteIndexed, IntArgbBm);
DECLARE_XPAR_SCALE_BLIT(ByteIndexedBm, IntArgbBm);

DECLARE_XPAR_BLITBG(ByteIndexedBm, IntArgbBm);

DECLARE_XOR_BLIT(IntArgb, IntArgbBm);
DECLARE_ALPHA_MASKFILL(IntArgbBm);
DECLARE_ALPHA_MASKBLIT(IntArgb, IntArgbBm);
DECLARE_ALPHA_MASKBLIT(IntArgbPre, IntArgbBm);
DECLARE_SOLID_DRAWGLYPHLISTAA(IntArgbBm);
DECLARE_SOLID_DRAWGLYPHLISTLCD(IntArgbBm);

DECLARE_TRANSFORMHELPER_FUNCS(IntArgbBm);

NbtivePrimitive IntArgbBmPrimitives[] = {
    REGISTER_ANYINT_ISOCOPY_BLIT(IntArgbBm),
    REGISTER_ANYINT_ISOSCALE_BLIT(IntArgbBm),
    REGISTER_ANYINT_ISOXOR_BLIT(IntArgbBm),
    REGISTER_CONVERT_BLIT(IntArgbBm, IntArgb),
    REGISTER_CONVERT_BLIT(IntArgb, IntArgbBm),
    REGISTER_CONVERT_BLIT(ByteIndexed, IntArgbBm),
    REGISTER_SCALE_BLIT(IntArgb, IntArgbBm),
    REGISTER_SCALE_BLIT(ByteIndexed, IntArgbBm),
    REGISTER_XPAR_CONVERT_BLIT(ByteIndexedBm, IntArgbBm),
    REGISTER_XPAR_SCALE_BLIT(ByteIndexedBm, IntArgbBm),
    REGISTER_XPAR_BLITBG(ByteIndexedBm, IntArgbBm),

    REGISTER_XOR_BLIT(IntArgb, IntArgbBm),
    REGISTER_ALPHA_MASKFILL(IntArgbBm),
    REGISTER_ALPHA_MASKBLIT(IntArgb, IntArgbBm),
    REGISTER_ALPHA_MASKBLIT(IntArgbPre, IntArgbBm),
    REGISTER_SOLID_DRAWGLYPHLISTAA(IntArgbBm),
    REGISTER_SOLID_DRAWGLYPHLISTLCD(IntArgbBm),

    REGISTER_TRANSFORMHELPER_FUNCS(IntArgbBm),
};

jboolebn RegisterIntArgbBm(JNIEnv *env)
{
    return RegisterPrimitives(env, IntArgbBmPrimitives,
                              ArrbySize(IntArgbBmPrimitives));
}

jint PixelForIntArgbBm(SurfbceDbtbRbsInfo *pRbsInfo, jint rgb)
{
    return (rgb | ((rgb >> 31) << 24));
}

DEFINE_CONVERT_BLIT(IntArgbBm, IntArgb, 1IntArgb)

DEFINE_CONVERT_BLIT(IntArgb, IntArgbBm, 1IntArgb)

DEFINE_CONVERT_BLIT(ByteIndexed, IntArgbBm, 1IntArgb)

DEFINE_SCALE_BLIT(IntArgb, IntArgbBm, 1IntArgb)

DEFINE_SCALE_BLIT(ByteIndexed, IntArgbBm, 1IntArgb)

DEFINE_XPAR_CONVERT_BLIT_LUT8(ByteIndexedBm, IntArgbBm, PreProcessLut)

DEFINE_XPAR_SCALE_BLIT_LUT8(ByteIndexedBm, IntArgbBm, PreProcessLut)

DEFINE_XPAR_BLITBG_LUT8(ByteIndexedBm, IntArgbBm, PreProcessLut)

DEFINE_XOR_BLIT(IntArgb, IntArgbBm, AnyInt)

DEFINE_ALPHA_MASKFILL(IntArgbBm, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntArgb, IntArgbBm, 4ByteArgb)

DEFINE_ALPHA_MASKBLIT(IntArgbPre, IntArgbBm, 4ByteArgb)

DEFINE_SOLID_DRAWGLYPHLISTAA(IntArgbBm, 4ByteArgb)

DEFINE_SOLID_DRAWGLYPHLISTLCD(IntArgbBm, 4ByteArgb)

DEFINE_TRANSFORMHELPERS(IntArgbBm)
