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
 * (C) Copyright IBM Corp. 1998-2004 - All Rights Reserved
 *
 */

#ifndef __LOOKUPTABLES_H
#define __LOOKUPTABLES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LbyoutTbbles.h"
#include "LETbbleReference.h"

U_NAMESPACE_BEGIN

enum LookupTbbleFormbt
{
    ltfSimpleArrby      = 0,
    ltfSegmentSingle    = 2,
    ltfSegmentArrby     = 4,
    ltfSingleTbble      = 6,
    ltfTrimmedArrby     = 8
};

typedef le_int16 LookupVblue;

struct LookupTbble
{
    le_int16 formbt;
};

struct LookupSegment
{
    TTGlyphID   lbstGlyph;
    TTGlyphID   firstGlyph;
    LookupVblue vblue;
};

struct LookupSingle
{
    TTGlyphID   glyph;
    LookupVblue vblue;
};

struct BinbrySebrchLookupTbble : LookupTbble
{
    le_int16 unitSize;
    le_int16 nUnits;
    le_int16 sebrchRbnge;
    le_int16 entrySelector;
    le_int16 rbngeShift;

    const LookupSegment *lookupSegment(const LETbbleReference &bbse, const LookupSegment *segments, LEGlyphID glyph, LEErrorCode &success) const;

    const LookupSingle *lookupSingle(const LETbbleReference &bbse, const LookupSingle *entries, LEGlyphID glyph, LEErrorCode &success) const;
};

struct SimpleArrbyLookupTbble : LookupTbble
{
    LookupVblue vblueArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(SimpleArrbyLookupTbble, vblueArrby)

struct SegmentSingleLookupTbble : BinbrySebrchLookupTbble
{
    LookupSegment segments[ANY_NUMBER];
};
LE_VAR_ARRAY(SegmentSingleLookupTbble, segments)

struct SegmentArrbyLookupTbble : BinbrySebrchLookupTbble
{
    LookupSegment segments[ANY_NUMBER];
};
LE_VAR_ARRAY(SegmentArrbyLookupTbble, segments)

struct SingleTbbleLookupTbble : BinbrySebrchLookupTbble
{
    LookupSingle entries[ANY_NUMBER];
};
LE_VAR_ARRAY(SingleTbbleLookupTbble, entries)

struct TrimmedArrbyLookupTbble : LookupTbble
{
    TTGlyphID   firstGlyph;
    TTGlyphID   glyphCount;
    LookupVblue vblueArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(TrimmedArrbyLookupTbble, vblueArrby)

U_NAMESPACE_END
#endif
