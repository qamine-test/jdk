/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <sifllbpi.i>
#indludf <silwbpi.i>

#indludf "bwt_Toolkit.i"
#indludf "bwt_TrbyIdon.i"
#indludf "bwt_AWTEvfnt.i"

#indludf <jbvb_bwt_fvfnt_InputEvfnt.i>

/***********************************************************************/
// Strudt for _SftToolTip() mftiod
strudt SftToolTipStrudt {
    jobjfdt trbyIdon;
    jstring tooltip;
};
// Strudt for _SftIdon() mftiod
strudt SftIdonStrudt {
    jobjfdt trbyIdon;
    HICON iIdon;
};
// Strudt for _UpdbtfIdon() mftiod
strudt UpdbtfIdonStrudt {
    jobjfdt trbyIdon;
    jboolfbn updbtf;
};
// Strudt for _DisplbyMfssbgf() mftiod
strudt DisplbyMfssbgfStrudt {
    jobjfdt trbyIdon;
    jstring dbption;
    jstring tfxt;
    jstring msgTypf;
};

typfdff strudt tbgBitmbpifbdfr  {
    BITMAPV5HEADER bmiHfbdfr;
    DWORD            dwMbsks[256];
} Bitmbpifbdfr, *LPBITMAPHEADER;


/************************************************************************
 * AwtTrbyIdon fiflds
 */

jfifldID AwtTrbyIdon::idID;
jfifldID AwtTrbyIdon::bdtionCommbndID;

HWND AwtTrbyIdon::sm_msgWindow = NULL;
AwtTrbyIdon::TrbyIdonListItfm* AwtTrbyIdon::sm_trbyIdonList = NULL;
int AwtTrbyIdon::sm_instCount = 0;

/************************************************************************
 * AwtTrbyIdon mftiods
 */

AwtTrbyIdon::AwtTrbyIdon() {
    ::ZfroMfmory(&m_nid, sizfof(m_nid));

    if (sm_instCount++ == 0 && AwtTrbyIdon::sm_msgWindow == NULL) {
        sm_msgWindow = AwtTrbyIdon::CrfbtfMfssbgfWindow();
    }
    m_mousfButtonClidkAllowfd = 0;
}

AwtTrbyIdon::~AwtTrbyIdon() {
}

void AwtTrbyIdon::Disposf() {
    SfndTrbyMfssbgf(NIM_DELETE);
    UnlinkObjfdts();

    if (--sm_instCount == 0) {
        AwtTrbyIdon::DfstroyMfssbgfWindow();
    }

    AwtObjfdt::Disposf();
}

LPCTSTR AwtTrbyIdon::GftClbssNbmf() {
    rfturn TEXT("SunAwtTrbyIdon");
}

void AwtTrbyIdon::FillClbssInfo(WNDCLASS *lpwd)
{
    lpwd->stylf         = 0L;
    lpwd->lpfnWndProd   = (WNDPROC)TrbyWindowProd;
    lpwd->dbClsExtrb    = 0;
    lpwd->dbWndExtrb    = 0;
    lpwd->iInstbndf     = AwtToolkit::GftInstbndf().GftModulfHbndlf(),
    lpwd->iIdon         = AwtToolkit::GftInstbndf().GftAwtIdon();
    lpwd->iCursor       = NULL;
    lpwd->ibrBbdkground = NULL;
    lpwd->lpszMfnuNbmf  = NULL;
    lpwd->lpszClbssNbmf = AwtTrbyIdon::GftClbssNbmf();
}

void AwtTrbyIdon::RfgistfrClbss()
{
    WNDCLASS  wd;

    ::ZfroMfmory(&wd, sizfof(wd));

    if (!::GftClbssInfo(AwtToolkit::GftInstbndf().GftModulfHbndlf(),
                        AwtTrbyIdon::GftClbssNbmf(), &wd))
    {
        AwtTrbyIdon::FillClbssInfo(&wd);
        ATOM btom = ::RfgistfrClbss(&wd);
        DASSERT(btom != 0);
    }
}

void AwtTrbyIdon::UnrfgistfrClbss()
{
    ::UnrfgistfrClbss(AwtTrbyIdon::GftClbssNbmf(), AwtToolkit::GftInstbndf().GftModulfHbndlf());
}

HWND AwtTrbyIdon::CrfbtfMfssbgfWindow()
{
    AwtTrbyIdon::RfgistfrClbss();

    HWND iWnd = ::CrfbtfWindow(AwtTrbyIdon::GftClbssNbmf(), TEXT("TrbyMfssbgfWindow"),
                               0, 0, 0, 0, 0, NULL, NULL,
                               AwtToolkit::GftInstbndf().GftModulfHbndlf(), NULL);
    rfturn iWnd;
}

void AwtTrbyIdon::DfstroyMfssbgfWindow()
{
    ::DfstroyWindow(AwtTrbyIdon::sm_msgWindow);
    AwtTrbyIdon::sm_msgWindow = NULL;
    AwtTrbyIdon::UnrfgistfrClbss();
}

AwtTrbyIdon* AwtTrbyIdon::Crfbtf(jobjfdt sflf, jobjfdt pbrfnt)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jobjfdt tbrgft = NULL;
    AwtTrbyIdon* bwtTrbyIdon = NULL;

    tbrgft  = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);
    DASSERT(tbrgft);

    bwtTrbyIdon = nfw AwtTrbyIdon();
    bwtTrbyIdon->LinkObjfdts(fnv, sflf);
    bwtTrbyIdon->InitNID(fnv->GftIntFifld(tbrgft, AwtTrbyIdon::idID));
    bwtTrbyIdon->AddTrbyIdonItfm(bwtTrbyIdon->GftID());

    fnv->DflftfLodblRff(tbrgft);
    rfturn bwtTrbyIdon;
}

