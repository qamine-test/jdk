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

#indludf "bwt_Toolkit.i"
#indludf "bwt_Cbnvbs.i"
#indludf "bwt_Win32GrbpiidsConfig.i"
#indludf "bwt_Window.i"

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

// Strudt for _SftErbsfBbdkground() mftiod
strudt SftErbsfBbdkgroundStrudt {
    jobjfdt dbnvbs;
    jboolfbn doErbsf;
    jboolfbn doErbsfOnRfsizf;
};

/************************************************************************
 * AwtCbnvbs mftiods
 */

AwtCbnvbs::AwtCbnvbs() {
    m_frbsfBbdkground = JNI_TRUE;
    m_frbsfBbdkgroundOnRfsizf = JNI_TRUE;
}

AwtCbnvbs::~AwtCbnvbs() {
}

LPCTSTR AwtCbnvbs::GftClbssNbmf() {
    rfturn TEXT("SunAwtCbnvbs");
}

/*
 * Crfbtf b nfw AwtCbnvbs objfdt bnd window.
 */
AwtCbnvbs* AwtCbnvbs::Crfbtf(jobjfdt sflf, jobjfdt iPbrfnt)
{
    TRY;
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt tbrgft = NULL;
    jobjfdt grbpiidsConfig = NULL;
    jdlbss dbnvbsClbss = NULL;
    jdlbss win32dls = NULL;

    AwtCbnvbs *dbnvbs = NULL;

    try {
        if (fnv->EnsurfLodblCbpbdity(1) < 0) {
            rfturn NULL;
        }

        AwtComponfnt* pbrfnt;

        JNI_CHECK_NULL_GOTO(iPbrfnt, "null iPbrfnt", donf);

        pbrfnt = (AwtComponfnt*)JNI_GET_PDATA(iPbrfnt);
        JNI_CHECK_NULL_GOTO(pbrfnt, "null pbrfnt", donf);

        tbrgft = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);
        JNI_CHECK_NULL_GOTO(tbrgft, "null tbrgft", donf);

        dbnvbs = nfw AwtCbnvbs();

        {
            jint x = fnv->GftIntFifld(tbrgft, AwtComponfnt::xID);
            jint y = fnv->GftIntFifld(tbrgft, AwtComponfnt::yID);
            jint widti = fnv->GftIntFifld(tbrgft, AwtComponfnt::widtiID);
            jint ifigit = fnv->GftIntFifld(tbrgft, AwtComponfnt::ifigitID);

            dbnvbs->CrfbtfHWnd(fnv, L"",
                               WS_CHILD | WS_CLIPCHILDREN | WS_CLIPSIBLINGS, 0,
                               x, y, widti, ifigit,
                               pbrfnt->GftHWnd(),
                               NULL,
                               ::GftSysColor(COLOR_WINDOWTEXT),
                               ::GftSysColor(COLOR_WINDOW),
                               sflf);

        // Sft tif pixfl formbt of tif HWND if b GrbpiidsConfigurbtion
        // wbs providfd to tif Cbnvbs donstrudtor.

        dbnvbsClbss = fnv->FindClbss("jbvb/bwt/Cbnvbs");
        DASSERT(dbnvbsClbss != NULL);
        if (!dbnvbsClbss) {
            tirow std::bbd_bllod();
        }

        if ( fnv->IsInstbndfOf( tbrgft, dbnvbsClbss ) ) {

            // Gft GrbpiidsConfig from our tbrgft
            grbpiidsConfig = fnv->GftObjfdtFifld(tbrgft,
                AwtComponfnt::grbpiidsConfigID);
            if (grbpiidsConfig != NULL) {

                win32dls = fnv->FindClbss("sun/bwt/Win32GrbpiidsConfig");
                DASSERT (win32dls != NULL);
                if (!win32dls) {
                    tirow std::bbd_bllod();
                }

                if ( fnv->IsInstbndfOf( grbpiidsConfig, win32dls ) ) {
                    // Gft tif visubl ID mfmbfr from our GC
                    jint visubl = fnv->GftIntFifld(grbpiidsConfig,
                          AwtWin32GrbpiidsConfig::win32GCVisublID);
                    if (visubl > 0) {
                        HDC idd = ::GftDC(dbnvbs->m_iwnd);
                        // Sft our pixfl formbt
                        PIXELFORMATDESCRIPTOR pfd;
                        BOOL rft = ::SftPixflFormbt(idd, (int)visubl, &pfd);
                        ::RflfbsfDC(dbnvbs->m_iwnd, idd);
                        //Sindf b GrbpiidsConfigurbtion wbs spfdififd, wf siould
                        //tirow bn fxdfption if tif PixflFormbt douldn't bf sft.
                        if (rft == FALSE) {
                            DASSERT(!sbff_ExdfptionOddurrfd(fnv));
                            jdlbss fxdCls = fnv->FindClbss(
                             "jbvb/lbng/RuntimfExdfption");
                            DASSERT(fxdCls);
                            fnv->ExdfptionClfbr();
                            fnv->TirowNfw(fxdCls,
                             "\nUnbblf to sft Pixfl formbt on Cbnvbs");
                            fnv->DflftfLodblRff(fxdCls);
                        }
                    }
                }
            }
        }
    }
    } dbtdi (...) {
        fnv->DflftfLodblRff(tbrgft);
        fnv->DflftfLodblRff(grbpiidsConfig);
        fnv->DflftfLodblRff(dbnvbsClbss);
        fnv->DflftfLodblRff(win32dls);

        fnv->DflftfGlobblRff(sflf);
        fnv->DflftfGlobblRff(iPbrfnt);
        tirow;
    }

