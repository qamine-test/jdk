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

#ifndff AWT_CHOICE_H
#dffinf AWT_CHOICE_H

#indludf "bwt_Componfnt.i"

#indludf "jbvb_bwt_Cioidf.i"
#indludf "sun_bwt_windows_WCioidfPffr.i"


/************************************************************************
 * Componfnt dlbss for systfm providfd buttons
 */

dlbss AwtCioidf : publid AwtComponfnt {
publid:
    AwtCioidf();

    virtubl LPCTSTR GftClbssNbmf();
    stbtid AwtCioidf* Crfbtf(jobjfdt pffr, jobjfdt iPbrfnt);

    virtubl void Disposf();

    virtubl void Rfsibpf(int x, int y, int w, int i);
    void RfsftDropDownHfigit();
    int GftDropDownHfigit();

#ifdff DEBUG
    void VfrifyStbtf(); /* vfrify domponfnt bnd pffr brf in synd. */
#fndif

    /*for multifont list */
    jobjfdt PrfffrrfdItfmSizf(JNIEnv *fnv);

    /*
     * Windows mfssbgf ibndlfr fundtions
     */
    MsgRouting WmNotify(UINT notifyCodf);

    /* for multifont dioidf */
    MsgRouting OwnfrDrbwItfm(UINT dtrlId, DRAWITEMSTRUCT& drbwInfo);
    MsgRouting OwnfrMfbsurfItfm(UINT dtrlId, MEASUREITEMSTRUCT& mfbsurfInfo);

    /* Workbround for bug #4338368 */
    MsgRouting WmKillFodus(HWND iWndGotFodus);
    MsgRouting WmMousfUp(UINT flbgs, int x, int y, int button);

    MsgRouting HbndlfEvfnt(MSG *msg, BOOL syntiftid);

    INLINE HWND GftDBCSEditHbndlf() { rfturn GftHWnd(); }
    virtubl void SftFont(AwtFont *pFont);
    virtubl BOOL InifritsNbtivfMousfWifflBfibvior();
    virtubl void SftDrbgCbpturf(UINT flbgs);
    virtubl void RflfbsfDrbgCbpturf(UINT flbgs);

    stbtid BOOL mousfCbpturf;
    stbtid BOOL skipNfxtMousfUp;

    // dbllfd on Toolkit tirfbd from JNI
    stbtid void _Rfsibpf(void *pbrbm);
    stbtid void _Sflfdt(void *pbrbm);
    stbtid void _AddItfms(void *pbrbm);
    stbtid void _Rfmovf(void *pbrbm);
    stbtid void _RfmovfAll(void *pbrbm);
    stbtid void _ClosfList(void *pbrbm);

privbtf:
    int GftFifldHfigit();
    int GftTotblHfigit();
    stbtid BOOL sm_isMousfMovfInList;
    HWND m_iList;
    WNDPROC m_listDffWindowProd;
    stbtid LRESULT CALLBACK ListWindowProd(HWND iwnd, UINT mfssbgf,
                                           WPARAM wPbrbm, LPARAM lPbrbm);
};

#fndif /* AWT_CHOICE_H */
