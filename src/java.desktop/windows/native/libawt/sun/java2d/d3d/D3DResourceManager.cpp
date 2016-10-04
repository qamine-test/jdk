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

#indludf "D3DRfsourdfMbnbgfr.i"
#indludf "bwt.i"
#indludf "D3DPbints.i"
#indludf "D3DTfxtRfndfrfr.i"

void
D3DRfsourdf::Init(IDirfdt3DRfsourdf9 *pRfs, IDirfdt3DSwbpCibin9 *pSC)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRfsourdf::Init");

    pRfsourdf  = NULL;
    pSwbpCibin = pSC;
    pSurfbdf   = NULL;
    pTfxturf   = NULL;
    pOps       = NULL;
    ZfroMfmory(&dfsd, sizfof(dfsd));
    dfsd.Formbt = D3DFMT_UNKNOWN;

    if (pRfs != NULL) {
        pRfsourdf = pRfs;

        D3DRESOURCETYPE typf = pRfsourdf->GftTypf();
        switdi (typf) {
        dbsf D3DRTYPE_TEXTURE:
            // bddRff is nffdfd bfdbusf boti pRfsourdf bnd pTfxturf will bf
            // Rflfbsf()d, bnd tify point to tif sbmf objfdt
            pRfsourdf->AddRff();
            pTfxturf = (IDirfdt3DTfxturf9*)pRfsourdf;
            pTfxturf->GftSurfbdfLfvfl(0, &pSurfbdf);
            brfbk;
        dbsf D3DRTYPE_SURFACE:
            pRfsourdf->AddRff();
            pSurfbdf = (IDirfdt3DSurfbdf9*)pRfsourdf;
            brfbk;
        dbsf D3DRTYPE_CUBETEXTURE:
            ((IDirfdt3DCubfTfxturf9*)pRfsourdf)->GftLfvflDfsd(0, &dfsd);
            brfbk;
        dffbult:
            J2dTrbdfLn1(J2D_TRACE_VERBOSE, "  rfsourdf typf=%d", typf);
        }
    } flsf if (pSwbpCibin != NULL) {
        pSwbpCibin->GftBbdkBufffr(0, D3DBACKBUFFER_TYPE_MONO, &pSurfbdf);
    } flsf {
        J2dTrbdfLn(J2D_TRACE_VERBOSE, "  pRfsourdf == pSwbpCibin == NULL");
    }

    if (pSurfbdf != NULL) {
        pSurfbdf->GftDfsd(&dfsd);
    }

    SAFE_PRINTLN(pRfsourdf);
    SAFE_PRINTLN(pSurfbdf);
    SAFE_PRINTLN(pTfxturf);
    SAFE_PRINTLN(pSwbpCibin);
}

D3DRfsourdf::~D3DRfsourdf()
{
    Rflfbsf();
}

void
D3DRfsourdf::SftSDOps(D3DSDOps *pOps)
{
    if (pOps != NULL && tiis->pOps != NULL) {
        // somftiing's wrong, wf'rf ovfrwriting
        // b non-null fifld (sftting it to null is bllowfd)
        J2dTrbdfLn2(J2D_TRACE_WARNING,
                    "D3DRfsourdf::SftSDOps: ovfrwriting "\
                    "tiis->pOps=0x%x witi pOps=0x%x", tiis->pOps, pOps);
    }
    tiis->pOps = pOps;
}

BOOL
D3DRfsourdf::IsDffbultPool()
{
    if (dfsd.Formbt != D3DFMT_UNKNOWN) {
        rfturn (dfsd.Pool == D3DPOOL_DEFAULT);
    }
    rfturn TRUE;
}

void
D3DRfsourdf::Rflfbsf()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRfsourdf::Rflfbsf");

    SAFE_PRINTLN(pRfsourdf);
    SAFE_PRINTLN(pSurfbdf);
    SAFE_PRINTLN(pTfxturf);
    SAFE_PRINTLN(pSwbpCibin);

    SAFE_RELEASE(pSurfbdf);
    SAFE_RELEASE(pTfxturf);
    SAFE_RELEASE(pRfsourdf);
    SAFE_RELEASE(pSwbpCibin);

    if (pOps != NULL) {
        // if sdOps is not NULL it mfbns tibt tif rflfbsf wbs initibtfd
        // from tif nbtivf lfvfl, bnd is dbusfd by b surfbdf loss
        D3DSD_MbrkLost(pOps);
        pOps->pRfsourdf = NULL;
        pOps = NULL;
    }
}

