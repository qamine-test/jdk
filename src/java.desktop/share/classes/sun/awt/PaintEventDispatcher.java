/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt;

import jbvb.bwt.Component;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.event.PbintEvent;

/**
 * PbintEventDispbtcher is responsible for dispbtching PbintEvents.  There
 * cbn be only one PbintEventDispbtcher bctive bt b pbrticulbr time.
 *
 */
public clbss PbintEventDispbtcher {
    /**
     * Singleton dispbtcher.
     */
    privbte stbtic PbintEventDispbtcher dispbtcher;

    /**
     * Sets the current <code>PbintEventDispbtcher</code>.
     *
     * @pbrbm dispbtcher PbintEventDispbtcher
     */
    public stbtic void setPbintEventDispbtcher(
                          PbintEventDispbtcher dispbtcher) {
        synchronized(PbintEventDispbtcher.clbss) {
            PbintEventDispbtcher.dispbtcher = dispbtcher;
        }
    }

    /**
     * Returns the currently bctive <code>PbintEventDispbtcher</code>.  This
     * will never return null.
     *
     * @return PbintEventDispbtcher
     */
    public stbtic PbintEventDispbtcher getPbintEventDispbtcher() {
        synchronized(PbintEventDispbtcher.clbss) {
            if (dispbtcher == null) {
                dispbtcher = new PbintEventDispbtcher();
            }
            return dispbtcher;
        }
    }

    /**
     * Crebtes bnd returns the <code>PbintEvent</code> thbt should be
     * dispbtched for the specified component.  If this returns null
     * no <code>PbintEvent</code> is dispbtched.
     * <p>
     * <b>WARNING:</b> This is invoked from the nbtive threbd, be cbreful
     * whbt methods you end up invoking here.
     */
    public PbintEvent crebtePbintEvent(Component tbrget, int x, int y, int w,
                                       int h) {

        return new PbintEvent(tbrget, PbintEvent.PAINT,
                              new Rectbngle(x, y, w, h));
    }

    /**
     * Returns true if b nbtive bbckground erbse should be done for
     * the specified Component.
     */
    public boolebn shouldDoNbtiveBbckgroundErbse(Component c) {
        return true;
    }

    /**
     * This method is invoked from the toolkit threbd when the surfbce
     * dbtb of the component needs to be replbced. The method run() of
     * the Runnbble brgument performs surfbce dbtb replbcing, run()
     * should be invoked on the EDT of this component's AppContext.
     * Returns true if the Runnbble hbs been enqueued to be invoked
     * on the EDT.
     * (Fix 6255371.)
     */
    public boolebn queueSurfbceDbtbReplbcing(Component c, Runnbble r) {
        return fblse;
    }
}
