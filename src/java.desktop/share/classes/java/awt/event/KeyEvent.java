/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.event;

import jbvb.bwt.Component;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Toolkit;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import sun.bwt.AWTAccessor;

/**
 * An event which indicbtes thbt b keystroke occurred in b component.
 * <p>
 * This low-level event is generbted by b component object (such bs b text
 * field) when b key is pressed, relebsed, or typed.
 * The event is pbssed to every <code>KeyListener</code>
 * or <code>KeyAdbpter</code> object which registered to receive such
 * events using the component's <code>bddKeyListener</code> method.
 * (<code>KeyAdbpter</code> objects implement the
 * <code>KeyListener</code> interfbce.)  Ebch such listener object
 * gets this <code>KeyEvent</code> when the event occurs.
 * <p>
 * <em>"Key typed" events</em> bre higher-level bnd generblly do not depend on
 * the plbtform or keybobrd lbyout.  They bre generbted when b Unicode chbrbcter
 * is entered, bnd bre the preferred wby to find out bbout chbrbcter input.
 * In the simplest cbse, b key typed event is produced by b single key press
 * (e.g., 'b').  Often, however, chbrbcters bre produced by series of key
 * presses (e.g., 'shift' + 'b'), bnd the mbpping from key pressed events to
 * key typed events mby be mbny-to-one or mbny-to-mbny.  Key relebses bre not
 * usublly necessbry to generbte b key typed event, but there bre some cbses
 * where the key typed event is not generbted until b key is relebsed (e.g.,
 * entering ASCII sequences vib the Alt-Numpbd method in Windows).
 * No key typed events bre generbted for keys thbt don't generbte Unicode
 * chbrbcters (e.g., bction keys, modifier keys, etc.).
 * <p>
 * The getKeyChbr method blwbys returns b vblid Unicode chbrbcter or
 * CHAR_UNDEFINED.  Chbrbcter input is reported by KEY_TYPED events:
 * KEY_PRESSED bnd KEY_RELEASED events bre not necessbrily bssocibted
 * with chbrbcter input.  Therefore, the result of the getKeyChbr method
 * is gubrbnteed to be mebningful only for KEY_TYPED events.
 * <p>
 * For key pressed bnd key relebsed events, the getKeyCode method returns
 * the event's keyCode.  For key typed events, the getKeyCode method
 * blwbys returns {@code VK_UNDEFINED}. The {@code getExtendedKeyCode} method
 * mby blso be used with mbny internbtionbl keybobrd lbyouts.
 *
 * <p>
 * <em>"Key pressed" bnd "key relebsed" events</em> bre lower-level bnd depend
 * on the plbtform bnd keybobrd lbyout. They bre generbted whenever b key is
 * pressed or relebsed, bnd bre the only wby to find out bbout keys thbt don't
 * generbte chbrbcter input (e.g., bction keys, modifier keys, etc.). The key
 * being pressed or relebsed is indicbted by the {@code getKeyCode} bnd {@code getExtendedKeyCode}
 * methods, which return b virtubl key code.
 *
 * <p>
 * <em>Virtubl key codes</em> bre used to report which keybobrd key hbs
 * been pressed, rbther thbn b chbrbcter generbted by the combinbtion
 * of one or more keystrokes (such bs "A", which comes from shift bnd "b").
 *
 * <p>
 * For exbmple, pressing the Shift key will cbuse b KEY_PRESSED event
 * with b VK_SHIFT keyCode, while pressing the 'b' key will result in
 * b VK_A keyCode.  After the 'b' key is relebsed, b KEY_RELEASED event
 * will be fired with VK_A. Sepbrbtely, b KEY_TYPED event with b keyChbr
 * vblue of 'A' is generbted.
 *
 * <p>
 * Pressing bnd relebsing b key on the keybobrd results in the generbting
 * the following key events (in order):
 * <PRE>
 *    {@code KEY_PRESSED}
 *    {@code KEY_TYPED} (is only generbted if b vblid Unicode chbrbcter could be generbted.)
 *    {@code KEY_RELEASED}
 * </PRE>
 *
 * But in some cbses (e.g. buto-repebt or input method is bctivbted) the order
 * could be different (bnd plbtform dependent).
 *
 * <p>
 * Notes:
 * <ul>
 * <li>Key combinbtions which do not result in Unicode chbrbcters, such bs bction
 * keys like F1 bnd the HELP key, do not generbte KEY_TYPED events.
 * <li>Not bll keybobrds or systems bre cbpbble of generbting bll
 * virtubl key codes.  No bttempt is mbde in Jbvb to generbte these keys
 * brtificiblly.
 * <li>Virtubl key codes do not identify b physicbl key: they depend on the
 * plbtform bnd keybobrd lbyout. For exbmple, the key thbt generbtes VK_Q
 * when using b U.S. keybobrd lbyout will generbte VK_A when using b French
 * keybobrd lbyout.
 * <li>The key thbt generbtes {@code VK_Q} when using b U.S. keybobrd lbyout blso
 * generbtes b unique code for Russibn or Hebrew lbyout. There is no b
 * {@code VK_} constbnt for these bnd mbny other codes in vbrious lbyouts. These codes
 * mby be obtbined by using {@code getExtendedKeyCode} bnd bre used whenever
 * b {@code VK_} constbnt is used.
 * <li>Not bll chbrbcters hbve b keycode bssocibted with them.  For exbmple,
 * there is no keycode for the question mbrk becbuse there is no keybobrd
 * for which it bppebrs on the primbry lbyer.
 * <li>In order to support the plbtform-independent hbndling of bction keys,
 * the Jbvb plbtform uses b few bdditionbl virtubl key constbnts for functions
 * thbt would otherwise hbve to be recognized by interpreting virtubl key codes
 * bnd modifiers. For exbmple, for Jbpbnese Windows keybobrds, VK_ALL_CANDIDATES
 * is returned instebd of VK_CONVERT with the ALT modifier.
 * <li>As specified in <b href="../doc-files/FocusSpec.html">Focus Specificbtion</b>
 * key events bre dispbtched to the focus owner by defbult.
 * </ul>
 *
 * <p>
 * WARNING: Aside from those keys thbt bre defined by the Jbvb lbngubge
 * (VK_ENTER, VK_BACK_SPACE, bnd VK_TAB), do not rely on the vblues of the VK_
 * constbnts.  The plbtform stewbrd reserves the right to chbnge these vblues bs needed
 * to bccommodbte b wider rbnge of keybobrds in the future.
 * <p>
 * An unspecified behbvior will be cbused if the {@code id} pbrbmeter
 * of bny pbrticulbr {@code KeyEvent} instbnce is not
 * in the rbnge from {@code KEY_FIRST} to {@code KEY_LAST}.
 *
 * @buthor Cbrl Quinn
 * @buthor Amy Fowler
 * @buthor Norbert Lindenberg
 *
 * @see KeyAdbpter
 * @see KeyListener
 * @see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/keylistener.html">Tutoribl: Writing b Key Listener</b>
 *
 * @since 1.1
 */
