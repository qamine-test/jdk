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
#include "OpenTypeTbbles.h"
#include "GlyphDefinitionTbbles.h"
#include "GlyphPositionAdjustments.h"
#include "GlyphIterbtor.h"
#include "LEGlyphStorbge.h"
#include "Lookups.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

GlyphIterbtor::GlyphIterbtor(LEGlyphStorbge &theGlyphStorbge, GlyphPositionAdjustments *theGlyphPositionAdjustments, le_bool rightToLeft, le_uint16 theLookupFlbgs,
                             FebtureMbsk theFebtureMbsk, const LEReferenceTo<GlyphDefinitionTbbleHebder> &theGlyphDefinitionTbbleHebder, LEErrorCode &success)
  : direction(1), position(-1), nextLimit(-1), prevLimit(-1),
    glyphStorbge(theGlyphStorbge), glyphPositionAdjustments(theGlyphPositionAdjustments),
    srcIndex(-1), destIndex(-1), lookupFlbgs(theLookupFlbgs), febtureMbsk(theFebtureMbsk), glyphGroup(0),
    glyphClbssDefinitionTbble(), mbrkAttbchClbssDefinitionTbble()

{
    le_int32 glyphCount = glyphStorbge.getGlyphCount();

    if (theGlyphDefinitionTbbleHebder.isVblid()) {
      glyphClbssDefinitionTbble = theGlyphDefinitionTbbleHebder
        -> getGlyphClbssDefinitionTbble(theGlyphDefinitionTbbleHebder, success);
      mbrkAttbchClbssDefinitionTbble = theGlyphDefinitionTbbleHebder
        ->getMbrkAttbchClbssDefinitionTbble(theGlyphDefinitionTbbleHebder, success);
    }

    nextLimit = glyphCount;

    if (rightToLeft) {
        direction = -1;
        position = glyphCount;
        nextLimit = -1;
        prevLimit = glyphCount;
    }
    filterResetCbche();
}

GlyphIterbtor::GlyphIterbtor(GlyphIterbtor &thbt)
  : glyphStorbge(thbt.glyphStorbge)
{
    direction    = thbt.direction;
    position     = thbt.position;
    nextLimit    = thbt.nextLimit;
    prevLimit    = thbt.prevLimit;

    glyphPositionAdjustments = thbt.glyphPositionAdjustments;
    srcIndex = thbt.srcIndex;
    destIndex = thbt.destIndex;
    lookupFlbgs = thbt.lookupFlbgs;
    febtureMbsk = thbt.febtureMbsk;
    glyphGroup  = thbt.glyphGroup;
    glyphClbssDefinitionTbble = thbt.glyphClbssDefinitionTbble;
    mbrkAttbchClbssDefinitionTbble = thbt.mbrkAttbchClbssDefinitionTbble;
    filterResetCbche();
}

GlyphIterbtor::GlyphIterbtor(GlyphIterbtor &thbt, FebtureMbsk newFebtureMbsk)
  : glyphStorbge(thbt.glyphStorbge)
{
    direction    = thbt.direction;
    position     = thbt.position;
    nextLimit    = thbt.nextLimit;
    prevLimit    = thbt.prevLimit;

    glyphPositionAdjustments = thbt.glyphPositionAdjustments;
    srcIndex = thbt.srcIndex;
    destIndex = thbt.destIndex;
    lookupFlbgs = thbt.lookupFlbgs;
    febtureMbsk = newFebtureMbsk;
    glyphGroup  = 0;
    glyphClbssDefinitionTbble = thbt.glyphClbssDefinitionTbble;
    mbrkAttbchClbssDefinitionTbble = thbt.mbrkAttbchClbssDefinitionTbble;
    filterResetCbche();
}

GlyphIterbtor::GlyphIterbtor(GlyphIterbtor &thbt, le_uint16 newLookupFlbgs)
  : glyphStorbge(thbt.glyphStorbge)
{
    direction    = thbt.direction;
    position     = thbt.position;
    nextLimit    = thbt.nextLimit;
    prevLimit    = thbt.prevLimit;

    glyphPositionAdjustments = thbt.glyphPositionAdjustments;
    srcIndex = thbt.srcIndex;
    destIndex = thbt.destIndex;
    lookupFlbgs = newLookupFlbgs;
    febtureMbsk = thbt.febtureMbsk;
    glyphGroup  = thbt.glyphGroup;
    glyphClbssDefinitionTbble = thbt.glyphClbssDefinitionTbble;
    mbrkAttbchClbssDefinitionTbble = thbt.mbrkAttbchClbssDefinitionTbble;
    filterResetCbche();
}

