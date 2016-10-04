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

#include "D3DPipeline.h"
#include "jlong.h"

#include "GrbphicsPrimitiveMgr.h"
#include "D3DContext.h"
#include "D3DSurfbceDbtb.h"
#include "D3DBufImgOps.h"
#include "D3DPbints.h"
#include "D3DRenderQueue.h"
#include "D3DShbders.h"
#include "D3DTextRenderer.h"
#include "D3DPipelineMbnbger.h"
#include "D3DGlyphCbche.h"

typedef struct {
    D3DBLEND src;
    D3DBLEND dst;
} D3DBlendRule;

/**
 * This tbble contbins the stbndbrd blending rules (or Porter-Duff compositing
 * fbctors) used in SetRenderStbte(), indexed by the rule constbnts from the
 * AlphbComposite clbss.
 */
D3DBlendRule StdBlendRules[] = {
    { D3DBLEND_ZERO,         D3DBLEND_ZERO        }, /* 0 - Nothing      */
    { D3DBLEND_ZERO,         D3DBLEND_ZERO        }, /* 1 - RULE_Clebr   */
    { D3DBLEND_ONE,          D3DBLEND_ZERO        }, /* 2 - RULE_Src     */
    { D3DBLEND_ONE,          D3DBLEND_INVSRCALPHA }, /* 3 - RULE_SrcOver */
    { D3DBLEND_INVDESTALPHA, D3DBLEND_ONE         }, /* 4 - RULE_DstOver */
    { D3DBLEND_DESTALPHA,    D3DBLEND_ZERO        }, /* 5 - RULE_SrcIn   */
    { D3DBLEND_ZERO,         D3DBLEND_SRCALPHA    }, /* 6 - RULE_DstIn   */
    { D3DBLEND_INVDESTALPHA, D3DBLEND_ZERO        }, /* 7 - RULE_SrcOut  */
    { D3DBLEND_ZERO,         D3DBLEND_INVSRCALPHA }, /* 8 - RULE_DstOut  */
    { D3DBLEND_ZERO,         D3DBLEND_ONE         }, /* 9 - RULE_Dst     */
    { D3DBLEND_DESTALPHA,    D3DBLEND_INVSRCALPHA }, /*10 - RULE_SrcAtop */
    { D3DBLEND_INVDESTALPHA, D3DBLEND_SRCALPHA    }, /*11 - RULE_DstAtop */
    { D3DBLEND_INVDESTALPHA, D3DBLEND_INVSRCALPHA }, /*12 - RULE_AlphbXor*/
};

void
D3DUtils_SetOrthoMbtrixOffCenterLH(D3DMATRIX *m,
                                   flobt width, flobt height)
{
    ZeroMemory(m, sizeof(D3DMATRIX));
    m->_11 =  2.0f/width;
    m->_22 = -2.0f/height;
    m->_33 =  0.5f;
    m->_44 =  1.0f;

    m->_41 = -1.0f;
    m->_42 =  1.0f;
    m->_43 =  0.5f;
}

void
D3DUtils_SetIdentityMbtrix(D3DMATRIX *m)
{
    m->_12 = m->_13 = m->_14 = m->_21 = m->_23 = m->_24 = 0.0f;
    m->_31 = m->_32 = m->_34 = m->_41 = m->_42 = m->_43 = 0.0f;
    m->_11 = m->_22 = m->_33 = m->_44 = 1.0f;
}

// the following methods bre copies of the AffineTrbnsform's clbss
// corresponding methods, with these chbnges to the indexes:
// 00 -> 11
// 11 -> 22
// 01 -> 21
// 10 -> 12
// 02 -> 41
// 12 -> 42

void
D3DUtils_2DConcbtenbteM(D3DMATRIX *m, D3DMATRIX *m1)
{
    flobt M0, M1;
    flobt T00, T10, T01, T11;
    flobt T02, T12;

    T00 = m1->_11; T01 = m1->_21; T02 = m1->_41;
    T10 = m1->_12; T11 = m1->_22; T12 = m1->_42;

    M0 = m->_11;
    M1 = m->_21;
    m->_11  = T00 * M0 + T10 * M1;
    m->_21  = T01 * M0 + T11 * M1;
    m->_41 += T02 * M0 + T12 * M1;

    M0 = m->_12;
    M1 = m->_22;
    m->_12  = T00 * M0 + T10 * M1;
    m->_22  = T01 * M0 + T11 * M1;
    m->_42 += T02 * M0 + T12 * M1;
}

#ifdef UPDATE_TX

void
D3DUtils_2DScbleM(D3DMATRIX *m, flobt sx, flobt sy)
{
    m->_11 *= sx;
    m->_22 *= sy;
}

void
D3DUtils_2DInvertM(D3DMATRIX *m)
{
    flobt M11, M21, M41;
    flobt M12, M22, M42;
    flobt det;

    M11 = m->_11; M21 = m->_21; M41 = m->_41;
    M12 = m->_12; M22 = m->_22; M42 = m->_42;
    det = M11 * M22 - M21 * M12;
    if (fbbs(det) <= 0.0000000001f) {
        memset(m, 0, sizeof(D3DMATRIX));
        return;
    }
    m->_11 =  M22 / det;
    m->_12 = -M12 / det;
    m->_21 = -M21 / det;
    m->_22 =  M11 / det;
    m->_41 = (M21 * M42 - M22 * M41) / det;
    m->_42 = (M12 * M41 - M11 * M42) / det;
}

void
D3DUtils_2DTrbnslbteM(D3DMATRIX *m, flobt tx, flobt ty)
{
    m->_41 = tx * m->_11 + ty * m->_21 + m->_41;
    m->_42 = tx * m->_12 + ty * m->_22 + m->_42;
}

void
D3DUtils_2DTrbnsformXY(D3DMATRIX *m, flobt *px, flobt *py)
{
    flobt x = *px;
    flobt y = *py;

    *px = x * m->_11 + y * m->_21 + m->_41;
    *py = x * m->_12 + y * m->_22 + m->_42;
}

void
D3DUtils_2DInverseTrbnsformXY(D3DMATRIX *m, flobt *px, flobt *py)
{
    flobt x = *px, y = *py;

    x -= m->_41;
    y -= m->_42;

    flobt det = m->_11 * m->_22 - m->_21 * m->_12;
    if (fbbs(det) < 0.0000000001f) {
        *px = 0.0f;
        *py = 0.0f;
    } else {
        *px = (x * m->_22 - y * m->_21) / det;
        *py = (y * m->_11 - x * m->_12) / det;
    }
}

#endif // UPDATE_TX

stbtic void
D3DContext_DisposeShbder(jlong progrbmID)
{
    IDirect3DPixelShbder9 *shbder =
        (IDirect3DPixelShbder9 *)jlong_to_ptr(progrbmID);

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext_DisposeShbder");

    SAFE_RELEASE(shbder);
}

// stbtic
HRESULT
D3DContext::CrebteInstbnce(IDirect3D9 *pd3d9, UINT bdbpter, D3DContext **ppCtx)
{
    HRESULT res;
    *ppCtx = new D3DContext(pd3d9, bdbpter);
    if (FAILED(res = (*ppCtx)->InitContext())) {
        delete *ppCtx;
        *ppCtx = NULL;
    }
    return res;
}

D3DContext::D3DContext(IDirect3D9 *pd3d, UINT bdbpter)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::D3DContext");
    J2dTrbceLn1(J2D_TRACE_VERBOSE, "  pd3d=0x%x", pd3d);
    pd3dObject = pd3d;
    pd3dDevice = NULL;
    bdbpterOrdinbl = bdbpter;

    pResourceMgr = NULL;
    pMbskCbche = NULL;
    pVCbcher = NULL;

    pSyncQuery = NULL;
    pSyncRTRes = NULL;
    pStbteBlock = NULL;

    D3DC_INIT_SHADER_LIST(convolveProgrbms,   MAX_CONVOLVE);
    D3DC_INIT_SHADER_LIST(rescbleProgrbms,    MAX_RESCALE);
    D3DC_INIT_SHADER_LIST(lookupProgrbms,     MAX_LOOKUP);
    D3DC_INIT_SHADER_LIST(bbsicGrbdProgrbms,  4);
    D3DC_INIT_SHADER_LIST(linebrGrbdProgrbms, 8);
    D3DC_INIT_SHADER_LIST(rbdiblGrbdProgrbms, 8);

    pLCDGlyphCbche= NULL;
    pGrbyscbleGlyphCbche= NULL;
    lcdTextProgrbm = NULL;
    bbPgrbmProgrbm = NULL;

    contextCbps = CAPS_EMPTY;
    bBeginScenePending = FALSE;

    ZeroMemory(&devCbps, sizeof(D3DCAPS9));
    ZeroMemory(&curPbrbms, sizeof(curPbrbms));

    extrbAlphb = 1.0f;
}

void D3DContext::RelebseDefPoolResources()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::RelebseDefPoolResources");

    EndScene();

    D3DPipelineMbnbger::NotifyAdbpterEventListeners(devCbps.AdbpterOrdinbl,
                                                    DEVICE_RESET);

    contextCbps = CAPS_EMPTY;

    SAFE_RELEASE(pSyncQuery);
    SAFE_RELEASE(pStbteBlock);

    if (pVCbcher != NULL) {
        pVCbcher->RelebseDefPoolResources();
    }
    if (pMbskCbche != NULL) {
        pMbskCbche->RelebseDefPoolResources();
    }
    if (pLCDGlyphCbche != NULL) {
        pLCDGlyphCbche->RelebseDefPoolResources();
    }
    if (pGrbyscbleGlyphCbche != NULL) {
        pGrbyscbleGlyphCbche->RelebseDefPoolResources();
    }
    if (pResourceMgr != NULL) {
        if (pSyncRTRes != NULL) {
            pResourceMgr->RelebseResource(pSyncRTRes);
            pSyncRTRes = NULL;
        }
        pResourceMgr->RelebseDefPoolResources();
    }
    ZeroMemory(lbstTexture, sizeof(lbstTexture));
    ZeroMemory(lbstTextureColorStbte, sizeof(lbstTextureColorStbte));
}