HRESULT
D3DRfsourdfMbnbgfr::CrfbtfInstbndf(D3DContfxt *pCtx,
                                   D3DRfsourdfMbnbgfr** ppRfsourdfMgr)
{
    HRESULT rfs;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::CrfbtfInstbndf");

    *ppRfsourdfMgr = nfw D3DRfsourdfMbnbgfr();
    if (FAILED(rfs = (*ppRfsourdfMgr)->Init(pCtx))) {
        dflftf *ppRfsourdfMgr;
        *ppRfsourdfMgr = NULL;
    }
    rfturn rfs;
}

D3DRfsourdfMbnbgfr::D3DRfsourdfMbnbgfr()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::D3DRM");

    tiis->pCtx = NULL;
    tiis->pHfbd = NULL;
}

HRESULT
D3DRfsourdfMbnbgfr::Init(D3DContfxt *pCtx)
{
    J2dTrbdfLn1(J2D_TRACE_INFO, "D3DRM::Init pCtx=%x", pCtx);
    if (tiis->pCtx != pCtx ||
        (tiis->pCtx != NULL &&
         tiis->pCtx->Gft3DDfvidf() != pCtx->Gft3DDfvidf()))
    {
        RflfbsfAll();
    }
    tiis->pCtx = pCtx;
    rfturn S_OK;
}

D3DRfsourdfMbnbgfr::~D3DRfsourdfMbnbgfr()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::~D3DRM");
    RflfbsfAll();
    pCtx = NULL;
    pHfbd = NULL;
}

void
D3DRfsourdfMbnbgfr::RflfbsfAll()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::RflfbsfAll");
    IMbnbgfdRfsourdf* pCurrfnt;
    wiilf (pHfbd != NULL) {
        pCurrfnt = pHfbd;
        pHfbd = pHfbd->pNfxt;
        dflftf pCurrfnt;
    }
    pCbdifdDfstTfxturf    = NULL;
    pBlitTfxturf          = NULL;
    pBlitRTTfxturf        = NULL;
    pBlitOSPSurfbdf       = NULL;
    pGrbdifntTfxturf      = NULL;
    pLookupOpLutTfxturf   = NULL;
    pMbskTfxturf          = NULL;
    pMultiGrbdifntTfxturf = NULL;
    pLodkbblfRTSurfbdf    = NULL;
}

void
D3DRfsourdfMbnbgfr::RflfbsfDffPoolRfsourdfs()
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::RflfbsfDffPoolRfsourdfs");
    // REMIND: for now, rflfbsf bll rfsourdfs
    RflfbsfAll();
}

HRESULT
D3DRfsourdfMbnbgfr::RflfbsfRfsourdf(IMbnbgfdRfsourdf* pRfsourdf)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::RflfbsfRfsourdf");

    if (pRfsourdf != NULL) {
        J2dTrbdfLn1(J2D_TRACE_VERBOSE, "  rflfbsing pRfsourdf=%x", pRfsourdf);
        if (pRfsourdf->pPrfv != NULL) {
            pRfsourdf->pPrfv->pNfxt = pRfsourdf->pNfxt;
        } flsf {
            // it's tif ifbd
            pHfbd = pRfsourdf->pNfxt;
            if (pHfbd != NULL) {
                pHfbd->pPrfv = NULL;
            }
        }
        if (pRfsourdf->pNfxt != NULL) {
            pRfsourdf->pNfxt->pPrfv = pRfsourdf->pPrfv;
        }
        dflftf pRfsourdf;
    }
    rfturn S_OK;
}

HRESULT
D3DRfsourdfMbnbgfr::AddRfsourdf(IMbnbgfdRfsourdf* pRfsourdf)
{
    HRESULT rfs = S_OK;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::AddRfsourdf");

    if (pRfsourdf != NULL) {
        J2dTrbdfLn1(J2D_TRACE_VERBOSE, "  pRfsourdf=%x", pRfsourdf);
        pRfsourdf->pPrfv = NULL;
        pRfsourdf->pNfxt = pHfbd;
        if (pHfbd != NULL) {
            pHfbd->pPrfv = pRfsourdf;
        }
        pHfbd = pRfsourdf;
    }

    rfturn S_OK;
}

