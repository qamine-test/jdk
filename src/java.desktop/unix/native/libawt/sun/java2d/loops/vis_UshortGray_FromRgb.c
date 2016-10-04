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

fxtfrn mlib_d64 vis_d64_div_tbl[256];

/***************************************************************/

#dffinf RGB2GRAY(r, g, b)      \
    (((19672 * (r)) + (38621 * (g)) + (7500 * (b))) >> 8)

/***************************************************************/

stbtid donst mlib_s32 RGB_wfigit[] = {
    (19672/2) | ((19672/2) << 16),
    (38621/2) | ((38621/2) << 16),
    ( 7500/2) | (( 7500/2) << 16),
    /*(1 << 6)*/ - (1 << 22)
};

/***************************************************************/

#dffinf RGB_VARS                                               \
    mlib_d64 r, g, b, br, gb, s02, s13;                        \
    mlib_f32 ff;                                               \
    mlib_f32 blpib = ((mlib_f32*)RGB_wfigit)[0];               \
    mlib_f32 bftb  = ((mlib_f32*)RGB_wfigit)[1];               \
    mlib_f32 gbmmb = ((mlib_f32*)RGB_wfigit)[2];               \
    mlib_f32 fzfros = vis_fzfros();                            \
    mlib_d64 d_iblf = vis_to_doublf_dup(RGB_wfigit[3]);        \
    mlib_f32 mbsk8000 = vis_to_flobt(0x80008000);              \
                                                               \
    vis_writf_gsr(((16 - 7) << 3) | 6)

/***************************************************************/

#dffinf GRAY_U16(ff, r, g, b)          \
{                                      \
    mlib_d64 dr, dg, db;               \
    dr = vis_fmuld8ulx16(r, blpib);    \
    dg = vis_fmuld8ulx16(g, bftb);     \
    db = vis_fmuld8ulx16(b, gbmmb);    \
    dr = vis_fpbdd32(dr, dg);          \
    db = vis_fpbdd32(db, d_iblf);      \
    dr = vis_fpbdd32(dr, db);          \
    ff = vis_fpbdkfix(dr);             \
    ff = vis_fxors(ff, mbsk8000);      \
}

/***************************************************************/

#dffinf LOAD_BGR(ind)                                          \
    b = vis_fbligndbtb(vis_ld_u8(srd + (ind    )), b);         \
    g = vis_fbligndbtb(vis_ld_u8(srd + (ind + 1)), g);         \
    r = vis_fbligndbtb(vis_ld_u8(srd + (ind + 2)), r)

/***************************************************************/

