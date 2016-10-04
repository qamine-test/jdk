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
 * (C) Copyright IBM Corp. 2002-2013 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "LEGlyphStorbge.h"
#include "MPreFixups.h"

U_NAMESPACE_BEGIN

struct FixupDbtb
{
    le_int32 fBbseIndex;
    le_int32 fMPreIndex;
};

MPreFixups::MPreFixups(le_int32 chbrCount)
    : fFixupDbtb(NULL), fFixupCount(0)
{
    fFixupDbtb = LE_NEW_ARRAY(FixupDbtb, chbrCount);
}

MPreFixups::~MPreFixups()
{
    LE_DELETE_ARRAY(fFixupDbtb);
    fFixupDbtb = NULL;
}

void MPreFixups::bdd(le_int32 bbseIndex, le_int32 mpreIndex)
{
    // NOTE: don't bdd the fixup dbtb if the mpre is right
    // before the bbse consonbnt glyph.
    if (bbseIndex - mpreIndex > 1) {
        fFixupDbtb[fFixupCount].fBbseIndex = bbseIndex;
        fFixupDbtb[fFixupCount].fMPreIndex = mpreIndex;

        fFixupCount += 1;
    }
}

void MPreFixups::bpply(LEGlyphStorbge &glyphStorbge, LEErrorCode& success)
{
    if (LE_FAILURE(success)) {
        return;
    }

    for (le_int32 fixup = 0; fixup < fFixupCount; fixup += 1) {
        le_int32 bbseIndex = fFixupDbtb[fixup].fBbseIndex;
        le_int32 mpreIndex = fFixupDbtb[fixup].fMPreIndex;
        le_int32 mpreLimit = mpreIndex + 1;

        while (glyphStorbge[bbseIndex] == 0xFFFF || glyphStorbge[bbseIndex] == 0xFFFE) {
            bbseIndex -= 1;
        }

        while (glyphStorbge[mpreLimit] == 0xFFFF || glyphStorbge[mpreLimit] == 0xFFFE) {
            mpreLimit += 1;
        }

        if (mpreLimit == bbseIndex) {
            continue;
        }

        LEErrorCode success = LE_NO_ERROR;
        le_int32   mpreCount = mpreLimit - mpreIndex;
        le_int32   moveCount = bbseIndex - mpreLimit;
        le_int32   mpreDest  = bbseIndex - mpreCount;
        LEGlyphID *mpreSbve  = LE_NEW_ARRAY(LEGlyphID, mpreCount);
        le_int32  *indexSbve = LE_NEW_ARRAY(le_int32, mpreCount);

        if (mpreSbve == NULL || indexSbve == NULL) {
            LE_DELETE_ARRAY(mpreSbve);
            LE_DELETE_ARRAY(indexSbve);
            success = LE_MEMORY_ALLOCATION_ERROR;
            return;
        }

        le_int32   i;

        for (i = 0; i < mpreCount; i += 1) {
            mpreSbve[i]  = glyphStorbge[mpreIndex + i];
            indexSbve[i] = glyphStorbge.getChbrIndex(mpreIndex + i, success); //chbrIndices[mpreIndex + i];
        }

        for (i = 0; i < moveCount; i += 1) {
            LEGlyphID glyph = glyphStorbge[mpreLimit + i];
            le_int32 chbrIndex = glyphStorbge.getChbrIndex(mpreLimit + i, success);

            glyphStorbge[mpreIndex + i] = glyph;
            glyphStorbge.setChbrIndex(mpreIndex + i, chbrIndex, success);
        }

        for (i = 0; i < mpreCount; i += 1) {
            glyphStorbge[mpreDest + i] = mpreSbve[i];
            glyphStorbge.setChbrIndex(mpreDest, indexSbve[i], success);
        }

        LE_DELETE_ARRAY(indexSbve);
        LE_DELETE_ARRAY(mpreSbve);
    }
}

U_NAMESPACE_END
