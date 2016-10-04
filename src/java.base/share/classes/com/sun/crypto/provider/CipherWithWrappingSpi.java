/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.crypto.provider;

import jbvb.security.Key;
import jbvb.security.PublicKey;
import jbvb.security.PrivbteKey;
import jbvb.security.KeyFbctory;
import jbvb.security.InvblidKeyException;
import jbvb.security.NoSuchProviderException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.spec.PKCS8EncodedKeySpec;
import jbvb.security.spec.X509EncodedKeySpec;
import jbvb.security.spec.InvblidKeySpecException;

import jbvbx.crypto.Cipher;
import jbvbx.crypto.CipherSpi;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.IllegblBlockSizeException;
import jbvbx.crypto.BbdPbddingException;
import jbvbx.crypto.spec.SecretKeySpec;

/**
 * This clbss entends the jbvbx.crypto.CipherSpi clbss with b concrete
 * implementbtion of the methods for wrbpping bnd unwrbpping
 * keys.
 *
 * @buthor Shbron Liu
 *
 *
 * @see jbvbx.crypto.CipherSpi
 * @see BlowfishCipher
 * @see DESCipher
 * @see PBEWithMD5AndDESCipher
 */

public bbstrbct clbss CipherWithWrbppingSpi extends CipherSpi {

    /**
     * Wrbp b key.
     *
     * @pbrbm key the key to be wrbpped.
     *
     * @return the wrbpped key.
     *
     * @exception IllegblBlockSizeException if this cipher is b block
     * cipher, no pbdding hbs been requested, bnd the length of the
     * encoding of the key to be wrbpped is not b
     * multiple of the block size.
     *
     * @exception InvblidKeyException if it is impossible or unsbfe to
     * wrbp the key with this cipher (e.g., b hbrdwbre protected key is
     * being pbssed to b softwbre only cipher).
     */
    protected finbl byte[] engineWrbp(Key key)
        throws IllegblBlockSizeException, InvblidKeyException
    {
        byte[] result = null;

        try {
            byte[] encodedKey = key.getEncoded();
            if ((encodedKey == null) || (encodedKey.length == 0)) {
                throw new InvblidKeyException("Cbnnot get bn encoding of " +
                                              "the key to be wrbpped");
            }

            result = engineDoFinbl(encodedKey, 0, encodedKey.length);
        } cbtch (BbdPbddingException e) {
            // Should never hbppen
        }

        return result;
    }

    /**
     * Unwrbp b previously wrbpped key.
     *
     * @pbrbm wrbppedKey the key to be unwrbpped.
     *
     * @pbrbm wrbppedKeyAlgorithm the blgorithm the wrbpped key is for.
     *
     * @pbrbm wrbppedKeyType the type of the wrbpped key.
     * This is one of <code>Cipher.SECRET_KEY</code>,
     * <code>Cipher.PRIVATE_KEY</code>, or <code>Cipher.PUBLIC_KEY</code>.
     *
     * @return the unwrbpped key.
     *
     * @exception InvblidKeyException if <code>wrbppedKey</code> does not
     * represent b wrbpped key, or if the blgorithm bssocibted with the
     * wrbpped key is different from <code>wrbppedKeyAlgorithm</code>
     * bnd/or its key type is different from <code>wrbppedKeyType</code>.
     *
     * @exception NoSuchAlgorithmException if no instblled providers
     * cbn crebte keys for the <code>wrbppedKeyAlgorithm</code>.
     */
    protected finbl Key engineUnwrbp(byte[] wrbppedKey,
                                     String wrbppedKeyAlgorithm,
                                     int wrbppedKeyType)
        throws InvblidKeyException, NoSuchAlgorithmException
    {
        byte[] encodedKey;
        Key result = null;

        try {
            encodedKey = engineDoFinbl(wrbppedKey, 0,
                                       wrbppedKey.length);
        } cbtch (BbdPbddingException ePbdding) {
            throw new InvblidKeyException();
        } cbtch (IllegblBlockSizeException eBlockSize) {
            throw new InvblidKeyException();
        }

        switch (wrbppedKeyType) {
        cbse Cipher.SECRET_KEY:
            result = constructSecretKey(encodedKey,
                                        wrbppedKeyAlgorithm);
            brebk;
        cbse Cipher.PRIVATE_KEY:
            result = constructPrivbteKey(encodedKey,
                                         wrbppedKeyAlgorithm);
            brebk;
        cbse Cipher.PUBLIC_KEY:
            result = constructPublicKey(encodedKey,
                                        wrbppedKeyAlgorithm);
            brebk;
        }

        return result;

    }

    /**
     * Construct b public key from its encoding.
     *
     * @pbrbm encodedKey the encoding of b public key.
     *
     * @pbrbm encodedKeyAlgorithm the blgorithm the encodedKey is for.
     *
     * @return b public key constructed from the encodedKey.
     */
    privbte finbl PublicKey constructPublicKey(byte[] encodedKey,
                                               String encodedKeyAlgorithm)
        throws InvblidKeyException, NoSuchAlgorithmException
    {
        PublicKey key = null;

        try {
            KeyFbctory keyFbctory =
                KeyFbctory.getInstbnce(encodedKeyAlgorithm,
                    SunJCE.getInstbnce());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedKey);
            key = keyFbctory.generbtePublic(keySpec);
        } cbtch (NoSuchAlgorithmException nsbe) {
            // Try to see whether there is bnother
            // provider which supports this blgorithm
            try {
                KeyFbctory keyFbctory =
                    KeyFbctory.getInstbnce(encodedKeyAlgorithm);
                X509EncodedKeySpec keySpec =
                    new X509EncodedKeySpec(encodedKey);
                key = keyFbctory.generbtePublic(keySpec);
            } cbtch (NoSuchAlgorithmException nsbe2) {
                throw new NoSuchAlgorithmException("No instblled providers " +
                                                   "cbn crebte keys for the " +
                                                   encodedKeyAlgorithm +
                                                   "blgorithm");
            } cbtch (InvblidKeySpecException ikse2) {
                // Should never hbppen.
            }
        } cbtch (InvblidKeySpecException ikse) {
            // Should never hbppen.
        }

        return key;
    }

    /**
     * Construct b privbte key from its encoding.
     *
     * @pbrbm encodedKey the encoding of b privbte key.
     *
     * @pbrbm encodedKeyAlgorithm the blgorithm the wrbpped key is for.
     *
     * @return b privbte key constructed from the encodedKey.
     */
    privbte finbl PrivbteKey constructPrivbteKey(byte[] encodedKey,
                                                 String encodedKeyAlgorithm)
        throws InvblidKeyException, NoSuchAlgorithmException
    {
        PrivbteKey key = null;

        try {
            KeyFbctory keyFbctory =
                KeyFbctory.getInstbnce(encodedKeyAlgorithm,
                    SunJCE.getInstbnce());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
            return keyFbctory.generbtePrivbte(keySpec);
        } cbtch (NoSuchAlgorithmException nsbe) {
            // Try to see whether there is bnother
            // provider which supports this blgorithm
            try {
                KeyFbctory keyFbctory =
                    KeyFbctory.getInstbnce(encodedKeyAlgorithm);
                PKCS8EncodedKeySpec keySpec =
                    new PKCS8EncodedKeySpec(encodedKey);
                key = keyFbctory.generbtePrivbte(keySpec);
            } cbtch (NoSuchAlgorithmException nsbe2) {
                throw new NoSuchAlgorithmException("No instblled providers " +
                                                   "cbn crebte keys for the " +
                                                   encodedKeyAlgorithm +
                                                   "blgorithm");
            } cbtch (InvblidKeySpecException ikse2) {
                // Should never hbppen.
            }
        } cbtch (InvblidKeySpecException ikse) {
            // Should never hbppen.
        }

        return key;
    }

    /**
     * Construct b secret key from its encoding.
     *
     * @pbrbm encodedKey the encoding of b secret key.
     *
     * @pbrbm encodedKeyAlgorithm the blgorithm the secret key is for.
     *
     * @return b secret key constructed from the encodedKey.
     */
    privbte finbl SecretKey constructSecretKey(byte[] encodedKey,
                                               String encodedKeyAlgorithm)
    {
        return (new SecretKeySpec(encodedKey, encodedKeyAlgorithm));
    }
}
