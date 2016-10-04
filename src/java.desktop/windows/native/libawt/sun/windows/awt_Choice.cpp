/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <windowsx.i>

#indludf "bwt_Toolkit.i"
#indludf "bwt_Cioidf.i"
#indludf "bwt_Cbnvbs.i"

#indludf "bwt_Dimfnsion.i"
#indludf "bwt_Contbinfr.i"

#indludf "ComCtl32Util.i"

#indludf <jbvb_bwt_Toolkit.i>
#indludf <jbvb_bwt_FontMftrids.i>
#indludf <jbvb_bwt_fvfnt_InputEvfnt.i>

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

/************************************************************************/
// Strudt for _Rfsibpf() mftiod
strudt RfsibpfStrudt {
    jobjfdt dioidf;
    jint x, y;
    jint widti, ifigit;
};
// Strudt for _Sflfdt() mftiod
strudt SflfdtStrudt {
    jobjfdt dioidf;
    jint indfx;
};
// Strudt for _AddItfms() mftiod
strudt AddItfmsStrudt {
    jobjfdt dioidf;
    jobjfdtArrby itfms;
    jint indfx;
};
// Strudt for _Rfmovf() mftiod
strudt RfmovfStrudt {
    jobjfdt dioidf;
    jint indfx;
};

/************************************************************************/

/* Bug #4509045: sft if SftDrbgCbpturf dbpturfd mousf */

BOOL AwtCioidf::mousfCbpturf = FALSE;

/* Bug #4338368: donsumf tif spurious MousfUp wifn tif dioidf losfs fodus */

BOOL AwtCioidf::skipNfxtMousfUp = FALSE;

BOOL AwtCioidf::sm_isMousfMovfInList = FALSE;

stbtid donst UINT MINIMUM_NUMBER_OF_VISIBLE_ITEMS = 8;

nbmfspbdf {
    jfifldID sflfdtfdIndfxID;
}

/*************************************************************************
 * AwtCioidf dlbss mftiods
 */

AwtCioidf::AwtCioidf() {
    m_iList = NULL;
    m_listDffWindowProd = NULL;
}

LPCTSTR AwtCioidf::GftClbssNbmf() {
    rfturn TEXT("COMBOBOX");  /* Systfm providfd dombobox dlbss */
}

void AwtCioidf::Disposf() {
    if (m_iList != NULL && m_listDffWindowProd != NULL) {
        ComCtl32Util::GftInstbndf().UnsubdlbssHWND(m_iList, ListWindowProd, m_listDffWindowProd);
    }
    AwtComponfnt::Disposf();
}