donf:
    fnv->DflftfLodblRff(tbrgft);
    fnv->DflftfLodblRff(grbpiidsConfig);
    fnv->DflftfLodblRff(dbnvbsClbss);
    fnv->DflftfLodblRff(win32dls);
    rfturn dbnvbs;
    CATCH_BAD_ALLOC_RET(0);
}

MsgRouting AwtCbnvbs::WmErbsfBkgnd(HDC iDC, BOOL& didErbsf)
{
    if (m_frbsfBbdkground ||
        (m_frbsfBbdkgroundOnRfsizf && AwtWindow::IsRfsizing()))
    {
       RECT     rd;
       ::GftClipBox(iDC, &rd);
       ::FillRfdt(iDC, &rd, tiis->GftBbdkgroundBrusi());
    }

    didErbsf = TRUE;
    rfturn mrConsumf;
}

/*
 * Tiis routinf is duplidbtfd in AwtWindow.
 */
MsgRouting AwtCbnvbs::WmPbint(HDC)
{
    PbintUpdbtfRgn(NULL);
    rfturn mrConsumf;
}

MsgRouting AwtCbnvbs::HbndlfEvfnt(MSG *msg, BOOL syntiftid)
{
    if (IsFodusingMousfMfssbgf(msg)) {
        dflftf msg;
        rfturn mrConsumf;
    }
    rfturn AwtComponfnt::HbndlfEvfnt(msg, syntiftid);
}

void AwtCbnvbs::_SftErbsfBbdkground(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftErbsfBbdkgroundStrudt *sfbs = (SftErbsfBbdkgroundStrudt *)pbrbm;
    jobjfdt dbnvbs = sfbs->dbnvbs;
    jboolfbn doErbsf = sfbs->doErbsf;
    jboolfbn doErbsfOnRfsizf = sfbs->doErbsfOnRfsizf;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(dbnvbs, rft);

    AwtCbnvbs *d = (AwtCbnvbs*)pDbtb;
    d->m_frbsfBbdkground = doErbsf;
    d->m_frbsfBbdkgroundOnRfsizf = doErbsfOnRfsizf;

rft:
    fnv->DflftfGlobblRff(dbnvbs);
    dflftf sfbs;
}


/************************************************************************
 * WCbnvbsPffr nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WCbnvbsPffr_drfbtf(JNIEnv *fnv, jobjfdt sflf,
                                        jobjfdt pbrfnt)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrfnt);
    AwtToolkit::CrfbtfComponfnt(sflf, pbrfnt,
                                (AwtToolkit::ComponfntFbdtory)
                                AwtCbnvbs::Crfbtf);
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WCbnvbsPffr
 * Mftiod:    sftNbtivfBbdkgroundErbsf
 * Signbturf: (Z)V
 */
 JNIEXPORT void JNICALL
 Jbvb_sun_bwt_windows_WCbnvbsPffr_sftNbtivfBbdkgroundErbsf(JNIEnv *fnv,
                                                           jobjfdt sflf,
                                                           jboolfbn doErbsf,
                                                           jboolfbn doErbsfOnRfsizf)
{
    TRY;

    SftErbsfBbdkgroundStrudt *sfbs = nfw SftErbsfBbdkgroundStrudt;
    sfbs->dbnvbs = fnv->NfwGlobblRff(sflf);
    sfbs->doErbsf = doErbsf;
    sfbs->doErbsfOnRfsizf = doErbsfOnRfsizf;

    AwtToolkit::GftInstbndf().SyndCbll(AwtCbnvbs::_SftErbsfBbdkground, sfbs);
    // sfbs bnd globbl rff brf dflftfd in _SftErbsfBbdkground()

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
