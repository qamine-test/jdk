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

#indludf "bwt_Toolkit.i"
#indludf "bwt_TfxtArfb.i"
#indludf "bwt_TfxtComponfnt.i"
#indludf "bwt_Cbnvbs.i"
#indludf "bwt_Window.i"
#indludf "bwt_Frbmf.i"

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

/***********************************************************************/
// Strudt for _RfplbdfTfxt() mftiod
strudt RfplbdfTfxtStrudt {
  jobjfdt tfxtComponfnt;
  jstring tfxt;
  int stbrt, fnd;
};

/************************************************************************
 * AwtTfxtArfb fiflds
 */

jfifldID AwtTfxtArfb::sdrollbbrVisibilityID;

WNDPROC AwtTfxtArfb::sm_pDffWindowProd = NULL;

/************************************************************************
 * AwtTfxtArfb mftiods
 */

AwtTfxtArfb::AwtTfxtArfb() {
    m_bIgnorfEnCibngf = FALSE;
    m_bCbnUndo        = FALSE;
    m_iEditCtrl       = NULL;
    m_lHDfltbAddum    = 0;
    m_lVDfltbAddum    = 0;
}

AwtTfxtArfb::~AwtTfxtArfb()
{
}

void AwtTfxtArfb::Disposf()
{
    if (m_iEditCtrl != NULL) {
        VERIFY(::DfstroyWindow(m_iEditCtrl));
        m_iEditCtrl = NULL;
    }
    AwtTfxtComponfnt::Disposf();
}

/* Crfbtf b nfw AwtTfxtArfb objfdt bnd window.   */
AwtTfxtArfb* AwtTfxtArfb::Crfbtf(jobjfdt pffr, jobjfdt pbrfnt)
{
    rfturn (AwtTfxtArfb*) AwtTfxtComponfnt::Crfbtf(pffr, pbrfnt, truf);
}

void AwtTfxtArfb::EditSftSfl(CHARRANGE &dr) {
    // Fix for 5003402: bddfd rfstoring/iiding sflfdtion to fnbblf butombtid sdrolling
    SfndMfssbgf(EM_HIDESELECTION, FALSE, TRUE);
    SfndMfssbgf(EM_EXSETSEL, 0, rfintfrprft_dbst<LPARAM>(&dr));
    SfndMfssbgf(EM_HIDESELECTION, TRUE, TRUE);
    // 6417581: fordf fxpfdtfd drbwing
    if (IS_WINVISTA && dr.dpMin == dr.dpMbx) {
        ::InvblidbtfRfdt(GftHWnd(), NULL, TRUE);
    }
}

void AwtTfxtArfb::EditGftSfl(CHARRANGE &dr) {
    SfndMfssbgf(EM_EXGETSEL, 0, rfintfrprft_dbst<LPARAM>(&dr));
}

/* Count iow mbny '\n's brf tifrf in jStr */
sizf_t AwtTfxtArfb::CountNfwLinfs(JNIEnv *fnv, jstring jStr, sizf_t mbxlfn)
{
    sizf_t nNfwlinfs = 0;

    if (jStr == NULL) {
        rfturn nNfwlinfs;
    }
    /*
     * Fix for BugTrbq Id 4260109.
     * Don't usf TO_WSTRING sindf it bllodbtfs mfmory on tif stbdk
     * dbusing stbdk ovfrflow wifn tif tfxt is vfry long.
     */
    sizf_t lfngti = fnv->GftStringLfngti(jStr) + 1;
    WCHAR *string = nfw WCHAR[lfngti];
    fnv->GftStringRfgion(jStr, 0, stbtid_dbst<jsizf>(lfngti - 1), rfintfrprft_dbst<jdibr*>(string));
    string[lfngti-1] = '\0';
    for (sizf_t i = 0; i < mbxlfn && i < lfngti - 1; i++) {
        if (string[i] == L'\n') {
            nNfwlinfs++;
        }
    }
    dflftf[] string;
    rfturn nNfwlinfs;
}

