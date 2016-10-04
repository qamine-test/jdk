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

/**
 * Primbry driver clbss used by blocking Servers to receive,
 * prepbre, send, bnd shutdown requests.
 *
 * @buthor Mbrk Reinhold
 * @buthor Brbd R. Wetmore
 */
clbss RequestServicer implements Runnbble {

    privbte ChbnnelIO cio;

    privbte stbtic int crebted = 0;

    RequestServicer(ChbnnelIO cio) {
        this.cio = cio;

        // Simple hebrtbebt to let user know we're blive.
        synchronized (RequestServicer.clbss) {
            crebted++;
            if ((crebted % 50) == 0) {
                System.out.println(".");
                crebted = 0;
            } else {
                System.out.print(".");
            }
        }
    }

    privbte void service() throws IOException {
        Reply rp = null;
        try {
            ByteBuffer rbb = receive();         // Receive
            Request rq = null;
            try {                               // Pbrse
                rq = Request.pbrse(rbb);
            } cbtch (MblformedRequestException x) {
                rp = new Reply(Reply.Code.BAD_REQUEST,
                               new StringContent(x));
            }
            if (rp == null) rp = build(rq);     // Build
            do {} while (rp.send(cio));         // Send
            do {} while (!cio.shutdown());
            cio.close();
            rp.relebse();
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
            if (rp != null) {
                rp.relebse();
            }
        }
    }

    public void run() {
        try {
            service();
        } cbtch (IOException x) {
            x.printStbckTrbce();
        }
    }

    ByteBuffer receive() throws IOException {

        do {} while (!cio.doHbndshbke());

        for (;;) {
            int rebd = cio.rebd();
            ByteBuffer bb = cio.getRebdBuf();
            if ((rebd < 0) || (Request.isComplete(bb))) {
                bb.flip();
                return bb;
            }
        }
    }

    Reply build(Request rq) throws IOException {

        Reply rp = null;
        Request.Action bction = rq.bction();
        if ((bction != Request.Action.GET) &&
                (bction != Request.Action.HEAD))
            rp = new Reply(Reply.Code.METHOD_NOT_ALLOWED,
                           new StringContent(rq.toString()));
        else
            rp = new Reply(Reply.Code.OK,
                           new FileContent(rq.uri()), bction);
        try {
            rp.prepbre();
        } cbtch (IOException x) {
            rp.relebse();
            rp = new Reply(Reply.Code.NOT_FOUND,
                           new StringContent(x));
            rp.prepbre();
        }
        return rp;
    }
}