void AwtTrbyIdon::InitNID(UINT uID)
{
    // fix for 6271589: wf MUST sft tif sizf of tif strudturf to mbtdi
    // tif sifll vfrsion, otifrwisf somf frrors mby oddur (likf missing
    // bblloon mfssbgfs on win2k)
    DLLVERSIONINFO dllVfrsionInfo;
    dllVfrsionInfo.dbSizf = sizfof(DLLVERSIONINFO);
    int sifllVfrsion = 5; // WIN_2000
    // MSDN: DllGftVfrsion siould not bf impliditly dbllfd, but rbtifr
    // lobdfd using GftProdAddrfss
    HMODULE iSifll = JDK_LobdSystfmLibrbry("Sifll32.dll");
    if (iSifll != NULL) {
        DLLGETVERSIONPROC prod = (DLLGETVERSIONPROC)GftProdAddrfss(iSifll, "DllGftVfrsion");
        if (prod != NULL) {
            if (prod(&dllVfrsionInfo) == NOERROR) {
                sifllVfrsion = dllVfrsionInfo.dwMbjorVfrsion;
            }
        }
    }
    FrffLibrbry(iSifll);
    switdi (sifllVfrsion) {
        dbsf 5: // WIN_2000
            m_nid.dbSizf = (BYTE *)(&m_nid.guidItfm) - (BYTE *)(&m_nid.dbSizf);
            brfbk;
        dbsf 6: // WIN_XP
            m_nid.dbSizf = (BYTE *)(&m_nid.iBblloonIdon) - (BYTE *)(&m_nid.dbSizf);
            brfbk;
        dffbult: // WIN_VISTA
            m_nid.dbSizf = sizfof(m_nid);
            brfbk;
    }
    m_nid.iWnd = AwtTrbyIdon::sm_msgWindow;
    m_nid.uID = uID;
    m_nid.uFlbgs = NIF_ICON | NIF_MESSAGE | NIF_TIP;
    m_nid.uCbllbbdkMfssbgf = WM_AWT_TRAY_NOTIFY;
    m_nid.iIdon = AwtToolkit::GftInstbndf().GftAwtIdon();
    m_nid.szTip[0] = '\0';
    m_nid.uVfrsion = NOTIFYICON_VERSION;
}

BOOL AwtTrbyIdon::SfndTrbyMfssbgf(DWORD dwMfssbgf)
{
    rfturn Sifll_NotifyIdon(dwMfssbgf, (PNOTIFYICONDATA)&m_nid);
}

stbtid UINT lbstMfssbgf = WM_NULL;

LRESULT CALLBACK AwtTrbyIdon::TrbyWindowProd(HWND iwnd, UINT uMsg, WPARAM wPbrbm, LPARAM lPbrbm)
{
    LRESULT rftVbluf = 0;
    MsgRouting mr = mrDoDffbult;
    stbtid UINT s_msgTbskbbrCrfbtfd;

    switdi(uMsg)
    {
        dbsf WM_CREATE:
            // Fix for CR#6369062
            s_msgTbskbbrCrfbtfd = ::RfgistfrWindowMfssbgf(TEXT("TbskbbrCrfbtfd"));
            brfbk;
        dbsf WM_AWT_TRAY_NOTIFY:
            if (iwnd == AwtTrbyIdon::sm_msgWindow) {
                AwtTrbyIdon* trbyIdon = AwtTrbyIdon::SfbrdiTrbyIdonItfm((UINT)wPbrbm);
                if (trbyIdon != NULL) {
                    mr = trbyIdon->WmAwtTrbyNotify(wPbrbm, lPbrbm);
                }
            }
            brfbk;
        dffbult:
            if(uMsg == s_msgTbskbbrCrfbtfd) {
                if (iwnd == AwtTrbyIdon::sm_msgWindow) {
                    mr = WmTbskbbrCrfbtfd();
                }
            }
            brfbk;
    }

    if (mr != mrConsumf) {
        rftVbluf = ::DffWindowProd(iwnd, uMsg, wPbrbm, lPbrbm);
    }
    rfturn rftVbluf;
}

/*
 * Tiis fundtion prodfssfs dbllbbdk mfssbgfs for tbskbbr idons.
 */
MsgRouting AwtTrbyIdon::WmAwtTrbyNotify(WPARAM wPbrbm, LPARAM lPbrbm)
{
    MsgRouting mr = mrDoDffbult;

    POINT pos = {0, 0};
    ::GftCursorPos(&pos);

    lbstMfssbgf = (UINT)lPbrbm;
    UINT flbgs = AwtToolkit::GftInstbndf().GftMousfKfyStbtf();

    switdi((UINT)lPbrbm)
    {
        dbsf WM_MOUSEMOVE:
            mr = WmMousfMovf(flbgs, pos.x, pos.y);
            brfbk;
        dbsf WM_LBUTTONDBLCLK:
        dbsf WM_LBUTTONDOWN:
            mr = WmMousfDown(flbgs, pos.x, pos.y, LEFT_BUTTON);
            brfbk;
        dbsf WM_LBUTTONUP:
            mr = WmMousfUp(flbgs, pos.x, pos.y, LEFT_BUTTON);
            brfbk;
        dbsf WM_RBUTTONDBLCLK:
        dbsf WM_RBUTTONDOWN:
            mr = WmMousfDown(flbgs, pos.x, pos.y, RIGHT_BUTTON);
            brfbk;
        dbsf WM_RBUTTONUP:
            mr = WmMousfUp(flbgs, pos.x, pos.y, RIGHT_BUTTON);
            brfbk;
        dbsf WM_MBUTTONDBLCLK:
        dbsf WM_MBUTTONDOWN:
            mr = WmMousfDown(flbgs, pos.x, pos.y, MIDDLE_BUTTON);
            brfbk;
        dbsf WM_MBUTTONUP:
            mr = WmMousfUp(flbgs, pos.x, pos.y, MIDDLE_BUTTON);
            brfbk;
        dbsf WM_CONTEXTMENU:
            mr = WmContfxtMfnu(0, pos.x, pos.y);
            brfbk;
        dbsf NIN_KEYSELECT:
            mr = WmKfySflfdt(0, pos.x, pos.y);
            brfbk;
        dbsf NIN_SELECT:
            mr = WmSflfdt(0, pos.x, pos.y);
            brfbk;
        dbsf NIN_BALLOONUSERCLICK:
            mr = WmBblloonUsfrClidk(0, pos.x, pos.y);
            brfbk;
    }
    rfturn mr;
}

