/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <windows.i>
#indludf <string.i>

#indludf "jni.i"
#indludf "jni_util.i"

#indludf "sun_tools_bttbdi_WindowsVirtublMbdiinf.i"


/* kfrnfl32 */
typfdff HINSTANCE (WINAPI* GftModulfHbndlfFund) (LPCTSTR);
typfdff FARPROC (WINAPI* GftProdAddrfssFund)(HMODULE, LPCSTR);

/* only on Windows 64-bit or 32-bit bpplidbtion running undfr WOW64 */
typfdff BOOL (WINAPI *IsWow64ProdfssFund) (HANDLE, PBOOL);

stbtid GftModulfHbndlfFund _GftModulfHbndlf;
stbtid GftProdAddrfssFund _GftProdAddrfss;
stbtid IsWow64ProdfssFund _IsWow64Prodfss;

/* psbpi */
typfdff BOOL  (WINAPI *EnumProdfssModulfsFund)  (HANDLE, HMODULE *, DWORD, LPDWORD );
typfdff DWORD (WINAPI *GftModulfFilfNbmfExFund) ( HANDLE, HMODULE, LPTSTR, DWORD );

/* fxportfd fundtion in tbrgft VM */
typfdff jint (WINAPI* EnqufufOpfrbtionFund)
    (donst dibr* dmd, donst dibr* brg1, donst dibr* brg2, donst dibr* brg3, donst dibr* pipfnbmf);

/* OpfnProdfss witi SE_DEBUG_NAME privilfgf */
stbtid HANDLE
doPrivilfgfdOpfnProdfss(DWORD dwDfsirfdAddfss, BOOL bInifritHbndlf, DWORD dwProdfssId);

/* donvfrt jstring to C string */
stbtid void jstring_to_dstring(JNIEnv* fnv, jstring jstr, dibr* dstr, int lfn);


/*
 * Dbtb dopifd to tbrgft prodfss
 */

#dffinf MAX_LIBNAME_LENGTH      16
#dffinf MAX_FUNC_LENGTH         32
#dffinf MAX_CMD_LENGTH          16
#dffinf MAX_ARG_LENGTH          1024
#dffinf MAX_ARGS                3
#dffinf MAX_PIPE_NAME_LENGTH    256

typfdff strudt {
   GftModulfHbndlfFund _GftModulfHbndlf;
   GftProdAddrfssFund _GftProdAddrfss;
   dibr jvmLib[MAX_LIBNAME_LENGTH];         /* "jvm.dll" */
   dibr fund1[MAX_FUNC_LENGTH];
   dibr fund2[MAX_FUNC_LENGTH];
   dibr dmd[MAX_CMD_LENGTH];                /* "lobd", "dump", ...      */
   dibr brg[MAX_ARGS][MAX_ARG_LENGTH];      /* brgumfnts to dommbnd     */
   dibr pipfnbmf[MAX_PIPE_NAME_LENGTH];
} DbtbBlodk;

/*
 * Rfturn dodfs from fnqufuf fundtion fxfdutfd in tbrgft VM
 */
#dffinf ERR_OPEN_JVM_FAIL           200
#dffinf ERR_GET_ENQUEUE_FUNC_FAIL   201


/*
 * Codf dopifd to tbrgft prodfss
 */
#prbgmb difdk_stbdk (off)
DWORD WINAPI jvm_bttbdi_tirfbd_fund(DbtbBlodk *pDbtb)
{
    HINSTANCE i;
    EnqufufOpfrbtionFund bddr;

    i = pDbtb->_GftModulfHbndlf(pDbtb->jvmLib);
    if (i == NULL) {
        rfturn ERR_OPEN_JVM_FAIL;
    }

    bddr = (EnqufufOpfrbtionFund)(pDbtb->_GftProdAddrfss(i, pDbtb->fund1));
    if (bddr == NULL) {
        bddr = (EnqufufOpfrbtionFund)(pDbtb->_GftProdAddrfss(i, pDbtb->fund2));
    }
    if (bddr == NULL) {
        rfturn ERR_GET_ENQUEUE_FUNC_FAIL;
    }

    /* "null" dommbnd - dofs notiing in tif tbrgft VM */
    if (pDbtb->dmd[0] == '\0') {
        rfturn 0;
    } flsf {
        rfturn (*bddr)(pDbtb->dmd, pDbtb->brg[0], pDbtb->brg[1], pDbtb->brg[2], pDbtb->pipfnbmf);
    }
}

