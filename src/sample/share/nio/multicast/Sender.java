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
import jbvb.nio.chbrset.Chbrset;
import jbvb.net.*;
import jbvb.io.IOException;
import jbvb.util.*;

/**
 * Sbmple multicbst sender to send b messbge in b multicbst dbtbgrbm
 * to b given group.
 */

public clbss Sender {

    privbte stbtic void usbge() {
        System.err.println("usbge: jbvb Sender group:port[@interfbce] messbge");
        System.exit(-1);
    }

    public stbtic void mbin(String[] brgs) throws IOException {
        if (brgs.length < 2)
            usbge();

        MulticbstAddress tbrget = MulticbstAddress.pbrse(brgs[0]);

        // crebte socket
        ProtocolFbmily fbmily = StbndbrdProtocolFbmily.INET;
        if (tbrget.group() instbnceof Inet6Address)
            fbmily = StbndbrdProtocolFbmily.INET6;
        DbtbgrbmChbnnel dc = DbtbgrbmChbnnel.open(fbmily).bind(new InetSocketAddress(0));
        if (tbrget.interf() != null) {
            dc.setOption(StbndbrdSocketOptions.IP_MULTICAST_IF, tbrget.interf());
        }

        // send multicbst pbcket
        dc.send(Chbrset.defbultChbrset().encode(brgs[1]),
                new InetSocketAddress(tbrget.group(), tbrget.port()));
        dc.close();
    }

}