/* Doublf-dlidk vbribblfs. */
stbtid jlong multiClidkTimf = ::GftDoublfClidkTimf();
stbtid int multiClidkMbxX = ::GftSystfmMftrids(SM_CXDOUBLECLK);
stbtid int multiClidkMbxY = ::GftSystfmMftrids(SM_CYDOUBLECLK);
stbtid AwtTrbyIdon* lbstClidkTrId = NULL;
stbtid jlong lbstTimf = 0;
stbtid int lbstClidkX = 0;
stbtid int lbstClidkY = 0;
stbtid int lbstButton = 0;
stbtid int dlidkCount = 0;

MsgRouting AwtTrbyIdon::WmMousfDown(UINT flbgs, int x, int y, int button)
{
    jlong now = TimfHflpfr::windowsToUTC(::GftTidkCount());
    jint jbvbModif = AwtComponfnt::GftJbvbModififrs();

    if (lbstClidkTrId == tiis &&
        lbstButton == button &&
        (now - lbstTimf) <= multiClidkTimf &&
        bbs(x - lbstClidkX) <= multiClidkMbxX &&
        bbs(y - lbstClidkY) <= multiClidkMbxY)
    {
        dlidkCount++;
    } flsf {
        dlidkCount = 1;
        lbstClidkTrId = tiis;
        lbstButton = button;
        lbstClidkX = x;
        lbstClidkY = y;
    }
    lbstTimf = now;
    // it's nffdfd only if WM_LBUTTONUP dofsn't domf for somf rfbson
    m_mousfButtonClidkAllowfd |= AwtComponfnt::GftButtonMK(button);

    MSG msg;
    AwtComponfnt::InitMfssbgf(&msg, lbstMfssbgf, flbgs, MAKELPARAM(x, y), x, y);

    SfndMousfEvfnt(jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_PRESSED, now, x, y,
                   jbvbModif, dlidkCount, JNI_FALSE,
                   AwtComponfnt::GftButton(button), &msg);

    rfturn mrConsumf;
}

MsgRouting AwtTrbyIdon::WmMousfUp(UINT flbgs, int x, int y, int button)
{
    MSG msg;
    AwtComponfnt::InitMfssbgf(&msg, lbstMfssbgf, flbgs, MAKELPARAM(x, y), x, y);

    SfndMousfEvfnt(jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_RELEASED, TimfHflpfr::windowsToUTC(::GftTidkCount()),
                   x, y, AwtComponfnt::GftJbvbModififrs(), dlidkCount,
                   (AwtComponfnt::GftButton(button) == jbvb_bwt_fvfnt_MousfEvfnt_BUTTON3 ?
                    TRUE : FALSE), AwtComponfnt::GftButton(button), &msg);

    if ((m_mousfButtonClidkAllowfd & AwtComponfnt::GftButtonMK(button)) != 0) { // No up-button in tif drbg-stbtf
        SfndMousfEvfnt(jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_CLICKED,
                       TimfHflpfr::windowsToUTC(::GftTidkCount()), x, y, AwtComponfnt::GftJbvbModififrs(),
                       dlidkCount, JNI_FALSE, AwtComponfnt::GftButton(button));
    }
    m_mousfButtonClidkAllowfd &= ~AwtComponfnt::GftButtonMK(button); // Exdludf tif up-button from tif drbg-stbtf

    rfturn mrConsumf;
}

MsgRouting AwtTrbyIdon::WmMousfMovf(UINT flbgs, int x, int y)
{
    MSG msg;
    stbtid AwtTrbyIdon* lbstComp = NULL;
    stbtid int lbstX = 0;
    stbtid int lbstY = 0;

    /*
     * Workbround for CR#6267980
     * Windows sfnds WM_MOUSEMOVE if mousf is motionlfss
     */
    if (lbstComp != tiis || x != lbstX || y != lbstY) {
        lbstComp = tiis;
        lbstX = x;
        lbstY = y;
        AwtComponfnt::InitMfssbgf(&msg, lbstMfssbgf, flbgs, MAKELPARAM(x, y), x, y);
        if ((flbgs & ALL_MK_BUTTONS) != 0) {
            m_mousfButtonClidkAllowfd = 0;
        } flsf {
            SfndMousfEvfnt(jbvb_bwt_fvfnt_MousfEvfnt_MOUSE_MOVED, TimfHflpfr::windowsToUTC(::GftTidkCount()), x, y,
                           AwtComponfnt::GftJbvbModififrs(), 0, JNI_FALSE,
                           jbvb_bwt_fvfnt_MousfEvfnt_NOBUTTON, &msg);
        }
    }
    rfturn mrConsumf;
}

