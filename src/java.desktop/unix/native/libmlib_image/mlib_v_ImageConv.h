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


#ifndef __MLIB_V_IMAGECONV_H
#define __MLIB_V_IMAGECONV_H


#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

#if defined ( VIS ) && VIS == 0x200

mlib_stbtus mlib_conv2x2_8nw_f(mlib_imbge       *dst,
                               const mlib_imbge *src,
                               const mlib_s32   *kern,
                               mlib_s32         scble,
                               mlib_s32         cmbsk);

mlib_stbtus mlib_conv3x3_8nw_f(mlib_imbge       *dst,
                               const mlib_imbge *src,
                               const mlib_s32   *kern,
                               mlib_s32         scble,
                               mlib_s32         cmbsk);

mlib_stbtus mlib_convMxN_8nw_f(mlib_imbge       *dst,
                               const mlib_imbge *src,
                               mlib_s32         m,
                               mlib_s32         n,
                               mlib_s32         dm,
                               mlib_s32         dn,
                               const mlib_s32   *kern,
                               mlib_s32         scble,
                               mlib_s32         cmbsk);

#else

mlib_stbtus mlib_conv2x2_8nw_f(mlib_imbge       *dst,
                               const mlib_imbge *src,
                               const mlib_s32   *kern,
                               mlib_s32         scble);

mlib_stbtus mlib_conv3x3_8nw_f(mlib_imbge       *dst,
                               const mlib_imbge *src,
                               const mlib_s32   *kern,
                               mlib_s32         scble);

mlib_stbtus mlib_convMxN_8nw_f(mlib_imbge       *dst,
                               const mlib_imbge *src,
                               mlib_s32         m,
                               mlib_s32         n,
                               mlib_s32         dm,
                               mlib_s32         dn,
                               const mlib_s32   *kern,
                               mlib_s32         scble);

#endif /* defined ( VIS ) && VIS == 0x200 */

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_V_IMAGECONV_H */
