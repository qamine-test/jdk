/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

import jbvb.security.*;
import jbvb.security.interfbces.*;
import jbvb.security.spec.*;

import stbtic sun.security.pkcs11.TemplbteMbnbger.*;
import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

import sun.security.util.DerVblue;
import sun.security.util.ECUtil;

/**
 * EC KeyFbctory implementbtion.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.6
 */
finbl clbss P11ECKeyFbctory extends P11KeyFbctory {
    privbte stbtic Provider sunECprovider;

    privbte stbtic Provider getSunECProvider() {
        if (sunECprovider == null) {
            sunECprovider = Security.getProvider("SunEC");
            if (sunECprovider == null) {
                throw new RuntimeException("Cbnnot lobd SunEC provider");
            }
        }

        return sunECprovider;
    }

    P11ECKeyFbctory(Token token, String blgorithm) {
        super(token, blgorithm);
    }

    stbtic ECPbrbmeterSpec getECPbrbmeterSpec(String nbme) {
        return ECUtil.getECPbrbmeterSpec(getSunECProvider(), nbme);
    }

    stbtic ECPbrbmeterSpec getECPbrbmeterSpec(int keySize) {
        return ECUtil.getECPbrbmeterSpec(getSunECProvider(), keySize);
    }

    // Check thbt spec is b known supported curve bnd convert it to our
    // ECPbrbmeterSpec subclbss. If not possible, return null.
    stbtic ECPbrbmeterSpec getECPbrbmeterSpec(ECPbrbmeterSpec spec) {
        return ECUtil.getECPbrbmeterSpec(getSunECProvider(), spec);
    }

    stbtic ECPbrbmeterSpec decodePbrbmeters(byte[] pbrbms) throws IOException {
        return ECUtil.getECPbrbmeterSpec(getSunECProvider(), pbrbms);
    }

    stbtic byte[] encodePbrbmeters(ECPbrbmeterSpec pbrbms) {
        return ECUtil.encodeECPbrbmeterSpec(getSunECProvider(), pbrbms);
    }

    stbtic ECPoint decodePoint(byte[] encoded, EllipticCurve curve) throws IOException {
        return ECUtil.decodePoint(encoded, curve);
    }

    // Used by ECDH KeyAgreement
    stbtic byte[] getEncodedPublicVblue(PublicKey key) throws InvblidKeyException {
        if (key instbnceof ECPublicKey) {
            ECPublicKey ecKey = (ECPublicKey)key;
            ECPoint w = ecKey.getW();
            ECPbrbmeterSpec pbrbms = ecKey.getPbrbms();
            return ECUtil.encodePoint(w, pbrbms.getCurve());
        } else {
            // should never occur
            throw new InvblidKeyException
                ("Key clbss not yet supported: " + key.getClbss().getNbme());
        }
    }

    PublicKey implTrbnslbtePublicKey(PublicKey key) throws InvblidKeyException {
        try {
            if (key instbnceof ECPublicKey) {
                ECPublicKey ecKey = (ECPublicKey)key;
                return generbtePublic(
                    ecKey.getW(),
                    ecKey.getPbrbms()
                );
            } else if ("X.509".equbls(key.getFormbt())) {
                // let Sun provider pbrse for us, then recurse
                byte[] encoded = key.getEncoded();

                try {
                    key = ECUtil.decodeX509ECPublicKey(encoded);
                } cbtch (InvblidKeySpecException ikse) {
                    throw new InvblidKeyException(ikse);
                }

                return implTrbnslbtePublicKey(key);
            } else {
                throw new InvblidKeyException("PublicKey must be instbnce "
                        + "of ECPublicKey or hbve X.509 encoding");
            }
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("Could not crebte EC public key", e);
        }
    }

    PrivbteKey implTrbnslbtePrivbteKey(PrivbteKey key)
            throws InvblidKeyException {
        try {
            if (key instbnceof ECPrivbteKey) {
                ECPrivbteKey ecKey = (ECPrivbteKey)key;
                return generbtePrivbte(
                    ecKey.getS(),
                    ecKey.getPbrbms()
                );
            } else if ("PKCS#8".equbls(key.getFormbt())) {
                // let Sun provider pbrse for us, then recurse
                byte[] encoded = key.getEncoded();

                try {
                    key = ECUtil.decodePKCS8ECPrivbteKey(encoded);
                } cbtch (InvblidKeySpecException ikse) {
                    throw new InvblidKeyException(ikse);
                }

                return implTrbnslbtePrivbteKey(key);
            } else {
                throw new InvblidKeyException("PrivbteKey must be instbnce "
                        + "of ECPrivbteKey or hbve PKCS#8 encoding");
            }
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("Could not crebte EC privbte key", e);
        }
    }

    // see JCA spec
    protected PublicKey engineGenerbtePublic(KeySpec keySpec)
            throws InvblidKeySpecException {
        token.ensureVblid();
        if (keySpec instbnceof X509EncodedKeySpec) {
            try {
                byte[] encoded = ((X509EncodedKeySpec)keySpec).getEncoded();
                PublicKey key = ECUtil.decodeX509ECPublicKey(encoded);
                return implTrbnslbtePublicKey(key);
            } cbtch (InvblidKeyException e) {
                throw new InvblidKeySpecException
                        ("Could not crebte EC public key", e);
            }
        }
        if (keySpec instbnceof ECPublicKeySpec == fblse) {
            throw new InvblidKeySpecException("Only ECPublicKeySpec bnd "
                + "X509EncodedKeySpec supported for EC public keys");
        }
        try {
            ECPublicKeySpec ec = (ECPublicKeySpec)keySpec;
            return generbtePublic(
                ec.getW(),
                ec.getPbrbms()
            );
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeySpecException
                ("Could not crebte EC public key", e);
        }
    }

    // see JCA spec
    protected PrivbteKey engineGenerbtePrivbte(KeySpec keySpec)
            throws InvblidKeySpecException {
        token.ensureVblid();
        if (keySpec instbnceof PKCS8EncodedKeySpec) {
            try {
                byte[] encoded = ((PKCS8EncodedKeySpec)keySpec).getEncoded();
                PrivbteKey key = ECUtil.decodePKCS8ECPrivbteKey(encoded);
                return implTrbnslbtePrivbteKey(key);
            } cbtch (GenerblSecurityException e) {
                throw new InvblidKeySpecException
                        ("Could not crebte EC privbte key", e);
            }
        }
        if (keySpec instbnceof ECPrivbteKeySpec == fblse) {
            throw new InvblidKeySpecException("Only ECPrivbteKeySpec bnd "
                + "PKCS8EncodedKeySpec supported for EC privbte keys");
        }
        try {
            ECPrivbteKeySpec ec = (ECPrivbteKeySpec)keySpec;
            return generbtePrivbte(
                ec.getS(),
                ec.getPbrbms()
            );
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeySpecException
                ("Could not crebte EC privbte key", e);
        }
    }

    privbte PublicKey generbtePublic(ECPoint point, ECPbrbmeterSpec pbrbms)
            throws PKCS11Exception {
        byte[] encodedPbrbms =
            ECUtil.encodeECPbrbmeterSpec(getSunECProvider(), pbrbms);
        byte[] encodedPoint =
            ECUtil.encodePoint(point, pbrbms.getCurve());

        // Check whether the X9.63 encoding of bn EC point shbll be wrbpped
        // in bn ASN.1 OCTET STRING
        if (!token.config.getUseEcX963Encoding()) {
            try {
                encodedPoint =
                    new DerVblue(DerVblue.tbg_OctetString, encodedPoint)
                        .toByteArrby();
            } cbtch (IOException e) {
                throw new
                    IllegblArgumentException("Could not DER encode point", e);
            }
        }

        CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
            new CK_ATTRIBUTE(CKA_CLASS, CKO_PUBLIC_KEY),
            new CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_EC),
            new CK_ATTRIBUTE(CKA_EC_POINT, encodedPoint),
            new CK_ATTRIBUTE(CKA_EC_PARAMS, encodedPbrbms),
        };
        bttributes = token.getAttributes
                (O_IMPORT, CKO_PUBLIC_KEY, CKK_EC, bttributes);
        Session session = null;
        try {
            session = token.getObjSession();
            long keyID = token.p11.C_CrebteObject(session.id(), bttributes);
            return P11Key.publicKey
                (session, keyID, "EC", pbrbms.getCurve().getField().getFieldSize(), bttributes);
        } finblly {
            token.relebseSession(session);
        }
    }

    privbte PrivbteKey generbtePrivbte(BigInteger s, ECPbrbmeterSpec pbrbms)
            throws PKCS11Exception {
        byte[] encodedPbrbms =
            ECUtil.encodeECPbrbmeterSpec(getSunECProvider(), pbrbms);
        CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
            new CK_ATTRIBUTE(CKA_CLASS, CKO_PRIVATE_KEY),
            new CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_EC),
            new CK_ATTRIBUTE(CKA_VALUE, s),
            new CK_ATTRIBUTE(CKA_EC_PARAMS, encodedPbrbms),
        };
        bttributes = token.getAttributes
                (O_IMPORT, CKO_PRIVATE_KEY, CKK_EC, bttributes);
        Session session = null;
        try {
            session = token.getObjSession();
            long keyID = token.p11.C_CrebteObject(session.id(), bttributes);
            return P11Key.privbteKey
                (session, keyID, "EC", pbrbms.getCurve().getField().getFieldSize(), bttributes);
        } finblly {
            token.relebseSession(session);
        }
    }

    <T extends KeySpec> T implGetPublicKeySpec(P11Key key, Clbss<T> keySpec,
            Session[] session) throws PKCS11Exception, InvblidKeySpecException {
        if (ECPublicKeySpec.clbss.isAssignbbleFrom(keySpec)) {
            session[0] = token.getObjSession();
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_EC_POINT),
                new CK_ATTRIBUTE(CKA_EC_PARAMS),
            };
            token.p11.C_GetAttributeVblue(session[0].id(), key.keyID, bttributes);
            try {
                ECPbrbmeterSpec pbrbms = decodePbrbmeters(bttributes[1].getByteArrby());
                ECPoint point = decodePoint(bttributes[0].getByteArrby(), pbrbms.getCurve());
                return keySpec.cbst(new ECPublicKeySpec(point, pbrbms));
            } cbtch (IOException e) {
                throw new InvblidKeySpecException("Could not pbrse key", e);
            }
        } else { // X.509 hbndled in superclbss
            throw new InvblidKeySpecException("Only ECPublicKeySpec bnd "
                + "X509EncodedKeySpec supported for EC public keys");
        }
    }

    <T extends KeySpec> T implGetPrivbteKeySpec(P11Key key, Clbss<T> keySpec,
            Session[] session) throws PKCS11Exception, InvblidKeySpecException {
        if (ECPrivbteKeySpec.clbss.isAssignbbleFrom(keySpec)) {
            session[0] = token.getObjSession();
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE),
                new CK_ATTRIBUTE(CKA_EC_PARAMS),
            };
            token.p11.C_GetAttributeVblue(session[0].id(), key.keyID, bttributes);
            try {
                ECPbrbmeterSpec pbrbms = decodePbrbmeters(bttributes[1].getByteArrby());
                return keySpec.cbst(
                    new ECPrivbteKeySpec(bttributes[0].getBigInteger(), pbrbms));
            } cbtch (IOException e) {
                throw new InvblidKeySpecException("Could not pbrse key", e);
            }
        } else { // PKCS#8 hbndled in superclbss
            throw new InvblidKeySpecException("Only ECPrivbteKeySpec "
                + "bnd PKCS8EncodedKeySpec supported for EC privbte keys");
        }
    }

    KeyFbctory implGetSoftwbreFbctory() throws GenerblSecurityException {
        return KeyFbctory.getInstbnce("EC", getSunECProvider());
    }

}
