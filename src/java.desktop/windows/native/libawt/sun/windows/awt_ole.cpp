/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt_olf.i"
#indludf <timf.i>
#indludf <sys/timfb.i>

nbmfspbdf SUN_DBG_NS{
  //WIN32 dfbug dibnnfl bpprobdi
  //inlinf void DbgOut(LPCTSTR lpStr) { ::OutputDfbugString(lpStr); }

  //Jbvb dfbug dibnnfl bpprobdi
  inlinf void DbgOut(LPCTSTR lpStr) { DTRACE_PRINT(_B(lpStr)); }

  LPCTSTR CrfbtfTimfStbmp(LPTSTR lpBufffr, sizf_t iBufffrSizf)
  {
        strudt _timfb tb;
        _ftimf(&tb);
        sizf_t lfn = _tdsftimf(lpBufffr, iBufffrSizf, _T("%b %d %H:%M:%S"), lodbltimf(&tb.timf));
        if (lfn && lfn+4 < iBufffrSizf) {
            if (_sntprintf(lpBufffr+lfn, iBufffrSizf-lfn-1, _T(".%03d"), tb.millitm) < 0) {
                 lpBufffr[iBufffrSizf-lfn-1] = 0;
            }
        }
        rfturn lpBufffr;
  }

  #dffinf DTRACE_BUF_LEN 1024
  void snvTrbdf(LPCTSTR lpszFormbt, vb_list brgList)
  {
        TCHAR szBufffr[DTRACE_BUF_LEN];
        if (_vsntprintf( szBufffr, DTRACE_BUF_LEN, lpszFormbt, brgList ) < 0) {
            szBufffr[DTRACE_BUF_LEN-1] = 0;
        }
        TCHAR szTimf[32];
        CrfbtfTimfStbmp(szTimf, sizfof(szTimf));
        _tdsdbt(szTimf, _T(" "));
        TCHAR szBufffr1[DTRACE_BUF_LEN];
        sizf_t iFormbtLfn = _tdslfn(lpszFormbt);
        BOOL bErrorRfport = iFormbtLfn>6 && _tdsdmp(lpszFormbt + iFormbtLfn - 6, _T("[%08x]"))==0;
        sizf_t iTimfLfn = _tdslfn(szTimf);
        if (_sntprintf(
            szBufffr1 + iTimfLfn,
            DTRACE_BUF_LEN - iTimfLfn - 1, //rfsfrvfr for \n
            _T("P:%04d T:%04d ") TRACE_SUFFIX _T("%s%s"),
            ::GftCurrfntProdfssId(),
            ::GftCurrfntTirfbdId(),
            bErrorRfport?_T("Error:"):_T(""),
            szBufffr) < 0)
        {
            _tdsdpy_s(szBufffr1 + DTRACE_BUF_LEN - 5, 5, _T("...")); //rfsfrvfr for \n
        }
        mfmdpy(szBufffr1, szTimf, iTimfLfn*sizfof(TCHAR));
        _tdsdbt(szBufffr1, _T("\n"));
        DbgOut( szBufffr1 );
  }
  void snTrbdf(LPCTSTR lpszFormbt, ... )
  {
        vb_list brgList;
        vb_stbrt(brgList, lpszFormbt);
        snvTrbdf(lpszFormbt, brgList);
        vb_fnd(brgList);
  }
}//SUN_DBG_NS nbmfspbdf fnd
