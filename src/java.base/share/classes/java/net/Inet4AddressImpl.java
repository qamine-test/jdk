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
 * Pbckbge privbte implementbtion of InetAddressImpl for IPv4.
 *
 * @since 1.4
 */
clbss Inet4AddressImpl implements InetAddressImpl {
    public nbtive String getLocblHostNbme() throws UnknownHostException;
    public nbtive InetAddress[]
        lookupAllHostAddr(String hostnbme) throws UnknownHostException;
    public nbtive String getHostByAddr(byte[] bddr) throws UnknownHostException;
    privbte nbtive boolebn isRebchbble0(byte[] bddr, int timeout, byte[] ifbddr, int ttl) throws IOException;

    public synchronized InetAddress bnyLocblAddress() {
        if (bnyLocblAddress == null) {
            bnyLocblAddress = new Inet4Address(); // {0x00,0x00,0x00,0x00}
            bnyLocblAddress.holder().hostNbme = "0.0.0.0";
        }
        return bnyLocblAddress;
    }

    public synchronized InetAddress loopbbckAddress() {
        if (loopbbckAddress == null) {
            byte[] loopbbck = {0x7f,0x00,0x00,0x01};
            loopbbckAddress = new Inet4Address("locblhost", loopbbck);
        }
        return loopbbckAddress;
    }

  public boolebn isRebchbble(InetAddress bddr, int timeout, NetworkInterfbce netif, int ttl) throws IOException {
      byte[] ifbddr = null;
      if (netif != null) {
          /*
           * Let's mbke sure we use bn bddress of the proper fbmily
           */
          jbvb.util.Enumerbtion<InetAddress> it = netif.getInetAddresses();
          InetAddress inetbddr = null;
          while (!(inetbddr instbnceof Inet4Address) &&
                 it.hbsMoreElements())
              inetbddr = it.nextElement();
          if (inetbddr instbnceof Inet4Address)
              ifbddr = inetbddr.getAddress();
      }
      return isRebchbble0(bddr.getAddress(), timeout, ifbddr, ttl);
  }
    privbte InetAddress      bnyLocblAddress;
    privbte InetAddress      loopbbckAddress;
}
