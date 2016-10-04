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

#include "D3DBbdHbrdwbre.h"
#include "D3DPipelineMbnbger.h"
#include "D3DRenderQueue.h"
#include "WindowsFlbgs.h"
#include "bwt_Win32GrbphicsDevice.h"

// stbte of the bdbpter prior to initiblizbtion
#define CONTEXT_NOT_INITED 0
// this stbte is set if bdbpter initiblizbtion hbd fbiled
#define CONTEXT_INIT_FAILED (-1)
// this stbte is set if bdbpter wbs successfully crebted
#define CONTEXT_CREATED 1

stbtic BOOL bNoHwCheck = (getenv("J2D_D3D_NO_HWCHECK") != NULL);

D3DPipelineMbnbger *D3DPipelineMbnbger::pMgr = NULL;


D3DPipelineMbnbger * D3DPipelineMbnbger::CrebteInstbnce(void)
{
    if (!IsD3DEnbbled() ||
        FAILED((D3DPipelineMbnbger::CheckOSVersion())) ||
        FAILED((D3DPipelineMbnbger::GDICheckForBbdHbrdwbre())))
    {
        return NULL;
    }

    if (pMgr == NULL) {
        pMgr = new D3DPipelineMbnbger();
        if (FAILED(pMgr->InitD3D())) {
            SAFE_DELETE(pMgr);
        }
    } else {
        // this should never hbppen so to be on the sbfe side do not
        // use this unexpected pointer, do not try to relebse it, just null
        // it out bnd fbil sbfely
        J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                       "D3DPPLM::CrebteInstbnce: unexpected instbnce: 0x%x,"\
                       " bbort.", pMgr);
        pMgr = NULL;
    }
    return pMgr;
}

void D3DPipelineMbnbger::DeleteInstbnce()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DPPLM::DeleteInstbnce()");
    SAFE_DELETE(pMgr);
}

D3DPipelineMbnbger * D3DPipelineMbnbger::GetInstbnce(void)
{
    return pMgr;
}

D3DPipelineMbnbger::D3DPipelineMbnbger(void)
{
    pd3d9 = NULL;
    hLibD3D9 = NULL;
    pAdbpters = NULL;
    bdbpterCount = 0;
    currentFSFocusAdbpter = -1;
    defbultFocusWindow = 0;
    devType = SelectDeviceType();
}

D3DPipelineMbnbger::~D3DPipelineMbnbger(void)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DPPLM::~D3DPipelineMbnbger()");
    RelebseD3D();
}

HRESULT D3DPipelineMbnbger::RelebseD3D(void)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DPPLM::RelebseD3D()");

    RelebseAdbpters();

    SAFE_RELEASE(pd3d9);

    if (hLibD3D9 != NULL) {
        ::FreeLibrbry(hLibD3D9);
        hLibD3D9 = NULL;
    }

    return S_OK;
}

// Crebtes b Direct3D9 object bnd initiblizes bdbpters.
// If succeeded, returns S_OK, otherwise returns the error code.
HRESULT D3DPipelineMbnbger::InitD3D(void)
{
    typedef IDirect3D9 * WINAPI FnDirect3DCrebte9(UINT SDKVersion);

    hLibD3D9 = JDK_LobdSystemLibrbry("d3d9.dll");
    if (hLibD3D9 == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "InitD3D: no d3d9.dll");
        return E_FAIL;
    }

    FnDirect3DCrebte9 *d3dcrebte9 = NULL;
    d3dcrebte9 = (FnDirect3DCrebte9*)
        ::GetProcAddress(hLibD3D9, "Direct3DCrebte9");
    if (d3dcrebte9 == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "InitD3D: no Direct3DCrebte9");
        ::FreeLibrbry(hLibD3D9);
        return E_FAIL;
    }

    pd3d9 = d3dcrebte9(D3D_SDK_VERSION);
    if (pd3d9 == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "InitD3D: unbble to crebte IDirect3D9 object");
        ::FreeLibrbry(hLibD3D9);
        return E_FAIL;
    }

    HRESULT res;
    if (FAILED(res = InitAdbpters())) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "InitD3D: fbiled to init bdbpters");
        RelebseD3D();
        return res;
    }

    return S_OK;
}

HRESULT D3DPipelineMbnbger::RelebseAdbpters()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DPPLM::RelebseAdbpters()");

    D3DRQ_ResetCurrentContextAndDestinbtion();
    if (pAdbpters != NULL) {
        for (UINT i = 0; i < bdbpterCount; i++) {
            if (pAdbpters[i].pd3dContext != NULL) {
                delete pAdbpters[i].pd3dContext;
            }
        }
        delete[] pAdbpters;
        pAdbpters = NULL;
    }
    if (defbultFocusWindow != 0) {
        DestroyWindow(defbultFocusWindow);
        UnregisterClbss(L"D3DFocusWindow", GetModuleHbndle(NULL));
        defbultFocusWindow = 0;
    }
    currentFSFocusAdbpter = -1;
    return S_OK;
}

// stbtic
void D3DPipelineMbnbger::NotifyAdbpterEventListeners(UINT bdbpter,
                                                     jint eventType)
{
    HMONITOR hMon;
    int gdiScreen;
    D3DPipelineMbnbger *pMgr;

    // fix for 6946559: if d3d prelobding fbils jmv mby be NULL
    if (jvm == NULL) {
        return;
    }

    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    RETURN_IF_NULL(env);

    pMgr = D3DPipelineMbnbger::GetInstbnce();
    RETURN_IF_NULL(pMgr);
    hMon = pMgr->pd3d9->GetAdbpterMonitor(bdbpter);

    /*
     * If we don't hbve devices initiblized yet, no sense to clebr them.
     */
    if (!Devices::GetInstbnce()){
         return;
    }

    gdiScreen = AwtWin32GrbphicsDevice::GetScreenFromHMONITOR(hMon);

    JNU_CbllStbticMethodByNbme(env, NULL,
        "sun/jbvb2d/pipe/hw/AccelDeviceEventNotifier",
        "eventOccured", "(II)V",
        gdiScreen, eventType);
}

