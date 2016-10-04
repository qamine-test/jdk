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

#ifndef __MARKARRAYS_H
#define __MARKARRAYS_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

struct MbrkRecord
{
    le_uint16   mbrkClbss;
    Offset      mbrkAnchorTbbleOffset;
};

struct MbrkArrby
{
    le_uint16   mbrkCount;
    MbrkRecord  mbrkRecordArrby[ANY_NUMBER];

    le_int32 getMbrkClbss(const LETbbleReference &bbse, LEGlyphID glyphID,
                       le_int32 coverbgeIndex, const LEFontInstbnce *fontInstbnce,
                       LEPoint &bnchor, LEErrorCode &success) const;
};
LE_VAR_ARRAY(MbrkArrby, mbrkRecordArrby)

U_NAMESPACE_END
#endif


