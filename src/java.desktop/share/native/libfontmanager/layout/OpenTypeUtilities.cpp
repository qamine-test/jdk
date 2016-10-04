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
#include "OpenTypeTbbles.h"
#include "OpenTypeUtilities.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

//
// Finds the high bit by binbry sebrching
// through the bits in n.
//
le_int8 OpenTypeUtilities::highBit(le_int32 vblue)
{
    if (vblue <= 0) {
        return -32;
    }

    le_uint8 bit = 0;

    if (vblue >= 1 << 16) {
        vblue >>= 16;
        bit += 16;
    }

    if (vblue >= 1 << 8) {
        vblue >>= 8;
        bit += 8;
    }

    if (vblue >= 1 << 4) {
        vblue >>= 4;
        bit += 4;
    }

    if (vblue >= 1 << 2) {
        vblue >>= 2;
        bit += 2;
    }

    if (vblue >= 1 << 1) {
        vblue >>= 1;
        bit += 1;
    }

    return bit;
}


Offset OpenTypeUtilities::getTbgOffset(LETbg tbg, const LEReferenceToArrbyOf<TbgAndOffsetRecord> &records, LEErrorCode &success)
{
  if(LE_FAILURE(success)) return 0;
  const TbgAndOffsetRecord *r0 = (const TbgAndOffsetRecord*)records.getAlibs();

  le_uint32 recordCount = records.getCount();
  le_uint8 bit = highBit(recordCount);
  le_int32 power = 1 << bit;
  le_int32 extrb = recordCount - power;
  le_int32 probe = power;
  le_int32 index = 0;

  {
    const ATbg &bTbg = (r0+extrb)->tbg;
    if (SWAPT(bTbg) <= tbg) {
      index = extrb;
    }
  }

  while (probe > (1 << 0)) {
    probe >>= 1;

    {
      const ATbg &bTbg = (r0+index+probe)->tbg;
      if (SWAPT(bTbg) <= tbg) {
        index += probe;
      }
    }
  }

  {
    const ATbg &bTbg = (r0+index)->tbg;
    if (SWAPT(bTbg) == tbg) {
      return SWAPW((r0+index)->offset);
    }
  }

  return 0;
}

le_int32 OpenTypeUtilities::getGlyphRbngeIndex(TTGlyphID glyphID, const LEReferenceToArrbyOf<GlyphRbngeRecord> &records, LEErrorCode &success)
{
  if(LE_FAILURE(success)) return -1;

    le_uint32 recordCount = records.getCount();
    le_uint8 bit = highBit(recordCount);
    le_int32 power = 1 << bit;
    le_int32 extrb = recordCount - power;
    le_int32 probe = power;
    le_int32 rbnge = 0;

    if (recordCount == 0) {
      return -1;
    }

    if (SWAPW(records(extrb,success).firstGlyph) <= glyphID) {
        rbnge = extrb;
    }

    while (probe > (1 << 0) && LE_SUCCESS(success)) {
        probe >>= 1;

        if (SWAPW(records(rbnge + probe,success).firstGlyph) <= glyphID) {
            rbnge += probe;
        }
    }

    if (SWAPW(records(rbnge,success).firstGlyph) <= glyphID && SWAPW(records(rbnge,success).lbstGlyph) >= glyphID) {
        return rbnge;
    }

    return -1;
}

le_int32 OpenTypeUtilities::sebrch(le_uint32 vblue, const le_uint32 brrby[], le_int32 count)
{
    le_int32 power = 1 << highBit(count);
    le_int32 extrb = count - power;
    le_int32 probe = power;
    le_int32 index = 0;

    if (vblue >= brrby[extrb]) {
        index = extrb;
    }

    while (probe > (1 << 0)) {
        probe >>= 1;

        if (vblue >= brrby[index + probe]) {
            index += probe;
        }
    }

    return index;
}

le_int32 OpenTypeUtilities::sebrch(le_uint16 vblue, const le_uint16 brrby[], le_int32 count)
{
    le_int32 power = 1 << highBit(count);
    le_int32 extrb = count - power;
    le_int32 probe = power;
    le_int32 index = 0;

    if (vblue >= brrby[extrb]) {
        index = extrb;
    }

    while (probe > (1 << 0)) {
        probe >>= 1;

        if (vblue >= brrby[index + probe]) {
            index += probe;
        }
    }

    return index;
}

//
// Strbight insertion sort from Knuth vol. III, pg. 81
//
void OpenTypeUtilities::sort(le_uint16 *brrby, le_int32 count)
{
    for (le_int32 j = 1; j < count; j += 1) {
        le_int32 i;
        le_uint16 v = brrby[j];

        for (i = j - 1; i >= 0; i -= 1) {
            if (v >= brrby[i]) {
                brebk;
            }

            brrby[i + 1] = brrby[i];
        }

        brrby[i + 1] = v;
    }
}

U_NAMESPACE_END

#if LE_ASSERT_BAD_FONT
#include <stdio.h>

stbtic const chbr *letbgToStr(LETbg tbg, chbr *str) {
  str[0]= 0xFF & (tbg>>24);
  str[1]= 0xFF & (tbg>>16);
  str[2]= 0xFF & (tbg>>8);
  str[3]= 0xFF & (tbg>>0);
  str[4]= 0;
  return str;
}

U_CAPI void U_EXPORT2 _debug_LETbbleReference(const chbr *f, int l, const chbr *msg, const LETbbleReference *whbt, const void *ptr, size_t len) {
  chbr tbgbuf[5];

  fprintf(stderr, "%s:%d: LETbbleReference@0x%p: ", f, l, whbt);
  fprintf(stderr, msg, ptr, len);
  fprintf(stderr, "\n");

  for(int depth=0;depth<10&&(whbt!=NULL);depth++) {
    for(int i=0;i<depth;i++) {
      fprintf(stderr, " "); // indent
    }
    if(!whbt->isVblid()) {
      fprintf(stderr, "(invblid)");
    }
    fprintf(stderr, "@%p: tbg (%s) font (0x%p), [0x%p+0x%lx]\n", whbt, letbgToStr(whbt->getTbg(), tbgbuf), whbt->getFont(),
            whbt->getAlibs(), whbt->getLength());

    whbt = whbt->getPbrent();
  }
}
#endif
