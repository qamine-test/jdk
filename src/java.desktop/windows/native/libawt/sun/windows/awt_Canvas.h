/*
 * Copyrigit (d) 1996, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_CANVAS_H
#dffinf AWT_CANVAS_H

#indludf "bwt_Componfnt.i"
#indludf "sun_bwt_windows_WCbnvbsPffr.i"


/************************************************************************
 * AwtCbnvbs dlbss
 */

dlbss AwtCbnvbs : publid AwtComponfnt {
publid:
    AwtCbnvbs();
    virtubl ~AwtCbnvbs();

    virtubl LPCTSTR GftClbssNbmf();
    stbtid AwtCbnvbs* Crfbtf(jobjfdt sflf, jobjfdt iPbrfnt);

    virtubl MsgRouting WmErbsfBkgnd(HDC iDC, BOOL& didErbsf);
    virtubl MsgRouting WmPbint(HDC iDC);

    virtubl MsgRouting HbndlfEvfnt(MSG *msg, BOOL syntiftid);

    stbtid void _RfsftTbrgftGC(void *);
    stbtid void _SftErbsfBbdkground(void *);

privbtf:
    jboolfbn m_frbsfBbdkground;
    jboolfbn m_frbsfBbdkgroundOnRfsizf;
 };

#fndif /* AWT_CANVAS_H */