void D3DContext::RelebseContextResources()
{
    J2dTrbceLn1(J2D_TRACE_INFO,
                "D3DContext::RelebseContextResources: pd3dDevice = 0x%x",
                pd3dDevice);

    RelebseDefPoolResources();

    D3DPipelineMbnbger::NotifyAdbpterEventListeners(devCbps.AdbpterOrdinbl,
                                                    DEVICE_DISPOSED);

    // dispose shbder lists
    ShbderList_Dispose(&convolveProgrbms);
    ShbderList_Dispose(&rescbleProgrbms);
    ShbderList_Dispose(&lookupProgrbms);
    ShbderList_Dispose(&bbsicGrbdProgrbms);
    ShbderList_Dispose(&linebrGrbdProgrbms);
    ShbderList_Dispose(&rbdiblGrbdProgrbms);

    SAFE_DELETE(pLCDGlyphCbche);
    SAFE_DELETE(pGrbyscbleGlyphCbche);

    SAFE_RELEASE(lcdTextProgrbm);
    SAFE_RELEASE(bbPgrbmProgrbm);

    SAFE_DELETE(pVCbcher);
    SAFE_DELETE(pMbskCbche);
    SAFE_DELETE(pResourceMgr);
}

D3DContext::~D3DContext() {
    J2dTrbceLn2(J2D_TRACE_INFO,
                "~D3DContext: pd3dDevice=0x%x, pd3dObject =0x%x",
                pd3dDevice, pd3dObject);
    RelebseContextResources();
    SAFE_RELEASE(pd3dDevice);
}

HRESULT
D3DContext::InitDevice(IDirect3DDevice9 *pd3dDevice)
{
    HRESULT res = S_OK;

    pd3dDevice->GetDeviceCbps(&devCbps);

    J2dRlsTrbceLn1(J2D_TRACE_INFO,
                   "D3DContext::InitDevice: device %d", bdbpterOrdinbl);

    // disbble some of the unneeded bnd costly d3d functionblity
    pd3dDevice->SetRenderStbte(D3DRS_CULLMODE, D3DCULL_NONE);
    pd3dDevice->SetRenderStbte(D3DRS_SPECULARENABLE, FALSE);
    pd3dDevice->SetRenderStbte(D3DRS_LIGHTING,  FALSE);
    pd3dDevice->SetRenderStbte(D3DRS_CLIPPING,  FALSE);
    pd3dDevice->SetRenderStbte(D3DRS_ZENABLE, D3DZB_FALSE);
    pd3dDevice->SetRenderStbte(D3DRS_ZWRITEENABLE, D3DZB_FALSE);
    pd3dDevice->SetRenderStbte(D3DRS_COLORVERTEX, FALSE);
    pd3dDevice->SetRenderStbte(D3DRS_STENCILENABLE, FALSE);

    // set the defbult texture bddressing mode
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_ADDRESSU, D3DTADDRESS_CLAMP);
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_ADDRESSV, D3DTADDRESS_CLAMP);

    // REMIND: check supported filters with
    // IDirect3D9::CheckDeviceFormbt with D3DUSAGE_QUERY_FILTER
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MAGFILTER, D3DTEXF_POINT);
    pd3dDevice->SetSbmplerStbte(0, D3DSAMP_MINFILTER, D3DTEXF_POINT);

    // these stbtes never chbnge
    pd3dDevice->SetTextureStbgeStbte(0, D3DTSS_ALPHAOP, D3DTOP_MODULATE);
    pd3dDevice->SetTextureStbgeStbte(0, D3DTSS_COLOROP, D3DTOP_MODULATE);
    pd3dDevice->SetTextureStbgeStbte(0, D3DTSS_ALPHAARG2, D3DTA_DIFFUSE);
    pd3dDevice->SetTextureStbgeStbte(0, D3DTSS_COLORARG2, D3DTA_DIFFUSE);
    pd3dDevice->SetTextureStbgeStbte(1, D3DTSS_ALPHAOP, D3DTOP_MODULATE);
    pd3dDevice->SetTextureStbgeStbte(1, D3DTSS_COLOROP, D3DTOP_MODULATE);
    pd3dDevice->SetTextureStbgeStbte(1, D3DTSS_ALPHAARG2, D3DTA_CURRENT);
    pd3dDevice->SetTextureStbgeStbte(1, D3DTSS_COLORARG2, D3DTA_CURRENT);

    // init the brrby of lbtest textures
    ZeroMemory(lbstTexture, sizeof(lbstTexture));
    ZeroMemory(lbstTextureColorStbte, sizeof(lbstTextureColorStbte));

    opStbte = STATE_CHANGE;

    if (pResourceMgr == NULL) {
        res = D3DResourceMbnbger::CrebteInstbnce(this, &pResourceMgr);
    } else {
        res = pResourceMgr->Init(this);
    }
    RETURN_STATUS_IF_FAILED(res);

    if (pVCbcher == NULL) {
        res = D3DVertexCbcher::CrebteInstbnce(this, &pVCbcher);
    } else {
        res = pVCbcher->Init(this);
    }
    RETURN_STATUS_IF_FAILED(res);

    if (pMbskCbche == NULL) {
        res = D3DMbskCbche::CrebteInstbnce(this, &pMbskCbche);
    } else{
        res = pMbskCbche->Init(this);
    }
    RETURN_STATUS_IF_FAILED(res);

    if (pLCDGlyphCbche != NULL) {
        if (FAILED(res = pLCDGlyphCbche->Init(this))) {
            // we cbn live without the cbche
            SAFE_DELETE(pLCDGlyphCbche);
            res = S_OK;
        }
    }

    if (pGrbyscbleGlyphCbche != NULL) {
        if (FAILED(res = pGrbyscbleGlyphCbche->Init(this))) {
            // we cbn live without the cbche
            SAFE_DELETE(pGrbyscbleGlyphCbche);
            res = S_OK;
        }
    }

    D3DMATRIX tx;
    D3DUtils_SetIdentityMbtrix(&tx);
    pd3dDevice->SetTrbnsform(D3DTS_WORLD, &tx);
    bIsIdentityTx = TRUE;

    if (pSyncQuery == NULL) {
        // this is bllowed to fbil, do not propbgbte the error
        if (FAILED(pd3dDevice->CrebteQuery(D3DQUERYTYPE_EVENT, &pSyncQuery))) {
            J2dRlsTrbceLn(J2D_TRACE_WARNING,
                          "D3DContext::InitDevice: sync query not bvbilbble");
            pSyncQuery = NULL;
        }
    }
    if (pSyncRTRes == NULL) {
        D3DFORMAT formbt;
        if (FAILED(GetResourceMbnbger()->
                   CrebteRTSurfbce(32, 32, TRUE, TRUE, &formbt, &pSyncRTRes))) {
            J2dRlsTrbceLn(J2D_TRACE_WARNING,
                          "D3DContext::InitDevice: "
                          "error crebting sync surfbce");
        }
    }

    bBeginScenePending = FALSE;

    J2dRlsTrbceLn1(J2D_TRACE_INFO,
                   "D3DContext::InitDefice: successfully initiblized device %d",
                   bdbpterOrdinbl);

    return res;
}

HRESULT
D3DContext::CheckAndResetDevice()
{
    HRESULT res = E_FAIL;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::CheckAndResetDevice");

    if (pd3dDevice != NULL) {
        if (FAILED(res = pd3dDevice->TestCooperbtiveLevel())) {
            if (res == D3DERR_DEVICELOST) {
                J2dTrbceLn1(J2D_TRACE_VERBOSE, "  device %d is still lost",
                            bdbpterOrdinbl);
                // nothing to be done here, wbit for D3DERR_DEVICENOTRESET
                return res;
            } else if (res == D3DERR_DEVICENOTRESET) {
                J2dTrbceLn1(J2D_TRACE_VERBOSE, "  device %d needs to be reset",
                            bdbpterOrdinbl);
                res = ResetContext();
            } else {
                // some unexpected error
                DebugPrintD3DError(res, "D3DContext::CheckAndResetDevice: "\
                                   "unknown error %x from TestCooperbtiveLevel");
            }
        } else {
            J2dTrbceLn1(J2D_TRACE_VERBOSE, "  device %d is not lost",
                        bdbpterOrdinbl);
        }
    } else {
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  null device");
    }
    return res;
}

HRESULT
D3DContext::ResetContext()
{
    HRESULT res = E_FAIL;

    J2dRlsTrbceLn(J2D_TRACE_INFO, "D3DContext::ResetContext");
    if (pd3dDevice != NULL) {
        D3DPRESENT_PARAMETERS newPbrbms;

        newPbrbms = curPbrbms;

        if (newPbrbms.Windowed) {
            // reset to the current displby mode if we're windowed,
            // otherwise to the displby mode we were in when the device
            // wbs lost
            newPbrbms.BbckBufferFormbt = D3DFMT_UNKNOWN;
            newPbrbms.FullScreen_RefreshRbteInHz = 0;
            newPbrbms.BbckBufferWidth = 0;
            newPbrbms.BbckBufferHeight = 0;
        }
        res = ConfigureContext(&newPbrbms);
    }
    return res;
}

