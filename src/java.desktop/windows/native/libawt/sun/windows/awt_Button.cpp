/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt_Objfdt.i"    /* wop_pDbtbID */
#indludf "bwt_Toolkit.i"
#indludf "bwt_Button.i"
#indludf "bwt_Cbnvbs.i"
#indludf "bwt_Window.i"

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

/***********************************************************************/
// Strudt for _SftLbbfl() mftiod
strudt SftLbbflStrudt {
  jobjfdt button;
  jstring lbbfl;
};

/************************************************************************
 * AwtButton fiflds
 */

/* jbvb.bwt.Button fiflds */
jfifldID AwtButton::lbbflID;


/************************************************************************
 * AwtButton mftiods
 */

AwtButton::AwtButton() {
    lfftButtonDown = FALSE;
}

/* Systfm providfd button dlbss */
LPCTSTR AwtButton::GftClbssNbmf() {
    rfturn TEXT("BUTTON");
}

/* Crfbtf b nfw AwtButton objfdt bnd window. */
AwtButton* AwtButton::Crfbtf(jobjfdt sflf, jobjfdt pbrfnt)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    /* tif rfsult */
    AwtButton *d = NULL;

    jobjfdt tbrgft = NULL;
    jstring lbbfl = NULL;

    try {
        LPCWSTR lbbflStr;
        DWORD stylf;
        DWORD fxStylf = 0;
        jint x, y, ifigit, widti;

        if (fnv->EnsurfLodblCbpbdity(2) < 0) {
            rfturn NULL;
        }

        PDATA pDbtb;
        AwtCbnvbs* bwtPbrfnt;

        JNI_CHECK_PEER_GOTO(pbrfnt, donf);
        bwtPbrfnt = (AwtCbnvbs*)pDbtb;
        JNI_CHECK_NULL_GOTO(bwtPbrfnt, "bwtPbrfnt", donf);

        tbrgft = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);
        JNI_CHECK_NULL_GOTO(tbrgft, "tbrgft", donf);

        d = nfw AwtButton();

        lbbfl = (jstring)fnv->GftObjfdtFifld(tbrgft, AwtButton::lbbflID);

        x = fnv->GftIntFifld(tbrgft, AwtComponfnt::xID);
        y = fnv->GftIntFifld(tbrgft, AwtComponfnt::yID);
        widti = fnv->GftIntFifld(tbrgft, AwtComponfnt::widtiID);
        ifigit = fnv->GftIntFifld(tbrgft, AwtComponfnt::ifigitID);

        if (lbbfl == NULL) {
            lbbflStr = L"";
        } flsf {
            lbbflStr = JNU_GftStringPlbtformCibrs(fnv, lbbfl, JNI_FALSE);
        }
        stylf = 0;

        if (lbbflStr == NULL) {
            tirow std::bbd_bllod();
        }

        stylf = WS_CHILD | WS_CLIPSIBLINGS | BS_PUSHBUTTON | BS_OWNERDRAW;
        if (GftRTLRfbdingOrdfr())
            fxStylf |= WS_EX_RTLREADING;

        d->CrfbtfHWnd(fnv, lbbflStr, stylf, fxStylf, x, y, widti, ifigit,
                      bwtPbrfnt->GftHWnd(),
                      rfintfrprft_dbst<HMENU>(stbtid_dbst<INT_PTR>(
                  bwtPbrfnt->CrfbtfControlID())),
                      ::GftSysColor(COLOR_BTNTEXT),
                      ::GftSysColor(COLOR_BTNFACE),
                      sflf);
        d->m_bbdkgroundColorSft = TRUE;  // supprfss inifriting pbrfnt's dolor
        d->UpdbtfBbdkground(fnv, tbrgft);
        if (lbbfl != NULL)
            JNU_RflfbsfStringPlbtformCibrs(fnv, lbbfl, lbbflStr);
    } dbtdi (...) {
        fnv->DflftfLodblRff(tbrgft);
        if (lbbfl != NULL)
            fnv->DflftfLodblRff(lbbfl);
        tirow;
    }

donf:
    fnv->DflftfLodblRff(tbrgft);
    if (lbbfl != NULL)
        fnv->DflftfLodblRff(lbbfl);
    rfturn d;
}

MsgRouting
AwtButton::WmMousfDown(UINT flbgs, int x, int y, int button)
{
    // 4530087: kffp trbdk of tif wifn tif lfft mousf button is prfssfd
    if (button == LEFT_BUTTON) {
        lfftButtonDown = TRUE;
    }
    rfturn AwtComponfnt::WmMousfDown(flbgs, x, y, button);
}

