/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The peer interfbce for {@link Window}.
 *
 * The peer interfbces bre intended only for use in porting
 * the AWT. They bre not intended for use by bpplicbtion
 * developers, bnd developers should not implement peers
 * nor invoke bny of the peer methods directly on the peer
 * instbnces.
 */
public interfbce WindowPeer extends ContbinerPeer {

    /**
     * Mbkes this window the topmost window on the desktop.
     *
     * @see Window#toFront()
     */
    void toFront();

    /**
     * Mbkes this window the bottommost window on the desktop.
     *
     * @see Window#toBbck()
     */
    void toBbck();

    /**
     * Updbtes the window's blwbys-on-top stbte.
     * Sets if the window should blwbys stby
     * on top of bll other windows or not.
     *
     * @see Window#isAlwbysOnTop()
     * @see Window#setAlwbysOnTop(boolebn)
     */
    void updbteAlwbysOnTopStbte();

    /**
     * Updbtes the window's focusbble stbte.
     *
     * @see Window#setFocusbbleWindowStbte(boolebn)
     */
    void updbteFocusbbleWindowStbte();

    /**
     * Sets if this window is blocked by b modbl diblog or not.
     *
     * @pbrbm blocker the blocking modbl diblog
     * @pbrbm blocked {@code true} to block the window, {@code fblse}
     *        to unblock it
     */
    void setModblBlocked(Diblog blocker, boolebn blocked);

    /**
     * Updbtes the minimum size on the peer.
     *
     * @see Window#setMinimumSize(Dimension)
     */
    void updbteMinimumSize();

    /**
     * Updbtes the icons for the window.
     *
     * @see Window#setIconImbges(jbvb.util.List)
     */
    void updbteIconImbges();

    /**
     * Sets the level of opbcity for the window.
     * @pbrbm opbcity the level of opbcity
     * @see Window#setOpbcity(flobt)
     */
    void setOpbcity(flobt opbcity);

    /**
     * Enbbles the per-pixel blphb support for the window.
     * @pbrbm isOpbque whether or not per-pixel blphb support is
     * enbbled
     * @see Window#setBbckground(Color)
     */
    void setOpbque(boolebn isOpbque);

    /**
     * Updbtes the nbtive pbrt of non-opbque window.
     *
     * @see Window#setBbckground(Color)
     */
    void updbteWindow();

    /**
     * Instructs the peer to updbte the position of the security wbrning.
     */
    void repositionSecurityWbrning();
}
