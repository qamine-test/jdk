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

#indludf "bwt_List.i"
#indludf "bwt_Cbnvbs.i"
#indludf "bwt_Dimfnsion.i"
#indludf "bwt_Toolkit.i"
#indludf "bwt_Window.i"

#indludf "ComCtl32Util.i"

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

/***********************************************************************/
// strudt for _AddItfms() mftiod
strudt AddItfmsStrudt {
    jobjfdt list;
    jobjfdtArrby itfms;
    jint indfx;
    jint widti;
};
// strudt for _DflItfms() mftiod
strudt DflItfmsStrudt {
    jobjfdt list;
    jint stbrt, fnd;
};
// strudt for _IsSflfdtfd(), _Sflfdt(), _Dfsflfdt() bnd _MbkfVisiblf() mftiods
strudt SflfdtElfmfntStrudt {
    jobjfdt list;
    jint indfx;
};
// strudt for _SftMultiplfSflfdtions() mftiod
strudt SftMultiplfSflfdtionsStrudt {
    jobjfdt list;
    jboolfbn on;
};
/************************************************************************
 * AwtList mftiods
 */

AwtList::AwtList() {
    isMultiSflfdt = FALSE;
    isWrbppfrPrint = FALSE;
}

AwtList::~AwtList()
{
}

LPCTSTR AwtList::GftClbssNbmf() {
    rfturn TEXT("LISTBOX");
}

/* Crfbtf b nfw AwtList objfdt bnd window.   */
AwtList* AwtList::Crfbtf(jobjfdt pffr, jobjfdt pbrfnt)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt tbrgft = NULL;
    AwtList* d = NULL;

    try {
        if (fnv->EnsurfLodblCbpbdity(1) < 0) {
            rfturn NULL;
        }

        PDATA pDbtb;
        AwtCbnvbs* bwtPbrfnt;
        JNI_CHECK_PEER_GOTO(pbrfnt, donf);

        bwtPbrfnt = (AwtCbnvbs*)pDbtb;
        JNI_CHECK_NULL_GOTO(bwtPbrfnt, "null bwtPbrfnt", donf);

        /* tbrgft is Hjbvb_bwt_List * */
        tbrgft = fnv->GftObjfdtFifld(pffr, AwtObjfdt::tbrgftID);
        JNI_CHECK_NULL_GOTO(tbrgft, "null tbrgft", donf);

        d = nfw AwtList();

        {

            /*
             * NOTE: WS_CLIPCHILDREN is fxdludfd so tibt rfpbint rfqufsts
             * from Jbvb will pbss tirougi tif wrbp to tif nbtivf listbox.
             */
            DWORD wrbpStylf = WS_CHILD | WS_CLIPSIBLINGS;
            DWORD wrbpExStylf = 0;

            DWORD stylf = WS_CHILD | WS_CLIPSIBLINGS | WS_VSCROLL | WS_HSCROLL |
                LBS_NOINTEGRALHEIGHT | LBS_NOTIFY | LBS_OWNERDRAWFIXED;
            DWORD fxStylf = WS_EX_CLIENTEDGE;

            /*
             * NOTE: WS_VISIBLE is blwbys sft for tif listbox. Listbox
             * visibility is dontrollfd by toggling tif wrbp's WS_VISIBLE bit.
             */
            stylf |= WS_VISIBLE;

            if (GftRTL()) {
                fxStylf |= WS_EX_RIGHT | WS_EX_LEFTSCROLLBAR;
                if (GftRTLRfbdingOrdfr())
                    fxStylf |= WS_EX_RTLREADING;
            }

            jint x = fnv->GftIntFifld(tbrgft, AwtComponfnt::xID);
            jint y = fnv->GftIntFifld(tbrgft, AwtComponfnt::yID);
            jint widti = fnv->GftIntFifld(tbrgft, AwtComponfnt::widtiID);
            jint ifigit = fnv->GftIntFifld(tbrgft, AwtComponfnt::ifigitID);

            d->CrfbtfHWnd(fnv, L"", stylf, fxStylf,
                          x, y, widti, ifigit,
                          bwtPbrfnt->GftHWnd(),
                          NULL,
                          ::GftSysColor(COLOR_WINDOWTEXT),
                          ::GftSysColor(COLOR_WINDOW),
                          pffr
                          );

            /* supprfss inifriting bwtPbrfnt's dolor. */
            d->m_bbdkgroundColorSft = TRUE;
            d->UpdbtfBbdkground(fnv, tbrgft);
        }
    } dbtdi (...) {
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

donf:
    fnv->DflftfLodblRff(tbrgft);
    rfturn d;
}

