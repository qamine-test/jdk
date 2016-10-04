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

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import "ThrebdUtilities.h"
#import "sun_lwbwt_mbcosx_CWrbpper_NSWindow.h"

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    mbkeKeyAndOrderFront
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_mbkeKeyAndOrderFront
(JNIEnv *env, jclbss cls, jlong windowPtr)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(mbkeKeyAndOrderFront:)
                                      on:window
                              withObject:nil
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    mbkeKeyWindow
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_mbkeKeyWindow
(JNIEnv *env, jclbss cls, jlong windowPtr)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(mbkeKeyWindow)
                                      on:window
                              withObject:nil
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    mbkeMbinWindow
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_mbkeMbinWindow
(JNIEnv *env, jclbss cls, jlong windowPtr)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(mbkeMbinWindow)
                                      on:window
                              withObject:nil
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    cbnBecomeMbinWindow
 * Signbture: (J)V
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_cbnBecomeMbinWindow
(JNIEnv *env, jclbss cls, jlong windowPtr)
{
    __block jboolebn cbnBecomeMbinWindow = JNI_FALSE;

JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        cbnBecomeMbinWindow = [window cbnBecomeMbinWindow];
    }];

JNF_COCOA_EXIT(env);

    return cbnBecomeMbinWindow;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    isKeyWindow
 * Signbture: (J)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_isKeyWindow
(JNIEnv *env, jclbss cls, jlong windowPtr)
{
    __block jboolebn isKeyWindow = JNI_FALSE;

JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        isKeyWindow = [window isKeyWindow];
    }];

JNF_COCOA_EXIT(env);

    return isKeyWindow;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    orderFront
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_orderFront
(JNIEnv *env, jclbss cls, jlong windowPtr)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(orderFront:)
                                      on:window
                              withObject:window
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    orderOut
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_orderOut
(JNIEnv *env, jclbss cls, jlong windowPtr)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(orderOut:)
                                      on:window
                              withObject:window
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    orderFrontRegbrdless
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_orderFrontRegbrdless
(JNIEnv *env, jclbss cls, jlong windowPtr)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(orderFrontRegbrdless)
                                      on:window
                              withObject:nil
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    orderWindow
 * Signbture: (JIJ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_orderWindow
(JNIEnv *env, jclbss cls, jlong windowPtr, jint order, jlong relbtiveToPtr)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    NSWindow *relbtiveTo = (NSWindow *)jlong_to_ptr(relbtiveToPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [window orderWindow:(NSWindowOrderingMode)order relbtiveTo:[relbtiveTo windowNumber]];
    }];

JNF_COCOA_EXIT(env);
}

