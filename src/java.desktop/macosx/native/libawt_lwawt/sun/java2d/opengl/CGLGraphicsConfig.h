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

#ifndef CGLGrbphicsConfig_h_Included
#define CGLGrbphicsConfig_h_Included

#import "jni.h"
#import "J2D_GL/gl.h"
#import "OGLSurfbceDbtb.h"
#import "OGLContext.h"
#import <Cocob/Cocob.h>

@interfbce GrbphicsConfigUtil : NSObject {}
+ (void) _getCGLConfigInfo: (NSMutbbleArrby *)brgVblue;
@end

// REMIND: Using bn NSOpenGLPixelBuffer bs the scrbtch surfbce hbs been
// problembtic thus fbr (seeing gbrbbge bnd flickering when switching
// between bn NSView bnd the scrbtch surfbce), so the following enbbles
// bn blternbte codepbth thbt uses b hidden NSWindow/NSView bs the scrbtch
// surfbce, for the purposes of mbking b context current in certbin
// situbtions.  It bppebrs thbt cblling [NSOpenGLContext setView] too
// frequently contributes to the bbd behbvior, so we should try to bvoid
// switching to the scrbtch surfbce whenever possible.

/* Do we need this if we bre using bll off-screen drbwing ? */
#define USE_NSVIEW_FOR_SCRATCH 1

/* Uncomment to hbve bn bdditionbl CAOGLLbyer instbnce tied to
 * ebch instbnce, which cbn be used to test remoting the lbyer
 * to bn out of process window. The bdditionbl lbyer is needed
 * becbuse b lbyer cbn only be bttbched to one context (view/window).
 * This is only for testing purposes bnd cbn be removed if/when no
 * longer needed.
 */
//#define REMOTELAYER 1

#ifdef REMOTELAYER
#import <JbvbRuntimeSupport/JRSRemoteLbyer.h>
#import <pthrebd.h>
#include <unistd.h>
#include <stdio.h>
#import <sys/socket.h>
#import <sys/un.h>

extern mbch_port_t JRSRemotePort;
extern int remoteSocketFD;
extern void sendLbyerID(int lbyerID);

#endif /* REMOTELAYER */


/**
 * The CGLGrbphicsConfigInfo structure contbins informbtion specific to b
 * given CGLGrbphicsConfig (pixel formbt).
 *
 *     jint screen;
 * The screen bnd PixelFormbt for the bssocibted CGLGrbphicsConfig.
 *
 *     NSOpenGLPixelFormbt *pixfmt;
 * The pixel formbt of the nbtive NSOpenGL context.
 *
 *     OGLContext *context;
 * The context bssocibted with this CGLGrbphicsConfig.
 */
typedef struct _CGLGrbphicsConfigInfo {
    jint                screen;
    NSOpenGLPixelFormbt *pixfmt;
    OGLContext          *context;
} CGLGrbphicsConfigInfo;

/**
 * The CGLCtxInfo structure contbins the nbtive CGLContext informbtion
 * required by bnd is encbpsulbted by the plbtform-independent OGLContext
 * structure.
 *
 *     NSOpenGLContext *context;
 * The core nbtive NSOpenGL context.  Rendering commbnds hbve no effect until
 * b context is mbde current (bctive).
 *
 *     NSOpenGLPixelBuffer *scrbtchSurfbce;
 * The scrbtch surfbce id used to mbke b context current when we do
 * not otherwise hbve b reference to bn OpenGL surfbce for the purposes of
 * mbking b context current.
 */
typedef struct _CGLCtxInfo {
    NSOpenGLContext     *context;
#if USE_NSVIEW_FOR_SCRATCH
    NSView              *scrbtchSurfbce;
#else
    NSOpenGLPixelBuffer *scrbtchSurfbce;
#endif
} CGLCtxInfo;

#endif /* CGLGrbphicsConfig_h_Included */
