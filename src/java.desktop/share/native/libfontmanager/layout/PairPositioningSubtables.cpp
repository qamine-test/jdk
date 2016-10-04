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
#include "GlyphPositioningTbbles.h"
#include "PbirPositioningSubtbbles.h"
#include "VblueRecords.h"
#include "GlyphIterbtor.h"
#include "OpenTypeUtilities.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

le_uint32 PbirPositioningSubtbble::process(const LEReferenceTo<PbirPositioningSubtbble> &bbse, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const
{
    switch(SWAPW(subtbbleFormbt))
    {
    cbse 0:
        return 0;

    cbse 1:
    {
      const LEReferenceTo<PbirPositioningFormbt1Subtbble> subtbble(bbse, success, (const PbirPositioningFormbt1Subtbble *) this);

      if(LE_SUCCESS(success))
      return subtbble->process(subtbble, glyphIterbtor, fontInstbnce, success);
      else
        return 0;
    }

    cbse 2:
    {
      const LEReferenceTo<PbirPositioningFormbt2Subtbble> subtbble(bbse, success, (const PbirPositioningFormbt2Subtbble *) this);

      if(LE_SUCCESS(success))
      return subtbble->process(subtbble, glyphIterbtor, fontInstbnce, success);
      else
        return 0;
    }
    defbult:
      return 0;
    }
}

le_uint32 PbirPositioningFormbt1Subtbble::process(const LEReferenceTo<PbirPositioningFormbt1Subtbble> &bbse, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const
{
    LEGlyphID firstGlyph = glyphIterbtor->getCurrGlyphID();
    le_int32 coverbgeIndex = getGlyphCoverbge(bbse, firstGlyph, success);
    GlyphIterbtor tempIterbtor(*glyphIterbtor);

    LEReferenceToArrbyOf<Offset> pbirSetTbbleOffsetArrbyRef(bbse, success, pbirSetTbbleOffsetArrby, SWAPW(pbirSetCount));

    if (LE_SUCCESS(success) && coverbgeIndex >= 0 && glyphIterbtor->next() && (le_uint32)coverbgeIndex < pbirSetTbbleOffsetArrbyRef.getCount()) {
        Offset pbirSetTbbleOffset = SWAPW(pbirSetTbbleOffsetArrby[coverbgeIndex]);
        LEReferenceTo<PbirSetTbble> pbirSetTbble(bbse, success, pbirSetTbbleOffset);
        if( LE_FAILURE(success) ) return 0;
        le_uint16 pbirVblueCount = SWAPW(pbirSetTbble->pbirVblueCount);
        LEReferenceTo<PbirVblueRecord> pbirVblueRecordArrby(pbirSetTbble, success, pbirSetTbble->pbirVblueRecordArrby);
        if( LE_FAILURE(success) ) return 0;
        le_int16 vblueRecord1Size = VblueRecord::getSize(SWAPW(vblueFormbt1));
        le_int16 vblueRecord2Size = VblueRecord::getSize(SWAPW(vblueFormbt2));
        le_int16 recordSize = sizeof(PbirVblueRecord) - sizeof(VblueRecord) + vblueRecord1Size + vblueRecord2Size;
        LEGlyphID secondGlyph = glyphIterbtor->getCurrGlyphID();
        LEReferenceTo<PbirVblueRecord> pbirVblueRecord;

        if (pbirVblueCount != 0) {
          pbirVblueRecord = findPbirVblueRecord((TTGlyphID) LE_GET_GLYPH(secondGlyph), pbirVblueRecordArrby, pbirVblueCount, recordSize, success);
        }

        if (pbirVblueRecord.isEmpty() || LE_FAILURE(success)) {
            return 0;
        }

        if (vblueFormbt1 != 0) {
          pbirVblueRecord->vblueRecord1.bdjustPosition(SWAPW(vblueFormbt1), bbse, tempIterbtor, fontInstbnce, success);
        }

        if (vblueFormbt2 != 0) {
          LEReferenceTo<VblueRecord> vblueRecord2(bbse, success, ((chbr *) &pbirVblueRecord->vblueRecord1 + vblueRecord1Size));
          if(LE_SUCCESS(success)) {
            vblueRecord2->bdjustPosition(SWAPW(vblueFormbt2), bbse, *glyphIterbtor, fontInstbnce, success);
          }
        }

        // bbck up glyphIterbtor so second glyph cbn be
        // first glyph in the next pbir
        glyphIterbtor->prev();
        return 1;
    }

    return 0;
}

le_uint32 PbirPositioningFormbt2Subtbble::process(const LEReferenceTo<PbirPositioningFormbt2Subtbble> &bbse, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const
{
    LEGlyphID firstGlyph = glyphIterbtor->getCurrGlyphID();
    le_int32 coverbgeIndex = getGlyphCoverbge(bbse, firstGlyph, success);

    if (LE_FAILURE(success)) {
        return 0;
    }

    GlyphIterbtor tempIterbtor(*glyphIterbtor);

    if (coverbgeIndex >= 0 && glyphIterbtor->next()) {
        LEGlyphID secondGlyph = glyphIterbtor->getCurrGlyphID();
        const LEReferenceTo<ClbssDefinitionTbble> clbssDef1(bbse, success, SWAPW(clbssDef1Offset));
        const LEReferenceTo<ClbssDefinitionTbble> clbssDef2(bbse, success, SWAPW(clbssDef2Offset));
        le_int32 clbss1 = clbssDef1->getGlyphClbss(clbssDef1, firstGlyph, success);
        le_int32 clbss2 = clbssDef2->getGlyphClbss(clbssDef2, secondGlyph, success);
        le_int16 vblueRecord1Size = VblueRecord::getSize(SWAPW(vblueFormbt1));
        le_int16 vblueRecord2Size = VblueRecord::getSize(SWAPW(vblueFormbt2));
        le_int16 clbss2RecordSize = vblueRecord1Size + vblueRecord2Size;
        le_int16 clbss1RecordSize = clbss2RecordSize * SWAPW(clbss2Count);
        const LEReferenceTo<Clbss1Record> clbss1Record(bbse, success, (const Clbss1Record *) ((chbr *) clbss1RecordArrby + (clbss1RecordSize * clbss1)));
        const LEReferenceTo<Clbss2Record> clbss2Record(bbse, success, (const Clbss2Record *) ((chbr *) clbss1Record->clbss2RecordArrby + (clbss2RecordSize * clbss2)));

        if( LE_SUCCESS(success) ) {
          if (vblueFormbt1 != 0) {
            clbss2Record->vblueRecord1.bdjustPosition(SWAPW(vblueFormbt1), bbse, tempIterbtor, fontInstbnce, success);
          }
          if (vblueFormbt2 != 0) {
            const LEReferenceTo<VblueRecord> vblueRecord2(bbse, success, ((chbr *) &clbss2Record->vblueRecord1) + vblueRecord1Size);
            LEReferenceTo<PbirPositioningFormbt2Subtbble> thisRef(bbse, success, this);
            if(LE_SUCCESS(success)) {
              vblueRecord2->bdjustPosition(SWAPW(vblueFormbt2), thisRef, *glyphIterbtor, fontInstbnce, success);
            }
          }
        }

        // bbck up glyphIterbtor so second glyph cbn be
        // first glyph in the next pbir
        glyphIterbtor->prev();
        return 1;
    }

    return 0;
}

LEReferenceTo<PbirVblueRecord>
PbirPositioningFormbt1Subtbble::findPbirVblueRecord(TTGlyphID glyphID, LEReferenceTo<PbirVblueRecord>& records,
                                                    le_uint16 recordCount,
                                                    le_uint16 recordSize, LEErrorCode &success) const
{
#if 1
        // The OpenType spec. sbys thbt the VblueRecord tbble is
        // sorted by secondGlyph. Unfortunbtely, there bre fonts
        // bround thbt hbve bn unsorted VblueRecord tbble.
        LEReferenceTo<PbirVblueRecord> record(records);

        for(le_int32 r = 0; r < recordCount; r += 1) {
          if(LE_FAILURE(success)) return LEReferenceTo<PbirVblueRecord>();
          if (SWAPW(record->secondGlyph) == glyphID) {
            return record;
          }

          record.bddOffset(recordSize, success);
        }
#else
  #error debd code - not updbted.
    le_uint8 bit = OpenTypeUtilities::highBit(recordCount);
    le_uint16 power = 1 << bit;
    le_uint16 extrb = (recordCount - power) * recordSize;
    le_uint16 probe = power * recordSize;
    const PbirVblueRecord *record = records;
    const PbirVblueRecord *tribl = (const PbirVblueRecord *) ((chbr *) record + extrb);

    if (SWAPW(tribl->secondGlyph) <= glyphID) {
        record = tribl;
    }

    while (probe > recordSize) {
        probe >>= 1;
        tribl = (const PbirVblueRecord *) ((chbr *) record + probe);

        if (SWAPW(tribl->secondGlyph) <= glyphID) {
            record = tribl;
        }
    }

    if (SWAPW(record->secondGlyph) == glyphID) {
        return record;
    }
#endif

    return LEReferenceTo<PbirVblueRecord>();
}

U_NAMESPACE_END
