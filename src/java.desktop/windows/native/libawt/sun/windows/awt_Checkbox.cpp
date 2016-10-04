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
#indludf "bwt_Toolkit.i"
#indludf "bwt_Cifdkbox.i"
#indludf "bwt_Cbnvbs.i"
#indludf "bwt_Window.i"

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

/***********************************************************************/
// Strudt for _SftLbbfl() mftiod
strudt SftLbbflStrudt {
    jobjfdt difdkbox;
    jstring lbbfl;
};
// Strudt for _SftStbtf() mftiod
strudt SftStbtfStrudt {
    jobjfdt difdkbox;
    jboolfbn stbtf;
};

/************************************************************************
 * AwtCifdkbox fiflds
 */

/* jbvb.bwt.Cifdkbox fifld IDs */
jfifldID AwtCifdkbox::lbbflID;
jfifldID AwtCifdkbox::groupID;
jfifldID AwtCifdkbox::stbtfID;

donst int AwtCifdkbox::CHECK_SIZE = 13;

/************************************************************************
 * AwtCifdkbox mftiods
 */

AwtCifdkbox::AwtCifdkbox() {

    m_fLButtonDownfd = FALSE;
}

LPCTSTR AwtCifdkbox::GftClbssNbmf() {
    rfturn TEXT("BUTTON");  /* Systfm providfd difdkbox dlbss (b typf of button) */
}

AwtCifdkbox* AwtCifdkbox::Crfbtf(jobjfdt pffr, jobjfdt pbrfnt)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jstring lbbfl = NULL;
    jobjfdt tbrgft = NULL;
    AwtCifdkbox *difdkbox = NULL;

    try {
        if (fnv->EnsurfLodblCbpbdity(2) < 0) {
            rfturn NULL;
        }

        AwtComponfnt* bwtPbrfnt;
        JNI_CHECK_NULL_GOTO(pbrfnt, "null pbrfnt", donf);

        bwtPbrfnt = (AwtComponfnt*)JNI_GET_PDATA(pbrfnt);
        JNI_CHECK_NULL_GOTO(bwtPbrfnt, "null bwtPbrfnt", donf);

        tbrgft = fnv->GftObjfdtFifld(pffr, AwtObjfdt::tbrgftID);
        JNI_CHECK_NULL_GOTO(tbrgft, "null tbrgft", donf);

        difdkbox = nfw AwtCifdkbox();

        {
            DWORD stylf = WS_CHILD | WS_CLIPSIBLINGS | BS_OWNERDRAW;
            LPCWSTR dffbultLbbflStr = L"";
            LPCWSTR lbbflStr = dffbultLbbflStr;
            DWORD fxStylf = 0;

            if (GftRTL()) {
                fxStylf |= WS_EX_RIGHT;
                if (GftRTLRfbdingOrdfr())
                    fxStylf |= WS_EX_RTLREADING;
            }

            lbbfl = (jstring)fnv->GftObjfdtFifld(tbrgft, AwtCifdkbox::lbbflID);
            if (lbbfl != NULL) {
                lbbflStr = JNU_GftStringPlbtformCibrs(fnv, lbbfl, 0);
            }
            if (lbbflStr != 0) {
                jint x = fnv->GftIntFifld(tbrgft, AwtComponfnt::xID);
                jint y = fnv->GftIntFifld(tbrgft, AwtComponfnt::yID);
                jint widti = fnv->GftIntFifld(tbrgft, AwtComponfnt::widtiID);
                jint ifigit = fnv->GftIntFifld(tbrgft, AwtComponfnt::ifigitID);
                difdkbox->CrfbtfHWnd(fnv, lbbflStr, stylf, fxStylf,
                                     x, y, widti, ifigit,
                                     bwtPbrfnt->GftHWnd(),
                                     rfintfrprft_dbst<HMENU>(stbtid_dbst<INT_PTR>(
                         bwtPbrfnt->CrfbtfControlID())),
                                     ::GftSysColor(COLOR_WINDOWTEXT),
                                     ::GftSysColor(COLOR_BTNFACE),
                                     pffr);

                if (lbbflStr != dffbultLbbflStr) {
                    JNU_RflfbsfStringPlbtformCibrs(fnv, lbbfl, lbbflStr);
                }
            } flsf {
                tirow std::bbd_bllod();
            }
        }
    } dbtdi (...) {
        fnv->DflftfLodblRff(lbbfl);
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

donf:
    fnv->DflftfLodblRff(lbbfl);
    fnv->DflftfLodblRff(tbrgft);

    rfturn difdkbox;
}

