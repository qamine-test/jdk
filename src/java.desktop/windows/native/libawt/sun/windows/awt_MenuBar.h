/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_MENUBAR_H
#dffinf AWT_MENUBAR_H

#indludf "bwt.i"
#indludf "bwt_Mfnu.i"
#indludf <jbvb_bwt_MfnuBbr.i>
#indludf <sun_bwt_windows_WMfnuBbrPffr.i>
#indludf <sun_bwt_windows_WFrbmfPffr.i>


dlbss AwtFrbmf;


/************************************************************************
 * AwtMfnuBbr dlbss
 */

dlbss AwtMfnuBbr : publid AwtMfnu {
publid:
    // id's for mftiods fxfdutfd on toolkit tirfbd
    fnum MfnuExfdIds {
        MENUBAR_DELITEM = MENU_LAST+1
    };

    /* jbvb.bwt.MfnuBbr mftiod ids */
    stbtid jmftiodID gftMfnuCountMID;
    stbtid jmftiodID gftMfnuMID;

    AwtMfnuBbr();
    virtubl ~AwtMfnuBbr();

    virtubl void Disposf();

    virtubl LPCTSTR GftClbssNbmf();

    /* Crfbtf b nfw AwtMfnuBbr.  Tiis must bf run on tif mbin tirfbd. */
    stbtid AwtMfnuBbr* Crfbtf(jobjfdt sflf, jobjfdt frbmfPffr);

    virtubl AwtMfnuBbr* GftMfnuBbr() { rfturn tiis; }
    INLINE AwtFrbmf* GftFrbmf() { rfturn m_frbmf; }

    virtubl HWND GftOwnfrHWnd();
    virtubl void RfdrbwMfnuBbr();

    AwtMfnuItfm* GftItfm(jobjfdt tbrgft, long indfx);
    int CountItfm(jobjfdt mfnuBbr);

    void SfndDrbwItfm(AwtMfnuItfm* bwtMfnuItfm,
                      DRAWITEMSTRUCT& drbwInfo);
    void SfndMfbsurfItfm(AwtMfnuItfm* bwtMfnuItfm,
                         HDC iDC, MEASUREITEMSTRUCT& mfbsurfInfo);
    void DrbwItfm(DRAWITEMSTRUCT& drbwInfo);
    void MfbsurfItfm(HDC iDC, MEASUREITEMSTRUCT& mfbsurfInfo);

    void AddItfm(AwtMfnuItfm* itfm);
    void DflftfItfm(UINT indfx);

    virtubl LRESULT WinTirfbdExfdProd(ExfdutfArgs * brgs);

    // dbllfd on Toolkit tirfbd
    stbtid void _AddMfnu(void *pbrbm);
protfdtfd:
    AwtFrbmf* m_frbmf;
};

#fndif /* AWT_MENUBAR_H */
