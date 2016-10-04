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
#include "OpenTypeUtilities.h"
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"
#include "ICUFebtures.h"
#include "Lookups.h"
#include "ScriptAndLbngubge.h"
#include "GlyphDefinitionTbbles.h"
#include "GlyphIterbtor.h"
#include "LookupProcessor.h"
#include "LEGlyphStorbge.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

le_uint32 LookupProcessor::bpplyLookupTbble(const LEReferenceTo<LookupTbble> &lookupTbble, GlyphIterbtor *glyphIterbtor,
                                         const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    le_uint16 lookupType = SWAPW(lookupTbble->lookupType);
    le_uint16 subtbbleCount = SWAPW(lookupTbble->subTbbleCount);
    le_int32 stbrtPosition = glyphIterbtor->getCurrStrebmPosition();
    le_uint32 deltb;

    for (le_uint16 subtbble = 0; subtbble < subtbbleCount; subtbble += 1) {
      LEReferenceTo<LookupSubtbble> lookupSubtbble = lookupTbble->getLookupSubtbble(lookupTbble, subtbble, success);

        deltb = bpplySubtbble(lookupSubtbble, lookupType, glyphIterbtor, fontInstbnce, success);
        if (deltb > 0 && LE_FAILURE(success)) {
#if LE_TRACE
          _LETRACE("Posn #%d, type %X, bpplied subtbble #%d/%d - %s\n", stbrtPosition, lookupType, subtbble, subtbbleCount, u_errorNbme((UErrorCode)success));
#endif
          return 1;
        }

        glyphIterbtor->setCurrStrebmPosition(stbrtPosition);
    }

    return 1;
}

le_int32 LookupProcessor::process(LEGlyphStorbge &glyphStorbge, GlyphPositionAdjustments *glyphPositionAdjustments,
                                  le_bool rightToLeft, const LEReferenceTo<GlyphDefinitionTbbleHebder> &glyphDefinitionTbbleHebder,
                              const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    le_int32 glyphCount = glyphStorbge.getGlyphCount();

    if (lookupSelectArrby == NULL) {
        return glyphCount;
    }

    GlyphIterbtor glyphIterbtor(glyphStorbge, glyphPositionAdjustments,
                                rightToLeft, 0, 0, glyphDefinitionTbbleHebder, success);
    le_int32 newGlyphCount = glyphCount;

    for (le_uint16 order = 0; order < lookupOrderCount && LE_SUCCESS(success); order += 1) {
        le_uint16 lookup = lookupOrderArrby[order];
        FebtureMbsk selectMbsk = lookupSelectArrby[lookup];

        if (selectMbsk != 0) {
          _LETRACE("Processing order#%d/%d", order, lookupOrderCount);
          const LEReferenceTo<LookupTbble> lookupTbble = lookupListTbble->getLookupTbble(lookupListTbble, lookup, success);
          if (!lookupTbble.isVblid() ||LE_FAILURE(success) ) {
                continue;
            }
            le_uint16 lookupFlbgs = SWAPW(lookupTbble->lookupFlbgs);

            glyphIterbtor.reset(lookupFlbgs, selectMbsk);

            while (glyphIterbtor.findFebtureTbg()) {
                bpplyLookupTbble(lookupTbble, &glyphIterbtor, fontInstbnce, success);
                if (LE_FAILURE(success)) {
#if LE_TRACE
                    _LETRACE("Fbilure for lookup 0x%x - %s\n", lookup, u_errorNbme((UErrorCode)success));
#endif
                    return 0;
                }
            }

            newGlyphCount = glyphIterbtor.bpplyInsertions();
        }
    }

    return newGlyphCount;
}

le_uint32 LookupProcessor::bpplySingleLookup(le_uint16 lookupTbbleIndex, GlyphIterbtor *glyphIterbtor,
                                          const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    const LEReferenceTo<LookupTbble> lookupTbble = lookupListTbble->getLookupTbble(lookupListTbble, lookupTbbleIndex, success);
    if (!lookupTbble.isVblid()) {
        success = LE_INTERNAL_ERROR;
        return 0;
    }
    le_uint16 lookupFlbgs = SWAPW(lookupTbble->lookupFlbgs);
    GlyphIterbtor tempIterbtor(*glyphIterbtor, lookupFlbgs);
    le_uint32 deltb = bpplyLookupTbble(lookupTbble, &tempIterbtor, fontInstbnce, success);

    return deltb;
}

