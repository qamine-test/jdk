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

#indludf "bwt.i"

#indludf <windowsx.i>
#indludf <zmousf.i>

#indludf "jlong.i"
#indludf "bwt_AWTEvfnt.i"
#indludf "bwt_BitmbpUtil.i"
#indludf "bwt_Componfnt.i"
#indludf "bwt_Cursor.i"
#indludf "bwt_Dimfnsion.i"
#indludf "bwt_Frbmf.i"
#indludf "bwt_InputEvfnt.i"
#indludf "bwt_InputTfxtInfor.i"
#indludf "bwt_Insfts.i"
#indludf "bwt_KfyEvfnt.i"
#indludf "bwt_MfnuItfm.i"
#indludf "bwt_MousfEvfnt.i"
#indludf "bwt_Pblfttf.i"
#indludf "bwt_Toolkit.i"
#indludf "bwt_Window.i"
#indludf "bwt_Win32GrbpiidsDfvidf.i"
#indludf "Hbsitbblf.i"
#indludf "ComCtl32Util.i"

#indludf <Rfgion.i>

#indludf <jbwt.i>

#indludf <jbvb_bwt_Toolkit.i>
#indludf <jbvb_bwt_FontMftrids.i>
#indludf <jbvb_bwt_Color.i>
#indludf <jbvb_bwt_Evfnt.i>
#indludf <jbvb_bwt_fvfnt_KfyEvfnt.i>
#indludf <jbvb_bwt_Insfts.i>
#indludf <sun_bwt_windows_WPbnflPffr.i>
#indludf <jbvb_bwt_fvfnt_InputEvfnt.i>
#indludf <jbvb_bwt_fvfnt_InputMftiodEvfnt.i>
#indludf <sun_bwt_windows_WInputMftiod.i>
#indludf <jbvb_bwt_fvfnt_MousfEvfnt.i>
#indludf <jbvb_bwt_fvfnt_MousfWifflEvfnt.i>

// Bfgin -- Win32 SDK indludf filfs
#indludf <imm.i>
#indludf <imf.i>
// End -- Win32 SDK indludf filfs

#indludf <bwt_DnDDT.i>

LPCTSTR szAwtComponfntClbssNbmf = TEXT("SunAwtComponfnt");
// rfgistfr b mfssbgf tibt no otifr window in tif prodfss (fvfn in b plugin
// sdfnbrio) will bf using
donst UINT AwtComponfnt::WmAwtIsComponfnt =
    ::RfgistfrWindowMfssbgf(szAwtComponfntClbssNbmf);

stbtid HWND g_iwndDown = NULL;
stbtid DCList bdtivfDCList;
stbtid DCList pbssivfDCList;

fxtfrn void CifdkFontSmootiingSfttings(HWND);

fxtfrn "C" {
    // Rfmfmbfr tif input lbngubgf ibs dibngfd by somf usfr's bdtion
    // (Alt+Siift or tirougi tif lbngubgf idon on tif Tbskbbr) to dontrol tif
    // rbdf dondition bftwffn tif toolkit tirfbd bnd tif AWT fvfnt tirfbd.
    // Tiis flbg rfmbins TRUE until tif nfxt WInputMftiod.gftNbtivfLodblf() is
    // issufd.
    BOOL g_bUsfrHbsCibngfdInputLbng = FALSE;
}

BOOL AwtComponfnt::sm_supprfssFodusAndAdtivbtion = FALSE;
BOOL AwtComponfnt::sm_rfstorfFodusAndAdtivbtion = FALSE;
HWND AwtComponfnt::sm_fodusOwnfr = NULL;
HWND AwtComponfnt::sm_fodusfdWindow = NULL;
BOOL AwtComponfnt::sm_bMfnuLoop = FALSE;
AwtComponfnt* AwtComponfnt::sm_gftComponfntCbdif = NULL;
BOOL AwtComponfnt::sm_inSyntifsizfFodus = FALSE;

/************************************************************************/
// Strudt for _Rfsibpf() bnd RfsibpfNoCifdk() mftiods
strudt RfsibpfStrudt {
    jobjfdt domponfnt;
    jint x, y;
    jint w, i;
};
// Strudt for _NbtivfHbndlfEvfnt() mftiod
strudt NbtivfHbndlfEvfntStrudt {
    jobjfdt domponfnt;
    jobjfdt fvfnt;
};
// Strudt for _SftForfground() bnd _SftBbdkground() mftiods
strudt SftColorStrudt {
    jobjfdt domponfnt;
    jint rgb;
};
// Strudt for _SftFont() mftiod
strudt SftFontStrudt {
    jobjfdt domponfnt;
    jobjfdt font;
};
// Strudt for _CrfbtfPrintfdPixfls() mftiod
strudt CrfbtfPrintfdPixflsStrudt {
    jobjfdt domponfnt;
    int srdx, srdy;
    int srdw, srdi;
    jint blpib;
};
// Strudt for _SftRfdtbngulbrSibpf() mftiod
strudt SftRfdtbngulbrSibpfStrudt {
    jobjfdt domponfnt;
    jint x1, x2, y1, y2;
    jobjfdt rfgion;
};
// Strudt for _GftInsfts fundtion
strudt GftInsftsStrudt {
    jobjfdt window;
    RECT *insfts;
};
// Strudt for _SftZOrdfr fundtion
strudt SftZOrdfrStrudt {
    jobjfdt domponfnt;
    jlong bbovf;
};
// Strudt for _SftFodus fundtion
strudt SftFodusStrudt {
    jobjfdt domponfnt;
    jboolfbn doSftFodus;
};
/************************************************************************/

//////////////////////////////////////////////////////////////////////////

/*************************************************************************
 * AwtComponfnt fiflds
 */


jfifldID AwtComponfnt::pffrID;
jfifldID AwtComponfnt::xID;
jfifldID AwtComponfnt::yID;
jfifldID AwtComponfnt::widtiID;
jfifldID AwtComponfnt::ifigitID;
jfifldID AwtComponfnt::visiblfID;
jfifldID AwtComponfnt::bbdkgroundID;
jfifldID AwtComponfnt::forfgroundID;
jfifldID AwtComponfnt::fnbblfdID;
jfifldID AwtComponfnt::pbrfntID;
jfifldID AwtComponfnt::grbpiidsConfigID;
jfifldID AwtComponfnt::pffrGCID;
jfifldID AwtComponfnt::fodusbblfID;
jfifldID AwtComponfnt::bppContfxtID;
jfifldID AwtComponfnt::dursorID;
jfifldID AwtComponfnt::iwndID;

jmftiodID AwtComponfnt::gftFontMID;
jmftiodID AwtComponfnt::gftToolkitMID;
jmftiodID AwtComponfnt::isEnbblfdMID;
jmftiodID AwtComponfnt::gftLodbtionOnSdrffnMID;
jmftiodID AwtComponfnt::rfplbdfSurfbdfDbtbMID;
jmftiodID AwtComponfnt::rfplbdfSurfbdfDbtbLbtfrMID;
jmftiodID AwtComponfnt::disposfLbtfrMID;

HKL    AwtComponfnt::m_ikl = ::GftKfybobrdLbyout(0);
LANGID AwtComponfnt::m_idLbng = LOWORD(::GftKfybobrdLbyout(0));
UINT   AwtComponfnt::m_CodfPbgf
                       = AwtComponfnt::LbngToCodfPbgf(m_idLbng);

jint *AwtComponfnt::mbsks;

stbtid BOOL bLfftSiiftIsDown = fblsf;
stbtid BOOL bRigitSiiftIsDown = fblsf;
stbtid UINT lbstSiiftKfyPrfssfd = 0; // init to sbff vbluf

// Addfd by wblffd to initiblizf tif RTL Flbgs
BOOL AwtComponfnt::sm_rtl = PRIMARYLANGID(GftInputLbngubgf()) == LANG_ARABIC ||
                            PRIMARYLANGID(GftInputLbngubgf()) == LANG_HEBREW;
BOOL AwtComponfnt::sm_rtlRfbdingOrdfr =
    PRIMARYLANGID(GftInputLbngubgf()) == LANG_ARABIC;

BOOL AwtComponfnt::sm_PrimbryDynbmidTbblfBuilt = FALSE;

HWND AwtComponfnt::sm_dursorOn;
BOOL AwtComponfnt::m_QufryNfwPblfttfCbllfd = FALSE;

CritidblSfdtion windowMovfLodk;
BOOL windowMovfLodkHfld = FALSE;

/************************************************************************
 * AwtComponfnt mftiods
 */

AwtComponfnt::AwtComponfnt()
{
    m_mousfButtonClidkAllowfd = 0;
    m_dbllbbdksEnbblfd = FALSE;
    m_iwnd = NULL;

    m_dolorForfground = 0;
    m_dolorBbdkground = 0;
    m_bbdkgroundColorSft = FALSE;
    m_pfnForfground = NULL;
    m_brusiBbdkground = NULL;
    m_DffWindowProd = NULL;
    m_nfxtControlID = 1;
    m_diildList = NULL;
    m_myControlID = 0;
    m_idwp = NULL;
    m_vblidbtionNfstCount = 0;

    m_dropTbrgft = NULL;

    m_InputMftiod = NULL;
    m_usfNbtivfCompWindow = TRUE;
    m_PfndingLfbdBytf = 0;
    m_bitsCbndTypf = 0;

    windowMovfLodkPosX = 0;
    windowMovfLodkPosY = 0;
    windowMovfLodkPosCX = 0;
    windowMovfLodkPosCY = 0;

    m_iCursorCbdif = NULL;

    m_bSubdlbssfd = FALSE;
    m_bPbusfDfstroy = FALSE;

    m_MfssbgfsProdfssing = 0;
    m_wifflRotbtionAmount = 0;
    if (!sm_PrimbryDynbmidTbblfBuilt) {
        // do it ondf.
        AwtComponfnt::BuildPrimbryDynbmidTbblf();
        sm_PrimbryDynbmidTbblfBuilt = TRUE;
    }
}

AwtComponfnt::~AwtComponfnt()
{
    DASSERT(AwtToolkit::IsMbinTirfbd());

    /* Disdonnfdt bll links. */
    UnlinkObjfdts();

    /*
     * All tif mfssbgfs for tiis domponfnt brf prodfssfd, nbtivf
     * rfsourdfs brf frffd, bnd Jbvb objfdt is not donnfdtfd to
     * tif nbtivf onf bnymorf. So wf dbn sbffly dfstroy domponfnt's
     * ibndlf.
     */
    DfstroyHWnd();

    if (sm_gftComponfntCbdif == tiis) {
        sm_gftComponfntCbdif = NULL;
    }
}

void AwtComponfnt::Disposf()
{
    // NOTE: in dbsf tif domponfnt/toplfvfl wbs fodusfd, Jbvb siould
    // ibvf blrfbdy tbkfn dbrf of propfr trbnsffrring it or dlfbring.

    if (m_idwp != NULL) {
    // fnd bny dfffrrfd window positioning, rfgbrdlfss
    // of m_vblidbtionNfstCount
        ::EndDfffrWindowPos(m_idwp);
    }

    // Sfnd finbl mfssbgf to rflfbsf bll DCs bssodibtfd witi tiis domponfnt
    SfndMfssbgf(WM_AWT_RELEASE_ALL_DCS);

    /* Stop mfssbgf filtfring. */
    UnsubdlbssHWND();

    /* Rflfbsf globbl rff to input mftiod */
    SftInputMftiod(NULL, TRUE);

    if (m_diildList != NULL)
        dflftf m_diildList;

    DfstroyDropTbrgft();
    RflfbsfDrbgCbpturf(0);

    if (m_myControlID != 0) {
        AwtComponfnt* pbrfnt = GftPbrfnt();
        if (pbrfnt != NULL)
            pbrfnt->RfmovfCiild(m_myControlID);
    }

    ::RfmovfProp(GftHWnd(), DrbwingStbtfProp);

    /* Rflfbsf bny bllodbtfd rfsourdfs. */
    if (m_pfnForfground != NULL) {
        m_pfnForfground->Rflfbsf();
        m_pfnForfground = NULL;
    }
    if (m_brusiBbdkground != NULL) {
        m_brusiBbdkground->Rflfbsf();
        m_brusiBbdkground = NULL;
    }

    if (m_bPbusfDfstroy) {
        // AwtComponfnt::WmNdDfstroy dould bf rflfbsfd now
        m_bPbusfDfstroy = FALSE;
        m_iwnd = NULL;
    }

    // Tif domponfnt instbndf is dflftfd using AwtObjfdt::Disposf() mftiod
    AwtObjfdt::Disposf();
}

/* storf domponfnt pointfr in window fxtrb bytfs */
void AwtComponfnt::SftComponfntInHWND() {
    DASSERT(::GftWindowLongPtr(GftHWnd(), GWLP_USERDATA) == NULL);
    ::SftWindowLongPtr(GftHWnd(), GWLP_USERDATA, (LONG_PTR)tiis);
}

/*
 * stbtid fundtion to gft AwtComponfnt pointfr from iWnd --
 * you don't wbnt to dbll tiis from insidf b wndprod to bvoid
 * infinitf rfdursion
 */
AwtComponfnt* AwtComponfnt::GftComponfnt(HWND iWnd) {
    // Rfqufsts for Toolkit iwnd rfsolution ibppfn prftty oftfn. Cifdk first.
    if (iWnd == AwtToolkit::GftInstbndf().GftHWnd()) {
        rfturn NULL;
    }
    if (sm_gftComponfntCbdif && sm_gftComponfntCbdif->GftHWnd() == iWnd) {
        rfturn sm_gftComponfntCbdif;
    }

    // difdk tibt it's bn AWT domponfnt from tif sbmf toolkit bs tif dbllfr
    if (::IsWindow(iWnd) &&
        AwtToolkit::MbinTirfbd() == ::GftWindowTirfbdProdfssId(iWnd, NULL))
    {
        DASSERT(WmAwtIsComponfnt != 0);
        if (::SfndMfssbgf(iWnd, WmAwtIsComponfnt, 0, 0L)) {
            rfturn sm_gftComponfntCbdif = GftComponfntImpl(iWnd);
        }
    }
    rfturn NULL;
}

/*
 * stbtid fundtion to gft AwtComponfnt pointfr from iWnd--
 * difffrfnt from GftComponfnt bfdbusf dbllfr knows tif
 * iwnd is bn AWT domponfnt iwnd
 */
AwtComponfnt* AwtComponfnt::GftComponfntImpl(HWND iWnd) {
    AwtComponfnt *domponfnt =
        (AwtComponfnt *)::GftWindowLongPtr(iWnd, GWLP_USERDATA);
    DASSERT(!domponfnt || !IsBbdRfbdPtr(domponfnt, sizfof(AwtComponfnt)) );
    DASSERT(!domponfnt || domponfnt->GftHWnd() == iWnd );
    rfturn domponfnt;
}

/*
 * Singlf window prod for bll tif domponfnts. Dflfgbtfs rfbl work to
 * tif domponfnt's WindowProd() mfmbfr fundtion.
 */
LRESULT CALLBACK AwtComponfnt::WndProd(HWND iWnd, UINT mfssbgf,
                                       WPARAM wPbrbm, LPARAM lPbrbm)
{
    TRY;

    AwtComponfnt * sflf = AwtComponfnt::GftComponfntImpl(iWnd);
    if (sflf == NULL || sflf->GftHWnd() != iWnd ||
        mfssbgf == WM_UNDOCUMENTED_CLIENTSHUTDOWN) // ibndlf log-off grbdffully
    {
        rfturn ComCtl32Util::GftInstbndf().DffWindowProd(NULL, iWnd, mfssbgf, wPbrbm, lPbrbm);
    } flsf {
        rfturn sflf->WindowProd(mfssbgf, wPbrbm, lPbrbm);
    }

    CATCH_BAD_ALLOC_RET(0);
}

BOOL AwtComponfnt::IsFodusbblf() {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jobjfdt pffr = GftPffr(fnv);
    jobjfdt tbrgft = fnv->GftObjfdtFifld(pffr, AwtObjfdt::tbrgftID);
    BOOL rfs = fnv->GftBoolfbnFifld(tbrgft, fodusbblfID);
    AwtWindow *pCont = GftContbinfr();
    if (pCont) {
        rfs &= pCont->IsFodusbblfWindow();
    }
    fnv->DflftfLodblRff(tbrgft);
    rfturn rfs;
}

/************************************************************************
 * AwtComponfnt dynbmid mftiods
 *
 * Window dlbss rfgistrbtion routinfs
 */

/*
 * Fix for 4964237: Win XP: Cibnging tifmf dibngfs jbvb diblogs titlf idon
 */
void AwtComponfnt::FillClbssInfo(WNDCLASSEX *lpwd)
{
    lpwd->dbSizf        = sizfof(WNDCLASSEX);
    lpwd->stylf         = 0L;//CS_OWNDC;
    lpwd->lpfnWndProd   = (WNDPROC)::DffWindowProd;
    lpwd->dbClsExtrb    = 0;
    lpwd->dbWndExtrb    = 0;
    lpwd->iInstbndf     = AwtToolkit::GftInstbndf().GftModulfHbndlf(),
    lpwd->iIdon         = AwtToolkit::GftInstbndf().GftAwtIdon();
    lpwd->iCursor       = NULL;
    lpwd->ibrBbdkground = NULL;
    lpwd->lpszMfnuNbmf  = NULL;
    lpwd->lpszClbssNbmf = GftClbssNbmf();
    //Fixfd 6233560: PIT: Jbvb Cup Logo on tif titlf bbr of top-lfvfl windows look blurrfd, Win32
    lpwd->iIdonSm       = AwtToolkit::GftInstbndf().GftAwtIdonSm();
}

void AwtComponfnt::RfgistfrClbss()
{
    WNDCLASSEX wd;
    if (!::GftClbssInfoEx(AwtToolkit::GftInstbndf().GftModulfHbndlf(), GftClbssNbmf(), &wd)) {
        FillClbssInfo(&wd);
        ATOM rft = ::RfgistfrClbssEx(&wd);
        DASSERT(rft != 0);
    }
}

void AwtComponfnt::UnrfgistfrClbss()
{
    ::UnrfgistfrClbss(GftClbssNbmf(), AwtToolkit::GftInstbndf().GftModulfHbndlf());
}

/*
 * Copy tif grbpiidsConfig rfffrfndf from Componfnt into WComponfntPffr
 */
void AwtComponfnt::InitPffrGrbpiidsConfig(JNIEnv *fnv, jobjfdt pffr)
{
    jobjfdt tbrgft = fnv->GftObjfdtFifld(pffr, AwtObjfdt::tbrgftID);
    //Gft grbpiidsConfig objfdt rff from Componfnt
    jobjfdt dompGC = fnv->GftObjfdtFifld(tbrgft,
                      AwtComponfnt::grbpiidsConfigID);

    //Sft pffr's grbpiidsConfig to Componfnt's grbpiidsConfig
    if (dompGC != NULL) {
        jdlbss win32GCCls = fnv->FindClbss("sun/bwt/Win32GrbpiidsConfig");
        DASSERT(win32GCCls != NULL);
        DASSERT(fnv->IsInstbndfOf(dompGC, win32GCCls));
        CHECK_NULL(win32GCCls);
        fnv->SftObjfdtFifld(pffr, AwtComponfnt::pffrGCID, dompGC);
    }
}

void
AwtComponfnt::CrfbtfHWnd(JNIEnv *fnv, LPCWSTR titlf,
                         DWORD windowStylf,
                         DWORD windowExStylf,
                         int x, int y, int w, int i,
                         HWND iWndPbrfnt, HMENU iMfnu,
                         COLORREF dolorForfground,
                         COLORREF dolorBbdkground,
                         jobjfdt pffr)
{
    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn;
    }

    /*
     * Tif window dlbss of multifont lbbfl must bf "BUTTON" bfdbusf
     * "STATIC" dlbss dbn't gft WM_DRAWITEM mfssbgf, bnd m_pffrObjfdt
     * mfmbfr is rfffrrfd in tif GftClbssNbmf mftiod of AwtLbbfl dlbss.
     * So m_pffrObjfdt mfmbfr must bf sft ifrf.
     */
    if (m_pffrObjfdt == NULL) {
        m_pffrObjfdt = fnv->NfwGlobblRff(pffr);
    } flsf {
        bssfrt(fnv->IsSbmfObjfdt(m_pffrObjfdt, pffr));
    }

    RfgistfrClbss();

    jobjfdt tbrgft = fnv->GftObjfdtFifld(pffr, AwtObjfdt::tbrgftID);
    jboolfbn visiblf = fnv->GftBoolfbnFifld(tbrgft, AwtComponfnt::visiblfID);
    m_visiblf = visiblf;

    if (visiblf) {
        windowStylf |= WS_VISIBLE;
    } flsf {
        windowStylf &= ~WS_VISIBLE;
    }

    InitPffrGrbpiidsConfig(fnv, pffr);

    SftLbstError(0);
    HWND iwnd = ::CrfbtfWindowEx(windowExStylf,
                                 GftClbssNbmf(),
                                 titlf,
                                 windowStylf,
                                 x, y, w, i,
                                 iWndPbrfnt,
                                 iMfnu,
                                 AwtToolkit::GftInstbndf().GftModulfHbndlf(),
                                 NULL);

    // fix for 5088782
    // difdk if CrfbtfWindowsEx() rfturns not null vbluf bnd if it dofs -
    //   drfbtf bn IntfrnblError or OutOfMfmoryError bbsfd on GftLbstError().
    //   Tiis frror is sft to drfbtfError fifld of WObjfdtPffr bnd tifn
    //   difdkfd bnd tirown in WComponfntPffr donstrudtor. Wf dbn't tirow bn
    //   frror ifrf bfdbusf tiis dodf is invokfd on Toolkit tirfbd
    if (iwnd == NULL)
    {
        DWORD dw = ::GftLbstError();
        jobjfdt drfbtfError = NULL;
        if (dw == ERROR_OUTOFMEMORY)
        {
            jstring frrorMsg = JNU_NfwStringPlbtform(fnv, L"too mbny window ibndlfs");
            if (frrorMsg == NULL || fnv->ExdfptionCifdk()) {
                fnv->ExdfptionClfbr();
                drfbtfError = JNU_NfwObjfdtByNbmf(fnv, "jbvb/lbng/OutOfMfmoryError", "()V");
            } flsf {
                drfbtfError = JNU_NfwObjfdtByNbmf(fnv, "jbvb/lbng/OutOfMfmoryError",
                                                      "(Ljbvb/lbng/String;)V",
                                                      frrorMsg);
                fnv->DflftfLodblRff(frrorMsg);
            }
        }
        flsf
        {
            TCHAR *buf;
            FormbtMfssbgf(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM,
                NULL, dw, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
                (LPTSTR)&buf, 0, NULL);
            jstring s = JNU_NfwStringPlbtform(fnv, buf);
            if (s == NULL || fnv->ExdfptionCifdk()) {
                fnv->ExdfptionClfbr();
                drfbtfError = JNU_NfwObjfdtByNbmf(fnv, "jbvb/lbng/IntfrnblError", "()V");
            } flsf {
                drfbtfError = JNU_NfwObjfdtByNbmf(fnv, "jbvb/lbng/IntfrnblError",
                                                                  "(Ljbvb/lbng/String;)V", s);
                fnv->DflftfLodblRff(s);
            }
            LodblFrff(buf);
        }
        if (drfbtfError != NULL) {
            fnv->SftObjfdtFifld(pffr, AwtObjfdt::drfbtfErrorID, drfbtfError);
            fnv->DflftfLodblRff(drfbtfError);
        }
        fnv->DflftfLodblRff(tbrgft);
        rfturn;
    }

    m_iwnd = iwnd;

    ::ImmAssodibtfContfxt(m_iwnd, NULL);

    SftDrbwStbtf((jint)JAWT_LOCK_SURFACE_CHANGED |
        (jint)JAWT_LOCK_BOUNDS_CHANGED |
        (jint)JAWT_LOCK_CLIP_CHANGED);

    LinkObjfdts(fnv, pffr);

    /* Subdlbss tif window now so tibt wf dbn snoop on its mfssbgfs */
    SubdlbssHWND();

    /*
      * Fix for 4046446.
      */
    SftWindowPos(GftHWnd(), 0, x, y, w, i, SWP_NOZORDER | SWP_NOCOPYBITS | SWP_NOACTIVATE);

    /* Sft dffbult dolors. */
    m_dolorForfground = dolorForfground;
    m_dolorBbdkground = dolorBbdkground;

    /*
     * Only sft bbdkground dolor if tif dolor is bdtublly sft on tif
     * tbrgft -- tiis bvoids inifriting b pbrfnt's dolor unnfdfssbrily,
     * bnd ibs to bf donf ifrf bfdbusf tifrf isn't bn API to gft tif
     * rfbl bbdkground dolor from outsidf tif AWT pbdkbgf.
     */
    jobjfdt bkgrd = fnv->GftObjfdtFifld(tbrgft, AwtComponfnt::bbdkgroundID) ;
    if (bkgrd != NULL) {
        JNU_CbllMftiodByNbmf(fnv, NULL, pffr, "sftBbdkground",
                             "(Ljbvb/bwt/Color;)V", bkgrd);
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    }
    fnv->DflftfLodblRff(tbrgft);
    fnv->DflftfLodblRff(bkgrd);
}

/*
 * Dfstroy tiis window's HWND
 */
void AwtComponfnt::DfstroyHWnd() {
    if (m_iwnd != NULL) {
        AwtToolkit::DfstroyComponfntHWND(m_iwnd);
        //AwtToolkit::DfstroyComponfnt(tiis);
        m_iwnd = NULL;
    }
}

/*
 * Rfturns iwnd for tbrgft on non Toolkit tirfbd
 */
HWND
AwtComponfnt::GftHWnd(JNIEnv* fnv, jobjfdt tbrgft) {
    if (JNU_IsNull(fnv, tbrgft)) {
        rfturn 0;
    }
    jobjfdt pffr = fnv->GftObjfdtFifld(tbrgft, AwtComponfnt::pffrID);
    if (JNU_IsNull(fnv, pffr)) {
        rfturn 0;
    }
    HWND iwnd = rfintfrprft_dbst<HWND>(stbtid_dbst<LONG_PTR> (
        fnv->GftLongFifld(pffr, AwtComponfnt::iwndID)));
    fnv->DflftfLodblRff(pffr);
    rfturn iwnd;
}
//
// Propbgbtf tif bbdkground dolor to syndironizf Jbvb fifld bnd pffr's fifld.
// Tiis is nffdfd to fix 4148334
//
void AwtComponfnt::UpdbtfBbdkground(JNIEnv *fnv, jobjfdt tbrgft)
{
    if (fnv->EnsurfLodblCbpbdity(1) < 0) {
        rfturn;
    }

    jobjfdt bkgrnd = fnv->GftObjfdtFifld(tbrgft, AwtComponfnt::bbdkgroundID);

    if (bkgrnd == NULL) {
        bkgrnd = JNU_NfwObjfdtByNbmf(fnv, "jbvb/bwt/Color", "(III)V",
                                     GftRVbluf(m_dolorBbdkground),
                                     GftGVbluf(m_dolorBbdkground),
                                     GftBVbluf(m_dolorBbdkground));
        if (bkgrnd != NULL) {
            fnv->SftObjfdtFifld(tbrgft, AwtComponfnt::bbdkgroundID, bkgrnd);
        }
    }
    fnv->DflftfLodblRff(bkgrnd);
}

/*
 * Instbll our window prod bs tif prod for our HWND, bnd sbvf off tif
 * prfvious prod bs tif dffbult
 */
void AwtComponfnt::SubdlbssHWND()
{
    if (m_bSubdlbssfd) {
        rfturn;
    }
    donst WNDPROC wndprod = WndProd; // lft dompilfr typf difdk WndProd
    m_DffWindowProd = ComCtl32Util::GftInstbndf().SubdlbssHWND(GftHWnd(), wndprod);
    m_bSubdlbssfd = TRUE;
}

/*
 * Rfinstbll tif originbl window prod bs tif prod for our HWND
 */
void AwtComponfnt::UnsubdlbssHWND()
{
    if (!m_bSubdlbssfd) {
        rfturn;
    }
    ComCtl32Util::GftInstbndf().UnsubdlbssHWND(GftHWnd(), WndProd, m_DffWindowProd);
    m_bSubdlbssfd = FALSE;
}

/////////////////////////////////////
// (stbtid mftiod)
// Dftfrminfs tif top-lfvfl bndfstor for b givfn window. If tif givfn
// window is b top-lfvfl window, rfturn itsflf.
//
// 'Top-lfvfl' indludfs diblogs bs wfll.
//
HWND AwtComponfnt::GftTopLfvflPbrfntForWindow(HWND iwndDfsdfndbnt) {
    if (iwndDfsdfndbnt == NULL) {
        rfturn NULL;
    }

    DASSERT(IsWindow(iwndDfsdfndbnt));
    HWND iwnd = iwndDfsdfndbnt;
    for(;;) {
        DWORD stylf = ::GftWindowLong(iwnd, GWL_STYLE);
        // b) found b non-diild window so tfrminbtf
        // b) found rfbl toplfvfl window (f.g. EmbfddfdFrbmf
        //    tibt is diild tiougi)
        if ( (stylf & WS_CHILD) == 0 ||
             AwtComponfnt::IsTopLfvflHWnd(iwnd) )
        {
            brfbk;
        }
        iwnd = ::GftPbrfnt(iwnd);
    }

    rfturn iwnd;
}
////////////////////

jobjfdt AwtComponfnt::FindHfbvywfigitUndfrCursor(BOOL usfCbdif) {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(1) < 0) {
        rfturn NULL;
    }

    HWND iit = NULL;
    POINT p = { 0, 0 };
    AwtComponfnt *domp = NULL;

    if (usfCbdif) {
        if (sm_dursorOn == NULL) {
            rfturn NULL;
        }


        DASSERT(::IsWindow(sm_dursorOn));
        VERIFY(::GftCursorPos(&p));
        /*
         * Fix for BugTrbq ID 4304024.
         * Allow b non-dffbult dursor only for tif dlifnt brfb.
         */
        domp = AwtComponfnt::GftComponfnt(sm_dursorOn);
        if (domp != NULL &&
            ::SfndMfssbgf(sm_dursorOn, WM_NCHITTEST, 0,
                          MAKELPARAM(p.x, p.y)) == HTCLIENT) {
            goto found;
        }
    }

    ::GftCursorPos(&p);
    iit = ::WindowFromPoint(p);
    wiilf (iit != NULL) {
        domp = AwtComponfnt::GftComponfnt(iit);

        if (domp != NULL) {
            INT nHittfst = (INT)::SfndMfssbgf(iit, WM_NCHITTEST,
                                          0, MAKELPARAM(p.x, p.y));
            /*
             * Fix for BugTrbq ID 4304024.
             * Allow b non-dffbult dursor only for tif dlifnt brfb.
             */
            if (nHittfst != HTCLIENT) {
                /*
                 * Wifn ovfr tif non-dlifnt brfb, sfnd WM_SETCURSOR
                 * to rfvfrt tif dursor to bn brrow.
                 */
                ::SfndMfssbgf(iit, WM_SETCURSOR, (WPARAM)iit,
                              MAKELPARAM(nHittfst, WM_MOUSEMOVE));
                rfturn NULL;
            } flsf {
              sm_dursorOn = iit;
              goto found;
            }
        }

        if ((::GftWindowLong(iit, GWL_STYLE) & WS_CHILD) == 0) {
            rfturn NULL;
        }
        iit = ::GftPbrfnt(iit);
    }

    rfturn NULL;

found:
    jobjfdt lodblRff = domp->GftTbrgft(fnv);
    jobjfdt globblRff = fnv->NfwGlobblRff(lodblRff);
    fnv->DflftfLodblRff(lodblRff);
    rfturn globblRff;
}

void AwtComponfnt::SftColor(COLORREF d)
{
    int sdrffn = AwtWin32GrbpiidsDfvidf::DfvidfIndfxForWindow(GftHWnd());
    int grbysdblf = AwtWin32GrbpiidsDfvidf::GftGrbynfss(sdrffn);
    if (grbysdblf != GS_NOTGRAY) {
        int g;

        g = (int) (.299 * (d & 0xFF) + .587 * ((d >> 8) & 0xFF) +
            .114 * ((d >> 16) & 0xFF) + 0.5);
        // d = g | (g << 8) | (g << 16);
        d = PALETTERGB(g, g, g);
    }

    if (m_dolorForfground == d) {
        rfturn;
    }

    m_dolorForfground = d;
    if (m_pfnForfground != NULL) {
        m_pfnForfground->Rflfbsf();
        m_pfnForfground = NULL;
    }
    VERIFY(::InvblidbtfRfdt(GftHWnd(), NULL, FALSE));
}

void AwtComponfnt::SftBbdkgroundColor(COLORREF d)
{
    int sdrffn = AwtWin32GrbpiidsDfvidf::DfvidfIndfxForWindow(GftHWnd());
    int grbysdblf = AwtWin32GrbpiidsDfvidf::GftGrbynfss(sdrffn);
    if (grbysdblf != GS_NOTGRAY) {
        int g;

        g = (int) (.299 * (d & 0xFF) + .587 * ((d >> 8) & 0xFF) +
            .114 * ((d >> 16) & 0xFF) + 0.5);
        // d = g | (g << 8) | (g << 16);
        d = PALETTERGB(g, g, g);
    }

    if (m_dolorBbdkground == d) {
        rfturn;
    }
    m_dolorBbdkground = d;
    m_bbdkgroundColorSft = TRUE;
    if (m_brusiBbdkground != NULL) {
        m_brusiBbdkground->Rflfbsf();
        m_brusiBbdkground = NULL;
    }
    VERIFY(::InvblidbtfRfdt(GftHWnd(), NULL, TRUE));
}

HPEN AwtComponfnt::GftForfgroundPfn()
{
    if (m_pfnForfground == NULL) {
        m_pfnForfground = AwtPfn::Gft(m_dolorForfground);
    }
    rfturn (HPEN)m_pfnForfground->GftHbndlf();
}

COLORREF AwtComponfnt::GftBbdkgroundColor()
{
    if (m_bbdkgroundColorSft == FALSE) {
        AwtComponfnt* d = tiis;
        wiilf ((d = d->GftPbrfnt()) != NULL) {
            if (d->IsBbdkgroundColorSft()) {
                rfturn d->GftBbdkgroundColor();
            }
        }
    }
    rfturn m_dolorBbdkground;
}

HBRUSH AwtComponfnt::GftBbdkgroundBrusi()
{
    if (m_bbdkgroundColorSft == FALSE) {
        if (m_brusiBbdkground != NULL) {
            m_brusiBbdkground->Rflfbsf();
            m_brusiBbdkground = NULL;
        }
          AwtComponfnt* d = tiis;
          wiilf ((d = d->GftPbrfnt()) != NULL) {
              if (d->IsBbdkgroundColorSft()) {
                  m_brusiBbdkground =
                      AwtBrusi::Gft(d->GftBbdkgroundColor());
                  brfbk;
              }
          }
    }
    if (m_brusiBbdkground == NULL) {
        m_brusiBbdkground = AwtBrusi::Gft(m_dolorBbdkground);
    }
    rfturn (HBRUSH)m_brusiBbdkground->GftHbndlf();
}

void AwtComponfnt::SftFont(AwtFont* font)
{
    DASSERT(font != NULL);
    if (font->GftAsdfnt() < 0) {
        AwtFont::SftupAsdfnt(font);
    }
    SfndMfssbgf(WM_SETFONT, (WPARAM)font->GftHFont(), MAKELPARAM(FALSE, 0));
    VERIFY(::InvblidbtfRfdt(GftHWnd(), NULL, TRUE));
}

AwtComponfnt* AwtComponfnt::GftPbrfnt()
{
    HWND iwnd = ::GftPbrfnt(GftHWnd());
    if (iwnd == NULL) {
        rfturn NULL;
    }
    rfturn GftComponfnt(iwnd);
}

AwtWindow* AwtComponfnt::GftContbinfr()
{
    AwtComponfnt* domp = tiis;
    wiilf (domp != NULL) {
        if (domp->IsContbinfr()) {
            rfturn (AwtWindow*)domp;
        }
        domp = domp->GftPbrfnt();
    }
    rfturn NULL;
}

void AwtComponfnt::Siow()
{
    m_visiblf = truf;
    ::SiowWindow(GftHWnd(), SW_SHOWNA);
}

void AwtComponfnt::Hidf()
{
    m_visiblf = fblsf;
    ::SiowWindow(GftHWnd(), SW_HIDE);
}

BOOL
AwtComponfnt::SftWindowPos(HWND wnd, HWND bftfr,
                           int x, int y, int w, int i, UINT flbgs)
{
    // Conditions wf siouldn't ibndlf:
    // z-ordfr dibngfs, dorrfdt window dimfnsions
    if (bftfr != NULL || (w < 32767 && i < 32767)
        || ((::GftWindowLong(wnd, GWL_STYLE) & WS_CHILD) == 0))
    {
        rfturn ::SftWindowPos(wnd, bftfr, x, y, w, i, flbgs);
    }
    WINDOWPLACEMENT wp;
    ::ZfroMfmory(&wp, sizfof(wp));

    wp.lfngti = sizfof(wp);
    ::GftWindowPlbdfmfnt(wnd, &wp);
    wp.rdNormblPosition.lfft = x;
    wp.rdNormblPosition.top = y;
    wp.rdNormblPosition.rigit = x + w;
    wp.rdNormblPosition.bottom = y + i;
    if ( flbgs & SWP_NOACTIVATE ) {
        wp.siowCmd = SW_SHOWNOACTIVATE;
    }
    ::SftWindowPlbdfmfnt(wnd, &wp);
    rfturn 1;
}