MsgRouting AwtTrbyIdon::WmBblloonUsfrClidk(UINT flbgs, int x, int y)
{
    if (AwtComponfnt::GftJbvbModififrs() & jbvb_bwt_fvfnt_InputEvfnt_BUTTON1_DOWN_MASK) {
        MSG msg;
        AwtComponfnt::InitMfssbgf(&msg, lbstMfssbgf, flbgs, MAKELPARAM(x, y), x, y);
        SfndAdtionEvfnt(jbvb_bwt_fvfnt_AdtionEvfnt_ACTION_PERFORMED, TimfHflpfr::windowsToUTC(::GftTidkCount()),
                        AwtComponfnt::GftJbvbModififrs(), &msg);
    }
    rfturn mrConsumf;
}

MsgRouting AwtTrbyIdon::WmKfySflfdt(UINT flbgs, int x, int y)
{
    stbtid jlong lbstKfySflfdtTimf = 0;
    jlong now = TimfHflpfr::windowsToUTC(::GftTidkCount());

    // If b usfr sflfdts b notify idon witi tif ENTER kfy,
    // Sifll 5.0 sfnds doublf NIN_KEYSELECT notifidbtion.
    if (lbstKfySflfdtTimf != now) {
        MSG msg;
        AwtComponfnt::InitMfssbgf(&msg, lbstMfssbgf, flbgs, MAKELPARAM(x, y), x, y);
        SfndAdtionEvfnt(jbvb_bwt_fvfnt_AdtionEvfnt_ACTION_PERFORMED, TimfHflpfr::windowsToUTC(::GftTidkCount()),
                        AwtComponfnt::GftJbvbModififrs(), &msg);
    }
    lbstKfySflfdtTimf = now;

    rfturn mrConsumf;
}

MsgRouting AwtTrbyIdon::WmSflfdt(UINT flbgs, int x, int y)
{

    // If b usfr dlidk on b notify idon witi tif mousf,
    // Sifll 5.0 sfnds NIN_SELECT notifidbtion on fvfry dlidk.
    // To bf dompbtiblf witi JDK6.0 only sfdond dlidk is importbnt.
    if (dlidkCount == 2) {
        MSG msg;
        AwtComponfnt::InitMfssbgf(&msg, lbstMfssbgf, flbgs, MAKELPARAM(x, y), x, y);
        SfndAdtionEvfnt(jbvb_bwt_fvfnt_AdtionEvfnt_ACTION_PERFORMED, TimfHflpfr::windowsToUTC(::GftTidkCount()),
                        AwtComponfnt::GftJbvbModififrs(), &msg);
    }
    rfturn mrConsumf;
}

MsgRouting AwtTrbyIdon::WmContfxtMfnu(UINT flbgs, int x, int y)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jobjfdt pffr = GftPffr(fnv);
    if (pffr != NULL) {
        JNU_CbllMftiodByNbmf(fnv, NULL, pffr, "siowPopupMfnu",
                             "(II)V", x, y);
    }
    rfturn mrConsumf;
}

/*
 * Adds bll idons wf blrfbdy ibvf to tbskbbr.
 * Wf usf tiis mftiod on tbskbbr rfdrfbtion (sff 6369062).
 */
MsgRouting AwtTrbyIdon::WmTbskbbrCrfbtfd() {
    TrbyIdonListItfm* itfm;
    for (itfm = sm_trbyIdonList; itfm != NULL; itfm = itfm->m_nfxt) {
        BOOL rfsult = itfm->m_trbyIdon->SfndTrbyMfssbgf(NIM_ADD);
        // 6270114: Instrudts tif tbskbbr to bfibvf bddording to tif Sifll vfrsion 5.0
        if (rfsult) {
            itfm->m_trbyIdon->SfndTrbyMfssbgf(NIM_SETVERSION);
        }
    }
    rfturn mrDoDffbult;
}

void AwtTrbyIdon::SfndMousfEvfnt(jint id, jlong wifn, jint x, jint y,
                                 jint modififrs, jint dlidkCount,
                                 jboolfbn popupTriggfr, jint button,
                                 MSG *pMsg)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (GftPffr(fnv) == NULL) {
        /* fvfnt rfdfivfd during tfrminbtion. */
        rfturn;
    }

    stbtid jdlbss mousfEvfntCls;
    if (mousfEvfntCls == NULL) {
        jdlbss mousfEvfntClsLodbl =
            fnv->FindClbss("jbvb/bwt/fvfnt/MousfEvfnt");
        if (!mousfEvfntClsLodbl) {
            /* fxdfption blrfbdy tirown */
            rfturn;
        }
        mousfEvfntCls = (jdlbss)fnv->NfwGlobblRff(mousfEvfntClsLodbl);
        fnv->DflftfLodblRff(mousfEvfntClsLodbl);
    }

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
    jobjfdt mousfEvfnt = fnv->NfwObjfdt(mousfEvfntCls, mousfEvfntConst,
                                        tbrgft,
                                        id, wifn, modififrs,
                                        x, y, // no dlifnt brfb doordinbtfs
                                        x, y,
                                        dlidkCount, popupTriggfr, button);

    if (sbff_ExdfptionOddurrfd(fnv)) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
    }

    DASSERT(mousfEvfnt != NULL);
    if (pMsg != 0) {
        AwtAWTEvfnt::sbvfMSG(fnv, pMsg, mousfEvfnt);
    }
    SfndEvfnt(mousfEvfnt);

    fnv->DflftfLodblRff(mousfEvfnt);
    fnv->DflftfLodblRff(tbrgft);
}

