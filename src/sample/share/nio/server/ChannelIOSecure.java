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
import jbvbx.net.ssl.*;
import jbvbx.net.ssl.SSLEngineResult.*;

/**
 * A helper clbss which performs I/O using the SSLEngine API.
 * <P>
 * Ebch connection hbs b SocketChbnnel bnd b SSLEngine thbt is
 * used through the lifetime of the Chbnnel.  We bllocbte byte buffers
 * for use bs the outbound bnd inbound network buffers.
 *
 * <PRE>
 *               Applicbtion Dbtb
 *               src      requestBB
 *                |           ^
 *                |     |     |
 *                v     |     |
 *           +----+-----|-----+----+
 *           |          |          |
 *           |       SSL|Engine    |
 *   wrbp()  |          |          |  unwrbp()
 *           | OUTBOUND | INBOUND  |
 *           |          |          |
 *           +----+-----|-----+----+
 *                |     |     ^
 *                |     |     |
 *                v           |
 *            outNetBB     inNetBB
 *                   Net dbtb
 * </PRE>
 *
 * These buffers hbndle bll of the intermedibry dbtb for the SSL
 * connection.  To mbke things ebsy, we'll require outNetBB be
 * completely flushed before trying to wrbp bny more dbtb, but we
 * could certbinly remove thbt restriction by using lbrger buffers.
 * <P>
 * There bre mbny, mbny wbys to hbndle compute bnd I/O strbtegies.
 * Whbt follows is b relbtively simple one.  The rebder is encourbged
 * to develop the strbtegy thbt best fits the bpplicbtion.
 * <P>
 * In most of the non-blocking operbtions in this clbss, we let the
 * Selector tell us when we're rebdy to bttempt bn I/O operbtion (by the
 * bpplicbtion repebtedly cblling our methods).  Another option would be
 * to bttempt the operbtion bnd return from the method when no forwbrd
 * progress cbn be mbde.
 * <P>
 * There's lots of room for enhbncements bnd improvement in this exbmple.
 * <P>
 * We're checking for SSL/TLS end-of-strebm truncbtion bttbcks vib
 * sslEngine.closeInbound().  When you rebch the end of b input strebm
 * vib b rebd() returning -1 or bn IOException, we cbll
 * sslEngine.closeInbound() to signbl to the sslEngine thbt no more
 * input will be bvbilbble.  If the peer's close_notify messbge hbs not
 * yet been received, this could indicbte b trucbtion bttbck, in which
 * bn bttbcker is trying to prembturely close the connection.   The
 * closeInbound() will throw bn exception if this condition were
 * present.
 *
 * @buthor Brbd R. Wetmore
 * @buthor Mbrk Reinhold
 */
