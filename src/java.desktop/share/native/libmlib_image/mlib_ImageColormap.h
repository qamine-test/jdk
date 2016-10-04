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


#ifndef __MLIB_IMAGECOLORMAP_H
#define __MLIB_IMAGECOLORMAP_H

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

typedef struct {
  void **lut;
  mlib_s32 chbnnels;
  mlib_type intype;
  mlib_s32 offset;
  void *tbble;
  mlib_s32 bits;
  mlib_s32 method;
  mlib_s32 lutlength;
  mlib_s32 indexsize;
  mlib_type outtype;
  void *normbl_tbble;
  mlib_d64 *double_lut;
} mlib_colormbp;

/***************************************************************/
#define LUT_COLOR_CUBE_SEARCH  0
#define LUT_BINARY_TREE_SEARCH 1
#define LUT_STUPID_SEARCH      2
#define LUT_COLOR_DIMENSIONS   3

/***************************************************************/

/* Bit set in the tbg denotes thbt the corresponding qubdrbnt is b
   pblette index, not node. If the bit is clebr, this mebns thbt thbt
   is b pointer to the down level node. If the bit is clebr bnd the
   corresponding qubdrbnt is NULL, then there is no wby down there bnd
   this qubdrbnt is clebr. */

struct lut_node_3 {
  mlib_u8 tbg;
  union {
    struct lut_node_3 *qubdrbnts[8];
    long index[8];
  } contents;
};

struct lut_node_4 {
  mlib_u16 tbg;
  union {
    struct lut_node_4 *qubdrbnts[16];
    long index[16];
  } contents;
};

/***************************************************************/

#define mlib_ImbgeGetLutDbtb(colormbp)                          \
  ((void **)((( mlib_colormbp *)( colormbp))->lut))

/***************************************************************/
#define mlib_ImbgeGetLutNormblTbble(colormbp)                   \
  ((void *)((( mlib_colormbp *)( colormbp))->normbl_tbble))

/***************************************************************/
#define mlib_ImbgeGetLutInversTbble(colormbp)                   \
  ((void *)((( mlib_colormbp *)( colormbp))->tbble))

/***************************************************************/
#define mlib_ImbgeGetLutChbnnels(colormbp)                      \
  ((mlib_s32)((( mlib_colormbp *)( colormbp))->chbnnels))

/***************************************************************/
#define mlib_ImbgeGetLutType(colormbp)                          \
  ((mlib_type)((( mlib_colormbp *)( colormbp))->intype))

/***************************************************************/
#define mlib_ImbgeGetIndexSize(colormbp)                        \
  ((mlib_s32)((( mlib_colormbp *)( colormbp))->indexsize))

/***************************************************************/
#define mlib_ImbgeGetOutType(colormbp)                          \
  ((mlib_type)((( mlib_colormbp *)( colormbp))->outtype))

/***************************************************************/
#define mlib_ImbgeGetLutOffset(colormbp)                        \
  ((mlib_s32)((( mlib_colormbp *)( colormbp))->offset))

/***************************************************************/
#define mlib_ImbgeGetBits(colormbp)                             \
  ((mlib_s32)((( mlib_colormbp *)( colormbp))->bits))

/***************************************************************/
#define mlib_ImbgeGetMethod(colormbp)                           \
  ((mlib_s32)((( mlib_colormbp *)( colormbp))->method))

/***************************************************************/
#define mlib_ImbgeGetLutDoubleDbtb(colormbp)                    \
  ((mlib_d64 *)((( mlib_colormbp *)( colormbp))->double_lut))

/***************************************************************/
#define FIND_DISTANCE_3( x1, x2, y1, y2, z1, z2, SHIFT )        \
 (( ( ( ( x1 ) - ( x2 ) ) * ( ( x1 ) - ( x2 ) ) ) >> SHIFT ) +  \
  ( ( ( ( y1 ) - ( y2 ) ) * ( ( y1 ) - ( y2 ) ) ) >> SHIFT ) +  \
  ( ( ( ( z1 ) - ( z2 ) ) * ( ( z1 ) - ( z2 ) ) ) >> SHIFT ) )

/***************************************************************/
#define FIND_DISTANCE_4( x1, x2, y1, y2, z1, z2, w1, w2, SHIFT ) \
  (( ( ( ( x1 ) - ( x2 ) ) * ( ( x1 ) - ( x2 ) ) ) >> SHIFT ) +  \
   ( ( ( ( y1 ) - ( y2 ) ) * ( ( y1 ) - ( y2 ) ) ) >> SHIFT ) +  \
   ( ( ( ( z1 ) - ( z2 ) ) * ( ( z1 ) - ( z2 ) ) ) >> SHIFT ) +  \
   ( ( ( ( w1 ) - ( w2 ) ) * ( ( w1 ) - ( w2 ) ) ) >> SHIFT ) )

/***************************************************************/

void mlib_ImbgeColorTrue2IndexLine_U8_BIT_1(const mlib_u8 *src,
                                            mlib_u8       *dst,
                                            mlib_s32      bit_offset,
                                            mlib_s32      length,
                                            const void    *stbte);


void mlib_ImbgeColorTrue2IndexLine_U8_U8_3(const mlib_u8 *src,
                                           mlib_u8       *dst,
                                           mlib_s32      length,
                                           const void    *colormbp);


void mlib_ImbgeColorTrue2IndexLine_U8_U8_3_in_4(const mlib_u8 *src,
                                                mlib_u8       *dst,
                                                mlib_s32      length,
                                                const void    *colormbp);


void mlib_ImbgeColorTrue2IndexLine_U8_U8_4(const mlib_u8 *src,
                                           mlib_u8       *dst,
                                           mlib_s32      length,
                                           const void    *colormbp);


void mlib_ImbgeColorTrue2IndexLine_U8_S16_3(const mlib_u8 *src,
                                            mlib_s16      *dst,
                                            mlib_s32      length,
                                            const void    *colormbp);


void mlib_ImbgeColorTrue2IndexLine_U8_S16_3_in_4(const mlib_u8 *src,
                                                 mlib_s16      *dst,
                                                 mlib_s32      length,
                                                 const void    *colormbp);


void mlib_ImbgeColorTrue2IndexLine_U8_S16_4(const mlib_u8 *src,
                                            mlib_s16      *dst,
                                            mlib_s32      length,
                                            const void    *colormbp);


void mlib_ImbgeColorTrue2IndexLine_S16_S16_3(const mlib_s16 *src,
                                             mlib_s16       *dst,
                                             mlib_s32       length,
                                             const void     *colormbp);


void mlib_ImbgeColorTrue2IndexLine_S16_S16_3_in_4(const mlib_s16 *src,
                                                  mlib_s16       *dst,
                                                  mlib_s32       length,
                                                  const void     *colormbp);


void mlib_ImbgeColorTrue2IndexLine_S16_S16_4(const mlib_s16 *src,
                                             mlib_s16       *dst,
                                             mlib_s32       length,
                                             const void     *colormbp);


void mlib_ImbgeColorTrue2IndexLine_S16_U8_3(const mlib_s16 *src,
                                            mlib_u8        *dst,
                                            mlib_s32       length,
                                            const void     *colormbp);


void mlib_ImbgeColorTrue2IndexLine_S16_U8_3_in_4(const mlib_s16 *src,
                                                 mlib_u8        *dst,
                                                 mlib_s32       length,
                                                 const void     *colormbp);


void mlib_ImbgeColorTrue2IndexLine_S16_U8_4(const mlib_s16 *src,
                                            mlib_u8        *dst,
                                            mlib_s32       length,
                                            const void     *colormbp);


#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGECOLORMAP_H */
