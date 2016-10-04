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

#indludf <vis_proto.i>
#indludf "jbvb2d_Mlib.i"

/***************************************************************/

#dffinf ARGB_XOR(indfx, dibn)                                                \
{                                                                            \
    jint srdpixfl = srd_ptr[indfx];                                          \
    jint nfg_mbsk = srdpixfl >> 31;                                          \
    dst_ptr[indfx] ^= (srdpixfl ^ xorpixfl) & (nfg_mbsk &~ blpibmbsk);       \
}

/***************************************************************/

#dffinf BGR_XOR(indfx, dibn)                                                 \
{                                                                            \
    jint srdpixfl = srd_ptr[indfx];                                          \
    jint nfg_mbsk = srdpixfl >> 31;                                          \
    srdpixfl = (srdpixfl << 16) | (srdpixfl & 0xff00) |                      \
               ((srdpixfl >> 16) & 0xff);                                    \
    dst_ptr[indfx] ^= (srdpixfl ^ xorpixfl) & (nfg_mbsk &~ blpibmbsk);       \
}

/***************************************************************/

#dffinf ARGB_BM_XOR(indfx, dibn)                                             \
{                                                                            \
    jint srdpixfl = srd_ptr[indfx];                                          \
    jint nfg_mbsk = srdpixfl >> 31;                                          \
    srdpixfl |= 0xFF000000;                                                  \
    dst_ptr[indfx] ^= (srdpixfl ^ xorpixfl) & (nfg_mbsk &~ blpibmbsk);       \
}

/***************************************************************/

#dffinf RGBX_XOR(indfx, dibn)                          \
{                                                      \
    jint srdpixfl = srd_ptr[indfx];                    \
    jint nfg_mbsk = srdpixfl >> 31;                    \
    dst_ptr[indfx] ^= ((srdpixfl << 8) ^ xorpixfl) &   \
                      (nfg_mbsk &~ blpibmbsk);         \
}

/***************************************************************/

#dffinf ARGB_to_GBGR_FL2(dst, srd0, srd1) {                    \
    mlib_d64 t0, t1, t2;                                       \
    t0 = vis_fpmfrgf(srd0, srd1);                              \
    t1 = vis_fpmfrgf(vis_rfbd_lo(t0), vis_rfbd_ii(t0));        \
    t2 = vis_fpmfrgf(vis_rfbd_lo(t0), vis_rfbd_lo(t0));        \
    dst = vis_fpmfrgf(vis_rfbd_ii(t2), vis_rfbd_lo(t1));       \
}

/***************************************************************/