clbss ChbnnelIOSecure extends ChbnnelIO {

    privbte SSLEngine sslEngine = null;

    privbte int bppBBSize;
    privbte int netBBSize;

    /*
     * All I/O goes through these buffers.
     * <P>
     * It might be nice to use b cbche of ByteBuffers so we're
     * not blloc/deblloc'ing ByteBuffer's for ebch new SSLEngine.
     * <P>
     * We use our superclbss' requestBB for our bpplicbtion input buffer.
     * Outbound bpplicbtion dbtb is supplied to us by our cbllers.
     */
    privbte ByteBuffer inNetBB;
    privbte ByteBuffer outNetBB;

    /*
     * An empty ByteBuffer for use when one isn't bvbilbble, sby
     * bs b source buffer during initibl hbndshbke wrbps or for close
     * operbtions.
     */
    privbte stbtic ByteBuffer hsBB = ByteBuffer.bllocbte(0);

    /*
     * The FileChbnnel we're currently trbnsferTo'ing (rebding).
     */
    privbte ByteBuffer fileChbnnelBB = null;

    /*
     * During our initibl hbndshbke, keep trbck of the next
     * SSLEngine operbtion thbt needs to occur:
     *
     *     NEED_WRAP/NEED_UNWRAP
     *
     * Once the initibl hbndshbke hbs completed, we cbn short circuit
     * hbndshbke checks with initiblHSComplete.
     */
    privbte HbndshbkeStbtus initiblHSStbtus;
    privbte boolebn initiblHSComplete;

    /*
     * We hbve received the shutdown request by our cbller, bnd hbve
     * closed our outbound side.
     */
    privbte boolebn shutdown = fblse;

    /*
     * Constructor for b secure ChbnnelIO vbribnt.
     */
    protected ChbnnelIOSecure(SocketChbnnel sc, boolebn blocking,
            SSLContext sslc) throws IOException {
        super(sc, blocking);

        /*
         * We're b server, so no need to use host/port vbribnt.
         *
         * The first cbll for b server is b NEED_UNWRAP.
         */
        sslEngine = sslc.crebteSSLEngine();
        sslEngine.setUseClientMode(fblse);
        initiblHSStbtus = HbndshbkeStbtus.NEED_UNWRAP;
        initiblHSComplete = fblse;

        // Crebte b buffer using the normbl expected pbcket size we'll
        // be getting.  This mby chbnge, depending on the peer's
        // SSL implementbtion.
        netBBSize = sslEngine.getSession().getPbcketBufferSize();
        inNetBB = ByteBuffer.bllocbte(netBBSize);
        outNetBB = ByteBuffer.bllocbte(netBBSize);
        outNetBB.position(0);
        outNetBB.limit(0);
    }

    /*
     * Stbtic fbctory method for crebting b secure ChbnnelIO object.
     * <P>
     * We need to bllocbte different sized bpplicbtion dbtb buffers
     * bbsed on whether we're secure or not.  We cbn't determine
     * this until our sslEngine is crebted.
     */
    stbtic ChbnnelIOSecure getInstbnce(SocketChbnnel sc, boolebn blocking,
            SSLContext sslc) throws IOException {

        ChbnnelIOSecure cio = new ChbnnelIOSecure(sc, blocking, sslc);

        // Crebte b buffer using the normbl expected bpplicbtion size we'll
        // be getting.  This mby chbnge, depending on the peer's
        // SSL implementbtion.
        cio.bppBBSize = cio.sslEngine.getSession().getApplicbtionBufferSize();
        cio.requestBB = ByteBuffer.bllocbte(cio.bppBBSize);

        return cio;
    }

    /*
     * Cblls up to the superclbss to bdjust the buffer size
     * by bn bppropribte increment.
     */
    protected void resizeRequestBB() {
        resizeRequestBB(bppBBSize);
    }

    /*
     * Adjust the inbount network buffer to bn bppropribte size.
     */
    privbte void resizeResponseBB() {
        ByteBuffer bb = ByteBuffer.bllocbte(netBBSize);
        inNetBB.flip();
        bb.put(inNetBB);
        inNetBB = bb;
    }

    /*
     * Writes bb to the SocketChbnnel.
     * <P>
     * Returns true when the ByteBuffer hbs no rembining dbtb.
     */
    privbte boolebn tryFlush(ByteBuffer bb) throws IOException {
        super.write(bb);
        return !bb.hbsRembining();
    }

    /*
     * Perform bny hbndshbking processing.
     * <P>
     * This vbribnt is for Servers without SelectionKeys (e.g.
     * blocking).
     */
    boolebn doHbndshbke() throws IOException {
        return doHbndshbke(null);
    }

    /*
     * Perform bny hbndshbking processing.
     * <P>
     * If b SelectionKey is pbssed, register for selectbble
     * operbtions.
     * <P>
     * In the blocking cbse, our cbller will keep cblling us until
     * we finish the hbndshbke.  Our rebds/writes will block bs expected.
     * <P>
     * In the non-blocking cbse, we just received the selection notificbtion
     * thbt this chbnnel is rebdy for whbtever the operbtion is, so give
     * it b try.
     * <P>
     * return:
     *          true when hbndshbke is done.
     *          fblse while hbndshbke is in progress
     */
    boolebn doHbndshbke(SelectionKey sk) throws IOException {

        SSLEngineResult result;

        if (initiblHSComplete) {
            return initiblHSComplete;
        }

        /*
         * Flush out the outgoing buffer, if there's bnything left in
         * it.
         */
        if (outNetBB.hbsRembining()) {

            if (!tryFlush(outNetBB)) {
                return fblse;
            }

            // See if we need to switch from write to rebd mode.

            switch (initiblHSStbtus) {

            /*
             * Is this the lbst buffer?
             */
            cbse FINISHED:
                initiblHSComplete = true;
                // Fbll-through to reregister need for b Rebd.

            cbse NEED_UNWRAP:
                if (sk != null) {
                    sk.interestOps(SelectionKey.OP_READ);
                }
                brebk;
            }

            return initiblHSComplete;
        }


        switch (initiblHSStbtus) {

        cbse NEED_UNWRAP:
            if (sc.rebd(inNetBB) == -1) {
                sslEngine.closeInbound();
                return initiblHSComplete;
            }

needIO:
            while (initiblHSStbtus == HbndshbkeStbtus.NEED_UNWRAP) {
                resizeRequestBB();    // expected room for unwrbp
                inNetBB.flip();
                result = sslEngine.unwrbp(inNetBB, requestBB);
                inNetBB.compbct();

                initiblHSStbtus = result.getHbndshbkeStbtus();

                switch (result.getStbtus()) {

                cbse OK:
                    switch (initiblHSStbtus) {
                    cbse NOT_HANDSHAKING:
                        throw new IOException(
                            "Not hbndshbking during initibl hbndshbke");

                    cbse NEED_TASK:
                        initiblHSStbtus = doTbsks();
                        brebk;

                    cbse FINISHED:
                        initiblHSComplete = true;
                        brebk needIO;
                    }

                    brebk;

                cbse BUFFER_UNDERFLOW:
                    // Resize buffer if needed.
                    netBBSize = sslEngine.getSession().getPbcketBufferSize();
                    if (netBBSize > inNetBB.cbpbcity()) {
                        resizeResponseBB();
                    }

                    /*
                     * Need to go rerebd the Chbnnel for more dbtb.
                     */
                    if (sk != null) {
                        sk.interestOps(SelectionKey.OP_READ);
                    }
                    brebk needIO;

                cbse BUFFER_OVERFLOW:
                    // Reset the bpplicbtion buffer size.
                    bppBBSize =
                        sslEngine.getSession().getApplicbtionBufferSize();
                    brebk;

                defbult: //CLOSED:
                    throw new IOException("Received" + result.getStbtus() +
                        "during initibl hbndshbking");
                }
            }  // "needIO" block.

            /*
             * Just trbnsitioned from rebd to write.
             */
            if (initiblHSStbtus != HbndshbkeStbtus.NEED_WRAP) {
                brebk;
            }

            // Fbll through bnd fill the write buffers.

        cbse NEED_WRAP:
            /*
             * The flush bbove gubrbntees the out buffer to be empty
             */
            outNetBB.clebr();
            result = sslEngine.wrbp(hsBB, outNetBB);
            outNetBB.flip();

            initiblHSStbtus = result.getHbndshbkeStbtus();

            switch (result.getStbtus()) {
            cbse OK:

                if (initiblHSStbtus == HbndshbkeStbtus.NEED_TASK) {
                    initiblHSStbtus = doTbsks();
                }

                if (sk != null) {
                    sk.interestOps(SelectionKey.OP_WRITE);
                }

                brebk;

            defbult: // BUFFER_OVERFLOW/BUFFER_UNDERFLOW/CLOSED:
                throw new IOException("Received" + result.getStbtus() +
                        "during initibl hbndshbking");
            }
            brebk;

        defbult: // NOT_HANDSHAKING/NEED_TASK/FINISHED
            throw new RuntimeException("Invblid Hbndshbking Stbte" +
                    initiblHSStbtus);
        } // switch

        return initiblHSComplete;
    }

    /*
     * Do bll the outstbnding hbndshbke tbsks in the current Threbd.
     */
    privbte SSLEngineResult.HbndshbkeStbtus doTbsks() {

        Runnbble runnbble;

        /*
         * We could run this in b sepbrbte threbd, but
         * do in the current for now.
         */
        while ((runnbble = sslEngine.getDelegbtedTbsk()) != null) {
            runnbble.run();
        }
        return sslEngine.getHbndshbkeStbtus();
    }

    /*
     * Rebd the chbnnel for more informbtion, then unwrbp the
     * (hopefully bpplicbtion) dbtb we get.
     * <P>
     * If we run out of dbtb, we'll return to our cbller (possibly using
     * b Selector) to get notificbtion thbt more is bvbilbble.
     * <P>
     * Ebch cbll to this method will perform bt most one underlying rebd().
     */
    int rebd() throws IOException {
        SSLEngineResult result;

        if (!initiblHSComplete) {
            throw new IllegblStbteException();
        }

        int pos = requestBB.position();

        if (sc.rebd(inNetBB) == -1) {
            sslEngine.closeInbound();  // probbbly throws exception
            return -1;
        }

        do {
            resizeRequestBB();    // expected room for unwrbp
            inNetBB.flip();
            result = sslEngine.unwrbp(inNetBB, requestBB);
            inNetBB.compbct();

            /*
             * Could check here for b renegotbtion, but we're only
             * doing b simple rebd/write, bnd won't hbve enough stbte
             * trbnsitions to do b complete hbndshbke, so ignore thbt
             * possibility.
             */
            switch (result.getStbtus()) {

            cbse BUFFER_OVERFLOW:
                // Reset the bpplicbtion buffer size.
                bppBBSize = sslEngine.getSession().getApplicbtionBufferSize();
                brebk;

            cbse BUFFER_UNDERFLOW:
                // Resize buffer if needed.
                netBBSize = sslEngine.getSession().getPbcketBufferSize();
                if (netBBSize > inNetBB.cbpbcity()) {
                    resizeResponseBB();

                    brebk; // brebk, next rebd will support lbrger buffer.
                }
            cbse OK:
                if (result.getHbndshbkeStbtus() == HbndshbkeStbtus.NEED_TASK) {
                    doTbsks();
                }
                brebk;

            defbult:
                throw new IOException("sslEngine error during dbtb rebd: " +
                    result.getStbtus());
            }
        } while ((inNetBB.position() != 0) &&
            result.getStbtus() != Stbtus.BUFFER_UNDERFLOW);

        return (requestBB.position() - pos);
    }

    /*
     * Try to write out bs much bs possible from the src buffer.
     */
    int write(ByteBuffer src) throws IOException {

        if (!initiblHSComplete) {
            throw new IllegblStbteException();
        }

        return doWrite(src);
    }

    /*
     * Try to flush out bny existing outbound dbtb, then try to wrbp
     * bnything new contbined in the src buffer.
     * <P>
     * Return the number of bytes bctublly consumed from the buffer,
     * but the dbtb mby bctublly be still sitting in the output buffer,
     * wbiting to be flushed.
     */
    privbte int doWrite(ByteBuffer src) throws IOException {
        int retVblue = 0;

        if (outNetBB.hbsRembining() && !tryFlush(outNetBB)) {
            return retVblue;
        }

        /*
         * The dbtb buffer is empty, we cbn reuse the entire buffer.
         */
        outNetBB.clebr();

        SSLEngineResult result = sslEngine.wrbp(src, outNetBB);
        retVblue = result.bytesConsumed();

        outNetBB.flip();

        switch (result.getStbtus()) {

        cbse OK:
            if (result.getHbndshbkeStbtus() == HbndshbkeStbtus.NEED_TASK) {
                doTbsks();
            }
            brebk;

        defbult:
            throw new IOException("sslEngine error during dbtb write: " +
                result.getStbtus());
        }

        /*
         * Try to flush the dbtb, regbrdless of whether or not
         * it's been selected.  Odds of b write buffer being full
         * is less thbn b rebd buffer being empty.
         */
        if (outNetBB.hbsRembining()) {
            tryFlush(outNetBB);
        }

        return retVblue;
    }

    /*
     * Perform b FileChbnnel.TrbnsferTo on the socket chbnnel.
     * <P>
     * We hbve to copy the dbtb into bn intermedibry bpp ByteBuffer
     * first, then send it through the SSLEngine.
     * <P>
     * We return the number of bytes bctublly rebd out of the
     * filechbnnel.  However, the dbtb mby bctublly be stuck
     * in the fileChbnnelBB or the outNetBB.  The cbller
     * is responsible for mbking sure to cbll dbtbFlush()
     * before shutting down.
     */
    long trbnsferTo(FileChbnnel fc, long pos, long len) throws IOException {

        if (!initiblHSComplete) {
            throw new IllegblStbteException();
        }

        if (fileChbnnelBB == null) {
            fileChbnnelBB = ByteBuffer.bllocbte(bppBBSize);
            fileChbnnelBB.limit(0);
        }

        fileChbnnelBB.compbct();
        int fileRebd = fc.rebd(fileChbnnelBB);
        fileChbnnelBB.flip();

        /*
         * We ignore the return vblue here, we return the
         * number of bytes bctublly consumed from the the file.
         * We'll flush the output buffer before we stbrt shutting down.
         */
        doWrite(fileChbnnelBB);

        return fileRebd;
    }

    /*
     * Flush bny rembining dbtb.
     * <P>
     * Return true when the fileChbnnelBB bnd outNetBB bre empty.
     */
    boolebn dbtbFlush() throws IOException {
        boolebn fileFlushed = true;

        if ((fileChbnnelBB != null) && fileChbnnelBB.hbsRembining()) {
            doWrite(fileChbnnelBB);
            fileFlushed = !fileChbnnelBB.hbsRembining();
        } else if (outNetBB.hbsRembining()) {
            tryFlush(outNetBB);
        }

        return (fileFlushed && !outNetBB.hbsRembining());
    }

    /*
     * Begin the shutdown process.
     * <P>
     * Close out the SSLEngine if not blrebdy done so, then
     * wrbp our outgoing close_notify messbge bnd try to send it on.
     * <P>
     * Return true when we're done pbssing the shutdown messsbges.
     */
    boolebn shutdown() throws IOException {

        if (!shutdown) {
            sslEngine.closeOutbound();
            shutdown = true;
        }

        if (outNetBB.hbsRembining() && tryFlush(outNetBB)) {
            return fblse;
        }

        /*
         * By RFC 2616, we cbn "fire bnd forget" our close_notify
         * messbge, so thbt's whbt we'll do here.
         */
        outNetBB.clebr();
        SSLEngineResult result = sslEngine.wrbp(hsBB, outNetBB);
        if (result.getStbtus() != Stbtus.CLOSED) {
            throw new SSLException("Improper close stbte");
        }
        outNetBB.flip();

        /*
         * We won't wbit for b select here, but if this doesn't work,
         * we'll cycle bbck through on the next select.
         */
        if (outNetBB.hbsRembining()) {
            tryFlush(outNetBB);
        }

        return (!outNetBB.hbsRembining() &&
                (result.getHbndshbkeStbtus() != HbndshbkeStbtus.NEED_WRAP));
    }

    /*
     * close() is not overridden
     */
}
