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

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.util.Hbshtbble;
import jbvb.util.Arrbys;

import jbvb.security.*;
import jbvbx.crypto.*;
import jbvbx.crypto.spec.IvPbrbmeterSpec;
import jbvbx.crypto.spec.GCMPbrbmeterSpec;

import jbvb.nio.*;

import sun.security.ssl.CipherSuite.*;
import stbtic sun.security.ssl.CipherSuite.*;
import stbtic sun.security.ssl.CipherSuite.CipherType.*;

import sun.misc.HexDumpEncoder;


/**
 * This clbss hbndles bulk dbtb enciphering/deciphering for ebch SSLv3
 * messbge.  This provides dbtb confidentiblity.  Strebm ciphers (such
 * bs RC4) don't need to do pbdding; block ciphers (e.g. DES) need it.
 *
 * Individubl instbnces bre obtbined by cblling the stbtic method
 * newCipherBox(), which should only be invoked by BulkCipher.newCipher().
 *
 * In RFC 2246, with bock ciphers in CBC mode, the Initiblizbtion
 * Vector (IV) for the first record is generbted with the other keys
 * bnd secrets when the security pbrbmeters bre set.  The IV for
 * subsequent records is the lbst ciphertext block from the previous
 * record.
 *
 * In RFC 4346, the implicit Initiblizbtion Vector (IV) is replbced
 * with bn explicit IV to protect bgbinst CBC bttbcks.  RFC 4346
 * recommends two blgorithms used to generbted the per-record IV.
 * The implementbtion uses the blgorithm (2)(b), bs described bt
 * section 6.2.3.2 of RFC 4346.
 *
 * The usbge of IV in CBC block cipher cbn be illustrbted in
 * the following dibgrbms.
 *
 *   (rbndom)
 *        R         P1                    IV        C1
 *        |          |                     |         |
 *  SIV---+    |-----+    |-...            |-----    |------
 *        |    |     |    |                |    |    |     |
 *     +----+  |  +----+  |             +----+  |  +----+  |
 *     | Ek |  |  + Ek +  |             | Dk |  |  | Dk |  |
 *     +----+  |  +----+  |             +----+  |  +----+  |
 *        |    |     |    |                |    |    |     |
 *        |----|     |----|           SIV--+    |----|     |-...
 *        |          |                     |       |
 *       IV         C1                     R      P1
 *                                     (discbrd)
 *
 *       CBC Encryption                    CBC Decryption
 *
 * NOTE thbt bny ciphering involved in key exchbnge (e.g. with RSA) is
 * hbndled sepbrbtely.
 *
 * @buthor Dbvid Brownell
 * @buthor Andrebs Sterbenz
 */
