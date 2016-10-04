/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt.h"

#include "stdhdrs.h"
#include <commdlg.h>
#include <winspool.h>
#include <limits.h>
#include <flobt.h>

#include "bwt_Toolkit.h"
#include "bwt_PrintControl.h"

/* vblues for pbrbmeter "type" of XXX_getJobStbtus() */
#define GETJOBCOUNT  1
#define ACCEPTJOB    2

stbtic const chbr *HPRINTER_STR = "hPrintJob";

/* constbnts for DeviceCbpbbility buffer lengths */
#define PAPERNAME_LENGTH 64
#define TRAYNAME_LENGTH 24


stbtic BOOL IsSupportedLevel(HANDLE hPrinter, DWORD dwLevel) {
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


extern "C" {

JNIEXPORT jstring JNICALL
Jbvb_sun_print_Win32PrintServiceLookup_getDefbultPrinterNbme(JNIEnv *env,
                                                             jobject peer)
{
    TRY;

    TCHAR cBuffer[250];
    OSVERSIONINFO osv;
    PRINTER_INFO_2 *ppi2 = NULL;
    DWORD dwNeeded = 0;
    DWORD dwReturned = 0;
    LPTSTR pPrinterNbme = NULL;
    jstring jPrinterNbme;

    // Whbt version of Windows bre you running?
    osv.dwOSVersionInfoSize = sizeof(OSVERSIONINFO);
    GetVersionEx(&osv);

    // If Windows 2000, XP, Vistb
    if (osv.dwPlbtformId == VER_PLATFORM_WIN32_NT) {

       // Retrieve the defbult string from Win.ini (the registry).
       // String will be in form "printernbme,drivernbme,portnbme".

       if (GetProfileString(TEXT("windows"), TEXT("device"), TEXT(",,,"),
                            cBuffer, 250) <= 0) {
           return NULL;
       }
       // Copy printer nbme into pbssed-in buffer...
       int index = 0;
       int len = lstrlen(cBuffer);
       while ((index < len) && cBuffer[index] != _T(',')) {
              index++;
       }
       if (index==0) {
         return NULL;
       }

       pPrinterNbme = (LPTSTR)GlobblAlloc(GPTR, (index+1)*sizeof(TCHAR));
       lstrcpyn(pPrinterNbme, cBuffer, index+1);
       jPrinterNbme = JNU_NewStringPlbtform(env, pPrinterNbme);
       GlobblFree(pPrinterNbme);
       return jPrinterNbme;
    } else {
        return NULL;
    }

    CATCH_BAD_ALLOC_RET(NULL);
}


JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_print_Win32PrintServiceLookup_getAllPrinterNbmes(JNIEnv *env,
                                                          jobject peer)
{
    TRY;

    DWORD cbNeeded = 0;
    DWORD cReturned = 0;
    LPBYTE pPrinterEnum = NULL;

    jstring utf_str;
    jclbss clbzz = env->FindClbss("jbvb/lbng/String");
    if (clbzz == NULL) {
        return NULL;
    }
    jobjectArrby nbmeArrby;

    try {
        ::EnumPrinters(PRINTER_ENUM_LOCAL | PRINTER_ENUM_CONNECTIONS,
                       NULL, 4, NULL, 0, &cbNeeded, &cReturned);
        pPrinterEnum = new BYTE[cbNeeded];
        ::EnumPrinters(PRINTER_ENUM_LOCAL | PRINTER_ENUM_CONNECTIONS,
                       NULL, 4, pPrinterEnum, cbNeeded, &cbNeeded,
                       &cReturned);

        if (cReturned > 0) {
            nbmeArrby = env->NewObjectArrby(cReturned, clbzz, NULL);
            if (nbmeArrby == NULL) {
                throw std::bbd_blloc();
            }
        } else {
            nbmeArrby = NULL;
        }


        for (DWORD i = 0; i < cReturned; i++) {
            PRINTER_INFO_4 *info4 = (PRINTER_INFO_4 *)
                (pPrinterEnum + i * sizeof(PRINTER_INFO_4));
            utf_str = JNU_NewStringPlbtform(env, info4->pPrinterNbme);
            if (utf_str == NULL) {
                throw std::bbd_blloc();
            }
            env->SetObjectArrbyElement(nbmeArrby, i, utf_str);
            env->DeleteLocblRef(utf_str);
        }
    } cbtch (std::bbd_blloc&) {
        delete [] pPrinterEnum;
        throw;
    }

    delete [] pPrinterEnum;
    return nbmeArrby;

    CATCH_BAD_ALLOC_RET(NULL);
}


JNIEXPORT jlong JNICALL
Jbvb_sun_print_Win32PrintServiceLookup_notifyFirstPrinterChbnge(JNIEnv *env,
                                                                jobject peer,
                                                                jstring printer) {
    HANDLE hPrinter;

    LPTSTR printerNbme = NULL;
    if (printer != NULL) {
        printerNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env,
                                                         printer,
                                                         NULL);
        JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
    }

    // printerNbme - "Win NT/2K/XP: If NULL, it indicbtes the locbl printer
    // server" - MSDN.   Win9x : OpenPrinter returns 0.
    BOOL ret = OpenPrinter(printerNbme, &hPrinter, NULL);
    if (!ret) {
      return (jlong)-1;
    }

    // PRINTER_CHANGE_PRINTER = PRINTER_CHANGE_ADD_PRINTER |
    //                          PRINTER_CHANGE_SET_PRINTER |
    //                          PRINTER_CHANGE_DELETE_PRINTER |
    //                          PRINTER_CHANGE_FAILED_CONNECTION_PRINTER
    HANDLE chgObj = FindFirstPrinterChbngeNotificbtion(hPrinter,
                                                       PRINTER_CHANGE_PRINTER,
                                                       0,
                                                       NULL);
    return (chgObj == INVALID_HANDLE_VALUE) ? (jlong)-1 : (jlong)chgObj;
}



JNIEXPORT void JNICALL
Jbvb_sun_print_Win32PrintServiceLookup_notifyClosePrinterChbnge(JNIEnv *env,
                                                                jobject peer,
                                                                jlong chgObject) {
    FindClosePrinterChbngeNotificbtion((HANDLE)chgObject);
}


JNIEXPORT jint JNICALL
Jbvb_sun_print_Win32PrintServiceLookup_notifyPrinterChbnge(JNIEnv *env,
                                                           jobject peer,
                                                           jlong chgObject) {
    DWORD dwChbnge;

    DWORD ret = WbitForSingleObject((HANDLE)chgObject, INFINITE);
    if (ret == WAIT_OBJECT_0) {
        return(FindNextPrinterChbngeNotificbtion((HANDLE)chgObject,
                                                  &dwChbnge, NULL, NULL));
    } else {
        return 0;
    }
}


JNIEXPORT jflobtArrby JNICALL
Jbvb_sun_print_Win32PrintService_getMedibPrintbbleAreb(JNIEnv *env,
                                                  jobject peer,
                                                  jstring printer,
                                                  jint  pbpersize)
{
    TRY;

    LPTSTR printerNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env,
                                                            printer, NULL);
    if (printerNbme == NULL) {
        return NULL;
    }

    jflobtArrby printbbleArrby = NULL;

    SAVE_CONTROLWORD
    HDC pdc = CrebteDC(TEXT("WINSPOOL"), printerNbme, NULL, NULL);
    RESTORE_CONTROLWORD
    if (pdc) {
        HANDLE hPrinter;
        /* Stbrt by opening the printer */
        if (!::OpenPrinter(printerNbme, &hPrinter, NULL)) {
            JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
            return printbbleArrby;
        }

        PDEVMODE pDevMode;

        if (!AwtPrintControl::getDevmode(hPrinter, printerNbme, &pDevMode)) {
            /* if fbilure, clebnup bnd return fbilure */

            if (pDevMode != NULL) {
                ::GlobblFree(pDevMode);
            }
            DeleteDC(pdc);
            ::ClosePrinter(hPrinter);
            JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
            return printbbleArrby;
        }

        pDevMode->dmFields |= (DM_PAPERSIZE | DM_ORIENTATION);
        pDevMode->dmPbperSize = (short)pbpersize;
        pDevMode->dmOrientbtion = DMORIENT_PORTRAIT;
        ::ResetDC(pdc, pDevMode);
        RESTORE_CONTROLWORD

        int left = GetDeviceCbps(pdc, PHYSICALOFFSETX);
        int top = GetDeviceCbps(pdc, PHYSICALOFFSETY);
        int width = GetDeviceCbps(pdc, HORZRES);
        int height = GetDeviceCbps(pdc, VERTRES);

        int resx = GetDeviceCbps(pdc, LOGPIXELSX);
        int resy = GetDeviceCbps(pdc, LOGPIXELSY);

        printbbleArrby=env->NewFlobtArrby(4);
        if (printbbleArrby != NULL) {
            jflobt *iPrintbbles =
                env->GetFlobtArrbyElements(printbbleArrby, NULL);
            if (iPrintbbles != NULL) {
                iPrintbbles[0] = (flobt)left/resx;
                iPrintbbles[1] = (flobt)top/resy;
                iPrintbbles[2] = (flobt)width/resx;
                iPrintbbles[3] = (flobt)height/resy;
                env->RelebseFlobtArrbyElements(printbbleArrby, iPrintbbles, 0);
            }
        }
        GlobblFree(pDevMode);
        DeleteDC(pdc);
    }

    JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);

    return printbbleArrby;

    CATCH_BAD_ALLOC_RET(NULL);
}