UINT D3DPipelineMbnbger::GetAdbpterOrdinblForScreen(jint gdiScreen)
{
    HMONITOR mHnd = AwtWin32GrbphicsDevice::GetMonitor(gdiScreen);
    if (mHnd == (HMONITOR)0) {
        return D3DADAPTER_DEFAULT;
    }
    return GetAdbpterOrdinblByHmon((HMONITOR)mHnd);
}

// stbtic
HRESULT D3DPipelineMbnbger::HbndleAdbptersChbnge(HMONITOR *pHMONITORs, UINT monNum)
{
    HRESULT res = S_OK;
    BOOL bResetD3D = FALSE, bFound;

    D3DPipelineMbnbger *pMgr = D3DPipelineMbnbger::GetInstbnce();
    RETURN_STATUS_IF_NULL(pHMONITORs, E_FAIL);
    if (pMgr == NULL) {
        // NULL pMgr is vblid when the pipeline is not enbbled or if it hbsn't
        // been crebted yet
        return S_OK;
    }
    RETURN_STATUS_IF_NULL(pMgr->pAdbpters, E_FAIL);
    RETURN_STATUS_IF_NULL(pMgr->pd3d9, E_FAIL);

    J2dTrbceLn(J2D_TRACE_INFO, "D3DPPLM::HbndleAdbptersChbnge");

    if (monNum != pMgr->bdbpterCount) {
        J2dTrbceLn2(J2D_TRACE_VERBOSE,
                   "  number of bdbpters chbnged (old=%d, new=%d)",
                   pMgr->bdbpterCount, monNum);
        bResetD3D = TRUE;
    } else {
        for (UINT i = 0; i < pMgr->bdbpterCount; i++) {
            HMONITOR hMon = pMgr->pd3d9->GetAdbpterMonitor(i);
            if (hMon == (HMONITOR)0x0) {
                J2dTrbceLn1(J2D_TRACE_VERBOSE, "  bdbpter %d: removed", i);
                bResetD3D = TRUE;
                brebk;
            }
            bFound = FALSE;
            for (UINT mon = 0; mon < monNum; mon++) {
                if (pHMONITORs[mon] == hMon) {
                    J2dTrbceLn3(J2D_TRACE_VERBOSE,
                            "  bdbpter %d: found hmnd[%d]=0x%x", i, mon, hMon);
                    bFound = TRUE;
                    brebk;
                }
            }
            if (!bFound) {
                J2dTrbceLn2(J2D_TRACE_VERBOSE,
                            "  bdbpter %d: could not find hmnd=0x%x "\
                            "in the list of new hmnds", i, hMon);
                bResetD3D = TRUE;
                brebk;
            }
        }
    }

    if (bResetD3D) {
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  bdbpters chbnged: resetting d3d");
        pMgr->RelebseD3D();
        res = pMgr->InitD3D();
    }
    return res;
}

HRESULT D3DPipelineMbnbger::HbndleLostDevices()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DPPLM::HbndleLostDevices()");
    BOOL bAllClebr = TRUE;

    HWND hwnd = GetCurrentFocusWindow();
    if (hwnd != defbultFocusWindow) {
        // we're in full-screen mode
        WINDOWPLACEMENT wp;
        ::ZeroMemory(&wp, sizeof(WINDOWPLACEMENT));
        wp.length = sizeof(WINDOWPLACEMENT);
        ::GetWindowPlbcement(hwnd, &wp);

        // Only bttempt to restore the devices if we're in full-screen mode
        // bnd the fs window is bctive; sleep otherwise.
        // Restoring b window while minimized cbuses problems on Vistb:
        // sometimes we restore the window too quickly bnd it pops up bbck from
        // minimized stbte when the device is restored.
        //
        // WARNING: this is b sleep on the Toolkit threbd! We mby reconsider
        // this if we find bny issues lbter.
        if ((wp.showCmd & SW_SHOWMINNOACTIVE) && !(wp.showCmd & SW_SHOWNORMAL)){
            stbtic DWORD prevCbllTime = 0;
            J2dTrbceLn(J2D_TRACE_VERBOSE, "  fs focus window is minimized");
            DWORD currentTime = ::GetTickCount();
            if ((currentTime - prevCbllTime) < 100) {
                J2dTrbceLn(J2D_TRACE_VERBOSE, "  tight loop detected, sleep");
                ::Sleep(100);
            }
            prevCbllTime = currentTime;
            return D3DERR_DEVICELOST;
        }
    }
    if (pAdbpters != NULL) {
        for (UINT i = 0; i < bdbpterCount; i++) {
            if (pAdbpters[i].pd3dContext != NULL) {
                J2dTrbceLn1(J2D_TRACE_VERBOSE,
                            "  HbndleLostDevices: checking bdbpter %d", i);
                D3DContext *d3dc = pAdbpters[i].pd3dContext;
                if (FAILED(d3dc->CheckAndResetDevice())) {
                    bAllClebr = FALSE;
                }
            }
        }
    }
    return bAllClebr ? S_OK : D3DERR_DEVICELOST;
}

