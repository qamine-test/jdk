/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "D3DGlypiCbdif.i"
#indludf "D3DTfxtRfndfrfr.i"
#indludf "D3DRfndfrQufuf.i"

void D3DGlypiCbdif_FlusiGlypiVfrtfxCbdif();

// stbtid
HRESULT
D3DGlypiCbdif::CrfbtfInstbndf(D3DContfxt *pCtx, GlypiCbdifTypf gdTypf,
                              D3DGlypiCbdif **ppGlypiCbdif)
{
    HRESULT rfs;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGlypiCbdif::CrfbtfInstbndf");

    *ppGlypiCbdif = nfw D3DGlypiCbdif(gdTypf);
    if (FAILED(rfs = (*ppGlypiCbdif)->Init(pCtx))) {
        dflftf *ppGlypiCbdif;
        *ppGlypiCbdif = NULL;
    }
    rfturn rfs;
}

D3DGlypiCbdif::D3DGlypiCbdif(GlypiCbdifTypf typf)
{
    J2dTrbdfLn1(J2D_TRACE_INFO, "D3DGlypiCbdif::D3DGlypiCbdif gdTypf=%d", typf);

    pCtx = NULL;
    gdTypf = typf;
    pGlypiCbdifRfs = NULL;
    pGlypiCbdif = NULL;
    tilfFormbt = (gdTypf == CACHE_GRAY) ? TILEFMT_1BYTE_ALPHA : TILEFMT_UNKNOWN;
    lbstRGBOrdfr = JNI_FALSE;
}

D3DGlypiCbdif::~D3DGlypiCbdif()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGlypiCbdif::~D3DGlypiCbdif");

    RflfbsfDffPoolRfsourdfs();

    pCtx = NULL;
    if (pGlypiCbdif != NULL) {
        AddflGlypiCbdif_Frff(pGlypiCbdif);
        pGlypiCbdif = NULL;
    }
}

void
D3DGlypiCbdif::RflfbsfDffPoolRfsourdfs()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGlypiCbdif::RflfbsfDffPoolRfsourdfs");

    AddflGlypiCbdif_Invblidbtf(pGlypiCbdif);
    // REMIND: tif glypi dbdif tfxturf is not in tif dffbult pool, so
    // tiis dbn bf optimizfd not to rflfbsf tif tfxturf
    pCtx->GftRfsourdfMbnbgfr()->RflfbsfRfsourdf(pGlypiCbdifRfs);
    pGlypiCbdifRfs = NULL;
}

HRESULT
D3DGlypiCbdif::Init(D3DContfxt *pCtx)
{
    D3DFORMAT formbt;

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);

    J2dTrbdfLn1(J2D_TRACE_INFO, "D3DGlypiCbdif::Init pCtx=%x", pCtx);

    tiis->pCtx = pCtx;

    if (pGlypiCbdif == NULL) {
        // init glypi dbdif dbtb strudturf
        pGlypiCbdif = AddflGlypiCbdif_Init(D3DTR_CACHE_WIDTH,
                                           D3DTR_CACHE_HEIGHT,
                                           D3DTR_CACHE_CELL_WIDTH,
                                           D3DTR_CACHE_CELL_HEIGHT,
                                           D3DGlypiCbdif_FlusiGlypiVfrtfxCbdif);
        if (pGlypiCbdif == NULL) {
            J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                          "D3DGlypiCbdif::Init: "\
                          "dould not init D3D glypi dbdif");
            rfturn E_FAIL;
        }
    }

    if (gdTypf == CACHE_GRAY) {
        formbt = pCtx->IsTfxturfFormbtSupportfd(D3DFMT_A8) ?
            D3DFMT_A8 : D3DFMT_A8R8G8B8;
    } flsf { // gdTypf == CACHE_LCD
        formbt = pCtx->IsTfxturfFormbtSupportfd(D3DFMT_R8G8B8) ?
            D3DFMT_R8G8B8 : D3DFMT_A8R8G8B8;
    }

    HRESULT rfs = pCtx->GftRfsourdfMbnbgfr()->
        CrfbtfTfxturf(D3DTR_CACHE_WIDTH, D3DTR_CACHE_HEIGHT,
                      FALSE/*isRTT*/, FALSE/*isOpbquf*/, &formbt, 0/*usbgf*/,
                      &pGlypiCbdifRfs);
    if (FAILED(rfs)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                      "D3DGlypiCbdif::Init: "\
                      "dould not drfbtf glypi dbdif tfxturf");
    }

    rfturn rfs;
}

