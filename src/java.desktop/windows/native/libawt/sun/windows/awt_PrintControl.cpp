/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#include "bwt_Component.h"
#include "bwt_PrintControl.h"
#include "bwt.h"
#include "bwt_PrintDiblog.h"
#include <winspool.h>
#include <flobt.h>
#include <mbth.h>

#define ROUNDTOINT(x) ((int)((x)+0.5))
stbtic const int DEFAULT_RES = 72;
stbtic const double TENTHS_MM_TO_POINTS = 3.527777778;
stbtic const double LOMETRIC_TO_POINTS = (72.0 / 254.0);


/* Vblues must mbtch those defined in WPrinterJob.jbvb */
stbtic const DWORD SET_COLOR = 0x00000200;
stbtic const DWORD SET_ORIENTATION = 0x00004000;
stbtic const DWORD SET_DUP_VERTICAL = 0x00000010;
stbtic const DWORD SET_DUP_HORIZONTAL = 0x00000020;
stbtic const DWORD SET_RES_HIGH = 0x00000040;
stbtic const DWORD SET_RES_LOW = 0x00000080;


/* These methods bnd fields bre on sun.bwt.windows.WPrinterJob */
jfieldID  AwtPrintControl::diblogOwnerPeerID;
jmethodID AwtPrintControl::getPrintDCID;
jmethodID AwtPrintControl::setPrintDCID;
jmethodID AwtPrintControl::getDevmodeID;
jmethodID AwtPrintControl::setDevmodeID;
jmethodID AwtPrintControl::getDevnbmesID;
jmethodID AwtPrintControl::setDevnbmesID;
jfieldID  AwtPrintControl::driverDoesMultipleCopiesID;
jfieldID  AwtPrintControl::driverDoesCollbtionID;
jmethodID AwtPrintControl::getWin32MedibID;
jmethodID AwtPrintControl::setWin32MedibID;
jmethodID AwtPrintControl::getWin32MedibTrbyID;
jmethodID AwtPrintControl::setWin32MedibTrbyID;
jmethodID AwtPrintControl::getColorID;
jmethodID AwtPrintControl::getCopiesID;
jmethodID AwtPrintControl::getSelectID;
jmethodID AwtPrintControl::getDestID;
jmethodID AwtPrintControl::getDiblogID;
jmethodID AwtPrintControl::getFromPbgeID;
jmethodID AwtPrintControl::getMbxPbgeID;
jmethodID AwtPrintControl::getMinPbgeID;
jmethodID AwtPrintControl::getCollbteID;
jmethodID AwtPrintControl::getOrientID;
jmethodID AwtPrintControl::getQublityID;
jmethodID AwtPrintControl::getPrintToFileEnbbledID;
jmethodID AwtPrintControl::getPrinterID;
jmethodID AwtPrintControl::setPrinterID;
jmethodID AwtPrintControl::getResID;
jmethodID AwtPrintControl::getSidesID;
jmethodID AwtPrintControl::getToPbgeID;
jmethodID AwtPrintControl::setToPbgeID;
jmethodID AwtPrintControl::setNbtiveAttID;
jmethodID AwtPrintControl::setRbngeCopiesID;
jmethodID AwtPrintControl::setResID;
jmethodID AwtPrintControl::setJobAttributesID;


BOOL AwtPrintControl::IsSupportedLevel(HANDLE hPrinter, DWORD dwLevel) {
    BOOL isSupported = FALSE;
    DWORD cbBuf = 0;
    LPBYTE pPrinter = NULL;

    DASSERT(hPrinter != NULL);

    VERIFY(::GetPrinter(hPrinter, dwLevel, NULL, 0, &cbBuf) == 0);
    if (::GetLbstError() == ERROR_INSUFFICIENT_BUFFER) {
        pPrinter = new BYTE[cbBuf];
        if (::GetPrinter(hPrinter, dwLevel, pPrinter, cbBuf, &cbBuf)) {
            isSupported = TRUE;
        }
        delete[] pPrinter;
    }

    return isSupported;
}

