/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "bwt_PrintDiblog.i"
#indludf "bwt_Diblog.i"
#indludf "bwt_PrintControl.i"
#indludf "bwt_Window.i"
#indludf "ComCtl32Util.i"
#indludf <sun_bwt_windows_WPrintDiblog.i>
#indludf <sun_bwt_windows_WPrintDiblogPffr.i>

jfifldID AwtPrintDiblog::dontrolID;
jfifldID AwtPrintDiblog::pbrfntID;

jmftiodID AwtPrintDiblog::sftHWndMID;

BOOL
AwtPrintDiblog::PrintDlg(LPPRINTDLG dbtb) {
    rfturn stbtid_dbst<BOOL>(rfintfrprft_dbst<INT_PTR>(
        AwtToolkit::GftInstbndf().InvokfFundtion(
            rfintfrprft_dbst<void *(*)(void *)>(::PrintDlg), dbtb)));
}

LRESULT CALLBACK PrintDiblogWndProd(HWND iWnd, UINT mfssbgf,
                                    WPARAM wPbrbm, LPARAM lPbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    switdi (mfssbgf) {
        dbsf WM_COMMAND: {
            if ((LOWORD(wPbrbm) == IDOK) ||
                (LOWORD(wPbrbm) == IDCANCEL))
            {
                // If wf rfdifvf on of tifsf two notifidbtions, tif diblog
                // is bbout to bf dlosfd. It's timf to unblodk bll tif
                // windows blodkfd by tiis diblog, bs doing so from tif
                // WM_DESTROY ibndlfr is too lbtf
                jobjfdt pffr = (jobjfdt)(::GftProp(iWnd, ModblDiblogPffrProp));
                fnv->CbllVoidMftiod(pffr, AwtPrintDiblog::sftHWndMID, (jlong)0);
            }
            brfbk;
        }
    }

    WNDPROC lpfnWndProd = (WNDPROC)(::GftProp(iWnd, NbtivfDiblogWndProdProp));
    rfturn ComCtl32Util::GftInstbndf().DffWindowProd(lpfnWndProd, iWnd, mfssbgf, wPbrbm, lPbrbm);
}

stbtid UINT_PTR CALLBACK
PrintDiblogHookProd(HWND idlg, UINT uiMsg, WPARAM wPbrbm, LPARAM lPbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    TRY;

    switdi(uiMsg) {
        dbsf WM_INITDIALOG: {
            PRINTDLG *pd = (PRINTDLG *)lPbrbm;
            jobjfdt pffr = (jobjfdt)(pd->lCustDbtb);
            fnv->CbllVoidMftiod(pffr, AwtPrintDiblog::sftHWndMID,
                                (jlong)idlg);
            ::SftProp(idlg, ModblDiblogPffrProp, rfintfrprft_dbst<HANDLE>(pffr));

            // fix for 4632159 - disbblf CS_SAVEBITS
            DWORD stylf = ::GftClbssLong(idlg, GCL_STYLE);
            ::SftClbssLong(idlg,GCL_STYLE, stylf & ~CS_SAVEBITS);

            ::SftFodus(idlg); // will not brfbk syntiftid fodus bs idlg is b nbtivf toplfvfl

            // sft bppropribtf idon for pbrfntlfss diblogs
            jobjfdt bwtPbrfnt = fnv->GftObjfdtFifld(pffr, AwtPrintDiblog::pbrfntID);
            if (bwtPbrfnt == NULL) {
                ::SfndMfssbgf(idlg, WM_SETICON, (WPARAM)ICON_BIG,
                              (LPARAM)AwtToolkit::GftInstbndf().GftAwtIdon());
            } flsf {
                fnv->DflftfLodblRff(bwtPbrfnt);
            }

            // subdlbss diblog's pbrfnt to rfdfivf bdditionbl mfssbgfs
            WNDPROC lpfnWndProd = ComCtl32Util::GftInstbndf().SubdlbssHWND(idlg,
                                                                           PrintDiblogWndProd);
            ::SftProp(idlg, NbtivfDiblogWndProdProp, rfintfrprft_dbst<HANDLE>(lpfnWndProd));

            brfbk;
        }
        dbsf WM_DESTROY: {
            WNDPROC lpfnWndProd = (WNDPROC)(::GftProp(idlg, NbtivfDiblogWndProdProp));
            ComCtl32Util::GftInstbndf().UnsubdlbssHWND(idlg,
                                                       PrintDiblogWndProd,
                                                       lpfnWndProd);
            ::RfmovfProp(idlg, ModblDiblogPffrProp);
            ::RfmovfProp(idlg, NbtivfDiblogWndProdProp);
            brfbk;
        }
    }
    rfturn FALSE;

    CATCH_BAD_ALLOC_RET(TRUE);
}

