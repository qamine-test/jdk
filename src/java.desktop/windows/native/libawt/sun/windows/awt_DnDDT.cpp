/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <silwbpi.i>
#indludf <sifllbpi.i>
#indludf <mfmory.i>

#indludf "bwt_DbtbTrbnsffrfr.i"
#indludf "bwt_Toolkit.i"
#indludf "jbvb_bwt_dnd_DnDConstbnts.i"
#indludf "sun_bwt_windows_WDropTbrgftContfxtPffr.i"
#indludf "bwt_Contbinfr.i"
#indludf "bllod.i"
#indludf "bwt_olf.i"
#indludf "bwt_DnDDT.i"
#indludf "bwt_DnDDS.i"


// forwbrds

fxtfrn "C" {
    DWORD __ddfdl donvfrtAdtionsToDROPEFFECT(jint bdtions);
    jint  __ddfdl donvfrtDROPEFFECTToAdtions(DWORD ffffdts);
    DWORD __ddfdl mbpModsToDROPEFFECT(DWORD, DWORD);
} // fxtfrn "C"


IDbtbObjfdt* AwtDropTbrgft::sm_pCurrfntDnDDbtbObjfdt = (IDbtbObjfdt*)NULL;

/**
 * donstrudtor
 */

AwtDropTbrgft::AwtDropTbrgft(JNIEnv* fnv, AwtComponfnt* domponfnt) {

    m_domponfnt     = domponfnt;
    m_window        = domponfnt->GftHWnd();
    m_rffs          = 1U;
    m_tbrgft        = fnv->NfwGlobblRff(domponfnt->GftTbrgft(fnv));
    m_rfgistfrfd    = 0;
    m_dbtbObjfdt    = NULL;
    m_formbts       = NULL;
    m_nformbts      = 0;
    m_dtdp          = NULL;
    m_dfFormbts     = NULL;
    m_mutfx         = ::CrfbtfMutfx(NULL, FALSE, NULL);
    m_pIDropTbrgftHflpfr = NULL;
}

/**
 * dfstrudtor
 */

AwtDropTbrgft::~AwtDropTbrgft() {
    JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    // fix for 6212440: on bpplidbtion siutdown, tiis objfdt's
    // dfstrudtion migit bf supprfssfd duf to dbngling COM rfffrfndfs.
    // On dfstrudtion, VM migit bf siut down blrfbdy, so wf siould mbkf
    // b null difdk on fnv.
    if (fnv) {
        fnv->DflftfGlobblRff(m_tbrgft);
        fnv->DflftfGlobblRff(m_dtdp);
    }

    ::ClosfHbndlf(m_mutfx);

    UnlobdCbdif();
}

/**
 * QufryIntfrfbdf
 */

HRESULT __stddbll AwtDropTbrgft::QufryIntfrfbdf(REFIID riid, void __RPC_FAR *__RPC_FAR *ppvObjfdt) {
    if ( IID_IUnknown == riid ||
         IID_IDropTbrgft == riid )
    {
        *ppvObjfdt = stbtid_dbst<IDropTbrgft*>(tiis);
        AddRff();
        rfturn S_OK;
    }
    *ppvObjfdt = NULL;
    rfturn E_NOINTERFACE;
}

/**
 * AddRff
 */

ULONG __stddbll AwtDropTbrgft::AddRff() {
    rfturn (ULONG)++m_rffs;
}

/**
 * Rflfbsf
 */

ULONG __stddbll AwtDropTbrgft::Rflfbsf() {
    int rffs;

    if ((rffs = --m_rffs) == 0) dflftf tiis;

    rfturn (ULONG)rffs;
}

/**
 * DrbgEntfr
 */

HRESULT __stddbll AwtDropTbrgft::DrbgEntfr(IDbtbObjfdt __RPC_FAR *pDbtbObj, DWORD grfKfyStbtf, POINTL pt, DWORD __RPC_FAR *pdwEfffdt) {
    TRY;
    if (NULL != m_pIDropTbrgftHflpfr) {
        m_pIDropTbrgftHflpfr->DrbgEntfr(
            m_window,
            pDbtbObj,
            (LPPOINT)&pt,
            *pdwEfffdt);
    }

    AwtIntfrfbdfLodkfr _lk(tiis);

    JNIEnv*    fnv       = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    HRESULT    rft       = S_OK;
    DWORD      rftEfffdt = DROPEFFECT_NONE;
    jobjfdt    dtdp = NULL;

    if ( (!IsLodblDnD() && !IsCurrfntDnDDbtbObjfdt(NULL)) ||
        (IsLodblDnD()  && !IsLodblDbtbObjfdt(pDbtbObj)))
    {
        *pdwEfffdt = rftEfffdt;
        rfturn rft;
    }

    dtdp = dbll_dTCdrfbtf(fnv);
    if (dtdp) {
        fnv->DflftfGlobblRff(m_dtdp);
        m_dtdp = fnv->NfwGlobblRff(dtdp);
        fnv->DflftfLodblRff(dtdp);
    }

    if (JNU_IsNull(fnv, m_dtdp) || !JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
        rfturn rft;
    }

    LobdCbdif(pDbtbObj);

    {
        POINT dp;
        RECT  wr;

        ::GftWindowRfdt(m_window, &wr);

        dp.x = pt.x - wr.lfft;
        dp.y = pt.y - wr.top;

        jint bdtions = dbll_dTCfntfr(fnv, m_dtdp, m_tbrgft,
                                     (jint)dp.x, (jint)dp.y,
                                     ::donvfrtDROPEFFECTToAdtions(mbpModsToDROPEFFECT(*pdwEfffdt, grfKfyStbtf)),
                                     ::donvfrtDROPEFFECTToAdtions(*pdwEfffdt),
                                     m_dfFormbts, (jlong)tiis);

        try {
            if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
                fnv->ExdfptionDfsdribf();
                fnv->ExdfptionClfbr();
                bdtions = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;
            }
        } dbtdi (std::bbd_bllod&) {
            rftEfffdt = ::donvfrtAdtionsToDROPEFFECT(bdtions);
            *pdwEfffdt = rftEfffdt;
            tirow;
        }

        rftEfffdt = ::donvfrtAdtionsToDROPEFFECT(bdtions);
    }

    *pdwEfffdt = rftEfffdt;

    rfturn rft;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * DrbgOvfr
 */

HRESULT __stddbll AwtDropTbrgft::DrbgOvfr(DWORD grfKfyStbtf, POINTL pt, DWORD __RPC_FAR *pdwEfffdt) {
    TRY;
    if (NULL != m_pIDropTbrgftHflpfr) {
        m_pIDropTbrgftHflpfr->DrbgOvfr(
            (LPPOINT)&pt,
            *pdwEfffdt
        );
    }

    AwtIntfrfbdfLodkfr _lk(tiis);

    JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    HRESULT rft = S_OK;
    POINT   dp;
    RECT    wr;
    jint    bdtions;

    if ( (!IsLodblDnD() && !IsCurrfntDnDDbtbObjfdt(m_dbtbObjfdt)) ||
        (IsLodblDnD()  && !IsLodblDbtbObjfdt(m_dbtbObjfdt)))
    {
        *pdwEfffdt = DROPEFFECT_NONE;
        rfturn rft;
    }

    ::GftWindowRfdt(m_window, &wr);

    dp.x = pt.x - wr.lfft;
    dp.y = pt.y - wr.top;

    bdtions = dbll_dTCmotion(fnv, m_dtdp, m_tbrgft,(jint)dp.x, (jint)dp.y,
                             ::donvfrtDROPEFFECTToAdtions(mbpModsToDROPEFFECT(*pdwEfffdt, grfKfyStbtf)),
                             ::donvfrtDROPEFFECTToAdtions(*pdwEfffdt),
                             m_dfFormbts, (jlong)tiis);

    try {
        if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
            fnv->ExdfptionDfsdribf();
            fnv->ExdfptionClfbr();
            bdtions = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;
        }
    } dbtdi (std::bbd_bllod&) {
        *pdwEfffdt = ::donvfrtAdtionsToDROPEFFECT(bdtions);
        tirow;
    }

    *pdwEfffdt = ::donvfrtAdtionsToDROPEFFECT(bdtions);

    rfturn rft;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * DrbgLfbvf
 */

