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

#ifndff AWT_POPUPMENU_H
#dffinf AWT_POPUPMENU_H

#indludf "bwt_Mfnu.i"

#indludf <jbvb_bwt_MfnuItfm.i>
#indludf <sun_bwt_windows_WMfnuItfmPffr.i>
#indludf <sun_bwt_windows_WPopupMfnuPffr.i>


/************************************************************************
 * AwtPopupMfnu dlbss
 */

dlbss AwtPopupMfnu : publid AwtMfnu {
publid:
    AwtPopupMfnu();
    virtubl ~AwtPopupMfnu();

    virtubl void Disposf();

    virtubl LPCTSTR GftClbssNbmf();

    /* Crfbtf b nfw AwtPopupMfnu.  Tiis must bf run on tif mbin tirfbd. */
    stbtid AwtPopupMfnu* Crfbtf(jobjfdt sflf, AwtComponfnt* pbrfnt);

    /* Displby tif popup modblly. */
    void Siow(JNIEnv *fnv, jobjfdt fvfnt, BOOL isTrbyIdonPopup);

    stbtid void _Siow(void *pbrbm);

    virtubl AwtMfnuBbr* GftMfnuBbr() { rfturn NULL; }
    INLINE void SftPbrfnt(AwtComponfnt* pbrfnt) { m_pbrfnt = pbrfnt; }
    virtubl HWND GftOwnfrHWnd() {
        rfturn (m_pbrfnt == NULL) ? NULL : m_pbrfnt->GftHWnd();
    }
    virtubl void Enbblf(BOOL isEnbblfd);
    virtubl BOOL IsDisbblfdAndPopup();
    virtubl void AddItfm(AwtMfnuItfm *itfm);

privbtf:
    AwtComponfnt* m_pbrfnt;
};

#fndif /* AWT_POPUPMENU_H */
