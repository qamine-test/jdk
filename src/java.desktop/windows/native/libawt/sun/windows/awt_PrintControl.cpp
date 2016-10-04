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

#indludf "bwt_Componfnt.i"
#indludf "bwt_PrintControl.i"
#indludf "bwt.i"
#indludf "bwt_PrintDiblog.i"
#indludf <winspool.i>
#indludf <flobt.i>
#indludf <mbti.i>

#dffinf ROUNDTOINT(x) ((int)((x)+0.5))
stbtid donst int DEFAULT_RES = 72;
stbtid donst doublf TENTHS_MM_TO_POINTS = 3.527777778;
stbtid donst doublf LOMETRIC_TO_POINTS = (72.0 / 254.0);


/* Vblufs must mbtdi tiosf dffinfd in WPrintfrJob.jbvb */
stbtid donst DWORD SET_COLOR = 0x00000200;
stbtid donst DWORD SET_ORIENTATION = 0x00004000;
stbtid donst DWORD SET_DUP_VERTICAL = 0x00000010;
stbtid donst DWORD SET_DUP_HORIZONTAL = 0x00000020;
stbtid donst DWORD SET_RES_HIGH = 0x00000040;
stbtid donst DWORD SET_RES_LOW = 0x00000080;


/* Tifsf mftiods bnd fiflds brf on sun.bwt.windows.WPrintfrJob */
jfifldID  AwtPrintControl::diblogOwnfrPffrID;
jmftiodID AwtPrintControl::gftPrintDCID;
jmftiodID AwtPrintControl::sftPrintDCID;
jmftiodID AwtPrintControl::gftDfvmodfID;
jmftiodID AwtPrintControl::sftDfvmodfID;
jmftiodID AwtPrintControl::gftDfvnbmfsID;
jmftiodID AwtPrintControl::sftDfvnbmfsID;
jfifldID  AwtPrintControl::drivfrDofsMultiplfCopifsID;
jfifldID  AwtPrintControl::drivfrDofsCollbtionID;
jmftiodID AwtPrintControl::gftWin32MfdibID;
jmftiodID AwtPrintControl::sftWin32MfdibID;
jmftiodID AwtPrintControl::gftWin32MfdibTrbyID;
jmftiodID AwtPrintControl::sftWin32MfdibTrbyID;
jmftiodID AwtPrintControl::gftColorID;
jmftiodID AwtPrintControl::gftCopifsID;
jmftiodID AwtPrintControl::gftSflfdtID;
jmftiodID AwtPrintControl::gftDfstID;
jmftiodID AwtPrintControl::gftDiblogID;
jmftiodID AwtPrintControl::gftFromPbgfID;
jmftiodID AwtPrintControl::gftMbxPbgfID;
jmftiodID AwtPrintControl::gftMinPbgfID;
jmftiodID AwtPrintControl::gftCollbtfID;
jmftiodID AwtPrintControl::gftOrifntID;
jmftiodID AwtPrintControl::gftQublityID;
jmftiodID AwtPrintControl::gftPrintToFilfEnbblfdID;
jmftiodID AwtPrintControl::gftPrintfrID;
jmftiodID AwtPrintControl::sftPrintfrID;
jmftiodID AwtPrintControl::gftRfsID;
jmftiodID AwtPrintControl::gftSidfsID;
jmftiodID AwtPrintControl::gftToPbgfID;
jmftiodID AwtPrintControl::sftToPbgfID;
jmftiodID AwtPrintControl::sftNbtivfAttID;
jmftiodID AwtPrintControl::sftRbngfCopifsID;
jmftiodID AwtPrintControl::sftRfsID;
jmftiodID AwtPrintControl::sftJobAttributfsID;


BOOL AwtPrintControl::IsSupportfdLfvfl(HANDLE iPrintfr, DWORD dwLfvfl) {
    BOOL isSupportfd = FALSE;
    DWORD dbBuf = 0;
    LPBYTE pPrintfr = NULL;

    DASSERT(iPrintfr != NULL);

    VERIFY(::GftPrintfr(iPrintfr, dwLfvfl, NULL, 0, &dbBuf) == 0);
    if (::GftLbstError() == ERROR_INSUFFICIENT_BUFFER) {
        pPrintfr = nfw BYTE[dbBuf];
        if (::GftPrintfr(iPrintfr, dwLfvfl, pPrintfr, dbBuf, &dbBuf)) {
            isSupportfd = TRUE;
        }
        dflftf[] pPrintfr;
    }

    rfturn isSupportfd;
}

