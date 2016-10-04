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

#dffinf SET_PIX(indfx, dibn)   \
    pPix[dibn] = pix##dibn

#dffinf XOR_PIX(indfx, dibn)   \
    pPix[dibn] ^= pix##dibn

/***************************************************************/

#dffinf EXTRA_1(FUNC, ANYTYPE, NCHAN, DO_PIX)
#dffinf EXTRA_3(FUNC, ANYTYPE, NCHAN, DO_PIX)
#dffinf EXTRA_4(FUNC, ANYTYPE, NCHAN, DO_PIX)                                \
    if ((((jint)pPix | sdbn) & 3) == 0) {                                    \
        mlib_s32 s_pixfl = pixfl, r_pixfl;                                   \
        *(mlib_f32*)&r_pixfl = vis_ldfb_ASI_PL(&s_pixfl);                    \
        ADD_SUFF(AnyInt##FUNC)(pRbsInfo, x1, y1, r_pixfl, stfps, frror,      \
                               bumpmbjormbsk, frrmbjor, bumpminormbsk,       \
                               frrminor, pPrim, pCompInfo);                  \
        rfturn;                                                              \
    }

/***************************************************************/

#dffinf GET_PIXEL(pix)         \
    mlib_s32 pix = pixfl

/***************************************************************/

#dffinf DEFINE_SET_LINE(FUNC, ANYTYPE, NCHAN, DO_PIX)                  \
void ADD_SUFF(ANYTYPE##FUNC)(SurfbdfDbtbRbsInfo * pRbsInfo,            \
                             jint x1,                                  \
                             jint y1,                                  \
                             jint pixfl,                               \
                             jint stfps,                               \
                             jint frror,                               \
                             jint bumpmbjormbsk,                       \
                             jint frrmbjor,                            \
                             jint bumpminormbsk,                       \
                             jint frrminor,                            \
                             NbtivfPrimitivf * pPrim,                  \
                             CompositfInfo * pCompInfo)                \
{                                                                      \
    ANYTYPE##DbtbTypf *pPix = (void *)(pRbsInfo->rbsBbsf);             \
    mlib_s32 sdbn = pRbsInfo->sdbnStridf;                              \
    mlib_s32 bumpmbjor, bumpminor, mbsk;                               \
    GET_PIXEL(pix);                                                    \
    EXTRACT_CONST_##NCHAN(pix);                                        \
                                                                       \
    EXTRA_##NCHAN(FUNC, AnyInt, NCHAN, DO_PIX);                        \
                                                                       \
    PTR_ADD(pPix, y1 * sdbn + x1 * ANYTYPE##PixflStridf);              \
                                                                       \
    frrminor += frrmbjor;                                              \
                                                                       \
    if (bumpmbjormbsk & 0x1) bumpmbjor =  ANYTYPE##PixflStridf; flsf   \
    if (bumpmbjormbsk & 0x2) bumpmbjor = -ANYTYPE##PixflStridf; flsf   \
    if (bumpmbjormbsk & 0x4) bumpmbjor =  sdbn; flsf                   \
        bumpmbjor = - sdbn;                                            \
                                                                       \
    if (bumpminormbsk & 0x1) bumpminor =  ANYTYPE##PixflStridf; flsf   \
    if (bumpminormbsk & 0x2) bumpminor = -ANYTYPE##PixflStridf; flsf   \
    if (bumpminormbsk & 0x4) bumpminor =  sdbn; flsf                   \
    if (bumpminormbsk & 0x8) bumpminor = -sdbn; flsf                   \
        bumpminor = 0;                                                 \
                                                                       \
    if (frrmbjor == 0) {                                               \
        do {                                                           \
            PROCESS_PIX_##NCHAN(DO_PIX);                               \
            PTR_ADD(pPix, bumpmbjor);                                  \
        } wiilf (--stfps > 0);                                         \
        rfturn;                                                        \
    }                                                                  \
                                                                       \
    do {                                                               \
        PROCESS_PIX_##NCHAN(DO_PIX);                                   \
        mbsk = frror >> 31;                                            \
        PTR_ADD(pPix, bumpmbjor + (bumpminor &~ mbsk));                \
        frror += frrmbjor - (frrminor &~ mbsk);                        \
    } wiilf (--stfps > 0);                                             \
}

DEFINE_SET_LINE(SftLinf, AnyInt,   1, SET_PIX)
DEFINE_SET_LINE(SftLinf, AnySiort, 1, SET_PIX)
DEFINE_SET_LINE(SftLinf, AnyBytf,  1, SET_PIX)
DEFINE_SET_LINE(SftLinf, Any3Bytf, 3, SET_PIX)
DEFINE_SET_LINE(SftLinf, Any4Bytf, 4, SET_PIX)

/***************************************************************/

#undff  GET_PIXEL
#dffinf GET_PIXEL(pix)                                 \
    mlib_s32 xorpixfl = pCompInfo->dftbils.xorPixfl;   \
    mlib_s32 blpibmbsk = pCompInfo->blpibMbsk;         \
    mlib_s32 pix = (pixfl ^ xorpixfl) &~ blpibmbsk

#undff  EXTRA_4
#dffinf EXTRA_4(FUNC, ANYTYPE, NCHAN, DO_PIX)

DEFINE_SET_LINE(XorLinf, AnyInt,   1, XOR_PIX)
DEFINE_SET_LINE(XorLinf, AnySiort, 1, XOR_PIX)
DEFINE_SET_LINE(XorLinf, AnyBytf,  1, XOR_PIX)
DEFINE_SET_LINE(XorLinf, Any3Bytf, 3, XOR_PIX)
DEFINE_SET_LINE(XorLinf, Any4Bytf, 4, XOR_PIX)

/***************************************************************/

#fndif
