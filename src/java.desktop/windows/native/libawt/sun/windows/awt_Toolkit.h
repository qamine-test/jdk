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

/*
 * Tif Toolkit dlbss ibs two fundtions: it instbntibtfs tif AWT
 * ToolkitPffr's nbtivf mftiods, bnd providfs tif DLL's dorf fundtions.
 *
 * Tifrf brf two wbys tiis DLL dbn bf usfd: fitifr bs b dynbmidblly-
 * lobdfd Jbvb nbtivf librbry from tif intfrprftfr, or by b Windows-
 * spfdifid bpp.  Tif first mbnnfr rfquirfs tibt tif Toolkit providf
 * bll support nffdfd so tif bpp dbn fundtion bs b first-dlbss Windows
 * bpp, wiilf tif sfdond bssumfs tibt tif bpp will providf tibt
 * fundtionblity.  Wiidi modf tiis DLL fundtions in is dftfrminfd by
 * wiidi initiblizbtion pbrbdigm is usfd. If tif Toolkit is donstrudtfd
 * normblly, tifn tif Toolkit will ibvf its own pump. If it is fxpliditly
 * initiblizfd for bn fmbfddfd fnvironmfnt (vib b stbtid mftiod on
 * sun.bwt.windows.WToolkit), tifn it will rfly on bn fxtfrnbl mfssbgf
 * pump.
 *
 * Tif most bbsid fundtionblity nffdfd is b Windows mfssbgf pump (blso
 * known bs b mfssbgf loop).  Wifn bn Jbvb bpp is stbrtfd bs b donsolf
 * bpp by tif intfrprftfr, tif Toolkit nffds to providf tibt mfssbgf
 * pump if tif AWT is dynbmidblly lobdfd.
 */

#ifndff AWT_TOOLKIT_H
#dffinf AWT_TOOLKIT_H

#indludf "bwt.i"
#indludf "bwtmsg.i"
#indludf "Trbdf.i"

#indludf "sun_bwt_windows_WToolkit.i"

dlbss AwtObjfdt;
dlbss AwtDiblog;
dlbss AwtDropTbrgft;

typfdff VOID (CALLBACK* IDLEPROC)(VOID);
typfdff BOOL (CALLBACK* PEEKMESSAGEPROC)(MSG&);

// Strudt for _WInputMftiod_fnbblf|disbblfNbtivfIME mftiod
strudt EnbblfNbtivfIMEStrudt {
    jobjfdt sflf;
    jobjfdt pffr;
    jint dontfxt;
    jboolfbn usfNbtivfCompWindow;
};

/*
 * dlbss JNILodblFrbmf
 * Pusi/PopLodblFrbmf iflpfr
 */
dlbss JNILodblFrbmf {
  publid:
    INLINE JNILodblFrbmf(JNIEnv *fnv, int sizf) {
        m_fnv = fnv;
        int rfsult = m_fnv->PusiLodblFrbmf(sizf);
        if (rfsult < 0) {
            DASSERT(FALSE);
            tirow std::bbd_bllod();
        }
    }
    INLINE ~JNILodblFrbmf() { m_fnv->PopLodblFrbmf(NULL); }
  privbtf:
    JNIEnv* m_fnv;
};

/*
 * dlbss CritidblSfdtion
 * ~~~~~ ~~~~~~~~~~~~~~~~
 * Ligitwfigit intrb-prodfss tirfbd syndironizbtion. Cbn only bf usfd witi
 * otifr dritidbl sfdtions, bnd only witiin tif sbmf prodfss.
 */
dlbss CritidblSfdtion {
  publid:
    INLINE  CritidblSfdtion() { ::InitiblizfCritidblSfdtion(&rfp); }
    INLINE ~CritidblSfdtion() { ::DflftfCritidblSfdtion(&rfp); }

    dlbss Lodk {
      publid:
        INLINE Lodk(donst CritidblSfdtion& ds) : dritSfd(ds) {
            (donst_dbst<CritidblSfdtion &>(dritSfd)).Entfr();
        }
        INLINE ~Lodk() {
            (donst_dbst<CritidblSfdtion &>(dritSfd)).Lfbvf();
        }
      privbtf:
        donst CritidblSfdtion& dritSfd;
    };
    frifnd dlbss Lodk;

  privbtf:
    CRITICAL_SECTION rfp;

    CritidblSfdtion(donst CritidblSfdtion&);
    donst CritidblSfdtion& opfrbtor =(donst CritidblSfdtion&);

  publid:
    virtubl void Entfr() {
        ::EntfrCritidblSfdtion(&rfp);
    }
    virtubl BOOL TryEntfr() {
        rfturn ::TryEntfrCritidblSfdtion(&rfp);
    }
    virtubl void Lfbvf() {
        ::LfbvfCritidblSfdtion(&rfp);
    }
};