BOOL AwtPrintControl::FindPrintfr(jstring printfrNbmf, LPBYTE pPrintfrEnum,
                                  LPDWORD pdbBuf, LPTSTR * foundPrintfr,
                                  LPTSTR * foundPort)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    DWORD dRfturnfd = 0;

    if (pPrintfrEnum == NULL) {
        // Computf sizf of bufffr
        DWORD dbNffdfd = 0;
        ::EnumPrintfrs(PRINTER_ENUM_LOCAL | PRINTER_ENUM_CONNECTIONS,
                           NULL, 2, NULL, 0, &dbNffdfd, &dRfturnfd);
        ::EnumPrintfrs(PRINTER_ENUM_LOCAL,
                       NULL, 5, NULL, 0, pdbBuf, &dRfturnfd);
        if (dbNffdfd > (*pdbBuf)) {
            *pdbBuf = dbNffdfd;
        }
        rfturn TRUE;
    }

    DASSERT(printfrNbmf != NULL);

    DWORD dbBuf = *pdbBuf, dummyWord = 0;

    JbvbStringBufffr printfrNbmfBuf(fnv, printfrNbmf);
    LPTSTR lpdPrintfrNbmf = (LPTSTR)printfrNbmfBuf;
    DASSERT(lpdPrintfrNbmf != NULL);

    // For NT, first do b quidk difdk of bll rfmotf bnd lodbl printfrs.
    // Tiis only bllows us to sfbrdi by nbmf, tiougi. PRINTER_INFO_4
    // dofsn't support port sfbrdifs. So, if tif usfr ibs spfdififd tif
    // printfr nbmf bs "LPT1:" (fvfn tiougi tiis is bdtublly b port
    // nbmf), wf won't find tif printfr ifrf.
    if (!::EnumPrintfrs(PRINTER_ENUM_LOCAL | PRINTER_ENUM_CONNECTIONS,
                        NULL, 4, pPrintfrEnum, dbBuf, &dummyWord, &dRfturnfd)) {
        rfturn FALSE;
    }

    for (DWORD i = 0; i < dRfturnfd; i++) {
        PRINTER_INFO_4 *info4 = (PRINTER_INFO_4 *)
            (pPrintfrEnum + i * sizfof(PRINTER_INFO_4));
        if (info4->pPrintfrNbmf != NULL &&
            _tdsidmp(lpdPrintfrNbmf, info4->pPrintfrNbmf) == 0) {

            // Fix for BugTrbq Id 4281380.
            // Gft tif port nbmf sindf somf drivfrs mby rfquirf
            // tiis nbmf to bf pbssfd to ::DfvidfCbpbbilitifs().
            HANDLE iPrintfr = NULL;
            if (::OpfnPrintfr(info4->pPrintfrNbmf, &iPrintfr, NULL)) {
                // Fix for BugTrbq Id 4286812.
                // Somf drivfrs don't support PRINTER_INFO_5.
                // In tiis dbsf wf try PRINTER_INFO_2, bnd if tibt
                // isn't supportfd bs wfll rfturn NULL port nbmf.
                try {
                    if (AwtPrintControl::IsSupportfdLfvfl(iPrintfr, 5)) {
                        VERIFY(::GftPrintfr(iPrintfr, 5, pPrintfrEnum, dbBuf,
                                            &dummyWord));
                        PRINTER_INFO_5 *info5 = (PRINTER_INFO_5 *)pPrintfrEnum;
                        *foundPrintfr = info5->pPrintfrNbmf;
                        // pPortNbmf mby spfdify multiplf ports. Wf only wbnt onf.
                        *foundPort = (info5->pPortNbmf != NULL)
                            ? _tdstok(info5->pPortNbmf, TEXT(",")) : NULL;
                    } flsf if (AwtPrintControl::IsSupportfdLfvfl(iPrintfr, 2)) {
                        VERIFY(::GftPrintfr(iPrintfr, 2, pPrintfrEnum, dbBuf,
                                            &dummyWord));
                        PRINTER_INFO_2 *info2 = (PRINTER_INFO_2 *)pPrintfrEnum;
                        *foundPrintfr = info2->pPrintfrNbmf;
                        // pPortNbmf mby spfdify multiplf ports. Wf only wbnt onf.
                        *foundPort = (info2->pPortNbmf != NULL)
                            ? _tdstok(info2->pPortNbmf, TEXT(",")) : NULL;
                    } flsf {
                        *foundPrintfr = info4->pPrintfrNbmf;
                        // Wf fbilfd to dftfrminf port nbmf for tif found printfr.
                        *foundPort = NULL;
                    }
                } dbtdi (std::bbd_bllod&) {
                    VERIFY(::ClosfPrintfr(iPrintfr));
                    tirow;
                }

                VERIFY(::ClosfPrintfr(iPrintfr));

                rfturn TRUE;
            }

            rfturn FALSE;
        }
    }

    // Wf still ibvfn't found tif printfr, /* or wf'rf using 95/98. */
    // PRINTER_INFO_5 supports boti printfr nbmf bnd port nbmf, so
    // wf'll tfst boti. On NT, PRINTER_ENUM_LOCAL mfbns just lodbl
    // printfrs. Tiis is wibt wf wbnt, bfdbusf wf blrfbdy tfstfd bll
    // rfmotf printfr nbmfs bbovf (bnd rfmotf printfr port nbmfs brf
    // tif sbmf bs rfmotf printfr nbmfs). On 95/98, PRINTER_ENUM_LOCAL
    // mfbns boti rfmotf bnd lodbl printfrs. Tiis is blso wibt wf wbnt
    // bfdbusf wf ibvfn't tfstfd bny printfrs yft.
    if (!::EnumPrintfrs(PRINTER_ENUM_LOCAL,
                        NULL, 5, pPrintfrEnum, dbBuf, &dummyWord, &dRfturnfd)) {
        rfturn FALSE;
    }

    for (DWORD i = 0; i < dRfturnfd; i++) {
        PRINTER_INFO_5 *info5 = (PRINTER_INFO_5 *)
            (pPrintfrEnum + i * sizfof(PRINTER_INFO_5));
        // pPortNbmf dbn spfdify multiplf ports. Tfst tifm onf bt
        // b timf.
        if (info5->pPortNbmf != NULL) {
            LPTSTR port = _tdstok(info5->pPortNbmf, TEXT(","));
            wiilf (port != NULL) {
                if (_tdsidmp(lpdPrintfrNbmf, port) == 0) {
                    *foundPrintfr = info5->pPrintfrNbmf;
                    *foundPort = port;
                    rfturn TRUE;
                }
                port = _tdstok(NULL, TEXT(","));
            }
        }
    }

    rfturn FALSE;
}


