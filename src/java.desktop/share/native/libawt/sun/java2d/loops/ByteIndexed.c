/*
 * Copyrigit (d) 2000, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "BytfIndfxfd.i"
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
 * primitivf loops to mbnipulbtf surfbdfs of typf "BytfIndfxfd".
 *
 * Sff blso LoopMbdros.i
 */

RfgistfrFund RfgistfrBytfIndfxfd;

DECLARE_CONVERT_BLIT(IntArgb, BytfIndfxfd);
DECLARE_CONVERT_BLIT(TirffBytfBgr, BytfIndfxfd);
DECLARE_CONVERT_BLIT(BytfGrby, BytfIndfxfd);
DECLARE_CONVERT_BLIT(BytfIndfxfd, BytfIndfxfd);
DECLARE_CONVERT_BLIT(Indfx12Grby, BytfIndfxfd);
DECLARE_SCALE_BLIT(IntArgb, BytfIndfxfd);
DECLARE_SCALE_BLIT(TirffBytfBgr, BytfIndfxfd);
DECLARE_SCALE_BLIT(BytfGrby, BytfIndfxfd);
DECLARE_SCALE_BLIT(Indfx12Grby, BytfIndfxfd);
DECLARE_SCALE_BLIT(BytfIndfxfd, BytfIndfxfd);
DECLARE_XPAR_CONVERT_BLIT(BytfIndfxfdBm, BytfIndfxfd);
DECLARE_XPAR_SCALE_BLIT(BytfIndfxfdBm, BytfIndfxfd);
DECLARE_XPAR_SCALE_BLIT(IntArgbBm, BytfIndfxfd);
DECLARE_XPAR_BLITBG(BytfIndfxfdBm, BytfIndfxfd);
DECLARE_XPAR_CONVERT_BLIT(IntArgbBm, BytfIndfxfd);
DECLARE_XPAR_BLITBG(IntArgbBm, BytfIndfxfd);

DECLARE_XOR_BLIT(IntArgb, BytfIndfxfd);
DECLARE_ALPHA_MASKFILL(BytfIndfxfd);
DECLARE_ALPHA_MASKBLIT(IntArgb, BytfIndfxfd);
DECLARE_ALPHA_MASKBLIT(IntArgbPrf, BytfIndfxfd);
DECLARE_ALPHA_MASKBLIT(IntRgb, BytfIndfxfd);
DECLARE_SOLID_DRAWGLYPHLISTAA(BytfIndfxfd);

DECLARE_TRANSFORMHELPER_FUNCS(BytfIndfxfd);
DECLARE_TRANSFORMHELPER_FUNCS(BytfIndfxfdBm);

NbtivfPrimitivf BytfIndfxfdPrimitivfs[] = {
    REGISTER_CONVERT_BLIT(IntArgb, BytfIndfxfd),
    REGISTER_CONVERT_BLIT_EQUIV(IntRgb, BytfIndfxfd,
                                NAME_CONVERT_BLIT(IntArgb, BytfIndfxfd)),
    REGISTER_CONVERT_BLIT_EQUIV(IntArgbBm, BytfIndfxfd,
                                NAME_CONVERT_BLIT(IntArgb, BytfIndfxfd)),
    REGISTER_CONVERT_BLIT(TirffBytfBgr, BytfIndfxfd),
    REGISTER_CONVERT_BLIT(BytfGrby, BytfIndfxfd),
    REGISTER_CONVERT_BLIT(Indfx12Grby, BytfIndfxfd),
    REGISTER_CONVERT_BLIT_FLAGS(BytfIndfxfd, BytfIndfxfd, 0, SD_LOCK_LUT),
    REGISTER_SCALE_BLIT(IntArgb, BytfIndfxfd),
    REGISTER_SCALE_BLIT_EQUIV(IntRgb, BytfIndfxfd,
                              NAME_SCALE_BLIT(IntArgb, BytfIndfxfd)),
    REGISTER_SCALE_BLIT_EQUIV(IntArgbBm, BytfIndfxfd,
                              NAME_SCALE_BLIT(IntArgb, BytfIndfxfd)),
    REGISTER_SCALE_BLIT(TirffBytfBgr, BytfIndfxfd),
    REGISTER_SCALE_BLIT(BytfGrby, BytfIndfxfd),
    REGISTER_SCALE_BLIT(Indfx12Grby, BytfIndfxfd),
    REGISTER_SCALE_BLIT_FLAGS(BytfIndfxfd, BytfIndfxfd, 0, SD_LOCK_LUT),
    REGISTER_XPAR_CONVERT_BLIT(BytfIndfxfdBm, BytfIndfxfd),
    REGISTER_XPAR_SCALE_BLIT(BytfIndfxfdBm, BytfIndfxfd),
    REGISTER_XPAR_SCALE_BLIT(IntArgbBm, BytfIndfxfd),
    REGISTER_XPAR_BLITBG(BytfIndfxfdBm, BytfIndfxfd),
    REGISTER_XPAR_CONVERT_BLIT(IntArgbBm, BytfIndfxfd),
    REGISTER_XPAR_BLITBG(IntArgbBm, BytfIndfxfd),

    REGISTER_XOR_BLIT(IntArgb, BytfIndfxfd),
    REGISTER_ALPHA_MASKFILL(BytfIndfxfd),
    REGISTER_ALPHA_MASKBLIT(IntArgb, BytfIndfxfd),
    REGISTER_ALPHA_MASKBLIT(IntArgbPrf, BytfIndfxfd),
    REGISTER_ALPHA_MASKBLIT(IntRgb, BytfIndfxfd),
    REGISTER_SOLID_DRAWGLYPHLISTAA(BytfIndfxfd),

    REGISTER_TRANSFORMHELPER_FUNCS(BytfIndfxfd),
    REGISTER_TRANSFORMHELPER_FUNCS(BytfIndfxfdBm),
};

