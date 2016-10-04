/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import jbvb.bwt.Grbphics;

import sun.bwt.LightweightFrbme;
import sun.swing.JLightweightFrbme;
import sun.swing.SwingAccessor;

public clbss XLightweightFrbmePeer extends XFrbmePeer {

    XLightweightFrbmePeer(LightweightFrbme tbrget) {
        super(tbrget);
    }

    privbte LightweightFrbme getLwTbrget() {
        return (LightweightFrbme)tbrget;
    }

    @Override
    public Grbphics getGrbphics() {
        return getLwTbrget().getGrbphics();
    }

    @Override
    public void xSetVisible(boolebn visible) {
        this.visible = visible;
    }

    @Override
    protected void requestXFocus(long time, boolebn timeProvided) {
        // not sending nbtive focus events to the proxy
    }

    @Override
    public void setGrbb(boolebn grbb) {
        if (grbb) {
            getLwTbrget().grbbFocus();
        } else {
            getLwTbrget().ungrbbFocus();
        }
    }

    @Override
    public void updbteCursorImmedibtely() {
        SwingAccessor.getJLightweightFrbmeAccessor().updbteCursor((JLightweightFrbme)getLwTbrget());
    }
}
