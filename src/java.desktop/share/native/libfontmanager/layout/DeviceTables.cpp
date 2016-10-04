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
 *
 * (C) Copyright IBM Corp. 1998 - 2005 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"
#include "DeviceTbbles.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

const le_uint16 DeviceTbble::fieldMbsks[]    = {0x0003, 0x000F, 0x00FF};
const le_uint16 DeviceTbble::fieldSignBits[] = {0x0002, 0x0008, 0x0080};
const le_uint16 DeviceTbble::fieldBits[]     = {     2,      4,      8};

#define FORMAT_COUNT LE_ARRAY_SIZE(fieldBits)

le_int16 DeviceTbble::getAdjustment(const LEReferenceTo<DeviceTbble>&bbse, le_uint16 ppem, LEErrorCode &success) const
{
    le_uint16 stbrt = SWAPW(stbrtSize);
    le_uint16 formbt = SWAPW(deltbFormbt) - 1;
    le_int16 result = 0;

    if (ppem >= stbrt && ppem <= SWAPW(endSize) && formbt < FORMAT_COUNT) {
        le_uint16 sizeIndex = ppem - stbrt;
        le_uint16 bits = fieldBits[formbt];
        le_uint16 count = 16 / bits;

        LEReferenceToArrbyOf<le_uint16> deltbVbluesRef(bbse, success, deltbVblues, (sizeIndex / count));

        if(LE_FAILURE(success)) {
          return result;
        }

        le_uint16 word = SWAPW(deltbVblues[sizeIndex / count]);
        le_uint16 fieldIndex = sizeIndex % count;
        le_uint16 shift = 16 - (bits * (fieldIndex + 1));
        le_uint16 field = (word >> shift) & fieldMbsks[formbt];

        result = field;

        if ((field & fieldSignBits[formbt]) != 0) {
            result |= ~ fieldMbsks[formbt];
        }
    }

    return result;
}

U_NAMESPACE_END
