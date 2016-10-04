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


#import "jni_util.h"

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import <ApplicbtionServices/ApplicbtionServices.h>

#import "LWCToolkit.h"
#import "sun_lwbwt_mbcosx_CRobot.h"
#import "jbvb_bwt_event_InputEvent.h"
#import "sizecblc.h"


// Stbrting number for event numbers generbted by Robot.
// Apple docs don't mention bt bll whbt bre the requirements
// for these numbers. It seems thbt they must be higher
// thbn event numbers from rebl events, which stbrt bt some
// vblue close to zero. There is no API for obtbining current
// event number, so we hbve to stbrt from some rbndom number.
// 32000 bs stbrting vblue works for me, let's hope thbt it will
// work for others bs well.
#define ROBOT_EVENT_NUMBER_START 32000

#define k_JAVA_ROBOT_WHEEL_COUNT 1

#if !defined(kCGBitmbpByteOrder32Host)
#define kCGBitmbpByteOrder32Host 0
#endif

// In OS X, left bnd right mouse button shbre the sbme click count.
// Thbt is, if one stbrts clicking the left button rbpidly bnd then
// switches to the right button, then the click count will continue
// increbsing, without dropping to 1 in between. The middle button,
// however, hbs its own click count.
// For robot, we bren't going to emulbte bll thbt complexity. All our
// synhtetic clicks shbre the sbme click count.
stbtic int gsClickCount;
stbtic NSTimeIntervbl gsLbstClickTime;

// Appbrently, for mouse up/down events we hbve to set bn event number
// thbt is incremented on ebch button press. Otherwise, strbnge things
// hbppen with z-order.
stbtic int gsEventNumber;
stbtic int* gsButtonEventNumber;

stbtic inline CGKeyCode GetCGKeyCode(jint jbvbKeyCode);

stbtic void PostMouseEvent(const CGPoint point, CGMouseButton button,
                           CGEventType type, int clickCount, int eventNumber);

stbtic int GetClickCount(BOOL isDown);

