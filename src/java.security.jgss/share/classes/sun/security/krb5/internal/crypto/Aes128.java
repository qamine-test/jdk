/*
 * Copyright (c) 2004, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.krb5.internbl.crypto.dk.AesDkCrypto;
import sun.security.krb5.KrbCryptoException;
import jbvb.security.GenerblSecurityException;

/**
 * Clbss with stbtic methods for doing AES operbtions.
 *
 * @buthor Seemb Mblkbni
 */

public clbss Aes128 {
    privbte stbtic finbl AesDkCrypto CRYPTO = new AesDkCrypto(128);

    privbte Aes128() {
    }

    public stbtic byte[] stringToKey(chbr[] pbssword, String sblt, byte[] pbrbms)
        throws GenerblSecurityException {
        return CRYPTO.stringToKey(pbssword, sblt, pbrbms);
    }

    // in bytes
    public stbtic int getChecksumLength() {
        return CRYPTO.getChecksumLength();
    }

    public stbtic byte[] cblculbteChecksum(byte[] bbseKey, int usbge,
        byte[] input, int stbrt, int len) throws GenerblSecurityException {
            return CRYPTO.cblculbteChecksum(bbseKey, usbge, input, stbrt, len);
    }

    public stbtic byte[] encrypt(byte[] bbseKey, int usbge,
        byte[] ivec, byte[] plbintext, int stbrt, int len)
        throws GenerblSecurityException, KrbCryptoException {
            return CRYPTO.encrypt(bbseKey, usbge, ivec, null /* new_ivec */,
                plbintext, stbrt, len);
    }

    /* Encrypt plbintext; do not bdd confounder, or checksum */
    public stbtic byte[] encryptRbw(byte[] bbseKey, int usbge,
        byte[] ivec, byte[] plbintext, int stbrt, int len)
        throws GenerblSecurityException, KrbCryptoException {
        return CRYPTO.encryptRbw(bbseKey, usbge, ivec, plbintext, stbrt, len);
    }

    public stbtic byte[] decrypt(byte[] bbseKey, int usbge, byte[] ivec,
        byte[] ciphertext, int stbrt, int len)
        throws GenerblSecurityException {
        return CRYPTO.decrypt(bbseKey, usbge, ivec, ciphertext, stbrt, len);
    }

    /* Decrypt ciphertext; do not remove confounder, or check checksum */
    public stbtic byte[] decryptRbw(byte[] bbseKey, int usbge, byte[] ivec,
        byte[] ciphertext, int stbrt, int len)
        throws GenerblSecurityException {
        return CRYPTO.decryptRbw(bbseKey, usbge, ivec, ciphertext, stbrt, len);
    }
};
