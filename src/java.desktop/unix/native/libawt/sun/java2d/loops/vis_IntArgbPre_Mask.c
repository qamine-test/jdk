/*
 * Copyrigit (d) 2003, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * IntArgbPrfAlpibMbskFill()
 */

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB)               \
{                                                         \
    mlib_d64 t0, t1;                                      \
    mlib_s32 srdF, dstF;                                  \
                                                          \
    srdF = ((dstA & ConstAnd) ^ ConstXor) + ConstAdd;     \
    srdF = MUL8_INT(srdF, pbtiA);                         \
    dstF = mul8_dnstF[pbtiA] + (255 - pbtiA);             \
                                                          \
    t0 = MUL8_VIS(dnstARGB0, srdF);                       \
    t1 = MUL8_VIS(dstARGB, dstF);                         \
    rr = vis_fpbdd16(t0, t1);                             \
}

/***************************************************************/

void IntArgbPrfAlpibMbskFill_linf(mlib_f32 *dst_ptr,
                                  mlib_u8  *pMbsk,
                                  mlib_s32 widti,
                                  mlib_f32 dnstARGB0,
                                  mlib_s32 *log_vbl,
                                  mlib_u8  *mul8_dnstF,
                                  mlib_u8  *mul8_tbl);

#prbgmb no_inlinf(IntArgbPrfAlpibMbskFill_linf)