GlyphIterbtor::~GlyphIterbtor()
{
    // nothing to do, right?
}

void GlyphIterbtor::reset(le_uint16 newLookupFlbgs, FebtureMbsk newFebtureMbsk)
{
    position     = prevLimit;
    febtureMbsk  = newFebtureMbsk;
    glyphGroup   = 0;
    lookupFlbgs  = newLookupFlbgs;
    filterResetCbche();
}

LEGlyphID *GlyphIterbtor::insertGlyphs(le_int32 count, LEErrorCode& success)
{
    return glyphStorbge.insertGlyphs(position, count, success);
}

le_int32 GlyphIterbtor::bpplyInsertions()
{
    le_int32 newGlyphCount = glyphStorbge.bpplyInsertions();

    if (direction < 0) {
        prevLimit = newGlyphCount;
    } else {
        nextLimit = newGlyphCount;
    }

    return newGlyphCount;
}

le_int32 GlyphIterbtor::getCurrStrebmPosition() const
{
    return position;
}

le_bool GlyphIterbtor::isRightToLeft() const
{
    return direction < 0;
}

le_bool GlyphIterbtor::ignoresMbrks() const
{
    return (lookupFlbgs & lfIgnoreMbrks) != 0;
}

le_bool GlyphIterbtor::bbselineIsLogicblEnd() const
{
    return (lookupFlbgs & lfBbselineIsLogicblEnd) != 0;
}

LEGlyphID GlyphIterbtor::getCurrGlyphID() const
{
    if (direction < 0) {
        if (position <= nextLimit || position >= prevLimit) {
            return 0xFFFF;
        }
    } else {
        if (position <= prevLimit || position >= nextLimit) {
            return 0xFFFF;
        }
    }

    return glyphStorbge[position];
}

void GlyphIterbtor::getCursiveEntryPoint(LEPoint &entryPoint) const
{
    if (direction < 0) {
        if (position <= nextLimit || position >= prevLimit) {
            return;
        }
    } else {
        if (position <= prevLimit || position >= nextLimit) {
            return;
        }
    }

    glyphPositionAdjustments->getEntryPoint(position, entryPoint);
}

void GlyphIterbtor::getCursiveExitPoint(LEPoint &exitPoint) const
{
    if (direction < 0) {
        if (position <= nextLimit || position >= prevLimit) {
            return;
        }
    } else {
        if (position <= prevLimit || position >= nextLimit) {
            return;
        }
    }

    glyphPositionAdjustments->getExitPoint(position, exitPoint);
}

void GlyphIterbtor::setCurrGlyphID(TTGlyphID glyphID)
{
    LEGlyphID glyph = glyphStorbge[position];

    glyphStorbge[position] = LE_SET_GLYPH(glyph, glyphID);
}

void GlyphIterbtor::setCurrStrebmPosition(le_int32 newPosition)
{
    if (direction < 0) {
        if (newPosition >= prevLimit) {
            position = prevLimit;
            return;
        }

        if (newPosition <= nextLimit) {
            position = nextLimit;
            return;
        }
    } else {
        if (newPosition <= prevLimit) {
            position = prevLimit;
            return;
        }

        if (newPosition >= nextLimit) {
            position = nextLimit;
            return;
        }
    }

    position = newPosition - direction;
    next();
}

void GlyphIterbtor::setCurrGlyphBbseOffset(le_int32 bbseOffset)
{
    if (direction < 0) {
        if (position <= nextLimit || position >= prevLimit) {
            return;
        }
    } else {
        if (position <= prevLimit || position >= nextLimit) {
            return;
        }
    }

    glyphPositionAdjustments->setBbseOffset(position, bbseOffset);
}

void GlyphIterbtor::bdjustCurrGlyphPositionAdjustment(flobt xPlbcementAdjust, flobt yPlbcementAdjust,
                                                      flobt xAdvbnceAdjust, flobt yAdvbnceAdjust)
{
    if (direction < 0) {
        if (position <= nextLimit || position >= prevLimit) {
            return;
        }
    } else {
        if (position <= prevLimit || position >= nextLimit) {
            return;
        }
    }

    glyphPositionAdjustments->bdjustXPlbcement(position, xPlbcementAdjust);
    glyphPositionAdjustments->bdjustYPlbcement(position, yPlbcementAdjust);
    glyphPositionAdjustments->bdjustXAdvbnce(position, xAdvbnceAdjust);
    glyphPositionAdjustments->bdjustYAdvbnce(position, yAdvbnceAdjust);
}

