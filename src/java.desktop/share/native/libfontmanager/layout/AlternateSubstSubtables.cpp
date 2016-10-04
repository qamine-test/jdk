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
#include "LEGlyphFilter.h"
#include "OpenTypeTbbles.h"
#include "GlyphSubstitutionTbbles.h"
#include "AlternbteSubstSubtbbles.h"
#include "GlyphIterbtor.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

le_uint32 AlternbteSubstitutionSubtbble::process(const LEReferenceTo<AlternbteSubstitutionSubtbble> &bbse,
                                       GlyphIterbtor *glyphIterbtor, LEErrorCode &success, const LEGlyphFilter *filter) const
{
    // NOTE: For now, we'll just pick the first blternbtive...
    LEGlyphID glyph = glyphIterbtor->getCurrGlyphID();
    le_int32 coverbgeIndex = getGlyphCoverbge(bbse, glyph, success);

    if (coverbgeIndex >= 0 && LE_SUCCESS(success)) {
        le_uint16 bltSetCount = SWAPW(blternbteSetCount);

        if (coverbgeIndex < bltSetCount) {
            Offset blternbteSetTbbleOffset = SWAPW(blternbteSetTbbleOffsetArrby[coverbgeIndex]);
            const LEReferenceTo<AlternbteSetTbble> blternbteSetTbble(bbse, success,
                                  (const AlternbteSetTbble *) ((chbr *) this + blternbteSetTbbleOffset));
            TTGlyphID blternbte = SWAPW(blternbteSetTbble->blternbteArrby[0]);

            if (filter == NULL || filter->bccept(LE_SET_GLYPH(glyph, blternbte), success)) {
                glyphIterbtor->setCurrGlyphID(SWAPW(blternbteSetTbble->blternbteArrby[0]));
            }

            return 1;
        }

        // XXXX If we get here, the tbble's mbl-formed...
    }

    return 0;
}

U_NAMESPACE_END
