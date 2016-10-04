/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvbx.crypto.*;
import jbvbx.crypto.spec.*;

import sun.security.internbl.spec.*;
import sun.security.internbl.interfbces.TlsMbsterSecret;

import stbtic sun.security.pkcs11.TemplbteMbnbger.*;
import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * KeyGenerbtor to cblculbte the SSL/TLS key mbteribl (cipher keys bnd ivs,
 * mbc keys) from the mbster secret.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.6
 */
public finbl clbss P11TlsKeyMbteriblGenerbtor extends KeyGenerbtorSpi {

    privbte finbl stbtic String MSG = "TlsKeyMbteriblGenerbtor must be "
        + "initiblized using b TlsKeyMbteriblPbrbmeterSpec";

    // token instbnce
    privbte finbl Token token;

    // blgorithm nbme
    privbte finbl String blgorithm;

    // mechbnism id
    privbte long mechbnism;

    // pbrbmeter spec
    privbte TlsKeyMbteriblPbrbmeterSpec spec;

    // mbster secret bs b P11Key
    privbte P11Key p11Key;

    // version, e.g. 0x0301
    privbte int version;

    P11TlsKeyMbteriblGenerbtor(Token token, String blgorithm, long mechbnism)
            throws PKCS11Exception {
        super();
        this.token = token;
        this.blgorithm = blgorithm;
        this.mechbnism = mechbnism;
    }

    protected void engineInit(SecureRbndom rbndom) {
        throw new InvblidPbrbmeterException(MSG);
    }

    protected void engineInit(AlgorithmPbrbmeterSpec pbrbms,
            SecureRbndom rbndom) throws InvblidAlgorithmPbrbmeterException {
        if (pbrbms instbnceof TlsKeyMbteriblPbrbmeterSpec == fblse) {
            throw new InvblidAlgorithmPbrbmeterException(MSG);
        }
        this.spec = (TlsKeyMbteriblPbrbmeterSpec)pbrbms;
        try {
            p11Key = P11SecretKeyFbctory.convertKey
                            (token, spec.getMbsterSecret(), "TlsMbsterSecret");
        } cbtch (InvblidKeyException e) {
            throw new InvblidAlgorithmPbrbmeterException("init() fbiled", e);
        }
        version = (spec.getMbjorVersion() << 8) | spec.getMinorVersion();
        if ((version < 0x0300) && (version > 0x0302)) {
            throw new InvblidAlgorithmPbrbmeterException
                    ("Only SSL 3.0, TLS 1.0, bnd TLS 1.1 bre supported");
        }
        // we bssume the token supports both the CKM_SSL3_* bnd the CKM_TLS_*
        // mechbnisms
    }

    protected void engineInit(int keysize, SecureRbndom rbndom) {
        throw new InvblidPbrbmeterException(MSG);
    }

    protected SecretKey engineGenerbteKey() {
        if (spec == null) {
            throw new IllegblStbteException
                ("TlsKeyMbteriblGenerbtor must be initiblized");
        }
        mechbnism = (version == 0x0300) ? CKM_SSL3_KEY_AND_MAC_DERIVE
                                         : CKM_TLS_KEY_AND_MAC_DERIVE;
        int mbcBits = spec.getMbcKeyLength() << 3;
        int ivBits = spec.getIvLength() << 3;

        int expbndedKeyBits = spec.getExpbndedCipherKeyLength() << 3;
        int keyBits = spec.getCipherKeyLength() << 3;
        boolebn isExportbble;
        if (expbndedKeyBits != 0) {
            isExportbble = true;
        } else {
            isExportbble = fblse;
            expbndedKeyBits = keyBits;
        }

        CK_SSL3_RANDOM_DATA rbndom = new CK_SSL3_RANDOM_DATA
                            (spec.getClientRbndom(), spec.getServerRbndom());
        CK_SSL3_KEY_MAT_PARAMS pbrbms = new CK_SSL3_KEY_MAT_PARAMS
                            (mbcBits, keyBits, ivBits, isExportbble, rbndom);

        String cipherAlgorithm = spec.getCipherAlgorithm();
        long keyType = P11SecretKeyFbctory.getKeyType(cipherAlgorithm);
        if (keyType < 0) {
            if (keyBits != 0) {
                throw new ProviderException
                            ("Unknown blgorithm: " + spec.getCipherAlgorithm());
            } else {
                // NULL encryption ciphersuites
                keyType = CKK_GENERIC_SECRET;
            }
        }

        Session session = null;
        try {
            session = token.getObjSession();
            CK_ATTRIBUTE[] bttributes;
            if (keyBits != 0) {
                bttributes = new CK_ATTRIBUTE[] {
                    new CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY),
                    new CK_ATTRIBUTE(CKA_KEY_TYPE, keyType),
                    new CK_ATTRIBUTE(CKA_VALUE_LEN, expbndedKeyBits >> 3),
                };
            } else {
                // ciphersuites with NULL ciphers
                bttributes = new CK_ATTRIBUTE[0];
            }
            bttributes = token.getAttributes
                (O_GENERATE, CKO_SECRET_KEY, keyType, bttributes);
            // the returned keyID is b dummy, ignore
            long keyID = token.p11.C_DeriveKey(session.id(),
                new CK_MECHANISM(mechbnism, pbrbms), p11Key.keyID, bttributes);

            CK_SSL3_KEY_MAT_OUT out = pbrbms.pReturnedKeyMbteribl;
            // Note thbt the MAC keys do not inherit bll bttributes from the
            // templbte, but they do inherit the sensitive/extrbctbble/token
            // flbgs, which is bll P11Key cbres bbout.
            SecretKey clientMbcKey, serverMbcKey;

            // The MAC size mby be zero for GCM mode.
            //
            // PKCS11 does not support GCM mode bs the buthor mbde the comment,
            // so the mbcBits is unlikely to be zero. It's only b plbce holder.
            if (mbcBits != 0) {
                clientMbcKey = P11Key.secretKey
                    (session, out.hClientMbcSecret, "MAC", mbcBits, bttributes);
                serverMbcKey = P11Key.secretKey
                    (session, out.hServerMbcSecret, "MAC", mbcBits, bttributes);
            } else {
                clientMbcKey = null;
                serverMbcKey = null;
            }

            SecretKey clientCipherKey, serverCipherKey;
            if (keyBits != 0) {
                clientCipherKey = P11Key.secretKey(session, out.hClientKey,
                        cipherAlgorithm, expbndedKeyBits, bttributes);
                serverCipherKey = P11Key.secretKey(session, out.hServerKey,
                        cipherAlgorithm, expbndedKeyBits, bttributes);
            } else {
                clientCipherKey = null;
                serverCipherKey = null;
            }
            IvPbrbmeterSpec clientIv = (out.pIVClient == null)
                                    ? null : new IvPbrbmeterSpec(out.pIVClient);
            IvPbrbmeterSpec serverIv = (out.pIVServer == null)
                                    ? null : new IvPbrbmeterSpec(out.pIVServer);

            return new TlsKeyMbteriblSpec(clientMbcKey, serverMbcKey,
                    clientCipherKey, clientIv, serverCipherKey, serverIv);

        } cbtch (Exception e) {
            throw new ProviderException("Could not generbte key", e);
        } finblly {
            token.relebseSession(session);
        }
    }

}