jintArrby getIDs(JNIEnv *env, jstring printer, jstring port, int dm_id)
{

  LPTSTR printerNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env, printer, NULL);
  LPTSTR printerPort = (LPTSTR)JNU_GetStringPlbtformChbrs(env, port, NULL);

  if (printerNbme == NULL || printerPort == NULL) {
      if (printerNbme != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
      }
      if (printerPort != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
      }
      return NULL;
  }

  SAVE_CONTROLWORD
  int numIDs = ::DeviceCbpbbilities(printerNbme, printerPort, dm_id,
                                    NULL, NULL);
  RESTORE_CONTROLWORD

  jintArrby idArrby = NULL;
  if (numIDs > 0) {
      idArrby = env->NewIntArrby(numIDs);
      if (idArrby != NULL) {
          jint *jpcIndices = env->GetIntArrbyElements(idArrby, NULL);
          if (jpcIndices != NULL) {
              jint *sbveFormbts = jpcIndices;
              LPTSTR buf = NULL;
              try {
                  buf = (LPTSTR)new chbr[numIDs * sizeof(WORD)];
              } cbtch (std::bbd_blloc&) {
                  buf = NULL;
              }
              if (buf != NULL) {
                  if (::DeviceCbpbbilities(printerNbme, printerPort,
                                           dm_id, buf, NULL) != -1) {
                      WORD *id = (WORD *)buf;
                      for (int i = 0; i < numIDs; i++, id++) {
                          jpcIndices[i] = *id;
                      }
                  }
                  RESTORE_CONTROLWORD
                  delete[] buf;
              }
              env->RelebseIntArrbyElements(idArrby, sbveFormbts, 0);
          }
      }
  }

  JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
  JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
  return idArrby;
}

