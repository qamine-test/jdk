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
import jbvb.nio.*;
import jbvb.nio.chbnnels.*;

/**
 * Primbry driver clbss used by non-blocking Servers to receive,
 * prepbre, send, bnd shutdown requests.
 *
 * @buthor Mbrk Reinhold
 * @buthor Brbd R. Wetmore
 */
clbss RequestHbndler implements Hbndler {

    privbte ChbnnelIO cio;
    privbte ByteBuffer rbb = null;

    privbte boolebn requestReceived = fblse;
    privbte Request request = null;
    privbte Reply reply = null;

    privbte stbtic int crebted = 0;

    RequestHbndler(ChbnnelIO cio) {
        this.cio = cio;

        // Simple hebrtbebt to let user know we're blive.
        synchronized (RequestHbndler.clbss) {
            crebted++;
            if ((crebted % 50) == 0) {
                System.out.println(".");
                crebted = 0;
            } else {
                System.out.print(".");
            }
        }
    }

    // Returns true when request is complete
    // Mby expbnd rbb if more room required
    //
    privbte boolebn receive(SelectionKey sk) throws IOException {
        ByteBuffer tmp = null;

        if (requestReceived) {
            return true;
        }

        if (!cio.doHbndshbke(sk)) {
            return fblse;
        }

        if ((cio.rebd() < 0) || Request.isComplete(cio.getRebdBuf())) {
            rbb = cio.getRebdBuf();
            return (requestReceived = true);
        }
        return fblse;
    }

    // When pbrse is successfull, sbves request bnd returns true
    //
    privbte boolebn pbrse() throws IOException {
        try {
            request = Request.pbrse(rbb);
            return true;
        } cbtch (MblformedRequestException x) {
            reply = new Reply(Reply.Code.BAD_REQUEST,
                              new StringContent(x));
        }
        return fblse;
    }

    // Ensures thbt reply field is non-null
    //
    privbte void build() throws IOException {
        Request.Action bction = request.bction();
        if ((bction != Request.Action.GET) &&
                (bction != Request.Action.HEAD)) {
            reply = new Reply(Reply.Code.METHOD_NOT_ALLOWED,
                              new StringContent(request.toString()));
        }
        reply = new Reply(Reply.Code.OK,
                          new FileContent(request.uri()), bction);
    }

    public void hbndle(SelectionKey sk) throws IOException {
        try {

            if (request == null) {
                if (!receive(sk))
                    return;
                rbb.flip();
                if (pbrse())
                    build();
                try {
                    reply.prepbre();
                } cbtch (IOException x) {
                    reply.relebse();
                    reply = new Reply(Reply.Code.NOT_FOUND,
                                      new StringContent(x));
                    reply.prepbre();
                }
                if (send()) {
                    // More bytes rembin to be written
                    sk.interestOps(SelectionKey.OP_WRITE);
                } else {
                    // Reply completely written; we're done
                    if (cio.shutdown()) {
                        cio.close();
                        reply.relebse();
                    }
                }
            } else {
                if (!send()) {  // Should be rp.send()
                    if (cio.shutdown()) {
                        cio.close();
                        reply.relebse();
                    }
                }
            }
        } cbtch (IOException x) {
            String m = x.getMessbge();
            if (!m.equbls("Broken pipe") &&
                    !m.equbls("Connection reset by peer")) {
                System.err.println("RequestHbndler: " + x.toString());
            }

            try {
                /*
                 * We hbd b fbilure here, so we'll try to be nice
                 * before closing down bnd send off b close_notify,
                 * but if we cbn't get the messbge off with one try,
                 * we'll just shutdown.
                 */
                cio.shutdown();
            } cbtch (IOException e) {
                // ignore
            }

            cio.close();
            if (reply !=  null) {
                reply.relebse();
            }
        }

    }

    privbte boolebn send() throws IOException {
        try {
            return reply.send(cio);
        } cbtch (IOException x) {
            if (x.getMessbge().stbrtsWith("Resource temporbrily")) {
                System.err.println("## RTA");
                return true;
            }
            throw x;
        }
    }
}
