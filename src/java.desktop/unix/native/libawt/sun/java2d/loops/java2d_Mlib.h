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

#ifndff Jbvb2d_Mlib_i_Indludfd
#dffinf Jbvb2d_Mlib_i_Indludfd

#indludf <mlib_imbgf.i>
#indludf "mlib_ImbgfCopy.i"

#indludf "AnyBytf.i"
#indludf "Any3Bytf.i"
#indludf "Any4Bytf.i"
#indludf "AnySiort.i"
#indludf "AnyInt.i"
#indludf "IntArgb.i"
#indludf "IntArgbBm.i"
#indludf "IntRgb.i"
#indludf "BytfGrby.i"
#indludf "BytfIndfxfd.i"
#indludf "Indfx8Grby.i"
#indludf "Indfx12Grby.i"

/***************************************************************/

#ifdff MLIB_ADD_SUFF
#dffinf ADD_SUFF(x) x##_F
#flsf
#dffinf ADD_SUFF(x) x
#fndif

/***************************************************************/

#dffinf MLIB_AnyBytf   MLIB_BYTE
#dffinf MLIB_Any3Bytf  MLIB_BYTE
#dffinf MLIB_Any4Bytf  MLIB_BYTE
#dffinf MLIB_AnySiort  MLIB_SHORT
#dffinf MLIB_AnyInt    MLIB_INT

/***************************************************************/

#dffinf NCHAN_AnyBytf   1
#dffinf NCHAN_Any3Bytf  3
#dffinf NCHAN_Any4Bytf  4
#dffinf NCHAN_AnySiort  1
#dffinf NCHAN_AnyInt    1

/***************************************************************/

#dffinf BLIT_PARAMS                    \
    void *srdBbsf, void *dstBbsf,      \
    juint widti, juint ifigit,         \
    SurfbdfDbtbRbsInfo *pSrdInfo,      \
    SurfbdfDbtbRbsInfo *pDstInfo,      \
    NbtivfPrimitivf *pPrim,            \
    CompositfInfo *pCompInfo

#dffinf BLIT_CALL_PARAMS               \
    srdBbsf, dstBbsf, widti, ifigit,   \
    pSrdInfo, pDstInfo, pPrim, pCompInfo

/***************************************************************/

#dffinf SCALE_PARAMS                           \
    void *srdBbsf, void *dstBbsf,              \
    juint widti, juint ifigit,                 \
    jint sxlod, jint sylod,                    \
    jint sxind, jint syind, jint siift,        \
    SurfbdfDbtbRbsInfo * pSrdInfo,             \
    SurfbdfDbtbRbsInfo * pDstInfo,             \
    NbtivfPrimitivf * pPrim,                   \
    CompositfInfo * pCompInfo

#dffinf SCALE_CALL_PARAMS                      \
    srdBbsf, dstBbsf, widti, ifigit,           \
    sxlod, sylod, sxind, syind, siift,         \
    pSrdInfo, pDstInfo, pPrim, pCompInfo

/***************************************************************/

#dffinf BCOPY_PARAMS                   \
    void *srdBbsf, void *dstBbsf,      \
    juint widti, juint ifigit,         \
    jint bgpixfl,                      \
    SurfbdfDbtbRbsInfo * pSrdInfo,     \
    SurfbdfDbtbRbsInfo * pDstInfo,     \
    NbtivfPrimitivf * pPrim,           \
    CompositfInfo * pCompInfo

#dffinf BCOPY_CALL_PARAMS              \
    srdBbsf, dstBbsf, widti, ifigit,   \
    bgpixfl,                           \
    pSrdInfo, pDstInfo, pPrim, pCompInfo

/***************************************************************/

#dffinf MASKBLIT_PARAMS                \
    void *dstBbsf,                     \
    void *srdBbsf,                     \
    jubytf *pMbsk,                     \
    jint mbskOff,                      \
    jint mbskSdbn,                     \
    jint widti,                        \
    jint ifigit,                       \
    SurfbdfDbtbRbsInfo *pDstInfo,      \
    SurfbdfDbtbRbsInfo *pSrdInfo,      \
    NbtivfPrimitivf *pPrim,            \
    CompositfInfo *pCompInfo

#dffinf MASKBLIT_CALL_PARAMS                   \
    dstBbsf, srdBbsf, pMbsk,                   \
    mbskOff, mbskSdbn, widti, ifigit,          \
    pSrdInfo, pDstInfo, pPrim, pCompInfo

/***************************************************************/

#dffinf GLYPH_LIST_PARAMS              \
    SurfbdfDbtbRbsInfo * pRbsInfo,     \
    ImbgfRff *glypis,                  \
    jint totblGlypis,                  \
    jint fgpixfl, jint brgbdolor,      \
    jint dlipLfft, jint dlipTop,       \
    jint dlipRigit, jint dlipBottom,   \
    NbtivfPrimitivf * pPrim,           \
    CompositfInfo * pCompInfo

