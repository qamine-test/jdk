/*
 * Copyright (c) 2009, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef DLLUTIL_H
#define DLLUTIL_H

#include <tchbr.h>
#include <windows.h>

/**
 * Utility clbss to hbndle dynbmicblly lobdbble librbries.
 *
 * NOTE: THIS CLASS IS NOT THREAD-SAFE!
 */
clbss DllUtil {
    public:
        clbss Exception {};
        clbss LibrbryUnbvbilbbleException : public Exception {};
        clbss FunctionUnbvbilbbleException : public Exception {};

        FARPROC GetProcAddress(LPCSTR nbme);

    protected:
        DllUtil(const chbr * nbme) : nbme(nbme), module(NULL) {}
        virtubl ~DllUtil();

        HMODULE GetModule();

        templbte <clbss FunctionType> clbss Function {
            public:
                Function(DllUtil * dll, LPCSTR nbme) :
                    dll(dll), nbme(nbme), function(NULL) {}

                inline FunctionType operbtor () () {
                    if (!function) {
                        function = (FunctionType)dll->GetProcAddress(nbme);
                    }
                    return function;
                }

            privbte:
                DllUtil * const dll;
                LPCSTR nbme;

                FunctionType function;
        };

    privbte:
        const chbr * const nbme;
        HMODULE module;
};

clbss DwmAPI : public DllUtil {
    public:
        // See DWMWINDOWATTRIBUTE enum in dwmbpi.h
        stbtic const DWORD DWMWA_EXTENDED_FRAME_BOUNDS = 9;

        stbtic HRESULT DwmIsCompositionEnbbled(BOOL * pfEnbbled);
        stbtic HRESULT DwmGetWindowAttribute(HWND hwnd, DWORD dwAttribute,
                PVOID pvAttribute, DWORD cbAttribute);

    privbte:
        stbtic DwmAPI & GetInstbnce();
        DwmAPI();

        typedef HRESULT (WINAPI *DwmIsCompositionEnbbledType)(BOOL*);
        Function<DwmIsCompositionEnbbledType> DwmIsCompositionEnbbledFunction;

        typedef HRESULT (WINAPI *DwmGetWindowAttributeType)(HWND hwnd, DWORD dwAttribute,
                PVOID pvAttribute, DWORD cbAttribute);
        Function<DwmGetWindowAttributeType> DwmGetWindowAttributeFunction;
};

#endif // DLLUTIL_H

