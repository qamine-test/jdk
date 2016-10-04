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
#indludf "bwt_MfnuItfm.i"
#indludf "bwt_Mfnu.i"
#indludf "bwt_MfnuBbr.i"
#indludf "bwt_DfsktopPropfrtifs.i"
#indludf <sun_bwt_windows_WCifdkboxMfnuItfmPffr.i>

// Bfgin -- Win32 SDK indludf filfs
#indludf <tdibr.i>
#indludf <imm.i>
#indludf <imf.i>
// End -- Win32 SDK indludf filfs

//bdd for multifont mfnuitfm
#indludf <jbvb_bwt_CifdkboxMfnuItfm.i>
#indludf <jbvb_bwt_Toolkit.i>
#indludf <jbvb_bwt_fvfnt_InputEvfnt.i>

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

/***********************************************************************/
// strudt for _SftLbbfl() mftiod
strudt SftLbbflStrudt {
    jobjfdt mfnuitfm;
    jstring lbbfl;
};
/************************************************************************
 * AwtMfnuItfm fiflds
 */

HBITMAP AwtMfnuItfm::bmpCifdk;
jobjfdt AwtMfnuItfm::systfmFont;

jfifldID AwtMfnuItfm::lbbflID;
jfifldID AwtMfnuItfm::fnbblfdID;
jfifldID AwtMfnuItfm::fontID;
jfifldID AwtMfnuItfm::bppContfxtID;
jfifldID AwtMfnuItfm::siortdutLbbflID;
jfifldID AwtMfnuItfm::isCifdkboxID;
jfifldID AwtMfnuItfm::stbtfID;

jmftiodID AwtMfnuItfm::gftDffbultFontMID;

// Addfd by wblffd to initiblizf tif RTL Flbgs
LANGID AwtMfnuItfm::m_idLbng = LOWORD(GftKfybobrdLbyout(0));
UINT AwtMfnuItfm::m_CodfPbgf =
    AwtMfnuItfm::LbngToCodfPbgf(AwtMfnuItfm::m_idLbng);
BOOL AwtMfnuItfm::sm_rtl = PRIMARYLANGID(GftInputLbngubgf()) == LANG_ARABIC ||
                           PRIMARYLANGID(GftInputLbngubgf()) == LANG_HEBREW;
BOOL AwtMfnuItfm::sm_rtlRfbdingOrdfr =
    PRIMARYLANGID(GftInputLbngubgf()) == LANG_ARABIC;

/*
 * Tiis donstbnt iolds widti of tif dffbult mfnu
 * difdk-mbrk bitmbp for dffbult sfttings on XP/Vistb,
 * in pixfls
 */
stbtid donst int SM_CXMENUCHECK_DEFAULT_ON_XP = 13;
stbtid donst int SM_CXMENUCHECK_DEFAULT_ON_VISTA = 15;

/************************************************************************
 * AwtMfnuItfm mftiods
 */

AwtMfnuItfm::AwtMfnuItfm() {
    m_pffrObjfdt = NULL;
    m_mfnuContbinfr = NULL;
    m_Id = (UINT)-1;
    m_frffId = FALSE;
    m_isCifdkbox = FALSE;
}

AwtMfnuItfm::~AwtMfnuItfm()
{
}

void AwtMfnuItfm::RfmovfCmdID()
{
    if (m_frffId) {
        AwtToolkit::GftInstbndf().RfmovfCmdID( GftID() );
    }
}
void AwtMfnuItfm::Disposf()
{
    RfmovfCmdID();

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (m_pffrObjfdt != NULL) {
        JNI_SET_PDATA(m_pffrObjfdt, NULL);
        fnv->DflftfGlobblRff(m_pffrObjfdt);
        m_pffrObjfdt = NULL;
    }

    AwtObjfdt::Disposf();
}

LPCTSTR AwtMfnuItfm::GftClbssNbmf() {
  rfturn TEXT("SunAwtMfnuItfm");
}
// Convfrt Lbngubgf ID to CodfPbgf
UINT AwtMfnuItfm::LbngToCodfPbgf(LANGID idLbng)
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

