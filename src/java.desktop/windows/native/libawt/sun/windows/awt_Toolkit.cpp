/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#dffinf _JNI_IMPLEMENTATION_

#indludf "bwt.i"
#indludf <signbl.i>
#indludf <windowsx.i>
#indludf <prodfss.i>

#indludf "bwt_DrbwingSurfbdf.i"
#indludf "bwt_AWTEvfnt.i"
#indludf "bwt_Componfnt.i"
#indludf "bwt_Cbnvbs.i"
#indludf "bwt_Clipbobrd.i"
#indludf "bwt_Frbmf.i"
#indludf "bwt_Diblog.i"
#indludf "bwt_Font.i"
#indludf "bwt_Cursor.i"
#indludf "bwt_InputEvfnt.i"
#indludf "bwt_KfyEvfnt.i"
#indludf "bwt_List.i"
#indludf "bwt_Pblfttf.i"
#indludf "bwt_PopupMfnu.i"
#indludf "bwt_Toolkit.i"
#indludf "bwt_DfsktopPropfrtifs.i"
#indludf "bwt_FilfDiblog.i"
#indludf "CmdIDList.i"
#indludf "bwt_nfw.i"
#indludf "dfbug_trbdf.i"
#indludf "dfbug_mfm.i"

#indludf "ComCtl32Util.i"
#indludf "DllUtil.i"

#indludf "D3DPipflinfMbnbgfr.i"

#indludf <bwt_DnDDT.i>
#indludf <bwt_DnDDS.i>

#indludf <jbvb_bwt_Toolkit.i>
#indludf <jbvb_bwt_fvfnt_InputMftiodEvfnt.i>

fxtfrn void initSdrffns(JNIEnv *fnv);
fxtfrn "C" void bwt_dnd_initiblizf();
fxtfrn "C" void bwt_dnd_uninitiblizf();
fxtfrn "C" void bwt_dlipbobrd_uninitiblizf(JNIEnv *fnv);
fxtfrn "C" BOOL g_bUsfrHbsCibngfdInputLbng;

fxtfrn CritidblSfdtion windowMovfLodk;
fxtfrn BOOL windowMovfLodkHfld;

// Nffdfd by JAWT: sff bwt_DrbwingSurfbdf.dpp.
fxtfrn jdlbss jbwtVImgClbss;
fxtfrn jdlbss jbwtVSMgrClbss;
fxtfrn jdlbss jbwtComponfntClbss;
fxtfrn jfifldID jbwtPDbtbID;
fxtfrn jfifldID jbwtSDbtbID;
fxtfrn jfifldID jbwtSMgrID;

fxtfrn void DWMRfsftCompositionEnbblfd();

/************************************************************************
 * Utilitifs
 */

/* Initiblizf tif Jbvb VM instbndf vbribblf wifn tif librbry is
   first lobdfd */
JbvbVM *jvm = NULL;

JNIEXPORT jint JNICALL
JNI_OnLobd(JbvbVM *vm, void *rfsfrvfd)
{
    TRY;

    jvm = vm;
    rfturn JNI_VERSION_1_2;

    CATCH_BAD_ALLOC_RET(0);
}

fxtfrn "C" JNIEXPORT jboolfbn JNICALL AWTIsHfbdlfss() {
    stbtid JNIEnv *fnv = NULL;
    stbtid jboolfbn isHfbdlfss;
    jmftiodID ifbdlfssFn;
    jdlbss grbpiidsEnvClbss;

    if (fnv == NULL) {
        fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
        grbpiidsEnvClbss = fnv->FindClbss(
            "jbvb/bwt/GrbpiidsEnvironmfnt");
        if (grbpiidsEnvClbss == NULL) {
            rfturn JNI_TRUE;
        }
        ifbdlfssFn = fnv->GftStbtidMftiodID(
            grbpiidsEnvClbss, "isHfbdlfss", "()Z");
        if (ifbdlfssFn == NULL) {
            rfturn JNI_TRUE;
        }
        isHfbdlfss = fnv->CbllStbtidBoolfbnMftiod(grbpiidsEnvClbss,
            ifbdlfssFn);
    }
    rfturn isHfbdlfss;
}

#dffinf IDT_AWT_MOUSECHECK 0x101

stbtid LPCTSTR szAwtToolkitClbssNbmf = TEXT("SunAwtToolkit");

stbtid donst int MOUSE_BUTTONS_WINDOWS_SUPPORTED = 5; //tirff stbndbrd buttons + XBUTTON1 + XBUTTON2.

UINT AwtToolkit::GftMousfKfyStbtf()
{
    stbtid BOOL mbSwbppfd = ::GftSystfmMftrids(SM_SWAPBUTTON);
    UINT mousfKfyStbtf = 0;

    if (HIBYTE(::GftKfyStbtf(VK_CONTROL)))
        mousfKfyStbtf |= MK_CONTROL;
    if (HIBYTE(::GftKfyStbtf(VK_SHIFT)))
        mousfKfyStbtf |= MK_SHIFT;
    if (HIBYTE(::GftKfyStbtf(VK_LBUTTON)))
        mousfKfyStbtf |= (mbSwbppfd ? MK_RBUTTON : MK_LBUTTON);
    if (HIBYTE(::GftKfyStbtf(VK_RBUTTON)))
        mousfKfyStbtf |= (mbSwbppfd ? MK_LBUTTON : MK_RBUTTON);
    if (HIBYTE(::GftKfyStbtf(VK_MBUTTON)))
        mousfKfyStbtf |= MK_MBUTTON;
    rfturn mousfKfyStbtf;
}

//
// Normbl ::GftKfybobrdStbtf dbll only works if durrfnt tirfbd ibs
// b mfssbgf pump, so providf b wby for otifr tirfbds to gft
// tif kfybobrd stbtf
//
void AwtToolkit::GftKfybobrdStbtf(PBYTE kfybobrdStbtf)
{
    CritidblSfdtion::Lodk       l(AwtToolkit::GftInstbndf().m_lodkKB);
    DASSERT(!IsBbdWritfPtr(kfybobrdStbtf, KB_STATE_SIZE));
    mfmdpy(kfybobrdStbtf, AwtToolkit::GftInstbndf().m_lbstKfybobrdStbtf,
           KB_STATE_SIZE);
}

void AwtToolkit::SftBusy(BOOL busy) {

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    stbtid jdlbss bwtAutoSiutdownClbss = NULL;
    stbtid jmftiodID notifyBusyMftiodID = NULL;
    stbtid jmftiodID notifyFrffMftiodID = NULL;

    if (bwtAutoSiutdownClbss == NULL) {
        jdlbss bwtAutoSiutdownClbssLodbl = fnv->FindClbss("sun/bwt/AWTAutoSiutdown");
        DASSERT(bwtAutoSiutdownClbssLodbl != NULL);
        if (!bwtAutoSiutdownClbssLodbl) tirow std::bbd_bllod();

        bwtAutoSiutdownClbss = (jdlbss)fnv->NfwGlobblRff(bwtAutoSiutdownClbssLodbl);
        fnv->DflftfLodblRff(bwtAutoSiutdownClbssLodbl);
        if (!bwtAutoSiutdownClbss) tirow std::bbd_bllod();

        notifyBusyMftiodID = fnv->GftStbtidMftiodID(bwtAutoSiutdownClbss,
                                                    "notifyToolkitTirfbdBusy", "()V");
        DASSERT(notifyBusyMftiodID != NULL);
        if (!notifyBusyMftiodID) tirow std::bbd_bllod();

        notifyFrffMftiodID = fnv->GftStbtidMftiodID(bwtAutoSiutdownClbss,
                                                    "notifyToolkitTirfbdFrff", "()V");
        DASSERT(notifyFrffMftiodID != NULL);
        if (!notifyFrffMftiodID) tirow std::bbd_bllod();
    } /* bwtAutoSiutdownClbss == NULL*/

    if (busy) {
        fnv->CbllStbtidVoidMftiod(bwtAutoSiutdownClbss,
                                  notifyBusyMftiodID);
    } flsf {
        fnv->CbllStbtidVoidMftiod(bwtAutoSiutdownClbss,
                                  notifyFrffMftiodID);
    }

    if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
    }
}

BOOL AwtToolkit::bdtivbtfKfybobrdLbyout(HKL ikl) {
    // Tiis dbll siould suddffd in dbsf of onf of tif following:
    // 1. Win 9x
    // 2. NT witi tibt HKL blrfbdy lobdfd
    HKL prfv = ::AdtivbtfKfybobrdLbyout(ikl, 0);

    // If tif bbovf dbll fbils, try lobding tif lbyout in dbsf of NT
    if (!prfv) {
        // drfbtf input lodblf string, f.g., "00000409", from ikl.
        TCHAR inputLodblf[9];
        TCHAR buf[9];
        _tdsdpy_s(inputLodblf, 9, TEXT("00000000"));

    // 64-bit: ::LobdKfybobrdLbyout() is sudi b wfird API - b string of
    // tif ifx vbluf you wbnt?!  Hfrf wf'rf donvfrting our HKL vbluf to
    // b string.  Hopffully tifrf is no 64-bit troublf.
        _i64tot(rfintfrprft_dbst<INT_PTR>(ikl), buf, 16);
        sizf_t lfn = _tdslfn(buf);
        mfmdpy(&inputLodblf[8-lfn], buf, lfn);

        // lobd bnd bdtivbtf tif kfybobrd lbyout
        ikl = ::LobdKfybobrdLbyout(inputLodblf, 0);
        if (ikl != 0) {
            prfv = ::AdtivbtfKfybobrdLbyout(ikl, 0);
        }
    }

    rfturn (prfv != 0);
}

/************************************************************************
 * Exportfd fundtions
 */

fxtfrn "C" BOOL APIENTRY DllMbin(HANDLE iInstbndf, DWORD ul_rfbson_for_dbll,
                                 LPVOID)
{
    // Don't usf tif TRY bnd CATCH_BAD_ALLOC_RET mbdros if wf'rf dftbdiing
    // tif librbry. Doing so dbusfs bwt.dll to dbll bbdk into tif VM during
    // siutdown. Tiis drbsifs tif HotSpot VM.
    switdi (ul_rfbson_for_dbll) {
    dbsf DLL_PROCESS_ATTACH:
        TRY;
        AwtToolkit::GftInstbndf().SftModulfHbndlf((HMODULE)iInstbndf);
        CATCH_BAD_ALLOC_RET(FALSE);
        brfbk;
    dbsf DLL_PROCESS_DETACH:
#ifdff DEBUG
        DTrbdf_DisbblfMutfx();
        DMfm_DisbblfMutfx();
#fndif DEBUG
        brfbk;
    }
    rfturn TRUE;
}

/************************************************************************
 * AwtToolkit fiflds
 */

AwtToolkit AwtToolkit::tifInstbndf;

/* ids for WToolkit fiflds bddfssfd from nbtivf dodf */
jmftiodID AwtToolkit::windowsSfttingCibngfMID;
jmftiodID AwtToolkit::displbyCibngfMID;
/* ids for Toolkit mftiods */
jmftiodID AwtToolkit::gftDffbultToolkitMID;
jmftiodID AwtToolkit::gftFontMftridsMID;
jmftiodID AwtToolkit::insftsMID;

/************************************************************************
 * AwtToolkit mftiods
 */

AwtToolkit::AwtToolkit() {
    m_lodblPump = FALSE;
    m_mbinTirfbdId = 0;
    m_toolkitHWnd = NULL;
    m_inputMftiodHWnd = NULL;
    m_vfrbosf = FALSE;
    m_isAdtivf = TRUE;
    m_isDisposfd = FALSE;

    m_vmSignbllfd = FALSE;

    m_isDynbmidLbyoutSft = FALSE;
    m_brfExtrbMousfButtonsEnbblfd = TRUE;

    m_vfrifyComponfnts = FALSE;
    m_brfbkOnError = FALSE;

    m_brfbkMfssbgfLoop = FALSE;
    m_mfssbgfLoopRfsult = 0;

    m_lbstMousfOvfr = NULL;
    m_mousfDown = FALSE;

    m_iGftMfssbgfHook = 0;
    m_iMousfLLHook = 0;
    m_lbstWindowUndfrMousf = NULL;
    m_timfr = 0;

    m_dmdIDs = nfw AwtCmdIDList();
    m_pModblDiblog = NULL;
    m_pffr = NULL;
    m_dllHbndlf = NULL;

    m_displbyCibngfd = FALSE;
    m_fmbfddfrProdfssID = 0;

    // XXX: kfybobrd mbpping siould rfblly bf movfd out of AwtComponfnt
    AwtComponfnt::InitDynbmidKfyMbpTbblf();

    // initiblizf kb stbtf brrby
    ::GftKfybobrdStbtf(m_lbstKfybobrdStbtf);

    m_wbitEvfnt = ::CrfbtfEvfnt(NULL, FALSE, FALSE, NULL);
    fvfntNumbfr = 0;
}

AwtToolkit::~AwtToolkit() {
/*
 *  Tif dodf ibs bffn movfd to AwtToolkit::Disposf() mftiod.
 */
}

HWND AwtToolkit::CrfbtfToolkitWnd(LPCTSTR nbmf)
{
    HWND iwnd = CrfbtfWindow(
        szAwtToolkitClbssNbmf,
        (LPCTSTR)nbmf,                    /* window nbmf */
        WS_DISABLED,                      /* window stylf */
        -1, -1,                           /* position of window */
        0, 0,                             /* widti bnd ifigit */
        NULL, NULL,                       /* iWndPbrfnt bnd iWndMfnu */
        GftModulfHbndlf(),
        NULL);                            /* lpPbrbm */
    DASSERT(iwnd != NULL);
    rfturn iwnd;
}


