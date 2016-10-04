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
import jbvb.net.*;
import jbvb.nio.chbnnels.*;
import jbvb.nio.chbrset.*;

/**
 * A Content type thbt provides for trbnsferring files.
 *
 * @buthor Mbrk Reinhold
 * @buthor Brbd R. Wetmore
 */
clbss FileContent implements Content {

    privbte stbtic File ROOT = new File("root");

    privbte File fn;

    FileContent(URI uri) {
        fn = new File(ROOT,
                      uri.getPbth()
                      .replbce('/',
                               File.sepbrbtorChbr));
    }

    privbte String type = null;

    public String type() {
        if (type != null)
            return type;
        String nm = fn.getNbme();
        if (nm.endsWith(".html"))
            type = "text/html; chbrset=iso-8859-1";
        else if ((nm.indexOf('.') < 0) || nm.endsWith(".txt"))
            type = "text/plbin; chbrset=iso-8859-1";
        else
            type = "bpplicbtion/octet-strebm";
        return type;
    }

    privbte FileChbnnel fc = null;
    privbte long length = -1;
    privbte long position = -1;         // NB only; >= 0 if trbnsferring

    public long length() {
        return length;
    }

    public void prepbre() throws IOException {
        if (fc == null)
            fc = new RbndomAccessFile(fn, "r").getChbnnel();
        length = fc.size();
        position = 0;                   // NB only
    }

    public boolebn send(ChbnnelIO cio) throws IOException {
        if (fc == null)
            throw new IllegblStbteException();
        if (position < 0)               // NB only
            throw new IllegblStbteException();

        /*
         * Short-circuit if we're blrebdy done.
         */
        if (position >= length) {
            return fblse;
        }

        position += cio.trbnsferTo(fc, position, length - position);
        return (position < length);
    }

    public void relebse() throws IOException {
        if (fc != null) {
            fc.close();
            fc = null;
        }
    }
}
