/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.bwt.Grbphics;
import jbvb.bwt.Component;


/**
 * A smbll fixed size picture, typicblly used to decorbte components.
 *
 * @see ImbgeIcon
 * @since 1.2
 */

public interfbce Icon
{
    /**
     * Drbw the icon bt the specified locbtion.  Icon implementbtions
     * mby use the Component brgument to get properties useful for
     * pbinting, e.g. the foreground or bbckground color.
     *
     * @pbrbm c  b {@code Component} to get properties useful for pbinting
     * @pbrbm g  the grbphics context
     * @pbrbm x  the X coordinbte of the icon's top-left corner
     * @pbrbm y  the Y coordinbte of the icon's top-left corner
     */
    void pbintIcon(Component c, Grbphics g, int x, int y);

    /**
     * Returns the icon's width.
     *
     * @return bn int specifying the fixed width of the icon.
     */
    int getIconWidth();

    /**
     * Returns the icon's height.
     *
     * @return bn int specifying the fixed height of the icon.
     */
    int getIconHeight();
}
