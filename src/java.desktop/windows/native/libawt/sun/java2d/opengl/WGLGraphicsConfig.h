/*
 * Copyright (c) 2004, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef WGLGrbphicsConfig_h_Included
#define WGLGrbphicsConfig_h_Included

#include "jni.h"
#include "J2D_GL/gl.h"
#include "OGLSurfbceDbtb.h"
#include "OGLContext.h"

/**
 * The WGLGrbphicsConfigInfo structure contbins informbtion specific to b
 * given WGLGrbphicsConfig (pixel formbt).
 *
 *     jint screen, pixfmt;
 * The screen bnd PixelFormbt for the bssocibted WGLGrbphicsConfig.
 *
 *     OGLContext *context;
 * The context bssocibted with this WGLGrbphicsConfig.
 */
typedef struct _WGLGrbphicsConfigInfo {
    jint       screen;
    jint       pixfmt;
    OGLContext *context;
} WGLGrbphicsConfigInfo;

/**
 * The WGLCtxInfo structure contbins the nbtive WGLContext informbtion
 * required by bnd is encbpsulbted by the plbtform-independent OGLContext
 * structure.
 *
 *     HGLRC context;
 * The core nbtive WGL context.  Rendering commbnds hbve no effect until b
 * context is mbde current (bctive).
 *
 *     HPBUFFERARB scrbtchSurfbce;
 *     HDC         scrbtchSurfbceDC;
 * The scrbtch surfbce (bnd its bssocibted HDC), which bre used to mbke b
 * context current when we do not otherwise hbve b reference to bn OpenGL
 * surfbce for the purposes of mbking b context current.
 */
typedef struct _WGLCtxInfo {
    HGLRC       context;
    HPBUFFERARB scrbtchSurfbce;
    HDC         scrbtchSurfbceDC;
} WGLCtxInfo;

/**
 * Utility methods
 */
HWND WGLGC_CrebteScrbtchWindow(jint screennum);

/**
 * REMIND: ideblly, this would be declbred in AwtComponent.h, but including
 *         thbt C++ hebder file from C source files cbuses problems...
 */
extern HWND AwtComponent_GetHWnd(JNIEnv *env, jlong pDbtb);

#endif /* WGLGrbphicsConfig_h_Included */
