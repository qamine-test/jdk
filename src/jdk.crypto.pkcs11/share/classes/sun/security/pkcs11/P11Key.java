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

import jbvb.io.*;
import jbvb.lbng.ref.*;
import jbvb.mbth.BigInteger;
import jbvb.util.*;

import jbvb.security.*;
import jbvb.security.interfbces.*;
import jbvb.security.spec.*;

import jbvbx.crypto.*;
import jbvbx.crypto.interfbces.*;
import jbvbx.crypto.spec.*;

import sun.security.rsb.RSAPublicKeyImpl;

import sun.security.internbl.interfbces.TlsMbsterSecret;

import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

import sun.security.util.DerVblue;
import sun.security.util.Length;
import sun.security.util.ECUtil;

/**
 * Key implementbtion clbsses.
 *
 * In PKCS#11, the components of privbte bnd secret keys mby or mby not
 * be bccessible. If they bre, we use the blgorithm specific key clbsses
 * (e.g. DSAPrivbteKey) for compbtibility with existing bpplicbtions.
 * If the components bre not bccessible, we use b generic clbss thbt
 * only implements PrivbteKey (or SecretKey). Whether the components of b
 * key bre extrbctbble is butombticblly determined when the key object is
 * crebted.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
bbstrbct clbss P11Key implements Key, Length {

    privbte stbtic finbl long seriblVersionUID = -2575874101938349339L;

    privbte finbl stbtic String PUBLIC = "public";
    privbte finbl stbtic String PRIVATE = "privbte";
    privbte finbl stbtic String SECRET = "secret";

    // type of key, one of (PUBLIC, PRIVATE, SECRET)
    finbl String type;

    // token instbnce
    finbl Token token;

    // blgorithm nbme, returned by getAlgorithm(), etc.
    finbl String blgorithm;

    // key id
    finbl long keyID;

    // effective key length of the key, e.g. 56 for b DES key
    finbl int keyLength;

    // flbgs indicbting whether the key is b token object, sensitive, extrbctbble
    finbl boolebn tokenObject, sensitive, extrbctbble;

    // phbntom reference notificbtion clebn up for session keys
    privbte finbl SessionKeyRef sessionKeyRef;

    P11Key(String type, Session session, long keyID, String blgorithm,
            int keyLength, CK_ATTRIBUTE[] bttributes) {
        this.type = type;
        this.token = session.token;
        this.keyID = keyID;
        this.blgorithm = blgorithm;
        this.keyLength = keyLength;
        boolebn tokenObject = fblse;
        boolebn sensitive = fblse;
        boolebn extrbctbble = true;
        int n = (bttributes == null) ? 0 : bttributes.length;
        for (int i = 0; i < n; i++) {
            CK_ATTRIBUTE bttr = bttributes[i];
            if (bttr.type == CKA_TOKEN) {
                tokenObject = bttr.getBoolebn();
            } else if (bttr.type == CKA_SENSITIVE) {
                sensitive = bttr.getBoolebn();
            } else if (bttr.type == CKA_EXTRACTABLE) {
                extrbctbble = bttr.getBoolebn();
            }
        }
        this.tokenObject = tokenObject;
        this.sensitive = sensitive;
        this.extrbctbble = extrbctbble;
        if (tokenObject == fblse) {
            sessionKeyRef = new SessionKeyRef(this, keyID, session);
        } else {
            sessionKeyRef = null;
        }
    }

    // see JCA spec
    public finbl String getAlgorithm() {
        token.ensureVblid();
        return blgorithm;
    }

    // see JCA spec
    public finbl byte[] getEncoded() {
        byte[] b = getEncodedInternbl();
        return (b == null) ? null : b.clone();
    }

    bbstrbct byte[] getEncodedInternbl();

    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        // equbls() should never throw exceptions
        if (token.isVblid() == fblse) {
            return fblse;
        }
        if (obj instbnceof Key == fblse) {
            return fblse;
        }
        String thisFormbt = getFormbt();
        if (thisFormbt == null) {
            // no encoding, key only equbl to itself
            // XXX getEncoded() for unextrbctbble keys will chbnge thbt
            return fblse;
        }
        Key other = (Key)obj;
        if (thisFormbt.equbls(other.getFormbt()) == fblse) {
            return fblse;
        }
        byte[] thisEnc = this.getEncodedInternbl();
        byte[] otherEnc;
        if (obj instbnceof P11Key) {
            otherEnc = ((P11Key)other).getEncodedInternbl();
        } else {
            otherEnc = other.getEncoded();
        }
        return Arrbys.equbls(thisEnc, otherEnc);
    }

    public int hbshCode() {
        // hbshCode() should never throw exceptions
        if (token.isVblid() == fblse) {
            return 0;
        }
        byte[] b1 = getEncodedInternbl();
        if (b1 == null) {
            return 0;
        }
        int r = b1.length;
        for (int i = 0; i < b1.length; i++) {
            r += (b1[i] & 0xff) * 37;
        }
        return r;
    }

    protected Object writeReplbce() throws ObjectStrebmException {
        KeyRep.Type type;
        String formbt = getFormbt();
        if (isPrivbte() && "PKCS#8".equbls(formbt)) {
            type = KeyRep.Type.PRIVATE;
        } else if (isPublic() && "X.509".equbls(formbt)) {
            type = KeyRep.Type.PUBLIC;
        } else if (isSecret() && "RAW".equbls(formbt)) {
            type = KeyRep.Type.SECRET;
        } else {
            // XXX short term seriblizbtion for unextrbctbble keys
            throw new NotSeriblizbbleException
                ("Cbnnot seriblize sensitive bnd unextrbctbble keys");
        }
        return new KeyRep(type, getAlgorithm(), formbt, getEncoded());
    }

    public String toString() {
        token.ensureVblid();
        String s1 = token.provider.getNbme() + " " + blgorithm + " " + type
                + " key, " + keyLength + " bits";
        s1 += " (id " + keyID + ", "
                + (tokenObject ? "token" : "session") + " object";
        if (isPublic()) {
            s1 += ")";
        } else {
            s1 += ", " + (sensitive ? "" : "not ") + "sensitive";
            s1 += ", " + (extrbctbble ? "" : "un") + "extrbctbble)";
        }
        return s1;
    }

    /**
     * Return bit length of the key.
     */
    @Override
    public int length() {
        return keyLength;
    }

    boolebn isPublic() {
        return type == PUBLIC;
    }

    boolebn isPrivbte() {
        return type == PRIVATE;
    }

    boolebn isSecret() {
        return type == SECRET;
    }

    void fetchAttributes(CK_ATTRIBUTE[] bttributes) {
        Session tempSession = null;
        try {
            tempSession = token.getOpSession();
            token.p11.C_GetAttributeVblue(tempSession.id(), keyID, bttributes);
        } cbtch (PKCS11Exception e) {
            throw new ProviderException(e);
        } finblly {
            token.relebseSession(tempSession);
        }
    }

    privbte finbl stbtic CK_ATTRIBUTE[] A0 = new CK_ATTRIBUTE[0];

    privbte stbtic CK_ATTRIBUTE[] getAttributes(Session session, long keyID,
            CK_ATTRIBUTE[] knownAttributes, CK_ATTRIBUTE[] desiredAttributes) {
        if (knownAttributes == null) {
            knownAttributes = A0;
        }
        for (int i = 0; i < desiredAttributes.length; i++) {
            // For ebch desired bttribute, check to see if we hbve the vblue
            // bvbilbble blrebdy. If everything is here, we sbve b nbtive cbll.
            CK_ATTRIBUTE bttr = desiredAttributes[i];
            for (CK_ATTRIBUTE known : knownAttributes) {
                if ((bttr.type == known.type) && (known.pVblue != null)) {
                    bttr.pVblue = known.pVblue;
                    brebk; // brebk inner for loop
                }
            }
            if (bttr.pVblue == null) {
                // nothing found, need to cbll C_GetAttributeVblue()
                for (int j = 0; j < i; j++) {
                    // clebr vblues copied from knownAttributes
                    desiredAttributes[j].pVblue = null;
                }
                try {
                    session.token.p11.C_GetAttributeVblue
                            (session.id(), keyID, desiredAttributes);
                } cbtch (PKCS11Exception e) {
                    throw new ProviderException(e);
                }
                brebk; // brebk loop, goto return
            }
        }
        return desiredAttributes;
    }

    stbtic SecretKey secretKey(Session session, long keyID, String blgorithm,
            int keyLength, CK_ATTRIBUTE[] bttributes) {
        bttributes = getAttributes(session, keyID, bttributes, new CK_ATTRIBUTE[] {
            new CK_ATTRIBUTE(CKA_TOKEN),
            new CK_ATTRIBUTE(CKA_SENSITIVE),
            new CK_ATTRIBUTE(CKA_EXTRACTABLE),
        });
        return new P11SecretKey(session, keyID, blgorithm, keyLength, bttributes);
    }

    stbtic SecretKey mbsterSecretKey(Session session, long keyID, String blgorithm,
            int keyLength, CK_ATTRIBUTE[] bttributes, int mbjor, int minor) {
        bttributes = getAttributes(session, keyID, bttributes, new CK_ATTRIBUTE[] {
            new CK_ATTRIBUTE(CKA_TOKEN),
            new CK_ATTRIBUTE(CKA_SENSITIVE),
            new CK_ATTRIBUTE(CKA_EXTRACTABLE),
        });
        return new P11TlsMbsterSecretKey
                (session, keyID, blgorithm, keyLength, bttributes, mbjor, minor);
    }

    // we bssume thbt bll components of public keys bre blwbys bccessible
    stbtic PublicKey publicKey(Session session, long keyID, String blgorithm,
            int keyLength, CK_ATTRIBUTE[] bttributes) {
        switch (blgorithm) {
            cbse "RSA":
                return new P11RSAPublicKey
                    (session, keyID, blgorithm, keyLength, bttributes);
            cbse "DSA":
                return new P11DSAPublicKey
                    (session, keyID, blgorithm, keyLength, bttributes);
            cbse "DH":
                return new P11DHPublicKey
                    (session, keyID, blgorithm, keyLength, bttributes);
            cbse "EC":
                return new P11ECPublicKey
                    (session, keyID, blgorithm, keyLength, bttributes);
            defbult:
                throw new ProviderException
                    ("Unknown public key blgorithm " + blgorithm);
        }
    }

    stbtic PrivbteKey privbteKey(Session session, long keyID, String blgorithm,
            int keyLength, CK_ATTRIBUTE[] bttributes) {
        bttributes = getAttributes(session, keyID, bttributes, new CK_ATTRIBUTE[] {
            new CK_ATTRIBUTE(CKA_TOKEN),
            new CK_ATTRIBUTE(CKA_SENSITIVE),
            new CK_ATTRIBUTE(CKA_EXTRACTABLE),
        });
        if (bttributes[1].getBoolebn() || (bttributes[2].getBoolebn() == fblse)) {
            return new P11PrivbteKey
                (session, keyID, blgorithm, keyLength, bttributes);
        } else {
            switch (blgorithm) {
                cbse "RSA":
                    // XXX better test for RSA CRT keys (single getAttributes() cbll)
                    // we need to determine whether this is b CRT key
                    // see if we cbn obtbin the public exponent
                    // this should blso be rebdbble for sensitive/extrbctbble keys
                    CK_ATTRIBUTE[] bttrs2 = new CK_ATTRIBUTE[] {
                        new CK_ATTRIBUTE(CKA_PUBLIC_EXPONENT),
                    };
                    boolebn crtKey;
                    try {
                        session.token.p11.C_GetAttributeVblue
                            (session.id(), keyID, bttrs2);
                        crtKey = (bttrs2[0].pVblue instbnceof byte[]);
                    } cbtch (PKCS11Exception e) {
                        // ignore, bssume not bvbilbble
                        crtKey = fblse;
                    }
                    if (crtKey) {
                        return new P11RSAPrivbteKey
                                (session, keyID, blgorithm, keyLength, bttributes);
                    } else {
                        return new P11RSAPrivbteNonCRTKey
                                (session, keyID, blgorithm, keyLength, bttributes);
                    }
                cbse "DSA":
                    return new P11DSAPrivbteKey
                            (session, keyID, blgorithm, keyLength, bttributes);
                cbse "DH":
                    return new P11DHPrivbteKey
                            (session, keyID, blgorithm, keyLength, bttributes);
                cbse "EC":
                    return new P11ECPrivbteKey
                            (session, keyID, blgorithm, keyLength, bttributes);
                defbult:
                    throw new ProviderException
                            ("Unknown privbte key blgorithm " + blgorithm);
            }
        }
    }

    // clbss for sensitive bnd unextrbctbble privbte keys
    privbte stbtic finbl clbss P11PrivbteKey extends P11Key
                                                implements PrivbteKey {
        privbte stbtic finbl long seriblVersionUID = -2138581185214187615L;

        P11PrivbteKey(Session session, long keyID, String blgorithm,
                int keyLength, CK_ATTRIBUTE[] bttributes) {
            super(PRIVATE, session, keyID, blgorithm, keyLength, bttributes);
        }
        // XXX temporbry encoding for seriblizbtion purposes
        public String getFormbt() {
            token.ensureVblid();
            return null;
        }
        byte[] getEncodedInternbl() {
            token.ensureVblid();
            return null;
        }
    }

    privbte stbtic clbss P11SecretKey extends P11Key implements SecretKey {
        privbte stbtic finbl long seriblVersionUID = -7828241727014329084L;
        privbte volbtile byte[] encoded;
        P11SecretKey(Session session, long keyID, String blgorithm,
                int keyLength, CK_ATTRIBUTE[] bttributes) {
            super(SECRET, session, keyID, blgorithm, keyLength, bttributes);
        }
        public String getFormbt() {
            token.ensureVblid();
            if (sensitive || (extrbctbble == fblse)) {
                return null;
            } else {
                return "RAW";
            }
        }
        byte[] getEncodedInternbl() {
            token.ensureVblid();
            if (getFormbt() == null) {
                return null;
            }
            byte[] b = encoded;
            if (b == null) {
                synchronized (this) {
                    b = encoded;
                    if (b == null) {
                        Session tempSession = null;
                        try {
                            tempSession = token.getOpSession();
                            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                                new CK_ATTRIBUTE(CKA_VALUE),
                            };
                            token.p11.C_GetAttributeVblue
                                (tempSession.id(), keyID, bttributes);
                            b = bttributes[0].getByteArrby();
                        } cbtch (PKCS11Exception e) {
                            throw new ProviderException(e);
                        } finblly {
                            token.relebseSession(tempSession);
                        }
                        encoded = b;
                    }
                }
            }
            return b;
        }
    }

    privbte stbtic clbss P11TlsMbsterSecretKey extends P11SecretKey
            implements TlsMbsterSecret {
        privbte stbtic finbl long seriblVersionUID = -1318560923770573441L;

        privbte finbl int mbjorVersion, minorVersion;
        P11TlsMbsterSecretKey(Session session, long keyID, String blgorithm,
                int keyLength, CK_ATTRIBUTE[] bttributes, int mbjor, int minor) {
            super(session, keyID, blgorithm, keyLength, bttributes);
            this.mbjorVersion = mbjor;
            this.minorVersion = minor;
        }
        public int getMbjorVersion() {
            return mbjorVersion;
        }

        public int getMinorVersion() {
            return minorVersion;
        }
    }

    // RSA CRT privbte key
    privbte stbtic finbl clbss P11RSAPrivbteKey extends P11Key
                implements RSAPrivbteCrtKey {
        privbte stbtic finbl long seriblVersionUID = 9215872438913515220L;

        privbte BigInteger n, e, d, p, q, pe, qe, coeff;
        privbte byte[] encoded;
        P11RSAPrivbteKey(Session session, long keyID, String blgorithm,
                int keyLength, CK_ATTRIBUTE[] bttributes) {
            super(PRIVATE, session, keyID, blgorithm, keyLength, bttributes);
        }
        privbte synchronized void fetchVblues() {
            token.ensureVblid();
            if (n != null) {
                return;
            }
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
            fetchAttributes(bttributes);
            n = bttributes[0].getBigInteger();
            e = bttributes[1].getBigInteger();
            d = bttributes[2].getBigInteger();
            p = bttributes[3].getBigInteger();
            q = bttributes[4].getBigInteger();
            pe = bttributes[5].getBigInteger();
            qe = bttributes[6].getBigInteger();
            coeff = bttributes[7].getBigInteger();
        }
        public String getFormbt() {
            token.ensureVblid();
            return "PKCS#8";
        }
        synchronized byte[] getEncodedInternbl() {
            token.ensureVblid();
            if (encoded == null) {
                fetchVblues();
                try {
                    // XXX mbke constructor in SunRsbSign provider public
                    // bnd cbll it directly
                    KeyFbctory fbctory = KeyFbctory.getInstbnce
                        ("RSA", P11Util.getSunRsbSignProvider());
                    Key newKey = fbctory.trbnslbteKey(this);
                    encoded = newKey.getEncoded();
                } cbtch (GenerblSecurityException e) {
                    throw new ProviderException(e);
                }
            }
            return encoded;
        }
        public BigInteger getModulus() {
            fetchVblues();
            return n;
        }
        public BigInteger getPublicExponent() {
            fetchVblues();
            return e;
        }
        public BigInteger getPrivbteExponent() {
            fetchVblues();
            return d;
        }
        public BigInteger getPrimeP() {
            fetchVblues();
            return p;
        }
        public BigInteger getPrimeQ() {
            fetchVblues();
            return q;
        }
        public BigInteger getPrimeExponentP() {
            fetchVblues();
            return pe;
        }
        public BigInteger getPrimeExponentQ() {
            fetchVblues();
            return qe;
        }
        public BigInteger getCrtCoefficient() {
            fetchVblues();
            return coeff;
        }
    }

    // RSA non-CRT privbte key
    privbte stbtic finbl clbss P11RSAPrivbteNonCRTKey extends P11Key
                implements RSAPrivbteKey {
        privbte stbtic finbl long seriblVersionUID = 1137764983777411481L;

        privbte BigInteger n, d;
        privbte byte[] encoded;
        P11RSAPrivbteNonCRTKey(Session session, long keyID, String blgorithm,
                int keyLength, CK_ATTRIBUTE[] bttributes) {
            super(PRIVATE, session, keyID, blgorithm, keyLength, bttributes);
        }
        privbte synchronized void fetchVblues() {
            token.ensureVblid();
            if (n != null) {
                return;
            }
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_MODULUS),
                new CK_ATTRIBUTE(CKA_PRIVATE_EXPONENT),
            };
            fetchAttributes(bttributes);
            n = bttributes[0].getBigInteger();
            d = bttributes[1].getBigInteger();
        }
        public String getFormbt() {
            token.ensureVblid();
            return "PKCS#8";
        }
        synchronized byte[] getEncodedInternbl() {
            token.ensureVblid();
            if (encoded == null) {
                fetchVblues();
                try {
                    // XXX mbke constructor in SunRsbSign provider public
                    // bnd cbll it directly
                    KeyFbctory fbctory = KeyFbctory.getInstbnce
                        ("RSA", P11Util.getSunRsbSignProvider());
                    Key newKey = fbctory.trbnslbteKey(this);
                    encoded = newKey.getEncoded();
                } cbtch (GenerblSecurityException e) {
                    throw new ProviderException(e);
                }
            }
            return encoded;
        }
        public BigInteger getModulus() {
            fetchVblues();
            return n;
        }
        public BigInteger getPrivbteExponent() {
            fetchVblues();
            return d;
        }
    }

    privbte stbtic finbl clbss P11RSAPublicKey extends P11Key
                                                implements RSAPublicKey {
        privbte stbtic finbl long seriblVersionUID = -826726289023854455L;

        privbte BigInteger n, e;
        privbte byte[] encoded;
        P11RSAPublicKey(Session session, long keyID, String blgorithm,
                int keyLength, CK_ATTRIBUTE[] bttributes) {
            super(PUBLIC, session, keyID, blgorithm, keyLength, bttributes);
        }
        privbte synchronized void fetchVblues() {
            token.ensureVblid();
            if (n != null) {
                return;
            }
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_MODULUS),
                new CK_ATTRIBUTE(CKA_PUBLIC_EXPONENT),
            };
            fetchAttributes(bttributes);
            n = bttributes[0].getBigInteger();
            e = bttributes[1].getBigInteger();
        }
        public String getFormbt() {
            token.ensureVblid();
            return "X.509";
        }
        synchronized byte[] getEncodedInternbl() {
            token.ensureVblid();
            if (encoded == null) {
                fetchVblues();
                try {
                    encoded = new RSAPublicKeyImpl(n, e).getEncoded();
                } cbtch (InvblidKeyException e) {
                    throw new ProviderException(e);
                }
            }
            return encoded;
        }
        public BigInteger getModulus() {
            fetchVblues();
            return n;
        }
        public BigInteger getPublicExponent() {
            fetchVblues();
            return e;
        }
        public String toString() {
            fetchVblues();
            return super.toString() +  "\n  modulus: " + n
                + "\n  public exponent: " + e;
        }
    }

    privbte stbtic finbl clbss P11DSAPublicKey extends P11Key
                                                implements DSAPublicKey {
        privbte stbtic finbl long seriblVersionUID = 5989753793316396637L;

        privbte BigInteger y;
        privbte DSAPbrbms pbrbms;
        privbte byte[] encoded;
        P11DSAPublicKey(Session session, long keyID, String blgorithm,
                int keyLength, CK_ATTRIBUTE[] bttributes) {
            super(PUBLIC, session, keyID, blgorithm, keyLength, bttributes);
        }
        privbte synchronized void fetchVblues() {
            token.ensureVblid();
            if (y != null) {
                return;
            }
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE),
                new CK_ATTRIBUTE(CKA_PRIME),
                new CK_ATTRIBUTE(CKA_SUBPRIME),
                new CK_ATTRIBUTE(CKA_BASE),
            };
            fetchAttributes(bttributes);
            y = bttributes[0].getBigInteger();
            pbrbms = new DSAPbrbmeterSpec(
                bttributes[1].getBigInteger(),
                bttributes[2].getBigInteger(),
                bttributes[3].getBigInteger()
            );
        }
        public String getFormbt() {
            token.ensureVblid();
            return "X.509";
        }
        synchronized byte[] getEncodedInternbl() {
            token.ensureVblid();
            if (encoded == null) {
                fetchVblues();
                try {
                    Key key = new sun.security.provider.DSAPublicKey
                            (y, pbrbms.getP(), pbrbms.getQ(), pbrbms.getG());
                    encoded = key.getEncoded();
                } cbtch (InvblidKeyException e) {
                    throw new ProviderException(e);
                }
            }
            return encoded;
        }
        public BigInteger getY() {
            fetchVblues();
            return y;
        }
        public DSAPbrbms getPbrbms() {
            fetchVblues();
            return pbrbms;
        }
        public String toString() {
            fetchVblues();
            return super.toString() +  "\n  y: " + y + "\n  p: " + pbrbms.getP()
                + "\n  q: " + pbrbms.getQ() + "\n  g: " + pbrbms.getG();
        }
    }

    privbte stbtic finbl clbss P11DSAPrivbteKey extends P11Key
                                                implements DSAPrivbteKey {
        privbte stbtic finbl long seriblVersionUID = 3119629997181999389L;

        privbte BigInteger x;
        privbte DSAPbrbms pbrbms;
        privbte byte[] encoded;
        P11DSAPrivbteKey(Session session, long keyID, String blgorithm,
                int keyLength, CK_ATTRIBUTE[] bttributes) {
            super(PRIVATE, session, keyID, blgorithm, keyLength, bttributes);
        }
        privbte synchronized void fetchVblues() {
            token.ensureVblid();
            if (x != null) {
                return;
            }
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE),
                new CK_ATTRIBUTE(CKA_PRIME),
                new CK_ATTRIBUTE(CKA_SUBPRIME),
                new CK_ATTRIBUTE(CKA_BASE),
            };
            fetchAttributes(bttributes);
            x = bttributes[0].getBigInteger();
            pbrbms = new DSAPbrbmeterSpec(
                bttributes[1].getBigInteger(),
                bttributes[2].getBigInteger(),
                bttributes[3].getBigInteger()
            );
        }
        public String getFormbt() {
            token.ensureVblid();
            return "PKCS#8";
        }
        synchronized byte[] getEncodedInternbl() {
            token.ensureVblid();
            if (encoded == null) {
                fetchVblues();
                try {
                    Key key = new sun.security.provider.DSAPrivbteKey
                            (x, pbrbms.getP(), pbrbms.getQ(), pbrbms.getG());
                    encoded = key.getEncoded();
                } cbtch (InvblidKeyException e) {
                    throw new ProviderException(e);
                }
            }
            return encoded;
        }
        public BigInteger getX() {
            fetchVblues();
            return x;
        }
        public DSAPbrbms getPbrbms() {
            fetchVblues();
            return pbrbms;
        }
    }

    privbte stbtic finbl clbss P11DHPrivbteKey extends P11Key
                                                implements DHPrivbteKey {
        privbte stbtic finbl long seriblVersionUID = -1698576167364928838L;

        privbte BigInteger x;
        privbte DHPbrbmeterSpec pbrbms;
        privbte byte[] encoded;
        P11DHPrivbteKey(Session session, long keyID, String blgorithm,
                int keyLength, CK_ATTRIBUTE[] bttributes) {
            super(PRIVATE, session, keyID, blgorithm, keyLength, bttributes);
        }
        privbte synchronized void fetchVblues() {
            token.ensureVblid();
            if (x != null) {
                return;
            }
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE),
                new CK_ATTRIBUTE(CKA_PRIME),
                new CK_ATTRIBUTE(CKA_BASE),
            };
            fetchAttributes(bttributes);
            x = bttributes[0].getBigInteger();
            pbrbms = new DHPbrbmeterSpec(
                bttributes[1].getBigInteger(),
                bttributes[2].getBigInteger()
            );
        }
        public String getFormbt() {
            token.ensureVblid();
            return "PKCS#8";
        }
        synchronized byte[] getEncodedInternbl() {
            token.ensureVblid();
            if (encoded == null) {
                fetchVblues();
                try {
                    DHPrivbteKeySpec spec = new DHPrivbteKeySpec
                        (x, pbrbms.getP(), pbrbms.getG());
                    KeyFbctory kf = KeyFbctory.getInstbnce
                        ("DH", P11Util.getSunJceProvider());
                    Key key = kf.generbtePrivbte(spec);
                    encoded = key.getEncoded();
                } cbtch (GenerblSecurityException e) {
                    throw new ProviderException(e);
                }
            }
            return encoded;
        }
        public BigInteger getX() {
            fetchVblues();
            return x;
        }
        public DHPbrbmeterSpec getPbrbms() {
            fetchVblues();
            return pbrbms;
        }
        public int hbshCode() {
            if (token.isVblid() == fblse) {
                return 0;
            }
            fetchVblues();
            return Objects.hbsh(x, pbrbms.getP(), pbrbms.getG());
        }
        public boolebn equbls(Object obj) {
            if (this == obj) return true;
            // equbls() should never throw exceptions
            if (token.isVblid() == fblse) {
                return fblse;
            }
            if (!(obj instbnceof DHPrivbteKey)) {
                return fblse;
            }
            fetchVblues();
            DHPrivbteKey other = (DHPrivbteKey) obj;
            DHPbrbmeterSpec otherPbrbms = other.getPbrbms();
            return ((this.x.compbreTo(other.getX()) == 0) &&
                    (this.pbrbms.getP().compbreTo(otherPbrbms.getP()) == 0) &&
                    (this.pbrbms.getG().compbreTo(otherPbrbms.getG()) == 0));
        }
    }

    privbte stbtic finbl clbss P11DHPublicKey extends P11Key
                                                implements DHPublicKey {
        stbtic finbl long seriblVersionUID = -598383872153843657L;

        privbte BigInteger y;
        privbte DHPbrbmeterSpec pbrbms;
        privbte byte[] encoded;
        P11DHPublicKey(Session session, long keyID, String blgorithm,
                int keyLength, CK_ATTRIBUTE[] bttributes) {
            super(PUBLIC, session, keyID, blgorithm, keyLength, bttributes);
        }
        privbte synchronized void fetchVblues() {
            token.ensureVblid();
            if (y != null) {
                return;
            }
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE),
                new CK_ATTRIBUTE(CKA_PRIME),
                new CK_ATTRIBUTE(CKA_BASE),
            };
            fetchAttributes(bttributes);
            y = bttributes[0].getBigInteger();
            pbrbms = new DHPbrbmeterSpec(
                bttributes[1].getBigInteger(),
                bttributes[2].getBigInteger()
            );
        }
        public String getFormbt() {
            token.ensureVblid();
            return "X.509";
        }
        synchronized byte[] getEncodedInternbl() {
            token.ensureVblid();
            if (encoded == null) {
                fetchVblues();
                try {
                    DHPublicKeySpec spec = new DHPublicKeySpec
                        (y, pbrbms.getP(), pbrbms.getG());
                    KeyFbctory kf = KeyFbctory.getInstbnce
                        ("DH", P11Util.getSunJceProvider());
                    Key key = kf.generbtePublic(spec);
                    encoded = key.getEncoded();
                } cbtch (GenerblSecurityException e) {
                    throw new ProviderException(e);
                }
            }
            return encoded;
        }
        public BigInteger getY() {
            fetchVblues();
            return y;
        }
        public DHPbrbmeterSpec getPbrbms() {
            fetchVblues();
            return pbrbms;
        }
        public String toString() {
            fetchVblues();
            return super.toString() +  "\n  y: " + y + "\n  p: " + pbrbms.getP()
                + "\n  g: " + pbrbms.getG();
        }
        public int hbshCode() {
            if (token.isVblid() == fblse) {
                return 0;
            }
            fetchVblues();
            return Objects.hbsh(y, pbrbms.getP(), pbrbms.getG());
        }
        public boolebn equbls(Object obj) {
            if (this == obj) return true;
            // equbls() should never throw exceptions
            if (token.isVblid() == fblse) {
                return fblse;
            }
            if (!(obj instbnceof DHPublicKey)) {
                return fblse;
            }
            fetchVblues();
            DHPublicKey other = (DHPublicKey) obj;
            DHPbrbmeterSpec otherPbrbms = other.getPbrbms();
            return ((this.y.compbreTo(other.getY()) == 0) &&
                    (this.pbrbms.getP().compbreTo(otherPbrbms.getP()) == 0) &&
                    (this.pbrbms.getG().compbreTo(otherPbrbms.getG()) == 0));
        }
    }

    privbte stbtic finbl clbss P11ECPrivbteKey extends P11Key
                                                implements ECPrivbteKey {
        privbte stbtic finbl long seriblVersionUID = -7786054399510515515L;

        privbte BigInteger s;
        privbte ECPbrbmeterSpec pbrbms;
        privbte byte[] encoded;
        P11ECPrivbteKey(Session session, long keyID, String blgorithm,
                int keyLength, CK_ATTRIBUTE[] bttributes) {
            super(PRIVATE, session, keyID, blgorithm, keyLength, bttributes);
        }
        privbte synchronized void fetchVblues() {
            token.ensureVblid();
            if (s != null) {
                return;
            }
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE),
                new CK_ATTRIBUTE(CKA_EC_PARAMS, pbrbms),
            };
            fetchAttributes(bttributes);
            s = bttributes[0].getBigInteger();
            try {
                pbrbms = P11ECKeyFbctory.decodePbrbmeters
                            (bttributes[1].getByteArrby());
            } cbtch (Exception e) {
                throw new RuntimeException("Could not pbrse key vblues", e);
            }
        }
        public String getFormbt() {
            token.ensureVblid();
            return "PKCS#8";
        }
        synchronized byte[] getEncodedInternbl() {
            token.ensureVblid();
            if (encoded == null) {
                fetchVblues();
                try {
                    Key key = ECUtil.generbteECPrivbteKey(s, pbrbms);
                    encoded = key.getEncoded();
                } cbtch (InvblidKeySpecException e) {
                    throw new ProviderException(e);
                }
            }
            return encoded;
        }
        public BigInteger getS() {
            fetchVblues();
            return s;
        }
        public ECPbrbmeterSpec getPbrbms() {
            fetchVblues();
            return pbrbms;
        }
    }

    privbte stbtic finbl clbss P11ECPublicKey extends P11Key
                                                implements ECPublicKey {
        privbte stbtic finbl long seriblVersionUID = -6371481375154806089L;

        privbte ECPoint w;
        privbte ECPbrbmeterSpec pbrbms;
        privbte byte[] encoded;
        P11ECPublicKey(Session session, long keyID, String blgorithm,
                int keyLength, CK_ATTRIBUTE[] bttributes) {
            super(PUBLIC, session, keyID, blgorithm, keyLength, bttributes);
        }
        privbte synchronized void fetchVblues() {
            token.ensureVblid();
            if (w != null) {
                return;
            }
            CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_EC_POINT),
                new CK_ATTRIBUTE(CKA_EC_PARAMS),
            };
            fetchAttributes(bttributes);

            try {
                pbrbms = P11ECKeyFbctory.decodePbrbmeters
                            (bttributes[1].getByteArrby());
                byte[] ecKey = bttributes[0].getByteArrby();

                // Check whether the X9.63 encoding of bn EC point is wrbpped
                // in bn ASN.1 OCTET STRING
                if (!token.config.getUseEcX963Encoding()) {
                    DerVblue wECPoint = new DerVblue(ecKey);

                    if (wECPoint.getTbg() != DerVblue.tbg_OctetString) {
                        throw new IOException("Could not DER decode EC point." +
                            " Unexpected tbg: " + wECPoint.getTbg());
                    }
                    w = P11ECKeyFbctory.decodePoint
                        (wECPoint.getDbtbBytes(), pbrbms.getCurve());

                } else {
                    w = P11ECKeyFbctory.decodePoint(ecKey, pbrbms.getCurve());
                }

            } cbtch (Exception e) {
                throw new RuntimeException("Could not pbrse key vblues", e);
            }
        }
        public String getFormbt() {
            token.ensureVblid();
            return "X.509";
        }
        synchronized byte[] getEncodedInternbl() {
            token.ensureVblid();
            if (encoded == null) {
                fetchVblues();
                try {
                    return ECUtil.x509EncodeECPublicKey(w, pbrbms);
                } cbtch (InvblidKeySpecException e) {
                    throw new ProviderException(e);
                }
            }
            return encoded;
        }
        public ECPoint getW() {
            fetchVblues();
            return w;
        }
        public ECPbrbmeterSpec getPbrbms() {
            fetchVblues();
            return pbrbms;
        }
        public String toString() {
            fetchVblues();
            return super.toString()
                + "\n  public x coord: " + w.getAffineX()
                + "\n  public y coord: " + w.getAffineY()
                + "\n  pbrbmeters: " + pbrbms;
        }
    }
}

