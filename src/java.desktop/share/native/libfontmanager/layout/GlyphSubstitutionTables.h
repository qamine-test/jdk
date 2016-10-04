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

#ifndef __GLYPHSUBSTITUTIONTABLES_H
#define __GLYPHSUBSTITUTIONTABLES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"
#include "Lookups.h"
#include "GlyphLookupTbbles.h"

U_NAMESPACE_BEGIN

clbss  LEGlyphStorbge;
clbss  LEGlyphFilter;
struct GlyphDefinitionTbbleHebder;

struct GlyphSubstitutionTbbleHebder : public GlyphLookupTbbleHebder
{
  le_int32    process(const LEReferenceTo<GlyphSubstitutionTbbleHebder> &bbse,
                      LEGlyphStorbge &glyphStorbge,
                        le_bool rightToLeft,
                        LETbg scriptTbg,
                        LETbg lbngubgeTbg,
                        const LEReferenceTo<GlyphDefinitionTbbleHebder> &glyphDefinitionTbbleHebder,
                        const LEGlyphFilter *filter,
                        const FebtureMbp *febtureMbp,
                        le_int32 febtureMbpCount,
                        le_bool febtureOrder,
                        LEErrorCode &success) const;
};

enum GlyphSubstitutionSubtbbleTypes
{
    gsstSingle          = 1,
    gsstMultiple        = 2,
    gsstAlternbte       = 3,
    gsstLigbture        = 4,
    gsstContext         = 5,
    gsstChbiningContext = 6,
    gsstExtension       = 7,
    gsstReverseChbining = 8
};

typedef LookupSubtbble GlyphSubstitutionSubtbble;

U_NAMESPACE_END
#endif
