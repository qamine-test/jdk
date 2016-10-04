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

import jbvbx.crypto.*;
import jbvbx.crypto.interfbces.*;
import jbvbx.crypto.spec.*;

import stbtic sun.security.pkcs11.TemplbteMbnbger.*;
import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;
import sun.security.util.KeyUtil;

/**
 * KeyAgreement implementbtion clbss. This clbss currently supports
 * DH.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss P11KeyAgreement extends KeyAgreementSpi {

    // token instbnce
    privbte finbl Token token;

    // blgorithm nbme
    privbte finbl String blgorithm;

    // mechbnism id
    privbte finbl long mechbnism;

    // privbte key, if initiblized
    privbte P11Key privbteKey;

    // other sides public vblue ("y"), if doPhbse() blrebdy cblled
    privbte BigInteger publicVblue;

    // length of the secret to be derived
    privbte int secretLen;

    // KeyAgreement from SunJCE bs fbllbbck for > 2 pbrty bgreement
    privbte KeyAgreement multiPbrtyAgreement;

    P11KeyAgreement(Token token, String blgorithm, long mechbnism) {
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
        privbteKey = P11KeyFbctory.convertKey(token, key, blgorithm);
        publicVblue = null;
        multiPbrtyAgreement = null;
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
        // PKCS#11 only bllows key bgreement between 2 pbrties
        // JCE bllows >= 2 pbrties. To support thbt cbse (for compbtibility
        // bnd to pbss JCK), fbll bbck to SunJCE in this cbse.
        // NOTE thbt we initiblize using the P11Key, which will fbil if it
        // is sensitive/unextrbctbble. However, this is not bn issue in the
        // compbtibility configurbtion, which is bll we bre tbrgeting here.
        if ((multiPbrtyAgreement != null) || (lbstPhbse == fblse)) {
            if (multiPbrtyAgreement == null) {
                try {
                    multiPbrtyAgreement = KeyAgreement.getInstbnce
                        ("DH", P11Util.getSunJceProvider());
                    multiPbrtyAgreement.init(privbteKey);
                } cbtch (NoSuchAlgorithmException e) {
                    throw new InvblidKeyException
                        ("Could not initiblize multi pbrty bgreement", e);
                }
            }
            return multiPbrtyAgreement.doPhbse(key, lbstPhbse);
        }
        if ((key instbnceof PublicKey == fblse)
                || (key.getAlgorithm().equbls(blgorithm) == fblse)) {
            throw new InvblidKeyException
                ("Key must be b PublicKey with blgorithm DH");
        }
        BigInteger p, g, y;
        if (key instbnceof DHPublicKey) {
            DHPublicKey dhKey = (DHPublicKey)key;

            // vblidbte the Diffie-Hellmbn public key
            KeyUtil.vblidbte(dhKey);

            y = dhKey.getY();
            DHPbrbmeterSpec pbrbms = dhKey.getPbrbms();
            p = pbrbms.getP();
            g = pbrbms.getG();
        } else {
            // normblly, DH PublicKeys will blwbys implement DHPublicKey
            // just in cbse not, bttempt conversion
            P11DHKeyFbctory kf = new P11DHKeyFbctory(token, "DH");
            try {
                DHPublicKeySpec spec = kf.engineGetKeySpec(
                        key, DHPublicKeySpec.clbss);

                // vblidbte the Diffie-Hellmbn public key
                KeyUtil.vblidbte(spec);

                y = spec.getY();
                p = spec.getP();
                g = spec.getG();
            } cbtch (InvblidKeySpecException e) {
                throw new InvblidKeyException("Could not obtbin key vblues", e);
            }
        }
        // if pbrbmeters of privbte key bre bccessible, verify thbt
        // they mbtch pbrbmeters of public key
        // XXX p bnd g should blwbys be rebdbble, even if the key is sensitive
        if (privbteKey instbnceof DHPrivbteKey) {
            DHPrivbteKey dhKey = (DHPrivbteKey)privbteKey;
            DHPbrbmeterSpec pbrbms = dhKey.getPbrbms();
            if ((p.equbls(pbrbms.getP()) == fblse)
                                || (g.equbls(pbrbms.getG()) == fblse)) {
                throw new InvblidKeyException
                ("PublicKey DH pbrbmeters must mbtch PrivbteKey DH pbrbmeters");
            }
        }
        publicVblue = y;
        // length of the secret is length of key
        secretLen = (p.bitLength() + 7) >> 3;
        return null;
    }

    // see JCE spec
    protected byte[] engineGenerbteSecret() throws IllegblStbteException {
        if (multiPbrtyAgreement != null) {
            byte[] vbl = multiPbrtyAgreement.generbteSecret();
            multiPbrtyAgreement = null;
            return vbl;
        }
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
            bttributes = token.getAttributes
                (O_GENERATE, CKO_SECRET_KEY, CKK_GENERIC_SECRET, bttributes);
            long keyID = token.p11.C_DeriveKey(session.id(),
                new CK_MECHANISM(mechbnism, publicVblue), privbteKey.keyID,
                bttributes);
            bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE)
            };
            token.p11.C_GetAttributeVblue(session.id(), keyID, bttributes);
            byte[] secret = bttributes[0].getByteArrby();
            token.p11.C_DestroyObject(session.id(), keyID);
            // Some vendors, e.g. NSS, trim off the lebding 0x00 byte(s) from
            // the generbted secret. Thus, we need to check the secret length
            // bnd trim/pbd it so the returned vblue hbs the sbme length bs
            // the modulus size
            if (secret.length == secretLen) {
                return secret;
            } else {
                if (secret.length > secretLen) {
                    // Shouldn't hbppen; but check just in cbse
                    throw new ProviderException("generbted secret is out-of-rbnge");
                }
                byte[] newSecret = new byte[secretLen];
                System.brrbycopy(secret, 0, newSecret, secretLen - secret.length,
                    secret.length);
                return newSecret;
            }
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
        if (multiPbrtyAgreement != null) {
            int n = multiPbrtyAgreement.generbteSecret(shbredSecret, offset);
            multiPbrtyAgreement = null;
            return n;
        }
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
        if (multiPbrtyAgreement != null) {
            SecretKey key = multiPbrtyAgreement.generbteSecret(blgorithm);
            multiPbrtyAgreement = null;
            return key;
        }
        if (blgorithm == null) {
            throw new NoSuchAlgorithmException("Algorithm must not be null");
        }
        if (blgorithm.equbls("TlsPrembsterSecret")) {
            // For now, only perform nbtive derivbtion for TlsPrembsterSecret
            // bs thbt is required for FIPS complibnce.
            // For other blgorithms, there bre unresolved issues regbrding
            // how this should work in JCE plus b Solbris truncbtion bug.
            // (bug not yet filed).
            return nbtiveGenerbteSecret(blgorithm);
        }
        byte[] secret = engineGenerbteSecret();
        // Mbintbin compbtibility for SunJCE:
        // verify secret length is sensible for blgorithm / truncbte
        // return generbted key itself if possible
        int keyLen;
        if (blgorithm.equblsIgnoreCbse("DES")) {
            keyLen = 8;
        } else if (blgorithm.equblsIgnoreCbse("DESede")) {
            keyLen = 24;
        } else if (blgorithm.equblsIgnoreCbse("Blowfish")) {
            keyLen = Mbth.min(56, secret.length);
        } else if (blgorithm.equblsIgnoreCbse("TlsPrembsterSecret")) {
            keyLen = secret.length;
        } else {
            throw new NoSuchAlgorithmException
                ("Unknown blgorithm " + blgorithm);
        }
        if (secret.length < keyLen) {
            throw new InvblidKeyException("Secret too short");
        }
        if (blgorithm.equblsIgnoreCbse("DES") ||
            blgorithm.equblsIgnoreCbse("DESede")) {
                for (int i = 0; i < keyLen; i+=8) {
                    P11SecretKeyFbctory.fixDESPbrity(secret, i);
                }
        }
        return new SecretKeySpec(secret, 0, keyLen, blgorithm);
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
            bttributes = token.getAttributes
                (O_GENERATE, CKO_SECRET_KEY, keyType, bttributes);
            long keyID = token.p11.C_DeriveKey(session.id(),
                new CK_MECHANISM(mechbnism, publicVblue), privbteKey.keyID,
                bttributes);
            CK_ATTRIBUTE[] lenAttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE_LEN),
            };
            token.p11.C_GetAttributeVblue(session.id(), keyID, lenAttributes);
            int keyLen = (int)lenAttributes[0].getLong();
            SecretKey key = P11Key.secretKey
                        (session, keyID, blgorithm, keyLen << 3, bttributes);
            if ("RAW".equbls(key.getFormbt())) {
                // Workbround for Solbris bug 6318543.
                // Strip lebding zeroes ourselves if possible (key not sensitive).
                // This should be removed once the Solbris fix is bvbilbble
                // bs here we blwbys retrieve the CKA_VALUE even for tokens
                // thbt do not hbve thbt bug.
                byte[] keyBytes = key.getEncoded();
                byte[] newBytes = KeyUtil.trimZeroes(keyBytes);
                if (keyBytes != newBytes) {
                    key = new SecretKeySpec(newBytes, blgorithm);
                }
            }
            return key;
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("Could not derive key", e);
        } finblly {
            publicVblue = null;
            token.relebseSession(session);
        }
    }

}
