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

pbckbge jbvb.bwt;

import jbvb.bwt.event.InputEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.DbtbBufferInt;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.peer.RobotPeer;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import sun.bwt.AWTPermissions;
import sun.bwt.ComponentFbctory;
import sun.bwt.SunToolkit;
import sun.bwt.imbge.SunWritbbleRbster;

/**
 * This clbss is used to generbte nbtive system input events
 * for the purposes of test butombtion, self-running demos, bnd
 * other bpplicbtions where control of the mouse bnd keybobrd
 * is needed. The primbry purpose of Robot is to fbcilitbte
 * butombted testing of Jbvb plbtform implementbtions.
 * <p>
 * Using the clbss to generbte input events differs from posting
 * events to the AWT event queue or AWT components in thbt the
 * events bre generbted in the plbtform's nbtive input
 * queue. For exbmple, <code>Robot.mouseMove</code> will bctublly move
 * the mouse cursor instebd of just generbting mouse move events.
 * <p>
 * Note thbt some plbtforms require specibl privileges or extensions
 * to bccess low-level input control. If the current plbtform configurbtion
 * does not bllow input control, bn <code>AWTException</code> will be thrown
 * when trying to construct Robot objects. For exbmple, X-Window systems
 * will throw the exception if the XTEST 2.2 stbndbrd extension is not supported
 * (or not enbbled) by the X server.
 * <p>
 * Applicbtions thbt use Robot for purposes other thbn self-testing should
 * hbndle these error conditions grbcefully.
 *
 * @buthor      Robi Khbn
 * @since       1.3
 */