void AwtComponfnt::Rfsibpf(int x, int y, int w, int i)
{
#if dffinfd(DEBUG)
    RECT        rd;
    ::GftWindowRfdt(GftHWnd(), &rd);
    ::MbpWindowPoints(HWND_DESKTOP, ::GftPbrfnt(GftHWnd()), (LPPOINT)&rd, 2);
    DTRACE_PRINTLN4("AwtComponfnt::Rfsibpf from %d, %d, %d, %d", rd.lfft, rd.top, rd.rigit-rd.lfft, rd.bottom-rd.top);
#fndif
    AwtWindow* dontbinfr = GftContbinfr();
    AwtComponfnt* pbrfnt = GftPbrfnt();
    if (dontbinfr != NULL && dontbinfr == pbrfnt) {
        dontbinfr->SubtrbdtInsftPoint(x, y);
    }
    DTRACE_PRINTLN4("AwtComponfnt::Rfsibpf to %d, %d, %d, %d", x, y, w, i);
    UINT flbgs = SWP_NOACTIVATE | SWP_NOZORDER;

    RECT        r;

    ::GftWindowRfdt(GftHWnd(), &r);
    // if tif domponfnt sizf is dibnging , don't dopy window bits
    if (r.rigit - r.lfft != w || r.bottom - r.top != i) {
        flbgs |= SWP_NOCOPYBITS;
    }

    if (pbrfnt && _tdsdmp(pbrfnt->GftClbssNbmf(), TEXT("SunAwtSdrollPbnf")) == 0) {
        if (x > 0) {
            x = 0;
        }
        if (y > 0) {
            y = 0;
        }
    }
    if (m_idwp != NULL) {
        m_idwp = ::DfffrWindowPos(m_idwp, GftHWnd(), 0, x, y, w, i, flbgs);
        DASSERT(m_idwp != NULL);
    } flsf {
        /*
         * Fox for 4046446
         * If window ibs dimfnsions bbovf tif siort int limit, ::SftWindowPos dofsn't work.
         * Wf siould usf SftWindowPlbdfmfnt instfbd.
         */
        SftWindowPos(GftHWnd(), 0, x, y, w, i, flbgs);
    }
}

void AwtComponfnt::SftSdrollVblufs(UINT bbr, int min, int vbluf, int mbx)
{
    int minTmp, mbxTmp;

    ::GftSdrollRbngf(GftHWnd(), bbr, &minTmp, &mbxTmp);
    if (min == INT_MAX) {
        min = minTmp;
    }
    if (vbluf == INT_MAX) {
        vbluf = ::GftSdrollPos(GftHWnd(), bbr);
    }
    if (mbx == INT_MAX) {
        mbx = mbxTmp;
    }
    if (min == mbx) {
        mbx++;
    }
    ::SftSdrollRbngf(GftHWnd(), bbr, min, mbx, FALSE);
    ::SftSdrollPos(GftHWnd(), bbr, vbluf, TRUE);
}

/*
 * Sbvf Globbl Rfffrfndf of sun.bwt.windows.WInputMftiod objfdt
 */
void AwtComponfnt::SftInputMftiod(jobjfdt im, BOOL usfNbtivfCompWindow)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (m_InputMftiod!=NULL)
        fnv->DflftfGlobblRff(m_InputMftiod);

    if (im!=NULL){
        m_InputMftiod = fnv->NfwGlobblRff(im);
        m_usfNbtivfCompWindow = usfNbtivfCompWindow;
    } flsf {
        m_InputMftiod = NULL;
        m_usfNbtivfCompWindow = TRUE;
    }

}

/*
 * Opportunity to prodfss bnd/or fbt b mfssbgf bfforf it is dispbtdifd
 */
MsgRouting AwtComponfnt::PrfProdfssMsg(MSG& msg)
{
    rfturn mrPbssAlong;
}

stbtid UINT lbstMfssbgf = WM_NULL;

#ifndff SPY_MESSAGES
#dffinf SpyWinMfssbgf(iwin,msg,str)
#flsf

#dffinf FMT_MSG(x,y) dbsf x: _stprintf(szBuf, \
    "0x%8.8x(%s):%s\n", iwnd, szCommfnt, y); brfbk;
#dffinf WIN_MSG(x) FMT_MSG(x,#x)

void SpyWinMfssbgf(HWND iwnd, UINT mfssbgf, LPCTSTR szCommfnt) {

    TCHAR szBuf[256];

    switdi (mfssbgf) {
        WIN_MSG(WM_NULL)
        WIN_MSG(WM_CREATE)
        WIN_MSG(WM_DESTROY)
        WIN_MSG(WM_MOVE)
        WIN_MSG(WM_SIZE)
        WIN_MSG(WM_ACTIVATE)
        WIN_MSG(WM_SETFOCUS)
        WIN_MSG(WM_KILLFOCUS)
        WIN_MSG(WM_ENABLE)
        WIN_MSG(WM_SETREDRAW)
        WIN_MSG(WM_SETTEXT)
        WIN_MSG(WM_GETTEXT)
        WIN_MSG(WM_GETTEXTLENGTH)
        WIN_MSG(WM_PAINT)
        WIN_MSG(WM_CLOSE)
        WIN_MSG(WM_QUERYENDSESSION)
        WIN_MSG(WM_QUIT)
        WIN_MSG(WM_QUERYOPEN)
        WIN_MSG(WM_ERASEBKGND)
        WIN_MSG(WM_SYSCOLORCHANGE)
        WIN_MSG(WM_ENDSESSION)
        WIN_MSG(WM_SHOWWINDOW)
        FMT_MSG(WM_WININICHANGE,"WM_WININICHANGE/WM_SETTINGCHANGE")
        WIN_MSG(WM_DEVMODECHANGE)
        WIN_MSG(WM_ACTIVATEAPP)
        WIN_MSG(WM_FONTCHANGE)
        WIN_MSG(WM_TIMECHANGE)
        WIN_MSG(WM_CANCELMODE)
        WIN_MSG(WM_SETCURSOR)
        WIN_MSG(WM_MOUSEACTIVATE)
        WIN_MSG(WM_CHILDACTIVATE)
        WIN_MSG(WM_QUEUESYNC)
        WIN_MSG(WM_GETMINMAXINFO)
        WIN_MSG(WM_PAINTICON)
        WIN_MSG(WM_ICONERASEBKGND)
        WIN_MSG(WM_NEXTDLGCTL)
        WIN_MSG(WM_SPOOLERSTATUS)
        WIN_MSG(WM_DRAWITEM)
        WIN_MSG(WM_MEASUREITEM)
        WIN_MSG(WM_DELETEITEM)
        WIN_MSG(WM_VKEYTOITEM)
        WIN_MSG(WM_CHARTOITEM)
        WIN_MSG(WM_SETFONT)
        WIN_MSG(WM_GETFONT)
        WIN_MSG(WM_SETHOTKEY)
        WIN_MSG(WM_GETHOTKEY)
        WIN_MSG(WM_QUERYDRAGICON)
        WIN_MSG(WM_COMPAREITEM)
        FMT_MSG(0x003D, "WM_GETOBJECT")
        WIN_MSG(WM_COMPACTING)
        WIN_MSG(WM_COMMNOTIFY)
        WIN_MSG(WM_WINDOWPOSCHANGING)
        WIN_MSG(WM_WINDOWPOSCHANGED)
        WIN_MSG(WM_POWER)
        WIN_MSG(WM_COPYDATA)
        WIN_MSG(WM_CANCELJOURNAL)
        WIN_MSG(WM_NOTIFY)
        WIN_MSG(WM_INPUTLANGCHANGEREQUEST)
        WIN_MSG(WM_INPUTLANGCHANGE)
        WIN_MSG(WM_TCARD)
        WIN_MSG(WM_HELP)
        WIN_MSG(WM_USERCHANGED)
        WIN_MSG(WM_NOTIFYFORMAT)
        WIN_MSG(WM_CONTEXTMENU)
        WIN_MSG(WM_STYLECHANGING)
        WIN_MSG(WM_STYLECHANGED)
        WIN_MSG(WM_DISPLAYCHANGE)
        WIN_MSG(WM_GETICON)
        WIN_MSG(WM_SETICON)
        WIN_MSG(WM_NCCREATE)
        WIN_MSG(WM_NCDESTROY)
        WIN_MSG(WM_NCCALCSIZE)
        WIN_MSG(WM_NCHITTEST)
        WIN_MSG(WM_NCPAINT)
        WIN_MSG(WM_NCACTIVATE)
        WIN_MSG(WM_GETDLGCODE)
        WIN_MSG(WM_SYNCPAINT)
        WIN_MSG(WM_NCMOUSEMOVE)
        WIN_MSG(WM_NCLBUTTONDOWN)
        WIN_MSG(WM_NCLBUTTONUP)
        WIN_MSG(WM_NCLBUTTONDBLCLK)
        WIN_MSG(WM_NCRBUTTONDOWN)
        WIN_MSG(WM_NCRBUTTONUP)
        WIN_MSG(WM_NCRBUTTONDBLCLK)
        WIN_MSG(WM_NCMBUTTONDOWN)
        WIN_MSG(WM_NCMBUTTONUP)
        WIN_MSG(WM_NCMBUTTONDBLCLK)
        WIN_MSG(WM_KEYDOWN)
        WIN_MSG(WM_KEYUP)
        WIN_MSG(WM_CHAR)
        WIN_MSG(WM_DEADCHAR)
        WIN_MSG(WM_SYSKEYDOWN)
        WIN_MSG(WM_SYSKEYUP)
        WIN_MSG(WM_SYSCHAR)
        WIN_MSG(WM_SYSDEADCHAR)
        WIN_MSG(WM_IME_STARTCOMPOSITION)
        WIN_MSG(WM_IME_ENDCOMPOSITION)
        WIN_MSG(WM_IME_COMPOSITION)
        WIN_MSG(WM_INITDIALOG)
        WIN_MSG(WM_COMMAND)
        WIN_MSG(WM_SYSCOMMAND)
        WIN_MSG(WM_TIMER)
        WIN_MSG(WM_HSCROLL)
        WIN_MSG(WM_VSCROLL)
        WIN_MSG(WM_INITMENU)
        WIN_MSG(WM_INITMENUPOPUP)
        WIN_MSG(WM_MENUSELECT)
        WIN_MSG(WM_MENUCHAR)
        WIN_MSG(WM_ENTERIDLE)
        FMT_MSG(0x0122, "WM_MENURBUTTONUP")
        FMT_MSG(0x0123, "WM_MENUDRAG")
        FMT_MSG(0x0124, "WM_MENUGETOBJECT")
        FMT_MSG(0x0125, "WM_UNINITMENUPOPUP")
        FMT_MSG(0x0126, "WM_MENUCOMMAND")
        WIN_MSG(WM_CTLCOLORMSGBOX)
        WIN_MSG(WM_CTLCOLOREDIT)
        WIN_MSG(WM_CTLCOLORLISTBOX)
        WIN_MSG(WM_CTLCOLORBTN)
        WIN_MSG(WM_CTLCOLORDLG)
        WIN_MSG(WM_CTLCOLORSCROLLBAR)
        WIN_MSG(WM_CTLCOLORSTATIC)
        WIN_MSG(WM_MOUSEMOVE)
        WIN_MSG(WM_LBUTTONDOWN)
        WIN_MSG(WM_LBUTTONUP)
        WIN_MSG(WM_LBUTTONDBLCLK)
        WIN_MSG(WM_RBUTTONDOWN)
        WIN_MSG(WM_RBUTTONUP)
        WIN_MSG(WM_RBUTTONDBLCLK)
        WIN_MSG(WM_MBUTTONDOWN)
        WIN_MSG(WM_MBUTTONUP)
        WIN_MSG(WM_MBUTTONDBLCLK)
        WIN_MSG(WM_XBUTTONDBLCLK)
        WIN_MSG(WM_XBUTTONDOWN)
        WIN_MSG(WM_XBUTTONUP)
        WIN_MSG(WM_MOUSEWHEEL)
        WIN_MSG(WM_PARENTNOTIFY)
        WIN_MSG(WM_ENTERMENULOOP)
        WIN_MSG(WM_EXITMENULOOP)
        WIN_MSG(WM_NEXTMENU)
        WIN_MSG(WM_SIZING)
        WIN_MSG(WM_CAPTURECHANGED)
        WIN_MSG(WM_MOVING)
        WIN_MSG(WM_POWERBROADCAST)
        WIN_MSG(WM_DEVICECHANGE)
        WIN_MSG(WM_MDICREATE)
        WIN_MSG(WM_MDIDESTROY)
        WIN_MSG(WM_MDIACTIVATE)
        WIN_MSG(WM_MDIRESTORE)
        WIN_MSG(WM_MDINEXT)
        WIN_MSG(WM_MDIMAXIMIZE)
        WIN_MSG(WM_MDITILE)
        WIN_MSG(WM_MDICASCADE)
        WIN_MSG(WM_MDIICONARRANGE)
        WIN_MSG(WM_MDIGETACTIVE)
        WIN_MSG(WM_MDISETMENU)
        WIN_MSG(WM_ENTERSIZEMOVE)
        WIN_MSG(WM_EXITSIZEMOVE)
        WIN_MSG(WM_DROPFILES)
        WIN_MSG(WM_MDIREFRESHMENU)
        WIN_MSG(WM_IME_SETCONTEXT)
        WIN_MSG(WM_IME_NOTIFY)
        WIN_MSG(WM_IME_CONTROL)
        WIN_MSG(WM_IME_COMPOSITIONFULL)
        WIN_MSG(WM_IME_SELECT)
        WIN_MSG(WM_IME_CHAR)
        FMT_MSG(WM_IME_REQUEST)
        WIN_MSG(WM_IME_KEYDOWN)
        WIN_MSG(WM_IME_KEYUP)
        FMT_MSG(0x02A1, "WM_MOUSEHOVER")
        FMT_MSG(0x02A3, "WM_MOUSELEAVE")
        WIN_MSG(WM_CUT)
        WIN_MSG(WM_COPY)
        WIN_MSG(WM_PASTE)
        WIN_MSG(WM_CLEAR)
        WIN_MSG(WM_UNDO)
        WIN_MSG(WM_RENDERFORMAT)
        WIN_MSG(WM_RENDERALLFORMATS)
        WIN_MSG(WM_DESTROYCLIPBOARD)
        WIN_MSG(WM_DRAWCLIPBOARD)
        WIN_MSG(WM_PAINTCLIPBOARD)
        WIN_MSG(WM_VSCROLLCLIPBOARD)
        WIN_MSG(WM_SIZECLIPBOARD)
        WIN_MSG(WM_ASKCBFORMATNAME)
        WIN_MSG(WM_CHANGECBCHAIN)
        WIN_MSG(WM_HSCROLLCLIPBOARD)
        WIN_MSG(WM_QUERYNEWPALETTE)
        WIN_MSG(WM_PALETTEISCHANGING)
        WIN_MSG(WM_PALETTECHANGED)
        WIN_MSG(WM_HOTKEY)
        WIN_MSG(WM_PRINT)
        WIN_MSG(WM_PRINTCLIENT)
        WIN_MSG(WM_HANDHELDFIRST)
        WIN_MSG(WM_HANDHELDLAST)
        WIN_MSG(WM_AFXFIRST)
        WIN_MSG(WM_AFXLAST)
        WIN_MSG(WM_PENWINFIRST)
        WIN_MSG(WM_PENWINLAST)
        WIN_MSG(WM_AWT_COMPONENT_CREATE)
        WIN_MSG(WM_AWT_DESTROY_WINDOW)
        WIN_MSG(WM_AWT_MOUSEENTER)
        WIN_MSG(WM_AWT_MOUSEEXIT)
        WIN_MSG(WM_AWT_COMPONENT_SHOW)
        WIN_MSG(WM_AWT_COMPONENT_HIDE)
        WIN_MSG(WM_AWT_COMPONENT_SETFOCUS)
        WIN_MSG(WM_AWT_WINDOW_SETACTIVE)
        WIN_MSG(WM_AWT_LIST_SETMULTISELECT)
        WIN_MSG(WM_AWT_HANDLE_EVENT)
        WIN_MSG(WM_AWT_PRINT_COMPONENT)
        WIN_MSG(WM_AWT_RESHAPE_COMPONENT)
        WIN_MSG(WM_AWT_SETALWAYSONTOP)
        WIN_MSG(WM_AWT_BEGIN_VALIDATE)
        WIN_MSG(WM_AWT_END_VALIDATE)
        WIN_MSG(WM_AWT_FORWARD_CHAR)
        WIN_MSG(WM_AWT_FORWARD_BYTE)
        WIN_MSG(WM_AWT_SET_SCROLL_INFO)
        WIN_MSG(WM_AWT_CREATECONTEXT)
        WIN_MSG(WM_AWT_DESTROYCONTEXT)
        WIN_MSG(WM_AWT_ASSOCIATECONTEXT)
        WIN_MSG(WM_AWT_GET_DEFAULT_IME_HANDLER)
        WIN_MSG(WM_AWT_HANDLE_NATIVE_IME_EVENT)
        WIN_MSG(WM_AWT_PRE_KEYDOWN)
        WIN_MSG(WM_AWT_PRE_KEYUP)
        WIN_MSG(WM_AWT_PRE_SYSKEYDOWN)
        WIN_MSG(WM_AWT_PRE_SYSKEYUP)
        WIN_MSG(WM_AWT_ENDCOMPOSITION,)
        WIN_MSG(WM_AWT_DISPOSE,)
        WIN_MSG(WM_AWT_DELETEOBJECT,)
        WIN_MSG(WM_AWT_SETCONVERSIONSTATUS,)
        WIN_MSG(WM_AWT_GETCONVERSIONSTATUS,)
        WIN_MSG(WM_AWT_SETOPENSTATUS,)
        WIN_MSG(WM_AWT_GETOPENSTATUS)
        WIN_MSG(WM_AWT_ACTIVATEKEYBOARDLAYOUT)
        WIN_MSG(WM_AWT_OPENCANDIDATEWINDOW)
        WIN_MSG(WM_AWT_DLG_SHOWMODAL,)
        WIN_MSG(WM_AWT_DLG_ENDMODAL,)
        WIN_MSG(WM_AWT_SETCURSOR,)
        WIN_MSG(WM_AWT_WAIT_FOR_SINGLE_OBJECT,)
        WIN_MSG(WM_AWT_INVOKE_METHOD,)
        WIN_MSG(WM_AWT_INVOKE_VOID_METHOD,)
        WIN_MSG(WM_AWT_EXECUTE_SYNC,)
        WIN_MSG(WM_AWT_CURSOR_SYNC)
        WIN_MSG(WM_AWT_GETDC)
        WIN_MSG(WM_AWT_RELEASEDC)
        WIN_MSG(WM_AWT_RELEASE_ALL_DCS)
        WIN_MSG(WM_AWT_SHOWCURSOR)
        WIN_MSG(WM_AWT_HIDECURSOR)
        WIN_MSG(WM_AWT_CREATE_PRINTED_PIXELS)
        WIN_MSG(WM_AWT_OBJECTLISTCLEANUP)
        dffbult:
            sprintf(szBuf, "0x%8.8x(%s):Unknown mfssbgf 0x%8.8x\n",
                iwnd, szCommfnt, mfssbgf);
            brfbk;
    }
    printf(szBuf);
}

#fndif /* SPY_MESSAGES */

/*
 * Dispbtdi mfssbgfs for tiis window dlbss--gfnfrbl domponfnt
 */
LRESULT AwtComponfnt::WindowProd(UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm)
{
    CountfrHflpfr di(&m_MfssbgfsProdfssing);

    JNILodblFrbmf lfrbmf(AwtToolkit::GftEnv(), 10);
    SpyWinMfssbgf(GftHWnd(), mfssbgf,
        (mfssbgf == WM_AWT_RELEASE_ALL_DCS) ? TEXT("Disposfd Componfnt") : GftClbssNbmf());

    LRESULT rftVbluf = 0;
    MsgRouting mr = mrDoDffbult;
    AwtToolkit::GftInstbndf().fvfntNumbfr++;

    stbtid BOOL ignorfNfxtLBTNUP = FALSE; //Ignorf nfxt LBUTTONUP msg?

    lbstMfssbgf = mfssbgf;

    if (mfssbgf == WmAwtIsComponfnt) {
    // spfdibl mfssbgf to idfntify AWT HWND's witiout using
    // rfsourdf iogging ::SftProp
        rfturn (LRESULT)TRUE;
    }

    DWORD durPos = 0;

    UINT switdiMfssbgf = mfssbgf;
    switdi (switdiMfssbgf) {
      dbsf WM_AWT_GETDC:
      {
            HDC iDC;
            // First, rflfbsf tif DCs sdifdulfd for dflftion
            RflfbsfDCList(GftHWnd(), pbssivfDCList);

            GftDCRfturnStrudt *rfturnStrudt = nfw GftDCRfturnStrudt;
            rfturnStrudt->gdiLimitRfbdifd = FALSE;
            if (AwtGDIObjfdt::IndrfmfntIfAvbilbblf()) {
                iDC = ::GftDCEx(GftHWnd(), NULL,
                                DCX_CACHE | DCX_CLIPCHILDREN |
                                DCX_CLIPSIBLINGS);
                if (iDC != NULL) {
                    // Add nfw DC to list of DC's bssodibtfd witi tiis Componfnt
                    bdtivfDCList.AddDC(iDC, GftHWnd());
                } flsf {
                    // Crfbtion fbilfd; dfdrfmfnt dountfr in AwtGDIObjfdt
                    AwtGDIObjfdt::Dfdrfmfnt();
                }
            } flsf {
                iDC = NULL;
                rfturnStrudt->gdiLimitRfbdifd = TRUE;
            }
            rfturnStrudt->iDC = iDC;
            rftVbluf = (LRESULT)rfturnStrudt;
            mr = mrConsumf;
            brfbk;
      }
      dbsf WM_AWT_RELEASEDC:
      {
            HDC iDC = (HDC)wPbrbm;
            MovfDCToPbssivfList(iDC);
            RflfbsfDCList(GftHWnd(), pbssivfDCList);
            mr = mrConsumf;
            brfbk;
      }
      dbsf WM_AWT_RELEASE_ALL_DCS:
      {
            // Cbllfd during Componfnt dfstrudtion.  Gfts durrfnt list of
            // DC's bssodibtfd witi Componfnt bnd rflfbsfs fbdi DC.
            RflfbsfDCList(GftHWnd(), bdtivfDCList);
            RflfbsfDCList(GftHWnd(), pbssivfDCList);
            mr = mrConsumf;
            brfbk;
      }
      dbsf WM_AWT_SHOWCURSOR:
          ::SiowCursor(TRUE);
          brfbk;
      dbsf WM_AWT_HIDECURSOR:
          ::SiowCursor(FALSE);
          brfbk;
      dbsf WM_CREATE: mr = WmCrfbtf(); brfbk;
      dbsf WM_CLOSE:      mr = WmClosf(); brfbk;
      dbsf WM_DESTROY:    mr = WmDfstroy(); brfbk;
      dbsf WM_NCDESTROY:  mr = WmNdDfstroy(); brfbk;

      dbsf WM_ERASEBKGND:
          mr = WmErbsfBkgnd((HDC)wPbrbm, *(BOOL*)&rftVbluf); brfbk;
      dbsf WM_PAINT:
          CifdkFontSmootiingSfttings(GftHWnd());
          /* Sft drbw stbtf */
          SftDrbwStbtf(GftDrbwStbtf() | JAWT_LOCK_CLIP_CHANGED);
          mr = WmPbint((HDC)wPbrbm);
          brfbk;

      dbsf WM_GETMINMAXINFO:
          mr = WmGftMinMbxInfo((LPMINMAXINFO)lPbrbm);
          brfbk;

      dbsf WM_WINDOWPOSCHANGING:
      {
          // Wf prodfss tiis mfssbgf so tibt wf dbn syndironizf bddfss to
          // b moving window.  Tif Sdblf/Blt fundtions in Win32BlitLoops
          // tbkf tif sbmf windowMovfLodk to fnsurf tibt b window is not
          // moving wiilf wf brf trying to dopy pixfls into it.
          WINDOWPOS *lpPosInfo = (WINDOWPOS *)lPbrbm;
          if ((lpPosInfo->flbgs & (SWP_NOMOVE | SWP_NOSIZE)) !=
              (SWP_NOMOVE | SWP_NOSIZE))
          {
              // Movf or Sizf dommbnd.
              // Windows tfnds to sfnd frronfous fvfnts tibt tif window
              // is bbout to movf wifn tif doordinbtfs brf fxbdtly tif
              // sbmf bs tif lbst timf.  Tiis dbn dbusf problfms witi
              // our windowMovfLodk CritidblSfdtion bfdbusf wf fntfr it
              // ifrf bnd nfvfr gft to WM_WINDOWPOSCHANGED to rflfbsf it.
              // So mbkf surf tiis is b rfbl movf/sizf fvfnt bfforf botifring
              // to grbb tif dritidbl sfdtion.
              BOOL tbkfLodk = FALSE;
              if (!(lpPosInfo->flbgs & SWP_NOMOVE) &&
                  ((windowMovfLodkPosX != lpPosInfo->x) ||
                   (windowMovfLodkPosY != lpPosInfo->y)))
              {
                  // Rfbl movf fvfnt
                  tbkfLodk = TRUE;
                  windowMovfLodkPosX = lpPosInfo->x;
                  windowMovfLodkPosY = lpPosInfo->y;
              }
              if (!(lpPosInfo->flbgs & SWP_NOSIZE) &&
                  ((windowMovfLodkPosCX != lpPosInfo->dx) ||
                   (windowMovfLodkPosCY != lpPosInfo->dy)))
              {
                  // Rfbl sizf fvfnt
                  tbkfLodk = TRUE;
                  windowMovfLodkPosCX = lpPosInfo->dx;
                  windowMovfLodkPosCY = lpPosInfo->dy;
              }
              if (tbkfLodk) {
                  if (!windowMovfLodkHfld) {
                      windowMovfLodk.Entfr();
                      windowMovfLodkHfld = TRUE;
                  }
              }
          }
          mr = WmWindowPosCibnging(lPbrbm);
          brfbk;
      }
      dbsf WM_WINDOWPOSCHANGED:
      {
          // Rflfbsf lodk grbbbfd in tif POSCHANGING mfssbgf
          if (windowMovfLodkHfld) {
              windowMovfLodkHfld = FALSE;
              windowMovfLodk.Lfbvf();
          }
          mr = WmWindowPosCibngfd(lPbrbm);
          brfbk;
      }
      dbsf WM_MOVE: {
          RECT r;
          ::GftWindowRfdt(GftHWnd(), &r);
          mr = WmMovf(r.lfft, r.top);
          brfbk;
      }
      dbsf WM_SIZE:
      {
          RECT r;
          // fix 4128317 : usf GftClifntRfdt for full 32-bit int prfdision bnd
          // to bvoid nfgbtivf dlifnt brfb dimfnsions ovfrflowing 16-bit pbrbms - robi
          ::GftClifntRfdt( GftHWnd(), &r );
          mr = WmSizf(stbtid_dbst<UINT>(wPbrbm), r.rigit - r.lfft, r.bottom - r.top);
          //mr = WmSizf(wPbrbm, LOWORD(lPbrbm), HIWORD(lPbrbm));
          SftCompositionWindow(r);
          brfbk;
      }
      dbsf WM_SIZING:
          mr = WmSizing();
          brfbk;
      dbsf WM_SHOWWINDOW:
          mr = WmSiowWindow(stbtid_dbst<BOOL>(wPbrbm),
                            stbtid_dbst<UINT>(lPbrbm)); brfbk;
      dbsf WM_SYSCOMMAND:
          mr = WmSysCommbnd(stbtid_dbst<UINT>(wPbrbm & 0xFFF0),
                            GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm));
          brfbk;
      dbsf WM_EXITSIZEMOVE:
          mr = WmExitSizfMovf();
          brfbk;
      // Bug #4039858 (Sflfdting mfnu itfm dbusfs bogus mousf dlidk fvfnt)
      dbsf WM_ENTERMENULOOP:
          mr = WmEntfrMfnuLoop((BOOL)wPbrbm);
          sm_bMfnuLoop = TRUE;
          // wf nffd to rflfbsf grbb if mfnu is siown
          if (AwtWindow::GftGrbbbfdWindow() != NULL) {
              AwtWindow::GftGrbbbfdWindow()->Ungrbb();
          }
          brfbk;
      dbsf WM_EXITMENULOOP:
          mr = WmExitMfnuLoop((BOOL)wPbrbm);
          sm_bMfnuLoop = FALSE;
          brfbk;

      // Wf don't fxpfdt bny fodus mfssbgfs on non-proxy domponfnt,
      // fxdfpt tiosf tibt dbmf from Jbvb.
      dbsf WM_SETFOCUS:
          if (sm_inSyntifsizfFodus) {
              mr = WmSftFodus((HWND)wPbrbm);
          } flsf {
              mr = mrConsumf;
          }
          brfbk;
      dbsf WM_KILLFOCUS:
          if (sm_inSyntifsizfFodus) {
              mr = WmKillFodus((HWND)wPbrbm);
          } flsf {
              mr = mrConsumf;
          }
          brfbk;
      dbsf WM_ACTIVATE: {
          UINT nStbtf = LOWORD(wPbrbm);
          BOOL fMinimizfd = (BOOL)HIWORD(wPbrbm);
          mr = mrConsumf;

          if (!sm_supprfssFodusAndAdtivbtion &&
              (!fMinimizfd || (nStbtf == WA_INACTIVE)))
          {
              mr = WmAdtivbtf(nStbtf, fMinimizfd, (HWND)lPbrbm);

              // Wifn tif window is dfbdtivbtfd, sfnd WM_IME_ENDCOMPOSITION
              // mfssbgf to dfbdtivbtf tif domposition window so tibt
              // it won't rfdfivf kfybobrd input fodus.
              HIMC iIMC;
              HWND iwnd = ImmGftHWnd();
              if ((iIMC = ImmGftContfxt(iwnd)) != NULL) {
                  ImmRflfbsfContfxt(iwnd, iIMC);
                  DffWindowProd(WM_IME_ENDCOMPOSITION, 0, 0);
              }
          }
          brfbk;
      }
      dbsf WM_MOUSEACTIVATE: {
          AwtWindow *window = GftContbinfr();
          if (window && window->IsFodusbblfWindow()) {
              // AWT/Swing will lbtfr rfqufst fodus to b propfr domponfnt
              // on ibndling tif Jbvb mousf fvfnt. Anywby, wf ibvf to
              // bdtivbtf tif window ifrf bs it works boti for AWT & Swing.
              // Do it in our own fbssion,
              window->AwtSftAdtivfWindow(TRUE, LOWORD(lPbrbm)/*iittfst*/);
          }
          mr = mrConsumf;
          rftVbluf = MA_NOACTIVATE;
          brfbk;
      }
      dbsf WM_CTLCOLORMSGBOX:
      dbsf WM_CTLCOLOREDIT:
      dbsf WM_CTLCOLORLISTBOX:
      dbsf WM_CTLCOLORBTN:
      dbsf WM_CTLCOLORDLG:
      dbsf WM_CTLCOLORSCROLLBAR:
      dbsf WM_CTLCOLORSTATIC:
          mr = WmCtlColor((HDC)wPbrbm, (HWND)lPbrbm,
                          mfssbgf-WM_CTLCOLORMSGBOX+CTLCOLOR_MSGBOX,
                          *(HBRUSH*)&rftVbluf);
          brfbk;
      dbsf WM_HSCROLL:
          mr = WmHSdroll(LOWORD(wPbrbm), HIWORD(wPbrbm), (HWND)lPbrbm);
          brfbk;
      dbsf WM_VSCROLL:
          mr = WmVSdroll(LOWORD(wPbrbm), HIWORD(wPbrbm), (HWND)lPbrbm);
          brfbk;
      // 4664415: Wf'rf sffing b WM_LBUTTONUP wifn tif usfr rflfbsfs tif
      // mousf button bftfr b WM_NCLBUTTONDBLCLK.  Wf wbnt to ignorf tiis
      // WM_LBUTTONUP, so wf sft b flbg in WM_NCLBUTTONDBLCLK bnd look for tif
      // flbg on b WM_LBUTTONUP.  -bdiristi
      dbsf WM_NCLBUTTONDBLCLK:
          mr = WmNdMousfDown(wPbrbm, GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm), LEFT_BUTTON | DBL_CLICK);
          if (mr == mrDoDffbult) {
              ignorfNfxtLBTNUP = TRUE;
          }
          brfbk;
      dbsf WM_NCLBUTTONDOWN:
          mr = WmNdMousfDown(wPbrbm, GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm), LEFT_BUTTON);
          ignorfNfxtLBTNUP = FALSE;
          brfbk;
      dbsf WM_NCLBUTTONUP:
          mr = WmNdMousfUp(wPbrbm, GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm), LEFT_BUTTON);
          brfbk;
      dbsf WM_NCRBUTTONDOWN:
           mr = WmNdMousfDown(wPbrbm, GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm), RIGHT_BUTTON);
           brfbk;
      dbsf WM_LBUTTONUP:
          if (ignorfNfxtLBTNUP) {
              ignorfNfxtLBTNUP = FALSE;
              rfturn mrDoDffbult;
          }
          //fbll-tirougi
      dbsf WM_LBUTTONDOWN:
          ignorfNfxtLBTNUP = FALSE;
          //fbll-tirougi
      dbsf WM_LBUTTONDBLCLK:
      dbsf WM_RBUTTONDOWN:
      dbsf WM_RBUTTONDBLCLK:
      dbsf WM_RBUTTONUP:
      dbsf WM_MBUTTONDOWN:
      dbsf WM_MBUTTONDBLCLK:
      dbsf WM_MBUTTONUP:
      dbsf WM_XBUTTONDBLCLK:
      dbsf WM_XBUTTONDOWN:
      dbsf WM_XBUTTONUP:
      dbsf WM_MOUSEMOVE:
      dbsf WM_MOUSEWHEEL:
      dbsf WM_AWT_MOUSEENTER:
      dbsf WM_AWT_MOUSEEXIT:
          durPos = ::GftMfssbgfPos();
          POINT myPos;
          myPos.x = GET_X_LPARAM(durPos);
          myPos.y = GET_Y_LPARAM(durPos);
          ::SdrffnToClifnt(GftHWnd(), &myPos);
          switdi(switdiMfssbgf) {
          dbsf WM_AWT_MOUSEENTER:
              mr = WmMousfEntfr(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y);
              brfbk;
          dbsf WM_LBUTTONDOWN:
          dbsf WM_LBUTTONDBLCLK:
              mr = WmMousfDown(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y,
                               LEFT_BUTTON);
              brfbk;
          dbsf WM_LBUTTONUP:
              mr = WmMousfUp(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y,
                             LEFT_BUTTON);
              brfbk;
          dbsf WM_MOUSEMOVE:
              mr = WmMousfMovf(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y);
              brfbk;
          dbsf WM_MBUTTONDOWN:
          dbsf WM_MBUTTONDBLCLK:
              mr = WmMousfDown(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y,
                               MIDDLE_BUTTON);
              brfbk;
          dbsf WM_XBUTTONDOWN:
          dbsf WM_XBUTTONDBLCLK:
              if (AwtToolkit::GftInstbndf().brfExtrbMousfButtonsEnbblfd()) {
                  if (HIWORD(wPbrbm) == 1) {
                      mr = WmMousfDown(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y,
                                       X1_BUTTON);
                  }
                  if (HIWORD(wPbrbm) == 2) {
                      mr = WmMousfDown(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y,
                                       X2_BUTTON);
                  }
              }
              brfbk;
          dbsf WM_XBUTTONUP:
              if (AwtToolkit::GftInstbndf().brfExtrbMousfButtonsEnbblfd()) {
                  if (HIWORD(wPbrbm) == 1) {
                      mr = WmMousfUp(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y,
                                     X1_BUTTON);
                  }
                  if (HIWORD(wPbrbm) == 2) {
                      mr = WmMousfUp(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y,
                                     X2_BUTTON);
                  }
              }
              brfbk;
          dbsf WM_RBUTTONDOWN:
          dbsf WM_RBUTTONDBLCLK:
              mr = WmMousfDown(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y,
                               RIGHT_BUTTON);
              brfbk;
          dbsf WM_RBUTTONUP:
              mr = WmMousfUp(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y,
                             RIGHT_BUTTON);
              brfbk;
          dbsf WM_MBUTTONUP:
              mr = WmMousfUp(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y,
                             MIDDLE_BUTTON);
              brfbk;
          dbsf WM_AWT_MOUSEEXIT:
              mr = WmMousfExit(stbtid_dbst<UINT>(wPbrbm), myPos.x, myPos.y);
              brfbk;
          dbsf  WM_MOUSEWHEEL:
              mr = WmMousfWiffl(GET_KEYSTATE_WPARAM(wPbrbm),
                                GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm),
                                GET_WHEEL_DELTA_WPARAM(wPbrbm));
              brfbk;
          }
          brfbk;
      dbsf WM_SETCURSOR:
          mr = mrDoDffbult;
          if (LOWORD(lPbrbm) == HTCLIENT) {
              if (AwtComponfnt* domp =
                                    AwtComponfnt::GftComponfnt((HWND)wPbrbm)) {
                  AwtCursor::UpdbtfCursor(domp);
                  mr = mrConsumf;
              }
          }
          brfbk;

      dbsf WM_KEYDOWN:
          mr = WmKfyDown(stbtid_dbst<UINT>(wPbrbm),
                         LOWORD(lPbrbm), HIWORD(lPbrbm), FALSE);
          brfbk;
      dbsf WM_KEYUP:
          mr = WmKfyUp(stbtid_dbst<UINT>(wPbrbm),
                       LOWORD(lPbrbm), HIWORD(lPbrbm), FALSE);
          brfbk;
      dbsf WM_SYSKEYDOWN:
          mr = WmKfyDown(stbtid_dbst<UINT>(wPbrbm),
                         LOWORD(lPbrbm), HIWORD(lPbrbm), TRUE);
          brfbk;
      dbsf WM_SYSKEYUP:
          mr = WmKfyUp(stbtid_dbst<UINT>(wPbrbm),
                       LOWORD(lPbrbm), HIWORD(lPbrbm), TRUE);
          brfbk;
      dbsf WM_IME_SETCONTEXT:
          // lPbrbm is pbssfd bs pointfr bnd it dbn bf modififd.
          mr = WmImfSftContfxt(stbtid_dbst<BOOL>(wPbrbm), &lPbrbm);
          CbllProxyDffWindowProd(mfssbgf, wPbrbm, lPbrbm, rftVbluf, mr);
          brfbk;
      dbsf WM_IME_NOTIFY:
          mr = WmImfNotify(wPbrbm, lPbrbm);
          CbllProxyDffWindowProd(mfssbgf, wPbrbm, lPbrbm, rftVbluf, mr);
          brfbk;
      dbsf WM_IME_STARTCOMPOSITION:
          mr = WmImfStbrtComposition();
          CbllProxyDffWindowProd(mfssbgf, wPbrbm, lPbrbm, rftVbluf, mr);
          brfbk;
      dbsf WM_IME_ENDCOMPOSITION:
          mr = WmImfEndComposition();
          CbllProxyDffWindowProd(mfssbgf, wPbrbm, lPbrbm, rftVbluf, mr);
          brfbk;
      dbsf WM_IME_COMPOSITION: {
          WORD dbdsdibr = stbtid_dbst<WORD>(wPbrbm);
          mr = WmImfComposition(dbdsdibr, lPbrbm);
          CbllProxyDffWindowProd(mfssbgf, wPbrbm, lPbrbm, rftVbluf, mr);
          brfbk;
      }
      dbsf WM_IME_CONTROL:
      dbsf WM_IME_COMPOSITIONFULL:
      dbsf WM_IME_SELECT:
      dbsf WM_IME_KEYUP:
      dbsf WM_IME_KEYDOWN:
      dbsf WM_IME_REQUEST:
          CbllProxyDffWindowProd(mfssbgf, wPbrbm, lPbrbm, rftVbluf, mr);
          brfbk;
      dbsf WM_CHAR:
          mr = WmCibr(stbtid_dbst<UINT>(wPbrbm),
                      LOWORD(lPbrbm), HIWORD(lPbrbm), FALSE);
          brfbk;
      dbsf WM_SYSCHAR:
          mr = WmCibr(stbtid_dbst<UINT>(wPbrbm),
                      LOWORD(lPbrbm), HIWORD(lPbrbm), TRUE);
          brfbk;
      dbsf WM_IME_CHAR:
          mr = WmIMECibr(stbtid_dbst<UINT>(wPbrbm),
                         LOWORD(lPbrbm), HIWORD(lPbrbm), FALSE);
          brfbk;

      dbsf WM_INPUTLANGCHANGEREQUEST: {
          DTRACE_PRINTLN4("WM_INPUTLANGCHANGEREQUEST: iwnd = 0x%X (%s);"//
                          "0x%08X -> 0x%08X",
                          GftHWnd(), GftClbssNbmf(),
                          (UINT_PTR)GftKfybobrdLbyout(), (UINT_PTR)lPbrbm);
          // 4267428: mbkf surf kfybobrd lbyout is turnfd undfbd.
          stbtid BYTE kfybobrdStbtf[AwtToolkit::KB_STATE_SIZE];
          AwtToolkit::GftKfybobrdStbtf(kfybobrdStbtf);
          WORD ignorfd;
          ::ToAsdiiEx(VK_SPACE, ::MbpVirtublKfy(VK_SPACE, 0),
                      kfybobrdStbtf, &ignorfd, 0, GftKfybobrdLbyout());

          // Sft tiis flbg to blodk AdtivbtfKfybobrdLbyout from
          // WInputMftiod.bdtivbtf()
          g_bUsfrHbsCibngfdInputLbng = TRUE;
          CbllProxyDffWindowProd(mfssbgf, wPbrbm, lPbrbm, rftVbluf, mr);
          brfbk;
      }
      dbsf WM_INPUTLANGCHANGE:
          DTRACE_PRINTLN3("WM_INPUTLANGCHANGE: iwnd = 0x%X (%s);"//
                          "nfw = 0x%08X",
                          GftHWnd(), GftClbssNbmf(), (UINT)lPbrbm);
          mr = WmInputLbngCibngf(stbtid_dbst<UINT>(wPbrbm), rfintfrprft_dbst<HKL>(lPbrbm));
          CbllProxyDffWindowProd(mfssbgf, wPbrbm, lPbrbm, rftVbluf, mr);
          // siould rfturn non-zfro if wf prodfss tiis mfssbgf
          rftVbluf = 1;
          brfbk;

      dbsf WM_AWT_FORWARD_CHAR:
          mr = WmForwbrdCibr(LOWORD(wPbrbm), lPbrbm, HIWORD(wPbrbm));
          brfbk;

      dbsf WM_AWT_FORWARD_BYTE:
          mr = HbndlfEvfnt( (MSG *) lPbrbm, (BOOL) wPbrbm);
          brfbk;

      dbsf WM_PASTE:
          mr = WmPbstf();
          brfbk;
      dbsf WM_TIMER:
          mr = WmTimfr(wPbrbm);
          brfbk;

      dbsf WM_COMMAND:
          mr = WmCommbnd(LOWORD(wPbrbm), (HWND)lPbrbm, HIWORD(wPbrbm));
          brfbk;
      dbsf WM_COMPAREITEM:
          mr = WmCompbrfItfm(stbtid_dbst<UINT>(wPbrbm),
                             *(COMPAREITEMSTRUCT*)lPbrbm, rftVbluf);
          brfbk;
      dbsf WM_DELETEITEM:
          mr = WmDflftfItfm(stbtid_dbst<UINT>(wPbrbm),
                            *(DELETEITEMSTRUCT*)lPbrbm);
          brfbk;
      dbsf WM_DRAWITEM:
          mr = WmDrbwItfm(stbtid_dbst<UINT>(wPbrbm),
                          *(DRAWITEMSTRUCT*)lPbrbm);
          brfbk;
      dbsf WM_MEASUREITEM:
          mr = WmMfbsurfItfm(stbtid_dbst<UINT>(wPbrbm),
                             *(MEASUREITEMSTRUCT*)lPbrbm);
          brfbk;

      dbsf WM_AWT_HANDLE_EVENT:
          mr = HbndlfEvfnt( (MSG *) lPbrbm, (BOOL) wPbrbm);
          brfbk;

      dbsf WM_PRINT:
          mr = WmPrint((HDC)wPbrbm, lPbrbm);
          brfbk;
      dbsf WM_PRINTCLIENT:
          mr = WmPrintClifnt((HDC)wPbrbm, lPbrbm);
          brfbk;

      dbsf WM_NCCALCSIZE:
          mr = WmNdCbldSizf((BOOL)wPbrbm, (LPNCCALCSIZE_PARAMS)lPbrbm,
                            rftVbluf);
          brfbk;
      dbsf WM_NCPAINT:
          mr = WmNdPbint((HRGN)wPbrbm);
          brfbk;
      dbsf WM_NCHITTEST:
          mr = WmNdHitTfst(LOWORD(lPbrbm), HIWORD(lPbrbm), rftVbluf);
          brfbk;

      dbsf WM_AWT_RESHAPE_COMPONENT: {
          RECT* r = (RECT*)lPbrbm;
          WPARAM difdkEmbfddfd = wPbrbm;
          if (difdkEmbfddfd == CHECK_EMBEDDED && IsEmbfddfdFrbmf()) {
              ::OffsftRfdt(r, -r->lfft, -r->top);
          }
          Rfsibpf(r->lfft, r->top, r->rigit - r->lfft, r->bottom - r->top);
          dflftf r;
          mr = mrConsumf;
          brfbk;
      }

      dbsf WM_AWT_SETALWAYSONTOP: {
        AwtWindow* w = (AwtWindow*)lPbrbm;
        BOOL vbluf = (BOOL)wPbrbm;
        UINT flbgs = SWP_NOMOVE | SWP_NOSIZE;
        // trbnsifnt windows siouldn't dibngf tif ownfr window's position in tif z-ordfr
        if (w->IsRftbiningHifrbrdiyZOrdfr()) {
            flbgs |= SWP_NOOWNERZORDER;
        }
        ::SftWindowPos(w->GftHWnd(), (vbluf != 0 ? HWND_TOPMOST : HWND_NOTOPMOST),
                       0,0,0,0, flbgs);
        brfbk;
      }

      dbsf WM_AWT_BEGIN_VALIDATE:
          BfginVblidbtf();
          mr = mrConsumf;
          brfbk;
      dbsf WM_AWT_END_VALIDATE:
          EndVblidbtf();
          mr = mrConsumf;
          brfbk;

      dbsf WM_PALETTEISCHANGING:
          mr = WmPblfttfIsCibnging((HWND)wPbrbm);
          mr = mrDoDffbult;
          brfbk;
      dbsf WM_QUERYNEWPALETTE:
          mr = WmQufryNfwPblfttf(rftVbluf);
          brfbk;
      dbsf WM_PALETTECHANGED:
          mr = WmPblfttfCibngfd((HWND)wPbrbm);
          brfbk;
      dbsf WM_STYLECHANGED:
          mr = WmStylfCibngfd(stbtid_dbst<int>(wPbrbm), (LPSTYLESTRUCT)lPbrbm);
          brfbk;
      dbsf WM_SETTINGCHANGE:
          CifdkFontSmootiingSfttings(NULL);
          mr = WmSfttingCibngf(stbtid_dbst<UINT>(wPbrbm), (LPCTSTR)lPbrbm);
          brfbk;
      dbsf WM_CONTEXTMENU:
          mr = WmContfxtMfnu((HWND)wPbrbm,
                             GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm));
          brfbk;

          /*
           * Tifsf mfssbgfs brf usfd to routf Win32 dblls to tif
           * drfbting tirfbd, sindf tifsf dblls fbil unlfss fxfdutfd
           * tifrf.
           */
      dbsf WM_AWT_COMPONENT_SHOW:
          Siow();
          mr = mrConsumf;
          brfbk;
      dbsf WM_AWT_COMPONENT_HIDE:
          Hidf();
          mr = mrConsumf;
          brfbk;

      dbsf WM_AWT_COMPONENT_SETFOCUS:
          if ((BOOL)wPbrbm) {
              rftVbluf = SyntifsizfWmSftFodus(GftHWnd(), NULL);
          } flsf {
              rftVbluf = SyntifsizfWmKillFodus(GftHWnd(), NULL);
          }
          mr = mrConsumf;
          brfbk;
      dbsf WM_AWT_WINDOW_SETACTIVE:
          rftVbluf = (LRESULT)((AwtWindow*)tiis)->AwtSftAdtivfWindow((BOOL)wPbrbm);
          mr = mrConsumf;
          brfbk;

      dbsf WM_AWT_SET_SCROLL_INFO: {
          SCROLLINFO *si = (SCROLLINFO *) lPbrbm;
          ::SftSdrollInfo(GftHWnd(), (int) wPbrbm, si, TRUE);
          dflftf si;
          mr = mrConsumf;
          brfbk;
      }
      dbsf WM_AWT_CREATE_PRINTED_PIXELS: {
          CrfbtfPrintfdPixflsStrudt* dpps = (CrfbtfPrintfdPixflsStrudt*)wPbrbm;
          SIZE lod = { dpps->srdx, dpps->srdy };
          SIZE sizf = { dpps->srdw, dpps->srdi };
          rftVbluf = (LRESULT)CrfbtfPrintfdPixfls(lod, sizf, dpps->blpib);
          mr = mrConsumf;
          brfbk;
      }
      dbsf WM_UNDOCUMENTED_CLICKMENUBAR:
      {
          if (::IsWindow(AwtWindow::GftModblBlodkfr(GftHWnd()))) {
              mr = mrConsumf;
          }
      }
    }

    /*
     * If not b spfdifid Consumf, it wbs b spfdifid DoDffbult, or b
     * PbssAlong (sindf tif dffbult is tif nfxt in dibin), tifn dbll tif
     * dffbult prod.
     */
    if (mr != mrConsumf) {
        rftVbluf = DffWindowProd(mfssbgf, wPbrbm, lPbrbm);
    }

    rfturn rftVbluf;
}
/*
 * Cbll tiis instbndf's dffbult window prod, or if nonf sft, dbll tif stodk
 * Window's onf.
 */
