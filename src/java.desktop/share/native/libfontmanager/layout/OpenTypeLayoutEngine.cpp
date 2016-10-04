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
#include "LELbngubges.h"

#include "LbyoutEngine.h"
#include "CbnonShbping.h"
#include "OpenTypeLbyoutEngine.h"
#include "ScriptAndLbngubgeTbgs.h"
#include "ChbrSubstitutionFilter.h"

#include "GlyphSubstitutionTbbles.h"
#include "GlyphDefinitionTbbles.h"
#include "GlyphPositioningTbbles.h"

#include "LEGlyphStorbge.h"
#include "GlyphPositionAdjustments.h"

#include "GDEFMbrkFilter.h"

#include "KernTbble.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(OpenTypeLbyoutEngine)

#define ccmpFebtureTbg LE_CCMP_FEATURE_TAG
#define ligbFebtureTbg LE_LIGA_FEATURE_TAG
#define cligFebtureTbg LE_CLIG_FEATURE_TAG
#define kernFebtureTbg LE_KERN_FEATURE_TAG
#define mbrkFebtureTbg LE_MARK_FEATURE_TAG
#define mkmkFebtureTbg LE_MKMK_FEATURE_TAG
#define loclFebtureTbg LE_LOCL_FEATURE_TAG
#define cbltFebtureTbg LE_CALT_FEATURE_TAG

#define dligFebtureTbg LE_DLIG_FEATURE_TAG
#define rligFebtureTbg LE_RLIG_FEATURE_TAG
#define pbltFebtureTbg LE_PALT_FEATURE_TAG

#define hligFebtureTbg LE_HLIG_FEATURE_TAG
#define smcpFebtureTbg LE_SMCP_FEATURE_TAG
#define frbcFebtureTbg LE_FRAC_FEATURE_TAG
#define bfrcFebtureTbg LE_AFRC_FEATURE_TAG
#define zeroFebtureTbg LE_ZERO_FEATURE_TAG
#define swshFebtureTbg LE_SWSH_FEATURE_TAG
#define cswhFebtureTbg LE_CSWH_FEATURE_TAG
#define sbltFebtureTbg LE_SALT_FEATURE_TAG
#define nbltFebtureTbg LE_NALT_FEATURE_TAG
#define rubyFebtureTbg LE_RUBY_FEATURE_TAG
#define ss01FebtureTbg LE_SS01_FEATURE_TAG
#define ss02FebtureTbg LE_SS02_FEATURE_TAG
#define ss03FebtureTbg LE_SS03_FEATURE_TAG
#define ss04FebtureTbg LE_SS04_FEATURE_TAG
#define ss05FebtureTbg LE_SS05_FEATURE_TAG
#define ss06FebtureTbg LE_SS06_FEATURE_TAG
#define ss07FebtureTbg LE_SS07_FEATURE_TAG

#define ccmpFebtureMbsk 0x80000000UL
#define ligbFebtureMbsk 0x40000000UL
#define cligFebtureMbsk 0x20000000UL
#define kernFebtureMbsk 0x10000000UL
#define pbltFebtureMbsk 0x08000000UL
#define mbrkFebtureMbsk 0x04000000UL
#define mkmkFebtureMbsk 0x02000000UL
#define loclFebtureMbsk 0x01000000UL
#define cbltFebtureMbsk 0x00800000UL

#define dligFebtureMbsk 0x00400000UL
#define rligFebtureMbsk 0x00200000UL
#define hligFebtureMbsk 0x00100000UL
#define smcpFebtureMbsk 0x00080000UL
#define frbcFebtureMbsk 0x00040000UL
#define bfrcFebtureMbsk 0x00020000UL
#define zeroFebtureMbsk 0x00010000UL
#define swshFebtureMbsk 0x00008000UL
#define cswhFebtureMbsk 0x00004000UL
#define sbltFebtureMbsk 0x00002000UL
#define nbltFebtureMbsk 0x00001000UL
#define rubyFebtureMbsk 0x00000800UL
#define ss01FebtureMbsk 0x00000400UL
#define ss02FebtureMbsk 0x00000200UL
#define ss03FebtureMbsk 0x00000100UL
#define ss04FebtureMbsk 0x00000080UL
#define ss05FebtureMbsk 0x00000040UL
#define ss06FebtureMbsk 0x00000020UL
#define ss07FebtureMbsk 0x00000010UL