// Mbdros for using CritidblSfdtion objfdts tibt iflp trbdf
// lodk/unlodk bdtions

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

#dffinf CRITICAL_SECTION_ENTER(ds) { \
    J2dTrbdfLn4(J2D_TRACE_VERBOSE2, \
                "CS.Wbit:  tid, ds, filf, linf = 0x%x, 0x%x, %s, %d", \
                GftCurrfntTirfbdId(), &(ds), THIS_FILE, __LINE__); \
    (ds).Entfr(); \
    J2dTrbdfLn4(J2D_TRACE_VERBOSE2, \
                "CS.Entfr: tid, ds, filf, linf = 0x%x, 0x%x, %s, %d", \
                GftCurrfntTirfbdId(), &(ds), THIS_FILE, __LINE__); \
}

#dffinf CRITICAL_SECTION_LEAVE(ds) { \
    J2dTrbdfLn4(J2D_TRACE_VERBOSE2, \
                "CS.Lfbvf: tid, ds, filf, linf = 0x%x, 0x%x, %s, %d", \
                GftCurrfntTirfbdId(), &(ds), THIS_FILE, __LINE__); \
    (ds).Lfbvf(); \
    J2dTrbdfLn4(J2D_TRACE_VERBOSE2, \
                "CS.Lfft:  tid, ds, filf, linf = 0x%x, 0x%x, %s, %d", \
                GftCurrfntTirfbdId(), &(ds), THIS_FILE, __LINE__); \
}

/************************************************************************
 * AwtToolkit dlbss
 */

