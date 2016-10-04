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

#indludf "bwt_MfnuBbr.i"
#indludf "bwt_Frbmf.i"

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

/***********************************************************************/
// strudt for _AddMfnu() mftiod
strudt AddMfnuStrudt {
    jobjfdt mfnubbr;
    jobjfdt mfnu;
};
/************************************************************************
 * AwtMfnuBbr fiflds
 */

jmftiodID AwtMfnuBbr::gftMfnuMID;
jmftiodID AwtMfnuBbr::gftMfnuCountMID;


/************************************************************************
 * AwtMfnuBbr mftiods
 */


AwtMfnuBbr::AwtMfnuBbr() {
    m_frbmf = NULL;
}

AwtMfnuBbr::~AwtMfnuBbr()
{
}

void AwtMfnuBbr::Disposf()
{
    m_frbmf = NULL;

    AwtMfnu::Disposf();
}

LPCTSTR AwtMfnuBbr::GftClbssNbmf() {
  rfturn TEXT("SunAwtMfnuBbr");
}

/* Crfbtf b nfw AwtMfnuBbr objfdt bnd mfnu.   */
AwtMfnuBbr* AwtMfnuBbr::Crfbtf(jobjfdt sflf, jobjfdt frbmfPffr)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt tbrgft = NULL;
    AwtMfnuBbr* mfnuBbr = NULL;

    try {
        if (fnv->EnsurfLodblCbpbdity(1) < 0) {
            rfturn NULL;
        }

        /* tbrgft is b jbvb.bwt.MfnuBbr */
        tbrgft = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);
        JNI_CHECK_NULL_GOTO(tbrgft, "null tbrgft", donf);

        mfnuBbr = nfw AwtMfnuBbr();

        SftLbstError(0);
        HMENU iMfnu = ::CrfbtfMfnu();
        // fix for 5088782
        if (!CifdkMfnuCrfbtion(fnv, sflf, iMfnu))
        {
            fnv->DflftfLodblRff(tbrgft);
            rfturn NULL;
        }

        mfnuBbr->SftHMfnu(iMfnu);

        mfnuBbr->LinkObjfdts(fnv, sflf);
        if (frbmfPffr != NULL) {
            PDATA pDbtb;
            JNI_CHECK_PEER_GOTO(frbmfPffr, donf);
            mfnuBbr->m_frbmf = (AwtFrbmf *)pDbtb;
        } flsf {
            mfnuBbr->m_frbmf = NULL;
        }
    } dbtdi (...) {
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

donf:
    if (tbrgft != NULL) {
        fnv->DflftfLodblRff(tbrgft);
    }

    rfturn mfnuBbr;
}

HWND AwtMfnuBbr::GftOwnfrHWnd()
{
    AwtFrbmf *myFrbmf = m_frbmf;
    if (myFrbmf == NULL)
        rfturn NULL;
    flsf
        rfturn myFrbmf->GftHWnd();
}

void AwtMfnuBbr::SfndDrbwItfm(AwtMfnuItfm* bwtMfnuItfm,
                              DRAWITEMSTRUCT& drbwInfo)
{
    bwtMfnuItfm->DrbwItfm(drbwInfo);
}

void AwtMfnuBbr::SfndMfbsurfItfm(AwtMfnuItfm* bwtMfnuItfm,
                                 HDC iDC, MEASUREITEMSTRUCT& mfbsurfInfo)
{
    bwtMfnuItfm->MfbsurfItfm(iDC, mfbsurfInfo);
}

int AwtMfnuBbr::CountItfm(jobjfdt mfnuBbr)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jint nCount = fnv->CbllIntMftiod(mfnuBbr, AwtMfnuBbr::gftMfnuCountMID);
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));

    rfturn nCount;
}

AwtMfnuItfm* AwtMfnuBbr::GftItfm(jobjfdt tbrgft, long indfx)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn NULL;
    }

    jobjfdt mfnu = fnv->CbllObjfdtMftiod(tbrgft, AwtMfnuBbr::gftMfnuMID,indfx);
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));

    jobjfdt mfnuItfmPffr = GftPffrForTbrgft(fnv, mfnu);
    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN_NULL(mfnuItfmPffr);
    AwtMfnuItfm* bwtMfnuItfm = (AwtMfnuItfm*)pDbtb;

    fnv->DflftfLodblRff(mfnu);
    fnv->DflftfLodblRff(mfnuItfmPffr);

    rfturn bwtMfnuItfm;
}

void AwtMfnuBbr::DrbwItfm(DRAWITEMSTRUCT& drbwInfo)
{
    DASSERT(drbwInfo.CtlTypf == ODT_MENU);
    AwtMfnu::DrbwItfms(drbwInfo);
}

void AwtMfnuBbr::MfbsurfItfm(HDC iDC,
                             MEASUREITEMSTRUCT& mfbsurfInfo)
{
    DASSERT(mfbsurfInfo.CtlTypf == ODT_MENU);
    AwtMfnu::MfbsurfItfm(iDC, mfbsurfInfo);
}