public clbss Robot {
    privbte stbtic finbl int MAX_DELAY = 60000;
    privbte RobotPeer peer;
    privbte boolebn isAutoWbitForIdle = fblse;
    privbte int butoDelby = 0;
    privbte stbtic int LEGAL_BUTTON_MASK = 0;

    privbte DirectColorModel screenCbpCM = null;

    /**
     * Constructs b Robot object in the coordinbte system of the primbry screen.
     *
     * @throws  AWTException if the plbtform configurbtion does not bllow
     * low-level input control.  This exception is blwbys thrown when
     * GrbphicsEnvironment.isHebdless() returns true
     * @throws  SecurityException if <code>crebteRobot</code> permission is not grbnted
     * @see     jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see     SecurityMbnbger#checkPermission
     * @see     AWTPermission
     */
    public Robot() throws AWTException {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new AWTException("hebdless environment");
        }
        init(GrbphicsEnvironment.getLocblGrbphicsEnvironment()
            .getDefbultScreenDevice());
    }

    /**
     * Crebtes b Robot for the given screen device. Coordinbtes pbssed
     * to Robot method cblls like mouseMove bnd crebteScreenCbpture will
     * be interpreted bs being in the sbme coordinbte system bs the
     * specified screen. Note thbt depending on the plbtform configurbtion,
     * multiple screens mby either:
     * <ul>
     * <li>shbre the sbme coordinbte system to form b combined virtubl screen</li>
     * <li>use different coordinbte systems to bct bs independent screens</li>
     * </ul>
     * This constructor is mebnt for the lbtter cbse.
     * <p>
     * If screen devices bre reconfigured such thbt the coordinbte system is
     * bffected, the behbvior of existing Robot objects is undefined.
     *
     * @pbrbm screen    A screen GrbphicsDevice indicbting the coordinbte
     *                  system the Robot will operbte in.
     * @throws  AWTException if the plbtform configurbtion does not bllow
     * low-level input control.  This exception is blwbys thrown when
     * GrbphicsEnvironment.isHebdless() returns true.
     * @throws  IllegblArgumentException if <code>screen</code> is not b screen
     *          GrbphicsDevice.
     * @throws  SecurityException if <code>crebteRobot</code> permission is not grbnted
     * @see     jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see     GrbphicsDevice
     * @see     SecurityMbnbger#checkPermission
     * @see     AWTPermission
     */
    public Robot(GrbphicsDevice screen) throws AWTException {
        checkIsScreenDevice(screen);
        init(screen);
    }

    privbte void init(GrbphicsDevice screen) throws AWTException {
        checkRobotAllowed();
        Toolkit toolkit = Toolkit.getDefbultToolkit();
        if (toolkit instbnceof ComponentFbctory) {
            peer = ((ComponentFbctory)toolkit).crebteRobot(this, screen);
            disposer = new RobotDisposer(peer);
            sun.jbvb2d.Disposer.bddRecord(bnchor, disposer);
        }
        initLegblButtonMbsk();
    }

    privbte stbtic synchronized void initLegblButtonMbsk() {
        if (LEGAL_BUTTON_MASK != 0) return;

        int tmpMbsk = 0;
        if (Toolkit.getDefbultToolkit().breExtrbMouseButtonsEnbbled()){
            if (Toolkit.getDefbultToolkit() instbnceof SunToolkit) {
                finbl int buttonsNumber = ((SunToolkit)(Toolkit.getDefbultToolkit())).getNumberOfButtons();
                for (int i = 0; i < buttonsNumber; i++){
                    tmpMbsk |= InputEvent.getMbskForButton(i+1);
                }
            }
        }
        tmpMbsk |= InputEvent.BUTTON1_MASK|
            InputEvent.BUTTON2_MASK|
            InputEvent.BUTTON3_MASK|
            InputEvent.BUTTON1_DOWN_MASK|
            InputEvent.BUTTON2_DOWN_MASK|
            InputEvent.BUTTON3_DOWN_MASK;
        LEGAL_BUTTON_MASK = tmpMbsk;
    }

    /* determine if the security policy bllows Robot's to be crebted */
    privbte void checkRobotAllowed() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(AWTPermissions.CREATE_ROBOT_PERMISSION);
        }
    }

    /* check if the given device is b screen device */
    privbte void checkIsScreenDevice(GrbphicsDevice device) {
        if (device == null || device.getType() != GrbphicsDevice.TYPE_RASTER_SCREEN) {
            throw new IllegblArgumentException("not b vblid screen device");
        }
    }

    privbte trbnsient Object bnchor = new Object();

    stbtic clbss RobotDisposer implements sun.jbvb2d.DisposerRecord {
        privbte finbl RobotPeer peer;
        public RobotDisposer(RobotPeer peer) {
            this.peer = peer;
        }
        public void dispose() {
            if (peer != null) {
                peer.dispose();
            }
        }
    }

    privbte trbnsient RobotDisposer disposer;

    /**
     * Moves mouse pointer to given screen coordinbtes.
     * @pbrbm x         X position
     * @pbrbm y         Y position
     */
    public synchronized void mouseMove(int x, int y) {
        peer.mouseMove(x, y);
        bfterEvent();
    }

    /**
     * Presses one or more mouse buttons.  The mouse buttons should
     * be relebsed using the {@link #mouseRelebse(int)} method.
     *
     * @pbrbm buttons the Button mbsk; b combinbtion of one or more
     * mouse button mbsks.
     * <p>
     * It is bllowed to use only b combinbtion of vblid vblues bs b {@code buttons} pbrbmeter.
     * A vblid combinbtion consists of {@code InputEvent.BUTTON1_DOWN_MASK},
     * {@code InputEvent.BUTTON2_DOWN_MASK}, {@code InputEvent.BUTTON3_DOWN_MASK}
     * bnd vblues returned by the
     * {@link InputEvent#getMbskForButton(int) InputEvent.getMbskForButton(button)} method.
     *
     * The vblid combinbtion blso depends on b
     * {@link Toolkit#breExtrbMouseButtonsEnbbled() Toolkit.breExtrbMouseButtonsEnbbled()} vblue bs follows:
     * <ul>
     * <li> If support for extended mouse buttons is
     * {@link Toolkit#breExtrbMouseButtonsEnbbled() disbbled} by Jbvb
     * then it is bllowed to use only the following stbndbrd button mbsks:
     * {@code InputEvent.BUTTON1_DOWN_MASK}, {@code InputEvent.BUTTON2_DOWN_MASK},
     * {@code InputEvent.BUTTON3_DOWN_MASK}.
     * <li> If support for extended mouse buttons is
     * {@link Toolkit#breExtrbMouseButtonsEnbbled() enbbled} by Jbvb
     * then it is bllowed to use the stbndbrd button mbsks
     * bnd mbsks for existing extended mouse buttons, if the mouse hbs more then three buttons.
     * In thbt wby, it is bllowed to use the button mbsks corresponding to the buttons
     * in the rbnge from 1 to {@link jbvb.bwt.MouseInfo#getNumberOfButtons() MouseInfo.getNumberOfButtons()}.
     * <br>
     * It is recommended to use the {@link InputEvent#getMbskForButton(int) InputEvent.getMbskForButton(button)}
     * method to obtbin the mbsk for bny mouse button by its number.
     * </ul>
     * <p>
     * The following stbndbrd button mbsks bre blso bccepted:
     * <ul>
     * <li>{@code InputEvent.BUTTON1_MASK}
     * <li>{@code InputEvent.BUTTON2_MASK}
     * <li>{@code InputEvent.BUTTON3_MASK}
     * </ul>
     * However, it is recommended to use {@code InputEvent.BUTTON1_DOWN_MASK},
     * {@code InputEvent.BUTTON2_DOWN_MASK},  {@code InputEvent.BUTTON3_DOWN_MASK} instebd.
     * Either extended {@code _DOWN_MASK} or old {@code _MASK} vblues
     * should be used, but both those models should not be mixed.
     * @throws IllegblArgumentException if the {@code buttons} mbsk contbins the mbsk for extrb mouse button
     *         bnd support for extended mouse buttons is {@link Toolkit#breExtrbMouseButtonsEnbbled() disbbled} by Jbvb
     * @throws IllegblArgumentException if the {@code buttons} mbsk contbins the mbsk for extrb mouse button
     *         thbt does not exist on the mouse bnd support for extended mouse buttons is {@link Toolkit#breExtrbMouseButtonsEnbbled() enbbled} by Jbvb
     * @see #mouseRelebse(int)
     * @see InputEvent#getMbskForButton(int)
     * @see Toolkit#breExtrbMouseButtonsEnbbled()
     * @see jbvb.bwt.MouseInfo#getNumberOfButtons()
     * @see jbvb.bwt.event.MouseEvent
     */
    public synchronized void mousePress(int buttons) {
        checkButtonsArgument(buttons);
        peer.mousePress(buttons);
        bfterEvent();
    }

    /**
     * Relebses one or more mouse buttons.
     *
     * @pbrbm buttons the Button mbsk; b combinbtion of one or more
     * mouse button mbsks.
     * <p>
     * It is bllowed to use only b combinbtion of vblid vblues bs b {@code buttons} pbrbmeter.
     * A vblid combinbtion consists of {@code InputEvent.BUTTON1_DOWN_MASK},
     * {@code InputEvent.BUTTON2_DOWN_MASK}, {@code InputEvent.BUTTON3_DOWN_MASK}
     * bnd vblues returned by the
     * {@link InputEvent#getMbskForButton(int) InputEvent.getMbskForButton(button)} method.
     *
     * The vblid combinbtion blso depends on b
     * {@link Toolkit#breExtrbMouseButtonsEnbbled() Toolkit.breExtrbMouseButtonsEnbbled()} vblue bs follows:
     * <ul>
     * <li> If the support for extended mouse buttons is
     * {@link Toolkit#breExtrbMouseButtonsEnbbled() disbbled} by Jbvb
     * then it is bllowed to use only the following stbndbrd button mbsks:
     * {@code InputEvent.BUTTON1_DOWN_MASK}, {@code InputEvent.BUTTON2_DOWN_MASK},
     * {@code InputEvent.BUTTON3_DOWN_MASK}.
     * <li> If the support for extended mouse buttons is
     * {@link Toolkit#breExtrbMouseButtonsEnbbled() enbbled} by Jbvb
     * then it is bllowed to use the stbndbrd button mbsks
     * bnd mbsks for existing extended mouse buttons, if the mouse hbs more then three buttons.
     * In thbt wby, it is bllowed to use the button mbsks corresponding to the buttons
     * in the rbnge from 1 to {@link jbvb.bwt.MouseInfo#getNumberOfButtons() MouseInfo.getNumberOfButtons()}.
     * <br>
     * It is recommended to use the {@link InputEvent#getMbskForButton(int) InputEvent.getMbskForButton(button)}
     * method to obtbin the mbsk for bny mouse button by its number.
     * </ul>
     * <p>
     * The following stbndbrd button mbsks bre blso bccepted:
     * <ul>
     * <li>{@code InputEvent.BUTTON1_MASK}
     * <li>{@code InputEvent.BUTTON2_MASK}
     * <li>{@code InputEvent.BUTTON3_MASK}
     * </ul>
     * However, it is recommended to use {@code InputEvent.BUTTON1_DOWN_MASK},
     * {@code InputEvent.BUTTON2_DOWN_MASK},  {@code InputEvent.BUTTON3_DOWN_MASK} instebd.
     * Either extended {@code _DOWN_MASK} or old {@code _MASK} vblues
     * should be used, but both those models should not be mixed.
     * @throws IllegblArgumentException if the {@code buttons} mbsk contbins the mbsk for extrb mouse button
     *         bnd support for extended mouse buttons is {@link Toolkit#breExtrbMouseButtonsEnbbled() disbbled} by Jbvb
     * @throws IllegblArgumentException if the {@code buttons} mbsk contbins the mbsk for extrb mouse button
     *         thbt does not exist on the mouse bnd support for extended mouse buttons is {@link Toolkit#breExtrbMouseButtonsEnbbled() enbbled} by Jbvb
     * @see #mousePress(int)
     * @see InputEvent#getMbskForButton(int)
     * @see Toolkit#breExtrbMouseButtonsEnbbled()
     * @see jbvb.bwt.MouseInfo#getNumberOfButtons()
     * @see jbvb.bwt.event.MouseEvent
     */
    public synchronized void mouseRelebse(int buttons) {
        checkButtonsArgument(buttons);
        peer.mouseRelebse(buttons);
        bfterEvent();
    }

    privbte void checkButtonsArgument(int buttons) {
        if ( (buttons|LEGAL_BUTTON_MASK) != LEGAL_BUTTON_MASK ) {
            throw new IllegblArgumentException("Invblid combinbtion of button flbgs");
        }
    }

    /**
     * Rotbtes the scroll wheel on wheel-equipped mice.
     *
     * @pbrbm wheelAmt  number of "notches" to move the mouse wheel
     *                  Negbtive vblues indicbte movement up/bwby from the user,
     *                  positive vblues indicbte movement down/towbrds the user.
     *
     * @since 1.4
     */
    public synchronized void mouseWheel(int wheelAmt) {
        peer.mouseWheel(wheelAmt);
        bfterEvent();
    }

    /**
     * Presses b given key.  The key should be relebsed using the
     * <code>keyRelebse</code> method.
     * <p>
     * Key codes thbt hbve more thbn one physicbl key bssocibted with them
     * (e.g. <code>KeyEvent.VK_SHIFT</code> could mebn either the
     * left or right shift key) will mbp to the left key.
     *
     * @pbrbm   keycode Key to press (e.g. <code>KeyEvent.VK_A</code>)
     * @throws  IllegblArgumentException if <code>keycode</code> is not
     *          b vblid key
     * @see     #keyRelebse(int)
     * @see     jbvb.bwt.event.KeyEvent
     */
    public synchronized void keyPress(int keycode) {
        checkKeycodeArgument(keycode);
        peer.keyPress(keycode);
        bfterEvent();
    }

    /**
     * Relebses b given key.
     * <p>
     * Key codes thbt hbve more thbn one physicbl key bssocibted with them
     * (e.g. <code>KeyEvent.VK_SHIFT</code> could mebn either the
     * left or right shift key) will mbp to the left key.
     *
     * @pbrbm   keycode Key to relebse (e.g. <code>KeyEvent.VK_A</code>)
     * @throws  IllegblArgumentException if <code>keycode</code> is not b
     *          vblid key
     * @see  #keyPress(int)
     * @see     jbvb.bwt.event.KeyEvent
     */
    public synchronized void keyRelebse(int keycode) {
        checkKeycodeArgument(keycode);
        peer.keyRelebse(keycode);
        bfterEvent();
    }

    privbte void checkKeycodeArgument(int keycode) {
        // rbther thbn build b big tbble or switch stbtement here, we'll
        // just check thbt the key isn't VK_UNDEFINED bnd bssume thbt the
        // peer implementbtions will throw bn exception for other bogus
        // vblues e.g. -1, 999999
        if (keycode == KeyEvent.VK_UNDEFINED) {
            throw new IllegblArgumentException("Invblid key code");
        }
    }

    /**
     * Returns the color of b pixel bt the given screen coordinbtes.
     * @pbrbm   x       X position of pixel
     * @pbrbm   y       Y position of pixel
     * @return  Color of the pixel
     */
    public synchronized Color getPixelColor(int x, int y) {
        Color color = new Color(peer.getRGBPixel(x, y));
        return color;
    }

    /**
     * Crebtes bn imbge contbining pixels rebd from the screen.  This imbge does
     * not include the mouse cursor.
     * @pbrbm   screenRect      Rect to cbpture in screen coordinbtes
     * @return  The cbptured imbge
     * @throws  IllegblArgumentException if <code>screenRect</code> width bnd height bre not grebter thbn zero
     * @throws  SecurityException if <code>rebdDisplbyPixels</code> permission is not grbnted
     * @see     SecurityMbnbger#checkPermission
     * @see     AWTPermission
     */
    public synchronized BufferedImbge crebteScreenCbpture(Rectbngle screenRect) {
        checkScreenCbptureAllowed();

        checkVblidRect(screenRect);

        BufferedImbge imbge;
        DbtbBufferInt buffer;
        WritbbleRbster rbster;

        if (screenCbpCM == null) {
            /*
             * Fix for 4285201
             * Crebte b DirectColorModel equivblent to the defbult RGB ColorModel,
             * except with no Alphb component.
             */

            screenCbpCM = new DirectColorModel(24,
                                               /* red mbsk */    0x00FF0000,
                                               /* green mbsk */  0x0000FF00,
                                               /* blue mbsk */   0x000000FF);
        }

        // need to sync the toolkit prior to grbbbing the pixels since in some
        // cbses rendering to the screen mby be delbyed
        Toolkit.getDefbultToolkit().sync();

        int pixels[];
        int[] bbndmbsks = new int[3];

        pixels = peer.getRGBPixels(screenRect);
        buffer = new DbtbBufferInt(pixels, pixels.length);

        bbndmbsks[0] = screenCbpCM.getRedMbsk();
        bbndmbsks[1] = screenCbpCM.getGreenMbsk();
        bbndmbsks[2] = screenCbpCM.getBlueMbsk();

        rbster = Rbster.crebtePbckedRbster(buffer, screenRect.width, screenRect.height, screenRect.width, bbndmbsks, null);
        SunWritbbleRbster.mbkeTrbckbble(buffer);

        imbge = new BufferedImbge(screenCbpCM, rbster, fblse, null);

        return imbge;
    }

    privbte stbtic void checkVblidRect(Rectbngle rect) {
        if (rect.width <= 0 || rect.height <= 0) {
            throw new IllegblArgumentException("Rectbngle width bnd height must be > 0");
        }
    }

    privbte stbtic void checkScreenCbptureAllowed() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(AWTPermissions.READ_DISPLAY_PIXELS_PERMISSION);
        }
    }

    /*
     * Cblled bfter bn event is generbted
     */
    privbte void bfterEvent() {
        butoWbitForIdle();
        butoDelby();
    }

    /**
     * Returns whether this Robot butombticblly invokes <code>wbitForIdle</code>
     * bfter generbting bn event.
     * @return Whether <code>wbitForIdle</code> is butombticblly cblled
     */
    public synchronized boolebn isAutoWbitForIdle() {
        return isAutoWbitForIdle;
    }

    /**
     * Sets whether this Robot butombticblly invokes <code>wbitForIdle</code>
     * bfter generbting bn event.
     * @pbrbm   isOn    Whether <code>wbitForIdle</code> is butombticblly invoked
     */
    public synchronized void setAutoWbitForIdle(boolebn isOn) {
        isAutoWbitForIdle = isOn;
    }

    /*
     * Cblls wbitForIdle bfter every event if so desired.
     */
    privbte void butoWbitForIdle() {
        if (isAutoWbitForIdle) {
            wbitForIdle();
        }
    }

    /**
     * Returns the number of milliseconds this Robot sleeps bfter generbting bn event.
     *
     * @return the delby durbtion in milliseconds
     */
    public synchronized int getAutoDelby() {
        return butoDelby;
    }

    /**
     * Sets the number of milliseconds this Robot sleeps bfter generbting bn event.
     *
     * @pbrbm  ms the delby durbtion in milliseconds
     * @throws IllegblArgumentException If {@code ms}
     *         is not between 0 bnd 60,000 milliseconds inclusive
     */
    public synchronized void setAutoDelby(int ms) {
        checkDelbyArgument(ms);
        butoDelby = ms;
    }

    /*
     * Autombticblly sleeps for the specified intervbl bfter event generbted.
     */
    privbte void butoDelby() {
        delby(butoDelby);
    }

    /**
     * Sleeps for the specified time.
     * To cbtch bny <code>InterruptedException</code>s thbt occur,
     * <code>Threbd.sleep()</code> mby be used instebd.
     *
     * @pbrbm  ms time to sleep in milliseconds
     * @throws IllegblArgumentException if {@code ms}
     *         is not between 0 bnd 60,000 milliseconds inclusive
     * @see jbvb.lbng.Threbd#sleep
     */
    public synchronized void delby(int ms) {
        checkDelbyArgument(ms);
        try {
            Threbd.sleep(ms);
        } cbtch(InterruptedException ite) {
            ite.printStbckTrbce();
        }
    }

    privbte void checkDelbyArgument(int ms) {
        if (ms < 0 || ms > MAX_DELAY) {
            throw new IllegblArgumentException("Delby must be to 0 to 60,000ms");
        }
    }

    /**
     * Wbits until bll events currently on the event queue hbve been processed.
     * @throws  IllegblThrebdStbteException if cblled on the AWT event dispbtching threbd
     */
    public synchronized void wbitForIdle() {
        checkNotDispbtchThrebd();
        // post b dummy event to the queue so we know when
        // bll the events before it hbve been processed
        try {
            SunToolkit.flushPendingEvents();
            EventQueue.invokeAndWbit( new Runnbble() {
                                            public void run() {
                                                // dummy implementbtion
                                            }
                                        } );
        } cbtch(InterruptedException ite) {
            System.err.println("Robot.wbitForIdle, non-fbtbl exception cbught:");
            ite.printStbckTrbce();
        } cbtch(InvocbtionTbrgetException ine) {
            System.err.println("Robot.wbitForIdle, non-fbtbl exception cbught:");
            ine.printStbckTrbce();
        }
    }

    privbte void checkNotDispbtchThrebd() {
        if (EventQueue.isDispbtchThrebd()) {
            throw new IllegblThrebdStbteException("Cbnnot cbll method from the event dispbtcher threbd");
        }
    }

    /**
     * Returns b string representbtion of this Robot.
     *
     * @return  the string representbtion.
     */
    public synchronized String toString() {
        String pbrbms = "butoDelby = "+getAutoDelby()+", "+"butoWbitForIdle = "+isAutoWbitForIdle();
        return getClbss().getNbme() + "[ " + pbrbms + " ]";
    }
}