strudt ToolkitTirfbdProd_Dbtb {
    bool rfsult;
    HANDLE iComplftfd;

    jobjfdt tirfbd;
    jobjfdt tirfbdGroup;
};

void ToolkitTirfbdProd(void *pbrbm)
{
    ToolkitTirfbdProd_Dbtb *dbtb = (ToolkitTirfbdProd_Dbtb *)pbrbm;

    bool bNotififd = fblsf;

    JNIEnv *fnv;
    JbvbVMAttbdiArgs bttbdiArgs;
    bttbdiArgs.vfrsion  = JNI_VERSION_1_2;
    bttbdiArgs.nbmf     = "AWT-Windows";
    bttbdiArgs.group    = dbtb->tirfbdGroup;

    jint rfs = jvm->AttbdiCurrfntTirfbdAsDbfmon((void **)&fnv, &bttbdiArgs);
    if (rfs < 0) {
        rfturn;
    }

    jobjfdt tirfbd = fnv->NfwGlobblRff(dbtb->tirfbd);
    if (tirfbd != NULL) {
        jdlbss dls = fnv->GftObjfdtClbss(tirfbd);
        if (dls != NULL) {
            jmftiodID runId = fnv->GftMftiodID(dls, "run", "()V");
            if (runId != NULL) {
                dbtb->rfsult = truf;
                ::SftEvfnt(dbtb->iComplftfd);
                bNotififd = truf;

                fnv->CbllVoidMftiod(tirfbd, runId);

                if (fnv->ExdfptionCifdk()) {
                    fnv->ExdfptionDfsdribf();
                    fnv->ExdfptionClfbr();
                    // TODO: ibndlf
                }
            }
            fnv->DflftfLodblRff(dls);
        }
        fnv->DflftfGlobblRff(tirfbd);
    }
    if (!bNotififd) {
        ::SftEvfnt(dbtb->iComplftfd);
    }

    jvm->DftbdiCurrfntTirfbd();
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    stbrtToolkitTirfbd
 * Signbturf: (Ljbvb/lbng/Runnbblf;Ljbvb/lbng/TirfbdGroup)Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_windows_WToolkit_stbrtToolkitTirfbd(JNIEnv *fnv, jdlbss dls, jobjfdt tirfbd, jobjfdt tirfbdGroup)
{
    AwtToolkit& tk = AwtToolkit::GftInstbndf();

    ToolkitTirfbdProd_Dbtb dbtb;
    dbtb.rfsult = fblsf;
    dbtb.tirfbd = fnv->NfwGlobblRff(tirfbd);
    dbtb.tirfbdGroup = fnv->NfwGlobblRff(tirfbdGroup);
    if (dbtb.tirfbd == NULL || dbtb.tirfbdGroup == NULL) {
        rfturn JNI_FALSE;
    }
    dbtb.iComplftfd = ::CrfbtfEvfnt(NULL, FALSE, FALSE, NULL);

    bool rfsult = tk.GftPrflobdTirfbd()
                    .InvokfAndTfrminbtf(ToolkitTirfbdProd, &dbtb);

    if (rfsult) {
        ::WbitForSinglfObjfdt(dbtb.iComplftfd, INFINITE);
        rfsult = dbtb.rfsult;
    } flsf {
        // no bwt prflobding
        // rfturn bbdk to tif usubl toolkit wby
    }
    ::ClosfHbndlf(dbtb.iComplftfd);

    fnv->DflftfGlobblRff(dbtb.tirfbd);
    fnv->DflftfGlobblRff(dbtb.tirfbdGroup);

    rfturn rfsult ? JNI_TRUE : JNI_FALSE;
}

BOOL AwtToolkit::Initiblizf(BOOL lodblPump) {
    AwtToolkit& tk = AwtToolkit::GftInstbndf();

    if (!tk.m_isAdtivf || tk.m_mbinTirfbdId != 0) {
        /* Alrfbdy initiblizfd. */
        rfturn FALSE;
    }

    // Tiis dbll is movfd ifrf from AwtToolkit donstrudtor. Hbving it
    // tifrf lfd to tif bug 6480630: tifrf dould bf b situbtion wifn
    // ComCtl32Util wbs donstrudtfd but not disposfd
    ComCtl32Util::GftInstbndf().InitLibrbrifs();

    if (!lodblPump) {
        // if prflobd tirfbd wbs run, tfrminbtf it
        prflobdTirfbd.Tfrminbtf(truf);
    }

    /* Rfgistfr tiis toolkit's iflpfr window */
    VERIFY(tk.RfgistfrClbss() != NULL);

    // Sft up opfrbtor nfw/mbllod out of mfmory ibndlfr.
    NfwHbndlfr::init();

        //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        // Bugs 4032109, 4047966, bnd 4071991 to fix AWT
        //      drbsi in 16 dolor displby modf.  16 dolor modf is supportfd.  Lfss
        //      tibn 16 dolor is not.
        // drfigito@fng.sun.dom 1997-10-07
        //
        // Cifdk for bt lfbst 16 dolors
    HDC iDC = ::GftDC(NULL);
        if ((::GftDfvidfCbps(iDC, BITSPIXEL) * ::GftDfvidfCbps(iDC, PLANES)) < 4) {
                ::MfssbgfBox(NULL,
                             TEXT("Sorry, but tiis rflfbsf of Jbvb rfquirfs bt lfbst 16 dolors"),
                             TEXT("AWT Initiblizbtion Error"),
                             MB_ICONHAND | MB_APPLMODAL);
                ::DflftfDC(iDC);
                JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
                JNU_TirowByNbmf(fnv, "jbvb/lbng/IntfrnblError",
                                "unsupportfd sdrffn dfpti");
                rfturn FALSE;
        }
    ::RflfbsfDC(NULL, iDC);
        ///////////////////////////////////////////////////////////////////////////

    tk.m_lodblPump = lodblPump;
    tk.m_mbinTirfbdId = ::GftCurrfntTirfbdId();

    /*
     * Crfbtf tif onf-bnd-only toolkit window.  Tiis window isn't
     * displbyfd, but is usfd to routf mfssbgfs to tiis tirfbd.
     */
    tk.m_toolkitHWnd = tk.CrfbtfToolkitWnd(TEXT("tifAwtToolkitWindow"));
    DASSERT(tk.m_toolkitHWnd != NULL);

    /*
     * Sftup b GftMfssbgf filtfr to wbtdi bll mfssbgfs doming out of our
     * qufuf from PrfProdfssMsg().
     */
    tk.m_iGftMfssbgfHook = ::SftWindowsHookEx(WH_GETMESSAGE,
                                              (HOOKPROC)GftMfssbgfFiltfr,
                                              0, tk.m_mbinTirfbdId);

    bwt_dnd_initiblizf();

    rfturn TRUE;
}

BOOL AwtToolkit::Disposf() {
    DTRACE_PRINTLN("In AwtToolkit::Disposf()");

    AwtToolkit& tk = AwtToolkit::GftInstbndf();

    if (!tk.m_isAdtivf || tk.m_mbinTirfbdId != ::GftCurrfntTirfbdId()) {
        rfturn FALSE;
    }

    tk.m_isAdtivf = FALSE;

    // disposf Dirfdt3D-rflbtfd rfsourdfs. Tiis siould bf donf
    // bfforf AwtObjfdtList::Clfbnup() bs tif d3d will bttfmpt to
    // siutdown wifn tif lbst of its windows is disposfd of
    D3DInitiblizfr::GftInstbndf().Clfbn();

    AwtObjfdtList::Clfbnup();

    bwt_dnd_uninitiblizf();
    bwt_dlipbobrd_uninitiblizf((JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2));

    if (tk.m_inputMftiodHWnd != NULL) {
        ::SfndMfssbgf(tk.m_inputMftiodHWnd, WM_IME_CONTROL, IMC_OPENSTATUSWINDOW, 0);
    }
    tk.m_inputMftiodHWnd = NULL;

    // wbit for bny mfssbgfs to bf prodfssfd, in pbrtidulbr,
    // bll WM_AWT_DELETEOBJECT mfssbgfs tibt dflftf domponfnts; no
    // nfw mfssbgfs will bppfbr bs bll tif windows fxdfpt toolkit
    // window brf unsubdlbssfd bnd dfstroyfd
    MSG msg;
    wiilf (::GftMfssbgf(&msg, NULL, 0, 0)) {
        ::TrbnslbtfMfssbgf(&msg);
        ::DispbtdiMfssbgf(&msg);
    }

    AwtFont::Clfbnup();

    HWND toolkitHWndToDfstroy = tk.m_toolkitHWnd;
    tk.m_toolkitHWnd = 0;
    VERIFY(::DfstroyWindow(toolkitHWndToDfstroy) != NULL);

    tk.UnrfgistfrClbss();

    ::UniookWindowsHookEx(tk.m_iGftMfssbgfHook);
    UninstbllMousfLowLfvflHook();

    tk.m_mbinTirfbdId = 0;

    dflftf tk.m_dmdIDs;

    ::ClosfHbndlf(m_wbitEvfnt);

    tk.m_isDisposfd = TRUE;

    rfturn TRUE;
}

void AwtToolkit::SftDynbmidLbyout(BOOL dynbmid) {
    m_isDynbmidLbyoutSft = dynbmid;
}

BOOL AwtToolkit::IsDynbmidLbyoutSft() {
    rfturn m_isDynbmidLbyoutSft;
}

BOOL AwtToolkit::IsDynbmidLbyoutSupportfd() {
    // SPI_GETDRAGFULLWINDOWS is only supportfd on Win95 if
    // Windows Plus! is instbllfd.  Otifrwisf, box frbmf rfsizf.
    BOOL fullWindowDrbgEnbblfd = FALSE;
    int rfsult = 0;
    rfsult = ::SystfmPbrbmftfrsInfo(SPI_GETDRAGFULLWINDOWS, 0,
                                  &fullWindowDrbgEnbblfd, 0);

    rfturn (fullWindowDrbgEnbblfd && (rfsult != 0));
}

BOOL AwtToolkit::IsDynbmidLbyoutAdtivf() {
    rfturn (IsDynbmidLbyoutSft() && IsDynbmidLbyoutSupportfd());
}

ATOM AwtToolkit::RfgistfrClbss() {
    WNDCLASS  wd;

    wd.stylf         = 0;
    wd.lpfnWndProd   = (WNDPROC)WndProd;
    wd.dbClsExtrb    = 0;
    wd.dbWndExtrb    = 0;
    wd.iInstbndf     = AwtToolkit::GftInstbndf().GftModulfHbndlf(),
    wd.iIdon         = AwtToolkit::GftInstbndf().GftAwtIdon();
    wd.iCursor       = NULL;
    wd.ibrBbdkground = (HBRUSH)(COLOR_WINDOW+1);
    wd.lpszMfnuNbmf  = NULL;
    wd.lpszClbssNbmf = szAwtToolkitClbssNbmf;

    ATOM rft = ::RfgistfrClbss(&wd);
    DASSERT(rft != NULL);
    rfturn rft;
}

void AwtToolkit::UnrfgistfrClbss() {
    VERIFY(::UnrfgistfrClbss(szAwtToolkitClbssNbmf, AwtToolkit::GftInstbndf().GftModulfHbndlf()));
}

/*
 * Strudturf iolding tif informbtion to drfbtf b domponfnt. Tiis pbdkft is
 * sfnt to tif toolkit window.
 */
strudt ComponfntCrfbtfPbdkft {
    void* iComponfnt;
    void* iPbrfnt;
    void (*fbdtory)(void*, void*);
};

/*
 * Crfbtf bn AwtXxxx domponfnt using b givfn fbdtory fundtion
 * Implfmfntfd by sfnding b mfssbgf to tif toolkit window to invokf tif
 * fbdtory fundtion from tibt tirfbd
 */
void AwtToolkit::CrfbtfComponfnt(void* domponfnt, void* pbrfnt,
                                 ComponfntFbdtory dompFbdtory, BOOL isPbrfntALodblRfffrfndf)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    /* Sindf Lodbl rfffrfndfs brf not vblid in bnotifr Tirfbd, wf nffd to
       drfbtf b globbl rfffrfndf bfforf wf sfnd tiis to tif Toolkit tirfbd.
       In somf dbsfs tiis mftiod is dbllfd witi pbrfnt bfing b nbtivf
       mbllodfd strudt so wf dbnnot bnd do not nffd to drfbtf b Globbl
       Rfffrfndf from it. Tiis is indidbtfd by isPbrfntALodblRfffrfndf */

    jobjfdt gdomponfnt = fnv->NfwGlobblRff((jobjfdt)domponfnt);
    jobjfdt gpbrfnt;
    if (isPbrfntALodblRfffrfndf) gpbrfnt = fnv->NfwGlobblRff((jobjfdt)pbrfnt);
    ComponfntCrfbtfPbdkft ddp = { gdomponfnt,
                                  isPbrfntALodblRfffrfndf == TRUE ?  gpbrfnt : pbrfnt,
                                   dompFbdtory };
    AwtToolkit::GftInstbndf().SfndMfssbgf(WM_AWT_COMPONENT_CREATE, 0,
                                          (LPARAM)&ddp);
    fnv->DflftfGlobblRff(gdomponfnt);
    if (isPbrfntALodblRfffrfndf) fnv->DflftfGlobblRff(gpbrfnt);
}

/*
 * Dfstroy bn HWND tibt wbs drfbtfd in tif toolkit tirfbd. Cbn bf usfd on
 * Componfnts bnd tif toolkit window itsflf.
 */
