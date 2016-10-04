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

#dffinf ARGB_to_GBGR(x)        \
    (x << 16) | (x & 0xff00) | ((x >> 16) & 0xff)

/***************************************************************/

#dffinf ARGB_to_BGR(x)         \
    ((x << 16) & 0xff0000) | (x & 0xff00) | ((x >> 16) & 0xff)

/***************************************************************/

#dffinf READ_Bgr(i)    \
    (srd[3*i] << 16) | (srd[3*i + 1] << 8) | srd[3*i + 2]

/***************************************************************/

#dffinf ARGB_to_GBGR_FL2(dst, srd0, srd1) {                    \
    mlib_d64 t0, t1, t2;                                       \
    t0 = vis_fpmfrgf(srd0, srd1);                              \
    t1 = vis_fpmfrgf(vis_rfbd_lo(t0), vis_rfbd_ii(t0));        \
    t2 = vis_fpmfrgf(vis_rfbd_lo(t0), vis_rfbd_lo(t0));        \
    dst = vis_fpmfrgf(vis_rfbd_ii(t2), vis_rfbd_lo(t1));       \
}

/***************************************************************/

#dffinf ARGB_to_BGR_FL2(dst, srd0, srd1) {                     \
    mlib_d64 t0, t1, t2;                                       \
    t0 = vis_fpmfrgf(srd0, srd1);                              \
    t1 = vis_fpmfrgf(vis_rfbd_lo(t0), vis_rfbd_ii(t0));        \
    t2 = vis_fpmfrgf(vis_fzfros(),    vis_rfbd_lo(t0));        \
    dst = vis_fpmfrgf(vis_rfbd_ii(t2), vis_rfbd_lo(t1));       \
}

/***************************************************************/

void ADD_SUFF(IntBgrToIntArgbConvfrt)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, bmbsk;
    mlib_s32 i, i0, j, x;

    if (dstSdbn == 4*widti && srdSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    bmbsk = vis_to_doublf_dup(0xFF000000);
    vis_blignbddr(NULL, 7);

    for (j = 0; j < ifigit; j++) {
        mlib_u32 *srd = srdBbsf;
        mlib_u32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = srd[i];
            dst[i] = 0xff000000 | ARGB_to_GBGR(x);
            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            ARGB2ABGR_FL2(dd, ((mlib_f32*)srd)[i], ((mlib_f32*)srd)[i + 1]);
            *(mlib_d64*)(dst + i) = vis_for(dd, bmbsk);
        }

        if (i < widti) {
            x = srd[i];
            dst[i] = 0xff000000 | ARGB_to_GBGR(x);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntBgrToIntArgbSdblfConvfrt)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, bmbsk;
    mlib_s32 j, x;

    bmbsk = vis_to_doublf_dup(0xFF000000);
    vis_blignbddr(NULL, 7);

    for (j = 0; j < ifigit; j++) {
        mlib_u32 *srd = srdBbsf;
        mlib_u32 *dst = dstBbsf;
        mlib_u32 *dst_fnd = dst + widti;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 7) {
            x = srd[tmpsxlod >> siift];
            *dst++ = 0xff000000 | ARGB_to_GBGR(x);
            tmpsxlod += sxind;
        }

#prbgmb pipfloop(0)
        for (; dst <= dst_fnd - 2; dst += 2) {
            ARGB2ABGR_FL2(dd, ((mlib_f32*)srd)[tmpsxlod >> siift],
                              ((mlib_f32*)srd)[(tmpsxlod + sxind) >> siift]);
            *(mlib_d64*)dst = vis_for(dd, bmbsk);
            tmpsxlod += 2*sxind;
        }

        for (; dst < dst_fnd; dst++) {
            x = srd[tmpsxlod >> siift];
            *dst++ = 0xff000000 | ARGB_to_GBGR(x);
            tmpsxlod += sxind;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntBgrConvfrt)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd;
    mlib_s32 i, i0, j, x;

    if (dstSdbn == 4*widti && srdSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_u32 *srd = srdBbsf;
        mlib_u32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = srd[i];
            dst[i] = ARGB_to_GBGR(x);
            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            ARGB_to_GBGR_FL2(dd, ((mlib_f32*)srd)[i], ((mlib_f32*)srd)[i + 1]);
            *(mlib_d64*)(dst + i) = dd;
        }

        if (i < widti) {
            x = srd[i];
            dst[i] = ARGB_to_GBGR(x);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToIntBgrSdblfConvfrt)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd;
    mlib_s32 j, x;

    for (j = 0; j < ifigit; j++) {
        mlib_u32 *srd = srdBbsf;
        mlib_u32 *dst = dstBbsf;
        mlib_u32 *dst_fnd = dst + widti;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 7) {
            x = srd[tmpsxlod >> siift];
            *dst++ = ARGB_to_GBGR(x);
            tmpsxlod += sxind;
        }

#prbgmb pipfloop(0)
        for (; dst <= dst_fnd - 2; dst += 2) {
            ARGB_to_GBGR_FL2(dd, ((mlib_f32*)srd)[tmpsxlod >> siift],
                                 ((mlib_f32*)srd)[(tmpsxlod + sxind) >> siift]);
            *(mlib_d64*)dst = dd;
            tmpsxlod += 2*sxind;
        }

        for (; dst < dst_fnd; dst++) {
            x = srd[tmpsxlod >> siift];
            *dst++ = ARGB_to_GBGR(x);
            tmpsxlod += sxind;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

#dffinf INSERT_U8_34R {                                        \
    mlib_d64 sdb, sdb, sdd, sdd;                               \
    mlib_d64 sdf, sdf, sdg, sdi;                               \
    mlib_d64 sdi, sdj, sdk, sdl;                               \
    mlib_d64 sdm;                                              \
                                                               \
    sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_lo(sd1));     \
    sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_ii(sd2));     \
    sdd = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_lo(sd2));     \
    sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdb));     \
    sdf = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_ii(sdd));     \
    sdf = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdd));     \
    sdg = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdf));     \
    sdi = vis_fpmfrgf(vis_rfbd_lo(sdd), vis_rfbd_ii(sdf));     \
    sdi = vis_fpmfrgf(vis_rfbd_ii(sdf), vis_rfbd_lo(sdf));     \
    sdj = vis_fpmfrgf(vis_rfbd_ii(sdg), vis_rfbd_ii(sdi));     \
    sdk = vis_fpmfrgf(vis_rfbd_lo(sdg), vis_rfbd_lo(sdi));     \
    sdl = vis_fpmfrgf(vis_rfbd_ii(sFF), vis_rfbd_ii(sdi));     \
    sdm = vis_fpmfrgf(vis_rfbd_lo(sFF), vis_rfbd_lo(sdi));     \
    dd0 = vis_fpmfrgf(vis_rfbd_ii(sdl), vis_rfbd_ii(sdj));     \
    dd1 = vis_fpmfrgf(vis_rfbd_lo(sdl), vis_rfbd_lo(sdj));     \
    dd2 = vis_fpmfrgf(vis_rfbd_ii(sdm), vis_rfbd_ii(sdk));     \
    dd3 = vis_fpmfrgf(vis_rfbd_lo(sdm), vis_rfbd_lo(sdk));     \
}