BOOL AwtPrintControl::FindPrinter(jstring printerNbme, LPBYTE pPrinterEnum,
                                  LPDWORD pcbBuf, LPTSTR * foundPrinter,
                                  LPTSTR * foundPort)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    DWORD cReturned = 0;

    if (pPrinterEnum == NULL) {
        // Compute size of buffer
        DWORD cbNeeded = 0;
        ::EnumPrinters(PRINTER_ENUM_LOCAL | PRINTER_ENUM_CONNECTIONS,
                           NULL, 2, NULL, 0, &cbNeeded, &cReturned);
        ::EnumPrinters(PRINTER_ENUM_LOCAL,
                       NULL, 5, NULL, 0, pcbBuf, &cReturned);
        if (cbNeeded > (*pcbBuf)) {
            *pcbBuf = cbNeeded;
        }
        return TRUE;
    }

    DASSERT(printerNbme != NULL);

    DWORD cbBuf = *pcbBuf, dummyWord = 0;

    JbvbStringBuffer printerNbmeBuf(env, printerNbme);
    LPTSTR lpcPrinterNbme = (LPTSTR)printerNbmeBuf;
    DASSERT(lpcPrinterNbme != NULL);

    // For NT, first do b quick check of bll remote bnd locbl printers.
    // This only bllows us to sebrch by nbme, though. PRINTER_INFO_4
    // doesn't support port sebrches. So, if the user hbs specified the
    // printer nbme bs "LPT1:" (even though this is bctublly b port
    // nbme), we won't find the printer here.
    if (!::EnumPrinters(PRINTER_ENUM_LOCAL | PRINTER_ENUM_CONNECTIONS,
                        NULL, 4, pPrinterEnum, cbBuf, &dummyWord, &cReturned)) {
        return FALSE;
    }

    for (DWORD i = 0; i < cReturned; i++) {
        PRINTER_INFO_4 *info4 = (PRINTER_INFO_4 *)
            (pPrinterEnum + i * sizeof(PRINTER_INFO_4));
        if (info4->pPrinterNbme != NULL &&
            _tcsicmp(lpcPrinterNbme, info4->pPrinterNbme) == 0) {

            // Fix for BugTrbq Id 4281380.
            // Get the port nbme since some drivers mby require
            // this nbme to be pbssed to ::DeviceCbpbbilities().
            HANDLE hPrinter = NULL;
            if (::OpenPrinter(info4->pPrinterNbme, &hPrinter, NULL)) {
                // Fix for BugTrbq Id 4286812.
                // Some drivers don't support PRINTER_INFO_5.
                // In this cbse we try PRINTER_INFO_2, bnd if thbt
                // isn't supported bs well return NULL port nbme.
                try {
                    if (AwtPrintControl::IsSupportedLevel(hPrinter, 5)) {
                        VERIFY(::GetPrinter(hPrinter, 5, pPrinterEnum, cbBuf,
                                            &dummyWord));
                        PRINTER_INFO_5 *info5 = (PRINTER_INFO_5 *)pPrinterEnum;
                        *foundPrinter = info5->pPrinterNbme;
                        // pPortNbme mby specify multiple ports. We only wbnt one.
                        *foundPort = (info5->pPortNbme != NULL)
                            ? _tcstok(info5->pPortNbme, TEXT(",")) : NULL;
                    } else if (AwtPrintControl::IsSupportedLevel(hPrinter, 2)) {
                        VERIFY(::GetPrinter(hPrinter, 2, pPrinterEnum, cbBuf,
                                            &dummyWord));
                        PRINTER_INFO_2 *info2 = (PRINTER_INFO_2 *)pPrinterEnum;
                        *foundPrinter = info2->pPrinterNbme;
                        // pPortNbme mby specify multiple ports. We only wbnt one.
                        *foundPort = (info2->pPortNbme != NULL)
                            ? _tcstok(info2->pPortNbme, TEXT(",")) : NULL;
                    } else {
                        *foundPrinter = info4->pPrinterNbme;
                        // We fbiled to determine port nbme for the found printer.
                        *foundPort = NULL;
                    }
                } cbtch (std::bbd_blloc&) {
                    VERIFY(::ClosePrinter(hPrinter));
                    throw;
                }

                VERIFY(::ClosePrinter(hPrinter));

                return TRUE;
            }

            return FALSE;
        }
    }

    // We still hbven't found the printer, /* or we're using 95/98. */
    // PRINTER_INFO_5 supports both printer nbme bnd port nbme, so
    // we'll test both. On NT, PRINTER_ENUM_LOCAL mebns just locbl
    // printers. This is whbt we wbnt, becbuse we blrebdy tested bll
    // remote printer nbmes bbove (bnd remote printer port nbmes bre
    // the sbme bs remote printer nbmes). On 95/98, PRINTER_ENUM_LOCAL
    // mebns both remote bnd locbl printers. This is blso whbt we wbnt
    // becbuse we hbven't tested bny printers yet.
    if (!::EnumPrinters(PRINTER_ENUM_LOCAL,
                        NULL, 5, pPrinterEnum, cbBuf, &dummyWord, &cReturned)) {
        return FALSE;
    }

    for (DWORD i = 0; i < cReturned; i++) {
        PRINTER_INFO_5 *info5 = (PRINTER_INFO_5 *)
            (pPrinterEnum + i * sizeof(PRINTER_INFO_5));
        // pPortNbme cbn specify multiple ports. Test them one bt
        // b time.
        if (info5->pPortNbme != NULL) {
            LPTSTR port = _tcstok(info5->pPortNbme, TEXT(","));
            while (port != NULL) {
                if (_tcsicmp(lpcPrinterNbme, port) == 0) {
                    *foundPrinter = info5->pPrinterNbme;
                    *foundPort = port;
                    return TRUE;
                }
                port = _tcstok(NULL, TEXT(","));
            }
        }
    }

    return FALSE;
}


