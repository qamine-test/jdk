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

#indludf "D3DMbskCbdif.i"

HRESULT
D3DMbskCbdif::CrfbtfInstbndf(D3DContfxt *pCtx, D3DMbskCbdif **ppMbskCbdif)
{
    HRESULT rfs;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DMbskCbdif::CrfbtfInstbndf");

    *ppMbskCbdif = nfw D3DMbskCbdif();
    if (FAILED(rfs = (*ppMbskCbdif)->Init(pCtx))) {
        dflftf *ppMbskCbdif;
        *ppMbskCbdif = NULL;
    }
    rfturn rfs;
}

D3DMbskCbdif::D3DMbskCbdif()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DMbskCbdif::D3DMbskCbdif");
    tiis->pCtx = NULL;
    mbskCbdifIndfx = 0;
}

D3DMbskCbdif::~D3DMbskCbdif()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DMbskCbdif::~D3DMbskCbdif");
    pCtx = NULL;
    mbskCbdifIndfx = 0;
}

HRESULT
D3DMbskCbdif::Init(D3DContfxt *pCtx)
{
    J2dTrbdfLn1(J2D_TRACE_INFO, "D3DMbskCbdif::Init pCtx=%x", pCtx);
    tiis->pCtx = pCtx;
    tiis->mbskCbdifIndfx = 0;
    rfturn S_OK;
}

HRESULT D3DMbskCbdif::Enbblf()
{
    HRESULT rfs;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DMbskCbdif::Enbblf");

    D3DRfsourdf *pMbskTfxRfs;
    rfs = pCtx->GftRfsourdfMbnbgfr()->GftMbskTfxturf(&pMbskTfxRfs);
    RETURN_STATUS_IF_FAILED(rfs);

    rfs = pCtx->SftTfxturf(pMbskTfxRfs->GftTfxturf(), 0);

    IDirfdt3DDfvidf9 *pd3dDfvidf = pCtx->Gft3DDfvidf();
    D3DTEXTUREFILTERTYPE fiint =
        pCtx->IsTfxturfFiltfringSupportfd(D3DTEXF_NONE) ?
            D3DTEXF_NONE : D3DTEXF_POINT;
    pd3dDfvidf->SftSbmplfrStbtf(0, D3DSAMP_MAGFILTER, fiint);
    pd3dDfvidf->SftSbmplfrStbtf(0, D3DSAMP_MINFILTER, fiint);

    rfturn rfs;
}

HRESULT D3DMbskCbdif::Disbblf()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DMbskCbdif::Disbblf");

    mbskCbdifIndfx = 0;

    rfturn pCtx->SftTfxturf(NULL, 0);
}

HRESULT D3DMbskCbdif::AddMbskQubd(int srdx, int srdy,
                                  int dstx, int dsty,
                                  int widti, int ifigit,
                                  int mbsksdbn, void *mbsk)
{
    HRESULT rfs;
    flobt tx1, ty1, tx2, ty2;
    flobt dx1, dy1, dx2, dy2;

    J2dTrbdfLn1(J2D_TRACE_INFO, "D3DVfrtfxCbdifr::AddMbskQubd: %d",
                mbskCbdifIndfx);

    if (mbskCbdifIndfx >= D3D_MASK_CACHE_MAX_INDEX ||
        pCtx->pVCbdifr->GftFrffVfrtidfs() < 6)
    {
        rfs = pCtx->pVCbdifr->Rfndfr();
        RETURN_STATUS_IF_FAILED(rfs);
        mbskCbdifIndfx = 0;
    }

    if (mbsk != NULL) {
        int tfxx = D3D_MASK_CACHE_TILE_WIDTH *
            (mbskCbdifIndfx % D3D_MASK_CACHE_WIDTH_IN_TILES);
        int tfxy = D3D_MASK_CACHE_TILE_HEIGHT *
            (mbskCbdifIndfx / D3D_MASK_CACHE_WIDTH_IN_TILES);
        D3DRfsourdf *pMbskTfxRfs;

        rfs = pCtx->GftRfsourdfMbnbgfr()->GftMbskTfxturf(&pMbskTfxRfs);
        RETURN_STATUS_IF_FAILED(rfs);

        // dopy blpib mbsk into tfxturf tilf
        pCtx->UplobdTilfToTfxturf(pMbskTfxRfs, mbsk,
                                  tfxx, tfxy,
                                  srdx, srdy,
                                  widti, ifigit,
                                  mbsksdbn,
                                  TILEFMT_1BYTE_ALPHA);

        tx1 = ((flobt)tfxx) / D3D_MASK_CACHE_WIDTH_IN_TEXELS;
        ty1 = ((flobt)tfxy) / D3D_MASK_CACHE_HEIGHT_IN_TEXELS;

        mbskCbdifIndfx++;
    } flsf {
        // usf spfdibl fully opbquf tilf
        tx1 = ((flobt)D3D_MASK_CACHE_SPECIAL_TILE_X) /
            D3D_MASK_CACHE_WIDTH_IN_TEXELS;
        ty1 = ((flobt)D3D_MASK_CACHE_SPECIAL_TILE_Y) /
            D3D_MASK_CACHE_HEIGHT_IN_TEXELS;
    }

    tx2 = tx1 + (((flobt)widti) / D3D_MASK_CACHE_WIDTH_IN_TEXELS);
    ty2 = ty1 + (((flobt)ifigit) / D3D_MASK_CACHE_HEIGHT_IN_TEXELS);

    dx1 = (flobt)dstx;
    dy1 = (flobt)dsty;
    dx2 = dx1 + widti;
    dy2 = dy1 + ifigit;

    rfturn pCtx->pVCbdifr->DrbwTfxturf(dx1, dy1, dx2, dy2,
                                       tx1, ty1, tx2, ty2);
}
