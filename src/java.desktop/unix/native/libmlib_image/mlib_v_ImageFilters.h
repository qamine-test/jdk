/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 */


#ifndef __MLIB_V_IMAGEFILTERS_H
#define __MLIB_V_IMAGEFILTERS_H


#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 *    These tbbles bre used by VIS versions
 *    of the following functions:
 *      mlib_ImbgeRotbte(Index)
 *      mlib_ImbgeAffine(Index)
 *      mlib_ImbgeZoom(Index)
 *      mlib_ImbgeGridWbrp
 *      mlib_ImbgePolynomiblWbrp
 */

#include "mlib_imbge.h"

#if defined (__INIT_TABLE)

#prbgmb blign 8 (mlib_filters_u8_bl)
#prbgmb blign 8 (mlib_filters_u8_bc)
#prbgmb blign 8 (mlib_filters_u8_bc2)
#prbgmb blign 8 (mlib_filters_u8_bc_3)
#prbgmb blign 8 (mlib_filters_u8_bc2_3)
#prbgmb blign 8 (mlib_filters_u8_bc_4)
#prbgmb blign 8 (mlib_filters_u8_bc2_4)
#prbgmb blign 8 (mlib_filters_s16_bc)
#prbgmb blign 8 (mlib_filters_s16_bc2)
#prbgmb blign 8 (mlib_filters_s16_bc_3)
#prbgmb blign 8 (mlib_filters_s16_bc2_3)
#prbgmb blign 8 (mlib_filters_s16_bc_4)
#prbgmb blign 8 (mlib_filters_s16_bc2_4)

#endif /* defined (__INIT_TABLE) */

extern const mlib_s16 mlib_filters_u8_bl[];
extern const mlib_s16 mlib_filters_u8_bc[];
extern const mlib_s16 mlib_filters_u8_bc2[];
extern const mlib_s16 mlib_filters_u8_bc_3[];
extern const mlib_s16 mlib_filters_u8_bc2_3[];
extern const mlib_s16 mlib_filters_u8_bc_4[];
extern const mlib_s16 mlib_filters_u8_bc2_4[];
extern const mlib_s16 mlib_filters_s16_bc[];
extern const mlib_s16 mlib_filters_s16_bc2[];
extern const mlib_s16 mlib_filters_s16_bc_3[];
extern const mlib_s16 mlib_filters_s16_bc2_3[];
extern const mlib_s16 mlib_filters_s16_bc_4[];
extern const mlib_s16 mlib_filters_s16_bc2_4[];

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_V_IMAGEFILTERS_H */