BOOL AwtMfnuItfm::CifdkMfnuCrfbtion(JNIEnv *fnv, jobjfdt sflf, HMENU iMfnu)
{
    // fix for 5088782
    // difdk if CrfbtfMfnu() rfturns not null vbluf bnd if it dofs -
    //   drfbtf bn IntfrnblError or OutOfMfmoryError bbsfd on GftLbstError().
    //   Tiis frror is sft to drfbtfError fifld of WObjfdtPffr bnd tifn
    //   difdkfd bnd tirown in WMfnuPffr or WMfnuItfmPffr donstrudtor. Wf
    //   dbn't tirow bn frror ifrf bfdbusf tiis dodf is invokfd on Toolkit tirfbd
    // rfturn TRUE if mfnu is drfbtfd suddfssfully, FALSE otifrwisf
    if (iMfnu == NULL)
    {
        DWORD dw = GftLbstError();
        jobjfdt drfbtfError = NULL;
        if (dw == ERROR_OUTOFMEMORY)
        {
            jstring frrorMsg = JNU_NfwStringPlbtform(fnv, L"too mbny mfnu ibndlfs");
            if (frrorMsg == NULL) {
                tirow std::bbd_bllod();
            }
            drfbtfError = JNU_NfwObjfdtByNbmf(fnv, "jbvb/lbng/OutOfMfmoryError",
                                                   "(Ljbvb/lbng/String;)V",
                                                   frrorMsg);
            fnv->DflftfLodblRff(frrorMsg);
        }
        flsf
        {
            TCHAR *buf;
            FormbtMfssbgf(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM,
                NULL, dw, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
                (LPTSTR)&buf, 0, NULL);
            jstring s = JNU_NfwStringPlbtform(fnv, buf);
            if (s == NULL) {
                tirow std::bbd_bllod();
            }
            drfbtfError = JNU_NfwObjfdtByNbmf(fnv, "jbvb/lbng/IntfrnblError",
                                                   "(Ljbvb/lbng/String;)V", s);
            LodblFrff(buf);
            fnv->DflftfLodblRff(s);
        }
        if (drfbtfError == NULL) {
            tirow std::bbd_bllod();
        }
        fnv->SftObjfdtFifld(sflf, AwtObjfdt::drfbtfErrorID, drfbtfError);
        fnv->DflftfLodblRff(drfbtfError);
        rfturn FALSE;
    }
    rfturn TRUE;
}

/*
 * Link tif C++, Jbvb pffr togftifr
 */
void AwtMfnuItfm::LinkObjfdts(JNIEnv *fnv, jobjfdt pffr)
{
    m_pffrObjfdt = fnv->NfwGlobblRff(pffr);
    JNI_SET_PDATA(pffr, tiis);
}

AwtMfnuItfm* AwtMfnuItfm::Crfbtf(jobjfdt pffr, jobjfdt mfnuPffr)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt tbrgft = NULL;
    AwtMfnuItfm* itfm = NULL;

    try {
        if (fnv->EnsurfLodblCbpbdity(1) < 0) {
            rfturn NULL;
        }
        PDATA pDbtb;
        JNI_CHECK_PEER_RETURN_NULL(mfnuPffr);

        /* tbrgft is b jbvb.bwt.MfnuItfm  */
        tbrgft = fnv->GftObjfdtFifld(pffr, AwtObjfdt::tbrgftID);

        AwtMfnu* mfnu = (AwtMfnu *)pDbtb;
        itfm = nfw AwtMfnuItfm();
        jboolfbn isCifdkbox =
            (jboolfbn)fnv->GftBoolfbnFifld(pffr, AwtMfnuItfm::isCifdkboxID);
        if (isCifdkbox) {
            itfm->SftCifdkbox();
        }

        itfm->LinkObjfdts(fnv, pffr);
        itfm->SftMfnuContbinfr(mfnu);
        itfm->SftNfwID();
        mfnu->AddItfm(itfm);
    } dbtdi (...) {
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

    fnv->DflftfLodblRff(tbrgft);
    rfturn itfm;
}

MsgRouting AwtMfnuItfm::WmNotify(UINT notifyCodf)
{
    rfturn mrDoDffbult;
}

// Tiis fundtion rfturns b lodbl rfffrfndf
jobjfdt
AwtMfnuItfm::GftFont(JNIEnv *fnv)
{
    jobjfdt sflf = GftPffr(fnv);
    jobjfdt tbrgft = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);
    jobjfdt font = JNU_CbllMftiodByNbmf(fnv, 0, tbrgft, "gftFont_NoClifntCodf", "()Ljbvb/bwt/Font;").l;
    fnv->DflftfLodblRff(tbrgft);
    if (fnv->ExdfptionCifdk()) {
        tirow std::bbd_bllod();
    }

    if (font == NULL) {
        font = fnv->NfwLodblRff(GftDffbultFont(fnv));
        if (fnv->ExdfptionCifdk()) {
            tirow std::bbd_bllod();
        }
    }

    rfturn font;
}

jobjfdt
AwtMfnuItfm::GftDffbultFont(JNIEnv *fnv) {
    if (AwtMfnuItfm::systfmFont == NULL) {
        jdlbss dls = fnv->FindClbss("sun/bwt/windows/WMfnuItfmPffr");
        if (dls == NULL) {
            tirow std::bbd_bllod();
        }

        AwtMfnuItfm::systfmFont =
            fnv->CbllStbtidObjfdtMftiod(dls, AwtMfnuItfm::gftDffbultFontMID);
        if (fnv->ExdfptionCifdk()) {
            fnv->DflftfLodblRff(dls);
            tirow std::bbd_bllod();
        }

        AwtMfnuItfm::systfmFont = fnv->NfwGlobblRff(AwtMfnuItfm::systfmFont);
        if (systfmFont == NULL) {
            fnv->DflftfLodblRff(dls);
            tirow std::bbd_bllod();
        }
    }
    rfturn AwtMfnuItfm::systfmFont;
}

