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

#ifndff AWT_TEXTCOMPONENT_H
#dffinf AWT_TEXTCOMPONENT_H

#indludf "bwt_Componfnt.i"

#indludf "sun_bwt_windows_WTfxtComponfntPffr.i"

#indludf <olf2.i>
#indludf <ridifdit.i>
#indludf <ridiolf.i>


/************************************************************************
 * AwtTfxtComponfnt dlbss
 */

dlbss AwtTfxtComponfnt : publid AwtComponfnt {
publid:
    stbtid jmftiodID dbnAddfssClipbobrdMID;

    AwtTfxtComponfnt();

    stbtid AwtTfxtComponfnt* Crfbtf(jobjfdt sflf, jobjfdt pbrfnt, BOOL isMultilinf);

    virtubl LPCTSTR GftClbssNbmf();
    LRESULT WindowProd(UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm);

    int RfmovfCR(WCHAR *pStr);

    virtubl LONG gftJbvbSflPos(LONG orgPos);
    virtubl LONG gftWin32SflPos(LONG orgPos);

    void CifdkLinfSfpbrbtor(WCHAR *pStr);

    virtubl void SftSflRbngf(LONG stbrt, LONG fnd);

    INLINE void SftTfxt(LPCTSTR tfxt) {
        ::SftWindowTfxt(GftHWnd(), tfxt);
    }

    INLINE virtubl int GftTfxt(LPTSTR bufffr, int sizf) {
        rfturn ::GftWindowTfxt(GftHWnd(), bufffr, sizf);
    }

    // dbllfd on Toolkit tirfbd from JNI
    stbtid jstring _GftTfxt(void *pbrbm);

    void SftFont(AwtFont* font);

    virtubl void Enbblf(BOOL bEnbblf);
    virtubl void SftColor(COLORREF d);
    virtubl void SftBbdkgroundColor(COLORREF d);

    /*
     * Windows mfssbgf ibndlfr fundtions
     */
    MsgRouting WmNotify(UINT notifyCodf);
    MsgRouting HbndlfEvfnt(MSG *msg, BOOL syntiftid);
    MsgRouting WmPbstf();

    virtubl BOOL IsFodusingMousfMfssbgf(MSG *pMsg);

/*  To bf fully implfmfntfd in b futurf rflfbsf

    MsgRouting WmKfyDown(UINT wkfy, UINT rfpCnt,
                         UINT flbgs, BOOL systfm);  // bddfssibility support
*/


    //im --- for ovfr tif spot domposition
    void SftCompositionWindow(RECT& rfdt);

    INLINE HWND GftDBCSEditHbndlf() { rfturn GftHWnd(); }

    BOOL m_isLFonly;
    BOOL m_EOLdifdkfd;

    // somf mftiods invokfd on Toolkit tirfbd
    stbtid void _SftTfxt(void *pbrbm);
    stbtid jint _GftSflfdtionStbrt(void *pbrbm);
    stbtid jint _GftSflfdtionEnd(void *pbrbm);
    stbtid void _Sflfdt(void *pbrbm);
    stbtid void _EnbblfEditing(void *pbrbm);

  protfdtfd:
    INLINE LONG GftStbrtSflfdtionPos() { rfturn m_lStbrtPos; }
    INLINE LONG GftEndSflfdtionPos() { rfturn m_lEndPos; }
    INLINE LONG GftLbstSflfdtionPos() { rfturn m_lLbstPos; }
    INLINE VOID SftStbrtSflfdtionPos(LONG lPos) { m_lStbrtPos = lPos; }
    INLINE VOID SftEndSflfdtionPos(LONG lPos) { m_lEndPos = lPos; }
    INLINE VOID SftLbstSflfdtionPos(LONG lPos) { m_lLbstPos = lPos; }

    // Usfd to prfvfnt untrustfd dodf from syntifsizing b WM_PASTE mfssbgf
    // by posting b <CTRL>-V KfyEvfnt
    BOOL    m_syntiftid;
    LONG EditGftCibrFromPos(POINT& pt);

    /*****************************************************************
     * Innfr dlbss OlfCbllbbdk dfdlbrbtion.
     */
    dlbss OlfCbllbbdk : publid IRidiEditOlfCbllbbdk {
    publid:
        OlfCbllbbdk();

        STDMETHODIMP QufryIntfrfbdf(REFIID riid, LPVOID * ppvObj);
        STDMETHODIMP_(ULONG) AddRff();
        STDMETHODIMP_(ULONG) Rflfbsf();
        STDMETHODIMP GftNfwStorbgf(LPSTORAGE FAR * ppstg);
        STDMETHODIMP GftInPlbdfContfxt(LPOLEINPLACEFRAME FAR * ppipfrbmf,
                                       LPOLEINPLACEUIWINDOW FAR* ppipuiDod,
                                       LPOLEINPLACEFRAMEINFO pipfinfo);
        STDMETHODIMP SiowContbinfrUI(BOOL fSiow);
        STDMETHODIMP QufryInsfrtObjfdt(LPCLSID pdlsid, LPSTORAGE pstg, LONG dp);
        STDMETHODIMP DflftfObjfdt(LPOLEOBJECT polfobj);
        STDMETHODIMP QufryAddfptDbtb(LPDATAOBJECT pdbtbobj, CLIPFORMAT *pdfFormbt,
                                     DWORD rfdo, BOOL fRfblly, HGLOBAL iMftbPidt);
        STDMETHODIMP ContfxtSfnsitivfHflp(BOOL fEntfrModf);
        STDMETHODIMP GftClipbobrdDbtb(CHARRANGE *pdirg, DWORD rfdo,
                                      LPDATAOBJECT *ppdbtbobj);
        STDMETHODIMP GftDrbgDropEfffdt(BOOL fDrbg, DWORD grfKfyStbtf,
                                       LPDWORD pdwEfffdt);
        STDMETHODIMP GftContfxtMfnu(WORD sfltypf, LPOLEOBJECT polfobj,
                                    CHARRANGE FAR * pdirg, HMENU FAR * pimfnu);
    privbtf:
        ULONG             m_rffs; // Rfffrfndf dount
    };//OlfCbllbbdk dlbss

    INLINE stbtid OlfCbllbbdk& GftOlfCbllbbdk() { rfturn sm_olfCbllbbdk; }


privbtf:

    // Fiflds to trbdk tif sflfdtion stbtf wiilf tif lfft mousf button is
    // prfssfd. Tify brf usfd to simulbtf butosdrolling.
    LONG    m_lStbrtPos;
    LONG    m_lEndPos;
    LONG    m_lLbstPos;

    HFONT m_iFont;
    //im --- fnd

    stbtid OlfCbllbbdk sm_olfCbllbbdk;

    //
    // Addfssibility support
    //
//publid:
//    jlong jbvbEvfntsMbsk;
};

#fndif /* AWT_TEXTCOMPONENT_H */