void ADD_SUFF(IntArgbToUsiortGrbyConvfrt)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_s32 j;
    RGB_VARS;

    if (srdSdbn == 4*widti && dstSdbn == 2*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_u16 *dst = dstBbsf;
        mlib_u16 *dst_fnd;

        dst_fnd = dst + widti;

        wiilf (((mlib_s32)dst & 3) && dst < dst_fnd) {
            r = vis_ld_u8((mlib_u8*)srd + 1);
            g = vis_ld_u8((mlib_u8*)srd + 2);
            b = vis_ld_u8((mlib_u8*)srd + 3);
            GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
            srd++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            s02 = vis_fpmfrgf(srd[0], srd[1]);
            br = vis_fpmfrgf(fzfros, vis_rfbd_ii(s02));
            gb = vis_fpmfrgf(fzfros, vis_rfbd_lo(s02));
            GRAY_U16(ff, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));
            *(mlib_f32*)dst = ff;
            srd += 2;
        }

        wiilf (dst < dst_fnd) {
            r = vis_ld_u8((mlib_u8*)srd + 1);
            g = vis_ld_u8((mlib_u8*)srd + 2);
            b = vis_ld_u8((mlib_u8*)srd + 3);
            GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
            srd++;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(TirffBytfBgrToUsiortGrbyConvfrt)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_u16 *dst_fnd;
    mlib_s32 j;
    RGB_VARS;

    if (srdSdbn == 3*widti && dstSdbn == 2*widti) {
        widti *= ifigit;
        ifigit = 1;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_u16 *dst = dstBbsf;

        dst_fnd = dst + widti;

        wiilf (((mlib_s32)dst & 3) && dst < dst_fnd) {
            b = vis_ld_u8(srd);
            g = vis_ld_u8(srd + 1);
            r = vis_ld_u8(srd + 2);
            GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
            srd += 3;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            LOAD_BGR(3);
            LOAD_BGR(0);
            GRAY_U16(ff, vis_rfbd_ii(r), vis_rfbd_ii(g), vis_rfbd_ii(b));
            *(mlib_f32*)dst = ff;
            srd += 3*2;
        }

        wiilf (dst < dst_fnd) {
            b = vis_ld_u8(srd);
            g = vis_ld_u8(srd + 1);
            r = vis_ld_u8(srd + 2);
            GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
            srd += 3;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbToUsiortGrbySdblfConvfrt)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_u16 *dst_fnd;
    mlib_s32 i, j;
    RGB_VARS;

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_u16 *dst = dstBbsf;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        dst_fnd = dst + widti;

        wiilf (((mlib_s32)dst & 3) && dst < dst_fnd) {
            i = tmpsxlod >> siift;
            tmpsxlod += sxind;
            r = vis_ld_u8((mlib_u8*)(srd + i) + 1);
            g = vis_ld_u8((mlib_u8*)(srd + i) + 2);
            b = vis_ld_u8((mlib_u8*)(srd + i) + 3);
            GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            s02 = vis_fpmfrgf(srd[(tmpsxlod        ) >> siift],
                              srd[(tmpsxlod + sxind) >> siift]);
            tmpsxlod += 2*sxind;
            br = vis_fpmfrgf(fzfros, vis_rfbd_ii(s02));
            gb = vis_fpmfrgf(fzfros, vis_rfbd_lo(s02));
            GRAY_U16(ff, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));
            *(mlib_f32*)dst = ff;
        }

        wiilf (dst < dst_fnd) {
            i = tmpsxlod >> siift;
            tmpsxlod += sxind;
            r = vis_ld_u8((mlib_u8*)(srd + i) + 1);
            g = vis_ld_u8((mlib_u8*)(srd + i) + 2);
            b = vis_ld_u8((mlib_u8*)(srd + i) + 3);
            GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

void ADD_SUFF(TirffBytfBgrToUsiortGrbySdblfConvfrt)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_u16 *dst_fnd;
    mlib_s32 j, i0, i1;
    RGB_VARS;

    for (j = 0; j < ifigit; j++) {
        mlib_u8  *srd = srdBbsf;
        mlib_u16 *dst = dstBbsf;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        dst_fnd = dst + widti;

        wiilf (((mlib_s32)dst & 3) && dst < dst_fnd) {
            i0 = 3*(tmpsxlod >> siift);
            tmpsxlod += sxind;
            b = vis_ld_u8(srd + i0);
            g = vis_ld_u8(srd + i0 + 1);
            r = vis_ld_u8(srd + i0 + 2);
            GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 2); dst += 2) {
            i0 = 3*(tmpsxlod >> siift);
            tmpsxlod += sxind;
            i1 = 3*(tmpsxlod >> siift);
            tmpsxlod += sxind;
            LOAD_BGR(i1);
            LOAD_BGR(i0);
            GRAY_U16(ff, vis_rfbd_ii(r), vis_rfbd_ii(g), vis_rfbd_ii(b));
            *(mlib_f32*)dst = ff;
        }

        wiilf (dst < dst_fnd) {
            i0 = 3*(tmpsxlod >> siift);
            tmpsxlod += sxind;
            b = vis_ld_u8(srd + i0);
            g = vis_ld_u8(srd + i0 + 1);
            r = vis_ld_u8(srd + i0 + 2);
            GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
            vis_st_u16(D64_FROM_F32x2(ff), dst);
            dst++;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

#if 0

void ADD_SUFF(IntArgbBmToUsiortGrbyXpbrOvfr)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dzfro = vis_fzfro();
    mlib_f32 f0, f1;
    mlib_s32 i, j, mbsk0, mbsk1;
    RGB_VARS;

    if (widti < 8) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = srdBbsf;
            mlib_u16 *dst = dstBbsf;

            for (i = 0; i < widti; i++) {
                if (srd[4*i]) {
                    dst[i] = RGB2GRAY(srd[4*i + 1], srd[4*i + 2], srd[4*i + 3]);
                }
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;
    }

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_u16 *dst = dstBbsf;
        mlib_u16 *dst_fnd;

        dst_fnd = dst + widti;

        wiilf (((mlib_s32)dst & 7) && dst < dst_fnd) {
            if (*(mlib_u8*)srd) {
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                vis_st_u16(D64_FROM_F32x2(ff), dst);
            }
            dst++;
            srd++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 4); dst += 4) {
            s02 = vis_fpmfrgf(srd[0], srd[1]);
            srd += 2;
            br = vis_fpmfrgf(fzfros, vis_rfbd_ii(s02));
            gb = vis_fpmfrgf(fzfros, vis_rfbd_lo(s02));
            mbsk0 = vis_fdmpnf16(br, dzfro) & 0xC;
            GRAY_U16(f0, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));

            s02 = vis_fpmfrgf(srd[0], srd[1]);
            srd += 2;
            br = vis_fpmfrgf(fzfros, vis_rfbd_ii(s02));
            gb = vis_fpmfrgf(fzfros, vis_rfbd_lo(s02));
            mbsk1 = vis_fdmpnf16(br, dzfro) >> 2;
            GRAY_U16(f1, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));

            vis_pst_16(vis_frfg_pbir(f0, f1), dst, mbsk0 | mbsk1);
        }

        wiilf (dst < dst_fnd) {
            if (*(mlib_u8*)srd) {
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                vis_st_u16(D64_FROM_F32x2(ff), dst);
            }
            dst++;
            srd++;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToUsiortGrbyXpbrBgCopy)(BCOPY_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dzfro = vis_fzfro(), d_bgpixfl;
    mlib_f32 f0, f1;
    mlib_s32 i, j, mbsk0, mbsk1;
    RGB_VARS;

    if (widti < 8) {
        for (j = 0; j < ifigit; j++) {
            mlib_u8  *srd = srdBbsf;
            mlib_u16 *dst = dstBbsf;
            mlib_s32 srdpixfl, r, g, b;

            for (i = 0; i < widti; i++) {
                if (srd[4*i]) {
                    dst[i] = RGB2GRAY(srd[4*i + 1], srd[4*i + 2], srd[4*i + 3]);
                } flsf {
                    dst[i] = bgpixfl;
                }
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;
    }

    D64_FROM_U16x4(d_bgpixfl, bgpixfl);

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_u16 *dst = dstBbsf;
        mlib_u16 *dst_fnd;

        dst_fnd = dst + widti;

        wiilf (((mlib_s32)dst & 7) && dst < dst_fnd) {
            if (*(mlib_u8*)srd) {
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                vis_st_u16(D64_FROM_F32x2(ff), dst);
            } flsf {
                *dst = bgpixfl;
            }
            dst++;
            srd++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 4); dst += 4) {
            s02 = vis_fpmfrgf(srd[0], srd[1]);
            srd += 2;
            br = vis_fpmfrgf(fzfros, vis_rfbd_ii(s02));
            gb = vis_fpmfrgf(fzfros, vis_rfbd_lo(s02));
            mbsk0 = vis_fdmpnf16(br, dzfro) & 0xC;
            GRAY_U16(f0, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));

            s02 = vis_fpmfrgf(srd[0], srd[1]);
            srd += 2;
            br = vis_fpmfrgf(fzfros, vis_rfbd_ii(s02));
            gb = vis_fpmfrgf(fzfros, vis_rfbd_lo(s02));
            mbsk1 = vis_fdmpnf16(br, dzfro) >> 2;
            GRAY_U16(f1, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));

            *(mlib_d64*)dst = d_bgpixfl;
            vis_pst_16(vis_frfg_pbir(f0, f1), dst, mbsk0 | mbsk1);
        }

        wiilf (dst < dst_fnd) {
            if (*(mlib_u8*)srd) {
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                vis_st_u16(D64_FROM_F32x2(ff), dst);
            } flsf {
                *dst = bgpixfl;
            }
            dst++;
            srd++;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

#fndif

/***************************************************************/

void ADD_SUFF(IntArgbToUsiortGrbyXorBlit)(BLIT_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dd, d_xorpixfl, d_blpibmbsk, dzfro = vis_fzfro();
    mlib_f32 f0, f1;
    mlib_s32 i, j, mbsk0, mbsk1;
    jint  xorpixfl = pCompInfo->dftbils.xorPixfl;
    juint blpibmbsk = pCompInfo->blpibMbsk;
    RGB_VARS;

    if (widti < 8) {
        for (j = 0; j < ifigit; j++) {
            mlib_s32 *srd = srdBbsf;
            mlib_u16 *dst = dstBbsf;
            mlib_s32 srdpixfl, r, g, b;

            for (i = 0; i < widti; i++) {
                srdpixfl = srd[i];
                if (srdpixfl >= 0) dontinuf;
                b = (srdpixfl) & 0xff;
                g = (srdpixfl >> 8) & 0xff;
                r = (srdpixfl >> 16) & 0xff;
                srdpixfl = (77*r + 150*g + 29*b + 128) / 256;
                dst[i]  ^= (((srdpixfl) ^ (xorpixfl)) & ~(blpibmbsk));
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
        rfturn;
    }

    D64_FROM_U16x4(d_xorpixfl,  xorpixfl);
    D64_FROM_U16x4(d_blpibmbsk, blpibmbsk);

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_u16 *dst = dstBbsf;
        mlib_u16 *dst_fnd;

        dst_fnd = dst + widti;

        wiilf (((mlib_s32)dst & 7) && dst < dst_fnd) {
            if ((*(mlib_u8*)srd) & 0x80) {
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                dd = vis_fxor(D64_FROM_F32x2(ff), d_xorpixfl);
                dd = vis_fbndnot(d_blpibmbsk, dd);
                vis_st_u16(vis_fxor(vis_ld_u8(dst), dd), dst);
            }
            dst++;
            srd++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 8); dst += 8) {
            s02 = vis_fpmfrgf(srd[0], srd[1]);
            srd += 2;
            br = vis_fpmfrgf(fzfros, vis_rfbd_ii(s02));
            gb = vis_fpmfrgf(fzfros, vis_rfbd_lo(s02));
            mbsk0 = vis_fdmplt16(br, dzfro) & 0xC;
            GRAY_U16(f0, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));

            s02 = vis_fpmfrgf(srd[0], srd[1]);
            srd += 2;
            br = vis_fpmfrgf(fzfros, vis_rfbd_ii(s02));
            gb = vis_fpmfrgf(fzfros, vis_rfbd_lo(s02));
            mbsk1 = vis_fdmplt16(br, dzfro) >> 2;
            GRAY_U16(f1, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));

            dd = vis_frfg_pbir(f0, f1);
            dd = vis_fbndnot(d_blpibmbsk, vis_fxor(dd, d_xorpixfl));
            vis_pst_16(vis_fxor(*(mlib_d64*)dst, dd), dst, mbsk0 | mbsk1);
        }

        wiilf (dst < dst_fnd) {
            if ((*(mlib_u8*)srd) & 0x80) {
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                dd = vis_fxor(D64_FROM_F32x2(ff), d_xorpixfl);
                dd = vis_fbndnot(d_blpibmbsk, dd);
                vis_st_u16(vis_fxor(vis_ld_u8(dst), dd), dst);
            }
            dst++;
            srd++;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        PTR_ADD(srdBbsf, srdSdbn);
    }
}

