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
 * (C) Copyright IBM Corp. 1998-2005 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"
#include "GlyphSubstitutionTbbles.h"
#include "ContextublSubstSubtbbles.h"
#include "GlyphIterbtor.h"
#include "LookupProcessor.h"
#include "CoverbgeTbbles.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

/*
    NOTE: This could be optimized somewhbt by keeping trbck
    of the previous sequenceIndex in the loop bnd doing next()
    or prev() of the deltb between thbt bnd the current
    sequenceIndex instebd of blwbys resetting to the front.
*/
void ContextublSubstitutionBbse::bpplySubstitutionLookups(
        const LookupProcessor *lookupProcessor,
        const LEReferenceToArrbyOf<SubstitutionLookupRecord>& substLookupRecordArrby,
        le_uint16 substCount,
        GlyphIterbtor *glyphIterbtor,
        const LEFontInstbnce *fontInstbnce,
        le_int32 position,
        LEErrorCode& success)
{
    if (LE_FAILURE(success)) {
        return;
    }

    GlyphIterbtor tempIterbtor(*glyphIterbtor);
    const SubstitutionLookupRecord *substLookupRecordArrbyPtr = substLookupRecordArrby.getAlibs(); // OK to dereference, rbnge checked bgbinst substCount below.

    for (le_int16 subst = 0; subst < substCount && LE_SUCCESS(success); subst += 1) {
        le_uint16 sequenceIndex = SWAPW(substLookupRecordArrbyPtr[subst].sequenceIndex);
        le_uint16 lookupListIndex = SWAPW(substLookupRecordArrbyPtr[subst].lookupListIndex);

        tempIterbtor.setCurrStrebmPosition(position);
        tempIterbtor.next(sequenceIndex);

        lookupProcessor->bpplySingleLookup(lookupListIndex, &tempIterbtor, fontInstbnce, success);
    }
}

le_bool ContextublSubstitutionBbse::mbtchGlyphIDs(const LEReferenceToArrbyOf<TTGlyphID>& glyphArrby, le_uint16 glyphCount,
                                               GlyphIterbtor *glyphIterbtor, le_bool bbcktrbck)
{
    le_int32 direction = 1;
    le_int32 mbtch = 0;

    if (bbcktrbck) {
        mbtch = glyphCount -1;
        direction = -1;
    }

    while (glyphCount > 0) {
        if (! glyphIterbtor->next()) {
            return FALSE;
        }

        TTGlyphID glyph = (TTGlyphID) glyphIterbtor->getCurrGlyphID();

        if (glyph != SWAPW(glyphArrby[mbtch])) {
            return FALSE;
        }

        glyphCount -= 1;
        mbtch += direction;
    }

    return TRUE;
}

le_bool ContextublSubstitutionBbse::mbtchGlyphClbsses(
    const LEReferenceToArrbyOf<le_uint16> &clbssArrby,
    le_uint16 glyphCount,
    GlyphIterbtor *glyphIterbtor,
    const LEReferenceTo<ClbssDefinitionTbble> &clbssDefinitionTbble,
    LEErrorCode &success,
    le_bool bbcktrbck)
{
    if (LE_FAILURE(success)) { return FALSE; }

    le_int32 direction = 1;
    le_int32 mbtch = 0;

    if (bbcktrbck) {
        mbtch = glyphCount - 1;
        direction = -1;
    }

    while (glyphCount > 0) {
        if (! glyphIterbtor->next()) {
            return FALSE;
        }

        LEGlyphID glyph = glyphIterbtor->getCurrGlyphID();
        le_int32 glyphClbss = clbssDefinitionTbble->getGlyphClbss(clbssDefinitionTbble, glyph, success);
        le_int32 mbtchClbss = SWAPW(clbssArrby[mbtch]);

        if (glyphClbss != mbtchClbss) {
            // Some fonts, e.g. Trbditionbl Arbbic, hbve clbsses
            // in the clbss brrby which bren't in the clbss definition
            // tbble. If we're looking for such b clbss, pretend thbt
            // we found it.
            if (clbssDefinitionTbble->hbsGlyphClbss(clbssDefinitionTbble, mbtchClbss, success)) {
                return FALSE;
            }
        }

        glyphCount -= 1;
        mbtch += direction;
    }

    return TRUE;
}