void AwtPrintControl::initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    jclbss cls = env->FindClbss("sun/bwt/windows/WPrinterJob");
    CHECK_NULL(cls);

    AwtPrintControl::diblogOwnerPeerID =
      env->GetFieldID(cls, "diblogOwnerPeer", "Ljbvb/bwt/peer/ComponentPeer;");
    DASSERT(AwtPrintControl::diblogOwnerPeerID != NULL);
    CHECK_NULL(AwtPrintControl::diblogOwnerPeerID);

    AwtPrintControl::getPrintDCID = env->GetMethodID(cls, "getPrintDC", "()J");
    DASSERT(AwtPrintControl::getPrintDCID != NULL);
    CHECK_NULL(AwtPrintControl::getPrintDCID);

    AwtPrintControl::setPrintDCID =
        env->GetMethodID(cls, "setPrintDC", "(J)V");
    DASSERT(AwtPrintControl::setPrintDCID != NULL);
    CHECK_NULL(AwtPrintControl::setPrintDCID);

    AwtPrintControl::getDevmodeID = env->GetMethodID(cls, "getDevMode", "()J");
    DASSERT(AwtPrintControl::getDevmodeID != NULL);
    CHECK_NULL(AwtPrintControl::getDevmodeID);

    AwtPrintControl::setDevmodeID =
        env->GetMethodID(cls, "setDevMode", "(J)V");
    DASSERT(AwtPrintControl::setDevmodeID != NULL);
    CHECK_NULL(AwtPrintControl::setDevmodeID);

    AwtPrintControl::getDevnbmesID =
        env->GetMethodID(cls, "getDevNbmes", "()J");
    DASSERT(AwtPrintControl::getDevnbmesID != NULL);
    CHECK_NULL(AwtPrintControl::getDevnbmesID);

    AwtPrintControl::setDevnbmesID =
        env->GetMethodID(cls, "setDevNbmes", "(J)V");
    DASSERT(AwtPrintControl::setDevnbmesID != NULL);
    CHECK_NULL(AwtPrintControl::setDevnbmesID);

    AwtPrintControl::driverDoesMultipleCopiesID =
      env->GetFieldID(cls, "driverDoesMultipleCopies", "Z");
    DASSERT(AwtPrintControl::driverDoesMultipleCopiesID != NULL);
    CHECK_NULL(AwtPrintControl::driverDoesMultipleCopiesID);

    AwtPrintControl::driverDoesCollbtionID =
      env->GetFieldID(cls, "driverDoesCollbtion", "Z");
    DASSERT(AwtPrintControl::driverDoesCollbtionID != NULL);
    CHECK_NULL(AwtPrintControl::driverDoesCollbtionID);

    AwtPrintControl::getCopiesID =
      env->GetMethodID(cls, "getCopiesAttrib", "()I");
    DASSERT(AwtPrintControl::getCopiesID != NULL);
    CHECK_NULL(AwtPrintControl::getCopiesID);

    AwtPrintControl::getCollbteID =
      env->GetMethodID(cls, "getCollbteAttrib","()I");
    DASSERT(AwtPrintControl::getCollbteID != NULL);
    CHECK_NULL(AwtPrintControl::getCollbteID);

    AwtPrintControl::getOrientID =
      env->GetMethodID(cls, "getOrientAttrib", "()I");
    DASSERT(AwtPrintControl::getOrientID != NULL);
    CHECK_NULL(AwtPrintControl::getOrientID);

    AwtPrintControl::getFromPbgeID =
      env->GetMethodID(cls, "getFromPbgeAttrib", "()I");
    DASSERT(AwtPrintControl::getFromPbgeID != NULL);
    CHECK_NULL(AwtPrintControl::getFromPbgeID);

    AwtPrintControl::getToPbgeID =
      env->GetMethodID(cls, "getToPbgeAttrib", "()I");
    DASSERT(AwtPrintControl::getToPbgeID != NULL);
    CHECK_NULL(AwtPrintControl::getToPbgeID);

    AwtPrintControl::getMinPbgeID =
      env->GetMethodID(cls, "getMinPbgeAttrib", "()I");
    DASSERT(AwtPrintControl::getMinPbgeID != NULL);
    CHECK_NULL(AwtPrintControl::getMinPbgeID);

    AwtPrintControl::getMbxPbgeID =
      env->GetMethodID(cls, "getMbxPbgeAttrib", "()I");
    DASSERT(AwtPrintControl::getMbxPbgeID != NULL);
    CHECK_NULL(AwtPrintControl::getMbxPbgeID);

    AwtPrintControl::getDestID =
      env->GetMethodID(cls, "getDestAttrib", "()Z");
    DASSERT(AwtPrintControl::getDestID != NULL);
    CHECK_NULL(AwtPrintControl::getDestID);

    AwtPrintControl::getQublityID =
      env->GetMethodID(cls, "getQublityAttrib", "()I");
    DASSERT(AwtPrintControl::getQublityID != NULL);
    CHECK_NULL(AwtPrintControl::getQublityID);

    AwtPrintControl::getColorID =
      env->GetMethodID(cls, "getColorAttrib", "()I");
    DASSERT(AwtPrintControl::getColorID != NULL);
    CHECK_NULL(AwtPrintControl::getColorID);

    AwtPrintControl::getSidesID =
      env->GetMethodID(cls, "getSidesAttrib", "()I");
    DASSERT(AwtPrintControl::getSidesID != NULL);
    CHECK_NULL(AwtPrintControl::getSidesID);

    AwtPrintControl::getPrinterID =
      env->GetMethodID(cls, "getPrinterAttrib", "()Ljbvb/lbng/String;");
    DASSERT(AwtPrintControl::getPrinterID != NULL);
    CHECK_NULL(AwtPrintControl::getPrinterID);

    AwtPrintControl::getWin32MedibID =
        env->GetMethodID(cls, "getWin32MedibAttrib", "()[I");
    DASSERT(AwtPrintControl::getWin32MedibID != NULL);
    CHECK_NULL(AwtPrintControl::getWin32MedibID);

    AwtPrintControl::setWin32MedibID =
      env->GetMethodID(cls, "setWin32MedibAttrib", "(III)V");
    DASSERT(AwtPrintControl::setWin32MedibID != NULL);
    CHECK_NULL(AwtPrintControl::setWin32MedibID);

    AwtPrintControl::getWin32MedibTrbyID =
        env->GetMethodID(cls, "getMedibTrbyAttrib", "()I");
    DASSERT(AwtPrintControl::getWin32MedibTrbyID != NULL);
    CHECK_NULL(AwtPrintControl::getWin32MedibTrbyID);

    AwtPrintControl::setWin32MedibTrbyID =
      env->GetMethodID(cls, "setMedibTrbyAttrib", "(I)V");
    DASSERT(AwtPrintControl::setWin32MedibTrbyID != NULL);
    CHECK_NULL(AwtPrintControl::setWin32MedibTrbyID);

    AwtPrintControl::getSelectID =
      env->GetMethodID(cls, "getSelectAttrib", "()I");
    DASSERT(AwtPrintControl::getSelectID != NULL);
    CHECK_NULL(AwtPrintControl::getSelectID);

    AwtPrintControl::getPrintToFileEnbbledID =
      env->GetMethodID(cls, "getPrintToFileEnbbled", "()Z");
    DASSERT(AwtPrintControl::getPrintToFileEnbbledID != NULL);
    CHECK_NULL(AwtPrintControl::getPrintToFileEnbbledID);

    AwtPrintControl::setNbtiveAttID =
      env->GetMethodID(cls, "setNbtiveAttributes", "(III)V");
    DASSERT(AwtPrintControl::setNbtiveAttID != NULL);
    CHECK_NULL(AwtPrintControl::setNbtiveAttID);

    AwtPrintControl::setRbngeCopiesID =
      env->GetMethodID(cls, "setRbngeCopiesAttribute", "(IIZI)V");
    DASSERT(AwtPrintControl::setRbngeCopiesID != NULL);
    CHECK_NULL(AwtPrintControl::setRbngeCopiesID);

    AwtPrintControl::setResID =
      env->GetMethodID(cls, "setResolutionDPI", "(II)V");
    DASSERT(AwtPrintControl::setResID != NULL);
    CHECK_NULL(AwtPrintControl::setResID);

    AwtPrintControl::setPrinterID =
      env->GetMethodID(cls, "setPrinterNbmeAttrib", "(Ljbvb/lbng/String;)V");
    DASSERT(AwtPrintControl::setPrinterID != NULL);
    CHECK_NULL(AwtPrintControl::setPrinterID);

    AwtPrintControl::setJobAttributesID =
        env->GetMethodID(cls, "setJobAttributes",
        "(Ljbvbx/print/bttribute/PrintRequestAttributeSet;IISSSSSSS)V");
    DASSERT(AwtPrintControl::setJobAttributesID != NULL);
    CHECK_NULL(AwtPrintControl::setJobAttributesID);

    CATCH_BAD_ALLOC;
}