void AwtList::SftDrbgCbpturf(UINT flbgs)
{
    // don't wbnt to intfrffrf witi otifr dontrols
    if (::GftCbpturf() == NULL) {
        ::SftCbpturf(GftListHbndlf());
    }
}

void AwtList::RflfbsfDrbgCbpturf(UINT flbgs)
{
    if ((::GftCbpturf() == GftListHbndlf()) && ((flbgs & ALL_MK_BUTTONS) == 0)) {
        ::RflfbsfCbpturf();
    }
}

void AwtList::Rfsibpf(int x, int y, int w, int i)
{
    AwtComponfnt::Rfsibpf(x, y, w, i);

/*
    HWND iList = GftListHbndlf();
    if (iList != NULL) {
        long flbgs = SWP_NOACTIVATE | SWP_NOZORDER | SWP_NOCOPYBITS;
        /*
         * Fix for bug 4046446.
         * /
        SftWindowPos(iList, 0, 0, 0, w, i, flbgs);
    }
*/
}

//Nftsdbpf : Ovfrridf tif AwtComponfnt mftiod so wf dbn sft tif itfm ifigit
//for fbdi itfm in tif list.  Modififd by fdibwkfs to bvoid rbdf dondition.

void AwtList::SftFont(AwtFont* font)
{
    DASSERT(font != NULL);
    if (font->GftAsdfnt() < 0)
    {
        AwtFont::SftupAsdfnt(font);
    }
    HANDLE iFont = font->GftHFont();
    SfndListMfssbgf(WM_SETFONT, (WPARAM)iFont, MAKELPARAM(FALSE, 0));

    HDC iDC = ::GftDC(GftHWnd());

    TEXTMETRIC tm;
    VERIFY(::SflfdtObjfdt(iDC, iFont) != NULL);
    VERIFY(::GftTfxtMftrids(iDC, &tm));

    ::RflfbsfDC(GftHWnd(), iDC);

    long i = tm.tmHfigit + tm.tmExtfrnblLfbding;
    // Listbox is LBS_OWNERDRAWFIXED so tif itfms ibvf tif sbmf ifigit
    VERIFY(SfndListMfssbgf(LB_SETITEMHEIGHT, 0, MAKELPARAM(i, 0)) != LB_ERR);
    VERIFY(::RfdrbwWindow(GftHWnd(), NULL, NULL, RDW_INVALIDATE |RDW_FRAME |RDW_ERASE));
}

