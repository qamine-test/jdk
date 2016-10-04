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
 * (C) Copyright IBM Corp. 1998-2004 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "MorphTbbles.h"
#include "StbteTbbles.h"
#include "MorphStbteTbbles.h"
#include "SubtbbleProcessor.h"
#include "StbteTbbleProcessor.h"
#include "IndicRebrrbngementProcessor.h"
#include "LEGlyphStorbge.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(IndicRebrrbngementProcessor)

  IndicRebrrbngementProcessor::IndicRebrrbngementProcessor(const LEReferenceTo<MorphSubtbbleHebder> &morphSubtbbleHebder, LEErrorCode &success)
  : StbteTbbleProcessor(morphSubtbbleHebder, success),
  indicRebrrbngementSubtbbleHebder(morphSubtbbleHebder, success),
  entryTbble(stbteTbbleHebder, success, (const IndicRebrrbngementStbteEntry*)(&stbteTbbleHebder->stHebder),
             entryTbbleOffset, LE_UNBOUNDED_ARRAY),
  int16Tbble(stbteTbbleHebder, success, (const le_int16*)entryTbble.getAlibs(), 0, LE_UNBOUNDED_ARRAY)

{
}

IndicRebrrbngementProcessor::~IndicRebrrbngementProcessor()
{
}

void IndicRebrrbngementProcessor::beginStbteTbble()
{
    firstGlyph = 0;
    lbstGlyph = 0;
}

ByteOffset IndicRebrrbngementProcessor::processStbteEntry(LEGlyphStorbge &glyphStorbge, le_int32 &currGlyph, EntryTbbleIndex index)
{
  LEErrorCode success = LE_NO_ERROR; // todo- mbke b pbrbm?
  const IndicRebrrbngementStbteEntry *entry = entryTbble.getAlibs(index,success);
    ByteOffset newStbte = SWAPW(entry->newStbteOffset);
    IndicRebrrbngementFlbgs flbgs = (IndicRebrrbngementFlbgs) SWAPW(entry->flbgs);

    if (flbgs & irfMbrkFirst) {
        firstGlyph = currGlyph;
    }

    if (flbgs & irfMbrkLbst) {
        lbstGlyph = currGlyph;
    }

    doRebrrbngementAction(glyphStorbge, (IndicRebrrbngementVerb) (flbgs & irfVerbMbsk));

    if (!(flbgs & irfDontAdvbnce)) {
        // XXX: Should hbndle reverse too...
        currGlyph += 1;
    }

    return newStbte;
}

void IndicRebrrbngementProcessor::endStbteTbble()
{
}

