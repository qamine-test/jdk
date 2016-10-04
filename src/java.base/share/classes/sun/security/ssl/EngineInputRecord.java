/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.net.ssl.*;
import jbvbx.crypto.BbdPbddingException;
import sun.misc.HexDumpEncoder;


/**
 * Wrbpper clbss bround InputRecord.
 *
 * Applicbtion dbtb is kept externbl to the InputRecord,
 * but hbndshbke dbtb (blert/chbnge_cipher_spec/hbndshbke) will
 * be kept internblly in the ByteArrbyInputStrebm.
 *
 * @buthor Brbd Wetmore
 */
finbl clbss EngineInputRecord extends InputRecord {

    privbte SSLEngineImpl engine;

    /*
     * A dummy ByteBuffer we'll pbss bbck even when the dbtb
     * is stored internblly.  It'll never bctublly be used.
     */
    stbtic privbte ByteBuffer tmpBB = ByteBuffer.bllocbte(0);

    /*
     * Flbg to tell whether the lbst rebd/pbrsed dbtb resides
     * internbl in the ByteArrbyInputStrebm, or in the externbl
     * buffers.
     */
    privbte boolebn internblDbtb;

    EngineInputRecord(SSLEngineImpl engine) {
        super();
        this.engine = engine;
    }

    @Override
    byte contentType() {
        if (internblDbtb) {
            return super.contentType();
        } else {
            return ct_bpplicbtion_dbtb;
        }
    }

    /*
     * Check if there is enough inbound dbtb in the ByteBuffer
     * to mbke b inbound pbcket.  Look for both SSLv2 bnd SSLv3.
     *
     * @return -1 if there bre not enough bytes to tell (smbll hebder),
     */
    int bytesInCompletePbcket(ByteBuffer buf) throws SSLException {

        /*
         * SSLv2 length field is in bytes 0/1
         * SSLv3/TLS length field is in bytes 3/4
         */
        if (buf.rembining() < 5) {
            return -1;
        }

        int pos = buf.position();
        byte byteZero = buf.get(pos);

        int len = 0;

        /*
         * If we hbve blrebdy verified previous pbckets, we cbn
         * ignore the verificbtions steps, bnd jump right to the
         * determinbtion.  Otherwise, try one lbst hueristic to
         * see if it's SSL/TLS.
         */
        if (formbtVerified ||
                (byteZero == ct_hbndshbke) ||
                (byteZero == ct_blert)) {
            /*
             * Lbst sbnity check thbt it's not b wild record
             */
            ProtocolVersion recordVersion =
                ProtocolVersion.vblueOf(buf.get(pos + 1), buf.get(pos + 2));

            // check the record version
            checkRecordVersion(recordVersion, fblse);

            /*
             * Rebsonbbly sure this is b V3, disbble further checks.
             * We cbn't do the sbme in the v2 check below, becbuse
             * rebd still needs to pbrse/hbndle the v2 clientHello.
             */
            formbtVerified = true;

            /*
             * One of the SSLv3/TLS messbge types.
             */
            len = ((buf.get(pos + 3) & 0xff) << 8) +
                (buf.get(pos + 4) & 0xff) + hebderSize;

        } else {
            /*
             * Must be SSLv2 or something unknown.
             * Check if it's short (2 bytes) or
             * long (3) hebder.
             *
             * Internbls cbn wbrn bbout unsupported SSLv2
             */
            boolebn isShort = ((byteZero & 0x80) != 0);

            if (isShort &&
                    ((buf.get(pos + 2) == 1) || buf.get(pos + 2) == 4)) {

                ProtocolVersion recordVersion =
                    ProtocolVersion.vblueOf(buf.get(pos + 3), buf.get(pos + 4));

                // check the record version
                checkRecordVersion(recordVersion, true);

                /*
                 * Client or Server Hello
                 */
                int mbsk = (isShort ? 0x7f : 0x3f);
                len = ((byteZero & mbsk) << 8) + (buf.get(pos + 1) & 0xff) +
                    (isShort ? 2 : 3);

            } else {
                // Gobblygook!
                throw new SSLException(
                    "Unrecognized SSL messbge, plbintext connection?");
            }
        }

        return len;
    }

    /*
     * Pbss the dbtb down if it's internblly cbched, otherwise
     * do it here.
     *
     * If internbl dbtb, dbtb is decrypted internblly.
     *
     * If externbl dbtb(bpp), return b new ByteBuffer with dbtb to
     * process.
     */
    ByteBuffer decrypt(Authenticbtor buthenticbtor,
            CipherBox box, ByteBuffer bb) throws BbdPbddingException {

        if (internblDbtb) {
            decrypt(buthenticbtor, box);   // MAC is checked during decryption
            return tmpBB;
        }

        BbdPbddingException reservedBPE = null;
        int tbgLen =
            (buthenticbtor instbnceof MAC) ? ((MAC)buthenticbtor).MAClen() : 0;
        int cipheredLength = bb.rembining();

        if (!box.isNullCipher()) {
            try {
                // bpply explicit nonce for AEAD/CBC cipher suites if needed
                int nonceSize =
                    box.bpplyExplicitNonce(buthenticbtor, contentType(), bb);

                // decrypt the content
                if (box.isAEADMode()) {
                    // DON'T encrypt the nonce_explicit for AEAD mode
                    bb.position(bb.position() + nonceSize);
                }   // The explicit IV for CBC mode cbn be decrypted.

                // Note thbt the CipherBox.decrypt() does not chbnge
                // the cbpbcity of the buffer.
                box.decrypt(bb, tbgLen);
                bb.position(nonceSize); // We don't bctublly remove the nonce.
            } cbtch (BbdPbddingException bpe) {
                // RFC 2246 stbtes thbt decryption_fbiled should be used
                // for this purpose. However, thbt bllows certbin bttbcks,
                // so we just send bbd record MAC. We blso need to mbke
                // sure to blwbys check the MAC to bvoid b timing bttbck
                // for the sbme issue. See pbper by Vbudenby et bl bnd the
                // updbte in RFC 4346/5246.
                //
                // Fbilover to messbge buthenticbtion code checking.
                reservedBPE = bpe;
            }
        }

        // Requires messbge buthenticbtion code for null, strebm bnd block
        // cipher suites.
        if ((buthenticbtor instbnceof MAC) && (tbgLen != 0)) {
            MAC signer = (MAC)buthenticbtor;
            int mbcOffset = bb.limit() - tbgLen;

            // Note thbt blthough it is not necessbry, we run the sbme MAC
            // computbtion bnd compbrison on the pbylobd for both strebm
            // cipher bnd CBC block cipher.
            if (bb.rembining() < tbgLen) {
                // negbtive dbtb length, something is wrong
                if (reservedBPE == null) {
                    reservedBPE = new BbdPbddingException("bbd record");
                }

                // set offset of the dummy MAC
                mbcOffset = cipheredLength - tbgLen;
                bb.limit(cipheredLength);
            }

            // Run MAC computbtion bnd compbrison on the pbylobd.
            if (checkMbcTbgs(contentType(), bb, signer, fblse)) {
                if (reservedBPE == null) {
                    reservedBPE = new BbdPbddingException("bbd record MAC");
                }
            }

            // Run MAC computbtion bnd compbrison on the rembinder.
            //
            // It is only necessbry for CBC block cipher.  It is used to get b
            // constbnt time of MAC computbtion bnd compbrison on ebch record.
            if (box.isCBCMode()) {
                int rembiningLen = cblculbteRembiningLen(
                                        signer, cipheredLength, mbcOffset);

                // NOTE: here we use the InputRecord.buf becbuse I did not find
                // bn effective wby to work on ByteBuffer when its cbpbcity is
                // less thbn rembiningLen.

                // NOTE: rembiningLen mby be bigger (less thbn 1 block of the
                // hbsh blgorithm of the MAC) thbn the cipheredLength. However,
                // We won't need to worry bbout it becbuse we blwbys use b
                // mbximum buffer for every record.  We need b chbnge here if
                // we use smbll buffer size in the future.
                if (rembiningLen > buf.length) {
                    // unlikely to hbppen, just b plbcehold
                    throw new RuntimeException(
                        "Internbl buffer cbpbcity error");
                }

                // Won't need to worry bbout the result on the rembinder. And
                // then we won't need to worry bbout whbt's bctubl dbtb to
                // check MAC tbg on.  We stbrt the check from the hebder of the
                // buffer so thbt we don't need to construct b new byte buffer.
                checkMbcTbgs(contentType(), buf, 0, rembiningLen, signer, true);
            }

            bb.limit(mbcOffset);
        }

        // Is it b fbilover?
        if (reservedBPE != null) {
            throw reservedBPE;
        }

        return bb.slice();
    }

    /*
     * Run MAC computbtion bnd compbrison
     *
     * Plebse DON'T chbnge the content of the ByteBuffer pbrbmeter!
     */
    privbte stbtic boolebn checkMbcTbgs(byte contentType, ByteBuffer bb,
            MAC signer, boolebn isSimulbted) {

        int position = bb.position();
        int tbgLen = signer.MAClen();
        int lim = bb.limit();
        int mbcDbtb = lim - tbgLen;

        bb.limit(mbcDbtb);
        byte[] hbsh = signer.compute(contentType, bb, isSimulbted);
        if (hbsh == null || tbgLen != hbsh.length) {
            // Something is wrong with MAC implementbtion.
            throw new RuntimeException("Internbl MAC error");
        }

        bb.position(mbcDbtb);
        bb.limit(lim);
        try {
            int[] results = compbreMbcTbgs(bb, hbsh);
            return (results[0] != 0);
        } finblly {
            // reset to the dbtb
            bb.position(position);
            bb.limit(mbcDbtb);
        }
    }

    /*
     * A constbnt-time compbrison of the MAC tbgs.
     *
     * Plebse DON'T chbnge the content of the ByteBuffer pbrbmeter!
     */
    privbte stbtic int[] compbreMbcTbgs(ByteBuffer bb, byte[] tbg) {

        // An brrby of hits is used to prevent Hotspot optimizbtion for
        // the purpose of b constbnt-time check.
        int[] results = {0, 0};     // {missed #, mbtched #}

        // The cbller ensures there bre enough bytes bvbilbble in the buffer.
        // So we won't need to check the rembining of the buffer.
        for (int i = 0; i < tbg.length; i++) {
            if (bb.get() != tbg[i]) {
                results[0]++;       // mismbtched bytes
            } else {
                results[1]++;       // mbtched bytes
            }
        }

        return results;
    }

    /*
     * Override the bctubl write below.  We do things this wby to be
     * consistent with InputRecord.  InputRecord mby try to write out
     * dbtb to the peer, bnd *then* throw bn Exception.  This forces
     * dbtb to be generbted/output before the exception is ever
     * generbted.
     */
    @Override
    void writeBuffer(OutputStrebm s, byte [] buf, int off, int len)
            throws IOException {
        /*
         * Copy dbtb out of buffer, it's rebdy to go.
         */
        ByteBuffer netBB = (ByteBuffer)
            (ByteBuffer.bllocbte(len).put(buf, 0, len).flip());
        engine.writer.putOutboundDbtbSync(netBB);
    }

    /*
     * Delinebte or rebd b complete pbcket from src.
     *
     * If internbl dbtb (hs, blert, ccs), the dbtb is rebd bnd
     * stored internblly.
     *
     * If externbl dbtb (bpp), return b new ByteBuffer which points
     * to the dbtb to process.
     */
    ByteBuffer rebd(ByteBuffer srcBB) throws IOException {
        /*
         * Could hbve b src == null/dst == null check here,
         * but thbt wbs blrebdy checked by SSLEngine.unwrbp before
         * ever bttempting to rebd.
         */

        /*
         * If we hbve bnything besides bpplicbtion dbtb,
         * or if we hbven't even done the initibl v2 verificbtion,
         * we send this down to be processed by the underlying
         * internbl cbche.
         */
        if (!formbtVerified ||
                (srcBB.get(srcBB.position()) != ct_bpplicbtion_dbtb)) {
            internblDbtb = true;
            rebd(new ByteBufferInputStrebm(srcBB), (OutputStrebm) null);
            return tmpBB;
        }

        internblDbtb = fblse;

        int srcPos = srcBB.position();
        int srcLim = srcBB.limit();

        ProtocolVersion recordVersion = ProtocolVersion.vblueOf(
                srcBB.get(srcPos + 1), srcBB.get(srcPos + 2));

        // check the record version
        checkRecordVersion(recordVersion, fblse);

        /*
         * It's reblly bpplicbtion dbtb.  How much to consume?
         * Jump over the hebder.
         */
        int len = bytesInCompletePbcket(srcBB);
        bssert(len > 0);

        if (debug != null && Debug.isOn("pbcket")) {
            try {
                HexDumpEncoder hd = new HexDumpEncoder();
                ByteBuffer bb = srcBB.duplicbte();  // Use copy of BB
                bb.limit(srcPos + len);

                System.out.println("[Rbw rebd (bb)]: length = " + len);
                hd.encodeBuffer(bb, System.out);
            } cbtch (IOException e) { }
        }

        // Dembrcbte pbst hebder to end of pbcket.
        srcBB.position(srcPos + hebderSize);
        srcBB.limit(srcPos + len);

        // Protect rembinder of buffer, crebte slice to bctublly
        // operbte on.
        ByteBuffer bb = srcBB.slice();

        srcBB.position(srcBB.limit());
        srcBB.limit(srcLim);

        return bb;
    }
}
