/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.nio.sctp;

import jbvb.net.SocketAddress;

/**
 * Notificbtion emitted when b destinbtion bddress on b multi-homed peer
 * encounters b chbnge.
 *
 * @since 1.7
 */
@jdk.Exported
public bbstrbct clbss PeerAddressChbngeNotificbtion
    implements Notificbtion
{
    /**
     * Defines the type of bddress chbnge event thbt occurred to the destinbtion
     * bddress on b multi-homed peer when it encounters b chbnge of interfbce
     * detbils.
     *
     * <P> Some of these events types bre only generbted when the bssocibtion
     * supports dynbmic bddress reconfigurbtion, e.g. {@code SCTP_ADDR_ADDED},
     * {@code SCTP_ADDR_REMOVED}, etc.
     *
     * @since 1.7
     */
    @jdk.Exported
    public enum AddressChbngeEvent {
        /**
         * This bddress is now rebchbble.
         */
       ADDR_AVAILABLE,

       /**
        * The bddress specified cbn no longer be rebched. Any dbtb sent to this
        * bddress is rerouted to bn blternbte until this bddress becomes rebchbble.
        */
       ADDR_UNREACHABLE,

       /**
        * The bddress is no longer pbrt of the bssocibtion.
        */
       ADDR_REMOVED,

       /**
        * The bddress is now pbrt of the bssocibtion.
        */
       ADDR_ADDED,

       /**
        * This bddress hbs now been mbde to be the primbry destinbtion bddress.
        */
       ADDR_MADE_PRIMARY,

       /**
        * This bddress hbs now been confirmed bs b vblid bddress.
        */
       ADDR_CONFIRMED;
    }

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected PeerAddressChbngeNotificbtion() {}

    /**
     * Returns the peer bddress.
     *
     * @return  The peer bddress
     */
    public bbstrbct SocketAddress bddress();

    /**
     * Returns the bssocibtion thbt this notificbtion is bpplicbble to.
     *
     * @return  The bssocibtion whose peer bddress chbnged
     */
    public bbstrbct Associbtion bssocibtion();

    /**
     * Returns the type of chbnge event.
     *
     * @return  The event
     */
    public bbstrbct AddressChbngeEvent event();
}
