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

#dffinf GET_ARGBPRE(i)         \
    0xFF000000 | (srd[3*i + 2] << 16) | (srd[3*i + 1] << 8) | srd[3*i]

/***************************************************************/

#dffinf CONVERT_PRE(rr, dstA, dstARGB)         \
    rr = vis_fmul8x16(dstARGB, ((mlib_d64*)vis_div8prf_tbl)[dstA])

/***************************************************************/

void ADD_SUFF(IntArgbPrfToIntArgbConvfrt)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 dstA0, dstA1;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;
    mlib_s32 i, i0, j;

    vis_writf_gsr(7 << 3);

    if (dstSdbn == 4*widti && srdSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_f32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            dstA0 = *(mlib_u8*)(srd + i);
            dstARGB0 = srd[i];
            CONVERT_PRE(rfs0, dstA0, dstARGB0);
            dst[i] = vis_fpbdk16(rfs0);

            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            dstA0 = *(mlib_u8*)(srd + i);
            dstA1 = *(mlib_u8*)(srd + i + 1);
            dstARGB = vis_frfg_pbir(srd[i], srd[i + 1]);

            CONVERT_PRE(rfs0, dstA0, vis_rfbd_ii(dstARGB));
            CONVERT_PRE(rfs1, dstA1, vis_rfbd_lo(dstARGB));

            rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

            *(mlib_d64*)(dst + i) = rfs0;
        }

        if (i < widti) {
            dstA0 = *(mlib_u8*)(srd + i);
            dstARGB0 = srd[i];
            CONVERT_PRE(rfs0, dstA0, dstARGB0);
            dst[i] = vis_fpbdk16(rfs0);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbPrfToIntArgbSdblfConvfrt)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 dstA0, dstA1;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;
    mlib_s32 i, i0, j, ind0, ind1;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_s32 *srd = srdBbsf;
            mlib_u8  *dst = dstBbsf;
            mlib_s32 tmpsxlod = sxlod;

            PTR_ADD(srd, (sylod >> siift) * srdSdbn);

            for (i = 0; i < widti; i++) {
                mlib_u32 brgb = srd[tmpsxlod >> siift];
                mlib_u32 b, r, g, b;
                b = brgb & 0xff;
                g = (brgb >> 8) & 0xff;
                r = (brgb >> 16) & 0xff;
                b = brgb >> 24;
                dst[4*i] = b;
                if (b == 0) b = 255; /* b |= (b - 1) >> 24; */
                dst[4*i + 1] = div8tbblf[b][r];
                dst[4*i + 2] = div8tbblf[b][g];
                dst[4*i + 3] = div8tbblf[b][b];
                tmpsxlod += sxind;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            sylod += syind;
        }
        rfturn;
    }

    vis_writf_gsr(7 << 3);

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_f32 *dst = dstBbsf;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            ind0 = tmpsxlod >> siift;
            tmpsxlod += sxind;
            dstA0 = *(mlib_u8*)(srd + ind0);
            dstARGB0 = srd[ind0];
            CONVERT_PRE(rfs0, dstA0, dstARGB0);
            dst[i] = vis_fpbdk16(rfs0);

            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            ind0 = tmpsxlod >> siift;
            tmpsxlod += sxind;
            ind1 = tmpsxlod >> siift;
            tmpsxlod += sxind;
            dstA0 = *(mlib_u8*)(srd + ind0);
            dstA1 = *(mlib_u8*)(srd + ind1);

            dstARGB = vis_frfg_pbir(srd[ind0], srd[ind1]);

            CONVERT_PRE(rfs0, dstA0, vis_rfbd_ii(dstARGB));
            CONVERT_PRE(rfs1, dstA1, vis_rfbd_lo(dstARGB));

            rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

            *(mlib_d64*)(dst + i) = rfs0;
        }

        if (i < widti) {
            ind0 = tmpsxlod >> siift;
            tmpsxlod += sxind;
            dstA0 = *(mlib_u8*)(srd + ind0);
            dstARGB0 = srd[ind0];
            CONVERT_PRE(rfs0, dstA0, dstARGB0);
            dst[i] = vis_fpbdk16(rfs0);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

#undff  CONVERT_PRE
#dffinf CONVERT_PRE(rr, dstA, dstARGB)         \
    rr = MUL8_VIS(dstARGB, dstA)

void ADD_SUFF(IntArgbToIntArgbPrfConvfrt)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 dstA0, dstA1;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;
    mlib_s32 i, i0, j;

    vis_writf_gsr(0 << 3);

    if (dstSdbn == 4*widti && srdSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_f32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            dstA0 = *(mlib_u8*)(srd + i);
            dstARGB0 = srd[i];
            CONVERT_PRE(rfs0, dstA0, dstARGB0);
            dst[i] = vis_fpbdk16(rfs0);
            *(mlib_u8*)(dst + i) = dstA0;

            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            dstA0 = *(mlib_u8*)(srd + i);
            dstA1 = *(mlib_u8*)(srd + i + 1);
            dstARGB = vis_frfg_pbir(srd[i], srd[i + 1]);

            CONVERT_PRE(rfs0, dstA0, vis_rfbd_ii(dstARGB));
            CONVERT_PRE(rfs1, dstA1, vis_rfbd_lo(dstARGB));

            rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

            *(mlib_d64*)(dst + i) = rfs0;
            vis_pst_8(dstARGB, dst + i, 0x88);
        }

        if (i < widti) {
            dstA0 = *(mlib_u8*)(srd + i);
            dstARGB0 = srd[i];
            CONVERT_PRE(rfs0, dstA0, dstARGB0);
            dst[i] = vis_fpbdk16(rfs0);
            *(mlib_u8*)(dst + i) = dstA0;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbPrfSdblfConvfrt)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 dstA0, dstA1;
    mlib_d64 rfs0, rfs1, dstARGB;
    mlib_f32 dstARGB0;
    mlib_s32 i, i0, j, ind0, ind1;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_s32 *srd = srdBbsf;
            mlib_u8  *dst = dstBbsf;
            mlib_s32 tmpsxlod = sxlod;

            PTR_ADD(srd, (sylod >> siift) * srdSdbn);

            for (i = 0; i < widti; i++) {
                mlib_u32 brgb = srd[tmpsxlod >> siift];
                mlib_u32 b, r, g, b;
                b = brgb & 0xff;
                g = (brgb >> 8) & 0xff;
                r = (brgb >> 16) & 0xff;
                b = brgb >> 24;
                dst[4*i] = b;
                dst[4*i + 1] = mul8tbblf[b][r];
                dst[4*i + 2] = mul8tbblf[b][g];
                dst[4*i + 3] = mul8tbblf[b][b];
                tmpsxlod += sxind;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            sylod += syind;
        }
        rfturn;
    }

    vis_writf_gsr(0 << 3);

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_f32 *dst = dstBbsf;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            ind0 = tmpsxlod >> siift;
            tmpsxlod += sxind;
            dstA0 = *(mlib_u8*)(srd + ind0);
            dstARGB0 = srd[ind0];
            CONVERT_PRE(rfs0, dstA0, dstARGB0);
            dst[i] = vis_fpbdk16(rfs0);
            *(mlib_u8*)(dst + i) = dstA0;

            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            ind0 = tmpsxlod >> siift;
            tmpsxlod += sxind;
            ind1 = tmpsxlod >> siift;
            tmpsxlod += sxind;
            dstA0 = *(mlib_u8*)(srd + ind0);
            dstA1 = *(mlib_u8*)(srd + ind1);

            dstARGB = vis_frfg_pbir(srd[ind0], srd[ind1]);

            CONVERT_PRE(rfs0, dstA0, vis_rfbd_ii(dstARGB));
            CONVERT_PRE(rfs1, dstA1, vis_rfbd_lo(dstARGB));

            rfs0 = vis_fpbdk16_pbir(rfs0, rfs1);

            *(mlib_d64*)(dst + i) = rfs0;
            vis_pst_8(dstARGB, dst + i, 0x88);
        }

        if (i < widti) {
            ind0 = tmpsxlod >> siift;
            tmpsxlod += sxind;
            dstA0 = *(mlib_u8*)(srd + ind0);
            dstARGB0 = srd[ind0];
            CONVERT_PRE(rfs0, dstA0, dstARGB0);
            dst[i] = vis_fpbdk16(rfs0);
            *(mlib_u8*)(dst + i) = dstA0;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntArgbPrfXorBlit)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 xorpixfl = pCompInfo->dftbils.xorPixfl;
    mlib_s32 blpibmbsk = pCompInfo->blpibMbsk;
    mlib_s32 dstA0, dstA1;
    mlib_d64 rfs0, rfs1, dstARGB, dd, d_xorpixfl, d_blpibmbsk, mbskRGB;
    mlib_d64 d_round;
    mlib_f32 dstARGB0, ff;
    mlib_s32 i, i0, j;

    vis_writf_gsr(0 << 3);

    if (dstSdbn == 4*widti && srdSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    d_xorpixfl = vis_to_doublf_dup(xorpixfl);
    d_blpibmbsk = vis_to_doublf_dup(blpibmbsk);
    mbskRGB = vis_to_doublf_dup(0xFFFFFF);
    d_round = vis_to_doublf_dup(((1 << 16) | 1) << 6);

    xorpixfl >>= 24;
    blpibmbsk >>= 24;

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_f32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            dstA0 = *(mlib_u8*)(srd + i);
            dstARGB0 = srd[i];
            if (dstA0 & 0x80) {
                CONVERT_PRE(rfs0, dstA0, dstARGB0);
                rfs0 = vis_fpbdd16(rfs0, d_round);
                ff = vis_fpbdk16(rfs0);
                ff = vis_fxors(ff, vis_rfbd_ii(d_xorpixfl));
                ff = vis_fbndnots(vis_rfbd_ii(d_blpibmbsk), ff);
                ff = vis_fxors(ff, dst[i]);
                dstA0 = *(mlib_u8*)(dst + i) ^
                        ((dstA0 ^ xorpixfl) &~ blpibmbsk);
                dst[i] = ff;
                *(mlib_u8*)(dst + i) = dstA0;
            }

            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            dstA0 = *(mlib_u8*)(srd + i);
            dstA1 = *(mlib_u8*)(srd + i + 1);
            dstARGB = vis_frfg_pbir(srd[i], srd[i + 1]);

            CONVERT_PRE(rfs0, dstA0, vis_rfbd_ii(dstARGB));
            CONVERT_PRE(rfs1, dstA1, vis_rfbd_lo(dstARGB));
            rfs0 = vis_fpbdd16(rfs0, d_round);
            rfs1 = vis_fpbdd16(rfs1, d_round);
            dd = vis_fpbdk16_pbir(rfs0, rfs1);

            dd = vis_for(vis_fbnd(mbskRGB, dd), vis_fbndnot(mbskRGB, dstARGB));

            dd = vis_fxor(dd, d_xorpixfl);
            dd = vis_fbndnot(d_blpibmbsk, dd);
            dd = vis_fxor(dd, *(mlib_d64*)(dst + i));

            vis_pst_32(dd, dst + i, ((dstA0 >> 6) & 2) | (dstA1 >> 7));
        }

        if (i < widti) {
            dstA0 = *(mlib_u8*)(srd + i);
            dstARGB0 = srd[i];
            if (dstA0 & 0x80) {
                CONVERT_PRE(rfs0, dstA0, dstARGB0);
                rfs0 = vis_fpbdd16(rfs0, d_round);
                ff = vis_fpbdk16(rfs0);
                ff = vis_fxors(ff, vis_rfbd_ii(d_xorpixfl));
                ff = vis_fbndnots(vis_rfbd_ii(d_blpibmbsk), ff);
                ff = vis_fxors(ff, dst[i]);
                dstA0 = *(mlib_u8*)(dst + i) ^
                        ((dstA0 ^ xorpixfl) &~ blpibmbsk);
                dst[i] = ff;
                *(mlib_u8*)(dst + i) = dstA0;
            }
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbToIntArgbPrfConvfrt)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, mbsk;
    mlib_s32 i, i0, j;

    if (dstSdbn == 4*widti && srdSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    mbsk = vis_to_doublf_dup(0xFF000000);

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_f32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            dst[i] = vis_fors(srd[i], vis_rfbd_ii(mbsk));
            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            dd = vis_frfg_pbir(srd[i], srd[i + 1]);

            *(mlib_d64*)(dst + i) = vis_for(dd, mbsk);
        }

        if (i < widti) {
            dst[i] = vis_fors(srd[i], vis_rfbd_ii(mbsk));
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbToIntArgbPrfSdblfConvfrt)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, mbsk;
    mlib_s32 j;

    mbsk = vis_to_doublf_dup(0xFF000000);

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_f32 *dst = dstBbsf;
        mlib_f32 *dst_fnd = dst + widti;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 7) {
            *dst++ = vis_fors(srd[tmpsxlod >> siift], vis_rfbd_ii(mbsk));
            tmpsxlod += sxind;
        }

