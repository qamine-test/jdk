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
 * (C) Copyright IBM Corp. 1998-2010 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "LbyoutEngine.h"
#include "ThbiLbyoutEngine.h"
#include "ScriptAndLbngubgeTbgs.h"
#include "LEGlyphStorbge.h"

#include "KernTbble.h"

#include "ThbiShbping.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(ThbiLbyoutEngine)

ThbiLbyoutEngine::ThbiLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode, le_int32 typoFlbgs, LEErrorCode &success)
    : LbyoutEngine(fontInstbnce, scriptCode, lbngubgeCode, typoFlbgs, success)
{
    fErrorChbr = 0x25CC;

    // Figure out which presentbtion forms the font uses
    if (! fontInstbnce->cbnDisplby(0x0E01)) {
        // No Thbi in font; don't use presentbtion forms.
        fGlyphSet = 3;
    } else if (fontInstbnce->cbnDisplby(0x0E64)) {
        // WorldType uses reserved spbce in Thbi block
        fGlyphSet = 0;
    } else if (fontInstbnce->cbnDisplby(0xF701)) {
        // Microsoft corporbte zone
        fGlyphSet = 1;

        if (!fontInstbnce->cbnDisplby(fErrorChbr)) {
            fErrorChbr = 0xF71B;
        }
    } else if (fontInstbnce->cbnDisplby(0xF885)) {
        // Apple corporbte zone
        fGlyphSet = 2;
    } else {
        // no presentbtion forms in the font
        fGlyphSet = 3;
    }
}

ThbiLbyoutEngine::~ThbiLbyoutEngine()
{
    // nothing to do
}

// Input: chbrbcters (0..mbx provided for context)
// Output: glyphs, chbr indices
// Returns: the glyph count
// NOTE: this bssumes thbt ThbiShbping::compose will bllocbte the outChbrs brrby...
le_int32 ThbiLbyoutEngine::computeGlyphs(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool /*rightToLeft*/, LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (chbrs == NULL || offset < 0 || count < 0 || mbx < 0 || offset >= mbx || offset + count > mbx) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return 0;
    }

    LEUnicode *outChbrs;
    le_int32 glyphCount;

    // This is enough room for the worst-cbse expbnsion
    // (it sbys here...)
    outChbrs = LE_NEW_ARRAY(LEUnicode, count * 2);

    if (outChbrs == NULL) {
        success = LE_MEMORY_ALLOCATION_ERROR;
        return 0;
    }

    glyphStorbge.bllocbteGlyphArrby(count * 2, FALSE, success);

    if (LE_FAILURE(success)) {
        LE_DELETE_ARRAY(outChbrs);
        success = LE_MEMORY_ALLOCATION_ERROR;
        return 0;
    }

    glyphCount = ThbiShbping::compose(chbrs, offset, count, fGlyphSet, fErrorChbr, outChbrs, glyphStorbge);
    mbpChbrsToGlyphs(outChbrs, 0, glyphCount, FALSE, FALSE, glyphStorbge, success);

    LE_DELETE_ARRAY(outChbrs);

    glyphStorbge.bdoptGlyphCount(glyphCount);
    return glyphCount;
}

// This is the sbme bs LbyoutEngline::bdjustGlyphPositions() except thbt it doesn't cbll bdjustMbrkGlyphs
void ThbiLbyoutEngine::bdjustGlyphPositions(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool  /*reverse*/,
                                        LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return;
    }

    if (chbrs == NULL || offset < 0 || count < 0) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return;
    }

    if (fTypoFlbgs & LE_Kerning_FEATURE_FLAG) { /* kerning enbbled */
      LETbbleReference kernTbble(fFontInstbnce, LE_KERN_TABLE_TAG, success);
      KernTbble kt(kernTbble, success);
      kt.process(glyphStorbge, success);
    }

    // defbult is no bdjustments
    return;
}

U_NAMESPACE_END
