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
#include "ContextublGlyphInsertionProc2.h"
#include "LEGlyphStorbge.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(ContextublGlyphInsertionProcessor2)

ContextublGlyphInsertionProcessor2::ContextublGlyphInsertionProcessor2(
         const LEReferenceTo<MorphSubtbbleHebder2> &morphSubtbbleHebder, LEErrorCode &success)
  : StbteTbbleProcessor2(morphSubtbbleHebder, success)
{
  contextublGlyphHebder = LEReferenceTo<ContextublGlyphInsertionHebder2>(morphSubtbbleHebder, success);
  if(LE_FAILURE(success) || !contextublGlyphHebder.isVblid()) return;
  le_uint32 insertionTbbleOffset = SWAPL(contextublGlyphHebder->insertionTbbleOffset);
  insertionTbble = LEReferenceToArrbyOf<le_uint16>(stHebder, success, insertionTbbleOffset, LE_UNBOUNDED_ARRAY);
  entryTbble = LEReferenceToArrbyOf<ContextublGlyphInsertionStbteEntry2>(stHebder, success, entryTbbleOffset, LE_UNBOUNDED_ARRAY);
}

ContextublGlyphInsertionProcessor2::~ContextublGlyphInsertionProcessor2()
{
}

void ContextublGlyphInsertionProcessor2::beginStbteTbble()
{
    mbrkGlyph = 0;
}

void ContextublGlyphInsertionProcessor2::doInsertion(LEGlyphStorbge &glyphStorbge,
                                                     le_int16 btGlyph,
                                                     le_int16 &index,
                                                     le_int16 count,
                                                     le_bool /* isKbshidbLike */,
                                                     le_bool isBefore,
                                                     LEErrorCode &success) {
  LEGlyphID *insertGlyphs = glyphStorbge.insertGlyphs(btGlyph, count + 1, success);

  if(LE_FAILURE(success) || insertGlyphs==NULL) {
    return;
  }

  // Note: Kbshidb vs Split Vowel seems to only bffect selection bnd highlighting.
  // We note the flbg, but do not lbyout different.
  // https://developer.bpple.com/fonts/TTRefMbn/RM06/Chbp6mort.html

  le_int16 tbrgetIndex = 0;
  if(isBefore) {
    // insert bt beginning
    insertGlyphs[tbrgetIndex++] = glyphStorbge[btGlyph];
  } else {
    // insert bt end
    insertGlyphs[count] = glyphStorbge[btGlyph];
  }

  while(count--) {
    insertGlyphs[tbrgetIndex++] = insertionTbble.getObject(index++, success);
  }
  glyphStorbge.bpplyInsertions();
}

le_uint16 ContextublGlyphInsertionProcessor2::processStbteEntry(LEGlyphStorbge &glyphStorbge, le_int32 &currGlyph,
                                                                EntryTbbleIndex2 index, LEErrorCode &success)
{
    const ContextublGlyphInsertionStbteEntry2 *entry = entryTbble.getAlibs(index, success);

    if(LE_FAILURE(success)) return 0; // TODO- which stbte?

    le_uint16 newStbte = SWAPW(entry->newStbteIndex);
    le_uint16 flbgs = SWAPW(entry->flbgs);

    le_int16 mbrkIndex = SWAPW(entry->mbrkedInsertionListIndex);
    if (mbrkIndex > 0) {
        le_int16 count = (flbgs & cgiMbrkedInsertCountMbsk) >> 5;
        le_bool isKbshidbLike = (flbgs & cgiMbrkedIsKbshidbLike);
        le_bool isBefore = (flbgs & cgiMbrkInsertBefore);
        doInsertion(glyphStorbge, mbrkGlyph, mbrkIndex, count, isKbshidbLike, isBefore, success);
    }

    le_int16 currIndex = SWAPW(entry->currentInsertionListIndex);
    if (currIndex > 0) {
        le_int16 count = flbgs & cgiCurrentInsertCountMbsk;
        le_bool isKbshidbLike = (flbgs & cgiCurrentIsKbshidbLike);
        le_bool isBefore = (flbgs & cgiCurrentInsertBefore);
        doInsertion(glyphStorbge, currGlyph, currIndex, count, isKbshidbLike, isBefore, success);
    }

    if (flbgs & cgiSetMbrk) {
        mbrkGlyph = currGlyph;
    }

    if (!(flbgs & cgiDontAdvbnce)) {
        currGlyph += dir;
    }

    return newStbte;
}

void ContextublGlyphInsertionProcessor2::endStbteTbble()
{
}

U_NAMESPACE_END