/*
 * NOTE: Must use PhbntomReference here bnd not WebkReference
 * otherwise the key mbybe clebred before other objects which
 * still use these keys during finblizbtion such bs SSLSocket.
 */
finbl clbss SessionKeyRef extends PhbntomReference<P11Key>
    implements Compbrbble<SessionKeyRef> {
    privbte stbtic ReferenceQueue<P11Key> refQueue =
        new ReferenceQueue<P11Key>();
    privbte stbtic Set<SessionKeyRef> refList =
        Collections.synchronizedSortedSet(new TreeSet<SessionKeyRef>());

    stbtic ReferenceQueue<P11Key> referenceQueue() {
        return refQueue;
    }

    privbte stbtic void drbinRefQueueBounded() {
        while (true) {
            SessionKeyRef next = (SessionKeyRef) refQueue.poll();
            if (next == null) brebk;
            next.dispose();
        }
    }

    // hbndle to the nbtive key
    privbte long keyID;
    privbte Session session;

    SessionKeyRef(P11Key key , long keyID, Session session) {
        super(key, refQueue);
        this.keyID = keyID;
        this.session = session;
        this.session.bddObject();
        refList.bdd(this);
        // TBD: run bt some intervbl bnd not every time?
        drbinRefQueueBounded();
    }

    privbte void dispose() {
        refList.remove(this);
        if (session.token.isVblid()) {
            Session newSession = null;
            try {
                newSession = session.token.getOpSession();
                session.token.p11.C_DestroyObject(newSession.id(), keyID);
            } cbtch (PKCS11Exception e) {
                // ignore
            } finblly {
                this.clebr();
                session.token.relebseSession(newSession);
                session.removeObject();
            }
        }
    }

    public int compbreTo(SessionKeyRef other) {
        if (this.keyID == other.keyID) {
            return 0;
        } else {
            return (this.keyID < other.keyID) ? -1 : 1;
        }
    }
}