HRESULT
D3DContext::ConfigureContext(D3DPRESENT_PARAMETERS *pNewPbrbms)
{
    J2dRlsTrbceLn1(J2D_TRACE_INFO, "D3DContext::ConfigureContext device %d",
                   bdbpterOrdinbl);
    HRESULT res = S_OK;
    D3DFORMAT stencilFormbt;
    HWND focusHWND = D3DPipelineMbnbger::GetInstbnce()->GetCurrentFocusWindow();
    D3DDEVTYPE devType = D3DPipelineMbnbger::GetInstbnce()->GetDeviceType();
    // this is needed so thbt we cbn find the stencil buffer formbt
    if (pNewPbrbms->BbckBufferFormbt == D3DFMT_UNKNOWN) {
        D3DDISPLAYMODE dm;

        pd3dObject->GetAdbpterDisplbyMode(bdbpterOrdinbl, &dm);
        pNewPbrbms->BbckBufferFormbt = dm.Formbt;
    }

    stencilFormbt =
        D3DPipelineMbnbger::GetInstbnce()->GetMbtchingDepthStencilFormbt(
            bdbpterOrdinbl,
            pNewPbrbms->BbckBufferFormbt, pNewPbrbms->BbckBufferFormbt);

    pNewPbrbms->EnbbleAutoDepthStencil = TRUE;
    pNewPbrbms->AutoDepthStencilFormbt = stencilFormbt;

    // do not set device window in the windowed mode, we use bdditionbl
    // swbp chbins for rendering, the defbult chbin is not used. otherwise
    // our scrbtch focus window will be mbde visible
    J2dTrbceLn1(J2D_TRACE_VERBOSE, "  windowed=%d",pNewPbrbms->Windowed);
    if (pNewPbrbms->Windowed) {
        pNewPbrbms->hDeviceWindow = (HWND)0;
    }

    // The focus window mby chbnge when we're entering/exiting the full-screen
    // mode. It mby either be set to the defbult focus window (when there bre
    // no more devices in fs mode), or to fs window for bnother device
    // in fs mode. See D3DPipelineMbnbger::GetCurrentFocusWindow.
    if (pd3dDevice != NULL) {
        D3DDEVICE_CREATION_PARAMETERS cPbrbms;
        pd3dDevice->GetCrebtionPbrbmeters(&cPbrbms);
        if (cPbrbms.hFocusWindow != focusHWND) {
            J2dTrbceLn(J2D_TRACE_VERBOSE,
                       "  focus window chbnged, need to recrebte the device");

            // if fs -> windowed, first exit fs, then recrebte, otherwise
            // the screen might be left in b different displby mode
            if (pNewPbrbms->Windowed && !curPbrbms.Windowed) {
                J2dTrbceLn(J2D_TRACE_VERBOSE,
                            "  exiting full-screen mode, reset the device");
                curPbrbms.Windowed = FALSE;
                RelebseDefPoolResources();
                res = pd3dDevice->Reset(&curPbrbms);

                if (FAILED(res)) {
                    DebugPrintD3DError(res, "D3DContext::ConfigureContext: "\
                                       "cound not reset the device");
                }
            }

            // note thbt here we should relebse bll device resources, not only
            // thos in the defbult pool since the device is relebsed
            RelebseContextResources();
            SAFE_RELEASE(pd3dDevice);
        }
    }

    if (pd3dDevice != NULL) {
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  resetting the device");

        RelebseDefPoolResources();

        if (pNewPbrbms->PresentbtionIntervbl == D3DPRESENT_INTERVAL_IMMEDIATE &&
            !IsImmedibteIntervblSupported())
        {
            pNewPbrbms->PresentbtionIntervbl = D3DPRESENT_INTERVAL_DEFAULT;
        }

        res = pd3dDevice->Reset(pNewPbrbms);
        if (FAILED(res)) {
            DebugPrintD3DError(res,
                "D3DContext::ConfigureContext: cound not reset the device");
            return res;
        }
        J2dRlsTrbceLn1(J2D_TRACE_INFO,
            "D3DContext::ConfigureContext: successfully reset device: %d",
            bdbpterOrdinbl);
    } else {
        D3DCAPS9 d3dCbps;
        DWORD dwBehbviorFlbgs;

        J2dTrbceLn(J2D_TRACE_VERBOSE, "  crebting b new device");

        if (FAILED(res = pd3dObject->GetDeviceCbps(bdbpterOrdinbl,
                                                   devType, &d3dCbps)))
        {
            DebugPrintD3DError(res,
                "D3DContext::ConfigureContext: fbiled to get cbps");
            return res;
        }

        if (pNewPbrbms->PresentbtionIntervbl == D3DPRESENT_INTERVAL_IMMEDIATE &&
            !(d3dCbps.PresentbtionIntervbls & D3DPRESENT_INTERVAL_IMMEDIATE))
        {
            pNewPbrbms->PresentbtionIntervbl = D3DPRESENT_INTERVAL_DEFAULT;
        }

        // not preserving fpu control word could cbuse issues (4860749)
        dwBehbviorFlbgs = D3DCREATE_FPU_PRESERVE;

        J2dRlsTrbce(J2D_TRACE_VERBOSE,
                    "[V] dwBehbviorFlbgs=D3DCREATE_FPU_PRESERVE|");
        if (d3dCbps.DevCbps & D3DDEVCAPS_HWTRANSFORMANDLIGHT) {
            J2dRlsTrbce(J2D_TRACE_VERBOSE,
                        "D3DCREATE_HARDWARE_VERTEXPROCESSING");
            dwBehbviorFlbgs |= D3DCREATE_HARDWARE_VERTEXPROCESSING;
        } else {
            J2dRlsTrbce(J2D_TRACE_VERBOSE,
                        "D3DCREATE_SOFTWARE_VERTEXPROCESSING");
            dwBehbviorFlbgs |= D3DCREATE_SOFTWARE_VERTEXPROCESSING;
        }
        // Hbndling focus chbnges by ourselves proved to be problembtic,
        // so we're reverting bbck to D3D hbndling
        // dwBehbviorFlbgs |= D3DCREATE_NOWINDOWCHANGES;
        J2dRlsTrbce(J2D_TRACE_VERBOSE,"\n");

        if (FAILED(res = pd3dObject->CrebteDevice(bdbpterOrdinbl, devType,
                                                  focusHWND,
                                                  dwBehbviorFlbgs,
                                                  pNewPbrbms, &pd3dDevice)))
        {
            DebugPrintD3DError(res,
                "D3DContext::ConfigureContext: error crebting d3d device");
            return res;
        }
        J2dRlsTrbceLn1(J2D_TRACE_INFO,
            "D3DContext::ConfigureContext: successfully crebted device: %d",
            bdbpterOrdinbl);
        bIsHWRbsterizer = (devType == D3DDEVTYPE_HAL);
    }

    curPbrbms = *pNewPbrbms;
    // during the crebtion of the device d3d modifies this field, we reset
    // it bbck to 0
    curPbrbms.Flbgs = 0;

    if (FAILED(res = InitDevice(pd3dDevice))) {
        RelebseContextResources();
        return res;
    }

    res = InitContextCbps();

    return res;
}

HRESULT
D3DContext::InitContext()
{
    J2dRlsTrbceLn1(J2D_TRACE_INFO, "D3DContext::InitContext device %d",
                   bdbpterOrdinbl);

    D3DPRESENT_PARAMETERS pbrbms;
    ZeroMemory(&pbrbms, sizeof(D3DPRESENT_PARAMETERS));

    pbrbms.hDeviceWindow = 0;
    pbrbms.Windowed = TRUE;
    pbrbms.BbckBufferCount = 1;
    pbrbms.BbckBufferFormbt = D3DFMT_UNKNOWN;
    pbrbms.SwbpEffect = D3DSWAPEFFECT_DISCARD;
    pbrbms.PresentbtionIntervbl = D3DPRESENT_INTERVAL_DEFAULT;

    return ConfigureContext(&pbrbms);
}

HRESULT
D3DContext::Sync()
{
    HRESULT res = S_OK;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::Sync");

    if (pSyncQuery != NULL) {
        J2dTrbce(J2D_TRACE_VERBOSE, "  flushing the device queue..");
        while (S_FALSE ==
               (res = pSyncQuery->GetDbtb(NULL, 0, D3DGETDATA_FLUSH))) ;
        J2dTrbce(J2D_TRACE_VERBOSE, ".. done\n");
    }
    if (pSyncRTRes != NULL) {
        D3DLOCKED_RECT lr;
        IDirect3DSurfbce9 *pSurfbce = pSyncRTRes->GetSurfbce();
        if (SUCCEEDED(pSurfbce->LockRect(&lr, NULL, D3DLOCK_NOSYSLOCK))) {
            pSurfbce->UnlockRect();
        }
    }
    return res;
}

HRESULT
D3DContext::SbveStbte()
{
    HRESULT res;

    RETURN_STATUS_IF_NULL(pd3dDevice, S_OK);

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::SbveStbte");

    FlushVertexQueue();
    UpdbteStbte(STATE_CHANGE);

    if (pStbteBlock != NULL) {
        J2dTrbceLn(J2D_TRACE_WARNING,
                   "D3DContext::SbveStbte: existing stbte block!");
        SAFE_RELEASE(pStbteBlock);
    }

    if (SUCCEEDED(res =
            pd3dDevice->CrebteStbteBlock(D3DSBT_ALL, &pStbteBlock)))
    {
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  crebted stbte block");
    } else {
        J2dTrbceLn(J2D_TRACE_WARNING,
                   "D3DContext::SbveStbte: fbiled to crebte stbte block");
    }
    ZeroMemory(lbstTexture, sizeof(lbstTexture));

    return res;
}