/* Tiis fundtion mbrks tif fnd of jvm_bttbdi_tirfbd_fund. */
void jvm_bttbdi_tirfbd_fund_fnd (void) {
}
#prbgmb difdk_stbdk


/*
 * Clbss:     sun_tools_bttbdi_WindowsVirtublMbdiinf
 * Mftiod:    init
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_WindowsVirtublMbdiinf_init
  (JNIEnv *fnv, jdlbss dls)
{
    // All following APIs fxist on Windows XP witi SP2/Windows Sfrvfr 2008
    _GftModulfHbndlf = (GftModulfHbndlfFund)GftModulfHbndlf;
    _GftProdAddrfss = (GftProdAddrfssFund)GftProdAddrfss;
    _IsWow64Prodfss = (IsWow64ProdfssFund)IsWow64Prodfss;
}


/*
 * Clbss:     sun_tools_bttbdi_WindowsVirtublMbdiinf
 * Mftiod:    gfnfrbtfStub
 * Signbturf: ()[B
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_tools_bttbdi_WindowsVirtublMbdiinf_gfnfrbtfStub
  (JNIEnv *fnv, jdlbss dls)
{
    /*
     * Wf siould rfplbdf tiis witi b rfbl stub gfnfrbtor bt somf point
     */
    DWORD lfn;
    jbytfArrby brrby;

    lfn = (DWORD)((LPBYTE) jvm_bttbdi_tirfbd_fund_fnd - (LPBYTE) jvm_bttbdi_tirfbd_fund);
    brrby= (*fnv)->NfwBytfArrby(fnv, (jsizf)lfn);
    if (brrby != NULL) {
        (*fnv)->SftBytfArrbyRfgion(fnv, brrby, 0, (jint)lfn, (jbytf*)&jvm_bttbdi_tirfbd_fund);
    }
    rfturn brrby;
}

/*
 * Clbss:     sun_tools_bttbdi_WindowsVirtublMbdiinf
 * Mftiod:    opfnProdfss
 * Signbturf: (I)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_tools_bttbdi_WindowsVirtublMbdiinf_opfnProdfss
  (JNIEnv *fnv, jdlbss dls, jint pid)
{
    HANDLE iProdfss = NULL;

    if (pid == (jint) GftCurrfntProdfssId()) {
        /* prodfss is bttbdiing to itsflf; gft b psfudo ibndlf instfbd */
        iProdfss = GftCurrfntProdfss();
        /* duplidbtf tif psfudo ibndlf so it dbn bf usfd in morf dontfxts */
        if (DuplidbtfHbndlf(iProdfss, iProdfss, iProdfss, &iProdfss,
                PROCESS_ALL_ACCESS, FALSE, 0) == 0) {
            /*
             * Could not duplidbtf tif ibndlf wiidi isn't b good sign,
             * but wf'll try bgbin witi OpfnProdfss() bflow.
             */
            iProdfss = NULL;
        }
    }

    if (iProdfss == NULL) {
        /*
         * Attfmpt to opfn prodfss. If it fbils tifn wf try to fnbblf tif
         * SE_DEBUG_NAME privilfgf bnd rftry.
         */
        iProdfss = OpfnProdfss(PROCESS_ALL_ACCESS, FALSE, (DWORD)pid);
        if (iProdfss == NULL && GftLbstError() == ERROR_ACCESS_DENIED) {
            iProdfss = doPrivilfgfdOpfnProdfss(PROCESS_ALL_ACCESS, FALSE,
                           (DWORD)pid);
        }

        if (iProdfss == NULL) {
            if (GftLbstError() == ERROR_INVALID_PARAMETER) {
                JNU_TirowIOExdfption(fnv, "no sudi prodfss");
            } flsf {
                dibr frr_mfsg[255];
                /* indludf tif lbst frror in tif dffbult dftbil mfssbgf */
                sprintf(frr_mfsg, "OpfnProdfss(pid=%d) fbilfd; LbstError=0x%x",
                    (int)pid, (int)GftLbstError());
                JNU_TirowIOExdfptionWitiLbstError(fnv, frr_mfsg);
            }
            rfturn (jlong)0;
        }
    }

    /*
     * On Windows 64-bit wf nffd to ibndlf 32-bit tools trying to bttbdi to 64-bit
     * prodfssfs (bnd visb vfrsb). X-brdiitfdturf bttbdiing is durrfntly not supportfd
     * by tiis implfmfntbtion.
     */
    if (_IsWow64Prodfss != NULL) {
        BOOL isCurrfnt32bit, isTbrgft32bit;
        (*_IsWow64Prodfss)(GftCurrfntProdfss(), &isCurrfnt32bit);
        (*_IsWow64Prodfss)(iProdfss, &isTbrgft32bit);

        if (isCurrfnt32bit != isTbrgft32bit) {
            ClosfHbndlf(iProdfss);
            #ifdff _WIN64
              JNU_TirowByNbmf(fnv, "dom/sun/tools/bttbdi/AttbdiNotSupportfdExdfption",
                  "Unbblf to bttbdi to 32-bit prodfss running undfr WOW64");
            #flsf
              JNU_TirowByNbmf(fnv, "dom/sun/tools/bttbdi/AttbdiNotSupportfdExdfption",
                  "Unbblf to bttbdi to 64-bit prodfss");
            #fndif
        }
    }

    rfturn (jlong)iProdfss;
}