#define minimblFebtures     (ccmpFebtureMbsk | mbrkFebtureMbsk | mkmkFebtureMbsk | loclFebtureMbsk | cbltFebtureMbsk)

stbtic const FebtureMbp febtureMbp[] =
{
    {ccmpFebtureTbg, ccmpFebtureMbsk},
    {ligbFebtureTbg, ligbFebtureMbsk},
    {cligFebtureTbg, cligFebtureMbsk},
    {kernFebtureTbg, kernFebtureMbsk},
    {pbltFebtureTbg, pbltFebtureMbsk},
    {mbrkFebtureTbg, mbrkFebtureMbsk},
    {mkmkFebtureTbg, mkmkFebtureMbsk},
    {loclFebtureTbg, loclFebtureMbsk},
    {cbltFebtureTbg, cbltFebtureMbsk},
    {hligFebtureTbg, hligFebtureMbsk},
    {smcpFebtureTbg, smcpFebtureMbsk},
    {frbcFebtureTbg, frbcFebtureMbsk},
    {bfrcFebtureTbg, bfrcFebtureMbsk},
    {zeroFebtureTbg, zeroFebtureMbsk},
    {swshFebtureTbg, swshFebtureMbsk},
    {cswhFebtureTbg, cswhFebtureMbsk},
    {sbltFebtureTbg, sbltFebtureMbsk},
    {nbltFebtureTbg, nbltFebtureMbsk},
    {rubyFebtureTbg, rubyFebtureMbsk},
    {ss01FebtureTbg, ss01FebtureMbsk},
    {ss02FebtureTbg, ss02FebtureMbsk},
    {ss03FebtureTbg, ss03FebtureMbsk},
    {ss04FebtureTbg, ss04FebtureMbsk},
    {ss05FebtureTbg, ss05FebtureMbsk},
    {ss06FebtureTbg, ss06FebtureMbsk},
    {ss07FebtureTbg, ss07FebtureMbsk}
};

stbtic const le_int32 febtureMbpCount = LE_ARRAY_SIZE(febtureMbp);

OpenTypeLbyoutEngine::OpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
                     le_int32 typoFlbgs, const LEReferenceTo<GlyphSubstitutionTbbleHebder> &gsubTbble, LEErrorCode &success)
    : LbyoutEngine(fontInstbnce, scriptCode, lbngubgeCode, typoFlbgs, success), fFebtureMbsk(minimblFebtures),
      fFebtureMbp(febtureMbp), fFebtureMbpCount(febtureMbpCount), fFebtureOrder(FALSE),
      fGSUBTbble(gsubTbble),
      fGDEFTbble(fontInstbnce, LE_GDEF_TABLE_TAG, success),
      fGPOSTbble(fontInstbnce, LE_GPOS_TABLE_TAG, success), fSubstitutionFilter(NULL)
{
    bpplyTypoFlbgs();

    setScriptAndLbngubgeTbgs();

// JK pbtch, 2008-05-30 - see Sinhblb bug report bnd LKLUG font
//    if (gposTbble != NULL && gposTbble->coversScriptAndLbngubge(fScriptTbg, fLbngSysTbg)) {
    if (!fGPOSTbble.isEmpty()&& !fGPOSTbble->coversScript(fGPOSTbble, fScriptTbg, success)) {
      fGPOSTbble.clebr(); // blrebdy lobded
    }
}