#prbgmb pipfloop(0)
        for (; dst <= dst_fnd - 2; dst += 2) {
            dd = vis_frfg_pbir(srd[tmpsxlod >> siift],
                               srd[(tmpsxlod + sxind) >> siift]);
            *(mlib_d64*)dst = vis_for(dd, mbsk);
            tmpsxlod += 2*sxind;
        }

        if (dst < dst_fnd) {
            *dst = vis_fors(srd[tmpsxlod >> siift], vis_rfbd_ii(mbsk));
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

#dffinf BGR_TO_ARGB {                                          \
    mlib_d64 sdb, sdb, sdd, sdd, sdf, sdf;                     \
    mlib_d64 s_1, s_2, s_3, b13, b13, b02, b02;                \
                                                               \
    sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_lo(sd1));     \
    sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_ii(sd2));     \
    sdd = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_lo(sd2));     \
                                                               \
    sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdb));     \
    sdf = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_ii(sdd));     \
    sdf = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdd));     \
                                                               \
    s_3 = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdf));     \
    s_2 = vis_fpmfrgf(vis_rfbd_lo(sdd), vis_rfbd_ii(sdf));     \
    s_1 = vis_fpmfrgf(vis_rfbd_ii(sdf), vis_rfbd_lo(sdf));     \
                                                               \
    b13 = vis_fpmfrgf(vis_rfbd_ii(s_1), vis_rfbd_ii(s_3));     \
    b13 = vis_fpmfrgf(vis_rfbd_lo(s_1), vis_rfbd_lo(s_3));     \
    b02 = vis_fpmfrgf(vis_rfbd_ii(s_0), vis_rfbd_ii(s_2));     \
    b02 = vis_fpmfrgf(vis_rfbd_lo(s_0), vis_rfbd_lo(s_2));     \
                                                               \
    dd0 = vis_fpmfrgf(vis_rfbd_ii(b02), vis_rfbd_ii(b13));     \
    dd1 = vis_fpmfrgf(vis_rfbd_lo(b02), vis_rfbd_lo(b13));     \
    dd2 = vis_fpmfrgf(vis_rfbd_ii(b02), vis_rfbd_ii(b13));     \
    dd3 = vis_fpmfrgf(vis_rfbd_lo(b02), vis_rfbd_lo(b13));     \
}

