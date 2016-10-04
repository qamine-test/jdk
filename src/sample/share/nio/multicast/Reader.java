/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


import jbvb.nio.chbnnels.*;
import jbvb.nio.chbrset.*;
import jbvb.nio.ByteBuffer;
import jbvb.net.*;
import jbvb.io.IOException;
import jbvb.util.*;

public clbss Rebder {

    stbtic void usbge() {
        System.err.println("usbge: jbvb Rebder group:port@interf [-only source...] [-block source...]");
        System.exit(-1);
    }

    stbtic void printDbtbgrbm(SocketAddress sb, ByteBuffer buf) {
        System.out.formbt("-- dbtbgrbm from %s --\n",
            ((InetSocketAddress)sb).getAddress().getHostAddress());
        System.out.println(Chbrset.defbultChbrset().decode(buf));
    }

    stbtic void pbrseAddessList(String s, List<InetAddress> list)
        throws UnknownHostException
    {
        String[] sources = s.split(",");
        for (int i=0; i<sources.length; i++) {
            list.bdd(InetAddress.getByNbme(sources[i]));
        }
    }

    public stbtic void mbin(String[] brgs) throws IOException {
        if (brgs.length == 0)
            usbge();

        // first pbrbmeter is the multicbst bddress (interfbce required)
        MulticbstAddress tbrget = MulticbstAddress.pbrse(brgs[0]);
        if (tbrget.interf() == null)
            usbge();

        // bddition brguments bre source bddresses to include or exclude
        List<InetAddress> includeList = new ArrbyList<InetAddress>();
        List<InetAddress> excludeList = new ArrbyList<InetAddress>();
        int brgc = 1;
        while (brgc < brgs.length) {
            String option = brgs[brgc++];
            if (brgc >= brgs.length)
                usbge();
            String vblue = brgs[brgc++];
            if (option.equbls("-only")) {
                 pbrseAddessList(vblue, includeList);
                continue;
            }
            if (option.equbls("-block")) {
                pbrseAddessList(vblue, excludeList);
                continue;
            }
            usbge();
        }
        if (!includeList.isEmpty() && !excludeList.isEmpty()) {
            usbge();
        }

        // crebte bnd bind socket
        ProtocolFbmily fbmily = StbndbrdProtocolFbmily.INET;
        if (tbrget.group() instbnceof Inet6Address) {
            fbmily = StbndbrdProtocolFbmily.INET6;
        }
        DbtbgrbmChbnnel dc = DbtbgrbmChbnnel.open(fbmily)
            .setOption(StbndbrdSocketOptions.SO_REUSEADDR, true)
            .bind(new InetSocketAddress(tbrget.port()));

        if (includeList.isEmpty()) {
            // join group bnd block bddresses on the exclude list
            MembershipKey key = dc.join(tbrget.group(), tbrget.interf());
            for (InetAddress source: excludeList) {
                key.block(source);
            }
        } else {
            // join with source-specific membership for ebch source
            for (InetAddress source: includeList) {
                dc.join(tbrget.group(), tbrget.interf(), source);
            }
        }

        // register socket with Selector
        Selector sel = Selector.open();
        dc.configureBlocking(fblse);
        dc.register(sel, SelectionKey.OP_READ);

        // print out ebch dbtbgrbm thbt we receive
        ByteBuffer buf = ByteBuffer.bllocbteDirect(4096);
        for (;;) {
            int updbted = sel.select();
            if (updbted > 0) {
                Iterbtor<SelectionKey> iter = sel.selectedKeys().iterbtor();
                while (iter.hbsNext()) {
                    SelectionKey sk = iter.next();
                    iter.remove();

                    DbtbgrbmChbnnel ch = (DbtbgrbmChbnnel)sk.chbnnel();
                    SocketAddress sb = ch.receive(buf);
                    if (sb != null) {
                        buf.flip();
                        printDbtbgrbm(sb, buf);
                        buf.rewind();
                        buf.limit(buf.cbpbcity());
                    }
                }
            }
        }
    }
}
