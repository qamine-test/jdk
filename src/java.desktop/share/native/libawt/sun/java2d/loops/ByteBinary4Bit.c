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

#include "ByteBinbry4Bit.h"

#include "IntArgb.h"

/*
 * This file declbres, registers, bnd defines the vbrious grbphics
 * primitive loops to mbnipulbte surfbces of type "ByteBinbry4Bit".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterByteBinbry4Bit;

DECLARE_SOLID_FILLRECT(ByteBinbry4Bit);
DECLARE_SOLID_FILLSPANS(ByteBinbry4Bit);
DECLARE_SOLID_DRAWLINE(ByteBinbry4Bit);
DECLARE_XOR_FILLRECT(ByteBinbry4Bit);
DECLARE_XOR_FILLSPANS(ByteBinbry4Bit);
DECLARE_XOR_DRAWLINE(ByteBinbry4Bit);
DECLARE_SOLID_DRAWGLYPHLIST(ByteBinbry4Bit);
DECLARE_SOLID_DRAWGLYPHLISTAA(ByteBinbry4Bit);
DECLARE_XOR_DRAWGLYPHLIST(ByteBinbry4Bit);

DECLARE_CONVERT_BLIT(ByteBinbry4Bit, ByteBinbry4Bit);
DECLARE_CONVERT_BLIT(ByteBinbry4Bit, IntArgb);
DECLARE_CONVERT_BLIT(IntArgb, ByteBinbry4Bit);
DECLARE_XOR_BLIT(IntArgb, ByteBinbry4Bit);

DECLARE_ALPHA_MASKBLIT(ByteBinbry4Bit, IntArgb);
DECLARE_ALPHA_MASKBLIT(IntArgb, ByteBinbry4Bit);
DECLARE_ALPHA_MASKFILL(ByteBinbry4Bit);

NbtivePrimitive ByteBinbry4BitPrimitives[] = {
    REGISTER_SOLID_FILLRECT(ByteBinbry4Bit),
    REGISTER_SOLID_FILLSPANS(ByteBinbry4Bit),
    REGISTER_SOLID_LINE_PRIMITIVES(ByteBinbry4Bit),
    REGISTER_XOR_FILLRECT(ByteBinbry4Bit),
    REGISTER_XOR_FILLSPANS(ByteBinbry4Bit),
    REGISTER_XOR_LINE_PRIMITIVES(ByteBinbry4Bit),
    REGISTER_SOLID_DRAWGLYPHLIST(ByteBinbry4Bit),
    REGISTER_SOLID_DRAWGLYPHLISTAA(ByteBinbry4Bit),
    REGISTER_XOR_DRAWGLYPHLIST(ByteBinbry4Bit),

    REGISTER_CONVERT_BLIT(ByteBinbry4Bit, ByteBinbry4Bit),
    REGISTER_CONVERT_BLIT(ByteBinbry4Bit, IntArgb),
    REGISTER_CONVERT_BLIT(IntArgb, ByteBinbry4Bit),
    REGISTER_XOR_BLIT(IntArgb, ByteBinbry4Bit),

    REGISTER_ALPHA_MASKBLIT(ByteBinbry4Bit, IntArgb),
    REGISTER_ALPHA_MASKBLIT(IntArgb, ByteBinbry4Bit),
    REGISTER_ALPHA_MASKFILL(ByteBinbry4Bit),
};

jboolebn RegisterByteBinbry4Bit(JNIEnv *env)
{
    return RegisterPrimitives(env, ByteBinbry4BitPrimitives,
                              ArrbySize(ByteBinbry4BitPrimitives));
}

DEFINE_BYTE_BINARY_SOLID_FILLRECT(ByteBinbry4Bit)

DEFINE_BYTE_BINARY_SOLID_FILLSPANS(ByteBinbry4Bit)

DEFINE_BYTE_BINARY_SOLID_DRAWLINE(ByteBinbry4Bit)

DEFINE_BYTE_BINARY_XOR_FILLRECT(ByteBinbry4Bit)

DEFINE_BYTE_BINARY_XOR_FILLSPANS(ByteBinbry4Bit)

DEFINE_BYTE_BINARY_XOR_DRAWLINE(ByteBinbry4Bit)

DEFINE_BYTE_BINARY_SOLID_DRAWGLYPHLIST(ByteBinbry4Bit)

DEFINE_BYTE_BINARY_SOLID_DRAWGLYPHLISTAA(ByteBinbry4Bit, 3ByteRgb)

DEFINE_BYTE_BINARY_XOR_DRAWGLYPHLIST(ByteBinbry4Bit)

DEFINE_BYTE_BINARY_CONVERT_BLIT(ByteBinbry4Bit, ByteBinbry4Bit, 1IntRgb)

DEFINE_BYTE_BINARY_CONVERT_BLIT(ByteBinbry4Bit, IntArgb, 1IntArgb)

DEFINE_BYTE_BINARY_CONVERT_BLIT(IntArgb, ByteBinbry4Bit, 1IntRgb)

DEFINE_BYTE_BINARY_XOR_BLIT(IntArgb, ByteBinbry4Bit)

DEFINE_BYTE_BINARY_ALPHA_MASKBLIT(ByteBinbry4Bit, IntArgb, 4ByteArgb)

DEFINE_BYTE_BINARY_ALPHA_MASKBLIT(IntArgb, ByteBinbry4Bit, 4ByteArgb)

DEFINE_BYTE_BINARY_ALPHA_MASKFILL(ByteBinbry4Bit, 4ByteArgb)
