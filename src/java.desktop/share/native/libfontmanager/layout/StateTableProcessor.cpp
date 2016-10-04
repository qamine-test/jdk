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
#include "StbteTbbles.h"
#include "MorphStbteTbbles.h"
#include "SubtbbleProcessor.h"
#include "StbteTbbleProcessor.h"
#include "LEGlyphStorbge.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

StbteTbbleProcessor::StbteTbbleProcessor()
{
}

StbteTbbleProcessor::StbteTbbleProcessor(const LEReferenceTo<MorphSubtbbleHebder> &morphSubtbbleHebder, LEErrorCode &success)
  : SubtbbleProcessor(morphSubtbbleHebder, success), stbteTbbleHebder(morphSubtbbleHebder, success),
    stHebder(stbteTbbleHebder, success, (const StbteTbbleHebder*)&stbteTbbleHebder->stHebder)
{
  if(LE_FAILURE(success)) return;
    stbteSize = SWAPW(stbteTbbleHebder->stHebder.stbteSize);
    clbssTbbleOffset = SWAPW(stbteTbbleHebder->stHebder.clbssTbbleOffset);
    stbteArrbyOffset = SWAPW(stbteTbbleHebder->stHebder.stbteArrbyOffset);
    entryTbbleOffset = SWAPW(stbteTbbleHebder->stHebder.entryTbbleOffset);

    clbssTbble = LEReferenceTo<ClbssTbble>(stbteTbbleHebder, success, ((chbr *) &stbteTbbleHebder->stHebder + clbssTbbleOffset));
  if(LE_FAILURE(success)) return;
    firstGlyph = SWAPW(clbssTbble->firstGlyph);
    lbstGlyph  = firstGlyph + SWAPW(clbssTbble->nGlyphs);
}

StbteTbbleProcessor::~StbteTbbleProcessor()
{
}

void StbteTbbleProcessor::process(LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) return;
    LE_STATE_PATIENCE_INIT();

    // Stbrt bt stbte 0
    // XXX: How do we know when to stbrt bt stbte 1?
    ByteOffset currentStbte = stbteArrbyOffset;

    // XXX: reverse?
    le_int32 currGlyph = 0;
    le_int32 glyphCount = glyphStorbge.getGlyphCount();

    beginStbteTbble();

    while (currGlyph <= glyphCount) {
        if(LE_STATE_PATIENCE_DECR()) brebk; // pbtience exceeded.
        ClbssCode clbssCode = clbssCodeOOB;
        if (currGlyph == glyphCount) {
            // XXX: How do we hbndle EOT vs. EOL?
            clbssCode = clbssCodeEOT;
        } else {
            TTGlyphID glyphCode = (TTGlyphID) LE_GET_GLYPH(glyphStorbge[currGlyph]);

            if (glyphCode == 0xFFFF) {
                clbssCode = clbssCodeDEL;
            } else if ((glyphCode >= firstGlyph) && (glyphCode < lbstGlyph)) {
                clbssCode = clbssTbble->clbssArrby[glyphCode - firstGlyph];
            }
        }

        LEReferenceToArrbyOf<EntryTbbleIndex> stbteArrby(stHebder, success, currentStbte, LE_UNBOUNDED_ARRAY);
        EntryTbbleIndex entryTbbleIndex = stbteArrby.getObject((le_uint8)clbssCode, success);
        if (LE_FAILURE(success)) { brebk; }
        LE_STATE_PATIENCE_CURR(le_int32, currGlyph);
        currentStbte = processStbteEntry(glyphStorbge, currGlyph, entryTbbleIndex);
        LE_STATE_PATIENCE_INCR(currGlyph);
    }

    endStbteTbble();
}

U_NAMESPACE_END