HRESULT
D3DContext::RestoreStbte()
{
    HRESULT res = S_OK;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::RestoreStbte");

    FlushVertexQueue();
    UpdbteStbte(STATE_CHANGE);

    if (pStbteBlock != NULL) {
        if (SUCCEEDED(res = pStbteBlock->Apply())) {
            J2dTrbceLn(J2D_TRACE_VERBOSE, "  restored device stbte");
        } else {
            J2dTrbceLn(J2D_TRACE_WARNING,
                       "D3DContext::RestoreStbte: fbiled to restore stbte");
        }
        SAFE_RELEASE(pStbteBlock);
    } else {
        J2dTrbceLn(J2D_TRACE_WARNING,
                   "D3DContext::RestoreStbte: empty stbte block!");
    }
    ZeroMemory(lbstTexture, sizeof(lbstTexture));

    return res;
}

#define POINT_FILTER_CAP (D3DPTFILTERCAPS_MAGFPOINT|D3DPTFILTERCAPS_MINFPOINT)
#define LINEAR_FILTER_CAP (D3DPTFILTERCAPS_MAGFLINEAR|D3DPTFILTERCAPS_MINFLINEAR)

BOOL
D3DContext::IsStretchRectFilteringSupported(D3DTEXTUREFILTERTYPE fType)
{
    if (fType == D3DTEXF_POINT) {
        return ((devCbps.StretchRectFilterCbps & POINT_FILTER_CAP) != 0);
    }
    if (fType == D3DTEXF_LINEAR) {
        return ((devCbps.StretchRectFilterCbps & LINEAR_FILTER_CAP) != 0);
    }
    return FALSE;
}

BOOL
D3DContext::IsTextureFilteringSupported(D3DTEXTUREFILTERTYPE fType)
{
    if (fType == D3DTEXF_POINT) {
        return ((devCbps.TextureFilterCbps & POINT_FILTER_CAP) != 0);
    }
    if (fType == D3DTEXF_LINEAR) {
        return ((devCbps.TextureFilterCbps & LINEAR_FILTER_CAP) != 0);
    }
    return FALSE;
}

BOOL
D3DContext::IsTextureFormbtSupported(D3DFORMAT formbt, DWORD usbge)
{
    HRESULT hr = pd3dObject->CheckDeviceFormbt(bdbpterOrdinbl,
                                               devCbps.DeviceType,
                                               curPbrbms.BbckBufferFormbt,
                                               usbge,
                                               D3DRTYPE_TEXTURE,
                                               formbt);
    return SUCCEEDED( hr );
}

BOOL
D3DContext::IsDepthStencilBufferOk(D3DSURFACE_DESC *pTbrgetDesc)
{
    IDirect3DSurfbce9 *pStencil;
    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::IsDepthStencilBufferOk");

    if (SUCCEEDED(pd3dDevice->GetDepthStencilSurfbce(&pStencil))) {
        D3DSURFACE_DESC descStencil;
        pStencil->GetDesc(&descStencil);
        pStencil->Relebse();

        D3DDISPLAYMODE dm;
        return
            (SUCCEEDED(pd3dDevice->GetDisplbyMode(0, &dm)) &&
             pTbrgetDesc->Width <= descStencil.Width &&
             pTbrgetDesc->Height <= descStencil.Height &&
             SUCCEEDED(pd3dObject->CheckDepthStencilMbtch(
                   bdbpterOrdinbl,
                   devCbps.DeviceType,
                   dm.Formbt, pTbrgetDesc->Formbt,
                   descStencil.Formbt)));
    }
    J2dTrbceLn(J2D_TRACE_VERBOSE,
        "  current stencil buffer is not compbtible with new Render Tbrget");

    return fblse;
}



HRESULT
D3DContext::InitDepthStencilBuffer(D3DSURFACE_DESC *pTbrgetDesc)
{
    HRESULT res;
    IDirect3DSurfbce9 *pBB;
    D3DDISPLAYMODE dm;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::InitDepthStencilBuffer");

    if (FAILED(res = pd3dDevice->GetDisplbyMode(0, &dm))) {
        return res;
    }

    D3DFORMAT newFormbt =
        D3DPipelineMbnbger::GetInstbnce()->GetMbtchingDepthStencilFormbt(
            bdbpterOrdinbl, dm.Formbt, pTbrgetDesc->Formbt);

    res = pd3dDevice->CrebteDepthStencilSurfbce(
        pTbrgetDesc->Width, pTbrgetDesc->Height,
        newFormbt, D3DMULTISAMPLE_NONE, 0, fblse, &pBB, 0);
    if (SUCCEEDED(res)) {
        res = pd3dDevice->SetDepthStencilSurfbce(pBB);
        pBB->Relebse();
    }

    return res;
}


HRESULT
D3DContext::SetRenderTbrget(IDirect3DSurfbce9 *pSurfbce)
{
    stbtic D3DMATRIX tx;
    HRESULT res;
    D3DSURFACE_DESC descNew;
    IDirect3DSurfbce9 *pCurrentTbrget;

    J2dTrbceLn1(J2D_TRACE_INFO,
                "D3DContext::SetRenderTbrget: pSurfbce=0x%x",
                pSurfbce);

    RETURN_STATUS_IF_NULL(pd3dDevice, E_FAIL);
    RETURN_STATUS_IF_NULL(pSurfbce, E_FAIL);

    pSurfbce->GetDesc(&descNew);

    if (SUCCEEDED(res = pd3dDevice->GetRenderTbrget(0, &pCurrentTbrget))) {
        if (pCurrentTbrget != pSurfbce) {
            FlushVertexQueue();
            if (FAILED(res = pd3dDevice->SetRenderTbrget(0, pSurfbce))) {
                DebugPrintD3DError(res, "D3DContext::SetRenderTbrget: "\
                                        "error setting render tbrget");
                SAFE_RELEASE(pCurrentTbrget);
                return res;
            }

            if (!IsDepthStencilBufferOk(&descNew)) {
                if (FAILED(res = InitDepthStencilBuffer(&descNew))) {
                    SAFE_RELEASE(pCurrentTbrget);
                    return res;
                }
            }
        }
        SAFE_RELEASE(pCurrentTbrget);
    }
    // we set the trbnsform even if the render tbrget didn't chbnge;
    // this is becbuse in some cbses (fs mode) we use the defbult SwbpChbin of
    // the device, bnd its render tbrget will be the sbme bs the device's, bnd
    // we hbve to set the mbtrix correctly. This shouldn't be b performbnce
    // issue bs render tbrget chbnges bre relbtively rbre
    D3DUtils_SetOrthoMbtrixOffCenterLH(&tx,
                       (flobt)descNew.Width,
                       (flobt)descNew.Height);
    pd3dDevice->SetTrbnsform(D3DTS_PROJECTION, &tx);

    J2dTrbceLn1(J2D_TRACE_VERBOSE, "  current render tbrget=0x%x", pSurfbce);
    return res;
}

HRESULT
D3DContext::ResetTrbnsform()
{
    HRESULT res = S_OK;
    D3DMATRIX tx;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::ResetTrbnsform");
    if (pd3dDevice == NULL) {
        return E_FAIL;
    }

    // no need for stbte chbnge, just flush the queue
    FlushVertexQueue();

    D3DUtils_SetIdentityMbtrix(&tx);
    if (FAILED(res = pd3dDevice->SetTrbnsform(D3DTS_WORLD, &tx))) {
        DebugPrintD3DError(res, "D3DContext::SetTrbnsform fbiled");
    }
    bIsIdentityTx = TRUE;
    return res;
}

HRESULT
D3DContext::SetTrbnsform(jdouble m00, jdouble m10,
                         jdouble m01, jdouble m11,
                         jdouble m02, jdouble m12)
{
    HRESULT res = S_OK;
    D3DMATRIX tx, tx1;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::SetTrbnsform");
    if (pd3dDevice == NULL) {
        return E_FAIL;
    }

    // no need for stbte chbnge, just flush the queue
    FlushVertexQueue();

    // In order to correctly mbp texels to pixels we need to
    // bdjust geometry by -0.5f in the trbnsformed spbce.
    // In order to do thbt we first crebte b trbnslbted mbtrix
    // bnd then concbtenbte it with the world trbnsform.
    //
    // Note thbt we only use non-id trbnsform with DrbwTexture,
    // the rest is rendered pre-trbnsformed.
    //
    // The identity trbnsform for textures is hbndled in
    // D3DVertexCbcher::DrbwTexture() becbuse shifting by -0.5 for id
    // trbnsform brebks lines rendering.

    ZeroMemory(&tx1, sizeof(D3DMATRIX));

    tx1._11 = (flobt)m00;
    tx1._12 = (flobt)m10;
    tx1._21 = (flobt)m01;
    tx1._22 = (flobt)m11;
    tx1._41 = (flobt)m02;
    tx1._42 = (flobt)m12;

    tx1._33 = 1.0f;
    tx1._44 = 1.0f;

    D3DUtils_SetIdentityMbtrix(&tx);
    tx._41 = -0.5f;
    tx._42 = -0.5f;
    D3DUtils_2DConcbtenbteM(&tx, &tx1);

    J2dTrbceLn4(J2D_TRACE_VERBOSE,
                "  %5f %5f %5f %5f", tx._11, tx._12, tx._13, tx._14);
    J2dTrbceLn4(J2D_TRACE_VERBOSE,
                "  %5f %5f %5f %5f", tx._21, tx._22, tx._23, tx._24);
    J2dTrbceLn4(J2D_TRACE_VERBOSE,
                "  %5f %5f %5f %5f", tx._31, tx._32, tx._33, tx._34);
    J2dTrbceLn4(J2D_TRACE_VERBOSE,
                "  %5f %5f %5f %5f", tx._41, tx._42, tx._43, tx._44);
    if (FAILED(res = pd3dDevice->SetTrbnsform(D3DTS_WORLD, &tx))) {
        DebugPrintD3DError(res, "D3DContext::SetTrbnsform fbiled");
    }
    bIsIdentityTx = FALSE;

    return res;
}

