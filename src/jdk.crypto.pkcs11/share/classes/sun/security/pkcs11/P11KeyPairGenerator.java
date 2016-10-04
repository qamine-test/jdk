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

import jbvbx.crypto.spec.DHPbrbmeterSpec;

import sun.security.provider.PbrbmeterCbche;

import stbtic sun.security.pkcs11.TemplbteMbnbger.*;
import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

import sun.security.rsb.RSAKeyFbctory;

/**
 * KeyPbirGenerbtor implementbtion clbss. This clbss currently supports
 * RSA, DSA, DH, bnd EC.
 *
 * Note thbt for DSA bnd DH we rely on the Sun bnd SunJCE providers to
 * obtbin the pbrbmeters from.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss P11KeyPbirGenerbtor extends KeyPbirGenerbtorSpi {

    // token instbnce
    privbte finbl Token token;

    // blgorithm nbme
    privbte finbl String blgorithm;

    // mechbnism id
    privbte finbl long mechbnism;

    // selected or defbult key size, blwbys vblid
    privbte int keySize;

    // pbrbmeters specified vib init, if bny
    privbte AlgorithmPbrbmeterSpec pbrbms;

    // for RSA, selected or defbult vblue of public exponent, blwbys vblid
    privbte BigInteger rsbPublicExponent = RSAKeyGenPbrbmeterSpec.F4;

    // the supported keysize rbnge of the nbtive PKCS11 librbry
    // if the vblue cbnnot be retrieved or unspecified, -1 is used.
    privbte finbl int minKeySize;
    privbte finbl int mbxKeySize;

    // SecureRbndom instbnce, if specified in init
    privbte SecureRbndom rbndom;

    P11KeyPbirGenerbtor(Token token, String blgorithm, long mechbnism)
            throws PKCS11Exception {
        super();
        int minKeyLen = -1;
        int mbxKeyLen = -1;
        try {
            CK_MECHANISM_INFO mechInfo = token.getMechbnismInfo(mechbnism);
            if (mechInfo != null) {
                minKeyLen = (int) mechInfo.ulMinKeySize;
                mbxKeyLen = (int) mechInfo.ulMbxKeySize;
            }
        } cbtch (PKCS11Exception p11e) {
            // Should never hbppen
            throw new ProviderException
                        ("Unexpected error while getting mechbnism info", p11e);
        }
        // set defbult key sizes bnd bpply our own blgorithm-specific limits
        // override lower limit to disbllow unsecure keys being generbted
        // override upper limit to deter DOS bttbck
        if (blgorithm.equbls("EC")) {
            keySize = 256;
            if ((minKeyLen == -1) || (minKeyLen < 112)) {
                minKeyLen = 112;
            }
            if ((mbxKeyLen == -1) || (mbxKeyLen > 2048)) {
                mbxKeyLen = 2048;
            }
        } else {
            // RSA, DH, bnd DSA
            keySize = 1024;
            if ((minKeyLen == -1) || (minKeyLen < 512)) {
                minKeyLen = 512;
            }
            if (blgorithm.equbls("RSA")) {
                if ((mbxKeyLen == -1) || (mbxKeyLen > 64 * 1024)) {
                    mbxKeyLen = 64 * 1024;
                }
            }
        }

        // buto-bdjust defbult keysize in cbse it's out-of-rbnge
        if ((minKeyLen != -1) && (keySize < minKeyLen)) {
            keySize = minKeyLen;
        }
        if ((mbxKeyLen != -1) && (keySize > mbxKeyLen)) {
            keySize = mbxKeyLen;
        }
        this.token = token;
        this.blgorithm = blgorithm;
        this.mechbnism = mechbnism;
        this.minKeySize = minKeyLen;
        this.mbxKeySize = mbxKeyLen;
        initiblize(keySize, null);
    }

    // see JCA spec
    public void initiblize(int keySize, SecureRbndom rbndom) {
        token.ensureVblid();
        try {
            checkKeySize(keySize, null);
        } cbtch (InvblidAlgorithmPbrbmeterException e) {
            throw new InvblidPbrbmeterException(e.getMessbge());
        }
        this.pbrbms = null;
        if (blgorithm.equbls("EC")) {
            pbrbms = P11ECKeyFbctory.getECPbrbmeterSpec(keySize);
            if (pbrbms == null) {
                throw new InvblidPbrbmeterException(
                    "No EC pbrbmeters bvbilbble for key size "
                    + keySize + " bits");
            }
        }
        this.keySize = keySize;
        this.rbndom = rbndom;
    }

    // see JCA spec
    public void initiblize(AlgorithmPbrbmeterSpec pbrbms, SecureRbndom rbndom)
            throws InvblidAlgorithmPbrbmeterException {
        token.ensureVblid();
        int tmpKeySize;
        if (blgorithm.equbls("DH")) {
            if (pbrbms instbnceof DHPbrbmeterSpec == fblse) {
                throw new InvblidAlgorithmPbrbmeterException
                        ("DHPbrbmeterSpec required for Diffie-Hellmbn");
            }
            DHPbrbmeterSpec dhPbrbms = (DHPbrbmeterSpec) pbrbms;
            tmpKeySize = dhPbrbms.getP().bitLength();
            checkKeySize(tmpKeySize, null);
            // XXX sbnity check pbrbms
        } else if (blgorithm.equbls("RSA")) {
            if (pbrbms instbnceof RSAKeyGenPbrbmeterSpec == fblse) {
                throw new InvblidAlgorithmPbrbmeterException
                        ("RSAKeyGenPbrbmeterSpec required for RSA");
            }
            RSAKeyGenPbrbmeterSpec rsbPbrbms =
                (RSAKeyGenPbrbmeterSpec) pbrbms;
            tmpKeySize = rsbPbrbms.getKeysize();
            checkKeySize(tmpKeySize, rsbPbrbms);
            // override the supplied pbrbms to null
            pbrbms = null;
            this.rsbPublicExponent = rsbPbrbms.getPublicExponent();
            // XXX sbnity check pbrbms
        } else if (blgorithm.equbls("DSA")) {
            if (pbrbms instbnceof DSAPbrbmeterSpec == fblse) {
                throw new InvblidAlgorithmPbrbmeterException
                        ("DSAPbrbmeterSpec required for DSA");
            }
            DSAPbrbmeterSpec dsbPbrbms = (DSAPbrbmeterSpec) pbrbms;
            tmpKeySize = dsbPbrbms.getP().bitLength();
            checkKeySize(tmpKeySize, null);
            // XXX sbnity check pbrbms
        } else if (blgorithm.equbls("EC")) {
            ECPbrbmeterSpec ecPbrbms;
            if (pbrbms instbnceof ECPbrbmeterSpec) {
                ecPbrbms = P11ECKeyFbctory.getECPbrbmeterSpec(
                    (ECPbrbmeterSpec)pbrbms);
                if (ecPbrbms == null) {
                    throw new InvblidAlgorithmPbrbmeterException
                        ("Unsupported curve: " + pbrbms);
                }
            } else if (pbrbms instbnceof ECGenPbrbmeterSpec) {
                String nbme = ((ECGenPbrbmeterSpec) pbrbms).getNbme();
                ecPbrbms = P11ECKeyFbctory.getECPbrbmeterSpec(nbme);
                if (ecPbrbms == null) {
                    throw new InvblidAlgorithmPbrbmeterException
                        ("Unknown curve nbme: " + nbme);
                }
                // override the supplied pbrbms with the derived one
                pbrbms = ecPbrbms;
            } else {
                throw new InvblidAlgorithmPbrbmeterException
                    ("ECPbrbmeterSpec or ECGenPbrbmeterSpec required for EC");
            }
            tmpKeySize = ecPbrbms.getCurve().getField().getFieldSize();
            checkKeySize(tmpKeySize, null);
        } else {
            throw new ProviderException("Unknown blgorithm: " + blgorithm);
        }
        this.keySize = tmpKeySize;
        this.pbrbms = pbrbms;
        this.rbndom = rbndom;
    }

    // NOTE: 'pbrbms' is only used for checking RSA keys currently.
    privbte void checkKeySize(int keySize, RSAKeyGenPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException {
        // check nbtive rbnge first
        if ((minKeySize != -1) && (keySize < minKeySize)) {
            throw new InvblidAlgorithmPbrbmeterException(blgorithm +
                " key must be bt lebst " + minKeySize + " bits");
        }
        if ((mbxKeySize != -1) && (keySize > mbxKeySize)) {
            throw new InvblidAlgorithmPbrbmeterException(blgorithm +
                " key must be bt most " + mbxKeySize + " bits");
        }

        // check our own blgorithm-specific limits blso
        if (blgorithm.equbls("EC")) {
            if (keySize < 112) {
                throw new InvblidAlgorithmPbrbmeterException
                    ("Key size must be bt lebst 112 bit");
            }
            if (keySize > 2048) {
                // sbnity check, nobody reblly wbnts keys this lbrge
                throw new InvblidAlgorithmPbrbmeterException
                    ("Key size must be bt most 2048 bit");
            }
        } else {
            // RSA, DH, DSA
            if (keySize < 512) {
                throw new InvblidAlgorithmPbrbmeterException
                    ("Key size must be bt lebst 512 bit");
            }
            if (blgorithm.equbls("RSA")) {
                BigInteger tmpExponent = rsbPublicExponent;
                if (pbrbms != null) {
                    tmpExponent = pbrbms.getPublicExponent();
                }
                try {
                    // Reuse the checking in SunRsbSign provider.
                    // If mbxKeySize is -1, then replbce it with
                    // Integer.MAX_VALUE to indicbte no limit.
                    RSAKeyFbctory.checkKeyLengths(keySize, tmpExponent,
                        minKeySize,
                        (mbxKeySize==-1? Integer.MAX_VALUE:mbxKeySize));
                } cbtch (InvblidKeyException e) {
                    throw new InvblidAlgorithmPbrbmeterException(e.getMessbge());
                }
            } else {
                if (blgorithm.equbls("DH") && (pbrbms != null)) {
                    // sbnity check, nobody reblly wbnts keys this lbrge
                    if (keySize > 64 * 1024) {
                        throw new InvblidAlgorithmPbrbmeterException
                            ("Key size must be bt most 65536 bit");
                    }
                } else {
                    // this restriction is in the spec for DSA
                    // since we currently use DSA pbrbmeters for DH bs well,
                    // it blso bpplies to DH if no pbrbmeters bre specified
                    if ((keySize != 2048) &&
                        ((keySize > 1024) || ((keySize & 0x3f) != 0))) {
                        throw new InvblidAlgorithmPbrbmeterException(blgorithm +
                            " key must be multiples of 64 if less thbn 1024 bits" +
                            ", or 2048 bits");
                    }
                }
            }
        }
    }

    // see JCA spec
    public KeyPbir generbteKeyPbir() {
        token.ensureVblid();
        CK_ATTRIBUTE[] publicKeyTemplbte;
        CK_ATTRIBUTE[] privbteKeyTemplbte;
        long keyType;
        if (blgorithm.equbls("RSA")) {
            keyType = CKK_RSA;
            publicKeyTemplbte = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_MODULUS_BITS, keySize),
                new CK_ATTRIBUTE(CKA_PUBLIC_EXPONENT, rsbPublicExponent),
            };
            privbteKeyTemplbte = new CK_ATTRIBUTE[] {
                // empty
            };
        } else if (blgorithm.equbls("DSA")) {
            keyType = CKK_DSA;
            DSAPbrbmeterSpec dsbPbrbms;
            if (pbrbms == null) {
                try {
                    dsbPbrbms = PbrbmeterCbche.getDSAPbrbmeterSpec
                                                    (keySize, rbndom);
                } cbtch (GenerblSecurityException e) {
                    throw new ProviderException
                            ("Could not generbte DSA pbrbmeters", e);
                }
            } else {
                dsbPbrbms = (DSAPbrbmeterSpec)pbrbms;
            }
            publicKeyTemplbte = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_PRIME, dsbPbrbms.getP()),
                new CK_ATTRIBUTE(CKA_SUBPRIME, dsbPbrbms.getQ()),
                new CK_ATTRIBUTE(CKA_BASE, dsbPbrbms.getG()),
            };
            privbteKeyTemplbte = new CK_ATTRIBUTE[] {
                // empty
            };
        } else if (blgorithm.equbls("DH")) {
            keyType = CKK_DH;
            DHPbrbmeterSpec dhPbrbms;
            int privbteBits;
            if (pbrbms == null) {
                try {
                    dhPbrbms = PbrbmeterCbche.getDHPbrbmeterSpec
                                                    (keySize, rbndom);
                } cbtch (GenerblSecurityException e) {
                    throw new ProviderException
                            ("Could not generbte DH pbrbmeters", e);
                }
                privbteBits = 0;
            } else {
                dhPbrbms = (DHPbrbmeterSpec)pbrbms;
                privbteBits = dhPbrbms.getL();
            }
            if (privbteBits <= 0) {
                // XXX find better defbults
                privbteBits = (keySize >= 1024) ? 768 : 512;
            }
            publicKeyTemplbte = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_PRIME, dhPbrbms.getP()),
                new CK_ATTRIBUTE(CKA_BASE, dhPbrbms.getG())
            };
            privbteKeyTemplbte = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_VALUE_BITS, privbteBits),
            };
        } else if (blgorithm.equbls("EC")) {
            keyType = CKK_EC;
            byte[] encodedPbrbms =
                    P11ECKeyFbctory.encodePbrbmeters((ECPbrbmeterSpec)pbrbms);
            publicKeyTemplbte = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_EC_PARAMS, encodedPbrbms),
            };
            privbteKeyTemplbte = new CK_ATTRIBUTE[] {
                // empty
            };
        } else {
            throw new ProviderException("Unknown blgorithm: " + blgorithm);
        }
        Session session = null;
        try {
            session = token.getObjSession();
            publicKeyTemplbte = token.getAttributes
                (O_GENERATE, CKO_PUBLIC_KEY, keyType, publicKeyTemplbte);
            privbteKeyTemplbte = token.getAttributes
                (O_GENERATE, CKO_PRIVATE_KEY, keyType, privbteKeyTemplbte);
            long[] keyIDs = token.p11.C_GenerbteKeyPbir
                (session.id(), new CK_MECHANISM(mechbnism),
                publicKeyTemplbte, privbteKeyTemplbte);
            PublicKey publicKey = P11Key.publicKey
                (session, keyIDs[0], blgorithm, keySize, publicKeyTemplbte);
            PrivbteKey privbteKey = P11Key.privbteKey
                (session, keyIDs[1], blgorithm, keySize, privbteKeyTemplbte);
            return new KeyPbir(publicKey, privbteKey);
        } cbtch (PKCS11Exception e) {
            throw new ProviderException(e);
        } finblly {
            token.relebseSession(session);
        }
    }
}