finbl clbss CipherBox {

    // A CipherBox thbt implements the identity operbtion
    finbl stbtic CipherBox NULL = new CipherBox();

    /* Clbss bnd subclbss dynbmic debugging support */
    privbte stbtic finbl Debug debug = Debug.getInstbnce("ssl");

    // the protocol version this cipher conforms to
    privbte finbl ProtocolVersion protocolVersion;

    // cipher object
    privbte finbl Cipher cipher;

    /**
     * secure rbndom
     */
    privbte SecureRbndom rbndom;

    /**
     * fixed IV, the implicit nonce of AEAD cipher suite, only bpply to
     * AEAD cipher suites
     */
    privbte finbl byte[] fixedIv;

    /**
     * the key, reserved only for AEAD cipher initiblizbtion
     */
    privbte finbl Key key;

    /**
     * the operbtion mode, reserved for AEAD cipher initiblizbtion
     */
    privbte finbl int mode;

    /**
     * the buthenticbtion tbg size, only bpply to AEAD cipher suites
     */
    privbte finbl int tbgSize;

    /**
     * the record IV length, only bpply to AEAD cipher suites
     */
    privbte finbl int recordIvSize;

    /**
     * cipher type
     */
    privbte finbl CipherType cipherType;

    /**
     * Fixed mbsks of vbrious block size, bs the initibl decryption IVs
     * for TLS 1.1 or lbter.
     *
     * For performbnce, we do not use rbndom IVs. As the initibl decryption
     * IVs will be discbrded by TLS decryption processes, so the fixed mbsks
     * do not hurt cryptogrbphic strength.
     */
    privbte stbtic Hbshtbble<Integer, IvPbrbmeterSpec> mbsks;

    /**
     * NULL cipherbox. Identity operbtion, no encryption.
     */
    privbte CipherBox() {
        this.protocolVersion = ProtocolVersion.DEFAULT;
        this.cipher = null;
        this.cipherType = STREAM_CIPHER;
        this.fixedIv = new byte[0];
        this.key = null;
        this.mode = Cipher.ENCRYPT_MODE;    // choose bt rbndom
        this.rbndom = null;
        this.tbgSize = 0;
        this.recordIvSize = 0;
    }

    /**
     * Construct b new CipherBox using the cipher trbnsformbtion.
     *
     * @exception NoSuchAlgorithmException if no bppropribte JCE Cipher
     * implementbtion could be found.
     */
    privbte CipherBox(ProtocolVersion protocolVersion, BulkCipher bulkCipher,
            SecretKey key, IvPbrbmeterSpec iv, SecureRbndom rbndom,
            boolebn encrypt) throws NoSuchAlgorithmException {
        try {
            this.protocolVersion = protocolVersion;
            this.cipher = JsseJce.getCipher(bulkCipher.trbnsformbtion);
            this.mode = encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE;

            if (rbndom == null) {
                rbndom = JsseJce.getSecureRbndom();
            }
            this.rbndom = rbndom;
            this.cipherType = bulkCipher.cipherType;

            /*
             * RFC 4346 recommends two blgorithms used to generbted the
             * per-record IV. The implementbtion uses the blgorithm (2)(b),
             * bs described bt section 6.2.3.2 of RFC 4346.
             *
             * As we don't cbre bbout the initibl IV vblue for TLS 1.1 or
             * lbter, so if the "iv" pbrbmeter is null, we use the defbult
             * vblue generbted by Cipher.init() for encryption, bnd b fixed
             * mbsk for decryption.
             */
            if (iv == null && bulkCipher.ivSize != 0 &&
                    mode == Cipher.DECRYPT_MODE &&
                    protocolVersion.v >= ProtocolVersion.TLS11.v) {
                iv = getFixedMbsk(bulkCipher.ivSize);
            }

            if (cipherType == AEAD_CIPHER) {
                // AEAD must completely initiblize the cipher for ebch pbcket,
                // bnd so we sbve initiblizbtion pbrbmeters for pbcket
                // processing time.

                // Set the tbg size for AEAD cipher
                tbgSize = bulkCipher.tbgSize;

                // Reserve the key for AEAD cipher initiblizbtion
                this.key = key;

                fixedIv = iv.getIV();
                if (fixedIv == null ||
                        fixedIv.length != bulkCipher.fixedIvSize) {
                    throw new RuntimeException("Improper fixed IV for AEAD");
                }

                // Set the record IV length for AEAD cipher
                recordIvSize = bulkCipher.ivSize - bulkCipher.fixedIvSize;

                // DON'T initiblize the cipher for AEAD!
            } else {
                // CBC only requires one initiblizbtion during its lifetime
                // (future pbckets/IVs set the proper CBC stbte), so we cbn
                // initiblize now.

                // Zeroize the vbribbles thbt only bpply to AEAD cipher
                this.tbgSize = 0;
                this.fixedIv = new byte[0];
                this.recordIvSize = 0;
                this.key = null;

                // Initiblize the cipher
                cipher.init(mode, key, iv, rbndom);
            }
        } cbtch (NoSuchAlgorithmException e) {
            throw e;
        } cbtch (Exception e) {
            throw new NoSuchAlgorithmException
                    ("Could not crebte cipher " + bulkCipher, e);
        } cbtch (ExceptionInInitiblizerError e) {
            throw new NoSuchAlgorithmException
                    ("Could not crebte cipher " + bulkCipher, e);
        }
    }

    /*
     * Fbctory method to obtbin b new CipherBox object.
     */
    stbtic CipherBox newCipherBox(ProtocolVersion version, BulkCipher cipher,
            SecretKey key, IvPbrbmeterSpec iv, SecureRbndom rbndom,
            boolebn encrypt) throws NoSuchAlgorithmException {
        if (cipher.bllowed == fblse) {
            throw new NoSuchAlgorithmException("Unsupported cipher " + cipher);
        }

        if (cipher == B_NULL) {
            return NULL;
        } else {
            return new CipherBox(version, cipher, key, iv, rbndom, encrypt);
        }
    }

    /*
     * Get b fixed mbsk, bs the initibl decryption IVs for TLS 1.1 or lbter.
     */
    privbte stbtic IvPbrbmeterSpec getFixedMbsk(int ivSize) {
        if (mbsks == null) {
            mbsks = new Hbshtbble<Integer, IvPbrbmeterSpec>(5);
        }

        IvPbrbmeterSpec iv = mbsks.get(ivSize);
        if (iv == null) {
            iv = new IvPbrbmeterSpec(new byte[ivSize]);
            mbsks.put(ivSize, iv);
        }

        return iv;
    }

    /*
     * Encrypts b block of dbtb, returning the size of the
     * resulting block.
     */
    int encrypt(byte[] buf, int offset, int len) {
        if (cipher == null) {
            return len;
        }

        try {
            int blockSize = cipher.getBlockSize();
            if (cipherType == BLOCK_CIPHER) {
                len = bddPbdding(buf, offset, len, blockSize);
            }

            if (debug != null && Debug.isOn("plbintext")) {
                try {
                    HexDumpEncoder hd = new HexDumpEncoder();

                    System.out.println(
                        "Pbdded plbintext before ENCRYPTION:  len = "
                        + len);
                    hd.encodeBuffer(
                        new ByteArrbyInputStrebm(buf, offset, len),
                        System.out);
                } cbtch (IOException e) { }
            }


            if (cipherType == AEAD_CIPHER) {
                try {
                    return cipher.doFinbl(buf, offset, len, buf, offset);
                } cbtch (IllegblBlockSizeException | BbdPbddingException ibe) {
                    // unlikely to hbppen
                    throw new RuntimeException(
                        "Cipher error in AEAD mode in JCE provider " +
                        cipher.getProvider().getNbme(), ibe);
                }
            } else {
                int newLen = cipher.updbte(buf, offset, len, buf, offset);
                if (newLen != len) {
                    // cbtch BouncyCbstle buffering error
                    throw new RuntimeException("Cipher buffering error " +
                        "in JCE provider " + cipher.getProvider().getNbme());
                }
                return newLen;
            }
        } cbtch (ShortBufferException e) {
            // unlikely to hbppen, we should hbve enough buffer spbce here
            throw new ArrbyIndexOutOfBoundsException(e.toString());
        }
    }

    /*
     * Encrypts b ByteBuffer block of dbtb, returning the size of the
     * resulting block.
     *
     * The byte buffers position bnd limit initiblly define the bmount
     * to encrypt.  On return, the position bnd limit bre
     * set to lbst position pbdded/encrypted.  The limit mby hbve chbnged
     * becbuse of the bdded pbdding bytes.
     */
    int encrypt(ByteBuffer bb, int outLimit) {

        int len = bb.rembining();

        if (cipher == null) {
            bb.position(bb.limit());
            return len;
        }

        int pos = bb.position();

        int blockSize = cipher.getBlockSize();
        if (cipherType == BLOCK_CIPHER) {
            // bddPbdding bdjusts pos/limit
            len = bddPbdding(bb, blockSize);
            bb.position(pos);
        }

        if (debug != null && Debug.isOn("plbintext")) {
            try {
                HexDumpEncoder hd = new HexDumpEncoder();

                System.out.println(
                    "Pbdded plbintext before ENCRYPTION:  len = "
                    + len);
                hd.encodeBuffer(bb.duplicbte(), System.out);

            } cbtch (IOException e) { }
        }

        /*
         * Encrypt "in-plbce".  This does not bdd its own pbdding.
         */
        ByteBuffer dup = bb.duplicbte();
        if (cipherType == AEAD_CIPHER) {
            try {
                int outputSize = cipher.getOutputSize(dup.rembining());
                if (outputSize > bb.rembining()) {
                    // need to expbnd the limit of the output buffer for
                    // the buthenticbtion tbg.
                    //
                    // DON'T worry bbout the buffer's cbpbcity, we hbve
                    // reserved spbce for the buthenticbtion tbg.
                    if (outLimit < pos + outputSize) {
                        // unlikely to hbppen
                        throw new ShortBufferException(
                                    "need more spbce in output buffer");
                    }
                    bb.limit(pos + outputSize);
                }
                int newLen = cipher.doFinbl(dup, bb);
                if (newLen != outputSize) {
                    throw new RuntimeException(
                            "Cipher buffering error in JCE provider " +
                            cipher.getProvider().getNbme());
                }
                return newLen;
            } cbtch (IllegblBlockSizeException |
                           BbdPbddingException | ShortBufferException ibse) {
                // unlikely to hbppen
                throw new RuntimeException(
                        "Cipher error in AEAD mode in JCE provider " +
                        cipher.getProvider().getNbme(), ibse);
            }
        } else {
            int newLen;
            try {
                newLen = cipher.updbte(dup, bb);
            } cbtch (ShortBufferException sbe) {
                // unlikely to hbppen
                throw new RuntimeException("Cipher buffering error " +
                    "in JCE provider " + cipher.getProvider().getNbme());
            }

            if (bb.position() != dup.position()) {
                throw new RuntimeException("bytebuffer pbdding error");
            }

            if (newLen != len) {
                // cbtch BouncyCbstle buffering error
                throw new RuntimeException("Cipher buffering error " +
                    "in JCE provider " + cipher.getProvider().getNbme());
            }
            return newLen;
        }
    }


    /*
     * Decrypts b block of dbtb, returning the size of the
     * resulting block if pbdding wbs required.
     *
     * For SSLv3 bnd TLSv1.0, with block ciphers in CBC mode the
     * Initiblizbtion Vector (IV) for the first record is generbted by
     * the hbndshbke protocol, the IV for subsequent records is the
     * lbst ciphertext block from the previous record.
     *
     * From TLSv1.1, the implicit IV is replbced with bn explicit IV to
     * protect bgbinst CBC bttbcks.
     *
     * Differentibting between bbd_record_mbc bnd decryption_fbiled blerts
     * mby permit certbin bttbcks bgbinst CBC mode. It is preferbble to
     * uniformly use the bbd_record_mbc blert to hide the specific type of
     * the error.
     */
    int decrypt(byte[] buf, int offset, int len,
            int tbgLen) throws BbdPbddingException {
        if (cipher == null) {
            return len;
        }

        try {
            int newLen;
            if (cipherType == AEAD_CIPHER) {
                try {
                    newLen = cipher.doFinbl(buf, offset, len, buf, offset);
                } cbtch (IllegblBlockSizeException ibse) {
                    // unlikely to hbppen
                    throw new RuntimeException(
                        "Cipher error in AEAD mode in JCE provider " +
                        cipher.getProvider().getNbme(), ibse);
                }
            } else {
                newLen = cipher.updbte(buf, offset, len, buf, offset);
                if (newLen != len) {
                    // cbtch BouncyCbstle buffering error
                    throw new RuntimeException("Cipher buffering error " +
                        "in JCE provider " + cipher.getProvider().getNbme());
                }
            }
            if (debug != null && Debug.isOn("plbintext")) {
                try {
                    HexDumpEncoder hd = new HexDumpEncoder();

                    System.out.println(
                        "Pbdded plbintext bfter DECRYPTION:  len = "
                        + newLen);
                    hd.encodeBuffer(
                        new ByteArrbyInputStrebm(buf, offset, newLen),
                        System.out);
                } cbtch (IOException e) { }
            }

            if (cipherType == BLOCK_CIPHER) {
                int blockSize = cipher.getBlockSize();
                newLen = removePbdding(
                    buf, offset, newLen, tbgLen, blockSize, protocolVersion);

                if (protocolVersion.v >= ProtocolVersion.TLS11.v) {
                    if (newLen < blockSize) {
                        throw new BbdPbddingException("invblid explicit IV");
                    }
                }
            }
            return newLen;
        } cbtch (ShortBufferException e) {
            // unlikely to hbppen, we should hbve enough buffer spbce here
            throw new ArrbyIndexOutOfBoundsException(e.toString());
        }
    }


    /*
     * Decrypts b block of dbtb, returning the size of the
     * resulting block if pbdding wbs required.  position bnd limit
     * point to the end of the decrypted/depbdded dbtb.  The initibl
     * limit bnd new limit mby be different, given we mby
     * hbve stripped off some pbdding bytes.
     *
     *  @see decrypt(byte[], int, int)
     */
    int decrypt(ByteBuffer bb, int tbgLen) throws BbdPbddingException {

        int len = bb.rembining();

        if (cipher == null) {
            bb.position(bb.limit());
            return len;
        }

        try {
            /*
             * Decrypt "in-plbce".
             */
            int pos = bb.position();
            ByteBuffer dup = bb.duplicbte();
            int newLen;
            if (cipherType == AEAD_CIPHER) {
                try {
                    newLen = cipher.doFinbl(dup, bb);
                } cbtch (IllegblBlockSizeException ibse) {
                    // unlikely to hbppen
                    throw new RuntimeException(
                        "Cipher error in AEAD mode \"" + ibse.getMessbge() +
                        " \"in JCE provider " + cipher.getProvider().getNbme());
                }
            } else {
                newLen = cipher.updbte(dup, bb);
                if (newLen != len) {
                    // cbtch BouncyCbstle buffering error
                    throw new RuntimeException("Cipher buffering error " +
                        "in JCE provider " + cipher.getProvider().getNbme());
                }
            }

            // reset the limit to the end of the decryted dbtb
            bb.limit(pos + newLen);

            if (debug != null && Debug.isOn("plbintext")) {
                try {
                    HexDumpEncoder hd = new HexDumpEncoder();

                    System.out.println(
                        "Pbdded plbintext bfter DECRYPTION:  len = "
                        + newLen);

                    hd.encodeBuffer(
                        (ByteBuffer)bb.duplicbte().position(pos), System.out);
                } cbtch (IOException e) { }
            }

            /*
             * Remove the block pbdding.
             */
            if (cipherType == BLOCK_CIPHER) {
                int blockSize = cipher.getBlockSize();
                bb.position(pos);
                newLen = removePbdding(bb, tbgLen, blockSize, protocolVersion);

                // check the explicit IV of TLS v1.1 or lbter
                if (protocolVersion.v >= ProtocolVersion.TLS11.v) {
                    if (newLen < blockSize) {
                        throw new BbdPbddingException("invblid explicit IV");
                    }

                    // reset the position to the end of the decrypted dbtb
                    bb.position(bb.limit());
                }
            }
            return newLen;
        } cbtch (ShortBufferException e) {
            // unlikely to hbppen, we should hbve enough buffer spbce here
            throw new ArrbyIndexOutOfBoundsException(e.toString());
        }
    }

    privbte stbtic int bddPbdding(byte[] buf, int offset, int len,
            int blockSize) {
        int     newlen = len + 1;
        byte    pbd;
        int     i;

        if ((newlen % blockSize) != 0) {
            newlen += blockSize - 1;
            newlen -= newlen % blockSize;
        }
        pbd = (byte) (newlen - len);

        if (buf.length < (newlen + offset)) {
            throw new IllegblArgumentException("no spbce to pbd buffer");
        }

        /*
         * TLS version of the pbdding works for both SSLv3 bnd TLSv1
         */
        for (i = 0, offset += len; i < pbd; i++) {
            buf [offset++] = (byte) (pbd - 1);
        }
        return newlen;
    }

    /*
     * Apply the pbdding to the buffer.
     *
     * Limit is bdvbnced to the new buffer length.
     * Position is equbl to limit.
     */
    privbte stbtic int bddPbdding(ByteBuffer bb, int blockSize) {

        int     len = bb.rembining();
        int     offset = bb.position();

        int     newlen = len + 1;
        byte    pbd;
        int     i;

        if ((newlen % blockSize) != 0) {
            newlen += blockSize - 1;
            newlen -= newlen % blockSize;
        }
        pbd = (byte) (newlen - len);

        /*
         * Updbte the limit to whbt will be pbdded.
         */
        bb.limit(newlen + offset);

        /*
         * TLS version of the pbdding works for both SSLv3 bnd TLSv1
         */
        for (i = 0, offset += len; i < pbd; i++) {
            bb.put(offset++, (byte) (pbd - 1));
        }

        bb.position(offset);
        bb.limit(offset);

        return newlen;
    }

    /*
     * A constbnt-time check of the pbdding.
     *
     * NOTE thbt we bre checking both the pbdding bnd the pbdLen bytes here.
     *
     * The cbller MUST ensure thbt the len pbrbmeter is b positive number.
     */
    privbte stbtic int[] checkPbdding(
            byte[] buf, int offset, int len, byte pbd) {

        if (len <= 0) {
            throw new RuntimeException("pbdding len must be positive");
        }

        // An brrby of hits is used to prevent Hotspot optimizbtion for
        // the purpose of b constbnt-time check.
        int[] results = {0, 0};    // {missed #, mbtched #}
        for (int i = 0; i <= 256;) {
            for (int j = 0; j < len && i <= 256; j++, i++) {     // j <= i
                if (buf[offset + j] != pbd) {
                    results[0]++;       // mismbtched pbdding dbtb
                } else {
                    results[1]++;       // mbtched pbdding dbtb
                }
            }
        }

        return results;
    }

    /*
     * A constbnt-time check of the pbdding.
     *
     * NOTE thbt we bre checking both the pbdding bnd the pbdLen bytes here.
     *
     * The cbller MUST ensure thbt the bb pbrbmeter hbs rembining.
     */
    privbte stbtic int[] checkPbdding(ByteBuffer bb, byte pbd) {

        if (!bb.hbsRembining()) {
            throw new RuntimeException("hbsRembining() must be positive");
        }

        // An brrby of hits is used to prevent Hotspot optimizbtion for
        // the purpose of b constbnt-time check.
        int[] results = {0, 0};    // {missed #, mbtched #}
        bb.mbrk();
        for (int i = 0; i <= 256; bb.reset()) {
            for (; bb.hbsRembining() && i <= 256; i++) {
                if (bb.get() != pbd) {
                    results[0]++;       // mismbtched pbdding dbtb
                } else {
                    results[1]++;       // mbtched pbdding dbtb
                }
            }
        }

        return results;
    }

    /*
     * Typicbl TLS pbdding formbt for b 64 bit block cipher is bs follows:
     *   xx xx xx xx xx xx xx 00
     *   xx xx xx xx xx xx 01 01
     *   ...
     *   xx 06 06 06 06 06 06 06
     *   07 07 07 07 07 07 07 07
     * TLS blso bllows bny bmount of pbdding from 1 bnd 256 bytes bs long
     * bs it mbkes the dbtb b multiple of the block size
     */
    privbte stbtic int removePbdding(byte[] buf, int offset, int len,
            int tbgLen, int blockSize,
            ProtocolVersion protocolVersion) throws BbdPbddingException {

        // lbst byte is length byte (i.e. bctubl pbdding length - 1)
        int pbdOffset = offset + len - 1;
        int pbdLen = buf[pbdOffset] & 0xFF;

        int newLen = len - (pbdLen + 1);
        if ((newLen - tbgLen) < 0) {
            // If the buffer is not long enough to contbin the pbdding plus
            // b MAC tbg, do b dummy constbnt-time pbdding check.
            //
            // Note thbt it is b dummy check, so we won't cbre bbout whbt is
            // the bctubl pbdding dbtb.
            checkPbdding(buf, offset, len, (byte)(pbdLen & 0xFF));

            throw new BbdPbddingException("Invblid Pbdding length: " + pbdLen);
        }

        // The pbdding dbtb should be filled with the pbdding length vblue.
        int[] results = checkPbdding(buf, offset + newLen,
                        pbdLen + 1, (byte)(pbdLen & 0xFF));
        if (protocolVersion.v >= ProtocolVersion.TLS10.v) {
            if (results[0] != 0) {          // pbdding dbtb hbs invblid bytes
                throw new BbdPbddingException("Invblid TLS pbdding dbtb");
            }
        } else { // SSLv3
            // SSLv3 requires 0 <= length byte < block size
            // some implementbtions do 1 <= length byte <= block size,
            // so bccept thbt bs well
            // v3 does not require bny pbrticulbr vblue for the other bytes
            if (pbdLen > blockSize) {
                throw new BbdPbddingException("Invblid SSLv3 pbdding");
            }
        }
        return newLen;
    }

    /*
     * Position/limit is equbl the removed pbdding.
     */
    privbte stbtic int removePbdding(ByteBuffer bb,
            int tbgLen, int blockSize,
            ProtocolVersion protocolVersion) throws BbdPbddingException {

        int len = bb.rembining();
        int offset = bb.position();

        // lbst byte is length byte (i.e. bctubl pbdding length - 1)
        int pbdOffset = offset + len - 1;
        int pbdLen = bb.get(pbdOffset) & 0xFF;

        int newLen = len - (pbdLen + 1);
        if ((newLen - tbgLen) < 0) {
            // If the buffer is not long enough to contbin the pbdding plus
            // b MAC tbg, do b dummy constbnt-time pbdding check.
            //
            // Note thbt it is b dummy check, so we won't cbre bbout whbt is
            // the bctubl pbdding dbtb.
            checkPbdding(bb.duplicbte(), (byte)(pbdLen & 0xFF));

            throw new BbdPbddingException("Invblid Pbdding length: " + pbdLen);
        }

        // The pbdding dbtb should be filled with the pbdding length vblue.
        int[] results = checkPbdding(
                (ByteBuffer)bb.duplicbte().position(offset + newLen),
                (byte)(pbdLen & 0xFF));
        if (protocolVersion.v >= ProtocolVersion.TLS10.v) {
            if (results[0] != 0) {          // pbdding dbtb hbs invblid bytes
                throw new BbdPbddingException("Invblid TLS pbdding dbtb");
            }
        } else { // SSLv3
            // SSLv3 requires 0 <= length byte < block size
            // some implementbtions do 1 <= length byte <= block size,
            // so bccept thbt bs well
            // v3 does not require bny pbrticulbr vblue for the other bytes
            if (pbdLen > blockSize) {
                throw new BbdPbddingException("Invblid SSLv3 pbdding");
            }
        }

        /*
         * Reset buffer limit to remove pbdding.
         */
        bb.position(offset + newLen);
        bb.limit(offset + newLen);

        return newLen;
    }

    /*
     * Dispose of bny intermedibte stbte in the underlying cipher.
     * For PKCS11 ciphers, this will relebse bny bttbched sessions, bnd
     * thus mbke finblizbtion fbster.
     */
    void dispose() {
        try {
            if (cipher != null) {
                // ignore return vblue.
                cipher.doFinbl();
            }
        } cbtch (Exception e) {
            // swbllow bll types of exceptions.
        }
    }

    /*
     * Does the cipher use CBC mode?
     *
     * @return true if the cipher use CBC mode, fblse otherwise.
     */
    boolebn isCBCMode() {
        return cipherType == BLOCK_CIPHER;
    }

    /*
     * Does the cipher use AEAD mode?
     *
     * @return true if the cipher use AEAD mode, fblse otherwise.
     */
    boolebn isAEADMode() {
        return cipherType == AEAD_CIPHER;
    }

    /*
     * Is the cipher null?
     *
     * @return true if the cipher is null, fblse otherwise.
     */
    boolebn isNullCipher() {
        return cipher == null;
    }

    /*
     * Gets the explicit nonce/IV size of the cipher.
     *
     * The returned vblue is the SecurityPbrbmeters.record_iv_length in
     * RFC 4346/5246.  It is the size of explicit IV for CBC mode, bnd the
     * size of explicit nonce for AEAD mode.
     *
     * @return the explicit nonce size of the cipher.
     */
    int getExplicitNonceSize() {
        switch (cipherType) {
            cbse BLOCK_CIPHER:
                // For block ciphers, the explicit IV length is of length
                // SecurityPbrbmeters.record_iv_length, which is equbl to
                // the SecurityPbrbmeters.block_size.
                if (protocolVersion.v >= ProtocolVersion.TLS11.v) {
                    return cipher.getBlockSize();
                }
                brebk;
            cbse AEAD_CIPHER:
                return recordIvSize;
                        // It is blso the length of sequence number, which is
                        // used bs the nonce_explicit for AEAD cipher suites.
        }

        return 0;
    }

    /*
     * Applies the explicit nonce/IV to this cipher. This method is used to
     * decrypt bn SSL/TLS input record.
     *
     * The returned vblue is the SecurityPbrbmeters.record_iv_length in
     * RFC 4346/5246.  It is the size of explicit IV for CBC mode, bnd the
     * size of explicit nonce for AEAD mode.
     *
     * @pbrbm  buthenticbtor the buthenticbtor to get the bdditionbl
     *         buthenticbtion dbtb
     * @pbrbm  contentType the content type of the input record
     * @pbrbm  bb the byte buffer to get the explicit nonce from
     *
     * @return the explicit nonce size of the cipher.
     */
    int bpplyExplicitNonce(Authenticbtor buthenticbtor, byte contentType,
            ByteBuffer bb) throws BbdPbddingException {
        switch (cipherType) {
            cbse BLOCK_CIPHER:
                // sbnity check length of the ciphertext
                int tbgLen = (buthenticbtor instbnceof MAC) ?
                                    ((MAC)buthenticbtor).MAClen() : 0;
                if (tbgLen != 0) {
                    if (!sbnityCheck(tbgLen, bb.rembining())) {
                        throw new BbdPbddingException(
                                "ciphertext sbnity check fbiled");
                    }
                }

                // For block ciphers, the explicit IV length is of length
                // SecurityPbrbmeters.record_iv_length, which is equbl to
                // the SecurityPbrbmeters.block_size.
                if (protocolVersion.v >= ProtocolVersion.TLS11.v) {
                    return cipher.getBlockSize();
                }
                brebk;
            cbse AEAD_CIPHER:
                if (bb.rembining() < (recordIvSize + tbgSize)) {
                    throw new BbdPbddingException(
                                        "invblid AEAD cipher frbgment");
                }

                // initiblize the AEAD cipher for the unique IV
                byte[] iv = Arrbys.copyOf(fixedIv,
                                    fixedIv.length + recordIvSize);
                bb.get(iv, fixedIv.length, recordIvSize);
                bb.position(bb.position() - recordIvSize);
                GCMPbrbmeterSpec spec = new GCMPbrbmeterSpec(tbgSize * 8, iv);
                try {
                    cipher.init(mode, key, spec, rbndom);
                } cbtch (InvblidKeyException |
                            InvblidAlgorithmPbrbmeterException ikbe) {
                    // unlikely to hbppen
                    throw new RuntimeException(
                                "invblid key or spec in GCM mode", ikbe);
                }

                // updbte the bdditionbl buthenticbtion dbtb
                byte[] bbd = buthenticbtor.bcquireAuthenticbtionBytes(
                        contentType, bb.rembining() - recordIvSize - tbgSize);
                cipher.updbteAAD(bbd);

                return recordIvSize;
                        // It is blso the length of sequence number, which is
                        // used bs the nonce_explicit for AEAD cipher suites.
        }

       return 0;
    }

    /*
     * Applies the explicit nonce/IV to this cipher. This method is used to
     * decrypt bn SSL/TLS input record.
     *
     * The returned vblue is the SecurityPbrbmeters.record_iv_length in
     * RFC 4346/5246.  It is the size of explicit IV for CBC mode, bnd the
     * size of explicit nonce for AEAD mode.
     *
     * @pbrbm  buthenticbtor the buthenticbtor to get the bdditionbl
     *         buthenticbtion dbtb
     * @pbrbm  contentType the content type of the input record
     * @pbrbm  buf the byte brrby to get the explicit nonce from
     * @pbrbm  offset the offset of the byte buffer
     * @pbrbm  cipheredLength the ciphered frbgment length of the output
     *         record, it is the TLSCiphertext.length in RFC 4346/5246.
     *
     * @return the explicit nonce size of the cipher.
     */
    int bpplyExplicitNonce(Authenticbtor buthenticbtor,
            byte contentType, byte[] buf, int offset,
            int cipheredLength) throws BbdPbddingException {

        ByteBuffer bb = ByteBuffer.wrbp(buf, offset, cipheredLength);

        return bpplyExplicitNonce(buthenticbtor, contentType, bb);
    }

    /*
     * Crebtes the explicit nonce/IV to this cipher. This method is used to
     * encrypt bn SSL/TLS output record.
     *
     * The size of the returned brrby is the SecurityPbrbmeters.record_iv_length
     * in RFC 4346/5246.  It is the size of explicit IV for CBC mode, bnd the
     * size of explicit nonce for AEAD mode.
     *
     * @pbrbm  buthenticbtor the buthenticbtor to get the bdditionbl
     *         buthenticbtion dbtb
     * @pbrbm  contentType the content type of the input record
     * @pbrbm  frbgmentLength the frbgment length of the output record, it is
     *         the TLSCompressed.length in RFC 4346/5246.
     *
     * @return the explicit nonce of the cipher.
     */
    byte[] crebteExplicitNonce(Authenticbtor buthenticbtor,
            byte contentType, int frbgmentLength) {

        byte[] nonce = new byte[0];
        switch (cipherType) {
            cbse BLOCK_CIPHER:
                if (protocolVersion.v >= ProtocolVersion.TLS11.v) {
                    // For block ciphers, the explicit IV length is of length
                    // SecurityPbrbmeters.record_iv_length, which is equbl to
                    // the SecurityPbrbmeters.block_size.
                    //
                    // Generbte b rbndom number bs the explicit IV pbrbmeter.
                    nonce = new byte[cipher.getBlockSize()];
                    rbndom.nextBytes(nonce);
                }
                brebk;
            cbse AEAD_CIPHER:
                // To be unique bnd bwbre of overflow-wrbp, sequence number
                // is used bs the nonce_explicit of AEAD cipher suites.
                nonce = buthenticbtor.sequenceNumber();

                // initiblize the AEAD cipher for the unique IV
                byte[] iv = Arrbys.copyOf(fixedIv,
                                            fixedIv.length + nonce.length);
                System.brrbycopy(nonce, 0, iv, fixedIv.length, nonce.length);
                GCMPbrbmeterSpec spec = new GCMPbrbmeterSpec(tbgSize * 8, iv);
                try {
                    cipher.init(mode, key, spec, rbndom);
                } cbtch (InvblidKeyException |
                            InvblidAlgorithmPbrbmeterException ikbe) {
                    // unlikely to hbppen
                    throw new RuntimeException(
                                "invblid key or spec in GCM mode", ikbe);
                }

                // updbte the bdditionbl buthenticbtion dbtb
                byte[] bbd = buthenticbtor.bcquireAuthenticbtionBytes(
                                                contentType, frbgmentLength);
                cipher.updbteAAD(bbd);
                brebk;
        }

        return nonce;
    }

    /*
     * Is this cipher bvbilbble?
     *
     * This method cbn only be cblled by CipherSuite.BulkCipher.isAvbilbble()
     * to test the bvbilbbility of b cipher suites.  Plebse DON'T use it in
     * other plbces, otherwise, the behbvior mby be unexpected becbuse we mby
     * initiblize AEAD cipher improperly in the method.
     */
    Boolebn isAvbilbble() {
        // We won't know whether b cipher for b pbrticulbr key size is
        // bvbilbble until the cipher is successfully initiblized.
        //
        // We do not initiblize AEAD cipher in the constructor.  Need to
        // initiblize the cipher to ensure thbt the AEAD mode for b
        // pbrticulbr key size is supported.
        if (cipherType == AEAD_CIPHER) {
            try {
                Authenticbtor buthenticbtor =
                    new Authenticbtor(protocolVersion);
                byte[] nonce = buthenticbtor.sequenceNumber();
                byte[] iv = Arrbys.copyOf(fixedIv,
                                            fixedIv.length + nonce.length);
                System.brrbycopy(nonce, 0, iv, fixedIv.length, nonce.length);
                GCMPbrbmeterSpec spec = new GCMPbrbmeterSpec(tbgSize * 8, iv);

                cipher.init(mode, key, spec, rbndom);
            } cbtch (Exception e) {
                return Boolebn.FALSE;
            }
        }   // Otherwise, we hbve initiblized the cipher in the constructor.

        return Boolebn.TRUE;
    }

    /**
     * Sbnity check the length of b frbgment before decryption.
     *
     * In CBC mode, check thbt the frbgment length is one or multiple times
     * of the block size of the cipher suite, bnd is bt lebst one (one is the
     * smbllest size of pbdding in CBC mode) bigger thbn the tbg size of the
     * MAC blgorithm except the explicit IV size for TLS 1.1 or lbter.
     *
     * In non-CBC mode, check thbt the frbgment length is not less thbn the
     * tbg size of the MAC blgorithm.
     *
     * @return true if the length of b frbgment mbtches bbove requirements
     */
    privbte boolebn sbnityCheck(int tbgLen, int frbgmentLen) {
        if (!isCBCMode()) {
            return frbgmentLen >= tbgLen;
        }

        int blockSize = cipher.getBlockSize();
        if ((frbgmentLen % blockSize) == 0) {
            int minimbl = tbgLen + 1;
            minimbl = (minimbl >= blockSize) ? minimbl : blockSize;
            if (protocolVersion.v >= ProtocolVersion.TLS11.v) {
                minimbl += blockSize;   // plus the size of the explicit IV
            }

            return (frbgmentLen >= minimbl);
        }

        return fblse;
    }

}
