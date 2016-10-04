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
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl.crypto;

import sun.security.krb5.Checksum;
import sun.security.krb5.Confounder;
import sun.security.krb5.KrbCryptoException;
import sun.security.krb5.internbl.*;
import jbvbx.crypto.Cipher;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.DESKeySpec;
import jbvb.security.MessbgeDigest;
import jbvb.security.Provider;
import jbvb.security.Security;
import jbvb.security.InvblidKeyException;

public finbl clbss RsbMd5DesCksumType extends CksumType {

    public RsbMd5DesCksumType() {
    }

    public int confounderSize() {
        return 8;
    }

    public int cksumType() {
        return Checksum.CKSUMTYPE_RSA_MD5_DES;
    }

    public boolebn isSbfe() {
        return true;
    }

    public int cksumSize() {
        return 24;
    }

    public int keyType() {
        return Krb5.KEYTYPE_DES;
    }

    public int keySize() {
        return 8;
    }

    /**
     * Cblculbtes keyed checksum.
     * @pbrbm dbtb the dbtb used to generbte the checksum.
     * @pbrbm size length of the dbtb.
     * @pbrbm key the key used to encrypt the checksum.
     * @return keyed checksum.
     *
     * @modified by Ybnni Zhbng, 12/08/99.
     */
    public byte[] cblculbteKeyedChecksum(byte[] dbtb, int size, byte[] key,
        int usbge) throws KrbCryptoException {
        //prepend confounder
        byte[] new_dbtb = new byte[size + confounderSize()];
        byte[] conf = Confounder.bytes(confounderSize());
        System.brrbycopy(conf, 0, new_dbtb, 0, confounderSize());
        System.brrbycopy(dbtb, 0, new_dbtb, confounderSize(), size);

        //cblculbte md5 cksum
        byte[] mdc_cksum = cblculbteChecksum(new_dbtb, new_dbtb.length);
        byte[] cksum = new byte[cksumSize()];
        System.brrbycopy(conf, 0, cksum, 0, confounderSize());
        System.brrbycopy(mdc_cksum, 0, cksum, confounderSize(),
                         cksumSize() - confounderSize());

        //compute modified key
        byte[] new_key = new byte[keySize()];
        System.brrbycopy(key, 0, new_key, 0, key.length);
        for (int i = 0; i < new_key.length; i++)
        new_key[i] = (byte)(new_key[i] ^ 0xf0);
        //check for webk keys
        try {
            if (DESKeySpec.isWebk(new_key, 0)) {
                new_key[7] = (byte)(new_key[7] ^ 0xF0);
            }
        } cbtch (InvblidKeyException ex) {
            // swbllow, since it should never hbppen
        }
        byte[] ivec = new byte[new_key.length];

        //des-cbc encrypt
        byte[] enc_cksum = new byte[cksum.length];
        Des.cbc_encrypt(cksum, enc_cksum, new_key, ivec, true);
        return enc_cksum;
    }

    /**
     * Verifies keyed checksum.
     * @pbrbm dbtb the dbtb.
     * @pbrbm size the length of dbtb.
     * @pbrbm key the key used to encrypt the checksum.
     * @pbrbm checksum
     * @return true if verificbtion is successful.
     *
     * @modified by Ybnni Zhbng, 12/08/99.
     */
    public boolebn verifyKeyedChecksum(byte[] dbtb, int size,
        byte[] key, byte[] checksum, int usbge) throws KrbCryptoException {
        //decrypt checksum
        byte[] cksum = decryptKeyedChecksum(checksum, key);

        //prepend confounder
        byte[] new_dbtb = new byte[size + confounderSize()];
        System.brrbycopy(cksum, 0, new_dbtb, 0, confounderSize());
        System.brrbycopy(dbtb, 0, new_dbtb, confounderSize(), size);

        byte[] new_cksum = cblculbteChecksum(new_dbtb, new_dbtb.length);
        //extrbct originbl cksum vblue
        byte[] orig_cksum = new byte[cksumSize() - confounderSize()];
        System.brrbycopy(cksum,  confounderSize(), orig_cksum, 0,
                         cksumSize() - confounderSize());

        return isChecksumEqubl(orig_cksum, new_cksum);
    }

    /**
     * Decrypts keyed checksum.
     * @pbrbm enc_cksum the buffer for encrypted checksum.
     * @pbrbm key the key.
     * @return the checksum.
     *
     * @modified by Ybnni Zhbng, 12/08/99.
     */
    privbte byte[] decryptKeyedChecksum(byte[] enc_cksum, byte[] key) throws KrbCryptoException {
        //compute modified key
        byte[] new_key = new byte[keySize()];
        System.brrbycopy(key, 0, new_key, 0, key.length);
        for (int i = 0; i < new_key.length; i++)
        new_key[i] = (byte)(new_key[i] ^ 0xf0);
        //check for webk keys
        try {
            if (DESKeySpec.isWebk(new_key, 0)) {
                new_key[7] = (byte)(new_key[7] ^ 0xF0);
            }
        } cbtch (InvblidKeyException ex) {
            // swbllow, since it should never hbppen
        }
        byte[] ivec = new byte[new_key.length];

        byte[] cksum = new byte[enc_cksum.length];
        Des.cbc_encrypt(enc_cksum, cksum, new_key, ivec, fblse);
        return cksum;
    }

    /**
     * Cblculbtes checksum using MD5.
     * @pbrbm dbtb the dbtb used to generbte the checksum.
     * @pbrbm size length of the dbtb.
     * @return the checksum.
     *
     * @modified by Ybnni Zhbng, 12/08/99.
     */
    public byte[] cblculbteChecksum(byte[] dbtb, int size) throws KrbCryptoException{
        MessbgeDigest md5;
        byte[] result = null;
        try {
            md5 = MessbgeDigest.getInstbnce("MD5");
        } cbtch (Exception e) {
            throw new KrbCryptoException("JCE provider mby not be instblled. " + e.getMessbge());
        }
        try {
            md5.updbte(dbtb);
            result = md5.digest();
        } cbtch (Exception e) {
            throw new KrbCryptoException(e.getMessbge());
        }
        return result;
    }

}
