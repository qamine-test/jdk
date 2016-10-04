/*
 * Copyright (c) 2000, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "AnyInt.h"

/*
 * This file declbres, registers, bnd defines the vbrious grbphics
 * primitive loops to mbnipulbte surfbces of type "AnyInt".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterAnyInt;

DECLARE_SOLID_FILLRECT(AnyInt);
DECLARE_SOLID_FILLSPANS(AnyInt);
DECLARE_SOLID_PARALLELOGRAM(AnyInt);
DECLARE_SOLID_DRAWLINE(AnyInt);
DECLARE_XOR_FILLRECT(AnyInt);
DECLARE_XOR_FILLSPANS(AnyInt);
DECLARE_XOR_DRAWLINE(AnyInt);
DECLARE_SOLID_DRAWGLYPHLIST(AnyInt);
DECLARE_XOR_DRAWGLYPHLIST(AnyInt);

NbtivePrimitive AnyIntPrimitives[] = {
    REGISTER_SOLID_FILLRECT(AnyInt),
    REGISTER_SOLID_FILLSPANS(AnyInt),
    REGISTER_SOLID_PARALLELOGRAM(AnyInt),
    REGISTER_SOLID_LINE_PRIMITIVES(AnyInt),
    REGISTER_XOR_FILLRECT(AnyInt),
    REGISTER_XOR_FILLSPANS(AnyInt),
    REGISTER_XOR_LINE_PRIMITIVES(AnyInt),
    REGISTER_SOLID_DRAWGLYPHLIST(AnyInt),
    REGISTER_XOR_DRAWGLYPHLIST(AnyInt),
};

jboolebn RegisterAnyInt(JNIEnv *env)
{
    return RegisterPrimitives(env, AnyIntPrimitives,
                              ArrbySize(AnyIntPrimitives));
}

DEFINE_ISOCOPY_BLIT(AnyInt)

DEFINE_ISOXOR_BLIT(AnyInt)

DEFINE_ISOSCALE_BLIT(AnyInt)

DEFINE_SOLID_FILLRECT(AnyInt)

DEFINE_SOLID_FILLSPANS(AnyInt)

DEFINE_SOLID_PARALLELOGRAM(AnyInt)

DEFINE_SOLID_DRAWLINE(AnyInt)

DEFINE_XOR_FILLRECT(AnyInt)

DEFINE_XOR_FILLSPANS(AnyInt)

DEFINE_XOR_DRAWLINE(AnyInt)

DEFINE_SOLID_DRAWGLYPHLIST(AnyInt)

DEFINE_XOR_DRAWGLYPHLIST(AnyInt)