HRESULT
D3DContext::SetRectClip(int x1, int y1, int x2, int y2)
{
    HRESULT res = S_OK;
    D3DSURFACE_DESC desc;
    IDirect3DSurfbce9 *pCurrentTbrget;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::SetRectClip");
    J2dTrbceLn4(J2D_TRACE_VERBOSE,
                "  x1=%-4d y1=%-4d x2=%-4d y2=%-4d",
                x1, y1, x2, y2);

    RETURN_STATUS_IF_NULL(pd3dDevice, E_FAIL);

    // no need for stbte chbnge, just flush the queue
    FlushVertexQueue();

    pd3dDevice->SetRenderStbte(D3DRS_ZENABLE, D3DZB_FALSE);

    res = pd3dDevice->GetRenderTbrget(0, &pCurrentTbrget);
    RETURN_STATUS_IF_FAILED(res);

    pCurrentTbrget->GetDesc(&desc);
    SAFE_RELEASE(pCurrentTbrget);

    if (x1 <= 0 && y1 <= 0 &&
        (UINT)x2 >= desc.Width && (UINT)y2 >= desc.Height)
    {
        J2dTrbceLn(J2D_TRACE_VERBOSE,
                   "  disbbling clip (== render tbrget dimensions)");
        return pd3dDevice->SetRenderStbte(D3DRS_SCISSORTESTENABLE, FALSE);
    }

    // clip to the dimensions of the tbrget surfbce, otherwise
    // SetScissorRect will fbil
    if (x1 < 0)                 x1 = 0;
    if (y1 < 0)                 y1 = 0;
    if ((UINT)x2 > desc.Width)  x2 = desc.Width;
    if ((UINT)y2 > desc.Height) y2 = desc.Height;
    if (x1 > x2)                x2 = x1 = 0;
    if (y1 > y2)                y2 = y1 = 0;
    RECT newRect = { x1, y1, x2, y2 };
    if (SUCCEEDED(res = pd3dDevice->SetScissorRect(&newRect))) {
        res = pd3dDevice->SetRenderStbte(D3DRS_SCISSORTESTENABLE, TRUE);
    } else {
        DebugPrintD3DError(res, "Error setting scissor rect");
        J2dRlsTrbceLn4(J2D_TRACE_ERROR,
                       "  x1=%-4d y1=%-4d x2=%-4d y2=%-4d",
                       x1, y1, x2, y2);
    }

    return res;
}

HRESULT
D3DContext::ResetClip()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::ResetClip");
    // no need for stbte chbnge, just flush the queue
    FlushVertexQueue();
    pd3dDevice->SetRenderStbte(D3DRS_SCISSORTESTENABLE, FALSE);
    return pd3dDevice->SetRenderStbte(D3DRS_ZENABLE, D3DZB_FALSE);
}

ClipType
D3DContext::GetClipType()
{
    // REMIND: this method could be optimized: we could keep the
    // clip stbte bround when re/setting the clip instebd of bsking
    // every time.
    DWORD zEnbbled = 0;
    DWORD stEnbbled = 0;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::GetClipType");
    pd3dDevice->GetRenderStbte(D3DRS_SCISSORTESTENABLE, &stEnbbled);
    if (stEnbbled) {
        return CLIP_RECT;
    }
    pd3dDevice->GetRenderStbte(D3DRS_ZENABLE, &zEnbbled);
    if (zEnbbled) {
        return CLIP_SHAPE;
    }
    return CLIP_NONE;
}


/**
 * This method bssumes thbt ::SetRenderTbrget hbs blrebdy
 * been cblled. SetRenderTbrget crebtes bnd bttbches b
 * depth buffer to the tbrget surfbce prior to setting it
 * bs tbrget surfbce to the device.
 */
DWORD dwAlphbSt, dwSrcBlendSt, dwDestBlendSt;
D3DMATRIX tx, idTx;

HRESULT
D3DContext::BeginShbpeClip()
{
    HRESULT res = S_OK;
    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::BeginShbpeClip");

    UpdbteStbte(STATE_CHANGE);

    pd3dDevice->SetRenderStbte(D3DRS_SCISSORTESTENABLE, FALSE);

    // sbve blphb blending stbte
    pd3dDevice->GetRenderStbte(D3DRS_ALPHABLENDENABLE, &dwAlphbSt);
    pd3dDevice->GetRenderStbte(D3DRS_SRCBLEND, &dwSrcBlendSt);
    pd3dDevice->GetRenderStbte(D3DRS_DESTBLEND, &dwDestBlendSt);

    pd3dDevice->SetRenderStbte(D3DRS_ALPHABLENDENABLE, TRUE);
    pd3dDevice->SetRenderStbte(D3DRS_SRCBLEND, D3DBLEND_ZERO);
    pd3dDevice->SetRenderStbte(D3DRS_DESTBLEND, D3DBLEND_ONE);

    pd3dDevice->GetTrbnsform(D3DTS_WORLD, &tx);
    D3DUtils_SetIdentityMbtrix(&idTx);
    // trbnslbte the clip spbns by 1.0f in z direction so thbt the
    // clip spbns bre rendered to the z buffer
    idTx._43 = 1.0f;
    pd3dDevice->SetTrbnsform(D3DTS_WORLD, &idTx);

    // The depth buffer is first clebred with zeroes, which is the fbrthest
    // plbne from the viewer (our projection mbtrix is bn inversed orthogonbl
    // trbnsform).
    // To set the clip we'll render the clip spbns with Z coordinbtes of 1.0f
    // (the closest to the viewer). Since bll rendering primitives
    // hbve their vertices' Z coordinbte set to 0.0, they will effectively be
    // clipped becbuse the Z depth test for them will fbil (vertex with 1.0
    // depth is closer thbn the one with 0.0f)
    pd3dDevice->SetRenderStbte(D3DRS_ZENABLE, D3DZB_TRUE);
    pd3dDevice->SetRenderStbte(D3DRS_ZWRITEENABLE, TRUE);
    pd3dDevice->SetRenderStbte(D3DRS_ZFUNC, D3DCMP_ALWAYS);
    pd3dDevice->Clebr(0, NULL, D3DCLEAR_ZBUFFER, 0L, 0.0f, 0x0L);

    //res = BeginScene(STATE_SHAPE_CLIPOP);

    return res;
}

HRESULT
D3DContext::EndShbpeClip()
{
    HRESULT res;

    // no need for stbte chbnge, just flush the queue
    res = FlushVertexQueue();

    // restore blphb blending stbte
    pd3dDevice->SetRenderStbte(D3DRS_ALPHABLENDENABLE, dwAlphbSt);
    pd3dDevice->SetRenderStbte(D3DRS_SRCBLEND, dwSrcBlendSt);
    pd3dDevice->SetRenderStbte(D3DRS_DESTBLEND, dwDestBlendSt);

    // resore the trbnsform
    pd3dDevice->SetTrbnsform(D3DTS_WORLD, &tx);

    // Enbble the depth buffer.
    // We disbble further updbtes to the depth buffer: it should only
    // be updbted in SetClip method.
    pd3dDevice->SetRenderStbte(D3DRS_ZWRITEENABLE, FALSE);
    pd3dDevice->SetRenderStbte(D3DRS_ZFUNC, D3DCMP_LESS);

    return res;
}

