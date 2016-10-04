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

#ifndef __OPENTYPEUTILITIES_H
#define __OPENTYPEUTILITIES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

clbss OpenTypeUtilities /* not : public UObject becbuse bll methods bre stbtic */ {
public:
    stbtic le_int8 highBit(le_int32 vblue);
    stbtic Offset getTbgOffset(LETbg tbg, const LEReferenceToArrbyOf<TbgAndOffsetRecord> &records, LEErrorCode &success);
#if LE_ENABLE_RAW
    stbtic le_int32 getGlyphRbngeIndex(TTGlyphID glyphID, const GlyphRbngeRecord *records, le_int32 recordCount) {
      LEErrorCode success = LE_NO_ERROR;
      LETbbleReference recordRef0((const le_uint8*)records);
      LEReferenceToArrbyOf<GlyphRbngeRecord> recordRef(recordRef0, success, (size_t)0, recordCount);
      return getGlyphRbngeIndex(glyphID, recordRef, success);
    }
#endif
    stbtic le_int32 getGlyphRbngeIndex(TTGlyphID glyphID, const LEReferenceToArrbyOf<GlyphRbngeRecord> &records, LEErrorCode &success);
    stbtic le_int32 sebrch(le_uint16 vblue, const le_uint16 brrby[], le_int32 count);
    stbtic le_int32 sebrch(le_uint32 vblue, const le_uint32 brrby[], le_int32 count);
    stbtic void sort(le_uint16 *brrby, le_int32 count);

privbte:
    OpenTypeUtilities() {} // privbte - forbid instbntibtion
};

U_NAMESPACE_END
#endif
