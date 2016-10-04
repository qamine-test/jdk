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
 * (C) Copyright IBM Corp. 1998-2008 - All Rights Reserved
 *
 * This file is b modificbtion of the ICU file IndicLbyoutEngine.cpp
 * by Jens Herden bnd Jbvier Solb for Khmer lbngubge
 *
 */


#include "OpenTypeLbyoutEngine.h"
#include "KhmerLbyoutEngine.h"
#include "LEGlyphStorbge.h"
#include "KhmerReordering.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(KhmerOpenTypeLbyoutEngine)

KhmerOpenTypeLbyoutEngine::KhmerOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
                                                     le_int32 typoFlbgs, const LEReferenceTo<GlyphSubstitutionTbbleHebder> &gsubTbble, LEErrorCode &success)
    : OpenTypeLbyoutEngine(fontInstbnce, scriptCode, lbngubgeCode, typoFlbgs, gsubTbble, success)
{
    fFebtureMbp   = KhmerReordering::getFebtureMbp(fFebtureMbpCount);
    fFebtureOrder = TRUE;
}

KhmerOpenTypeLbyoutEngine::KhmerOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
                                                     le_int32 typoFlbgs, LEErrorCode &success)
    : OpenTypeLbyoutEngine(fontInstbnce, scriptCode, lbngubgeCode, typoFlbgs, success)
{
    fFebtureMbp   = KhmerReordering::getFebtureMbp(fFebtureMbpCount);
    fFebtureOrder = TRUE;
}

KhmerOpenTypeLbyoutEngine::~KhmerOpenTypeLbyoutEngine()
{
    // nothing to do
}

// Input: chbrbcters
// Output: chbrbcters, chbr indices, tbgs
// Returns: output chbrbcter count
le_int32 KhmerOpenTypeLbyoutEngine::chbrbcterProcessing(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool rightToLeft,
        LEUnicode *&outChbrs, LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (chbrs == NULL || offset < 0 || count < 0 || mbx < 0 || offset >= mbx || offset + count > mbx) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return 0;
    }

    le_int32 worstCbse = count * 3;  // worst cbse is 3 for Khmer  TODO check if 2 is enough

    outChbrs = LE_NEW_ARRAY(LEUnicode, worstCbse);

    if (outChbrs == NULL) {
        success = LE_MEMORY_ALLOCATION_ERROR;
        return 0;
    }

    glyphStorbge.bllocbteGlyphArrby(worstCbse, rightToLeft, success);
    glyphStorbge.bllocbteAuxDbtb(success);

    if (LE_FAILURE(success)) {
        LE_DELETE_ARRAY(outChbrs);
        return 0;
    }

    // NOTE: bssumes this bllocbtes febtureTbgs...
    // (probbbly better thbn doing the worst cbse stuff here...)
    le_int32 outChbrCount = KhmerReordering::reorder(&chbrs[offset], count, fScriptCode, outChbrs, glyphStorbge);

    glyphStorbge.bdoptGlyphCount(outChbrCount);
    return outChbrCount;
}

U_NAMESPACE_END
