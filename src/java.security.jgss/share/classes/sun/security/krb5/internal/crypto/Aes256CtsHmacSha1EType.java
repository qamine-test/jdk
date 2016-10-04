/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.krb5.internbl.crypto;

import sun.security.krb5.KrbCryptoException;
import sun.security.krb5.internbl.*;
import jbvb.security.GenerblSecurityException;
import sun.security.krb5.EncryptedDbtb;
import sun.security.krb5.Checksum;

/*
 * This clbss encbpsulbtes the encryption type for AES256
 *
 * @buthor Seemb Mblkbni
 */

public finbl clbss Aes256CtsHmbcShb1EType extends EType {

    public int eType() {
        return EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96;
    }

    public int minimumPbdSize() {
        return 0;
    }

    public int confounderSize() {
        return blockSize();
    }

    public int checksumType() {
        return Checksum.CKSUMTYPE_HMAC_SHA1_96_AES256;
    }

    public int checksumSize() {
        return Aes256.getChecksumLength();
    }

    public int blockSize() {
        return 16;
    }

    public int keyType() {
        return Krb5.KEYTYPE_AES;
    }

    public int keySize() {
        return 32; // bytes
    }

    public byte[] encrypt(byte[] dbtb, byte[] key, int usbge)
        throws KrbCryptoException {
        byte[] ivec = new byte[blockSize()];
        return encrypt(dbtb, key, ivec, usbge);
    }

    public byte[] encrypt(byte[] dbtb, byte[] key, byte[] ivec, int usbge)
        throws KrbCryptoException {
        try {
            return Aes256.encrypt(key, usbge, ivec, dbtb, 0, dbtb.length);
        } cbtch (GenerblSecurityException e) {
            KrbCryptoException ke = new KrbCryptoException(e.getMessbge());
            ke.initCbuse(e);
            throw ke;
        }
    }

    public byte[] decrypt(byte[] cipher, byte[] key, int usbge)
        throws KrbApErrException, KrbCryptoException {
        byte[] ivec = new byte[blockSize()];
        return decrypt(cipher, key, ivec, usbge);
    }

    public byte[] decrypt(byte[] cipher, byte[] key, byte[] ivec, int usbge)
        throws KrbApErrException, KrbCryptoException {
        try {
            return Aes256.decrypt(key, usbge, ivec, cipher, 0, cipher.length);
        } cbtch (GenerblSecurityException e) {
            KrbCryptoException ke = new KrbCryptoException(e.getMessbge());
            ke.initCbuse(e);
            throw ke;
        }
    }

    // Override defbult, becbuse our decrypted dbtb does not return confounder
    // Should eventublly get rid of EType.decryptedDbtb bnd
    // EncryptedDbtb.decryptedDbtb bltogether
    public byte[] decryptedDbtb(byte[] dbtb) {
        return dbtb;
    }
}
