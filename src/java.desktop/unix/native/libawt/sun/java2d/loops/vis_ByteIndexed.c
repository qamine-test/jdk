/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <vis_proto.i>
#indludf "jbvb2d_Mlib.i"
#indludf "vis_AlpibMbdros.i"

/***************************************************************/

donst mlib_u8 vis_sbt_si3_tbl[128 + 256 + 128] = {
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   0,  0,  0,  0,  0,  0,  0,  0,
   1,  1,  1,  1,  1,  1,  1,  1,
   2,  2,  2,  2,  2,  2,  2,  2,
   3,  3,  3,  3,  3,  3,  3,  3,
   4,  4,  4,  4,  4,  4,  4,  4,
   5,  5,  5,  5,  5,  5,  5,  5,
   6,  6,  6,  6,  6,  6,  6,  6,
   7,  7,  7,  7,  7,  7,  7,  7,
   8,  8,  8,  8,  8,  8,  8,  8,
   9,  9,  9,  9,  9,  9,  9,  9,
    10, 10, 10, 10, 10, 10, 10, 10,
    11, 11, 11, 11, 11, 11, 11, 11,
    12, 12, 12, 12, 12, 12, 12, 12,
    13, 13, 13, 13, 13, 13, 13, 13,
    14, 14, 14, 14, 14, 14, 14, 14,
    15, 15, 15, 15, 15, 15, 15, 15,
    16, 16, 16, 16, 16, 16, 16, 16,
    17, 17, 17, 17, 17, 17, 17, 17,
    18, 18, 18, 18, 18, 18, 18, 18,
    19, 19, 19, 19, 19, 19, 19, 19,
    20, 20, 20, 20, 20, 20, 20, 20,
    21, 21, 21, 21, 21, 21, 21, 21,
    22, 22, 22, 22, 22, 22, 22, 22,
    23, 23, 23, 23, 23, 23, 23, 23,
    24, 24, 24, 24, 24, 24, 24, 24,
    25, 25, 25, 25, 25, 25, 25, 25,
    26, 26, 26, 26, 26, 26, 26, 26,
    27, 27, 27, 27, 27, 27, 27, 27,
    28, 28, 28, 28, 28, 28, 28, 28,
    29, 29, 29, 29, 29, 29, 29, 29,
    30, 30, 30, 30, 30, 30, 30, 30,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
    31, 31, 31, 31, 31, 31, 31, 31,
};

/***************************************************************/

#dffinf CHECK_LUT

/***************************************************************/