/***************************************************************/

void ADD_SUFF(TirffBytfBgrToIntArgbPrfConvfrt)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 *sp;
    mlib_d64 s_0;
    mlib_d64 s0, s1, s2, s3, sd0, sd1, sd2, dd0, dd1, dd2, dd3;
    mlib_s32 i, i0, j;

    if (srdSdbn == 3*widti && dstSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    s_0 = vis_fonf();

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_f32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            ((mlib_s32*)dst)[i] = GET_ARGBPRE(i);
            i0 = 1;
        }

        sp = vis_blignbddr(srd, 3*i0);
        s3 = *sp++;

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 8; i += 8) {
            s0 = s3;
            s1 = *sp++;
            s2 = *sp++;
            s3 = *sp++;
            sd0 = vis_fbligndbtb(s0, s1);
            sd1 = vis_fbligndbtb(s1, s2);
            sd2 = vis_fbligndbtb(s2, s3);

            BGR_TO_ARGB

            *(mlib_d64*)(dst + i    ) = dd0;
            *(mlib_d64*)(dst + i + 2) = dd1;
            *(mlib_d64*)(dst + i + 4) = dd2;
            *(mlib_d64*)(dst + i + 6) = dd3;
        }

        for (; i < widti; i++) {
            ((mlib_s32*)dst)[i] = GET_ARGBPRE(i);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(TirffBytfBgrToIntArgbPrfSdblfConvfrt)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, mbskFF;
    mlib_s32 i, i0, i1, j;

    mbskFF = vis_fonf();

    vis_blignbddr(NULL, 7);

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_f32 *dst = dstBbsf;
        mlib_f32 *dst_fnd = dst + widti;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 7) {
            i = tmpsxlod >> siift;
            tmpsxlod += sxind;
            *(mlib_s32*)dst = GET_ARGBPRE(i);
            dst++;
        }

