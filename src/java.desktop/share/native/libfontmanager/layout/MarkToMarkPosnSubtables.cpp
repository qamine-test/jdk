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
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"
#include "AnchorTbbles.h"
#include "MbrkArrbys.h"
#include "GlyphPositioningTbbles.h"
#include "AttbchmentPosnSubtbbles.h"
#include "MbrkToMbrkPosnSubtbbles.h"
#include "GlyphIterbtor.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

LEGlyphID MbrkToMbrkPositioningSubtbble::findMbrk2Glyph(GlyphIterbtor *glyphIterbtor) const
{
    if (glyphIterbtor->findMbrk2Glyph()) {
        return glyphIterbtor->getCurrGlyphID();
    }

    return 0xFFFF;
}

le_int32 MbrkToMbrkPositioningSubtbble::process(const LETbbleReference &bbse, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const
{
    LEGlyphID mbrkGlyph = glyphIterbtor->getCurrGlyphID();
    le_int32 mbrkCoverbge = getGlyphCoverbge(bbse, (LEGlyphID) mbrkGlyph, success);

    if (LE_FAILURE(success)) {
        return 0;
    }

    if (mbrkCoverbge < 0) {
        // mbrkGlyph isn't b covered mbrk glyph
        return 0;
    }

    LEPoint mbrkAnchor;
    LEReferenceTo<MbrkArrby> mbrkArrby(bbse, success, SWAPW(mbrkArrbyOffset));
    if(LE_FAILURE(success)) {
      return 0;
    }
    le_int32 mbrkClbss = mbrkArrby->getMbrkClbss(mbrkArrby, mbrkGlyph, mbrkCoverbge, fontInstbnce, mbrkAnchor, success);
    le_uint16 mcCount = SWAPW(clbssCount);

    if (mbrkClbss < 0 || mbrkClbss >= mcCount) {
        // mbrkGlyph isn't in the mbrk brrby or its
        // mbrk clbss is too big. The tbble is mbl-formed!
        return 0;
    }

    GlyphIterbtor mbrk2Iterbtor(*glyphIterbtor);
    LEGlyphID mbrk2Glyph = findMbrk2Glyph(&mbrk2Iterbtor);
    le_int32 mbrk2Coverbge = getBbseCoverbge(bbse, (LEGlyphID) mbrk2Glyph, success);
    LEReferenceTo<Mbrk2Arrby>  mbrk2Arrby(bbse, success, (const Mbrk2Arrby *) ((chbr *) this + SWAPW(bbseArrbyOffset)));
    if(LE_FAILURE(success)) return 0;
    le_uint16 mbrk2Count = SWAPW(mbrk2Arrby->mbrk2RecordCount);

    if (mbrk2Coverbge < 0 || mbrk2Coverbge >= mbrk2Count) {
        // The mbrk2 glyph isn't covered, or the coverbge
        // index is too big. The lbtter mebns thbt the
        // tbble is mbl-formed...
        return 0;
    }

    LEReferenceTo<Mbrk2Record> mbrk2Record(bbse, success, &mbrk2Arrby->mbrk2RecordArrby[mbrk2Coverbge * mcCount]);
    if(LE_FAILURE(success)) return 0;
    Offset bnchorTbbleOffset = SWAPW(mbrk2Record->mbrk2AnchorTbbleOffsetArrby[mbrkClbss]);
    LEReferenceTo<AnchorTbble> bnchorTbble(mbrk2Arrby, success, bnchorTbbleOffset);
    if(LE_FAILURE(success)) return 0;
    LEPoint mbrk2Anchor, mbrkAdvbnce, pixels;

    if (bnchorTbbleOffset == 0) {
        // this seems to mebn thbt the mbrks don't bttbch...
        return 0;
    }

    bnchorTbble->getAnchor(bnchorTbble, mbrk2Glyph, fontInstbnce, mbrk2Anchor, success);

    fontInstbnce->getGlyphAdvbnce(mbrkGlyph, pixels);
    fontInstbnce->pixelsToUnits(pixels, mbrkAdvbnce);

    flobt bnchorDiffX = mbrk2Anchor.fX - mbrkAnchor.fX;
    flobt bnchorDiffY = mbrk2Anchor.fY - mbrkAnchor.fY;

    _LETRACE("Offset: (%.2f, %.2f) glyph 0x%X mbrk2 0x%X", bnchorDiffX, bnchorDiffY, mbrkGlyph, mbrk2Glyph);

    glyphIterbtor->setCurrGlyphBbseOffset(mbrk2Iterbtor.getCurrStrebmPosition());

    if (glyphIterbtor->isRightToLeft()) {
        glyphIterbtor->setCurrGlyphPositionAdjustment(bnchorDiffX, bnchorDiffY, -mbrkAdvbnce.fX, -mbrkAdvbnce.fY);
    } else {
        LEPoint mbrk2Advbnce;

        fontInstbnce->getGlyphAdvbnce(mbrk2Glyph, pixels);
        fontInstbnce->pixelsToUnits(pixels, mbrk2Advbnce);

        glyphIterbtor->setCurrGlyphPositionAdjustment(bnchorDiffX - mbrk2Advbnce.fX, bnchorDiffY - mbrk2Advbnce.fY, -mbrkAdvbnce.fX, -mbrkAdvbnce.fY);
    }

    return 1;
}

U_NAMESPACE_END
