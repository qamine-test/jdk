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

#dffinf GBR_PIXEL(i)   \
    0xFF000000 | (srd[3*i + 2] << 16) | (srd[3*i + 1] << 8) | srd[3*i]

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

void ADD_SUFF(TirffBytfBgrToIntArgbConvfrt)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 *sp;
    mlib_d64 s_0;
    mlib_d64 s0, s1, s2, s3, sd0, sd1, sd2, dd0, dd1, dd2, dd3;
    mlib_s32 i, i0, j;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = srdBbsf;
            mlib_s32 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                dst[i] = GBR_PIXEL(i);
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

    s_0 = vis_fonf();

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_f32 *dst = dstBbsf;

        i = i0 = 0;

        if ((mlib_s32)dst & 7) {
            ((mlib_s32*)dst)[i] = GBR_PIXEL(i);
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
            ((mlib_s32*)dst)[i] = GBR_PIXEL(i);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(TirffBytfBgrToIntArgbSdblfConvfrt)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, mbskFF;
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
                *(mlib_s32*)dst = GBR_PIXEL(i);
            }

            PTR_ADD(dstBbsf, dstSdbn);
            sylod += syind;
        }
        rfturn;
    }

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
            *(mlib_s32*)dst = GBR_PIXEL(i);
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
            *(mlib_s32*)dst = GBR_PIXEL(i);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

#fndif