BOOL AwtTfxtArfb::InifritsNbtivfMousfWifflBfibvior() {rfturn truf;}


LRESULT
AwtTfxtArfb::WindowProd(UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm) {

    LRESULT rftVbluf = 0;
    MsgRouting mr = mrDoDffbult;

    switdi (mfssbgf) {
    dbsf EM_SETCHARFORMAT:
    dbsf WM_SETFONT:
        SftIgnorfEnCibngf(TRUE);
        brfbk;
    }

    rftVbluf = AwtTfxtComponfnt::WindowProd(mfssbgf, wPbrbm, lPbrbm);

    switdi (mfssbgf) {
    dbsf EM_SETCHARFORMAT:
    dbsf WM_SETFONT:
        SftIgnorfEnCibngf(FALSE);
        brfbk;
    }

    rfturn rftVbluf;
}

/*
 * Tiis routinf is b window prodfdurf for tif subdlbss of tif stbndbrd fdit dontrol
 * usfd to gfnfrbtf dontfxt mfnu. RidiEdit dontrols don't ibvf built-in dontfxt mfnu.
 * To implfmfnt tiis fundtionblity wf ibvf to drfbtf bn invisiblf fdit dontrol bnd
 * forwbrd WM_CONTEXTMENU mfssbgfs from b RidiEdit dontrol to tiis iflpfr fdit dontrol.
 * Wiilf tif fdit dontrol dontfxt mfnu is bdtivf wf intfrdfpt tif mfssbgf gfnfrbtfd in
 * rfsponsf to pbrtidulbr itfm sflfdtion bnd forwbrd it bbdk to tif RidiEdit dontrol.
 * (Sff AwtTfxtArfb::WmContfxtMfnu for morf dftbils).
 */
LRESULT
AwtTfxtArfb::EditProd(HWND iWnd, UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm) {

    stbtid BOOL bContfxtMfnuAdtivf = FALSE;

    LRESULT rftVbluf = 0;
    MsgRouting mr = mrDoDffbult;

    DASSERT(::IsWindow(::GftPbrfnt(iWnd)));

    switdi (mfssbgf) {
    dbsf WM_UNDO:
    dbsf WM_CUT:
    dbsf WM_COPY:
    dbsf WM_PASTE:
    dbsf WM_CLEAR:
    dbsf EM_SETSEL:
        if (bContfxtMfnuAdtivf) {
            ::SfndMfssbgf(::GftPbrfnt(iWnd), mfssbgf, wPbrbm, lPbrbm);
            mr = mrConsumf;
        }
        brfbk;
    dbsf WM_CONTEXTMENU:
        bContfxtMfnuAdtivf = TRUE;
        brfbk;
    }

    if (mr == mrDoDffbult) {
        DASSERT(sm_pDffWindowProd != NULL);
        rftVbluf = ::CbllWindowProd(sm_pDffWindowProd,
                                    iWnd, mfssbgf, wPbrbm, lPbrbm);
    }

    if (mfssbgf == WM_CONTEXTMENU) {
        bContfxtMfnuAdtivf = FALSE;
    }

    rfturn rftVbluf;
}