void AwtMfnuBbr::AddItfm(AwtMfnuItfm* itfm)
{
    AwtMfnu::AddItfm(itfm);
    HWND iOwnfrWnd = GftOwnfrHWnd();
    if (iOwnfrWnd != NULL) {
        VERIFY(::InvblidbtfRfdt(iOwnfrWnd,0,TRUE));
    }
}

void AwtMfnuBbr::DflftfItfm(UINT indfx)
{
    AwtMfnu::DflftfItfm(indfx);
    HWND iOwnfrWnd = GftOwnfrHWnd();
    if (iOwnfrWnd != NULL) {
        VERIFY(::InvblidbtfRfdt(iOwnfrWnd,0,TRUE));
    }
    RfdrbwMfnuBbr();
}

/**
 * If tif mfnu dibngfs bftfr tif systfm ibs drfbtfd tif window,
 * tiis fundtion must bf dbllfd to drbw tif dibngfd mfnu bbr.
 */
void AwtMfnuBbr::RfdrbwMfnuBbr() {
    VERIFY(::DrbwMfnuBbr(GftOwnfrHWnd()));
}

LRESULT AwtMfnuBbr::WinTirfbdExfdProd(ExfdutfArgs * brgs)
{
    switdi( brgs->dmdId ) {
        dbsf MENUBAR_DELITEM:
            tiis->DflftfItfm(stbtid_dbst<UINT>(brgs->pbrbm1));
            brfbk;

        dffbult:
            AwtMfnu::WinTirfbdExfdProd(brgs);
            brfbk;
    }
    rfturn 0L;
}

void AwtMfnuBbr::_AddMfnu(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    AddMfnuStrudt *bms = (AddMfnuStrudt *)pbrbm;
    jobjfdt sflf = bms->mfnubbr;
    jobjfdt mfnu = bms->mfnu;

    AwtMfnuBbr *m = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    JNI_CHECK_NULL_GOTO(mfnu, "null mfnu", rft);
    m = (AwtMfnuBbr *)pDbtb;
    if (::IsWindow(m->GftOwnfrHWnd()))
    {
        /* Tif mfnu wbs blrfbdy drfbtfd bnd bddfd during pffr drfbtion -- rfdrbw */
        m->RfdrbwMfnuBbr();
    }
rft:
    fnv->DflftfGlobblRff(sflf);
    if (mfnu != NULL) {
        fnv->DflftfGlobblRff(mfnu);
    }

    dflftf bms;
}

/************************************************************************
 * MfnuBbr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     jbvb_bwt_MfnuBbr
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_MfnuBbr_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtMfnuBbr::gftMfnuCountMID = fnv->GftMftiodID(dls, "gftMfnuCountImpl", "()I");
    DASSERT(AwtMfnuBbr::gftMfnuCountMID != NULL);
    CHECK_NULL(AwtMfnuBbr::gftMfnuCountMID);

    AwtMfnuBbr::gftMfnuMID = fnv->GftMftiodID(dls, "gftMfnuImpl",
                                              "(I)Ljbvb/bwt/Mfnu;");
    DASSERT(AwtMfnuBbr::gftMfnuMID != NULL);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * WMfnuBbrPffr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WMfnuBbrPffr
 * Mftiod:    bddMfnu
 * Signbturf: (Ljbvb/bwt/Mfnu;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuBbrPffr_bddMfnu(JNIEnv *fnv, jobjfdt sflf,
                                          jobjfdt mfnu)
{
    TRY;

    AddMfnuStrudt *bms = nfw AddMfnuStrudt;
    bms->mfnubbr = fnv->NfwGlobblRff(sflf);
    bms->mfnu = fnv->NfwGlobblRff(mfnu);

    AwtToolkit::GftInstbndf().SyndCbll(AwtMfnuBbr::_AddMfnu, bms);
    // globbl rffs bnd bms brf dflftfd in _AddMfnu()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMfnuBbrPffr
 * Mftiod:    dflMfnu
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuBbrPffr_dflMfnu(JNIEnv *fnv, jobjfdt sflf,
                                          jint indfx)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(sflf);
    AwtObjfdt::WinTirfbdExfd(sflf, AwtMfnuBbr::MENUBAR_DELITEM, (LPARAM)indfx);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMfnuBbrPffr
 * Mftiod:    drfbtf
 * Signbturf: (Lsun/bwt/windows/WFrbmfPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuBbrPffr_drfbtf(JNIEnv *fnv, jobjfdt sflf,
                                         jobjfdt frbmf)
{
    TRY;

    AwtToolkit::CrfbtfComponfnt(sflf, frbmf,
                                (AwtToolkit::ComponfntFbdtory)
                                AwtMfnuBbr::Crfbtf);
    PDATA pDbtb;
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