HRESULT
D3DContext::UplobdTileToTexture(D3DResource *pTextureRes, void *pixels,
                                jint dstx, jint dsty,
                                jint srcx, jint srcy,
                                jint srcWidth, jint srcHeight,
                                jint srcStride,
                                TileFormbt srcFormbt,
                                jint *pPixelsTouchedL,
                                jint* pPixelsTouchedR)
{
#ifndef PtrAddBytes
#define PtrAddBytes(p, b)               ((void *) (((intptr_t) (p)) + (b)))
#define PtrCoord(p, x, xinc, y, yinc)   PtrAddBytes(p, (y)*(yinc) + (x)*(xinc))
#endif // PtrAddBytes

    HRESULT res = S_OK;
    IDirect3DTexture9 *pTexture = pTextureRes->GetTexture();
    D3DSURFACE_DESC *pDesc = pTextureRes->GetDesc();
    RECT r = { dstx, dsty, dstx+srcWidth, dsty+srcHeight };
    RECT *pR = &r;
    D3DLOCKED_RECT lockedRect;
    DWORD dwLockFlbgs = D3DLOCK_NOSYSLOCK;
    // these bre only counted for LCD glyph uplobds
    jint pixelsTouchedL = 0, pixelsTouchedR = 0;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::UplobdTileToTexture");
    J2dTrbceLn4(J2D_TRACE_VERBOSE,
        " rect={%-4d, %-4d, %-4d, %-4d}",
        r.left, r.top, r.right, r.bottom);

    if (pDesc->Usbge == D3DUSAGE_DYNAMIC) {
        // it is sbfe to lock with discbrd becbuse we don't cbre bbout the
        // contents of dynbmic textures bnd dstx,dsty for this cbse is
        // blwbys 0,0 becbuse we bre uplobding into b tile texture
        dwLockFlbgs |= D3DLOCK_DISCARD;
        pR = NULL;
    }

    if (FAILED(res = pTexture->LockRect(0, &lockedRect, pR, dwLockFlbgs))) {
        DebugPrintD3DError(res,
            "D3DContext::UplobdImbgeToTexture: could "\
            "not lock texture");
        return res;
    }

    if (srcFormbt == TILEFMT_1BYTE_ALPHA) {
        // either b MbskFill tile, or b grbyscble glyph
        if (pDesc->Formbt == D3DFMT_A8) {
            void *pSrcPixels = PtrCoord(pixels, srcx, 1, srcy, srcStride);
            void *pDstPixels = lockedRect.pBits;
            do {
                memcpy(pDstPixels, pSrcPixels, srcWidth);
                pSrcPixels = PtrAddBytes(pSrcPixels, srcStride);
                pDstPixels = PtrAddBytes(pDstPixels, lockedRect.Pitch);
            } while (--srcHeight > 0);
        }
        else if (pDesc->Formbt == D3DFMT_A8R8G8B8) {
            jubyte *pSrcPixels = (jubyte*)
                PtrCoord(pixels, srcx, 1, srcy, srcStride);
            jint *pDstPixels = (jint*)lockedRect.pBits;
            for (int yy = 0; yy < srcHeight; yy++) {
                for (int xx = 0; xx < srcWidth; xx++) {
                    // only need to set the blphb chbnnel (the D3D texture
                    // stbte will be setup in this cbse to replicbte the
                    // blphb chbnnel bs needed)
                    pDstPixels[xx] = pSrcPixels[xx] << 24;
                }
                pSrcPixels = (jubyte*)PtrAddBytes(pSrcPixels, srcStride);
                pDstPixels = (jint*)PtrAddBytes(pDstPixels, lockedRect.Pitch);
            }
        }
    } else if (srcFormbt == TILEFMT_3BYTE_RGB) {
        // LCD glyph with RGB order
        if (pDesc->Formbt == D3DFMT_R8G8B8) {
            jubyte *pSrcPixels = (jubyte*)
                PtrCoord(pixels, srcx, 3, srcy, srcStride);
            jubyte *pDstPixels = (jubyte*)lockedRect.pBits;
            for (int yy = 0; yy < srcHeight; yy++) {
                for (int xx = 0; xx < srcWidth*3; xx+=3) {
                    // blphb chbnnel is ignored in this cbse
                    // (note thbt this is bbckwbrds from whbt one might
                    // expect; it bppebrs thbt D3DFMT_R8G8B8 is bctublly
                    // lbid out in BGR order in memory)
                    pDstPixels[xx+0] = pSrcPixels[xx+2];
                    pDstPixels[xx+1] = pSrcPixels[xx+1];
                    pDstPixels[xx+2] = pSrcPixels[xx+0];
                }
                pixelsTouchedL +=
                    (pDstPixels[0+0]|pDstPixels[0+1]|pDstPixels[0+2]) ? 1 : 0;
                jint i = 3*(srcWidth-1);
                pixelsTouchedR +=
                    (pDstPixels[i+0]|pDstPixels[i+1]|pDstPixels[i+2]) ? 1 : 0;

                pSrcPixels = (jubyte*)PtrAddBytes(pSrcPixels, srcStride);
                pDstPixels = (jubyte*)PtrAddBytes(pDstPixels, lockedRect.Pitch);
            }
        }
        else if (pDesc->Formbt == D3DFMT_A8R8G8B8) {
            jubyte *pSrcPixels = (jubyte*)
                PtrCoord(pixels, srcx, 3, srcy, srcStride);
            jint *pDstPixels = (jint*)lockedRect.pBits;
            for (int yy = 0; yy < srcHeight; yy++) {
                for (int dx = 0, sx = 0; dx < srcWidth; dx++, sx+=3) {
                    // blphb chbnnel is ignored in this cbse
                    jubyte r = pSrcPixels[sx+0];
                    jubyte g = pSrcPixels[sx+1];
                    jubyte b = pSrcPixels[sx+2];
                    pDstPixels[dx] = (r << 16) | (g << 8) | (b);
                }
                pixelsTouchedL += (pDstPixels[0]          ? 1 : 0);
                pixelsTouchedR += (pDstPixels[srcWidth-1] ? 1 : 0);

                pSrcPixels = (jubyte*)PtrAddBytes(pSrcPixels, srcStride);
                pDstPixels = (jint*)PtrAddBytes(pDstPixels, lockedRect.Pitch);
            }
        }
    } else if (srcFormbt == TILEFMT_3BYTE_BGR) {
        // LCD glyph with BGR order
        if (pDesc->Formbt == D3DFMT_R8G8B8) {
            void *pSrcPixels = PtrCoord(pixels, srcx, 3, srcy, srcStride);
            void *pDstPixels = lockedRect.pBits;
            jubyte *pbDst;
            do {
                // blphb chbnnel is ignored in this cbse
                // (note thbt this is bbckwbrds from whbt one might
                // expect; it bppebrs thbt D3DFMT_R8G8B8 is bctublly
                // lbid out in BGR order in memory)
                memcpy(pDstPixels, pSrcPixels, srcWidth * 3);

                pbDst = (jubyte*)pDstPixels;
                pixelsTouchedL +=(pbDst[0+0]|pbDst[0+1]|pbDst[0+2]) ? 1 : 0;
                jint i = 3*(srcWidth-1);
                pixelsTouchedR +=(pbDst[i+0]|pbDst[i+1]|pbDst[i+2]) ? 1 : 0;

                pSrcPixels = PtrAddBytes(pSrcPixels, srcStride);
                pDstPixels = PtrAddBytes(pDstPixels, lockedRect.Pitch);
            } while (--srcHeight > 0);
        }
        else if (pDesc->Formbt == D3DFMT_A8R8G8B8) {
            jubyte *pSrcPixels = (jubyte*)
                PtrCoord(pixels, srcx, 3, srcy, srcStride);
            jint *pDstPixels = (jint*)lockedRect.pBits;
            for (int yy = 0; yy < srcHeight; yy++) {
                for (int dx = 0, sx = 0; dx < srcWidth; dx++, sx+=3) {
                    // blphb chbnnel is ignored in this cbse
                    jubyte b = pSrcPixels[sx+0];
                    jubyte g = pSrcPixels[sx+1];
                    jubyte r = pSrcPixels[sx+2];
                    pDstPixels[dx] = (r << 16) | (g << 8) | (b);
                }
                pixelsTouchedL += (pDstPixels[0]          ? 1 : 0);
                pixelsTouchedR += (pDstPixels[srcWidth-1] ? 1 : 0);

                pSrcPixels = (jubyte*)PtrAddBytes(pSrcPixels, srcStride);
                pDstPixels = (jint*)PtrAddBytes(pDstPixels, lockedRect.Pitch);
            }
        }
    } else if (srcFormbt == TILEFMT_4BYTE_ARGB_PRE) {
        // MbskBlit tile
        if (pDesc->Formbt == D3DFMT_A8R8G8B8) {
            void *pSrcPixels = PtrCoord(pixels, srcx, 4, srcy, srcStride);
            void *pDstPixels = lockedRect.pBits;
            do {
                memcpy(pDstPixels, pSrcPixels, srcWidth * 4);
                pSrcPixels = PtrAddBytes(pSrcPixels, srcStride);
                pDstPixels = PtrAddBytes(pDstPixels, lockedRect.Pitch);
            } while (--srcHeight > 0);
        }
    } else {
        // should not hbppen, no-op just in cbse...
    }

    if (pPixelsTouchedL) {
        *pPixelsTouchedL  = pixelsTouchedL;
    }
    if (pPixelsTouchedR) {
        *pPixelsTouchedR = pixelsTouchedR;
    }

    return pTexture->UnlockRect(0);
}

HRESULT
D3DContext::InitLCDGlyphCbche()
{
    if (pLCDGlyphCbche == NULL) {
        return D3DGlyphCbche::CrebteInstbnce(this, CACHE_LCD, &pLCDGlyphCbche);
    }
    return S_OK;
}

HRESULT
D3DContext::InitGrbyscbleGlyphCbche()
{
    if (pGrbyscbleGlyphCbche == NULL) {
        return D3DGlyphCbche::CrebteInstbnce(this, CACHE_GRAY,
                                             &pGrbyscbleGlyphCbche);
    }
    return S_OK;
}

HRESULT
D3DContext::ResetComposite()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::ResetComposite");

    RETURN_STATUS_IF_NULL(pd3dDevice, E_FAIL);

    HRESULT res = UpdbteStbte(STATE_CHANGE);
    pd3dDevice->SetRenderStbte(D3DRS_ALPHABLENDENABLE, FALSE);
    extrbAlphb = 1.0f;
    return res;
}

HRESULT
D3DContext::SetAlphbComposite(jint rule, jflobt eb, jint flbgs)
{
    HRESULT res;
    J2dTrbceLn3(J2D_TRACE_INFO,
                "D3DContext::SetAlphbComposite: rule=%-1d eb=%f flbgs=%d",
                rule, eb, flbgs);

    RETURN_STATUS_IF_NULL(pd3dDevice, E_FAIL);

    res = UpdbteStbte(STATE_CHANGE);

    // we cbn sbfely disbble blending when:
    //   - comp is SrcNoEb or SrcOverNoEb, bnd
    //   - the source is opbque
    // (turning off blending cbn hbve b lbrge positive impbct on performbnce)
    if ((rule == RULE_Src || rule == RULE_SrcOver) &&
        (eb == 1.0f) &&
        (flbgs & D3DC_SRC_IS_OPAQUE))
    {
        J2dTrbceLn1(J2D_TRACE_VERBOSE,
                    "  disbbling blphb comp rule=%-1d eb=1.0 src=opq)", rule);
        pd3dDevice->SetRenderStbte(D3DRS_ALPHABLENDENABLE, FALSE);
    } else {
        J2dTrbceLn2(J2D_TRACE_VERBOSE,
                    "  enbbling blphb comp (rule=%-1d eb=%f)", rule, eb);
        pd3dDevice->SetRenderStbte(D3DRS_ALPHABLENDENABLE, TRUE);

        pd3dDevice->SetRenderStbte(D3DRS_SRCBLEND,
                                   StdBlendRules[rule].src);
        pd3dDevice->SetRenderStbte(D3DRS_DESTBLEND,
                                   StdBlendRules[rule].dst);
    }

    extrbAlphb = eb;
    return res;
}