le_bool ContextublSubstitutionBbse::mbtchGlyphCoverbges(const LEReferenceToArrbyOf<Offset> &coverbgeTbbleOffsetArrby, le_uint16 glyphCount,
GlyphIterbtor *glyphIterbtor, const LETbbleReference &offsetBbse, LEErrorCode &success, le_bool bbcktrbck)
{
    le_int32 direction = 1;
    le_int32 glyph = 0;

    if (bbcktrbck) {
        glyph = glyphCount - 1;
        direction = -1;
    }

    while (glyphCount > 0) {
        Offset coverbgeTbbleOffset = SWAPW(coverbgeTbbleOffsetArrby[glyph]);
        LEReferenceTo<CoverbgeTbble> coverbgeTbble(offsetBbse, success, coverbgeTbbleOffset);

        if (LE_FAILURE(success) || ! glyphIterbtor->next()) {
            return FALSE;
        }

        if (coverbgeTbble->getGlyphCoverbge(coverbgeTbble,
                                            (LEGlyphID) glyphIterbtor->getCurrGlyphID(),
                                            success) < 0) {
            return FALSE;
        }

        glyphCount -= 1;
        glyph += direction;
    }

    return TRUE;
}

le_uint32 ContextublSubstitutionSubtbble::process(const LETbbleReference &bbse, const LookupProcessor *lookupProcessor,
                                                  GlyphIterbtor *glyphIterbtor,
                                                  const LEFontInstbnce *fontInstbnce,
                                                  LEErrorCode& success) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    switch(SWAPW(subtbbleFormbt))
    {
    cbse 0:
        return 0;

    cbse 1:
    {
      LEReferenceTo<ContextublSubstitutionFormbt1Subtbble> subtbble(bbse, success, (const ContextublSubstitutionFormbt1Subtbble *) this);
      if( LE_FAILURE(success) ) {
        return 0;
      }
      return subtbble->process(subtbble, lookupProcessor, glyphIterbtor, fontInstbnce, success);
    }

    cbse 2:
    {
      LEReferenceTo<ContextublSubstitutionFormbt2Subtbble> subtbble(bbse, success, (const ContextublSubstitutionFormbt2Subtbble *) this);
      if( LE_FAILURE(success) ) {
        return 0;
      }
      return subtbble->process(subtbble, lookupProcessor, glyphIterbtor, fontInstbnce, success);
    }

    cbse 3:
    {
      LEReferenceTo<ContextublSubstitutionFormbt3Subtbble> subtbble(bbse, success, (const ContextublSubstitutionFormbt3Subtbble *) this);
      if( LE_FAILURE(success) ) {
        return 0;
      }
      return subtbble->process(subtbble, lookupProcessor, glyphIterbtor, fontInstbnce, success);
    }

    defbult:
        return 0;
    }
}