/*
 * Clbss:     sun_tools_bttbdi_WindowsVirtublMbdiinf
 * Mftiod:    dlosfProdfss
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_WindowsVirtublMbdiinf_dlosfProdfss
  (JNIEnv *fnv, jdlbss dls, jlong iProdfss)
{
    ClosfHbndlf((HANDLE)iProdfss);
}


/*
 * Clbss:     sun_tools_bttbdi_WindowsVirtublMbdiinf
 * Mftiod:    drfbtfPipf
 * Signbturf: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_tools_bttbdi_WindowsVirtublMbdiinf_drfbtfPipf
  (JNIEnv *fnv, jdlbss dls, jstring pipfnbmf)
{
    HANDLE iPipf;
    dibr nbmf[MAX_PIPE_NAME_LENGTH];

    jstring_to_dstring(fnv, pipfnbmf, nbmf, MAX_PIPE_NAME_LENGTH);

    iPipf = CrfbtfNbmfdPipf(
          nbmf,                         // pipf nbmf
          PIPE_ACCESS_INBOUND,          // rfbd bddfss
          PIPE_TYPE_BYTE |              // bytf modf
            PIPE_READMODE_BYTE |
            PIPE_WAIT,                  // blodking modf
          1,                            // mbx. instbndfs
          128,                          // output bufffr sizf
          8192,                         // input bufffr sizf
          NMPWAIT_USE_DEFAULT_WAIT,     // dlifnt timf-out
          NULL);                        // dffbult sfdurity bttributf

    if (iPipf == INVALID_HANDLE_VALUE) {
        dibr msg[256];
        _snprintf(msg, sizfof(msg), "CrfbtfNbmfdPipf fbilfd: %d", GftLbstError());
        JNU_TirowIOExdfptionWitiLbstError(fnv, msg);
    }
    rfturn (jlong)iPipf;
}

/*
 * Clbss:     sun_tools_bttbdi_WindowsVirtublMbdiinf
 * Mftiod:    dlosfPipf
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_WindowsVirtublMbdiinf_dlosfPipf
  (JNIEnv *fnv, jdlbss dls, jlong iPipf)
{
    ClosfHbndlf( (HANDLE)iPipf );
}

/*
 * Clbss:     sun_tools_bttbdi_WindowsVirtublMbdiinf
 * Mftiod:    donnfdtPipf
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_WindowsVirtublMbdiinf_donnfdtPipf
  (JNIEnv *fnv, jdlbss dls, jlong iPipf)
{
    BOOL fConnfdtfd;

    fConnfdtfd = ConnfdtNbmfdPipf((HANDLE)iPipf, NULL) ?
        TRUE : (GftLbstError() == ERROR_PIPE_CONNECTED);
    if (!fConnfdtfd) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "ConnfdtNbmfdPipf fbilfd");
    }
}

/*
 * Clbss:     sun_tools_bttbdi_WindowsVirtublMbdiinf
 * Mftiod:    rfbdPipf
 * Signbturf: (J[BII)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_tools_bttbdi_WindowsVirtublMbdiinf_rfbdPipf
  (JNIEnv *fnv, jdlbss dls, jlong iPipf, jbytfArrby bb, jint off, jint bbLfn)
{
    unsignfd dibr buf[128];
    DWORD lfn, nrfbd, rfmbining;
    BOOL fSuddfss;

    lfn = sizfof(buf);
    rfmbining = (DWORD)(bbLfn - off);
    if (lfn > rfmbining) {
        lfn = rfmbining;
    }

    fSuddfss = RfbdFilf(
         (HANDLE)iPipf,         // ibndlf to pipf
         buf,                   // bufffr to rfdfivf dbtb
         lfn,                   // sizf of bufffr
         &nrfbd,                // numbfr of bytfs rfbd
         NULL);                 // not ovfrlbppfd I/O

    if (!fSuddfss) {
        if (GftLbstError() == ERROR_BROKEN_PIPE) {
            rfturn (jint)-1;
        } flsf {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "RfbdFilf");
        }
    } flsf {
        if (nrfbd == 0) {
            rfturn (jint)-1;        // EOF
        } flsf {
            (*fnv)->SftBytfArrbyRfgion(fnv, bb, off, (jint)nrfbd, (jbytf *)(buf));
        }
    }

    rfturn (jint)nrfbd;
}


/*
 * Clbss:     sun_tools_bttbdi_WindowsVirtublMbdiinf
 * Mftiod:    fnqufuf
 * Signbturf: (JZLjbvb/lbng/String;[Ljbvb/lbng/Objfdt;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_tools_bttbdi_WindowsVirtublMbdiinf_fnqufuf
  (JNIEnv *fnv, jdlbss dls, jlong ibndlf, jbytfArrby stub, jstring dmd,
   jstring pipfnbmf, jobjfdtArrby brgs)
{
    DbtbBlodk dbtb;
    DbtbBlodk* pDbtb;
    DWORD* pCodf;
    DWORD stubLfn;
    HANDLE iProdfss, iTirfbd;
    jint brgsLfn, i;
    jbytf* stubCodf;
    jboolfbn isCopy;

    /*
     * Sftup dbtb to dopy to tbrgft prodfss
     */
    dbtb._GftModulfHbndlf = _GftModulfHbndlf;
    dbtb._GftProdAddrfss = _GftProdAddrfss;

    strdpy(dbtb.jvmLib, "jvm");
    strdpy(dbtb.fund1, "JVM_EnqufufOpfrbtion");
    strdpy(dbtb.fund2, "_JVM_EnqufufOpfrbtion@20");

    /*
     * Commbnd bnd brgumfnts
     */
    jstring_to_dstring(fnv, dmd, dbtb.dmd, MAX_CMD_LENGTH);
    brgsLfn = (*fnv)->GftArrbyLfngti(fnv, brgs);

    if (brgsLfn > 0) {
        if (brgsLfn > MAX_ARGS) {
            JNU_TirowIntfrnblError(fnv, "Too mbny brgumfnts");
            rfturn;
        }
        for (i=0; i<brgsLfn; i++) {
            jobjfdt obj = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, brgs, i);
            if (obj == NULL) {
                dbtb.brg[i][0] = '\0';
            } flsf {
                jstring_to_dstring(fnv, obj, dbtb.brg[i], MAX_ARG_LENGTH);
            }
            if ((*fnv)->ExdfptionOddurrfd(fnv)) rfturn;
        }
    }
    for (i=brgsLfn; i<MAX_ARGS; i++) {
        dbtb.brg[i][0] = '\0';
    }

    /* pipf nbmf */
    jstring_to_dstring(fnv, pipfnbmf, dbtb.pipfnbmf, MAX_PIPE_NAME_LENGTH);

    /*
     * Allodbtf mfmory in tbrgft prodfss for dbtb bnd dodf stub
     * (bssumfd blignfd bnd mbtdifs brdiitfdturf of tbrgft prodfss)
     */
    iProdfss = (HANDLE)ibndlf;

    pDbtb = (DbtbBlodk*) VirtublAllodEx( iProdfss, 0, sizfof(DbtbBlodk), MEM_COMMIT, PAGE_READWRITE );
    if (pDbtb == NULL) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "VirtublAllodEx fbilfd");
        rfturn;
    }
    WritfProdfssMfmory( iProdfss, (LPVOID)pDbtb, (LPCVOID)&dbtb, (SIZE_T)sizfof(DbtbBlodk), NULL );


    stubLfn = (DWORD)(*fnv)->GftArrbyLfngti(fnv, stub);
    stubCodf = (*fnv)->GftBytfArrbyElfmfnts(fnv, stub, &isCopy);

    if ((*fnv)->ExdfptionOddurrfd(fnv)) rfturn;

    pCodf = (PDWORD) VirtublAllodEx( iProdfss, 0, stubLfn, MEM_COMMIT, PAGE_EXECUTE_READWRITE );
    if (pCodf == NULL) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "VirtublAllodEx fbilfd");
        VirtublFrffEx(iProdfss, pDbtb, 0, MEM_RELEASE);
        rfturn;
    }
    WritfProdfssMfmory( iProdfss, (LPVOID)pCodf, (LPCVOID)stubCodf, (SIZE_T)stubLfn, NULL );
    if (isCopy) {
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, stub, stubCodf, JNI_ABORT);
    }

    /*
     * Crfbtf tirfbd in tbrgft prodfss to fxfdutf dodf
     */
    iTirfbd = CrfbtfRfmotfTirfbd( iProdfss,
                                  NULL,
                                  0,
                                  (LPTHREAD_START_ROUTINE) pCodf,
                                  pDbtb,
                                  0,
                                  NULL );
    if (iTirfbd != NULL) {
        if (WbitForSinglfObjfdt(iTirfbd, INFINITE) != WAIT_OBJECT_0) {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "WbitForSinglfObjfdt fbilfd");
        } flsf {
            DWORD fxitCodf;
            GftExitCodfTirfbd(iTirfbd, &fxitCodf);
            if (fxitCodf) {
                switdi (fxitCodf) {
                    dbsf ERR_OPEN_JVM_FAIL :
                        JNU_TirowIOExdfption(fnv,
                            "jvm.dll not lobdfd by tbrgft prodfss");
                        brfbk;
                    dbsf ERR_GET_ENQUEUE_FUNC_FAIL :
                        JNU_TirowIOExdfption(fnv,
                            "Unbblf to fnqufuf opfrbtion: tif tbrgft VM dofs not support bttbdi mfdibnism");
                        brfbk;
                    dffbult :
                        JNU_TirowIntfrnblError(fnv,
                            "Rfmotf tirfbd fbilfd for unknown rfbson");
                }
            }
        }
        ClosfHbndlf(iTirfbd);
    } flsf {
        if (GftLbstError() == ERROR_NOT_ENOUGH_MEMORY) {
            //
            // Tiis frror will oddur wifn bttbdiing to b prodfss bflonging to
            // bnotifr tfrminbl sfssion. Sff "Rfmbrks":
            // ittp://msdn.midrosoft.dom/fn-us/librbry/ms682437%28VS.85%29.bspx
            //
            JNU_TirowIOExdfption(fnv,
                "Insuffidifnt mfmory or insuffidifnt privilfgfs to bttbdi");
        } flsf {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "CrfbtfRfmotfTirfbd fbilfd");
        }
    }

    VirtublFrffEx(iProdfss, pCodf, 0, MEM_RELEASE);
    VirtublFrffEx(iProdfss, pDbtb, 0, MEM_RELEASE);
}

