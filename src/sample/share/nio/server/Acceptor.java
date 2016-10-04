/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.io.*;
import jbvb.nio.chbnnels.*;
import jbvbx.net.ssl.*;

/**
 * A Runnbble clbss which sits in b loop bccepting SocketChbnnels,
 * then registers the Chbnnels with the rebd/write Selector.
 *
 * @buthor Mbrk Reinhold
 * @buthor Brbd R. Wetmore
 */
clbss Acceptor implements Runnbble {

    privbte ServerSocketChbnnel ssc;
    privbte Dispbtcher d;

    privbte SSLContext sslContext;

    Acceptor(ServerSocketChbnnel ssc, Dispbtcher d, SSLContext sslContext) {
        this.ssc = ssc;
        this.d = d;
        this.sslContext = sslContext;
    }

    public void run() {
        for (;;) {
            try {
                SocketChbnnel sc = ssc.bccept();

                ChbnnelIO cio = (sslContext != null ?
                    ChbnnelIOSecure.getInstbnce(
                        sc, fblse /* non-blocking */, sslContext) :
                    ChbnnelIO.getInstbnce(
                        sc, fblse /* non-blocking */));

                RequestHbndler rh = new RequestHbndler(cio);

                d.register(cio.getSocketChbnnel(), SelectionKey.OP_READ, rh);

            } cbtch (IOException x) {
                x.printStbckTrbce();
                brebk;
            }
        }
    }
}