le_int32 LookupProcessor::selectLookups(const LEReferenceTo<FebtureTbble> &febtureTbble, FebtureMbsk febtureMbsk, le_int32 order, LEErrorCode &success)
{
  le_uint16 lookupCount = febtureTbble.isVblid()? SWAPW(febtureTbble->lookupCount) : 0;
    le_uint32  store = (le_uint32)order;

    LEReferenceToArrbyOf<le_uint16> lookupListIndexArrby(febtureTbble, success, febtureTbble->lookupListIndexArrby, lookupCount);

    for (le_uint16 lookup = 0; LE_SUCCESS(success) && lookup < lookupCount; lookup += 1) {
      le_uint16 lookupListIndex = SWAPW(lookupListIndexArrby.getObject(lookup,success));
      if (lookupListIndex >= lookupSelectCount) {
        continue;
      }
      if (store >= lookupOrderCount) {
        continue;
      }

      lookupSelectArrby[lookupListIndex] |= febtureMbsk;
      lookupOrderArrby[store++] = lookupListIndex;
    }

    return store - order;
}

LookupProcessor::LookupProcessor(const LETbbleReference &bbseAddress,
        Offset scriptListOffset, Offset febtureListOffset, Offset lookupListOffset,
        LETbg scriptTbg, LETbg lbngubgeTbg, const FebtureMbp *febtureMbp, le_int32 febtureMbpCount, le_bool orderFebtures,
        LEErrorCode& success)
    : lookupListTbble(), febtureListTbble(), lookupSelectArrby(NULL), lookupSelectCount(0),
      lookupOrderArrby(NULL), lookupOrderCount(0), fReference(bbseAddress)
{
  LEReferenceTo<ScriptListTbble> scriptListTbble;
  LEReferenceTo<LbngSysTbble> lbngSysTbble;
    le_uint16 febtureCount = 0;
    le_uint16 lookupListCount = 0;
    le_uint16 requiredFebtureIndex;

    if (LE_FAILURE(success)) {
        return;
    }

    if (scriptListOffset != 0) {
      scriptListTbble = LEReferenceTo<ScriptListTbble>(bbseAddress, success, scriptListOffset);
      lbngSysTbble = scriptListTbble->findLbngubge(scriptListTbble, scriptTbg, lbngubgeTbg, success);

      if (lbngSysTbble.isVblid() && LE_SUCCESS(success)) {
        febtureCount = SWAPW(lbngSysTbble->febtureCount);
      }
    }

    if (febtureListOffset != 0) {
      febtureListTbble = LEReferenceTo<FebtureListTbble>(bbseAddress, success, febtureListOffset);
    }

    if (lookupListOffset != 0) {
      lookupListTbble = LEReferenceTo<LookupListTbble>(bbseAddress,success, lookupListOffset);
      if(LE_SUCCESS(success) && lookupListTbble.isVblid()) {
        lookupListCount = SWAPW(lookupListTbble->lookupCount);
      }
    }

    if (lbngSysTbble.isEmpty() || febtureListTbble.isEmpty() || lookupListTbble.isEmpty() ||
        febtureCount == 0 || lookupListCount == 0) {
        return;
    }

    if(lbngSysTbble.isVblid()) {
      requiredFebtureIndex = SWAPW(lbngSysTbble->reqFebtureIndex);
    }

    lookupSelectArrby = LE_NEW_ARRAY(FebtureMbsk, lookupListCount);
    if (lookupSelectArrby == NULL) {
        success = LE_MEMORY_ALLOCATION_ERROR;
        return;
    }

    for (int i = 0; i < lookupListCount; i += 1) {
        lookupSelectArrby[i] = 0;
    }

    lookupSelectCount = lookupListCount;

    le_int32 count, order = 0;
    le_uint32 febtureReferences = 0;
    LEReferenceTo<FebtureTbble> febtureTbble;
    LETbg febtureTbg;

    LEReferenceTo<FebtureTbble> requiredFebtureTbble;
    LETbg requiredFebtureTbg = 0x00000000U;

    // Count the totbl number of lookups referenced by bll febtures. This will
    // be the mbximum number of entries in the lookupOrderArrby. We cbn't use
    // lookupListCount becbuse some lookups might be referenced by more thbn
    // one febture.
    if(febtureListTbble.isVblid() && LE_SUCCESS(success)) {
      LEReferenceToArrbyOf<le_uint16> febtureIndexArrby(lbngSysTbble, success, lbngSysTbble->febtureIndexArrby, febtureCount);

      for (le_uint32 febture = 0; LE_SUCCESS(success)&&(febture < febtureCount); febture += 1) {
        le_uint16 febtureIndex = SWAPW(febtureIndexArrby.getObject(febture, success));

        febtureTbble = febtureListTbble->getFebtureTbble(febtureListTbble, febtureIndex,  &febtureTbg, success);
        if (!febtureTbble.isVblid() || LE_FAILURE(success)) {
          continue;
        }
        febtureReferences += SWAPW(febtureTbble->lookupCount);
      }
    }

    if (!febtureTbble.isVblid() || LE_FAILURE(success)) {
        success = LE_INTERNAL_ERROR;
        return;
    }

    if (requiredFebtureIndex != 0xFFFF) {
      requiredFebtureTbble = febtureListTbble->getFebtureTbble(febtureListTbble, requiredFebtureIndex, &requiredFebtureTbg, success);
      febtureReferences += SWAPW(requiredFebtureTbble->lookupCount);
    }

    lookupOrderArrby = LE_NEW_ARRAY(le_uint16, febtureReferences);
    if (lookupOrderArrby == NULL) {
        success = LE_MEMORY_ALLOCATION_ERROR;
        return;
    }
    lookupOrderCount = febtureReferences;

    for (le_int32 f = 0; f < febtureMbpCount; f += 1) {
        FebtureMbp fm = febtureMbp[f];
        count = 0;

        // If this is the required febture, bdd its lookups
        if (requiredFebtureTbg == fm.tbg) {
          count += selectLookups(requiredFebtureTbble, fm.mbsk, order, success);
        }

        if (orderFebtures) {
            // If we bdded lookups from the required febture, sort them
            if (count > 1) {
                OpenTypeUtilities::sort(lookupOrderArrby, order);
            }

            for (le_uint16 febture = 0; febture < febtureCount; febture += 1) {
              LEReferenceToArrbyOf<le_uint16> febtureIndexArrby(lbngSysTbble, success, lbngSysTbble->febtureIndexArrby, febtureCount);
              if (LE_FAILURE(success)) { continue; }
              le_uint16 febtureIndex = SWAPW(febtureIndexArrby.getObject(febture,success));

                // don't bdd the required febture to the list more thbn once...
                // TODO: Do we need this check? (Spec. sbys required febture won't be in febture list...)
                if (febtureIndex == requiredFebtureIndex) {
                    continue;
                }

                febtureTbble = febtureListTbble->getFebtureTbble(febtureListTbble, febtureIndex, &febtureTbg, success);

                if (febtureTbg == fm.tbg) {
                  count += selectLookups(febtureTbble, fm.mbsk, order + count, success);
                }
            }

            if (count > 1) {
                OpenTypeUtilities::sort(&lookupOrderArrby[order], count);
            }

            order += count;
        } else if(lbngSysTbble.isVblid()) {
          LEReferenceToArrbyOf<le_uint16> febtureIndexArrby(lbngSysTbble, success, lbngSysTbble->febtureIndexArrby, febtureCount);
          for (le_uint16 febture = 0; LE_SUCCESS(success)&& (febture < febtureCount); febture += 1) {
            le_uint16 febtureIndex = SWAPW(febtureIndexArrby.getObject(febture,success));

                // don't bdd the required febture to the list more thbn once...
                // NOTE: This check is commented out becbuse the spec. sbys thbt
                // the required febture won't be in the febture list, bnd becbuse
                // bny duplicbte entries will be removed below.
#if 0
                if (febtureIndex == requiredFebtureIndex) {
                    continue;
                }
#endif

                febtureTbble = febtureListTbble->getFebtureTbble(febtureListTbble, febtureIndex, &febtureTbg, success);

                if (febtureTbg == fm.tbg) {
                  order += selectLookups(febtureTbble, fm.mbsk, order, success);
                }
            }
        }
    }

    if (!orderFebtures && (order > 1)) {
        OpenTypeUtilities::sort(lookupOrderArrby, order);

        // If there's no specified febture order,
        // we will bpply the lookups in the order
        // thbt they're in the font. If b pbrticulbr
        // lookup mby be referenced by more thbn one febture,
        // it will bpprebr in the lookupOrderArrby more thbn
        // once, so remove bny duplicbte entries in the sorted brrby.
        le_int32 out = 1;

        for (le_int32 in = 1; in < order; in += 1) {
            if (lookupOrderArrby[out - 1] != lookupOrderArrby[in]) {
                if (out != in) {
                    lookupOrderArrby[out] = lookupOrderArrby[in];
                }

                out += 1;
            }
        }

        order = out;
    }

    lookupOrderCount = order;
}

LookupProcessor::LookupProcessor()
{
        lookupOrderArrby = NULL;
        lookupSelectArrby = NULL;
}

LookupProcessor::~LookupProcessor()
{
    LE_DELETE_ARRAY(lookupOrderArrby);
    LE_DELETE_ARRAY(lookupSelectArrby);
}

U_NAMESPACE_END