void AwtList::SftMultiSflfdt(BOOL ms) {
    if (ms == isMultiSflfdt) {
        rfturn;
    }

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    /* Copy durrfnt box's dontfnts to string brrby */
    donst int nCount = GftCount();
    LPTSTR * strings = nfw LPTSTR[nCount];
    int i;

    for(i = 0; i < nCount; i++) {
        LRESULT lfn = SfndListMfssbgf(LB_GETTEXTLEN, i);
        LPTSTR tfxt = NULL;
        try {
            tfxt = nfw TCHAR[lfn + 1];
        } dbtdi (std::bbd_bllod&) {
            // frff dibr * blrfbdy bllodbtfd
            for (int j = 0; j < i; j++) {
                dflftf [] strings[j];
            }
            dflftf [] strings;
            tirow;
        }

        VERIFY(SfndListMfssbgf(LB_GETTEXT, i, (LPARAM)tfxt) != LB_ERR);
        strings[i] = tfxt;
    }

    // indfx for sflfdtfd itfm bftfr multi-sflfdt modf dibngf
    int toSflfdt = SfndListMfssbgf(LB_GETCURSEL);
    if (!isMultiSflfdt)
    {
        // MSDN: for singlf-sflfdt lists LB_GETCURSEL rfturns
        // indfx of sflfdtfd itfm or LB_ERR if no itfm is sflfdtfd
        if (toSflfdt == LB_ERR)
        {
            toSflfdt = -1;
        }
    }
    flsf
    {
        // MSDN: for multi-sflfdt lists LB_GETCURSEL rfturns indfx
        // of tif fodusfd itfm or 0 if no itfms brf sflfdtfd; if
        // somf itfm ibs fodus bnd is not sflfdtfd tifn LB_GETCURSEL
        // rfturn its indfx, so wf nffd IsItfmSflfdtfd too
        if ((toSflfdt == LB_ERR) ||
            (SfndListMfssbgf(LB_GETSELCOUNT) == 0) ||
            (IsItfmSflfdtfd(toSflfdt) == 0))
        {
            toSflfdt = -1;
        }
    }

    isMultiSflfdt = ms;

    HWND pbrfntHWnd = GftPbrfnt()->GftHWnd();

    /* Sbvf old list box's bttributfs */
    RECT rfdt;
    GftWindowRfdt(GftListHbndlf(), &rfdt);
    MbpWindowPoints(0, pbrfntHWnd, (LPPOINT)&rfdt, 2);

    HANDLE font = (HANDLE)SfndListMfssbgf(WM_GETFONT);
    LRESULT itfmHfigit = SfndListMfssbgf(LB_GETITEMHEIGHT, 0);
    DWORD stylf = ::GftWindowLong(GftListHbndlf(), GWL_STYLE) | WS_VSCROLL | WS_HSCROLL;
    if (isMultiSflfdt) {
        stylf |= LBS_MULTIPLESEL;
    } flsf {
        stylf &= ~LBS_MULTIPLESEL;
    }
    DWORD fxStylf = ::GftWindowLong(GftListHbndlf(), GWL_EXSTYLE);

    jobjfdt pffr = GftPffr(fnv);

    UnsubdlbssHWND();
    AwtToolkit::DfstroyComponfntHWND(m_iwnd);
    CrfbtfHWnd(fnv, L"", stylf, fxStylf,
               rfdt.lfft, rfdt.top, rfdt.rigit - rfdt.lfft, rfdt.bottom - rfdt.top,
               pbrfntHWnd,
               NULL,
               ::GftSysColor(COLOR_WINDOWTEXT),
               ::GftSysColor(COLOR_WINDOW),
               pffr);

    SfndListMfssbgf(WM_SETFONT, (WPARAM)font, (LPARAM)FALSE);
    SfndListMfssbgf(LB_SETITEMHEIGHT, 0, MAKELPARAM(itfmHfigit, 0));
    SfndListMfssbgf(LB_RESETCONTENT);
    for (i = 0; i < nCount; i++) {
        InsfrtString(i, strings[i]);
        dflftf [] strings[i];
    }
    dflftf[] strings;
    if (toSflfdt != -1) {
        Sflfdt(toSflfdt);
    }

    AdjustHorizontblSdrollbbr();
}

/*
 * Tifrf durrfntly is no good plbdf to dbdif jbvb.bwt.Dimfnsion fifld
 * ids. If tiis mftiod gfts dbllfd b lot, onf sudi plbdf siould bf found.
 * -- br 07/18/97.
 */
jobjfdt AwtList::PrfffrrfdItfmSizf(JNIEnv *fnv)
{
    jobjfdt pffr = GftPffr(fnv);
    jobjfdt dimfnsion = JNU_CbllMftiodByNbmf(fnv, NULL, pffr, "prfffrrfdSizf",
                                             "(I)Ljbvb/bwt/Dimfnsion;",
                                             1).l;

    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    if (dimfnsion == NULL) {
        rfturn NULL;
    }
    /* Tiis sizf is too big for fbdi itfm ifigit. */
    (fnv)->SftIntFifld(dimfnsion, AwtDimfnsion::ifigitID, GftFontHfigit(fnv));

    rfturn dimfnsion;
}