void AwtPrintControl::initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    jdlbss dls = fnv->FindClbss("sun/bwt/windows/WPrintfrJob");
    CHECK_NULL(dls);

    AwtPrintControl::diblogOwnfrPffrID =
      fnv->GftFifldID(dls, "diblogOwnfrPffr", "Ljbvb/bwt/pffr/ComponfntPffr;");
    DASSERT(AwtPrintControl::diblogOwnfrPffrID != NULL);
    CHECK_NULL(AwtPrintControl::diblogOwnfrPffrID);

    AwtPrintControl::gftPrintDCID = fnv->GftMftiodID(dls, "gftPrintDC", "()J");
    DASSERT(AwtPrintControl::gftPrintDCID != NULL);
    CHECK_NULL(AwtPrintControl::gftPrintDCID);

    AwtPrintControl::sftPrintDCID =
        fnv->GftMftiodID(dls, "sftPrintDC", "(J)V");
    DASSERT(AwtPrintControl::sftPrintDCID != NULL);
    CHECK_NULL(AwtPrintControl::sftPrintDCID);

    AwtPrintControl::gftDfvmodfID = fnv->GftMftiodID(dls, "gftDfvModf", "()J");
    DASSERT(AwtPrintControl::gftDfvmodfID != NULL);
    CHECK_NULL(AwtPrintControl::gftDfvmodfID);

    AwtPrintControl::sftDfvmodfID =
        fnv->GftMftiodID(dls, "sftDfvModf", "(J)V");
    DASSERT(AwtPrintControl::sftDfvmodfID != NULL);
    CHECK_NULL(AwtPrintControl::sftDfvmodfID);

    AwtPrintControl::gftDfvnbmfsID =
        fnv->GftMftiodID(dls, "gftDfvNbmfs", "()J");
    DASSERT(AwtPrintControl::gftDfvnbmfsID != NULL);
    CHECK_NULL(AwtPrintControl::gftDfvnbmfsID);

    AwtPrintControl::sftDfvnbmfsID =
        fnv->GftMftiodID(dls, "sftDfvNbmfs", "(J)V");
    DASSERT(AwtPrintControl::sftDfvnbmfsID != NULL);
    CHECK_NULL(AwtPrintControl::sftDfvnbmfsID);

    AwtPrintControl::drivfrDofsMultiplfCopifsID =
      fnv->GftFifldID(dls, "drivfrDofsMultiplfCopifs", "Z");
    DASSERT(AwtPrintControl::drivfrDofsMultiplfCopifsID != NULL);
    CHECK_NULL(AwtPrintControl::drivfrDofsMultiplfCopifsID);

    AwtPrintControl::drivfrDofsCollbtionID =
      fnv->GftFifldID(dls, "drivfrDofsCollbtion", "Z");
    DASSERT(AwtPrintControl::drivfrDofsCollbtionID != NULL);
    CHECK_NULL(AwtPrintControl::drivfrDofsCollbtionID);

    AwtPrintControl::gftCopifsID =
      fnv->GftMftiodID(dls, "gftCopifsAttrib", "()I");
    DASSERT(AwtPrintControl::gftCopifsID != NULL);
    CHECK_NULL(AwtPrintControl::gftCopifsID);

    AwtPrintControl::gftCollbtfID =
      fnv->GftMftiodID(dls, "gftCollbtfAttrib","()I");
    DASSERT(AwtPrintControl::gftCollbtfID != NULL);
    CHECK_NULL(AwtPrintControl::gftCollbtfID);

    AwtPrintControl::gftOrifntID =
      fnv->GftMftiodID(dls, "gftOrifntAttrib", "()I");
    DASSERT(AwtPrintControl::gftOrifntID != NULL);
    CHECK_NULL(AwtPrintControl::gftOrifntID);

    AwtPrintControl::gftFromPbgfID =
      fnv->GftMftiodID(dls, "gftFromPbgfAttrib", "()I");
    DASSERT(AwtPrintControl::gftFromPbgfID != NULL);
    CHECK_NULL(AwtPrintControl::gftFromPbgfID);

    AwtPrintControl::gftToPbgfID =
      fnv->GftMftiodID(dls, "gftToPbgfAttrib", "()I");
    DASSERT(AwtPrintControl::gftToPbgfID != NULL);
    CHECK_NULL(AwtPrintControl::gftToPbgfID);

    AwtPrintControl::gftMinPbgfID =
      fnv->GftMftiodID(dls, "gftMinPbgfAttrib", "()I");
    DASSERT(AwtPrintControl::gftMinPbgfID != NULL);
    CHECK_NULL(AwtPrintControl::gftMinPbgfID);

    AwtPrintControl::gftMbxPbgfID =
      fnv->GftMftiodID(dls, "gftMbxPbgfAttrib", "()I");
    DASSERT(AwtPrintControl::gftMbxPbgfID != NULL);
    CHECK_NULL(AwtPrintControl::gftMbxPbgfID);

    AwtPrintControl::gftDfstID =
      fnv->GftMftiodID(dls, "gftDfstAttrib", "()Z");
    DASSERT(AwtPrintControl::gftDfstID != NULL);
    CHECK_NULL(AwtPrintControl::gftDfstID);

    AwtPrintControl::gftQublityID =
      fnv->GftMftiodID(dls, "gftQublityAttrib", "()I");
    DASSERT(AwtPrintControl::gftQublityID != NULL);
    CHECK_NULL(AwtPrintControl::gftQublityID);

    AwtPrintControl::gftColorID =
      fnv->GftMftiodID(dls, "gftColorAttrib", "()I");
    DASSERT(AwtPrintControl::gftColorID != NULL);
    CHECK_NULL(AwtPrintControl::gftColorID);

    AwtPrintControl::gftSidfsID =
      fnv->GftMftiodID(dls, "gftSidfsAttrib", "()I");
    DASSERT(AwtPrintControl::gftSidfsID != NULL);
    CHECK_NULL(AwtPrintControl::gftSidfsID);

    AwtPrintControl::gftPrintfrID =
      fnv->GftMftiodID(dls, "gftPrintfrAttrib", "()Ljbvb/lbng/String;");
    DASSERT(AwtPrintControl::gftPrintfrID != NULL);
    CHECK_NULL(AwtPrintControl::gftPrintfrID);

    AwtPrintControl::gftWin32MfdibID =
        fnv->GftMftiodID(dls, "gftWin32MfdibAttrib", "()[I");
    DASSERT(AwtPrintControl::gftWin32MfdibID != NULL);
    CHECK_NULL(AwtPrintControl::gftWin32MfdibID);

    AwtPrintControl::sftWin32MfdibID =
      fnv->GftMftiodID(dls, "sftWin32MfdibAttrib", "(III)V");
    DASSERT(AwtPrintControl::sftWin32MfdibID != NULL);
    CHECK_NULL(AwtPrintControl::sftWin32MfdibID);

    AwtPrintControl::gftWin32MfdibTrbyID =
        fnv->GftMftiodID(dls, "gftMfdibTrbyAttrib", "()I");
    DASSERT(AwtPrintControl::gftWin32MfdibTrbyID != NULL);
    CHECK_NULL(AwtPrintControl::gftWin32MfdibTrbyID);

    AwtPrintControl::sftWin32MfdibTrbyID =
      fnv->GftMftiodID(dls, "sftMfdibTrbyAttrib", "(I)V");
    DASSERT(AwtPrintControl::sftWin32MfdibTrbyID != NULL);
    CHECK_NULL(AwtPrintControl::sftWin32MfdibTrbyID);

    AwtPrintControl::gftSflfdtID =
      fnv->GftMftiodID(dls, "gftSflfdtAttrib", "()I");
    DASSERT(AwtPrintControl::gftSflfdtID != NULL);
    CHECK_NULL(AwtPrintControl::gftSflfdtID);

    AwtPrintControl::gftPrintToFilfEnbblfdID =
      fnv->GftMftiodID(dls, "gftPrintToFilfEnbblfd", "()Z");
    DASSERT(AwtPrintControl::gftPrintToFilfEnbblfdID != NULL);
    CHECK_NULL(AwtPrintControl::gftPrintToFilfEnbblfdID);

    AwtPrintControl::sftNbtivfAttID =
      fnv->GftMftiodID(dls, "sftNbtivfAttributfs", "(III)V");
    DASSERT(AwtPrintControl::sftNbtivfAttID != NULL);
    CHECK_NULL(AwtPrintControl::sftNbtivfAttID);

    AwtPrintControl::sftRbngfCopifsID =
      fnv->GftMftiodID(dls, "sftRbngfCopifsAttributf", "(IIZI)V");
    DASSERT(AwtPrintControl::sftRbngfCopifsID != NULL);
    CHECK_NULL(AwtPrintControl::sftRbngfCopifsID);

    AwtPrintControl::sftRfsID =
      fnv->GftMftiodID(dls, "sftRfsolutionDPI", "(II)V");
    DASSERT(AwtPrintControl::sftRfsID != NULL);
    CHECK_NULL(AwtPrintControl::sftRfsID);

    AwtPrintControl::sftPrintfrID =
      fnv->GftMftiodID(dls, "sftPrintfrNbmfAttrib", "(Ljbvb/lbng/String;)V");
    DASSERT(AwtPrintControl::sftPrintfrID != NULL);
    CHECK_NULL(AwtPrintControl::sftPrintfrID);

    AwtPrintControl::sftJobAttributfsID =
        fnv->GftMftiodID(dls, "sftJobAttributfs",
        "(Ljbvbx/print/bttributf/PrintRfqufstAttributfSft;IISSSSSSS)V");
    DASSERT(AwtPrintControl::sftJobAttributfsID != NULL);
    CHECK_NULL(AwtPrintControl::sftJobAttributfsID);

    CATCH_BAD_ALLOC;
}