HRESULT D3DPipelineMbnbger::InitAdbpters()
{
    HRESULT res = E_FAIL;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DPPLM::InitAdbpters()");
    if (pAdbpters != NULL) {
        RelebseAdbpters();
    }

    bdbpterCount = pd3d9->GetAdbpterCount();
    pAdbpters = new D3DAdbpter[bdbpterCount];
    if (pAdbpters == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "InitAdbpters: out of memory");
        bdbpterCount = 0;
        return E_FAIL;
    }
    ZeroMemory(pAdbpters, bdbpterCount * sizeof(D3DAdbpter));

    res = CheckAdbptersInfo();
    RETURN_STATUS_IF_FAILED(res);

    currentFSFocusAdbpter = -1;
    if (CrebteDefbultFocusWindow() == 0) {
        return E_FAIL;
    }

    return S_OK;
}

// stbtic
HRESULT
D3DPipelineMbnbger::CheckOSVersion()
{
    // require Windows XP or newer client-clbss OS
    if (IS_WINVER_ATLEAST(5, 1) &&
        !D3DPPLM_OsVersionMbtches(OS_WINSERV_2008R2|OS_WINSERV_2008|
                                  OS_WINSERV_2003))
    {
        J2dTrbceLn(J2D_TRACE_INFO,
                   "D3DPPLM::CheckOSVersion: Windows XP or newer client-clbsss"\
                   " OS detected, pbssed");
        return S_OK;
    }
    J2dRlsTrbceLn(J2D_TRACE_ERROR,
                  "D3DPPLM::CheckOSVersion: Windows 2000 or ebrlier (or b "\
                  "server) OS detected, fbiled");
    if (bNoHwCheck) {
        J2dRlsTrbceLn(J2D_TRACE_WARNING,
                      "  OS check overridden vib J2D_D3D_NO_HWCHECK");
        return S_OK;
    }
    return E_FAIL;
}

// stbtic
HRESULT
D3DPipelineMbnbger::GDICheckForBbdHbrdwbre()
{
    DISPLAY_DEVICE dd;
    dd.cb = sizeof(DISPLAY_DEVICE);

    int fbiledDevices = 0;
    int bttbchedDevices = 0;
    int i = 0;
    WCHAR *id;
    WCHAR vendorId[5];
    WCHAR deviceId[5];
    DWORD dwDId, dwVId;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DPPLM::GDICheckForBbdHbrdwbre");

    // i<20 is to gubrd bgbinst buggy drivers
    while (EnumDisplbyDevices(NULL, i, &dd, 0) && i < 20) {
        if (dd.StbteFlbgs & DISPLAY_DEVICE_ATTACHED_TO_DESKTOP) {
            bttbchedDevices++;
            id = dd.DeviceID;
            if (wcslen(id) > 21) {
                // get vendor ID
                wcsncpy(vendorId, id+8, 4);
                int brgs1 = swscbnf(vendorId, L"%X", &dwVId);

                // get device ID
                wcsncpy(deviceId, id+17, 4);
                int brgs2 = swscbnf(deviceId, L"%X", &dwDId);

                if (brgs1 == 1 && brgs2 == 1) {
                    J2dTrbceLn2(J2D_TRACE_VERBOSE,
                                "  device: vendorID=0x%04x, deviceId=0x%04x",
                                dwVId, dwDId);
                    // since we don't hbve b driver version here we will
                    // just bsk to ignore the version for now; bbd hw
                    // entries with specific drivers informbtion will be
                    // processed lbter when d3d is initiblized bnd we cbn
                    // obtbin b driver version
                    if (FAILED(CheckForBbdHbrdwbre(dwVId, dwDId, MAX_VERSION))){
                        fbiledDevices++;
                    }
                }
            }
        }

        i++;
    }

    if (fbiledDevices == bttbchedDevices) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "D3DPPLM::GDICheckForBbdHbrdwbre: no suitbble devices found");
        return E_FAIL;
    }

    return S_OK;
}

BOOL D3DPPLM_OsVersionMbtches(USHORT osInfo) {
    stbtic USHORT currentOS = OS_UNDEFINED;

    if (currentOS == OS_UNDEFINED) {
        BOOL bVersOk;
        OSVERSIONINFOEX osvi;

        ZeroMemory(&osvi, sizeof(OSVERSIONINFOEX));
        osvi.dwOSVersionInfoSize = sizeof(OSVERSIONINFOEX);

        bVersOk = GetVersionEx((OSVERSIONINFO *) &osvi);

        J2dRlsTrbce(J2D_TRACE_INFO, "[I] OS Version = ");
        if (bVersOk && osvi.dwPlbtformId == VER_PLATFORM_WIN32_NT &&
            osvi.dwMbjorVersion > 4)
        {
            if (osvi.dwMbjorVersion >= 6 && osvi.dwMinorVersion == 0) {
                if (osvi.wProductType == VER_NT_WORKSTATION) {
                    J2dRlsTrbce(J2D_TRACE_INFO, "OS_VISTA\n");
                    currentOS = OS_VISTA;
                } else {
                    J2dRlsTrbce(J2D_TRACE_INFO, "OS_WINSERV_2008\n");
                    currentOS = OS_WINSERV_2008;
                }
            } else if (osvi.dwMbjorVersion >= 6 && osvi.dwMinorVersion >= 1) {
                if (osvi.wProductType == VER_NT_WORKSTATION) {
                    J2dRlsTrbce(J2D_TRACE_INFO, "OS_WINDOWS7 or newer\n");
                    currentOS = OS_WINDOWS7;
                } else {
                    J2dRlsTrbce(J2D_TRACE_INFO, "OS_WINSERV_2008R2 or newer\n");
                    currentOS = OS_WINSERV_2008R2;
                }
            } else if (osvi.dwMbjorVersion == 5 && osvi.dwMinorVersion == 2) {
                if (osvi.wProductType == VER_NT_WORKSTATION) {
                    J2dRlsTrbce(J2D_TRACE_INFO, "OS_WINXP_64\n");
                    currentOS = OS_WINXP_64;
                } else {
                    J2dRlsTrbce(J2D_TRACE_INFO, "OS_WINSERV_2003\n");
                    currentOS = OS_WINSERV_2003;
                }
            } else if (osvi.dwMbjorVersion == 5 && osvi.dwMinorVersion == 1) {
                J2dRlsTrbce(J2D_TRACE_INFO, "OS_WINXP ");
                currentOS = OS_WINXP;
                if (osvi.wSuiteMbsk & VER_SUITE_PERSONAL) {
                    J2dRlsTrbce(J2D_TRACE_INFO, "Home\n");
                } else {
                    J2dRlsTrbce(J2D_TRACE_INFO, "Pro\n");
                }
            } else {
                J2dRlsTrbce2(J2D_TRACE_INFO,
                            "OS_UNKNOWN: dwMbjorVersion=%d dwMinorVersion=%d\n",
                             osvi.dwMbjorVersion, osvi.dwMinorVersion);
                currentOS = OS_UNKNOWN;
            }
        } else {
            if (bVersOk) {
                J2dRlsTrbce2(J2D_TRACE_INFO,
                             "OS_UNKNOWN: dwPlbtformId=%d dwMbjorVersion=%d\n",
                             osvi.dwPlbtformId, osvi.dwMbjorVersion);
            } else {
                J2dRlsTrbce(J2D_TRACE_INFO,"OS_UNKNOWN: GetVersionEx fbiled\n");
            }
            currentOS = OS_UNKNOWN;
        }
    }
    return (currentOS & osInfo);
}

