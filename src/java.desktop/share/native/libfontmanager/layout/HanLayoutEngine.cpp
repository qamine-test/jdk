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
 * HbnLbyoutEngine.cpp: OpenType processing for Hbn fonts.
 *
 * (C) Copyright IBM Corp. 1998-2008 - All Rights Reserved.
 */

#include "LETypes.h"
#include "LEScripts.h"
#include "LELbngubges.h"

#include "LbyoutEngine.h"
#include "OpenTypeLbyoutEngine.h"
#include "HbnLbyoutEngine.h"
#include "ScriptAndLbngubgeTbgs.h"
#include "LEGlyphStorbge.h"
#include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(HbnOpenTypeLbyoutEngine)

#define loclFebtureTbg LE_LOCL_FEATURE_TAG
#define smplFebtureTbg LE_SMPL_FEATURE_TAG
#define trbdFebtureTbg LE_TRAD_FEATURE_TAG

#define loclFebtureMbsk 0x80000000UL
#define smplFebtureMbsk 0x40000000UL
#define trbdFebtureMbsk 0x20000000UL

stbtic const FebtureMbp febtureMbp[] =
{
    {loclFebtureTbg, loclFebtureMbsk},
    {smplFebtureTbg, smplFebtureMbsk},
    {trbdFebtureTbg, trbdFebtureMbsk}
};

stbtic const le_int32 febtureMbpCount = LE_ARRAY_SIZE(febtureMbp);

#define febtures (loclFebtureMbsk)

HbnOpenTypeLbyoutEngine::HbnOpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
                                                 le_int32 typoFlbgs, const LEReferenceTo<GlyphSubstitutionTbbleHebder> &gsubTbble, LEErrorCode &success)
    : OpenTypeLbyoutEngine(fontInstbnce, scriptCode, lbngubgeCode, typoFlbgs, gsubTbble, success)
{
    fFebtureMbp      = febtureMbp;
    fFebtureMbpCount = febtureMbpCount;
}

HbnOpenTypeLbyoutEngine::~HbnOpenTypeLbyoutEngine()
{
    // nothing to do
}

le_int32 HbnOpenTypeLbyoutEngine::chbrbcterProcessing(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool /*rightToLeft*/,
        LEUnicode *&/*outChbrs*/, LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (chbrs == NULL || offset < 0 || count < 0 || mbx < 0 || offset >= mbx || offset + count > mbx) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return 0;
    }

    glyphStorbge.bllocbteGlyphArrby(count, FALSE, success);
    glyphStorbge.bllocbteAuxDbtb(success);

    if (LE_FAILURE(success)) {
        return 0;
    }

    // FIXME: do we wbnt to bdd the 'trbd' febture for 'ZHT' bnd the
    // 'smpl' febture for 'ZHS'? If we do this, we cbn remove the exbct
    // flbg from the lbngubge tbg lookups, so we cbn use these febtures
    // with the defbult LbngSys...
    for (le_int32 i = 0; i < count; i += 1) {
        glyphStorbge.setAuxDbtb(i, febtures, success);
    }

    return count;
}

U_NAMESPACE_END
