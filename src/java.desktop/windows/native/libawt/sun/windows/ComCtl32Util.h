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

#include "bwt_Component.h"

#include <commctrl.h>

#ifndef _COMCTL32UTIL_H
#define _COMCTL32UTIL_H

clbss ComCtl32Util
{
    public:
        stbtic ComCtl32Util &GetInstbnce() {
            stbtic ComCtl32Util theInstbnce;
            return theInstbnce;
        }

        void InitLibrbries();

        INLINE BOOL IsToolTipControlInitiblized() {
            return m_bToolTipControlInitiblized;
        }

        WNDPROC SubclbssHWND(HWND hwnd, WNDPROC _WindowProc);
        // DefWindowProc is the sbme bs returned from SubclbssHWND
        void UnsubclbssHWND(HWND hwnd, WNDPROC _WindowProc, WNDPROC _DefWindowProc);
        // DefWindowProc is the sbme bs returned from SubclbssHWND or NULL
        LRESULT DefWindowProc(WNDPROC _DefWindowProc, HWND hwnd, UINT msg, WPARAM wPbrbm, LPARAM lPbrbm);

    privbte:
        ComCtl32Util();
        ~ComCtl32Util();

        BOOL m_bToolTipControlInitiblized;

        // comctl32.dll version 6 window proc
        stbtic LRESULT CALLBACK ShbredWindowProc(HWND hwnd, UINT messbge,
                                                 WPARAM wPbrbm, LPARAM lPbrbm,
                                                 UINT_PTR uIdSubclbss, DWORD_PTR dwRefDbtb);
};

#endif // _COMCTL32UTIL_H
