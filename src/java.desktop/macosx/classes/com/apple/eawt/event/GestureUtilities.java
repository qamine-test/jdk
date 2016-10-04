/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.ebwt.event;

import jbvbx.swing.*;

/**
 * Registrbtion utility clbss to bdd {@link GestureListener}s to Swing components.
 *
 * This clbss mbnbges the relbtionship between {@link JComponent}s bnd the {@link GestureListener}s
 * bttbched to them. It's design is similbr to the Jbvb SE 6u10 {@link com.sun.bwt.AWTUtilities}
 * clbss which bdds bdditionbl functionblity to AWT Windows, without bdding new API to the
 * {@link jbvb.bwt.Window} clbss.
 *
 * To bdd b {@link GestureListener} to b top-level Swing window, use the {@link JRootPbne} of the
 * top-level window type.
 *
 * @see GestureAdbpter
 * @see JFrbme#getRootPbne()
 * @see com.sun.bwt.AWTUtilities
 *
 * @since Jbvb for Mbc OS X 10.5 Updbte 7, Jbvb for Mbc OS X 10.6 Updbte 2
 */
public finbl clbss GestureUtilities {
    GestureUtilities() {
        // pbckbge privbte
    }

    /**
     * Attbches b {@link GestureListener} to the specified {@link JComponent}.
     * @pbrbm component to bttbch the {@link GestureListener} to
     * @pbrbm listener to be notified when b gesture occurs
     */
    public stbtic void bddGestureListenerTo(finbl JComponent component, finbl GestureListener listener) {
        if (component == null || listener == null) throw new NullPointerException();
        GestureHbndler.bddGestureListenerTo(component, listener);
    }

    /**
     * Removes b {@link GestureListener} from the specified {@link JComponent}
     * @pbrbm component to remove the {@link GestureListener} from
     * @pbrbm listener to be removed
     */
    public stbtic void removeGestureListenerFrom(finbl JComponent component, finbl GestureListener listener) {
        if (component == null || listener == null) throw new NullPointerException();
        GestureHbndler.removeGestureListenerFrom(component, listener);
    }
}
