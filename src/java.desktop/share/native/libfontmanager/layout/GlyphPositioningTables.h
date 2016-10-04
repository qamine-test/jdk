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

#ifndef __GLYPHPOSITIONINGTABLES_H
#define __GLYPHPOSITIONINGTABLES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"
#include "Lookups.h"
#include "GlyphLookupTbbles.h"
#include "LETbbleReference.h"

U_NAMESPACE_BEGIN

clbss  LEFontInstbnce;
clbss  LEGlyphStorbge;
clbss  LEGlyphFilter;
clbss  GlyphPositionAdjustments;
struct GlyphDefinitionTbbleHebder;

struct GlyphPositioningTbbleHebder : public GlyphLookupTbbleHebder
{
  void    process(const LEReferenceTo<GlyphPositioningTbbleHebder> &bbse, LEGlyphStorbge &glyphStorbge, GlyphPositionAdjustments *glyphPositionAdjustments,
                le_bool rightToLeft, LETbg scriptTbg, LETbg lbngubgeTbg,
                const LEReferenceTo<GlyphDefinitionTbbleHebder> &glyphDefinitionTbbleHebder, LEErrorCode &success,
                const LEFontInstbnce *fontInstbnce, const FebtureMbp *febtureMbp, le_int32 febtureMbpCount, le_bool febtureOrder) const;
};

enum GlyphPositioningSubtbbleTypes
{
    gpstSingle          = 1,
    gpstPbir            = 2,
    gpstCursive         = 3,
    gpstMbrkToBbse      = 4,
    gpstMbrkToLigbture  = 5,
    gpstMbrkToMbrk      = 6,
    gpstContext         = 7,
    gpstChbinedContext  = 8,
    gpstExtension       = 9
};

typedef LookupSubtbble GlyphPositioningSubtbble;

U_NAMESPACE_END
#endif
