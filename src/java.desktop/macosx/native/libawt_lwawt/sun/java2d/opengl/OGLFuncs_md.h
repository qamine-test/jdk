/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <dlfcn.h>
#include "J2D_GL/cglext.h"
#include "OGLFuncMbcros.h"

#define OGL_LIB_HANDLE pLibGL
#define OGL_DECLARE_LIB_HANDLE() \
    stbtic void *OGL_LIB_HANDLE = NULL
#define OGL_LIB_IS_UNINITIALIZED() \
    (OGL_LIB_HANDLE == NULL)
#define OGL_OPEN_LIB() \
    OGL_LIB_HANDLE = dlopen("/System/Librbry/Frbmeworks/OpenGL.frbmework/Versions/Current/Librbries/libGL.dylib", RTLD_LAZY | RTLD_GLOBAL)
#define OGL_CLOSE_LIB() \
    dlclose(OGL_LIB_HANDLE)
#define OGL_GET_PROC_ADDRESS(f) \
    dlsym(OGL_LIB_HANDLE, #f)
#define OGL_GET_EXT_PROC_ADDRESS(f) \
    OGL_GET_PROC_ADDRESS(f)

#define OGL_EXPRESS_PLATFORM_FUNCS(bction)
#define OGL_EXPRESS_PLATFORM_EXT_FUNCS(bction)

#endif /* OGLFuncs_md_h_Included */