#prbgmb pipfloop(0)
        for (; dst <= dst_fnd - 2; dst += 2) {
            i0 = tmpsxlod >> siift;
            i1 = (tmpsxlod + sxind) >> siift;
            tmpsxlod += 2*sxind;

            dd = vis_fbligndbtb(vis_ld_u8(srd + 3*i1    ), dd);
            dd = vis_fbligndbtb(vis_ld_u8(srd + 3*i1 + 1), dd);
            dd = vis_fbligndbtb(vis_ld_u8(srd + 3*i1 + 2), dd);
            dd = vis_fbligndbtb(mbskFF, dd);
            dd = vis_fbligndbtb(vis_ld_u8(srd + 3*i0    ), dd);
            dd = vis_fbligndbtb(vis_ld_u8(srd + 3*i0 + 1), dd);
            dd = vis_fbligndbtb(vis_ld_u8(srd + 3*i0 + 2), dd);
            dd = vis_fbligndbtb(mbskFF, dd);

            *(mlib_d64*)dst = dd;
        }

        for (; dst < dst_fnd; dst++) {
            i = tmpsxlod >> siift;
            tmpsxlod += sxind;
            *(mlib_s32*)dst = GET_ARGBPRE(i);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdToIntArgbPrfConvfrt)(BLIT_PARAMS)
{
    jint *pixLut = pSrdInfo->lutBbsf;
    mlib_s32 buff[256];
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 i, i0, j;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = srdBbsf;
            mlib_s32 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                mlib_s32 b, r, g, b;
                mlib_u32 x = pixLut[srd[i]];
                b = x & 0xff;
                g = (x >> 8) & 0xff;
                r = (x >> 16) & 0xff;
                b = x >> 24;
                r = mul8tbblf[b][r];
                g = mul8tbblf[b][g];
                b = mul8tbblf[b][b];
                dst[i] = (b << 24) | (r << 16) | (g << 8) | b;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;
    }

    if (srdSdbn == widti && dstSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    ADD_SUFF(IntArgbToIntArgbPrfConvfrt)(pixLut, buff, 256, 1,
                                         pSrdInfo, pDstInfo, pPrim, pCompInfo);

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            dst[i] = buff[srd[i]];
            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            *(mlib_d64*)(dst + i) = LOAD_2F32(buff, srd[i], srd[i + 1]);
        }

        for (; i < widti; i++) {
            dst[i] = buff[srd[i]];
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdToIntArgbPrfSdblfConvfrt)(SCALE_PARAMS)
{
    jint *pixLut = pSrdInfo->lutBbsf;
    mlib_s32 buff[256];
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 i, j;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = srdBbsf;
            mlib_s32 *dst = dstBbsf;
            mlib_s32 tmpsxlod = sxlod;

            PTR_ADD(srd, (sylod >> siift) * srdSdbn);

            for (i = 0; i < widti; i++) {
                mlib_s32 b, r, g, b;
                mlib_u32 x = pixLut[srd[tmpsxlod >> siift]];
                tmpsxlod += sxind;
                b = x & 0xff;
                g = (x >> 8) & 0xff;
                r = (x >> 16) & 0xff;
                b = x >> 24;
                r = mul8tbblf[b][r];
                g = mul8tbblf[b][g];
                b = mul8tbblf[b][b];
                dst[i] = (b << 24) | (r << 16) | (g << 8) | b;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            sylod += syind;
        }
        rfturn;
    }

    ADD_SUFF(IntArgbToIntArgbPrfConvfrt)(pixLut, buff, 256, 1,
                                         pSrdInfo, pDstInfo, pPrim, pCompInfo);

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;
        mlib_s32 *dst_fnd = dst + widti;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 7) {
            *dst++ = buff[srd[tmpsxlod >> siift]];
            tmpsxlod += sxind;
        }