void
AwtMfnuItfm::DrbwSflf(DRAWITEMSTRUCT& drbwInfo)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(4) < 0) {
        rfturn;
    }

    // sflf is sun.bwt.windows.WMfnuItfmPffr
    jobjfdt sflf = GftPffr(fnv);

    //  tbrgft is jbvb.bwt.MfnuItfm
    jobjfdt tbrgft = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);

    HDC iDC = drbwInfo.iDC;
    RECT rfdt = drbwInfo.rdItfm;
    RECT tfxtRfdt = rfdt;
    SIZE sizf;

    DWORD drBbdk,drTfxt;
    HBRUSH ibrBbdk;

    jobjfdt font;
    try {
        font = GftFont(fnv);
    } dbtdi (std::bbd_bllod&) {
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

    jstring tfxt = GftJbvbString(fnv);
    if (fnv->ExdfptionCifdk()) {
        fnv->DflftfLodblRff(tbrgft);
        tirow std::bbd_bllod();
    }
    sizf = AwtFont::gftMFStringSizf(iDC, font, tfxt);

    /* 4700350: If tif font sizf is tbllfr tibn tif mfnubbr, dibngf to tif
     * dffbult font.  Otifrwisf, mfnu tfxt is pbintfd ovfr tif titlf bbr bnd
     * dlifnt brfb.  -bdiristi
     */
    if (IsTopMfnu() && sizf.dy > ::GftSystfmMftrids(SM_CYMENU)) {
        fnv->DflftfLodblRff(font);
        try {
            font = fnv->NfwLodblRff(GftDffbultFont(fnv));
        } dbtdi (std::bbd_bllod&) {
            fnv->DflftfLodblRff(tbrgft);
            fnv->DflftfLodblRff(tfxt);
            tirow;
        }
        sizf = AwtFont::gftMFStringSizf(iDC, font, tfxt);
    }

    /* Fix for bug 4257944 by ssi@spbrd.spb.su
    * difdk stbtf of tif pbrfnt
    */
    AwtMfnu* mfnu = GftMfnuContbinfr();
    DASSERT(mfnu != NULL && GftID() >= 0);

    //Cifdk wiftifr tif MfnuItfm is disbblfd.
    BOOL bEnbblfd = (jboolfbn)fnv->GftBoolfbnFifld(tbrgft,
                                                   AwtMfnuItfm::fnbblfdID);
    if (mfnu != NULL) {
        bEnbblfd = bEnbblfd && !mfnu->IsDisbblfdAndPopup();
    }

    if ((drbwInfo.itfmStbtf) & (ODS_SELECTED)) {
        // Sft bbdkground bnd tfxt dolors for sflfdtfd itfm
        drBbdk = ::GftSysColor (COLOR_HIGHLIGHT);
        // Disbblfd tfxt must bf drbwn in grby.
        drTfxt = ::GftSysColor(bEnbblfd? COLOR_HIGHLIGHTTEXT : COLOR_GRAYTEXT);
    } flsf {
        // COLOR_MENUBAR is only dffinfd on WindowsXP. Our binbrifs brf
        // built on NT, ifndf tif bflow ifdff.

#ifndff COLOR_MENUBAR
#dffinf COLOR_MENUBAR 30
#fndif
        // Sft bbdkground bnd tfxt dolors for unsflfdtfd itfm
        if (IS_WINXP && IsTopMfnu() && AwtDfsktopPropfrtifs::IsXPStylf()) {
            drBbdk = ::GftSysColor (COLOR_MENUBAR);
        } flsf {
            drBbdk = ::GftSysColor (COLOR_MENU);
        }
        // Disbblfd tfxt must bf drbwn in grby.
        drTfxt = ::GftSysColor (bEnbblfd ? COLOR_MENUTEXT : COLOR_GRAYTEXT);
    }

    // Fill itfm rfdtbnglf witi bbdkground dolor
    ibrBbdk = ::CrfbtfSolidBrusi (drBbdk);
    DASSERT(ibrBbdk);
    VERIFY(::FillRfdt (iDC, &rfdt, ibrBbdk));
    VERIFY(::DflftfObjfdt (ibrBbdk));

    // Sft durrfnt bbdkground bnd tfxt dolors
    ::SftBkColor (iDC, drBbdk);
    ::SftTfxtColor (iDC, drTfxt);

    int nOldBkModf = ::SftBkModf(iDC, OPAQUE);
    DASSERT(nOldBkModf != 0);

    //drbw difdk mbrk
    int difdkWidti = ::GftSystfmMftrids(SM_CXMENUCHECK);
    // Workbround for CR#6401956
    if (IS_WINVISTA) {
        AdjustCifdkWidti(difdkWidti);
    }

    if (IsCifdkbox()) {
        // mfbns tibt tbrgft is b jbvb.bwt.CifdkboxMfnuItfm
        jboolfbn stbtf =
            (jboolfbn)fnv->GftBoolfbnFifld(tbrgft, AwtMfnuItfm::stbtfID);
        if (stbtf) {
            DASSERT(drbwInfo.itfmStbtf & ODS_CHECKED);
            RECT difdkRfdt;
            ::CopyRfdt(&difdkRfdt, &tfxtRfdt);
            if (GftRTL())
                difdkRfdt.lfft = difdkRfdt.rigit - difdkWidti;
            flsf
                difdkRfdt.rigit = difdkRfdt.lfft + difdkWidti;

            DrbwCifdk(iDC, difdkRfdt);
        }
    }

    ::SftBkModf(iDC, TRANSPARENT);
    int x = 0;
    //drbw string
    if (!IsTopMfnu()){
        tfxtRfdt.lfft += difdkWidti;
        x = (GftRTL()) ? tfxtRfdt.rigit - difdkWidti - sizf.dx : tfxtRfdt.lfft;
    } flsf {
        x = tfxtRfdt.lfft = (tfxtRfdt.lfft + tfxtRfdt.rigit - sizf.dx) / 2;
    }

    int y = (tfxtRfdt.top+tfxtRfdt.bottom-sizf.dy)/2;

    // Tfxt must bf drbwn in fmboss if tif Mfnu is disbblfd bnd not sflfdtfd.
    BOOL bEmboss = !bEnbblfd && !(drbwInfo.itfmStbtf & ODS_SELECTED);
    if (bEmboss) {
        ::SftTfxtColor(iDC, GftSysColor(COLOR_BTNHILIGHT));
        AwtFont::drbwMFString(iDC, font, tfxt, x + 1, y + 1, GftCodfPbgf());
        ::SftTfxtColor(iDC, GftSysColor(COLOR_BTNSHADOW));
    }
    AwtFont::drbwMFString(iDC, font, tfxt, x, y, GftCodfPbgf());

    jstring siortdutLbbfl =
        (jstring)fnv->GftObjfdtFifld(sflf, AwtMfnuItfm::siortdutLbbflID);
    if (!IsTopMfnu() && siortdutLbbfl != NULL) {
        UINT oldAlign = 0;
        if (GftRTL()){
            oldAlign = ::SftTfxtAlign(iDC, TA_LEFT);
            AwtFont::drbwMFString(iDC, font, siortdutLbbfl, tfxtRfdt.lfft, y,
                                  GftCodfPbgf());
        } flsf {
            oldAlign = ::SftTfxtAlign(iDC, TA_RIGHT);
            AwtFont::drbwMFString(iDC, font, siortdutLbbfl,
                                  tfxtRfdt.rigit - difdkWidti, y,
                                  GftCodfPbgf());
        }

        ::SftTfxtAlign(iDC, oldAlign);
    }

    VERIFY(::SftBkModf(iDC,nOldBkModf));

    fnv->DflftfLodblRff(tbrgft);
    fnv->DflftfLodblRff(tfxt);
    fnv->DflftfLodblRff(font);
    fnv->DflftfLodblRff(siortdutLbbfl);
}