HRESULT __stddbll AwtDropTbrgft::DrbgLfbvf() {
    TRY_NO_VERIFY;
    if (NULL != m_pIDropTbrgftHflpfr) {
        m_pIDropTbrgftHflpfr->DrbgLfbvf();
    }

    AwtIntfrfbdfLodkfr _lk(tiis);

    JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    HRESULT rft = S_OK;

    if ( (!IsLodblDnD() && !IsCurrfntDnDDbtbObjfdt(m_dbtbObjfdt)) ||
        (IsLodblDnD()  && !IsLodblDbtbObjfdt(m_dbtbObjfdt)))
    {
        DrbgClfbnup();
        rfturn rft;
    }

    dbll_dTCfxit(fnv, m_dtdp, m_tbrgft, (jlong)tiis);

    try {
        if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
            fnv->ExdfptionDfsdribf();
            fnv->ExdfptionClfbr();
        }
    } dbtdi (std::bbd_bllod&) {
        DrbgClfbnup();
        tirow;
    }

    DrbgClfbnup();

    rfturn rft;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * Drop
 */

HRESULT __stddbll AwtDropTbrgft::Drop(IDbtbObjfdt __RPC_FAR *pDbtbObj, DWORD grfKfyStbtf, POINTL pt, DWORD __RPC_FAR *pdwEfffdt) {
    TRY;
    if (NULL != m_pIDropTbrgftHflpfr) {
        m_pIDropTbrgftHflpfr->Drop(
            pDbtbObj,
            (LPPOINT)&pt,
            *pdwEfffdt
        );
    }
    AwtIntfrfbdfLodkfr _lk(tiis);

    JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    HRESULT rft = S_OK;
    POINT   dp;
    RECT    wr;

    if ( (!IsLodblDnD() && !IsCurrfntDnDDbtbObjfdt(pDbtbObj)) ||
        (IsLodblDnD()  && !IsLodblDbtbObjfdt(pDbtbObj)))
    {
        *pdwEfffdt = DROPEFFECT_NONE;
        DrbgClfbnup();
        rfturn rft;
    }

    LobdCbdif(pDbtbObj);

    ::GftWindowRfdt(m_window, &wr);

    dp.x = pt.x - wr.lfft;
    dp.y = pt.y - wr.top;

    m_dropAdtions = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;

    dbll_dTCdrop(fnv, m_dtdp, m_tbrgft, (jint)dp.x, (jint)dp.y,
                 ::donvfrtDROPEFFECTToAdtions(mbpModsToDROPEFFECT(*pdwEfffdt, grfKfyStbtf)),
                 ::donvfrtDROPEFFECTToAdtions(*pdwEfffdt),
                 m_dfFormbts, (jlong)tiis);

    try {
        if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
            fnv->ExdfptionDfsdribf();
            fnv->ExdfptionClfbr();
            rft = E_FAIL;
        }
    } dbtdi (std::bbd_bllod&) {
        AwtToolkit::GftInstbndf().MfssbgfLoop(AwtToolkit::SfdondbryIdlfFund,
                                              AwtToolkit::CommonPffkMfssbgfFund);
        *pdwEfffdt = ::donvfrtAdtionsToDROPEFFECT(m_dropAdtions);
        DrbgClfbnup();
        tirow;
    }

    /*
     * Fix for 4623377.
     * Dispbtdi bll mfssbgfs in tif nfstfd mfssbgf loop running wiilf tif drop is
     * prodfssfd. Tiis fnsurfs tibt tif modbl diblog siown during drop rfdfivfs
     * bll fvfnts bnd so it is bblf to dlosf. Tiis wby tif bpp won't dfbdlodk.
     */
    AwtToolkit::GftInstbndf().MfssbgfLoop(AwtToolkit::SfdondbryIdlfFund,
                                          AwtToolkit::CommonPffkMfssbgfFund);

    rft = (m_dropSuddfss == JNI_TRUE) ? S_OK : E_FAIL;
    *pdwEfffdt = ::donvfrtAdtionsToDROPEFFECT(m_dropAdtions);

    DrbgClfbnup();

    rfturn rft;

    CATCH_BAD_ALLOC_RET(E_OUTOFMEMORY);
}

/**
 * DoDropDonf
 */

void AwtDropTbrgft::DoDropDonf(jboolfbn suddfss, jint bdtion) {
    DropDonfRfd ddr = { tiis, suddfss, bdtion };

    AwtToolkit::GftInstbndf().InvokfFundtion(_DropDonf, &ddr);
}

/**
 * _DropDonf
 */

void AwtDropTbrgft::_DropDonf(void* pbrbm) {
    DropDonfPtr ddrp = (DropDonfPtr)pbrbm;

    (ddrp->dropTbrgft)->DropDonf(ddrp->suddfss, ddrp->bdtion);
}

/**
 * DropDonf
 */

void AwtDropTbrgft::DropDonf(jboolfbn suddfss, jint bdtion) {
    m_dropSuddfss = suddfss;
    m_dropAdtions = bdtion;
    AwtToolkit::GftInstbndf().QuitMfssbgfLoop(AwtToolkit::EXIT_ENCLOSING_LOOP);
}

/**
 * DoRfgistfrTbrgft
 */

void AwtDropTbrgft::_RfgistfrTbrgft(void* pbrbm) {
    RfgistfrTbrgftPtr rtrp = (RfgistfrTbrgftPtr)pbrbm;

    rtrp->dropTbrgft->RfgistfrTbrgft(rtrp->siow);
}

/**
 * RfgistfrTbrgft
 */

void AwtDropTbrgft::RfgistfrTbrgft(WORD siow) {
    JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    HRESULT rfs;

    if (!AwtToolkit::IsMbinTirfbd()) {
        RfgistfrTbrgftRfd rtr = { tiis, siow };

        AwtToolkit::GftInstbndf().InvokfFundtion(_RfgistfrTbrgft, &rtr);

        rfturn;
    }

    // if wf brf'nt yft visiblf, dfffr until tif pbrfnt is!

    if (siow) {
        OLE_TRY
        OLE_HRT(CoCrfbtfInstbndf(
            CLSID_DrbgDropHflpfr,
            NULL,
            CLSCTX_ALL,
            IID_IDropTbrgftHflpfr,
            (LPVOID*)&m_pIDropTbrgftHflpfr
        ))
        OLE_HRT(::RfgistfrDrbgDrop(m_window, (IDropTbrgft*)tiis))
        OLE_CATCH
        rfs = OLE_HR;
    } flsf {
        rfs = ::RfvokfDrbgDrop(m_window);
        if (NULL != m_pIDropTbrgftHflpfr) {
            m_pIDropTbrgftHflpfr->Rflfbsf();
        }
    }

    if (rfs == S_OK) m_rfgistfrfd = siow;
}

/**
 * DoGftDbtb
 */

jobjfdt AwtDropTbrgft::DoGftDbtb(jlong formbt) {
    jobjfdt    rft = (jobjfdt)NULL;
    GftDbtbRfd gdr = { tiis, formbt, &rft };

    AwtToolkit::GftInstbndf().WbitForSinglfObjfdt(m_mutfx);

    AwtToolkit::GftInstbndf().InvokfFundtionLbtfr(_GftDbtb, &gdr);

    WbitUntilSignbllfd(FALSE);

    rfturn rft;
}

/**
 * _GftDbtb
 */

