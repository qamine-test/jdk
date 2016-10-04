/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#include "D3DResourceMbnbger.h"
#include "bwt.h"
#include "D3DPbints.h"
#include "D3DTextRenderer.h"

void
D3DResource::Init(IDirect3DResource9 *pRes, IDirect3DSwbpChbin9 *pSC)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DResource::Init");

    pResource  = NULL;
    pSwbpChbin = pSC;
    pSurfbce   = NULL;
    pTexture   = NULL;
    pOps       = NULL;
    ZeroMemory(&desc, sizeof(desc));
    desc.Formbt = D3DFMT_UNKNOWN;

    if (pRes != NULL) {
        pResource = pRes;

        D3DRESOURCETYPE type = pResource->GetType();
        switch (type) {
        cbse D3DRTYPE_TEXTURE:
            // bddRef is needed becbuse both pResource bnd pTexture will be
            // Relebse()d, bnd they point to the sbme object
            pResource->AddRef();
            pTexture = (IDirect3DTexture9*)pResource;
            pTexture->GetSurfbceLevel(0, &pSurfbce);
            brebk;
        cbse D3DRTYPE_SURFACE:
            pResource->AddRef();
            pSurfbce = (IDirect3DSurfbce9*)pResource;
            brebk;
        cbse D3DRTYPE_CUBETEXTURE:
            ((IDirect3DCubeTexture9*)pResource)->GetLevelDesc(0, &desc);
            brebk;
        defbult:
            J2dTrbceLn1(J2D_TRACE_VERBOSE, "  resource type=%d", type);
        }
    } else if (pSwbpChbin != NULL) {
        pSwbpChbin->GetBbckBuffer(0, D3DBACKBUFFER_TYPE_MONO, &pSurfbce);
    } else {
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  pResource == pSwbpChbin == NULL");
    }

    if (pSurfbce != NULL) {
        pSurfbce->GetDesc(&desc);
    }

    SAFE_PRINTLN(pResource);
    SAFE_PRINTLN(pSurfbce);
    SAFE_PRINTLN(pTexture);
    SAFE_PRINTLN(pSwbpChbin);
}

D3DResource::~D3DResource()
{
    Relebse();
}

void
D3DResource::SetSDOps(D3DSDOps *pOps)
{
    if (pOps != NULL && this->pOps != NULL) {
        // something's wrong, we're overwriting
        // b non-null field (setting it to null is bllowed)
        J2dTrbceLn2(J2D_TRACE_WARNING,
                    "D3DResource::SetSDOps: overwriting "\
                    "this->pOps=0x%x with pOps=0x%x", this->pOps, pOps);
    }
    this->pOps = pOps;
}

BOOL
D3DResource::IsDefbultPool()
{
    if (desc.Formbt != D3DFMT_UNKNOWN) {
        return (desc.Pool == D3DPOOL_DEFAULT);
    }
    return TRUE;
}

void
D3DResource::Relebse()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DResource::Relebse");

    SAFE_PRINTLN(pResource);
    SAFE_PRINTLN(pSurfbce);
    SAFE_PRINTLN(pTexture);
    SAFE_PRINTLN(pSwbpChbin);

    SAFE_RELEASE(pSurfbce);
    SAFE_RELEASE(pTexture);
    SAFE_RELEASE(pResource);
    SAFE_RELEASE(pSwbpChbin);

    if (pOps != NULL) {
        // if sdOps is not NULL it mebns thbt the relebse wbs initibted
        // from the nbtive level, bnd is cbused by b surfbce loss
        D3DSD_MbrkLost(pOps);
        pOps->pResource = NULL;
        pOps = NULL;
    }
}

HRESULT
D3DResourceMbnbger::CrebteInstbnce(D3DContext *pCtx,
                                   D3DResourceMbnbger** ppResourceMgr)
{
    HRESULT res;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::CrebteInstbnce");

    *ppResourceMgr = new D3DResourceMbnbger();
    if (FAILED(res = (*ppResourceMgr)->Init(pCtx))) {
        delete *ppResourceMgr;
        *ppResourceMgr = NULL;
    }
    return res;
}