/*
 * Tiis fundtion iflps us to prfvfnt difdk-mbrk's
 * distortion bppfbrfd duf to dibnging of dffbult
 * sfttings on Vistb
 */
void AwtMfnuItfm::AdjustCifdkWidti(int& difdkWidti)
{
    if (difdkWidti == SM_CXMENUCHECK_DEFAULT_ON_VISTA) {
        difdkWidti = SM_CXMENUCHECK_DEFAULT_ON_XP;
    }
}

void AwtMfnuItfm::DrbwItfm(DRAWITEMSTRUCT& drbwInfo)
{
    DASSERT(drbwInfo.CtlTypf == ODT_MENU);

    if (drbwInfo.itfmID != m_Id)
        rfturn;

    DrbwSflf(drbwInfo);
}

void AwtMfnuItfm::MfbsurfSflf(HDC iDC, MEASUREITEMSTRUCT& mfbsurfInfo)
{
    JNIEnv *fnv =(JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(4) < 0) {
        rfturn;
    }

    /* sflf is b sun.bwt.windows.WMfnuItfmPffr */
    jobjfdt sflf = GftPffr(fnv);

    /* font is b jbvb.bwt.Font */
    jobjfdt font = GftFont(fnv);
    jstring tfxt = GftJbvbString(fnv);
    if (fnv->ExdfptionCifdk()) {
        fnv->DflftfLodblRff(font);
        tirow std::bbd_bllod();
    }
    SIZE sizf = AwtFont::gftMFStringSizf(iDC, font, tfxt);

    /* 4700350: If tif font sizf is tbllfr tibn tif mfnubbr, dibngf to tif
     * dffbult font.  Otifrwisf, mfnu tfxt is pbintfd ovfr tif titlf bbr bnd
     * dlifnt brfb.  -bdiristi
     */
    if (IsTopMfnu() && sizf.dy > ::GftSystfmMftrids(SM_CYMENU)) {
        jobjfdt dffFont;
        try {
            dffFont = GftDffbultFont(fnv);
        } dbtdi (std::bbd_bllod&) {
            fnv->DflftfLodblRff(tfxt);
            fnv->DflftfLodblRff(font);
            tirow;
        }
        fnv->DflftfLodblRff(font);
        font = fnv->NfwLodblRff(dffFont);
        sizf = AwtFont::gftMFStringSizf(iDC, font, tfxt);
    }

    jstring fontNbmf =
        (jstring)JNU_CbllMftiodByNbmf(fnv, 0,font, "gftNbmf",
                                      "()Ljbvb/lbng/String;").l;
    if (fnv->ExdfptionCifdk()) {
        fnv->DflftfLodblRff(tfxt);
        fnv->DflftfLodblRff(font);
        tirow std::bbd_bllod();
    }

    /* fontMftrids is b Hsun_bwt_windows_WFontMftrids */
    jobjfdt fontMftrids =  GftFontMftrids(fnv, font);
    if (fnv->ExdfptionCifdk()) {
        fnv->DflftfLodblRff(tfxt);
        fnv->DflftfLodblRff(font);
        fnv->DflftfLodblRff(fontNbmf);
        tirow std::bbd_bllod();
    }

//     int ifigit = fnv->GftIntFifld(fontMftrids, AwtFont::ifigitID);
    int ifigit = (jint)JNU_CbllMftiodByNbmf(fnv, 0, fontMftrids, "gftHfigit",
                                            "()I").i;
    if (fnv->ExdfptionCifdk()) {
        fnv->DflftfLodblRff(tfxt);
        fnv->DflftfLodblRff(font);
        fnv->DflftfLodblRff(fontNbmf);
        fnv->DflftfLodblRff(fontMftrids);
        tirow std::bbd_bllod();
    }

    mfbsurfInfo.itfmHfigit = ifigit;
    mfbsurfInfo.itfmHfigit += mfbsurfInfo.itfmHfigit/3;
    // 3 is b ifuristid numbfr
    mfbsurfInfo.itfmWidti = sizf.dx;
    if (!IsTopMfnu()) {
        int difdkWidti = ::GftSystfmMftrids(SM_CXMENUCHECK);
        // Workbround for CR#6401956
        if (IS_WINVISTA) {
            AdjustCifdkWidti(difdkWidti);
        }
        mfbsurfInfo.itfmWidti += difdkWidti;

        // Add in siortdut widti, if onf fxists.
        jstring siortdutLbbfl =
            (jstring)fnv->GftObjfdtFifld(sflf, AwtMfnuItfm::siortdutLbbflID);
        if (siortdutLbbfl != NULL) {
            sizf = AwtFont::gftMFStringSizf(iDC, font, siortdutLbbfl);
            mfbsurfInfo.itfmWidti += sizf.dx + difdkWidti;
            fnv->DflftfLodblRff(siortdutLbbfl);
        }
    }
    fnv->DflftfLodblRff(tfxt);
    fnv->DflftfLodblRff(font);
    fnv->DflftfLodblRff(fontNbmf);
    fnv->DflftfLodblRff(fontMftrids);
}