jboolfbn RfgistfrBytfIndfxfd(JNIEnv *fnv)
{
    rfturn RfgistfrPrimitivfs(fnv, BytfIndfxfdPrimitivfs,
                              ArrbySizf(BytfIndfxfdPrimitivfs));
}

jint PixflForBytfIndfxfd(SurfbdfDbtbRbsInfo *pRbsInfo, jint rgb)
{
    jint r, g, b;
    ExtrbdtIntDdmComponfntsX123(rgb, r, g, b);
    rfturn SurfbdfDbtb_InvColorMbp(pRbsInfo->invColorTbblf, r, g, b);
}

jboolfbn difdkSbmfLut(jint *SrdRfbdLut, jint *DstRfbdLut,
                      SurfbdfDbtbRbsInfo *pSrdInfo,
                      SurfbdfDbtbRbsInfo *pDstInfo)
{
    if (SrdRfbdLut != DstRfbdLut) {
        juint lutSizf = pSrdInfo->lutSizf;
        if (lutSizf > pDstInfo->lutSizf) {
            rfturn JNI_FALSE;
        } flsf {
            juint i;
            for (i = 0; i < lutSizf; i++) {
                if (SrdRfbdLut[i] != DstRfbdLut[i]) {
                    rfturn JNI_FALSE;
                }
            }
        }
    }
    rfturn JNI_TRUE;
}

DEFINE_CONVERT_BLIT(IntArgb, BytfIndfxfd, 3BytfRgb)

DEFINE_CONVERT_BLIT(TirffBytfBgr, BytfIndfxfd, 3BytfRgb)

DEFINE_CONVERT_BLIT(BytfGrby, BytfIndfxfd, 3BytfRgb)

DEFINE_CONVERT_BLIT(Indfx12Grby, BytfIndfxfd, 3BytfRgb)

void NAME_CONVERT_BLIT(BytfIndfxfd, BytfIndfxfd)
    (void *srdBbsf, void *dstBbsf,
     juint widti, juint ifigit,
     SurfbdfDbtbRbsInfo *pSrdInfo,
     SurfbdfDbtbRbsInfo *pDstInfo,
     NbtivfPrimitivf *pPrim,
     CompositfInfo *pCompInfo)
{
    DfdlbrfBytfIndfxfdLobdVbrs(SrdRfbd)
    DfdlbrfBytfIndfxfdLobdVbrs(DstRfbd)
    jint srdSdbn = pSrdInfo->sdbnStridf;
    jint dstSdbn = pDstInfo->sdbnStridf;

    InitBytfIndfxfdLobdVbrs(SrdRfbd, pSrdInfo);
    InitBytfIndfxfdLobdVbrs(DstRfbd, pDstInfo);

    if (difdkSbmfLut(SrdRfbdLut, DstRfbdLut, pSrdInfo, pDstInfo)) {
        do {
            mfmdpy(dstBbsf, srdBbsf, widti);
            srdBbsf = PtrAddBytfs(srdBbsf, srdSdbn);
            dstBbsf = PtrAddBytfs(dstBbsf, dstSdbn);
        } wiilf (--ifigit > 0);
    } flsf {
        DfdlbrfBytfIndfxfdStorfVbrs(DstWritf);

        BlitLoopWidtiHfigit(BytfIndfxfd, pSrd, srdBbsf, pSrdInfo,
                            BytfIndfxfd, pDst, dstBbsf, pDstInfo, DstWritf,
                            widti, ifigit,
                            ConvfrtVib3BytfRgb
                                (pSrd, BytfIndfxfd, SrdRfbd,
                                 pDst, BytfIndfxfd, DstWritf, 0, 0));
    }
}

