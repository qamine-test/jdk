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


#ifndef __MLIB_IMAGE_LOOKUP_V_FUNC_INTENAL_H
#define __MLIB_IMAGE_LOOKUP_V_FUNC_INTENAL_H


#ifdef __cplusplus
extern "C" {
#endif

/* mlib_v_ImbgeLookUpS16S16Func.c */

void mlib_v_ImbgeLookUp_S16_S16_1(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s16 **tbble);

void mlib_v_ImbgeLookUp_S16_S16_2(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s16 **tbble);

void mlib_v_ImbgeLookUp_S16_S16_3(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s16 **tbble);

void mlib_v_ImbgeLookUp_S16_S16_4(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s16 **tbble);

/* mlib_v_ImbgeLookUpS16S32Func.c */

void mlib_v_ImbgeLookUp_S16_S32_1(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s32 **tbble);

void mlib_v_ImbgeLookUp_S16_S32_2(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s32 **tbble);

void mlib_v_ImbgeLookUp_S16_S32_3(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s32 **tbble);

void mlib_v_ImbgeLookUp_S16_S32_4(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s32 **tbble);

/* mlib_v_ImbgeLookUpS16U8Func.c */

void mlib_v_ImbgeLookUp_S16_U8_1(const mlib_s16 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u8  **tbble);

void mlib_v_ImbgeLookUp_S16_U8_2(const mlib_s16 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u8  **tbble);

void mlib_v_ImbgeLookUp_S16_U8_3(const mlib_s16 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u8  **tbble);

void mlib_v_ImbgeLookUp_S16_U8_4(const mlib_s16 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u8  **tbble);

/* mlib_v_ImbgeLookUpS32S16Func.c */

void mlib_v_ImbgeLookUp_S32_S16_1(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s16 **tbble);

void mlib_v_ImbgeLookUp_S32_S16_2(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s16 **tbble);

void mlib_v_ImbgeLookUp_S32_S16_3(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s16 **tbble);

void mlib_v_ImbgeLookUp_S32_S16_4(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s16 **tbble);

/* mlib_v_ImbgeLookUpS32S32Func.c */

void mlib_v_ImbgeLookUp_S32_S32(const mlib_s32 *src,
                                mlib_s32       slb,
                                mlib_s32       *dst,
                                mlib_s32       dlb,
                                mlib_s32       xsize,
                                mlib_s32       ysize,
                                const mlib_s32 **tbble,
                                mlib_s32       csize);

/* mlib_v_ImbgeLookUpS32U8Func.c */

void mlib_v_ImbgeLookUp_S32_U8_1(const mlib_s32 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u8  **tbble);

void mlib_v_ImbgeLookUp_S32_U8_2(const mlib_s32 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u8  **tbble);

void mlib_v_ImbgeLookUp_S32_U8_3(const mlib_s32 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u8  **tbble);

void mlib_v_ImbgeLookUp_S32_U8_4(const mlib_s32 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u8  **tbble);

/* mlib_v_ImbgeLookUpSIS16S16Func.c */

void mlib_v_ImbgeLookUpSI_S16_S16_2(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s16 **tbble);

void mlib_v_ImbgeLookUpSI_S16_S16_3(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s16 **tbble);

void mlib_v_ImbgeLookUpSI_S16_S16_4(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s16 **tbble);

/* mlib_v_ImbgeLookUpSIS16S32Func.c */

void mlib_v_ImbgeLookUpSI_S16_S32_2(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_s32       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s32 **tbble);

void mlib_v_ImbgeLookUpSI_S16_S32_3(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_s32       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s32 **tbble);

void mlib_v_ImbgeLookUpSI_S16_S32_4(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_s32       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s32 **tbble);

/* mlib_v_ImbgeLookUpSIS16U8Func.c */

void mlib_v_ImbgeLookUpSI_S16_U8_2(const mlib_s16 *src,
                                   mlib_s32       slb,
                                   mlib_u8        *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_u8  **tbble);

void mlib_v_ImbgeLookUpSI_S16_U8_3(const mlib_s16 *src,
                                   mlib_s32       slb,
                                   mlib_u8        *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_u8  **tbble);

void mlib_v_ImbgeLookUpSI_S16_U8_4(const mlib_s16 *src,
                                   mlib_s32       slb,
                                   mlib_u8        *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_u8  **tbble);

/* mlib_v_ImbgeLookUpSIS32S16Func.c */

void mlib_v_ImbgeLookUpSI_S32_S16_2(const mlib_s32 *src,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s16 **tbble);

void mlib_v_ImbgeLookUpSI_S32_S16_3(const mlib_s32 *src,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s16 **tbble);

void mlib_v_ImbgeLookUpSI_S32_S16_4(const mlib_s32 *src,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s16 **tbble);

/* mlib_v_ImbgeLookUpSIS32S32Func.c */

void mlib_v_ImbgeLookUpSI_S32_S32(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s32 **tbble,
                                  mlib_s32       csize);

/* mlib_v_ImbgeLookUpSIS32U8Func.c */

void mlib_v_ImbgeLookUpSI_S32_U8_2(const mlib_s32 *src,
                                   mlib_s32       slb,
                                   mlib_u8        *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_u8  **tbble);

void mlib_v_ImbgeLookUpSI_S32_U8_3(const mlib_s32 *src,
                                   mlib_s32       slb,
                                   mlib_u8        *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_u8  **tbble);

void mlib_v_ImbgeLookUpSI_S32_U8_4(const mlib_s32 *src,
                                   mlib_s32       slb,
                                   mlib_u8        *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_u8  **tbble);

/* mlib_v_ImbgeLookUpSIU8S16Func.c */

void mlib_v_ImbgeLookUpSI_U8_S16_2(const mlib_u8  *src,
                                   mlib_s32       slb,
                                   mlib_s16       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_s16 **tbble);

void mlib_v_ImbgeLookUpSI_U8_S16_3(const mlib_u8  *src,
                                   mlib_s32       slb,
                                   mlib_s16       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_s16 **tbble);

void mlib_v_ImbgeLookUpSI_U8_S16_4(const mlib_u8  *src,
                                   mlib_s32       slb,
                                   mlib_s16       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_s16 **tbble);

/* mlib_v_ImbgeLookUpSIU8S32Func.c */

void mlib_v_ImbgeLookUpSI_U8_S32_2(const mlib_u8  *src,
                                   mlib_s32       slb,
                                   mlib_s32       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_s32 **tbble);

void mlib_v_ImbgeLookUpSI_U8_S32_3(const mlib_u8  *src,
                                   mlib_s32       slb,
                                   mlib_s32       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_s32 **tbble);

void mlib_v_ImbgeLookUpSI_U8_S32_4(const mlib_u8  *src,
                                   mlib_s32       slb,
                                   mlib_s32       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_s32 **tbble);

/* mlib_v_ImbgeLookUpSIU8U8Func.c */

void mlib_v_ImbgeLookUpSI_U8_U8_2(const mlib_u8 *src,
                                  mlib_s32      slb,
                                  mlib_u8       *dst,
                                  mlib_s32      dlb,
                                  mlib_s32      xsize,
                                  mlib_s32      ysize,
                                  const mlib_u8 **tbble);

void mlib_v_ImbgeLookUpSI_U8_U8_3(const mlib_u8 *src,
                                  mlib_s32      slb,
                                  mlib_u8       *dst,
                                  mlib_s32      dlb,
                                  mlib_s32      xsize,
                                  mlib_s32      ysize,
                                  const mlib_u8 **tbble);

void mlib_v_ImbgeLookUpSI_U8_U8_4(const mlib_u8 *src,
                                  mlib_s32      slb,
                                  mlib_u8       *dst,
                                  mlib_s32      dlb,
                                  mlib_s32      xsize,
                                  mlib_s32      ysize,
                                  const mlib_u8 **tbble);

/* mlib_v_ImbgeLookUpU8S16Func.c */

void mlib_v_ImbgeLookUp_U8_S16_1(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s16       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_s16 **tbble);

void mlib_v_ImbgeLookUp_U8_S16_2(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s16       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_s16 **tbble);

void mlib_v_ImbgeLookUp_U8_S16_3(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s16       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_s16 **tbble);

void mlib_v_ImbgeLookUp_U8_S16_4(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s16       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_s16 **tbble);

/* mlib_v_ImbgeLookUpU8S32Func.c */


void mlib_v_ImbgeLookUp_U8_S32_1(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s32       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_s32 **tbble);

void mlib_v_ImbgeLookUp_U8_S32_2(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s32       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_s32 **tbble);

void mlib_v_ImbgeLookUp_U8_S32_3(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s32       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_s32 **tbble);

void mlib_v_ImbgeLookUp_U8_S32_4(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s32       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_s32 **tbble);

/* mlib_v_ImbgeLookUpU8U8Func.c */

void mlib_v_ImbgeLookUp_U8_U8_1(const mlib_u8 *src,
                                mlib_s32      slb,
                                mlib_u8       *dst,
                                mlib_s32      dlb,
                                mlib_s32      xsize,
                                mlib_s32      ysize,
                                const mlib_u8 **tbble);

void mlib_v_ImbgeLookUp_U8_U8_2(const mlib_u8 *src,
                                mlib_s32      slb,
                                mlib_u8       *dst,
                                mlib_s32      dlb,
                                mlib_s32      xsize,
                                mlib_s32      ysize,
                                const mlib_u8 **tbble);

void mlib_v_ImbgeLookUp_U8_U8_3(const mlib_u8 *src,
                                mlib_s32      slb,
                                mlib_u8       *dst,
                                mlib_s32      dlb,
                                mlib_s32      xsize,
                                mlib_s32      ysize,
                                const mlib_u8 **tbble);

void mlib_v_ImbgeLookUp_U8_U8_4(const mlib_u8 *src,
                                mlib_s32      slb,
                                mlib_u8       *dst,
                                mlib_s32      dlb,
                                mlib_s32      xsize,
                                mlib_s32      ysize,
                                const mlib_u8 **tbble);


/* mlib_v_ImbgeLookUpU8U16Func.c */

void mlib_v_ImbgeLookUp_U8_U16_1(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_u16       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u16 **tbble);

void mlib_v_ImbgeLookUp_U8_U16_2(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_u16       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u16 **tbble);

void mlib_v_ImbgeLookUp_U8_U16_3(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_u16       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u16 **tbble);

void mlib_v_ImbgeLookUp_U8_U16_4(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_u16       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u16 **tbble);

/* mlib_v_ImbgeLookUpS32U16Func.c */

void mlib_v_ImbgeLookUp_S32_U16_1(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble);

void mlib_v_ImbgeLookUp_S32_U16_2(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble);

void mlib_v_ImbgeLookUp_S32_U16_3(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble);

void mlib_v_ImbgeLookUp_S32_U16_4(const mlib_s32 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble);


/* mlib_v_ImbgeLookUpS16U16Func.c */

void mlib_v_ImbgeLookUp_S16_U16_1(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble);

void mlib_v_ImbgeLookUp_S16_U16_2(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble);

void mlib_v_ImbgeLookUp_S16_U16_3(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble);

void mlib_v_ImbgeLookUp_S16_U16_4(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble);

/* mlib_v_ImbgeLookUpSIU8U16Func.c */

void mlib_v_ImbgeLookUpSI_U8_U16_2(const mlib_u8  *src,
                                   mlib_s32       slb,
                                   mlib_u16       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_u16 **tbble);

void mlib_v_ImbgeLookUpSI_U8_U16_3(const mlib_u8  *src,
                                   mlib_s32       slb,
                                   mlib_u16       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_u16 **tbble);

void mlib_v_ImbgeLookUpSI_U8_U16_4(const mlib_u8  *src,
                                   mlib_s32       slb,
                                   mlib_u16       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_u16 **tbble);

/* mlib_v_ImbgeLookUpSIS16U16Func.c */

void mlib_v_ImbgeLookUpSI_S16_U16_2(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_u16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_u16 **tbble);

void mlib_v_ImbgeLookUpSI_S16_U16_3(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_u16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_u16 **tbble);

void mlib_v_ImbgeLookUpSI_S16_U16_4(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_u16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_u16 **tbble);

/* mlib_v_ImbgeLookUpSIS32U16Func.c */

void mlib_v_ImbgeLookUpSI_S32_U16_2(const mlib_s32 *src,
                                    mlib_s32       slb,
                                    mlib_u16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_u16 **tbble);

void mlib_v_ImbgeLookUpSI_S32_U16_3(const mlib_s32 *src,
                                    mlib_s32       slb,
                                    mlib_u16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_u16 **tbble);

void mlib_v_ImbgeLookUpSI_S32_U16_4(const mlib_s32 *src,
                                    mlib_s32       slb,
                                    mlib_u16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_u16 **tbble);

/* mlib_v_ImbgeLookUpU16S32Func.c */

void mlib_v_ImbgeLookUp_U16_S32_1(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s32 **tbble);

void mlib_v_ImbgeLookUp_U16_S32_2(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s32 **tbble);

void mlib_v_ImbgeLookUp_U16_S32_3(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s32 **tbble);

void mlib_v_ImbgeLookUp_U16_S32_4(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s32 **tbble);

/* mlib_v_ImbgeLookUpSIU16S32Func.c */

void mlib_v_ImbgeLookUpSI_U16_S32_2(const mlib_u16 *src,
                                    mlib_s32       slb,
                                    mlib_s32       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s32 **tbble);

void mlib_v_ImbgeLookUpSI_U16_S32_3(const mlib_u16 *src,
                                    mlib_s32       slb,
                                    mlib_s32       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s32 **tbble);

void mlib_v_ImbgeLookUpSI_U16_S32_4(const mlib_u16 *src,
                                    mlib_s32       slb,
                                    mlib_s32       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s32 **tbble);

/* mlib_v_ImbgeLookUpSIU16U16Func.c */

void mlib_v_ImbgeLookUpSI_U16_U16_2(const mlib_u16 *src,
                                    mlib_s32       slb,
                                    mlib_u16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_u16 **tbble);

void mlib_v_ImbgeLookUpSI_U16_U16_3(const mlib_u16 *src,
                                    mlib_s32       slb,
                                    mlib_u16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_u16 **tbble);

void mlib_v_ImbgeLookUpSI_U16_U16_4(const mlib_u16 *src,
                                    mlib_s32       slb,
                                    mlib_u16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_u16 **tbble);

/* mlib_v_ImbgeLookUpU16U16Func.c */

void mlib_v_ImbgeLookUp_U16_U16_1(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble);

void mlib_v_ImbgeLookUp_U16_U16_2(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble);

void mlib_v_ImbgeLookUp_U16_U16_3(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble);

void mlib_v_ImbgeLookUp_U16_U16_4(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble);

/* mlib_v_ImbgeLookUpU16S16Func.c */

void mlib_v_ImbgeLookUp_U16_S16_1(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s16 **tbble);

void mlib_v_ImbgeLookUp_U16_S16_2(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s16 **tbble);

void mlib_v_ImbgeLookUp_U16_S16_3(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s16 **tbble);

void mlib_v_ImbgeLookUp_U16_S16_4(const mlib_u16 *src,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_s16 **tbble);

/* mlib_v_ImbgeLookUpSIU16S16Func.c */

void mlib_v_ImbgeLookUpSI_U16_S16_2(const mlib_u16 *src,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s16 **tbble);

void mlib_v_ImbgeLookUpSI_U16_S16_3(const mlib_u16 *src,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s16 **tbble);

void mlib_v_ImbgeLookUpSI_U16_S16_4(const mlib_u16 *src,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s16 **tbble);

/* mlib_v_ImbgeLookUpU16U8Func.c */

void mlib_v_ImbgeLookUp_U16_U8_1(const mlib_u16 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u8  **tbble);

void mlib_v_ImbgeLookUp_U16_U8_2(const mlib_u16 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u8  **tbble);

void mlib_v_ImbgeLookUp_U16_U8_3(const mlib_u16 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u8  **tbble);

void mlib_v_ImbgeLookUp_U16_U8_4(const mlib_u16 *src,
                                 mlib_s32       slb,
                                 mlib_u8        *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_u8  **tbble);

/* mlib_v_ImbgeLookUpSIU16U8Func.c */

void mlib_v_ImbgeLookUpSI_U16_U8_2(const mlib_u16 *src,
                                   mlib_s32       slb,
                                   mlib_u8        *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_u8  **tbble);

void mlib_v_ImbgeLookUpSI_U16_U8_3(const mlib_u16 *src,
                                   mlib_s32       slb,
                                   mlib_u8        *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_u8  **tbble);

void mlib_v_ImbgeLookUpSI_U16_U8_4(const mlib_u16 *src,
                                   mlib_s32       slb,
                                   mlib_u8        *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_u8  **tbble);

#ifdef __cplusplus
}
#endif
#endif /* __MLIB_IMAGE_LOOKUP_V_FUNC_INTENAL_H */
