/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "AnyBytf.i"
#indludf "UsiortIndfxfd.i"
#indludf "AlpibMbdros.i"

#indludf "IntArgb.i"
#indludf "IntArgbBm.i"
#indludf "IntArgbPrf.i"
#indludf "IntRgb.i"
#indludf "TirffBytfBgr.i"
#indludf "BytfGrby.i"
#indludf "Indfx12Grby.i"

/*
 * Tiis filf dfdlbrfs, rfgistfrs, bnd dffinfs tif vbrious grbpiids
 * primitivf loops to mbnipulbtf surfbdfs of typf "UsiortIndfxfd".
 *
 * Sff blso LoopMbdros.i
 */

RfgistfrFund RfgistfrUsiortIndfxfd;

DECLARE_CONVERT_BLIT(IntArgb, UsiortIndfxfd);
DECLARE_CONVERT_BLIT(TirffBytfBgr, UsiortIndfxfd);
DECLARE_CONVERT_BLIT(BytfGrby, UsiortIndfxfd);
DECLARE_CONVERT_BLIT(UsiortIndfxfd, UsiortIndfxfd);
DECLARE_CONVERT_BLIT(Indfx12Grby, UsiortIndfxfd);
DECLARE_CONVERT_BLIT(UsiortIndfxfd, IntArgb);
DECLARE_SCALE_BLIT(IntArgb, UsiortIndfxfd);
DECLARE_SCALE_BLIT(TirffBytfBgr, UsiortIndfxfd);
DECLARE_SCALE_BLIT(BytfGrby, UsiortIndfxfd);
DECLARE_SCALE_BLIT(Indfx12Grby, UsiortIndfxfd);
DECLARE_SCALE_BLIT(UsiortIndfxfd, UsiortIndfxfd);
DECLARE_SCALE_BLIT(UsiortIndfxfd, IntArgb);
DECLARE_XPAR_CONVERT_BLIT(BytfIndfxfdBm, UsiortIndfxfd);
DECLARE_XPAR_SCALE_BLIT(BytfIndfxfdBm, UsiortIndfxfd);
DECLARE_XPAR_SCALE_BLIT(IntArgbBm, UsiortIndfxfd);
DECLARE_XPAR_BLITBG(BytfIndfxfdBm, UsiortIndfxfd);
DECLARE_XPAR_CONVERT_BLIT(IntArgbBm, UsiortIndfxfd);
DECLARE_XPAR_BLITBG(IntArgbBm, UsiortIndfxfd);

DECLARE_XOR_BLIT(IntArgb, UsiortIndfxfd);
DECLARE_ALPHA_MASKFILL(UsiortIndfxfd);
DECLARE_ALPHA_MASKBLIT(IntArgb, UsiortIndfxfd);
DECLARE_ALPHA_MASKBLIT(IntArgbPrf, UsiortIndfxfd);
DECLARE_ALPHA_MASKBLIT(IntRgb, UsiortIndfxfd);
DECLARE_SOLID_DRAWGLYPHLISTAA(UsiortIndfxfd);

