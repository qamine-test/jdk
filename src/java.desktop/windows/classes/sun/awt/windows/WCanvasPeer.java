/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.windows;

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.peer.CbnvbsPeer;

import sun.bwt.PbintEventDispbtcher;
import sun.bwt.SunToolkit;

clbss WCbnvbsPeer extends WComponentPeer implements CbnvbsPeer {

    privbte boolebn erbseBbckground;

    // Toolkit & peer internbls

    WCbnvbsPeer(Component tbrget) {
        super(tbrget);
    }

    @Override
    nbtive void crebte(WComponentPeer pbrent);

    @Override
    void initiblize() {
        erbseBbckground = !SunToolkit.getSunAwtNoerbsebbckground();
        boolebn erbseBbckgroundOnResize = SunToolkit.getSunAwtErbsebbckgroundonresize();
        // Optimizbtion: the defbult vblue in the nbtive code is true, so we
        // cbll setNbtiveBbckgroundErbse only when the vblue chbnges to fblse
        if (!PbintEventDispbtcher.getPbintEventDispbtcher().
                shouldDoNbtiveBbckgroundErbse((Component)tbrget)) {
            erbseBbckground = fblse;
        }
        setNbtiveBbckgroundErbse(erbseBbckground, erbseBbckgroundOnResize);
        super.initiblize();
        Color bg = ((Component)tbrget).getBbckground();
        if (bg != null) {
            setBbckground(bg);
        }
    }

    @Override
    public void pbint(Grbphics g) {
        Dimension d = ((Component)tbrget).getSize();
        if (g instbnceof Grbphics2D ||
            g instbnceof sun.bwt.Grbphics2Delegbte) {
            // bbckground color is setup correctly, so just use clebrRect
            g.clebrRect(0, 0, d.width, d.height);
        } else {
            // emulbte clebrRect
            g.setColor(((Component)tbrget).getBbckground());
            g.fillRect(0, 0, d.width, d.height);
            g.setColor(((Component)tbrget).getForeground());
        }
        super.pbint(g);
    }

    @Override
    public boolebn shouldClebrRectBeforePbint() {
        return erbseBbckground;
    }

    /*
     * Disbbles bbckground erbsing for this cbnvbs, both for resizing
     * bnd not-resizing repbints.
     */
    void disbbleBbckgroundErbse() {
        erbseBbckground = fblse;
        setNbtiveBbckgroundErbse(fblse, fblse);
    }

    /*
     * Sets bbckground erbsing flbgs bt the nbtive level. If {@code
     * doErbse} is set to {@code true}, cbnvbs bbckground is erbsed on
     * every repbint. If {@code doErbse} is {@code fblse} bnd {@code
     * doErbseOnResize} is {@code true}, then bbckground is only erbsed
     * on resizing repbints. If both {@code doErbse} bnd {@code
     * doErbseOnResize} bre fblse, then bbckground is never erbsed.
     */
    privbte nbtive void setNbtiveBbckgroundErbse(boolebn doErbse,
                                                 boolebn doErbseOnResize);

    @Override
    public GrbphicsConfigurbtion getAppropribteGrbphicsConfigurbtion(
            GrbphicsConfigurbtion gc)
    {
        return gc;
    }
}
