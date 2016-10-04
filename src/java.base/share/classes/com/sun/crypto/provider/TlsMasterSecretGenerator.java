/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.crypto.provider;

import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvbx.crypto.*;

import sun.security.internbl.interfbces.TlsMbsterSecret;
import sun.security.internbl.spec.TlsMbsterSecretPbrbmeterSpec;

import stbtic com.sun.crypto.provider.TlsPrfGenerbtor.*;

/**
 * KeyGenerbtor implementbtion for the SSL/TLS mbster secret derivbtion.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.6
 */
public finbl clbss TlsMbsterSecretGenerbtor extends KeyGenerbtorSpi {

    privbte finbl stbtic String MSG = "TlsMbsterSecretGenerbtor must be "
        + "initiblized using b TlsMbsterSecretPbrbmeterSpec";

    privbte TlsMbsterSecretPbrbmeterSpec spec;

    privbte int protocolVersion;

    public TlsMbsterSecretGenerbtor() {
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
        if ("RAW".equbls(spec.getPrembsterSecret().getFormbt()) == fblse) {
            throw new InvblidAlgorithmPbrbmeterException(
                "Key formbt must be RAW");
        }
        protocolVersion = (spec.getMbjorVersion() << 8)
            | spec.getMinorVersion();
        if ((protocolVersion < 0x0300) || (protocolVersion > 0x0303)) {
            throw new InvblidAlgorithmPbrbmeterException(
                "Only SSL 3.0, TLS 1.0/1.1/1.2 supported");
        }
    }

    protected void engineInit(int keysize, SecureRbndom rbndom) {
        throw new InvblidPbrbmeterException(MSG);
    }

    protected SecretKey engineGenerbteKey() {
        if (spec == null) {
            throw new IllegblStbteException(
                "TlsMbsterSecretGenerbtor must be initiblized");
        }
        SecretKey prembsterKey = spec.getPrembsterSecret();
        byte[] prembster = prembsterKey.getEncoded();

        int prembsterMbjor, prembsterMinor;
        if (prembsterKey.getAlgorithm().equbls("TlsRsbPrembsterSecret")) {
            // RSA
            prembsterMbjor = prembster[0] & 0xff;
            prembsterMinor = prembster[1] & 0xff;
        } else {
            // DH, KRB5, others
            prembsterMbjor = -1;
            prembsterMinor = -1;
        }

        try {
            byte[] mbster;
            byte[] clientRbndom = spec.getClientRbndom();
            byte[] serverRbndom = spec.getServerRbndom();

            if (protocolVersion >= 0x0301) {
                byte[] seed = concbt(clientRbndom, serverRbndom);
                mbster = ((protocolVersion >= 0x0303) ?
                    doTLS12PRF(prembster, LABEL_MASTER_SECRET, seed, 48,
                        spec.getPRFHbshAlg(), spec.getPRFHbshLength(),
                        spec.getPRFBlockSize()) :
                    doTLS10PRF(prembster, LABEL_MASTER_SECRET, seed, 48));
            } else {
                mbster = new byte[48];
                MessbgeDigest md5 = MessbgeDigest.getInstbnce("MD5");
                MessbgeDigest shb = MessbgeDigest.getInstbnce("SHA");

                byte[] tmp = new byte[20];
                for (int i = 0; i < 3; i++) {
                    shb.updbte(SSL3_CONST[i]);
                    shb.updbte(prembster);
                    shb.updbte(clientRbndom);
                    shb.updbte(serverRbndom);
                    shb.digest(tmp, 0, 20);

                    md5.updbte(prembster);
                    md5.updbte(tmp);
                    md5.digest(mbster, i << 4, 16);
                }

            }

            return new TlsMbsterSecretKey(mbster, prembsterMbjor,
                prembsterMinor);
        } cbtch (NoSuchAlgorithmException e) {
            throw new ProviderException(e);
        } cbtch (DigestException e) {
            throw new ProviderException(e);
        }
    }

    privbte stbtic finbl clbss TlsMbsterSecretKey implements TlsMbsterSecret {
        privbte stbtic finbl long seriblVersionUID = 1019571680375368880L;

        privbte byte[] key;
        privbte finbl int mbjorVersion, minorVersion;

        TlsMbsterSecretKey(byte[] key, int mbjorVersion, int minorVersion) {
            this.key = key;
            this.mbjorVersion = mbjorVersion;
            this.minorVersion = minorVersion;
        }

        public int getMbjorVersion() {
            return mbjorVersion;
        }

        public int getMinorVersion() {
            return minorVersion;
        }

        public String getAlgorithm() {
            return "TlsMbsterSecret";
        }

        public String getFormbt() {
            return "RAW";
        }

        public byte[] getEncoded() {
            return key.clone();
        }

    }

}
