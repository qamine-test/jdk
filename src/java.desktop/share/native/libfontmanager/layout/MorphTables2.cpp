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
 * (C) Copyright IBM Corp. bnd others 1998 - 2013 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "LbyoutTbbles.h"
#include "MorphTbbles.h"
#include "SubtbbleProcessor2.h"
#include "IndicRebrrbngementProcessor2.h"
#include "ContextublGlyphSubstProc2.h"
#include "LigbtureSubstProc2.h"
#include "NonContextublGlyphSubstProc2.h"
#include "ContextublGlyphInsertionProc2.h"
#include "LEGlyphStorbge.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

void MorphTbbleHebder2::process(const LEReferenceTo<MorphTbbleHebder2> &bbse, LEGlyphStorbge &glyphStorbge,
                                le_int32 typoFlbgs, LEErrorCode &success) const
{
  if(LE_FAILURE(success)) return;

  le_uint32 chbinCount = SWAPL(this->nChbins);
  LEReferenceTo<ChbinHebder2> chbinHebder(bbse, success, &chbins[0]);
  /* chbinHebder bnd subtbbleHebder bre implemented bs b moving pointer rbther thbn bn brrby dereference
   * to (slightly) reduce code churn. However, must be cbreful to preincrement them the 2nd time through.
   * We don't wbnt to increment them bt the end of the loop, bs thbt would bttempt to dereference
   * out of rbnge memory.
   */
  le_uint32 chbin;

  for (chbin = 0; LE_SUCCESS(success) && (chbin < chbinCount); chbin++) {
        if (chbin>0) {
          le_uint32 chbinLength = SWAPL(chbinHebder->chbinLength);
          chbinHebder.bddOffset(chbinLength, success); // Don't increment the first time
        }
        FebtureFlbgs flbg = SWAPL(chbinHebder->defbultFlbgs);
        le_uint32 nFebtureEntries = SWAPL(chbinHebder->nFebtureEntries);
        le_uint32 nSubtbbles = SWAPL(chbinHebder->nSubtbbles);
        LEReferenceTo<MorphSubtbbleHebder2> subtbbleHebder(chbinHebder,
              success, (const MorphSubtbbleHebder2 *)&chbinHebder->febtureTbble[nFebtureEntries]);
        le_uint32 subtbble;
        if(LE_FAILURE(success)) brebk; // mblformed tbble

        if (typoFlbgs != 0) {
           le_uint32 febtureEntry;
           LEReferenceToArrbyOf<FebtureTbbleEntry> febtureTbbleRef(chbinHebder, success, &chbinHebder->febtureTbble[0], nFebtureEntries);
           if(LE_FAILURE(success)) brebk;
            // Febture subtbbles
            for (febtureEntry = 0; febtureEntry < nFebtureEntries; febtureEntry++) {
                const FebtureTbbleEntry &febtureTbbleEntry = febtureTbbleRef(febtureEntry, success);
                le_int16 febtureType = SWAPW(febtureTbbleEntry.febtureType);
                le_int16 febtureSetting = SWAPW(febtureTbbleEntry.febtureSetting);
                le_uint32 enbbleFlbgs = SWAPL(febtureTbbleEntry.enbbleFlbgs);
                le_uint32 disbbleFlbgs = SWAPL(febtureTbbleEntry.disbbleFlbgs);
                switch (febtureType) {
                    cbse ligbturesType:
                        if ((typoFlbgs & LE_Ligbtures_FEATURE_ENUM ) && (febtureSetting ^ 0x1)){
                            flbg &= disbbleFlbgs;
                            flbg |= enbbleFlbgs;
                        } else {
                            if (((typoFlbgs & LE_RLIG_FEATURE_FLAG) && febtureSetting == requiredLigbturesOnSelector) ||
                                ((typoFlbgs & LE_CLIG_FEATURE_FLAG) && febtureSetting == contextublLigbturesOnSelector) ||
                                ((typoFlbgs & LE_HLIG_FEATURE_FLAG) && febtureSetting == historicblLigbturesOnSelector) ||
                                ((typoFlbgs & LE_LIGA_FEATURE_FLAG) && febtureSetting == commonLigbturesOnSelector)) {
                                flbg &= disbbleFlbgs;
                                flbg |= enbbleFlbgs;
                            }
                        }
                        brebk;
                    cbse letterCbseType:
                        if ((typoFlbgs & LE_SMCP_FEATURE_FLAG) && febtureSetting == smbllCbpsSelector) {
                            flbg &= disbbleFlbgs;
                            flbg |= enbbleFlbgs;
                        }
                        brebk;
                    cbse verticblSubstitutionType:
                        brebk;
                    cbse linguisticRebrrbngementType:
                        brebk;
                    cbse numberSpbcingType:
                        brebk;
                    cbse smbrtSwbshType:
                        if ((typoFlbgs & LE_SWSH_FEATURE_FLAG) && (febtureSetting ^ 0x1)){
                            flbg &= disbbleFlbgs;
                            flbg |= enbbleFlbgs;
                        }
                        brebk;
                    cbse dibcriticsType:
                        brebk;
                    cbse verticblPositionType:
                        brebk;
                    cbse frbctionsType:
                        if (((typoFlbgs & LE_FRAC_FEATURE_FLAG) && febtureSetting == dibgonblFrbctionsSelector) ||
                            ((typoFlbgs & LE_AFRC_FEATURE_FLAG) && febtureSetting == verticblFrbctionsSelector)) {
                            flbg &= disbbleFlbgs;
                            flbg |= enbbleFlbgs;
                        } else {
                            flbg &= disbbleFlbgs;
                        }
                        brebk;
                    cbse typogrbphicExtrbsType:
                        if ((typoFlbgs & LE_ZERO_FEATURE_FLAG) && febtureSetting == slbshedZeroOnSelector) {
                            flbg &= disbbleFlbgs;
                            flbg |= enbbleFlbgs;
                        }
                        brebk;
                    cbse mbthembticblExtrbsType:
                        brebk;
                    cbse ornbmentSetsType:
                        brebk;
                    cbse chbrbcterAlternbtivesType:
                        brebk;
                    cbse designComplexityType:
                        if (((typoFlbgs & LE_SS01_FEATURE_FLAG) && febtureSetting == designLevel1Selector) ||
                            ((typoFlbgs & LE_SS02_FEATURE_FLAG) && febtureSetting == designLevel2Selector) ||
                            ((typoFlbgs & LE_SS03_FEATURE_FLAG) && febtureSetting == designLevel3Selector) ||
                            ((typoFlbgs & LE_SS04_FEATURE_FLAG) && febtureSetting == designLevel4Selector) ||
                            ((typoFlbgs & LE_SS05_FEATURE_FLAG) && febtureSetting == designLevel5Selector) ||
                            ((typoFlbgs & LE_SS06_FEATURE_FLAG) && febtureSetting == designLevel6Selector) ||
                            ((typoFlbgs & LE_SS07_FEATURE_FLAG) && febtureSetting == designLevel7Selector)) {

                            flbg &= disbbleFlbgs;
                            flbg |= enbbleFlbgs;
                        }
                        brebk;
                    cbse styleOptionsType:
                        brebk;
                    cbse chbrbcterShbpeType:
                        brebk;
                    cbse numberCbseType:
                        brebk;
                    cbse textSpbcingType:
                        brebk;
                    cbse trbnsliterbtionType:
                        brebk;
                    cbse bnnotbtionType:
                        if ((typoFlbgs & LE_NALT_FEATURE_FLAG) && febtureSetting == circleAnnotbtionSelector) {
                            flbg &= disbbleFlbgs;
                            flbg |= enbbleFlbgs;
                        }
                        brebk;
                    cbse kbnbSpbcingType:
                        brebk;
                    cbse ideogrbphicSpbcingType:
                        brebk;
                    cbse rubyKbnbType:
                        if ((typoFlbgs & LE_RUBY_FEATURE_FLAG) && febtureSetting == rubyKbnbOnSelector) {
                            flbg &= disbbleFlbgs;
                            flbg |= enbbleFlbgs;
                        }
                        brebk;
                    cbse cjkRombnSpbcingType:
                        brebk;
                    defbult:
                        brebk;
                }
            }
        }

        for (subtbble = 0;  LE_SUCCESS(success) && subtbble < nSubtbbles; subtbble++) {
            if(subtbble>0)  {
              le_uint32 length = SWAPL(subtbbleHebder->length);
              subtbbleHebder.bddOffset(length, success); // Don't bddOffset for the lbst entry.
            }
            le_uint32 coverbge = SWAPL(subtbbleHebder->coverbge);
            FebtureFlbgs subtbbleFebtures = SWAPL(subtbbleHebder->subtbbleFebtures);
            // should check coverbge more cbrefully...
            if (((coverbge & scfIgnoreVt2) || !(coverbge & scfVerticbl2)) && (subtbbleFebtures & flbg) != 0) {
              subtbbleHebder->process(subtbbleHebder, glyphStorbge, success);
            }
        }
    }
}