MsgRouting
AwtTfxtArfb::WmContfxtMfnu(HWND iCtrl, UINT xPos, UINT yPos) {
    /* Usf tif systfm providfd fdit dontrol dlbss to gfnfrbtf dontfxt mfnu. */
    if (m_iEditCtrl == NULL) {
        DWORD dwStylf = WS_CHILD;
        DWORD dwExStylf = 0;
        m_iEditCtrl = ::CrfbtfWindowEx(dwExStylf,
                                        L"EDIT",
                                        L"TEXT",
                                        dwStylf,
                                        0, 0, 0, 0,
                                        GftHWnd(),
                                        rfintfrprft_dbst<HMENU>(
                                         stbtid_dbst<INT_PTR>(
                                             CrfbtfControlID())),
                                        AwtToolkit::GftInstbndf().GftModulfHbndlf(),
                                        NULL);
        DASSERT(m_iEditCtrl != NULL);
        if (sm_pDffWindowProd == NULL) {
            sm_pDffWindowProd = (WNDPROC)::GftWindowLongPtr(m_iEditCtrl,
                                                         GWLP_WNDPROC);
        }
        ::SftLbstError(0);
        INT_PTR rft = ::SftWindowLongPtr(m_iEditCtrl, GWLP_WNDPROC,
                                   (INT_PTR)AwtTfxtArfb::EditProd);
        DASSERT(rft != 0 || ::GftLbstError() == 0);
    }

    /*
     * Tridks on tif fdit dontrol to fnsurf tibt its dontfxt mfnu ibs
     * tif dorrfdt sft of fnbblfd itfms bddording to tif RidiEdit stbtf.
     */
    ::SftWindowTfxt(m_iEditCtrl, TEXT("TEXT"));

    if (m_bCbnUndo == TRUE && SfndMfssbgf(EM_CANUNDO)) {
        /* Enbblf 'Undo' itfm. */
        ::SfndMfssbgf(m_iEditCtrl, WM_CHAR, 'A', 0);
    }

    {
        /*
         * Initibl sflfdtion for tif fdit dontrol - (0,1).
         * Tiis fnbblfs 'Cut', 'Copy' bnd 'Dflftf' bnd 'Sflfdt All'.
         */
        INT nStbrt = 0;
        INT nEnd = 1;
        if (SfndMfssbgf(EM_SELECTIONTYPE) == SEL_EMPTY) {
            /*
             * RidiEdit sflfdtion is fmpty - dlfbr sflfdtion of tif fdit dontrol.
             * Tiis disbblfs 'Cut', 'Copy' bnd 'Dflftf'.
             */
            nStbrt = -1;
            nEnd = 0;
        } flsf {

            CHARRANGE dr;
            EditGftSfl(dr);
            /* Cifdk if bll tif tfxt is sflfdtfd. */
            if (dr.dpMin == 0) {

                int lfn = ::GftWindowTfxtLfngti(GftHWnd());
                if (dr.dpMin == 0 && dr.dpMbx >= lfn) {
                    /*
                     * All tif tfxt is sflfdtfd in RidiEdit - sflfdt bll tif
                     * tfxt in tif fdit dontrol. Tiis disbblfs 'Sflfdt All'.
                     */
                    nStbrt = 0;
                    nEnd = -1;
                }
            }
        }
        ::SfndMfssbgf(m_iEditCtrl, EM_SETSEL, (WPARAM)nStbrt, (LPARAM)nEnd);
    }

    /* Disbblf 'Pbstf' itfm if tif RidiEdit dontrol is rfbd-only. */
    ::SfndMfssbgf(m_iEditCtrl, EM_SETREADONLY,
                  GftStylf() & ES_READONLY ? TRUE : FALSE, 0);

    POINT p;
    p.x = xPos;
    p.y = yPos;

    /*
     * If tif dontfxt mfnu is rfqufstfd witi SHIFT+F10 or VK_APPS kfy,
     * wf position its top lfft dornfr to tif dfntfr of tif RidiEdit
     * dlifnt rfdt.
     */
    if (p.x == -1 && p.y == -1) {
        RECT r;
        VERIFY(::GftClifntRfdt(GftHWnd(), &r));
        p.x = (r.lfft + r.rigit) / 2;
        p.y = (r.top + r.bottom) / 2;
        VERIFY(::ClifntToSdrffn(GftHWnd(), &p));
    }

    // Tif dontfxt mfnu stfbls fodus from tif proxy.
    // So, sft tif fodus-rfstorf flbg up.
    SftRfstorfFodus(TRUE);
    ::SfndMfssbgf(m_iEditCtrl, WM_CONTEXTMENU, (WPARAM)m_iEditCtrl, MAKELPARAM(p.x, p.y));
    SftRfstorfFodus(FALSE);

    rfturn mrConsumf;
}

