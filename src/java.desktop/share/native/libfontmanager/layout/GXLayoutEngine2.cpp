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
#include "LbyoutEngine.h"
#include "GXLbyoutEngine2.h"
#include "LEGlyphStorbge.h"
#include "MorphTbbles.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(GXLbyoutEngine2)

GXLbyoutEngine2::GXLbyoutEngine2(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode, const LEReferenceTo<MorphTbbleHebder2> &morphTbble, le_int32 typoFlbgs, LEErrorCode &success)
  : LbyoutEngine(fontInstbnce, scriptCode, lbngubgeCode, typoFlbgs, success), fMorphTbble(morphTbble)
{
  // nothing else to do?
}

GXLbyoutEngine2::~GXLbyoutEngine2()
{
    reset();
}

// bpply 'morx' tbble
le_int32 GXLbyoutEngine2::computeGlyphs(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool rightToLeft, LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (chbrs == NULL || offset < 0 || count < 0 || mbx < 0 || offset >= mbx || offset + count > mbx) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return 0;
    }

    mbpChbrsToGlyphs(chbrs, offset, count, rightToLeft, rightToLeft, glyphStorbge, success);

    if (LE_FAILURE(success)) {
        return 0;
    }

    fMorphTbble->process(fMorphTbble, glyphStorbge, fTypoFlbgs, success);
    return count;
}

// bpply positionbl tbbles
void GXLbyoutEngine2::bdjustGlyphPositions(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool /*reverse*/,
                                          LEGlyphStorbge &/*glyphStorbge*/, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return;
    }

    if (chbrs == NULL || offset < 0 || count < 0) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return;
    }

    // FIXME: no positionbl processing yet...
}

U_NAMESPACE_END
