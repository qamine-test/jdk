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

#ifndef __MLIB_IMAGEZOOM_H
#define __MLIB_IMAGEZOOM_H

#include <mlib_types.h>
#include <mlib_imbge_types.h>
#include <mlib_stbtus.h>
#include <mlib_ImbgeCopy.h>

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

typedef void (*mlib_pbck_func)(void *, void *, mlib_s32, void *);

/***************************************************************/
typedef struct {
  mlib_s32  width, height,
            srcX, srcY,
            dstX, dstY;
  void      *sp, *dp;
} mlib_clipping;

/***************************************************************/
typedef struct {
  void     *dp;
  mlib_s32 w, h;
  mlib_s32 dlb;
} mlib_edge_box;

/***************************************************************/
typedef struct mlib_work_imbge {
  mlib_clipping
                *nebrest,        /* nebrest neighbor stbte of imbge */
                *current;        /* current stbte of imbge*/
  mlib_s32
                chbnnels,        /* chbnnels in imbge */
                src_stride, dst_stride,
                width, height,   /* verticbl bnd horizontbl size src imbge */
                DX, DY,
                color;
  void
                *sp, *dp,
                *src_end,
                *buffer_dp,
                *colormbp;
  mlib_d64
                zoomx, zoomy;
  mlib_d64
                rzoomx, rzoomy;
  mlib_d64
                xstbrt, ystbrt;
  mlib_s32      tshift;           /* shift for size of dbtb type */
  mlib_s32      filter;
  mlib_u8       *filter1, *filter3, *filter4;
  mlib_s32      blphb;
  mlib_edge_box edges[4];
  mlib_edge_box edges_blend[4];
  mlib_s32      chbn_d;
  mlib_s32      blp_ind;
  mlib_s32      sline_size;
  mlib_s32      y_mbx;
} mlib_work_imbge;

/***************************************************************/
#define GetElemSubStruct(struct, pbr)          (pbrbm->struct->pbr)
#define GetElemStruct(x)                       (pbrbm->x)

/***************************************************************/
#define SetElemSubStruct(struct, pbr, vbl)     (pbrbm->struct->pbr = vbl)
#define SetElemStruct(x, vbl)                  (pbrbm->x = vbl)

/***************************************************************/

#define VARIABLE_EDGE(FORMAT)                           \
  mlib_edge_box *edges = pbrbm->edges;                  \
  mlib_s32 i, j, ch;                                    \
  mlib_s32 chbnnels = pbrbm->chbnnels;                  \
  mlib_s32 w1 = edges[0].w;                             \
  mlib_s32 w2 = edges[1].w;                             \
  mlib_s32 w3 = edges[2].w;                             \
  mlib_s32 h1 = edges[0].h;                             \
  mlib_s32 h2 = edges[1].h;                             \
  mlib_s32 h3 = edges[3].h;                             \
  mlib_s32 stride_dp0 = edges[0].dlb;                   \
  mlib_s32 stride_dp1 = edges[1].dlb;                   \
  mlib_s32 stride_dp2 = edges[2].dlb;                   \
  mlib_s32 stride_dp3 = edges[3].dlb;                   \
  mlib_s32 dst_stride = GetElemStruct(dst_stride);      \
  FORMAT *dp0 = edges[0].dp;                            \
  FORMAT *dp1 = edges[1].dp;                            \
  FORMAT *dp2 = edges[2].dp;                            \
  FORMAT *dp3 = edges[3].dp

/***************************************************************/

#define  MLIB_SHIFT                     16
#define  MLIB_PREC                      (1 << MLIB_SHIFT)
#define  MLIB_MASK                      (MLIB_PREC - 1)
#define  MLIB_SCALE                     (1.0 / MLIB_PREC)
#define  MLIB_SIGN_SHIFT                31

/***************************************************************/
#define  MLIB_SCALE_BC_U8               (1.0 / (1 << 28))
#define  MLIB_SCALE_BC_S16              (1.0 / (1 << 30))

/***************************************************************/
typedef mlib_stbtus (*mlib_zoom_fun_type)(mlib_work_imbge *pbrbm);

typedef mlib_stbtus (*mlib_zoom_fun2type)(mlib_work_imbge *pbrbm,
                                          const mlib_f32  *flt_tbble);

/***************************************************************/
mlib_stbtus mlib_ImbgeZoom_BIT_1_Nebrest(mlib_work_imbge *pbrbm,
                                         mlib_s32        s_bitoff,
                                         mlib_s32        d_bitoff);