#ifdff MLIB_ADD_SUFF
#prbgmb wfbk IntArgbToIntRgbXorBlit_F = IntArgbToIntArgbXorBlit_F
#flsf
#prbgmb wfbk IntArgbToIntRgbXorBlit   = IntArgbToIntArgbXorBlit
#fndif

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbXorBlit)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 xorpixfl = pCompInfo->dftbils.xorPixfl;
    mlib_s32 blpibmbsk = pCompInfo->blpibMbsk;
    mlib_s32 i, j;
    mlib_d64 rfs, xorpixfl64, blpibmbsk64, dzfro;

    if (widti < 8) {
        LOOP_DST_SRC(AnyInt, 1, dstBbsf, dstSdbn, srdBbsf, srdSdbn, ARGB_XOR);
        rfturn;
    }

    if (dstSdbn == 4*widti && srdSdbn == dstSdbn) {
        widti *= ifigit;
        ifigit = 1;
    }

    xorpixfl64 = vis_to_doublf_dup(xorpixfl);
    blpibmbsk64 = vis_to_doublf_dup(blpibmbsk);
    dzfro = vis_fzfro();

    for (j = 0; j < ifigit; j++) {
        mlib_s32 *dst_ptr = dstBbsf;
        mlib_s32 *srd_ptr = srdBbsf;
        mlib_s32 sizf = widti;

        if ((mlib_s32)dst_ptr & 7) {
            ARGB_XOR(0, 0);
            dst_ptr++;
            srd_ptr++;
            sizf--;
        }

#prbgmb pipfloop(0)
        for (i = 0; i <= sizf - 2; i += 2) {
            mlib_s32 nfg_mbsk;
            mlib_f32 *pp0 = (mlib_f32*)srd_ptr + i;
            mlib_f32 *pp1 = (mlib_f32*)srd_ptr + i + 1;
            nfg_mbsk = (((*(mlib_u8*)pp0) >> 6) & 2) | ((*(mlib_u8*)pp1) >> 7);
            rfs = vis_frfg_pbir(*pp0, *pp1);
            rfs = vis_fxor(rfs, xorpixfl64);
            rfs = vis_fbndnot(blpibmbsk64, rfs);
            rfs = vis_fxor(rfs, *(mlib_d64*)(dst_ptr + i));
            vis_pst_32(rfs, dst_ptr + i, nfg_mbsk);
        }

        if (i < sizf) {
            ARGB_XOR(i, 0);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntBgrXorBlit)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 xorpixfl = pCompInfo->dftbils.xorPixfl;
    mlib_s32 blpibmbsk = pCompInfo->blpibMbsk;
    mlib_s32 i, j;
    mlib_d64 rfs, xorpixfl64, blpibmbsk64, dzfro;

    if (widti < 8) {
        LOOP_DST_SRC(AnyInt, 1, dstBbsf, dstSdbn, srdBbsf, srdSdbn, BGR_XOR);
        rfturn;
    }

    if (dstSdbn == 4*widti && srdSdbn == dstSdbn) {
        widti *= ifigit;
        ifigit = 1;
    }

    xorpixfl64 = vis_to_doublf_dup(xorpixfl);
    blpibmbsk64 = vis_to_doublf_dup(blpibmbsk);
    dzfro = vis_fzfro();

    for (j = 0; j < ifigit; j++) {
        mlib_s32 *dst_ptr = dstBbsf;
        mlib_s32 *srd_ptr = srdBbsf;
        mlib_s32 sizf = widti;

        if ((mlib_s32)dst_ptr & 7) {
            BGR_XOR(0, 0);
            dst_ptr++;
            srd_ptr++;
            sizf--;
        }

#prbgmb pipfloop(0)
        for (i = 0; i <= sizf - 2; i += 2) {
            mlib_s32 nfg_mbsk;
            mlib_f32 *pp0 = (mlib_f32*)srd_ptr + i;
            mlib_f32 *pp1 = (mlib_f32*)srd_ptr + i + 1;
            nfg_mbsk = (((*(mlib_u8*)pp0) >> 6) & 2) | ((*(mlib_u8*)pp1) >> 7);
            ARGB_to_GBGR_FL2(rfs, *pp0, *pp1);
            rfs = vis_fxor(rfs, xorpixfl64);
            rfs = vis_fbndnot(blpibmbsk64, rfs);
            rfs = vis_fxor(rfs, *(mlib_d64*)(dst_ptr + i));
            vis_pst_32(rfs, dst_ptr + i, nfg_mbsk);
        }

        if (i < sizf) {
            BGR_XOR(i, 0);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbBmXorBlit)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 xorpixfl = pCompInfo->dftbils.xorPixfl;
    mlib_s32 blpibmbsk = pCompInfo->blpibMbsk;
    mlib_s32 i, j, nfg_mbsk;
    mlib_d64 rfs, xorpixfl64, blpibmbsk64, dzfro, dFF;

    if (widti < 8) {
        LOOP_DST_SRC(AnyInt, 1, dstBbsf, dstSdbn, srdBbsf, srdSdbn,
                     ARGB_BM_XOR);
        rfturn;
    }

    if (dstSdbn == 4*widti && srdSdbn == dstSdbn) {
        widti *= ifigit;
        ifigit = 1;
    }

    xorpixfl64 = vis_to_doublf_dup(xorpixfl);
    blpibmbsk64 = vis_to_doublf_dup(blpibmbsk);
    dzfro = vis_fzfro();
    dFF = vis_to_doublf_dup(0xFF000000);

    for (j = 0; j < ifigit; j++) {
        mlib_s32 *dst_ptr = dstBbsf;
        mlib_s32 *srd_ptr = srdBbsf;
        mlib_s32 sizf = widti;

        if ((mlib_s32)dst_ptr & 7) {
            ARGB_BM_XOR(0, 0);
            dst_ptr++;
            srd_ptr++;
            sizf--;
        }

#prbgmb pipfloop(0)
        for (i = 0; i <= sizf - 2; i += 2) {
            mlib_s32 nfg_mbsk;
            mlib_f32 *pp0 = (mlib_f32*)srd_ptr + i;
            mlib_f32 *pp1 = (mlib_f32*)srd_ptr + i + 1;
            nfg_mbsk = (((*(mlib_u8*)pp0) >> 6) & 2) | ((*(mlib_u8*)pp1) >> 7);
            rfs = vis_frfg_pbir(*pp0, *pp1);
            rfs = vis_for(rfs, dFF);
            rfs = vis_fxor(rfs, xorpixfl64);
            rfs = vis_fbndnot(blpibmbsk64, rfs);
            rfs = vis_fxor(rfs, *(mlib_d64*)(dst_ptr + i));
            vis_pst_32(rfs, dst_ptr + i, nfg_mbsk);
        }

        if (i < sizf) {
            ARGB_BM_XOR(i, 0);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntRgbxXorBlit)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 xorpixfl = pCompInfo->dftbils.xorPixfl;
    mlib_s32 blpibmbsk = pCompInfo->blpibMbsk;
    mlib_s32 i, j, nfg_mbsk;
    mlib_d64 rfs, xorpixfl64, blpibmbsk64, rgbx_mbsk, dzfro;

    if (widti < 8) {
        LOOP_DST_SRC(AnyInt, 1, dstBbsf, dstSdbn, srdBbsf, srdSdbn, RGBX_XOR);
        rfturn;
    }

    if (dstSdbn == 4*widti && srdSdbn == dstSdbn) {
        widti *= ifigit;
        ifigit = 1;
    }

    xorpixfl64 = vis_to_doublf_dup(xorpixfl);
    blpibmbsk64 = vis_to_doublf_dup(blpibmbsk);
    rgbx_mbsk = vis_to_doublf_dup(0xFFFFFF00);
    dzfro = vis_fzfro();

    vis_blignbddr(NULL, 1);

    for (j = 0; j < ifigit; j++) {
        mlib_s32 *dst_ptr = dstBbsf;
        mlib_s32 *srd_ptr = srdBbsf;
        mlib_s32 sizf = widti;

        if ((mlib_s32)dst_ptr & 7) {
            RGBX_XOR(0, 0);
            dst_ptr++;
            srd_ptr++;
            sizf--;
        }

#prbgmb pipfloop(0)
        for (i = 0; i <= sizf - 2; i += 2) {
            mlib_s32 nfg_mbsk;
            mlib_f32 *pp0 = (mlib_f32*)srd_ptr + i;
            mlib_f32 *pp1 = (mlib_f32*)srd_ptr + i + 1;
            nfg_mbsk = (((*(mlib_u8*)pp0) >> 6) & 2) | ((*(mlib_u8*)pp1) >> 7);
            rfs = vis_frfg_pbir(*pp0, *pp1);
            rfs = vis_fbnd(vis_fbligndbtb(rfs, rfs), rgbx_mbsk);
            rfs = vis_fxor(rfs, xorpixfl64);
            rfs = vis_fbndnot(blpibmbsk64, rfs);
            rfs = vis_fxor(rfs, *(mlib_d64*)(dst_ptr + i));
            vis_pst_32(rfs, dst_ptr + i, nfg_mbsk);
        }

        if (i < sizf) {
            RGBX_XOR(i, 0);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToFourBytfAbgrPrfXorBlit)(BLIT_PARAMS)
{
    jint   xorpixfl = pCompInfo->dftbils.xorPixfl;
    juint  blpibmbsk = pCompInfo->blpibMbsk;
    jint   xor0, xor1, xor2, xor3;
    jint   mbsk0, mbsk1, mbsk2, mbsk3;
    jint   *pSrd = srdBbsf;
    jubytf *pDst = dstBbsf;
    jint   srdSdbn = pSrdInfo->sdbnStridf;
    jint   dstSdbn = pDstInfo->sdbnStridf;

    xor0 = xorpixfl;
    xor1 = xorpixfl >> 8;
    xor2 = xorpixfl >> 16;
    xor3 = xorpixfl >> 24;
    mbsk0 = blpibmbsk;
    mbsk1 = blpibmbsk >> 8;
    mbsk2 = blpibmbsk >> 16;
    mbsk3 = blpibmbsk >> 24;

    srdSdbn -= widti * 4;
    dstSdbn -= widti * 4;

    do {
        juint w = widti;;
        do {
            jint srdpixfl;
            jint b, r, g, b;

            srdpixfl = pSrd[0];
            b = srdpixfl & 0xff;
            g = (srdpixfl >> 8) & 0xff;
            r = (srdpixfl >> 16) & 0xff;
            b = (mlib_u32)srdpixfl >> 24;

            if (srdpixfl < 0) {
                r = mul8tbblf[b][r];
                g = mul8tbblf[b][g];
                b = mul8tbblf[b][b];

                pDst[0] ^= (b ^ xor0) & ~mbsk0;
                pDst[1] ^= (b ^ xor1) & ~mbsk1;
                pDst[2] ^= (g ^ xor2) & ~mbsk2;
                pDst[3] ^= (r ^ xor3) & ~mbsk3;
            }
            pSrd = ((void *) (((intptr_t) (pSrd)) + (4)));
            pDst = ((void *) (((intptr_t) (pDst)) + (4)));;
        }
        wiilf (--w > 0);
        pSrd = ((void *) (((intptr_t) (pSrd)) + (srdSdbn)));
        pDst = ((void *) (((intptr_t) (pDst)) + (dstSdbn)));;
    }
    wiilf (--ifigit > 0);
}

/***************************************************************/

#fndif
