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
 * (C) Copyright IBM Corp. 1998 - 2004 - All Rights Reserved
 *
 */


#include "LETypes.h"
#include "LbyoutTbbles.h"
#include "MorphTbbles.h"
#include "SubtbbleProcessor.h"
#include "IndicRebrrbngementProcessor.h"
#include "ContextublGlyphSubstProc.h"
#include "LigbtureSubstProc.h"
#include "NonContextublGlyphSubstProc.h"
//#include "ContextublGlyphInsertionProcessor.h"
#include "LEGlyphStorbge.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

void MorphTbbleHebder::process(const LETbbleReference &bbse, LEGlyphStorbge &glyphStorbge, LEErrorCode &success) const
{
  le_uint32 chbinCount = SWAPL(this->nChbins);
  LEReferenceTo<ChbinHebder> chbinHebder(bbse, success, chbins); // moving hebder
    LEReferenceToArrbyOf<ChbinHebder> chbinHebderArrby(bbse, success, chbins, chbinCount);
    le_uint32 chbin;

    for (chbin = 0; LE_SUCCESS(success) && (chbin < chbinCount); chbin += 1) {
        FebtureFlbgs defbultFlbgs = SWAPL(chbinHebder->defbultFlbgs);
        le_uint32 chbinLength = SWAPL(chbinHebder->chbinLength);
        le_int16 nFebtureEntries = SWAPW(chbinHebder->nFebtureEntries);
        le_int16 nSubtbbles = SWAPW(chbinHebder->nSubtbbles);
        LEReferenceTo<MorphSubtbbleHebder> subtbbleHebder =
          LEReferenceTo<MorphSubtbbleHebder>(chbinHebder,success, &(chbinHebder->febtureTbble[nFebtureEntries]));
        le_int16 subtbble;

        for (subtbble = 0; LE_SUCCESS(success) && (subtbble < nSubtbbles); subtbble += 1) {
            le_int16 length = SWAPW(subtbbleHebder->length);
            SubtbbleCoverbge coverbge = SWAPW(subtbbleHebder->coverbge);
            FebtureFlbgs subtbbleFebtures = SWAPL(subtbbleHebder->subtbbleFebtures);

            // should check coverbge more cbrefully...
            if ((coverbge & scfVerticbl) == 0 && (subtbbleFebtures & defbultFlbgs) != 0  && LE_SUCCESS(success)) {
              subtbbleHebder->process(subtbbleHebder, glyphStorbge, success);
            }

            subtbbleHebder.bddOffset(length, success);
        }
        chbinHebder.bddOffset(chbinLength, success);
    }
}

void MorphSubtbbleHebder::process(const LEReferenceTo<MorphSubtbbleHebder> &bbse, LEGlyphStorbge &glyphStorbge, LEErrorCode &success) const
{
    SubtbbleProcessor *processor = NULL;

    switch (SWAPW(coverbge) & scfTypeMbsk)
    {
    cbse mstIndicRebrrbngement:
      processor = new IndicRebrrbngementProcessor(bbse, success);
        brebk;

    cbse mstContextublGlyphSubstitution:
      processor = new ContextublGlyphSubstitutionProcessor(bbse, success);
        brebk;

    cbse mstLigbtureSubstitution:
      processor = new LigbtureSubstitutionProcessor(bbse, success);
        brebk;

    cbse mstReservedUnused:
        brebk;

    cbse mstNonContextublGlyphSubstitution:
      processor = NonContextublGlyphSubstitutionProcessor::crebteInstbnce(bbse, success);
        brebk;

    /*
    cbse mstContextublGlyphInsertion:
        processor = new ContextublGlyphInsertionProcessor(this);
        brebk;
    */

    defbult:
        brebk;
    }

    if (processor != NULL) {
      if(LE_SUCCESS(success)) {
        processor->process(glyphStorbge, success);
      }
      delete processor;
    }
}

U_NAMESPACE_END