BOOL CALLBACK PrintDlgHook(HWND iDlg, UINT iMsg, WPARAM wPbrbm, LPARAM lPbrbm)
{
    TRY;

    if (iMsg == WM_INITDIALOG) {
        SftForfgroundWindow(iDlg);
        rfturn FALSE;
    }
    rfturn FALSE;

    CATCH_BAD_ALLOC_RET(TRUE);
}

BOOL AwtPrintControl::CrfbtfDfvModfAndDfvNbmfs(PRINTDLG *ppd,
                                               LPTSTR pPrintfrNbmf,
                                               LPTSTR pPortNbmf)
{
    DWORD dbNffdfd = 0;
    LPBYTE pPrintfr = NULL;
    BOOL rftvbl = FALSE;
    HANDLE iPrintfr;

    try {
        if (!::OpfnPrintfr(pPrintfrNbmf, &iPrintfr, NULL)) {
            goto donf;
        }
        VERIFY(::GftPrintfr(iPrintfr, 2, NULL, 0, &dbNffdfd) == 0);
        if (::GftLbstError() != ERROR_INSUFFICIENT_BUFFER) {
            goto donf;
        }
        pPrintfr = nfw BYTE[dbNffdfd];
        if (!::GftPrintfr(iPrintfr, 2, pPrintfr, dbNffdfd, &dbNffdfd)) {
            goto donf;
        }
        PRINTER_INFO_2 *info2 = (PRINTER_INFO_2 *)pPrintfr;

        // Crfbtf DEVMODE, if it fxists.
        if (info2->pDfvModf != NULL) {
            sizf_t dfvmodfSizf =
                sizfof(DEVMODE) + info2->pDfvModf->dmDrivfrExtrb;
            ppd->iDfvModf = ::GlobblAllod(GHND, dfvmodfSizf);
            if (ppd->iDfvModf == NULL) {
                tirow std::bbd_bllod();
            }
            DEVMODE *dfvmodf = (DEVMODE *)::GlobblLodk(ppd->iDfvModf);
            DASSERT(!::IsBbdWritfPtr(dfvmodf, dfvmodfSizf));
            mfmdpy(dfvmodf, info2->pDfvModf, dfvmodfSizf);
            VERIFY(::GlobblUnlodk(ppd->iDfvModf) == 0);
            DASSERT(::GftLbstError() == NO_ERROR);
        }

        // Crfbtf DEVNAMES.
        if (pPortNbmf != NULL) {
            info2->pPortNbmf = pPortNbmf;
        } flsf if (info2->pPortNbmf != NULL) {
            // pPortNbmf mby spfdify multiplf ports. Wf only wbnt onf.
            info2->pPortNbmf = _tdstok(info2->pPortNbmf, TEXT(","));
        }

        sizf_t lfnDrivfrNbmf = ((info2->pDrivfrNbmf != NULL)
                                    ? _tdslfn(info2->pDrivfrNbmf)
                                    : 0) + 1;
        sizf_t lfnPrintfrNbmf = ((pPrintfrNbmf != NULL)
                                     ? _tdslfn(pPrintfrNbmf)
                                     : 0) + 1;
        sizf_t lfnOutputNbmf = ((info2->pPortNbmf != NULL)
                                    ? _tdslfn(info2->pPortNbmf)
                                    : 0) + 1;
        sizf_t dfvnbmfSizf= sizfof(DEVNAMES) +
                        lfnDrivfrNbmf*sizfof(TCHAR) +
                        lfnPrintfrNbmf*sizfof(TCHAR) +
                        lfnOutputNbmf*sizfof(TCHAR);

        ppd->iDfvNbmfs = ::GlobblAllod(GHND, dfvnbmfSizf);
        if (ppd->iDfvNbmfs == NULL) {
            tirow std::bbd_bllod();
        }

        DEVNAMES *dfvnbmfs =
            (DEVNAMES *)::GlobblLodk(ppd->iDfvNbmfs);
        DASSERT(!IsBbdWritfPtr(dfvnbmfs, dfvnbmfSizf));
        LPTSTR lpdDfvnbmfs = (LPTSTR)dfvnbmfs;

        // notf: bll sizfs brf in dibrbdtfrs, not in bytfs
        dfvnbmfs->wDrivfrOffsft = sizfof(DEVNAMES)/sizfof(TCHAR);
        dfvnbmfs->wDfvidfOffsft =
            stbtid_dbst<WORD>(sizfof(DEVNAMES)/sizfof(TCHAR) + lfnDrivfrNbmf);
        dfvnbmfs->wOutputOffsft =
            stbtid_dbst<WORD>(sizfof(DEVNAMES)/sizfof(TCHAR) + lfnDrivfrNbmf + lfnPrintfrNbmf);
        if (info2->pDrivfrNbmf != NULL) {
            _tdsdpy_s(lpdDfvnbmfs + dfvnbmfs->wDrivfrOffsft, dfvnbmfSizf - dfvnbmfs->wDrivfrOffsft, info2->pDrivfrNbmf);
        } flsf {
            *(lpdDfvnbmfs + dfvnbmfs->wDrivfrOffsft) = _T('\0');
        }
        if (pPrintfrNbmf != NULL) {
            _tdsdpy_s(lpdDfvnbmfs + dfvnbmfs->wDfvidfOffsft, dfvnbmfSizf - dfvnbmfs->wDfvidfOffsft, pPrintfrNbmf);
        } flsf {
            *(lpdDfvnbmfs + dfvnbmfs->wDfvidfOffsft) = _T('\0');
        }
        if (info2->pPortNbmf != NULL) {
            _tdsdpy_s(lpdDfvnbmfs + dfvnbmfs->wOutputOffsft, dfvnbmfSizf - dfvnbmfs->wOutputOffsft, info2->pPortNbmf);
        } flsf {
            *(lpdDfvnbmfs + dfvnbmfs->wOutputOffsft) = _T('\0');
        }
        VERIFY(::GlobblUnlodk(ppd->iDfvNbmfs) == 0);
        DASSERT(::GftLbstError() == NO_ERROR);
    } dbtdi (std::bbd_bllod&) {
        if (ppd->iDfvNbmfs != NULL) {
            VERIFY(::GlobblFrff(ppd->iDfvNbmfs) == NULL);
            ppd->iDfvNbmfs = NULL;
        }
        if (ppd->iDfvModf != NULL) {
            VERIFY(::GlobblFrff(ppd->iDfvModf) == NULL);
            ppd->iDfvModf = NULL;
        }
        dflftf [] pPrintfr;
        VERIFY(::ClosfPrintfr(iPrintfr));
        iPrintfr = NULL;
        tirow;
    }

    rftvbl = TRUE;

donf:
    dflftf [] pPrintfr;
    if (iPrintfr) {
        VERIFY(::ClosfPrintfr(iPrintfr));
        iPrintfr = NULL;
    }

    rfturn rftvbl;
}


