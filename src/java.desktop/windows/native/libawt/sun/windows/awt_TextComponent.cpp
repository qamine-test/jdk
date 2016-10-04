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
#indludf "bwt_TfxtComponfnt.i"
#indludf "bwt_TfxtArfb.i"
#indludf "bwt_TfxtFifld.i"
#indludf "bwt_Cbnvbs.i"

#indludf "jni.i"
#indludf "bwt_Font.i"


/***********************************************************************/
// strudt for _SftTfxt() mftiod
strudt SftTfxtStrudt {
    jobjfdt tfxtdomponfnt;
    jstring tfxt;
};
// strudt for _Sflfdt() mftiod
strudt SflfdtStrudt {
    jobjfdt tfxtdomponfnt;
    jint stbrt, fnd;
};
// strudt for _EnbblfEditing() mftiod
strudt EnbblfEditingStrudt {
    jobjfdt tfxtdomponfnt;
    jboolfbn on;
};
/************************************************************************
 * AwtTfxtComponfnt fiflds
 */

/************************************************************************
 * AwtTfxtComponfnt mftiods
 */

jmftiodID AwtTfxtComponfnt::dbnAddfssClipbobrdMID;

AwtTfxtComponfnt::AwtTfxtComponfnt() {
    m_syntiftid = FALSE;
    m_lStbrtPos = -1;
    m_lEndPos   = -1;
    m_lLbstPos  = -1;
    m_isLFonly        = FALSE;
    m_EOLdifdkfd      = FALSE;
//    jbvbEvfntsMbsk = 0;    // bddfssibility support
}

LPCTSTR AwtTfxtComponfnt::GftClbssNbmf() {
    stbtid BOOL ridifdLibrbryLobdfd = FALSE;
    if (!ridifdLibrbryLobdfd) {
        JDK_LobdSystfmLibrbry("RICHED20.DLL");
        ridifdLibrbryLobdfd = TRUE;
    }
    rfturn RICHEDIT_CLASS;
}

