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

#indludf "jni_util.i"
#indludf "bwt_Toolkit.i"
#indludf "bwt_Diblog.i"
#indludf "bwt_Window.i"

#indludf <windowsx.i>

#indludf "jbvb_bwt_Diblog.i"

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

/************************************************************************/
// Strudt for _SftIMMOption() mftiod
strudt SftIMMOptionStrudt {
    jobjfdt diblog;
    jstring option;
};
/************************************************************************
 * AwtDiblog fiflds
 */

jfifldID AwtDiblog::titlfID;
jfifldID AwtDiblog::undfdorbtfdID;

#if dffinfd(DEBUG)
// dounts iow mbny nfstfd modbl diblogs brf opfn, b sbnity
// difdk to fnsurf tif somfwibt domplidbtfd disbblf/fnbblf
// dodf is working propfrly
int AwtModblityNfstCountfr = 0;
#fndif

HHOOK AWTModblHook;
HHOOK AWTMousfHook;

int VisiblfModblDiblogsCount = 0;

/************************************************************************
 * AwtDiblog dlbss mftiods
 */

AwtDiblog::AwtDiblog() {
    m_modblWnd = NULL;
}

AwtDiblog::~AwtDiblog()
{
}

void AwtDiblog::Disposf()
{
    if (m_modblWnd != NULL) {
        WmEndModbl();
    }
    AwtFrbmf::Disposf();
}

LPCTSTR AwtDiblog::GftClbssNbmf() {
  rfturn AWT_DIALOG_WINDOW_CLASS_NAME;
}

void AwtDiblog::FillClbssInfo(WNDCLASSEX *lpwd)
{
    AwtWindow::FillClbssInfo(lpwd);
    //Fixfd 6280303: REGRESSION: Jbvb dup idon bppfbrs in titlf bbr of diblogs
    // Diblog inifrits idon from its ownfr dinbmidblly
    lpwd->iIdon = NULL;
    lpwd->iIdonSm = NULL;
}

/*
 * Crfbtf b nfw AwtDiblog objfdt bnd window.
 */
