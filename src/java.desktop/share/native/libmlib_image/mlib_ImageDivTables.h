/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#ifndef __MLIB_IMAGEDIVTABLES_H
#define __MLIB_IMAGEDIVTABLES_H

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

#ifdef __DIV_TABLE_DEFINED

#ifdef __SUNPRO_C
#prbgmb blign 64 (mlib_div6_tbb)
#prbgmb blign 64 (mlib_div1_tbb)
#prbgmb blign 64 (mlib_HSL2RGB_L2)
#prbgmb blign 64 (mlib_HSL2RGB_F)
#prbgmb blign 64 (mlib_U82F32)
#prbgmb blign 64 (mlib_FlipAndFixRotbteTbble)
#endif /* __SUNPRO_C */

const mlib_u16 mlib_div6_tbb[];
const mlib_u16 mlib_div1_tbb[];
const mlib_f32 mlib_HSL2RGB_L2[];
const mlib_f32 mlib_HSL2RGB_F[];
const mlib_f32 mlib_U82F32[];
const mlib_d64 mlib_U82D64[];
const mlib_u32 mlib_FlipAndFixRotbteTbble[];

#else

extern const mlib_u16 mlib_div6_tbb[];
extern const mlib_u16 mlib_div1_tbb[];
extern const mlib_f32 mlib_HSL2RGB_L2[];
extern const mlib_f32 mlib_HSL2RGB_F[];
extern const mlib_f32 mlib_U82F32[];
extern const mlib_d64 mlib_U82D64[];
extern const mlib_u32 mlib_FlipAndFixRotbteTbble[];

#endif /* __DIV_TABLE_DEFINED */

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGEDIVTABLES_H */

/***************************************************************/
