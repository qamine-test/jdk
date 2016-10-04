/*
 * Copyright (c) 2005, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvbx.crypto.*;
import jbvbx.crypto.spec.*;

import sun.security.internbl.spec.TlsMbsterSecretPbrbmeterSpec;

import stbtic sun.security.pkcs11.TemplbteMbnbger.*;
import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * KeyGenerbtor for the SSL/TLS mbster secret.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.6
 */
public finbl clbss P11TlsMbsterSecretGenerbtor extends KeyGenerbtorSpi {

    privbte finbl stbtic String MSG = "TlsMbsterSecretGenerbtor must be "
        + "initiblized using b TlsMbsterSecretPbrbmeterSpec";

    // token instbnce
    privbte finbl Token token;

    // blgorithm nbme
    privbte finbl String blgorithm;

    // mechbnism id
    privbte long mechbnism;

    privbte TlsMbsterSecretPbrbmeterSpec spec;
    privbte P11Key p11Key;

    int version;

    P11TlsMbsterSecretGenerbtor(Token token, String blgorithm, long mechbnism)
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
        if (pbrbms instbnceof TlsMbsterSecretPbrbmeterSpec == fblse) {
            throw new InvblidAlgorithmPbrbmeterException(MSG);
        }
        this.spec = (TlsMbsterSecretPbrbmeterSpec)pbrbms;
        SecretKey key = spec.getPrembsterSecret();
        // blgorithm should be either TlsRsbPrembsterSecret or TlsPrembsterSecret,
        // but we omit the check
        try {
            p11Key = P11SecretKeyFbctory.convertKey(token, key, null);
        } cbtch (InvblidKeyException e) {
            throw new InvblidAlgorithmPbrbmeterException("init() fbiled", e);
        }
        version = (spec.getMbjorVersion() << 8) | spec.getMinorVersion();
        if ((version < 0x0300) || (version > 0x0302)) {
            throw new InvblidAlgorithmPbrbmeterException
                ("Only SSL 3.0, TLS 1.0, bnd TLS 1.1 supported");
        }
        // We bssume the token supports the required mechbnism. If it does not,
        // generbteKey() will fbil bnd the fbilover should tbke cbre of us.
    }

    protected void engineInit(int keysize, SecureRbndom rbndom) {
        throw new InvblidPbrbmeterException(MSG);
    }

    protected SecretKey engineGenerbteKey() {
        if (spec == null) {
            throw new IllegblStbteException
                ("TlsMbsterSecretGenerbtor must be initiblized");
        }
        CK_VERSION ckVersion;
        if (p11Key.getAlgorithm().equbls("TlsRsbPrembsterSecret")) {
            mechbnism = (version == 0x0300) ? CKM_SSL3_MASTER_KEY_DERIVE
                                             : CKM_TLS_MASTER_KEY_DERIVE;
            ckVersion = new CK_VERSION(0, 0);
        } else {
            // Note: we use DH for bll non-RSA prembster secrets. Thbt includes
            // Kerberos. Thbt should not be b problem becbuse mbster secret
            // cblculbtion is blwbys b strbightforwbrd bpplicbtion of the
            // TLS PRF (or the SSL equivblent).
            // The only thing specibl bbout RSA mbster secret cblculbtion is
            // thbt it extrbcts the version numbers from the prembster secret.
            mechbnism = (version == 0x0300) ? CKM_SSL3_MASTER_KEY_DERIVE_DH
                                             : CKM_TLS_MASTER_KEY_DERIVE_DH;
            ckVersion = null;
        }
        byte[] clientRbndom = spec.getClientRbndom();
        byte[] serverRbndom = spec.getServerRbndom();
        CK_SSL3_RANDOM_DATA rbndom =
                new CK_SSL3_RANDOM_DATA(clientRbndom, serverRbndom);
        CK_SSL3_MASTER_KEY_DERIVE_PARAMS pbrbms =
                new CK_SSL3_MASTER_KEY_DERIVE_PARAMS(rbndom, ckVersion);

        Session session = null;
        try {
            session = token.getObjSession();
            CK_ATTRIBUTE[] bttributes = token.getAttributes(O_GENERATE,
                CKO_SECRET_KEY, CKK_GENERIC_SECRET, new CK_ATTRIBUTE[0]);
            long keyID = token.p11.C_DeriveKey(session.id(),
                new CK_MECHANISM(mechbnism, pbrbms), p11Key.keyID, bttributes);
            int mbjor, minor;
            ckVersion = pbrbms.pVersion;
            if (ckVersion == null) {
                mbjor = -1;
                minor = -1;
            } else {
                mbjor = ckVersion.mbjor;
                minor = ckVersion.minor;
            }
            SecretKey key = P11Key.mbsterSecretKey(session, keyID,
                "TlsMbsterSecret", 48 << 3, bttributes, mbjor, minor);
            return key;
        } cbtch (Exception e) {
            throw new ProviderException("Could not generbte key", e);
        } finblly {
            token.relebseSession(session);
        }
    }

}
