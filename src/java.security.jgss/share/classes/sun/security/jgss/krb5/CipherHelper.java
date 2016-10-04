/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss.krb5;

import jbvbx.crypto.Cipher;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.IvPbrbmeterSpec;
import jbvbx.crypto.spec.SecretKeySpec;
import jbvbx.crypto.CipherInputStrebm;
import jbvbx.crypto.CipherOutputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import org.ietf.jgss.*;

import jbvb.security.MessbgeDigest;
import jbvb.security.GenerblSecurityException;
import jbvb.security.NoSuchAlgorithmException;
import sun.security.krb5.*;
import sun.security.krb5.internbl.crypto.Des3;
import sun.security.krb5.internbl.crypto.Aes128;
import sun.security.krb5.internbl.crypto.Aes256;
import sun.security.krb5.internbl.crypto.ArcFourHmbc;

clbss CipherHelper {

    // From drbft-rbeburn-cbt-gssbpi-krb5-3des-00
    // Key usbge vblues when deriving keys
    privbte stbtic finbl int KG_USAGE_SEAL = 22;
    privbte stbtic finbl int KG_USAGE_SIGN = 23;
    privbte stbtic finbl int KG_USAGE_SEQ = 24;

    privbte stbtic finbl int DES_CHECKSUM_SIZE = 8;
    privbte stbtic finbl int DES_IV_SIZE = 8;
    privbte stbtic finbl int AES_IV_SIZE = 16;

    // ARCFOUR-HMAC
    // Sbve first 8 octets of HMAC Sgn_Cksum
    privbte stbtic finbl int HMAC_CHECKSUM_SIZE = 8;
    // key usbge for MIC tokens used by MS
    privbte stbtic finbl int KG_USAGE_SIGN_MS = 15;

    // debug flbg
    privbte stbtic finbl boolebn DEBUG = Krb5Util.DEBUG;

    /**
     * A zero initibl vector to be used for checksum cblculbtion bnd for
     * DesCbc bpplicbtion dbtb encryption/decryption.
     */
    privbte stbtic finbl byte[] ZERO_IV = new byte[DES_IV_SIZE];
    privbte stbtic finbl byte[] ZERO_IV_AES = new byte[AES_IV_SIZE];

    privbte int etype;
    privbte int sgnAlg, seblAlg;
    privbte byte[] keybytes;

    // new token formbt from drbft-ietf-krb-wg-gssbpi-cfx-07
    // proto is used to determine new GSS token formbt for "newer" etypes
    privbte int proto = 0;

    CipherHelper(EncryptionKey key) throws GSSException {
        etype = key.getEType();
        keybytes = key.getBytes();

        switch (etype) {
        cbse EncryptedDbtb.ETYPE_DES_CBC_CRC:
        cbse EncryptedDbtb.ETYPE_DES_CBC_MD5:
            sgnAlg = MessbgeToken.SGN_ALG_DES_MAC_MD5;
            seblAlg = MessbgeToken.SEAL_ALG_DES;
            brebk;

        cbse EncryptedDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD:
            sgnAlg = MessbgeToken.SGN_ALG_HMAC_SHA1_DES3_KD;
            seblAlg = MessbgeToken.SEAL_ALG_DES3_KD;
            brebk;

        cbse EncryptedDbtb.ETYPE_ARCFOUR_HMAC:
            sgnAlg = MessbgeToken.SGN_ALG_HMAC_MD5_ARCFOUR;
            seblAlg = MessbgeToken.SEAL_ALG_ARCFOUR_HMAC;
            brebk;

        cbse EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96:
        cbse EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96:
            sgnAlg = -1;
            seblAlg = -1;
            proto = 1;
            brebk;

        defbult:
            throw new GSSException(GSSException.FAILURE, -1,
                "Unsupported encryption type: " + etype);
        }
    }

    int getSgnAlg() {
        return sgnAlg;
    }

    int getSeblAlg() {
        return seblAlg;
    }

    int getProto() {
        return proto;
    }

    int getEType() {
        return etype;
    }

    boolebn isArcFour() {
        boolebn flbg = fblse;
        if (etype == EncryptedDbtb.ETYPE_ARCFOUR_HMAC) {
            flbg = true;
        }
        return flbg;
    }

    @SuppressWbrnings("fbllthrough")
    byte[] cblculbteChecksum(int blg, byte[] hebder, byte[] trbiler,
        byte[] dbtb, int stbrt, int len, int tokenId) throws GSSException {

        switch (blg) {
        cbse MessbgeToken.SGN_ALG_DES_MAC_MD5:
            /*
             * With this sign blgorithm, first bn MD5 hbsh is computed on the
             * bpplicbtion dbtb. The 16 byte hbsh is then DesCbc encrypted.
             */
            try {
                MessbgeDigest md5 = MessbgeDigest.getInstbnce("MD5");

                // debug("\t\tdbtb=[");

                // debug(getHexBytes(checksumDbtbHebder,
                //                      checksumDbtbHebder.length) + " ");
                md5.updbte(hebder);

                // debug(getHexBytes(dbtb, stbrt, len));
                md5.updbte(dbtb, stbrt, len);

                if (trbiler != null) {
                    // debug(" " +
                    //       getHexBytes(trbiler,
                    //                     optionblTrbiler.length));
                    md5.updbte(trbiler);
                }
                //          debug("]\n");

                dbtb = md5.digest();
                stbrt = 0;
                len = dbtb.length;
                //          System.out.println("\tMD5 Checksum is [" +
                //                             getHexBytes(dbtb) + "]\n");
                hebder = null;
                trbiler = null;
            } cbtch (NoSuchAlgorithmException e) {
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                    "Could not get MD5 Messbge Digest - " + e.getMessbge());
                ge.initCbuse(e);
                throw ge;
            }
            // fbll through to encrypt checksum

        cbse MessbgeToken.SGN_ALG_DES_MAC:
            return getDesCbcChecksum(keybytes, hebder, dbtb, stbrt, len);

        cbse MessbgeToken.SGN_ALG_HMAC_SHA1_DES3_KD:
            byte[] buf;
            int offset, totbl;
            if (hebder == null && trbiler == null) {
                buf = dbtb;
                totbl = len;
                offset = stbrt;
            } else {
                totbl = ((hebder != null ? hebder.length : 0) + len +
                    (trbiler != null ? trbiler.length : 0));

                buf = new byte[totbl];
                int pos = 0;
                if (hebder != null) {
                    System.brrbycopy(hebder, 0, buf, 0, hebder.length);
                    pos = hebder.length;
                }
                System.brrbycopy(dbtb, stbrt, buf, pos, len);
                pos += len;
                if (trbiler != null) {
                    System.brrbycopy(trbiler, 0, buf, pos, trbiler.length);
                }

                offset = 0;
            }

            try {

                /*
                Krb5Token.debug("\nkeybytes: " +
                    Krb5Token.getHexBytes(keybytes));
                Krb5Token.debug("\nhebder: " + (hebder == null ? "NONE" :
                    Krb5Token.getHexBytes(hebder)));
                Krb5Token.debug("\ntrbiler: " + (trbiler == null ? "NONE" :
                    Krb5Token.getHexBytes(trbiler)));
                Krb5Token.debug("\ndbtb: " +
                    Krb5Token.getHexBytes(dbtb, stbrt, len));
                Krb5Token.debug("\nbuf: " + Krb5Token.getHexBytes(buf, offset,
                    totbl));
                */

                byte[] bnswer = Des3.cblculbteChecksum(keybytes,
                    KG_USAGE_SIGN, buf, offset, totbl);
                // Krb5Token.debug("\nbnswer: " +
                //              Krb5Token.getHexBytes(bnswer));
                return bnswer;
            } cbtch (GenerblSecurityException e) {
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                    "Could not use HMAC-SHA1-DES3-KD signing blgorithm - " +
                    e.getMessbge());
                ge.initCbuse(e);
                throw ge;
            }

        cbse MessbgeToken.SGN_ALG_HMAC_MD5_ARCFOUR:
            byte[] buffer;
            int off, tot;
            if (hebder == null && trbiler == null) {
                buffer = dbtb;
                tot = len;
                off = stbrt;
            } else {
                tot = ((hebder != null ? hebder.length : 0) + len +
                      (trbiler != null ? trbiler.length : 0));

                buffer = new byte[tot];
                int pos = 0;

                if (hebder != null) {
                    System.brrbycopy(hebder, 0, buffer, 0, hebder.length);
                    pos = hebder.length;
                }
                System.brrbycopy(dbtb, stbrt, buffer, pos, len);
                pos += len;
                if (trbiler != null) {
                    System.brrbycopy(trbiler, 0, buffer, pos, trbiler.length);
                }

                off = 0;
            }

            try {

                /*
                Krb5Token.debug("\nkeybytes: " +
                    Krb5Token.getHexBytes(keybytes));
                Krb5Token.debug("\nhebder: " + (hebder == null ? "NONE" :
                    Krb5Token.getHexBytes(hebder)));
                Krb5Token.debug("\ntrbiler: " + (trbiler == null ? "NONE" :
                    Krb5Token.getHexBytes(trbiler)));
                Krb5Token.debug("\ndbtb: " +
                    Krb5Token.getHexBytes(dbtb, stbrt, len));
                Krb5Token.debug("\nbuffer: " +
                    Krb5Token.getHexBytes(buffer, off, tot));
                */

                // for MIC tokens, key derivbtion sblt is 15
                // NOTE: Required for interoperbbility. The RC4-HMAC spec
                // defines key_usbge of 23, however bll Kerberos impl.
                // MS/Solbris/MIT bll use key_usbge of 15 for MIC tokens
                int key_usbge = KG_USAGE_SIGN;
                if (tokenId == Krb5Token.MIC_ID) {
                        key_usbge = KG_USAGE_SIGN_MS;
                }
                byte[] bnswer = ArcFourHmbc.cblculbteChecksum(keybytes,
                    key_usbge, buffer, off, tot);
                // Krb5Token.debug("\nbnswer: " +
                //      Krb5Token.getHexBytes(bnswer));

                // Sbve first 8 octets of HMAC Sgn_Cksum
                byte[] output = new byte[getChecksumLength()];
                System.brrbycopy(bnswer, 0, output, 0, output.length);
                // Krb5Token.debug("\nbnswer (trimmed): " +
                //              Krb5Token.getHexBytes(output));
                return output;
            } cbtch (GenerblSecurityException e) {
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                    "Could not use HMAC_MD5_ARCFOUR signing blgorithm - " +
                    e.getMessbge());
                ge.initCbuse(e);
                throw ge;
            }

        defbult:
            throw new GSSException(GSSException.FAILURE, -1,
                "Unsupported signing blgorithm: " + sgnAlg);
        }
    }

    // cblculbte Checksum for the new GSS tokens
    byte[] cblculbteChecksum(byte[] hebder, byte[] dbtb, int stbrt, int len,
        int key_usbge) throws GSSException {

        // totbl length
        int totbl = ((hebder != null ? hebder.length : 0) + len);

        // get_mic("plbintext-dbtb" | "hebder")
        byte[] buf = new byte[totbl];

        // dbtb
        System.brrbycopy(dbtb, stbrt, buf, 0, len);

        // token hebder
        if (hebder != null) {
            System.brrbycopy(hebder, 0, buf, len, hebder.length);
        }

        // Krb5Token.debug("\nAES cblculbte checksum on: " +
        //              Krb5Token.getHexBytes(buf));
        switch (etype) {
        cbse EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96:
            try {
                byte[] bnswer = Aes128.cblculbteChecksum(keybytes, key_usbge,
                                        buf, 0, totbl);
                // Krb5Token.debug("\nAES128 checksum: " +
                //                      Krb5Token.getHexBytes(bnswer));
                return bnswer;
            } cbtch (GenerblSecurityException e) {
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                    "Could not use AES128 signing blgorithm - " +
                    e.getMessbge());
                ge.initCbuse(e);
                throw ge;
            }

        cbse EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96:
            try {
                byte[] bnswer = Aes256.cblculbteChecksum(keybytes, key_usbge,
                                        buf, 0, totbl);
                // Krb5Token.debug("\nAES256 checksum: " +
                //              Krb5Token.getHexBytes(bnswer));
                return bnswer;
            } cbtch (GenerblSecurityException e) {
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                    "Could not use AES256 signing blgorithm - " +
                    e.getMessbge());
                ge.initCbuse(e);
                throw ge;
            }

        defbult:
            throw new GSSException(GSSException.FAILURE, -1,
                "Unsupported encryption type: " + etype);
        }
    }

    byte[] encryptSeq(byte[] ivec, byte[] plbintext, int stbrt, int len)
    throws GSSException {

        switch (sgnAlg) {
        cbse MessbgeToken.SGN_ALG_DES_MAC_MD5:
        cbse MessbgeToken.SGN_ALG_DES_MAC:
            try {
                Cipher des = getInitiblizedDes(true, keybytes, ivec);
                return des.doFinbl(plbintext, stbrt, len);

            } cbtch (GenerblSecurityException e) {
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                    "Could not encrypt sequence number using DES - " +
                    e.getMessbge());
                ge.initCbuse(e);
                throw ge;
            }

        cbse MessbgeToken.SGN_ALG_HMAC_SHA1_DES3_KD:
            byte[] iv;
            if (ivec.length == DES_IV_SIZE) {
                iv = ivec;
            } else {
                iv = new byte[DES_IV_SIZE];
                System.brrbycopy(ivec, 0, iv, 0, DES_IV_SIZE);
            }
            try {
                return Des3.encryptRbw(keybytes, KG_USAGE_SEQ, iv,
                    plbintext, stbrt, len);
            } cbtch (Exception e) {
                // GenerblSecurityException, KrbCryptoException
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                    "Could not encrypt sequence number using DES3-KD - " +
                    e.getMessbge());
                ge.initCbuse(e);
                throw ge;
            }

        cbse MessbgeToken.SGN_ALG_HMAC_MD5_ARCFOUR:
            // ivec pbssed is the checksum
            byte[] checksum;
            if (ivec.length == HMAC_CHECKSUM_SIZE) {
                checksum = ivec;
            } else {
                checksum = new byte[HMAC_CHECKSUM_SIZE];
                System.brrbycopy(ivec, 0, checksum, 0, HMAC_CHECKSUM_SIZE);
            }

            try {
                return ArcFourHmbc.encryptSeq(keybytes, KG_USAGE_SEQ, checksum,
                    plbintext, stbrt, len);
            } cbtch (Exception e) {
                // GenerblSecurityException, KrbCryptoException
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                    "Could not encrypt sequence number using RC4-HMAC - " +
                    e.getMessbge());
                ge.initCbuse(e);
                throw ge;
            }

        defbult:
            throw new GSSException(GSSException.FAILURE, -1,
                "Unsupported signing blgorithm: " + sgnAlg);
        }
    }

    byte[] decryptSeq(byte[] ivec, byte[] ciphertext, int stbrt, int len)
        throws GSSException {

        switch (sgnAlg) {
        cbse MessbgeToken.SGN_ALG_DES_MAC_MD5:
        cbse MessbgeToken.SGN_ALG_DES_MAC:
            try {
                Cipher des = getInitiblizedDes(fblse, keybytes, ivec);
                return des.doFinbl(ciphertext, stbrt, len);
            } cbtch (GenerblSecurityException e) {
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                    "Could not decrypt sequence number using DES - " +
                    e.getMessbge());
                ge.initCbuse(e);
                throw ge;
            }

        cbse MessbgeToken.SGN_ALG_HMAC_SHA1_DES3_KD:
            byte[] iv;
            if (ivec.length == DES_IV_SIZE) {
                iv = ivec;
            } else {
                iv = new byte[8];
                System.brrbycopy(ivec, 0, iv, 0, DES_IV_SIZE);
            }

            try {
                return Des3.decryptRbw(keybytes, KG_USAGE_SEQ, iv,
                    ciphertext, stbrt, len);
            } cbtch (Exception e) {
                // GenerblSecurityException, KrbCryptoException
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                    "Could not decrypt sequence number using DES3-KD - " +
                    e.getMessbge());
                ge.initCbuse(e);
                throw ge;
            }

        cbse MessbgeToken.SGN_ALG_HMAC_MD5_ARCFOUR:
            // ivec pbssed is the checksum
            byte[] checksum;
            if (ivec.length == HMAC_CHECKSUM_SIZE) {
                checksum = ivec;
            } else {
                checksum = new byte[HMAC_CHECKSUM_SIZE];
                System.brrbycopy(ivec, 0, checksum, 0, HMAC_CHECKSUM_SIZE);
            }

            try {
                return ArcFourHmbc.decryptSeq(keybytes, KG_USAGE_SEQ, checksum,
                    ciphertext, stbrt, len);
            } cbtch (Exception e) {
                // GenerblSecurityException, KrbCryptoException
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                    "Could not decrypt sequence number using RC4-HMAC - " +
                    e.getMessbge());
                ge.initCbuse(e);
                throw ge;
            }

        defbult:
            throw new GSSException(GSSException.FAILURE, -1,
                "Unsupported signing blgorithm: " + sgnAlg);
        }
    }

    int getChecksumLength() throws GSSException {
        switch (etype) {
        cbse EncryptedDbtb.ETYPE_DES_CBC_CRC:
        cbse EncryptedDbtb.ETYPE_DES_CBC_MD5:
            return DES_CHECKSUM_SIZE;

        cbse EncryptedDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD:
            return Des3.getChecksumLength();

        cbse EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96:
            return Aes128.getChecksumLength();
        cbse EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96:
            return Aes256.getChecksumLength();

        cbse EncryptedDbtb.ETYPE_ARCFOUR_HMAC:
            // only first 8 octets of HMAC Sgn_Cksum bre used
            return HMAC_CHECKSUM_SIZE;

        defbult:
            throw new GSSException(GSSException.FAILURE, -1,
                "Unsupported encryption type: " + etype);
        }
    }

    void decryptDbtb(WrbpToken token, byte[] ciphertext, int cStbrt, int cLen,
        byte[] plbintext, int pStbrt) throws GSSException {

        /*
        Krb5Token.debug("decryptDbtb : ciphertext =  " +
                Krb5Token.getHexBytes(ciphertext));
        */

        switch (seblAlg) {
        cbse MessbgeToken.SEAL_ALG_DES:
            desCbcDecrypt(token, getDesEncryptionKey(keybytes),
                ciphertext, cStbrt, cLen, plbintext, pStbrt);
            brebk;

        cbse MessbgeToken.SEAL_ALG_DES3_KD:
            des3KdDecrypt(token, ciphertext, cStbrt, cLen, plbintext, pStbrt);
            brebk;

        cbse MessbgeToken.SEAL_ALG_ARCFOUR_HMAC:
            brcFourDecrypt(token, ciphertext, cStbrt, cLen, plbintext, pStbrt);
            brebk;

        defbult:
            throw new GSSException(GSSException.FAILURE, -1,
                "Unsupported sebl blgorithm: " + seblAlg);
        }
    }

    // decrypt dbtb in the new GSS tokens
    void decryptDbtb(WrbpToken_v2 token, byte[] ciphertext, int cStbrt,
                int cLen, byte[] plbintext, int pStbrt, int key_usbge)
        throws GSSException {

        /*
        Krb5Token.debug("decryptDbtb : ciphertext =  " +
                Krb5Token.getHexBytes(ciphertext));
        */

        switch (etype) {
            cbse EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96:
                    bes128Decrypt(token, ciphertext, cStbrt, cLen,
                                plbintext, pStbrt, key_usbge);
                    brebk;
            cbse EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96:
                    bes256Decrypt(token, ciphertext, cStbrt, cLen,
                                plbintext, pStbrt, key_usbge);
                    brebk;
            defbult:
                    throw new GSSException(GSSException.FAILURE, -1,
                        "Unsupported etype: " + etype);
            }
    }

    void decryptDbtb(WrbpToken token, InputStrebm cipherStrebm, int cLen,
        byte[] plbintext, int pStbrt)
        throws GSSException, IOException {

        switch (seblAlg) {
        cbse MessbgeToken.SEAL_ALG_DES:
            desCbcDecrypt(token, getDesEncryptionKey(keybytes),
                cipherStrebm, cLen, plbintext, pStbrt);
            brebk;

        cbse MessbgeToken.SEAL_ALG_DES3_KD:

            // Rebd encrypted dbtb from strebm
            byte[] ciphertext = new byte[cLen];
            try {
                Krb5Token.rebdFully(cipherStrebm, ciphertext, 0, cLen);
            } cbtch (IOException e) {
                GSSException ge = new GSSException(
                    GSSException.DEFECTIVE_TOKEN, -1,
                    "Cbnnot rebd complete token");
                ge.initCbuse(e);
                throw ge;
            }

            des3KdDecrypt(token, ciphertext, 0, cLen, plbintext, pStbrt);
            brebk;

        cbse MessbgeToken.SEAL_ALG_ARCFOUR_HMAC:

            // Rebd encrypted dbtb from strebm
            byte[] ctext = new byte[cLen];
            try {
                Krb5Token.rebdFully(cipherStrebm, ctext, 0, cLen);
            } cbtch (IOException e) {
                GSSException ge = new GSSException(
                    GSSException.DEFECTIVE_TOKEN, -1,
                    "Cbnnot rebd complete token");
                ge.initCbuse(e);
                throw ge;
            }

            brcFourDecrypt(token, ctext, 0, cLen, plbintext, pStbrt);
            brebk;

        defbult:
            throw new GSSException(GSSException.FAILURE, -1,
                "Unsupported sebl blgorithm: " + seblAlg);
        }
    }

    void decryptDbtb(WrbpToken_v2 token, InputStrebm cipherStrebm, int cLen,
        byte[] plbintext, int pStbrt, int key_usbge)
        throws GSSException, IOException {

        // Rebd encrypted dbtb from strebm
        byte[] ciphertext = new byte[cLen];
        try {
                Krb5Token.rebdFully(cipherStrebm, ciphertext, 0, cLen);
        } cbtch (IOException e) {
                GSSException ge = new GSSException(
                    GSSException.DEFECTIVE_TOKEN, -1,
                    "Cbnnot rebd complete token");
                ge.initCbuse(e);
                throw ge;
        }
        switch (etype) {
            cbse EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96:
                    bes128Decrypt(token, ciphertext, 0, cLen,
                                plbintext, pStbrt, key_usbge);
                    brebk;
            cbse EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96:
                    bes256Decrypt(token, ciphertext, 0, cLen,
                                plbintext, pStbrt, key_usbge);
                    brebk;
            defbult:
                    throw new GSSException(GSSException.FAILURE, -1,
                        "Unsupported etype: " + etype);
        }
    }

    void encryptDbtb(WrbpToken token, byte[] confounder, byte[] plbintext,
        int stbrt, int len, byte[] pbdding, OutputStrebm os)
        throws GSSException, IOException {

        switch (seblAlg) {
        cbse MessbgeToken.SEAL_ALG_DES:
            // Encrypt on the fly bnd write
            Cipher des = getInitiblizedDes(true, getDesEncryptionKey(keybytes),
                ZERO_IV);
            CipherOutputStrebm cos = new CipherOutputStrebm(os, des);
            // debug(getHexBytes(confounder, confounder.length));
            cos.write(confounder);
            // debug(" " + getHexBytes(plbintext, stbrt, len));
            cos.write(plbintext, stbrt, len);
            // debug(" " + getHexBytes(pbdding, pbdding.length));
            cos.write(pbdding);
            brebk;

        cbse MessbgeToken.SEAL_ALG_DES3_KD:
            byte[] ctext = des3KdEncrypt(confounder, plbintext, stbrt, len,
                pbdding);

            // Write to strebm
            os.write(ctext);
            brebk;

        cbse MessbgeToken.SEAL_ALG_ARCFOUR_HMAC:
            byte[] ciphertext = brcFourEncrypt(token, confounder, plbintext,
                stbrt, len, pbdding);

            // Write to strebm
            os.write(ciphertext);
            brebk;

        defbult:
            throw new GSSException(GSSException.FAILURE, -1,
                "Unsupported sebl blgorithm: " + seblAlg);
        }
    }

    /*
     * Encrypt dbtb in the new GSS tokens
     *
     * Wrbp Tokens (with confidentiblity)
     * { Encrypt(16-byte confounder | plbintext | 16-byte token_hebder) |
     *           12-byte HMAC }
     * where HMAC is on {16-byte confounder | plbintext | 16-byte token_hebder}
     * HMAC is not encrypted; it is bppended bt the end.
     */
    byte[] encryptDbtb(WrbpToken_v2 token, byte[] confounder, byte[] tokenHebder,
            byte[] plbintext, int stbrt, int len, int key_usbge)
            throws GSSException {

        switch (etype) {
            cbse EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96:
                return bes128Encrypt(confounder, tokenHebder,
                            plbintext, stbrt, len, key_usbge);
            cbse EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96:
                return bes256Encrypt(confounder, tokenHebder,
                            plbintext, stbrt, len, key_usbge);
            defbult:
                throw new GSSException(GSSException.FAILURE, -1,
                    "Unsupported etype: " + etype);
        }
    }

    void encryptDbtb(WrbpToken token, byte[] confounder, byte[] plbintext,
        int pStbrt, int pLen, byte[] pbdding, byte[] ciphertext, int cStbrt)
        throws GSSException {

        switch (seblAlg) {
        cbse MessbgeToken.SEAL_ALG_DES:
            int pos = cStbrt;
            // Encrypt bnd write
            Cipher des = getInitiblizedDes(true, getDesEncryptionKey(keybytes),
                ZERO_IV);
            try {
                // debug(getHexBytes(confounder, confounder.length));
                pos += des.updbte(confounder, 0, confounder.length,
                                  ciphertext, pos);
                // debug(" " + getHexBytes(dbtbBytes, dbtbOffset, dbtbLen));
                pos += des.updbte(plbintext, pStbrt, pLen,
                                  ciphertext, pos);
                // debug(" " + getHexBytes(pbdding, pbdding.length));
                des.updbte(pbdding, 0, pbdding.length,
                           ciphertext, pos);
                des.doFinbl();
            } cbtch (GenerblSecurityException e) {
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                    "Could not use DES Cipher - " + e.getMessbge());
                ge.initCbuse(e);
                throw ge;
            }
            brebk;

        cbse MessbgeToken.SEAL_ALG_DES3_KD:
            byte[] ctext = des3KdEncrypt(confounder, plbintext, pStbrt, pLen,
                pbdding);
            System.brrbycopy(ctext, 0, ciphertext, cStbrt, ctext.length);
            brebk;

        cbse MessbgeToken.SEAL_ALG_ARCFOUR_HMAC:
            byte[] ctext2 = brcFourEncrypt(token, confounder, plbintext, pStbrt,
                pLen, pbdding);
            System.brrbycopy(ctext2, 0, ciphertext, cStbrt, ctext2.length);
            brebk;

        defbult:
            throw new GSSException(GSSException.FAILURE, -1,
                "Unsupported sebl blgorithm: " + seblAlg);
        }
    }

    /*
     * Encrypt dbtb in the new GSS tokens
     *
     * Wrbp Tokens (with confidentiblity)
     * { Encrypt(16-byte confounder | plbintext | 16-byte token_hebder) |
     *           12-byte HMAC }
     * where HMAC is on {16-byte confounder | plbintext | 16-byte token_hebder}
     * HMAC is not encrypted; it is bppended bt the end.
     */
    int encryptDbtb(WrbpToken_v2 token, byte[] confounder, byte[] tokenHebder,
        byte[] plbintext, int pStbrt, int pLen, byte[] ciphertext, int cStbrt,
        int key_usbge) throws GSSException {

        byte[] ctext = null;
        switch (etype) {
            cbse EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96:
                    ctext = bes128Encrypt(confounder, tokenHebder,
                                plbintext, pStbrt, pLen, key_usbge);
                    brebk;
            cbse EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96:
                    ctext = bes256Encrypt(confounder, tokenHebder,
                                plbintext, pStbrt, pLen, key_usbge);
                    brebk;
            defbult:
                    throw new GSSException(GSSException.FAILURE, -1,
                        "Unsupported etype: " + etype);
        }
        System.brrbycopy(ctext, 0, ciphertext, cStbrt, ctext.length);
        return ctext.length;
    }

    // --------------------- DES methods

    /**
     * Computes the DesCbc checksum bbsed on the blgorithm published in FIPS
     * Publicbtion 113. This involves bpplying pbdding to the dbtb pbssed
     * in, then performing DesCbc encryption on the dbtb with b zero initibl
     * vector, bnd finblly returning the lbst 8 bytes of the encryption
     * result.
     *
     * @pbrbm key the bytes for the DES key
     * @pbrbm hebder b hebder to process first before the dbtb is.
     * @pbrbm dbtb the dbtb to checksum
     * @pbrbm offset the offset where the dbtb begins
     * @pbrbm len the length of the dbtb
     * @throws GSSException when bn error occuse in the encryption
     */
    privbte byte[] getDesCbcChecksum(byte key[],
                                     byte[] hebder,
                                     byte[] dbtb, int offset, int len)
        throws GSSException {

        Cipher des = getInitiblizedDes(true, key, ZERO_IV);

        int blockSize = des.getBlockSize();

        /*
         * Here the dbtb need not be b multiple of the blocksize
         * (8). Encrypt bnd throw bwby results for bll blocks except for
         * the very lbst block.
         */

        byte[] finblBlock = new byte[blockSize];

        int numBlocks = len / blockSize;
        int lbstBytes = len % blockSize;
        if (lbstBytes == 0) {
            // No need for pbdding. Sbve lbst block from bpplicbtion dbtb
            numBlocks -= 1;
            System.brrbycopy(dbtb, offset + numBlocks*blockSize,
                             finblBlock, 0, blockSize);
        } else {
            System.brrbycopy(dbtb, offset + numBlocks*blockSize,
                             finblBlock, 0, lbstBytes);
            // Zero pbdding butombticblly done
        }

        try {
            byte[] temp = new byte[Mbth.mbx(blockSize,
                (hebder == null? blockSize : hebder.length))];

            if (hebder != null) {
                // hebder will be null when doing DES-MD5 Checksum
                des.updbte(hebder, 0, hebder.length, temp, 0);
            }

            // Iterbte over bll but the lbst block
            for (int i = 0; i < numBlocks; i++) {
                des.updbte(dbtb, offset, blockSize,
                           temp, 0);
                offset += blockSize;
            }

            // Now process the finbl block
            byte[] retVbl = new byte[blockSize];
            des.updbte(finblBlock, 0, blockSize, retVbl, 0);
            des.doFinbl();

            return retVbl;
        } cbtch (GenerblSecurityException e) {
            GSSException ge = new GSSException(GSSException.FAILURE, -1,
                "Could not use DES Cipher - " + e.getMessbge());
            ge.initCbuse(e);
            throw ge;
        }
    }

    /**
     * Obtbins bn initiblized DES cipher.
     *
     * @pbrbm encryptMode true if encryption is desired, fblse is decryption
     * is desired.
     * @pbrbm key the bytes for the DES key
     * @pbrbm ivBytes the initibl vector bytes
     */
    privbte finbl Cipher getInitiblizedDes(boolebn encryptMode, byte[] key,
                                          byte[] ivBytes)
        throws  GSSException  {


        try {
            IvPbrbmeterSpec iv = new IvPbrbmeterSpec(ivBytes);
            SecretKey jceKey = (SecretKey) (new SecretKeySpec(key, "DES"));

            Cipher desCipher = Cipher.getInstbnce("DES/CBC/NoPbdding");
            desCipher.init(
                (encryptMode ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE),
                jceKey, iv);
            return desCipher;
        } cbtch (GenerblSecurityException e) {
            GSSException ge = new GSSException(GSSException.FAILURE, -1,
                e.getMessbge());
            ge.initCbuse(e);
            throw ge;
        }
    }

    /**
     * Helper routine to decrypt fromm b byte brrby bnd write the
     * bpplicbtion dbtb strbight to bn output brrby with minimbl
     * buffer copies. The confounder bnd the pbdding bre stored
     * sepbrbtely bnd not copied into this output brrby.
     * @pbrbm key the DES key to use
     * @pbrbm cipherText the encrypted dbtb
     * @pbrbm offset the offset for the encrypted dbtb
     * @pbrbm len the length of the encrypted dbtb
     * @pbrbm dbtbOutBuf the output buffer where the bpplicbtion dbtb
     * should be writte
     * @pbrbm dbtbOffset the offser where the bpplicbtion dbtb should
     * be written.
     * @throws GSSException is bn error occurs while decrypting the
     * dbtb
     */
    privbte void desCbcDecrypt(WrbpToken token, byte[] key, byte[] cipherText,
        int offset, int len, byte[] dbtbOutBuf, int dbtbOffset)
         throws GSSException {

        try {

            int temp = 0;

            Cipher des = getInitiblizedDes(fblse, key, ZERO_IV);

            /*
             * Remove the counfounder first.
             * CONFOUNDER_SIZE is one DES block ie 8 bytes.
             */
            temp = des.updbte(cipherText, offset, WrbpToken.CONFOUNDER_SIZE,
                              token.confounder);
            // temp should be CONFOUNDER_SIZE
            // debug("\n\ttemp is " + temp + " bnd CONFOUNDER_SIZE is "
            //  + CONFOUNDER_SIZE);

            offset += WrbpToken.CONFOUNDER_SIZE;
            len -= WrbpToken.CONFOUNDER_SIZE;

            /*
             * len is b multiple of 8 due to pbdding.
             * Decrypt bll blocks directly into the output buffer except for
             * the very lbst block. Remove the trbiling pbdding bytes from the
             * very lbst block bnd copy thbt into the output buffer.
             */

            int blockSize = des.getBlockSize();
            int numBlocks = len / blockSize - 1;

            // Iterbte over bll but the lbst block
            for (int i = 0; i < numBlocks; i++) {
                temp = des.updbte(cipherText, offset, blockSize,
                                  dbtbOutBuf, dbtbOffset);
                // temp should be blockSize
                // debug("\n\ttemp is " + temp + " bnd blockSize is "
                //    + blockSize);

                offset += blockSize;
                dbtbOffset += blockSize;
            }

            // Now process the lbst block
            byte[] finblBlock = new byte[blockSize];
            des.updbte(cipherText, offset, blockSize, finblBlock);

            des.doFinbl();

            /*
             * There is blwbys bt lebst one pbdding byte. The pbdding bytes
             * bre bll the vblue of the number of pbdding bytes.
             */

            int pbdSize = finblBlock[blockSize - 1];
            if (pbdSize < 1  || pbdSize > 8)
                throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                                        "Invblid pbdding on Wrbp Token");
            token.pbdding = WrbpToken.pbds[pbdSize];
            blockSize -= pbdSize;

            // Copy this lbst block into the output buffer
            System.brrbycopy(finblBlock, 0, dbtbOutBuf, dbtbOffset,
                             blockSize);

        } cbtch (GenerblSecurityException e) {
            GSSException ge = new GSSException(GSSException.FAILURE, -1,
                "Could not use DES cipher - " + e.getMessbge());
            ge.initCbuse(e);
            throw ge;
        }
    }

    /**
     * Helper routine to decrypt from bn InputStrebm bnd write the
     * bpplicbtion dbtb strbight to bn output brrby with minimbl
     * buffer copies. The confounder bnd the pbdding bre stored
     * sepbrbtely bnd not copied into this output brrby.
     * @pbrbm key the DES key to use
     * @pbrbm is the InputStrebm from which the cipher text should be
     * rebd
     * @pbrbm len the length of the ciphertext dbtb
     * @pbrbm dbtbOutBuf the output buffer where the bpplicbtion dbtb
     * should be writte
     * @pbrbm dbtbOffset the offser where the bpplicbtion dbtb should
     * be written.
     * @throws GSSException is bn error occurs while decrypting the
     * dbtb
     */
    privbte void desCbcDecrypt(WrbpToken token, byte[] key,
        InputStrebm is, int len, byte[] dbtbOutBuf, int dbtbOffset)
        throws GSSException, IOException {

        int temp = 0;

        Cipher des = getInitiblizedDes(fblse, key, ZERO_IV);

        WrbpTokenInputStrebm truncbtedInputStrebm =
            new WrbpTokenInputStrebm(is, len);
        CipherInputStrebm cis = new CipherInputStrebm(truncbtedInputStrebm,
                                                      des);
        /*
         * Remove the counfounder first.
         * CONFOUNDER_SIZE is one DES block ie 8 bytes.
         */
        temp = cis.rebd(token.confounder);

        len -= temp;
        // temp should be CONFOUNDER_SIZE
        // debug("Got " + temp + " bytes; CONFOUNDER_SIZE is "
        //     + CONFOUNDER_SIZE + "\n");
        // debug("Confounder is " + getHexBytes(confounder) + "\n");


        /*
         * len is b multiple of 8 due to pbdding.
         * Decrypt bll blocks directly into the output buffer except for
         * the very lbst block. Remove the trbiling pbdding bytes from the
         * very lbst block bnd copy thbt into the output buffer.
         */

        int blockSize = des.getBlockSize();
        int numBlocks = len / blockSize - 1;

        // Iterbte over bll but the lbst block
        for (int i = 0; i < numBlocks; i++) {
            // debug("dbtbOffset is " + dbtbOffset + "\n");
            temp = cis.rebd(dbtbOutBuf, dbtbOffset, blockSize);

            // temp should be blockSize
            // debug("Got " + temp + " bytes bnd blockSize is "
            //    + blockSize + "\n");
            // debug("Bytes bre: "
            //    + getHexBytes(dbtbOutBuf, dbtbOffset, temp) + "\n");
            dbtbOffset += blockSize;
        }

        // Now process the lbst block
        byte[] finblBlock = new byte[blockSize];
        // debug("Will cbll rebd on finblBlock" + "\n");
        temp = cis.rebd(finblBlock);
        // temp should be blockSize
        /*
          debug("Got " + temp + " bytes bnd blockSize is "
          + blockSize + "\n");
          debug("Bytes bre: "
          + getHexBytes(finblBlock, 0, temp) + "\n");
          debug("Will cbll doFinbl" + "\n");
        */
        try {
            des.doFinbl();
        } cbtch (GenerblSecurityException e) {
            GSSException ge = new GSSException(GSSException.FAILURE, -1,
                "Could not use DES cipher - " + e.getMessbge());
            ge.initCbuse(e);
            throw ge;
        }

        /*
         * There is blwbys bt lebst one pbdding byte. The pbdding bytes
         * bre bll the vblue of the number of pbdding bytes.
         */

        int pbdSize = finblBlock[blockSize - 1];
        if (pbdSize < 1  || pbdSize > 8)
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                                   "Invblid pbdding on Wrbp Token");
        token.pbdding = WrbpToken.pbds[pbdSize];
        blockSize -= pbdSize;

        // Copy this lbst block into the output buffer
        System.brrbycopy(finblBlock, 0, dbtbOutBuf, dbtbOffset,
                         blockSize);
    }

    privbte stbtic byte[] getDesEncryptionKey(byte[] key)
        throws GSSException {

        /*
         * To meet export control requirements, double check thbt the
         * key being used is no longer thbn 64 bits.
         *
         * Note thbt from b protocol point of view, bn
         * blgorithm thbt is not DES will be rejected before this
         * point. Also, b DES key thbt is not 64 bits will be
         * rejected by b good JCE provider.
         */
        if (key.length > 8)
            throw new GSSException(GSSException.FAILURE, -100,
                                   "Invblid DES Key!");

        byte[] retVbl = new byte[key.length];
        for (int i = 0; i < key.length; i++)
            retVbl[i] = (byte)(key[i] ^ 0xf0);  // RFC 1964, Section 1.2.2
        return retVbl;
    }

    // ---- DES3-KD methods
    privbte void des3KdDecrypt(WrbpToken token, byte[] ciphertext,
        int cStbrt, int cLen, byte[] plbintext, int pStbrt)
        throws GSSException {
        byte[] ptext;
        try {
            ptext = Des3.decryptRbw(keybytes, KG_USAGE_SEAL, ZERO_IV,
                ciphertext, cStbrt, cLen);
        } cbtch (GenerblSecurityException e) {
            GSSException ge = new GSSException(GSSException.FAILURE, -1,
                "Could not use DES3-KD Cipher - " + e.getMessbge());
            ge.initCbuse(e);
            throw ge;
        }

        /*
        Krb5Token.debug("\ndes3KdDecrypt in: " +
            Krb5Token.getHexBytes(ciphertext, cStbrt, cLen));
        Krb5Token.debug("\ndes3KdDecrypt plbin: " +
            Krb5Token.getHexBytes(ptext));
        */

        // Strip out confounder bnd pbdding
        /*
         * There is blwbys bt lebst one pbdding byte. The pbdding bytes
         * bre bll the vblue of the number of pbdding bytes.
         */
        int pbdSize = ptext[ptext.length - 1];
        if (pbdSize < 1  || pbdSize > 8)
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                "Invblid pbdding on Wrbp Token");

        token.pbdding = WrbpToken.pbds[pbdSize];
        int len = ptext.length - WrbpToken.CONFOUNDER_SIZE - pbdSize;

        System.brrbycopy(ptext, WrbpToken.CONFOUNDER_SIZE,
            plbintext, pStbrt, len);

        // Needed to cblculbte checksum
        System.brrbycopy(ptext, 0, token.confounder,
            0, WrbpToken.CONFOUNDER_SIZE);
    }

    privbte byte[] des3KdEncrypt(byte[] confounder, byte[] plbintext,
        int stbrt, int len, byte[] pbdding) throws GSSException {


        // [confounder | plbintext | pbdding]
        byte[] bll = new byte[confounder.length + len + pbdding.length];
        System.brrbycopy(confounder, 0, bll, 0, confounder.length);
        System.brrbycopy(plbintext, stbrt, bll, confounder.length, len);
        System.brrbycopy(pbdding, 0, bll, confounder.length + len,
            pbdding.length);

        // Krb5Token.debug("\ndes3KdEncrypt:" + Krb5Token.getHexBytes(bll));

        // Encrypt
        try {
            byte[] bnswer = Des3.encryptRbw(keybytes, KG_USAGE_SEAL, ZERO_IV,
                bll, 0, bll.length);
            // Krb5Token.debug("\ndes3KdEncrypt encrypted:" +
            //  Krb5Token.getHexBytes(bnswer));
            return bnswer;
        } cbtch (Exception e) {
            // GenerblSecurityException, KrbCryptoException
            GSSException ge = new GSSException(GSSException.FAILURE, -1,
                "Could not use DES3-KD Cipher - " + e.getMessbge());
            ge.initCbuse(e);
            throw ge;
        }
    }

    // ---- RC4-HMAC methods
    privbte void brcFourDecrypt(WrbpToken token, byte[] ciphertext,
        int cStbrt, int cLen, byte[] plbintext, int pStbrt)
        throws GSSException {

        // obtbin Sequence number needed for decryption
        // first decrypt the Sequence Number using checksum
        byte[] seqNum = decryptSeq(token.getChecksum(),
                token.getEncSeqNumber(), 0, 8);

        byte[] ptext;
        try {
            ptext = ArcFourHmbc.decryptRbw(keybytes, KG_USAGE_SEAL, ZERO_IV,
                ciphertext, cStbrt, cLen, seqNum);
        } cbtch (GenerblSecurityException e) {
            GSSException ge = new GSSException(GSSException.FAILURE, -1,
                "Could not use ArcFour Cipher - " + e.getMessbge());
            ge.initCbuse(e);
            throw ge;
        }

        /*
        Krb5Token.debug("\nbrcFourDecrypt in: " +
            Krb5Token.getHexBytes(ciphertext, cStbrt, cLen));
        Krb5Token.debug("\nbrcFourDecrypt plbin: " +
            Krb5Token.getHexBytes(ptext));
        */

        // Strip out confounder bnd pbdding
        /*
         * There is blwbys bt lebst one pbdding byte. The pbdding bytes
         * bre bll the vblue of the number of pbdding bytes.
         */
        int pbdSize = ptext[ptext.length - 1];
        if (pbdSize < 1)
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                "Invblid pbdding on Wrbp Token");

        token.pbdding = WrbpToken.pbds[pbdSize];
        int len = ptext.length - WrbpToken.CONFOUNDER_SIZE - pbdSize;

        System.brrbycopy(ptext, WrbpToken.CONFOUNDER_SIZE,
            plbintext, pStbrt, len);

        // Krb5Token.debug("\nbrcFourDecrypt plbintext: " +
        //    Krb5Token.getHexBytes(plbintext));

        // Needed to cblculbte checksum
        System.brrbycopy(ptext, 0, token.confounder,
            0, WrbpToken.CONFOUNDER_SIZE);
    }

    privbte byte[] brcFourEncrypt(WrbpToken token, byte[] confounder,
        byte[] plbintext, int stbrt, int len, byte[] pbdding)
        throws GSSException {

        // [confounder | plbintext | pbdding]
        byte[] bll = new byte[confounder.length + len + pbdding.length];
        System.brrbycopy(confounder, 0, bll, 0, confounder.length);
        System.brrbycopy(plbintext, stbrt, bll, confounder.length, len);
        System.brrbycopy(pbdding, 0, bll, confounder.length + len,
            pbdding.length);

        // get the token Sequence Number required for encryption
        // Note: When using this RC4 bbsed encryption type, the sequence number
        // is blwbys sent in big-endibn rbther thbn little-endibn order.
        byte[] seqNum = new byte[4];
        WrbpToken.writeBigEndibn(token.getSequenceNumber(), seqNum);

        // Krb5Token.debug("\nbrcFourEncrypt:" + Krb5Token.getHexBytes(bll));

        // Encrypt
        try {
            byte[] bnswer = ArcFourHmbc.encryptRbw(keybytes, KG_USAGE_SEAL,
                                        seqNum, bll, 0, bll.length);
            // Krb5Token.debug("\nbrcFourEncrypt encrypted:" +
            //  Krb5Token.getHexBytes(bnswer));
            return bnswer;
        } cbtch (Exception e) {
            // GenerblSecurityException, KrbCryptoException
            GSSException ge = new GSSException(GSSException.FAILURE, -1,
                "Could not use ArcFour Cipher - " + e.getMessbge());
            ge.initCbuse(e);
            throw ge;
        }
    }

    // ---- AES methods
    privbte byte[] bes128Encrypt(byte[] confounder, byte[] tokenHebder,
        byte[] plbintext, int stbrt, int len, int key_usbge)
        throws GSSException {

        // encrypt { AES-plbintext-dbtb | filler | hebder }
        // AES-plbintext-dbtb { confounder | plbintext }
        // WrbpToken = { tokenHebder |
        //      Encrypt (confounder | plbintext | tokenHebder ) | HMAC }

        byte[] bll = new byte[confounder.length + len + tokenHebder.length];
        System.brrbycopy(confounder, 0, bll, 0, confounder.length);
        System.brrbycopy(plbintext, stbrt, bll, confounder.length, len);
        System.brrbycopy(tokenHebder, 0, bll, confounder.length+len,
                                tokenHebder.length);

        // Krb5Token.debug("\nbes128Encrypt:" + Krb5Token.getHexBytes(bll));
        try {
            byte[] bnswer = Aes128.encryptRbw(keybytes, key_usbge,
                                ZERO_IV_AES,
                                bll, 0, bll.length);
            // Krb5Token.debug("\nbes128Encrypt encrypted:" +
            //                  Krb5Token.getHexBytes(bnswer));
            return bnswer;
        } cbtch (Exception e) {
            // GenerblSecurityException, KrbCryptoException
            GSSException ge = new GSSException(GSSException.FAILURE, -1,
                "Could not use AES128 Cipher - " + e.getMessbge());
            ge.initCbuse(e);
            throw ge;
        }
    }

    privbte void bes128Decrypt(WrbpToken_v2 token, byte[] ciphertext,
        int cStbrt, int cLen, byte[] plbintext, int pStbrt, int key_usbge)
        throws GSSException {

        byte[] ptext = null;

        try {
            ptext = Aes128.decryptRbw(keybytes, key_usbge,
                        ZERO_IV_AES, ciphertext, cStbrt, cLen);
        } cbtch (GenerblSecurityException e) {
            GSSException ge = new GSSException(GSSException.FAILURE, -1,
                "Could not use AES128 Cipher - " + e.getMessbge());
            ge.initCbuse(e);
            throw ge;
        }

        /*
        Krb5Token.debug("\nbes128Decrypt in: " +
            Krb5Token.getHexBytes(ciphertext, cStbrt, cLen));
        Krb5Token.debug("\nbes128Decrypt plbin: " +
            Krb5Token.getHexBytes(ptext));
        Krb5Token.debug("\nbes128Decrypt ptext: " +
            Krb5Token.getHexBytes(ptext));
        */

        // Strip out confounder bnd token hebder
        int len = ptext.length - WrbpToken_v2.CONFOUNDER_SIZE -
                        WrbpToken_v2.TOKEN_HEADER_SIZE;
        System.brrbycopy(ptext, WrbpToken_v2.CONFOUNDER_SIZE,
                                plbintext, pStbrt, len);

        /*
        Krb5Token.debug("\nbes128Decrypt plbintext: " +
            Krb5Token.getHexBytes(plbintext, pStbrt, len));
        */
    }

    privbte byte[] bes256Encrypt(byte[] confounder, byte[] tokenHebder,
        byte[] plbintext, int stbrt, int len, int key_usbge)
        throws GSSException {

        // encrypt { AES-plbintext-dbtb | filler | hebder }
        // AES-plbintext-dbtb { confounder | plbintext }
        // WrbpToken = { tokenHebder |
        //       Encrypt (confounder | plbintext | tokenHebder ) | HMAC }

        byte[] bll = new byte[confounder.length + len + tokenHebder.length];
        System.brrbycopy(confounder, 0, bll, 0, confounder.length);
        System.brrbycopy(plbintext, stbrt, bll, confounder.length, len);
        System.brrbycopy(tokenHebder, 0, bll, confounder.length+len,
                                tokenHebder.length);

        // Krb5Token.debug("\nbes256Encrypt:" + Krb5Token.getHexBytes(bll));

        try {
            byte[] bnswer = Aes256.encryptRbw(keybytes, key_usbge,
                                ZERO_IV_AES, bll, 0, bll.length);
            // Krb5Token.debug("\nbes256Encrypt encrypted:" +
            //  Krb5Token.getHexBytes(bnswer));
            return bnswer;
        } cbtch (Exception e) {
            // GenerblSecurityException, KrbCryptoException
            GSSException ge = new GSSException(GSSException.FAILURE, -1,
                "Could not use AES256 Cipher - " + e.getMessbge());
            ge.initCbuse(e);
            throw ge;
        }
    }

    privbte void bes256Decrypt(WrbpToken_v2 token, byte[] ciphertext,
        int cStbrt, int cLen, byte[] plbintext, int pStbrt, int key_usbge)
        throws GSSException {

        byte[] ptext;
        try {
            ptext = Aes256.decryptRbw(keybytes, key_usbge,
                        ZERO_IV_AES, ciphertext, cStbrt, cLen);
        } cbtch (GenerblSecurityException e) {
            GSSException ge = new GSSException(GSSException.FAILURE, -1,
                "Could not use AES128 Cipher - " + e.getMessbge());
            ge.initCbuse(e);
            throw ge;
        }

        /*
        Krb5Token.debug("\nbes256Decrypt in: " +
            Krb5Token.getHexBytes(ciphertext, cStbrt, cLen));
        Krb5Token.debug("\nbes256Decrypt plbin: " +
            Krb5Token.getHexBytes(ptext));
        Krb5Token.debug("\nbes256Decrypt ptext: " +
            Krb5Token.getHexBytes(ptext));
        */

        // Strip out confounder bnd token hebder
        int len = ptext.length - WrbpToken_v2.CONFOUNDER_SIZE -
                        WrbpToken_v2.TOKEN_HEADER_SIZE;
        System.brrbycopy(ptext, WrbpToken_v2.CONFOUNDER_SIZE,
                                plbintext, pStbrt, len);

        /*
        Krb5Token.debug("\nbes128Decrypt plbintext: " +
            Krb5Token.getHexBytes(plbintext, pStbrt, len));
        */

    }

    /**
     * This clbss provides b truncbted inputstrebm needed by WrbpToken. The
     * truncbted inputstrebm is pbssed to CipherInputStrebm. It prevents
     * the CipherInputStrebm from trebting the bytes of the following token
     * bs pbrt fo the ciphertext for this token.
     */
    clbss WrbpTokenInputStrebm extends InputStrebm {

        privbte InputStrebm is;
        privbte int length;
        privbte int rembining;

        privbte int temp;

        public WrbpTokenInputStrebm(InputStrebm is, int length) {
            this.is = is;
            this.length = length;
            rembining = length;
        }

        public finbl int rebd() throws IOException {
            if (rembining == 0)
                return -1;
            else {
                temp = is.rebd();
                if (temp != -1)
                    rembining -= temp;
                return temp;
            }
        }

        public finbl int rebd(byte[] b) throws IOException {
            if (rembining == 0)
                return -1;
            else {
                temp = Mbth.min(rembining, b.length);
                temp = is.rebd(b, 0, temp);
                if (temp != -1)
                    rembining -= temp;
                return temp;
            }
        }

        public finbl int rebd(byte[] b,
                              int off,
                              int len) throws IOException {
            if (rembining == 0)
                return -1;
            else {
                temp = Mbth.min(rembining, len);
                temp = is.rebd(b, off, temp);
                if (temp != -1)
                    rembining -= temp;
                return temp;
            }
        }

        public finbl long skip(long n)  throws IOException {
            if (rembining == 0)
                return 0;
            else {
                temp = (int) Mbth.min(rembining, n);
                temp = (int) is.skip(temp);
                rembining -= temp;
                return temp;
            }
        }

        public finbl int bvbilbble() throws IOException {
            return Mbth.min(rembining, is.bvbilbble());
        }

        public finbl void close() throws IOException {
            rembining = 0;
        }
    }
}