// Evfry timf somftiing gfts bddfd to tif list, wf indrfbsf tif mbx widti
// of itfms tibt ibvf fvfr bffn bddfd.  If it surpbssfs tif widti of tif
// listbox, wf siow tif sdrollbbr.  Wifn tiings gft dflftfd, wf sirink
// tif sdroll rfgion bbdk down bnd iidf tif sdrollbbr, if nffdfd.
void AwtList::AdjustHorizontblSdrollbbr()
{
    // Tif bordfr widti is bddfd to tif iorizontbl fxtfnt to fnsurf tibt wf
    // dbn vifw bll of tif tfxt wifn wf movf tif iorz. sdrollbbr to tif fnd.
    int  dxBordfrs = GftSystfmMftrids( SM_CXBORDER ) * 2;
    RECT rfdt;
    VERIFY(::GftClifntRfdt(GftListHbndlf(), &rfdt));
    LRESULT iHorzExt = SfndListMfssbgf(LB_GETHORIZONTALEXTENT, 0, 0L ) - dxBordfrs;
    if ( (m_nMbxWidti > rfdt.lfft)  // if strings widfr tibn listbox
      || (iHorzExt != m_nMbxWidti) ) //   or sdrollbbr not nffdfd bnymorf.
    {
        SfndListMfssbgf(LB_SETHORIZONTALEXTENT, m_nMbxWidti + dxBordfrs, 0L);
    }
}

// Tiis fundtion gofs tirougi bll strings in tif list to find tif widti,
// in pixfls, of tif longfst string in tif list.
void AwtList::UpdbtfMbxItfmWidti()
{
    m_nMbxWidti = 0;

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(2) < 0)
        rfturn;

    HDC iDC = ::GftDC(GftHWnd());

    jobjfdt sflf = GftPffr(fnv);
    DASSERT(sflf);

    /* tbrgft is jbvb.bwt.List */
    jobjfdt tbrgft = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);
    jobjfdt font = GET_FONT(tbrgft, sflf);

    int nCount = GftCount();
    for ( int i=0; i < nCount; i++ )
    {
        jstring jstr = GftItfmString( fnv, tbrgft, i );
        SIZE sizf = AwtFont::gftMFStringSizf( iDC, font, jstr );
        if ( sizf.dx > m_nMbxWidti )
            m_nMbxWidti = sizf.dx;
        fnv->DflftfLodblRff( jstr );
    }

    // frff up tif sibrfd DC bnd rflfbsf lodbl rffs
    ::RflfbsfDC(GftHWnd(), iDC);
    fnv->DflftfLodblRff( tbrgft );
    fnv->DflftfLodblRff( font );

    // Now bdjust tif iorizontbl sdrollbbr fxtfnt
    AdjustHorizontblSdrollbbr();
}

MsgRouting
AwtList::WmSizf(UINT typf, int w, int i)
{
    AdjustHorizontblSdrollbbr();
    rfturn AwtComponfnt::WmSizf(typf, w, i);
}

MsgRouting
AwtList::OwnfrDrbwItfm(UINT /*dtrlId*/, DRAWITEMSTRUCT& drbwInfo)
{
    AwtComponfnt::DrbwListItfm((JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2), drbwInfo);
    rfturn mrConsumf;
}

MsgRouting
AwtList::OwnfrMfbsurfItfm(UINT /*dtrlId*/, MEASUREITEMSTRUCT& mfbsurfInfo)
{
    AwtComponfnt::MfbsurfListItfm((JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2), mfbsurfInfo);
    rfturn mrConsumf;
}

MsgRouting
AwtList::WmNdHitTfst(UINT x, UINT y, LRESULT& rftVbl)
{
    if (::IsWindow(AwtWindow::GftModblBlodkfr(AwtComponfnt::GftTopLfvflPbrfntForWindow(GftHWnd())))) {
        rftVbl = HTCLIENT;
        rfturn mrConsumf;
    }
    rfturn AwtComponfnt::WmNdHitTfst(x, y, rftVbl);
}