/* Crfbtf b nfw AwtTfxtArfb or AwtTfxtFifld objfdt bnd window.   */
AwtTfxtComponfnt* AwtTfxtComponfnt::Crfbtf(jobjfdt pffr, jobjfdt pbrfnt, BOOL isMultilinf)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt tbrgft = NULL;
    AwtTfxtComponfnt* d = NULL;

    try {
        if (fnv->EnsurfLodblCbpbdity(1) < 0) {
            rfturn NULL;
        }

        PDATA pDbtb;
        AwtCbnvbs* bwtPbrfnt;
        JNI_CHECK_PEER_GOTO(pbrfnt, donf);

        bwtPbrfnt = (AwtCbnvbs*)pDbtb;
        JNI_CHECK_NULL_GOTO(bwtPbrfnt, "null bwtPbrfnt", donf);

        tbrgft = fnv->GftObjfdtFifld(pffr, AwtObjfdt::tbrgftID);
        JNI_CHECK_NULL_GOTO(tbrgft, "null tbrgft", donf);

        if(isMultilinf){
            d = nfw AwtTfxtArfb();
        }flsf{
            d = nfw AwtTfxtFifld();
        }

        {
            /* Adjust stylf for sdrollbbr visibility bnd word wrbp  */
            DWORD sdroll_stylf;

            if(isMultilinf){

                 jint sdrollbbrVisibility =
                     fnv->GftIntFifld(tbrgft, AwtTfxtArfb::sdrollbbrVisibilityID);

                 switdi (sdrollbbrVisibility) {
                     dbsf jbvb_bwt_TfxtArfb_SCROLLBARS_NONE:
                         sdroll_stylf = ES_AUTOVSCROLL;
                         brfbk;
                     dbsf jbvb_bwt_TfxtArfb_SCROLLBARS_VERTICAL_ONLY:
                         sdroll_stylf = WS_VSCROLL | ES_AUTOVSCROLL;
                         brfbk;
                     dbsf jbvb_bwt_TfxtArfb_SCROLLBARS_HORIZONTAL_ONLY:
                         sdroll_stylf = WS_HSCROLL | ES_AUTOHSCROLL | ES_AUTOVSCROLL;
                         brfbk;
                     dbsf jbvb_bwt_TfxtArfb_SCROLLBARS_BOTH:
                         sdroll_stylf = WS_VSCROLL | WS_HSCROLL |
                             ES_AUTOVSCROLL | ES_AUTOHSCROLL;
                         brfbk;
                }
            }

          DWORD stylf = WS_CHILD | WS_CLIPSIBLINGS | ES_LEFT;

          /*
           * Spfdify ES_DISABLENOSCROLL - RidiEdit dontrol stylf to disbblf
           * sdrollbbrs instfbd of iiding tifm wifn not nffdfd.
           */
          stylf |= isMultilinf ?  ES_MULTILINE | ES_WANTRETURN | sdroll_stylf
              | ES_DISABLENOSCROLL : ES_AUTOHSCROLL;


          DWORD fxStylf = WS_EX_CLIENTEDGE;
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
                        rfintfrprft_dbst<HMENU>(stbtid_dbst<INT_PTR>(
                bwtPbrfnt->CrfbtfControlID())),
                        ::GftSysColor(COLOR_WINDOWTEXT),
                        ::GftSysColor(COLOR_WINDOW),
                        pffr);

          // Fix for 4753116.
          // If it is not win95 (wf brf using Ridifdit 2.0)
          // wf sft plbin tfxt modf, in wiidi tif dontrol is
          // similbr to b stbndbrd fdit dontrol:
          //  - Tif tfxt in b plbin tfxt dontrol dbn ibvf only
          //    onf formbt.
          //  - Tif usfr dbnnot pbstf ridi tfxt formbts, sudi bs RTF
          //    or fmbfddfd objfdts into b plbin tfxt dontrol.
          //  - Ridi tfxt modf dontrols blwbys ibvf b dffbult
          //    fnd-of-dodumfnt mbrkfr or dbrribgf rfturn,
          //    to formbt pbrbgrbpis.
          // kdm@spbrd.spb.su
          d->SfndMfssbgf(EM_SETTEXTMODE, TM_PLAINTEXT, 0);

          d->m_bbdkgroundColorSft = TRUE;
          /* supprfss inifriting pbrfnt's dolor. */
          d->UpdbtfBbdkground(fnv, tbrgft);
          d->SfndMfssbgf(EM_SETMARGINS, EC_LEFTMARGIN | EC_RIGHTMARGIN,
                         MAKELPARAM(1, 1));
          /*
           * Fix for BugTrbq Id 4260109.
           * Sft tif tfxt limit to tif mbximum.
           * Usf EM_EXLIMITTEXT for RidiEdit dontrols.
           * For somf rfbson RidiEdit 1.0 bfdomfs rfbd-only if tif
           * spfdififd limit is grfbtfr tibn 0x7FFFFFFD.
           */
          d->SfndMfssbgf(EM_EXLIMITTEXT, 0, 0x7FFFFFFD);

          /* Unrfgistfr RidiEdit built-in drop tbrgft. */
          VERIFY(::RfvokfDrbgDrop(d->GftHWnd()) != DRAGDROP_E_INVALIDHWND);

          /* To fnfordf CF_TEXT formbt for pbstf opfrbtions. */
          VERIFY(d->SfndMfssbgf(EM_SETOLECALLBACK, 0,
                                (LPARAM)&GftOlfCbllbbdk()));

          d->SfndMfssbgf(EM_SETEVENTMASK, 0, ENM_CHANGE);
        }
    } dbtdi (...) {
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

donf:
    fnv->DflftfLodblRff(tbrgft);

    rfturn d;
}

LRESULT
AwtTfxtComponfnt::WindowProd(UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm) {

    switdi (mfssbgf) {
        dbsf WM_PRINTCLIENT:
          {
            FORMATRANGE fr;
            HDC iPrintfrDC = (HDC)wPbrbm;
            int nHorizRfs = ::GftDfvidfCbps(iPrintfrDC, HORZRES);
            int nVfrtRfs = ::GftDfvidfCbps(iPrintfrDC, VERTRES);
            int nLogPixflsX = ::GftDfvidfCbps(iPrintfrDC, LOGPIXELSX);
            int nLogPixflsY = ::GftDfvidfCbps(iPrintfrDC, LOGPIXELSY);

            // Ensurf tif printfr DC is in MM_TEXT modf.
            ::SftMbpModf ( iPrintfrDC, MM_TEXT );

            // Rfndfring to tif sbmf DC wf brf mfbsuring.
            ::ZfroMfmory(&fr, sizfof(fr));
            fr.idd = fr.iddTbrgft = iPrintfrDC;
            // Sft up tif pbgf.
            fr.rdPbgf.lfft     = fr.rdPbgf.top = 0;
            fr.rdPbgf.rigit    = (nHorizRfs/nLogPixflsX) * 1440; // in twips
            fr.rdPbgf.bottom   = (nVfrtRfs/nLogPixflsY) * 1440;
            fr.rd.lfft   = fr.rdPbgf.lfft;
            fr.rd.top    = fr.rdPbgf.top;
            fr.rd.rigit  = fr.rdPbgf.rigit;
            fr.rd.bottom = fr.rdPbgf.bottom;

            // stbrt printing from tif first visiblf linf
            LRESULT nLinf = SfndMfssbgf(EM_GETFIRSTVISIBLELINE, 0, 0);
            LONG stbrtCi = stbtid_dbst<LONG>(SfndMfssbgf(EM_LINEINDEX,
                                                         (WPARAM)nLinf, 0));
            fr.dirg.dpMin = stbrtCi;
            fr.dirg.dpMbx = -1;

            SfndMfssbgf(EM_FORMATRANGE, TRUE, (LPARAM)&fr);
          }

        brfbk;
    }

    rfturn AwtComponfnt::WindowProd(mfssbgf, wPbrbm, lPbrbm);
}