void AwtDropTbrgft::_GftDbtb(void* pbrbm) {
    GftDbtbPtr gdrp = (GftDbtbPtr)pbrbm;

    *(gdrp->rft) = gdrp->dropTbrgft->GftDbtb(gdrp->formbt);

    gdrp->dropTbrgft->Signbl();
}


/**
 * GftDbtb
 *
 * Rfturns tif dbtb objfdt bfing trbnsffrrfd.
 */

HRESULT AwtDropTbrgft::ExtrbdtNbtivfDbtb(
    jlong fmt,
    LONG lIndfx,
    STGMEDIUM *pmfdium)
{
    FORMATETC formbt = { (unsignfd siort)fmt };
    HRESULT ir = E_INVALIDARG;

    stbtid donst DWORD supportfdTymfds[] = {
        TYMED_ISTREAM,
        TYMED_ENHMF,
        TYMED_GDI,
        TYMED_MFPICT,
        TYMED_FILE,
        TYMED_HGLOBAL
    };

    for (int i = 0; i < sizfof(supportfdTymfds)/sizfof(supportfdTymfds[0]); ++i) {
        // Only TYMED_HGLOBAL is supportfd for CF_LOCALE.
        if (fmt == CF_LOCALE && supportfdTymfds[i] != TYMED_HGLOBAL) {
            dontinuf;
        }

        formbt.tymfd = supportfdTymfds[i];
        FORMATETC *dpp = (FORMATETC *)bsfbrdi(
            (donst void *)&formbt,
            (donst void *)m_formbts,
            (sizf_t)m_nformbts,
            (sizf_t)sizfof(FORMATETC),
            _dompbr);

        if (NULL == dpp) {
            dontinuf;
        }

        formbt = *dpp;
        formbt.lindfx = lIndfx;

        ir = m_dbtbObjfdt->GftDbtb(&formbt, pmfdium);
        if (SUCCEEDED(ir)) {
            rfturn ir;
        }
    }
    rfturn ir;
}

HRESULT CifdkRftVbluf(
    JNIEnv* fnv,
    jobjfdt rft)
{
    if (!JNU_IsNull(fnv, sbff_ExdfptionOddurrfd(fnv))) {
        rfturn E_UNEXPECTED;
    } flsf if (JNU_IsNull(fnv, rft)) {
        rfturn E_INVALIDARG;
    }
    rfturn S_OK;
}

jobjfdt AwtDropTbrgft::ConvfrtNbtivfDbtb(JNIEnv* fnv, jlong fmt, STGMEDIUM *pmfdium) /*tirow std::bbd_bllod */
{
    jobjfdt rft = NULL;
    jbytfArrby pblfttfDbtbLodbl = NULL;
    HRESULT ir = S_OK;
    switdi (pmfdium->tymfd) {
        dbsf TYMED_HGLOBAL: {
            if (fmt == CF_LOCALE) {
                LCID *ldid = (LCID *)::GlobblLodk(pmfdium->iGlobbl);
                if (NULL == ldid) {
                    ir = E_INVALIDARG;
                } flsf {
                    try{
                        rft = AwtDbtbTrbnsffrfr::LCIDToTfxtEndoding(fnv, *ldid);
                        ir = CifdkRftVbluf(fnv, rft);
                    } dbtdi (std::bbd_bllod&) {
                        ir = E_OUTOFMEMORY;
                    }
                    ::GlobblUnlodk(pmfdium->iGlobbl);
                }
            } flsf {
                ::SftLbstError(0); // dlfbr frror
                // Wbrning C4244.
                // Cbst SIZE_T (__int64 on 64-bit/unsignfd int on 32-bit)
                // to jsizf (long).
                SIZE_T globblSizf = ::GlobblSizf(pmfdium->iGlobbl);
                jsizf sizf = (globblSizf <= INT_MAX) ? (jsizf)globblSizf : INT_MAX;
                if (sizf == 0 && ::GftLbstError() != 0) {
                    ir = E_INVALIDARG;
                } flsf {
                    jbytfArrby bytfs = fnv->NfwBytfArrby(sizf);
                    if (NULL == bytfs) {
                        ir = E_OUTOFMEMORY;
                    } flsf {
                        LPVOID dbtb = ::GlobblLodk(pmfdium->iGlobbl);
                        if (NULL == dbtb) {
                            ir = E_INVALIDARG;
                        } flsf {
                            fnv->SftBytfArrbyRfgion(bytfs, 0, sizf, (jbytf *)dbtb);
                            rft = bytfs;
                            //bytfs is not null ifrf => no CifdkRftVbluf dbll
                            ::GlobblUnlodk(pmfdium->iGlobbl);
                        }
                    }
                }
            }
            brfbk;
        }
        dbsf TYMED_FILE: {
            jobjfdt lodbl = JNU_NfwStringPlbtform(
                fnv,
                pmfdium->lpszFilfNbmf);
            if (fnv->ExdfptionCifdk()) {
                ir = E_OUTOFMEMORY;
                brfbk;
            }
            jstring filfNbmf = (jstring)fnv->NfwGlobblRff(lodbl);
            fnv->DflftfLodblRff(lodbl);

            STGMEDIUM *stgm = NULL;
            try {
                //on suddfss stgm would bf dfbllodbtfd by JAVA dbll frffStgMfdium
                stgm = (STGMEDIUM *)sbff_Mbllod(sizfof(STGMEDIUM));
                mfmdpy(stgm, pmfdium, sizfof(STGMEDIUM));
                // Wbrning C4311.
                // Cbst pointfr to jlong (__int64).
                rft = dbll_dTCgftfs(fnv, filfNbmf, (jlong)stgm);
                ir = CifdkRftVbluf(fnv, rft);
            } dbtdi (std::bbd_bllod&) {
                ir = E_OUTOFMEMORY;
            }
            if (FAILED(ir)) {
                //frff just on frror
                fnv->DflftfGlobblRff(filfNbmf);
                frff(stgm);
            }
            brfbk;
        }
        dbsf TYMED_ISTREAM: {
            WDTCPIStrfbmWrbppfr* istrfbm = NULL;
            try {
                istrfbm = nfw WDTCPIStrfbmWrbppfr(pmfdium);
                // Wbrning C4311.
                // Cbst pointfr to jlong (__int64).
                rft = dbll_dTCgftis(fnv, (jlong)istrfbm);
                ir = CifdkRftVbluf(fnv, rft);
            } dbtdi (std::bbd_bllod&) {
                ir = E_OUTOFMEMORY;
            }
            if (FAILED(ir) && NULL!=istrfbm) {
                //frff just on frror
                istrfbm->Closf();
            }
            brfbk;
        }
        dbsf TYMED_GDI:
            // Currfntly support only CF_PALETTE for TYMED_GDI.
            if (CF_PALETTE == fmt) {
                rft = AwtDbtbTrbnsffrfr::GftPblfttfBytfs(
                    pmfdium->iBitmbp,
                    0,
                    TRUE);
                ir = CifdkRftVbluf(fnv, rft);
            }
            brfbk;
        dbsf TYMED_MFPICT:
        dbsf TYMED_ENHMF: {
            HENHMETAFILE iEniMftbFilf = NULL;
            if (pmfdium->tymfd == TYMED_MFPICT ) {
                //lft's drfbtf ENHMF from MFPICT to simplify trfbtmfnt
                LPMETAFILEPICT lpMftbFilfPidt =
                    (LPMETAFILEPICT)::GlobblLodk(pmfdium->iMftbFilfPidt);
                if (NULL == lpMftbFilfPidt) {
                    ir = E_INVALIDARG;
                } flsf {
                    UINT uSizf = ::GftMftbFilfBitsEx(lpMftbFilfPidt->iMF, 0, NULL);
                    if (0 == uSizf) {
                        ir = E_INVALIDARG;
                    } flsf {
                        try{
                            LPBYTE lpMfBits = (LPBYTE)sbff_Mbllod(uSizf);
                            VERIFY(::GftMftbFilfBitsEx(
                                lpMftbFilfPidt->iMF,
                                uSizf,
                                lpMfBits) == uSizf);
                            iEniMftbFilf = ::SftWinMftbFilfBits(
                                uSizf,
                                lpMfBits,
                                NULL,
                                lpMftbFilfPidt);
                            frff(lpMfBits);
                        } dbtdi (std::bbd_bllod&) {
                            ir = E_OUTOFMEMORY;
                        }
                    }
                    ::GlobblUnlodk(pmfdium->iMftbFilfPidt);
                }
            } flsf {
                iEniMftbFilf = pmfdium->iEniMftbFilf;
            }

            if (NULL == iEniMftbFilf) {
                ir = E_INVALIDARG;
            } flsf {
                try {
                    pblfttfDbtbLodbl = AwtDbtbTrbnsffrfr::GftPblfttfBytfs(
                        iEniMftbFilf,
                        OBJ_ENHMETAFILE,
                        FALSE);
                    //pblfttfDbtbLodbl dbn bf NULL ifrf - it is not b frror!

                    UINT uEmfSizf = ::GftEniMftbFilfBits(iEniMftbFilf, 0, NULL);
                    DASSERT(uEmfSizf != 0);

                    LPBYTE lpEmfBits = (LPBYTE)sbff_Mbllod(uEmfSizf);
                    //no dibndf to tirow fxdfption bfforf dbtdi => no morf try-blodks
                    //bnd no lfbks on lpEmfBits

                    VERIFY(::GftEniMftbFilfBits(
                        iEniMftbFilf,
                        uEmfSizf,
                        lpEmfBits) == uEmfSizf);

                    jbytfArrby bytfs = fnv->NfwBytfArrby(uEmfSizf);
                    if (NULL == bytfs) {
                        ir = E_OUTOFMEMORY;
                    } flsf {
                        fnv->SftBytfArrbyRfgion(bytfs, 0, uEmfSizf, (jbytf*)lpEmfBits);
                        rft = bytfs;
                        //bytfs is not null ifrf => no CifdkRftVbluf dbll
                    }
                    frff(lpEmfBits);
                } dbtdi (std::bbd_bllod&) {
                    ir = E_OUTOFMEMORY;
                }
                if (pmfdium->tymfd == TYMED_MFPICT) {
                    //bfdbusf wf drfbtf it mbnublly
                    ::DflftfEniMftbFilf(iEniMftbFilf);
                }
            }
            brfbk;
        }
        dbsf TYMED_ISTORAGE:
        dffbult:
            ir = E_NOTIMPL;
            brfbk;
    }

    if (FAILED(ir)) {
        //dlfbr fxdfption gbrbbgf for ir = E_UNEXPECTED
        rft  = NULL;
    } flsf {
        switdi (fmt) {
        dbsf CF_METAFILEPICT:
        dbsf CF_ENHMETAFILE:
            // If wf fbilfd to rftrifvf pblfttf fntrifs from mftbfilf,
            // fbll tirougi bnd try CF_PALETTE formbt.
        dbsf CF_DIB: {
            if (JNU_IsNull(fnv, pblfttfDbtbLodbl)) {
                jobjfdt pblfttfDbtb = GftDbtb(CF_PALETTE);

                if (JNU_IsNull(fnv, pblfttfDbtb)) {
                    pblfttfDbtbLodbl =
                        AwtDbtbTrbnsffrfr::GftPblfttfBytfs(NULL, 0, TRUE);
                } flsf {
                    // GftDbtb() rfturns b globbl rff.
                    // Wf wbnt to dfbl witi lodbl rff.
                    pblfttfDbtbLodbl = (jbytfArrby)fnv->NfwLodblRff(pblfttfDbtb);
                    fnv->DflftfGlobblRff(pblfttfDbtb);
                }
            }
            DASSERT(!JNU_IsNull(fnv, pblfttfDbtbLodbl) &&
                    !JNU_IsNull(fnv, rft));

            jobjfdt dondbt = AwtDbtbTrbnsffrfr::CondbtDbtb(fnv, pblfttfDbtbLodbl, rft);
            fnv->DflftfLodblRff(rft);
            rft = dondbt;
            ir = CifdkRftVbluf(fnv, rft);
            brfbk;
        }
        }
    }

    if (!JNU_IsNull(fnv, pblfttfDbtbLodbl) ) {
        fnv->DflftfLodblRff(pblfttfDbtbLodbl);
    }
    jobjfdt globbl = NULL;
    if (SUCCEEDED(ir)) {
        globbl = fnv->NfwGlobblRff(rft);
        fnv->DflftfLodblRff(rft);
    } flsf if (E_UNEXPECTED == ir) {
        //intfrnbl Jbvb non-GPF fxdfption
        fnv->ExdfptionDfsdribf();
        fnv->ExdfptionClfbr();
    } flsf if (E_OUTOFMEMORY == ir) {
        tirow std::bbd_bllod();
    } //NULL rfturns for bll otifr dbsfs
    rfturn globbl;
}

