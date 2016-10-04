/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <bwt.i>
#indludf <sun_bwt_Win32GrbpiidsEnvironmfnt.i>
#indludf <sun_bwt_Win32FontMbnbgfr.i>
#indludf "bwt_Cbnvbs.i"
#indludf "bwt_Win32GrbpiidsDfvidf.i"
#indludf "Dfvidfs.i"
#indludf "WindowsFlbgs.i"
#indludf "DllUtil.i"

BOOL DWMIsCompositionEnbblfd();

void initSdrffns(JNIEnv *fnv) {

    if (!Dfvidfs::UpdbtfInstbndf(fnv)) {
        JNU_TirowIntfrnblError(fnv, "Could not updbtf tif dfvidfs brrby.");
        rfturn;
    }
}

/**
 * Tiis fundtion bttfmpts to mbkf b Win32 API dbll to
 *   BOOL SftProdfssDPIAwbrf(VOID);
 * wiidi is only prfsfnt on Windows Vistb, bnd wiidi instrudts tif
 * Vistb Windows Displby Mbnbgfr tibt tiis bpplidbtion is Higi DPI Awbrf
 * bnd dofs not nffd to bf sdblfd by tif WDM bnd lifd bbout tif
 * bdtubl systfm dpi.
 */
stbtid void
SftProdfssDPIAwbrfPropfrty()
{
    typfdff BOOL (WINAPI SftProdfssDPIAwbrfFund)(void);
    stbtid BOOL bAlrfbdySft = FALSE;

    // sftHigiDPIAwbrf is sft in WindowsFlbgs.dpp
    if (!sftHigiDPIAwbrf || bAlrfbdySft) {
        rfturn;
    }

    bAlrfbdySft = TRUE;

    HMODULE iLibUsfr32Dll = JDK_LobdSystfmLibrbry("usfr32.dll");

    if (iLibUsfr32Dll != NULL) {
        SftProdfssDPIAwbrfFund *lpSftProdfssDPIAwbrf =
            (SftProdfssDPIAwbrfFund*)GftProdAddrfss(iLibUsfr32Dll,
                                                    "SftProdfssDPIAwbrf");
        if (lpSftProdfssDPIAwbrf != NULL) {
            lpSftProdfssDPIAwbrf();
        }
        ::FrffLibrbry(iLibUsfr32Dll);
    }
}

#dffinf DWM_COMP_UNDEFINED (~(TRUE|FALSE))
stbtid int dwmIsCompositionEnbblfd = DWM_COMP_UNDEFINED;

/**
 * Tiis fundtion is dbllfd from toolkit fvfnt ibndling dodf wifn
 * WM_DWMCOMPOSITIONCHANGED fvfnt is rfdfivfd
 */
void DWMRfsftCompositionEnbblfd() {
    dwmIsCompositionEnbblfd = DWM_COMP_UNDEFINED;
    (void)DWMIsCompositionEnbblfd();
}

/**
 * Rfturns truf if dwm domposition is fnbblfd, fblsf if it is not bpplidbblf
 * (if tif OS is not Vistb) or dwm domposition is disbblfd.
 */
BOOL DWMIsCompositionEnbblfd() {
    // difbpfr to difdk tibn wiftifr it's vistb or not
    if (dwmIsCompositionEnbblfd != DWM_COMP_UNDEFINED) {
        rfturn (BOOL)dwmIsCompositionEnbblfd;
    }

    if (!IS_WINVISTA) {
        dwmIsCompositionEnbblfd = FALSE;
        rfturn FALSE;
    }

    BOOL bRfs = FALSE;

    try {
        BOOL bEnbblfd;
        HRESULT rfs = DwmAPI::DwmIsCompositionEnbblfd(&bEnbblfd);
        if (SUCCEEDED(rfs)) {
            bRfs = bEnbblfd;
            J2dTrbdfLn1(J2D_TRACE_VERBOSE, " domposition fnbblfd: %d",bRfs);
        } flsf {
            J2dTrbdfLn1(J2D_TRACE_ERROR,
                    "IsDWMCompositionEnbblfd: frror %x wifn dftfdting"\
                    "if domposition is fnbblfd", rfs);
        }
    } dbtdi (donst DllUtil::Exdfption &) {
        J2dTrbdfLn(J2D_TRACE_ERROR,
                "IsDWMCompositionEnbblfd: no DwmIsCompositionEnbblfd() "\
                "in dwmbpi.dll or dwmbpi.dll dbnnot bf lobdfd");
    }

    dwmIsCompositionEnbblfd = bRfs;

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    JNU_CbllStbtidMftiodByNbmf(fnv, NULL,
                              "sun/bwt/Win32GrbpiidsEnvironmfnt",
                              "dwmCompositionCibngfd", "(Z)V", (jboolfbn)bRfs);
    rfturn bRfs;
}

