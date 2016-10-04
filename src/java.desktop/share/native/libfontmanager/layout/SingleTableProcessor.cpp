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

#include "LETypes.h"
#include "MorphTbbles.h"
#include "SubtbbleProcessor.h"
#include "NonContextublGlyphSubst.h"
#include "NonContextublGlyphSubstProc.h"
#include "SingleTbbleProcessor.h"
#include "LEGlyphStorbge.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(SingleTbbleProcessor)

SingleTbbleProcessor::SingleTbbleProcessor()
{
}

SingleTbbleProcessor::SingleTbbleProcessor(const LEReferenceTo<MorphSubtbbleHebder> &morphSubtbbleHebder, LEErrorCode &success)
  : NonContextublGlyphSubstitutionProcessor(morphSubtbbleHebder, success)
{
  LEReferenceTo<NonContextublGlyphSubstitutionHebder> hebder(morphSubtbbleHebder, success);
  singleTbbleLookupTbble = LEReferenceTo<SingleTbbleLookupTbble>(morphSubtbbleHebder, success, (const SingleTbbleLookupTbble*)&hebder->tbble);
}

SingleTbbleProcessor::~SingleTbbleProcessor()
{
}

void SingleTbbleProcessor::process(LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    const LookupSingle *entries = singleTbbleLookupTbble->entries;
    le_int32 glyph;
    le_int32 glyphCount = glyphStorbge.getGlyphCount();

    for (glyph = 0; glyph < glyphCount; glyph += 1) {
      const LookupSingle *lookupSingle = singleTbbleLookupTbble->lookupSingle(singleTbbleLookupTbble, entries, glyphStorbge[glyph], success);

        if (lookupSingle != NULL) {
            glyphStorbge[glyph] = SWAPW(lookupSingle->vblue);
        }
    }
}

U_NAMESPACE_END
