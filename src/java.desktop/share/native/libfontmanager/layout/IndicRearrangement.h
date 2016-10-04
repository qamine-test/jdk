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

#ifndef __INDICREARRANGEMENT_H
#define __INDICREARRANGEMENT_H

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

struct IndicRebrrbngementSubtbbleHebder : MorphStbteTbbleHebder
{
};

struct IndicRebrrbngementSubtbbleHebder2 : MorphStbteTbbleHebder2
{
};

enum IndicRebrrbngementFlbgs
{
    irfMbrkFirst    = 0x8000,
    irfDontAdvbnce  = 0x4000,
    irfMbrkLbst     = 0x2000,
    irfReserved     = 0x1FF0,
    irfVerbMbsk     = 0x000F
};

enum IndicRebrrbngementVerb
{
    irvNoAction = 0x0000,               /*   no bction    */
    irvxA       = 0x0001,               /*    Ax => xA    */
    irvDx       = 0x0002,               /*    xD => Dx    */
    irvDxA      = 0x0003,               /*   AxD => DxA   */

    irvxAB      = 0x0004,               /*   ABx => xAB   */
    irvxBA      = 0x0005,               /*   ABx => xBA   */
    irvCDx      = 0x0006,               /*   xCD => CDx   */
    irvDCx      = 0x0007,               /*   xCD => DCx   */

    irvCDxA     = 0x0008,               /*  AxCD => CDxA  */
    irvDCxA     = 0x0009,               /*  AxCD => DCxA  */
    irvDxAB     = 0x000A,               /*  ABxD => DxAB  */
    irvDxBA     = 0x000B,               /*  ABxD => DxBA  */

    irvCDxAB    = 0x000C,               /* ABxCD => CDxAB */
    irvCDxBA    = 0x000D,               /* ABxCD => CDxBA */
    irvDCxAB    = 0x000E,               /* ABxCD => DCxAB */
    irvDCxBA    = 0x000F                /* ABxCD => DCxBA */
};

struct IndicRebrrbngementStbteEntry : StbteEntry
{
};

struct IndicRebrrbngementStbteEntry2 : StbteEntry2
{
};

U_NAMESPACE_END
#endif