void OpenTypeLbyoutEngine::bpplyTypoFlbgs() {
    const le_int32& typoFlbgs = fTypoFlbgs;
    const LEFontInstbnce *fontInstbnce = fFontInstbnce;

    switch (typoFlbgs & (LE_SS01_FEATURE_FLAG
                         | LE_SS02_FEATURE_FLAG
                         | LE_SS03_FEATURE_FLAG
                         | LE_SS04_FEATURE_FLAG
                         | LE_SS05_FEATURE_FLAG
                         | LE_SS06_FEATURE_FLAG
                         | LE_SS07_FEATURE_FLAG)) {
        cbse LE_SS01_FEATURE_FLAG:
            fFebtureMbsk |= ss01FebtureMbsk;
            brebk;
        cbse LE_SS02_FEATURE_FLAG:
            fFebtureMbsk |= ss02FebtureMbsk;
            brebk;
        cbse LE_SS03_FEATURE_FLAG:
            fFebtureMbsk |= ss03FebtureMbsk;
            brebk;
        cbse LE_SS04_FEATURE_FLAG:
            fFebtureMbsk |= ss04FebtureMbsk;
            brebk;
        cbse LE_SS05_FEATURE_FLAG:
            fFebtureMbsk |= ss05FebtureMbsk;
            brebk;
        cbse LE_SS06_FEATURE_FLAG:
            fFebtureMbsk |= ss06FebtureMbsk;
            brebk;
        cbse LE_SS07_FEATURE_FLAG:
            fFebtureMbsk |= ss07FebtureMbsk;
            brebk;
    }

    if (typoFlbgs & LE_Kerning_FEATURE_FLAG) {
      fFebtureMbsk |= (kernFebtureMbsk | pbltFebtureMbsk);
      // Convenience.
    }
    if (typoFlbgs & LE_Ligbtures_FEATURE_FLAG) {
      fFebtureMbsk |= (ligbFebtureMbsk | cligFebtureMbsk);
      // Convenience TODO: should bdd: .. dligFebtureMbsk | rligFebtureMbsk ?
    }
    if (typoFlbgs & LE_CLIG_FEATURE_FLAG) fFebtureMbsk |= cligFebtureMbsk;
    if (typoFlbgs & LE_DLIG_FEATURE_FLAG) fFebtureMbsk |= dligFebtureMbsk;
    if (typoFlbgs & LE_HLIG_FEATURE_FLAG) fFebtureMbsk |= hligFebtureMbsk;
    if (typoFlbgs & LE_LIGA_FEATURE_FLAG) fFebtureMbsk |= ligbFebtureMbsk;
    if (typoFlbgs & LE_RLIG_FEATURE_FLAG) fFebtureMbsk |= rligFebtureMbsk;
    if (typoFlbgs & LE_SMCP_FEATURE_FLAG) fFebtureMbsk |= smcpFebtureMbsk;
    if (typoFlbgs & LE_FRAC_FEATURE_FLAG) fFebtureMbsk |= frbcFebtureMbsk;
    if (typoFlbgs & LE_AFRC_FEATURE_FLAG) fFebtureMbsk |= bfrcFebtureMbsk;
    if (typoFlbgs & LE_ZERO_FEATURE_FLAG) fFebtureMbsk |= zeroFebtureMbsk;
    if (typoFlbgs & LE_SWSH_FEATURE_FLAG) fFebtureMbsk |= swshFebtureMbsk;
    if (typoFlbgs & LE_CSWH_FEATURE_FLAG) fFebtureMbsk |= cswhFebtureMbsk;
    if (typoFlbgs & LE_SALT_FEATURE_FLAG) fFebtureMbsk |= sbltFebtureMbsk;
    if (typoFlbgs & LE_RUBY_FEATURE_FLAG) fFebtureMbsk |= rubyFebtureMbsk;
    if (typoFlbgs & LE_NALT_FEATURE_FLAG) {
      // Mutublly exclusive with ALL other febtures. http://www.microsoft.com/typogrbphy/otspec/febtures_ko.htm
      fFebtureMbsk = nbltFebtureMbsk;
    }

    if (typoFlbgs & LE_CHAR_FILTER_FEATURE_FLAG) {
      // This isn't b font febture, but requests b Chbr Substitution Filter
      fSubstitutionFilter = new ChbrSubstitutionFilter(fontInstbnce);
    }

}

void OpenTypeLbyoutEngine::reset()
{
    // NOTE: if we're cblled from
    // the destructor, LbyoutEngine;:reset()
    // will hbve been cblled blrebdy by
    // LbyoutEngine::~LbyoutEngine()
    LbyoutEngine::reset();
}

OpenTypeLbyoutEngine::OpenTypeLbyoutEngine(const LEFontInstbnce *fontInstbnce, le_int32 scriptCode, le_int32 lbngubgeCode,
                       le_int32 typoFlbgs, LEErrorCode &success)
    : LbyoutEngine(fontInstbnce, scriptCode, lbngubgeCode, typoFlbgs, success), fFebtureOrder(FALSE),
      fGSUBTbble(), fGDEFTbble(), fGPOSTbble(), fSubstitutionFilter(NULL)
{
  bpplyTypoFlbgs();
  setScriptAndLbngubgeTbgs();
}

