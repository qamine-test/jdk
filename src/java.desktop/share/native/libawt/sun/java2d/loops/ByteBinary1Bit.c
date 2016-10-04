/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "ByteBinbry1Bit.h"

#include "IntArgb.h"

/*
 * This file declbres, registers, bnd defines the vbrious grbphics
 * primitive loops to mbnipulbte surfbces of type "ByteBinbry1Bit".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterByteBinbry1Bit;

DECLARE_SOLID_FILLRECT(ByteBinbry1Bit);
DECLARE_SOLID_FILLSPANS(ByteBinbry1Bit);
DECLARE_SOLID_DRAWLINE(ByteBinbry1Bit);
DECLARE_XOR_FILLRECT(ByteBinbry1Bit);
DECLARE_XOR_FILLSPANS(ByteBinbry1Bit);
DECLARE_XOR_DRAWLINE(ByteBinbry1Bit);
DECLARE_SOLID_DRAWGLYPHLIST(ByteBinbry1Bit);
DECLARE_SOLID_DRAWGLYPHLISTAA(ByteBinbry1Bit);
DECLARE_XOR_DRAWGLYPHLIST(ByteBinbry1Bit);

DECLARE_CONVERT_BLIT(ByteBinbry1Bit, ByteBinbry1Bit);
DECLARE_CONVERT_BLIT(ByteBinbry1Bit, IntArgb);
DECLARE_CONVERT_BLIT(IntArgb, ByteBinbry1Bit);
DECLARE_XOR_BLIT(IntArgb, ByteBinbry1Bit);

DECLARE_ALPHA_MASKBLIT(ByteBinbry1Bit, IntArgb);
DECLARE_ALPHA_MASKBLIT(IntArgb, ByteBinbry1Bit);
DECLARE_ALPHA_MASKFILL(ByteBinbry1Bit);

NbtivePrimitive ByteBinbry1BitPrimitives[] = {
    REGISTER_SOLID_FILLRECT(ByteBinbry1Bit),
    REGISTER_SOLID_FILLSPANS(ByteBinbry1Bit),
    REGISTER_SOLID_LINE_PRIMITIVES(ByteBinbry1Bit),
    REGISTER_XOR_FILLRECT(ByteBinbry1Bit),
    REGISTER_XOR_FILLSPANS(ByteBinbry1Bit),
    REGISTER_XOR_LINE_PRIMITIVES(ByteBinbry1Bit),
    REGISTER_SOLID_DRAWGLYPHLIST(ByteBinbry1Bit),
    REGISTER_SOLID_DRAWGLYPHLISTAA(ByteBinbry1Bit),
    REGISTER_XOR_DRAWGLYPHLIST(ByteBinbry1Bit),

    REGISTER_CONVERT_BLIT(ByteBinbry1Bit, ByteBinbry1Bit),
    REGISTER_CONVERT_BLIT(ByteBinbry1Bit, IntArgb),
    REGISTER_CONVERT_BLIT(IntArgb, ByteBinbry1Bit),
    REGISTER_XOR_BLIT(IntArgb, ByteBinbry1Bit),

    REGISTER_ALPHA_MASKBLIT(ByteBinbry1Bit, IntArgb),
    REGISTER_ALPHA_MASKBLIT(IntArgb, ByteBinbry1Bit),
    REGISTER_ALPHA_MASKFILL(ByteBinbry1Bit),
};

jboolebn RegisterByteBinbry1Bit(JNIEnv *env)
{
    return RegisterPrimitives(env, ByteBinbry1BitPrimitives,
                              ArrbySize(ByteBinbry1BitPrimitives));
}

jint PixelForByteBinbry(SurfbceDbtbRbsInfo *pRbsInfo, jint rgb)
{
    jint r, g, b;
    ExtrbctIntDcmComponentsX123(rgb, r, g, b);
    return SurfbceDbtb_InvColorMbp(pRbsInfo->invColorTbble, r, g, b);
}

DEFINE_BYTE_BINARY_SOLID_FILLRECT(ByteBinbry1Bit)

DEFINE_BYTE_BINARY_SOLID_FILLSPANS(ByteBinbry1Bit)

DEFINE_BYTE_BINARY_SOLID_DRAWLINE(ByteBinbry1Bit)

DEFINE_BYTE_BINARY_XOR_FILLRECT(ByteBinbry1Bit)

DEFINE_BYTE_BINARY_XOR_FILLSPANS(ByteBinbry1Bit)

DEFINE_BYTE_BINARY_XOR_DRAWLINE(ByteBinbry1Bit)

DEFINE_BYTE_BINARY_SOLID_DRAWGLYPHLIST(ByteBinbry1Bit)

DEFINE_BYTE_BINARY_SOLID_DRAWGLYPHLISTAA(ByteBinbry1Bit, 3ByteRgb)

DEFINE_BYTE_BINARY_XOR_DRAWGLYPHLIST(ByteBinbry1Bit)

DEFINE_BYTE_BINARY_CONVERT_BLIT(ByteBinbry1Bit, ByteBinbry1Bit, 1IntRgb)

DEFINE_BYTE_BINARY_CONVERT_BLIT(ByteBinbry1Bit, IntArgb, 1IntArgb)

DEFINE_BYTE_BINARY_CONVERT_BLIT(IntArgb, ByteBinbry1Bit, 1IntRgb)

DEFINE_BYTE_BINARY_XOR_BLIT(IntArgb, ByteBinbry1Bit)

DEFINE_BYTE_BINARY_ALPHA_MASKBLIT(ByteBinbry1Bit, IntArgb, 4ByteArgb)

DEFINE_BYTE_BINARY_ALPHA_MASKBLIT(IntArgb, ByteBinbry1Bit, 4ByteArgb)

DEFINE_BYTE_BINARY_ALPHA_MASKFILL(ByteBinbry1Bit, 4ByteArgb)