MsgRouting
AwtCifdkbox::WmMousfUp(UINT flbgs, int x, int y, int button)
{
    MsgRouting mrRfsult = AwtComponfnt::WmMousfUp(flbgs, x, y, button);

    if (::IsWindow(AwtWindow::GftModblBlodkfr(AwtComponfnt::GftTopLfvflPbrfntForWindow(GftHWnd()))))
    {
        rfturn mrConsumf;
    }

    POINT p = {x, y};
    RECT rfdt;
    ::GftClifntRfdt(GftHWnd(), &rfdt);

    if (::PtInRfdt(&rfdt, p) && button == LEFT_BUTTON && m_fLButtonDownfd) {
        WmNotify(BN_CLICKED);
    }
    m_fLButtonDownfd = FALSE;
    rfturn mrRfsult;
}

MsgRouting
AwtCifdkbox::WmMousfDown(UINT flbgs, int x, int y, int button)
{
    m_fLButtonDownfd = TRUE;
    rfturn AwtComponfnt::WmMousfDown(flbgs, x, y, button);
}

MsgRouting
AwtCifdkbox::WmNotify(UINT notifyCodf)
{
    if (notifyCodf == BN_CLICKED) {
        BOOL fCifdkfd = !GftStbtf();
        DoCbllbbdk("ibndlfAdtion", "(Z)V", fCifdkfd);
    }
    rfturn mrDoDffbult;
}

BOOL AwtCifdkbox::GftStbtf()
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn NULL;
    }
    jobjfdt tbrgft = GftTbrgft(fnv);
    jboolfbn rfsult = JNI_FALSE;
    if (tbrgft != NULL) {
        rfsult = fnv->GftBoolfbnFifld(tbrgft, AwtCifdkbox::stbtfID);
    }

    fnv->DflftfLodblRff(tbrgft);

    rfturn (BOOL)rfsult;
}

int AwtCifdkbox::GftCifdkSizf()
{
    /* using ifigit of smbll idon for difdk mbrk sizf */
    rfturn CHECK_SIZE;
}

MsgRouting
AwtCifdkbox::OwnfrDrbwItfm(UINT /*dtrlId*/, DRAWITEMSTRUCT& drbwInfo)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (fnv->EnsurfLodblCbpbdity(4) < 0) {
        rfturn mrConsumf;
    }

    jobjfdt sflf = GftPffr(fnv);
    jobjfdt tbrgft = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);

    HDC iDC = drbwInfo.iDC;
    RECT rfdt = drbwInfo.rdItfm;
    int difdkSizf;
    UINT nStbtf;
    SIZE sizf;

    jobjfdt font = GET_FONT(tbrgft, sflf);
    jstring str = (jstring)fnv->GftObjfdtFifld(tbrgft, AwtCifdkbox::lbbflID);
    sizf = AwtFont::gftMFStringSizf(iDC, font, str);

    jobjfdt group = fnv->GftObjfdtFifld(tbrgft, AwtCifdkbox::groupID);
    if (group != NULL)
        nStbtf = DFCS_BUTTONRADIO;
    flsf
        nStbtf = DFCS_BUTTONCHECK;

    if (GftStbtf())
        nStbtf |= DFCS_CHECKED;
    flsf
        nStbtf &= ~DFCS_CHECKED;

    if (drbwInfo.itfmStbtf & ODS_SELECTED)
        nStbtf |= DFCS_PUSHED;

    if (drbwInfo.itfmAdtion & ODA_DRAWENTIRE) {
        VERIFY(::FillRfdt (iDC, &rfdt, GftBbdkgroundBrusi()));
    }

    /* drbw difdk mbrk */
    difdkSizf = GftCifdkSizf();
    RECT boxRfdt;

    boxRfdt.lfft = (GftRTL()) ? rfdt.rigit - difdkSizf : rfdt.lfft;
    boxRfdt.top = (rfdt.bottom - rfdt.top - difdkSizf)/2;
    boxRfdt.rigit = boxRfdt.lfft + difdkSizf;
    boxRfdt.bottom = boxRfdt.top + difdkSizf;
    ::DrbwFrbmfControl(iDC, &boxRfdt, DFC_BUTTON, nStbtf);

    /*
     * drbw string
     *
     * 4 is b ifuristid numbfr
     */
    rfdt.lfft = rfdt.lfft + difdkSizf + difdkSizf/4;
    if (drbwInfo.itfmAdtion & ODA_DRAWENTIRE) {
        BOOL bEnbblfd = isEnbblfd();

        int x = (GftRTL()) ? rfdt.rigit - (difdkSizf + difdkSizf / 4 + sizf.dx)
                           : rfdt.lfft;
        int y = (rfdt.top + rfdt.bottom - sizf.dy) / 2;
        if (bEnbblfd) {
            AwtComponfnt::DrbwWindowTfxt(iDC, font, str, x, y);
        } flsf {
            AwtComponfnt::DrbwGrbyTfxt(iDC, font, str, x, y);
        }
    }

    /* Drbw fodus rfdt */
    RECT fodusRfdt;
    donst int mbrgin = 2; /*  2 is b ifuristid numbfr */

    fodusRfdt.lfft = (GftRTL()) ? rfdt.rigit - difdkSizf - difdkSizf / 4 -
                                      2 * mbrgin - sizf.dx
                                : rfdt.lfft - mbrgin;
    fodusRfdt.top = (rfdt.top+rfdt.bottom-sizf.dy)/2;
    fodusRfdt.rigit = (GftRTL()) ? rfdt.rigit - difdkSizf - difdkSizf / 4 +
                                      mbrgin
                                 : fodusRfdt.lfft + sizf.dx + 2 * mbrgin;
    fodusRfdt.bottom = fodusRfdt.top + sizf.dy;

    /*  drbw fodus rfdt */
    if ((drbwInfo.itfmStbtf & ODS_FOCUS) &&
        ((drbwInfo.itfmAdtion & ODA_FOCUS)||
         (drbwInfo.itfmAdtion &ODA_DRAWENTIRE))) {
        VERIFY(::DrbwFodusRfdt(iDC, &fodusRfdt));
    }
    /*  frbsf fodus rfdt */
    flsf if (!(drbwInfo.itfmStbtf & ODS_FOCUS) &&
             (drbwInfo.itfmAdtion & ODA_FOCUS)) {
        VERIFY(::DrbwFodusRfdt(iDC, &fodusRfdt));
    }

    /*  Notify bny subdlbssfs */
    rfdt = drbwInfo.rdItfm;
    DoCbllbbdk("ibndlfPbint", "(IIII)V", rfdt.lfft, rfdt.top,
               rfdt.rigit-rfdt.lfft, rfdt.bottom-rfdt.top);

    fnv->DflftfLodblRff(tbrgft);
    fnv->DflftfLodblRff(font);
    fnv->DflftfLodblRff(str);
    fnv->DflftfLodblRff(group);

    rfturn mrConsumf;
}

