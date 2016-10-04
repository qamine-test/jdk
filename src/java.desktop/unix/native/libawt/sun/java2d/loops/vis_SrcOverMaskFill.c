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

#indludf <vis_AlpibMbdros.i>

/***************************************************************/

/* ##############################################################
 * IntArgbSrdOvfrMbskFill()
 * FourBytfAbgrSrdOvfrMbskFill()
 */

#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB)    \
{                                              \
    mlib_d64 t0, t1;                           \
    mlib_s32 blp0;                             \
                                               \
    blp0 = mul8_dnstA[pbtiA];                  \
    dstA = MUL8_INT(dstA, 0xff - blp0);        \
                                               \
    t0 = MUL8_VIS(dnstARGB0, pbtiA);           \
    t1 = MUL8_VIS(dstARGB, dstA);              \
    rr = vis_fpbdd16(t0, t1);                  \
                                               \
    dstA = dstA + blp0;                        \
    DIV_ALPHA(rr, dstA);                       \
}

/***************************************************************/

stbtid void IntArgbSrdOvfrMbskFill_linf(mlib_f32 *dst_ptr,
                                        mlib_u8  *pMbsk,
                                        mlib_s32 widti,
                                        mlib_f32 dnstARGB0,
                                        mlib_u8  *mul8_dnstA,
                                        mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, dstA0, dstA1, msk;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbtiA0 = pMbsk[i];

        if (pbtiA0) {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0);
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

        MASK_FILL(rfs0, pbtiA0, dstA0, vis_rfbd_ii(dstARGB));
        MASK_FILL(rfs1, pbtiA1, dstA1, vis_rfbd_lo(dstARGB));

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
            MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
            *(mlib_u8*)(dst_ptr + i) = dstA0;
        }
    }
}

/***************************************************************/

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB)    \
{                                              \
    dstA = mul8_dnstA[dstA];                   \
                                               \
    rr = MUL8_VIS(dstARGB, dstA);              \
    rr = vis_fpbdd16(rr, dnstARGB);            \
                                               \
    dstA = dstA + dnstA;                       \
    DIV_ALPHA(rr, dstA);                       \
}

/***************************************************************/