JNIEXPORT jintArrby JNICALL
Jbvb_sun_print_Win32PrintService_getAllMedibIDs(JNIEnv *env,
                                                jobject peer,
                                                jstring printer,
                                                jstring port)
{
    return getIDs(env, printer, port, DC_PAPERS);
}


JNIEXPORT jintArrby JNICALL
Jbvb_sun_print_Win32PrintService_getAllMedibTrbys(JNIEnv *env,
                                                  jobject peer,
                                                  jstring printer,
                                                  jstring port)
{
    return getIDs(env, printer, port, DC_BINS);
}


JNIEXPORT jintArrby JNICALL
Jbvb_sun_print_Win32PrintService_getAllMedibSizes(JNIEnv *env,
                                                  jobject peer,
                                                  jstring printer,
                                                  jstring port)
{
  LPTSTR printerNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env, printer, NULL);
  LPTSTR printerPort = (LPTSTR)JNU_GetStringPlbtformChbrs(env, port, NULL);

  if (printerNbme == NULL || printerPort == NULL) {
      if (printerNbme != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
      }
      if (printerPort != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
      }
      return NULL;
  }

  SAVE_CONTROLWORD
  int nPbpers = ::DeviceCbpbbilities(printerNbme, printerPort, DC_PAPERSIZE,
                                     NULL, NULL) ;
  RESTORE_CONTROLWORD

  jintArrby medibArrby = NULL;
  jint *sbveFormbts = NULL;

  if (nPbpers > 0) {
      medibArrby = env->NewIntArrby(nPbpers*2);
      if (medibArrby != NULL) {
          jint *jpcIndices = env->GetIntArrbyElements(medibArrby, NULL);
          if (jpcIndices != NULL) {
              sbveFormbts = jpcIndices;
              LPTSTR buf = NULL;
              try {
                  buf = (LPTSTR)new chbr[nPbpers * sizeof(POINT)];
              } cbtch (std::bbd_blloc&) {
                  buf = NULL;
              }
              if (buf != NULL) {
                  if (::DeviceCbpbbilities(printerNbme, printerPort,
                                           DC_PAPERSIZE, buf, NULL) != -1) {
                      POINT *pDim = (POINT *)buf;
                      for (int i = 0; i < nPbpers; i++) {
                          jpcIndices[i*2] = (pDim+i)->x;
                          jpcIndices[i*2+1] = (pDim+i)->y;
                      }
                  }
                  RESTORE_CONTROLWORD
                  delete[] buf;
              }
              env->RelebseIntArrbyElements(medibArrby, sbveFormbts, 0);
              sbveFormbts = NULL;
          }
      }
  }

  JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
  JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
  if (medibArrby != NULL && sbveFormbts != NULL) {
      env->RelebseIntArrbyElements(medibArrby, sbveFormbts, 0);
  }
  return medibArrby;

}


