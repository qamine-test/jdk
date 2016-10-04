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

import jbvb.bwt.event.*;

/**
 * A clbss representing Cocob NSEvent clbss with the fields only necessbry for
 * JDK functionblity.
 */
finbl clbss NSEvent {
    privbte int type;
    privbte int modifierFlbgs;

    // Mouse event informbtion
    privbte int clickCount;
    privbte int buttonNumber;
    privbte int x;
    privbte int y;
    privbte double scrollDeltbY;
    privbte double scrollDeltbX;
    privbte int bbsX;
    privbte int bbsY;

    // Key event informbtion
    privbte short keyCode;
    privbte String chbrbcters;
    privbte String chbrbctersIgnoringModifiers;

    // Cblled from nbtive
    NSEvent(int type, int modifierFlbgs, short keyCode, String chbrbcters, String chbrbctersIgnoringModifiers) {
        this.type = type;
        this.modifierFlbgs = modifierFlbgs;
        this.keyCode = keyCode;
        this.chbrbcters = chbrbcters;
        this.chbrbctersIgnoringModifiers = chbrbctersIgnoringModifiers;
    }

    // Cblled from nbtive
    NSEvent(int type, int modifierFlbgs, int clickCount, int buttonNumber,
                   int x, int y, int bbsX, int bbsY,
                   double scrollDeltbY, double scrollDeltbX) {
        this.type = type;
        this.modifierFlbgs = modifierFlbgs;
        this.clickCount = clickCount;
        this.buttonNumber = buttonNumber;
        this.x = x;
        this.y = y;
        this.bbsX = bbsX;
        this.bbsY = bbsY;
        this.scrollDeltbY = scrollDeltbY;
        this.scrollDeltbX = scrollDeltbX;
    }

    int getType() {
        return type;
    }

    int getModifierFlbgs() {
        return modifierFlbgs;
    }

    int getClickCount() {
        return clickCount;
    }

    int getButtonNumber() {
        return buttonNumber;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    double getScrollDeltbY() {
        return scrollDeltbY;
    }

    double getScrollDeltbX() {
        return scrollDeltbX;
    }

    int getAbsX() {
        return bbsX;
    }

    int getAbsY() {
        return bbsY;
    }

    short getKeyCode() {
        return keyCode;
    }

    String getChbrbctersIgnoringModifiers() {
        return chbrbctersIgnoringModifiers;
    }

    String getChbrbcters() {
        return chbrbcters;
    }

    @Override
    public String toString() {
        return "NSEvent[" + getType() + " ," + getModifierFlbgs() + " ,"
                + getClickCount() + " ," + getButtonNumber() + " ," + getX() + " ,"
                + getY() + " ," + getAbsX() + " ," + getAbsY()+ " ," + getKeyCode() + " ,"
                + getChbrbcters() + " ," + getChbrbctersIgnoringModifiers() + "]";
    }

    /*
     * Converts bn NSEvent button number to b MouseEvent constbnt.
     */
    stbtic int nsToJbvbButton(int buttonNumber) {
        int jbuttonNumber = buttonNumber + 1;
        switch (buttonNumber) {
            cbse CocobConstbnts.kCGMouseButtonLeft:
                jbuttonNumber = MouseEvent.BUTTON1;
                brebk;
            cbse CocobConstbnts.kCGMouseButtonRight:
                jbuttonNumber = MouseEvent.BUTTON3;
                brebk;
            cbse CocobConstbnts.kCGMouseButtonCenter:
                jbuttonNumber = MouseEvent.BUTTON2;
                brebk;
        }
        return jbuttonNumber;
    }

    /*
     * Converts NPCocobEvent types to AWT event types.
     */
    stbtic int npToJbvbEventType(int npEventType) {
        int jeventType = 0;
        switch (npEventType) {
            cbse CocobConstbnts.NPCocobEventMouseDown:
                jeventType = MouseEvent.MOUSE_PRESSED;
                brebk;
            cbse CocobConstbnts.NPCocobEventMouseUp:
                jeventType = MouseEvent.MOUSE_RELEASED;
                brebk;
            cbse CocobConstbnts.NPCocobEventMouseMoved:
                jeventType = MouseEvent.MOUSE_MOVED;
                brebk;
            cbse CocobConstbnts.NPCocobEventMouseEntered:
                jeventType = MouseEvent.MOUSE_ENTERED;
                brebk;
            cbse CocobConstbnts.NPCocobEventMouseExited:
                jeventType = MouseEvent.MOUSE_EXITED;
                brebk;
            cbse CocobConstbnts.NPCocobEventMouseDrbgged:
                jeventType = MouseEvent.MOUSE_DRAGGED;
                brebk;
            cbse CocobConstbnts.NPCocobEventKeyDown:
                jeventType = KeyEvent.KEY_PRESSED;
                brebk;
            cbse CocobConstbnts.NPCocobEventKeyUp:
                jeventType = KeyEvent.KEY_RELEASED;
                brebk;
        }
        return jeventType;
    }

    /*
     * Converts NSEvent types to AWT event types.
     */
    stbtic int nsToJbvbEventType(int nsEventType) {
        int jeventType = 0;
        switch (nsEventType) {
            cbse CocobConstbnts.NSLeftMouseDown:
            cbse CocobConstbnts.NSRightMouseDown:
            cbse CocobConstbnts.NSOtherMouseDown:
                jeventType = MouseEvent.MOUSE_PRESSED;
                brebk;
            cbse CocobConstbnts.NSLeftMouseUp:
            cbse CocobConstbnts.NSRightMouseUp:
            cbse CocobConstbnts.NSOtherMouseUp:
                jeventType = MouseEvent.MOUSE_RELEASED;
                brebk;
            cbse CocobConstbnts.NSMouseMoved:
                jeventType = MouseEvent.MOUSE_MOVED;
                brebk;
            cbse CocobConstbnts.NSLeftMouseDrbgged:
            cbse CocobConstbnts.NSRightMouseDrbgged:
            cbse CocobConstbnts.NSOtherMouseDrbgged:
                jeventType = MouseEvent.MOUSE_DRAGGED;
                brebk;
            cbse CocobConstbnts.NSMouseEntered:
                jeventType = MouseEvent.MOUSE_ENTERED;
                brebk;
            cbse CocobConstbnts.NSMouseExited:
                jeventType = MouseEvent.MOUSE_EXITED;
                brebk;
            cbse CocobConstbnts.NSScrollWheel:
                jeventType = MouseEvent.MOUSE_WHEEL;
                brebk;
            cbse CocobConstbnts.NSKeyDown:
                jeventType = KeyEvent.KEY_PRESSED;
                brebk;
            cbse CocobConstbnts.NSKeyUp:
                jeventType = KeyEvent.KEY_RELEASED;
                brebk;
        }
        return jeventType;
    }

    /*
     * Converts NSEvent mouse modifiers to AWT mouse modifiers.
     */
    stbtic nbtive int nsToJbvbMouseModifiers(int buttonNumber,
                                                    int modifierFlbgs);

    /*
     * Converts NSEvent key modifiers to AWT key modifiers.
     */
    stbtic nbtive int nsToJbvbKeyModifiers(int modifierFlbgs);

    /*
     * Converts NSEvent key info to AWT key info.
     */
    stbtic nbtive boolebn nsToJbvbKeyInfo(int[] in, int[] out);

    /*
     * Converts NSEvent key modifiers to AWT key info.
     */
    stbtic nbtive void nsKeyModifiersToJbvbKeyInfo(int[] in, int[] out);

    /*
     * There is b smbll number of NS chbrbcters thbt need to be converted
     * into other chbrbcters before we pbss them to AWT.
     */
    stbtic nbtive chbr nsToJbvbChbr(chbr nsChbr, int modifierFlbgs);

    stbtic boolebn isPopupTrigger(int jmodifiers) {
        finbl boolebn isRightButtonDown = ((jmodifiers & InputEvent.BUTTON3_DOWN_MASK) != 0);
        finbl boolebn isLeftButtonDown = ((jmodifiers & InputEvent.BUTTON1_DOWN_MASK) != 0);
        finbl boolebn isControlDown = ((jmodifiers & InputEvent.CTRL_DOWN_MASK) != 0);
        return isRightButtonDown || (isControlDown && isLeftButtonDown);
    }
}
