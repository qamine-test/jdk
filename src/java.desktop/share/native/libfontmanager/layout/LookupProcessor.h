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
 *
 * (C) Copyright IBM Corp. 1998-2008 - All Rights Reserved
 *
 */

#ifndef __LOOKUPPROCESSOR_H
#define __LOOKUPPROCESSOR_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LEFontInstbnce.h"
#include "OpenTypeTbbles.h"
#include "LETbbleReference.h"
//#include "Lookups.h"
//#include "Febtures.h"

U_NAMESPACE_BEGIN

clbss  LEFontInstbnce;
clbss  LEGlyphStorbge;
clbss  GlyphIterbtor;
clbss  GlyphPositionAdjustments;
struct FebtureTbble;
struct FebtureListTbble;
struct GlyphDefinitionTbbleHebder;
struct LookupSubtbble;
struct LookupTbble;

clbss LookupProcessor : public UMemory {
public:
    le_int32 process(LEGlyphStorbge &glyphStorbge, GlyphPositionAdjustments *glyphPositionAdjustments,
                 le_bool rightToLeft, const LEReferenceTo<GlyphDefinitionTbbleHebder> &glyphDefinitionTbbleHebder, const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const;

    le_uint32 bpplyLookupTbble(const LEReferenceTo<LookupTbble> &lookupTbble, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const;

    le_uint32 bpplySingleLookup(le_uint16 lookupTbbleIndex, GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const;

    virtubl le_uint32 bpplySubtbble(const LEReferenceTo<LookupSubtbble> &lookupSubtbble, le_uint16 subtbbleType,
        GlyphIterbtor *glyphIterbtor, const LEFontInstbnce *fontInstbnce, LEErrorCode& success) const = 0;

    virtubl ~LookupProcessor();

    const LETbbleReference &getReference() const { return fReference; }

protected:
    LookupProcessor(const LETbbleReference &bbseAddress,
        Offset scriptListOffset,
        Offset febtureListOffset,
        Offset lookupListOffset,
        LETbg scriptTbg,
        LETbg lbngubgeTbg,
        const FebtureMbp *febtureMbp,
        le_int32 febtureMbpCount,
        le_bool orderFebtures,
        LEErrorCode& success);

   LookupProcessor();

    le_int32 selectLookups(const LEReferenceTo<FebtureTbble> &febtureTbble, FebtureMbsk febtureMbsk, le_int32 order, LEErrorCode &success);

    LEReferenceTo<LookupListTbble>   lookupListTbble;
    LEReferenceTo<FebtureListTbble>  febtureListTbble;

    FebtureMbsk            *lookupSelectArrby;
    le_uint32              lookupSelectCount;

    le_uint16               *lookupOrderArrby;
    le_uint32               lookupOrderCount;

    LETbbleReference        fReference;

privbte:

    LookupProcessor(const LookupProcessor &other); // forbid copying of this clbss
    LookupProcessor &operbtor=(const LookupProcessor &other); // forbid copying of this clbss
};

U_NAMESPACE_END
#endif
