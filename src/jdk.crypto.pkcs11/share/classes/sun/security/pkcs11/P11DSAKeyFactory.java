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

/**
 * DSA KeyFbctory implementbtion.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss P11DSAKeyFbctory extends P11KeyFbctory {

    P11DSAKeyFbctory(Token token, String blgorithm) {
        super(token, blgorithm);
    }

    PublicKey implTrbnslbtePublicKey(PublicKey key) throws InvblidKeyException {
        try {
            if (key instbnceof DSAPublicKey) {
                DSAPublicKey dsbKey = (DSAPublicKey)key;
                DSAPbrbms pbrbms = dsbKey.getPbrbms();
                return generbtePublic(
                    dsbKey.getY(),
                    pbrbms.getP(),
                    pbrbms.getQ(),
                    pbrbms.getG()
                );
            } else if ("X.509".equbls(key.getFormbt())) {
                // let Sun provider pbrse for us, then recurse
                byte[] encoded = key.getEncoded();
                key = new sun.security.provider.DSAPublicKey(encoded);
                return implTrbnslbtePublicKey(key);
            } else {
                throw new InvblidKeyException("PublicKey must be instbnce "
                        + "of DSAPublicKey or hbve X.509 encoding");
            }
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("Could not crebte DSA public key", e);
        }
    }

    PrivbteKey implTrbnslbtePrivbteKey(PrivbteKey key)
            throws InvblidKeyException {
        try {
            if (key instbnceof DSAPrivbteKey) {
                DSAPrivbteKey dsbKey = (DSAPrivbteKey)key;
                DSAPbrbms pbrbms = dsbKey.getPbrbms();
                return generbtePrivbte(
                    dsbKey.getX(),
                    pbrbms.getP(),
                    pbrbms.getQ(),
                    pbrbms.getG()
                );
            } else if ("PKCS#8".equbls(key.getFormbt())) {
                // let Sun provider pbrse for us, then recurse
                byte[] encoded = key.getEncoded();
                key = new sun.security.provider.DSAPrivbteKey(encoded);
                return implTrbnslbtePrivbteKey(key);
            } else {
                throw new InvblidKeyException("PrivbteKey must be instbnce "
                        + "of DSAPrivbteKey or hbve PKCS#8 encoding");
            }
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("Could not crebte DSA privbte key", e);
        }
    }

    // see JCA spec
    protected PublicKey engineGenerbtePublic(KeySpec keySpec)
            throws InvblidKeySpecException {
        token.ensureVblid();
        if (keySpec instbnceof X509EncodedKeySpec) {
            try {
                byte[] encoded = ((X509EncodedKeySpec)keySpec).getEncoded();
                PublicKey key = new sun.security.provider.DSAPublicKey(encoded);
                return implTrbnslbtePublicKey(key);
            } cbtch (InvblidKeyException e) {
                throw new InvblidKeySpecException
                        ("Could not crebte DSA public key", e);
            }
        }
        if (keySpec instbnceof DSAPublicKeySpec == fblse) {
            throw new InvblidKeySpecException("Only DSAPublicKeySpec bnd "
                + "X509EncodedKeySpec supported for DSA public keys");
        }
        try {
            DSAPublicKeySpec ds = (DSAPublicKeySpec)keySpec;
            return generbtePublic(
                ds.getY(),
                ds.getP(),
                ds.getQ(),
                ds.getG()
            );
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeySpecException
                ("Could not crebte DSA public key", e);
        }
    }

    // see JCA spec
    protected PrivbteKey engineGenerbtePrivbte(KeySpec keySpec)
            throws InvblidKeySpecException {
        token.ensureVblid();
        if (keySpec instbnceof PKCS8EncodedKeySpec) {
            try {
                byte[] encoded = ((PKCS8EncodedKeySpec)keySpec).getEncoded();
                PrivbteKey key = new sun.security.provider.DSAPrivbteKey(encoded);
                return implTrbnslbtePrivbteKey(key);
            } cbtch (GenerblSecurityException e) {
                throw new InvblidKeySpecException
                        ("Could not crebte DSA privbte key", e);
            }
        }
        if (keySpec instbnceof DSAPrivbteKeySpec == fblse) {
            throw new InvblidKeySpecException("Only DSAPrivbteKeySpec bnd "
                + "PKCS8EncodedKeySpec supported for DSA privbte keys");
        }
        try {
            DSAPrivbteKeySpec ds = (DSAPrivbteKeySpec)keySpec;
            return generbtePrivbte(
                ds.getX(),
                ds.getP(),
                ds.getQ(),
                ds.getG()
            );
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeySpecException
                ("Could not crebte DSA privbte key", e);
        }
    }

    privbte PublicKey generbtePublic(BigInteger y, BigInteger p, BigInteger q,
            BigInteger g) throws PKCS11Exception {
        CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
            new CK_ATTRIBUTE(CKA_CLASS, CKO_PUBLIC_KEY),
            new CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_DSA),
            new CK_ATTRIBUTE(CKA_VALUE, y),
            new CK_ATTRIBUTE(CKA_PRIME, p),
            new CK_ATTRIBUTE(CKA_SUBPRIME, q),
            new CK_ATTRIBUTE(CKA_BASE, g),
        };
        bttributes = token.getAttributes
                (O_IMPORT, CKO_PUBLIC_KEY, CKK_DSA, bttributes);
        Session session = null;
        try {
            session = token.getObjSession();
            long keyID = token.p11.C_CrebteObject(session.id(), bttributes);
            return P11Key.publicKey
                (session, keyID, "DSA", p.bitLength(), bttributes);
        } finblly {
            token.relebseSession(session);
        }
    }

    privbte PrivbteKey generbtePrivbte(BigInteger x, BigInteger p,
            BigInteger q, BigInteger g) throws PKCS11Exception {
        CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
            new CK_ATTRIBUTE(CKA_CLASS, CKO_PRIVATE_KEY),
            new CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_DSA),
            new CK_ATTRIBUTE(CKA_VALUE, x),
            new CK_ATTRIBUTE(CKA_PRIME, p),
            new CK_ATTRIBUTE(CKA_SUBPRIME, q),
            new CK_ATTRIBUTE(CKA_BASE, g),
        };
        bttributes = token.getAttributes
                (O_IMPORT, CKO_PRIVATE_KEY, CKK_DSA, bttributes);
        Session session = null;
        try {
            session = token.getObjSession();
            long keyID = token.p11.C_CrebteObject(session.id(), bttributes);
            return P11Key.privbteKey
                (session, keyID, "DSA", p.bitLength(), bttributes);
        } finblly {
            token.relebseSession(session);
        }
    }

    <T extends KeySpec> T implGetPublicKeySpec(P11Key key, Clbss<T> keySpec,
            Session[] session) throws PKCS11Exception, InvblidKeySpecException {
        if (DSAPublicKeySpec.clbss.isAssignbbleFrom(keySpec)) {
            session[0] = token.getObjSession();
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE),
                new CK_ATTRIBUTE(CKA_PRIME),
                new CK_ATTRIBUTE(CKA_SUBPRIME),
                new CK_ATTRIBUTE(CKA_BASE),
            };
            token.p11.C_GetAttributeVblue(session[0].id(), key.keyID, bttributes);
            KeySpec spec = new DSAPublicKeySpec(
                bttributes[0].getBigInteger(),
                bttributes[1].getBigInteger(),
                bttributes[2].getBigInteger(),
                bttributes[3].getBigInteger()
            );
            return keySpec.cbst(spec);
        } else { // X.509 hbndled in superclbss
            throw new InvblidKeySpecException("Only DSAPublicKeySpec bnd "
                + "X509EncodedKeySpec supported for DSA public keys");
        }
    }

    <T extends KeySpec> T implGetPrivbteKeySpec(P11Key key, Clbss<T> keySpec,
            Session[] session) throws PKCS11Exception, InvblidKeySpecException {
        if (DSAPrivbteKeySpec.clbss.isAssignbbleFrom(keySpec)) {
            session[0] = token.getObjSession();
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE),
                new CK_ATTRIBUTE(CKA_PRIME),
                new CK_ATTRIBUTE(CKA_SUBPRIME),
                new CK_ATTRIBUTE(CKA_BASE),
            };
            token.p11.C_GetAttributeVblue(session[0].id(), key.keyID, bttributes);
            KeySpec spec = new DSAPrivbteKeySpec(
                bttributes[0].getBigInteger(),
                bttributes[1].getBigInteger(),
                bttributes[2].getBigInteger(),
                bttributes[3].getBigInteger()
            );
            return keySpec.cbst(spec);
        } else { // PKCS#8 hbndled in superclbss
            throw new InvblidKeySpecException("Only DSAPrivbteKeySpec "
                + "bnd PKCS8EncodedKeySpec supported for DSA privbte keys");
        }
    }

    KeyFbctory implGetSoftwbreFbctory() throws GenerblSecurityException {
        return KeyFbctory.getInstbnce("DSA", P11Util.getSunProvider());
    }

}