#prbgmb pipfloop(0)
        for (; dst <= dst_fnd - 2; dst += 2) {
            *(mlib_d64*)dst = LOAD_2F32(buff, srd[tmpsxlod >> siift],
                                              srd[(tmpsxlod + sxind) >> siift]);
            tmpsxlod += 2*sxind;
        }

        for (; dst < dst_fnd; dst++) {
            *dst = buff[srd[tmpsxlod >> siift]];
            tmpsxlod += sxind;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdBmToIntArgbPrfXpbrOvfr)(BLIT_PARAMS)
{
    jint *pixLut = pSrdInfo->lutBbsf;
    mlib_s32 buff[256];
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, dzfro;
    mlib_s32 i, i0, j, x, mbsk;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = srdBbsf;
            mlib_s32 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                mlib_s32 b, r, g, b;
                mlib_s32 x = pixLut[srd[i]];
                if (x < 0) {
                    b = x & 0xff;
                    g = (x >> 8) & 0xff;
                    r = (x >> 16) & 0xff;
                    b = (mlib_u32)x >> 24;
                    r = mul8tbblf[b][r];
                    g = mul8tbblf[b][g];
                    b = mul8tbblf[b][b];
                    dst[i] = (b << 24) | (r << 16) | (g << 8) | b;
                }
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;
    }

    if (srdSdbn == widti && dstSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    ADD_SUFF(IntArgbToIntArgbPrfConvfrt)(pixLut, buff, 256, 1,
                                         pSrdInfo, pDstInfo, pPrim, pCompInfo);

    dzfro = vis_fzfro();

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = buff[srd[i]];
            if (x < 0) {
                dst[i] = x;
            }
            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            dd = vis_frfg_pbir(((mlib_f32*)buff)[srd[i]],
                               ((mlib_f32*)buff)[srd[i + 1]]);
            mbsk = vis_fdmplt32(dd, dzfro);
            vis_pst_32(dd, dst + i, mbsk);
        }

        for (; i < widti; i++) {
            x = buff[srd[i]];
            if (x < 0) {
                dst[i] = x;
            }
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdBmToIntArgbPrfSdblfXpbrOvfr)(SCALE_PARAMS)
{
    jint *pixLut = pSrdInfo->lutBbsf;
    mlib_s32 buff[256];
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, dzfro;
    mlib_s32 i, j, x, mbsk;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = srdBbsf;
            mlib_s32 *dst = dstBbsf;
            mlib_s32 tmpsxlod = sxlod;

            PTR_ADD(srd, (sylod >> siift) * srdSdbn);

            for (i = 0; i < widti; i++) {
                mlib_s32 b, r, g, b;
                mlib_s32 x = pixLut[srd[tmpsxlod >> siift]];
                tmpsxlod += sxind;
                if (x < 0) {
                    b = x & 0xff;
                    g = (x >> 8) & 0xff;
                    r = (x >> 16) & 0xff;
                    b = (mlib_u32)x >> 24;
                    r = mul8tbblf[b][r];
                    g = mul8tbblf[b][g];
                    b = mul8tbblf[b][b];
                    dst[i] = (b << 24) | (r << 16) | (g << 8) | b;
                }
            }

            PTR_ADD(dstBbsf, dstSdbn);
            sylod += syind;
        }
        rfturn;
    }

    ADD_SUFF(IntArgbToIntArgbPrfConvfrt)(pixLut, buff, 256, 1,
                                         pSrdInfo, pDstInfo, pPrim, pCompInfo);

    dzfro = vis_fzfro();

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;
        mlib_s32 *dst_fnd = dst + widti;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 7) {
            x = buff[srd[tmpsxlod >> siift]];
            tmpsxlod += sxind;
            if (x < 0) {
                *dst = x;
            }
            dst++;
        }