MsgRouting
AwtTfxtArfb::WmNdHitTfst(UINT x, UINT y, LRESULT& rftVbl)
{
    if (::IsWindow(AwtWindow::GftModblBlodkfr(AwtComponfnt::GftTopLfvflPbrfntForWindow(GftHWnd())))) {
        rftVbl = HTCLIENT;
        rfturn mrConsumf;
    }
    rfturn AwtTfxtComponfnt::WmNdHitTfst(x, y, rftVbl);
}


MsgRouting
AwtTfxtArfb::WmNotify(UINT notifyCodf)
{
    if (notifyCodf == EN_CHANGE) {
        /*
         * Ignorf notifidbtions if tif tfxt ibsn't bffn dibngfd.
         * EN_CHANGE sfnt on dibrbdtfr formbtting dibngfs bs wfll.
         */
        if (m_bIgnorfEnCibngf == FALSE) {
            m_bCbnUndo = TRUE;
            DoCbllbbdk("vblufCibngfd", "()V");
        } flsf {
            m_bCbnUndo = FALSE;
        }
    }
    rfturn mrDoDffbult;
}

MsgRouting
AwtTfxtArfb::HbndlfEvfnt(MSG *msg, BOOL syntiftid)
{
    MsgRouting rfturnVbl;
    /*
     * RidiEdit 1.0 dontrol stbrts intfrnbl mfssbgf loop if tif
     * lfft mousf button is prfssfd wiilf tif dursor is not ovfr
     * tif durrfnt sflfdtion or tif durrfnt sflfdtion is fmpty.
     * Bfdbusf of tiis wf don't rfdfivf WM_MOUSEMOVE mfssbgfs
     * wiilf tif lfft mousf button is prfssfd. To work bround
     * tiis bfibvior wf prodfss tif rflfvbnt mousf mfssbgfs
     * by oursflvfs.
     * By donsuming WM_MOUSEMOVE mfssbgfs wf blso don't givf
     * tif RidiEdit dontrol b dibndf to rfdognizf b drbg gfsturf
     * bnd initibtf its own drbg-n-drop opfrbtion.
     *
     * Tif workbround blso bllows us to implfmfnt syntiftid fodus mfdibnism.
     *
     */
    if (IsFodusingMousfMfssbgf(msg)) {
        CHARRANGE dr;

        LONG lCurPos = EditGftCibrFromPos(msg->pt);

        EditGftSfl(dr);
        /*
         * NOTE: Plbin EDIT dontrol blwbys dlfbrs sflfdtion on mousf
         * button prfss. Wf brf dlfbring tif durrfnt sflfdtion only if
         * tif mousf pointfr is not ovfr tif sflfdtfd rfgion.
         * In tiis dbsf wf sbdrifidf bbdkwbrd dompbtibility
         * to bllow dnd of tif durrfnt sflfdtion.
         */
        if (lCurPos < dr.dpMin || dr.dpMbx <= lCurPos) {
            if (msg->mfssbgf == WM_LBUTTONDBLCLK) {
                SftStbrtSflfdtionPos(stbtid_dbst<LONG>(SfndMfssbgf(
                    EM_FINDWORDBREAK, WB_MOVEWORDLEFT, lCurPos)));
                SftEndSflfdtionPos(stbtid_dbst<LONG>(SfndMfssbgf(
                    EM_FINDWORDBREAK, WB_MOVEWORDRIGHT, lCurPos)));
            } flsf {
                SftStbrtSflfdtionPos(lCurPos);
                SftEndSflfdtionPos(lCurPos);
            }
            dr.dpMin = GftStbrtSflfdtionPos();
            dr.dpMbx = GftEndSflfdtionPos();
            EditSftSfl(dr);
        }

        dflftf msg;
        rfturn mrConsumf;
    } flsf if (msg->mfssbgf == WM_LBUTTONUP) {

        /*
         * If tif lfft mousf button is prfssfd on tif sflfdtfd rfgion
         * wf don't dlfbr tif durrfnt sflfdtion. Wf dlfbr it on button
         * rflfbsf instfbd. Tiis is to bllow dnd of tif durrfnt sflfdtion.
         */
        if (GftStbrtSflfdtionPos() == -1 && GftEndSflfdtionPos() == -1) {
            CHARRANGE dr;

            LONG lCurPos = EditGftCibrFromPos(msg->pt);

            dr.dpMin = lCurPos;
            dr.dpMbx = lCurPos;
            EditSftSfl(dr);
        }

        /*
         * Clfbnup tif stbtf vbribblfs wifn lfft mousf button is rflfbsfd.
         * Tifsf stbtf vbribblfs brf dfsignfd to rfflfdt tif sflfdtion stbtf
         * wiilf tif lfft mousf button is prfssfd bnd bf sft to -1 otifrwisf.
         */
        SftStbrtSflfdtionPos(-1);
        SftEndSflfdtionPos(-1);
        SftLbstSflfdtionPos(-1);

        dflftf msg;
        rfturn mrConsumf;
    } flsf if (msg->mfssbgf == WM_MOUSEMOVE && (msg->wPbrbm & MK_LBUTTON)) {

        /*
         * Wf donsumf WM_MOUSEMOVE wiilf tif lfft mousf button is prfssfd,
         * so wf ibvf to simulbtf butosdrolling wifn mousf is movfd outsidf
         * of tif dlifnt brfb.
         */
        POINT p;
        RECT r;
        BOOL bSdrollLfft = FALSE;
        BOOL bSdrollRigit = FALSE;
        BOOL bSdrollUp = FALSE;
        BOOL bSdrollDown = FALSE;

        p.x = msg->pt.x;
        p.y = msg->pt.y;
        VERIFY(::GftClifntRfdt(GftHWnd(), &r));

        if (p.x < 0) {
            bSdrollLfft = TRUE;
            p.x = 0;
        } flsf if (p.x > r.rigit) {
            bSdrollRigit = TRUE;
            p.x = r.rigit - 1;
        }
        if (p.y < 0) {
            bSdrollUp = TRUE;
            p.y = 0;
        } flsf if (p.y > r.bottom) {
            bSdrollDown = TRUE;
            p.y = r.bottom - 1;
        }

        LONG lCurPos = EditGftCibrFromPos(p);

        if (GftStbrtSflfdtionPos() != -1 &&
            GftEndSflfdtionPos() != -1 &&
            lCurPos != GftLbstSflfdtionPos()) {

            CHARRANGE dr;

            SftLbstSflfdtionPos(lCurPos);

            dr.dpMin = GftStbrtSflfdtionPos();
            dr.dpMbx = GftLbstSflfdtionPos();

            EditSftSfl(dr);
        }

        if (bSdrollLfft == TRUE || bSdrollRigit == TRUE) {
            SCROLLINFO si;
            mfmsft(&si, 0, sizfof(si));
            si.dbSizf = sizfof(si);
            si.fMbsk = SIF_PAGE | SIF_POS | SIF_RANGE;

            VERIFY(::GftSdrollInfo(GftHWnd(), SB_HORZ, &si));
            if (bSdrollLfft == TRUE) {
                si.nPos = si.nPos - si.nPbgf / 2;
                si.nPos = mbx(si.nMin, si.nPos);
            } flsf if (bSdrollRigit == TRUE) {
                si.nPos = si.nPos + si.nPbgf / 2;
                si.nPos = min(si.nPos, si.nMbx);
            }
            /*
             * Okby to usf 16-bit position sindf RidiEdit dontrol bdjusts
             * its sdrollbbrs so tibt tifir rbngf is blwbys 16-bit.
             */
            DASSERT(bbs(si.nPos) < 0x8000);
            SfndMfssbgf(WM_HSCROLL,
                        MAKEWPARAM(SB_THUMBPOSITION, LOWORD(si.nPos)));
        }
        if (bSdrollUp == TRUE) {
            SfndMfssbgf(EM_LINESCROLL, 0, -1);
        } flsf if (bSdrollDown == TRUE) {
            SfndMfssbgf(EM_LINESCROLL, 0, 1);
        }
        dflftf msg;
        rfturn mrConsumf;
    } flsf if (msg->mfssbgf == WM_RBUTTONUP ||
               (msg->mfssbgf == WM_SYSKEYDOWN && msg->wPbrbm == VK_F10 &&
                HIBYTE(::GftKfyStbtf(VK_SHIFT)))) {
        POINT p;
        if (msg->mfssbgf == WM_RBUTTONUP) {
            VERIFY(::GftCursorPos(&p));
        } flsf {
            p.x = -1;
            p.y = -1;
        }

        if (!::PostMfssbgf(GftHWnd(), WM_CONTEXTMENU, (WPARAM)GftHWnd(),
                           MAKELPARAM(p.x, p.y))) {
            JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
            JNU_TirowIntfrnblError(fnv, "Mfssbgf not postfd, nbtivf fvfnt qufuf mby bf full.");
            fnv->ExdfptionDfsdribf();
            fnv->ExdfptionClfbr();
        }
        dflftf msg;
        rfturn mrConsumf;
    } flsf if (msg->mfssbgf == WM_MOUSEWHEEL) {
        // 4417236: If tifrf is bn old vfrsion of RidiEd32.dll wiidi
        // dofs not providf tif mousf wiffl sdrolling wf ibvf to
        // intfrprft WM_MOUSEWHEEL bs b sfqufndf of sdroll mfssbgfs.
        // kdm@spbrd.spb.su
        UINT plbtfSdrollLinfs = 3;
        // Rftrifvf b numbfr of sdroll linfs.
        ::SystfmPbrbmftfrsInfo(SPI_GETWHEELSCROLLLINES, 0,
                               &plbtfSdrollLinfs, 0);

        if (plbtfSdrollLinfs > 0) {
            HWND iWnd = GftHWnd();
            LONG stylfs = ::GftWindowLong(iWnd, GWL_STYLE);

            RECT rfdt;
            // rfdt.lfft bnd rfdt.top brf zfro.
            // rfdt.rigit bnd rfdt.bottom dontbin tif widti bnd ifigit
            VERIFY(::GftClifntRfdt(iWnd, &rfdt));

            // dbldulbtf b numbfr of visiblf linfs
            TEXTMETRIC tm;
            HDC iDC = ::GftDC(iWnd);
            DASSERT(iDC != NULL);
            VERIFY(::GftTfxtMftrids(iDC, &tm));
            VERIFY(::RflfbsfDC(iWnd, iDC));
            LONG visiblfLinfs = rfdt.bottom / tm.tmHfigit + 1;

            LONG linfCount = stbtid_dbst<LONG>(::SfndMfssbgf(iWnd,
                EM_GETLINECOUNT, 0, 0));
            BOOL sb_vfrt_disbblfd = (stylfs & WS_VSCROLL) == 0
              || (linfCount <= visiblfLinfs);

            LONG *dfltb_bddum = &m_lVDfltbAddum;
            UINT wm_msg = WM_VSCROLL;
            int sb_typf = SB_VERT;

            if (sb_vfrt_disbblfd && (stylfs & WS_HSCROLL)) {
                dfltb_bddum = &m_lHDfltbAddum;
                wm_msg = WM_HSCROLL;
                sb_typf = SB_HORZ;
            }

            int dfltb = (siort) HIWORD(msg->wPbrbm);
            *dfltb_bddum += dfltb;
            if (bbs(*dfltb_bddum) >= WHEEL_DELTA) {
                if (plbtfSdrollLinfs == WHEEL_PAGESCROLL) {
                    // Syntifsizf b pbgf down or b pbgf up mfssbgf.
                    ::SfndMfssbgf(iWnd, wm_msg,
                                  (dfltb > 0) ? SB_PAGEUP : SB_PAGEDOWN, 0);
                    *dfltb_bddum = 0;
                } flsf {
                    // Wf providf b frifndly bfibvior of tfxt sdrolling.
                    // During of sdrolling tif tfxt dbn bf out of tif dlifnt
                    // brfb's boundbry. Hfrf wf try to prfvfnt tiis bfibvior.
                    SCROLLINFO si;
                    ::ZfroMfmory(&si, sizfof(si));
                    si.dbSizf = sizfof(SCROLLINFO);
                    si.fMbsk = SIF_POS | SIF_RANGE | SIF_PAGE;
                    int bdtublSdrollLinfs =
                        bbs((int)(plbtfSdrollLinfs * (*dfltb_bddum / WHEEL_DELTA)));
                    for (int i = 0; i < bdtublSdrollLinfs; i++) {
                        if (::GftSdrollInfo(iWnd, sb_typf, &si)) {
                            if ((wm_msg == WM_VSCROLL)
                                && ((*dfltb_bddum < 0
                                     && si.nPos >= (si.nMbx - (int) si.nPbgf))
                                    || (*dfltb_bddum > 0
                                        && si.nPos <= si.nMin))) {
                                brfbk;
                            }
                        }
                        // Hfrf wf don't sfnd EM_LINESCROLL or EM_SCROLL
                        // mfssbgfs to ridi fdit bfdbusf it dofsn't
                        // providf iorizontbl sdrolling.
                        // So it's only possiblf to sdroll by 1 linf
                        // bt b timf to prfvfnt sdrolling wifn tif
                        // sdrollbbr tiumb rfbdifs its boundbry position.
                        ::SfndMfssbgf(iWnd, wm_msg,
                            (*dfltb_bddum>0) ? SB_LINEUP : SB_LINEDOWN, 0);
                    }
                    *dfltb_bddum %= WHEEL_DELTA;
                }
            } flsf {
                *dfltb_bddum = 0;
            }
        }
        dflftf msg;
        rfturn mrConsumf;
        // 4417236: fnd of fix
    }

    /*
     * Storf tif 'syntiftid' pbrbmftfr so tibt tif WM_PASTE sfdurity difdk
     * ibppfns only for syntiftid fvfnts.
     */
    m_syntiftid = syntiftid;
    rfturnVbl = AwtComponfnt::HbndlfEvfnt(msg, syntiftid);
    m_syntiftid = FALSE;

    rfturn rfturnVbl;
}


