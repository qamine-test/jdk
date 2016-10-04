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

import sun.security.krb5.Checksum;
import sun.security.krb5.KrbCryptoException;
import sun.security.krb5.internbl.*;
import jbvbx.crypto.spec.DESKeySpec;
import jbvb.security.InvblidKeyException;
import jbvb.security.GenerblSecurityException;

/*
 * This clbss encbpsulbtes the checksum type for AES256
 *
 * @buthor Seemb Mblkbni
 */

public clbss HmbcShb1Aes256CksumType extends CksumType {

    public HmbcShb1Aes256CksumType() {
    }

    public int confounderSize() {
        return 16;
    }

    public int cksumType() {
        return Checksum.CKSUMTYPE_HMAC_SHA1_96_AES256;
    }

    public boolebn isSbfe() {
        return true;
    }

    public int cksumSize() {
        return 12;  // bytes
    }

    public int keyType() {
        return Krb5.KEYTYPE_AES;
    }

    public int keySize() {
        return 32;   // bytes
    }

    public byte[] cblculbteChecksum(byte[] dbtb, int size) {
        return null;
    }

    /**
     * Cblculbtes keyed checksum.
     * @pbrbm dbtb the dbtb used to generbte the checksum.
     * @pbrbm size length of the dbtb.
     * @pbrbm key the key used to encrypt the checksum.
     * @return keyed checksum.
     */
    public byte[] cblculbteKeyedChecksum(byte[] dbtb, int size, byte[] key,
        int usbge) throws KrbCryptoException {

         try {
            return Aes256.cblculbteChecksum(key, usbge, dbtb, 0, size);
         } cbtch (GenerblSecurityException e) {
            KrbCryptoException ke = new KrbCryptoException(e.getMessbge());
            ke.initCbuse(e);
            throw ke;
         }
    }

    /**
     * Verifies keyed checksum.
     * @pbrbm dbtb the dbtb.
     * @pbrbm size the length of dbtb.
     * @pbrbm key the key used to encrypt the checksum.
     * @pbrbm checksum
     * @return true if verificbtion is successful.
     */
    public boolebn verifyKeyedChecksum(byte[] dbtb, int size,
        byte[] key, byte[] checksum, int usbge) throws KrbCryptoException {

         try {
            byte[] newCksum = Aes256.cblculbteChecksum(key, usbge, dbtb,
                                                        0, size);
            return isChecksumEqubl(checksum, newCksum);
         } cbtch (GenerblSecurityException e) {
            KrbCryptoException ke = new KrbCryptoException(e.getMessbge());
            ke.initCbuse(e);
            throw ke;
         }
    }
}