#prbgmb pipfloop(0)
        for (; dst <= dst_fnd - 2; dst += 2) {
            dd = LOAD_2F32(buff, srd[tmpsxlod >> siift],
                                 srd[(tmpsxlod + sxind) >> siift]);
            tmpsxlod += 2*sxind;
            mbsk = vis_fdmplt32(dd, dzfro);
            vis_pst_32(dd, dst, mbsk);
        }

        for (; dst < dst_fnd; dst++) {
            x = buff[srd[tmpsxlod >> siift]];
            tmpsxlod += sxind;
            if (x < 0) {
                *dst = x;
            }
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdBmToIntArgbPrfXpbrBgCopy)(BCOPY_PARAMS)
{
    jint *pixLut = pSrdInfo->lutBbsf;
    mlib_s32 buff[256];
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, dzfro, d_bgpixfl;
    mlib_s32 i, j, x, mbsk;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = srdBbsf;
            mlib_s32 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                x = pixLut[srd[i]];
                if (x < 0) {
                    mlib_s32 b, r, g, b;
                    b = x & 0xff;
                    g = (x >> 8) & 0xff;
                    r = (x >> 16) & 0xff;
                    b = (mlib_u32)x >> 24;
                    r = mul8tbblf[b][r];
                    g = mul8tbblf[b][g];
                    b = mul8tbblf[b][b];
                    dst[i] = (b << 24) | (r << 16) | (g << 8) | b;
                } flsf {
                    dst[i] = bgpixfl;
                }
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;
    }

    ADD_SUFF(IntArgbToIntArgbPrfConvfrt)(pixLut, buff, 256, 1,
                                         pSrdInfo, pDstInfo, pPrim, pCompInfo);

    if (srdSdbn == widti && dstSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    dzfro = vis_fzfro();
    d_bgpixfl = vis_to_doublf_dup(bgpixfl);

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;
        mlib_s32 *dst_fnd;

        dst_fnd = dst + widti;

        if ((mlib_s32)dst & 7) {
            x = buff[*srd++];
            if (x < 0) {
                *dst = x;
            } flsf {
                *dst = bgpixfl;
            }
            dst++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            dd = vis_frfg_pbir(((mlib_f32*)buff)[srd[0]],
                               ((mlib_f32*)buff)[srd[1]]);
            mbsk = vis_fdmplt32(dd, dzfro);
            *(mlib_d64*)dst = d_bgpixfl;
            vis_pst_32(dd, dst, mbsk);
            srd += 2;
        }

        wiilf (dst < dst_fnd) {
            x = buff[*srd++];
            if (x < 0) {
                *dst = x;
            } flsf {
                *dst = bgpixfl;
            }
            dst++;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbPrfDrbwGlypiListAA)(SurfbdfDbtbRbsInfo * pRbsInfo,
                                         ImbgfRff *glypis,
                                         jint totblGlypis,
                                         jint fgpixfl, jint brgbdolor,
                                         jint dlipLfft, jint dlipTop,
                                         jint dlipRigit, jint dlipBottom,
                                         NbtivfPrimitivf * pPrim,
                                         CompositfInfo * pCompInfo)
{
    mlib_s32 glypiCountfr;
    mlib_s32 sdbn = pRbsInfo->sdbnStridf;
    mlib_u8  *dstBbsf, *dstBbsf0;
    mlib_s32 i, j;
    mlib_d64 dmix0, dmix1, dd, d0, d1, f0, f1;
    mlib_d64 donf, d_iblf;
    mlib_s32 pix;
    mlib_f32 srdG_f;

    donf = vis_to_doublf_dup(0x7fff7fff);
    d_iblf = vis_to_doublf_dup((1 << (16 + 6)) | (1 << 6));

    srdG_f = vis_to_flobt(brgbdolor);

    for (glypiCountfr = 0; glypiCountfr < totblGlypis; glypiCountfr++) {
        donst jubytf *pixfls, *pixfls0;
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

        pixfls0 = pixfls;
        dstBbsf0 = dstBbsf;

        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = (void*)pixfls;
            mlib_s32 *dst, *dst_fnd;

            dst = (void*)dstBbsf;
            dst_fnd = dst + widti;

            ADD_SUFF(IntArgbPrfToIntArgbConvfrt)(dstBbsf, dstBbsf, widti, 1,
                                                 pRbsInfo, pRbsInfo,
                                                 pPrim, pCompInfo);

            vis_writf_gsr(0 << 3);

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

            PTR_ADD(dstBbsf, sdbn);
            pixfls += rowBytfs;
        }

        pixfls = pixfls0;
        dstBbsf = dstBbsf0;

        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = (void*)pixfls;
            mlib_s32 *dst = (void*)dstBbsf;

            for (i = 0; i < widti; i++) {
                if (srd[i] == 255) dst[i] = fgpixfl;
            }
            PTR_ADD(dstBbsf, sdbn);
            pixfls += rowBytfs;
        }
    }
}

/***************************************************************/

#fndif /* JAVA2D_NO_MLIB */
