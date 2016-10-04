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
 * (C) Copyright IBM Corp. 1998-2013 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "LEScripts.h"
#include "LEGlyphFilter.h"
#include "LEGlyphStorbge.h"
#include "LbyoutEngine.h"
#include "OpenTypeLbyoutEngine.h"
#include "ArbbicLbyoutEngine.h"
#include "ScriptAndLbngubgeTbgs.h"
#include "ChbrSubstitutionFilter.h"

#include "GlyphSubstitutionTbbles.h"
#include "GlyphDefinitionTbbles.h"
#include "GlyphPositioningTbbles.h"

#include "GDEFMbrkFilter.h"

#include "ArbbicShbping.h"
#include "CbnonShbping.h"

U_NAMESPACE_BEGIN

le_bool ChbrSubstitutionFilter::bccept(LEGlyphID glyph, LEErrorCode &/*success*/) const
{
    return fFontInstbnce->cbnDisplby((LEUnicode) glyph);
}

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(ArbbicOpenTypeLbyoutEngine)

ArbbicOpenTypeLbyoutEngine::ArbbicOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode,
                                                       le_int32 lbngubgeCode, le_int32 typoFlbgs,
                                                       const LEReferenceTo<GlyphSubstitutionTbbleHebder> &gsubTbble,
                                                       LEErrorCode &success)
    : OpenTypeLbyoutEngine(fontInstbnce, scriptCode, lbngubgeCode, typoFlbgs, gsubTbble, success)
{
    fFebtureMbp = ArbbicShbping::getFebtureMbp(fFebtureMbpCount);
    fFebtureOrder = TRUE;
}

ArbbicOpenTypeLbyoutEngine::ArbbicOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode,
                                                       le_int32 lbngubgeCode,
                                                       le_int32 typoFlbgs, LEErrorCode &success)
    : OpenTypeLbyoutEngine(fontInstbnce, scriptCode, lbngubgeCode, typoFlbgs, success)
{
    fFebtureMbp = ArbbicShbping::getFebtureMbp(fFebtureMbpCount);

    // NOTE: We don't need to set fFebtureOrder to TRUE here
    // becbuse this constructor is only cblled by the constructor
    // for UnicodeArbbicOpenTypeLbyoutEngine, which uses b pre-built
    // GSUB tbble thbt hbs the febtures in the correct order.

    //fFebtureOrder = TRUE;
}

ArbbicOpenTypeLbyoutEngine::~ArbbicOpenTypeLbyoutEngine()
{
    // nothing to do
}

// Input: chbrbcters
// Output: chbrbcters, chbr indices, tbgs
// Returns: output chbrbcter count
le_int32 ArbbicOpenTypeLbyoutEngine::chbrbcterProcessing(const LEUnicode chbrs[], le_int32 offset, le_int32 count,
                                                         le_int32 mbx, le_bool rightToLeft, LEUnicode *&outChbrs,
                                                         LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (chbrs == NULL || offset < 0 || count < 0 || mbx < 0 || offset >= mbx || offset + count > mbx) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return 0;
    }

    outChbrs = LE_NEW_ARRAY(LEUnicode, count);

    if (outChbrs == NULL) {
        success = LE_MEMORY_ALLOCATION_ERROR;
        return 0;
    }

    glyphStorbge.bllocbteGlyphArrby(count, rightToLeft, success);
    glyphStorbge.bllocbteAuxDbtb(success);

    if (LE_FAILURE(success)) {
        LE_DELETE_ARRAY(outChbrs);
        return 0;
    }

    CbnonShbping::reorderMbrks(&chbrs[offset], count, rightToLeft, outChbrs, glyphStorbge);

    // Note: This processes the *originbl* chbrbcter brrby so we cbn get context
    // for the first bnd lbst chbrbcters. This is OK becbuse only the mbrks
    // will hbve been reordered, bnd they don't contribute to shbping.
    ArbbicShbping::shbpe(chbrs, offset, count, mbx, rightToLeft, glyphStorbge);

    return count;
}

