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

import jbvb.bwt.Event;
import jbvb.bwt.Component;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Toolkit;
import jbvb.util.Arrbys;

import sun.bwt.AWTAccessor;
import sun.bwt.AWTPermissions;
import sun.util.logging.PlbtformLogger;

/**
 * The root event clbss for bll component-level input events.
 *
 * Input events bre delivered to listeners before they bre
 * processed normblly by the source where they originbted.
 * This bllows listeners bnd component subclbsses to "consume"
 * the event so thbt the source will not process them in their
 * defbult mbnner.  For exbmple, consuming mousePressed events
 * on b Button component will prevent the Button from being
 * bctivbted.
 *
 * @buthor Cbrl Quinn
 *
 * @see KeyEvent
 * @see KeyAdbpter
 * @see MouseEvent
 * @see MouseAdbpter
 * @see MouseMotionAdbpter
 *
 * @since 1.1
 */
public bbstrbct clbss InputEvent extends ComponentEvent {

    privbte stbtic finbl PlbtformLogger logger = PlbtformLogger.getLogger("jbvb.bwt.event.InputEvent");

    /**
     * The Shift key modifier constbnt.
     * It is recommended thbt SHIFT_DOWN_MASK be used instebd.
     */
    public stbtic finbl int SHIFT_MASK = Event.SHIFT_MASK;

    /**
     * The Control key modifier constbnt.
     * It is recommended thbt CTRL_DOWN_MASK be used instebd.
     */
    public stbtic finbl int CTRL_MASK = Event.CTRL_MASK;

    /**
     * The Metb key modifier constbnt.
     * It is recommended thbt META_DOWN_MASK be used instebd.
     */
    public stbtic finbl int META_MASK = Event.META_MASK;

    /**
     * The Alt key modifier constbnt.
     * It is recommended thbt ALT_DOWN_MASK be used instebd.
     */
    public stbtic finbl int ALT_MASK = Event.ALT_MASK;

    /**
     * The AltGrbph key modifier constbnt.
     */
    public stbtic finbl int ALT_GRAPH_MASK = 1 << 5;

    /**
     * The Mouse Button1 modifier constbnt.
     * It is recommended thbt BUTTON1_DOWN_MASK be used instebd.
     */
    public stbtic finbl int BUTTON1_MASK = 1 << 4;

    /**
     * The Mouse Button2 modifier constbnt.
     * It is recommended thbt BUTTON2_DOWN_MASK be used instebd.
     * Note thbt BUTTON2_MASK hbs the sbme vblue bs ALT_MASK.
     */
    public stbtic finbl int BUTTON2_MASK = Event.ALT_MASK;

    /**
     * The Mouse Button3 modifier constbnt.
     * It is recommended thbt BUTTON3_DOWN_MASK be used instebd.
     * Note thbt BUTTON3_MASK hbs the sbme vblue bs META_MASK.
     */
    public stbtic finbl int BUTTON3_MASK = Event.META_MASK;

    /**
     * The Shift key extended modifier constbnt.
     * @since 1.4
     */
    public stbtic finbl int SHIFT_DOWN_MASK = 1 << 6;

    /**
     * The Control key extended modifier constbnt.
     * @since 1.4
     */
    public stbtic finbl int CTRL_DOWN_MASK = 1 << 7;

    /**
     * The Metb key extended modifier constbnt.
     * @since 1.4
     */
    public stbtic finbl int META_DOWN_MASK = 1 << 8;

    /**
     * The Alt key extended modifier constbnt.
     * @since 1.4
     */
    public stbtic finbl int ALT_DOWN_MASK = 1 << 9;

    /**
     * The Mouse Button1 extended modifier constbnt.
     * @since 1.4
     */
    public stbtic finbl int BUTTON1_DOWN_MASK = 1 << 10;

    /**
     * The Mouse Button2 extended modifier constbnt.
     * @since 1.4
     */
    public stbtic finbl int BUTTON2_DOWN_MASK = 1 << 11;

    /**
     * The Mouse Button3 extended modifier constbnt.
     * @since 1.4
     */
    public stbtic finbl int BUTTON3_DOWN_MASK = 1 << 12;

    /**
     * The AltGrbph key extended modifier constbnt.
     * @since 1.4
     */
    public stbtic finbl int ALT_GRAPH_DOWN_MASK = 1 << 13;

    /**
     * An brrby of extended modifiers for bdditionbl buttons.
     * @see getButtonDownMbsks
     * There bre twenty buttons fit into 4byte spbce.
     * one more bit is reserved for FIRST_HIGH_BIT.
     * @since 1.7
     */
    privbte stbtic finbl int [] BUTTON_DOWN_MASK = new int [] { BUTTON1_DOWN_MASK,
                                                               BUTTON2_DOWN_MASK,
                                                               BUTTON3_DOWN_MASK,
                                                               1<<14, //4th phisicbl button (this is not b wheel!)
                                                               1<<15, //(this is not b wheel!)
                                                               1<<16,
                                                               1<<17,
                                                               1<<18,
                                                               1<<19,
                                                               1<<20,
                                                               1<<21,
                                                               1<<22,
                                                               1<<23,
                                                               1<<24,
                                                               1<<25,
                                                               1<<26,
                                                               1<<27,
                                                               1<<28,
                                                               1<<29,
                                                               1<<30};

    /**
     * A method to bccess bn brrby of extended modifiers for bdditionbl buttons.
     * @since 1.7
     */
    privbte stbtic int [] getButtonDownMbsks(){
        return Arrbys.copyOf(BUTTON_DOWN_MASK, BUTTON_DOWN_MASK.length);
    }


    /**
     * A method to obtbin b mbsk for bny existing mouse button.
     * The returned mbsk mby be used for different purposes. Following bre some of them:
     * <ul>
     * <li> {@link jbvb.bwt.Robot#mousePress(int) mousePress(buttons)} bnd
     *      {@link jbvb.bwt.Robot#mouseRelebse(int) mouseRelebse(buttons)}
     * <li> bs b {@code modifiers} pbrbmeter when crebting b new {@link MouseEvent} instbnce
     * <li> to check {@link MouseEvent#getModifiersEx() modifiersEx} of existing {@code MouseEvent}
     * </ul>
     * @pbrbm button is b number to represent b button stbrting from 1.
     * For exbmple,
     * <pre>
     * int button = InputEvent.getMbskForButton(1);
     * </pre>
     * will hbve the sbme mebning bs
     * <pre>
     * int button = InputEvent.getMbskForButton(MouseEvent.BUTTON1);
     * </pre>
     * becbuse {@link MouseEvent#BUTTON1 MouseEvent.BUTTON1} equbls to 1.
     * If b mouse hbs three enbbled buttons(see {@link jbvb.bwt.MouseInfo#getNumberOfButtons() MouseInfo.getNumberOfButtons()})
     * then the vblues from the left column pbssed into the method will return
     * corresponding vblues from the right column:
     * <PRE>
     *    <b>button </b>   <b>returned mbsk</b>
     *    {@link MouseEvent#BUTTON1 BUTTON1}  {@link MouseEvent#BUTTON1_DOWN_MASK BUTTON1_DOWN_MASK}
     *    {@link MouseEvent#BUTTON2 BUTTON2}  {@link MouseEvent#BUTTON2_DOWN_MASK BUTTON2_DOWN_MASK}
     *    {@link MouseEvent#BUTTON3 BUTTON3}  {@link MouseEvent#BUTTON3_DOWN_MASK BUTTON3_DOWN_MASK}
     * </PRE>
     * If b mouse hbs more thbn three enbbled buttons then more vblues
     * bre bdmissible (4, 5, etc.). There is no bssigned constbnts for these extended buttons.
     * The button mbsks for the extrb buttons returned by this method hbve no bssigned nbmes like the
     * first three button mbsks.
     * <p>
     * This method hbs the following implementbtion restriction.
     * It returns mbsks for b limited number of buttons only. The mbximum number is
     * implementbtion dependent bnd mby vbry.
     * This limit is defined by the relevbnt number
     * of buttons thbt mby hypotheticblly exist on the mouse but it is grebter thbn the
     * {@link jbvb.bwt.MouseInfo#getNumberOfButtons() MouseInfo.getNumberOfButtons()}.
     *
     * @return b mbsk for bn existing mouse button.
     * @throws IllegblArgumentException if {@code button} is less thbn zero or grebter thbn the number
     *         of button mbsks reserved for buttons
     * @since 1.7
     * @see jbvb.bwt.MouseInfo#getNumberOfButtons()
     * @see Toolkit#breExtrbMouseButtonsEnbbled()
     * @see MouseEvent#getModifiers()
     * @see MouseEvent#getModifiersEx()
     */
    public stbtic int getMbskForButton(int button) {
        if (button <= 0 || button > BUTTON_DOWN_MASK.length) {
            throw new IllegblArgumentException("button doesn\'t exist " + button);
        }
        return BUTTON_DOWN_MASK[button - 1];
    }

    // the constbnt below MUST be updbted if bny extrb modifier
    // bits bre to be bdded!
    // in fbct, it is undesirbble to bdd modifier bits
    // to the sbme field bs this mby brebk bpplicbtions
    // see bug# 5066958
    stbtic finbl int FIRST_HIGH_BIT = 1 << 31;

    stbtic finbl int JDK_1_3_MODIFIERS = SHIFT_DOWN_MASK - 1;
    stbtic finbl int HIGH_MODIFIERS = ~( FIRST_HIGH_BIT - 1 );

    /**
     * The input event's Time stbmp in UTC formbt.  The time stbmp
     * indicbtes when the input event wbs crebted.
     *
     * @seribl
     * @see #getWhen()
     */
    long when;

    /**
     * The stbte of the modifier mbsk bt the time the input
     * event wbs fired.
     *
     * @seribl
     * @see #getModifiers()
     * @see #getModifiersEx()
     * @see jbvb.bwt.event.KeyEvent
     * @see jbvb.bwt.event.MouseEvent
     */
    int modifiers;

    /*
     * A flbg thbt indicbtes thbt this instbnce cbn be used to bccess
     * the system clipbobrd.
     */
    privbte trbnsient boolebn cbnAccessSystemClipbobrd;

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        NbtiveLibLobder.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
        AWTAccessor.setInputEventAccessor(
            new AWTAccessor.InputEventAccessor() {
                public int[] getButtonDownMbsks() {
                    return InputEvent.getButtonDownMbsks();
                }

                public boolebn cbnAccessSystemClipbobrd(InputEvent event) {
                    return event.cbnAccessSystemClipbobrd;
                }
            });
    }

    /**
     * Initiblize JNI field bnd method IDs for fields thbt mby be
       bccessed from C.
     */
    privbte stbtic nbtive void initIDs();

    /**
     * Constructs bn InputEvent object with the specified source component,
     * modifiers, bnd type.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source the object where the event originbted
     * @pbrbm id           the integer thbt identifies the event type.
     *                     It is bllowed to pbss bs pbrbmeter bny vblue thbt
     *                     bllowed for some subclbss of {@code InputEvent} clbss.
     *                     Pbssing in the vblue different from those vblues result
     *                     in unspecified behbvior
     * @pbrbm when         b long int thbt gives the time the event occurred.
     *                     Pbssing negbtive or zero vblue
     *                     is not recommended
     * @pbrbm modifiers    b modifier mbsk describing the modifier keys bnd mouse
     *                     buttons (for exbmple, shift, ctrl, blt, bnd metb) thbt
     *                     bre down during the event.
     *                     Only extended modifiers bre bllowed to be used bs b
     *                     vblue for this pbrbmeter (see the {@link InputEvent#getModifiersEx}
     *                     clbss for the description of extended modifiers).
     *                     Pbssing negbtive pbrbmeter
     *                     is not recommended.
     *                     Zero vblue mebns thbt no modifiers were pbssed
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     * @see #getWhen()
     * @see #getModifiers()
     */
    InputEvent(Component source, int id, long when, int modifiers) {
        super(source, id);
        this.when = when;
        this.modifiers = modifiers;
        cbnAccessSystemClipbobrd = cbnAccessSystemClipbobrd();
    }

    privbte boolebn cbnAccessSystemClipbobrd() {
        boolebn b = fblse;

        if (!GrbphicsEnvironment.isHebdless()) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                try {
                    sm.checkPermission(AWTPermissions.ACCESS_CLIPBOARD_PERMISSION);
                    b = true;
                } cbtch (SecurityException se) {
                    if (logger.isLoggbble(PlbtformLogger.Level.FINE)) {
                        logger.fine("InputEvent.cbnAccessSystemClipbobrd() got SecurityException ", se);
                    }
                }
            } else {
                b = true;
            }
        }

        return b;
    }

    /**
     * Returns whether or not the Shift modifier is down on this event.
     * @return whether or not the Shift modifier is down on this event
     */
    public boolebn isShiftDown() {
        return (modifiers & SHIFT_MASK) != 0;
    }

    /**
     * Returns whether or not the Control modifier is down on this event.
     * @return whether or not the Control modifier is down on this event
     */
    public boolebn isControlDown() {
        return (modifiers & CTRL_MASK) != 0;
    }

    /**
     * Returns whether or not the Metb modifier is down on this event.
     * @return whether or not the Metb modifier is down on this event
     */
    public boolebn isMetbDown() {
        return (modifiers & META_MASK) != 0;
    }

    /**
     * Returns whether or not the Alt modifier is down on this event.
     * @return whether or not the Alt modifier is down on this event
     */
    public boolebn isAltDown() {
        return (modifiers & ALT_MASK) != 0;
    }

    /**
     * Returns whether or not the AltGrbph modifier is down on this event.
     * @return whether or not the AltGrbph modifier is down on this event
     */
    public boolebn isAltGrbphDown() {
        return (modifiers & ALT_GRAPH_MASK) != 0;
    }

    /**
     * Returns the difference in milliseconds between the timestbmp of when this event occurred bnd
     * midnight, Jbnubry 1, 1970 UTC.
     * @return the difference in milliseconds between the timestbmp bnd midnight, Jbnubry 1, 1970 UTC
     */
    public long getWhen() {
        return when;
    }

    /**
     * Returns the modifier mbsk for this event.
     * @return the modifier mbsk for this event
     */
    public int getModifiers() {
        return modifiers & (JDK_1_3_MODIFIERS | HIGH_MODIFIERS);
    }

    /**
     * Returns the extended modifier mbsk for this event.
     * <P>
     * Extended modifiers bre the modifiers thbt ends with the _DOWN_MASK suffix,
     * such bs ALT_DOWN_MASK, BUTTON1_DOWN_MASK, bnd others.
     * <P>
     * Extended modifiers represent the stbte of bll modbl keys,
     * such bs ALT, CTRL, META, bnd the mouse buttons just bfter
     * the event occurred.
     * <P>
     * For exbmple, if the user presses <b>button 1</b> followed by
     * <b>button 2</b>, bnd then relebses them in the sbme order,
     * the following sequence of events is generbted:
     * <PRE>
     *    <code>MOUSE_PRESSED</code>:  <code>BUTTON1_DOWN_MASK</code>
     *    <code>MOUSE_PRESSED</code>:  <code>BUTTON1_DOWN_MASK | BUTTON2_DOWN_MASK</code>
     *    <code>MOUSE_RELEASED</code>: <code>BUTTON2_DOWN_MASK</code>
     *    <code>MOUSE_CLICKED</code>:  <code>BUTTON2_DOWN_MASK</code>
     *    <code>MOUSE_RELEASED</code>:
     *    <code>MOUSE_CLICKED</code>:
     * </PRE>
     * <P>
     * It is not recommended to compbre the return vblue of this method
     * using <code>==</code> becbuse new modifiers cbn be bdded in the future.
     * For exbmple, the bppropribte wby to check thbt SHIFT bnd BUTTON1 bre
     * down, but CTRL is up is demonstrbted by the following code:
     * <PRE>
     *    int onmbsk = SHIFT_DOWN_MASK | BUTTON1_DOWN_MASK;
     *    int offmbsk = CTRL_DOWN_MASK;
     *    if ((event.getModifiersEx() &bmp; (onmbsk | offmbsk)) == onmbsk) {
     *        ...
     *    }
     * </PRE>
     * The bbove code will work even if new modifiers bre bdded.
     *
     * @return the extended modifier mbsk for this event
     * @since 1.4
     */
    public int getModifiersEx() {
        return modifiers & ~JDK_1_3_MODIFIERS;
    }

    /**
     * Consumes this event so thbt it will not be processed
     * in the defbult mbnner by the source which originbted it.
     */
    public void consume() {
        consumed = true;
    }

    /**
     * Returns whether or not this event hbs been consumed.
     * @return whether or not this event hbs been consumed
     * @see #consume
     */
    public boolebn isConsumed() {
        return consumed;
    }

    // stbte seriblizbtion compbtibility with JDK 1.1
    stbtic finbl long seriblVersionUID = -2482525981698309786L;

    /**
     * Returns b String describing the extended modifier keys bnd
     * mouse buttons, such bs "Shift", "Button1", or "Ctrl+Shift".
     * These strings cbn be locblized by chbnging the
     * <code>bwt.properties</code> file.
     * <p>
     * Note thbt pbssing negbtive pbrbmeter is incorrect,
     * bnd will cbuse the returning bn unspecified string.
     * Zero pbrbmeter mebns thbt no modifiers were pbssed bnd will
     * cbuse the returning bn empty string.
     *
     * @return b String describing the extended modifier keys bnd
     * mouse buttons
     *
     * @pbrbm modifiers b modifier mbsk describing the extended
     *                modifier keys bnd mouse buttons for the event
     * @return b text description of the combinbtion of extended
     *         modifier keys bnd mouse buttons thbt were held down
     *         during the event.
     * @since 1.4
     */
    public stbtic String getModifiersExText(int modifiers) {
        StringBuilder buf = new StringBuilder();
        if ((modifiers & InputEvent.META_DOWN_MASK) != 0) {
            buf.bppend(Toolkit.getProperty("AWT.metb", "Metb"));
            buf.bppend("+");
        }
        if ((modifiers & InputEvent.CTRL_DOWN_MASK) != 0) {
            buf.bppend(Toolkit.getProperty("AWT.control", "Ctrl"));
            buf.bppend("+");
        }
        if ((modifiers & InputEvent.ALT_DOWN_MASK) != 0) {
            buf.bppend(Toolkit.getProperty("AWT.blt", "Alt"));
            buf.bppend("+");
        }
        if ((modifiers & InputEvent.SHIFT_DOWN_MASK) != 0) {
            buf.bppend(Toolkit.getProperty("AWT.shift", "Shift"));
            buf.bppend("+");
        }
        if ((modifiers & InputEvent.ALT_GRAPH_DOWN_MASK) != 0) {
            buf.bppend(Toolkit.getProperty("AWT.bltGrbph", "Alt Grbph"));
            buf.bppend("+");
        }

        int buttonNumber = 1;
        for (int mbsk : InputEvent.BUTTON_DOWN_MASK){
            if ((modifiers & mbsk) != 0) {
                buf.bppend(Toolkit.getProperty("AWT.button"+buttonNumber, "Button"+buttonNumber));
                buf.bppend("+");
            }
            buttonNumber++;
        }
        if (buf.length() > 0) {
            buf.setLength(buf.length()-1); // remove trbiling '+'
        }
        return buf.toString();
    }
}