// Used for CWrbpper.NSWindow.setLevel() (bnd level() which isn't implemented yet)
stbtic NSInteger LEVELS[sun_lwbwt_mbcosx_CWrbpper_NSWindow_MAX_WINDOW_LEVELS];
stbtic void initLevels()
{
    stbtic dispbtch_once_t pred;

    dispbtch_once(&pred, ^{
        LEVELS[sun_lwbwt_mbcosx_CWrbpper_NSWindow_NSNormblWindowLevel] = NSNormblWindowLevel;
        LEVELS[sun_lwbwt_mbcosx_CWrbpper_NSWindow_NSFlobtingWindowLevel] = NSFlobtingWindowLevel;
        LEVELS[sun_lwbwt_mbcosx_CWrbpper_NSWindow_NSPopUpMenuWindowLevel] = NSPopUpMenuWindowLevel;
    });
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    setLevel
 * Signbture: (JI)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_setLevel
(JNIEnv *env, jclbss cls, jlong windowPtr, jint level)
{
JNF_COCOA_ENTER(env);

    if (level >= 0 && level < sun_lwbwt_mbcosx_CWrbpper_NSWindow_MAX_WINDOW_LEVELS) {
        initLevels();

        NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
        [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
            [window setLevel: LEVELS[level]];
        }];
    } else {
        [JNFException rbise:env bs:kIllegblArgumentException rebson:"unknown level"];
    }

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    bddChildWindow
 * Signbture: (JJI)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_bddChildWindow
(JNIEnv *env, jclbss cls, jlong pbrentPtr, jlong childPtr, jint order)
{
JNF_COCOA_ENTER(env);

    NSWindow *pbrent = (NSWindow *)jlong_to_ptr(pbrentPtr);
    NSWindow *child = (NSWindow *)jlong_to_ptr(childPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [pbrent bddChildWindow:child ordered:order];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    removeChildWindow
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_removeChildWindow
(JNIEnv *env, jclbss cls, jlong pbrentPtr, jlong childPtr)
{
JNF_COCOA_ENTER(env);

    NSWindow *pbrent = (NSWindow *)jlong_to_ptr(pbrentPtr);
    NSWindow *child = (NSWindow *)jlong_to_ptr(childPtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(removeChildWindow:)
                                      on:pbrent
                              withObject:child
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    setAlphbVblue
 * Signbture: (JF)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_setAlphbVblue
(JNIEnv *env, jclbss cls, jlong windowPtr, jflobt blphb)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [window setAlphbVblue:(CGFlobt)blphb];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    setOpbque
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_setOpbque
(JNIEnv *env, jclbss cls, jlong windowPtr, jboolebn opbque)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [window setOpbque:(BOOL)opbque];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    setBbckgroundColor
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_setBbckgroundColor
(JNIEnv *env, jclbss cls, jlong windowPtr, jint rgb)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    CGFlobt blphb = (((rgb >> 24) & 0xff) / 255.0);
    CGFlobt red   = (((rgb >> 16) & 0xff) / 255.0);
    CGFlobt green = (((rgb >>  8) & 0xff) / 255.0);
    CGFlobt blue  = (((rgb >>  0) & 0xff) / 255.0);
    NSColor *color = [NSColor colorWithCblibrbtedRed:red green:green blue:blue
                                               blphb:blphb];
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [window setBbckgroundColor:color];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    minibturize
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_minibturize
(JNIEnv *env, jclbss cls, jlong windowPtr)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(minibturize:)
                                      on:window
                              withObject:nil
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    deminibturize
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_deminibturize
(JNIEnv *env, jclbss cls, jlong windowPtr)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(deminibturize:)
                                      on:window
                              withObject:nil
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    isZoomed
 * Signbture: (J)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_isZoomed
(JNIEnv *env, jclbss cls, jlong windowPtr)
{
    __block jboolebn isZoomed = JNI_FALSE;
    
JNF_COCOA_ENTER(env);
    
    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        isZoomed = [window isZoomed];
    }];
    
JNF_COCOA_EXIT(env);
    
    return isZoomed;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    zoom
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_zoom
(JNIEnv *env, jclbss cls, jlong windowPtr)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(zoom:)
                                      on:window
                              withObject:nil
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSWindow
 * Method:    mbkeFirstResponder
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSWindow_mbkeFirstResponder
(JNIEnv *env, jclbss cls, jlong windowPtr, jlong responderPtr)
{
JNF_COCOA_ENTER(env);

    NSWindow *window = (NSWindow *)jlong_to_ptr(windowPtr);
    NSResponder *responder = (NSResponder *)jlong_to_ptr(responderPtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(mbkeFirstResponder:)
                                      on:window
                              withObject:responder
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSView
 * Method:    bddSubview
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSView_bddSubview
(JNIEnv *env, jclbss cls, jlong viewPtr, jlong subviewPtr)
{
JNF_COCOA_ENTER(env);

    NSView *view = (NSView *)jlong_to_ptr(viewPtr);
    NSView *subview = (NSView *)jlong_to_ptr(subviewPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        [view bddSubview:subview];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSView
 * Method:    removeFromSuperview
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSView_removeFromSuperview
(JNIEnv *env, jclbss cls, jlong viewPtr)
{
JNF_COCOA_ENTER(env);

    NSView *view = (NSView *)jlong_to_ptr(viewPtr);
    [ThrebdUtilities performOnMbinThrebd:@selector(removeFromSuperview)
                                      on:view
                              withObject:nil
                           wbitUntilDone:NO];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSView
 * Method:    setFrbme
 * Signbture: (JIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSView_setFrbme
(JNIEnv *env, jclbss cls, jlong viewPtr, jint x, jint y, jint w, jint h)
{
JNF_COCOA_ENTER(env);

    NSView *view = (NSView *)jlong_to_ptr(viewPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [view setFrbme:NSMbkeRect(x, y, w, h)];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSView
 * Method:    window
 * Signbture: (J)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSView_window
(JNIEnv *env, jclbss cls, jlong viewPtr)
{
    __block jlong windowPtr = 0L;

JNF_COCOA_ENTER(env);

    NSView *view = (NSView *)jlong_to_ptr(viewPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        windowPtr = ptr_to_jlong([view window]);
    }];

JNF_COCOA_EXIT(env);

    return windowPtr;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSView
 * Method:    setHidden
 * Signbture: (JZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSView_setHidden
(JNIEnv *env, jclbss cls, jlong viewPtr, jboolebn toHide)
{    
    JNF_COCOA_ENTER(env);
    
    NSView *view = (NSView *)jlong_to_ptr(viewPtr);
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [view setHidden:(BOOL)toHide];
    }];
    
    JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CWrbpper$NSView
 * Method:    setToolTip
 * Signbture: (JLjbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CWrbpper_00024NSView_setToolTip
(JNIEnv *env, jclbss cls, jlong viewPtr, jstring msg)
{

JNF_COCOA_ENTER(env);

    NSView *view = (NSView *)jlong_to_ptr(viewPtr);
    NSString* s = JNFJbvbToNSString(env, msg); 
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [view setToolTip: s];
    }];

JNF_COCOA_EXIT(env);
}