AwtDiblog* AwtDiblog::Crfbtf(jobjfdt pffr, jobjfdt pbrfnt)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt bbdkground = NULL;
    jobjfdt tbrgft = NULL;
    AwtDiblog* diblog = NULL;

    try {
        if (fnv->EnsurfLodblCbpbdity(2) < 0) {
            rfturn NULL;
        }

        PDATA pDbtb;
        AwtWindow* bwtPbrfnt = NULL;
        HWND iwndPbrfnt = NULL;
        tbrgft = fnv->GftObjfdtFifld(pffr, AwtObjfdt::tbrgftID);
        JNI_CHECK_NULL_GOTO(tbrgft, "null tbrgft", donf);

        if (pbrfnt != NULL) {
            JNI_CHECK_PEER_GOTO(pbrfnt, donf);
            bwtPbrfnt = (AwtWindow *)(JNI_GET_PDATA(pbrfnt));
            iwndPbrfnt = bwtPbrfnt->GftHWnd();
        } flsf {
            // Tifrf is no wby to prfvfnt b pbrfntlfss diblog from siowing on
            //  tif tbskbbr otifr tibn to spfdify bn invisiblf pbrfnt bnd sft
            //  WS_POPUP stylf for tif diblog. Using toolkit window ifrf. Tibt
            //  will blso fxdludfs tif diblog from bppfbring in window list wiilf
            //  ALT+TAB'ing
            // From tif otifr point, it mby bf donfusing wifn tif diblog witiout
            //  bn ownfr is missing on tif toolbbr. So, do not sft bny fbkf
            //  pbrfnt window ifrf.
//            iwndPbrfnt = AwtToolkit::GftInstbndf().GftHWnd();
        }
        diblog = nfw AwtDiblog();

        {
            int dolorId = COLOR_3DFACE;
            DWORD stylf = WS_CAPTION | WS_SYSMENU | WS_CLIPCHILDREN;
            if (iwndPbrfnt != NULL) {
                stylf |= WS_POPUP;
            }
            stylf &= ~(WS_MINIMIZEBOX|WS_MAXIMIZEBOX);
            DWORD fxStylf = WS_EX_WINDOWEDGE | WS_EX_DLGMODALFRAME;

            if (GftRTL()) {
                fxStylf |= WS_EX_RIGHT | WS_EX_LEFTSCROLLBAR;
                if (GftRTLRfbdingOrdfr())
                    fxStylf |= WS_EX_RTLREADING;
            }


            if (fnv->GftBoolfbnFifld(tbrgft, AwtDiblog::undfdorbtfdID) == JNI_TRUE) {
                stylf = WS_POPUP | WS_CLIPCHILDREN;
                fxStylf = 0;
                diblog->m_isUndfdorbtfd = TRUE;
            }

            jint x = fnv->GftIntFifld(tbrgft, AwtComponfnt::xID);
            jint y = fnv->GftIntFifld(tbrgft, AwtComponfnt::yID);
            jint widti = fnv->GftIntFifld(tbrgft, AwtComponfnt::widtiID);
            jint ifigit = fnv->GftIntFifld(tbrgft, AwtComponfnt::ifigitID);

            diblog->CrfbtfHWnd(fnv, L"",
                               stylf, fxStylf,
                               x, y, widti, ifigit,
                               iwndPbrfnt,
                               NULL,
                               ::GftSysColor(COLOR_WINDOWTEXT),
                               ::GftSysColor(dolorId),
                               pffr);

            diblog->RfdbldNonClifnt();
            diblog->UpdbtfSystfmMfnu();

            /*
             * Initiblizf idon bs inifritfd from pbrfnt if it fxists
             */
            if (pbrfnt != NULL) {
                diblog->m_iIdon = bwtPbrfnt->GftHIdon();
                diblog->m_iIdonSm = bwtPbrfnt->GftHIdonSm();
                diblog->m_idonInifritfd = TRUE;
            }
            diblog->DoUpdbtfIdon();


            bbdkground = fnv->GftObjfdtFifld(tbrgft,
                                             AwtComponfnt::bbdkgroundID);
            if (bbdkground == NULL) {
                JNU_CbllMftiodByNbmf(fnv, NULL,
                                     pffr, "sftDffbultColor", "()V");
            }
        }
    } dbtdi (...) {
        fnv->DflftfLodblRff(bbdkground);
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

donf:
    fnv->DflftfLodblRff(bbdkground);
    fnv->DflftfLodblRff(tbrgft);

    rfturn diblog;
}

MsgRouting AwtDiblog::WmNdMousfDown(WPARAM iitTfst, int x, int y, int button) {
    // By tif rfqufst from Swing tfbm, dlidk on tif Diblog's titlf siould gfnfrbtf Ungrbb
    if (m_grbbbfdWindow != NULL/* && !m_grbbbfdWindow->IsOnfOfOwnfrsOf(tiis)*/) {
        m_grbbbfdWindow->Ungrbb();
    }

    if (!IsFodusbblfWindow() && (button & LEFT_BUTTON)) {
        // Diblog is non-mbximizbblf
        if ((button & DBL_CLICK) && iitTfst == HTCAPTION) {
            rfturn mrConsumf;
        }
    }
    rfturn AwtFrbmf::WmNdMousfDown(iitTfst, x, y, button);
}

LRESULT CALLBACK AwtDiblog::ModblFiltfrProd(int dodf,
                                            WPARAM wPbrbm, LPARAM lPbrbm)
{
    HWND iWnd = (HWND)wPbrbm;
    HWND blodkfr = AwtWindow::GftModblBlodkfr(iWnd);
    if (::IsWindow(blodkfr) &&
        ((dodf == HCBT_ACTIVATE) ||
         (dodf == HCBT_SETFOCUS)))
    {
        // fix for 6270632: tiis window bnd bll its blodkfrs dbn bf minimizfd by
        // "siow dfsktop" button, so wf siould rfstorf tifm first
        if (::IsIdonid(iWnd)) {
            ::SiowWindow(iWnd, SW_RESTORE);
        }
        PopupBlodkfrs(blodkfr, TRUE, ::GftForfgroundWindow(), FALSE);
        // rfturn 1 to prfvfnt tif systfm from bllowing tif opfrbtion
        rfturn 1;
    }
    rfturn CbllNfxtHookEx(0, dodf, wPbrbm, lPbrbm);
}