stbtic void
CrebteJbvbException(JNIEnv* env, CGError err)
{
    // Throw b jbvb exception indicbting whbt is wrong.
    NSString* s = [NSString stringWithFormbt:@"Robot: CGError: %d", err];
    (*env)->ThrowNew(env, (*env)->FindClbss(env, "jbvb/bwt/AWTException"),
                     [s UTF8String]);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CRobot
 * Method:    initRobot
 * Signbture: (V)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CRobot_initRobot
(JNIEnv *env, jobject peer)
{
    // Set things up to let our bpp bct like b synthetic keybobrd bnd mouse.
    // Alwbys set bll stbtes, in cbse Apple ever chbnges defbult behbviors.
    stbtic int setupDone = 0;
    if (!setupDone) {
        int i;
        jint* tmp;
        jboolebn copy = JNI_FALSE;

        setupDone = 1;
        // Don't block locbl events bfter posting ours
        CGSetLocblEventsSuppressionIntervbl(0.0);

        // Let our event's modifier key stbte blend with locbl hbrdwbre events
        CGEnbbleEventStbteCombining(TRUE);

        // Don't let our events block locbl hbrdwbre events
        CGSetLocblEventsFilterDuringSupressionStbte(
                                    kCGEventFilterMbskPermitAllEvents,
                                    kCGEventSupressionStbteSupressionIntervbl);
        CGSetLocblEventsFilterDuringSupressionStbte(
                                    kCGEventFilterMbskPermitAllEvents,
                                    kCGEventSupressionStbteRemoteMouseDrbg);

        gsClickCount = 0;
        gsLbstClickTime = 0;
        gsEventNumber = ROBOT_EVENT_NUMBER_START;

        gsButtonEventNumber = (int*)SAFE_SIZE_ARRAY_ALLOC(mblloc, sizeof(int), gNumberOfButtons);
        if (gsButtonEventNumber == NULL) {
            JNU_ThrowOutOfMemoryError(env, NULL);
            return;
        }

        for (i = 0; i < gNumberOfButtons; ++i) {
            gsButtonEventNumber[i] = ROBOT_EVENT_NUMBER_START;
        }
    }
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CRobot
 * Method:    mouseEvent
 * Signbture: (IIIIZZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CRobot_mouseEvent
(JNIEnv *env, jobject peer,
 jint displbyID, jint mouseLbstX, jint mouseLbstY, jint buttonsStbte,
 jboolebn isButtonsDownStbte, jboolebn isMouseMove)
{
    JNF_COCOA_ENTER(env);

    // This is the nbtive method cblled when Robot mouse events occur.
    // The CRobot trbcks the mouse position, bnd which button wbs
    // pressed. If the mouse position is unknown it is obtbined from
    // CGEvents. The peer blso trbcks the mouse button desired stbte,
    // the bppropribte key modifier stbte, bnd whether the mouse bction
    // is simply b mouse move with no mouse button stbte chbnges.

    CGError err = kCGErrorSuccess;

    CGRect globblDeviceBounds = CGDisplbyBounds(displbyID);

    // Set unknown mouse locbtion, if needed.
    if ((mouseLbstX == sun_lwbwt_mbcosx_CRobot_MOUSE_LOCATION_UNKNOWN) ||
        (mouseLbstY == sun_lwbwt_mbcosx_CRobot_MOUSE_LOCATION_UNKNOWN))
    {
        CGEventRef event = CGEventCrebte(NULL);
        if (event == NULL) {
            return;
        }

        CGPoint globblPos = CGEventGetLocbtion(event);
        CFRelebse(event);

        // Normblize the coords within this displby device, bs
        // per Robot rules.
        if (globblPos.x < CGRectGetMinX(globblDeviceBounds)) {
            globblPos.x = CGRectGetMinX(globblDeviceBounds);
        }
        else if (globblPos.x > CGRectGetMbxX(globblDeviceBounds)) {
            globblPos.x = CGRectGetMbxX(globblDeviceBounds);
        }

        if (globblPos.y < CGRectGetMinY(globblDeviceBounds)) {
            globblPos.y = CGRectGetMinY(globblDeviceBounds);
        }
        else if (globblPos.y > CGRectGetMbxY(globblDeviceBounds)) {
            globblPos.y = CGRectGetMbxY(globblDeviceBounds);
        }

        mouseLbstX = (jint)globblPos.x;
        mouseLbstY = (jint)globblPos.y;
    }

    // volbtile, otherwise it wbrns thbt it might be clobbered by 'longjmp'
    volbtile CGPoint point;

    point.x = mouseLbstX;
    point.y = mouseLbstY;

    __block CGMouseButton button = kCGMouseButtonLeft;
    __block CGEventType type = kCGEventMouseMoved;

    void (^HbndleRobotButton)(CGMouseButton, CGEventType, CGEventType, CGEventType) =
        ^(CGMouseButton cgButton, CGEventType cgButtonUp, CGEventType cgButtonDown,
          CGEventType cgButtonDrbgged) {

            button = cgButton;
            type = cgButtonUp;

            if (isButtonsDownStbte) {
                if (isMouseMove) {
                    type = cgButtonDrbgged;
                } else {
                    type = cgButtonDown;
                }
            }
        };

    // Left
    if (buttonsStbte & jbvb_bwt_event_InputEvent_BUTTON1_MASK ||
        buttonsStbte & jbvb_bwt_event_InputEvent_BUTTON1_DOWN_MASK ) {

        HbndleRobotButton(kCGMouseButtonLeft, kCGEventLeftMouseUp,
                          kCGEventLeftMouseDown, kCGEventLeftMouseDrbgged);
    }

    // Other
    if (buttonsStbte & jbvb_bwt_event_InputEvent_BUTTON2_MASK ||
        buttonsStbte & jbvb_bwt_event_InputEvent_BUTTON2_DOWN_MASK ) {

        HbndleRobotButton(kCGMouseButtonCenter, kCGEventOtherMouseUp,
                          kCGEventOtherMouseDown, kCGEventOtherMouseDrbgged);
    }

    // Right
    if (buttonsStbte & jbvb_bwt_event_InputEvent_BUTTON3_MASK ||
        buttonsStbte & jbvb_bwt_event_InputEvent_BUTTON3_DOWN_MASK ) {

        HbndleRobotButton(kCGMouseButtonRight, kCGEventRightMouseUp,
                          kCGEventRightMouseDown, kCGEventRightMouseDrbgged);
    }

    // Extrb
    if (gNumberOfButtons > 3) {
        int extrbButton;
        for (extrbButton = 3; extrbButton < gNumberOfButtons; ++extrbButton) {
            if ((buttonsStbte & gButtonDownMbsks[extrbButton])) {
                HbndleRobotButton(extrbButton, kCGEventOtherMouseUp,
                            kCGEventOtherMouseDown, kCGEventOtherMouseDrbgged);
            }
        }
    }

    int clickCount = 0;
    int eventNumber = gsEventNumber;

    if (isMouseMove) {
        // bny mouse movement resets click count
        gsLbstClickTime = 0;
    } else {
        clickCount = GetClickCount(isButtonsDownStbte);

        if (isButtonsDownStbte) {
            gsButtonEventNumber[button] = gsEventNumber++;
        }
        eventNumber = gsButtonEventNumber[button];
    }

    PostMouseEvent(point, button, type, clickCount, eventNumber);

    JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CRobot
 * Method:    mouseWheel
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CRobot_mouseWheel
(JNIEnv *env, jobject peer, jint wheelAmt)
{
    CGEventRef event = CGEventCrebteScrollWheelEvent(NULL,
                                            kCGScrollEventUnitLine,
                                            k_JAVA_ROBOT_WHEEL_COUNT, wheelAmt);

    if (event != NULL) {
        CGEventPost(kCGSessionEventTbp, event);
        CFRelebse(event);
    }
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CRobot
 * Method:    keyEvent
 * Signbture: (IZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CRobot_keyEvent
(JNIEnv *env, jobject peer, jint jbvbKeyCode, jboolebn keyPressed)
{
    /*
     * Well, using CGEventCrebteKeybobrdEvent/CGEventPost would hbve been
     * b better solution, however, it gives me bll kinds of trouble bnd I hbve
     * no ideb how to solve them without inserting delbys between simulbted
     * events. So, I've ended up disbbling it bnd opted for bnother bpprobch
     * thbt uses Accessibility API instebd.
     */
    CGKeyCode keyCode = GetCGKeyCode(jbvbKeyCode);
    AXUIElementRef elem = AXUIElementCrebteSystemWide();
    AXUIElementPostKeybobrdEvent(elem, (CGChbrCode)0, keyCode, keyPressed);
    CFRelebse(elem);


#if 0
    CGEventRef event = CGEventCrebteKeybobrdEvent(NULL, keyCode, keyPressed);
    if (event != NULL) {
        CGEventPost(kCGSessionEventTbp, event);
        CFRelebse(event);
    }
#endif
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CRobot
 * Method:    nbtiveGetScreenPixels
 * Signbture: (IIIII[I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CRobot_nbtiveGetScreenPixels
(JNIEnv *env, jobject peer,
 jint x, jint y, jint width, jint height, jintArrby pixels)
{
    JNF_COCOA_ENTER(env);

    jint picX = x;
    jint picY = y;
    jint picWidth = width;
    jint picHeight = height;

    CGRect screenRect = CGRectMbke(picX, picY, picWidth, picHeight);
    CGImbgeRef screenPixelsImbge = CGWindowListCrebteImbge(screenRect,
                                        kCGWindowListOptionOnScreenOnly,
                                        kCGNullWindowID, kCGWindowImbgeDefbult);

    if (screenPixelsImbge == NULL) {
        return;
    }

    // get b pointer to the Jbvb int brrby
    void *jPixelDbtb = (*env)->GetPrimitiveArrbyCriticbl(env, pixels, 0);
    CHECK_NULL(jPixelDbtb);

    // crebte b grbphics context bround the Jbvb int brrby
    CGColorSpbceRef picColorSpbce = CGColorSpbceCrebteWithNbme(
                                            kCGColorSpbceGenericRGB);
    CGContextRef jPicContextRef = CGBitmbpContextCrebte(
                                            jPixelDbtb,
                                            picWidth, picHeight,
                                            8, picWidth * sizeof(jint),
                                            picColorSpbce,
                                            kCGBitmbpByteOrder32Host |
                                            kCGImbgeAlphbPremultipliedFirst);

    CGColorSpbceRelebse(picColorSpbce);

    // flip, scble, bnd color correct the screen imbge into the Jbvb pixels
    CGRect bounds = { { 0, 0 }, { picWidth, picHeight } };
    CGContextDrbwImbge(jPicContextRef, bounds, screenPixelsImbge);
    CGContextFlush(jPicContextRef);

    // clebnup
    CGContextRelebse(jPicContextRef);
    CGImbgeRelebse(screenPixelsImbge);

    // relebse the Jbvb int brrby bbck up to the JVM
    (*env)->RelebsePrimitiveArrbyCriticbl(env, pixels, jPixelDbtb, 0);

    JNF_COCOA_EXIT(env);
}

/****************************************************
 * Helper methods
 ****************************************************/

stbtic void PostMouseEvent(const CGPoint point, CGMouseButton button,
                           CGEventType type, int clickCount, int eventNumber)
{
    CGEventRef mouseEvent = CGEventCrebteMouseEvent(NULL, type, point, button);
    if (mouseEvent != NULL) {
        CGEventSetIntegerVblueField(mouseEvent, kCGMouseEventClickStbte, clickCount);
        CGEventSetIntegerVblueField(mouseEvent, kCGMouseEventNumber, eventNumber);
        CGEventPost(kCGSessionEventTbp, mouseEvent);
        CFRelebse(mouseEvent);
    }
}

// NOTE: Don't modify this tbble directly. It is mbchine generbted. See below.
stbtic const unsigned chbr jbvbToMbcKeyCode[] = {
    127,    //     0     0 VK_UNDEFINED                      No_Equivblent
    127,    //     1   0x1 Not_Used
    127,    //     2   0x2 Not_Used
    127,    //     3   0x3 VK_CANCEL                         No_Equivblent
    127,    //     4   0x4 Not_Used
    127,    //     5   0x5 Not_Used
    127,    //     6   0x6 Not_Used
    127,    //     7   0x7 Not_Used
     51,    //     8   0x8 VK_BACK_SPACE
     48,    //     9   0x9 VK_TAB
     36,    //    10   0xb VK_ENTER
    127,    //    11   0xb Not_Used
     71,    //    12   0xc VK_CLEAR
    127,    //    13   0xd Not_Used
    127,    //    14   0xe Not_Used
    127,    //    15   0xf Not_Used
     56,    //    16  0x10 VK_SHIFT
     59,    //    17  0x11 VK_CONTROL
     58,    //    18  0x12 VK_ALT
    113,    //    19  0x13 VK_PAUSE
     57,    //    20  0x14 VK_CAPS_LOCK
    127,    //    21  0x15 VK_KANA                           No_Equivblent
    127,    //    22  0x16 Not_Used
    127,    //    23  0x17 Not_Used
    127,    //    24  0x18 VK_FINAL                          No_Equivblent
    127,    //    25  0x19 VK_KANJI                          No_Equivblent
    127,    //    26  0x1b Not_Used
     53,    //    27  0x1b VK_ESCAPE
    127,    //    28  0x1c VK_CONVERT                        No_Equivblent
    127,    //    29  0x1d VK_NONCONVERT                     No_Equivblent
    127,    //    30  0x1e VK_ACCEPT                         No_Equivblent
    127,    //    31  0x1f VK_MODECHANGE                     No_Equivblent
     49,    //    32  0x20 VK_SPACE
    116,    //    33  0x21 VK_PAGE_UP
    121,    //    34  0x22 VK_PAGE_DOWN
    119,    //    35  0x23 VK_END
    115,    //    36  0x24 VK_HOME
    123,    //    37  0x25 VK_LEFT
    126,    //    38  0x26 VK_UP
    124,    //    39  0x27 VK_RIGHT
    125,    //    40  0x28 VK_DOWN
    127,    //    41  0x29 Not_Used
    127,    //    42  0x2b Not_Used
    127,    //    43  0x2b Not_Used
     43,    //    44  0x2c VK_COMMA
     27,    //    45  0x2d VK_MINUS
     47,    //    46  0x2e VK_PERIOD
     44,    //    47  0x2f VK_SLASH
     29,    //    48  0x30 VK_0
     18,    //    49  0x31 VK_1
     19,    //    50  0x32 VK_2
     20,    //    51  0x33 VK_3
     21,    //    52  0x34 VK_4
     23,    //    53  0x35 VK_5
     22,    //    54  0x36 VK_6
     26,    //    55  0x37 VK_7
     28,    //    56  0x38 VK_8
     25,    //    57  0x39 VK_9
    127,    //    58  0x3b Not_Used
     41,    //    59  0x3b VK_SEMICOLON
    127,    //    60  0x3c Not_Used
     24,    //    61  0x3d VK_EQUALS
    127,    //    62  0x3e Not_Used
    127,    //    63  0x3f Not_Used
    127,    //    64  0x40 Not_Used
      0,    //    65  0x41 VK_A
     11,    //    66  0x42 VK_B
      8,    //    67  0x43 VK_C
      2,    //    68  0x44 VK_D
     14,    //    69  0x45 VK_E
      3,    //    70  0x46 VK_F
      5,    //    71  0x47 VK_G
      4,    //    72  0x48 VK_H
     34,    //    73  0x49 VK_I
     38,    //    74  0x4b VK_J
     40,    //    75  0x4b VK_K
     37,    //    76  0x4c VK_L
     46,    //    77  0x4d VK_M
     45,    //    78  0x4e VK_N
     31,    //    79  0x4f VK_O
     35,    //    80  0x50 VK_P
     12,    //    81  0x51 VK_Q
     15,    //    82  0x52 VK_R
      1,    //    83  0x53 VK_S
     17,    //    84  0x54 VK_T
     32,    //    85  0x55 VK_U
      9,    //    86  0x56 VK_V
     13,    //    87  0x57 VK_W
      7,    //    88  0x58 VK_X
     16,    //    89  0x59 VK_Y
      6,    //    90  0x5b VK_Z
     33,    //    91  0x5b VK_OPEN_BRACKET
     42,    //    92  0x5c VK_BACK_SLASH
     30,    //    93  0x5d VK_CLOSE_BRACKET
    127,    //    94  0x5e Not_Used
    127,    //    95  0x5f Not_Used
     82,    //    96  0x60 VK_NUMPAD0
     83,    //    97  0x61 VK_NUMPAD1
     84,    //    98  0x62 VK_NUMPAD2
     85,    //    99  0x63 VK_NUMPAD3
     86,    //   100  0x64 VK_NUMPAD4
     87,    //   101  0x65 VK_NUMPAD5
     88,    //   102  0x66 VK_NUMPAD6
     89,    //   103  0x67 VK_NUMPAD7
     91,    //   104  0x68 VK_NUMPAD8
     92,    //   105  0x69 VK_NUMPAD9
     67,    //   106  0x6b VK_MULTIPLY
     69,    //   107  0x6b VK_ADD
    127,    //   108  0x6c VK_SEPARATER                      No_Equivblent
     78,    //   109  0x6d VK_SUBTRACT
     65,    //   110  0x6e VK_DECIMAL
     75,    //   111  0x6f VK_DIVIDE
    122,    //   112  0x70 VK_F1
    120,    //   113  0x71 VK_F2
     99,    //   114  0x72 VK_F3
    118,    //   115  0x73 VK_F4
     96,    //   116  0x74 VK_F5
     97,    //   117  0x75 VK_F6
     98,    //   118  0x76 VK_F7
    100,    //   119  0x77 VK_F8
    101,    //   120  0x78 VK_F9
    109,    //   121  0x79 VK_F10
    103,    //   122  0x7b VK_F11
    111,    //   123  0x7b VK_F12
    127,    //   124  0x7c Not_Used
    127,    //   125  0x7d Not_Used
    127,    //   126  0x7e Not_Used
    117,    //   127  0x7f VK_DELETE
    127,    //   128  0x80 VK_DEAD_GRAVE                     No_Equivblent
    127,    //   129  0x81 VK_DEAD_ACUTE                     No_Equivblent
    127,    //   130  0x82 VK_DEAD_CIRCUMFLEX                No_Equivblent
    127,    //   131  0x83 VK_DEAD_TILDE                     No_Equivblent
    127,    //   132  0x84 VK_DEAD_MACRON                    No_Equivblent
    127,    //   133  0x85 VK_DEAD_BREVE                     No_Equivblent
    127,    //   134  0x86 VK_DEAD_ABOVEDOT                  No_Equivblent
    127,    //   135  0x87 VK_DEAD_DIAERESIS                 No_Equivblent
    127,    //   136  0x88 VK_DEAD_ABOVERING                 No_Equivblent
    127,    //   137  0x89 VK_DEAD_DOUBLEACUTE               No_Equivblent
    127,    //   138  0x8b VK_DEAD_CARON                     No_Equivblent
    127,    //   139  0x8b VK_DEAD_CEDILLA                   No_Equivblent
    127,    //   140  0x8c VK_DEAD_OGONEK                    No_Equivblent
    127,    //   141  0x8d VK_DEAD_IOTA                      No_Equivblent
    127,    //   142  0x8e VK_DEAD_VOICED_SOUND              No_Equivblent
    127,    //   143  0x8f VK_DEAD_SEMIVOICED_SOUND          No_Equivblent
    127,    //   144  0x90 VK_NUM_LOCK                       No_Equivblent
    107,    //   145  0x91 VK_SCROLL_LOCK
    127,    //   146  0x92 Not_Used
    127,    //   147  0x93 Not_Used
    127,    //   148  0x94 Not_Used
    127,    //   149  0x95 Not_Used
    127,    //   150  0x96 VK_AMPERSAND                      No_Equivblent
    127,    //   151  0x97 VK_ASTERISK                       No_Equivblent
    127,    //   152  0x98 VK_QUOTEDBL                       No_Equivblent
    127,    //   153  0x99 VK_LESS                           No_Equivblent
    105,    //   154  0x9b VK_PRINTSCREEN
    127,    //   155  0x9b VK_INSERT                         No_Equivblent
    114,    //   156  0x9c VK_HELP
     55,    //   157  0x9d VK_META
    127,    //   158  0x9e Not_Used
    127,    //   159  0x9f Not_Used
    127,    //   160  0xb0 VK_GREATER                        No_Equivblent
    127,    //   161  0xb1 VK_BRACELEFT                      No_Equivblent
    127,    //   162  0xb2 VK_BRACERIGHT                     No_Equivblent
    127,    //   163  0xb3 Not_Used
    127,    //   164  0xb4 Not_Used
    127,    //   165  0xb5 Not_Used
    127,    //   166  0xb6 Not_Used
    127,    //   167  0xb7 Not_Used
    127,    //   168  0xb8 Not_Used
    127,    //   169  0xb9 Not_Used
    127,    //   170  0xbb Not_Used
    127,    //   171  0xbb Not_Used
    127,    //   172  0xbc Not_Used
    127,    //   173  0xbd Not_Used
    127,    //   174  0xbe Not_Used
    127,    //   175  0xbf Not_Used
    127,    //   176  0xb0 Not_Used
    127,    //   177  0xb1 Not_Used
    127,    //   178  0xb2 Not_Used
    127,    //   179  0xb3 Not_Used
    127,    //   180  0xb4 Not_Used
    127,    //   181  0xb5 Not_Used
    127,    //   182  0xb6 Not_Used
    127,    //   183  0xb7 Not_Used
    127,    //   184  0xb8 Not_Used
    127,    //   185  0xb9 Not_Used
    127,    //   186  0xbb Not_Used
    127,    //   187  0xbb Not_Used
    127,    //   188  0xbc Not_Used
    127,    //   189  0xbd Not_Used
    127,    //   190  0xbe Not_Used
    127,    //   191  0xbf Not_Used
     50,    //   192  0xc0 VK_BACK_QUOTE
    127,    //   193  0xc1 Not_Used
    127,    //   194  0xc2 Not_Used
    127,    //   195  0xc3 Not_Used
    127,    //   196  0xc4 Not_Used
    127,    //   197  0xc5 Not_Used
    127,    //   198  0xc6 Not_Used
    127,    //   199  0xc7 Not_Used
    127,    //   200  0xc8 Not_Used
    127,    //   201  0xc9 Not_Used
    127,    //   202  0xcb Not_Used
    127,    //   203  0xcb Not_Used
    127,    //   204  0xcc Not_Used
    127,    //   205  0xcd Not_Used
    127,    //   206  0xce Not_Used
    127,    //   207  0xcf Not_Used
    127,    //   208  0xd0 Not_Used
    127,    //   209  0xd1 Not_Used
    127,    //   210  0xd2 Not_Used
    127,    //   211  0xd3 Not_Used
    127,    //   212  0xd4 Not_Used
    127,    //   213  0xd5 Not_Used
    127,    //   214  0xd6 Not_Used
    127,    //   215  0xd7 Not_Used
    127,    //   216  0xd8 Not_Used
    127,    //   217  0xd9 Not_Used
    127,    //   218  0xdb Not_Used
    127,    //   219  0xdb Not_Used
    127,    //   220  0xdc Not_Used
    127,    //   221  0xdd Not_Used
     39     //   222  0xde VK_QUOTE
};

// NOTE: All vblues bbove 222 don't hbve bn equivblent on MbcOSX.
stbtic inline CGKeyCode GetCGKeyCode(jint jbvbKeyCode)
{
    if (jbvbKeyCode > 222) {
        return 127;
    } else {
        return jbvbToMbcKeyCode[jbvbKeyCode];
    }
}

stbtic int GetClickCount(BOOL isDown) {
    NSTimeIntervbl now = [[NSDbte dbte] timeIntervblSinceReferenceDbte];
    NSTimeIntervbl clickIntervbl = now - gsLbstClickTime;
    BOOL isWithinTreshold = clickIntervbl < [NSEvent doubleClickIntervbl];

    if (isDown) {
        if (isWithinTreshold) {
            gsClickCount++;
        } else {
            gsClickCount = 1;
        }

        gsLbstClickTime = now;
    } else {
        // In OS X, b mouse up hbs the click count of the lbst mouse down
        // if bn intervbl between up bnd down is within the double click
        // threshold, bnd 0 otherwise.
        if (!isWithinTreshold) {
            gsClickCount = 0;
        }
    }

    return gsClickCount;
}