HRESULT
D3DGlypiCbdif::AddGlypi(GlypiInfo *glypi)
{
    HRESULT rfs = S_OK;

    RETURN_STATUS_IF_NULL(pGlypiCbdifRfs, E_FAIL);

    CbdifCfllInfo *dfllInfo = AddflGlypiCbdif_AddGlypi(pGlypiCbdif, glypi);
    if (dfllInfo != NULL) {
        jint pixflsToudifdL = 0, pixflsToudifdR = 0;
        // storf glypi imbgf in tfxturf dfll
        rfs = pCtx->UplobdTilfToTfxturf(pGlypiCbdifRfs,
                                        glypi->imbgf,
                                        dfllInfo->x, dfllInfo->y,
                                        0, 0,
                                        glypi->widti, glypi->ifigit,
                                        glypi->rowBytfs, tilfFormbt,
                                        &pixflsToudifdL,
                                        &pixflsToudifdR);
        // LCD tfxt rfndfring optimizbtion: if tif numbfr of pixfls toudifd on
        // tif first or lbst dolumn of tif glypi imbgf is lfss tibn 1/3 of tif
        // ifigit of tif glypi wf do not donsidfr tifm toudifd.
        // Sff D3DTfxtRfndfrfr.dpp:UpdbtfCbdifdDfstinbtion for morf informbtion.
        // Tif lfftOff/rigitOff brf only usfd in LCD dbdif dbsf.
        if (gdTypf == CACHE_LCD) {
            jint tirfsiold = glypi->ifigit/3;

            dfllInfo->lfftOff  = pixflsToudifdL < tirfsiold ?  1 : 0;
            dfllInfo->rigitOff = pixflsToudifdR < tirfsiold ? -1 : 0;
        } flsf {
            dfllInfo->lfftOff  = 0;
            dfllInfo->rigitOff = 0;
        }
    }

    rfturn rfs;
}

HRESULT
D3DGlypiCbdif::CifdkGlypiCbdifBytfOrdfr(jboolfbn rgbOrdfr)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGlypiCbdif::CifdkGlypiCbdifBytfOrdfr");

    if (gdTypf != CACHE_LCD) {
        J2dTrbdfLn(J2D_TRACE_ERROR, "D3DGlypiCbdif::CifdkGlypiCbdifBytfOrdfr"\
                   " invokfd on CACHE_GRAY dbdif typf instbndf!");
        rfturn E_FAIL;
    }

    if (rgbOrdfr != lbstRGBOrdfr) {
        // nffd to invblidbtf tif dbdif in tiis dbsf; sff dommfnts
        // for lbstRGBOrdfr
        AddflGlypiCbdif_Invblidbtf(pGlypiCbdif);
        lbstRGBOrdfr = rgbOrdfr;
    }
    tilfFormbt = rgbOrdfr ? TILEFMT_3BYTE_RGB : TILEFMT_3BYTE_BGR;

    rfturn S_OK;
}

/**
 * Tiis mftiod is invokfd in tif (rflbtivfly rbrf) dbsf wifrf onf or
 * morf glypis is bbout to bf kidkfd out of tif glypi dbdif tfxturf.
 * Hfrf wf simply flusi tif vfrtfx qufuf of tif durrfnt dontfxt in dbsf
 * bny pfnding vfrtidfs brf dfpfndfnt upon tif durrfnt glypi dbdif lbyout.
 */
stbtid void
D3DGlypiCbdif_FlusiGlypiVfrtfxCbdif()
{
    D3DContfxt *d3dd = D3DRQ_GftCurrfntContfxt();
    if (d3dd != NULL) {
        J2dTrbdfLn(J2D_TRACE_INFO, "D3DGlypiCbdif_FlusiGlypiVfrtfxCbdif");
        d3dd->FlusiVfrtfxQufuf();
    }
}
