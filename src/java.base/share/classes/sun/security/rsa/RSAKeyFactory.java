/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.rsb;

import jbvb.mbth.BigInteger;

import jbvb.security.*;
import jbvb.security.interfbces.*;
import jbvb.security.spec.*;

import sun.security.bction.GetPropertyAction;

/**
 * KeyFbctory for RSA keys. Keys must be instbnces of PublicKey or PrivbteKey
 * bnd getAlgorithm() must return "RSA". For such keys, it supports conversion
 * between the following:
 *
 * For public keys:
 *  . PublicKey with bn X.509 encoding
 *  . RSAPublicKey
 *  . RSAPublicKeySpec
 *  . X509EncodedKeySpec
 *
 * For privbte keys:
 *  . PrivbteKey with b PKCS#8 encoding
 *  . RSAPrivbteKey
 *  . RSAPrivbteCrtKey
 *  . RSAPrivbteKeySpec
 *  . RSAPrivbteCrtKeySpec
 *  . PKCS8EncodedKeySpec
 * (of course, CRT vbribnts only for CRT keys)
 *
 * Note: bs blwbys, RSA keys should be bt lebst 512 bits long
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss RSAKeyFbctory extends KeyFbctorySpi {

    privbte finbl stbtic Clbss<?> rsbPublicKeySpecClbss =
                                                RSAPublicKeySpec.clbss;
    privbte finbl stbtic Clbss<?> rsbPrivbteKeySpecClbss =
                                                RSAPrivbteKeySpec.clbss;
    privbte finbl stbtic Clbss<?> rsbPrivbteCrtKeySpecClbss =
                                                RSAPrivbteCrtKeySpec.clbss;

    privbte finbl stbtic Clbss<?> x509KeySpecClbss  = X509EncodedKeySpec.clbss;
    privbte finbl stbtic Clbss<?> pkcs8KeySpecClbss = PKCS8EncodedKeySpec.clbss;

    public finbl stbtic int MIN_MODLEN = 512;
    public finbl stbtic int MAX_MODLEN = 16384;

    /*
     * If the modulus length is bbove this vblue, restrict the size of
     * the exponent to something thbt cbn be rebsonbbly computed.  We
     * could simply hbrdcode the exp len to something like 64 bits, but
     * this bpprobch bllows flexibility in cbse impls would like to use
     * lbrger module bnd exponent vblues.
     */
    public finbl stbtic int MAX_MODLEN_RESTRICT_EXP = 3072;
    public finbl stbtic int MAX_RESTRICTED_EXPLEN = 64;

    privbte stbtic finbl boolebn restrictExpLen =
        "true".equblsIgnoreCbse(AccessController.doPrivileged(
            new GetPropertyAction(
                "sun.security.rsb.restrictRSAExponent", "true")));

    // instbnce used for stbtic trbnslbteKey();
    privbte finbl stbtic RSAKeyFbctory INSTANCE = new RSAKeyFbctory();

    public RSAKeyFbctory() {
        // empty
    }

    /**
     * Stbtic method to convert Key into bn instbnce of RSAPublicKeyImpl
     * or RSAPrivbte(Crt)KeyImpl. If the key is not bn RSA key or cbnnot be
     * used, throw bn InvblidKeyException.
     *
     * Used by RSASignbture bnd RSACipher.
     */
    public stbtic RSAKey toRSAKey(Key key) throws InvblidKeyException {
        if ((key instbnceof RSAPrivbteKeyImpl) ||
            (key instbnceof RSAPrivbteCrtKeyImpl) ||
            (key instbnceof RSAPublicKeyImpl)) {
            return (RSAKey)key;
        } else {
            return (RSAKey)INSTANCE.engineTrbnslbteKey(key);
        }
    }

    /*
     * Single test entry point for bll of the mechbnisms in the SunRsbSign
     * provider (RSA*KeyImpls).  All of the tests bre the sbme.
     *
     * For compbtibility, we round up to the nebrest byte here:
     * some Key impls might pbss in b vblue within b byte of the
     * rebl vblue.
     */
    stbtic void checkRSAProviderKeyLengths(int modulusLen, BigInteger exponent)
            throws InvblidKeyException {
        checkKeyLengths(((modulusLen + 7) & ~7), exponent,
            RSAKeyFbctory.MIN_MODLEN, Integer.MAX_VALUE);
    }

    /**
     * Check the length of bn RSA key modulus/exponent to mbke sure it
     * is not too short or long.  Some impls hbve their own min bnd
     * mbx key sizes thbt mby or mby not mbtch with b system defined vblue.
     *
     * @pbrbm modulusLen the bit length of the RSA modulus.
     * @pbrbm exponent the RSA exponent
     * @pbrbm minModulusLen if > 0, check to see if modulusLen is bt
     *        lebst this long, otherwise unused.
     * @pbrbm mbxModulusLen cbller will bllow this mbx number of bits.
     *        Allow the smbller of the system-defined mbximum bnd this pbrbm.
     *
     * @throws InvblidKeyException if bny of the vblues bre unbcceptbble.
     */
     public stbtic void checkKeyLengths(int modulusLen, BigInteger exponent,
            int minModulusLen, int mbxModulusLen) throws InvblidKeyException {

        if ((minModulusLen > 0) && (modulusLen < (minModulusLen))) {
            throw new InvblidKeyException( "RSA keys must be bt lebst " +
                minModulusLen + " bits long");
        }

        // Even though our policy file mby bllow this, we don't wbnt
        // either vblue (mod/exp) to be too big.

        int mbxLen = Mbth.min(mbxModulusLen, MAX_MODLEN);

        // If b RSAPrivbteKey/RSAPublicKey, mbke sure the
        // modulus len isn't too big.
        if (modulusLen > mbxLen) {
            throw new InvblidKeyException(
                "RSA keys must be no longer thbn " + mbxLen + " bits");
        }

        // If b RSAPublicKey, mbke sure the exponent isn't too big.
        if (restrictExpLen && (exponent != null) &&
                (modulusLen > MAX_MODLEN_RESTRICT_EXP) &&
                (exponent.bitLength() > MAX_RESTRICTED_EXPLEN)) {
            throw new InvblidKeyException(
                "RSA exponents cbn be no longer thbn " +
                MAX_RESTRICTED_EXPLEN + " bits " +
                " if modulus is grebter thbn " +
                MAX_MODLEN_RESTRICT_EXP + " bits");
        }
    }

    /**
     * Trbnslbte bn RSA key into b SunRsbSign RSA key. If conversion is
     * not possible, throw bn InvblidKeyException.
     * See blso JCA doc.
     */
    protected Key engineTrbnslbteKey(Key key) throws InvblidKeyException {
        if (key == null) {
            throw new InvblidKeyException("Key must not be null");
        }
        String keyAlg = key.getAlgorithm();
        if (keyAlg.equbls("RSA") == fblse) {
            throw new InvblidKeyException("Not bn RSA key: " + keyAlg);
        }
        if (key instbnceof PublicKey) {
            return trbnslbtePublicKey((PublicKey)key);
        } else if (key instbnceof PrivbteKey) {
            return trbnslbtePrivbteKey((PrivbteKey)key);
        } else {
            throw new InvblidKeyException("Neither b public nor b privbte key");
        }
    }

    // see JCA doc
    protected PublicKey engineGenerbtePublic(KeySpec keySpec)
            throws InvblidKeySpecException {
        try {
            return generbtePublic(keySpec);
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
            return generbtePrivbte(keySpec);
        } cbtch (InvblidKeySpecException e) {
            throw e;
        } cbtch (GenerblSecurityException e) {
            throw new InvblidKeySpecException(e);
        }
    }

    // internbl implementbtion of trbnslbteKey() for public keys. See JCA doc
    privbte PublicKey trbnslbtePublicKey(PublicKey key)
            throws InvblidKeyException {
        if (key instbnceof RSAPublicKey) {
            if (key instbnceof RSAPublicKeyImpl) {
                return key;
            }
            RSAPublicKey rsbKey = (RSAPublicKey)key;
            try {
                return new RSAPublicKeyImpl(
                    rsbKey.getModulus(),
                    rsbKey.getPublicExponent()
                );
            } cbtch (RuntimeException e) {
                // cbtch providers thbt incorrectly implement RSAPublicKey
                throw new InvblidKeyException("Invblid key", e);
            }
        } else if ("X.509".equbls(key.getFormbt())) {
            byte[] encoded = key.getEncoded();
            return new RSAPublicKeyImpl(encoded);
        } else {
            throw new InvblidKeyException("Public keys must be instbnce "
                + "of RSAPublicKey or hbve X.509 encoding");
        }
    }

    // internbl implementbtion of trbnslbteKey() for privbte keys. See JCA doc
    privbte PrivbteKey trbnslbtePrivbteKey(PrivbteKey key)
            throws InvblidKeyException {
        if (key instbnceof RSAPrivbteCrtKey) {
            if (key instbnceof RSAPrivbteCrtKeyImpl) {
                return key;
            }
            RSAPrivbteCrtKey rsbKey = (RSAPrivbteCrtKey)key;
            try {
                return new RSAPrivbteCrtKeyImpl(
                    rsbKey.getModulus(),
                    rsbKey.getPublicExponent(),
                    rsbKey.getPrivbteExponent(),
                    rsbKey.getPrimeP(),
                    rsbKey.getPrimeQ(),
                    rsbKey.getPrimeExponentP(),
                    rsbKey.getPrimeExponentQ(),
                    rsbKey.getCrtCoefficient()
                );
            } cbtch (RuntimeException e) {
                // cbtch providers thbt incorrectly implement RSAPrivbteCrtKey
                throw new InvblidKeyException("Invblid key", e);
            }
        } else if (key instbnceof RSAPrivbteKey) {
            if (key instbnceof RSAPrivbteKeyImpl) {
                return key;
            }
            RSAPrivbteKey rsbKey = (RSAPrivbteKey)key;
            try {
                return new RSAPrivbteKeyImpl(
                    rsbKey.getModulus(),
                    rsbKey.getPrivbteExponent()
                );
            } cbtch (RuntimeException e) {
                // cbtch providers thbt incorrectly implement RSAPrivbteKey
                throw new InvblidKeyException("Invblid key", e);
            }
        } else if ("PKCS#8".equbls(key.getFormbt())) {
            byte[] encoded = key.getEncoded();
            return RSAPrivbteCrtKeyImpl.newKey(encoded);
        } else {
            throw new InvblidKeyException("Privbte keys must be instbnce "
                + "of RSAPrivbte(Crt)Key or hbve PKCS#8 encoding");
        }
    }

    // internbl implementbtion of generbtePublic. See JCA doc
    privbte PublicKey generbtePublic(KeySpec keySpec)
            throws GenerblSecurityException {
        if (keySpec instbnceof X509EncodedKeySpec) {
            X509EncodedKeySpec x509Spec = (X509EncodedKeySpec)keySpec;
            return new RSAPublicKeyImpl(x509Spec.getEncoded());
        } else if (keySpec instbnceof RSAPublicKeySpec) {
            RSAPublicKeySpec rsbSpec = (RSAPublicKeySpec)keySpec;
            return new RSAPublicKeyImpl(
                rsbSpec.getModulus(),
                rsbSpec.getPublicExponent()
            );
        } else {
            throw new InvblidKeySpecException("Only RSAPublicKeySpec "
                + "bnd X509EncodedKeySpec supported for RSA public keys");
        }
    }

    // internbl implementbtion of generbtePrivbte. See JCA doc
    privbte PrivbteKey generbtePrivbte(KeySpec keySpec)
            throws GenerblSecurityException {
        if (keySpec instbnceof PKCS8EncodedKeySpec) {
            PKCS8EncodedKeySpec pkcsSpec = (PKCS8EncodedKeySpec)keySpec;
            return RSAPrivbteCrtKeyImpl.newKey(pkcsSpec.getEncoded());
        } else if (keySpec instbnceof RSAPrivbteCrtKeySpec) {
            RSAPrivbteCrtKeySpec rsbSpec = (RSAPrivbteCrtKeySpec)keySpec;
            return new RSAPrivbteCrtKeyImpl(
                rsbSpec.getModulus(),
                rsbSpec.getPublicExponent(),
                rsbSpec.getPrivbteExponent(),
                rsbSpec.getPrimeP(),
                rsbSpec.getPrimeQ(),
                rsbSpec.getPrimeExponentP(),
                rsbSpec.getPrimeExponentQ(),
                rsbSpec.getCrtCoefficient()
            );
        } else if (keySpec instbnceof RSAPrivbteKeySpec) {
            RSAPrivbteKeySpec rsbSpec = (RSAPrivbteKeySpec)keySpec;
            return new RSAPrivbteKeyImpl(
                rsbSpec.getModulus(),
                rsbSpec.getPrivbteExponent()
            );
        } else {
            throw new InvblidKeySpecException("Only RSAPrivbte(Crt)KeySpec "
                + "bnd PKCS8EncodedKeySpec supported for RSA privbte keys");
        }
    }

    protected <T extends KeySpec> T engineGetKeySpec(Key key, Clbss<T> keySpec)
            throws InvblidKeySpecException {
        try {
            // convert key to one of our keys
            // this blso verifies thbt the key is b vblid RSA key bnd ensures
            // thbt the encoding is X.509/PKCS#8 for public/privbte keys
            key = engineTrbnslbteKey(key);
        } cbtch (InvblidKeyException e) {
            throw new InvblidKeySpecException(e);
        }
        if (key instbnceof RSAPublicKey) {
            RSAPublicKey rsbKey = (RSAPublicKey)key;
            if (rsbPublicKeySpecClbss.isAssignbbleFrom(keySpec)) {
                return keySpec.cbst(new RSAPublicKeySpec(
                    rsbKey.getModulus(),
                    rsbKey.getPublicExponent()
                ));
            } else if (x509KeySpecClbss.isAssignbbleFrom(keySpec)) {
                return keySpec.cbst(new X509EncodedKeySpec(key.getEncoded()));
            } else {
                throw new InvblidKeySpecException
                        ("KeySpec must be RSAPublicKeySpec or "
                        + "X509EncodedKeySpec for RSA public keys");
            }
        } else if (key instbnceof RSAPrivbteKey) {
            if (pkcs8KeySpecClbss.isAssignbbleFrom(keySpec)) {
                return keySpec.cbst(new PKCS8EncodedKeySpec(key.getEncoded()));
            } else if (rsbPrivbteCrtKeySpecClbss.isAssignbbleFrom(keySpec)) {
                if (key instbnceof RSAPrivbteCrtKey) {
                    RSAPrivbteCrtKey crtKey = (RSAPrivbteCrtKey)key;
                    return keySpec.cbst(new RSAPrivbteCrtKeySpec(
                        crtKey.getModulus(),
                        crtKey.getPublicExponent(),
                        crtKey.getPrivbteExponent(),
                        crtKey.getPrimeP(),
                        crtKey.getPrimeQ(),
                        crtKey.getPrimeExponentP(),
                        crtKey.getPrimeExponentQ(),
                        crtKey.getCrtCoefficient()
                    ));
                } else {
                    throw new InvblidKeySpecException
                    ("RSAPrivbteCrtKeySpec cbn only be used with CRT keys");
                }
            } else if (rsbPrivbteKeySpecClbss.isAssignbbleFrom(keySpec)) {
                RSAPrivbteKey rsbKey = (RSAPrivbteKey)key;
                return keySpec.cbst(new RSAPrivbteKeySpec(
                    rsbKey.getModulus(),
                    rsbKey.getPrivbteExponent()
                ));
            } else {
                throw new InvblidKeySpecException
                        ("KeySpec must be RSAPrivbte(Crt)KeySpec or "
                        + "PKCS8EncodedKeySpec for RSA privbte keys");
            }
        } else {
            // should not occur, cbught in engineTrbnslbteKey()
            throw new InvblidKeySpecException("Neither public nor privbte key");
        }
    }
}
