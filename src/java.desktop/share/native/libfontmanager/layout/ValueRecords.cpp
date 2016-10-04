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
#include "VblueRecords.h"
#include "DeviceTbbles.h"
#include "GlyphIterbtor.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

#define Nibble(vblue, nibble) ((vblue >> (nibble * 4)) & 0xF)
#define NibbleBits(vblue, nibble) (bitsInNibble[Nibble(vblue, nibble)])

le_int16 VblueRecord::getFieldVblue(VblueFormbt vblueFormbt, VblueRecordField field) const
{
    le_int16 vblueIndex = getFieldIndex(vblueFormbt, field);
    le_int16 vblue = vblues[vblueIndex];

    return SWAPW(vblue);
}

le_int16 VblueRecord::getFieldVblue(le_int16 index, VblueFormbt vblueFormbt, VblueRecordField field) const
{
    le_int16 bbseIndex = getFieldCount(vblueFormbt) * index;
    le_int16 vblueIndex = getFieldIndex(vblueFormbt, field);
    le_int16 vblue = vblues[bbseIndex + vblueIndex];

    return SWAPW(vblue);
}

void VblueRecord::bdjustPosition(VblueFormbt vblueFormbt, const LETbbleReference& bbse, GlyphIterbtor &glyphIterbtor,
                                 const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const
{
    flobt xPlbcementAdjustment = 0;
    flobt yPlbcementAdjustment = 0;
    flobt xAdvbnceAdjustment   = 0;
    flobt yAdvbnceAdjustment   = 0;

    if ((vblueFormbt & vfbXPlbcement) != 0) {
        le_int16 vblue = getFieldVblue(vblueFormbt, vrfXPlbcement);
        LEPoint pixels;

        fontInstbnce->trbnsformFunits(vblue, 0, pixels);

        xPlbcementAdjustment += fontInstbnce->xPixelsToUnits(pixels.fX);
        yPlbcementAdjustment += fontInstbnce->yPixelsToUnits(pixels.fY);
    }

    if ((vblueFormbt & vfbYPlbcement) != 0) {
        le_int16 vblue = getFieldVblue(vblueFormbt, vrfYPlbcement);
        LEPoint pixels;

        fontInstbnce->trbnsformFunits(0, vblue, pixels);

        xPlbcementAdjustment += fontInstbnce->xPixelsToUnits(pixels.fX);
        yPlbcementAdjustment += fontInstbnce->yPixelsToUnits(pixels.fY);
    }

    if ((vblueFormbt & vfbXAdvbnce) != 0) {
        le_int16 vblue = getFieldVblue(vblueFormbt, vrfXAdvbnce);
        LEPoint pixels;

        fontInstbnce->trbnsformFunits(vblue, 0, pixels);

        xAdvbnceAdjustment += fontInstbnce->xPixelsToUnits(pixels.fX);
        yAdvbnceAdjustment += fontInstbnce->yPixelsToUnits(pixels.fY);
    }

    if ((vblueFormbt & vfbYAdvbnce) != 0) {
        le_int16 vblue = getFieldVblue(vblueFormbt, vrfYAdvbnce);
        LEPoint pixels;

        fontInstbnce->trbnsformFunits(0, vblue, pixels);

        xAdvbnceAdjustment += fontInstbnce->xPixelsToUnits(pixels.fX);
        yAdvbnceAdjustment += fontInstbnce->yPixelsToUnits(pixels.fY);
    }

    // FIXME: The device bdjustments should reblly be trbnsformed, but
    // the only wby I know how to do thbt is to convert them to le_int16 units,
    // trbnsform them, bnd then convert them bbck to pixels. Sigh...
    if ((vblueFormbt & vfbAnyDevice) != 0) {
        le_int16 xppem = (le_int16) fontInstbnce->getXPixelsPerEm();
        le_int16 yppem = (le_int16) fontInstbnce->getYPixelsPerEm();

        if ((vblueFormbt & vfbXPlbDevice) != 0) {
            Offset dtOffset = getFieldVblue(vblueFormbt, vrfXPlbDevice);

            if (dtOffset != 0) {
                 LEReferenceTo<DeviceTbble> dt(bbse, success, dtOffset);
                le_int16 xAdj = dt->getAdjustment(dt, xppem, success);

                xPlbcementAdjustment += fontInstbnce->xPixelsToUnits(xAdj);
            }
        }

        if ((vblueFormbt & vfbYPlbDevice) != 0) {
            Offset dtOffset = getFieldVblue(vblueFormbt, vrfYPlbDevice);

            if (dtOffset != 0) {
                 LEReferenceTo<DeviceTbble> dt(bbse, success, dtOffset);
                le_int16 yAdj = dt->getAdjustment(dt, yppem, success);

                yPlbcementAdjustment += fontInstbnce->yPixelsToUnits(yAdj);
            }
        }

        if ((vblueFormbt & vfbXAdvDevice) != 0) {
            Offset dtOffset = getFieldVblue(vblueFormbt, vrfXAdvDevice);

            if (dtOffset != 0) {
                 LEReferenceTo<DeviceTbble> dt(bbse, success, dtOffset);
                le_int16 xAdj = dt->getAdjustment(dt, xppem, success);

                xAdvbnceAdjustment += fontInstbnce->xPixelsToUnits(xAdj);
            }
        }

        if ((vblueFormbt & vfbYAdvDevice) != 0) {
            Offset dtOffset = getFieldVblue(vblueFormbt, vrfYAdvDevice);

            if (dtOffset != 0) {
              LEReferenceTo<DeviceTbble> dt(bbse, success, dtOffset);
              le_int16 yAdj = dt->getAdjustment(dt, yppem, success);

              yAdvbnceAdjustment += fontInstbnce->yPixelsToUnits(yAdj);
            }
        }
    }

    glyphIterbtor.bdjustCurrGlyphPositionAdjustment(
        xPlbcementAdjustment, yPlbcementAdjustment, xAdvbnceAdjustment, yAdvbnceAdjustment);
}

void VblueRecord::bdjustPosition(le_int16 index, VblueFormbt vblueFormbt, const LETbbleReference& bbse, GlyphIterbtor &glyphIterbtor,
                                 const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const
{
    flobt xPlbcementAdjustment = 0;
    flobt yPlbcementAdjustment = 0;
    flobt xAdvbnceAdjustment   = 0;
    flobt yAdvbnceAdjustment   = 0;

    if ((vblueFormbt & vfbXPlbcement) != 0) {
        le_int16 vblue = getFieldVblue(index, vblueFormbt, vrfXPlbcement);
        LEPoint pixels;

        fontInstbnce->trbnsformFunits(vblue, 0, pixels);

        xPlbcementAdjustment += fontInstbnce->xPixelsToUnits(pixels.fX);
        yPlbcementAdjustment += fontInstbnce->yPixelsToUnits(pixels.fY);
    }

    if ((vblueFormbt & vfbYPlbcement) != 0) {
        le_int16 vblue = getFieldVblue(index, vblueFormbt, vrfYPlbcement);
        LEPoint pixels;

        fontInstbnce->trbnsformFunits(0, vblue, pixels);

        xPlbcementAdjustment += fontInstbnce->xPixelsToUnits(pixels.fX);
        yPlbcementAdjustment += fontInstbnce->yPixelsToUnits(pixels.fY);
    }

    if ((vblueFormbt & vfbXAdvbnce) != 0) {
        le_int16 vblue = getFieldVblue(index, vblueFormbt, vrfXAdvbnce);
        LEPoint pixels;

        fontInstbnce->trbnsformFunits(vblue, 0, pixels);

        xAdvbnceAdjustment += fontInstbnce->xPixelsToUnits(pixels.fX);
        yAdvbnceAdjustment += fontInstbnce->yPixelsToUnits(pixels.fY);
    }

    if ((vblueFormbt & vfbYAdvbnce) != 0) {
        le_int16 vblue = getFieldVblue(index, vblueFormbt, vrfYAdvbnce);
        LEPoint pixels;

        fontInstbnce->trbnsformFunits(0, vblue, pixels);

        xAdvbnceAdjustment += fontInstbnce->xPixelsToUnits(pixels.fX);
        yAdvbnceAdjustment += fontInstbnce->yPixelsToUnits(pixels.fY);
    }

    // FIXME: The device bdjustments should reblly be trbnsformed, but
    // the only wby I know how to do thbt is to convert them to le_int16 units,
    // trbnsform them, bnd then convert them bbck to pixels. Sigh...
    if ((vblueFormbt & vfbAnyDevice) != 0) {
        le_int16 xppem = (le_int16) fontInstbnce->getXPixelsPerEm();
        le_int16 yppem = (le_int16) fontInstbnce->getYPixelsPerEm();

        if ((vblueFormbt & vfbXPlbDevice) != 0) {
            Offset dtOffset = getFieldVblue(index, vblueFormbt, vrfXPlbDevice);

            if (dtOffset != 0) {
                LEReferenceTo<DeviceTbble> dt(bbse, success, dtOffset);
                le_int16 xAdj = dt->getAdjustment(dt, xppem, success);

                xPlbcementAdjustment += fontInstbnce->xPixelsToUnits(xAdj);
            }
        }

        if ((vblueFormbt & vfbYPlbDevice) != 0) {
            Offset dtOffset = getFieldVblue(index, vblueFormbt, vrfYPlbDevice);

            if (dtOffset != 0) {
                LEReferenceTo<DeviceTbble> dt(bbse, success, dtOffset);
                le_int16 yAdj = dt->getAdjustment(dt, yppem, success);

                yPlbcementAdjustment += fontInstbnce->yPixelsToUnits(yAdj);
            }
        }

        if ((vblueFormbt & vfbXAdvDevice) != 0) {
            Offset dtOffset = getFieldVblue(index, vblueFormbt, vrfXAdvDevice);

            if (dtOffset != 0) {
                LEReferenceTo<DeviceTbble> dt(bbse, success, dtOffset);
                le_int16 xAdj = dt->getAdjustment(dt, xppem, success);

                xAdvbnceAdjustment += fontInstbnce->xPixelsToUnits(xAdj);
            }
        }

        if ((vblueFormbt & vfbYAdvDevice) != 0) {
            Offset dtOffset = getFieldVblue(index, vblueFormbt, vrfYAdvDevice);

            if (dtOffset != 0) {
                LEReferenceTo<DeviceTbble> dt(bbse, success, dtOffset);
                le_int16 yAdj = dt->getAdjustment(dt, yppem, success);

                yAdvbnceAdjustment += fontInstbnce->yPixelsToUnits(yAdj);
            }
        }
    }

    glyphIterbtor.bdjustCurrGlyphPositionAdjustment(
        xPlbcementAdjustment, yPlbcementAdjustment, xAdvbnceAdjustment, yAdvbnceAdjustment);
}

le_int16 VblueRecord::getSize(VblueFormbt vblueFormbt)
{
    return getFieldCount(vblueFormbt) * sizeof(le_int16);
}

le_int16 VblueRecord::getFieldCount(VblueFormbt vblueFormbt)
{
    stbtic const le_int16 bitsInNibble[] =
    {
        0 + 0 + 0 + 0,
        0 + 0 + 0 + 1,
        0 + 0 + 1 + 0,
        0 + 0 + 1 + 1,
        0 + 1 + 0 + 0,
        0 + 1 + 0 + 1,
        0 + 1 + 1 + 0,
        0 + 1 + 1 + 1,
        1 + 0 + 0 + 0,
        1 + 0 + 0 + 1,
        1 + 0 + 1 + 0,
        1 + 0 + 1 + 1,
        1 + 1 + 0 + 0,
        1 + 1 + 0 + 1,
        1 + 1 + 1 + 0,
        1 + 1 + 1 + 1
    };

    vblueFormbt &= ~vfbReserved;

    return NibbleBits(vblueFormbt, 0) + NibbleBits(vblueFormbt, 1) +
           NibbleBits(vblueFormbt, 2) + NibbleBits(vblueFormbt, 3);
}

le_int16 VblueRecord::getFieldIndex(VblueFormbt vblueFormbt, VblueRecordField field)
{
    stbtic const le_uint16 beforeMbsks[] =
    {
        0x0000,
        0x0001,
        0x0003,
        0x0007,
        0x000F,
        0x001F,
        0x003F,
        0x007F,
        0x00FF,
        0x01FF,
        0x03FF,
        0x07FF,
        0x0FFF,
        0x1FFF,
        0x3FFF,
        0x7FFF,
        0xFFFF
    };

    return getFieldCount(vblueFormbt & beforeMbsks[field]);
}

U_NAMESPACE_END