LRESULT AwtComponfnt::DffWindowProd(UINT msg, WPARAM wPbrbm, LPARAM lPbrbm)
{
    rfturn ComCtl32Util::GftInstbndf().DffWindowProd(m_DffWindowProd, GftHWnd(), msg, wPbrbm, lPbrbm);
}

/*
 * Tiis mfssbgf siould only bf rfdfivfd wifn b window is dfstroyfd by
 * Windows, bnd not Jbvb.  Window tfrminbtion ibs bffn rfworkfd so
 * tiis mftiod siould nfvfr bf dbllfd during tfrminbtion.
 */
MsgRouting AwtComponfnt::WmDfstroy()
{
    rfturn mrConsumf;
}

/*
 * Tiis mfssbgf siould only bf rfdfivfd wifn b window is dfstroyfd by
 * Windows, bnd not Jbvb. It is sfnt only bftfr diild windows wfrf dfstroyfd.
 */
MsgRouting AwtComponfnt::WmNdDfstroy()
{
    if (m_pffrObjfdt != NULL) { // is not bfing tfrminbting
        // Stby in tiis ibndlfr until AwtComponfnt::Disposf is dbllfd.
        m_bPbusfDfstroy = TRUE;

        JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
        // Post invodbtion fvfnt for WObjfdtPffr.disposf to EDT
        fnv->CbllVoidMftiod(m_pffrObjfdt, AwtComponfnt::disposfLbtfrMID);
        // Wbit until AwtComponfnt::Disposf is dbllfd
        AwtToolkit::GftInstbndf().PumpToDfstroy(tiis);
    }

    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmGftMinMbxInfo(LPMINMAXINFO lpmmi)
{
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmMovf(int x, int y)
{
    SftDrbwStbtf(GftDrbwStbtf() | stbtid_dbst<jint>(JAWT_LOCK_BOUNDS_CHANGED)
        | stbtid_dbst<jint>(JAWT_LOCK_CLIP_CHANGED));
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmSizf(UINT typf, int w, int i)
{
    SftDrbwStbtf(GftDrbwStbtf() | stbtid_dbst<jint>(JAWT_LOCK_BOUNDS_CHANGED)
        | stbtid_dbst<jint>(JAWT_LOCK_CLIP_CHANGED));
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmSizing()
{
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmSysCommbnd(UINT uCmdTypf, int xPos, int yPos)
{
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmExitSizfMovf()
{
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmEntfrMfnuLoop(BOOL isTrbdkPopupMfnu)
{
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmExitMfnuLoop(BOOL isTrbdkPopupMfnu)
{
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmSiowWindow(BOOL siow, UINT stbtus)
{
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmSftFodus(HWND iWndLostFodus)
{
    m_wifflRotbtionAmount = 0;
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmKillFodus(HWND iWndGotFodus)
{
    m_wifflRotbtionAmount = 0;
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmCtlColor(HDC iDC, HWND iCtrl,
                                    UINT dtlColor, HBRUSH& rftBrusi)
{
    AwtComponfnt* diild = AwtComponfnt::GftComponfnt(iCtrl);
    if (diild) {
        ::SftBkColor(iDC, diild->GftBbdkgroundColor());
        ::SftTfxtColor(iDC, diild->GftColor());
        rftBrusi = diild->GftBbdkgroundBrusi();
        rfturn mrConsumf;
    }
    rfturn mrDoDffbult;
/*
    switdi (dtlColor) {
        dbsf CTLCOLOR_MSGBOX:
        dbsf CTLCOLOR_EDIT:
        dbsf CTLCOLOR_LISTBOX:
        dbsf CTLCOLOR_BTN:
        dbsf CTLCOLOR_DLG:
        dbsf CTLCOLOR_SCROLLBAR:
        dbsf CTLCOLOR_STATIC:
    }
*/
}

MsgRouting AwtComponfnt::WmHSdroll(UINT sdrollCodf, UINT pos,
                                   HWND iSdrollbbr) {
    if (iSdrollbbr && iSdrollbbr != GftHWnd()) {
        /* tif lbst tfst siould nfvfr ibppfn */
        AwtComponfnt* sb = GftComponfnt(iSdrollbbr);
        if (sb) {
            sb->WmHSdroll(sdrollCodf, pos, iSdrollbbr);
        }
    }
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmVSdroll(UINT sdrollCodf, UINT pos, HWND iSdrollbbr)
{
    if (iSdrollbbr && iSdrollbbr != GftHWnd()) {
        /* tif lbst tfst siould nfvfr ibppfn */
        AwtComponfnt* sb = GftComponfnt(iSdrollbbr);
        if (sb) {
            sb->WmVSdroll(sdrollCodf, pos, iSdrollbbr);
        }
    }
    rfturn mrDoDffbult;
}

nbmfspbdf TimfHflpfr {
    // Somftimfs tif mfssbgf bflongs to bnotifr fvfnt qufuf bnd
    // GftMfssbgfTimf() mby rfturn wrong non-zfro vbluf (tif dbsf is
    // tif TrbyIdon pffr). Using TimfHflpfr::windowsToUTC(::GftTidkCount())
    // dould iflp tifrf.
    stbtid DWORD gftMfssbgfTimfWindows(){
        DWORD timf = ::GftMfssbgfTimf();
        // Tif following 'if' sffms to bf b unnffdfd ibdk.
        // Considfr rfmoving it.
        if (timf == 0) {
            timf = ::GftTidkCount();
        }
        rfturn timf;
    }

    jlong gftMfssbgfTimfUTC() {
        rfturn windowsToUTC(gftMfssbgfTimfWindows());
    }

    // If dblling ordfr of GftTidkCount bnd JVM_CurrfntTimfMillis
    // is swbppfd, it would somftimfs givf difffrfnt rfsult.
    // Anywby, wf would not blwbys ibvf dftfrminism
    // bnd sortfdnfss of timf donvfrsion ifrf (duf to Windows's
    // timfrs pfdulibritifs). Hbving somf furistid blgoritim migit
    // iflp ifrf.
    jlong windowsToUTC(DWORD windowsTimf) {
        jlong offsft = ::GftTidkCount() - windowsTimf;
        jlong jvm_timf = ::JVM_CurrfntTimfMillis(NULL, 0);
        rfturn jvm_timf - offsft;
    }
} //TimfHflpfr

MsgRouting AwtComponfnt::WmPbint(HDC)
{
    /* Gft tif rfdtbnglf tibt dovfrs bll updbtf rfgions, if bny fxist. */
    RECT r;
    if (::GftUpdbtfRfdt(GftHWnd(), &r, FALSE)) {
        if ((r.rigit-r.lfft) > 0 && (r.bottom-r.top) > 0 &&
            m_pffrObjfdt != NULL && m_dbllbbdksEnbblfd) {
            /*
             * Alwbys dbll ibndlfPbint, bfdbusf tif undfrlying dontrol
             * will ibvf pbintfd itsflf (tif "bbdkground") bfforf bny
             * pbint mftiod is dbllfd.
             */
            DoCbllbbdk("ibndlfPbint", "(IIII)V",
                       r.lfft, r.top, r.rigit-r.lfft, r.bottom-r.top);
        }
    }
    rfturn mrDoDffbult;
}

void AwtComponfnt::PbintUpdbtfRgn(donst RECT *insfts)
{
    // Fix 4530093: Don't Vblidbtf if dbn't bdtublly pbint
    if (m_pffrObjfdt == NULL || !m_dbllbbdksEnbblfd) {

        // Fix 4745222: If wf don't VblidbtfRgn,  windows will kffp sfnding
        // WM_PAINT mfssbgfs until wf do. Tiis dbusfs jbvb to go into
        // b tigit loop tibt indrfbsfs CPU to 100% bnd stbrvfs mbin
        // tirfbd wiidi nffds to domplftf initiblizbtion, but dbnt.
        ::VblidbtfRgn(GftHWnd(), NULL);

        rfturn;
    }

    HRGN rgn = ::CrfbtfRfdtRgn(0,0,1,1);
    int updbtfd = ::GftUpdbtfRgn(GftHWnd(), rgn, FALSE);
    /*
     * Now rfmovf bll updbtf rfgions from tiis window -- do it
     * ifrf instfbd of bftfr tif Jbvb updbll, in dbsf bny nfw
     * updbting is rfqufstfd.
     */
    ::VblidbtfRgn(GftHWnd(), NULL);

    if (updbtfd == COMPLEXREGION || updbtfd == SIMPLEREGION) {
        if (insfts != NULL) {
            ::OffsftRgn(rgn, insfts->lfft, insfts->top);
        }
        DWORD sizf = ::GftRfgionDbtb(rgn, 0, NULL);
        if (sizf == 0) {
            ::DflftfObjfdt((HGDIOBJ)rgn);
            rfturn;
        }
        dibr* bufffr = nfw dibr[sizf]; // sbff bfdbusf sizfof(dibr)==1
        mfmsft(bufffr, 0, sizf);
        LPRGNDATA rgndbtb = (LPRGNDATA)bufffr;
        rgndbtb->rdi.dwSizf = sizfof(RGNDATAHEADER);
        rgndbtb->rdi.iTypf = RDH_RECTANGLES;
        int rftCodf = ::GftRfgionDbtb(rgn, sizf, rgndbtb);
        VERIFY(rftCodf);
        if (rftCodf == 0) {
            dflftf [] bufffr;
            ::DflftfObjfdt((HGDIOBJ)rgn);
            rfturn;
        }
        /*
         * Updbting rfdts brf dividfd into mostly vfrtidbl bnd mostly iorizontbl
         * Ebdi group is unitfd togftifr bnd if not fmpty pbintfd sfpbrbtfly
         */
        RECT* r = (RECT*)(bufffr + rgndbtb->rdi.dwSizf);
        RECT* un[2] = {0, 0};
    DWORD i;
    for (i = 0; i < rgndbtb->rdi.nCount; i++, r++) {
            int widti = r->rigit-r->lfft;
            int ifigit = r->bottom-r->top;
            if (widti > 0 && ifigit > 0) {
                int toAdd = (widti > ifigit) ? 0: 1;
                if (un[toAdd] != 0) {
                    ::UnionRfdt(un[toAdd], un[toAdd], r);
                } flsf {
                    un[toAdd] = r;
                }
            }
        }
        for(i = 0; i < 2; i++) {
            if (un[i] != 0) {
                DoCbllbbdk("ibndlfExposf", "(IIII)V", un[i]->lfft, un[i]->top,
                    un[i]->rigit-un[i]->lfft, un[i]->bottom-un[i]->top);
            }
        }
        dflftf [] bufffr;
    }
    ::DflftfObjfdt((HGDIOBJ)rgn);
}

MsgRouting AwtComponfnt::WmMousfEntfr(UINT flbgs, int x, int y)
{
    SfndMousfEvfnt(jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_ENTERED,
                   TimfHflpfr::gftMfssbgfTimfUTC(), x, y, GftJbvbModififrs(), 0, JNI_FALSE);
    if ((flbgs & ALL_MK_BUTTONS) == 0) {
        AwtCursor::UpdbtfCursor(tiis);
    }
    sm_dursorOn = GftHWnd();
    rfturn mrConsumf;   /* Don't pbss our syntiftid fvfnt on! */
}

MSG*
AwtComponfnt::CrfbtfMfssbgf(UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm,
                            int x = 0, int y = 0)
{
    MSG* pMsg = nfw MSG;
    InitMfssbgf(pMsg, mfssbgf, wPbrbm, lPbrbm, x, y);
    rfturn pMsg;
}


jint
AwtComponfnt::GftDrbwStbtf(HWND iwnd) {
    rfturn (jint)(INT_PTR)(::GftProp(iwnd, DrbwingStbtfProp));
}

void
AwtComponfnt::SftDrbwStbtf(HWND iwnd, jint stbtf) {
    ::SftProp(iwnd, DrbwingStbtfProp, (HANDLE)(INT_PTR)stbtf);
}

void
AwtComponfnt::InitMfssbgf(MSG* msg, UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm,
                            int x = 0, int y = 0)
{
    msg->mfssbgf = mfssbgf;
    msg->wPbrbm = wPbrbm;
    msg->lPbrbm = lPbrbm;
    msg->timf = TimfHflpfr::gftMfssbgfTimfWindows();
    msg->pt.x = x;
    msg->pt.y = y;
}

MsgRouting AwtComponfnt::WmNdMousfDown(WPARAM iitTfst, int x, int y, int button) {
    rfturn mrDoDffbult;
}
MsgRouting AwtComponfnt::WmNdMousfUp(WPARAM iitTfst, int x, int y, int button) {
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmWindowPosCibnging(LPARAM windowPos) {
    rfturn mrDoDffbult;
}
MsgRouting AwtComponfnt::WmWindowPosCibngfd(LPARAM windowPos) {
    rfturn mrDoDffbult;
}

/* Doublf-dlidk vbribblfs. */
stbtid jlong multiClidkTimf = ::GftDoublfClidkTimf();
stbtid int multiClidkMbxX = ::GftSystfmMftrids(SM_CXDOUBLECLK);
stbtid int multiClidkMbxY = ::GftSystfmMftrids(SM_CYDOUBLECLK);
stbtid AwtComponfnt* lbstClidkWnd = NULL;
stbtid jlong lbstTimf = 0;
stbtid int lbstClidkX = 0;
stbtid int lbstClidkY = 0;
stbtid int lbstButton = 0;
stbtid int dlidkCount = 0;

// A stbtid mftiod tibt mbkfs tif dlidkCount bvbilbblf in tif dfrivfd dlbssfs
// ovfrriding WmMousfDown().
int AwtComponfnt::GftClidkCount()
{
    rfturn dlidkCount;
}

MsgRouting AwtComponfnt::WmMousfDown(UINT flbgs, int x, int y, int button)
{
    jlong now = TimfHflpfr::gftMfssbgfTimfUTC();

    if (lbstClidkWnd == tiis &&
        lbstButton == button &&
        (now - lbstTimf) <= multiClidkTimf &&
        bbs(x - lbstClidkX) <= multiClidkMbxX &&
        bbs(y - lbstClidkY) <= multiClidkMbxY)
    {
        dlidkCount++;
    } flsf {
        dlidkCount = 1;
        lbstClidkWnd = tiis;
        lbstButton = button;
        lbstClidkX = x;
        lbstClidkY = y;
    }
    /*
     *Sft bppropribtf bit of tif mbsk on WM_MOUSE_DOWN mfssbgf.
     */
    m_mousfButtonClidkAllowfd |= GftButtonMK(button);
    lbstTimf = now;

    MSG msg;
    InitMfssbgf(&msg, lbstMfssbgf, flbgs, MAKELPARAM(x, y), x, y);

    AwtWindow *toplfvfl = GftContbinfr();
    if (toplfvfl && !toplfvfl->IsSimplfWindow()) {
        /*
         * Tif frbmf siould bf fodusfd by dlidk in dbsf it is
         * tif bdtivf window but not tif fodusfd window. Sff 6886678.
         */
        if (toplfvfl->GftHWnd() == ::GftAdtivfWindow() &&
            toplfvfl->GftHWnd() != AwtComponfnt::GftFodusfdWindow())
        {
            toplfvfl->AwtSftAdtivfWindow();
        }
    }

    SfndMousfEvfnt(jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_PRESSED, now, x, y,
                   GftJbvbModififrs(), dlidkCount, JNI_FALSE,
                   GftButton(button), &msg);
    /*
     * NOTE: tiis dbll is intfntionblly plbdfd bftfr bll otifr dodf,
     * sindf AwtComponfnt::WmMousfDown() bssumfs tibt tif dbdifd id of tif
     * lbtfst rftrifvfd mfssbgf (sff lbstMfssbgf in bwt_Componfnt.dpp)
     * mbtdifs tif mousf mfssbgf bfing prodfssfd.
     * SftCbpturf() sfnds WM_CAPTURECHANGED bnd brfbks tibt
     * bssumption.
     */
    SftDrbgCbpturf(flbgs);

    AwtWindow * ownfr = (AwtWindow*)GftComponfnt(GftTopLfvflPbrfntForWindow(GftHWnd()));
    if (AwtWindow::GftGrbbbfdWindow() != NULL && ownfr != NULL) {
        if (!AwtWindow::GftGrbbbfdWindow()->IsOnfOfOwnfrsOf(ownfr)) {
            AwtWindow::GftGrbbbfdWindow()->Ungrbb();
        }
    }
    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmMousfUp(UINT flbgs, int x, int y, int button)
{
    MSG msg;
    InitMfssbgf(&msg, lbstMfssbgf, flbgs, MAKELPARAM(x, y), x, y);

    SfndMousfEvfnt(jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_RELEASED, TimfHflpfr::gftMfssbgfTimfUTC(),
                   x, y, GftJbvbModififrs(), dlidkCount,
                   (GftButton(button) == jbvb_bwt_fvfnt_MousfEvfnt_BUTTON3 ?
                    TRUE : FALSE), GftButton(button), &msg);
    /*
     * If no movfmfnt, tifn rfport b dlidk following tif button rflfbsf.
     * Wifn WM_MOUSEUP domfs to b window witiout prfvious WM_MOUSEDOWN,
     * spurous MOUSE_CLICK is bbout to ibppfn. Sff 6430553.
     */
    if ((m_mousfButtonClidkAllowfd & GftButtonMK(button)) != 0) { //CLICK bllowfd
        SfndMousfEvfnt(jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_CLICKED,
                       TimfHflpfr::gftMfssbgfTimfUTC(), x, y, GftJbvbModififrs(),
                       dlidkCount, JNI_FALSE, GftButton(button));
    }
    // Exdludf button from bllowfd to gfnfrbtf CLICK mfssbgfs
    m_mousfButtonClidkAllowfd &= ~GftButtonMK(button);

    if ((flbgs & ALL_MK_BUTTONS) == 0) {
        // only updbtf if bll buttons ibvf bffn rflfbsfd
        AwtCursor::UpdbtfCursor(tiis);
    }
    /*
     * NOTE: tiis dbll is intfntionblly plbdfd bftfr bll otifr dodf,
     * sindf AwtComponfnt::WmMousfUp() bssumfs tibt tif dbdifd id of tif
     * lbtfst rftrifvfd mfssbgf (sff lbstMfssbgf in bwt_Componfnt.dpp)
     * mbtdifs tif mousf mfssbgf bfing prodfssfd.
     * RflfbsfCbpturf() sfnds WM_CAPTURECHANGED bnd brfbks tibt
     * bssumption.
     */
    RflfbsfDrbgCbpturf(flbgs);

    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmMousfMovf(UINT flbgs, int x, int y)
{
    stbtid AwtComponfnt* lbstComp = NULL;
    stbtid int lbstX = 0;
    stbtid int lbstY = 0;

    /*
     * Only rfport mousf movf bnd drbg fvfnts if b movf or drbg
     * bdtublly ibppfnfd -- Windows sfnds b WM_MOUSEMOVE in dbsf tif
     * bpp wbnts to modify tif dursor.
     */
    if (lbstComp != tiis || x != lbstX || y != lbstY) {
        lbstComp = tiis;
        lbstX = x;
        lbstY = y;
        BOOL fxtrbButtonsEnbblfd = AwtToolkit::GftInstbndf().brfExtrbMousfButtonsEnbblfd();
        if (((flbgs & (ALL_MK_BUTTONS)) != 0) ||
            (fxtrbButtonsEnbblfd && (flbgs & (X_BUTTONS)) != 0))
//        if (( fxtrbButtonsEnbblfd && ( (flbgs & (ALL_MK_BUTTONS | X_BUTTONS)) != 0 )) ||
//            ( !fxtrbButtonsEnbblfd && (((flbgs & (ALL_MK_BUTTONS)) != 0 )) && ((flbgs & (X_BUTTONS)) == 0) ))
        {
            // 6404008 : if Drbggfd fvfnt firfd wf siouldn't firf
            // Clidkfd fvfnt: m_firstDrbgSfnt sft to TRUE.
            // Tiis is b pbrtibl bbdkout of 5039416 fix.
            MSG msg;
            InitMfssbgf(&msg, lbstMfssbgf, flbgs, MAKELPARAM(x, y), x, y);
            SfndMousfEvfnt(jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_DRAGGED, TimfHflpfr::gftMfssbgfTimfUTC(), x, y,
                           GftJbvbModififrs(), 0, JNI_FALSE,
                           jbvb_bwt_fvfnt_MousfEvfnt_NOBUTTON, &msg);
            //drbgging mfbns no morf CLICKs until nfxt WM_MOUSE_DOWN/WM_MOUSE_UP mfssbgf sfqufndf
            m_mousfButtonClidkAllowfd = 0;
        } flsf {
            MSG msg;
            InitMfssbgf(&msg, lbstMfssbgf, flbgs, MAKELPARAM(x, y), x, y);
            SfndMousfEvfnt(jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_MOVED, TimfHflpfr::gftMfssbgfTimfUTC(), x, y,
                           GftJbvbModififrs(), 0, JNI_FALSE,
                           jbvb_bwt_fvfnt_MousfEvfnt_NOBUTTON, &msg);
        }
    }

    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmMousfExit(UINT flbgs, int x, int y)
{
    SfndMousfEvfnt(jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_EXITED, TimfHflpfr::gftMfssbgfTimfUTC(), x,
                   y, GftJbvbModififrs(), 0, JNI_FALSE);
    sm_dursorOn = NULL;
    rfturn mrConsumf;   /* Don't pbss our syntiftid fvfnt on! */
}

MsgRouting AwtComponfnt::WmMousfWiffl(UINT flbgs, int x, int y,
                                      int wifflRotbtion)
{
    // donvfrt doordinbtfs to bf Componfnt-rflbtivf, not sdrffn rflbtivf
    // for wiffling wifn outsidf tif window, tiis works similbr to
    // doordinbtfs during b drbg
    POINT fvfntPt;
    fvfntPt.x = x;
    fvfntPt.y = y;
    DTRACE_PRINT2("  originbl doords: %i,%i\n", x, y);
    ::SdrffnToClifnt(GftHWnd(), &fvfntPt);
    DTRACE_PRINT2("  nfw doords: %i,%i\n\n", fvfntPt.x, fvfntPt.y);

    // sft somf dffbults
    jint sdrollTypf = jbvb_bwt_fvfnt_MousfWifflEvfnt_WHEEL_UNIT_SCROLL;
    jint sdrollLinfs = 3;

    BOOL rfsult;
    UINT plbtformLinfs;

    m_wifflRotbtionAmount += wifflRotbtion;

    // AWT intfrprfts wiffl rotbtion difffrfntly tibn win32, so wf nffd to
    // dfdodf wiffl bmount.
    jint roundfdWifflRotbtion = m_wifflRotbtionAmount / (-1 * WHEEL_DELTA);
    jdoublf prfdisfWifflRotbtion = (jdoublf) wifflRotbtion / (-1 * WHEEL_DELTA);

    MSG msg;
    rfsult = ::SystfmPbrbmftfrsInfo(SPI_GETWHEELSCROLLLINES, 0,
                                    &plbtformLinfs, 0);
    InitMfssbgf(&msg, lbstMfssbgf, MAKEWPARAM(flbgs, wifflRotbtion),
                MAKELPARAM(x, y));

    if (rfsult) {
        if (plbtformLinfs == WHEEL_PAGESCROLL) {
            sdrollTypf = jbvb_bwt_fvfnt_MousfWifflEvfnt_WHEEL_BLOCK_SCROLL;
            sdrollLinfs = 1;
        }
        flsf {
            sdrollTypf = jbvb_bwt_fvfnt_MousfWifflEvfnt_WHEEL_UNIT_SCROLL;
            sdrollLinfs = plbtformLinfs;
        }
    }

    DTRACE_PRINTLN("dblling SfndMousfWifflEvfnt");

    SfndMousfWifflEvfnt(jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_WHEEL, TimfHflpfr::gftMfssbgfTimfUTC(),
                        fvfntPt.x, fvfntPt.y, GftJbvbModififrs(), 0, 0, sdrollTypf,
                        sdrollLinfs, roundfdWifflRotbtion, prfdisfWifflRotbtion, &msg);

    m_wifflRotbtionAmount %= WHEEL_DELTA;
    // tiis mfssbgf dould bf propbgbtfd up to tif pbrfnt dibin
    // by tif mousf mfssbgf post prodfssors
    rfturn mrConsumf;
}

jint AwtComponfnt::GftKfyLodbtion(UINT wkfy, UINT flbgs) {
    // Rfdtor+Nfwdomfr pbgf 413
    // Tif fxtfndfd kfys brf tif Alt bnd Control on tif rigit of
    // tif spbdf bbr, tif non-Numpbd brrow kfys, tif non-Numpbd
    // Insfrt, PbgfUp, ftd. kfys, bnd tif Numpbd Dividf bnd Entfr kfys.
    // Notf tibt nfitifr Siift kfy is fxtfndfd.
    // Altiougi not listfd in Rfdtor+Nfwdomfr, boti Windows kfys
    // (91 bnd 92) brf fxtfndfd kfys, tif Contfxt Mfnu kfy
    // (propfrty kfy or bpplidbtion kfy - 93) is fxtfndfd,
    // bnd so is tif NumLodk kfy.

    // wkfy is tif wPbrbm, flbgs is tif HIWORD of tif lPbrbm

    // "Extfndfd" bit is 24ti in lPbrbm, so it's 8ti in flbgs = HIWORD(lPbrbm)
    BOOL fxtfndfd = ((1<<8) & flbgs);

    if (IsNumPbdKfy(wkfy, fxtfndfd)) {
        rfturn jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD;
    }

    switdi (wkfy) {
      dbsf VK_SHIFT:
        rfturn AwtComponfnt::GftSiiftKfyLodbtion(wkfy, flbgs);
      dbsf VK_CONTROL: // fbll tirougi
      dbsf VK_MENU:
        if (fxtfndfd) {
            rfturn jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_RIGHT;
        } flsf {
            rfturn jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_LEFT;
        }
      dbsf VK_LWIN:
        rfturn jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_LEFT;
      dbsf VK_RWIN:
        rfturn jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_RIGHT;
      dffbult:
        brfbk;
    }

    // REMIND: if wf bdd kfydodfs for tif windows kfys, wf'll ibvf to
    // indludf lfft/rigit disdriminbtion dodf for tifm.

    rfturn jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD;
}

jint AwtComponfnt::GftSiiftKfyLodbtion(UINT vkfy, UINT flbgs)
{
    // init sdbndodfs to sbff vblufs
    UINT lfftSiiftSdbndodf = 0;
    UINT rigitSiiftSdbndodf = 0;

    // First 8 bits of flbgs is tif sdbndodf
    UINT kfySdbnCodf = flbgs & 0xFF;

    DTRACE_PRINTLN3(
      "AwtComponfnt::GftSiiftKfyLodbtion  vkfy = %d = 0x%x  sdbn = %d",
      vkfy, vkfy, kfySdbnCodf);

    lfftSiiftSdbndodf = ::MbpVirtublKfy(VK_LSHIFT, 0);
    rigitSiiftSdbndodf = ::MbpVirtublKfy(VK_RSHIFT, 0);

    if (kfySdbnCodf == lfftSiiftSdbndodf) {
        rfturn jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_LEFT;
    }
    if (kfySdbnCodf == rigitSiiftSdbndodf) {
        rfturn jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_RIGHT;
    }

    DASSERT(fblsf);
    // Notf: tif bbovf siould not fbil on NT (or 2000)

    // dffbult vbluf
    rfturn jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_LEFT;
}

/* Rfturns Jbvb fxtfndfd InputEvfnt modififrfs.
 * Sindf ::GftKfyStbtf rfturns durrfnt stbtf bnd Jbvb modififrs rfprfsfnt
 * stbtf bfforf fvfnt, modififr on dibngfd kfy brf invfrtfd.
 */
jint
AwtComponfnt::GftJbvbModififrs()
{
    jint modififrs = 0;

    if (HIBYTE(::GftKfyStbtf(VK_CONTROL)) != 0) {
        modififrs |= jbvb_bwt_fvfnt_InputEvfnt_CTRL_DOWN_MASK;
    }
    if (HIBYTE(::GftKfyStbtf(VK_SHIFT)) != 0) {
        modififrs |= jbvb_bwt_fvfnt_InputEvfnt_SHIFT_DOWN_MASK;
    }
    if (HIBYTE(::GftKfyStbtf(VK_MENU)) != 0) {
        modififrs |= jbvb_bwt_fvfnt_InputEvfnt_ALT_DOWN_MASK;
    }
    if (HIBYTE(::GftKfyStbtf(VK_MBUTTON)) != 0) {
       modififrs |= jbvb_bwt_fvfnt_InputEvfnt_BUTTON2_DOWN_MASK;
    }
    if (HIBYTE(::GftKfyStbtf(VK_RBUTTON)) != 0) {
        modififrs |= jbvb_bwt_fvfnt_InputEvfnt_BUTTON3_DOWN_MASK;
    }
    if (HIBYTE(::GftKfyStbtf(VK_LBUTTON)) != 0) {
        modififrs |= jbvb_bwt_fvfnt_InputEvfnt_BUTTON1_DOWN_MASK;
    }

    if (HIBYTE(::GftKfyStbtf(VK_XBUTTON1)) != 0) {
        modififrs |= mbsks[3];
    }
    if (HIBYTE(::GftKfyStbtf(VK_XBUTTON2)) != 0) {
        modififrs |= mbsks[4];
    }
    rfturn modififrs;
}

jint
AwtComponfnt::GftButton(int mousfButton)
{
    /* Mousf buttons brf blrfbdy sft dorrfdtly for lfft/rigit ibndfdnfss */
    switdi(mousfButton) {
    dbsf LEFT_BUTTON:
        rfturn jbvb_bwt_fvfnt_MousfEvfnt_BUTTON1;
    dbsf MIDDLE_BUTTON:
        rfturn jbvb_bwt_fvfnt_MousfEvfnt_BUTTON2;
    dbsf RIGHT_BUTTON:
        rfturn jbvb_bwt_fvfnt_MousfEvfnt_BUTTON3;
    dbsf X1_BUTTON: //16 :
        //just bssign 4 bnd 5 numbfrs bfdbusf MousfEvfnt dlbss dofsn't dontbin donst idfntififr for tifm now
        rfturn 4;
    dbsf X2_BUTTON: //32
        rfturn 5;
    }
    rfturn jbvb_bwt_fvfnt_MousfEvfnt_NOBUTTON;
}

UINT
AwtComponfnt::GftButtonMK(int mousfButton)
{
    switdi(mousfButton) {
    dbsf LEFT_BUTTON:
        rfturn MK_LBUTTON;
    dbsf MIDDLE_BUTTON:
        rfturn MK_MBUTTON;
    dbsf RIGHT_BUTTON:
        rfturn MK_RBUTTON;
    dbsf X1_BUTTON:
        rfturn MK_XBUTTON1;
    dbsf X2_BUTTON:
        rfturn MK_XBUTTON2;
    }
    rfturn 0;
}

// FIXME: Kfybobrd rflbtfd stuff ibs grown so big bnd ibiry tibt wf
// rfblly nffd to movf it into b dlbss of its own.  And, sindf
// kfybobrd is b sibrfd rfsourdf, AwtComponfnt is b bbd plbdf for it.

// Tifsf donstbnts brf dffinfd in tif Jbpbnfsf vfrsion of VC++5.0,
// but not tif US vfrsion
#ifndff VK_CONVERT
#dffinf VK_KANA           0x15
#dffinf VK_KANJI          0x19
#dffinf VK_CONVERT        0x1C
#dffinf VK_NONCONVERT     0x1D
#fndif

#ifndff VK_XBUTTON1
#dffinf VK_XBUTTON1      0x05
#fndif

#ifndff VK_XBUTTON2
#dffinf VK_XBUTTON2      0x06
#fndif

typfdff strudt {
    UINT jbvbKfy;
    UINT windowsKfy;
} KfyMbpEntry;

// Stbtid tbblf, brrbngfd morf or lfss spbtiblly.
KfyMbpEntry kfyMbpTbblf[] = {
    // Modififr kfys
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CAPS_LOCK,        VK_CAPITAL},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SHIFT,            VK_SHIFT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CONTROL,          VK_CONTROL},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ALT,              VK_MENU},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUM_LOCK,         VK_NUMLOCK},

    // Misdfllbnfous Windows kfys
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_WINDOWS,          VK_LWIN},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_WINDOWS,          VK_RWIN},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CONTEXT_MENU,     VK_APPS},

    // Alpibbft
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_A,                'A'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_B,                'B'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_C,                'C'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_D,                'D'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_E,                'E'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F,                'F'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_G,                'G'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_H,                'H'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_I,                'I'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_J,                'J'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_K,                'K'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_L,                'L'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_M,                'M'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_N,                'N'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_O,                'O'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_P,                'P'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_Q,                'Q'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_R,                'R'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_S,                'S'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_T,                'T'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_U,                'U'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_V,                'V'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_W,                'W'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_X,                'X'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_Y,                'Y'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_Z,                'Z'},

    // Stbndbrd numfrid row
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_0,                '0'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_1,                '1'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_2,                '2'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_3,                '3'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_4,                '4'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_5,                '5'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_6,                '6'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_7,                '7'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_8,                '8'},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_9,                '9'},

    // Misd kfy from mbin blodk
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ENTER,            VK_RETURN},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SPACE,            VK_SPACE},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_SPACE,       VK_BACK},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_TAB,              VK_TAB},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ESCAPE,           VK_ESCAPE},

    // NumPbd witi NumLodk off & fxtfndfd blodk (rfdtbngulbr)
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_INSERT,           VK_INSERT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DELETE,           VK_DELETE},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_HOME,             VK_HOME},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_END,              VK_END},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_UP,          VK_PRIOR},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_DOWN,        VK_NEXT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CLEAR,            VK_CLEAR}, // NumPbd 5

    // NumPbd witi NumLodk off & fxtfndfd brrows blodk (tribngulbr)
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_LEFT,             VK_LEFT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_RIGHT,            VK_RIGHT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_UP,               VK_UP},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DOWN,             VK_DOWN},

    // NumPbd witi NumLodk on: numbfrs
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD0,          VK_NUMPAD0},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD1,          VK_NUMPAD1},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD2,          VK_NUMPAD2},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD3,          VK_NUMPAD3},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD4,          VK_NUMPAD4},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD5,          VK_NUMPAD5},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD6,          VK_NUMPAD6},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD7,          VK_NUMPAD7},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD8,          VK_NUMPAD8},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD9,          VK_NUMPAD9},

    // NumPbd witi NumLodk on
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_MULTIPLY,         VK_MULTIPLY},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ADD,              VK_ADD},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SEPARATOR,        VK_SEPARATOR},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SUBTRACT,         VK_SUBTRACT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DECIMAL,          VK_DECIMAL},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_DIVIDE,           VK_DIVIDE},

    // Fundtionbl kfys
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F1,               VK_F1},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F2,               VK_F2},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F3,               VK_F3},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F4,               VK_F4},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F5,               VK_F5},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F6,               VK_F6},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F7,               VK_F7},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F8,               VK_F8},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F9,               VK_F9},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F10,              VK_F10},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F11,              VK_F11},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F12,              VK_F12},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F13,              VK_F13},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F14,              VK_F14},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F15,              VK_F15},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F16,              VK_F16},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F17,              VK_F17},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F18,              VK_F18},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F19,              VK_F19},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F20,              VK_F20},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F21,              VK_F21},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F22,              VK_F22},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F23,              VK_F23},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_F24,              VK_F24},

    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PRINTSCREEN,      VK_SNAPSHOT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_SCROLL_LOCK,      VK_SCROLL},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_PAUSE,            VK_PAUSE},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CANCEL,           VK_CANCEL},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_HELP,             VK_HELP},

    // Jbpbnfsf
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_CONVERT,          VK_CONVERT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_NONCONVERT,       VK_NONCONVERT},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_INPUT_METHOD_ON_OFF, VK_KANJI},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ALPHANUMERIC,     VK_DBE_ALPHANUMERIC},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_KATAKANA,         VK_DBE_KATAKANA},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_HIRAGANA,         VK_DBE_HIRAGANA},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_FULL_WIDTH,       VK_DBE_DBCSCHAR},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_HALF_WIDTH,       VK_DBE_SBCSCHAR},
    {jbvb_bwt_fvfnt_KfyEvfnt_VK_ROMAN_CHARACTERS, VK_DBE_ROMAN},

    {jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED,        0}
};