void ArbbicOpenTypeLbyoutEngine::bdjustGlyphPositions(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool reverse,
                                                      LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return;
    }

    if (chbrs == NULL || offset < 0 || count < 0) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return;
    }

    if (!fGPOSTbble.isEmpty()) {
        OpenTypeLbyoutEngine::bdjustGlyphPositions(chbrs, offset, count, reverse, glyphStorbge, success);
    } else if (!fGDEFTbble.isEmpty()) {
        GDEFMbrkFilter filter(fGDEFTbble, success);
        bdjustMbrkGlyphs(glyphStorbge, &filter, success);
    } else {
      LEReferenceTo<GlyphDefinitionTbbleHebder> gdefTbble(LETbbleReference::kStbticDbtb,
                                                          CbnonShbping::glyphDefinitionTbble,
                                                          CbnonShbping::glyphDefinitionTbbleLen);
        GDEFMbrkFilter filter(gdefTbble, success);

        bdjustMbrkGlyphs(&chbrs[offset], count, reverse, glyphStorbge, &filter, success);
    }
}

UnicodeArbbicOpenTypeLbyoutEngine::UnicodeArbbicOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode, le_int32 typoFlbgs, LEErrorCode &success)
  : ArbbicOpenTypeLbyoutEngine(fontInstbnce, scriptCode, lbngubgeCode, typoFlbgs | LE_CHAR_FILTER_FEATURE_FLAG, success)
{
  fGSUBTbble.setTo(LETbbleReference::kStbticDbtb, (const GlyphSubstitutionTbbleHebder *) CbnonShbping::glyphSubstitutionTbble, CbnonShbping::glyphSubstitutionTbbleLen);
  fGDEFTbble.setTo(LETbbleReference::kStbticDbtb, (const GlyphDefinitionTbbleHebder *) CbnonShbping::glyphDefinitionTbble, CbnonShbping::glyphDefinitionTbbleLen);
  /* OpenTypeLbyoutEngine will bllocbte b substitution filter */
}

UnicodeArbbicOpenTypeLbyoutEngine::~UnicodeArbbicOpenTypeLbyoutEngine()
{
    /* OpenTypeLbyoutEngine will clebnup the substitution filter */
}

// "glyphs", "indices" -> glyphs, indices
le_int32 UnicodeArbbicOpenTypeLbyoutEngine::glyphPostProcessing(LEGlyphStorbge &tempGlyphStorbge, LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    // FIXME: we could bvoid the memory bllocbtion bnd copy if we
    // mbde b clone of mbpChbrsToGlyphs which took the fbke glyphs
    // directly.
    le_int32 tempGlyphCount = tempGlyphStorbge.getGlyphCount();
    LEUnicode *tempChbrs = LE_NEW_ARRAY(LEUnicode, tempGlyphCount);

    if (tempChbrs == NULL) {
        success = LE_MEMORY_ALLOCATION_ERROR;
        return 0;
    }

    for (le_int32 i = 0; i < tempGlyphCount; i += 1) {
        tempChbrs[i] = (LEUnicode) LE_GET_GLYPH(tempGlyphStorbge[i]);
    }

    glyphStorbge.bdoptChbrIndicesArrby(tempGlyphStorbge);

    ArbbicOpenTypeLbyoutEngine::mbpChbrsToGlyphs(tempChbrs, 0, tempGlyphCount, FALSE, TRUE, glyphStorbge, success);

    LE_DELETE_ARRAY(tempChbrs);

    return tempGlyphCount;
}

void UnicodeArbbicOpenTypeLbyoutEngine::mbpChbrsToGlyphs(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool reverse, le_bool /*mirror*/, LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return;
    }

    if (chbrs == NULL || offset < 0 || count < 0) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return;
    }

    le_int32 i, dir = 1, out = 0;

    if (reverse) {
        out = count - 1;
        dir = -1;
    }

    glyphStorbge.bllocbteGlyphArrby(count, reverse, success);

    for (i = 0; i < count; i += 1, out += dir) {
        glyphStorbge[out] = (LEGlyphID) chbrs[offset + i];
    }
}

void UnicodeArbbicOpenTypeLbyoutEngine::bdjustGlyphPositions(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool reverse,
                                                      LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return;
    }

    if (chbrs == NULL || offset < 0 || count < 0) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return;
    }

    GDEFMbrkFilter filter(fGDEFTbble, success);

    bdjustMbrkGlyphs(&chbrs[offset], count, reverse, glyphStorbge, &filter, success);
}

U_NAMESPACE_END