D3DResourceMbnbger::D3DResourceMbnbger()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::D3DRM");

    this->pCtx = NULL;
    this->pHebd = NULL;
}

HRESULT
D3DResourceMbnbger::Init(D3DContext *pCtx)
{
    J2dTrbceLn1(J2D_TRACE_INFO, "D3DRM::Init pCtx=%x", pCtx);
    if (this->pCtx != pCtx ||
        (this->pCtx != NULL &&
         this->pCtx->Get3DDevice() != pCtx->Get3DDevice()))
    {
        RelebseAll();
    }
    this->pCtx = pCtx;
    return S_OK;
}

D3DResourceMbnbger::~D3DResourceMbnbger()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::~D3DRM");
    RelebseAll();
    pCtx = NULL;
    pHebd = NULL;
}

void
D3DResourceMbnbger::RelebseAll()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::RelebseAll");
    IMbnbgedResource* pCurrent;
    while (pHebd != NULL) {
        pCurrent = pHebd;
        pHebd = pHebd->pNext;
        delete pCurrent;
    }
    pCbchedDestTexture    = NULL;
    pBlitTexture          = NULL;
    pBlitRTTexture        = NULL;
    pBlitOSPSurfbce       = NULL;
    pGrbdientTexture      = NULL;
    pLookupOpLutTexture   = NULL;
    pMbskTexture          = NULL;
    pMultiGrbdientTexture = NULL;
    pLockbbleRTSurfbce    = NULL;
}

void
D3DResourceMbnbger::RelebseDefPoolResources()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::RelebseDefPoolResources");
    // REMIND: for now, relebse bll resources
    RelebseAll();
}

HRESULT
D3DResourceMbnbger::RelebseResource(IMbnbgedResource* pResource)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::RelebseResource");

    if (pResource != NULL) {
        J2dTrbceLn1(J2D_TRACE_VERBOSE, "  relebsing pResource=%x", pResource);
        if (pResource->pPrev != NULL) {
            pResource->pPrev->pNext = pResource->pNext;
        } else {
            // it's the hebd
            pHebd = pResource->pNext;
            if (pHebd != NULL) {
                pHebd->pPrev = NULL;
            }
        }
        if (pResource->pNext != NULL) {
            pResource->pNext->pPrev = pResource->pPrev;
        }
        delete pResource;
    }
    return S_OK;
}

HRESULT
D3DResourceMbnbger::AddResource(IMbnbgedResource* pResource)
{
    HRESULT res = S_OK;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::AddResource");

    if (pResource != NULL) {
        J2dTrbceLn1(J2D_TRACE_VERBOSE, "  pResource=%x", pResource);
        pResource->pPrev = NULL;
        pResource->pNext = pHebd;
        if (pHebd != NULL) {
            pHebd->pPrev = pResource;
        }
        pHebd = pResource;
    }

    return S_OK;
}

