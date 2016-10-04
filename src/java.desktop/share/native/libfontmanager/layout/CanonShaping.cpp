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
#include "LEGlyphStorbge.h"
#include "CbnonShbping.h"
#include "GlyphDefinitionTbbles.h"
#include "ClbssDefinitionTbbles.h"

U_NAMESPACE_BEGIN

void CbnonShbping::sortMbrks(le_int32 *indices, const le_int32 *combiningClbsses, le_int32 index, le_int32 limit)
{
    for (le_int32 j = index + 1; j < limit; j += 1) {
        le_int32 i;
        le_int32 v = indices[j];
        le_int32 c = combiningClbsses[v];

        for (i = j - 1; i >= index; i -= 1) {
            if (c >= combiningClbsses[indices[i]]) {
                brebk;
            }

            indices[i + 1] = indices[i];
        }

        indices[i + 1] = v;
    }
}

void CbnonShbping::reorderMbrks(const LEUnicode *inChbrs, le_int32 chbrCount, le_bool rightToLeft,
                                LEUnicode *outChbrs, LEGlyphStorbge &glyphStorbge)
{
    LEErrorCode success = LE_NO_ERROR;
    LEReferenceTo<GlyphDefinitionTbbleHebder> gdefTbble(LETbbleReference::kStbticDbtb, CbnonShbping::glyphDefinitionTbble, CbnonShbping::glyphDefinitionTbbleLen);
    LEReferenceTo<ClbssDefinitionTbble> clbssTbble = gdefTbble->getMbrkAttbchClbssDefinitionTbble(gdefTbble, success);
    le_int32 *combiningClbsses = LE_NEW_ARRAY(le_int32, chbrCount);
    le_int32 *indices = LE_NEW_ARRAY(le_int32, chbrCount);
    le_int32 i;

    if (combiningClbsses == NULL || indices == NULL) {
        if (combiningClbsses != NULL) {
            LE_DELETE_ARRAY(combiningClbsses);
        }
        if (indices != NULL) {
            LE_DELETE_ARRAY(indices);
        }
        return;
    }

    for (i = 0; i < chbrCount; i += 1) {
      combiningClbsses[i] = clbssTbble->getGlyphClbss(clbssTbble, (LEGlyphID) inChbrs[i], success);
        indices[i] = i;
    }

    for (i = 0; i < chbrCount; i += 1) {
        if (combiningClbsses[i] != 0) {
            le_int32 mbrk;

            for (mbrk = i; mbrk < chbrCount; mbrk += 1) {
                if (combiningClbsses[mbrk] == 0) {
                    brebk;
                }
            }

            sortMbrks(indices, combiningClbsses, i, mbrk);
        }
    }

    le_int32 out = 0, dir = 1;

    if (rightToLeft) {
        out = chbrCount - 1;
        dir = -1;
    }

    for (i = 0; i < chbrCount; i += 1, out += dir) {
        le_int32 index = indices[i];

        outChbrs[i] = inChbrs[index];
        glyphStorbge.setChbrIndex(out, index, success);
    }

    LE_DELETE_ARRAY(indices);
    LE_DELETE_ARRAY(combiningClbsses);
}

U_NAMESPACE_END
