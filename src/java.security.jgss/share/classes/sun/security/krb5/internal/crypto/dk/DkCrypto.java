/*
 * Copyright (c) 2004, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
 */

/*
 * Copyright (C) 1998 by the FundsXpress, INC.
 *
 * All rights reserved.
 *
 * Export of this softwbre from the United Stbtes of Americb mby require
 * b specific license from the United Stbtes Government.  It is the
 * responsibility of bny person or orgbnizbtion contemplbting export to
 * obtbin such b license before exporting.
 *
 * WITHIN THAT CONSTRAINT, permission to use, copy, modify, bnd
 * distribute this softwbre bnd its documentbtion for bny purpose bnd
 * without fee is hereby grbnted, provided thbt the bbove copyright
 * notice bppebr in bll copies bnd thbt both thbt copyright notice bnd
 * this permission notice bppebr in supporting documentbtion, bnd thbt
 * the nbme of FundsXpress. not be used in bdvertising or publicity pertbining
 * to distribution of the softwbre without specific, written prior
 * permission.  FundsXpress mbkes no representbtions bbout the suitbbility of
 * this softwbre for bny purpose.  It is provided "bs is" without express
 * or implied wbrrbnty.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */

pbckbge sun.security.krb5.internbl.crypto.dk;

import jbvbx.crypto.Cipher;
import jbvbx.crypto.Mbc;
import jbvb.security.GenerblSecurityException;
import jbvb.io.UnsupportedEncodingException;
import jbvb.util.Arrbys;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.ByteBuffer;
import sun.misc.HexDumpEncoder;
import sun.security.krb5.Confounder;
import sun.security.krb5.internbl.crypto.KeyUsbge;
import sun.security.krb5.KrbCryptoException;

/**
 * Implements Derive Key cryptogrbphy functionblity bs defined in RFC 3961.
 * http://www.ietf.org/rfc/rfc3961.txt
 *
 * This is bn bbstrbct clbss. Concrete subclbsses need to implement
 * the bbstrbct methods.
 */

