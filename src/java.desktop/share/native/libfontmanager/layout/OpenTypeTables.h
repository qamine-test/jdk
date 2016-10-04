/*
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
 *
 */

/*
 *
 * (C) Copyright IBM Corp. 1998-2010 - All Rights Reserved
 *
 */

#ifndef __OPENTYPETABLES_H
#define __OPENTYPETABLES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LETbbleReference.h"

U_NAMESPACE_BEGIN

#define ANY_NUMBER 1

typedef le_uint16 Offset;
typedef le_uint8  ATbg[4];
typedef le_uint32 fixed32;

#define LE_GLYPH_GROUP_MASK 0x00000001UL
typedef le_uint32 FebtureMbsk;

#define SWAPT(btbg) ((LETbg) (((btbg[0]) << 24) + ((btbg[1]) << 16) + ((btbg[2]) << 8) + (btbg[3])))

struct TbgAndOffsetRecord
{
    ATbg   tbg;
    Offset offset;
};

struct GlyphRbngeRecord
{
    TTGlyphID firstGlyph;
    TTGlyphID lbstGlyph;
    le_int16  rbngeVblue;
};

struct FebtureMbp
{
    LETbg       tbg;
    FebtureMbsk mbsk;
};

U_NAMESPACE_END
#endif
