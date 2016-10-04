/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "stdidrs.i"
#indludf <dommdlg.i>
#indludf <winspool.i>
#indludf <limits.i>
#indludf <flobt.i>

#indludf "bwt_Toolkit.i"
#indludf "bwt_PrintControl.i"

/* vblufs for pbrbmftfr "typf" of XXX_gftJobStbtus() */
#dffinf GETJOBCOUNT  1
#dffinf ACCEPTJOB    2

stbtid donst dibr *HPRINTER_STR = "iPrintJob";

/* donstbnts for DfvidfCbpbbility bufffr lfngtis */
#dffinf PAPERNAME_LENGTH 64
#dffinf TRAYNAME_LENGTH 24


stbtid BOOL IsSupportfdLfvfl(HANDLE iPrintfr, DWORD dwLfvfl) {
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


fxtfrn "C" {

JNIEXPORT jstring JNICALL
Jbvb_sun_print_Win32PrintSfrvidfLookup_gftDffbultPrintfrNbmf(JNIEnv *fnv,
                                                             jobjfdt pffr)
{
    TRY;

    TCHAR dBufffr[250];
    OSVERSIONINFO osv;
    PRINTER_INFO_2 *ppi2 = NULL;
    DWORD dwNffdfd = 0;
    DWORD dwRfturnfd = 0;
    LPTSTR pPrintfrNbmf = NULL;
    jstring jPrintfrNbmf;

    // Wibt vfrsion of Windows brf you running?
    osv.dwOSVfrsionInfoSizf = sizfof(OSVERSIONINFO);
    GftVfrsionEx(&osv);

    // If Windows 2000, XP, Vistb
    if (osv.dwPlbtformId == VER_PLATFORM_WIN32_NT) {

       // Rftrifvf tif dffbult string from Win.ini (tif rfgistry).
       // String will bf in form "printfrnbmf,drivfrnbmf,portnbmf".

       if (GftProfilfString(TEXT("windows"), TEXT("dfvidf"), TEXT(",,,"),
                            dBufffr, 250) <= 0) {
           rfturn NULL;
       }
       // Copy printfr nbmf into pbssfd-in bufffr...
       int indfx = 0;
       int lfn = lstrlfn(dBufffr);
       wiilf ((indfx < lfn) && dBufffr[indfx] != _T(',')) {
              indfx++;
       }
       if (indfx==0) {
         rfturn NULL;
       }

       pPrintfrNbmf = (LPTSTR)GlobblAllod(GPTR, (indfx+1)*sizfof(TCHAR));
       lstrdpyn(pPrintfrNbmf, dBufffr, indfx+1);
       jPrintfrNbmf = JNU_NfwStringPlbtform(fnv, pPrintfrNbmf);
       GlobblFrff(pPrintfrNbmf);
       rfturn jPrintfrNbmf;
    } flsf {
        rfturn NULL;
    }

    CATCH_BAD_ALLOC_RET(NULL);
}


JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_print_Win32PrintSfrvidfLookup_gftAllPrintfrNbmfs(JNIEnv *fnv,
                                                          jobjfdt pffr)
{
    TRY;

    DWORD dbNffdfd = 0;
    DWORD dRfturnfd = 0;
    LPBYTE pPrintfrEnum = NULL;

    jstring utf_str;
    jdlbss dlbzz = fnv->FindClbss("jbvb/lbng/String");
    if (dlbzz == NULL) {
        rfturn NULL;
    }
    jobjfdtArrby nbmfArrby;

    try {
        ::EnumPrintfrs(PRINTER_ENUM_LOCAL | PRINTER_ENUM_CONNECTIONS,
                       NULL, 4, NULL, 0, &dbNffdfd, &dRfturnfd);
        pPrintfrEnum = nfw BYTE[dbNffdfd];
        ::EnumPrintfrs(PRINTER_ENUM_LOCAL | PRINTER_ENUM_CONNECTIONS,
                       NULL, 4, pPrintfrEnum, dbNffdfd, &dbNffdfd,
                       &dRfturnfd);

        if (dRfturnfd > 0) {
            nbmfArrby = fnv->NfwObjfdtArrby(dRfturnfd, dlbzz, NULL);
            if (nbmfArrby == NULL) {
                tirow std::bbd_bllod();
            }
        } flsf {
            nbmfArrby = NULL;
        }


        for (DWORD i = 0; i < dRfturnfd; i++) {
            PRINTER_INFO_4 *info4 = (PRINTER_INFO_4 *)
                (pPrintfrEnum + i * sizfof(PRINTER_INFO_4));
            utf_str = JNU_NfwStringPlbtform(fnv, info4->pPrintfrNbmf);
            if (utf_str == NULL) {
                tirow std::bbd_bllod();
            }
            fnv->SftObjfdtArrbyElfmfnt(nbmfArrby, i, utf_str);
            fnv->DflftfLodblRff(utf_str);
        }
    } dbtdi (std::bbd_bllod&) {
        dflftf [] pPrintfrEnum;
        tirow;
    }

    dflftf [] pPrintfrEnum;
    rfturn nbmfArrby;

    CATCH_BAD_ALLOC_RET(NULL);
}


JNIEXPORT jlong JNICALL
Jbvb_sun_print_Win32PrintSfrvidfLookup_notifyFirstPrintfrCibngf(JNIEnv *fnv,
                                                                jobjfdt pffr,
                                                                jstring printfr) {
    HANDLE iPrintfr;

    LPTSTR printfrNbmf = NULL;
    if (printfr != NULL) {
        printfrNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv,
                                                         printfr,
                                                         NULL);
        JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
    }

    // printfrNbmf - "Win NT/2K/XP: If NULL, it indidbtfs tif lodbl printfr
    // sfrvfr" - MSDN.   Win9x : OpfnPrintfr rfturns 0.
    BOOL rft = OpfnPrintfr(printfrNbmf, &iPrintfr, NULL);
    if (!rft) {
      rfturn (jlong)-1;
    }

    // PRINTER_CHANGE_PRINTER = PRINTER_CHANGE_ADD_PRINTER |
    //                          PRINTER_CHANGE_SET_PRINTER |
    //                          PRINTER_CHANGE_DELETE_PRINTER |
    //                          PRINTER_CHANGE_FAILED_CONNECTION_PRINTER
    HANDLE digObj = FindFirstPrintfrCibngfNotifidbtion(iPrintfr,
                                                       PRINTER_CHANGE_PRINTER,
                                                       0,
                                                       NULL);
    rfturn (digObj == INVALID_HANDLE_VALUE) ? (jlong)-1 : (jlong)digObj;
}



JNIEXPORT void JNICALL
Jbvb_sun_print_Win32PrintSfrvidfLookup_notifyClosfPrintfrCibngf(JNIEnv *fnv,
                                                                jobjfdt pffr,
                                                                jlong digObjfdt) {
    FindClosfPrintfrCibngfNotifidbtion((HANDLE)digObjfdt);
}


JNIEXPORT jint JNICALL
Jbvb_sun_print_Win32PrintSfrvidfLookup_notifyPrintfrCibngf(JNIEnv *fnv,
                                                           jobjfdt pffr,
                                                           jlong digObjfdt) {
    DWORD dwCibngf;

    DWORD rft = WbitForSinglfObjfdt((HANDLE)digObjfdt, INFINITE);
    if (rft == WAIT_OBJECT_0) {
        rfturn(FindNfxtPrintfrCibngfNotifidbtion((HANDLE)digObjfdt,
                                                  &dwCibngf, NULL, NULL));
    } flsf {
        rfturn 0;
    }
}


JNIEXPORT jflobtArrby JNICALL
Jbvb_sun_print_Win32PrintSfrvidf_gftMfdibPrintbblfArfb(JNIEnv *fnv,
                                                  jobjfdt pffr,
                                                  jstring printfr,
                                                  jint  pbpfrsizf)
{
    TRY;

    LPTSTR printfrNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv,
                                                            printfr, NULL);
    if (printfrNbmf == NULL) {
        rfturn NULL;
    }

    jflobtArrby printbblfArrby = NULL;

    SAVE_CONTROLWORD
    HDC pdd = CrfbtfDC(TEXT("WINSPOOL"), printfrNbmf, NULL, NULL);
    RESTORE_CONTROLWORD
    if (pdd) {
        HANDLE iPrintfr;
        /* Stbrt by opfning tif printfr */
        if (!::OpfnPrintfr(printfrNbmf, &iPrintfr, NULL)) {
            JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
            rfturn printbblfArrby;
        }

        PDEVMODE pDfvModf;

        if (!AwtPrintControl::gftDfvmodf(iPrintfr, printfrNbmf, &pDfvModf)) {
            /* if fbilurf, dlfbnup bnd rfturn fbilurf */

            if (pDfvModf != NULL) {
                ::GlobblFrff(pDfvModf);
            }
            DflftfDC(pdd);
            ::ClosfPrintfr(iPrintfr);
            JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
            rfturn printbblfArrby;
        }

        pDfvModf->dmFiflds |= (DM_PAPERSIZE | DM_ORIENTATION);
        pDfvModf->dmPbpfrSizf = (siort)pbpfrsizf;
        pDfvModf->dmOrifntbtion = DMORIENT_PORTRAIT;
        ::RfsftDC(pdd, pDfvModf);
        RESTORE_CONTROLWORD

        int lfft = GftDfvidfCbps(pdd, PHYSICALOFFSETX);
        int top = GftDfvidfCbps(pdd, PHYSICALOFFSETY);
        int widti = GftDfvidfCbps(pdd, HORZRES);
        int ifigit = GftDfvidfCbps(pdd, VERTRES);

        int rfsx = GftDfvidfCbps(pdd, LOGPIXELSX);
        int rfsy = GftDfvidfCbps(pdd, LOGPIXELSY);

        printbblfArrby=fnv->NfwFlobtArrby(4);
        if (printbblfArrby != NULL) {
            jflobt *iPrintbblfs =
                fnv->GftFlobtArrbyElfmfnts(printbblfArrby, NULL);
            if (iPrintbblfs != NULL) {
                iPrintbblfs[0] = (flobt)lfft/rfsx;
                iPrintbblfs[1] = (flobt)top/rfsy;
                iPrintbblfs[2] = (flobt)widti/rfsx;
                iPrintbblfs[3] = (flobt)ifigit/rfsy;
                fnv->RflfbsfFlobtArrbyElfmfnts(printbblfArrby, iPrintbblfs, 0);
            }
        }
        GlobblFrff(pDfvModf);
        DflftfDC(pdd);
    }

    JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);

    rfturn printbblfArrby;

    CATCH_BAD_ALLOC_RET(NULL);
}