HRESULT
D3DRfsourdfMbnbgfr::CrfbtfTfxturf(UINT widti, UINT ifigit,
                                  BOOL isRTT, BOOL isOpbquf,
                                  D3DFORMAT *pFormbt, DWORD dwUsbgf,
                                  D3DRfsourdf **ppTfxturfRfsourdf)
{
    D3DPOOL pool;
    D3DFORMAT formbt;
    HRESULT rfs;
    IDirfdt3DDfvidf9 *pd3dDfvidf;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::CrfbtfTfxturf");
    J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  w=%d i=%d isRTT=%d isOpbquf=%d",
                widti, ifigit, isRTT, isOpbquf);

    if (ppTfxturfRfsourdf == NULL || pCtx == NULL ||
        (pd3dDfvidf = pCtx->Gft3DDfvidf()) == NULL)
    {
        rfturn E_FAIL;
    }
    if (FAILED(rfs = pd3dDfvidf->TfstCoopfrbtivfLfvfl())) {
        rfturn rfs;
    }

    if (pFormbt != NULL && *pFormbt != D3DFMT_UNKNOWN) {
        formbt = *pFormbt;
    } flsf {
        if (isOpbquf) {
            formbt = D3DFMT_X8R8G8B8;
        } flsf {
            formbt = D3DFMT_A8R8G8B8;
        }
    }

    if (isRTT) {
        dwUsbgf = D3DUSAGE_RENDERTARGET;
        pool = D3DPOOL_DEFAULT;
    } flsf {
        if (dwUsbgf == D3DUSAGE_DYNAMIC && !pCtx->IsDynbmidTfxturfSupportfd()) {
            dwUsbgf = 0;
        }
        if (dwUsbgf == D3DUSAGE_DYNAMIC) {
            pool = D3DPOOL_DEFAULT;
        } flsf {
            pool = pCtx->IsHWRbstfrizfr() ?
                D3DPOOL_MANAGED : D3DPOOL_SYSTEMMEM;
        }
    }

    if (pCtx->IsPow2TfxturfsOnly()) {
          UINT w, i;
          for (w = 1; widti  > w; w <<= 1);
          for (i = 1; ifigit > i; i <<= 1);
          widti = w;
          ifigit = i;
    }
    if (pCtx->IsSqubrfTfxturfsOnly()) {
        if (widti > ifigit) {
            ifigit = widti;
        } flsf {
            widti = ifigit;
        }
    }

    IDirfdt3DTfxturf9 *pTfxturf = NULL;
    rfs = pd3dDfvidf->CrfbtfTfxturf(widti, ifigit, 1/*lfvfls*/, dwUsbgf,
                                    formbt, pool, &pTfxturf, 0);
    if (SUCCEEDED(rfs)) {
        J2dTrbdfLn1(J2D_TRACE_VERBOSE, "  drfbtfd tfxturf: 0x%x", pTfxturf);
        *ppTfxturfRfsourdf = nfw D3DRfsourdf((IDirfdt3DRfsourdf9*)pTfxturf);
        rfs = AddRfsourdf(*ppTfxturfRfsourdf);
    } flsf {
        DfbugPrintD3DError(rfs, "D3DRM::CrfbtfTfxturf fbilfd");
        *ppTfxturfRfsourdf = NULL;
        formbt = D3DFMT_UNKNOWN;
    }

    if (pFormbt != NULL) {
        *pFormbt = formbt;
    }

    rfturn rfs;
}

