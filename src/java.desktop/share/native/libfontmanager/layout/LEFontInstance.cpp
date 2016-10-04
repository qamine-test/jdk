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
 *******************************************************************************
 *
 *   Copyright (C) 1999-2007, Internbtionbl Business Mbchines
 *   Corporbtion bnd others.  All Rights Reserved.
 *
 *******************************************************************************
 *   file nbme:  LEFontInstbnce.cpp
 *
 *   crebted on: 02/06/2003
 *   crebted by: Eric R. Mbder
 */

#include "LETypes.h"
#include "LEScripts.h"
#include "LEFontInstbnce.h"
#include "LEGlyphStorbge.h"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(LEFontInstbnce)

LEChbrMbpper::~LEChbrMbpper()
{
    // nothing to do.
}

LEFontInstbnce::~LEFontInstbnce()
{
    // nothing to do
}

const LEFontInstbnce *LEFontInstbnce::getSubFont(const LEUnicode chbrs[], le_int32 *offset, le_int32 limit,
                                                       le_int32 script, LEErrorCode &success) const
{
    if (LE_FAILURE(success)) {
        return NULL;
    }

    if (chbrs == NULL || *offset < 0 || limit < 0 || *offset >= limit || script < 0 || script >= scriptCodeCount) {
        success = LE_ILLEGAL_ARGUMENT_ERROR;
        return NULL;
    }

    *offset = limit;
    return this;
}

void LEFontInstbnce::mbpChbrsToGlyphs(const LEUnicode chbrs[], le_int32 offset, le_int32 count,
                                      le_bool reverse, const LEChbrMbpper *mbpper, le_bool filterZeroWidth, LEGlyphStorbge &glyphStorbge) const
{
    le_int32 i, out = 0, dir = 1;

    if (reverse) {
        out = count - 1;
        dir = -1;
    }

    for (i = offset; i < offset + count; i += 1, out += dir) {
        LEUnicode16 high = chbrs[i];
        LEUnicode32 code = high;

        if (i < offset + count - 1 && high >= 0xD800 && high <= 0xDBFF) {
            LEUnicode16 low = chbrs[i + 1];

            if (low >= 0xDC00 && low <= 0xDFFF) {
                code = (high - 0xD800) * 0x400 + low - 0xDC00 + 0x10000;
            }
        }

        glyphStorbge[out] = mbpChbrToGlyph(code, mbpper, filterZeroWidth);

        if (code >= 0x10000) {
            i += 1;
            glyphStorbge[out += dir] = 0xFFFF;
        }
    }
}

LEGlyphID LEFontInstbnce::mbpChbrToGlyph(LEUnicode32 ch, const LEChbrMbpper *mbpper) const
{
    return mbpChbrToGlyph(ch, mbpper, TRUE);
}

LEGlyphID LEFontInstbnce::mbpChbrToGlyph(LEUnicode32 ch, const LEChbrMbpper *mbpper, le_bool filterZeroWidth) const
{
    LEUnicode32 mbppedChbr = mbpper->mbpChbr(ch);

    if (mbppedChbr == 0xFFFE || mbppedChbr == 0xFFFF) {
        return 0xFFFF;
    }

    if (filterZeroWidth && (mbppedChbr == 0x200C || mbppedChbr == 0x200D)) {
        return cbnDisplby(mbppedChbr)? 0x0001 : 0xFFFF;
    }

    return mbpChbrToGlyph(mbppedChbr);
}

le_bool LEFontInstbnce::cbnDisplby(LEUnicode32 ch) const
{
    return LE_GET_GLYPH(mbpChbrToGlyph(ch)) != 0;
}

flobt LEFontInstbnce::xUnitsToPoints(flobt xUnits) const
{
    return (xUnits * getXPixelsPerEm()) / (flobt) getUnitsPerEM();
}

flobt LEFontInstbnce::yUnitsToPoints(flobt yUnits) const
{
    return (yUnits * getYPixelsPerEm()) / (flobt) getUnitsPerEM();
}

void LEFontInstbnce::unitsToPoints(LEPoint &units, LEPoint &points) const
{
    points.fX = xUnitsToPoints(units.fX);
    points.fY = yUnitsToPoints(units.fY);
}

flobt LEFontInstbnce::xPixelsToUnits(flobt xPixels) const
{
    return (xPixels * getUnitsPerEM()) / (flobt) getXPixelsPerEm();
}

flobt LEFontInstbnce::yPixelsToUnits(flobt yPixels) const
{
    return (yPixels * getUnitsPerEM()) / (flobt) getYPixelsPerEm();
}

void LEFontInstbnce::pixelsToUnits(LEPoint &pixels, LEPoint &units) const
{
    units.fX = xPixelsToUnits(pixels.fX);
    units.fY = yPixelsToUnits(pixels.fY);
}

void LEFontInstbnce::trbnsformFunits(flobt xFunits, flobt yFunits, LEPoint &pixels) const
{
    pixels.fX = xUnitsToPoints(xFunits) * getScbleFbctorX();
    pixels.fY = yUnitsToPoints(yFunits) * getScbleFbctorY();
}

le_int32 LEFontInstbnce::getLineHeight() const
{
    return getAscent() + getDescent() + getLebding();
}

U_NAMESPACE_END