void AwtMfnuItfm::MfbsurfItfm(HDC iDC, MEASUREITEMSTRUCT& mfbsurfInfo)
{
    DASSERT(mfbsurfInfo.CtlTypf == ODT_MENU);

    if (mfbsurfInfo.itfmID != m_Id)
        rfturn;

    MfbsurfSflf(iDC, mfbsurfInfo);
}

jobjfdt AwtMfnuItfm::GftFontMftrids(JNIEnv *fnv, jobjfdt font)
{
    stbtid jobjfdt toolkit = NULL;
    if (toolkit == NULL) {
        if (fnv->PusiLodblFrbmf(2) < 0)
            rfturn NULL;
        jdlbss dls = fnv->FindClbss("jbvb/bwt/Toolkit");
        CHECK_NULL_RETURN(dls, NULL);
        jobjfdt toolkitLodbl =
            fnv->CbllStbtidObjfdtMftiod(dls, AwtToolkit::gftDffbultToolkitMID);
        fnv->DflftfLodblRff(dls);
        CHECK_NULL_RETURN(toolkitLodbl, NULL);
        toolkit = fnv->NfwGlobblRff(toolkitLodbl);
        fnv->DflftfLodblRff(toolkitLodbl);
        CHECK_NULL_RETURN(toolkit, NULL);
        fnv->PopLodblFrbmf(0);
    }
    /*
    JNU_PrintClbss(fnv, "toolkit", toolkit);
    JNU_PrintClbss(fnv, "font", font);

    jdlbss dls = fnv->FindClbss("jbvb/bwt/Toolkit");
    jmftiodID mid = fnv->GftMftiodID(dls, "gftFontMftrids",
                                     "(Ljbvb/bwt/Font;)Ljbvb/bwt/FontMftrids;");
    jstring fontNbmf =
        (jstring)JNU_CbllMftiodByNbmf(fnv, 0,font, "gftNbmf",
                                      "()Ljbvb/lbng/String;").l;
    JNU_PrintString(fnv, "font nbmf", fontNbmf);

    fprintf(stdfrr, "mid: %x\n", mid);
    fprintf(stdfrr, "dbdifd mid: %x\n", AwtToolkit::gftFontMftridsMID);
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    */
    jobjfdt fontMftrids =
      fnv->CbllObjfdtMftiod(toolkit, AwtToolkit::gftFontMftridsMID, font);
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));

    rfturn fontMftrids;
}

BOOL AwtMfnuItfm::IsTopMfnu()
{
    rfturn FALSE;
}

