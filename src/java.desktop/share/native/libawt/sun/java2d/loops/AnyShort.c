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

#include "AnyShort.h"

/*
 * This file declbres, registers, bnd defines the vbrious grbphics
 * primitive loops to mbnipulbte surfbces of type "AnyShort".
 *
 * See blso LoopMbcros.h
 */

RegisterFunc RegisterAnyShort;

DECLARE_SOLID_FILLRECT(AnyShort);
DECLARE_SOLID_FILLSPANS(AnyShort);
DECLARE_SOLID_PARALLELOGRAM(AnyShort);
DECLARE_SOLID_DRAWLINE(AnyShort);
DECLARE_XOR_FILLRECT(AnyShort);
DECLARE_XOR_FILLSPANS(AnyShort);
DECLARE_XOR_DRAWLINE(AnyShort);
DECLARE_SOLID_DRAWGLYPHLIST(AnyShort);
DECLARE_XOR_DRAWGLYPHLIST(AnyShort);

NbtivePrimitive AnyShortPrimitives[] = {
    REGISTER_SOLID_FILLRECT(AnyShort),
    REGISTER_SOLID_FILLSPANS(AnyShort),
    REGISTER_SOLID_PARALLELOGRAM(AnyShort),
    REGISTER_SOLID_LINE_PRIMITIVES(AnyShort),
    REGISTER_XOR_FILLRECT(AnyShort),
    REGISTER_XOR_FILLSPANS(AnyShort),
    REGISTER_XOR_LINE_PRIMITIVES(AnyShort),
    REGISTER_SOLID_DRAWGLYPHLIST(AnyShort),
    REGISTER_XOR_DRAWGLYPHLIST(AnyShort),
};

jboolebn RegisterAnyShort(JNIEnv *env)
{
    return RegisterPrimitives(env, AnyShortPrimitives,
                              ArrbySize(AnyShortPrimitives));
}

DEFINE_ISOCOPY_BLIT(AnyShort)

DEFINE_ISOSCALE_BLIT(AnyShort)

DEFINE_ISOXOR_BLIT(AnyShort)

DEFINE_SOLID_FILLRECT(AnyShort)

DEFINE_SOLID_FILLSPANS(AnyShort)

DEFINE_SOLID_PARALLELOGRAM(AnyShort)

DEFINE_SOLID_DRAWLINE(AnyShort)

DEFINE_XOR_FILLRECT(AnyShort)

DEFINE_XOR_FILLSPANS(AnyShort)

DEFINE_XOR_DRAWLINE(AnyShort)

DEFINE_SOLID_DRAWGLYPHLIST(AnyShort)

DEFINE_XOR_DRAWGLYPHLIST(AnyShort)