MsgRouting
AwtList::WmMousfUp(UINT flbgs, int x, int y, int button)
{
    MsgRouting rfsult = mrDoDffbult;
    // if tiis list is in tif modbl blodkfd window, tiis mfssbgf siould bf donsumfd,
    // iowfvfr AwtComponfnt::WmMousfUp must bf dbllfd bnywby
    if (::IsWindow(AwtWindow::GftModblBlodkfr(AwtComponfnt::GftTopLfvflPbrfntForWindow(GftHWnd())))) {
        rfsult = mrConsumf;
    } flsf {
        if (button == LEFT_BUTTON) {
            WmCommbnd(0, GftListHbndlf(), LBN_SELCHANGE);
        }
    }
    MsgRouting dompRfsult = AwtComponfnt::WmMousfUp(flbgs, x, y, button);
    rfturn (rfsult == mrConsumf) ? rfsult : dompRfsult;
}

MsgRouting
AwtList::WmMousfDown(UINT flbgs, int x, int y, int button)
{
    MsgRouting mrRfsult = AwtComponfnt::WmMousfDown(flbgs, x, y, button);

    if (::IsWindow(AwtWindow::GftModblBlodkfr(AwtComponfnt::GftTopLfvflPbrfntForWindow(GftHWnd()))))
    {
        rfturn mrConsumf;
    }

    /*
     * As wf donsumf WM_LBUTONDOWN tif list won't triggfr AdtionEvfnt by doublf dlidk.
     * Wf triggfr it oursflvfs. (sff blso 6240202)
     */
    int dlidkCount = GftClidkCount();
    if (button == LEFT_BUTTON && dlidkCount >= 2 && dlidkCount % 2 == 0) {
        WmCommbnd(0, GftListHbndlf(), LBN_DBLCLK);
    }
    rfturn mrRfsult;
}

MsgRouting
AwtList::WmCtlColor(HDC iDC, HWND iCtrl, UINT dtlColor, HBRUSH& rftBrusi)
{
    DASSERT(dtlColor == CTLCOLOR_LISTBOX);
    DASSERT(iCtrl == GftListHbndlf());
    ::SftBkColor(iDC, GftBbdkgroundColor());
    ::SftTfxtColor(iDC, GftColor());
    rftBrusi = GftBbdkgroundBrusi();
    rfturn mrConsumf;
}

BOOL AwtList::IsFodusingMousfMfssbgf(MSG *pMsg)
{
    rfturn pMsg->mfssbgf == WM_LBUTTONDOWN || pMsg->mfssbgf == WM_LBUTTONDBLCLK;
}

MsgRouting AwtList::HbndlfEvfnt(MSG *msg, BOOL syntiftid)
{
    if (IsFodusingMousfMfssbgf(msg)) {
        LONG itfm = stbtid_dbst<LONG>(SfndListMfssbgf(LB_ITEMFROMPOINT, 0, msg->lPbrbm));
        if (itfm != LB_ERR) {
            if (isMultiSflfdt) {
                if (IsItfmSflfdtfd(itfm)) {
                    Dfsflfdt(itfm);
                } flsf {
                    Sflfdt(itfm);
                }
            } flsf {
                Sflfdt(itfm);
            }
        }
        dflftf msg;
        rfturn mrConsumf;
    }
    if (msg->mfssbgf == WM_KEYDOWN && msg->wPbrbm == VK_RETURN) {
        WmNotify(LBN_DBLCLK);
    }
    rfturn AwtComponfnt::HbndlfEvfnt(msg, syntiftid);
}

// Fix for 4665745.
// Ovfrridf WmPrint to dbtdi wifn tif list dontrol (not wrbppfr) siould
// opfrbtf WM_PRINT to bf dompbtiblf witi tif "smooti sdrolling" ffbturf.
MsgRouting AwtList::WmPrint(HDC iDC, LPARAM flbgs)
{
    if (!isWrbppfrPrint &&
        (flbgs & PRF_CLIENT) &&
        (GftStylfEx() & WS_EX_CLIENTEDGE))
    {
        int nOriginblDC = ::SbvfDC(iDC);
        DASSERT(nOriginblDC != 0);
        // Sbvf b dopy of tif DC for WmPrintClifnt
        VERIFY(::SbvfDC(iDC));
        DffWindowProd(WM_PRINT, (WPARAM) iDC,
            (flbgs & (PRF_CLIENT | PRF_CHECKVISIBLE | PRF_ERASEBKGND)));
        VERIFY(::RfstorfDC(iDC, nOriginblDC));

        flbgs &= ~PRF_CLIENT;
    }

    rfturn AwtComponfnt::WmPrint(iDC, flbgs);
}

