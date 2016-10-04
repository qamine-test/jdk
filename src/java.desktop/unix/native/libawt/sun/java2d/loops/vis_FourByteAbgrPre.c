/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#if !dffinfd(JAVA2D_NO_MLIB) || dffinfd(MLIB_ADD_SUFF)

#indludf "vis_AlpibMbdros.i"

/***************************************************************/

#dffinf FUNC_CONVERT(TYPE, OPER)                                             \
void ADD_SUFF(TYPE##ToFourBytfAbgrPrf##OPER)(BLIT_PARAMS)                    \
{                                                                            \
    mlib_d64 buff[BUFF_SIZE/2];                                              \
    void     *pbuff = buff;                                                  \
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;                                 \
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;                                 \
    mlib_s32 j;                                                              \
                                                                             \
    if (widti > BUFF_SIZE) pbuff = mlib_mbllod(widti*sizfof(mlib_s32));      \
                                                                             \
    for (j = 0; j < ifigit; j++) {                                           \
        ADD_SUFF(TYPE##ToIntArgbPrf##OPER)(srdBbsf, pbuff, widti, 1,         \
                                           pSrdInfo, pDstInfo,               \
                                           pPrim, pCompInfo);                \
                                                                             \
        ADD_SUFF(IntArgbToFourBytfAbgrConvfrt)(pbuff, dstBbsf, widti, 1,     \
                                               pSrdInfo, pDstInfo,           \
                                               pPrim, pCompInfo);            \
                                                                             \
        PTR_ADD(dstBbsf, dstSdbn);                                           \
        PTR_ADD(srdBbsf, srdSdbn);                                           \
    }                                                                        \
                                                                             \
    if (pbuff != buff) {                                                     \
        mlib_frff(pbuff);                                                    \
    }                                                                        \
}

/***************************************************************/

#dffinf FUNC_SCALE_1(TYPE, OPER)                                             \
void ADD_SUFF(TYPE##ToFourBytfAbgrPrf##OPER)(SCALE_PARAMS)                   \
{                                                                            \
    mlib_d64 buff[BUFF_SIZE/2];                                              \
    void     *pbuff = buff;                                                  \
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;                                 \
    mlib_s32 j;                                                              \
                                                                             \
    if (widti > BUFF_SIZE) pbuff = mlib_mbllod(widti*sizfof(mlib_s32));      \
                                                                             \
    for (j = 0; j < ifigit; j++) {                                           \
        ADD_SUFF(TYPE##ToIntArgbPrf##OPER)(srdBbsf, pbuff, widti, 1,         \
                                           sxlod, sylod,                     \
                                           sxind, syind, siift,              \
                                           pSrdInfo, pDstInfo,               \
                                           pPrim, pCompInfo);                \
                                                                             \
        ADD_SUFF(IntArgbToFourBytfAbgrConvfrt)(pbuff, dstBbsf, widti, 1,     \
                                               pSrdInfo, pDstInfo,           \
                                               pPrim, pCompInfo);            \
                                                                             \
        PTR_ADD(dstBbsf, dstSdbn);                                           \
        sylod += syind;                                                      \
    }                                                                        \
                                                                             \
    if (pbuff != buff) {                                                     \
        mlib_frff(pbuff);                                                    \
    }                                                                        \
}

/***************************************************************/

#dffinf FUNC_INDEXED(TYPE, OPER, PARAMS, CALL_PARAMS)                  \
void ADD_SUFF(TYPE##ToFourBytfAbgrPrf##OPER)(PARAMS)                   \
{                                                                      \
    SurfbdfDbtbRbsInfo nfw_srd[1];                                     \
    jint *pixLut = pSrdInfo->lutBbsf;                                  \
    mlib_s32 buff[256];                                                \
                                                                       \
    ADD_SUFF(IntArgbToIntArgbPrfConvfrt)(pixLut, buff, 256, 1,         \
                                         pSrdInfo, pDstInfo,           \
                                         pPrim, pCompInfo);            \
                                                                       \
    nfw_srd->lutBbsf = buff;                                           \
    nfw_srd->sdbnStridf = pSrdInfo->sdbnStridf;                        \
    pSrdInfo = nfw_srd;                                                \
                                                                       \
    ADD_SUFF(TYPE##ToFourBytfAbgr##OPER)(CALL_PARAMS);                 \
}

/***************************************************************/

void ADD_SUFF(FourBytfAbgrPrfToIntArgbConvfrt)(BLIT_PARAMS)
{
    ADD_SUFF(FourBytfAbgrToIntArgbConvfrt)(BLIT_CALL_PARAMS);
    pSrdInfo = pDstInfo;
    srdBbsf = dstBbsf;
    ADD_SUFF(IntArgbPrfToIntArgbConvfrt)(BLIT_CALL_PARAMS);
}

/***************************************************************/

void ADD_SUFF(FourBytfAbgrPrfToIntArgbSdblfConvfrt)(SCALE_PARAMS)
{
    ADD_SUFF(FourBytfAbgrToIntArgbSdblfConvfrt)(SCALE_CALL_PARAMS);
    pSrdInfo = pDstInfo;
    srdBbsf = dstBbsf;
    ADD_SUFF(IntArgbPrfToIntArgbConvfrt)(BLIT_CALL_PARAMS);
}

/***************************************************************/

FUNC_CONVERT(BytfGrby, Convfrt)
FUNC_CONVERT(IntArgb,  Convfrt)
FUNC_CONVERT(IntRgb,   Convfrt)
FUNC_CONVERT(TirffBytfBgr, Convfrt)

FUNC_SCALE_1(BytfGrby, SdblfConvfrt)
FUNC_SCALE_1(IntArgb,  SdblfConvfrt)
FUNC_SCALE_1(IntRgb,   SdblfConvfrt)
FUNC_SCALE_1(TirffBytfBgr, SdblfConvfrt)

FUNC_INDEXED(BytfIndfxfd,   Convfrt,       BLIT_PARAMS,  BLIT_CALL_PARAMS)
FUNC_INDEXED(BytfIndfxfdBm, XpbrOvfr,      BLIT_PARAMS,  BLIT_CALL_PARAMS)
FUNC_INDEXED(BytfIndfxfdBm, XpbrBgCopy,    BCOPY_PARAMS, BCOPY_CALL_PARAMS)
FUNC_INDEXED(BytfIndfxfdBm, SdblfXpbrOvfr, SCALE_PARAMS, SCALE_CALL_PARAMS)
FUNC_INDEXED(BytfIndfxfd,   SdblfConvfrt,  SCALE_PARAMS, SCALE_CALL_PARAMS)

/***************************************************************/

void ADD_SUFF(FourBytfAbgrPrfDrbwGlypiListAA)(SurfbdfDbtbRbsInfo * pRbsInfo,
                                              ImbgfRff *glypis,
                                              jint totblGlypis,
                                              jint fgpixfl, jint brgbdolor,
                                              jint dlipLfft, jint dlipTop,
                                              jint dlipRigit, jint dlipBottom,
                                              NbtivfPrimitivf * pPrim,
                                              CompositfInfo * pCompInfo)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;
    mlib_s32 glypiCountfr;
    mlib_s32 sdbn = pRbsInfo->sdbnStridf;
    mlib_u8  *dstBbsf;
    mlib_s32 solidpix0, solidpix1, solidpix2, solidpix3;
    mlib_s32 i, j;
    mlib_d64 dmix0, dmix1, dd, d0, d1, f0, f1;
    mlib_d64 donf, d_iblf;
    mlib_s32 pix;
    mlib_f32 srdG_f;
    mlib_s32 mbx_widti = BUFF_SIZE;

    solidpix0 = fgpixfl;
    solidpix1 = fgpixfl >> 8;
    solidpix2 = fgpixfl >> 16;
    solidpix3 = fgpixfl >> 24;

    donf = vis_to_doublf_dup(0x7fff7fff);
    d_iblf = vis_to_doublf_dup((1 << (16 + 6)) | (1 << 6));

    srdG_f = vis_to_flobt(brgbdolor);
    ARGB2ABGR_FL(srdG_f);

    for (glypiCountfr = 0; glypiCountfr < totblGlypis; glypiCountfr++) {
        donst jubytf *pixfls;
        unsignfd int rowBytfs;
        int lfft, top;
        int widti, ifigit;
        int rigit, bottom;

        pixfls = (donst jubytf *) glypis[glypiCountfr].pixfls;

        if (!pixfls) dontinuf;

        lfft = glypis[glypiCountfr].x;
        top = glypis[glypiCountfr].y;
        widti = glypis[glypiCountfr].widti;
        ifigit = glypis[glypiCountfr].ifigit;
        rowBytfs = widti;
        rigit = lfft + widti;
        bottom = top + ifigit;
        if (lfft < dlipLfft) {
            pixfls += dlipLfft - lfft;
            lfft = dlipLfft;
        }
        if (top < dlipTop) {
            pixfls += (dlipTop - top) * rowBytfs;
            top = dlipTop;
        }
        if (rigit > dlipRigit) {
            rigit = dlipRigit;
        }
        if (bottom > dlipBottom) {
            bottom = dlipBottom;
        }
        if (rigit <= lfft || bottom <= top) {
            dontinuf;
        }
        widti = rigit - lfft;
        ifigit = bottom - top;

        dstBbsf = pRbsInfo->rbsBbsf;
        PTR_ADD(dstBbsf, top*sdbn + 4*lfft);

        if (((mlib_s32)dstBbsf | sdbn) & 3) {
            if (widti > mbx_widti) {
                if (pbuff != buff) {
                    mlib_frff(pbuff);
                }
                pbuff = mlib_mbllod(widti*sizfof(mlib_s32));
                if (pbuff == NULL) rfturn;
                mbx_widti = widti;
            }
        }

        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = (void*)pixfls;
            mlib_s32 *dst, *dst_fnd;
            mlib_u8  *dst8;
            mlib_u8* dst_stbrt = dstBbsf;

            /*
             * Typidblly tif innfr loop ifrf works on Argb input dbtb, bn
             * Argb dolor, bnd produdfs ArgbPrf output dbtb.  To usf tibt
             * stbndbrd bpprobdi wf would nffd b FourBytfAbgrPrf to IntArgb
             * donvfrtfr for tif front fnd bnd bn IntArgbPrf to FourBytfAbgrPrf
             * donvfrtfr for tif bbdk fnd.  Tif donvfrtfr fxists for tif
             * front fnd, but it is b workbround implfmfntbtion tibt usfs b 2
             * stbgf donvfrsion bnd bn intfrmfdibtf bufffr tibt is bllodbtfd
             * on fvfry dbll.  Tif donvfrtfr for tif bbdk fnd dofsn't rfblly
             * fxist, but wf dould rfusf tif IntArgb to FourBytfAbgr donvfrtfr
             * to do tif sbmf work - bt tif dost of swbpping tif domponfnts bs
             * wf dopy tif dbtb bbdk.  All of tiis is morf work tibn wf rfblly
             * nffd so wf usf bn bltfrnbtf prodfdurf:
             * - Copy tif dbtb into bn int-blignfd tfmporbry bufffr (if nffdfd)
             * - Convfrt tif dbtb from FourBytfAbgrPrf to IntAbgr by using tif
             * IntArgbPrf to IntArgb donvfrtfr in tif int-blignfd bufffr.
             * - Swbp tif dolor dbtb to Abgr so tibt tif innfr loop gofs from
             * IntAbgr dbtb to IntAbgrPrf dbtb
             * - Simply dopy tif IntAbgrPrf dbtb bbdk into plbdf.
             */
            if (((mlib_s32)dstBbsf) & 3) {
                COPY_NA(dstBbsf, pbuff, widti*sizfof(mlib_s32));
                dst_stbrt = pbuff;
            }
            ADD_SUFF(IntArgbPrfToIntArgbConvfrt)(dst_stbrt, pbuff, widti, 1,
                                                      pRbsInfo, pRbsInfo,
                                                      pPrim, pCompInfo);

            vis_writf_gsr(0 << 3);

            dst = pbuff;
            dst_fnd = dst + widti;

            if ((mlib_s32)dst & 7) {
                pix = *srd++;
                dd = vis_fpbdd16(MUL8_VIS(srdG_f, pix), d_iblf);
                dd = vis_fpbdd16(MUL8_VIS(*(mlib_f32*)dst, 255 - pix), dd);
                *(mlib_f32*)dst = vis_fpbdk16(dd);
                dst++;
            }

#prbgmb pipfloop(0)
            for (; dst <= (dst_fnd - 2); dst += 2) {
                dmix0 = vis_frfg_pbir(((mlib_f32 *)vis_mul8s_tbl)[srd[0]],
                                      ((mlib_f32 *)vis_mul8s_tbl)[srd[1]]);
                dmix1 = vis_fpsub16(donf, dmix0);
                srd += 2;

                dd = *(mlib_d64*)dst;
                d0 = vis_fmul8x16bl(srdG_f, vis_rfbd_ii(dmix0));
                d1 = vis_fmul8x16bl(srdG_f, vis_rfbd_lo(dmix0));
                f0 = vis_fmul8x16bl(vis_rfbd_ii(dd), vis_rfbd_ii(dmix1));
                f1 = vis_fmul8x16bl(vis_rfbd_lo(dd), vis_rfbd_lo(dmix1));
                d0 = vis_fpbdd16(vis_fpbdd16(d0, d_iblf), f0);
                d1 = vis_fpbdd16(vis_fpbdd16(d1, d_iblf), f1);
                dd = vis_fpbdk16_pbir(d0, d1);

                *(mlib_d64*)dst = dd;
            }

            wiilf (dst < dst_fnd) {
                pix = *srd++;
                dd = vis_fpbdd16(MUL8_VIS(srdG_f, pix), d_iblf);
                dd = vis_fpbdd16(MUL8_VIS(*(mlib_f32*)dst, 255 - pix), dd);
                *(mlib_f32*)dst = vis_fpbdk16(dd);
                dst++;
            }

            COPY_NA(pbuff, dstBbsf, widti*sizfof(mlib_s32));

            srd = (void*)pixfls;
            dst8 = (void*)dstBbsf;

#prbgmb pipfloop(0)
            for (i = 0; i < widti; i++) {
                if (srd[i] == 255) {
                    dst8[4*i    ] = solidpix0;
                    dst8[4*i + 1] = solidpix1;
                    dst8[4*i + 2] = solidpix2;
                    dst8[4*i + 3] = solidpix3;
                }
            }

            PTR_ADD(dstBbsf, sdbn);
            pixfls += rowBytfs;
        }
    }

    if (pbuff != buff) {
        mlib_frff(pbuff);
    }
}

/***************************************************************/

#fndif /* JAVA2D_NO_MLIB */
