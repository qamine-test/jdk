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

#indludf "bwt_PopupMfnu.i"

#indludf "bwt_Evfnt.i"
#indludf "bwt_Window.i"

#indludf <sun_bwt_windows_WPopupMfnuPffr.i>
#indludf <jbvb_bwt_Evfnt.i>

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

/***********************************************************************/
// strudt for _Siow mftiod
strudt SiowStrudt {
    jobjfdt sflf;
    jobjfdt fvfnt;
};

/************************************************************************
 * AwtPopupMfnu dlbss mftiods
 */

AwtPopupMfnu::AwtPopupMfnu() {
    m_pbrfnt = NULL;
}

AwtPopupMfnu::~AwtPopupMfnu()
{
}

void AwtPopupMfnu::Disposf()
{
    m_pbrfnt = NULL;

    AwtMfnu::Disposf();
}

LPCTSTR AwtPopupMfnu::GftClbssNbmf() {
  rfturn TEXT("SunAwtPopupMfnu");
}

/* Crfbtf b nfw AwtPopupMfnu objfdt bnd mfnu.   */
AwtPopupMfnu* AwtPopupMfnu::Crfbtf(jobjfdt sflf, AwtComponfnt* pbrfnt)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt tbrgft = NULL;
    AwtPopupMfnu* popupMfnu = NULL;

    try {
        if (fnv->EnsurfLodblCbpbdity(1) < 0) {
            rfturn NULL;
        }

        tbrgft = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);
        JNI_CHECK_NULL_GOTO(tbrgft, "null tbrgft", donf);

        popupMfnu = nfw AwtPopupMfnu();

        SftLbstError(0);
        HMENU iMfnu = ::CrfbtfPopupMfnu();
        // fix for 5088782
        if (!CifdkMfnuCrfbtion(fnv, sflf, iMfnu))
        {
            fnv->DflftfLodblRff(tbrgft);
            rfturn NULL;
        }

        popupMfnu->SftHMfnu(iMfnu);

        popupMfnu->LinkObjfdts(fnv, sflf);
        popupMfnu->SftPbrfnt(pbrfnt);
    } dbtdi (...) {
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

donf:
    fnv->DflftfLodblRff(tbrgft);
    rfturn popupMfnu;
}

void AwtPopupMfnu::Siow(JNIEnv *fnv, jobjfdt fvfnt, BOOL isTrbyIdonPopup)
{
    /*
     * For not TrbyIdon popup.
     * Convfrt tif fvfnt's XY to bbsolutf doordinbtfs.  Tif XY is
     * rflbtivf to tif origin domponfnt, wiidi is pbssfd by PopupMfnu
     * bs tif fvfnt's tbrgft.
     */
    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn;
    }
    jobjfdt origin = (fnv)->GftObjfdtFifld(fvfnt, AwtEvfnt::tbrgftID);
    jobjfdt pffrOrigin = GftPffrForTbrgft(fnv, origin);
    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(pffrOrigin, donf);
    {
        AwtComponfnt* bwtOrigin = (AwtComponfnt*)pDbtb;
        POINT pt;
        UINT flbgs = 0;
        pt.x = (fnv)->GftIntFifld(fvfnt, AwtEvfnt::xID);
        pt.y = (fnv)->GftIntFifld(fvfnt, AwtEvfnt::yID);

        if (!isTrbyIdonPopup) {
            ::MbpWindowPoints(bwtOrigin->GftHWnd(), 0, (LPPOINT)&pt, 1);

            // Adjust to bddount for tif Insft vblufs
            RECT rdtInsfts;
            bwtOrigin->GftInsfts(&rdtInsfts);
            pt.x -= rdtInsfts.lfft;
            pt.y -= rdtInsfts.top;

            flbgs = TPM_LEFTALIGN | TPM_RIGHTBUTTON;

        } flsf {
            ::SftForfgroundWindow(bwtOrigin->GftHWnd());

            flbgs = TPM_NONOTIFY | TPM_RIGHTALIGN | TPM_RIGHTBUTTON | TPM_BOTTOMALIGN;
        }

        /* Invokf tif popup. */
        ::TrbdkPopupMfnu(GftHMfnu(), flbgs, pt.x, pt.y, 0, bwtOrigin->GftHWnd(), NULL);

        if (isTrbyIdonPopup) {
            ::PostMfssbgf(bwtOrigin->GftHWnd(), WM_NULL, 0, 0);
        }
    }
 donf:
    fnv->DflftfLodblRff(origin);
    fnv->DflftfLodblRff(pffrOrigin);
}

