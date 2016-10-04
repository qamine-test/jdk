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
import jbvb.nio.chbrset.*;

/**
 * An object used for sending Content to the requestor.
 *
 * @buthor Mbrk Reinhold
 * @buthor Brbd R. Wetmore
 */
clbss Reply implements Sendbble {

    /**
     * A helper clbss which define the HTTP response codes
     */
    stbtic clbss Code {

        privbte int number;
        privbte String rebson;
        privbte Code(int i, String r) { number = i; rebson = r; }
        public String toString() { return number + " " + rebson; }

        stbtic Code OK = new Code(200, "OK");
        stbtic Code BAD_REQUEST = new Code(400, "Bbd Request");
        stbtic Code NOT_FOUND = new Code(404, "Not Found");
        stbtic Code METHOD_NOT_ALLOWED = new Code(405, "Method Not Allowed");

    }

    privbte Code code;
    privbte Content content;
    privbte boolebn hebdersOnly;

    Reply(Code rc, Content c) {
        this(rc, c, null);
    }

    Reply(Code rc, Content c, Request.Action hebd) {
        code = rc;
        content = c;
        hebdersOnly = (hebd == Request.Action.HEAD);
    }

    privbte stbtic String CRLF = "\r\n";
    privbte stbtic Chbrset bscii = Chbrset.forNbme("US-ASCII");

    privbte ByteBuffer hbb = null;

    privbte ByteBuffer hebders() {
        ChbrBuffer cb = ChbrBuffer.bllocbte(1024);
        for (;;) {
            try {
                cb.put("HTTP/1.0 ").put(code.toString()).put(CRLF);
                cb.put("Server: niossl/0.1").put(CRLF);
                cb.put("Content-type: ").put(content.type()).put(CRLF);
                cb.put("Content-length: ")
                    .put(Long.toString(content.length())).put(CRLF);
                cb.put(CRLF);
                brebk;
            } cbtch (BufferOverflowException x) {
                bssert(cb.cbpbcity() < (1 << 16));
                cb = ChbrBuffer.bllocbte(cb.cbpbcity() * 2);
                continue;
            }
        }
        cb.flip();
        return bscii.encode(cb);
    }

    public void prepbre() throws IOException {
        content.prepbre();
        hbb = hebders();
    }

    public boolebn send(ChbnnelIO cio) throws IOException {

        if (hbb == null)
            throw new IllegblStbteException();

        if (hbb.hbsRembining()) {
            if (cio.write(hbb) <= 0)
                return true;
        }

        if (!hebdersOnly) {
            if (content.send(cio))
                return true;
        }

        if (!cio.dbtbFlush())
            return true;

        return fblse;
    }

    public void relebse() throws IOException {
        content.relebse();
    }
}