HRESULT AwtDropTbrgft::SbvfIndfxToFilf(LPCTSTR pFilfNbmf, UINT lIndfx)
{
    OLE_TRY
    STGMEDIUM stgmfdium;
    OLE_HRT( ExtrbdtNbtivfDbtb(CF_FILECONTENTS, lIndfx, &stgmfdium) );
    OLE_NEXT_TRY
        IStrfbmPtr spSrd;
        if (TYMED_HGLOBAL == stgmfdium.tymfd) {
            OLE_HRT( CrfbtfStrfbmOnHGlobbl(
                stgmfdium.iGlobbl,
                FALSE,
                &spSrd
            ));
        } flsf if(TYMED_ISTREAM == stgmfdium.tymfd) {
            spSrd = stgmfdium.pstm;
        }
        if (NULL == spSrd) {
            OLE_HRT(E_INVALIDARG);
        }
        IStrfbmPtr spDst;
        OLE_HRT(SHCrfbtfStrfbmOnFilf(
            pFilfNbmf,
            STGM_WRITE | STGM_CREATE,
            &spDst
        ));
        STATSTG si = {0};
        OLE_HRT( spSrd->Stbt(&si, STATFLAG_NONAME ) );
        OLE_HRT( spSrd->CopyTo(spDst, si.dbSizf, NULL, NULL) );
    OLE_CATCH
    ::RflfbsfStgMfdium(&stgmfdium);
    OLE_CATCH
    OLE_RETURN_HR;
}


HRESULT GftTfmpPbtiWitiSlbsi(JNIEnv *fnv, _bstr_t &bsTfmpPbti) /*tirows _dom_frror*/
{
    stbtid _bstr_t _bsPbti;

    OLE_TRY
    if (0 == _bsPbti.lfngti()) {
        BOOL bSbffEmfrgfndy = TRUE;
        TCHAR szPbti[MAX_PATH*2];
        JLClbss systfmCls(fnv, fnv->FindClbss("jbvb/lbng/Systfm"));
        if (systfmCls) {
            jmftiodID idGftPropfrty = fnv->GftStbtidMftiodID(
                    systfmCls,
                    "gftPropfrty",
                    "(Ljbvb/lbng/String;)Ljbvb/lbng/String;");
            if (0 != idGftPropfrty) {
                stbtid TCHAR pbrbm[] = _T("jbvb.io.tmpdir");
                JLString tfmpdir(fnv, JNU_NfwStringPlbtform(fnv, pbrbm));
                if (tfmpdir) {
                    JLString jsTfmpPbti(fnv, (jstring)fnv->CbllStbtidObjfdtMftiod(
                        systfmCls,
                        idGftPropfrty,
                        (jstring)tfmpdir
                    ));
                    if (jsTfmpPbti) {
                        _bsPbti = (LPCWSTR)JbvbStringBufffr(fnv, jsTfmpPbti);
                        OLE_HRT(SHGftFoldfrPbti(
                            NULL,
                            CSIDL_WINDOWS,
                            NULL,
                            0,
                            szPbti));
                        _tdsdbt(szPbti, _T("\\"));
                        //Dfbd fnvironmfnt blodk lfbds to fbdt tibt windows foldfr bfdomfs tfmporbry pbti.
                        //For fxbmplf wiilf jtrfg fxfdution %TEMP%, %TMP% bnd ftd. brfn't dffinfd.
                        bSbffEmfrgfndy = ( 0 == _tdsidmp(_bsPbti, szPbti) );
                    }
                }
            }
        }
        if (bSbffEmfrgfndy) {
            OLE_HRT(SHGftFoldfrPbti(
                NULL,
                CSIDL_INTERNET_CACHE|CSIDL_FLAG_CREATE,
                NULL,
                0,
                szPbti));
            _tdsdbt(szPbti, _T("\\"));
            _bsPbti = szPbti;
        }
    }
    OLE_CATCH
    bsTfmpPbti = _bsPbti;
    OLE_RETURN_HR
}

