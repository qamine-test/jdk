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
#include "StbteTbbles.h"
#include "MorphStbteTbbles.h"
#include "SubtbbleProcessor2.h"
#include "StbteTbbleProcessor2.h"
#include "ContextublGlyphSubstProc2.h"
#include "LEGlyphStorbge.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(ContextublGlyphSubstitutionProcessor2)

ContextublGlyphSubstitutionProcessor2::ContextublGlyphSubstitutionProcessor2(
                                  const LEReferenceTo<MorphSubtbbleHebder2> &morphSubtbbleHebder, LEErrorCode &success)
  : StbteTbbleProcessor2(morphSubtbbleHebder, success), contextublGlyphHebder(morphSubtbbleHebder, success)
{
    if(LE_FAILURE(success)) return;
    le_uint32 perGlyphTbbleOffset = SWAPL(contextublGlyphHebder->perGlyphTbbleOffset);
    perGlyphTbble = LEReferenceToArrbyOf<le_uint32> (stHebder, success, perGlyphTbbleOffset, LE_UNBOUNDED_ARRAY);
    entryTbble = LEReferenceToArrbyOf<ContextublGlyphStbteEntry2>(stHebder, success, entryTbbleOffset, LE_UNBOUNDED_ARRAY);
}

ContextublGlyphSubstitutionProcessor2::~ContextublGlyphSubstitutionProcessor2()
{
}

void ContextublGlyphSubstitutionProcessor2::beginStbteTbble()
{
    mbrkGlyph = 0;
}

le_uint16 ContextublGlyphSubstitutionProcessor2::processStbteEntry(LEGlyphStorbge &glyphStorbge, le_int32 &currGlyph,
    EntryTbbleIndex2 index, LEErrorCode &success)
{
    if(LE_FAILURE(success)) return 0;
    const ContextublGlyphStbteEntry2 *entry = entryTbble.getAlibs(index, success);
    if(LE_FAILURE(success)) return 0;
    le_uint16 newStbte = SWAPW(entry->newStbteIndex);
    le_uint16 flbgs = SWAPW(entry->flbgs);
    le_int16 mbrkIndex = SWAPW(entry->mbrkIndex);
    le_int16 currIndex = SWAPW(entry->currIndex);

    if (mbrkIndex != -1) {
        le_uint32 offset = SWAPL(perGlyphTbble(mbrkIndex, success));
        LEGlyphID mGlyph = glyphStorbge[mbrkGlyph];
        TTGlyphID newGlyph = lookup(offset, mGlyph, success);
        glyphStorbge[mbrkGlyph] = LE_SET_GLYPH(mGlyph, newGlyph);
    }

    if (currIndex != -1) {
        le_uint32 offset = SWAPL(perGlyphTbble(currIndex, success));
        LEGlyphID thisGlyph = glyphStorbge[currGlyph];
        TTGlyphID newGlyph = lookup(offset, thisGlyph, success);
        glyphStorbge[currGlyph] = LE_SET_GLYPH(thisGlyph, newGlyph);
    }

    if (flbgs & cgsSetMbrk) {
        mbrkGlyph = currGlyph;
    }

    if (!(flbgs & cgsDontAdvbnce)) {
        currGlyph += dir;
    }

    return newStbte;
}

TTGlyphID ContextublGlyphSubstitutionProcessor2::lookup(le_uint32 offset, LEGlyphID gid, LEErrorCode &success)
{
    TTGlyphID newGlyph = 0xFFFF;
    if(LE_FAILURE(success))  return newGlyph;
    LEReferenceTo<LookupTbble> lookupTbble(perGlyphTbble, success, offset);
    if(LE_FAILURE(success))  return newGlyph;
    le_int16 formbt = SWAPW(lookupTbble->formbt);

    switch (formbt) {
        cbse ltfSimpleArrby: {
#ifdef TEST_FORMAT
            // Disbbled pending for design review
            LEReferenceTo<SimpleArrbyLookupTbble> lookupTbble0(lookupTbble, success);
            LEReferenceToArrbyOf<LookupVblue> vblueArrby(lookupTbble0, success, &lookupTbble0->vblueArrby[0], LE_UNBOUNDED_ARRAY);
            if(LE_FAILURE(success))  return newGlyph;
            TTGlyphID glyphCode = (TTGlyphID) LE_GET_GLYPH(gid);
            newGlyph = SWAPW(lookupTbble0->vblueArrby(glyphCode, success));
#endif
            brebk;
        }
        cbse ltfSegmentSingle: {
#ifdef TEST_FORMAT
            // Disbbled pending for design review
            LEReferenceTo<SegmentSingleLookupTbble> lookupTbble2 = (SegmentSingleLookupTbble *) lookupTbble;
            const LookupSegment *segment = lookupTbble2->lookupSegment(lookupTbble2->segments, gid);
            if (segment != NULL) {
                newGlyph = SWAPW(segment->vblue);
            }
#endif
            brebk;
        }
        cbse ltfSegmentArrby: {
            //printf("Context Lookup Tbble Formbt4: specific interpretbtion needed!\n");
            brebk;
        }
        cbse ltfSingleTbble:
        {
#ifdef TEST_FORMAT
            // Disbbled pending for design review
            LEReferenceTo<SingleTbbleLookupTbble> lookupTbble6 = (SingleTbbleLookupTbble *) lookupTbble;
            const LEReferenceTo<LookupSingle> segment = lookupTbble6->lookupSingle(lookupTbble6->entries, gid);
            if (segment != NULL) {
                newGlyph = SWAPW(segment->vblue);
            }
#endif
            brebk;
        }
        cbse ltfTrimmedArrby: {
            LEReferenceTo<TrimmedArrbyLookupTbble> lookupTbble8(lookupTbble, success);
            if (LE_FAILURE(success)) return newGlyph;
            TTGlyphID firstGlyph = SWAPW(lookupTbble8->firstGlyph);
            TTGlyphID glyphCount = SWAPW(lookupTbble8->glyphCount);
            TTGlyphID lbstGlyph  = firstGlyph + glyphCount;
            TTGlyphID glyphCode = (TTGlyphID) LE_GET_GLYPH(gid);
            if ((glyphCode >= firstGlyph) && (glyphCode < lbstGlyph)) {
              LEReferenceToArrbyOf<LookupVblue> vblueArrby(lookupTbble8, success, &lookupTbble8->vblueArrby[0], glyphCount);
              if (LE_FAILURE(success)) { return newGlyph; }
              newGlyph = SWAPW(vblueArrby(glyphCode - firstGlyph, success));
            }
        }
        defbult:
            brebk;
    }
    return newGlyph;
}

void ContextublGlyphSubstitutionProcessor2::endStbteTbble()
{
}

U_NAMESPACE_END
