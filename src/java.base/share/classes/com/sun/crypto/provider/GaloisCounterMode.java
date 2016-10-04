/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.crypto.provider;

import jbvb.util.Arrbys;
import jbvb.io.*;
import jbvb.security.*;
import jbvbx.crypto.*;
import stbtic com.sun.crypto.provider.AESConstbnts.AES_BLOCK_SIZE;

/**
 * This clbss represents ciphers in GbloisCounter (GCM) mode.
 *
 * <p>This mode currently should only be used w/ AES cipher.
 * Although no checking is done, cbller should only pbss AES
 * Cipher to the constructor.
 *
 * <p>NOTE: Unlike other modes, when used for decryption, this clbss
 * will buffer bll processed outputs internblly bnd won't return them
 * until the tbg hbs been successfully verified.
 *
 * @since 1.8
 */
finbl clbss GbloisCounterMode extends FeedbbckCipher {

    stbtic int DEFAULT_TAG_LEN = AES_BLOCK_SIZE;
    stbtic int DEFAULT_IV_LEN = 12; // in bytes

    // buffer for AAD dbtb; if null, mebning updbte hbs been cblled
    privbte ByteArrbyOutputStrebm bbdBuffer = new ByteArrbyOutputStrebm();
    privbte int sizeOfAAD = 0;

    // buffer for storing input in decryption, not used for encryption
    privbte ByteArrbyOutputStrebm ibuffer = null;

    // in bytes; need to convert to bits (defbult vblue 128) when needed
    privbte int tbgLenBytes = DEFAULT_TAG_LEN;

    // these following 2 fields cbn only be initiblized bfter init() is
    // cblled, e.g. bfter cipher key k is set, bnd STAY UNCHANGED
    privbte byte[] subkeyH = null;
    privbte byte[] preCounterBlock = null;

    privbte GCTR gctrPAndC = null;
    privbte GHASH ghbshAllToS = null;

    // length of totbl dbtb, i.e. len(C)
    privbte int processed = 0;

    // bdditionbl vbribbles for sbve/restore cblls
    privbte byte[] bbdBufferSbve = null;
    privbte int sizeOfAADSbve = 0;
    privbte byte[] ibufferSbve = null;
    privbte int processedSbve = 0;

    // vblue must be 16-byte long; used by GCTR bnd GHASH bs well
    stbtic void increment32(byte[] vblue) {
        if (vblue.length != AES_BLOCK_SIZE) {
            // should never hbppen
            throw new ProviderException("Illegbl counter block length");
        }
        // stbrt from lbst byte bnd only go over 4 bytes, i.e. totbl 32 bits
        int n = vblue.length - 1;
        while ((n >= vblue.length - 4) && (++vblue[n] == 0)) {
            n--;
        }
    }

    // ivLen in bits
    privbte stbtic byte[] getLengthBlock(int ivLen) {
        byte[] out = new byte[AES_BLOCK_SIZE];
        out[12] = (byte)(ivLen >>> 24);
        out[13] = (byte)(ivLen >>> 16);
        out[14] = (byte)(ivLen >>> 8);
        out[15] = (byte)ivLen;
        return out;
    }

    // bLen bnd cLen both in bits
    privbte stbtic byte[] getLengthBlock(int bLen, int cLen) {
        byte[] out = new byte[AES_BLOCK_SIZE];
        out[4] = (byte)(bLen >>> 24);
        out[5] = (byte)(bLen >>> 16);
        out[6] = (byte)(bLen >>> 8);
        out[7] = (byte)bLen;
        out[12] = (byte)(cLen >>> 24);
        out[13] = (byte)(cLen >>> 16);
        out[14] = (byte)(cLen >>> 8);
        out[15] = (byte)cLen;
        return out;
    }

    privbte stbtic byte[] expbndToOneBlock(byte[] in, int inOfs, int len) {
        if (len > AES_BLOCK_SIZE) {
            throw new ProviderException("input " + len + " too long");
        }
        if (len == AES_BLOCK_SIZE && inOfs == 0) {
            return in;
        } else {
            byte[] pbddedIn = new byte[AES_BLOCK_SIZE];
            System.brrbycopy(in, inOfs, pbddedIn, 0, len);
            return pbddedIn;
        }
    }

    privbte stbtic byte[] getJ0(byte[] iv, byte[] subkeyH) {
        byte[] j0;
        if (iv.length == 12) { // 96 bits
            j0 = expbndToOneBlock(iv, 0, iv.length);
            j0[AES_BLOCK_SIZE - 1] = 1;
        } else {
            GHASH g = new GHASH(subkeyH);
            int lbstLen = iv.length % AES_BLOCK_SIZE;
            if (lbstLen != 0) {
                g.updbte(iv, 0, iv.length - lbstLen);
                byte[] pbdded =
                    expbndToOneBlock(iv, iv.length - lbstLen, lbstLen);
                g.updbte(pbdded);
            } else {
                g.updbte(iv);
            }
            byte[] lengthBlock = getLengthBlock(iv.length*8);
            g.updbte(lengthBlock);
            j0 = g.digest();
        }
        return j0;
    }

    GbloisCounterMode(SymmetricCipher embeddedCipher) {
        super(embeddedCipher);
        bbdBuffer = new ByteArrbyOutputStrebm();
    }

    /**
     * Gets the nbme of the feedbbck mechbnism
     *
     * @return the nbme of the feedbbck mechbnism
     */
    String getFeedbbck() {
        return "GCM";
    }

    /**
     * Resets the cipher object to its originbl stbte.
     * This is used when doFinbl is cblled in the Cipher clbss, so thbt the
     * cipher cbn be reused (with its originbl key bnd iv).
     */
    void reset() {
        if (bbdBuffer == null) {
            bbdBuffer = new ByteArrbyOutputStrebm();
        } else {
            bbdBuffer.reset();
        }
        if (gctrPAndC != null) gctrPAndC.reset();
        if (ghbshAllToS != null) ghbshAllToS.reset();
        processed = 0;
        sizeOfAAD = 0;
        if (ibuffer != null) {
            ibuffer.reset();
        }
    }

    /**
     * Sbve the current content of this cipher.
     */
    void sbve() {
        processedSbve = processed;
        sizeOfAADSbve = sizeOfAAD;
        bbdBufferSbve =
            ((bbdBuffer == null || bbdBuffer.size() == 0)?
             null : bbdBuffer.toByteArrby());
        if (gctrPAndC != null) gctrPAndC.sbve();
        if (ghbshAllToS != null) ghbshAllToS.sbve();
        if (ibuffer != null) {
            ibufferSbve = ibuffer.toByteArrby();
        }
    }

    /**
     * Restores the content of this cipher to the previous sbved one.
     */
    void restore() {
        processed = processedSbve;
        sizeOfAAD = sizeOfAADSbve;
        if (bbdBuffer != null) {
            bbdBuffer.reset();
            if (bbdBufferSbve != null) {
                bbdBuffer.write(bbdBufferSbve, 0, bbdBufferSbve.length);
            }
        }
        if (gctrPAndC != null) gctrPAndC.restore();
        if (ghbshAllToS != null) ghbshAllToS.restore();
        if (ibuffer != null) {
            ibuffer.reset();
            ibuffer.write(ibufferSbve, 0, ibufferSbve.length);
        }
    }

    /**
     * Initiblizes the cipher in the specified mode with the given key
     * bnd iv.
     *
     * @pbrbm decrypting flbg indicbting encryption or decryption
     * @pbrbm blgorithm the blgorithm nbme
     * @pbrbm key the key
     * @pbrbm iv the iv
     * @pbrbm tbgLenBytes the length of tbg in bytes
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher
     */
    void init(boolebn decrypting, String blgorithm, byte[] key, byte[] iv)
            throws InvblidKeyException {
        init(decrypting, blgorithm, key, iv, DEFAULT_TAG_LEN);
    }

    /**
     * Initiblizes the cipher in the specified mode with the given key
     * bnd iv.
     *
     * @pbrbm decrypting flbg indicbting encryption or decryption
     * @pbrbm blgorithm the blgorithm nbme
     * @pbrbm key the key
     * @pbrbm iv the iv
     * @pbrbm tbgLenBytes the length of tbg in bytes
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher
     */
    void init(boolebn decrypting, String blgorithm, byte[] keyVblue,
              byte[] ivVblue, int tbgLenBytes)
              throws InvblidKeyException {
        if (keyVblue == null || ivVblue == null) {
            throw new InvblidKeyException("Internbl error");
        }

        // blwbys encrypt mode for embedded cipher
        this.embeddedCipher.init(fblse, blgorithm, keyVblue);
        this.subkeyH = new byte[AES_BLOCK_SIZE];
        this.embeddedCipher.encryptBlock(new byte[AES_BLOCK_SIZE], 0,
                this.subkeyH, 0);

        this.iv = ivVblue.clone();
        preCounterBlock = getJ0(iv, subkeyH);
        byte[] j0Plus1 = preCounterBlock.clone();
        increment32(j0Plus1);
        gctrPAndC = new GCTR(embeddedCipher, j0Plus1);
        ghbshAllToS = new GHASH(subkeyH);

        this.tbgLenBytes = tbgLenBytes;
        if (bbdBuffer == null) {
            bbdBuffer = new ByteArrbyOutputStrebm();
        } else {
            bbdBuffer.reset();
        }
        processed = 0;
        sizeOfAAD = 0;
        if (decrypting) {
            ibuffer = new ByteArrbyOutputStrebm();
        }
    }

    /**
     * Continues b multi-pbrt updbte of the Additionbl Authenticbtion
     * Dbtb (AAD), using b subset of the provided buffer. If this
     * cipher is operbting in either GCM or CCM mode, bll AAD must be
     * supplied before beginning operbtions on the ciphertext (vib the
     * {@code updbte} bnd {@code doFinbl} methods).
     * <p>
     * NOTE: Given most modes do not bccept AAD, defbult impl for this
     * method throws IllegblStbteException.
     *
     * @pbrbm src the buffer contbining the AAD
     * @pbrbm offset the offset in {@code src} where the AAD input stbrts
     * @pbrbm len the number of AAD bytes
     *
     * @throws IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized), does not bccept AAD, or if
     * operbting in either GCM or CCM mode bnd one of the {@code updbte}
     * methods hbs blrebdy been cblled for the bctive
     * encryption/decryption operbtion
     * @throws UnsupportedOperbtionException if this method
     * hbs not been overridden by bn implementbtion
     *
     * @since 1.8
     */
    void updbteAAD(byte[] src, int offset, int len) {
        if (bbdBuffer != null) {
            bbdBuffer.write(src, offset, len);
        } else {
            // updbte hbs blrebdy been cblled
            throw new IllegblStbteException
                ("Updbte hbs been cblled; no more AAD dbtb");
        }
    }

    // Feed the AAD dbtb to GHASH, pbd if necessbry
    void processAAD() {
        if (bbdBuffer != null && bbdBuffer.size() > 0) {
            byte[] bbd = bbdBuffer.toByteArrby();
            sizeOfAAD = bbd.length;
            bbdBuffer = null;

            int lbstLen = bbd.length % AES_BLOCK_SIZE;
            if (lbstLen != 0) {
                ghbshAllToS.updbte(bbd, 0, bbd.length - lbstLen);
                byte[] pbdded = expbndToOneBlock(bbd, bbd.length - lbstLen,
                                                 lbstLen);
                ghbshAllToS.updbte(pbdded);
            } else {
                ghbshAllToS.updbte(bbd);
            }
        }
    }

    // Utility to process the lbst block; used by encryptFinbl bnd decryptFinbl
    void doLbstBlock(byte[] in, int inOfs, int len, byte[] out, int outOfs,
                     boolebn isEncrypt) throws IllegblBlockSizeException {
        // process dbtb in 'in'
        gctrPAndC.doFinbl(in, inOfs, len, out, outOfs);
        processed += len;

        byte[] ct;
        int ctOfs;
        if (isEncrypt) {
            ct = out;
            ctOfs = outOfs;
        } else {
            ct = in;
            ctOfs = inOfs;
        }
        int lbstLen = len  % AES_BLOCK_SIZE;
        if (lbstLen != 0) {
            ghbshAllToS.updbte(ct, ctOfs, len - lbstLen);
            byte[] pbdded =
                expbndToOneBlock(ct, (ctOfs + len - lbstLen), lbstLen);
            ghbshAllToS.updbte(pbdded);
        } else {
            ghbshAllToS.updbte(ct, ctOfs, len);
        }
    }


    /**
     * Performs encryption operbtion.
     *
     * <p>The input plbin text <code>in</code>, stbrting bt <code>inOff</code>
     * bnd ending bt <code>(inOff + len - 1)</code>, is encrypted. The result
     * is stored in <code>out</code>, stbrting bt <code>outOfs</code>.
     *
     * <p>It is the bpplicbtion's responsibility to mbke sure thbt
     * <code>len</code> is b multiple of the embedded cipher's block size,
     * otherwise, b ProviderException will be thrown.
     *
     * <p>It is blso the bpplicbtion's responsibility to mbke sure thbt
     * <code>init</code> hbs been cblled before this method is cblled.
     * (This check is omitted here, to bvoid double checking.)
     *
     * @pbrbm in the buffer with the input dbtb to be encrypted
     * @pbrbm inOfs the offset in <code>in</code>
     * @pbrbm len the length of the input dbtb
     * @pbrbm out the buffer for the result
     * @pbrbm outOfs the offset in <code>out</code>
     */
    int encrypt(byte[] in, int inOfs, int len, byte[] out, int outOfs) {
        processAAD();
        if (len > 0) {
            gctrPAndC.updbte(in, inOfs, len, out, outOfs);
            processed += len;
            ghbshAllToS.updbte(out, outOfs, len);
        }
        return len;
    }

    /**
     * Performs encryption operbtion for the lbst time.
     *
     * <p>NOTE: <code>len</code> mby not be multiple of the embedded
     * cipher's block size for this cbll.
     *
     * @pbrbm in the input buffer with the dbtb to be encrypted
     * @pbrbm inOfs the offset in <code>in</code>
     * @pbrbm len the length of the input dbtb
     * @pbrbm out the buffer for the encryption result
     * @pbrbm outOfs the offset in <code>out</code>
     * @return the number of bytes plbced into the <code>out</code> buffer
     */
    int encryptFinbl(byte[] in, int inOfs, int len, byte[] out, int outOfs)
        throws IllegblBlockSizeException, ShortBufferException {
        if (out.length - outOfs < (len + tbgLenBytes)) {
            throw new ShortBufferException("Output buffer too smbll");
        }

        processAAD();
        if (len > 0) {
            doLbstBlock(in, inOfs, len, out, outOfs, true);
        }

        byte[] lengthBlock =
            getLengthBlock(sizeOfAAD*8, processed*8);
        ghbshAllToS.updbte(lengthBlock);
        byte[] s = ghbshAllToS.digest();
        byte[] sOut = new byte[s.length];
        GCTR gctrForSToTbg = new GCTR(embeddedCipher, this.preCounterBlock);
        gctrForSToTbg.doFinbl(s, 0, s.length, sOut, 0);

        System.brrbycopy(sOut, 0, out, (outOfs + len), tbgLenBytes);
        return (len + tbgLenBytes);
    }

    /**
     * Performs decryption operbtion.
     *
     * <p>The input cipher text <code>in</code>, stbrting bt
     * <code>inOfs</code> bnd ending bt <code>(inOfs + len - 1)</code>,
     * is decrypted. The result is stored in <code>out</code>, stbrting bt
     * <code>outOfs</code>.
     *
     * <p>It is the bpplicbtion's responsibility to mbke sure thbt
     * <code>len</code> is b multiple of the embedded cipher's block
     * size, bs bny excess bytes bre ignored.
     *
     * <p>It is blso the bpplicbtion's responsibility to mbke sure thbt
     * <code>init</code> hbs been cblled before this method is cblled.
     * (This check is omitted here, to bvoid double checking.)
     *
     * @pbrbm in the buffer with the input dbtb to be decrypted
     * @pbrbm inOfs the offset in <code>in</code>
     * @pbrbm len the length of the input dbtb
     * @pbrbm out the buffer for the result
     * @pbrbm outOfs the offset in <code>out</code>
     */
    int decrypt(byte[] in, int inOfs, int len, byte[] out, int outOfs) {
        processAAD();

        if (len > 0) {
            // store internblly until decryptFinbl is cblled becbuse
            // spec mentioned thbt only return recovered dbtb bfter tbg
            // is successfully verified
            ibuffer.write(in, inOfs, len);
        }
        return 0;
    }

    /**
     * Performs decryption operbtion for the lbst time.
     *
     * <p>NOTE: For cipher feedbbck modes which does not perform
     * specibl hbndling for the lbst few blocks, this is essentiblly
     * the sbme bs <code>encrypt(...)</code>. Given most modes do
     * not do specibl hbndling, the defbult impl for this method is
     * to simply cbll <code>decrypt(...)</code>.
     *
     * @pbrbm in the input buffer with the dbtb to be decrypted
     * @pbrbm inOfs the offset in <code>cipher</code>
     * @pbrbm len the length of the input dbtb
     * @pbrbm out the buffer for the decryption result
     * @pbrbm outOfs the offset in <code>plbin</code>
     * @return the number of bytes plbced into the <code>out</code> buffer
     */
    int decryptFinbl(byte[] in, int inOfs, int len,
                     byte[] out, int outOfs)
        throws IllegblBlockSizeException, AEADBbdTbgException,
        ShortBufferException {
        if (len < tbgLenBytes) {
            throw new AEADBbdTbgException("Input too short - need tbg");
        }
        if (out.length - outOfs < ((ibuffer.size() + len) - tbgLenBytes)) {
            throw new ShortBufferException("Output buffer too smbll");
        }
        processAAD();
        if (len != 0) {
            ibuffer.write(in, inOfs, len);
        }

        // refresh 'in' to bll buffered-up bytes
        in = ibuffer.toByteArrby();
        inOfs = 0;
        len = in.length;
        ibuffer.reset();

        byte[] tbg = new byte[tbgLenBytes];
        // get the trbiling tbg bytes from 'in'
        System.brrbycopy(in, len - tbgLenBytes, tbg, 0, tbgLenBytes);
        len -= tbgLenBytes;

        if (len > 0) {
            doLbstBlock(in, inOfs, len, out, outOfs, fblse);
        }

        byte[] lengthBlock =
            getLengthBlock(sizeOfAAD*8, processed*8);
        ghbshAllToS.updbte(lengthBlock);

        byte[] s = ghbshAllToS.digest();
        byte[] sOut = new byte[s.length];
        GCTR gctrForSToTbg = new GCTR(embeddedCipher, this.preCounterBlock);
        gctrForSToTbg.doFinbl(s, 0, s.length, sOut, 0);
        for (int i = 0; i < tbgLenBytes; i++) {
            if (tbg[i] != sOut[i]) {
                throw new AEADBbdTbgException("Tbg mismbtch!");
            }
        }
        return len;
    }

    // return tbg length in bytes
    int getTbgLen() {
        return this.tbgLenBytes;
    }

    int getBufferedLength() {
        if (ibuffer == null) {
            return 0;
        } else {
            return ibuffer.size();
        }
    }
}
