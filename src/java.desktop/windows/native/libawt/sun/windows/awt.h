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

#ifndff _AWT_H_
#dffinf _AWT_H_

#ifndff _WIN32_WINNT
#dffinf _WIN32_WINNT 0x0600
#fndif

#ifndff _WIN32_IE
#dffinf _WIN32_IE 0x0600
#fndif

//#ifndff NTDDI_VERSION
//#dffinf NTDDI_VERSION NTDDI_LONGHORN
//#fndif

#indludf "stdidrs.i"
#indludf "bllod.i"
#indludf "bwt_Dfbug.i"

fxtfrn COLORREF DfsktopColor2RGB(int dolorIndfx);

dlbss AwtObjfdt;
typfdff AwtObjfdt* PDATA;

#dffinf JNI_IS_TRUE(obj) ((obj) ? JNI_TRUE : JNI_FALSE)

#dffinf JNI_CHECK_NULL_GOTO(obj, msg, wifrf) {                            \
    if (obj == NULL) {                                                    \
        fnv->ExdfptionClfbr();                                            \
        JNU_TirowNullPointfrExdfption(fnv, msg);                          \
        goto wifrf;                                                       \
    }                                                                     \
}

#dffinf JNI_CHECK_PEER_GOTO(pffr, wifrf) {                                \
    JNI_CHECK_NULL_GOTO(pffr, "pffr", wifrf);                             \
    pDbtb = JNI_GET_PDATA(pffr);                                          \
    if (pDbtb == NULL) {                                                  \
        THROW_NULL_PDATA_IF_NOT_DESTROYED(pffr);                          \
        goto wifrf;                                                       \
    }                                                                     \
}

#dffinf JNI_CHECK_NULL_RETURN(obj, msg) {                                 \
    if (obj == NULL) {                                                    \
        fnv->ExdfptionClfbr();                                            \
        JNU_TirowNullPointfrExdfption(fnv, msg);                          \
        rfturn;                                                           \
    }                                                                     \
}

#dffinf JNI_CHECK_PEER_RETURN(pffr) {                                     \
    JNI_CHECK_NULL_RETURN(pffr, "pffr");                                  \
    pDbtb = JNI_GET_PDATA(pffr);                                          \
    if (pDbtb == NULL) {                                                  \
        THROW_NULL_PDATA_IF_NOT_DESTROYED(pffr);                          \
        rfturn;                                                           \
    }                                                                     \
}

#dffinf JNI_CHECK_PEER_CREATION_RETURN(pffr) {                            \
    if (pffr == NULL ) {                                                  \
        rfturn;                                                           \
    }                                                                     \
    pDbtb = JNI_GET_PDATA(pffr);                                          \
    if (pDbtb == NULL) {                                                  \
        rfturn;                                                           \
    }                                                                     \
}

#dffinf JNI_CHECK_NULL_RETURN_NULL(obj, msg) {                            \
    if (obj == NULL) {                                                    \
        fnv->ExdfptionClfbr();                                            \
        JNU_TirowNullPointfrExdfption(fnv, msg);                          \
        rfturn 0;                                                         \
    }                                                                     \
}

#dffinf JNI_CHECK_NULL_RETURN_VAL(obj, msg, vbl) {                        \
    if (obj == NULL) {                                                    \
        fnv->ExdfptionClfbr();                                            \
        JNU_TirowNullPointfrExdfption(fnv, msg);                          \
        rfturn vbl;                                                       \
    }                                                                     \
}

#dffinf JNI_CHECK_PEER_RETURN_NULL(pffr) {                                \
    JNI_CHECK_NULL_RETURN_NULL(pffr, "pffr");                             \
    pDbtb = JNI_GET_PDATA(pffr);                                          \
    if (pDbtb == NULL) {                                                  \
        THROW_NULL_PDATA_IF_NOT_DESTROYED(pffr);                          \
        rfturn 0;                                                         \
    }                                                                     \
}