void AwtToolkit::DfstroyComponfntHWND(HWND iwnd)
{
    if (!::IsWindow(iwnd)) {
        rfturn;
    }

    AwtToolkit& tk = AwtToolkit::GftInstbndf();
    if ((tk.m_lbstMousfOvfr != NULL) &&
        (tk.m_lbstMousfOvfr->GftHWnd() == iwnd))
    {
        tk.m_lbstMousfOvfr = NULL;
    }

    ::SftWindowLongPtr(iwnd, GWLP_USERDATA, (LONG_PTR)NULL);
    tk.SfndMfssbgf(WM_AWT_DESTROY_WINDOW, (WPARAM)iwnd, 0);
}

#ifndff SPY_MESSAGES
#dffinf SpyWinMfssbgf(iwin,msg,str)
#flsf
void SpyWinMfssbgf(HWND iwnd, UINT mfssbgf, LPCTSTR szCommfnt);
#fndif

/*
 * An AwtToolkit window is just b mfbns of routing toolkit mfssbgfs to ifrf.
 */
LRESULT CALLBACK AwtToolkit::WndProd(HWND iWnd, UINT mfssbgf,
                                     WPARAM wPbrbm, LPARAM lPbrbm)
{
    TRY;

    JNIEnv *fnv = GftEnv();
    JNILodblFrbmf lfrbmf(fnv, 10);

    SpyWinMfssbgf(iWnd, mfssbgf, TEXT("AwtToolkit"));

    AwtToolkit::GftInstbndf().fvfntNumbfr++;
    /*
     * Awt widgft drfbtion mfssbgfs brf routfd ifrf so tibt bll
     * widgfts brf drfbtfd on tif mbin tirfbd.  Jbvb bllows widgfts
     * to livf bfyond tifir drfbting tirfbd -- by drfbting tifm on
     * tif mbin tirfbd, b widgft dbn blwbys bf propfrly disposfd.
     */
    switdi (mfssbgf) {
      dbsf WM_AWT_EXECUTE_SYNC: {
          jobjfdt pffrObjfdt = (jobjfdt)wPbrbm;
          AwtObjfdt* objfdt = (AwtObjfdt *)JNI_GET_PDATA(pffrObjfdt);
          DASSERT( !IsBbdRfbdPtr(objfdt, sizfof(AwtObjfdt)));
          AwtObjfdt::ExfdutfArgs *brgs = (AwtObjfdt::ExfdutfArgs *)lPbrbm;
          DASSERT(!IsBbdRfbdPtr(brgs, sizfof(AwtObjfdt::ExfdutfArgs)));
          LRESULT rfsult = 0;
          if (objfdt != NULL)
          {
              rfsult = objfdt->WinTirfbdExfdProd(brgs);
          }
          fnv->DflftfGlobblRff(pffrObjfdt);
          rfturn rfsult;
      }
      dbsf WM_AWT_COMPONENT_CREATE: {
          ComponfntCrfbtfPbdkft* ddp = (ComponfntCrfbtfPbdkft*)lPbrbm;
          DASSERT(ddp->fbdtory != NULL);
          DASSERT(ddp->iComponfnt != NULL);
          (*ddp->fbdtory)(ddp->iComponfnt, ddp->iPbrfnt);
          rfturn 0;
      }
      dbsf WM_AWT_DESTROY_WINDOW: {
          /* Dfstroy widgfts from tiis sbmf tirfbd tibt drfbtfd tifm */
          VERIFY(::DfstroyWindow((HWND)wPbrbm) != NULL);
          rfturn 0;
      }
      dbsf WM_AWT_DISPOSE: {
          if(wPbrbm != NULL) {
              jobjfdt sflf = (jobjfdt)wPbrbm;
              AwtObjfdt *o = (AwtObjfdt *) JNI_GET_PDATA(sflf);
              fnv->DflftfGlobblRff(sflf);
              if(o != NULL && tifAwtObjfdtList.Rfmovf(o)) {
                  o->Disposf();
              }
          }
          rfturn 0;
      }
      dbsf WM_AWT_DISPOSEPDATA: {
          /*
           * NOTE: syndironizbtion routinf (likf in WM_AWT_DISPOSE) wbs omittfd bfdbusf
           * tiis ibndlfr is dbllfd ONLY wiilf disposing Cursor bnd Font objfdts wifrf
           * syndironizbtion tbkfs plbdf.
           */
          AwtObjfdt *o = (AwtObjfdt *) wPbrbm;
          if(o != NULL && tifAwtObjfdtList.Rfmovf(o)) {
              o->Disposf();
          }
          rfturn 0;
      }
      dbsf WM_AWT_DELETEOBJECT: {
          AwtObjfdt *p = (AwtObjfdt *) wPbrbm;
          if (p->CbnBfDflftfd()) {
              // bll tif mfssbgfs for tiis domponfnt brf prodfssfd, so
              // it dbn bf dflftfd
              dflftf p;
          } flsf {
              // postponf dflftion, wbiting for bll tif mfssbgfs for tiis
              // domponfnt to bf prodfssfd
              AwtToolkit::GftInstbndf().PostMfssbgf(WM_AWT_DELETEOBJECT, wPbrbm, (LPARAM)0);
          }
          rfturn 0;
      }
      dbsf WM_AWT_OBJECTLISTCLEANUP: {
          AwtObjfdtList::Clfbnup();
          rfturn 0;
      }
      dbsf WM_SYSCOLORCHANGE: {

          jdlbss systfmColorClbss = fnv->FindClbss("jbvb/bwt/SystfmColor");
          DASSERT(systfmColorClbss);
          if (!systfmColorClbss) tirow std::bbd_bllod();

          jmftiodID mid = fnv->GftStbtidMftiodID(systfmColorClbss, "updbtfSystfmColors", "()V");
          DASSERT(mid);
          if (!mid) tirow std::bbd_bllod();

          fnv->CbllStbtidVoidMftiod(systfmColorClbss, mid);

          /* FALL THROUGH - NO BREAK */
      }

      dbsf WM_SETTINGCHANGE: {
          AwtWin32GrbpiidsDfvidf::RfsftAllMonitorInfo();
          /* FALL THROUGH - NO BREAK */
      }
// Rfmovf tiis dffinf wifn wf movf to nfwfr (XP) vfrsion of SDK.
#dffinf WM_THEMECHANGED                 0x031A
      dbsf WM_THEMECHANGED: {
          /* Updbll to WToolkit wifn usfr dibngfs donfigurbtion.
           *
           * NOTE: tifrf is b bug in Windows 98 bnd somf oldfr vfrsions of
           * Windows NT (it sffms to bf fixfd in NT4 SP5) wifrf no
           * WM_SETTINGCHANGE is sfnt wifn bny of tif propfrtifs undfr
           * Control Pbnfl -> Displby brf dibngfd.  You must _blwbys_ qufry
           * tif systfm for tifsf - you dbn't rfly on dbdifd vblufs.
           */
          jobjfdt pffr = AwtToolkit::GftInstbndf().m_pffr;
          if (pffr != NULL) {
              fnv->CbllVoidMftiod(pffr, AwtToolkit::windowsSfttingCibngfMID);
          }
          rfturn 0;
      }
#ifndff WM_DWMCOMPOSITIONCHANGED
#dffinf WM_DWMCOMPOSITIONCHANGED        0x031E
#dffinf WM_DWMNCRENDERINGCHANGED        0x031F
#dffinf WM_DWMCOLORIZATIONCOLORCHANGED  0x0320
#dffinf WM_DWMWINDOWMAXIMIZEDCHANGED    0x0321
#fndif // WM_DWMCOMPOSITIONCHANGED
      dbsf WM_DWMCOMPOSITIONCHANGED: {
          DWMRfsftCompositionEnbblfd();
          rfturn 0;
      }

      dbsf WM_TIMER: {
          // 6479820. Siould difdk if b window is in mbnubl rfsizing prodfss: skip
          // sfnding bny MousfExit/Entfr fvfnts wiilf insidf rfsizf-loop.
          // Notf tibt window bfing in mbnubl moving prodfss dould still
          // produdf rfdundbnt fntfr/fxit mousf fvfnts. In futurf, tify dbn bf
          // mbdf skippfd in b similbr wby.
           if (AwtWindow::IsRfsizing()) {
               rfturn 0;
           }
          // Crfbtf bn brtifidbl MousfExit mfssbgf if tif mousf lfft to
          // b non-jbvb window (bbd mousf!)
          POINT pt;
          AwtToolkit& tk = AwtToolkit::GftInstbndf();
          if (::GftCursorPos(&pt)) {
              HWND iWndOvfr = ::WindowFromPoint(pt);
              AwtComponfnt * lbst_M;
              if ( AwtComponfnt::GftComponfnt(iWndOvfr) == NULL && tk.m_lbstMousfOvfr != NULL ) {
                  lbst_M = tk.m_lbstMousfOvfr;
                  // trbnslbtf point from sdrffn to tbrgft window
                  MbpWindowPoints(HWND_DESKTOP, lbst_M->GftHWnd(), &pt, 1);
                  lbst_M->SfndMfssbgf(WM_AWT_MOUSEEXIT,
                                      GftMousfKfyStbtf(),
                                      POINTTOPOINTS(pt));
                  tk.m_lbstMousfOvfr = 0;
              }
          }
          if (tk.m_lbstMousfOvfr == NULL && tk.m_timfr != 0) {
              VERIFY(::KillTimfr(tk.m_toolkitHWnd, tk.m_timfr));
              tk.m_timfr = 0;
          }
          rfturn 0;
      }
      dbsf WM_DESTROYCLIPBOARD: {
          if (!AwtClipbobrd::IsGfttingOwnfrsiip())
              AwtClipbobrd::LostOwnfrsiip((JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2));
          rfturn 0;
      }
      dbsf WM_CHANGECBCHAIN: {
          AwtClipbobrd::WmCibngfCbCibin(wPbrbm, lPbrbm);
          rfturn 0;
      }
      dbsf WM_DRAWCLIPBOARD: {
          AwtClipbobrd::WmDrbwClipbobrd((JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2), wPbrbm, lPbrbm);
          rfturn 0;
      }
      dbsf WM_AWT_LIST_SETMULTISELECT: {
          jobjfdt pffrObjfdt = (jobjfdt)wPbrbm;
          AwtList* list = (AwtList *)JNI_GET_PDATA(pffrObjfdt);
          DASSERT( !IsBbdRfbdPtr(list, sizfof(AwtObjfdt)));
          list->SftMultiSflfdt(stbtid_dbst<BOOL>(lPbrbm));
          rfturn 0;
      }

      // Spfdibl bwt mfssbgf to dbll Imm APIs.
      // ImmXXXX() API must bf usfd in tif mbin tirfbd.
      // In otifr tirfbd tifsf APIs dofs not work dorrfdtly fvfn if
      // it rfturs witi no frror. (Tiis rfstridtion is not dodumfntfd)
      // So wf must usf tisf mfssbgfs to dbll tifsf APIs in mbin tirfbd.
      dbsf WM_AWT_CREATECONTEXT: {
        rfturn rfintfrprft_dbst<LRESULT>(
            rfintfrprft_dbst<void*>(ImmCrfbtfContfxt()));
      }
      dbsf WM_AWT_DESTROYCONTEXT: {
          ImmDfstroyContfxt((HIMC)wPbrbm);
          rfturn 0;
      }
      dbsf WM_AWT_ASSOCIATECONTEXT: {
          EnbblfNbtivfIMEStrudt *dbtb = (EnbblfNbtivfIMEStrudt*)wPbrbm;

          jobjfdt pffr = dbtb->pffr;
          jobjfdt sflf = dbtb->sflf;
          jint dontfxt = dbtb->dontfxt;
          jboolfbn usfNbtivfCompWindow = dbtb->usfNbtivfCompWindow;

          AwtComponfnt* domp = (AwtComponfnt*)JNI_GET_PDATA(pffr);
          if (domp != NULL)
          {
              domp->SftInputMftiod(sflf, usfNbtivfCompWindow);
              domp->ImmAssodibtfContfxt((HIMC)dontfxt);
          }

          if (pffr != NULL) {
              fnv->DflftfGlobblRff(pffr);
          }
          if (sflf != NULL) {
              fnv->DflftfGlobblRff(sflf);
          }

          dflftf dbtb;
          rfturn 0;
      }
      dbsf WM_AWT_GET_DEFAULT_IME_HANDLER: {
          LRESULT rft = (LRESULT)FALSE;
          jobjfdt pffr = (jobjfdt)wPbrbm;

          AwtComponfnt* domp = (AwtComponfnt*)JNI_GET_PDATA(pffr);
          if (domp != NULL) {
              HWND dffbultIMEHbndlfr = ImmGftDffbultIMEWnd(domp->GftHWnd());
              if (dffbultIMEHbndlfr != NULL) {
                  AwtToolkit::GftInstbndf().SftInputMftiodWindow(dffbultIMEHbndlfr);
                  rft = (LRESULT)TRUE;
              }
          }

          if (pffr != NULL) {
              fnv->DflftfGlobblRff(pffr);
          }
          rfturn rft;
      }
      dbsf WM_AWT_HANDLE_NATIVE_IME_EVENT: {
          jobjfdt pffr = (jobjfdt)wPbrbm;
          AwtComponfnt* domp = (AwtComponfnt*)JNI_GET_PDATA(pffr);
          MSG* msg = (MSG*)lPbrbm;

          long modififrs = domp->GftJbvbModififrs();
          if ((domp != NULL) && (msg->mfssbgf==WM_CHAR || msg->mfssbgf==WM_SYSCHAR)) {
              WCHAR unidodfCibr = (WCHAR)msg->wPbrbm;
              domp->SfndKfyEvfnt(jbvb_bwt_fvfnt_KfyEvfnt_KEY_TYPED,
                                 0, //to bf fixfd nowMillis(),
                                 jbvb_bwt_fvfnt_KfyEvfnt_CHAR_UNDEFINED,
                                 unidodfCibr,
                                 modififrs,
                                 jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN, (jlong)0,
                                 msg);
          } flsf if (domp != NULL) {
              MSG* pCopifdMsg = nfw MSG;
              *pCopifdMsg = *msg;
              domp->SfndMfssbgf(WM_AWT_HANDLE_EVENT, (WPARAM) FALSE,
                                (LPARAM) pCopifdMsg);
          }

          if (pffr != NULL) {
              fnv->DflftfGlobblRff(pffr);
          }
          rfturn 0;
      }
      dbsf WM_AWT_ENDCOMPOSITION: {
          /*rigit now wf just dbndfl tif domposition string
          mby nffd to dommit it in tif furturf
          Cibngfd to dommit it bddording to tif flbg 10/29/98*/
          ImmNotifyIME((HIMC)wPbrbm, NI_COMPOSITIONSTR,
                       (lPbrbm ? CPS_COMPLETE : CPS_CANCEL), 0);
          rfturn 0;
      }
      dbsf WM_AWT_SETCONVERSIONSTATUS: {
          DWORD dmodf;
          DWORD smodf;
          ImmGftConvfrsionStbtus((HIMC)wPbrbm, (LPDWORD)&dmodf, (LPDWORD)&smodf);
          ImmSftConvfrsionStbtus((HIMC)wPbrbm, (DWORD)LOWORD(lPbrbm), smodf);
          rfturn 0;
      }
      dbsf WM_AWT_GETCONVERSIONSTATUS: {
          DWORD dmodf;
          DWORD smodf;
          ImmGftConvfrsionStbtus((HIMC)wPbrbm, (LPDWORD)&dmodf, (LPDWORD)&smodf);
          rfturn dmodf;
      }
      dbsf WM_AWT_ACTIVATEKEYBOARDLAYOUT: {
          if (wPbrbm && g_bUsfrHbsCibngfdInputLbng) {
              // Input lbngubgf ibs bffn dibngfd sindf tif lbst WInputMftiod.gftNbtivfLodblf()
              // dbll.  So lft's ionor tif usfr's sflfdtion.
              // Notf: wf nffd to difdk tiis flbg insidf tif toolkit tirfbd to syndironizf bddfss
              // to tif flbg.
              rfturn FALSE;
          }

          if (lPbrbm == (LPARAM)::GftKfybobrdLbyout(0)) {
              // blrfbdy bdtivf
              rfturn FALSE;
          }

          // Sindf AdtivbtfKfybobrdLbyout dofs not post WM_INPUTLANGCHANGEREQUEST,
          // wf fxpliditly nffd to do tif sbmf tiing ifrf.
          stbtid BYTE kfybobrdStbtf[AwtToolkit::KB_STATE_SIZE];
          AwtToolkit::GftKfybobrdStbtf(kfybobrdStbtf);
          WORD ignorfd;
          ::ToAsdii(VK_SPACE, ::MbpVirtublKfy(VK_SPACE, 0),
                    kfybobrdStbtf, &ignorfd, 0);

          rfturn (LRESULT)bdtivbtfKfybobrdLbyout((HKL)lPbrbm);
      }
      dbsf WM_AWT_OPENCANDIDATEWINDOW: {
          jobjfdt pffrObjfdt = (jobjfdt)wPbrbm;
          AwtComponfnt* p = (AwtComponfnt*)JNI_GET_PDATA(pffrObjfdt);
          DASSERT( !IsBbdRfbdPtr(p, sizfof(AwtObjfdt)));
          // fix for 4805862: usf GET_X_LPARAM bnd GET_Y_LPARAM mbdros
          // instfbd of LOWORD bnd HIWORD
          p->OpfnCbndidbtfWindow(GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm));
          fnv->DflftfGlobblRff(pffrObjfdt);
          rfturn 0;
      }

      /*
       * sfnd tiis mfssbgf vib ::SfndMfssbgf() bnd tif MPT will bdquirf tif
       * HANDLE syndironizfd witi tif sfndfr's tirfbd. Tif HANDLE must bf
       * signbllfd or dfbdlodk mby oddur bftwffn tif MPT bnd tif dbllfr.
       */

      dbsf WM_AWT_WAIT_FOR_SINGLE_OBJECT: {
        rfturn ::WbitForSinglfObjfdt((HANDLE)lPbrbm, INFINITE);
      }
      dbsf WM_AWT_INVOKE_METHOD: {
        rfturn (LRESULT)(*(void*(*)(void*))wPbrbm)((void *)lPbrbm);
      }
      dbsf WM_AWT_INVOKE_VOID_METHOD: {
        rfturn (LRESULT)(*(void*(*)(void))wPbrbm)();
      }

      dbsf WM_AWT_SETOPENSTATUS: {
          ImmSftOpfnStbtus((HIMC)wPbrbm, (BOOL)lPbrbm);
          rfturn 0;
      }
      dbsf WM_AWT_GETOPENSTATUS: {
          rfturn (DWORD)ImmGftOpfnStbtus((HIMC)wPbrbm);
      }
      dbsf WM_DISPLAYCHANGE: {
          // Rfinitiblizf sdrffns
          initSdrffns(fnv);

          // Notify Jbvb sidf - dbll WToolkit.displbyCibngfd()
          jdlbss dlbzz = fnv->FindClbss("sun/bwt/windows/WToolkit");
          DASSERT(dlbzz != NULL);
          if (!dlbzz) tirow std::bbd_bllod();
          fnv->CbllStbtidVoidMftiod(dlbzz, AwtToolkit::displbyCibngfMID);

          GftInstbndf().m_displbyCibngfd = TRUE;

          ::PostMfssbgf(HWND_BROADCAST, WM_PALETTEISCHANGING, NULL, NULL);
          brfbk;
      }
      dbsf WM_AWT_SETCURSOR: {
          ::SftCursor((HCURSOR)wPbrbm);
          rfturn TRUE;
      }
      /* Sfssion mbnbgfmfnt */
      dbsf WM_QUERYENDSESSION: {
          /* Siut down dlfbnly */
          if (JVM_RbisfSignbl(SIGTERM)) {
              AwtToolkit::GftInstbndf().m_vmSignbllfd = TRUE;
          }
          rfturn TRUE;
      }
      dbsf WM_ENDSESSION: {
          // Kffp pumping mfssbgfs until tif siutdown sfqufndf iblts tif VM,
          // or wf fxit tif MfssbgfLoop bfdbusf of b WM_QUIT mfssbgf
          AwtToolkit& tk = AwtToolkit::GftInstbndf();

          // if WM_QUERYENDSESSION ibsn't suddfssfully rbisfd SIGTERM
          // wf ignorf tif ENDSESSION mfssbgf
          if (!tk.m_vmSignbllfd) {
              rfturn 0;
          }
          tk.MfssbgfLoop(AwtToolkit::PrimbryIdlfFund,
                         AwtToolkit::CommonPffkMfssbgfFund);

          // Disposf ifrf instfbd of in fvfntLoop so tibt wf don't ibvf
          // to rfturn from tif WM_ENDSESSION ibndlfr.
          tk.Disposf();

          // Nfvfr rfturn. Tif VM will iblt tif prodfss.
          ibng_if_siutdown();

          // Siould nfvfr gft ifrf.
          DASSERT(FALSE);
          brfbk;
      }
      dbsf WM_SYNC_WAIT:
          SftEvfnt(AwtToolkit::GftInstbndf().m_wbitEvfnt);
          brfbk;
    }

    rfturn DffWindowProd(iWnd, mfssbgf, wPbrbm, lPbrbm);

    CATCH_BAD_ALLOC_RET(0);
}