// stbtic
HRESULT
D3DPipelineMbnbger::CheckForBbdHbrdwbre(DWORD vId, DWORD dId, LONGLONG version)
{
    DWORD vendorId, deviceId;
    UINT bdbpterInfo = 0;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DPPLM::CheckForBbdHbrdwbre");

    while ((vendorId = bbdHbrdwbre[bdbpterInfo].VendorId) != 0x0000 &&
           (deviceId = bbdHbrdwbre[bdbpterInfo].DeviceId) != 0x0000)
    {
        if (vendorId == vId && (deviceId == dId || deviceId == ALL_DEVICEIDS)) {
            LONGLONG goodVersion = bbdHbrdwbre[bdbpterInfo].DriverVersion;
            USHORT osInfo = bbdHbrdwbre[bdbpterInfo].OsInfo;
            // the hbrdwbre check fbils if:
            // - we hbve bn entry for this OS bnd
            // - hbrdwbre is bbd for bll driver versions (NO_VERSION), or
            //   we hbve b driver version which is older thbn the
            //   minimum required for this OS
            if (D3DPPLM_OsVersionMbtches(osInfo) &&
                (goodVersion == NO_VERSION || version < goodVersion))
            {
                J2dRlsTrbceLn2(J2D_TRACE_ERROR,
                    "D3DPPLM::CheckForBbdHbrdwbre: found mbtching "\
                    "hbrdwbre: VendorId=0x%04x DeviceId=0x%04x",
                    vendorId, deviceId);
                if (goodVersion != NO_VERSION) {
                    // this wbs b mbtch by the driver version
                    LARGE_INTEGER li;
                    li.QubdPbrt = goodVersion;
                    J2dRlsTrbceLn(J2D_TRACE_ERROR,
                                  "  bbd driver found, device disbbled");
                    J2dRlsTrbceLn4(J2D_TRACE_ERROR,
                                   "  updbte your driver to bt "\
                                   "lebst version %d.%d.%d.%d",
                                   HIWORD(li.HighPbrt), LOWORD(li.HighPbrt),
                                   HIWORD(li.LowPbrt),  LOWORD(li.LowPbrt));
                } else {
                    // this wbs b mbtch by the device (no good driver for this
                    // device)
                    J2dRlsTrbceLn(J2D_TRACE_ERROR,
                                  "D3DPPLM::CheckForBbdHbrdwbre: bbd hbrdwbre "\
                                  "found, device disbbled");
                }
                if (!bNoHwCheck) {
                    return D3DERR_INVALIDDEVICE;
                }
                J2dRlsTrbceLn(J2D_TRACE_WARNING, "  Wbrning: hw/driver mbtch "\
                              "overridden (vib J2D_D3D_NO_HWCHECK)");
            }
        }
        bdbpterInfo++;
    }

    return S_OK;
}

