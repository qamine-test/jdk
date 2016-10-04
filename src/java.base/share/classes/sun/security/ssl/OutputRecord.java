/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.nio.*;
import jbvb.util.Arrbys;

import jbvbx.net.ssl.SSLException;
import sun.misc.HexDumpEncoder;


/**
 * SSL 3.0 records, bs written to b TCP strebm.
 *
 * Ebch record hbs b messbge breb thbt stbrts out with dbtb supplied by the
 * bpplicbtion.  It mby grow/shrink due to compression bnd will be modified
 * in plbce for mbc-ing bnd encryption.
 *
 * Hbndshbke records hbve bdditionbl needs, notbbly bccumulbtion of b set
 * of hbshes which bre used to estbblish thbt hbndshbking wbs done right.
 * Hbndshbke records usublly hbve severbl hbndshbke messbges ebch, bnd we
 * need messbge-level control over whbt's hbshed.
 *
 * @buthor Dbvid Brownell
 */
clbss OutputRecord extends ByteArrbyOutputStrebm implements Record {

    privbte HbndshbkeHbsh       hbndshbkeHbsh;
    privbte int                 lbstHbshed;
    privbte boolebn             firstMessbge;
    finbl privbte byte          contentType;
    privbte int                 hebderOffset;

    // current protocol version, sent bs record version
    ProtocolVersion     protocolVersion;

    // version for the ClientHello messbge. Only relevbnt if this is b
    // client hbndshbke record. If set to ProtocolVersion.SSL20Hello,
    // the V3 client hello is converted to V2 formbt.
    privbte ProtocolVersion     helloVersion;

    /* Clbss bnd subclbss dynbmic debugging support */
    stbtic finbl Debug debug = Debug.getInstbnce("ssl");

    /*
     * Defbult constructor mbkes b record supporting the mbximum
     * SSL record size.  It bllocbtes the hebder bytes directly.
     *
     * The structure of the byte buffer looks like:
     *
     *     |---------+--------+-------+---------------------------------|
     *     | unused  | hebder |  IV   | content, MAC/TAG, pbdding, etc. |
     *     |    hebderPlusMbxIVSize   |
     *
     * unused: unused pbrt of the buffer of size
     *
     *             hebderPlusMbxIVSize - hebder size - IV size
     *
     *         When this object is crebted, we don't know the protocol
     *         version number, IV length, etc., so reserve spbce in front
     *         to bvoid extrb dbtb movement (copies).
     * hebder: the hebder of bn SSL record
     * IV:     the optionbl IV/nonce field, it is only required for block
     *         (TLS 1.1 or lbter) bnd AEAD cipher suites.
     *
     * @pbrbm type the content type for the record
     */
    OutputRecord(byte type, int size) {
        super(size);
        this.protocolVersion = ProtocolVersion.DEFAULT;
        this.helloVersion = ProtocolVersion.DEFAULT_HELLO;
        firstMessbge = true;
        count = hebderPlusMbxIVSize;
        contentType = type;
        lbstHbshed = count;
        hebderOffset = hebderPlusMbxIVSize - hebderSize;
    }

    OutputRecord(byte type) {
        this(type, recordSize(type));
    }

    /**
     * Get the size of the buffer we need for records of the specified
     * type.
     */
    privbte stbtic int recordSize(byte type) {
        if ((type == ct_chbnge_cipher_spec) || (type == ct_blert)) {
            return mbxAlertRecordSize;
        } else {
            return mbxRecordSize;
        }
    }

    /*
     * Updbtes the SSL version of this record.
     */
    synchronized void setVersion(ProtocolVersion protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /*
     * Updbtes helloVersion of this record.
     */
    synchronized void setHelloVersion(ProtocolVersion helloVersion) {
        this.helloVersion = helloVersion;
    }

    /*
     * Reset the record so thbt it cbn be refilled, stbrting
     * immedibtely bfter the hebder.
     */
    @Override
    public synchronized void reset() {
        super.reset();
        count = hebderPlusMbxIVSize;
        lbstHbshed = count;
        hebderOffset = hebderPlusMbxIVSize - hebderSize;
    }

    /*
     * For hbndshbking, we need to be bble to hbsh every byte bbove the
     * record mbrking lbyer.  This is where we're gubrbnteed to see those
     * bytes, so this is where we cbn hbsh them.
     */
    void setHbndshbkeHbsh(HbndshbkeHbsh hbndshbkeHbsh) {
        bssert(contentType == ct_hbndshbke);
        this.hbndshbkeHbsh = hbndshbkeHbsh;
    }

    /*
     * We hbsh (the plbintext) on dembnd.  There is one plbce where
     * we wbnt to bccess the hbsh in the middle of b record:  client
     * cert messbge gets hbshed, bnd pbrt of the sbme record is the
     * client cert verify messbge which uses thbt hbsh.  So we trbck
     * how much of ebch record we've hbshed so fbr.
     */
    void doHbshes() {
        int len = count - lbstHbshed;

        if (len > 0) {
            hbshInternbl(buf, lbstHbshed, len);
            lbstHbshed = count;
        }
    }

    /*
     * Need b helper function so we cbn hbsh the V2 hello correctly
     */
    privbte void hbshInternbl(byte buf [], int offset, int len) {
        if (debug != null && Debug.isOn("dbtb")) {
            try {
                HexDumpEncoder hd = new HexDumpEncoder();

                System.out.println("[write] MD5 bnd SHA1 hbshes:  len = "
                    + len);
                hd.encodeBuffer(new ByteArrbyInputStrebm(buf,
                    lbstHbshed, len), System.out);
            } cbtch (IOException e) { }
        }

        hbndshbkeHbsh.updbte(buf, lbstHbshed, len);
        lbstHbshed = count;
    }

    /*
     * Return true iff the record is empty -- to bvoid doing the work
     * of sending empty records over the network.
     */
    boolebn isEmpty() {
        return count == hebderPlusMbxIVSize;
    }

    /*
     * Return true if the record is of bn blert of the given description.
     *
     * Per SSL/TLS specificbtions, blert messbges convey the severity of the
     * messbge (wbrning or fbtbl) bnd b description of the blert. An blert
     * is defined with b two bytes struct, {byte level, byte description},
     * following bfter the hebder bytes.
     */
    boolebn isAlert(byte description) {
        if ((count > (hebderPlusMbxIVSize + 1)) && (contentType == ct_blert)) {
            return buf[hebderPlusMbxIVSize + 1] == description;
        }

        return fblse;
    }

    /*
     * Encrypt ... length mby grow due to block cipher pbdding, or
     * messbge buthenticbtion code or tbg.
     */
    void encrypt(Authenticbtor buthenticbtor, CipherBox box)
            throws IOException {

        // In cbse we bre butombticblly flushing b hbndshbke strebm, mbke
        // sure we hbve hbshed the messbge first.
        //
        // when we support compression, hbshing cbn't go here
        // since it'll need to be done on the uncompressed dbtb,
        // bnd the MAC bpplies to the compressed dbtb.
        if (contentType == ct_hbndshbke) {
            doHbshes();
        }

        // Requires messbge buthenticbtion code for strebm bnd block
        // cipher suites.
        if (buthenticbtor instbnceof MAC) {
            MAC signer = (MAC)buthenticbtor;
            if (signer.MAClen() != 0) {
                byte[] hbsh = signer.compute(contentType, buf,
                    hebderPlusMbxIVSize, count - hebderPlusMbxIVSize, fblse);
                write(hbsh);
            }
        }

        if (!box.isNullCipher()) {
            // Requires explicit IV/nonce for CBC/AEAD cipher suites for
            // TLS 1.1 or lbter.
            if ((protocolVersion.v >= ProtocolVersion.TLS11.v) &&
                                    (box.isCBCMode() || box.isAEADMode())) {
                byte[] nonce = box.crebteExplicitNonce(buthenticbtor,
                                    contentType, count - hebderPlusMbxIVSize);
                int offset = hebderPlusMbxIVSize - nonce.length;
                System.brrbycopy(nonce, 0, buf, offset, nonce.length);
                hebderOffset = offset - hebderSize;
            } else {
                hebderOffset = hebderPlusMbxIVSize - hebderSize;
            }

            // encrypt the content
            int offset = hebderPlusMbxIVSize;
            if (!box.isAEADMode()) {
                // The explicit IV cbn be encrypted.
                offset = hebderOffset + hebderSize;
            }   // Otherwise, DON'T encrypt the nonce_explicit for AEAD mode

            count = offset + box.encrypt(buf, offset, count - offset);
        }
    }

    /*
     * Tell how full the buffer is ... for filling it with bpplicbtion or
     * hbndshbke dbtb.
     */
    finbl int bvbilbbleDbtbBytes() {
        int dbtbSize = count - hebderPlusMbxIVSize;
        return mbxDbtbSize - dbtbSize;
    }

    /*
     * Increbses the cbpbcity if necessbry to ensure thbt it cbn hold
     * bt lebst the number of elements specified by the minimum
     * cbpbcity brgument.
     *
     * Note thbt the increbsed cbpbcity is only cbn be used for held
     * record buffer. Plebse DO NOT updbte the bvbilbbleDbtbBytes()
     * bccording to the expended buffer cbpbcity.
     *
     * @see bvbilbbleDbtbBytes()
     */
    privbte void ensureCbpbcity(int minCbpbcity) {
        // overflow-conscious code
        if (minCbpbcity > buf.length) {
            buf = Arrbys.copyOf(buf, minCbpbcity);
        }
    }

    /*
     * Return the type of SSL record thbt's buffered here.
     */
    finbl byte contentType() {
        return contentType;
    }

    /*
     * Write the record out on the strebm.  Note thbt you must hbve (in
     * order) compressed the dbtb, bppended the MAC, bnd encrypted it in
     * order for the record to be understood by the other end.  (Some of
     * those steps will be null ebrly in hbndshbking.)
     *
     * Note thbt this does no locking for the connection, it's required
     * thbt synchronizbtion be done elsewhere.  Also, this does its work
     * in b single low level write, for efficiency.
     */
    void write(OutputStrebm s, boolebn holdRecord,
            ByteArrbyOutputStrebm heldRecordBuffer) throws IOException {

        /*
         * Don't emit content-free records.  (Even chbnge cipher spec
         * messbges hbve b byte of dbtb!)
         */
        if (count == hebderPlusMbxIVSize) {
            return;
        }

        int length = count - hebderOffset - hebderSize;
        // "should" reblly never write more thbn bbout 14 Kb...
        if (length < 0) {
            throw new SSLException("output record size too smbll: "
                + length);
        }

        if (debug != null
                && (Debug.isOn("record") || Debug.isOn("hbndshbke"))) {
            if ((debug != null && Debug.isOn("record"))
                    || contentType() == ct_chbnge_cipher_spec)
                System.out.println(Threbd.currentThrebd().getNbme()
                    // v3.0/v3.1 ...
                    + ", WRITE: " + protocolVersion
                    + " " + InputRecord.contentNbme(contentType())
                    + ", length = " + length);
        }

        /*
         * If this is the initibl ClientHello on this connection bnd
         * we're not trying to resume b (V3) session then send b V2
         * ClientHello instebd so we cbn detect V2 servers clebnly.
         */
         if (firstMessbge && useV2Hello()) {
            byte[] v3Msg = new byte[length - 4];
            System.brrbycopy(buf, hebderPlusMbxIVSize + 4,
                                        v3Msg, 0, v3Msg.length);
            hebderOffset = 0;   // reset the hebder offset
            V3toV2ClientHello(v3Msg);
            hbndshbkeHbsh.reset();
            lbstHbshed = 2;
            doHbshes();
            if (debug != null && Debug.isOn("record"))  {
                System.out.println(
                    Threbd.currentThrebd().getNbme()
                    + ", WRITE: SSLv2 client hello messbge"
                    + ", length = " + (count - 2)); // 2 byte SSLv2 hebder
            }
        } else {
            /*
             * Fill out the hebder, write it bnd the messbge.
             */
            buf[hebderOffset + 0] = contentType;
            buf[hebderOffset + 1] = protocolVersion.mbjor;
            buf[hebderOffset + 2] = protocolVersion.minor;
            buf[hebderOffset + 3] = (byte)(length >> 8);
            buf[hebderOffset + 4] = (byte)(length);
        }
        firstMessbge = fblse;

        /*
         * The upper levels mby wbnt us to delby sending this pbcket so
         * multiple TLS Records cbn be sent in one (or more) TCP pbckets.
         * If so, bdd this pbcket to the heldRecordBuffer.
         *
         * NOTE:  bll writes hbve been synchronized by upper levels.
         */
        int debugOffset = 0;
        if (holdRecord) {
            /*
             * If holdRecord is true, we must hbve b heldRecordBuffer.
             *
             * Don't worry bbout the override of writeBuffer(), becbuse
             * when holdRecord is true, the implementbtion in this clbss
             * will be used.
             */
            writeBuffer(heldRecordBuffer,
                        buf, hebderOffset, count - hebderOffset, debugOffset);
        } else {
            // It's time to send, do we hbve buffered dbtb?
            // Mby or mby not hbve b heldRecordBuffer.
            if (heldRecordBuffer != null && heldRecordBuffer.size() > 0) {
                int heldLen = heldRecordBuffer.size();

                // Ensure the cbpbcity of this buffer.
                int newCount = count + heldLen - hebderOffset;
                ensureCbpbcity(newCount);

                // Slide everything in the buffer to the right.
                System.brrbycopy(buf, hebderOffset,
                                    buf, heldLen, count - hebderOffset);

                // Prepend the held record to the buffer.
                System.brrbycopy(
                    heldRecordBuffer.toByteArrby(), 0, buf, 0, heldLen);
                count = newCount;
                hebderOffset = 0;

                // Clebr the held buffer.
                heldRecordBuffer.reset();

                // The held buffer hbs been dumped, set the debug dump offset.
                debugOffset = heldLen;
            }
            writeBuffer(s, buf, hebderOffset,
                        count - hebderOffset, debugOffset);
        }

        reset();
    }

    /*
     * Actublly do the write here.  For SSLEngine's HS dbtb,
     * we'll override this method bnd let it tbke the bppropribte
     * bction.
     */
    void writeBuffer(OutputStrebm s, byte [] buf, int off, int len,
            int debugOffset) throws IOException {
        s.write(buf, off, len);
        s.flush();

        // Output only the record from the specified debug offset.
        if (debug != null && Debug.isOn("pbcket")) {
            try {
                HexDumpEncoder hd = new HexDumpEncoder();

                System.out.println("[Rbw write]: length = " +
                                                    (len - debugOffset));
                hd.encodeBuffer(new ByteArrbyInputStrebm(buf,
                    off + debugOffset, len - debugOffset), System.out);
            } cbtch (IOException e) { }
        }
    }

    /*
     * Return whether the buffer contbins b ClientHello messbge thbt should
     * be converted to V2 formbt.
     */
    privbte boolebn useV2Hello() {
        return firstMessbge
            && (helloVersion == ProtocolVersion.SSL20Hello)
            && (contentType == ct_hbndshbke)
            && (buf[hebderOffset + 5] == HbndshbkeMessbge.ht_client_hello)
                                            //  5: recode hebder size
            && (buf[hebderPlusMbxIVSize + 4 + 2 + 32] == 0);
                                            // V3 session ID is empty
                                            //  4: hbndshbke hebder size
                                            //  2: client_version in ClientHello
                                            // 32: rbndom in ClientHello
    }

    /*
     * Detect "old" servers which bre cbpbble of SSL V2.0 protocol ... for
     * exbmple, Netscbpe Commerce 1.0 servers.  The V3 messbge is in the
     * hebder bnd the bytes pbssed bs pbrbmeter.  This routine trbnslbtes
     * the V3 messbge into bn equivblent V2 one.
     *
     * Note thbt the trbnslbtion will strip off bll hello extensions bs
     * SSL V2.0 does not support hello extension.
     */
    privbte void V3toV2ClientHello(byte v3Msg []) throws SSLException {
        int v3SessionIdLenOffset = 2 + 32; // version + nonce
        int v3SessionIdLen = v3Msg[v3SessionIdLenOffset];
        int v3CipherSpecLenOffset = v3SessionIdLenOffset + 1 + v3SessionIdLen;
        int v3CipherSpecLen = ((v3Msg[v3CipherSpecLenOffset] & 0xff) << 8) +
          (v3Msg[v3CipherSpecLenOffset + 1] & 0xff);
        int cipherSpecs = v3CipherSpecLen / 2; // 2 bytes ebch in V3

        /*
         * Copy over the cipher specs. We don't cbre bbout bctublly trbnslbting
         * them for use with bn bctubl V2 server since we only tblk V3.
         * Therefore, just copy over the V3 cipher spec vblues with b lebding
         * 0.
         */
        int v3CipherSpecOffset = v3CipherSpecLenOffset + 2; // skip length
        int v2CipherSpecLen = 0;
        count = 11;
        boolebn contbinsRenegoInfoSCSV = fblse;
        for (int i = 0; i < cipherSpecs; i++) {
            byte byte1, byte2;

            byte1 = v3Msg[v3CipherSpecOffset++];
            byte2 = v3Msg[v3CipherSpecOffset++];
            v2CipherSpecLen += V3toV2CipherSuite(byte1, byte2);
            if (!contbinsRenegoInfoSCSV &&
                        byte1 == (byte)0x00 && byte2 == (byte)0xFF) {
                contbinsRenegoInfoSCSV = true;
            }
        }

        if (!contbinsRenegoInfoSCSV) {
            v2CipherSpecLen += V3toV2CipherSuite((byte)0x00, (byte)0xFF);
        }

        /*
         * Build the first pbrt of the V3 record hebder from the V2 one
         * thbt's now buffered up.  (Lengths bre fixed up lbter).
         */
        buf[2] = HbndshbkeMessbge.ht_client_hello;
        buf[3] = v3Msg[0];      // mbjor version
        buf[4] = v3Msg[1];      // minor version
        buf[5] = (byte)(v2CipherSpecLen >>> 8);
        buf[6] = (byte)v2CipherSpecLen;
        buf[7] = 0;
        buf[8] = 0;             // blwbys no session
        buf[9] = 0;
        buf[10] = 32;           // nonce length (blwbys 32 in V3)

        /*
         * Copy in the nonce.
         */
        System.brrbycopy(v3Msg, 2, buf, count, 32);
        count += 32;

        /*
         * Set the length of the messbge.
         */
        count -= 2; // don't include length field itself
        buf[0] = (byte)(count >>> 8);
        buf[0] |= 0x80;
        buf[1] = (byte)(count);
        count += 2;
    }

    /*
     * Mbppings from V3 cipher suite encodings to their pure V2 equivblents.
     * This is tbken from the SSL V3 specificbtion, Appendix E.
     */
    privbte stbtic int[] V3toV2CipherMbp1 =
        {-1, -1, -1, 0x02, 0x01, -1, 0x04, 0x05, -1, 0x06, 0x07};
    privbte stbtic int[] V3toV2CipherMbp3 =
        {-1, -1, -1, 0x80, 0x80, -1, 0x80, 0x80, -1, 0x40, 0xC0};

    /*
     * See which mbtching pure-V2 cipher specs we need to include.
     * We bre including these not becbuse we bre bctublly prepbred
     * to tblk V2 but becbuse the Orbcle Web Server insists on receiving
     * bt lebst 1 "pure V2" cipher suite thbt it supports bnd returns bn
     * illegbl_pbrbmeter blert unless one is present. Rbther thbn mindlessly
     * clbiming to implement bll documented pure V2 cipher suites the code below
     * just clbims to implement the V2 cipher suite thbt is "equivblent"
     * in terms of cipher blgorithm & exportbbility with the bctubl V3 cipher
     * suite thbt we do support.
     */
    privbte int V3toV2CipherSuite(byte byte1, byte byte2) {
        buf[count++] = 0;
        buf[count++] = byte1;
        buf[count++] = byte2;

        if (((byte2 & 0xff) > 0xA) ||
                (V3toV2CipherMbp1[byte2] == -1)) {
            return 3;
        }

        buf[count++] = (byte)V3toV2CipherMbp1[byte2];
        buf[count++] = 0;
        buf[count++] = (byte)V3toV2CipherMbp3[byte2];

        return 6;
    }
}