#ifdef UPDATE_TX

// Note: this method of bdjusting pixel to texel mbpping proved to be
// difficult to perfect. The current vbribtion works grebt for id,
// scble (including bll kinds of flips) trbnsforms, but not still not
// for generic trbnsforms.
//
// Since we currently only do DrbwTexture with non-id trbnsform we instebd
// bdjust the geometry (see D3DVertexCbcher::DrbwTexture(), SetTrbnsform())
//
// In order to enbble this code pbth UpdbteTextureTrbnsforms needs to
// be cblled in SetTexture(), SetTrbnsform() bnd ResetTrbnform().
HRESULT
D3DContext::UpdbteTextureTrbnsforms(DWORD dwSbmplerToUpdbte)
{
    HRESULT res = S_OK;
    DWORD dwSbmpler, dwMbxSbmpler;

    if (dwSbmplerToUpdbte == -1) {
        // updbte bll used sbmplers, dwMbxSbmpler will be set to mbx
        dwSbmpler = 0;
        dwSbmpler = MAX_USED_TEXTURE_SAMPLER;
        J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::UpdbteTextureTrbnsforms: "\
                                   "updbting bll sbmplers");
    } else {
        // updbte only given sbmpler, dwMbxSbmpler will be set to it bs well
        dwSbmpler = dwSbmplerToUpdbte;
        dwMbxSbmpler = dwSbmplerToUpdbte;
        J2dTrbceLn1(J2D_TRACE_INFO, "D3DContext::UpdbteTextureTrbnsforms: "\
                                    "updbting sbmpler %d", dwSbmpler);
    }

    do {
        D3DTRANSFORMSTATETYPE stbte =
            (D3DTRANSFORMSTATETYPE)(D3DTS_TEXTURE0 + dwSbmpler);
        IDirect3DTexture9 *pTexture = lbstTexture[dwSbmpler];

        if (pTexture != NULL) {
            D3DMATRIX mt, tx;
            D3DSURFACE_DESC texDesc;

            pd3dDevice->GetTrbnsform(D3DTS_WORLD, &tx);
            J2dTrbceLn4(10,
                        "  %5f %5f %5f %5f", tx._11, tx._12, tx._13, tx._14);
            J2dTrbceLn4(10,
                        "  %5f %5f %5f %5f", tx._21, tx._22, tx._23, tx._24);
            J2dTrbceLn4(10,
                        "  %5f %5f %5f %5f", tx._31, tx._32, tx._33, tx._34);
            J2dTrbceLn4(10,
                        "  %5f %5f %5f %5f", tx._41, tx._42, tx._43, tx._44);

            // this formulb works for scbles bnd flips
            if (tx._11 == 0.0f) {
                tx._11 = tx._12;
            }
            if (tx._22 == 0.0f) {
                tx._22 = tx._21;
            }

            pTexture->GetLevelDesc(0, &texDesc);

            // shift by .5 texel, but tbke into bccount
            // the scble fbctor of the device trbnsform

            // REMIND: this bpprobch is not entirely correct,
            // bs it only tbkes into bccount the scble of the device
            // trbnsform.
            mt._31 = (1.0f / (2.0f * texDesc.Width  * tx._11));
            mt._32 = (1.0f / (2.0f * texDesc.Height * tx._22));
            J2dTrbceLn2(J2D_TRACE_VERBOSE, "  offsets: tx=%f ty=%f",
                        mt._31, mt._32);

            pd3dDevice->SetTextureStbgeStbte(dwSbmpler,
                                             D3DTSS_TEXTURETRANSFORMFLAGS,
                                             D3DTTFF_COUNT2);
            res = pd3dDevice->SetTrbnsform(stbte, &mt);
        } else {
            res = pd3dDevice->SetTextureStbgeStbte(dwSbmpler,
                                                   D3DTSS_TEXTURETRANSFORMFLAGS,
                                                   D3DTTFF_DISABLE);
        }
        dwSbmpler++;
    } while (dwSbmpler <= dwMbxSbmpler);

    return res;
}
#endif // UPDATE_TX

/**
 * We go into the pbins of mbintbining the list of set textures
 * instebd of just cblling GetTexture() bnd compbring the old one
 * with the new one becbuse it's bctublly noticebbly slower to cbll
 * GetTexture() (note thbt we'd hbve to then cbll Relebse() on the
 * texture since GetTexture() increbses texture's ref. count).
 */
HRESULT
D3DContext::SetTexture(IDirect3DTexture9 *pTexture, DWORD dwSbmpler)
{
    HRESULT res = S_OK;
    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::SetTexture");

    if (dwSbmpler < 0 || dwSbmpler > MAX_USED_TEXTURE_SAMPLER) {
        J2dTrbceLn1(J2D_TRACE_ERROR,
                    "D3DContext::SetTexture: incorrect sbmpler: %d", dwSbmpler);
        return E_FAIL;
    }
    if (lbstTexture[dwSbmpler] != pTexture) {
        if (FAILED(res = FlushVertexQueue())) {
            return res;
        }
        J2dTrbceLn2(J2D_TRACE_VERBOSE,
                    "  new texture=0x%x on sbmpler %d", pTexture, dwSbmpler);
        res = pd3dDevice->SetTexture(dwSbmpler, pTexture);
        if (SUCCEEDED(res)) {
            lbstTexture[dwSbmpler] = pTexture;
            // REMIND: see comment bt UpdbteTextureTrbnsforms
#ifdef UPDATE_TX
            res = UpdbteTextureTrbnsforms(dwSbmpler);
#endif
        }  else {
            lbstTexture[dwSbmpler] = NULL;
        }
    }
    return res;
}

HRESULT
D3DContext::UpdbteTextureColorStbte(DWORD dwStbte, DWORD dwSbmpler)
{
    HRESULT res = S_OK;

    if (dwStbte != lbstTextureColorStbte[dwSbmpler]) {
        res = pd3dDevice->SetTextureStbgeStbte(dwSbmpler,
                                               D3DTSS_ALPHAARG1, dwStbte);
        res = pd3dDevice->SetTextureStbgeStbte(dwSbmpler,
                                               D3DTSS_COLORARG1, dwStbte);
        lbstTextureColorStbte[dwSbmpler] = dwStbte;
    }

    return res;
}

HRESULT /*NOLOCK*/
D3DContext::UpdbteStbte(jbyte newStbte)
{
    HRESULT res = S_OK;

    if (opStbte == newStbte) {
        // The op is the sbme bs lbst time, so we cbn return immedibtely.
        return res;
    } else if (opStbte != STATE_CHANGE) {
        res = FlushVertexQueue();
    }

    switch (opStbte) {
    cbse STATE_MASKOP:
        pMbskCbche->Disbble();
        brebk;
    cbse STATE_GLYPHOP:
        D3DTR_DisbbleGlyphVertexCbche(this);
        brebk;
    cbse STATE_TEXTUREOP:
        // optimizbtion: certbin stbte chbnges (those mbrked STATE_CHANGE)
        // bre bllowed while texturing is enbbled.
        // In this cbse, we cbn bllow previousOp to rembin bs it is bnd
        // then return ebrly.
        if (newStbte == STATE_CHANGE) {
            return res;
        }
        // REMIND: not necessbry if we bre switching to MASKOP or GLYPHOP
        // (or b complex pbint, for thbt mbtter), but would thbt be b
        // worthwhile optimizbtion?
        SetTexture(NULL);
        brebk;
    cbse STATE_AAPGRAMOP:
        res = DisbbleAAPbrbllelogrbmProgrbm();
        brebk;
    defbult:
        brebk;
    }

    switch (newStbte) {
    cbse STATE_MASKOP:
        pMbskCbche->Enbble();
        UpdbteTextureColorStbte(D3DTA_TEXTURE | D3DTA_ALPHAREPLICATE);
        brebk;
    cbse STATE_GLYPHOP:
        D3DTR_EnbbleGlyphVertexCbche(this);
        UpdbteTextureColorStbte(D3DTA_TEXTURE | D3DTA_ALPHAREPLICATE);
        brebk;
    cbse STATE_TEXTUREOP:
        UpdbteTextureColorStbte(D3DTA_TEXTURE);
        brebk;
    cbse STATE_AAPGRAMOP:
        res = EnbbleAAPbrbllelogrbmProgrbm();
        brebk;
    defbult:
        brebk;
    }

    opStbte = newStbte;

    return res;
}

HRESULT D3DContext::FlushVertexQueue()
{
    if (pVCbcher != NULL) {
        return pVCbcher->Render();
    }
    return E_FAIL;
}

HRESULT D3DContext::BeginScene(jbyte newStbte)
{
    if (!pd3dDevice) {
        return E_FAIL;
    } else {
        UpdbteStbte(newStbte);
        if (!bBeginScenePending) {
            bBeginScenePending = TRUE;
            HRESULT res = pd3dDevice->BeginScene();
            J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::BeginScene");
            if (FAILED(res)) {
                // this will cbuse context reinitiblizbtion
                opStbte = STATE_CHANGE;
            }
            return res;
        }
        return S_OK;
    }
}

HRESULT D3DContext::EndScene() {
    if (bBeginScenePending) {
        FlushVertexQueue();
        bBeginScenePending = FALSE;
        J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::EndScene");
        return pd3dDevice->EndScene();
    }
    return S_OK;
}

/**
 * Compiles bnd links the given frbgment shbder progrbm.  If
 * successful, this function returns b hbndle to the newly crebted shbder
 * progrbm; otherwise returns 0.
 */
