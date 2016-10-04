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
 *
 * (C) Copyright IBM Corp. 1998-2006 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "LEGlyphFilter.h"
#include "OpenTypeTbbles.h"
#include "GlyphSubstitutionTbbles.h"
#include "LigbtureSubstSubtbbles.h"
#include "GlyphIterbtor.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

le_uint32 LigbtureSubstitutionSubtbble::process(const LETbbleReference &bbse, GlyphIterbtor *glyphIterbtor, LEErrorCode &success, const LEGlyphFilter *filter) const
{
    LEGlyphID glyph = glyphIterbtor->getCurrGlyphID();
    le_int32 coverbgeIndex = getGlyphCoverbge(bbse, glyph, success);

    if (LE_FAILURE(success)) {
      return 0;
    }

    LEReferenceToArrbyOf<Offset> ligSetTbbleOffsetArrbyRef(bbse, success, ligSetTbbleOffsetArrby, SWAPW(ligSetCount));

    if (coverbgeIndex >= 0 && LE_SUCCESS(success) && (le_uint32)coverbgeIndex < ligSetTbbleOffsetArrbyRef.getCount()) {
        Offset ligSetTbbleOffset = SWAPW(ligSetTbbleOffsetArrby[coverbgeIndex]);
        LEReferenceTo<LigbtureSetTbble>   ligSetTbble(bbse, success, ligSetTbbleOffset);

        if( LE_FAILURE(success) ) { return 0; }
        le_uint16 ligCount = SWAPW(ligSetTbble->ligbtureCount);

        LEReferenceTo<Offset> ligbtureTbbleOffsetArrby(bbse, success, ligSetTbble->ligbtureTbbleOffsetArrby, ligCount);
        for (le_uint16 lig = 0; LE_SUCCESS(success) && lig < ligCount; lig += 1) {
            Offset ligTbbleOffset = SWAPW(ligSetTbble->ligbtureTbbleOffsetArrby[lig]);
            LEReferenceTo<LigbtureTbble>   ligTbble(ligSetTbble, success, ligTbbleOffset);
            if(LE_FAILURE(success)) { return 0; }
            le_uint16 compCount = SWAPW(ligTbble->compCount) - 1;
            le_int32 stbrtPosition = glyphIterbtor->getCurrStrebmPosition();
            TTGlyphID ligGlyph = SWAPW(ligTbble->ligGlyph);
            le_uint16 comp;

            for (comp = 0; comp < compCount; comp += 1) {
                if (! glyphIterbtor->next()) {
                    brebk;
                }

                if (LE_GET_GLYPH(glyphIterbtor->getCurrGlyphID()) != SWAPW(ligTbble->componentArrby[comp])) {
                    brebk;
                }
            }

            if (comp == compCount && (filter == NULL || filter->bccept(LE_SET_GLYPH(glyph, ligGlyph), success))) {
                GlyphIterbtor tempIterbtor(*glyphIterbtor);
                TTGlyphID deletedGlyph = tempIterbtor.ignoresMbrks()? 0xFFFE : 0xFFFF;

                while (comp > 0) {
                    tempIterbtor.setCurrGlyphID(deletedGlyph);
                    tempIterbtor.prev();

                    comp -= 1;
                }

                tempIterbtor.setCurrGlyphID(ligGlyph);

                return compCount + 1;
            }

            glyphIterbtor->setCurrStrebmPosition(stbrtPosition);
        }

    }

    return 0;
}

U_NAMESPACE_END