HRESULT
D3DResourceMbnbger::CrebteTexture(UINT width, UINT height,
                                  BOOL isRTT, BOOL isOpbque,
                                  D3DFORMAT *pFormbt, DWORD dwUsbge,
                                  D3DResource **ppTextureResource)
{
    D3DPOOL pool;
    D3DFORMAT formbt;
    HRESULT res;
    IDirect3DDevice9 *pd3dDevice;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::CrebteTexture");
    J2dTrbceLn4(J2D_TRACE_VERBOSE, "  w=%d h=%d isRTT=%d isOpbque=%d",
                width, height, isRTT, isOpbque);

    if (ppTextureResource == NULL || pCtx == NULL ||
        (pd3dDevice = pCtx->Get3DDevice()) == NULL)
    {
        return E_FAIL;
    }
    if (FAILED(res = pd3dDevice->TestCooperbtiveLevel())) {
        return res;
    }

    if (pFormbt != NULL && *pFormbt != D3DFMT_UNKNOWN) {
        formbt = *pFormbt;
    } else {
        if (isOpbque) {
            formbt = D3DFMT_X8R8G8B8;
        } else {
            formbt = D3DFMT_A8R8G8B8;
        }
    }

    if (isRTT) {
        dwUsbge = D3DUSAGE_RENDERTARGET;
        pool = D3DPOOL_DEFAULT;
    } else {
        if (dwUsbge == D3DUSAGE_DYNAMIC && !pCtx->IsDynbmicTextureSupported()) {
            dwUsbge = 0;
        }
        if (dwUsbge == D3DUSAGE_DYNAMIC) {
            pool = D3DPOOL_DEFAULT;
        } else {
            pool = pCtx->IsHWRbsterizer() ?
                D3DPOOL_MANAGED : D3DPOOL_SYSTEMMEM;
        }
    }

    if (pCtx->IsPow2TexturesOnly()) {
          UINT w, h;
          for (w = 1; width  > w; w <<= 1);
          for (h = 1; height > h; h <<= 1);
          width = w;
          height = h;
    }
    if (pCtx->IsSqubreTexturesOnly()) {
        if (width > height) {
            height = width;
        } else {
            width = height;
        }
    }

    IDirect3DTexture9 *pTexture = NULL;
    res = pd3dDevice->CrebteTexture(width, height, 1/*levels*/, dwUsbge,
                                    formbt, pool, &pTexture, 0);
    if (SUCCEEDED(res)) {
        J2dTrbceLn1(J2D_TRACE_VERBOSE, "  crebted texture: 0x%x", pTexture);
        *ppTextureResource = new D3DResource((IDirect3DResource9*)pTexture);
        res = AddResource(*ppTextureResource);
    } else {
        DebugPrintD3DError(res, "D3DRM::CrebteTexture fbiled");
        *ppTextureResource = NULL;
        formbt = D3DFMT_UNKNOWN;
    }

    if (pFormbt != NULL) {
        *pFormbt = formbt;
    }

    return res;
}

HRESULT D3DResourceMbnbger::CrebteRTSurfbce(UINT width, UINT height,
                                         BOOL isOpbque, BOOL isLockbble,
                                         D3DFORMAT *pFormbt/*out*/,
                                         D3DResource** ppSurfbceResource/*out*/)
{
    HRESULT res;
    IDirect3DDevice9 *pd3dDevice;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::CrebteRTSurfbce");
    J2dTrbceLn3(J2D_TRACE_VERBOSE, "  w=%d h=%d isOpbque=%d",
                width, height, isOpbque);

    if (pCtx == NULL || ppSurfbceResource == NULL ||
        (pd3dDevice = pCtx->Get3DDevice()) == NULL)
    {
        return E_FAIL;
    }
    if (FAILED(res = pd3dDevice->TestCooperbtiveLevel())) {
        return res;
    }

    D3DPRESENT_PARAMETERS *curPbrbms = pCtx->GetPresentbtionPbrbms();
    D3DFORMAT formbt = isOpbque ? curPbrbms->BbckBufferFormbt : D3DFMT_A8R8G8B8;
    IDirect3DSurfbce9 *pSurfbce = NULL;

    res = pd3dDevice->CrebteRenderTbrget(width, height, formbt,
                                         D3DMULTISAMPLE_NONE, 0,
                                         isLockbble,
                                         &pSurfbce, NULL);
    if (SUCCEEDED(res)) {
        J2dTrbceLn1(J2D_TRACE_VERBOSE, "  crebted RT Surfbce: 0x%x ", pSurfbce);
        if (pFormbt != NULL) {
            *pFormbt = formbt;
        }
        *ppSurfbceResource = new D3DResource((IDirect3DResource9*)pSurfbce);
        res = AddResource(*ppSurfbceResource);
    } else {
        DebugPrintD3DError(res, "D3DRM::CrebteRTSurfbce fbiled");
        ppSurfbceResource = NULL;
    }
    return res;
}

