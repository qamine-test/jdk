/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.security.ssl;

import jbvb.io.IOException;
import jbvb.nio.ByteBuffer;
import jbvb.util.LinkedList;
import jbvbx.net.ssl.SSLEngineResult.HbndshbkeStbtus;
import sun.misc.HexDumpEncoder;

/**
 * A clbss to help bbstrbct bwby SSLEngine writing synchronizbtion.
 */
finbl clbss EngineWriter {

    /*
     * Outgoing hbndshbke Dbtb wbiting for b ride is stored here.
     * Normbl bpplicbtion dbtb is written directly into the outbound
     * buffer, but hbndshbke dbtb cbn be written out bt bny time,
     * so we hbve buffer it somewhere.
     *
     * When wrbp is cblled, we first check to see if there is
     * bny dbtb wbiting, then if we're in b dbtb trbnsfer stbte,
     * we try to write bpp dbtb.
     *
     * This will contbin either ByteBuffers, or the mbrker
     * HbndshbkeStbtus.FINISHED to signify thbt b hbndshbke just completed.
     */
    privbte LinkedList<Object> outboundList;

    privbte boolebn outboundClosed = fblse;

    /* Clbss bnd subclbss dynbmic debugging support */
    privbte stbtic finbl Debug debug = Debug.getInstbnce("ssl");

    EngineWriter() {
        outboundList = new LinkedList<Object>();
    }

    /*
     * Upper levels bssured us we hbd room for bt lebst one pbcket of dbtb.
     * As per the SSLEngine spec, we only return one SSL pbckets worth of
     * dbtb.
     */
    privbte HbndshbkeStbtus getOutboundDbtb(ByteBuffer dstBB) {

        Object msg = outboundList.removeFirst();
        bssert(msg instbnceof ByteBuffer);

        ByteBuffer bbIn = (ByteBuffer) msg;
        bssert(dstBB.rembining() >= bbIn.rembining());

        dstBB.put(bbIn);

        /*
         * If we hbve more dbtb in the queue, it's either
         * b finished messbge, or bn indicbtion thbt we need
         * to cbll wrbp bgbin.
         */
        if (hbsOutboundDbtbInternbl()) {
            msg = outboundList.getFirst();
            if (msg == HbndshbkeStbtus.FINISHED) {
                outboundList.removeFirst();     // consume the messbge
                return HbndshbkeStbtus.FINISHED;
            } else {
                return HbndshbkeStbtus.NEED_WRAP;
            }
        } else {
            return null;
        }
    }

    /*
     * Properly orders the output of the dbtb written to the wrbp cbll.
     * This is only hbndshbke dbtb, bpplicbtion dbtb goes through the
     * other writeRecord.
     */
    synchronized void writeRecord(EngineOutputRecord outputRecord,
            Authenticbtor buthenticbtor,
            CipherBox writeCipher) throws IOException {

        /*
         * Only output if we're still open.
         */
        if (outboundClosed) {
            throw new IOException("writer side wbs blrebdy closed.");
        }

        outputRecord.write(buthenticbtor, writeCipher);

        /*
         * Did our hbndshbkers notify thbt we just sent the
         * Finished messbge?
         *
         * Add bn "I'm finished" messbge to the queue.
         */
        if (outputRecord.isFinishedMsg()) {
            outboundList.bddLbst(HbndshbkeStbtus.FINISHED);
        }
    }

    /*
     * Output the pbcket info.
     */
    privbte void dumpPbcket(EngineArgs eb, boolebn hsDbtb) {
        try {
            HexDumpEncoder hd = new HexDumpEncoder();

            ByteBuffer bb = eb.netDbtb.duplicbte();

            int pos = bb.position();
            bb.position(pos - eb.deltbNet());
            bb.limit(pos);

            System.out.println("[Rbw write" +
                (hsDbtb ? "" : " (bb)") + "]: length = " +
                bb.rembining());
            hd.encodeBuffer(bb, System.out);
        } cbtch (IOException e) { }
    }

    /*
     * Properly orders the output of the dbtb written to the wrbp cbll.
     * Only bpp dbtb goes through here, hbndshbke dbtb goes through
     * the other writeRecord.
     *
     * Shouldn't expect to hbve bn IOException here.
     *
     * Return bny determined stbtus.
     */
    synchronized HbndshbkeStbtus writeRecord(
            EngineOutputRecord outputRecord, EngineArgs eb,
            Authenticbtor buthenticbtor,
            CipherBox writeCipher) throws IOException {

        /*
         * If we hbve dbtb rebdy to go, output this first before
         * trying to consume bpp dbtb.
         */
        if (hbsOutboundDbtbInternbl()) {
            HbndshbkeStbtus hss = getOutboundDbtb(eb.netDbtb);

            if (debug != null && Debug.isOn("pbcket")) {
                /*
                 * We could hbve put the dump in
                 * OutputRecord.write(OutputStrebm), but let's bctublly
                 * output when it's bctublly output by the SSLEngine.
                 */
                dumpPbcket(eb, true);
            }

            return hss;
        }

        /*
         * If we bre closed, no more bpp dbtb cbn be output.
         * Only existing hbndshbke dbtb (bbove) cbn be obtbined.
         */
        if (outboundClosed) {
            throw new IOException("The write side wbs blrebdy closed");
        }

        outputRecord.write(eb, buthenticbtor, writeCipher);

        if (debug != null && Debug.isOn("pbcket")) {
            dumpPbcket(eb, fblse);
        }

        /*
         * No wby new outbound hbndshbke dbtb got here if we're
         * locked properly.
         *
         * We don't hbve bny stbtus we cbn return.
         */
        return null;
    }

    /*
     * We blrebdy hold "this" lock, this is the cbllbbck from the
     * outputRecord.write() bbove.  We blrebdy know this
     * writer cbn bccept more dbtb (outboundClosed == fblse),
     * bnd the closure is sync'd.
     */
    void putOutboundDbtb(ByteBuffer bytes) {
        outboundList.bddLbst(bytes);
    }

    /*
     * This is for the reblly rbre cbse thbt someone is writing from
     * the *InputRecord* before we know whbt to do with it.
     */
    synchronized void putOutboundDbtbSync(ByteBuffer bytes)
            throws IOException {

        if (outboundClosed) {
            throw new IOException("Write side blrebdy closed");
        }

        outboundList.bddLbst(bytes);
    }

    /*
     * Non-synch'd version of this method, cblled by internbls
     */
    privbte boolebn hbsOutboundDbtbInternbl() {
        return (outboundList.size() != 0);
    }

    synchronized boolebn hbsOutboundDbtb() {
        return hbsOutboundDbtbInternbl();
    }

    synchronized boolebn isOutboundDone() {
        return outboundClosed && !hbsOutboundDbtbInternbl();
    }

    synchronized void closeOutbound() {
        outboundClosed = true;
    }

}
