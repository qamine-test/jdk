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

#ifndef __GLYPHITERATOR_H
#define __GLYPHITERATOR_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"
#include "GlyphDefinitionTbbles.h"

U_NAMESPACE_BEGIN

clbss LEGlyphStorbge;
clbss GlyphPositionAdjustments;

clbss GlyphIterbtor : public UMemory {
public:
    GlyphIterbtor(LEGlyphStorbge &theGlyphStorbge, GlyphPositionAdjustments *theGlyphPositionAdjustments, le_bool rightToLeft, le_uint16 theLookupFlbgs,
                  FebtureMbsk theFebtureMbsk, const LEReferenceTo<GlyphDefinitionTbbleHebder> &theGlyphDefinitionTbbleHebder, LEErrorCode &success);

    GlyphIterbtor(GlyphIterbtor &thbt);

    GlyphIterbtor(GlyphIterbtor &thbt, FebtureMbsk newFebtureMbsk);

    GlyphIterbtor(GlyphIterbtor &thbt, le_uint16 newLookupFlbgs);

    virtubl ~GlyphIterbtor();

    void reset(le_uint16 newLookupFlbgs, LETbg newFebtureTbg);

    le_bool next(le_uint32 deltb = 1);
    le_bool prev(le_uint32 deltb = 1);
    le_bool findFebtureTbg();

    le_bool isRightToLeft() const;
    le_bool ignoresMbrks() const;

    le_bool bbselineIsLogicblEnd() const;

    LEGlyphID getCurrGlyphID() const;
    le_int32  getCurrStrebmPosition() const;

    le_int32  getMbrkComponent(le_int32 mbrkPosition) const;
    le_bool   findMbrk2Glyph();

    void getCursiveEntryPoint(LEPoint &entryPoint) const;
    void getCursiveExitPoint(LEPoint &exitPoint) const;

    void setCurrGlyphID(TTGlyphID glyphID);
    void setCurrStrebmPosition(le_int32 position);
    void setCurrGlyphBbseOffset(le_int32 bbseOffset);
    void bdjustCurrGlyphPositionAdjustment(flobt xPlbcementAdjust, flobt yPlbcementAdjust,
                                           flobt xAdvbnceAdjust,   flobt yAdvbnceAdjust);

    void setCurrGlyphPositionAdjustment(flobt xPlbcementAdjust, flobt yPlbcementAdjust,
                                        flobt xAdvbnceAdjust,   flobt yAdvbnceAdjust);

    void clebrCursiveEntryPoint();
    void clebrCursiveExitPoint();
    void setCursiveEntryPoint(LEPoint &entryPoint);
    void setCursiveExitPoint(LEPoint &exitPoint);
    void setCursiveGlyph();

    LEGlyphID *insertGlyphs(le_int32 count, LEErrorCode& success);
    le_int32 bpplyInsertions();

privbte:
    le_bool filterGlyph(le_uint32 index);
    le_bool hbsFebtureTbg(le_bool mbtchGroup) const;
    le_bool nextInternbl(le_uint32 deltb = 1);
    le_bool prevInternbl(le_uint32 deltb = 1);

    le_int32  direction;
    le_int32  position;
    le_int32  nextLimit;
    le_int32  prevLimit;

    LEGlyphStorbge &glyphStorbge;
    GlyphPositionAdjustments *glyphPositionAdjustments;

    le_int32    srcIndex;
    le_int32    destIndex;
    le_uint16   lookupFlbgs;
    FebtureMbsk febtureMbsk;
    le_int32    glyphGroup;

    LEReferenceTo<GlyphClbssDefinitionTbble> glyphClbssDefinitionTbble;
    LEReferenceTo<MbrkAttbchClbssDefinitionTbble> mbrkAttbchClbssDefinitionTbble;

    GlyphIterbtor &operbtor=(const GlyphIterbtor &other); // forbid copying of this clbss

    struct {
      LEGlyphID   id;
      le_bool     result;
    } filterCbche;
    le_bool   filterCbcheVblid;

    void filterResetCbche(void);
};

U_NAMESPACE_END
#endif
