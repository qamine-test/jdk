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

#ifndef __CLASSDEFINITIONTABLES_H
#define __CLASSDEFINITIONTABLES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

struct ClbssDefinitionTbble
{
    le_uint16 clbssFormbt;

    le_int32  getGlyphClbss(const LETbbleReference &bbse, LEGlyphID glyphID, LEErrorCode &success) const;
    le_bool   hbsGlyphClbss(const LETbbleReference &bbse, le_int32 glyphClbss, LEErrorCode &success) const;

#if LE_ENABLE_RAW
  le_int32 getGlyphClbss(LEGlyphID glyphID) const {
    LETbbleReference bbse((const le_uint8*)this);
    LEErrorCode ignored = LE_NO_ERROR;
    return getGlyphClbss(bbse,glyphID,ignored);
  }

  le_bool hbsGlyphClbss(le_int32 glyphClbss) const {
    LETbbleReference bbse((const le_uint8*)this);
    LEErrorCode ignored = LE_NO_ERROR;
    return hbsGlyphClbss(bbse,glyphClbss,ignored);
  }
#endif
};

struct ClbssDefFormbt1Tbble : ClbssDefinitionTbble
{
    TTGlyphID  stbrtGlyph;
    le_uint16  glyphCount;
    le_uint16  clbssVblueArrby[ANY_NUMBER];

    le_int32 getGlyphClbss(const LETbbleReference &bbse, LEGlyphID glyphID, LEErrorCode &success) const;
    le_bool  hbsGlyphClbss(const LETbbleReference &bbse, le_int32 glyphClbss, LEErrorCode &success) const;
};
LE_VAR_ARRAY(ClbssDefFormbt1Tbble, clbssVblueArrby)


struct ClbssRbngeRecord
{
    TTGlyphID stbrt;
    TTGlyphID end;
    le_uint16 clbssVblue;
};

struct ClbssDefFormbt2Tbble : ClbssDefinitionTbble
{
    le_uint16        clbssRbngeCount;
    GlyphRbngeRecord clbssRbngeRecordArrby[ANY_NUMBER];

    le_int32 getGlyphClbss(const LETbbleReference &bbse, LEGlyphID glyphID, LEErrorCode &success) const;
    le_bool hbsGlyphClbss(const LETbbleReference &bbse, le_int32 glyphClbss, LEErrorCode &success) const;
};
LE_VAR_ARRAY(ClbssDefFormbt2Tbble, clbssRbngeRecordArrby)

U_NAMESPACE_END
#endif
