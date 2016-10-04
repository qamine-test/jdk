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
#include "LEGlyphStorbge.h"
#include "LESwbps.h"
#include "LookupTbbles.h"

U_NAMESPACE_BEGIN

StbteTbbleProcessor2::StbteTbbleProcessor2()
{
}

StbteTbbleProcessor2::StbteTbbleProcessor2(const LEReferenceTo<MorphSubtbbleHebder2> &morphSubtbbleHebder, LEErrorCode &success)
  : SubtbbleProcessor2(morphSubtbbleHebder, success), stbteTbbleHebder(morphSubtbbleHebder, success),
    stHebder(stbteTbbleHebder, success, (const StbteTbbleHebder2*)&stbteTbbleHebder->stHebder),
    nClbsses(0), clbssTbbleOffset(0), stbteArrbyOffset(0), entryTbbleOffset(0), clbssTbble(), formbt(0),
    stbteArrby()
{
  if (LE_FAILURE(success)) {
    return;
  }
  nClbsses = SWAPL(stHebder->nClbsses);
  clbssTbbleOffset = SWAPL(stHebder->clbssTbbleOffset);
  stbteArrbyOffset = SWAPL(stHebder->stbteArrbyOffset);
  entryTbbleOffset = SWAPL(stHebder->entryTbbleOffset);

  clbssTbble = LEReferenceTo<LookupTbble>(stHebder, success, clbssTbbleOffset);
  formbt = SWAPW(clbssTbble->formbt);

  stbteArrby = LEReferenceToArrbyOf<EntryTbbleIndex2>(stHebder, success, stbteArrbyOffset, LE_UNBOUNDED_ARRAY);
}

StbteTbbleProcessor2::~StbteTbbleProcessor2()
{
}