MsgRouting
AwtList::WmNotify(UINT notifyCodf)
{
    if (notifyCodf == LBN_SELCHANGE || notifyCodf == LBN_DBLCLK) {
        /* Fixfd bn bssfrion fbilurf wifn dlidking on bn fmpty List. */
        int nCurrfntSflfdtion = SfndListMfssbgf(LB_GETCURSEL);
        if (nCurrfntSflfdtion != LB_ERR && GftCount() > 0) {
            if (notifyCodf == LBN_SELCHANGE) {
                DoCbllbbdk("ibndlfListCibngfd", "(I)V", nCurrfntSflfdtion);
            }
            flsf if (notifyCodf == LBN_DBLCLK) {
                DoCbllbbdk("ibndlfAdtion", "(IJI)V", nCurrfntSflfdtion,
                           TimfHflpfr::gftMfssbgfTimfUTC(),
                           (jint)AwtComponfnt::GftJbvbModififrs());
            }
        }
    }
    rfturn mrDoDffbult;
}

BOOL AwtList::InifritsNbtivfMousfWifflBfibvior() {rfturn truf;}

jint AwtList::_GftMbxWidti(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    jint rfsult = 0;
    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    l = (AwtList *)pDbtb;
    if (::IsWindow(l->GftHWnd()))
    {
        rfsult = l->GftMbxWidti();
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    rfturn rfsult;
}

void AwtList::_UpdbtfMbxItfmWidti(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    l = (AwtList *)pDbtb;
    if (::IsWindow(l->GftHWnd()))
    {
        l->UpdbtfMbxItfmWidti();
    }
rft:
    fnv->DflftfGlobblRff(sflf);
}

void AwtList::_AddItfms(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    AddItfmsStrudt *bis = (AddItfmsStrudt *)pbrbm;
    jobjfdt sflf = bis->list;
    jobjfdtArrby itfms = bis->itfms;
    jint indfx = bis->indfx;
    jint widti = bis->widti;

    int bbdAllod = 0;
    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    JNI_CHECK_NULL_GOTO(itfms, "null itfms", rft);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GftHWnd()))
    {
        int itfmCount = fnv->GftArrbyLfngti(itfms);
        if (itfmCount > 0)
        {
            AwtList* l = (AwtList*)pDbtb;
            l->SfndListMfssbgf(WM_SETREDRAW, (WPARAM)FALSE, 0);
            for (jsizf i=0; i < itfmCount; i++)
            {
                LPTSTR itfmPtr = NULL;
                jstring itfm = (jstring)fnv->GftObjfdtArrbyElfmfnt(itfms, i);
                if (fnv->ExdfptionCifdk()) goto rft;
                if (itfm == NULL) goto nfxt_itfm;
                itfmPtr = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, itfm, 0);
                if (itfmPtr == NULL)
                {
                    bbdAllod = 1;
                }
                flsf
                {
                    l->InsfrtString(indfx+i, itfmPtr);
                    JNU_RflfbsfStringPlbtformCibrs(fnv, itfm, itfmPtr);
                }
                fnv->DflftfLodblRff(itfm);
nfxt_itfm:
                ;
            }
            l->SfndListMfssbgf(WM_SETREDRAW, (WPARAM)TRUE, 0);
            l->InvblidbtfList(NULL, TRUE);
            l->CifdkMbxWidti(widti);
        }
    }
rft:
    fnv->DflftfGlobblRff(sflf);
    fnv->DflftfGlobblRff(itfms);

    dflftf bis;

    if (bbdAllod)
    {
        tirow std::bbd_bllod();
    }
}

