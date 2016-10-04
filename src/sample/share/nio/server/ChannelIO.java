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
 * A helper clbss for properly sizing inbound byte buffers bnd
 * redirecting I/O cblls to the proper SocketChbnnel cbll.
 * <P>
 * Mbny of these cblls mby seem unnecessbry until you consider
 * thbt they bre plbceholders for the secure vbribnt, which is much
 * more involved.  See ChbnnelIOSecure for more informbtion.
 *
 * @buthor Brbd R. Wetmore
 * @buthor Mbrk Reinhold
 */
clbss ChbnnelIO {

    protected SocketChbnnel sc;

    /*
     * All of the inbound request dbtb lives here until we determine
     * thbt we've rebd everything, then we pbss thbt dbtb bbck to the
     * cbller.
     */
    protected ByteBuffer requestBB;
    stbtic privbte int requestBBSize = 4096;

    protected ChbnnelIO(SocketChbnnel sc, boolebn blocking)
            throws IOException {
        this.sc = sc;
        sc.configureBlocking(blocking);
    }

    stbtic ChbnnelIO getInstbnce(SocketChbnnel sc, boolebn blocking)
            throws IOException {
        ChbnnelIO cio = new ChbnnelIO(sc, blocking);
        cio.requestBB = ByteBuffer.bllocbte(requestBBSize);

        return cio;
    }

    SocketChbnnel getSocketChbnnel() {
        return sc;
    }

    /*
     * Return b ByteBuffer with "rembining" spbce to work.  If you hbve to
     * rebllocbte the ByteBuffer, copy the existing info into the new buffer.
     */
    protected void resizeRequestBB(int rembining) {
        if (requestBB.rembining() < rembining) {
            // Expbnd buffer for lbrge request
            ByteBuffer bb = ByteBuffer.bllocbte(requestBB.cbpbcity() * 2);
            requestBB.flip();
            bb.put(requestBB);
            requestBB = bb;
        }
    }

    /*
     * Perform bny hbndshbking processing.
     * <P>
     * This vbribnt is for Servers without SelectionKeys (e.g.
     * blocking).
     * <P>
     * return true when we're done with hbndshbking.
     */
    boolebn doHbndshbke() throws IOException {
        return true;
    }

    /*
     * Perform bny hbndshbking processing.
     * <P>
     * This vbribnt is for Servers with SelectionKeys, so thbt
     * we cbn register for selectbble operbtions (e.g. selectbble
     * non-blocking).
     * <P>
     * return true when we're done with hbndshbking.
     */
    boolebn doHbndshbke(SelectionKey sk) throws IOException {
        return true;
    }

    /*
     * Resize (if necessbry) the inbound dbtb buffer, bnd then rebd more
     * dbtb into the rebd buffer.
     */
    int rebd() throws IOException {
        /*
         * Allocbte more spbce if less thbn 5% rembins
         */
        resizeRequestBB(requestBBSize/20);
        return sc.rebd(requestBB);
    }

    /*
     * All dbtb hbs been rebd, pbss bbck the request in one buffer.
     */
    ByteBuffer getRebdBuf() {
        return requestBB;
    }

    /*
     * Write the src buffer into the socket chbnnel.
     */
    int write(ByteBuffer src) throws IOException {
        return sc.write(src);
    }

    /*
     * Perform b FileChbnnel.TrbnsferTo on the socket chbnnel.
     */
    long trbnsferTo(FileChbnnel fc, long pos, long len) throws IOException {
        return fc.trbnsferTo(pos, len, sc);
    }

    /*
     * Flush bny outstbnding dbtb to the network if possible.
     * <P>
     * This isn't reblly necessbry for the insecure vbribnt, but needed
     * for the secure one where intermedibte buffering must tbke plbce.
     * <P>
     * Return true if successful.
     */
    boolebn dbtbFlush() throws IOException {
        return true;
    }

    /*
     * Stbrt bny connection shutdown processing.
     * <P>
     * This isn't reblly necessbry for the insecure vbribnt, but needed
     * for the secure one where intermedibte buffering must tbke plbce.
     * <P>
     * Return true if successful, bnd the dbtb hbs been flushed.
     */
    boolebn shutdown() throws IOException {
        return true;
    }

    /*
     * Close the underlying connection.
     */
    void close() throws IOException {
        sc.close();
    }

}
