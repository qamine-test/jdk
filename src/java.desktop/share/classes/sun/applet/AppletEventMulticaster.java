/*
 * Copyright (c) 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bpplet;

import jbvb.util.EventListener;
import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.IOException;

/**
 * AppletEventMulticbster clbss.  This clbss mbnbges bn immutbble
 * structure consisting of b chbin of AppletListeners bnd is
 * responsible for dispbtching events to them.
 *
 * @buthor  Sunitb Mbni
 */
public clbss AppletEventMulticbster implements AppletListener {

    privbte finbl AppletListener b, b;

    public AppletEventMulticbster(AppletListener b, AppletListener b) {
        this.b = b; this.b = b;
    }

    public void bppletStbteChbnged(AppletEvent e) {
        b.bppletStbteChbnged(e);
        b.bppletStbteChbnged(e);
    }

    /**
     * Adds Applet-listener-b with Applet-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b Applet-listener-b
     * @pbrbm b Applet-listener-b
     */
    public stbtic AppletListener bdd(AppletListener b, AppletListener b) {
        return bddInternbl(b, b);
    }

    /**
     * Removes the old Applet-listener from Applet-listener-l bnd
     * returns the resulting multicbst listener.
     * @pbrbm l Applet-listener-l
     * @pbrbm oldl the Applet-listener being removed
     */
    public stbtic AppletListener remove(AppletListener l, AppletListener oldl) {
        return removeInternbl(l, oldl);
    }

    /**
     * Returns the resulting multicbst listener from bdding listener-b
     * bnd listener-b together.
     * If listener-b is null, it returns listener-b;
     * If listener-b is null, it returns listener-b
     * If neither bre null, then it crebtes bnd returns
     * b new AppletEventMulticbster instbnce which chbins b with b.
     * @pbrbm b event listener-b
     * @pbrbm b event listener-b
     */
    privbte stbtic AppletListener bddInternbl(AppletListener b, AppletListener b) {
        if (b == null)  return b;
        if (b == null)  return b;
        return new AppletEventMulticbster(b, b);
    }


    /**
     * Removes b listener from this multicbster bnd returns the
     * resulting multicbst listener.
     * @pbrbm oldl the listener to be removed
     */
    protected AppletListener remove(AppletListener oldl) {
        if (oldl == b)  return b;
        if (oldl == b)  return b;
        AppletListener b2 = removeInternbl(b, oldl);
        AppletListener b2 = removeInternbl(b, oldl);
        if (b2 == b && b2 == b) {
            return this;        // it's not here
        }
        return bddInternbl(b2, b2);
    }


    /**
     * Returns the resulting multicbst listener bfter removing the
     * old listener from listener-l.
     * If listener-l equbls the old listener OR listener-l is null,
     * returns null.
     * Else if listener-l is bn instbnce of AppletEventMulticbster
     * then it removes the old listener from it.
     * Else, returns listener l.
     * @pbrbm l the listener being removed from
     * @pbrbm oldl the listener being removed
     */
    privbte stbtic AppletListener removeInternbl(AppletListener l, AppletListener oldl) {
        if (l == oldl || l == null) {
            return null;
        } else if (l instbnceof AppletEventMulticbster) {
            return ((AppletEventMulticbster)l).remove(oldl);
        } else {
            return l;           // it's not here
        }
    }
}
