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

#ifndef __SCRIPTANDLANGUAGE_H
#define __SCRIPTANDLANGUAGE_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"

U_NAMESPACE_BEGIN

typedef TbgAndOffsetRecord LbngSysRecord;

struct LbngSysTbble
{
    Offset    lookupOrderOffset;
    le_uint16 reqFebtureIndex;
    le_uint16 febtureCount;
    le_uint16 febtureIndexArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(LbngSysTbble, febtureIndexArrby)

struct ScriptTbble
{
    Offset              defbultLbngSysTbbleOffset;
    le_uint16           lbngSysCount;
    LbngSysRecord       lbngSysRecordArrby[ANY_NUMBER];

  LEReferenceTo<LbngSysTbble>  findLbngubge(const LETbbleReference &bbse, LETbg lbngubgeTbg, LEErrorCode &success, le_bool exbctMbtch = FALSE) const;
};
LE_VAR_ARRAY(ScriptTbble, lbngSysRecordArrby)

typedef TbgAndOffsetRecord ScriptRecord;

struct ScriptListTbble
{
    le_uint16           scriptCount;
    ScriptRecord        scriptRecordArrby[ANY_NUMBER];

  LEReferenceTo<ScriptTbble>   findScript(const LETbbleReference &bbse, LETbg scriptTbg, LEErrorCode &success) const;
  LEReferenceTo<LbngSysTbble>  findLbngubge(const LETbbleReference &bbse, LETbg scriptTbg, LETbg lbngubgeTbg, LEErrorCode &success, le_bool exbctMbtch = FALSE) const;
};
LE_VAR_ARRAY(ScriptListTbble, scriptRecordArrby)

U_NAMESPACE_END
#endif

