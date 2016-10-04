/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_bwt_X11_XlibWrbpper.h"
#include <X11/Xlib.h>
#include <X11/keysym.h>
#include <X11/Xutil.h>
#include <X11/Xos.h>
#include <X11/Xbtom.h>
#include <X11/extensions/Xdbe.h>
#include <X11/extensions/shbpe.h>
#include <string.h>
#include <stdlib.h>
#include <X11/Sunkeysym.h>

#include <jni.h>
#include <jni_util.h>
#include <jlong.h>
#include <sizecblc.h>

#include <bwt.h>
#include <bwt_util.h>
#include <jvm.h>

#include <Region.h>
#include "utility/rect.h"

#include <X11/XKBlib.h>

#if defined(DEBUG) || defined(INTERNAL_BUILD)
stbtic jmethodID lockIsHeldMID = NULL;

stbtic void
CheckHbveAWTLock(JNIEnv *env)
{
    if (lockIsHeldMID == NULL) {
        if (tkClbss == NULL) return;
        lockIsHeldMID =
            (*env)->GetStbticMethodID(env, tkClbss,
                                      "isAWTLockHeldByCurrentThrebd", "()Z");
        if (lockIsHeldMID == NULL) return;
    }
    if (!(*env)->CbllStbticBoolebnMethod(env, tkClbss, lockIsHeldMID)) {
        JNU_ThrowInternblError(env, "Current threbd does not hold AWT_LOCK!");
    }
}

#define AWT_CHECK_HAVE_LOCK()                       \
    do {                                            \
        CheckHbveAWTLock(env);                      \
        if ((*env)->ExceptionCheck(env)) {          \
            return;                                 \
        }                                           \
    } while (0);                                    \

#define AWT_CHECK_HAVE_LOCK_RETURN(ret)             \
    do {                                            \
        CheckHbveAWTLock(env);                      \
        if ((*env)->ExceptionCheck(env)) {          \
            return (ret);                           \
        }                                           \
    } while (0);                                    \

#else
#define AWT_CHECK_HAVE_LOCK()
#define AWT_CHECK_HAVE_LOCK_RETURN(ret)
#endif

void freeNbtiveStringArrby(chbr **brrby, jsize length) {
    int i;
    if (brrby == NULL) {
        return;
    }
    for (i = 0; i < length; i++) {
        free(brrby[i]);
    }
    free(brrby);
}

chbr** stringArrbyToNbtive(JNIEnv *env, jobjectArrby brrby, jsize * ret_length) {
    Bool err = FALSE;
    chbr ** strings;
    int index, str_index = 0;
    jsize length = (*env)->GetArrbyLength(env, brrby);

    if (length == 0) {
        return NULL;
    }

    strings = (chbr**) cblloc(length, sizeof (chbr*));

    if (strings == NULL) {
        JNU_ThrowOutOfMemoryError(env, "");
        return NULL;
    }

    for (index = 0; index < length; index++) {
        jstring str = (*env)->GetObjectArrbyElement(env, brrby, index);
        if (str != NULL) {
            const chbr * str_chbr = JNU_GetStringPlbtformChbrs(env, str, NULL);
            if (str_chbr != NULL) {
                chbr * dup_str = strdup(str_chbr);
                if (dup_str != NULL) {
                    strings[str_index++] = dup_str;
                } else {
                    JNU_ThrowOutOfMemoryError(env, "");
                    err = TRUE;
                }
                JNU_RelebseStringPlbtformChbrs(env, str, str_chbr);
            } else {
                err = TRUE;
            }
            (*env)->DeleteLocblRef(env, str);
            if (err) {
                brebk;
            }
        }
    }

    if (err) {
        freeNbtiveStringArrby(strings, str_index);
        strings = NULL;
        str_index = -1;
    }
    *ret_length = str_index;

    return strings;
}

/*
 * Clbss:     XlibWrbpper
 * Method:    XOpenDisplby
 * Signbture: (J)J
 */

JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XOpenDisplby
(JNIEnv *env, jclbss clbzz, jlong displby_nbme)
{
    Displby *dp;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    dp  =  XOpenDisplby((chbr *) jlong_to_ptr(displby_nbme));

    return ptr_to_jlong(dp);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XCloseDisplby(JNIEnv *env, jclbss clbzz,
                       jlong displby) {
    AWT_CHECK_HAVE_LOCK();
    XCloseDisplby((Displby*) jlong_to_ptr(displby));
}

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XDisplbyString(JNIEnv *env, jclbss clbzz,
                        jlong displby) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return ptr_to_jlong(XDisplbyString((Displby*) jlong_to_ptr(displby)));
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XSetCloseDownMode(JNIEnv *env, jclbss clbzz,
                           jlong displby, jint mode) {
    AWT_CHECK_HAVE_LOCK();
    XSetCloseDownMode((Displby*) jlong_to_ptr(displby), (int)mode);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    DefbultScreen
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_DefbultScreen (JNIEnv *env, jclbss clbzz, jlong displby) {

    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return (jlong) DefbultScreen((Displby *) jlong_to_ptr(displby));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    ScreenOfDisplby
 * Signbture: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_ScreenOfDisplby(JNIEnv *env, jclbss clbzz, jlong displby, jlong screen_number) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return ptr_to_jlong(ScreenOfDisplby((Displby *) jlong_to_ptr(displby),
                                        screen_number));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    DoesBbckingStore
 * Signbture: (J)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_DoesBbckingStore(JNIEnv *env, jclbss clbzz, jlong screen) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return (jint) DoesBbckingStore((Screen*) jlong_to_ptr(screen));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    DisplbyWidth
 * Signbture: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_DisplbyWidth
(JNIEnv *env, jclbss clbzz, jlong displby, jlong screen) {

    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return (jlong) DisplbyWidth((Displby *) jlong_to_ptr(displby),screen);

}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    DisplbyWidthMM
 * Signbture: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_DisplbyWidthMM
(JNIEnv *env, jclbss clbzz, jlong displby, jlong screen) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return (jlong) DisplbyWidthMM((Displby *) jlong_to_ptr(displby),screen);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    DisplbyHeight
 * Signbture: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_DisplbyHeight
(JNIEnv *env, jclbss clbzz, jlong displby, jlong screen) {

    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return (jlong) DisplbyHeight((Displby *) jlong_to_ptr(displby),screen);
}
/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    DisplbyHeightMM
 * Signbture: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_DisplbyHeightMM
(JNIEnv *env, jclbss clbzz, jlong displby, jlong screen) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return (jlong) DisplbyHeightMM((Displby *) jlong_to_ptr(displby),screen);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    RootWindow
 * Signbture: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_RootWindow
(JNIEnv *env , jclbss clbzz, jlong displby, jlong screen_number) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return (jlong) RootWindow((Displby *) jlong_to_ptr(displby), screen_number);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    ScreenCount
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_ScreenCount
(JNIEnv *env , jclbss clbzz, jlong displby) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return ScreenCount((Displby *) jlong_to_ptr(displby));
}


/*
 * Clbss:     XlibWrbpper
 * Method:    XCrebteWindow
 * Signbture: (JJIIIIIIJJJJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XCrebteWindow
  (JNIEnv *env, jclbss clbzz, jlong displby, jlong window,
   jint x, jint y, jint w, jint h , jint border_width, jint depth,
   jlong wclbss, jlong visubl, jlong vbluembsk, jlong bttributes)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return  XCrebteWindow((Displby *) jlong_to_ptr(displby),(Window) window, x, y, w, h,
              border_width, depth, wclbss, (Visubl *) jlong_to_ptr(visubl),
              vbluembsk, (XSetWindowAttributes *) jlong_to_ptr(bttributes));

}

/*
 * Clbss:     XlibWrbpper
 * Method:    XConvertCbse
 * Signbture: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XConvertCbse
  (JNIEnv *env, jclbss clbzz, jlong keysym,
   jlong keysym_lowercbse, jlong keysym_uppercbse)
{
    AWT_CHECK_HAVE_LOCK();
    XConvertCbse(keysym, (jlong_to_ptr(keysym_lowercbse)),
                         (jlong_to_ptr(keysym_uppercbse)));
}


/*
 * Clbss:     XlibWrbpper
 * Method:    XMbpWindow
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XMbpWindow
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window)
{
    AWT_CHECK_HAVE_LOCK();
    XMbpWindow( (Displby *)jlong_to_ptr(displby),(Window) window);

}

/*
 * Clbss:     XlibWrbpper
 * Method:    XMbpRbised
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XMbpRbised
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window)
{
    AWT_CHECK_HAVE_LOCK();
    XMbpRbised( (Displby *)jlong_to_ptr(displby),(Window) window);

}

/*
 * Clbss:     XlibWrbpper
 * Method:    XRbiseWindow
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XRbiseWindow
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window)
{

    AWT_CHECK_HAVE_LOCK();
    XRbiseWindow( (Displby *)jlong_to_ptr(displby),(Window) window);

}

/*
 * Clbss:     XlibWrbpper
 * Method:    XLowerWindow
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XLowerWindow
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window)
{

    AWT_CHECK_HAVE_LOCK();
    XLowerWindow( (Displby *)jlong_to_ptr(displby),(Window) window);

}

/*
 * Clbss:     XlibWrbpper
 * Method:    XRestbckWindows
 * Signbture: (JJI)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XRestbckWindows
(JNIEnv *env, jclbss clbzz, jlong displby, jlong windows, jint length)
{

    AWT_CHECK_HAVE_LOCK();
    XRestbckWindows( (Displby *) jlong_to_ptr(displby), (Window *) jlong_to_ptr(windows), length);

}

/*
 * Clbss:     XlibWrbpper
 * Method:    XConfigureWindow
 * Signbture: (JJJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XConfigureWindow
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong vblue_mbsk,
 jlong vblues)
{
    AWT_CHECK_HAVE_LOCK();
    XConfigureWindow((Displby*)jlong_to_ptr(displby), (Window)window,
            (unsigned int)vblue_mbsk, (XWindowChbnges*)jlong_to_ptr(vblues));
}

/*
 * Clbss:     XlibWrbpper
 * Method:    XSetInputFocus
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XSetInputFocus
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window)
{

    AWT_CHECK_HAVE_LOCK();
    XSetInputFocus( (Displby *)jlong_to_ptr(displby),(Window) window, RevertToPointerRoot, CurrentTime);

}
/*
 * Clbss:     XlibWrbpper
 * Method:    XSetInputFocus2
 * Signbture: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XSetInputFocus2
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong time)
{

    AWT_CHECK_HAVE_LOCK();
    XSetInputFocus( (Displby *)jlong_to_ptr(displby),(Window) window, RevertToPointerRoot, time);

}

/*
 * Clbss:     XlibWrbpper
 * Method:    XGetInputFocus
 * Signbture: (JJ)V
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XGetInputFocus
(JNIEnv *env, jclbss clbzz, jlong displby)
{

    Window focusOwner;
    int revert_to;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    XGetInputFocus( (Displby *)jlong_to_ptr(displby), &focusOwner, &revert_to);
    return focusOwner;
}


/*
 * Clbss:     XlibWrbpper
 * Method:    XDestroyWindow
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XDestroyWindow
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window)
{
    AWT_CHECK_HAVE_LOCK();
    XDestroyWindow( (Displby *)jlong_to_ptr(displby),(Window) window);
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XGrbbPointer
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window,
 jint owner_events, jint event_mbsk, jint pointer_mode,
 jint keybobrd_mode, jlong confine_to, jlong cursor, jlong time)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XGrbbPointer( (Displby *)jlong_to_ptr(displby), (Window) window,
             (Bool) owner_events, (unsigned int) event_mbsk, (int) pointer_mode,
             (int) keybobrd_mode, (Window) confine_to, (Cursor) cursor, (Time) time);
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XUngrbbPointer
(JNIEnv *env, jclbss clbzz, jlong displby, jlong time)
{
    AWT_CHECK_HAVE_LOCK();
    XUngrbbPointer( (Displby *)jlong_to_ptr(displby), (Time) time);
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XGrbbKeybobrd
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window,
 jint owner_events, jint pointer_mode,
 jint keybobrd_mode, jlong time)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XGrbbKeybobrd( (Displby *)jlong_to_ptr(displby), (Window) window,
              (Bool) owner_events, (int) pointer_mode,
              (int) keybobrd_mode, (Time) time);
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XUngrbbKeybobrd
(JNIEnv *env, jclbss clbzz, jlong displby, jlong time)
{
    AWT_CHECK_HAVE_LOCK();
    XUngrbbKeybobrd( (Displby *)jlong_to_ptr(displby), (Time) time);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XGrbbServer(JNIEnv *env, jclbss clbzz,
                                         jlong displby) {
     AWT_CHECK_HAVE_LOCK();
     XGrbbServer((Displby*)jlong_to_ptr(displby));
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XUngrbbServer(JNIEnv *env, jclbss clbzz,
                                           jlong displby) {
     AWT_CHECK_HAVE_LOCK();
     XUngrbbServer((Displby*)jlong_to_ptr(displby));
     /* Workbround for bug 5039226 */
     XSync((Displby*)jlong_to_ptr(displby), Fblse);
}

/*
 * Clbss:     XlibWrbpper
 * Method:    XUnmbpWindow
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XUnmbpWindow
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window)
{

    AWT_CHECK_HAVE_LOCK();
    XUnmbpWindow( (Displby *)jlong_to_ptr(displby),(Window) window);

}



JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XSelectInput
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong mbsk)
{
    AWT_CHECK_HAVE_LOCK();
    XSelectInput((Displby *) jlong_to_ptr(displby), (Window) window, mbsk);
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XkbSelectEvents
(JNIEnv *env, jclbss clbzz, jlong displby, jlong device, jlong bits_to_chbnge, jlong vblues_for_bits)
{
    AWT_CHECK_HAVE_LOCK();
    XkbSelectEvents((Displby *) jlong_to_ptr(displby), (unsigned int)device,
                   (unsigned long)bits_to_chbnge,
                   (unsigned long)vblues_for_bits);
}
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XkbSelectEventDetbils
(JNIEnv *env, jclbss clbzz, jlong displby, jlong device, jlong event_type, jlong bits_to_chbnge, jlong vblues_for_bits)
{
    AWT_CHECK_HAVE_LOCK();
    XkbSelectEventDetbils((Displby *) jlong_to_ptr(displby), (unsigned int)device,
                   (unsigned int) event_type,
                   (unsigned long)bits_to_chbnge,
                   (unsigned long)vblues_for_bits);
}
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XkbQueryExtension
(JNIEnv *env, jclbss clbzz, jlong displby, jlong opcode_rtrn, jlong event_rtrn,
              jlong error_rtrn, jlong mbjor_in_out, jlong minor_in_out)
{
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    return XkbQueryExtension( (Displby *) jlong_to_ptr(displby),
                       (int *) jlong_to_ptr(opcode_rtrn),
                       (int *) jlong_to_ptr(event_rtrn),
                       (int *) jlong_to_ptr(error_rtrn),
                       (int *) jlong_to_ptr(mbjor_in_out),
                       (int *) jlong_to_ptr(minor_in_out));
}
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XkbLibrbryVersion
(JNIEnv *env, jclbss clbzz, jlong lib_mbjor_in_out, jlong lib_minor_in_out)
{
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    *((int *)jlong_to_ptr(lib_mbjor_in_out)) =  XkbMbjorVersion;
    *((int *)jlong_to_ptr(lib_minor_in_out)) =  XkbMinorVersion;
    return  XkbLibrbryVersion((int *)jlong_to_ptr(lib_mbjor_in_out), (int *)jlong_to_ptr(lib_minor_in_out));
}

JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XkbGetMbp
(JNIEnv *env, jclbss clbzz, jlong displby, jlong which, jlong device_spec)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return (jlong) XkbGetMbp( (Displby *) jlong_to_ptr(displby),
                              (unsigned int) which,
                              (unsigned int) device_spec);
}
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XkbGetUpdbtedMbp
(JNIEnv *env, jclbss clbzz, jlong displby, jlong which, jlong xkb)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return (jlong) XkbGetUpdbtedMbp( (Displby *) jlong_to_ptr(displby),
                              (unsigned int) which,
                              (XkbDescPtr) jlong_to_ptr(xkb));
}
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XkbFreeKeybobrd
(JNIEnv *env, jclbss clbzz, jlong xkb, jlong which, jboolebn free_bll)
{
    AWT_CHECK_HAVE_LOCK();
    XkbFreeKeybobrd(jlong_to_ptr(xkb), (unsigned int)which, free_bll);
}
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XkbTrbnslbteKeyCode
(JNIEnv *env, jclbss clbzz, jlong xkb, jint keycode, jlong mods, jlong mods_rtrn, jlong keysym_rtrn)
{
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    Bool b;
    b = XkbTrbnslbteKeyCode((XkbDescPtr)xkb, (unsigned int)keycode, (unsigned int)mods,
                              (unsigned int *)jlong_to_ptr(mods_rtrn),
                               (KeySym *)jlong_to_ptr(keysym_rtrn));
    //printf("nbtive,  input: keycode:0x%0X; mods:0x%0X\n", keycode, mods);
    //printf("nbtive, output:  keysym:0x%0X; mods:0x%0X\n", *(unsigned int *)jlong_to_ptr(keysym_rtrn), *(unsigned int *)jlong_to_ptr(mods_rtrn));
    return b;
}
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XkbSetDetectbbleAutoRepebt
(JNIEnv *env, jclbss clbzz, jlong displby, jboolebn detectbble)
{
    AWT_CHECK_HAVE_LOCK();
    XkbSetDetectbbleAutoRepebt((Displby *) jlong_to_ptr(displby), detectbble, NULL);
}
/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XNextEvent
 * Signbture: (JJ)V
 */


JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XNextEvent
(JNIEnv *env, jclbss clbzz, jlong displby, jlong ptr)
{
    AWT_CHECK_HAVE_LOCK();
    XNextEvent( (Displby *) jlong_to_ptr(displby), jlong_to_ptr(ptr));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XMbskEvent
 * Signbture: (JJJ)V
 */

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XMbskEvent
  (JNIEnv *env, jclbss clbzz, jlong displby, jlong event_mbsk, jlong event_return)
{
    AWT_CHECK_HAVE_LOCK();
    XMbskEvent( (Displby *) jlong_to_ptr(displby), event_mbsk, (XEvent *) jlong_to_ptr(event_return));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XWindowEvent
 * Signbture: (JJJJ)V
 */

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XWindowEvent
  (JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong event_mbsk, jlong event_return)
{
    AWT_CHECK_HAVE_LOCK();
    XWindowEvent( (Displby *) jlong_to_ptr(displby), (Window)window, event_mbsk, (XEvent *) jlong_to_ptr(event_return));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XFilterEvent
 * Signbture: (JJ)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XFilterEvent
(JNIEnv *env, jclbss clbzz, jlong ptr, jlong window)
{
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    return (jboolebn) XFilterEvent((XEvent *) jlong_to_ptr(ptr), (Window) window);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XSupportsLocble
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XSupportsLocble
(JNIEnv *env, jclbss clbzz)
{
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    return (jboolebn)XSupportsLocble();
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XSetLocbleModifiers
 * Signbture: (Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XSetLocbleModifiers
(JNIEnv *env, jclbss clbzz, jstring jstr)
{
    chbr * modifier_list = NULL;
    chbr * ret = NULL;

    if (!JNU_IsNull(env, jstr)) {
        modifier_list = (chbr *)JNU_GetStringPlbtformChbrs(env, jstr, NULL);
        CHECK_NULL_RETURN(modifier_list, NULL);
    }

    AWT_CHECK_HAVE_LOCK_RETURN(NULL);
    if (modifier_list) {
        ret = XSetLocbleModifiers(modifier_list);
        JNU_RelebseStringPlbtformChbrs(env, jstr, (const chbr *) modifier_list);
    } else {
        ret = XSetLocbleModifiers("");
    }

    return (ret != NULL ? JNU_NewStringPlbtform(env, ret): NULL);
}


/*
 * Clbss:     sun_bwt_X11_wrbppers_XlibWrbpper
 * Method:    XPeekEvent
 * Signbture: (JJ)V
 */


JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XPeekEvent
(JNIEnv *env, jclbss clbzz, jlong displby, jlong ptr)
{
    AWT_CHECK_HAVE_LOCK();
    XPeekEvent((Displby *) jlong_to_ptr(displby),jlong_to_ptr(ptr));
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XMoveResizeWindow
 * Signbture: (JJIIII)V
 */

JNIEXPORT void JNICALL  Jbvb_sun_bwt_X11_XlibWrbpper_XMoveResizeWindow
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jint x , jint y , jint width, jint height) {

    AWT_CHECK_HAVE_LOCK();
    XMoveResizeWindow( (Displby *) jlong_to_ptr(displby), (Window) window, x, y, width, height);

}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XResizeWindow
 * Signbture: (JJII)V
 */

JNIEXPORT void JNICALL  Jbvb_sun_bwt_X11_XlibWrbpper_XResizeWindow
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jint width, jint height)
{
    AWT_CHECK_HAVE_LOCK();
    XResizeWindow( (Displby *) jlong_to_ptr(displby),(Window) window,width,height);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XMoveWindow
 * Signbture: (JJII)V
 */

JNIEXPORT void JNICALL  Jbvb_sun_bwt_X11_XlibWrbpper_XMoveWindow
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jint width, jint height)
{
    AWT_CHECK_HAVE_LOCK();
    XMoveWindow( (Displby *) jlong_to_ptr(displby),(Window) window,width,height);
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XSetWindowBbckground
 * Signbture: (JJJ)V
 */

JNIEXPORT void JNICALL  Jbvb_sun_bwt_X11_XlibWrbpper_XSetWindowBbckground
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong bbckground_pixel) {

    AWT_CHECK_HAVE_LOCK();
    XSetWindowBbckground((Displby *) jlong_to_ptr(displby),window,bbckground_pixel);

}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XFlush
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XFlush
(JNIEnv *env, jclbss clbzz, jlong displby) {

    AWT_CHECK_HAVE_LOCK();
    XFlush((Displby *)jlong_to_ptr(displby));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XSync
 * Signbture: (JI)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XSync
(JNIEnv *env, jclbss clbzz, jlong displby, jint discbrd) {

    AWT_CHECK_HAVE_LOCK();
    XSync((Displby *) jlong_to_ptr(displby), discbrd);

}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XTrbnslbteCoordinbtes
(JNIEnv *env, jclbss clbzz, jlong displby, jlong src_w, jlong dest_w,
 jlong src_x, jlong src_y, jlong dest_x_return, jlong dest_y_return,
 jlong child_return)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XTrbnslbteCoordinbtes( (Displby *) jlong_to_ptr(displby), src_w, dest_w,
                  src_x, src_y,
                  (int *) jlong_to_ptr(dest_x_return),
                  (int *) jlong_to_ptr(dest_y_return),
                  (Window *) jlong_to_ptr(child_return));
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XEventsQueued
(JNIEnv *env, jclbss clbzz, jlong displby, jint mode) {

    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XEventsQueued((Displby *) jlong_to_ptr(displby), mode);

}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    SetProperty
 * Signbture: (JJJLjbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_SetProperty
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong btom, jstring jstr) {
    chbr *cnbme;
    XTextProperty tp;
    int32_t stbtus;

    /*
       In cbse there bre direct support of UTF-8 declbred, use UTF-8 strings.
    */
    if (!JNU_IsNull(env, jstr)) {
#ifdef X_HAVE_UTF8_STRING
        cnbme = (chbr *) (*env)->GetStringUTFChbrs(env, jstr, JNI_FALSE);
#else
        cnbme = (chbr *) JNU_GetStringPlbtformChbrs(env, jstr, NULL);
#endif
        CHECK_NULL(cnbme);
    } else {
        cnbme = "";
    }


    AWT_CHECK_HAVE_LOCK();

#ifdef X_HAVE_UTF8_STRING
    stbtus = Xutf8TextListToTextProperty((Displby *)jlong_to_ptr(displby), &cnbme, 1,
                                       XStdICCTextStyle, &tp);
#else
    stbtus = XmbTextListToTextProperty((Displby *)jlong_to_ptr(displby), &cnbme, 1,
                                       XStdICCTextStyle, &tp);
#endif


    if (stbtus == Success || stbtus > 0) {
        XChbngeProperty((Displby *)jlong_to_ptr(displby), window, btom, tp.encoding, tp.formbt, PropModeReplbce, tp.vblue, tp.nitems);
        if (tp.vblue != NULL) {
            XFree(tp.vblue);
        }
    }

    if (!JNU_IsNull(env, jstr)) {
#ifdef X_HAVE_UTF8_STRING
        (*env)->RelebseStringUTFChbrs(env, jstr, (const chbr *) cnbme);
#else
        JNU_RelebseStringPlbtformChbrs(env, jstr, (const chbr *) cnbme);
#endif
    }
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XChbngeProperty
 * Signbture: (JJJJJJJJJJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XChbngePropertyImpl(
    JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong property,
    jlong type, jint formbt, jint mode, jlong dbtb, jint nelements)
{
    AWT_CHECK_HAVE_LOCK();
    XChbngeProperty((Displby*) jlong_to_ptr(displby), (Window) window, (Atom) property,
            (Atom) type, formbt, mode, (unsigned chbr*) jlong_to_ptr(dbtb),
            nelements);
}
/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XChbngePropertyS
 * Signbture: (JJJJJJJJJLjbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XChbngePropertyS(
    JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong property,
    jlong type, jint formbt, jint mode, jstring vblue)
{
    jboolebn iscopy;
    AWT_CHECK_HAVE_LOCK();
    const chbr * chbrs = JNU_GetStringPlbtformChbrs(env, vblue, &iscopy);
    CHECK_NULL(chbrs);
    XChbngeProperty((Displby*)jlong_to_ptr(displby), window, (Atom)property,
                    (Atom)type, formbt, mode, (unsigned chbr*)chbrs, strlen(chbrs));
    if (iscopy) {
        JNU_RelebseStringPlbtformChbrs(env, vblue, chbrs);
    }
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XGetWindowProperty
 * Signbture: (JJJJJJJJJJJ)J;
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XGetWindowProperty
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong property, jlong long_offset,
 jlong long_length, jlong delete, jlong req_type, jlong bctubl_type,
 jlong bctubl_formbt, jlong nitems_ptr, jlong bytes_bfter, jlong dbtb_ptr)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XGetWindowProperty((Displby*) jlong_to_ptr(displby), window, property, long_offset, long_length,
                  delete, (Atom) req_type, (Atom*) jlong_to_ptr(bctubl_type),
                  (int *) jlong_to_ptr(bctubl_formbt), (unsigned long *) jlong_to_ptr(nitems_ptr),
                  (unsigned long*) jlong_to_ptr(bytes_bfter), (unsigned chbr**) jlong_to_ptr(dbtb_ptr));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    GetProperty
 * Signbture: (JJJ)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_GetProperty
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong btom)
{
    /* Request stbtus */
    int stbtus;

    /* Returns of XGetWindowProperty */
    Atom bctubl_type;
    int bctubl_formbt;
    unsigned long nitems;
    unsigned long bytes_bfter;
    unsigned chbr * string;
    jstring res = NULL;
    AWT_CHECK_HAVE_LOCK_RETURN(NULL);
    stbtus = XGetWindowProperty((Displby*)jlong_to_ptr(displby), window,
                                btom, 0, 0xFFFF, Fblse, XA_STRING,
                                &bctubl_type, &bctubl_formbt, &nitems, &bytes_bfter,
                                &string);

    if (stbtus != Success || string == NULL) {
        return NULL;
    }

    if (bctubl_type == XA_STRING && bctubl_formbt == 8) {
        res = JNU_NewStringPlbtform(env,(chbr*) string);
    }
    XFree(string);
    return res;
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    InternAtom
 * Signbture: (JLjbvb/lbng/String;I)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_InternAtom
(JNIEnv *env, jclbss clbzz, jlong displby, jstring jstr, jint ife) {

    chbr *cnbme;
    unsigned long btom;

    AWT_CHECK_HAVE_LOCK_RETURN(0);

    if (!JNU_IsNull(env, jstr)) {
        cnbme = (chbr *)JNU_GetStringPlbtformChbrs(env, jstr, NULL);
        CHECK_NULL_RETURN(cnbme, 0);
    } else {
        cnbme = "";
    }

    btom = XInternAtom((Displby *) jlong_to_ptr(displby), cnbme, ife);

    if (!JNU_IsNull(env, jstr)) {
        JNU_RelebseStringPlbtformChbrs(env, jstr, (const chbr *) cnbme);
    }

    return (jlong) btom;

}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XCrebteFontCursor
(JNIEnv *env, jclbss clbzz, jlong displby, jint shbpe) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XCrebteFontCursor((Displby *) jlong_to_ptr(displby), (int) shbpe);
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XCrebtePixmbpCursor
 * Signbture: (JJJJJII)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XCrebtePixmbpCursor
(JNIEnv *env , jclbss clbzz, jlong displby, jlong source, jlong mbsk, jlong fore, jlong bbck, jint x , jint y) {

    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return (jlong) XCrebtePixmbpCursor((Displby *) jlong_to_ptr(displby), (Pixmbp) source, (Pixmbp) mbsk,
                                       (XColor *) jlong_to_ptr(fore), (XColor *) jlong_to_ptr(bbck), x, y);
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XQueryBestCursor
 * Signbture: (JJIIJJ)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XQueryBestCursor
(JNIEnv *env, jclbss clbzz, jlong displby, jlong drbwbble, jint width, jint height, jlong width_return, jlong height_return) {

    Stbtus stbtus;

    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    stbtus  =  XQueryBestCursor((Displby *) jlong_to_ptr(displby), (Drbwbble) drbwbble, width,height,
                                (unsigned int *) jlong_to_ptr(width_return), (unsigned int *) jlong_to_ptr(height_return));

    if (stbtus == 0) return JNI_FALSE;
    else return JNI_TRUE;
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XFreeCursor
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XFreeCursor
(JNIEnv *env, jclbss clbzz, jlong displby, jlong cursor) {

    AWT_CHECK_HAVE_LOCK();
    XFreeCursor( (Displby *) jlong_to_ptr(displby), (Cursor) cursor);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XQueryPointer
 * Signbture: (JJJJJJJJJ)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XQueryPointer
(JNIEnv *env, jclbss clbzz, jlong displby, jlong w, jlong root_return, jlong child_return, jlong root_x_return , jlong root_y_return, jlong win_x_return, jlong win_y_return, jlong mbsk_return) {

    Bool b;

    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    b = XQueryPointer((Displby *) jlong_to_ptr(displby),
                      (Window) w, (Window *) jlong_to_ptr(root_return), (Window *) jlong_to_ptr(child_return),
                      (int *) jlong_to_ptr(root_x_return), (int *) jlong_to_ptr(root_y_return),
                      (int *) jlong_to_ptr(win_x_return), (int *) jlong_to_ptr(win_y_return),
                      (unsigned int *) jlong_to_ptr(mbsk_return));

    return b ? JNI_TRUE : JNI_FALSE;
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XChbngeWindowAttributes
 * Signbture: (JJJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XChbngeWindowAttributes
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong vbluembsk, jlong bttributes) {

    AWT_CHECK_HAVE_LOCK();
    XChbngeWindowAttributes((Displby *) jlong_to_ptr(displby), (Window) window, (unsigned long) vbluembsk,
                            (XSetWindowAttributes *) jlong_to_ptr(bttributes));
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XSetTrbnsientFor
 * Signbture: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XSetTrbnsientFor
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong trbnsient_for_window)
{
    AWT_CHECK_HAVE_LOCK();
    XSetTrbnsientForHint((Displby *) jlong_to_ptr(displby), window, trbnsient_for_window);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XSetWMHints
 * Signbture: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XSetWMHints
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong hints)
{
    AWT_CHECK_HAVE_LOCK();
    XSetWMHints((Displby *) jlong_to_ptr(displby), window, (XWMHints *) jlong_to_ptr(hints));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XGetWMHints
 * Signbture: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XGetWMHints
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong hints)
{
    XWMHints * get_hints;
    AWT_CHECK_HAVE_LOCK();
    get_hints = XGetWMHints((Displby*)jlong_to_ptr(displby), window);
    if (get_hints != NULL) {
        memcpy(jlong_to_ptr(hints), get_hints, sizeof(XWMHints));
        XFree(get_hints);
    } else {
        memset(jlong_to_ptr(hints), 0, sizeof(XWMHints));
    }
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XGetPointerMbpping
 * Signbture: (JJI)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XGetPointerMbpping
(JNIEnv *env, jclbss clbzz, jlong displby, jlong mbp, jint buttonNumber)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XGetPointerMbpping((Displby*)jlong_to_ptr(displby), (unsigned chbr*) jlong_to_ptr(mbp), buttonNumber);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XGetDefbult
 * Signbture: (JJI)I
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XGetDefbult
(JNIEnv *env, jclbss clbzz, jlong displby, jstring progrbm, jstring option)
{
    chbr * c_progrbm = NULL;
    chbr * c_option = NULL;
    chbr * c_res = NULL;

    if (!JNU_IsNull(env, progrbm)) {
        c_progrbm = (chbr *)JNU_GetStringPlbtformChbrs(env, progrbm, NULL);
    }
    CHECK_NULL_RETURN(c_progrbm, NULL);

    if (!JNU_IsNull(env, option)) {
        c_option = (chbr *)JNU_GetStringPlbtformChbrs(env, option, NULL);
    }

    if (c_option == NULL) {
        JNU_RelebseStringPlbtformChbrs(env, progrbm, (const chbr *) c_progrbm);
        return NULL;
    }

    AWT_CHECK_HAVE_LOCK_RETURN(NULL);
    c_res = XGetDefbult((Displby*)jlong_to_ptr(displby), c_progrbm, c_option);
    // The strings returned by XGetDefbult() bre owned by Xlib bnd
    // should not be modified or freed by the client.

    JNU_RelebseStringPlbtformChbrs(env, progrbm, (const chbr *) c_progrbm);
    JNU_RelebseStringPlbtformChbrs(env, option, (const chbr *) c_option);

    if (c_res != NULL) {
        return JNU_NewStringPlbtform(env, c_res);
    } else {
        return NULL;
    }
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    getScreenOfWindow
 * Signbture: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_getScreenOfWindow
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window)
{
    XWindowAttributes bttrs;
    memset(&bttrs, 0, sizeof(bttrs));
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    XGetWindowAttributes((Displby *) jlong_to_ptr(displby), window, &bttrs);
    return ptr_to_jlong(bttrs.screen);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XScreenNumberOfScreen
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XScreenNumberOfScreen
(JNIEnv *env, jclbss clbzz, jlong screen)
{
    AWT_CHECK_HAVE_LOCK_RETURN(-1);
    if(jlong_to_ptr(screen) == NULL) {
        return -1;
    }
    return XScreenNumberOfScreen((Screen*) jlong_to_ptr(screen));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XIconifyWindow
 * Signbture: (JJJ)V
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XIconifyWindow
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong screenNumber)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XIconifyWindow((Displby*) jlong_to_ptr(displby), window, screenNumber);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XFree
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XFree
(JNIEnv *env, jclbss clbzz, jlong ptr)
{
    AWT_CHECK_HAVE_LOCK();
    XFree(jlong_to_ptr(ptr));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XFree
 * Signbture: (J)V
 */
JNIEXPORT jbyteArrby JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_getStringBytes
(JNIEnv *env, jclbss clbzz, jlong str_ptr)
{
    unsigned chbr * str = (unsigned chbr*) jlong_to_ptr(str_ptr);
    long length = strlen((chbr*)str);
    jbyteArrby res = (*env)->NewByteArrby(env, length);
    CHECK_NULL_RETURN(res, NULL);
    (*env)->SetByteArrbyRegion(env, res, 0, length,
                   (const signed chbr*) str);
    return res;
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    ServerVendor
 * Signbture: (J)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_ServerVendor
(JNIEnv *env, jclbss clbzz, jlong displby)
{
    AWT_CHECK_HAVE_LOCK_RETURN(NULL);
    return JNU_NewStringPlbtform(env, ServerVendor((Displby*)jlong_to_ptr(displby)));
}
/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    VendorRelebse
 * Signbture: (J)I;
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_VendorRelebse
(JNIEnv *env, jclbss clbzz, jlong displby)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return VendorRelebse((Displby*)jlong_to_ptr(displby));
}
/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    IsXsunKPBehbvior
 * Signbture: (J)Z;
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_IsXsunKPBehbvior
(JNIEnv *env, jclbss clbzz, jlong displby)
{
    // Xsun without XKB uses keysymbrrby[2] keysym to determine if it is KP event.
    // Otherwise, it is [1] or sometimes [0].
    // This sniffer first tries to determine whbt is b keycode for XK_KP_7
    // using XKeysymToKeycode;
    // second, in which plbce in the keysymbrrby is XK_KP_7
    // using XKeycodeToKeysym.
    int kc7;
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    kc7 = XKeysymToKeycode((Displby*)jlong_to_ptr(displby), XK_KP_7);
    if( !kc7 ) {
        // keycode is not defined. Why, it's b reduced keybobrd perhbps:
        // report brbitrbrily fblse.
        return JNI_FALSE;
    } else {
        long ks2 = XKeycodeToKeysym((Displby*)jlong_to_ptr(displby), kc7, 2);
        if( ks2 == XK_KP_7 ) {
            //XXX If some Xorg server would put XK_KP_7 in keysymbrrby[2] bs well,
            //XXX for yet unknown to me rebson, the sniffer would lie.
            return JNI_TRUE;
        }else{
            return JNI_FALSE;
        }
    }
}


JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_IsSunKeybobrd
(JNIEnv *env, jclbss clbzz, jlong displby)
{
    int xx;
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    xx = XKeysymToKeycode((Displby*)jlong_to_ptr(displby), SunXK_F37);
    return (!xx) ? JNI_FALSE : JNI_TRUE;
}

JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_IsKbnbKeybobrd
(JNIEnv *env, jclbss clbzz, jlong displby)
{
    int xx;
    stbtic jboolebn result = JNI_FALSE;

    int32_t minKeyCode, mbxKeyCode, keySymsPerKeyCode;
    KeySym *keySyms, *keySymsStbrt, keySym;
    int32_t i;
    int32_t kbnbCount = 0;

    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);

    // There's no direct wby to determine whether the keybobrd hbs
    // b kbnb lock key. From bvbilbble keybobrd mbpping tbbles, it looks
    // like only keybobrds with the kbnb lock key cbn produce keysyms
    // for kbnb chbrbcters. So, bs bn indirect test, we check for those.
    XDisplbyKeycodes((Displby*)jlong_to_ptr(displby), &minKeyCode, &mbxKeyCode);
    keySyms = XGetKeybobrdMbpping((Displby*)jlong_to_ptr(displby), minKeyCode, mbxKeyCode - minKeyCode + 1, &keySymsPerKeyCode);
    keySymsStbrt = keySyms;
    for (i = 0; i < (mbxKeyCode - minKeyCode + 1) * keySymsPerKeyCode; i++) {
        keySym = *keySyms++;
        if ((keySym & 0xff00) == 0x0400) {
            kbnbCount++;
        }
    }
    XFree(keySymsStbrt);

    // use b (somewhbt brbitrbry) minimum so we don't get confused by b strby function key
    result = kbnbCount > 10;
    return result ? JNI_TRUE : JNI_FALSE;
}

JbvbVM* jvm = NULL;
stbtic int ToolkitErrorHbndler(Displby * dpy, XErrorEvent * event) {
    JNIEnv * env;
    // First cbll the nbtive synthetic error hbndler declbred in "bwt_util.h" file.
    if (current_nbtive_xerror_hbndler != NULL) {
        current_nbtive_xerror_hbndler(dpy, event);
    }
    if (jvm != NULL) {
        env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        if (env) {
            return JNU_CbllStbticMethodByNbme(env, NULL, "sun/bwt/X11/XErrorHbndlerUtil",
                "globblErrorHbndler", "(JJ)I", ptr_to_jlong(dpy), ptr_to_jlong(event)).i;
        }
    }
    return 0;
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    SetToolkitErrorHbndler
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_SetToolkitErrorHbndler
(JNIEnv *env, jclbss clbzz)
{
    if ((*env)->GetJbvbVM(env, &jvm) < 0) {
        return 0;
    }
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return ptr_to_jlong(XSetErrorHbndler(ToolkitErrorHbndler));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XSetErrorHbndler
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XSetErrorHbndler
(JNIEnv *env, jclbss clbzz, jlong hbndler)
{
    AWT_CHECK_HAVE_LOCK();
    XSetErrorHbndler((XErrorHbndler) jlong_to_ptr(hbndler));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    CbllErrorHbndler
 * Signbture: (JJJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_CbllErrorHbndler
(JNIEnv *env, jclbss clbzz, jlong hbndler, jlong displby, jlong event_ptr)
{
    return (*(XErrorHbndler)jlong_to_ptr(hbndler))((Displby*) jlong_to_ptr(displby), (XErrorEvent*) jlong_to_ptr(event_ptr));
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    PrintXErrorEvent
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_PrintXErrorEvent
(JNIEnv *env, jclbss clbzz, jlong displby, jlong event_ptr)
{
    chbr msg[128];
    chbr buf[128];

    XErrorEvent* err = (XErrorEvent *)jlong_to_ptr(event_ptr);

    XGetErrorText((Displby *)jlong_to_ptr(displby), err->error_code, msg, sizeof(msg));
    jio_fprintf(stderr, "Xerror %s, XID %x, ser# %d\n", msg, err->resourceid, err->seribl);
    jio_snprintf(buf, sizeof(buf), "%d", err->request_code);
    XGetErrorDbtbbbseText((Displby *)jlong_to_ptr(displby), "XRequest", buf, "Unknown", msg, sizeof(msg));
    jio_fprintf(stderr, "Mbjor opcode %d (%s)\n", err->request_code, msg);
    if (err->request_code > 128) {
        jio_fprintf(stderr, "Minor opcode %d\n", err->minor_code);
    }
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XInternAtoms
 * Signbture: (J[Ljbvb/lbng/String;ZJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XInternAtoms
(JNIEnv *env, jclbss clbzz, jlong displby, jobjectArrby nbmes_brr, jboolebn only_if_exists, jlong btoms)
{
    int stbtus = 0;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    jsize length;
    chbr** nbmes = stringArrbyToNbtive(env, nbmes_brr, &length);
    if (nbmes) {
        stbtus = XInternAtoms((Displby*)jlong_to_ptr(displby), nbmes, length, only_if_exists, (Atom*) jlong_to_ptr(btoms));
        freeNbtiveStringArrby(nbmes, length);
    }
    return stbtus;
}



/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XGetWindowAttributes
 * Signbture: (JJJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XGetWindowAttributes
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong bttr_ptr)
{
    jint stbtus;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    memset((XWindowAttributes*) jlong_to_ptr(bttr_ptr), 0, sizeof(XWindowAttributes));
    stbtus =  XGetWindowAttributes((Displby*)jlong_to_ptr(displby), window, (XWindowAttributes*) jlong_to_ptr(bttr_ptr));
    return stbtus;
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XGetGeometry
 * Signbture: (JJJJJJJJJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XGetGeometry
(JNIEnv *env, jclbss clbzz, jlong displby, jlong drbwbble, jlong root_return,
     jlong x_return, jlong y_return, jlong width_return, jlong height_return,
     jlong border_width_return, jlong depth_return)
{
    jint stbtus;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    stbtus = XGetGeometry((Displby *)jlong_to_ptr(displby),
                          (Drbwbble)drbwbble, (Window *)jlong_to_ptr(root_return),
                          (int *)jlong_to_ptr(x_return), (int *)jlong_to_ptr(y_return),
                          (unsigned int *)jlong_to_ptr(width_return), (unsigned int *)jlong_to_ptr(height_return),
                          (unsigned int *)jlong_to_ptr(border_width_return),
                          (unsigned int *)jlong_to_ptr(depth_return));
    return stbtus;
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XGetWMNormblHints
 * Signbture: (JJJJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XGetWMNormblHints
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong hints, jlong supplied_return)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XGetWMNormblHints((Displby*) jlong_to_ptr(displby),
                             window,
                             (XSizeHints*) jlong_to_ptr(hints),
                             (long*) jlong_to_ptr(supplied_return));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XSetWMNormblHints
 * Signbture: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XSetWMNormblHints
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong hints)
{
    AWT_CHECK_HAVE_LOCK();
    XSetWMNormblHints((Displby*) jlong_to_ptr(displby), window, (XSizeHints*) jlong_to_ptr(hints));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XDeleteProperty
 * Signbture: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XDeleteProperty
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong btom)
{
    AWT_CHECK_HAVE_LOCK();
    XDeleteProperty((Displby*) jlong_to_ptr(displby), window, (Atom)btom);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XSendEvent
 * Signbture: (JJZJJ)V
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XSendEvent
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jboolebn propbgbte, jlong event_mbsk, jlong event)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XSendEvent((Displby*) jlong_to_ptr(displby),
                      window,
                      propbgbte==JNI_TRUE?True:Fblse,
                      (long) event_mbsk,
                      (XEvent*) jlong_to_ptr(event));
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XQueryTree
 * Signbture: (JJJJJJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XQueryTree
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong root_return, jlong pbrent_return, jlong children_return, jlong nchildren_return)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XQueryTree((Displby*) jlong_to_ptr(displby),
                      window,
                      (Window *) jlong_to_ptr(root_return),
                      (Window*) jlong_to_ptr(pbrent_return),
                      (Window**) jlong_to_ptr(children_return),
                      (unsigned int*) jlong_to_ptr(nchildren_return));
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    memcpy
 * Signbture: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_memcpy
(JNIEnv *env, jclbss clbzz, jlong dest_ptr, jlong src_ptr, jlong length)
{
    memcpy(jlong_to_ptr(dest_ptr), jlong_to_ptr(src_ptr), length);
}


JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XSetMinMbxHints
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jint x, jint y, jint width, jint height, jlong flbgs) {
    XSizeHints * hints;
    AWT_CHECK_HAVE_LOCK();
    hints = XAllocSizeHints();
    hints->flbgs = flbgs;
    hints->width = width;
    hints->min_width = width;
    hints->mbx_width = width;
    hints->height = height;
    hints->min_height = height;
    hints->mbx_height = height;
    hints->x = x;
    hints->y = y;
    XSetWMNormblHints((Displby*) jlong_to_ptr(displby), window, hints);
    XFree(hints);
}


JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XGetVisublInfo
(JNIEnv *env, jclbss clbzz, jlong displby, jlong vinfo_mbsk, jlong vinfo_templbte,
 jlong nitems_return)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return ptr_to_jlong(XGetVisublInfo((Displby*) jlong_to_ptr(displby),
                                       (long) vinfo_mbsk,
                                       (XVisublInfo*) jlong_to_ptr(vinfo_templbte),
                                       (int*) jlong_to_ptr(nitems_return)));
}

JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XAllocSizeHints
  (JNIEnv *env, jclbss clbzz)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return ptr_to_jlong(XAllocSizeHints());
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XIconifyWindow
 * Signbture: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XBell
(JNIEnv *env, jclbss clbzz, jlong displby, jint percent)
{
    AWT_CHECK_HAVE_LOCK();
    XBell((Displby*)jlong_to_ptr(displby), percent);
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XAllocColor
 * Signbture: (JJJ)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XAllocColor
(JNIEnv *env, jclbss clbzz, jlong displby , jlong colormbp, jlong xcolor) {

    Stbtus stbtus;
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    stbtus = XAllocColor((Displby *) jlong_to_ptr(displby), (Colormbp) colormbp, (XColor *) jlong_to_ptr(xcolor));

    if (stbtus == 0) return JNI_FALSE;
    else return JNI_TRUE;
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XCrebteBitmbpFromDbtb
 * Signbture: (JJJII)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XCrebteBitmbpFromDbtb
(JNIEnv *env, jclbss clbzz, jlong displby, jlong drbwbble, jlong dbtb, jint width, jint height) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);

    return (jlong) XCrebteBitmbpFromDbtb((Displby *) jlong_to_ptr(displby), (Drbwbble) drbwbble,
                                         (chbr *) jlong_to_ptr(dbtb), width, height);
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XFreePixmbp
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XFreePixmbp
(JNIEnv *env, jclbss clbzz, jlong displby, jlong pixmbp) {
    AWT_CHECK_HAVE_LOCK();
    XFreePixmbp((Displby *)jlong_to_ptr(displby), (Pixmbp) pixmbp);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XRepbrentWindow
 * Signbture: (JJJII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XRepbrentWindow
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong pbrent, jint x, jint y) {
    AWT_CHECK_HAVE_LOCK();
    XRepbrentWindow((Displby*)jlong_to_ptr(displby), window, pbrent, x, y);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XConvertSelection
 * Signbture: (JJJJJJ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XConvertSelection(JNIEnv *env, jclbss clbzz,
                           jlong displby, jlong selection,
                           jlong tbrget, jlong property,
                           jlong requestor, jlong time) {
    AWT_CHECK_HAVE_LOCK();
    XConvertSelection((Displby*)jlong_to_ptr(displby), selection, tbrget, property, requestor,
              time);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XSetSelectionOwner
 * Signbture: (JJJJ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XSetSelectionOwner(JNIEnv *env, jclbss clbzz,
                        jlong displby, jlong selection,
                        jlong owner, jlong time) {
    AWT_CHECK_HAVE_LOCK();
    XSetSelectionOwner((Displby*)jlong_to_ptr(displby), selection, owner, time);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XGetSelectionOwner
 * Signbture: (JJ)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XGetSelectionOwner(JNIEnv *env, jclbss clbzz,
                        jlong displby, jlong selection) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return (jlong)XGetSelectionOwner((Displby*)jlong_to_ptr(displby), selection);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XGetAtomNbme
 * Signbture: (JJ)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XGetAtomNbme(JNIEnv *env, jclbss clbzz,
                      jlong displby, jlong btom)
{
    jstring string = NULL;
    chbr* nbme;
    AWT_CHECK_HAVE_LOCK_RETURN(NULL);
    nbme = (chbr*) XGetAtomNbme((Displby*)jlong_to_ptr(displby), btom);

    if (nbme == NULL) {
        fprintf(stderr, "Atom wbs %d\n", (int)btom);
        JNU_ThrowNullPointerException(env, "Fbiled to retrieve btom nbme.");
        return NULL;
    }

    string = (*env)->NewStringUTF(env, (const chbr *)nbme);

    XFree(nbme);

    return string;
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XMbxRequestSize
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XMbxRequestSize(JNIEnv *env, jclbss clbzz,
                         jlong displby) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XMbxRequestSize((Displby*) jlong_to_ptr(displby));
}

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XAllocWMHints(JNIEnv *env, jclbss clbzz)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return ptr_to_jlong(XAllocWMHints());
}

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XCrebtePixmbp(JNIEnv *env, jclbss clbzz, jlong displby, jlong drbwbble, jint width, jint height, jint depth)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XCrebtePixmbp((Displby*)jlong_to_ptr(displby), (Drbwbble)drbwbble, width, height, depth);
}
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XCrebteImbge
  (JNIEnv *env, jclbss clbzz, jlong displby, jlong visubl_ptr,
   jint depth, jint formbt, jint offset, jlong dbtb, jint width,
   jint height, jint bitmbp_pbd, jint bytes_per_line)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return ptr_to_jlong(XCrebteImbge((Displby*) jlong_to_ptr(displby), (Visubl*) jlong_to_ptr(visubl_ptr),
                depth, formbt, offset, (chbr*) jlong_to_ptr(dbtb),
                width, height, bitmbp_pbd, bytes_per_line));
}
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XCrebteGC
  (JNIEnv *env, jclbss clbzz, jlong displby, jlong drbwbble,
   jlong vbluembsk, jlong vblues)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return ptr_to_jlong(XCrebteGC((Displby*) jlong_to_ptr(displby), (Drbwbble)drbwbble, vbluembsk, (XGCVblues*) jlong_to_ptr(vblues)));
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XDestroyImbge(JNIEnv *env, jclbss clbzz, jlong imbge)
{
    XImbge *img = (XImbge*) jlong_to_ptr(imbge);
    AWT_CHECK_HAVE_LOCK();

    // Fix for bug 4903671 :
    // We should be cbreful to not double free the memory pointed to dbtb
    // Since we use unsbfe to bllocbte it, we should use unsbfe to free it.
    // So we should NULL the dbtb pointer before cblling XDestroyImbge so
    // thbt X does not free the pointer for us.
    img->dbtb = NULL;
    XDestroyImbge(img);
}
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XPutImbge(JNIEnv *env, jclbss clbzz, jlong displby, jlong drbwbble, jlong gc, jlong imbge, jint src_x, jint src_y, jint dest_x, jint dest_y, jint width, jint height)
{
    AWT_CHECK_HAVE_LOCK();
    XPutImbge((Displby*)jlong_to_ptr(displby), (Drbwbble)drbwbble, (GC) jlong_to_ptr(gc), (XImbge*) jlong_to_ptr(imbge), src_x, src_y,
              dest_x, dest_y, width, height);
}
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XFreeGC(JNIEnv *env, jclbss clbzz, jlong displby, jlong gc)
{
    AWT_CHECK_HAVE_LOCK();
    XFreeGC((Displby*) jlong_to_ptr(displby), (GC) jlong_to_ptr(gc));
}
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XSetWindowBbckgroundPixmbp(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong pixmbp)
{
    AWT_CHECK_HAVE_LOCK();
    XSetWindowBbckgroundPixmbp((Displby*) jlong_to_ptr(displby), (Window)window, (Pixmbp)pixmbp);
}
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XClebrWindow(JNIEnv *env, jclbss clbzz, jlong displby, jlong window)
{
    AWT_CHECK_HAVE_LOCK();
    XClebrWindow((Displby*) jlong_to_ptr(displby), (Window)window);
}

JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XGetIconSizes(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong ret_sizes, jlong ret_count)
{
    XIconSize** psize = (XIconSize**) jlong_to_ptr(ret_sizes);
    int * pcount = (int *) jlong_to_ptr(ret_count);
    Stbtus res;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    res = XGetIconSizes((Displby*) jlong_to_ptr(displby), (Window)window, psize, pcount);
    return res;
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XdbeQueryExtension
  (JNIEnv *env, jclbss clbzz, jlong displby, jlong mbjor_version_return,
   jlong minor_version_return)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XdbeQueryExtension((Displby*) jlong_to_ptr(displby), (int *) jlong_to_ptr(mbjor_version_return),
                  (int *) jlong_to_ptr(minor_version_return));
}

JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XQueryExtension
  (JNIEnv *env, jclbss clbzz, jlong displby, jstring jstr, jlong mop_return,
   jlong feve_return, jlong err_return)
{
    chbr *cnbme;
    Boolebn bu;
    if (!JNU_IsNull(env, jstr)) {
        cnbme = (chbr *)JNU_GetStringPlbtformChbrs(env, jstr, NULL);
        CHECK_NULL_RETURN(cnbme, JNI_FALSE);
    } else {
        cnbme = "";
    }

    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    bu = XQueryExtension((Displby*) jlong_to_ptr(displby), cnbme, (int *) jlong_to_ptr(mop_return),
                (int *) jlong_to_ptr(feve_return),  (int *) jlong_to_ptr(err_return));
    if (!JNU_IsNull(env, jstr)) {
        JNU_RelebseStringPlbtformChbrs(env, jstr, (const chbr *) cnbme);
    }
    return bu ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_IsKeypbdKey
  (JNIEnv *env, jclbss clbzz, jlong keysym)
{
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    if(IsKeypbdKey(keysym)) {
        return JNI_TRUE;
    }
    return JNI_FALSE;
}

JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XdbeAllocbteBbckBufferNbme
  (JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jint swbp_bction)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XdbeAllocbteBbckBufferNbme((Displby*) jlong_to_ptr(displby), (Window) window,
                      (XdbeSwbpAction) swbp_bction);
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XdbeDebllocbteBbckBufferNbme
  (JNIEnv *env, jclbss clbzz, jlong displby, jlong buffer)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XdbeDebllocbteBbckBufferNbme((Displby*) jlong_to_ptr(displby), (XdbeBbckBuffer) buffer);
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XdbeBeginIdiom
  (JNIEnv *env, jclbss clbzz, jlong displby)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XdbeBeginIdiom((Displby*) jlong_to_ptr(displby));
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XdbeEndIdiom
  (JNIEnv *env, jclbss clbzz, jlong displby)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XdbeEndIdiom((Displby*) jlong_to_ptr(displby));
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XdbeSwbpBuffers
  (JNIEnv *env, jclbss clbzz, jlong displby, jlong swbp_info, jint num_windows)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XdbeSwbpBuffers((Displby*) jlong_to_ptr(displby), (XdbeSwbpInfo *) jlong_to_ptr(swbp_info), num_windows);
}
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XQueryKeymbp
(JNIEnv *env, jclbss clbzz, jlong displby, jlong vector)
{

    AWT_CHECK_HAVE_LOCK();
    XQueryKeymbp( (Displby *) jlong_to_ptr(displby), (chbr *) jlong_to_ptr(vector));
}

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XKeycodeToKeysym(JNIEnv *env, jclbss clbzz,
                                              jlong displby, jint keycode,
                                              jint index) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XKeycodeToKeysym((Displby*) jlong_to_ptr(displby), (unsigned int)keycode, (int)index);
}

JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XkbGetEffectiveGroup(JNIEnv *env, jclbss clbzz,
                                              jlong displby) {
    XkbStbteRec sr;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    memset(&sr, 0, sizeof(XkbStbteRec));
    XkbGetStbte((Displby*) jlong_to_ptr(displby), XkbUseCoreKbd, &sr);
//    printf("-------------------------------------VVVV\n");
//    printf("                 group:0x%0X\n",sr.group);
//    printf("            bbse_group:0x%0X\n",sr.bbse_group);
//    printf("         lbtched_group:0x%0X\n",sr.lbtched_group);
//    printf("          locked_group:0x%0X\n",sr.locked_group);
//    printf("                  mods:0x%0X\n",sr.mods);
//    printf("             bbse_mods:0x%0X\n",sr.bbse_mods);
//    printf("          lbtched_mods:0x%0X\n",sr.lbtched_mods);
//    printf("           locked_mods:0x%0X\n",sr.locked_mods);
//    printf("          compbt_stbte:0x%0X\n",sr.compbt_stbte);
//    printf("             grbb_mods:0x%0X\n",sr.grbb_mods);
//    printf("      compbt_grbb_mods:0x%0X\n",sr.compbt_grbb_mods);
//    printf("           lookup_mods:0x%0X\n",sr.lookup_mods);
//    printf("    compbt_lookup_mods:0x%0X\n",sr.compbt_lookup_mods);
//    printf("           ptr_buttons:0x%0X\n",sr.ptr_buttons);
//    printf("-------------------------------------^^^^\n");
    return (jint)(sr.group);
}
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XkbKeycodeToKeysym(JNIEnv *env, jclbss clbzz,
                                              jlong displby, jint keycode,
                                              jint group, jint level) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XkbKeycodeToKeysym((Displby*) jlong_to_ptr(displby), (unsigned int)keycode, (unsigned int)group, (unsigned int)level);
}

JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XKeysymToKeycode(JNIEnv *env, jclbss clbzz,
                                              jlong displby, jlong keysym) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return XKeysymToKeycode((Displby*) jlong_to_ptr(displby), (KeySym)keysym);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XGetModifierMbpping(JNIEnv *env, jclbss clbzz,
                                              jlong displby) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    return ptr_to_jlong(XGetModifierMbpping((Displby*) jlong_to_ptr(displby)));
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XFreeModifiermbp(JNIEnv *env, jclbss clbzz,
                                              jlong keymbp) {
    AWT_CHECK_HAVE_LOCK();
    XFreeModifiermbp((XModifierKeymbp*) jlong_to_ptr(keymbp));
}
/*
 * Clbss:     sun_bwt_X11_XlibWrbpper
 * Method:    XRefreshKeybobrdMbpping
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbpper_XRefreshKeybobrdMbpping
(JNIEnv *env, jclbss clbzz, jlong event_ptr)
{
    AWT_CHECK_HAVE_LOCK();
    XRefreshKeybobrdMbpping((XMbppingEvent*) jlong_to_ptr(event_ptr));
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XChbngeActivePointerGrbb(JNIEnv *env, jclbss clbzz,
                                                      jlong displby, jint mbsk,
                                                      jlong cursor, jlong time) {
    AWT_CHECK_HAVE_LOCK();
    XChbngeActivePointerGrbb((Displby*)jlong_to_ptr(displby), (unsigned int)mbsk,
                             (Cursor)cursor, (Time)time);
}

/******************* Secondbry loop support ************************************/
#define AWT_SECONDARY_LOOP_TIMEOUT 250

stbtic Bool exitSecondbryLoop = True;

/*
 * This predicbte procedure bllows the Toolkit threbd to process specific events
 * while it is blocked wbiting for the event dispbtch threbd to process
 * b SunDropTbrgetEvent. We need this to prevent debdlock when the client code
 * processing SunDropTbrgetEvent sets or gets the contents of the system
 * clipbobrd/selection. In this cbse the event dispbtch threbd wbits for the
 * Toolkit threbd to process PropertyNotify or SelectionNotify events.
 */
stbtic Bool
secondbry_loop_event(Displby* dpy, XEvent* event, chbr* brg) {
    return (event->type == SelectionNotify ||
            event->type == SelectionClebr  ||
            event->type == PropertyNotify) ? True : Fblse;
}


JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XNextSecondbryLoopEvent(JNIEnv *env, jclbss clbzz,
                                                     jlong displby, jlong ptr) {
    uint32_t timeout = 1;

    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    exitSecondbryLoop = Fblse;
    while (!exitSecondbryLoop) {
        if (XCheckIfEvent((Displby*) jlong_to_ptr(displby), (XEvent*) jlong_to_ptr(ptr), secondbry_loop_event, NULL)) {
            return JNI_TRUE;
        }
        timeout = (timeout < AWT_SECONDARY_LOOP_TIMEOUT) ? (timeout << 1) : AWT_SECONDARY_LOOP_TIMEOUT;
        AWT_WAIT(timeout);
    }
    return JNI_FALSE;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_ExitSecondbryLoop(JNIEnv *env, jclbss clbzz) {
    DASSERT(!exitSecondbryLoop);
    AWT_CHECK_HAVE_LOCK();
    exitSecondbryLoop = True;
    AWT_NOTIFY_ALL();
}
/*******************************************************************************/

JNIEXPORT jobjectArrby JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XTextPropertyToStringList(JNIEnv *env,
                                                       jclbss clbzz,
                                                       jbyteArrby bytes,
                                                       jlong encodingAtom) {
    XTextProperty tp;
    jbyte         *vblue;

    chbr**        strings  = (chbr **)NULL;
    int32_t       nstrings = 0;
    jobjectArrby  ret = NULL;
    int32_t       i;
    jsize         len;
    jboolebn      isCopy = JNI_FALSE;
    stbtic jclbss stringClbss = NULL;
    jclbss        stringClbssLocbl = NULL;

    AWT_CHECK_HAVE_LOCK_RETURN(NULL);

    if (JNU_IsNull(env, stringClbss)) {
        stringClbssLocbl = (*env)->FindClbss(env, "jbvb/lbng/String");

        if ((*env)->ExceptionCheck(env)) {
            (*env)->ExceptionDescribe(env);
            (*env)->ExceptionClebr(env);
            DASSERT(Fblse);
        }

        if (JNU_IsNull(env, stringClbssLocbl)) {
            return NULL;
        }

        stringClbss = (*env)->NewGlobblRef(env, stringClbssLocbl); /* never freed! */
        (*env)->DeleteLocblRef(env, stringClbssLocbl);

        if (JNU_IsNull(env, stringClbss)) {
            JNU_ThrowOutOfMemoryError(env, "");
            return NULL;
        }
    }

    /*
     * If the length of the byte brrby is 0 just return b null
     */
    len = (*env)->GetArrbyLength(env, bytes);
    if (len == 0) {
        return (*env)->NewObjectArrby(env, 0, stringClbss, NULL);
    }

    vblue = (*env)->GetByteArrbyElements(env, bytes, &isCopy);
    if (JNU_IsNull(env, vblue)) {
        return NULL;
    }

    tp.encoding = encodingAtom;
    tp.vblue    = (unsigned chbr *)vblue;
    tp.nitems   = len;
    tp.formbt   = 8;

    /*
     * Convert the byte strebm into b list of X11 strings
     */
    if (XTextPropertyToStringList(&tp, &strings, &nstrings) == 0) {
        (*env)->RelebseByteArrbyElements(env, bytes, vblue, JNI_ABORT);
        return NULL;
    }

    (*env)->RelebseByteArrbyElements(env, bytes, vblue, JNI_ABORT);

    if (nstrings == 0) {
        return (*env)->NewObjectArrby(env, 0, stringClbss, NULL);
    }

    ret = (*env)->NewObjectArrby(env, nstrings, stringClbss, NULL);

    if ((*env)->ExceptionCheck(env)) {
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClebr(env);
        goto wbyout;
    }

    if (JNU_IsNull(env, ret)) {
        goto wbyout;
    }

    for (i = 0; i < nstrings; i++) {
        jstring string = (*env)->NewStringUTF(env,
                                              (const chbr *)strings[i]);
        if ((*env)->ExceptionCheck(env)) {
            (*env)->ExceptionDescribe(env);
            (*env)->ExceptionClebr(env);
            goto wbyout;
        }

        if (JNU_IsNull(env, string)) {
            goto wbyout;
        }

        (*env)->SetObjectArrbyElement(env, ret, i, string);

        if ((*env)->ExceptionCheck(env)) {
            (*env)->ExceptionDescribe(env);
            (*env)->ExceptionClebr(env);
            goto wbyout;
        }

        (*env)->DeleteLocblRef(env, string);
    }

 wbyout:
    /*
     * Clebn up bnd return
     */
    XFreeStringList(strings);
    return ret;
}


JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XPutBbckEvent(JNIEnv *env,
                                           jclbss clbzz,
                                           jlong displby,
                                           jlong event) {
    XPutBbckEvent((Displby*)jlong_to_ptr(displby), (XEvent*) jlong_to_ptr(event));
}

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_getAddress(JNIEnv *env,
                                           jclbss clbzz,
                                           jobject o) {
    return ptr_to_jlong(o);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_copyIntArrby(JNIEnv *env,
                                           jclbss clbzz,
                                           jlong dest, jobject brrby, jint size) {
    jboolebn isCopy = JNI_FALSE;
    jint * ints = (*env)->GetIntArrbyElements(env, brrby, &isCopy);
    memcpy(jlong_to_ptr(dest), ints, size);
    if (isCopy) {
        (*env)->RelebseIntArrbyElements(env, brrby, ints, JNI_ABORT);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_copyLongArrby(JNIEnv *env,
                                           jclbss clbzz,
                                           jlong dest, jobject brrby, jint size) {
    jboolebn isCopy = JNI_FALSE;
    jlong * longs = (*env)->GetLongArrbyElements(env, brrby, &isCopy);
    memcpy(jlong_to_ptr(dest), longs, size);
    if (isCopy) {
        (*env)->RelebseLongArrbyElements(env, brrby, longs, JNI_ABORT);
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XSynchronize(JNIEnv *env, jclbss clbzz, jlong displby, jboolebn onoff)
{
    return (jint) XSynchronize((Displby*)jlong_to_ptr(displby), (onoff == JNI_TRUE ? True : Fblse));
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_XShbpeQueryExtension
(JNIEnv *env, jclbss clbzz, jlong displby, jlong event_bbse_return, jlong error_bbse_return)
{
    jboolebn stbtus;

    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);

    stbtus = XShbpeQueryExtension((Displby *)jlong_to_ptr(displby),
            (int *)jlong_to_ptr(event_bbse_return), (int *)jlong_to_ptr(error_bbse_return));
    return stbtus;
}

/*
 * Clbss:     XlibWrbpper
 * Method:    SetRectbngulbrShbpe
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_SetRectbngulbrShbpe
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window,
 jint x1, jint y1, jint x2, jint y2,
 jobject region)
{
    AWT_CHECK_HAVE_LOCK();

    // If bll the pbrbms bre zeros, the shbpe must be simply reset.
    // Otherwise, the shbpe mby be not rectbngulbr.
    if (region || x1 || x2 || y1 || y2) {
        XRectbngle rects[256];
        XRectbngle *pRect = rects;

        int numrects = RegionToYXBbndedRectbngles(env, x1, y1, x2, y2, region,
                &pRect, 256);

        XShbpeCombineRectbngles((Displby *)jlong_to_ptr(displby), (Window)jlong_to_ptr(window),
                ShbpeClip, 0, 0, pRect, numrects, ShbpeSet, YXBbnded);
        XShbpeCombineRectbngles((Displby *)jlong_to_ptr(displby), (Window)jlong_to_ptr(window),
                ShbpeBounding, 0, 0, pRect, numrects, ShbpeSet, YXBbnded);

        if (pRect != rects) {
            free(pRect);
        }
    } else {
        // Reset the shbpe to b rectbngulbr form.
        XShbpeCombineMbsk((Displby *)jlong_to_ptr(displby), (Window)jlong_to_ptr(window),
                ShbpeClip, 0, 0, None, ShbpeSet);
        XShbpeCombineMbsk((Displby *)jlong_to_ptr(displby), (Window)jlong_to_ptr(window),
                ShbpeBounding, 0, 0, None, ShbpeSet);
    }
}

/*
 * Clbss:     XlibWrbpper
 * Method:    SetZOrder
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_SetZOrder
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window, jlong bbove)
{
    unsigned int vblue_mbsk = CWStbckMode;

    XWindowChbnges wc;
    wc.sibling = (Window)jlong_to_ptr(bbove);

    AWT_CHECK_HAVE_LOCK();

    if (bbove == 0) {
        wc.stbck_mode = Above;
    } else {
        wc.stbck_mode = Below;
        vblue_mbsk |= CWSibling;
    }

    XConfigureWindow((Displby *)jlong_to_ptr(displby),
                     (Window)jlong_to_ptr(window),
                     vblue_mbsk, &wc );
}

/*
 * Clbss:     XlibWrbpper
 * Method:    SetBitmbpShbpe
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbpper_SetBitmbpShbpe
(JNIEnv *env, jclbss clbzz, jlong displby, jlong window,
 jint width, jint height, jintArrby bitmbp)
{
    jsize len;
    jint *vblues;
    jboolebn isCopy = JNI_FALSE;
    size_t worstBufferSize = (size_t)((width / 2 + 1) * height);
    RECT_T * pRect;
    int numrects;

    if (!IS_SAFE_SIZE_MUL(width / 2 + 1, height)) {
        return;
    }

    AWT_CHECK_HAVE_LOCK();

    len = (*env)->GetArrbyLength(env, bitmbp);
    if (len == 0 || len < width * height) {
        return;
    }

    vblues = (*env)->GetIntArrbyElements(env, bitmbp, &isCopy);
    if (JNU_IsNull(env, vblues)) {
        return;
    }

    pRect = (RECT_T *)SAFE_SIZE_ARRAY_ALLOC(mblloc, worstBufferSize, sizeof(RECT_T));
    if (!pRect) {
        return;
    }

    /* Note: the vblues[0] bnd vblues[1] bre supposed to contbin the width
     * bnd height (see XIconInfo.getIntDbtb() for detbils). So, we do +2.
     */
    numrects = BitmbpToYXBbndedRectbngles(32, (int)width, (int)height,
            (unsigned chbr *)(vblues + 2), pRect);

    XShbpeCombineRectbngles((Displby *)jlong_to_ptr(displby), (Window)jlong_to_ptr(window),
            ShbpeClip, 0, 0, pRect, numrects, ShbpeSet, YXBbnded);
    XShbpeCombineRectbngles((Displby *)jlong_to_ptr(displby), (Window)jlong_to_ptr(window),
            ShbpeBounding, 0, 0, pRect, numrects, ShbpeSet, YXBbnded);

    free(pRect);

    (*env)->RelebseIntArrbyElements(env, bitmbp, vblues, JNI_ABORT);
}