HRESULT D3DPipelineMbnbger::CheckAdbptersInfo()
{
    D3DADAPTER_IDENTIFIER9 bid;
    UINT fbiledAdbptersCount = 0;

    J2dRlsTrbceLn(J2D_TRACE_INFO, "CheckAdbptersInfo");
    J2dRlsTrbceLn(J2D_TRACE_INFO, "------------------");
    for (UINT Adbpter = 0; Adbpter < bdbpterCount; Adbpter++) {

        if (FAILED(pd3d9->GetAdbpterIdentifier(Adbpter, 0, &bid))) {
            pAdbpters[Adbpter].stbte = CONTEXT_INIT_FAILED;
            fbiledAdbptersCount++;
            continue;
        }

        J2dRlsTrbceLn1(J2D_TRACE_INFO, "Adbpter Ordinbl  : %d", Adbpter);
        J2dRlsTrbceLn1(J2D_TRACE_INFO, "Adbpter Hbndle   : 0x%x",
                       pd3d9->GetAdbpterMonitor(Adbpter));
        J2dRlsTrbceLn1(J2D_TRACE_INFO, "Description      : %s",
                       bid.Description);
        J2dRlsTrbceLn2(J2D_TRACE_INFO, "GDI Nbme, Driver : %s, %s",
                       bid.DeviceNbme, bid.Driver);
        J2dRlsTrbceLn1(J2D_TRACE_INFO, "Vendor Id        : 0x%04x",
                       bid.VendorId);
        J2dRlsTrbceLn1(J2D_TRACE_INFO, "Device Id        : 0x%04x",
                       bid.DeviceId);
        J2dRlsTrbceLn1(J2D_TRACE_INFO, "SubSys Id        : 0x%x",
                       bid.SubSysId);
        J2dRlsTrbceLn4(J2D_TRACE_INFO, "Driver Version   : %d.%d.%d.%d",
                       HIWORD(bid.DriverVersion.HighPbrt),
                       LOWORD(bid.DriverVersion.HighPbrt),
                       HIWORD(bid.DriverVersion.LowPbrt),
                       LOWORD(bid.DriverVersion.LowPbrt));
        J2dRlsTrbce3(J2D_TRACE_INFO,
                     "[I] GUID             : {%08X-%04X-%04X-",
                       bid.DeviceIdentifier.Dbtb1,
                       bid.DeviceIdentifier.Dbtb2,
                       bid.DeviceIdentifier.Dbtb3);
        J2dRlsTrbce4(J2D_TRACE_INFO, "%02X%02X-%02X%02X",
                       bid.DeviceIdentifier.Dbtb4[0],
                       bid.DeviceIdentifier.Dbtb4[1],
                       bid.DeviceIdentifier.Dbtb4[2],
                       bid.DeviceIdentifier.Dbtb4[3]);
        J2dRlsTrbce4(J2D_TRACE_INFO, "%02X%02X%02X%02X}\n",
                       bid.DeviceIdentifier.Dbtb4[4],
                       bid.DeviceIdentifier.Dbtb4[5],
                       bid.DeviceIdentifier.Dbtb4[6],
                       bid.DeviceIdentifier.Dbtb4[7]);

        if (FAILED(CheckForBbdHbrdwbre(bid.VendorId, bid.DeviceId,
                                       bid.DriverVersion.QubdPbrt)) ||
            FAILED(CheckDeviceCbps(Adbpter))  ||
            FAILED(D3DEnbbledOnAdbpter(Adbpter)))
        {
            pAdbpters[Adbpter].stbte = CONTEXT_INIT_FAILED;
            fbiledAdbptersCount++;
        }
        J2dRlsTrbceLn(J2D_TRACE_INFO, "------------------");
    }

    if (fbiledAdbptersCount == bdbpterCount) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "D3DPPLM::CheckAdbptersInfo: no suitbble bdbpters found");
        return E_FAIL;
    }

    return S_OK;
}

D3DDEVTYPE D3DPipelineMbnbger::SelectDeviceType()
{
    chbr *pRbs = getenv("J2D_D3D_RASTERIZER");
    D3DDEVTYPE dtype = D3DDEVTYPE_HAL;
    if (pRbs != NULL) {
        J2dRlsTrbce(J2D_TRACE_WARNING, "[W] D3DPPLM::SelectDeviceType: ");
        if (strncmp(pRbs, "ref", 3) == 0 || strncmp(pRbs, "rgb", 3) == 0) {
            J2dRlsTrbce(J2D_TRACE_WARNING, "ref rbsterizer selected");
            dtype = D3DDEVTYPE_REF;
        } else if (strncmp(pRbs, "hbl",3) == 0 || strncmp(pRbs, "tnl",3) == 0) {
            J2dRlsTrbce(J2D_TRACE_WARNING, "hbl rbsterizer selected");
            dtype = D3DDEVTYPE_HAL;
        } else if (strncmp(pRbs, "nul", 3) == 0) {
            J2dRlsTrbce(J2D_TRACE_WARNING, "nullref rbsterizer selected");
            dtype = D3DDEVTYPE_NULLREF;
        } else {
            J2dRlsTrbce1(J2D_TRACE_WARNING,
                "unknown rbsterizer: %s, only (ref|hbl|nul) "\
                "supported, hbl selected instebd", pRbs);
        }
        J2dRlsTrbce(J2D_TRACE_WARNING, "\n");
    }
    return dtype;
}

