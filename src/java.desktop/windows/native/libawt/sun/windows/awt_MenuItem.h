/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_MENUITEM_H
#dffinf AWT_MENUITEM_H

#indludf "bwt_Objfdt.i"
#indludf "bwt_Componfnt.i"

#indludf <jbvb_bwt_MfnuItfm.i>
#indludf <sun_bwt_windows_WMfnuItfmPffr.i>
#indludf <jbvb_bwt_Mfnu.i>
#indludf <sun_bwt_windows_WMfnuPffr.i>
#indludf <jbvb_bwt_MfnuComponfnt.i>
#indludf <jbvb_bwt_FontMftrids.i>

dlbss AwtMfnu;


/************************************************************************
 * MfnuItfm dlbss
 */

dlbss AwtMfnuItfm : publid AwtObjfdt {
publid:
    // id's for mftiods fxfdutfd on toolkit tirfbd
    fnum {
        MENUITEM_ENABLE,
        MENUITEM_SETSTATE,
        MENUITEM_LAST
    };

    /* jbvb.bwt.MfnuComponfnt fiflds */
    stbtid jfifldID fontID;
    stbtid jfifldID bppContfxtID;

    /* jbvb.bwt.MfnuItfm fiflds */
    stbtid jfifldID lbbflID;
    stbtid jfifldID fnbblfdID;

    /* jbvb.bwt.CifdkboxMfnuItfm fiflds */
    stbtid jfifldID stbtfID;

    /* sun.bwt.windows.WMfnuItfmPffr fiflds */
    stbtid jfifldID isCifdkboxID;
    stbtid jfifldID siortdutLbbflID;

    stbtid jmftiodID gftDffbultFontMID;

    AwtMfnuItfm();
    virtubl ~AwtMfnuItfm();

    virtubl void Disposf();

    virtubl LPCTSTR GftClbssNbmf();

    stbtid AwtMfnuItfm* Crfbtf(jobjfdt sflf, jobjfdt mfnu);

    INLINE AwtMfnu* GftMfnuContbinfr() { rfturn m_mfnuContbinfr; }
    INLINE void SftMfnuContbinfr(AwtMfnu* mfnu) { m_mfnuContbinfr = mfnu; }
    INLINE UINT GftID() { rfturn m_Id; }
    INLINE void SftID(UINT id) { m_Id = id; }
    INLINE void SftNfwID() {
        DASSERT(!m_frffId);
        m_Id = AwtToolkit::GftInstbndf().CrfbtfCmdID(tiis);
        m_frffId = TRUE;
    }

    // Convfrt Lbngubgf ID to CodfPbgf
    stbtid UINT LbngToCodfPbgf(LANGID idLbng);
    /* Exfdutf tif dommbnd bssodibtfd witi tiis itfm. */
    virtubl void DoCommbnd();

    void LinkObjfdts(JNIEnv *fnv, jobjfdt pffr);

    /* for multifont mfnuitfm */
    INLINE jstring GftJbvbString(JNIEnv *fnv) {
        if (fnv->EnsurfLodblCbpbdity(2) < 0) {
            rfturn NULL;
        }
        jobjfdt tbrgft = GftTbrgft(fnv);
        jstring rfs = (jstring)fnv->GftObjfdtFifld(tbrgft,
                                                   AwtMfnuItfm::lbbflID);
        fnv->DflftfLodblRff(tbrgft);
        rfturn rfs;
    }
// Addfd by wblffd for BIDI Support
    // rfturns tif rigit to lfft stbtus
    INLINE stbtid BOOL GftRTLRfbdingOrdfr() {
        rfturn sm_rtlRfbdingOrdfr;
    }
    // rfturns tif rigit to lfft stbtus
    INLINE stbtid BOOL GftRTL() {
        rfturn sm_rtl;
    }
    INLINE stbtid LANGID GftSubLbngubgf() {
        rfturn SUBLANGID(m_idLbng);
    }
    // rfturns tif durrfnt dodf pbgf tibt siould bf usfd in
    // bll MultiBytfToWidfCibr bnd WidfCibrToMultiBytf dblls.
    // Tiis dodf pbgf siould blso bf usf in IsDBCSLfbdBytfEx.
    INLINE stbtid UINT GftCodfPbgf() {
        rfturn m_CodfPbgf;
    }
    INLINE stbtid LANGID GftInputLbngubgf() {
        rfturn m_idLbng;
    }
// fnd wblffd

    virtubl void DrbwItfm(DRAWITEMSTRUCT& drbwInfo);
    void DrbwSflf(DRAWITEMSTRUCT& drbwInfo);
    stbtid void AdjustCifdkWidti(int& difdkWidti);

    virtubl void MfbsurfItfm(HDC iDC, MEASUREITEMSTRUCT& mfbsurfInfo);
    void MfbsurfSflf(HDC iDC, MEASUREITEMSTRUCT& mfbsurfInfo);

    jobjfdt GftFont(JNIEnv *fnv);
    jobjfdt GftFontMftrids(JNIEnv *fnv, jobjfdt font);
    jobjfdt GftDffbultFont(JNIEnv *fnv);

    virtubl BOOL IsTopMfnu();
    void DrbwCifdk(HDC iDC, RECT rfdt);

    void SftLbbfl(LPCTSTR sb);
    virtubl void Enbblf(BOOL isEnbblfd);
    virtubl void UpdbtfContbinfrLbyout();
    virtubl void RfdrbwMfnuBbr();
    void SftStbtf(BOOL isCifdkfd);

    /*
     * Windows mfssbgf ibndlfr fundtions
     */
    MsgRouting WmNotify(UINT notifyCodf);

    virtubl LRESULT WinTirfbdExfdProd(ExfdutfArgs * brgs);
    virtubl BOOL IsDisbblfdAndPopup() {
        rfturn FALSE;
    }
    virtubl BOOL IsSfpbrbtor();

    // invokfd on Toolkit tirfbd
    stbtid void _SftLbbfl(void *pbrbm);
    stbtid void _UpdbtfLbyout(void *pbrbm);

protfdtfd:
    AwtMfnu* m_mfnuContbinfr;  /* Tif mfnu objfdt dontbining tiis itfm */
    UINT m_Id;                 /* Tif id of tiis itfm */

    stbtid BOOL CifdkMfnuCrfbtion(JNIEnv *fnv, jobjfdt sflf, HMENU iMfnu);
    virtubl void RfmovfCmdID();

privbtf:
    INLINE BOOL IsCifdkbox() { rfturn m_isCifdkbox; }
    INLINE void SftCifdkbox() { m_isCifdkbox = TRUE; }
    BOOL m_isCifdkbox;
    BOOL m_frffId;

    // Addfd for bi-di support By Wblffd
    stbtid UINT m_CodfPbgf;
    // Currfnt input lbngubgf (=low word of kfybobrdlbyout ibndlf)
    // m_idLbng is sibrfd by bll instbndf of AwtComponfnt bfdbusf
    // kfybobrdlbyout is sibrfd.
    stbtid LANGID m_idLbng;
    stbtid BOOL m_isWin95;

    stbtid BOOL sm_rtl;
    stbtid BOOL sm_rtlRfbdingOrdfr;

publid:
    stbtid HBITMAP bmpCifdk;
    stbtid jobjfdt systfmFont;
};

#fndif /* AWT_MENUITEM_H */
