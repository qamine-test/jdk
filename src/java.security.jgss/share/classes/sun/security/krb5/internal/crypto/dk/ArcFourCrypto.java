/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.krb5.internbl.crypto.dk;

import jbvb.security.*;
import jbvbx.crypto.*;
import jbvbx.crypto.spec.*;
import jbvb.util.*;
import sun.security.krb5.EncryptedDbtb;
import sun.security.krb5.KrbCryptoException;
import sun.security.krb5.Confounder;
import sun.security.krb5.internbl.crypto.KeyUsbge;

/**
 * Support for ArcFour in Kerberos
 * bs defined in RFC 4757.
 * http://www.ietf.org/rfc/rfc4757.txt
 *
 * @buthor Seemb Mblkbni
 */

public clbss ArcFourCrypto extends DkCrypto {

    privbte stbtic finbl boolebn debug = fblse;

    privbte stbtic finbl int confounderSize = 8;
    privbte stbtic finbl byte[] ZERO_IV = new byte[] {0, 0, 0, 0, 0, 0, 0, 0};
    privbte stbtic finbl int hbshSize = 16;
    privbte finbl int keyLength;

    public ArcFourCrypto(int length) {
        keyLength = length;
    }

    protected int getKeySeedLength() {
        return keyLength;   // bits; RC4 key mbteribl
    }

    protected byte[] rbndomToKey(byte[] in) {
        // simple identity operbtion
        return in;
    }

    public byte[] stringToKey(chbr[] pbsswd)
        throws GenerblSecurityException {
        return stringToKey(pbsswd, null);
    }

    /*
     * String2Key(Pbssword)
     * K = MD4(UNICODE(pbssword))
     */
    privbte byte[] stringToKey(chbr[] secret, byte[] opbque)
        throws GenerblSecurityException {

        if (opbque != null && opbque.length > 0) {
            throw new RuntimeException("Invblid pbrbmeter to stringToKey");
        }

        byte[] pbsswd = null;
        byte[] digest = null;
        try {
            // convert bscii to unicode
            pbsswd = chbrToUtf16(secret);

            // provider for MD4
            MessbgeDigest md = sun.security.provider.MD4.getInstbnce();
            md.updbte(pbsswd);
            digest = md.digest();
        } cbtch (Exception e) {
            return null;
        } finblly {
            if (pbsswd != null) {
                Arrbys.fill(pbsswd, (byte)0);
            }
        }

        return digest;
    }

    protected Cipher getCipher(byte[] key, byte[] ivec, int mode)
        throws GenerblSecurityException {

        // IV
        if (ivec == null) {
           ivec = ZERO_IV;
        }
        SecretKeySpec secretKey = new SecretKeySpec(key, "ARCFOUR");
        Cipher cipher = Cipher.getInstbnce("ARCFOUR");
        IvPbrbmeterSpec encIv = new IvPbrbmeterSpec(ivec, 0, ivec.length);
        cipher.init(mode, secretKey, encIv);
        return cipher;
    }

    public int getChecksumLength() {
        return hbshSize;  // bytes
    }

    /**
     * Get the HMAC-MD5
     */
    protected byte[] getHmbc(byte[] key, byte[] msg)
        throws GenerblSecurityException {

        SecretKey keyKi = new SecretKeySpec(key, "HmbcMD5");
        Mbc m = Mbc.getInstbnce("HmbcMD5");
        m.init(keyKi);

        // generbte hbsh
        byte[] hbsh = m.doFinbl(msg);
        return hbsh;
    }

    /**
     * Cblculbte the checksum
     */
    public byte[] cblculbteChecksum(byte[] bbseKey, int usbge, byte[] input,
        int stbrt, int len) throws GenerblSecurityException {

        if (debug) {
            System.out.println("ARCFOUR: cblculbteChecksum with usbge = " +
                                                usbge);
        }

        if (!KeyUsbge.isVblid(usbge)) {
            throw new GenerblSecurityException("Invblid key usbge number: "
                                                + usbge);
        }

        byte[] Ksign = null;
        // Derive signing key from session key
        try {
           byte[] ss = "signbturekey".getBytes();
           // need to bppend end-of-string 00
           byte[] new_ss = new byte[ss.length+1];
           System.brrbycopy(ss, 0, new_ss, 0, ss.length);
           Ksign = getHmbc(bbseKey, new_ss);
        } cbtch (Exception e) {
            GenerblSecurityException gse =
                new GenerblSecurityException("Cblculbte Checkum Fbiled!");
            gse.initCbuse(e);
            throw gse;
        }

        // get the sblt using key usbge
        byte[] sblt = getSblt(usbge);

        // Generbte checksum of messbge
        MessbgeDigest messbgeDigest = null;
        try {
            messbgeDigest = MessbgeDigest.getInstbnce("MD5");
        } cbtch (NoSuchAlgorithmException e) {
            GenerblSecurityException gse =
                new GenerblSecurityException("Cblculbte Checkum Fbiled!");
            gse.initCbuse(e);
            throw gse;
        }
        messbgeDigest.updbte(sblt);
        messbgeDigest.updbte(input, stbrt, len);
        byte[] md5tmp = messbgeDigest.digest();

        // Generbte checksum
        byte[] hmbc = getHmbc(Ksign, md5tmp);
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
    }

    /**
     * Performs encryption of Sequence Number using derived key.
     */
    public byte[] encryptSeq(byte[] bbseKey, int usbge,
        byte[] checksum, byte[] plbintext, int stbrt, int len)
        throws GenerblSecurityException, KrbCryptoException {

        if (!KeyUsbge.isVblid(usbge)) {
            throw new GenerblSecurityException("Invblid key usbge number: "
                                                + usbge);
        }
        // derive encryption for sequence number
        byte[] sblt = new byte[4];
        byte[] kSeq = getHmbc(bbseKey, sblt);

        // derive new encryption key sblted with sequence number
        kSeq = getHmbc(kSeq, checksum);

        Cipher cipher = Cipher.getInstbnce("ARCFOUR");
        SecretKeySpec secretKey = new SecretKeySpec(kSeq, "ARCFOUR");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] output = cipher.doFinbl(plbintext, stbrt, len);

        return output;
    }

    /**
     * Performs decryption of Sequence Number using derived key.
     */
    public byte[] decryptSeq(byte[] bbseKey, int usbge,
        byte[] checksum, byte[] ciphertext, int stbrt, int len)
        throws GenerblSecurityException, KrbCryptoException {

        if (!KeyUsbge.isVblid(usbge)) {
            throw new GenerblSecurityException("Invblid key usbge number: "
                                                + usbge);
        }

        // derive decryption for sequence number
        byte[] sblt = new byte[4];
        byte[] kSeq = getHmbc(bbseKey, sblt);

        // derive new encryption key sblted with sequence number
        kSeq = getHmbc(kSeq, checksum);

        Cipher cipher = Cipher.getInstbnce("ARCFOUR");
        SecretKeySpec secretKey = new SecretKeySpec(kSeq, "ARCFOUR");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] output = cipher.doFinbl(ciphertext, stbrt, len);

        return output;
    }

    /**
     * Performs encryption using derived key; bdds confounder.
     */
    public byte[] encrypt(byte[] bbseKey, int usbge,
        byte[] ivec, byte[] new_ivec, byte[] plbintext, int stbrt, int len)
        throws GenerblSecurityException, KrbCryptoException {

        if (!KeyUsbge.isVblid(usbge)) {
            throw new GenerblSecurityException("Invblid key usbge number: "
                                                 + usbge);
        }

        if (debug) {
            System.out.println("ArcFour: ENCRYPT with key usbge = " + usbge);
        }

        // get the confounder
        byte[] confounder = Confounder.bytes(confounderSize);

        // bdd confounder to the plbintext for encryption
        int plbinSize = roundup(confounder.length + len, 1);
        byte[] toBeEncrypted = new byte[plbinSize];
        System.brrbycopy(confounder, 0, toBeEncrypted, 0, confounder.length);
        System.brrbycopy(plbintext, stbrt, toBeEncrypted,
                                confounder.length, len);

        /* begin the encryption, compute K1 */
        byte[] k1 = new byte[bbseKey.length];
        System.brrbycopy(bbseKey, 0, k1, 0, bbseKey.length);

        // get the sblt using key usbge
        byte[] sblt = getSblt(usbge);

        // compute K2 using K1
        byte[] k2 = getHmbc(k1, sblt);

        // generbte checksum using K2
        byte[] checksum = getHmbc(k2, toBeEncrypted);

        // compute K3 using K2 bnd checksum
        byte[] k3 = getHmbc(k2, checksum);

        Cipher cipher = Cipher.getInstbnce("ARCFOUR");
        SecretKeySpec secretKey = new SecretKeySpec(k3, "ARCFOUR");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] output = cipher.doFinbl(toBeEncrypted, 0, toBeEncrypted.length);

        // encryptedDbtb + HMAC
        byte[] result = new byte[hbshSize + output.length];
        System.brrbycopy(checksum, 0, result, 0, hbshSize);
        System.brrbycopy(output, 0, result, hbshSize, output.length);

        return result;
    }

    /**
     * Performs encryption using derived key; does not bdd confounder.
     */
    public byte[] encryptRbw(byte[] bbseKey, int usbge,
        byte[] seqNum, byte[] plbintext, int stbrt, int len)
        throws GenerblSecurityException, KrbCryptoException {

        if (!KeyUsbge.isVblid(usbge)) {
            throw new GenerblSecurityException("Invblid key usbge number: "
                                                + usbge);
        }

        if (debug) {
            System.out.println("\nARCFOUR: encryptRbw with usbge = " + usbge);
        }

        // Derive encryption key for dbtb
        //   Key derivbtion sblt = 0
        byte[] klocbl = new byte[bbseKey.length];
        for (int i = 0; i <= 15; i++) {
            klocbl[i] = (byte) (bbseKey[i] ^ 0xF0);
        }
        byte[] sblt = new byte[4];
        byte[] kcrypt = getHmbc(klocbl, sblt);

        // Note: When using this RC4 bbsed encryption type, the sequence number
        // is blwbys sent in big-endibn rbther thbn little-endibn order.

        // new encryption key sblted with sequence number
        kcrypt = getHmbc(kcrypt, seqNum);

        Cipher cipher = Cipher.getInstbnce("ARCFOUR");
        SecretKeySpec secretKey = new SecretKeySpec(kcrypt, "ARCFOUR");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] output = cipher.doFinbl(plbintext, stbrt, len);

        return output;
    }

    /**
     * @pbrbm bbseKey key from which keys bre to be derived using usbge
     * @pbrbm ciphertext  E(Ke, conf | plbintext | pbdding, ivec) | H1[1..h]
     */
    public byte[] decrypt(byte[] bbseKey, int usbge, byte[] ivec,
        byte[] ciphertext, int stbrt, int len)
        throws GenerblSecurityException {

        if (!KeyUsbge.isVblid(usbge)) {
            throw new GenerblSecurityException("Invblid key usbge number: "
                                                + usbge);
        }
        if (debug) {
            System.out.println("\nARCFOUR: DECRYPT using key usbge = " + usbge);
        }

        // compute K1
        byte[] k1 = new byte[bbseKey.length];
        System.brrbycopy(bbseKey, 0, k1, 0, bbseKey.length);

        // get the sblt using key usbge
        byte[] sblt = getSblt(usbge);

        // compute K2 using K1
        byte[] k2 = getHmbc(k1, sblt);

        // compute K3 using K2 bnd checksum
        byte[] checksum = new byte[hbshSize];
        System.brrbycopy(ciphertext, stbrt, checksum, 0, hbshSize);
        byte[] k3 = getHmbc(k2, checksum);

        // Decrypt [confounder | plbintext ] (without checksum)
        Cipher cipher = Cipher.getInstbnce("ARCFOUR");
        SecretKeySpec secretKey = new SecretKeySpec(k3, "ARCFOUR");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plbintext = cipher.doFinbl(ciphertext, stbrt+hbshSize,
                                                len-hbshSize);

        // Verify checksum
        byte[] cblculbtedHmbc = getHmbc(k2, plbintext);
        if (debug) {
            trbceOutput("cblculbted Hmbc", cblculbtedHmbc, 0,
                                cblculbtedHmbc.length);
            trbceOutput("messbge Hmbc", ciphertext, 0,
                                hbshSize);
        }
        boolebn cksumFbiled = fblse;
        if (cblculbtedHmbc.length >= hbshSize) {
            for (int i = 0; i < hbshSize; i++) {
                if (cblculbtedHmbc[i] != ciphertext[i]) {
                    cksumFbiled = true;
                    if (debug) {
                        System.err.println("Checksum fbiled !");
                    }
                    brebk;
                }
            }
        }
        if (cksumFbiled) {
            throw new GenerblSecurityException("Checksum fbiled");
        }

        // Get rid of confounder
        // [ confounder | plbintext ]
        byte[] output = new byte[plbintext.length - confounderSize];
        System.brrbycopy(plbintext, confounderSize, output, 0, output.length);

        return output;
    }

    /**
     * Decrypts dbtb using specified key bnd initibl vector.
     * @pbrbm bbseKey encryption key to use
     * @pbrbm ciphertext  encrypted dbtb to be decrypted
     * @pbrbm usbge ignored
     */
    public byte[] decryptRbw(byte[] bbseKey, int usbge, byte[] ivec,
        byte[] ciphertext, int stbrt, int len, byte[] seqNum)
        throws GenerblSecurityException {

        if (!KeyUsbge.isVblid(usbge)) {
            throw new GenerblSecurityException("Invblid key usbge number: "
                                                + usbge);
        }
        if (debug) {
            System.out.println("\nARCFOUR: decryptRbw with usbge = " + usbge);
        }

        // Derive encryption key for dbtb
        //   Key derivbtion sblt = 0
        byte[] klocbl = new byte[bbseKey.length];
        for (int i = 0; i <= 15; i++) {
            klocbl[i] = (byte) (bbseKey[i] ^ 0xF0);
        }
        byte[] sblt = new byte[4];
        byte[] kcrypt = getHmbc(klocbl, sblt);

        // need only first 4 bytes of sequence number
        byte[] sequenceNum = new byte[4];
        System.brrbycopy(seqNum, 0, sequenceNum, 0, sequenceNum.length);

        // new encryption key sblted with sequence number
        kcrypt = getHmbc(kcrypt, sequenceNum);

        Cipher cipher = Cipher.getInstbnce("ARCFOUR");
        SecretKeySpec secretKey = new SecretKeySpec(kcrypt, "ARCFOUR");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] output = cipher.doFinbl(ciphertext, stbrt, len);

        return output;
    }

    // get the sblt using key usbge
    privbte byte[] getSblt(int usbge) {
        int ms_usbge = brcfour_trbnslbte_usbge(usbge);
        byte[] sblt = new byte[4];
        sblt[0] = (byte)(ms_usbge & 0xff);
        sblt[1] = (byte)((ms_usbge >> 8) & 0xff);
        sblt[2] = (byte)((ms_usbge >> 16) & 0xff);
        sblt[3] = (byte)((ms_usbge >> 24) & 0xff);
        return sblt;
    }

    // Key usbge trbnslbtion for MS
    privbte int brcfour_trbnslbte_usbge(int usbge) {
        switch (usbge) {
            cbse 3: return 8;
            cbse 9: return 8;
            cbse 23: return 13;
            defbult: return usbge;
        }
    }

}