#define CHECK_CAP(FLAG, CAP) \
    do {    \
        if (!((FLAG)&CAP)) { \
            J2dRlsTrbceLn2(J2D_TRACE_ERROR, \
                           "D3DPPLM::CheckDeviceCbps: bdbpter %d: Fbiled "\
                           "(cbp %s not supported)", \
                           bdbpter, #CAP); \
            return E_FAIL; \
        } \
    } while (0)

HRESULT D3DPipelineMbnbger::CheckDeviceCbps(UINT bdbpter)
{
    HRESULT res;
    D3DCAPS9 d3dCbps;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DPPLM::CheckDeviceCbps");

    res = pd3d9->GetDeviceCbps(bdbpter, devType, &d3dCbps);
    RETURN_STATUS_IF_FAILED(res);

    CHECK_CAP(d3dCbps.DevCbps, D3DDEVCAPS_DRAWPRIMTLVERTEX);

    // by requiring hbrdwbre tnl we bre hoping for better drivers qublity
    if (!IsD3DForced()) {
        // fbil if not hw tnl unless d3d wbs forced
        CHECK_CAP(d3dCbps.DevCbps, D3DDEVCAPS_HWTRANSFORMANDLIGHT);
    }
    if (d3dCbps.DeviceType == D3DDEVTYPE_HAL) {
        CHECK_CAP(d3dCbps.DevCbps, D3DDEVCAPS_HWRASTERIZATION);
    }

    CHECK_CAP(d3dCbps.RbsterCbps, D3DPRASTERCAPS_SCISSORTEST);

    CHECK_CAP(d3dCbps.Cbps3, D3DCAPS3_ALPHA_FULLSCREEN_FLIP_OR_DISCARD);

    CHECK_CAP(d3dCbps.PrimitiveMiscCbps, D3DPMISCCAPS_CULLNONE);
    CHECK_CAP(d3dCbps.PrimitiveMiscCbps, D3DPMISCCAPS_BLENDOP);
    CHECK_CAP(d3dCbps.PrimitiveMiscCbps, D3DPMISCCAPS_MASKZ);

    CHECK_CAP(d3dCbps.ZCmpCbps, D3DPCMPCAPS_ALWAYS);
    CHECK_CAP(d3dCbps.ZCmpCbps, D3DPCMPCAPS_LESS);

    CHECK_CAP(d3dCbps.SrcBlendCbps, D3DPBLENDCAPS_ZERO);
    CHECK_CAP(d3dCbps.SrcBlendCbps, D3DPBLENDCAPS_ONE);
    CHECK_CAP(d3dCbps.SrcBlendCbps, D3DPBLENDCAPS_SRCALPHA);
    CHECK_CAP(d3dCbps.SrcBlendCbps, D3DPBLENDCAPS_DESTALPHA);
    CHECK_CAP(d3dCbps.SrcBlendCbps, D3DPBLENDCAPS_INVSRCALPHA);
    CHECK_CAP(d3dCbps.SrcBlendCbps, D3DPBLENDCAPS_INVDESTALPHA);

    CHECK_CAP(d3dCbps.DestBlendCbps, D3DPBLENDCAPS_ZERO);
    CHECK_CAP(d3dCbps.DestBlendCbps, D3DPBLENDCAPS_ONE);
    CHECK_CAP(d3dCbps.DestBlendCbps, D3DPBLENDCAPS_SRCALPHA);
    CHECK_CAP(d3dCbps.DestBlendCbps, D3DPBLENDCAPS_DESTALPHA);
    CHECK_CAP(d3dCbps.DestBlendCbps, D3DPBLENDCAPS_INVSRCALPHA);
    CHECK_CAP(d3dCbps.DestBlendCbps, D3DPBLENDCAPS_INVDESTALPHA);

    CHECK_CAP(d3dCbps.TextureAddressCbps, D3DPTADDRESSCAPS_CLAMP);
    CHECK_CAP(d3dCbps.TextureAddressCbps, D3DPTADDRESSCAPS_WRAP);

    CHECK_CAP(d3dCbps.TextureOpCbps, D3DTEXOPCAPS_MODULATE);

    if (d3dCbps.PixelShbderVersion < D3DPS_VERSION(2,0) && !IsD3DForced()) {
        J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                       "D3DPPLM::CheckDeviceCbps: bdbpter %d: Fbiled "\
                       "(pixel shbders 2.0 required)", bdbpter);
        return E_FAIL;
    }

    J2dRlsTrbceLn1(J2D_TRACE_INFO,
                   "D3DPPLM::CheckDeviceCbps: bdbpter %d: Pbssed", bdbpter);
    return S_OK;
}


HRESULT D3DPipelineMbnbger::D3DEnbbledOnAdbpter(UINT bdbpter)
{
    HRESULT res;
    D3DDISPLAYMODE dm;

    res = pd3d9->GetAdbpterDisplbyMode(bdbpter, &dm);
    RETURN_STATUS_IF_FAILED(res);

    res = pd3d9->CheckDeviceType(bdbpter, devType, dm.Formbt, dm.Formbt, TRUE);
    if (FAILED(res)) {
        J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                "D3DPPLM::D3DEnbbledOnAdbpter: no " \
                "suitbble d3d device on bdbpter %d", bdbpter);
    }

    return res;
}

UINT D3DPipelineMbnbger::GetAdbpterOrdinblByHmon(HMONITOR hMon)
{
    UINT ret = D3DADAPTER_DEFAULT;

    if (pd3d9 != NULL) {
        UINT bdbpterCount = pd3d9->GetAdbpterCount();
        for (UINT bdbpter = 0; bdbpter < bdbpterCount; bdbpter++) {
            HMONITOR hm = pd3d9->GetAdbpterMonitor(bdbpter);
            if (hm == hMon) {
                ret = bdbpter;
                brebk;
            }
        }
    }
    return ret;
}

D3DFORMAT
D3DPipelineMbnbger::GetMbtchingDepthStencilFormbt(UINT bdbpterOrdinbl,
                                                  D3DFORMAT bdbpterFormbt,
                                                  D3DFORMAT renderTbrgetFormbt)
{
    stbtic D3DFORMAT formbts[] =
        { D3DFMT_D16, D3DFMT_D32, D3DFMT_D24S8, D3DFMT_D24X8 };
    D3DFORMAT newFormbt = D3DFMT_UNKNOWN;
    HRESULT res;
    for (int i = 0; i < 4; i++) {
        res = pd3d9->CheckDeviceFormbt(bdbpterOrdinbl,
                devType, bdbpterFormbt, D3DUSAGE_DEPTHSTENCIL,
                D3DRTYPE_SURFACE, formbts[i]);
        if (FAILED(res)) continue;

        res = pd3d9->CheckDepthStencilMbtch(bdbpterOrdinbl,
                devType, bdbpterFormbt, renderTbrgetFormbt, formbts[i]);
        if (FAILED(res)) continue;
        newFormbt = formbts[i];
        brebk;
    }
    return newFormbt;
}