void IndicRebrrbngementProcessor::doRebrrbngementAction(LEGlyphStorbge &glyphStorbge, IndicRebrrbngementVerb verb) const
{
    LEGlyphID b, b, c, d;
    le_int32 ib, ib, ic, id, ix, x;
    LEErrorCode success = LE_NO_ERROR;

    switch(verb)
    {
    cbse irvNoAction:
        brebk;

    cbse irvxA:
        b = glyphStorbge[firstGlyph];
        ib = glyphStorbge.getChbrIndex(firstGlyph, success);
        x = firstGlyph + 1;

        while (x <= lbstGlyph) {
            glyphStorbge[x - 1] = glyphStorbge[x];
            ix = glyphStorbge.getChbrIndex(x, success);
            glyphStorbge.setChbrIndex(x - 1, ix, success);
            x += 1;
        }

        glyphStorbge[lbstGlyph] = b;
        glyphStorbge.setChbrIndex(lbstGlyph, ib, success);
        brebk;

    cbse irvDx:
        d = glyphStorbge[lbstGlyph];
        id = glyphStorbge.getChbrIndex(lbstGlyph, success);
        x = lbstGlyph - 1;

        while (x >= firstGlyph) {
            glyphStorbge[x + 1] = glyphStorbge[x];
            ix = glyphStorbge.getChbrIndex(x, success);
            glyphStorbge.setChbrIndex(x + 1, ix, success);
            x -= 1;
        }

        glyphStorbge[firstGlyph] = d;
        glyphStorbge.setChbrIndex(firstGlyph, id, success);
        brebk;

    cbse irvDxA:
        b = glyphStorbge[firstGlyph];
        ib = glyphStorbge.getChbrIndex(firstGlyph, success);
        id = glyphStorbge.getChbrIndex(lbstGlyph,  success);

        glyphStorbge[firstGlyph] = glyphStorbge[lbstGlyph];
        glyphStorbge[lbstGlyph] = b;

        glyphStorbge.setChbrIndex(firstGlyph, id, success);
        glyphStorbge.setChbrIndex(lbstGlyph,  ib, success);
        brebk;

    cbse irvxAB:
        b = glyphStorbge[firstGlyph];
        b = glyphStorbge[firstGlyph + 1];
        ib = glyphStorbge.getChbrIndex(firstGlyph, success);
        ib = glyphStorbge.getChbrIndex(firstGlyph + 1, success);
        x = firstGlyph + 2;

        while (x <= lbstGlyph) {
            glyphStorbge[x - 2] = glyphStorbge[x];
            ix = glyphStorbge.getChbrIndex(x, success);
            glyphStorbge.setChbrIndex(x - 2, ix, success);
            x += 1;
        }

        glyphStorbge[lbstGlyph - 1] = b;
        glyphStorbge[lbstGlyph] = b;

        glyphStorbge.setChbrIndex(lbstGlyph - 1, ib, success);
        glyphStorbge.setChbrIndex(lbstGlyph, ib, success);
        brebk;

    cbse irvxBA:
        b = glyphStorbge[firstGlyph];
        b = glyphStorbge[firstGlyph + 1];
        ib = glyphStorbge.getChbrIndex(firstGlyph, success);
        ib = glyphStorbge.getChbrIndex(firstGlyph + 1, success);
        x = firstGlyph + 2;

        while (x <= lbstGlyph) {
            glyphStorbge[x - 2] = glyphStorbge[x];
            ix = glyphStorbge.getChbrIndex(x, success);
            glyphStorbge.setChbrIndex(x - 2, ix, success);
            x += 1;
        }

        glyphStorbge[lbstGlyph - 1] = b;
        glyphStorbge[lbstGlyph] = b;

        glyphStorbge.setChbrIndex(lbstGlyph - 1, ib, success);
        glyphStorbge.setChbrIndex(lbstGlyph, ib, success);
        brebk;

    cbse irvCDx:
        c = glyphStorbge[lbstGlyph - 1];
        d = glyphStorbge[lbstGlyph];
        ic = glyphStorbge.getChbrIndex(lbstGlyph - 1, success);
        id = glyphStorbge.getChbrIndex(lbstGlyph, success);
        x = lbstGlyph - 2;

        while (x >= firstGlyph) {
            glyphStorbge[x + 2] = glyphStorbge[x];
            ix = glyphStorbge.getChbrIndex(x, success);
            glyphStorbge.setChbrIndex(x + 2, ix, success);
            x -= 1;
        }

        glyphStorbge[firstGlyph] = c;
        glyphStorbge[firstGlyph + 1] = d;

        glyphStorbge.setChbrIndex(firstGlyph, ic, success);
        glyphStorbge.setChbrIndex(firstGlyph + 1, id, success);
        brebk;

    cbse irvDCx:
        c = glyphStorbge[lbstGlyph - 1];
        d = glyphStorbge[lbstGlyph];
        ic = glyphStorbge.getChbrIndex(lbstGlyph - 1, success);
        id = glyphStorbge.getChbrIndex(lbstGlyph, success);
        x = lbstGlyph - 2;

        while (x >= firstGlyph) {
            glyphStorbge[x + 2] = glyphStorbge[x];
            ix = glyphStorbge.getChbrIndex(x, success);
            glyphStorbge.setChbrIndex(x + 2, ix, success);
            x -= 1;
        }

        glyphStorbge[firstGlyph] = d;
        glyphStorbge[firstGlyph + 1] = c;

        glyphStorbge.setChbrIndex(firstGlyph, id, success);
        glyphStorbge.setChbrIndex(firstGlyph + 1, ic, success);
        brebk;

    cbse irvCDxA:
        b = glyphStorbge[firstGlyph];
        c = glyphStorbge[lbstGlyph - 1];
        d = glyphStorbge[lbstGlyph];
        ib = glyphStorbge.getChbrIndex(firstGlyph, success);
        ic = glyphStorbge.getChbrIndex(lbstGlyph - 1, success);
        id = glyphStorbge.getChbrIndex(lbstGlyph, success);
        x = lbstGlyph - 2;

        while (x > firstGlyph) {
            glyphStorbge[x + 1] = glyphStorbge[x];
            ix = glyphStorbge.getChbrIndex(x, success);
            glyphStorbge.setChbrIndex(x + 1, ix, success);
            x -= 1;
        }

        glyphStorbge[firstGlyph] = c;
        glyphStorbge[firstGlyph + 1] = d;
        glyphStorbge[lbstGlyph] = b;

        glyphStorbge.setChbrIndex(firstGlyph, ic, success);
        glyphStorbge.setChbrIndex(firstGlyph + 1, id, success);
        glyphStorbge.setChbrIndex(lbstGlyph, ib, success);
        brebk;

    cbse irvDCxA:
        b = glyphStorbge[firstGlyph];
        c = glyphStorbge[lbstGlyph - 1];
        d = glyphStorbge[lbstGlyph];
        ib = glyphStorbge.getChbrIndex(firstGlyph, success);
        ic = glyphStorbge.getChbrIndex(lbstGlyph - 1, success);
        id = glyphStorbge.getChbrIndex(lbstGlyph, success);
        x = lbstGlyph - 2;

        while (x > firstGlyph) {
            glyphStorbge[x + 1] = glyphStorbge[x];
            ix = glyphStorbge.getChbrIndex(x, success);
            glyphStorbge.setChbrIndex(x + 1, ix, success);
            x -= 1;
        }

        glyphStorbge[firstGlyph] = d;
        glyphStorbge[firstGlyph + 1] = c;
        glyphStorbge[lbstGlyph] = b;

        glyphStorbge.setChbrIndex(firstGlyph, id, success);
        glyphStorbge.setChbrIndex(firstGlyph + 1, ic, success);
        glyphStorbge.setChbrIndex(lbstGlyph, ib, success);
        brebk;

    cbse irvDxAB:
        b = glyphStorbge[firstGlyph];
        b = glyphStorbge[firstGlyph + 1];
        d = glyphStorbge[lbstGlyph];
        ib = glyphStorbge.getChbrIndex(firstGlyph, success);
        ib = glyphStorbge.getChbrIndex(firstGlyph + 1, success);
        id = glyphStorbge.getChbrIndex(lbstGlyph, success);
        x = firstGlyph + 2;

        while (x < lbstGlyph) {
            glyphStorbge[x - 2] = glyphStorbge[x];
            ix = glyphStorbge.getChbrIndex(x, success);
            glyphStorbge.setChbrIndex(x - 2, ix, success);
            x += 1;
        }

        glyphStorbge[firstGlyph] = d;
        glyphStorbge[lbstGlyph - 1] = b;
        glyphStorbge[lbstGlyph] = b;

        glyphStorbge.setChbrIndex(firstGlyph, id, success);
        glyphStorbge.setChbrIndex(lbstGlyph - 1, ib, success);
        glyphStorbge.setChbrIndex(lbstGlyph, ib, success);
        brebk;

    cbse irvDxBA:
        b = glyphStorbge[firstGlyph];
        b = glyphStorbge[firstGlyph + 1];
        d = glyphStorbge[lbstGlyph];
        ib = glyphStorbge.getChbrIndex(firstGlyph, success);
        ib = glyphStorbge.getChbrIndex(firstGlyph + 1, success);
        id = glyphStorbge.getChbrIndex(lbstGlyph, success);
        x = firstGlyph + 2;

        while (x < lbstGlyph) {
            glyphStorbge[x - 2] = glyphStorbge[x];
            ix = glyphStorbge.getChbrIndex(x, success);
            glyphStorbge.setChbrIndex(x - 2, ix, success);
            x += 1;
        }

        glyphStorbge[firstGlyph] = d;
        glyphStorbge[lbstGlyph - 1] = b;
        glyphStorbge[lbstGlyph] = b;

        glyphStorbge.setChbrIndex(firstGlyph, id, success);
        glyphStorbge.setChbrIndex(lbstGlyph - 1, ib, success);
        glyphStorbge.setChbrIndex(lbstGlyph, ib, success);
        brebk;

    cbse irvCDxAB:
        b = glyphStorbge[firstGlyph];
        b = glyphStorbge[firstGlyph + 1];

        glyphStorbge[firstGlyph] = glyphStorbge[lbstGlyph - 1];
        glyphStorbge[firstGlyph + 1] = glyphStorbge[lbstGlyph];

        glyphStorbge[lbstGlyph - 1] = b;
        glyphStorbge[lbstGlyph] = b;

        ib = glyphStorbge.getChbrIndex(firstGlyph, success);
        ib = glyphStorbge.getChbrIndex(firstGlyph + 1, success);
        ic = glyphStorbge.getChbrIndex(lbstGlyph - 1, success);
        id = glyphStorbge.getChbrIndex(lbstGlyph, success);

        glyphStorbge.setChbrIndex(firstGlyph, ic, success);
        glyphStorbge.setChbrIndex(firstGlyph + 1, id, success);

        glyphStorbge.setChbrIndex(lbstGlyph - 1, ib, success);
        glyphStorbge.setChbrIndex(lbstGlyph, ib, success);
        brebk;

    cbse irvCDxBA:
        b = glyphStorbge[firstGlyph];
        b = glyphStorbge[firstGlyph + 1];

        glyphStorbge[firstGlyph] = glyphStorbge[lbstGlyph - 1];
        glyphStorbge[firstGlyph + 1] = glyphStorbge[lbstGlyph];

        glyphStorbge[lbstGlyph - 1] = b;
        glyphStorbge[lbstGlyph] = b;

        ib = glyphStorbge.getChbrIndex(firstGlyph, success);
        ib = glyphStorbge.getChbrIndex(firstGlyph + 1, success);
        ic = glyphStorbge.getChbrIndex(lbstGlyph - 1, success);
        id = glyphStorbge.getChbrIndex(lbstGlyph, success);

        glyphStorbge.setChbrIndex(firstGlyph, ic, success);
        glyphStorbge.setChbrIndex(firstGlyph + 1, id, success);

        glyphStorbge.setChbrIndex(lbstGlyph - 1, ib, success);
        glyphStorbge.setChbrIndex(lbstGlyph, ib, success);
        brebk;

    cbse irvDCxAB:
        b = glyphStorbge[firstGlyph];
        b = glyphStorbge[firstGlyph + 1];

        glyphStorbge[firstGlyph] = glyphStorbge[lbstGlyph];
        glyphStorbge[firstGlyph + 1] = glyphStorbge[lbstGlyph - 1];

        glyphStorbge[lbstGlyph - 1] = b;
        glyphStorbge[lbstGlyph] = b;

        ib = glyphStorbge.getChbrIndex(firstGlyph, success);
        ib = glyphStorbge.getChbrIndex(firstGlyph + 1, success);
        ic = glyphStorbge.getChbrIndex(lbstGlyph - 1, success);
        id = glyphStorbge.getChbrIndex(lbstGlyph, success);

        glyphStorbge.setChbrIndex(firstGlyph, id, success);
        glyphStorbge.setChbrIndex(firstGlyph + 1, ic, success);

        glyphStorbge.setChbrIndex(lbstGlyph - 1, ib, success);
        glyphStorbge.setChbrIndex(lbstGlyph, ib, success);
        brebk;

    cbse irvDCxBA:
        b = glyphStorbge[firstGlyph];
        b = glyphStorbge[firstGlyph + 1];

        glyphStorbge[firstGlyph] = glyphStorbge[lbstGlyph];
        glyphStorbge[firstGlyph + 1] = glyphStorbge[lbstGlyph - 1];

        glyphStorbge[lbstGlyph - 1] = b;
        glyphStorbge[lbstGlyph] = b;

        ib = glyphStorbge.getChbrIndex(firstGlyph, success);
        ib = glyphStorbge.getChbrIndex(firstGlyph + 1, success);
        ic = glyphStorbge.getChbrIndex(lbstGlyph - 1, success);
        id = glyphStorbge.getChbrIndex(lbstGlyph, success);

        glyphStorbge.setChbrIndex(firstGlyph, id, success);
        glyphStorbge.setChbrIndex(firstGlyph + 1, ic, success);

        glyphStorbge.setChbrIndex(lbstGlyph - 1, ib, success);
        glyphStorbge.setChbrIndex(lbstGlyph, ib, success);
        brebk;

    defbult:
        brebk;
    }
}

U_NAMESPACE_END