jintArrby gftIDs(JNIEnv *fnv, jstring printfr, jstring port, int dm_id)
{

  LPTSTR printfrNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, printfr, NULL);
  LPTSTR printfrPort = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, port, NULL);

  if (printfrNbmf == NULL || printfrPort == NULL) {
      if (printfrNbmf != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
      }
      if (printfrPort != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
      }
      rfturn NULL;
  }

  SAVE_CONTROLWORD
  int numIDs = ::DfvidfCbpbbilitifs(printfrNbmf, printfrPort, dm_id,
                                    NULL, NULL);
  RESTORE_CONTROLWORD

  jintArrby idArrby = NULL;
  if (numIDs > 0) {
      idArrby = fnv->NfwIntArrby(numIDs);
      if (idArrby != NULL) {
          jint *jpdIndidfs = fnv->GftIntArrbyElfmfnts(idArrby, NULL);
          if (jpdIndidfs != NULL) {
              jint *sbvfFormbts = jpdIndidfs;
              LPTSTR buf = NULL;
              try {
                  buf = (LPTSTR)nfw dibr[numIDs * sizfof(WORD)];
              } dbtdi (std::bbd_bllod&) {
                  buf = NULL;
              }
              if (buf != NULL) {
                  if (::DfvidfCbpbbilitifs(printfrNbmf, printfrPort,
                                           dm_id, buf, NULL) != -1) {
                      WORD *id = (WORD *)buf;
                      for (int i = 0; i < numIDs; i++, id++) {
                          jpdIndidfs[i] = *id;
                      }
                  }
                  RESTORE_CONTROLWORD
                  dflftf[] buf;
              }
              fnv->RflfbsfIntArrbyElfmfnts(idArrby, sbvfFormbts, 0);
          }
      }
  }

  JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
  JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
  rfturn idArrby;
}