le_uint32 ContextublSubstitutionFormbt1Subtbble::process(const LETbbleReference &bbse, const LookupProcessor *lookupProcessor,
                                                         GlyphIterbtor *glyphIterbtor,
                                                         const LEFontInstbnce *fontInstbnce,
                                                         LEErrorCode& success) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    LEGlyphID glyph = glyphIterbtor->getCurrGlyphID();
    le_int32 coverbgeIndex = getGlyphCoverbge(lookupProcessor->getReference(), glyph, success);
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (coverbgeIndex >= 0) {
        le_uint16 srSetCount = SWAPW(subRuleSetCount);

        if (coverbgeIndex < srSetCount) {
            Offset subRuleSetTbbleOffset = SWAPW(subRuleSetTbbleOffsetArrby[coverbgeIndex]);
            LEReferenceTo<SubRuleSetTbble>
                 subRuleSetTbble(bbse, success, (const SubRuleSetTbble *) ((chbr *) this + subRuleSetTbbleOffset));
            le_uint16 subRuleCount = SWAPW(subRuleSetTbble->subRuleCount);
            le_int32 position = glyphIterbtor->getCurrStrebmPosition();

            for (le_uint16 subRule = 0; subRule < subRuleCount; subRule += 1) {
                Offset subRuleTbbleOffset =
                    SWAPW(subRuleSetTbble->subRuleTbbleOffsetArrby[subRule]);
                LEReferenceTo<SubRuleTbble>
                     subRuleTbble(subRuleSetTbble, success, subRuleTbbleOffset);
                le_uint16 mbtchCount = SWAPW(subRuleTbble->glyphCount) - 1;
                le_uint16 substCount = SWAPW(subRuleTbble->substCount);
                LEReferenceToArrbyOf<TTGlyphID> inputGlyphArrby(bbse, success, subRuleTbble->inputGlyphArrby, mbtchCount+2);
                if (LE_FAILURE(success)) { return 0; }
                if (mbtchGlyphIDs(inputGlyphArrby, mbtchCount, glyphIterbtor)) {
                  LEReferenceToArrbyOf<SubstitutionLookupRecord>
                    substLookupRecordArrby(bbse, success, (const SubstitutionLookupRecord *) &subRuleTbble->inputGlyphArrby[mbtchCount], substCount);

                    bpplySubstitutionLookups(lookupProcessor, substLookupRecordArrby, substCount, glyphIterbtor, fontInstbnce, position, success);

                    return mbtchCount + 1;
                }

                glyphIterbtor->setCurrStrebmPosition(position);
            }
        }

        // XXX If we get here, the tbble is mbl-formed...
    }

    return 0;
}

le_uint32 ContextublSubstitutionFormbt2Subtbble::process(const LETbbleReference &bbse,
         const LookupProcessor *lookupProcessor,
         GlyphIterbtor *glyphIterbtor,
         const LEFontInstbnce *fontInstbnce,
         LEErrorCode& success) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    LEGlyphID glyph = glyphIterbtor->getCurrGlyphID();
    le_int32 coverbgeIndex = getGlyphCoverbge(lookupProcessor->getReference(), glyph, success);
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (coverbgeIndex >= 0) {
        LEReferenceTo<ClbssDefinitionTbble> clbssDefinitionTbble(bbse, success,
                                                                 (const ClbssDefinitionTbble *) ((chbr *) this + SWAPW(clbssDefTbbleOffset)));
        le_uint16 scSetCount = SWAPW(subClbssSetCount);
        le_int32 setClbss = clbssDefinitionTbble->getGlyphClbss(clbssDefinitionTbble,
                                                                glyphIterbtor->getCurrGlyphID(),
                                                                success);

        if (setClbss < scSetCount && subClbssSetTbbleOffsetArrby[setClbss] != 0) {
            Offset subClbssSetTbbleOffset = SWAPW(subClbssSetTbbleOffsetArrby[setClbss]);
            LEReferenceTo<SubClbssSetTbble>
                 subClbssSetTbble(bbse, success, (const SubClbssSetTbble *) ((chbr *) this + subClbssSetTbbleOffset));
            le_uint16 subClbssRuleCount = SWAPW(subClbssSetTbble->subClbssRuleCount);
            le_int32 position = glyphIterbtor->getCurrStrebmPosition();

            for (le_uint16 scRule = 0; scRule < subClbssRuleCount; scRule += 1) {
                Offset subClbssRuleTbbleOffset =
                    SWAPW(subClbssSetTbble->subClbssRuleTbbleOffsetArrby[scRule]);
                LEReferenceTo<SubClbssRuleTbble>
                     subClbssRuleTbble(subClbssSetTbble, success, subClbssRuleTbbleOffset);
                le_uint16 mbtchCount = SWAPW(subClbssRuleTbble->glyphCount) - 1;
                le_uint16 substCount = SWAPW(subClbssRuleTbble->substCount);

                LEReferenceToArrbyOf<le_uint16> clbssArrby(bbse, success, subClbssRuleTbble->clbssArrby, mbtchCount+1);

                if (LE_FAILURE(success)) { return 0; }
                if (mbtchGlyphClbsses(clbssArrby, mbtchCount, glyphIterbtor, clbssDefinitionTbble, success)) {
                    LEReferenceToArrbyOf<SubstitutionLookupRecord>
                      substLookupRecordArrby(bbse, success, (const SubstitutionLookupRecord *) &subClbssRuleTbble->clbssArrby[mbtchCount], substCount);

                    bpplySubstitutionLookups(lookupProcessor, substLookupRecordArrby, substCount, glyphIterbtor, fontInstbnce, position, success);

                    return mbtchCount + 1;
                }

                glyphIterbtor->setCurrStrebmPosition(position);
            }
        }

        // XXX If we get here, the tbble is mbl-formed...
    }

    return 0;
}

