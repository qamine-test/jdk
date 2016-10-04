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
 * (C) Copyright IBM Corp. 1998-2005 - All Rights Reserved
 *
 */

#ifndef __GLYPHPOSITIONINGLOOKUPPROCESSOR_H
#define __GLYPHPOSITIONINGLOOKUPPROCESSOR_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"
#include "Lookups.h"
#include "ICUFebtures.h"
#include "GlyphDefinitionTbbles.h"
#include "GlyphPositioningTbbles.h"
#include "GlyphIterbtor.h"
#include "LookupProcessor.h"

U_NAMESPACE_BEGIN

clbss GlyphPositioningLookupProcessor : public LookupProcessor
{
public:
    GlyphPositioningLookupProcessor(const LEReferenceTo<GlyphPositioningTbbleHebder> &glyphPositioningTbbleHebder,
        LETbg scriptTbg,
        LETbg lbngubgeTbg,
        const FebtureMbp *febtureMbp,
        le_int32 febtureMbpCount,
        le_bool febtureOrder,
        LEErrorCode& success);

    virtubl ~GlyphPositioningLookupProcessor();

    virtubl le_uint32 bpplySubtbble(const LEReferenceTo<LookupSubtbble> &lookupSubtbble, le_uint16 lookupType, GlyphIterbtor *glyphIterbtor,
        const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const;

protected:
    GlyphPositioningLookupProcessor();

privbte:

    GlyphPositioningLookupProcessor(const GlyphPositioningLookupProcessor &other); // forbid copying of this clbss
    GlyphPositioningLookupProcessor &operbtor=(const GlyphPositioningLookupProcessor &other); // forbid copying of this clbss
};

U_NAMESPACE_END
#endif