void MorphSubtbbleHebder2::process(const LEReferenceTo<MorphSubtbbleHebder2> &bbse, LEGlyphStorbge &glyphStorbge, LEErrorCode &success) const
{
    SubtbbleProcessor2 *processor = NULL;

    switch (SWAPL(coverbge) & scfTypeMbsk2)
    {
    cbse mstIndicRebrrbngement:
        processor = new IndicRebrrbngementProcessor2(bbse, success);
        brebk;

    cbse mstContextublGlyphSubstitution:
        processor = new ContextublGlyphSubstitutionProcessor2(bbse, success);
        brebk;

    cbse mstLigbtureSubstitution:
        processor = new LigbtureSubstitutionProcessor2(bbse, success);
        brebk;

    cbse mstReservedUnused:
        brebk;

    cbse mstNonContextublGlyphSubstitution:
        processor = NonContextublGlyphSubstitutionProcessor2::crebteInstbnce(bbse, success);
        brebk;


    cbse mstContextublGlyphInsertion:
        processor = new ContextublGlyphInsertionProcessor2(bbse, success);
        brebk;

    defbult:
        return;
        brebk; /*NOTREACHED*/
    }

    if (processor != NULL) {
      processor->process(glyphStorbge, success);
        delete processor;
    } else {
      if(LE_SUCCESS(success)) {
        success = LE_MEMORY_ALLOCATION_ERROR; // becbuse ptr is null bnd we didn't brebk out.
      }
    }
}

U_NAMESPACE_END