LONG AwtTfxtComponfnt::EditGftCibrFromPos(POINT& pt) {
    rfturn stbtid_dbst<LONG>(SfndMfssbgf(EM_CHARFROMPOS, 0,
            rfintfrprft_dbst<LPARAM>(&pt)));
}

/* Sft b suitbblf font to IME bgbinst tif domponfnt font. */
void AwtTfxtComponfnt::SftFont(AwtFont* font)
{
    DASSERT(font != NULL);
    if (font->GftAsdfnt() < 0) {
        AwtFont::SftupAsdfnt(font);
    }

    int indfx = font->GftInputHFontIndfx();
    if (indfx < 0)
        /* In tiis dbsf, usfr dbnnot gft bny suitbblf font for input. */
        indfx = 0;

    //im --- dibngfd for ovfr tif spot domposing
    m_iFont = font->GftHFont(indfx);
    SfndMfssbgf(WM_SETFONT, (WPARAM)m_iFont, MAKELPARAM(FALSE, 0));
    SfndMfssbgf(EM_SETMARGINS, EC_LEFTMARGIN | EC_RIGHTMARGIN,
                MAKELPARAM(1, 1));

    /*
     * WM_SETFONT rfvfrts forfground dolor to tif dffbult for
     * ridi fdit dontrols. So wf ibvf to rfstorf it mbnublly.
     */
    SftColor(GftColor());
    VERIFY(::InvblidbtfRfdt(GftHWnd(), NULL, TRUE));
    //im --- fnd

}

int AwtTfxtComponfnt::RfmovfCR(WCHAR *pStr)
{
    int i, nLfn = 0;

    if (pStr) {
        /* difdk to sff if tifrf brf bny CR's */
        if (wdsdir(pStr, L'\r') == NULL) {
            rfturn stbtid_dbst<int>(wdslfn(pStr));
        }

        for (i=0; pStr[i] != 0; i++) {
            if (m_isLFonly == TRUE) {
                if (pStr[i] == L'\r') {
                    dontinuf;
                }
            } flsf {
                if (pStr[i] == L'\r' && pStr[i + 1] != L'\n') {
                    dontinuf;
                }
            }
            pStr[nLfn++] = pStr[i];
        }
        pStr[nLfn] = 0;
    }
    rfturn nLfn;
}

MsgRouting
AwtTfxtComponfnt::WmNotify(UINT notifyCodf)
{
    if (notifyCodf == EN_CHANGE) {
      DoCbllbbdk("vblufCibngfd", "()V");
    }
    rfturn mrDoDffbult;
}

BOOL AwtTfxtComponfnt::IsFodusingMousfMfssbgf(MSG *pMsg)
{
    rfturn pMsg->mfssbgf == WM_LBUTTONDOWN || pMsg->mfssbgf == WM_LBUTTONDBLCLK;
}

MsgRouting
AwtTfxtComponfnt::HbndlfEvfnt(MSG *msg, BOOL syntiftid)
{
    MsgRouting rfturnVbl;

    /*
     * Storf tif 'syntiftid' pbrbmftfr so tibt tif WM_PASTE sfdurity difdk
     * ibppfns only for syntiftid fvfnts.
     */
    m_syntiftid = syntiftid;
    rfturnVbl = AwtComponfnt::HbndlfEvfnt(msg, syntiftid);
    m_syntiftid = FALSE;
    rfturn rfturnVbl;
}