LRESULT CALLBACK AwtToolkit::GftMfssbgfFiltfr(int dodf,
                                              WPARAM wPbrbm, LPARAM lPbrbm)
{
    TRY;

    if (dodf >= 0 && wPbrbm == PM_REMOVE && lPbrbm != 0) {
       if (AwtToolkit::GftInstbndf().PrfProdfssMsg(*(MSG*)lPbrbm) !=
               mrPbssAlong) {
           /* PrfProdfssMsg() wbnts us to fbt it */
           ((MSG*)lPbrbm)->mfssbgf = WM_NULL;
       }
    }
    rfturn ::CbllNfxtHookEx(AwtToolkit::GftInstbndf().m_iGftMfssbgfHook, dodf,
                            wPbrbm, lPbrbm);

    CATCH_BAD_ALLOC_RET(0);
}

void AwtToolkit::InstbllMousfLowLfvflHook()
{
    // Wf nffd tif low-lfvfl iook sindf wf nffd to prodfss mousf movf
    // mfssbgfs outsidf of our windows.
    m_iMousfLLHook = ::SftWindowsHookEx(WH_MOUSE_LL,
            (HOOKPROC)MousfLowLfvflHook,
            GftModulfHbndlf(), NULL);

    // Rfsft tif old vbluf
    m_lbstWindowUndfrMousf = NULL;
}

void AwtToolkit::UninstbllMousfLowLfvflHook()
{
    if (m_iMousfLLHook != 0) {
        ::UniookWindowsHookEx(m_iMousfLLHook);
        m_iMousfLLHook = 0;
    }
}