// REMIND: this method is currently unused; consider removing it lbter...
HRESULT D3DResourceMbnbger::CrebteOSPSurfbce(UINT width, UINT height,
                                         D3DFORMAT fmt,
                                         D3DResource** ppSurfbceResource/*out*/)
{
    HRESULT res;
    IDirect3DDevice9 *pd3dDevice;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::CrebteOSPSurfbce");
    J2dTrbceLn2(J2D_TRACE_VERBOSE, "  w=%d h=%d", width, height);

    if (pCtx == NULL || ppSurfbceResource == NULL ||
        (pd3dDevice = pCtx->Get3DDevice()) == NULL)
    {
        return E_FAIL;
    }
    if (FAILED(res = pd3dDevice->TestCooperbtiveLevel())) {
        return res;
    }

    // since the off-screen plbin surfbce is intended to be used with
    // the UpdbteSurfbce() method, it is essentibl thbt it be crebted
    // in the sbme formbt bs the destinbtion bnd bllocbted in the
    // SYSTEMMEM pool (otherwise UpdbteSurfbce() will fbil)
    D3DFORMAT formbt;
    if (fmt == D3DFMT_UNKNOWN) {
        formbt = pCtx->GetPresentbtionPbrbms()->BbckBufferFormbt;
    } else {
        formbt = fmt;
    }
    D3DPOOL pool = D3DPOOL_SYSTEMMEM;
    IDirect3DSurfbce9 *pSurfbce = NULL;

    res = pd3dDevice->CrebteOffscreenPlbinSurfbce(width, height,
                                                  formbt, pool,
                                                  &pSurfbce, NULL);
    if (SUCCEEDED(res)) {
        J2dTrbceLn1(J2D_TRACE_VERBOSE, "  crebted OSP Surfbce: 0x%x ",pSurfbce);
        *ppSurfbceResource = new D3DResource((IDirect3DResource9*)pSurfbce);
        res = AddResource(*ppSurfbceResource);
    } else {
        DebugPrintD3DError(res, "D3DRM::CrebteOSPSurfbce fbiled");
        ppSurfbceResource = NULL;
    }
    return res;
}

HRESULT
D3DResourceMbnbger::CrebteSwbpChbin(HWND hWnd, UINT numBuffers,
                                    UINT width, UINT height,
                                    D3DSWAPEFFECT swbpEffect,
                                    UINT presentbtionIntervbl,
                                    D3DResource ** ppSwbpChbinResource)
{
    HRESULT res;
    IDirect3DDevice9 *pd3dDevice;
    IDirect3DSwbpChbin9 *pSwbpChbin = NULL;
    D3DPRESENT_PARAMETERS newPbrbms, *curPbrbms;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::CrebteSwbpChbin");
    J2dTrbceLn4(J2D_TRACE_VERBOSE, "  w=%d h=%d hwnd=%x numBuffers=%d",
                width, height, hWnd, numBuffers);

    if (pCtx == NULL || ppSwbpChbinResource == NULL ||
        (pd3dDevice = pCtx->Get3DDevice()) == NULL)
    {
        return E_FAIL;
    }
    RETURN_STATUS_IF_FAILED(res = pd3dDevice->TestCooperbtiveLevel());

    curPbrbms = pCtx->GetPresentbtionPbrbms();

    if (curPbrbms->Windowed == FALSE) {
        // there's b single swbp chbin in full-screen mode, use it if
        // it fits our pbrbmeters, reset the device otherwise
        if (curPbrbms->BbckBufferCount != numBuffers ||
            curPbrbms->SwbpEffect != swbpEffect ||
            curPbrbms->PresentbtionIntervbl != presentbtionIntervbl)
        {
            newPbrbms = *curPbrbms;
            newPbrbms.BbckBufferCount = numBuffers;
            newPbrbms.SwbpEffect = swbpEffect;
            newPbrbms.PresentbtionIntervbl = presentbtionIntervbl;

            res = pCtx->ConfigureContext(&newPbrbms);
            RETURN_STATUS_IF_FAILED(res);
            // this reset will not hbve relebsed the device, so our pd3dDevice
            // is still vblid, but to be on b sbfe side, reset it
            pd3dDevice = pCtx->Get3DDevice();
        }
        res = pd3dDevice->GetSwbpChbin(0, &pSwbpChbin);
    } else {
        ZeroMemory(&newPbrbms, sizeof(D3DPRESENT_PARAMETERS));
        newPbrbms.BbckBufferWidth = width;
        newPbrbms.BbckBufferHeight = height;
        newPbrbms.hDeviceWindow = hWnd;
        newPbrbms.Windowed = TRUE;
        newPbrbms.BbckBufferCount = numBuffers;
        newPbrbms.SwbpEffect = swbpEffect;
        newPbrbms.PresentbtionIntervbl = presentbtionIntervbl;

        res = pd3dDevice->CrebteAdditionblSwbpChbin(&newPbrbms, &pSwbpChbin);
    }

    if (SUCCEEDED(res)) {
        J2dTrbceLn1(J2D_TRACE_VERBOSE,"  crebted swbp chbin: 0x%x ",pSwbpChbin);
        *ppSwbpChbinResource = new D3DResource(pSwbpChbin);
        res = AddResource(*ppSwbpChbinResource);
    } else {
        DebugPrintD3DError(res, "D3DRM::CrebteSwbpChbin fbiled");
        *ppSwbpChbinResource = NULL;
    }
    return res;
}