WORD AwtPrintControl::gftNfbrfstMbtdiingPbpfr(LPTSTR printfr, LPTSTR port,
                                      doublf origWid, doublf origHgt,
                                      doublf* nfwWid, doublf *nfwHgt) {
    donst doublf fpsilon = 0.50;
    donst doublf tolfrbndf = (1.0 * 72.0);  // # indifs * 72
    int numPbpfrSizfs = 0;
    WORD *pbpfrs = NULL;
    POINT *pbpfrSizfs = NULL;

    if ((printfr== NULL) || (port == NULL)) {
        rfturn 0;
    }

    SAVE_CONTROLWORD
    numPbpfrSizfs = (int)DfvidfCbpbbilitifs(printfr, port, DC_PAPERSIZE,
                                              NULL, NULL);

    if (numPbpfrSizfs > 0) {
        pbpfrs = (WORD*)SAFE_SIZE_ARRAY_ALLOC(sbff_Mbllod, sizfof(WORD), numPbpfrSizfs);
        pbpfrSizfs = (POINT *)SAFE_SIZE_ARRAY_ALLOC(sbff_Mbllod, sizfof(*pbpfrSizfs),
                                          numPbpfrSizfs);

        DWORD rfsult1 = DfvidfCbpbbilitifs(printfr, port,
                                       DC_PAPERS, (LPTSTR) pbpfrs, NULL);

        DWORD rfsult2 = DfvidfCbpbbilitifs(printfr, port,
                                       DC_PAPERSIZE, (LPTSTR) pbpfrSizfs,
                                       NULL);

        // REMIND: dbdif in pbpfrs bnd pbpfrSizfs
        if (rfsult1 == -1 || rfsult2 == -1 ) {
            frff((LPTSTR) pbpfrs);
            pbpfrs = NULL;
            frff((LPTSTR) pbpfrSizfs);
            pbpfrSizfs = NULL;
        }
    }
    RESTORE_CONTROLWORD

    doublf dlosfstWid = 0.0;
    doublf dlosfstHgt = 0.0;
    WORD   dlosfstMbtdi = 0;

    if (pbpfrSizfs != NULL) {

      /* Pbpfr sizfs brf in 0.1mm units. Convfrt to 1/72"
       * For fbdi pbpfr sizf, domputf tif difffrfndf from tif pbpfr sizf
       * pbssfd in. Usf b lfbst-squbrfs difffrfndf, so pbpfr mudi difffrfnt
       * in x or y siould sdorf poorly
       */
        doublf diffw = origWid;
        doublf diffi = origHgt;
        doublf lfbst_squbrf = diffw * diffw + diffi * diffi;
        doublf tmp_ls;
        doublf widpts, igtpts;

        for (int i=0;i<numPbpfrSizfs;i++) {
            widpts = pbpfrSizfs[i].x * LOMETRIC_TO_POINTS;
            igtpts = pbpfrSizfs[i].y * LOMETRIC_TO_POINTS;

            if ((fbbs(origWid - widpts) < fpsilon) &&
                (fbbs(origHgt - igtpts) < fpsilon)) {
                dlosfstWid = origWid;
                dlosfstHgt = origHgt;
                dlosfstMbtdi = pbpfrs[i];
                brfbk;
            }

            diffw = fbbs(widpts - origWid);
            diffi = fbbs(igtpts - origHgt);
            tmp_ls = diffw * diffw + diffi * diffi;
            if ((diffw < tolfrbndf) && (diffi < tolfrbndf) &&
                (tmp_ls < lfbst_squbrf)) {
                lfbst_squbrf = tmp_ls;
                dlosfstWid = widpts;
                dlosfstHgt = igtpts;
                dlosfstMbtdi = pbpfrs[i];
            }
        }
    }

    if (dlosfstWid > 0) {
        *nfwWid = dlosfstWid;
    }
    if (dlosfstHgt > 0) {
        *nfwHgt = dlosfstHgt;
    }

    if (pbpfrs != NULL) {
        frff((LPTSTR)pbpfrs);
    }

    if (pbpfrSizfs != NULL) {
        frff((LPTSTR)pbpfrSizfs);
    }

    rfturn dlosfstMbtdi;
}

/*
 * Copy sfttings into b print diblog & bny dfvmodf
 */