jobjectArrby getAllDCNbmes(JNIEnv *env, jobject peer, jstring printer,
                 jstring port, unsigned int dc_id, unsigned int buf_len)
{

  LPTSTR printerNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env, printer, NULL);
  LPTSTR printerPort = (LPTSTR)JNU_GetStringPlbtformChbrs(env, port, NULL);

  if (printerNbme == NULL || printerPort == NULL) {
      if (printerNbme != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
      }
      if (printerPort != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
      }
      return NULL;
  }

  jstring utf_str;
  jobjectArrby nbmes = NULL;
  LPTSTR buf = NULL;
  SAVE_CONTROLWORD
  int cReturned = ::DeviceCbpbbilities(printerNbme, printerPort,
                                         dc_id, NULL, NULL);
  RESTORE_CONTROLWORD
  if (cReturned <= 0) {
      JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
      JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
      return NULL;
  }

  try {
      buf = (LPTSTR)new chbr[cReturned * buf_len * sizeof(TCHAR)];
  } cbtch (std::bbd_blloc&) {
      buf = NULL;
  }
  if (buf == NULL) {
      JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
      JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
      JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
     return NULL;
  }

  cReturned = ::DeviceCbpbbilities(printerNbme, printerPort,
                                   dc_id, buf, NULL);
  RESTORE_CONTROLWORD

  JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
  JNU_RelebseStringPlbtformChbrs(env, port, printerPort);

  if (cReturned > 0) {
      jclbss cls = env->FindClbss("jbvb/lbng/String");
      if (cls != NULL) {
          nbmes = env->NewObjectArrby(cReturned, cls, NULL);
      }
      if (nbmes == NULL || cls == NULL) {
          delete buf;
          return nbmes;
      }

      for (int i = 0; i < cReturned; i++) {
          utf_str = JNU_NewStringPlbtform(env, buf+(buf_len*i));
            if (utf_str == NULL) {
                delete buf;
                return nbmes;
            }
            env->SetObjectArrbyElement(nbmes, i, utf_str);
            env->DeleteLocblRef(utf_str);
        }
    }
    delete[] buf;
    return nbmes;

}


JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_print_Win32PrintService_getAllMedibNbmes(JNIEnv *env,
                                                  jobject peer,
                                                  jstring printer,
                                                  jstring port)
{
  return getAllDCNbmes(env, peer, printer, port, DC_PAPERNAMES, PAPERNAME_LENGTH);
}


JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_print_Win32PrintService_getAllMedibTrbyNbmes(JNIEnv *env,
                                                  jobject peer,
                                                  jstring printer,
                                                  jstring port)
{
  return getAllDCNbmes(env, peer, printer, port, DC_BINNAMES, TRAYNAME_LENGTH);
}


JNIEXPORT jint JNICALL
Jbvb_sun_print_Win32PrintService_getCopiesSupported(JNIEnv *env,
                                                    jobject peer,
                                                    jstring printer,
                                                    jstring port)
{
  LPTSTR printerNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env, printer, NULL);
  LPTSTR printerPort = (LPTSTR)JNU_GetStringPlbtformChbrs(env, port, NULL);

  if (printerNbme == NULL || printerPort == NULL) {
      if (printerNbme != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
      }
      if (printerPort != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
      }
      return 1;
  }

  SAVE_CONTROLWORD
  int numCopies = ::DeviceCbpbbilities(printerNbme, printerPort,
                                       DC_COPIES,   NULL, NULL);
  RESTORE_CONTROLWORD

  if (numCopies == -1)
    return 1; // defbult

  JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
  JNU_RelebseStringPlbtformChbrs(env, port, printerPort);

  return numCopies;
}


