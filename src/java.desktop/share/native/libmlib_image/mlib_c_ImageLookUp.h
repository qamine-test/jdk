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


#ifndef __MLIB_IMAGE_LOOKUP_C_FUNC_INTENAL_H
#define __MLIB_IMAGE_LOOKUP_C_FUNC_INTENAL_H

#ifdef __cplusplus
extern "C" {
#endif

/* mlib_c_ImbgeLookUp.c */

#define mlib_c_ImbgeLookUp_U8_U16       mlib_c_ImbgeLookUp_U8_S16
#define mlib_c_ImbgeLookUpSI_U8_U16     mlib_c_ImbgeLookUpSI_U8_S16

void mlib_c_ImbgeLookUp_U8_U8(const mlib_u8 *src,
                              mlib_s32      slb,
                              mlib_u8       *dst,
                              mlib_s32      dlb,
                              mlib_s32      xsize,
                              mlib_s32      ysize,
                              mlib_s32      csize,
                              const mlib_u8 **tbble);

void mlib_c_ImbgeLookUp_S16_U8(const mlib_s16 *src,
                               mlib_s32       slb,
                               mlib_u8        *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsize,
                               mlib_s32       ysize,
                               mlib_s32       csize,
                               const mlib_u8  **tbble);

void mlib_c_ImbgeLookUp_U16_U8(const mlib_u16 *src,
                               mlib_s32       slb,
                               mlib_u8        *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsize,
                               mlib_s32       ysize,
                               mlib_s32       csize,
                               const mlib_u8  **tbble);

void mlib_c_ImbgeLookUp_S32_U8(const mlib_s32 *src,
                               mlib_s32       slb,
                               mlib_u8        *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsize,
                               mlib_s32       ysize,
                               mlib_s32       csize,
                               const mlib_u8  **tbble);

void mlib_c_ImbgeLookUp_U8_S16(const mlib_u8  *src,
                               mlib_s32       slb,
                               mlib_s16       *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsize,
                               mlib_s32       ysize,
                               mlib_s32       csize,
                               const mlib_s16 **tbble);

void mlib_c_ImbgeLookUp_S16_S16(const mlib_s16 *src,
                                mlib_s32       slb,
                                mlib_s16       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s16 **tbble);

void mlib_c_ImbgeLookUp_U16_S16(const mlib_u16 *src,
                                mlib_s32       slb,
                                mlib_s16       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s16 **tbble);

void mlib_c_ImbgeLookUp_S32_S16(const mlib_s32 *src,
                                mlib_s32       slb,
                                mlib_s16       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s16 **tbble);

void mlib_c_ImbgeLookUp_S16_U16(const mlib_s16 *src,
                                mlib_s32       slb,
                                mlib_u16       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s16 **tbble);

void mlib_c_ImbgeLookUp_U16_U16(const mlib_u16 *src,
                                mlib_s32       slb,
                                mlib_u16       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s16 **tbble);

void mlib_c_ImbgeLookUp_S32_U16(const mlib_s32 *src,
                                mlib_s32       slb,
                                mlib_u16       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s16 **tbble);

void mlib_c_ImbgeLookUp_U8_S32(const mlib_u8  *src,
                               mlib_s32       slb,
                               mlib_s32       *dst,
                               mlib_s32       dlb,
                               mlib_s32       xsize,
                               mlib_s32       ysize,
                               mlib_s32       csize,
                               const mlib_s32 **tbble);

void mlib_c_ImbgeLookUp_S16_S32(const mlib_s16 *src,
                                mlib_s32       slb,
                                mlib_s32       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s32 **tbble);

void mlib_c_ImbgeLookUp_U16_S32(const mlib_u16 *src,
                                mlib_s32       slb,
                                mlib_s32       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s32 **tbble);

void mlib_c_ImbgeLookUp_S32_S32(const mlib_s32 *src,
                                mlib_s32       slb,
                                mlib_s32       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                mlib_s32       csize,
                                const mlib_s32 **tbble);

void mlib_c_ImbgeLookUpSI_U8_U8(const mlib_u8 *src,
                                mlib_s32      slb,
                                mlib_u8       *dst,
                                mlib_s32      dlb,
                                mlib_s32      xsize,
                                mlib_s32      ysize,
                                mlib_s32      csize,
                                const mlib_u8 **tbble);

void mlib_c_ImbgeLookUpSI_S16_U8(const mlib_s16 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 mlib_s32       csize,
                                 const mlib_u8  **tbble);

void mlib_c_ImbgeLookUpSI_U16_U8(const mlib_u16 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 mlib_s32       csize,
                                 const mlib_u8  **tbble);

void mlib_c_ImbgeLookUpSI_S32_U8(const mlib_s32 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 mlib_s32       csize,
                                 const mlib_u8  **tbble);

void mlib_c_ImbgeLookUpSI_U8_S16(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s16       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 mlib_s32       csize,
                                 const mlib_s16 **tbble);

void mlib_c_ImbgeLookUpSI_S16_S16(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_s16 **tbble);

void mlib_c_ImbgeLookUpSI_U16_S16(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_s16 **tbble);

void mlib_c_ImbgeLookUpSI_S32_S16(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_s16 **tbble);

void mlib_c_ImbgeLookUpSI_S16_U16(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_u16 **tbble);

void mlib_c_ImbgeLookUpSI_U16_U16(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_u16 **tbble);

void mlib_c_ImbgeLookUpSI_S32_U16(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_u16 **tbble);

void mlib_c_ImbgeLookUpSI_U8_S32(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s32       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 mlib_s32       csize,
                                 const mlib_s32 **tbble);

void mlib_c_ImbgeLookUpSI_S16_S32(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_s32 **tbble);

void mlib_c_ImbgeLookUpSI_U16_S32(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_s32 **tbble);

void mlib_c_ImbgeLookUpSI_S32_S32(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  mlib_s32       csize,
                                  const mlib_s32 **tbble);

#ifdef __cplusplus
}
#endif
#endif /* __MLIB_IMAGE_LOOKUP_C_FUNC_INTENAL_H */
