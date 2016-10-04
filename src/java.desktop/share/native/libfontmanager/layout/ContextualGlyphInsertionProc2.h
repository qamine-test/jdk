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
 * (C) Copyright IBM Corp.  bnd others 2013 - All Rights Reserved
 *
 */

#ifndef __CONTEXTUALGLYPHINSERTIONPROCESSOR2_H
#define __CONTEXTUALGLYPHINSERTIONPROCESSOR2_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "MorphTbbles.h"
#include "SubtbbleProcessor2.h"
#include "StbteTbbleProcessor2.h"
#include "ContextublGlyphInsertionProc2.h"
#include "ContextublGlyphInsertion.h"

U_NAMESPACE_BEGIN

clbss LEGlyphStorbge;

clbss ContextublGlyphInsertionProcessor2 : public StbteTbbleProcessor2
{
public:
    virtubl void beginStbteTbble();

    virtubl le_uint16 processStbteEntry(LEGlyphStorbge &glyphStorbge,
                                        le_int32 &currGlyph, EntryTbbleIndex2 index, LEErrorCode &success);

    virtubl void endStbteTbble();

    ContextublGlyphInsertionProcessor2(const LEReferenceTo<MorphSubtbbleHebder2> &morphSubtbbleHebder, LEErrorCode &success);
    virtubl ~ContextublGlyphInsertionProcessor2();

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

privbte:
    ContextublGlyphInsertionProcessor2();

    /**
     * Perform the bctubl insertion
     * @pbrbm btGlyph index of glyph to insert bt
     * @pbrbm index index into the insertionTbble (in/out)
     * @pbrbm count number of insertions
     * @pbrbm isKbshidbLike Kbshidb like (vs Split Vowel like). No effect currently.
     * @pbrbm isBefore if true, insert extrb glyphs before the mbrked glyph
     */
    void doInsertion(LEGlyphStorbge &glyphStorbge,
                              le_int16 btGlyph,
                              le_int16 &index,
                              le_int16 count,
                              le_bool isKbshidbLike,
                              le_bool isBefore,
                              LEErrorCode &success);


protected:
    le_int32 mbrkGlyph;
    LEReferenceToArrbyOf<le_uint16> insertionTbble;
    LEReferenceToArrbyOf<ContextublGlyphInsertionStbteEntry2> entryTbble;
    LEReferenceTo<ContextublGlyphInsertionHebder2> contextublGlyphHebder;
};

U_NAMESPACE_END
#endif