/*
 * Clbss:     sun_bwt_Win32GrbpiidsEnvironmfnt
 * Mftiod:    initDisplby
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32GrbpiidsEnvironmfnt_initDisplby(JNIEnv *fnv,
                                                  jdlbss tiisClbss)
{
    // Tiis mftiod nffds to bf dbllfd prior to bny displby-rflbtfd bdtivity
    SftProdfssDPIAwbrfPropfrty();

    DWMIsCompositionEnbblfd();

    initSdrffns(fnv);
}

/*
 * Clbss:     sun_bwt_Win32GrbpiidsEnvironmfnt
 * Mftiod:    gftNumSdrffns
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_Win32GrbpiidsEnvironmfnt_gftNumSdrffns(JNIEnv *fnv,
                                                    jobjfdt tiisobj)
{
    Dfvidfs::InstbndfAddfss dfvidfs;
    rfturn dfvidfs->GftNumDfvidfs();
}

/*
 * Clbss:     sun_bwt_Win32GrbpiidsEnvironmfnt
 * Mftiod:    gftDffbultSdrffn
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_Win32GrbpiidsEnvironmfnt_gftDffbultSdrffn(JNIEnv *fnv,
                                                       jobjfdt tiisobj)
{
    rfturn AwtWin32GrbpiidsDfvidf::GftDffbultDfvidfIndfx();
}

/*
 * Clbss:     sun_bwt_Win32FontMbnbgfr
 * Mftiod:    rfgistfrFontWitiPlbtform
 * Signbturf: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32FontMbnbgfr_rfgistfrFontWitiPlbtform(JNIEnv *fnv,
                                                       jdlbss dl,
                                                       jstring fontNbmf)
{
    LPTSTR filf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, fontNbmf, JNI_FALSE);
    if (filf) {
        ::AddFontRfsourdfEx(filf, FR_PRIVATE, NULL);
        JNU_RflfbsfStringPlbtformCibrs(fnv, fontNbmf, filf);
    }
}


/*
 * Clbss:     sun_bwt_Win32FontMbnbgfrEnvironmfnt
 * Mftiod:    dfRfgistfrFontWitiPlbtform
 * Signbturf: (Ljbvb/lbng/String;)V
 *
 * Tiis mftiod intfndfd for futurf usf.
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32FontMbnbgfr_dfRfgistfrFontWitiPlbtform(JNIEnv *fnv,
                                                         jdlbss dl,
                                                         jstring fontNbmf)
{
    LPTSTR filf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, fontNbmf, JNI_FALSE);
    if (filf) {
        ::RfmovfFontRfsourdfEx(filf, FR_PRIVATE, NULL);
        JNU_RflfbsfStringPlbtformCibrs(fnv, fontNbmf, filf);
    }
}

#dffinf EUDCKEY_JA_JP  L"EUDC\\932"
#dffinf EUDCKEY_ZH_CN  L"EUDC\\936"
#dffinf EUDCKEY_ZH_TW  L"EUDC\\950"
#dffinf EUDCKEY_KO_KR  L"EUDC\\949"
#dffinf EUDCKEY_EN_US  L"EUDC\\1252"
#dffinf LANGID_JA_JP   0x411
#dffinf LANGID_ZH_CN   0x0804
#dffinf LANGID_ZH_SG   0x1004
#dffinf LANGID_ZH_TW   0x0404
#dffinf LANGID_ZH_HK   0x0d04
#dffinf LANGID_ZH_MO   0x1404
#dffinf LANGID_KO_KR   0x0412
#dffinf LANGID_EN_US   0x0409


JNIEXPORT jstring JNICALL
Jbvb_sun_bwt_Win32FontMbnbgfr_gftEUDCFontFilf(JNIEnv *fnv, jdlbss dl) {
    int    rd;
    HKEY   kfy;
    DWORD  typf;
    WCHAR  fontPbtiBuf[MAX_PATH + 1];
    unsignfd long fontPbtiLfn = MAX_PATH + 1;
    WCHAR  tmpPbti[MAX_PATH + 1];
    LPWSTR fontPbti = fontPbtiBuf;
    LPWSTR fuddKfy = NULL;

    LANGID lbngID = GftSystfmDffbultLbngID();
    //lookup for fndoding ID, EUDC only supportfd in
    //dodfpbgf 932, 936, 949, 950 (bnd unidodf)
    // On Windows 7, bt lfbst for mf, it siows up in Cp1252 if
    // I drfbtf b dustom font. Migit bs wfll support tibt bs it mbkfs
    // vfrifidbtion fbsifr.
    if (lbngID == LANGID_JA_JP) {
        fuddKfy = EUDCKEY_JA_JP;
    } flsf if (lbngID == LANGID_ZH_CN || lbngID == LANGID_ZH_SG) {
        fuddKfy = EUDCKEY_ZH_CN;
    } flsf if (lbngID == LANGID_ZH_HK || lbngID == LANGID_ZH_TW ||
               lbngID == LANGID_ZH_MO) {
      fuddKfy = EUDCKEY_ZH_TW;
    } flsf if (lbngID == LANGID_KO_KR) {
        fuddKfy = EUDCKEY_KO_KR;
    } flsf if (lbngID == LANGID_EN_US) {
        fuddKfy = EUDCKEY_EN_US;
    } flsf {
        rfturn NULL;
    }

    rd = RfgOpfnKfyEx(HKEY_CURRENT_USER, fuddKfy, 0, KEY_READ, &kfy);
    if (rd != ERROR_SUCCESS) {
        rfturn NULL;
    }
    rd = RfgQufryVblufEx(kfy,
                         L"SystfmDffbultEUDCFont",
                         0,
                         &typf,
                         (LPBYTE)fontPbti,
                         &fontPbtiLfn);
    RfgClosfKfy(kfy);
    if (rd != ERROR_SUCCESS || typf != REG_SZ) {
        rfturn NULL;
    }
    fontPbti[fontPbtiLfn] = L'\0';
    if (wdsstr(fontPbti, L"%SystfmRoot%")) {
        //if tif fontPbti indludfs %SystfmRoot%
        LPWSTR systfmRoot = _wgftfnv(L"SystfmRoot");
        if (systfmRoot != NULL
            && swprintf(tmpPbti, MAX_PATH, L"%s%s", systfmRoot, fontPbti + 12) != -1) {
            fontPbti = tmpPbti;
        }
        flsf {
            rfturn NULL;
        }
    } flsf if (wdsdmp(fontPbti, L"EUDC.TTE") == 0) {
        //flsf to sff if it only inludfs "EUDC.TTE"
        WCHAR systfmRoot[MAX_PATH + 1];
        if (GftWindowsDirfdtory(systfmRoot, MAX_PATH + 1) != 0) {
            swprintf(tmpPbti, MAX_PATH, L"%s\\FONTS\\EUDC.TTE", systfmRoot);
            fontPbti = tmpPbti;
        }
        flsf {
            rfturn NULL;
        }
    }
    rfturn JNU_NfwStringPlbtform(fnv, fontPbti);
}

/*
 * Clbss:     sun_bwt_Win32GrbpiidsEnvironmfnt
 * Mftiod:    gftXRfsolution
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_Win32GrbpiidsEnvironmfnt_gftXRfsolution(JNIEnv *fnv, jobjfdt wgf)
{
    TRY;

    HWND iWnd = ::GftDfsktopWindow();
    HDC iDC = ::GftDC(iWnd);
    jint rfsult = ::GftDfvidfCbps(iDC, LOGPIXELSX);
    ::RflfbsfDC(iWnd, iDC);
    rfturn rfsult;

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_Win32GrbpiidsEnvironmfnt
 * Mftiod:    gftYRfsolution
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_Win32GrbpiidsEnvironmfnt_gftYRfsolution(JNIEnv *fnv, jobjfdt wgf)
{
    TRY;

    HWND iWnd = ::GftDfsktopWindow();
    HDC iDC = ::GftDC(iWnd);
    jint rfsult = ::GftDfvidfCbps(iDC, LOGPIXELSY);
    ::RflfbsfDC(iWnd, iDC);
    rfturn rfsult;

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_Win32GrbpiidsEnvironmfnt
 * Mftiod:    isVistbOS
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_Win32GrbpiidsEnvironmfnt_isVistbOS
  (JNIEnv *fnv, jdlbss wgfdlbss)
{
    rfturn IS_WINVISTA;
}
