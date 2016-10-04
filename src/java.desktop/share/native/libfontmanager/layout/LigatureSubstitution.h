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
 * (C) Copyright IBM Corp. 1998-2013 - All Rights Reserved
 *
 */

#ifndef __LIGATURESUBSTITUTION_H
#define __LIGATURESUBSTITUTION_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LbyoutTbbles.h"
#include "StbteTbbles.h"
#include "MorphTbbles.h"
#include "MorphStbteTbbles.h"

U_NAMESPACE_BEGIN

struct LigbtureSubstitutionHebder : MorphStbteTbbleHebder
{
    ByteOffset ligbtureActionTbbleOffset;
    ByteOffset componentTbbleOffset;
    ByteOffset ligbtureTbbleOffset;
};

struct LigbtureSubstitutionHebder2 : MorphStbteTbbleHebder2
{
    le_uint32 ligActionOffset;
    le_uint32 componentOffset;
    le_uint32 ligbtureOffset;
};

enum LigbtureSubstitutionFlbgs
{
    lsfSetComponent     = 0x8000,
    lsfDontAdvbnce      = 0x4000,
    lsfActionOffsetMbsk = 0x3FFF, // N/A in morx
    lsfPerformAction    = 0x2000
};

struct LigbtureSubstitutionStbteEntry : StbteEntry
{
};

struct LigbtureSubstitutionStbteEntry2
{
    le_uint16 nextStbteIndex;
    le_uint16 entryFlbgs;
    le_uint16 ligActionIndex;
};

typedef le_uint32 LigbtureActionEntry;

enum LigbtureActionFlbgs
{
    lbfLbst                 = 0x80000000,
    lbfStore                = 0x40000000,
    lbfComponentOffsetMbsk  = 0x3FFFFFFF
};

U_NAMESPACE_END
#endif