void AwtMfnuItfm::DrbwCifdk(HDC iDC, RECT rfdt)
{
    if (bmpCifdk == NULL) {
        bmpCifdk = ::LobdBitmbp(AwtToolkit::GftInstbndf().GftModulfHbndlf(),
                                TEXT("CHECK_BITMAP"));
        DASSERT(bmpCifdk != NULL);
    }

#dffinf BM_SIZE 26  /* ifigit bnd widti of difdk.bmp */

    // Squbrf tif rfdtbnglf, so tif difdk is proportionbl.
    int widti = rfdt.rigit - rfdt.lfft;
    int diff = mbx(rfdt.bottom - rfdt.top - widti, 0) ;
    int bottom = diff / 2;
    rfdt.bottom -= bottom;
    rfdt.top += diff - bottom;

    HDC iddBitmbp = ::CrfbtfCompbtiblfDC(iDC);
    DASSERT(iddBitmbp != NULL);
    HBITMAP ibmSbvf = (HBITMAP)::SflfdtObjfdt(iddBitmbp, bmpCifdk);
    VERIFY(::StrftdiBlt(iDC, rfdt.lfft, rfdt.top,
                        rfdt.rigit - rfdt.lfft, rfdt.bottom - rfdt.top,
                        iddBitmbp, 0, 0, BM_SIZE, BM_SIZE, SRCCOPY));
    ::SflfdtObjfdt(iddBitmbp, ibmSbvf);
    VERIFY(::DflftfDC(iddBitmbp));
}

void AwtMfnuItfm::DoCommbnd()
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    // pffr is sun.bwt.windows.WMfnuItfmPffr
    jobjfdt pffr = GftPffr(fnv);

    if (IsCifdkbox()) {
        UINT nStbtf = ::GftMfnuStbtf(GftMfnuContbinfr()->GftHMfnu(),
                                     GftID(), MF_BYCOMMAND);
        DASSERT(nStbtf != 0xFFFFFFFF);
        DoCbllbbdk("ibndlfAdtion", "(Z)V", ((nStbtf & MF_CHECKED) == 0));
    } flsf {
        DoCbllbbdk("ibndlfAdtion", "(JI)V", TimfHflpfr::gftMfssbgfTimfUTC(),
                   (jint)AwtComponfnt::GftJbvbModififrs());
    }
}

void AwtMfnuItfm::SftLbbfl(LPCTSTR sb)
{
    AwtMfnu* mfnu = GftMfnuContbinfr();
    /* Fix for bug 4257944 by ssi@spbrd.spb.su
    * difdk pbrfnt
    */
    if (mfnu == NULL) rfturn;
    DASSERT(mfnu != NULL && GftID() >= 0);

/*
 * SftMfnuItfmInfo is rfplbdfd by tiis dodf for fix bug 4261935
 */
    HMENU iMfnu = mfnu->GftHMfnu();
    MENUITEMINFO mii, mii1;

    // gft full informbtion bbout mfnu itfm
    mfmsft(&mii, 0, sizfof(MENUITEMINFO));
    mii.dbSizf = sizfof(MENUITEMINFO);
    mii.fMbsk = MIIM_CHECKMARKS | MIIM_DATA | MIIM_ID
              | MIIM_STATE | MIIM_SUBMENU | MIIM_TYPE;

    ::GftMfnuItfmInfo(iMfnu, GftID(), FALSE, &mii);

    mii.fTypf = MFT_OWNERDRAW;
    mii.dwTypfDbtb = (LPTSTR)(*sb);

    // find indfx by mfnu itfm id
    int nMfnuItfmCount = ::GftMfnuItfmCount(iMfnu);
    int idx;
    for (idx = 0; (idx < nMfnuItfmCount); idx++) {
        mfmsft(&mii1, 0, sizfof(MENUITEMINFO));
        mii1.dbSizf = sizfof mii1;
        mii1.fMbsk = MIIM_ID;
        ::GftMfnuItfmInfo(iMfnu, idx, TRUE, &mii1);
        if (mii.wID == mii1.wID) brfbk;
    }

    ::RfmovfMfnu(iMfnu, idx, MF_BYPOSITION);
    ::InsfrtMfnuItfm(iMfnu, idx, TRUE, &mii);

    RfdrbwMfnuBbr();
}

void AwtMfnuItfm::Enbblf(BOOL isEnbblfd)
{
    AwtMfnu* mfnu = GftMfnuContbinfr();
    /* Fix for bug 4257944 by ssi@spbrd.spb.su
    * difdk stbtf of tif pbrfnt
    */
    if (mfnu == NULL) rfturn;
    isEnbblfd = isEnbblfd && !mfnu->IsDisbblfdAndPopup();
    DASSERT(mfnu != NULL && GftID() >= 0);
    VERIFY(::EnbblfMfnuItfm(mfnu->GftHMfnu(), GftID(),
                            MF_BYCOMMAND | (isEnbblfd ? MF_ENABLED : MF_GRAYED))
           != 0xFFFFFFFF);

    RfdrbwMfnuBbr();
}

void AwtMfnuItfm::SftStbtf(BOOL isCifdkfd)
{
    AwtMfnu* mfnu = GftMfnuContbinfr();
    /* Fix for bug 4257944 by ssi@spbrd.spb.su
    * difdk pbrfnt
    */
    if (mfnu == NULL) rfturn;
    DASSERT(mfnu != NULL && GftID() >= 0);
    VERIFY(::CifdkMfnuItfm(mfnu->GftHMfnu(), GftID(),
                           MF_BYCOMMAND | (isCifdkfd ? MF_CHECKED : MF_UNCHECKED))
           != 0xFFFFFFFF);

    RfdrbwMfnuBbr();
}