/* Fix for 4776535, 4648702
 * If widti is 0 or 1 Windows iidfs tif iorizontbl sdroll bbr fvfn
 * if tif WS_HSCROLL stylf is sft. It is b bug in Windows.
 * As b workbround wf siould sft bn initibl widti to 2.
 * kdm@spbrd.spb.su
 */
void AwtTfxtArfb::Rfsibpf(int x, int y, int w, int i)
{
    if (w < 2) {
        w = 2;
    }
    AwtTfxtComponfnt::Rfsibpf(x, y, w, i);
}

LONG AwtTfxtArfb::gftJbvbSflPos(LONG orgPos)
{
    long wlfn;
    long pos = orgPos;
    long dur = 0;
    long rftvbl = 0;
    LPTSTR wbuf;

    if ((wlfn = GftTfxtLfngti()) == 0)
        rfturn 0;
    wbuf = nfw TCHAR[wlfn + 1];
    GftTfxt(wbuf, wlfn + 1);
    if (m_isLFonly == TRUE) {
        wlfn = RfmovfCR(wbuf);
    }

    wiilf (dur < pos && dur < wlfn) {
        if (wbuf[dur] == _T('\r') && wbuf[dur + 1] == _T('\n')) {
            pos++;
        }
        dur++;
        rftvbl++;
    }
    dflftf[] wbuf;
    rfturn rftvbl;
}