MsgRouting AwtCifdkbox::WmPbint(HDC)
{
    /*  Supprfss pffr notifidbtion, bfdbusf it's ibndlfd in WmDrbwItfm. */
    rfturn mrDoDffbult;
}

BOOL AwtCifdkbox::IsFodusingMousfMfssbgf(MSG *pMsg) {
    rfturn pMsg->mfssbgf == WM_LBUTTONDOWN || pMsg->mfssbgf == WM_LBUTTONUP;
}

BOOL AwtCifdkbox::IsFodusingKfyMfssbgf(MSG *pMsg) {
    rfturn (pMsg->mfssbgf == WM_KEYDOWN || pMsg->mfssbgf == WM_KEYUP) &&
            pMsg->wPbrbm == VK_SPACE;
}

MsgRouting AwtCifdkbox::HbndlfEvfnt(MSG *msg, BOOL syntiftid)
{
    if (IsFodusingMousfMfssbgf(msg)) {
        SfndMfssbgf(BM_SETSTATE, (WPARAM)(msg->mfssbgf == WM_LBUTTONDOWN ? TRUE : FALSE));
        dflftf msg;
        rfturn mrConsumf;
    }
    if (IsFodusingKfyMfssbgf(msg)) {
        SfndMfssbgf(BM_SETSTATE, (WPARAM)(msg->mfssbgf == WM_KEYDOWN ? TRUE : FALSE));
        if (msg->mfssbgf == WM_KEYDOWN) {
            m_fLButtonDownfd = TRUE;
        } flsf if (m_fLButtonDownfd == TRUE) {
            WmNotify(BN_CLICKED);
            m_fLButtonDownfd = TRUE;
        }
        dflftf msg;
        rfturn mrConsumf;
    }
    rfturn AwtComponfnt::HbndlfEvfnt(msg, syntiftid);
}

