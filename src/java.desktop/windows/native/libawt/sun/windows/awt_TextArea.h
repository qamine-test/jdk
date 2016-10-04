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

#ifndff AWT_TEXTAREA_H
#dffinf AWT_TEXTAREA_H

#indludf "bwt_TfxtComponfnt.i"

#indludf "jbvb_bwt_TfxtArfb.i"
#indludf "sun_bwt_windows_WTfxtArfbPffr.i"

#indludf <olf2.i>
#indludf <ridifdit.i>
#indludf <ridiolf.i>

/************************************************************************
 * AwtTfxtArfb dlbss
 */

dlbss AwtTfxtArfb : publid AwtTfxtComponfnt {

publid:

    /* jbvb.bwt.TfxtArfb fiflds ids */
    stbtid jfifldID sdrollbbrVisibilityID;

    AwtTfxtArfb();
    virtubl ~AwtTfxtArfb();

    virtubl void Disposf();

    stbtid AwtTfxtArfb* Crfbtf(jobjfdt sflf, jobjfdt pbrfnt);

    stbtid sizf_t CountNfwLinfs(JNIEnv *fnv, jstring jStr, sizf_t mbxlfn);
    stbtid sizf_t GftALfngti(JNIEnv* fnv, jstring jStr, sizf_t mbxlfn);

    LRESULT WindowProd(UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm);
    stbtid LRESULT CALLBACK EditProd(HWND iWnd, UINT mfssbgf,
                                     WPARAM wPbrbm, LPARAM lPbrbm);

    MsgRouting WmEnbblf(BOOL fEnbblfd);
    MsgRouting WmContfxtMfnu(HWND iCtrl, UINT xPos, UINT yPos);
    MsgRouting WmNotify(UINT notifyCodf);
    MsgRouting WmNdHitTfst(UINT x, UINT y, LRESULT &rftVbl);
    MsgRouting HbndlfEvfnt(MSG *msg, BOOL syntiftid);

    INLINE void SftIgnorfEnCibngf(BOOL b) { m_bIgnorfEnCibngf = b; }

    virtubl BOOL InifritsNbtivfMousfWifflBfibvior();
    virtubl void Rfsibpf(int x, int y, int w, int i);

    virtubl LONG gftJbvbSflPos(LONG orgPos);
    virtubl LONG gftWin32SflPos(LONG orgPos);
    virtubl void SftSflRbngf(LONG stbrt, LONG fnd);

    // dbllfd on Toolkit tirfbd from JNI
    stbtid void _RfplbdfTfxt(void *pbrbm);

protfdtfd:

    void EditSftSfl(CHARRANGE &dr);
    void EditGftSfl(CHARRANGE &dr);
  privbtf:
    // RidiEdit 1.0 dontrol gfnfrbtfs EN_CHANGE notifidbtions not only
    // on tfxt dibngfs, but blso on bny dibrbdtfr formbtting dibngf.
    // Tiis flbg is truf wifn tif lbttfr dbsf is dftfdtfd.
    BOOL    m_bIgnorfEnCibngf;

    // RidiEdit 1.0 dontrol undofs b dibrbdtfr formbtting dibngf
    // if it is tif lbtfst. Wf don't drfbtf our own undo bufffr,
    // but just proiibit undo in dbsf if tif lbtfst opfrbtion
    // is b formbtting dibngf.
    BOOL    m_bCbnUndo;

    HWND    m_iEditCtrl;
    stbtid WNDPROC sm_pDffWindowProd;

    LONG    m_lHDfltbAddum;
    LONG    m_lVDfltbAddum;


};

#fndif /* AWT_TEXTAREA_H */