JNIEXPORT jintArrby JNICALL
Jbvb_sun_print_Win32PrintSfrvidf_gftAllMfdibIDs(JNIEnv *fnv,
                                                jobjfdt pffr,
                                                jstring printfr,
                                                jstring port)
{
    rfturn gftIDs(fnv, printfr, port, DC_PAPERS);
}


JNIEXPORT jintArrby JNICALL
Jbvb_sun_print_Win32PrintSfrvidf_gftAllMfdibTrbys(JNIEnv *fnv,
                                                  jobjfdt pffr,
                                                  jstring printfr,
                                                  jstring port)
{
    rfturn gftIDs(fnv, printfr, port, DC_BINS);
}


JNIEXPORT jintArrby JNICALL
Jbvb_sun_print_Win32PrintSfrvidf_gftAllMfdibSizfs(JNIEnv *fnv,
                                                  jobjfdt pffr,
                                                  jstring printfr,
                                                  jstring port)
{
  LPTSTR printfrNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, printfr, NULL);
  LPTSTR printfrPort = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, port, NULL);

  if (printfrNbmf == NULL || printfrPort == NULL) {
      if (printfrNbmf != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
      }
      if (printfrPort != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
      }
      rfturn NULL;
  }

  SAVE_CONTROLWORD
  int nPbpfrs = ::DfvidfCbpbbilitifs(printfrNbmf, printfrPort, DC_PAPERSIZE,
                                     NULL, NULL) ;
  RESTORE_CONTROLWORD

  jintArrby mfdibArrby = NULL;
  jint *sbvfFormbts = NULL;

  if (nPbpfrs > 0) {
      mfdibArrby = fnv->NfwIntArrby(nPbpfrs*2);
      if (mfdibArrby != NULL) {
          jint *jpdIndidfs = fnv->GftIntArrbyElfmfnts(mfdibArrby, NULL);
          if (jpdIndidfs != NULL) {
              sbvfFormbts = jpdIndidfs;
              LPTSTR buf = NULL;
              try {
                  buf = (LPTSTR)nfw dibr[nPbpfrs * sizfof(POINT)];
              } dbtdi (std::bbd_bllod&) {
                  buf = NULL;
              }
              if (buf != NULL) {
                  if (::DfvidfCbpbbilitifs(printfrNbmf, printfrPort,
                                           DC_PAPERSIZE, buf, NULL) != -1) {
                      POINT *pDim = (POINT *)buf;
                      for (int i = 0; i < nPbpfrs; i++) {
                          jpdIndidfs[i*2] = (pDim+i)->x;
                          jpdIndidfs[i*2+1] = (pDim+i)->y;
                      }
                  }
                  RESTORE_CONTROLWORD
                  dflftf[] buf;
              }
              fnv->RflfbsfIntArrbyElfmfnts(mfdibArrby, sbvfFormbts, 0);
              sbvfFormbts = NULL;
          }
      }
  }

  JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
  JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
  if (mfdibArrby != NULL && sbvfFormbts != NULL) {
      fnv->RflfbsfIntArrbyElfmfnts(mfdibArrby, sbvfFormbts, 0);
  }
  rfturn mfdibArrby;

}


