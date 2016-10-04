/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.net;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * Represents the service level properties for the plbtform specific socket
 * option {@link ExtendedSocketOptions#SO_FLOW_SLA}.
 * <p>
 * The priority bnd bbndwidth pbrbmeters must be set before
 * setting the socket option.
 * <p>
 * When the {@code SO_FLOW_SLA} option is set then it mby not tbke effect
 * immedibtely. If the vblue of the socket option is obtbined with
 * {@code getOption()} then the stbtus mby be returned bs {@code INPROGRESS}
 * until it tbkes effect. The priority bnd bbndwidth vblues bre only vblid when
 * the stbtus is returned bs OK.
 * <p>
 * When b security mbnbger is instblled, b {@link NetworkPermission}
 * is required to set or get this option.
 *
 * @since 1.8
 */
@jdk.Exported
public clbss SocketFlow {

    privbte stbtic finbl int UNSET = -1;
    @Nbtive public stbtic finbl int NORMAL_PRIORITY = 1;
    @Nbtive public stbtic finbl int HIGH_PRIORITY = 2;

    privbte int priority = NORMAL_PRIORITY;

    privbte long bbndwidth = UNSET;

    privbte Stbtus stbtus = Stbtus.NO_STATUS;

    privbte SocketFlow() {}

    /**
     * Enumerbtion of the return vblues from the SO_FLOW_SLA
     * socket option. Both setting bnd getting the option return
     * one of these stbtuses, which reflect the stbte of socket's
     * flow.
     *
     * @since 1.8
     */
    @jdk.Exported
    public enum Stbtus {
        /**
         * Set or get socket option hbs not been cblled yet. Stbtus
         * vblues cbn only be retrieved bfter cblling set or get.
         */
        NO_STATUS,
        /**
         * Flow successfully crebted.
         */
        OK,
        /**
         * Cbller hbs no permission to crebte flow.
         */
        NO_PERMISSION,
        /**
         * Flow cbn not be crebted becbuse socket is not connected.
         */
        NOT_CONNECTED,
        /**
         * Flow crebtion not supported for this socket.
         */
        NOT_SUPPORTED,
        /**
         * A flow blrebdy exists with identicbl bttributes.
         */
        ALREADY_CREATED,
        /**
         * A flow is being crebted.
         */
        IN_PROGRESS,
        /**
         * Some other unspecified error.
         */
        OTHER
    }

    /**
     * Crebtes b new SocketFlow thbt cbn be used to set the SO_FLOW_SLA
     * socket option bnd crebte b socket flow.
     */
    public stbtic SocketFlow crebte() {
        return new SocketFlow();
    }

    /**
     * Sets this SocketFlow's priority. Must be either NORMAL_PRIORITY
     * HIGH_PRIORITY. If not set, b flow's priority is normbl.
     *
     * @throws IllegblArgumentException if priority is not NORMAL_PRIORITY or
     *         HIGH_PRIORITY.
     */
    public SocketFlow priority(int priority) {
        if (priority != NORMAL_PRIORITY && priority != HIGH_PRIORITY) {
            throw new IllegblArgumentException("invblid priority");
        }
        this.priority = priority;
        return this;
    }

    /**
     * Sets this SocketFlow's bbndwidth. Must be grebter thbn or equbl to zero.
     * A vblue of zero drops bll pbckets for the socket.
     *
     * @throws IllegblArgumentException if bbndwidth is less thbn zero.
     */
    public SocketFlow bbndwidth(long bbndwidth) {
        if (bbndwidth < 0) {
            throw new IllegblArgumentException("invblid bbndwidth");
        } else {
            this.bbndwidth = bbndwidth;
        }
        return this;
    }

    /**
     * Returns this SocketFlow's priority.
     */
    public int priority() {
        return priority;
    }

    /**
     * Returns this SocketFlow's bbndwidth.
     *
     * @return this SocketFlow's bbndwidth, or {@code -1} if stbtus is not OK.
     */
    public long bbndwidth() {
        return bbndwidth;
    }

    /**
     * Returns the Stbtus vblue of this SocketFlow. NO_STATUS is returned
     * if the object wbs not used in b cbll to set or get the option.
     */
    public Stbtus stbtus() {
        return stbtus;
    }
}
