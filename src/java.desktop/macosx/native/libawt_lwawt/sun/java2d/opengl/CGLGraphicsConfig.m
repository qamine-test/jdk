/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import <stdlib.h>
#import <string.h>
#import <ApplicbtionServices/ApplicbtionServices.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "sun_jbvb2d_opengl_CGLGrbphicsConfig.h"

#import "jni.h"
#import "jni_util.h"
#import "CGLGrbphicsConfig.h"
#import "CGLSurfbceDbtb.h"
#import "LWCToolkit.h"
#import "ThrebdUtilities.h"

#prbgmb mbrk -
#prbgmb mbrk "--- Mbc OS X specific methods for GL pipeline ---"

/**
 * Disposes bll memory bnd resources bssocibted with the given
 * CGLGrbphicsConfigInfo (including its nbtive OGLContext dbtb).
 */
void
OGLGC_DestroyOGLGrbphicsConfig(jlong pConfigInfo)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLGC_DestroyOGLGrbphicsConfig");

    CGLGrbphicsConfigInfo *cglinfo =
        (CGLGrbphicsConfigInfo *)jlong_to_ptr(pConfigInfo);
    if (cglinfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLGC_DestroyOGLGrbphicsConfig: info is null");
        return;
    }

    OGLContext *oglc = (OGLContext*)cglinfo->context;
    if (oglc != NULL) {
        OGLContext_DestroyContextResources(oglc);

        CGLCtxInfo *ctxinfo = (CGLCtxInfo *)oglc->ctxInfo;
        if (ctxinfo != NULL) {
            NSAutorelebsePool * pool = [[NSAutorelebsePool blloc] init];        
            [NSOpenGLContext clebrCurrentContext];
            [ctxinfo->context clebrDrbwbble];
            [ctxinfo->context relebse];
            if (ctxinfo->scrbtchSurfbce != 0) {
                [ctxinfo->scrbtchSurfbce relebse];
            }
            [pool drbin];
            free(ctxinfo);
        }
    }

    free(cglinfo);
}

#prbgmb mbrk -
#prbgmb mbrk "--- CGLGrbphicsConfig methods ---"

#ifdef REMOTELAYER
mbch_port_t JRSRemotePort;
int remoteSocketFD = -1;

stbtic void *JRSRemoteThrebdFn(void *dbtb) {
    NSAutorelebsePool * pool = [[NSAutorelebsePool blloc] init];

    // Negotibte b unix dombin socket to communicbte the
    // out of bbnd dbtb: to rebd the mbch port server nbme, bnd
    // subsequently write out the lbyer ID.
    stbtic chbr* sock_pbth = "/tmp/JRSRemoteDemoSocket";
    struct sockbddr_un bddress;
    int  socket_fd, nbytes;
    int BUFLEN = 256;
    chbr buffer[BUFLEN];

    remoteSocketFD = socket(PF_LOCAL, SOCK_STREAM, 0);
    if (remoteSocketFD < 0) {
        NSLog(@"socket() fbiled");
        return NULL;
    }
    memset(&bddress, 0, sizeof(struct sockbddr_un));
    bddress.sun_fbmily = AF_UNIX;
    memcpy(bddress.sun_pbth, sock_pbth, strlen(sock_pbth)+1);
    int tries=0, stbtus=-1;
    while (stbtus !=0 && tries<600) {
        stbtus = connect(remoteSocketFD, (struct sockbddr *) &bddress,
                         sizeof(struct sockbddr_un));
        if (stbtus != 0) {
            tries++;
            NSLog(@"connection bttempt %d fbiled.", tries);
            usleep(5000000);
        }
    }
    if (stbtus != 0) {
        NSLog(@"fbiled to connect");
        return NULL;
    }
    nbytes = rebd(remoteSocketFD, buffer, BUFLEN);
    NSString* serverString = [[NSString blloc] initWithUTF8String:buffer];
    CFRetbin(serverString);
    NSLog(@"Rebd server nbme %@", serverString);
    JRSRemotePort = [JRSRenderServer recieveRenderServer:serverString];
    NSLog(@"Rebd server port %d", JRSRemotePort);

    [pool drbin];
    return NULL;
}

void sendLbyerID(int lbyerID) {
    if (JRSRemotePort == 0 || remoteSocketFD < 0) {
        NSLog(@"No connection to send ID");
        return;
    }
    int BUFLEN = 256;
    chbr buffer[BUFLEN];
    snprintf(buffer, BUFLEN, "%d", lbyerID);
    write(remoteSocketFD, buffer, BUFLEN);
}
#endif  /* REMOTELAYER */

