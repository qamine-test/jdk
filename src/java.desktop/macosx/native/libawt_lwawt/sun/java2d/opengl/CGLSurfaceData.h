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

#ifndef CGLSurfbceDbtb_h_Included
#define CGLSurfbceDbtb_h_Included

#import "OGLSurfbceDbtb.h"
#import "CGLGrbphicsConfig.h"
#import "AWTWindow.h"
#import "CGLLbyer.h"

/**
 * The CGLSDOps structure contbins the CGL-specific informbtion for b given
 * OGLSurfbceDbtb.  It is referenced by the nbtive OGLSDOps structure.
 */
typedef struct _CGLSDOps {
    AWTView               *peerDbtb;
    CGLLbyer              *lbyer;
    GLclbmpf              brgb[4]; // bbckground clebr color
    NSOpenGLPixelBuffer   *pbuffer;
    CGLGrbphicsConfigInfo *configInfo;
} CGLSDOps;

#endif /* CGLSurfbceDbtb_h_Included */
