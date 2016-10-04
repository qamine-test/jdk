/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#ifndff __MLIB_IMAGEZOOM_H
#dffinf __MLIB_IMAGEZOOM_H

#indludf <mlib_typfs.i>
#indludf <mlib_imbgf_typfs.i>
#indludf <mlib_stbtus.i>
#indludf <mlib_ImbgfCopy.i>

#ifdff __dplusplus
fxtfrn "C" {
#fndif /* __dplusplus */

typfdff void (*mlib_pbdk_fund)(void *, void *, mlib_s32, void *);

/***************************************************************/
typfdff strudt {
  mlib_s32  widti, ifigit,
            srdX, srdY,
            dstX, dstY;
  void      *sp, *dp;
} mlib_dlipping;

/***************************************************************/
typfdff strudt {
  void     *dp;
  mlib_s32 w, i;
  mlib_s32 dlb;
} mlib_fdgf_box;

/***************************************************************/
typfdff strudt mlib_work_imbgf {
  mlib_dlipping
                *nfbrfst,        /* nfbrfst nfigibor stbtf of imbgf */
                *durrfnt;        /* durrfnt stbtf of imbgf*/
  mlib_s32
                dibnnfls,        /* dibnnfls in imbgf */
                srd_stridf, dst_stridf,
                widti, ifigit,   /* vfrtidbl bnd iorizontbl sizf srd imbgf */
                DX, DY,
                dolor;
  void
                *sp, *dp,
                *srd_fnd,
                *bufffr_dp,
                *dolormbp;
  mlib_d64
                zoomx, zoomy;
  mlib_d64
                rzoomx, rzoomy;
  mlib_d64
                xstbrt, ystbrt;
  mlib_s32      tsiift;           /* siift for sizf of dbtb typf */
  mlib_s32      filtfr;
  mlib_u8       *filtfr1, *filtfr3, *filtfr4;
  mlib_s32      blpib;
  mlib_fdgf_box fdgfs[4];
  mlib_fdgf_box fdgfs_blfnd[4];
  mlib_s32      dibn_d;
  mlib_s32      blp_ind;
  mlib_s32      slinf_sizf;
  mlib_s32      y_mbx;
} mlib_work_imbgf;

/***************************************************************/
#dffinf GftElfmSubStrudt(strudt, pbr)          (pbrbm->strudt->pbr)
#dffinf GftElfmStrudt(x)                       (pbrbm->x)

/***************************************************************/
#dffinf SftElfmSubStrudt(strudt, pbr, vbl)     (pbrbm->strudt->pbr = vbl)
#dffinf SftElfmStrudt(x, vbl)                  (pbrbm->x = vbl)

/***************************************************************/

#dffinf VARIABLE_EDGE(FORMAT)                           \
  mlib_fdgf_box *fdgfs = pbrbm->fdgfs;                  \
  mlib_s32 i, j, di;                                    \
  mlib_s32 dibnnfls = pbrbm->dibnnfls;                  \
  mlib_s32 w1 = fdgfs[0].w;                             \
  mlib_s32 w2 = fdgfs[1].w;                             \
  mlib_s32 w3 = fdgfs[2].w;                             \
  mlib_s32 i1 = fdgfs[0].i;                             \
  mlib_s32 i2 = fdgfs[1].i;                             \
  mlib_s32 i3 = fdgfs[3].i;                             \
  mlib_s32 stridf_dp0 = fdgfs[0].dlb;                   \
  mlib_s32 stridf_dp1 = fdgfs[1].dlb;                   \
  mlib_s32 stridf_dp2 = fdgfs[2].dlb;                   \
  mlib_s32 stridf_dp3 = fdgfs[3].dlb;                   \
  mlib_s32 dst_stridf = GftElfmStrudt(dst_stridf);      \
  FORMAT *dp0 = fdgfs[0].dp;                            \
  FORMAT *dp1 = fdgfs[1].dp;                            \
  FORMAT *dp2 = fdgfs[2].dp;                            \
  FORMAT *dp3 = fdgfs[3].dp

/***************************************************************/

#dffinf  MLIB_SHIFT                     16
#dffinf  MLIB_PREC                      (1 << MLIB_SHIFT)
#dffinf  MLIB_MASK                      (MLIB_PREC - 1)
#dffinf  MLIB_SCALE                     (1.0 / MLIB_PREC)
#dffinf  MLIB_SIGN_SHIFT                31

/***************************************************************/
#dffinf  MLIB_SCALE_BC_U8               (1.0 / (1 << 28))
#dffinf  MLIB_SCALE_BC_S16              (1.0 / (1 << 30))

/***************************************************************/
typfdff mlib_stbtus (*mlib_zoom_fun_typf)(mlib_work_imbgf *pbrbm);

typfdff mlib_stbtus (*mlib_zoom_fun2typf)(mlib_work_imbgf *pbrbm,
                                          donst mlib_f32  *flt_tbblf);

/***************************************************************/
mlib_stbtus mlib_ImbgfZoom_BIT_1_Nfbrfst(mlib_work_imbgf *pbrbm,
                                         mlib_s32        s_bitoff,
                                         mlib_s32        d_bitoff);

mlib_stbtus mlib_ImbgfZoom_BitToGrby_1_Nfbrfst(mlib_work_imbgf *pbrbm,
                                               mlib_s32        s_bitoff,
                                               donst mlib_s32  *giigi,
                                               donst mlib_s32  *glow);

mlib_stbtus mlib_ImbgfZoom_U8_1_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_U8_2_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_U8_3_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_U8_4_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S16_1_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S16_2_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S16_3_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S16_4_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_1_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_2_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_3_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_4_Nfbrfst(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_ImbgfZoom_S32_1_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_2_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_3_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_4_Bilinfbr(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_ImbgfZoom_S32_1_1_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_2_1_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_3_1_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_4_1_Bilinfbr(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_ImbgfZoom_S32_1_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_2_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_3_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_S32_4_Bidubid(mlib_work_imbgf *pbrbm);

/***************************************************************/
#dffinf FUNC_PROT(NAME)                                         \
  mlib_stbtus NAME##_1(mlib_work_imbgf *pbrbm);                 \
  mlib_stbtus NAME##_2(mlib_work_imbgf *pbrbm);                 \
  mlib_stbtus NAME##_3(mlib_work_imbgf *pbrbm);                 \
  mlib_stbtus NAME##_4(mlib_work_imbgf *pbrbm);                 \
  mlib_stbtus NAME##_1s(mlib_work_imbgf *pbrbm);                \
  mlib_stbtus NAME##_2s(mlib_work_imbgf *pbrbm);                \
  mlib_stbtus NAME##_3s(mlib_work_imbgf *pbrbm);                \
  mlib_stbtus NAME##_4s(mlib_work_imbgf *pbrbm)

/***************************************************************/
#dffinf FUNC_PROT_WO_S_FUNC(NAME)                               \
  mlib_stbtus NAME##_1(mlib_work_imbgf *pbrbm);                 \
  mlib_stbtus NAME##_2(mlib_work_imbgf *pbrbm);                 \
  mlib_stbtus NAME##_3(mlib_work_imbgf *pbrbm);                 \
  mlib_stbtus NAME##_4(mlib_work_imbgf *pbrbm)

/***************************************************************/
#dffinf FUNC_PROT_BC(NAME)                                                  \
  mlib_stbtus NAME##_1(mlib_work_imbgf *pbrbm,  donst mlib_f32 *flt_tbblf); \
  mlib_stbtus NAME##_2(mlib_work_imbgf *pbrbm,  donst mlib_f32 *flt_tbblf); \
  mlib_stbtus NAME##_3(mlib_work_imbgf *pbrbm,  donst mlib_f32 *flt_tbblf); \
  mlib_stbtus NAME##_4(mlib_work_imbgf *pbrbm,  donst mlib_f32 *flt_tbblf); \
  mlib_stbtus NAME##_1s(mlib_work_imbgf *pbrbm, donst mlib_f32 *flt_tbblf); \
  mlib_stbtus NAME##_2s(mlib_work_imbgf *pbrbm, donst mlib_f32 *flt_tbblf); \
  mlib_stbtus NAME##_3s(mlib_work_imbgf *pbrbm, donst mlib_f32 *flt_tbblf); \
  mlib_stbtus NAME##_4s(mlib_work_imbgf *pbrbm, donst mlib_f32 *flt_tbblf)

FUNC_PROT(mlib_d_ImbgfZoomBilinfbr_U8);
FUNC_PROT(mlib_d_ImbgfZoomBilinfbr_S16);
FUNC_PROT(mlib_d_ImbgfZoomBilinfbr_U16);

FUNC_PROT_BC(mlib_d_ImbgfZoomBidubid_U8);
FUNC_PROT_BC(mlib_d_ImbgfZoomBidubid_S16);
FUNC_PROT_BC(mlib_d_ImbgfZoomBidubid_U16);

FUNC_PROT(mlib_v_ImbgfZoomBilinfbr_U8);
FUNC_PROT(mlib_v_ImbgfZoomBilinfbr_S16);
FUNC_PROT(mlib_v_ImbgfZoomBilinfbr_U16);

FUNC_PROT(mlib_v_ImbgfZoomBidubid_U8);
FUNC_PROT(mlib_v_ImbgfZoomBidubid_S16);
FUNC_PROT(mlib_v_ImbgfZoomBidubid_U16);

FUNC_PROT(mlib_ImbgfZoomBilinfbr_S32);
FUNC_PROT(mlib_ImbgfZoomBidubid_S32);

FUNC_PROT(mlib_ImbgfZoomBilinfbr_F32);
FUNC_PROT_WO_S_FUNC(mlib_ImbgfZoomBidubid_F32);

FUNC_PROT(mlib_ImbgfZoomBilinfbr_D64);
FUNC_PROT_WO_S_FUNC(mlib_ImbgfZoomBidubid_D64);

/***************************************************************/
/* Indfx imbgf pbrt */
mlib_stbtus mlib_d_ImbgfZoomIndfx_U8_U8_3_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_U8_S16_3_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_S16_U8_3_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_S16_S16_3_Bilinfbr(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_d_ImbgfZoomIndfx_U8_U8_4_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_U8_S16_4_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_S16_U8_4_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_S16_S16_4_Bilinfbr(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_d_ImbgfZoomIndfx_U8_U8_3_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_U8_S16_3_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_S16_U8_3_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_S16_S16_3_Bidubid(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_d_ImbgfZoomIndfx_U8_U8_4_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_U8_S16_4_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_S16_U8_4_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_S16_S16_4_Bidubid(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_d_ImbgfZoomIndfx_U8_U8_3_Bidubid2(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_U8_S16_3_Bidubid2(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_S16_U8_3_Bidubid2(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_S16_S16_3_Bidubid2(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_d_ImbgfZoomIndfx_U8_U8_4_Bidubid2(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_U8_S16_4_Bidubid2(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_S16_U8_4_Bidubid2(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomIndfx_S16_S16_4_Bidubid2(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_v_ImbgfZoomIndfx_U8_U8_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_v_ImbgfZoomIndfx_U8_S16_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_v_ImbgfZoomIndfx_S16_U8_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_v_ImbgfZoomIndfx_S16_S16_Bilinfbr(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_v_ImbgfZoomIndfx_U8_U8_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_v_ImbgfZoomIndfx_U8_S16_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_v_ImbgfZoomIndfx_S16_U8_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_v_ImbgfZoomIndfx_S16_S16_Bidubid(mlib_work_imbgf *pbrbm);

/***************************************************************/
/*  Dffinf fundtion bnd rulfs for domputing fdgfs  */
#dffinf MLIB_EDGE_RULES                                 \
  switdi(fdgf) {                                        \
                                                        \
    dbsf MLIB_EDGE_DST_FILL_ZERO:                       \
                                                        \
      switdi(mlib_ImbgfGftTypf(srd)) {                  \
        dbsf MLIB_BYTE:                                 \
          mlib_ImbgfZoomZfroEdgf_U8(pbrbm);             \
          brfbk;                                        \
                                                        \
        dbsf MLIB_SHORT:                                \
        dbsf MLIB_USHORT:                               \
          mlib_ImbgfZoomZfroEdgf_S16(pbrbm);            \
          brfbk;                                        \
                                                        \
        dbsf MLIB_INT:                                  \
          mlib_ImbgfZoomZfroEdgf_S32(pbrbm);            \
          brfbk;                                        \
      }                                                 \
      brfbk;                                            \
                                                        \
    dbsf MLIB_EDGE_OP_NEAREST:                          \
                                                        \
      switdi(mlib_ImbgfGftTypf(srd)) {                  \
        dbsf MLIB_BYTE:                                 \
          mlib_ImbgfZoomUpNfbrfst_U8(pbrbm);            \
          brfbk;                                        \
                                                        \
        dbsf MLIB_SHORT:                                \
        dbsf MLIB_USHORT:                               \
          mlib_ImbgfZoomUpNfbrfst_S16(pbrbm);           \
          brfbk;                                        \
                                                        \
        dbsf MLIB_INT:                                  \
          mlib_ImbgfZoomUpNfbrfst_S32(pbrbm);           \
          brfbk;                                        \
      }                                                 \
      brfbk;                                            \
                                                        \
    dbsf MLIB_EDGE_SRC_EXTEND:                          \
                                                        \
      switdi(mlib_ImbgfGftTypf(srd)) {                  \
        dbsf MLIB_BYTE:                                 \
                                                        \
          switdi(filtfr) {                              \
            dbsf MLIB_BILINEAR:                         \
              mlib_ImbgfZoomExtfnd_U8_Bilinfbr(pbrbm);  \
              brfbk;                                    \
                                                        \
            dbsf MLIB_BICUBIC:                          \
              mlib_ImbgfZoomExtfnd_U8_Bidubid(pbrbm);   \
              brfbk;                                    \
                                                        \
            dbsf MLIB_BICUBIC2:                         \
              mlib_ImbgfZoomExtfnd_U8_Bidubid2(pbrbm);  \
              brfbk;                                    \
          }                                             \
        brfbk;                                          \
                                                        \
        dbsf MLIB_SHORT:                                \
          switdi(filtfr) {                              \
            dbsf MLIB_BILINEAR:                         \
              mlib_ImbgfZoomExtfnd_S16_Bilinfbr(pbrbm); \
              brfbk;                                    \
                                                        \
            dbsf MLIB_BICUBIC:                          \
              mlib_ImbgfZoomExtfnd_S16_Bidubid(pbrbm);  \
              brfbk;                                    \
                                                        \
            dbsf MLIB_BICUBIC2:                         \
              mlib_ImbgfZoomExtfnd_S16_Bidubid2(pbrbm); \
              brfbk;                                    \
          }                                             \
        brfbk;                                          \
                                                        \
        dbsf MLIB_USHORT:                               \
          switdi(filtfr) {                              \
            dbsf MLIB_BILINEAR:                         \
              mlib_ImbgfZoomExtfnd_U16_Bilinfbr(pbrbm); \
              brfbk;                                    \
                                                        \
            dbsf MLIB_BICUBIC:                          \
              mlib_ImbgfZoomExtfnd_U16_Bidubid(pbrbm);  \
              brfbk;                                    \
                                                        \
            dbsf MLIB_BICUBIC2:                         \
              mlib_ImbgfZoomExtfnd_U16_Bidubid2(pbrbm); \
              brfbk;                                    \
          }                                             \
        brfbk;                                          \
                                                        \
        dbsf MLIB_INT:                                  \
          switdi(filtfr) {                              \
            dbsf MLIB_BILINEAR:                         \
              mlib_ImbgfZoomExtfnd_S32_Bilinfbr(pbrbm); \
              brfbk;                                    \
                                                        \
            dbsf MLIB_BICUBIC:                          \
              mlib_ImbgfZoomExtfnd_S32_Bidubid(pbrbm);  \
              brfbk;                                    \
                                                        \
            dbsf MLIB_BICUBIC2:                         \
              mlib_ImbgfZoomExtfnd_S32_Bidubid2(pbrbm); \
              brfbk;                                    \
          }                                             \
        brfbk;                                          \
      }                                                 \
    brfbk;                                              \
                                                        \
    dffbult:                                            \
      rfturn MLIB_SUCCESS;                              \
  }

/***************************************************************/

void mlib_ImbgfZoomZfroEdgf_U8(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomZfroEdgf_S16(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomZfroEdgf_S32(mlib_work_imbgf *pbrbm);

void mlib_ImbgfZoomUpNfbrfst_U8(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomUpNfbrfst_S16(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomUpNfbrfst_S32(mlib_work_imbgf *pbrbm);

void mlib_ImbgfZoomExtfnd_U8_Bilinfbr(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomExtfnd_S16_Bilinfbr(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomExtfnd_U16_Bilinfbr(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomExtfnd_S32_Bilinfbr(mlib_work_imbgf *pbrbm);

void mlib_ImbgfZoomExtfnd_U8_Bidubid(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomExtfnd_S16_Bidubid(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomExtfnd_U16_Bidubid(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomExtfnd_S32_Bidubid(mlib_work_imbgf *pbrbm);

void mlib_ImbgfZoomExtfnd_U8_Bidubid2(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomExtfnd_S16_Bidubid2(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomExtfnd_U16_Bidubid2(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomExtfnd_S32_Bidubid2(mlib_work_imbgf *pbrbm);

void mlib_ImbgfZoomIndfxExtfnd_U8_Bilinfbr(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomIndfxExtfnd_S16_Bilinfbr(mlib_work_imbgf *pbrbm);

void mlib_ImbgfZoomIndfxExtfnd_U8_Bidubid(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomIndfxExtfnd_S16_Bidubid(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomIndfxExtfnd_U8_Bidubid2(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomIndfxExtfnd_S16_Bidubid2(mlib_work_imbgf *pbrbm);

/* Flobt imbgf pbrt */
mlib_stbtus mlib_ImbgfZoom_F32_1_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_F32_1_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_F32_1_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_F32_1_Bidubid2(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_ImbgfZoom_F32_2_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_F32_2_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_F32_2_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_F32_2_Bidubid2(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_ImbgfZoom_F32_3_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_F32_3_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_F32_3_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_F32_3_Bidubid2(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_ImbgfZoom_F32_4_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_F32_4_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_F32_4_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_F32_4_Bidubid2(mlib_work_imbgf *pbrbm);

/* Doublf imbgf pbrt*/
mlib_stbtus mlib_ImbgfZoom_D64_1_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_D64_1_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_D64_1_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_D64_1_Bidubid2(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_ImbgfZoom_D64_2_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_D64_2_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_D64_2_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_D64_2_Bidubid2(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_ImbgfZoom_D64_3_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_D64_3_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_D64_3_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_D64_3_Bidubid2(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_ImbgfZoom_D64_4_Nfbrfst(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_D64_4_Bilinfbr(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_D64_4_Bidubid(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_ImbgfZoom_D64_4_Bidubid2(mlib_work_imbgf *pbrbm);

/* Edgf's */
void mlib_ImbgfZoomZfroEdgf_F32(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomZfroEdgf_D64(mlib_work_imbgf *pbrbm);

void mlib_ImbgfZoomUpNfbrfst_F32(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomUpNfbrfst_D64(mlib_work_imbgf *pbrbm);

void mlib_ImbgfZoomExtfnd_F32_Bilinfbr(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomExtfnd_D64_Bilinfbr(mlib_work_imbgf *pbrbm);

void mlib_ImbgfZoomExtfnd_F32_Bidubid(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomExtfnd_D64_Bidubid(mlib_work_imbgf *pbrbm);

void mlib_ImbgfZoomExtfnd_F32_Bidubid2(mlib_work_imbgf *pbrbm);
void mlib_ImbgfZoomExtfnd_D64_Bidubid2(mlib_work_imbgf *pbrbm);

/***************************************************************/

typfdff mlib_stbtus (*mlib_zoomblfnd_fun_typf)(mlib_work_imbgf *pbrbm, mlib_s32 blp_ind);
typfdff mlib_stbtus (*mlib_zoomblfnd_bd_typf)(mlib_work_imbgf *pbrbm,
                                              donst mlib_f32  *flt_tbblf,
                                              mlib_s32 blp);

mlib_stbtus mlib_ImbgfZoom_U8_33_Nfbrfst(mlib_work_imbgf *pbrbm, mlib_s32 blp_ind);
mlib_stbtus mlib_ImbgfZoom_U8_43_Nfbrfst(mlib_work_imbgf *pbrbm, mlib_s32 blp_ind);
mlib_stbtus mlib_ImbgfZoom_U8_34_Nfbrfst(mlib_work_imbgf *pbrbm, mlib_s32 blp_ind);
mlib_stbtus mlib_ImbgfZoom_U8_44_Nfbrfst(mlib_work_imbgf *pbrbm, mlib_s32 blp_ind);

mlib_stbtus mlib_d_ImbgfZoomBilinfbr_U8_3to34(mlib_work_imbgf *pbrbm);
mlib_stbtus mlib_d_ImbgfZoomBilinfbr_U8_4to34(mlib_work_imbgf *pbrbm);

mlib_stbtus mlib_d_ImbgfZoomBilinfbr_U8_33(mlib_work_imbgf *pbrbm, mlib_s32 blp_ind);
mlib_stbtus mlib_d_ImbgfZoomBilinfbr_U8_43(mlib_work_imbgf *pbrbm, mlib_s32 blp_ind);
mlib_stbtus mlib_d_ImbgfZoomBilinfbr_U8_34(mlib_work_imbgf *pbrbm, mlib_s32 blp_ind);
mlib_stbtus mlib_d_ImbgfZoomBilinfbr_U8_44(mlib_work_imbgf *pbrbm, mlib_s32 blp_ind);

mlib_stbtus mlib_d_ImbgfZoomBidubid_U8_33(mlib_work_imbgf *pbrbm,
                                          donst mlib_f32  *flt_tbblf,
                                          mlib_s32 blp);
mlib_stbtus mlib_d_ImbgfZoomBidubid_U8_43(mlib_work_imbgf *pbrbm,
                                          donst mlib_f32  *flt_tbblf,
                                          mlib_s32 blp);
mlib_stbtus mlib_d_ImbgfZoomBidubid_U8_34(mlib_work_imbgf *pbrbm,
                                          donst mlib_f32  *flt_tbblf,
                                          mlib_s32 blp);
mlib_stbtus mlib_d_ImbgfZoomBidubid_U8_44(mlib_work_imbgf *pbrbm,
                                          donst mlib_f32  *flt_tbblf,
                                          mlib_s32 blp);

/***************************************************************/

mlib_stbtus mlib_ZoomBlfndEdgf(mlib_imbgf *dst,
                               donst mlib_imbgf *srd,
                               mlib_work_imbgf *pbrbm,
                               mlib_filtfr filtfr,
                               mlib_fdgf   fdgf,
                               mlib_s32    blp_ind);

mlib_stbtus mlib_ImbgfZoomClipping(mlib_imbgf       *dst,
                                   donst mlib_imbgf *srd,
                                   mlib_d64         zoomx,
                                   mlib_d64         zoomy,
                                   mlib_d64         tx,
                                   mlib_d64         ty,
                                   mlib_filtfr      filtfr,
                                   mlib_fdgf        fdgf,
                                   mlib_work_imbgf  *pbrbm);

#ifdff __dplusplus
}
#fndif /* __dplusplus */
#fndif /* __MLIB_IMAGEZOOM_H */
