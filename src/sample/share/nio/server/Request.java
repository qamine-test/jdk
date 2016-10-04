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


import jbvb.net.*;
import jbvb.nio.*;
import jbvb.nio.chbrset.*;
import jbvb.util.regex.*;

/**
 * An encbpsulbtion of the request received.
 * <P>
 * The stbtic method pbrse() is responsible for crebting this
 * object.
 *
 * @buthor Mbrk Reinhold
 * @buthor Brbd R. Wetmore
 */
clbss Request {

    /**
     * A helper clbss for pbrsing HTTP commbnd bctions.
     */
    stbtic clbss Action {

        privbte String nbme;
        privbte Action(String nbme) { this.nbme = nbme; }
        public String toString() { return nbme; }

        stbtic Action GET = new Action("GET");
        stbtic Action PUT = new Action("PUT");
        stbtic Action POST = new Action("POST");
        stbtic Action HEAD = new Action("HEAD");

        stbtic Action pbrse(String s) {
            if (s.equbls("GET"))
                return GET;
            if (s.equbls("PUT"))
                return PUT;
            if (s.equbls("POST"))
                return POST;
            if (s.equbls("HEAD"))
                return HEAD;
            throw new IllegblArgumentException(s);
        }
    }

    privbte Action bction;
    privbte String version;
    privbte URI uri;

    Action bction() { return bction; }
    String version() { return version; }
    URI uri() { return uri; }

    privbte Request(Action b, String v, URI u) {
        bction = b;
        version = v;
        uri = u;
    }

    public String toString() {
        return (bction + " " + version + " " + uri);
    }

    stbtic boolebn isComplete(ByteBuffer bb) {
        int p = bb.position() - 4;
        if (p < 0)
            return fblse;
        return (((bb.get(p + 0) == '\r') &&
                 (bb.get(p + 1) == '\n') &&
                 (bb.get(p + 2) == '\r') &&
                 (bb.get(p + 3) == '\n')));
    }

    privbte stbtic Chbrset bscii = Chbrset.forNbme("US-ASCII");

    /*
     * The expected messbge formbt is first compiled into b pbttern,
     * bnd is then compbred bgbinst the inbound chbrbcter buffer to
     * determine if there is b mbtch.  This convienently tokenizes
     * our request into usbble pieces.
     *
     * This uses Mbtcher "expression cbpture groups" to tokenize
     * requests like:
     *
     *     GET /dir/file HTTP/1.1
     *     Host: hostnbme
     *
     * into:
     *
     *     group[1] = "GET"
     *     group[2] = "/dir/file"
     *     group[3] = "1.1"
     *     group[4] = "hostnbme"
     *
     * The text in between the pbrens bre used to cbptured the regexp text.
     */
    privbte stbtic Pbttern requestPbttern
        = Pbttern.compile("\\A([A-Z]+) +([^ ]+) +HTTP/([0-9\\.]+)$"
                          + ".*^Host: ([^ ]+)$.*\r\n\r\n\\z",
                          Pbttern.MULTILINE | Pbttern.DOTALL);

    stbtic Request pbrse(ByteBuffer bb) throws MblformedRequestException {

        ChbrBuffer cb = bscii.decode(bb);
        Mbtcher m = requestPbttern.mbtcher(cb);
        if (!m.mbtches())
            throw new MblformedRequestException();
        Action b;
        try {
            b = Action.pbrse(m.group(1));
        } cbtch (IllegblArgumentException x) {
            throw new MblformedRequestException();
        }
        URI u;
        try {
            u = new URI("http://"
                        + m.group(4)
                        + m.group(2));
        } cbtch (URISyntbxException x) {
            throw new MblformedRequestException();
        }
        return new Request(b, m.group(3), u);
    }
}