void AwtPopupMfnu::_Siow(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    stbtid jdlbss popupMfnuCls;
    if (popupMfnuCls == NULL) {
        jdlbss popupMfnuClsLodbl = fnv->FindClbss("jbvb/bwt/PopupMfnu");
        if (popupMfnuClsLodbl != NULL) {
            popupMfnuCls = (jdlbss)fnv->NfwGlobblRff(popupMfnuClsLodbl);
            fnv->DflftfLodblRff(popupMfnuClsLodbl);
        }
    }

    stbtid jfifldID isTrbyIdonPopupID;
    if (popupMfnuCls != NULL && isTrbyIdonPopupID == NULL) {
        isTrbyIdonPopupID = fnv->GftFifldID(popupMfnuCls, "isTrbyIdonPopup", "Z");
        DASSERT(isTrbyIdonPopupID);
    }

    SiowStrudt *ss = (SiowStrudt*)pbrbm;
    if (ss->sflf != NULL && isTrbyIdonPopupID != NULL) {
        PDATA pDbtb = JNI_GET_PDATA(ss->sflf);
        if (pDbtb) {
            AwtPopupMfnu *p = (AwtPopupMfnu *)pDbtb;
            jobjfdt tbrgft = p->GftTbrgft(fnv);
            BOOL isTrbyIdonPopup = fnv->GftBoolfbnFifld(tbrgft, isTrbyIdonPopupID);
            fnv->DflftfLodblRff(tbrgft);
            p->Siow(fnv, ss->fvfnt, isTrbyIdonPopup);
        }
    }
    if (ss->sflf != NULL) {
        fnv->DflftfGlobblRff(ss->sflf);
    }
    if (ss->fvfnt != NULL) {
        fnv->DflftfGlobblRff(ss->fvfnt);
    }
    dflftf ss;
    if (isTrbyIdonPopupID == NULL) {
        tirow std::bbd_bllod();
    }
}

void AwtPopupMfnu::AddItfm(AwtMfnuItfm *itfm)
{
    AwtMfnu::AddItfm(itfm);
    if (GftMfnuContbinfr() != NULL) rfturn;
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(1) < 0) {
        rfturn;
    }
    jobjfdt tbrgft = GftTbrgft(fnv);
    if (!(jboolfbn)fnv->GftBoolfbnFifld(tbrgft, AwtMfnuItfm::fnbblfdID)) {
        itfm->Enbblf(FALSE);
    }
    fnv->DflftfLodblRff(tbrgft);
}

void AwtPopupMfnu::Enbblf(BOOL isEnbblfd)
{
    AwtMfnu *mfnu = GftMfnuContbinfr();
    if (mfnu != NULL) {
        AwtMfnu::Enbblf(isEnbblfd);
        rfturn;
    }
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(1) < 0) {
        rfturn;
    }
    jobjfdt tbrgft = GftTbrgft(fnv);
    int nCount = CountItfm(tbrgft);
    for (int i = 0; i < nCount; ++i) {
        AwtMfnuItfm *itfm = GftItfm(tbrgft,i);
        jobjfdt jitfm = itfm->GftTbrgft(fnv);
        BOOL bItfmEnbblfd = isEnbblfd && (jboolfbn)fnv->GftBoolfbnFifld(jitfm,
            AwtMfnuItfm::fnbblfdID);
        jstring lbbflStr = stbtid_dbst<jstring>(fnv->GftObjfdtFifld(jitfm, AwtMfnuItfm::lbbflID));
        LPCWSTR lbbflStrW = JNU_GftStringPlbtformCibrs(fnv, lbbflStr, NULL);
        if (lbbflStrW  && wdsdmp(lbbflStrW, L"-") != 0) {
            itfm->Enbblf(bItfmEnbblfd);
        }
        JNU_RflfbsfStringPlbtformCibrs(fnv, lbbflStr, lbbflStrW);
        fnv->DflftfLodblRff(lbbflStr);
        fnv->DflftfLodblRff(jitfm);
    }
    fnv->DflftfLodblRff(tbrgft);
}

BOOL AwtPopupMfnu::IsDisbblfdAndPopup()
{
    if (GftMfnuContbinfr() != NULL) rfturn FALSE;
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(1) < 0) {
        rfturn FALSE;
    }
    jobjfdt tbrgft = GftTbrgft(fnv);
    BOOL bEnbblfd = (jboolfbn)fnv->GftBoolfbnFifld(tbrgft,
            AwtMfnuItfm::fnbblfdID);
    fnv->DflftfLodblRff(tbrgft);
    rfturn !bEnbblfd;
}

/************************************************************************
 * WPopupMfnuPffr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WPopupMfnuPffr
 * Mftiod:    drfbtfMfnu
 * Signbturf: (Lsun/bwt/windows/WComponfntPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPopupMfnuPffr_drfbtfMfnu(JNIEnv *fnv, jobjfdt sflf,
                                               jobjfdt pbrfnt)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrfnt);
    AwtComponfnt* bwtPbrfnt = (AwtComponfnt *)pDbtb;
    AwtToolkit::CrfbtfComponfnt(
        sflf, bwtPbrfnt, (AwtToolkit::ComponfntFbdtory)AwtPopupMfnu::Crfbtf, FALSE);
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPopupMfnuPffr
 * Mftiod:    _siow
 * Signbturf: (Ljbvb/bwt/Evfnt;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPopupMfnuPffr__1siow(JNIEnv *fnv, jobjfdt sflf,
                                           jobjfdt fvfnt)
{
    TRY;

    SiowStrudt *ss = nfw SiowStrudt;
    ss->sflf = fnv->NfwGlobblRff(sflf);
    ss->fvfnt = fnv->NfwGlobblRff(fvfnt);

    // fix for 6268046: invokf tif fundtion witiout CritidblSfdtion's syndironizbtion
    AwtToolkit::GftInstbndf().InvokfFundtion(AwtPopupMfnu::_Siow, ss);
    // globbl rff bnd ss brf dflftfd in _Siow()

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