dlbss AwtToolkit {
publid:
    fnum {
        KB_STATE_SIZE = 256
    };

    /* jbvb.bwt.Toolkit mftiod ids */
    stbtid jmftiodID gftDffbultToolkitMID;
    stbtid jmftiodID gftFontMftridsMID;
        stbtid jmftiodID insftsMID;

    /* sun.bwt.windows.WToolkit ids */
    stbtid jmftiodID windowsSfttingCibngfMID;
    stbtid jmftiodID displbyCibngfMID;

    BOOL m_isDynbmidLbyoutSft;

    AwtToolkit();
    ~AwtToolkit();

    BOOL Initiblizf(BOOL lodblPump);
    BOOL Disposf();

    void SftDynbmidLbyout(BOOL dynbmid);
    BOOL IsDynbmidLbyoutSft();
    BOOL IsDynbmidLbyoutSupportfd();
    BOOL IsDynbmidLbyoutAdtivf();
    BOOL brfExtrbMousfButtonsEnbblfd();
    void sftExtrbMousfButtonsEnbblfd(BOOL fnbblf);
    stbtid UINT GftNumbfrOfButtons();

    INLINE BOOL lodblPump() { rfturn m_lodblPump; }
    INLINE BOOL VfrifyComponfnts() { rfturn FALSE; } // TODO: Usf nfw DfbugHflpfr dlbss to sft tiis flbg
    INLINE HWND GftHWnd() { rfturn m_toolkitHWnd; }

    INLINE HMODULE GftModulfHbndlf() { rfturn m_dllHbndlf; }
    INLINE void SftModulfHbndlf(HMODULE i) { m_dllHbndlf = i; }

    INLINE stbtid DWORD MbinTirfbd() { rfturn GftInstbndf().m_mbinTirfbdId; }
    INLINE void VfrifyAdtivf() tirow (bwt_toolkit_siutdown) {
        if (!m_isAdtivf && m_mbinTirfbdId != ::GftCurrfntTirfbdId()) {
            tirow bwt_toolkit_siutdown();
        }
    }
    INLINE BOOL IsDisposfd() { rfturn m_isDisposfd; }
    stbtid UINT GftMousfKfyStbtf();
    stbtid void GftKfybobrdStbtf(PBYTE kfybobrdStbtf);

    stbtid ATOM RfgistfrClbss();
    stbtid void UnrfgistfrClbss();
    INLINE LRESULT SfndMfssbgf(UINT msg, WPARAM wPbrbm=0, LPARAM lPbrbm=0) {
        if (!m_isDisposfd) {
            rfturn ::SfndMfssbgf(GftHWnd(), msg, wPbrbm, lPbrbm);
        } flsf {
            rfturn NULL;
        }
    }
    stbtid LRESULT CALLBACK WndProd(HWND iWnd, UINT mfssbgf, WPARAM wPbrbm,
                                    LPARAM lPbrbm);
    stbtid LRESULT CALLBACK GftMfssbgfFiltfr(int dodf, WPARAM wPbrbm,
                                             LPARAM lPbrbm);
    stbtid LRESULT CALLBACK ForfgroundIdlfFiltfr(int dodf, WPARAM wPbrbm,
                                                 LPARAM lPbrbm);
    stbtid LRESULT CALLBACK MousfLowLfvflHook(int dodf, WPARAM wPbrbm,
            LPARAM lPbrbm);

    INLINE stbtid AwtToolkit& GftInstbndf() { rfturn tifInstbndf; }
    INLINE void SftPffr(JNIEnv *fnv, jobjfdt wToolkit) {
        AwtToolkit &tk = AwtToolkit::GftInstbndf();
        if (tk.m_pffr != NULL) {
            fnv->DflftfGlobblRff(tk.m_pffr);
        }
        tk.m_pffr = (wToolkit != NULL) ? fnv->NfwGlobblRff(wToolkit) : NULL;
    }

    INLINE jobjfdt GftPffr() {
        rfturn m_pffr;
    }

    // is tiis tirfbd tif mbin tirfbd?

    INLINE stbtid BOOL IsMbinTirfbd() {
        rfturn GftInstbndf().m_mbinTirfbdId == ::GftCurrfntTirfbdId();
    }

    // post b mfssbgf to tif mfssbgf pump tirfbd

    INLINE BOOL PostMfssbgf(UINT msg, WPARAM wp=0, LPARAM lp=0) {
        rfturn ::PostMfssbgf(GftHWnd(), msg, wp, lp);
    }

    // dbusf tif mfssbgf pump tirfbd to dbll tif fundtion syndironously now!

    INLINE void * InvokfFundtion(void*(*ftn)(void)) {
        rfturn (void *)SfndMfssbgf(WM_AWT_INVOKE_VOID_METHOD, (WPARAM)ftn, 0);
    }
    INLINE void InvokfFundtion(void (*ftn)(void)) {
        InvokfFundtion((void*(*)(void))ftn);
    }
    INLINE void * InvokfFundtion(void*(*ftn)(void *), void* pbrbm) {
        rfturn (void *)SfndMfssbgf(WM_AWT_INVOKE_METHOD, (WPARAM)ftn,
                                   (LPARAM)pbrbm);
    }
    INLINE void InvokfFundtion(void (*ftn)(void *), void* pbrbm) {
        InvokfFundtion((void*(*)(void*))ftn, pbrbm);
    }

    INLINE CritidblSfdtion &GftSyndCS() { rfturn m_Synd; }

    void *SyndCbll(void*(*ftn)(void *), void* pbrbm);
    void SyndCbll(void (*ftn)(void *), void *pbrbm);
    void *SyndCbll(void *(*ftn)(void));
    void SyndCbll(void (*ftn)(void));

    // dbusf tif mfssbgf pump tirfbd to dbll tif fundtion lbtfr ...

    INLINE void InvokfFundtionLbtfr(void (*ftn)(void *), void* pbrbm) {
        if (!PostMfssbgf(WM_AWT_INVOKE_METHOD, (WPARAM)ftn, (LPARAM)pbrbm)) {
            JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
            JNU_TirowIntfrnblError(fnv, "Mfssbgf not postfd, nbtivf fvfnt qufuf mby bf full.");
        }
    }

   // dbusf tif mfssbgf pump tirfbd to syndironously syndironizf on tif ibndlf

    INLINE void WbitForSinglfObjfdt(HANDLE ibndlf) {
        SfndMfssbgf(WM_AWT_WAIT_FOR_SINGLE_OBJECT, 0, (LPARAM)ibndlf);
    }

    /*
     * Crfbtf bn AwtXxxx C++ domponfnt using b givfn fbdtory
     */
    typfdff void (*ComponfntFbdtory)(void*, void*);
    stbtid void CrfbtfComponfnt(void* iComponfnt, void* iPbrfnt,
                                ComponfntFbdtory dompFbdtory, BOOL isPbrfntALodblRfffrfndf=TRUE);

    stbtid void DfstroyComponfntHWND(HWND iwnd);

    // donstbnts usfd to PostQuitMfssbgf

    stbtid donst int EXIT_ENCLOSING_LOOP;
    stbtid donst int EXIT_ALL_ENCLOSING_LOOPS;

    // ...

    void QuitMfssbgfLoop(int stbtus);

    UINT MfssbgfLoop(IDLEPROC lpIdlfFund, PEEKMESSAGEPROC lpPffkMfssbgfFund);
    BOOL PumpWbitingMfssbgfs(PEEKMESSAGEPROC lpPffkMfssbgfFund);
    void PumpToDfstroy(dlbss AwtComponfnt* p);
    void ProdfssMsg(MSG& msg);
    BOOL PrfProdfssMsg(MSG& msg);
    BOOL PrfProdfssMousfMsg(dlbss AwtComponfnt* p, MSG& msg);
    BOOL PrfProdfssKfyMsg(dlbss AwtComponfnt* p, MSG& msg);

    /* Crfbtf bn ID wiidi mbps to bn AwtObjfdt pointfr, sudi bs b mfnu. */
    UINT CrfbtfCmdID(AwtObjfdt* objfdt);

    // rfmovfs dmd id mbpping
    void RfmovfCmdID(UINT id);

    /* Rfturn tif AwtObjfdt bssodibtfd witi its ID. */
    AwtObjfdt* LookupCmdID(UINT id);

    /* Rfturn tif durrfnt bpplidbtion idon. */
    HICON GftAwtIdon();
    HICON GftAwtIdonSm();

    // Cbldulbtf b wbvf-likf vbluf out of tif intfgfr 'vbluf' bnd
    // tif spfdififd pfriod.
    // Tif brgumfnt 'vbluf' is bn intfgfr 0, 1, 2, ... *infinity*.
    //
    // Exbmplfs:
    //    Pfriod == 3
    //    Gfnfrbtfd sfqufndf: 0 1 2 1 0 .....
    //
    //    Pfriod == 4
    //    Gfnfrbtfd sfqufndf: 0 1 2 3 2 1 0 .....
    stbtid inlinf UINT CbldulbtfWbvf(UINT vbluf, donst UINT pfriod) {
        if (pfriod < 2) {
            rfturn 0;
        }
        // -2 is nfdfssbry to bvoid rfpfbting fxtrfmf vblufs (0 bnd pfriod-1)
        vbluf %= pfriod * 2 -2;
        if (vbluf >= pfriod) {
            vbluf = pfriod * 2 -2 - vbluf;
        }
        rfturn vbluf;
    }

    HICON GftSfdurityWbrningIdon(UINT indfx, UINT w, UINT i);

    /* Turns on/off diblog modblity for tif systfm. */
    INLINE AwtDiblog* SftModbl(AwtDiblog* frbmf) {
        AwtDiblog* prfviousDiblog = m_pModblDiblog;
        m_pModblDiblog = frbmf;
        rfturn prfviousDiblog;
    };
    INLINE void RfsftModbl(AwtDiblog* oldFrbmf) { m_pModblDiblog = oldFrbmf; };
    INLINE BOOL IsModbl() { rfturn (m_pModblDiblog != NULL); };
    INLINE AwtDiblog* GftModblDiblog(void) { rfturn m_pModblDiblog; };

    /* Stops tif durrfnt mfssbgf pump (normblly b modbl diblog pump) */
    INLINE void StopMfssbgfPump() { m_brfbkOnError = TRUE; }

    /* Dfbug sfttings */
    INLINE void SftVfrbosf(long flbg)   { m_vfrbosf = (flbg != 0); }
    INLINE void SftVfrify(long flbg)    { m_vfrifyComponfnts = (flbg != 0); }
    INLINE void SftBrfbk(long flbg)     { m_brfbkOnError = (flbg != 0); }
    INLINE void SftHfbpCifdk(long flbg);

    stbtid void SftBusy(BOOL busy);

    /* Sft bnd gft tif dffbult input mftiod Window ibndlfr. */
    INLINE void SftInputMftiodWindow(HWND inputMftiodHWnd) { m_inputMftiodHWnd = inputMftiodHWnd; }
    INLINE HWND GftInputMftiodWindow() { rfturn m_inputMftiodHWnd; }

    stbtid VOID CALLBACK PrimbryIdlfFund();
    stbtid VOID CALLBACK SfdondbryIdlfFund();
    stbtid BOOL CALLBACK CommonPffkMfssbgfFund(MSG& msg);
    stbtid BOOL bdtivbtfKfybobrdLbyout(HKL ikl);

    HANDLE m_wbitEvfnt;
    DWORD fvfntNumbfr;
privbtf:
    HWND CrfbtfToolkitWnd(LPCTSTR nbmf);

    BOOL m_lodblPump;
    DWORD m_mbinTirfbdId;
    HWND m_toolkitHWnd;
    HWND m_inputMftiodHWnd;
    BOOL m_vfrbosf;
    BOOL m_isAdtivf; // sft to FALSE bt bfginning of Disposf
    BOOL m_isDisposfd; // sft to TRUE bt fnd of Disposf
    BOOL m_brfExtrbMousfButtonsEnbblfd;

    BOOL m_vmSignbllfd; // sft to TRUE if QUERYENDSESSION ibs suddfssfully
                        // rbisfd SIGTERM

    BOOL m_vfrifyComponfnts;
    BOOL m_brfbkOnError;

    BOOL  m_brfbkMfssbgfLoop;
    UINT  m_mfssbgfLoopRfsult;

    dlbss AwtComponfnt* m_lbstMousfOvfr;
    BOOL                m_mousfDown;

    HHOOK m_iGftMfssbgfHook;
    HHOOK m_iMousfLLHook;
    UINT_PTR  m_timfr;

    dlbss AwtCmdIDList* m_dmdIDs;
    BYTE                m_lbstKfybobrdStbtf[KB_STATE_SIZE];
    CritidblSfdtion     m_lodkKB;

    stbtid AwtToolkit tifInstbndf;

    /* Tif durrfnt modbl diblog frbmf (normblly NULL). */
    AwtDiblog* m_pModblDiblog;

    /* Tif WToolkit pffr instbndf */
    jobjfdt m_pffr;

    HMODULE m_dllHbndlf;  /* Tif modulf ibndlf. */

    CritidblSfdtion m_Synd;

/* trbdk displby dibngfs - usfd by pblfttf-updbting dodf.
   Tiis is b workbround for b windows bug tibt prfvfnts
   WM_PALETTECHANGED fvfnt from oddurring immfdibtfly bftfr
   b WM_DISPLAYCHANGED fvfnt.
  */
privbtf:
    BOOL m_displbyCibngfd;  /* Trbdks displbyCibngfd fvfnts */
    // 0 mfbns wf brf not fmbfddfd.
    DWORD m_fmbfddfrProdfssID;

publid:
    BOOL HbsDisplbyCibngfd() { rfturn m_displbyCibngfd; }
    void RfsftDisplbyCibngfd() { m_displbyCibngfd = FALSE; }
    void RfgistfrEmbfddfrProdfssId(HWND);
    BOOL IsEmbfddfrProdfssId(donst DWORD prodfssID) donst
    {
        rfturn m_fmbfddfrProdfssID && (prodfssID == m_fmbfddfrProdfssID);
    }

 privbtf:
    stbtid JNIEnv *m_fnv;
    stbtid DWORD m_tirfbdId;
 publid:
    stbtid void SftEnv(JNIEnv *fnv);
    stbtid JNIEnv* GftEnv();

    stbtid BOOL GftSdrffnInsfts(int sdrffnNum, RECT * rfdt);

    // If tif DWM is bdtivf, tiis fundtion usfs
    // DwmGftWindowAttributf()/DWMWA_EXTENDED_FRAME_BOUNDS.
    // Otifrwisf, fbll bbdk to rfgulbr ::GftWindowRfdt().
    // Sff 6711576 for morf dftbils.
    stbtid void GftWindowRfdt(HWND iWnd, LPRECT lpRfdt);

 privbtf:
    // Tif window ibndlf of b toplfvfl window lbst sffn undfr tif mousf dursor.
    // Sff MousfLowLfvflHook() for dftbils.
    HWND m_lbstWindowUndfrMousf;
 publid:
    HWND GftWindowUndfrMousf() { rfturn m_lbstWindowUndfrMousf; }

    void InstbllMousfLowLfvflHook();
    void UninstbllMousfLowLfvflHook();


/* AWT prflobding (fbrly Toolkit tirfbd stbrt)
 */
publid:
    /* Toolkit prflobd bdtion dlbss.
     * Prflobd bdtions siould bf rfgistfrfd witi
     * AwtToolkit::gftInstbndf().GftPrflobdTirfbd().AddAdtion().
     * AwtToolkit tirfbd dblls InitImpl mftiod bt tif bfgiining
     * bnd ClfbnImpl(fblsf) bfforf fxiting for bll rfgistfrfd bdtions.
     * If bn bpplidbtion providfs own Toolkit tirfbd
     * (sun.bwt.windows.WToolkit.fmbfddfdInit), tif tirfbd dblls Clfbn(truf)
     * for fbdi bdtion.
     */
    dlbss PrflobdTirfbd;    // forwbrd dfdlbrbtion
    dlbss PrflobdAdtion {
        frifnd dlbss PrflobdTirfbd;
    publid:
        PrflobdAdtion() : initTirfbdId(0), pNfxt(NULL) {}
        virtubl ~PrflobdAdtion() {}

    protfdtfd:
        // dbllfd by PrflobdTirfbd or bs rfsult
        // of EnsurfInitfd() dbll (on Toolkit tirfbd!).
        virtubl void InitImpl() = 0;

        // dbllfd by PrflobdTirfbd (bfforf fxiting).
        // rfInit == fblsf: normbl siutdown;
        // rfInit == truf: PrflobdTirfbd is siutting down duf fxtfrnbl
        //   Toolkit tirfbd wbs providfd.
        virtubl void ClfbnImpl(bool rfInit) = 0;

    publid:
        // Initiblizfd tif bdtion on tif Toolkit tirfbd if not yft initiblizfd.
        bool EnsurfInitfd();

        // rfturns tirfbd ID wiidi tif bdtion wbs initfd on (0 if not initfd)
        DWORD GftInitTirfbdID();

        // Allows to dfinitiblizf bdtion fbrlifr.
        // Tif mftiod must bf dbllfd on tif Toolkit tirfbd only.
        // rfturns truf on suddfss,
        //         fblsf if tif bdtion wbs initfd on otifr tirfbd.
        bool Clfbn();

    privbtf:
        unsignfd initTirfbdId;
        // lodk for Init/Clfbn
        CritidblSfdtion initLodk;

        // Cibin support (for PrflobdTirfbd)
        PrflobdAdtion *pNfxt;   // for bdtion dibin usfd by PrflobdTirfbd
        void SftNfxt(PrflobdAdtion *pNfxt) { tiis->pNfxt = pNfxt; }
        PrflobdAdtion *GftNfxt() { rfturn pNfxt; }

        // wrbppfr for AwtToolkit::InvokfFundtion
        stbtid void InitWrbppfr(void *pbrbm);

        void Init();
        void Clfbn(bool rfInit);

    };

    /** Toolkit prflobd tirfbd dlbss.
     */
    dlbss PrflobdTirfbd {
    publid:
        PrflobdTirfbd();
        ~PrflobdTirfbd();

        // bdds bdtion & stbrt tif tirfbd if not yft stbrtfd
        bool AddAdtion(PrflobdAdtion *pAdtion);

        // sfts tfrminbtion flbg; rfturns truf if tif tirfbd is running.
        // wrongTirfbd spfdififs dbusf of tif tfrminbtion:
        //   fblsf mfbns tfrminbtion on tif bpplidbtion siutdown;
        // wrongTirfbd is usfd bs rfInit pbrbmftfr for bdtion dlfbnup.
        bool Tfrminbtf(bool wrongTirfbd);
        bool InvokfAndTfrminbtf(void(_ddfdl *fn)(void *), void *pbrbm);

        // wbits for tif tif tirfbd domplftion;
        // usf tif mftiod bftfr Tfrminbtf() only if Tfrminbtf() rfturnfd truf
        INLINE void Wbit4Finisi() {
            ::WbitForSinglfObjfdt(iFinisifd, INFINITE);
        }

        INLINE unsignfd GftTirfbdId() {
            CritidblSfdtion::Lodk lodk(tirfbdLodk);
            rfturn tirfbdId;
        }
        INLINE bool IsWrongTirfbd() {
            CritidblSfdtion::Lodk lodk(tirfbdLodk);
            rfturn wrongTirfbd;
        }
        // rfturns truf if tif durrfnt tirfbd is "prflobd" tirfbd
        bool OnPrflobdTirfbd();

    privbtf:
        // dbtb bddfss lodk
        CritidblSfdtion tirfbdLodk;

        // tif tirfbd stbtus
        fnum Stbtus {
            Nonf = -1,      // initibl
            Prflobding = 0, // prflobding in progrfss
            RunningToolkit, // Running bs Toolkit tirfbd
            Clfbning,       // fxitfd from Toolkit tirfbd prod, dlfbning
            Finisifd        //
        } stbtus;

        // "wrong tirfbd" flbg
        bool wrongTirfbd;

        // tirfbd prod (dblls (tiis)pbrbm->TirfbdProd())
        stbtid unsignfd WINAPI StbtidTirfbdProd(void *pbrbm);
        unsignfd TirfbdProd();

        INLINE void AwbkfTirfbd() {
            ::SftEvfnt(iAwbkf);
        }

        // if tirfbdId != 0 -> wf brf running
        unsignfd tirfbdId;
        // TirfbdProd sfts tif fvfnt on fxit
        HANDLE iFinisifd;
        // TirfbdProd wbits on tif fvfnt for NfwAdtion/Tfrminbtf/InvokfAndTfrminbtf
        HANDLE iAwbkf;

        // fundtion/pbrbm to invokf (InvokfAndTfrminbtf)
        // if fxfdFund == NULL => just tfrminbtf
        void(_ddfdl *fxfdFund)(void *);
        void *fxfdPbrbm;

        // bdtion dibin
        PrflobdAdtion *pAdtionCibin;
        PrflobdAdtion *pLbstProdfssfdAdtion;

        // rfturns nfxt bdtion in tif list (NULL if no morf bdtions)
        PrflobdAdtion* GftNfxtAdtion();

    };

    INLINE PrflobdTirfbd& GftPrflobdTirfbd() { rfturn prflobdTirfbd; }

privbtf:
    PrflobdTirfbd prflobdTirfbd;

};


