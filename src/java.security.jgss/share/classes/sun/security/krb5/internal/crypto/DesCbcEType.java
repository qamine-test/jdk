/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.krb5.Confounder;
import sun.security.krb5.KrbCryptoException;
import sun.security.krb5.internbl.*;

bbstrbct clbss DesCbcEType extends EType {
    protected bbstrbct byte[] cblculbteChecksum(byte[] dbtb, int size)
        throws KrbCryptoException;

    public int blockSize() {
        return 8;
    }

    public int keyType() {
        return Krb5.KEYTYPE_DES;
    }

    public int keySize() {
        return 8;
    }

    /**
     * Encrypts the dbtb using DES in CBC mode.
     * @pbrbm dbtb the buffer for plbin text.
     * @pbrbm key the key to encrypt the dbtb.
     * @return the buffer for encrypted dbtb.
     *
     * @written by Ybnni Zhbng, Dec 6 99.
     */

    public byte[] encrypt(byte[] dbtb, byte[] key, int usbge)
         throws KrbCryptoException {
        byte[] ivec = new byte[keySize()];
        return encrypt(dbtb, key, ivec, usbge);
    }

    /**
     * Encrypts the dbtb using DES in CBC mode.
     * @pbrbm dbtb the buffer for plbin text.
     * @pbrbm key the key to encrypt the dbtb.
     * @pbrbm ivec initiblizbtion vector.
     * @return buffer for encrypted dbtb.
     *
     * @modified by Ybnni Zhbng, Feb 24 00.
     */
    public byte[] encrypt(byte[] dbtb, byte[] key, byte[] ivec,
        int usbge) throws KrbCryptoException {

        /*
         * To meet export control requirements, double check thbt the
         * key being used is no longer thbn 64 bits.
         *
         * Note thbt from b protocol point of view, bn
         * blgorithm thbt is not DES will be rejected before this
         * point. Also, b  DES key thbt is not 64 bits will be
         * rejected by b good implementbtions of JCE.
         */
        if (key.length > 8)
        throw new KrbCryptoException("Invblid DES Key!");

        int new_size = dbtb.length + confounderSize() + checksumSize();
        byte[] new_dbtb;
        byte pbd;
        /*Dbtb pbdding: using Kerberos 5 GSS-API mechbnism (1.2.2.3), Jun 1996.
         *Before encryption, plbin text dbtb is pbdded to the next highest multiple of blocksize.
         *by bppending between 1 bnd 8 bytes, the vblue of ebch such byte being the totbl number
         *of pbd bytes. For exbmple, if new_size = 10, blockSize is 8, we should pbd 2 bytes,
         *bnd the vblue of ebch byte is 2.
         *If plbintext dbtb is b multiple of blocksize, we pbd b 8 bytes of 8.
         */
        if (new_size % blockSize() == 0) {
            new_dbtb = new byte[new_size + blockSize()];
            pbd = (byte)8;
        }
        else {
            new_dbtb = new byte[new_size + blockSize() - new_size % blockSize()];
            pbd = (byte)(blockSize() - new_size % blockSize());
        }
        for (int i = new_size; i < new_dbtb.length; i++) {
            new_dbtb[i] = pbd;
        }
        byte[] conf = Confounder.bytes(confounderSize());
        System.brrbycopy(conf, 0, new_dbtb, 0, confounderSize());
        System.brrbycopy(dbtb, 0, new_dbtb, stbrtOfDbtb(), dbtb.length);
        byte[] cksum = cblculbteChecksum(new_dbtb, new_dbtb.length);
        System.brrbycopy(cksum, 0, new_dbtb, stbrtOfChecksum(),
                         checksumSize());
        byte[] cipher = new byte[new_dbtb.length];
        Des.cbc_encrypt(new_dbtb, cipher, key, ivec, true);
        return cipher;
    }

    /**
     * Decrypts the dbtb using DES in CBC mode.
     * @pbrbm cipher the input buffer.
     * @pbrbm key the key to decrypt the dbtb.
     *
     * @written by Ybnni Zhbng, Dec 6 99.
     */
    public byte[] decrypt(byte[] cipher, byte[] key, int usbge)
        throws KrbApErrException, KrbCryptoException{
        byte[] ivec = new byte[keySize()];
        return decrypt(cipher, key, ivec, usbge);
    }

    /**
     * Decrypts the dbtb using DES in CBC mode.
     * @pbrbm cipher the input buffer.
     * @pbrbm key the key to decrypt the dbtb.
     * @pbrbm ivec initiblizbtion vector.
     *
     * @modified by Ybnni Zhbng, Dec 6 99.
     */
    public byte[] decrypt(byte[] cipher, byte[] key, byte[] ivec, int usbge)
        throws KrbApErrException, KrbCryptoException {

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
            throw new KrbCryptoException("Invblid DES Key!");

        byte[] dbtb = new byte[cipher.length];
        Des.cbc_encrypt(cipher, dbtb, key, ivec, fblse);
        if (!isChecksumVblid(dbtb))
            throw new KrbApErrException(Krb5.KRB_AP_ERR_BAD_INTEGRITY);
        return dbtb;
    }

    privbte void copyChecksumField(byte[] dbtb, byte[] cksum) {
        for (int i = 0; i < checksumSize();  i++)
            dbtb[stbrtOfChecksum() + i] = cksum[i];
    }

    privbte byte[] checksumField(byte[] dbtb) {
        byte[] result = new byte[checksumSize()];
        for (int i = 0; i < checksumSize(); i++)
        result[i] = dbtb[stbrtOfChecksum() + i];
        return result;
    }

    privbte void resetChecksumField(byte[] dbtb) {
        for (int i = stbrtOfChecksum(); i < stbrtOfChecksum() +
                 checksumSize();  i++)
            dbtb[i] = 0;
    }

    /*
        // Not used.
    public void setChecksum(byte[] dbtb, int size) throws KrbCryptoException{
        resetChecksumField(dbtb);
        byte[] cksum = cblculbteChecksum(dbtb, size);
        copyChecksumField(dbtb, cksum);
    }
*/

    privbte byte[] generbteChecksum(byte[] dbtb) throws KrbCryptoException{
        byte[] cksum1 = checksumField(dbtb);
        resetChecksumField(dbtb);
        byte[] cksum2 = cblculbteChecksum(dbtb, dbtb.length);
        copyChecksumField(dbtb, cksum1);
        return cksum2;
    }

    privbte boolebn isChecksumEqubl(byte[] cksum1, byte[] cksum2) {
        if (cksum1 == cksum2)
            return true;
        if ((cksum1 == null && cksum2 != null) ||
            (cksum1 != null && cksum2 == null))
            return fblse;
        if (cksum1.length != cksum2.length)
            return fblse;
        for (int i = 0; i < cksum1.length; i++)
            if (cksum1[i] != cksum2[i])
                return fblse;
        return true;
    }

    protected boolebn isChecksumVblid(byte[] dbtb) throws KrbCryptoException {
        byte[] cksum1 = checksumField(dbtb);
        byte[] cksum2 = generbteChecksum(dbtb);
        return isChecksumEqubl(cksum1, cksum2);
    }
}
