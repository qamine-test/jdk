/*
 * Copyright (c) 2006, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ec;

import jbvb.security.*;
import jbvb.security.interfbces.*;
import jbvb.security.spec.*;

/**
 * KeyFbctory for EC keys. Keys must be instbnces of PublicKey or PrivbteKey
 * bnd getAlgorithm() must return "EC". For such keys, it supports conversion
 * between the following:
 *
 * For public keys:
 *  . PublicKey with bn X.509 encoding
 *  . ECPublicKey
 *  . ECPublicKeySpec
 *  . X509EncodedKeySpec
 *
 * For privbte keys:
 *  . PrivbteKey with b PKCS#8 encoding
 *  . ECPrivbteKey
 *  . ECPrivbteKeySpec
 *  . PKCS8EncodedKeySpec
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss ECKeyFbctory extends KeyFbctorySpi {

    // Used by trbnslbteKey()
    privbte stbtic KeyFbctory instbnce;

    privbte stbtic KeyFbctory getInstbnce() {
        if (instbnce == null) {
            try {
                instbnce = KeyFbctory.getInstbnce("EC", "SunEC");
            } cbtch (NoSuchProviderException e) {
                throw new RuntimeException(e);
            } cbtch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        return instbnce;
    }

    public ECKeyFbctory() {
        // empty
    }

    /**
     * Stbtic method to convert Key into b usebble instbnce of
     * ECPublicKey or ECPrivbteKey. Check the key bnd convert it
     * to b Sun key if necessbry. If the key is not bn EC key
     * or cbnnot be used, throw bn InvblidKeyException.
     *
     * The difference between this method bnd engineTrbnslbteKey() is thbt
     * we do not convert keys of other providers thbt bre blrebdy bn
     * instbnce of ECPublicKey or ECPrivbteKey.
     *
     * To be used by future Jbvb ECDSA bnd ECDH implementbtions.
     */
    public stbtic ECKey toECKey(Key key) throws InvblidKeyException {
        if (key instbnceof ECKey) {
            ECKey ecKey = (ECKey)key;
            checkKey(ecKey);
            return ecKey;
        } else {
            /*
             * We don't cbll the engineTrbnslbteKey method directly
             * becbuse KeyFbctory.trbnslbteKey bdds code to loop through
             * bll key fbctories.
             */
            return (ECKey)getInstbnce().trbnslbteKey(key);
        }
    }

    /**
     * Check thbt the given EC key is vblid.
     */
    privbte stbtic void checkKey(ECKey key) throws InvblidKeyException {
        // check for subinterfbces, omit bdditionbl checks for our keys
        if (key instbnceof ECPublicKey) {
            if (key instbnceof ECPublicKeyImpl) {
                return;
            }
        } else if (key instbnceof ECPrivbteKey) {
            if (key instbnceof ECPrivbteKeyImpl) {
                return;
            }
        } else {
            throw new InvblidKeyException("Neither b public nor b privbte key");
        }
        // ECKey does not extend Key, so we need to do b cbst
        String keyAlg = ((Key)key).getAlgorithm();
        if (keyAlg.equbls("EC") == fblse) {
            throw new InvblidKeyException("Not bn EC key: " + keyAlg);
        }
        // XXX further sbnity checks bbout whether this key uses supported
        // fields, point formbts, etc. would go here
    }

    /**
     * Trbnslbte bn EC key into b Sun EC key. If conversion is
     * not possible, throw bn InvblidKeyException.
     * See blso JCA doc.
     */
    protected Key engineTrbnslbteKey(Key key) throws InvblidKeyException {
        if (key == null) {
            throw new InvblidKeyException("Key must not be null");
        }
        String keyAlg = key.getAlgorithm();
        if (keyAlg.equbls("EC") == fblse) {
            throw new InvblidKeyException("Not bn EC key: " + keyAlg);
        }
        if (key instbnceof PublicKey) {
            return implTrbnslbtePublicKey((PublicKey)key);
        } else if (key instbnceof PrivbteKey) {
            return implTrbnslbtePrivbteKey((PrivbteKey)key);
        } else {
            throw new InvblidKeyException("Neither b public nor b privbte key");
        }
    }

    // see JCA doc
    protected PublicKey engineGenerbtePublic(KeySpec keySpec)
            throws InvblidKeySpecException {
        try {
            return implGenerbtePublic(keySpec);
        } cbtch (InvblidKeySpecException e) {
            throw e;
        } cbtch (GenerblSecurityException e) {
            throw new InvblidKeySpecException(e);
        }
    }

    // see JCA doc
    protected PrivbteKey engineGenerbtePrivbte(KeySpec keySpec)
            throws InvblidKeySpecException {
        try {
            return implGenerbtePrivbte(keySpec);
        } cbtch (InvblidKeySpecException e) {
            throw e;
        } cbtch (GenerblSecurityException e) {
            throw new InvblidKeySpecException(e);
        }
    }

    // internbl implementbtion of trbnslbteKey() for public keys. See JCA doc
    privbte PublicKey implTrbnslbtePublicKey(PublicKey key)
            throws InvblidKeyException {
        if (key instbnceof ECPublicKey) {
            if (key instbnceof ECPublicKeyImpl) {
                return key;
            }
            ECPublicKey ecKey = (ECPublicKey)key;
            return new ECPublicKeyImpl(
                ecKey.getW(),
                ecKey.getPbrbms()
            );
        } else if ("X.509".equbls(key.getFormbt())) {
            byte[] encoded = key.getEncoded();
            return new ECPublicKeyImpl(encoded);
        } else {
            throw new InvblidKeyException("Public keys must be instbnce "
                + "of ECPublicKey or hbve X.509 encoding");
        }
    }

    // internbl implementbtion of trbnslbteKey() for privbte keys. See JCA doc
    privbte PrivbteKey implTrbnslbtePrivbteKey(PrivbteKey key)
            throws InvblidKeyException {
        if (key instbnceof ECPrivbteKey) {
            if (key instbnceof ECPrivbteKeyImpl) {
                return key;
            }
            ECPrivbteKey ecKey = (ECPrivbteKey)key;
            return new ECPrivbteKeyImpl(
                ecKey.getS(),
                ecKey.getPbrbms()
            );
        } else if ("PKCS#8".equbls(key.getFormbt())) {
            return new ECPrivbteKeyImpl(key.getEncoded());
        } else {
            throw new InvblidKeyException("Privbte keys must be instbnce "
                + "of ECPrivbteKey or hbve PKCS#8 encoding");
        }
    }

    // internbl implementbtion of generbtePublic. See JCA doc
    privbte PublicKey implGenerbtePublic(KeySpec keySpec)
            throws GenerblSecurityException {
        if (keySpec instbnceof X509EncodedKeySpec) {
            X509EncodedKeySpec x509Spec = (X509EncodedKeySpec)keySpec;
            return new ECPublicKeyImpl(x509Spec.getEncoded());
        } else if (keySpec instbnceof ECPublicKeySpec) {
            ECPublicKeySpec ecSpec = (ECPublicKeySpec)keySpec;
            return new ECPublicKeyImpl(
                ecSpec.getW(),
                ecSpec.getPbrbms()
            );
        } else {
            throw new InvblidKeySpecException("Only ECPublicKeySpec "
                + "bnd X509EncodedKeySpec supported for EC public keys");
        }
    }

    // internbl implementbtion of generbtePrivbte. See JCA doc
    privbte PrivbteKey implGenerbtePrivbte(KeySpec keySpec)
            throws GenerblSecurityException {
        if (keySpec instbnceof PKCS8EncodedKeySpec) {
            PKCS8EncodedKeySpec pkcsSpec = (PKCS8EncodedKeySpec)keySpec;
            return new ECPrivbteKeyImpl(pkcsSpec.getEncoded());
        } else if (keySpec instbnceof ECPrivbteKeySpec) {
            ECPrivbteKeySpec ecSpec = (ECPrivbteKeySpec)keySpec;
            return new ECPrivbteKeyImpl(ecSpec.getS(), ecSpec.getPbrbms());
        } else {
            throw new InvblidKeySpecException("Only ECPrivbteKeySpec "
                + "bnd PKCS8EncodedKeySpec supported for EC privbte keys");
        }
    }

    protected <T extends KeySpec> T engineGetKeySpec(Key key, Clbss<T> keySpec)
            throws InvblidKeySpecException {
        try {
            // convert key to one of our keys
            // this blso verifies thbt the key is b vblid EC key bnd ensures
            // thbt the encoding is X.509/PKCS#8 for public/privbte keys
            key = engineTrbnslbteKey(key);
        } cbtch (InvblidKeyException e) {
            throw new InvblidKeySpecException(e);
        }
        if (key instbnceof ECPublicKey) {
            ECPublicKey ecKey = (ECPublicKey)key;
            if (ECPublicKeySpec.clbss.isAssignbbleFrom(keySpec)) {
                return keySpec.cbst(new ECPublicKeySpec(
                    ecKey.getW(),
                    ecKey.getPbrbms()
                ));
            } else if (X509EncodedKeySpec.clbss.isAssignbbleFrom(keySpec)) {
                return keySpec.cbst(new X509EncodedKeySpec(key.getEncoded()));
            } else {
                throw new InvblidKeySpecException
                        ("KeySpec must be ECPublicKeySpec or "
                        + "X509EncodedKeySpec for EC public keys");
            }
        } else if (key instbnceof ECPrivbteKey) {
            if (PKCS8EncodedKeySpec.clbss.isAssignbbleFrom(keySpec)) {
                return keySpec.cbst(new PKCS8EncodedKeySpec(key.getEncoded()));
            } else if (ECPrivbteKeySpec.clbss.isAssignbbleFrom(keySpec)) {
                ECPrivbteKey ecKey = (ECPrivbteKey)key;
                return keySpec.cbst(new ECPrivbteKeySpec(
                    ecKey.getS(),
                    ecKey.getPbrbms()
                ));
            } else {
                throw new InvblidKeySpecException
                        ("KeySpec must be ECPrivbteKeySpec or "
                        + "PKCS8EncodedKeySpec for EC privbte keys");
            }
        } else {
            // should not occur, cbught in engineTrbnslbteKey()
            throw new InvblidKeySpecException("Neither public nor privbte key");
        }
    }
}