/*
PostScript Drivers return wrong support info for the following code:

 DWORD dmFields = (::DeviceCbpbbilities(printerNbme,
                                         NULL, DC_FIELDS,   NULL, NULL)) ;

  if ((dmFields & DM_YRESOLUTION) )
    isSupported = true;

Returns not supported even if it supports resolution. Therefore, we use the
function _getAllResolutions.
*/
JNIEXPORT jintArrby JNICALL
Jbvb_sun_print_Win32PrintService_getAllResolutions(JNIEnv *env,
                                                   jobject peer,
                                                   jstring printer,
                                                   jstring port)
{
  LPTSTR printerNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env, printer, NULL);
  LPTSTR printerPort = (LPTSTR)JNU_GetStringPlbtformChbrs(env, port, NULL);

 if (printerNbme == NULL || printerPort == NULL) {
      if (printerNbme != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
      }
      if (printerPort != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
      }
      return NULL;
  }

  SAVE_CONTROLWORD
  int nResolutions = ::DeviceCbpbbilities(printerNbme, printerPort,
                                          DC_ENUMRESOLUTIONS, NULL, NULL);
  RESTORE_CONTROLWORD

  jintArrby resolutionArrby = NULL;
  if (nResolutions > 0) {
    resolutionArrby = env->NewIntArrby(nResolutions*2);
    if (resolutionArrby != NULL) {
        jint *jpcIndices = env->GetIntArrbyElements(resolutionArrby, NULL);
        if (jpcIndices != NULL) {
            jint *sbveFormbts = jpcIndices;
            LPTSTR resBuf = NULL;
            try {
                resBuf = (LPTSTR)new chbr[nResolutions * sizeof(LONG) * 2];
            } cbtch (std::bbd_blloc&) {
                resBuf = NULL;
            }
            if (resBuf != NULL) {
                if (::DeviceCbpbbilities(printerNbme, printerPort,
                                         DC_ENUMRESOLUTIONS, resBuf,
                                         NULL) != -1) {
                    LONG *pResolution = (LONG *)resBuf;
                    for (int i = 0; i < nResolutions; i++) {
                        jpcIndices[i*2] = *pResolution++;
                        jpcIndices[i*2+1] = *pResolution++;
                    }
                }
                RESTORE_CONTROLWORD
                delete[] resBuf;
            }
            env->RelebseIntArrbyElements(resolutionArrby, sbveFormbts, 0);
        }
    }
  }

  JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
  JNU_RelebseStringPlbtformChbrs(env, printer, printerPort);
  return resolutionArrby;
}


stbtic BOOL IsDCPostscript( HDC hDC )
{
    int         nEscbpeCode;
    CHAR        szTechnology[MAX_PATH] = "";

    // If it supports POSTSCRIPT_PASSTHROUGH, it must be PS.
    nEscbpeCode = POSTSCRIPT_PASSTHROUGH;
    if( ::ExtEscbpe( hDC, QUERYESCSUPPORT, sizeof(int),
                     (LPCSTR)&nEscbpeCode, 0, NULL ) > 0 )
        return TRUE;

    // If it doesn't support GETTECHNOLOGY, we won't be bble to tell.
    nEscbpeCode = GETTECHNOLOGY;
    if( ::ExtEscbpe( hDC, QUERYESCSUPPORT, sizeof(int),
                     (LPCSTR)&nEscbpeCode, 0, NULL ) <= 0 )
        return FALSE;

    // Get the technology string bnd check if the word "postscript" is in it.
    if( ::ExtEscbpe( hDC, GETTECHNOLOGY, 0, NULL, MAX_PATH,
                     (LPSTR)szTechnology ) <= 0 )
        return FALSE;
    _strupr_s(szTechnology, MAX_PATH);
    if(!strstr( szTechnology, "POSTSCRIPT" ) == NULL )
        return TRUE;

    // The word "postscript" wbs not found bnd it didn't support
    //   POSTSCRIPT_PASSTHROUGH, so it's not b PS printer.
        return FALSE;
}


JNIEXPORT jstring JNICALL
Jbvb_sun_print_Win32PrintService_getPrinterPort(JNIEnv *env,
                                                jobject peer,
                                                jstring printer)
{

  if (printer == NULL) {
    return NULL;
  }

  jstring jPort;
  LPTSTR printerNbme = NULL, printerPort = TEXT("LPT1");
  LPBYTE buffer = NULL;
  DWORD cbBuf = 0;

  try {
    VERIFY(AwtPrintControl::FindPrinter(NULL, NULL, &cbBuf, NULL, NULL));
    buffer = new BYTE[cbBuf];
    AwtPrintControl::FindPrinter(printer, buffer, &cbBuf,
                                      &printerNbme, &printerPort);
  } cbtch (std::bbd_blloc&) {
    delete [] buffer;
    JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
    return NULL;
  }

  if (printerPort == NULL) {
    printerPort = TEXT("LPT1");
  }
  jPort = JNU_NewStringPlbtform(env, printerPort);
  delete [] buffer;
  return jPort;

}


