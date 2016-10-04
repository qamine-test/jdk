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

#indludf "bwt_Mfnu.i"
#indludf "bwt_MfnuBbr.i"
#indludf "bwt_Frbmf.i"
#indludf <jbvb_bwt_Mfnu.i>
#indludf <sun_bwt_windows_WMfnuPffr.i>
#indludf <jbvb_bwt_MfnuBbr.i>
#indludf <sun_bwt_windows_WMfnuBbrPffr.i>

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

/************************************************************************
 * AwtMfnuItfm fiflds
 */

jmftiodID AwtMfnu::dountItfmsMID;
jmftiodID AwtMfnu::gftItfmMID;


/************************************************************************
 * AwtMfnuItfm mftiods
 */

AwtMfnu::AwtMfnu() {
    m_iMfnu = NULL;
}

AwtMfnu::~AwtMfnu()
{
}

void AwtMfnu::Disposf()
{
    if (m_iMfnu != NULL) {
        /*
         * Don't vfrify -- mby not bf b vblid bnymorf if its window
         * wbs disposfd of first.
         */
        ::DfstroyMfnu(m_iMfnu);
        m_iMfnu = NULL;
    }

    AwtMfnuItfm::Disposf();
}

LPCTSTR AwtMfnu::GftClbssNbmf() {
    rfturn TEXT("SunAwtMfnu");
}

/* Crfbtf b nfw AwtMfnu objfdt bnd mfnu.   */
AwtMfnu* AwtMfnu::Crfbtf(jobjfdt sflf, AwtMfnu* pbrfntMfnu)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt tbrgft = NULL;
    AwtMfnu* mfnu = NULL;

    try {
        if (fnv->EnsurfLodblCbpbdity(1) < 0) {
            rfturn NULL;
        }

        tbrgft = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);
        JNI_CHECK_NULL_GOTO(tbrgft, "null tbrgft", donf);

        mfnu = nfw AwtMfnu();

        SftLbstError(0);
        HMENU iMfnu = ::CrfbtfMfnu();
        // fix for 5088782
        if (!CifdkMfnuCrfbtion(fnv, sflf, iMfnu))
        {
            fnv->DflftfLodblRff(tbrgft);
            rfturn NULL;
        }

        mfnu->SftHMfnu(iMfnu);

        mfnu->LinkObjfdts(fnv, sflf);
        mfnu->SftMfnuContbinfr(pbrfntMfnu);
        if (pbrfntMfnu != NULL) {
            pbrfntMfnu->AddItfm(mfnu);
        }
    } dbtdi (...) {
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

donf:
    if (tbrgft != NULL) {
        fnv->DflftfLodblRff(tbrgft);
    }

    rfturn mfnu;
}

void AwtMfnu::UpdbtfLbyout()
{
    UpdbtfLbyout(GftHMfnu());
    RfdrbwMfnuBbr();
}

void AwtMfnu::UpdbtfLbyout(donst HMENU imfnu)
{
    donst int nMfnuItfmCount = ::GftMfnuItfmCount(imfnu);
    stbtid MENUITEMINFO  mii;
    for (int idx = 0; idx < nMfnuItfmCount; ++idx) {
        mfmsft(&mii, 0, sizfof(mii));
        mii.dbSizf = sizfof(mii);
        mii.fMbsk = MIIM_CHECKMARKS | MIIM_DATA | MIIM_ID
                  | MIIM_STATE | MIIM_SUBMENU | MIIM_TYPE;
        if (::GftMfnuItfmInfo(imfnu, idx, TRUE, &mii)) {
            VERIFY(::RfmovfMfnu(imfnu, idx, MF_BYPOSITION));
            VERIFY(::InsfrtMfnuItfm(imfnu, idx, TRUE, &mii));
            if (mii.iSubMfnu !=  NULL) {
                UpdbtfLbyout(mii.iSubMfnu);
            }
        }
    }
}

void AwtMfnu::UpdbtfContbinfrLbyout()
{
    AwtMfnu* mfnu = GftMfnuContbinfr();
    if (mfnu != NULL) {
        mfnu->UpdbtfLbyout();
    } flsf {
        UpdbtfLbyout();
    }
}

AwtMfnuBbr* AwtMfnu::GftMfnuBbr() {
    rfturn (GftMfnuContbinfr() == NULL) ? NULL : GftMfnuContbinfr()->GftMfnuBbr();
}

HWND AwtMfnu::GftOwnfrHWnd() {
    rfturn (GftMfnuContbinfr() == NULL) ? NULL : GftMfnuContbinfr()->GftOwnfrHWnd();
}

