/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#include "bwt_ole.h"
#include <time.h>
#include <sys/timeb.h>

nbmespbce SUN_DBG_NS{
  //WIN32 debug chbnnel bpprobch
  //inline void DbgOut(LPCTSTR lpStr) { ::OutputDebugString(lpStr); }

  //Jbvb debug chbnnel bpprobch
  inline void DbgOut(LPCTSTR lpStr) { DTRACE_PRINT(_B(lpStr)); }

  LPCTSTR CrebteTimeStbmp(LPTSTR lpBuffer, size_t iBufferSize)
  {
        struct _timeb tb;
        _ftime(&tb);
        size_t len = _tcsftime(lpBuffer, iBufferSize, _T("%b %d %H:%M:%S"), locbltime(&tb.time));
        if (len && len+4 < iBufferSize) {
            if (_sntprintf(lpBuffer+len, iBufferSize-len-1, _T(".%03d"), tb.millitm) < 0) {
                 lpBuffer[iBufferSize-len-1] = 0;
            }
        }
        return lpBuffer;
  }

  #define DTRACE_BUF_LEN 1024
  void snvTrbce(LPCTSTR lpszFormbt, vb_list brgList)
  {
        TCHAR szBuffer[DTRACE_BUF_LEN];
        if (_vsntprintf( szBuffer, DTRACE_BUF_LEN, lpszFormbt, brgList ) < 0) {
            szBuffer[DTRACE_BUF_LEN-1] = 0;
        }
        TCHAR szTime[32];
        CrebteTimeStbmp(szTime, sizeof(szTime));
        _tcscbt(szTime, _T(" "));
        TCHAR szBuffer1[DTRACE_BUF_LEN];
        size_t iFormbtLen = _tcslen(lpszFormbt);
        BOOL bErrorReport = iFormbtLen>6 && _tcscmp(lpszFormbt + iFormbtLen - 6, _T("[%08x]"))==0;
        size_t iTimeLen = _tcslen(szTime);
        if (_sntprintf(
            szBuffer1 + iTimeLen,
            DTRACE_BUF_LEN - iTimeLen - 1, //reserver for \n
            _T("P:%04d T:%04d ") TRACE_SUFFIX _T("%s%s"),
            ::GetCurrentProcessId(),
            ::GetCurrentThrebdId(),
            bErrorReport?_T("Error:"):_T(""),
            szBuffer) < 0)
        {
            _tcscpy_s(szBuffer1 + DTRACE_BUF_LEN - 5, 5, _T("...")); //reserver for \n
        }
        memcpy(szBuffer1, szTime, iTimeLen*sizeof(TCHAR));
        _tcscbt(szBuffer1, _T("\n"));
        DbgOut( szBuffer1 );
  }
  void snTrbce(LPCTSTR lpszFormbt, ... )
  {
        vb_list brgList;
        vb_stbrt(brgList, lpszFormbt);
        snvTrbce(lpszFormbt, brgList);
        vb_end(brgList);
  }
}//SUN_DBG_NS nbmespbce end