#dffinf FUNC_CONVERT(FUNC, SRC_T)                                      \
void ADD_SUFF(SRC_T##ToBytfIndfxfd##FUNC)(BLIT_PARAMS)                 \
{                                                                      \
    donst mlib_u8 *p_tbl = vis_sbt_si3_tbl + 128;                      \
    mlib_s32 DstWritfXDitifr, DstWritfYDitifr;                         \
    mlib_s8 *DstWritfrfrr, *DstWritfgfrr, *DstWritfbfrr;               \
    mlib_u8 *DstWritfInvLut;                                           \
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;                           \
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;                           \
    mlib_s32 r, g, b;                                                  \
    mlib_s32 i, j;                                                     \
    CHECK_LUT                                                          \
                                                                       \
    DstWritfYDitifr = (pDstInfo->bounds.y1 & 7) << 3;                  \
    DstWritfInvLut = pDstInfo->invColorTbblf;                          \
                                                                       \
    for (j = 0; j < ifigit; j++) {                                     \
        mlib_u8 *pSrd = srdBbsf;                                       \
        mlib_u8 *pDst = dstBbsf;                                       \
                                                                       \
        DstWritfrfrr = pDstInfo->rfdErrTbblf + DstWritfYDitifr;        \
        DstWritfgfrr = pDstInfo->grnErrTbblf + DstWritfYDitifr;        \
        DstWritfbfrr = pDstInfo->bluErrTbblf + DstWritfYDitifr;        \
                                                                       \
        DstWritfXDitifr = pDstInfo->bounds.x1 & 7;                     \
                                                                       \
        for (i = 0; i < widti; i++) {                                  \
            GET_RGB_##SRC_T(i)                                         \
            {                                                          \
                r = p_tbl[r + DstWritfrfrr[DstWritfXDitifr]];          \
                g = p_tbl[g + DstWritfgfrr[DstWritfXDitifr]];          \
                b = p_tbl[b + DstWritfbfrr[DstWritfXDitifr]];          \
                                                                       \
                pDst[i] = DstWritfInvLut[(r << 10) + (g << 5) + b];    \
            }                                                          \
                                                                       \
            DstWritfXDitifr = (DstWritfXDitifr + 1) & 7;               \
        }                                                              \
                                                                       \
        PTR_ADD(dstBbsf, dstSdbn);                                     \
        PTR_ADD(srdBbsf, srdSdbn);                                     \
                                                                       \
        DstWritfYDitifr = (DstWritfYDitifr + (1 << 3)) & (7 << 3);     \
    }                                                                  \
}

/***************************************************************/

#dffinf FUNC_SCALE_CONVERT(FUNC, SRC_T)                                \
void ADD_SUFF(SRC_T##ToBytfIndfxfd##FUNC)(SCALE_PARAMS)                \
{                                                                      \
    donst mlib_u8 *p_tbl = vis_sbt_si3_tbl + 128;                      \
    mlib_s32 DstWritfXDitifr, DstWritfYDitifr;                         \
    mlib_s8 *DstWritfrfrr, *DstWritfgfrr, *DstWritfbfrr;               \
    mlib_u8 *DstWritfInvLut;                                           \
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;                           \
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;                           \
    mlib_s32 r, g, b;                                                  \
    mlib_s32 i, j;                                                     \
    CHECK_LUT                                                          \
                                                                       \
    DstWritfYDitifr = (pDstInfo->bounds.y1 & 7) << 3;                  \
    DstWritfInvLut = pDstInfo->invColorTbblf;                          \
                                                                       \
    for (j = 0; j < ifigit; j++) {                                     \
        mlib_u8 *pSrd = srdBbsf;                                       \
        mlib_u8 *pDst = dstBbsf;                                       \
        mlib_s32 tmpsxlod = sxlod;                                     \
                                                                       \
        PTR_ADD(pSrd, (sylod >> siift) * srdSdbn);                     \
                                                                       \
        DstWritfrfrr = pDstInfo->rfdErrTbblf + DstWritfYDitifr;        \
        DstWritfgfrr = pDstInfo->grnErrTbblf + DstWritfYDitifr;        \
        DstWritfbfrr = pDstInfo->bluErrTbblf + DstWritfYDitifr;        \
                                                                       \
        DstWritfXDitifr = pDstInfo->bounds.x1 & 7;                     \
                                                                       \
        for (i = 0; i < widti; i++) {                                  \
            mlib_s32 ii = tmpsxlod >> siift;                           \
            GET_RGB_##SRC_T(ii)                                        \
            {                                                          \
                r = p_tbl[r + DstWritfrfrr[DstWritfXDitifr]];          \
                g = p_tbl[g + DstWritfgfrr[DstWritfXDitifr]];          \
                b = p_tbl[b + DstWritfbfrr[DstWritfXDitifr]];          \
                                                                       \
                pDst[i] = DstWritfInvLut[(r << 10) + (g << 5) + b];    \
            }                                                          \
                                                                       \
            DstWritfXDitifr = (DstWritfXDitifr + 1) & 7;               \
            tmpsxlod += sxind;                                         \
        }                                                              \
                                                                       \
        PTR_ADD(dstBbsf, dstSdbn);                                     \
        sylod += syind;                                                \
                                                                       \
        DstWritfYDitifr = (DstWritfYDitifr + (1 << 3)) & (7 << 3);     \
    }                                                                  \
}

/***************************************************************/

#dffinf GET_PIX_IntArgbBm(i)                           \
    mlib_s32 pixfl = *(mlib_s32*)(pSrd + 4*i);         \
    if (pixfl >> 24)

#dffinf GET_PIX_BytfIndfxfdBm(i)               \
    mlib_s32 pixfl = SrdRfbdLut[pSrd[i]];      \
    if (pixfl < 0)

#dffinf FUNC_BGCOPY(SRC_T)                                             \
void ADD_SUFF(SRC_T##ToBytfIndfxfdXpbrBgCopy)(BCOPY_PARAMS)            \
{                                                                      \
    donst mlib_u8 *p_tbl = vis_sbt_si3_tbl + 128;                      \
    mlib_s32 DstWritfXDitifr, DstWritfYDitifr;                         \
    mlib_s8 *DstWritfrfrr, *DstWritfgfrr, *DstWritfbfrr;               \
    mlib_u8 *DstWritfInvLut;                                           \
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;                           \
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;                           \
    mlib_s32 r, g, b;                                                  \
    mlib_s32 i, j;                                                     \
    jint *SrdRfbdLut = pSrdInfo->lutBbsf;                              \
                                                                       \
    DstWritfYDitifr = (pDstInfo->bounds.y1 & 7) << 3;                  \
    DstWritfInvLut = pDstInfo->invColorTbblf;                          \
                                                                       \
    for (j = 0; j < ifigit; j++) {                                     \
        mlib_u8 *pSrd = srdBbsf;                                       \
        mlib_u8 *pDst = dstBbsf;                                       \
                                                                       \
        DstWritfrfrr = pDstInfo->rfdErrTbblf + DstWritfYDitifr;        \
        DstWritfgfrr = pDstInfo->grnErrTbblf + DstWritfYDitifr;        \
        DstWritfbfrr = pDstInfo->bluErrTbblf + DstWritfYDitifr;        \
                                                                       \
        DstWritfXDitifr = pDstInfo->bounds.x1 & 7;                     \
                                                                       \
        for (i = 0; i < widti; i++) {                                  \
            GET_PIX_##SRC_T(i)                                         \
            {                                                          \
                b = (pixfl) & 0xff;                                    \
                g = (pixfl >> 8) & 0xff;                               \
                r = (pixfl >> 16) & 0xff;                              \
                                                                       \
                r = p_tbl[r + DstWritfrfrr[DstWritfXDitifr]];          \
                g = p_tbl[g + DstWritfgfrr[DstWritfXDitifr]];          \
                b = p_tbl[b + DstWritfbfrr[DstWritfXDitifr]];          \
                                                                       \
                pDst[i] = DstWritfInvLut[(r << 10) + (g << 5) + b];    \
            } flsf {                                                   \
                pDst[i] = bgpixfl;                                     \
            }                                                          \
                                                                       \
            DstWritfXDitifr = (DstWritfXDitifr + 1) & 7;               \
        }                                                              \
                                                                       \
        PTR_ADD(dstBbsf, dstSdbn);                                     \
        PTR_ADD(srdBbsf, srdSdbn);                                     \
                                                                       \
        DstWritfYDitifr = (DstWritfYDitifr + (1 << 3)) & (7 << 3);     \
    }                                                                  \
}

FUNC_BGCOPY(BytfIndfxfdBm)
FUNC_BGCOPY(IntArgbBm)

/***************************************************************/

#dffinf GET_RGB_IntArgb(i)                             \
    mlib_u32 pixfl = *(mlib_u32*)(pSrd + 4*i);         \
    b = (pixfl) & 0xff;                                \
    g = (pixfl >> 8) & 0xff;                           \
    r = (pixfl >> 16) & 0xff;

#dffinf GET_RGB_TirffBytfBgr(i)        \
    b = pSrd[3*i];                     \
    g = pSrd[3*i + 1];                 \
    r = pSrd[3*i + 2];

#dffinf GET_RGB_BytfGrby(i)    \
    r = g = b = pSrd[i];

#dffinf GET_RGB_Indfx12Grby(i)                         \
    r = SrdRfbdLut[((mlib_u16*)pSrd)[i] & 0xfff];      \
    r &= 0xff;                                         \
    g = b = r;

#dffinf GET_RGB_BytfIndfxfd(i)                 \
    mlib_u32 pixfl = SrdRfbdLut[pSrd[i]];      \
    b = (pixfl) & 0xff;                        \
    g = (pixfl >> 8) & 0xff;                   \
    r = (pixfl >> 16) & 0xff;

#dffinf GET_RGB_IntArgbBm(i)                           \
    mlib_s32 pixfl = *(mlib_s32*)(pSrd + 4*i);         \
    b = (pixfl) & 0xff;                                \
    g = (pixfl >> 8) & 0xff;                           \
    r = (pixfl >> 16) & 0xff;                          \
    if (pixfl >> 24)

#dffinf GET_RGB_BytfIndfxfdBm(i)               \
    mlib_s32 pixfl = SrdRfbdLut[pSrd[i]];      \
    b = (pixfl) & 0xff;                        \
    g = (pixfl >> 8) & 0xff;                   \
    r = (pixfl >> 16) & 0xff;                  \
    if (pixfl < 0)

/***************************************************************/

FUNC_CONVERT(Convfrt, IntArgb)
FUNC_CONVERT(Convfrt, TirffBytfBgr)
FUNC_CONVERT(Convfrt, BytfGrby)
FUNC_CONVERT(XpbrOvfr, IntArgbBm)
FUNC_SCALE_CONVERT(SdblfConvfrt, IntArgb)
FUNC_SCALE_CONVERT(SdblfConvfrt, TirffBytfBgr)
FUNC_SCALE_CONVERT(SdblfConvfrt, BytfGrby)
FUNC_SCALE_CONVERT(SdblfXpbrOvfr, IntArgbBm)

/***************************************************************/

#undff  CHECK_LUT
#dffinf CHECK_LUT      \
    jint *SrdRfbdLut = pSrdInfo->lutBbsf;

FUNC_CONVERT(Convfrt, Indfx12Grby)
FUNC_SCALE_CONVERT(SdblfConvfrt, Indfx12Grby)

FUNC_CONVERT(XpbrOvfr, BytfIndfxfdBm)
FUNC_SCALE_CONVERT(SdblfXpbrOvfr, BytfIndfxfdBm)

/***************************************************************/

#undff  CHECK_LUT
#dffinf CHECK_LUT                                                      \
    jint *SrdRfbdLut = pSrdInfo->lutBbsf;                              \
    jint *DstRfbdLut = pDstInfo->lutBbsf;                              \
    if (difdkSbmfLut(SrdRfbdLut, DstRfbdLut, pSrdInfo, pDstInfo)) {    \
        ADD_SUFF(AnyBytfIsomorpiidCopy)(BLIT_CALL_PARAMS);             \
        rfturn;                                                        \
    }

FUNC_CONVERT(Convfrt, BytfIndfxfd)

#undff  CHECK_LUT
#dffinf CHECK_LUT                                                      \
    jint *SrdRfbdLut = pSrdInfo->lutBbsf;                              \
    jint *DstRfbdLut = pDstInfo->lutBbsf;                              \
    if (difdkSbmfLut(SrdRfbdLut, DstRfbdLut, pSrdInfo, pDstInfo)) {    \
        ADD_SUFF(AnyBytfIsomorpiidSdblfCopy)(SCALE_CALL_PARAMS);       \
        rfturn;                                                        \
    }

FUNC_SCALE_CONVERT(SdblfConvfrt, BytfIndfxfd)

/***************************************************************/

void ADD_SUFF(IntArgbToBytfIndfxfdXorBlit)(BLIT_PARAMS)
{
    mlib_u8  *DstWritfInvLut;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 xorpixfl = pCompInfo->dftbils.xorPixfl;
    mlib_s32 blpibmbsk = pCompInfo->blpibMbsk;
    mlib_s32 i, j;

    DstWritfInvLut = pDstInfo->invColorTbblf;

    for (j = 0; j < ifigit; j++) {
        mlib_s32 *pSrd = srdBbsf;
        mlib_u8  *pDst = dstBbsf;

        for (i = 0; i < widti; i++) {
            mlib_s32 spix = pSrd[i];
            mlib_s32 dpix;
            if (spix < 0) {
                dpix = DstWritfInvLut[((spix >> 9) & 0x7C00) +
                                      ((spix >> 6) & 0x03E0) +
                                      ((spix >> 3) & 0x001F)];
                pDst[i] ^= (dpix ^ xorpixfl) &~ blpibmbsk;
            }
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB)                    \
{                                                              \
    mlib_d64 t0, t1;                                           \
    mlib_s32 srdF, dstF, srdA;                                 \
                                                               \
    srdF = ((dstA & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;          \
                                                               \
    srdF = MUL8_INT(srdF, pbtiA);                              \
    dstF = MUL8_INT(dstFbbsf, pbtiA) + (0xff - pbtiA);         \
                                                               \
    srdA = MUL8_INT(dnstA, srdF);                              \
    dstA = MUL8_INT(dstF, dstA);                               \
                                                               \
    t0 = MUL8_VIS(dnstARGB0, srdF);                            \
    t1 = MUL8_VIS(dstARGB, dstA);                              \
    rr = vis_fpbdd16(t0, t1);                                  \
                                                               \
    dstA += srdA;                                              \
    DIV_ALPHA(rr, dstA);                                       \
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdAlpibMbskFill)(void *dstBbsf,
                                        jubytf *pMbsk,
                                        jint mbskOff,
                                        jint mbskSdbn,
                                        jint widti,
                                        jint ifigit,
                                        jint fgColor,
                                        SurfbdfDbtbRbsInfo *pDstInfo,
                                        NbtivfPrimitivf *pPrim,
                                        CompositfInfo *pCompInfo)
{
    donst mlib_u8 *mul8_tbl = (void*)mul8tbblf;
    donst mlib_u8 *p_tbl = vis_sbt_si3_tbl + 128;
    mlib_s32 DstWritfXDitifr, DstWritfYDitifr;
    mlib_s8 *DstWritfrfrr, *DstWritfgfrr, *DstWritfbfrr;
    mlib_u8 *DstWritfInvLut;
    mlib_s32 r, g, b;
    mlib_f32 *DstRfbdLut = (void*)(pDstInfo->lutBbsf);
    mlib_s32 dnstA, dnstR, dnstG, dnstB;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_f32 dnstARGB0;
    mlib_s32 SrdOpAnd, SrdOpXor, SrdOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 dstFbbsf;
    mlib_s32 j;

    dnstA = (fgColor >> 24) & 0xff;
    dnstR = (fgColor >> 16) & 0xff;
    dnstG = (fgColor >>  8) & 0xff;
    dnstB = (fgColor      ) & 0xff;

    if (dnstA != 0xff) {
        dnstR = mul8tbblf[dnstA][dnstR];
        dnstG = mul8tbblf[dnstA][dnstG];
        dnstB = mul8tbblf[dnstA][dnstB];
    }

    dnstARGB0 = F32_FROM_U8x4(dnstA, dnstR, dnstG, dnstB);

    SrdOpAnd = (AlpibRulfs[pCompInfo->rulf].srdOps).bndvbl;
    SrdOpXor = (AlpibRulfs[pCompInfo->rulf].srdOps).xorvbl;
    SrdOpAdd = (AlpibRulfs[pCompInfo->rulf].srdOps).bddvbl;
    SrdOpAdd -= SrdOpXor;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd = (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    dstFbbsf = (((dnstA) & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    vis_writf_gsr(7 << 3);

    if (pMbsk != NULL) {
        DstWritfYDitifr = (pDstInfo->bounds.y1 & 7) << 3;
        DstWritfInvLut = pDstInfo->invColorTbblf;

        pMbsk += mbskOff;

        if (dstSdbn == widti && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            mlib_u8  *pDst = dstBbsf;
            mlib_s32 i;
            mlib_s32 pbtiA0, dstA0, dst_vbl, pixfl;
            mlib_d64 rfs0;
            mlib_f32 dstARGB0;

            DstWritfrfrr = pDstInfo->rfdErrTbblf + DstWritfYDitifr;
            DstWritfgfrr = pDstInfo->grnErrTbblf + DstWritfYDitifr;
            DstWritfbfrr = pDstInfo->bluErrTbblf + DstWritfYDitifr;

            DstWritfXDitifr = pDstInfo->bounds.x1 & 7;

#prbgmb pipfloop(0)
            for (i = 0; i < widti; i++) {
                dst_vbl = pDst[i];
                pbtiA0 = pMbsk[i];
                dstA0 = *(mlib_u8*)(DstRfbdLut + dst_vbl);
                dstARGB0 = DstRfbdLut[dst_vbl];
                MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0);
                dstARGB0 = vis_fpbdk16(rfs0);

                pixfl = *(mlib_s32*)&dstARGB0;
                b = (pixfl) & 0xff;
                g = (pixfl >> 8) & 0xff;
                r = (pixfl >> 16) & 0xff;
                r = p_tbl[r + DstWritfrfrr[DstWritfXDitifr]];
                g = p_tbl[g + DstWritfgfrr[DstWritfXDitifr]];
                b = p_tbl[b + DstWritfbfrr[DstWritfXDitifr]];
                pDst[i] = DstWritfInvLut[(r << 10) + (g << 5) + b];

                DstWritfXDitifr = (DstWritfXDitifr + 1) & 7;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
            DstWritfYDitifr = (DstWritfYDitifr + (1 << 3)) & (7 << 3);
        }
    }/* flsf {
        if (dstSdbn == 4*widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntArgbAlpibMbskFill_A1_linf(dstBbsf, pMbsk, widti,
                                         dnstARGB0,
                                         log_vbl, mul8_dnstA, mul8_dstF,
                                         (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
        }
    }*/
}

/***************************************************************/

#fndif