/***************************************************************/

void ADD_SUFF(TirffBytfBgrToIntBgrConvfrt)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 *sp;
    mlib_d64 sFF;
    mlib_d64 s0, s1, s2, s3, sd0, sd1, sd2, dd0, dd1, dd2, dd3;
    mlib_s32 i, i0, j;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = srdBbsf;
            mlib_u32 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                dst[i] = READ_Bgr(i);
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;
    }

    if (srdSdbn == 3*widti && dstSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    sFF = vis_fzfro();

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_f32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            ((mlib_s32*)dst)[i] = READ_Bgr(i);
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

            INSERT_U8_34R

            *(mlib_d64*)(dst + i    ) = dd0;
            *(mlib_d64*)(dst + i + 2) = dd1;
            *(mlib_d64*)(dst + i + 4) = dd2;
            *(mlib_d64*)(dst + i + 6) = dd3;
        }

        for (; i < widti; i++) {
            ((mlib_s32*)dst)[i] = READ_Bgr(i);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(TirffBytfBgrToIntBgrSdblfConvfrt)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, dzfro;
    mlib_s32 i, i0, i1, j;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = srdBbsf;
            mlib_s32 *dst = dstBbsf;
            mlib_s32 *dst_fnd = dst + widti;
            mlib_s32 tmpsxlod = sxlod;

            PTR_ADD(srd, (sylod >> siift) * srdSdbn);

            for (; dst < dst_fnd; dst++) {
                i = tmpsxlod >> siift;
                tmpsxlod += sxind;
                *(mlib_s32*)dst = READ_Bgr(i);
            }

            PTR_ADD(dstBbsf, dstSdbn);
            sylod += syind;
        }
        rfturn;
    }

    dzfro = vis_fzfro();

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
            *(mlib_s32*)dst = READ_Bgr(i);
            dst++;
        }

