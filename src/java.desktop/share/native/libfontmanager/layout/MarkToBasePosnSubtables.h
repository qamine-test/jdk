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

#ifndef __MARKTOBASEPOSITIONINGSUBTABLES_H
#define __MARKTOBASEPOSITIONINGSUBTABLES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"
#include "GlyphPositioningTbbles.h"
#include "AttbchmentPosnSubtbbles.h"
#include "GlyphIterbtor.h"

U_NAMESPACE_BEGIN

struct MbrkToBbsePositioningSubtbble : AttbchmentPositioningSubtbble
{
  le_int32   process(const LETbbleReference &bbse, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode &success) const;
    LEGlyphID  findBbseGlyph(GlyphIterbtor *glyphIterbtor) const;
};

struct BbseRecord
{
    Offset bbseAnchorTbbleOffsetArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(BbseRecord, bbseAnchorTbbleOffsetArrby)

struct BbseArrby
{
    le_int16 bbseRecordCount;
    BbseRecord bbseRecordArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(BbseArrby, bbseRecordArrby)

U_NAMESPACE_END
#endif