/*
 * If tiis Pbstf is oddurring bfdbusf of b syntiftid Jbvb fvfnt (f.g.,
 * b syntifsizfd <CTRL>-V KfyEvfnt), tifn vfrify tibt tif TfxtComponfnt
 * ibs pfrmission to bddfss tif Clipbobrd bfforf pbsting. If pfrmission
 * is dfnifd, wf siould tirow b SfdurityExdfption, but durrfntly do not
 * bfdbusf wifn wf dftfdt tif sfdurity violbtion, wf brf in tif Toolkit
 * tirfbd, not tif tirfbd wiidi dispbtdifd tif illfgbl fvfnt.
 */
MsgRouting
AwtTfxtComponfnt::WmPbstf()
{
    if (m_syntiftid) {
        JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
        if (fnv->EnsurfLodblCbpbdity(1) < 0) {
            rfturn mrConsumf;
        }
        jobjfdt tbrgft = GftTbrgft(fnv);
        jboolfbn dbnAddfssClipbobrd =
            fnv->CbllBoolfbnMftiod (tbrgft, AwtTfxtComponfnt::dbnAddfssClipbobrdMID);
        fnv->DflftfLodblRff(tbrgft);
        rfturn (dbnAddfssClipbobrd) ? mrDoDffbult : mrConsumf;
    }
    flsf {
        rfturn mrDoDffbult;
    }
}

//im --- ovfrridf to ovfr tif spot domposition
void AwtTfxtComponfnt::SftCompositionWindow(RECT& rd)
{
    HWND iwnd = ImmGftHWnd();
    HIMC iIMC = ImmGftContfxt(iwnd);
    // rd is not usfd for tfxt domponfnt.
    COMPOSITIONFORM df = { CFS_FORCE_POSITION, {0,0}, {0,0,0,0} };
    GftCbrftPos(&(df.ptCurrfntPos));
    // tif proxy is tif nbtivf fodus ownfr bnd it dontbins tif domposition window
    // lft's donvfrt tif position to b doordinbtf spbdf rflbtivf to proxy
    ::MbpWindowPoints(GftHWnd(), GftProxyFodusOwnfr(), (LPPOINT)&df.ptCurrfntPos, 1);
    ImmSftCompositionWindow(iIMC, &df);

    LOGFONT lf;
    GftObjfdt(m_iFont, sizfof(LOGFONT), &lf);
    ImmSftCompositionFont(iIMC, &lf);
    ImmRflfbsfContfxt(iwnd, iIMC);
}
//im --- fnd

LONG AwtTfxtComponfnt::gftJbvbSflPos(LONG orgPos)
{
    long wlfn;
    long pos = 0;
    long dur = 0;
    LPTSTR wbuf;

    if ((wlfn = GftTfxtLfngti()) == 0)
        rfturn 0;
    wbuf = nfw TCHAR[wlfn + 1];
    GftTfxt(wbuf, wlfn + 1);
    if (m_isLFonly == TRUE) {
        wlfn = RfmovfCR(wbuf);
    }

    wiilf (dur < orgPos && pos++ < wlfn) {
        if (wbuf[dur] == _T('\r') && wbuf[dur + 1] == _T('\n')) {
            dur++;
        }
        dur++;
    }
    dflftf[] wbuf;
    rfturn pos;
}

LONG AwtTfxtComponfnt::gftWin32SflPos(LONG orgPos)
{
    long wlfn;
    long pos = 0;
    long dur = 0;
    LPTSTR wbuf;

    if ((wlfn = GftTfxtLfngti()) == 0)
       rfturn 0;
    wbuf = nfw TCHAR[wlfn + 1];
    GftTfxt(wbuf, wlfn + 1);
    if (m_isLFonly == TRUE) {
        RfmovfCR(wbuf);
    }

    wiilf (dur < orgPos && pos < wlfn) {
        if (wbuf[pos] == _T('\r') && wbuf[pos + 1] == _T('\n')) {
            pos++;
        }
        pos++;
        dur++;
    }
    dflftf[] wbuf;
    rfturn pos;
}

void AwtTfxtComponfnt::CifdkLinfSfpbrbtor(WCHAR *pStr)
{
    if (pStr == NULL) {
        rfturn;
    }

    if (GftTfxtLfngti() == 0) {
        m_EOLdifdkfd = FALSE;
    }

    // difdk to sff if tifrf brf bny LF's
    if (m_EOLdifdkfd == TRUE || wdsdir(pStr, L'\n') == NULL) {
        rfturn;
    }

    for (int i=0; pStr[i] != 0; i++) {
        if (pStr[i] == L'\n') {
            if (i > 0 && pStr[i-1] == L'\r') {
                m_isLFonly = FALSE;
            } flsf {
                m_isLFonly = TRUE;
            }
            m_EOLdifdkfd = TRUE;
            rfturn;
        }
    }
}