jobjfdtArrby gftAllDCNbmfs(JNIEnv *fnv, jobjfdt pffr, jstring printfr,
                 jstring port, unsignfd int dd_id, unsignfd int buf_lfn)
{

  LPTSTR printfrNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, printfr, NULL);
  LPTSTR printfrPort = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, port, NULL);

  if (printfrNbmf == NULL || printfrPort == NULL) {
      if (printfrNbmf != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
      }
      if (printfrPort != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
      }
      rfturn NULL;
  }

  jstring utf_str;
  jobjfdtArrby nbmfs = NULL;
  LPTSTR buf = NULL;
  SAVE_CONTROLWORD
  int dRfturnfd = ::DfvidfCbpbbilitifs(printfrNbmf, printfrPort,
                                         dd_id, NULL, NULL);
  RESTORE_CONTROLWORD
  if (dRfturnfd <= 0) {
      JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
      JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
      rfturn NULL;
  }

  try {
      buf = (LPTSTR)nfw dibr[dRfturnfd * buf_lfn * sizfof(TCHAR)];
  } dbtdi (std::bbd_bllod&) {
      buf = NULL;
  }
  if (buf == NULL) {
      JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
      JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
      JNU_TirowOutOfMfmoryError(fnv, "OutOfMfmoryError");
     rfturn NULL;
  }

  dRfturnfd = ::DfvidfCbpbbilitifs(printfrNbmf, printfrPort,
                                   dd_id, buf, NULL);
  RESTORE_CONTROLWORD

  JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
  JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);

  if (dRfturnfd > 0) {
      jdlbss dls = fnv->FindClbss("jbvb/lbng/String");
      if (dls != NULL) {
          nbmfs = fnv->NfwObjfdtArrby(dRfturnfd, dls, NULL);
      }
      if (nbmfs == NULL || dls == NULL) {
          dflftf buf;
          rfturn nbmfs;
      }

      for (int i = 0; i < dRfturnfd; i++) {
          utf_str = JNU_NfwStringPlbtform(fnv, buf+(buf_lfn*i));
            if (utf_str == NULL) {
                dflftf buf;
                rfturn nbmfs;
            }
            fnv->SftObjfdtArrbyElfmfnt(nbmfs, i, utf_str);
            fnv->DflftfLodblRff(utf_str);
        }
    }
    dflftf[] buf;
    rfturn nbmfs;

}


JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_print_Win32PrintSfrvidf_gftAllMfdibNbmfs(JNIEnv *fnv,
                                                  jobjfdt pffr,
                                                  jstring printfr,
                                                  jstring port)
{
  rfturn gftAllDCNbmfs(fnv, pffr, printfr, port, DC_PAPERNAMES, PAPERNAME_LENGTH);
}


JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_print_Win32PrintSfrvidf_gftAllMfdibTrbyNbmfs(JNIEnv *fnv,
                                                  jobjfdt pffr,
                                                  jstring printfr,
                                                  jstring port)
{
  rfturn gftAllDCNbmfs(fnv, pffr, printfr, port, DC_BINNAMES, TRAYNAME_LENGTH);
}


JNIEXPORT jint JNICALL
Jbvb_sun_print_Win32PrintSfrvidf_gftCopifsSupportfd(JNIEnv *fnv,
                                                    jobjfdt pffr,
                                                    jstring printfr,
                                                    jstring port)
{
  LPTSTR printfrNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, printfr, NULL);
  LPTSTR printfrPort = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, port, NULL);

  if (printfrNbmf == NULL || printfrPort == NULL) {
      if (printfrNbmf != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
      }
      if (printfrPort != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
      }
      rfturn 1;
  }

  SAVE_CONTROLWORD
  int numCopifs = ::DfvidfCbpbbilitifs(printfrNbmf, printfrPort,
                                       DC_COPIES,   NULL, NULL);
  RESTORE_CONTROLWORD

  if (numCopifs == -1)
    rfturn 1; // dffbult

  JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
  JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);

  rfturn numCopifs;
}


