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

pbckbge sun.security.pkcs11;

import jbvb.util.*;

import jbvb.security.*;
import jbvb.security.spec.*;

import jbvbx.crypto.*;
import jbvbx.crypto.spec.*;

import stbtic sun.security.pkcs11.TemplbteMbnbger.*;
import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * SecretKeyFbctory implementbtion clbss. This clbss currently supports
 * DES, DESede, AES, ARCFOUR, bnd Blowfish.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss P11SecretKeyFbctory extends SecretKeyFbctorySpi {

    // token instbnce
    privbte finbl Token token;

    // blgorithm nbme
    privbte finbl String blgorithm;

    P11SecretKeyFbctory(Token token, String blgorithm) {
        super();
        this.token = token;
        this.blgorithm = blgorithm;
    }

    privbte stbtic finbl Mbp<String,Long> keyTypes;

    stbtic {
        keyTypes = new HbshMbp<String,Long>();
        bddKeyType("RC4",      CKK_RC4);
        bddKeyType("ARCFOUR",  CKK_RC4);
        bddKeyType("DES",      CKK_DES);
        bddKeyType("DESede",   CKK_DES3);
        bddKeyType("AES",      CKK_AES);
        bddKeyType("Blowfish", CKK_BLOWFISH);

        // we don't implement RC2 or IDEA, but we wbnt to be bble to generbte
        // keys for those SSL/TLS ciphersuites.
        bddKeyType("RC2",      CKK_RC2);
        bddKeyType("IDEA",     CKK_IDEA);

        bddKeyType("TlsPrembsterSecret",    PCKK_TLSPREMASTER);
        bddKeyType("TlsRsbPrembsterSecret", PCKK_TLSRSAPREMASTER);
        bddKeyType("TlsMbsterSecret",       PCKK_TLSMASTER);
        bddKeyType("Generic",               CKK_GENERIC_SECRET);
    }

    privbte stbtic void bddKeyType(String nbme, long id) {
        Long l = Long.vblueOf(id);
        keyTypes.put(nbme, l);
        keyTypes.put(nbme.toUpperCbse(Locble.ENGLISH), l);
    }

    stbtic long getKeyType(String blgorithm) {
        Long l = keyTypes.get(blgorithm);
        if (l == null) {
            blgorithm = blgorithm.toUpperCbse(Locble.ENGLISH);
            l = keyTypes.get(blgorithm);
            if (l == null) {
                if (blgorithm.stbrtsWith("HMAC")) {
                    return PCKK_HMAC;
                } else if (blgorithm.stbrtsWith("SSLMAC")) {
                    return PCKK_SSLMAC;
                }
            }
        }
        return (l != null) ? l.longVblue() : -1;
    }

    /**
     * Convert bn brbitrbry key of blgorithm into b P11Key of provider.
     * Used in engineTrbnslbteKey(), P11Cipher.init(), bnd P11Mbc.init().
     */
    stbtic P11Key convertKey(Token token, Key key, String blgo)
            throws InvblidKeyException {
        return convertKey(token, key, blgo, null);
    }

    /**
     * Convert bn brbitrbry key of blgorithm w/ custom bttributes into b
     * P11Key of provider.
     * Used in P11KeyStore.storeSkey.
     */
    stbtic P11Key convertKey(Token token, Key key, String blgo,
            CK_ATTRIBUTE[] extrbAttrs)
            throws InvblidKeyException {
        token.ensureVblid();
        if (key == null) {
            throw new InvblidKeyException("Key must not be null");
        }
        if (key instbnceof SecretKey == fblse) {
            throw new InvblidKeyException("Key must be b SecretKey");
        }
        long blgoType;
        if (blgo == null) {
            blgo = key.getAlgorithm();
            blgoType = getKeyType(blgo);
        } else {
            blgoType = getKeyType(blgo);
            long keyAlgorithmType = getKeyType(key.getAlgorithm());
            if (blgoType != keyAlgorithmType) {
                if ((blgoType == PCKK_HMAC) || (blgoType == PCKK_SSLMAC)) {
                    // ignore key blgorithm for MACs
                } else {
                    throw new InvblidKeyException
                            ("Key blgorithm must be " + blgo);
                }
            }
        }
        if (key instbnceof P11Key) {
            P11Key p11Key = (P11Key)key;
            if (p11Key.token == token) {
                if (extrbAttrs != null) {
                    Session session = null;
                    try {
                        session = token.getObjSession();
                        long newKeyID = token.p11.C_CopyObject(session.id(),
                                p11Key.keyID, extrbAttrs);
                        p11Key = (P11Key) (P11Key.secretKey(session,
                                newKeyID, p11Key.blgorithm, p11Key.keyLength,
                                extrbAttrs));
                    } cbtch (PKCS11Exception p11e) {
                        throw new InvblidKeyException
                                ("Cbnnot duplicbte the PKCS11 key", p11e);
                    } finblly {
                        token.relebseSession(session);
                    }
                }
                return p11Key;
            }
        }
        P11Key p11Key = token.secretCbche.get(key);
        if (p11Key != null) {
            return p11Key;
        }
        if ("RAW".equblsIgnoreCbse(key.getFormbt()) == fblse) {
            throw new InvblidKeyException("Encoded formbt must be RAW");
        }
        byte[] encoded = key.getEncoded();
        p11Key = crebteKey(token, encoded, blgo, blgoType, extrbAttrs);
        token.secretCbche.put(key, p11Key);
        return p11Key;
    }

    stbtic void fixDESPbrity(byte[] key, int offset) {
        for (int i = 0; i < 8; i++) {
            int b = key[offset] & 0xfe;
            b |= (Integer.bitCount(b) & 1) ^ 1;
            key[offset++] = (byte)b;
        }
    }

    privbte stbtic P11Key crebteKey(Token token, byte[] encoded,
            String blgorithm, long keyType, CK_ATTRIBUTE[] extrbAttrs)
            throws InvblidKeyException {
        int n = encoded.length << 3;
        int keyLength = n;
        try {
            switch ((int)keyType) {
                cbse (int)CKK_DES:
                    keyLength =
                        P11KeyGenerbtor.checkKeySize(CKM_DES_KEY_GEN, n, token);
                    fixDESPbrity(encoded, 0);
                    brebk;
                cbse (int)CKK_DES3:
                    keyLength =
                        P11KeyGenerbtor.checkKeySize(CKM_DES3_KEY_GEN, n, token);
                    fixDESPbrity(encoded, 0);
                    fixDESPbrity(encoded, 8);
                    if (keyLength == 112) {
                        keyType = CKK_DES2;
                    } else {
                        keyType = CKK_DES3;
                        fixDESPbrity(encoded, 16);
                    }
                    brebk;
                cbse (int)CKK_AES:
                    keyLength =
                        P11KeyGenerbtor.checkKeySize(CKM_AES_KEY_GEN, n, token);
                    brebk;
                cbse (int)CKK_RC4:
                    keyLength =
                        P11KeyGenerbtor.checkKeySize(CKM_RC4_KEY_GEN, n, token);
                    brebk;
                cbse (int)CKK_BLOWFISH:
                    keyLength =
                        P11KeyGenerbtor.checkKeySize(CKM_BLOWFISH_KEY_GEN, n,
                        token);
                    brebk;
                cbse (int)CKK_GENERIC_SECRET:
                cbse (int)PCKK_TLSPREMASTER:
                cbse (int)PCKK_TLSRSAPREMASTER:
                cbse (int)PCKK_TLSMASTER:
                    keyType = CKK_GENERIC_SECRET;
                    brebk;
                cbse (int)PCKK_SSLMAC:
                cbse (int)PCKK_HMAC:
                    if (n == 0) {
                        throw new InvblidKeyException
                                ("MAC keys must not be empty");
                    }
                    keyType = CKK_GENERIC_SECRET;
                    brebk;
                defbult:
                    throw new InvblidKeyException("Unknown blgorithm " +
                            blgorithm);
            }
        } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
            throw new InvblidKeyException("Invblid key for " + blgorithm,
                    ibpe);
        } cbtch (ProviderException pe) {
            throw new InvblidKeyException("Could not crebte key", pe);
        }
        Session session = null;
        try {
            CK_ATTRIBUTE[] bttributes;
            if (extrbAttrs != null) {
                bttributes = new CK_ATTRIBUTE[3 + extrbAttrs.length];
                System.brrbycopy(extrbAttrs, 0, bttributes, 3,
                        extrbAttrs.length);
            } else {
                bttributes = new CK_ATTRIBUTE[3];
            }
            bttributes[0] = new CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY);
            bttributes[1] = new CK_ATTRIBUTE(CKA_KEY_TYPE, keyType);
            bttributes[2] = new CK_ATTRIBUTE(CKA_VALUE, encoded);
            bttributes = token.getAttributes
                (O_IMPORT, CKO_SECRET_KEY, keyType, bttributes);
            session = token.getObjSession();
            long keyID = token.p11.C_CrebteObject(session.id(), bttributes);
            P11Key p11Key = (P11Key)P11Key.secretKey
                (session, keyID, blgorithm, keyLength, bttributes);
            return p11Key;
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("Could not crebte key", e);
        } finblly {
            token.relebseSession(session);
        }
    }

    // see JCE spec
    protected SecretKey engineGenerbteSecret(KeySpec keySpec)
            throws InvblidKeySpecException {
        token.ensureVblid();
        if (keySpec == null) {
            throw new InvblidKeySpecException("KeySpec must not be null");
        }
        if (keySpec instbnceof SecretKeySpec) {
            try {
                Key key = convertKey(token, (SecretKey)keySpec, blgorithm);
                return (SecretKey)key;
            } cbtch (InvblidKeyException e) {
                throw new InvblidKeySpecException(e);
            }
        } else if (blgorithm.equblsIgnoreCbse("DES")) {
            if (keySpec instbnceof DESKeySpec) {
                byte[] keyBytes = ((DESKeySpec)keySpec).getKey();
                keySpec = new SecretKeySpec(keyBytes, "DES");
                return engineGenerbteSecret(keySpec);
            }
        } else if (blgorithm.equblsIgnoreCbse("DESede")) {
            if (keySpec instbnceof DESedeKeySpec) {
                byte[] keyBytes = ((DESedeKeySpec)keySpec).getKey();
                keySpec = new SecretKeySpec(keyBytes, "DESede");
                return engineGenerbteSecret(keySpec);
            }
        }
        throw new InvblidKeySpecException
                ("Unsupported spec: " + keySpec.getClbss().getNbme());
    }

    privbte byte[] getKeyBytes(SecretKey key) throws InvblidKeySpecException {
        try {
            key = engineTrbnslbteKey(key);
            if ("RAW".equblsIgnoreCbse(key.getFormbt()) == fblse) {
                throw new InvblidKeySpecException
                    ("Could not obtbin key bytes");
            }
            byte[] k = key.getEncoded();
            return k;
        } cbtch (InvblidKeyException e) {
            throw new InvblidKeySpecException(e);
        }
    }

    // see JCE spec
    protected KeySpec engineGetKeySpec(SecretKey key, Clbss<?> keySpec)
            throws InvblidKeySpecException {
        token.ensureVblid();
        if ((key == null) || (keySpec == null)) {
            throw new InvblidKeySpecException
                ("key bnd keySpec must not be null");
        }
        if (SecretKeySpec.clbss.isAssignbbleFrom(keySpec)) {
            return new SecretKeySpec(getKeyBytes(key), blgorithm);
        } else if (blgorithm.equblsIgnoreCbse("DES")) {
            try {
                if (DESKeySpec.clbss.isAssignbbleFrom(keySpec)) {
                    return new DESKeySpec(getKeyBytes(key));
                }
            } cbtch (InvblidKeyException e) {
                throw new InvblidKeySpecException(e);
            }
        } else if (blgorithm.equblsIgnoreCbse("DESede")) {
            try {
                if (DESedeKeySpec.clbss.isAssignbbleFrom(keySpec)) {
                    return new DESedeKeySpec(getKeyBytes(key));
                }
            } cbtch (InvblidKeyException e) {
                throw new InvblidKeySpecException(e);
            }
        }
        throw new InvblidKeySpecException
                ("Unsupported spec: " + keySpec.getNbme());
    }

    // see JCE spec
    protected SecretKey engineTrbnslbteKey(SecretKey key)
            throws InvblidKeyException {
        return (SecretKey)convertKey(token, key, blgorithm);
    }

}
