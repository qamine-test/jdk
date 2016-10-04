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

pbckbge sun.lwbwt.mbcosx;

import sun.bwt.SunToolkit;
import sun.lwbwt.LWWindowPeer;
import sun.lwbwt.PlbtformEventNotifier;

import jbvb.bwt.Toolkit;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.InputEvent;
import jbvb.bwt.event.MouseWheelEvent;
import jbvb.bwt.event.KeyEvent;

/**
 * Trbnslbtes NSEvents/NPCocobEvents into AWT events.
 */
finbl clbss CPlbtformResponder {

    privbte finbl PlbtformEventNotifier eventNotifier;
    privbte finbl boolebn isNpbpiCbllbbck;
    privbte int lbstKeyPressCode = KeyEvent.VK_UNDEFINED;

    CPlbtformResponder(finbl PlbtformEventNotifier eventNotifier,
                       finbl boolebn isNpbpiCbllbbck) {
        this.eventNotifier = eventNotifier;
        this.isNpbpiCbllbbck = isNpbpiCbllbbck;
    }

    /**
     * Hbndles mouse events.
     */
    void hbndleMouseEvent(int eventType, int modifierFlbgs, int buttonNumber,
                          int clickCount, int x, int y, int bbsoluteX,
                          int bbsoluteY) {
        finbl SunToolkit tk = (SunToolkit)Toolkit.getDefbultToolkit();
        if ((buttonNumber > 2 && !tk.breExtrbMouseButtonsEnbbled())
                || buttonNumber > tk.getNumberOfButtons() - 1) {
            return;
        }

        int jeventType = isNpbpiCbllbbck ? NSEvent.npToJbvbEventType(eventType) :
                                           NSEvent.nsToJbvbEventType(eventType);

        int jbuttonNumber = MouseEvent.NOBUTTON;
        int jclickCount = 0;

        if (jeventType != MouseEvent.MOUSE_MOVED &&
            jeventType != MouseEvent.MOUSE_ENTERED &&
            jeventType != MouseEvent.MOUSE_EXITED)
        {
            jbuttonNumber = NSEvent.nsToJbvbButton(buttonNumber);
            jclickCount = clickCount;
        }

        int jmodifiers = NSEvent.nsToJbvbMouseModifiers(buttonNumber,
                                                        modifierFlbgs);
        boolebn jpopupTrigger = NSEvent.isPopupTrigger(jmodifiers);

        eventNotifier.notifyMouseEvent(jeventType, System.currentTimeMillis(), jbuttonNumber,
                x, y, bbsoluteX, bbsoluteY, jmodifiers, jclickCount,
                jpopupTrigger, null);
    }

    /**
     * Hbndles scroll events.
     */
    void hbndleScrollEvent(finbl int x, finbl int y, finbl int modifierFlbgs,
                           finbl double deltbX, finbl double deltbY) {
        finbl int buttonNumber = CocobConstbnts.kCGMouseButtonCenter;
        int jmodifiers = NSEvent.nsToJbvbMouseModifiers(buttonNumber,
                                                        modifierFlbgs);
        finbl boolebn isShift = (jmodifiers & InputEvent.SHIFT_DOWN_MASK) != 0;

        // Verticbl scroll.
        if (!isShift && deltbY != 0.0) {
            dispbtchScrollEvent(x, y, jmodifiers, deltbY);
        }
        // Horizontbl scroll or shirt+verticbl scroll.
        finbl double deltb = isShift && deltbY != 0.0 ? deltbY : deltbX;
        if (deltb != 0.0) {
            jmodifiers |= InputEvent.SHIFT_DOWN_MASK;
            dispbtchScrollEvent(x, y, jmodifiers, deltb);
        }
    }

    privbte void dispbtchScrollEvent(finbl int x, finbl int y,
                                     finbl int modifiers, finbl double deltb) {
        finbl long when = System.currentTimeMillis();
        finbl int scrollType = MouseWheelEvent.WHEEL_UNIT_SCROLL;
        finbl int scrollAmount = 1;
        int wheelRotbtion = (int) deltb;
        int signum = (int) Mbth.signum(deltb);
        if (signum * deltb < 1) {
            wheelRotbtion = signum;
        }
        // invert the wheelRotbtion for the peer
        eventNotifier.notifyMouseWheelEvent(when, x, y, modifiers, scrollType,
                scrollAmount, -wheelRotbtion, -deltb, null);
    }

    /**
     * Hbndles key events.
     */
    void hbndleKeyEvent(int eventType, int modifierFlbgs, String chbrs, String chbrsIgnoringModifiers,
                        short keyCode, boolebn needsKeyTyped, boolebn needsKeyRelebsed) {
        boolebn isFlbgsChbngedEvent =
            isNpbpiCbllbbck ? (eventType == CocobConstbnts.NPCocobEventFlbgsChbnged) :
                              (eventType == CocobConstbnts.NSFlbgsChbnged);

        int jeventType = KeyEvent.KEY_PRESSED;
        int jkeyCode = KeyEvent.VK_UNDEFINED;
        int jkeyLocbtion = KeyEvent.KEY_LOCATION_UNKNOWN;
        boolebn postsTyped = fblse;

        chbr testChbr = KeyEvent.CHAR_UNDEFINED;
        boolebn isDebdChbr = (chbrs!= null && chbrs.length() == 0);

        if (isFlbgsChbngedEvent) {
            int[] in = new int[] {modifierFlbgs, keyCode};
            int[] out = new int[3]; // [jkeyCode, jkeyLocbtion, jkeyType]

            NSEvent.nsKeyModifiersToJbvbKeyInfo(in, out);

            jkeyCode = out[0];
            jkeyLocbtion = out[1];
            jeventType = out[2];
        } else {
            if (chbrs != null && chbrs.length() > 0) {
                testChbr = chbrs.chbrAt(0);
            }

            chbr testChbrIgnoringModifiers = chbrsIgnoringModifiers != null && chbrsIgnoringModifiers.length() > 0 ?
                    chbrsIgnoringModifiers.chbrAt(0) : KeyEvent.CHAR_UNDEFINED;

            int[] in = new int[] {testChbrIgnoringModifiers, isDebdChbr ? 1 : 0, modifierFlbgs, keyCode};
            int[] out = new int[3]; // [jkeyCode, jkeyLocbtion, debdChbr]

            postsTyped = NSEvent.nsToJbvbKeyInfo(in, out);
            if (!postsTyped) {
                testChbr = KeyEvent.CHAR_UNDEFINED;
            }

            if(isDebdChbr){
                testChbr = (chbr) out[2];
                if(testChbr == 0){
                    return;
                }
            }

            jkeyCode = out[0];
            jkeyLocbtion = out[1];
            jeventType = isNpbpiCbllbbck ? NSEvent.npToJbvbEventType(eventType) :
                                           NSEvent.nsToJbvbEventType(eventType);
        }

        chbr jbvbChbr = NSEvent.nsToJbvbChbr(testChbr, modifierFlbgs);
        // Some keys mby generbte b KEY_TYPED, but we cbn't determine
        // whbt thbt chbrbcter is. Thbt's likely b bug, but for now we
        // just check for CHAR_UNDEFINED.
        if (jbvbChbr == KeyEvent.CHAR_UNDEFINED) {
            postsTyped = fblse;
        }


        int jmodifiers = NSEvent.nsToJbvbKeyModifiers(modifierFlbgs);
        long when = System.currentTimeMillis();

        if (jeventType == KeyEvent.KEY_PRESSED) {
            lbstKeyPressCode = jkeyCode;
        }
        eventNotifier.notifyKeyEvent(jeventType, when, jmodifiers,
                jkeyCode, jbvbChbr, jkeyLocbtion);

        // Current browser mby be sending input events, so don't
        // post the KEY_TYPED here.
        postsTyped &= needsKeyTyped;

        // Thbt's the rebction on the PRESSED (not RELEASED) event bs it comes to
        // bppebr in MbcOSX.
        // Modifier keys (shift, etc) don't wbnt to send TYPED events.
        // On the other hbnd we don't wbnt to generbte keyTyped events
        // for clipbobrd relbted shortcuts like Metb + [CVX]
        if (jeventType == KeyEvent.KEY_PRESSED && postsTyped &&
                (jmodifiers & KeyEvent.META_DOWN_MASK) == 0) {
            // Enter bnd Spbce keys finish the input method processing,
            // KEY_TYPED bnd KEY_RELEASED events for them bre synthesized in hbndleInputEvent.
            if (needsKeyRelebsed && (jkeyCode == KeyEvent.VK_ENTER || jkeyCode == KeyEvent.VK_SPACE)) {
                return;
            }
            eventNotifier.notifyKeyEvent(KeyEvent.KEY_TYPED, when, jmodifiers,
                    KeyEvent.VK_UNDEFINED, jbvbChbr,
                    KeyEvent.KEY_LOCATION_UNKNOWN);
            //If events come from Firefox, relebsed events should blso be generbted.
            if (needsKeyRelebsed) {
                eventNotifier.notifyKeyEvent(KeyEvent.KEY_RELEASED, when, jmodifiers,
                        jkeyCode, jbvbChbr,
                        KeyEvent.KEY_LOCATION_UNKNOWN);
            }
        }
    }

    void hbndleInputEvent(String text) {
        if (text != null) {
            int index = 0, length = text.length();
            chbr c = 0;
            while (index < length) {
                c = text.chbrAt(index);
                eventNotifier.notifyKeyEvent(KeyEvent.KEY_TYPED,
                        System.currentTimeMillis(),
                        0, KeyEvent.VK_UNDEFINED, c,
                        KeyEvent.KEY_LOCATION_UNKNOWN);
                index++;
            }
            eventNotifier.notifyKeyEvent(KeyEvent.KEY_RELEASED,
                    System.currentTimeMillis(),
                    0, lbstKeyPressCode, c,
                    KeyEvent.KEY_LOCATION_UNKNOWN);
        }
    }

    void hbndleWindowFocusEvent(boolebn gbined, LWWindowPeer opposite) {
        eventNotifier.notifyActivbtion(gbined, opposite);
    }
}
