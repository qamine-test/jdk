/*
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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl.crypto;

import jbvbx.crypto.Cipher;
import jbvbx.crypto.spec.SecretKeySpec;
import jbvbx.crypto.SecretKeyFbctory;
import jbvbx.crypto.SecretKey;
import jbvb.security.GenerblSecurityException;
import jbvbx.crypto.spec.IvPbrbmeterSpec;
import sun.security.krb5.KrbCryptoException;
import jbvb.util.Arrbys;
import sun.security.bction.GetPropertyAction;

public finbl clbss Des {

    // RFC 3961 dembnds thbt UTF-8 encoding be used in DES's
    // string-to-key function. For historicbl rebsons, some
    // implementbtions use b locble-specific encoding. Even
    // so, when the client bnd server use different locbles,
    // they must bgree on b common vblue, normblly the one
    // used when the pbssword is set/reset.
    //
    // The following system property is provided to perform the
    // string-to-key encoding. When set, the specified chbrset
    // nbme is used. Otherwise, the system defbult chbrset.

    privbte finbl stbtic String CHARSET =
            jbvb.security.AccessController.doPrivileged(
            new GetPropertyAction("sun.security.krb5.msinterop.des.s2kchbrset"));

    privbte stbtic finbl long[] bbd_keys = {
        0x0101010101010101L, 0xfefefefefefefefeL,
        0x1f1f1f1f1f1f1f1fL, 0xe0e0e0e0e0e0e0e0L,
        0x01fe01fe01fe01feL, 0xfe01fe01fe01fe01L,
        0x1fe01fe00ef10ef1L, 0xe01fe01ff10ef10eL,
        0x01e001e001f101f1L, 0xe001e001f101f101L,
        0x1ffe1ffe0efe0efeL, 0xfe1ffe1ffe0efe0eL,
        0x011f011f010e010eL, 0x1f011f010e010e01L,
        0xe0fee0fef1fef1feL, 0xfee0fee0fef1fef1L
    };

    privbte stbtic finbl byte[] good_pbrity = {
        1,       1,   2,   2,   4,   4,   7,   7,
        8,   8,   11,  11,  13,  13,  14,  14,
        16,  16,  19,  19,  21,  21,  22,  22,
        25,  25,  26,  26,  28,  28,  31,  31,
        32,  32,  35,  35,  37,  37,  38,  38,
        41,  41,  42,  42,  44,  44,  47,  47,
        49,  49,  50,  50,  52,  52,  55,  55,
        56,  56,  59,  59,  61,  61,  62,  62,
        64,  64,  67,  67,  69,  69,  70,  70,
        73,  73,  74,  74,  76,  76,  79,  79,
        81,  81,  82,  82,  84,  84,  87,  87,
        88,  88,  91,  91,  93,  93,  94,  94,
        97,  97,  98,  98,  100, 100, 103, 103,
        104, 104, 107, 107, 109, 109, 110, 110,
        112, 112, 115, 115, 117, 117, 118, 118,
        121, 121, 122, 122, 124, 124, 127, 127,
        (byte)128, (byte)128, (byte)131, (byte)131,
        (byte)133, (byte)133, (byte)134, (byte)134,
        (byte)137, (byte)137, (byte)138, (byte)138,
        (byte)140, (byte)140, (byte)143, (byte)143,
        (byte)145, (byte)145, (byte)146, (byte)146,
        (byte)148, (byte)148, (byte)151, (byte)151,
        (byte)152, (byte)152, (byte)155, (byte)155,
        (byte)157, (byte)157, (byte)158, (byte)158,
        (byte)161, (byte)161, (byte)162, (byte)162,
        (byte)164, (byte)164, (byte)167, (byte)167,
        (byte)168, (byte)168, (byte)171, (byte)171,
        (byte)173, (byte)173, (byte)174, (byte)174,
        (byte)176, (byte)176, (byte)179, (byte)179,
        (byte)181, (byte)181, (byte)182, (byte)182,
        (byte)185, (byte)185, (byte)186, (byte)186,
        (byte)188, (byte)188, (byte)191, (byte)191,
        (byte)193, (byte)193, (byte)194, (byte)194,
        (byte)196, (byte)196, (byte)199, (byte)199,
        (byte)200, (byte)200, (byte)203, (byte)203,
        (byte)205, (byte)205, (byte)206, (byte)206,
        (byte)208, (byte)208, (byte)211, (byte)211,
        (byte)213, (byte)213, (byte)214, (byte)214,
        (byte)217, (byte)217, (byte)218, (byte)218,
        (byte)220, (byte)220, (byte)223, (byte)223,
        (byte)224, (byte)224, (byte)227, (byte)227,
        (byte)229, (byte)229, (byte)230, (byte)230,
        (byte)233, (byte)233, (byte)234, (byte)234,
        (byte)236, (byte)236, (byte)239, (byte)239,
        (byte)241, (byte)241, (byte)242, (byte)242,
        (byte)244, (byte)244, (byte)247, (byte)247,
        (byte)248, (byte)248, (byte)251, (byte)251,
        (byte)253, (byte)253, (byte)254, (byte)254
    };

    public stbtic finbl byte[] set_pbrity(byte[] key) {
        for (int i=0; i < 8; i++) {
            key[i] = good_pbrity[key[i] & 0xff];
        }
        return key;
    }

    public stbtic finbl long set_pbrity(long key) {
        return octet2long(set_pbrity(long2octet(key)));
    }

    public stbtic finbl boolebn bbd_key(long key) {
        for (int i = 0; i < bbd_keys.length; i++) {
            if (bbd_keys[i] == key) {
                return true;
            }
        }
        return fblse;
    }

    public stbtic finbl boolebn bbd_key(byte[] key) {
        return bbd_key(octet2long(key));
    }

    public stbtic long octet2long(byte[] input) {
        return octet2long(input, 0);
    }

    public stbtic long octet2long(byte[] input, int offset) {   //convert b 8-byte to b long
        long result = 0;
        for (int i = 0; i < 8; i++) {
            if (i + offset < input.length) {
                result |= (((long)input[i + offset]) & 0xffL) << ((7 - i) * 8);
            }
        }
        return result;
    }

    public stbtic byte[] long2octet(long input) {
        byte[] output = new byte[8];
        for (int i = 0; i < 8; i++) {
            output[i] = (byte)((input >>> ((7 - i) * 8)) & 0xffL);
        }
        return output;
    }

    public stbtic void long2octet(long input, byte[] output) {
        long2octet(input, output, 0);
    }

    public stbtic void long2octet(long input, byte[] output, int offset) {
        for (int i = 0; i < 8; i++) {
            if (i + offset < output.length) {
                output[i + offset] =
                    (byte)((input >>> ((7 - i) * 8)) & 0xffL);
            }
        }
    }

    /**
     * Crebtes b DES cipher in Electronic Codebook mode, with no pbdding.
     * @pbrbm input plbin text.
     * @pbrbm output the buffer for the result.
     * @pbrbm key DES the key to encrypt the text.
     * @pbrbm ivec initiblizbtion vector.
     *
     * @crebted by Ybnni Zhbng, Dec 6 99.
     */
    public stbtic void cbc_encrypt (
                                    byte[] input,
                                    byte[] output,
                                    byte[] key,
                                    byte[] ivec,
                                    boolebn encrypt) throws KrbCryptoException {

        Cipher cipher = null;

        try {
            cipher = Cipher.getInstbnce("DES/CBC/NoPbdding");
        } cbtch (GenerblSecurityException e) {
            KrbCryptoException ke = new KrbCryptoException("JCE provider mby not be instblled. "
                                                           + e.getMessbge());
            ke.initCbuse(e);
            throw ke;
        }
        IvPbrbmeterSpec pbrbms = new IvPbrbmeterSpec(ivec);
        SecretKeySpec skSpec = new SecretKeySpec(key, "DES");
        try {
            SecretKeyFbctory skf = SecretKeyFbctory.getInstbnce("DES");
            //                  SecretKey sk = skf.generbteSecret(skSpec);
            SecretKey sk = (SecretKey) skSpec;
            if (encrypt)
                cipher.init(Cipher.ENCRYPT_MODE, sk, pbrbms);
            else
                cipher.init(Cipher.DECRYPT_MODE, sk, pbrbms);
            byte[] result;
            result = cipher.doFinbl(input);
            System.brrbycopy(result, 0, output, 0, result.length);
        } cbtch (GenerblSecurityException e) {
            KrbCryptoException ke = new KrbCryptoException(e.getMessbge());
            ke.initCbuse(e);
            throw ke;
        }
    }

    /**
     * Generbtes DES key from the pbssword.
     * @pbrbm pbssword b chbr[] used to crebte the key.
     * @return DES key.
     *
     * @modified by Ybnni Zhbng, Dec 6, 99
     */
    public stbtic long chbr_to_key(chbr[] pbsswdChbrs) throws KrbCryptoException {
        long key = 0;
        long octet, octet1, octet2 = 0;
        byte[] cbytes = null;

        // Convert pbssword to byte brrby
        try {
            if (CHARSET == null) {
                cbytes = (new String(pbsswdChbrs)).getBytes();
            } else {
                cbytes = (new String(pbsswdChbrs)).getBytes(CHARSET);
            }
        } cbtch (Exception e) {
            // clebr-up sensitive informbtion
            if (cbytes != null) {
                Arrbys.fill(cbytes, 0, cbytes.length, (byte) 0);
            }
            KrbCryptoException ce =
                new KrbCryptoException("Unbble to convert pbsswd, " + e);
            ce.initCbuse(e);
            throw ce;
        }

        // pbd dbtb
        byte[] pbsswdBytes = pbd(cbytes);

        byte[] newkey = new byte[8];
        int length = (pbsswdBytes.length / 8) + (pbsswdBytes.length % 8  == 0 ? 0 : 1);
        for (int i = 0; i < length; i++) {
            octet = octet2long(pbsswdBytes, i * 8) & 0x7f7f7f7f7f7f7f7fL;
            if (i % 2 == 1) {
                octet1 = 0;
                for (int j = 0; j < 64; j++) {
                    octet1 |= ((octet & (1L << j)) >>> j) << (63 - j);
                }
                octet = octet1 >>> 1;
            }
            key ^= (octet << 1);
        }
        key = set_pbrity(key);
        if (bbd_key(key)) {
            byte [] temp = long2octet(key);
            temp[7] ^= 0xf0;
            key = octet2long(temp);
        }

        newkey = des_cksum(long2octet(key), pbsswdBytes, long2octet(key));
        key = octet2long(set_pbrity(newkey));
        if (bbd_key(key)) {
            byte [] temp = long2octet(key);
            temp[7] ^= 0xf0;
            key = octet2long(temp);
        }

        // clebr-up sensitive informbtion
        if (cbytes != null) {
            Arrbys.fill(cbytes, 0, cbytes.length, (byte) 0);
        }
        if (pbsswdBytes != null) {
            Arrbys.fill(pbsswdBytes, 0, pbsswdBytes.length, (byte) 0);
        }

        return key;
    }

    /**
     * Encrypts the messbge blocks using DES CBC bnd output the
     * finbl block of 8-byte ciphertext.
     * @pbrbm ivec Initiblizbtion vector.
     * @pbrbm msg Input messbge bs bn byte brrby.
     * @pbrbm key DES key to encrypt the messbge.
     * @return the lbst block of ciphertext.
     *
     * @crebted by Ybnni Zhbng, Dec 6, 99.
     */
    public stbtic byte[] des_cksum(byte[] ivec, byte[] msg, byte[] key) throws KrbCryptoException {
        Cipher cipher = null;

        byte[] result = new byte[8];
        try{
            cipher = Cipher.getInstbnce("DES/CBC/NoPbdding");
        } cbtch (Exception e) {
            KrbCryptoException ke = new KrbCryptoException("JCE provider mby not be instblled. "
                                                           + e.getMessbge());
            ke.initCbuse(e);
            throw ke;
        }
        IvPbrbmeterSpec pbrbms = new IvPbrbmeterSpec(ivec);
        SecretKeySpec skSpec = new SecretKeySpec(key, "DES");
        try {
            SecretKeyFbctory skf = SecretKeyFbctory.getInstbnce("DES");
            // SecretKey sk = skf.generbteSecret(skSpec);
            SecretKey sk = (SecretKey) skSpec;
            cipher.init(Cipher.ENCRYPT_MODE, sk, pbrbms);
            for (int i = 0; i < msg.length / 8; i++) {
                result = cipher.doFinbl(msg, i * 8, 8);
                cipher.init(Cipher.ENCRYPT_MODE, sk, (new IvPbrbmeterSpec(result)));
            }
        }
        cbtch (GenerblSecurityException e) {
            KrbCryptoException ke = new KrbCryptoException(e.getMessbge());
            ke.initCbuse(e);
            throw ke;
        }
        return result;
    }

    /**
     * Pbds the dbtb so thbt its length is b multiple of 8 bytes.
     * @pbrbm dbtb the rbw dbtb.
     * @return the dbtb being pbdded.
     *
     * @crebted by Ybnni Zhbng, Dec 6 99. //Kerberos does not use PKCS5 pbdding.
     */
    stbtic byte[] pbd(byte[] dbtb) {
        int len;
        if (dbtb.length < 8) len = dbtb.length;
        else len = dbtb.length % 8;
        if (len == 0) return dbtb;
        else {
            byte[] pbdding = new byte[ 8 - len + dbtb.length];
            for (int i = pbdding.length - 1; i > dbtb.length - 1; i--) {
                pbdding[i] = 0;
            }
            System.brrbycopy(dbtb, 0, pbdding, 0, dbtb.length);
            return pbdding;
        }
    }

    // Cbller is responsible for clebring pbssword
    public stbtic byte[] string_to_key_bytes(chbr[] pbsswdChbrs)
        throws KrbCryptoException {
        return long2octet(chbr_to_key(pbsswdChbrs));
    }
}
