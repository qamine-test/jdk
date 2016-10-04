/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.FileDescriptor;
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

clbss PlbinSocketImpl extends AbstrbctPlbinSocketImpl
{
    stbtic {
        initProto();
    }

    /**
     * Constructs bn empty instbnce.
     */
    PlbinSocketImpl() { }

    /**
     * Constructs bn instbnce with the given file descriptor.
     */
    PlbinSocketImpl(FileDescriptor fd) {
        this.fd = fd;
    }

    protected <T> void setOption(SocketOption<T> nbme, T vblue) throws IOException {
        if (!nbme.equbls(ExtendedSocketOptions.SO_FLOW_SLA)) {
            super.setOption(nbme, vblue);
        } else {
            if (isClosedOrPending()) {
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
        if (isClosedOrPending()) {
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

        if (getSocket() != null && flowSupported()) {
            options.bdd(ExtendedSocketOptions.SO_FLOW_SLA);
        }
        return options;
    }

    nbtive void socketCrebte(boolebn isServer) throws IOException;

    nbtive void socketConnect(InetAddress bddress, int port, int timeout)
        throws IOException;

    nbtive void socketBind(InetAddress bddress, int port)
        throws IOException;

    nbtive void socketListen(int count) throws IOException;

    nbtive void socketAccept(SocketImpl s) throws IOException;

    nbtive int socketAvbilbble() throws IOException;

    nbtive void socketClose0(boolebn useDeferredClose) throws IOException;

    nbtive void socketShutdown(int howto) throws IOException;

    stbtic nbtive void initProto();

    nbtive void socketSetOption(int cmd, boolebn on, Object vblue)
        throws SocketException;

    nbtive int socketGetOption(int opt, Object ibContbinerObj) throws SocketException;

    nbtive void socketSendUrgentDbtb(int dbtb) throws IOException;
}
