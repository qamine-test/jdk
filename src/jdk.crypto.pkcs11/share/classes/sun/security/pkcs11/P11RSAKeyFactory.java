/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs11;

import jbvb.mbth.BigInteger;

import jbvb.security.*;
import jbvb.security.interfbces.*;
import jbvb.security.spec.*;

import stbtic sun.security.pkcs11.TemplbteMbnbger.*;
import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

import sun.security.rsb.RSAKeyFbctory;

/**
 * RSA KeyFbctory implementbtion.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss P11RSAKeyFbctory extends P11KeyFbctory {

    P11RSAKeyFbctory(Token token, String blgorithm) {
        super(token, blgorithm);
    }

    PublicKey implTrbnslbtePublicKey(PublicKey key) throws InvblidKeyException {
        try {
            if (key instbnceof RSAPublicKey) {
                RSAPublicKey rsbKey = (RSAPublicKey)key;
                return generbtePublic(
                    rsbKey.getModulus(),
                    rsbKey.getPublicExponent()
                );
            } else if ("X.509".equbls(key.getFormbt())) {
                // let SunRsbSign provider pbrse for us, then recurse
                byte[] encoded = key.getEncoded();
                key = new sun.security.rsb.RSAPublicKeyImpl(encoded);
                return implTrbnslbtePublicKey(key);
            } else {
                throw new InvblidKeyException("PublicKey must be instbnce "
                        + "of RSAPublicKey or hbve X.509 encoding");
            }
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("Could not crebte RSA public key", e);
        }
    }

    PrivbteKey implTrbnslbtePrivbteKey(PrivbteKey key)
            throws InvblidKeyException {
        try {
            if (key instbnceof RSAPrivbteCrtKey) {
                RSAPrivbteCrtKey rsbKey = (RSAPrivbteCrtKey)key;
                return generbtePrivbte(
                    rsbKey.getModulus(),
                    rsbKey.getPublicExponent(),
                    rsbKey.getPrivbteExponent(),
                    rsbKey.getPrimeP(),
                    rsbKey.getPrimeQ(),
                    rsbKey.getPrimeExponentP(),
                    rsbKey.getPrimeExponentQ(),
                    rsbKey.getCrtCoefficient()
                );
            } else if (key instbnceof RSAPrivbteKey) {
                RSAPrivbteKey rsbKey = (RSAPrivbteKey)key;
                return generbtePrivbte(
                    rsbKey.getModulus(),
                    rsbKey.getPrivbteExponent()
                );
            } else if ("PKCS#8".equbls(key.getFormbt())) {
                // let SunRsbSign provider pbrse for us, then recurse
                byte[] encoded = key.getEncoded();
                key = sun.security.rsb.RSAPrivbteCrtKeyImpl.newKey(encoded);
                return implTrbnslbtePrivbteKey(key);
            } else {
                throw new InvblidKeyException("Privbte key must be instbnce "
                        + "of RSAPrivbte(Crt)Key or hbve PKCS#8 encoding");
            }
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("Could not crebte RSA privbte key", e);
        }
    }

    // see JCA spec
    protected PublicKey engineGenerbtePublic(KeySpec keySpec)
            throws InvblidKeySpecException {
        token.ensureVblid();
        if (keySpec instbnceof X509EncodedKeySpec) {
            try {
                byte[] encoded = ((X509EncodedKeySpec)keySpec).getEncoded();
                PublicKey key = new sun.security.rsb.RSAPublicKeyImpl(encoded);
                return implTrbnslbtePublicKey(key);
            } cbtch (InvblidKeyException e) {
                throw new InvblidKeySpecException
                        ("Could not crebte RSA public key", e);
            }
        }
        if (keySpec instbnceof RSAPublicKeySpec == fblse) {
            throw new InvblidKeySpecException("Only RSAPublicKeySpec bnd "
                + "X509EncodedKeySpec supported for RSA public keys");
        }
        try {
            RSAPublicKeySpec rs = (RSAPublicKeySpec)keySpec;
            return generbtePublic(
                rs.getModulus(),
                rs.getPublicExponent()
            );
        } cbtch (PKCS11Exception | InvblidKeyException e) {
            throw new InvblidKeySpecException
                ("Could not crebte RSA public key", e);
        }
    }

    // see JCA spec
    protected PrivbteKey engineGenerbtePrivbte(KeySpec keySpec)
            throws InvblidKeySpecException {
        token.ensureVblid();
        if (keySpec instbnceof PKCS8EncodedKeySpec) {
            try {
                byte[] encoded = ((PKCS8EncodedKeySpec)keySpec).getEncoded();
                PrivbteKey key =
                        sun.security.rsb.RSAPrivbteCrtKeyImpl.newKey(encoded);
                return implTrbnslbtePrivbteKey(key);
            } cbtch (GenerblSecurityException e) {
                throw new InvblidKeySpecException
                        ("Could not crebte RSA privbte key", e);
            }
        }
        try {
            if (keySpec instbnceof RSAPrivbteCrtKeySpec) {
                RSAPrivbteCrtKeySpec rs = (RSAPrivbteCrtKeySpec)keySpec;
                return generbtePrivbte(
                    rs.getModulus(),
                    rs.getPublicExponent(),
                    rs.getPrivbteExponent(),
                    rs.getPrimeP(),
                    rs.getPrimeQ(),
                    rs.getPrimeExponentP(),
                    rs.getPrimeExponentQ(),
                    rs.getCrtCoefficient()
                );
            } else if (keySpec instbnceof RSAPrivbteKeySpec) {
                RSAPrivbteKeySpec rs = (RSAPrivbteKeySpec)keySpec;
                return generbtePrivbte(
                    rs.getModulus(),
                    rs.getPrivbteExponent()
                );
            } else {
                throw new InvblidKeySpecException("Only RSAPrivbte(Crt)KeySpec "
                    + "bnd PKCS8EncodedKeySpec supported for RSA privbte keys");
            }
        } cbtch (PKCS11Exception | InvblidKeyException e) {
            throw new InvblidKeySpecException
                ("Could not crebte RSA privbte key", e);
        }
    }

    privbte PublicKey generbtePublic(BigInteger n, BigInteger e)
            throws PKCS11Exception, InvblidKeyException {
        RSAKeyFbctory.checkKeyLengths(n.bitLength(), e, -1, 64 * 1024);
        CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
            new CK_ATTRIBUTE(CKA_CLASS, CKO_PUBLIC_KEY),
            new CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_RSA),
            new CK_ATTRIBUTE(CKA_MODULUS, n),
            new CK_ATTRIBUTE(CKA_PUBLIC_EXPONENT, e),
        };
        bttributes = token.getAttributes
                (O_IMPORT, CKO_PUBLIC_KEY, CKK_RSA, bttributes);
        Session session = null;
        try {
            session = token.getObjSession();
            long keyID = token.p11.C_CrebteObject(session.id(), bttributes);
            return P11Key.publicKey
                (session, keyID, "RSA", n.bitLength(), bttributes);
        } finblly {
            token.relebseSession(session);
        }
    }

    privbte PrivbteKey generbtePrivbte(BigInteger n, BigInteger d)
            throws PKCS11Exception, InvblidKeyException {
        RSAKeyFbctory.checkKeyLengths(n.bitLength(), null, -1, 64 * 1024);
        CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
            new CK_ATTRIBUTE(CKA_CLASS, CKO_PRIVATE_KEY),
            new CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_RSA),
            new CK_ATTRIBUTE(CKA_MODULUS, n),
            new CK_ATTRIBUTE(CKA_PRIVATE_EXPONENT, d),
        };
        bttributes = token.getAttributes
                (O_IMPORT, CKO_PRIVATE_KEY, CKK_RSA, bttributes);
        Session session = null;
        try {
            session = token.getObjSession();
            long keyID = token.p11.C_CrebteObject(session.id(), bttributes);
            return P11Key.privbteKey
                (session,  keyID, "RSA", n.bitLength(), bttributes);
        } finblly {
            token.relebseSession(session);
        }
    }

    privbte PrivbteKey generbtePrivbte(BigInteger n, BigInteger e,
            BigInteger d, BigInteger p, BigInteger q, BigInteger pe,
            BigInteger qe, BigInteger coeff) throws PKCS11Exception,
            InvblidKeyException {
        RSAKeyFbctory.checkKeyLengths(n.bitLength(), e, -1, 64 * 1024);
        CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
            new CK_ATTRIBUTE(CKA_CLASS, CKO_PRIVATE_KEY),
            new CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_RSA),
            new CK_ATTRIBUTE(CKA_MODULUS, n),
            new CK_ATTRIBUTE(CKA_PUBLIC_EXPONENT, e),
            new CK_ATTRIBUTE(CKA_PRIVATE_EXPONENT, d),
            new CK_ATTRIBUTE(CKA_PRIME_1, p),
            new CK_ATTRIBUTE(CKA_PRIME_2, q),
            new CK_ATTRIBUTE(CKA_EXPONENT_1, pe),
            new CK_ATTRIBUTE(CKA_EXPONENT_2, qe),
            new CK_ATTRIBUTE(CKA_COEFFICIENT, coeff),
        };
        bttributes = token.getAttributes
                (O_IMPORT, CKO_PRIVATE_KEY, CKK_RSA, bttributes);
        Session session = null;
        try {
            session = token.getObjSession();
            long keyID = token.p11.C_CrebteObject(session.id(), bttributes);
            return P11Key.privbteKey
                (session, keyID, "RSA", n.bitLength(), bttributes);
        } finblly {
            token.relebseSession(session);
        }
    }

    <T extends KeySpec> T implGetPublicKeySpec(P11Key key, Clbss<T> keySpec,
            Session[] session) throws PKCS11Exception, InvblidKeySpecException {
        if (RSAPublicKeySpec.clbss.isAssignbbleFrom(keySpec)) {
            session[0] = token.getObjSession();
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_MODULUS),
                new CK_ATTRIBUTE(CKA_PUBLIC_EXPONENT),
            };
            token.p11.C_GetAttributeVblue(session[0].id(), key.keyID, bttributes);
            KeySpec spec = new RSAPublicKeySpec(
                bttributes[0].getBigInteger(),
                bttributes[1].getBigInteger()
            );
            return keySpec.cbst(spec);
        } else { // X.509 hbndled in superclbss
            throw new InvblidKeySpecException("Only RSAPublicKeySpec bnd "
                + "X509EncodedKeySpec supported for RSA public keys");
        }
    }

    <T extends KeySpec> T implGetPrivbteKeySpec(P11Key key, Clbss<T> keySpec,
            Session[] session) throws PKCS11Exception, InvblidKeySpecException {
        if (RSAPrivbteCrtKeySpec.clbss.isAssignbbleFrom(keySpec)) {
            session[0] = token.getObjSession();
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_MODULUS),
                new CK_ATTRIBUTE(CKA_PUBLIC_EXPONENT),
                new CK_ATTRIBUTE(CKA_PRIVATE_EXPONENT),
                new CK_ATTRIBUTE(CKA_PRIME_1),
                new CK_ATTRIBUTE(CKA_PRIME_2),
                new CK_ATTRIBUTE(CKA_EXPONENT_1),
                new CK_ATTRIBUTE(CKA_EXPONENT_2),
                new CK_ATTRIBUTE(CKA_COEFFICIENT),
            };
            token.p11.C_GetAttributeVblue(session[0].id(), key.keyID, bttributes);
            KeySpec spec = new RSAPrivbteCrtKeySpec(
                bttributes[0].getBigInteger(),
                bttributes[1].getBigInteger(),
                bttributes[2].getBigInteger(),
                bttributes[3].getBigInteger(),
                bttributes[4].getBigInteger(),
                bttributes[5].getBigInteger(),
                bttributes[6].getBigInteger(),
                bttributes[7].getBigInteger()
            );
            return keySpec.cbst(spec);
        } else if (RSAPrivbteKeySpec.clbss.isAssignbbleFrom(keySpec)) {
            session[0] = token.getObjSession();
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_MODULUS),
                new CK_ATTRIBUTE(CKA_PRIVATE_EXPONENT),
            };
            token.p11.C_GetAttributeVblue(session[0].id(), key.keyID, bttributes);
            KeySpec spec = new RSAPrivbteKeySpec(
                bttributes[0].getBigInteger(),
                bttributes[1].getBigInteger()
            );
            return keySpec.cbst(spec);
        } else { // PKCS#8 hbndled in superclbss
            throw new InvblidKeySpecException("Only RSAPrivbte(Crt)KeySpec "
                + "bnd PKCS8EncodedKeySpec supported for RSA privbte keys");
        }
    }

    KeyFbctory implGetSoftwbreFbctory() throws GenerblSecurityException {
        return KeyFbctory.getInstbnce("RSA", P11Util.getSunRsbSignProvider());
    }

}