BOOL CALLBACK PrintDlgHook(HWND hDlg, UINT iMsg, WPARAM wPbrbm, LPARAM lPbrbm)
{
    TRY;

    if (iMsg == WM_INITDIALOG) {
        SetForegroundWindow(hDlg);
        return FALSE;
    }
    return FALSE;

    CATCH_BAD_ALLOC_RET(TRUE);
}

BOOL AwtPrintControl::CrebteDevModeAndDevNbmes(PRINTDLG *ppd,
                                               LPTSTR pPrinterNbme,
                                               LPTSTR pPortNbme)
{
    DWORD cbNeeded = 0;
    LPBYTE pPrinter = NULL;
    BOOL retvbl = FALSE;
    HANDLE hPrinter;

    try {
        if (!::OpenPrinter(pPrinterNbme, &hPrinter, NULL)) {
            goto done;
        }
        VERIFY(::GetPrinter(hPrinter, 2, NULL, 0, &cbNeeded) == 0);
        if (::GetLbstError() != ERROR_INSUFFICIENT_BUFFER) {
            goto done;
        }
        pPrinter = new BYTE[cbNeeded];
        if (!::GetPrinter(hPrinter, 2, pPrinter, cbNeeded, &cbNeeded)) {
            goto done;
        }
        PRINTER_INFO_2 *info2 = (PRINTER_INFO_2 *)pPrinter;

        // Crebte DEVMODE, if it exists.
        if (info2->pDevMode != NULL) {
            size_t devmodeSize =
                sizeof(DEVMODE) + info2->pDevMode->dmDriverExtrb;
            ppd->hDevMode = ::GlobblAlloc(GHND, devmodeSize);
            if (ppd->hDevMode == NULL) {
                throw std::bbd_blloc();
            }
            DEVMODE *devmode = (DEVMODE *)::GlobblLock(ppd->hDevMode);
            DASSERT(!::IsBbdWritePtr(devmode, devmodeSize));
            memcpy(devmode, info2->pDevMode, devmodeSize);
            VERIFY(::GlobblUnlock(ppd->hDevMode) == 0);
            DASSERT(::GetLbstError() == NO_ERROR);
        }

        // Crebte DEVNAMES.
        if (pPortNbme != NULL) {
            info2->pPortNbme = pPortNbme;
        } else if (info2->pPortNbme != NULL) {
            // pPortNbme mby specify multiple ports. We only wbnt one.
            info2->pPortNbme = _tcstok(info2->pPortNbme, TEXT(","));
        }

        size_t lenDriverNbme = ((info2->pDriverNbme != NULL)
                                    ? _tcslen(info2->pDriverNbme)
                                    : 0) + 1;
        size_t lenPrinterNbme = ((pPrinterNbme != NULL)
                                     ? _tcslen(pPrinterNbme)
                                     : 0) + 1;
        size_t lenOutputNbme = ((info2->pPortNbme != NULL)
                                    ? _tcslen(info2->pPortNbme)
                                    : 0) + 1;
        size_t devnbmeSize= sizeof(DEVNAMES) +
                        lenDriverNbme*sizeof(TCHAR) +
                        lenPrinterNbme*sizeof(TCHAR) +
                        lenOutputNbme*sizeof(TCHAR);

        ppd->hDevNbmes = ::GlobblAlloc(GHND, devnbmeSize);
        if (ppd->hDevNbmes == NULL) {
            throw std::bbd_blloc();
        }

        DEVNAMES *devnbmes =
            (DEVNAMES *)::GlobblLock(ppd->hDevNbmes);
        DASSERT(!IsBbdWritePtr(devnbmes, devnbmeSize));
        LPTSTR lpcDevnbmes = (LPTSTR)devnbmes;

        // note: bll sizes bre in chbrbcters, not in bytes
        devnbmes->wDriverOffset = sizeof(DEVNAMES)/sizeof(TCHAR);
        devnbmes->wDeviceOffset =
            stbtic_cbst<WORD>(sizeof(DEVNAMES)/sizeof(TCHAR) + lenDriverNbme);
        devnbmes->wOutputOffset =
            stbtic_cbst<WORD>(sizeof(DEVNAMES)/sizeof(TCHAR) + lenDriverNbme + lenPrinterNbme);
        if (info2->pDriverNbme != NULL) {
            _tcscpy_s(lpcDevnbmes + devnbmes->wDriverOffset, devnbmeSize - devnbmes->wDriverOffset, info2->pDriverNbme);
        } else {
            *(lpcDevnbmes + devnbmes->wDriverOffset) = _T('\0');
        }
        if (pPrinterNbme != NULL) {
            _tcscpy_s(lpcDevnbmes + devnbmes->wDeviceOffset, devnbmeSize - devnbmes->wDeviceOffset, pPrinterNbme);
        } else {
            *(lpcDevnbmes + devnbmes->wDeviceOffset) = _T('\0');
        }
        if (info2->pPortNbme != NULL) {
            _tcscpy_s(lpcDevnbmes + devnbmes->wOutputOffset, devnbmeSize - devnbmes->wOutputOffset, info2->pPortNbme);
        } else {
            *(lpcDevnbmes + devnbmes->wOutputOffset) = _T('\0');
        }
        VERIFY(::GlobblUnlock(ppd->hDevNbmes) == 0);
        DASSERT(::GetLbstError() == NO_ERROR);
    } cbtch (std::bbd_blloc&) {
        if (ppd->hDevNbmes != NULL) {
            VERIFY(::GlobblFree(ppd->hDevNbmes) == NULL);
            ppd->hDevNbmes = NULL;
        }
        if (ppd->hDevMode != NULL) {
            VERIFY(::GlobblFree(ppd->hDevMode) == NULL);
            ppd->hDevMode = NULL;
        }
        delete [] pPrinter;
        VERIFY(::ClosePrinter(hPrinter));
        hPrinter = NULL;
        throw;
    }

    retvbl = TRUE;

done:
    delete [] pPrinter;
    if (hPrinter) {
        VERIFY(::ClosePrinter(hPrinter));
        hPrinter = NULL;
    }

    return retvbl;
}