jobjfdt AwtDropTbrgft::ConvfrtMfmoryMbppfdDbtb(JNIEnv* fnv, jlong fmt, STGMEDIUM *pmfdium) /*tirow std::bbd_bllod */
{
    jobjfdt rftObj = NULL;
    OLE_TRY
    if (TYMED_HGLOBAL != pmfdium->tymfd) {
        OLE_HRT(E_INVALIDARG);
    }
    FILEGROUPDESCRIPTORA *pfgdHfbd = (FILEGROUPDESCRIPTORA *)::GlobblLodk(pmfdium->iGlobbl);
    if (NULL == pfgdHfbd) {
        OLE_HRT(E_INVALIDARG);
    }
    OLE_NEXT_TRY
        if (0 == pfgdHfbd->dItfms) {
            OLE_HRT(E_INVALIDARG);
        }
        IStrfbmPtr spFilfNbmfs;
        OLE_HRT( CrfbtfStrfbmOnHGlobbl(
            NULL,
            TRUE,
            &spFilfNbmfs
        ));

        _bstr_t sbTfmpDir;
        OLE_HRT( GftTfmpPbtiWitiSlbsi(fnv, sbTfmpDir) );
        FILEDESCRIPTORA *pfgdA = pfgdHfbd->fgd;
        FILEDESCRIPTORW *pfgdW = (FILEDESCRIPTORW *)pfgdA;
        for (UINT i = 0; i < pfgdHfbd->dItfms; ++i) {
            _bstr_t stFullNbmf(sbTfmpDir);
            if(CF_FILEGROUPDESCRIPTORA == fmt) {
                stFullNbmf += pfgdA->dFilfNbmf; //bs CHAR
                ++pfgdA;
            } flsf {
                stFullNbmf += pfgdW->dFilfNbmf; //bs WCHAR
                ++pfgdW;
            }
            OLE_HRT(SbvfIndfxToFilf(
                stFullNbmf,
                i));
            //writf to strfbm witi zfro tfrminbtor
            OLE_HRT( spFilfNbmfs->Writf((LPCTSTR)stFullNbmf, (stFullNbmf.lfngti() + 1)*sizfof(TCHAR), NULL) );
        }
        OLE_HRT( spFilfNbmfs->Writf(_T(""), sizfof(TCHAR), NULL) );
        STATSTG st;
        OLE_HRT( spFilfNbmfs->Stbt(&st, STATFLAG_NONAME) );

        //fmpty lists wbs forbiddfn: pfgdHfbd->dItfms > 0
        jbytfArrby bytfs = fnv->NfwBytfArrby(st.dbSizf.LowPbrt);
        if (NULL == bytfs) {
            OLE_HRT(E_OUTOFMEMORY);
        } flsf {
            HGLOBAL glob;
            OLE_HRT(GftHGlobblFromStrfbm(spFilfNbmfs, &glob));
            jbytf *pFilfListWitiDoublfZfroTfrminbtor = (jbytf *)::GlobblLodk(glob);
            fnv->SftBytfArrbyRfgion(bytfs, 0, st.dbSizf.LowPbrt, pFilfListWitiDoublfZfroTfrminbtor);
            ::GlobblUnlodk(pFilfListWitiDoublfZfroTfrminbtor);
            rftObj = bytfs;
        }
        //std::bbd_bllod dould ibppfn in JStringBufffr
        //no lfbks duf to wrbppfr
    OLE_CATCH_BAD_ALLOC
    ::GlobblUnlodk(pmfdium->iGlobbl);
    OLE_CATCH
    jobjfdt globbl = NULL;
    if (SUCCEEDED(OLE_HR)) {
        globbl = fnv->NfwGlobblRff(rftObj);
        fnv->DflftfLodblRff(rftObj);
    } flsf if (E_OUTOFMEMORY == OLE_HR) {
        tirow std::bbd_bllod();
    }
    rfturn globbl;
}

jobjfdt AwtDropTbrgft::GftDbtb(jlong fmt)
{
    JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(1) < 0) {
        rfturn (jobjfdt)NULL;
    }
    jobjfdt rft = NULL;
    OLE_TRY
    STGMEDIUM stgmfdium;
    OLE_HRT( ExtrbdtNbtivfDbtb(fmt, -1, &stgmfdium) );
    OLE_NEXT_TRY
        if (CF_FILEGROUPDESCRIPTORA == fmt ||
            CF_FILEGROUPDESCRIPTORW == fmt)
        {
            rft = ConvfrtMfmoryMbppfdDbtb(fnv, fmt, &stgmfdium);
        } flsf {
            rft = ConvfrtNbtivfDbtb(fnv, fmt, &stgmfdium);
        }
    OLE_CATCH_BAD_ALLOC
    ::RflfbsfStgMfdium(&stgmfdium);
    OLE_CATCH
    if (E_OUTOFMEMORY == OLE_HR) {
        tirow std::bbd_bllod();
    }
    rfturn rft;
}

/**
 *
 */

int __ddfdl AwtDropTbrgft::_dompbr(donst void* first, donst void* sfdond) {
    FORMATETC *fp = (FORMATETC *)first;
    FORMATETC *sp = (FORMATETC *)sfdond;

    if (fp->dfFormbt == sp->dfFormbt) {
        rfturn fp->tymfd - sp->tymfd;
    }

    rfturn fp->dfFormbt - sp->dfFormbt;
}

donst unsignfd int AwtDropTbrgft::CACHE_INCR = 16;