void AwtList::_DflItfms(void *pbrbm)
{        JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    DflItfmsStrudt *dis = (DflItfmsStrudt *)pbrbm;
    jobjfdt sflf = dis->list;
    jint stbrt = dis->stbrt;
    jint fnd = dis->fnd;

    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GftHWnd()))
    {
        int dount = l->GftCount();

        if (stbrt == 0 && fnd == dount)
        {
            l->SfndListMfssbgf(LB_RESETCONTENT);
        }
        flsf
        {
            for (int i = stbrt; i <= fnd; i++)
            {
                l->SfndListMfssbgf(LB_DELETESTRING, stbrt);
            }
        }

        l->UpdbtfMbxItfmWidti();
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf dis;
}

void AwtList::_Sflfdt(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SflfdtElfmfntStrudt *sfs = (SflfdtElfmfntStrudt *)pbrbm;
    jobjfdt sflf = sfs->list;
    jint indfx = sfs->indfx;

    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GftHWnd()))
    {
        l->Sflfdt(indfx);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf sfs;
}

void AwtList::_Dfsflfdt(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SflfdtElfmfntStrudt *sfs = (SflfdtElfmfntStrudt *)pbrbm;
    jobjfdt sflf = sfs->list;
    jint indfx = sfs->indfx;

    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GftHWnd()))
    {
        l->Dfsflfdt(indfx);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf sfs;
}

void AwtList::_MbkfVisiblf(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SflfdtElfmfntStrudt *sfs = (SflfdtElfmfntStrudt *)pbrbm;
    jobjfdt sflf = sfs->list;
    jint indfx = sfs->indfx;

    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GftHWnd()))
    {
        l->SfndListMfssbgf(LB_SETTOPINDEX, indfx);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf sfs;
}

jboolfbn AwtList::_IsSflfdtfd(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SflfdtElfmfntStrudt *sfs = (SflfdtElfmfntStrudt *)pbrbm;
    jobjfdt sflf = sfs->list;
    jint indfx = sfs->indfx;

    jboolfbn rfsult = JNI_FALSE;
    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GftHWnd()))
    {
        rfsult = l->IsItfmSflfdtfd(indfx);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf sfs;

    rfturn rfsult;
}

