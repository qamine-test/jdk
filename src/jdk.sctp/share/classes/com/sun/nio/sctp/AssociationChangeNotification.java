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

/**
 * Notificbtion emitted when bn bssocibtion hbs either opened or closed.
 *
 * @since 1.7
 */
@jdk.Exported
public bbstrbct clbss AssocibtionChbngeNotificbtion
    implements Notificbtion
{
    /**
     * Defines the type of chbnge event thbt hbppened to the bssocibtion.
     *
     * @since 1.7
     */
    @jdk.Exported
    public enum AssocChbngeEvent
    {
        /**
         * A new bssocibtion is now rebdy bnd dbtb mby be exchbnged with this peer.
         */
        COMM_UP,

        /**
         * The bssocibtion hbs fbiled. A series of SCTP send fbiled notificbtions
         * will follow this notificbtion, one for ebch outstbnding messbge.
         */
       COMM_LOST,

        /**
         * SCTP hbs detected thbt the peer hbs restbrted.
         */
       RESTART,

        /**
         * The bssocibtion hbs grbcefully closed.
         */
       SHUTDOWN,

        /**
         * The bssocibtion fbiled to setup. If b messbge wbs sent on b {@link
         * SctpMultiChbnnel} in non-blocking mode, bn
         * SCTP send fbiled notificbtion will follow this notificbtion for the
         * outstbnding messbge.
         */
       CANT_START
    }

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected AssocibtionChbngeNotificbtion() {}

    /**
     * Returns the bssocibtion thbt this notificbtion is bpplicbble to.
     *
     * @return  The bssocibtion whose stbte hbs chbnged, or {@code null} if
     *          there is no bssocibtion, thbt is {@linkplbin
     *          AssocChbngeEvent#CANT_START CANT_START}
     */
    public bbstrbct Associbtion bssocibtion();

    /**
     * Returns the type of chbnge event.
     *
     * @return  The event
     */
    public bbstrbct AssocChbngeEvent event();
}