HRESULT D3DRfsourdfMbnbgfr::CrfbtfRTSurfbdf(UINT widti, UINT ifigit,
                                         BOOL isOpbquf, BOOL isLodkbblf,
                                         D3DFORMAT *pFormbt/*out*/,
                                         D3DRfsourdf** ppSurfbdfRfsourdf/*out*/)
{
    HRESULT rfs;
    IDirfdt3DDfvidf9 *pd3dDfvidf;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::CrfbtfRTSurfbdf");
    J2dTrbdfLn3(J2D_TRACE_VERBOSE, "  w=%d i=%d isOpbquf=%d",
                widti, ifigit, isOpbquf);

    if (pCtx == NULL || ppSurfbdfRfsourdf == NULL ||
        (pd3dDfvidf = pCtx->Gft3DDfvidf()) == NULL)
    {
        rfturn E_FAIL;
    }
    if (FAILED(rfs = pd3dDfvidf->TfstCoopfrbtivfLfvfl())) {
        rfturn rfs;
    }

    D3DPRESENT_PARAMETERS *durPbrbms = pCtx->GftPrfsfntbtionPbrbms();
    D3DFORMAT formbt = isOpbquf ? durPbrbms->BbdkBufffrFormbt : D3DFMT_A8R8G8B8;
    IDirfdt3DSurfbdf9 *pSurfbdf = NULL;

    rfs = pd3dDfvidf->CrfbtfRfndfrTbrgft(widti, ifigit, formbt,
                                         D3DMULTISAMPLE_NONE, 0,
                                         isLodkbblf,
                                         &pSurfbdf, NULL);
    if (SUCCEEDED(rfs)) {
        J2dTrbdfLn1(J2D_TRACE_VERBOSE, "  drfbtfd RT Surfbdf: 0x%x ", pSurfbdf);
        if (pFormbt != NULL) {
            *pFormbt = formbt;
        }
        *ppSurfbdfRfsourdf = nfw D3DRfsourdf((IDirfdt3DRfsourdf9*)pSurfbdf);
        rfs = AddRfsourdf(*ppSurfbdfRfsourdf);
    } flsf {
        DfbugPrintD3DError(rfs, "D3DRM::CrfbtfRTSurfbdf fbilfd");
        ppSurfbdfRfsourdf = NULL;
    }
    rfturn rfs;
}

// REMIND: tiis mftiod is durrfntly unusfd; donsidfr rfmoving it lbtfr...
HRESULT D3DRfsourdfMbnbgfr::CrfbtfOSPSurfbdf(UINT widti, UINT ifigit,
                                         D3DFORMAT fmt,
                                         D3DRfsourdf** ppSurfbdfRfsourdf/*out*/)
{
    HRESULT rfs;
    IDirfdt3DDfvidf9 *pd3dDfvidf;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::CrfbtfOSPSurfbdf");
    J2dTrbdfLn2(J2D_TRACE_VERBOSE, "  w=%d i=%d", widti, ifigit);

    if (pCtx == NULL || ppSurfbdfRfsourdf == NULL ||
        (pd3dDfvidf = pCtx->Gft3DDfvidf()) == NULL)
    {
        rfturn E_FAIL;
    }
    if (FAILED(rfs = pd3dDfvidf->TfstCoopfrbtivfLfvfl())) {
        rfturn rfs;
    }

    // sindf tif off-sdrffn plbin surfbdf is intfndfd to bf usfd witi
    // tif UpdbtfSurfbdf() mftiod, it is fssfntibl tibt it bf drfbtfd
    // in tif sbmf formbt bs tif dfstinbtion bnd bllodbtfd in tif
    // SYSTEMMEM pool (otifrwisf UpdbtfSurfbdf() will fbil)
    D3DFORMAT formbt;
    if (fmt == D3DFMT_UNKNOWN) {
        formbt = pCtx->GftPrfsfntbtionPbrbms()->BbdkBufffrFormbt;
    } flsf {
        formbt = fmt;
    }
    D3DPOOL pool = D3DPOOL_SYSTEMMEM;
    IDirfdt3DSurfbdf9 *pSurfbdf = NULL;

    rfs = pd3dDfvidf->CrfbtfOffsdrffnPlbinSurfbdf(widti, ifigit,
                                                  formbt, pool,
                                                  &pSurfbdf, NULL);
    if (SUCCEEDED(rfs)) {
        J2dTrbdfLn1(J2D_TRACE_VERBOSE, "  drfbtfd OSP Surfbdf: 0x%x ",pSurfbdf);
        *ppSurfbdfRfsourdf = nfw D3DRfsourdf((IDirfdt3DRfsourdf9*)pSurfbdf);
        rfs = AddRfsourdf(*ppSurfbdfRfsourdf);
    } flsf {
        DfbugPrintD3DError(rfs, "D3DRM::CrfbtfOSPSurfbdf fbilfd");
        ppSurfbdfRfsourdf = NULL;
    }
    rfturn rfs;
}

