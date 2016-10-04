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
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"
#include "ICUFebtures.h"
#include "Lookups.h"
#include "ScriptAndLbngubge.h"
#include "GlyphDefinitionTbbles.h"
#include "GlyphPositioningTbbles.h"
#include "SinglePositioningSubtbbles.h"
#include "PbirPositioningSubtbbles.h"
#include "CursiveAttbchmentSubtbbles.h"
#include "MbrkToBbsePosnSubtbbles.h"
#include "MbrkToLigbturePosnSubtbbles.h"
#include "MbrkToMbrkPosnSubtbbles.h"
//#include "ContextublPositioningSubtbbles.h"
#include "ContextublSubstSubtbbles.h"
#include "ExtensionSubtbbles.h"
#include "LookupProcessor.h"
#include "GlyphPosnLookupProc.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

// Aside from the nbmes, the contextubl positioning subtbbles bre
// the sbme bs the contextubl substitution subtbbles.
typedef ContextublSubstitutionSubtbble ContextublPositioningSubtbble;
typedef ChbiningContextublSubstitutionSubtbble ChbiningContextublPositioningSubtbble;

GlyphPositioningLookupProcessor::GlyphPositioningLookupProcessor(
        const LEReferenceTo<GlyphPositioningTbbleHebder> &glyphPositioningTbbleHebder,
        LETbg scriptTbg,
        LETbg lbngubgeTbg,
        const FebtureMbp *febtureMbp,
        le_int32 febtureMbpCount,
        le_bool febtureOrder,
        LEErrorCode& success)
    : LookupProcessor(
                      glyphPositioningTbbleHebder,
                      SWAPW(glyphPositioningTbbleHebder->scriptListOffset),
                      SWAPW(glyphPositioningTbbleHebder->febtureListOffset),
                      SWAPW(glyphPositioningTbbleHebder->lookupListOffset),
                      scriptTbg,
                      lbngubgeTbg,
                      febtureMbp,
                      febtureMbpCount,
                      febtureOrder,
                      success
                      )
{
    // bnything?
}

GlyphPositioningLookupProcessor::GlyphPositioningLookupProcessor()
{
}

le_uint32 GlyphPositioningLookupProcessor::bpplySubtbble(const LEReferenceTo<LookupSubtbble> &lookupSubtbble, le_uint16 lookupType,
                                                       GlyphIterbtor *glyphIterbtor,
                                                       const LEFontInstbnce *fontInstbnce,
                                                       LEErrorCode& success) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    le_uint32 deltb = 0;

    //_LETRACE("bttempting lookupType #%d", lookupType);

    switch(lookupType)
    {
    cbse 0:
        brebk;

    cbse gpstSingle:
    {
      LEReferenceTo<SinglePositioningSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, glyphIterbtor, fontInstbnce, success);
        brebk;
    }

    cbse gpstPbir:
    {
        LEReferenceTo<PbirPositioningSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, glyphIterbtor, fontInstbnce, success);
        brebk;
    }

    cbse gpstCursive:
    {
        LEReferenceTo<CursiveAttbchmentSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, glyphIterbtor, fontInstbnce, success);
        brebk;
    }

    cbse gpstMbrkToBbse:
    {
        LEReferenceTo<MbrkToBbsePositioningSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, glyphIterbtor, fontInstbnce, success);
        brebk;
    }

     cbse gpstMbrkToLigbture:
    {
        LEReferenceTo<MbrkToLigbturePositioningSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, glyphIterbtor, fontInstbnce, success);
        brebk;
    }

    cbse gpstMbrkToMbrk:
    {
        LEReferenceTo<MbrkToMbrkPositioningSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, glyphIterbtor, fontInstbnce, success);
        brebk;
    }

   cbse gpstContext:
    {
        LEReferenceTo<ContextublPositioningSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, this , glyphIterbtor, fontInstbnce, success);
        brebk;
    }

    cbse gpstChbinedContext:
    {
        const LEReferenceTo<ChbiningContextublPositioningSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, this, glyphIterbtor, fontInstbnce, success);
        brebk;
    }

    cbse gpstExtension:
    {
        const LEReferenceTo<ExtensionSubtbble> subtbble(lookupSubtbble, success);

        deltb = subtbble->process(subtbble, this, lookupType, glyphIterbtor, fontInstbnce, success);
        brebk;
    }

    defbult:
        brebk;
    }

#if LE_TRACE
    if(deltb != 0) {
      _LETRACE("GlyphPositioningLookupProcessor bpplied #%d -> deltb %d @ %d", lookupType, deltb, glyphIterbtor->getCurrStrebmPosition());
    }
#endif

    return deltb;
}

GlyphPositioningLookupProcessor::~GlyphPositioningLookupProcessor()
{
}

U_NAMESPACE_END