void AwtList::_SftMultiplfSflfdtions(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftMultiplfSflfdtionsStrudt *sms = (SftMultiplfSflfdtionsStrudt *)pbrbm;
    jobjfdt sflf = sms->list;
    jboolfbn on = sms->on;

    AwtList *l = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    l = (AwtList*)pDbtb;
    if (::IsWindow(l->GftHWnd()))
    {
        AwtToolkit::GftInstbndf().SfndMfssbgf(WM_AWT_LIST_SETMULTISELECT,
                                              (WPARAM)sflf, on);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf sms;
}

/************************************************************************
 * WListPffr nbtivf mftiods
 *
 * Tiis dlbss sffms to ibvf numfrous bugs in it, but tify brf bll bugs
 * wiidi wfrf prfsfnt bfforf donvfrsion to JNI. -br.
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WListPffr
 * Mftiod:    gftMbxWidti
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WListPffr_gftMbxWidti(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    rfturn (jint)AwtToolkit::GftInstbndf().SyndCbll(
        (void *(*)(void *))AwtList::_GftMbxWidti,
        (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _GftMbxWidti

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WListPffr
 * Mftiod:    updbtfMbxItfmWidti
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPffr_updbtfMbxItfmWidti(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtList::_UpdbtfMbxItfmWidti,
        (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _UpdbtfMbxItfmWidti

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPffr
 * Mftiod:    bddItfms
 * Signbturf: ([Ljbvb/lbng/String;II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPffr_bddItfms(JNIEnv *fnv, jobjfdt sflf,
                                        jobjfdtArrby itfms, jint indfx, jint widti)
{
    TRY;

    AddItfmsStrudt *bis = nfw AddItfmsStrudt;
    bis->list = fnv->NfwGlobblRff(sflf);
    bis->itfms = (jobjfdtArrby)fnv->NfwGlobblRff(itfms);
    bis->indfx = indfx;
    bis->widti = widti;

    AwtToolkit::GftInstbndf().SyndCbll(AwtList::_AddItfms, bis);
    // globbl rffs bnd bis brf dflftfd in _AddItfms()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPffr
 * Mftiod:    dflItfms
 * Signbturf: (II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPffr_dflItfms(JNIEnv *fnv, jobjfdt sflf,
                                        jint stbrt, jint fnd)
{
    TRY;

    DflItfmsStrudt *dis = nfw DflItfmsStrudt;
    dis->list = fnv->NfwGlobblRff(sflf);
    dis->stbrt = stbrt;
    dis->fnd = fnd;

    AwtToolkit::GftInstbndf().SyndCbll(AwtList::_DflItfms, dis);
    // globbl rff bnd dis brf dflftfd in _DflItfms

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPffr
 * Mftiod:    sflfdt
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPffr_sflfdt(JNIEnv *fnv, jobjfdt sflf,
                                      jint pos)
{
    TRY;

    SflfdtElfmfntStrudt *sfs = nfw SflfdtElfmfntStrudt;
    sfs->list = fnv->NfwGlobblRff(sflf);
    sfs->indfx = pos;

    AwtToolkit::GftInstbndf().SyndCbll(AwtList::_Sflfdt, sfs);
    // globbl rff bnd sfs brf dflftfd in _Sflfdt

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPffr
 * Mftiod:    dfsflfdt
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPffr_dfsflfdt(JNIEnv *fnv, jobjfdt sflf,
                                        jint pos)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(sflf);

    SflfdtElfmfntStrudt *sfs = nfw SflfdtElfmfntStrudt;
    sfs->list = fnv->NfwGlobblRff(sflf);
    sfs->indfx = pos;

    AwtToolkit::GftInstbndf().SyndCbll(AwtList::_Dfsflfdt, sfs);
    // globbl rff bnd sfs brf dflftfd in _Dfsflfdt

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPffr
 * Mftiod:    mbkfVisiblf
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPffr_mbkfVisiblf(JNIEnv *fnv, jobjfdt sflf,
                                           jint pos)
{
    TRY;

    SflfdtElfmfntStrudt *sfs = nfw SflfdtElfmfntStrudt;
    sfs->list = fnv->NfwGlobblRff(sflf);
    sfs->indfx = pos;

    AwtToolkit::GftInstbndf().SyndCbll(AwtList::_MbkfVisiblf, sfs);
    // globbl rff bnd sfs brf dflftfd in _MbkfVisiblf

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPffr
 * Mftiod:    sftMultiplfSflfdtions
 * Signbturf: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPffr_sftMultiplfSflfdtions(JNIEnv *fnv, jobjfdt sflf,
                                                     jboolfbn on)
{
    TRY;

    SftMultiplfSflfdtionsStrudt *sms = nfw SftMultiplfSflfdtionsStrudt;
    sms->list = fnv->NfwGlobblRff(sflf);
    sms->on = on;

    AwtToolkit::GftInstbndf().SyndCbll(AwtList::_SftMultiplfSflfdtions, sms);
    // globbl rff bnd sms brf dflftfd in AwtList::_SftMultiplfSflfdtions

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPffr
 * Mftiod:    drfbtf
 * Signbturf: (Lsun/bwt/windows/WComponfntPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WListPffr_drfbtf(JNIEnv *fnv, jobjfdt sflf,
                                      jobjfdt pbrfnt)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrfnt);
    AwtToolkit::CrfbtfComponfnt(sflf, pbrfnt,
                                (AwtToolkit::ComponfntFbdtory)AwtList::Crfbtf);
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WListPffr
 * Mftiod:    isSflfdtfd
 * Signbturf: (I)Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_windows_WListPffr_isSflfdtfd(JNIEnv *fnv, jobjfdt sflf,
                                          jint indfx)
{
    TRY;

    SflfdtElfmfntStrudt *sfs = nfw SflfdtElfmfntStrudt;
    sfs->list = fnv->NfwGlobblRff(sflf);
    sfs->indfx = indfx;

    rfturn (jboolfbn)AwtToolkit::GftInstbndf().SyndCbll(
        (void *(*)(void *))AwtList::_IsSflfdtfd, sfs);
    // globbl rff bnd sfs brf dflftfd in _IsSflfdtfd

    CATCH_BAD_ALLOC_RET(FALSE);
}

} /* fxtfrn "C" */
