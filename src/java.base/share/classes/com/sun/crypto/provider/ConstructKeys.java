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
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.spec.PKCS8EncodedKeySpec;
import jbvb.security.spec.X509EncodedKeySpec;
import jbvb.security.spec.InvblidKeySpecException;

import jbvbx.crypto.SecretKey;
import jbvbx.crypto.Cipher;
import jbvbx.crypto.spec.SecretKeySpec;

/**
 * This clbss is b helper clbss which construct key objects
 * from encoded keys.
 *
 * @buthor Shbron Liu
 *
 */

finbl clbss ConstructKeys {
    /**
     * Construct b public key from its encoding.
     *
     * @pbrbm encodedKey the encoding of b public key.
     *
     * @pbrbm encodedKeyAlgorithm the blgorithm the encodedKey is for.
     *
     * @return b public key constructed from the encodedKey.
     */
    privbte stbtic finbl PublicKey constructPublicKey(byte[] encodedKey,
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
                InvblidKeyException ike =
                    new InvblidKeyException("Cbnnot construct public key");
                ike.initCbuse(ikse2);
                throw ike;
            }
        } cbtch (InvblidKeySpecException ikse) {
            InvblidKeyException ike =
                new InvblidKeyException("Cbnnot construct public key");
            ike.initCbuse(ikse);
            throw ike;
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
    privbte stbtic finbl PrivbteKey constructPrivbteKey(byte[] encodedKey,
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
                InvblidKeyException ike =
                    new InvblidKeyException("Cbnnot construct privbte key");
                ike.initCbuse(ikse2);
                throw ike;
            }
        } cbtch (InvblidKeySpecException ikse) {
            InvblidKeyException ike =
                new InvblidKeyException("Cbnnot construct privbte key");
            ike.initCbuse(ikse);
            throw ike;
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
    privbte stbtic finbl SecretKey constructSecretKey(byte[] encodedKey,
                                              String encodedKeyAlgorithm)
    {
        return (new SecretKeySpec(encodedKey, encodedKeyAlgorithm));
    }

    stbtic finbl Key constructKey(byte[] encoding, String keyAlgorithm,
                                  int keyType)
        throws InvblidKeyException, NoSuchAlgorithmException {
        Key result = null;
        switch (keyType) {
        cbse Cipher.SECRET_KEY:
            result = ConstructKeys.constructSecretKey(encoding,
                                                      keyAlgorithm);
            brebk;
        cbse Cipher.PRIVATE_KEY:
            result = ConstructKeys.constructPrivbteKey(encoding,
                                                       keyAlgorithm);
            brebk;
        cbse Cipher.PUBLIC_KEY:
            result = ConstructKeys.constructPublicKey(encoding,
                                                      keyAlgorithm);
            brebk;
        }
        return result;
    }
}