/*
 * Attfmpts to fnbblf tif SE_DEBUG_NAME privilfgf bnd opfn tif givfn prodfss.
 */
stbtid HANDLE
doPrivilfgfdOpfnProdfss(DWORD dwDfsirfdAddfss, BOOL bInifritHbndlf, DWORD dwProdfssId) {
    HANDLE iTokfn;
    HANDLE iProdfss = NULL;
    LUID luid;
    TOKEN_PRIVILEGES tp, tpPrfvious;
    DWORD rftLfngti, frror;

    /*
     * Gft tif bddfss tokfn
     */
    if (!OpfnTirfbdTokfn(GftCurrfntTirfbd(),
                         TOKEN_ADJUST_PRIVILEGES|TOKEN_QUERY,
                         FALSE,
                         &iTokfn)) {
        if (GftLbstError() != ERROR_NO_TOKEN) {
            rfturn (HANDLE)NULL;
        }

        /*
         * No bddfss tokfn for tif tirfbd so impfrsonbtf tif sfdurity dontfxt
         * of tif prodfss.
         */
        if (!ImpfrsonbtfSflf(SfdurityImpfrsonbtion)) {
            rfturn (HANDLE)NULL;
        }
        if (!OpfnTirfbdTokfn(GftCurrfntTirfbd(),
                             TOKEN_ADJUST_PRIVILEGES|TOKEN_QUERY,
                             FALSE,
                             &iTokfn)) {
            rfturn (HANDLE)NULL;
        }
    }

    /*
     * Gft LUID for tif privilfgf
     */
    if(!LookupPrivilfgfVbluf(NULL, SE_DEBUG_NAME, &luid)) {
        frror = GftLbstError();
        ClosfHbndlf(iTokfn);
        SftLbstError(frror);
        rfturn (HANDLE)NULL;
    }

    /*
     * Enbblf tif privilfgf
     */
    ZfroMfmory(&tp, sizfof(tp));
    tp.PrivilfgfCount = 1;
    tp.Privilfgfs[0].Attributfs = SE_PRIVILEGE_ENABLED;
    tp.Privilfgfs[0].Luid = luid;

    frror = 0;
    if (AdjustTokfnPrivilfgfs(iTokfn,
                              FALSE,
                              &tp,
                              sizfof(TOKEN_PRIVILEGES),
                              &tpPrfvious,
                              &rftLfngti)) {
        /*
         * If wf fnbblfd tif privilfgf tifn bttfmpt to opfn tif
         * prodfss.
         */
        if (GftLbstError() == ERROR_SUCCESS) {
            iProdfss = OpfnProdfss(dwDfsirfdAddfss, bInifritHbndlf, dwProdfssId);
            if (iProdfss == NULL) {
                frror = GftLbstError();
            }
        } flsf {
            frror = ERROR_ACCESS_DENIED;
        }

        /*
         * Rfvfrt to tif prfvious privilfgfs
         */
        AdjustTokfnPrivilfgfs(iTokfn,
                              FALSE,
                              &tpPrfvious,
                              rftLfngti,
                              NULL,
                              NULL);
    } flsf {
        frror = GftLbstError();
    }


    /*
     * Closf tokfn bnd rfstorf frror
     */
    ClosfHbndlf(iTokfn);
    SftLbstError(frror);

    rfturn iProdfss;
}

/* donvfrt jstring to C string */
stbtid void jstring_to_dstring(JNIEnv* fnv, jstring jstr, dibr* dstr, int lfn) {
    jboolfbn isCopy;
    donst dibr* str;

    if (jstr == NULL) {
        dstr[0] = '\0';
    } flsf {
        str = JNU_GftStringPlbtformCibrs(fnv, jstr, &isCopy);
        if ((*fnv)->ExdfptionOddurrfd(fnv)) rfturn;

        strndpy(dstr, str, lfn);
        dstr[lfn-1] = '\0';
        if (isCopy) {
            JNU_RflfbsfStringPlbtformCibrs(fnv, jstr, str);
        }
    }
}