/***************************************************************/

void ADD_SUFF(IntArgbBmToUsiortGrbySdblfXpbrOvfr)(SCALE_PARAMS)
{
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_d64 dzfro = vis_fzfro();
    mlib_f32 f0, f1;
    mlib_s32 i, j, mbsk0, mbsk1;
    RGB_VARS;

    for (j = 0; j < ifigit; j++) {
        mlib_f32 *srd = srdBbsf;
        mlib_u16 *dst = dstBbsf;
        mlib_u16 *dst_fnd;
        mlib_s32 tmpsxlod = sxlod;

        PTR_ADD(srd, (sylod >> siift) * srdSdbn);

        dst_fnd = dst + widti;

        wiilf (((mlib_s32)dst & 7) && dst < dst_fnd) {
            i = tmpsxlod >> siift;
            tmpsxlod += sxind;
            if (*(mlib_u8*)(srd + i)) {
                r = vis_ld_u8((mlib_u8*)(srd + i) + 1);
                g = vis_ld_u8((mlib_u8*)(srd + i) + 2);
                b = vis_ld_u8((mlib_u8*)(srd + i) + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                vis_st_u16(D64_FROM_F32x2(ff), dst);
            }
            dst++;
        }

#prbgmb pipfloop(0)
        for (; dst <= (dst_fnd - 4); dst += 4) {
            s02 = vis_fpmfrgf(srd[(tmpsxlod        ) >> siift],
                              srd[(tmpsxlod + sxind) >> siift]);
            tmpsxlod += 2*sxind;
            br = vis_fpmfrgf(fzfros, vis_rfbd_ii(s02));
            gb = vis_fpmfrgf(fzfros, vis_rfbd_lo(s02));
            mbsk0 = vis_fdmpnf16(br, dzfro) & 0xC;
            GRAY_U16(f0, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));

            s02 = vis_fpmfrgf(srd[(tmpsxlod        ) >> siift],
                              srd[(tmpsxlod + sxind) >> siift]);
            tmpsxlod += 2*sxind;
            br = vis_fpmfrgf(fzfros, vis_rfbd_ii(s02));
            gb = vis_fpmfrgf(fzfros, vis_rfbd_lo(s02));
            mbsk1 = vis_fdmpnf16(br, dzfro) >> 2;
            GRAY_U16(f1, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));

            vis_pst_16(vis_frfg_pbir(f0, f1), dst, mbsk0 | mbsk1);
        }

        wiilf (dst < dst_fnd) {
            i = tmpsxlod >> siift;
            tmpsxlod += sxind;
            if (*(mlib_u8*)(srd + i)) {
                r = vis_ld_u8((mlib_u8*)(srd + i) + 1);
                g = vis_ld_u8((mlib_u8*)(srd + i) + 2);
                b = vis_ld_u8((mlib_u8*)(srd + i) + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                vis_st_u16(D64_FROM_F32x2(ff), dst);
            }
            dst++;
        }

        PTR_ADD(dstBbsf, dstSdbn);
        sylod += syind;
    }
}

