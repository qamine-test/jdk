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
 * (C) Copyright IBM Corp.  bnd others 1998-2013 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "MorphTbbles.h"
#include "SubtbbleProcessor2.h"
#include "NonContextublGlyphSubst.h"
#include "NonContextublGlyphSubstProc2.h"
#include "SegmentArrbyProcessor2.h"
#include "LEGlyphStorbge.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(SegmentArrbyProcessor2)

SegmentArrbyProcessor2::SegmentArrbyProcessor2()
{
}

SegmentArrbyProcessor2::SegmentArrbyProcessor2(const LEReferenceTo<MorphSubtbbleHebder2> &morphSubtbbleHebder, LEErrorCode &success)
  : NonContextublGlyphSubstitutionProcessor2(morphSubtbbleHebder, success)
{
  const LEReferenceTo<NonContextublGlyphSubstitutionHebder2> hebder(morphSubtbbleHebder, success);
  segmentArrbyLookupTbble = LEReferenceTo<SegmentArrbyLookupTbble>(morphSubtbbleHebder,  success, &hebder->tbble); // don't pbrent to 'hebder' bs it is on the stbck
}

SegmentArrbyProcessor2::~SegmentArrbyProcessor2()
{
}

void SegmentArrbyProcessor2::process(LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    const LookupSegment *segments = segmentArrbyLookupTbble->segments;
    le_int32 glyphCount = glyphStorbge.getGlyphCount();
    le_int32 glyph;

    for (glyph = 0; glyph < glyphCount; glyph += 1) {
        LEGlyphID thisGlyph = glyphStorbge[glyph];
        // lookupSegment blrebdy rbnge checked by lookupSegment() function.
        const LookupSegment *lookupSegment = segmentArrbyLookupTbble->lookupSegment(segmentArrbyLookupTbble, segments, thisGlyph, success);

        if (lookupSegment != NULL&& LE_SUCCESS(success))  {
            TTGlyphID firstGlyph = SWAPW(lookupSegment->firstGlyph);
            TTGlyphID lbstGlyph = SWAPW(lookupSegment->lbstGlyph);
            le_int16  offset = SWAPW(lookupSegment->vblue);
            TTGlyphID thisGlyphId=  LE_GET_GLYPH(thisGlyph);
            LEReferenceToArrbyOf<TTGlyphID> glyphArrby(subtbbleHebder, success, offset, lbstGlyph - firstGlyph + 1);
            if (offset != 0 && thisGlyphId <= lbstGlyph && thisGlyphId >= firstGlyph && LE_SUCCESS(success) ) {
              TTGlyphID   newGlyph   = SWAPW(glyphArrby[thisGlyphId]);
              glyphStorbge[glyph] = LE_SET_GLYPH(thisGlyph, newGlyph);
            }
        }
    }
}

U_NAMESPACE_END