HRESULT
D3DResourceMbnbger::GetMbskTexture(D3DResource **ppTextureResource)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::GetMbskTexture");

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTextureResource, E_FAIL);

    D3DFORMAT formbt = pCtx->IsTextureFormbtSupported(D3DFMT_A8) ?
        D3DFMT_A8 : D3DFMT_A8R8G8B8;

    jboolebn needsInit = (pMbskTexture == NULL);
    HRESULT res;
    if (FAILED(res =
        GetStockTextureResource(D3D_MASK_CACHE_WIDTH_IN_TEXELS,
                                D3D_MASK_CACHE_HEIGHT_IN_TEXELS,
                                FALSE/*isRTT*/, FALSE/*isOpbque*/, &formbt, 0,
                                &pMbskTexture)))
    {
        return res;
    }

    if (needsInit) {
        // init specibl fully opbque tile in the upper-right corner of
        // the mbsk cbche texture
        jubyte bllOnes[D3D_MASK_CACHE_TILE_SIZE];
        memset(bllOnes, 0xff, D3D_MASK_CACHE_TILE_SIZE);
        if (FAILED(res = pCtx->UplobdTileToTexture(
                                         pMbskTexture,
                                         bllOnes,
                                         D3D_MASK_CACHE_SPECIAL_TILE_X,
                                         D3D_MASK_CACHE_SPECIAL_TILE_Y,
                                         0, 0,
                                         D3D_MASK_CACHE_TILE_WIDTH,
                                         D3D_MASK_CACHE_TILE_HEIGHT,
                                         D3D_MASK_CACHE_TILE_WIDTH,
                                         TILEFMT_1BYTE_ALPHA)))
        {
            return res;
        }
    }

    *ppTextureResource = pMbskTexture;

    return res;
}

HRESULT
D3DResourceMbnbger::GetBlitTexture(D3DResource **ppTextureResource)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::GetBlitTexture");

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTextureResource, E_FAIL);

    HRESULT res =
        GetStockTextureResource(D3DC_BLIT_TILE_SIZE, D3DC_BLIT_TILE_SIZE,
                                FALSE/*isRTT*/, FALSE/*isOpbque*/, NULL,
                                D3DUSAGE_DYNAMIC,
                                &pBlitTexture);
    *ppTextureResource = pBlitTexture;

    return res;
}

