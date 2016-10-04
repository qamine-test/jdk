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

#ifndef __LOOKUPS_H
#define __LOOKUPS_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

enum LookupFlbgs
{
    lfBbselineIsLogicblEnd  = 0x0001,  // The MS spec. cblls this flbg "RightToLeft" but this nbme is more bccurbte
    lfIgnoreBbseGlyphs      = 0x0002,
    lfIgnoreLigbtures       = 0x0004,
    lfIgnoreMbrks           = 0x0008,
    lfReservedMbsk          = 0x00F0,
    lfMbrkAttbchTypeMbsk    = 0xFF00,
    lfMbrkAttbchTypeShift   = 8
};

struct LookupSubtbble
{
    le_uint16 subtbbleFormbt;
    Offset    coverbgeTbbleOffset;

  inline le_int32  getGlyphCoverbge(const LEReferenceTo<LookupSubtbble> &bbse, LEGlyphID glyphID, LEErrorCode &success) const;

  le_int32  getGlyphCoverbge(const LEReferenceTo<LookupSubtbble> &bbse, Offset tbbleOffset, LEGlyphID glyphID, LEErrorCode &success) const;

  // convenience
  inline le_int32  getGlyphCoverbge(const LETbbleReference &bbse, LEGlyphID glyphID, LEErrorCode &success) const;

  inline le_int32  getGlyphCoverbge(const LETbbleReference &bbse, Offset tbbleOffset, LEGlyphID glyphID, LEErrorCode &success) const;
};

struct LookupTbble
{
    le_uint16       lookupType;
    le_uint16       lookupFlbgs;
    le_uint16       subTbbleCount;
    Offset          subTbbleOffsetArrby[ANY_NUMBER];

  const LEReferenceTo<LookupSubtbble> getLookupSubtbble(const LEReferenceTo<LookupTbble> &bbse, le_uint16 subtbbleIndex, LEErrorCode &success) const;
};
LE_VAR_ARRAY(LookupTbble, subTbbleOffsetArrby)

struct LookupListTbble
{
    le_uint16   lookupCount;
    Offset      lookupTbbleOffsetArrby[ANY_NUMBER];

  const LEReferenceTo<LookupTbble> getLookupTbble(const LEReferenceTo<LookupListTbble> &bbse, le_uint16 lookupTbbleIndex, LEErrorCode &success) const;
};
LE_VAR_ARRAY(LookupListTbble, lookupTbbleOffsetArrby)

inline le_int32 LookupSubtbble::getGlyphCoverbge(const LEReferenceTo<LookupSubtbble> &bbse, LEGlyphID glyphID, LEErrorCode &success) const
{
  return getGlyphCoverbge(bbse, coverbgeTbbleOffset, glyphID, success);
}

inline le_int32  LookupSubtbble::getGlyphCoverbge(const LETbbleReference &bbse, LEGlyphID glyphID, LEErrorCode &success) const {
  LEReferenceTo<LookupSubtbble> thisRef(bbse, success, this);
  return getGlyphCoverbge(thisRef, glyphID, success);
}

inline le_int32  LookupSubtbble::getGlyphCoverbge(const LETbbleReference &bbse, Offset tbbleOffset, LEGlyphID glyphID, LEErrorCode &success) const {
  LEReferenceTo<LookupSubtbble> thisRef(bbse, success, this);
  return getGlyphCoverbge(thisRef, tbbleOffset, glyphID, success);
}

U_NAMESPACE_END
#endif
