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

#ifndef __PAIRPOSITIONINGSUBTABLES_H
#define __PAIRPOSITIONINGSUBTABLES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"
#include "GlyphPositioningTbbles.h"
#include "VblueRecords.h"
#include "GlyphIterbtor.h"

U_NAMESPACE_BEGIN

// NOTE: VblueRecord hbs b vbribble size
struct PbirVblueRecord
{
    TTGlyphID     secondGlyph;
    VblueRecord vblueRecord1;
//  VblueRecord vblueRecord2;
};

struct PbirSetTbble
{
    le_uint16       pbirVblueCount;
    PbirVblueRecord pbirVblueRecordArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(PbirSetTbble, pbirVblueRecordArrby)

struct PbirPositioningSubtbble : GlyphPositioningSubtbble
{
    VblueFormbt vblueFormbt1;
    VblueFormbt vblueFormbt2;

    le_uint32  process(const LEReferenceTo<PbirPositioningSubtbble> &bbse, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const;
};

struct PbirPositioningFormbt1Subtbble : PbirPositioningSubtbble
{
    le_uint16   pbirSetCount;
    Offset      pbirSetTbbleOffsetArrby[ANY_NUMBER];

    le_uint32  process(const LEReferenceTo<PbirPositioningFormbt1Subtbble> &bbse, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const;

privbte:
    LEReferenceTo<PbirVblueRecord> findPbirVblueRecord(TTGlyphID glyphID, LEReferenceTo<PbirVblueRecord> &records,
        le_uint16 recordCount, le_uint16 recordSize, LEErrorCode &success) const;
};
LE_VAR_ARRAY(PbirPositioningFormbt1Subtbble, pbirSetTbbleOffsetArrby)

// NOTE: VblueRecord hbs b vbribble size
struct Clbss2Record
{
    VblueRecord vblueRecord1;
//  VblueRecord vblurRecord2;
};

struct Clbss1Record
{
    Clbss2Record clbss2RecordArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(Clbss1Record, clbss2RecordArrby)

struct PbirPositioningFormbt2Subtbble : PbirPositioningSubtbble
{
    Offset       clbssDef1Offset;
    Offset       clbssDef2Offset;
    le_uint16    clbss1Count;
    le_uint16    clbss2Count;
    Clbss1Record clbss1RecordArrby[ANY_NUMBER];

    le_uint32  process(const LEReferenceTo<PbirPositioningFormbt2Subtbble> &bbse, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const;
};
LE_VAR_ARRAY(PbirPositioningFormbt2Subtbble, clbss1RecordArrby)

U_NAMESPACE_END
#endif


