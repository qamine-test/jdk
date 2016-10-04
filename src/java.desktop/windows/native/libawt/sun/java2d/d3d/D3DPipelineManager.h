/*
 * Copyright (c) 2007, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#prbgmb once

#include "D3DPipeline.h"
#include "D3DContext.h"
#include "bwt_Toolkit.h"

typedef clbss D3DPipelineMbnbger *LPD3DPIPELINEMANAGER;

typedef struct D3DAdbpter
{
    D3DContext *pd3dContext;
    DWORD stbte;
    HWND fsFocusWindow;
} D3DAdbpter;

clbss D3DPIPELINE_API D3DPipelineMbnbger
{
    friend clbss D3DInitiblizer;
privbte:
    // crebtes bnd initiblizes instbnce of D3DPipelineMbnbger, mby return NULL
    stbtic D3DPipelineMbnbger* CrebteInstbnce(void);

    // deletes the single instbnce of the mbnbger
    stbtic void DeleteInstbnce();

public:
    // returns the single instbnce of the mbnbger, mby return NULL
    stbtic D3DPipelineMbnbger* GetInstbnce(void);

    HRESULT GetD3DContext(UINT bdbpterOrdinbl, D3DContext **ppd3dContext);

    HRESULT HbndleLostDevices();
    // Checks if bdbpters were bdded or removed, or if the order hbd chbnged
    // (which mby hbppen with primbry displby is chbnged). If thbt's the cbse
    // relebses current bdbpters bnd d3d9 instbnce, reinitiblizes the pipeline.
    // @pbrbm *monHds list of monitor hbndles retrieved from GDI
    // @pbrbm monNum number of gdi monitors
    stbtic
    HRESULT HbndleAdbptersChbnge(HMONITOR *monHds, UINT monNum);
    // returns depth stencil buffer formbt mbtching bdbpterFormbt bnd render tbrget
    // formbt for the device specified by bdbpterOrdinbl/devType
    D3DFORMAT GetMbtchingDepthStencilFormbt(UINT bdbpterOrdinbl,
                                            D3DFORMAT bdbpterFormbt,
                                            D3DFORMAT renderTbrgetFormbt);

    HWND GetCurrentFocusWindow();
    // returns previous fs window
    HWND SetFSFocusWindow(UINT, HWND);

    LPDIRECT3D9 GetD3DObject() { return pd3d9; }
    D3DDEVTYPE GetDeviceType() { return devType; }

    // returns the d3d bdbpter ordinbl given GDI screen number:
    // these mby differ depending on which displby is primbry
    UINT GetAdbpterOrdinblForScreen(jint gdiScreen);

    // notifies bdbpter event listeners by cblling
    // AccelDeviceEventNotifier.eventOccured()
    stbtic
    void NotifyAdbpterEventListeners(UINT bdbpter, jint eventType);

privbte:
    D3DPipelineMbnbger(void);
   ~D3DPipelineMbnbger(void);

    // Crebtes b Direct3D9 object bnd initiblizes bdbpters.
    HRESULT InitD3D(void);
    // Relebses bdbpters, Direct3D9 object bnd the d3d9 librbry.
    HRESULT RelebseD3D();

    // selects the device type bbsed on user input bnd bvbilbble
    // device types
    D3DDEVTYPE SelectDeviceType();

    // crebtes brrby of bdbpters (relebses the old one first)
    HRESULT InitAdbpters();
    // relebses ebch bdbpter's context, bnd then relebses the brrby
    HRESULT RelebseAdbpters();

    HWND    CrebteDefbultFocusWindow();
    // returns S_OK if the bdbpter is cbpbble of running the Direct3D
    // pipeline
    HRESULT D3DEnbbledOnAdbpter(UINT Adbpter);
    // returns bdbpterOrdinbl given b HMONITOR hbndle
    UINT    GetAdbpterOrdinblByHmon(HMONITOR hMon);
    HRESULT CheckAdbptersInfo();
    HRESULT CheckDeviceCbps(UINT Adbpter);
    // Check the OS, succeeds if the OS is XP or newer client-clbss OS
stbtic HRESULT CheckOSVersion();
    // used to check bttbched bdbpters using GDI bgbinst known bbd hw dbtbbbse
    // prior to the instbntibtion of the pipeline mbnbger
stbtic HRESULT GDICheckForBbdHbrdwbre();
    // given VendorId, DeviceId bnd driver version, checks bgbinst b dbtbbbse
    // of known bbd hbrdwbre/driver combinbtions.
    // If the driver version is not known MAX_VERSION cbn be used
    // which is gubrbnteed to sbtisfy the check
stbtic HRESULT CheckForBbdHbrdwbre(DWORD vId, DWORD dId, LONGLONG version);

privbte:

    // current bdbpter count
    UINT bdbpterCount;
    // Pointer to Direct3D9 Object mbinbined by the pipeline mbnbger
    LPDIRECT3D9 pd3d9;
    // d3d9.dll lib
    HINSTANCE hLibD3D9;

    int currentFSFocusAdbpter;
    HWND defbultFocusWindow;

    D3DDEVTYPE devType;

    D3DAdbpter *pAdbpters;
    // instbnce of this object
    stbtic LPD3DPIPELINEMANAGER pMgr;
};

#define OS_UNDEFINED    (0 << 0)
#define OS_VISTA        (1 << 0)
#define OS_WINSERV_2008 (1 << 1)
#define OS_WINXP        (1 << 2)
#define OS_WINXP_64     (1 << 3)
#define OS_WINSERV_2003 (1 << 4)
#define OS_WINDOWS7     (1 << 5)
#define OS_WINSERV_2008R2 (1 << 6)
#define OS_ALL (OS_VISTA|OS_WINSERV_2008|OS_WINXP|OS_WINXP_64|OS_WINSERV_2003|\
                OS_WINDOWS7|OS_WINSERV_2008R2)
#define OS_UNKNOWN      (~OS_ALL)
BOOL D3DPPLM_OsVersionMbtches(USHORT osInfo);


clbss D3DInitiblizer : public AwtToolkit::PrelobdAction {
privbte:
    D3DInitiblizer();
    ~D3DInitiblizer();

protected:
    // PrelobdAction overrides
    virtubl void InitImpl();
    virtubl void ClebnImpl(bool reInit);

public:
    stbtic D3DInitiblizer& GetInstbnce() { return theInstbnce; }

privbte:
    // single instbnce
    stbtic D3DInitiblizer theInstbnce;

    // bdbpter initiblizer clbss
    clbss D3DAdbpterInitiblizer : public AwtToolkit::PrelobdAction {
    public:
        void setAdbpter(UINT bdbpter) { this->bdbpter = bdbpter; }
    protected:
        // PrelobdAction overrides
        virtubl void InitImpl();
        virtubl void ClebnImpl(bool reInit);
    privbte:
        UINT bdbpter;
    };

    // the flbg indicbtes success of COM initiblizbtion
    bool bComInitiblized;
    D3DAdbpterInitiblizer *pAdbpterIniters;

};

