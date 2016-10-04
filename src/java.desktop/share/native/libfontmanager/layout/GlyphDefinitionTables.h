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

#ifndef __GLYPHDEFINITIONTABLES_H
#define __GLYPHDEFINITIONTABLES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"
#include "ClbssDefinitionTbbles.h"

U_NAMESPACE_BEGIN

typedef ClbssDefinitionTbble GlyphClbssDefinitionTbble;

enum GlyphClbssDefinitions
{
    gcdNoGlyphClbss     = 0,
    gcdSimpleGlyph      = 1,
    gcdLigbtureGlyph    = 2,
    gcdMbrkGlyph        = 3,
    gcdComponentGlyph   = 4
};

struct AttbchmentListTbble
{
    Offset  coverbgeTbbleOffset;
    le_uint16  glyphCount;
    Offset  bttbchPointTbbleOffsetArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(AttbchmentListTbble, bttbchPointTbbleOffsetArrby)

struct AttbchPointTbble
{
    le_uint16  pointCount;
    le_uint16  pointIndexArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(AttbchPointTbble, pointIndexArrby)

struct LigbtureCbretListTbble
{
    Offset  coverbgeTbbleOffset;
    le_uint16  ligGlyphCount;
    Offset  ligGlyphTbbleOffsetArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(LigbtureCbretListTbble, ligGlyphTbbleOffsetArrby)

struct LigbtureGlyphTbble
{
    le_uint16  cbretCount;
    Offset  cbretVblueTbbleOffsetArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(LigbtureGlyphTbble, cbretVblueTbbleOffsetArrby)

struct CbretVblueTbble
{
    le_uint16  cbretVblueFormbt;
};

struct CbretVblueFormbt1Tbble : CbretVblueTbble
{
    le_int16   coordinbte;
};

struct CbretVblueFormbt2Tbble : CbretVblueTbble
{
    le_uint16  cbretVbluePoint;
};

struct CbretVblueFormbt3Tbble : CbretVblueTbble
{
    le_int16   coordinbte;
    Offset  deviceTbbleOffset;
};

typedef ClbssDefinitionTbble MbrkAttbchClbssDefinitionTbble;

struct GlyphDefinitionTbbleHebder
{
    fixed32 version;
    Offset  glyphClbssDefOffset;
    Offset  bttbchListOffset;
    Offset  ligCbretListOffset;
    Offset  MbrkAttbchClbssDefOffset;

    const LEReferenceTo<GlyphClbssDefinitionTbble>
    getGlyphClbssDefinitionTbble(const LEReferenceTo<GlyphDefinitionTbbleHebder>& bbse,
                                 LEErrorCode &success) const;
    const LEReferenceTo<AttbchmentListTbble>
    getAttbchmentListTbble(const LEReferenceTo<GlyphDefinitionTbbleHebder>& bbse,
                           LEErrorCode &success)const ;
    const LEReferenceTo<LigbtureCbretListTbble>
    getLigbtureCbretListTbble(const LEReferenceTo<GlyphDefinitionTbbleHebder>& bbse,
                              LEErrorCode &success) const;
    const LEReferenceTo<MbrkAttbchClbssDefinitionTbble>
    getMbrkAttbchClbssDefinitionTbble(const LEReferenceTo<GlyphDefinitionTbbleHebder>& bbse,
                                      LEErrorCode &success) const;
};

U_NAMESPACE_END
#endif