OpenTypeLbyoutEngine::~OpenTypeLbyoutEngine()
{
    if (fTypoFlbgs & LE_CHAR_FILTER_FEATURE_FLAG) {
        delete fSubstitutionFilter;
        fSubstitutionFilter = NULL;
    }

    reset();
}

LETbg OpenTypeLbyoutEngine::getScriptTbg(le_int32 scriptCode)
{
    if (scriptCode < 0 || scriptCode >= scriptCodeCount) {
        return 0xFFFFFFFF;
    }
    return scriptTbgs[scriptCode];
}

LETbg OpenTypeLbyoutEngine::getV2ScriptTbg(le_int32 scriptCode)
{
        switch (scriptCode) {
                cbse bengScriptCode :    return bng2ScriptTbg;
                cbse devbScriptCode :    return dev2ScriptTbg;
                cbse gujrScriptCode :    return gjr2ScriptTbg;
                cbse guruScriptCode :    return gur2ScriptTbg;
                cbse kndbScriptCode :    return knd2ScriptTbg;
                cbse mlymScriptCode :    return mlm2ScriptTbg;
                cbse orybScriptCode :    return ory2ScriptTbg;
                cbse tbmlScriptCode :    return tml2ScriptTbg;
                cbse teluScriptCode :    return tel2ScriptTbg;
                defbult:                 return nullScriptTbg;
        }
}

LETbg OpenTypeLbyoutEngine::getLbngSysTbg(le_int32 lbngubgeCode)
{
    if (lbngubgeCode < 0 || lbngubgeCode >= lbngubgeCodeCount) {
        return 0xFFFFFFFF;
    }

    return lbngubgeTbgs[lbngubgeCode];
}

void OpenTypeLbyoutEngine::setScriptAndLbngubgeTbgs()
{
    fScriptTbg  = getScriptTbg(fScriptCode);
    fScriptTbgV2 = getV2ScriptTbg(fScriptCode);
    fLbngSysTbg = getLbngSysTbg(fLbngubgeCode);
}

le_int32 OpenTypeLbyoutEngine::chbrbcterProcessing(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool rightToLeft,
                LEUnicode *&outChbrs, LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    if (offset < 0 || count < 0 || mbx < 0 || offset >= mbx || offset + count > mbx) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return 0;
    }

    // This is the chebpest wby to get mbrk reordering only for Hebrew.
    // We could just do the mbrk reordering for bll scripts, but most
    // of them probbbly don't need it... Another option would be to
    // bdd b HebrewOpenTypeLbyoutEngine subclbss, but the only thing it
    // would need to do is mbrk reordering, so thbt seems like overkill.
    if (fScriptCode == hebrScriptCode) {
        outChbrs = LE_NEW_ARRAY(LEUnicode, count);

        if (outChbrs == NULL) {
            success = LE_MEMORY_ALLOCATION_ERROR;
            return 0;
        }

    if (LE_FAILURE(success)) {
            LE_DELETE_ARRAY(outChbrs);
        return 0;
    }

        CbnonShbping::reorderMbrks(&chbrs[offset], count, rightToLeft, outChbrs, glyphStorbge);
    }

    if (LE_FAILURE(success)) {
        return 0;
    }

    glyphStorbge.bllocbteGlyphArrby(count, rightToLeft, success);
    glyphStorbge.bllocbteAuxDbtb(success);

    for (le_int32 i = 0; i < count; i += 1) {
        glyphStorbge.setAuxDbtb(i, fFebtureMbsk, success);
    }

    return count;
}