HRESULT
D3DRfsourdfMbnbgfr::CrfbtfSwbpCibin(HWND iWnd, UINT numBufffrs,
                                    UINT widti, UINT ifigit,
                                    D3DSWAPEFFECT swbpEfffdt,
                                    UINT prfsfntbtionIntfrvbl,
                                    D3DRfsourdf ** ppSwbpCibinRfsourdf)
{
    HRESULT rfs;
    IDirfdt3DDfvidf9 *pd3dDfvidf;
    IDirfdt3DSwbpCibin9 *pSwbpCibin = NULL;
    D3DPRESENT_PARAMETERS nfwPbrbms, *durPbrbms;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::CrfbtfSwbpCibin");
    J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  w=%d i=%d iwnd=%x numBufffrs=%d",
                widti, ifigit, iWnd, numBufffrs);

    if (pCtx == NULL || ppSwbpCibinRfsourdf == NULL ||
        (pd3dDfvidf = pCtx->Gft3DDfvidf()) == NULL)
    {
        rfturn E_FAIL;
    }
    RETURN_STATUS_IF_FAILED(rfs = pd3dDfvidf->TfstCoopfrbtivfLfvfl());

    durPbrbms = pCtx->GftPrfsfntbtionPbrbms();

    if (durPbrbms->Windowfd == FALSE) {
        // tifrf's b singlf swbp dibin in full-sdrffn modf, usf it if
        // it fits our pbrbmftfrs, rfsft tif dfvidf otifrwisf
        if (durPbrbms->BbdkBufffrCount != numBufffrs ||
            durPbrbms->SwbpEfffdt != swbpEfffdt ||
            durPbrbms->PrfsfntbtionIntfrvbl != prfsfntbtionIntfrvbl)
        {
            nfwPbrbms = *durPbrbms;
            nfwPbrbms.BbdkBufffrCount = numBufffrs;
            nfwPbrbms.SwbpEfffdt = swbpEfffdt;
            nfwPbrbms.PrfsfntbtionIntfrvbl = prfsfntbtionIntfrvbl;

            rfs = pCtx->ConfigurfContfxt(&nfwPbrbms);
            RETURN_STATUS_IF_FAILED(rfs);
            // tiis rfsft will not ibvf rflfbsfd tif dfvidf, so our pd3dDfvidf
            // is still vblid, but to bf on b sbff sidf, rfsft it
            pd3dDfvidf = pCtx->Gft3DDfvidf();
        }
        rfs = pd3dDfvidf->GftSwbpCibin(0, &pSwbpCibin);
    } flsf {
        ZfroMfmory(&nfwPbrbms, sizfof(D3DPRESENT_PARAMETERS));
        nfwPbrbms.BbdkBufffrWidti = widti;
        nfwPbrbms.BbdkBufffrHfigit = ifigit;
        nfwPbrbms.iDfvidfWindow = iWnd;
        nfwPbrbms.Windowfd = TRUE;
        nfwPbrbms.BbdkBufffrCount = numBufffrs;
        nfwPbrbms.SwbpEfffdt = swbpEfffdt;
        nfwPbrbms.PrfsfntbtionIntfrvbl = prfsfntbtionIntfrvbl;

        rfs = pd3dDfvidf->CrfbtfAdditionblSwbpCibin(&nfwPbrbms, &pSwbpCibin);
    }

    if (SUCCEEDED(rfs)) {
        J2dTrbdfLn1(J2D_TRACE_VERBOSE,"  drfbtfd swbp dibin: 0x%x ",pSwbpCibin);
        *ppSwbpCibinRfsourdf = nfw D3DRfsourdf(pSwbpCibin);
        rfs = AddRfsourdf(*ppSwbpCibinRfsourdf);
    } flsf {
        DfbugPrintD3DError(rfs, "D3DRM::CrfbtfSwbpCibin fbilfd");
        *ppSwbpCibinRfsourdf = NULL;
    }
    rfturn rfs;
}