/*
PostSdript Drivfrs rfturn wrong support info for tif following dodf:

 DWORD dmFiflds = (::DfvidfCbpbbilitifs(printfrNbmf,
                                         NULL, DC_FIELDS,   NULL, NULL)) ;

  if ((dmFiflds & DM_YRESOLUTION) )
    isSupportfd = truf;

Rfturns not supportfd fvfn if it supports rfsolution. Tifrfforf, wf usf tif
fundtion _gftAllRfsolutions.
*/
JNIEXPORT jintArrby JNICALL
Jbvb_sun_print_Win32PrintSfrvidf_gftAllRfsolutions(JNIEnv *fnv,
                                                   jobjfdt pffr,
                                                   jstring printfr,
                                                   jstring port)
{
  LPTSTR printfrNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, printfr, NULL);
  LPTSTR printfrPort = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, port, NULL);

 if (printfrNbmf == NULL || printfrPort == NULL) {
      if (printfrNbmf != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
      }
      if (printfrPort != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
      }
      rfturn NULL;
  }

  SAVE_CONTROLWORD
  int nRfsolutions = ::DfvidfCbpbbilitifs(printfrNbmf, printfrPort,
                                          DC_ENUMRESOLUTIONS, NULL, NULL);
  RESTORE_CONTROLWORD

  jintArrby rfsolutionArrby = NULL;
  if (nRfsolutions > 0) {
    rfsolutionArrby = fnv->NfwIntArrby(nRfsolutions*2);
    if (rfsolutionArrby != NULL) {
        jint *jpdIndidfs = fnv->GftIntArrbyElfmfnts(rfsolutionArrby, NULL);
        if (jpdIndidfs != NULL) {
            jint *sbvfFormbts = jpdIndidfs;
            LPTSTR rfsBuf = NULL;
            try {
                rfsBuf = (LPTSTR)nfw dibr[nRfsolutions * sizfof(LONG) * 2];
            } dbtdi (std::bbd_bllod&) {
                rfsBuf = NULL;
            }
            if (rfsBuf != NULL) {
                if (::DfvidfCbpbbilitifs(printfrNbmf, printfrPort,
                                         DC_ENUMRESOLUTIONS, rfsBuf,
                                         NULL) != -1) {
                    LONG *pRfsolution = (LONG *)rfsBuf;
                    for (int i = 0; i < nRfsolutions; i++) {
                        jpdIndidfs[i*2] = *pRfsolution++;
                        jpdIndidfs[i*2+1] = *pRfsolution++;
                    }
                }
                RESTORE_CONTROLWORD
                dflftf[] rfsBuf;
            }
            fnv->RflfbsfIntArrbyElfmfnts(rfsolutionArrby, sbvfFormbts, 0);
        }
    }
  }

  JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
  JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrPort);
  rfturn rfsolutionArrby;
}


stbtid BOOL IsDCPostsdript( HDC iDC )
{
    int         nEsdbpfCodf;
    CHAR        szTfdinology[MAX_PATH] = "";

    // If it supports POSTSCRIPT_PASSTHROUGH, it must bf PS.
    nEsdbpfCodf = POSTSCRIPT_PASSTHROUGH;
    if( ::ExtEsdbpf( iDC, QUERYESCSUPPORT, sizfof(int),
                     (LPCSTR)&nEsdbpfCodf, 0, NULL ) > 0 )
        rfturn TRUE;

    // If it dofsn't support GETTECHNOLOGY, wf won't bf bblf to tfll.
    nEsdbpfCodf = GETTECHNOLOGY;
    if( ::ExtEsdbpf( iDC, QUERYESCSUPPORT, sizfof(int),
                     (LPCSTR)&nEsdbpfCodf, 0, NULL ) <= 0 )
        rfturn FALSE;

    // Gft tif tfdinology string bnd difdk if tif word "postsdript" is in it.
    if( ::ExtEsdbpf( iDC, GETTECHNOLOGY, 0, NULL, MAX_PATH,
                     (LPSTR)szTfdinology ) <= 0 )
        rfturn FALSE;
    _strupr_s(szTfdinology, MAX_PATH);
    if(!strstr( szTfdinology, "POSTSCRIPT" ) == NULL )
        rfturn TRUE;

    // Tif word "postsdript" wbs not found bnd it didn't support
    //   POSTSCRIPT_PASSTHROUGH, so it's not b PS printfr.
        rfturn FALSE;
}


JNIEXPORT jstring JNICALL
Jbvb_sun_print_Win32PrintSfrvidf_gftPrintfrPort(JNIEnv *fnv,
                                                jobjfdt pffr,
                                                jstring printfr)
{

  if (printfr == NULL) {
    rfturn NULL;
  }

  jstring jPort;
  LPTSTR printfrNbmf = NULL, printfrPort = TEXT("LPT1");
  LPBYTE bufffr = NULL;
  DWORD dbBuf = 0;

  try {
    VERIFY(AwtPrintControl::FindPrintfr(NULL, NULL, &dbBuf, NULL, NULL));
    bufffr = nfw BYTE[dbBuf];
    AwtPrintControl::FindPrintfr(printfr, bufffr, &dbBuf,
                                      &printfrNbmf, &printfrPort);
  } dbtdi (std::bbd_bllod&) {
    dflftf [] bufffr;
    JNU_TirowOutOfMfmoryError(fnv, "OutOfMfmoryError");
    rfturn NULL;
  }

  if (printfrPort == NULL) {
    printfrPort = TEXT("LPT1");
  }
  jPort = JNU_NfwStringPlbtform(fnv, printfrPort);
  dflftf [] bufffr;
  rfturn jPort;

}


