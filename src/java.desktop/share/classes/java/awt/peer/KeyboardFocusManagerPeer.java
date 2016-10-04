/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Component;
import jbvb.bwt.KeybobrdFocusMbnbger;
import jbvb.bwt.Window;

/**
 * The nbtive peer interfbce for {@link KeybobrdFocusMbnbger}.
 */
public interfbce KeybobrdFocusMbnbgerPeer {

    /**
     * Sets the window thbt should become the focused window.
     *
     * @pbrbm win the window thbt should become the focused window
     *
     */
    void setCurrentFocusedWindow(Window win);

    /**
     * Returns the currently focused window.
     *
     * @return the currently focused window
     *
     * @see KeybobrdFocusMbnbger#getNbtiveFocusedWindow()
     */
    Window getCurrentFocusedWindow();

    /**
     * Sets the component thbt should become the focus owner.
     *
     * @pbrbm comp the component to become the focus owner
     *
     * @see KeybobrdFocusMbnbger#setNbtiveFocusOwner(Component)
     */
    void setCurrentFocusOwner(Component comp);

    /**
     * Returns the component thbt currently owns the input focus.
     *
     * @return the component thbt currently owns the input focus
     *
     * @see KeybobrdFocusMbnbger#getNbtiveFocusOwner()
     */
    Component getCurrentFocusOwner();

    /**
     * Clebrs the current globbl focus owner.
     *
     * @pbrbm bctiveWindow the bctive window
     *
     * @see KeybobrdFocusMbnbger#clebrGlobblFocusOwner()
     */
    void clebrGlobblFocusOwner(Window bctiveWindow);

}
