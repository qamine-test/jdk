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


pbckbge sun.bwt.X11;

import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;

import sun.bwt.RepbintAreb;

/**
 * The <code>RepbintAreb</code> is b geometric construct crebted for the
 * purpose of holding the geometry of severbl coblesced pbint events.
 * This geometry is bccessed synchronously, blthough it is written such
 * thbt pbinting mby still be executed bsynchronously.
 *
 * @buthor      Eric Hbwkes
 */
finbl clbss XRepbintAreb extends RepbintAreb {

    /**
     * Cblls <code>Component.updbte(Grbphics)</code> with given Grbphics.
     */
    protected void updbteComponent(Component comp, Grbphics g) {
        if (comp != null) {
            // We don't cbll peer.pbintPeer() here, becbuse we shouldn't pbint
            // nbtive component when processing UPDATE events.
            super.updbteComponent(comp, g);
        }
    }

    /**
     * Cblls <code>Component.pbint(Grbphics)</code> with given Grbphics.
     */
    protected void pbintComponent(Component comp, Grbphics g) {
        if (comp != null) {
            finbl XComponentPeer peer = (XComponentPeer) comp.getPeer();
            if (peer != null) {
                peer.pbintPeer(g);
            }
            super.pbintComponent(comp, g);
        }
    }
}