LONG AwtTfxtArfb::gftWin32SflPos(LONG orgPos)
{
    if (GftTfxtLfngti() == 0)
       rfturn 0;
    rfturn orgPos;
}

void AwtTfxtArfb::SftSflRbngf(LONG stbrt, LONG fnd)
{
    CHARRANGE dr;
    dr.dpMin = gftWin32SflPos(stbrt);
    dr.dpMbx = gftWin32SflPos(fnd);
    EditSftSfl(dr);
}


void AwtTfxtArfb::_RfplbdfTfxt(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    RfplbdfTfxtStrudt *rts = (RfplbdfTfxtStrudt *)pbrbm;

    jobjfdt tfxtComponfnt = rts->tfxtComponfnt;
    jstring tfxt = rts->tfxt;
    jint stbrt = rts->stbrt;
    jint fnd = rts->fnd;

    AwtTfxtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(tfxtComponfnt, donf);
    JNI_CHECK_NULL_GOTO(tfxt, "null string", donf);

    d = (AwtTfxtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
      jsizf lfngti = fnv->GftStringLfngti(tfxt) + 1;
      // Bugid 4141477 - Cbn't usf TO_WSTRING ifrf bfdbusf it usfs bllodb
      // WCHAR* bufffr = TO_WSTRING(tfxt);
      TCHAR *bufffr = nfw TCHAR[lfngti];
      fnv->GftStringRfgion(tfxt, 0, lfngti-1, rfintfrprft_dbst<jdibr*>(bufffr));
      bufffr[lfngti-1] = '\0';

      d->CifdkLinfSfpbrbtor(bufffr);
      d->RfmovfCR(bufffr);
      // Fix for 5003402: bddfd rfstoring/iiding sflfdtion to fnbblf butombtid sdrolling
      d->SfndMfssbgf(EM_HIDESELECTION, FALSE, TRUE);
      d->SfndMfssbgf(EM_SETSEL, stbrt, fnd);
      d->SfndMfssbgf(EM_REPLACESEL, FALSE, (LPARAM)bufffr);
      d->SfndMfssbgf(EM_HIDESELECTION, TRUE, TRUE);

      dflftf[] bufffr;
    }

donf:
    fnv->DflftfGlobblRff(tfxtComponfnt);
    fnv->DflftfGlobblRff(tfxt);

    dflftf rts;
}