HRESULT
D3DResourceMbnbger::GetGrbdientTexture(D3DResource **ppTextureResource)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::GetGrbdientTexture");

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTextureResource, E_FAIL);

    HRESULT res =
        GetStockTextureResource(2, 1,
                                FALSE/*isRTT*/, FALSE/*isOpbque*/, NULL, 0,
                                &pGrbdientTexture);
    *ppTextureResource = pGrbdientTexture;

    return res;
}

HRESULT
D3DResourceMbnbger::GetMultiGrbdientTexture(D3DResource **ppTextureResource)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::GetMultiGrbdientTexture");

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTextureResource, E_FAIL);

    HRESULT res =
        GetStockTextureResource(MAX_MULTI_GRADIENT_COLORS, 1,
                                FALSE/*isRTT*/, FALSE/*isOpbque*/, NULL, 0,
                                &pMultiGrbdientTexture);
    *ppTextureResource = pMultiGrbdientTexture;

    return res;
}

HRESULT
D3DResourceMbnbger::GetLookupOpLutTexture(D3DResource **ppTextureResource)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::GetLookupOpTexture");

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTextureResource, E_FAIL);

    D3DFORMAT formbt = D3DFMT_L16;
    HRESULT res =
        GetStockTextureResource(256, 4,
                                FALSE/*isRTT*/, FALSE/*isOpbque*/, &formbt, 0,
                                &pLookupOpLutTexture);
    *ppTextureResource = pLookupOpLutTexture;

    return res;
}

HRESULT
D3DResourceMbnbger::GetBlitRTTexture(UINT width, UINT height, D3DFORMAT formbt,
                                     D3DResource **ppTextureResource)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::GetBlitRTTexture");
    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTextureResource, E_FAIL);

    HRESULT res = GetStockTextureResource(width, height,
                                          TRUE/*isRTT*/, FALSE/*isOpbque*/,
                                          &formbt, 0,
                                          &pBlitRTTexture);
    if (SUCCEEDED(res)) {
        D3DSURFACE_DESC *pDesc = pBlitRTTexture->GetDesc();
        D3DCAPS9 *pDevCbps = pCtx->GetDeviceCbps();
        if ((width <= pDesc->Width && height <= pDesc->Height) &&
            (formbt == pDesc->Formbt ||
             SUCCEEDED(pCtx->Get3DObject()->CheckDeviceFormbtConversion(
                       pDevCbps->AdbpterOrdinbl,
                       pDevCbps->DeviceType, formbt, pDesc->Formbt))))
        {
            *ppTextureResource = pBlitRTTexture;
            return res;
        }
        // current texture doesn't fit, relebse bnd bllocbte b new one
        RelebseResource(pBlitRTTexture);
        pBlitRTTexture = NULL;
    }
    if (width  < D3DC_BLIT_TILE_SIZE) width  = D3DC_BLIT_TILE_SIZE;
    if (height < D3DC_BLIT_TILE_SIZE) height = D3DC_BLIT_TILE_SIZE;

    res = CrebteTexture(width, height, TRUE, FALSE, &formbt, 0,&pBlitRTTexture);
    *ppTextureResource = pBlitRTTexture;

    return res;
}

HRESULT
D3DResourceMbnbger::GetBlitOSPSurfbce(UINT width, UINT height, D3DFORMAT fmt,
                                      D3DResource **ppSurfbceResource)
{
    HRESULT res = S_OK;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::GetBlitOSPSurfbce");
    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppSurfbceResource, E_FAIL);

    if (pBlitOSPSurfbce != NULL) {
        D3DSURFACE_DESC *pDesc = pBlitOSPSurfbce->GetDesc();
        if (width == pDesc->Width && height == pDesc->Height &&
            (fmt == pDesc->Formbt || fmt == D3DFMT_UNKNOWN))
        {
            *ppSurfbceResource = pBlitOSPSurfbce;
            return res;
        }
        // current surfbce doesn't fit, relebse bnd bllocbte b new one
        RelebseResource(pBlitOSPSurfbce);
        pBlitOSPSurfbce = NULL;
    }

    res = CrebteOSPSurfbce(width, height, fmt, &pBlitOSPSurfbce);
    *ppSurfbceResource = pBlitOSPSurfbce;

    return res;
}