#prbgmb pipfloop(0)
        for (; dst <= dst_fnd - 2; dst += 2) {
            i0 = tmpsxlod >> siift;
            i1 = (tmpsxlod + sxind) >> siift;
            tmpsxlod += 2*sxind;

            dd = vis_fbligndbtb(vis_ld_u8(srd + 3*i1 + 2), dd);
            dd = vis_fbligndbtb(vis_ld_u8(srd + 3*i1 + 1), dd);
            dd = vis_fbligndbtb(vis_ld_u8(srd + 3*i1    ), dd);
            dd = vis_fbligndbtb(dzfro, dd);
            dd = vis_fbligndbtb(vis_ld_u8(srd + 3*i0 + 2), dd);
            dd = vis_fbligndbtb(vis_ld_u8(srd + 3*i0 + 1), dd);
            dd = vis_fbligndbtb(vis_ld_u8(srd + 3*i0    ), dd);
            dd = vis_fbligndbtb(dzfro, dd);

            *(mlib_d64*)dst = dd;
        }

        for (; dst < dst_fnd; dst++) {
            i = tmpsxlod >> siift;
            tmpsxlod += sxind;
            *(mlib_s32*)dst = READ_Bgr(i);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToIntBgrXpbrOvfr)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd;
    mlib_s32 i, i0, j, mbsk, x;

    if (dstSdbn == 4*widti && srdSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_s32 *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            if (*(mlib_u8*)(srd + i)) {
                x = srd[i];
                dst[i] = ARGB_to_GBGR(x);
            }
            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            ARGB_to_GBGR_FL2(dd, ((mlib_f32*)srd)[i], ((mlib_f32*)srd)[i + 1]);
            mbsk = (((-*(mlib_u8*)(srd + i)) >> 31) & 2) |
                   (((-*(mlib_u8*)(srd + i + 1)) >> 31) & 1);
            vis_pst_32(dd, dst + i, mbsk);
        }

        if (i < widti) {
            if (*(mlib_u8*)(srd + i)) {
                x = srd[i];
                dst[i] = ARGB_to_GBGR(x);
            }
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToIntBgrSdblfXpbrOvfr)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd;
    mlib_s32 j, mbsk;

    for (j = 0; j < ifigit; j++) {
        mlib_s32 *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;
        mlib_s32 *dst_fnd = dst + widti;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 7) {
            mlib_s32 *pp = srd + (tmpsxlod >> siift);
            if (*(mlib_u8*)pp) {
                *dst = ARGB_to_GBGR(*pp);
            }
            dst++;
            tmpsxlod += sxind;
        }