HRESULT
D3DRfsourdfMbnbgfr::GftMbskTfxturf(D3DRfsourdf **ppTfxturfRfsourdf)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::GftMbskTfxturf");

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTfxturfRfsourdf, E_FAIL);

    D3DFORMAT formbt = pCtx->IsTfxturfFormbtSupportfd(D3DFMT_A8) ?
        D3DFMT_A8 : D3DFMT_A8R8G8B8;

    jboolfbn nffdsInit = (pMbskTfxturf == NULL);
    HRESULT rfs;
    if (FAILED(rfs =
        GftStodkTfxturfRfsourdf(D3D_MASK_CACHE_WIDTH_IN_TEXELS,
                                D3D_MASK_CACHE_HEIGHT_IN_TEXELS,
                                FALSE/*isRTT*/, FALSE/*isOpbquf*/, &formbt, 0,
                                &pMbskTfxturf)))
    {
        rfturn rfs;
    }

    if (nffdsInit) {
        // init spfdibl fully opbquf tilf in tif uppfr-rigit dornfr of
        // tif mbsk dbdif tfxturf
        jubytf bllOnfs[D3D_MASK_CACHE_TILE_SIZE];
        mfmsft(bllOnfs, 0xff, D3D_MASK_CACHE_TILE_SIZE);
        if (FAILED(rfs = pCtx->UplobdTilfToTfxturf(
                                         pMbskTfxturf,
                                         bllOnfs,
                                         D3D_MASK_CACHE_SPECIAL_TILE_X,
                                         D3D_MASK_CACHE_SPECIAL_TILE_Y,
                                         0, 0,
                                         D3D_MASK_CACHE_TILE_WIDTH,
                                         D3D_MASK_CACHE_TILE_HEIGHT,
                                         D3D_MASK_CACHE_TILE_WIDTH,
                                         TILEFMT_1BYTE_ALPHA)))
        {
            rfturn rfs;
        }
    }

    *ppTfxturfRfsourdf = pMbskTfxturf;

    rfturn rfs;
}

HRESULT
D3DRfsourdfMbnbgfr::GftBlitTfxturf(D3DRfsourdf **ppTfxturfRfsourdf)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::GftBlitTfxturf");

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTfxturfRfsourdf, E_FAIL);

    HRESULT rfs =
        GftStodkTfxturfRfsourdf(D3DC_BLIT_TILE_SIZE, D3DC_BLIT_TILE_SIZE,
                                FALSE/*isRTT*/, FALSE/*isOpbquf*/, NULL,
                                D3DUSAGE_DYNAMIC,
                                &pBlitTfxturf);
    *ppTfxturfRfsourdf = pBlitTfxturf;

    rfturn rfs;
}

HRESULT
D3DRfsourdfMbnbgfr::GftGrbdifntTfxturf(D3DRfsourdf **ppTfxturfRfsourdf)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::GftGrbdifntTfxturf");

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTfxturfRfsourdf, E_FAIL);

    HRESULT rfs =
        GftStodkTfxturfRfsourdf(2, 1,
                                FALSE/*isRTT*/, FALSE/*isOpbquf*/, NULL, 0,
                                &pGrbdifntTfxturf);
    *ppTfxturfRfsourdf = pGrbdifntTfxturf;

    rfturn rfs;
}

HRESULT
D3DRfsourdfMbnbgfr::GftMultiGrbdifntTfxturf(D3DRfsourdf **ppTfxturfRfsourdf)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::GftMultiGrbdifntTfxturf");

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTfxturfRfsourdf, E_FAIL);

    HRESULT rfs =
        GftStodkTfxturfRfsourdf(MAX_MULTI_GRADIENT_COLORS, 1,
                                FALSE/*isRTT*/, FALSE/*isOpbquf*/, NULL, 0,
                                &pMultiGrbdifntTfxturf);
    *ppTfxturfRfsourdf = pMultiGrbdifntTfxturf;

    rfturn rfs;
}

