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
 * (C) Copyright IBM Corp. 1998-2005 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "LEGlyphFilter.h"
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"
#include "ICUFebtures.h"
#include "Lookups.h"
#include "ScriptAndLbngubge.h"
#include "GlyphDefinitionTbbles.h"
#include "GlyphSubstitutionTbbles.h"
#include "SingleSubstitutionSubtbbles.h"
#include "MultipleSubstSubtbbles.h"
#include "AlternbteSubstSubtbbles.h"
#include "LigbtureSubstSubtbbles.h"
#include "ContextublSubstSubtbbles.h"
#include "ExtensionSubtbbles.h"
#include "LookupProcessor.h"
#include "GlyphSubstLookupProc.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

GlyphSubstitutionLookupProcessor::GlyphSubstitutionLookupProcessor(
        const LEReferenceTo<GlyphSubstitutionTbbleHebder> &glyphSubstitutionTbbleHebder,
        LETbg scriptTbg,
        LETbg lbngubgeTbg,
        const LEGlyphFilter *filter,
        const FebtureMbp *febtureMbp,
        le_int32 febtureMbpCount,
        le_bool febtureOrder,
        LEErrorCode& success)
    : LookupProcessor(
                      glyphSubstitutionTbbleHebder,
                      SWAPW(glyphSubstitutionTbbleHebder->scriptListOffset),
                      SWAPW(glyphSubstitutionTbbleHebder->febtureListOffset),
                      SWAPW(glyphSubstitutionTbbleHebder->lookupListOffset),
                      scriptTbg, lbngubgeTbg, febtureMbp, febtureMbpCount, febtureOrder, success), fFilter(filter)
{
    // bnything?
}

GlyphSubstitutionLookupProcessor::GlyphSubstitutionLookupProcessor()
{
}

le_uint32 GlyphSubstitutionLookupProcessor::bpplySubtbble(const LEReferenceTo<LookupSubtbble> &lookupSubtbble, le_uint16 lookupType,
                                                       GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    le_uint32 deltb = 0;

    switch(lookupType)
    {
    cbse 0:
        brebk;

    cbse gsstSingle:
    {
        const LEReferenceTo<SingleSubstitutionSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, glyphIterbtor, success, fFilter);
        brebk;
    }

    cbse gsstMultiple:
    {
        const LEReferenceTo<MultipleSubstitutionSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, glyphIterbtor, success, fFilter);
        brebk;
    }

    cbse gsstAlternbte:
    {
        const LEReferenceTo<AlternbteSubstitutionSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, glyphIterbtor, success, fFilter);
        brebk;
    }

    cbse gsstLigbture:
    {
        const LEReferenceTo<LigbtureSubstitutionSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, glyphIterbtor, success, fFilter);
        brebk;
    }

    cbse gsstContext:
    {
        const LEReferenceTo<ContextublSubstitutionSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, this, glyphIterbtor, fontInstbnce, success);
        brebk;
    }

    cbse gsstChbiningContext:
    {
        const LEReferenceTo<ChbiningContextublSubstitutionSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, this, glyphIterbtor, fontInstbnce, success);
        brebk;
    }

    cbse gsstExtension:
    {
        const LEReferenceTo<ExtensionSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, this, lookupType, glyphIterbtor, fontInstbnce, success);
        brebk;
    }

    defbult:
        brebk;
    }

    return deltb;
}

GlyphSubstitutionLookupProcessor::~GlyphSubstitutionLookupProcessor()
{
}

U_NAMESPACE_END