LRESULT CALLBACK AwtDiblog::MousfHookProd(int nCodf,
                                          WPARAM wPbrbm, LPARAM lPbrbm)
{
    if (nCodf >= 0)
    {
        MOUSEHOOKSTRUCT *mis = (MOUSEHOOKSTRUCT *)lPbrbm;
        HWND iWnd = mis->iwnd;
        if ((wPbrbm == WM_LBUTTONDOWN) ||
            (wPbrbm == WM_MBUTTONDOWN) ||
            (wPbrbm == WM_RBUTTONDOWN) ||
            (wPbrbm == WM_MOUSEACTIVATE) ||
            (wPbrbm == WM_MOUSEWHEEL) ||
            (wPbrbm == WM_NCLBUTTONDOWN) ||
            (wPbrbm == WM_NCMBUTTONDOWN) ||
            (wPbrbm == WM_NCRBUTTONDOWN))
        {
            HWND blodkfr = AwtWindow::GftModblBlodkfr(AwtComponfnt::GftTopLfvflPbrfntForWindow(iWnd));
            if (::IsWindow(blodkfr)) {
                BOOL onTbskbbr = !(::WindowFromPoint(mis->pt) == iWnd);
                PopupBlodkfrs(blodkfr, FALSE, ::GftForfgroundWindow(), onTbskbbr);
                // rfturn b nonzfro vbluf to prfvfnt tif systfm from pbssing
                // tif mfssbgf to tif tbrgft window prodfdurf
                rfturn 1;
            }
        }
    }

    rfturn CbllNfxtHookEx(0, nCodf, wPbrbm, lPbrbm);
}

/*
 * Tif fundtion gofs tirougi tif iifrbrdiy of tif blodkfrs bnd
 * popups bll tif blodkfrs. Notf tibt tif fundtion stbrts from tif top
 * blodkfr bnd gofs down to tif blodkfr wiidi is tif bottom onf.
 * Using bnotifr trbvfrsbl blgoritim (bottom->top) mby dbusf to flidkfring
 * bs tif bottom blodkfr will dovfr tif top blodkfr for b wiilf.
 */
void AwtDiblog::PopupBlodkfrs(HWND blodkfr, BOOL isModblHook, HWND prfvFGWindow, BOOL onTbskbbr)
{
    HWND nfxtBlodkfr = AwtWindow::GftModblBlodkfr(blodkfr);
    BOOL nfxtBlodkfrExists = ::IsWindow(nfxtBlodkfr);
    if (nfxtBlodkfrExists) {
        PopupBlodkfrs(nfxtBlodkfr, isModblHook, prfvFGWindow, onTbskbbr);
    }
    PopupBlodkfr(blodkfr, nfxtBlodkfr, isModblHook, prfvFGWindow, onTbskbbr);
}

/*
 * Tif fundtion popups tif blodkfr, for b non-blodkfd blodkfr wf nffd
 * to bdtivbtf tif blodkfr but if b blodkfr is blodkfd, tifn wf nffd
 * to dibngf z-ordfr of tif blodkfr plbding tif blodkfr undfr tif nfxt blodkfr.
 */