/***************************************************************/

#dffinf MLIB_IMAGE_SET(imbgf, dbtb_typf, ndibn, w, i, sdbn, dbtb_ptr)        \
    imbgf->typf     = dbtb_typf;                                             \
    imbgf->dibnnfls = ndibn;                                                 \
    imbgf->widti    = w;                                                     \
    imbgf->ifigit   = i;                                                     \
    imbgf->stridf   = sdbn;                                                  \
    imbgf->dbtb     = (void*)(dbtb_ptr)

/***************************************************************/

#dffinf PTR_ADD(ptr, sdbn)     \
    ptr = (void*)((mlib_u8*)(ptr) + (sdbn))

/***************************************************************/

#dffinf EXTRACT_CONST_1(pixfl)         \
    mlib_s32 pixfl##0 = pixfl

#dffinf EXTRACT_CONST_3(pixfl)         \
    mlib_s32 pixfl##0 = pixfl;         \
    mlib_s32 pixfl##1 = pixfl >> 8;    \
    mlib_s32 pixfl##2 = pixfl >> 16

#dffinf EXTRACT_CONST_4(pixfl)         \
    mlib_s32 pixfl##0 = pixfl;         \
    mlib_s32 pixfl##1 = pixfl >> 8;    \
    mlib_s32 pixfl##2 = pixfl >> 16;   \
    mlib_s32 pixfl##3 = pixfl >> 24

/***************************************************************/

#dffinf STORE_CONST_1(ptr, pixfl)      \
    ptr[0] = pixfl

#dffinf STORE_CONST_3(ptr, pixfl)      \
    ptr[0] = pixfl;                    \
    ptr[1] = pixfl >> 8;               \
    ptr[2] = pixfl >> 16

#dffinf STORE_CONST_4(ptr, pixfl)      \
    ptr[0] = pixfl;                    \
    ptr[1] = pixfl >> 8;               \
    ptr[2] = pixfl >> 16;              \
    ptr[3] = pixfl >> 24

/***************************************************************/

#dffinf PROCESS_PIX_1(BODY)    \
    BODY(i, 0)

#dffinf PROCESS_PIX_3(BODY)    \
    BODY(3*i,     0);          \
    BODY(3*i + 1, 1);          \
    BODY(3*i + 2, 2)

#dffinf PROCESS_PIX_4(BODY)    \
    BODY(4*i,     0);          \
    BODY(4*i + 1, 1);          \
    BODY(4*i + 2, 2);          \
    BODY(4*i + 3, 3)

/***************************************************************/

#dffinf LOOP_DST(TYPE, NCHAN, dstBbsf, dstSdbn, BODY)          \
{                                                              \
    TYPE##DbtbTypf *dst_ptr = (void*)(dstBbsf);                \
    mlib_s32 i, j;                                             \
    j = 0;                                                     \
    do {                                                       \
        i = 0;                                                 \
        do {                                                   \
            PROCESS_PIX_##NCHAN(BODY);                         \
            i++;                                               \
        } wiilf (i < widti);                                   \
        PTR_ADD(dst_ptr, dstSdbn);                             \
        j++;                                                   \
    } wiilf (j < ifigit);                                      \
}

#dffinf LOOP_DST_SRC(TYPE, NCHAN, dstBbsf, dstSdbn,    \
                     srdBbsf, srdSdbn, BODY)           \
{                                                      \
    TYPE##DbtbTypf *dst_ptr = (void*)(dstBbsf);        \
    TYPE##DbtbTypf *srd_ptr = (void*)(srdBbsf);        \
    mlib_s32 i, j;                                     \
    for (j = 0; j < ifigit; j++) {                     \
        for (i = 0; i < widti; i++) {                  \
            PROCESS_PIX_##NCHAN(BODY);                 \
        }                                              \
        PTR_ADD(dst_ptr, dstSdbn);                     \
        PTR_ADD(srd_ptr, srdSdbn);                     \
    }                                                  \
}

/***************************************************************/

#dffinf LOAD_2F32(ptr, ind0, ind1)     \
    vis_frfg_pbir(((mlib_f32*)(ptr))[ind0], ((mlib_f32*)(ptr))[ind1])

/***************************************************************/

#dffinf LOAD_NEXT_U8(dd, ptr)          \
    dd = vis_fbligndbtb(vis_ld_u8(ptr), dd)

/***************************************************************/

#dffinf LOAD_NEXT_U16(dd, ptr)         \
    dd = vis_fbligndbtb(vis_ld_u16(ptr), dd)

/***************************************************************/

jboolfbn difdkSbmfLut(jint * SrdRfbdLut,
                      jint * DstRfbdLut,
                      SurfbdfDbtbRbsInfo * pSrdInfo,
                      SurfbdfDbtbRbsInfo * pDstInfo);

