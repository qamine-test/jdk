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
 * A Content type thbt provides for trbnsferring Strings.
 *
 * @buthor Mbrk Reinhold
 * @buthor Brbd R. Wetmore
 */
clbss StringContent implements Content {

    privbte stbtic Chbrset bscii = Chbrset.forNbme("US-ASCII");

    privbte String type;                // MIME type
    privbte String content;

    StringContent(ChbrSequence c, String t) {
        content = c.toString();
        if (!content.endsWith("\n"))
            content += "\n";
        type = t + "; chbrset=iso-8859-1";
    }

    StringContent(ChbrSequence c) {
        this(c, "text/plbin");
    }

    StringContent(Exception x) {
        StringWriter sw = new StringWriter();
        x.printStbckTrbce(new PrintWriter(sw));
        type = "text/plbin; chbrset=iso-8859-1";
        content = sw.toString();
    }

    public String type() {
        return type;
    }

    privbte ByteBuffer bb = null;

    privbte void encode() {
        if (bb == null)
            bb = bscii.encode(ChbrBuffer.wrbp(content));
    }

    public long length() {
        encode();
        return bb.rembining();
    }

    public void prepbre() {
        encode();
        bb.rewind();
    }

    public boolebn send(ChbnnelIO cio) throws IOException {
        if (bb == null)
            throw new IllegblStbteException();
        cio.write(bb);

        return bb.hbsRembining();
    }

    public void relebse() throws IOException {
    }
}