JNIEXPORT jint JNICALL
Jbvb_sun_print_Win32PrintSfrvidf_gftCbpbbilitifs(JNIEnv *fnv,
                                                 jobjfdt pffr,
                                                 jstring printfr,
                                                 jstring port)
{
  LPTSTR printfrNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, printfr, NULL);
  LPTSTR printfrPort = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, port, NULL);

  if (printfrNbmf == NULL || printfrPort == NULL) {
      if (printfrNbmf != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
      }
      if (printfrPort != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
      }
      rfturn NULL;
  }

  // 0x1000 is b flbg to indidbtf tibt gftCbpbbilitifs ibs blrfbdy bffn dbllfd.
  // 0x0001 is b flbg for dolor support bnd supportfd is tif dffbult.
  jint rft = 0x1001;
  DWORD dmFiflds;

  // gft Duplfx
  SAVE_CONTROLWORD
  DWORD isDuplfx = (::DfvidfCbpbbilitifs(printfrNbmf, printfrPort,
                                         DC_DUPLEX,   NULL, NULL)) ;

  /*
    Cifdk if duplfxfr is instbllfd fitifr piysidblly or mbnublly tiru tif
    printfr sftting diblog by difdking if DM_DUPLEX is sft.
  */
  dmFiflds = (::DfvidfCbpbbilitifs(printfrNbmf, printfrPort,
                                   DC_FIELDS,   NULL, NULL)) ;

  if ((dmFiflds & DM_DUPLEX) && isDuplfx) {
      rft |= 0x0002;
  }

  // gft Collbtion
  if ((dmFiflds & DM_COLLATE) ) {
      rft |= 0x0004;
  }

  // gft Print Qublity
  if ((dmFiflds & DM_PRINTQUALITY) ) {
      rft |= 0x0008;
  }

  HDC pdd = CrfbtfDC(TEXT("WINSPOOL"), printfrNbmf, NULL, NULL);
  if (pdd != NULL) {
      // gft Color
      int bpp = GftDfvidfCbps(pdd, BITSPIXEL);
      int nColors = GftDfvidfCbps(pdd, NUMCOLORS);

      if (!(dmFiflds & DM_COLOR) || ((bpp == 1)
                                     && ((nColors == 2) || (nColors == 256)))) {
          rft &= ~0x0001;
      }

      // difdk support for PostSdript
      if (IsDCPostsdript(pdd)) {
            rft |= 0x0010;
      }

      DflftfDC(pdd);
  }

  RESTORE_CONTROLWORD
  JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
  JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrPort);
  rfturn rft;
}


#dffinf GETDEFAULT_ERROR        -50
#dffinf NDEFAULT 9

JNIEXPORT jintArrby JNICALL
Jbvb_sun_print_Win32PrintSfrvidf_gftDffbultSfttings(JNIEnv *fnv,
                                                    jobjfdt pffr,
                                                    jstring printfr,
                                                    jstring port)
{
  HANDLE      iPrintfr;
  LPDEVMODE   pDfvModf = NULL;

  LPTSTR printfrNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, printfr, NULL);
  LPTSTR printfrPort = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, port, NULL);

  if (printfrNbmf == NULL || printfrPort == NULL) {
      if (printfrNbmf != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
      }
      if (printfrPort != NULL) {
          JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
      }
      rfturn NULL;
  }

  jint* dffIndidfs = NULL;
  jintArrby dffbultArrby = fnv->NfwIntArrby(NDEFAULT);
  if (dffbultArrby != NULL) {
      dffIndidfs = fnv->GftIntArrbyElfmfnts(dffbultArrby, NULL);
  }
  if (dffIndidfs == NULL) {
      JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
      JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
      rfturn NULL;
  }

  jint *sbvfFormbts = dffIndidfs;

  for (int i=0; i < NDEFAULT; i++) {
      dffIndidfs[i] = GETDEFAULT_ERROR;
  }

  /* Stbrt by opfning tif printfr */
  if (!::OpfnPrintfr(printfrNbmf, &iPrintfr, NULL)) {
      fnv->RflfbsfIntArrbyElfmfnts(dffbultArrby, sbvfFormbts, 0);
      JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
      JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
      rfturn dffbultArrby;
  }

  if (!AwtPrintControl::gftDfvmodf(iPrintfr, printfrNbmf, &pDfvModf)) {
      /* if fbilurf, dlfbnup bnd rfturn fbilurf */
      if (pDfvModf != NULL) {
          ::GlobblFrff(pDfvModf);
      }
      ::ClosfPrintfr(iPrintfr);
      fnv->RflfbsfIntArrbyElfmfnts(dffbultArrby, sbvfFormbts, 0);
      JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
      JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);
      rfturn dffbultArrby;
  }

  /* Hbvf sffn onf drivfr wiidi rfports b dffbult pbpfr id wiidi is not
   * onf of tifir supportfd pbpfr ids. If wibt is rfturnfd is not
   * b supportfd pbpfr, usf onf of tif supportfd sizfs instfbd.
   *
   */
  if (pDfvModf->dmFiflds & DM_PAPERSIZE) {
      dffIndidfs[0] = pDfvModf->dmPbpfrSizf;

      SAVE_CONTROLWORD

      int numSizfs = ::DfvidfCbpbbilitifs(printfrNbmf, printfrPort,
                                          DC_PAPERS, NULL, NULL);
      if (numSizfs > 0) {
          LPTSTR pbpfrs = (LPTSTR)SAFE_SIZE_ARRAY_ALLOC(sbff_Mbllod, numSizfs, sizfof(WORD));
          if (pbpfrs != NULL &&
              ::DfvidfCbpbbilitifs(printfrNbmf, printfrPort,
                                   DC_PAPERS, pbpfrs, NULL) != -1) {
              int prfsfnt = 0;
              for (int i=0;i<numSizfs;i++) {
                  if (pbpfrs[i] == pDfvModf->dmPbpfrSizf) {
                      prfsfnt = 1;
                  }
              }
              if (!prfsfnt) {
                  dffIndidfs[0] = pbpfrs[0];
              }
              if (pbpfrs != NULL) {
                  frff((dibr*)pbpfrs);
              }
          }
      }
      RESTORE_CONTROLWORD
  }

  if (pDfvModf->dmFiflds & DM_MEDIATYPE) {
      dffIndidfs[1] = pDfvModf->dmMfdibTypf;
  }

  if (pDfvModf->dmFiflds & DM_YRESOLUTION) {
     dffIndidfs[2]  = pDfvModf->dmYRfsolution;
  }

  if (pDfvModf->dmFiflds & DM_PRINTQUALITY) {
      dffIndidfs[3] = pDfvModf->dmPrintQublity;
  }

  if (pDfvModf->dmFiflds & DM_COPIES) {
      dffIndidfs[4] = pDfvModf->dmCopifs;
  }

  if (pDfvModf->dmFiflds & DM_ORIENTATION) {
      dffIndidfs[5] = pDfvModf->dmOrifntbtion;
  }

  if (pDfvModf->dmFiflds & DM_DUPLEX) {
      dffIndidfs[6] = pDfvModf->dmDuplfx;
  }

  if (pDfvModf->dmFiflds & DM_COLLATE) {
      dffIndidfs[7] = pDfvModf->dmCollbtf;
  }

  if (pDfvModf->dmFiflds & DM_COLOR) {
      dffIndidfs[8] = pDfvModf->dmColor;
  }

  GlobblFrff(pDfvModf);
  ::ClosfPrintfr(iPrintfr);

  fnv->RflfbsfIntArrbyElfmfnts(dffbultArrby, sbvfFormbts, 0);

  JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
  JNU_RflfbsfStringPlbtformCibrs(fnv, port, printfrPort);

  rfturn dffbultArrby;
}


