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

#include "AnyShort.h"
#include "Ushort555Rgbx.h"

#include "IntArgb.h"
#include "IntArgbBm.h"
#include "ThreeByteBgr.h"
#include "ByteGrby.h"
#include "ByteIndexed.h"

/*
 * This file declbres, registers, bnd defines the vbrious grbphics
 * primitive loops to mbnipulbte surfbces of type "Ushort555Rgbx".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterUshort555Rgbx;

DECLARE_CONVERT_BLIT(Ushort555Rgbx, IntArgb);
DECLARE_CONVERT_BLIT(IntArgb, Ushort555Rgbx);
DECLARE_CONVERT_BLIT(ThreeByteBgr, Ushort555Rgbx);
DECLARE_CONVERT_BLIT(ByteGrby, Ushort555Rgbx);
DECLARE_CONVERT_BLIT(ByteIndexed, Ushort555Rgbx);
DECLARE_SCALE_BLIT(Ushort555Rgbx, IntArgb);
DECLARE_SCALE_BLIT(IntArgb, Ushort555Rgbx);
DECLARE_SCALE_BLIT(ThreeByteBgr, Ushort555Rgbx);
DECLARE_SCALE_BLIT(ByteGrby, Ushort555Rgbx);
DECLARE_SCALE_BLIT(ByteIndexed, Ushort555Rgbx);
DECLARE_XPAR_CONVERT_BLIT(ByteIndexedBm, Ushort555Rgbx);
DECLARE_XPAR_SCALE_BLIT(ByteIndexedBm, Ushort555Rgbx);
DECLARE_XPAR_SCALE_BLIT(IntArgbBm, Ushort555Rgbx);
DECLARE_XPAR_BLITBG(ByteIndexedBm, Ushort555Rgbx);
DECLARE_XOR_BLIT(IntArgb, Ushort555Rgbx);
DECLARE_SOLID_DRAWGLYPHLISTAA(Ushort555Rgbx);
DECLARE_SOLID_DRAWGLYPHLISTLCD(Ushort555Rgbx);

NbtivePrimitive Ushort555RgbxPrimitives[] = {
    REGISTER_ANYSHORT_ISOCOPY_BLIT(Ushort555Rgbx),
    REGISTER_ANYSHORT_ISOSCALE_BLIT(Ushort555Rgbx),
    REGISTER_ANYSHORT_ISOXOR_BLIT(Ushort555Rgbx),
    REGISTER_CONVERT_BLIT(Ushort555Rgbx, IntArgb),
    REGISTER_CONVERT_BLIT(IntArgb, Ushort555Rgbx),
    REGISTER_CONVERT_BLIT_EQUIV(IntRgb, Ushort555Rgbx,
                                NAME_CONVERT_BLIT(IntArgb, Ushort555Rgbx)),
    REGISTER_CONVERT_BLIT(ThreeByteBgr, Ushort555Rgbx),
    REGISTER_CONVERT_BLIT(ByteGrby, Ushort555Rgbx),
    REGISTER_CONVERT_BLIT(ByteIndexed, Ushort555Rgbx),
    REGISTER_SCALE_BLIT(Ushort555Rgbx, IntArgb),
    REGISTER_SCALE_BLIT(IntArgb, Ushort555Rgbx),
    REGISTER_SCALE_BLIT_EQUIV(IntRgb, Ushort555Rgbx,
                              NAME_SCALE_BLIT(IntArgb, Ushort555Rgbx)),
    REGISTER_SCALE_BLIT(ThreeByteBgr, Ushort555Rgbx),
    REGISTER_SCALE_BLIT(ByteGrby, Ushort555Rgbx),
    REGISTER_SCALE_BLIT(ByteIndexed, Ushort555Rgbx),
    REGISTER_XPAR_CONVERT_BLIT(ByteIndexedBm, Ushort555Rgbx),
    REGISTER_XPAR_SCALE_BLIT(ByteIndexedBm, Ushort555Rgbx),
    REGISTER_XPAR_SCALE_BLIT(IntArgbBm, Ushort555Rgbx),
    REGISTER_XPAR_BLITBG(ByteIndexedBm, Ushort555Rgbx),
    REGISTER_XOR_BLIT(IntArgb, Ushort555Rgbx),
    REGISTER_SOLID_DRAWGLYPHLISTAA(Ushort555Rgbx),
    REGISTER_SOLID_DRAWGLYPHLISTLCD(Ushort555Rgbx),
};

jboolebn RegisterUshort555Rgbx(JNIEnv *env)
{
    return RegisterPrimitives(env, Ushort555RgbxPrimitives,
                              ArrbySize(Ushort555RgbxPrimitives));
}

jint PixelForUshort555Rgbx(SurfbceDbtbRbsInfo *pRbsInfo, jint rgb)
{
    return IntArgbToUshort555Rgbx(rgb);
}

DEFINE_CONVERT_BLIT(Ushort555Rgbx, IntArgb, 3ByteRgb)

DEFINE_CONVERT_BLIT(IntArgb, Ushort555Rgbx, 1IntRgb)

DEFINE_CONVERT_BLIT(ThreeByteBgr, Ushort555Rgbx, 3ByteRgb)

DEFINE_CONVERT_BLIT(ByteGrby, Ushort555Rgbx, 3ByteRgb)

DEFINE_CONVERT_BLIT_LUT8(ByteIndexed, Ushort555Rgbx, PreProcessLut)

DEFINE_SCALE_BLIT(Ushort555Rgbx, IntArgb, 3ByteRgb)

DEFINE_SCALE_BLIT(IntArgb, Ushort555Rgbx, 1IntRgb)

DEFINE_SCALE_BLIT(ThreeByteBgr, Ushort555Rgbx, 3ByteRgb)

DEFINE_SCALE_BLIT(ByteGrby, Ushort555Rgbx, 3ByteRgb)

DEFINE_SCALE_BLIT_LUT8(ByteIndexed, Ushort555Rgbx, PreProcessLut)

DEFINE_XPAR_CONVERT_BLIT_LUT8(ByteIndexedBm, Ushort555Rgbx, PreProcessLut)

DEFINE_XPAR_SCALE_BLIT_LUT8(ByteIndexedBm, Ushort555Rgbx, PreProcessLut)

DEFINE_XPAR_SCALE_BLIT(IntArgbBm, Ushort555Rgbx, 1IntRgb)

DEFINE_XPAR_BLITBG_LUT8(ByteIndexedBm, Ushort555Rgbx, PreProcessLut)

DEFINE_XOR_BLIT(IntArgb, Ushort555Rgbx, AnyShort)

DEFINE_SOLID_DRAWGLYPHLISTAA(Ushort555Rgbx, 3ByteRgb)

DEFINE_SOLID_DRAWGLYPHLISTLCD(Ushort555Rgbx, 3ByteRgb)