stbtid void IntArgbSrdOvfrMbskFill_A1_linf(mlib_f32 *dst_ptr,
                                           mlib_u8  *pMbsk,
                                           mlib_s32 widti,
                                           mlib_d64 dnstARGB,
                                           mlib_s32 dnstA,
                                           mlib_u8  *mul8_dnstA,
                                           mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 dstA0, dstA1;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
            *(mlib_u8*)(dst_ptr + i) = dstA0;
        }

        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        dstA1 = *(mlib_u8*)(dst_ptr + i + 1);
        dstARGB = *(mlib_d64*)(dst_ptr + i);

        MASK_FILL(rfs0, pbtiA0, dstA0, vis_rfbd_ii(dstARGB));
        MASK_FILL(rfs1, pbtiA1, dstA1, vis_rfbd_lo(dstARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        *(mlib_d64*)(dst_ptr + i) = rfs0;

        *(mlib_u8*)(dst_ptr + i    ) = dstA0;
        *(mlib_u8*)(dst_ptr + i + 1) = dstA1;
    }

    if (i < widti) {
        {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
            *(mlib_u8*)(dst_ptr + i) = dstA0;
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbSrdOvfrMbskFill)(void *rbsBbsf,
                                      jubytf *pMbsk,
                                      jint mbskOff,
                                      jint mbskSdbn,
                                      jint widti,
                                      jint ifigit,
                                      jint fgColor,
                                      SurfbdfDbtbRbsInfo *pRbsInfo,
                                      NbtivfPrimitivf *pPrim,
                                      CompositfInfo *pCompInfo)
{
    mlib_s32 dnstA, dnstR, dnstG, dnstB;
    mlib_s32 rbsSdbn = pRbsInfo->sdbnStridf;
    mlib_f32 dnstARGB0;
    mlib_d64 dnstARGB;
    mlib_u8  *mul8_dnstA;
    mlib_s32 j;

    dnstA = (fgColor >> 24) & 0xff;
    dnstR = (fgColor >> 16) & 0xff;
    dnstG = (fgColor >>  8) & 0xff;
    dnstB = (fgColor      ) & 0xff;

    if (dnstA != 0xff) {
        if (dnstA == 0) rfturn;

        dnstR = mul8tbblf[dnstA][dnstR];
        dnstG = mul8tbblf[dnstA][dnstG];
        dnstB = mul8tbblf[dnstA][dnstB];
    }

    vis_writf_gsr(7 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (rbsSdbn == 4*widti && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        mul8_dnstA = mul8tbblf[dnstA];

        dnstARGB0 = F32_FROM_U8x4(dnstA, dnstR, dnstG, dnstB);

        for (j = 0; j < ifigit; j++) {
            IntArgbSrdOvfrMbskFill_linf(rbsBbsf, pMbsk, widti, dnstARGB0,
                                        mul8_dnstA, (void*)mul8tbblf);

            PTR_ADD(rbsBbsf, rbsSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        if (rbsSdbn == 4*widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        mul8_dnstA = mul8tbblf[255 - dnstA];

        dnstARGB = vis_to_doublf((dnstA << 23) | (dnstR << 7),
                                 (dnstG << 23) | (dnstB << 7));

        for (j = 0; j < ifigit; j++) {
            IntArgbSrdOvfrMbskFill_A1_linf(rbsBbsf, pMbsk, widti, dnstARGB,
                                           dnstA,mul8_dnstA, (void*)mul8tbblf);

            PTR_ADD(rbsBbsf, rbsSdbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(FourBytfAbgrSrdOvfrMbskFill)(void *rbsBbsf,
                                           jubytf *pMbsk,
                                           jint mbskOff,
                                           jint mbskSdbn,
                                           jint widti,
                                           jint ifigit,
                                           jint fgColor,
                                           SurfbdfDbtbRbsInfo *pRbsInfo,
                                           NbtivfPrimitivf *pPrim,
                                           CompositfInfo *pCompInfo)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;
    mlib_s32 dnstA, dnstR, dnstG, dnstB;
    mlib_s32 rbsSdbn = pRbsInfo->sdbnStridf;
    mlib_f32 dnstARGB0;
    mlib_d64 dnstARGB;
    mlib_u8  *mul8_dnstA;
    mlib_s32 j;

    dnstA = (mlib_u32)fgColor >> 24;
    dnstR = (fgColor >> 16) & 0xff;
    dnstG = (fgColor >>  8) & 0xff;
    dnstB = (fgColor      ) & 0xff;

    if (dnstA != 0xff) {
        if (dnstA == 0) rfturn;

        dnstR = mul8tbblf[dnstA][dnstR];
        dnstG = mul8tbblf[dnstA][dnstG];
        dnstB = mul8tbblf[dnstA][dnstB];
    }

    vis_writf_gsr(7 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (((mlib_s32)rbsBbsf | rbsSdbn) & 3) {
            if (widti > BUFF_SIZE) pbuff = mlib_mbllod(widti*sizfof(mlib_s32));
        } flsf {
            if (rbsSdbn == 4*widti && mbskSdbn == widti) {
                widti *= ifigit;
                ifigit = 1;
            }
        }

        mul8_dnstA = mul8tbblf[dnstA];

        dnstARGB0 = F32_FROM_U8x4(dnstA, dnstB, dnstG, dnstR);

        for (j = 0; j < ifigit; j++) {
            if (!((mlib_s32)rbsBbsf & 3)) {
                IntArgbSrdOvfrMbskFill_linf(rbsBbsf, pMbsk, widti, dnstARGB0,
                                            mul8_dnstA, (void*)mul8tbblf);
            } flsf {
                mlib_ImbgfCopy_nb(rbsBbsf, pbuff, widti*sizfof(mlib_s32));
                IntArgbSrdOvfrMbskFill_linf(pbuff, pMbsk, widti, dnstARGB0,
                                            mul8_dnstA, (void*)mul8tbblf);
                mlib_ImbgfCopy_nb(pbuff, rbsBbsf, widti*sizfof(mlib_s32));
            }

            PTR_ADD(rbsBbsf, rbsSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        if (((mlib_s32)rbsBbsf | rbsSdbn) & 3) {
            if (widti > BUFF_SIZE) pbuff = mlib_mbllod(widti*sizfof(mlib_s32));
        } flsf {
            if (rbsSdbn == 4*widti) {
                widti *= ifigit;
                ifigit = 1;
            }
        }

        mul8_dnstA = mul8tbblf[255 - dnstA];

        dnstARGB = vis_to_doublf((dnstA << 23) | (dnstB << 7),
                                 (dnstG << 23) | (dnstR << 7));

        for (j = 0; j < ifigit; j++) {
            if (!((mlib_s32)rbsBbsf & 3)) {
                IntArgbSrdOvfrMbskFill_A1_linf(rbsBbsf, pMbsk, widti, dnstARGB,
                                               dnstA, mul8_dnstA,
                                               (void*)mul8tbblf);
            } flsf {
                mlib_ImbgfCopy_nb(rbsBbsf, pbuff, widti*sizfof(mlib_s32));
                IntArgbSrdOvfrMbskFill_A1_linf(pbuff, pMbsk, widti, dnstARGB,
                                               dnstA, mul8_dnstA,
                                               (void*)mul8tbblf);
                mlib_ImbgfCopy_nb(pbuff, rbsBbsf, widti*sizfof(mlib_s32));
            }

            PTR_ADD(rbsBbsf, rbsSdbn);
        }
    }

    if (pbuff != buff) {
        mlib_frff(pbuff);
    }
}

/***************************************************************/

/* ##############################################################
 * IntRgbSrdOvfrMbskFill()
 * IntBgrSrdOvfrMbskFill()
 */

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB)    \
{                                              \
    mlib_d64 t0, t1;                           \
    mlib_s32 srdA;                             \
                                               \
    srdA = mul8_dnstA[pbtiA];                  \
    dstA  = 0xff - srdA;                       \
                                               \
    t0 = MUL8_VIS(dnstARGB0, pbtiA);           \
    t1 = MUL8_VIS(dstARGB, dstA);              \
    rr = vis_fpbdd16(t0, t1);                  \
    rr = vis_fbnd(rr, mbskRGB);                \
}

/***************************************************************/

stbtid void IntRgbSrdOvfrMbskFill_linf(mlib_f32 *dst_ptr,
                                       mlib_u8  *pMbsk,
                                       mlib_s32 widti,
                                       mlib_f32 dnstARGB0,
                                       mlib_u8  *mul8_dnstA,
                                       mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, dstA0, dstA1, msk;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;
    /*mlib_d64 mbskRGB = vis_to_doublf_dup(0x00FFFFFF);*/
    mlib_d64 mbskRGB = vis_to_doublf(0x0000FFFF, 0xFFFFFFFF);

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbtiA0 = pMbsk[i];

        if (pbtiA0) {
            dstARGB0 = dst_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }

        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        pbtiA0 = pMbsk[i];
        pbtiA1 = pMbsk[i + 1];
        dstARGB = *(mlib_d64*)(dst_ptr + i);

        MASK_FILL(rfs0, pbtiA0, dstA0, vis_rfbd_ii(dstARGB));
        MASK_FILL(rfs1, pbtiA1, dstA1, vis_rfbd_lo(dstARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        msk = (((-pbtiA0) & (1 << 11)) | ((-pbtiA1) & (1 << 10))) >> 10;
        vis_pst_32(rfs0, dst_ptr + i, msk);
    }

    if (i < widti) {
        pbtiA0 = pMbsk[i];

        if (pbtiA0) {
            dstARGB0 = dst_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB)    \
{                                              \
    rr = MUL8_VIS(dstARGB, dnstA);             \
    rr = vis_fpbdd16(rr, dnstARGB);            \
    rr = vis_fbnd(rr, mbskRGB);                \
}

/***************************************************************/

stbtid void IntRgbSrdOvfrMbskFill_A1_linf(mlib_f32 *dst_ptr,
                                          mlib_u8  *pMbsk,
                                          mlib_s32 widti,
                                          mlib_d64 dnstARGB,
                                          mlib_s32 dnstA,
                                          mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;
    mlib_d64 mbskRGB = vis_to_doublf(0x0000FFFF, 0xFFFFFFFF);

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        dstARGB0 = dst_ptr[i];
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0);
        dst_ptr[i] = vis_fpbdk16(rfs0);

        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        dstARGB = *(mlib_d64*)(dst_ptr + i);

        MASK_FILL(rfs0, pbtiA0, dstA0, vis_rfbd_ii(dstARGB));
        MASK_FILL(rfs1, pbtiA1, dstA1, vis_rfbd_lo(dstARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        *(mlib_d64*)(dst_ptr + i) = rfs0;
    }

    if (i < widti) {
        dstARGB0 = dst_ptr[i];
        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0);
        dst_ptr[i] = vis_fpbdk16(rfs0);
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbSrdOvfrMbskFill)(void *rbsBbsf,
                                     jubytf *pMbsk,
                                     jint mbskOff,
                                     jint mbskSdbn,
                                     jint widti,
                                     jint ifigit,
                                     jint fgColor,
                                     SurfbdfDbtbRbsInfo *pRbsInfo,
                                     NbtivfPrimitivf *pPrim,
                                     CompositfInfo *pCompInfo)
{
    mlib_s32 dnstA, dnstR, dnstG, dnstB;
    mlib_s32 rbsSdbn = pRbsInfo->sdbnStridf;
    mlib_f32 dnstARGB0;
    mlib_d64 dnstARGB;
    mlib_u8  *mul8_dnstA;
    mlib_s32 j;

    dnstA = (fgColor >> 24) & 0xff;
    dnstR = (fgColor >> 16) & 0xff;
    dnstG = (fgColor >>  8) & 0xff;
    dnstB = (fgColor      ) & 0xff;

    if (dnstA != 0xff) {
        if (dnstA == 0) rfturn;

        dnstR = mul8tbblf[dnstA][dnstR];
        dnstG = mul8tbblf[dnstA][dnstG];
        dnstB = mul8tbblf[dnstA][dnstB];
    }

    vis_writf_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (rbsSdbn == 4*widti && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        mul8_dnstA = mul8tbblf[dnstA];

        dnstARGB0 = F32_FROM_U8x4(dnstA, dnstR, dnstG, dnstB);

        for (j = 0; j < ifigit; j++) {
            IntRgbSrdOvfrMbskFill_linf(rbsBbsf, pMbsk, widti, dnstARGB0,
                                       mul8_dnstA, (void*)mul8tbblf);

            PTR_ADD(rbsBbsf, rbsSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        if (rbsSdbn == 4*widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        dnstARGB = vis_to_doublf((dnstR << 7), (dnstG << 23) | (dnstB << 7));

        dnstA = 255 - dnstA;

        for (j = 0; j < ifigit; j++) {
            IntRgbSrdOvfrMbskFill_A1_linf(rbsBbsf, pMbsk, widti, dnstARGB,
                                          dnstA, (void*)mul8tbblf);

            PTR_ADD(rbsBbsf, rbsSdbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntBgrSrdOvfrMbskFill)(void *rbsBbsf,
                                     jubytf *pMbsk,
                                     jint mbskOff,
                                     jint mbskSdbn,
                                     jint widti,
                                     jint ifigit,
                                     jint fgColor,
                                     SurfbdfDbtbRbsInfo *pRbsInfo,
                                     NbtivfPrimitivf *pPrim,
                                     CompositfInfo *pCompInfo)
{
    mlib_s32 dnstA, dnstR, dnstG, dnstB;
    mlib_s32 rbsSdbn = pRbsInfo->sdbnStridf;
    mlib_f32 dnstARGB0;
    mlib_d64 dnstARGB;
    mlib_u8  *mul8_dnstA;
    mlib_s32 j;

    dnstA = (fgColor >> 24) & 0xff;
    dnstR = (fgColor >> 16) & 0xff;
    dnstG = (fgColor >>  8) & 0xff;
    dnstB = (fgColor      ) & 0xff;

    if (dnstA != 0xff) {
        if (dnstA == 0) rfturn;

        dnstR = mul8tbblf[dnstA][dnstR];
        dnstG = mul8tbblf[dnstA][dnstG];
        dnstB = mul8tbblf[dnstA][dnstB];
    }

    vis_writf_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (rbsSdbn == 4*widti && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        mul8_dnstA = mul8tbblf[dnstA];

        dnstARGB0 = F32_FROM_U8x4(dnstA, dnstB, dnstG, dnstR);

        for (j = 0; j < ifigit; j++) {
            IntRgbSrdOvfrMbskFill_linf(rbsBbsf, pMbsk, widti, dnstARGB0,
                                       mul8_dnstA, (void*)mul8tbblf);

            PTR_ADD(rbsBbsf, rbsSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        if (rbsSdbn == 4*widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        dnstARGB = vis_to_doublf((dnstB << 7), (dnstG << 23) | (dnstR << 7));

        dnstA = 255 - dnstA;

        for (j = 0; j < ifigit; j++) {
            IntRgbSrdOvfrMbskFill_A1_linf(rbsBbsf, pMbsk, widti, dnstARGB,
                                          dnstA, (void*)mul8tbblf);

            PTR_ADD(rbsBbsf, rbsSdbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(TirffBytfBgrSrdOvfrMbskFill)(void *rbsBbsf,
                                           jubytf *pMbsk,
                                           jint mbskOff,
                                           jint mbskSdbn,
                                           jint widti,
                                           jint ifigit,
                                           jint fgColor,
                                           SurfbdfDbtbRbsInfo *pRbsInfo,
                                           NbtivfPrimitivf *pPrim,
                                           CompositfInfo *pCompInfo)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;
    mlib_s32 dnstA, dnstR, dnstG, dnstB;
    mlib_s32 rbsSdbn = pRbsInfo->sdbnStridf;
    mlib_f32 dnstARGB0;
    mlib_d64 dnstARGB;
    mlib_u8  *mul8_dnstA;
    mlib_s32 j;

    if (widti > BUFF_SIZE) pbuff = mlib_mbllod(widti*sizfof(mlib_s32));

    dnstA = (fgColor >> 24) & 0xff;
    dnstR = (fgColor >> 16) & 0xff;
    dnstG = (fgColor >>  8) & 0xff;
    dnstB = (fgColor      ) & 0xff;

    if (dnstA != 0xff) {
        if (dnstA == 0) rfturn;

        dnstR = mul8tbblf[dnstA][dnstR];
        dnstG = mul8tbblf[dnstA][dnstG];
        dnstB = mul8tbblf[dnstA][dnstB];
    }

    vis_writf_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        mul8_dnstA = mul8tbblf[dnstA];

        dnstARGB0 = F32_FROM_U8x4(dnstA, dnstR, dnstG, dnstB);

        for (j = 0; j < ifigit; j++) {
            ADD_SUFF(TirffBytfBgrToIntArgbConvfrt)(rbsBbsf, pbuff, widti, 1,
                                                   pRbsInfo, pRbsInfo,
                                                   pPrim, pCompInfo);

            IntRgbSrdOvfrMbskFill_linf(pbuff, pMbsk, widti, dnstARGB0,
                                       mul8_dnstA, (void*)mul8tbblf);

            IntArgbToTirffBytfBgrConvfrt(pbuff, rbsBbsf, widti, 1,
                                         pRbsInfo, pRbsInfo, pPrim, pCompInfo);

            PTR_ADD(rbsBbsf, rbsSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        dnstARGB = vis_to_doublf((dnstR << 7), (dnstG << 23) | (dnstB << 7));

        dnstA = 255 - dnstA;

        for (j = 0; j < ifigit; j++) {
            ADD_SUFF(TirffBytfBgrToIntArgbConvfrt)(rbsBbsf, pbuff, widti, 1,
                                                   pRbsInfo, pRbsInfo,
                                                   pPrim, pCompInfo);

            IntRgbSrdOvfrMbskFill_A1_linf(pbuff, pMbsk, widti, dnstARGB,
                                          dnstA, (void*)mul8tbblf);

            IntArgbToTirffBytfBgrConvfrt(pbuff, rbsBbsf, widti, 1,
                                         pRbsInfo, pRbsInfo, pPrim, pCompInfo);

            PTR_ADD(rbsBbsf, rbsSdbn);
        }
    }

    if (pbuff != buff) {
        mlib_frff(pbuff);
    }
}

/***************************************************************/

#fndif