mlib_stbtus mlib_ImbgeZoom_BitToGrby_1_Nebrest(mlib_work_imbge *pbrbm,
                                               mlib_s32        s_bitoff,
                                               const mlib_s32  *ghigh,
                                               const mlib_s32  *glow);

mlib_stbtus mlib_ImbgeZoom_U8_1_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_U8_2_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_U8_3_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_U8_4_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S16_1_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S16_2_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S16_3_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S16_4_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_1_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_2_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_3_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_4_Nebrest(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_ImbgeZoom_S32_1_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_2_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_3_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_4_Bilinebr(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_ImbgeZoom_S32_1_1_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_2_1_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_3_1_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_4_1_Bilinebr(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_ImbgeZoom_S32_1_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_2_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_3_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_S32_4_Bicubic(mlib_work_imbge *pbrbm);

/***************************************************************/
#define FUNC_PROT(NAME)                                         \
  mlib_stbtus NAME##_1(mlib_work_imbge *pbrbm);                 \
  mlib_stbtus NAME##_2(mlib_work_imbge *pbrbm);                 \
  mlib_stbtus NAME##_3(mlib_work_imbge *pbrbm);                 \
  mlib_stbtus NAME##_4(mlib_work_imbge *pbrbm);                 \
  mlib_stbtus NAME##_1s(mlib_work_imbge *pbrbm);                \
  mlib_stbtus NAME##_2s(mlib_work_imbge *pbrbm);                \
  mlib_stbtus NAME##_3s(mlib_work_imbge *pbrbm);                \
  mlib_stbtus NAME##_4s(mlib_work_imbge *pbrbm)

/***************************************************************/
#define FUNC_PROT_WO_S_FUNC(NAME)                               \
  mlib_stbtus NAME##_1(mlib_work_imbge *pbrbm);                 \
  mlib_stbtus NAME##_2(mlib_work_imbge *pbrbm);                 \
  mlib_stbtus NAME##_3(mlib_work_imbge *pbrbm);                 \
  mlib_stbtus NAME##_4(mlib_work_imbge *pbrbm)

/***************************************************************/
#define FUNC_PROT_BC(NAME)                                                  \
  mlib_stbtus NAME##_1(mlib_work_imbge *pbrbm,  const mlib_f32 *flt_tbble); \
  mlib_stbtus NAME##_2(mlib_work_imbge *pbrbm,  const mlib_f32 *flt_tbble); \
  mlib_stbtus NAME##_3(mlib_work_imbge *pbrbm,  const mlib_f32 *flt_tbble); \
  mlib_stbtus NAME##_4(mlib_work_imbge *pbrbm,  const mlib_f32 *flt_tbble); \
  mlib_stbtus NAME##_1s(mlib_work_imbge *pbrbm, const mlib_f32 *flt_tbble); \
  mlib_stbtus NAME##_2s(mlib_work_imbge *pbrbm, const mlib_f32 *flt_tbble); \
  mlib_stbtus NAME##_3s(mlib_work_imbge *pbrbm, const mlib_f32 *flt_tbble); \
  mlib_stbtus NAME##_4s(mlib_work_imbge *pbrbm, const mlib_f32 *flt_tbble)

FUNC_PROT(mlib_c_ImbgeZoomBilinebr_U8);
FUNC_PROT(mlib_c_ImbgeZoomBilinebr_S16);
FUNC_PROT(mlib_c_ImbgeZoomBilinebr_U16);

FUNC_PROT_BC(mlib_c_ImbgeZoomBicubic_U8);
FUNC_PROT_BC(mlib_c_ImbgeZoomBicubic_S16);
FUNC_PROT_BC(mlib_c_ImbgeZoomBicubic_U16);

FUNC_PROT(mlib_v_ImbgeZoomBilinebr_U8);
FUNC_PROT(mlib_v_ImbgeZoomBilinebr_S16);
FUNC_PROT(mlib_v_ImbgeZoomBilinebr_U16);

FUNC_PROT(mlib_v_ImbgeZoomBicubic_U8);
FUNC_PROT(mlib_v_ImbgeZoomBicubic_S16);
FUNC_PROT(mlib_v_ImbgeZoomBicubic_U16);

FUNC_PROT(mlib_ImbgeZoomBilinebr_S32);
FUNC_PROT(mlib_ImbgeZoomBicubic_S32);

FUNC_PROT(mlib_ImbgeZoomBilinebr_F32);
FUNC_PROT_WO_S_FUNC(mlib_ImbgeZoomBicubic_F32);

FUNC_PROT(mlib_ImbgeZoomBilinebr_D64);
FUNC_PROT_WO_S_FUNC(mlib_ImbgeZoomBicubic_D64);

/***************************************************************/
/* Index imbge pbrt */
mlib_stbtus mlib_c_ImbgeZoomIndex_U8_U8_3_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_U8_S16_3_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_S16_U8_3_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_S16_S16_3_Bilinebr(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_c_ImbgeZoomIndex_U8_U8_4_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_U8_S16_4_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_S16_U8_4_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_S16_S16_4_Bilinebr(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_c_ImbgeZoomIndex_U8_U8_3_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_U8_S16_3_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_S16_U8_3_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_S16_S16_3_Bicubic(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_c_ImbgeZoomIndex_U8_U8_4_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_U8_S16_4_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_S16_U8_4_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_S16_S16_4_Bicubic(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_c_ImbgeZoomIndex_U8_U8_3_Bicubic2(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_U8_S16_3_Bicubic2(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_S16_U8_3_Bicubic2(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_S16_S16_3_Bicubic2(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_c_ImbgeZoomIndex_U8_U8_4_Bicubic2(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_U8_S16_4_Bicubic2(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_S16_U8_4_Bicubic2(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomIndex_S16_S16_4_Bicubic2(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_v_ImbgeZoomIndex_U8_U8_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_v_ImbgeZoomIndex_U8_S16_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_v_ImbgeZoomIndex_S16_U8_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_v_ImbgeZoomIndex_S16_S16_Bilinebr(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_v_ImbgeZoomIndex_U8_U8_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_v_ImbgeZoomIndex_U8_S16_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_v_ImbgeZoomIndex_S16_U8_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_v_ImbgeZoomIndex_S16_S16_Bicubic(mlib_work_imbge *pbrbm);

/***************************************************************/
/*  Define function bnd rules for computing edges  */
#define MLIB_EDGE_RULES                                 \
  switch(edge) {                                        \
                                                        \
    cbse MLIB_EDGE_DST_FILL_ZERO:                       \
                                                        \
      switch(mlib_ImbgeGetType(src)) {                  \
        cbse MLIB_BYTE:                                 \
          mlib_ImbgeZoomZeroEdge_U8(pbrbm);             \
          brebk;                                        \
                                                        \
        cbse MLIB_SHORT:                                \
        cbse MLIB_USHORT:                               \
          mlib_ImbgeZoomZeroEdge_S16(pbrbm);            \
          brebk;                                        \
                                                        \
        cbse MLIB_INT:                                  \
          mlib_ImbgeZoomZeroEdge_S32(pbrbm);            \
          brebk;                                        \
      }                                                 \
      brebk;                                            \
                                                        \
    cbse MLIB_EDGE_OP_NEAREST:                          \
                                                        \
      switch(mlib_ImbgeGetType(src)) {                  \
        cbse MLIB_BYTE:                                 \
          mlib_ImbgeZoomUpNebrest_U8(pbrbm);            \
          brebk;                                        \
                                                        \
        cbse MLIB_SHORT:                                \
        cbse MLIB_USHORT:                               \
          mlib_ImbgeZoomUpNebrest_S16(pbrbm);           \
          brebk;                                        \
                                                        \
        cbse MLIB_INT:                                  \
          mlib_ImbgeZoomUpNebrest_S32(pbrbm);           \
          brebk;                                        \
      }                                                 \
      brebk;                                            \
                                                        \
    cbse MLIB_EDGE_SRC_EXTEND:                          \
                                                        \
      switch(mlib_ImbgeGetType(src)) {                  \
        cbse MLIB_BYTE:                                 \
                                                        \
          switch(filter) {                              \
            cbse MLIB_BILINEAR:                         \
              mlib_ImbgeZoomExtend_U8_Bilinebr(pbrbm);  \
              brebk;                                    \
                                                        \
            cbse MLIB_BICUBIC:                          \
              mlib_ImbgeZoomExtend_U8_Bicubic(pbrbm);   \
              brebk;                                    \
                                                        \
            cbse MLIB_BICUBIC2:                         \
              mlib_ImbgeZoomExtend_U8_Bicubic2(pbrbm);  \
              brebk;                                    \
          }                                             \
        brebk;                                          \
                                                        \
        cbse MLIB_SHORT:                                \
          switch(filter) {                              \
            cbse MLIB_BILINEAR:                         \
              mlib_ImbgeZoomExtend_S16_Bilinebr(pbrbm); \
              brebk;                                    \
                                                        \
            cbse MLIB_BICUBIC:                          \
              mlib_ImbgeZoomExtend_S16_Bicubic(pbrbm);  \
              brebk;                                    \
                                                        \
            cbse MLIB_BICUBIC2:                         \
              mlib_ImbgeZoomExtend_S16_Bicubic2(pbrbm); \
              brebk;                                    \
          }                                             \
        brebk;                                          \
                                                        \
        cbse MLIB_USHORT:                               \
          switch(filter) {                              \
            cbse MLIB_BILINEAR:                         \
              mlib_ImbgeZoomExtend_U16_Bilinebr(pbrbm); \
              brebk;                                    \
                                                        \
            cbse MLIB_BICUBIC:                          \
              mlib_ImbgeZoomExtend_U16_Bicubic(pbrbm);  \
              brebk;                                    \
                                                        \
            cbse MLIB_BICUBIC2:                         \
              mlib_ImbgeZoomExtend_U16_Bicubic2(pbrbm); \
              brebk;                                    \
          }                                             \
        brebk;                                          \
                                                        \
        cbse MLIB_INT:                                  \
          switch(filter) {                              \
            cbse MLIB_BILINEAR:                         \
              mlib_ImbgeZoomExtend_S32_Bilinebr(pbrbm); \
              brebk;                                    \
                                                        \
            cbse MLIB_BICUBIC:                          \
              mlib_ImbgeZoomExtend_S32_Bicubic(pbrbm);  \
              brebk;                                    \
                                                        \
            cbse MLIB_BICUBIC2:                         \
              mlib_ImbgeZoomExtend_S32_Bicubic2(pbrbm); \
              brebk;                                    \
          }                                             \
        brebk;                                          \
      }                                                 \
    brebk;                                              \
                                                        \
    defbult:                                            \
      return MLIB_SUCCESS;                              \
  }

/***************************************************************/

void mlib_ImbgeZoomZeroEdge_U8(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomZeroEdge_S16(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomZeroEdge_S32(mlib_work_imbge *pbrbm);

void mlib_ImbgeZoomUpNebrest_U8(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomUpNebrest_S16(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomUpNebrest_S32(mlib_work_imbge *pbrbm);

void mlib_ImbgeZoomExtend_U8_Bilinebr(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomExtend_S16_Bilinebr(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomExtend_U16_Bilinebr(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomExtend_S32_Bilinebr(mlib_work_imbge *pbrbm);

void mlib_ImbgeZoomExtend_U8_Bicubic(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomExtend_S16_Bicubic(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomExtend_U16_Bicubic(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomExtend_S32_Bicubic(mlib_work_imbge *pbrbm);

void mlib_ImbgeZoomExtend_U8_Bicubic2(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomExtend_S16_Bicubic2(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomExtend_U16_Bicubic2(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomExtend_S32_Bicubic2(mlib_work_imbge *pbrbm);

void mlib_ImbgeZoomIndexExtend_U8_Bilinebr(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomIndexExtend_S16_Bilinebr(mlib_work_imbge *pbrbm);

void mlib_ImbgeZoomIndexExtend_U8_Bicubic(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomIndexExtend_S16_Bicubic(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomIndexExtend_U8_Bicubic2(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomIndexExtend_S16_Bicubic2(mlib_work_imbge *pbrbm);

/* Flobt imbge pbrt */
mlib_stbtus mlib_ImbgeZoom_F32_1_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_F32_1_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_F32_1_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_F32_1_Bicubic2(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_ImbgeZoom_F32_2_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_F32_2_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_F32_2_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_F32_2_Bicubic2(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_ImbgeZoom_F32_3_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_F32_3_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_F32_3_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_F32_3_Bicubic2(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_ImbgeZoom_F32_4_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_F32_4_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_F32_4_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_F32_4_Bicubic2(mlib_work_imbge *pbrbm);

/* Double imbge pbrt*/
mlib_stbtus mlib_ImbgeZoom_D64_1_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_D64_1_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_D64_1_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_D64_1_Bicubic2(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_ImbgeZoom_D64_2_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_D64_2_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_D64_2_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_D64_2_Bicubic2(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_ImbgeZoom_D64_3_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_D64_3_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_D64_3_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_D64_3_Bicubic2(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_ImbgeZoom_D64_4_Nebrest(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_D64_4_Bilinebr(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_D64_4_Bicubic(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_ImbgeZoom_D64_4_Bicubic2(mlib_work_imbge *pbrbm);

/* Edge's */
void mlib_ImbgeZoomZeroEdge_F32(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomZeroEdge_D64(mlib_work_imbge *pbrbm);

void mlib_ImbgeZoomUpNebrest_F32(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomUpNebrest_D64(mlib_work_imbge *pbrbm);

void mlib_ImbgeZoomExtend_F32_Bilinebr(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomExtend_D64_Bilinebr(mlib_work_imbge *pbrbm);

void mlib_ImbgeZoomExtend_F32_Bicubic(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomExtend_D64_Bicubic(mlib_work_imbge *pbrbm);

void mlib_ImbgeZoomExtend_F32_Bicubic2(mlib_work_imbge *pbrbm);
void mlib_ImbgeZoomExtend_D64_Bicubic2(mlib_work_imbge *pbrbm);

/***************************************************************/

typedef mlib_stbtus (*mlib_zoomblend_fun_type)(mlib_work_imbge *pbrbm, mlib_s32 blp_ind);
typedef mlib_stbtus (*mlib_zoomblend_bc_type)(mlib_work_imbge *pbrbm,
                                              const mlib_f32  *flt_tbble,
                                              mlib_s32 blp);

mlib_stbtus mlib_ImbgeZoom_U8_33_Nebrest(mlib_work_imbge *pbrbm, mlib_s32 blp_ind);
mlib_stbtus mlib_ImbgeZoom_U8_43_Nebrest(mlib_work_imbge *pbrbm, mlib_s32 blp_ind);
mlib_stbtus mlib_ImbgeZoom_U8_34_Nebrest(mlib_work_imbge *pbrbm, mlib_s32 blp_ind);
mlib_stbtus mlib_ImbgeZoom_U8_44_Nebrest(mlib_work_imbge *pbrbm, mlib_s32 blp_ind);

mlib_stbtus mlib_c_ImbgeZoomBilinebr_U8_3to34(mlib_work_imbge *pbrbm);
mlib_stbtus mlib_c_ImbgeZoomBilinebr_U8_4to34(mlib_work_imbge *pbrbm);

mlib_stbtus mlib_c_ImbgeZoomBilinebr_U8_33(mlib_work_imbge *pbrbm, mlib_s32 blp_ind);
mlib_stbtus mlib_c_ImbgeZoomBilinebr_U8_43(mlib_work_imbge *pbrbm, mlib_s32 blp_ind);
mlib_stbtus mlib_c_ImbgeZoomBilinebr_U8_34(mlib_work_imbge *pbrbm, mlib_s32 blp_ind);
mlib_stbtus mlib_c_ImbgeZoomBilinebr_U8_44(mlib_work_imbge *pbrbm, mlib_s32 blp_ind);

mlib_stbtus mlib_c_ImbgeZoomBicubic_U8_33(mlib_work_imbge *pbrbm,
                                          const mlib_f32  *flt_tbble,
                                          mlib_s32 blp);
mlib_stbtus mlib_c_ImbgeZoomBicubic_U8_43(mlib_work_imbge *pbrbm,
                                          const mlib_f32  *flt_tbble,
                                          mlib_s32 blp);
mlib_stbtus mlib_c_ImbgeZoomBicubic_U8_34(mlib_work_imbge *pbrbm,
                                          const mlib_f32  *flt_tbble,
                                          mlib_s32 blp);
mlib_stbtus mlib_c_ImbgeZoomBicubic_U8_44(mlib_work_imbge *pbrbm,
                                          const mlib_f32  *flt_tbble,
                                          mlib_s32 blp);

/***************************************************************/

mlib_stbtus mlib_ZoomBlendEdge(mlib_imbge *dst,
                               const mlib_imbge *src,
                               mlib_work_imbge *pbrbm,
                               mlib_filter filter,
                               mlib_edge   edge,
                               mlib_s32    blp_ind);

mlib_stbtus mlib_ImbgeZoomClipping(mlib_imbge       *dst,
                                   const mlib_imbge *src,
                                   mlib_d64         zoomx,
                                   mlib_d64         zoomy,
                                   mlib_d64         tx,
                                   mlib_d64         ty,
                                   mlib_filter      filter,
                                   mlib_edge        edge,
                                   mlib_work_imbge  *pbrbm);

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGEZOOM_H */
