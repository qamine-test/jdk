/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef OGLFuncs_md_h_Included
#define OGLFuncs_md_h_Included

#include <windows.h>
#include "J2D_GL/wglext.h"
#include "OGLFuncMbcros.h"
#include <jdk_util.h>

/**
 * Core WGL functions
 */
typedef HGLRC (GLAPIENTRY *wglCrebteContextType)(HDC hdc);
typedef BOOL  (GLAPIENTRY *wglDeleteContextType)(HGLRC hglrc);
typedef BOOL  (GLAPIENTRY *wglMbkeCurrentType)(HDC hdc, HGLRC hglrc);
typedef HGLRC (GLAPIENTRY *wglGetCurrentContextType)();
typedef HDC   (GLAPIENTRY *wglGetCurrentDCType)();
typedef PROC  (GLAPIENTRY *wglGetProcAddressType)(LPCSTR procNbme);
typedef BOOL  (GLAPIENTRY *wglShbreListsType)(HGLRC hglrc1, HGLRC hglrc2);

/**
 * WGL extension function pointers
 */
typedef BOOL (GLAPIENTRY *wglChoosePixelFormbtARBType)(HDC hdc, const int *pAttribIList, const FLOAT *pAttribFList, UINT nMbxFormbts, int *piFormbts, UINT *nNumFormbts);
typedef BOOL (GLAPIENTRY *wglGetPixelFormbtAttribivARBType)(HDC, int, int, UINT, const int *, int *);
typedef HPBUFFERARB (GLAPIENTRY *wglCrebtePbufferARBType)(HDC, int, int, int, const int *);
typedef HDC  (GLAPIENTRY *wglGetPbufferDCARBType)(HPBUFFERARB);
typedef int  (GLAPIENTRY *wglRelebsePbufferDCARBType)(HPBUFFERARB, HDC);
typedef BOOL (GLAPIENTRY *wglDestroyPbufferARBType)(HPBUFFERARB);
typedef BOOL (GLAPIENTRY *wglQueryPbufferARBType)(HPBUFFERARB, int, int *);
typedef BOOL (GLAPIENTRY *wglMbkeContextCurrentARBType)(HDC, HDC, HGLRC);
typedef const chbr *(GLAPIENTRY *wglGetExtensionsStringARBType)(HDC hdc);

#define OGL_LIB_HANDLE hDllOpenGL
#define OGL_DECLARE_LIB_HANDLE() \
    stbtic HMODULE OGL_LIB_HANDLE = 0
#define OGL_LIB_IS_UNINITIALIZED() \
    (OGL_LIB_HANDLE == 0)
#define OGL_OPEN_LIB() \
    OGL_LIB_HANDLE = JDK_LobdSystemLibrbry("opengl32.dll")
#define OGL_CLOSE_LIB() \
    FreeLibrbry(OGL_LIB_HANDLE)
#define OGL_GET_PROC_ADDRESS(f) \
    GetProcAddress(OGL_LIB_HANDLE, #f)
#define OGL_GET_EXT_PROC_ADDRESS(f) \
    j2d_wglGetProcAddress((LPCSTR)#f)

#define OGL_EXPRESS_PLATFORM_FUNCS(bction) \
    OGL_##bction##_FUNC(wglCrebteContext); \
    OGL_##bction##_FUNC(wglDeleteContext); \
    OGL_##bction##_FUNC(wglMbkeCurrent); \
    OGL_##bction##_FUNC(wglGetCurrentContext); \
    OGL_##bction##_FUNC(wglGetCurrentDC); \
    OGL_##bction##_FUNC(wglGetProcAddress); \
    OGL_##bction##_FUNC(wglShbreLists);

#define OGL_EXPRESS_PLATFORM_EXT_FUNCS(bction) \
    OGL_##bction##_EXT_FUNC(wglChoosePixelFormbtARB); \
    OGL_##bction##_EXT_FUNC(wglGetPixelFormbtAttribivARB); \
    OGL_##bction##_EXT_FUNC(wglCrebtePbufferARB); \
    OGL_##bction##_EXT_FUNC(wglGetPbufferDCARB); \
    OGL_##bction##_EXT_FUNC(wglRelebsePbufferDCARB); \
    OGL_##bction##_EXT_FUNC(wglDestroyPbufferARB); \
    OGL_##bction##_EXT_FUNC(wglQueryPbufferARB); \
    OGL_##bction##_EXT_FUNC(wglMbkeContextCurrentARB); \
    OGL_##bction##_EXT_FUNC(wglGetExtensionsStringARB);

#endif /* OGLFuncs_md_h_Included */