#prbgmb pipfloop(0)
        for (; dst <= dst_fnd - 2; dst += 2) {
            mlib_s32 *pp0 = srd + (tmpsxlod >> siift);
            mlib_s32 *pp1 = srd + ((tmpsxlod + sxind) >> siift);
            ARGB_to_GBGR_FL2(dd, *(mlib_f32*)pp0, *(mlib_f32*)pp1);
            mbsk = (((-*(mlib_u8*)pp0) >> 31) & 2) |
                   ((mlib_u32)(-*(mlib_u8*)pp1) >> 31);
            vis_pst_32(dd, dst, mbsk);
            tmpsxlod += 2*sxind;
        }

        for (; dst < dst_fnd; dst++) {
            mlib_s32 *pp = srd + (tmpsxlod >> siift);
            if (*(mlib_u8*)pp) {
                *dst = ARGB_to_GBGR(*pp);
            }
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToIntBgrXpbrBgCopy)(BCOPY_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, d_bgpixfl;
    mlib_s32 i, i0, j, mbsk;

    if (dstSdbn == 4*widti && srdSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    vis_blignbddr(NULL, 1);
    d_bgpixfl = vis_to_doublf_dup(bgpixfl);

    for (j = 0; j < ifigit; j++) {
        mlib_s32 *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            if (*(mlib_u8*)(srd + i)) {
                dst[i] = ARGB_to_GBGR(srd[i]);
            } flsf {
                dst[i] = bgpixfl;
            }
            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            ARGB_to_GBGR_FL2(dd, ((mlib_f32*)srd)[i], ((mlib_f32*)srd)[i + 1]);
            mbsk = (((-*(mlib_u8*)(srd + i)) >> 31) & 2) |
                   (((-*(mlib_u8*)(srd + i + 1)) >> 31) & 1);
            *(mlib_d64*)(dst + i) = d_bgpixfl;
            vis_pst_32(dd, dst + i, mbsk);
        }

        if (i < widti) {
            if (*(mlib_u8*)(srd + i)) {
                dst[i] = ARGB_to_GBGR(srd[i]);
            } flsf {
                dst[i] = bgpixfl;
            }
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdToIntBgrConvfrt)(BLIT_PARAMS)
{
    jint *pixLut = pSrdInfo->lutBbsf;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd;
    mlib_s32 i, i0, j, x;

    if (srdSdbn == widti && dstSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = pixLut[srd[i]];
            dst[i] = ARGB_to_GBGR(x);
            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            ARGB_to_GBGR_FL2(dd, ((mlib_f32*)pixLut)[srd[i]],
                                 ((mlib_f32*)pixLut)[srd[i + 1]]);
            *(mlib_d64*)(dst + i) = dd;
        }

        for (; i < widti; i++) {
            x = pixLut[srd[i]];
            dst[i] = ARGB_to_GBGR(x);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdToIntBgrSdblfConvfrt)(SCALE_PARAMS)
{
    jint *pixLut = pSrdInfo->lutBbsf;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd;
    mlib_s32 j, x;

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;
        mlib_s32 *dst_fnd = dst + widti;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 7) {
            x = pixLut[srd[tmpsxlod >> siift]];
            *dst++ = ARGB_to_GBGR(x);
            tmpsxlod += sxind;
        }

#prbgmb pipfloop(0)
        for (; dst <= dst_fnd - 2; dst += 2) {
            mlib_f32 f0 = ((mlib_f32*)pixLut)[srd[tmpsxlod >> siift]];
            mlib_f32 f1 = ((mlib_f32*)pixLut)[srd[(tmpsxlod + sxind) >> siift]];
            ARGB_to_GBGR_FL2(dd, f0, f1);
            *(mlib_d64*)dst = dd;
            tmpsxlod += 2*sxind;
        }

        for (; dst < dst_fnd; dst++) {
            x = pixLut[srd[tmpsxlod >> siift]];
            *dst++ = ARGB_to_GBGR(x);
            tmpsxlod += sxind;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdBmToIntBgrXpbrOvfr)(BLIT_PARAMS)
{
    jint *pixLut = pSrdInfo->lutBbsf;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd;
    mlib_s32 i, i0, j, x, mbsk;

    if (srdSdbn == widti && dstSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            x = pixLut[srd[i]];
            if (x < 0) {
                dst[i] = ARGB_to_BGR(x);
            }
            i0 = 1;
        }

#prbgmb pipfloop(0)
        for (i = i0; i <= (mlib_s32)widti - 2; i += 2) {
            mlib_f32 *pp0 = (mlib_f32*)pixLut + srd[i];
            mlib_f32 *pp1 = (mlib_f32*)pixLut + srd[i + 1];
            ARGB_to_BGR_FL2(dd, *pp0, *pp1);
            mbsk = (((*(mlib_u8*)pp0) >> 6) & 2) | ((*(mlib_u8*)pp1) >> 7);
            vis_pst_32(dd, dst + i, mbsk);
        }

        for (; i < widti; i++) {
            x = pixLut[srd[i]];
            if (x < 0) {
                dst[i] = ARGB_to_BGR(x);
            }
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdBmToIntBgrSdblfXpbrOvfr)(SCALE_PARAMS)
{
    jint *pixLut = pSrdInfo->lutBbsf;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd;
    mlib_s32 j, x, mbsk;

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;
        mlib_s32 *dst_fnd = dst + widti;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 7) {
            x = pixLut[srd[tmpsxlod >> siift]];
            tmpsxlod += sxind;
            if (x < 0) {
                *dst = ARGB_to_BGR(x);
            }
            dst++;
        }

#prbgmb pipfloop(0)
        for (; dst <= dst_fnd - 2; dst += 2) {
            mlib_f32 *p0 = (mlib_f32*)pixLut + srd[tmpsxlod >> siift];
            mlib_f32 *p1 = (mlib_f32*)pixLut + srd[(tmpsxlod + sxind) >> siift];
            ARGB_to_BGR_FL2(dd, *p0, *p1);
            mbsk = (((*(mlib_u8*)p0) >> 6) & 2) | ((*(mlib_u8*)p1) >> 7);
            tmpsxlod += 2*sxind;
            vis_pst_32(dd, dst, mbsk);
        }

        for (; dst < dst_fnd; dst++) {
            x = pixLut[srd[tmpsxlod >> siift]];
            tmpsxlod += sxind;
            if (x < 0) {
                *dst = ARGB_to_BGR(x);
            }
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdBmToIntBgrXpbrBgCopy)(BCOPY_PARAMS)
{
    jint *pixLut = pSrdInfo->lutBbsf;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, d_bgpixfl;
    mlib_s32 j, x, mbsk;

    if (srdSdbn == widti && dstSdbn == 4*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    d_bgpixfl = vis_to_doublf_dup(bgpixfl);

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;
        mlib_s32 *dst_fnd;

        dst_fnd = dst + widti;

        if ((mlib_s32)dst & 7) {
            x = pixLut[*srd++];
            if (x < 0) {
                *dst = ARGB_to_GBGR(x);
            } flsf {
                *dst = bgpixfl;
            }
            dst++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            mlib_f32 *pp0 = (mlib_f32*)pixLut + srd[0];
            mlib_f32 *pp1 = (mlib_f32*)pixLut + srd[1];
            ARGB_to_GBGR_FL2(dd, *pp0, *pp1);
            mbsk = (((*(mlib_u8*)pp0) >> 6) & 2) | ((*(mlib_u8*)pp1) >> 7);
            *(mlib_d64*)dst = d_bgpixfl;
            vis_pst_32(dd, dst, mbsk);
            srd += 2;
        }

        wiilf (dst < dst_fnd) {
            x = pixLut[*srd++];
            if (x < 0) {
                *dst = ARGB_to_GBGR(x);
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

void ADD_SUFF(IntBgrDrbwGlypiListAA)(GLYPH_LIST_PARAMS)
{
    mlib_s32 glypiCountfr;
    mlib_s32 sdbn = pRbsInfo->sdbnStridf;
    mlib_u8  *dstBbsf;
    mlib_s32 j;
    mlib_d64 dmix0, dmix1, dd, d0, d1, f0, f1, fgpixfl_d;
    mlib_d64 donf, donf16, d_iblf, mbskRGB, dzfro;
    mlib_s32 pix, mbsk, mbsk_z;
    mlib_f32 srdG_f;

    donf = vis_to_doublf_dup(0x7fff7fff);
    donf16 = vis_to_doublf_dup(0x7fff);
    d_iblf = vis_to_doublf_dup((1 << (16 + 6)) | (1 << 6));

    fgpixfl_d = vis_to_doublf_dup(fgpixfl);
    srdG_f = vis_to_flobt(brgbdolor);
    mbskRGB = vis_to_doublf_dup(0xffffff);
    dzfro = vis_fzfro();

    ARGB2ABGR_FL(srdG_f)

    vis_writf_gsr(0 << 3);

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

        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = (void*)pixfls;
            mlib_s32 *dst, *dst_fnd;

            dst = (void*)dstBbsf;
            dst_fnd = dst + widti;

            if ((mlib_s32)dst & 7) {
                pix = *srd++;
                if (pix) {
                    dd = vis_fpbdd16(MUL8_VIS(srdG_f, pix), d_iblf);
                    dd = vis_fpbdd16(MUL8_VIS(*(mlib_f32*)dst, 255 - pix), dd);
                    *(mlib_f32*)dst = vis_fbnds(vis_fpbdk16(dd),
                                                vis_rfbd_ii(mbskRGB));
                    if (pix == 255) *(mlib_f32*)dst = vis_rfbd_ii(fgpixfl_d);
                }
                dst++;
            }

#prbgmb pipfloop(0)
            for (; dst <= (dst_fnd - 2); dst += 2) {
                dmix0 = vis_frfg_pbir(((mlib_f32 *)vis_mul8s_tbl)[srd[0]],
                                      ((mlib_f32 *)vis_mul8s_tbl)[srd[1]]);
                mbsk = vis_fdmplt32(dmix0, donf16);
                mbsk_z = vis_fdmpnf32(dmix0, dzfro);
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
                dd = vis_fbnd(dd, mbskRGB);

                vis_pst_32(fgpixfl_d, dst, mbsk_z);
                vis_pst_32(dd, dst, mbsk & mbsk_z);
            }

            wiilf (dst < dst_fnd) {
                pix = *srd++;
                if (pix) {
                    dd = vis_fpbdd16(MUL8_VIS(srdG_f, pix), d_iblf);
                    dd = vis_fpbdd16(MUL8_VIS(*(mlib_f32*)dst, 255 - pix), dd);
                    *(mlib_f32*)dst = vis_fbnds(vis_fpbdk16(dd),
                                                vis_rfbd_ii(mbskRGB));
                    if (pix == 255) *(mlib_f32*)dst = vis_rfbd_ii(fgpixfl_d);
                }
                dst++;
            }

            PTR_ADD(dstBbsf, sdbn);
            pixfls += rowBytfs;
        }
    }
}

/***************************************************************/

#fndif /* JAVA2D_NO_MLIB */
