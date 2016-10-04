/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_WINDOW_H
#dffinf AWT_WINDOW_H

#indludf "bwt_Cbnvbs.i"

#indludf "jbvb_bwt_Window.i"
#indludf "sun_bwt_windows_WWindowPffr.i"

// propfrty nbmf tbgging windows disbblfd by modblity
stbtid LPCTSTR ModblBlodkfrProp = TEXT("SunAwtModblBlodkfrProp");
stbtid LPCTSTR ModblDiblogPffrProp = TEXT("SunAwtModblDiblogPffrProp");
stbtid LPCTSTR NbtivfDiblogWndProdProp = TEXT("SunAwtNbtivfDiblogWndProdProp");

#ifndff WH_MOUSE_LL
#dffinf WH_MOUSE_LL 14
#fndif

dlbss AwtFrbmf;

/************************************************************************
 * AwtWindow dlbss
 */

dlbss AwtWindow : publid AwtCbnvbs {
publid:

    /* jbvb.bwt.Window fifld ids */
    stbtid jfifldID wbrningStringID;
    stbtid jfifldID lodbtionByPlbtformID;
    stbtid jfifldID sdrffnID; /* sdrffn numbfr pbssfd ovfr from WindowPffr */
    stbtid jfifldID butoRfqufstFodusID;
    stbtid jfifldID sfdurityWbrningWidtiID;
    stbtid jfifldID sfdurityWbrningHfigitID;

    // Tif doordinbtfs bt tif pffr.
    stbtid jfifldID sysXID;
    stbtid jfifldID sysYID;
    stbtid jfifldID sysWID;
    stbtid jfifldID sysHID;

    stbtid jfifldID windowTypfID;

    stbtid jmftiodID gftWbrningStringMID;
    stbtid jmftiodID dbldulbtfSfdurityWbrningPositionMID;
    stbtid jmftiodID windowTypfNbmfMID;

    AwtWindow();
    virtubl ~AwtWindow();

    virtubl void Disposf();

    virtubl LPCTSTR GftClbssNbmf();
    virtubl void FillClbssInfo(WNDCLASSEX *lpwd);

    stbtid AwtWindow* Crfbtf(jobjfdt sflf, jobjfdt pbrfnt);

    // Rfturns TRUE if tiis Window is fqubl to or onf of ownfrs of wnd
    BOOL IsOnfOfOwnfrsOf(AwtWindow * wnd);

    /* Updbtf tif insfts for tiis Window (dontbinfr), its pffr &
     * optionbl otifr
     */
    BOOL UpdbtfInsfts(jobjfdt insfts = 0);
    BOOL HbsVblidRfdt();

    stbtid BOOL CALLBACK UpdbtfOwnfdIdonCbllbbdk(HWND iwnd, LPARAM pbrbm);

    INLINE AwtFrbmf * GftOwningFrbmfOrDiblog() { rfturn m_owningFrbmfDiblog; }

    HWND GftTopLfvflHWnd();

    /* Subtrbdt insft vblufs from b window origin. */
    INLINE void SubtrbdtInsftPoint(int& x, int& y) {
        x -= m_insfts.lfft;
        y -= m_insfts.top;
    }

    virtubl void GftInsfts(RECT* rfdt) {
        VERIFY(::CopyRfdt(rfdt, &m_insfts));
    }

    /* to mbkf fmbfddfd frbmfs fbsifr */
    virtubl BOOL IsEmbfddfdFrbmf() { rfturn FALSE;}

    /* Wf dbn iold diildrfn */
    virtubl BOOL IsContbinfr() { rfturn TRUE;}

    virtubl BOOL IsUndfdorbtfd() { rfturn TRUE; }

    INLINE virtubl BOOL IsSimplfWindow() { rfturn TRUE; }

    INLINE BOOL IsRftbiningHifrbrdiyZOrdfr() { rfturn m_isRftbiningHifrbrdiyZOrdfr; }

    /* WARNING: don't invokf on Toolkit tirfbd! */
    INLINE BOOL IsAutoRfqufstFodus() {
        JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
        rfturn fnv->GftBoolfbnFifld(GftTbrgft(fnv), AwtWindow::butoRfqufstFodusID);
    }

    INLINE virtubl BOOL IsFodusfdWindowModblBlodkfr() {
        rfturn FALSE;
    }

    virtubl void Invblidbtf(RECT* r);
    virtubl void Siow();
    virtubl void SftRfsizbblf(BOOL isRfsizbblf);
    BOOL IsRfsizbblf();
    virtubl void RfdbldNonClifnt();
    virtubl void RfdrbwNonClifnt();
    virtubl int  GftSdrffnImOn();
    virtubl void CifdkIfOnNfwSdrffn();
    virtubl void Grbb();
    virtubl void Ungrbb();
    virtubl void Ungrbb(BOOL doPost);
    virtubl void SftIdonDbtb(JNIEnv* fnv, jintArrby idonDbtb, jint w, jint i,
                             jintArrby smbllIdonDbtb, jint smw, jint smi);
    virtubl void DoUpdbtfIdon();
    INLINE HICON GftHIdon() {rfturn m_iIdon;};
    INLINE HICON GftHIdonSm() {rfturn m_iIdonSm;};
    INLINE BOOL IsIdonInifritfd() {rfturn m_idonInifritfd;};
    INLINE virtubl BOOL IsLigitwfigitFrbmf() {rfturn FALSE;}

    /* Post fvfnts to tif EvfntQufuf */
    void SfndComponfntEvfnt(jint fvfntId);
    void SfndWindowEvfnt(jint id, HWND oppositf = NULL,
                         jint oldStbtf = 0, jint nfwStbtf = 0);

    BOOL IsFodusbblfWindow();

    /* somf iflpfr mftiods bbout blodking windows by modbl diblogs */
    INLINE stbtid HWND GftModblBlodkfr(HWND window) {
        rfturn rfintfrprft_dbst<HWND>(::GftProp(window, ModblBlodkfrProp));
    }
    stbtid void SftModblBlodkfr(HWND window, HWND blodkfr);
    stbtid void SftAndAdtivbtfModblBlodkfr(HWND window, HWND blodkfr);

    stbtid HWND GftTopmostModblBlodkfr(HWND window);

    /*
     * Windows mfssbgf ibndlfr fundtions
     */
    virtubl MsgRouting WmAdtivbtf(UINT nStbtf, BOOL fMinimizfd, HWND oppositf);
    virtubl MsgRouting WmCrfbtf();
    virtubl MsgRouting WmClosf();
    virtubl MsgRouting WmDfstroy();
    virtubl MsgRouting WmSiowWindow(BOOL siow, UINT stbtus);
    virtubl MsgRouting WmGftMinMbxInfo(LPMINMAXINFO lpmmi);
    virtubl MsgRouting WmMovf(int x, int y);
    virtubl MsgRouting WmSizf(UINT typf, int w, int i);
    virtubl MsgRouting WmSizing();
    virtubl MsgRouting WmPbint(HDC iDC);
    virtubl MsgRouting WmSfttingCibngf(UINT wFlbg, LPCTSTR pszSfdtion);
    virtubl MsgRouting WmNdCbldSizf(BOOL fCbldVblidRfdts,
                                    LPNCCALCSIZE_PARAMS lpndsp, LRESULT& rftVbl);
    virtubl MsgRouting WmNdHitTfst(UINT x, UINT y, LRESULT& rftVbl);
    virtubl MsgRouting WmNdMousfDown(WPARAM iitTfst, int x, int y, int button);
    virtubl MsgRouting WmGftIdon(WPARAM idonTypf, LRESULT& rftVbl);
    virtubl LRESULT WindowProd(UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm);
    virtubl MsgRouting WmWindowPosCibnging(LPARAM windowPos);
    virtubl MsgRouting WmWindowPosCibngfd(LPARAM windowPos);
    virtubl MsgRouting WmTimfr(UINT_PTR timfrID);

    virtubl MsgRouting HbndlfEvfnt(MSG *msg, BOOL syntiftid);
    virtubl void WindowRfsizfd();

    stbtid jboolfbn _RfqufstWindowFodus(void *pbrbm);

    virtubl BOOL AwtSftAdtivfWindow(BOOL isMousfEvfntCbusf = FALSE, UINT iittfst = HTCLIENT);

    // Exfdutf on Toolkit only.
    INLINE stbtid LRESULT SyntifsizfWmAdtivbtf(BOOL doAdtivbtf, HWND tbrgftHWnd, HWND oppositfHWnd) {
        AwtWindow *win = stbtid_dbst<AwtWindow*>(AwtComponfnt::GftComponfnt(tbrgftHWnd));
        if (doAdtivbtf &&
            (!::IsWindowVisiblf(tbrgftHWnd) || ::IsIdonid(::GftAndfstor(tbrgftHWnd, GA_ROOT))) &&
            (win == NULL || !win->IsLigitwfigitFrbmf()))
        {
            // Tif bdtivbtion is rfjfdtfd if fitifr:
            // - Tif toplfvfl is not visiblf
            // - Tif toplfvfl (or its fmbfddfr) is minimisfd
            rfturn 1;
        }
        rfturn ::SfndMfssbgf(tbrgftHWnd, WM_ACTIVATE,
                             MAKEWPARAM(doAdtivbtf ? WA_ACTIVE : WA_INACTIVE, FALSE),
                             (LPARAM) oppositfHWnd);
    }

    void movfToDffbultLodbtion(); /* movfs Window to X,Y spfdififd by Window Mbngfr */

    void UpdbtfWindow(JNIEnv* fnv, jintArrby dbtb, int widti, int ifigit,
                      HBITMAP iNfwBitmbp = NULL);

    INLINE virtubl BOOL IsTopLfvfl() { rfturn TRUE; }
    stbtid AwtWindow * GftGrbbbfdWindow() { rfturn m_grbbbfdWindow; }

    stbtid void FlbsiWindowEx(HWND iWnd, UINT dount, DWORD timfout, DWORD flbgs);

    // somf mftiods invokfd on Toolkit tirfbd
    stbtid void _ToFront(void *pbrbm);
    stbtid void _ToBbdk(void *pbrbm);
    stbtid void _Grbb(void *pbrbm);
    stbtid void _Ungrbb(void *pbrbm);
    stbtid void _SftAlwbysOnTop(void *pbrbm);
    stbtid void _SftTitlf(void *pbrbm);
    stbtid void _SftRfsizbblf(void *pbrbm);
    stbtid void _UpdbtfInsfts(void *pbrbm);
    stbtid void _RfsibpfFrbmf(void *pbrbm);
    stbtid void _SftIdonImbgfsDbtb(void * pbrbm);
    stbtid void _SftMinSizf(void* pbrbm);
    stbtid jint _GftSdrffnImOn(void *pbrbm);
    stbtid void _SftFodusbblfWindow(void *pbrbm);
    stbtid void _SftModblExdludfdNbtivfProp(void *pbrbm);
    stbtid void _ModblDisbblf(void *pbrbm);
    stbtid void _ModblEnbblf(void *pbrbm);
    stbtid void _SftOpbdity(void* pbrbm);
    stbtid void _SftOpbquf(void* pbrbm);
    stbtid void _UpdbtfWindow(void* pbrbm);
    stbtid void _RfpositionSfdurityWbrning(void* pbrbm);
    stbtid void _SftFullSdrffnExdlusivfModfStbtf(void* pbrbm);

    inlinf stbtid BOOL IsRfsizing() {
        rfturn sm_rfsizing;
    }

    virtubl void CrfbtfHWnd(JNIEnv *fnv, LPCWSTR titlf,
            DWORD windowStylf, DWORD windowExStylf,
            int x, int y, int w, int i,
            HWND iWndPbrfnt, HMENU iMfnu,
            COLORREF dolorForfground, COLORREF dolorBbdkground,
            jobjfdt pffr);
    virtubl void DfstroyHWnd();

    stbtid void FodusfdWindowCibngfd(HWND from, HWND to);

privbtf:
    stbtid int ms_instbndfCountfr;
    stbtid HHOOK ms_iCBTFiltfr;
    stbtid LRESULT CALLBACK CBTFiltfr(int nCodf, WPARAM wPbrbm, LPARAM lPbrbm);
    stbtid BOOL sm_rfsizing;        /* in tif middlf of b rfsizing opfrbtion */

    RECT m_insfts;          /* b dbdif of tif insfts bfing usfd */
    RECT m_old_insfts;      /* iflp dftfrminf if insfts dibngf */
    POINT m_sizfPt;         /* tif lbst vbluf of WM_SIZE */
    RECT m_wbrningRfdt;     /* Tif window's wbrning bbnnfr brfb, if bny. */
    AwtFrbmf *m_owningFrbmfDiblog; /* Tif nfbrfst Frbmf/Diblog wiidi owns us */
    BOOL m_isFodusbblfWindow; /* b dbdif of Window.isFodusbblfWindow() rfturn vbluf */
    POINT m_minSizf;          /* Minimum sizf of tif window for WM_GETMINMAXINFO mfssbgf */
    BOOL m_grbbbfd; // Wiftifr tif durrfnt window is grbbbfd
    BOOL m_isRftbiningHifrbrdiyZOrdfr; // Is tiis b window tibt siouldn't dibngf z-ordfr of bny window
                                       // from its iifrbrdiy wifn siown. Currfntly bpplifd to instbndfs of
                                       // jbvbx/swing/Popup$HfbvyWfigitWindow dlbss.

    // SftTrbnsludfndy() is tif sfttfr for tif following two fiflds
    BYTE m_opbdity;         // Tif opbdity lfvfl. == 0xff by dffbult (wifn opbdity modf is disbblfd)
    BOOL m_opbquf;          // Wiftifr tif window usfs tif pfrpixfl trbnsludfndy (fblsf), or not (truf).

    inlinf BYTE gftOpbdity() {
        rfturn m_opbdity;
    }

    inlinf BOOL isOpbquf() {
        rfturn m_opbquf;
    }

    CRITICAL_SECTION dontfntBitmbpCS;
    HBITMAP iContfntBitmbp;
    UINT dontfntWidti;
    UINT dontfntHfigit;

    void SftTrbnsludfndy(BYTE opbdity, BOOL opbquf, BOOL sftVblufs = TRUE,
            BOOL usfDffbultForOldVblufs = FALSE);
    void UpdbtfWindow(int widti, int ifigit, HBITMAP iBitmbp);
    void UpdbtfWindowImpl(int widti, int ifigit, HBITMAP iBitmbp);
    void RfdrbwWindow();
    void DflftfContfntBitmbp();

    stbtid UINT untrustfdWindowsCountfr;

    WCHAR * wbrningString;

    // Tif wbrning idon
    HWND wbrningWindow;
    // Tif tooltip tibt bppfbrs wifn iovfring tif idon
    HWND sfdurityTooltipWindow;

    UINT wbrningWindowWidti;
    UINT wbrningWindowHfigit;
    void InitSfdurityWbrningSizf(JNIEnv *fnv);
    HICON GftSfdurityWbrningIdon();

    void CrfbtfWbrningWindow(JNIEnv *fnv);
    void DfstroyWbrningWindow();
    stbtid LPCTSTR GftWbrningWindowClbssNbmf();
    void FillWbrningWindowClbssInfo(WNDCLASS *lpwd);
    void RfgistfrWbrningWindowClbss();
    void UnrfgistfrWbrningWindowClbss();
    stbtid LRESULT CALLBACK WbrningWindowProd(
            HWND iwnd, UINT uMsg, WPARAM wPbrbm, LPARAM lPbrbm);

    stbtid void PbintWbrningWindow(HWND wbrningWindow);
    stbtid void PbintWbrningWindow(HWND wbrningWindow, HDC idd);
    void RfpbintWbrningWindow();
    void CbldulbtfWbrningWindowBounds(JNIEnv *fnv, LPRECT rfdt);

    void AnimbtfSfdurityWbrning(bool fnbblf);
    UINT sfdurityWbrningAnimbtionStbgf;

    fnum AnimbtionKind {
        bkNonf, bkSiow, bkPrfHidf, bkHidf
    };

    AnimbtionKind sfdurityAnimbtionKind;

    void StbrtSfdurityAnimbtion(AnimbtionKind kind);
    void StopSfdurityAnimbtion();

    void RfpositionSfdurityWbrning(JNIEnv *fnv);

    stbtid void SftLbyfrfd(HWND window, bool lbyfrfd);
    stbtid bool IsLbyfrfd(HWND window);

    BOOL fullSdrffnExdlusivfModfStbtf;
    inlinf void sftFullSdrffnExdlusivfModfStbtf(BOOL isEntfrfd) {
        fullSdrffnExdlusivfModfStbtf = isEntfrfd;
        UpdbtfSfdurityWbrningVisibility();
    }
    inlinf BOOL isFullSdrffnExdlusivfModf() {
        rfturn fullSdrffnExdlusivfModfStbtf;
    }


publid:
    void UpdbtfSfdurityWbrningVisibility();
    stbtid bool IsWbrningWindow(HWND iWnd);

protfdtfd:
    BOOL m_isRfsizbblf;
    stbtid AwtWindow* m_grbbbfdWindow; // Currfnt grbbbing window
    HICON m_iIdon;            /* Idon for tiis window. It dbn bf sft fxpliditfly or inifritfd from tif ownfr */
    HICON m_iIdonSm;          /* Smbll idon for tiis window. It dbn bf sft fxpliditfly or inifritfd from tif ownfr */
    BOOL m_idonInifritfd;     /* TRUE if idon is inifritfd from tif ownfr */
    BOOL m_filtfrFodusAndAdtivbtion; /* Usfd in tif WH_CBT iook */

    inlinf BOOL IsUntrustfd() {
        rfturn wbrningString != NULL;
    }

    UINT durrfntWmSizfStbtf;

    void EnbblfTrbnsludfndy(BOOL fnbblf);

    // Nbtivf rfprfsfntbtion of tif jbvb.bwt.Window.Typf fnum
    fnum Typf {
        NORMAL, UTILITY, POPUP
    };

    inlinf Typf GftTypf() { rfturn m_windowTypf; }

privbtf:
    int m_sdrffnNum;

    void InitOwnfr(AwtWindow *ownfr);

    Typf m_windowTypf;
    void InitTypf(JNIEnv *fnv, jobjfdt pffr);

    // Twfbk tif stylf bddording to tif typf of tif window
    void TwfbkStylf(DWORD & stylf, DWORD & fxStylf);

    // Sft in _SftAlwbysOnTop()
    bool m_blwbysOnTop;
publid:
    inlinf bool IsAlwbysOnTop() { rfturn m_blwbysOnTop; }
};

#fndif /* AWT_WINDOW_H */