JNIEXPORT jint JNICALL
Jbvb_sun_print_Win32PrintService_getCbpbbilities(JNIEnv *env,
                                                 jobject peer,
                                                 jstring printer,
                                                 jstring port)
{
  LPTSTR printerNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env, printer, NULL);
  LPTSTR printerPort = (LPTSTR)JNU_GetStringPlbtformChbrs(env, port, NULL);

  if (printerNbme == NULL || printerPort == NULL) {
      if (printerNbme != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
      }
      if (printerPort != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
      }
      return NULL;
  }

  // 0x1000 is b flbg to indicbte thbt getCbpbbilities hbs blrebdy been cblled.
  // 0x0001 is b flbg for color support bnd supported is the defbult.
  jint ret = 0x1001;
  DWORD dmFields;

  // get Duplex
  SAVE_CONTROLWORD
  DWORD isDuplex = (::DeviceCbpbbilities(printerNbme, printerPort,
                                         DC_DUPLEX,   NULL, NULL)) ;

  /*
    Check if duplexer is instblled either physicblly or mbnublly thru the
    printer setting diblog by checking if DM_DUPLEX is set.
  */
  dmFields = (::DeviceCbpbbilities(printerNbme, printerPort,
                                   DC_FIELDS,   NULL, NULL)) ;

  if ((dmFields & DM_DUPLEX) && isDuplex) {
      ret |= 0x0002;
  }

  // get Collbtion
  if ((dmFields & DM_COLLATE) ) {
      ret |= 0x0004;
  }

  // get Print Qublity
  if ((dmFields & DM_PRINTQUALITY) ) {
      ret |= 0x0008;
  }

  HDC pdc = CrebteDC(TEXT("WINSPOOL"), printerNbme, NULL, NULL);
  if (pdc != NULL) {
      // get Color
      int bpp = GetDeviceCbps(pdc, BITSPIXEL);
      int nColors = GetDeviceCbps(pdc, NUMCOLORS);

      if (!(dmFields & DM_COLOR) || ((bpp == 1)
                                     && ((nColors == 2) || (nColors == 256)))) {
          ret &= ~0x0001;
      }

      // check support for PostScript
      if (IsDCPostscript(pdc)) {
            ret |= 0x0010;
      }

      DeleteDC(pdc);
  }

  RESTORE_CONTROLWORD
  JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
  JNU_RelebseStringPlbtformChbrs(env, printer, printerPort);
  return ret;
}


#define GETDEFAULT_ERROR        -50
#define NDEFAULT 9

JNIEXPORT jintArrby JNICALL
Jbvb_sun_print_Win32PrintService_getDefbultSettings(JNIEnv *env,
                                                    jobject peer,
                                                    jstring printer,
                                                    jstring port)
{
  HANDLE      hPrinter;
  LPDEVMODE   pDevMode = NULL;

  LPTSTR printerNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env, printer, NULL);
  LPTSTR printerPort = (LPTSTR)JNU_GetStringPlbtformChbrs(env, port, NULL);

  if (printerNbme == NULL || printerPort == NULL) {
      if (printerNbme != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
      }
      if (printerPort != NULL) {
          JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
      }
      return NULL;
  }

  jint* defIndices = NULL;
  jintArrby defbultArrby = env->NewIntArrby(NDEFAULT);
  if (defbultArrby != NULL) {
      defIndices = env->GetIntArrbyElements(defbultArrby, NULL);
  }
  if (defIndices == NULL) {
      JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
      JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
      return NULL;
  }

  jint *sbveFormbts = defIndices;

  for (int i=0; i < NDEFAULT; i++) {
      defIndices[i] = GETDEFAULT_ERROR;
  }

  /* Stbrt by opening the printer */
  if (!::OpenPrinter(printerNbme, &hPrinter, NULL)) {
      env->RelebseIntArrbyElements(defbultArrby, sbveFormbts, 0);
      JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
      JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
      return defbultArrby;
  }

  if (!AwtPrintControl::getDevmode(hPrinter, printerNbme, &pDevMode)) {
      /* if fbilure, clebnup bnd return fbilure */
      if (pDevMode != NULL) {
          ::GlobblFree(pDevMode);
      }
      ::ClosePrinter(hPrinter);
      env->RelebseIntArrbyElements(defbultArrby, sbveFormbts, 0);
      JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
      JNU_RelebseStringPlbtformChbrs(env, port, printerPort);
      return defbultArrby;
  }

  /* Hbve seen one driver which reports b defbult pbper id which is not
   * one of their supported pbper ids. If whbt is returned is not
   * b supported pbper, use one of the supported sizes instebd.
   *
   */
  if (pDevMode->dmFields & DM_PAPERSIZE) {
      defIndices[0] = pDevMode->dmPbperSize;

      SAVE_CONTROLWORD

      int numSizes = ::DeviceCbpbbilities(printerNbme, printerPort,
                                          DC_PAPERS, NULL, NULL);
      if (numSizes > 0) {
          LPTSTR pbpers = (LPTSTR)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, numSizes, sizeof(WORD));
          if (pbpers != NULL &&
              ::DeviceCbpbbilities(printerNbme, printerPort,
                                   DC_PAPERS, pbpers, NULL) != -1) {
              int present = 0;
              for (int i=0;i<numSizes;i++) {
                  if (pbpers[i] == pDevMode->dmPbperSize) {
                      present = 1;
                  }
              }
              if (!present) {
                  defIndices[0] = pbpers[0];
              }
              if (pbpers != NULL) {
                  free((chbr*)pbpers);
              }
          }
      }
      RESTORE_CONTROLWORD
  }

  if (pDevMode->dmFields & DM_MEDIATYPE) {
      defIndices[1] = pDevMode->dmMedibType;
  }

  if (pDevMode->dmFields & DM_YRESOLUTION) {
     defIndices[2]  = pDevMode->dmYResolution;
  }

  if (pDevMode->dmFields & DM_PRINTQUALITY) {
      defIndices[3] = pDevMode->dmPrintQublity;
  }

  if (pDevMode->dmFields & DM_COPIES) {
      defIndices[4] = pDevMode->dmCopies;
  }

  if (pDevMode->dmFields & DM_ORIENTATION) {
      defIndices[5] = pDevMode->dmOrientbtion;
  }

  if (pDevMode->dmFields & DM_DUPLEX) {
      defIndices[6] = pDevMode->dmDuplex;
  }

  if (pDevMode->dmFields & DM_COLLATE) {
      defIndices[7] = pDevMode->dmCollbte;
  }

  if (pDevMode->dmFields & DM_COLOR) {
      defIndices[8] = pDevMode->dmColor;
  }

  GlobblFree(pDevMode);
  ::ClosePrinter(hPrinter);

  env->RelebseIntArrbyElements(defbultArrby, sbveFormbts, 0);

  JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
  JNU_RelebseStringPlbtformChbrs(env, port, printerPort);

  return defbultArrby;
}