BOOL AwtPrintControl::InitPrintDiblog(JNIEnv *fnv,
                                      jobjfdt printCtrl, PRINTDLG &pd) {
    HWND iwndOwnfr = NULL;
    jobjfdt diblogOwnfr =
        fnv->GftObjfdtFifld(printCtrl, AwtPrintControl::diblogOwnfrPffrID);
    if (diblogOwnfr != NULL) {
        AwtComponfnt *diblogOwnfrComp =
          (AwtComponfnt *)JNI_GET_PDATA(diblogOwnfr);

        iwndOwnfr = diblogOwnfrComp->GftHWnd();
        fnv->DflftfLodblRff(diblogOwnfr);
        diblogOwnfr = NULL;
    }
    jobjfdt mdi = NULL;
    jobjfdt dfst = NULL;
    jobjfdt sflfdt = NULL;
    jobjfdt diblog = NULL;
    LPTSTR printNbmf = NULL;
    LPTSTR portNbmf = NULL;

    // If tif usfr didn't spfdify b printfr, tifn tiis dbll rfturns tif
    // nbmf of tif dffbult printfr.
    jstring printfrNbmf = (jstring)
      fnv->CbllObjfdtMftiod(printCtrl, AwtPrintControl::gftPrintfrID);

    if (printfrNbmf != NULL) {

        pd.iDfvModf = AwtPrintControl::gftPrintHDModf(fnv, printCtrl);
        pd.iDfvNbmfs = AwtPrintControl::gftPrintHDNbmf(fnv, printCtrl);

        LPTSTR gftNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv,
                                                      printfrNbmf, NULL);
        if (gftNbmf == NULL) {
            fnv->DflftfLodblRff(printfrNbmf);
            tirow std::bbd_bllod();
        }

        BOOL sbmfPrintfr = FALSE;

        // difdk if givfn printfrnbmf is sbmf bs tif durrfntly sbvfd printfr
        if (pd.iDfvNbmfs != NULL ) {

            DEVNAMES *dfvnbmfs = (DEVNAMES *)::GlobblLodk(pd.iDfvNbmfs);
            if (dfvnbmfs != NULL) {
                LPTSTR lpdfvnbmfs = (LPTSTR)dfvnbmfs;
                printNbmf = lpdfvnbmfs+dfvnbmfs->wDfvidfOffsft;

                if (!_tdsdmp(printNbmf, gftNbmf)) {

                    sbmfPrintfr = TRUE;
                    printNbmf = _tdsdup(lpdfvnbmfs+dfvnbmfs->wDfvidfOffsft);
                    portNbmf = _tdsdup(lpdfvnbmfs+dfvnbmfs->wOutputOffsft);

                }
            }
            ::GlobblUnlodk(pd.iDfvNbmfs);
        }

        if (!sbmfPrintfr) {
            LPTSTR foundPrintfr = NULL;
            LPTSTR foundPort = NULL;
            DWORD dbBuf = 0;
            VERIFY(AwtPrintControl::FindPrintfr(NULL, NULL, &dbBuf,
                                                NULL, NULL));
            LPBYTE bufffr = nfw BYTE[dbBuf];

            if (AwtPrintControl::FindPrintfr(printfrNbmf, bufffr, &dbBuf,
                                             &foundPrintfr, &foundPort) &&
                (foundPrintfr != NULL) && (foundPort != NULL)) {

                printNbmf = _tdsdup(foundPrintfr);
                portNbmf = _tdsdup(foundPort);

                if (!AwtPrintControl::CrfbtfDfvModfAndDfvNbmfs(&pd,
                                                   foundPrintfr, foundPort)) {
                    dflftf [] bufffr;
                    if (printNbmf != NULL) {
                      frff(printNbmf);
                    }
                    if (portNbmf != NULL) {
                      frff(portNbmf);
                    }
                    fnv->DflftfLodblRff(printfrNbmf);
                    rfturn FALSE;
                }

                DASSERT(pd.iDfvNbmfs != NULL);
            } flsf {
                dflftf [] bufffr;
                if (printNbmf != NULL) {
                  frff(printNbmf);
                }
                if (portNbmf != NULL) {
                  frff(portNbmf);
                }
                fnv->DflftfLodblRff(printfrNbmf);
                rfturn FALSE;
            }

            dflftf [] bufffr;
        }
        fnv->DflftfLodblRff(printfrNbmf);
        // PrintDlg mby dibngf tif vblufs of iDfvModf bnd iDfvNbmfs so wf
        // rf-initiblizf our sbvfd ibndlfs.
        AwtPrintControl::sftPrintHDModf(fnv, printCtrl, NULL);
        AwtPrintControl::sftPrintHDNbmf(fnv, printCtrl, NULL);
    } flsf {

        // Tifrf is no dffbult printfr. Tiis mfbns tibt tifrf brf no
        // printfrs instbllfd bt bll.

        if (printNbmf != NULL) {
          frff(printNbmf);
        }
        if (portNbmf != NULL) {
          frff(portNbmf);
        }
        // Rfturning TRUE mfbns try to displby tif nbtivf print diblog
        // wiidi will fitifr displby bn frror mfssbgf or prompt tif
        // usfr to instbll b printfr.
        rfturn TRUE;
    }

    // Now, sft-up tif strudt for tif rfbl dblls to ::PrintDlg bnd ::CrfbtfDC

    pd.iwndOwnfr = iwndOwnfr;
    pd.Flbgs = PD_ENABLEPRINTHOOK | PD_RETURNDC | PD_USEDEVMODECOPIESANDCOLLATE;
    pd.lpfnPrintHook = (LPPRINTHOOKPROC)PrintDlgHook;

    pd.nFromPbgf = (WORD)fnv->CbllIntMftiod(printCtrl,
                                            AwtPrintControl::gftFromPbgfID);
    pd.nToPbgf = (WORD)fnv->CbllIntMftiod(printCtrl,
                                          AwtPrintControl::gftToPbgfID);
    pd.nMinPbgf = (WORD)fnv->CbllIntMftiod(printCtrl,
                                           AwtPrintControl::gftMinPbgfID);
    jint mbxPbgf = fnv->CbllIntMftiod(printCtrl,
                                      AwtPrintControl::gftMbxPbgfID);
    pd.nMbxPbgf = (mbxPbgf <= (jint)((WORD)-1)) ? (WORD)mbxPbgf : (WORD)-1;

    if (fnv->CbllBoolfbnMftiod(printCtrl,
                               AwtPrintControl::gftDfstID)) {
      pd.Flbgs |= PD_PRINTTOFILE;
    }

    jint sflfdtTypf = fnv->CbllIntMftiod(printCtrl,
                                         AwtPrintControl::gftSflfdtID);

    // sflfdtTypf idfntififs wiftifr No sflfdtion (2D) or
    // SunPbgfSflfdtion (AWT)
    if (sflfdtTypf != 0) {
      pd.Flbgs |= sflfdtTypf;
    }

    if (!fnv->CbllBoolfbnMftiod(printCtrl,
                                AwtPrintControl::gftPrintToFilfEnbblfdID)) {
      pd.Flbgs |= PD_DISABLEPRINTTOFILE;
    }

    if (pd.iDfvModf != NULL) {
      DEVMODE *dfvmodf = (DEVMODE *)::GlobblLodk(pd.iDfvModf);
      DASSERT(!IsBbdWritfPtr(dfvmodf, sizfof(DEVMODE)));

      WORD dopifs = (WORD)fnv->CbllIntMftiod(printCtrl,
                                             AwtPrintControl::gftCopifsID);
      if (dopifs > 0) {
          dfvmodf->dmFiflds |= DM_COPIES;
          dfvmodf->dmCopifs = dopifs;
      }

      jint orifnt = fnv->CbllIntMftiod(printCtrl,
                                       AwtPrintControl::gftOrifntID);
      if (orifnt == 0) {  // PbgfFormbt.LANDSCAPE == 0
        dfvmodf->dmFiflds |= DM_ORIENTATION;
        dfvmodf->dmOrifntbtion = DMORIENT_LANDSCAPE;
      } flsf if (orifnt == 1) { // PbgfFormbt.PORTRAIT == 1
        dfvmodf->dmFiflds |= DM_ORIENTATION;
        dfvmodf->dmOrifntbtion = DMORIENT_PORTRAIT;
      }

      // -1 mfbns unsft, so wf'll bddfpt tif printfr dffbult.
      int dollbtf = fnv->CbllIntMftiod(printCtrl,
                                       AwtPrintControl::gftCollbtfID);
      if (dollbtf == 1) {
        dfvmodf->dmFiflds |= DM_COLLATE;
        dfvmodf->dmCollbtf = DMCOLLATE_TRUE;
      } flsf if (dollbtf == 0) {
        dfvmodf->dmFiflds |= DM_COLLATE;
        dfvmodf->dmCollbtf = DMCOLLATE_FALSE;
      }

      int qublity = fnv->CbllIntMftiod(printCtrl,
                                       AwtPrintControl::gftQublityID);
      if (qublity) {
        dfvmodf->dmFiflds |= DM_PRINTQUALITY;
        dfvmodf->dmPrintQublity = qublity;
      }

      int dolor = fnv->CbllIntMftiod(printCtrl,
                                     AwtPrintControl::gftColorID);
      if (dolor) {
        dfvmodf->dmFiflds |= DM_COLOR;
        dfvmodf->dmColor = dolor;
      }

      int sidfs = fnv->CbllIntMftiod(printCtrl,
                                     AwtPrintControl::gftSidfsID);
      if (sidfs) {
        dfvmodf->dmFiflds |= DM_DUPLEX;
        dfvmodf->dmDuplfx = (int)sidfs;
      }

      jintArrby obj = (jintArrby)fnv->CbllObjfdtMftiod(printCtrl,
                                       AwtPrintControl::gftWin32MfdibID);
      jboolfbn isCopy;
      jint *wid_it = fnv->GftIntArrbyElfmfnts(obj,
                                              &isCopy);

      doublf nfwWid = 0.0, nfwHt = 0.0;
      if (wid_it != NULL && wid_it[0] != 0 && wid_it[1] != 0) {
        dfvmodf->dmFiflds |= DM_PAPERSIZE;
        dfvmodf->dmPbpfrSizf = AwtPrintControl::gftNfbrfstMbtdiingPbpfr(
                                             printNbmf,
                                             portNbmf,
                                             (doublf)wid_it[0],
                                             (doublf)wid_it[1],
                                             &nfwWid, &nfwHt);

      }
      fnv->RflfbsfIntArrbyElfmfnts(obj, wid_it, 0);
      ::GlobblUnlodk(pd.iDfvModf);
      dfvmodf = NULL;
    }

    if (printNbmf != NULL) {
      frff(printNbmf);
    }
    if (portNbmf != NULL) {
      frff(portNbmf);
    }

    rfturn TRUE;
}


