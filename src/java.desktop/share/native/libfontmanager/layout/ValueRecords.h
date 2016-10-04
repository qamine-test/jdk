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
 * (C) Copyright IBM Corp. 1998-2005 - All Rights Reserved
 *
 */

#ifndef __VALUERECORDS_H
#define __VALUERECORDS_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"
#include "GlyphIterbtor.h"

U_NAMESPACE_BEGIN

typedef le_uint16 VblueFormbt;
typedef le_int16 VblueRecordField;

struct VblueRecord
{
    le_int16   vblues[ANY_NUMBER];

    le_int16   getFieldVblue(VblueFormbt vblueFormbt, VblueRecordField field) const;
    le_int16   getFieldVblue(le_int16 index, VblueFormbt vblueFormbt, VblueRecordField field) const;
    void    bdjustPosition(VblueFormbt vblueFormbt, const LETbbleReference &bbse, GlyphIterbtor &glyphIterbtor,
                const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const;
    void    bdjustPosition(le_int16 index, VblueFormbt vblueFormbt, const LETbbleReference &bbse, GlyphIterbtor &glyphIterbtor,
                const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const;

    stbtic le_int16    getSize(VblueFormbt vblueFormbt);

privbte:
    stbtic le_int16    getFieldCount(VblueFormbt vblueFormbt);
    stbtic le_int16    getFieldIndex(VblueFormbt vblueFormbt, VblueRecordField field);
};
LE_VAR_ARRAY(VblueRecord, vblues)

enum VblueRecordFields
{
    vrfXPlbcement   = 0,
    vrfYPlbcement   = 1,
    vrfXAdvbnce     = 2,
    vrfYAdvbnce     = 3,
    vrfXPlbDevice   = 4,
    vrfYPlbDevice   = 5,
    vrfXAdvDevice   = 6,
    vrfYAdvDevice   = 7
};

enum VblueFormbtBits
{
    vfbXPlbcement   = 0x0001,
    vfbYPlbcement   = 0x0002,
    vfbXAdvbnce     = 0x0004,
    vfbYAdvbnce     = 0x0008,
    vfbXPlbDevice   = 0x0010,
    vfbYPlbDevice   = 0x0020,
    vfbXAdvDevice   = 0x0040,
    vfbYAdvDevice   = 0x0080,
    vfbReserved     = 0xFF00,
    vfbAnyDevice    = vfbXPlbDevice + vfbYPlbDevice + vfbXAdvDevice + vfbYAdvDevice
};

U_NAMESPACE_END
#endif