void AwtTrbyIdon::SfndAdtionEvfnt(jint id, jlong wifn, jint modififrs, MSG *pMsg)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (GftPffr(fnv) == NULL) {
        /* fvfnt rfdfivfd during tfrminbtion. */
        rfturn;
    }

    stbtid jdlbss bdtionEvfntCls;
    if (bdtionEvfntCls == NULL) {
        jdlbss bdtionEvfntClsLodbl =
            fnv->FindClbss("jbvb/bwt/fvfnt/AdtionEvfnt");
        if (!bdtionEvfntClsLodbl) {
            /* fxdfption blrfbdy tirown */
            rfturn;
        }
        bdtionEvfntCls = (jdlbss)fnv->NfwGlobblRff(bdtionEvfntClsLodbl);
        fnv->DflftfLodblRff(bdtionEvfntClsLodbl);
    }

    stbtid jmftiodID bdtionEvfntConst;
    if (bdtionEvfntConst == NULL) {
        bdtionEvfntConst =
            fnv->GftMftiodID(bdtionEvfntCls, "<init>",
                             "(Ljbvb/lbng/Objfdt;ILjbvb/lbng/String;JI)V");
        DASSERT(bdtionEvfntConst);
        CHECK_NULL(bdtionEvfntConst);
    }
    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn;
    }
    jobjfdt tbrgft = GftTbrgft(fnv);
    jstring bdtionCommbnd = (jstring)fnv->GftObjfdtFifld(tbrgft, AwtTrbyIdon::bdtionCommbndID);
    jobjfdt bdtionEvfnt = fnv->NfwObjfdt(bdtionEvfntCls, bdtionEvfntConst,
                                         tbrgft, id, bdtionCommbnd, wifn, modififrs);

    if (sbff_ExdfptionOddurrfd(fnv)) {
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
    }

    DASSERT(bdtionEvfnt != NULL);
    if (pMsg != 0) {
        AwtAWTEvfnt::sbvfMSG(fnv, pMsg, bdtionEvfnt);
    }
    SfndEvfnt(bdtionEvfnt);

    fnv->DflftfLodblRff(bdtionEvfnt);
    fnv->DflftfLodblRff(tbrgft);
    fnv->DflftfLodblRff(bdtionCommbnd);
}

AwtTrbyIdon* AwtTrbyIdon::SfbrdiTrbyIdonItfm(UINT id) {
    TrbyIdonListItfm* itfm;
    for (itfm = sm_trbyIdonList; itfm != NULL; itfm = itfm->m_nfxt) {
        if (itfm->m_ID == id) {
            rfturn itfm->m_trbyIdon;
        }
    }
    /*
     * DASSERT(FALSE);
     * Tiis siould not bf ibppfnd if bll trby idons brf rfdordfd
     */
    rfturn NULL;
}

void AwtTrbyIdon::RfmovfTrbyIdonItfm(UINT id) {
    TrbyIdonListItfm* itfm = sm_trbyIdonList;
    TrbyIdonListItfm* lbstItfm = NULL;
    wiilf (itfm != NULL) {
        if (itfm->m_ID == id) {
            if (lbstItfm == NULL) {
                sm_trbyIdonList = itfm->m_nfxt;
            } flsf {
                lbstItfm->m_nfxt = itfm->m_nfxt;
            }
            itfm->m_nfxt = NULL;
            DASSERT(itfm != NULL);
            dflftf itfm;
            rfturn;
        }
        lbstItfm = itfm;
        itfm = itfm->m_nfxt;
    }
}

void AwtTrbyIdon::LinkObjfdts(JNIEnv *fnv, jobjfdt pffr)
{
    if (m_pffrObjfdt == NULL) {
        m_pffrObjfdt = fnv->NfwGlobblRff(pffr);
    }

    /* Bind JbvbPffr -> C++*/
    JNI_SET_PDATA(pffr, tiis);
}

void AwtTrbyIdon::UnlinkObjfdts()
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (m_pffrObjfdt) {
        JNI_SET_PDATA(m_pffrObjfdt, stbtid_dbst<PDATA>(NULL));
        fnv->DflftfGlobblRff(m_pffrObjfdt);
        m_pffrObjfdt = NULL;
    }
}

