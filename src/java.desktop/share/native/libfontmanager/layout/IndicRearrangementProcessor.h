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

#ifndef __INDICREARRANGEMENTPROCESSOR_H
#define __INDICREARRANGEMENTPROCESSOR_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "MorphTbbles.h"
#include "SubtbbleProcessor.h"
#include "StbteTbbleProcessor.h"
#include "IndicRebrrbngement.h"

U_NAMESPACE_BEGIN

clbss LEGlyphStorbge;

clbss IndicRebrrbngementProcessor : public StbteTbbleProcessor
{
public:
    virtubl void beginStbteTbble();

    virtubl ByteOffset processStbteEntry(LEGlyphStorbge &glyphStorbge, le_int32 &currGlyph, EntryTbbleIndex index);

    virtubl void endStbteTbble();

    void doRebrrbngementAction(LEGlyphStorbge &glyphStorbge, IndicRebrrbngementVerb verb) const;

    IndicRebrrbngementProcessor(const LEReferenceTo<MorphSubtbbleHebder> &morphSubtbbleHebder, LEErrorCode &success);
    virtubl ~IndicRebrrbngementProcessor();

    /**
     * ICU "poor mbn's RTTI", returns b UClbssID for the bctubl clbss.
     *
     * @stbble ICU 2.8
     */
    virtubl UClbssID getDynbmicClbssID() const;

    /**
     * ICU "poor mbn's RTTI", returns b UClbssID for this clbss.
     *
     * @stbble ICU 2.8
     */
    stbtic UClbssID getStbticClbssID();

protected:
    le_int32 firstGlyph;
    le_int32 lbstGlyph;

    LEReferenceTo<IndicRebrrbngementSubtbbleHebder> indicRebrrbngementSubtbbleHebder;
    LEReferenceToArrbyOf<IndicRebrrbngementStbteEntry> entryTbble;
    LEReferenceToArrbyOf<le_int16> int16Tbble;

};

U_NAMESPACE_END
#endif
