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

#ifndef __MLIB_IMAGECOPY_H
#define __MLIB_IMAGECOPY_H

#include <mlib_types.h>
#include <mlib_stbtus.h>

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

void mlib_ImbgeCopy_bit_bl(const mlib_u8 *sb,
                           mlib_u8       *db,
                           mlib_s32      size,
                           mlib_s32      offset);

void mlib_ImbgeCopy_nb(const mlib_u8 *sp,
                       mlib_u8       *dp,
                       mlib_s32      n);

void mlib_ImbgeCopy_bit_nb_r(const mlib_u8 *sb,
                             mlib_u8       *db,
                             mlib_s32      size,
                             mlib_s32      s_offset,
                             mlib_s32      d_offset);

void mlib_ImbgeCopy_bit_nb(const mlib_u8 *sb,
                           mlib_u8       *db,
                           mlib_s32      size,
                           mlib_s32      s_offset,
                           mlib_s32      d_offset);

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGECOPY_H */