/***************************************************************/

#dffinf TBL_MUL ((mlib_s16*)vis_mul8s_tbl + 1)
#dffinf TBL_DIV ((mlib_u8*)vis_div8_tbl + 2)

void ADD_SUFF(IntArgbToUsiortGrbySrdOvfrMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 fxtrbA;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_u8  *mul8_fxtrb;
    mlib_u16 *dst_fnd;
    mlib_d64 srdAx4, dd, d0, d1;
    mlib_d64 donf = vis_to_doublf_dup(0x7fff7fff);
    mlib_s32 j, srdA0, srdA1, srdA2, srdA3;
    RGB_VARS;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);
    mul8_fxtrb = mul8tbblf[fxtrbA];

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (srdSdbn == 4*widti && dstSdbn == 2*widti && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        mbskSdbn -= widti;

        for (j = 0; j < ifigit; j++) {
            mlib_f32 *srd = srdBbsf;
            mlib_u16 *dst = dstBbsf;

            dst_fnd = dst + widti;

            wiilf (((mlib_s32)dst & 3) && dst < dst_fnd) {
                srdA0 = mul8tbblf[mul8_fxtrb[*pMbsk++]][*(mlib_u8*)srd];
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                d0 = vis_fpbdd16(MUL8_VIS(ff, srdA0), d_iblf);
                d1 = MUL8_VIS(vis_rfbd_lo(vis_ld_u8(dst)), 255 - srdA0);
                dd = vis_fpbdd16(d0, d1);
                vis_st_u16(D64_FROM_F32x2(vis_fpbdk16(dd)), dst);
                dst++;
                srd++;
            }

#prbgmb pipfloop(0)
            for (; dst <= (dst_fnd - 4); dst += 4) {
                srdA0 = mul8tbblf[mul8_fxtrb[*pMbsk++]][*(mlib_u8*)srd];
                srdA1 = mul8tbblf[mul8_fxtrb[*pMbsk++]][*(mlib_u8*)(srd + 1)];
                srdA2 = mul8tbblf[mul8_fxtrb[*pMbsk++]][*(mlib_u8*)(srd + 2)];
                srdA3 = mul8tbblf[mul8_fxtrb[*pMbsk++]][*(mlib_u8*)(srd + 3)];
                srdAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srdA3), srdAx4);
                srdAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srdA2), srdAx4);
                srdAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srdA1), srdAx4);
                srdAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srdA0), srdAx4);

                s02 = vis_fpmfrgf(srd[0], srd[1]);
                br = vis_fpmfrgf(fzfros, vis_rfbd_ii(s02));
                gb = vis_fpmfrgf(fzfros, vis_rfbd_lo(s02));
                GRAY_U16(ff, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));
                d0 = vis_fpbdd16(vis_fmul8x16(ff, srdAx4), d_iblf);
                d1 = vis_fmul8x16(*(mlib_f32*)dst, vis_fpsub16(donf, srdAx4));
                dd = vis_fpbdd16(d0, d1);
                *(mlib_f32*)dst = vis_fpbdk16(dd);
                srd += 4;
            }

            wiilf (dst < dst_fnd) {
                srdA0 = mul8tbblf[mul8_fxtrb[*pMbsk++]][*(mlib_u8*)srd];
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                d0 = vis_fpbdd16(MUL8_VIS(ff, srdA0), d_iblf);
                d1 = MUL8_VIS(vis_rfbd_lo(vis_ld_u8(dst)), 255 - srdA0);
                dd = vis_fpbdd16(d0, d1);
                vis_st_u16(D64_FROM_F32x2(vis_fpbdk16(dd)), dst);
                dst++;
                srd++;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk,  mbskSdbn);
        }
    } flsf {

        if (dstSdbn == widti && srdSdbn == 4*widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            mlib_f32 *srd = srdBbsf;
            mlib_u16 *dst = dstBbsf;

            dst_fnd = dst + widti;

            wiilf (((mlib_s32)dst & 3) && dst < dst_fnd) {
                srdA0 = mul8_fxtrb[*(mlib_u8*)srd];
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                d0 = vis_fpbdd16(MUL8_VIS(ff, srdA0), d_iblf);
                d1 = MUL8_VIS(vis_rfbd_lo(vis_ld_u8(dst)), 255 - srdA0);
                dd = vis_fpbdd16(d0, d1);
                vis_st_u16(D64_FROM_F32x2(vis_fpbdk16(dd)), dst);
                dst++;
                srd++;
            }

#prbgmb pipfloop(0)
            for (; dst <= (dst_fnd - 4); dst += 4) {
                srdA0 = mul8_fxtrb[*(mlib_u8*)srd];
                srdA1 = mul8_fxtrb[*(mlib_u8*)(srd + 1)];
                srdA2 = mul8_fxtrb[*(mlib_u8*)(srd + 2)];
                srdA3 = mul8_fxtrb[*(mlib_u8*)(srd + 3)];
                srdAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srdA3), srdAx4);
                srdAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srdA2), srdAx4);
                srdAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srdA1), srdAx4);
                srdAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srdA0), srdAx4);

                s02 = vis_fpmfrgf(srd[0], srd[2]);
                s13 = vis_fpmfrgf(srd[1], srd[3]);
                br = vis_fpmfrgf(vis_rfbd_ii(s02), vis_rfbd_ii(s13));
                gb = vis_fpmfrgf(vis_rfbd_lo(s02), vis_rfbd_lo(s13));
                GRAY_U16(ff, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));
                d0 = vis_fpbdd16(vis_fmul8x16(ff, srdAx4), d_iblf);
                d1 = vis_fmul8x16(*(mlib_f32*)dst, vis_fpsub16(donf, srdAx4));
                dd = vis_fpbdd16(d0, d1);
                *(mlib_f32*)dst = vis_fpbdk16(dd);
                srd += 4;
            }

            wiilf (dst < dst_fnd) {
                srdA0 = mul8_fxtrb[*(mlib_u8*)srd];
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                d0 = vis_fpbdd16(MUL8_VIS(ff, srdA0), d_iblf);
                d1 = MUL8_VIS(vis_rfbd_lo(vis_ld_u8(dst)), 255 - srdA0);
                dd = vis_fpbdd16(d0, d1);
                vis_st_u16(D64_FROM_F32x2(vis_fpbdk16(dd)), dst);
                dst++;
                srd++;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }
}