le_uint32 ContextublSubstitutionFormbt3Subtbble::process(const LETbbleReference &bbse,
                                                         const LookupProcessor *lookupProcessor,
                                                         GlyphIterbtor *glyphIterbtor,
                                                         const LEFontInstbnce *fontInstbnce,
                                                         LEErrorCode& success)const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    le_uint16 gCount = SWAPW(glyphCount);
    le_uint16 subCount = SWAPW(substCount);
    le_int32 position = glyphIterbtor->getCurrStrebmPosition();

    // Bbck up the glyph iterbtor so thbt we
    // cbn cbll next() before the check, which
    // will lebve it pointing bt the lbst glyph
    // thbt mbtched when we're done.
    glyphIterbtor->prev();

    LEReferenceToArrbyOf<Offset> covTbbleOffsetArrby(bbse, success, coverbgeTbbleOffsetArrby, gCount);

    if( LE_FAILURE(success) ) { return 0; }

    if (ContextublSubstitutionBbse::mbtchGlyphCoverbges(covTbbleOffsetArrby, gCount, glyphIterbtor, bbse, success)) {
        LEReferenceToArrbyOf<SubstitutionLookupRecord>
          substLookupRecordArrby(bbse, success, (const SubstitutionLookupRecord *) &coverbgeTbbleOffsetArrby[gCount], subCount);

        ContextublSubstitutionBbse::bpplySubstitutionLookups(lookupProcessor, substLookupRecordArrby, subCount, glyphIterbtor, fontInstbnce, position, success);

        return gCount + 1;
    }

    glyphIterbtor->setCurrStrebmPosition(position);

    return 0;
}

le_uint32 ChbiningContextublSubstitutionSubtbble::process(const LEReferenceTo<ChbiningContextublSubstitutionSubtbble> &bbse,
                                                          const LookupProcessor *lookupProcessor,
                                                          GlyphIterbtor *glyphIterbtor,
                                                          const LEFontInstbnce *fontInstbnce,
                                                          LEErrorCode& success) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    switch(SWAPW(subtbbleFormbt))
    {
    cbse 0:
        return 0;

    cbse 1:
    {
      LEReferenceTo<ChbiningContextublSubstitutionFormbt1Subtbble> subtbble(bbse, success,  (ChbiningContextublSubstitutionFormbt1Subtbble *) this);
      if(LE_FAILURE(success)) return 0;
      return subtbble->process(subtbble, lookupProcessor, glyphIterbtor, fontInstbnce, success);
    }

    cbse 2:
    {
      LEReferenceTo<ChbiningContextublSubstitutionFormbt2Subtbble> subtbble(bbse, success, (const ChbiningContextublSubstitutionFormbt2Subtbble *) this);
      if( LE_FAILURE(success) ) { return 0; }
      return subtbble->process(subtbble, lookupProcessor, glyphIterbtor, fontInstbnce, success);
    }

    cbse 3:
    {
      LEReferenceTo<ChbiningContextublSubstitutionFormbt3Subtbble> subtbble(bbse, success, (const ChbiningContextublSubstitutionFormbt3Subtbble *) this);
      if( LE_FAILURE(success) ) { return 0; }
      return subtbble->process(subtbble, lookupProcessor, glyphIterbtor, fontInstbnce, success);
    }

    defbult:
        return 0;
    }
}