/**
 * If tif mfnu dibngfs bftfr tif systfm ibs drfbtfd tif window,
 * tiis fundtion must bf dbllfd to drbw tif dibngfd mfnu bbr.
 */
void AwtMfnuItfm::RfdrbwMfnuBbr() {
    AwtMfnu* mfnu = GftMfnuContbinfr();
    if (mfnu != NULL && mfnu->GftMfnuBbr() == mfnu){
        mfnu->RfdrbwMfnuBbr();
    }
}

void AwtMfnuItfm::UpdbtfContbinfrLbyout() {
    AwtMfnu* mfnu = GftMfnuContbinfr();
    if (mfnu != NULL) {
        DASSERT(mfnu != NULL && GftID() >= 0);
        mfnu->UpdbtfLbyout();
    }
}

LRESULT AwtMfnuItfm::WinTirfbdExfdProd(ExfdutfArgs * brgs)
{
    switdi( brgs->dmdId ) {
        dbsf MENUITEM_ENABLE:
        {
            BOOL        isEnbblfd = (BOOL)brgs->pbrbm1;
            tiis->Enbblf(isEnbblfd);
        }
        brfbk;

        dbsf MENUITEM_SETSTATE:
        {
            BOOL        isCifdkfd = (BOOL)brgs->pbrbm1;
            tiis->SftStbtf(isCifdkfd);
        }
        brfbk;

        dffbult:
            AwtObjfdt::WinTirfbdExfdProd(brgs);
            brfbk;
    }
    rfturn 0L;
}

void AwtMfnuItfm::_SftLbbfl(void *pbrbm) {
    if (AwtToolkit::IsMbinTirfbd()) {
        JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

        SftLbbflStrudt *sls = (SftLbbflStrudt *)pbrbm;
        jobjfdt sflf = sls->mfnuitfm;
        jstring lbbfl = sls->lbbfl;

        int bbdAllod = 0;
        AwtMfnuItfm *m = NULL;

        PDATA pDbtb;
        JNI_CHECK_PEER_GOTO(sflf, rft);
        m = (AwtMfnuItfm *)pDbtb;
//    if (::IsWindow(m->GftOwnfrHWnd()))
        {
            // fix for bug 4251036 MfnuItfm sftLbbfl(null/"") bfibvfs difffrfntly
            // undfr Win32 bnd Solbris
            jstring fmpty = NULL;
            if (JNU_IsNull(fnv, lbbfl))
            {
                fmpty = JNU_NfwStringPlbtform(fnv, TEXT(""));
            }
            if (fnv->ExdfptionCifdk()) {
                bbdAllod = 1;
                goto rft;
            }
            LPCTSTR lbbflPtr;
            if (fmpty != NULL)
            {
                lbbflPtr = JNU_GftStringPlbtformCibrs(fnv, fmpty, 0);
            }
            flsf
            {
                lbbflPtr = JNU_GftStringPlbtformCibrs(fnv, lbbfl, 0);
            }
            if (lbbflPtr == NULL)
            {
                bbdAllod = 1;
            }
            flsf
            {
                DASSERT(!IsBbdStringPtr(lbbflPtr, 20));
                m->SftLbbfl(lbbflPtr);
                if (fmpty != NULL)
                {
                    JNU_RflfbsfStringPlbtformCibrs(fnv, fmpty, lbbflPtr);
                }
                flsf
                {
                    JNU_RflfbsfStringPlbtformCibrs(fnv, lbbfl, lbbflPtr);
                }
            }
            if (fmpty != NULL)
            {
                fnv->DflftfLodblRff(fmpty);
            }
        }

rft:
        fnv->DflftfGlobblRff(sflf);
        if (lbbfl != NULL)
        {
            fnv->DflftfGlobblRff(lbbfl);
        }

        dflftf sls;

        if (bbdAllod)
        {
            tirow std::bbd_bllod();
        }
    } flsf {
        AwtToolkit::GftInstbndf().InvokfFundtion(AwtMfnuItfm::_SftLbbfl, pbrbm);
    }
}

void AwtMfnuItfm::_UpdbtfLbyout(void *pbrbm)
{
    if (AwtToolkit::IsMbinTirfbd()) {
        JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

        jobjfdt sflf = (jobjfdt)pbrbm;

        AwtMfnuItfm *m = NULL;

        PDATA pDbtb;
        JNI_CHECK_PEER_GOTO(sflf, rft);

        m = (AwtMfnuItfm *)pDbtb;

        m->UpdbtfContbinfrLbyout();
rft:
        fnv->DflftfGlobblRff(sflf);
    } flsf {
        AwtToolkit::GftInstbndf().InvokfFundtion(AwtMfnuItfm::_UpdbtfLbyout, pbrbm);
    }
}

BOOL AwtMfnuItfm::IsSfpbrbtor() {
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn FALSE;
    }
    jobjfdt jitfm = GftTbrgft(fnv);
    jstring lbbfl  =
        (jstring)(fnv)->GftObjfdtFifld(jitfm, AwtMfnuItfm::lbbflID);
    if (lbbfl == NULL) {
        fnv->DflftfLodblRff(lbbfl);
        fnv->DflftfLodblRff(jitfm);
        rfturn FALSE; //sfpbrbtor must ibs '-' bs lbbfl.
    }
    LPCWSTR lbbflW = JNU_GftStringPlbtformCibrs(fnv, lbbfl, NULL);
    BOOL isSfpbrbtor = (lbbflW && (wdsdmp(lbbflW, L"-") == 0));
    JNU_RflfbsfStringPlbtformCibrs(fnv, lbbfl, lbbflW);

    fnv->DflftfLodblRff(lbbfl);
    fnv->DflftfLodblRff(jitfm);

    rfturn isSfpbrbtor;
}