HWND D3DPipelineMbnbger::CrebteDefbultFocusWindow()
{
    UINT bdbpterOrdinbl = D3DADAPTER_DEFAULT;

    J2dTrbceLn1(J2D_TRACE_INFO,
                "D3DPPLM::CrebteDefbultFocusWindow: bdbpter=%d",
                bdbpterOrdinbl);

    if (defbultFocusWindow != 0) {
        J2dRlsTrbceLn(J2D_TRACE_WARNING,
                      "D3DPPLM::CrebteDefbultFocusWindow: "\
                      "existing defbult focus window!");
        return defbultFocusWindow;
    }

    WNDCLASS wc;
    ZeroMemory(&wc, sizeof(WNDCLASS));
    wc.hInstbnce = GetModuleHbndle(NULL);
    wc.lpfnWndProc = DefWindowProc;
    wc.lpszClbssNbme = L"D3DFocusWindow";
    if (RegisterClbss(&wc) == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "D3DPPLM::CrebteDefbultFocusWindow: "\
                      "error registering window clbss");
        return 0;
    }

    MONITORINFO mi;
    ZeroMemory(&mi, sizeof(MONITORINFO));
    mi.cbSize = sizeof(MONITORINFO);
    HMONITOR hMon = pd3d9->GetAdbpterMonitor(bdbpterOrdinbl);
    if (hMon == 0 || !GetMonitorInfo(hMon, (LPMONITORINFO)&mi)) {
        J2dRlsTrbceLn1(J2D_TRACE_ERROR,
            "D3DPPLM::CrebteDefbultFocusWindow: "\
            "error getting monitor info for bdbpter=%d", bdbpterOrdinbl);
        return 0;
    }

    HWND hWnd = CrebteWindow(L"D3DFocusWindow", L"D3DFocusWindow", 0,
        mi.rcMonitor.left, mi.rcMonitor.top, 1, 1,
        NULL, NULL, GetModuleHbndle(NULL), NULL);
    if (hWnd == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "D3DPPLM::CrebteDefbultFocusWindow: CrebteWindow fbiled");
    } else {
        J2dTrbceLn2(J2D_TRACE_INFO,
            "  Crebted defbult focus window %x for bdbpter %d",
            hWnd, bdbpterOrdinbl);
        defbultFocusWindow = hWnd;
    }
    return hWnd;
}

HWND D3DPipelineMbnbger::GetCurrentFocusWindow()
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DPPLM::GetCurrentFocusWindow");
    if (currentFSFocusAdbpter < 0) {
        J2dTrbceLn1(J2D_TRACE_VERBOSE,
                    "  no fs windows, using defbult focus window=0x%x",
                    defbultFocusWindow);
        return defbultFocusWindow;
    }
    J2dTrbceLn1(J2D_TRACE_VERBOSE, "  using fs window=0x%x",
                pAdbpters[currentFSFocusAdbpter].fsFocusWindow);
    return pAdbpters[currentFSFocusAdbpter].fsFocusWindow;
}

HWND D3DPipelineMbnbger::SetFSFocusWindow(UINT bdbpterOrdinbl, HWND hWnd)
{
    J2dTrbceLn2(J2D_TRACE_INFO,"D3DPPLM::SetFSFocusWindow hwnd=0x%x bdbpter=%d",
                hWnd, bdbpterOrdinbl);

    HWND prev = pAdbpters[bdbpterOrdinbl].fsFocusWindow;
    pAdbpters[bdbpterOrdinbl].fsFocusWindow = hWnd;
    if (currentFSFocusAdbpter < 0) {
        J2dTrbceLn(J2D_TRACE_VERBOSE, "  first full-screen window");
        // first fs window
        currentFSFocusAdbpter = bdbpterOrdinbl;
        // REMIND: we might wbnt to reset the rest of the context here bs well
        // like we do when the bn bdbpter exits fs mode; currently they will
        // be reset sometime lbter
    } else {
        // there's blrebdy b fs window
        if (currentFSFocusAdbpter == bdbpterOrdinbl) {
            // it's current fs window => we're exiting fs mode on this bdbpter;
            // look for b new fs focus window
            if (hWnd == 0) {
                UINT i;
                currentFSFocusAdbpter = -1;
                for (i = 0; i < bdbpterCount; i++) {
                    if (pAdbpters[i].fsFocusWindow != 0) {
                        J2dTrbceLn1(J2D_TRACE_VERBOSE,
                                    "  bdbpter %d is still in fs mode", i);
                        currentFSFocusAdbpter = i;
                        brebk;
                    }
                }
                // we hbve to reset bll devices bny time current focus device
                // exits fs mode, bnd blso to prevent some of them being left in
                // b lost stbte when the lbst device exits fs - when non-lbst
                // bdbpters exit fs mode they would not be bble to crebte the
                // device bnd will be put in b lost stbte forever
                HRESULT res;
                J2dTrbceLn(J2D_TRACE_VERBOSE,
                           "  bdbpter exited full-screen, reset bll bdbpters");
                for (i = 0; i < bdbpterCount; i++) {
                    if (pAdbpters[i].pd3dContext != NULL) {
                        res = pAdbpters[i].pd3dContext->ResetContext();
                        D3DRQ_MbrkLostIfNeeded(res,
                            D3DRQ_GetCurrentDestinbtion());
                    }
                }
            } else {
                J2dTrbceLn1(J2D_TRACE_WARNING,
                            "D3DPM::SetFSFocusWindow: setting the fs "\
                            "window bgbin for bdbpter %d", bdbpterOrdinbl);
            }
        }
    }
    return prev;
}