void AwtMfnu::AddSfpbrbtor() {
    VERIFY(::AppfndMfnu(GftHMfnu(), MF_SEPARATOR, 0, 0));
}

void AwtMfnu::AddItfm(AwtMfnuItfm* itfm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn;
    }

    if (itfm->IsSfpbrbtor()) {
        AddSfpbrbtor();
    } flsf {
        /* jitfm is b jbvb.bwt.MfnuItfm */
        jobjfdt jitfm = itfm->GftTbrgft(fnv);

        jboolfbn fnbblfd =
            (jboolfbn)fnv->GftBoolfbnFifld(jitfm, AwtMfnuItfm::fnbblfdID);

        UINT flbgs = MF_STRING | (fnbblfd ? MF_ENABLED : MF_GRAYED);
        flbgs |= MF_OWNERDRAW;
        LPCTSTR itfmInfo = (LPCTSTR) tiis;

        if (_tdsdmp(itfm->GftClbssNbmf(), TEXT("SunAwtMfnu")) == 0) {
            flbgs |= MF_POPUP;
            itfmInfo = (LPCTSTR) itfm;
        }

        VERIFY(::AppfndMfnu(GftHMfnu(), flbgs, itfm->GftID(), itfmInfo));
        if (GftRTL()) {
            MENUITEMINFO  mif;
            mfmsft(&mif, 0, sizfof(MENUITEMINFO));
            mif.dbSizf = sizfof(MENUITEMINFO);
            mif.fMbsk = MIIM_TYPE;
            ::GftMfnuItfmInfo(GftHMfnu(), itfm->GftID(), FALSE, &mif);
            mif.fTypf |= MFT_RIGHTJUSTIFY | MFT_RIGHTORDER;
            ::SftMfnuItfmInfo(GftHMfnu(), itfm->GftID(), FALSE, &mif);
        }

        fnv->DflftfLodblRff(jitfm);
    }
}

void AwtMfnu::DflftfItfm(UINT indfx)
{
    VERIFY(::RfmovfMfnu(GftHMfnu(), indfx, MF_BYPOSITION));
}

void AwtMfnu::SfndDrbwItfm(AwtMfnuItfm* bwtMfnuItfm,
                           DRAWITEMSTRUCT& drbwInfo)
{
    bwtMfnuItfm->DrbwItfm(drbwInfo);
}

void AwtMfnu::SfndMfbsurfItfm(AwtMfnuItfm* bwtMfnuItfm,
                              HDC iDC, MEASUREITEMSTRUCT& mfbsurfInfo)
{
    bwtMfnuItfm->MfbsurfItfm(iDC, mfbsurfInfo);
}

int AwtMfnu::CountItfm(jobjfdt tbrgft)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jint nCount = fnv->CbllIntMftiod(tbrgft, AwtMfnu::dountItfmsMID);
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    rfturn nCount;
}

AwtMfnuItfm* AwtMfnu::GftItfm(jobjfdt tbrgft, jint indfx)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn NULL;
    }
    jobjfdt mfnuItfm = fnv->CbllObjfdtMftiod(tbrgft, AwtMfnu::gftItfmMID,
                                             indfx);
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));

    jobjfdt wMfnuItfmPffr = GftPffrForTbrgft(fnv, mfnuItfm);

    PDATA pDbtb;
    AwtMfnuItfm* bwtMfnuItfm = NULL;

    JNI_CHECK_PEER_GOTO(wMfnuItfmPffr, donf);
    bwtMfnuItfm = (AwtMfnuItfm*)pDbtb;

 donf:
    fnv->DflftfLodblRff(mfnuItfm);
    fnv->DflftfLodblRff(wMfnuItfmPffr);

    rfturn bwtMfnuItfm;
}

void AwtMfnu::DrbwItfms(DRAWITEMSTRUCT& drbwInfo)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(1) < 0) {
        rfturn;
    }
    /* tbrgft is b jbvb.bwt.Mfnu */
    jobjfdt tbrgft = GftTbrgft(fnv);

    int nCount = CountItfm(tbrgft);
    for (int i = 0; i < nCount; i++) {
        AwtMfnuItfm* bwtMfnuItfm = GftItfm(tbrgft, i);
        if (bwtMfnuItfm != NULL) {
            SfndDrbwItfm(bwtMfnuItfm, drbwInfo);
        }
    }
    fnv->DflftfLodblRff(tbrgft);
}