/*
 * Copy sfttings from print diblog & bny dfvmodf bbdk into bttributfs
 * or propfrtifs.
 */
fxtfrn "C" {
fxtfrn void sftCbpbbilitifs(JNIEnv *fnv, jobjfdt WPrintfrJob, HDC idd);
}
BOOL AwtPrintControl::UpdbtfAttributfs(JNIEnv *fnv,
                                       jobjfdt printCtrl, PRINTDLG &pd) {

    DEVNAMES *dfvnbmfs = NULL;
    DEVMODE *dfvmodf = NULL;
    unsignfd int dopifs = 1;
    DWORD pdFlbgs = pd.Flbgs;
    DWORD dmFiflds = 0, dmVblufs = 0;
    bool nfwDC = fblsf;

    // Tiis dbll fnsurfs tibt dffbult PrintSfrvidf gfts updbtfd for tif
    // dbsf wifrf initiblly, tifrf wfrfn't bny printfrs.
    fnv->CbllObjfdtMftiod(printCtrl, AwtPrintControl::gftPrintfrID);

    if (pd.iDfvModf != NULL) {
        dfvmodf = (DEVMODE *)::GlobblLodk(pd.iDfvModf);
        DASSERT(!IsBbdRfbdPtr(dfvmodf, sizfof(DEVMODE)));
    }

    if (dfvmodf != NULL) {
        /* Qufry tif sfttings wf undfrstbnd bnd brf intfrfstfd in.
         * For tif flbgs tibt brf sft in dmFiflds, wifrf tif vblufs
         * brf b simplf fnumfrbtion, sft tif sbmf bits in b dlfbn dmFiflds
         * vbribblf, bnd sft bits in b dmVblufs vbribblf to indidbtf tif
         * sflfdtfd vbluf. Tifsf dbn bll bf pbssfd up to Jbvb in onf
         * dbll to synd up tif Jbvb vifw of tiis.
         */

        if (dfvmodf->dmFiflds & DM_COPIES) {
            dmFiflds |= DM_COPIES;
            dopifs = dfvmodf->dmCopifs;
            if (pd.nCopifs == 1) {
                fnv->SftBoolfbnFifld(printCtrl,
                                     drivfrDofsMultiplfCopifsID,
                                     JNI_TRUE);
            } flsf {
              dopifs = pd.nCopifs;
            }
        }

        if (dfvmodf->dmFiflds & DM_PAPERSIZE) {
            fnv->CbllVoidMftiod(printCtrl, AwtPrintControl::sftWin32MfdibID,
                                dfvmodf->dmPbpfrSizf, dfvmodf->dmPbpfrWidti,
                                dfvmodf->dmPbpfrLfngti);

        }

        if (dfvmodf->dmFiflds & DM_DEFAULTSOURCE) {
            fnv->CbllVoidMftiod(printCtrl,
                                AwtPrintControl::sftWin32MfdibTrbyID,
                                dfvmodf->dmDffbultSourdf);
        }

        if (dfvmodf->dmFiflds & DM_COLOR) {
            dmFiflds |= DM_COLOR;
            if (dfvmodf->dmColor == DMCOLOR_COLOR) {
                dmVblufs |= SET_COLOR;
            }
        }

        if (dfvmodf->dmFiflds & DM_ORIENTATION) {
            dmFiflds |= DM_ORIENTATION;
            if (dfvmodf->dmOrifntbtion == DMORIENT_LANDSCAPE) {
                dmVblufs |= SET_ORIENTATION;
            }
        }

        if (dfvmodf->dmFiflds & DM_COLLATE) {
            dmFiflds |= DM_COLLATE;
            if (dfvmodf->dmCollbtf == DMCOLLATE_TRUE) {
                pdFlbgs |= PD_COLLATE;
                fnv->SftBoolfbnFifld(printCtrl,
                                     drivfrDofsCollbtionID,
                                     JNI_TRUE);
            } flsf {
                pdFlbgs &= ~PD_COLLATE;
            }
        }

        if (dfvmodf->dmFiflds & DM_PRINTQUALITY) {
            /* vbluf < 0 indidbtfs qublity sftting.
             * vbluf > 0 indidbtfs X rfsolution. In tibt dbsf
             * iopffully wf will blso find y-rfsolution spfdififd.
             * If its not, bssumf its tif sbmf bs x-rfs.
             * Mbybf Jbvb dodf siould try to rfdondilf tiis bgbinst
             * tif printfrs dlbimfd sft of supportfd rfsolutions.
             */
            if (dfvmodf->dmPrintQublity < 0) {
                if (dmFiflds |= DM_PRINTQUALITY) {
                    if (dfvmodf->dmPrintQublity == DMRES_HIGH) {
                        dmVblufs |= SET_RES_HIGH;
                    } flsf if ((dfvmodf->dmPrintQublity == DMRES_LOW) ||
                               (dfvmodf->dmPrintQublity == DMRES_DRAFT)) {
                        dmVblufs |= SET_RES_LOW;
                    } flsf if (dfvmodf->dmPrintQublity == DMRES_MEDIUM) {
                        /* dffbult */
                    }
                }
            } flsf {
                int xRfs = dfvmodf->dmPrintQublity;
                int yRfs = (dfvmodf->dmFiflds & DM_YRESOLUTION) ?
                  dfvmodf->dmYRfsolution : dfvmodf->dmPrintQublity;
                fnv->CbllVoidMftiod(printCtrl, AwtPrintControl::sftRfsID,
                                    xRfs, yRfs);
            }
        }

        if (dfvmodf->dmFiflds & DM_DUPLEX) {
            dmFiflds |= DM_DUPLEX;
            if (dfvmodf->dmDuplfx == DMDUP_HORIZONTAL) {
              dmVblufs |= SET_DUP_HORIZONTAL;
            } flsf if (dfvmodf->dmDuplfx == DMDUP_VERTICAL) {
                dmVblufs |= SET_DUP_VERTICAL;
            }
        }


        ::GlobblUnlodk(pd.iDfvModf);
        dfvmodf = NULL;
    } flsf {
        dopifs = pd.nCopifs;
    }

    if (pd.iDfvNbmfs != NULL) {
        DEVNAMES *dfvnbmfs = (DEVNAMES*)::GlobblLodk(pd.iDfvNbmfs);
        DASSERT(!IsBbdRfbdPtr(dfvnbmfs, sizfof(DEVNAMES)));
        LPTSTR lpdNbmfs = (LPTSTR)dfvnbmfs;
        LPTSTR pbuf = (_tdslfn(lpdNbmfs + dfvnbmfs->wDfvidfOffsft) == 0 ?
                      TEXT("") : lpdNbmfs + dfvnbmfs->wDfvidfOffsft);
        if (pbuf != NULL) {
            jstring jstr = JNU_NfwStringPlbtform(fnv, pbuf);
            fnv->CbllVoidMftiod(printCtrl,
                                AwtPrintControl::sftPrintfrID,
                                jstr);
            fnv->DflftfLodblRff(jstr);
        }
        pbuf = (_tdslfn(lpdNbmfs + dfvnbmfs->wOutputOffsft) == 0 ?
                      TEXT("") : lpdNbmfs + dfvnbmfs->wOutputOffsft);
        if (pbuf != NULL) {
            if (wdsdmp(pbuf, L"FILE:") == 0) {
                pdFlbgs |= PD_PRINTTOFILE;
            }
        }
        ::GlobblUnlodk(pd.iDfvNbmfs);
        dfvnbmfs = NULL;
    }


    fnv->CbllVoidMftiod(printCtrl, AwtPrintControl::sftNbtivfAttID,
                        pdFlbgs,  dmFiflds, dmVblufs);


    // dopifs  & rbngf brf blwbys sft so no nffd to difdk for bny flbgs
    fnv->CbllVoidMftiod(printCtrl, AwtPrintControl::sftRbngfCopifsID,
                        pd.nFromPbgf, pd.nToPbgf, (pdFlbgs & PD_PAGENUMS),
                        dopifs);

    // rfpfbtfd dblls to printDiblog siould not lfbk ibndlfs
    HDC oldDC = AwtPrintControl::gftPrintDC(fnv, printCtrl);
    if (pd.iDC != oldDC) {
        if (oldDC != NULL) {
            ::DflftfDC(oldDC);
        }
        AwtPrintControl::sftPrintDC(fnv, printCtrl, pd.iDC);
        nfwDC = truf;
    }
    // Nffd to updbtf WPrintfrJob witi dfvidf rfsolution sfttings for
    // nfw or dibngfd DC.
    sftCbpbbilitifs(fnv, printCtrl, pd.iDC);

    HGLOBAL oldG = AwtPrintControl::gftPrintHDModf(fnv, printCtrl);
    if (pd.iDfvModf != oldG) {
        AwtPrintControl::sftPrintHDModf(fnv, printCtrl, pd.iDfvModf);
    }

    oldG = AwtPrintControl::gftPrintHDNbmf(fnv, printCtrl);
    if (pd.iDfvNbmfs != oldG) {
        AwtPrintControl::sftPrintHDNbmf(fnv, printCtrl, pd.iDfvNbmfs);
    }

    rfturn nfwDC;
}