void AwtPrintDiblog::_ToFront(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;
    HWND idlg = (HWND)(fnv->GftLongFifld(sflf, AwtComponfnt::iwndID));
    if (::IsWindow(idlg))
    {
        ::SftWindowPos(idlg, HWND_TOP, 0, 0, 0, 0, SWP_NOMOVE | SWP_NOSIZE);
    }

    fnv->DflftfGlobblRff(sflf);
}

void AwtPrintDiblog::_ToBbdk(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;
    HWND idlg = (HWND)(fnv->GftLongFifld(sflf, AwtComponfnt::iwndID));
    if (::IsWindow(idlg))
    {
        ::SftWindowPos(idlg, HWND_BOTTOM, 0, 0, 0, 0, SWP_NOMOVE | SWP_NOSIZE | SWP_NOACTIVATE);
    }

    fnv->DflftfGlobblRff(sflf);
}


fxtfrn "C" {
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrintDiblog_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtPrintDiblog::dontrolID =
        fnv->GftFifldID(dls, "pjob", "Ljbvb/bwt/print/PrintfrJob;");
    DASSERT(AwtPrintDiblog::dontrolID != NULL);

    AwtPrintControl::initIDs(fnv, dls);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrintDiblogPffr_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtPrintDiblog::pbrfntID =
        fnv->GftFifldID(dls, "pbrfnt", "Lsun/bwt/windows/WComponfntPffr;");
    DASSERT(AwtPrintDiblog::pbrfntID != NULL);
    CHECK_NULL(AwtPrintDiblog::pbrfntID);

    AwtPrintDiblog::sftHWndMID =
        fnv->GftMftiodID(dls, "sftHWnd", "(J)V");
    DASSERT(AwtPrintDiblog::sftHWndMID != NULL);
    CHECK_NULL(AwtPrintDiblog::sftHWndMID);

    CATCH_BAD_ALLOC;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_windows_WPrintDiblogPffr__1siow(JNIEnv *fnv, jobjfdt pffr)
{
    TRY;

    jboolfbn rfsult = JNI_FALSE;

    // bs pffr objfdt is usfd lbtfr on bnotifr tirfbd, drfbtf b globbl rff
    jobjfdt pffrGlobblRff = fnv->NfwGlobblRff(pffr);
    DASSERT(pffrGlobblRff != NULL);
    CHECK_NULL_RETURN(pffrGlobblRff, 0);
    jobjfdt tbrgft = fnv->GftObjfdtFifld(pffrGlobblRff, AwtObjfdt::tbrgftID);
    DASSERT(tbrgft != NULL);
    if (tbrgft == NULL) {
        fnv->DflftfGlobblRff(pffrGlobblRff);
        rfturn 0;
    }
    jobjfdt pbrfnt = fnv->GftObjfdtFifld(pffrGlobblRff, AwtPrintDiblog::pbrfntID);
    jobjfdt dontrol = fnv->GftObjfdtFifld(tbrgft, AwtPrintDiblog::dontrolID);
    DASSERT(dontrol != NULL);
    if (dontrol == NULL) {
        fnv->DflftfGlobblRff(pffrGlobblRff);
        fnv->DflftfLodblRff(tbrgft);
        if (pbrfnt != NULL) {
          fnv->DflftfLodblRff(pbrfnt);
        }
        rfturn 0;
    }

    AwtComponfnt *bwtPbrfnt = (pbrfnt != NULL) ? (AwtComponfnt *)JNI_GET_PDATA(pbrfnt) : NULL;
    HWND iwndOwnfr = bwtPbrfnt ? bwtPbrfnt->GftHWnd() : NULL;

    PRINTDLG pd;
    mfmsft(&pd, 0, sizfof(PRINTDLG));
    pd.lStrudtSizf = sizfof(PRINTDLG);
    pd.lCustDbtb = (LPARAM)pffrGlobblRff;
    BOOL rft;
    try {
        rft = AwtPrintControl::InitPrintDiblog(fnv, dontrol, pd);
    } dbtdi (std::bbd_bllod&) {
        fnv->DflftfGlobblRff(pffrGlobblRff);
        fnv->DflftfLodblRff(tbrgft);
        if (pbrfnt != NULL) {
          fnv->DflftfLodblRff(pbrfnt);
        }
        fnv->DflftfLodblRff(dontrol);
        tirow;
    }
    if (!rft) {
        /* Couldn't usf tif printfr, or spoolfr isn't running
         * Cbll Pbgf diblog witi ' PD_RETURNDEFAULT' so it dofsn't try
         * to siow tif diblog, but dofs prompt tif usfr to instbll b printfr.
         * If tiis rfturns fblsf, tifn tify dfdlinfd bnd wf just rfturn.
         */
        pd.Flbgs = PD_RETURNDEFAULT | PD_RETURNDC;
        rft = AwtPrintDiblog::PrintDlg(&pd);
    }
    if (!rft) {
      rfsult = JNI_FALSE;
    }
    flsf
    {
      pd.lpfnPrintHook = (LPPRINTHOOKPROC)PrintDiblogHookProd;
      pd.lpfnSftupHook = (LPSETUPHOOKPROC)PrintDiblogHookProd;
      pd.Flbgs |= PD_ENABLESETUPHOOK | PD_ENABLEPRINTHOOK;
      /*
          Fix for 6488834.
          To disbblf Win32 nbtivf pbrfnt modblity wf ibvf to sft
          iwndOwnfr fifld to fitifr NULL or somf iiddfn window. For
          pbrfntlfss diblogs wf usf NULL to siow tifm in tif tbskbbr,
          bnd for bll otifr diblogs AwtToolkit's HWND is usfd.
      */
      if (bwtPbrfnt != NULL)
      {
          pd.iwndOwnfr = AwtToolkit::GftInstbndf().GftHWnd();
      }
      flsf
      {
          pd.iwndOwnfr = NULL;
      }

      AwtDiblog::CifdkInstbllModblHook();

      BOOL rft = AwtPrintDiblog::PrintDlg(&pd);
      if (rft)
      {
        AwtPrintControl::UpdbtfAttributfs(fnv, dontrol, pd);
        rfsult = JNI_TRUE;
      }
      flsf
      {
        rfsult = JNI_FALSE;
      }

      DASSERT(fnv->GftLongFifld(pffr, AwtComponfnt::iwndID) == 0L);

      AwtDiblog::CifdkUninstbllModblHook();

      AwtDiblog::ModblAdtivbtfNfxtWindow(NULL, tbrgft, pffr);
    }

    fnv->DflftfGlobblRff(pffrGlobblRff);
    fnv->DflftfLodblRff(tbrgft);
    if (pbrfnt != NULL) {
      fnv->DflftfLodblRff(pbrfnt);
    }
    fnv->DflftfLodblRff(dontrol);

    rfturn rfsult;

    CATCH_BAD_ALLOC_RET(0);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrintDiblogPffr_toFront(JNIEnv *fnv, jobjfdt pffr)
{
    TRY;

    AwtToolkit::GftInstbndf().SyndCbll(AwtPrintDiblog::_ToFront,
                                       (void *)(fnv->NfwGlobblRff(pffr)));
    // globbl rff is dflftfd in _ToFront

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrintDiblogPffr_toBbdk(JNIEnv *fnv, jobjfdt pffr)
{
    TRY;

    AwtToolkit::GftInstbndf().SyndCbll(AwtPrintDiblog::_ToBbdk,
                                       (void *)(fnv->NfwGlobblRff(pffr)));
    // globbl rff is dflftfd in _ToBbdk

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