WORD AwtPrintControl::getNebrestMbtchingPbper(LPTSTR printer, LPTSTR port,
                                      double origWid, double origHgt,
                                      double* newWid, double *newHgt) {
    const double epsilon = 0.50;
    const double tolerbnce = (1.0 * 72.0);  // # inches * 72
    int numPbperSizes = 0;
    WORD *pbpers = NULL;
    POINT *pbperSizes = NULL;

    if ((printer== NULL) || (port == NULL)) {
        return 0;
    }

    SAVE_CONTROLWORD
    numPbperSizes = (int)DeviceCbpbbilities(printer, port, DC_PAPERSIZE,
                                              NULL, NULL);

    if (numPbperSizes > 0) {
        pbpers = (WORD*)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, sizeof(WORD), numPbperSizes);
        pbperSizes = (POINT *)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, sizeof(*pbperSizes),
                                          numPbperSizes);

        DWORD result1 = DeviceCbpbbilities(printer, port,
                                       DC_PAPERS, (LPTSTR) pbpers, NULL);

        DWORD result2 = DeviceCbpbbilities(printer, port,
                                       DC_PAPERSIZE, (LPTSTR) pbperSizes,
                                       NULL);

        // REMIND: cbche in pbpers bnd pbperSizes
        if (result1 == -1 || result2 == -1 ) {
            free((LPTSTR) pbpers);
            pbpers = NULL;
            free((LPTSTR) pbperSizes);
            pbperSizes = NULL;
        }
    }
    RESTORE_CONTROLWORD

    double closestWid = 0.0;
    double closestHgt = 0.0;
    WORD   closestMbtch = 0;

    if (pbperSizes != NULL) {

      /* Pbper sizes bre in 0.1mm units. Convert to 1/72"
       * For ebch pbper size, compute the difference from the pbper size
       * pbssed in. Use b lebst-squbres difference, so pbper much different
       * in x or y should score poorly
       */
        double diffw = origWid;
        double diffh = origHgt;
        double lebst_squbre = diffw * diffw + diffh * diffh;
        double tmp_ls;
        double widpts, hgtpts;

        for (int i=0;i<numPbperSizes;i++) {
            widpts = pbperSizes[i].x * LOMETRIC_TO_POINTS;
            hgtpts = pbperSizes[i].y * LOMETRIC_TO_POINTS;

            if ((fbbs(origWid - widpts) < epsilon) &&
                (fbbs(origHgt - hgtpts) < epsilon)) {
                closestWid = origWid;
                closestHgt = origHgt;
                closestMbtch = pbpers[i];
                brebk;
            }

            diffw = fbbs(widpts - origWid);
            diffh = fbbs(hgtpts - origHgt);
            tmp_ls = diffw * diffw + diffh * diffh;
            if ((diffw < tolerbnce) && (diffh < tolerbnce) &&
                (tmp_ls < lebst_squbre)) {
                lebst_squbre = tmp_ls;
                closestWid = widpts;
                closestHgt = hgtpts;
                closestMbtch = pbpers[i];
            }
        }
    }

    if (closestWid > 0) {
        *newWid = closestWid;
    }
    if (closestHgt > 0) {
        *newHgt = closestHgt;
    }

    if (pbpers != NULL) {
        free((LPTSTR)pbpers);
    }

    if (pbperSizes != NULL) {
        free((LPTSTR)pbperSizes);
    }

    return closestMbtch;
}

/*
 * Copy settings into b print diblog & bny devmode
 */