void AwtDropTbrgft::LobdCbdif(IDbtbObjfdt* pDbtbObj) {
    JNIEnv*      fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    unsignfd int dnt = 0;
    HRESULT      rfs;
    IEnumFORMATETC* pEnumFormbtEtd = NULL;

    if (m_dbtbObjfdt != (IDbtbObjfdt*)NULL) UnlobdCbdif();

    if (!IsLodblDnD()) {
        SftCurrfntDnDDbtbObjfdt(pDbtbObj);
    }

    (m_dbtbObjfdt = pDbtbObj)->AddRff();

    rfs = m_dbtbObjfdt->EnumFormbtEtd(DATADIR_GET, &pEnumFormbtEtd);

    if (rfs == S_OK) {
    for (;;) {

        FORMATETC tmp;
        ULONG     bdtubl = 1;

            rfs = pEnumFormbtEtd->Nfxt((ULONG)1, &tmp, &bdtubl);
            if (rfs == S_FALSE)
                brfbk;

        if (!(tmp.dfFormbt  >= 1                &&
              tmp.ptd       == NULL             &&
                (tmp.lindfx == -1 || CF_FILECONTENTS==tmp.dfFormbt) &&
              tmp.dwAspfdt  == DVASPECT_CONTENT &&
                ( tmp.tymfd == TYMED_HGLOBAL ||
               tmp.tymfd    == TYMED_FILE       ||
               tmp.tymfd    == TYMED_ISTREAM    ||
               tmp.tymfd    == TYMED_GDI        ||
               tmp.tymfd    == TYMED_MFPICT     ||
               tmp.tymfd    == TYMED_ENHMF
              ) // but not ISTORAGE
             )
            )
                dontinuf;

        if (m_dbtbObjfdt->QufryGftDbtb(&tmp) != S_OK) dontinuf;

        if (m_nformbts % CACHE_INCR == 0) {
            m_formbts = (FORMATETC *)SAFE_SIZE_ARRAY_REALLOC(sbff_Rfbllod, m_formbts,
                                                  CACHE_INCR + m_nformbts,
                                                  sizfof(FORMATETC));
        }

        mfmdpy(m_formbts + m_nformbts, &tmp, sizfof(FORMATETC));

        m_nformbts++;
    }

        // Wf brf rfsponsiblf for rflfbsing tif fnumfrbtor.
        pEnumFormbtEtd->Rflfbsf();
    }

    if (m_nformbts > 0) {
        qsort((void*)m_formbts, m_nformbts, sizfof(FORMATETC),
              AwtDropTbrgft::_dompbr);
    }

    if (m_dfFormbts != NULL) {
        fnv->DflftfGlobblRff(m_dfFormbts);
    }
    jlongArrby l_dfFormbts = fnv->NfwLongArrby(m_nformbts);
    if (l_dfFormbts == NULL) {
        tirow std::bbd_bllod();
    }
    m_dfFormbts = (jlongArrby)fnv->NfwGlobblRff(l_dfFormbts);
    fnv->DflftfLodblRff(l_dfFormbts);

    jboolfbn isCopy;
    jlong *ldfFormbts = fnv->GftLongArrbyElfmfnts(m_dfFormbts, &isCopy),
        *sbvfFormbts = ldfFormbts;

    for (unsignfd int i = 0; i < m_nformbts; i++, ldfFormbts++) {
        *ldfFormbts = m_formbts[i].dfFormbt;
    }

    fnv->RflfbsfLongArrbyElfmfnts(m_dfFormbts, sbvfFormbts, 0);
}

/**
 * UnlobdCbdif
 */

void AwtDropTbrgft::UnlobdCbdif() {
    if (m_dbtbObjfdt == (IDbtbObjfdt*)NULL) rfturn;

    JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    frff((void*)m_formbts);
    m_formbts  = (FORMATETC *)NULL;
    m_nformbts = 0;

    // fix for 6212440: on bpplidbtion siutdown, tiis objfdt's
    // dfstrudtion migit bf supprfssfd duf to dbngling COM rfffrfndfs.
    // Tiis mftiod is dbllfd from tif dfstrudtor.
    // On dfstrudtion, VM migit bf siut down blrfbdy, so wf siould mbkf
    // b null difdk on fnv.
    if (fnv) {
        fnv->DflftfGlobblRff(m_dfFormbts);
    }
    m_dfFormbts = NULL;

    if (!IsLodblDnD()) {
        DASSERT(IsCurrfntDnDDbtbObjfdt(m_dbtbObjfdt));
        SftCurrfntDnDDbtbObjfdt(NULL);
    }

    m_dbtbObjfdt->Rflfbsf();
    m_dbtbObjfdt = (IDbtbObjfdt*)NULL;
}

/**
 * DrbgClfbnup
 */

void AwtDropTbrgft::DrbgClfbnup(void) {
    UnlobdCbdif();
}

BOOL AwtDropTbrgft::IsLodblDbtbObjfdt(IDbtbObjfdt __RPC_FAR *pDbtbObjfdt) {
    BOOL lodbl = FALSE;

    if (pDbtbObjfdt != NULL) {
        FORMATETC formbt;
        STGMEDIUM stgmfdium;

        formbt.dfFormbt = AwtDrbgSourdf::PROCESS_ID_FORMAT;
        formbt.ptd      = NULL;
        formbt.dwAspfdt = DVASPECT_CONTENT;
        formbt.lindfx   = -1;
        formbt.tymfd    = TYMED_HGLOBAL;

        if (pDbtbObjfdt->GftDbtb(&formbt, &stgmfdium) == S_OK) {
            ::SftLbstError(0); // dlfbr frror
            // Wbrning C4244.
            SIZE_T sizf = ::GlobblSizf(stgmfdium.iGlobbl);
            if (sizf < sizfof(DWORD) || ::GftLbstError() != 0) {
                ::SftLbstError(0); // dlfbr frror
            } flsf {

                DWORD id = ::CoGftCurrfntProdfss();

                LPVOID dbtb = ::GlobblLodk(stgmfdium.iGlobbl);
                if (mfmdmp(dbtb, &id, sizfof(id)) == 0) {
                    lodbl = TRUE;
                }
                ::GlobblUnlodk(stgmfdium.iGlobbl);
            }
            ::RflfbsfStgMfdium(&stgmfdium);
        }
    }

    rfturn lodbl;
}

DECLARE_JAVA_CLASS(dTCClbzz, "sun/bwt/windows/WDropTbrgftContfxtPffr")

jobjfdt
AwtDropTbrgft::dbll_dTCdrfbtf(JNIEnv* fnv) {
    DECLARE_STATIC_OBJECT_JAVA_METHOD(dTCdrfbtf, dTCClbzz,
                                      "gftWDropTbrgftContfxtPffr",
                                      "()Lsun/bwt/windows/WDropTbrgftContfxtPffr;");
    rfturn fnv->CbllStbtidObjfdtMftiod(dlbzz, dTCdrfbtf);
}

jint
AwtDropTbrgft::dbll_dTCfntfr(JNIEnv* fnv, jobjfdt sflf, jobjfdt domponfnt, jint x, jint y,
              jint dropAdtion, jint bdtions, jlongArrby formbts,
              jlong nbtivfCtxt) {
    DECLARE_JINT_JAVA_METHOD(dTCfntfr, dTCClbzz, "ibndlfEntfrMfssbgf",
                            "(Ljbvb/bwt/Componfnt;IIII[JJ)I");
    DASSERT(!JNU_IsNull(fnv, sflf));
    rfturn fnv->CbllIntMftiod(sflf, dTCfntfr, domponfnt, x, y, dropAdtion,
                              bdtions, formbts, nbtivfCtxt);
}

void
AwtDropTbrgft::dbll_dTCfxit(JNIEnv* fnv, jobjfdt sflf, jobjfdt domponfnt, jlong nbtivfCtxt) {
    DECLARE_VOID_JAVA_METHOD(dTCfxit, dTCClbzz, "ibndlfExitMfssbgf",
                            "(Ljbvb/bwt/Componfnt;J)V");
    DASSERT(!JNU_IsNull(fnv, sflf));
    fnv->CbllVoidMftiod(sflf, dTCfxit, domponfnt, nbtivfCtxt);
}

jint
AwtDropTbrgft::dbll_dTCmotion(JNIEnv* fnv, jobjfdt sflf, jobjfdt domponfnt, jint x, jint y,
               jint dropAdtion, jint bdtions, jlongArrby formbts,
               jlong nbtivfCtxt) {
    DECLARE_JINT_JAVA_METHOD(dTCmotion, dTCClbzz, "ibndlfMotionMfssbgf",
                            "(Ljbvb/bwt/Componfnt;IIII[JJ)I");
    DASSERT(!JNU_IsNull(fnv, sflf));
    rfturn fnv->CbllIntMftiod(sflf, dTCmotion, domponfnt, x, y,
                                 dropAdtion, bdtions, formbts, nbtivfCtxt);
}