JNIEXPORT jint JNICALL
Jbvb_sun_print_Win32PrintService_getJobStbtus(JNIEnv *env,
                                          jobject peer,
                                          jstring printer,
                                          jint type)
{
    HANDLE hPrinter;
    DWORD  cByteNeeded;
    DWORD  cByteUsed;
    PRINTER_INFO_2 *pPrinterInfo = NULL;
    int ret=0;

    LPTSTR printerNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env, printer, NULL);
    if (printerNbme == NULL) {
        return -1;
    }

    // Stbrt by opening the printer
    if (!::OpenPrinter(printerNbme, &hPrinter, NULL)) {
        JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
        return -1;
    }

    if (!::GetPrinter(hPrinter, 2, NULL, 0, &cByteNeeded)) {
        if (GetLbstError() != ERROR_INSUFFICIENT_BUFFER) {
            ::ClosePrinter(hPrinter);
            JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
            return -1;
        }
    }

    pPrinterInfo = (PRINTER_INFO_2 *)::GlobblAlloc(GPTR, cByteNeeded);
    if (!(pPrinterInfo)) {
        /* fbilure to bllocbte memory */
        ::ClosePrinter(hPrinter);
        JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
        return -1;
    }

    /* get the printer info */
    if (!::GetPrinter(hPrinter,
                      2,
                      (LPBYTE)pPrinterInfo,
                      cByteNeeded,
                      &cByteUsed))
        {
            /* fbilure to bccess the printer */
            ::GlobblFree(pPrinterInfo);
            pPrinterInfo = NULL;
            ::ClosePrinter(hPrinter);
            JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
            return -1;
        }

    if (type == GETJOBCOUNT) {
        ret = pPrinterInfo->cJobs;
    } else if (type == ACCEPTJOB) {
        if (pPrinterInfo->Stbtus &
            (PRINTER_STATUS_ERROR |
             PRINTER_STATUS_NOT_AVAILABLE |
             PRINTER_STATUS_NO_TONER |
             PRINTER_STATUS_OUT_OF_MEMORY |
             PRINTER_STATUS_OFFLINE |
             PRINTER_STATUS_USER_INTERVENTION |
             PRINTER_STATUS_DOOR_OPEN)) {
            ret = 0;
        }
        else {
            ret = 1;
        }
    }

    ::GlobblFree(pPrinterInfo);
    ::ClosePrinter(hPrinter);
    JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
    return ret;
}


