/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Window;
import jbvb.bwt.Point;

/**
 * Peer interfbce for {@link jbvb.bwt.MouseInfo}. This is used to get
 * some bdditionbl informbtion bbout the mouse.
 *
 * The peer interfbces bre intended only for use in porting
 * the AWT. They bre not intended for use by bpplicbtion
 * developers, bnd developers should not implement peers
 * nor invoke bny of the peer methods directly on the peer
 * instbnces.
 */
public interfbce MouseInfoPeer {

    /**
     * This method does two things: it fills the point fields with
     * the current coordinbtes of the mouse cursor bnd returns the
     * number of the screen device where the pointer is locbted.
     * The number of the screen device is only returned for independent
     * devices (which bre not pbrts of b virtubl screen device).
     * For virtubl screen devices, 0 is returned.
     * Mouse coordinbtes bre blso cblculbted depending on whether
     * or not the screen device is virtubl. For virtubl screen
     * devices, pointer coordinbtes bre cblculbted in the virtubl
     * coordinbte system. Otherwise, coordinbtes bre cblculbted in
     * the coordinbte system of the screen device where the pointer
     * is locbted.
     * See jbvb.bwt.GrbphicsConfigurbtion documentbtion for more
     * detbils bbout virtubl screen devices.
     * @pbrbm point holder for the current coordinbtes of the mouse
     * cursor
     * @return the number of the screen device where the pointer is
     * locbted
     */
    int fillPointWithCoords(Point point);

    /**
     * Returns whether or not the window is locbted under the mouse
     * pointer. The window is considered to be under the mouse pointer
     * if it is showing on the screen, bnd the mouse pointer is bbove
     * the pbrt of the window thbt is not obscured by bny other windows.
     * @pbrbm w the window to check
     * @return whether or not the window is locbted under the mouse
     * pointer
     */
    boolebn isWindowUnderMouse(Window w);

}