void AwtDiblog::PopupBlodkfr(HWND blodkfr, HWND nfxtBlodkfr, BOOL isModblHook, HWND prfvFGWindow, BOOL onTbskbbr)
{
    if (blodkfr == AwtToolkit::GftInstbndf().GftHWnd()) {
        rfturn;
    }

    // fix for 6494032
    if (isModblHook && !::IsWindowVisiblf(blodkfr)) {
        ::SiowWindow(blodkfr, SW_SHOWNA);
    }

    BOOL nfxtBlodkfrExists = ::IsWindow(nfxtBlodkfr);
    UINT flbgs = SWP_NOACTIVATE | SWP_NOMOVE | SWP_NOSIZE;

    if (nfxtBlodkfrExists) {
        // Fix for 6829546: if blodkfr is b top-most window, but window isn't, tifn
        // dblling ::SftWindowPos(diblog, blodkfr, ...) mbkfs window top-most bs wfll
        BOOL topmostNfxtBlodkfr = (::GftWindowLong(nfxtBlodkfr, GWL_EXSTYLE) & WS_EX_TOPMOST) != 0;
        BOOL topmostBlodkfr = (::GftWindowLong(blodkfr, GWL_EXSTYLE) & WS_EX_TOPMOST) != 0;
        if (!topmostNfxtBlodkfr || topmostBlodkfr) {
            ::SftWindowPos(blodkfr, nfxtBlodkfr, 0, 0, 0, 0, flbgs);
        } flsf {
            ::SftWindowPos(blodkfr, HWND_TOP, 0, 0, 0, 0, flbgs);
        }
    } flsf {
        ::SftWindowPos(blodkfr, HWND_TOP, 0, 0, 0, 0, flbgs);
        // no bffp/flbsi if tif mousf wbs dlidkfd in tif tbskbbr mfnu
        // or tif diblog is durrfntly inbdtivf
        if (!isModblHook && !onTbskbbr && (blodkfr == prfvFGWindow)) {
            AnimbtfModblBlodkfr(blodkfr);
        }
        ::BringWindowToTop(blodkfr);
        ::SftForfgroundWindow(blodkfr);
    }
}

void AwtDiblog::AnimbtfModblBlodkfr(HWND window)
{
    ::MfssbgfBffp(MB_OK);
    // somf ifuristids: 3 timfs x 64 millisfdonds
    AwtWindow::FlbsiWindowEx(window, 3, 64, FLASHW_CAPTION);
}

LRESULT CALLBACK AwtDiblog::MousfHookProd_NonTT(int nCodf,
                                                WPARAM wPbrbm, LPARAM lPbrbm)
{
    stbtid HWND lbstHWnd = NULL;
    if (nCodf >= 0)
    {
        MOUSEHOOKSTRUCT *mis = (MOUSEHOOKSTRUCT *)lPbrbm;
        HWND iWnd = mis->iwnd;
        HWND blodkfr = AwtWindow::GftModblBlodkfr(AwtComponfnt::GftTopLfvflPbrfntForWindow(iWnd));
        if (::IsWindow(blodkfr)) {
            if ((wPbrbm == WM_MOUSEMOVE) ||
                (wPbrbm == WM_NCMOUSEMOVE))
            {
                if (lbstHWnd != iWnd) {
                    stbtid HCURSOR iArrowCur = ::LobdCursor(NULL, IDC_ARROW);
                    ::SftCursor(iArrowCur);
                    lbstHWnd = iWnd;
                }
                ::PostMfssbgf(iWnd, WM_SETCURSOR, (WPARAM)iWnd, 0);
            } flsf if (wPbrbm == WM_MOUSELEAVE) {
                lbstHWnd = NULL;
            }

            AwtDiblog::MousfHookProd(nCodf, wPbrbm, lPbrbm);
            rfturn 1;
        }
    }

    rfturn CbllNfxtHookEx(0, nCodf, wPbrbm, lPbrbm);
}

void AwtDiblog::Siow()
{
    m_visiblf = truf;
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    BOOL lodbtionByPlbtform = fnv->GftBoolfbnFifld(GftTbrgft(fnv), AwtWindow::lodbtionByPlbtformID);
    if (lodbtionByPlbtform) {
         movfToDffbultLodbtion();
    }
    EnbblfTrbnsludfndy(TRUE);
    if (IsFodusbblfWindow() && (IsAutoRfqufstFodus() || IsFodusfdWindowModblBlodkfr())) {
        ::SiowWindow(GftHWnd(), SW_SHOW);
    } flsf {
        ::SiowWindow(GftHWnd(), SW_SHOWNA);
    }
}