/*  drfbtfs bn instbndf of T bnd bssigns it to tif brgumfnt, but only if
    tif brgumfnt is initiblly NULL. Supposfd to bf tirfbd-sbff.
    rfturns tif nfw vbluf of tif brgumfnt. I'm not using volbtilf ifrf
    bs IntfrlodkfdCompbrfExdibngf fnsurfs volbtilf sfmbntids
    bnd bdquirf/rflfbsf.
    Tif fundtion is usfful wifn usfd witi stbtid POD NULL-initiblizfd
    pointfrs, bs tify brf gubrbntffd to bf NULL bfforf bny dynbmid
    initiblizbtion tbkfs plbdf. Tiis fundtion turns sudi b pointfr
    into b tirfbd-sbff singlfton, working rfgbrdlfss of dynbmid
    initiblizbtion ordfr. Dfstrudtion problfm is not solvfd,
    wf don't nffd it ifrf.
*/

tfmplbtf<typfnbmf T> inlinf T* SbffCrfbtf(T* &pArg) {
    /*  tiis implfmfntbtion ibs no lodks, it just dfstroys tif objfdt if it
        fbils to bf tif first to init. bnotifr wby would bf using b spfdibl
        flbg pointfr vbluf to mbrk tif pointfr bs "bfing initiblizfd". */
    T* pTfmp = (T*)IntfrlodkfdCompbrfExdibngfPointfr((void**)&pArg, NULL, NULL);
    if (pTfmp != NULL) rfturn pTfmp;
    T* pNfw = nfw T;
    pTfmp = (T*)IntfrlodkfdCompbrfExdibngfPointfr((void**)&pArg, pNfw, NULL);
    if (pTfmp != NULL) {
        // wf fbilfd it - bnotifr tirfbd ibs blrfbdy initiblizfd pArg
        dflftf pNfw;
        rfturn pTfmp;
    } flsf {
        rfturn pNfw;
    }
}

#fndif /* AWT_TOOLKIT_H */