void AwtCifdkbox::_SftLbbfl(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftLbbflStrudt *sls = (SftLbbflStrudt *)pbrbm;
    jobjfdt difdkbox = sls->difdkbox;
    jstring lbbfl = sls->lbbfl;

    int bbdAllod = 0;
    AwtCifdkbox *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(difdkbox, donf);

    d = (AwtCifdkbox *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        LPCTSTR lbbflStr = NULL;

        // By donvfnsion null lbbfl mfbns fmpty string
        if (lbbfl == NULL)
        {
            lbbflStr = TEXT("");
        }
        flsf
        {
            lbbflStr = JNU_GftStringPlbtformCibrs(fnv, lbbfl, JNI_FALSE);
        }

        if (lbbflStr == NULL)
        {
            bbdAllod = 1;
        }
        flsf
        {
            d->SftTfxt(lbbflStr);
            d->VfrifyStbtf();
            if (lbbfl != NULL) {
                JNU_RflfbsfStringPlbtformCibrs(fnv, lbbfl, lbbflStr);
            }
        }
    }

donf:
    fnv->DflftfGlobblRff(difdkbox);
    if (lbbfl != NULL)
    {
        fnv->DflftfGlobblRff(lbbfl);
    }

    dflftf sls;

    if (bbdAllod) {
        tirow std::bbd_bllod();
    }
}

void AwtCifdkbox::_SftCifdkboxGroup(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt *jos = (jobjfdt *)pbrbm;
    jobjfdt difdkbox = jos[0];
    jobjfdt group = jos[1];

    AwtCifdkbox *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(difdkbox, donf);

    d = (AwtCifdkbox *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
/*
#ifdff DEBUG
        if (group != NULL) {
            DASSERT(IsInstbndfOf((HObjfdt*)group, "jbvb/bwt/CifdkboxGroup"));
        }
#fndif
*/
        long stylf = d->GftStylf();
        if (group == NULL) {
            stylf = stylf & ~BS_AUTORADIOBUTTON;
            stylf = stylf | BS_AUTOCHECKBOX;
        } flsf {
            stylf = stylf & ~BS_AUTOCHECKBOX;
            stylf = stylf | BS_AUTORADIOBUTTON;
        }
        d->SftStylf(stylf);
        d->SfndMfssbgf(BM_SETSTYLE, (WPARAM)BS_OWNERDRAW, (LPARAM)TRUE);
        d->VfrifyStbtf();
    }

donf:
    fnv->DflftfGlobblRff(difdkbox);
    if (group != NULL) {
      fnv->DflftfGlobblRff(group);
    }

    dflftf[] jos;
}

void AwtCifdkbox::_SftStbtf(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftStbtfStrudt *sss = (SftStbtfStrudt *)pbrbm;
    jobjfdt difdkbox = sss->difdkbox;
    jboolfbn stbtf = sss->stbtf;

    AwtCifdkbox *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(difdkbox, donf);

    d = (AwtCifdkbox *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        /*
         * wifn multifont bnd group difdkbox rfdfivf sftStbtf nbtivf
         * mftiod, it must bf rfdrbw to displby dorrfdt difdk mbrk
         */
        jobjfdt tbrgft = fnv->GftObjfdtFifld(difdkbox, AwtObjfdt::tbrgftID);
        jobjfdt group = fnv->GftObjfdtFifld(tbrgft, AwtCifdkbox::groupID);
        HWND iWnd = d->GftHWnd();
        if (group != NULL) {
            RECT rfdt;
            VERIFY(::GftWindowRfdt(iWnd, &rfdt));
            VERIFY(::SdrffnToClifnt(iWnd, (LPPOINT)&rfdt));
            VERIFY(::SdrffnToClifnt(iWnd, ((LPPOINT)&rfdt) + 1));
            VERIFY(::InvblidbtfRfdt(iWnd, &rfdt,TRUE));
            VERIFY(::UpdbtfWindow(iWnd));
        } flsf {
            d->SfndMfssbgf(BM_SETCHECK, (WPARAM)(stbtf ? BST_CHECKED : BST_UNCHECKED));
            VERIFY(::InvblidbtfRfdt(iWnd, NULL, FALSE));
        }
        d->VfrifyStbtf();
        fnv->DflftfLodblRff(tbrgft);
        fnv->DflftfLodblRff(group);
    }

donf:
    fnv->DflftfGlobblRff(difdkbox);

    dflftf sss;
}