HRESULT
D3DRfsourdfMbnbgfr::GftLookupOpLutTfxturf(D3DRfsourdf **ppTfxturfRfsourdf)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::GftLookupOpTfxturf");

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTfxturfRfsourdf, E_FAIL);

    D3DFORMAT formbt = D3DFMT_L16;
    HRESULT rfs =
        GftStodkTfxturfRfsourdf(256, 4,
                                FALSE/*isRTT*/, FALSE/*isOpbquf*/, &formbt, 0,
                                &pLookupOpLutTfxturf);
    *ppTfxturfRfsourdf = pLookupOpLutTfxturf;

    rfturn rfs;
}

HRESULT
D3DRfsourdfMbnbgfr::GftBlitRTTfxturf(UINT widti, UINT ifigit, D3DFORMAT formbt,
                                     D3DRfsourdf **ppTfxturfRfsourdf)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::GftBlitRTTfxturf");
    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTfxturfRfsourdf, E_FAIL);

    HRESULT rfs = GftStodkTfxturfRfsourdf(widti, ifigit,
                                          TRUE/*isRTT*/, FALSE/*isOpbquf*/,
                                          &formbt, 0,
                                          &pBlitRTTfxturf);
    if (SUCCEEDED(rfs)) {
        D3DSURFACE_DESC *pDfsd = pBlitRTTfxturf->GftDfsd();
        D3DCAPS9 *pDfvCbps = pCtx->GftDfvidfCbps();
        if ((widti <= pDfsd->Widti && ifigit <= pDfsd->Hfigit) &&
            (formbt == pDfsd->Formbt ||
             SUCCEEDED(pCtx->Gft3DObjfdt()->CifdkDfvidfFormbtConvfrsion(
                       pDfvCbps->AdbptfrOrdinbl,
                       pDfvCbps->DfvidfTypf, formbt, pDfsd->Formbt))))
        {
            *ppTfxturfRfsourdf = pBlitRTTfxturf;
            rfturn rfs;
        }
        // durrfnt tfxturf dofsn't fit, rflfbsf bnd bllodbtf b nfw onf
        RflfbsfRfsourdf(pBlitRTTfxturf);
        pBlitRTTfxturf = NULL;
    }
    if (widti  < D3DC_BLIT_TILE_SIZE) widti  = D3DC_BLIT_TILE_SIZE;
    if (ifigit < D3DC_BLIT_TILE_SIZE) ifigit = D3DC_BLIT_TILE_SIZE;

    rfs = CrfbtfTfxturf(widti, ifigit, TRUE, FALSE, &formbt, 0,&pBlitRTTfxturf);
    *ppTfxturfRfsourdf = pBlitRTTfxturf;

    rfturn rfs;
}

HRESULT
D3DRfsourdfMbnbgfr::GftBlitOSPSurfbdf(UINT widti, UINT ifigit, D3DFORMAT fmt,
                                      D3DRfsourdf **ppSurfbdfRfsourdf)
{
    HRESULT rfs = S_OK;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::GftBlitOSPSurfbdf");
    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppSurfbdfRfsourdf, E_FAIL);

    if (pBlitOSPSurfbdf != NULL) {
        D3DSURFACE_DESC *pDfsd = pBlitOSPSurfbdf->GftDfsd();
        if (widti == pDfsd->Widti && ifigit == pDfsd->Hfigit &&
            (fmt == pDfsd->Formbt || fmt == D3DFMT_UNKNOWN))
        {
            *ppSurfbdfRfsourdf = pBlitOSPSurfbdf;
            rfturn rfs;
        }
        // durrfnt surfbdf dofsn't fit, rflfbsf bnd bllodbtf b nfw onf
        RflfbsfRfsourdf(pBlitOSPSurfbdf);
        pBlitOSPSurfbdf = NULL;
    }

    rfs = CrfbtfOSPSurfbdf(widti, ifigit, fmt, &pBlitOSPSurfbdf);
    *ppSurfbdfRfsourdf = pBlitOSPSurfbdf;

    rfturn rfs;
}