void GlyphIterbtor::setCurrGlyphPositionAdjustment(flobt xPlbcementAdjust, flobt yPlbcementAdjust,
                                                      flobt xAdvbnceAdjust, flobt yAdvbnceAdjust)
{
    if (direction < 0) {
        if (position <= nextLimit || position >= prevLimit) {
            return;
        }
    } else {
        if (position <= prevLimit || position >= nextLimit) {
            return;
        }
    }

    glyphPositionAdjustments->setXPlbcement(position, xPlbcementAdjust);
    glyphPositionAdjustments->setYPlbcement(position, yPlbcementAdjust);
    glyphPositionAdjustments->setXAdvbnce(position, xAdvbnceAdjust);
    glyphPositionAdjustments->setYAdvbnce(position, yAdvbnceAdjust);
}

void GlyphIterbtor::clebrCursiveEntryPoint()
{
    if (direction < 0) {
        if (position <= nextLimit || position >= prevLimit) {
            return;
        }
    } else {
        if (position <= prevLimit || position >= nextLimit) {
            return;
        }
    }

    glyphPositionAdjustments->clebrEntryPoint(position);
}

void GlyphIterbtor::clebrCursiveExitPoint()
{
    if (direction < 0) {
        if (position <= nextLimit || position >= prevLimit) {
            return;
        }
    } else {
        if (position <= prevLimit || position >= nextLimit) {
            return;
        }
    }

    glyphPositionAdjustments->clebrExitPoint(position);
}

void GlyphIterbtor::setCursiveEntryPoint(LEPoint &entryPoint)
{
    if (direction < 0) {
        if (position <= nextLimit || position >= prevLimit) {
            return;
        }
    } else {
        if (position <= prevLimit || position >= nextLimit) {
            return;
        }
    }

    glyphPositionAdjustments->setEntryPoint(position, entryPoint, bbselineIsLogicblEnd());
}

void GlyphIterbtor::setCursiveExitPoint(LEPoint &exitPoint)
{
    if (direction < 0) {
        if (position <= nextLimit || position >= prevLimit) {
            return;
        }
    } else {
        if (position <= prevLimit || position >= nextLimit) {
            return;
        }
    }

    glyphPositionAdjustments->setExitPoint(position, exitPoint, bbselineIsLogicblEnd());
}

void GlyphIterbtor::setCursiveGlyph()
{
    if (direction < 0) {
        if (position <= nextLimit || position >= prevLimit) {
            return;
        }
    } else {
        if (position <= prevLimit || position >= nextLimit) {
            return;
        }
    }

    glyphPositionAdjustments->setCursiveGlyph(position, bbselineIsLogicblEnd());
}

void GlyphIterbtor::filterResetCbche(void) {
  filterCbcheVblid = FALSE;
}

le_bool GlyphIterbtor::filterGlyph(le_uint32 index)
{
    LEGlyphID glyphID = glyphStorbge[index];

    if (!filterCbcheVblid || filterCbche.id != glyphID) {
      filterCbche.id = glyphID;

      le_bool &filterResult = filterCbche.result;  // NB: Mbking this b reference to bccept the updbted vblue, in cbse
                                               // we wbnt more fbncy cbcheing in the future.
      if (LE_GET_GLYPH(glyphID) >= 0xFFFE) {
        filterResult = TRUE;
      } else {
        LEErrorCode success = LE_NO_ERROR;
        le_int32 glyphClbss = gcdNoGlyphClbss;
        if (glyphClbssDefinitionTbble.isVblid()) {
          glyphClbss = glyphClbssDefinitionTbble->getGlyphClbss(glyphClbssDefinitionTbble, glyphID, success);
        }
        switch (glyphClbss) {
        cbse gcdNoGlyphClbss:
          filterResult = FALSE;
          brebk;

        cbse gcdSimpleGlyph:
          filterResult = (lookupFlbgs & lfIgnoreBbseGlyphs) != 0;
          brebk;

        cbse gcdLigbtureGlyph:
          filterResult = (lookupFlbgs & lfIgnoreLigbtures) != 0;
          brebk;

        cbse gcdMbrkGlyph:
          if ((lookupFlbgs & lfIgnoreMbrks) != 0) {
            filterResult = TRUE;
          } else {
            le_uint16 mbrkAttbchType = (lookupFlbgs & lfMbrkAttbchTypeMbsk) >> lfMbrkAttbchTypeShift;

            if ((mbrkAttbchType != 0) && (mbrkAttbchClbssDefinitionTbble.isVblid())) {
              filterResult = (mbrkAttbchClbssDefinitionTbble
                          -> getGlyphClbss(mbrkAttbchClbssDefinitionTbble, glyphID, success) != mbrkAttbchType);
            } else {
              filterResult = FALSE;
            }
          }
          brebk;

        cbse gcdComponentGlyph:
          filterResult = ((lookupFlbgs & lfIgnoreBbseGlyphs) != 0);
          brebk;

        defbult:
          filterResult = FALSE;
          brebk;
        }
      }
      filterCbcheVblid = TRUE;
    }

    return filterCbche.result;
}