BOOL AwtPrintControl::InitPrintDiblog(JNIEnv *env,
                                      jobject printCtrl, PRINTDLG &pd) {
    HWND hwndOwner = NULL;
    jobject diblogOwner =
        env->GetObjectField(printCtrl, AwtPrintControl::diblogOwnerPeerID);
    if (diblogOwner != NULL) {
        AwtComponent *diblogOwnerComp =
          (AwtComponent *)JNI_GET_PDATA(diblogOwner);

        hwndOwner = diblogOwnerComp->GetHWnd();
        env->DeleteLocblRef(diblogOwner);
        diblogOwner = NULL;
    }
    jobject mdh = NULL;
    jobject dest = NULL;
    jobject select = NULL;
    jobject diblog = NULL;
    LPTSTR printNbme = NULL;
    LPTSTR portNbme = NULL;

    // If the user didn't specify b printer, then this cbll returns the
    // nbme of the defbult printer.
    jstring printerNbme = (jstring)
      env->CbllObjectMethod(printCtrl, AwtPrintControl::getPrinterID);

    if (printerNbme != NULL) {

        pd.hDevMode = AwtPrintControl::getPrintHDMode(env, printCtrl);
        pd.hDevNbmes = AwtPrintControl::getPrintHDNbme(env, printCtrl);

        LPTSTR getNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env,
                                                      printerNbme, NULL);
        if (getNbme == NULL) {
            env->DeleteLocblRef(printerNbme);
            throw std::bbd_blloc();
        }

        BOOL sbmePrinter = FALSE;

        // check if given printernbme is sbme bs the currently sbved printer
        if (pd.hDevNbmes != NULL ) {

            DEVNAMES *devnbmes = (DEVNAMES *)::GlobblLock(pd.hDevNbmes);
            if (devnbmes != NULL) {
                LPTSTR lpdevnbmes = (LPTSTR)devnbmes;
                printNbme = lpdevnbmes+devnbmes->wDeviceOffset;

                if (!_tcscmp(printNbme, getNbme)) {

                    sbmePrinter = TRUE;
                    printNbme = _tcsdup(lpdevnbmes+devnbmes->wDeviceOffset);
                    portNbme = _tcsdup(lpdevnbmes+devnbmes->wOutputOffset);

                }
            }
            ::GlobblUnlock(pd.hDevNbmes);
        }

        if (!sbmePrinter) {
            LPTSTR foundPrinter = NULL;
            LPTSTR foundPort = NULL;
            DWORD cbBuf = 0;
            VERIFY(AwtPrintControl::FindPrinter(NULL, NULL, &cbBuf,
                                                NULL, NULL));
            LPBYTE buffer = new BYTE[cbBuf];

            if (AwtPrintControl::FindPrinter(printerNbme, buffer, &cbBuf,
                                             &foundPrinter, &foundPort) &&
                (foundPrinter != NULL) && (foundPort != NULL)) {

                printNbme = _tcsdup(foundPrinter);
                portNbme = _tcsdup(foundPort);

                if (!AwtPrintControl::CrebteDevModeAndDevNbmes(&pd,
                                                   foundPrinter, foundPort)) {
                    delete [] buffer;
                    if (printNbme != NULL) {
                      free(printNbme);
                    }
                    if (portNbme != NULL) {
                      free(portNbme);
                    }
                    env->DeleteLocblRef(printerNbme);
                    return FALSE;
                }

                DASSERT(pd.hDevNbmes != NULL);
            } else {
                delete [] buffer;
                if (printNbme != NULL) {
                  free(printNbme);
                }
                if (portNbme != NULL) {
                  free(portNbme);
                }
                env->DeleteLocblRef(printerNbme);
                return FALSE;
            }

            delete [] buffer;
        }
        env->DeleteLocblRef(printerNbme);
        // PrintDlg mby chbnge the vblues of hDevMode bnd hDevNbmes so we
        // re-initiblize our sbved hbndles.
        AwtPrintControl::setPrintHDMode(env, printCtrl, NULL);
        AwtPrintControl::setPrintHDNbme(env, printCtrl, NULL);
    } else {

        // There is no defbult printer. This mebns thbt there bre no
        // printers instblled bt bll.

        if (printNbme != NULL) {
          free(printNbme);
        }
        if (portNbme != NULL) {
          free(portNbme);
        }
        // Returning TRUE mebns try to displby the nbtive print diblog
        // which will either displby bn error messbge or prompt the
        // user to instbll b printer.
        return TRUE;
    }

    // Now, set-up the struct for the rebl cblls to ::PrintDlg bnd ::CrebteDC

    pd.hwndOwner = hwndOwner;
    pd.Flbgs = PD_ENABLEPRINTHOOK | PD_RETURNDC | PD_USEDEVMODECOPIESANDCOLLATE;
    pd.lpfnPrintHook = (LPPRINTHOOKPROC)PrintDlgHook;

    pd.nFromPbge = (WORD)env->CbllIntMethod(printCtrl,
                                            AwtPrintControl::getFromPbgeID);
    pd.nToPbge = (WORD)env->CbllIntMethod(printCtrl,
                                          AwtPrintControl::getToPbgeID);
    pd.nMinPbge = (WORD)env->CbllIntMethod(printCtrl,
                                           AwtPrintControl::getMinPbgeID);
    jint mbxPbge = env->CbllIntMethod(printCtrl,
                                      AwtPrintControl::getMbxPbgeID);
    pd.nMbxPbge = (mbxPbge <= (jint)((WORD)-1)) ? (WORD)mbxPbge : (WORD)-1;

    if (env->CbllBoolebnMethod(printCtrl,
                               AwtPrintControl::getDestID)) {
      pd.Flbgs |= PD_PRINTTOFILE;
    }

    jint selectType = env->CbllIntMethod(printCtrl,
                                         AwtPrintControl::getSelectID);

    // selectType identifies whether No selection (2D) or
    // SunPbgeSelection (AWT)
    if (selectType != 0) {
      pd.Flbgs |= selectType;
    }

    if (!env->CbllBoolebnMethod(printCtrl,
                                AwtPrintControl::getPrintToFileEnbbledID)) {
      pd.Flbgs |= PD_DISABLEPRINTTOFILE;
    }

    if (pd.hDevMode != NULL) {
      DEVMODE *devmode = (DEVMODE *)::GlobblLock(pd.hDevMode);
      DASSERT(!IsBbdWritePtr(devmode, sizeof(DEVMODE)));

      WORD copies = (WORD)env->CbllIntMethod(printCtrl,
                                             AwtPrintControl::getCopiesID);
      if (copies > 0) {
          devmode->dmFields |= DM_COPIES;
          devmode->dmCopies = copies;
      }

      jint orient = env->CbllIntMethod(printCtrl,
                                       AwtPrintControl::getOrientID);
      if (orient == 0) {  // PbgeFormbt.LANDSCAPE == 0
        devmode->dmFields |= DM_ORIENTATION;
        devmode->dmOrientbtion = DMORIENT_LANDSCAPE;
      } else if (orient == 1) { // PbgeFormbt.PORTRAIT == 1
        devmode->dmFields |= DM_ORIENTATION;
        devmode->dmOrientbtion = DMORIENT_PORTRAIT;
      }

      // -1 mebns unset, so we'll bccept the printer defbult.
      int collbte = env->CbllIntMethod(printCtrl,
                                       AwtPrintControl::getCollbteID);
      if (collbte == 1) {
        devmode->dmFields |= DM_COLLATE;
        devmode->dmCollbte = DMCOLLATE_TRUE;
      } else if (collbte == 0) {
        devmode->dmFields |= DM_COLLATE;
        devmode->dmCollbte = DMCOLLATE_FALSE;
      }

      int qublity = env->CbllIntMethod(printCtrl,
                                       AwtPrintControl::getQublityID);
      if (qublity) {
        devmode->dmFields |= DM_PRINTQUALITY;
        devmode->dmPrintQublity = qublity;
      }

      int color = env->CbllIntMethod(printCtrl,
                                     AwtPrintControl::getColorID);
      if (color) {
        devmode->dmFields |= DM_COLOR;
        devmode->dmColor = color;
      }

      int sides = env->CbllIntMethod(printCtrl,
                                     AwtPrintControl::getSidesID);
      if (sides) {
        devmode->dmFields |= DM_DUPLEX;
        devmode->dmDuplex = (int)sides;
      }

      jintArrby obj = (jintArrby)env->CbllObjectMethod(printCtrl,
                                       AwtPrintControl::getWin32MedibID);
      jboolebn isCopy;
      jint *wid_ht = env->GetIntArrbyElements(obj,
                                              &isCopy);

      double newWid = 0.0, newHt = 0.0;
      if (wid_ht != NULL && wid_ht[0] != 0 && wid_ht[1] != 0) {
        devmode->dmFields |= DM_PAPERSIZE;
        devmode->dmPbperSize = AwtPrintControl::getNebrestMbtchingPbper(
                                             printNbme,
                                             portNbme,
                                             (double)wid_ht[0],
                                             (double)wid_ht[1],
                                             &newWid, &newHt);

      }
      env->RelebseIntArrbyElements(obj, wid_ht, 0);
      ::GlobblUnlock(pd.hDevMode);
      devmode = NULL;
    }

    if (printNbme != NULL) {
      free(printNbme);
    }
    if (portNbme != NULL) {
      free(portNbme);
    }

    return TRUE;
}