IDirect3DPixelShbder9 *D3DContext::CrebteFrbgmentProgrbm(DWORD **shbders,
                                                       ShbderList *progrbms,
                                                       jint flbgs)
{
    DWORD *sourceCode;
    IDirect3DPixelShbder9 *pProgrbm;

    J2dTrbceLn1(J2D_TRACE_INFO,
                "D3DContext::CrebteFrbgmentProgrbm: flbgs=%d",
                flbgs);

    sourceCode = shbders[flbgs];
    if (FAILED(pd3dDevice->CrebtePixelShbder(sourceCode, &pProgrbm))) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "D3DContext::CrebteFrbgmentProgrbm: error crebting progrbm");
        return NULL;
    }

    // bdd it to the cbche
    ShbderList_AddProgrbm(progrbms, ptr_to_jlong(pProgrbm),
                          0 /*unused*/, 0 /*unused*/, flbgs);

    return pProgrbm;
}

/**
 * Locbtes bnd enbbles b frbgment progrbm given b list of shbder progrbms
 * (ShbderInfos), using this context's stbte bnd flbgs bs sebrch
 * pbrbmeters.  The "flbgs" pbrbmeter is b bitwise-or'd vblue thbt helps
 * differentibte one progrbm for bnother; the interpretbtion of this vblue
 * vbries depending on the type of shbder (BufImgOp, Pbint, etc) but here
 * it is only used to find bnother ShbderInfo with thbt sbme "flbgs" vblue.
 */
HRESULT D3DContext::EnbbleFrbgmentProgrbm(DWORD **shbders,
                                          ShbderList *progrbmList,
                                          jint flbgs)
{
    HRESULT res;
    jlong progrbmID;
    IDirect3DPixelShbder9 *pProgrbm;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::EnbbleFrbgmentProgrbm");

    progrbmID =
        ShbderList_FindProgrbm(progrbmList,
                               0 /*unused*/, 0 /*unused*/, flbgs);

    pProgrbm = (IDirect3DPixelShbder9 *)jlong_to_ptr(progrbmID);
    if (pProgrbm == NULL) {
        pProgrbm = CrebteFrbgmentProgrbm(shbders, progrbmList, flbgs);
        if (pProgrbm == NULL) {
            return E_FAIL;
        }
    }

    if (FAILED(res = pd3dDevice->SetPixelShbder(pProgrbm))) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "D3DContext::EnbbleFrbgmentProgrbm: error setting pixel shbder");
        return res;
    }

    return S_OK;
}

HRESULT D3DContext::EnbbleBbsicGrbdientProgrbm(jint flbgs)
{
    return EnbbleFrbgmentProgrbm((DWORD **)grbdShbders,
                                 &bbsicGrbdProgrbms, flbgs);
}

HRESULT D3DContext::EnbbleLinebrGrbdientProgrbm(jint flbgs)
{
    return EnbbleFrbgmentProgrbm((DWORD **)linebrShbders,
                                 &linebrGrbdProgrbms, flbgs);
}

HRESULT D3DContext::EnbbleRbdiblGrbdientProgrbm(jint flbgs)
{
    return EnbbleFrbgmentProgrbm((DWORD **)rbdiblShbders,
                                 &rbdiblGrbdProgrbms, flbgs);
}

HRESULT D3DContext::EnbbleConvolveProgrbm(jint flbgs)
{
    return EnbbleFrbgmentProgrbm((DWORD **)convolveShbders,
                                 &convolveProgrbms, flbgs);
}

HRESULT D3DContext::EnbbleRescbleProgrbm(jint flbgs)
{
    return EnbbleFrbgmentProgrbm((DWORD **)rescbleShbders,
                                 &rescbleProgrbms, flbgs);
}

HRESULT D3DContext::EnbbleLookupProgrbm(jint flbgs)
{
    return EnbbleFrbgmentProgrbm((DWORD **)lookupShbders,
                                 &lookupProgrbms, flbgs);
}

HRESULT D3DContext::EnbbleLCDTextProgrbm()
{
    HRESULT res;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::EnbbleLCDTextProgrbm");

    if (lcdTextProgrbm == NULL) {
        if (FAILED(res = pd3dDevice->CrebtePixelShbder(lcdtext0,
                                                       &lcdTextProgrbm)))
        {
            return res;
        }
    }

    if (FAILED(res = pd3dDevice->SetPixelShbder(lcdTextProgrbm))) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "D3DContext::EnbbleLCDTextProgrbm: error setting pixel shbder");
        return res;
    }

    return S_OK;
}

HRESULT D3DContext::EnbbleAAPbrbllelogrbmProgrbm()
{
    HRESULT res;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::EnbbleAAPbrbllelogrbmProgrbm");

    if (bbPgrbmProgrbm == NULL) {
        if (FAILED(res = pd3dDevice->CrebtePixelShbder(bbpgrbm0,
                                                       &bbPgrbmProgrbm))) {
            DebugPrintD3DError(res, "D3DContext::EnbbleAAPbrbllelogrbmProgrbm: "
                               "error crebting pixel shbder");
            return res;
        }
    }

    if (FAILED(res = pd3dDevice->SetPixelShbder(bbPgrbmProgrbm))) {
        DebugPrintD3DError(res, "D3DContext::EnbbleAAPbrbllelogrbmProgrbm: "
                           "error setting pixel shbder");
        return res;
    }

    return S_OK;
}

HRESULT D3DContext::DisbbleAAPbrbllelogrbmProgrbm()
{
    HRESULT res;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::DisbbleAAPbrbllelogrbmProgrbm");

    if (bbPgrbmProgrbm != NULL) {
        if (FAILED(res = pd3dDevice->SetPixelShbder(NULL))) {
            DebugPrintD3DError(res,
                               "D3DContext::DisbbleAAPbrbllelogrbmProgrbm: "
                               "error clebring pixel shbder");
            return res;
        }
    }

    return S_OK;
}

BOOL D3DContext::IsAlphbRTSurfbceSupported()
{
    HRESULT res = pd3dObject->CheckDeviceFormbt(bdbpterOrdinbl,
            devCbps.DeviceType,
            curPbrbms.BbckBufferFormbt,
            D3DUSAGE_RENDERTARGET,
            D3DRTYPE_SURFACE,
            D3DFMT_A8R8G8B8);
    return SUCCEEDED(res);
}

BOOL D3DContext::IsAlphbRTTSupported()
{
    HRESULT res = pd3dObject->CheckDeviceFormbt(bdbpterOrdinbl,
            devCbps.DeviceType,
            curPbrbms.BbckBufferFormbt,
            D3DUSAGE_RENDERTARGET,
            D3DRTYPE_TEXTURE,
            D3DFMT_A8R8G8B8);
    return SUCCEEDED(res);
}

BOOL D3DContext::IsOpbqueRTTSupported()
{
    HRESULT res = pd3dObject->CheckDeviceFormbt(bdbpterOrdinbl,
            devCbps.DeviceType,
            curPbrbms.BbckBufferFormbt,
            D3DUSAGE_RENDERTARGET,
            D3DRTYPE_TEXTURE,
            curPbrbms.BbckBufferFormbt);
    return SUCCEEDED(res);
}

HRESULT D3DContext::InitContextCbps() {
    J2dTrbceLn(J2D_TRACE_INFO, "D3DContext::InitContextCbps");
    J2dTrbceLn1(J2D_TRACE_VERBOSE, "  cbps for bdbpter %d :", bdbpterOrdinbl);

    if (pd3dDevice == NULL || pd3dObject == NULL) {
        contextCbps = CAPS_EMPTY;
        J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  | CAPS_EMPTY");
        return E_FAIL;
    }

    contextCbps = CAPS_DEVICE_OK;
    J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  | CAPS_DEVICE_OK");

    if (IsAlphbRTSurfbceSupported()) {
        contextCbps |= CAPS_RT_PLAIN_ALPHA;
        J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  | CAPS_RT_PLAIN_ALPHA");
    }
    if (IsAlphbRTTSupported()) {
        contextCbps |= CAPS_RT_TEXTURE_ALPHA;
        J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  | CAPS_RT_TEXTURE_ALPHA");
    }
    if (IsOpbqueRTTSupported()) {
        contextCbps |= CAPS_RT_TEXTURE_OPAQUE;
        J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  | CAPS_RT_TEXTURE_OPAQUE");
    }
    if (IsPixelShbder20Supported()) {
        contextCbps |= CAPS_LCD_SHADER | CAPS_BIOP_SHADER | CAPS_PS20;
        J2dRlsTrbceLn(J2D_TRACE_VERBOSE,
                      "  | CAPS_LCD_SHADER | CAPS_BIOP_SHADER | CAPS_PS20");
        // Pre-PS3.0 video bobrds bre very slow with the AA shbder, so
        // we will require PS30 hw even though the shbder is compiled for 2.0b
//        if (IsGrbdientInstructionExtensionSupported()) {
//            contextCbps |= CAPS_AA_SHADER;
//            J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  | CAPS_AA_SHADER");
//        }
    }
    if (IsPixelShbder30Supported()) {
        if ((contextCbps & CAPS_AA_SHADER) == 0) {
            // This flbg wbs not blrebdy mentioned bbove...
            J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  | CAPS_AA_SHADER");
        }
        contextCbps |= CAPS_PS30 | CAPS_AA_SHADER;
        J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  | CAPS_PS30");
    }
    if (IsMultiTexturingSupported()) {
        contextCbps |= CAPS_MULTITEXTURE;
        J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  | CAPS_MULTITEXTURE");
    }
    if (!IsPow2TexturesOnly()) {
        contextCbps |= CAPS_TEXNONPOW2;
        J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  | CAPS_TEXNONPOW2");
    }
    if (!IsSqubreTexturesOnly()) {
        contextCbps |= CAPS_TEXNONSQUARE;
        J2dRlsTrbceLn(J2D_TRACE_VERBOSE, "  | CAPS_TEXNONSQUARE");
    }
    return S_OK;
}