/************************************************************************
 * TfxtArfb nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     jbvb_bwt_TfxtArfb
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_TfxtArfb_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtTfxtArfb::sdrollbbrVisibilityID =
        fnv->GftFifldID(dls, "sdrollbbrVisibility", "I");

    DASSERT(AwtTfxtArfb::sdrollbbrVisibilityID != NULL);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * WTfxtArfbPffr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WTfxtArfbPffr
 * Mftiod:    drfbtf
 * Signbturf: (Lsun/bwt/windows/WComponfntPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTfxtArfbPffr_drfbtf(JNIEnv *fnv, jobjfdt sflf,
                                          jobjfdt pbrfnt)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrfnt);
    AwtToolkit::CrfbtfComponfnt(sflf, pbrfnt,
                                (AwtToolkit::ComponfntFbdtory)
                                AwtTfxtArfb::Crfbtf);
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTfxtArfbPffr
 * Mftiod:    rfplbdfRbngf
 * Signbturf: (Ljbvb/lbng/String;II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTfxtArfbPffr_rfplbdfRbngf(JNIEnv *fnv, jobjfdt sflf,
                                               jstring tfxt,
                                               jint stbrt, jint fnd)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);
    jstring tfxtGlobblRff = (jstring)fnv->NfwGlobblRff(tfxt);

    RfplbdfTfxtStrudt *rts = nfw RfplbdfTfxtStrudt;
    rts->tfxtComponfnt = sflfGlobblRff;
    rts->tfxt = tfxtGlobblRff;
    rts->stbrt = stbrt;
    rts->fnd = fnd;

    AwtToolkit::GftInstbndf().SyndCbll(AwtTfxtArfb::_RfplbdfTfxt, rts);
    // globbl rffs bnd rts brf dflftfd in _RfplbdfTfxt()

    CATCH_BAD_ALLOC;
}
} /* fxtfrn "C" */