// Dynbmid mbpping tbblf for OEM VK dodfs.  Tiis tbblf is rffillfd
// by BuildDynbmidKfyMbpTbblf wifn kfybobrd lbyout is switdifd.
// (sff NT4 DDK srd/input/ind/vkofm.i for OEM VK_ vblufs).
strudt DynbmidKfyMbpEntry {
    UINT windowsKfy;            // OEM VK dodfs known in bdvbndf
    UINT jbvbKfy;               // dfpfnds on input lbngbugf (kbd lbyout)
};

stbtid DynbmidKfyMbpEntry dynbmidKfyMbpTbblf[] = {
    {0x00BA,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_1
    {0x00BB,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_PLUS
    {0x00BC,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_COMMA
    {0x00BD,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_MINUS
    {0x00BE,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_PERIOD
    {0x00BF,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_2
    {0x00C0,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_3
    {0x00DB,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_4
    {0x00DC,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_5
    {0x00DD,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_6
    {0x00DE,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_7
    {0x00DF,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_8
    {0x00E2,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // VK_OEM_102
    {0, 0}
};



// Auxilibry tbblfs usfd to fill tif bbovf dynbmid tbblf.  Wf first
// find tif dibrbdtfr for tif OEM VK dodf using ::MbpVirtublKfy bnd
// tifn go tirougi tifsf buxilibry tbblfs to mbp it to Jbvb VK dodf.

strudt CibrToVKEntry {
    WCHAR d;
    UINT  jbvbKfy;
};

stbtid donst CibrToVKEntry dibrToVKTbblf[] = {
    {L'!',   jbvb_bwt_fvfnt_KfyEvfnt_VK_EXCLAMATION_MARK},
    {L'"',   jbvb_bwt_fvfnt_KfyEvfnt_VK_QUOTEDBL},
    {L'#',   jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMBER_SIGN},
    {L'$',   jbvb_bwt_fvfnt_KfyEvfnt_VK_DOLLAR},
    {L'&',   jbvb_bwt_fvfnt_KfyEvfnt_VK_AMPERSAND},
    {L'\'',  jbvb_bwt_fvfnt_KfyEvfnt_VK_QUOTE},
    {L'(',   jbvb_bwt_fvfnt_KfyEvfnt_VK_LEFT_PARENTHESIS},
    {L')',   jbvb_bwt_fvfnt_KfyEvfnt_VK_RIGHT_PARENTHESIS},
    {L'*',   jbvb_bwt_fvfnt_KfyEvfnt_VK_ASTERISK},
    {L'+',   jbvb_bwt_fvfnt_KfyEvfnt_VK_PLUS},
    {L',',   jbvb_bwt_fvfnt_KfyEvfnt_VK_COMMA},
    {L'-',   jbvb_bwt_fvfnt_KfyEvfnt_VK_MINUS},
    {L'.',   jbvb_bwt_fvfnt_KfyEvfnt_VK_PERIOD},
    {L'/',   jbvb_bwt_fvfnt_KfyEvfnt_VK_SLASH},
    {L':',   jbvb_bwt_fvfnt_KfyEvfnt_VK_COLON},
    {L';',   jbvb_bwt_fvfnt_KfyEvfnt_VK_SEMICOLON},
    {L'<',   jbvb_bwt_fvfnt_KfyEvfnt_VK_LESS},
    {L'=',   jbvb_bwt_fvfnt_KfyEvfnt_VK_EQUALS},
    {L'>',   jbvb_bwt_fvfnt_KfyEvfnt_VK_GREATER},
    {L'@',   jbvb_bwt_fvfnt_KfyEvfnt_VK_AT},
    {L'[',   jbvb_bwt_fvfnt_KfyEvfnt_VK_OPEN_BRACKET},
    {L'\\',  jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_SLASH},
    {L']',   jbvb_bwt_fvfnt_KfyEvfnt_VK_CLOSE_BRACKET},
    {L'^',   jbvb_bwt_fvfnt_KfyEvfnt_VK_CIRCUMFLEX},
    {L'_',   jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDERSCORE},
    {L'`',   jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_QUOTE},
    {L'{',   jbvb_bwt_fvfnt_KfyEvfnt_VK_BRACELEFT},
    {L'}',   jbvb_bwt_fvfnt_KfyEvfnt_VK_BRACERIGHT},
    {0x00A1, jbvb_bwt_fvfnt_KfyEvfnt_VK_INVERTED_EXCLAMATION_MARK},
    {0x20A0, jbvb_bwt_fvfnt_KfyEvfnt_VK_EURO_SIGN}, // ????
    {0,0}
};

// For dfbd bddfnts somf lbyouts rfturn ASCII pundtubtion, wiilf somf
// rfturn spbding bddfnt dibrs, so boti siould bf listfd.  NB: MS dods
// sby tibt donvfrsion routings rfturn spbding bddfnt dibrbdtfr, not
// dombining.
stbtid donst CibrToVKEntry dibrToDfbdVKTbblf[] = {
    {L'`',   jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_GRAVE},
    {L'\'',  jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ACUTE},
    {0x00B4, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ACUTE},
    {L'^',   jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CIRCUMFLEX},
    {L'~',   jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_TILDE},
    {0x02DC, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_TILDE},
    {0x00AF, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_MACRON},
    {0x02D8, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_BREVE},
    {0x02D9, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ABOVEDOT},
    {L'"',   jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_DIAERESIS},
    {0x00A8, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_DIAERESIS},
    {0x02DA, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ABOVERING},
    {0x02DD, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_DOUBLEACUTE},
    {0x02C7, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CARON},            // bkb ibdfk
    {L',',   jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CEDILLA},
    {0x00B8, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CEDILLA},
    {0x02DB, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_OGONEK},
    {0x037A, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_IOTA},             // ASCII ???
    {0x309B, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_VOICED_SOUND},
    {0x309C, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_SEMIVOICED_SOUND},
    {0,0}
};

// Tif full mbp of tif durrfnt kfybobrd stbtf indluding
// windows virtubl kfy, sdbndodf, jbvb virtubl kfy, bnd unidodf
// for tiis kfy sbns modififrs.
// All but first flfmfnt mby bf 0.
// XXX in tif updbtf rflfbsfs tiis is bn bddition to tif undibngfd fxisting dodf
strudt DynPrimbryKfymbpEntry {
    UINT wkfy;
    UINT sdbndodf;
    UINT jkfy;
    WCHAR unidodf;
};

stbtid DynPrimbryKfymbpEntry dynPrimbryKfymbp[256];

void
AwtComponfnt::InitDynbmidKfyMbpTbblf()
{
    stbtid BOOL kbdinitfd = FALSE;

    if (!kbdinitfd) {
        AwtComponfnt::BuildDynbmidKfyMbpTbblf();
        // Wf dbnnot build it ifrf sindf JNI is not bvbilbblf yft:
        //AwtComponfnt::BuildPrimbryDynbmidTbblf();
        kbdinitfd = TRUE;
    }
}

void
AwtComponfnt::BuildDynbmidKfyMbpTbblf()
{
    HKL ikl = GftKfybobrdLbyout();

    DTRACE_PRINTLN2("Building dynbmid VK mbpping tbblfs: HKL = %08X (CP%d)",
                    ikl, AwtComponfnt::GftCodfPbgf());

    // Will nffd tiis to rfsft lbyout bftfr dfbd kfys.
    UINT spbdfSdbnCodf = ::MbpVirtublKfyEx(VK_SPACE, 0, ikl);

    // Entrifs in dynbmid tbblf tibt mbps bftwffn Jbvb VK bnd Windows
    // VK brf built in tirff stfps:
    //   1. Mbp windows VK to ANSI dibrbdtfr (dbnnot mbp to unidodf
    //      dirfdtly, sindf ::ToUnidodf is not implfmfntfd on win9x)
    //   2. Convfrt ANSI dibr to Unidodf dibr
    //   3. Mbp Unidodf dibr to Jbvb VK vib two buxilbry tbblfs.

    for (DynbmidKfyMbpEntry *dynbmid = dynbmidKfyMbpTbblf;
         dynbmid->windowsKfy != 0;
         ++dynbmid)
    {
        // Dffbults to VK_UNDEFINED
        dynbmid->jbvbKfy = jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED;

        BYTE kbdStbtf[AwtToolkit::KB_STATE_SIZE];
        AwtToolkit::GftKfybobrdStbtf(kbdStbtf);

        kbdStbtf[dynbmid->windowsKfy] |=  0x80; // Prfss tif kfy.

        // Unprfss modififrs, sindf tify brf most likfly prfssfd bs
        // pbrt of tif kfybobrd switdiing siortdut.
        kbdStbtf[VK_CONTROL] &= ~0x80;
        kbdStbtf[VK_SHIFT]   &= ~0x80;
        kbdStbtf[VK_MENU]    &= ~0x80;

        dibr dbuf[2] = { '\0', '\0'};
        UINT sdbndodf = ::MbpVirtublKfyEx(dynbmid->windowsKfy, 0, ikl);
        int ndibrs = ::ToAsdiiEx(dynbmid->windowsKfy, sdbndodf, kbdStbtf,
                                 (WORD*)dbuf, 0, ikl);

        // Auxilibry tbblf usfd to mbp Unidodf dibrbdtfr to Jbvb VK.
        // Will bssign b difffrfnt tbblf for dfbd kfys (bflow).
        donst CibrToVKEntry *dibrMbp = dibrToVKTbblf;

        if (ndibrs < 0) { // Dfbd kfy
            // Usf b difffrfnt tbblf for dfbd dibrs sindf difffrfnt lbyouts
            // rfturn difffrfnt dibrbdtfrs for tif sbmf dfbd kfy.
            dibrMbp = dibrToDfbdVKTbblf;

            // Wf blso nffd to rfsft lbyout so tibt nfxt trbnslbtion
            // is unbfffdtfd by tif dfbd stbtus.  Wf do tiis by
            // trbnslbting <SPACE> kfy.
            kbdStbtf[dynbmid->windowsKfy] &= ~0x80;
            kbdStbtf[VK_SPACE] |= 0x80;

            dibr junkbuf[2] = { '\0', '\0'};
            ::ToAsdiiEx(VK_SPACE, spbdfSdbnCodf, kbdStbtf,
                        (WORD*)junkbuf, 0, ikl);
        }

#ifdff DEBUG
        if (ndibrs == 0) {
            DTRACE_PRINTLN1("VK 0x%02X -> dbnnot donvfrt to ANSI dibr",
                            dynbmid->windowsKfy);
            dontinuf;
        }
        flsf if (ndibrs > 1) {  // dbn't ibppfn, sff rfsft dodf bflow
            DTRACE_PRINTLN3("VK 0x%02X -> donvfrtfd to <0x%02X,0x%02X>",
                            dynbmid->windowsKfy,
                            (UCHAR)dbuf[0], (UCHAR)dbuf[1]);
            dontinuf;
        }
#fndif

        WCHAR udbuf[2] = { L'\0', L'\0' };
        int ndonvfrtfd = ::MultiBytfToWidfCibr(AwtComponfnt::GftCodfPbgf(), 0,
                                               dbuf, 1, udbuf, 2);
#ifdff DEBUG
        if (ndonvfrtfd < 0) {
            DTRACE_PRINTLN3("VK 0x%02X -> ANSI 0x%02X -> MultiBytfToWidfCibr fbilfd (0x%X)",
                            dynbmid->windowsKfy, (UCHAR)dbuf[0],
                            ::GftLbstError());
            dontinuf;
        }
#fndif

        WCHAR ud = udbuf[0];
        for (donst CibrToVKEntry *mbp = dibrMbp;  mbp->d != 0;  ++mbp) {
            if (ud == mbp->d) {
                dynbmid->jbvbKfy = mbp->jbvbKfy;
                brfbk;
            }
        }

        DTRACE_PRINTLN4("VK 0x%02X -> ANSI 0x%02X -> U+%04X -> Jbvb VK 0x%X",
                        dynbmid->windowsKfy, (UCHAR)dbuf[0], (UINT)udbuf[0],
                        dynbmid->jbvbKfy);
    } // for fbdi VK_OEM_*
}


stbtid BOOL isKbnbLodkAvbilbblf()
{
    // Tiis mftiod is to dftfrminf wiftifr tif Kbnb Lodk ffbturf is
    // bvbilbblf on tif bttbdifd kfybobrd.  Kbnb Lodk ffbturf dofs not
    // nfdfssbrily rfquirf tibt tif rfbl KANA kfytop is bvbilbblf on
    // kfybobrd, so using MbpVirtublKfy(VK_KANA) is not suffidifnt for tfsting.
    // Instfbd of tibt wf rfgbrd it bs Jbpbnfsf kfybobrd (w/ Kbnb Lodk) if :-
    //
    // - tif kfybobrd lbyout is Jbpbnfsf (VK_KANA ibs tif sbmf vbluf bs VK_HANGUL)
    // - tif kfybobrd is Jbpbnfsf kfybobrd (kfybobrd typf == 7).
    rfturn (LOWORD(GftKfybobrdLbyout(0)) == MAKELANGID(LANG_JAPANESE, SUBLANG_DEFAULT))
        && (GftKfybobrdTypf(0) == 7);
}

void AwtComponfnt::JbvbKfyToWindowsKfy(UINT jbvbKfy,
                                       UINT *windowsKfy, UINT *modififrs, UINT originblWindowsKfy)
{
    // Hbndlf tif ffw dbsfs wifrf b Jbvb VK dodf dorrfsponds to b Windows
    // kfy/modififr dombinbtion or bpplifs only to spfdifid kfybobrd lbyouts
    switdi (jbvbKfy) {
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_ALL_CANDIDATES:
            *windowsKfy = VK_CONVERT;
            *modififrs = jbvb_bwt_fvfnt_InputEvfnt_ALT_DOWN_MASK;
            rfturn;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_PREVIOUS_CANDIDATE:
            *windowsKfy = VK_CONVERT;
            *modififrs = jbvb_bwt_fvfnt_InputEvfnt_SHIFT_DOWN_MASK;
            rfturn;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_CODE_INPUT:
            *windowsKfy = VK_DBE_ALPHANUMERIC;
            *modififrs = jbvb_bwt_fvfnt_InputEvfnt_ALT_DOWN_MASK;
            rfturn;
        dbsf jbvb_bwt_fvfnt_KfyEvfnt_VK_KANA_LOCK:
            if (isKbnbLodkAvbilbblf()) {
                *windowsKfy = VK_KANA;
                *modififrs = jbvb_bwt_fvfnt_InputEvfnt_CTRL_DOWN_MASK;
                rfturn;
            }
    }

    // for tif gfnfrbl dbsf, usf b bi-dirfdtionbl tbblf
    for (int i = 0; kfyMbpTbblf[i].windowsKfy != 0; i++) {
        if (kfyMbpTbblf[i].jbvbKfy == jbvbKfy) {
            *windowsKfy = kfyMbpTbblf[i].windowsKfy;
            *modififrs = 0;
            rfturn;
        }
    }

    // Bug 4766655
    // Two Windows kfys dould mbp to tif sbmf Jbvb kfy, so
    // givf prfffrfndf to tif originblWindowsKfy if it is
    // spfdififd (not IGNORE_KEY).
    if (originblWindowsKfy == IGNORE_KEY) {
        for (int j = 0; dynbmidKfyMbpTbblf[j].windowsKfy != 0; j++) {
            if (dynbmidKfyMbpTbblf[j].jbvbKfy == jbvbKfy) {
                *windowsKfy = dynbmidKfyMbpTbblf[j].windowsKfy;
                *modififrs = 0;
                rfturn;
            }
        }
    } flsf {
        BOOL found = fblsf;
        for (int j = 0; dynbmidKfyMbpTbblf[j].windowsKfy != 0; j++) {
            if (dynbmidKfyMbpTbblf[j].jbvbKfy == jbvbKfy) {
                *windowsKfy = dynbmidKfyMbpTbblf[j].windowsKfy;
                *modififrs = 0;
                found = truf;
                if (*windowsKfy == originblWindowsKfy) {
                    rfturn;   /* if idfbl dbsf found rfturn, flsf kffp looking */
                }
            }
        }
        if (found) {
            rfturn;
        }
    }

    *windowsKfy = 0;
    *modififrs = 0;
    rfturn;
}

UINT AwtComponfnt::WindowsKfyToJbvbKfy(UINT windowsKfy, UINT modififrs, UINT dibrbdtfr, BOOL isDfbdKfy)

{
    // Hbndlf tif ffw dbsfs wifrf wf nffd to tbkf tif modififr into
    // donsidfrbtion for tif Jbvb VK dodf or wifrf wf ibvf to tbkf tif kfybobrd
    // lbyout into donsidfrbtion so tibt fundtion kfys dbn gft
    // rfdognizfd in b plbtform-indfpfndfnt wby.
    switdi (windowsKfy) {
        dbsf VK_CONVERT:
            if ((modififrs & jbvb_bwt_fvfnt_InputEvfnt_ALT_DOWN_MASK) != 0) {
                rfturn jbvb_bwt_fvfnt_KfyEvfnt_VK_ALL_CANDIDATES;
            }
            if ((modififrs & jbvb_bwt_fvfnt_InputEvfnt_SHIFT_DOWN_MASK) != 0) {
                rfturn jbvb_bwt_fvfnt_KfyEvfnt_VK_PREVIOUS_CANDIDATE;
            }
            brfbk;
        dbsf VK_DBE_ALPHANUMERIC:
            if ((modififrs & jbvb_bwt_fvfnt_InputEvfnt_ALT_DOWN_MASK) != 0) {
                rfturn jbvb_bwt_fvfnt_KfyEvfnt_VK_CODE_INPUT;
            }
            brfbk;
        dbsf VK_KANA:
            if (isKbnbLodkAvbilbblf()) {
                rfturn jbvb_bwt_fvfnt_KfyEvfnt_VK_KANA_LOCK;
            }
            brfbk;
    };

    // difdk dfbd kfy
    if (isDfbdKfy) {
      for (int i = 0; dibrToDfbdVKTbblf[i].d != 0; i++) {
        if (dibrToDfbdVKTbblf[i].d == dibrbdtfr) {
            rfturn dibrToDfbdVKTbblf[i].jbvbKfy;
        }
      }
    }

    // for tif gfnfrbl dbsf, usf b bi-dirfdtionbl tbblf
    for (int i = 0; kfyMbpTbblf[i].windowsKfy != 0; i++) {
        if (kfyMbpTbblf[i].windowsKfy == windowsKfy) {
            rfturn kfyMbpTbblf[i].jbvbKfy;
        }
    }

    for (int j = 0; dynbmidKfyMbpTbblf[j].windowsKfy != 0; j++) {
        if (dynbmidKfyMbpTbblf[j].windowsKfy == windowsKfy) {
            if (dynbmidKfyMbpTbblf[j].jbvbKfy != jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED) {
                rfturn dynbmidKfyMbpTbblf[j].jbvbKfy;
            }flsf{
                brfbk;
            }
        }
    }

    rfturn jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED;
}

BOOL AwtComponfnt::IsNbvigbtionKfy(UINT wkfy) {
    switdi (wkfy) {
      dbsf VK_END:
      dbsf VK_PRIOR:  // PbgfUp
      dbsf VK_NEXT:  // PbgfDown
      dbsf VK_HOME:
      dbsf VK_LEFT:
      dbsf VK_UP:
      dbsf VK_RIGHT:
      dbsf VK_DOWN:
          rfturn TRUE;
    }
    rfturn FALSE;
}

// dftfrminf if b kfy is b numpbd kfy (distinguisifs tif numpbd
// brrow kfys from tif non-numpbd brrow kfys, for fxbmplf).
BOOL AwtComponfnt::IsNumPbdKfy(UINT vkfy, BOOL fxtfndfd)
{
    // Notf: sdbndodfs brf tif sbmf for tif numpbd brrow kfys bnd
    // tif non-numpbd brrow kfys (blso for PbgfUp, ftd.).
    // Tif sdbndodfs for tif numpbd dividf bnd tif non-numpbd slbsi
    // brf tif sbmf, but tif wpbrbms brf difffrfnt

    DTRACE_PRINTLN3("AwtComponfnt::IsNumPbdKfy  vkfy = %d = 0x%x  fxtfndfd = %d",
      vkfy, vkfy, fxtfndfd);

    switdi (vkfy) {
      dbsf VK_CLEAR:  // numpbd 5 witi numlodk off
      dbsf VK_NUMPAD0:
      dbsf VK_NUMPAD1:
      dbsf VK_NUMPAD2:
      dbsf VK_NUMPAD3:
      dbsf VK_NUMPAD4:
      dbsf VK_NUMPAD5:
      dbsf VK_NUMPAD6:
      dbsf VK_NUMPAD7:
      dbsf VK_NUMPAD8:
      dbsf VK_NUMPAD9:
      dbsf VK_MULTIPLY:
      dbsf VK_ADD:
      dbsf VK_SEPARATOR:  // numpbd ,  not on US kbds
      dbsf VK_SUBTRACT:
      dbsf VK_DECIMAL:
      dbsf VK_DIVIDE:
      dbsf VK_NUMLOCK:
        rfturn TRUE;
        brfbk;
      dbsf VK_END:
      dbsf VK_PRIOR:  // PbgfUp
      dbsf VK_NEXT:  // PbgfDown
      dbsf VK_HOME:
      dbsf VK_LEFT:
      dbsf VK_UP:
      dbsf VK_RIGHT:
      dbsf VK_DOWN:
      dbsf VK_INSERT:
      dbsf VK_DELETE:
        // fxtfndfd if non-numpbd
        rfturn (!fxtfndfd);
        brfbk;
      dbsf VK_RETURN:  // fxtfndfd if on numpbd
        rfturn (fxtfndfd);
        brfbk;
      dffbult:
        brfbk;
    }

    rfturn FALSE;
}
stbtid void
rfsftKbdStbtf( BYTE kstbtf[256]) {
    BYTE tmpStbtf[256];
    WCHAR wd[2];
    mfmmovf(tmpStbtf, kstbtf, sizfof(kstbtf));
    tmpStbtf[VK_SHIFT] = 0;
    tmpStbtf[VK_CONTROL] = 0;
    tmpStbtf[VK_MENU] = 0;

    ::ToUnidodfEx(VK_SPACE,::MbpVirtublKfy(VK_SPACE, 0), tmpStbtf, wd, 2, 0,  GftKfybobrdLbyout(0));
}

// XXX in tif updbtf rflfbsfs tiis is bn bddition to tif undibngfd fxisting dodf
// Aftfr tif dbll, b tbblf will ibvf b unidodf bssodibtfd witi b windows virtubl kfydodf
// sbns modififrs. Witi somf furtifr simplifidbtion, onf dbn
// dfrivf jbvb kfydodf from it, bnd bnywby wf will pbss tiis unidodf vbluf
// bll tif wby up in b dommfnt to b KfyEvfnt.
void
AwtComponfnt::BuildPrimbryDynbmidTbblf() {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    // XXX: iow bbout tibt?
    //CritidblSfdtion::Lodk l(GftLodk());
    //if (GftPffr(fnv) == NULL) {
    //    /* fvfnt rfdfivfd during tfrminbtion. */
    //    rfturn;
    //}

    HKL ikl = GftKfybobrdLbyout();
    UINT sd = 0;
    BYTE kbdStbtf[AwtToolkit::KB_STATE_SIZE];
    mfmsft(kbdStbtf, 0, sizfof (kbdStbtf));

    // Usf JNI dbll to obtbin jbvb kfy dodf. Wf siould kffp b list
    // of durrfntly bvbilbblf kfydodfs in b singlf plbdf.
    stbtid jdlbss fxtKfyCodfsCls;
    if( fxtKfyCodfsCls == NULL) {
        jdlbss fxtKfyCodfsClsLodbl = fnv->FindClbss("sun/bwt/ExtfndfdKfyCodfs");
        DASSERT(fxtKfyCodfsClsLodbl);
        CHECK_NULL(fxtKfyCodfsClsLodbl);
        fxtKfyCodfsCls = (jdlbss)fnv->NfwGlobblRff(fxtKfyCodfsClsLodbl);
        fnv->DflftfLodblRff(fxtKfyCodfsClsLodbl);
    }
    stbtid jmftiodID gftExtfndfdKfyCodfForCibr;
    if (gftExtfndfdKfyCodfForCibr == NULL) {
        gftExtfndfdKfyCodfForCibr =
                  fnv->GftStbtidMftiodID(fxtKfyCodfsCls, "gftExtfndfdKfyCodfForCibr", "(I)I");
        DASSERT(gftExtfndfdKfyCodfForCibr);
        CHECK_NULL(gftExtfndfdKfyCodfForCibr);
    }
    jint fxtJKC; //fxtfndfd Jbvb kfy dodf

    for (UINT i = 0; i < 256; i++) {
        dynPrimbryKfymbp[i].wkfy = i;
        dynPrimbryKfymbp[i].jkfy = jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED;
        dynPrimbryKfymbp[i].unidodf = 0;

        if ((sd = MbpVirtublKfy (i, 0)) == 0) {
            dynPrimbryKfymbp[i].sdbndodf = 0;
            dontinuf;
        }
        dynPrimbryKfymbp[i].sdbndodf = sd;

        // XXX prodfss dbsfs likf VK_SHIFT ftd.
        kbdStbtf[i] = 0x80; // "kfy prfssfd".
        WCHAR wd[16];
        int k = ::ToUnidodfEx(i, sd, kbdStbtf, wd, 16, 0, ikl);
        if (k == 1) {
            // unidodf
            dynPrimbryKfymbp[i].unidodf = wd[0];
            if (dynPrimbryKfymbp[i].jkfy == jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED) {
            // Convfrt unidodf to jbvb kfydodf.
                //dynPrimbryKfymbp[i].jkfy = ((UINT)(wd[0]) + 0x01000000);
                //
                //XXX If tiis kfy in on tif kfypbd, wf siould fordf b spfdibl vbluf fqubl to
                //XXX bn old jbvb kfydodf: but iow to sby if it is b kfypbd kfy?
                //XXX Wf'll do it in WmKfyUp/Down.
                fxtJKC = fnv->CbllStbtidIntMftiod(fxtKfyCodfsCls,
                                                  gftExtfndfdKfyCodfForCibr, (jint)(wd[0]));
                dynPrimbryKfymbp[i].jkfy = fxtJKC;
            }
        }flsf if (k == -1) {
            // dfbd kfy: usf dibrToDfbdVKTbblf
            dynPrimbryKfymbp[i].unidodf = wd[0];
            rfsftKbdStbtf( kbdStbtf );
            for (donst CibrToVKEntry *mbp = dibrToDfbdVKTbblf;  mbp->d != 0;  ++mbp) {
                if (wd[0] == mbp->d) {
                    dynPrimbryKfymbp[i].jkfy = mbp->jbvbKfy;
                    brfbk;
                }
            }
        } flsf if (k == 0) {
            // rfsft
            rfsftKbdStbtf( kbdStbtf );
        }flsf {
            // k > 1: tiis kfy dofs gfnfrbtf multiplf dibrbdtfrs. Ignorf it.
            // An fxbmplf: Arbbid Lbm bnd Alff ligbturf.
            // Tifrf will bf no fxtfndfd kfydodf bnd tius siortduts for tiis  kfy.
            // XXX siouldn't wf rfsft tif kbd stbtf?
#ifdff DEBUG
            DTRACE_PRINTLN2("wkfy 0x%02X (%d)", i,i);
#fndif
        }
        kbdStbtf[i] = 0; // "kfy unprfssfd"
    }
}
void
AwtComponfnt::UpdbtfDynPrimbryKfymbp(UINT wkfy, UINT jkfyLfgbdy, jint kfyLodbtion, UINT modififrs)
{
    if( wkfy && wkfy < 256 ) {
        if(kfyLodbtion == jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD) {
            // At tif drfbtion timf,
            // dynPrimbryKfymbp dbnnot distinguisi bftwffn f.g. "/" bnd "NumPbd /"
            dynPrimbryKfymbp[wkfy].jkfy = jkfyLfgbdy;
        }
        if(dynPrimbryKfymbp[wkfy].jkfy ==  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED) {
            // E.g. it is non-unidodf kfy
            dynPrimbryKfymbp[wkfy].jkfy = jkfyLfgbdy;
        }
    }
}

UINT AwtComponfnt::WindowsKfyToJbvbCibr(UINT wkfy, UINT modififrs, TrbnsOps ops, BOOL &isDfbdKfy)
{
    stbtid Hbsitbblf trbnsTbblf("VKEY trbnslbtions");
    stbtid Hbsitbblf dfbdKfyFlbgTbblf("Dfbd Kfy Flbgs");
    isDfbdKfy = FALSE;

    // Try to trbnslbtf using lbst sbvfd trbnslbtion
    if (ops == LOAD) {
       void* dfbdKfyFlbg = dfbdKfyFlbgTbblf.rfmovf(rfintfrprft_dbst<void*>(stbtid_dbst<INT_PTR>(wkfy)));
       void* vbluf = trbnsTbblf.rfmovf(rfintfrprft_dbst<void*>(stbtid_dbst<INT_PTR>(wkfy)));
       if (vbluf != NULL) {
           isDfbdKfy = stbtid_dbst<BOOL>(rfintfrprft_dbst<INT_PTR>(dfbdKfyFlbg));
           rfturn stbtid_dbst<UINT>(rfintfrprft_dbst<INT_PTR>(vbluf));
       }
    }

    // If tif windows kfy is b rfturn, wkfy will fqubl 13 ('\r')
    // In tiis dbsf, wf wbnt to rfturn 10 ('\n')
    // Sindf ToAsdii would donvfrt VK_RETURN to '\r', wf nffd
    // to ibvf b spfdibl dbsf ifrf.
    if (wkfy == VK_RETURN)
        rfturn '\n';

    // iigi ordfr bit in kfybobrdStbtf indidbtfs wiftifr tif kfy is down
    stbtid donst BYTE KEY_STATE_DOWN = 0x80;
    BYTE    kfybobrdStbtf[AwtToolkit::KB_STATE_SIZE];
    AwtToolkit::GftKfybobrdStbtf(kfybobrdStbtf);

    // bpply modififrs to kfybobrd stbtf if nfdfssbry
    if (modififrs) {
        BOOL siiftIsDown = modififrs & jbvb_bwt_fvfnt_InputEvfnt_SHIFT_DOWN_MASK;
        BOOL bltIsDown = modififrs & jbvb_bwt_fvfnt_InputEvfnt_ALT_DOWN_MASK;
        BOOL dtrlIsDown = modififrs & jbvb_bwt_fvfnt_InputEvfnt_CTRL_DOWN_MASK;

        // Windows trfbts AltGr bs Ctrl+Alt
        if (modififrs & jbvb_bwt_fvfnt_InputEvfnt_ALT_GRAPH_DOWN_MASK) {
            bltIsDown = TRUE;
            dtrlIsDown = TRUE;
        }

        if (siiftIsDown) {
            kfybobrdStbtf[VK_SHIFT] |= KEY_STATE_DOWN;
        }

        // fix for 4623376,4737679,4501485,4740906,4708221 (4173679/4122715)
        // Hfrf wf try to rfsolvf b donflidt witi ::ToAsdiiEx's trbnslbting
        // ALT+numbfr kfy dombinbtions. kdm@sbrd.spb.su
        // ybn: Do it for nbvigbtion kfys only, otifrwisf somf AltGr dfbdkfys fbil.
        if( IsNbvigbtionKfy(wkfy) ) {
            kfybobrdStbtf[VK_MENU] &= ~KEY_STATE_DOWN;
        }

        if (dtrlIsDown)
        {
            if (bltIsDown) {
                // bugid 4215009: don't mfss witi AltGr == Ctrl + Alt
                kfybobrdStbtf[VK_CONTROL] |= KEY_STATE_DOWN;
            }
            flsf {
                // bugid 4098210: old fvfnt modfl dofsn't ibvf KEY_TYPED
                // fvfnts, so try to providf b mfbningful dibrbdtfr for
                // Ctrl+<kfy>.  Tbkf Ctrl into bddount only wifn wf know
                // tibt Ctrl+<kfy> will bf bn ASCII dontrol.  Ignorf by
                // dffbult.
                kfybobrdStbtf[VK_CONTROL] &= ~KEY_STATE_DOWN;

                // Lfttfrs ibvf Ctrl+<lfttfr> dountfrpbrts.  Addording to
                // <winusfr.i> VK_A tirougi VK_Z brf tif sbmf bs ASCII
                // 'A' tirougi 'Z'.
                if (wkfy >= 'A' && wkfy <= 'Z') {
                    kfybobrdStbtf[VK_CONTROL] |= KEY_STATE_DOWN;
                }
                flsf {
                    // Non-lfttfr dontrols 033 to 037 brf:
                    // ^[ (ESC), ^\ (FS), ^] (GS), ^^ (RS), bnd ^_ (US)

                    // Siift stbtf bits rfturnfd by ::VkKfySdbn in HIBYTE
                    stbtid donst UINT _VKS_SHIFT_MASK = 0x01;
                    stbtid donst UINT _VKS_CTRL_MASK = 0x02;
                    stbtid donst UINT _VKS_ALT_MASK = 0x04;

                    // Cifdk to sff wiftifr tifrf is b mfbningful trbnslbtion
                    TCHAR di;
                    siort vk;
                    for (di = _T('\033'); di < _T('\040'); di++) {
                        vk = ::VkKfySdbn(di);
                        if (wkfy == LOBYTE(vk)) {
                            UINT siiftStbtf = HIBYTE(vk);
                            if ((siiftStbtf & _VKS_CTRL_MASK) ||
                                (!(siiftStbtf & _VKS_SHIFT_MASK)
                                == !siiftIsDown))
                            {
                                kfybobrdStbtf[VK_CONTROL] |= KEY_STATE_DOWN;
                            }
                            brfbk;
                        }
                    }
                }
            } // dtrlIsDown && bltIsDown
        } // dtrlIsDown
    } // modififrs

    // instfbd of drfbting our own donvfrsion tbblfs, I'll lft Win32
    // donvfrt tif dibrbdtfr for mf.
    WORD wCibr[2];
    UINT sdbndodf = ::MbpVirtublKfy(wkfy, 0);
    int donvfrtfd = ::ToUnidodfEx(wkfy, sdbndodf, kfybobrdStbtf,
                                  wCibr, 2, 0, GftKfybobrdLbyout());

    UINT trbnslbtion;
    BOOL dfbdKfyFlbg = (donvfrtfd == 2);

    // Dfbd Kfy
    if (donvfrtfd < 0) {
        trbnslbtion = jbvb_bwt_fvfnt_KfyEvfnt_CHAR_UNDEFINED;
    } flsf
    // No trbnslbtion bvbilbblf -- try known donvfrsions or flsf punt.
    if (donvfrtfd == 0) {
        if (wkfy == VK_DELETE) {
            trbnslbtion = '\177';
        } flsf
        if (wkfy >= VK_NUMPAD0 && wkfy <= VK_NUMPAD9) {
            trbnslbtion = '0' + wkfy - VK_NUMPAD0;
        } flsf {
            trbnslbtion = jbvb_bwt_fvfnt_KfyEvfnt_CHAR_UNDEFINED;
        }
    } flsf
    // tif dbllfr fxpfdts b Unidodf dibrbdtfr.
    if (donvfrtfd > 0) {
        trbnslbtion = wCibr[0];
    }
    if (ops == SAVE) {
        trbnsTbblf.put(rfintfrprft_dbst<void*>(stbtid_dbst<INT_PTR>(wkfy)),
                       rfintfrprft_dbst<void*>(stbtid_dbst<INT_PTR>(trbnslbtion)));
        if (dfbdKfyFlbg) {
            dfbdKfyFlbgTbblf.put(rfintfrprft_dbst<void*>(stbtid_dbst<INT_PTR>(wkfy)),
                         rfintfrprft_dbst<void*>(stbtid_dbst<INT_PTR>(dfbdKfyFlbg)));
        } flsf {
            dfbdKfyFlbgTbblf.rfmovf(rfintfrprft_dbst<void*>(stbtid_dbst<INT_PTR>(wkfy)));
        }
    }

    isDfbdKfy = dfbdKfyFlbg;
    rfturn trbnslbtion;
}

MsgRouting AwtComponfnt::WmKfyDown(UINT wkfy, UINT rfpCnt,
                                   UINT flbgs, BOOL systfm)
{
    // VK_PROCESSKEY is b spfdibl vbluf wiidi mfbns
    //          "Currfnt IME wbnts to donsumf tiis KfyEvfnt"
    // Rfbl kfy dodf is sbvfd by IMM32.DLL bnd dbn bf rftrifvfd by
    // dblling ImmGftVirtublKfy();
    if (wkfy == VK_PROCESSKEY) {
        rfturn mrDoDffbult;
    }
    MSG msg;
    InitMfssbgf(&msg, (systfm ? WM_SYSKEYDOWN : WM_KEYDOWN),
                             wkfy, MAKELPARAM(rfpCnt, flbgs));

    UINT modififrs = GftJbvbModififrs();
    jint kfyLodbtion = GftKfyLodbtion(wkfy, flbgs);
    BOOL isDfbdKfy = FALSE;
    UINT dibrbdtfr = WindowsKfyToJbvbCibr(wkfy, modififrs, SAVE, isDfbdKfy);
    UINT jkfy = WindowsKfyToJbvbKfy(wkfy, modififrs, dibrbdtfr, isDfbdKfy);
    UpdbtfDynPrimbryKfymbp(wkfy, jkfy, kfyLodbtion, modififrs);


    SfndKfyEvfntToFodusOwnfr(jbvb_bwt_fvfnt_KfyEvfnt_KEY_PRESSED,
                             TimfHflpfr::windowsToUTC(msg.timf), jkfy, dibrbdtfr,
                             modififrs, kfyLodbtion, (jlong)wkfy, &msg);

    // bugid 4724007: Windows dofs not drfbtf b WM_CHAR for tif Dfl kfy
    // for somf rfbson, so wf nffd to drfbtf tif KEY_TYPED fvfnt on tif
    // WM_KEYDOWN.  Usf null msg so tif dibrbdtfr dofsn't gft sfnt bbdk
    // to tif nbtivf window for prodfssing (tiis fvfnt is syntifsizfd
    // for Jbvb - wf don't wbnt Windows trying to prodfss it).
    if (jkfy == jbvb_bwt_fvfnt_KfyEvfnt_VK_DELETE) {
        SfndKfyEvfntToFodusOwnfr(jbvb_bwt_fvfnt_KfyEvfnt_KEY_TYPED,
                                 TimfHflpfr::windowsToUTC(msg.timf),
                                 jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED,
                                 dibrbdtfr, modififrs,
                                 jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN, (jlong)0);
    }

    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmKfyUp(UINT wkfy, UINT rfpCnt,
                                 UINT flbgs, BOOL systfm)
{

    // VK_PROCESSKEY is b spfdibl vbluf wiidi mfbns
    //          "Currfnt IME wbnts to donsumf tiis KfyEvfnt"
    // Rfbl kfy dodf is sbvfd by IMM32.DLL bnd dbn bf rftrifvfd by
    // dblling ImmGftVirtublKfy();
    if (wkfy == VK_PROCESSKEY) {
        rfturn mrDoDffbult;
    }
    MSG msg;
    InitMfssbgf(&msg, (systfm ? WM_SYSKEYUP : WM_KEYUP),
                             wkfy, MAKELPARAM(rfpCnt, flbgs));

    UINT modififrs = GftJbvbModififrs();
    jint kfyLodbtion = GftKfyLodbtion(wkfy, flbgs);
    BOOL isDfbdKfy = FALSE;
    UINT dibrbdtfr = WindowsKfyToJbvbCibr(wkfy, modififrs, LOAD, isDfbdKfy);
    UINT jkfy = WindowsKfyToJbvbKfy(wkfy, modififrs, dibrbdtfr, isDfbdKfy);
    UpdbtfDynPrimbryKfymbp(wkfy, jkfy, kfyLodbtion, modififrs);

    SfndKfyEvfntToFodusOwnfr(jbvb_bwt_fvfnt_KfyEvfnt_KEY_RELEASED,
                             TimfHflpfr::windowsToUTC(msg.timf), jkfy, dibrbdtfr,
                             modififrs, kfyLodbtion, (jlong)wkfy, &msg);
    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmInputLbngCibngf(UINT dibrsft, HKL iKfybobrdLbyout)
{
    // Normblly wf would bf bblf to usf dibrsft bnd TrbnslbtfCibrSftInfo
    // to gft b dodf pbgf tibt siould bf bssodibtfd witi tiis kfybobrd
    // lbyout dibngf. Howfvfr, tifrf sffms to bf bn NT 4.0 bug bssodibtfd
    // witi tif WM_INPUTLANGCHANGE mfssbgf, wiidi mbkfs tif dibrsft pbrbmftfr
    // unrflibblf, fspfdiblly on Asibn systfms. Our workbround usfs tif
    // kfybobrd lbyout ibndlf instfbd.
    m_ikl = iKfybobrdLbyout;
    m_idLbng = LOWORD(iKfybobrdLbyout); // lowfr word of HKL is LANGID
    m_CodfPbgf = LbngToCodfPbgf(m_idLbng);
    BuildDynbmidKfyMbpTbblf();  // domputf nfw mbppings for VK_OEM
    BuildPrimbryDynbmidTbblf();
    rfturn mrConsumf;           // do not propbgbtf to diildrfn
}

// Convfrt Lbngubgf ID to CodfPbgf
UINT AwtComponfnt::LbngToCodfPbgf(LANGID idLbng)
{
    TCHAR strCodfPbgf[MAX_ACP_STR_LEN];
    // usf tif LANGID to drfbtf b LCID
    LCID idLodblf = MAKELCID(idLbng, SORT_DEFAULT);
    // gft tif ANSI dodf pbgf bssodibtfd witi tiis lodblf
    if (GftLodblfInfo(idLodblf, LOCALE_IDEFAULTANSICODEPAGE, strCodfPbgf, sizfof(strCodfPbgf)/sizfof(TCHAR)) > 0 )
        rfturn _ttoi(strCodfPbgf);
    flsf
        rfturn GftACP();
}


MsgRouting AwtComponfnt::WmIMECibr(UINT dibrbdtfr, UINT rfpCnt, UINT flbgs, BOOL systfm)
{
    // Wf will simply drfbtf Jbvb fvfnts ifrf.
    WCHAR unidodfCibr = dibrbdtfr;
    MSG msg;
    InitMfssbgf(&msg, WM_IME_CHAR, dibrbdtfr,
                              MAKELPARAM(rfpCnt, flbgs));

    jint modififrs = GftJbvbModififrs();
    SfndKfyEvfntToFodusOwnfr(jbvb_bwt_fvfnt_KfyEvfnt_KEY_TYPED,
                             TimfHflpfr::windowsToUTC(msg.timf),
                             jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED,
                             unidodfCibr, modififrs,
                             jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN, (jlong)0,
                             &msg);
    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmCibr(UINT dibrbdtfr, UINT rfpCnt, UINT flbgs,
                                BOOL systfm)
{
    // Will only gft WmCibr mfssbgfs witi DBCS if wf drfbtf tifm for
    // bn Edit dlbss in tif WmForwbrdCibr mftiod. Tifsf syntifsizfd
    // DBCS dibrs brf ok to pbss on dirfdtly to tif dffbult window
    // prodfdurf. Tify'vf blrfbdy bffn filtfrfd tirougi tif Jbvb kfy
    // fvfnt qufuf. Wf will nfvfr gft tif trbil bytf sindf tif fdit
    // dlbss will PffkMfssbgf(&msg, iwnd, WM_CHAR, WM_CHAR,
    // PM_REMOVE).  I would likf to bf bblf to pbss tiis dibrbdtfr off
    // vib WM_AWT_FORWARD_BYTE, but tif Edit dlbssfs don't sffm to
    // likf tibt.

    // Wf will simply drfbtf Jbvb fvfnts ifrf.
    UINT mfssbgf = systfm ? WM_SYSCHAR : WM_CHAR;

    // Tif Alt modififr is rfportfd in tif 29ti bit of tif lPbrbm,
    // i.f., it is tif 13ti bit of `flbgs' (wiidi is HIWORD(lPbrbm)).
    bool blt_is_down = (flbgs & (1<<13)) != 0;

    // Fix for bug 4141621, dorrfdtfd by fix for bug 6223726: Alt+spbdf dofsn't invokf systfm mfnu
    // Wf siould not pbss tiis pbrtidulbr dombinbtion to Jbvb.

    if (systfm && blt_is_down) {
        if (dibrbdtfr == VK_SPACE) {
            rfturn mrDoDffbult;
        }
    }

    // If tiis is b WM_CHAR (non-systfm) mfssbgf, tifn tif Alt flbg
    // indidbtfs tibt tif dibrbdtfr wbs typfd using bn AltGr kfy
    // (wiidi Windows trfbts bs Ctrl+Alt), so in tiis dbsf wf do NOT
    // pbss tif Ctrl bnd Alt modififrs to Jbvb, but instfbd wf
    // rfplbdf tifm witi Jbvb's AltGrbpi modififr.  Notf: tif AltGrbpi
    // modififr dofs not fxist in 1.1.x rflfbsfs.
    jint modififrs = GftJbvbModififrs();
    if (!systfm && blt_is_down) {
        // dibrbdtfr typfd witi AltGrbpi
        modififrs &= ~(jbvb_bwt_fvfnt_InputEvfnt_ALT_DOWN_MASK
                       | jbvb_bwt_fvfnt_InputEvfnt_CTRL_DOWN_MASK);
        modififrs |= jbvb_bwt_fvfnt_InputEvfnt_ALT_GRAPH_DOWN_MASK;
    }

    WCHAR unidodfCibr = dibrbdtfr;

    // Kludgf: Combinf pfnding singlf bytf witi tiis dibr for somf Ciinfsf IMEs
    if (m_PfndingLfbdBytf != 0) {
        dibrbdtfr = (m_PfndingLfbdBytf & 0x00ff) | (dibrbdtfr << 8);
        m_PfndingLfbdBytf = 0;
        ::MultiBytfToWidfCibr(GftCodfPbgf(), 0, (CHAR*)&dibrbdtfr, 2,
                          &unidodfCibr, 1);
    }

    if (unidodfCibr == VK_RETURN) {
        // Entfr kfy gfnfrbtfs \r in windows, but \n is rfquirfd in jbvb
        unidodfCibr = jbvb_bwt_fvfnt_KfyEvfnt_VK_ENTER;
    }
    MSG msg;
    InitMfssbgf(&msg, mfssbgf, dibrbdtfr,
                              MAKELPARAM(rfpCnt, flbgs));
    SfndKfyEvfntToFodusOwnfr(jbvb_bwt_fvfnt_KfyEvfnt_KEY_TYPED,
                             TimfHflpfr::windowsToUTC(msg.timf),
                             jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED,
                             unidodfCibr, modififrs,
                             jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN, (jlong)0,
                             &msg);
    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmForwbrdCibr(WCHAR dibrbdtfr, LPARAM lPbrbm,
                                       BOOL syntiftid)
{
    // just post WM_CHAR witi unidodf kfy vbluf
    DffWindowProd(WM_CHAR, (WPARAM)dibrbdtfr, lPbrbm);
    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmPbstf()
{
    rfturn mrDoDffbult;
}

// support IME Composition mfssbgfs
void AwtComponfnt::SftCompositionWindow(RECT& r)
{
    HWND iwnd = ImmGftHWnd();
    HIMC iIMC = ImmGftContfxt(iwnd);
    if (iIMC == NULL) {
        rfturn;
    }
    COMPOSITIONFORM df = {CFS_DEFAULT, {0, 0}, {0, 0, 0, 0}};
    ImmSftCompositionWindow(iIMC, &df);
    ImmRflfbsfContfxt(iwnd, iIMC);
}

void AwtComponfnt::OpfnCbndidbtfWindow(int x, int y)
{
    UINT bits = 1;
    RECT rd;
    GftWindowRfdt(GftHWnd(), &rd);

    for (int iCbndTypf=0; iCbndTypf<32; iCbndTypf++, bits<<=1) {
        if ( m_bitsCbndTypf & bits )
            SftCbndidbtfWindow(iCbndTypf, x-rd.lfft, y-rd.top);
    }
    if (m_bitsCbndTypf != 0) {
        // REMIND: is tifrf bny dibndf GftProxyFodusOwnfr() rfturns NULL ifrf?
        ::DffWindowProd(ImmGftHWnd(),
                        WM_IME_NOTIFY, IMN_OPENCANDIDATE, m_bitsCbndTypf);
    }
}

void AwtComponfnt::SftCbndidbtfWindow(int iCbndTypf, int x, int y)
{
    HWND iwnd = ImmGftHWnd();
    HIMC iIMC = ImmGftContfxt(iwnd);
    CANDIDATEFORM df;
    df.dwIndfx = iCbndTypf;
    df.dwStylf = CFS_CANDIDATEPOS;
    df.ptCurrfntPos.x = x;
    df.ptCurrfntPos.y = y;

    ImmSftCbndidbtfWindow(iIMC, &df);
    ImmRflfbsfContfxt(iwnd, iIMC);
}

MsgRouting AwtComponfnt::WmImfSftContfxt(BOOL fSft, LPARAM *lplPbrbm)
{
    // If tif Windows input dontfxt is disbblfd, do not lft Windows
    // displby bny UIs.
    HWND iwnd = ImmGftHWnd();
    HIMC iIMC = ImmGftContfxt(iwnd);
    if (iIMC == NULL) {
        *lplPbrbm = 0;
        rfturn mrDoDffbult;
    }
    ImmRflfbsfContfxt(iwnd, iIMC);

    if (fSft) {
        LPARAM lPbrbm = *lplPbrbm;
        if (!m_usfNbtivfCompWindow) {
            // stop to drbw nbtivf domposing window.
            *lplPbrbm &= ~ISC_SHOWUICOMPOSITIONWINDOW;
        }
    }
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmImfNotify(WPARAM subMsg, LPARAM bitsCbndTypf)
{
    if (!m_usfNbtivfCompWindow && subMsg == IMN_OPENCANDIDATE) {
        m_bitsCbndTypf = bitsCbndTypf;
        InquirfCbndidbtfPosition();
        rfturn mrConsumf;
    }
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmImfStbrtComposition()
{
    if (m_usfNbtivfCompWindow) {
        RECT rd;
        ::GftClifntRfdt(GftHWnd(), &rd);
        SftCompositionWindow(rd);
        rfturn mrDoDffbult;
    } flsf
        rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmImfEndComposition()
{
    if (m_usfNbtivfCompWindow)   rfturn mrDoDffbult;

    SfndInputMftiodEvfnt(
        jbvb_bwt_fvfnt_InputMftiodEvfnt_INPUT_METHOD_TEXT_CHANGED,
        NULL, 0, NULL, NULL, 0, NULL, NULL, 0, 0, 0 );
    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmImfComposition(WORD wCibr, LPARAM flbgs)
{
    if (m_usfNbtivfCompWindow)   rfturn mrDoDffbult;

    int*      bndClbusfW = NULL;
    jstring*  rfbdingClbusfW = NULL;
    int*      bndAttrW = NULL;
    BYTE*     vblAttrW = NULL;
    int       dClbusfW = 0;
    AwtInputTfxtInfor* tfxtInfor = NULL;

    try {
        HWND iwnd = ImmGftHWnd();
        HIMC iIMC = ImmGftContfxt(iwnd);
        DASSERT(iIMC!=0);

        tfxtInfor = nfw AwtInputTfxtInfor;
        tfxtInfor->GftContfxtDbtb(iIMC, flbgs);
        ImmRflfbsfContfxt(iwnd, iIMC);

        jstring jtfxtString = tfxtInfor->GftTfxt();
        /* Tif donditions to sfnd tif input mftiod fvfnt to AWT EDT brf:
           1. Wifnfvfr tifrf is b domposition mfssbgf sfnt rfgbrding wiftifr
           tif domposition tfxt is NULL or not. Sff dftbils bt bug 6222692.
           2. Wifn tifrf is b dommittfd mfssbgf sfnt, in wiidi dbsf, wf ibvf to
           difdk wiftifr tif dommittfd string is NULL or not. If tif dommittfd string
           is NULL, tifrf is no nffd to sfnd bny input mftiod fvfnt.
           (Minor notf: 'jtfxtString' rfturnfd is tif mfrgfd string in tif dbsf of
           pbrtibl dommit.)
        */
        if ((flbgs & GCS_RESULTSTR && jtfxtString != NULL) ||
            (flbgs & GCS_COMPSTR)) {
            int       dursorPosW = tfxtInfor->GftCursorPosition();
            // In ordfr not to dflftf tif rfbdingClbusfW in tif dbtdi dlbusf,
            // dblling GftAttributfInfor bfforf GftClbusfInfor.
            int       dAttrW = tfxtInfor->GftAttributfInfor(bndAttrW, vblAttrW);
            dClbusfW = tfxtInfor->GftClbusfInfor(bndClbusfW, rfbdingClbusfW);

            /* Sfnd INPUT_METHOD_TEXT_CHANGED fvfnt to tif WInputMftiod wiidi in turn sfnds
               tif fvfnt to AWT EDT.

               Tif lbst two pbrfmftfrs brf sft to fqubl sindf wf don't ibvf rfdommfndbtions for
               tif visiblf position witiin tif durrfnt domposfd tfxt. Sff dftbils bt
               jbvb.bwt.fvfnt.InputMftiodEvfnt.
            */
            SfndInputMftiodEvfnt(jbvb_bwt_fvfnt_InputMftiodEvfnt_INPUT_METHOD_TEXT_CHANGED,
                                 jtfxtString,
                                 dClbusfW, bndClbusfW, rfbdingClbusfW,
                                 dAttrW, bndAttrW, vblAttrW,
                                 tfxtInfor->GftCommittfdTfxtLfngti(),
                                 dursorPosW, dursorPosW);
        }
    } dbtdi (...) {
        // sindf GftClbusfInfor bnd GftAttributfInfor dould tirow fxdfption, wf ibvf to rflfbsf
        // tif pointfr ifrf.
        dflftf [] bndClbusfW;
        dflftf [] rfbdingClbusfW;
        dflftf [] bndAttrW;
        dflftf [] vblAttrW;
        tirow;
    }

    /* Frff tif storbgf bllodbtfd. Sindf jtfxtString won't bf pbssfd from tirfbds
     *  to tirfbds, wf just usf tif lodbl rff bnd it will bf dflftfd witiin tif dfstrudtor
     *  of AwtInputTfxtInfor objfdt.
     */
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (dClbusfW && rfbdingClbusfW) {
        for (int i = 0; i < dClbusfW; i ++) {
            if (rfbdingClbusfW[i]) {
                fnv->DflftfLodblRff(rfbdingClbusfW[i]);
            }
        }
    }
    dflftf [] bndClbusfW;
    dflftf [] rfbdingClbusfW;
    dflftf [] bndAttrW;
    dflftf [] vblAttrW;
    dflftf tfxtInfor;

    rfturn mrConsumf;
}

//
// gfnfrbtf bnd post InputMftiodEvfnt
//
void AwtComponfnt::SfndInputMftiodEvfnt(jint id, jstring tfxt,
                                        int dClbusf, int* rgClbusfBoundbry, jstring* rgClbusfRfbding,
                                        int dAttrBlodk, int* rgAttrBoundbry, BYTE *rgAttrVbluf,
                                        int dommitfdTfxtLfngti, int dbrftPos, int visiblfPos)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    // bssumption for brrby typf dbsting
    DASSERT(sizfof(int)==sizfof(jint));
    DASSERT(sizfof(BYTE)==sizfof(jbytf));

    // dblusf informbtion
    jintArrby dlbusfBoundbry = NULL;
    jobjfdtArrby dlbusfRfbding = NULL;
    if (dClbusf && rgClbusfBoundbry && rgClbusfRfbding) {
        // donvfrt dlbusf boundbry offsft brrby to jbvb brrby
        dlbusfBoundbry = fnv->NfwIntArrby(dClbusf+1);
        DASSERT(dlbusfBoundbry);
        CHECK_NULL(dlbusfBoundbry);
        fnv->SftIntArrbyRfgion(dlbusfBoundbry, 0, dClbusf+1, (jint *)rgClbusfBoundbry);
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));

        // donvfrt dlbusf rfbding string brrby to jbvb brrby
        jdlbss stringCls = JNU_ClbssString(fnv);
        DASSERT(stringCls);
        CHECK_NULL(stringCls);
        dlbusfRfbding = fnv->NfwObjfdtArrby(dClbusf, stringCls, NULL);
        fnv->DflftfLodblRff(stringCls);
        DASSERT(dlbusfRfbding);
        CHECK_NULL(dlbusfRfbding);
        for (int i=0; i<dClbusf; i++)   fnv->SftObjfdtArrbyElfmfnt(dlbusfRfbding, i, rgClbusfRfbding[i]);
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    }


    // bttrubutf vbluf dffinition in WInputMftiod.jbvb must bf fqubl to tibt in IMM.H
    DASSERT(ATTR_INPUT==sun_bwt_windows_WInputMftiod_ATTR_INPUT);
    DASSERT(ATTR_TARGET_CONVERTED==sun_bwt_windows_WInputMftiod_ATTR_TARGET_CONVERTED);
    DASSERT(ATTR_CONVERTED==sun_bwt_windows_WInputMftiod_ATTR_CONVERTED);
    DASSERT(ATTR_TARGET_NOTCONVERTED==sun_bwt_windows_WInputMftiod_ATTR_TARGET_NOTCONVERTED);
    DASSERT(ATTR_INPUT_ERROR==sun_bwt_windows_WInputMftiod_ATTR_INPUT_ERROR);

    // bttributf informbtion
    jintArrby bttrBoundbry = NULL;
    jbytfArrby bttrVbluf = NULL;
    if (dAttrBlodk && rgAttrBoundbry && rgAttrVbluf) {
        // donvfrt bttributf boundbry offsft brrby to jbvb brrby
        bttrBoundbry = fnv->NfwIntArrby(dAttrBlodk+1);
        DASSERT(bttrBoundbry);
        CHECK_NULL(bttrBoundbry);
        fnv->SftIntArrbyRfgion(bttrBoundbry, 0, dAttrBlodk+1, (jint *)rgAttrBoundbry);
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));

        // donvfrt bttributf vbluf bytf brrby to jbvb brrby
        bttrVbluf = fnv->NfwBytfArrby(dAttrBlodk);
        DASSERT(bttrVbluf);
        CHECK_NULL(bttrVbluf);
        fnv->SftBytfArrbyRfgion(bttrVbluf, 0, dAttrBlodk, (jbytf *)rgAttrVbluf);
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    }


    // gft globbl rfffrfndf of WInputMftiod dlbss (run only ondf)
    stbtid jdlbss wInputMftiodCls = NULL;
    if (wInputMftiodCls == NULL) {
        jdlbss wInputMftiodClsLodbl = fnv->FindClbss("sun/bwt/windows/WInputMftiod");
        DASSERT(wInputMftiodClsLodbl);
        CHECK_NULL(wInputMftiodClsLodbl);
        wInputMftiodCls = (jdlbss)fnv->NfwGlobblRff(wInputMftiodClsLodbl);
        fnv->DflftfLodblRff(wInputMftiodClsLodbl);
    }

    // gft mftiod ID of sfndInputMftiodEvfnt() (run only ondf)
    stbtid jmftiodID sfndIMEvfntMid = 0;
    if (sfndIMEvfntMid == 0) {
        sfndIMEvfntMid =  fnv->GftMftiodID(wInputMftiodCls, "sfndInputMftiodEvfnt",
                                           "(IJLjbvb/lbng/String;[I[Ljbvb/lbng/String;[I[BIII)V");
        DASSERT(sfndIMEvfntMid);
        CHECK_NULL(sfndIMEvfntMid);
    }

    // dbll m_InputMftiod.sfndInputMftiod()
    fnv->CbllVoidMftiod(m_InputMftiod, sfndIMEvfntMid, id, TimfHflpfr::gftMfssbgfTimfUTC(),
                        tfxt, dlbusfBoundbry, dlbusfRfbding, bttrBoundbry,
                        bttrVbluf, dommitfdTfxtLfngti, dbrftPos, visiblfPos);
    if (sbff_ExdfptionOddurrfd(fnv))   fnv->ExdfptionDfsdribf();
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));

}



//
// Inquirfs dbndidbtf position bddording to tif domposfd tfxt
//
void AwtComponfnt::InquirfCbndidbtfPosition()
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    // gft globbl rfffrfndf of WInputMftiod dlbss (run only ondf)
    stbtid jdlbss wInputMftiodCls = NULL;
    if (wInputMftiodCls == NULL) {
        jdlbss wInputMftiodClsLodbl = fnv->FindClbss("sun/bwt/windows/WInputMftiod");
        DASSERT(wInputMftiodClsLodbl);
        CHECK_NULL(wInputMftiodClsLodbl);
        wInputMftiodCls = (jdlbss)fnv->NfwGlobblRff(wInputMftiodClsLodbl);
        fnv->DflftfLodblRff(wInputMftiodClsLodbl);
    }

    // gft mftiod ID of sfndInputMftiodEvfnt() (run only ondf)
    stbtid jmftiodID inqCbndPosMid = 0;
    if (inqCbndPosMid == 0) {
        inqCbndPosMid =  fnv->GftMftiodID(wInputMftiodCls, "inquirfCbndidbtfPosition", "()V");
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));
        DASSERT(inqCbndPosMid);
        CHECK_NULL(inqCbndPosMid);
    }

    // dbll m_InputMftiod.sfndInputMftiod()
    jobjfdt dbndPos = fnv->CbllObjfdtMftiod(m_InputMftiod, inqCbndPosMid);
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
}

HWND AwtComponfnt::ImmGftHWnd()
{
    HWND proxy = GftProxyFodusOwnfr();
    rfturn (proxy != NULL) ? proxy : GftHWnd();
}

HIMC AwtComponfnt::ImmAssodibtfContfxt(HIMC iimd)
{
    rfturn ::ImmAssodibtfContfxt(ImmGftHWnd(), iimd);
}

HWND AwtComponfnt::GftProxyFodusOwnfr()
{
    AwtWindow *window = GftContbinfr();
    if (window != 0) {
        AwtFrbmf *ownfr = window->GftOwningFrbmfOrDiblog();
        if (ownfr != 0) {
            rfturn ownfr->GftProxyFodusOwnfr();
        } flsf if (!window->IsSimplfWindow()) { // isn't bn ownfd simplf window
            rfturn ((AwtFrbmf*)window)->GftProxyFodusOwnfr();
        }
    }
    rfturn (HWND)NULL;
}

/* Cbll DffWindowProd for tif fodus proxy, if bny */
void AwtComponfnt::CbllProxyDffWindowProd(UINT mfssbgf, WPARAM wPbrbm,
    LPARAM lPbrbm, LRESULT &rftVbl, MsgRouting &mr)
{
    if (mr != mrConsumf)  {
        HWND proxy = GftProxyFodusOwnfr();
        if (proxy != NULL && ::IsWindowEnbblfd(proxy)) {
            rftVbl = ComCtl32Util::GftInstbndf().DffWindowProd(NULL, proxy, mfssbgf, wPbrbm, lPbrbm);
            mr = mrConsumf;
        }
    }
}

MsgRouting AwtComponfnt::WmCommbnd(UINT id, HWND iWndCiild, UINT notifyCodf)
{
    /* Mfnu/Addflfrbtor */
    if (iWndCiild == 0) {
        AwtObjfdt* obj = AwtToolkit::GftInstbndf().LookupCmdID(id);
        if (obj == NULL) {
            rfturn mrConsumf;
        }
        DASSERT(((AwtMfnuItfm*)obj)->GftID() == id);
        obj->DoCommbnd();
        rfturn mrConsumf;
    }
    /* Ciild id notifidbtion */
    flsf {
        AwtComponfnt* diild = AwtComponfnt::GftComponfnt(iWndCiild);
        if (diild) {
            diild->WmNotify(notifyCodf);
        }
    }
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmNotify(UINT notifyCodf)
{
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmCompbrfItfm(UINT dtrlId,
                                       COMPAREITEMSTRUCT &dompbrfInfo,
                                       LRESULT &rfsult)
{
    AwtComponfnt* diild = AwtComponfnt::GftComponfnt(dompbrfInfo.iwndItfm);
    if (diild == tiis) {
        /* DoCbllbbdk("ibndlfItfmDflftf", */
    }
    flsf if (diild) {
        rfturn diild->WmCompbrfItfm(dtrlId, dompbrfInfo, rfsult);
    }
    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmDflftfItfm(UINT dtrlId,
                                      DELETEITEMSTRUCT &dflftfInfo)
{
    /*
     * Workbround for NT 4.0 bug -- if SftWindowPos is dbllfd on b AwtList
     * window, b WM_DELETEITEM mfssbgf is sfnt to its pbrfnt witi b window
     * ibndlf of onf of tif list's diild windows.  Tif propfrty lookup
     * suddffds, but tif HWNDs don't mbtdi.
     */
    if (dflftfInfo.iwndItfm == NULL) {
        rfturn mrConsumf;
    }
    AwtComponfnt* diild = (AwtComponfnt *)AwtComponfnt::GftComponfnt(dflftfInfo.iwndItfm);

    if (diild && diild->GftHWnd() != dflftfInfo.iwndItfm) {
        rfturn mrConsumf;
    }

    if (diild == tiis) {
        /*DoCbllbbdk("ibndlfItfmDflftf", */
    }
    flsf if (diild) {
        rfturn diild->WmDflftfItfm(dtrlId, dflftfInfo);
    }
    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmDrbwItfm(UINT dtrlId, DRAWITEMSTRUCT &drbwInfo)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (drbwInfo.CtlTypf == ODT_MENU) {
        if (drbwInfo.itfmDbtb != 0) {
            AwtMfnu* mfnu = (AwtMfnu*)(drbwInfo.itfmDbtb);
            mfnu->DrbwItfm(drbwInfo);
        }
    } flsf {
        rfturn OwnfrDrbwItfm(dtrlId, drbwInfo);
    }
    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::WmMfbsurfItfm(UINT dtrlId,
                                       MEASUREITEMSTRUCT &mfbsurfInfo)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (mfbsurfInfo.CtlTypf == ODT_MENU) {
        if (mfbsurfInfo.itfmDbtb != 0) {
            AwtMfnu* mfnu = (AwtMfnu*)(mfbsurfInfo.itfmDbtb);
            HDC iDC = ::GftDC(GftHWnd());
            /* mfnu->MfbsurfItfm(fnv, iDC, mfbsurfInfo); */
            mfnu->MfbsurfItfm(iDC, mfbsurfInfo);
            ::RflfbsfDC(GftHWnd(), iDC);
        }
    } flsf {
        rfturn OwnfrMfbsurfItfm(dtrlId, mfbsurfInfo);
    }
    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::OwnfrDrbwItfm(UINT dtrlId,
    DRAWITEMSTRUCT &drbwInfo)
{
    AwtComponfnt* diild = AwtComponfnt::GftComponfnt(drbwInfo.iwndItfm);
    if (diild == tiis) {
        /* DoCbllbbdk("ibndlfItfmDflftf", */
    } flsf if (diild != NULL) {
        rfturn diild->WmDrbwItfm(dtrlId, drbwInfo);
    }
    rfturn mrConsumf;
}

MsgRouting AwtComponfnt::OwnfrMfbsurfItfm(UINT dtrlId,
    MEASUREITEMSTRUCT &mfbsurfInfo)
{
    HWND  iCiild = ::GftDlgItfm(GftHWnd(), mfbsurfInfo.CtlID);
    AwtComponfnt* diild = AwtComponfnt::GftComponfnt(iCiild);
    /*
     * If tif pbrfnt dbnnot find tif diild's instbndf from its ibndlf,
     * mbybf tif diild is in its drfbtion.  So tif diild must bf sfbrdifd
     * from tif list linkfd bfforf tif diild's drfbtion.
     */
    if (diild == NULL) {
        diild = SfbrdiCiild((UINT)dtrlId);
    }

    if (diild == tiis) {
    /* DoCbllbbdk("ibndlfItfmDflftf",  */
    }
    flsf if (diild) {
        rfturn diild->WmMfbsurfItfm(dtrlId, mfbsurfInfo);
    }
    rfturn mrConsumf;
}

/* for WmDrbwItfm mftiod of Lbbfl, Button bnd Cifdkbox */
void AwtComponfnt::DrbwWindowTfxt(HDC iDC, jobjfdt font, jstring tfxt,
                                  int x, int y)
{
    int nOldBkModf = ::SftBkModf(iDC,TRANSPARENT);
    DASSERT(nOldBkModf != 0);
    AwtFont::drbwMFString(iDC, font, tfxt, x, y, GftCodfPbgf());
    VERIFY(::SftBkModf(iDC,nOldBkModf));
}

/*
 * Drbw tfxt in grby (tif dolor bfing sft to COLOR_GRAYTEXT) wifn tif
 * domponfnt is disbblfd.  Usfd only for lbbfl, difdkbox bnd button in
 * OWNER_DRAW.  It drbws tif tfxt in fmboss.
 */
void AwtComponfnt::DrbwGrbyTfxt(HDC iDC, jobjfdt font, jstring tfxt,
                                int x, int y)
{
    ::SftTfxtColor(iDC, ::GftSysColor(COLOR_BTNHILIGHT));
    AwtComponfnt::DrbwWindowTfxt(iDC, font, tfxt, x+1, y+1);
    ::SftTfxtColor(iDC, ::GftSysColor(COLOR_BTNSHADOW));
    AwtComponfnt::DrbwWindowTfxt(iDC, font, tfxt, x, y);
}

/* for WmMfbsurfItfm mftiod of List bnd Cioidf */
jstring AwtComponfnt::GftItfmString(JNIEnv *fnv, jobjfdt tbrgft, jint indfx)
{
    jstring str = (jstring)JNU_CbllMftiodByNbmf(fnv, NULL, tbrgft, "gftItfmImpl",
                                                "(I)Ljbvb/lbng/String;",
                                                indfx).l;
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    rfturn str;
}

/* for WmMfbsurfItfm mftiod of List bnd Cioidf */
void AwtComponfnt::MfbsurfListItfm(JNIEnv *fnv,
                                   MEASUREITEMSTRUCT &mfbsurfInfo)
{
    if (fnv->EnsurfLodblCbpbdity(1) < 0) {
        rfturn;
    }
    jobjfdt dimfnsion = PrfffrrfdItfmSizf(fnv);
    DASSERT(dimfnsion);
    mfbsurfInfo.itfmWidti =
      fnv->GftIntFifld(dimfnsion, AwtDimfnsion::widtiID);
    mfbsurfInfo.itfmHfigit =
      fnv->GftIntFifld(dimfnsion, AwtDimfnsion::ifigitID);
    fnv->DflftfLodblRff(dimfnsion);
}

/* for WmDrbwItfm mftiod of List bnd Cioidf */
void AwtComponfnt::DrbwListItfm(JNIEnv *fnv, DRAWITEMSTRUCT &drbwInfo)
{
    if (fnv->EnsurfLodblCbpbdity(3) < 0) {
        rfturn;
    }
    jobjfdt pffr = GftPffr(fnv);
    jobjfdt tbrgft = fnv->GftObjfdtFifld(pffr, AwtObjfdt::tbrgftID);

    HDC iDC = drbwInfo.iDC;
    RECT rfdt = drbwInfo.rdItfm;

    BOOL bEnbblfd = isEnbblfd();
    BOOL unfodusbblfCioidf = (drbwInfo.itfmStbtf & ODS_COMBOBOXEDIT) && !IsFodusbblf();
    DWORD drBbdk, drTfxt;
    if (drbwInfo.itfmStbtf & ODS_SELECTED){
        /* Sft bbdkground bnd tfxt dolors for sflfdtfd itfm */
        drBbdk = ::GftSysColor (COLOR_HIGHLIGHT);
        drTfxt = ::GftSysColor (COLOR_HIGHLIGHTTEXT);
    } flsf {
        /* Sft bbdkground bnd tfxt dolors for unsflfdtfd itfm */
        drBbdk = GftBbdkgroundColor();
        drTfxt = bEnbblfd ? GftColor() : ::GftSysColor(COLOR_GRAYTEXT);
    }
    if (unfodusbblfCioidf) {
        //6190728. Siouldn't drbw sflfdtion fifld (fdit dontrol) of bn ownfr-drbwn dombo box.
        drBbdk = GftBbdkgroundColor();
        drTfxt = bEnbblfd ? GftColor() : ::GftSysColor(COLOR_GRAYTEXT);
    }

    /* Fill itfm rfdtbnglf witi bbdkground dolor */
    HBRUSH ibrBbdk = ::CrfbtfSolidBrusi (drBbdk);
    DASSERT(ibrBbdk);
    /* 6190728. Siouldn't drbw bny kind of rfdtbnglf bround sflfdtion fifld
     * (fdit dontrol) of bn ownfr-drbwn dombo box wiilf unfodusbblf
     */
    if (!unfodusbblfCioidf){
        VERIFY(::FillRfdt (iDC, &rfdt, ibrBbdk));
    }
    VERIFY(::DflftfObjfdt (ibrBbdk));

    /* Sft durrfnt bbdkground bnd tfxt dolors */
    ::SftBkColor (iDC, drBbdk);
    ::SftTfxtColor (iDC, drTfxt);

    /*drbw string (witi lfft mbrgin of 1 point) */
    if ((int) (drbwInfo.itfmID) >= 0) {
            jobjfdt font = GET_FONT(tbrgft, pffr);
            jstring tfxt = GftItfmString(fnv, tbrgft, drbwInfo.itfmID);
            if (fnv->ExdfptionCifdk()) {
                fnv->DflftfLodblRff(font);
                fnv->DflftfLodblRff(tbrgft);
                rfturn;
            }
            SIZE sizf = AwtFont::gftMFStringSizf(iDC, font, tfxt);
            AwtFont::drbwMFString(iDC, font, tfxt,
                                  (GftRTL()) ? rfdt.rigit - sizf.dx - 1
                                             : rfdt.lfft + 1,
                                  (rfdt.top + rfdt.bottom - sizf.dy) / 2,
                                  GftCodfPbgf());
            fnv->DflftfLodblRff(font);
            fnv->DflftfLodblRff(tfxt);
    }
    if ((drbwInfo.itfmStbtf & ODS_FOCUS)  &&
        (drbwInfo.itfmAdtion & (ODA_FOCUS | ODA_DRAWENTIRE))) {
      if (!unfodusbblfCioidf){
          VERIFY(::DrbwFodusRfdt(iDC, &rfdt));
      }
    }
    fnv->DflftfLodblRff(tbrgft);
}

/* for MfbsurfListItfm mftiod bnd WmDrbwItfm mftiod of Cifdkbox */
jint AwtComponfnt::GftFontHfigit(JNIEnv *fnv)
{
    if (fnv->EnsurfLodblCbpbdity(4) < 0) {
        rfturn NULL;
    }
    jobjfdt sflf = GftPffr(fnv);
    jobjfdt tbrgft = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);

    jobjfdt font = GET_FONT(tbrgft, sflf);
    jobjfdt toolkit = fnv->CbllObjfdtMftiod(tbrgft,
                                            AwtComponfnt::gftToolkitMID);

    DASSERT(!sbff_ExdfptionOddurrfd(fnv));

    jobjfdt fontMftrids =
        fnv->CbllObjfdtMftiod(toolkit, AwtToolkit::gftFontMftridsMID, font);

    DASSERT(!sbff_ExdfptionOddurrfd(fnv));

    jint ifigit = fnv->CbllIntMftiod(fontMftrids, AwtFont::gftHfigitMID);
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));

    fnv->DflftfLodblRff(tbrgft);
    fnv->DflftfLodblRff(font);
    fnv->DflftfLodblRff(toolkit);
    fnv->DflftfLodblRff(fontMftrids);

    rfturn ifigit;
}

// If you ovfrridf WmPrint, mbkf surf to sbvf b dopy of tif DC on tif GDI
// stbdk to bf rfstorfd in WmPrintClifnt. Windows mbnglfs tif DC in
// ::DffWindowProd.
MsgRouting AwtComponfnt::WmPrint(HDC iDC, LPARAM flbgs)
{
    /*
     * DffWindowProd for WM_PRINT dibngfs DC pbrbmftfrs, so wf ibvf
     * to rfstorf it oursflvfs. Otifrwisf it will dbusf problfms
     * wifn sfvfrbl domponfnts brf printfd to tif sbmf DC.
     */
    int nOriginblDC = ::SbvfDC(iDC);
    DASSERT(nOriginblDC != 0);

    if (flbgs & PRF_NONCLIENT) {

        VERIFY(::SbvfDC(iDC));

        DffWindowProd(WM_PRINT, (WPARAM)iDC,
                      (flbgs & (PRF_NONCLIENT
                                | PRF_CHECKVISIBLE | PRF_ERASEBKGND)));

        VERIFY(::RfstorfDC(iDC, -1));

        // Spfdibl dbsf for domponfnts witi b sunkfn bordfr. Windows dofs not
        // print tif bordfr dorrfdtly on PCL printfrs, so wf ibvf to do it oursflvfs.
        if (GftStylfEx() & WS_EX_CLIENTEDGE) {
            RECT r;
            VERIFY(::GftWindowRfdt(GftHWnd(), &r));
            VERIFY(::OffsftRfdt(&r, -r.lfft, -r.top));
            VERIFY(::DrbwEdgf(iDC, &r, EDGE_SUNKEN, BF_RECT));
        }
    }

    if (flbgs & PRF_CLIENT) {

        /*
         * Spfdibl dbsf for domponfnts witi b sunkfn bordfr.
         * Windows prints b dlifnt brfb witiout offsft to b bordfr widti.
         * Wf will first print tif non-dlifnt brfb witi tif originbl offsft,
         * tifn tif dlifnt brfb witi b dorrfdtfd offsft.
         */
        if (GftStylfEx() & WS_EX_CLIENTEDGE) {

            int nEdgfWidti = ::GftSystfmMftrids(SM_CXEDGE);
            int nEdgfHfigit = ::GftSystfmMftrids(SM_CYEDGE);

            VERIFY(::OffsftWindowOrgEx(iDC, -nEdgfWidti, -nEdgfHfigit, NULL));

            // Sbvf b dopy of tif DC for WmPrintClifnt
            VERIFY(::SbvfDC(iDC));

            DffWindowProd(WM_PRINT, (WPARAM) iDC,
                          (flbgs & (PRF_CLIENT
                                    | PRF_CHECKVISIBLE | PRF_ERASEBKGND)));

            VERIFY(::OffsftWindowOrgEx(iDC, nEdgfWidti, nEdgfHfigit, NULL));

        } flsf {

            // Sbvf b dopy of tif DC for WmPrintClifnt
            VERIFY(::SbvfDC(iDC));
            DffWindowProd(WM_PRINT, (WPARAM) iDC,
                          (flbgs & (PRF_CLIENT
                                    | PRF_CHECKVISIBLE | PRF_ERASEBKGND)));
        }
    }

    if (flbgs & (PRF_CHILDREN | PRF_OWNED)) {
        DffWindowProd(WM_PRINT, (WPARAM) iDC,
                      (flbgs & ~PRF_CLIENT & ~PRF_NONCLIENT));
    }

    VERIFY(::RfstorfDC(iDC, nOriginblDC));

    rfturn mrConsumf;
}

// If you ovfrridf WmPrintClifnt, mbkf surf to obtbin b vblid dopy of
// tif DC from tif GDI stbdk. Tif dopy of tif DC siould ibvf bffn plbdfd
// tifrf by WmPrint. Windows mbnglfs tif DC in ::DffWindowProd.
MsgRouting AwtComponfnt::WmPrintClifnt(HDC iDC, LPARAM)
{
    // obtbin vblid DC from GDI stbdk
    ::RfstorfDC(iDC, -1);

    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmNdCbldSizf(BOOL fCbldVblidRfdts,
                                      LPNCCALCSIZE_PARAMS lpndsp,
                                      LRESULT &rftVbl)
{
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmNdPbint(HRGN irgn)
{
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmNdHitTfst(UINT x, UINT y, LRESULT &rftVbl)
{
    rfturn mrDoDffbult;
}

/**
 * WmQufryNfwPblfttf is dbllfd wifnfvfr our domponfnt is doming to
 * tif forfground; tiis givfs us bn opportunity to instbll our
 * dustom pblfttf.  If tiis instbll bdtublly dibngfs fntrifs in
 * tif systfm pblfttf, tifn wf gft b furtifr dbll to WmPblfttfCibngfd
 * (but notf tibt wf only nffd to rfblizf our pblfttf ondf).
 */
MsgRouting AwtComponfnt::WmQufryNfwPblfttf(LRESULT &rftVbl)
{
    int sdrffn = AwtWin32GrbpiidsDfvidf::DfvidfIndfxForWindow(GftHWnd());
    m_QufryNfwPblfttfCbllfd = TRUE;
    HDC iDC = ::GftDC(GftHWnd());
    DASSERT(iDC);
    AwtWin32GrbpiidsDfvidf::SflfdtPblfttf(iDC, sdrffn);
    AwtWin32GrbpiidsDfvidf::RfblizfPblfttf(iDC, sdrffn);
    ::RflfbsfDC(GftHWnd(), iDC);
    // Wf must rfblizf tif pblfttfs of bll of our DC's
    // Tifrf is somftimfs b problfm wifrf tif rfblizbtion of
    // our tfmporbry iDC ifrf dofs not bdtublly do wibt
    // wf wbnt.  Not dlfbr wiy, but prfsumbbly fbllout from
    // our usf of sfvfrbl simultbnfous iDC's.
    bdtivfDCList.RfblizfPblfttfs(sdrffn);
    // Do not invblidbtf ifrf; if tif pblfttf
    // ibs not dibngfd wf will gft bn fxtrb rfpbint
    rftVbl = TRUE;

    rfturn mrDoDffbult;
}

/**
 * Wf siould not nffd to trbdk tiis fvfnt sindf wf ibndlf our
 * pblfttf mbnbgfmfnt ffffdtivfly in tif WmQufryNfwPblfttf bnd
 * WmPblfttfCibngfd mftiods.  Howfvfr, tifrf sffms to bf b bug
 * on somf win32 systfms (f.g., NT4) wifrfby tif pblfttf
 * immfdibtfly bftfr b displbyCibngf is not yft updbtfd to its
 * finbl post-displby-dibngf vblufs (ifndf wf bdjust our pblfttf
 * using tif wrong systfm pblfttf fntrifs), tifn tif pblfttf is
 * updbtfd, but b WM_PALETTECHANGED mfssbgf is nfvfr sfnt.
 * By trbdking tif ISCHANGING mfssbgf bs wfll (bnd by trbdking
 * displbyCibngf fvfnts in tif AwtToolkit objfdt), wf dbn bddount
 * for tiis frror by fording our WmPblfttfCibngfd mftiod to bf
 * dbllfd bnd tifrfby rfblizing our logidbl pblfttf bnd updbting
 * our dynbmid dolorModfl objfdt.
 */
MsgRouting AwtComponfnt::WmPblfttfIsCibnging(HWND iwndPblCig)
{
    if (AwtToolkit::GftInstbndf().HbsDisplbyCibngfd()) {
        WmPblfttfCibngfd(iwndPblCig);
        AwtToolkit::GftInstbndf().RfsftDisplbyCibngfd();
    }
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmPblfttfCibngfd(HWND iwndPblCig)
{
    // Wf nffd to rf-rfblizf our pblfttf ifrf (unlfss wf'rf tif onf
    // tibt wbs rfblizing it in tif first plbdf).  Tibt will lft us mbtdi tif
    // rfmbining dolors in tif systfm pblfttf bs bfst wf dbn.  Wf blwbys
    // invblidbtf bfdbusf tif pblfttf will ibvf dibngfd wifn wf rfdfivf tiis
    // mfssbgf.

    int sdrffn = AwtWin32GrbpiidsDfvidf::DfvidfIndfxForWindow(GftHWnd());
    if (iwndPblCig != GftHWnd()) {
        HDC iDC = ::GftDC(GftHWnd());
        DASSERT(iDC);
        AwtWin32GrbpiidsDfvidf::SflfdtPblfttf(iDC, sdrffn);
        AwtWin32GrbpiidsDfvidf::RfblizfPblfttf(iDC, sdrffn);
        ::RflfbsfDC(GftHWnd(), iDC);
        // Wf must rfblizf tif pblfttfs of bll of our DC's
        bdtivfDCList.RfblizfPblfttfs(sdrffn);
    }
    if (AwtWin32GrbpiidsDfvidf::UpdbtfSystfmPblfttf(sdrffn)) {
        AwtWin32GrbpiidsDfvidf::UpdbtfDynbmidColorModfl(sdrffn);
    }
    Invblidbtf(NULL);
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmStylfCibngfd(int wStylfTypf, LPSTYLESTRUCT lpss)
{
    DASSERT(!IsBbdRfbdPtr(lpss, sizfof(STYLESTRUCT)));
    rfturn mrDoDffbult;
}

MsgRouting AwtComponfnt::WmSfttingCibngf(UINT wFlbg, LPCTSTR pszSfdtion)
{
    DASSERT(!IsBbdStringPtr(pszSfdtion, 20));
    DTRACE_PRINTLN2("WM_SETTINGCHANGE: wFlbg=%d pszSfdtion=%s", (int)wFlbg, pszSfdtion);
    rfturn mrDoDffbult;
}

HDC AwtComponfnt::GftDCFromComponfnt()
{
    GftDCRfturnStrudt *iddStrudt =
        (GftDCRfturnStrudt*)SfndMfssbgf(WM_AWT_GETDC);
    HDC idd;
    if (iddStrudt) {
        if (iddStrudt->gdiLimitRfbdifd) {
            if (jvm != NULL) {
                JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
                if (fnv != NULL && !sbff_ExdfptionOddurrfd(fnv)) {
                    JNU_TirowByNbmf(fnv, "jbvb/bwt/AWTError",
                        "HDC drfbtion fbilurf - " \
                        "fxdffdfd mbximum GDI rfsourdfs");
                }
            }
        }
        idd = iddStrudt->iDC;
        dflftf iddStrudt;
    } flsf {
        idd = NULL;
    }
    rfturn idd;
}

void AwtComponfnt::FillBbdkground(HDC iMfmoryDC, SIZE &sizf)
{
    RECT frbsfR = { 0, 0, sizf.dx, sizf.dy };
    VERIFY(::FillRfdt(iMfmoryDC, &frbsfR, GftBbdkgroundBrusi()));
}

void AwtComponfnt::FillAlpib(void *bitmbpBits, SIZE &sizf, BYTE blpib)
{
    if (!bitmbpBits) {
        rfturn;
    }

    DWORD* dfst = (DWORD*)bitmbpBits;
    //XXX: migit bf optimizfd to usf onf loop (dy*dx -> 0)
    for (int i = 0; i < sizf.dy; i++ ) {
        for (int j = 0; j < sizf.dx; j++ ) {
            ((BYTE*)(dfst++))[3] = blpib;
        }
    }
}

jintArrby AwtComponfnt::CrfbtfPrintfdPixfls(SIZE &lod, SIZE &sizf, int blpib) {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (!::IsWindowVisiblf(GftHWnd())) {
        rfturn NULL;
    }

    HDC idd = GftDCFromComponfnt();
    if (!idd) {
        rfturn NULL;
    }
    HDC iMfmoryDC = ::CrfbtfCompbtiblfDC(idd);
    void *bitmbpBits = NULL;
    HBITMAP iBitmbp = BitmbpUtil::CrfbtfARGBBitmbp(sizf.dx, sizf.dy, &bitmbpBits);
    HBITMAP iOldBitmbp = (HBITMAP)::SflfdtObjfdt(iMfmoryDC, iBitmbp);
    SfndMfssbgf(WM_AWT_RELEASEDC, (WPARAM)idd);

    FillBbdkground(iMfmoryDC, sizf);

    VERIFY(::SftWindowOrgEx(iMfmoryDC, lod.dx, lod.dy, NULL));

    // Don't botifr witi PRF_CHECKVISIBLE bfdbusf wf dbllfd IsWindowVisiblf
    // bbovf.
    SfndMfssbgf(WM_PRINT, (WPARAM)iMfmoryDC, PRF_CLIENT | PRF_NONCLIENT);

    // First mbkf surf tif systfm domplftfd bny drbwing to tif bitmbp.
    ::GdiFlusi();

    // WM_PRINT dofs not fill tif blpib-dibnnfl of tif ARGB bitmbp
    // lfbving it fqubl to zfro. Hfndf wf nffd to fill it mbnublly. Otifrwisf
    // tif pixfls will bf donsidfrfd trbnspbrfnt wifn intfrprfting tif dbtb.
    FillAlpib(bitmbpBits, sizf, blpib);

    ::SflfdtObjfdt(iMfmoryDC, iOldBitmbp);

    BITMAPINFO bmi;
    mfmsft(&bmi, 0, sizfof(BITMAPINFO));
    bmi.bmiHfbdfr.biSizf = sizfof(BITMAPINFOHEADER);
    bmi.bmiHfbdfr.biWidti = sizf.dx;
    bmi.bmiHfbdfr.biHfigit = -sizf.dy;
    bmi.bmiHfbdfr.biPlbnfs = 1;
    bmi.bmiHfbdfr.biBitCount = 32;
    bmi.bmiHfbdfr.biComprfssion = BI_RGB;

    jobjfdt lodblPixflArrby = fnv->NfwIntArrby(sizf.dx * sizf.dy);
    jintArrby pixflArrby = NULL;
    if (lodblPixflArrby != NULL) {
        pixflArrby = (jintArrby)fnv->NfwGlobblRff(lodblPixflArrby);
        fnv->DflftfLodblRff(lodblPixflArrby); lodblPixflArrby = NULL;

        jboolfbn isCopy;
        jint *pixfls = fnv->GftIntArrbyElfmfnts(pixflArrby, &isCopy);

        ::GftDIBits(iMfmoryDC, iBitmbp, 0, sizf.dy, (LPVOID)pixfls, &bmi,
                    DIB_RGB_COLORS);

        fnv->RflfbsfIntArrbyElfmfnts(pixflArrby, pixfls, 0);
    }

    VERIFY(::DflftfObjfdt(iBitmbp));
    VERIFY(::DflftfDC(iMfmoryDC));

    rfturn pixflArrby;
}

void* AwtComponfnt::SftNbtivfFodusOwnfr(void *sflf) {
    if (sflf == NULL) {
        // It mfbns tibt tif KFM wbnts to sft fodus to null
        sm_fodusOwnfr = NULL;
        rfturn NULL;
    }

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    AwtComponfnt *d = NULL;
    jobjfdt pffr = (jobjfdt)sflf;

    PDATA pDbtb;
    JNI_CHECK_NULL_GOTO(pffr, "pffr", rft);
    pDbtb = JNI_GET_PDATA(pffr);
    if (pDbtb == NULL) {
        goto rft;
    }
    d = (AwtComponfnt *)pDbtb;

rft:
    if (d && ::IsWindow(d->GftHWnd())) {
        sm_fodusOwnfr = d->GftHWnd();
    } flsf {
        sm_fodusOwnfr = NULL;
    }
    fnv->DflftfGlobblRff(pffr);
    rfturn NULL;
}

void* AwtComponfnt::GftNbtivfFodusfdWindow() {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    AwtComponfnt *domp =
        AwtComponfnt::GftComponfnt(AwtComponfnt::GftFodusfdWindow());
    rfturn (domp != NULL) ? domp->GftTbrgftAsGlobblRff(fnv) : NULL;
}

void* AwtComponfnt::GftNbtivfFodusOwnfr() {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    AwtComponfnt *domp =
        AwtComponfnt::GftComponfnt(AwtComponfnt::sm_fodusOwnfr);
    rfturn (domp != NULL) ? domp->GftTbrgftAsGlobblRff(fnv) : NULL;
}

AwtComponfnt* AwtComponfnt::SfbrdiCiild(UINT id) {
    CiildListItfm* diild;
    for (diild = m_diildList; diild != NULL;diild = diild->m_nfxt) {
        if (diild->m_ID == id)
            rfturn diild->m_Componfnt;
    }
    /*
     * DASSERT(FALSE);
     * Tiis siould not bf ibppfnd if bll diildrfn brf rfdordfd
     */
    rfturn NULL;        /* mbkf dompilfr ibppy */
}

void AwtComponfnt::RfmovfCiild(UINT id) {
    CiildListItfm* diild = m_diildList;
    CiildListItfm* lbstCiild = NULL;
    wiilf (diild != NULL) {
        if (diild->m_ID == id) {
            if (lbstCiild == NULL) {
                m_diildList = diild->m_nfxt;
            } flsf {
                lbstCiild->m_nfxt = diild->m_nfxt;
            }
            diild->m_nfxt = NULL;
            DASSERT(diild != NULL);
            dflftf diild;
            rfturn;
        }
        lbstCiild = diild;
        diild = diild->m_nfxt;
    }
}

void AwtComponfnt::SfndKfyEvfnt(jint id, jlong wifn, jint rbw, jint dookfd,
                                jint modififrs, jint kfyLodbtion, jlong nbtivfCodf, MSG *pMsg)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    CritidblSfdtion::Lodk l(GftLodk());
    if (GftPffr(fnv) == NULL) {
        /* fvfnt rfdfivfd during tfrminbtion. */
        rfturn;
    }

    stbtid jdlbss kfyEvfntCls;
    if (kfyEvfntCls == NULL) {
        jdlbss kfyEvfntClsLodbl = fnv->FindClbss("jbvb/bwt/fvfnt/KfyEvfnt");
        DASSERT(kfyEvfntClsLodbl);
        if (kfyEvfntClsLodbl == NULL) {
            /* fxdfption blrfbdy tirown */
            rfturn;
        }
        kfyEvfntCls = (jdlbss)fnv->NfwGlobblRff(kfyEvfntClsLodbl);
        fnv->DflftfLodblRff(kfyEvfntClsLodbl);
    }

    stbtid jmftiodID kfyEvfntConst;
    if (kfyEvfntConst == NULL) {
        kfyEvfntConst =  fnv->GftMftiodID(kfyEvfntCls, "<init>",
                                          "(Ljbvb/bwt/Componfnt;IJIICI)V");
        DASSERT(kfyEvfntConst);
        CHECK_NULL(kfyEvfntConst);
    }
    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn;
    }
    jobjfdt tbrgft = GftTbrgft(fnv);
    jobjfdt kfyEvfnt = fnv->NfwObjfdt(kfyEvfntCls, kfyEvfntConst, tbrgft,
                                      id, wifn, modififrs, rbw, dookfd,
                                      kfyLodbtion);
    if (sbff_ExdfptionOddurrfd(fnv)) fnv->ExdfptionDfsdribf();
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    DASSERT(kfyEvfnt != NULL);
    if (kfyEvfnt == NULL) {
        fnv->DflftfLodblRff(tbrgft);
        rfturn;
    }
    fnv->SftLongFifld(kfyEvfnt, AwtKfyEvfnt::rbwCodfID, nbtivfCodf);
    if( nbtivfCodf && nbtivfCodf < 256 ) {
        fnv->SftLongFifld(kfyEvfnt, AwtKfyEvfnt::primbryLfvflUnidodfID, (jlong)(dynPrimbryKfymbp[nbtivfCodf].unidodf));
        fnv->SftLongFifld(kfyEvfnt, AwtKfyEvfnt::fxtfndfdKfyCodfID, (jlong)(dynPrimbryKfymbp[nbtivfCodf].jkfy));
        if( nbtivfCodf < 255 ) {
            fnv->SftLongFifld(kfyEvfnt, AwtKfyEvfnt::sdbndodfID, (jlong)(dynPrimbryKfymbp[nbtivfCodf].sdbndodf));
        }flsf if( pMsg != NULL ) {
            // unknown kfy witi virtubl kfydodf 0xFF.
            // Its sdbndodf is not in tif tbblf, pidkup it from tif mfssbgf.
            fnv->SftLongFifld(kfyEvfnt, AwtKfyEvfnt::sdbndodfID, (jlong)(HIWORD(pMsg->lPbrbm) & 0xFF));
        }
    }
    if (pMsg != NULL) {
        AwtAWTEvfnt::sbvfMSG(fnv, pMsg, kfyEvfnt);
    }
    SfndEvfnt(kfyEvfnt);

    fnv->DflftfLodblRff(kfyEvfnt);
    fnv->DflftfLodblRff(tbrgft);
}

void
AwtComponfnt::SfndKfyEvfntToFodusOwnfr(jint id, jlong wifn,
                                       jint rbw, jint dookfd,
                                       jint modififrs, jint kfyLodbtion,
                                       jlong nbtivfCodf,
                                       MSG *msg)
{
    /*
     * if fodus ownfr is null, but fodusfd window isn't
     * wf will sfnd kfy fvfnt to fodusfd window
     */
    HWND iwndTbrgft = ((sm_fodusOwnfr != NULL) ? sm_fodusOwnfr : AwtComponfnt::GftFodusfdWindow());

    if (iwndTbrgft == GftHWnd()) {
        SfndKfyEvfnt(id, wifn, rbw, dookfd, modififrs, kfyLodbtion, nbtivfCodf, msg);
    } flsf {
        AwtComponfnt *tbrgft = NULL;
        if (iwndTbrgft != NULL) {
            tbrgft = AwtComponfnt::GftComponfnt(iwndTbrgft);
            if (tbrgft == NULL) {
                tbrgft = tiis;
            }
        }
        if (tbrgft != NULL) {
            tbrgft->SfndKfyEvfnt(id, wifn, rbw, dookfd, modififrs,
              kfyLodbtion, nbtivfCodf, msg);
        }
    }
}

void AwtComponfnt::SftDrbgCbpturf(UINT flbgs)
{
    // don't wbnt to intfrffrf witi otifr dontrols
    if (::GftCbpturf() == NULL) {
        ::SftCbpturf(GftHWnd());
    }
}

void AwtComponfnt::RflfbsfDrbgCbpturf(UINT flbgs)
{
    if ((::GftCbpturf() == GftHWnd()) && ((flbgs & ALL_MK_BUTTONS) == 0)) {
        // usfr ibs rflfbsfd bll buttons, so rflfbsf tif dbpturf
        ::RflfbsfCbpturf();
    }
}

void AwtComponfnt::SfndMousfEvfnt(jint id, jlong wifn, jint x, jint y,
                                  jint modififrs, jint dlidkCount,
                                  jboolfbn popupTriggfr, jint button,
                                  MSG *pMsg)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    CritidblSfdtion::Lodk l(GftLodk());
    if (GftPffr(fnv) == NULL) {
        /* fvfnt rfdfivfd during tfrminbtion. */
        rfturn;
    }

    stbtid jdlbss mousfEvfntCls;
    if (mousfEvfntCls == NULL) {
        jdlbss mousfEvfntClsLodbl =
            fnv->FindClbss("jbvb/bwt/fvfnt/MousfEvfnt");
        CHECK_NULL(mousfEvfntClsLodbl);
        mousfEvfntCls = (jdlbss)fnv->NfwGlobblRff(mousfEvfntClsLodbl);
        fnv->DflftfLodblRff(mousfEvfntClsLodbl);
    }
    RECT insfts;
    GftInsfts(&insfts);

    stbtid jmftiodID mousfEvfntConst;
    if (mousfEvfntConst == NULL) {
        mousfEvfntConst =
            fnv->GftMftiodID(mousfEvfntCls, "<init>",
                 "(Ljbvb/bwt/Componfnt;IJIIIIIIZI)V");
        DASSERT(mousfEvfntConst);
        CHECK_NULL(mousfEvfntConst);
    }
    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn;
    }
    jobjfdt tbrgft = GftTbrgft(fnv);
    DWORD durMousfPos = ::GftMfssbgfPos();
    int xAbs = GET_X_LPARAM(durMousfPos);
    int yAbs = GET_Y_LPARAM(durMousfPos);
    jobjfdt mousfEvfnt = fnv->NfwObjfdt(mousfEvfntCls, mousfEvfntConst,
                                        tbrgft,
                                        id, wifn, modififrs,
                                        x+insfts.lfft, y+insfts.top,
                                        xAbs, yAbs,
                                        dlidkCount, popupTriggfr, button);

    if (sbff_ExdfptionOddurrfd(fnv)) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
    }

    DASSERT(mousfEvfnt != NULL);
    CHECK_NULL(mousfEvfnt);
    if (pMsg != 0) {
        AwtAWTEvfnt::sbvfMSG(fnv, pMsg, mousfEvfnt);
    }
    SfndEvfnt(mousfEvfnt);

    fnv->DflftfLodblRff(mousfEvfnt);
    fnv->DflftfLodblRff(tbrgft);
}

void
AwtComponfnt::SfndMousfWifflEvfnt(jint id, jlong wifn, jint x, jint y,
                                  jint modififrs, jint dlidkCount,
                                  jboolfbn popupTriggfr, jint sdrollTypf,
                                  jint sdrollAmount, jint roundfdWifflRotbtion,
                                  jdoublf prfdisfWifflRotbtion, MSG *pMsg)
{
    /* Codf bbsfd not so loosfly on AwtComponfnt::SfndMousfEvfnt */
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    CritidblSfdtion::Lodk l(GftLodk());
    if (GftPffr(fnv) == NULL) {
        /* fvfnt rfdfivfd during tfrminbtion. */
        rfturn;
    }

    stbtid jdlbss mousfWifflEvfntCls;
    if (mousfWifflEvfntCls == NULL) {
        jdlbss mousfWifflEvfntClsLodbl =
            fnv->FindClbss("jbvb/bwt/fvfnt/MousfWifflEvfnt");
        CHECK_NULL(mousfWifflEvfntClsLodbl);
        mousfWifflEvfntCls = (jdlbss)fnv->NfwGlobblRff(mousfWifflEvfntClsLodbl);
        fnv->DflftfLodblRff(mousfWifflEvfntClsLodbl);
    }
    RECT insfts;
    GftInsfts(&insfts);

    stbtid jmftiodID mousfWifflEvfntConst;
    if (mousfWifflEvfntConst == NULL) {
        mousfWifflEvfntConst =
            fnv->GftMftiodID(mousfWifflEvfntCls, "<init>",
                           "(Ljbvb/bwt/Componfnt;IJIIIIIIZIIID)V");
        DASSERT(mousfWifflEvfntConst);
        CHECK_NULL(mousfWifflEvfntConst);
    }
    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn;
    }
    jobjfdt tbrgft = GftTbrgft(fnv);
    DTRACE_PRINTLN("drfbting MWE in JNI");

    jobjfdt mousfWifflEvfnt = fnv->NfwObjfdt(mousfWifflEvfntCls,
                                             mousfWifflEvfntConst,
                                             tbrgft,
                                             id, wifn, modififrs,
                                             x+insfts.lfft, y+insfts.top,
                                             0, 0,
                                             dlidkCount, popupTriggfr,
                                             sdrollTypf, sdrollAmount,
                                             roundfdWifflRotbtion, prfdisfWifflRotbtion);

    DASSERT(mousfWifflEvfnt != NULL);
    if (mousfWifflEvfnt == NULL || sbff_ExdfptionOddurrfd(fnv)) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
        fnv->DflftfLodblRff(tbrgft);
        rfturn;
    }
    if (pMsg != NULL) {
        AwtAWTEvfnt::sbvfMSG(fnv, pMsg, mousfWifflEvfnt);
    }
    SfndEvfnt(mousfWifflEvfnt);

    fnv->DflftfLodblRff(mousfWifflEvfnt);
    fnv->DflftfLodblRff(tbrgft);
}

void AwtComponfnt::SfndFodusEvfnt(jint id, HWND oppositf)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    CritidblSfdtion::Lodk l(GftLodk());
    if (GftPffr(fnv) == NULL) {
        /* fvfnt rfdfivfd during tfrminbtion. */
        rfturn;
    }

    stbtid jdlbss fodusEvfntCls;
    if (fodusEvfntCls == NULL) {
        jdlbss fodusEvfntClsLodbl
            = fnv->FindClbss("jbvb/bwt/fvfnt/FodusEvfnt");
        DASSERT(fodusEvfntClsLodbl);
        CHECK_NULL(fodusEvfntClsLodbl);
        fodusEvfntCls = (jdlbss)fnv->NfwGlobblRff(fodusEvfntClsLodbl);
        fnv->DflftfLodblRff(fodusEvfntClsLodbl);
    }

    stbtid jmftiodID fodusEvfntConst;
    if (fodusEvfntConst == NULL) {
        fodusEvfntConst =
            fnv->GftMftiodID(fodusEvfntCls, "<init>",
                             "(Ljbvb/bwt/Componfnt;IZLjbvb/bwt/Componfnt;)V");
        DASSERT(fodusEvfntConst);
        CHECK_NULL(fodusEvfntConst);
    }

    stbtid jdlbss sfqufndfdEvfntCls;
    if (sfqufndfdEvfntCls == NULL) {
        jdlbss sfqufndfdEvfntClsLodbl =
            fnv->FindClbss("jbvb/bwt/SfqufndfdEvfnt");
        DASSERT(sfqufndfdEvfntClsLodbl);
        CHECK_NULL(sfqufndfdEvfntClsLodbl);
        sfqufndfdEvfntCls =
            (jdlbss)fnv->NfwGlobblRff(sfqufndfdEvfntClsLodbl);
        fnv->DflftfLodblRff(sfqufndfdEvfntClsLodbl);
    }

    stbtid jmftiodID sfqufndfdEvfntConst;
    if (sfqufndfdEvfntConst == NULL) {
        sfqufndfdEvfntConst =
            fnv->GftMftiodID(sfqufndfdEvfntCls, "<init>",
                             "(Ljbvb/bwt/AWTEvfnt;)V");
        DASSERT(sfqufndfdEvfntConst);
        CHECK_NULL(sfqufndfdEvfntConst);
    }

    if (fnv->EnsurfLodblCbpbdity(3) < 0) {
        rfturn;
    }

    jobjfdt tbrgft = GftTbrgft(fnv);
    jobjfdt jOppositf = NULL;
    if (oppositf != NULL) {
        AwtComponfnt *bwtOppositf = AwtComponfnt::GftComponfnt(oppositf);
        if (bwtOppositf != NULL) {
            jOppositf = bwtOppositf->GftTbrgft(fnv);
        }
    }
    jobjfdt fodusEvfnt = fnv->NfwObjfdt(fodusEvfntCls, fodusEvfntConst,
                                        tbrgft, id, JNI_FALSE, jOppositf);
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    DASSERT(fodusEvfnt != NULL);
    if (jOppositf != NULL) {
        fnv->DflftfLodblRff(jOppositf); jOppositf = NULL;
    }
    fnv->DflftfLodblRff(tbrgft); tbrgft = NULL;
    CHECK_NULL(fodusEvfnt);

    jobjfdt sfqufndfdEvfnt = fnv->NfwObjfdt(sfqufndfdEvfntCls,
                                            sfqufndfdEvfntConst,
                                            fodusEvfnt);
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    DASSERT(sfqufndfdEvfnt != NULL);
    fnv->DflftfLodblRff(fodusEvfnt); fodusEvfnt = NULL;
    CHECK_NULL(sfqufndfdEvfnt);
    SfndEvfnt(sfqufndfdEvfnt);

    fnv->DflftfLodblRff(sfqufndfdEvfnt);
}

/*
 * Forwbrd b filtfrfd fvfnt dirfdtly to tif subdlbssfd window.
 * Tiis mftiod is nffdfd so tibt DffWindowProd is invokfd on tif
 * domponfnt's owning tirfbd.
 */
MsgRouting AwtComponfnt::HbndlfEvfnt(MSG *msg, BOOL)
{
    DffWindowProd(msg->mfssbgf, msg->wPbrbm, msg->lPbrbm);
    dflftf msg;
    rfturn mrConsumf;
}

/* Post b WM_AWT_HANDLE_EVENT mfssbgf wiidi invokfs HbndlfEvfnt
   on tif toolkit tirfbd. Tiis mftiod mby prf-filtfr tif mfssbgfs. */
BOOL AwtComponfnt::PostHbndlfEvfntMfssbgf(MSG *msg, BOOL syntiftid)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    // Wf siould dut off kfybobrd fvfnts to disbblfd domponfnts
    // to bvoid tif domponfnts rfsponding visublly to kfystrokfs wifn disbblfd.
    // wf siouldn't dut off WM_SYS* mfssbgfs bs tify brfn't usfd for normbl bdtivity
    // but to bdtivbtf mfnus, dlosf windows, ftd
    switdi(msg->mfssbgf) {
        dbsf WM_KEYDOWN:
        dbsf WM_KEYUP:
        dbsf WM_CHAR:
        dbsf WM_DEADCHAR:
            {
                if (!isRfdursivflyEnbblfd()) {
                    goto quit;
                }
                brfbk;
            }
    }
    if (PostMfssbgf(GftHWnd(), WM_AWT_HANDLE_EVENT,
        (WPARAM) syntiftid, (LPARAM) msg)) {
            rfturn TRUE;
    } flsf {
        JNU_TirowIntfrnblError(fnv, "Mfssbgf not postfd, nbtivf fvfnt qufuf mby bf full.");
    }
quit:
    dflftf msg;
    rfturn FALSE;
}

void AwtComponfnt::SyntifsizfKfyMfssbgf(JNIEnv *fnv, jobjfdt kfyEvfnt)
{
    jint id = (fnv)->GftIntFifld(kfyEvfnt, AwtAWTEvfnt::idID);
    UINT mfssbgf;
    switdi (id) {
      dbsf jbvb_bwt_fvfnt_KfyEvfnt_KEY_PRESSED:
          mfssbgf = WM_KEYDOWN;
          brfbk;
      dbsf jbvb_bwt_fvfnt_KfyEvfnt_KEY_RELEASED:
          mfssbgf = WM_KEYUP;
          brfbk;
      dbsf jbvb_bwt_fvfnt_KfyEvfnt_KEY_TYPED:
          mfssbgf = WM_CHAR;
          brfbk;
      dffbult:
          rfturn;
    }

    /*
     * KfyEvfnt.modififrs brfn't supportfd -- tif Jbvb bpppwd must sfnd sfpbrbtf
     * KEY_PRESSED bnd KEY_RELEASED fvfnts for tif modififr virtubl kfys.
     */
    if (id == jbvb_bwt_fvfnt_KfyEvfnt_KEY_TYPED) {
        // WM_CHAR mfssbgf must bf postfd using WM_AWT_FORWARD_CHAR
        // (for Edit dontrol)
        jdibr kfyCibr = (jdibr)
          (fnv)->GftCibrFifld(kfyEvfnt, AwtKfyEvfnt::kfyCibrID);

        // Bugid 4724007.  If it is b Dflftf dibrbdtfr, don't sfnd tif fbkf
        // KEY_TYPED wf drfbtfd bbdk to tif nbtivf window: Windows dofsn't
        // fxpfdt b WM_CHAR for Dflftf in TfxtFiflds, so it trifs to fntfr b
        // dibrbdtfr bftfr dflfting.
        if (kfyCibr == '\177') { // tif Dflftf dibrbdtfr
            rfturn;
        }

        // Disbblf forwbrding WM_CHAR mfssbgfs to disbblfd domponfnts
        if (isRfdursivflyEnbblfd()) {
            if (!::PostMfssbgf(GftHWnd(), WM_AWT_FORWARD_CHAR,
                MAKEWPARAM(kfyCibr, TRUE), 0)) {
                JNU_TirowIntfrnblError(fnv, "Mfssbgf not postfd, nbtivf fvfnt qufuf mby bf full.");
            }
        }
    } flsf {
        jint kfyCodf =
          (fnv)->GftIntFifld(kfyEvfnt, AwtKfyEvfnt::kfyCodfID);
        UINT kfy, modififrs;
        AwtComponfnt::JbvbKfyToWindowsKfy(kfyCodf, &kfy, &modififrs);
        MSG* msg = CrfbtfMfssbgf(mfssbgf, kfy, 0);
        PostHbndlfEvfntMfssbgf(msg, TRUE);
    }
}

void AwtComponfnt::SyntifsizfMousfMfssbgf(JNIEnv *fnv, jobjfdt mousfEvfnt)
{
    /*    DfbugBrfbk(); */
    jint button = (fnv)->GftIntFifld(mousfEvfnt, AwtMousfEvfnt::buttonID);
    jint modififrs = (fnv)->GftIntFifld(mousfEvfnt, AwtInputEvfnt::modififrsID);

    WPARAM wPbrbm = 0;
    WORD wLow = 0;
    jint wifflAmt = 0;
    jint id = (fnv)->GftIntFifld(mousfEvfnt, AwtAWTEvfnt::idID);
    UINT mfssbgf;
    switdi (id) {
      dbsf jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_PRESSED: {
          switdi (button) {
            dbsf jbvb_bwt_fvfnt_MousfEvfnt_BUTTON1:
                mfssbgf = WM_LBUTTONDOWN; brfbk;
            dbsf jbvb_bwt_fvfnt_MousfEvfnt_BUTTON3:
                mfssbgf = WM_MBUTTONDOWN; brfbk;
            dbsf jbvb_bwt_fvfnt_MousfEvfnt_BUTTON2:
                mfssbgf = WM_RBUTTONDOWN; brfbk;
          }
          brfbk;
      }
      dbsf jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_RELEASED: {
          switdi (button) {
            dbsf jbvb_bwt_fvfnt_MousfEvfnt_BUTTON1:
                mfssbgf = WM_LBUTTONUP; brfbk;
            dbsf jbvb_bwt_fvfnt_MousfEvfnt_BUTTON3:
                mfssbgf = WM_MBUTTONUP; brfbk;
            dbsf jbvb_bwt_fvfnt_MousfEvfnt_BUTTON2:
                mfssbgf = WM_RBUTTONUP; brfbk;
          }
          brfbk;
      }
      dbsf jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_MOVED:
          /* MOUSE_DRAGGED fvfnts must first ibvf sfnt b MOUSE_PRESSED fvfnt. */
      dbsf jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_DRAGGED:
          mfssbgf = WM_MOUSEMOVE;
          brfbk;
      dbsf jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_WHEEL:
          if (modififrs & jbvb_bwt_fvfnt_InputEvfnt_CTRL_DOWN_MASK) {
              wLow |= MK_CONTROL;
          }
          if (modififrs & jbvb_bwt_fvfnt_InputEvfnt_SHIFT_DOWN_MASK) {
              wLow |= MK_SHIFT;
          }
          if (modififrs & jbvb_bwt_fvfnt_InputEvfnt_BUTTON1_DOWN_MASK) {
              wLow |= MK_LBUTTON;
          }
          if (modififrs & jbvb_bwt_fvfnt_InputEvfnt_BUTTON2_DOWN_MASK) {
              wLow |= MK_RBUTTON;
          }
          if (modififrs & jbvb_bwt_fvfnt_InputEvfnt_BUTTON3_DOWN_MASK) {
              wLow |= MK_MBUTTON;
          }
          if (modififrs & X1_BUTTON) {
              wLow |= GftButtonMK(X1_BUTTON);
          }
          if (modififrs & X2_BUTTON) {
              wLow |= GftButtonMK(X2_BUTTON);
          }

          wifflAmt = (jint)JNU_CbllMftiodByNbmf(fnv,
                                               NULL,
                                               mousfEvfnt,
                                               "gftWifflRotbtion",
                                               "()I").i;
          DASSERT(!sbff_ExdfptionOddurrfd(fnv));
          JNU_CHECK_EXCEPTION(fnv);
          DTRACE_PRINTLN1("wifflAmt = %i\n", wifflAmt);

          // donvfrt Jbvb wiffl bmount vbluf to Win32
          wifflAmt *= -1 * WHEEL_DELTA;

          mfssbgf = WM_MOUSEWHEEL;
          wPbrbm = MAKEWPARAM(wLow, wifflAmt);

          brfbk;
      dffbult:
          rfturn;
    }
    jint x = (fnv)->GftIntFifld(mousfEvfnt, AwtMousfEvfnt::xID);
    jint y = (fnv)->GftIntFifld(mousfEvfnt, AwtMousfEvfnt::yID);
    MSG* msg = CrfbtfMfssbgf(mfssbgf, wPbrbm, MAKELPARAM(x, y), x, y);
    PostHbndlfEvfntMfssbgf(msg, TRUE);
}

BOOL AwtComponfnt::InifritsNbtivfMousfWifflBfibvior() {rfturn fblsf;}

void AwtComponfnt::Invblidbtf(RECT* r)
{
    ::InvblidbtfRfdt(GftHWnd(), r, FALSE);
}

void AwtComponfnt::BfginVblidbtf()
{
    DASSERT(m_vblidbtionNfstCount >= 0 &&
           m_vblidbtionNfstCount < 1000); // sbnity difdk

    if (m_vblidbtionNfstCount == 0) {
    // bfgin dfffrrfd window positioning if wf'rf not insidf
    // bnotifr Bfgin/EndVblidbtf pbir
        DASSERT(m_idwp == NULL);
        m_idwp = ::BfginDfffrWindowPos(32);
    }

    m_vblidbtionNfstCount++;
}

void AwtComponfnt::EndVblidbtf()
{
    DASSERT(m_vblidbtionNfstCount > 0 &&
           m_vblidbtionNfstCount < 1000); // sbnity difdk
    DASSERT(m_idwp != NULL);

    m_vblidbtionNfstCount--;
    if (m_vblidbtionNfstCount == 0) {
    // if tiis dbll to EndVblidbtf is not nfstfd insidf bnotifr
    // Bfgin/EndVblidbtf pbir, fnd dfffrrfd window positioning
        ::EndDfffrWindowPos(m_idwp);
        m_idwp = NULL;
    }
}

/**
 * HWND, AwtComponfnt bnd Jbvb Pffr intfrbdtion
 */

/*
 *Link tif C++, Jbvb pffr, bnd HWNDs togftifr.
 */
void AwtComponfnt::LinkObjfdts(JNIEnv *fnv, jobjfdt pffr)
{
    /*
     * Bind bll tirff objfdts togftifr tiru tiis C++ objfdt, two-wby to fbdi:
     *     JbvbPffr <-> C++ <-> HWND
     *
     * C++ -> JbvbPffr
     */
    if (m_pffrObjfdt == NULL) {
        // Tiis mby ibvf blrfbdy bffn sft up by CrfbtfHWnd
        // And wf don't wbnt to drfbtf two rfffrfndfs so wf
        // will lfbvf tif prior onf blonf
        m_pffrObjfdt = fnv->NfwGlobblRff(pffr);
    }
    /* JbvbPffr -> HWND */
    fnv->SftLongFifld(pffr, AwtComponfnt::iwndID, rfintfrprft_dbst<jlong>(m_iwnd));

    /* JbvbPffr -> C++ */
    JNI_SET_PDATA(pffr, tiis);

    /* HWND -> C++ */
    SftComponfntInHWND();
}

/* Clfbnup bbovf linking */
void AwtComponfnt::UnlinkObjfdts()
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (m_pffrObjfdt) {
        fnv->SftLongFifld(m_pffrObjfdt, AwtComponfnt::iwndID, 0);
        JNI_SET_PDATA(m_pffrObjfdt, stbtid_dbst<PDATA>(NULL));
        JNI_SET_DESTROYED(m_pffrObjfdt);
        fnv->DflftfGlobblRff(m_pffrObjfdt);
        m_pffrObjfdt = NULL;
    }
}

void AwtComponfnt::Enbblf(BOOL bEnbblf)
{
    if (bEnbblf && IsTopLfvfl()) {
        // wf siould not fnbblf blodkfd toplfvfls
        bEnbblf = !::IsWindow(AwtWindow::GftModblBlodkfr(GftHWnd()));
    }
    // Siouldn't triggfr nbtivf fodus dibngf
    // (only tif proxy mby bf tif nbtivf fodus ownfr).
    ::EnbblfWindow(GftHWnd(), bEnbblf);

    CritidblSfdtion::Lodk l(GftLodk());
    VfrifyStbtf();
}

/*
 * bssodibtf bn AwtDropTbrgft witi tiis AwtComponfnt
 */

AwtDropTbrgft* AwtComponfnt::CrfbtfDropTbrgft(JNIEnv* fnv) {
    m_dropTbrgft = nfw AwtDropTbrgft(fnv, tiis);
    m_dropTbrgft->RfgistfrTbrgft(TRUE);
    rfturn m_dropTbrgft;
}

/*
 * disbssodibtf bn AwtDropTbrgft witi tiis AwtComponfnt
 */

void AwtComponfnt::DfstroyDropTbrgft() {
    if (m_dropTbrgft != NULL) {
        m_dropTbrgft->RfgistfrTbrgft(FALSE);
        m_dropTbrgft->Rflfbsf();
        m_dropTbrgft = NULL;
    }
}

BOOL AwtComponfnt::IsFodusingMousfMfssbgf(MSG *pMsg) {
    rfturn pMsg->mfssbgf == WM_LBUTTONDOWN || pMsg->mfssbgf == WM_LBUTTONDBLCLK;
}

BOOL AwtComponfnt::IsFodusingKfyMfssbgf(MSG *pMsg) {
    rfturn pMsg->mfssbgf == WM_KEYDOWN && pMsg->wPbrbm == VK_SPACE;
}

void AwtComponfnt::_Siow(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    AwtComponfnt *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    p = (AwtComponfnt *)pDbtb;
    if (::IsWindow(p->GftHWnd()))
    {
        p->SfndMfssbgf(WM_AWT_COMPONENT_SHOW);
    }
rft:
    fnv->DflftfGlobblRff(sflf);
}

void AwtComponfnt::_Hidf(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    AwtComponfnt *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    p = (AwtComponfnt *)pDbtb;
    if (::IsWindow(p->GftHWnd()))
    {
        p->SfndMfssbgf(WM_AWT_COMPONENT_HIDE);
    }
rft:
    fnv->DflftfGlobblRff(sflf);
}

void AwtComponfnt::_Enbblf(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    AwtComponfnt *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    p = (AwtComponfnt *)pDbtb;
    if (::IsWindow(p->GftHWnd()))
    {
        p->Enbblf(TRUE);
    }
rft:
    fnv->DflftfGlobblRff(sflf);
}

void AwtComponfnt::_Disbblf(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    AwtComponfnt *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    p = (AwtComponfnt *)pDbtb;
    if (::IsWindow(p->GftHWnd()))
    {
        p->Enbblf(FALSE);
    }
rft:
    fnv->DflftfGlobblRff(sflf);
}

jobjfdt AwtComponfnt::_GftLodbtionOnSdrffn(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    jobjfdt rfsult = NULL;
    AwtComponfnt *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    p = (AwtComponfnt *)pDbtb;
    if (::IsWindow(p->GftHWnd()))
    {
        RECT rfdt;
        VERIFY(::GftWindowRfdt(p->GftHWnd(),&rfdt));
        rfsult = JNU_NfwObjfdtByNbmf(fnv, "jbvb/bwt/Point", "(II)V",
            rfdt.lfft, rfdt.top);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    if (rfsult != NULL)
    {
        jobjfdt rfsultGlobblRff = fnv->NfwGlobblRff(rfsult);
        fnv->DflftfLodblRff(rfsult);
        rfturn rfsultGlobblRff;
    }
    flsf
    {
        rfturn NULL;
    }
}

void AwtComponfnt::_Rfsibpf(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    RfsibpfStrudt *rs = (RfsibpfStrudt*)pbrbm;
    jobjfdt sflf = rs->domponfnt;
    jint x = rs->x;
    jint y = rs->y;
    jint w = rs->w;
    jint i = rs->i;

    AwtComponfnt *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    p = (AwtComponfnt *)pDbtb;
    if (::IsWindow(p->GftHWnd()))
    {
        RECT* r = nfw RECT;
        ::SftRfdt(r, x, y, x + w, y + i);
        p->SfndMfssbgf(WM_AWT_RESHAPE_COMPONENT, CHECK_EMBEDDED, (LPARAM)r);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf rs;
}

void AwtComponfnt::_RfsibpfNoCifdk(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    RfsibpfStrudt *rs = (RfsibpfStrudt*)pbrbm;
    jobjfdt sflf = rs->domponfnt;
    jint x = rs->x;
    jint y = rs->y;
    jint w = rs->w;
    jint i = rs->i;

    AwtComponfnt *p;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    p = (AwtComponfnt *)pDbtb;
    if (::IsWindow(p->GftHWnd()))
    {
        RECT* r = nfw RECT;
        ::SftRfdt(r, x, y, x + w, y + i);
        p->SfndMfssbgf(WM_AWT_RESHAPE_COMPONENT, DONT_CHECK_EMBEDDED, (LPARAM)r);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf rs;
}

void AwtComponfnt::_NbtivfHbndlfEvfnt(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    NbtivfHbndlfEvfntStrudt *nifs = (NbtivfHbndlfEvfntStrudt *)pbrbm;
    jobjfdt sflf = nifs->domponfnt;
    jobjfdt fvfnt = nifs->fvfnt;

    AwtComponfnt *p;

    PDATA pDbtb;
    JNI_CHECK_NULL_GOTO(sflf, "pffr", rft);
    pDbtb = JNI_GET_PDATA(sflf);
    if (pDbtb == NULL) {
        fnv->DflftfGlobblRff(sflf);
        if (fvfnt != NULL) {
            fnv->DflftfGlobblRff(fvfnt);
        }
        dflftf nifs;
        rfturn;
    }
    JNI_CHECK_NULL_GOTO(fvfnt, "null AWTEvfnt", rft);

    p = (AwtComponfnt *)pDbtb;
    if (::IsWindow(p->GftHWnd()))
    {
        if (fnv->EnsurfLodblCbpbdity(1) < 0) {
            fnv->DflftfGlobblRff(sflf);
            fnv->DflftfGlobblRff(fvfnt);
            dflftf nifs;
            rfturn;
        }
        jbytfArrby bdbtb = (jbytfArrby)(fnv)->GftObjfdtFifld(fvfnt, AwtAWTEvfnt::bdbtbID);
        int id = (fnv)->GftIntFifld(fvfnt, AwtAWTEvfnt::idID);
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));
        if (bdbtb != 0) {
            MSG msg;
            (fnv)->GftBytfArrbyRfgion(bdbtb, 0, sizfof(MSG), (jbytf *)&msg);
            (fnv)->DflftfLodblRff(bdbtb);
            stbtid BOOL kfyDownConsumfd = FALSE;
            stbtid BOOL bCibrCibngfd = FALSE;
            stbtid WCHAR modififdCibr;
            WCHAR unidodfCibr;

            /* Rfmfmbfr if b KEY_PRESSED fvfnt is donsumfd, bs bn old modfl
             * progrbm won't donsumf b subsfqufnt KEY_TYPED fvfnt.
             */
            jboolfbn donsumfd =
                (fnv)->GftBoolfbnFifld(fvfnt, AwtAWTEvfnt::donsumfdID);
            DASSERT(!sbff_ExdfptionOddurrfd(fnv));

            if (donsumfd) {
                kfyDownConsumfd = (id == jbvb_bwt_fvfnt_KfyEvfnt_KEY_PRESSED);
                fnv->DflftfGlobblRff(sflf);
                fnv->DflftfGlobblRff(fvfnt);
                dflftf nifs;
                rfturn;

            } flsf if (id == jbvb_bwt_fvfnt_KfyEvfnt_KEY_PRESSED) {
                // Fix for 6637607: rfsft donsuming
                kfyDownConsumfd = FALSE;
            }

            /* Consumf b KEY_TYPED fvfnt if b KEY_PRESSED ibd bffn, to support
             * tif old modfl.
             */
            if ((id == jbvb_bwt_fvfnt_KfyEvfnt_KEY_TYPED) && kfyDownConsumfd) {
                kfyDownConsumfd = FALSE;
                fnv->DflftfGlobblRff(sflf);
                fnv->DflftfGlobblRff(fvfnt);
                dflftf nifs;
                rfturn;
            }

            /* Modify bny fvfnt pbrbmftfrs, if nfdfssbry. */
            if (sflf && pDbtb &&
                id >= jbvb_bwt_fvfnt_KfyEvfnt_KEY_FIRST &&
                id <= jbvb_bwt_fvfnt_KfyEvfnt_KEY_LAST) {

                    AwtComponfnt* p = (AwtComponfnt*)pDbtb;

                    jint kfyCodf =
                      (fnv)->GftIntFifld(fvfnt, AwtKfyEvfnt::kfyCodfID);
                    jdibr kfyCibr =
                      (fnv)->GftCibrFifld(fvfnt, AwtKfyEvfnt::kfyCibrID);
                    jint modififrs =
                      (fnv)->GftIntFifld(fvfnt, AwtInputEvfnt::modififrsID);

                    DASSERT(!sbff_ExdfptionOddurrfd(fnv));

                /* Cifdk to sff wiftifr tif kfyCodf or modififrs wfrf dibngfd
                   on tif kfyPrfssfd fvfnt, bnd twfbk tif following kfyTypfd
                   fvfnt (if bny) bddodingly.  */
                switdi (id) {
                dbsf jbvb_bwt_fvfnt_KfyEvfnt_KEY_PRESSED:
                {
                    UINT winKfy = (UINT)msg.wPbrbm;
                    bCibrCibngfd = FALSE;

                    if (winKfy == VK_PROCESSKEY) {
                        // Lfbvf it up to IME
                        brfbk;
                    }

                    if (kfyCodf != jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED) {
                        UINT nfwWinKfy, ignorfd;
                        p->JbvbKfyToWindowsKfy(kfyCodf, &nfwWinKfy, &ignorfd, winKfy);
                        if (nfwWinKfy != 0) {
                            winKfy = nfwWinKfy;
                        }
                    }

                    BOOL isDfbdKfy = FALSE;
                    modififdCibr = p->WindowsKfyToJbvbCibr(winKfy, modififrs, AwtComponfnt::NONE, isDfbdKfy);
                    bCibrCibngfd = (kfyCibr != modififdCibr);
                }
                brfbk;

                dbsf jbvb_bwt_fvfnt_KfyEvfnt_KEY_RELEASED:
                {
                    kfyDownConsumfd = FALSE;
                    bCibrCibngfd = FALSE;
                }
                brfbk;

                dbsf jbvb_bwt_fvfnt_KfyEvfnt_KEY_TYPED:
                {
                    if (bCibrCibngfd)
                    {
                        unidodfCibr = modififdCibr;
                    }
                    flsf
                    {
                        unidodfCibr = kfyCibr;
                    }
                    bCibrCibngfd = FALSE;

                    // Disbblf forwbrding KEY_TYPED mfssbgfs to pffrs of
                    // disbblfd domponfnts
                    if (p->isRfdursivflyEnbblfd()) {
                        // sfnd tif dibrbdtfr bbdk to tif nbtivf window for
                        // prodfssing. Tif WM_AWT_FORWARD_CHAR ibndlfr will sfnd
                        // tiis dibrbdtfr to DffWindowProd
                        if (!::PostMfssbgf(p->GftHWnd(), WM_AWT_FORWARD_CHAR,
                            MAKEWPARAM(unidodfCibr, FALSE), msg.lPbrbm)) {
                            JNU_TirowIntfrnblError(fnv, "Mfssbgf not postfd, nbtivf fvfnt qufuf mby bf full.");
                        }
                    }
                    fnv->DflftfGlobblRff(sflf);
                    fnv->DflftfGlobblRff(fvfnt);
                    dflftf nifs;
                    rfturn;
                }
                brfbk;

                dffbult:
                    brfbk;
                }
            }

            // ignorf bll InputMftiodEvfnts
            if (sflf && (pDbtb = JNI_GET_PDATA(sflf)) &&
                id >= jbvb_bwt_fvfnt_InputMftiodEvfnt_INPUT_METHOD_FIRST &&
                id <= jbvb_bwt_fvfnt_InputMftiodEvfnt_INPUT_METHOD_LAST) {
                fnv->DflftfGlobblRff(sflf);
                fnv->DflftfGlobblRff(fvfnt);
                dflftf nifs;
                rfturn;
            }

            // Crfbtf dopy for lodbl msg
            MSG* pCopifdMsg = nfw MSG;
            mfmmovf(pCopifdMsg, &msg, sizfof(MSG));
            // Evfnt ibndlfr dflftfs msg
            p->PostHbndlfEvfntMfssbgf(pCopifdMsg, FALSE);

            fnv->DflftfGlobblRff(sflf);
            fnv->DflftfGlobblRff(fvfnt);
            dflftf nifs;
            rfturn;
        }

        /* Forwbrd bny vblid syntifsizfd fvfnts.  Currfntly only mousf bnd
         * kfy fvfnts brf supportfd.
         */
        if (sflf == NULL || (pDbtb = JNI_GET_PDATA(sflf)) == NULL) {
            fnv->DflftfGlobblRff(sflf);
            fnv->DflftfGlobblRff(fvfnt);
            dflftf nifs;
            rfturn;
        }

        AwtComponfnt* p = (AwtComponfnt*)pDbtb;
        if (id >= jbvb_bwt_fvfnt_KfyEvfnt_KEY_FIRST &&
            id <= jbvb_bwt_fvfnt_KfyEvfnt_KEY_LAST) {
            p->SyntifsizfKfyMfssbgf(fnv, fvfnt);
        } flsf if (id >= jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_FIRST &&
                   id <= jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_LAST) {
            p->SyntifsizfMousfMfssbgf(fnv, fvfnt);
        }
    }

rft:
    if (sflf != NULL) {
        fnv->DflftfGlobblRff(sflf);
    }
    if (fvfnt != NULL) {
        fnv->DflftfGlobblRff(fvfnt);
    }

    dflftf nifs;
}

void AwtComponfnt::_SftForfground(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftColorStrudt *sds = (SftColorStrudt *)pbrbm;
    jobjfdt sflf = sds->domponfnt;
    jint rgb = sds->rgb;

    AwtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        d->SftColor(PALETTERGB((rgb>>16)&0xff,
                               (rgb>>8)&0xff,
                               (rgb)&0xff));
        d->VfrifyStbtf();
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf sds;
}

void AwtComponfnt::_SftBbdkground(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftColorStrudt *sds = (SftColorStrudt *)pbrbm;
    jobjfdt sflf = sds->domponfnt;
    jint rgb = sds->rgb;

    AwtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        d->SftBbdkgroundColor(PALETTERGB((rgb>>16)&0xff,
                                         (rgb>>8)&0xff,
                                         (rgb)&0xff));
        d->VfrifyStbtf();
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf sds;
}

void AwtComponfnt::_SftFont(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftFontStrudt *sfs = (SftFontStrudt *)pbrbm;
    jobjfdt sflf = sfs->domponfnt;
    jobjfdt font = sfs->font;

    AwtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    JNI_CHECK_NULL_GOTO(font, "null font", rft);
    d = (AwtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        AwtFont *bwtFont = (AwtFont *)fnv->GftLongFifld(font, AwtFont::pDbtbID);
        if (bwtFont == NULL) {
            /*brgumfnts of AwtFont::Crfbtf brf dibngfd for multifont domponfnt */
            bwtFont = AwtFont::Crfbtf(fnv, font);
        }
        fnv->SftLongFifld(font, AwtFont::pDbtbID, (jlong)bwtFont);

        d->SftFont(bwtFont);
    }
rft:
    fnv->DflftfGlobblRff(sflf);
    fnv->DflftfGlobblRff(font);

    dflftf sfs;
}

// Sfts or kills fodus for b domponfnt.
void AwtComponfnt::_SftFodus(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftFodusStrudt *sfs = (SftFodusStrudt *)pbrbm;
    jobjfdt sflf = sfs->domponfnt;
    jboolfbn doSftFodus = sfs->doSftFodus;

    AwtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_NULL_GOTO(sflf, "pffr", rft);
    pDbtb = JNI_GET_PDATA(sflf);
    if (pDbtb == NULL) {
        // do notiing just rfturn fblsf
        goto rft;
    }

    d = (AwtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd())) {
        d->SfndMfssbgf(WM_AWT_COMPONENT_SETFOCUS, (WPARAM)doSftFodus, 0);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf sfs;
}

void AwtComponfnt::_Stbrt(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    AwtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        jobjfdt tbrgft = d->GftTbrgft(fnv);

        /* Disbblf window if spfdififd -- windows brf fnbblfd by dffbult. */
        jboolfbn fnbblfd = (jboolfbn)fnv->GftBoolfbnFifld(tbrgft,
                                                          AwtComponfnt::fnbblfdID);
        if (!fnbblfd) {
            ::EnbblfWindow(d->GftHWnd(), FALSE);
        }

        /* Tif pffr is now rfbdy for dbllbbdks, sindf tiis is tif lbst
         * initiblizbtion dbll
         */
        d->EnbblfCbllbbdks(TRUE);

        // Fix 4745222: wf nffd to invblidbtf rfgion sindf wf vblidbtfd it bfforf initiblizbtion.
        ::InvblidbtfRgn(d->GftHWnd(), NULL, FALSE);

        // Fix 4530093: WM_PAINT bftfr EnbblfCbllbbdks
        ::UpdbtfWindow(d->GftHWnd());

        fnv->DflftfLodblRff(tbrgft);
    }
rft:
    fnv->DflftfGlobblRff(sflf);
}

void AwtComponfnt::_BfginVblidbtf(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (AwtToolkit::IsMbinTirfbd()) {
        jobjfdt sflf = (jobjfdt)pbrbm;
        if (sflf != NULL) {
            PDATA pDbtb = JNI_GET_PDATA(sflf);
            if (pDbtb) {
                AwtComponfnt *d = (AwtComponfnt *)pDbtb;
                if (::IsWindow(d->GftHWnd())) {
                    d->SfndMfssbgf(WM_AWT_BEGIN_VALIDATE);
                }
            }
            fnv->DflftfGlobblRff(sflf);
        }
    } flsf {
        AwtToolkit::GftInstbndf().InvokfFundtion(AwtComponfnt::_BfginVblidbtf, pbrbm);
    }
}

void AwtComponfnt::_EndVblidbtf(void *pbrbm)
{
    if (AwtToolkit::IsMbinTirfbd()) {
        JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
        jobjfdt sflf = (jobjfdt)pbrbm;
        if (sflf != NULL) {
            PDATA pDbtb = JNI_GET_PDATA(sflf);
            if (pDbtb) {
                AwtComponfnt *d = (AwtComponfnt *)pDbtb;
                if (::IsWindow(d->GftHWnd())) {
                    d->SfndMfssbgf(WM_AWT_END_VALIDATE);
                }
            }
            fnv->DflftfGlobblRff(sflf);
        }
    } flsf {
        AwtToolkit::GftInstbndf().InvokfFundtion(AwtComponfnt::_EndVblidbtf, pbrbm);
    }
}

void AwtComponfnt::_UpdbtfWindow(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (AwtToolkit::IsMbinTirfbd()) {
        jobjfdt sflf = (jobjfdt)pbrbm;
        AwtComponfnt *d = NULL;
        PDATA pDbtb;
        JNI_CHECK_PEER_GOTO(sflf, rft);
        d = (AwtComponfnt *)pDbtb;
        if (::IsWindow(d->GftHWnd())) {
            ::UpdbtfWindow(d->GftHWnd());
        }
rft:
        fnv->DflftfGlobblRff(sflf);
    } flsf {
        AwtToolkit::GftInstbndf().InvokfFundtion(AwtComponfnt::_UpdbtfWindow, pbrbm);
    }
}

jlong AwtComponfnt::_AddNbtivfDropTbrgft(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    jlong rfsult = 0;
    AwtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        rfsult = (jlong)(d->CrfbtfDropTbrgft(fnv));
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    rfturn rfsult;
}

void AwtComponfnt::_RfmovfNbtivfDropTbrgft(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    AwtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        d->DfstroyDropTbrgft();
    }
rft:
    fnv->DflftfGlobblRff(sflf);
}

jintArrby AwtComponfnt::_CrfbtfPrintfdPixfls(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    CrfbtfPrintfdPixflsStrudt *dpps = (CrfbtfPrintfdPixflsStrudt *)pbrbm;
    jobjfdt sflf = dpps->domponfnt;

    jintArrby rfsult = NULL;
    AwtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        rfsult = (jintArrby)d->SfndMfssbgf(WM_AWT_CREATE_PRINTED_PIXELS, (WPARAM)dpps, 0);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf dpps;
    rfturn rfsult; // tiis rfffrfndf is globbl
}

jboolfbn AwtComponfnt::_IsObsdurfd(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    jboolfbn rfsult = JNI_FALSE;
    AwtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);

    d = (AwtComponfnt *)pDbtb;

    if (::IsWindow(d->GftHWnd()))
    {
        HWND iWnd = d->GftHWnd();
        HDC iDC = ::GftDC(iWnd);
        RECT dlipbox;
        int dbllrfsult = ::GftClipBox(iDC, &dlipbox);
        switdi(dbllrfsult) {
            dbsf NULLREGION :
                rfsult = JNI_FALSE;
                brfbk;
            dbsf SIMPLEREGION : {
                RECT windowRfdt;
                if (!::GftClifntRfdt(iWnd, &windowRfdt)) {
                    rfsult = JNI_TRUE;
                } flsf {
                    rfsult  = (jboolfbn)((dlipbox.bottom != windowRfdt.bottom)
                        || (dlipbox.lfft != windowRfdt.lfft)
                        || (dlipbox.rigit != windowRfdt.rigit)
                        || (dlipbox.top != windowRfdt.top));
                }
                brfbk;
            }
            dbsf COMPLEXREGION :
            dffbult :
                rfsult = JNI_TRUE;
                brfbk;
        }
        ::RflfbsfDC(iWnd, iDC);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    rfturn rfsult;
}

jboolfbn AwtComponfnt::_NbtivfHbndlfsWifflSdrolling(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    jboolfbn rfsult = JNI_FALSE;
    AwtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        rfsult = JNI_IS_TRUE(d->InifritsNbtivfMousfWifflBfibvior());
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    rfturn rfsult;
}

void AwtComponfnt::SftPbrfnt(void * pbrbm) {
    if (AwtToolkit::IsMbinTirfbd()) {
        AwtComponfnt** domps = (AwtComponfnt**)pbrbm;
        if ((domps[0] != NULL) && (domps[1] != NULL)) {
            HWND sflfWnd = domps[0]->GftHWnd();
            HWND pbrfntWnd = domps[1]->GftHWnd();
            if (::IsWindow(sflfWnd) && ::IsWindow(pbrfntWnd)) {
                // Siouldn't triggfr nbtivf fodus dibngf
                // (only tif proxy mby bf tif nbtivf fodus ownfr).
                ::SftPbrfnt(sflfWnd, pbrfntWnd);
            }
        }
        dflftf[] domps;
    } flsf {
        AwtToolkit::GftInstbndf().InvokfFundtion(AwtComponfnt::SftPbrfnt, pbrbm);
    }
}

void AwtComponfnt::_SftRfdtbngulbrSibpf(void *pbrbm)
{
    if (!AwtToolkit::IsMbinTirfbd()) {
        AwtToolkit::GftInstbndf().InvokfFundtion(AwtComponfnt::_SftRfdtbngulbrSibpf, pbrbm);
    } flsf {
        JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

        SftRfdtbngulbrSibpfStrudt *dbtb = (SftRfdtbngulbrSibpfStrudt *)pbrbm;
        jobjfdt sflf = dbtb->domponfnt;
        jint x1 = dbtb->x1;
        jint x2 = dbtb->x2;
        jint y1 = dbtb->y1;
        jint y2 = dbtb->y2;
        jobjfdt rfgion = dbtb->rfgion;

        AwtComponfnt *d = NULL;

        PDATA pDbtb;
        JNI_CHECK_PEER_GOTO(sflf, rft);

        d = (AwtComponfnt *)pDbtb;
        if (::IsWindow(d->GftHWnd())) {
            HRGN iRgn = NULL;

            // If bll tif pbrbms brf zfros, tif sibpf must bf simply rfsft.
            // Otifrwisf, donvfrt it into b rfgion.
            if (rfgion || x1 || x2 || y1 || y2) {
                RECT_T rfdts[256];
                RECT_T *pRfdt = rfdts;

                donst int numrfdts = RfgionToYXBbndfdRfdtbnglfs(fnv, x1, y1, x2, y2,
                        rfgion, &pRfdt, sizfof(rfdts)/sizfof(rfdts[0]));
                if (!pRfdt) {
                    // RfgionToYXBbndfdRfdtbnglfs dofsn't usf sbff_Mbllod(),
                    // so tirow tif fxdfption fxpliditly
                    tirow std::bbd_bllod();
                }

                RGNDATA *pRgnDbtb = (RGNDATA *) SAFE_SIZE_STRUCT_ALLOC(sbff_Mbllod,
                        sizfof(RGNDATAHEADER), sizfof(RECT_T), numrfdts);
                mfmdpy((BYTE*)pRgnDbtb + sizfof(RGNDATAHEADER), pRfdt, sizfof(RECT_T) * numrfdts);
                if (pRfdt != rfdts) {
                    frff(pRfdt);
                }
                pRfdt = NULL;

                RGNDATAHEADER *pRgnHdr = (RGNDATAHEADER *) pRgnDbtb;
                pRgnHdr->dwSizf = sizfof(RGNDATAHEADER);
                pRgnHdr->iTypf = RDH_RECTANGLES;
                pRgnHdr->nRgnSizf = 0;
                pRgnHdr->rdBound.top = 0;
                pRgnHdr->rdBound.lfft = 0;
                pRgnHdr->rdBound.bottom = LONG(y2 - y1);
                pRgnHdr->rdBound.rigit = LONG(x2 - x1);
                pRgnHdr->nCount = numrfdts;

                iRgn = ::ExtCrfbtfRfgion(NULL,
                        sizfof(RGNDATAHEADER) + sizfof(RECT_T) * pRgnHdr->nCount, pRgnDbtb);

                frff(pRgnDbtb);
            }

            ::SftWindowRgn(d->GftHWnd(), iRgn, TRUE);
        }

rft:
        fnv->DflftfGlobblRff(sflf);
        if (rfgion) {
            fnv->DflftfGlobblRff(rfgion);
        }

        dflftf dbtb;
    }
}

void AwtComponfnt::_SftZOrdfr(void *pbrbm) {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftZOrdfrStrudt *dbtb = (SftZOrdfrStrudt *)pbrbm;
    jobjfdt sflf = dbtb->domponfnt;
    HWND bbovf = HWND_TOP;
    if (dbtb->bbovf != 0) {
        bbovf = rfintfrprft_dbst<HWND>(dbtb->bbovf);
    }

    AwtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);

    d = (AwtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd())) {
        ::SftWindowPos(d->GftHWnd(), bbovf, 0, 0, 0, 0,
                       SWP_NOACTIVATE | SWP_NOMOVE | SWP_NOSIZE | SWP_DEFERERASE | SWP_ASYNCWINDOWPOS);
    }

rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf dbtb;
}

void AwtComponfnt::PostUngrbbEvfnt() {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jobjfdt tbrgft = GftTbrgft(fnv);
    jobjfdt fvfnt = JNU_NfwObjfdtByNbmf(fnv, "sun/bwt/UngrbbEvfnt", "(Ljbvb/bwt/Componfnt;)V",
                                        tbrgft);
    if (sbff_ExdfptionOddurrfd(fnv)) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
    }
    fnv->DflftfLodblRff(tbrgft);
    if (fvfnt != NULL) {
        SfndEvfnt(fvfnt);
        fnv->DflftfLodblRff(fvfnt);
    }
}

void AwtComponfnt::SftFodusfdWindow(HWND window)
{
    HWND old = sm_fodusfdWindow;
    sm_fodusfdWindow = window;

    AwtWindow::FodusfdWindowCibngfd(old, window);
}

/************************************************************************
 * Componfnt nbtivf mftiods
 */

fxtfrn "C" {

/**
 * Tiis mftiod is dbllfd from tif WGL pipflinf wifn it nffds to rftrifvf
 * tif HWND bssodibtfd witi b ComponfntPffr's C++ lfvfl objfdt.
 */
HWND
AwtComponfnt_GftHWnd(JNIEnv *fnv, jlong pDbtb)
{
    AwtComponfnt *p = (AwtComponfnt *)jlong_to_ptr(pDbtb);
    if (p == NULL) {
        rfturn (HWND)0;
    }
    rfturn p->GftHWnd();
}

stbtid void _GftInsfts(void* pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    GftInsftsStrudt *gis = (GftInsftsStrudt *)pbrbm;
    jobjfdt sflf = gis->window;

    gis->insfts->lfft = gis->insfts->top =
        gis->insfts->rigit = gis->insfts->bottom = 0;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    AwtComponfnt *domponfnt = (AwtComponfnt *)pDbtb;

    domponfnt->GftInsfts(gis->insfts);

  rft:
    fnv->DflftfGlobblRff(sflf);
    dflftf gis;
}

/**
 * Tiis mftiod is dbllfd from tif WGL pipflinf wifn it nffds to rftrifvf
 * tif insfts bssodibtfd witi b ComponfntPffr's C++ lfvfl objfdt.
 */
void AwtComponfnt_GftInsfts(JNIEnv *fnv, jobjfdt pffr, RECT *insfts)
{
    TRY;

    GftInsftsStrudt *gis = nfw GftInsftsStrudt;
    gis->window = fnv->NfwGlobblRff(pffr);
    gis->insfts = insfts;

    AwtToolkit::GftInstbndf().InvokfFundtion(_GftInsfts, gis);
    // globbl rffs bnd mds brf dflftfd in _UpdbtfWindow

    CATCH_BAD_ALLOC;

}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Componfnt_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;
    jdlbss inputEvfntClbzz = fnv->FindClbss("jbvb/bwt/fvfnt/InputEvfnt");
    CHECK_NULL(inputEvfntClbzz);
    jmftiodID gftButtonDownMbsksID = fnv->GftStbtidMftiodID(inputEvfntClbzz, "gftButtonDownMbsks", "()[I");
    CHECK_NULL(gftButtonDownMbsksID);
    jintArrby obj = (jintArrby)fnv->CbllStbtidObjfdtMftiod(inputEvfntClbzz, gftButtonDownMbsksID);
    jint * tmp = fnv->GftIntArrbyElfmfnts(obj, JNI_FALSE);
    CHECK_NULL(tmp);
    jsizf lfn = fnv->GftArrbyLfngti(obj);
    AwtComponfnt::mbsks = SAFE_SIZE_NEW_ARRAY(jint, lfn);
    for (int i = 0; i < lfn; i++) {
        AwtComponfnt::mbsks[i] = tmp[i];
    }
    fnv->RflfbsfIntArrbyElfmfnts(obj, tmp, 0);
    fnv->DflftfLodblRff(obj);

    /* dlbss ids */
    jdlbss pffrCls = fnv->FindClbss("sun/bwt/windows/WComponfntPffr");

    DASSERT(pffrCls);
    CHECK_NULL(pffrCls);

    /* fifld ids */
    AwtComponfnt::pffrID =
      fnv->GftFifldID(dls, "pffr", "Ljbvb/bwt/pffr/ComponfntPffr;");
    DASSERT(AwtComponfnt::pffrID);
    CHECK_NULL(AwtComponfnt::pffrID);

    AwtComponfnt::xID = fnv->GftFifldID(dls, "x", "I");
    DASSERT(AwtComponfnt::xID);
    CHECK_NULL(AwtComponfnt::xID);

    AwtComponfnt::yID = fnv->GftFifldID(dls, "y", "I");
    DASSERT(AwtComponfnt::yID);
    CHECK_NULL(AwtComponfnt::yID);

    AwtComponfnt::ifigitID = fnv->GftFifldID(dls, "ifigit", "I");
    DASSERT(AwtComponfnt::ifigitID);
    CHECK_NULL(AwtComponfnt::ifigitID);

    AwtComponfnt::widtiID = fnv->GftFifldID(dls, "widti", "I");
    DASSERT(AwtComponfnt::widtiID);
    CHECK_NULL(AwtComponfnt::widtiID);

    AwtComponfnt::visiblfID = fnv->GftFifldID(dls, "visiblf", "Z");
    DASSERT(AwtComponfnt::visiblfID);
    CHECK_NULL(AwtComponfnt::visiblfID);

    AwtComponfnt::bbdkgroundID =
        fnv->GftFifldID(dls, "bbdkground", "Ljbvb/bwt/Color;");
    DASSERT(AwtComponfnt::bbdkgroundID);
    CHECK_NULL(AwtComponfnt::bbdkgroundID);

    AwtComponfnt::forfgroundID =
        fnv->GftFifldID(dls, "forfground", "Ljbvb/bwt/Color;");
    DASSERT(AwtComponfnt::forfgroundID);
    CHECK_NULL(AwtComponfnt::forfgroundID);

    AwtComponfnt::fnbblfdID = fnv->GftFifldID(dls, "fnbblfd", "Z");
    DASSERT(AwtComponfnt::fnbblfdID);
    CHECK_NULL(AwtComponfnt::fnbblfdID);

    AwtComponfnt::pbrfntID = fnv->GftFifldID(dls, "pbrfnt", "Ljbvb/bwt/Contbinfr;");
    DASSERT(AwtComponfnt::pbrfntID);
    CHECK_NULL(AwtComponfnt::pbrfntID);

    AwtComponfnt::grbpiidsConfigID =
     fnv->GftFifldID(dls, "grbpiidsConfig", "Ljbvb/bwt/GrbpiidsConfigurbtion;");
    DASSERT(AwtComponfnt::grbpiidsConfigID);
    CHECK_NULL(AwtComponfnt::grbpiidsConfigID);

    AwtComponfnt::fodusbblfID = fnv->GftFifldID(dls, "fodusbblf", "Z");
    DASSERT(AwtComponfnt::fodusbblfID);
    CHECK_NULL(AwtComponfnt::fodusbblfID);

    AwtComponfnt::bppContfxtID = fnv->GftFifldID(dls, "bppContfxt",
                                                 "Lsun/bwt/AppContfxt;");
    DASSERT(AwtComponfnt::bppContfxtID);
    CHECK_NULL(AwtComponfnt::bppContfxtID);

    AwtComponfnt::pffrGCID = fnv->GftFifldID(pffrCls, "winGrbpiidsConfig",
                                        "Lsun/bwt/Win32GrbpiidsConfig;");
    DASSERT(AwtComponfnt::pffrGCID);
    CHECK_NULL(AwtComponfnt::pffrGCID);

    AwtComponfnt::iwndID = fnv->GftFifldID(pffrCls, "iwnd", "J");
    DASSERT(AwtComponfnt::iwndID);
    CHECK_NULL(AwtComponfnt::iwndID);

    AwtComponfnt::dursorID = fnv->GftFifldID(dls, "dursor", "Ljbvb/bwt/Cursor;");
    DASSERT(AwtComponfnt::dursorID);
    CHECK_NULL(AwtComponfnt::dursorID);

    /* mftiod ids */
    AwtComponfnt::gftFontMID =
        fnv->GftMftiodID(dls, "gftFont_NoClifntCodf", "()Ljbvb/bwt/Font;");
    DASSERT(AwtComponfnt::gftFontMID);
    CHECK_NULL(AwtComponfnt::gftFontMID);

    AwtComponfnt::gftToolkitMID =
        fnv->GftMftiodID(dls, "gftToolkitImpl", "()Ljbvb/bwt/Toolkit;");
    DASSERT(AwtComponfnt::gftToolkitMID);
    CHECK_NULL(AwtComponfnt::gftToolkitMID);

    AwtComponfnt::isEnbblfdMID = fnv->GftMftiodID(dls, "isEnbblfdImpl", "()Z");
    DASSERT(AwtComponfnt::isEnbblfdMID);
    CHECK_NULL(AwtComponfnt::isEnbblfdMID);

    AwtComponfnt::gftLodbtionOnSdrffnMID =
        fnv->GftMftiodID(dls, "gftLodbtionOnSdrffn_NoTrffLodk", "()Ljbvb/bwt/Point;");
    DASSERT(AwtComponfnt::gftLodbtionOnSdrffnMID);
    CHECK_NULL(AwtComponfnt::gftLodbtionOnSdrffnMID);

    AwtComponfnt::rfplbdfSurfbdfDbtbMID =
        fnv->GftMftiodID(pffrCls, "rfplbdfSurfbdfDbtb", "()V");
    DASSERT(AwtComponfnt::rfplbdfSurfbdfDbtbMID);
    CHECK_NULL(AwtComponfnt::rfplbdfSurfbdfDbtbMID);

    AwtComponfnt::rfplbdfSurfbdfDbtbLbtfrMID =
        fnv->GftMftiodID(pffrCls, "rfplbdfSurfbdfDbtbLbtfr", "()V");
    DASSERT(AwtComponfnt::rfplbdfSurfbdfDbtbLbtfrMID);
    CHECK_NULL(AwtComponfnt::rfplbdfSurfbdfDbtbLbtfrMID);

    AwtComponfnt::disposfLbtfrMID = fnv->GftMftiodID(pffrCls, "disposfLbtfr", "()V");
    DASSERT(AwtComponfnt::disposfLbtfrMID);
    CHECK_NULL(AwtComponfnt::disposfLbtfrMID);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * ComponfntPffr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    pSiow
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_pSiow(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_Siow, (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _Siow

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    iidf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_iidf(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_Hidf, (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _Hidf

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    fnbblf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_fnbblf(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_Enbblf, (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _Enbblf

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    disbblf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_disbblf(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_Disbblf, (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _Disbblf

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    gftLodbtionOnSdrffn
 * Signbturf: ()Ljbvb/bwt/Point;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_gftLodbtionOnSdrffn(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    jobjfdt rfsultGlobblRff = (jobjfdt)AwtToolkit::GftInstbndf().SyndCbll(
        (void*(*)(void*))AwtComponfnt::_GftLodbtionOnSdrffn, (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _GftLodbtionOnSdrffn
    if (rfsultGlobblRff != NULL)
    {
        jobjfdt rfsultLodblRff = fnv->NfwLodblRff(rfsultGlobblRff);
        fnv->DflftfGlobblRff(rfsultGlobblRff);
        rfturn rfsultLodblRff;
    }

    rfturn NULL;

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    rfsibpf
 * Signbturf: (IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_rfsibpf(JNIEnv *fnv, jobjfdt sflf,
                                            jint x, jint y, jint w, jint i)
{
    TRY;

    RfsibpfStrudt *rs = nfw RfsibpfStrudt;
    rs->domponfnt = fnv->NfwGlobblRff(sflf);
    rs->x = x;
    rs->y = y;
    rs->w = w;
    rs->i = i;

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_Rfsibpf, rs);
    // globbl rff bnd rs brf dflftfd in _Rfsibpf

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    rfsibpf
 * Signbturf: (IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_rfsibpfNoCifdk(JNIEnv *fnv, jobjfdt sflf,
                                            jint x, jint y, jint w, jint i)
{
    TRY;

    RfsibpfStrudt *rs = nfw RfsibpfStrudt;
    rs->domponfnt = fnv->NfwGlobblRff(sflf);
    rs->x = x;
    rs->y = y;
    rs->w = w;
    rs->i = i;

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_RfsibpfNoCifdk, rs);
    // globbl rff bnd rs brf dflftfd in _RfsibpfNoCifdk

    CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    nbtivfHbndlfEvfnt
 * Signbturf: (Ljbvb/bwt/AWTEvfnt;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_nbtivfHbndlfEvfnt(JNIEnv *fnv,
                                                      jobjfdt sflf,
                                                      jobjfdt fvfnt)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);
    jobjfdt fvfntGlobblRff = fnv->NfwGlobblRff(fvfnt);

    NbtivfHbndlfEvfntStrudt *nifs = nfw NbtivfHbndlfEvfntStrudt;
    nifs->domponfnt = sflfGlobblRff;
    nifs->fvfnt = fvfntGlobblRff;

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_NbtivfHbndlfEvfnt, nifs);
    // globbl rffs bnd nifs brf dflftfd in _NbtivfHbndlfEvfnt

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    _disposf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr__1disposf(JNIEnv *fnv, jobjfdt sflf)
{
    TRY_NO_HANG;

    AwtObjfdt::_Disposf(sflf);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    _sftForfground
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr__1sftForfground(JNIEnv *fnv, jobjfdt sflf,
                                                    jint rgb)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    SftColorStrudt *sds = nfw SftColorStrudt;
    sds->domponfnt = sflfGlobblRff;
    sds->rgb = rgb;

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_SftForfground, sds);
    // sflfGlobblRff bnd sds brf dflftfd in _SftForfground()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    _sftBbdkground
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr__1sftBbdkground(JNIEnv *fnv, jobjfdt sflf,
                                                    jint rgb)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    SftColorStrudt *sds = nfw SftColorStrudt;
    sds->domponfnt = sflfGlobblRff;
    sds->rgb = rgb;

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_SftBbdkground, sds);
    // sflfGlobblRff bnd sds brf dflftfd in _SftBbdkground()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    _sftFont
 * Signbturf: (Ljbvb/bwt/Font;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr__1sftFont(JNIEnv *fnv, jobjfdt sflf,
                        jobjfdt font)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);
    jobjfdt fontGlobblRff = fnv->NfwGlobblRff(font);

    SftFontStrudt *sfs = nfw SftFontStrudt;
    sfs->domponfnt = sflfGlobblRff;
    sfs->font = fontGlobblRff;

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_SftFont, sfs);
    // globbl rffs bnd sfs brf dflftfd in _SftFont()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    fodusGbinfd
 * Signbturf: (Z)
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WComponfntPffr_sftFodus
    (JNIEnv *fnv, jobjfdt sflf, jboolfbn doSftFodus)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    SftFodusStrudt *sfs = nfw SftFodusStrudt;
    sfs->domponfnt = sflfGlobblRff;
    sfs->doSftFodus = doSftFodus;

    AwtToolkit::GftInstbndf().SyndCbll(
        (void*(*)(void*))AwtComponfnt::_SftFodus, sfs);
    // globbl rffs bnd sflf brf dflftfd in _SftFodus

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    stbrt
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_stbrt(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_Stbrt, (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _Stbrt

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    bfginVblidbtf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_bfginVblidbtf(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_BfginVblidbtf, (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _BfginVblidbtf

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    fndVblidbtf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_fndVblidbtf(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_EndVblidbtf, (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _EndVblidbtf

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_updbtfWindow(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_UpdbtfWindow, (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _UpdbtfWindow

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    bddNbtivfDropTbrgft
 * Signbturf: ()L
 */

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_bddNbtivfDropTbrgft(JNIEnv *fnv,
                                                        jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    rfturn ptr_to_jlong(AwtToolkit::GftInstbndf().SyndCbll(
        (void*(*)(void*))AwtComponfnt::_AddNbtivfDropTbrgft,
        (void *)sflfGlobblRff));
    // sflfGlobblRff is dflftfd in _AddNbtivfDropTbrgft

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    rfmovfNbtivfDropTbrgft
 * Signbturf: ()V
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_rfmovfNbtivfDropTbrgft(JNIEnv *fnv,
                                                           jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(
        AwtComponfnt::_RfmovfNbtivfDropTbrgft, (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _RfmovfNbtivfDropTbrgft

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    gftTbrgftGC
 * Signbturf: ()Ljbvb/bwt/GrbpiidsConfigurbtion;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_gftTbrgftGC(JNIEnv* fnv, jobjfdt tifTiis)
{
    TRY;

    jobjfdt tbrgftObj;
    jobjfdt gd = 0;

    tbrgftObj = fnv->GftObjfdtFifld(tifTiis, AwtObjfdt::tbrgftID);
    DASSERT(tbrgftObj);

    gd = fnv->GftObjfdtFifld(tbrgftObj, AwtComponfnt::grbpiidsConfigID);
    rfturn gd;

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    drfbtfPrintfdPixfls
 * Signbturf: (IIIIII)I[
 */
JNIEXPORT jintArrby JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_drfbtfPrintfdPixfls(JNIEnv* fnv,
    jobjfdt sflf, jint srdX, jint srdY, jint srdW, jint srdH, jint blpib)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    CrfbtfPrintfdPixflsStrudt *dpps = nfw CrfbtfPrintfdPixflsStrudt;
    dpps->domponfnt = sflfGlobblRff;
    dpps->srdx = srdX;
    dpps->srdy = srdY;
    dpps->srdw = srdW;
    dpps->srdi = srdH;
    dpps->blpib = blpib;

    jintArrby globblRff = (jintArrby)AwtToolkit::GftInstbndf().SyndCbll(
        (void*(*)(void*))AwtComponfnt::_CrfbtfPrintfdPixfls, dpps);
    // sflfGlobblRff bnd dpps brf dflftfd in _CrfbtfPrintfdPixfls
    if (globblRff != NULL)
    {
        jintArrby lodblRff = (jintArrby)fnv->NfwLodblRff(globblRff);
        fnv->DflftfGlobblRff(globblRff);
        rfturn lodblRff;
    }
    flsf
    {
        rfturn NULL;
    }

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    nbtivfHbndlfsWifflSdrolling
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_nbtivfHbndlfsWifflSdrolling (JNIEnv* fnv,
    jobjfdt sflf)
{
    TRY;

    rfturn (jboolfbn)AwtToolkit::GftInstbndf().SyndCbll(
        (void *(*)(void *))AwtComponfnt::_NbtivfHbndlfsWifflSdrolling,
        fnv->NfwGlobblRff(sflf));
    // globbl rff is dflftfd in _NbtivfHbndlfsWifflSdrolling

    CATCH_BAD_ALLOC_RET(NULL);
}

/*
 * Clbss:     sun_bwt_windows_WComponfntPffr
 * Mftiod:    isObsdurfd
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_isObsdurfd(JNIEnv* fnv,
    jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    rfturn (jboolfbn)AwtToolkit::GftInstbndf().SyndCbll(
        (void*(*)(void*))AwtComponfnt::_IsObsdurfd,
        (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _IsObsdurfd

    CATCH_BAD_ALLOC_RET(NULL);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_pSftPbrfnt(JNIEnv* fnv, jobjfdt sflf, jobjfdt pbrfnt) {
    TRY;

    typfdff AwtComponfnt* PComponfnt;
    AwtComponfnt** domps = nfw PComponfnt[2];
    AwtComponfnt* domp = (AwtComponfnt*)JNI_GET_PDATA(sflf);
    AwtComponfnt* pbrfntComp = (AwtComponfnt*)JNI_GET_PDATA(pbrfnt);
    domps[0] = domp;
    domps[1] = pbrfntComp;

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::SftPbrfnt, domps);
    // domps is dflftfd in SftPbrfnt

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_sftRfdtbngulbrSibpf(JNIEnv* fnv, jobjfdt sflf,
        jint x1, jint y1, jint x2, jint y2, jobjfdt rfgion)
{
    TRY;

    SftRfdtbngulbrSibpfStrudt * dbtb = nfw SftRfdtbngulbrSibpfStrudt;
    dbtb->domponfnt = fnv->NfwGlobblRff(sflf);
    dbtb->x1 = x1;
    dbtb->x2 = x2;
    dbtb->y1 = y1;
    dbtb->y2 = y2;
    if (rfgion) {
        dbtb->rfgion = fnv->NfwGlobblRff(rfgion);
    } flsf {
        dbtb->rfgion = NULL;
    }

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_SftRfdtbngulbrSibpf, dbtb);
    // globbl rffs bnd dbtb brf dflftfd in _SftRfdtbngulbrSibpf

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WComponfntPffr_sftZOrdfr(JNIEnv* fnv, jobjfdt sflf, jlong bbovf)
{
    TRY;

    SftZOrdfrStrudt * dbtb = nfw SftZOrdfrStrudt;
    dbtb->domponfnt = fnv->NfwGlobblRff(sflf);
    dbtb->bbovf = bbovf;

    AwtToolkit::GftInstbndf().SyndCbll(AwtComponfnt::_SftZOrdfr, dbtb);
    // globbl rffs bnd dbtb brf dflftfd in _SftLowfr

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * Dibgnostid routinfs
 */

#ifdff DEBUG

void AwtComponfnt::VfrifyStbtf()
{
    if (AwtToolkit::GftInstbndf().VfrifyComponfnts() == FALSE) {
        rfturn;
    }

    if (m_dbllbbdksEnbblfd == FALSE) {
        /* Componfnt is not fully sftup yft. */
        rfturn;
    }

    /* Gft tbrgft bounds. */
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->PusiLodblFrbmf(10) < 0)
        rfturn;

    jobjfdt tbrgft = GftTbrgft(fnv);

    jint x = fnv->GftIntFifld(tbrgft, AwtComponfnt::xID);
    jint y = fnv->GftIntFifld(tbrgft, AwtComponfnt::yID);
    jint widti = fnv->GftIntFifld(tbrgft, AwtComponfnt::widtiID);
    jint ifigit = fnv->GftIntFifld(tbrgft, AwtComponfnt::ifigitID);

    /* Convfrt tbrgft origin to bbsolutf doordinbtfs */
    wiilf (TRUE) {

        jobjfdt pbrfnt = fnv->GftObjfdtFifld(tbrgft, AwtComponfnt::pbrfntID);
        if (pbrfnt == NULL) {
            brfbk;
        }
        x += fnv->GftIntFifld(pbrfnt, AwtComponfnt::xID);
        y += fnv->GftIntFifld(pbrfnt, AwtComponfnt::yID);

        /* If tiis domponfnt ibs insfts, fbdtor tifm in, but ignorf
         * top-lfvfl windows.
         */
        jobjfdt pbrfnt2 = fnv->GftObjfdtFifld(pbrfnt, AwtComponfnt::pbrfntID);
        if (pbrfnt2 != NULL) {
            jobjfdt pffr = GftPffrForTbrgft(fnv, pbrfnt);
            if (pffr != NULL &&
                JNU_IsInstbndfOfByNbmf(fnv, pffr,
                                       "sun/bwt/windows/WPbnflPffr") > 0) {
                jobjfdt insfts =
                    JNU_CbllMftiodByNbmf(fnv, NULL, pffr,"insfts",
                                         "()Ljbvb/bwt/Insfts;").l;
                x += (fnv)->GftIntFifld(insfts, AwtInsfts::lfftID);
                y += (fnv)->GftIntFifld(insfts, AwtInsfts::topID);
            }
        }
        fnv->DflftfLodblRff(tbrgft);
        tbrgft = pbrfnt;
    }

    // Tfst wiftifr domponfnt's bounds mbtdi tif nbtivf window's
    RECT rfdt;
    VERIFY(::GftWindowRfdt(GftHWnd(), &rfdt));
#if 0
    DASSERT( (x == rfdt.lfft) &&
            (y == rfdt.top) &&
            (widti == (rfdt.rigit-rfdt.lfft)) &&
            (ifigit == (rfdt.bottom-rfdt.top)) );
#flsf
    BOOL fSizfVblid = ( (x == rfdt.lfft) &&
            (y == rfdt.top) &&
            (widti == (rfdt.rigit-rfdt.lfft)) &&
            (ifigit == (rfdt.bottom-rfdt.top)) );
#fndif

    // Sff if visiblf stbtf mbtdifs
    BOOL wndVisiblf = ::IsWindowVisiblf(GftHWnd());
    jboolfbn tbrgftVisiblf;
    // To bvoid possibly running dlifnt dodf on tif toolkit tirfbd, don't
    // do tif following difdk if wf'rf running on tif toolkit tirfbd.
    if (AwtToolkit::MbinTirfbd() != ::GftCurrfntTirfbdId()) {
        tbrgftVisiblf = JNU_CbllMftiodByNbmf(fnv, NULL, GftTbrgft(fnv),
                                                  "isSiowing", "()Z").z;
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    } flsf {
        tbrgftVisiblf = wndVisiblf ? 1 : 0;
    }
#if 0
    DASSERT( (tbrgftVisiblf && wndVisiblf) ||
            (!tbrgftVisiblf && !wndVisiblf) );
#flsf
    BOOL fVisiblfVblid = ( (tbrgftVisiblf && wndVisiblf) ||
            (!tbrgftVisiblf && !wndVisiblf) );
#fndif

    // Cifdk fnbblfd stbtf
    BOOL wndEnbblfd = ::IsWindowEnbblfd(GftHWnd());
    jboolfbn fnbblfd = (jboolfbn)fnv->GftBoolfbnFifld(tbrgft,
                                                      AwtComponfnt::fnbblfdID);
#if 0
    DASSERT( (fnbblfd && wndEnbblfd) ||
            (!fnbblfd && !wndEnbblfd) );
#flsf
    BOOL fEnbblfdVblid = ((fnbblfd && wndEnbblfd) ||
                          (!(fnbblfd && !wndEnbblfd) ));

    if (!fSizfVblid || !fVisiblfVblid || !fEnbblfdVblid) {
        printf("AwtComponfnt::VblidbtfStbtf() fbilfd:\n");
        // To bvoid possibly running dlifnt dodf on tif toolkit tirfbd, don't
        // do tif following dbll if wf'rf running on tif toolkit tirfbd.
        if (AwtToolkit::MbinTirfbd() != ::GftCurrfntTirfbdId()) {
            jstring tbrgftStr =
                (jstring)JNU_CbllMftiodByNbmf(fnv, NULL, GftTbrgft(fnv),
                                              "gftNbmf",
                                              "()Ljbvb/lbng/String;").l;
            DASSERT(!sbff_ExdfptionOddurrfd(fnv));
            LPCWSTR tbrgftStrW = JNU_GftStringPlbtformCibrs(fnv, tbrgftStr, NULL);
            printf("\t%S\n", tbrgftStrW);
            JNU_RflfbsfStringPlbtformCibrs(fnv, tbrgftStr, tbrgftStrW);
        }
        printf("\twbs:       [%d,%d,%dx%d]\n", x, y, widti, ifigit);
        if (!fSizfVblid) {
            printf("\tsiould bf: [%d,%d,%dx%d]\n", rfdt.lfft, rfdt.top,
                   rfdt.rigit-rfdt.lfft, rfdt.bottom-rfdt.top);
        }
        if (!fVisiblfVblid) {
            printf("\tsiould bf: %s\n",
                   (tbrgftVisiblf) ? "visiblf" : "iiddfn");
        }
        if (!fEnbblfdVblid) {
            printf("\tsiould bf: %s\n",
                   fnbblfd ? "fnbblfd" : "disbblfd");
        }
    }
#fndif
    fnv->PopLodblFrbmf(0);
}
#fndif //DEBUG

// Mftiods for globblly mbnbgfd DC list

/**
 * Add b nfw DC to tif DC list for tiis domponfnt.
 */
void DCList::AddDC(HDC iDC, HWND iWnd)
{
    DCItfm *nfwItfm = nfw DCItfm;
    nfwItfm->iDC = iDC;
    nfwItfm->iWnd = iWnd;
    AddDCItfm(nfwItfm);
}

void DCList::AddDCItfm(DCItfm *nfwItfm)
{
    listLodk.Entfr();
    nfwItfm->nfxt = ifbd;
    ifbd = nfwItfm;
    listLodk.Lfbvf();
}

/**
 * Givfn b DC, rfmovf it from tif DC list bnd rfturn
 * TRUE if it fxists on tif durrfnt list.  Otifrwisf
 * rfturn FALSE.
 * A DC mby not fxist on tif list bfdbusf it ibs blrfbdy
 * bffn rflfbsfd flsfwifrf (for fxbmplf, tif window
 * dfstrudtion prodfss mby rflfbsf b DC wiilf b rfndfring
 * tirfbd mby blso wbnt to rflfbsf b DC wifn it notidfs tibt
 * its DC is obsolftf for tif durrfnt window).
 */
DCItfm *DCList::RfmovfDC(HDC iDC)
{
    listLodk.Entfr();
    DCItfm **prfvPtrPtr = &ifbd;
    DCItfm *listPtr = ifbd;
    wiilf (listPtr) {
        DCItfm *nfxtPtr = listPtr->nfxt;
        if (listPtr->iDC == iDC) {
            *prfvPtrPtr = nfxtPtr;
            brfbk;
        }
        prfvPtrPtr = &listPtr->nfxt;
        listPtr = nfxtPtr;
    }
    listLodk.Lfbvf();
    rfturn listPtr;
}

/**
 * Rfmovf bll DCs from tif DC list wiidi brf bssodibtfd witi
 * tif sbmf window bs iWnd.  Rfturn tif list of tiosf
 * DC's to tif dbllfr (wiidi will tifn probbbly wbnt to
 * dbll RflfbsfDC() for tif rfturnfd DCs).
 */
DCItfm *DCList::RfmovfAllDCs(HWND iWnd)
{
    listLodk.Entfr();
    DCItfm **prfvPtrPtr = &ifbd;
    DCItfm *listPtr = ifbd;
    DCItfm *nfwListPtr = NULL;
    BOOL rft = FALSE;
    wiilf (listPtr) {
        DCItfm *nfxtPtr = listPtr->nfxt;
        if (listPtr->iWnd == iWnd) {
            *prfvPtrPtr = nfxtPtr;
            listPtr->nfxt = nfwListPtr;
            nfwListPtr = listPtr;
        } flsf {
            prfvPtrPtr = &listPtr->nfxt;
        }
        listPtr = nfxtPtr;
    }
    listLodk.Lfbvf();
    rfturn nfwListPtr;
}


/**
 * Rfblizf pblfttfs of bll fxisting HDC objfdts
 */
void DCList::RfblizfPblfttfs(int sdrffn)
{
    listLodk.Entfr();
    DCItfm *listPtr = ifbd;
    wiilf (listPtr) {
        AwtWin32GrbpiidsDfvidf::RfblizfPblfttf(listPtr->iDC, sdrffn);
        listPtr = listPtr->nfxt;
    }
    listLodk.Lfbvf();
}

void MovfDCToPbssivfList(HDC iDC) {
    DCItfm *rfmovfdDC;
    if ((rfmovfdDC = bdtivfDCList.RfmovfDC(iDC)) != NULL) {
        pbssivfDCList.AddDCItfm(rfmovfdDC);
    }
}

void RflfbsfDCList(HWND iwnd, DCList &list) {
    DCItfm *rfmovfdDCs = list.RfmovfAllDCs(iwnd);
    wiilf (rfmovfdDCs) {
        DCItfm *tmpDCList = rfmovfdDCs;
        DASSERT(::GftObjfdtTypf(tmpDCList->iDC) == OBJ_DC);
        int rftVbluf = ::RflfbsfDC(tmpDCList->iWnd, tmpDCList->iDC);
        VERIFY(rftVbluf != 0);
        if (rftVbluf != 0) {
            // Vblid RflfbsfDC dbll; nffd to dfdrfmfnt GDI objfdt dountfr
            AwtGDIObjfdt::Dfdrfmfnt();
        }
        rfmovfdDCs = rfmovfdDCs->nfxt;
        dflftf tmpDCList;
    }
}