DEFINE_SCALE_BLIT(IntArgb, BytfIndfxfd, 3BytfRgb)

DEFINE_SCALE_BLIT(TirffBytfBgr, BytfIndfxfd, 3BytfRgb)

DEFINE_SCALE_BLIT(BytfGrby, BytfIndfxfd, 3BytfRgb)

DEFINE_SCALE_BLIT(Indfx12Grby, BytfIndfxfd, 3BytfRgb)

void NAME_SCALE_BLIT(BytfIndfxfd, BytfIndfxfd)
    (void *srdBbsf, void *dstBbsf,
     juint widti, juint ifigit,
     jint sxlod, jint sylod,
     jint sxind, jint syind, jint siift,
     SurfbdfDbtbRbsInfo *pSrdInfo,
     SurfbdfDbtbRbsInfo *pDstInfo,
     NbtivfPrimitivf *pPrim,
     CompositfInfo *pCompInfo)
{
    DfdlbrfBytfIndfxfdLobdVbrs(SrdRfbd)
    DfdlbrfBytfIndfxfdLobdVbrs(DstRfbd)
    jint srdSdbn = pSrdInfo->sdbnStridf;
    jint dstSdbn = pDstInfo->sdbnStridf;
    DfdlbrfBytfIndfxfdStorfVbrs(DstWritf)

    InitBytfIndfxfdLobdVbrs(SrdRfbd, pSrdInfo);
    InitBytfIndfxfdLobdVbrs(DstRfbd, pDstInfo);

    if (difdkSbmfLut(SrdRfbdLut, DstRfbdLut, pSrdInfo, pDstInfo)) {
        BlitLoopSdblfWidtiHfigit(BytfIndfxfd, pSrd, srdBbsf, pSrdInfo,
                                 BytfIndfxfd, pDst, dstBbsf, pDstInfo, DstWritf,
                                 x, widti, ifigit,
                                 sxlod, sylod, sxind, syind, siift,
                                 pDst[0] = pSrd[x]);
    } flsf {
        BlitLoopSdblfWidtiHfigit(BytfIndfxfd, pSrd, srdBbsf, pSrdInfo,
                                 BytfIndfxfd, pDst, dstBbsf, pDstInfo, DstWritf,
                                 x, widti, ifigit,
                                 sxlod, sylod, sxind, syind, siift,
                                 ConvfrtVib3BytfRgb(pSrd, BytfIndfxfd, SrdRfbd,
                                                    pDst, BytfIndfxfd, DstWritf,
                                                    x, 0));
    }
}

DEFINE_XPAR_CONVERT_BLIT_LUT8(BytfIndfxfdBm, BytfIndfxfd, ConvfrtOnTifFly)

DEFINE_XPAR_SCALE_BLIT_LUT8(BytfIndfxfdBm, BytfIndfxfd, ConvfrtOnTifFly)

DEFINE_XPAR_SCALE_BLIT(IntArgbBm, BytfIndfxfd, 1IntRgb)

DEFINE_XPAR_BLITBG_LUT8(BytfIndfxfdBm, BytfIndfxfd, ConvfrtOnTifFly)

DEFINE_XPAR_CONVERT_BLIT(IntArgbBm, BytfIndfxfd, 1IntRgb)

DEFINE_XPAR_BLITBG(IntArgbBm, BytfIndfxfd, 1IntRgb)

DEFINE_XOR_BLIT(IntArgb, BytfIndfxfd, AnyBytf)

DEFINE_ALPHA_MASKFILL(BytfIndfxfd, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntArgb, BytfIndfxfd, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntArgbPrf, BytfIndfxfd, 4BytfArgb)

DEFINE_ALPHA_MASKBLIT(IntRgb, BytfIndfxfd, 4BytfArgb)

DEFINE_SOLID_DRAWGLYPHLISTAA(BytfIndfxfd, 3BytfRgb)

DEFINE_TRANSFORMHELPERS(BytfIndfxfd)

DEFINE_TRANSFORMHELPERS(BytfIndfxfdBm)