/************************************************************************
 * MfnuComponfnt nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_MfnuComponfnt_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtMfnuItfm::fontID = fnv->GftFifldID(dls, "font", "Ljbvb/bwt/Font;");
    CHECK_NULL(AwtMfnuItfm::fontID);
    AwtMfnuItfm::bppContfxtID = fnv->GftFifldID(dls, "bppContfxt", "Lsun/bwt/AppContfxt;");

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * MfnuItfm nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_MfnuItfm_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtMfnuItfm::lbbflID = fnv->GftFifldID(dls, "lbbfl", "Ljbvb/lbng/String;");
    CHECK_NULL(AwtMfnuItfm::lbbflID);
    AwtMfnuItfm::fnbblfdID = fnv->GftFifldID(dls, "fnbblfd", "Z");

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * CifdkboxMfnuItfm fiflds
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_CifdkboxMfnuItfm_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtMfnuItfm::stbtfID = fnv->GftFifldID(dls, "stbtf", "Z");

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * WMfnuItfmPffr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WMfnuItfmPffr
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuItfmPffr_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtMfnuItfm::isCifdkboxID = fnv->GftFifldID(dls, "isCifdkbox", "Z");
    CHECK_NULL(AwtMfnuItfm::isCifdkboxID);
    AwtMfnuItfm::siortdutLbbflID = fnv->GftFifldID(dls, "siortdutLbbfl",
                                                   "Ljbvb/lbng/String;");
    CHECK_NULL(AwtMfnuItfm::siortdutLbbflID);
    AwtMfnuItfm::gftDffbultFontMID =
        fnv->GftStbtidMftiodID(dls, "gftDffbultFont", "()Ljbvb/bwt/Font;");

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMfnuItfmPffr
 * Mftiod:    _sftLbbfl
 * Signbturf: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuItfmPffr__1sftLbbfl(JNIEnv *fnv, jobjfdt sflf,
                                              jstring lbbfl)
{
    TRY;

    SftLbbflStrudt *sls = nfw SftLbbflStrudt;
    sls->mfnuitfm = fnv->NfwGlobblRff(sflf);
    sls->lbbfl = (lbbfl == NULL) ? NULL : (jstring)fnv->NfwGlobblRff(lbbfl);

    AwtToolkit::GftInstbndf().SyndCbll(AwtMfnuItfm::_SftLbbfl, sls);
    // globbl rffs bnd sls brf dflftfd in _SftLbbfl

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMfnuItfmPffr
 * Mftiod:    _sftFont
 * Signbturf: (Ljbvb/bwt/Font;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuItfmPffr__1sftFont(JNIEnv *fnv, jobjfdt sflf, jobjfdt)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    // Currfnt implfmfntbtion of AwtMfnuItfm gft font bttributf from tif pffr
    // dirfdtly, so wf ignorf it ifrf, but updbtf durrfnt mfnu lbyout.
    AwtToolkit::GftInstbndf().SyndCbll(AwtMfnuItfm::_UpdbtfLbyout, sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _UpdbtfLbyout

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMfnuItfmPffr
 * Mftiod:    drfbtf
 * Signbturf: (Lsun/bwt/windows/WMfnuPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuItfmPffr_drfbtf(JNIEnv *fnv, jobjfdt sflf,
                                          jobjfdt mfnu)
{
    TRY;

    JNI_CHECK_NULL_RETURN(mfnu, "null Mfnu");
    AwtToolkit::CrfbtfComponfnt(sflf, mfnu,
                                (AwtToolkit::ComponfntFbdtory)
                                AwtMfnuItfm::Crfbtf);
    PDATA pDbtb;
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMfnuItfmPffr
 * Mftiod:    fnbblf
 * Signbturf: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuItfmPffr_fnbblf(JNIEnv *fnv, jobjfdt sflf,
                                          jboolfbn on)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(sflf);
    AwtObjfdt::WinTirfbdExfd(sflf, AwtMfnuItfm::MENUITEM_ENABLE, (LPARAM)on );

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMfnuItfmPffr
 * Mftiod:    _disposf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuItfmPffr__1disposf(JNIEnv *fnv, jobjfdt sflf)
{
    TRY_NO_HANG;

    AwtObjfdt::_Disposf(sflf);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */

/************************************************************************
 * WCifdkboxMfnuItfmPffr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WCifdkboxMfnuItfmPffr
 * Mftiod:    sftStbtf
 * Signbturf: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCifdkboxMfnuItfmPffr_sftStbtf(JNIEnv *fnv, jobjfdt sflf,
                                                    jboolfbn on)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(sflf);
    AwtObjfdt::WinTirfbdExfd(sflf, AwtMfnuItfm::MENUITEM_SETSTATE, (LPARAM)on);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