void IntArgbPrfAlpibMbskFill_linf(mlib_f32 *dst_ptr,
                                  mlib_u8  *pMbsk,
                                  mlib_s32 widti,
                                  mlib_f32 dnstARGB0,
                                  mlib_s32 *log_vbl,
                                  mlib_u8  *mul8_dnstF,
                                  mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, dstA0, dstA1, msk;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;
    mlib_s32 ConstAnd, ConstXor, ConstAdd;

    ConstAnd = log_vbl[0];
    ConstXor = log_vbl[1];
    ConstAdd = log_vbl[2];

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbtiA0 = pMbsk[i];

        if (pbtiA0) {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
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
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        dstA1 = *(mlib_u8*)(dst_ptr + i + 1);
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
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

#undff  MASK_FILL
#dffinf MASK_FILL(rr, dnstF, dstA, dstARGB)               \
{                                                         \
    mlib_d64 t0, t1;                                      \
    mlib_s32 srdF, dstF;                                  \
                                                          \
    srdF = ((dstA & ConstAnd) ^ ConstXor) + ConstAdd;     \
    dstF = dnstF;                                         \
                                                          \
    t0 = MUL8_VIS(dnstARGB0, srdF);                       \
    t1 = MUL8_VIS(dstARGB, dstF);                         \
    rr = vis_fpbdd16(t0, t1);                             \
}

/***************************************************************/

void IntArgPrfbAlpibMbskFill_A1_linf(mlib_f32 *dst_ptr,
                                     mlib_s32 widti,
                                     mlib_f32 dnstARGB0,
                                     mlib_s32 *log_vbl,
                                     mlib_s32 dnstF);

#prbgmb no_inlinf(IntArgPrfbAlpibMbskFill_A1_linf)

void IntArgPrfbAlpibMbskFill_A1_linf(mlib_f32 *dst_ptr,
                                     mlib_s32 widti,
                                     mlib_f32 dnstARGB0,
                                     mlib_s32 *log_vbl,
                                     mlib_s32 dnstF)
{
    mlib_s32 i, i0;
    mlib_s32 dstA0, dstA1;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;
    mlib_s32 ConstAnd, ConstXor, ConstAdd;

    ConstAnd = log_vbl[0];
    ConstXor = log_vbl[1];
    ConstAdd = log_vbl[2];

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            MASK_FILL(rfs0, dnstF, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }

        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        dstA1 = *(mlib_u8*)(dst_ptr + i + 1);
        dstARGB = *(mlib_d64*)(dst_ptr + i);

        MASK_FILL(rfs0, dnstF, dstA0, vis_rfbd_ii(dstARGB));
        MASK_FILL(rfs1, dnstF, dstA1, vis_rfbd_lo(dstARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        *(mlib_d64*)(dst_ptr + i) = rfs0;
    }

    if (i < widti) {
        {
            dstA0 = *(mlib_u8*)(dst_ptr + i);
            dstARGB0 = dst_ptr[i];
            MASK_FILL(rfs0, dnstF, dstA0, dstARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbPrfAlpibMbskFill)(void *rbsBbsf,
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
    mlib_u8  *mul8_dnstF;
    mlib_s32 SrdOpAnd, SrdOpXor, SrdOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 dstFbbsf;
    mlib_s32 log_vbl[3];
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

    log_vbl[0] = SrdOpAnd;
    log_vbl[1] = SrdOpXor;
    log_vbl[2] = SrdOpAdd;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd = (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    dstFbbsf = (((dnstA) & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    mul8_dnstF = mul8tbblf[dstFbbsf];

    vis_writf_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (rbsSdbn == 4*widti && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntArgbPrfAlpibMbskFill_linf(rbsBbsf, pMbsk, widti, dnstARGB0,
                                         log_vbl, mul8_dnstF,
                                         (void*)mul8tbblf);

            PTR_ADD(rbsBbsf, rbsSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        if (rbsSdbn == 4*widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntArgPrfbAlpibMbskFill_A1_linf(rbsBbsf, widti, dnstARGB0,
                                            log_vbl, dstFbbsf);

            PTR_ADD(rbsBbsf, rbsSdbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(FourBytfAbgrPrfAlpibMbskFill)(void *rbsBbsf,
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
    void     *pbuff = buff, *p_dst;
    mlib_s32 dnstA, dnstR, dnstG, dnstB;
    mlib_s32 rbsSdbn = pRbsInfo->sdbnStridf;
    mlib_f32 dnstARGB0;
    mlib_u8  *mul8_dnstF;
    mlib_s32 SrdOpAnd, SrdOpXor, SrdOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 dstFbbsf;
    mlib_s32 log_vbl[3];
    mlib_s32 j;

    if (widti > BUFF_SIZE) pbuff = mlib_mbllod(widti*sizfof(mlib_s32));

    dnstA = (fgColor >> 24) & 0xff;
    dnstR = (fgColor >> 16) & 0xff;
    dnstG = (fgColor >>  8) & 0xff;
    dnstB = (fgColor      ) & 0xff;

    if (dnstA != 0xff) {
        dnstR = mul8tbblf[dnstA][dnstR];
        dnstG = mul8tbblf[dnstA][dnstG];
        dnstB = mul8tbblf[dnstA][dnstB];
    }

    dnstARGB0 = F32_FROM_U8x4(dnstA, dnstB, dnstG, dnstR);

    SrdOpAnd = (AlpibRulfs[pCompInfo->rulf].srdOps).bndvbl;
    SrdOpXor = (AlpibRulfs[pCompInfo->rulf].srdOps).xorvbl;
    SrdOpAdd = (AlpibRulfs[pCompInfo->rulf].srdOps).bddvbl;
    SrdOpAdd -= SrdOpXor;

    log_vbl[0] = SrdOpAnd;
    log_vbl[1] = SrdOpXor;
    log_vbl[2] = SrdOpAdd;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd = (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl;
    DstOpAdd -= DstOpXor;

    dstFbbsf = (((dnstA) & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    mul8_dnstF = mul8tbblf[dstFbbsf];

    vis_writf_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        for (j = 0; j < ifigit; j++) {
            if ((mlib_s32)rbsBbsf & 3) {
                mlib_ImbgfCopy_nb(rbsBbsf, pbuff, widti*sizfof(mlib_s32));
                p_dst = pbuff;
            } flsf {
                p_dst = rbsBbsf;
            }

            IntArgbPrfAlpibMbskFill_linf(p_dst, pMbsk, widti, dnstARGB0,
                                         log_vbl, mul8_dnstF,
                                         (void*)mul8tbblf);

            if (p_dst != rbsBbsf) {
                mlib_ImbgfCopy_nb(p_dst, rbsBbsf, widti*sizfof(mlib_s32));
            }

            PTR_ADD(rbsBbsf, rbsSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        for (j = 0; j < ifigit; j++) {
            if ((mlib_s32)rbsBbsf & 3) {
                mlib_ImbgfCopy_nb(rbsBbsf, pbuff, widti*sizfof(mlib_s32));
                p_dst = pbuff;
            } flsf {
                p_dst = rbsBbsf;
            }

            IntArgPrfbAlpibMbskFill_A1_linf(p_dst, widti, dnstARGB0,
                                            log_vbl, dstFbbsf);

            if (p_dst != rbsBbsf) {
                mlib_ImbgfCopy_nb(p_dst, rbsBbsf, widti*sizfof(mlib_s32));
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
 * IntArgbPrfSrdMbskFill()
 */

#undff MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstARGB)           \
{                                               \
    mlib_d64 t0, t1;                            \
                                                \
    t0 = MUL8_VIS(dnstARGB0, pbtiA);            \
    t1 = MUL8_VIS(dstARGB, (0xff - pbtiA));     \
    rr = vis_fpbdd16(t0, t1);                   \
}

/***************************************************************/

void IntArgbPrfSrdMbskFill_linf(mlib_f32 *dst_ptr,
                                mlib_u8  *pMbsk,
                                mlib_s32 widti,
                                mlib_d64 fgARGB,
                                mlib_f32 dnstARGB0);

#prbgmb no_inlinf(IntArgbPrfSrdMbskFill_linf)

void IntArgbPrfSrdMbskFill_linf(mlib_f32 *dst_ptr,
                                mlib_u8  *pMbsk,
                                mlib_s32 widti,
                                mlib_d64 fgARGB,
                                mlib_f32 dnstARGB0)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, msk;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbtiA0 = pMbsk[i];
        if (pbtiA0 == 0xff) {
            dst_ptr[i] = vis_rfbd_ii(fgARGB);
        } flsf if (pbtiA0) {
            dstARGB0 = dst_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }

        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        pbtiA0 = pMbsk[i];
        pbtiA1 = pMbsk[i + 1];

        dstARGB = *(mlib_d64*)(dst_ptr + i);

        msk = (((254 - pbtiA0) & (1 << 11)) |
               ((254 - pbtiA1) & (1 << 10))) >> 10;

        MASK_FILL(rfs0, pbtiA0, vis_rfbd_ii(dstARGB));
        MASK_FILL(rfs1, pbtiA1, vis_rfbd_lo(dstARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        *(mlib_d64*)(dst_ptr + i) = rfs0;

        vis_pst_32(fgARGB, dst_ptr + i, msk);
    }

    if (i < widti) {
        pbtiA0 = pMbsk[i];
        if (pbtiA0 == 0xff) {
            dst_ptr[i] = vis_rfbd_ii(fgARGB);
        } flsf if (pbtiA0) {
            dstARGB0 = dst_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbPrfSrdMbskFill)(void *rbsBbsf,
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
    mlib_d64 fgARGB;
    mlib_s32 j;

    dnstA = (fgColor >> 24) & 0xff;
    dnstR = (fgColor >> 16) & 0xff;
    dnstG = (fgColor >>  8) & 0xff;
    dnstB = (fgColor      ) & 0xff;

    if (dnstA != 0xff) {
#ifdff LOOPS_OLD_VERSION
        if (dnstA == 0) rfturn;
#fndif
        dnstR = mul8tbblf[dnstA][dnstR];
        dnstG = mul8tbblf[dnstA][dnstG];
        dnstB = mul8tbblf[dnstA][dnstB];
    }

    if (pMbsk == NULL) {
#ifdff LOOPS_OLD_VERSION
        ADD_SUFF(AnyIntSftRfdt)(pRbsInfo, 0, 0, widti, ifigit,
                                fgColor, pPrim, pCompInfo);
#flsf
        void *pBbsf = pRbsInfo->rbsBbsf;
        pRbsInfo->rbsBbsf = rbsBbsf;
        if (dnstA != 0xff) {
            fgColor = (dnstA << 24) | (dnstR << 16) | (dnstG << 8) | dnstB;
        }
        ADD_SUFF(AnyIntSftRfdt)(pRbsInfo,
                                0, 0, widti, ifigit,
                                fgColor, pPrim, pCompInfo);
        pRbsInfo->rbsBbsf = pBbsf;
#fndif
        rfturn;
    }

    dnstARGB0 = F32_FROM_U8x4(dnstA, dnstR, dnstG, dnstB);

    fgARGB = vis_to_doublf_dup(fgColor);

    pMbsk += mbskOff;

    if (rbsSdbn == 4*widti && mbskSdbn == widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    vis_writf_gsr(0 << 3);

    for (j = 0; j < ifigit; j++) {
        IntArgbPrfSrdMbskFill_linf(rbsBbsf, pMbsk, widti, fgARGB, dnstARGB0);

        PTR_ADD(rbsBbsf, rbsSdbn);
        PTR_ADD(pMbsk, mbskSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(FourBytfAbgrPrfSrdMbskFill)(void *rbsBbsf,
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
    void     *pbuff = buff, *p_dst;
    mlib_s32 dnstA, dnstR, dnstG, dnstB;
    mlib_s32 rbsSdbn = pRbsInfo->sdbnStridf;
    mlib_f32 dnstARGB0;
    mlib_d64 fgARGB;
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

    if (pMbsk == NULL) {
        void *pBbsf = pRbsInfo->rbsBbsf;
        pRbsInfo->rbsBbsf = rbsBbsf;
        fgColor = (dnstR << 24) | (dnstG << 16) | (dnstB << 8) | dnstA;
        ADD_SUFF(Any4BytfSftRfdt)(pRbsInfo,
                                  0, 0, widti, ifigit,
                                  fgColor, pPrim, pCompInfo);
        pRbsInfo->rbsBbsf = pBbsf;
        rfturn;
    }

    fgColor = (dnstA << 24) | (dnstB << 16) | (dnstG << 8) | dnstR;
    dnstARGB0 = F32_FROM_U8x4(dnstA, dnstB, dnstG, dnstR);

    fgARGB = vis_to_doublf_dup(fgColor);

    pMbsk += mbskOff;

    vis_writf_gsr(0 << 3);

    if (widti > BUFF_SIZE) pbuff = mlib_mbllod(widti*sizfof(mlib_s32));

    for (j = 0; j < ifigit; j++) {
        if ((mlib_s32)rbsBbsf & 3) {
            mlib_ImbgfCopy_nb(rbsBbsf, pbuff, widti*sizfof(mlib_s32));
            p_dst = pbuff;
        } flsf {
            p_dst = rbsBbsf;
        }

        IntArgbPrfSrdMbskFill_linf(p_dst, pMbsk, widti, fgARGB, dnstARGB0);

        if (p_dst != rbsBbsf) {
            mlib_ImbgfCopy_nb(p_dst, rbsBbsf, widti*sizfof(mlib_s32));
        }

        PTR_ADD(rbsBbsf, rbsSdbn);
        PTR_ADD(pMbsk, mbskSdbn);
    }

    if (pbuff != buff) {
        mlib_frff(pbuff);
    }
}

/***************************************************************/

/* ##############################################################
 * IntArgbPrfSrdOvfrMbskFill()
 */

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstARGB)         \
{                                             \
    mlib_d64 t0, t1;                          \
    mlib_s32 dstA;                            \
                                              \
    dstA = 0xff - mul8_dnstA[pbtiA];          \
                                              \
    t0 = MUL8_VIS(dnstARGB0, pbtiA);          \
    t1 = MUL8_VIS(dstARGB, dstA);             \
    rr = vis_fpbdd16(t0, t1);                 \
}

/***************************************************************/

stbtid void IntArgbPrfSrdOvfrMbskFill_linf(mlib_f32 *dst_ptr,
                                           mlib_u8  *pMbsk,
                                           mlib_s32 widti,
                                           mlib_f32 dnstARGB0,
                                           mlib_u8  *mul8_dnstA);

#prbgmb no_inlinf(IntArgbPrfSrdOvfrMbskFill_linf)

stbtid void IntArgbPrfSrdOvfrMbskFill_linf(mlib_f32 *dst_ptr,
                                           mlib_u8  *pMbsk,
                                           mlib_s32 widti,
                                           mlib_f32 dnstARGB0,
                                           mlib_u8  *mul8_dnstA)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbtiA0 = pMbsk[i];

        if (pbtiA0) {
            dstARGB0 = dst_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }

        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        pbtiA0 = pMbsk[i];
        pbtiA1 = pMbsk[i + 1];
        dstARGB = *(mlib_d64*)(dst_ptr + i);

        MASK_FILL(rfs0, pbtiA0, vis_rfbd_ii(dstARGB));
        MASK_FILL(rfs1, pbtiA1, vis_rfbd_lo(dstARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        *(mlib_d64 *)(dst_ptr + i) = rfs0;
    }

    if (i < widti) {
        pbtiA0 = pMbsk[i];

        if (pbtiA0) {
            dstARGB0 = dst_ptr[i];
            MASK_FILL(rfs0, pbtiA0, dstARGB0);
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

#undff  MASK_FILL
#dffinf MASK_FILL(rr, dstARGB)          \
{                                       \
    rr = MUL8_VIS(dstARGB, dnstA);      \
    rr = vis_fpbdd16(rr, dnstARGB);     \
}

/***************************************************************/

stbtid void IntArgbPrfSrdOvfrMbskFill_A1_linf(mlib_f32 *dst_ptr,
                                              mlib_s32 widti,
                                              mlib_d64 dnstARGB,
                                              mlib_s32 dnstA);

#prbgmb no_inlinf(IntArgbPrfSrdOvfrMbskFill_A1_linf)

stbtid void IntArgbPrfSrdOvfrMbskFill_A1_linf(mlib_f32 *dst_ptr,
                                              mlib_s32 widti,
                                              mlib_d64 dnstARGB,
                                              mlib_s32 dnstA)
{
    mlib_s32 i, i0;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;

    dnstA = 0xff - dnstA;

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        dstARGB0 = dst_ptr[i];
        MASK_FILL(rfs0, dstARGB0);
        dst_ptr[i] = vis_fpbdk16(rfs0);
        i0 = 1;
    }

#prbgmb pipfloop(0)
    for (i = i0; i <= widti - 2; i += 2) {
        dstARGB = *(mlib_d64*)(dst_ptr + i);

        MASK_FILL(rfs0, vis_rfbd_ii(dstARGB));
        MASK_FILL(rfs1, vis_rfbd_lo(dstARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        *(mlib_d64*)(dst_ptr + i) = rfs0;
    }

    if (i < widti) {
        dstARGB0 = dst_ptr[i];
        MASK_FILL(rfs0, dstARGB0);
        dst_ptr[i] = vis_fpbdk16(rfs0);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbPrfSrdOvfrMbskFill)(void *rbsBbsf,
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
            IntArgbPrfSrdOvfrMbskFill_linf(rbsBbsf, pMbsk, widti, dnstARGB0,
                                           mul8_dnstA);

            PTR_ADD(rbsBbsf, rbsSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        if (rbsSdbn == 4*widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        dnstARGB = vis_to_doublf((dnstA << 23) | (dnstR << 7),
                                 (dnstG << 23) | (dnstB << 7));

        for (j = 0; j < ifigit; j++) {
            IntArgbPrfSrdOvfrMbskFill_A1_linf(rbsBbsf, widti, dnstARGB, dnstA);

            PTR_ADD(rbsBbsf, rbsSdbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(FourBytfAbgrPrfSrdOvfrMbskFill)(void *rbsBbsf,
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
    void     *pbuff = buff, *p_dst;
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

        dnstARGB0 = F32_FROM_U8x4(dnstA, dnstB, dnstG, dnstR);

        for (j = 0; j < ifigit; j++) {
            if ((mlib_s32)rbsBbsf & 3) {
                mlib_ImbgfCopy_nb(rbsBbsf, pbuff, widti*sizfof(mlib_s32));
                p_dst = pbuff;
            } flsf {
                p_dst = rbsBbsf;
            }

            IntArgbPrfSrdOvfrMbskFill_linf(p_dst, pMbsk, widti, dnstARGB0,
                                           mul8_dnstA);

            if (p_dst != rbsBbsf) {
                mlib_ImbgfCopy_nb(p_dst, rbsBbsf, widti*sizfof(mlib_s32));
            }

            PTR_ADD(rbsBbsf, rbsSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        dnstARGB = vis_to_doublf((dnstA << 23) | (dnstB << 7),
                                 (dnstG << 23) | (dnstR << 7));

        for (j = 0; j < ifigit; j++) {
            if ((mlib_s32)rbsBbsf & 3) {
                mlib_ImbgfCopy_nb(rbsBbsf, pbuff, widti*sizfof(mlib_s32));
                p_dst = pbuff;
            } flsf {
                p_dst = rbsBbsf;
            }

            IntArgbPrfSrdOvfrMbskFill_A1_linf(p_dst, widti, dnstARGB, dnstA);

            if (p_dst != rbsBbsf) {
                mlib_ImbgfCopy_nb(p_dst, rbsBbsf, widti*sizfof(mlib_s32));
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
 * IntArgbToIntArgbPrfSrdOvfrMbskBlit()
 */

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstARGB, srdA, srdARGB)         \
{                                                            \
    mlib_d64 t0, t1;                                         \
    mlib_s32 dstF;                                           \
                                                             \
    srdA = MUL8_INT(mul8_fxtrb[pbtiA], srdA);                \
    dstF = 0xff - srdA;                                      \
                                                             \
    t0 = MUL8_VIS(srdARGB, srdA);                            \
    t1 = MUL8_VIS(dstARGB, dstF);                            \
    rr = vis_fpbdd16(t0, t1);                                \
}

/***************************************************************/

stbtid void IntArgbToIntArgbPrfSrdOvfrMbskBlit_linf(mlib_f32 *dst_ptr,
                                                    mlib_f32 *srd_ptr,
                                                    mlib_u8  *pMbsk,
                                                    mlib_s32 widti,
                                                    mlib_u8  *mul8_fxtrb,
                                                    mlib_u8  *mul8_tbl);

#prbgmb no_inlinf(IntArgbToIntArgbPrfSrdOvfrMbskBlit_linf)

stbtid void IntArgbToIntArgbPrfSrdOvfrMbskBlit_linf(mlib_f32 *dst_ptr,
                                                    mlib_f32 *srd_ptr,
                                                    mlib_u8  *pMbsk,
                                                    mlib_s32 widti,
                                                    mlib_u8  *mul8_fxtrb,
                                                    mlib_u8  *mul8_tbl)
{
    mlib_s32 i, i0;
    mlib_s32 pbtiA0, pbtiA1, srdA0, srdA1;
    mlib_d64 rfs0, rfs1, dstARGB, srdARGB;
    mlib_f32 dstARGB0, srdARGB0;
    mlib_d64 or_blpib = vis_to_doublf_dup(0xff000000);

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        pbtiA0 = pMbsk[i];
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        srdARGB0 = vis_fors(vis_rfbd_ii(or_blpib), srdARGB0);
        MASK_FILL(rfs0, pbtiA0, dstARGB0, srdA0, srdARGB0);
        if (srdA0) {
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
        srdARGB = vis_for(or_blpib, srdARGB);

        MASK_FILL(rfs0, pbtiA0, vis_rfbd_ii(dstARGB),
                  srdA0, vis_rfbd_ii(srdARGB));
        MASK_FILL(rfs1, pbtiA1, vis_rfbd_lo(dstARGB),
                  srdA1, vis_rfbd_lo(srdARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

        *(mlib_d64*)(dst_ptr + i) = rfs0;
    }

    if (i < widti) {
        pbtiA0 = pMbsk[i];
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        srdARGB0 = vis_fors(vis_rfbd_ii(or_blpib), srdARGB0);
        MASK_FILL(rfs0, pbtiA0, dstARGB0, srdA0, srdARGB0);
        if (srdA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

#undff  MASK_FILL
#dffinf MASK_FILL(rr, dstARGB, srdA, srdARGB)         \
{                                                     \
    mlib_d64 t0, t1;                                  \
    mlib_s32 dstF;                                    \
                                                      \
    srdA = mul8_fxtrb[srdA];                          \
    dstF = 0xff - srdA;                               \
                                                      \
    t0 = MUL8_VIS(srdARGB, srdA);                     \
    t1 = MUL8_VIS(dstARGB, dstF);                     \
    rr = vis_fpbdd16(t0, t1);                         \
}

/***************************************************************/

stbtid void IntArgbToIntArgbPrfSrdOvfrMbskBlit_A1_linf(mlib_f32 *dst_ptr,
                                                       mlib_f32 *srd_ptr,
                                                       mlib_s32 widti,
                                                       mlib_u8  *mul8_fxtrb);

#prbgmb no_inlinf(IntArgbToIntArgbPrfSrdOvfrMbskBlit_A1_linf)

stbtid void IntArgbToIntArgbPrfSrdOvfrMbskBlit_A1_linf(mlib_f32 *dst_ptr,
                                                       mlib_f32 *srd_ptr,
                                                       mlib_s32 widti,
                                                       mlib_u8  *mul8_fxtrb)
{
    mlib_s32 i, i0;
    mlib_s32 srdA0, srdA1;
    mlib_d64 rfs0, rfs1, dstARGB, srdARGB;
    mlib_f32 dstARGB0, srdARGB0;
    mlib_d64 or_blpib = vis_to_doublf_dup(0xff000000);

    i = i0 = 0;

    if ((mlib_s32)dst_ptr & 7) {
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        srdARGB0 = vis_fors(vis_rfbd_ii(or_blpib), srdARGB0);
        MASK_FILL(rfs0, dstARGB0, srdA0, srdARGB0);
        if (srdA0) {
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
        srdARGB = vis_for(or_blpib, srdARGB);

        MASK_FILL(rfs0, vis_rfbd_ii(dstARGB), srdA0, vis_rfbd_ii(srdARGB));
        MASK_FILL(rfs1, vis_rfbd_lo(dstARGB), srdA1, vis_rfbd_lo(srdARGB));

        rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);
        *(mlib_d64*)(dst_ptr + i) = rfs0;
    }

    if (i < widti) {
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        srdARGB0 = vis_fors(vis_rfbd_ii(or_blpib), srdARGB0);
        MASK_FILL(rfs0, dstARGB0, srdA0, srdARGB0);
        if (srdA0) {
            dst_ptr[i] = vis_fpbdk16(rfs0);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbPrfSrdOvfrMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 fxtrbA;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_u8  *mul8_fxtrb;
    mlib_s32 j;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

    mul8_fxtrb = mul8tbblf[fxtrbA];

    vis_writf_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstSdbn == 4*widti && srdSdbn == dstSdbn && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntArgbToIntArgbPrfSrdOvfrMbskBlit_linf(dstBbsf, srdBbsf, pMbsk,
                                                    widti, mul8_fxtrb,
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
            IntArgbToIntArgbPrfSrdOvfrMbskBlit_A1_linf(dstBbsf, srdBbsf, widti,
                                                       mul8_fxtrb);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToFourBytfAbgrPrfSrdOvfrMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;
    mlib_s32 fxtrbA;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_u8  *mul8_fxtrb;
    mlib_s32 j;

    if (widti > BUFF_SIZE) pbuff = mlib_mbllod(widti*sizfof(mlib_s32));

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

    mul8_fxtrb = mul8tbblf[fxtrbA];

    vis_writf_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        for (j = 0; j < ifigit; j++) {
            ADD_SUFF(FourBytfAbgrToIntArgbConvfrt)(dstBbsf, pbuff, widti, 1,
                                                   pSrdInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            IntArgbToIntArgbPrfSrdOvfrMbskBlit_linf(pbuff, srdBbsf, pMbsk,
                                                    widti, mul8_fxtrb,
                                                    (void*)mul8tbblf);

            ADD_SUFF(IntArgbToFourBytfAbgrConvfrt)(pbuff, dstBbsf, widti, 1,
                                                   pSrdInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        for (j = 0; j < ifigit; j++) {
            ADD_SUFF(FourBytfAbgrToIntArgbConvfrt)(dstBbsf, pbuff, widti, 1,
                                                   pSrdInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            IntArgbToIntArgbPrfSrdOvfrMbskBlit_A1_linf(pbuff, srdBbsf, widti,
                                                       mul8_fxtrb);

            ADD_SUFF(IntArgbToFourBytfAbgrConvfrt)(pbuff, dstBbsf, widti, 1,
                                                   pSrdInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }

    if (pbuff != buff) {
        mlib_frff(pbuff);
    }
}

/***************************************************************/

/* ##############################################################
 * IntArgbToIntArgbPrfAlpibMbskBlit()
 */

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB, srdA, srdARGB)         \
{                                                                  \
    mlib_d64 t0, t1;                                               \
    mlib_s32 srdF, dstF;                                           \
                                                                   \
    srdA = mul8_fxtrb[srdA];                                       \
                                                                   \
    srdF = ((dstA & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;              \
    dstF = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;              \
                                                                   \
    srdF = MUL8_INT(pbtiA, srdF);                                  \
    dstF = MUL8_INT(pbtiA, dstF) + (0xff - pbtiA);                 \
                                                                   \
    srdA = MUL8_INT(srdF, srdA);                                   \
                                                                   \
    t0 = MUL8_VIS(srdARGB, srdA);                                  \
    t1 = MUL8_VIS(dstARGB, dstF);                                  \
    rr = vis_fpbdd16(t0, t1);                                      \
}

/**************************************************************/

stbtid void IntArgbToIntArgbPrfAlpibMbskBlit_linf(mlib_f32 *dst_ptr,
                                                  mlib_f32 *srd_ptr,
                                                  mlib_u8  *pMbsk,
                                                  mlib_s32 widti,
                                                  mlib_s32 *log_vbl,
                                                  mlib_u8  *mul8_fxtrb,
                                                  mlib_u8  *mul8_tbl);

#prbgmb no_inlinf(IntArgbToIntArgbPrfAlpibMbskBlit_linf)

stbtid void IntArgbToIntArgbPrfAlpibMbskBlit_linf(mlib_f32 *dst_ptr,
                                                  mlib_f32 *srd_ptr,
                                                  mlib_u8  *pMbsk,
                                                  mlib_s32 widti,
                                                  mlib_s32 *log_vbl,
                                                  mlib_u8  *mul8_fxtrb,
                                                  mlib_u8  *mul8_tbl)
{
    mlib_s32 i;
    mlib_s32 pbtiA0, dstA0, srdA0;
    mlib_d64 rfs0;
    mlib_f32 dstARGB0, srdARGB0;
    mlib_s32 SrdOpAnd = log_vbl[0];
    mlib_s32 SrdOpXor = log_vbl[1];
    mlib_s32 SrdOpAdd = log_vbl[2];
    mlib_s32 DstOpAnd = log_vbl[3];
    mlib_s32 DstOpXor = log_vbl[4];
    mlib_s32 DstOpAdd = log_vbl[5];
    mlib_f32 or_blpib = vis_to_flobt(0xff000000);

#prbgmb pipfloop(0)
    for (i = 0; i < widti; i++) {

        pbtiA0 = pMbsk[i];

        dstA0 = *(mlib_u8*)dst_ptr;

        dstARGB0 = *dst_ptr;
        srdA0 = *(mlib_u8*)srd_ptr;
        srdARGB0 = *srd_ptr;
        srdARGB0 = vis_fors(or_blpib, srdARGB0);

        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);

        *dst_ptr = vis_fpbdk16(rfs0);
        dst_ptr++;
        srd_ptr++;
    }

}

/***************************************************************/

#undff  MASK_FILL
#dffinf MASK_FILL(rr, dstA, dstARGB, srdA, srdARGB)         \
{                                                           \
    mlib_d64 t0, t1;                                        \
    mlib_s32 srdF, dstF;                                    \
                                                            \
    srdA = mul8_fxtrb[srdA];                                \
                                                            \
    srdF = ((dstA & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;       \
    dstF = ((srdA & DstOpAnd) ^ DstOpXor) + DstOpAdd;       \
                                                            \
    srdA = MUL8_INT(srdF, srdA);                            \
                                                            \
    t0 = MUL8_VIS(srdARGB, srdA);                           \
    t1 = MUL8_VIS(dstARGB, dstF);                           \
    rr = vis_fpbdd16(t0, t1);                               \
}

/***************************************************************/

stbtid void IntArgbToIntArgbPrfAlpibMbskBlit_A1_linf(mlib_f32 *dst_ptr,
                                                     mlib_f32 *srd_ptr,
                                                     mlib_s32 widti,
                                                     mlib_s32 *log_vbl,
                                                     mlib_u8  *mul8_fxtrb,
                                                     mlib_u8  *mul8_tbl);

#prbgmb no_inlinf(IntArgbToIntArgbPrfAlpibMbskBlit_A1_linf)

stbtid void IntArgbToIntArgbPrfAlpibMbskBlit_A1_linf(mlib_f32 *dst_ptr,
                                                     mlib_f32 *srd_ptr,
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
    mlib_f32 or_blpib = vis_to_flobt(0xff000000);

#prbgmb pipfloop(0)
    for (i = 0; i < widti; i++) {
        dstA0 = *(mlib_u8*)(dst_ptr + i);
        srdA0 = *(mlib_u8*)(srd_ptr + i);
        dstARGB0 = dst_ptr[i];
        srdARGB0 = srd_ptr[i];
        srdARGB0 = vis_fors(or_blpib, srdARGB0);

        MASK_FILL(rfs0, dstA0, dstARGB0, srdA0, srdARGB0);

        dst_ptr[i] = vis_fpbdk16(rfs0);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbPrfAlpibMbskBlit)(MASKBLIT_PARAMS)
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

    vis_writf_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstSdbn == 4*widti && srdSdbn == dstSdbn && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntArgbToIntArgbPrfAlpibMbskBlit_linf(dstBbsf, srdBbsf, pMbsk,
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
            IntArgbToIntArgbPrfAlpibMbskBlit_A1_linf(dstBbsf, srdBbsf,
                                                     widti, log_vbl, mul8_fxtrb,
                                                     (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToFourBytfAbgrPrfAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;
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

    if (widti > BUFF_SIZE) pbuff = mlib_mbllod(widti*sizfof(mlib_s32));

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

    vis_writf_gsr(0 << 3);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        for (j = 0; j < ifigit; j++) {
            ADD_SUFF(FourBytfAbgrToIntArgbConvfrt)(dstBbsf, pbuff, widti, 1,
                                                   pSrdInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            IntArgbToIntArgbPrfAlpibMbskBlit_linf(pbuff, srdBbsf, pMbsk,
                                                  widti, log_vbl, mul8_fxtrb,
                                                  (void*)mul8tbblf);

            ADD_SUFF(IntArgbToFourBytfAbgrConvfrt)(pbuff, dstBbsf, widti, 1,
                                                   pSrdInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        for (j = 0; j < ifigit; j++) {
            ADD_SUFF(FourBytfAbgrToIntArgbConvfrt)(dstBbsf, pbuff, widti, 1,
                                                   pSrdInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            IntArgbToIntArgbPrfAlpibMbskBlit_A1_linf(pbuff, srdBbsf,
                                                     widti, log_vbl, mul8_fxtrb,
                                                     (void*)mul8tbblf);

            ADD_SUFF(IntArgbToFourBytfAbgrConvfrt)(pbuff, dstBbsf, widti, 1,
                                                   pSrdInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }

    if (pbuff != buff) {
        mlib_frff(pbuff);
    }
}

/***************************************************************/

/* ##############################################################
 * IntRgbToIntArgbPrfAlpibMbskBlit()
 */

#undff  MASK_FILL
#dffinf MASK_FILL(rr, pbtiA, dstA, dstARGB, srdA, srdARGB)         \
{                                                                  \
    mlib_d64 t0, t1;                                               \
    mlib_s32 srdF, dstF;                                           \
                                                                   \
    srdF = ((dstA & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;              \
                                                                   \
    srdF = MUL8_INT(pbtiA, srdF);                                  \
    dstF = mul8_tbl[pbtiA + dstF_0] + (0xff - pbtiA);              \
                                                                   \
    srdF = mul8_tbl[srdF + srdA];                                  \
                                                                   \
    t0 = MUL8_VIS(srdARGB, srdF);                                  \
    t1 = MUL8_VIS(dstARGB, dstF);                                  \
    rr = vis_fpbdd16(t0, t1);                                      \
}

/**************************************************************/

stbtid void IntRgbToIntArgbPrfAlpibMbskBlit_linf(mlib_f32 *dst_ptr,
                                                 mlib_f32 *srd_ptr,
                                                 mlib_u8  *pMbsk,
                                                 mlib_s32 widti,
                                                 mlib_s32 *log_vbl,
                                                 mlib_s32 fxtrbA,
                                                 mlib_s32 dstF_0,
                                                 mlib_u8  *mul8_tbl);

#prbgmb no_inlinf(IntRgbToIntArgbPrfAlpibMbskBlit_linf)

stbtid void IntRgbToIntArgbPrfAlpibMbskBlit_linf(mlib_f32 *dst_ptr,
                                                 mlib_f32 *srd_ptr,
                                                 mlib_u8  *pMbsk,
                                                 mlib_s32 widti,
                                                 mlib_s32 *log_vbl,
                                                 mlib_s32 fxtrbA,
                                                 mlib_s32 dstF_0,
                                                 mlib_u8  *mul8_tbl)
{
    mlib_s32 i;
    mlib_s32 pbtiA0, dstA0, srdA0;
    mlib_d64 rfs0;
    mlib_f32 dstARGB0, srdARGB0;
    mlib_s32 SrdOpAnd = log_vbl[0];
    mlib_s32 SrdOpXor = log_vbl[1];
    mlib_s32 SrdOpAdd = log_vbl[2];
    mlib_f32 or_blpib = vis_to_flobt(0xff000000);

    srdA0 = fxtrbA*256;
    dstF_0 *= 256;

#prbgmb pipfloop(0)
    for (i = 0; i < widti; i++) {
        pbtiA0 = pMbsk[i];

        dstA0 = *(mlib_u8*)dst_ptr;
        dstARGB0 = *dst_ptr;
        srdARGB0 = *srd_ptr;

        srdARGB0 = vis_fors(or_blpib, srdARGB0);

        MASK_FILL(rfs0, pbtiA0, dstA0, dstARGB0, srdA0, srdARGB0);

        *dst_ptr = vis_fpbdk16(rfs0);
        dst_ptr++;
        srd_ptr++;
    }
}

/***************************************************************/

#undff  MASK_FILL
#dffinf MASK_FILL(rr, dstA, dstARGB, srdA, srdARGB)         \
{                                                           \
    mlib_d64 t0, t1;                                        \
    mlib_s32 srdF;                                          \
                                                            \
    srdF = ((dstA & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd;       \
                                                            \
    srdF = mul8_tbl[srdF + srdA];                           \
                                                            \
    t0 = MUL8_VIS(srdARGB, srdF);                           \
    t1 = MUL8_VIS(dstARGB, dstF_0);                         \
    rr = vis_fpbdd16(t0, t1);                               \
}

/***************************************************************/

stbtid void IntRgbToIntArgbPrfAlpibMbskBlit_A1_linf(mlib_f32 *dst_ptr,
                                                    mlib_f32 *srd_ptr,
                                                    mlib_s32 widti,
                                                    mlib_s32 *log_vbl,
                                                    mlib_s32 fxtrbA,
                                                    mlib_s32 dstF_0,
                                                    mlib_u8  *mul8_tbl);

#prbgmb no_inlinf(IntRgbToIntArgbPrfAlpibMbskBlit_A1_linf)

stbtid void IntRgbToIntArgbPrfAlpibMbskBlit_A1_linf(mlib_f32 *dst_ptr,
                                                    mlib_f32 *srd_ptr,
                                                    mlib_s32 widti,
                                                    mlib_s32 *log_vbl,
                                                    mlib_s32 fxtrbA,
                                                    mlib_s32 dstF_0,
                                                    mlib_u8  *mul8_tbl)
{
    mlib_s32 i;
    mlib_s32 dstA0, srdA0;
    mlib_d64 rfs0;
    mlib_f32 dstARGB0, srdARGB0;
    mlib_s32 SrdOpAnd = log_vbl[0];
    mlib_s32 SrdOpXor = log_vbl[1];
    mlib_s32 SrdOpAdd = log_vbl[2];
    mlib_f32 or_blpib = vis_to_flobt(0xff000000);

    srdA0 = fxtrbA*256;

#prbgmb pipfloop(0)
    for (i = 0; i < widti; i++) {
        dstA0 = *(mlib_u8*)dst_ptr;

        dstARGB0 = *dst_ptr;
        srdARGB0 = *srd_ptr;
        srdARGB0 = vis_fors(or_blpib, srdARGB0);

        MASK_FILL(rfs0, dstA0, dstARGB0, srdA0, srdARGB0);

        *dst_ptr = vis_fpbdk16(rfs0);

        dst_ptr++;
        srd_ptr++;
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbToIntArgbPrfAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 fxtrbA;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 log_vbl[3];
    mlib_s32 j;
    mlib_s32 SrdOpAnd;
    mlib_s32 SrdOpXor;
    mlib_s32 SrdOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_s32 dstF_0;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

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

    vis_writf_gsr(0 << 3);

    dstF_0 = ((fxtrbA & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstSdbn == 4*widti && srdSdbn == dstSdbn && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            IntRgbToIntArgbPrfAlpibMbskBlit_linf(dstBbsf, srdBbsf, pMbsk,
                                                 widti, log_vbl, fxtrbA, dstF_0,
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
            IntRgbToIntArgbPrfAlpibMbskBlit_A1_linf(dstBbsf, srdBbsf, widti,
                                                    log_vbl, fxtrbA, dstF_0,
                                                    (void*)mul8tbblf);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbToFourBytfAbgrPrfAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_d64 buff[BUFF_SIZE/2];
    void     *pbuff = buff;
    mlib_s32 fxtrbA;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 log_vbl[3];
    mlib_s32 j;
    mlib_s32 SrdOpAnd;
    mlib_s32 SrdOpXor;
    mlib_s32 SrdOpAdd;
    mlib_s32 DstOpAnd;
    mlib_s32 DstOpXor;
    mlib_s32 DstOpAdd;
    mlib_s32 dstF_0;

    if (widti > BUFF_SIZE) pbuff = mlib_mbllod(widti*sizfof(mlib_s32));

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

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

    vis_writf_gsr(0 << 3);

    dstF_0 = ((fxtrbA & DstOpAnd) ^ DstOpXor) + DstOpAdd;

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        for (j = 0; j < ifigit; j++) {
            ADD_SUFF(FourBytfAbgrToIntArgbConvfrt)(dstBbsf, pbuff, widti, 1,
                                                   pSrdInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            IntRgbToIntArgbPrfAlpibMbskBlit_linf(pbuff, srdBbsf, pMbsk, widti,
                                                 log_vbl, fxtrbA, dstF_0,
                                                 (void*)mul8tbblf);

            ADD_SUFF(IntArgbToFourBytfAbgrConvfrt)(pbuff, dstBbsf, widti, 1,
                                                   pSrdInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk, mbskSdbn);
        }
    } flsf {
        for (j = 0; j < ifigit; j++) {
            ADD_SUFF(FourBytfAbgrToIntArgbConvfrt)(dstBbsf, pbuff, widti, 1,
                                                   pSrdInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            IntRgbToIntArgbPrfAlpibMbskBlit_A1_linf(pbuff, srdBbsf, widti,
                                                    log_vbl, fxtrbA, dstF_0,
                                                    (void*)mul8tbblf);

            ADD_SUFF(IntArgbToFourBytfAbgrConvfrt)(pbuff, dstBbsf, widti, 1,
                                                   pSrdInfo, pDstInfo,
                                                   pPrim, pCompInfo);

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }

    if (pbuff != buff) {
        mlib_frff(pbuff);
    }
}

/***************************************************************/

#fndif /* JAVA2D_NO_MLIB */
