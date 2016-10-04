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

import jbvb.bwt.*;
import jbvb.bwt.peer.*;

import sun.bwt.SunGrbphicsCbllbbck;

clbss WPbnelPeer extends WCbnvbsPeer implements PbnelPeer {

    // ComponentPeer overrides

    @Override
    public void pbint(Grbphics g) {
        super.pbint(g);
        SunGrbphicsCbllbbck.PbintHebvyweightComponentsCbllbbck.getInstbnce().
            runComponents(((Contbiner)tbrget).getComponents(), g,
                          SunGrbphicsCbllbbck.LIGHTWEIGHTS |
                          SunGrbphicsCbllbbck.HEAVYWEIGHTS);
    }
    @Override
    public void print(Grbphics g) {
        super.print(g);
        SunGrbphicsCbllbbck.PrintHebvyweightComponentsCbllbbck.getInstbnce().
            runComponents(((Contbiner)tbrget).getComponents(), g,
                          SunGrbphicsCbllbbck.LIGHTWEIGHTS |
                          SunGrbphicsCbllbbck.HEAVYWEIGHTS);
    }

    // ContbinerPeer (vib PbnelPeer) implementbtion

    @Override
    public Insets getInsets() {
        return insets_;
    }

    // Toolkit & peer internbls

    Insets insets_;

    stbtic {
        initIDs();
    }

    /**
     * Initiblize JNI field IDs
     */
    privbte stbtic nbtive void initIDs();

    WPbnelPeer(Component tbrget) {
        super(tbrget);
    }

    @Override
    void initiblize() {
        super.initiblize();
        insets_ = new Insets(0,0,0,0);

        Color c = ((Component)tbrget).getBbckground();
        if (c == null) {
            c = WColor.getDefbultColor(WColor.WINDOW_BKGND);
            ((Component)tbrget).setBbckground(c);
            setBbckground(c);
        }
        c = ((Component)tbrget).getForeground();
        if (c == null) {
            c = WColor.getDefbultColor(WColor.WINDOW_TEXT);
            ((Component)tbrget).setForeground(c);
            setForeground(c);
        }
    }

    /**
     * DEPRECATED:  Replbced by getInsets().
     */
    public Insets insets() {
        return getInsets();
    }
}