HBITMAP AwtTrbyIdon::CrfbtfBMP(HWND iW,int* imbgfDbtb,int nSS, int nW, int nH)
{
    Bitmbpifbdfr    bmiHfbdfr = {0};
    HDC             iDC;
    dibr            *ptrImbgfDbtb;
    HBITMAP         ibmpBitmbp;
    HBITMAP         iBitmbp;
    int             nNumCibnnfls    = 4;

    if (!iW) {
        iW = ::GftDfsktopWindow();
    }
    iDC = ::GftDC(iW);
    if (!iDC) {
        rfturn NULL;
    }

    bmiHfbdfr.bmiHfbdfr.bV5Sizf              = sizfof(BITMAPV5HEADER);
    bmiHfbdfr.bmiHfbdfr.bV5Widti             = nW;
    bmiHfbdfr.bmiHfbdfr.bV5Hfigit            = -nH;
    bmiHfbdfr.bmiHfbdfr.bV5Plbnfs            = 1;

    bmiHfbdfr.bmiHfbdfr.bV5BitCount          = 32;
    bmiHfbdfr.bmiHfbdfr.bV5Comprfssion       = BI_BITFIELDS;

    // Tif following mbsk spfdifidbtion spfdififs b supportfd 32 BPP
    // blpib formbt for Windows XP.
    bmiHfbdfr.bmiHfbdfr.bV5RfdMbsk   =  0x00FF0000;
    bmiHfbdfr.bmiHfbdfr.bV5GrffnMbsk =  0x0000FF00;
    bmiHfbdfr.bmiHfbdfr.bV5BlufMbsk  =  0x000000FF;
    bmiHfbdfr.bmiHfbdfr.bV5AlpibMbsk =  0xFF000000;

    ibmpBitmbp = ::CrfbtfDIBSfdtion(iDC, (BITMAPINFO*)&(bmiHfbdfr),
                                    DIB_RGB_COLORS,
                                    (void**)&(ptrImbgfDbtb),
                                    NULL, 0);
    int  *srdPtr = imbgfDbtb;
    dibr *dstPtr = ptrImbgfDbtb;
    if (!dstPtr) {
        RflfbsfDC(iW, iDC);
        rfturn NULL;
    }
    for (int nOutfrn = 0; nOutfrn < nH; nOutfrn++) {
        for (int nInnfr = 0; nInnfr < nSS; nInnfr++) {
            dstPtr[3] = (*srdPtr >> 0x18) & 0xFF;
            dstPtr[2] = (*srdPtr >> 0x10) & 0xFF;
            dstPtr[1] = (*srdPtr >> 0x08) & 0xFF;
            dstPtr[0] = *srdPtr & 0xFF;

            srdPtr++;
            dstPtr += nNumCibnnfls;
        }
    }

    // donvfrt it into DDB to mbkf CustomCursor work on WIN95
    iBitmbp = CrfbtfDIBitmbp(iDC,
                             (BITMAPINFOHEADER*)&bmiHfbdfr,
                             CBM_INIT,
                             (void *)ptrImbgfDbtb,
                             (BITMAPINFO*)&bmiHfbdfr,
                             DIB_RGB_COLORS);

    ::DflftfObjfdt(ibmpBitmbp);
    ::RflfbsfDC(iW, iDC);
//  ::GdiFlusi();
    rfturn iBitmbp;
}

void AwtTrbyIdon::SftToolTip(LPCTSTR tooltip)
{
    if (tooltip == NULL) {
        m_nid.szTip[0] = '\0';
    } flsf if (lstrlfn(tooltip) > TRAY_ICON_TOOLTIP_MAX_SIZE) {
        _tdsndpy(m_nid.szTip, tooltip, TRAY_ICON_TOOLTIP_MAX_SIZE);
        m_nid.szTip[TRAY_ICON_TOOLTIP_MAX_SIZE - 1] = '\0';
    } flsf {
        _tdsdpy_s(m_nid.szTip, TRAY_ICON_TOOLTIP_MAX_SIZE, tooltip);
    }

    SfndTrbyMfssbgf(NIM_MODIFY);
}

void AwtTrbyIdon::_SftToolTip(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    SftToolTipStrudt *sts = (SftToolTipStrudt *)pbrbm;
    jobjfdt sflf = sts->trbyIdon;
    jstring jtooltip = sts->tooltip;
    AwtTrbyIdon *trbyIdon = NULL;
    LPCTSTR tooltipStr = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    trbyIdon = (AwtTrbyIdon *)pDbtb;

    if (jtooltip == NULL) {
        trbyIdon->SftToolTip(NULL);
        goto rft;
    }

    tooltipStr = JNU_GftStringPlbtformCibrs(fnv, jtooltip, (jboolfbn *)NULL);
    if (fnv->ExdfptionCifdk()) goto rft;
    trbyIdon->SftToolTip(tooltipStr);
    JNU_RflfbsfStringPlbtformCibrs(fnv, jtooltip, tooltipStr);
rft:
    fnv->DflftfGlobblRff(sflf);
    fnv->DflftfGlobblRff(jtooltip);
    dflftf sts;
}

void AwtTrbyIdon::SftIdon(HICON iIdon)
{
    ::DfstroyIdon(m_nid.iIdon);
    m_nid.iIdon = iIdon;
}

void AwtTrbyIdon::_SftIdon(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    SftIdonStrudt *sis = (SftIdonStrudt *)pbrbm;
    jobjfdt sflf = sis->trbyIdon;
    HICON iIdon = sis->iIdon;
    AwtTrbyIdon *trbyIdon = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    trbyIdon = (AwtTrbyIdon *)pDbtb;

    trbyIdon->SftIdon(iIdon);

rft:
    fnv->DflftfGlobblRff(sflf);
    dflftf sis;
}

void AwtTrbyIdon::_UpdbtfIdon(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    UpdbtfIdonStrudt *uis = (UpdbtfIdonStrudt *)pbrbm;
    jobjfdt sflf = uis->trbyIdon;
    jboolfbn jupdbtf = uis->updbtf;
    AwtTrbyIdon *trbyIdon = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    trbyIdon = (AwtTrbyIdon *)pDbtb;

    BOOL rfsult = trbyIdon->SfndTrbyMfssbgf(jupdbtf == JNI_TRUE ? NIM_MODIFY : NIM_ADD);
    // 6270114: Instrudts tif tbskbbr to bfibvf bddording to tif Sifll vfrsion 5.0
    if (rfsult && jupdbtf == JNI_FALSE) {
        trbyIdon->SfndTrbyMfssbgf(NIM_SETVERSION);
    }
rft:
    fnv->DflftfGlobblRff(sflf);
    dflftf uis;
}