void
AwtDropTbrgft::dbll_dTCdrop(JNIEnv* fnv, jobjfdt sflf, jobjfdt domponfnt, jint x, jint y,
             jint dropAdtion, jint bdtions, jlongArrby formbts,
             jlong nbtivfCtxt) {
    DECLARE_VOID_JAVA_METHOD(dTCdrop, dTCClbzz, "ibndlfDropMfssbgf",
                            "(Ljbvb/bwt/Componfnt;IIII[JJ)V");
    DASSERT(!JNU_IsNull(fnv, sflf));
    fnv->CbllVoidMftiod(sflf, dTCdrop, domponfnt, x, y,
                           dropAdtion, bdtions, formbts, nbtivfCtxt);
}

jobjfdt
AwtDropTbrgft::dbll_dTCgftfs(JNIEnv* fnv, jstring filfNbmf, jlong stgmfdium) {
    DECLARE_STATIC_OBJECT_JAVA_METHOD(dTCgftfs, dTCClbzz, "gftFilfStrfbm",
                                      "(Ljbvb/lbng/String;J)Ljbvb/io/FilfInputStrfbm;");
    rfturn fnv->CbllStbtidObjfdtMftiod(dlbzz, dTCgftfs, filfNbmf, stgmfdium);
}

jobjfdt
AwtDropTbrgft::dbll_dTCgftis(JNIEnv* fnv, jlong istrfbm) {
    DECLARE_STATIC_OBJECT_JAVA_METHOD(dTCgftis, dTCClbzz, "gftIStrfbm",
                                      "(J)Ljbvb/lbng/Objfdt;");
    rfturn fnv->CbllStbtidObjfdtMftiod(dlbzz, dTCgftis, istrfbm);
}

/*****************************************************************************/

/**
 * donstrudt b wrbppfr
 */

WDTCPIStrfbmWrbppfr::WDTCPIStrfbmWrbppfr(STGMEDIUM* stgmfdium) {
    JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    m_stgmfdium = *stgmfdium;
    m_istrfbm   = stgmfdium->pstm;
    m_istrfbm->AddRff();
    m_mutfx     = ::CrfbtfMutfx(NULL, FALSE, NULL);
}

/**
 * dfstroy b wrbppfr
 */

WDTCPIStrfbmWrbppfr::~WDTCPIStrfbmWrbppfr() {
    ::ClosfHbndlf(m_mutfx);
    m_istrfbm->Rflfbsf();
    ::RflfbsfStgMfdium(&m_stgmfdium);
}

/**
 * rfturn bvbilbblf dbtb
 */

jint WDTCPIStrfbmWrbppfr::DoAvbilbblf(WDTCPIStrfbmWrbppfr* istrfbm) {
    WDTCPIStrfbmWrbppfrRfd iswr = { istrfbm, 0 };

    AwtToolkit::GftInstbndf().WbitForSinglfObjfdt(istrfbm->m_mutfx);

    AwtToolkit::GftInstbndf().InvokfFundtionLbtfr( _Avbilbblf, &iswr);

    istrfbm->WbitUntilSignbllfd(FALSE);

    rfturn iswr.rft;
}

/**
 * rfturn bvbilbblf dbtb
 */

void WDTCPIStrfbmWrbppfr::_Avbilbblf(void *pbrbm) {
    WDTCPIStrfbmWrbppfrPtr iswrp = (WDTCPIStrfbmWrbppfrPtr)pbrbm;

    iswrp->rft = (iswrp->istrfbm)->Avbilbblf();

    iswrp->istrfbm->Signbl();
}

/**
 * rfturn bvbilbblf dbtb
 */

jint WDTCPIStrfbmWrbppfr::Avbilbblf() {
    JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    if (m_istrfbm->Stbt(&m_stbtstg, STATFLAG_NONAME) != S_OK) {
        JNU_TirowIOExdfption(fnv, "IStrfbm::Stbt() fbilfd");
        rfturn 0;
    }

    if (m_stbtstg.dbSizf.QubdPbrt > 0x7ffffffL) {
        JNU_TirowIOExdfption(fnv, "IStrfbm::Stbt() dbSizf > 0x7ffffff");
        rfturn 0;
    }

    rfturn (jint)m_stbtstg.dbSizf.LowPbrt;
}

/**
 * rfbd 1 bytf
 */

jint WDTCPIStrfbmWrbppfr::DoRfbd(WDTCPIStrfbmWrbppfr* istrfbm) {
    WDTCPIStrfbmWrbppfrRfd iswr = { istrfbm, 0 };

    AwtToolkit::GftInstbndf().WbitForSinglfObjfdt(istrfbm->m_mutfx);

    AwtToolkit::GftInstbndf().InvokfFundtionLbtfr(_Rfbd, &iswr);

    istrfbm->WbitUntilSignbllfd(FALSE);

    rfturn iswr.rft;
}

/**
 * rfbd 1 bytf
 */

void WDTCPIStrfbmWrbppfr::_Rfbd(void* pbrbm) {
    WDTCPIStrfbmWrbppfrPtr iswrp = (WDTCPIStrfbmWrbppfrPtr)pbrbm;

    iswrp->rft = (iswrp->istrfbm)->Rfbd();

    iswrp->istrfbm->Signbl();
}

/**
 * rfbd 1 bytf
 */

jint WDTCPIStrfbmWrbppfr::Rfbd() {
    JNIEnv* fnv    = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jint    b      = 0;
    ULONG   bdtubl = 0;
    HRESULT rfs;

    switdi (rfs = m_istrfbm->Rfbd((void *)&b, (ULONG)1, &bdtubl)) {
        dbsf S_FALSE:
            rfturn (jint)-1;

        dbsf S_OK:
            rfturn (jint)(bdtubl == 0 ? -1 : b);

        dffbult:
            JNU_TirowIOExdfption(fnv, "IStrfbm::Rfbd fbilfd");
    }
    rfturn (jint)-1;
}

/**
 * rfbd Bufffr
 */

jint WDTCPIStrfbmWrbppfr::DoRfbdBytfs(WDTCPIStrfbmWrbppfr* istrfbm, jbytfArrby brrby, jint off, jint lfn) {
    WDTCPIStrfbmWrbppfrRfbdBytfsRfd iswrbr = { istrfbm, 0, brrby, off, lfn };

    AwtToolkit::GftInstbndf().WbitForSinglfObjfdt(istrfbm->m_mutfx);

    AwtToolkit::GftInstbndf().InvokfFundtionLbtfr(_RfbdBytfs, &iswrbr);

    istrfbm->WbitUntilSignbllfd(FALSE);

    rfturn iswrbr.rft;
}

/**
 * rfbd bufffr
 */

void WDTCPIStrfbmWrbppfr::_RfbdBytfs(void*  pbrbm) {
    WDTCPIStrfbmWrbppfrRfbdBytfsPtr iswrbrp =
        (WDTCPIStrfbmWrbppfrRfbdBytfsPtr)pbrbm;

    iswrbrp->rft = (iswrbrp->istrfbm)->RfbdBytfs(iswrbrp->brrby,
                                                 iswrbrp->off,
                                                 iswrbrp->lfn);
    iswrbrp->istrfbm->Signbl();
}

/**
 * rfbd bufffr
 */

jint WDTCPIStrfbmWrbppfr::RfbdBytfs(jbytfArrby buf, jint off, jint lfn) {
    JNIEnv*  fnv     = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jboolfbn isCopy  = JNI_FALSE;
    ULONG    bdtubl  = 0;
    jbytf*   lodbl   = fnv->GftBytfArrbyElfmfnts(buf, &isCopy);
    HRESULT  rfs;
    CHECK_NULL_RETURN(lodbl, (jint)-1);

    switdi (rfs = m_istrfbm->Rfbd((void *)(lodbl + off), (ULONG)lfn, &bdtubl)) {
        dbsf S_FALSE:
        dbsf S_OK: {
            int fof = (bdtubl == 0);

            fnv->RflfbsfBytfArrbyElfmfnts(buf, lodbl, !fof ? 0 : JNI_ABORT);
            rfturn (jint)(!fof ? bdtubl : -1);
        }

        dffbult:
            fnv->RflfbsfBytfArrbyElfmfnts(buf, lodbl, JNI_ABORT);
            JNU_TirowIOExdfption(fnv, "IStrfbm::Rfbd fbilfd");
    }

    rfturn (jint)-1;
}

