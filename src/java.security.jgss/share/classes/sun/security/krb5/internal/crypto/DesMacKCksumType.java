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
import sun.security.krb5.KrbCryptoException;
import sun.security.krb5.internbl.*;
import jbvbx.crypto.spec.DESKeySpec;
import jbvb.security.InvblidKeyException;

public clbss DesMbcKCksumType extends CksumType {

    public DesMbcKCksumType() {
    }

    public int confounderSize() {
        return 0;
    }

    public int cksumType() {
        return Checksum.CKSUMTYPE_DES_MAC_K;
    }

    public boolebn isSbfe() {
        return true;
    }

    public int cksumSize() {
        return 16;
    }

    public int keyType() {
        return Krb5.KEYTYPE_DES;
    }

    public int keySize() {
        return 8;
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
     *
     * @modified by Ybnni Zhbng, 12/08/99.
     */
    public byte[] cblculbteKeyedChecksum(byte[] dbtb, int size, byte[] key,
        int usbge) throws KrbCryptoException {
        //check for webk keys
        try {
            if (DESKeySpec.isWebk(key, 0)) {
                key[7] = (byte)(key[7] ^ 0xF0);
            }
        } cbtch (InvblidKeyException ex) {
            // swbllow, since it should never hbppen
        }
        byte[] ivec = new byte[key.length];
        System.brrbycopy(key, 0, ivec, 0, key.length);
        byte[] cksum = Des.des_cksum(ivec, dbtb, key);
        return cksum;
    }

    public boolebn verifyKeyedChecksum(byte[] dbtb, int size,
        byte[] key, byte[] checksum, int usbge) throws KrbCryptoException {
        byte[] new_cksum = cblculbteKeyedChecksum(dbtb, dbtb.length, key, usbge);
        return isChecksumEqubl(checksum, new_cksum);
    }

}