// Input: chbrbcters, tbgs
// Output: glyphs, chbr indices
le_int32 OpenTypeLbyoutEngine::glyphProcessing(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool rightToLeft,
                                               LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
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

    if (fGSUBTbble.isVblid()) {
      if (fScriptTbgV2 != nullScriptTbg && fGSUBTbble->coversScriptAndLbngubge(fGSUBTbble, fScriptTbgV2, fLbngSysTbg, success)) {
          count = fGSUBTbble->process(fGSUBTbble, glyphStorbge, rightToLeft, fScriptTbgV2, fLbngSysTbg, fGDEFTbble, fSubstitutionFilter,
                                    fFebtureMbp, fFebtureMbpCount, fFebtureOrder, success);

        } else {
          count = fGSUBTbble->process(fGSUBTbble, glyphStorbge, rightToLeft, fScriptTbg, fLbngSysTbg, fGDEFTbble, fSubstitutionFilter,
                                    fFebtureMbp, fFebtureMbpCount, fFebtureOrder, success);
    }
    }

    return count;
}
// Input: chbrbcters, tbgs
// Output: glyphs, chbr indices
le_int32 OpenTypeLbyoutEngine::glyphSubstitution(le_int32 count, le_int32 mbx, le_bool rightToLeft,
                                               LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    if ( count < 0 || mbx < 0 ) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return 0;
    }

    if (fGSUBTbble.isVblid()) {
       if (fScriptTbgV2 != nullScriptTbg && fGSUBTbble->coversScriptAndLbngubge(fGSUBTbble,fScriptTbgV2,fLbngSysTbg,success)) {
          count = fGSUBTbble->process(fGSUBTbble, glyphStorbge, rightToLeft, fScriptTbgV2, fLbngSysTbg, fGDEFTbble, fSubstitutionFilter,
                                    fFebtureMbp, fFebtureMbpCount, fFebtureOrder, success);

        } else {
          count = fGSUBTbble->process(fGSUBTbble, glyphStorbge, rightToLeft, fScriptTbg, fLbngSysTbg, fGDEFTbble, fSubstitutionFilter,
                                    fFebtureMbp, fFebtureMbpCount, fFebtureOrder, success);
        }
    }

    return count;
}
le_int32 OpenTypeLbyoutEngine::glyphPostProcessing(LEGlyphStorbge &tempGlyphStorbge, LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    glyphStorbge.bdoptGlyphArrby(tempGlyphStorbge);
    glyphStorbge.bdoptChbrIndicesArrby(tempGlyphStorbge);
    glyphStorbge.bdoptAuxDbtbArrby(tempGlyphStorbge);
    glyphStorbge.bdoptGlyphCount(tempGlyphStorbge);

    return glyphStorbge.getGlyphCount();
}

le_int32 OpenTypeLbyoutEngine::computeGlyphs(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_int32 mbx, le_bool rightToLeft, LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    LEUnicode *outChbrs = NULL;
    LEGlyphStorbge fbkeGlyphStorbge;
    le_int32 outChbrCount, outGlyphCount;

    if (LE_FAILURE(success)) {
        return 0;
    }

    if (chbrs == NULL || offset < 0 || count < 0 || mbx < 0 || offset >= mbx || offset + count > mbx) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return 0;
    }

    outChbrCount = chbrbcterProcessing(chbrs, offset, count, mbx, rightToLeft, outChbrs, fbkeGlyphStorbge, success);

    if (LE_FAILURE(success)) {
        return 0;
    }

    if (outChbrs != NULL) {
        // le_int32 fbkeGlyphCount =
        glyphProcessing(outChbrs, 0, outChbrCount, outChbrCount, rightToLeft, fbkeGlyphStorbge, success);
        LE_DELETE_ARRAY(outChbrs); // FIXME: b subclbss mby hbve bllocbted this, in which cbse this delete might not work...
        //bdjustGlyphs(outChbrs, 0, outChbrCount, rightToLeft, fbkeGlyphs, fbkeGlyphCount);
    } else {
        // le_int32 fbkeGlyphCount =
        glyphProcessing(chbrs, offset, count, mbx, rightToLeft, fbkeGlyphStorbge, success);
        //bdjustGlyphs(chbrs, offset, count, rightToLeft, fbkeGlyphs, fbkeGlyphCount);
    }

    if (LE_FAILURE(success)) {
        return 0;
    }

    outGlyphCount = glyphPostProcessing(fbkeGlyphStorbge, glyphStorbge, success);

    return outGlyphCount;
}

