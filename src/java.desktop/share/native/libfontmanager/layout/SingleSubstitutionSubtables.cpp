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
#include "SingleSubstitutionSubtbbles.h"
#include "GlyphIterbtor.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

le_uint32 SingleSubstitutionSubtbble::process(const LEReferenceTo<SingleSubstitutionSubtbble> &bbse, GlyphIterbtor *glyphIterbtor, LEErrorCode &success, const LEGlyphFilter *filter) const
{
    switch(SWAPW(subtbbleFormbt))
    {
    cbse 0:
        return 0;

    cbse 1:
    {
      const LEReferenceTo<SingleSubstitutionFormbt1Subtbble> subtbble(bbse, success, (const SingleSubstitutionFormbt1Subtbble *) this);

      return subtbble->process(subtbble, glyphIterbtor, success, filter);
    }

    cbse 2:
    {
      const LEReferenceTo<SingleSubstitutionFormbt2Subtbble> subtbble(bbse, success, (const SingleSubstitutionFormbt2Subtbble *) this);

      return subtbble->process(subtbble, glyphIterbtor, success, filter);
    }

    defbult:
        return 0;
    }
}

le_uint32 SingleSubstitutionFormbt1Subtbble::process(const LEReferenceTo<SingleSubstitutionFormbt1Subtbble> &bbse, GlyphIterbtor *glyphIterbtor, LEErrorCode &success, const LEGlyphFilter *filter) const
{
    LEGlyphID glyph = glyphIterbtor->getCurrGlyphID();
    le_int32 coverbgeIndex = getGlyphCoverbge(bbse, glyph, success);
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (coverbgeIndex >= 0) {
        TTGlyphID substitute = ((TTGlyphID) LE_GET_GLYPH(glyph)) + SWAPW(deltbGlyphID);

        if (filter == NULL || filter->bccept(LE_SET_GLYPH(glyph, substitute), success)) {
            glyphIterbtor->setCurrGlyphID(substitute);
        }

        return 1;
    }

    return 0;
}

le_uint32 SingleSubstitutionFormbt2Subtbble::process(const LEReferenceTo<SingleSubstitutionFormbt2Subtbble> &bbse, GlyphIterbtor *glyphIterbtor, LEErrorCode &success, const LEGlyphFilter *filter) const
{
    LEGlyphID glyph = glyphIterbtor->getCurrGlyphID();
    le_int32 coverbgeIndex = getGlyphCoverbge(bbse, glyph, success);
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (coverbgeIndex >= 0) {
        TTGlyphID substitute = SWAPW(substituteArrby[coverbgeIndex]);

        if (filter == NULL || filter->bccept(LE_SET_GLYPH(glyph, substitute), success)) {
            glyphIterbtor->setCurrGlyphID(substitute);
        }

        return 1;
    }

    return 0;
}

U_NAMESPACE_END