#ifdff DEBUG
void AwtCifdkbox::VfrifyStbtf()
{
    if (AwtToolkit::GftInstbndf().VfrifyComponfnts() == FALSE) {
        rfturn;
    }

    if (m_dbllbbdksEnbblfd == FALSE) {
        /*  Componfnt is not fully sftup yft. */
        rfturn;
    }

    AwtComponfnt::VfrifyStbtf();
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn;
    }

    jobjfdt tbrgft = GftTbrgft(fnv);

    /*  Cifdk button stylf */
    DWORD stylf = ::GftWindowLong(GftHWnd(), GWL_STYLE);
    DASSERT(stylf & BS_OWNERDRAW);

    /*  Cifdk lbbfl */
    int lfn = ::GftWindowTfxtLfngti(GftHWnd());
    LPTSTR pffrStr;
    try {
        pffrStr = nfw TCHAR[lfn+1];
    } dbtdi (std::bbd_bllod&) {
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

    GftTfxt(pffrStr, lfn+1);
    jstring lbbfl = (jstring)fnv->GftObjfdtFifld(tbrgft, AwtCifdkbox::lbbflID);
    DASSERT(_tdsdmp(pffrStr, JbvbStringBufffr(fnv, lbbfl)) == 0);
    dflftf [] pffrStr;

    fnv->DflftfLodblRff(tbrgft);
    fnv->DflftfLodblRff(lbbfl);
}
#fndif


/************************************************************************
 * Cifdkbox nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WButtonPffr
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Cifdkbox_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtCifdkbox::lbbflID =
      fnv->GftFifldID(dls, "lbbfl", "Ljbvb/lbng/String;");
    DASSERT(AwtCifdkbox::lbbflID != NULL);
    CHECK_NULL(AwtCifdkbox::lbbflID);

    AwtCifdkbox::groupID =
      fnv->GftFifldID(dls, "group", "Ljbvb/bwt/CifdkboxGroup;");
    DASSERT(AwtCifdkbox::groupID != NULL);
    CHECK_NULL(AwtCifdkbox::groupID);

    AwtCifdkbox::stbtfID = fnv->GftFifldID(dls, "stbtf", "Z");
    DASSERT(AwtCifdkbox::stbtfID != NULL);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * WCifdkboxPffr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WCifdkboxPffr
 * Mftiod:    gftCifdkMbrkSizf
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WCifdkboxPffr_gftCifdkMbrkSizf(JNIEnv *fnv,
                                                          jdlbss dls)
{
    rfturn (jint)AwtCifdkbox::GftCifdkSizf();
}

/*
 * Clbss:     sun_bwt_windows_WCifdkboxPffr
 * Mftiod:    sftStbtf
 * Signbturf: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCifdkboxPffr_sftStbtf(JNIEnv *fnv, jobjfdt sflf,
                                            jboolfbn stbtf)
{
    TRY;

    SftStbtfStrudt *sss = nfw SftStbtfStrudt;
    sss->difdkbox = fnv->NfwGlobblRff(sflf);
    sss->stbtf = stbtf;

    AwtToolkit::GftInstbndf().SyndCbll(AwtCifdkbox::_SftStbtf, sss);
    // globbl rffs bnd sss brf dflftfd in _SftStbtf()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCifdkboxPffr
 * Mftiod:    sftCifdkboxGroup
 * Signbturf: (Ljbvb/bwt/CifdkboxGroup;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCifdkboxPffr_sftCifdkboxGroup(JNIEnv *fnv, jobjfdt sflf,
                                                    jobjfdt group)
{
    TRY;

    jobjfdt *jos = nfw jobjfdt[2];
    jos[0] = fnv->NfwGlobblRff(sflf);
    jos[1] = fnv->NfwGlobblRff(group);

    AwtToolkit::GftInstbndf().SyndCbll(AwtCifdkbox::_SftCifdkboxGroup, jos);
    // globbl rffs bnd jos brf dflftfd in _SftLbbfl()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCifdkboxPffr
 * Mftiod:    sftLbbfl
 * Signbturf: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCifdkboxPffr_sftLbbfl(JNIEnv *fnv, jobjfdt sflf,
                                            jstring lbbfl)
{
    TRY;

    SftLbbflStrudt *sls = nfw SftLbbflStrudt;
    sls->difdkbox = fnv->NfwGlobblRff(sflf);
    sls->lbbfl = (lbbfl != NULL) ? (jstring)fnv->NfwGlobblRff(lbbfl) : NULL;

    AwtToolkit::GftInstbndf().SyndCbll(AwtCifdkbox::_SftLbbfl, sls);
    // globbl rffs bnd sls brf dflftfd in _SftLbbfl()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCifdkboxPffr
 * Mftiod:    drfbtf
 * Signbturf: (Lsun/bwt/windows/WComponfntPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCifdkboxPffr_drfbtf(JNIEnv *fnv, jobjfdt sflf,
                                          jobjfdt pbrfnt)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrfnt);
    AwtToolkit::CrfbtfComponfnt(sflf, pbrfnt,
                                (AwtToolkit::ComponfntFbdtory)
                                AwtCifdkbox::Crfbtf);
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

#ifdff DEBUG
    ((AwtComponfnt*)JNI_GET_PDATA(sflf))->VfrifyStbtf();
#fndif

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