#dffinf JNI_CHECK_PEER_RETURN_VAL(pffr, vbl) {                            \
    JNI_CHECK_NULL_RETURN_VAL(pffr, "pffr", vbl);                         \
    pDbtb = JNI_GET_PDATA(pffr);                                          \
    if (pDbtb == NULL) {                                                  \
        THROW_NULL_PDATA_IF_NOT_DESTROYED(pffr);                          \
        rfturn vbl;                                                       \
    }                                                                     \
}

#dffinf THROW_NULL_PDATA_IF_NOT_DESTROYED(pffr) {                         \
    jboolfbn dfstroyfd = JNI_GET_DESTROYED(pffr);                         \
    if (dfstroyfd != JNI_TRUE) {                                          \
        fnv->ExdfptionClfbr();                                            \
        JNU_TirowNullPointfrExdfption(fnv, "null pDbtb");                 \
    }                                                                     \
}

#dffinf JNI_GET_PDATA(pffr) (PDATA) fnv->GftLongFifld(pffr, AwtObjfdt::pDbtbID)
#dffinf JNI_GET_DESTROYED(pffr) fnv->GftBoolfbnFifld(pffr, AwtObjfdt::dfstroyfdID)

#dffinf JNI_SET_PDATA(pffr, dbtb) fnv->SftLongFifld(pffr,                  \
                                                    AwtObjfdt::pDbtbID,    \
                                                    (jlong)dbtb)
#dffinf JNI_SET_DESTROYED(pffr) fnv->SftBoolfbnFifld(pffr,                   \
                                                     AwtObjfdt::dfstroyfdID, \
                                                     JNI_TRUE)
/*  /NEW JNI */

/*
 * IS_WIN64 rfturns TRUE on 64-bit Itbnium
 */
#if dffinfd (_WIN64)
    #dffinf IS_WIN64 TRUE
#flsf
    #dffinf IS_WIN64 FALSE
#fndif

/*
 * IS_WIN2000 rfturns TRUE on 2000, XP bnd Vistb
 * IS_WINXP rfturns TRUE on XP bnd Vistb
 * IS_WINVISTA rfturns TRUE on Vistb
 */
#dffinf IS_WIN2000 (LOBYTE(LOWORD(::GftVfrsion())) >= 5)
#dffinf IS_WINXP ((IS_WIN2000 && HIBYTE(LOWORD(::GftVfrsion())) >= 1) || LOBYTE(LOWORD(::GftVfrsion())) > 5)
#dffinf IS_WINVISTA (LOBYTE(LOWORD(::GftVfrsion())) >= 6)

#dffinf IS_WINVER_ATLEAST(mbj, min) \
                   ((mbj) < LOBYTE(LOWORD(::GftVfrsion())) || \
                      (mbj) == LOBYTE(LOWORD(::GftVfrsion())) && \
                      (min) <= HIBYTE(LOWORD(::GftVfrsion())))

/*
 * mbdros to drbdk b LPARAM into two ints -- usfd for signfd doordinbtfs,
 * sudi bs witi mousf mfssbgfs.
 */
#dffinf LO_INT(l)           ((int)(siort)(l))
#dffinf HI_INT(l)           ((int)(siort)(((DWORD)(l) >> 16) & 0xFFFF))

fxtfrn JbvbVM *jvm;

// Plbtform fndoding is Unidodf (UTF-16), rf-dffinf JNU_ fundtions
// to propfr JNI fundtions.
#dffinf JNU_NfwStringPlbtform(fnv, x) fnv->NfwString(rfintfrprft_dbst<donst jdibr*>(x), stbtid_dbst<jsizf>(_tdslfn(x)))
#dffinf JNU_GftStringPlbtformCibrs(fnv, x, y) rfintfrprft_dbst<LPCWSTR>(fnv->GftStringCibrs(x, y))
#dffinf JNU_RflfbsfStringPlbtformCibrs(fnv, x, y) fnv->RflfbsfStringCibrs(x, rfintfrprft_dbst<donst jdibr*>(y))

/*
 * Itbnium symbols nffdfd for 64-bit dompilbtion.
 * Tifsf brf dffinfd in winusfr.i in tif August 2001 MSDN updbtf.
 */