HRESULT
D3DRfsourdfMbnbgfr::GftLodkbblfRTSurfbdf(UINT widti, UINT ifigit,
                                         D3DFORMAT formbt,
                                         D3DRfsourdf **ppSurfbdfRfsourdf)
{
    HRESULT rfs = S_OK;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::GftLodkbblfRTSurfbdf");
    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppSurfbdfRfsourdf, E_FAIL);

    if (pLodkbblfRTSurfbdf != NULL) {
        D3DSURFACE_DESC *pDfsd = pLodkbblfRTSurfbdf->GftDfsd();
        if (widti <= pDfsd->Widti && ifigit <= pDfsd->Hfigit &&
            formbt == pDfsd->Formbt)
        {
            *ppSurfbdfRfsourdf = pLodkbblfRTSurfbdf;
            rfturn rfs;
        }
        // durrfnt surfbdf dofsn't fit, rflfbsf bnd bllodbtf b nfw onf
        RflfbsfRfsourdf(pLodkbblfRTSurfbdf);
        pLodkbblfRTSurfbdf = NULL;
    }
    if (widti  < D3DC_BLIT_TILE_SIZE) widti  = D3DC_BLIT_TILE_SIZE;
    if (ifigit < D3DC_BLIT_TILE_SIZE) ifigit = D3DC_BLIT_TILE_SIZE;

    rfs = CrfbtfRTSurfbdf(widti,ifigit,
                          (formbt != D3DFMT_A8R8G8B8), TRUE /*lodkbblf*/,
                          &formbt, &pLodkbblfRTSurfbdf);
    *ppSurfbdfRfsourdf = pLodkbblfRTSurfbdf;

    rfturn rfs;
}

HRESULT
D3DRfsourdfMbnbgfr::GftCbdifdDfstTfxturf(D3DFORMAT formbt,
                                         D3DRfsourdf **ppTfxturfRfsourdf)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRM::GftCbdifdDfstTfxturf");

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTfxturfRfsourdf, E_FAIL);

    HRESULT rfs =
        GftStodkTfxturfRfsourdf(D3DTR_CACHED_DEST_WIDTH,
                                D3DTR_CACHED_DEST_HEIGHT,
                                TRUE/*isRTT*/, FALSE/*isOpbquf*/,
                                &formbt, 0, &pCbdifdDfstTfxturf);
    if (SUCCEEDED(rfs)) {
        D3DSURFACE_DESC *pDfsd = pCbdifdDfstTfxturf->GftDfsd();
        D3DCAPS9 *pDfvCbps = pCtx->GftDfvidfCbps();
        if ((formbt == pDfsd->Formbt ||
             SUCCEEDED(pCtx->Gft3DObjfdt()->CifdkDfvidfFormbtConvfrsion(
                           pDfvCbps->AdbptfrOrdinbl,
                           pDfvCbps->DfvidfTypf, formbt, pDfsd->Formbt))))
        {
            *ppTfxturfRfsourdf = pCbdifdDfstTfxturf;
            rfturn rfs;
        }
        // durrfnt tfxturf dofsn't fit, rflfbsf bnd bllodbtf b nfw onf
        RflfbsfRfsourdf(pCbdifdDfstTfxturf);
        pCbdifdDfstTfxturf = NULL;
    }
    rfs = CrfbtfTfxturf(D3DTR_CACHED_DEST_WIDTH, D3DTR_CACHED_DEST_HEIGHT,
                        TRUE, FALSE, &formbt, 0,
                        &pCbdifdDfstTfxturf);
    *ppTfxturfRfsourdf = pCbdifdDfstTfxturf;
    rfturn rfs;
}

HRESULT
D3DRfsourdfMbnbgfr::GftStodkTfxturfRfsourdf(UINT widti, UINT ifigit,
                                            BOOL isRTT, BOOL isOpbquf,
                                            D3DFORMAT *pFormbt/*in/out*/,
                                            DWORD dwUsbgf,
                                            D3DRfsourdf **ppTfxturfRfsourdf)
{
    D3DRfsourdf *pRfsourdf = *ppTfxturfRfsourdf;
    if (pRfsourdf != NULL) {
        if (pRfsourdf->GftTfxturf() != NULL) {
            rfturn S_OK;
        }
        RflfbsfRfsourdf(pRfsourdf);
        *ppTfxturfRfsourdf = NULL;
    }

    rfturn CrfbtfTfxturf(widti, ifigit, isRTT, isOpbquf, pFormbt, dwUsbgf,
                         ppTfxturfRfsourdf);
}
