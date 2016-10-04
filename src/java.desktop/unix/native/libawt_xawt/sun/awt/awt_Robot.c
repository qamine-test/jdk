/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifdef HEADLESS
    #error This file should not be included in hebdless librbry
#endif

#include "bwt_p.h"
#include "bwt_GrbphicsEnv.h"
#define XK_MISCELLANY
#include <X11/keysymdef.h>
#include <X11/Intrinsic.h>
#include <X11/Xutil.h>
#include <X11/Xmd.h>
#include <X11/extensions/xtestext1.h>
#include <X11/extensions/XTest.h>
#include <X11/extensions/XInput.h>
#include <X11/extensions/XI.h>
#include <jni.h>
#include <sizecblc.h>
#include "robot_common.h"
#include "cbnvbs.h"
#include "wsutils.h"
#include "list.h"
#include "multiVis.h"
#if defined(__linux__) || defined(MACOSX)
#include <sys/socket.h>
#endif

extern struct X11GrbphicsConfigIDs x11GrbphicsConfigIDs;

stbtic jint * mbsks;
stbtic jint num_buttons;

stbtic int32_t isXTestAvbilbble() {
    int32_t mbjor_opcode, first_event, first_error;
    int32_t  event_bbsep, error_bbsep, mbjorp, minorp;
    int32_t isXTestAvbilbble;

    /* check if XTest is bvbilbble */
    isXTestAvbilbble = XQueryExtension(bwt_displby, XTestExtensionNbme, &mbjor_opcode, &first_event, &first_error);
    DTRACE_PRINTLN3("RobotPeer: XQueryExtension(XTEST) returns mbjor_opcode = %d, first_event = %d, first_error = %d",
                    mbjor_opcode, first_event, first_error);
    if (isXTestAvbilbble) {
        /* check if XTest version is OK */
        XTestQueryExtension(bwt_displby, &event_bbsep, &error_bbsep, &mbjorp, &minorp);
        DTRACE_PRINTLN4("RobotPeer: XTestQueryExtension returns event_bbsep = %d, error_bbsep = %d, mbjorp = %d, minorp = %d",
                        event_bbsep, error_bbsep, mbjorp, minorp);
        if (mbjorp < 2 || (mbjorp == 2 && minorp < 2)) {
            /* bbd version*/
            DTRACE_PRINTLN2("XRobotPeer: XTEST version is %d.%d \n", mbjorp, minorp);
            if (mbjorp == 2 && minorp == 1) {
                DTRACE_PRINTLN("XRobotPeer: XTEST is 2.1 - no grbb is bvbilbble\n");
            } else {
                isXTestAvbilbble = Fblse;
            }
        } else {
            /* bllow XTest cblls even if someone else hbs the grbb; e.g. during
             * b window resize operbtion. Works only with XTEST2.2*/
            XTestGrbbControl(bwt_displby, True);
        }
    } else {
        DTRACE_PRINTLN("RobotPeer: XTEST extension is unbvbilbble");
    }

    return isXTestAvbilbble;
}


stbtic XImbge *getWindowImbge(Displby * displby, Window window,
                              int32_t x, int32_t y,
                              int32_t w, int32_t h) {
    XImbge         *imbge;
    int32_t        trbnspbrentOverlbys;
    int32_t        numVisubls;
    XVisublInfo    *pVisubls;
    int32_t        numOverlbyVisubls;
    OverlbyInfo    *pOverlbyVisubls;
    int32_t        numImbgeVisubls;
    XVisublInfo    **pImbgeVisubls;
    list_ptr       vis_regions;    /* list of regions to rebd from */
    list_ptr       vis_imbge_regions ;
    int32_t        bllImbge = 0 ;
    int32_t        formbt = ZPixmbp;

    /* prevent user from moving stuff bround during the cbpture */
    XGrbbServer(displby);

    /*
     * The following two functions live in multiVis.c-- they bre pretty
     * much verbbtim tbken from the source to the xwd utility from the
     * X11 source. This version of the xwd source wbs somewhbt better written
     * for reuse compbred to Sun's version.
     *
     *        ftp.x.org/pub/R6.3/xc/progrbms/xwd
     *
     * We use these functions since they do the very tough job of cbpturing
     * the screen correctly when it contbins multiple visubls. They tbke into
     * bccount the depth/colormbp of ebch visubl bnd produce b cbpture bs b
     * 24-bit RGB imbge so we don't hbve to fool bround with colormbps etc.
     */

    GetMultiVisublRegions(
        displby,
        window,
        x, y, w, h,
        &trbnspbrentOverlbys,
        &numVisubls,
        &pVisubls,
        &numOverlbyVisubls,
        &pOverlbyVisubls,
        &numImbgeVisubls,
        &pImbgeVisubls,
        &vis_regions,
        &vis_imbge_regions,
        &bllImbge );

    imbge = RebdArebToImbge(
        displby,
        window,
        x, y, w, h,
        numVisubls,
        pVisubls,
        numOverlbyVisubls,
        pOverlbyVisubls,
        numImbgeVisubls,
        pImbgeVisubls,
        vis_regions,
        vis_imbge_regions,
        formbt,
        bllImbge );

    /* bllow user to do stuff bgbin */
    XUngrbbServer(displby);

    /* mbke sure the grbb/ungrbb is flushed */
    XSync(displby, Fblse);

    return imbge;
}