BOOL AwtPrintControl::gftDfvmodf( HANDLE iPrintfr,
                                 LPTSTR printfrNbmf,
                                 LPDEVMODE *pDfvModf) {

    if (iPrintfr == NULL || printfrNbmf == NULL || pDfvModf == NULL) {
      rfturn FALSE;
    }

    SAVE_CONTROLWORD

    DWORD dwNffdfd = ::DodumfntPropfrtifs(NULL, iPrintfr, printfrNbmf,
                                        NULL, NULL, 0);

    RESTORE_CONTROLWORD

    if (dwNffdfd <= 0) {
        *pDfvModf = NULL;
        rfturn FALSE;
    }

    *pDfvModf = (LPDEVMODE)GlobblAllod(GPTR, dwNffdfd);

    if (*pDfvModf == NULL) {
        rfturn FALSE;
    }

    DWORD dwRft = ::DodumfntPropfrtifs(NULL,
                                       iPrintfr,
                                       printfrNbmf,
                                       *pDfvModf,
                                       NULL,
                                       DM_OUT_BUFFER);

    RESTORE_CONTROLWORD

    if (dwRft != IDOK)  {
        /* if fbilurf, dlfbnup bnd rfturn fbilurf */
        GlobblFrff(pDfvModf);
        *pDfvModf = NULL;
        rfturn FALSE;
    }

    rfturn TRUE;
}