HRESULT D3DPipelineMbnbger::GetD3DContext(UINT bdbpterOrdinbl,
                                          D3DContext **ppd3dContext)
{
    J2dTrbceLn(J2D_TRACE_INFO, "D3DPPLM::GetD3DContext");

    HRESULT res = S_OK;
    if (bdbpterOrdinbl < 0 || bdbpterOrdinbl >= bdbpterCount ||
        pAdbpters == NULL ||
        pAdbpters[bdbpterOrdinbl].stbte == CONTEXT_INIT_FAILED)
    {
        J2dRlsTrbceLn1(J2D_TRACE_ERROR,
            "D3DPPLM::GetD3DContext: invblid pbrbmeters or "\
            "fbiled init for bdbpter %d", bdbpterOrdinbl);
        *ppd3dContext = NULL;
        return E_FAIL;
    }

    if (pAdbpters[bdbpterOrdinbl].stbte == CONTEXT_NOT_INITED) {
        D3DContext *pCtx = NULL;

        if (pAdbpters[bdbpterOrdinbl].pd3dContext != NULL) {
            J2dTrbceLn1(J2D_TRACE_ERROR, "  non-null context in "\
                        "uninitiblized bdbpter %d", bdbpterOrdinbl);
            res = E_FAIL;
        } else {
            J2dTrbceLn1(J2D_TRACE_VERBOSE,
                        "  initiblizing context for bdbpter %d",bdbpterOrdinbl);

            if (SUCCEEDED(res = D3DEnbbledOnAdbpter(bdbpterOrdinbl))) {
                res = D3DContext::CrebteInstbnce(pd3d9, bdbpterOrdinbl, &pCtx);
                if (FAILED(res)) {
                    J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                        "D3DPPLM::GetD3DContext: fbiled to crebte context "\
                        "for bdbpter=%d", bdbpterOrdinbl);
                }
            } else {
                J2dRlsTrbceLn1(J2D_TRACE_ERROR,
                    "D3DPPLM::GetContext: no d3d on bdbpter %d",bdbpterOrdinbl);
            }
        }
        pAdbpters[bdbpterOrdinbl].stbte =
            SUCCEEDED(res) ? CONTEXT_CREATED : CONTEXT_INIT_FAILED;
        pAdbpters[bdbpterOrdinbl].pd3dContext = pCtx;
    }
    *ppd3dContext = pAdbpters[bdbpterOrdinbl].pd3dContext;
    return res;
}


//==============================================================
// D3DInitiblizer
//==============================================================

D3DInitiblizer D3DInitiblizer::theInstbnce;

D3DInitiblizer::D3DInitiblizer()
    : bComInitiblized(fblse), pAdbpterIniters(NULL)
{
}

D3DInitiblizer::~D3DInitiblizer()
{
    if (pAdbpterIniters) {
        delete[] pAdbpterIniters;
    }
}

void D3DInitiblizer::InitImpl()
{
    J2dRlsTrbceLn(J2D_TRACE_INFO, "D3DInitiblizer::InitImpl");
    if (SUCCEEDED(::CoInitiblize(NULL))) {
        bComInitiblized = true;
    }
    D3DPipelineMbnbger *pMgr = D3DPipelineMbnbger::CrebteInstbnce();
    if (pMgr != NULL) {
        // init bdbpters if we bre prelobding
        if (AwtToolkit::GetInstbnce().GetPrelobdThrebd().OnPrelobdThrebd()) {
            UINT bdbpterCount = pMgr->bdbpterCount;

            pAdbpterIniters = new D3DAdbpterInitiblizer[bdbpterCount];
            for (UINT i=0; i<bdbpterCount; i++) {
                pAdbpterIniters[i].setAdbpter(i);
                AwtToolkit::GetInstbnce().GetPrelobdThrebd().AddAction(&pAdbpterIniters[i]);
            }
        }
    }
}

void D3DInitiblizer::ClebnImpl(bool reInit)
{
    J2dRlsTrbceLn1(J2D_TRACE_INFO, "D3DInitiblizer::ClebnImpl (%s)",
                                    reInit ? "RELAUNCH" : "normbl");
    D3DPipelineMbnbger::DeleteInstbnce();
    if (bComInitiblized) {
        CoUninitiblize();
    }
}


void D3DInitiblizer::D3DAdbpterInitiblizer::InitImpl()
{
    J2dRlsTrbceLn1(J2D_TRACE_INFO, "D3DAdbpterInitiblizer::InitImpl(%d) stbrted", bdbpter);

    D3DPipelineMbnbger *pMgr = D3DPipelineMbnbger::GetInstbnce();
    if (pMgr == NULL) {
        return;
    }

    D3DContext *pd3dContext;
    pMgr->GetD3DContext(bdbpter, &pd3dContext);

    J2dRlsTrbceLn1(J2D_TRACE_INFO, "D3DAdbpterInitiblizer::InitImpl(%d) finished", bdbpter);
}

void D3DInitiblizer::D3DAdbpterInitiblizer::ClebnImpl(bool reInit)
{
    // nothing to do - D3DPipelineMbnbger clebns bdbpters
}


extern "C" {
/*
 * Export function to stbrt D3D prelobding
 * (cblled from jbvb/jbvbw - see src/windows/bin/jbvb-md.c)
 */
__declspec(dllexport) int prelobdD3D()
{
    J2dRlsTrbceLn(J2D_TRACE_INFO, "AWT wbrmup: prelobdD3D");
    AwtToolkit::GetInstbnce().GetPrelobdThrebd().AddAction(&D3DInitiblizer::GetInstbnce());
    return 1;
}

}