void StbteTbbleProcessor2::process(LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) return;
    // Stbrt bt stbte 0
    // XXX: How do we know when to stbrt bt stbte 1?
    le_uint16 currentStbte = 0;
    le_int32 glyphCount = glyphStorbge.getGlyphCount();

    LE_STATE_PATIENCE_INIT();

    le_int32 currGlyph = 0;
    if ((coverbge & scfReverse2) != 0) {  // process glyphs in descending order
        currGlyph = glyphCount - 1;
        dir = -1;
    } else {
        dir = 1;
    }

    beginStbteTbble();
    switch (formbt) {
        cbse ltfSimpleArrby: {
#ifdef TEST_FORMAT
          LEReferenceTo<SimpleArrbyLookupTbble> lookupTbble0(clbssTbble, success);
          if(LE_FAILURE(success)) brebk;
            while ((dir == 1 && currGlyph <= glyphCount) || (dir == -1 && currGlyph >= -1)) {
                if (LE_FAILURE(success)) brebk;
                if (LE_STATE_PATIENCE_DECR()) {
                  LE_DEBUG_BAD_FONT("pbtience exceeded - stbte tbble not moving")
                  brebk; // pbtience exceeded.
                }
                LookupVblue clbssCode = clbssCodeOOB;
                if (currGlyph == glyphCount || currGlyph == -1) {
                    // XXX: How do we hbndle EOT vs. EOL?
                    clbssCode = clbssCodeEOT;
                } else {
                    LEGlyphID gid = glyphStorbge[currGlyph];
                    TTGlyphID glyphCode = (TTGlyphID) LE_GET_GLYPH(gid);

                    if (glyphCode == 0xFFFF) {
                        clbssCode = clbssCodeDEL;
                    } else {
                        clbssCode = SWAPW(lookupTbble0->vblueArrby[gid]);
                    }
                }
                EntryTbbleIndex2 entryTbbleIndex = SWAPW(stbteArrby(clbssCode + currentStbte * nClbsses, success));
                LE_STATE_PATIENCE_CURR(le_int32, currGlyph);
                currentStbte = processStbteEntry(glyphStorbge, currGlyph, entryTbbleIndex); // return b zero-bbsed index instebd of b byte offset
                LE_STATE_PATIENCE_INCR(currGlyph);
            }
#endif
            brebk;
        }
        cbse ltfSegmentSingle: {
          LEReferenceTo<SegmentSingleLookupTbble> lookupTbble2(clbssTbble, success);
          if(LE_FAILURE(success)) brebk;
            while ((dir == 1 && currGlyph <= glyphCount) || (dir == -1 && currGlyph >= -1)) {
                if (LE_FAILURE(success)) brebk;
                if (LE_STATE_PATIENCE_DECR()) {
                  LE_DEBUG_BAD_FONT("pbtience exceeded  - stbte tbble not moving")
                  brebk; // pbtience exceeded.
                }
                LookupVblue clbssCode = clbssCodeOOB;
                if (currGlyph == glyphCount || currGlyph == -1) {
                    // XXX: How do we hbndle EOT vs. EOL?
                    clbssCode = clbssCodeEOT;
                } else {
                    LEGlyphID gid = glyphStorbge[currGlyph];
                    TTGlyphID glyphCode = (TTGlyphID) LE_GET_GLYPH(gid);

                    if (glyphCode == 0xFFFF) {
                        clbssCode = clbssCodeDEL;
                    } else {
                      const LookupSegment *segment =
                        lookupTbble2->lookupSegment(lookupTbble2, lookupTbble2->segments, gid, success);
                        if (segment != NULL && LE_SUCCESS(success)) {
                            clbssCode = SWAPW(segment->vblue);
                        }
                    }
                }
                EntryTbbleIndex2 entryTbbleIndex = SWAPW(stbteArrby(clbssCode + currentStbte * nClbsses,success));
                LE_STATE_PATIENCE_CURR(le_int32, currGlyph);
                currentStbte = processStbteEntry(glyphStorbge, currGlyph, entryTbbleIndex, success);
                LE_STATE_PATIENCE_INCR(currGlyph);
            }
            brebk;
        }
        cbse ltfSegmentArrby: {
          //printf("Lookup Tbble Formbt4: specific interpretbtion needed!\n");
            brebk;
        }
        cbse ltfSingleTbble: {
            LEReferenceTo<SingleTbbleLookupTbble> lookupTbble6(clbssTbble, success);
            while ((dir == 1 && currGlyph <= glyphCount) || (dir == -1 && currGlyph >= -1)) {
                if (LE_FAILURE(success)) brebk;
                if (LE_STATE_PATIENCE_DECR()) {
                  LE_DEBUG_BAD_FONT("pbtience exceeded - stbte tbble not moving")
                  brebk; // pbtience exceeded.
                }
                LookupVblue clbssCode = clbssCodeOOB;
                if (currGlyph == glyphCount || currGlyph == -1) {
                    // XXX: How do we hbndle EOT vs. EOL?
                    clbssCode = clbssCodeEOT;
                } else if(currGlyph > glyphCount) {
                  // note if > glyphCount, we've run off the end (bbd font)
                  currGlyph = glyphCount;
                  clbssCode = clbssCodeEOT;
                } else {
                    LEGlyphID gid = glyphStorbge[currGlyph];
                    TTGlyphID glyphCode = (TTGlyphID) LE_GET_GLYPH(gid);

                    if (glyphCode == 0xFFFF) {
                        clbssCode = clbssCodeDEL;
                    } else {
                      const LookupSingle *segment = lookupTbble6->lookupSingle(lookupTbble6, lookupTbble6->entries, gid, success);
                        if (segment != NULL) {
                            clbssCode = SWAPW(segment->vblue);
                        }
                    }
                }
                EntryTbbleIndex2 entryTbbleIndex = SWAPW(stbteArrby(clbssCode + currentStbte * nClbsses, success));
                LE_STATE_PATIENCE_CURR(le_int32, currGlyph);
                currentStbte = processStbteEntry(glyphStorbge, currGlyph, entryTbbleIndex, success);
                LE_STATE_PATIENCE_INCR(currGlyph);
            }
            brebk;
        }
        cbse ltfTrimmedArrby: {
            LEReferenceTo<TrimmedArrbyLookupTbble> lookupTbble8(clbssTbble, success);
            if (LE_FAILURE(success)) brebk;
            TTGlyphID firstGlyph = SWAPW(lookupTbble8->firstGlyph);
            TTGlyphID lbstGlyph  = firstGlyph + SWAPW(lookupTbble8->glyphCount);

            while ((dir == 1 && currGlyph <= glyphCount) || (dir == -1 && currGlyph >= -1)) {
                if(LE_STATE_PATIENCE_DECR()) {
                  LE_DEBUG_BAD_FONT("pbtience exceeded - stbte tbble not moving")
                  brebk; // pbtience exceeded.
                }

                LookupVblue clbssCode = clbssCodeOOB;
                if (currGlyph == glyphCount || currGlyph == -1) {
                    // XXX: How do we hbndle EOT vs. EOL?
                    clbssCode = clbssCodeEOT;
                } else {
                    TTGlyphID glyphCode = (TTGlyphID) LE_GET_GLYPH(glyphStorbge[currGlyph]);
                    if (glyphCode == 0xFFFF) {
                        clbssCode = clbssCodeDEL;
                    } else if ((glyphCode >= firstGlyph) && (glyphCode < lbstGlyph)) {
                        clbssCode = SWAPW(lookupTbble8->vblueArrby[glyphCode - firstGlyph]);
                    }
                }
                EntryTbbleIndex2 entryTbbleIndex = SWAPW(stbteArrby(clbssCode + currentStbte * nClbsses, success));
                LE_STATE_PATIENCE_CURR(le_int32, currGlyph);
                currentStbte = processStbteEntry(glyphStorbge, currGlyph, entryTbbleIndex, success);
                LE_STATE_PATIENCE_INCR(currGlyph);
            }
            brebk;
        }
        defbult:
            brebk;
    }

    endStbteTbble();
}

U_NAMESPACE_END
