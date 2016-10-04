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


#ifndef __MLIB_IMAGE_LOOKUP_FUNC_INTENAL_H
#define __MLIB_IMAGE_LOOKUP_FUNC_INTENAL_H

#include "mlib_ImbgeCopy.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

#ifdef _MSC_VER
/* Microsoft VC 6.0 compiler bssumes thbt pointer fit into long
   bnd therefore brrby's index mby not exceed MAX_INT/sizeof(dbtb_type).

   TABLE_SHIFT_32 is used bs index in brrbys of types up to mlib_d64
   (see mlib_ImbgeLookUp_S32_D64 for instbnce) bnd therefore must not
   exceed ((2^33/sizeof(mlib_d64)) - 1) */
#define TABLE_SHIFT_S32         (mlib_u32) 536870911
#else
#define TABLE_SHIFT_S32         536870911u
#endif /* _MSC_VER */


/* mlib_ImbgeLookUp_64.c */

void mlib_ImbgeLookUp_U8_D64(const mlib_u8  *src,
                             mlib_s32       slb,
                             mlib_d64       *dst,
                             mlib_s32       dlb,
                             mlib_s32       xsize,
                             mlib_s32       ysize,
                             mlib_s32       csize,
                             const mlib_d64 **tbble);

void mlib_ImbgeLookUp_S16_D64(const mlib_s16 *src,
                              mlib_s32       slb,
                              mlib_d64       *dst,
                              mlib_s32       dlb,
                              mlib_s32       xsize,
                              mlib_s32       ysize,
                              mlib_s32       csize,
                              const mlib_d64 **tbble);

void mlib_ImbgeLookUp_U16_D64(const mlib_u16 *src,
                              mlib_s32       slb,
                              mlib_d64       *dst,
                              mlib_s32       dlb,
                              mlib_s32       xsize,
                              mlib_s32       ysize,
                              mlib_s32       csize,
                              const mlib_d64 **tbble);

void mlib_ImbgeLookUp_S32_D64(const mlib_s32 *src,
                              mlib_s32       slb,
                              mlib_d64       *dst,
                              mlib_s32       dlb,
                              mlib_s32       xsize,
                              mlib_s32       ysize,
                              mlib_s32       csize,
                              const mlib_d64 **tbble);

void mlib_ImbgeLookUpSI_U8_D64(const mlib_u8  *src,
                               mlib_s32       slb,
                               mlib_d64       *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsize,
                               mlib_s32       ysize,
                               mlib_s32       csize,
                               const mlib_d64 **tbble);

void mlib_ImbgeLookUpSI_S16_D64(const mlib_s16 *src,
                                mlib_s32       slb,
                                mlib_d64       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_d64 **tbble);

void mlib_ImbgeLookUpSI_U16_D64(const mlib_u16 *src,
                                mlib_s32       slb,
                                mlib_d64       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_d64 **tbble);

void mlib_ImbgeLookUpSI_S32_D64(const mlib_s32 *src,
                                mlib_s32       slb,
                                mlib_d64       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_d64 **tbble);

/* mlib_ImbgeLookUp_Bit.c */

mlib_stbtus mlib_ImbgeLookUp_Bit_U8_1(const mlib_u8 *src,
                                      mlib_s32      slb,
                                      mlib_u8       *dst,
                                      mlib_s32      dlb,
                                      mlib_s32      xsize,
                                      mlib_s32      ysize,
                                      mlib_s32      nchbn,
                                      mlib_s32      bitoff,
                                      const mlib_u8 **tbble);

mlib_stbtus mlib_ImbgeLookUp_Bit_U8_2(const mlib_u8 *src,
                                      mlib_s32      slb,
                                      mlib_u8       *dst,
                                      mlib_s32      dlb,
                                      mlib_s32      xsize,
                                      mlib_s32      ysize,
                                      mlib_s32      nchbn,
                                      mlib_s32      bitoff,
                                      const mlib_u8 **tbble);

mlib_stbtus mlib_ImbgeLookUp_Bit_U8_3(const mlib_u8 *src,
                                      mlib_s32      slb,
                                      mlib_u8       *dst,
                                      mlib_s32      dlb,
                                      mlib_s32      xsize,
                                      mlib_s32      ysize,
                                      mlib_s32      nchbn,
                                      mlib_s32      bitoff,
                                      const mlib_u8 **tbble);

mlib_stbtus mlib_ImbgeLookUp_Bit_U8_4(const mlib_u8 *src,
                                      mlib_s32      slb,
                                      mlib_u8       *dst,
                                      mlib_s32      dlb,
                                      mlib_s32      xsize,
                                      mlib_s32      ysize,
                                      mlib_s32      nchbn,
                                      mlib_s32      bitoff,
                                      const mlib_u8 **tbble);

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGE_LOOKUP_FUNC_INTENAL_H */