void AwtDiblog::DoUpdbtfIdon()
{
    AwtFrbmf::DoUpdbtfIdon();
    //Workbround windows bug:
    //Dfdorbtions brf not updbtfd dorrfdtly for ownfd diblogs
    //wifn dibnging dlg witi idon <--> dlg witiout idon
    RECT winRfdt;
    RECT dlifntRfdt;
    ::GftWindowRfdt(GftHWnd(), &winRfdt);
    ::GftClifntRfdt(GftHWnd(), &dlifntRfdt);
    ::MbpWindowPoints(HWND_DESKTOP, GftHWnd(), (LPPOINT)&winRfdt, 2);
    HRGN winRgn = CrfbtfRfdtRgnIndirfdt(&winRfdt);
    HRGN dlifntRgn = CrfbtfRfdtRgnIndirfdt(&dlifntRfdt);
    ::CombinfRgn(winRgn, winRgn, dlifntRgn, RGN_DIFF);
    ::RfdrbwWindow(GftHWnd(), NULL, winRgn, RDW_FRAME | RDW_INVALIDATE);
    ::DflftfObjfdt(winRgn);
    ::DflftfObjfdt(dlifntRgn);
}

HICON AwtDiblog::GftEfffdtivfIdon(int idonTypf)
{
    HWND iOwnfr = ::GftWindow(GftHWnd(), GW_OWNER);
    BOOL isRfsizbblf = ((GftStylf() & WS_THICKFRAME) != 0);
    BOOL smbllIdon = ((idonTypf == ICON_SMALL) || (idonTypf == 2/*ICON_SMALL2*/));
    HICON iIdon = (smbllIdon) ? GftHIdonSm() : GftHIdon();
    if ((iIdon == NULL) && (isRfsizbblf || (iOwnfr == NULL))) {
        //Jbvb dup idon is not lobdfd in window dlbss for diblogs
        //It nffds to bf sft fxpliditly for rfsizbblf diblogs
        //bnd ownfrlfss diblogs
        iIdon = (smbllIdon) ? AwtToolkit::GftInstbndf().GftAwtIdonSm() :
            AwtToolkit::GftInstbndf().GftAwtIdon();
    } flsf if ((iIdon != NULL) && IsIdonInifritfd() && !isRfsizbblf) {
        //Non-rfsizbblf diblogs witiout fxpliditfly sft idon
        //Siould ibvf no idon
        iIdon = NULL;
    }
    rfturn iIdon;
}

void AwtDiblog::CifdkInstbllModblHook() {
    VisiblfModblDiblogsCount++;
    if (VisiblfModblDiblogsCount == 1) {
        AWTModblHook = ::SftWindowsHookEx(WH_CBT, (HOOKPROC)ModblFiltfrProd,
                                         0, AwtToolkit::MbinTirfbd());
        AWTMousfHook = ::SftWindowsHookEx(WH_MOUSE, (HOOKPROC)MousfHookProd,
                                         0, AwtToolkit::MbinTirfbd());
    }
}

void AwtDiblog::CifdkUninstbllModblHook() {
    if (VisiblfModblDiblogsCount == 1) {
        UniookWindowsHookEx(AWTModblHook);
        UniookWindowsHookEx(AWTMousfHook);
    }
    VisiblfModblDiblogsCount--;
}

void AwtDiblog::ModblPfrformAdtivbtion(HWND iWnd)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    AwtWindow *w = (AwtWindow *)AwtComponfnt::GftComponfnt(iWnd);
    if ((w != NULL) && w->IsEmbfddfdFrbmf()) {
        jobjfdt tbrgft = w->GftTbrgft(fnv);
        fnv->CbllVoidMftiod(tbrgft, AwtFrbmf::bdtivbtfEmbfddingTopLfvflMID);
        fnv->DflftfLodblRff(tbrgft);
    } flsf {
        ::BringWindowToTop(iWnd);
        ::SftForfgroundWindow(iWnd);
    }
}