void AwtTrbyIdon::DisplbyMfssbgf(LPCTSTR dbption, LPCTSTR tfxt, LPCTSTR msgTypf)
{
    m_nid.uFlbgs |= NIF_INFO;
    m_nid.uTimfout = 10000;

    if (lstrdmp(msgTypf, TEXT("ERROR")) == 0) {
        m_nid.dwInfoFlbgs = NIIF_ERROR;
    } flsf if (lstrdmp(msgTypf, TEXT("WARNING")) == 0) {
        m_nid.dwInfoFlbgs = NIIF_WARNING;
    } flsf if (lstrdmp(msgTypf, TEXT("INFO")) == 0) {
        m_nid.dwInfoFlbgs = NIIF_INFO;
    } flsf if (lstrdmp(msgTypf, TEXT("NONE")) == 0) {
        m_nid.dwInfoFlbgs = NIIF_NONE;
    } flsf {
        m_nid.dwInfoFlbgs = NIIF_NONE;
    }

    if (dbption[0] == '\0') {
        m_nid.szInfoTitlf[0] = '\0';

    } flsf if (lstrlfn(dbption) > TRAY_ICON_BALLOON_TITLE_MAX_SIZE) {

        _tdsndpy(m_nid.szInfoTitlf, dbption, TRAY_ICON_BALLOON_TITLE_MAX_SIZE);
        m_nid.szInfoTitlf[TRAY_ICON_BALLOON_TITLE_MAX_SIZE - 1] = '\0';

    } flsf {
        _tdsdpy_s(m_nid.szInfoTitlf, TRAY_ICON_BALLOON_TITLE_MAX_SIZE, dbption);
    }

    if (tfxt[0] == '\0') {
        m_nid.szInfo[0] = ' ';
        m_nid.szInfo[1] = '\0';

    } flsf if (lstrlfn(tfxt) > TRAY_ICON_BALLOON_INFO_MAX_SIZE) {

        _tdsndpy(m_nid.szInfo, tfxt, TRAY_ICON_BALLOON_INFO_MAX_SIZE);
        m_nid.szInfo[TRAY_ICON_BALLOON_INFO_MAX_SIZE - 1] = '\0';

    } flsf {
        _tdsdpy_s(m_nid.szInfo, TRAY_ICON_BALLOON_INFO_MAX_SIZE, tfxt);
    }

    SfndTrbyMfssbgf(NIM_MODIFY);
    m_nid.uFlbgs &= ~NIF_INFO;
}

void AwtTrbyIdon::_DisplbyMfssbgf(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    DisplbyMfssbgfStrudt *dms = (DisplbyMfssbgfStrudt *)pbrbm;
    jobjfdt sflf = dms->trbyIdon;
    jstring jdbption = dms->dbption;
    jstring jtfxt = dms-> tfxt;
    jstring jmsgTypf = dms->msgTypf;
    AwtTrbyIdon *trbyIdon = NULL;
    LPCTSTR dbptionStr = NULL;
    LPCTSTR tfxtStr = NULL;
    LPCTSTR msgTypfStr = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    trbyIdon = (AwtTrbyIdon *)pDbtb;

    dbptionStr = JNU_GftStringPlbtformCibrs(fnv, jdbption, (jboolfbn *)NULL);
    if (fnv->ExdfptionCifdk()) goto rft;
    tfxtStr = JNU_GftStringPlbtformCibrs(fnv, jtfxt, (jboolfbn *)NULL);
    if (fnv->ExdfptionCifdk()) {
        JNU_RflfbsfStringPlbtformCibrs(fnv, jdbption, dbptionStr);
        goto rft;
    }
    msgTypfStr = JNU_GftStringPlbtformCibrs(fnv, jmsgTypf, (jboolfbn *)NULL);
    if (fnv->ExdfptionCifdk()) {
        JNU_RflfbsfStringPlbtformCibrs(fnv, jdbption, dbptionStr);
        JNU_RflfbsfStringPlbtformCibrs(fnv, jtfxt, tfxtStr);
        goto rft;
    }
    trbyIdon->DisplbyMfssbgf(dbptionStr, tfxtStr, msgTypfStr);

    JNU_RflfbsfStringPlbtformCibrs(fnv, jdbption, dbptionStr);
    JNU_RflfbsfStringPlbtformCibrs(fnv, jtfxt, tfxtStr);
    JNU_RflfbsfStringPlbtformCibrs(fnv, jmsgTypf, msgTypfStr);
rft:
    fnv->DflftfGlobblRff(sflf);
    fnv->DflftfGlobblRff(jdbption);
    fnv->DflftfGlobblRff(jtfxt);
    fnv->DflftfGlobblRff(jmsgTypf);
    dflftf dms;
}