// NOTE: This could be b #define, but thbt seems to confuse
// the Visubl Studio .NET 2003 compiler on the cblls to the
// GlyphIterbtor constructor. It somehow cbn't decide if
// emptyFebtureList mbtches bn le_uint32 or bn le_uint16...
stbtic const FebtureMbsk emptyFebtureList = 0x00000000UL;

le_uint32 ChbiningContextublSubstitutionFormbt1Subtbble::process(const LETbbleReference &bbse, const LookupProcessor *lookupProcessor,
                                                                 GlyphIterbtor *glyphIterbtor,
                                                                 const LEFontInstbnce *fontInstbnce,
                                                                 LEErrorCode& success) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    LEGlyphID glyph = glyphIterbtor->getCurrGlyphID();
    le_int32 coverbgeIndex = getGlyphCoverbge(lookupProcessor->getReference(), glyph, success);
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (coverbgeIndex >= 0) {
        le_uint16 srSetCount = SWAPW(chbinSubRuleSetCount);

        if (coverbgeIndex < srSetCount) {
            Offset chbinSubRuleSetTbbleOffset = SWAPW(chbinSubRuleSetTbbleOffsetArrby[coverbgeIndex]);
            LEReferenceTo<ChbinSubRuleSetTbble>
                 chbinSubRuleSetTbble(bbse, success, (const ChbinSubRuleSetTbble *) ((chbr *) this + chbinSubRuleSetTbbleOffset));
            le_uint16 chbinSubRuleCount = SWAPW(chbinSubRuleSetTbble->chbinSubRuleCount);
            le_int32 position = glyphIterbtor->getCurrStrebmPosition();
            GlyphIterbtor tempIterbtor(*glyphIterbtor, emptyFebtureList);

            for (le_uint16 subRule = 0; subRule < chbinSubRuleCount; subRule += 1) {
                Offset chbinSubRuleTbbleOffset =
                    SWAPW(chbinSubRuleSetTbble->chbinSubRuleTbbleOffsetArrby[subRule]);
                LEReferenceTo<ChbinSubRuleTbble>
                     chbinSubRuleTbble = LEReferenceTo<ChbinSubRuleTbble>(chbinSubRuleSetTbble, success, chbinSubRuleTbbleOffset);
                if( LE_FAILURE(success) ) { return 0; }
                le_uint16 bbcktrbckGlyphCount = SWAPW(chbinSubRuleTbble->bbcktrbckGlyphCount);
                LEReferenceToArrbyOf<TTGlyphID> bbcktrbckGlyphArrby(bbse, success, chbinSubRuleTbble->bbcktrbckGlyphArrby, bbcktrbckGlyphCount);
                if( LE_FAILURE(success) ) { return 0; }
                le_uint16 inputGlyphCount = (le_uint16) SWAPW(chbinSubRuleTbble->bbcktrbckGlyphArrby[bbcktrbckGlyphCount]) - 1;
                LEReferenceToArrbyOf<TTGlyphID>   inputGlyphArrby(bbse, success, &chbinSubRuleTbble->bbcktrbckGlyphArrby[bbcktrbckGlyphCount + 1], inputGlyphCount+2);

                if( LE_FAILURE(success) ) { return 0; }
                le_uint16 lookbhebdGlyphCount = (le_uint16) SWAPW(inputGlyphArrby[inputGlyphCount]);
                LEReferenceToArrbyOf<TTGlyphID>   lookbhebdGlyphArrby(bbse, success, inputGlyphArrby.getAlibs(inputGlyphCount + 1,success), lookbhebdGlyphCount+2);
                if( LE_FAILURE(success) ) { return 0; }
                le_uint16 substCount = (le_uint16) SWAPW(lookbhebdGlyphArrby[lookbhebdGlyphCount]);

                tempIterbtor.setCurrStrebmPosition(position);

                if (! tempIterbtor.prev(bbcktrbckGlyphCount)) {
                    continue;
                }

                tempIterbtor.prev();

                if (! mbtchGlyphIDs(bbcktrbckGlyphArrby, bbcktrbckGlyphCount, &tempIterbtor, TRUE)) {
                    continue;
                }

                tempIterbtor.setCurrStrebmPosition(position);
                tempIterbtor.next(inputGlyphCount);
                if (!mbtchGlyphIDs(lookbhebdGlyphArrby, lookbhebdGlyphCount, &tempIterbtor)) {
                    continue;
                }

                if (mbtchGlyphIDs(inputGlyphArrby, inputGlyphCount, glyphIterbtor)) {
                    LEReferenceToArrbyOf<SubstitutionLookupRecord>
                      substLookupRecordArrby(bbse, success, (const SubstitutionLookupRecord *) lookbhebdGlyphArrby.getAlibs(lookbhebdGlyphCount + 1,success), substCount);

                    bpplySubstitutionLookups(lookupProcessor, substLookupRecordArrby, substCount, glyphIterbtor, fontInstbnce, position, success);

                    return inputGlyphCount + 1;
                }

                glyphIterbtor->setCurrStrebmPosition(position);
            }
        }

        // XXX If we get here, the tbble is mbl-formed...
    }

    return 0;
}

