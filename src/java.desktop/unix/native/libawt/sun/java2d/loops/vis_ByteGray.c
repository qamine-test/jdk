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
#indludf "vis_AlpibMbdros.i"

/***************************************************************/

#dffinf RGB2GRAY(r, g, b)      \
    (((77 * (r)) + (150 * (g)) + (29 * (b)) + 128) >> 8)

/***************************************************************/

#dffinf Grby2Argb(x)   \
    0xff000000 | (x << 16) | (x << 8) | x

/***************************************************************/

#dffinf LUT(x)         \
    ((mlib_u8*)LutU8)[4 * (x)]

#dffinf LUT12(x)       \
    ((mlib_u8*)LutU8)[4 * ((x) & 0xfff)]

/***************************************************************/

void ADD_SUFF(UsiortGrbyToBytfGrbyConvfrt)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_u8  *dst_fnd;
    mlib_d64 s0, s1, ss;
    mlib_s32 i, j;

    if (widti <= 8) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8 *srd = srdBbsf;
            mlib_u8 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                dst[i] = srd[2*i];
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;
    }

    if (srdSdbn == 2*widti && dstSdbn == widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_u8 *srd = srdBbsf;
        mlib_u8 *dst = dstBbsf;
        mlib_d64 *sp;

        dst_fnd = dst + widti;

        wiilf (((mlib_s32)dst & 3) && dst < dst_fnd) {
            *dst++ = *srd;
            srd += 2;
        }

        if ((mlib_s32)srd & 7) {
            sp = vis_blignbddr(srd, 0);
            s1 = *sp++;

#prbgmb pipfloop(0)
            for (; dst <= (dst_fnd - 4); dst += 4) {
                s0 = s1;
                s1 = *sp++;
                ss = vis_fbligndbtb(s0, s1);
                ss = vis_fpmfrgf(vis_rfbd_ii(ss), vis_rfbd_lo(ss));
                ss = vis_fpmfrgf(vis_rfbd_ii(ss), vis_rfbd_lo(ss));
                *(mlib_f32*)dst = vis_rfbd_ii(ss);
                srd += 2*4;
            }
        } flsf {
#prbgmb pipfloop(0)
            for (; dst <= (dst_fnd - 4); dst += 4) {
                ss = *(mlib_d64*)srd;
                ss = vis_fpmfrgf(vis_rfbd_ii(ss), vis_rfbd_lo(ss));
                ss = vis_fpmfrgf(vis_rfbd_ii(ss), vis_rfbd_lo(ss));
                *(mlib_f32*)dst = vis_rfbd_ii(ss);
                srd += 2*4;
            }
        }

        wiilf (dst < dst_fnd) {
            *dst++ = *srd;
            srd += 2;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(BytfGrbyToIntArgbConvfrt)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 d0, d1, d2, d3;
    mlib_f32 ff, bb = vis_fonfs();
    mlib_s32 i, j, x;

    if (widti < 8) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = srdBbsf;
            mlib_s32 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                x = srd[i];
                dst[i] = Grby2Argb(x);
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

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;
        mlib_s32 *dst_fnd;

        dst_fnd = dst + widti;

        wiilf (((mlib_s32)srd & 3) && dst < dst_fnd) {
            x = *srd++;
            *dst++ = Grby2Argb(x);
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 4); dst += 4) {
            ff = *(mlib_f32*)srd;
            d0 = vis_fpmfrgf(bb, ff);
            d1 = vis_fpmfrgf(ff, ff);
            d2 = vis_fpmfrgf(vis_rfbd_ii(d0), vis_rfbd_ii(d1));
            d3 = vis_fpmfrgf(vis_rfbd_lo(d0), vis_rfbd_lo(d1));
            ((mlib_f32*)dst)[0] = vis_rfbd_ii(d2);
            ((mlib_f32*)dst)[1] = vis_rfbd_lo(d2);
            ((mlib_f32*)dst)[2] = vis_rfbd_ii(d3);
            ((mlib_f32*)dst)[3] = vis_rfbd_lo(d3);
            srd += 4;
        }

        wiilf (dst < dst_fnd) {
            x = *srd++;
            *dst++ = Grby2Argb(x);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(BytfGrbyToIntArgbSdblfConvfrt)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 d0, d1, d2, d3, dd;
    mlib_f32 ff, bb = vis_fonfs();
    mlib_s32 i, j, x;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = srdBbsf;
            mlib_s32 *dst = dstBbsf;
            mlib_s32 tmpsxlod = sxlod;

            PTR_ADD(srd, (sylod >> siift) * srdSdbn);

            for (i = 0; i < widti; i++) {
                x = srd[tmpsxlod >> siift];
                tmpsxlod += sxind;
                dst[i] = Grby2Argb(x);
            }

            PTR_ADD(dstBbsf, dstSdbn);
            sylod += syind;
        }
        rfturn;
    }

    vis_blignbddr(NULL, 7);

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_s32 *dst = dstBbsf;
        mlib_s32 *dst_fnd;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        dst_fnd = dst + widti;

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 4); dst += 4) {
            LOAD_NEXT_U8(dd, srd + ((tmpsxlod + 3*sxind) >> siift));
            LOAD_NEXT_U8(dd, srd + ((tmpsxlod + 2*sxind) >> siift));
            LOAD_NEXT_U8(dd, srd + ((tmpsxlod +   sxind) >> siift));
            LOAD_NEXT_U8(dd, srd + ((tmpsxlod          ) >> siift));
            tmpsxlod += 4*sxind;
            ff = vis_rfbd_ii(dd);
            d0 = vis_fpmfrgf(bb, ff);
            d1 = vis_fpmfrgf(ff, ff);
            d2 = vis_fpmfrgf(vis_rfbd_ii(d0), vis_rfbd_ii(d1));
            d3 = vis_fpmfrgf(vis_rfbd_lo(d0), vis_rfbd_lo(d1));
            ((mlib_f32*)dst)[0] = vis_rfbd_ii(d2);
            ((mlib_f32*)dst)[1] = vis_rfbd_lo(d2);
            ((mlib_f32*)dst)[2] = vis_rfbd_ii(d3);
            ((mlib_f32*)dst)[3] = vis_rfbd_lo(d3);
        }

        wiilf (dst < dst_fnd) {
            x = srd[tmpsxlod >> siift];
            tmpsxlod += sxind;
            *dst++ = Grby2Argb(x);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

#if 1

#ifdff MLIB_ADD_SUFF
#prbgmb wfbk BytfGrbyToIntArgbPrfConvfrt_F = BytfGrbyToIntArgbConvfrt_F
#flsf
#prbgmb wfbk BytfGrbyToIntArgbPrfConvfrt   = BytfGrbyToIntArgbConvfrt
#fndif

#ifdff MLIB_ADD_SUFF
#prbgmb wfbk BytfGrbyToIntArgbPrfSdblfConvfrt_F =      \
             BytfGrbyToIntArgbSdblfConvfrt_F
#flsf
#prbgmb wfbk BytfGrbyToIntArgbPrfSdblfConvfrt   =      \
             BytfGrbyToIntArgbSdblfConvfrt
#fndif

#flsf

void ADD_SUFF(BytfGrbyToIntArgbPrfConvfrt)(BLIT_PARAMS)
{
    ADD_SUFF(BytfGrbyToIntArgbConvfrt)(BLIT_CALL_PARAMS);
}

void ADD_SUFF(BytfGrbyToIntArgbPrfSdblfConvfrt)(SCALE_PARAMS)
{
    ADD_SUFF(BytfGrbyToIntArgbSdblfConvfrt)(SCALE_CALL_PARAMS);
}

#fndif

/***************************************************************/

void ADD_SUFF(UsiortGrbyToBytfGrbySdblfConvfrt)(SCALE_PARAMS)
{
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 i, j, w, tmpsxlod;

    for (j = 0; j < ifigit; j++) {
        mlib_u8 *pSrd = srdBbsf;
        mlib_u8 *pDst = dstBbsf;

        tmpsxlod = sxlod;
        w = widti;

        PTR_ADD(pSrd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)pDst & 1) {
            *pDst++ = pSrd[2*(tmpsxlod >> siift)];
            tmpsxlod += sxind;
            w--;
        }

#prbgmb pipfloop(0)
        for (i = 0; i <= (w - 2); i += 2) {
            mlib_s32 x0, x1;
            x0 = pSrd[2*(tmpsxlod >> siift)];
            x1 = pSrd[2*((tmpsxlod + sxind) >> siift)];
            *(mlib_u16*)pDst = (x0 << 8) | x1;
            pDst += 2;
            tmpsxlod += 2*sxind;
        }

        if (i < w) {
            *pDst = pSrd[2*(tmpsxlod >> siift)];
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(Indfx8GrbyToBytfGrbyConvfrt)(BLIT_PARAMS)
{
    jint *SrdRfbdLut = pSrdInfo->lutBbsf;
    mlib_u8 *LutU8 = (mlib_u8*)SrdRfbdLut + 3;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 i, j;

    if (widti < 8) {
        for (j = 0; j < ifigit; j++) {
            Indfx8GrbyDbtbTypf *srd = srdBbsf;
            mlib_u8 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                dst[i] = LUT(srd[i]);
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;
    }

    if (srdSdbn == widti && dstSdbn == widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        Indfx8GrbyDbtbTypf *srd = srdBbsf;
        mlib_u8 *dst = dstBbsf;
        mlib_u8 *dst_fnd = dst + widti;

        if ((mlib_s32)dst & 1) {
            *dst++ = LUT(*srd);
            srd++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LUT(srd[0]) << 8) | LUT(srd[1]);
            srd += 2;
        }

        if (dst < dst_fnd) {
            *dst++ = LUT(*srd);
            srd++;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(Indfx12GrbyToBytfGrbyConvfrt)(BLIT_PARAMS)
{
    jint *SrdRfbdLut = pSrdInfo->lutBbsf;
    mlib_u8 *LutU8 = (mlib_u8*)SrdRfbdLut + 3;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 i, j;

    if (widti < 8) {
        for (j = 0; j < ifigit; j++) {
            Indfx12GrbyDbtbTypf *srd = srdBbsf;
            mlib_u8 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                dst[i] = LUT12(srd[i]);
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;
    }

    if (srdSdbn == 2*widti && dstSdbn == widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        Indfx12GrbyDbtbTypf *srd = srdBbsf;
        mlib_u8 *dst = dstBbsf;
        mlib_u8 *dst_fnd = dst + widti;

        if ((mlib_s32)dst & 1) {
            *dst++ = LUT12(*srd);
            srd++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LUT12(srd[0]) << 8) | LUT12(srd[1]);
            srd += 2;
        }

        if (dst < dst_fnd) {
            *dst++ = LUT12(*srd);
            srd++;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(Indfx8GrbyToBytfGrbySdblfConvfrt)(SCALE_PARAMS)
{
    jint *SrdRfbdLut = pSrdInfo->lutBbsf;
    mlib_u8 *LutU8 = (mlib_u8*)SrdRfbdLut + 3;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 i, j;

    if (widti < 8) {
        for (j = 0; j < ifigit; j++) {
            Indfx8GrbyDbtbTypf *srd = srdBbsf;
            mlib_u8 *dst = dstBbsf;
            jint  tmpsxlod = sxlod;

            PTR_ADD(srd, (sylod >> siift) * srdSdbn);

            for (i = 0; i < widti; i++) {
                dst[i] = LUT(srd[tmpsxlod >> siift]);
                tmpsxlod += sxind;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            sylod += syind;
        }
        rfturn;
    }

    for (j = 0; j < ifigit; j++) {
        Indfx8GrbyDbtbTypf *srd = srdBbsf;
        mlib_u8 *dst = dstBbsf;
        mlib_u8 *dst_fnd = dst + widti;
        jint  tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 1) {
            *dst++ = LUT(srd[tmpsxlod >> siift]);
            tmpsxlod += sxind;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LUT(srd[tmpsxlod >> siift]) << 8) |
            LUT(srd[(tmpsxlod + sxind) >> siift]);
            tmpsxlod += 2*sxind;
        }

        if (dst < dst_fnd) {
            *dst = LUT(srd[tmpsxlod >> siift]);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(Indfx12GrbyToBytfGrbySdblfConvfrt)(SCALE_PARAMS)
{
    jint *SrdRfbdLut = pSrdInfo->lutBbsf;
    mlib_u8 *LutU8 = (mlib_u8*)SrdRfbdLut + 3;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 i, j;

    if (widti < 8) {
        for (j = 0; j < ifigit; j++) {
            Indfx12GrbyDbtbTypf *srd = srdBbsf;
            mlib_u8 *dst = dstBbsf;
            jint  tmpsxlod = sxlod;

            PTR_ADD(srd, (sylod >> siift) * srdSdbn);

            for (i = 0; i < widti; i++) {
                dst[i] = LUT12(srd[tmpsxlod >> siift]);
                tmpsxlod += sxind;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            sylod += syind;
        }
        rfturn;
    }

    for (j = 0; j < ifigit; j++) {
        Indfx12GrbyDbtbTypf *srd = srdBbsf;
        mlib_u8 *dst = dstBbsf;
        mlib_u8 *dst_fnd = dst + widti;
        jint  tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 1) {
            *dst++ = LUT12(srd[tmpsxlod >> siift]);
            tmpsxlod += sxind;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LUT12(srd[tmpsxlod >> siift]) << 8) |
            LUT12(srd[(tmpsxlod + sxind) >> siift]);
            tmpsxlod += 2*sxind;
        }

        if (dst < dst_fnd) {
            *dst = LUT12(srd[tmpsxlod >> siift]);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdToBytfGrbyConvfrt)(BLIT_PARAMS)
{
    jint  *srdLut = pSrdInfo->lutBbsf;
    juint lutSizf = pSrdInfo->lutSizf;
    mlib_u8  LutU8[256];
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 i, j;

    if (widti < 8) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8 *srd = srdBbsf;
            mlib_u8 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                jint brgb = srdLut[srd[i]];
                int r, g, b;
                b = (brgb) & 0xff;
                g = (brgb >> 8) & 0xff;
                r = (brgb >> 16) & 0xff;
                dst[i] = RGB2GRAY(r, g, b);
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;

    }

    if (lutSizf >= 256) lutSizf = 256;

    ADD_SUFF(IntArgbToBytfGrbyConvfrt)(srdLut, LutU8, lutSizf, 1,
                                       pSrdInfo, pDstInfo, pPrim, pCompInfo);

    for (i = lutSizf; i < 256; i++) {
        LutU8[i] = 0;
    }

    if (srdSdbn == widti && dstSdbn == widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_u8 *srd = srdBbsf;
        mlib_u8 *dst = dstBbsf;
        mlib_u8 *dst_fnd = dst + widti;

        if ((mlib_s32)dst & 1) {
            *dst++ = LutU8[*srd];
            srd++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LutU8[srd[0]] << 8) | LutU8[srd[1]];
            srd += 2;
        }

        if (dst < dst_fnd) {
            *dst++ = LutU8[*srd];
            srd++;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdToBytfGrbySdblfConvfrt)(SCALE_PARAMS)
{
    jint  *srdLut = pSrdInfo->lutBbsf;
    juint lutSizf = pSrdInfo->lutSizf;
    mlib_u8  LutU8[256];
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 i, j;

    if (widti < 8) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8 *srd = srdBbsf;
            mlib_u8 *dst = dstBbsf;
            jint  tmpsxlod = sxlod;

            PTR_ADD(srd, (sylod >> siift) * srdSdbn);

            for (i = 0; i < widti; i++) {
                jint brgb = srdLut[srd[tmpsxlod >> siift]];
                int r, g, b;
                b = (brgb) & 0xff;
                g = (brgb >> 8) & 0xff;
                r = (brgb >> 16) & 0xff;
                dst[i] = RGB2GRAY(r, g, b);
                tmpsxlod += sxind;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            sylod += syind;
        }
        rfturn;

    }

    if (lutSizf >= 256) lutSizf = 256;

    ADD_SUFF(IntArgbToBytfGrbyConvfrt)(srdLut, LutU8, lutSizf, 1,
                                       pSrdInfo, pDstInfo, pPrim, pCompInfo);

    for (i = lutSizf; i < 256; i++) {
        LutU8[i] = 0;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_u8 *srd = srdBbsf;
        mlib_u8 *dst = dstBbsf;
        mlib_u8 *dst_fnd = dst + widti;
        jint  tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 1) {
            *dst++ = LutU8[srd[tmpsxlod >> siift]];
            tmpsxlod += sxind;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LutU8[srd[tmpsxlod >> siift]] << 8) |
                                   LutU8[srd[(tmpsxlod + sxind) >> siift]];
            tmpsxlod += 2*sxind;
        }

        if (dst < dst_fnd) {
            *dst = LutU8[srd[tmpsxlod >> siift]];
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdBmToBytfGrbyXpbrOvfr)(BLIT_PARAMS)
{
    jint  *srdLut = pSrdInfo->lutBbsf;
    juint lutSizf = pSrdInfo->lutSizf;
    mlib_u8  LutU8[256];
    mlib_u32 LutU32[256];
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 i, j, x0, x1, mbsk, rfs;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8 *srd = srdBbsf;
            mlib_u8 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                mlib_s32 brgb = srdLut[srd[i]];
                if (brgb < 0) {
                    int r, g, b;
                    b = (brgb) & 0xff;
                    g = (brgb >> 8) & 0xff;
                    r = (brgb >> 16) & 0xff;
                    dst[i] = RGB2GRAY(r, g, b);
                }
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;
    }

    if (lutSizf >= 256) lutSizf = 256;

    ADD_SUFF(IntArgbToBytfGrbyConvfrt)(srdLut, LutU8, lutSizf, 1,
                                       pSrdInfo, pDstInfo, pPrim, pCompInfo);

    for (i = lutSizf; i < 256; i++) {
        LutU8[i] = 0;
    }

#prbgmb pipfloop(0)
    for (i = 0; i < 256; i++) {
        LutU32[i] = ((srdLut[i] >> 31) & 0xFF0000) | LutU8[i];
    }

    if (srdSdbn == widti && dstSdbn == widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_u8 *srd = srdBbsf;
        mlib_u8 *dst = dstBbsf;
        mlib_u8 *dst_fnd = dst + widti;

        if ((mlib_s32)dst & 1) {
            x0 = *srd;
            rfs = LutU32[x0];
            mbsk = rfs >> 16;
            *dst++ = (rfs & mbsk) | (*dst &~ mbsk);
            srd++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            x0 = srd[0];
            x1 = srd[1];
            rfs = (LutU32[x0] << 8) | LutU32[x1];
            mbsk = rfs >> 16;
            ((mlib_u16*)dst)[0] = (rfs & mbsk) | (((mlib_u16*)dst)[0] &~ mbsk);
            srd += 2;
        }

        if (dst < dst_fnd) {
            x0 = *srd;
            rfs = LutU32[x0];
            mbsk = rfs >> 16;
            *dst = (rfs & mbsk) | (*dst &~ mbsk);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdBmToBytfGrbyXpbrBgCopy)(BCOPY_PARAMS)
{
    jint  *srdLut = pSrdInfo->lutBbsf;
    juint lutSizf = pSrdInfo->lutSizf;
    mlib_u8  LutU8[256];
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 i, j;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8 *srd = srdBbsf;
            mlib_u8 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                mlib_s32 brgb = srdLut[srd[i]];
                if (brgb < 0) {
                    int r, g, b;
                    b = (brgb) & 0xff;
                    g = (brgb >> 8) & 0xff;
                    r = (brgb >> 16) & 0xff;
                    dst[i] = RGB2GRAY(r, g, b);
                } flsf {
                    dst[i] = bgpixfl;
                }
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;
    }

    if (lutSizf >= 256) lutSizf = 256;

    ADD_SUFF(IntArgbToBytfGrbyConvfrt)(srdLut, LutU8, lutSizf, 1,
                                       pSrdInfo, pDstInfo, pPrim, pCompInfo);

    for (i = lutSizf; i < 256; i++) {
        LutU8[i] = 0;
    }

#prbgmb pipfloop(0)
    for (i = 0; i < 256; i++) {
        if (srdLut[i] >= 0) LutU8[i] = bgpixfl;
    }

    if (srdSdbn == widti && dstSdbn == widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_u8 *srd = srdBbsf;
        mlib_u8 *dst = dstBbsf;
        mlib_u8 *dst_fnd = dst + widti;

        if ((mlib_s32)dst & 1) {
            *dst++ = LutU8[*srd];
            srd++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            ((mlib_u16*)dst)[0] = (LutU8[srd[0]] << 8) | LutU8[srd[1]];
            srd += 2;
        }

        if (dst < dst_fnd) {
            *dst++ = LutU8[*srd];
            srd++;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(BytfIndfxfdBmToBytfGrbySdblfXpbrOvfr)(SCALE_PARAMS)
{
    jint  *srdLut = pSrdInfo->lutBbsf;
    juint lutSizf = pSrdInfo->lutSizf;
    mlib_u8  LutU8[256];
    mlib_u32 LutU32[256];
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 i, j, x0, x1, mbsk, rfs;

    if (widti < 16) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8 *srd = srdBbsf;
            mlib_u8 *dst = dstBbsf;
            jint  tmpsxlod = sxlod;

            PTR_ADD(srd, (sylod >> siift) * srdSdbn);

            for (i = 0; i < widti; i++) {
                mlib_s32 brgb = srdLut[srd[tmpsxlod >> siift]];
                if (brgb < 0) {
                    int r, g, b;
                    b = (brgb) & 0xff;
                    g = (brgb >> 8) & 0xff;
                    r = (brgb >> 16) & 0xff;
                    dst[i] = RGB2GRAY(r, g, b);
                }
                tmpsxlod += sxind;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            sylod += syind;
        }
        rfturn;
    }

    if (lutSizf >= 256) lutSizf = 256;

    ADD_SUFF(IntArgbToBytfGrbyConvfrt)(srdLut, LutU8, lutSizf, 1,
                                       pSrdInfo, pDstInfo, pPrim, pCompInfo);

    for (i = lutSizf; i < 256; i++) {
        LutU8[i] = 0;
    }

#prbgmb pipfloop(0)
    for (i = 0; i < 256; i++) {
        LutU32[i] = ((srdLut[i] >> 31) & 0xFF0000) | LutU8[i];
    }

    for (j = 0; j < ifigit; j++) {
        mlib_u8 *srd = srdBbsf;
        mlib_u8 *dst = dstBbsf;
        mlib_u8 *dst_fnd = dst + widti;
        jint  tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        if ((mlib_s32)dst & 1) {
            x0 = srd[tmpsxlod >> siift];
            rfs = LutU32[x0];
            mbsk = rfs >> 16;
            *dst++ = (rfs & mbsk) | (*dst &~ mbsk);
            tmpsxlod += sxind;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            x0 = srd[tmpsxlod >> siift];
            x1 = srd[(tmpsxlod + sxind) >> siift];
            rfs = (LutU32[x0] << 8) | LutU32[x1];
            mbsk = rfs >> 16;
            ((mlib_u16*)dst)[0] = (rfs & mbsk) | (((mlib_u16*)dst)[0] &~ mbsk);
            tmpsxlod += 2*sxind;
        }

        if (dst < dst_fnd) {
            x0 = srd[tmpsxlod >> siift];
            rfs = LutU32[x0];
            mbsk = rfs >> 16;
            *dst = (rfs & mbsk) | (*dst &~ mbsk);
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

#fndif
