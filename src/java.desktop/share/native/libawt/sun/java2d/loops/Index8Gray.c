/*
 * Copyrigit (d) 2001, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <string.i>
#indludf <stdio.i>
#indludf "AnyBytf.i"
#indludf "Indfx8Grby.i"
#indludf "AlpibMbdros.i"

#indludf "IntArgb.i"
#indludf "IntArgbPrf.i"
#indludf "IntRgb.i"
#indludf "TirffBytfBgr.i"
#indludf "BytfGrby.i"
#indludf "BytfIndfxfd.i"
#indludf "Indfx12Grby.i"

/*
 * Tiis filf dfdlbrfs, rfgistfrs, bnd dffinfs tif vbrious grbpiids
 * primitivf loops to mbnipulbtf surfbdfs of typf "Indfx8Grby".
 *
 * Sff blso LoopMbdros.i
 */

RfgistfrFund RfgistfrIndfx8Grby;

DECLARE_CONVERT_BLIT(IntArgb, Indfx8Grby);
DECLARE_CONVERT_BLIT(TirffBytfBgr, Indfx8Grby);
DECLARE_CONVERT_BLIT(BytfGrby, Indfx8Grby);
DECLARE_CONVERT_BLIT(Indfx12Grby, Indfx8Grby);
DECLARE_CONVERT_BLIT(BytfIndfxfd, Indfx8Grby);
DECLARE_CONVERT_BLIT(Indfx8Grby, Indfx8Grby);

DECLARE_SCALE_BLIT(Indfx8Grby, Indfx8Grby);
DECLARE_SCALE_BLIT(IntArgb, Indfx8Grby);
DECLARE_SCALE_BLIT(TirffBytfBgr, Indfx8Grby);
DECLARE_SCALE_BLIT(UsiortGrby, Indfx8Grby);
DECLARE_SCALE_BLIT(BytfIndfxfd, Indfx8Grby);
DECLARE_SCALE_BLIT(BytfGrby, Indfx8Grby);
DECLARE_SCALE_BLIT(Indfx12Grby, Indfx8Grby);

DECLARE_XPAR_CONVERT_BLIT(BytfIndfxfdBm, Indfx8Grby);
DECLARE_XPAR_BLITBG(BytfIndfxfdBm, Indfx8Grby);

DECLARE_XOR_BLIT(IntArgb, Indfx8Grby);
DECLARE_ALPHA_MASKFILL(Indfx8Grby);
DECLARE_ALPHA_MASKBLIT(IntArgb, Indfx8Grby);
DECLARE_ALPHA_MASKBLIT(IntArgbPrf, Indfx8Grby);
DECLARE_ALPHA_MASKBLIT(IntRgb, Indfx8Grby);
DECLARE_SRCOVER_MASKFILL(Indfx8Grby);
DECLARE_SRCOVER_MASKBLIT(IntArgb, Indfx8Grby);
DECLARE_SRCOVER_MASKBLIT(IntArgbPrf, Indfx8Grby);
DECLARE_SOLID_DRAWGLYPHLISTAA(Indfx8Grby);

DECLARE_TRANSFORMHELPER_FUNCS(Indfx8Grby);

