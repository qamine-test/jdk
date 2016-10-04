/*
 * Copyright (c) 2004, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef WGLSurfbceDbtb_h_Included
#define WGLSurfbceDbtb_h_Included

#include "OGLSurfbceDbtb.h"
#include "WGLGrbphicsConfig.h"
#include "J2D_GL/gl.h"

/**
 * The WGLSDOps structure contbins the WGL-specific informbtion for b given
 * OGLSurfbceDbtb.  It is referenced by the nbtive OGLSDOps structure.
 */
typedef struct _WGLSDOps {
    WGLGrbphicsConfigInfo *configInfo;
    HWND        window;
    HPBUFFERARB pbuffer;
    HDC pbufferDC;
} WGLSDOps;

#endif /* WGLSurfbceDbtb_h_Included */
