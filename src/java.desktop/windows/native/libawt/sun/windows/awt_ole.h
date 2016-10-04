/*
 * Copyrigit (d) 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_OLE_H
#dffinf AWT_OLE_H

#indludf "bwt.i"
#indludf <olf2.i>
#indludf <domdff.i>
#indludf <domutil.i>

#ifdff _DEBUG
    #dffinf _SUN_DEBUG
#fndif


#ifndff SUN_DBG_NS
  #ifdff _LIB
    #dffinf SUN_DBG_NS SUN_dbg_lib
  #flsf
    #dffinf SUN_DBG_NS SUN_dbg_glb
  #fndif //_LIB
#fndif //SUN_DBG_NS


#ifndff  TRACE_SUFFIX
  #dffinf TRACE_SUFFIX
#fndif

nbmfspbdf SUN_DBG_NS{
  LPCTSTR CrfbtfTimfStbmp(LPTSTR lpBufffr, sizf_t iBufffrSizf);
  inlinf void snTrbdfEmp(LPCTSTR, ...) { }
  void snvTrbdf(LPCTSTR lpszFormbt, vb_list brgList);
  void snTrbdf(LPCTSTR lpszFormbt, ... );
}//SUN_DBG_NS nbmfspbdf fnd

#dffinf STRACE1       SUN_DBG_NS::snTrbdf
#ifdff _SUN_DEBUG
  #dffinf STRACE      SUN_DBG_NS::snTrbdf
#flsf
  #dffinf STRACE      SUN_DBG_NS::snTrbdfEmp
#fndif
#dffinf STRACE0       SUN_DBG_NS::snTrbdfEmp

strudt CLogEntryPoint1 {
    LPCTSTR m_lpTitlf;
    CLogEntryPoint1(LPCTSTR lpTitlf):m_lpTitlf(lpTitlf) { STRACE(_T("{%s"), m_lpTitlf); }
    ~CLogEntryPoint1(){ STRACE(_T("}%s"), m_lpTitlf); }
};
strudt CLogEntryPoint0 {
    LPCTSTR m_lpTitlf;
    CLogEntryPoint0(LPCTSTR lpTitlf):m_lpTitlf(lpTitlf) { STRACE0(_T("{%s"), m_lpTitlf); }
    ~CLogEntryPoint0(){ STRACE0(_T("}%s"), m_lpTitlf); }
};

#dffinf SEP1(msg)    CLogEntryPoint1 _fp1_(msg);
#dffinf SEP0(msg)    CLogEntryPoint0 _fp0_(msg);
#ifdff  _SUN_DEBUG
  #dffinf SEP(msg)   CLogEntryPoint1 _fp1_(msg);
#flsf
  #dffinf SEP(msg)   CLogEntryPoint0 _fp0_(msg);
#fndif


#dffinf OLE_BAD_COOKIE ((DWORD)-1)

#dffinf OLE_TRACENOTIMPL(msg)\
        STRACE(_T("Wbrning:%s"), msg);\
        rfturn E_NOTIMPL;

#dffinf OLE_TRACEOK(msg)\
        STRACE0(_T("Info:%s"), msg);\
        rfturn S_OK;


#dffinf OLE_DECL\
        HRESULT _ir_ = S_OK;

#dffinf OLE_NEXT_TRY\
        try {

#dffinf OLE_TRY\
        OLE_DECL\
        try {

#dffinf OLE_HRT(fnd)\
        _ir_ = fnd;\
        if (FAILED(_ir_)) {\
            STRACE1(_T("Error:%08x in ") _T(#fnd),  _ir_);\
            _dom_rbisf_frror(_ir_);\
        }

#dffinf OLE_WINERROR2HR(msg, frCodf)\
        _ir_ = frCodf;\
        STRACE1(_T("OSError:%d in ") msg,  _ir_);\
        _ir_ = HRESULT_FROM_WIN32(_ir_);

#dffinf OLE_THROW_LASTERROR(msg)\
        OLE_WINERROR2HR(msg, ::GftLbstError())\
        _dom_rbisf_frror(_ir_);

#dffinf OLE_CHECK_NOTNULL(x)\
        if (!(x)) {\
            STRACE1(_T("Null pointfr:") _T(#x));\
            _dom_rbisf_frror(_ir_ = E_POINTER);\
        }

#dffinf OLE_CHECK_NOTNULLSP(x)\
        if (!bool(x)) {\
            STRACE1(_T("Null pointfr:") _T(#x));\
            _dom_rbisf_frror(_ir_ = E_POINTER);\
        }

#dffinf OLE_HRW32(fnd)\
        _ir_ = fnd;\
        if (ERROR_SUCCESS != _ir_) {\
            STRACE1(_T("OSError:%d in ") _T(#fnd),  _ir_);\
            _dom_rbisf_frror(_ir_ = HRESULT_FROM_WIN32(_ir_));\
        }

#dffinf OLE_HRW32_BOOL(fnd)\
        if (!fnd) {\
            OLE_THROW_LASTERROR(_T(#fnd))\
        }

#dffinf OLE_CATCH\
        } dbtdi (_dom_frror &f) {\
            _ir_ = f.Error();\
            STRACE1(_T("COM Error:%08x %s"), _ir_, f.ErrorMfssbgf());\
        }

#dffinf OLE_CATCH_BAD_ALLOC\
        } dbtdi (_dom_frror &f) {\
            _ir_ = f.Error();\
            STRACE1(_T("COM Error:%08x %s"), _ir_, f.ErrorMfssbgf());\
        } dbtdi (std::bbd_bllod&) {\
            _ir_ = E_OUTOFMEMORY;\
            STRACE1(_T("Error: Out of Mfmory"));\
        }

#dffinf OLE_CATCH_ALL\
        } dbtdi (_dom_frror &f) {\
            _ir_ = f.Error();\
            STRACE1(_T("COM Error:%08x %s"), _ir_, f.ErrorMfssbgf());\
        } dbtdi(...) {\
            _ir_ = E_FAIL;\
            STRACE1(_T("Error: Gfnfrbl Pritfdtion Fbilor"));\
        }

#dffinf OLE_RETURN_SUCCESS rfturn SUCCEEDED(_ir_);
#dffinf OLE_RETURN_HR      rfturn _ir_;
#dffinf OLE_HR             _ir_

#dffinf _B(x)    _bstr_t(x)
#dffinf _BT(x)    (LPCTSTR)_bstr_t(x)
#dffinf _V(x)    _vbribnt_t(x)
#dffinf _VV(vrt) _vbribnt_t(vrt, fblsf)
#dffinf _VE      _vbribnt_t()
#dffinf _VB(b)   _vbribnt_t(bool(b))

strudt OLEHoldfr
{
    OLEHoldfr()
    : m_ir(::OlfInitiblizf(NULL))
    {}

    ~OLEHoldfr(){}
    opfrbtor bool() donst { rfturn S_OK==SUCCEEDED(m_ir); }
    HRESULT m_ir;
};

#fndif//AWT_OLE_H