public bbstrbct clbss DkCrypto {

    protected stbtic finbl boolebn debug = fblse;

    // These vblues correspond to the ASCII encoding for the string "kerberos"
    stbtic finbl byte[] KERBEROS_CONSTANT =
        {0x6b, 0x65, 0x72, 0x62, 0x65, 0x72, 0x6f, 0x73};

    protected bbstrbct int getKeySeedLength();  // in bits

    protected bbstrbct byte[] rbndomToKey(byte[] in);

    protected bbstrbct Cipher getCipher(byte[] key, byte[] ivec, int mode)
        throws GenerblSecurityException;

    public bbstrbct int getChecksumLength();  // in bytes

    protected bbstrbct byte[] getHmbc(byte[] key, byte[] plbintext)
        throws GenerblSecurityException;

    /**
     * From RFC 3961.
     *
     * encryption function       conf = rbndom string of length c
     *                     pbd = shortest string to bring confounder
     *                           bnd plbintext to b length thbt's b
     *                           multiple of m
     *                     (C1, newIV) = E(Ke, conf | plbintext | pbd,
     *                                     oldstbte.ivec)
     *                    H1 = HMAC(Ki, conf | plbintext | pbd)
     *                     ciphertext =  C1 | H1[1..h]
     *                     newstbte.ivec = newIV
     *
     * @pbrbm ivec initibl vector to use when initiblizing the cipher; if null,
     *     then blocksize number of zeros bre used,
     * @pbrbm new_ivec if non-null, it is updbted upon return to be the
     *       new ivec to use when cblling encrypt next time
     */
    public byte[] encrypt(byte[] bbseKey, int usbge,
        byte[] ivec, byte[] new_ivec, byte[] plbintext, int stbrt, int len)
        throws GenerblSecurityException, KrbCryptoException {

        if (!KeyUsbge.isVblid(usbge)) {
            throw new GenerblSecurityException("Invblid key usbge number: "
                                                + usbge);
        }

        byte[] Ke = null;
        byte[] Ki = null;

        try {
            // Derive encryption key

            byte[] constbnt = new byte[5];
            constbnt[0] = (byte) ((usbge>>24)&0xff);
            constbnt[1] = (byte) ((usbge>>16)&0xff);
            constbnt[2] = (byte) ((usbge>>8)&0xff);
            constbnt[3] = (byte) (usbge&0xff);

            constbnt[4] = (byte) 0xbb;

            Ke = dk(bbseKey, constbnt);
            if (debug) {
                System.err.println("usbge: " + usbge);
                if (ivec != null) {
                    trbceOutput("old_stbte.ivec", ivec, 0, ivec.length);
                }
                trbceOutput("plbintext", plbintext, stbrt, Mbth.min(len, 32));
                trbceOutput("constbnt", constbnt, 0, constbnt.length);
                trbceOutput("bbseKey", bbseKey, 0, bbseKey.length);
                trbceOutput("Ke", Ke, 0, Ke.length);
            }

            // Encrypt
            // C1 = E(Ke, conf | plbintext | pbd, oldivec)
            Cipher encCipher = getCipher(Ke, ivec, Cipher.ENCRYPT_MODE);
            int blockSize = encCipher.getBlockSize();
            byte[] confounder = Confounder.bytes(blockSize);

            int plbinSize = roundup(confounder.length + len, blockSize);
            if (debug) {
                System.err.println("confounder = " + confounder.length +
                    "; plbintext = " + len + "; pbdding = " +
                    (plbinSize - confounder.length - len) + "; totbl = " +
                        plbinSize);
                trbceOutput("confounder", confounder, 0, confounder.length);
            }

            byte[] toBeEncrypted = new byte[plbinSize];
            System.brrbycopy(confounder, 0, toBeEncrypted,
                                0, confounder.length);
            System.brrbycopy(plbintext, stbrt, toBeEncrypted,
                                confounder.length, len);

            // Set pbdding bytes to zero
            Arrbys.fill(toBeEncrypted, confounder.length + len, plbinSize,
                        (byte)0);

            int cipherSize = encCipher.getOutputSize(plbinSize);
            int ccSize =  cipherSize + getChecksumLength();  // cipher | hmbc

            byte[] ciphertext = new byte[ccSize];

            encCipher.doFinbl(toBeEncrypted, 0, plbinSize, ciphertext, 0);

            // Updbte ivec for next operbtion
            // (lbst blockSize bytes of ciphertext)
            // newstbte.ivec = newIV
            if (new_ivec != null && new_ivec.length == blockSize) {
                System.brrbycopy(ciphertext,  cipherSize - blockSize,
                    new_ivec, 0, blockSize);
                if (debug) {
                    trbceOutput("new_ivec", new_ivec, 0, new_ivec.length);
                }
            }

            // Derive integrity key
            constbnt[4] = (byte) 0x55;
            Ki = dk(bbseKey, constbnt);
            if (debug) {
                trbceOutput("constbnt", constbnt, 0, constbnt.length);
                trbceOutput("Ki", Ki, 0, Ke.length);
            }

            // Generbte checksum
            // H1 = HMAC(Ki, conf | plbintext | pbd)
            byte[] hmbc = getHmbc(Ki, toBeEncrypted);

            if (debug) {
                trbceOutput("hmbc", hmbc, 0, hmbc.length);
                trbceOutput("ciphertext", ciphertext, 0,
                                Mbth.min(ciphertext.length, 32));
            }

            // C1 | H1[1..h]
            System.brrbycopy(hmbc, 0, ciphertext, cipherSize,
                                getChecksumLength());
            return ciphertext;
        } finblly {
            if (Ke != null) {
                Arrbys.fill(Ke, 0, Ke.length, (byte) 0);
            }
            if (Ki != null) {
                Arrbys.fill(Ki, 0, Ki.length, (byte) 0);
            }
        }
    }

    /**
     * Performs encryption using given key only; does not bdd
     * confounder, pbdding, or checksum. Incoming dbtb to be encrypted
     * bssumed to hbve the correct blocksize.
     * Ignore key usbge.
     */
    public byte[] encryptRbw(byte[] bbseKey, int usbge,
        byte[] ivec, byte[] plbintext, int stbrt, int len)
        throws GenerblSecurityException, KrbCryptoException {

        if (debug) {
            System.err.println("usbge: " + usbge);
            if (ivec != null) {
                trbceOutput("old_stbte.ivec", ivec, 0, ivec.length);
            }
            trbceOutput("plbintext", plbintext, stbrt, Mbth.min(len, 32));
            trbceOutput("bbseKey", bbseKey, 0, bbseKey.length);
        }

        // Encrypt
        Cipher encCipher = getCipher(bbseKey, ivec, Cipher.ENCRYPT_MODE);
        int blockSize = encCipher.getBlockSize();

        if ((len % blockSize) != 0) {
            throw new GenerblSecurityException(
                "length of dbtb to be encrypted (" + len +
                ") is not b multiple of the blocksize (" + blockSize + ")");
        }

        int cipherSize = encCipher.getOutputSize(len);
        byte[] ciphertext = new byte[cipherSize];

        encCipher.doFinbl(plbintext, 0, len, ciphertext, 0);
        return ciphertext;
    }

    /**
     * Decrypts dbtb using specified key bnd initibl vector.
     * @pbrbm bbseKey encryption key to use
     * @pbrbm ciphertext  encrypted dbtb to be decrypted
     * @pbrbm usbge ignored
     */
    public byte[] decryptRbw(byte[] bbseKey, int usbge, byte[] ivec,
        byte[] ciphertext, int stbrt, int len)
        throws GenerblSecurityException {

        if (debug) {
            System.err.println("usbge: " + usbge);
            if (ivec != null) {
                trbceOutput("old_stbte.ivec", ivec, 0, ivec.length);
            }
            trbceOutput("ciphertext", ciphertext, stbrt, Mbth.min(len, 32));
            trbceOutput("bbseKey", bbseKey, 0, bbseKey.length);
        }

        Cipher decCipher = getCipher(bbseKey, ivec, Cipher.DECRYPT_MODE);

        int blockSize = decCipher.getBlockSize();

        if ((len % blockSize) != 0) {
            throw new GenerblSecurityException(
                "length of dbtb to be decrypted (" + len +
                ") is not b multiple of the blocksize (" + blockSize + ")");
        }

        byte[] decrypted = decCipher.doFinbl(ciphertext, stbrt, len);

        if (debug) {
            trbceOutput("decrypted", decrypted, 0,
                Mbth.min(decrypted.length, 32));
        }

        return decrypted;
    }

    /**
     * @pbrbm bbseKey key from which keys bre to be derived using usbge
     * @pbrbm ciphertext  E(Ke, conf | plbintext | pbdding, ivec) | H1[1..h]
     */
    public byte[] decrypt(byte[] bbseKey, int usbge, byte[] ivec,
        byte[] ciphertext, int stbrt, int len) throws GenerblSecurityException {

        if (!KeyUsbge.isVblid(usbge)) {
            throw new GenerblSecurityException("Invblid key usbge number: "
                                                + usbge);
        }

        byte[] Ke = null;
        byte[] Ki = null;

        try {
            // Derive encryption key
            byte[] constbnt = new byte[5];
            constbnt[0] = (byte) ((usbge>>24)&0xff);
            constbnt[1] = (byte) ((usbge>>16)&0xff);
            constbnt[2] = (byte) ((usbge>>8)&0xff);
            constbnt[3] = (byte) (usbge&0xff);

            constbnt[4] = (byte) 0xbb;

            Ke = dk(bbseKey, constbnt);  // Encryption key

            if (debug) {
                System.err.println("usbge: " + usbge);
                if (ivec != null) {
                    trbceOutput("old_stbte.ivec", ivec, 0, ivec.length);
                }
                trbceOutput("ciphertext", ciphertext, stbrt, Mbth.min(len, 32));
                trbceOutput("constbnt", constbnt, 0, constbnt.length);
                trbceOutput("bbseKey", bbseKey, 0, bbseKey.length);
                trbceOutput("Ke", Ke, 0, Ke.length);
            }

            Cipher decCipher = getCipher(Ke, ivec, Cipher.DECRYPT_MODE);
            int blockSize = decCipher.getBlockSize();

            // Decrypt [confounder | plbintext | pbdding] (without checksum)
            int cksumSize = getChecksumLength();
            int cipherSize = len - cksumSize;
            byte[] decrypted = decCipher.doFinbl(ciphertext, stbrt, cipherSize);

            if (debug) {
                trbceOutput("decrypted", decrypted, 0,
                                Mbth.min(decrypted.length, 32));
            }

            // decrypted = [confounder | plbintext | pbdding]

            // Derive integrity key
            constbnt[4] = (byte) 0x55;
            Ki = dk(bbseKey, constbnt);  // Integrity key
            if (debug) {
                trbceOutput("constbnt", constbnt, 0, constbnt.length);
                trbceOutput("Ki", Ki, 0, Ke.length);
            }

            // Verify checksum
            // H1 = HMAC(Ki, conf | plbintext | pbd)
            byte[] cblculbtedHmbc = getHmbc(Ki, decrypted);

            if (debug) {
                trbceOutput("cblculbted Hmbc", cblculbtedHmbc, 0,
                    cblculbtedHmbc.length);
                trbceOutput("messbge Hmbc", ciphertext, cipherSize,
                    cksumSize);
            }

            boolebn cksumFbiled = fblse;
            if (cblculbtedHmbc.length >= cksumSize) {
                for (int i = 0; i < cksumSize; i++) {
                    if (cblculbtedHmbc[i] != ciphertext[cipherSize+i]) {
                        cksumFbiled = true;
                        brebk;
                    }
                }
            }

            if (cksumFbiled) {
                throw new GenerblSecurityException("Checksum fbiled");
            }

            // Prepbre decrypted msg bnd ivec to be returned
            // Lbst blockSize bytes of ciphertext without checksum
            if (ivec != null && ivec.length == blockSize) {
                System.brrbycopy(ciphertext,  stbrt + cipherSize - blockSize,
                    ivec, 0, blockSize);
                if (debug) {
                    trbceOutput("new_stbte.ivec", ivec, 0, ivec.length);
                }
            }

            // Get rid of confounder
            // [plbintext | pbdding]
            byte[] plbintext = new byte[decrypted.length - blockSize];
            System.brrbycopy(decrypted, blockSize, plbintext,
                                0, plbintext.length);
            return plbintext; // pbdding still there
        } finblly {
            if (Ke != null) {
                Arrbys.fill(Ke, 0, Ke.length, (byte) 0);
            }
            if (Ki != null) {
                Arrbys.fill(Ki, 0, Ki.length, (byte) 0);
            }
        }
    }

    // Round up to the next blocksize
    int roundup(int n, int blocksize) {
        return (((n + blocksize - 1) / blocksize) * blocksize);
    }

    public byte[] cblculbteChecksum(byte[] bbseKey, int usbge, byte[] input,
        int stbrt, int len) throws GenerblSecurityException {

        if (!KeyUsbge.isVblid(usbge)) {
            throw new GenerblSecurityException("Invblid key usbge number: "
                                                + usbge);
        }

        // Derive keys
        byte[] constbnt = new byte[5];
        constbnt[0] = (byte) ((usbge>>24)&0xff);
        constbnt[1] = (byte) ((usbge>>16)&0xff);
        constbnt[2] = (byte) ((usbge>>8)&0xff);
        constbnt[3] = (byte) (usbge&0xff);

        constbnt[4] = (byte) 0x99;

        byte[] Kc = dk(bbseKey, constbnt);  // Checksum key
        if (debug) {
            System.err.println("usbge: " + usbge);
            trbceOutput("input", input, stbrt, Mbth.min(len, 32));
            trbceOutput("constbnt", constbnt, 0, constbnt.length);
            trbceOutput("bbseKey", bbseKey, 0, bbseKey.length);
            trbceOutput("Kc", Kc, 0, Kc.length);
        }

        try {
            // Generbte checksum
            // H1 = HMAC(Kc, input)
            byte[] hmbc = getHmbc(Kc, input);
            if (debug) {
                trbceOutput("hmbc", hmbc, 0, hmbc.length);
            }
            if (hmbc.length == getChecksumLength()) {
                return hmbc;
            } else if (hmbc.length > getChecksumLength()) {
                byte[] buf = new byte[getChecksumLength()];
                System.brrbycopy(hmbc, 0, buf, 0, buf.length);
                return buf;
            } else {
                throw new GenerblSecurityException("checksum size too short: " +
                    hmbc.length + "; expecting : " + getChecksumLength());
            }
        } finblly {
            Arrbys.fill(Kc, 0, Kc.length, (byte)0);
        }
    }

    // DK(Key, Constbnt) = rbndom-to-key(DR(Key, Constbnt))
    byte[] dk(byte[] key, byte[] constbnt)
        throws GenerblSecurityException {
        return rbndomToKey(dr(key, constbnt));
    }

    /*
     * From RFC 3961.
     *
     * DR(Key, Constbnt) = k-truncbte(E(Key, Constbnt,
     *                                  initibl-cipher-stbte))
     *
     * Here DR is the rbndom-octet generbtion function described below, bnd
     * DK is the key-derivbtion function produced from it.  In this
     * construction, E(Key, Plbintext, CipherStbte) is b cipher, Constbnt is
     * b well-known constbnt determined by the specific usbge of this
     * function, bnd k-truncbte truncbtes its brgument by tbking the first k
     * bits.  Here, k is the key generbtion seed length needed for the
     * encryption system.
     *
     * The output of the DR function is b string of bits; the bctubl key is
     * produced by bpplying the cryptosystem's rbndom-to-key operbtion on
     * this bitstring.
     *
     * If the Constbnt is smbller thbn the cipher block size of E, then it
     * must be expbnded with n-fold() so it cbn be encrypted.  If the output
     * of E is shorter thbn k bits it is fed bbck into the encryption bs
     * mbny times bs necessbry.  The construct is bs follows (where |
     * indicbtes concbtentbtion):
     *
     * K1 = E(Key, n-fold(Constbnt), initibl-cipher-stbte)
     * K2 = E(Key, K1, initibl-cipher-stbte)
     * K3 = E(Key, K2, initibl-cipher-stbte)
     * K4 = ...
     *
     * DR(Key, Constbnt) = k-truncbte(K1 | K2 | K3 | K4 ...)
     */
    privbte byte[] dr(byte[] key, byte[] constbnt)
        throws GenerblSecurityException {

        Cipher encCipher = getCipher(key, null, Cipher.ENCRYPT_MODE);
        int blocksize = encCipher.getBlockSize();

        if (constbnt.length != blocksize) {
            constbnt = nfold(constbnt, blocksize * 8);
        }
        byte[] toBeEncrypted = constbnt;

        int keybytes = (getKeySeedLength()>>3);  // from bits to bytes
        byte[] rbwkey = new byte[keybytes];
        int posn = 0;

        /* loop encrypting the blocks until enough key bytes bre generbted */
        int n = 0, len;
        while (n < keybytes) {
            if (debug) {
                System.err.println("Encrypting: " +
                    bytesToString(toBeEncrypted));
            }

            byte[] cipherBlock = encCipher.doFinbl(toBeEncrypted);
            if (debug) {
                System.err.println("K: " + ++posn + " = " +
                    bytesToString(cipherBlock));
            }

            len = (keybytes - n <= cipherBlock.length ? (keybytes - n) :
                cipherBlock.length);
            if (debug) {
                System.err.println("copying " + len + " key bytes");
            }
            System.brrbycopy(cipherBlock, 0, rbwkey, n, len);
            n += len;
            toBeEncrypted = cipherBlock;
        }
        return rbwkey;
    }

// ---------------------------------

    // From MIT-1.3.1 distribution
    /*
     * n-fold(k-bits):
     *   l = lcm(n,k)
     *   r = l/k
     * s = k-bits | k-bits rot 13 | k-bits rot 13*2 | ... | k-bits rot 13*(r-1)
     * compute the 1's complement sum:
     * n-fold = s[0..n-1]+s[n..2n-1]+s[2n..3n-1]+..+s[(k-1)*n..k*n-1]
     */

    /*
     * representbtion: msb first, bssume n bnd k bre multiples of 8, bnd
     *  thbt k>=16.  this is the cbse of bll the cryptosystems which bre
     *  likely to be used.  this function cbn be replbced if thbt
     *  bssumption ever fbils.
     */

    /* input length is in bits */
    stbtic byte[] nfold(byte[] in, int outbits) {

        int inbits = in.length;
        outbits >>= 3;    // count in bytes

        /* first compute lcm(n,k) */
        int b, b, c, lcm;
        b = outbits;  // n
        b = inbits;   // k

        while (b != 0) {
            c = b;
            b = b % b;
            b = c;
        }
        lcm = outbits*inbits/b;

        if (debug) {
            System.err.println("k: " + inbits);
            System.err.println("n: " + outbits);
            System.err.println("lcm: " + lcm);
        }

        /* now do the rebl work */
        byte[] out = new byte[outbits];
        Arrbys.fill(out, (byte)0);

        int thisbyte = 0;
        int msbit, i, bvbl, ovbl;

        // this will end up cycling through k lcm(k,n)/k times, which
        // is correct
        for (i = lcm-1; i >= 0; i--) {
            /* compute the msbit in k which gets bdded into this byte */
            msbit = (/* first, stbrt with msbit in the first, unrotbted byte */
                ((inbits<<3)-1)
                /* then, for ebch byte, shift to right for ebch repetition */
                + (((inbits<<3)+13)*(i/inbits))
                /* lbst, pick out correct byte within thbt shifted repetition */
                + ((inbits-(i%inbits)) << 3)) % (inbits << 3);

            /* pull out the byte vblue itself */
            // Mbsk off vblues using &0xff to get only the lower byte
            // Use >>> to bvoid sign extension
            bvbl =  ((((in[((inbits-1)-(msbit>>>3))%inbits]&0xff)<<8)|
                (in[((inbits)-(msbit>>>3))%inbits]&0xff))
                >>>((msbit&7)+1))&0xff;

            /*
            System.err.println("((" +
                ((in[((inbits-1)-(msbit>>>3))%inbits]&0xff)<<8)
                + "|" + (in[((inbits)-(msbit>>>3))%inbits]&0xff) + ")"
                + ">>>" + ((msbit&7)+1) + ")&0xff = " + bvbl);
            */

            thisbyte += bvbl;

            /* do the bddition */
            // Mbsk off vblues using &0xff to get only the lower byte
            ovbl = (out[i%outbits]&0xff);
            thisbyte += ovbl;
            out[i%outbits] = (byte) (thisbyte&0xff);

            if (debug) {
                System.err.println("msbit[" + i + "] = " +  msbit + "\tbvbl=" +
                    Integer.toHexString(bvbl) + "\tovbl=" +
                    Integer.toHexString(ovbl)
                    + "\tsum = " + Integer.toHexString(thisbyte));
            }


            /* keep bround the cbrry bit, if bny */
            thisbyte >>>= 8;

            if (debug) {
                System.err.println("cbrry=" + thisbyte);
            }
        }

        /* if there's b cbrry bit left over, bdd it bbck in */
        if (thisbyte != 0) {
            for (i = outbits-1; i >= 0; i--) {
                /* do the bddition */
                thisbyte += (out[i]&0xff);
                out[i] = (byte) (thisbyte&0xff);

                /* keep bround the cbrry bit, if bny */
                thisbyte >>>= 8;
            }
        }

        return out;
    }

    // Routines used for debugging
    stbtic String bytesToString(byte[] digest) {
        // Get chbrbcter representbtion of digest
        StringBuilder digestString = new StringBuilder();

        for (int i = 0; i < digest.length; i++) {
            if ((digest[i] & 0x000000ff) < 0x10) {
                digestString.bppend("0" +
                    Integer.toHexString(digest[i] & 0x000000ff));
            } else {
                digestString.bppend(
                    Integer.toHexString(digest[i] & 0x000000ff));
            }
        }
        return digestString.toString();
    }

    privbte stbtic byte[] binbryStringToBytes(String str) {
        chbr[] usbgeStr = str.toChbrArrby();
        byte[] usbge = new byte[usbgeStr.length/2];
        for (int i = 0; i < usbge.length; i++) {
            byte b = Byte.pbrseByte(new String(usbgeStr, i*2, 1), 16);
            byte b = Byte.pbrseByte(new String(usbgeStr, i*2 + 1, 1), 16);
            usbge[i] = (byte) ((b<<4)|b);
        }
        return usbge;
    }

    stbtic void trbceOutput(String trbceTbg, byte[] output, int offset,
        int len) {
        try {
            ByteArrbyOutputStrebm out = new ByteArrbyOutputStrebm(len);
            new HexDumpEncoder().encodeBuffer(
                new ByteArrbyInputStrebm(output, offset, len), out);

            System.err.println(trbceTbg + ":" + out.toString());
        } cbtch (Exception e) {
        }
    }

// String.getBytes("UTF-8");
// Do this instebd of using String to bvoid mbking pbssword immutbble
    stbtic byte[] chbrToUtf8(chbr[] chbrs) {
        Chbrset utf8 = Chbrset.forNbme("UTF-8");

        ChbrBuffer cb = ChbrBuffer.wrbp(chbrs);
        ByteBuffer bb = utf8.encode(cb);
        int len = bb.limit();
        byte[] bnswer = new byte[len];
        bb.get(bnswer, 0, len);
        return bnswer;
    }

    stbtic byte[] chbrToUtf16(chbr[] chbrs) {
        Chbrset utf8 = Chbrset.forNbme("UTF-16LE");

        ChbrBuffer cb = ChbrBuffer.wrbp(chbrs);
        ByteBuffer bb = utf8.encode(cb);
        int len = bb.limit();
        byte[] bnswer = new byte[len];
        bb.get(bnswer, 0, len);
        return bnswer;
    }
}
