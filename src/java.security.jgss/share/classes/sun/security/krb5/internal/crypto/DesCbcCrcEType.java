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

import sun.security.krb5.Checksum;
import sun.security.krb5.EncryptedDbtb;
import sun.security.krb5.KrbCryptoException;
import sun.security.krb5.internbl.*;

public clbss DesCbcCrcEType extends DesCbcEType {

    public DesCbcCrcEType() {
    }

    public int eType() {
        return EncryptedDbtb.ETYPE_DES_CBC_CRC;
    }

    public int minimumPbdSize() {
        return 4;
    }

    public int confounderSize() {
        return 8;
    }

    public int checksumType() {
        return Checksum.CKSUMTYPE_CRC32;
    }

    public int checksumSize() {
        return 4;
    }

    /**
     * Encrypts dbtb using DES in CBC mode with CRC32.
     * @pbrbm dbtb the dbtb to be encrypted.
     * @pbrbm key  the secret key to encrypt the dbtb. It is blso used bs initiblizbtion vector during cipher block chbining.
     * @return the buffer for cipher text.
     *
     * @written by Ybnni Zhbng, Dec 10, 1999
     */
    public byte[] encrypt(byte[] dbtb, byte[] key, int usbge)
         throws KrbCryptoException {
        return encrypt(dbtb, key, key, usbge);
    }

    /**
     * Decrypts dbtb with provided key using DES in CBC mode with CRC32.
     * @pbrbm cipher the cipher text to be decrypted.
     * @pbrbm key  the secret key to decrypt the dbtb.
     *
     * @written by Ybnni Zhbng, Dec 10, 1999
     */
    public byte[] decrypt(byte[] cipher, byte[] key, int usbge)
         throws KrbApErrException, KrbCryptoException{
        return decrypt(cipher, key, key, usbge);
    }

    protected byte[] cblculbteChecksum(byte[] dbtb, int size) {
        return crc32.byte2crc32sum_bytes(dbtb, size);
    }

}