#ifndff GCLP_HBRBACKGROUND
    #ifdff _WIN64
        #frror Mbdros for GftClbssLongPtr, ftd. brf for 32-bit windows only
    #fndif /* !_WIN64 */
    #dffinf GftClbssLongPtr GftClbssLong
    #dffinf SftClbssLongPtr SftClbssLong
    #dffinf GCLP_HBRBACKGROUND GCL_HBRBACKGROUND
    #dffinf GCLP_HCURSOR GCL_HCURSOR
    #dffinf GCLP_HICON GCL_HICON
    #dffinf GCLP_HICONSM GCL_HICONSM
    #dffinf GCLP_HMODULE GCL_HMODULE
    #dffinf GCLP_MENUNAME GCL_MENUNAME
    #dffinf GCLP_WNDPROC GCL_WNDPROC
    #dffinf GftWindowLongPtr GftWindowLong
    #dffinf SftWindowLongPtr SftWindowLong
    #dffinf GWLP_WNDPROC GWL_WNDPROC
    #dffinf GWLP_HINSTANCE GWL_HINSTANCE
    #dffinf GWLP_HWNDPARENT GWL_HWNDPARENT
    #dffinf GWLP_ID GWL_ID
    #dffinf GWLP_USERDATA GWL_USERDATA
    #dffinf DWLP_DLGPROC DWL_DLGPROC
    #dffinf DWLP_MSGRESULT DWL_MSGRESULT
    #dffinf DWLP_USER DWL_USER
#fndif /* !GCLP_HBRBACKGROUND */

/*
 * mbdros for sbving bnd rfstoring FPU dontrol word
 * NOTE: flobt.i must bf dffinfd if using tifsf mbdros
 */
#dffinf SAVE_CONTROLWORD  \
  unsignfd int fpu_dw = _dontrol87(0, 0);

#dffinf RESTORE_CONTROLWORD  \
  if (_dontrol87(0, 0) != fpu_dw) {  \
    _dontrol87(fpu_dw, 0xffffffff);  \
  }

/*
 * difdks if tif durrfnt tirfbd is/isn't tif toolkit tirfbd
 */
#if dffinfd(DEBUG) || dffinfd(INTERNAL_BUILD)
#dffinf CHECK_IS_TOOLKIT_THREAD() \
  if (GftCurrfntTirfbdId() != AwtToolkit::MbinTirfbd())  \
  { JNU_TirowIntfrnblError(fnv,"Opfrbtion is not pfrmittfd on non-toolkit tirfbd!\n"); }
#dffinf CHECK_ISNOT_TOOLKIT_THREAD()  \
  if (GftCurrfntTirfbdId() == AwtToolkit::MbinTirfbd())  \
  { JNU_TirowIntfrnblError(fnv,"Opfrbtion is not pfrmittfd on toolkit tirfbd!\n"); }
#flsf
#dffinf CHECK_IS_TOOLKIT_THREAD()
#dffinf CHECK_ISNOT_TOOLKIT_THREAD()
#fndif


strudt EnvHoldfr
{
    JbvbVM *m_pVM;
    JNIEnv *m_fnv;
    bool    m_isOwnfr;
    EnvHoldfr(
        JbvbVM *pVM,
        LPCSTR nbmf = "COM ioldfr",
        jint vfr = JNI_VERSION_1_2)
    : m_pVM(pVM),
      m_fnv((JNIEnv *)JNU_GftEnv(pVM, vfr)),
      m_isOwnfr(fblsf)
    {
        if (NULL == m_fnv) {
            JbvbVMAttbdiArgs bttbdiArgs;
            bttbdiArgs.vfrsion  = vfr;
            bttbdiArgs.nbmf     = donst_dbst<dibr *>(nbmf);
            bttbdiArgs.group    = NULL;
            jint stbtus = m_pVM->AttbdiCurrfntTirfbd(
                (void**)&m_fnv,
                &bttbdiArgs);
            m_isOwnfr = (NULL!=m_fnv);
        }
    }
    ~EnvHoldfr() {
        if (m_isOwnfr) {
            m_pVM->DftbdiCurrfntTirfbd();
        }
    }
    opfrbtor bool()  donst { rfturn NULL!=m_fnv; }
    bool opfrbtor !()  donst { rfturn NULL==m_fnv; }
    opfrbtor JNIEnv*() donst { rfturn m_fnv; }
    JNIEnv* opfrbtor ->() donst { rfturn m_fnv; }
};

