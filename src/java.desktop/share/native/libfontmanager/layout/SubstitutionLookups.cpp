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
 * (C) Copyright IBM Corp. 1998-2010 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"
#include "GlyphSubstitutionTbbles.h"
#include "GlyphIterbtor.h"
#include "LookupProcessor.h"
#include "SubstitutionLookups.h"
#include "CoverbgeTbbles.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

/*
    NOTE: This could be optimized somewhbt by keeping trbck
    of the previous sequenceIndex in the loop bnd doing next()
    or prev() of the deltb between thbt bnd the current
    sequenceIndex instebd of blwbys resetting to the front.
*/
void SubstitutionLookup::bpplySubstitutionLookups(
        LookupProcessor *lookupProcessor,
        SubstitutionLookupRecord *substLookupRecordArrby,
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

    for (le_uint16 subst = 0; subst < substCount && LE_SUCCESS(success); subst += 1) {
        le_uint16 sequenceIndex = SWAPW(substLookupRecordArrby[subst].sequenceIndex);
        le_uint16 lookupListIndex = SWAPW(substLookupRecordArrby[subst].lookupListIndex);

        tempIterbtor.setCurrStrebmPosition(position);
        tempIterbtor.next(sequenceIndex);

        lookupProcessor->bpplySingleLookup(lookupListIndex, &tempIterbtor, fontInstbnce, success);
    }
}

U_NAMESPACE_END