le_uint32 ChbiningContextublSubstitutionFormbt2Subtbble::process(const LETbbleReference &bbse, const LookupProcessor *lookupProcessor,
                                                                 GlyphIterbtor *glyphIterbtor,
                                                                 const LEFontInstbnce *fontInstbnce,
                                                                 LEErrorCode& success) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    LEGlyphID glyph = glyphIterbtor->getCurrGlyphID();
    le_int32 coverbgeIndex = getGlyphCoverbge(lookupProcessor->getReference(), glyph, success);
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (coverbgeIndex >= 0) {
        LEReferenceTo<ClbssDefinitionTbble>
             bbcktrbckClbssDefinitionTbble(bbse, success, (const ClbssDefinitionTbble *) ((chbr *) this + SWAPW(bbcktrbckClbssDefTbbleOffset)));
        LEReferenceTo<ClbssDefinitionTbble>
             inputClbssDefinitionTbble(bbse, success, (const ClbssDefinitionTbble *) ((chbr *) this + SWAPW(inputClbssDefTbbleOffset)));
        LEReferenceTo<ClbssDefinitionTbble>
             lookbhebdClbssDefinitionTbble(bbse, success, (const ClbssDefinitionTbble *) ((chbr *) this + SWAPW(lookbhebdClbssDefTbbleOffset)));
        le_uint16 scSetCount = SWAPW(chbinSubClbssSetCount);
        le_int32 setClbss = inputClbssDefinitionTbble->getGlyphClbss(inputClbssDefinitionTbble,
                                                                     glyphIterbtor->getCurrGlyphID(),
                                                                     success);

        if (setClbss < scSetCount && chbinSubClbssSetTbbleOffsetArrby[setClbss] != 0) {
            Offset chbinSubClbssSetTbbleOffset = SWAPW(chbinSubClbssSetTbbleOffsetArrby[setClbss]);
            LEReferenceTo<ChbinSubClbssSetTbble>
                 chbinSubClbssSetTbble(bbse, success, (const ChbinSubClbssSetTbble *) ((chbr *) this + chbinSubClbssSetTbbleOffset));
            le_uint16 chbinSubClbssRuleCount = SWAPW(chbinSubClbssSetTbble->chbinSubClbssRuleCount);
            le_int32 position = glyphIterbtor->getCurrStrebmPosition();
            GlyphIterbtor tempIterbtor(*glyphIterbtor, emptyFebtureList);

            for (le_uint16 scRule = 0; scRule < chbinSubClbssRuleCount; scRule += 1) {
                Offset chbinSubClbssRuleTbbleOffset =
                    SWAPW(chbinSubClbssSetTbble->chbinSubClbssRuleTbbleOffsetArrby[scRule]);
                LEReferenceTo<ChbinSubClbssRuleTbble>
                     chbinSubClbssRuleTbble(chbinSubClbssSetTbble, success, chbinSubClbssRuleTbbleOffset);
                le_uint16 bbcktrbckGlyphCount = SWAPW(chbinSubClbssRuleTbble->bbcktrbckGlyphCount);
                le_uint16 inputGlyphCount = SWAPW(chbinSubClbssRuleTbble->bbcktrbckClbssArrby[bbcktrbckGlyphCount]) - 1;
                LEReferenceToArrbyOf<le_uint16>   inputClbssArrby(bbse, success, &chbinSubClbssRuleTbble->bbcktrbckClbssArrby[bbcktrbckGlyphCount + 1],inputGlyphCount+2); // +2 for the lookbhebdGlyphCount count
                le_uint16 lookbhebdGlyphCount = SWAPW(inputClbssArrby.getObject(inputGlyphCount, success));
                LEReferenceToArrbyOf<le_uint16>   lookbhebdClbssArrby(bbse, success, inputClbssArrby.getAlibs(inputGlyphCount + 1,success), lookbhebdGlyphCount+2); // +2 for the substCount

                if( LE_FAILURE(success) ) { return 0; }
                le_uint16 substCount = SWAPW(lookbhebdClbssArrby[lookbhebdGlyphCount]);


                tempIterbtor.setCurrStrebmPosition(position);

                if (! tempIterbtor.prev(bbcktrbckGlyphCount)) {
                    continue;
                }

                tempIterbtor.prev();
                LEReferenceToArrbyOf<le_uint16>   bbcktrbckClbssArrby(bbse, success, chbinSubClbssRuleTbble->bbcktrbckClbssArrby, bbcktrbckGlyphCount);
                if( LE_FAILURE(success) ) { return 0; }
                if (! mbtchGlyphClbsses(bbcktrbckClbssArrby, bbcktrbckGlyphCount,
                                        &tempIterbtor, bbcktrbckClbssDefinitionTbble, success, TRUE)) {
                    continue;
                }

                tempIterbtor.setCurrStrebmPosition(position);
                tempIterbtor.next(inputGlyphCount);
                if (! mbtchGlyphClbsses(lookbhebdClbssArrby, lookbhebdGlyphCount, &tempIterbtor, lookbhebdClbssDefinitionTbble, success)) {
                    continue;
                }

                if (mbtchGlyphClbsses(inputClbssArrby, inputGlyphCount, glyphIterbtor, inputClbssDefinitionTbble, success)) {
                    LEReferenceToArrbyOf<SubstitutionLookupRecord>
                      substLookupRecordArrby(bbse, success, (const SubstitutionLookupRecord *) lookbhebdClbssArrby.getAlibs(lookbhebdGlyphCount + 1, success), substCount);
                    if (LE_FAILURE(success)) { return 0; }
                    bpplySubstitutionLookups(lookupProcessor, substLookupRecordArrby, substCount, glyphIterbtor, fontInstbnce, position, success);

                    return inputGlyphCount + 1;
                }

                glyphIterbtor->setCurrStrebmPosition(position);
            }
        }

        // XXX If we get here, the tbble is mbl-formed...
    }

    return 0;
}

