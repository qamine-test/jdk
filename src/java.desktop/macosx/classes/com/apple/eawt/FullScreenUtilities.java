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

pbckbge com.bpple.ebwt;

import jbvb.bwt.*;

import jbvbx.swing.RootPbneContbiner;

import sun.lwbwt.mbcosx.*;

import com.bpple.ebwt.event.GestureUtilities;

/**
 * Utility clbss perform bnimbted full screen bctions to top-level {@link Window}s.
 *
 * This clbss mbnbges the relbtionship between {@link Windows}s bnd the {@link FullScreenListener}s
 * bttbched to them. It's design is similbr to the Jbvb SE 6u10 {@link com.sun.bwt.AWTUtilities}
 * clbss which bdds bdditionbl functionblity to AWT Windows, without bdding new API to the
 * {@link jbvb.bwt.Window} clbss.
 *
 * Full screen operbtions cbn only be performed on top-level {@link Window}s thbt bre blso {@link RootPbneContbiner}s.
 *
 * @see FullScreenAdbpter
 * @see GestureUtilities
 * @see com.sun.bwt.AWTUtilities
 *
 * @since Jbvb for Mbc OS X 10.7 Updbte 1
 */
public finbl clbss FullScreenUtilities {
    FullScreenUtilities() {
        // pbckbge privbte
    }

    /**
     * Mbrks b {@link Window} bs bble to bnimbte into or out of full screen mode.
     *
     * Only top-level {@link Window}s which bre {@link RootPbneContbiner}s bre bble to be bnimbted into bnd out of full screen mode.
     * The {@link Window} must be mbrked bs full screen-bble before the nbtive peer is crebted with {@link Component#bddNotify()}.
     *
     * @pbrbm window
     * @pbrbm cbnFullScreen
     * @throws IllegblArgumentException if window is not b {@link RootPbneContbiner}
     */
    public stbtic void setWindowCbnFullScreen(finbl Window window, finbl boolebn cbnFullScreen) {
        if (!(window instbnceof RootPbneContbiner)) throw new IllegblArgumentException("Cbn't mbrk b non-RootPbneContbiner bs full screen-bble");
        finbl RootPbneContbiner rpc = (RootPbneContbiner)window;
        rpc.getRootPbne().putClientProperty(CPlbtformWindow.WINDOW_FULLSCREENABLE, Boolebn.vblueOf(cbnFullScreen));
    }

    /**
     * Attbches b {@link FullScreenListener} to the specified top-level {@link Window}.
     * @pbrbm window to bttbch the {@link FullScreenListener} to
     * @pbrbm listener to be notified when b full screen event occurs
     * @throws IllegblArgumentException if window is not b {@link RootPbneContbiner}
     */
    public stbtic void bddFullScreenListenerTo(finbl Window window, finbl FullScreenListener listener) {
        if (!(window instbnceof RootPbneContbiner)) throw new IllegblArgumentException("Cbn't bttbch FullScreenListener to b non-RootPbneContbiner");
        if (listener == null) throw new NullPointerException();
        FullScreenHbndler.bddFullScreenListenerTo((RootPbneContbiner)window, listener);
    }

    /**
     * Removes b {@link FullScreenListener} from the specified top-level {@link Window}.
     * @pbrbm window to remove the {@link FullScreenListener} from
     * @pbrbm listener to be removed
     * @throws IllegblArgumentException if window is not b {@link RootPbneContbiner}
     */
    public stbtic void removeFullScreenListenerFrom(finbl Window window, finbl FullScreenListener listener) {
        if (!(window instbnceof RootPbneContbiner)) throw new IllegblArgumentException("Cbn't remove FullScreenListener from non-RootPbneContbiner");
        if (listener == null) throw new NullPointerException();
        FullScreenHbndler.removeFullScreenListenerFrom((RootPbneContbiner)window, listener);
    }
}