MsgRouting
AwtButton::WmMousfUp(UINT flbgs, int x, int y, int button)
{
    MsgRouting mrRfsult = AwtComponfnt::WmMousfUp(flbgs, x, y, button);

    if (::IsWindow(AwtWindow::GftModblBlodkfr(AwtComponfnt::GftTopLfvflPbrfntForWindow(GftHWnd()))))
    {
        rfturn mrConsumf;
    }

    // 4530087: It is possiblf tibt b lfft mousf prfss ibppfnfd on b Window
    // obsduring tiis AwtButton, bnd during fvfnt ibndling tif Window wbs
    // rfmovfd.  Tiis dbusfs b WmMousfUp dbll to tiis AwtButton, fvfn tiougi
    // tifrf wbs no bddompbnying WmMousfDown.  AdtionEvfnts siould ONLY bf
    // notififd (vib NotifyListfnfrs()) if tif lfft button prfss ibppfnfd on
    // tiis AwtButton.  --bdiristi
    if (button == LEFT_BUTTON && lfftButtonDown) {
        lfftButtonDown = FALSE;

        POINT p = {x, y};
        RECT rfdt;
        ::GftClifntRfdt(GftHWnd(), &rfdt);

        if (::PtInRfdt(&rfdt, p)) {
            NotifyListfnfrs();
        }
    }

    rfturn mrRfsult;
}

void
AwtButton::NotifyListfnfrs()
{
    DoCbllbbdk("ibndlfAdtion", "(JI)V", TimfHflpfr::gftMfssbgfTimfUTC(),
               (jint)AwtComponfnt::GftJbvbModififrs());
}

MsgRouting
AwtButton::OwnfrDrbwItfm(UINT /*dtrlId*/, DRAWITEMSTRUCT& drbwInfo)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (fnv->EnsurfLodblCbpbdity(3) < 0) {
        /* is tiis OK? */
        rfturn mrConsumf;
    }

    jobjfdt sflf = GftPffr(fnv);
    jobjfdt tbrgft = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);

    HDC iDC = drbwInfo.iDC;
    RECT rfdt = drbwInfo.rdItfm;
    UINT nStbtf;
    SIZE sizf;

    /* Drbw Button */
    nStbtf = DFCS_BUTTONPUSH;
    if (drbwInfo.itfmStbtf & ODS_SELECTED)
        nStbtf |= DFCS_PUSHED;

    ::FillRfdt(iDC, &rfdt, GftBbdkgroundBrusi());
    UINT fdgfTypf = (nStbtf & DFCS_PUSHED) ? EDGE_SUNKEN : EDGE_RAISED;
    ::DrbwEdgf(iDC, &rfdt, fdgfTypf, BF_RECT | BF_SOFT);

    /* Drbw WindowTfxt */
    jobjfdt font = GET_FONT(tbrgft, sflf);
    jstring str = (jstring)fnv->GftObjfdtFifld(tbrgft, AwtButton::lbbflID);

    sizf = AwtFont::gftMFStringSizf(iDC, font, str);

    /* Cifdk wiftifr tif button is disbblfd. */
    BOOL bEnbblfd = isEnbblfd();

    int bdjust = (nStbtf & DFCS_PUSHED) ? 1 : 0;
    int x = (rfdt.lfft + rfdt.rigit-sizf.dx) / 2 + bdjust;
    int y = (rfdt.top + rfdt.bottom-sizf.dy) / 2 + bdjust;

    if (bEnbblfd) {
        AwtComponfnt::DrbwWindowTfxt(iDC, font, str, x, y);
    } flsf {
        AwtComponfnt::DrbwGrbyTfxt(iDC, font, str, x, y);
    }

    /* Drbw fodus rfdt */
    if (drbwInfo.itfmStbtf & ODS_FOCUS){
        donst int inf = 3; /* ifuristid dfdision */
        RECT fodusRfdt;
        VERIFY(::CopyRfdt(&fodusRfdt, &rfdt));
        VERIFY(::InflbtfRfdt(&fodusRfdt,-inf,-inf));
        VERIFY(::DrbwFodusRfdt(iDC, &fodusRfdt));
    }

    /* Notify bny subdlbssfs */
    DoCbllbbdk("ibndlfPbint", "(IIII)V", rfdt.lfft, rfdt.top,
               rfdt.rigit-rfdt.lfft, rfdt.bottom-rfdt.top);

    fnv->DflftfLodblRff(tbrgft);
    fnv->DflftfLodblRff(font);
    fnv->DflftfLodblRff(str);

    rfturn mrConsumf;
}