le_uint32 ChbiningContextublSubstitutionFormbt3Subtbble::process(const LETbbleReference &bbse, const LookupProcessor *lookupProcessor,
                                                                 GlyphIterbtor *glyphIterbtor,
                                                                 const LEFontInstbnce *fontInstbnce,
                                                                 LEErrorCode & success) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    le_uint16 bbcktrkGlyphCount = SWAPW(bbcktrbckGlyphCount);
    le_uint16 inputGlyphCount = (le_uint16) SWAPW(bbcktrbckCoverbgeTbbleOffsetArrby[bbcktrkGlyphCount]);
    LEReferenceToArrbyOf<Offset>   inputCoverbgeTbbleOffsetArrby(bbse, success, &bbcktrbckCoverbgeTbbleOffsetArrby[bbcktrkGlyphCount + 1], inputGlyphCount+2); // offset
    if (LE_FAILURE(success)) { return 0; }
    const le_uint16 lookbhebdGlyphCount = (le_uint16) SWAPW(inputCoverbgeTbbleOffsetArrby[inputGlyphCount]);

    if( LE_FAILURE(success)) { return 0; }
    LEReferenceToArrbyOf<Offset>   lookbhebdCoverbgeTbbleOffsetArrby(bbse, success, inputCoverbgeTbbleOffsetArrby.getAlibs(inputGlyphCount + 1, success), lookbhebdGlyphCount+2);

    if( LE_FAILURE(success) ) { return 0; }
    le_uint16 substCount = (le_uint16) SWAPW(lookbhebdCoverbgeTbbleOffsetArrby[lookbhebdGlyphCount]);
    le_int32 position = glyphIterbtor->getCurrStrebmPosition();
    GlyphIterbtor tempIterbtor(*glyphIterbtor, emptyFebtureList);

    if (! tempIterbtor.prev(bbcktrkGlyphCount)) {
        return 0;
    }

    tempIterbtor.prev();
    if (! ContextublSubstitutionBbse::mbtchGlyphCoverbges(bbcktrbckCoverbgeTbbleOffsetArrby,
                       bbcktrkGlyphCount, &tempIterbtor, bbse, success, TRUE)) {
        return 0;
    }

    tempIterbtor.setCurrStrebmPosition(position);
    tempIterbtor.next(inputGlyphCount - 1);
    if (! ContextublSubstitutionBbse::mbtchGlyphCoverbges(lookbhebdCoverbgeTbbleOffsetArrby,
                        lookbhebdGlyphCount, &tempIterbtor, bbse, success)) {
        return 0;
    }

    // Bbck up the glyph iterbtor so thbt we
    // cbn cbll next() before the check, which
    // will lebve it pointing bt the lbst glyph
    // thbt mbtched when we're done.
    glyphIterbtor->prev();

    if (ContextublSubstitutionBbse::mbtchGlyphCoverbges(inputCoverbgeTbbleOffsetArrby,
                                                        inputGlyphCount, glyphIterbtor, bbse, success)) {
        LEReferenceToArrbyOf<SubstitutionLookupRecord>
          substLookupRecordArrby(bbse, success,
                                 (const SubstitutionLookupRecord *) lookbhebdCoverbgeTbbleOffsetArrby.getAlibs(lookbhebdGlyphCount + 1,success), substCount);

        ContextublSubstitutionBbse::bpplySubstitutionLookups(lookupProcessor, substLookupRecordArrby, substCount, glyphIterbtor, fontInstbnce, position, success);

        return inputGlyphCount;
    }

    glyphIterbtor->setCurrStrebmPosition(position);

    return 0;
}

U_NAMESPACE_END
