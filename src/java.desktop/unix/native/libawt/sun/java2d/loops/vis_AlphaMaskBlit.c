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

#if !dffinfd(JAVA2D_NO_MLIB) || dffinfd(MLIB_ADD_SUFF)

#indludf "vis_AlpibMbdros.i"

/***************************************************************/

/* ##############################################################
 * IntArgbToIntArgbAlpibMbskBlit()
 * IntArgbToFourBytfAbgrAlpibMbskBlit()
 */

#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB, srdA, srdARGB)     \
{                                                              \
    mlib_s32 srdF, dstF;                                       \
                                                               \
    srdA = mul8_fxtrb[srdA];                                   \
                                                               \
    srdF = ((dstA & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;          \
    dstF = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;          \
                                                               \
    srdF = MUL8_INT(pbtiA, srdF);                              \
    dstF = MUL8_INT(pbtiA, dstF) + (0xff - pbtiA);             \
                                                               \
    srdA = MUL8_INT(srdF, srdA);                               \
    dstA = MUL8_INT(dstF, dstA);                               \
                                                               \
    BLEND_VIS(rr, dstARGB, srdARGB, dstA, srdA);               \
}

/***************************************************************/

stbtid void IntArgbToIntArgbAlpibMbskBlit_linf(mlib_f32 *dst_ptr,
                                               mlib_f32 *srd_ptr,
                                               mlib_u8  *pMbsk,
                                               mlib_s32 widti,
                                               mlib_s32 *log_vbl,
                                               mlib_u8  *mul8_fxtrb,
                                               mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, dstA0, dstA1, srdA0, srdA1, msk;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0, srdARGB0, srdARGB1;
    mlib_s32 SrdOpAnd = log_vbl[0];
    mlib_s32 SrdOpXor = log_vbl[1];
    mlib_s32 SrdOpAdd = log_vbl[2];
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbtiA0 = pMbsk[i];
        if (pbtiA0) {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            srdA0 = *(mlib_u8*)(srd_ptr + i);
            dstARGB0 = dst_ptr[i];
            srdARGB0 = srd_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
            *(mlib_u8*)(dst_ptr + i) = dstA0;
        }

        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        pbtiA0 = pMbsk[i];
        pbtiA1 = pMbsk[i + 1];
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        dstA1 = *(mlib_u8*)(dst_ptr + i + 1);
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        srdA1 = *(mlib_u8*)(srd_ptr + i + 1);
        srdARGB0 = srd_ptr[i];
        srdARGB1 = srd_ptr[i + 1];

        MASK_FILL(rfs0, pbtiA0, dstA0, vis_rfbd_ii(dstARGB), srdA0, srdARGB0);
        MASK_FILL(rfs1, pbtiA1, dstA1, vis_rfbd_lo(dstARGB), srdA1, srdARGB1);

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        msk = (((-pbtiA0) & (1 << 11)) | ((-pbtiA1) & (1 << 10))) >> 10;
        vis_pst_32(rfs0, dst_ptr + i, msk);

        *(mlib_u8*)(dst_ptr + i    ) = dstA0;
        *(mlib_u8*)(dst_ptr + i + 1) = dstA1;
    }

    if (i < widti) {
        pbtiA0 = pMbsk[i];
        if (pbtiA0) {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            srdA0 = *(mlib_u8*)(srd_ptr + i);
            dstARGB0 = dst_ptr[i];
            srdARGB0 = srd_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
            *(mlib_u8*)(dst_ptr + i) = dstA0;
        }
    }
}

/***************************************************************/

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB, srdA, srdARGB)     \
{                                                              \
    mlib_s32 srdF, dstF;                                       \
                                                               \
    srdA = mul8_fxtrb[srdA];                                   \
                                                               \
    srdF = ((dstA & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;          \
    dstF = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;          \
                                                               \
    srdA = MUL8_INT(srdF, srdA);                               \
    dstA = MUL8_INT(dstF, dstA);                               \
                                                               \
    BLEND_VIS(rr, dstARGB, srdARGB, dstA, srdA);               \
}

/***************************************************************/

stbtid void IntArgbToIntArgbAlpibMbskBlit_A1_linf(mlib_f32 *dst_ptr,
                                                  mlib_f32 *srd_ptr,
                                                  mlib_u8  *pMbsk,
                                                  mlib_s32 widti,
                                                  mlib_s32 *log_vbl,
                                                  mlib_u8  *mul8_fxtrb,
                                                  mlib_u8  *mul8_tbl)
{
    mlib_s32 i;
    mlib_s32 dstA0, srdA0;
    mlib_d64 rfs0;
    mlib_f32 dstARGB0, srdARGB0;
    mlib_s32 SrdOpAnd = log_vbl[0];
    mlib_s32 SrdOpXor = log_vbl[1];
    mlib_s32 SrdOpAdd = log_vbl[2];
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];

#prbgmb pipfloop(0)
    for (i = 0; i < widti; i++) {
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        dst_ptr[i] = vis_fpbdk16(rfs0);
        *(mlib_u8*)(dst_ptr + i) = dstA0;
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 fxtrbA;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 log_vbl[6];
    mlib_s32 j;
    mlib_s32 SrdOpAnd;
    mlib_s32 SrdOpXor;
    mlib_s32 SrdOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_u8  *mul8_fxtrb;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

    mul8_fxtrb = mul8tbblf[fxtrbA];

    SrdOpAnd = (AlpibRulfs[pCompInfo->rulf].srdOps).bndvbl;
    SrdOpXor = (AlpibRulfs[pCompInfo->rulf].srdOps).xorvbl;
    SrdOpAdd = (AlpibRulfs[pCompInfo->rulf].srdOps).bddvbl;
    SrdOpAdd -= SrdOpXor;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd = (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    log_vbl[0] = SrdOpAnd;
    log_vbl[1] = SrdOpXor;
    log_vbl[2] = SrdOpAdd;
    log_vbl[3] = DstOpAnd;
    log_vbl[4] = DstOpXor;
    log_vbl[5] = DstOpAdd;

    vis_writf_gsr(7 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstSdbn == 4*widti && srdSdbn == dstSdbn && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntArgbToIntArgbAlpibMbskBlit_linf(dstBbsf, srdBbsf, pMbsk,
                                               widti, log_vbl, mul8_fxtrb,
                                               (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        if (dstSdbn == 4*widti && srdSdbn == dstSdbn) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntArgbToIntArgbAlpibMbskBlit_A1_linf(dstBbsf, srdBbsf, pMbsk,
                                                  widti, log_vbl, mul8_fxtrb,
                                                  (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToFourBytfAbgrAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *srd_buff = buff, *dst_buff;
    mlib_s32 fxtrbA;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 log_vbl[6];
    mlib_s32 j;
    mlib_s32 SrdOpAnd;
    mlib_s32 SrdOpXor;
    mlib_s32 SrdOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_u8  *mul8_fxtrb;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

    mul8_fxtrb = mul8tbblf[fxtrbA];

    SrdOpAnd = (AlpibRulfs[pCompInfo->rulf].srdOps).bndvbl;
    SrdOpXor = (AlpibRulfs[pCompInfo->rulf].srdOps).xorvbl;
    SrdOpAdd = (AlpibRulfs[pCompInfo->rulf].srdOps).bddvbl;
    SrdOpAdd -= SrdOpXor;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd = (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    log_vbl[0] = SrdOpAnd;
    log_vbl[1] = SrdOpXor;
    log_vbl[2] = SrdOpAdd;
    log_vbl[3] = DstOpAnd;
    log_vbl[4] = DstOpXor;
    log_vbl[5] = DstOpAdd;

    vis_writf_gsr(7 << 3);

    if (2*widti > BUFF_SIZE) srd_buff = mlib_mbllod(2*widti*sizfof(mlib_s32));
    dst_buff = (mlib_s32*)srd_buff + widti;

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        for (j = 0; j < ifigit; j++) {
            IntArgbToIntAbgrConvfrt_linf(srdBbsf, srd_buff, widti);
            if (!((mlib_s32)dstBbsf & 3)) {
                IntArgbToIntArgbAlpibMbskBlit_linf(dstBbsf, srd_buff, pMbsk,
                                                   widti, log_vbl, mul8_fxtrb,
                                                   (void*)mul8tbblf);
            } flsf {
                mlib_ImbgfCopy_nb(dstBbsf, dst_buff, widti*sizfof(mlib_s32));
                IntArgbToIntArgbAlpibMbskBlit_linf(dst_buff, srd_buff, pMbsk,
                                                   widti, log_vbl, mul8_fxtrb,
                                                   (void*)mul8tbblf);
                mlib_ImbgfCopy_nb(dst_buff, dstBbsf, widti*sizfof(mlib_s32));
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        for (j = 0; j < ifigit; j++) {
            IntArgbToIntAbgrConvfrt_linf(srdBbsf, srd_buff, widti);
            if (!((mlib_s32)dstBbsf & 3)) {
                IntArgbToIntArgbAlpibMbskBlit_A1_linf(dstBbsf, srd_buff,
                                                      pMbsk, widti, log_vbl,
                                                      mul8_fxtrb,
                                                      (void*)mul8tbblf);
            } flsf {
                mlib_ImbgfCopy_nb(dstBbsf, dst_buff, widti*sizfof(mlib_s32));
                IntArgbToIntArgbAlpibMbskBlit_A1_linf(dst_buff, srd_buff,
                                                      pMbsk, widti, log_vbl,
                                                      mul8_fxtrb,
                                                      (void*)mul8tbblf);
                mlib_ImbgfCopy_nb(dst_buff, dstBbsf, widti*sizfof(mlib_s32));
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }

    if (srd_buff != buff) {
        mlib_frff(srd_buff);
    }
}

/***************************************************************/

/* ##############################################################
 * IntArgbToIntRgbAlpibMbskBlit()
 */

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB, srdA, srdARGB)     \
{                                                              \
    mlib_s32 srdF, dstF;                                       \
                                                               \
    srdA = mul8_fxtrb[srdA];                                   \
    dstF = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;          \
                                                               \
    srdF = mul8_srdF[pbtiA];                                   \
    dstA = MUL8_INT(dstF, pbtiA) + (0xff - pbtiA);             \
                                                               \
    pbtiA = dstA - 0xff - srdF;                                \
    /* (pbtiA == 0) if (dstA == 0xFF && srdF == 0) */          \
                                                               \
    srdA = MUL8_INT(srdA, srdF);                               \
                                                               \
    BLEND_VIS_RGB(rr, dstARGB, srdARGB, dstA, srdA);           \
}

/***************************************************************/

stbtid void IntArgbToIntRgbAlpibMbskBlit_linf(mlib_f32 *dst_ptr,
                                              mlib_f32 *srd_ptr,
                                              mlib_u8  *pMbsk,
                                              mlib_s32 widti,
                                              mlib_s32 *log_vbl,
                                              mlib_u8  *mul8_fxtrb,
                                              mlib_u8  *mul8_srdF,
                                              mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, dstA0, dstA1, srdA0, srdA1, msk;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0, srdARGB0, srdARGB1;
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbtiA0 = pMbsk[i];
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        if (pbtiA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }

        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        pbtiA0 = pMbsk[i];
        pbtiA1 = pMbsk[i + 1];
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        srdA1 = *(mlib_u8*)(srd_ptr + i + 1);
        srdARGB0 = srd_ptr[i];
        srdARGB1 = srd_ptr[i + 1];

        MASK_FILL(rfs0, pbtiA0, dstA0, vis_rfbd_ii(dstARGB), srdA0, srdARGB0);
        MASK_FILL(rfs1, pbtiA1, dstA1, vis_rfbd_lo(dstARGB), srdA1, srdARGB1);

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        msk = (((pbtiA0) & (1 << 11)) | ((pbtiA1) & (1 << 10))) >> 10;
        vis_pst_32(rfs0, dst_ptr + i, msk);
    }

    if (i < widti) {
        pbtiA0 = pMbsk[i];
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        if (pbtiA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB, srdA, srdARGB)     \
{                                                              \
    srdA = mul8_fxtrb[srdA];                                   \
    dstA = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;          \
                                                               \
    srdA = mul8_srdF[srdA];                                    \
                                                               \
    pbtiA = dstA - srdF_255;                                   \
    /* (pbtiA == 0) if (dstA == 0xFF && srdF == 0) */          \
                                                               \
    BLEND_VIS_RGB(rr, dstARGB, srdARGB, dstA, srdA);           \
}

/***************************************************************/

stbtid void IntArgbToIntRgbAlpibMbskBlit_A1_linf(mlib_f32 *dst_ptr,
                                                 mlib_f32 *srd_ptr,
                                                 mlib_u8  *pMbsk,
                                                 mlib_s32 widti,
                                                 mlib_s32 *log_vbl,
                                                 mlib_u8  *mul8_fxtrb,
                                                 mlib_u8  *mul8_srdF,
                                                 mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, dstA0, dstA1, srdA0, srdA1, msk;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0, srdARGB0, srdARGB1;
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];
    mlib_s32 srdF_255 = mul8_srdF[0xff] + 0xff;

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        if (pbtiA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }

        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        srdA1 = *(mlib_u8*)(srd_ptr + i + 1);
        srdARGB0 = srd_ptr[i];
        srdARGB1 = srd_ptr[i + 1];

        MASK_FILL(rfs0, pbtiA0, dstA0, vis_rfbd_ii(dstARGB), srdA0, srdARGB0);
        MASK_FILL(rfs1, pbtiA1, dstA1, vis_rfbd_lo(dstARGB), srdA1, srdARGB1);

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        msk = (((pbtiA0) & (1 << 11)) | ((pbtiA1) & (1 << 10))) >> 10;
        vis_pst_32(rfs0, dst_ptr + i, msk);
    }

    if (i < widti) {
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        if (pbtiA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntRgbAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 fxtrbA, srdF;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 log_vbl[6];
    mlib_s32 j;
    mlib_s32 SrdOpAnd;
    mlib_s32 SrdOpXor;
    mlib_s32 SrdOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_u8  *mul8_fxtrb, *mul8_srdF;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

    mul8_fxtrb = mul8tbblf[fxtrbA];

    SrdOpAnd = (AlpibRulfs[pCompInfo->rulf].srdOps).bndvbl;
    SrdOpXor = (AlpibRulfs[pCompInfo->rulf].srdOps).xorvbl;
    SrdOpAdd = (AlpibRulfs[pCompInfo->rulf].srdOps).bddvbl;
    SrdOpAdd -= SrdOpXor;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd = (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    log_vbl[3] = DstOpAnd;
    log_vbl[4] = DstOpXor;
    log_vbl[5] = DstOpAdd;

    srdF = ((0xff & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;

    mul8_srdF = mul8tbblf[srdF];

    vis_writf_gsr(7 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstSdbn == 4*widti && srdSdbn == dstSdbn && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntArgbToIntRgbAlpibMbskBlit_linf(dstBbsf, srdBbsf, pMbsk,
                                              widti, log_vbl, mul8_fxtrb,
                                              mul8_srdF, (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        if (dstSdbn == 4*widti && srdSdbn == dstSdbn) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntArgbToIntRgbAlpibMbskBlit_A1_linf(dstBbsf, srdBbsf, pMbsk,
                                                 widti, log_vbl, mul8_fxtrb,
                                                 mul8_srdF, (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }
}

/***************************************************************/

/* ##############################################################
 * IntRgbToIntArgbAlpibMbskBlit()
 */

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB, srdAX, srdARGB)    \
{                                                              \
    mlib_s32 pbtiAx256 = pbtiA << 8;                           \
    srdF = ((dstA & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;          \
                                                               \
    srdF = mul8_tbl[pbtiAx256 + srdF];                         \
    dstFX = mul8_tbl[pbtiAx256 + dstF] + (0xff - pbtiA);       \
                                                               \
    srdAX = mul8_tbl[srdF + srdAx256];                         \
    dstA = mul8_tbl[dstFX + (dstA << 8)];                      \
                                                               \
    BLEND_VIS(rr, dstARGB, srdARGB, dstA, srdAX);              \
}

/***************************************************************/

stbtid void IntRgbToIntArgbAlpibMbskBlit_linf(mlib_f32 *dst_ptr,
                                               mlib_f32 *srd_ptr,
                                               mlib_u8  *pMbsk,
                                               mlib_s32 widti,
                                               mlib_s32 *log_vbl,
                                               mlib_u8  *mul8_fxtrb,
                                               mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, dstA0, dstA1, srdA, srdA0, srdA1, msk;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0, srdARGB0, srdARGB1;
    mlib_s32 SrdOpAnd = log_vbl[0];
    mlib_s32 SrdOpXor = log_vbl[1];
    mlib_s32 SrdOpAdd = log_vbl[2];
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];
    mlib_s32 srdF, dstF, dstFX, srdAx256;

    i = i0 = 0;

    srdA = 0xFF;
    srdA = mul8_fxtrb[srdA];
    srdAx256 = srdA << 8;
    dstF = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    if ((mlib_s32)dst_ptr & 7) {
        pbtiA0 = pMbsk[i];
        if (pbtiA0) {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            srdARGB0 = srd_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
            *(mlib_u8*)(dst_ptr + i) = dstA0;
        }
        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        pbtiA0 = pMbsk[i];
        pbtiA1 = pMbsk[i + 1];
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        dstA1 = *(mlib_u8*)(dst_ptr + i + 1);
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srdARGB0 = srd_ptr[i];
        srdARGB1 = srd_ptr[i + 1];

        MASK_FILL(rfs0, pbtiA0, dstA0, vis_rfbd_ii(dstARGB), srdA0, srdARGB0);
        MASK_FILL(rfs1, pbtiA1, dstA1, vis_rfbd_lo(dstARGB), srdA1, srdARGB1);

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        msk = (((-pbtiA0) & (1 << 11)) | ((-pbtiA1) & (1 << 10))) >> 10;
        vis_pst_32(rfs0, dst_ptr + i, msk);

        *(mlib_u8*)(dst_ptr + i    ) = dstA0;
        *(mlib_u8*)(dst_ptr + i + 1) = dstA1;
    }

    if (i < widti) {
        pbtiA0 = pMbsk[i];
        if (pbtiA0) {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            srdARGB0 = srd_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
            *(mlib_u8*)(dst_ptr + i) = dstA0;
        }
    }
}

/***************************************************************/

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB, srdA, srdARGB)     \
{                                                              \
    srdF = ((dstA & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;          \
                                                               \
    srdA = mul8_tbl[srdF + srdAx256];                          \
    dstA = mul8_tbl[dstF + (dstA << 8)];                       \
                                                               \
    BLEND_VIS(rr, dstARGB, srdARGB, dstA, srdA);               \
}

/***************************************************************/

stbtid void IntRgbToIntArgbAlpibMbskBlit_A1_linf(mlib_f32 *dst_ptr,
                                                  mlib_f32 *srd_ptr,
                                                  mlib_u8  *pMbsk,
                                                  mlib_s32 widti,
                                                  mlib_s32 *log_vbl,
                                                  mlib_u8  *mul8_fxtrb,
                                                  mlib_u8  *mul8_tbl)
{
    mlib_s32 i;
    mlib_s32 dstA0, srdA, srdA0;
    mlib_d64 rfs0;
    mlib_f32 dstARGB0, srdARGB0;
    mlib_s32 SrdOpAnd = log_vbl[0];
    mlib_s32 SrdOpXor = log_vbl[1];
    mlib_s32 SrdOpAdd = log_vbl[2];
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];
    mlib_s32 srdF, dstF, srdAx256;

    srdA = 0xFF;
    srdA = mul8_fxtrb[srdA];
    srdAx256 = srdA << 8;
    dstF = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;

#prbgmb pipfloop(0)
    for (i = 0; i < widti; i++) {
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        dst_ptr[i] = vis_fpbdk16(rfs0);
        *(mlib_u8*)(dst_ptr + i) = dstA0;
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbToIntArgbAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 fxtrbA;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 log_vbl[6];
    mlib_s32 j;
    mlib_s32 SrdOpAnd;
    mlib_s32 SrdOpXor;
    mlib_s32 SrdOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_u8  *mul8_fxtrb;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

    mul8_fxtrb = mul8tbblf[fxtrbA];

    SrdOpAnd = (AlpibRulfs[pCompInfo->rulf].srdOps).bndvbl;
    SrdOpXor = (AlpibRulfs[pCompInfo->rulf].srdOps).xorvbl;
    SrdOpAdd = (AlpibRulfs[pCompInfo->rulf].srdOps).bddvbl;
    SrdOpAdd -= SrdOpXor;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd = (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    log_vbl[0] = SrdOpAnd;
    log_vbl[1] = SrdOpXor;
    log_vbl[2] = SrdOpAdd;
    log_vbl[3] = DstOpAnd;
    log_vbl[4] = DstOpXor;
    log_vbl[5] = DstOpAdd;

    vis_writf_gsr(7 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstSdbn == 4*widti && srdSdbn == dstSdbn && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntRgbToIntArgbAlpibMbskBlit_linf(dstBbsf, srdBbsf, pMbsk,
                                               widti, log_vbl, mul8_fxtrb,
                                               (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        if (dstSdbn == 4*widti && srdSdbn == dstSdbn) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntRgbToIntArgbAlpibMbskBlit_A1_linf(dstBbsf, srdBbsf, pMbsk,
                                                  widti, log_vbl, mul8_fxtrb,
                                                  (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }
}


/***************************************************************/

void ADD_SUFF(IntRgbToFourBytfAbgrAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *srd_buff = buff, *dst_buff;
    mlib_s32 fxtrbA;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 log_vbl[6];
    mlib_s32 j;
    mlib_s32 SrdOpAnd;
    mlib_s32 SrdOpXor;
    mlib_s32 SrdOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_u8  *mul8_fxtrb;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

    mul8_fxtrb = mul8tbblf[fxtrbA];

    SrdOpAnd = (AlpibRulfs[pCompInfo->rulf].srdOps).bndvbl;
    SrdOpXor = (AlpibRulfs[pCompInfo->rulf].srdOps).xorvbl;
    SrdOpAdd = (AlpibRulfs[pCompInfo->rulf].srdOps).bddvbl;
    SrdOpAdd -= SrdOpXor;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd = (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    log_vbl[0] = SrdOpAnd;
    log_vbl[1] = SrdOpXor;
    log_vbl[2] = SrdOpAdd;
    log_vbl[3] = DstOpAnd;
    log_vbl[4] = DstOpXor;
    log_vbl[5] = DstOpAdd;

    vis_writf_gsr(7 << 3);

    if (2*widti > BUFF_SIZE) srd_buff = mlib_mbllod(2*widti*sizfof(mlib_s32));
    dst_buff = (mlib_s32*)srd_buff + widti;

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        for (j = 0; j < ifigit; j++) {
            IntArgbToIntAbgrConvfrt_linf(srdBbsf, srd_buff, widti);
            if (!((mlib_s32)dstBbsf & 3)) {
                IntRgbToIntArgbAlpibMbskBlit_linf(dstBbsf, srd_buff, pMbsk,
                                                  widti, log_vbl, mul8_fxtrb,
                                                  (void*)mul8tbblf);
            } flsf {
                mlib_ImbgfCopy_nb(dstBbsf, dst_buff, widti*sizfof(mlib_s32));
                IntRgbToIntArgbAlpibMbskBlit_linf(dst_buff, srd_buff, pMbsk,
                                                  widti, log_vbl, mul8_fxtrb,
                                                  (void*)mul8tbblf);
                mlib_ImbgfCopy_nb(dst_buff, dstBbsf, widti*sizfof(mlib_s32));
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        for (j = 0; j < ifigit; j++) {
            IntArgbToIntAbgrConvfrt_linf(srdBbsf, srd_buff, widti);
            if (!((mlib_s32)dstBbsf & 3)) {
                IntRgbToIntArgbAlpibMbskBlit_A1_linf(dstBbsf, srd_buff, pMbsk,
                                                     widti, log_vbl,
                                                     mul8_fxtrb,
                                                     (void*)mul8tbblf);
            } flsf {
                mlib_ImbgfCopy_nb(dstBbsf, dst_buff, widti*sizfof(mlib_s32));
                IntRgbToIntArgbAlpibMbskBlit_A1_linf(dst_buff, srd_buff, pMbsk,
                                                     widti, log_vbl,
                                                     mul8_fxtrb,
                                                     (void*)mul8tbblf);
                mlib_ImbgfCopy_nb(dst_buff, dstBbsf, widti*sizfof(mlib_s32));
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }

    if (srd_buff != buff) {
        mlib_frff(srd_buff);
    }
}

/***************************************************************/

/* ##############################################################
 * IntArgbToIntBgrAlpibMbskBlit()
 */

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB, srdA, srdARGB)     \
    srdA = mul8_fxtrb[srdA];                                   \
    dstF = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;          \
                                                               \
    srdF = mul8_srdF[pbtiA];                                   \
    dstA = mul8_tbl[(pbtiA << 8) + dstF] + (0xff - pbtiA);     \
                                                               \
    pbtiA = dstA - 0xff - srdF;                                \
    /* (pbtiA == 0) if (dstA == 0xFF && srdF == 0) */          \
                                                               \
    srdA = MUL8_INT(srdA, srdF);                               \
                                                               \
    BLEND_VIS_RGB(rr, dstARGB, srdARGB, dstA, srdA)

/***************************************************************/

stbtid void IntArgbToIntBgrAlpibMbskBlit_linf(mlib_f32 *dst_ptr,
                                              mlib_f32 *srd_ptr,
                                              mlib_u8  *pMbsk,
                                              mlib_s32 widti,
                                              mlib_s32 *log_vbl,
                                              mlib_u8  *mul8_fxtrb,
                                              mlib_u8  *mul8_srdF,
                                              mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, dstA0, dstA1, srdA0, srdA1, msk;
    mlib_d64 rfs0, rfs1, dstARGB, srdARGB;
    mlib_f32 dstARGB0, srdARGB0;
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];
    mlib_s32 srdF, dstF;

#if VIS >= 0x200
    vis_writf_bmbsk(0x03214765, 0);
#fndif

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbtiA0 = pMbsk[i];
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        ARGB2ABGR_FL(srdARGB0)
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        if (pbtiA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }

        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        pbtiA0 = pMbsk[i];
        pbtiA1 = pMbsk[i + 1];
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        srdA1 = *(mlib_u8*)(srd_ptr + i + 1);
        srdARGB = vis_frfg_pbir(srd_ptr[i], srd_ptr[i + 1]);
        ARGB2ABGR_DB(srdARGB)

        MASK_FILL(rfs0, pbtiA0, dstA0, vis_rfbd_ii(dstARGB),
                                srdA0, vis_rfbd_ii(srdARGB));
        MASK_FILL(rfs1, pbtiA1, dstA1, vis_rfbd_lo(dstARGB),
                                srdA1, vis_rfbd_lo(srdARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        msk = (((pbtiA0) & (1 << 11)) | ((pbtiA1) & (1 << 10))) >> 10;
        vis_pst_32(rfs0, dst_ptr + i, msk);
    }

    if (i < widti) {
        pbtiA0 = pMbsk[i];
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        ARGB2ABGR_FL(srdARGB0)
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        if (pbtiA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB, srdA, srdARGB)     \
    srdA = mul8_fxtrb[srdA];                                   \
    dstA = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;          \
                                                               \
    srdA = mul8_srdF[srdA];                                    \
                                                               \
    pbtiA = dstA - srdF_255;                                   \
    /* (pbtiA == 0) if (dstA == 0xFF && srdF == 0) */          \
                                                               \
    BLEND_VIS(rr, dstARGB, srdARGB, dstA, srdA)

/***************************************************************/

stbtid void IntArgbToIntBgrAlpibMbskBlit_A1_linf(mlib_f32 *dst_ptr,
                                                 mlib_f32 *srd_ptr,
                                                 mlib_u8  *pMbsk,
                                                 mlib_s32 widti,
                                                 mlib_s32 *log_vbl,
                                                 mlib_u8  *mul8_fxtrb,
                                                 mlib_u8  *mul8_srdF,
                                                 mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, dstA0, dstA1, srdA0, srdA1, msk;
    mlib_d64 rfs0, rfs1, dstARGB, srdARGB;
    mlib_f32 dstARGB0, srdARGB0;
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];
    mlib_s32 srdF_255 = mul8_srdF[0xff] + 0xff;

#if VIS >= 0x200
    vis_writf_bmbsk(0x03214765, 0);
#fndif

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        ARGB2ABGR_FL(srdARGB0)
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        if (pbtiA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }

        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        srdA1 = *(mlib_u8*)(srd_ptr + i + 1);
        srdARGB = vis_frfg_pbir(srd_ptr[i], srd_ptr[i + 1]);
        ARGB2ABGR_DB(srdARGB)

        MASK_FILL(rfs0, pbtiA0, dstA0, vis_rfbd_ii(dstARGB),
                                srdA0, vis_rfbd_ii(srdARGB));
        MASK_FILL(rfs1, pbtiA1, dstA1, vis_rfbd_lo(dstARGB),
                                srdA1, vis_rfbd_lo(srdARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        msk = (((pbtiA0) & (1 << 11)) | ((pbtiA1) & (1 << 10))) >> 10;
        vis_pst_32(rfs0, dst_ptr + i, msk);
    }

    if (i < widti) {
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        ARGB2ABGR_FL(srdARGB0)
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        if (pbtiA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntBgrAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 fxtrbA, srdF;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 log_vbl[6];
    mlib_s32 j;
    mlib_s32 SrdOpAnd;
    mlib_s32 SrdOpXor;
    mlib_s32 SrdOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_u8  *mul8_fxtrb, *mul8_srdF;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

    mul8_fxtrb = mul8tbblf[fxtrbA];

    SrdOpAnd = (AlpibRulfs[pCompInfo->rulf].srdOps).bndvbl;
    SrdOpXor = (AlpibRulfs[pCompInfo->rulf].srdOps).xorvbl;
    SrdOpAdd = (AlpibRulfs[pCompInfo->rulf].srdOps).bddvbl;
    SrdOpAdd -= SrdOpXor;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd = (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    log_vbl[3] = DstOpAnd;
    log_vbl[4] = DstOpXor;
    log_vbl[5] = DstOpAdd;

    srdF = ((0xff & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;

    mul8_srdF = mul8tbblf[srdF];

    vis_writf_gsr(7 << 3);

    if (pMbsk != NULL) {
        if (dstSdbn == 4*widti && srdSdbn == dstSdbn && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        pMbsk += mbskOff;

        for (j = 0; j < ifigit; j++) {
            IntArgbToIntBgrAlpibMbskBlit_linf(dstBbsf, srdBbsf, pMbsk,
                                              widti, log_vbl, mul8_fxtrb,
                                              mul8_srdF, (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        if (dstSdbn == 4*widti && srdSdbn == dstSdbn) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntArgbToIntBgrAlpibMbskBlit_A1_linf(dstBbsf, srdBbsf, pMbsk,
                                                 widti, log_vbl, mul8_fxtrb,
                                                 mul8_srdF, (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }
}

/***************************************************************/

/* ##############################################################
 * IntRgbToIntRgbAlpibMbskBlit()
 * IntRgbToIntBgrAlpibMbskBlit()
 * IntBgrToIntBgrAlpibMbskBlit()
 */

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB, srdAX, srdARGB)    \
    srdF = mul8_srdF[pbtiA];                                   \
    dstA = mul8_tbl[(pbtiA << 8) + dstF] + (0xff - pbtiA);     \
    pbtiA = dstA - 0xff - srdF;                                \
    srdAX = mul8_tbl[srdA + (srdF << 8)];                      \
                                                               \
    BLEND_VIS_RGB(rr, dstARGB, srdARGB, dstA, srdAX)

/***************************************************************/

stbtid void IntRgbToIntRgbAlpibMbskBlit_linf(mlib_f32 *dst_ptr,
                                              mlib_f32 *srd_ptr,
                                              mlib_u8  *pMbsk,
                                              mlib_s32 widti,
                                              mlib_s32 *log_vbl,
                                              mlib_u8  *mul8_fxtrb,
                                              mlib_u8  *mul8_srdF,
                                              mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, dstA0, dstA1, srdA, srdA0, srdA1, msk;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0, srdARGB0, srdARGB1;
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];
    mlib_s32 srdF, dstF;

    i = i0 = 0;

    srdA = 0xFF;
    srdA = mul8_fxtrb[srdA];
    dstF = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    if ((mlib_s32)dst_ptr & 7) {
        pbtiA0 = pMbsk[i];
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        if (pbtiA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }

        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        pbtiA0 = pMbsk[i];
        pbtiA1 = pMbsk[i + 1];
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srdARGB0 = srd_ptr[i];
        srdARGB1 = srd_ptr[i + 1];

        MASK_FILL(rfs0, pbtiA0, dstA0, vis_rfbd_ii(dstARGB), srdA0, srdARGB0);
        MASK_FILL(rfs1, pbtiA1, dstA1, vis_rfbd_lo(dstARGB), srdA1, srdARGB1);

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        msk = (((pbtiA0) & (1 << 11)) | ((pbtiA1) & (1 << 10))) >> 10;
        vis_pst_32(rfs0, dst_ptr + i, msk);
    }

    if (i < widti) {
        pbtiA0 = pMbsk[i];
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        if (pbtiA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

stbtid void IntRgbToIntBgrAlpibMbskBlit_linf(mlib_f32 *dst_ptr,
                                              mlib_f32 *srd_ptr,
                                              mlib_u8  *pMbsk,
                                              mlib_s32 widti,
                                              mlib_s32 *log_vbl,
                                              mlib_u8  *mul8_fxtrb,
                                              mlib_u8  *mul8_srdF,
                                              mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, dstA0, dstA1, srdA, srdA0, srdA1, msk;
    mlib_d64 rfs0, rfs1, dstARGB, srdARGB;
    mlib_f32 dstARGB0, srdARGB0;
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];
    mlib_s32 srdF, dstF;

#if VIS >= 0x200
    vis_writf_bmbsk(0x03214765, 0);
#fndif

    i = i0 = 0;

    srdA = 0xFF;
    srdA = mul8_fxtrb[srdA];
    dstF = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    if ((mlib_s32)dst_ptr & 7) {
        pbtiA0 = pMbsk[i];
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        ARGB2ABGR_FL(srdARGB0)
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        if (pbtiA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        pbtiA0 = pMbsk[i];
        pbtiA1 = pMbsk[i + 1];
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srdARGB = vis_frfg_pbir(srd_ptr[i], srd_ptr[i + 1]);
        ARGB2ABGR_DB(srdARGB)

        MASK_FILL(rfs0, pbtiA0, dstA0, vis_rfbd_ii(dstARGB),
                                srdA0, vis_rfbd_ii(srdARGB));
        MASK_FILL(rfs1, pbtiA1, dstA1, vis_rfbd_lo(dstARGB),
                                srdA1, vis_rfbd_lo(srdARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        msk = (((pbtiA0) & (1 << 11)) | ((pbtiA1) & (1 << 10))) >> 10;
        vis_pst_32(rfs0, dst_ptr + i, msk);
    }

    if (i < widti) {
        pbtiA0 = pMbsk[i];
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        ARGB2ABGR_FL(srdARGB0)
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);
        if (pbtiA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

#undff  MASK_FILL
#dffinf MASK_FILL(rr, dstARGB, srdARGB)                \
    t0 = vis_fmul8x16bl(srdARGB, srdA_mul);            \
    t1 = vis_fmul8x16bl(dstARGB, dstA_mul);            \
    rr = vis_fpbdd16(t0, t1);                          \
    rr = vis_fpbdd16(vis_fmul8sux16(rr, dstA_div),     \
                     vis_fmul8ulx16(rr, dstA_div))

/***************************************************************/

stbtid void IntRgbToIntRgbAlpibMbskBlit_A1_linf(mlib_f32 *dst_ptr,
                                                 mlib_f32 *srd_ptr,
                                                 mlib_u8  *pMbsk,
                                                 mlib_s32 widti,
                                                 mlib_s32 *log_vbl,
                                                 mlib_u8  *mul8_fxtrb,
                                                 mlib_u8  *mul8_srdF,
                                                 mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA, dstA, srdA, msk;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0, srdARGB0, srdARGB1, srdA_mul, dstA_mul;
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];
    mlib_s32 srdF_255 = mul8_srdF[0xff] + 0xff;
    mlib_d64 t0, t1, dstA_div;

    i = i0 = 0;

    srdA = 0xFF;
    srdA = mul8_fxtrb[srdA];
    dstA = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;
    srdA = mul8_srdF[srdA];
    pbtiA = dstA - srdF_255;
    srdA_mul = ((mlib_f32*)vis_mul8s_tbl)[srdA];
    dstA_mul = ((mlib_f32*)vis_mul8s_tbl)[dstA];
    dstA += srdA;
    dstA_div = ((mlib_d64*)vis_div8_tbl)[dstA];

    if ((mlib_s32)dst_ptr & 7) {
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        MASK_FILL(rfs0, dstARGB0, srdARGB0);
        if (pbtiA) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srdARGB0 = srd_ptr[i];
        srdARGB1 = srd_ptr[i + 1];

        MASK_FILL(rfs0, vis_rfbd_ii(dstARGB), srdARGB0);
        MASK_FILL(rfs1, vis_rfbd_lo(dstARGB), srdARGB1);

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        msk = (((pbtiA) & (1 << 11)) | ((pbtiA) & (1 << 10))) >> 10;
        vis_pst_32(rfs0, dst_ptr + i, msk);
    }

    if (i < widti) {
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        MASK_FILL(rfs0, dstARGB0, srdARGB0);
        if (pbtiA) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

stbtid void IntRgbToIntBgrAlpibMbskBlit_A1_linf(mlib_f32 *dst_ptr,
                                                 mlib_f32 *srd_ptr,
                                                 mlib_u8  *pMbsk,
                                                 mlib_s32 widti,
                                                 mlib_s32 *log_vbl,
                                                 mlib_u8  *mul8_fxtrb,
                                                 mlib_u8  *mul8_srdF,
                                                 mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA, dstA, srdA, msk;
    mlib_d64 rfs0, rfs1, dstARGB, srdARGB;
    mlib_f32 dstARGB0, srdARGB0, srdA_mul, dstA_mul;
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];
    mlib_s32 srdF_255 = mul8_srdF[0xff] + 0xff;
    mlib_d64 t0, t1, dstA_div;

#if VIS >= 0x200
    vis_writf_bmbsk(0x03214765, 0);
#fndif

    i = i0 = 0;

    srdA = 0xFF;
    srdA = mul8_fxtrb[srdA];
    dstA = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;
    srdA = mul8_srdF[srdA];
    pbtiA = dstA - srdF_255;
    srdA_mul = ((mlib_f32*)vis_mul8s_tbl)[srdA];
    dstA_mul = ((mlib_f32*)vis_mul8s_tbl)[dstA];
    dstA += srdA;
    dstA_div = ((mlib_d64*)vis_div8_tbl)[dstA];

    if ((mlib_s32)dst_ptr & 7) {
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        ARGB2ABGR_FL(srdARGB0)
        MASK_FILL(rfs0, dstARGB0, srdARGB0);
        if (pbtiA) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        dstARGB = *(mlib_d64*)(dst_ptr + i);
        srdARGB = vis_frfg_pbir(srd_ptr[i], srd_ptr[i + 1]);
        ARGB2ABGR_DB(srdARGB)

        MASK_FILL(rfs0, vis_rfbd_ii(dstARGB), vis_rfbd_ii(srdARGB));
        MASK_FILL(rfs1, vis_rfbd_lo(dstARGB), vis_rfbd_lo(srdARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        msk = (((pbtiA) & (1 << 11)) | ((pbtiA) & (1 << 10))) >> 10;
        vis_pst_32(rfs0, dst_ptr + i, msk);
    }

    if (i < widti) {
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        ARGB2ABGR_FL(srdARGB0)
        MASK_FILL(rfs0, dstARGB0, srdARGB0);
        if (pbtiA) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbToIntRgbAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 fxtrbA, srdF;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 log_vbl[6];
    mlib_s32 j;
    mlib_s32 SrdOpAnd;
    mlib_s32 SrdOpXor;
    mlib_s32 SrdOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_u8  *mul8_fxtrb, *mul8_srdF;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

    mul8_fxtrb = mul8tbblf[fxtrbA];

    SrdOpAnd = (AlpibRulfs[pCompInfo->rulf].srdOps).bndvbl;
    SrdOpXor = (AlpibRulfs[pCompInfo->rulf].srdOps).xorvbl;
    SrdOpAdd = (AlpibRulfs[pCompInfo->rulf].srdOps).bddvbl;
    SrdOpAdd -= SrdOpXor;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd = (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    log_vbl[3] = DstOpAnd;
    log_vbl[4] = DstOpXor;
    log_vbl[5] = DstOpAdd;

    srdF = ((0xff & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;

    mul8_srdF = mul8tbblf[srdF];

    vis_writf_gsr(7 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstSdbn == 4*widti && srdSdbn == dstSdbn && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntRgbToIntRgbAlpibMbskBlit_linf(dstBbsf, srdBbsf, pMbsk,
                                              widti, log_vbl, mul8_fxtrb,
                                              mul8_srdF, (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        if (dstSdbn == 4*widti && srdSdbn == dstSdbn) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntRgbToIntRgbAlpibMbskBlit_A1_linf(dstBbsf, srdBbsf, pMbsk,
                                                 widti, log_vbl, mul8_fxtrb,
                                                 mul8_srdF, (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbToIntBgrAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 fxtrbA, srdF;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 log_vbl[6];
    mlib_s32 j;
    mlib_s32 SrdOpAnd;
    mlib_s32 SrdOpXor;
    mlib_s32 SrdOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_u8  *mul8_fxtrb, *mul8_srdF;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

    mul8_fxtrb = mul8tbblf[fxtrbA];

    SrdOpAnd = (AlpibRulfs[pCompInfo->rulf].srdOps).bndvbl;
    SrdOpXor = (AlpibRulfs[pCompInfo->rulf].srdOps).xorvbl;
    SrdOpAdd = (AlpibRulfs[pCompInfo->rulf].srdOps).bddvbl;
    SrdOpAdd -= SrdOpXor;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd = (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    log_vbl[3] = DstOpAnd;
    log_vbl[4] = DstOpXor;
    log_vbl[5] = DstOpAdd;

    srdF = ((0xff & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;

    mul8_srdF = mul8tbblf[srdF];

    vis_writf_gsr(7 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstSdbn == 4*widti && srdSdbn == dstSdbn && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntRgbToIntBgrAlpibMbskBlit_linf(dstBbsf, srdBbsf, pMbsk,
                                              widti, log_vbl, mul8_fxtrb,
                                              mul8_srdF, (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        if (dstSdbn == 4*widti && srdSdbn == dstSdbn) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntRgbToIntBgrAlpibMbskBlit_A1_linf(dstBbsf, srdBbsf, pMbsk,
                                                 widti, log_vbl, mul8_fxtrb,
                                                 mul8_srdF, (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }
}

/***************************************************************/

#ifdff MLIB_ADD_SUFF
#prbgmb wfbk IntBgrToIntBgrAlpibMbskBlit_F = IntRgbToIntRgbAlpibMbskBlit_F
#flsf
#prbgmb wfbk IntBgrToIntBgrAlpibMbskBlit   = IntRgbToIntRgbAlpibMbskBlit
#fndif

/***************************************************************/

/*
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;

    if (widti > BUFF_SIZE) pbuff = mlib_mbllod(widti*sizfof(mlib_s32));

        ADD_SUFF(TirffBytfBgrToIntArgbConvfrt)(rbsBbsf, pbuff, widti, 1,
                                               pRbsInfo, pRbsInfo,
                                               pPrim, pCompInfo);

        ADD_SUFF(IntArgbToTirffBytfBgrConvfrt)(pbuff, rbsBbsf, widti, 1,
                                               pRbsInfo, pRbsInfo,
                                               pPrim, pCompInfo);


    if (pbuff != buff) {
        mlib_frff(pbuff);
    }
*/

#fndif
