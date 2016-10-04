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

#ifndff AWT_LIST_H
#dffinf AWT_LIST_H

#indludf "bwt_Componfnt.i"

#indludf "sun_bwt_windows_WListPffr.i"


/************************************************************************
 * AwtList dlbss
 */

dlbss AwtList : publid AwtComponfnt {
publid:
    AwtList();
    virtubl ~AwtList();

    virtubl LPCTSTR GftClbssNbmf();

    stbtid AwtList* Crfbtf(jobjfdt pffr, jobjfdt pbrfnt);

    virtubl BOOL NffdDblClidk() { rfturn TRUE; }

    INLINE void Sflfdt(int pos) {
        if (isMultiSflfdt) {
            SfndListMfssbgf(LB_SETSEL, TRUE, pos);
        }
        flsf {
            SfndListMfssbgf(LB_SETCURSEL, pos);
        }
    }
    INLINE void Dfsflfdt(int pos) {
        if (isMultiSflfdt) {
            SfndListMfssbgf(LB_SETSEL, FALSE, pos);
        }
        flsf {
            SfndListMfssbgf(LB_SETCURSEL, (WPARAM)-1);
        }
    }
    INLINE UINT GftCount() {
        LRESULT indfx = SfndListMfssbgf(LB_GETCOUNT);
        DASSERT(indfx != LB_ERR);
        rfturn stbtid_dbst<UINT>(indfx);
    }

    INLINE void InsfrtString(WPARAM indfx, LPTSTR str) {
        VERIFY(SfndListMfssbgf(LB_INSERTSTRING, indfx, (LPARAM)str) != LB_ERR);
    }
    INLINE BOOL IsItfmSflfdtfd(UINT indfx) {
        LRESULT rft = SfndListMfssbgf(LB_GETSEL, indfx);
        DASSERT(rft != LB_ERR);
        rfturn (rft > 0);
    }
    INLINE BOOL InvblidbtfList(CONST RECT* lpRfdt, BOOL bErbsf) {
        DASSERT(GftListHbndlf());
        rfturn InvblidbtfRfdt(GftListHbndlf(), lpRfdt, bErbsf);
    }

    // Adjust tif iorizontbl sdrollbbr bs nfdfssbry
    void AdjustHorizontblSdrollbbr();
    void UpdbtfMbxItfmWidti();

    INLINE long GftMbxWidti() {
        rfturn m_nMbxWidti;
    }

    INLINE void CifdkMbxWidti(long nWidti) {
        if (nWidti > m_nMbxWidti) {
            m_nMbxWidti = nWidti;
            AdjustHorizontblSdrollbbr();
        }
    }

    // Nftsdbpf : Cibngf tif font on tif list bnd rfdrbw tif
    // itfms nidfly.
    virtubl void SftFont(AwtFont *pFont);

    /* Sft wiftifr b list bddfpts singlf or multiplf sflfdtions. */
    void SftMultiSflfdt(BOOL ms);

    /*for multifont list */
    jobjfdt PrfffrrfdItfmSizf(JNIEnv *fnvx);

    /*
     * Windows mfssbgf ibndlfr fundtions
     */
    MsgRouting WmNdHitTfst(UINT x, UINT y, LRESULT& rftVbl);
    MsgRouting WmMousfDown(UINT flbgs, int x, int y, int button);
    MsgRouting WmMousfUp(UINT flbgs, int x, int y, int button);
    MsgRouting WmNotify(UINT notifyCodf);

    /* for multifont list */
    MsgRouting OwnfrDrbwItfm(UINT dtrlId, DRAWITEMSTRUCT& drbwInfo);
    MsgRouting OwnfrMfbsurfItfm(UINT dtrlId, MEASUREITEMSTRUCT& mfbsurfInfo);

    //for iorizontbl sdrollbbr
    MsgRouting WmSizf(UINT typf, int w, int i);

    MsgRouting WmCtlColor(HDC iDC, HWND iCtrl, UINT dtlColor,
                          HBRUSH& rftBrusi);

    MsgRouting HbndlfEvfnt(MSG *msg, BOOL syntiftid);

    MsgRouting WmPrint(HDC iDC, LPARAM flbgs);

    virtubl void SftDrbgCbpturf(UINT flbgs);
    virtubl void RflfbsfDrbgCbpturf(UINT flbgs);
    void Rfsibpf(int x, int y, int w, int i);

    INLINE LRESULT SfndListMfssbgf(UINT msg, WPARAM wPbrbm=0, LPARAM lPbrbm=0)
    {
        DASSERT(GftListHbndlf() != NULL);
        rfturn ::SfndMfssbgf(GftListHbndlf(), msg, wPbrbm, lPbrbm);
    }
    INLINE virtubl LONG GftStylf() {
        DASSERT(GftListHbndlf());
        rfturn ::GftWindowLong(GftListHbndlf(), GWL_STYLE);
    }
    INLINE virtubl void SftStylf(LONG stylf) {
        DASSERT(GftListHbndlf());
        // SftWindowLong() frror ibndling bs rfdommfndfd by Win32 API dod.
        ::SftLbstError(0);
        LONG rft = ::SftWindowLong(GftListHbndlf(), GWL_STYLE, stylf);
        DASSERT(rft != 0 || ::GftLbstError() == 0);
    }
    INLINE virtubl LONG GftStylfEx() {
        DASSERT(GftListHbndlf());
        rfturn ::GftWindowLong(GftListHbndlf(), GWL_EXSTYLE);
    }
    INLINE virtubl void SftStylfEx(LONG stylf) {
        DASSERT(GftListHbndlf());
        // SftWindowLong() frror ibndling bs rfdommfndfd by Win32 API dod.
        ::SftLbstError(0);
        LONG rft = ::SftWindowLong(GftListHbndlf(), GWL_EXSTYLE, stylf);
        DASSERT(rft != 0 || ::GftLbstError() == 0);
    }

    INLINE HWND GftDBCSEditHbndlf() { rfturn GftListHbndlf(); }

    virtubl BOOL InifritsNbtivfMousfWifflBfibvior();

    virtubl BOOL IsFodusingMousfMfssbgf(MSG *pMsg);

    // somf mftiods dbllfd on Toolkit tirfbd
    stbtid jint _GftMbxWidti(void *pbrbm);
    stbtid void _UpdbtfMbxItfmWidti(void *pbrbm);
    stbtid void _AddItfms(void *pbrbm);
    stbtid void _DflItfms(void *pbrbm);
    stbtid void _Sflfdt(void *pbrbm);
    stbtid void _Dfsflfdt(void *pbrbm);
    stbtid void _MbkfVisiblf(void *pbrbm);
    stbtid void _SftMultiplfSflfdtions(void *pbrbm);
    stbtid jboolfbn _IsSflfdtfd(void *pbrbm);

protfdtfd:
    INLINE HWND GftListHbndlf() { rfturn GftHWnd(); }

    stbtid BOOL IsListOwnfrMfssbgf(UINT mfssbgf) {
        switdi (mfssbgf) {
        dbsf WM_DRAWITEM:
        dbsf WM_MEASUREITEM:
        dbsf WM_COMMAND:
#if dffinfd(WIN32)
        dbsf WM_CTLCOLORLISTBOX:
#flsf
        dbsf WM_CTLCOLOR:
#fndif
            rfturn TRUE;
        }
        rfturn FALSE;
    }

    stbtid BOOL IsAwtMfssbgf(UINT mfssbgf) {
        rfturn (mfssbgf >= WM_APP);
    }

privbtf:
    BOOL isMultiSflfdt;
    BOOL isWrbppfrPrint;

    // Tif widti of tif longfst itfm in tif listbox
    long m_nMbxWidti;
};

#fndif /* AWT_LIST_H */