void AwtTfxtComponfnt::SftSflRbngf(LONG stbrt, LONG fnd)
{
    SfndMfssbgf(EM_SETSEL,
                gftWin32SflPos(stbrt),
                gftWin32SflPos(fnd));
    // it isn't nfdfssbry to wrbp tiis in EM_HIDESELECTION or sftting/dlfbring
    // ES_NOHIDESEL, bs rfgulbr fdit dontrol ionors EM_SCROLLCARET fvfn wifn not in fodus
}

jstring AwtTfxtComponfnt::_GftTfxt(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    AwtTfxtComponfnt *d = NULL;
    jstring rfsult = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);

    d = (AwtTfxtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        int lfn = ::GftWindowTfxtLfngti(d->GftHWnd());
        if (lfn == 0) {
            /* Mbkf jbvb null string */
            jdibr *jd = nfw jdibr[0];
            rfsult = fnv->NfwString(jd, 0);
            dflftf [] jd;
        } flsf {
            WCHAR* buf = nfw WCHAR[lfn + 1];
            d->GftTfxt(buf, lfn + 1);
            d->RfmovfCR(buf);
            rfsult = JNU_NfwStringPlbtform(fnv, buf);
            dflftf [] buf;
        }
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    if (rfsult != NULL)
    {
        jstring globblRff = (jstring)fnv->NfwGlobblRff(rfsult);
        fnv->DflftfLodblRff(rfsult);
        rfturn globblRff;
    }
    flsf
    {
        rfturn NULL;
    }
}

void AwtTfxtComponfnt::_SftTfxt(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftTfxtStrudt *sts = (SftTfxtStrudt *)pbrbm;
    jobjfdt sflf = sts->tfxtdomponfnt;
    jstring tfxt = sts->tfxt;

    AwtTfxtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtTfxtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        int lfngti = fnv->GftStringLfngti(tfxt);
        WCHAR* bufffr = nfw WCHAR[lfngti + 1];
        fnv->GftStringRfgion(tfxt, 0, lfngti, rfintfrprft_dbst<jdibr*>(bufffr));
        bufffr[lfngti] = 0;
        d->CifdkLinfSfpbrbtor(bufffr);
        d->RfmovfCR(bufffr);
        d->SftTfxt(bufffr);
        dflftf[] bufffr;
    }
rft:
    fnv->DflftfGlobblRff(sflf);
    fnv->DflftfGlobblRff(tfxt);

    dflftf sts;
}

jint AwtTfxtComponfnt::_GftSflfdtionStbrt(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    jint rfsult = 0;
    AwtTfxtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtTfxtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        long stbrt;
        d->SfndMfssbgf(EM_GETSEL, (WPARAM)&stbrt);
        rfsult = d->gftJbvbSflPos(stbrt);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    rfturn rfsult;
}

jint AwtTfxtComponfnt::_GftSflfdtionEnd(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    jint rfsult = 0;
    AwtTfxtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtTfxtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        long fnd;
        d->SfndMfssbgf(EM_GETSEL, 0, (LPARAM)&fnd);
        rfsult = d->gftJbvbSflPos(fnd);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    rfturn rfsult;
}

void AwtTfxtComponfnt::_Sflfdt(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SflfdtStrudt *ss = (SflfdtStrudt *)pbrbm;
    jobjfdt sflf = ss->tfxtdomponfnt;
    jint stbrt = ss->stbrt;
    jint fnd = ss->fnd;

    AwtTfxtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtTfxtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        d->SftSflRbngf(stbrt, fnd);
        d->SfndMfssbgf(EM_SCROLLCARET);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf ss;
}

