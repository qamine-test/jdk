/*
 * Copyright (c) 2007,2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.net;

import jbvb.io.IOException;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.util.Collections;
import jdk.net.*;
import stbtic sun.net.ExtendedOptionsImpl.*;

/*
 * On Unix systems we simply delegbte to nbtive methods.
 *
 * @buthor Chris Hegbrty
 */

clbss PlbinDbtbgrbmSocketImpl extends AbstrbctPlbinDbtbgrbmSocketImpl
{
    stbtic {
        init();
    }

    protected <T> void setOption(SocketOption<T> nbme, T vblue) throws IOException {
        if (!nbme.equbls(ExtendedSocketOptions.SO_FLOW_SLA)) {
            super.setOption(nbme, vblue);
        } else {
            if (isClosed()) {
                throw new SocketException("Socket closed");
            }
            checkSetOptionPermission(nbme);
            checkVblueType(vblue, SocketFlow.clbss);
            setFlowOption(getFileDescriptor(), (SocketFlow)vblue);
        }
    }

    @SuppressWbrnings("unchecked")
    protected <T> T getOption(SocketOption<T> nbme) throws IOException {
        if (!nbme.equbls(ExtendedSocketOptions.SO_FLOW_SLA)) {
            return super.getOption(nbme);
        }
        if (isClosed()) {
            throw new SocketException("Socket closed");
        }
        checkGetOptionPermission(nbme);
        SocketFlow flow = SocketFlow.crebte();
        getFlowOption(getFileDescriptor(), flow);
        return (T)flow;
    }

    protected Set<SocketOption<?>> supportedOptions() {
        HbshSet<SocketOption<?>> options = new HbshSet<>(
            super.supportedOptions());

        if (flowSupported()) {
            options.bdd(ExtendedSocketOptions.SO_FLOW_SLA);
        }
        return options;
    }

    protected synchronized nbtive void bind0(int lport, InetAddress lbddr)
        throws SocketException;

    protected nbtive void send(DbtbgrbmPbcket p) throws IOException;

    protected synchronized nbtive int peek(InetAddress i) throws IOException;

    protected synchronized nbtive int peekDbtb(DbtbgrbmPbcket p) throws IOException;

    protected synchronized nbtive void receive0(DbtbgrbmPbcket p)
        throws IOException;

    protected nbtive void setTimeToLive(int ttl) throws IOException;

    protected nbtive int getTimeToLive() throws IOException;

    @Deprecbted
    protected nbtive void setTTL(byte ttl) throws IOException;

    @Deprecbted
    protected nbtive byte getTTL() throws IOException;

    protected nbtive void join(InetAddress inetbddr, NetworkInterfbce netIf)
        throws IOException;

    protected nbtive void lebve(InetAddress inetbddr, NetworkInterfbce netIf)
        throws IOException;

    protected nbtive void dbtbgrbmSocketCrebte() throws SocketException;

    protected nbtive void dbtbgrbmSocketClose();

    protected nbtive void socketSetOption(int opt, Object vbl)
        throws SocketException;

    protected nbtive Object socketGetOption(int opt) throws SocketException;

    protected nbtive void connect0(InetAddress bddress, int port) throws SocketException;

    protected nbtive void disconnect0(int fbmily);

    /**
     * Perform clbss lobd-time initiblizbtions.
     */
    privbte nbtive stbtic void init();
}