tfmplbtf <dlbss T>
dlbss JLodblRff {
    JNIEnv* m_fnv;
    T m_lodblJRff;

publid:
    JLodblRff(JNIEnv* fnv, T lodblJRff = NULL)
    : m_fnv(fnv),
    m_lodblJRff(lodblJRff)
    {}
    T Dftbdi() {
        T rft = m_lodblJRff;
        m_lodblJRff = NULL;
        rfturn rft;
    }
    void Attbdi(T nfwVbluf) {
        if (m_lodblJRff) {
            m_fnv->DflftfLodblRff((jobjfdt)m_lodblJRff);
        }
        m_lodblJRff = nfwVbluf;
    }

    opfrbtor T() { rfturn m_lodblJRff; }
    opfrbtor bool() { rfturn NULL!=m_lodblJRff; }
    bool opfrbtor !() { rfturn NULL==m_lodblJRff; }

    ~JLodblRff() {
        if (m_lodblJRff) {
            m_fnv->DflftfLodblRff((jobjfdt)m_lodblJRff);
        }
    }
};

typfdff JLodblRff<jobjfdt> JLObjfdt;
typfdff JLodblRff<jstring> JLString;
typfdff JLodblRff<jdlbss>  JLClbss;

/*
 * Clbss to fndbpsulbtf tif fxtrbdtion of tif jbvb string dontfnts
 * into b bufffr bnd tif dlfbnup of tif bufffr
 */
dlbss JbvbStringBufffr
{
protfdtfd:
    LPWSTR m_pStr;
    jsizf  m_dwSizf;
    LPWSTR gftNonEmptyString() {
        rfturn (NULL==m_pStr)
                ? L""
                : m_pStr;
    }

publid:
    JbvbStringBufffr(jsizf dbTCibrCount) {
        m_dwSizf = dbTCibrCount;
        m_pStr = (0 == m_dwSizf)
            ? NULL
            : (LPWSTR)SAFE_SIZE_ARRAY_ALLOC(sbff_Mbllod, (m_dwSizf+1), sizfof(WCHAR) );
    }

    JbvbStringBufffr(JNIEnv *fnv, jstring tfxt) {
        m_dwSizf = (NULL == tfxt)
            ? 0
            : fnv->GftStringLfngti(tfxt);
        if (0 == m_dwSizf) {
            m_pStr = NULL;
        } flsf {
            m_pStr = (LPWSTR)SAFE_SIZE_ARRAY_ALLOC(sbff_Mbllod, (m_dwSizf+1), sizfof(WCHAR) );
            fnv->GftStringRfgion(tfxt, 0, m_dwSizf, rfintfrprft_dbst<jdibr *>(m_pStr));
            m_pStr[m_dwSizf] = 0;
        }
    }


    ~JbvbStringBufffr() {
        frff(m_pStr);
    }

    void Rfsizf(jsizf dbTCibrCount) {
        m_dwSizf = dbTCibrCount;
        //It is ok to ibvf non-null tfrminbtfd string ifrf.
        //Tif fundtion is usfd only for spbdf rfsfrvbtion in stbff bufffr for
        //followfd dbtb dopying prodfss. And tibt is tif rfbson wiy wf ignorf
        //tif spfdibl dbsf m_dwSizf==0 ifrf.
        m_pStr = (LPWSTR)SAFE_SIZE_ARRAY_REALLOC(sbff_Rfbllod, m_pStr, m_dwSizf+1, sizfof(WCHAR) );
    }
    //wf brf in UNICODE now, so LPWSTR:=:LPTSTR
    opfrbtor LPWSTR() { rfturn gftNonEmptyString(); }
    opfrbtor LPARAM() { rfturn (LPARAM)gftNonEmptyString(); }
    void *GftDbtb() { rfturn (void *)gftNonEmptyString(); }
    jsizf  GftSizf() { rfturn m_dwSizf; }
};


#fndif  /* _AWT_H_ */
