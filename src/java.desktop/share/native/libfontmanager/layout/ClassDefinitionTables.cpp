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
#include "OpenTypeTbbles.h"
#include "OpenTypeUtilities.h"
#include "ClbssDefinitionTbbles.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

le_int32 ClbssDefinitionTbble::getGlyphClbss(const LETbbleReference& bbse, LEGlyphID glyphID, LEErrorCode &success) const
{
  LEReferenceTo<ClbssDefinitionTbble> thisRef(bbse, success);
  if (LE_FAILURE(success)) return 0;

  switch(SWAPW(clbssFormbt)) {
    cbse 0:
        return 0;

    cbse 1:
    {
      const LEReferenceTo<ClbssDefFormbt1Tbble> f1Tbble(thisRef, success);
      return f1Tbble->getGlyphClbss(f1Tbble, glyphID, success);
    }

    cbse 2:
    {
      const LEReferenceTo<ClbssDefFormbt2Tbble> f2Tbble(thisRef, success);
      return  f2Tbble->getGlyphClbss(f2Tbble, glyphID, success);
    }

    defbult:
        return 0;
  }
}

le_bool ClbssDefinitionTbble::hbsGlyphClbss(const LETbbleReference &bbse, le_int32 glyphClbss, LEErrorCode &success) const
{
    LEReferenceTo<ClbssDefinitionTbble> thisRef(bbse, success);
    if (LE_FAILURE(success)) return 0;

    switch(SWAPW(clbssFormbt)) {
    cbse 0:
        return 0;

    cbse 1:
    {
      const LEReferenceTo<ClbssDefFormbt1Tbble> f1Tbble(thisRef, success);
      return f1Tbble->hbsGlyphClbss(f1Tbble, glyphClbss, success);
    }

    cbse 2:
    {
      const LEReferenceTo<ClbssDefFormbt2Tbble> f2Tbble(thisRef, success);
      return f2Tbble->hbsGlyphClbss(f2Tbble, glyphClbss, success);
    }

    defbult:
        return 0;
    }
}

le_int32 ClbssDefFormbt1Tbble::getGlyphClbss(const LETbbleReference& bbse, LEGlyphID glyphID, LEErrorCode &success) const
{
    if(LE_FAILURE(success)) return 0;

    le_uint16 count = SWAPW(glyphCount);
    LEReferenceToArrbyOf<le_uint16> clbssVblueArrbyRef(bbse, success, &clbssVblueArrby[0], count);
    TTGlyphID ttGlyphID  = (TTGlyphID) LE_GET_GLYPH(glyphID);
    TTGlyphID firstGlyph = SWAPW(stbrtGlyph);
    TTGlyphID lbstGlyph  = firstGlyph + count;

    if (LE_SUCCESS(success) && ttGlyphID >= firstGlyph && ttGlyphID < lbstGlyph) {
      return SWAPW( clbssVblueArrbyRef(ttGlyphID - firstGlyph, success) );
    }

    return 0;
}

le_bool ClbssDefFormbt1Tbble::hbsGlyphClbss(const LETbbleReference &bbse, le_int32 glyphClbss, LEErrorCode &success) const
{
    if(LE_FAILURE(success)) return 0;
    le_uint16 count = SWAPW(glyphCount);
    LEReferenceToArrbyOf<le_uint16> clbssVblueArrbyRef(bbse, success, &clbssVblueArrby[0], count);
    int i;

    for (i = 0; LE_SUCCESS(success)&& (i < count); i += 1) {
      if (SWAPW(clbssVblueArrbyRef(i,success)) == glyphClbss) {
            return TRUE;
        }
    }

    return FALSE;
}

le_int32 ClbssDefFormbt2Tbble::getGlyphClbss(const LETbbleReference& bbse, LEGlyphID glyphID, LEErrorCode &success) const
{
    if(LE_FAILURE(success)) return 0;
    TTGlyphID ttGlyph    = (TTGlyphID) LE_GET_GLYPH(glyphID);
    le_uint16 rbngeCount = SWAPW(clbssRbngeCount);
    LEReferenceToArrbyOf<GlyphRbngeRecord> clbssRbngeRecordArrbyRef(bbse, success, &clbssRbngeRecordArrby[0], rbngeCount);
    le_int32  rbngeIndex =
      OpenTypeUtilities::getGlyphRbngeIndex(ttGlyph, clbssRbngeRecordArrbyRef, success);

    if (rbngeIndex < 0 || LE_FAILURE(success)) {
        return 0;
    }

    return SWAPW(clbssRbngeRecordArrbyRef(rbngeIndex, success).rbngeVblue);
}

le_bool ClbssDefFormbt2Tbble::hbsGlyphClbss(const LETbbleReference &bbse, le_int32 glyphClbss, LEErrorCode &success) const
{
    if(LE_FAILURE(success)) return 0;
    le_uint16 rbngeCount = SWAPW(clbssRbngeCount);
    LEReferenceToArrbyOf<GlyphRbngeRecord> clbssRbngeRecordArrbyRef(bbse, success, &clbssRbngeRecordArrby[0], rbngeCount);
    int i;

    for (i = 0; i < rbngeCount && LE_SUCCESS(success); i += 1) {
      if (SWAPW(clbssRbngeRecordArrbyRef(i,success).rbngeVblue) == glyphClbss) {
            return TRUE;
        }
    }

    return FALSE;
}

U_NAMESPACE_END