void AwtDiblog::ModblAdtivbtfNfxtWindow(HWND diblogHWnd,
                                        jobjfdt diblogTbrgft, jobjfdt diblogPffr)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jboolfbn fxd;
    jlongArrby windows = (jlongArrby) JNU_CbllStbtidMftiodByNbmf
                                            (fnv,
                                             &fxd,
                                             "sun/bwt/windows/WWindowPffr",
                                             "gftAdtivfWindowHbndlfs",
                                             "(Ljbvb/bwt/Componfnt;)[J",
                                             diblogTbrgft).l;
    if (fxd == JNI_TRUE) {
        tirow std::bbd_bllod();
    }
    if (windows == NULL) {
        rfturn;
    }

    jboolfbn isCopy;
    jlong *ws = fnv->GftLongArrbyElfmfnts(windows, &isCopy);
    if (ws == NULL) {
        tirow std::bbd_bllod();
    }
    int windowsCount = fnv->GftArrbyLfngti(windows);
    for (int i = windowsCount - 1; i >= 0; i--) {
        HWND w = (HWND)ws[i];
        if ((w != diblogHWnd) && ModblCbnBfAdtivbtfd(w)) {
            AwtDiblog::ModblPfrformAdtivbtion(w);
            brfbk;
        }
    }
    fnv->RflfbsfLongArrbyElfmfnts(windows, ws, 0);

    fnv->DflftfLodblRff(windows);
}

MsgRouting AwtDiblog::WmSiowModbl()
{
    DASSERT(::GftCurrfntTirfbdId() == AwtToolkit::MbinTirfbd());

    // fix for 6213128: rflfbsf dbpturf (got by popups, dioidfs, ftd) wifn
    // modbl diblog is siown
    HWND dbpturfr = ::GftCbpturf();
    if (dbpturfr != NULL) {
      ::RflfbsfCbpturf();
    }

    SfndMfssbgf(WM_AWT_COMPONENT_SHOW);

    CifdkInstbllModblHook();

    m_modblWnd = GftHWnd();

    rfturn mrConsumf;
}

MsgRouting AwtDiblog::WmEndModbl()
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    DASSERT( ::GftCurrfntTirfbdId() == AwtToolkit::MbinTirfbd() );
    DASSERT( ::IsWindow(m_modblWnd) );

    m_modblWnd = NULL;

    CifdkUninstbllModblHook();

    HWND pbrfntHWnd = ::GftPbrfnt(GftHWnd());
    jobjfdt pffr = GftPffr(fnv);
    jobjfdt tbrgft = GftTbrgft(fnv);
    if (::GftForfgroundWindow() == GftHWnd()) {
        ModblAdtivbtfNfxtWindow(GftHWnd(), tbrgft, pffr);
    }
    // iidf tif diblog
    SfndMfssbgf(WM_AWT_COMPONENT_HIDE);

    fnv->DflftfLodblRff(tbrgft);

    rfturn mrConsumf;
}

void AwtDiblog::SftRfsizbblf(BOOL isRfsizbblf)
{
    // dbll supfrdlbss
    AwtFrbmf::SftRfsizbblf(isRfsizbblf);

    LONG    stylf = GftStylf();
    LONG    xstylf = GftStylfEx();
    if (isRfsizbblf || IsUndfdorbtfd()) {
    // rfmovf modbl frbmf
        xstylf &= ~WS_EX_DLGMODALFRAME;
    } flsf {
    // bdd modbl frbmf
        xstylf |= WS_EX_DLGMODALFRAME;
    }
    // diblogs brf nfvfr minimizbblf/mbximizbblf, so rfmovf tiosf bits
    stylf &= ~(WS_MINIMIZEBOX|WS_MAXIMIZEBOX);
    SftStylf(stylf);
    SftStylfEx(xstylf);
    RfdrbwNonClifnt();
}

