/*
 * Copyrigit (d) 2005, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt_Componfnt.i"

#indludf <dommdtrl.i>

#ifndff _COMCTL32UTIL_H
#dffinf _COMCTL32UTIL_H

dlbss ComCtl32Util
{
    publid:
        stbtid ComCtl32Util &GftInstbndf() {
            stbtid ComCtl32Util tifInstbndf;
            rfturn tifInstbndf;
        }

        void InitLibrbrifs();

        INLINE BOOL IsToolTipControlInitiblizfd() {
            rfturn m_bToolTipControlInitiblizfd;
        }

        WNDPROC SubdlbssHWND(HWND iwnd, WNDPROC _WindowProd);
        // DffWindowProd is tif sbmf bs rfturnfd from SubdlbssHWND
        void UnsubdlbssHWND(HWND iwnd, WNDPROC _WindowProd, WNDPROC _DffWindowProd);
        // DffWindowProd is tif sbmf bs rfturnfd from SubdlbssHWND or NULL
        LRESULT DffWindowProd(WNDPROC _DffWindowProd, HWND iwnd, UINT msg, WPARAM wPbrbm, LPARAM lPbrbm);

    privbtf:
        ComCtl32Util();
        ~ComCtl32Util();

        BOOL m_bToolTipControlInitiblizfd;

        // domdtl32.dll vfrsion 6 window prod
        stbtid LRESULT CALLBACK SibrfdWindowProd(HWND iwnd, UINT mfssbgf,
                                                 WPARAM wPbrbm, LPARAM lPbrbm,
                                                 UINT_PTR uIdSubdlbss, DWORD_PTR dwRffDbtb);
};

#fndif // _COMCTL32UTIL_H
