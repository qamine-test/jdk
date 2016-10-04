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

#ifndef __LIGATURESUBSTITUTIONSUBTABLES_H
#define __LIGATURESUBSTITUTIONSUBTABLES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LEGlyphFilter.h"
#include "OpenTypeTbbles.h"
#include "GlyphSubstitutionTbbles.h"
#include "GlyphIterbtor.h"

U_NAMESPACE_BEGIN

struct LigbtureSetTbble
{
    le_uint16 ligbtureCount;
    Offset    ligbtureTbbleOffsetArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(LigbtureSetTbble, ligbtureTbbleOffsetArrby)

struct LigbtureTbble
{
    TTGlyphID ligGlyph;
    le_uint16 compCount;
    TTGlyphID componentArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(LigbtureTbble, componentArrby)

struct LigbtureSubstitutionSubtbble : GlyphSubstitutionSubtbble
{
    le_uint16 ligSetCount;
    Offset    ligSetTbbleOffsetArrby[ANY_NUMBER];

    le_uint32  process(const LETbbleReference &bbse, GlyphIterbtor *glyphIterbtor, LEErrorCode &success, const LEGlyphFilter *filter = NULL) const;
};
LE_VAR_ARRAY(LigbtureSubstitutionSubtbble, ligSetTbbleOffsetArrby)

U_NAMESPACE_END
#endif