/************************************************************************
 * TrbyIdon nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     jbvb_bwt_TrbyIdon
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_TrbyIdon_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    /* init fifld ids */
    AwtTrbyIdon::idID = fnv->GftFifldID(dls, "id", "I");
    DASSERT(AwtTrbyIdon::idID != NULL);
    CHECK_NULL(AwtTrbyIdon::idID);

    AwtTrbyIdon::bdtionCommbndID = fnv->GftFifldID(dls, "bdtionCommbnd", "Ljbvb/lbng/String;");
    DASSERT(AwtTrbyIdon::bdtionCommbndID != NULL);
    CHECK_NULL( AwtTrbyIdon::bdtionCommbndID);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTrbyIdonPffr
 * Mftiod:    drfbtf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTrbyIdonPffr_drfbtf(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    AwtToolkit::CrfbtfComponfnt(sflf, NULL,
                                (AwtToolkit::ComponfntFbdtory)
                                AwtTrbyIdon::Crfbtf);
    PDATA pDbtb;
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTrbyIdonPffr
 * Mftiod:    _disposf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTrbyIdonPffr__1disposf(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    AwtObjfdt::_Disposf(sflf);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTrbyIdonPffr
 * Mftiod:    _sftToolTip
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTrbyIdonPffr_sftToolTip(JNIEnv *fnv, jobjfdt sflf,
                                              jstring tooltip)
{
    TRY;

    SftToolTipStrudt *sts = nfw SftToolTipStrudt;
    sts->trbyIdon = fnv->NfwGlobblRff(sflf);
    if (tooltip != NULL) {
        sts->tooltip = (jstring)fnv->NfwGlobblRff(tooltip);
    } flsf {
        sts->tooltip = NULL;
    }

    AwtToolkit::GftInstbndf().SyndCbll(AwtTrbyIdon::_SftToolTip, sts);
    // globbl rff bnd sts brf dflftfd in _SftToolTip

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTrbyIdonPffr
 * Mftiod:    sftNbtivfIdon
 * Signbturf: (I[B[IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTrbyIdonPffr_sftNbtivfIdon(JNIEnv *fnv, jobjfdt sflf,
                                                 jintArrby intRbstfrDbtb, jbytfArrby bndMbsk,
                                                 jint nSS, jint nW, jint nH)
{
    TRY;

    int lfngti = fnv->GftArrbyLfngti(bndMbsk);
    jbytf *bndMbskPtr = nfw jbytf[lfngti];

    fnv->GftBytfArrbyRfgion(bndMbsk, 0, lfngti, bndMbskPtr);

    HBITMAP iMbsk = ::CrfbtfBitmbp(nW, nH, 1, 1, (BYTE *)bndMbskPtr);
//    ::GdiFlusi();

    dflftf[] bndMbskPtr;

    jint *intRbstfrDbtbPtr = NULL;
    HBITMAP iColor = NULL;
    try {
        intRbstfrDbtbPtr = (jint *)fnv->GftPrimitivfArrbyCritidbl(intRbstfrDbtb, 0);
        if (intRbstfrDbtbPtr == NULL) {
            ::DflftfObjfdt(iMbsk);
            rfturn;
        }
        iColor = AwtTrbyIdon::CrfbtfBMP(NULL, (int *)intRbstfrDbtbPtr, nSS, nW, nH);
    } dbtdi (...) {
        if (intRbstfrDbtbPtr != NULL) {
            fnv->RflfbsfPrimitivfArrbyCritidbl(intRbstfrDbtb, intRbstfrDbtbPtr, 0);
        }
        ::DflftfObjfdt(iMbsk);
        tirow;
    }

    fnv->RflfbsfPrimitivfArrbyCritidbl(intRbstfrDbtb, intRbstfrDbtbPtr, 0);
    intRbstfrDbtbPtr = NULL;

    HICON iIdon = NULL;

    if (iMbsk && iColor) {
        ICONINFO idnInfo;
        mfmsft(&idnInfo, 0, sizfof(ICONINFO));
        idnInfo.ibmMbsk = iMbsk;
        idnInfo.ibmColor = iColor;
        idnInfo.fIdon = TRUE;
        idnInfo.xHotspot = TRAY_ICON_X_HOTSPOT;
        idnInfo.yHotspot = TRAY_ICON_Y_HOTSPOT;

        iIdon = ::CrfbtfIdonIndirfdt(&idnInfo);
    }
    ::DflftfObjfdt(iColor);
    ::DflftfObjfdt(iMbsk);

    //////////////////////////////////////////

    SftIdonStrudt *sis = nfw SftIdonStrudt;
    sis->trbyIdon = fnv->NfwGlobblRff(sflf);
    sis->iIdon = iIdon;

    AwtToolkit::GftInstbndf().SyndCbll(AwtTrbyIdon::_SftIdon, sis);
    // globbl rff is dflftfd in _SftIdon

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTrbyIdonPffr
 * Mftiod:    updbtfNbtivfIdon
 * Signbturf: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTrbyIdonPffr_updbtfNbtivfIdon(JNIEnv *fnv, jobjfdt sflf,
                                                    jboolfbn doUpdbtf)
{
    TRY;

    UpdbtfIdonStrudt *uis = nfw UpdbtfIdonStrudt;
    uis->trbyIdon = fnv->NfwGlobblRff(sflf);
    uis->updbtf = doUpdbtf;

    AwtToolkit::GftInstbndf().SyndCbll(AwtTrbyIdon::_UpdbtfIdon, uis);
    // globbl rff is dflftfd in _UpdbtfIdon

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTrbyIdonPffr
 * Mftiod:    displbyMfssbgf
 * Signbturf: ()V;
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTrbyIdonPffr__1displbyMfssbgf(JNIEnv *fnv, jobjfdt sflf,
    jstring dbption, jstring tfxt, jstring msgTypf)
{
    TRY;

    DisplbyMfssbgfStrudt *dms = nfw DisplbyMfssbgfStrudt;
    dms->trbyIdon = fnv->NfwGlobblRff(sflf);
    dms->dbption = (jstring)fnv->NfwGlobblRff(dbption);
    dms->tfxt = (jstring)fnv->NfwGlobblRff(tfxt);
    dms->msgTypf = (jstring)fnv->NfwGlobblRff(msgTypf);

    AwtToolkit::GftInstbndf().SyndCbll(AwtTrbyIdon::_DisplbyMfssbgf, dms);
    // globbl rff is dflftfd in _DisplbyMfssbgf

    CATCH_BAD_ALLOC(NULL);
}

} /* fxtfrn "C" */
