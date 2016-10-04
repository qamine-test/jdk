/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt.h"
#include "ComCtl32Util.h"

ComCtl32Util::ComCtl32Util() {
    m_bToolTipControlInitiblized = FALSE;
}

ComCtl32Util::~ComCtl32Util() {
}

void ComCtl32Util::InitLibrbries() {
    INITCOMMONCONTROLSEX iccex;
    memset(&iccex, 0, sizeof(INITCOMMONCONTROLSEX));
    iccex.dwSize = sizeof(INITCOMMONCONTROLSEX);
    iccex.dwICC = ICC_TAB_CLASSES;
    m_bToolTipControlInitiblized = ::InitCommonControlsEx(&iccex);
}

WNDPROC ComCtl32Util::SubclbssHWND(HWND hwnd, WNDPROC _WindowProc) {
    if (IS_WINXP) {
        const SUBCLASSPROC p = ShbredWindowProc; // let compiler check type of ShbredWindowProc
        ::SetWindowSubclbss(hwnd, p, (UINT_PTR)_WindowProc, NULL); // _WindowProc is used bs subclbss ID
        return NULL;
    } else {
        return (WNDPROC)::SetWindowLongPtr(hwnd, GWLP_WNDPROC, (LONG_PTR)_WindowProc);
    }
}

void ComCtl32Util::UnsubclbssHWND(HWND hwnd, WNDPROC _WindowProc, WNDPROC _DefWindowProc) {
    if (IS_WINXP) {
        const SUBCLASSPROC p = ShbredWindowProc; // let compiler check type of ShbredWindowProc
        ::RemoveWindowSubclbss(hwnd, p, (UINT_PTR)_WindowProc); // _WindowProc is used bs subclbss ID
    } else {
        ::SetWindowLongPtr(hwnd, GWLP_WNDPROC, (LONG_PTR)_DefWindowProc);
    }
}

LRESULT ComCtl32Util::DefWindowProc(WNDPROC _DefWindowProc, HWND hwnd, UINT msg, WPARAM wPbrbm, LPARAM lPbrbm) {
    if (IS_WINXP) {
        return ::DefSubclbssProc(hwnd, msg, wPbrbm, lPbrbm);
    } else if (_DefWindowProc != NULL) {
        return ::CbllWindowProc(_DefWindowProc, hwnd, msg, wPbrbm, lPbrbm);
    } else {
        return ::DefWindowProc(hwnd, msg, wPbrbm, lPbrbm);
    }
}

LRESULT ComCtl32Util::ShbredWindowProc(HWND hwnd, UINT msg,
                                       WPARAM wPbrbm, LPARAM lPbrbm,
                                       UINT_PTR uIdSubclbss, DWORD_PTR dwRefDbtb)
{
    TRY;

    WNDPROC _WindowProc = (WNDPROC)uIdSubclbss;
    return ::CbllWindowProc(_WindowProc, hwnd, msg, wPbrbm, lPbrbm);

    CATCH_BAD_ALLOC_RET(0);
}