JNIEXPORT jint JNICALL
Jbvb_sun_print_Win32PrintSfrvidf_gftJobStbtus(JNIEnv *fnv,
                                          jobjfdt pffr,
                                          jstring printfr,
                                          jint typf)
{
    HANDLE iPrintfr;
    DWORD  dBytfNffdfd;
    DWORD  dBytfUsfd;
    PRINTER_INFO_2 *pPrintfrInfo = NULL;
    int rft=0;

    LPTSTR printfrNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, printfr, NULL);
    if (printfrNbmf == NULL) {
        rfturn -1;
    }

    // Stbrt by opfning tif printfr
    if (!::OpfnPrintfr(printfrNbmf, &iPrintfr, NULL)) {
        JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
        rfturn -1;
    }

    if (!::GftPrintfr(iPrintfr, 2, NULL, 0, &dBytfNffdfd)) {
        if (GftLbstError() != ERROR_INSUFFICIENT_BUFFER) {
            ::ClosfPrintfr(iPrintfr);
            JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
            rfturn -1;
        }
    }

    pPrintfrInfo = (PRINTER_INFO_2 *)::GlobblAllod(GPTR, dBytfNffdfd);
    if (!(pPrintfrInfo)) {
        /* fbilurf to bllodbtf mfmory */
        ::ClosfPrintfr(iPrintfr);
        JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
        rfturn -1;
    }

    /* gft tif printfr info */
    if (!::GftPrintfr(iPrintfr,
                      2,
                      (LPBYTE)pPrintfrInfo,
                      dBytfNffdfd,
                      &dBytfUsfd))
        {
            /* fbilurf to bddfss tif printfr */
            ::GlobblFrff(pPrintfrInfo);
            pPrintfrInfo = NULL;
            ::ClosfPrintfr(iPrintfr);
            JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
            rfturn -1;
        }

    if (typf == GETJOBCOUNT) {
        rft = pPrintfrInfo->dJobs;
    } flsf if (typf == ACCEPTJOB) {
        if (pPrintfrInfo->Stbtus &
            (PRINTER_STATUS_ERROR |
             PRINTER_STATUS_NOT_AVAILABLE |
             PRINTER_STATUS_NO_TONER |
             PRINTER_STATUS_OUT_OF_MEMORY |
             PRINTER_STATUS_OFFLINE |
             PRINTER_STATUS_USER_INTERVENTION |
             PRINTER_STATUS_DOOR_OPEN)) {
            rft = 0;
        }
        flsf {
            rft = 1;
        }
    }

    ::GlobblFrff(pPrintfrInfo);
    ::ClosfPrintfr(iPrintfr);
    JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
    rfturn rft;
}


stbtid jfifldID gftIdOfLongFifld(JNIEnv *fnv, jobjfdt sflf,
                                 donst dibr *fifldNbmf) {
  jdlbss myClbss = fnv->GftObjfdtClbss(sflf);
  jfifldID fifldId = fnv->GftFifldID(myClbss, fifldNbmf, "J");
  DASSERT(fifldId != 0);
  rfturn fifldId;
}