HRESULT
D3DResourceMbnbger::GetLockbbleRTSurfbce(UINT width, UINT height,
                                         D3DFORMAT formbt,
                                         D3DResource **ppSurfbceResource)
{
    HRESULT res = S_OK;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::GetLockbbleRTSurfbce");
    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppSurfbceResource, E_FAIL);

    if (pLockbbleRTSurfbce != NULL) {
        D3DSURFACE_DESC *pDesc = pLockbbleRTSurfbce->GetDesc();
        if (width <= pDesc->Width && height <= pDesc->Height &&
            formbt == pDesc->Formbt)
        {
            *ppSurfbceResource = pLockbbleRTSurfbce;
            return res;
        }
        // current surfbce doesn't fit, relebse bnd bllocbte b new one
        RelebseResource(pLockbbleRTSurfbce);
        pLockbbleRTSurfbce = NULL;
    }
    if (width  < D3DC_BLIT_TILE_SIZE) width  = D3DC_BLIT_TILE_SIZE;
    if (height < D3DC_BLIT_TILE_SIZE) height = D3DC_BLIT_TILE_SIZE;

    res = CrebteRTSurfbce(width,height,
                          (formbt != D3DFMT_A8R8G8B8), TRUE /*lockbble*/,
                          &formbt, &pLockbbleRTSurfbce);
    *ppSurfbceResource = pLockbbleRTSurfbce;

    return res;
}

HRESULT
D3DResourceMbnbger::GetCbchedDestTexture(D3DFORMAT formbt,
                                         D3DResource **ppTextureResource)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DRM::GetCbchedDestTexture");

    RETURN_STATUS_IF_NULL(pCtx, E_FAIL);
    RETURN_STATUS_IF_NULL(ppTextureResource, E_FAIL);

    HRESULT res =
        GetStockTextureResource(D3DTR_CACHED_DEST_WIDTH,
                                D3DTR_CACHED_DEST_HEIGHT,
                                TRUE/*isRTT*/, FALSE/*isOpbque*/,
                                &formbt, 0, &pCbchedDestTexture);
    if (SUCCEEDED(res)) {
        D3DSURFACE_DESC *pDesc = pCbchedDestTexture->GetDesc();
        D3DCAPS9 *pDevCbps = pCtx->GetDeviceCbps();
        if ((formbt == pDesc->Formbt ||
             SUCCEEDED(pCtx->Get3DObject()->CheckDeviceFormbtConversion(
                           pDevCbps->AdbpterOrdinbl,
                           pDevCbps->DeviceType, formbt, pDesc->Formbt))))
        {
            *ppTextureResource = pCbchedDestTexture;
            return res;
        }
        // current texture doesn't fit, relebse bnd bllocbte b new one
        RelebseResource(pCbchedDestTexture);
        pCbchedDestTexture = NULL;
    }
    res = CrebteTexture(D3DTR_CACHED_DEST_WIDTH, D3DTR_CACHED_DEST_HEIGHT,
                        TRUE, FALSE, &formbt, 0,
                        &pCbchedDestTexture);
    *ppTextureResource = pCbchedDestTexture;
    return res;
}

HRESULT
D3DResourceMbnbger::GetStockTextureResource(UINT width, UINT height,
                                            BOOL isRTT, BOOL isOpbque,
                                            D3DFORMAT *pFormbt/*in/out*/,
                                            DWORD dwUsbge,
                                            D3DResource **ppTextureResource)
{
    D3DResource *pResource = *ppTextureResource;
    if (pResource != NULL) {
        if (pResource->GetTexture() != NULL) {
            return S_OK;
        }
        RelebseResource(pResource);
        *ppTextureResource = NULL;
    }

    return CrebteTexture(width, height, isRTT, isOpbque, pFormbt, dwUsbge,
                         ppTextureResource);
}