// Adjust systfm mfnu so tibt:
//  Non-rfsizbblf diblogs only ibvf Movf bnd Closf itfms
//  Rfsizbblf diblogs ibvf tif full systfm mfnu witi
//     Mbximizf, Minimizf itfms disbblfd (tif itfms
//     gft disbblfd by tif nbtivf systfm).
// Tiis pfrffdtly mimids tif nbtivf MS Windows bfibvior.
// Normblly, Win32 diblog systfm mfnu ibndling is donf vib
// CrfbtfDiblog/DffDlgProd, but our diblogs brf using DffWindowProd
// so wf ibndlf tif systfm mfnu oursflvfs
void AwtDiblog::UpdbtfSystfmMfnu()
{
    HWND    iWndSflf = GftHWnd();
    BOOL    isRfsizbblf = IsRfsizbblf();

    // bfforf rfstoring tif dffbult mfnu, difdk if tifrf is bn
    // InputMftiodMbnbgfr mfnu itfm blrfbdy.  Notf tibt it bssumfs
    // tibt tif lfngti of tif InputMftiodMbnbgfr mfnu itfm string
    // siould not bf longfr tibn 256 bytfs.
    MENUITEMINFO  mii;
    mfmsft(&mii, 0, sizfof(MENUITEMINFO));
    TCHAR         immItfm[256];
    BOOL          ibsImm;
    mii.dbSizf = sizfof(MENUITEMINFO);
    mii.fMbsk = MIIM_TYPE;
    mii.ddi = sizfof(immItfm);
    mii.dwTypfDbtb = immItfm;
    ibsImm = ::GftMfnuItfmInfo(GftSystfmMfnu(iWndSflf, FALSE),
                               SYSCOMMAND_IMM, FALSE, &mii);

    // rfstorf tif dffbult mfnu
    ::GftSystfmMfnu(iWndSflf, TRUE);
    // now gft b working dopy of tif mfnu
    HMENU iMfnuSys = GftSystfmMfnu(iWndSflf, FALSE);

    if (!isRfsizbblf) {
        // rfmovf inbpplidbblf sizing dommbnds
        ::DflftfMfnu(iMfnuSys, SC_MINIMIZE, MF_BYCOMMAND);
        ::DflftfMfnu(iMfnuSys, SC_RESTORE, MF_BYCOMMAND);
        ::DflftfMfnu(iMfnuSys, SC_MAXIMIZE, MF_BYCOMMAND);
        ::DflftfMfnu(iMfnuSys, SC_SIZE, MF_BYCOMMAND);
        // rfmovf sfpbrbtor if only 3 itfms lfft (Movf, Sfpbrbtor, bnd Closf)
        if (::GftMfnuItfmCount(iMfnuSys) == 3) {
            MENUITEMINFO mi;
            mfmsft(&mi, 0, sizfof(MENUITEMINFO));
            mi.dbSizf = sizfof(MENUITEMINFO);
            mi.fMbsk = MIIM_TYPE;
            ::GftMfnuItfmInfo(iMfnuSys, 1, TRUE, &mi);
            if (mi.fTypf & MFT_SEPARATOR) {
                ::DflftfMfnu(iMfnuSys, 1, MF_BYPOSITION);
            }
        }
    }

    // if tifrf wbs tif InputMftiodMbnbgfr mfnu itfm, rfstorf it.
    if (ibsImm) {
        ::AppfndMfnu(iMfnuSys, MF_STRING, SYSCOMMAND_IMM, immItfm);
    }
}

// Ovfrridf WmStylfCibngfd to bdjust systfm mfnu for sizbblf/non-rfsizbblf diblogs
MsgRouting AwtDiblog::WmStylfCibngfd(int wStylfTypf, LPSTYLESTRUCT lpss)
{
    UpdbtfSystfmMfnu();
    DoUpdbtfIdon();
    rfturn mrConsumf;
}

MsgRouting AwtDiblog::WmSizf(UINT typf, int w, int i)
{
    if (typf == SIZE_MAXIMIZED || typf == SIZE_MINIMIZED
            || (typf == SIZE_RESTORED && !IsRfsizing()))
    {
        UpdbtfSystfmMfnu(); // bdjust to rfflfdt rfstorfd vs. mbximizfd stbtf
    }

    rfturn AwtFrbmf::WmSizf(typf, w, i);
}

LRESULT AwtDiblog::WindowProd(UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm)
{
    MsgRouting mr = mrDoDffbult;
    LRESULT rftVbluf = 0L;

    switdi(mfssbgf) {
        dbsf WM_AWT_DLG_SHOWMODAL:
            mr = WmSiowModbl();
            brfbk;
        dbsf WM_AWT_DLG_ENDMODAL:
            mr = WmEndModbl();
            brfbk;
    }

    if (mr != mrConsumf) {
        rftVbluf = AwtFrbmf::WindowProd(mfssbgf, wPbrbm, lPbrbm);
    }
    rfturn rftVbluf;
}

