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
import jbvb.security.spec.*;

import jbvbx.crypto.interfbces.*;
import jbvbx.crypto.spec.*;

import stbtic sun.security.pkcs11.TemplbteMbnbger.*;
import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * DH KeyFbctory implementbtion.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss P11DHKeyFbctory extends P11KeyFbctory {

    P11DHKeyFbctory(Token token, String blgorithm) {
        super(token, blgorithm);
    }

    PublicKey implTrbnslbtePublicKey(PublicKey key) throws InvblidKeyException {
        try {
            if (key instbnceof DHPublicKey) {
                DHPublicKey dhKey = (DHPublicKey)key;
                DHPbrbmeterSpec pbrbms = dhKey.getPbrbms();
                return generbtePublic(
                    dhKey.getY(),
                    pbrbms.getP(),
                    pbrbms.getG()
                );
            } else if ("X.509".equbls(key.getFormbt())) {
                // let SunJCE provider pbrse for us, then recurse
                try {
                    KeyFbctory fbctory = implGetSoftwbreFbctory();
                    key = (PublicKey)fbctory.trbnslbteKey(key);
                    return implTrbnslbtePublicKey(key);
                } cbtch (GenerblSecurityException e) {
                    throw new InvblidKeyException("Could not trbnslbte key", e);
                }
            } else {
                throw new InvblidKeyException("PublicKey must be instbnce "
                        + "of DHPublicKey or hbve X.509 encoding");
            }
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("Could not crebte DH public key", e);
        }
    }

    PrivbteKey implTrbnslbtePrivbteKey(PrivbteKey key)
            throws InvblidKeyException {
        try {
            if (key instbnceof DHPrivbteKey) {
                DHPrivbteKey dhKey = (DHPrivbteKey)key;
                DHPbrbmeterSpec pbrbms = dhKey.getPbrbms();
                return generbtePrivbte(
                    dhKey.getX(),
                    pbrbms.getP(),
                    pbrbms.getG()
                );
            } else if ("PKCS#8".equbls(key.getFormbt())) {
                // let SunJCE provider pbrse for us, then recurse
                try {
                    KeyFbctory fbctory = implGetSoftwbreFbctory();
                    key = (PrivbteKey)fbctory.trbnslbteKey(key);
                    return implTrbnslbtePrivbteKey(key);
                } cbtch (GenerblSecurityException e) {
                    throw new InvblidKeyException("Could not trbnslbte key", e);
                }
            } else {
                throw new InvblidKeyException("PrivbteKey must be instbnce "
                        + "of DHPrivbteKey or hbve PKCS#8 encoding");
            }
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("Could not crebte DH privbte key", e);
        }
    }

    // see JCA spec
    protected PublicKey engineGenerbtePublic(KeySpec keySpec)
            throws InvblidKeySpecException {
        token.ensureVblid();
        if (keySpec instbnceof X509EncodedKeySpec) {
            try {
                KeyFbctory fbctory = implGetSoftwbreFbctory();
                PublicKey key = fbctory.generbtePublic(keySpec);
                return implTrbnslbtePublicKey(key);
            } cbtch (GenerblSecurityException e) {
                throw new InvblidKeySpecException
                        ("Could not crebte DH public key", e);
            }
        }
        if (keySpec instbnceof DHPublicKeySpec == fblse) {
            throw new InvblidKeySpecException("Only DHPublicKeySpec bnd "
                + "X509EncodedKeySpec supported for DH public keys");
        }
        try {
            DHPublicKeySpec ds = (DHPublicKeySpec)keySpec;
            return generbtePublic(
                ds.getY(),
                ds.getP(),
                ds.getG()
            );
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeySpecException
                ("Could not crebte DH public key", e);
        }
    }

    // see JCA spec
    protected PrivbteKey engineGenerbtePrivbte(KeySpec keySpec)
            throws InvblidKeySpecException {
        token.ensureVblid();
        if (keySpec instbnceof PKCS8EncodedKeySpec) {
            try {
                KeyFbctory fbctory = implGetSoftwbreFbctory();
                PrivbteKey key = fbctory.generbtePrivbte(keySpec);
                return implTrbnslbtePrivbteKey(key);
            } cbtch (GenerblSecurityException e) {
                throw new InvblidKeySpecException
                        ("Could not crebte DH privbte key", e);
            }
        }
        if (keySpec instbnceof DHPrivbteKeySpec == fblse) {
            throw new InvblidKeySpecException("Only DHPrivbteKeySpec bnd "
                + "PKCS8EncodedKeySpec supported for DH privbte keys");
        }
        try {
            DHPrivbteKeySpec ds = (DHPrivbteKeySpec)keySpec;
            return generbtePrivbte(
                ds.getX(),
                ds.getP(),
                ds.getG()
            );
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeySpecException
                ("Could not crebte DH privbte key", e);
        }
    }

    privbte PublicKey generbtePublic(BigInteger y, BigInteger p, BigInteger g)
            throws PKCS11Exception {
        CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
            new CK_ATTRIBUTE(CKA_CLASS, CKO_PUBLIC_KEY),
            new CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_DH),
            new CK_ATTRIBUTE(CKA_VALUE, y),
            new CK_ATTRIBUTE(CKA_PRIME, p),
            new CK_ATTRIBUTE(CKA_BASE, g),
        };
        bttributes = token.getAttributes
                (O_IMPORT, CKO_PUBLIC_KEY, CKK_DH, bttributes);
        Session session = null;
        try {
            session = token.getObjSession();
            long keyID = token.p11.C_CrebteObject(session.id(), bttributes);
            return P11Key.publicKey
                (session, keyID, "DH", p.bitLength(), bttributes);
        } finblly {
            token.relebseSession(session);
        }
    }

    privbte PrivbteKey generbtePrivbte(BigInteger x, BigInteger p,
            BigInteger g) throws PKCS11Exception {
        CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
            new CK_ATTRIBUTE(CKA_CLASS, CKO_PRIVATE_KEY),
            new CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_DH),
            new CK_ATTRIBUTE(CKA_VALUE, x),
            new CK_ATTRIBUTE(CKA_PRIME, p),
            new CK_ATTRIBUTE(CKA_BASE, g),
        };
        bttributes = token.getAttributes
                (O_IMPORT, CKO_PRIVATE_KEY, CKK_DH, bttributes);
        Session session = null;
        try {
            session = token.getObjSession();
            long keyID = token.p11.C_CrebteObject(session.id(), bttributes);
            return P11Key.privbteKey
                (session, keyID, "DH", p.bitLength(), bttributes);
        } finblly {
            token.relebseSession(session);
        }
    }

    <T extends KeySpec> T implGetPublicKeySpec(P11Key key, Clbss<T> keySpec,
            Session[] session) throws PKCS11Exception, InvblidKeySpecException {
        if (DHPublicKeySpec.clbss.isAssignbbleFrom(keySpec)) {
            session[0] = token.getObjSession();
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE),
                new CK_ATTRIBUTE(CKA_PRIME),
                new CK_ATTRIBUTE(CKA_BASE),
            };
            token.p11.C_GetAttributeVblue(session[0].id(), key.keyID, bttributes);
            KeySpec spec = new DHPublicKeySpec(
                bttributes[0].getBigInteger(),
                bttributes[1].getBigInteger(),
                bttributes[2].getBigInteger()
            );
            return keySpec.cbst(spec);
        } else { // X.509 hbndled in superclbss
            throw new InvblidKeySpecException("Only DHPublicKeySpec bnd "
                + "X509EncodedKeySpec supported for DH public keys");
        }
    }

    <T extends KeySpec> T implGetPrivbteKeySpec(P11Key key, Clbss<T> keySpec,
            Session[] session) throws PKCS11Exception, InvblidKeySpecException {
        if (DHPrivbteKeySpec.clbss.isAssignbbleFrom(keySpec)) {
            session[0] = token.getObjSession();
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE),
                new CK_ATTRIBUTE(CKA_PRIME),
                new CK_ATTRIBUTE(CKA_BASE),
            };
            token.p11.C_GetAttributeVblue(session[0].id(), key.keyID, bttributes);
            KeySpec spec = new DHPrivbteKeySpec(
                bttributes[0].getBigInteger(),
                bttributes[1].getBigInteger(),
                bttributes[2].getBigInteger()
            );
            return keySpec.cbst(spec);
        } else { // PKCS#8 hbndled in superclbss
            throw new InvblidKeySpecException("Only DHPrivbteKeySpec "
                + "bnd PKCS8EncodedKeySpec supported for DH privbte keys");
        }
    }

    KeyFbctory implGetSoftwbreFbctory() throws GenerblSecurityException {
        return KeyFbctory.getInstbnce("DH", P11Util.getSunJceProvider());
    }

}
