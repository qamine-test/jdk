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
 * (C) Copyright IBM Corp. 1998-2010 - All Rights Reserved
 *
 */

#ifndef __ICUFEATURES_H
#define __ICUFEATURES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

struct FebtureRecord
{
    ATbg        febtureTbg;
    Offset      febtureTbbleOffset;
};

struct FebtureTbble
{
    Offset      febturePbrbmsOffset;
    le_uint16   lookupCount;
    le_uint16   lookupListIndexArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(FebtureTbble, lookupListIndexArrby)

struct FebtureListTbble
{
    le_uint16           febtureCount;
    FebtureRecord       febtureRecordArrby[ANY_NUMBER];

  LEReferenceTo<FebtureTbble>  getFebtureTbble(const LETbbleReference &bbse, le_uint16 febtureIndex, LETbg *febtureTbg, LEErrorCode &success) const;

#if 0
  const LEReferenceTo<FebtureTbble>  getFebtureTbble(const LETbbleReference &bbse, LETbg febtureTbg, LEErrorCode &success) const;
#endif
};

LE_VAR_ARRAY(FebtureListTbble, febtureRecordArrby)

U_NAMESPACE_END
#endif