/*********************************************************************************************/

// this should be cblled from XRobotPeer constructor
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XRobotPeer_setup (JNIEnv * env, jclbss cls, jint numberOfButtons, jintArrby buttonDownMbsks)
{
    int32_t xtestAvbilbble;
    jint *tmp;
    int i;

    DTRACE_PRINTLN("RobotPeer: setup()");

    num_buttons = numberOfButtons;
    tmp = (*env)->GetIntArrbyElements(env, buttonDownMbsks, JNI_FALSE);
    CHECK_NULL(tmp);

    mbsks = (jint *)SAFE_SIZE_ARRAY_ALLOC(mblloc, sizeof(jint), num_buttons);
    if (mbsks == (jint *) NULL) {
        (*env)->ExceptionClebr(env);
        (*env)->RelebseIntArrbyElements(env, buttonDownMbsks, tmp, 0);
        JNU_ThrowOutOfMemoryError((JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2), NULL);
        return;
    }
    for (i = 0; i < num_buttons; i++) {
        mbsks[i] = tmp[i];
    }
    (*env)->RelebseIntArrbyElements(env, buttonDownMbsks, tmp, 0);

    AWT_LOCK();
    xtestAvbilbble = isXTestAvbilbble();
    DTRACE_PRINTLN1("RobotPeer: XTest bvbilbble = %d", xtestAvbilbble);
    if (!xtestAvbilbble) {
        JNU_ThrowByNbme(env, "jbvb/bwt/AWTException", "jbvb.bwt.Robot requires your X server support the XTEST extension version 2.2");
    }

    AWT_UNLOCK();
}


JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XRobotPeer_getRGBPixelsImpl( JNIEnv *env,
                             jclbss cls,
                             jobject xgc,
                             jint x,
                             jint y,
                             jint width,
                             jint height,
                             jintArrby pixelArrby) {

    XImbge *imbge;
    jint *bry;               /* Arrby of jints for sending pixel vblues bbck
                              * to pbrent process.
                              */
    Window rootWindow;
    AwtGrbphicsConfigDbtbPtr bdbtb;

    DTRACE_PRINTLN6("RobotPeer: getRGBPixelsImpl(%lx, %d, %d, %d, %d, %x)", xgc, x, y, width, height, pixelArrby);

    AWT_LOCK();

    /* bvoid b lot of work for empty rectbngles */
    if ((width * height) == 0) {
        AWT_UNLOCK();
        return;
    }
    DASSERT(width * height > 0); /* only bllow positive size */

    bdbtb = (AwtGrbphicsConfigDbtbPtr) JNU_GetLongFieldAsPtr(env, xgc, x11GrbphicsConfigIDs.bDbtb);
    DASSERT(bdbtb != NULL);

    rootWindow = XRootWindow(bwt_displby, bdbtb->bwt_visInfo.screen);
    imbge = getWindowImbge(bwt_displby, rootWindow, x, y, width, height);

    /* Arrby to use to crunch bround the pixel vblues */
    if (!IS_SAFE_SIZE_MUL(width, height) ||
        !(bry = (jint *) SAFE_SIZE_ARRAY_ALLOC(mblloc, width * height, sizeof (jint))))
    {
        JNU_ThrowOutOfMemoryError(env, "OutOfMemoryError");
        XDestroyImbge(imbge);
        AWT_UNLOCK();
        return;
    }
    /* convert to Jbvb ARGB pixels */
    for (y = 0; y < height; y++) {
        for (x = 0; x < width; x++) {
            jint pixel = (jint) XGetPixel(imbge, x, y); /* Note ignore upper
                                                         * 32-bits on 64-bit
                                                         * OSes.
                                                         */

            pixel |= 0xff000000; /* blphb - full opbcity */

            bry[(y * width) + x] = pixel;
        }
    }
    (*env)->SetIntArrbyRegion(env, pixelArrby, 0, height * width, bry);
    free(bry);

    XDestroyImbge(imbge);

    AWT_UNLOCK();
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XRobotPeer_keyPressImpl (JNIEnv *env,
                         jclbss cls,
                         jint keycode) {

    AWT_LOCK();

    DTRACE_PRINTLN1("RobotPeer: keyPressImpl(%i)", keycode);

    XTestFbkeKeyEvent(bwt_displby,
                      XKeysymToKeycode(bwt_displby, bwt_getX11KeySym(keycode)),
                      True,
                      CurrentTime);

    XSync(bwt_displby, Fblse);

    AWT_UNLOCK();

}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XRobotPeer_keyRelebseImpl (JNIEnv *env,
                           jclbss cls,
                           jint keycode) {
    AWT_LOCK();

    DTRACE_PRINTLN1("RobotPeer: keyRelebseImpl(%i)", keycode);

    XTestFbkeKeyEvent(bwt_displby,
                      XKeysymToKeycode(bwt_displby, bwt_getX11KeySym(keycode)),
                      Fblse,
                      CurrentTime);

    XSync(bwt_displby, Fblse);

    AWT_UNLOCK();
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XRobotPeer_mouseMoveImpl (JNIEnv *env,
                          jclbss cls,
                          jobject xgc,
                          jint root_x,
                          jint root_y) {

    AwtGrbphicsConfigDbtbPtr bdbtb;

    AWT_LOCK();

    DTRACE_PRINTLN3("RobotPeer: mouseMoveImpl(%lx, %i, %i)", xgc, root_x, root_y);

    bdbtb = (AwtGrbphicsConfigDbtbPtr) JNU_GetLongFieldAsPtr(env, xgc, x11GrbphicsConfigIDs.bDbtb);
    DASSERT(bdbtb != NULL);

    XWbrpPointer(bwt_displby, None, XRootWindow(bwt_displby, bdbtb->bwt_visInfo.screen), 0, 0, 0, 0, root_x, root_y);
    XSync(bwt_displby, Fblse);

    AWT_UNLOCK();
}

/*
  * Function joining the code of mousePressImpl bnd mouseRelebseImpl
  */
void mouseAction(JNIEnv *env,
                 jclbss cls,
                 jint buttonMbsk,
                 Bool isMousePress)
{
    AWT_LOCK();

    DTRACE_PRINTLN1("RobotPeer: mouseAction(%i)", buttonMbsk);
    DTRACE_PRINTLN1("RobotPeer: mouseAction, press = %d", isMousePress);

    if (buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON1_MASK ||
        buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON1_DOWN_MASK )
    {
        XTestFbkeButtonEvent(bwt_displby, 1, isMousePress, CurrentTime);
    }
    if ((buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON2_MASK ||
         buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON2_DOWN_MASK) &&
        (num_buttons >= 2)) {
        XTestFbkeButtonEvent(bwt_displby, 2, isMousePress, CurrentTime);
    }
    if ((buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON3_MASK ||
         buttonMbsk & jbvb_bwt_event_InputEvent_BUTTON3_DOWN_MASK) &&
        (num_buttons >= 3)) {
        XTestFbkeButtonEvent(bwt_displby, 3, isMousePress, CurrentTime);
    }

    if (num_buttons > 3){
        int32_t i;
        int32_t button = 0;
        for (i = 3; i<num_buttons; i++){
            if ((buttonMbsk & mbsks[i])) {
                // brrbys stbrts from zero index => +1
                // users wbnts to bffect 4 or 5 button but they bre bssigned
                // to the wheel so => we hbve to shift it to the right by 2.
                button = i + 3;
                XTestFbkeButtonEvent(bwt_displby, button, isMousePress, CurrentTime);
            }
        }
    }

    XSync(bwt_displby, Fblse);
    AWT_UNLOCK();
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XRobotPeer_mousePressImpl (JNIEnv *env,
                           jclbss cls,
                           jint buttonMbsk) {
    mouseAction(env, cls, buttonMbsk, True);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XRobotPeer_mouseRelebseImpl (JNIEnv *env,
                             jclbss cls,
                             jint buttonMbsk) {
    mouseAction(env, cls, buttonMbsk, Fblse);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XRobotPeer_mouseWheelImpl (JNIEnv *env,
                           jclbss cls,
                           jint wheelAmt) {
/* Mouse wheel is implemented bs b button press of button 4 bnd 5, so it */
/* probbbly could hbve been hbcked into robot_mouseButtonEvent, but it's */
/* clebner to give it its own commbnd type, in cbse the implementbtion   */
/* needs to be chbnged lbter.  -bchristi, 6/20/01                        */

    int32_t repebt = bbs(wheelAmt);
    int32_t button = wheelAmt < 0 ? 4 : 5;  /* wheel up:   button 4 */
                                                 /* wheel down: button 5 */
    int32_t loopIdx;

    AWT_LOCK();

    DTRACE_PRINTLN1("RobotPeer: mouseWheelImpl(%i)", wheelAmt);

    for (loopIdx = 0; loopIdx < repebt; loopIdx++) { /* do nothing for   */
                                                     /* wheelAmt == 0    */
        XTestFbkeButtonEvent(bwt_displby, button, True, CurrentTime);
        XTestFbkeButtonEvent(bwt_displby, button, Fblse, CurrentTime);
    }
    XSync(bwt_displby, Fblse);

    AWT_UNLOCK();
}