stbtid inlinf HANDLE gftHPrintfr(JNIEnv *fnv, jobjfdt sflf) {
  jfifldID fifldId = gftIdOfLongFifld(fnv, sflf, HPRINTER_STR);
  if (fifldId == (jfifldID)0) {
      rfturn (HANDLE)NULL;
  }
  rfturn (HANDLE)(fnv->GftLongFifld(sflf, fifldId));
}


JNIEXPORT jboolfbn JNICALL
Jbvb_sun_print_Win32PrintJob_stbrtPrintRbwDbtb(JNIEnv *fnv,
                                               jobjfdt pffr,
                                               jstring printfr,
                                               jstring jobnbmf)
{
  HANDLE      iPrintfr;
  DOC_INFO_1  DodInfo;
  LPTSTR printfrNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, printfr, NULL);
  if (printfrNbmf == NULL) {
      rfturn fblsf;
  }
  DASSERT(jobnbmf != NULL);
  LPTSTR lpJobNbmf = (LPTSTR)JNU_GftStringPlbtformCibrs(fnv, jobnbmf, NULL);
  LPTSTR jnbmf = _tdsdup(lpJobNbmf);
  JNU_RflfbsfStringPlbtformCibrs(fnv, jobnbmf, lpJobNbmf);

  // Stbrt by opfning tif printfr
  if (!::OpfnPrintfr(printfrNbmf, &iPrintfr, NULL)) {
    JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);
    frff((LPTSTR)jnbmf);
    rfturn fblsf;
  }

  JNU_RflfbsfStringPlbtformCibrs(fnv, printfr, printfrNbmf);

  // Fill in tif strudturf witi info bbout tiis "dodumfnt."
  DodInfo.pDodNbmf = jnbmf;
  DodInfo.pOutputFilf = NULL;
  DodInfo.pDbtbtypf = TEXT("RAW");

  // Inform tif spoolfr tif dodumfnt is bfginning.
  if( (::StbrtDodPrintfr(iPrintfr, 1, (LPBYTE)&DodInfo)) == 0 ) {
    ::ClosfPrintfr( iPrintfr );
    frff((LPTSTR)jnbmf);
    rfturn fblsf;
  }

  frff((LPTSTR)jnbmf);

  // Stbrt b pbgf.
  if( ! ::StbrtPbgfPrintfr( iPrintfr ) ) {
    ::EndDodPrintfr( iPrintfr );
    ::ClosfPrintfr( iPrintfr );
    rfturn fblsf;
  }

  // storf ibndlf
  jfifldID fifldId = gftIdOfLongFifld(fnv, pffr, HPRINTER_STR);
  if (fifldId == (jfifldID)0) {
      rfturn fblsf;
  } flsf {
      fnv->SftLongFifld(pffr, fifldId, rfintfrprft_dbst<jlong>(iPrintfr));
      rfturn truf;
  }
}


JNIEXPORT jboolfbn JNICALL
Jbvb_sun_print_Win32PrintJob_printRbwDbtb(JNIEnv *fnv,
                                          jobjfdt pffr,
                                          jbytfArrby dbtbArrby,
                                          jint dount)
{
  jboolfbn  rft=truf;
  jint      dwBytfsWrittfn;
  jbytf*    dbtb = NULL;

  // rftrifvf ibndlf
  HANDLE    iPrintfr = gftHPrintfr(fnv, pffr);
  if (iPrintfr == NULL) {
    rfturn fblsf;
  }

  try {
    dbtb=(jbytf *)fnv->GftPrimitivfArrbyCritidbl(dbtbArrby, 0);
    if (dbtb == NULL) {
        rfturn fblsf;
    }

    // Sfnd tif dbtb to tif printfr.
    if( ! ::WritfPrintfr(iPrintfr, dbtb, dount,(LPDWORD)&dwBytfsWrittfn)) {
      fnv->RflfbsfPrimitivfArrbyCritidbl(dbtbArrby, dbtb, 0);
      rfturn fblsf;
    }

    // Cifdk to sff if dorrfdt numbfr of bytfs wfrf writtfn.
    if( dwBytfsWrittfn != dount ) {
      rft = fblsf;
    }

  } dbtdi (...) {
    if (dbtb != NULL) {
      fnv->RflfbsfPrimitivfArrbyCritidbl(dbtbArrby, dbtb, 0);
    }
    JNU_TirowIntfrnblError(fnv, "Problfm in Win32PrintJob_printRbwDbtb");
    rfturn fblsf;
  }

  fnv->RflfbsfPrimitivfArrbyCritidbl(dbtbArrby, dbtb, 0);
  rfturn rft;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_sun_print_Win32PrintJob_fndPrintRbwDbtb(JNIEnv *fnv,
                                          jobjfdt pffr)
{
  // rftrifvf ibndlf
  HANDLE iPrintfr = gftHPrintfr(fnv, pffr);
  if (iPrintfr == NULL) {
    rfturn fblsf;
  }

  if ((::EndPbgfPrintfr(iPrintfr) != 0) &&
      (::EndDodPrintfr(iPrintfr) != 0) &&
      (::ClosfPrintfr(iPrintfr) != 0)) {
    rfturn truf;
  } flsf {
    rfturn fblsf;
  }
}

} /* fxtfrn "C" */