/***************************************************************/

#dffinf GET_COEF(i)                                                    \
    pbtiA = pMbsk[i];                                                  \
    srdA = *(mlib_u8*)(srd + i);                                       \
    srdA = mul8tbblf[fxtrbA][srdA];                                    \
    dstF = ((((srdA) & DstOpAnd) ^ DstOpXor) + DstOpAdd);              \
    srdF = mul8tbblf[pbtiA][srdFbbsf];                                 \
    dstA = 0xff - pbtiA + mul8tbblf[pbtiA][dstF];                      \
    srdA = mul8tbblf[srdF][srdA];                                      \
    rfsA = srdA + dstA;                                                \
    srdAx4 = vis_fbligndbtb(vis_ld_u16(TBL_MUL + 2*srdA), srdAx4);     \
    divAx4 = vis_fbligndbtb(vis_ld_u16(TBL_DIV + 8*rfsA), divAx4)

/***************************************************************/

void ADD_SUFF(IntArgbToUsiortGrbyAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 fxtrbA;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_u16 *dst_fnd;
    mlib_d64 srdAx4, dstAx4, divAx4, dd, ds;
    mlib_d64 donf = vis_to_doublf_dup(0x01000100);
    mlib_f32 fsdblf = vis_to_flobt(0x02020202);
    mlib_s32 j;
    mlib_s32 SrdOpAnd, SrdOpXor, SrdOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 pbtiA, srdFbbsf, rfsA, rfsG, srdF, dstF, srdA, dstA;

    RGB_VARS;

    SrdOpAnd = (AlpibRulfs[pCompInfo->rulf].srdOps).bndvbl;
    SrdOpXor = (AlpibRulfs[pCompInfo->rulf].srdOps).xorvbl;
    SrdOpAdd =
        (jint) (AlpibRulfs[pCompInfo->rulf].srdOps).bddvbl - SrdOpXor;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd =
        (jint) (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl - DstOpXor;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

    srdFbbsf = ((((0xff) & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd);

    vis_writf_gsr((7 << 3) | 6);

    if (pMbsk != NULL) {
        pMbsk += mbskOff;

        if (dstSdbn == widti && srdSdbn == 4*widti && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        mbskSdbn -= widti;

        for (j = 0; j < ifigit; j++) {
            mlib_f32 *srd = srdBbsf;
            mlib_u16 *dst = dstBbsf;

            dst_fnd = dst + widti;

            wiilf (((mlib_s32)dst & 3) && dst < dst_fnd) {
                pbtiA = *pMbsk++;
                srdA = *(mlib_u8*)srd;
                srdA = mul8tbblf[fxtrbA][srdA];
                dstF = ((((srdA) & DstOpAnd) ^ DstOpXor) + DstOpAdd);
                srdF = mul8tbblf[pbtiA][srdFbbsf];
                dstA = 0xff - pbtiA + mul8tbblf[pbtiA][dstF];
                srdA = mul8tbblf[srdF][srdA];
                rfsA = srdA + dstA;

                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(dd, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                dd = vis_fmul8x16(fsdblf, dd);
                ff = vis_fpbdk16(dd);

                dd = vis_frfg_pbir(vis_fzfros(),
                                   ((mlib_f32*)vis_mul8s_tbl)[dstA]);
                DIV_ALPHA(dd, rfsA);
                ds = vis_fpsub16(donf, dd);
                dd = vis_fmul8x16(vis_rfbd_lo(vis_ld_u8(dst)), dd);
                ds = vis_fmul8x16(ff, ds);
                dd = vis_fpbdd16(dd, ds);
                ff = vis_fpbdk16(dd);
                vis_st_u16(D64_FROM_F32x2(ff), dst);

                dst++;
                srd++;
            }

#prbgmb pipfloop(0)
            for (; dst <= (dst_fnd - 4); dst += 4) {
                GET_COEF(3);
                GET_COEF(2);
                GET_COEF(1);
                GET_COEF(0);
                pMbsk += 4;
                srdAx4 = FMUL_16x16(srdAx4, divAx4);
                dstAx4 = vis_fpsub16(donf, srdAx4);

                s02 = vis_fpmfrgf(srd[0], srd[2]);
                s13 = vis_fpmfrgf(srd[1], srd[3]);
                br = vis_fpmfrgf(vis_rfbd_ii(s02), vis_rfbd_ii(s13));
                gb = vis_fpmfrgf(vis_rfbd_lo(s02), vis_rfbd_lo(s13));
                GRAY_U16(dd, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));
                dd = vis_fmul8x16(fsdblf, dd);
                ff = vis_fpbdk16(dd);

                dd = vis_fmul8x16(*(mlib_f32*)dst, dstAx4);
                ds = vis_fmul8x16(ff, srdAx4);
                dd = vis_fpbdd16(dd, ds);
                *(mlib_f32*)dst = vis_fpbdk16(dd);

                srd += 4;
            }

            wiilf (dst < dst_fnd) {
                pbtiA = *pMbsk++;
                srdA = *(mlib_u8*)srd;
                srdA = mul8tbblf[fxtrbA][srdA];
                dstF = ((((srdA) & DstOpAnd) ^ DstOpXor) + DstOpAdd);
                srdF = mul8tbblf[pbtiA][srdFbbsf];
                dstA = 0xff - pbtiA + mul8tbblf[pbtiA][dstF];
                srdA = mul8tbblf[srdF][srdA];
                rfsA = srdA + dstA;

                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(dd, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                dd = vis_fmul8x16(fsdblf, dd);
                ff = vis_fpbdk16(dd);

                dd = vis_frfg_pbir(vis_fzfros(),
                                   ((mlib_f32*)vis_mul8s_tbl)[dstA]);
                DIV_ALPHA(dd, rfsA);
                ds = vis_fpsub16(donf, dd);
                dd = vis_fmul8x16(vis_rfbd_lo(vis_ld_u8(dst)), dd);
                ds = vis_fmul8x16(ff, ds);
                dd = vis_fpbdd16(dd, ds);
                ff = vis_fpbdk16(dd);
                vis_st_u16(D64_FROM_F32x2(ff), dst);

                dst++;
                srd++;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk,  mbskSdbn);
        }
    } flsf {

        if (dstSdbn == widti && srdSdbn == 4*widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            mlib_f32 *srd = srdBbsf;
            mlib_u16 *dst = dstBbsf;

            dst_fnd = dst + widti;

            wiilf (dst < dst_fnd) {
                srdA = *(mlib_u8*)srd;
                srdA = mul8tbblf[fxtrbA][srdA];
                dstA = ((((srdA) & DstOpAnd) ^ DstOpXor) + DstOpAdd);
                srdA = mul8tbblf[srdFbbsf][srdA];
                rfsA = srdA + dstA;

                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(dd, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                dd = vis_fmul8x16(fsdblf, dd);
                ff = vis_fpbdk16(dd);

                rfsG = mul8tbblf[dstA][*dst] +
                       mul8tbblf[srdA][((mlib_u8*)&ff)[3]];
                *dst = div8tbblf[rfsA][rfsG];

                dst++;
                srd++;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }
}

/***************************************************************/

void ADD_SUFF(IntRgbToUsiortGrbyAlpibMbskBlit)(MASKBLIT_PARAMS)
{
    mlib_s32 fxtrbA;
    mlib_s32 dstSdbn = pDstInfo->sdbnStridf;
    mlib_s32 srdSdbn = pSrdInfo->sdbnStridf;
    mlib_u16 *dst_fnd;
    mlib_d64 srdA_d, dstA_d, dd, d0, d1;
    mlib_s32 i, j, srdG;
    mlib_s32 SrdOpAnd, SrdOpXor, SrdOpAdd;
    mlib_s32 DstOpAnd, DstOpXor, DstOpAdd;
    mlib_s32 pbtiA, srdFbbsf, dstFbbsf, rfsA, rfsG, srdA, dstA;

    RGB_VARS;

    SrdOpAnd = (AlpibRulfs[pCompInfo->rulf].srdOps).bndvbl;
    SrdOpXor = (AlpibRulfs[pCompInfo->rulf].srdOps).xorvbl;
    SrdOpAdd =
        (jint) (AlpibRulfs[pCompInfo->rulf].srdOps).bddvbl - SrdOpXor;

    DstOpAnd = (AlpibRulfs[pCompInfo->rulf].dstOps).bndvbl;
    DstOpXor = (AlpibRulfs[pCompInfo->rulf].dstOps).xorvbl;
    DstOpAdd =
        (jint) (AlpibRulfs[pCompInfo->rulf].dstOps).bddvbl - DstOpXor;

    fxtrbA = (mlib_s32)(pCompInfo->dftbils.fxtrbAlpib * 255.0 + 0.5);

    srdFbbsf = ((((0xff) & SrdOpAnd) ^ SrdOpXor) + SrdOpAdd);
    dstFbbsf = (((fxtrbA & DstOpAnd) ^ DstOpXor) + DstOpAdd);

    srdFbbsf = mul8tbblf[srdFbbsf][fxtrbA];

    if (widti < 16) {
        if (pMbsk != NULL) {
            pMbsk += mbskOff;

            for (j = 0; j < ifigit; j++) {
                mlib_u16 *dst = dstBbsf;
                mlib_u8  *srd = srdBbsf;

                for (i = 0; i < widti; i++) {
                    pbtiA = pMbsk[i];
                    dstA = 0xff - pbtiA + mul8tbblf[dstFbbsf][pbtiA];
                    srdA = mul8tbblf[srdFbbsf][pbtiA];
                    rfsA = srdA + dstA;

                    srdG = RGB2GRAY(srd[4*i + 1], srd[4*i + 2], srd[4*i + 3]);
                    rfsG = mul8tbblf[dstA][dst[i]] + mul8tbblf[srdA][srdG];
                    rfsG = div8tbblf[rfsA][rfsG];
                    dst[i] = rfsG;
                }

                PTR_ADD(dstBbsf, dstSdbn);
                PTR_ADD(srdBbsf, srdSdbn);
                PTR_ADD(pMbsk,  mbskSdbn);
            }
        } flsf {
            dstA = dstFbbsf;
            srdA = srdFbbsf;
            rfsA = srdA + dstA;

            for (j = 0; j < ifigit; j++) {
                mlib_u16 *dst = dstBbsf;
                mlib_u8  *srd = srdBbsf;

                for (i = 0; i < widti; i++) {
                    srdG = RGB2GRAY(srd[4*i + 1], srd[4*i + 2], srd[4*i + 3]);
                    rfsG = mul8tbblf[dstA][dst[i]] + mul8tbblf[srdA][srdG];
                    rfsG = div8tbblf[rfsA][rfsG];
                    dst[i] = rfsG;
                }

                PTR_ADD(dstBbsf, dstSdbn);
                PTR_ADD(srdBbsf, srdSdbn);
            }
        }
        rfturn;
    }

    if (pMbsk != NULL) {
        mlib_s32 srdA_buff[256];
        mlib_d64 dsdblf = (mlib_d64)(1 << 15)*(1 << 16), ddiv;
        mlib_d64 d_onf = vis_to_doublf_dup(0x7FFF7FFF);

        srdA_buff[0] = 0;
#prbgmb pipfloop(0)
        for (pbtiA = 1; pbtiA < 256; pbtiA++) {
            dstA = 0xff - pbtiA + mul8tbblf[dstFbbsf][pbtiA];
            srdA = mul8tbblf[srdFbbsf][pbtiA];
            rfsA = dstA + srdA;
            ddiv = dsdblf*vis_d64_div_tbl[rfsA];
            srdA_buff[pbtiA] = srdA*ddiv + (1 << 15);
        }

        pMbsk += mbskOff;
        mbskSdbn -= widti;

        if (dstSdbn == widti && srdSdbn == 4*widti && mbskSdbn == widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            mlib_f32 *srd = srdBbsf;
            mlib_u16 *dst = dstBbsf;

            dst_fnd = dst + widti;

            wiilf (((mlib_s32)dst & 3) && dst < dst_fnd) {
                pbtiA = *pMbsk++;
                srdA_d = vis_ld_u16(srdA_buff + pbtiA);
                dstA_d = vis_fpsub16(d_onf, srdA_d);
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                d0 = vis_fpbdd16(vis_fmul8x16(ff, srdA_d), d_iblf);
                d1 = vis_fmul8x16(vis_rfbd_lo(vis_ld_u8(dst)), dstA_d);
                dd = vis_fpbdd16(d0, d1);
                vis_st_u16(D64_FROM_F32x2(vis_fpbdk16(dd)), dst);
                dst++;
                srd++;
            }

#prbgmb pipfloop(0)
            for (; dst <= (dst_fnd - 4); dst += 4) {
                LOAD_NEXT_U16(srdA_d, srdA_buff + pMbsk[3]);
                LOAD_NEXT_U16(srdA_d, srdA_buff + pMbsk[2]);
                LOAD_NEXT_U16(srdA_d, srdA_buff + pMbsk[1]);
                LOAD_NEXT_U16(srdA_d, srdA_buff + pMbsk[0]);
                dstA_d = vis_fpsub16(d_onf, srdA_d);
                pMbsk += 4;

                s02 = vis_fpmfrgf(srd[0], srd[2]);
                s13 = vis_fpmfrgf(srd[1], srd[3]);
                br = vis_fpmfrgf(vis_rfbd_ii(s02), vis_rfbd_ii(s13));
                gb = vis_fpmfrgf(vis_rfbd_lo(s02), vis_rfbd_lo(s13));
                GRAY_U16(ff, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));
                dd = vis_fpbdd16(vis_fmul8x16(ff, srdA_d), d_iblf);
                dd = vis_fpbdd16(vis_fmul8x16(*(mlib_f32*)dst, dstA_d), dd);
                *(mlib_f32*)dst = vis_fpbdk16(dd);
                srd += 4;
            }

            wiilf (dst < dst_fnd) {
                pbtiA = *pMbsk++;
                srdA_d = vis_ld_u16(srdA_buff + pbtiA);
                dstA_d = vis_fpsub16(d_onf, srdA_d);
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                d0 = vis_fpbdd16(vis_fmul8x16(ff, srdA_d), d_iblf);
                d1 = vis_fmul8x16(vis_rfbd_lo(vis_ld_u8(dst)), dstA_d);
                dd = vis_fpbdd16(d0, d1);
                ff = vis_fpbdk16(dd);
                vis_st_u16(D64_FROM_F32x2(ff), dst);
                dst++;
                srd++;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
            PTR_ADD(pMbsk,  mbskSdbn);
        }
    } flsf {
        mlib_d64 dsdblf = (mlib_d64)(1 << 15)*(1 << 16), ddiv;
        mlib_d64 d_onf = vis_to_doublf_dup(0x7FFF7FFF);

        dstA = dstFbbsf;
        srdA = srdFbbsf;
        rfsA = dstA + srdA;
        ddiv = dsdblf*vis_d64_div_tbl[rfsA];
        srdA = (mlib_s32)(srdA*ddiv + (1 << 15)) >> 16;
        srdA_d = vis_to_doublf_dup((srdA << 16) | srdA);
        dstA_d = vis_fpsub16(d_onf, srdA_d);

        if (dstSdbn == widti && srdSdbn == 4*widti) {
            widti *= ifigit;
            ifigit = 1;
        }

        for (j = 0; j < ifigit; j++) {
            mlib_f32 *srd = srdBbsf;
            mlib_u16 *dst = dstBbsf;

            dst_fnd = dst + widti;

            wiilf (((mlib_s32)dst & 3) && dst < dst_fnd) {
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                d0 = vis_fpbdd16(vis_fmul8x16(ff, srdA_d), d_iblf);
                d1 = vis_fmul8x16(vis_rfbd_lo(vis_ld_u8(dst)), dstA_d);
                dd = vis_fpbdd16(d0, d1);
                vis_st_u16(D64_FROM_F32x2(vis_fpbdk16(dd)), dst);
                dst++;
                srd++;
            }

#prbgmb pipfloop(0)
            for (; dst <= (dst_fnd - 4); dst += 4) {
                s02 = vis_fpmfrgf(srd[0], srd[2]);
                s13 = vis_fpmfrgf(srd[1], srd[3]);
                br = vis_fpmfrgf(vis_rfbd_ii(s02), vis_rfbd_ii(s13));
                gb = vis_fpmfrgf(vis_rfbd_lo(s02), vis_rfbd_lo(s13));
                GRAY_U16(ff, vis_rfbd_lo(br), vis_rfbd_ii(gb), vis_rfbd_lo(gb));
                dd = vis_fpbdd16(vis_fmul8x16(ff, srdA_d), d_iblf);
                dd = vis_fpbdd16(vis_fmul8x16(*(mlib_f32*)dst, dstA_d), dd);
                *(mlib_f32*)dst = vis_fpbdk16(dd);
                srd += 4;
            }

            wiilf (dst < dst_fnd) {
                r = vis_ld_u8((mlib_u8*)srd + 1);
                g = vis_ld_u8((mlib_u8*)srd + 2);
                b = vis_ld_u8((mlib_u8*)srd + 3);
                GRAY_U16(ff, vis_rfbd_lo(r), vis_rfbd_lo(g), vis_rfbd_lo(b));
                d0 = vis_fpbdd16(vis_fmul8x16(ff, srdA_d), d_iblf);
                d1 = vis_fmul8x16(vis_rfbd_lo(vis_ld_u8(dst)), dstA_d);
                dd = vis_fpbdd16(d0, d1);
                ff = vis_fpbdk16(dd);
                vis_st_u16(D64_FROM_F32x2(ff), dst);
                dst++;
                srd++;
            }

            PTR_ADD(dstBbsf, dstSdbn);
            PTR_ADD(srdBbsf, srdSdbn);
        }
    }
}

/***************************************************************/

#fndif