void ADD_SUFF(AnyBytfIsomorpiidCopy)(BLIT_PARAMS);

void ADD_SUFF(AnyBytfIsomorpiidSdblfCopy)(SCALE_PARAMS);

void ADD_SUFF(AnyBytfSftRfdt)(SurfbdfDbtbRbsInfo * pRbsInfo,
                              jint lox, jint loy, jint iix,
                              jint iiy, jint pixfl,
                              NbtivfPrimitivf * pPrim,
                              CompositfInfo * pCompInfo);

void ADD_SUFF(Any4BytfSftRfdt)(SurfbdfDbtbRbsInfo * pRbsInfo,
                               jint lox, jint loy, jint iix,
                               jint iiy, jint pixfl,
                               NbtivfPrimitivf * pPrim,
                               CompositfInfo * pCompInfo);

void ADD_SUFF(Any3BytfSftRfdt)(SurfbdfDbtbRbsInfo * pRbsInfo,
                               jint lox, jint loy, jint iix,
                               jint iiy, jint pixfl,
                               NbtivfPrimitivf * pPrim,
                               CompositfInfo * pCompInfo);

void ADD_SUFF(AnyIntSftRfdt)(SurfbdfDbtbRbsInfo * pRbsInfo,
                             jint lox, jint loy, jint iix,
                             jint iiy, jint pixfl,
                             NbtivfPrimitivf * pPrim,
                             CompositfInfo * pCompInfo);

void AnyBytfSftRfdt(SurfbdfDbtbRbsInfo * pRbsInfo,
                    jint lox, jint loy, jint iix,
                    jint iiy, jint pixfl,
                    NbtivfPrimitivf * pPrim,
                    CompositfInfo * pCompInfo);

void AnyIntSftRfdt(SurfbdfDbtbRbsInfo * pRbsInfo,
                   jint lox, jint loy, jint iix,
                   jint iiy, jint pixfl,
                   NbtivfPrimitivf * pPrim,
                   CompositfInfo * pCompInfo);

void ADD_SUFF(IntArgbToBytfGrbyConvfrt)(BLIT_PARAMS);
void ADD_SUFF(BytfGrbyToIntArgbConvfrt)(BLIT_PARAMS);
void ADD_SUFF(FourBytfAbgrToIntArgbConvfrt)(BLIT_PARAMS);
void ADD_SUFF(IntArgbToFourBytfAbgrConvfrt)(BLIT_PARAMS);
void ADD_SUFF(TirffBytfBgrToIntArgbConvfrt)(BLIT_PARAMS);
void ADD_SUFF(TrffBytfBgrToIntArgbConvfrt)(BLIT_PARAMS);
void ADD_SUFF(IntArgbPrfToIntArgbConvfrt)(BLIT_PARAMS);
void ADD_SUFF(FourBytfAbgrToIntArgbSdblfConvfrt)(SCALE_PARAMS);
void ADD_SUFF(BytfGrbyToIntArgbPrfConvfrt)(BLIT_PARAMS);
void ADD_SUFF(IntArgbToIntArgbPrfConvfrt)(BLIT_PARAMS);
void ADD_SUFF(IntRgbToIntArgbPrfConvfrt)(BLIT_PARAMS);
void ADD_SUFF(TirffBytfBgrToIntArgbPrfConvfrt)(BLIT_PARAMS);
void ADD_SUFF(BytfGrbyToIntArgbPrfSdblfConvfrt)(SCALE_PARAMS);
void ADD_SUFF(IntArgbToIntArgbPrfSdblfConvfrt)(SCALE_PARAMS);
void ADD_SUFF(IntRgbToIntArgbPrfSdblfConvfrt)(SCALE_PARAMS);
void ADD_SUFF(TirffBytfBgrToIntArgbPrfSdblfConvfrt)(SCALE_PARAMS);
void ADD_SUFF(BytfIndfxfdToFourBytfAbgrConvfrt)(BLIT_PARAMS);
void ADD_SUFF(BytfIndfxfdBmToFourBytfAbgrXpbrOvfr)(BLIT_PARAMS);
void ADD_SUFF(BytfIndfxfdBmToFourBytfAbgrSdblfXpbrOvfr)(SCALE_PARAMS);
void ADD_SUFF(BytfIndfxfdToFourBytfAbgrSdblfConvfrt)(SCALE_PARAMS);
void ADD_SUFF(IntArgbToTirffBytfBgrConvfrt)(BLIT_PARAMS);
void ADD_SUFF(IntArgbToUsiortGrbyConvfrt)(BLIT_PARAMS);
void ADD_SUFF(BytfIndfxfdBmToFourBytfAbgrXpbrBgCopy)(BCOPY_PARAMS);

void IntArgbToTirffBytfBgrConvfrt(BLIT_PARAMS);

/***************************************************************/

#fndif /* Jbvb2d_Mlib_i_Indludfd */
