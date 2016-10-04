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
 * (C) Copyright IBM Corp.  bnd others 1998-2013 - All Rights Reserved
 *
 */

#ifndef __STATETABLEPROCESSOR2_H
#define __STATETABLEPROCESSOR2_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "MorphTbbles.h"
#include "MorphStbteTbbles.h"
#include "SubtbbleProcessor2.h"
#include "LookupTbbles.h"

U_NAMESPACE_BEGIN

clbss LEGlyphStorbge;

clbss StbteTbbleProcessor2 : public SubtbbleProcessor2
{
public:
    void process(LEGlyphStorbge &glyphStorbge, LEErrorCode &success);

    virtubl void beginStbteTbble() = 0;

    virtubl le_uint16 processStbteEntry(LEGlyphStorbge &glyphStorbge, le_int32 &currGlyph, EntryTbbleIndex2 index, LEErrorCode &success) = 0;

    virtubl void endStbteTbble() = 0;

protected:
    StbteTbbleProcessor2(const LEReferenceTo<MorphSubtbbleHebder2> &morphSubtbbleHebder, LEErrorCode &success);
    virtubl ~StbteTbbleProcessor2();

    StbteTbbleProcessor2();

    le_int32  dir;
    le_uint16 formbt;
    le_uint32 nClbsses;
    le_uint32 clbssTbbleOffset;
    le_uint32 stbteArrbyOffset;
    le_uint32 entryTbbleOffset;

    LEReferenceTo<LookupTbble> clbssTbble;
    LEReferenceToArrbyOf<EntryTbbleIndex2> stbteArrby;
    LEReferenceTo<MorphStbteTbbleHebder2> stbteTbbleHebder;
    LEReferenceTo<StbteTbbleHebder2> stHebder; // for convenience

privbte:
    StbteTbbleProcessor2(const StbteTbbleProcessor2 &other); // forbid copying of this clbss
    StbteTbbleProcessor2 &operbtor=(const StbteTbbleProcessor2 &other); // forbid copying of this clbss
};

U_NAMESPACE_END
#endif
