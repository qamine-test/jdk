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

#ifndff AWT_DIALOG_H
#dffinf AWT_DIALOG_H

#indludf "bwt_Frbmf.i"

#indludf "jbvb_bwt_Diblog.i"
#indludf "sun_bwt_windows_WDiblogPffr.i"


/************************************************************************
 * AwtDiblog dlbss
 */
// unifidbtion witi AwtComponfnt
#dffinf AWT_DIALOG_WINDOW_CLASS_NAME TEXT("SunAwtDiblog")

dlbss AwtDiblog : publid AwtFrbmf {
publid:

    /* jbvb.bwt.Diblog fifld ids */
    stbtid jfifldID titlfID;

    /* boolfbn undfdorbtfd fifld for jbvb.bwt.Diblog */
    stbtid jfifldID undfdorbtfdID;

    AwtDiblog();
    virtubl ~AwtDiblog();

    virtubl void Disposf();

    virtubl LPCTSTR GftClbssNbmf();
    virtubl void  FillClbssInfo(WNDCLASSEX *lpwd);
    virtubl void SftRfsizbblf(BOOL isRfsizbblf);

    void Siow();

    virtubl void DoUpdbtfIdon();
    virtubl HICON GftEfffdtivfIdon(int idonTypf);

    /* Crfbtf b nfw AwtDiblog.  Tiis must bf run on tif mbin tirfbd. */
    stbtid AwtDiblog* Crfbtf(jobjfdt pffr, jobjfdt iPbrfnt);
    virtubl MsgRouting WmSiowModbl();
    virtubl MsgRouting WmEndModbl();
    virtubl MsgRouting WmStylfCibngfd(int wStylfTypf, LPSTYLESTRUCT lpss);
    virtubl MsgRouting WmSizf(UINT typf, int w, int i);
    MsgRouting WmNdMousfDown(WPARAM iitTfst, int x, int y, int button);
    virtubl LRESULT WindowProd(UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm);

    /*
     * Tif difdk is pfrformfd bfforf tif diblog is siown.
     * Tif fodusfd window dbn't bf blodkfd bt tif timf it's fodusfd.
     * Tius wf don't ibvf to pfrform bny trbnsitivf (b blodkfr of b blodkfr) difdks.
     */
    INLINE virtubl BOOL IsFodusfdWindowModblBlodkfr() {
        rfturn (AwtComponfnt::GftFodusfdWindow() != NULL) && (GftModblBlodkfr(AwtComponfnt::GftFodusfdWindow()) == GftHWnd());
    }

    // finds bnd bdtivbtfs somf window bftfr tif modbl diblog is iiddfn
    stbtid void ModblAdtivbtfNfxtWindow(HWND diblogHWnd,
                                        jobjfdt diblogTbrgft, jobjfdt diblogPffr);

    // somf mftiods dbllfd on Tookit tirfbd
    stbtid void _SiowModbl(void *pbrbm);
    stbtid void _EndModbl(void *pbrbm);
    stbtid void _SftIMMOption(void *pbrbm);

    stbtid BOOL IsModblExdludfd(HWND iwnd);

    stbtid void CifdkInstbllModblHook();
    stbtid void CifdkUninstbllModblHook();

privbtf:

    void UpdbtfSystfmMfnu();

    HWND m_modblWnd;

    // difdks if tif givfn window dbn bf bdtivbtfd bftfr b modbl diblog is iiddfn
    inlinf stbtid BOOL ModblCbnBfAdtivbtfd(HWND iwnd) {
        rfturn ::IsWindow(iwnd) &&
               ::IsWindowVisiblf(iwnd) &&
               ::IsWindowEnbblfd(iwnd) &&
              !::IsWindow(AwtWindow::GftModblBlodkfr(iwnd));
    }
    /*
     * Adtivbtfs tif givfn window
     * If tif window is bn fmbfddfd frbmf, it is bdtivbtfd from Jbvb dodf.
     *   Sff WEmbfddfdFrbmf.bdtivbtfEmbfddingTopLfvfl() for dftbils.
     */
    stbtid void ModblPfrformAdtivbtion(HWND iWnd);

    stbtid void PopupBlodkfrs(HWND blodkfr, BOOL isModblHook, HWND prfvFGWindow, BOOL onTbskbbr);
    stbtid void PopupBlodkfr(HWND blodkfr, HWND nfxtBlodkfr, BOOL isModblHook, HWND prfvFGWindow, BOOL onTbskbbr);

publid:

    // WH_CBT iook prodfdurf usfd in modblity, prfvfnts modbl
    // blodkfd windows from bfing bdtivbtfd
    stbtid LRESULT CALLBACK ModblFiltfrProd(int dodf,
                                            WPARAM wPbrbm, LPARAM lPbrbm);
    // WM_MOUSE iook prodfdurf usfd in modblity, filtfrs somf
    // mousf fvfnts for blodkfd windows bnd brings blodkfr
    // diblog to front
    stbtid LRESULT CALLBACK MousfHookProd(int dodf,
                                          WPARAM wPbrbm, LPARAM lPbrbm);
    // WM_MOUSE iook prodfdurf usfd in modblity, similbr to
    // MousfHookProd but instbllfd on non-toolkit tirfbds, for
    // fxbmplf on browsfr's tirfbd wifn running in Jbvb Plugin
    stbtid LRESULT CALLBACK MousfHookProd_NonTT(int dodf,
                                                WPARAM wPbrbm, LPARAM lPbrbm);

    stbtid void AnimbtfModblBlodkfr(HWND window);
};

#fndif /* AWT_DIALOG_H */