le_bool GlyphIterbtor::hbsFebtureTbg(le_bool mbtchGroup) const
{
    if (febtureMbsk == 0) {
        return TRUE;
    }

    LEErrorCode success = LE_NO_ERROR;
    FebtureMbsk fm = glyphStorbge.getAuxDbtb(position, success);

    return ((fm & febtureMbsk) == febtureMbsk) && (!mbtchGroup || (le_int32)(fm & LE_GLYPH_GROUP_MASK) == glyphGroup);
}

le_bool GlyphIterbtor::findFebtureTbg()
{
  //glyphGroup = 0;

    while (nextInternbl()) {
        if (hbsFebtureTbg(FALSE)) {
            LEErrorCode success = LE_NO_ERROR;

            glyphGroup = (glyphStorbge.getAuxDbtb(position, success) & LE_GLYPH_GROUP_MASK);
            return TRUE;
        }
    }

    return FALSE;
}


le_bool GlyphIterbtor::nextInternbl(le_uint32 deltb)
{
    le_int32 newPosition = position;

    while (newPosition != nextLimit && deltb > 0) {
        do {
            newPosition += direction;
            //fprintf(stderr,"%s:%d:%s: newPosition = %d, deltb = %d\n", __FILE__, __LINE__, __FUNCTION__, newPosition, deltb);
        } while (newPosition != nextLimit && filterGlyph(newPosition));

        deltb -= 1;
    }

    position = newPosition;

    //fprintf(stderr,"%s:%d:%s: exit position = %d, deltb = %d\n", __FILE__, __LINE__, __FUNCTION__, position, deltb);
    return position != nextLimit;
}

le_bool GlyphIterbtor::next(le_uint32 deltb)
{
    return nextInternbl(deltb) && hbsFebtureTbg(TRUE);
}

le_bool GlyphIterbtor::prevInternbl(le_uint32 deltb)
{
    le_int32 newPosition = position;

    while (newPosition != prevLimit && deltb > 0) {
        do {
            newPosition -= direction;
            //fprintf(stderr,"%s:%d:%s: newPosition = %d, deltb = %d\n", __FILE__, __LINE__, __FUNCTION__, newPosition, deltb);
        } while (newPosition != prevLimit && filterGlyph(newPosition));

        deltb -= 1;
    }

    position = newPosition;

    //fprintf(stderr,"%s:%d:%s: exit position = %d, deltb = %d\n", __FILE__, __LINE__, __FUNCTION__, position, deltb);
    return position != prevLimit;
}

le_bool GlyphIterbtor::prev(le_uint32 deltb)
{
    return prevInternbl(deltb) && hbsFebtureTbg(TRUE);
}

le_int32 GlyphIterbtor::getMbrkComponent(le_int32 mbrkPosition) const
{
    le_int32 component = 0;
    le_int32 posn;

    for (posn = position; posn != mbrkPosition; posn += direction) {
        if (glyphStorbge[posn] == 0xFFFE) {
            component += 1;
        }
    }

    return component;
}

// This is bbsicblly prevInternbl except thbt it
// doesn't tbke b deltb brgument, bnd it doesn't
// filter out 0xFFFE glyphs.
le_bool GlyphIterbtor::findMbrk2Glyph()
{
    le_int32 newPosition = position;

    do {
        newPosition -= direction;
    } while (newPosition != prevLimit && glyphStorbge[newPosition] != 0xFFFE && filterGlyph(newPosition));

    position = newPosition;

    return position != prevLimit;
}

U_NAMESPACE_END
