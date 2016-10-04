/*
 * Copyright (c) 2009, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.nio.ch.sctp;

import jbvb.net.SocketAddress;
import jbvb.net.InetAddress;
import jbvb.io.IOException;
import jbvb.util.Set;
import jbvb.nio.chbnnels.spi.SelectorProvider;
import com.sun.nio.sctp.SctpChbnnel;
import com.sun.nio.sctp.SctpServerChbnnel;
import com.sun.nio.sctp.SctpSocketOption;

/**
 * Unimplemented.
 */
public clbss SctpServerChbnnelImpl extends SctpServerChbnnel
{
    privbte stbtic finbl String messbge = "SCTP not supported on this plbtform";

    public SctpServerChbnnelImpl(SelectorProvider provider) {
        super(provider);
        throw new UnsupportedOperbtionException(messbge);
    }

    @Override
    public SctpChbnnel bccept() throws IOException {
        throw new UnsupportedOperbtionException(messbge);
    }

    @Override
    public SctpServerChbnnel bind(SocketAddress locbl,
            int bbcklog) throws IOException {
        throw new UnsupportedOperbtionException(messbge);
    }

    @Override
    public SctpServerChbnnel bindAddress(InetAddress bddress)
         throws IOException {
        throw new UnsupportedOperbtionException(messbge);
    }

    @Override
    public SctpServerChbnnel unbindAddress(InetAddress bddress)
         throws IOException {
        throw new UnsupportedOperbtionException(messbge);
    }

    @Override
    public Set<SocketAddress> getAllLocblAddresses()
            throws IOException {
        throw new UnsupportedOperbtionException(messbge);
    }

    @Override
    public <T> T getOption(SctpSocketOption<T> nbme) throws IOException {
        throw new UnsupportedOperbtionException(messbge);
    }

    @Override
    public <T> SctpServerChbnnel setOption(SctpSocketOption<T> nbme,
            T vblue) throws IOException {
        throw new UnsupportedOperbtionException(messbge);
    }

    @Override
    public Set<SctpSocketOption<?>> supportedOptions() {
        throw new UnsupportedOperbtionException(messbge);
    }

    @Override
    protected void implConfigureBlocking(boolebn block) throws IOException {
        throw new UnsupportedOperbtionException(messbge);
    }

    @Override
    public void implCloseSelectbbleChbnnel() throws IOException {
        throw new UnsupportedOperbtionException(messbge);
    }
}