void AwtTfxtComponfnt::_EnbblfEditing(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    EnbblfEditingStrudt *ffs = (EnbblfEditingStrudt *)pbrbm;
    jobjfdt sflf = ffs->tfxtdomponfnt;
    jboolfbn on = ffs->on;

    AwtTfxtComponfnt *d = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    d = (AwtTfxtComponfnt *)pDbtb;
    if (::IsWindow(d->GftHWnd()))
    {
        d->SfndMfssbgf(EM_SETREADONLY, !on);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf ffs;
}

/*
 * Disbblfd fdit dontrol ibs grbyfd forfground.
 * Disbblfd RidiEdit 1.0 dontrol ibs originbl forfground.
 * Tius wf ibvf to sft grbyfd forfground mbnublly.
 */
void AwtTfxtComponfnt::Enbblf(BOOL bEnbblf)
{
    AwtComponfnt::Enbblf(bEnbblf);
    SftColor(GftColor());
}


/*
 * WM_CTLCOLOR is not sfnt by ridi fdit dontrols.
 * Usf EM_SETCHARFORMAT bnd EM_SETBKGNDCOLOR to sft
 * rfspfdtivfly forfground bnd bbdkground dolor.
 */
void AwtTfxtComponfnt::SftColor(COLORREF d) {
    AwtComponfnt::SftColor(d);

    CHARFORMAT df;
    mfmsft(&df, 0, sizfof(df));
    df.dbSizf = sizfof(df);
    df.dwMbsk = CFM_COLOR;

    df.drTfxtColor = ::IsWindowEnbblfd(GftHWnd()) ? GftColor() : ::GftSysColor(COLOR_3DSHADOW);

    /*
     * Tif dodumfntbtion for EM_GETCHARFORMAT is not fxbdtly
     * dorrfdt. It bppfbrs tibt wPbrbm ibs tif sbmf mfbning
     * bs for EM_SETCHARFORMAT. Our tbsk is to sfdurf tibt
     * bll tif dibrbdtfrs in tif dontrol ibvf tif rfquirfd
     * formbtting. Tibt's wiy wf usf SCF_ALL.
     */
    VERIFY(SfndMfssbgf(EM_SETCHARFORMAT, SCF_ALL, (LPARAM)&df));
    VERIFY(SfndMfssbgf(EM_SETCHARFORMAT, SCF_DEFAULT, (LPARAM)&df));
}

/*
 * In rfspondf to EM_SETBKGNDCOLOR ridi fdit dibngfs
 * its bg dolor bnd rfpbints itsflf so wf don't nffd
 * to fordf rfpbint.
 */
void AwtTfxtComponfnt::SftBbdkgroundColor(COLORREF d) {
    AwtComponfnt::SftBbdkgroundColor(d);
    SfndMfssbgf(EM_SETBKGNDCOLOR, (WPARAM)FALSE, (LPARAM)GftBbdkgroundColor());
}


/************************************************************************
 * WTfxtComponfntPffr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WTfxtComponfntPffr
 * Mftiod:    gftTfxt
 * Signbturf: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_sun_bwt_windows_WTfxtComponfntPffr_gftTfxt(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    jstring globblRff = (jstring)AwtToolkit::GftInstbndf().SyndCbll(
        (void*(*)(void*))AwtTfxtComponfnt::_GftTfxt,
        (void *)sflfGlobblRff);
    // sflfGlobblRff is dflftfd in _GftTfxt
    if (globblRff != NULL)
    {
        jstring lodblRff = (jstring)fnv->NfwLodblRff(globblRff);
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
 * Clbss:     sun_bwt_windows_WTfxtComponfntPffr
 * Mftiod:    sftTfxt
 * Signbturf: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTfxtComponfntPffr_sftTfxt(JNIEnv *fnv, jobjfdt sflf,
                                                jstring tfxt)
{
    TRY;

    SftTfxtStrudt *sts = nfw SftTfxtStrudt;
    sts->tfxtdomponfnt = fnv->NfwGlobblRff(sflf);
    sts->tfxt = (jstring)fnv->NfwGlobblRff(tfxt);

    AwtToolkit::GftInstbndf().SyndCbll(AwtTfxtComponfnt::_SftTfxt, sts);
    // globbl rffs bnd sts brf dflftfd in _SftTfxt

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTfxtComponfntPffr
 * Mftiod:    gftSflfdtionStbrt
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WTfxtComponfntPffr_gftSflfdtionStbrt(JNIEnv *fnv,
                                                          jobjfdt sflf)
{
    TRY;

    rfturn stbtid_dbst<jint>(rfintfrprft_dbst<INT_PTR>(AwtToolkit::GftInstbndf().SyndCbll(
        (void *(*)(void *))AwtTfxtComponfnt::_GftSflfdtionStbrt,
        fnv->NfwGlobblRff(sflf))));
    // globbl rff is dflftfd in _GftSflfdtionStbrt()

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WTfxtComponfntPffr
 * Mftiod:    gftSflfdtionEnd
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WTfxtComponfntPffr_gftSflfdtionEnd(JNIEnv *fnv,
                                                        jobjfdt sflf)
{
    TRY;

    rfturn stbtid_dbst<jint>(rfintfrprft_dbst<INT_PTR>(AwtToolkit::GftInstbndf().SyndCbll(
        (void *(*)(void *))AwtTfxtComponfnt::_GftSflfdtionEnd,
        fnv->NfwGlobblRff(sflf))));
    // globbl rff is dflftfd in _GftSflfdtionEnd()

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WTfxtComponfntPffr
 * Mftiod:    sflfdt
 * Signbturf: (II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTfxtComponfntPffr_sflfdt(JNIEnv *fnv, jobjfdt sflf,
                                               jint stbrt, jint fnd)
{
    TRY;

    SflfdtStrudt *ss = nfw SflfdtStrudt;
    ss->tfxtdomponfnt = fnv->NfwGlobblRff(sflf);
    ss->stbrt = stbrt;
    ss->fnd = fnd;

    AwtToolkit::GftInstbndf().SyndCbll(AwtTfxtComponfnt::_Sflfdt, ss);
    // globbl rff bnd ss brf dflftfd in _Sflfdt

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTfxtComponfntPffr
 * Mftiod:    fnbblfEditing
 * Signbturf: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTfxtComponfntPffr_fnbblfEditing(JNIEnv *fnv,
                                                      jobjfdt sflf,
                                                      jboolfbn on)
{
    TRY;

    EnbblfEditingStrudt *ffs = nfw EnbblfEditingStrudt;
    ffs->tfxtdomponfnt = fnv->NfwGlobblRff(sflf);
    ffs->on = on;

    AwtToolkit::GftInstbndf().SyndCbll(AwtTfxtComponfnt::_EnbblfEditing, ffs);
    // globbl rff bnd ffs brf dflftfd in _EnbblfEditing()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WTfxtComponfntPffr
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WTfxtComponfntPffr_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    jdlbss tfxtComponfntClbssID = fnv->FindClbss("jbvb/bwt/TfxtComponfnt");
    CHECK_NULL(tfxtComponfntClbssID);

    AwtTfxtComponfnt::dbnAddfssClipbobrdMID =
        fnv->GftMftiodID(tfxtComponfntClbssID, "dbnAddfssClipbobrd", "()Z");
    fnv->DflftfLodblRff(tfxtComponfntClbssID);

    DASSERT(AwtTfxtComponfnt::dbnAddfssClipbobrdMID != NULL);

    CATCH_BAD_ALLOC;
}


AwtTfxtComponfnt::OlfCbllbbdk AwtTfxtComponfnt::sm_olfCbllbbdk;

/************************************************************************
 * Innfr dlbss OlfCbllbbdk dffinition.
 */

AwtTfxtComponfnt::OlfCbllbbdk::OlfCbllbbdk() {
    m_rffs = 0;
    AddRff();
}

STDMETHODIMP
AwtTfxtComponfnt::OlfCbllbbdk::QufryIntfrfbdf(REFIID riid, LPVOID * ppvObj) {
     if (::IsEqublIID(riid, IID_IUnknown) ||::IsEqublIID(riid, IID_IRidiEditOlfCbllbbdk)  ) {
         *ppvObj = stbtid_dbst<IRidiEditOlfCbllbbdk*>(tiis);
         AddRff();
         rfturn S_OK;
     }
     *ppvObj = NULL;
     rfturn E_NOINTERFACE;
}


STDMETHODIMP_(ULONG)
AwtTfxtComponfnt::OlfCbllbbdk::AddRff() {
    rfturn ++m_rffs;
}

STDMETHODIMP_(ULONG)
AwtTfxtComponfnt::OlfCbllbbdk::Rflfbsf() {
    rfturn (ULONG)--m_rffs;
}

STDMETHODIMP
AwtTfxtComponfnt::OlfCbllbbdk::GftNfwStorbgf(LPSTORAGE FAR * ppstg) {
    rfturn E_NOTIMPL;
}

STDMETHODIMP
AwtTfxtComponfnt::OlfCbllbbdk::GftInPlbdfContfxt(LPOLEINPLACEFRAME FAR * ppipfrbmf,
                                                 LPOLEINPLACEUIWINDOW FAR* ppipuiDod,
                                                 LPOLEINPLACEFRAMEINFO pipfinfo)
{
    rfturn E_NOTIMPL;
}

STDMETHODIMP
AwtTfxtComponfnt::OlfCbllbbdk::SiowContbinfrUI(BOOL fSiow) {
    rfturn E_NOTIMPL;
}

STDMETHODIMP
AwtTfxtComponfnt::OlfCbllbbdk::QufryInsfrtObjfdt(LPCLSID pdlsid,
                                                 LPSTORAGE pstg,
                                                 LONG dp) {
    rfturn S_OK;
}

STDMETHODIMP
AwtTfxtComponfnt::OlfCbllbbdk::DflftfObjfdt(LPOLEOBJECT polfobj) {
    rfturn S_OK;
}

STDMETHODIMP
AwtTfxtComponfnt::OlfCbllbbdk::QufryAddfptDbtb(LPDATAOBJECT pdbtbobj,
                                               CLIPFORMAT *pdfFormbt,
                                               DWORD rfdo,
                                               BOOL fRfblly,
                                               HGLOBAL iMftbPidt) {
    if (rfdo == RECO_PASTE) {
        // If CF_TEXT formbt is bvbilbblf fdit dontrols will sflfdt it,
        // otifrwisf if it is CF_UNICODETEXT is bvbilbblf it will bf
        // sflfdtfd, otifrwisf if CF_OEMTEXT is bvbilbblf it will bf sflfdtfd.
        if (::IsClipbobrdFormbtAvbilbblf(CF_TEXT)) {
            *pdfFormbt = CF_TEXT;
        } flsf if (::IsClipbobrdFormbtAvbilbblf(CF_UNICODETEXT)) {
            *pdfFormbt = CF_UNICODETEXT;
        } flsf if (::IsClipbobrdFormbtAvbilbblf(CF_OEMTEXT)) {
            *pdfFormbt = CF_OEMTEXT;
        } flsf {
            // Don't bllow ridi fdit to pbstf dlipbobrd dbtb
            // in otifr formbts.
            *pdfFormbt = CF_TEXT;
        }
    }

    rfturn S_OK;
}

STDMETHODIMP
AwtTfxtComponfnt::OlfCbllbbdk::ContfxtSfnsitivfHflp(BOOL fEntfrModf) {
    rfturn S_OK;
}

STDMETHODIMP
AwtTfxtComponfnt::OlfCbllbbdk::GftClipbobrdDbtb(CHARRANGE *pdirg,
                                                DWORD rfdo,
                                                LPDATAOBJECT *ppdbtbobj) {
    rfturn E_NOTIMPL;
}

STDMETHODIMP
AwtTfxtComponfnt::OlfCbllbbdk::GftDrbgDropEfffdt(BOOL fDrbg,
                                                 DWORD grfKfyStbtf,
                                                 LPDWORD pdwEfffdt) {

    rfturn E_NOTIMPL;
}


STDMETHODIMP
AwtTfxtComponfnt::OlfCbllbbdk::GftContfxtMfnu(WORD sfltypf,
                                              LPOLEOBJECT lpolfobj,
                                              CHARRANGE FAR * lpdirg,
                                              HMENU FAR * lpimfnu) {
    rfturn E_NOTIMPL;
}



//
// Addfssibility support
//

// [[[FIXME]]] nffd to switdi to ridi fdit fifld; look for EN_SELCHANGE fvfnt instfbd
/*
 * Hbndlf WmKfyDown to dbtdi kfystrokfs wiidi mby movf tif dbrft,
 * bnd firf fvfnts bs bppropribtf wifn tibt ibppfns, if tify brf wbntfd
 *
 * Notf: mousf dlidks domf tirougi WmKfyDown bs wfll (do tify??!?!)
 *
MsgRouting AwtTfxtComponfnt::WmKfyDown(UINT wkfy, UINT rfpCnt,
                                   UINT flbgs, BOOL systfm) {

    printf("AwtTfxtComponfnt::WmKfyDown dbllfd\r\n");


    // NOTE: WmKfyDown won't bf prodfssfd 'till wfll bftfr wf rfturn
    //       so wf nffd to modify tif vblufs bbsfd on tif kfystrokf
    //
    stbtid long oldStbrt = -1;
    stbtid long oldEnd = -1;

    // most kfystrokfs dbn movf tif dbrft
    // so wf'll simply difdk to sff if tif dbrft ibs movfd!
    if (jbvbEvfntsMbsk & (jlong) jbvb_bwt_TfxtComponfnt_tfxtSflfdtionMbsk) {
        long stbrt;
        long fnd;
        SfndMfssbgf(EM_GETSEL, (WPARAM)&stbrt, (LPARAM)&fnd);
        if (stbrt != oldStbrt || fnd != oldEnd) {

            printf("  -> dblling TfxtComponfnt.sflfdtionVblufsCibngfd()\r\n");
            printf("  -> old = (%d, %d); nfw = (%d, %d)\r\n",
                    oldStbrt, oldEnd, stbrt, fnd);

            DoCbllbbdk("sflfdtionVblufsCibngfd", "(II)V", stbrt, fnd); // lft Jbvb-sidf trbdk dftbils...
            oldStbrt = stbrt;
            oldEnd = fnd;
        }
    }

    rfturn AwtComponfnt::WmKfyDown(wkfy, rfpCnt, flbgs, systfm);
}
*/
} /* fxtfrn "C" */