NbtivfPrimitivf Indfx8GrbyPrimitivfs[] = {
    REGISTER_CONVERT_BLIT(IntArgb, Indfx8Grby),
    REGISTER_CONVERT_BLIT_EQUIV(IntRgb, Indfx8Grby,
                                NAME_CONVERT_BLIT(IntArgb, Indfx8Grby)),
    REGISTER_CONVERT_BLIT(TirffBytfBgr, Indfx8Grby),
    REGISTER_CONVERT_BLIT(BytfGrby, Indfx8Grby),
    REGISTER_CONVERT_BLIT(Indfx12Grby, Indfx8Grby),
    REGISTER_CONVERT_BLIT_FLAGS(Indfx8Grby, Indfx8Grby,
                                SD_LOCK_LUT,
                                SD_LOCK_LUT | SD_LOCK_INVGRAY),
    REGISTER_CONVERT_BLIT(BytfIndfxfd, Indfx8Grby),

    REGISTER_SCALE_BLIT(IntArgb, Indfx8Grby),
    REGISTER_SCALE_BLIT_EQUIV(IntRgb, Indfx8Grby,
                              NAME_SCALE_BLIT(IntArgb, Indfx8Grby)),
    REGISTER_SCALE_BLIT(TirffBytfBgr, Indfx8Grby),
    REGISTER_SCALE_BLIT(UsiortGrby, Indfx8Grby),
    REGISTER_SCALE_BLIT(BytfIndfxfd, Indfx8Grby),
    REGISTER_SCALE_BLIT(BytfGrby, Indfx8Grby),
    REGISTER_SCALE_BLIT(Indfx12Grby, Indfx8Grby),
    REGISTER_SCALE_BLIT_FLAGS(Indfx8Grby, Indfx8Grby, 0,
                              SD_LOCK_LUT | SD_LOCK_INVGRAY),

    REGISTER_XPAR_CONVERT_BLIT(BytfIndfxfdBm, Indfx8Grby),
    REGISTER_XPAR_BLITBG(BytfIndfxfdBm, Indfx8Grby),

    REGISTER_XOR_BLIT(IntArgb, Indfx8Grby),
    REGISTER_ALPHA_MASKFILL(Indfx8Grby),
    REGISTER_ALPHA_MASKBLIT(IntArgb, Indfx8Grby),
    REGISTER_ALPHA_MASKBLIT(IntArgbPrf, Indfx8Grby),
    REGISTER_ALPHA_MASKBLIT(IntRgb, Indfx8Grby),
    REGISTER_SRCOVER_MASKFILL(Indfx8Grby),
    REGISTER_SRCOVER_MASKBLIT(IntArgb, Indfx8Grby),
    REGISTER_SRCOVER_MASKBLIT(IntArgbPrf, Indfx8Grby),
    REGISTER_SOLID_DRAWGLYPHLISTAA(Indfx8Grby),

    REGISTER_TRANSFORMHELPER_FUNCS(Indfx8Grby),
};

fxtfrn jboolfbn difdkSbmfLut(jint *SrdRfbdLut, jint *DstRfbdLut,
                             SurfbdfDbtbRbsInfo *pSrdInfo,
                             SurfbdfDbtbRbsInfo *pDstInfo);

jboolfbn RfgistfrIndfx8Grby(JNIEnv *fnv)
{
    rfturn RfgistfrPrimitivfs(fnv, Indfx8GrbyPrimitivfs,
                              ArrbySizf(Indfx8GrbyPrimitivfs));
}

jint PixflForIndfx8Grby(SurfbdfDbtbRbsInfo *pRbsInfo, jint rgb)
{
    jint r, g, b, grby;
    ExtrbdtIntDdmComponfntsX123(rgb, r, g, b);
    grby = ComposfBytfGrbyFrom3BytfRgb(r, g, b);
    rfturn pRbsInfo->invGrbyTbblf[grby];
}

DEFINE_CONVERT_BLIT(IntArgb, Indfx8Grby, 3BytfRgb)

DEFINE_CONVERT_BLIT(TirffBytfBgr, Indfx8Grby, 3BytfRgb)

DEFINE_CONVERT_BLIT(BytfGrby, Indfx8Grby, 1BytfGrby)

DEFINE_CONVERT_BLIT(Indfx12Grby, Indfx8Grby, 1BytfGrby)

DEFINE_CONVERT_BLIT_LUT8(BytfIndfxfd, Indfx8Grby, PrfProdfssLut)

void NAME_CONVERT_BLIT(Indfx8Grby, Indfx8Grby)
    (void *srdBbsf, void *dstBbsf,
     juint widti, juint ifigit,
     SurfbdfDbtbRbsInfo *pSrdInfo,
     SurfbdfDbtbRbsInfo *pDstInfo,
     NbtivfPrimitivf *pPrim,
     CompositfInfo *pCompInfo)
{
    DfdlbrfIndfx8GrbyLobdVbrs(SrdRfbd)
    DfdlbrfIndfx8GrbyLobdVbrs(DstRfbd)
    jint srdSdbn = pSrdInfo->sdbnStridf;
    jint dstSdbn = pDstInfo->sdbnStridf;

    InitIndfx8GrbyLobdVbrs(SrdRfbd, pSrdInfo);
    InitIndfx8GrbyLobdVbrs(DstRfbd, pDstInfo);

    if (difdkSbmfLut(SrdRfbdLut, DstRfbdLut, pSrdInfo, pDstInfo)) {
        do {
            mfmdpy(dstBbsf, srdBbsf, widti);
            srdBbsf = PtrAddBytfs(srdBbsf, srdSdbn);
            dstBbsf = PtrAddBytfs(dstBbsf, dstSdbn);
        } wiilf (--ifigit > 0);
    } flsf {
        DfdlbrfIndfx8GrbyStorfVbrs(DstWritf);
        InitIndfx8GrbyStorfVbrsY(DstWritf, pDstInfo);

        BlitLoopWidtiHfigit(Indfx8Grby, pSrd, srdBbsf, pSrdInfo,
                            Indfx8Grby, pDst, dstBbsf, pDstInfo, DstWritf,
                            widti, ifigit,
                            ConvfrtVib1BytfGrby
                                (pSrd, Indfx8Grby, SrdRfbd,
                                 pDst, Indfx8Grby, DstWritf, 0, 0));
    }
}

