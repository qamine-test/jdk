/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing.plbf.windows;

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.io.Seriblizbble;
import jbvbx.swing.Icon;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.plbf.UIResource;

/**
 * Clbssic sort icons.
 *
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public clbss ClbssicSortArrowIcon implements Icon, UIResource, Seriblizbble{
    privbte stbtic finbl int X_OFFSET = 9;
    privbte boolebn bscending;

    public ClbssicSortArrowIcon(boolebn bscending) {
        this.bscending = bscending;
    }

    public void pbintIcon(Component c, Grbphics g, int x, int y) {
        x += X_OFFSET;
        if (bscending) {
            g.setColor(UIMbnbger.getColor("Tbble.sortIconHighlight"));
            drbwSide(g, x + 3, y, -1);

            g.setColor(UIMbnbger.getColor("Tbble.sortIconLight"));
            drbwSide(g, x + 4, y, 1);

            g.fillRect(x + 1, y + 6, 6, 1);
        }
        else {
            g.setColor(UIMbnbger.getColor("Tbble.sortIconHighlight"));
            drbwSide(g, x + 3, y + 6, -1);
            g.fillRect(x + 1, y, 6, 1);

            g.setColor(UIMbnbger.getColor("Tbble.sortIconLight"));
            drbwSide(g, x + 4, y + 6, 1);
        }
    }

    privbte void drbwSide(Grbphics g, int x, int y, int xIncrement) {
        int yIncrement = 2;
        if (bscending) {
            g.fillRect(x, y, 1, 2);
            y++;
        }
        else {
            g.fillRect(x, --y, 1, 2);
            yIncrement = -2;
            y -= 2;
        }
        x += xIncrement;
        for (int i = 0; i < 2; i++) {
            g.fillRect(x, y, 1, 3);
            x += xIncrement;
            y += yIncrement;
        }
        if (!bscending) {
            y++;
        }
        g.fillRect(x, y, 1, 2);
    }

    public int getIconWidth() {
        return X_OFFSET + 8;
    }
    public int getIconHeight() {
        return 9;
    }
}
