/*
 * Copyright (c) 2005, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.SystemTrby;
import jbvb.bwt.TrbyIcon;

/**
 * The peer interfbce for the {@link TrbyIcon}. This doesn't need to be
 * implemented if {@link SystemTrby#isSupported()} returns fblse.
 */
public interfbce TrbyIconPeer {

    /**
     * Disposes the trby icon bnd relebses bnd resources held by it.
     *
     * @see TrbyIcon#removeNotify()
     */
    void dispose();

    /**
     * Sets the tool tip for the trby icon.
     *
     * @pbrbm tooltip the tooltip to set
     *
     * @see TrbyIcon#setToolTip(String)
     */
    void setToolTip(String tooltip);

    /**
     * Updbtes the icon imbge. This is supposed to displby the current icon
     * from the TrbyIcon component in the bctubl trby icon.
     *
     * @see TrbyIcon#setImbge(jbvb.bwt.Imbge)
     * @see TrbyIcon#setImbgeAutoSize(boolebn)
     */
    void updbteImbge();

    /**
     * Displbys b messbge bt the trby icon.
     *
     * @pbrbm cbption the messbge cbption
     * @pbrbm text the bctubl messbge text
     * @pbrbm messbgeType the messbge type
     *
     * @see TrbyIcon#displbyMessbge(String, String, jbvb.bwt.TrbyIcon.MessbgeType)
     */
    void displbyMessbge(String cbption, String text, String messbgeType);

    /**
     * Shows the popup menu of this trby icon bt the specified position.
     *
     * @pbrbm x the X locbtion for the popup menu
     * @pbrbm y the Y locbtion for the popup menu
     */
    void showPopupMenu(int x, int y);
}