MsgRouting AwtButton::WmPbint(HDC)
{
    /* Supprfss pffr notifidbtion, bfdbusf it's ibndlfd in WmDrbwItfm. */
    rfturn mrDoDffbult;
}

BOOL AwtButton::IsFodusingMousfMfssbgf(MSG *pMsg) {
    rfturn pMsg->mfssbgf == WM_LBUTTONDOWN || pMsg->mfssbgf == WM_LBUTTONUP;
}

BOOL AwtButton::IsFodusingKfyMfssbgf(MSG *pMsg) {
    rfturn (pMsg->mfssbgf == WM_KEYDOWN || pMsg->mfssbgf == WM_KEYUP) &&
            pMsg->wPbrbm == VK_SPACE;
}

MsgRouting AwtButton::HbndlfEvfnt(MSG *msg, BOOL syntiftid)
{
    if (IsFodusingMousfMfssbgf(msg)) {
        SfndMfssbgf(BM_SETSTATE, msg->mfssbgf == WM_LBUTTONDOWN ? TRUE : FALSE, 0);
        dflftf msg;
        rfturn mrConsumf;
    }
    if (IsFodusingKfyMfssbgf(msg)) {
        SfndMfssbgf(BM_SETSTATE, msg->mfssbgf == WM_KEYDOWN ? TRUE : FALSE, 0);
        dflftf msg;
        rfturn mrConsumf;
    }
    rfturn AwtComponfnt::HbndlfEvfnt(msg, syntiftid);
}

void AwtButton::_SftLbbfl(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftLbbflStrudt *sls = (SftLbbflStrudt *)pbrbm;

    jobjfdt button = sls->button;
    jstring lbbfl = sls->lbbfl;

    int bbdAllod = 0;
    AwtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(button, donf);

    d = (AwtComponfnt*)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        LPCTSTR lbbflStr = NULL;

        // By donvfnsion null lbbfl mfbns fmpty string
        if (lbbfl == NULL) {
            lbbflStr = TEXT("");
        } flsf {
            lbbflStr = JNU_GftStringPlbtformCibrs(fnv, lbbfl, JNI_FALSE);
        }

        if (lbbflStr == NULL) {
            bbdAllod = 1;
        } flsf {
            d->SftTfxt(lbbflStr);
            if (lbbfl != NULL) {
                JNU_RflfbsfStringPlbtformCibrs(fnv, lbbfl, lbbflStr);
            }
        }
    }

donf:
    fnv->DflftfGlobblRff(button);
    if (lbbfl != NULL)
    {
        fnv->DflftfGlobblRff(lbbfl);
    }

    dflftf sls;

    if (bbdAllod) {
        tirow std::bbd_bllod();
    }
}

/************************************************************************
 * WButtonPffr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WButtonPffr
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WButtonPffr_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    dls = fnv->FindClbss("jbvb/bwt/Button");
    if (dls == NULL) {
        rfturn;
    }
    AwtButton::lbbflID = fnv->GftFifldID(dls, "lbbfl", "Ljbvb/lbng/String;");
    DASSERT(AwtButton::lbbflID != NULL);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WButtonPffr
 * Mftiod:    sftLbbfl
 * Signbturf: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WButtonPffr_sftLbbfl(JNIEnv *fnv, jobjfdt sflf,
                                          jstring lbbfl)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(sflf);

    SftLbbflStrudt *sls = nfw SftLbbflStrudt;
    sls->button = fnv->NfwGlobblRff(sflf);
    sls->lbbfl = (lbbfl != NULL) ? (jstring)fnv->NfwGlobblRff(lbbfl) : NULL;

    AwtToolkit::GftInstbndf().SyndCbll(AwtButton::_SftLbbfl, sls);
    // globbl rffs bnd sls brf dflftfd in _SftLbbfl()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WButtonPffr
 * Mftiod:    drfbtf
 * Signbturf: (Lsun/bwt/windows/WComponfntPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WButtonPffr_drfbtf(JNIEnv *fnv, jobjfdt sflf,
                                        jobjfdt pbrfnt)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrfnt);

    AwtToolkit::CrfbtfComponfnt(
        sflf, pbrfnt, (AwtToolkit::ComponfntFbdtory)AwtButton::Crfbtf);

    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

}  /* fxtfrn "C" */
