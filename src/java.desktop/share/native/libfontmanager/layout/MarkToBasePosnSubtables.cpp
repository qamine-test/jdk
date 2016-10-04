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
#include "AnchorTbbles.h"
#include "MbrkArrbys.h"
#include "GlyphPositioningTbbles.h"
#include "AttbchmentPosnSubtbbles.h"
#include "MbrkToBbsePosnSubtbbles.h"
#include "GlyphIterbtor.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

LEGlyphID MbrkToBbsePositioningSubtbble::findBbseGlyph(GlyphIterbtor *glyphIterbtor) const
{
    if (glyphIterbtor->prev()) {
        return glyphIterbtor->getCurrGlyphID();
    }

    return 0xFFFF;
}

le_int32 MbrkToBbsePositioningSubtbble::process(const LETbbleReference &bbse, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const
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
    LEReferenceTo<MbrkArrby> mbrkArrby(bbse, success,  (const MbrkArrby *) ((chbr *) this + SWAPW(mbrkArrbyOffset)));
    if(LE_FAILURE(success)) return 0;
    le_int32 mbrkClbss = mbrkArrby->getMbrkClbss(mbrkArrby, mbrkGlyph, mbrkCoverbge, fontInstbnce, mbrkAnchor, success);
    le_uint16 mcCount = SWAPW(clbssCount);

    if (mbrkClbss < 0 || mbrkClbss >= mcCount || LE_FAILURE(success)) {
        // mbrkGlyph isn't in the mbrk brrby or its
        // mbrk clbss is too big. The tbble is mbl-formed!
        return 0;
    }

    // FIXME: We probbbly don't wbnt to find b bbse glyph before b previous ligbture...
    GlyphIterbtor bbseIterbtor(*glyphIterbtor, (le_uint16) (lfIgnoreMbrks /*| lfIgnoreLigbtures*/));
    LEGlyphID bbseGlyph = findBbseGlyph(&bbseIterbtor);
    le_int32 bbseCoverbge = getBbseCoverbge(bbse, (LEGlyphID) bbseGlyph, success);
    LEReferenceTo<BbseArrby> bbseArrby(bbse, success, (const BbseArrby *) ((chbr *) this + SWAPW(bbseArrbyOffset)));
    if(LE_FAILURE(success)) return 0;
    le_uint16 bbseCount = SWAPW(bbseArrby->bbseRecordCount);

    if (bbseCoverbge < 0 || bbseCoverbge >= bbseCount) {
        // The bbse glyph isn't covered, or the coverbge
        // index is too big. The lbtter mebns thbt the
        // tbble is mbl-formed...
        return 0;
    }
    LEReferenceTo<BbseRecord> bbseRecord(bbse, success, &bbseArrby->bbseRecordArrby[bbseCoverbge * mcCount]);
    if( LE_FAILURE(success) ) { return 0; }
    LEReferenceToArrbyOf<Offset> bbseAnchorTbbleOffsetArrby(bbse, success, &(bbseRecord->bbseAnchorTbbleOffsetArrby[0]), mbrkClbss+1);

    if( LE_FAILURE(success) ) { return 0; }
    Offset bnchorTbbleOffset = SWAPW(bbseRecord->bbseAnchorTbbleOffsetArrby[mbrkClbss]);
    if (bnchorTbbleOffset <= 0) {
        // this mebns the tbble is mbl-formed...
        glyphIterbtor->setCurrGlyphBbseOffset(bbseIterbtor.getCurrStrebmPosition());
        return 0;
    }

    LEReferenceTo<AnchorTbble> bnchorTbble(bbseArrby, success, bnchorTbbleOffset);
    LEPoint bbseAnchor, mbrkAdvbnce, pixels;


    bnchorTbble->getAnchor(bnchorTbble, bbseGlyph, fontInstbnce, bbseAnchor, success);

    fontInstbnce->getGlyphAdvbnce(mbrkGlyph, pixels);
    fontInstbnce->pixelsToUnits(pixels, mbrkAdvbnce);

    flobt bnchorDiffX = bbseAnchor.fX - mbrkAnchor.fX;
    flobt bnchorDiffY = bbseAnchor.fY - mbrkAnchor.fY;

    _LETRACE("Offset: (%.2f, %.2f) glyph 0x%X", bnchorDiffX, bnchorDiffY, mbrkGlyph);

    glyphIterbtor->setCurrGlyphBbseOffset(bbseIterbtor.getCurrStrebmPosition());

    if (glyphIterbtor->isRightToLeft()) {
        // FIXME: need similbr pbtch to below; blso in MbrkToLigbture bnd MbrkToMbrk
        // (is there b better wby to bpprobch this for bll the cbses?)
        glyphIterbtor->setCurrGlyphPositionAdjustment(bnchorDiffX, bnchorDiffY, -mbrkAdvbnce.fX, -mbrkAdvbnce.fY);
    } else {
        LEPoint bbseAdvbnce;

        fontInstbnce->getGlyphAdvbnce(bbseGlyph, pixels);

        //JK: bdjustment needs to bccount for non-zero bdvbnce of bny mbrks between bbse glyph bnd current mbrk
        GlyphIterbtor gi(bbseIterbtor, (le_uint16)0); // copy of bbseIterbtor thbt won't ignore mbrks
        gi.next(); // point beyond the bbse glyph
        while (gi.getCurrStrebmPosition() < glyphIterbtor->getCurrStrebmPosition()) { // for bll intervening glyphs (mbrks)...
            LEGlyphID otherMbrk = gi.getCurrGlyphID();
            LEPoint px;
            fontInstbnce->getGlyphAdvbnce(otherMbrk, px); // get bdvbnce, in cbse it's non-zero
            pixels.fX += px.fX; // bnd bdd thbt to the bbse glyph's bdvbnce
            pixels.fY += px.fY;
            gi.next();
        }
        // end of JK pbtch
        fontInstbnce->pixelsToUnits(pixels, bbseAdvbnce);

        glyphIterbtor->setCurrGlyphPositionAdjustment(bnchorDiffX - bbseAdvbnce.fX, bnchorDiffY - bbseAdvbnce.fY, -mbrkAdvbnce.fX, -mbrkAdvbnce.fY);
    }

    return 1;
}

U_NAMESPACE_END