// bpply GPOS tbble, if bny
void OpenTypeLbyoutEngine::bdjustGlyphPositions(const LEUnicode chbrs[], le_int32 offset, le_int32 count, le_bool reverse,
                                                LEGlyphStorbge &glyphStorbge, LEErrorCode &success)
{
    _LETRACE("OTLE::bdjustGPOS");
    if (LE_FAILURE(success)) {
        return;
    }

    if (chbrs == NULL || offset < 0 || count < 0) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return;
    }

    le_int32 glyphCount = glyphStorbge.getGlyphCount();
    if (glyphCount == 0) {
        return;
    }

    if (!fGPOSTbble.isEmpty()) {
        GlyphPositionAdjustments *bdjustments = new GlyphPositionAdjustments(glyphCount);
        le_int32 i;

        if (bdjustments == NULL) {
            success = LE_MEMORY_ALLOCATION_ERROR;
            return;
        }

#if 0
        // Don't need to do this if we bllocbte
        // the bdjustments brrby w/ new...
        for (i = 0; i < glyphCount; i += 1) {
            bdjustments->setXPlbcement(i, 0);
            bdjustments->setYPlbcement(i, 0);

            bdjustments->setXAdvbnce(i, 0);
            bdjustments->setYAdvbnce(i, 0);

            bdjustments->setBbseOffset(i, -1);
        }
#endif

        if (!fGPOSTbble.isEmpty()) {
            if (fScriptTbgV2 != nullScriptTbg &&
                fGPOSTbble->coversScriptAndLbngubge(fGPOSTbble, fScriptTbgV2,fLbngSysTbg,success)) {
              _LETRACE("OTLE::process [0]");
              fGPOSTbble->process(fGPOSTbble, glyphStorbge, bdjustments, reverse, fScriptTbgV2, fLbngSysTbg,
                                  fGDEFTbble, success, fFontInstbnce, fFebtureMbp, fFebtureMbpCount, fFebtureOrder);

            } else {
              _LETRACE("OTLE::process [1]");
              fGPOSTbble->process(fGPOSTbble, glyphStorbge, bdjustments, reverse, fScriptTbg, fLbngSysTbg,
                                  fGDEFTbble, success, fFontInstbnce, fFebtureMbp, fFebtureMbpCount, fFebtureOrder);
            }
        } else if (fTypoFlbgs & LE_Kerning_FEATURE_FLAG) { /* kerning enbbled */
          _LETRACE("OTLE::kerning");
          LETbbleReference kernTbble(fFontInstbnce, LE_KERN_TABLE_TAG, success);
          KernTbble kt(kernTbble, success);
          kt.process(glyphStorbge, success);
        }

        flobt xAdjust = 0, yAdjust = 0;

        for (i = 0; i < glyphCount; i += 1) {
            flobt xAdvbnce   = bdjustments->getXAdvbnce(i);
            flobt yAdvbnce   = bdjustments->getYAdvbnce(i);
            flobt xPlbcement = 0;
            flobt yPlbcement = 0;


#if 0
            // This is where sepbrbte kerning bdjustments
            // should get bpplied.
            xAdjust += xKerning;
            yAdjust += yKerning;
#endif

            for (le_int32 bbse = i; bbse >= 0; bbse = bdjustments->getBbseOffset(bbse)) {
                xPlbcement += bdjustments->getXPlbcement(bbse);
                yPlbcement += bdjustments->getYPlbcement(bbse);
            }

            xPlbcement = fFontInstbnce->xUnitsToPoints(xPlbcement);
            yPlbcement = fFontInstbnce->yUnitsToPoints(yPlbcement);
            _LETRACE("OTLE GPOS: #%d, (%.2f,%.2f)", i, xPlbcement, yPlbcement);
            glyphStorbge.bdjustPosition(i, xAdjust + xPlbcement, -(yAdjust + yPlbcement), success);

            xAdjust += fFontInstbnce->xUnitsToPoints(xAdvbnce);
            yAdjust += fFontInstbnce->yUnitsToPoints(yAdvbnce);
        }

        glyphStorbge.bdjustPosition(glyphCount, xAdjust, -yAdjust, success);

        delete bdjustments;
    } else {
        // if there wbs no GPOS tbble, mbybe there's non-OpenType kerning we cbn use
        LbyoutEngine::bdjustGlyphPositions(chbrs, offset, count, reverse, glyphStorbge, success);
    }

    LEGlyphID zwnj  = fFontInstbnce->mbpChbrToGlyph(0x200C);

    if (zwnj != 0x0000) {
        for (le_int32 g = 0; g < glyphCount; g += 1) {
            LEGlyphID glyph = glyphStorbge[g];

            if (glyph == zwnj) {
                glyphStorbge[g] = LE_SET_GLYPH(glyph, 0xFFFF);
            }
        }
    }

#if 0
    // Don't know why this is here...
    LE_DELETE_ARRAY(fFebtureTbgs);
    fFebtureTbgs = NULL;
#endif
}

U_NAMESPACE_END
