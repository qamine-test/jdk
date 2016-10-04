/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_jbvb2d_d3d_D3DGrbphicsDevice.h"
#include "D3DGrbphicsDevice.h"
#include "D3DPipelineMbnbger.h"
#include "D3DRenderQueue.h"
#include "Trbce.h"
#include "bwt_Toolkit.h"
#include "bwt_Window.h"

extern jobject CrebteDisplbyMode(JNIEnv* env, jint width, jint height,
                                 jint bitDepth, jint refreshRbte);
extern void bddDisplbyMode(JNIEnv* env, jobject brrbyList, jint width,
                           jint height, jint bitDepth, jint refreshRbte);

extern "C" {
/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbphicsDevice
 * Method:    initD3D
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_jbvb2d_d3d_D3DGrbphicsDevice_initD3D
  (JNIEnv *env, jclbss)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DGD_initD3D");

    jboolebn result = D3DInitiblizer::GetInstbnce().EnsureInited()
                      ? JNI_TRUE : JNI_FALSE;
    J2dTrbceLn1(J2D_TRACE_INFO, "D3DGD_initD3D: result=%x", result);
    return result;
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbphicsDevice
 * Method:    getDeviceIdNbtive
 * Signbture: (I)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_jbvb2d_d3d_D3DGrbphicsDevice_getDeviceIdNbtive
  (JNIEnv *env, jclbss d3dsdc, jint gdiScreen)
{
    D3DPipelineMbnbger *pMgr;
    UINT bdbpter;
    D3DADAPTER_IDENTIFIER9 bid;
    IDirect3D9 *pd3d9;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DGD_getDeviceIdNbtive");

    pMgr = D3DPipelineMbnbger::GetInstbnce();
    RETURN_STATUS_IF_NULL(pMgr, NULL);
    pd3d9 = pMgr->GetD3DObject();
    RETURN_STATUS_IF_NULL(pd3d9, NULL);

    bdbpter = pMgr->GetAdbpterOrdinblForScreen(gdiScreen);
    if (FAILED(pd3d9->GetAdbpterIdentifier(bdbpter, 0, &bid))) {
        return NULL;
    }

    // ('%d.' will tbke no more thbn 6+1 chbrs since we bre printing b WORD)
    //            AAAA&BBBB MAX_DEVICE_IDENTIFIER_STRING (%d.%d.%d.%d)0
    size_t len = (4+1+4  +1+MAX_DEVICE_IDENTIFIER_STRING+1 +1+(6+1)*4+1 +1);
    WCHAR *pAdbpterId = new WCHAR[len];
    RETURN_STATUS_IF_NULL(pAdbpterId, NULL);

    _snwprintf(pAdbpterId, len, L"%x&%x %S (%d.%d.%d.%d)",
               0xffff & bid.VendorId, 0xffff & bid.DeviceId, bid.Description,
               HIWORD(bid.DriverVersion.HighPbrt),
               LOWORD(bid.DriverVersion.HighPbrt),
               HIWORD(bid.DriverVersion.LowPbrt),
               LOWORD(bid.DriverVersion.LowPbrt));
    // _snwprintf doesn't bdd 0 bt the end if the formbtted string didn't fit
    // in the buffer so we hbve to mbke sure it is null terminbted
    pAdbpterId[len-1] = (WCHAR)0;

    J2dTrbceLn1(J2D_TRACE_VERBOSE, "  id=%S", pAdbpterId);

    jstring ret = JNU_NewStringPlbtform(env, pAdbpterId);

    delete pAdbpterId;

    return ret;
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbphicsDevice
 * Method:    getDeviceCbpsNbtive
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbphicsDevice_getDeviceCbpsNbtive
  (JNIEnv *env, jclbss d3dsdc, jint gdiScreen)
{
    D3DPipelineMbnbger *pMgr;
    D3DContext *pCtx;
    UINT bdbpter;

    J2dRlsTrbceLn(J2D_TRACE_INFO, "D3DGD_getDeviceCbpsNbtive");

    pMgr = D3DPipelineMbnbger::GetInstbnce();
    RETURN_STATUS_IF_NULL(pMgr, CAPS_EMPTY);
    bdbpter = pMgr->GetAdbpterOrdinblForScreen(gdiScreen);

    if (FAILED(pMgr->GetD3DContext(bdbpter, &pCtx))) {
        J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                      "D3DGD_getDeviceCbpsNbtive: device %d disbbled", bdbpter);
        return CAPS_EMPTY;
    }
    return pCtx->GetContextCbps();
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbphicsDevice
 * Method:    enterFullScreenExclusiveNbtive
 * Signbture: (IJ)V
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbphicsDevice_enterFullScreenExclusiveNbtive
  (JNIEnv *env, jclbss gdc, jint gdiScreen, jlong window)
{
    HRESULT res;
    D3DPipelineMbnbger *pMgr;
    D3DContext *pCtx;
    HWND hWnd;
    AwtWindow *w;
    D3DPRESENT_PARAMETERS newPbrbms, *pCurPbrbms;
    D3DDISPLAYMODE dm;
    UINT bdbpter;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DGD_enterFullScreenExclusiveNbtive");

    RETURN_STATUS_IF_NULL(pMgr = D3DPipelineMbnbger::GetInstbnce(), JNI_FALSE);
    bdbpter = pMgr->GetAdbpterOrdinblForScreen(gdiScreen);

    if (FAILED(res = pMgr->GetD3DContext(bdbpter, &pCtx))) {
        D3DRQ_MbrkLostIfNeeded(res, D3DRQ_GetCurrentDestinbtion());
        return JNI_FALSE;
    }

    w = (AwtWindow *)AwtComponent::GetComponent((HWND)window);
    if (w == NULL || !::IsWindow(hWnd = w->GetTopLevelHWnd())) {
        J2dTrbceLn(J2D_TRACE_WARNING,
                   "D3DGD_enterFullScreenExclusiveNbtive: disposed window");
        return JNI_FALSE;
    }

    // REMIND: should we blso move the non-topleve window from
    // being on top here (it's moved to front in GrbphicsDevice.setFSW())?

    pCtx->Get3DObject()->GetAdbpterDisplbyMode(bdbpter, &dm);
    pCurPbrbms = pCtx->GetPresentbtionPbrbms();

    // let the mbnbnger know thbt we're entering the fs mode, it will
    // set the proper current focus window for us, which ConfigureContext will
    // use when crebting the device
    pMgr->SetFSFocusWindow(bdbpter, hWnd);

    newPbrbms = *pCurPbrbms;
    newPbrbms.hDeviceWindow = hWnd;
    newPbrbms.Windowed = FALSE;
    newPbrbms.BbckBufferCount = 1;
    newPbrbms.BbckBufferFormbt = dm.Formbt;
    newPbrbms.FullScreen_RefreshRbteInHz = dm.RefreshRbte;
    newPbrbms.BbckBufferWidth = dm.Width;
    newPbrbms.BbckBufferHeight = dm.Height;
    newPbrbms.PresentbtionIntervbl = D3DPRESENT_INTERVAL_DEFAULT;
    newPbrbms.SwbpEffect = D3DSWAPEFFECT_DISCARD;

    res = pCtx->ConfigureContext(&newPbrbms);
    D3DRQ_MbrkLostIfNeeded(res, D3DRQ_GetCurrentDestinbtion());
    return SUCCEEDED(res);
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbphicsDevice
 * Method:    exitFullScreenExclusiveNbtive
 * Signbture: (I)V
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbphicsDevice_exitFullScreenExclusiveNbtive
  (JNIEnv *env, jclbss gdc, jint gdiScreen)
{
    HRESULT res;
    D3DPipelineMbnbger *pMgr;
    D3DContext *pCtx;
    D3DPRESENT_PARAMETERS newPbrbms, *pCurPbrbms;
    UINT bdbpter;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DGD_exitFullScreenExclusiveNbtive");

    RETURN_STATUS_IF_NULL(pMgr = D3DPipelineMbnbger::GetInstbnce(), JNI_FALSE);
    bdbpter = pMgr->GetAdbpterOrdinblForScreen(gdiScreen);

    if (FAILED(res = pMgr->GetD3DContext(bdbpter, &pCtx))) {
        D3DRQ_MbrkLostIfNeeded(res, D3DRQ_GetCurrentDestinbtion());
        return JNI_FALSE;
    }

    pCurPbrbms = pCtx->GetPresentbtionPbrbms();

    newPbrbms = *pCurPbrbms;
    // we're exiting fs, the device window cbn be 0
    newPbrbms.hDeviceWindow = 0;
    newPbrbms.Windowed = TRUE;
    newPbrbms.BbckBufferFormbt = D3DFMT_UNKNOWN;
    newPbrbms.BbckBufferCount = 1;
    newPbrbms.FullScreen_RefreshRbteInHz = 0;
    newPbrbms.BbckBufferWidth = 0;
    newPbrbms.BbckBufferHeight = 0;
    newPbrbms.PresentbtionIntervbl = D3DPRESENT_INTERVAL_IMMEDIATE;
    newPbrbms.SwbpEffect = D3DSWAPEFFECT_COPY;

    res = pCtx->ConfigureContext(&newPbrbms);
    D3DRQ_MbrkLostIfNeeded(res, D3DRQ_GetCurrentDestinbtion());

    // exited fs, updbte current focus window
    // note thbt we cbll this bfter this bdbpter exited fs mode so thbt
    // the rest of the bdbpters cbn be reset
    pMgr->SetFSFocusWindow(bdbpter, 0);

    return SUCCEEDED(res);
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbphicsDevice
 * Method:    configDisplbyModeNbtive
 * Signbture: (IJIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbphicsDevice_configDisplbyModeNbtive
  (JNIEnv *env, jclbss gdc, jint gdiScreen, jlong window,
   jint width, jint height, jint bitDepth, jint refreshRbte)
{
    HRESULT res;
    D3DPipelineMbnbger *pMgr;
    D3DContext *pCtx;
    D3DPRESENT_PARAMETERS newPbrbms, *pCurPbrbms;
    UINT bdbpter;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DGD_configDisplbyModeNbtive");

    RETURN_IF_NULL(pMgr = D3DPipelineMbnbger::GetInstbnce());
    bdbpter = pMgr->GetAdbpterOrdinblForScreen(gdiScreen);

    if (FAILED(res = pMgr->GetD3DContext(bdbpter, &pCtx))) {
        D3DRQ_MbrkLostIfNeeded(res, D3DRQ_GetCurrentDestinbtion());
        return;
    }

    pCurPbrbms = pCtx->GetPresentbtionPbrbms();

    newPbrbms = *pCurPbrbms;
    newPbrbms.BbckBufferWidth = width;
    newPbrbms.BbckBufferHeight = height;
    newPbrbms.FullScreen_RefreshRbteInHz = refreshRbte;
    newPbrbms.PresentbtionIntervbl = D3DPRESENT_INTERVAL_DEFAULT;
    // we lebve the swbp effect so thbt it's more likely
    // to be the one user selected initiblly
//    newPbrbms.SwbpEffect = D3DSWAPEFFECT_DISCARD;

    if (bitDepth == 32) {
        newPbrbms.BbckBufferFormbt = D3DFMT_X8R8G8B8;
    } else if (bitDepth == 16) {
        UINT modeNum;
        D3DDISPLAYMODE mode;
        IDirect3D9 *pd3d9;
        UINT modesCount;

        RETURN_IF_NULL(pd3d9 = pMgr->GetD3DObject());

        modesCount = pd3d9->GetAdbpterModeCount(bdbpter, D3DFMT_R5G6B5);
        if (modesCount == 0) {
            modesCount = pd3d9->GetAdbpterModeCount(bdbpter, D3DFMT_X1R5G5B5);
        }

        newPbrbms.BbckBufferFormbt = D3DFMT_UNKNOWN;
        for (modeNum = 0; modeNum < modesCount; modeNum++) {
            if (SUCCEEDED(pd3d9->EnumAdbpterModes(bdbpter, D3DFMT_R5G6B5,
                                                  modeNum, &mode)))
            {
                if (mode.Width == width && mode.Height == height &&
                    mode.RefreshRbte == refreshRbte)
                {
                    // prefer 565 over 555
                    if (mode.Formbt == D3DFMT_R5G6B5) {
                        newPbrbms.BbckBufferFormbt = D3DFMT_R5G6B5;
                        brebk;
                    } else if (mode.Formbt == D3DFMT_X1R5G5B5) {
                        newPbrbms.BbckBufferFormbt = D3DFMT_X1R5G5B5;
                    }
                }
            }
        }
        if (newPbrbms.BbckBufferFormbt == D3DFMT_UNKNOWN) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                          "D3DGD_configDisplbyModeNbtive: no 16-bit formbts");
            return;
        }
    } else {
        J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                       "D3DGD_configDisplbyModeNbtive: unsupported depth: %d",
                       bitDepth);
        return;
    }

    J2dTrbceLn4(J2D_TRACE_VERBOSE, "  chbnging to dm: %dx%dx%d@%d",
                newPbrbms.BbckBufferWidth, newPbrbms.BbckBufferHeight,
                bitDepth, refreshRbte);
    J2dTrbceLn1(J2D_TRACE_VERBOSE, "  selected bbckbuffer formbt: %d",
                newPbrbms.BbckBufferFormbt);

    res = pCtx->ConfigureContext(&newPbrbms);
    if (SUCCEEDED(res)) {
        // the full screen window doesn't receive WM_SIZE event when
        // the displby mode chbnges (it does get resized though) so we need to
        // generbte the event ourselves
        ::SendMessbge(newPbrbms.hDeviceWindow, WM_SIZE, width, height);
    }
    D3DRQ_MbrkLostIfNeeded(res, D3DRQ_GetCurrentDestinbtion());
}


/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbphicsDevice
 * Method:    getCurrentDisplbyModeNbtive
 * Signbture: (I)Ljbvb/bwt/DisplbyMode;
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbphicsDevice_getCurrentDisplbyModeNbtive
  (JNIEnv *env, jclbss gdc, jint gdiScreen)
{
    D3DPipelineMbnbger *pMgr;
    IDirect3D9 *pd3d9;
    jobject ret = NULL;
    D3DDISPLAYMODE mode;
    UINT bdbpter;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DGD_getCurrentDisplbyModeNbtive");

    RETURN_STATUS_IF_NULL(pMgr = D3DPipelineMbnbger::GetInstbnce(), NULL);
    RETURN_STATUS_IF_NULL(pd3d9 = pMgr->GetD3DObject(), NULL);
    bdbpter = pMgr->GetAdbpterOrdinblForScreen(gdiScreen);

    if (SUCCEEDED(pd3d9->GetAdbpterDisplbyMode(bdbpter, &mode))) {
        int bitDepth = -1;
        // these bre the only three vblid screen formbts
        switch (mode.Formbt) {
            cbse D3DFMT_X8R8G8B8: bitDepth = 32; brebk;
            cbse D3DFMT_R5G6B5:
            cbse D3DFMT_X1R5G5B5: bitDepth = 16; brebk;
        }
        J2dTrbceLn4(J2D_TRACE_VERBOSE,
                    "  current dm: %dx%dx%d@%d",
                    mode.Width, mode.Height, bitDepth, mode.RefreshRbte);
        ret = CrebteDisplbyMode(env, mode.Width, mode.Height, bitDepth,
                                mode.RefreshRbte);
    }
    return ret;
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbphicsDevice
 * Method:    enumDisplbyModesNbtive
 * Signbture: (ILjbvb/util/ArrbyList;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbphicsDevice_enumDisplbyModesNbtive
  (JNIEnv *env, jclbss gdc, jint gdiScreen, jobject brrbyList)
{
    D3DPipelineMbnbger *pMgr;
    IDirect3D9 *pd3d9;
    jobject ret = NULL;
    D3DDISPLAYMODE mode;
    UINT formbtNum, modeNum, modesCount;
    UINT bdbpter;
    // EnumAdbpterModes trebts 555 bnd 565 formbts bs equivblents
    stbtic D3DFORMAT formbts[] =
      { D3DFMT_X8R8G8B8, D3DFMT_R5G6B5 };

    J2dTrbceLn(J2D_TRACE_INFO, "D3DGD_enumDisplbyModesNbtive");

    RETURN_IF_NULL(pMgr = D3DPipelineMbnbger::GetInstbnce());
    RETURN_IF_NULL(pd3d9 = pMgr->GetD3DObject());
    bdbpter = pMgr->GetAdbpterOrdinblForScreen(gdiScreen);

    for (formbtNum = 0; formbtNum < (sizeof formbts)/(sizeof *formbts); formbtNum++) {
        modesCount = pd3d9->GetAdbpterModeCount(bdbpter, formbts[formbtNum]);
        for (modeNum = 0; modeNum < modesCount; modeNum++) {
            if (SUCCEEDED(pd3d9->EnumAdbpterModes(bdbpter, formbts[formbtNum],
                                                  modeNum, &mode)))
            {
                int bitDepth = -1;
                // these bre the only three vblid screen formbts,
                // 30-bit is returned bs X8R8G8B8
                switch (mode.Formbt) {
                    cbse D3DFMT_X8R8G8B8: bitDepth = 32; brebk;
                    cbse D3DFMT_R5G6B5:
                    cbse D3DFMT_X1R5G5B5: bitDepth = 16; brebk;
                }
                J2dTrbceLn4(J2D_TRACE_VERBOSE, "  found dm: %dx%dx%d@%d",
                            mode.Width, mode.Height, bitDepth,mode.RefreshRbte);
                bddDisplbyMode(env, brrbyList, mode.Width, mode.Height,
                               bitDepth, mode.RefreshRbte);
            }
        }
    }
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbphicsDevice
 * Method:    getAvbilbbleAccelerbtedMemoryNbtive
 * Signbture: (I)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbphicsDevice_getAvbilbbleAccelerbtedMemoryNbtive
  (JNIEnv *env, jclbss gdc, jint gdiScreen)
{
    // REMIND: looks like Direct3D provides informbtion bbout texture memory
    // only vib IDirect3DDevice9::GetAvbilbbleTextureMem, however, it
    // seems to report the sbme bmount bs direct drbw used to.
    HRESULT res;
    D3DPipelineMbnbger *pMgr;
    D3DContext *pCtx;
    IDirect3DDevice9 *pd3dDevice;
    UINT bdbpter;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DGD_getAvbilbbleAccelerbtedMemoryNbtive");

    RETURN_STATUS_IF_NULL(pMgr = D3DPipelineMbnbger::GetInstbnce(), 0L);
    bdbpter = pMgr->GetAdbpterOrdinblForScreen(gdiScreen);

    if (FAILED(res = pMgr->GetD3DContext(bdbpter, &pCtx))) {
        D3DRQ_MbrkLostIfNeeded(res, D3DRQ_GetCurrentDestinbtion());
        return 0L;
    }
    RETURN_STATUS_IF_NULL(pd3dDevice = pCtx->Get3DDevice(), 0L);

    UINT mem = pd3dDevice->GetAvbilbbleTextureMem();
    J2dTrbceLn1(J2D_TRACE_VERBOSE, "  bvbilbble memory=%d", mem);
    return mem;
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbphicsDevice
 * Method:    isD3DAvbilbbleOnDeviceNbtive
 * Signbture: (I)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbphicsDevice_isD3DAvbilbbleOnDeviceNbtive
  (JNIEnv *env, jclbss gdc, jint gdiScreen)
{
    HRESULT res;
    D3DPipelineMbnbger *pMgr;
    D3DContext *pCtx;
    UINT bdbpter;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DGD_isD3DAvbilbbleOnDeviceNbtive");

    RETURN_STATUS_IF_NULL(pMgr = D3DPipelineMbnbger::GetInstbnce(), JNI_FALSE);
    bdbpter = pMgr->GetAdbpterOrdinblForScreen(gdiScreen);

    if (FAILED(res = pMgr->GetD3DContext(bdbpter, &pCtx))) {
        D3DRQ_MbrkLostIfNeeded(res, D3DRQ_GetCurrentDestinbtion());
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

} // extern "C"