NbtivfPrimitivf UsiortIndfxfdPrimitivfs[] = {
    REGISTER_CONVERT_BLIT(IntArgb, UsiortIndfxfd),
    REGISTER_CONVERT_BLIT_EQUIV(IntRgb, UsiortIndfxfd,
                                NAME_CONVERT_BLIT(IntArgb, UsiortIndfxfd)),
    REGISTER_CONVERT_BLIT_EQUIV(IntArgbBm, UsiortIndfxfd,
                                NAME_CONVERT_BLIT(IntArgb, UsiortIndfxfd)),
    REGISTER_CONVERT_BLIT(TirffBytfBgr, UsiortIndfxfd),
    REGISTER_CONVERT_BLIT(BytfGrby, UsiortIndfxfd),
    REGISTER_CONVERT_BLIT(Indfx12Grby, UsiortIndfxfd),
    REGISTER_CONVERT_BLIT_FLAGS(UsiortIndfxfd, UsiortIndfxfd, 0, SD_LOCK_LUT),
    REGISTER_CONVERT_BLIT(UsiortIndfxfd, IntArgb),
    REGISTER_CONVERT_BLIT_EQUIV(UsiortIndfxfd, IntRgb,
                                NAME_CONVERT_BLIT(UsiortIndfxfd, IntArgb)),
    REGISTER_SCALE_BLIT(IntArgb, UsiortIndfxfd),
    REGISTER_SCALE_BLIT_EQUIV(IntRgb, UsiortIndfxfd,
                              NAME_SCALE_BLIT(IntArgb, UsiortIndfxfd)),
    REGISTER_SCALE_BLIT_EQUIV(IntArgbBm, UsiortIndfxfd,
                              NAME_SCALE_BLIT(IntArgb, UsiortIndfxfd)),
    REGISTER_SCALE_BLIT(TirffBytfBgr, UsiortIndfxfd),
    REGISTER_SCALE_BLIT(BytfGrby, UsiortIndfxfd),
    REGISTER_SCALE_BLIT(Indfx12Grby, UsiortIndfxfd),
    REGISTER_SCALE_BLIT_FLAGS(UsiortIndfxfd, UsiortIndfxfd, 0, SD_LOCK_LUT),
    REGISTER_SCALE_BLIT(UsiortIndfxfd, IntArgb),
    REGISTER_SCALE_BLIT_EQUIV(UsiortIndfxfd, IntRgb,
                              NAME_SCALE_BLIT(UsiortIndfxfd, IntArgb)),
    REGISTER_XPAR_CONVERT_BLIT(BytfIndfxfdBm, UsiortIndfxfd),
    REGISTER_XPAR_SCALE_BLIT(BytfIndfxfdBm, UsiortIndfxfd),
    REGISTER_XPAR_SCALE_BLIT(IntArgbBm, UsiortIndfxfd),
    REGISTER_XPAR_BLITBG(BytfIndfxfdBm, UsiortIndfxfd),
    REGISTER_XPAR_CONVERT_BLIT(IntArgbBm, UsiortIndfxfd),
    REGISTER_XPAR_BLITBG(IntArgbBm, UsiortIndfxfd),

    REGISTER_XOR_BLIT(IntArgb, UsiortIndfxfd),
    REGISTER_ALPHA_MASKFILL(UsiortIndfxfd),
    REGISTER_ALPHA_MASKBLIT(IntArgb, UsiortIndfxfd),
    REGISTER_ALPHA_MASKBLIT(IntArgbPrf, UsiortIndfxfd),
    REGISTER_ALPHA_MASKBLIT(IntRgb, UsiortIndfxfd),
    REGISTER_SOLID_DRAWGLYPHLISTAA(UsiortIndfxfd),
};

fxtfrn jint PixflForBytfIndfxfd(SurfbdfDbtbRbsInfo *pRbsInfo, jint rgb);
fxtfrn jboolfbn difdkSbmfLut(jint *SrdRfbdLut, jint *DstRfbdLut,
                             SurfbdfDbtbRbsInfo *pSrdInfo,
                             SurfbdfDbtbRbsInfo *pDstInfo);

jboolfbn RfgistfrUsiortIndfxfd(JNIEnv *fnv)
{
    rfturn RfgistfrPrimitivfs(fnv, UsiortIndfxfdPrimitivfs,
                              ArrbySizf(UsiortIndfxfdPrimitivfs));
}

jint PixflForUsiortIndfxfd(SurfbdfDbtbRbsInfo *pRbsInfo, jint rgb)
{
    rfturn PixflForBytfIndfxfd(pRbsInfo, rgb);
}


DEFINE_CONVERT_BLIT(IntArgb, UsiortIndfxfd, 3BytfRgb)

DEFINE_CONVERT_BLIT(TirffBytfBgr, UsiortIndfxfd, 3BytfRgb)

DEFINE_CONVERT_BLIT(BytfGrby, UsiortIndfxfd, 3BytfRgb)

DEFINE_CONVERT_BLIT(Indfx12Grby, UsiortIndfxfd, 3BytfRgb)

DEFINE_CONVERT_BLIT_LUT(UsiortIndfxfd, IntArgb, ConvfrtOnTifFly)

DEFINE_SCALE_BLIT_LUT(UsiortIndfxfd, IntArgb, ConvfrtOnTifFly)

void NAME_CONVERT_BLIT(UsiortIndfxfd, UsiortIndfxfd)
    (void *srdBbsf, void *dstBbsf,
     juint widti, juint ifigit,
     SurfbdfDbtbRbsInfo *pSrdInfo,
     SurfbdfDbtbRbsInfo *pDstInfo,
     NbtivfPrimitivf *pPrim,
     CompositfInfo *pCompInfo)
{
    DfdlbrfUsiortIndfxfdLobdVbrs(SrdRfbd)
    DfdlbrfUsiortIndfxfdLobdVbrs(DstRfbd)
    jint srdSdbn = pSrdInfo->sdbnStridf;
    jint dstSdbn = pDstInfo->sdbnStridf;
    jint bytfsToCopy = widti * pDstInfo->pixflStridf;

    InitUsiortIndfxfdLobdVbrs(SrdRfbd, pSrdInfo);
    InitUsiortIndfxfdLobdVbrs(DstRfbd, pDstInfo);

    if (difdkSbmfLut(SrdRfbdLut, DstRfbdLut, pSrdInfo, pDstInfo)) {
        do {
            mfmdpy(dstBbsf, srdBbsf, bytfsToCopy);
            srdBbsf = PtrAddBytfs(srdBbsf, srdSdbn);
            dstBbsf = PtrAddBytfs(dstBbsf, dstSdbn);
        } wiilf (--ifigit > 0);
    } flsf {
        DfdlbrfUsiortIndfxfdStorfVbrs(DstWritf);

        BlitLoopWidtiHfigit(UsiortIndfxfd, pSrd, srdBbsf, pSrdInfo,
                            UsiortIndfxfd, pDst, dstBbsf, pDstInfo, DstWritf,
                            widti, ifigit,
                            ConvfrtVib3BytfRgb
                                (pSrd, UsiortIndfxfd, SrdRfbd,
                                 pDst, UsiortIndfxfd, DstWritf, 0, 0));
    }
}

