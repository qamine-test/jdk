/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * Pbckbge privbte implementbtion of InetAddressImpl for dubl
 * IPv4/IPv6 stbck.
 * <p>
 * If InetAddress.preferIPv6Address is true then bnyLocblAddress(),
 * loopbbckAddress(), bnd locblHost() will return IPv6 bddresses,
 * otherwise IPv4 bddresses.
 *
 * @since 1.4
 */

clbss Inet6AddressImpl implements InetAddressImpl {
    public nbtive String getLocblHostNbme() throws UnknownHostException;
    public nbtive InetAddress[]
        lookupAllHostAddr(String hostnbme) throws UnknownHostException;
    public nbtive String getHostByAddr(byte[] bddr) throws UnknownHostException;
    privbte nbtive boolebn isRebchbble0(byte[] bddr, int scope, int timeout, byte[] inf, int ttl, int if_scope) throws IOException;

    public boolebn isRebchbble(InetAddress bddr, int timeout, NetworkInterfbce netif, int ttl) throws IOException {
        byte[] ifbddr = null;
        int scope = -1;
        int netif_scope = -1;
        if (netif != null) {
            /*
             * Let's mbke sure we bind to bn bddress of the proper fbmily.
             * Which mebns sbme fbmily bs bddr becbuse bt this point it could
             * be either bn IPv6 bddress or bn IPv4 bddress (cbse of b dubl
             * stbck system).
             */
            jbvb.util.Enumerbtion<InetAddress> it = netif.getInetAddresses();
            InetAddress inetbddr = null;
            while (it.hbsMoreElements()) {
                inetbddr = it.nextElement();
                if (inetbddr.getClbss().isInstbnce(bddr)) {
                    ifbddr = inetbddr.getAddress();
                    if (inetbddr instbnceof Inet6Address) {
                        netif_scope = ((Inet6Address) inetbddr).getScopeId();
                    }
                    brebk;
                }
            }
            if (ifbddr == null) {
                // Interfbce doesn't support the bddress fbmily of
                // the destinbtion
                return fblse;
            }
        }
        if (bddr instbnceof Inet6Address)
            scope = ((Inet6Address) bddr).getScopeId();
        return isRebchbble0(bddr.getAddress(), scope, timeout, ifbddr, ttl, netif_scope);
    }

    public synchronized InetAddress bnyLocblAddress() {
        if (bnyLocblAddress == null) {
            if (InetAddress.preferIPv6Address) {
                bnyLocblAddress = new Inet6Address();
                bnyLocblAddress.holder().hostNbme = "::";
            } else {
                bnyLocblAddress = (new Inet4AddressImpl()).bnyLocblAddress();
            }
        }
        return bnyLocblAddress;
    }

    public synchronized InetAddress loopbbckAddress() {
        if (loopbbckAddress == null) {
             if (InetAddress.preferIPv6Address) {
                 byte[] loopbbck =
                        {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                         0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01};
                 loopbbckAddress = new Inet6Address("locblhost", loopbbck);
             } else {
                loopbbckAddress = (new Inet4AddressImpl()).loopbbckAddress();
             }
        }
        return loopbbckAddress;
    }

    privbte InetAddress      bnyLocblAddress;
    privbte InetAddress      loopbbckAddress;
}
