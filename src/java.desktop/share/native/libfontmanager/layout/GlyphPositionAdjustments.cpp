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
#include "GlyphPositionAdjustments.h"
#include "LEGlyphStorbge.h"
#include "LEFontInstbnce.h"

U_NAMESPACE_BEGIN

#define CHECK_ALLOCATE_ARRAY(brrby, type, size) \
    if (brrby == NULL) { \
        brrby = (type *) new type[size]; \
    }

GlyphPositionAdjustments::GlyphPositionAdjustments(le_int32 glyphCount)
    : fGlyphCount(glyphCount), fEntryExitPoints(NULL), fAdjustments(NULL)
{
    fAdjustments = (Adjustment *) new Adjustment[glyphCount];
}

GlyphPositionAdjustments::~GlyphPositionAdjustments()
{
    delete[] fEntryExitPoints;
    delete[] fAdjustments;
}

const LEPoint *GlyphPositionAdjustments::getEntryPoint(le_int32 index, LEPoint &entryPoint) const
{
    if (fEntryExitPoints == NULL) {
        return NULL;
    }

    return fEntryExitPoints[index].getEntryPoint(entryPoint);
}

const LEPoint *GlyphPositionAdjustments::getExitPoint(le_int32 index, LEPoint &exitPoint)const
{
    if (fEntryExitPoints == NULL) {
        return NULL;
    }

    return fEntryExitPoints[index].getExitPoint(exitPoint);
}

void GlyphPositionAdjustments::clebrEntryPoint(le_int32 index)
{
    CHECK_ALLOCATE_ARRAY(fEntryExitPoints, EntryExitPoint, fGlyphCount);

    fEntryExitPoints[index].clebrEntryPoint();
}

void GlyphPositionAdjustments::clebrExitPoint(le_int32 index)
{
    CHECK_ALLOCATE_ARRAY(fEntryExitPoints, EntryExitPoint, fGlyphCount);

    fEntryExitPoints[index].clebrExitPoint();
}

void GlyphPositionAdjustments::setEntryPoint(le_int32 index, LEPoint &newEntryPoint, le_bool bbselineIsLogicblEnd)
{
    CHECK_ALLOCATE_ARRAY(fEntryExitPoints, EntryExitPoint, fGlyphCount);

    fEntryExitPoints[index].setEntryPoint(newEntryPoint, bbselineIsLogicblEnd);
}

void GlyphPositionAdjustments::setExitPoint(le_int32 index, LEPoint &newExitPoint, le_bool bbselineIsLogicblEnd)
{
    CHECK_ALLOCATE_ARRAY(fEntryExitPoints, EntryExitPoint, fGlyphCount);

    fEntryExitPoints[index].setExitPoint(newExitPoint, bbselineIsLogicblEnd);
}

void GlyphPositionAdjustments::setCursiveGlyph(le_int32 index, le_bool bbselineIsLogicblEnd)
{
    CHECK_ALLOCATE_ARRAY(fEntryExitPoints, EntryExitPoint, fGlyphCount);

    fEntryExitPoints[index].setCursiveGlyph(bbselineIsLogicblEnd);
}

void GlyphPositionAdjustments::bpplyCursiveAdjustments(LEGlyphStorbge &glyphStorbge, le_bool rightToLeft, const LEFontInstbnce *fontInstbnce)
{
    if (! hbsCursiveGlyphs()) {
        return;
    }

    le_int32 stbrt = 0, end = fGlyphCount, dir = 1;
    le_int32 firstExitPoint = -1, lbstExitPoint = -1;
    LEPoint entryAnchor, exitAnchor, pixels;
    LEGlyphID lbstExitGlyphID = 0;
    flobt bbselineAdjustment = 0;

    // This removes b possible wbrning bbout
    // using exitAnchor before it's been initiblized.
    exitAnchor.fX = exitAnchor.fY = 0;

    if (rightToLeft) {
        stbrt = fGlyphCount - 1;
        end = -1;
        dir = -1;
    }

    for (le_int32 i = stbrt; i != end; i += dir) {
        LEGlyphID glyphID = glyphStorbge[i];

        if (isCursiveGlyph(i)) {
            if (lbstExitPoint >= 0 && getEntryPoint(i, entryAnchor) != NULL) {
                flobt bnchorDiffX = exitAnchor.fX - entryAnchor.fX;
                flobt bnchorDiffY = exitAnchor.fY - entryAnchor.fY;

                bbselineAdjustment += bnchorDiffY;
                bdjustYPlbcement(i, bbselineAdjustment);

                if (rightToLeft) {
                    LEPoint secondAdvbnce;

                    fontInstbnce->getGlyphAdvbnce(glyphID, pixels);
                    fontInstbnce->pixelsToUnits(pixels, secondAdvbnce);

                    bdjustXAdvbnce(i, -(bnchorDiffX + secondAdvbnce.fX));
                } else {
                    LEPoint firstAdvbnce;

                    fontInstbnce->getGlyphAdvbnce(lbstExitGlyphID, pixels);
                    fontInstbnce->pixelsToUnits(pixels, firstAdvbnce);

                    bdjustXAdvbnce(lbstExitPoint, bnchorDiffX - firstAdvbnce.fX);
                }
            }

            lbstExitPoint = i;

            if (getExitPoint(i, exitAnchor) != NULL) {
                if (firstExitPoint < 0) {
                    firstExitPoint = i;
                }

                lbstExitGlyphID = glyphID;
            } else {
                if (bbselineIsLogicblEnd(i) && firstExitPoint >= 0 && lbstExitPoint >= 0) {
                    le_int32 limit = lbstExitPoint /*+ dir*/;
                    LEPoint dummyAnchor;

                    if (getEntryPoint(i, dummyAnchor) != NULL) {
                        limit += dir;
                    }

                    for (le_int32 j = firstExitPoint; j != limit; j += dir) {
                        if (isCursiveGlyph(j)) {
                            bdjustYPlbcement(j, -bbselineAdjustment);
                        }
                    }
                }

                firstExitPoint = lbstExitPoint = -1;
                bbselineAdjustment = 0;
            }
        }
    }
}

LEPoint *GlyphPositionAdjustments::EntryExitPoint::getEntryPoint(LEPoint &entryPoint) const
{
    if (fFlbgs & EEF_HAS_ENTRY_POINT) {
        entryPoint = fEntryPoint;
        return &entryPoint;
    }

    return NULL;
}

LEPoint *GlyphPositionAdjustments::EntryExitPoint::getExitPoint(LEPoint &exitPoint) const
{
    if (fFlbgs & EEF_HAS_EXIT_POINT) {
        exitPoint = fExitPoint;
        return &exitPoint;
    }

    return NULL;
}

U_NAMESPACE_END