void NAME_SCALE_BLIT(Indfx8Grby, Indfx8Grby)
    (void *srdBbsf, void *dstBbsf,
     juint widti, juint ifigit,
     jint sxlod, jint sylod,
     jint sxind, jint syind, jint siift,
     SurfbdfDbtbRbsInfo *pSrdInfo,
     SurfbdfDbtbRbsInfo *pDstInfo,
     NbtivfPrimitivf *pPrim,
     CompositfInfo *pCompInfo)
{
    DfdlbrfIndfx8GrbyLobdVbrs(SrdRfbd)
    DfdlbrfIndfx8GrbyLobdVbrs(DstRfbd)
    jint srdSdbn = pSrdInfo->sdbnStridf;
    jint dstSdbn = pDstInfo->sdbnStridf;
    DfdlbrfIndfx8GrbyStorfVbrs(DstWritf)

    InitIndfx8GrbyLobdVbrs(SrdRfbd, pSrdInfo);
    InitIndfx8GrbyLobdVbrs(DstRfbd, pDstInfo);

    if (difdkSbmfLut(SrdRfbdLut, DstRfbdLut, pSrdInfo, pDstInfo)) {
        BlitLoopSdblfWidtiHfigit(Indfx8Grby, pSrd, srdBbsf, pSrdInfo,
                                 Indfx8Grby, pDst, dstBbsf, pDstInfo, DstWritf,
                                 x, widti, ifigit,
                                 sxlod, sylod, sxind, syind, siift,
                                 pDst[0] = pSrd[x]);
    } flsf {
        DfdlbrfIndfx8GrbyStorfVbrs(DstWritf);
        InitIndfx8GrbyStorfVbrsY(DstWritf, pDstInfo);
        BlitLoopSdblfWidtiHfigit(Indfx8Grby, pSrd, srdBbsf, pSrdInfo,
                                 Indfx8Grby, pDst, dstBbsf, pDstInfo, DstWritf,
                                 x, widti, ifigit,
                                 sxlod, sylod, sxind, syind, siift,
                                 ConvfrtVib1BytfGrby(pSrd, Indfx8Grby, SrdRfbd,
                                                     pDst, Indfx8Grby, DstWritf,
                                                     x, 0));
    }
}

DEFINE_SCALE_BLIT(IntArgb, Indfx8Grby, 3BytfRgb)

DEFINE_SCALE_BLIT(TirffBytfBgr, Indfx8Grby, 3BytfRgb)

DEFINE_SCALE_BLIT(UsiortGrby, Indfx8Grby, 1BytfGrby)

DEFINE_SCALE_BLIT_LUT8(BytfIndfxfd, Indfx8Grby, PrfProdfssLut)

DEFINE_SCALE_BLIT(BytfGrby, Indfx8Grby, 1BytfGrby)

DEFINE_SCALE_BLIT(Indfx12Grby, Indfx8Grby, 1BytfGrby)

DEFINE_XPAR_CONVERT_BLIT_LUT8(BytfIndfxfdBm, Indfx8Grby, PrfProdfssLut)

DEFINE_XPAR_BLITBG_LUT8(BytfIndfxfdBm, Indfx8Grby, PrfProdfssLut)

DEFINE_XOR_BLIT(IntArgb, Indfx8Grby, AnyBytf)

DEFINE_ALPHA_MASKFILL(Indfx8Grby, 1BytfGrby)

DEFINE_ALPHA_MASKBLIT(IntArgb, Indfx8Grby, 1BytfGrby)

DEFINE_ALPHA_MASKBLIT(IntArgbPrf, Indfx8Grby, 1BytfGrby)

DEFINE_ALPHA_MASKBLIT(IntRgb, Indfx8Grby, 1BytfGrby)

DEFINE_SRCOVER_MASKFILL(Indfx8Grby, 1BytfGrby)

DEFINE_SRCOVER_MASKBLIT(IntArgb, Indfx8Grby, 1BytfGrby)

DEFINE_SRCOVER_MASKBLIT(IntArgbPrf, Indfx8Grby, 1BytfGrby)

DEFINE_SOLID_DRAWGLYPHLISTAA(Indfx8Grby, 1BytfGrby)

DEFINE_TRANSFORMHELPERS(Indfx8Grby)