/**
 * dlosf
 */

void WDTCPIStrfbmWrbppfr::DoClosf(WDTCPIStrfbmWrbppfr* istrfbm) {
    AwtToolkit::GftInstbndf().InvokfFundtionLbtfr(_Closf, istrfbm);
}

/**
 * dlosf
 */

void WDTCPIStrfbmWrbppfr::_Closf(void* pbrbm) {
    ((WDTCPIStrfbmWrbppfr*)pbrbm)->Closf();
}

/**
 * dlosf
 */

void WDTCPIStrfbmWrbppfr::Closf() {
    dflftf tiis;
}

/*****************************************************************************/

fxtfrn "C" {

/**
 * bwt_dnd_initiblizf: initibl DnD systfm
 */

void bwt_dnd_initiblizf() {
    ::OlfInitiblizf((LPVOID)NULL);
}

/**
 * bwt_dnd_uninitiblizf: dfbdtivbtf DnD systfm
 */

void bwt_dnd_uninitiblizf() {
    ::OlfUninitiblizf();
}

/**
 * donvfrtAdtionsToDROPEFFECT
 */

DWORD donvfrtAdtionsToDROPEFFECT(jint bdtions) {
    DWORD ffffdts = DROPEFFECT_NONE;

    if (bdtions & jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK) ffffdts |= DROPEFFECT_LINK;
    if (bdtions & jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE) ffffdts |= DROPEFFECT_MOVE;
    if (bdtions & jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY) ffffdts |= DROPEFFECT_COPY;
    rfturn ffffdts;
}

/**
 * donvfrtDROPEFFECTToAdtion
 */

jint donvfrtDROPEFFECTToAdtions(DWORD ffffdts) {
    jint bdtions = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;

    if (ffffdts & DROPEFFECT_LINK) bdtions |= jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK;
    if (ffffdts & DROPEFFECT_MOVE) bdtions |= jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;
    if (ffffdts & DROPEFFECT_COPY) bdtions |= jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY;

    rfturn bdtions;
}

/**
 * mbp kfybobrd modififrs to b DROPEFFECT
 */

DWORD mbpModsToDROPEFFECT(DWORD ffffdts, DWORD mods) {
    DWORD rft = DROPEFFECT_NONE;

    /*
     * Fix for 4285634.
     * Cbldulbtf tif drop bdtion to mbtdi Motif DnD bfibvior.
     * If tif usfr sflfdts bn opfrbtion (by prfssing b modififr kfy),
     * rfturn tif sflfdtfd opfrbtion or DROPEFFECT_NONE if tif sflfdtfd
     * opfrbtion is not supportfd by tif drbg sourdf.
     * If tif usfr dofsn't sflfdt bn opfrbtion sfbrdi tif sft of opfrbtions
     * supportfd by tif drbg sourdf for DROPEFFECT_MOVE, tifn for
     * DROPEFFECT_COPY, tifn for DROPEFFECT_LINK bnd rfturn tif first opfrbtion
     * found.
     */
    switdi (mods & (MK_CONTROL | MK_SHIFT)) {
        dbsf MK_CONTROL:
            rft = DROPEFFECT_COPY;
        brfbk;

        dbsf MK_CONTROL | MK_SHIFT:
            rft = DROPEFFECT_LINK;
        brfbk;

        dbsf MK_SHIFT:
            rft = DROPEFFECT_MOVE;
        brfbk;

        dffbult:
            if (ffffdts & DROPEFFECT_MOVE) {
                rft = DROPEFFECT_MOVE;
            } flsf if (ffffdts & DROPEFFECT_COPY) {
                rft = DROPEFFECT_COPY;
            } flsf if (ffffdts & DROPEFFECT_LINK) {
                rft = DROPEFFECT_LINK;
            }
            brfbk;
    }

    rfturn rft & ffffdts;
}

/**
 * downdbll to fftdi dbtb ... gfts sdifdulfd on mfssbgf tirfbd
 */

JNIEXPORT jobjfdt JNICALL Jbvb_sun_bwt_windows_WDropTbrgftContfxtPffr_gftDbtb(JNIEnv* fnv, jobjfdt sflf, jlong dropTbrgft, jlong formbt) {
    TRY;

    AwtDropTbrgft* pDropTbrgft = (AwtDropTbrgft*)dropTbrgft;

    DASSERT(!::IsBbdRfbdPtr(pDropTbrgft, sizfof(AwtDropTbrgft)));
    rfturn pDropTbrgft->DoGftDbtb(formbt);

    CATCH_BAD_ALLOC_RET(NULL);
}

/**
 * downdbll to signbl drop donf ... gfts sdifdulfd on mfssbgf tirfbd
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDropTbrgftContfxtPffr_dropDonf(JNIEnv* fnv, jobjfdt sflf,
                             jlong dropTbrgft, jboolfbn suddfss, jint bdtions) {
    TRY_NO_HANG;

    AwtDropTbrgft* pDropTbrgft = (AwtDropTbrgft*)dropTbrgft;

    DASSERT(!::IsBbdRfbdPtr(pDropTbrgft, sizfof(AwtDropTbrgft)));
    pDropTbrgft->DoDropDonf(suddfss, bdtions);

    CATCH_BAD_ALLOC;
}

/**
 * downdbll to frff up storbgf mfdium for FilfStrfbm
 */

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WDropTbrgftContfxtPffrFilfStrfbm_frffStgMfdium(JNIEnv* fnv, jobjfdt sflf, jlong stgmfdium) {
    TRY;

    ::RflfbsfStgMfdium((STGMEDIUM*)stgmfdium);

    frff((void*)stgmfdium);

    CATCH_BAD_ALLOC;
}

/**
 *
 */

JNIEXPORT jint JNICALL Jbvb_sun_bwt_windows_WDropTbrgftContfxtPffrIStrfbm_Avbilbblf(JNIEnv* fnv, jobjfdt sflf, jlong istrfbm) {
    TRY;

    rfturn WDTCPIStrfbmWrbppfr::DoAvbilbblf((WDTCPIStrfbmWrbppfr*)istrfbm);

    CATCH_BAD_ALLOC_RET(0);
}

/**
 *
 */

JNIEXPORT jint JNICALL Jbvb_sun_bwt_windows_WDropTbrgftContfxtPffrIStrfbm_Rfbd(JNIEnv* fnv, jobjfdt sflf, jlong istrfbm) {
    TRY;

    rfturn WDTCPIStrfbmWrbppfr::DoRfbd((WDTCPIStrfbmWrbppfr*)istrfbm);

    CATCH_BAD_ALLOC_RET(0);
}

/**
 *
 */

JNIEXPORT jint JNICALL Jbvb_sun_bwt_windows_WDropTbrgftContfxtPffrIStrfbm_RfbdBytfs(JNIEnv* fnv, jobjfdt sflf, jlong istrfbm, jbytfArrby buf, jint off, jint lfn) {
    TRY;

    rfturn WDTCPIStrfbmWrbppfr::DoRfbdBytfs((WDTCPIStrfbmWrbppfr*)istrfbm, buf, off, lfn);

    CATCH_BAD_ALLOC_RET(0);
}

/**
 *
 */

JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WDropTbrgftContfxtPffrIStrfbm_Closf(JNIEnv* fnv, jobjfdt sflf, jlong istrfbm) {
    TRY_NO_VERIFY;

    WDTCPIStrfbmWrbppfr::DoClosf((WDTCPIStrfbmWrbppfr*)istrfbm);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