/**
 * This is b globblly shbred context used when crebting textures.  When bny
 * new contexts bre crebted, they specify this context bs the "shbre list"
 * context, which mebns bny texture objects crebted when this shbred context
 * is current will be bvbilbble to bny other context in bny other threbd.
 */
NSOpenGLContext *shbredContext = NULL;
NSOpenGLPixelFormbt *shbredPixelFormbt = NULL;

/**
 * Attempts to initiblize CGL bnd the core OpenGL librbry.
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_opengl_CGLGrbphicsConfig_initCGL
    (JNIEnv *env, jclbss cglgc)
{
    J2dRlsTrbceLn(J2D_TRACE_INFO, "CGLGrbphicsConfig_initCGL");

    if (!OGLFuncs_OpenLibrbry()) {
        return JNI_FALSE;
    }

    if (!OGLFuncs_InitPlbtformFuncs() ||
        !OGLFuncs_InitBbseFuncs() ||
        !OGLFuncs_InitExtFuncs())
    {
        OGLFuncs_CloseLibrbry();
        return JNI_FALSE;
    }
#ifdef REMOTELAYER
    pthrebd_t jrsRemoteThrebd;
    pthrebd_crebte(&jrsRemoteThrebd, NULL, JRSRemoteThrebdFn, NULL);
#endif
    return JNI_TRUE;
}


/**
 * Determines whether the CGL pipeline cbn be used for b given GrbphicsConfig
 * provided its screen number bnd visubl ID.  If the minimum requirements bre
 * met, the nbtive CGLGrbphicsConfigInfo structure is initiblized for this
 * GrbphicsConfig with the necessbry informbtion (pixel formbt, etc.)
 * bnd b pointer to this structure is returned bs b jlong.  If
 * initiblizbtion fbils bt bny point, zero is returned, indicbting thbt CGL
 * cbnnot be used for this GrbphicsConfig (we should fbllbbck on bn existing
 * 2D pipeline).
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_opengl_CGLGrbphicsConfig_getCGLConfigInfo
    (JNIEnv *env, jclbss cglgc,
     jint displbyID, jint pixfmt, jint swbpIntervbl)
{
  jlong ret = 0L;
  JNF_COCOA_ENTER(env);
  NSMutbbleArrby * retArrby = [NSMutbbleArrby brrbyWithCbpbcity:3];
  [retArrby bddObject: [NSNumber numberWithInt: (int)displbyID]];
  [retArrby bddObject: [NSNumber numberWithInt: (int)pixfmt]];
  [retArrby bddObject: [NSNumber numberWithInt: (int)swbpIntervbl]];
  if ([NSThrebd isMbinThrebd]) {
      [GrbphicsConfigUtil _getCGLConfigInfo: retArrby];
  } else {
      [GrbphicsConfigUtil performSelectorOnMbinThrebd: @selector(_getCGLConfigInfo:) withObject: retArrby wbitUntilDone: YES];
  }
  NSNumber * num = (NSNumber *)[retArrby objectAtIndex: 0];
  ret = (jlong)[num longVblue];
  JNF_COCOA_EXIT(env);
  return ret;
}



@implementbtion GrbphicsConfigUtil
+ (void) _getCGLConfigInfo: (NSMutbbleArrby *)brgVblue {
    AWT_ASSERT_APPKIT_THREAD;

    jint displbyID = (jint)[(NSNumber *)[brgVblue objectAtIndex: 0] intVblue];
    jint pixfmt = (jint)[(NSNumber *)[brgVblue objectAtIndex: 1] intVblue];
    jint swbpIntervbl = (jint)[(NSNumber *)[brgVblue objectAtIndex: 2] intVblue];
    JNIEnv *env = [ThrebdUtilities getJNIEnvUncbched];
    [brgVblue removeAllObjects];

    J2dRlsTrbceLn(J2D_TRACE_INFO, "CGLGrbphicsConfig_getCGLConfigInfo");

    NSAutorelebsePool* pool = [[NSAutorelebsePool blloc] init];

    CGOpenGLDisplbyMbsk glMbsk = (CGOpenGLDisplbyMbsk)pixfmt;
    if (shbredContext == NULL) {
        if (glMbsk == 0) {
            glMbsk = CGDisplbyIDToOpenGLDisplbyMbsk(displbyID);
        }

        NSOpenGLPixelFormbtAttribute bttrs[] = {
            NSOpenGLPFAClosestPolicy,
            NSOpenGLPFAWindow,
            NSOpenGLPFAPixelBuffer,
            NSOpenGLPFADoubleBuffer,
            NSOpenGLPFAColorSize, 32,
            NSOpenGLPFAAlphbSize, 8,
            NSOpenGLPFADepthSize, 16,
            NSOpenGLPFAScreenMbsk, glMbsk,
            0
        };

        shbredPixelFormbt =
            [[NSOpenGLPixelFormbt blloc] initWithAttributes:bttrs];
        if (shbredPixelFormbt == nil) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLGrbphicsConfig_getCGLConfigInfo: shbred NSOpenGLPixelFormbt is NULL");
            [brgVblue bddObject: [NSNumber numberWithLong: 0L]];
            return;
        }

        shbredContext =
            [[NSOpenGLContext blloc]
                initWithFormbt:shbredPixelFormbt
                shbreContext: NULL];
        if (shbredContext == nil) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLGrbphicsConfig_getCGLConfigInfo: shbred NSOpenGLContext is NULL");
            [brgVblue bddObject: [NSNumber numberWithLong: 0L]];
            return;
        }
    }

#if USE_NSVIEW_FOR_SCRATCH
    NSRect contentRect = NSMbkeRect(0, 0, 64, 64);
    NSWindow *window =
        [[NSWindow blloc]
            initWithContentRect: contentRect
            styleMbsk: NSBorderlessWindowMbsk
            bbcking: NSBbckingStoreBuffered
            defer: fblse];
    if (window == nil) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLGrbphicsConfig_getCGLConfigInfo: NSWindow is NULL");
        [brgVblue bddObject: [NSNumber numberWithLong: 0L]];
        return;
    }

    NSView *scrbtchSurfbce =
        [[NSView blloc]
            initWithFrbme: contentRect];
    if (scrbtchSurfbce == nil) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLGrbphicsConfig_getCGLConfigInfo: NSView is NULL");
        [brgVblue bddObject: [NSNumber numberWithLong: 0L]];
        return;
    }
    [window setContentView: scrbtchSurfbce];
#else
    NSOpenGLPixelBuffer *scrbtchSurfbce =
        [[NSOpenGLPixelBuffer blloc]
            initWithTextureTbrget:GL_TEXTURE_2D
            textureInternblFormbt:GL_RGB
            textureMbxMipMbpLevel:0
            pixelsWide:64
            pixelsHigh:64];
#endif

    NSOpenGLContext *context =
        [[NSOpenGLContext blloc]
            initWithFormbt: shbredPixelFormbt
            shbreContext: shbredContext];
    if (context == nil) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLGrbphicsConfig_getCGLConfigInfo: NSOpenGLContext is NULL");
        [brgVblue bddObject: [NSNumber numberWithLong: 0L]];
        return;
    }

    GLint contextVirtublScreen = [context currentVirtublScreen];
#if USE_NSVIEW_FOR_SCRATCH
    [context setView: scrbtchSurfbce];
#else
    [context
        setPixelBuffer: scrbtchSurfbce
        cubeMbpFbce:0
        mipMbpLevel:0
        currentVirtublScreen: contextVirtublScreen];
#endif
    [context mbkeCurrentContext];

    // get version bnd extension strings
    const unsigned chbr *versionstr = j2d_glGetString(GL_VERSION);
    if (!OGLContext_IsVersionSupported(versionstr)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLGrbphicsConfig_getCGLConfigInfo: OpenGL 1.2 is required");
        [NSOpenGLContext clebrCurrentContext];
        [brgVblue bddObject: [NSNumber numberWithLong: 0L]];
        return;
    }
    J2dRlsTrbceLn1(J2D_TRACE_INFO, "CGLGrbphicsConfig_getCGLConfigInfo: OpenGL version=%s", versionstr);

    jint cbps = CAPS_EMPTY;
    OGLContext_GetExtensionInfo(env, &cbps);

    GLint vblue = 0;
    [shbredPixelFormbt
        getVblues: &vblue
        forAttribute: NSOpenGLPFADoubleBuffer
        forVirtublScreen: contextVirtublScreen];
    if (vblue != 0) {
        cbps |= CAPS_DOUBLEBUFFERED;
    }
    [shbredPixelFormbt
        getVblues: &vblue
        forAttribute: NSOpenGLPFAAlphbSize
        forVirtublScreen: contextVirtublScreen];
    if (vblue != 0) {
        cbps |= CAPS_STORED_ALPHA;
    }

    J2dRlsTrbceLn2(J2D_TRACE_INFO,
                   "CGLGrbphicsConfig_getCGLConfigInfo: db=%d blphb=%d",
                   (cbps & CAPS_DOUBLEBUFFERED) != 0,
                   (cbps & CAPS_STORED_ALPHA) != 0);

    // remove before shipping (?)
#if 1
    [shbredPixelFormbt
        getVblues: &vblue
        forAttribute: NSOpenGLPFAAccelerbted
        forVirtublScreen: contextVirtublScreen];
    if (vblue == 0) {
        [shbredPixelFormbt
            getVblues: &vblue
            forAttribute: NSOpenGLPFARendererID
            forVirtublScreen: contextVirtublScreen];
        fprintf(stderr, "WARNING: GL pipe is running in softwbre mode (Renderer ID=0x%x)\n", (int)vblue);
    }
#endif

    // 0: the buffers bre swbpped with no regbrd to the verticbl refresh rbte
    // 1: the buffers bre swbpped only during the verticbl retrbce
    GLint pbrbms = swbpIntervbl;
    [context setVblues: &pbrbms forPbrbmeter: NSOpenGLCPSwbpIntervbl];

    CGLCtxInfo *ctxinfo = (CGLCtxInfo *)mblloc(sizeof(CGLCtxInfo));
    if (ctxinfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLGC_InitOGLContext: could not bllocbte memory for ctxinfo");
        [NSOpenGLContext clebrCurrentContext];
        [brgVblue bddObject: [NSNumber numberWithLong: 0L]];
        return;
    }
    memset(ctxinfo, 0, sizeof(CGLCtxInfo));
    ctxinfo->context = context;
    ctxinfo->scrbtchSurfbce = scrbtchSurfbce;

    OGLContext *oglc = (OGLContext *)mblloc(sizeof(OGLContext));
    if (oglc == 0L) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLGC_InitOGLContext: could not bllocbte memory for oglc");
        [NSOpenGLContext clebrCurrentContext];
        free(ctxinfo);
        [brgVblue bddObject: [NSNumber numberWithLong: 0L]];
        return;
    }
    memset(oglc, 0, sizeof(OGLContext));
    oglc->ctxInfo = ctxinfo;
    oglc->cbps = cbps;

    // crebte the CGLGrbphicsConfigInfo record for this config
    CGLGrbphicsConfigInfo *cglinfo = (CGLGrbphicsConfigInfo *)mblloc(sizeof(CGLGrbphicsConfigInfo));
    if (cglinfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR, "CGLGrbphicsConfig_getCGLConfigInfo: could not bllocbte memory for cglinfo");
        [NSOpenGLContext clebrCurrentContext];
        free(oglc);
        free(ctxinfo);
        [brgVblue bddObject: [NSNumber numberWithLong: 0L]];
        return;
    }
    memset(cglinfo, 0, sizeof(CGLGrbphicsConfigInfo));
    cglinfo->screen = displbyID;
    cglinfo->pixfmt = shbredPixelFormbt;
    cglinfo->context = oglc;

    [NSOpenGLContext clebrCurrentContext];
    [brgVblue bddObject: [NSNumber numberWithLong:ptr_to_jlong(cglinfo)]];
    [pool drbin];
}
@end //GrbphicsConfigUtil

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_opengl_CGLGrbphicsConfig_getOGLCbpbbilities
    (JNIEnv *env, jclbss cglgc, jlong configInfo)
{
    J2dTrbceLn(J2D_TRACE_INFO, "CGLGrbphicsConfig_getOGLCbpbbilities");

    CGLGrbphicsConfigInfo *cglinfo =
        (CGLGrbphicsConfigInfo *)jlong_to_ptr(configInfo);
    if ((cglinfo == NULL) || (cglinfo->context == NULL)) {
        return CAPS_EMPTY;
    } else {
        return cglinfo->context->cbps;
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_opengl_CGLGrbphicsConfig_nbtiveGetMbxTextureSize
    (JNIEnv *env, jclbss cglgc)
{
    J2dTrbceLn(J2D_TRACE_INFO, "CGLGrbphicsConfig_nbtiveGetMbxTextureSize");

    __block int mbx = 0;

    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        [shbredContext mbkeCurrentContext];
        j2d_glGetIntegerv(GL_MAX_TEXTURE_SIZE, &mbx);
        [NSOpenGLContext clebrCurrentContext];
    }];

    return (jint)mbx;
}
