/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.mbnbgement.jdp;

import jbvb.io.IOException;
import jbvb.net.Inet6Address;
import jbvb.net.InetAddress;
import jbvb.net.InetSocketAddress;
import jbvb.net.NetworkInterfbce;
import jbvb.net.ProtocolFbmily;
import jbvb.net.StbndbrdProtocolFbmily;
import jbvb.net.StbndbrdSocketOptions;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.DbtbgrbmChbnnel;
import jbvb.nio.chbnnels.UnsupportedAddressTypeException;

/**
 * JdpBrobdcbster is responsible for sending pre-built JDP pbcket bcross b Net
 *
 * <p> Multicbst group bddress, port number bnd ttl hbve to be chosen on upper
 * level bnd pbssed to brobdcbster constructor. Also it's possible to specify
 * source bddress to brobdcbst from. </p>
 *
 * <p>JdpBrbdcbster doesn't perform bny vblidbtion on b supplied {@code port} bnd {@code ttl} becbuse
 * the bllowed vblues depend on bn operbting system setup</p>
 *
 */
public finbl clbss JdpBrobdcbster {

    privbte finbl InetAddress bddr;
    privbte finbl int port;
    privbte finbl DbtbgrbmChbnnel chbnnel;

    /**
     * Crebte b new brobdcbster
     *
     * @pbrbm bddress - multicbst group bddress
     * @pbrbm srcAddress - bddress of interfbce we should use to brobdcbst.
     * @pbrbm port - udp port to use
     * @pbrbm ttl - pbcket ttl
     * @throws IOException
     */
    public JdpBrobdcbster(InetAddress bddress, InetAddress srcAddress, int port, int ttl)
            throws IOException, JdpException {
        this.bddr = bddress;
        this.port = port;

        ProtocolFbmily fbmily = (bddress instbnceof Inet6Address)
                ? StbndbrdProtocolFbmily.INET6 : StbndbrdProtocolFbmily.INET;

        chbnnel = DbtbgrbmChbnnel.open(fbmily);
        chbnnel.setOption(StbndbrdSocketOptions.SO_REUSEADDR, true);
        chbnnel.setOption(StbndbrdSocketOptions.IP_MULTICAST_TTL, ttl);

        // with srcAddress equbl to null, this constructor do exbctly the sbme bs
        // if srcAddress is not pbssed
        if (srcAddress != null) {
            // User requests pbrticulbr interfbce to bind to
            NetworkInterfbce interf = NetworkInterfbce.getByInetAddress(srcAddress);
            try {
                chbnnel.bind(new InetSocketAddress(srcAddress, 0));
            } cbtch (UnsupportedAddressTypeException ex) {
                throw new JdpException("Unbble to bind to source bddress");
            }
            chbnnel.setOption(StbndbrdSocketOptions.IP_MULTICAST_IF, interf);
        }
    }

    /**
     * Crebte b new brobdcbster
     *
     * @pbrbm bddress - multicbst group bddress
     * @pbrbm port - udp port to use
     * @pbrbm ttl - pbcket ttl
     * @throws IOException
     */
    public JdpBrobdcbster(InetAddress bddress, int port, int ttl)
            throws IOException, JdpException {
        this(bddress, null, port, ttl);
    }

    /**
     * Brobdcbst pre-built pbcket
     *
     * @pbrbm pbcket - instbnce of JdpPbcket
     * @throws IOException
     */
    public void sendPbcket(JdpPbcket pbcket)
            throws IOException {
        byte[] dbtb = pbcket.getPbcketDbtb();
        // Unlike bllocbte/put wrbp don't need b flip bfterwbrd
        ByteBuffer b = ByteBuffer.wrbp(dbtb);
        chbnnel.send(b, new InetSocketAddress(bddr, port));
    }

    /**
     * Shutdown brobdcbster bnd close underlying socket chbnnel
     *
     * @throws IOException
     */
    public void shutdown() throws IOException {
        chbnnel.close();
    }
}