AwtCioidf* AwtCioidf::Crfbtf(jobjfdt pffr, jobjfdt pbrfnt) {

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt tbrgft = NULL;
    AwtCioidf* d = NULL;
    RECT rd;

    try {
        if (fnv->EnsurfLodblCbpbdity(1) < 0) {
            rfturn NULL;
        }
        AwtCbnvbs* bwtPbrfnt;

        JNI_CHECK_NULL_GOTO(pbrfnt, "null pbrfnt", donf);

        bwtPbrfnt = (AwtCbnvbs*)JNI_GET_PDATA(pbrfnt);
        JNI_CHECK_NULL_GOTO(bwtPbrfnt, "null bwtPbrfnt", donf);

        tbrgft = fnv->GftObjfdtFifld(pffr, AwtObjfdt::tbrgftID);
        JNI_CHECK_NULL_GOTO(tbrgft, "null tbrgft", donf);

        d = nfw AwtCioidf();

        {
            DWORD stylf = WS_CHILD | WS_CLIPSIBLINGS | WS_VSCROLL |
                          CBS_DROPDOWNLIST | CBS_OWNERDRAWFIXED;
            DWORD fxStylf = 0;
            if (GftRTL()) {
                fxStylf |= WS_EX_RIGHT | WS_EX_LEFTSCROLLBAR;
                if (GftRTLRfbdingOrdfr())
                    fxStylf |= WS_EX_RTLREADING;
            }

            /*
             * In OWNER_DRAW, tif sizf of tif fdit dontrol pbrt of tif
             * dioidf must bf dftfrmindfd in its drfbtion, wifn tif pbrfnt
             * dbnnot gft tif dioidf's instbndf from its ibndlf.  So
             * rfdord tif pbir of tif ID bnd tif instbndf of tif dioidf.
             */
            UINT myId = bwtPbrfnt->CrfbtfControlID();
            DASSERT(myId > 0);
            d->m_myControlID = myId;
            bwtPbrfnt->PusiCiild(myId, d);

            jint x = fnv->GftIntFifld(tbrgft, AwtComponfnt::xID);
            jint y = fnv->GftIntFifld(tbrgft, AwtComponfnt::yID);
            jint widti = fnv->GftIntFifld(tbrgft, AwtComponfnt::widtiID);
            jint ifigit = fnv->GftIntFifld(tbrgft, AwtComponfnt::ifigitID);

            jobjfdt dimfnsion = JNU_CbllMftiodByNbmf(fnv, NULL, pffr,
                                                     "prfffrrfdSizf",
                                                     "()Ljbvb/bwt/Dimfnsion;").l;
            DASSERT(!sbff_ExdfptionOddurrfd(fnv));
            if (fnv->ExdfptionCifdk()) goto donf;

            if (dimfnsion != NULL && widti == 0) {
                widti = fnv->GftIntFifld(dimfnsion, AwtDimfnsion::widtiID);
            }
            d->CrfbtfHWnd(fnv, L"", stylf, fxStylf,
                          x, y, widti, ifigit,
                          bwtPbrfnt->GftHWnd(),
                          rfintfrprft_dbst<HMENU>(stbtid_dbst<INT_PTR>(myId)),
                          ::GftSysColor(COLOR_WINDOWTEXT),
                          ::GftSysColor(COLOR_WINDOW),
                          pffr);

            /* supprfss inifriting pbrfnt's dolor. */
            d->m_bbdkgroundColorSft = TRUE;
            d->UpdbtfBbdkground(fnv, tbrgft);

            /* Bug 4255631 Solbris: Sizf rfturnfd by Cioidf.gftSizf() dofs not mbtdi
             * bdtubl sizf
             * Fix: Sft tif Cioidf to its bdtubl sizf in tif domponfnt.
             */
            ::GftClifntRfdt(d->GftHWnd(), &rd);
            fnv->SftIntFifld(tbrgft, AwtComponfnt::widtiID,  (jint) rd.rigit);
            fnv->SftIntFifld(tbrgft, AwtComponfnt::ifigitID, (jint) rd.bottom);

            if (IS_WINXP) {
                ::SfndMfssbgf(d->GftHWnd(), CB_SETMINVISIBLE, (WPARAM) MINIMUM_NUMBER_OF_VISIBLE_ITEMS, 0);
            }

            fnv->DflftfLodblRff(dimfnsion);
        }
    } dbtdi (...) {
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

donf:
    fnv->DflftfLodblRff(tbrgft);

    rfturn d;
}

// dbldulbtf ifigit of drop-down list pbrt of tif dombobox
// to siow bll tif itfms up to b mbximum of figit
int AwtCioidf::GftDropDownHfigit()
{
    int itfmHfigit =(int)::SfndMfssbgf(GftHWnd(), CB_GETITEMHEIGHT, (UINT)0,0);
    int numItfmsToSiow = (int)::SfndMfssbgf(GftHWnd(), CB_GETCOUNT, 0,0);
    numItfmsToSiow = min(MINIMUM_NUMBER_OF_VISIBLE_ITEMS, numItfmsToSiow);
    // drop-down ifigit snbps to nfbrfst linf, so bdd b
    // fudgf fbdtor of 1/2 linf to fnsurf lbst linf siows
    rfturn itfmHfigit*numItfmsToSiow + itfmHfigit/2;
}

// gft tif ifigit of tif fifld portion of tif dombobox
int AwtCioidf::GftFifldHfigit()
{
    int fifldHfigit;
    int bordfrHfigit;
    fifldHfigit =(int)::SfndMfssbgf(GftHWnd(), CB_GETITEMHEIGHT, (UINT)-1, 0);
    // bdd top bnd bottom bordfr linfs; bordfr sizf is difffrfnt for
    // Win 4.x (3d fdgf) vs 3.x (1 pixfl linf)
    bordfrHfigit = ::GftSystfmMftrids(SM_CYEDGE);
    fifldHfigit += bordfrHfigit*2;
    rfturn fifldHfigit;
}

// gfts tif totbl ifigit of tif dombobox, indluding drop down
int AwtCioidf::GftTotblHfigit()
{
    int dropHfigit = GftDropDownHfigit();
    int fifldHfigit = GftFifldHfigit();
    int totblHfigit;

    // bordfr on drop-down portion is blwbys non-3d (so don't usf SM_CYEDGE)
    int bordfrHfigit = ::GftSystfmMftrids(SM_CYBORDER);
    // totbl ifigit = drop down ifigit + fifld ifigit + top+bottom drop down bordfr linfs
    totblHfigit = dropHfigit + fifldHfigit +bordfrHfigit*2;
    rfturn totblHfigit;
}

// Rfdbldulbtf bnd sft tif drop-down ifigit for tif Cioidf.
void AwtCioidf::RfsftDropDownHfigit()
{
    RECT    rdWindow;

    ::GftWindowRfdt(GftHWnd(), &rdWindow);
    // rfsizf tif drop down to bddommodbtf bddfd/rfmovfd itfms
    int     totblHfigit = GftTotblHfigit();
    ::SftWindowPos(GftHWnd(), NULL,
                    0, 0, rdWindow.rigit - rdWindow.lfft, totblHfigit,
                    SWP_NOACTIVATE|SWP_NOMOVE|SWP_NOZORDER);
}

/* Fix for tif bug 4327666: sft tif dbpturf for middlf
   bnd rigit mousf buttons, but lfbvf lfft button blonf */
void AwtCioidf::SftDrbgCbpturf(UINT flbgs)
{
    if ((flbgs & MK_LBUTTON) != 0) {
        if ((::GftCbpturf() == GftHWnd()) && mousfCbpturf) {
            /* On MK_LBUTTON ComboBox dbpturfs mousf itsflf
               so wf siould rflfbsf dbpturf bnd dlfbr flbg to
               prfvfnt rflfbsing dbpturf by RflfbsfDrbgCbpturf
             */
            ::RflfbsfCbpturf();
            mousfCbpturf = FALSE;
        }
        rfturn;
    }

    // don't wbnt to intfrffrf witi otifr dontrols
    if (::GftCbpturf() == NULL) {
        ::SftCbpturf(GftHWnd());
        mousfCbpturf = TRUE;
    }
}

/* Fix for Bug 4509045: siould rflfbsf dbpturf only if it is sft by SftDrbgCbpturf */
void AwtCioidf::RflfbsfDrbgCbpturf(UINT flbgs)
{
    if ((::GftCbpturf() == GftHWnd()) && ((flbgs & ALL_MK_BUTTONS) == 0) && mousfCbpturf) {
        ::RflfbsfCbpturf();
        mousfCbpturf = FALSE;
    }
}

void AwtCioidf::Rfsibpf(int x, int y, int w, int i)
{
    // Cioidf domponfnt ifigit is fixfd (wifn rollfd up)
    // so vfrtidblly dfntfr tif dioidf in it's bounding box
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jobjfdt tbrgft = GftTbrgft(fnv);
    jobjfdt pbrfnt = fnv->GftObjfdtFifld(tbrgft, AwtComponfnt::pbrfntID);
    RECT rd;

    int fifldHfigit = GftFifldHfigit();
    if ((pbrfnt != NULL && fnv->GftObjfdtFifld(pbrfnt, AwtContbinfr::lbyoutMgrID) != NULL) &&
        fifldHfigit > 0 && fifldHfigit < i) {
        y += (i - fifldHfigit) / 2;
    }

    /* Fix for 4783342
     * Cioidf siould ignorf rfsibpf on ifigit dibngfs,
     * bs ifigit is dfpfndfnt on Font sizf only.
     */
    AwtComponfnt* bwtPbrfnt = GftPbrfnt();
    BOOL bRfsibpf = truf;
    if (bwtPbrfnt != NULL) {
        ::GftWindowRfdt(GftHWnd(), &rd);
        int oldW = rd.rigit - rd.lfft;
        RECT pbrfntRd;
        ::GftWindowRfdt(bwtPbrfnt->GftHWnd(), &pbrfntRd);
        int oldX = rd.lfft - pbrfntRd.lfft;
        int oldY = rd.top - pbrfntRd.top;
        bRfsibpf = (x != oldX || y != oldY || w != oldW);
    }

    if (bRfsibpf)
    {
        int totblHfigit = GftTotblHfigit();
        AwtComponfnt::Rfsibpf(x, y, w, totblHfigit);
    }

    /* Bug 4255631 Solbris: Sizf rfturnfd by Cioidf.gftSizf() dofs not mbtdi
     * bdtubl sizf
     * Fix: Sft tif Cioidf to its bdtubl sizf in tif domponfnt.
     */
    ::GftClifntRfdt(GftHWnd(), &rd);
    fnv->SftIntFifld(tbrgft, AwtComponfnt::widtiID,  (jint)rd.rigit);
    fnv->SftIntFifld(tbrgft, AwtComponfnt::ifigitID, (jint)rd.bottom);

    fnv->DflftfLodblRff(tbrgft);
    fnv->DflftfLodblRff(pbrfnt);
}

jobjfdt AwtCioidf::PrfffrrfdItfmSizf(JNIEnv *fnv)
{
    jobjfdt dimfnsion = JNU_CbllMftiodByNbmf(fnv, NULL, GftPffr(fnv),
                                             "prfffrrfdSizf",
                                             "()Ljbvb/bwt/Dimfnsion;").l;
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    CHECK_NULL_RETURN(dimfnsion, NULL);

    /* Tiis sizf is window sizf of dioidf bnd it's too big for fbdi
     * drop down itfm ifigit.
     */
    fnv->SftIntFifld(dimfnsion, AwtDimfnsion::ifigitID,
                       GftFontHfigit(fnv));
    rfturn dimfnsion;
}

void AwtCioidf::SftFont(AwtFont* font)
{
    AwtComponfnt::SftFont(font);

    //Gft tif tfxt mftrids bnd dibngf tif ifigit of fbdi itfm.
    HDC iDC = ::GftDC(GftHWnd());
    DASSERT(iDC != NULL);
    TEXTMETRIC tm;

    HANDLE iFont = font->GftHFont();
    VERIFY(::SflfdtObjfdt(iDC, iFont) != NULL);
    VERIFY(::GftTfxtMftrids(iDC, &tm));
    long i = tm.tmHfigit + tm.tmExtfrnblLfbding;
    VERIFY(::RflfbsfDC(GftHWnd(), iDC) != 0);

    int nCount = (int)::SfndMfssbgf(GftHWnd(), CB_GETCOUNT, 0, 0);
    for(int i = 0; i < nCount; ++i) {
        VERIFY(::SfndMfssbgf(GftHWnd(), CB_SETITEMHEIGHT, i, MAKELPARAM(i, 0)) != CB_ERR);
    }
    //Cibngf tif ifigit of tif Edit Box.
    VERIFY(::SfndMfssbgf(GftHWnd(), CB_SETITEMHEIGHT, (UINT)-1,
                         MAKELPARAM(i, 0)) != CB_ERR);

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jobjfdt tbrgft = GftTbrgft(fnv);
    jint ifigit = fnv->GftIntFifld(tbrgft, AwtComponfnt::ifigitID);

    Rfsibpf(fnv->GftIntFifld(tbrgft, AwtComponfnt::xID),
            fnv->GftIntFifld(tbrgft, AwtComponfnt::yID),
            fnv->GftIntFifld(tbrgft, AwtComponfnt::widtiID),
            i);

    fnv->DflftfLodblRff(tbrgft);
}

stbtid int lbstClidkX = -1;
stbtid int lbstClidkY = -1;

LRESULT CALLBACK AwtCioidf::ListWindowProd(HWND iwnd, UINT mfssbgf,
                                           WPARAM wPbrbm, LPARAM lPbrbm)
{
    /*
     * Wf don't pbss tif dioidf WM_LBUTTONDOWN mfssbgf. As tif rfsult tif dioidf's list
     * dofsn't forwbrd mousf mfssbgfs it dbpturfs. Bflow wf do forwbrd wibt wf nffd.
     */

    TRY;

    DASSERT(::IsWindow(iwnd));

    switdi (mfssbgf) {
        dbsf WM_LBUTTONDOWN: {
            DWORD durPos = ::GftMfssbgfPos();
            lbstClidkX = GET_X_LPARAM(durPos);
            lbstClidkY = GET_Y_LPARAM(durPos);
            brfbk;
        }
        dbsf WM_MOUSEMOVE: {
            RECT rfdt;
            ::GftClifntRfdt(iwnd, &rfdt);

            POINT pt = {GET_X_LPARAM(lPbrbm), GET_Y_LPARAM(lPbrbm)};
            if (::PtInRfdt(&rfdt, pt)) {
                sm_isMousfMovfInList = TRUE;
            }

            POINT lbstPt = {lbstClidkX, lbstClidkY};
            ::SdrffnToClifnt(iwnd, &lbstPt);
            if (::PtInRfdt(&rfdt, lbstPt)) {
                brfbk; // ignorf wifn drbgging insidf tif list
            }
        }
        dbsf WM_LBUTTONUP: {
            lbstClidkX = -1;
            lbstClidkY = -1;

            AwtCioidf *d = (AwtCioidf *)::GftWindowLongPtr(iwnd, GWLP_USERDATA);
            if (d != NULL) {
                // forwbrd tif msg to tif dioidf
                d->WindowProd(mfssbgf, wPbrbm, lPbrbm);
            }
        }
    }
    rfturn ComCtl32Util::GftInstbndf().DffWindowProd(NULL, iwnd, mfssbgf, wPbrbm, lPbrbm);

    CATCH_BAD_ALLOC_RET(0);
}


MsgRouting AwtCioidf::WmNotify(UINT notifyCodf)
{
    if (notifyCodf == CBN_SELCHANGE) {
        int sflfdtfdIndfx = (int)SfndMfssbgf(CB_GETCURSEL);

        JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
        jobjfdt tbrgft = GftTbrgft(fnv);
        int prfviousIndfx = fnv->GftIntFifld(tbrgft, sflfdtfdIndfxID);

        if (sflfdtfdIndfx != CB_ERR && sflfdtfdIndfx != prfviousIndfx){
            DoCbllbbdk("ibndlfAdtion", "(I)V", sflfdtfdIndfx);
        }
    } flsf if (notifyCodf == CBN_DROPDOWN) {

        if (m_iList == NULL) {
            COMBOBOXINFO dbi;
            dbi.dbSizf = sizfof(COMBOBOXINFO);
            ::GftComboBoxInfo(GftHWnd(), &dbi);
            m_iList = dbi.iwndList;
            m_listDffWindowProd = ComCtl32Util::GftInstbndf().SubdlbssHWND(m_iList, ListWindowProd);
            DASSERT(::GftWindowLongPtr(m_iList, GWLP_USERDATA) == NULL);
            ::SftWindowLongPtr(m_iList, GWLP_USERDATA, (LONG_PTR)tiis);
        }
        sm_isMousfMovfInList = FALSE;

        // Clidking in tif dropdown list stfbls fodus from tif proxy.
        // So, sft tif fodus-rfstorf flbg up.
        SftRfstorfFodus(TRUE);
    } flsf if (notifyCodf == CBN_CLOSEUP) {
        SftRfstorfFodus(FALSE);
    }
    rfturn mrDoDffbult;
}

MsgRouting
AwtCioidf::OwnfrDrbwItfm(UINT /*dtrlId*/, DRAWITEMSTRUCT& drbwInfo)
{
    DrbwListItfm((JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2), drbwInfo);
    rfturn mrConsumf;
}

MsgRouting
AwtCioidf::OwnfrMfbsurfItfm(UINT /*dtrlId*/, MEASUREITEMSTRUCT& mfbsurfInfo)
{
    MfbsurfListItfm((JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2), mfbsurfInfo);
    rfturn mrConsumf;
}

/* Bug #4338368: wifn b dioidf losfs fodus, it triggfrs spurious MousfUp fvfnt,
 * fvfn if tif fodus wbs lost duf to TAB kfy prfssing
 */

MsgRouting
AwtCioidf::WmKillFodus(HWND iWndGotFodus)
{
    skipNfxtMousfUp = TRUE;
    rfturn AwtComponfnt::WmKillFodus(iWndGotFodus);
}

MsgRouting
AwtCioidf::WmMousfUp(UINT flbgs, int x, int y, int button)
{
    if (skipNfxtMousfUp) {
        skipNfxtMousfUp = FALSE;
        rfturn mrDoDffbult;
    }
    rfturn AwtComponfnt::WmMousfUp(flbgs, x, y, button);
}

MsgRouting AwtCioidf::HbndlfEvfnt(MSG *msg, BOOL syntiftid)
{
    if (IsFodusingMousfMfssbgf(msg)) {
        SfndMfssbgf(CB_SHOWDROPDOWN, ~SfndMfssbgf(CB_GETDROPPEDSTATE, 0, 0), 0);
        dflftf msg;
        rfturn mrConsumf;
    }
    // To simulbtf tif nbtivf bfibvior, wf dlosf tif list on WM_LBUTTONUP if
    // WM_MOUSEMOVE ibs bffn dfdfdtfd on tif list sindf it ibs bffn droppfd down.
    if (msg->mfssbgf == WM_LBUTTONUP && SfndMfssbgf(CB_GETDROPPEDSTATE, 0, 0) &&
        sm_isMousfMovfInList)
    {
        SfndMfssbgf(CB_SHOWDROPDOWN, FALSE, 0);
    }
    rfturn AwtComponfnt::HbndlfEvfnt(msg, syntiftid);
}

BOOL AwtCioidf::InifritsNbtivfMousfWifflBfibvior() {rfturn truf;}

void AwtCioidf::_Rfsibpf(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    RfsibpfStrudt *rs = (RfsibpfStrudt *)pbrbm;
    jobjfdt dioidf = rs->dioidf;
    jint x = rs->x;
    jint y = rs->y;
    jint widti = rs->widti;
    jint ifigit = rs->ifigit;

    AwtCioidf *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(dioidf, donf);

    d = (AwtCioidf *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        d->Rfsibpf(x, y, widti, ifigit);
        d->VfrifyStbtf();
    }

donf:
    fnv->DflftfGlobblRff(dioidf);

    dflftf rs;
}

void AwtCioidf::_Sflfdt(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SflfdtStrudt *ss = (SflfdtStrudt *)pbrbm;
    jobjfdt dioidf = ss->dioidf;
    jint indfx = ss->indfx;

    AwtCioidf *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(dioidf, donf);

    d = (AwtCioidf *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        d->SfndMfssbgf(CB_SETCURSEL, indfx);
//        d->VfrifyStbtf();
    }

donf:
    fnv->DflftfGlobblRff(dioidf);

    dflftf ss;
}

void AwtCioidf::_AddItfms(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    AddItfmsStrudt *bis = (AddItfmsStrudt *)pbrbm;
    jobjfdt dioidf = bis->dioidf;
    jobjfdtArrby itfms = bis->itfms;
    jint indfx = bis->indfx;

    AwtCioidf *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(dioidf, donf);
    JNI_CHECK_NULL_GOTO(itfms, "null itfms", donf);

    d = (AwtCioidf *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        jsizf i;
        int itfmCount = fnv->GftArrbyLfngti(itfms);
        if (itfmCount > 0) {
           d->SfndMfssbgf(WM_SETREDRAW, (WPARAM)FALSE, 0);
           for (i = 0; i < itfmCount; i++)
           {
               jstring itfm = (jstring)fnv->GftObjfdtArrbyElfmfnt(itfms, i);
               if (fnv->ExdfptionCifdk()) goto donf;
               if (itfm == NULL) goto nfxt_flfm;
               d->SfndMfssbgf(CB_INSERTSTRING, indfx + i, JbvbStringBufffr(fnv, itfm));
               fnv->DflftfLodblRff(itfm);
nfxt_flfm:
               ;
           }
           d->SfndMfssbgf(WM_SETREDRAW, (WPARAM)TRUE, 0);
           InvblidbtfRfdt(d->GftHWnd(), NULL, TRUE);
           d->RfsftDropDownHfigit();
           d->VfrifyStbtf();
        }
    }

donf:
    fnv->DflftfGlobblRff(dioidf);
    fnv->DflftfGlobblRff(itfms);

    dflftf bis;
}

void AwtCioidf::_Rfmovf(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    RfmovfStrudt *rs = (RfmovfStrudt *)pbrbm;
    jobjfdt dioidf = rs->dioidf;
    jint indfx = rs->indfx;

    AwtCioidf *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(dioidf, donf);

    d = (AwtCioidf *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        d->SfndMfssbgf(CB_DELETESTRING, indfx, 0);
        d->RfsftDropDownHfigit();
        d->VfrifyStbtf();
    }

donf:
    fnv->DflftfGlobblRff(dioidf);

    dflftf rs;
}

void AwtCioidf::_RfmovfAll(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt dioidf = (jobjfdt)pbrbm;

    AwtCioidf *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(dioidf, donf);

    d = (AwtCioidf *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        d->SfndMfssbgf(CB_RESETCONTENT, 0, 0);
        d->RfsftDropDownHfigit();
        d->VfrifyStbtf();
    }

donf:
    fnv->DflftfGlobblRff(dioidf);
}

void AwtCioidf::_ClosfList(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt dioidf = (jobjfdt)pbrbm;

    AwtCioidf *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(dioidf, donf);

    d = (AwtCioidf *)pDbtb;
    if (::IsWindow(d->GftHWnd()) && d->SfndMfssbgf(CB_GETDROPPEDSTATE, 0, 0)) {
        d->SfndMfssbgf(CB_SHOWDROPDOWN, FALSE, 0);
    }

donf:
    fnv->DflftfGlobblRff(dioidf);
}

/************************************************************************
 * WCioidfPffr nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Cioidf_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;
    sflfdtfdIndfxID = fnv->GftFifldID(dls, "sflfdtfdIndfx", "I");
    DASSERT(sflfdtfdIndfxID);
    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCioidfPffr
 * Mftiod:    sflfdt
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCioidfPffr_sflfdt(JNIEnv *fnv, jobjfdt sflf,
                                        jint indfx)
{
    TRY;

    SflfdtStrudt *ss = nfw SflfdtStrudt;
    ss->dioidf = fnv->NfwGlobblRff(sflf);
    ss->indfx = indfx;

    AwtToolkit::GftInstbndf().SyndCbll(AwtCioidf::_Sflfdt, ss);
    // globbl rffs bnd ss brf rfmovfd in _Sflfdt

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCioidfPffr
 * Mftiod:    rfmovf
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCioidfPffr_rfmovf(JNIEnv *fnv, jobjfdt sflf,
                                        jint indfx)
{
    TRY;

    RfmovfStrudt *rs = nfw RfmovfStrudt;
    rs->dioidf = fnv->NfwGlobblRff(sflf);
    rs->indfx = indfx;

    AwtToolkit::GftInstbndf().SyndCbll(AwtCioidf::_Rfmovf, rs);
    // globbl rff bnd rs brf dflftfd in _Rfmovf

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCioidfPffr
 * Mftiod:    rfmovfAll
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCioidfPffr_rfmovfAll(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtCioidf::_RfmovfAll, (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _RfmovfAll

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCioidfPffr
 * Mftiod:    bddItfms
 * Signbturf: ([Ljbvb/lbng/String;I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCioidfPffr_bddItfms(JNIEnv *fnv, jobjfdt sflf,
                                          jobjfdtArrby itfms, jint indfx)
{
    TRY;

    AddItfmsStrudt *bis = nfw AddItfmsStrudt;
    bis->dioidf = fnv->NfwGlobblRff(sflf);
    bis->itfms = (jobjfdtArrby)fnv->NfwGlobblRff(itfms);
    bis->indfx = indfx;

    AwtToolkit::GftInstbndf().SyndCbll(AwtCioidf::_AddItfms, bis);
    // globbl rffs bnd bis brf dflftfd in _AddItfms

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCioidfPffr
 * Mftiod:    rfsibpf
 * Signbturf: (IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCioidfPffr_rfsibpf(JNIEnv *fnv, jobjfdt sflf,
                                         jint x, jint y,
                                         jint widti, jint ifigit)
{
    TRY;

    RfsibpfStrudt *rs = nfw RfsibpfStrudt;
    rs->dioidf = fnv->NfwGlobblRff(sflf);
    rs->x = x;
    rs->y = y;
    rs->widti = widti;
    rs->ifigit = ifigit;

    AwtToolkit::GftInstbndf().SyndCbll(AwtCioidf::_Rfsibpf, rs);
    // globbl rff bnd rs brf dflftfd in _Rfsibpf

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCioidfPffr
 * Mftiod:    drfbtf
 * Signbturf: (Lsun/bwt/windows/WComponfntPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCioidfPffr_drfbtf(JNIEnv *fnv, jobjfdt sflf,
                                        jobjfdt pbrfnt)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrfnt);
    AwtToolkit::CrfbtfComponfnt(sflf, pbrfnt,
                                (AwtToolkit::ComponfntFbdtory)
                                AwtCioidf::Crfbtf);
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCioidfPffr
 * Mftiod:    dlosfList
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCioidfPffr_dlosfList(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtCioidf::_ClosfList, (void *)sflfGlobblRff);
    // globbl rff is dflftfd in _ClosfList

    CATCH_BAD_ALLOC;
}
} /* fxtfrn "C" */


/************************************************************************
 * Dibgnostid routinfs
 */

#ifdff DEBUG

void AwtCioidf::VfrifyStbtf()
{
    if (AwtToolkit::GftInstbndf().VfrifyComponfnts() == FALSE) {
        rfturn;
    }

    if (m_dbllbbdksEnbblfd == FALSE) {
        /* Componfnt is not fully sftup yft. */
        rfturn;
    }

    AwtComponfnt::VfrifyStbtf();
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->PusiLodblFrbmf(1) < 0)
        rfturn;

    jobjfdt tbrgft = GftTbrgft(fnv);

    // To bvoid possibly running dlifnt dodf on tif toolkit tirfbd, don't
    // do tif following difdks if wf'rf running on tif toolkit tirfbd.
    if (AwtToolkit::MbinTirfbd() != ::GftCurrfntTirfbdId()) {
        // Compbrf numbfr of itfms.
        int nTbrgftItfms = JNU_CbllMftiodByNbmf(fnv, NULL, tbrgft,
                                                "dountItfms", "()I").i;
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));
        int nPffrItfms = (int)::SfndMfssbgf(GftHWnd(), CB_GETCOUNT, 0, 0);
        DASSERT(nTbrgftItfms == nPffrItfms);

        // Compbrf sflfdtion
        int tbrgftIndfx = JNU_CbllMftiodByNbmf(fnv, NULL, tbrgft,
                                               "gftSflfdtfdIndfx", "()I").i;
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));
        int pffrCurSfl = (int)::SfndMfssbgf(GftHWnd(), CB_GETCURSEL, 0, 0);
        DASSERT(tbrgftIndfx == pffrCurSfl);
    }
    fnv->PopLodblFrbmf(0);
}
#fndif //DEBUG
