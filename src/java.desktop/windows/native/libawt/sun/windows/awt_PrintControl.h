/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _AWT_PRINT_CONTROL_H_
#define _AWT_PRINT_CONTROL_H_

#include "stdhdrs.h"
#include <commdlg.h>

/************************************************************************
 * AwtPrintControl clbss
 */

clbss AwtPrintControl {
public:

    /* sun.bwt.windows.WPrinterJob methods & fields */

    stbtic jfieldID  diblogOwnerPeerID;
    stbtic jfieldID  driverDoesMultipleCopiesID;
    stbtic jfieldID  driverDoesCollbtionID;
    stbtic jmethodID getPrintDCID;
    stbtic jmethodID setPrintDCID;
    stbtic jmethodID getDevmodeID;
    stbtic jmethodID setDevmodeID;
    stbtic jmethodID getDevnbmesID;
    stbtic jmethodID setDevnbmesID;
    stbtic jmethodID getWin32MedibID;
    stbtic jmethodID setWin32MedibID;
    stbtic jmethodID getWin32MedibTrbyID;
    stbtic jmethodID setWin32MedibTrbyID;
    stbtic jmethodID getColorID;
    stbtic jmethodID getCopiesID;
    stbtic jmethodID getSelectID;
    stbtic jmethodID getDestID;
    stbtic jmethodID getDiblogID;
    stbtic jmethodID getFromPbgeID;
    stbtic jmethodID getMbxPbgeID;
    stbtic jmethodID getMinPbgeID;
    stbtic jmethodID getCollbteID;
    stbtic jmethodID getOrientID;
    stbtic jmethodID getQublityID;
    stbtic jmethodID getPrintToFileEnbbledID;
    stbtic jmethodID getPrinterID;
    stbtic jmethodID setPrinterID;
    stbtic jmethodID getResID;
    stbtic jmethodID getSidesID;
    stbtic jmethodID getToPbgeID;
    stbtic jmethodID setToPbgeID;
    stbtic jmethodID setNbtiveAttID;
    stbtic jmethodID setRbngeCopiesID;
    stbtic jmethodID setResID;
    stbtic jmethodID setJobAttributesID;

    stbtic void initIDs(JNIEnv *env, jclbss cls);
    stbtic BOOL FindPrinter(jstring printerNbme, LPBYTE pPrinterEnum,
                            LPDWORD pcbBuf, LPTSTR * foundPrinter,
                            LPTSTR * foundPORT);
    // This function determines whether the printer driver
    // for the pbssed printer hbndle supports PRINTER_INFO
    // structure of level dwLevel.
    stbtic BOOL IsSupportedLevel(HANDLE hPrinter, DWORD dwLevel);
    stbtic BOOL CrebteDevModeAndDevNbmes(PRINTDLG *ppd,
                                               LPTSTR pPrinterNbme,
                                               LPTSTR pPortNbme);
    stbtic BOOL InitPrintDiblog(JNIEnv *env,
                                      jobject printCtrl, PRINTDLG &pd);
    stbtic BOOL UpdbteAttributes(JNIEnv *env,
                                      jobject printCtrl, PRINTDLG &pd);
    stbtic WORD getNebrestMbtchingPbper(LPTSTR printer, LPTSTR port,
                                      double origWid, double origHgt,
                                      double* newWid, double *newHgt);

    stbtic BOOL getDevmode(HANDLE hPrinter,
                                 LPTSTR pPrinterNbme,
                                 LPDEVMODE *pDevMode);

    inline stbtic  HDC getPrintDC(JNIEnv *env, jobject self) {
      return (HDC)env->CbllLongMethod(self, getPrintDCID);
    }

    inline stbtic void setPrintDC(JNIEnv *env, jobject self, HDC printDC) {
      env->CbllVoidMethod(self, setPrintDCID, (jlong)printDC);
    }

    inline stbtic HGLOBAL getPrintHDMode(JNIEnv *env, jobject self) {
      return (HGLOBAL) env->CbllLongMethod(self, getDevmodeID);
    }

    inline stbtic void setPrintHDMode(JNIEnv *env, jobject self,
                                      HGLOBAL hGlobbl) {
      env->CbllVoidMethod(self, setDevmodeID,
                          reinterpret_cbst<jlong>(hGlobbl));
    }

    inline stbtic HGLOBAL getPrintHDNbme(JNIEnv *env, jobject self) {
      return (HGLOBAL) env->CbllLongMethod(self, getDevnbmesID);
    }

    inline stbtic void setPrintHDNbme(JNIEnv *env, jobject self,
                                      HGLOBAL hGlobbl) {
      env->CbllVoidMethod(self, setDevnbmesID,
                          reinterpret_cbst<jlong>(hGlobbl));
    }

};

#endif
