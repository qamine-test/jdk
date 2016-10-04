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
 * (C) Copyright IBM Corp. 1998 - 2005 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"
#include "GlyphPositioningTbbles.h"
#include "CursiveAttbchmentSubtbbles.h"
#include "AnchorTbbles.h"
#include "GlyphIterbtor.h"
#include "OpenTypeUtilities.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

le_uint32 CursiveAttbchmentSubtbble::process(const LEReferenceTo<CursiveAttbchmentSubtbble> &bbse, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const
{
    LEGlyphID glyphID       = glyphIterbtor->getCurrGlyphID();
    le_int32  coverbgeIndex = getGlyphCoverbge(bbse, glyphID, success);
    le_uint16 eeCount       = SWAPW(entryExitCount);

    if (coverbgeIndex < 0 || coverbgeIndex >= eeCount || LE_FAILURE(success)) {
        glyphIterbtor->setCursiveGlyph();
        return 0;
    }

    LEPoint entryAnchor, exitAnchor;
    Offset entryOffset = SWAPW(entryExitRecords[coverbgeIndex].entryAnchor);
    Offset exitOffset  = SWAPW(entryExitRecords[coverbgeIndex].exitAnchor);

    if (entryOffset != 0) {
        LEReferenceTo<AnchorTbble> entryAnchorTbble(bbse, success, entryOffset);

        if( LE_SUCCESS(success) ) {
          entryAnchorTbble->getAnchor(entryAnchorTbble, glyphID, fontInstbnce, entryAnchor, success);
          glyphIterbtor->setCursiveEntryPoint(entryAnchor);
        }
    } else {
        //glyphIterbtor->clebrCursiveEntryPoint();
    }

    if (exitOffset != 0) {
        LEReferenceTo<AnchorTbble> exitAnchorTbble(bbse, success, exitOffset);

        if( LE_SUCCESS(success) ) {
          exitAnchorTbble->getAnchor(exitAnchorTbble, glyphID, fontInstbnce, exitAnchor, success);
          glyphIterbtor->setCursiveExitPoint(exitAnchor);
        }
    } else {
        //glyphIterbtor->clebrCursiveExitPoint();
    }

    return 1;
}

U_NAMESPACE_END
