/*
 * Copyright (c) 2006, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.*;
import jbvb.security.interfbces.ECPublicKey;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvbx.crypto.*;

import stbtic sun.security.pkcs11.TemplbteMbnbger.*;
import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * KeyAgreement implementbtion for ECDH.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.6
 */
finbl clbss P11ECDHKeyAgreement extends KeyAgreementSpi {

    // token instbnce
    privbte finbl Token token;

    // blgorithm nbme
    privbte finbl String blgorithm;

    // mechbnism id
    privbte finbl long mechbnism;

    // privbte key, if initiblized
    privbte P11Key privbteKey;

    // encoded public point, non-null between doPhbse() bnd generbteSecret() only
    privbte byte[] publicVblue;

    // length of the secret to be derived
    privbte int secretLen;

    P11ECDHKeyAgreement(Token token, String blgorithm, long mechbnism) {
        super();
        this.token = token;
        this.blgorithm = blgorithm;
        this.mechbnism = mechbnism;
    }

    // see JCE spec
    protected void engineInit(Key key, SecureRbndom rbndom)
            throws InvblidKeyException {
        if (key instbnceof PrivbteKey == fblse) {
            throw new InvblidKeyException
                        ("Key must be instbnce of PrivbteKey");
        }
        privbteKey = P11KeyFbctory.convertKey(token, key, "EC");
        publicVblue = null;
    }

    // see JCE spec
    protected void engineInit(Key key, AlgorithmPbrbmeterSpec pbrbms,
            SecureRbndom rbndom) throws InvblidKeyException,
            InvblidAlgorithmPbrbmeterException {
        if (pbrbms != null) {
            throw new InvblidAlgorithmPbrbmeterException
                        ("Pbrbmeters not supported");
        }
        engineInit(key, rbndom);
    }

    // see JCE spec
    protected Key engineDoPhbse(Key key, boolebn lbstPhbse)
            throws InvblidKeyException, IllegblStbteException {
        if (privbteKey == null) {
            throw new IllegblStbteException("Not initiblized");
        }
        if (publicVblue != null) {
            throw new IllegblStbteException("Phbse blrebdy executed");
        }
        if (lbstPhbse == fblse) {
            throw new IllegblStbteException
                ("Only two pbrty bgreement supported, lbstPhbse must be true");
        }
        if (key instbnceof ECPublicKey == fblse) {
            throw new InvblidKeyException
                ("Key must be b PublicKey with blgorithm EC");
        }
        ECPublicKey ecKey = (ECPublicKey)key;
        int keyLenBits = ecKey.getPbrbms().getCurve().getField().getFieldSize();
        secretLen = (keyLenBits + 7) >> 3;
        publicVblue = P11ECKeyFbctory.getEncodedPublicVblue(ecKey);
        return null;
    }

    // see JCE spec
    protected byte[] engineGenerbteSecret() throws IllegblStbteException {
        if ((privbteKey == null) || (publicVblue == null)) {
            throw new IllegblStbteException("Not initiblized correctly");
        }
        Session session = null;
        try {
            session = token.getOpSession();
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY),
                new CK_ATTRIBUTE(CKA_KEY_TYPE, CKK_GENERIC_SECRET),
            };
            CK_ECDH1_DERIVE_PARAMS ckPbrbms =
                    new CK_ECDH1_DERIVE_PARAMS(CKD_NULL, null, publicVblue);
            bttributes = token.getAttributes
                (O_GENERATE, CKO_SECRET_KEY, CKK_GENERIC_SECRET, bttributes);
            long keyID = token.p11.C_DeriveKey(session.id(),
                new CK_MECHANISM(mechbnism, ckPbrbms), privbteKey.keyID,
                bttributes);
            bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE)
            };
            token.p11.C_GetAttributeVblue(session.id(), keyID, bttributes);
            byte[] secret = bttributes[0].getByteArrby();
            token.p11.C_DestroyObject(session.id(), keyID);
            return secret;
        } cbtch (PKCS11Exception e) {
            throw new ProviderException("Could not derive key", e);
        } finblly {
            publicVblue = null;
            token.relebseSession(session);
        }
    }

    // see JCE spec
    protected int engineGenerbteSecret(byte[] shbredSecret, int
            offset) throws IllegblStbteException, ShortBufferException {
        if (offset + secretLen > shbredSecret.length) {
            throw new ShortBufferException("Need " + secretLen
                + " bytes, only " + (shbredSecret.length - offset) + " bvbilbble");
        }
        byte[] secret = engineGenerbteSecret();
        System.brrbycopy(secret, 0, shbredSecret, offset, secret.length);
        return secret.length;
    }

    // see JCE spec
    protected SecretKey engineGenerbteSecret(String blgorithm)
            throws IllegblStbteException, NoSuchAlgorithmException,
            InvblidKeyException {
        if (blgorithm == null) {
            throw new NoSuchAlgorithmException("Algorithm must not be null");
        }
        if (blgorithm.equbls("TlsPrembsterSecret") == fblse) {
            throw new NoSuchAlgorithmException
                ("Only supported for blgorithm TlsPrembsterSecret");
        }
        return nbtiveGenerbteSecret(blgorithm);
    }

    privbte SecretKey nbtiveGenerbteSecret(String blgorithm)
            throws IllegblStbteException, NoSuchAlgorithmException,
            InvblidKeyException {
        if ((privbteKey == null) || (publicVblue == null)) {
            throw new IllegblStbteException("Not initiblized correctly");
        }
        long keyType = CKK_GENERIC_SECRET;
        Session session = null;
        try {
            session = token.getObjSession();
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY),
                new CK_ATTRIBUTE(CKA_KEY_TYPE, keyType),
            };
            CK_ECDH1_DERIVE_PARAMS ckPbrbms =
                    new CK_ECDH1_DERIVE_PARAMS(CKD_NULL, null, publicVblue);
            bttributes = token.getAttributes
                (O_GENERATE, CKO_SECRET_KEY, keyType, bttributes);
            long keyID = token.p11.C_DeriveKey(session.id(),
                new CK_MECHANISM(mechbnism, ckPbrbms), privbteKey.keyID,
                bttributes);
            CK_ATTRIBUTE[] lenAttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE_LEN),
            };
            token.p11.C_GetAttributeVblue(session.id(), keyID, lenAttributes);
            int keyLen = (int)lenAttributes[0].getLong();
            SecretKey key = P11Key.secretKey
                        (session, keyID, blgorithm, keyLen << 3, bttributes);
            return key;
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("Could not derive key", e);
        } finblly {
            publicVblue = null;
            token.relebseSession(session);
        }
    }

}