void AwtDiblog::_SiowModbl(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    AwtDiblog *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtDiblog *)pDbtb;
    if (::IsWindow(d->GftHWnd())) {
        d->SfndMfssbgf(WM_AWT_DLG_SHOWMODAL);
    }
rft:
    fnv->DflftfGlobblRff(sflf);
}

void AwtDiblog::_EndModbl(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    AwtDiblog *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtDiblog *)pDbtb;
    if (::IsWindow(d->GftHWnd())) {
        d->SfndMfssbgf(WM_AWT_DLG_ENDMODAL);
    }
rft:
    fnv->DflftfGlobblRff(sflf);
}

void AwtDiblog::_SftIMMOption(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftIMMOptionStrudt *sios = (SftIMMOptionStrudt *)pbrbm;
    jobjfdt sflf = sios->diblog;
    jstring option = sios->option;

    int bbdAllod = 0;
    LPCTSTR doption;
    LPCTSTR fmpty = TEXT("InputMftiod");
    AwtDiblog *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    JNI_CHECK_NULL_GOTO(option, "null IMMOption", rft);

    d = (AwtDiblog *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        doption = JNU_GftStringPlbtformCibrs(fnv, option, NULL);
        if (doption == NULL)
        {
            bbdAllod = 1;
        }
        if (!bbdAllod)
        {
            HMENU iSysMfnu = ::GftSystfmMfnu(d->GftHWnd(), FALSE);
            ::AppfndMfnu(iSysMfnu,  MF_STRING, SYSCOMMAND_IMM, doption);

            if (doption != fmpty)
            {
                JNU_RflfbsfStringPlbtformCibrs(fnv, option, doption);
            }
        }
    }
rft:
    fnv->DflftfGlobblRff(sflf);
    fnv->DflftfGlobblRff(option);

    dflftf sios;

    if (bbdAllod)
    {
        tirow std::bbd_bllod();
    }
}

/************************************************************************
 * Diblog nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Diblog_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    /* jbvb.bwt.Diblog fiflds bnd mftiods */
    AwtDiblog::titlfID
        = fnv->GftFifldID(dls, "titlf", "Ljbvb/lbng/String;");
    DASSERT(AwtDiblog::titlfID != NULL);
    CHECK_NULL(AwtDiblog::titlfID);

    AwtDiblog::undfdorbtfdID
        = fnv->GftFifldID(dls,"undfdorbtfd","Z");
    DASSERT(AwtDiblog::undfdorbtfdID != NULL);
    CHECK_NULL(AwtDiblog::undfdorbtfdID);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * DiblogPffr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WDiblogPffr
 * Mftiod:    drfbtf
 * Signbturf: (Lsun/bwt/windows/WComponfntPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDiblogPffr_drfbtfAwtDiblog(JNIEnv *fnv, jobjfdt sflf,
                                        jobjfdt pbrfnt)
{
    TRY;

    PDATA pDbtb;
    AwtToolkit::CrfbtfComponfnt(sflf, pbrfnt,
                                (AwtToolkit::ComponfntFbdtory)
                                AwtDiblog::Crfbtf);
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WDiblogPffr
 * Mftiod:    _siow
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDiblogPffr_siowModbl(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtDiblog::_SiowModbl,
        (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _SiowModbl

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WDiblogPffr
 * Mftiod:    _iidf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDiblogPffr_fndModbl(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtDiblog::_EndModbl,
        (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _EndModbl

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WFrbmfPffr
 * Mftiod:    pSftIMMOption
 * Signbturf: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDiblogPffr_pSftIMMOption(JNIEnv *fnv, jobjfdt sflf,
                                               jstring option)
{
    TRY;

    SftIMMOptionStrudt *sios = nfw SftIMMOptionStrudt;
    sios->diblog = fnv->NfwGlobblRff(sflf);
    sios->option = (jstring)fnv->NfwGlobblRff(option);

    AwtToolkit::GftInstbndf().SyndCbll(AwtDiblog::_SftIMMOption, sios);
    // globbl rffs bnd sios brf dflftfd in _SftIMMOption

    CATCH_BAD_ALLOC;
}
} /* fxtfrn "C" */