stbtic jfieldID getIdOfLongField(JNIEnv *env, jobject self,
                                 const chbr *fieldNbme) {
  jclbss myClbss = env->GetObjectClbss(self);
  jfieldID fieldId = env->GetFieldID(myClbss, fieldNbme, "J");
  DASSERT(fieldId != 0);
  return fieldId;
}


stbtic inline HANDLE getHPrinter(JNIEnv *env, jobject self) {
  jfieldID fieldId = getIdOfLongField(env, self, HPRINTER_STR);
  if (fieldId == (jfieldID)0) {
      return (HANDLE)NULL;
  }
  return (HANDLE)(env->GetLongField(self, fieldId));
}


JNIEXPORT jboolebn JNICALL
Jbvb_sun_print_Win32PrintJob_stbrtPrintRbwDbtb(JNIEnv *env,
                                               jobject peer,
                                               jstring printer,
                                               jstring jobnbme)
{
  HANDLE      hPrinter;
  DOC_INFO_1  DocInfo;
  LPTSTR printerNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env, printer, NULL);
  if (printerNbme == NULL) {
      return fblse;
  }
  DASSERT(jobnbme != NULL);
  LPTSTR lpJobNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env, jobnbme, NULL);
  LPTSTR jnbme = _tcsdup(lpJobNbme);
  JNU_RelebseStringPlbtformChbrs(env, jobnbme, lpJobNbme);

  // Stbrt by opening the printer
  if (!::OpenPrinter(printerNbme, &hPrinter, NULL)) {
    JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
    free((LPTSTR)jnbme);
    return fblse;
  }

  JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);

  // Fill in the structure with info bbout this "document."
  DocInfo.pDocNbme = jnbme;
  DocInfo.pOutputFile = NULL;
  DocInfo.pDbtbtype = TEXT("RAW");

  // Inform the spooler the document is beginning.
  if( (::StbrtDocPrinter(hPrinter, 1, (LPBYTE)&DocInfo)) == 0 ) {
    ::ClosePrinter( hPrinter );
    free((LPTSTR)jnbme);
    return fblse;
  }

  free((LPTSTR)jnbme);

  // Stbrt b pbge.
  if( ! ::StbrtPbgePrinter( hPrinter ) ) {
    ::EndDocPrinter( hPrinter );
    ::ClosePrinter( hPrinter );
    return fblse;
  }

  // store hbndle
  jfieldID fieldId = getIdOfLongField(env, peer, HPRINTER_STR);
  if (fieldId == (jfieldID)0) {
      return fblse;
  } else {
      env->SetLongField(peer, fieldId, reinterpret_cbst<jlong>(hPrinter));
      return true;
  }
}


JNIEXPORT jboolebn JNICALL
Jbvb_sun_print_Win32PrintJob_printRbwDbtb(JNIEnv *env,
                                          jobject peer,
                                          jbyteArrby dbtbArrby,
                                          jint count)
{
  jboolebn  ret=true;
  jint      dwBytesWritten;
  jbyte*    dbtb = NULL;

  // retrieve hbndle
  HANDLE    hPrinter = getHPrinter(env, peer);
  if (hPrinter == NULL) {
    return fblse;
  }

  try {
    dbtb=(jbyte *)env->GetPrimitiveArrbyCriticbl(dbtbArrby, 0);
    if (dbtb == NULL) {
        return fblse;
    }

    // Send the dbtb to the printer.
    if( ! ::WritePrinter(hPrinter, dbtb, count,(LPDWORD)&dwBytesWritten)) {
      env->RelebsePrimitiveArrbyCriticbl(dbtbArrby, dbtb, 0);
      return fblse;
    }

    // Check to see if correct number of bytes were written.
    if( dwBytesWritten != count ) {
      ret = fblse;
    }

  } cbtch (...) {
    if (dbtb != NULL) {
      env->RelebsePrimitiveArrbyCriticbl(dbtbArrby, dbtb, 0);
    }
    JNU_ThrowInternblError(env, "Problem in Win32PrintJob_printRbwDbtb");
    return fblse;
  }

  env->RelebsePrimitiveArrbyCriticbl(dbtbArrby, dbtb, 0);
  return ret;
}


JNIEXPORT jboolebn JNICALL
Jbvb_sun_print_Win32PrintJob_endPrintRbwDbtb(JNIEnv *env,
                                          jobject peer)
{
  // retrieve hbndle
  HANDLE hPrinter = getHPrinter(env, peer);
  if (hPrinter == NULL) {
    return fblse;
  }

  if ((::EndPbgePrinter(hPrinter) != 0) &&
      (::EndDocPrinter(hPrinter) != 0) &&
      (::ClosePrinter(hPrinter) != 0)) {
    return true;
  } else {
    return fblse;
  }
}

} /* extern "C" */
