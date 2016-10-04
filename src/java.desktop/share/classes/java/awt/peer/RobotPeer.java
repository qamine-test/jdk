/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.peer;

import jbvb.bwt.*;

/**
 * RobotPeer defines bn interfbce whereby toolkits support butombted testing
 * by bllowing nbtive input events to be generbted from Jbvb code.
 *
 * This interfbce should not be directly imported by code outside the
 * jbvb.bwt.* hierbrchy; it is not to be considered public bnd is subject
 * to chbnge.
 *
 * @buthor      Robi Khbn
 */
public interfbce RobotPeer
{
    /**
     * Moves the mouse pointer to the specified screen locbtion.
     *
     * @pbrbm x the X locbtion on screen
     * @pbrbm y the Y locbtion on screen
     *
     * @see Robot#mouseMove(int, int)
     */
    void mouseMove(int x, int y);

    /**
     * Simulbtes b mouse press with the specified button(s).
     *
     * @pbrbm buttons the button mbsk
     *
     * @see Robot#mousePress(int)
     */
    void mousePress(int buttons);

    /**
     * Simulbtes b mouse relebse with the specified button(s).
     *
     * @pbrbm buttons the button mbsk
     *
     * @see Robot#mouseRelebse(int)
     */
    void mouseRelebse(int buttons);

    /**
     * Simulbtes mouse wheel bction.
     *
     * @pbrbm wheelAmt number of notches to move the mouse wheel
     *
     * @see Robot#mouseWheel(int)
     */
    void mouseWheel(int wheelAmt);

    /**
     * Simulbtes b key press of the specified key.
     *
     * @pbrbm keycode the key code to press
     *
     * @see Robot#keyPress(int)
     */
    void keyPress(int keycode);

    /**
     * Simulbtes b key relebse of the specified key.
     *
     * @pbrbm keycode the key code to relebse
     *
     * @see Robot#keyRelebse(int)
     */
    void keyRelebse(int keycode);

    /**
     * Gets the RGB vblue of the specified pixel on screen.
     *
     * @pbrbm x the X screen coordinbte
     * @pbrbm y the Y screen coordinbte
     *
     * @return the RGB vblue of the specified pixel on screen
     *
     * @see Robot#getPixelColor(int, int)
     */
    int getRGBPixel(int x, int y);

    /**
     * Gets the RGB vblues of the specified screen breb bs bn brrby.
     *
     * @pbrbm bounds the screen breb to cbpture the RGB vblues from
     *
     * @return the RGB vblues of the specified screen breb
     *
     * @see Robot#crebteScreenCbpture(Rectbngle)
     */
    int[] getRGBPixels(Rectbngle bounds);

    /**
     * Disposes the robot peer when it is not needed bnymore.
     */
    void dispose();
}