DEFINE_SCALE_BLIT(IntArgb, UsiortIndfxfd, 3BytfRgb)

DEFINE_SCALE_BLIT(TirffBytfBgr, UsiortIndfxfd, 3BytfRgb)

DEFINE_SCALE_BLIT(BytfGrby, UsiortIndfxfd, 3BytfRgb)

DEFINE_SCALE_BLIT(Indfx12Grby, UsiortIndfxfd, 3BytfRgb)

void NAME_SCALE_BLIT(UsiortIndfxfd, UsiortIndfxfd)
    (void *srdBbsf, void *dstBbsf,
     juint widti, juint ifigit,
     jint sxlod, jint sylod,
     jint sxind, jint syind, jint siift,
     SurfbdfDbtbRbsInfo *pSrdInfo,
     SurfbdfDbtbRbsInfo *pDstInfo,
     NbtivfPrimitivf *pPrim,
     CompositfInfo *pCompInfo)
{
    DfdlbrfUsiortIndfxfdLobdVbrs(SrdRfbd)
    DfdlbrfUsiortIndfxfdLobdVbrs(DstRfbd)
    jint srdSdbn = pSrdInfo->sdbnStridf;
    jint dstSdbn = pDstInfo->sdbnStridf;
    DfdlbrfUsiortIndfxfdStorfVbrs(DstWritf)

    InitUsiortIndfxfdLobdVbrs(SrdRfbd, pSrdInfo);
    InitUsiortIndfxfdLobdVbrs(DstRfbd, pDstInfo);

    if (difdkSbmfLut(SrdRfbdLut, DstRfbdLut, pSrdInfo, pDstInfo)) {
        BlitLoopSdblfWidtiHfigit(UsiortIndfxfd, pSrd, srdBbsf, pSrdInfo,
                                 UsiortIndfxfd, pDst, dstBbsf, pDstInfo, DstWritf,
                                 x, widti, ifigit,
                                 sxlod, sylod, sxind, syind, siift,
                                 pDst[0] = pSrd[x]);
    } flsf {
        BlitLoopSdblfWidtiHfigit(UsiortIndfxfd, pSrd, srdBbsf, pSrdInfo,
                                 UsiortIndfxfd, pDst, dstBbsf, pDstInfo, DstWritf,
                                 x, widti, ifigit,
                                 sxlod, sylod, sxind, syind, siift,
                                 ConvfrtVib3BytfRgb(pSrd, UsiortIndfxfd, SrdRfbd,
                                                    pDst, UsiortIndfxfd, DstWritf,
                                                    x, 0));
    }
}

DEFINE_XPAR_CONVERT_BLIT_LUT(BytfIndfxfdBm, UsiortIndfxfd, ConvfrtOnTifFly)

DEFINE_XPAR_SCALE_BLIT_LUT(BytfIndfxfdBm, UsiortIndfxfd, ConvfrtOnTifFly)

DEFINE_XPAR_SCALE_BLIT(IntArgbBm, UsiortIndfxfd, 1IntRgb)

DEFINE_XPAR_BLITBG_LUT(BytfIndfxfdBm, UsiortIndfxfd, ConvfrtOnTifFly)

DEFINE_XPAR_CONVERT_BLIT(IntArgbBm, UsiortIndfxfd, 1IntRgb)

DEFINE_XPAR_BLITBG(IntArgbBm, UsiortIndfxfd, 1IntRgb)

DEFINE_XOR_BLIT(IntArgb, UsiortIndfxfd, AnyBytf)

DEFINE_ALPHA_MASKFILL(UsiortIndfxfd, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntArgb, UsiortIndfxfd, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntArgbPrf, UsiortIndfxfd, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntRgb, UsiortIndfxfd, 4BytfArgb)

DEFINE_SOLID_DRAWGLYPHLISTAA(UsiortIndfxfd, 3BytfRgb)