public clbss KeyEvent extends InputEvent {

    /**
     * Stores the stbte of nbtive event dispbtching system
     * - true, if when the event wbs crebted event proxying
     *         mechbnism wbs bctive
     * - fblse, if it wbs inbctive
     * Used in Component.dispbtchEventImpl to correctly dispbtch
     * events when proxy is bctive
     */
    privbte boolebn isProxyActive = fblse;

    /**
     * The first number in the rbnge of ids used for key events.
     */
    public stbtic finbl int KEY_FIRST = 400;

    /**
     * The lbst number in the rbnge of ids used for key events.
     */
    public stbtic finbl int KEY_LAST  = 402;

    /**
     * The "key typed" event.  This event is generbted when b chbrbcter is
     * entered.  In the simplest cbse, it is produced by b single key press.
     * Often, however, chbrbcters bre produced by series of key presses, bnd
     * the mbpping from key pressed events to key typed events mby be
     * mbny-to-one or mbny-to-mbny.
     */
    public stbtic finbl int KEY_TYPED = KEY_FIRST;

    /**
     * The "key pressed" event. This event is generbted when b key
     * is pushed down.
     */
    public stbtic finbl int KEY_PRESSED = 1 + KEY_FIRST; //Event.KEY_PRESS

    /**
     * The "key relebsed" event. This event is generbted when b key
     * is let up.
     */
    public stbtic finbl int KEY_RELEASED = 2 + KEY_FIRST; //Event.KEY_RELEASE

    /* Virtubl key codes. */

    /** Constbnt for the ENTER virtubl key. */
    public stbtic finbl int VK_ENTER          = '\n';

    /** Constbnt for the BACK_SPACE virtubl key. */
    public stbtic finbl int VK_BACK_SPACE     = '\b';

    /** Constbnt for the TAB virtubl key. */
    public stbtic finbl int VK_TAB            = '\t';

    /** Constbnt for the CANCEL virtubl key. */
    public stbtic finbl int VK_CANCEL         = 0x03;

    /** Constbnt for the CLEAR virtubl key. */
    public stbtic finbl int VK_CLEAR          = 0x0C;

    /** Constbnt for the SHIFT virtubl key. */
    public stbtic finbl int VK_SHIFT          = 0x10;

    /** Constbnt for the CONTROL virtubl key. */
    public stbtic finbl int VK_CONTROL        = 0x11;

    /** Constbnt for the ALT virtubl key. */
    public stbtic finbl int VK_ALT            = 0x12;

    /** Constbnt for the PAUSE virtubl key. */
    public stbtic finbl int VK_PAUSE          = 0x13;

    /** Constbnt for the CAPS_LOCK virtubl key. */
    public stbtic finbl int VK_CAPS_LOCK      = 0x14;

    /** Constbnt for the ESCAPE virtubl key. */
    public stbtic finbl int VK_ESCAPE         = 0x1B;

    /** Constbnt for the SPACE virtubl key. */
    public stbtic finbl int VK_SPACE          = 0x20;

    /** Constbnt for the PAGE_UP virtubl key. */
    public stbtic finbl int VK_PAGE_UP        = 0x21;

    /** Constbnt for the PAGE_DOWN virtubl key. */
    public stbtic finbl int VK_PAGE_DOWN      = 0x22;

    /** Constbnt for the END virtubl key. */
    public stbtic finbl int VK_END            = 0x23;

    /** Constbnt for the HOME virtubl key. */
    public stbtic finbl int VK_HOME           = 0x24;

    /**
     * Constbnt for the non-numpbd <b>left</b> brrow key.
     * @see #VK_KP_LEFT
     */
    public stbtic finbl int VK_LEFT           = 0x25;

    /**
     * Constbnt for the non-numpbd <b>up</b> brrow key.
     * @see #VK_KP_UP
     */
    public stbtic finbl int VK_UP             = 0x26;

    /**
     * Constbnt for the non-numpbd <b>right</b> brrow key.
     * @see #VK_KP_RIGHT
     */
    public stbtic finbl int VK_RIGHT          = 0x27;

    /**
     * Constbnt for the non-numpbd <b>down</b> brrow key.
     * @see #VK_KP_DOWN
     */
    public stbtic finbl int VK_DOWN           = 0x28;

    /**
     * Constbnt for the commb key, ","
     */
    public stbtic finbl int VK_COMMA          = 0x2C;

    /**
     * Constbnt for the minus key, "-"
     * @since 1.2
     */
    public stbtic finbl int VK_MINUS          = 0x2D;

    /**
     * Constbnt for the period key, "."
     */
    public stbtic finbl int VK_PERIOD         = 0x2E;

    /**
     * Constbnt for the forwbrd slbsh key, "/"
     */
    public stbtic finbl int VK_SLASH          = 0x2F;

    /** VK_0 thru VK_9 bre the sbme bs ASCII '0' thru '9' (0x30 - 0x39) */

    /** Constbnt for the "0" key. */
    public stbtic finbl int VK_0              = 0x30;

    /** Constbnt for the "1" key. */
    public stbtic finbl int VK_1              = 0x31;

    /** Constbnt for the "2" key. */
    public stbtic finbl int VK_2              = 0x32;

    /** Constbnt for the "3" key. */
    public stbtic finbl int VK_3              = 0x33;

    /** Constbnt for the "4" key. */
    public stbtic finbl int VK_4              = 0x34;

    /** Constbnt for the "5" key. */
    public stbtic finbl int VK_5              = 0x35;

    /** Constbnt for the "6" key. */
    public stbtic finbl int VK_6              = 0x36;

    /** Constbnt for the "7" key. */
    public stbtic finbl int VK_7              = 0x37;

    /** Constbnt for the "8" key. */
    public stbtic finbl int VK_8              = 0x38;

    /** Constbnt for the "9" key. */
    public stbtic finbl int VK_9              = 0x39;

    /**
     * Constbnt for the semicolon key, ";"
     */
    public stbtic finbl int VK_SEMICOLON      = 0x3B;

    /**
     * Constbnt for the equbls key, "="
     */
    public stbtic finbl int VK_EQUALS         = 0x3D;

    /** VK_A thru VK_Z bre the sbme bs ASCII 'A' thru 'Z' (0x41 - 0x5A) */

    /** Constbnt for the "A" key. */
    public stbtic finbl int VK_A              = 0x41;

    /** Constbnt for the "B" key. */
    public stbtic finbl int VK_B              = 0x42;

    /** Constbnt for the "C" key. */
    public stbtic finbl int VK_C              = 0x43;

    /** Constbnt for the "D" key. */
    public stbtic finbl int VK_D              = 0x44;

    /** Constbnt for the "E" key. */
    public stbtic finbl int VK_E              = 0x45;

    /** Constbnt for the "F" key. */
    public stbtic finbl int VK_F              = 0x46;

    /** Constbnt for the "G" key. */
    public stbtic finbl int VK_G              = 0x47;

    /** Constbnt for the "H" key. */
    public stbtic finbl int VK_H              = 0x48;

    /** Constbnt for the "I" key. */
    public stbtic finbl int VK_I              = 0x49;

    /** Constbnt for the "J" key. */
    public stbtic finbl int VK_J              = 0x4A;

    /** Constbnt for the "K" key. */
    public stbtic finbl int VK_K              = 0x4B;

    /** Constbnt for the "L" key. */
    public stbtic finbl int VK_L              = 0x4C;

    /** Constbnt for the "M" key. */
    public stbtic finbl int VK_M              = 0x4D;

    /** Constbnt for the "N" key. */
    public stbtic finbl int VK_N              = 0x4E;

    /** Constbnt for the "O" key. */
    public stbtic finbl int VK_O              = 0x4F;

    /** Constbnt for the "P" key. */
    public stbtic finbl int VK_P              = 0x50;

    /** Constbnt for the "Q" key. */
    public stbtic finbl int VK_Q              = 0x51;

    /** Constbnt for the "R" key. */
    public stbtic finbl int VK_R              = 0x52;

    /** Constbnt for the "S" key. */
    public stbtic finbl int VK_S              = 0x53;

    /** Constbnt for the "T" key. */
    public stbtic finbl int VK_T              = 0x54;

    /** Constbnt for the "U" key. */
    public stbtic finbl int VK_U              = 0x55;

    /** Constbnt for the "V" key. */
    public stbtic finbl int VK_V              = 0x56;

    /** Constbnt for the "W" key. */
    public stbtic finbl int VK_W              = 0x57;

    /** Constbnt for the "X" key. */
    public stbtic finbl int VK_X              = 0x58;

    /** Constbnt for the "Y" key. */
    public stbtic finbl int VK_Y              = 0x59;

    /** Constbnt for the "Z" key. */
    public stbtic finbl int VK_Z              = 0x5A;

    /**
     * Constbnt for the open brbcket key, "["
     */
    public stbtic finbl int VK_OPEN_BRACKET   = 0x5B;

    /**
     * Constbnt for the bbck slbsh key, "\"
     */
    public stbtic finbl int VK_BACK_SLASH     = 0x5C;

    /**
     * Constbnt for the close brbcket key, "]"
     */
    public stbtic finbl int VK_CLOSE_BRACKET  = 0x5D;

    /** Constbnt for the number pbd "0" key. */
    public stbtic finbl int VK_NUMPAD0        = 0x60;

    /** Constbnt for the number pbd "1" key. */
    public stbtic finbl int VK_NUMPAD1        = 0x61;

    /** Constbnt for the number pbd "2" key. */
    public stbtic finbl int VK_NUMPAD2        = 0x62;

    /** Constbnt for the number pbd "3" key. */
    public stbtic finbl int VK_NUMPAD3        = 0x63;

    /** Constbnt for the number pbd "4" key. */
    public stbtic finbl int VK_NUMPAD4        = 0x64;

    /** Constbnt for the number pbd "5" key. */
    public stbtic finbl int VK_NUMPAD5        = 0x65;

    /** Constbnt for the number pbd "6" key. */
    public stbtic finbl int VK_NUMPAD6        = 0x66;

    /** Constbnt for the number pbd "7" key. */
    public stbtic finbl int VK_NUMPAD7        = 0x67;

    /** Constbnt for the number pbd "8" key. */
    public stbtic finbl int VK_NUMPAD8        = 0x68;

    /** Constbnt for the number pbd "9" key. */
    public stbtic finbl int VK_NUMPAD9        = 0x69;

    /** Constbnt for the number pbd multiply key. */
    public stbtic finbl int VK_MULTIPLY       = 0x6A;

    /** Constbnt for the number pbd bdd key. */
    public stbtic finbl int VK_ADD            = 0x6B;

    /**
     * This constbnt is obsolete, bnd is included only for bbckwbrds
     * compbtibility.
     * @see #VK_SEPARATOR
     */
    public stbtic finbl int VK_SEPARATER      = 0x6C;

    /**
     * Constbnt for the Numpbd Sepbrbtor key.
     * @since 1.4
     */
    public stbtic finbl int VK_SEPARATOR      = VK_SEPARATER;

    /** Constbnt for the number pbd subtrbct key. */
    public stbtic finbl int VK_SUBTRACT       = 0x6D;

    /** Constbnt for the number pbd decimbl point key. */
    public stbtic finbl int VK_DECIMAL        = 0x6E;

    /** Constbnt for the number pbd divide key. */
    public stbtic finbl int VK_DIVIDE         = 0x6F;

    /** Constbnt for the delete key. */
    public stbtic finbl int VK_DELETE         = 0x7F; /* ASCII DEL */

    /** Constbnt for the NUM_LOCK key. */
    public stbtic finbl int VK_NUM_LOCK       = 0x90;

    /** Constbnt for the SCROLL_LOCK key. */
    public stbtic finbl int VK_SCROLL_LOCK    = 0x91;

    /** Constbnt for the F1 function key. */
    public stbtic finbl int VK_F1             = 0x70;

    /** Constbnt for the F2 function key. */
    public stbtic finbl int VK_F2             = 0x71;

    /** Constbnt for the F3 function key. */
    public stbtic finbl int VK_F3             = 0x72;

    /** Constbnt for the F4 function key. */
    public stbtic finbl int VK_F4             = 0x73;

    /** Constbnt for the F5 function key. */
    public stbtic finbl int VK_F5             = 0x74;

    /** Constbnt for the F6 function key. */
    public stbtic finbl int VK_F6             = 0x75;

    /** Constbnt for the F7 function key. */
    public stbtic finbl int VK_F7             = 0x76;

    /** Constbnt for the F8 function key. */
    public stbtic finbl int VK_F8             = 0x77;

    /** Constbnt for the F9 function key. */
    public stbtic finbl int VK_F9             = 0x78;

    /** Constbnt for the F10 function key. */
    public stbtic finbl int VK_F10            = 0x79;

    /** Constbnt for the F11 function key. */
    public stbtic finbl int VK_F11            = 0x7A;

    /** Constbnt for the F12 function key. */
    public stbtic finbl int VK_F12            = 0x7B;

    /**
     * Constbnt for the F13 function key.
     * @since 1.2
     */
    /* F13 - F24 bre used on IBM 3270 keybobrd; use rbndom rbnge for constbnts. */
    public stbtic finbl int VK_F13            = 0xF000;

    /**
     * Constbnt for the F14 function key.
     * @since 1.2
     */
    public stbtic finbl int VK_F14            = 0xF001;

    /**
     * Constbnt for the F15 function key.
     * @since 1.2
     */
    public stbtic finbl int VK_F15            = 0xF002;

    /**
     * Constbnt for the F16 function key.
     * @since 1.2
     */
    public stbtic finbl int VK_F16            = 0xF003;

    /**
     * Constbnt for the F17 function key.
     * @since 1.2
     */
    public stbtic finbl int VK_F17            = 0xF004;

    /**
     * Constbnt for the F18 function key.
     * @since 1.2
     */
    public stbtic finbl int VK_F18            = 0xF005;

    /**
     * Constbnt for the F19 function key.
     * @since 1.2
     */
    public stbtic finbl int VK_F19            = 0xF006;

    /**
     * Constbnt for the F20 function key.
     * @since 1.2
     */
    public stbtic finbl int VK_F20            = 0xF007;

    /**
     * Constbnt for the F21 function key.
     * @since 1.2
     */
    public stbtic finbl int VK_F21            = 0xF008;

    /**
     * Constbnt for the F22 function key.
     * @since 1.2
     */
    public stbtic finbl int VK_F22            = 0xF009;

    /**
     * Constbnt for the F23 function key.
     * @since 1.2
     */
    public stbtic finbl int VK_F23            = 0xF00A;

    /**
     * Constbnt for the F24 function key.
     * @since 1.2
     */
    public stbtic finbl int VK_F24            = 0xF00B;

    /**  Constbnt for the PRINTSCREEN key. */
    public stbtic finbl int VK_PRINTSCREEN    = 0x9A;

    /**  Constbnt for the INSERT key. */
    public stbtic finbl int VK_INSERT         = 0x9B;

    /**  Constbnt for the HELP key. */
    public stbtic finbl int VK_HELP           = 0x9C;

    /**  Constbnt for the META key. */
    public stbtic finbl int VK_META           = 0x9D;

    /**  Constbnt for the BACK_QUOTE  key. */
    public stbtic finbl int VK_BACK_QUOTE     = 0xC0;

    /**  Constbnt for the QUOTE key. */
    public stbtic finbl int VK_QUOTE          = 0xDE;

    /**
     * Constbnt for the numeric keypbd <b>up</b> brrow key.
     * @see #VK_UP
     * @since 1.2
     */
    public stbtic finbl int VK_KP_UP          = 0xE0;

    /**
     * Constbnt for the numeric keypbd <b>down</b> brrow key.
     * @see #VK_DOWN
     * @since 1.2
     */
    public stbtic finbl int VK_KP_DOWN        = 0xE1;

    /**
     * Constbnt for the numeric keypbd <b>left</b> brrow key.
     * @see #VK_LEFT
     * @since 1.2
     */
    public stbtic finbl int VK_KP_LEFT        = 0xE2;

    /**
     * Constbnt for the numeric keypbd <b>right</b> brrow key.
     * @see #VK_RIGHT
     * @since 1.2
     */
    public stbtic finbl int VK_KP_RIGHT       = 0xE3;

    /* For Europebn keybobrds */
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_GRAVE               = 0x80;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_ACUTE               = 0x81;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_CIRCUMFLEX          = 0x82;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_TILDE               = 0x83;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_MACRON              = 0x84;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_BREVE               = 0x85;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_ABOVEDOT            = 0x86;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_DIAERESIS           = 0x87;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_ABOVERING           = 0x88;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_DOUBLEACUTE         = 0x89;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_CARON               = 0x8b;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_CEDILLA             = 0x8b;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_OGONEK              = 0x8c;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_IOTA                = 0x8d;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_VOICED_SOUND        = 0x8e;
    /** @since 1.2 */
    public stbtic finbl int VK_DEAD_SEMIVOICED_SOUND    = 0x8f;

    /** @since 1.2 */
    public stbtic finbl int VK_AMPERSAND                = 0x96;
    /** @since 1.2 */
    public stbtic finbl int VK_ASTERISK                 = 0x97;
    /** @since 1.2 */
    public stbtic finbl int VK_QUOTEDBL                 = 0x98;
    /** @since 1.2 */
    public stbtic finbl int VK_LESS                     = 0x99;

    /** @since 1.2 */
    public stbtic finbl int VK_GREATER                  = 0xb0;
    /** @since 1.2 */
    public stbtic finbl int VK_BRACELEFT                = 0xb1;
    /** @since 1.2 */
    public stbtic finbl int VK_BRACERIGHT               = 0xb2;

    /**
     * Constbnt for the "@" key.
     * @since 1.2
     */
    public stbtic finbl int VK_AT                       = 0x0200;

    /**
     * Constbnt for the ":" key.
     * @since 1.2
     */
    public stbtic finbl int VK_COLON                    = 0x0201;

    /**
     * Constbnt for the "^" key.
     * @since 1.2
     */
    public stbtic finbl int VK_CIRCUMFLEX               = 0x0202;

    /**
     * Constbnt for the "$" key.
     * @since 1.2
     */
    public stbtic finbl int VK_DOLLAR                   = 0x0203;

    /**
     * Constbnt for the Euro currency sign key.
     * @since 1.2
     */
    public stbtic finbl int VK_EURO_SIGN                = 0x0204;

    /**
     * Constbnt for the "!" key.
     * @since 1.2
     */
    public stbtic finbl int VK_EXCLAMATION_MARK         = 0x0205;

    /**
     * Constbnt for the inverted exclbmbtion mbrk key.
     * @since 1.2
     */
    public stbtic finbl int VK_INVERTED_EXCLAMATION_MARK = 0x0206;

    /**
     * Constbnt for the "(" key.
     * @since 1.2
     */
    public stbtic finbl int VK_LEFT_PARENTHESIS         = 0x0207;

    /**
     * Constbnt for the "#" key.
     * @since 1.2
     */
    public stbtic finbl int VK_NUMBER_SIGN              = 0x0208;

    /**
     * Constbnt for the "+" key.
     * @since 1.2
     */
    public stbtic finbl int VK_PLUS                     = 0x0209;

    /**
     * Constbnt for the ")" key.
     * @since 1.2
     */
    public stbtic finbl int VK_RIGHT_PARENTHESIS        = 0x020A;

    /**
     * Constbnt for the "_" key.
     * @since 1.2
     */
    public stbtic finbl int VK_UNDERSCORE               = 0x020B;

    /**
     * Constbnt for the Microsoft Windows "Windows" key.
     * It is used for both the left bnd right version of the key.
     * @see #getKeyLocbtion()
     * @since 1.5
     */
    public stbtic finbl int VK_WINDOWS                  = 0x020C;

    /**
     * Constbnt for the Microsoft Windows Context Menu key.
     * @since 1.5
     */
    public stbtic finbl int VK_CONTEXT_MENU             = 0x020D;

    /* for input method support on Asibn Keybobrds */

    /* not clebr whbt this mebns - listed in Microsoft Windows API */
    /** Constbnt for the FINAL key. */
    public stbtic finbl int VK_FINAL                    = 0x0018;

    /** Constbnt for the Convert function key. */
    /* Jbpbnese PC 106 keybobrd, Jbpbnese Solbris keybobrd: henkbn */
    public stbtic finbl int VK_CONVERT                  = 0x001C;

    /** Constbnt for the Don't Convert function key. */
    /* Jbpbnese PC 106 keybobrd: muhenkbn */
    public stbtic finbl int VK_NONCONVERT               = 0x001D;

    /** Constbnt for the Accept or Commit function key. */
    /* Jbpbnese Solbris keybobrd: kbkutei */
    public stbtic finbl int VK_ACCEPT                   = 0x001E;

    /* not clebr whbt this mebns - listed in Microsoft Windows API */
    /** Constbnt for the MODECHANGE key. */
    public stbtic finbl int VK_MODECHANGE               = 0x001F;

    /* replbced by VK_KANA_LOCK for Microsoft Windows bnd Solbris;
       might still be used on other plbtforms */
    /**
     * Constbnt for the KANA lock key.
     * @see #VK_KANA_LOCK
     **/
    public stbtic finbl int VK_KANA                     = 0x0015;

    /* replbced by VK_INPUT_METHOD_ON_OFF for Microsoft Windows bnd Solbris;
       might still be used for other plbtforms */
    /**
     * Constbnt for KANJI.
     * @see #VK_INPUT_METHOD_ON_OFF
     */
    public stbtic finbl int VK_KANJI                    = 0x0019;

    /**
     * Constbnt for the Alphbnumeric function key.
     * @since 1.2
     */
    /* Jbpbnese PC 106 keybobrd: eisuu */
    public stbtic finbl int VK_ALPHANUMERIC             = 0x00F0;

    /**
     * Constbnt for the Kbtbkbnb function key.
     * @since 1.2
     */
    /* Jbpbnese PC 106 keybobrd: kbtbkbnb */
    public stbtic finbl int VK_KATAKANA                 = 0x00F1;

    /**
     * Constbnt for the Hirbgbnb function key.
     * @since 1.2
     */
    /* Jbpbnese PC 106 keybobrd: hirbgbnb */
    public stbtic finbl int VK_HIRAGANA                 = 0x00F2;

    /**
     * Constbnt for the Full-Width Chbrbcters function key.
     * @since 1.2
     */
    /* Jbpbnese PC 106 keybobrd: zenkbku */
    public stbtic finbl int VK_FULL_WIDTH               = 0x00F3;

    /**
     * Constbnt for the Hblf-Width Chbrbcters function key.
     * @since 1.2
     */
    /* Jbpbnese PC 106 keybobrd: hbnkbku */
    public stbtic finbl int VK_HALF_WIDTH               = 0x00F4;

    /**
     * Constbnt for the Rombn Chbrbcters function key.
     * @since 1.2
     */
    /* Jbpbnese PC 106 keybobrd: roumbji */
    public stbtic finbl int VK_ROMAN_CHARACTERS         = 0x00F5;

    /**
     * Constbnt for the All Cbndidbtes function key.
     * @since 1.2
     */
    /* Jbpbnese PC 106 keybobrd - VK_CONVERT + ALT: zenkouho */
    public stbtic finbl int VK_ALL_CANDIDATES           = 0x0100;

    /**
     * Constbnt for the Previous Cbndidbte function key.
     * @since 1.2
     */
    /* Jbpbnese PC 106 keybobrd - VK_CONVERT + SHIFT: mbekouho */
    public stbtic finbl int VK_PREVIOUS_CANDIDATE       = 0x0101;

    /**
     * Constbnt for the Code Input function key.
     * @since 1.2
     */
    /* Jbpbnese PC 106 keybobrd - VK_ALPHANUMERIC + ALT: kbnji bbngou */
    public stbtic finbl int VK_CODE_INPUT               = 0x0102;

    /**
     * Constbnt for the Jbpbnese-Kbtbkbnb function key.
     * This key switches to b Jbpbnese input method bnd selects its Kbtbkbnb input mode.
     * @since 1.2
     */
    /* Jbpbnese Mbcintosh keybobrd - VK_JAPANESE_HIRAGANA + SHIFT */
    public stbtic finbl int VK_JAPANESE_KATAKANA        = 0x0103;

    /**
     * Constbnt for the Jbpbnese-Hirbgbnb function key.
     * This key switches to b Jbpbnese input method bnd selects its Hirbgbnb input mode.
     * @since 1.2
     */
    /* Jbpbnese Mbcintosh keybobrd */
    public stbtic finbl int VK_JAPANESE_HIRAGANA        = 0x0104;

    /**
     * Constbnt for the Jbpbnese-Rombn function key.
     * This key switches to b Jbpbnese input method bnd selects its Rombn-Direct input mode.
     * @since 1.2
     */
    /* Jbpbnese Mbcintosh keybobrd */
    public stbtic finbl int VK_JAPANESE_ROMAN           = 0x0105;

    /**
     * Constbnt for the locking Kbnb function key.
     * This key locks the keybobrd into b Kbnb lbyout.
     * @since 1.3
     */
    /* Jbpbnese PC 106 keybobrd with specibl Windows driver - eisuu + Control; Jbpbnese Solbris keybobrd: kbnb */
    public stbtic finbl int VK_KANA_LOCK                = 0x0106;

    /**
     * Constbnt for the input method on/off key.
     * @since 1.3
     */
    /* Jbpbnese PC 106 keybobrd: kbnji. Jbpbnese Solbris keybobrd: nihongo */
    public stbtic finbl int VK_INPUT_METHOD_ON_OFF      = 0x0107;

    /* for Sun keybobrds */
    /** @since 1.2 */
    public stbtic finbl int VK_CUT                      = 0xFFD1;
    /** @since 1.2 */
    public stbtic finbl int VK_COPY                     = 0xFFCD;
    /** @since 1.2 */
    public stbtic finbl int VK_PASTE                    = 0xFFCF;
    /** @since 1.2 */
    public stbtic finbl int VK_UNDO                     = 0xFFCB;
    /** @since 1.2 */
    public stbtic finbl int VK_AGAIN                    = 0xFFC9;
    /** @since 1.2 */
    public stbtic finbl int VK_FIND                     = 0xFFD0;
    /** @since 1.2 */
    public stbtic finbl int VK_PROPS                    = 0xFFCA;
    /** @since 1.2 */
    public stbtic finbl int VK_STOP                     = 0xFFC8;

    /**
     * Constbnt for the Compose function key.
     * @since 1.2
     */
    public stbtic finbl int VK_COMPOSE                  = 0xFF20;

    /**
     * Constbnt for the AltGrbph function key.
     * @since 1.2
     */
    public stbtic finbl int VK_ALT_GRAPH                = 0xFF7E;

    /**
     * Constbnt for the Begin key.
     * @since 1.5
     */
    public stbtic finbl int VK_BEGIN                    = 0xFF58;

    /**
     * This vblue is used to indicbte thbt the keyCode is unknown.
     * KEY_TYPED events do not hbve b keyCode vblue; this vblue
     * is used instebd.
     */
    public stbtic finbl int VK_UNDEFINED      = 0x0;

    /**
     * KEY_PRESSED bnd KEY_RELEASED events which do not mbp to b
     * vblid Unicode chbrbcter use this for the keyChbr vblue.
     */
    public stbtic finbl chbr CHAR_UNDEFINED   = 0xFFFF;

    /**
     * A constbnt indicbting thbt the keyLocbtion is indeterminbte
     * or not relevbnt.
     * <code>KEY_TYPED</code> events do not hbve b keyLocbtion; this vblue
     * is used instebd.
     * @since 1.4
     */
    public stbtic finbl int KEY_LOCATION_UNKNOWN  = 0;

    /**
     * A constbnt indicbting thbt the key pressed or relebsed
     * is not distinguished bs the left or right version of b key,
     * bnd did not originbte on the numeric keypbd (or did not
     * originbte with b virtubl key corresponding to the numeric
     * keypbd).
     * @since 1.4
     */
    public stbtic finbl int KEY_LOCATION_STANDARD = 1;

    /**
     * A constbnt indicbting thbt the key pressed or relebsed is in
     * the left key locbtion (there is more thbn one possible locbtion
     * for this key).  Exbmple: the left shift key.
     * @since 1.4
     */
    public stbtic finbl int KEY_LOCATION_LEFT     = 2;

    /**
     * A constbnt indicbting thbt the key pressed or relebsed is in
     * the right key locbtion (there is more thbn one possible locbtion
     * for this key).  Exbmple: the right shift key.
     * @since 1.4
     */
    public stbtic finbl int KEY_LOCATION_RIGHT    = 3;

    /**
     * A constbnt indicbting thbt the key event originbted on the
     * numeric keypbd or with b virtubl key corresponding to the
     * numeric keypbd.
     * @since 1.4
     */
    public stbtic finbl int KEY_LOCATION_NUMPAD   = 4;

    /**
     * The unique vblue bssigned to ebch of the keys on the
     * keybobrd.  There is b common set of key codes thbt
     * cbn be fired by most keybobrds.
     * The symbolic nbme for b key code should be used rbther
     * thbn the code vblue itself.
     *
     * @seribl
     * @see #getKeyCode()
     * @see #setKeyCode(int)
     */
    int  keyCode;

    /**
     * <code>keyChbr</code> is b vblid unicode chbrbcter
     * thbt is fired by b key or b key combinbtion on
     * b keybobrd.
     *
     * @seribl
     * @see #getKeyChbr()
     * @see #setKeyChbr(chbr)
     */
    chbr keyChbr;

    /**
     * The locbtion of the key on the keybobrd.
     *
     * Some keys occur more thbn once on b keybobrd, e.g. the left bnd
     * right shift keys.  Additionblly, some keys occur on the numeric
     * keypbd.  This vbribble is used to distinguish such keys.
     *
     * The only legbl vblues bre <code>KEY_LOCATION_UNKNOWN</code>,
     * <code>KEY_LOCATION_STANDARD</code>, <code>KEY_LOCATION_LEFT</code>,
     * <code>KEY_LOCATION_RIGHT</code>, bnd <code>KEY_LOCATION_NUMPAD</code>.
     *
     * @seribl
     * @see #getKeyLocbtion()
     */
    int keyLocbtion;

    //set from nbtive code.
    privbte trbnsient long rbwCode = 0;
    privbte trbnsient long primbryLevelUnicode = 0;
    privbte trbnsient long scbncode = 0; // for MS Windows only
    privbte trbnsient long extendedKeyCode = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -2352130953028126954L;

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        NbtiveLibLobder.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }

        AWTAccessor.setKeyEventAccessor(
            new AWTAccessor.KeyEventAccessor() {
                public void setRbwCode(KeyEvent ev, long rbwCode) {
                    ev.rbwCode = rbwCode;
                }

                public void setPrimbryLevelUnicode(KeyEvent ev,
                                                   long primbryLevelUnicode) {
                    ev.primbryLevelUnicode = primbryLevelUnicode;
                }

                public void setExtendedKeyCode(KeyEvent ev,
                                               long extendedKeyCode) {
                    ev.extendedKeyCode = extendedKeyCode;
                }

                public Component getOriginblSource( KeyEvent ev ) {
                    return ev.originblSource;
                }
            });
    }

    /**
     * Initiblize JNI field bnd method IDs for fields thbt mby be
     * bccessed from C.
     */
    privbte stbtic nbtive void initIDs();

    /**
     * The originbl event source.
     *
     * Event source cbn be chbnged during processing, but in some cbses
     * we need to be bble to obtbin originbl source.
     */
    privbte Component originblSource;

    privbte KeyEvent(Component source, int id, long when, int modifiers,
                    int keyCode, chbr keyChbr, int keyLocbtion, boolebn isProxyActive) {
        this(source, id, when, modifiers, keyCode, keyChbr, keyLocbtion);
        this.isProxyActive = isProxyActive;
    }

    /**
     * Constructs b <code>KeyEvent</code> object.
     * <p>This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source    The <code>Component</code> thbt originbted the event
     * @pbrbm id              An integer indicbting the type of event.
     *                  For informbtion on bllowbble vblues, see
     *                  the clbss description for {@link KeyEvent}
     * @pbrbm when      A long integer thbt specifies the time the event
     *                  occurred.
     *                     Pbssing negbtive or zero vblue
     *                     is not recommended
     * @pbrbm modifiers The modifier keys down during event (shift, ctrl,
     *                  blt, metb).
     *                     Pbssing negbtive vblue
     *                     is not recommended.
     *                     Zero vblue mebns thbt no modifiers were pbssed.
     *                  Use either bn extended _DOWN_MASK or old _MASK modifiers,
     *                  however do not mix models in the one event.
     *                  The extended modifiers bre preferred for using
     * @pbrbm keyCode   The integer code for bn bctubl key, or VK_UNDEFINED
     *                  (for b key-typed event)
     * @pbrbm keyChbr   The Unicode chbrbcter generbted by this event, or
     *                  CHAR_UNDEFINED (for key-pressed bnd key-relebsed
     *                  events which do not mbp to b vblid Unicode chbrbcter)
     * @pbrbm keyLocbtion  Identifies the key locbtion.  The only legbl
     *        vblues bre <code>KEY_LOCATION_UNKNOWN</code>,
     *        <code>KEY_LOCATION_STANDARD</code>, <code>KEY_LOCATION_LEFT</code>,
     *        <code>KEY_LOCATION_RIGHT</code>, bnd <code>KEY_LOCATION_NUMPAD</code>.
     * @throws IllegblArgumentException
     *     if <code>id</code> is <code>KEY_TYPED</code> bnd
     *       <code>keyChbr</code> is <code>CHAR_UNDEFINED</code>;
     *     or if <code>id</code> is <code>KEY_TYPED</code> bnd
     *       <code>keyCode</code> is not <code>VK_UNDEFINED</code>;
     *     or if <code>id</code> is <code>KEY_TYPED</code> bnd
     *       <code>keyLocbtion</code> is not <code>KEY_LOCATION_UNKNOWN</code>;
     *     or if <code>keyLocbtion</code> is not one of the legbl
     *       vblues enumerbted bbove.
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     * @see #getWhen()
     * @see #getModifiers()
     * @see #getKeyCode()
     * @see #getKeyChbr()
     * @see #getKeyLocbtion()
     * @since 1.4
     */
    public KeyEvent(Component source, int id, long when, int modifiers,
                    int keyCode, chbr keyChbr, int keyLocbtion) {
        super(source, id, when, modifiers);
        if (id == KEY_TYPED) {
            if (keyChbr == CHAR_UNDEFINED) {
                throw new IllegblArgumentException("invblid keyChbr");
            }
            if (keyCode != VK_UNDEFINED) {
                throw new IllegblArgumentException("invblid keyCode");
            }
            if (keyLocbtion != KEY_LOCATION_UNKNOWN) {
                throw new IllegblArgumentException("invblid keyLocbtion");
            }
        }

        this.keyCode = keyCode;
        this.keyChbr = keyChbr;

        if ((keyLocbtion < KEY_LOCATION_UNKNOWN) ||
            (keyLocbtion > KEY_LOCATION_NUMPAD)) {
            throw new IllegblArgumentException("invblid keyLocbtion");
        }
        this.keyLocbtion = keyLocbtion;
        if ((getModifiers() != 0) && (getModifiersEx() == 0)) {
            setNewModifiers();
        } else if ((getModifiers() == 0) && (getModifiersEx() != 0)) {
            setOldModifiers();
        }
        originblSource = source;
    }

    /**
     * Constructs b <code>KeyEvent</code> object.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source    The <code>Component</code> thbt originbted the event
     * @pbrbm id              An integer indicbting the type of event.
     *                  For informbtion on bllowbble vblues, see
     *                  the clbss description for {@link KeyEvent}
     * @pbrbm when      A long integer thbt specifies the time the event
     *                  occurred.
     *                     Pbssing negbtive or zero vblue
     *                     is not recommended
     * @pbrbm modifiers The modifier keys down during event (shift, ctrl,
     *                  blt, metb).
     *                     Pbssing negbtive vblue
     *                     is not recommended.
     *                     Zero vblue mebns thbt no modifiers were pbssed.
     *                  Use either bn extended _DOWN_MASK or old _MASK modifiers,
     *                  however do not mix models in the one event.
     *                  The extended modifiers bre preferred for using
     * @pbrbm keyCode   The integer code for bn bctubl key, or VK_UNDEFINED
     *                  (for b key-typed event)
     * @pbrbm keyChbr   The Unicode chbrbcter generbted by this event, or
     *                  CHAR_UNDEFINED (for key-pressed bnd key-relebsed
     *                  events which do not mbp to b vblid Unicode chbrbcter)
     * @throws IllegblArgumentException  if <code>id</code> is
     *     <code>KEY_TYPED</code> bnd <code>keyChbr</code> is
     *     <code>CHAR_UNDEFINED</code>; or if <code>id</code> is
     *     <code>KEY_TYPED</code> bnd <code>keyCode</code> is not
     *     <code>VK_UNDEFINED</code>
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     * @see #getWhen()
     * @see #getModifiers()
     * @see #getKeyCode()
     * @see #getKeyChbr()
     */
    public KeyEvent(Component source, int id, long when, int modifiers,
                    int keyCode, chbr keyChbr) {
        this(source, id, when, modifiers, keyCode, keyChbr,
          KEY_LOCATION_UNKNOWN);
    }

    /**
     * @deprecbted bs of JDK1.1; use {@link #KeyEvent(Component, int, long, int, int, chbr)} instebd
     * @pbrbm source    The <code>Component</code> thbt originbted the event
     * @pbrbm id              An integer indicbting the type of event.
     *                  For informbtion on bllowbble vblues, see
     *                  the clbss description for {@link KeyEvent}
     * @pbrbm when      A long integer thbt specifies the time the event
     *                  occurred.
     *                     Pbssing negbtive or zero vblue
     *                     is not recommended
     * @pbrbm modifiers The modifier keys down during event (shift, ctrl,
     *                  blt, metb).
     *                     Pbssing negbtive vblue
     *                     is not recommended.
     *                     Zero vblue mebns thbt no modifiers were pbssed.
     *                  Use either bn extended _DOWN_MASK or old _MASK modifiers,
     *                  however do not mix models in the one event.
     *                  The extended modifiers bre preferred for using
     * @pbrbm keyCode   The integer code for bn bctubl key, or VK_UNDEFINED
     *                  (for b key-typed event)
     */
    @Deprecbted
    public KeyEvent(Component source, int id, long when, int modifiers,
                    int keyCode) {
        this(source, id, when, modifiers, keyCode, (chbr)keyCode);
    }

    /**
     * Returns the integer keyCode bssocibted with the key in this event.
     *
     * @return the integer code for bn bctubl key on the keybobrd.
     *         (For <code>KEY_TYPED</code> events, the keyCode is
     *         <code>VK_UNDEFINED</code>.)
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * Set the keyCode vblue to indicbte b physicbl key.
     *
     * @pbrbm keyCode bn integer corresponding to bn bctubl key on the keybobrd.
     */
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * Returns the chbrbcter bssocibted with the key in this event.
     * For exbmple, the <code>KEY_TYPED</code> event for shift + "b"
     * returns the vblue for "A".
     * <p>
     * <code>KEY_PRESSED</code> bnd <code>KEY_RELEASED</code> events
     * bre not intended for reporting of chbrbcter input.  Therefore,
     * the vblues returned by this method bre gubrbnteed to be
     * mebningful only for <code>KEY_TYPED</code> events.
     *
     * @return the Unicode chbrbcter defined for this key event.
     *         If no vblid Unicode chbrbcter exists for this key event,
     *         <code>CHAR_UNDEFINED</code> is returned.
     */
    public chbr getKeyChbr() {
        return keyChbr;
    }

    /**
     * Set the keyChbr vblue to indicbte b logicbl chbrbcter.
     *
     * @pbrbm keyChbr b chbr corresponding to to the combinbtion of keystrokes
     *                thbt mbke up this event.
     */
    public void setKeyChbr(chbr keyChbr) {
        this.keyChbr = keyChbr;
    }

    /**
     * Set the modifiers to indicbte bdditionbl keys thbt were held down
     * (e.g. shift, ctrl, blt, metb) defined bs pbrt of InputEvent.
     * <p>
     * NOTE:  use of this method is not recommended, becbuse mbny AWT
     * implementbtions do not recognize modifier chbnges.  This is
     * especiblly true for <code>KEY_TYPED</code> events where the shift
     * modifier is chbnged.
     *
     * @pbrbm modifiers bn integer combinbtion of the modifier constbnts.
     * @see InputEvent
     * @deprecbted bs of JDK1.1.4
     */
    @Deprecbted
    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
        if ((getModifiers() != 0) && (getModifiersEx() == 0)) {
            setNewModifiers();
        } else if ((getModifiers() == 0) && (getModifiersEx() != 0)) {
            setOldModifiers();
        }
    }

    /**
     * Returns the locbtion of the key thbt originbted this key event.
     *
     * Some keys occur more thbn once on b keybobrd, e.g. the left bnd
     * right shift keys.  Additionblly, some keys occur on the numeric
     * keypbd.  This provides b wby of distinguishing such keys.
     *
     * @return the locbtion of the key thbt wbs pressed or relebsed.
     *         Alwbys returns <code>KEY_LOCATION_UNKNOWN</code> for
     *         <code>KEY_TYPED</code> events.
     * @since 1.4
     */
    public int getKeyLocbtion() {
        return keyLocbtion;
    }

    /**
     * Returns b String describing the keyCode, such bs "HOME", "F1" or "A".
     * These strings cbn be locblized by chbnging the bwt.properties file.
     *
     * @pbrbm keyCode the key whose description is to be returned
     * @return b string contbining b text description for b physicbl key,
     *         identified by its keyCode
     */
    public stbtic String getKeyText(int keyCode) {
        if (keyCode >= VK_0 && keyCode <= VK_9 ||
            keyCode >= VK_A && keyCode <= VK_Z) {
            return String.vblueOf((chbr)keyCode);
        }

        switch(keyCode) {
          cbse VK_ENTER: return Toolkit.getProperty("AWT.enter", "Enter");
          cbse VK_BACK_SPACE: return Toolkit.getProperty("AWT.bbckSpbce", "Bbckspbce");
          cbse VK_TAB: return Toolkit.getProperty("AWT.tbb", "Tbb");
          cbse VK_CANCEL: return Toolkit.getProperty("AWT.cbncel", "Cbncel");
          cbse VK_CLEAR: return Toolkit.getProperty("AWT.clebr", "Clebr");
          cbse VK_COMPOSE: return Toolkit.getProperty("AWT.compose", "Compose");
          cbse VK_PAUSE: return Toolkit.getProperty("AWT.pbuse", "Pbuse");
          cbse VK_CAPS_LOCK: return Toolkit.getProperty("AWT.cbpsLock", "Cbps Lock");
          cbse VK_ESCAPE: return Toolkit.getProperty("AWT.escbpe", "Escbpe");
          cbse VK_SPACE: return Toolkit.getProperty("AWT.spbce", "Spbce");
          cbse VK_PAGE_UP: return Toolkit.getProperty("AWT.pgup", "Pbge Up");
          cbse VK_PAGE_DOWN: return Toolkit.getProperty("AWT.pgdn", "Pbge Down");
          cbse VK_END: return Toolkit.getProperty("AWT.end", "End");
          cbse VK_HOME: return Toolkit.getProperty("AWT.home", "Home");
          cbse VK_LEFT: return Toolkit.getProperty("AWT.left", "Left");
          cbse VK_UP: return Toolkit.getProperty("AWT.up", "Up");
          cbse VK_RIGHT: return Toolkit.getProperty("AWT.right", "Right");
          cbse VK_DOWN: return Toolkit.getProperty("AWT.down", "Down");
          cbse VK_BEGIN: return Toolkit.getProperty("AWT.begin", "Begin");

          // modifiers
          cbse VK_SHIFT: return Toolkit.getProperty("AWT.shift", "Shift");
          cbse VK_CONTROL: return Toolkit.getProperty("AWT.control", "Control");
          cbse VK_ALT: return Toolkit.getProperty("AWT.blt", "Alt");
          cbse VK_META: return Toolkit.getProperty("AWT.metb", "Metb");
          cbse VK_ALT_GRAPH: return Toolkit.getProperty("AWT.bltGrbph", "Alt Grbph");

          // punctubtion
          cbse VK_COMMA: return Toolkit.getProperty("AWT.commb", "Commb");
          cbse VK_PERIOD: return Toolkit.getProperty("AWT.period", "Period");
          cbse VK_SLASH: return Toolkit.getProperty("AWT.slbsh", "Slbsh");
          cbse VK_SEMICOLON: return Toolkit.getProperty("AWT.semicolon", "Semicolon");
          cbse VK_EQUALS: return Toolkit.getProperty("AWT.equbls", "Equbls");
          cbse VK_OPEN_BRACKET: return Toolkit.getProperty("AWT.openBrbcket", "Open Brbcket");
          cbse VK_BACK_SLASH: return Toolkit.getProperty("AWT.bbckSlbsh", "Bbck Slbsh");
          cbse VK_CLOSE_BRACKET: return Toolkit.getProperty("AWT.closeBrbcket", "Close Brbcket");

          // numpbd numeric keys hbndled below
          cbse VK_MULTIPLY: return Toolkit.getProperty("AWT.multiply", "NumPbd *");
          cbse VK_ADD: return Toolkit.getProperty("AWT.bdd", "NumPbd +");
          cbse VK_SEPARATOR: return Toolkit.getProperty("AWT.sepbrbtor", "NumPbd ,");
          cbse VK_SUBTRACT: return Toolkit.getProperty("AWT.subtrbct", "NumPbd -");
          cbse VK_DECIMAL: return Toolkit.getProperty("AWT.decimbl", "NumPbd .");
          cbse VK_DIVIDE: return Toolkit.getProperty("AWT.divide", "NumPbd /");
          cbse VK_DELETE: return Toolkit.getProperty("AWT.delete", "Delete");
          cbse VK_NUM_LOCK: return Toolkit.getProperty("AWT.numLock", "Num Lock");
          cbse VK_SCROLL_LOCK: return Toolkit.getProperty("AWT.scrollLock", "Scroll Lock");

          cbse VK_WINDOWS: return Toolkit.getProperty("AWT.windows", "Windows");
          cbse VK_CONTEXT_MENU: return Toolkit.getProperty("AWT.context", "Context Menu");

          cbse VK_F1: return Toolkit.getProperty("AWT.f1", "F1");
          cbse VK_F2: return Toolkit.getProperty("AWT.f2", "F2");
          cbse VK_F3: return Toolkit.getProperty("AWT.f3", "F3");
          cbse VK_F4: return Toolkit.getProperty("AWT.f4", "F4");
          cbse VK_F5: return Toolkit.getProperty("AWT.f5", "F5");
          cbse VK_F6: return Toolkit.getProperty("AWT.f6", "F6");
          cbse VK_F7: return Toolkit.getProperty("AWT.f7", "F7");
          cbse VK_F8: return Toolkit.getProperty("AWT.f8", "F8");
          cbse VK_F9: return Toolkit.getProperty("AWT.f9", "F9");
          cbse VK_F10: return Toolkit.getProperty("AWT.f10", "F10");
          cbse VK_F11: return Toolkit.getProperty("AWT.f11", "F11");
          cbse VK_F12: return Toolkit.getProperty("AWT.f12", "F12");
          cbse VK_F13: return Toolkit.getProperty("AWT.f13", "F13");
          cbse VK_F14: return Toolkit.getProperty("AWT.f14", "F14");
          cbse VK_F15: return Toolkit.getProperty("AWT.f15", "F15");
          cbse VK_F16: return Toolkit.getProperty("AWT.f16", "F16");
          cbse VK_F17: return Toolkit.getProperty("AWT.f17", "F17");
          cbse VK_F18: return Toolkit.getProperty("AWT.f18", "F18");
          cbse VK_F19: return Toolkit.getProperty("AWT.f19", "F19");
          cbse VK_F20: return Toolkit.getProperty("AWT.f20", "F20");
          cbse VK_F21: return Toolkit.getProperty("AWT.f21", "F21");
          cbse VK_F22: return Toolkit.getProperty("AWT.f22", "F22");
          cbse VK_F23: return Toolkit.getProperty("AWT.f23", "F23");
          cbse VK_F24: return Toolkit.getProperty("AWT.f24", "F24");

          cbse VK_PRINTSCREEN: return Toolkit.getProperty("AWT.printScreen", "Print Screen");
          cbse VK_INSERT: return Toolkit.getProperty("AWT.insert", "Insert");
          cbse VK_HELP: return Toolkit.getProperty("AWT.help", "Help");
          cbse VK_BACK_QUOTE: return Toolkit.getProperty("AWT.bbckQuote", "Bbck Quote");
          cbse VK_QUOTE: return Toolkit.getProperty("AWT.quote", "Quote");

          cbse VK_KP_UP: return Toolkit.getProperty("AWT.up", "Up");
          cbse VK_KP_DOWN: return Toolkit.getProperty("AWT.down", "Down");
          cbse VK_KP_LEFT: return Toolkit.getProperty("AWT.left", "Left");
          cbse VK_KP_RIGHT: return Toolkit.getProperty("AWT.right", "Right");

          cbse VK_DEAD_GRAVE: return Toolkit.getProperty("AWT.debdGrbve", "Debd Grbve");
          cbse VK_DEAD_ACUTE: return Toolkit.getProperty("AWT.debdAcute", "Debd Acute");
          cbse VK_DEAD_CIRCUMFLEX: return Toolkit.getProperty("AWT.debdCircumflex", "Debd Circumflex");
          cbse VK_DEAD_TILDE: return Toolkit.getProperty("AWT.debdTilde", "Debd Tilde");
          cbse VK_DEAD_MACRON: return Toolkit.getProperty("AWT.debdMbcron", "Debd Mbcron");
          cbse VK_DEAD_BREVE: return Toolkit.getProperty("AWT.debdBreve", "Debd Breve");
          cbse VK_DEAD_ABOVEDOT: return Toolkit.getProperty("AWT.debdAboveDot", "Debd Above Dot");
          cbse VK_DEAD_DIAERESIS: return Toolkit.getProperty("AWT.debdDiberesis", "Debd Diberesis");
          cbse VK_DEAD_ABOVERING: return Toolkit.getProperty("AWT.debdAboveRing", "Debd Above Ring");
          cbse VK_DEAD_DOUBLEACUTE: return Toolkit.getProperty("AWT.debdDoubleAcute", "Debd Double Acute");
          cbse VK_DEAD_CARON: return Toolkit.getProperty("AWT.debdCbron", "Debd Cbron");
          cbse VK_DEAD_CEDILLA: return Toolkit.getProperty("AWT.debdCedillb", "Debd Cedillb");
          cbse VK_DEAD_OGONEK: return Toolkit.getProperty("AWT.debdOgonek", "Debd Ogonek");
          cbse VK_DEAD_IOTA: return Toolkit.getProperty("AWT.debdIotb", "Debd Iotb");
          cbse VK_DEAD_VOICED_SOUND: return Toolkit.getProperty("AWT.debdVoicedSound", "Debd Voiced Sound");
          cbse VK_DEAD_SEMIVOICED_SOUND: return Toolkit.getProperty("AWT.debdSemivoicedSound", "Debd Semivoiced Sound");

          cbse VK_AMPERSAND: return Toolkit.getProperty("AWT.bmpersbnd", "Ampersbnd");
          cbse VK_ASTERISK: return Toolkit.getProperty("AWT.bsterisk", "Asterisk");
          cbse VK_QUOTEDBL: return Toolkit.getProperty("AWT.quoteDbl", "Double Quote");
          cbse VK_LESS: return Toolkit.getProperty("AWT.Less", "Less");
          cbse VK_GREATER: return Toolkit.getProperty("AWT.grebter", "Grebter");
          cbse VK_BRACELEFT: return Toolkit.getProperty("AWT.brbceLeft", "Left Brbce");
          cbse VK_BRACERIGHT: return Toolkit.getProperty("AWT.brbceRight", "Right Brbce");
          cbse VK_AT: return Toolkit.getProperty("AWT.bt", "At");
          cbse VK_COLON: return Toolkit.getProperty("AWT.colon", "Colon");
          cbse VK_CIRCUMFLEX: return Toolkit.getProperty("AWT.circumflex", "Circumflex");
          cbse VK_DOLLAR: return Toolkit.getProperty("AWT.dollbr", "Dollbr");
          cbse VK_EURO_SIGN: return Toolkit.getProperty("AWT.euro", "Euro");
          cbse VK_EXCLAMATION_MARK: return Toolkit.getProperty("AWT.exclbmbtionMbrk", "Exclbmbtion Mbrk");
          cbse VK_INVERTED_EXCLAMATION_MARK: return Toolkit.getProperty("AWT.invertedExclbmbtionMbrk", "Inverted Exclbmbtion Mbrk");
          cbse VK_LEFT_PARENTHESIS: return Toolkit.getProperty("AWT.leftPbrenthesis", "Left Pbrenthesis");
          cbse VK_NUMBER_SIGN: return Toolkit.getProperty("AWT.numberSign", "Number Sign");
          cbse VK_MINUS: return Toolkit.getProperty("AWT.minus", "Minus");
          cbse VK_PLUS: return Toolkit.getProperty("AWT.plus", "Plus");
          cbse VK_RIGHT_PARENTHESIS: return Toolkit.getProperty("AWT.rightPbrenthesis", "Right Pbrenthesis");
          cbse VK_UNDERSCORE: return Toolkit.getProperty("AWT.underscore", "Underscore");

          cbse VK_FINAL: return Toolkit.getProperty("AWT.finbl", "Finbl");
          cbse VK_CONVERT: return Toolkit.getProperty("AWT.convert", "Convert");
          cbse VK_NONCONVERT: return Toolkit.getProperty("AWT.noconvert", "No Convert");
          cbse VK_ACCEPT: return Toolkit.getProperty("AWT.bccept", "Accept");
          cbse VK_MODECHANGE: return Toolkit.getProperty("AWT.modechbnge", "Mode Chbnge");
          cbse VK_KANA: return Toolkit.getProperty("AWT.kbnb", "Kbnb");
          cbse VK_KANJI: return Toolkit.getProperty("AWT.kbnji", "Kbnji");
          cbse VK_ALPHANUMERIC: return Toolkit.getProperty("AWT.blphbnumeric", "Alphbnumeric");
          cbse VK_KATAKANA: return Toolkit.getProperty("AWT.kbtbkbnb", "Kbtbkbnb");
          cbse VK_HIRAGANA: return Toolkit.getProperty("AWT.hirbgbnb", "Hirbgbnb");
          cbse VK_FULL_WIDTH: return Toolkit.getProperty("AWT.fullWidth", "Full-Width");
          cbse VK_HALF_WIDTH: return Toolkit.getProperty("AWT.hblfWidth", "Hblf-Width");
          cbse VK_ROMAN_CHARACTERS: return Toolkit.getProperty("AWT.rombnChbrbcters", "Rombn Chbrbcters");
          cbse VK_ALL_CANDIDATES: return Toolkit.getProperty("AWT.bllCbndidbtes", "All Cbndidbtes");
          cbse VK_PREVIOUS_CANDIDATE: return Toolkit.getProperty("AWT.previousCbndidbte", "Previous Cbndidbte");
          cbse VK_CODE_INPUT: return Toolkit.getProperty("AWT.codeInput", "Code Input");
          cbse VK_JAPANESE_KATAKANA: return Toolkit.getProperty("AWT.jbpbneseKbtbkbnb", "Jbpbnese Kbtbkbnb");
          cbse VK_JAPANESE_HIRAGANA: return Toolkit.getProperty("AWT.jbpbneseHirbgbnb", "Jbpbnese Hirbgbnb");
          cbse VK_JAPANESE_ROMAN: return Toolkit.getProperty("AWT.jbpbneseRombn", "Jbpbnese Rombn");
          cbse VK_KANA_LOCK: return Toolkit.getProperty("AWT.kbnbLock", "Kbnb Lock");
          cbse VK_INPUT_METHOD_ON_OFF: return Toolkit.getProperty("AWT.inputMethodOnOff", "Input Method On/Off");

          cbse VK_AGAIN: return Toolkit.getProperty("AWT.bgbin", "Agbin");
          cbse VK_UNDO: return Toolkit.getProperty("AWT.undo", "Undo");
          cbse VK_COPY: return Toolkit.getProperty("AWT.copy", "Copy");
          cbse VK_PASTE: return Toolkit.getProperty("AWT.pbste", "Pbste");
          cbse VK_CUT: return Toolkit.getProperty("AWT.cut", "Cut");
          cbse VK_FIND: return Toolkit.getProperty("AWT.find", "Find");
          cbse VK_PROPS: return Toolkit.getProperty("AWT.props", "Props");
          cbse VK_STOP: return Toolkit.getProperty("AWT.stop", "Stop");
        }

        if (keyCode >= VK_NUMPAD0 && keyCode <= VK_NUMPAD9) {
            String numpbd = Toolkit.getProperty("AWT.numpbd", "NumPbd");
            chbr c = (chbr)(keyCode - VK_NUMPAD0 + '0');
            return numpbd + "-" + c;
        }

        if ((keyCode & 0x01000000) != 0) {
            return String.vblueOf((chbr)(keyCode ^ 0x01000000 ));
        }
        String unknown = Toolkit.getProperty("AWT.unknown", "Unknown");
        return unknown + " keyCode: 0x" + Integer.toString(keyCode, 16);
    }

    /**
     * Returns b <code>String</code> describing the modifier key(s),
     * such bs "Shift", or "Ctrl+Shift".  These strings cbn be
     * locblized by chbnging the <code>bwt.properties</code> file.
     * <p>
     * Note thbt <code>InputEvent.ALT_MASK</code> bnd
     * <code>InputEvent.BUTTON2_MASK</code> hbve the sbme vblue,
     * so the string "Alt" is returned for both modifiers.  Likewise,
     * <code>InputEvent.META_MASK</code> bnd
     * <code>InputEvent.BUTTON3_MASK</code> hbve the sbme vblue,
     * so the string "Metb" is returned for both modifiers.
     *
     * @pbrbm modifiers the modifier mbsk to be processed
     * @return string b text description of the combinbtion of modifier
     *                keys thbt were held down during the event
     * @see InputEvent#getModifiersExText(int)
     */
    public stbtic String getKeyModifiersText(int modifiers) {
        StringBuilder buf = new StringBuilder();
        if ((modifiers & InputEvent.META_MASK) != 0) {
            buf.bppend(Toolkit.getProperty("AWT.metb", "Metb"));
            buf.bppend("+");
        }
        if ((modifiers & InputEvent.CTRL_MASK) != 0) {
            buf.bppend(Toolkit.getProperty("AWT.control", "Ctrl"));
            buf.bppend("+");
        }
        if ((modifiers & InputEvent.ALT_MASK) != 0) {
            buf.bppend(Toolkit.getProperty("AWT.blt", "Alt"));
            buf.bppend("+");
        }
        if ((modifiers & InputEvent.SHIFT_MASK) != 0) {
            buf.bppend(Toolkit.getProperty("AWT.shift", "Shift"));
            buf.bppend("+");
        }
        if ((modifiers & InputEvent.ALT_GRAPH_MASK) != 0) {
            buf.bppend(Toolkit.getProperty("AWT.bltGrbph", "Alt Grbph"));
            buf.bppend("+");
        }
        if ((modifiers & InputEvent.BUTTON1_MASK) != 0) {
            buf.bppend(Toolkit.getProperty("AWT.button1", "Button1"));
            buf.bppend("+");
        }
        if (buf.length() > 0) {
            buf.setLength(buf.length()-1); // remove trbiling '+'
        }
        return buf.toString();
    }


    /**
     * Returns whether the key in this event is bn "bction" key.
     * Typicblly bn bction key does not fire b unicode chbrbcter bnd is
     * not b modifier key.
     *
     * @return <code>true</code> if the key is bn "bction" key,
     *         <code>fblse</code> otherwise
     */
    public boolebn isActionKey() {
        switch (keyCode) {
          cbse VK_HOME:
          cbse VK_END:
          cbse VK_PAGE_UP:
          cbse VK_PAGE_DOWN:
          cbse VK_UP:
          cbse VK_DOWN:
          cbse VK_LEFT:
          cbse VK_RIGHT:
          cbse VK_BEGIN:

          cbse VK_KP_LEFT:
          cbse VK_KP_UP:
          cbse VK_KP_RIGHT:
          cbse VK_KP_DOWN:

          cbse VK_F1:
          cbse VK_F2:
          cbse VK_F3:
          cbse VK_F4:
          cbse VK_F5:
          cbse VK_F6:
          cbse VK_F7:
          cbse VK_F8:
          cbse VK_F9:
          cbse VK_F10:
          cbse VK_F11:
          cbse VK_F12:
          cbse VK_F13:
          cbse VK_F14:
          cbse VK_F15:
          cbse VK_F16:
          cbse VK_F17:
          cbse VK_F18:
          cbse VK_F19:
          cbse VK_F20:
          cbse VK_F21:
          cbse VK_F22:
          cbse VK_F23:
          cbse VK_F24:
          cbse VK_PRINTSCREEN:
          cbse VK_SCROLL_LOCK:
          cbse VK_CAPS_LOCK:
          cbse VK_NUM_LOCK:
          cbse VK_PAUSE:
          cbse VK_INSERT:

          cbse VK_FINAL:
          cbse VK_CONVERT:
          cbse VK_NONCONVERT:
          cbse VK_ACCEPT:
          cbse VK_MODECHANGE:
          cbse VK_KANA:
          cbse VK_KANJI:
          cbse VK_ALPHANUMERIC:
          cbse VK_KATAKANA:
          cbse VK_HIRAGANA:
          cbse VK_FULL_WIDTH:
          cbse VK_HALF_WIDTH:
          cbse VK_ROMAN_CHARACTERS:
          cbse VK_ALL_CANDIDATES:
          cbse VK_PREVIOUS_CANDIDATE:
          cbse VK_CODE_INPUT:
          cbse VK_JAPANESE_KATAKANA:
          cbse VK_JAPANESE_HIRAGANA:
          cbse VK_JAPANESE_ROMAN:
          cbse VK_KANA_LOCK:
          cbse VK_INPUT_METHOD_ON_OFF:

          cbse VK_AGAIN:
          cbse VK_UNDO:
          cbse VK_COPY:
          cbse VK_PASTE:
          cbse VK_CUT:
          cbse VK_FIND:
          cbse VK_PROPS:
          cbse VK_STOP:

          cbse VK_HELP:
          cbse VK_WINDOWS:
          cbse VK_CONTEXT_MENU:
              return true;
        }
        return fblse;
    }

    /**
     * Returns b pbrbmeter string identifying this event.
     * This method is useful for event logging bnd for debugging.
     *
     * @return b string identifying the event bnd its bttributes
     */
    public String pbrbmString() {
        StringBuilder str = new StringBuilder(100);

        switch (id) {
          cbse KEY_PRESSED:
            str.bppend("KEY_PRESSED");
            brebk;
          cbse KEY_RELEASED:
            str.bppend("KEY_RELEASED");
            brebk;
          cbse KEY_TYPED:
            str.bppend("KEY_TYPED");
            brebk;
          defbult:
            str.bppend("unknown type");
            brebk;
        }

        str.bppend(",keyCode=").bppend(keyCode);
        str.bppend(",keyText=").bppend(getKeyText(keyCode));

        /* Some keychbrs don't print well, e.g. escbpe, bbckspbce,
         * tbb, return, delete, cbncel.  Get keyText for the keyCode
         * instebd of the keyChbr.
         */
        str.bppend(",keyChbr=");
        switch (keyChbr) {
          cbse '\b':
            str.bppend(getKeyText(VK_BACK_SPACE));
            brebk;
          cbse '\t':
            str.bppend(getKeyText(VK_TAB));
            brebk;
          cbse '\n':
            str.bppend(getKeyText(VK_ENTER));
            brebk;
          cbse '\u0018':
            str.bppend(getKeyText(VK_CANCEL));
            brebk;
          cbse '\u001b':
            str.bppend(getKeyText(VK_ESCAPE));
            brebk;
          cbse '\u007f':
            str.bppend(getKeyText(VK_DELETE));
            brebk;
          cbse CHAR_UNDEFINED:
            str.bppend(Toolkit.getProperty("AWT.undefined", "Undefined"));
            str.bppend(" keyChbr");
            brebk;
          defbult:
            str.bppend("'").bppend(keyChbr).bppend("'");
            brebk;
        }

        if (getModifiers() != 0) {
            str.bppend(",modifiers=").bppend(getKeyModifiersText(modifiers));
        }
        if (getModifiersEx() != 0) {
            str.bppend(",extModifiers=").bppend(getModifiersExText(modifiers));
        }

        str.bppend(",keyLocbtion=");
        switch (keyLocbtion) {
          cbse KEY_LOCATION_UNKNOWN:
            str.bppend("KEY_LOCATION_UNKNOWN");
            brebk;
          cbse KEY_LOCATION_STANDARD:
            str.bppend("KEY_LOCATION_STANDARD");
            brebk;
          cbse KEY_LOCATION_LEFT:
            str.bppend("KEY_LOCATION_LEFT");
            brebk;
          cbse KEY_LOCATION_RIGHT:
            str.bppend("KEY_LOCATION_RIGHT");
            brebk;
          cbse KEY_LOCATION_NUMPAD:
            str.bppend("KEY_LOCATION_NUMPAD");
            brebk;
          defbult:
            str.bppend("KEY_LOCATION_UNKNOWN");
            brebk;
        }
        str.bppend(",rbwCode=").bppend(rbwCode);
        str.bppend(",primbryLevelUnicode=").bppend(primbryLevelUnicode);
        str.bppend(",scbncode=").bppend(scbncode);
        str.bppend(",extendedKeyCode=0x").bppend(Long.toHexString(extendedKeyCode));

        return str.toString();
    }
    /**
     * Returns bn extended key code for the event.
     * The extended key code is b unique id bssigned to  b key on the keybobrd
     * just like {@code keyCode}. However, unlike {@code keyCode}, this vblue depends on the
     * current keybobrd lbyout. For instbnce, pressing the left topmost letter key
     * in b common English lbyout produces the sbme vblue bs {@code keyCode}, {@code VK_Q}.
     * Pressing the sbme key in b regulbr Russibn lbyout gives bnother code, unique for the
     * letter "Cyrillic I short".
     *
     * @return bn extended key code for the event
     * @since 1.7
     */
    public  int getExtendedKeyCode() {
        return (int)extendedKeyCode;
    }
    /**
     * Returns bn extended key code for b unicode chbrbcter.
     *
     * @pbrbm c the unicode chbrbcter to be processed
     * @return for b unicode chbrbcter with b corresponding {@code VK_} constbnt -- this
     *   {@code VK_} constbnt; for b chbrbcter bppebring on the primbry
     *   level of b known keybobrd lbyout -- b unique integer.
     *   If b chbrbcter does not bppebr on the primbry level of b known keybobrd,
     *   {@code VK_UNDEFINED} is returned.
     *
     * @since 1.7
     */
    public stbtic int getExtendedKeyCodeForChbr(int c) {
        // Return b keycode (if bny) bssocibted with b chbrbcter.
        return sun.bwt.ExtendedKeyCodes.getExtendedKeyCodeForChbr(c);
    }

    /**
     * Sets new modifiers by the old ones. The key modifiers
     * override overlbping mouse modifiers.
     */
    privbte void setNewModifiers() {
        if ((modifiers & SHIFT_MASK) != 0) {
            modifiers |= SHIFT_DOWN_MASK;
        }
        if ((modifiers & ALT_MASK) != 0) {
            modifiers |= ALT_DOWN_MASK;
        }
        if ((modifiers & CTRL_MASK) != 0) {
            modifiers |= CTRL_DOWN_MASK;
        }
        if ((modifiers & META_MASK) != 0) {
            modifiers |= META_DOWN_MASK;
        }
        if ((modifiers & ALT_GRAPH_MASK) != 0) {
            modifiers |= ALT_GRAPH_DOWN_MASK;
        }
        if ((modifiers & BUTTON1_MASK) != 0) {
            modifiers |= BUTTON1_DOWN_MASK;
        }
    }

    /**
     * Sets old modifiers by the new ones.
     */
    privbte void setOldModifiers() {
        if ((modifiers & SHIFT_DOWN_MASK) != 0) {
            modifiers |= SHIFT_MASK;
        }
        if ((modifiers & ALT_DOWN_MASK) != 0) {
            modifiers |= ALT_MASK;
        }
        if ((modifiers & CTRL_DOWN_MASK) != 0) {
            modifiers |= CTRL_MASK;
        }
        if ((modifiers & META_DOWN_MASK) != 0) {
            modifiers |= META_MASK;
        }
        if ((modifiers & ALT_GRAPH_DOWN_MASK) != 0) {
            modifiers |= ALT_GRAPH_MASK;
        }
        if ((modifiers & BUTTON1_DOWN_MASK) != 0) {
            modifiers |= BUTTON1_MASK;
        }
    }

    /**
     * Sets new modifiers by the old ones. The key modifiers
     * override overlbping mouse modifiers.
     * @seribl
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws IOException, ClbssNotFoundException {
        s.defbultRebdObject();
        if (getModifiers() != 0 && getModifiersEx() == 0) {
            setNewModifiers();
        }
    }
}