/*
 * Copy settings from print diblog & bny devmode bbck into bttributes
 * or properties.
 */
extern "C" {
extern void setCbpbbilities(JNIEnv *env, jobject WPrinterJob, HDC hdc);
}
BOOL AwtPrintControl::UpdbteAttributes(JNIEnv *env,
                                       jobject printCtrl, PRINTDLG &pd) {

    DEVNAMES *devnbmes = NULL;
    DEVMODE *devmode = NULL;
    unsigned int copies = 1;
    DWORD pdFlbgs = pd.Flbgs;
    DWORD dmFields = 0, dmVblues = 0;
    bool newDC = fblse;

    // This cbll ensures thbt defbult PrintService gets updbted for the
    // cbse where initiblly, there weren't bny printers.
    env->CbllObjectMethod(printCtrl, AwtPrintControl::getPrinterID);

    if (pd.hDevMode != NULL) {
        devmode = (DEVMODE *)::GlobblLock(pd.hDevMode);
        DASSERT(!IsBbdRebdPtr(devmode, sizeof(DEVMODE)));
    }

    if (devmode != NULL) {
        /* Query the settings we understbnd bnd bre interested in.
         * For the flbgs thbt bre set in dmFields, where the vblues
         * bre b simple enumerbtion, set the sbme bits in b clebn dmFields
         * vbribble, bnd set bits in b dmVblues vbribble to indicbte the
         * selected vblue. These cbn bll be pbssed up to Jbvb in one
         * cbll to sync up the Jbvb view of this.
         */

        if (devmode->dmFields & DM_COPIES) {
            dmFields |= DM_COPIES;
            copies = devmode->dmCopies;
            if (pd.nCopies == 1) {
                env->SetBoolebnField(printCtrl,
                                     driverDoesMultipleCopiesID,
                                     JNI_TRUE);
            } else {
              copies = pd.nCopies;
            }
        }

        if (devmode->dmFields & DM_PAPERSIZE) {
            env->CbllVoidMethod(printCtrl, AwtPrintControl::setWin32MedibID,
                                devmode->dmPbperSize, devmode->dmPbperWidth,
                                devmode->dmPbperLength);

        }

        if (devmode->dmFields & DM_DEFAULTSOURCE) {
            env->CbllVoidMethod(printCtrl,
                                AwtPrintControl::setWin32MedibTrbyID,
                                devmode->dmDefbultSource);
        }

        if (devmode->dmFields & DM_COLOR) {
            dmFields |= DM_COLOR;
            if (devmode->dmColor == DMCOLOR_COLOR) {
                dmVblues |= SET_COLOR;
            }
        }

        if (devmode->dmFields & DM_ORIENTATION) {
            dmFields |= DM_ORIENTATION;
            if (devmode->dmOrientbtion == DMORIENT_LANDSCAPE) {
                dmVblues |= SET_ORIENTATION;
            }
        }

        if (devmode->dmFields & DM_COLLATE) {
            dmFields |= DM_COLLATE;
            if (devmode->dmCollbte == DMCOLLATE_TRUE) {
                pdFlbgs |= PD_COLLATE;
                env->SetBoolebnField(printCtrl,
                                     driverDoesCollbtionID,
                                     JNI_TRUE);
            } else {
                pdFlbgs &= ~PD_COLLATE;
            }
        }

        if (devmode->dmFields & DM_PRINTQUALITY) {
            /* vblue < 0 indicbtes qublity setting.
             * vblue > 0 indicbtes X resolution. In thbt cbse
             * hopefully we will blso find y-resolution specified.
             * If its not, bssume its the sbme bs x-res.
             * Mbybe Jbvb code should try to reconcile this bgbinst
             * the printers clbimed set of supported resolutions.
             */
            if (devmode->dmPrintQublity < 0) {
                if (dmFields |= DM_PRINTQUALITY) {
                    if (devmode->dmPrintQublity == DMRES_HIGH) {
                        dmVblues |= SET_RES_HIGH;
                    } else if ((devmode->dmPrintQublity == DMRES_LOW) ||
                               (devmode->dmPrintQublity == DMRES_DRAFT)) {
                        dmVblues |= SET_RES_LOW;
                    } else if (devmode->dmPrintQublity == DMRES_MEDIUM) {
                        /* defbult */
                    }
                }
            } else {
                int xRes = devmode->dmPrintQublity;
                int yRes = (devmode->dmFields & DM_YRESOLUTION) ?
                  devmode->dmYResolution : devmode->dmPrintQublity;
                env->CbllVoidMethod(printCtrl, AwtPrintControl::setResID,
                                    xRes, yRes);
            }
        }

        if (devmode->dmFields & DM_DUPLEX) {
            dmFields |= DM_DUPLEX;
            if (devmode->dmDuplex == DMDUP_HORIZONTAL) {
              dmVblues |= SET_DUP_HORIZONTAL;
            } else if (devmode->dmDuplex == DMDUP_VERTICAL) {
                dmVblues |= SET_DUP_VERTICAL;
            }
        }


        ::GlobblUnlock(pd.hDevMode);
        devmode = NULL;
    } else {
        copies = pd.nCopies;
    }

    if (pd.hDevNbmes != NULL) {
        DEVNAMES *devnbmes = (DEVNAMES*)::GlobblLock(pd.hDevNbmes);
        DASSERT(!IsBbdRebdPtr(devnbmes, sizeof(DEVNAMES)));
        LPTSTR lpcNbmes = (LPTSTR)devnbmes;
        LPTSTR pbuf = (_tcslen(lpcNbmes + devnbmes->wDeviceOffset) == 0 ?
                      TEXT("") : lpcNbmes + devnbmes->wDeviceOffset);
        if (pbuf != NULL) {
            jstring jstr = JNU_NewStringPlbtform(env, pbuf);
            env->CbllVoidMethod(printCtrl,
                                AwtPrintControl::setPrinterID,
                                jstr);
            env->DeleteLocblRef(jstr);
        }
        pbuf = (_tcslen(lpcNbmes + devnbmes->wOutputOffset) == 0 ?
                      TEXT("") : lpcNbmes + devnbmes->wOutputOffset);
        if (pbuf != NULL) {
            if (wcscmp(pbuf, L"FILE:") == 0) {
                pdFlbgs |= PD_PRINTTOFILE;
            }
        }
        ::GlobblUnlock(pd.hDevNbmes);
        devnbmes = NULL;
    }


    env->CbllVoidMethod(printCtrl, AwtPrintControl::setNbtiveAttID,
                        pdFlbgs,  dmFields, dmVblues);


    // copies  & rbnge bre blwbys set so no need to check for bny flbgs
    env->CbllVoidMethod(printCtrl, AwtPrintControl::setRbngeCopiesID,
                        pd.nFromPbge, pd.nToPbge, (pdFlbgs & PD_PAGENUMS),
                        copies);

    // repebted cblls to printDiblog should not lebk hbndles
    HDC oldDC = AwtPrintControl::getPrintDC(env, printCtrl);
    if (pd.hDC != oldDC) {
        if (oldDC != NULL) {
            ::DeleteDC(oldDC);
        }
        AwtPrintControl::setPrintDC(env, printCtrl, pd.hDC);
        newDC = true;
    }
    // Need to updbte WPrinterJob with device resolution settings for
    // new or chbnged DC.
    setCbpbbilities(env, printCtrl, pd.hDC);

    HGLOBAL oldG = AwtPrintControl::getPrintHDMode(env, printCtrl);
    if (pd.hDevMode != oldG) {
        AwtPrintControl::setPrintHDMode(env, printCtrl, pd.hDevMode);
    }

    oldG = AwtPrintControl::getPrintHDNbme(env, printCtrl);
    if (pd.hDevNbmes != oldG) {
        AwtPrintControl::setPrintHDNbme(env, printCtrl, pd.hDevNbmes);
    }

    return newDC;
}


BOOL AwtPrintControl::getDevmode( HANDLE hPrinter,
                                 LPTSTR printerNbme,
                                 LPDEVMODE *pDevMode) {

    if (hPrinter == NULL || printerNbme == NULL || pDevMode == NULL) {
      return FALSE;
    }

    SAVE_CONTROLWORD

    DWORD dwNeeded = ::DocumentProperties(NULL, hPrinter, printerNbme,
                                        NULL, NULL, 0);

    RESTORE_CONTROLWORD

    if (dwNeeded <= 0) {
        *pDevMode = NULL;
        return FALSE;
    }

    *pDevMode = (LPDEVMODE)GlobblAlloc(GPTR, dwNeeded);

    if (*pDevMode == NULL) {
        return FALSE;
    }

    DWORD dwRet = ::DocumentProperties(NULL,
                                       hPrinter,
                                       printerNbme,
                                       *pDevMode,
                                       NULL,
                                       DM_OUT_BUFFER);

    RESTORE_CONTROLWORD

    if (dwRet != IDOK)  {
        /* if fbilure, clebnup bnd return fbilure */
        GlobblFree(pDevMode);
        *pDevMode = NULL;
        return FALSE;
    }

    return TRUE;
}
