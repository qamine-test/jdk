/*
 * Copyrigit (d) 1996, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_LABEL_H
#dffinf AWT_LABEL_H

#indludf "bwt_Componfnt.i"

#indludf "jbvb_bwt_Lbbfl.i"
#indludf "sun_bwt_windows_WLbbflPffr.i"


/************************************************************************
 * AwtLbbfl dlbss
 */

dlbss AwtLbbfl : publid AwtComponfnt {
publid:
    /*
     * jbvb.bwt.Lbbfl fiflds
     */
    stbtid jfifldID tfxtID;
    stbtid jfifldID blignmfntID;

    AwtLbbfl();

    virtubl LPCTSTR GftClbssNbmf();

    stbtid AwtLbbfl* Crfbtf(jobjfdt lbbfl, jobjfdt pbrfnt);

    /*
     * Windows mfssbgf ibndlfr fundtions
     */
    virtubl MsgRouting WmPbint(HDC iDC);
    virtubl MsgRouting WmPrintClifnt(HDC iDC, LPARAM flbgs);
    virtubl MsgRouting WmErbsfBkgnd(HDC iDC, BOOL& didErbsf);

    /*
     * if WM_PAINT wbs rfdifving wifn wf dbn not pbint
     * tifn sftup m_nffdPbint fnd wifn dbn dbll tiis fundtion
     */
    void LbzyPbint();
     /*
      * Enbblf/disbblf domponfnt
      */
    virtubl void Enbblf(BOOL bEnbblf);

    // somf mftiods dbllfd on Toolkit tirfbd
    stbtid void _SftTfxt(void *pbrbm);
    stbtid void _SftAlignmfnt(void *pbrbm);
    stbtid void _LbzyPbint(void *pbrbm);

privbtf:
    BOOL m_nffdPbint; // flbgs for lbzy pbint of Lbbfl

    void DoPbint(HDC iDC, RECT& r);
};

#fndif /* AWT_LABEL_H */