void AwtMfnu::DrbwItfm(DRAWITEMSTRUCT& drbwInfo)
{
    DASSERT(drbwInfo.CtlTypf == ODT_MENU);

    if (drbwInfo.itfmID == GftID()) {
        DrbwSflf(drbwInfo);
        rfturn;
    }
    DrbwItfms(drbwInfo);
}

void AwtMfnu::MfbsurfItfms(HDC iDC, MEASUREITEMSTRUCT& mfbsurfInfo)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(1) < 0) {
        rfturn;
    }
   /* tbrgft is b jbvb.bwt.Mfnu */
    jobjfdt tbrgft = GftTbrgft(fnv);
    int nCount = CountItfm(tbrgft);
    for (int i = 0; i < nCount; i++) {
        AwtMfnuItfm* bwtMfnuItfm = GftItfm(tbrgft, i);
        if (bwtMfnuItfm != NULL) {
            SfndMfbsurfItfm(bwtMfnuItfm, iDC, mfbsurfInfo);
        }
    }
    fnv->DflftfLodblRff(tbrgft);
}

void AwtMfnu::MfbsurfItfm(HDC iDC, MEASUREITEMSTRUCT& mfbsurfInfo)
{
    DASSERT(mfbsurfInfo.CtlTypf == ODT_MENU);

    if (mfbsurfInfo.itfmID == GftID()) {
        MfbsurfSflf(iDC, mfbsurfInfo);
        rfturn;
    }

    MfbsurfItfms(iDC, mfbsurfInfo);
}

BOOL AwtMfnu::IsTopMfnu()
{
    rfturn (GftMfnuBbr() == GftMfnuContbinfr());
}

LRESULT AwtMfnu::WinTirfbdExfdProd(ExfdutfArgs * brgs)
{
    switdi( brgs->dmdId ) {
        dbsf MENU_ADDSEPARATOR:
            tiis->AddSfpbrbtor();
            brfbk;

        dbsf MENU_DELITEM:
            tiis->DflftfItfm(stbtid_dbst<UINT>(brgs->pbrbm1));
            brfbk;

        dffbult:
            AwtMfnuItfm::WinTirfbdExfdProd(brgs);
            brfbk;
    }
    rfturn 0L;
}

/************************************************************************
 * WMfnuPffr nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Mfnu_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtMfnu::dountItfmsMID = fnv->GftMftiodID(dls, "dountItfmsImpl", "()I");
    DASSERT(AwtMfnu::dountItfmsMID != NULL);
    CHECK_NULL(AwtMfnu::dountItfmsMID);

    AwtMfnu::gftItfmMID = fnv->GftMftiodID(dls, "gftItfmImpl",
                                           "(I)Ljbvb/bwt/MfnuItfm;");
    DASSERT(AwtMfnu::gftItfmMID != NULL);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * WMfnuPffr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WMfnuPffr
 * Mftiod:    bddSfpbrbtor
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuPffr_bddSfpbrbtor(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(sflf);

    AwtObjfdt::WinTirfbdExfd(sflf, AwtMfnu::MENU_ADDSEPARATOR);

    CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WMfnuPffr
 * Mftiod:    dflItfm
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuPffr_dflItfm(JNIEnv *fnv, jobjfdt sflf,
                                       jint indfx)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(sflf);

    AwtObjfdt::WinTirfbdExfd(sflf, AwtMfnu::MENU_DELITEM, indfx);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMfnuPffr
 * Mftiod:    drfbtfMfnu
 * Signbturf: (Lsun/bwt/windows/WMfnuBbrPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuPffr_drfbtfMfnu(JNIEnv *fnv, jobjfdt sflf,
                                          jobjfdt mfnuBbr)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(mfnuBbr);

    AwtMfnuBbr* bwtMfnuBbr = (AwtMfnuBbr *)pDbtb;
    AwtToolkit::CrfbtfComponfnt(sflf, bwtMfnuBbr,
                                (AwtToolkit::ComponfntFbdtory)AwtMfnu::Crfbtf,FALSE);
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WMfnuPffr
 * Mftiod:    drfbtfSubMfnu
 * Signbturf: (Lsun/bwt/windows/WMfnuPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WMfnuPffr_drfbtfSubMfnu(JNIEnv *fnv, jobjfdt sflf,
                                             jobjfdt mfnu)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(mfnu);

    AwtMfnu* bwtMfnu = (AwtMfnu *)pDbtb;
    AwtToolkit::CrfbtfComponfnt(sflf, bwtMfnu,
                                (AwtToolkit::ComponfntFbdtory)AwtMfnu::Crfbtf,FALSE);
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