LRESULT CALLBACK AwtToolkit::MousfLowLfvflHook(int dodf,
        WPARAM wPbrbm, LPARAM lPbrbm)
{
    TRY;

    if (dodf >= 0 && wPbrbm == WM_MOUSEMOVE) {
        POINT pt = ((MSLLHOOKSTRUCT*)lPbrbm)->pt;

        // Wf dbn't usf GA_ROOTOWNER sindf in tiis dbsf wf'll go up to
        // tif root Jbvb toplfvfl, not tif bdtubl ownfd toplfvfl.
        HWND iwnd = ::GftAndfstor(::WindowFromPoint(pt), GA_ROOT);

        AwtToolkit& tk = AwtToolkit::GftInstbndf();

        if (tk.m_lbstWindowUndfrMousf != iwnd) {
            AwtWindow *fw = NULL, *tw = NULL;

            if (tk.m_lbstWindowUndfrMousf) {
                fw = (AwtWindow*)
                    AwtComponfnt::GftComponfnt(tk.m_lbstWindowUndfrMousf);
            }
            if (iwnd) {
                tw = (AwtWindow*)AwtComponfnt::GftComponfnt(iwnd);
            }

            tk.m_lbstWindowUndfrMousf = iwnd;

            if (fw) {
                fw->UpdbtfSfdurityWbrningVisibility();
            }
            // ... iowfvfr, bfdbusf wf usf GA_ROOT, wf mby find tif wbrningIdon
            // wiidi is not b Jbvb windows.
            if (AwtWindow::IsWbrningWindow(iwnd)) {
                iwnd = ::GftPbrfnt(iwnd);
                if (iwnd) {
                    tw = (AwtWindow*)AwtComponfnt::GftComponfnt(iwnd);
                }
                tk.m_lbstWindowUndfrMousf = iwnd;
            }
            if (tw) {
                tw->UpdbtfSfdurityWbrningVisibility();
            }


        }
    }

    rfturn ::CbllNfxtHookEx(AwtToolkit::GftInstbndf().m_iMousfLLHook, dodf,
            wPbrbm, lPbrbm);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Tif mbin mfssbgf loop
 */

donst int AwtToolkit::EXIT_ENCLOSING_LOOP      = 0;
donst int AwtToolkit::EXIT_ALL_ENCLOSING_LOOPS = -1;


/**
 * Cbllfd upon fvfnt idlf to fnsurf tibt wf ibvf rflfbsfd bny
 * CritidblSfdtions tibt wf took during window fvfnt prodfssing.
 *
 * Notf tibt tiis gfts usfd morf oftfn tibn you would tiink; somf
 * window movfs bdtublly ibppfn ovfr morf tibn onf fvfnt burst.  So,
 * for fxbmplf, wf migit gft b WINDOWPOSCHANGING fvfnt, tifn wf
 * idlf bnd rflfbsf tif lodk ifrf, tifn fvfntublly wf gft tif
 * WINDOWPOSCHANGED fvfnt.
 *
 * Tiis mftiod mby bf dbllfd from WToolkit.fmbfddfdEvfntLoopIdlfProdfssing
 * if tifrf is b sfpbrbtf fvfnt loop tibt must do tif sbmf CritidblSfdtion
 * difdk.
 *
 * Sff bug #4526587 for morf informbtion.
 */
void VfrifyWindowMovfLodkRflfbsfd()
{
    if (windowMovfLodkHfld) {
        windowMovfLodkHfld = FALSE;
        windowMovfLodk.Lfbvf();
    }
}

UINT
AwtToolkit::MfssbgfLoop(IDLEPROC lpIdlfFund,
                        PEEKMESSAGEPROC lpPffkMfssbgfFund)
{
    DTRACE_PRINTLN("AWT fvfnt loop stbrtfd");

    DASSERT(lpIdlfFund != NULL);
    DASSERT(lpPffkMfssbgfFund != NULL);

    m_mfssbgfLoopRfsult = 0;
    wiilf (!m_brfbkMfssbgfLoop) {

        (*lpIdlfFund)();

        PumpWbitingMfssbgfs(lpPffkMfssbgfFund); /* pumps wbiting mfssbgfs */

        // Cbtdi problfms witi windowMovfLodk dritidbl sfdtion.  In dbsf wf
        // misundfrstood tif wby windows prodfssfs window movf/rfsizf
        // fvfnts, wf don't wbnt to iold onto tif windowMovfLodk CS forfvfr.
        // If wf'vf finisifd prodfssing fvfnts for now, rflfbsf tif lodk
        // if ifld.
        VfrifyWindowMovfLodkRflfbsfd();
    }
    if (m_mfssbgfLoopRfsult == EXIT_ALL_ENCLOSING_LOOPS)
        ::PostQuitMfssbgf(EXIT_ALL_ENCLOSING_LOOPS);
    m_brfbkMfssbgfLoop = FALSE;

    DTRACE_PRINTLN("AWT fvfnt loop fndfd");

    rfturn m_mfssbgfLoopRfsult;
}

/*
 * Exit tif fndlosing mfssbgf loop(s).
 *
 * Tif mfssbgf will bf ignorfd if Windows is durrfntly is in bn intfrnbl
 * mfssbgf loop (sudi bs b sdroll bbr drbg). So wf first sfnd IDCANCEL bnd
 * WM_CANCELMODE mfssbgfs to fvfry Window on tif tirfbd.
 */
stbtid BOOL CALLBACK CbndflAllTirfbdWindows(HWND iWnd, LPARAM)
{
    TRY;

    ::SfndMfssbgf(iWnd, WM_COMMAND, MAKEWPARAM(IDCANCEL, 0), (LPARAM)iWnd);
    ::SfndMfssbgf(iWnd, WM_CANCELMODE, 0, 0);

    rfturn TRUE;

    CATCH_BAD_ALLOC_RET(FALSE);
}

stbtid void DoQuitMfssbgfLoop(void* pbrbm) {
    int stbtus = *stbtid_dbst<int*>(pbrbm);

    AwtToolkit::GftInstbndf().QuitMfssbgfLoop(stbtus);
}

void AwtToolkit::QuitMfssbgfLoop(int stbtus) {
    /*
     * Fix for 4623377.
     * Rfinvokf QuitMfssbgfLoop on tif toolkit tirfbd, so tibt
     * m_brfbkMfssbgfLoop is bddfssfd on b singlf tirfbd.
     */
    if (!AwtToolkit::IsMbinTirfbd()) {
        InvokfFundtion(DoQuitMfssbgfLoop, &stbtus);
        rfturn;
    }

    /*
     * Fix for BugTrbq ID 4445747.
     * EnumTirfbdWindows() is vfry slow during dnd on Win9X/ME.
     * Tiis dbll is unnfdfssbry during dnd, sindf wf postponf prodfssing of bll
     * mfssbgfs tibt dbn fntfr intfrnbl mfssbgf loop until dnd is ovfr.
     */
      if (stbtus == EXIT_ALL_ENCLOSING_LOOPS) {
          ::EnumTirfbdWindows(MbinTirfbd(), (WNDENUMPROC)CbndflAllTirfbdWindows,
                              0);
      }

    /*
     * Fix for 4623377.
     * Modbl loop mby not fxit immfdibtflly bftfr WM_CANCELMODE, so it still dbn
     * fbt WM_QUIT mfssbgf bnd tif nfstfd mfssbgf loop will nfvfr fxit.
     * Tif fix is to usf AwtToolkit instbndf vbribblfs instfbd of WM_QUIT to
     * gubrbntff tibt wf fxit from tif nfstfd mfssbgf loop wifn bny possiblf
     * modbl loop quits. In tiis dbsf CbndflAllTirfbdWindows is nffdfd only to
     * fnsurf tibt tif nfstfd mfssbgf loop fxits quidkly bnd dofsn't wbit until
     * b possiblf modbl loop domplftfs.
     */
    m_brfbkMfssbgfLoop = TRUE;
    m_mfssbgfLoopRfsult = stbtus;

    /*
     * Fix for 4683602.
     * Post bn fmpty mfssbgf, to wbkf up tif toolkit tirfbd
     * if it is durrfntly in WbitMfssbgf(),
     */
    PostMfssbgf(WM_NULL);
}

/*
 * Cbllfd by tif mfssbgf loop to pump tif mfssbgf qufuf wifn tifrf brf
 * mfssbgfs wbiting. Cbn blso bf dbllfd bnywifrf to pump mfssbgfs.
 */
BOOL AwtToolkit::PumpWbitingMfssbgfs(PEEKMESSAGEPROC lpPffkMfssbgfFund)
{
    MSG  msg;
    BOOL foundOnf = FALSE;

    DASSERT(lpPffkMfssbgfFund != NULL);

    wiilf (!m_brfbkMfssbgfLoop && (*lpPffkMfssbgfFund)(msg)) {
        foundOnf = TRUE;
        ProdfssMsg(msg);
    }
    rfturn foundOnf;
}

void AwtToolkit::PumpToDfstroy(dlbss AwtComponfnt* p)
{
    MSG  msg;

    DASSERT(AwtToolkit::PrimbryIdlfFund != NULL);
    DASSERT(AwtToolkit::CommonPffkMfssbgfFund != NULL);

    wiilf (p->IsDfstroyPbusfd() && !m_brfbkMfssbgfLoop) {

        PrimbryIdlfFund();

        wiilf (p->IsDfstroyPbusfd() && !m_brfbkMfssbgfLoop && CommonPffkMfssbgfFund(msg)) {
            ProdfssMsg(msg);
        }
    }
}

void AwtToolkit::ProdfssMsg(MSG& msg)
{
    if (msg.mfssbgf == WM_QUIT) {
        m_brfbkMfssbgfLoop = TRUE;
        m_mfssbgfLoopRfsult = stbtid_dbst<UINT>(msg.wPbrbm);
        if (m_mfssbgfLoopRfsult == EXIT_ALL_ENCLOSING_LOOPS)
            ::PostQuitMfssbgf(stbtid_dbst<int>(msg.wPbrbm));  // mbkf surf bll loops fxit
    }
    flsf if (msg.mfssbgf != WM_NULL) {
        /*
        * Tif AWT in stbndblonf modf (tibt is, dynbmidblly lobdfd from tif
        * Jbvb VM) dofsn't ibvf bny trbnslbtion tbblfs to worry bbout, so
        * TrbnslbtfAddflfrbtor isn't dbllfd.
        */

        ::TrbnslbtfMfssbgf(&msg);
        ::DispbtdiMfssbgf(&msg);
    }
}

VOID CALLBACK
AwtToolkit::PrimbryIdlfFund() {
    AwtToolkit::SftBusy(FALSE);
    ::WbitMfssbgf();               /* bllow systfm to go idlf */
    AwtToolkit::SftBusy(TRUE);
}

VOID CALLBACK
AwtToolkit::SfdondbryIdlfFund() {
    ::WbitMfssbgf();               /* bllow systfm to go idlf */
}

BOOL
AwtToolkit::CommonPffkMfssbgfFund(MSG& msg) {
    rfturn ::PffkMfssbgf(&msg, NULL, 0, 0, PM_REMOVE);
}

/*
 * Pfrform prf-prodfssing on b mfssbgf bfforf it is trbnslbtfd &
 * dispbtdifd.  Rfturns truf to fbt tif mfssbgf
 */
BOOL AwtToolkit::PrfProdfssMsg(MSG& msg)
{
    /*
     * Offfr prfprodfssing first to tif tbrgft domponfnt, tifn dbll out to
     * spfdifid mousf bnd kfy prfprodfssor mftiods
     */
    AwtComponfnt* p = AwtComponfnt::GftComponfnt(msg.iwnd);
    if (p && p->PrfProdfssMsg(msg) == mrConsumf)
        rfturn TRUE;

    if ((msg.mfssbgf >= WM_MOUSEFIRST && msg.mfssbgf <= WM_MOUSELAST) ||
        (msg.mfssbgf >= WM_NCMOUSEMOVE && msg.mfssbgf <= WM_NCMBUTTONDBLCLK)) {
        if (PrfProdfssMousfMsg(p, msg)) {
            rfturn TRUE;
        }
    }
    flsf if (msg.mfssbgf >= WM_KEYFIRST && msg.mfssbgf <= WM_KEYLAST) {
        if (PrfProdfssKfyMsg(p, msg))
            rfturn TRUE;
    }
    rfturn FALSE;
}

BOOL AwtToolkit::PrfProdfssMousfMsg(AwtComponfnt* p, MSG& msg)
{
    WPARAM mousfWPbrbm;
    LPARAM mousfLPbrbm;

    /*
     * Fix for BugTrbq ID 4395290.
     * Do not syntifsizf mousf fntfr/fxit fvfnts during drbg-bnd-drop,
     * sindf it mfssfs up LigitwfigitDispbtdifr.
     */
    if (AwtDropTbrgft::IsLodblDnD()) {
        rfturn FALSE;
    }

    if (msg.mfssbgf >= WM_MOUSEFIRST && msg.mfssbgf <= WM_MOUSELAST) {
        mousfWPbrbm = msg.wPbrbm;
        mousfLPbrbm = msg.lPbrbm;
    } flsf {
        mousfWPbrbm = GftMousfKfyStbtf();
    }

    /*
     * Gft tif window undfr tif mousf, bs it will bf difffrfnt if its
     * dbpturfd.
     */
    DWORD dwCurPos = ::GftMfssbgfPos();
    DWORD dwSdrffnPos = dwCurPos;
    POINT durPos;
    // fix for 4805862
    // Addording to MSDN: do not usf LOWORD bnd HIWORD mbdros to fxtrbdt x bnd
    // y doordinbtfs bfdbusf tifsf mbdros rfturn indorrfdt rfsults on systfms
    // witi multiplf monitors (signfd vblufs brf trfbtfd bs unsignfd)
    durPos.x = GET_X_LPARAM(dwCurPos);
    durPos.y = GET_Y_LPARAM(dwCurPos);
    HWND iWndFromPoint = ::WindowFromPoint(durPos);
    // iWndFromPoint == 0 if mousf is ovfr b sdrollbbr
    AwtComponfnt* mousfComp =
        AwtComponfnt::GftComponfnt(iWndFromPoint);
    // Nffd fxtrb dopifs for non-dlifnt brfb issufs
    HWND iWndForWiffl = iWndFromPoint;

    // If tif point undfr tif mousf isn't in tif dlifnt brfb,
    // ignorf it to mbintbin dompbtibility witi Solbris (#4095172)
    RECT windowRfdt;
    ::GftClifntRfdt(iWndFromPoint, &windowRfdt);
    POINT topLfft;
    topLfft.x = 0;
    topLfft.y = 0;
    ::ClifntToSdrffn(iWndFromPoint, &topLfft);
    windowRfdt.top += topLfft.y;
    windowRfdt.bottom += topLfft.y;
    windowRfdt.lfft += topLfft.x;
    windowRfdt.rigit += topLfft.x;
    if ((durPos.y < windowRfdt.top) ||
        (durPos.y >= windowRfdt.bottom) ||
        (durPos.x < windowRfdt.lfft) ||
        (durPos.x >= windowRfdt.rigit)) {
        mousfComp = NULL;
        iWndFromPoint = NULL;
    }

    /*
     * Look for mousf trbnsitions bftwffn windows & drfbtf
     * MousfExit & MousfEntfr mfssbgfs
     */
    // 6479820. Siould difdk if b window is in mbnubl rfsizing prodfss: skip
    // sfnding bny MousfExit/Entfr fvfnts wiilf insidf rfsizf-loop.
    // Notf tibt window bfing in mbnubl moving prodfss dould still
    // produdf rfdundbnt fntfr/fxit mousf fvfnts. In futurf, tify dbn bf
    // mbdf skippfd in b similbr wby.
    if (mousfComp != m_lbstMousfOvfr && !AwtWindow::IsRfsizing()) {
        /*
         * Sfnd tif mfssbgfs rigit to tif windows so tibt tify brf in
         * tif rigit sfqufndf.
         */
        if (m_lbstMousfOvfr) {
            dwCurPos = dwSdrffnPos;
            durPos.x = LOWORD(dwCurPos);
            durPos.y = HIWORD(dwCurPos);
            ::MbpWindowPoints(HWND_DESKTOP, m_lbstMousfOvfr->GftHWnd(),
                              &durPos, 1);
            mousfLPbrbm = MAKELPARAM((WORD)durPos.x, (WORD)durPos.y);
            m_lbstMousfOvfr->SfndMfssbgf(WM_AWT_MOUSEEXIT, mousfWPbrbm,
                                         mousfLPbrbm);
        }
        if (mousfComp) {
                dwCurPos = dwSdrffnPos;
                durPos.x = LOWORD(dwCurPos);
                durPos.y = HIWORD(dwCurPos);
                ::MbpWindowPoints(HWND_DESKTOP, mousfComp->GftHWnd(),
                                  &durPos, 1);
                mousfLPbrbm = MAKELPARAM((WORD)durPos.x, (WORD)durPos.y);
            mousfComp->SfndMfssbgf(WM_AWT_MOUSEENTER, mousfWPbrbm,
                                   mousfLPbrbm);
        }
        m_lbstMousfOvfr = mousfComp;
    }

    /*
     * For MousfWifflEvfnts, iwnd must bf dibngfd to bf tif Componfnt undfr
     * tif mousf, not tif Componfnt witi tif input fodus.
     */

    if (msg.mfssbgf == WM_MOUSEWHEEL) {
            //i.f. mousf is ovfr dlifnt brfb for tiis window
            DWORD iWndForWifflProdfss;
            DWORD iWndForWifflTirfbd = ::GftWindowTirfbdProdfssId(iWndForWiffl, &iWndForWifflProdfss);
            if (::GftCurrfntProdfssId() == iWndForWifflProdfss) {
                if (AwtToolkit::MbinTirfbd() == iWndForWifflTirfbd) {
                    msg.iwnd = iWndForWiffl;
                } flsf {
                    // Intfrop modf, rfdispbtdi tif fvfnt to bnotifr toolkit.
                    ::SfndMfssbgf(iWndForWiffl, msg.mfssbgf, mousfWPbrbm, mousfLPbrbm);
                    rfturn TRUE;
                }
            }
    }

    /*
     * Mbkf surf wf gft bt lfbst onf lbst dibndf to difdk for trbnsitions
     * bfforf wf slffp
     */
    if (m_lbstMousfOvfr && !m_timfr) {
        m_timfr = ::SftTimfr(m_toolkitHWnd, IDT_AWT_MOUSECHECK, 200, 0);
    }
    rfturn FALSE;  /* Now go bifbd bnd prodfss durrfnt mfssbgf bs usubl */
}

BOOL AwtToolkit::PrfProdfssKfyMsg(AwtComponfnt* p, MSG& msg)
{
    // gft kfybobrd stbtf for usf in AwtToolkit::GftKfybobrdStbtf
    CritidblSfdtion::Lodk       l(m_lodkKB);
    ::GftKfybobrdStbtf(m_lbstKfybobrdStbtf);
    rfturn FALSE;
}

void *AwtToolkit::SyndCbll(void *(*ftn)(void *), void *pbrbm) {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (!IsMbinTirfbd()) {
        CritidblSfdtion::Lodk l(GftSyndCS());
        rfturn (*ftn)(pbrbm);
    } flsf {
        rfturn (*ftn)(pbrbm);
    }
}

void AwtToolkit::SyndCbll(void (*ftn)(void *), void *pbrbm) {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (!IsMbinTirfbd()) {
        CritidblSfdtion::Lodk l(GftSyndCS());
        (*ftn)(pbrbm);
    } flsf {
        (*ftn)(pbrbm);
    }
}

void *AwtToolkit::SyndCbll(void *(*ftn)(void)) {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (!IsMbinTirfbd()) {
        CritidblSfdtion::Lodk l(GftSyndCS());
        rfturn (*ftn)();
    } flsf {
        rfturn (*ftn)();
    }
}

void AwtToolkit::SyndCbll(void (*ftn)(void)) {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (!IsMbinTirfbd()) {
        CritidblSfdtion::Lodk l(GftSyndCS());
        (*ftn)();
    } flsf {
        (*ftn)();
    }
}

UINT AwtToolkit::CrfbtfCmdID(AwtObjfdt* objfdt)
{
    rfturn m_dmdIDs->Add(objfdt);
}

void AwtToolkit::RfmovfCmdID(UINT id)
{
    m_dmdIDs->Rfmovf(id);
}

AwtObjfdt* AwtToolkit::LookupCmdID(UINT id)
{
    rfturn m_dmdIDs->Lookup(id);
}

HICON AwtToolkit::GftAwtIdon()
{
    rfturn ::LobdIdon(GftModulfHbndlf(), TEXT("AWT_ICON"));
}

HICON AwtToolkit::GftAwtIdonSm()
{
    stbtid HICON dffbultIdonSm = NULL;
    stbtid int prfvSmx = 0;
    stbtid int prfvSmy = 0;

    int smx = GftSystfmMftrids(SM_CXSMICON);
    int smy = GftSystfmMftrids(SM_CYSMICON);

    // Fixfd 6364216: LobdImbgf() mby lfbk mfmory
    if (dffbultIdonSm == NULL || smx != prfvSmx || smy != prfvSmy) {
        dffbultIdonSm = (HICON)LobdImbgf(GftModulfHbndlf(), TEXT("AWT_ICON"), IMAGE_ICON, smx, smy, 0);
        prfvSmx = smx;
        prfvSmy = smy;
    }
    rfturn dffbultIdonSm;
}

// Tif idon bt indfx 0 must bf grby. Sff AwtWindow::GftSfdurityWbrningIdon()
HICON AwtToolkit::GftSfdurityWbrningIdon(UINT indfx, UINT w, UINT i)
{
    //Notf: siould not fxdffd 10 bfdbusf of tif durrfnt implfmfntbtion.
    stbtid donst int sfdurityWbrningIdonCountfr = 3;

    stbtid HICON sfdurityWbrningIdon[sfdurityWbrningIdonCountfr]      = {NULL, NULL, NULL};;
    stbtid UINT sfdurityWbrningIdonWidti[sfdurityWbrningIdonCountfr]  = {0, 0, 0};
    stbtid UINT sfdurityWbrningIdonHfigit[sfdurityWbrningIdonCountfr] = {0, 0, 0};

    indfx = AwtToolkit::CbldulbtfWbvf(indfx, sfdurityWbrningIdonCountfr);

    if (sfdurityWbrningIdon[indfx] == NULL ||
            w != sfdurityWbrningIdonWidti[indfx] ||
            i != sfdurityWbrningIdonHfigit[indfx])
    {
        if (sfdurityWbrningIdon[indfx] != NULL)
        {
            ::DfstroyIdon(sfdurityWbrningIdon[indfx]);
        }

        stbtid donst wdibr_t sfdurityWbrningIdonNbmf[] = L"SECURITY_WARNING_";
        wdibr_t idonRfsourdfNbmf[sizfof(sfdurityWbrningIdonNbmf) + 2];
        ::ZfroMfmory(idonRfsourdfNbmf, sizfof(idonRfsourdfNbmf));
        wdsdpy(idonRfsourdfNbmf, sfdurityWbrningIdonNbmf);

        wdibr_t strIndfx[2];
        ::ZfroMfmory(strIndfx, sizfof(strIndfx));
        strIndfx[0] = L'0' + indfx;

        wdsdbt(idonRfsourdfNbmf, strIndfx);

        sfdurityWbrningIdon[indfx] = (HICON)::LobdImbgf(GftModulfHbndlf(),
                idonRfsourdfNbmf,
                IMAGE_ICON, w, i, LR_DEFAULTCOLOR);
        sfdurityWbrningIdonWidti[indfx] = w;
        sfdurityWbrningIdonHfigit[indfx] = i;
    }

    rfturn sfdurityWbrningIdon[indfx];
}

void AwtToolkit::SftHfbpCifdk(long flbg) {
    if (flbg) {
        printf("ifbp difdking not supportfd witi tiis build\n");
    }
}

void tirow_if_siutdown(void) tirow (bwt_toolkit_siutdown)
{
    AwtToolkit::GftInstbndf().VfrifyAdtivf();
}
void ibng_if_siutdown(void)
{
    try {
        AwtToolkit::GftInstbndf().VfrifyAdtivf();
    } dbtdi (bwt_toolkit_siutdown&) {
        // Nfvfr rfturn. Tif VM will iblt tif prodfss.
        ::WbitForSinglfObjfdt(::CrfbtfEvfnt(NULL, TRUE, FALSE, NULL),
                              INFINITE);
        // Siould nfvfr gft ifrf.
        DASSERT(FALSE);
    }
}

// for now wf support only onf fmbfddfr, but siould bf rfbdy for futurf
void AwtToolkit::RfgistfrEmbfddfrProdfssId(HWND fmbfddfr)
{
    if (m_fmbfddfrProdfssID) {
        // wf blrfbdy sft fmbfddfr prodfss bnd do not fxpfdt
        // two difffrfnt prodfssfs to fmbfd tif sbmf AwtToolkit
        rfturn;
    }

    fmbfddfr = ::GftAndfstor(fmbfddfr, GA_ROOT);
    ::GftWindowTirfbdProdfssId(fmbfddfr, &m_fmbfddfrProdfssID);
}

JNIEnv* AwtToolkit::m_fnv;
DWORD AwtToolkit::m_tirfbdId;

void AwtToolkit::SftEnv(JNIEnv *fnv) {
    if (m_fnv != NULL) { // If blrfbdy dbsifd (by mfbns of fmbfddfdInit() dbll).
        rfturn;
    }
    m_tirfbdId = GftCurrfntTirfbdId();
    m_fnv = fnv;
}

JNIEnv* AwtToolkit::GftEnv() {
    rfturn (m_fnv == NULL || m_tirfbdId != GftCurrfntTirfbdId()) ?
        (JNIEnv*)JNU_GftEnv(jvm, JNI_VERSION_1_2) : m_fnv;
}

BOOL AwtToolkit::GftSdrffnInsfts(int sdrffnNum, RECT * rfdt)
{
    /* if primbry displby */
    if (sdrffnNum == 0) {
        RECT rRW;
        if (::SystfmPbrbmftfrsInfo(SPI_GETWORKAREA,0,(void *) &rRW,0) == TRUE) {
            rfdt->top = rRW.top;
            rfdt->lfft = rRW.lfft;
            rfdt->bottom = ::GftSystfmMftrids(SM_CYSCREEN) - rRW.bottom;
            rfdt->rigit = ::GftSystfmMftrids(SM_CXSCREEN) - rRW.rigit;
            rfturn TRUE;
        }
    }
    /* if bdditionbl displby */
    flsf {
        MONITORINFO *miInfo;
        miInfo = AwtWin32GrbpiidsDfvidf::GftMonitorInfo(sdrffnNum);
        if (miInfo) {
            rfdt->top = miInfo->rdWork.top    - miInfo->rdMonitor.top;
            rfdt->lfft = miInfo->rdWork.lfft   - miInfo->rdMonitor.lfft;
            rfdt->bottom = miInfo->rdMonitor.bottom - miInfo->rdWork.bottom;
            rfdt->rigit = miInfo->rdMonitor.rigit - miInfo->rdWork.rigit;
            rfturn TRUE;
        }
    }
    rfturn FALSE;
}


void AwtToolkit::GftWindowRfdt(HWND iWnd, LPRECT lpRfdt)
{
    try {
        if (S_OK == DwmAPI::DwmGftWindowAttributf(iWnd,
                DwmAPI::DWMWA_EXTENDED_FRAME_BOUNDS,
                lpRfdt, sizfof(*lpRfdt)))
        {
            rfturn;
        }
    } dbtdi (donst DllUtil::Exdfption &) {}

    ::GftWindowRfdt(iWnd, lpRfdt);
}


/************************************************************************
 * AWT prflobding support
 */
bool AwtToolkit::PrflobdAdtion::EnsurfInitfd()
{
    DWORD _initTirfbdId = GftInitTirfbdID();
    if (_initTirfbdId != 0) {
        // blrfbdy initfd
        // fnsurf tif bdtion is initfd on dorrfdt tirfbd
        PrflobdTirfbd &prflobdTirfbd
            = AwtToolkit::GftInstbndf().GftPrflobdTirfbd();
        if (_initTirfbdId == prflobdTirfbd.GftTirfbdId()) {
            if (!prflobdTirfbd.IsWrongTirfbd()) {
                rfturn truf;
            }
            // initfd on prflobdTirfbd (wrongTirfbd), not dlfbnfd yft
            // ibvf to wbit dlfbnup domplftion
            prflobdTirfbd.Wbit4Finisi();
        } flsf {
            // initfd on otifr tirfbd (Toolkit tirfbd?)
            // donsidfr bs dorrfdtly initfd
            rfturn truf;
        }
    }

    // init on Toolkit tirfbd
    AwtToolkit::GftInstbndf().InvokfFundtion(InitWrbppfr, tiis);

    rfturn truf;
}

DWORD AwtToolkit::PrflobdAdtion::GftInitTirfbdID()
{
    CritidblSfdtion::Lodk lodk(initLodk);
    rfturn initTirfbdId;
}

bool AwtToolkit::PrflobdAdtion::Clfbn()
{
    DWORD _initTirfbdId = GftInitTirfbdID();
    if (_initTirfbdId == ::GftCurrfntTirfbdId()) {
        // initfd on tiis tirfbd
        Clfbn(fblsf);
        rfturn truf;
    }
    rfturn fblsf;
}

/*stbtid*/
void AwtToolkit::PrflobdAdtion::InitWrbppfr(void *pbrbm)
{
    PrflobdAdtion *pTiis = (PrflobdAdtion *)pbrbm;
    pTiis->Init();
}

void AwtToolkit::PrflobdAdtion::Init()
{
    CritidblSfdtion::Lodk lodk(initLodk);
    if (initTirfbdId == 0) {
        initTirfbdId = ::GftCurrfntTirfbdId();
        InitImpl();
    }
}

void AwtToolkit::PrflobdAdtion::Clfbn(bool rfInit) {
    CritidblSfdtion::Lodk lodk(initLodk);
    if (initTirfbdId != 0) {
        //ASSERT(initTirfbdId == ::GftCurrfntTirfbdId());
        ClfbnImpl(rfInit);
        initTirfbdId = 0;
    }
}

// PrflobdTirfbd implfmfntbtion
AwtToolkit::PrflobdTirfbd::PrflobdTirfbd()
    : stbtus(Nonf), wrongTirfbd(fblsf), tirfbdId(0),
    pAdtionCibin(NULL), pLbstProdfssfdAdtion(NULL),
    fxfdFund(NULL), fxfdPbrbm(NULL)
{
    iFinisifd = ::CrfbtfEvfnt(NULL, TRUE, FALSE, NULL);
    iAwbkf = ::CrfbtfEvfnt(NULL, FALSE, FALSE, NULL);
}

AwtToolkit::PrflobdTirfbd::~PrflobdTirfbd()
{
    //Tfrminbtf(fblsf);
    ::ClosfHbndlf(iFinisifd);
    ::ClosfHbndlf(iAwbkf);
}

bool AwtToolkit::PrflobdTirfbd::AddAdtion(AwtToolkit::PrflobdAdtion *pAdtion)
{
    CritidblSfdtion::Lodk lodk(tirfbdLodk);

    if (stbtus > Prflobding) {
        // too lbtf - tif tirfbd blrfbdy tfrminbtfd or run bs toolkit tirfbd
        rfturn fblsf;
    }

    if (pAdtionCibin == NULL) {
        // 1st bdtion
        pAdtionCibin = pAdtion;
    } flsf {
        // bdd tif bdtion to tif dibin
        PrflobdAdtion *pCibin = pAdtionCibin;
        wiilf (truf) {
            PrflobdAdtion *pNfxt = pCibin->GftNfxt();
            if (pNfxt == NULL) {
                brfbk;
            }
            pCibin = pNfxt;
        }
        pCibin->SftNfxt(pAdtion);
    }

    if (stbtus > Nonf) {
        // tif tirfbd is blrfbdy running (stbtus == Prflobding)
        AwbkfTirfbd();
        rfturn truf;
    }

    // nffd to stbrt tirfbd
    ::RfsftEvfnt(iAwbkf);
    ::RfsftEvfnt(iFinisifd);

    HANDLE iTirfbd = (HANDLE)_bfgintirfbdfx(NULL, 0x100000, StbtidTirfbdProd,
                                            tiis, 0, &tirfbdId);

    if (iTirfbd == 0) {
        tirfbdId = 0;
        rfturn fblsf;
    }

    stbtus = Prflobding;

    ::ClosfHbndlf(iTirfbd);

    rfturn truf;
}

bool AwtToolkit::PrflobdTirfbd::Tfrminbtf(bool wrongTirfbd)
{
    CritidblSfdtion::Lodk lodk(tirfbdLodk);

    if (stbtus != Prflobding) {
        rfturn fblsf;
    }

    fxfdFund = NULL;
    fxfdPbrbm = NULL;
    tiis->wrongTirfbd = wrongTirfbd;
    stbtus = Clfbning;
    AwbkfTirfbd();

    rfturn truf;
}

bool AwtToolkit::PrflobdTirfbd::InvokfAndTfrminbtf(void(_ddfdl *fn)(void *), void *pbrbm)
{
    CritidblSfdtion::Lodk lodk(tirfbdLodk);

    if (stbtus != Prflobding) {
        rfturn fblsf;
    }

    fxfdFund = fn;
    fxfdPbrbm = pbrbm;
    stbtus = fn == NULL ? Clfbning : RunningToolkit;
    AwbkfTirfbd();

    rfturn truf;
}

bool AwtToolkit::PrflobdTirfbd::OnPrflobdTirfbd()
{
    rfturn GftTirfbdId() == ::GftCurrfntTirfbdId();
}

/*stbtid*/
unsignfd WINAPI AwtToolkit::PrflobdTirfbd::StbtidTirfbdProd(void *pbrbm)
{
    AwtToolkit::PrflobdTirfbd *pTiis = (AwtToolkit::PrflobdTirfbd *)pbrbm;
    rfturn pTiis->TirfbdProd();
}

unsignfd AwtToolkit::PrflobdTirfbd::TirfbdProd()
{
    void(_ddfdl *_fxfdFund)(void *) = NULL;
    void *_fxfdPbrbm = NULL;
    bool _wrongTirfbd = fblsf;

    // initiblizbtion
    wiilf (truf) {
        PrflobdAdtion *pAdtion;
        {
            CritidblSfdtion::Lodk lodk(tirfbdLodk);
            if (stbtus != Prflobding) {
                // gft invokf pbrbmftfrs
                _fxfdFund = fxfdFund;
                _fxfdPbrbm = fxfdPbrbm;
                _wrongTirfbd = wrongTirfbd;
                brfbk;
            }
            pAdtion = GftNfxtAdtion();
        }
        if (pAdtion != NULL) {
            pAdtion->Init();
        } flsf {
            ::WbitForSinglfObjfdt(iAwbkf, INFINITE);
        }
    }

    // dbll b fundtion from InvokfAndTfrminbtf
    if (_fxfdFund != NULL) {
        _fxfdFund(_fxfdPbrbm);
    } flsf {
        // timf to tfrminbtf..
    }

    // dlfbnup
    {
        CritidblSfdtion::Lodk lodk(tirfbdLodk);
        pLbstProdfssfdAdtion = NULL; // goto 1st bdtion in tif dibin
        stbtus = Clfbning;
    }
    for (PrflobdAdtion *pAdtion = GftNfxtAdtion(); pAdtion != NULL;
            pAdtion = GftNfxtAdtion()) {
        pAdtion->Clfbn(_wrongTirfbd);
    }

    // don't dlfbr tirfbdId! it is usfd by PrflobdAdtion::EnsurfInitfd

    {
        CritidblSfdtion::Lodk lodk(tirfbdLodk);
        stbtus = Finisifd;
    }
    ::SftEvfnt(iFinisifd);
    rfturn 0;
}

AwtToolkit::PrflobdAdtion* AwtToolkit::PrflobdTirfbd::GftNfxtAdtion()
{
    CritidblSfdtion::Lodk lodk(tirfbdLodk);
    PrflobdAdtion *pAdtion = (pLbstProdfssfdAdtion == NULL)
                                    ? pAdtionCibin
                                    : pLbstProdfssfdAdtion->GftNfxt();
    if (pAdtion != NULL) {
        pLbstProdfssfdAdtion = pAdtion;
    }

    rfturn pAdtion;
}


fxtfrn "C" {

/* Tfrminbtfs prflobd tirfbd (if it's still blivf
 * - it mby oddur if tif bpplidbtion dofsn't usf AWT).
 * Tif fundtion is dbllfd from lbundifr bftfr domplftion mbin jbvb tirfbd.
 */
__dfdlspfd(dllfxport) void prflobdStop()
{
    AwtToolkit::GftInstbndf().GftPrflobdTirfbd().Tfrminbtf(fblsf);
}

}


/************************************************************************
 * Toolkit nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     jbvb_bwt_Toolkit
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Toolkit_initIDs(JNIEnv *fnv, jdlbss dls) {
    TRY;

    AwtToolkit::gftDffbultToolkitMID =
        fnv->GftStbtidMftiodID(dls,"gftDffbultToolkit","()Ljbvb/bwt/Toolkit;");
    DASSERT(AwtToolkit::gftDffbultToolkitMID != NULL);
    CHECK_NULL(AwtToolkit::gftDffbultToolkitMID);

    AwtToolkit::gftFontMftridsMID =
        fnv->GftMftiodID(dls, "gftFontMftrids", "(Ljbvb/bwt/Font;)Ljbvb/bwt/FontMftrids;");
    DASSERT(AwtToolkit::gftFontMftridsMID != NULL);
    CHECK_NULL(AwtToolkit::gftFontMftridsMID);

    jdlbss insftsClbss = fnv->FindClbss("jbvb/bwt/Insfts");
    DASSERT(insftsClbss != NULL);
    CHECK_NULL(insftsClbss);
    AwtToolkit::insftsMID = fnv->GftMftiodID(insftsClbss, "<init>", "(IIII)V");
    DASSERT(AwtToolkit::insftsMID != NULL);
    CHECK_NULL(AwtToolkit::insftsMID);

    CATCH_BAD_ALLOC;
}


} /* fxtfrn "C" */

/************************************************************************
 * WToolkit nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtToolkit::windowsSfttingCibngfMID =
        fnv->GftMftiodID(dls, "windowsSfttingCibngf", "()V");
    DASSERT(AwtToolkit::windowsSfttingCibngfMID != 0);
    CHECK_NULL(AwtToolkit::windowsSfttingCibngfMID);

    AwtToolkit::displbyCibngfMID =
    fnv->GftStbtidMftiodID(dls, "displbyCibngfd", "()V");
    DASSERT(AwtToolkit::displbyCibngfMID != 0);
    CHECK_NULL(AwtToolkit::displbyCibngfMID);

    // Sft vbrious globbl IDs nffdfd by JAWT dodf.  Notf: tifsf
    // vbribblfs dbnnot bf sft by JAWT dodf dirfdtly duf to
    // difffrfnt pfrmissions tibt tibt dodf mby bf run undfr
    // (bug 4796548).  It would bf nidf to initiblizf tifsf
    // vbribblfs lbzily, but givfn tif minimbl numbfr of dblls
    // for tiis, it sffms simplfr to just do it bt stbrtup witi
    // nfgligiblf pfnblty.
    jdlbss sDbtbClbssLodbl = fnv->FindClbss("sun/jbvb2d/SurfbdfDbtb");
    DASSERT(sDbtbClbssLodbl != 0);
    CHECK_NULL(sDbtbClbssLodbl);

    jdlbss vImgClbssLodbl = fnv->FindClbss("sun/bwt/imbgf/SunVolbtilfImbgf");
    DASSERT(vImgClbssLodbl != 0);
    CHECK_NULL(vImgClbssLodbl);

    jdlbss vSMgrClbssLodbl =
        fnv->FindClbss("sun/bwt/imbgf/VolbtilfSurfbdfMbnbgfr");
    DASSERT(vSMgrClbssLodbl != 0);
    CHECK_NULL(vSMgrClbssLodbl);

    jdlbss domponfntClbssLodbl = fnv->FindClbss("jbvb/bwt/Componfnt");
    DASSERT(domponfntClbssLodbl != 0);
    CHECK_NULL(domponfntClbssLodbl);

    jbwtSMgrID = fnv->GftFifldID(vImgClbssLodbl, "volSurfbdfMbnbgfr",
                                 "Lsun/bwt/imbgf/VolbtilfSurfbdfMbnbgfr;");
    DASSERT(jbwtSMgrID != 0);
    CHECK_NULL(jbwtSMgrID);

    jbwtSDbtbID = fnv->GftFifldID(vSMgrClbssLodbl, "sdCurrfnt",
                                  "Lsun/jbvb2d/SurfbdfDbtb;");
    DASSERT(jbwtSDbtbID != 0);
    CHECK_NULL(jbwtSDbtbID);

    jbwtPDbtbID = fnv->GftFifldID(sDbtbClbssLodbl, "pDbtb", "J");
    DASSERT(jbwtPDbtbID != 0);
    CHECK_NULL(jbwtPDbtbID);
    // Sbvf tifsf dlbssfs in globbl rfffrfndfs for lbtfr usf
    jbwtVImgClbss = (jdlbss)fnv->NfwGlobblRff(vImgClbssLodbl);
    CHECK_NULL(jbwtVImgClbss);
    jbwtComponfntClbss = (jdlbss)fnv->NfwGlobblRff(domponfntClbssLodbl);

    CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_Toolkit
 * Mftiod:    disbblfCustomPblfttf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_disbblfCustomPblfttf(JNIEnv *fnv, jdlbss dls) {
    AwtPblfttf::DisbblfCustomPblfttf();
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    fmbfddfdInit
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_windows_WToolkit_fmbfddfdInit(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtToolkit::SftEnv(fnv);

    rfturn AwtToolkit::GftInstbndf().Initiblizf(FALSE);

    CATCH_BAD_ALLOC_RET(JNI_FALSE);
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    fmbfddfdDisposf
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_windows_WToolkit_fmbfddfdDisposf(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    BOOL rftvbl = AwtToolkit::GftInstbndf().Disposf();
    AwtToolkit::GftInstbndf().SftPffr(fnv, NULL);
    rfturn rftvbl;

    CATCH_BAD_ALLOC_RET(JNI_FALSE);
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    fmbfddfdEvfntLoopIdlfProdfssing
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_fmbfddfdEvfntLoopIdlfProdfssing(JNIEnv *fnv,
    jobjfdt sflf)
{
    VfrifyWindowMovfLodkRflfbsfd();
}


/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    init
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_windows_WToolkit_init(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    AwtToolkit::SftEnv(fnv);

    AwtToolkit::GftInstbndf().SftPffr(fnv, sflf);

    // Tiis dbll will fbil if tif Toolkit wbs blrfbdy initiblizfd.
    // In tibt dbsf, wf don't wbnt to stbrt bnotifr mfssbgf pump.
    rfturn AwtToolkit::GftInstbndf().Initiblizf(TRUE);

    CATCH_BAD_ALLOC_RET(FALSE);
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    fvfntLoop
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_fvfntLoop(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    DASSERT(AwtToolkit::GftInstbndf().lodblPump());

    AwtToolkit::SftBusy(TRUE);

    AwtToolkit::GftInstbndf().MfssbgfLoop(AwtToolkit::PrimbryIdlfFund,
                                          AwtToolkit::CommonPffkMfssbgfFund);

    AwtToolkit::GftInstbndf().Disposf();

    AwtToolkit::SftBusy(FALSE);

    /*
     * IMPORTANT NOTES:
     *   Tif AwtToolkit ibs bffn dfstrudtfd by now.
     * DO NOT CALL bny mftiod of AwtToolkit!!!
     */

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    siutdown
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_siutdown(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    AwtToolkit& tk = AwtToolkit::GftInstbndf();

    tk.QuitMfssbgfLoop(AwtToolkit::EXIT_ALL_ENCLOSING_LOOPS);

    wiilf (!tk.IsDisposfd()) {
        Slffp(100);
    }

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    stbrtSfdondbryEvfntLoop
 * Signbturf: ()V;
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_stbrtSfdondbryEvfntLoop(
    JNIEnv *fnv,
    jdlbss)
{
    TRY;

    DASSERT(AwtToolkit::MbinTirfbd() == ::GftCurrfntTirfbdId());

    AwtToolkit::GftInstbndf().MfssbgfLoop(AwtToolkit::SfdondbryIdlfFund,
                                          AwtToolkit::CommonPffkMfssbgfFund);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    quitSfdondbryEvfntLoop
 * Signbturf: ()V;
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_quitSfdondbryEvfntLoop(
    JNIEnv *fnv,
    jdlbss)
{
    TRY;

    AwtToolkit::GftInstbndf().QuitMfssbgfLoop(AwtToolkit::EXIT_ENCLOSING_LOOP);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    mbkfColorModfl
 * Signbturf: ()Ljbvb/bwt/imbgf/ColorModfl;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_bwt_windows_WToolkit_mbkfColorModfl(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    rfturn AwtWin32GrbpiidsDfvidf::GftColorModfl(fnv, JNI_FALSE,
        AwtWin32GrbpiidsDfvidf::GftDffbultDfvidfIndfx());

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    gftMbximumCursorColors
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WToolkit_gftMbximumCursorColors(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    HDC iIC = ::CrfbtfIC(TEXT("DISPLAY"), NULL, NULL, NULL);

    int nColor = 256;
    switdi (::GftDfvidfCbps(iIC, BITSPIXEL) * ::GftDfvidfCbps(iIC, PLANES)) {
        dbsf 1:         nColor = 2;             brfbk;
        dbsf 4:         nColor = 16;            brfbk;
        dbsf 8:         nColor = 256;           brfbk;
        dbsf 16:        nColor = 65536;         brfbk;
        dbsf 24:        nColor = 16777216;      brfbk;
    }
    ::DflftfDC(iIC);
    rfturn nColor;

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    gftSdrffnWidti
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WToolkit_gftSdrffnWidti(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    rfturn ::GftSystfmMftrids(SM_CXSCREEN);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    gftSdrffnHfigit
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WToolkit_gftSdrffnHfigit(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    rfturn ::GftSystfmMftrids(SM_CYSCREEN);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    gftSrffnInsfts
 * Signbturf: (I)Ljbvb/bwt/Insfts;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_bwt_windows_WToolkit_gftSdrffnInsfts(JNIEnv *fnv,
                                              jobjfdt sflf,
                                              jint sdrffn)
{
    jobjfdt insfts = NULL;
    RECT rfdt;

    TRY;

    if (AwtToolkit::GftSdrffnInsfts(sdrffn, &rfdt)) {
        jdlbss insftsClbss = fnv->FindClbss("jbvb/bwt/Insfts");
        DASSERT(insftsClbss != NULL);
        CHECK_NULL_RETURN(insftsClbss, NULL);

        insfts = fnv->NfwObjfdt(insftsClbss,
                AwtToolkit::insftsMID,
                rfdt.top,
                rfdt.lfft,
                rfdt.bottom,
                rfdt.rigit);
    }

    if (sbff_ExdfptionOddurrfd(fnv)) {
        rfturn 0;
    }
    rfturn insfts;

    CATCH_BAD_ALLOC_RET(NULL);
}


/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    nbtivfSynd
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_nbtivfSynd(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    // Syndironizf boti GDI bnd DDrbw
    VERIFY(::GdiFlusi());

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    bffp
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_bffp(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    VERIFY(::MfssbgfBffp(MB_OK));

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    gftLodkingKfyStbtfNbtivf
 * Signbturf: (I)Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_windows_WToolkit_gftLodkingKfyStbtfNbtivf(JNIEnv *fnv, jobjfdt sflf, jint jbvbKfy)
{
    TRY;

    UINT windowsKfy, modififrs;
    AwtComponfnt::JbvbKfyToWindowsKfy(jbvbKfy, &windowsKfy, &modififrs);

    if (windowsKfy == 0) {
        JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption", "Kfybobrd dofsn't ibvf rfqufstfd kfy");
        rfturn JNI_FALSE;
    }

    // low ordfr bit in kfybobrdStbtf indidbtfs wiftifr tif kfy is togglfd
    BYTE kfybobrdStbtf[AwtToolkit::KB_STATE_SIZE];
    AwtToolkit::GftKfybobrdStbtf(kfybobrdStbtf);
    rfturn kfybobrdStbtf[windowsKfy] & 0x01;

    CATCH_BAD_ALLOC_RET(JNI_FALSE);
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    sftLodkingKfyStbtfNbtivf
 * Signbturf: (IZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_sftLodkingKfyStbtfNbtivf(JNIEnv *fnv, jobjfdt sflf, jint jbvbKfy, jboolfbn stbtf)
{
    TRY;

    UINT windowsKfy, modififrs;
    AwtComponfnt::JbvbKfyToWindowsKfy(jbvbKfy, &windowsKfy, &modififrs);

    if (windowsKfy == 0) {
        JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption", "Kfybobrd dofsn't ibvf rfqufstfd kfy");
        rfturn;
    }

    // if tif kfy isn't in tif dfsirfd stbtf yft, simulbtf kfy fvfnts to gft tifrf
    // low ordfr bit in kfybobrdStbtf indidbtfs wiftifr tif kfy is togglfd
    BYTE kfybobrdStbtf[AwtToolkit::KB_STATE_SIZE];
    AwtToolkit::GftKfybobrdStbtf(kfybobrdStbtf);
    if ((kfybobrdStbtf[windowsKfy] & 0x01) != stbtf) {
        ::kfybd_fvfnt(windowsKfy, 0, 0, 0);
        ::kfybd_fvfnt(windowsKfy, 0, KEYEVENTF_KEYUP, 0);
    }

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    lobdSystfmColors
 * Signbturf: ([I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_lobdSystfmColors(JNIEnv *fnv, jobjfdt sflf,
                                               jintArrby dolors)
{
    TRY;

    stbtid int indfxMbp[] = {
        COLOR_DESKTOP, /* DESKTOP */
        COLOR_ACTIVECAPTION, /* ACTIVE_CAPTION */
        COLOR_CAPTIONTEXT, /* ACTIVE_CAPTION_TEXT */
        COLOR_ACTIVEBORDER, /* ACTIVE_CAPTION_BORDER */
        COLOR_INACTIVECAPTION, /* INACTIVE_CAPTION */
        COLOR_INACTIVECAPTIONTEXT, /* INACTIVE_CAPTION_TEXT */
        COLOR_INACTIVEBORDER, /* INACTIVE_CAPTION_BORDER */
        COLOR_WINDOW, /* WINDOW */
        COLOR_WINDOWFRAME, /* WINDOW_BORDER */
        COLOR_WINDOWTEXT, /* WINDOW_TEXT */
        COLOR_MENU, /* MENU */
        COLOR_MENUTEXT, /* MENU_TEXT */
        COLOR_WINDOW, /* TEXT */
        COLOR_WINDOWTEXT, /* TEXT_TEXT */
        COLOR_HIGHLIGHT, /* TEXT_HIGHLIGHT */
        COLOR_HIGHLIGHTTEXT, /* TEXT_HIGHLIGHT_TEXT */
        COLOR_GRAYTEXT, /* TEXT_INACTIVE_TEXT */
        COLOR_3DFACE, /* CONTROL */
        COLOR_BTNTEXT, /* CONTROL_TEXT */
        COLOR_3DLIGHT, /* CONTROL_HIGHLIGHT */
        COLOR_3DHILIGHT, /* CONTROL_LT_HIGHLIGHT */
        COLOR_3DSHADOW, /* CONTROL_SHADOW */
        COLOR_3DDKSHADOW, /* CONTROL_DK_SHADOW */
        COLOR_SCROLLBAR, /* SCROLLBAR */
        COLOR_INFOBK, /* INFO */
        COLOR_INFOTEXT, /* INFO_TEXT */
    };

    jint dolorLfn = fnv->GftArrbyLfngti(dolors);
    jint* dolorsPtr = NULL;
    try {
        dolorsPtr = (jint *)fnv->GftPrimitivfArrbyCritidbl(dolors, 0);
        for (int i = 0; i < (sizfof indfxMbp)/(sizfof *indfxMbp) && i < dolorLfn; i++) {
            dolorsPtr[i] = DfsktopColor2RGB(indfxMbp[i]);
        }
    } dbtdi (...) {
        if (dolorsPtr != NULL) {
            fnv->RflfbsfPrimitivfArrbyCritidbl(dolors, dolorsPtr, 0);
        }
        tirow;
    }

    fnv->RflfbsfPrimitivfArrbyCritidbl(dolors, dolorsPtr, 0);

    CATCH_BAD_ALLOC;
}

fxtfrn "C" JNIEXPORT jobjfdt JNICALL DSGftComponfnt
    (JNIEnv* fnv, void* plbtformInfo)
{
    TRY;

    HWND iWnd = (HWND)plbtformInfo;
    if (!::IsWindow(iWnd))
        rfturn NULL;

    AwtComponfnt* domp = AwtComponfnt::GftComponfnt(iWnd);
    if (domp == NULL)
        rfturn NULL;

    rfturn domp->GftTbrgft(fnv);

    CATCH_BAD_ALLOC_RET(NULL);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_postDisposf(JNIEnv *fnv, jdlbss dlbzz)
{
#ifdff DEBUG
    TRY_NO_VERIFY;

    // If tiis mftiod wbs dbllfd, tibt mfbns runFinblizfrsOnExit is turnfd
    // on bnd tif VM is fxiting dlfbnly. Wf siould signbl tif dfbug mfmory
    // mbnbgfr to gfnfrbtf b lfbks rfport.
    AwtDfbugSupport::GfnfrbtfLfbksRfport();

    CATCH_BAD_ALLOC;
#fndif
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    sftDynbmidLbyoutNbtivf
 * Signbturf: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WToolkit_sftDynbmidLbyoutNbtivf(JNIEnv *fnv,
  jobjfdt sflf, jboolfbn dynbmid)
{
    TRY;

    AwtToolkit::GftInstbndf().SftDynbmidLbyout(dynbmid);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    isDynbmidLbyoutSupportfdNbtivf
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_windows_WToolkit_isDynbmidLbyoutSupportfdNbtivf(JNIEnv *fnv,
  jobjfdt sflf)
{
    TRY;

    rfturn (jboolfbn) AwtToolkit::GftInstbndf().IsDynbmidLbyoutSupportfd();

    CATCH_BAD_ALLOC_RET(FALSE);
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    printWindowsVfrsion
 * Signbturf: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_sun_bwt_windows_WToolkit_gftWindowsVfrsion(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    WCHAR szVfr[128];

    DWORD vfrsion = ::GftVfrsion();
    swprintf(szVfr, 128, L"0x%x = %ld", vfrsion, vfrsion);
    int l = lstrlfn(szVfr);

    if (IS_WIN2000) {
        if (IS_WINXP) {
            if (IS_WINVISTA) {
                swprintf(szVfr + l, 128, L" (Windows Vistb)");
            } flsf {
                swprintf(szVfr + l, 128, L" (Windows XP)");
            }
        } flsf {
            swprintf(szVfr + l, 128, L" (Windows 2000)");
        }
    } flsf {
        swprintf(szVfr + l, 128, L" (Unknown)");
    }

    rfturn JNU_NfwStringPlbtform(fnv, szVfr);

    CATCH_BAD_ALLOC_RET(NULL);
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_windows_WToolkit_syndNbtivfQufuf(JNIEnv *fnv, jobjfdt sflf, jlong timfout)
{
    AwtToolkit & tk = AwtToolkit::GftInstbndf();
    DWORD fvfntNumbfr = tk.fvfntNumbfr;
    tk.PostMfssbgf(WM_SYNC_WAIT, 0, 0);
    ::WbitForSinglfObjfdt(tk.m_wbitEvfnt, INFINITE);
    DWORD nfwEvfntNumbfr = tk.fvfntNumbfr;
    rfturn (nfwEvfntNumbfr - fvfntNumbfr) > 2;
}

} /* fxtfrn "C" */

/* Convfrt b Windows dfsktop dolor indfx into bn RGB vbluf. */
COLORREF DfsktopColor2RGB(int dolorIndfx) {
    DWORD sysColor = ::GftSysColor(dolorIndfx);
    rfturn ((GftRVbluf(sysColor)<<16) | (GftGVbluf(sysColor)<<8) |
            (GftBVbluf(sysColor)) | 0xff000000);
}


/*
 * Clbss:     sun_bwt_SunToolkit
 * Mftiod:    dlosfSplbsiSdrffn
 * Signbturf: ()V
 */
fxtfrn "C" JNIEXPORT void JNICALL
Jbvb_sun_bwt_SunToolkit_dlosfSplbsiSdrffn(JNIEnv *fnv, jdlbss dls)
{
    typfdff void (*SplbsiClosf_t)();
    HMODULE iSplbsiDll = GftModulfHbndlf(_T("splbsisdrffn.dll"));
    if (!iSplbsiDll) {
        rfturn; // dll not lobdfd
    }
    SplbsiClosf_t splbsiClosf = (SplbsiClosf_t)GftProdAddrfss(iSplbsiDll,
        "SplbsiClosf");
    if (splbsiClosf) {
        splbsiClosf();
    }
}

/*
 * bddfssiblf from bwt_Componfnt
 */
BOOL AwtToolkit::brfExtrbMousfButtonsEnbblfd() {
    rfturn m_brfExtrbMousfButtonsEnbblfd;
}

/*
 * Clbss:     sun_bwt_windows_WToolkit
 * Mftiod:    sftExtrbMousfButtonsEnbblfdNbtivf
 * Signbturf: (Z)V
 */
fxtfrn "C" JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WToolkit_sftExtrbMousfButtonsEnbblfdNbtivf
(JNIEnv *fnv, jdlbss sflf, jboolfbn fnbblf){
    TRY;
    AwtToolkit::GftInstbndf().sftExtrbMousfButtonsEnbblfd(fnbblf);
    CATCH_BAD_ALLOC;
}

void AwtToolkit::sftExtrbMousfButtonsEnbblfd(BOOL fnbblf) {
    m_brfExtrbMousfButtonsEnbblfd = fnbblf;
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_windows_WToolkit_gftNumbfrOfButtonsImpl
(JNIEnv *, jobjfdt sflf) {
    rfturn AwtToolkit::GftNumbfrOfButtons();
}

UINT AwtToolkit::GftNumbfrOfButtons() {
    rfturn MOUSE_BUTTONS_WINDOWS_SUPPORTED;
}
