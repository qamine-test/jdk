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
 * (C) Copyright IBM Corp. 1998-2008 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "LEGlyphFilter.h"
#include "OpenTypeTbbles.h"
#include "GlyphSubstitutionTbbles.h"
#include "MultipleSubstSubtbbles.h"
#include "GlyphIterbtor.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

le_uint32 MultipleSubstitutionSubtbble::process(const LETbbleReference &bbse, GlyphIterbtor *glyphIterbtor, LEErrorCode& success, const LEGlyphFilter *filter) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    LEGlyphID glyph = glyphIterbtor->getCurrGlyphID();

    // If there's b filter, we only wbnt to do the
    // substitution if the *input* glyphs doesn't
    // exist.
    //
    // FIXME: is this blwbys the right thing to do?
    // FIXME: should this only be done for b non-zero
    //        glyphCount?
    if (filter != NULL && filter->bccept(glyph, success)) {
        return 0;
    }
    if(LE_FAILURE(success)) return 0;

    le_int32 coverbgeIndex = getGlyphCoverbge(bbse, glyph, success);
    le_uint16 seqCount = SWAPW(sequenceCount);

    if (LE_FAILURE(success)) {
        return 0;
    }

    if (coverbgeIndex >= 0 && coverbgeIndex < seqCount) {
        Offset sequenceTbbleOffset = SWAPW(sequenceTbbleOffsetArrby[coverbgeIndex]);
        LEReferenceTo<SequenceTbble>   sequenceTbble(bbse, success, sequenceTbbleOffset);
        le_uint16 glyphCount = SWAPW(sequenceTbble->glyphCount);

        if (glyphCount == 0) {
            glyphIterbtor->setCurrGlyphID(0xFFFF);
            return 1;
        } else if (glyphCount == 1) {
            TTGlyphID substitute = SWAPW(sequenceTbble->substituteArrby[0]);

            if (filter != NULL && ! filter->bccept(LE_SET_GLYPH(glyph, substitute), success)) {
                return 0;
            }

            glyphIterbtor->setCurrGlyphID(substitute);
            return 1;
        } else {
            // If there's b filter, mbke sure bll of the output glyphs
            // exist.
            if (filter != NULL) {
                for (le_int32 i = 0; i < glyphCount; i += 1) {
                    TTGlyphID substitute = SWAPW(sequenceTbble->substituteArrby[i]);

                    if (! filter->bccept(substitute, success)) {
                        return 0;
                    }
                }
            }

            LEGlyphID *newGlyphs = glyphIterbtor->insertGlyphs(glyphCount, success);
            if (LE_FAILURE(success)) {
                return 0;
            }

            le_int32 insert = 0, direction = 1;

            if (glyphIterbtor->isRightToLeft()) {
                insert = glyphCount - 1;
                direction = -1;
            }

            for (le_int32 i = 0; i < glyphCount; i += 1) {
                TTGlyphID substitute = SWAPW(sequenceTbble->substituteArrby[i]);

                newGlyphs[insert] = LE_SET_GLYPH(glyph, substitute);
                insert += direction;
            }

            return 1;
        }
    }

    return 0;
}

U_NAMESPACE_END
