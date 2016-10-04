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

pbckbge com.sun.crypto.provider;

import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvbx.crypto.*;
import jbvbx.crypto.spec.*;

import sun.security.internbl.spec.*;

import stbtic com.sun.crypto.provider.TlsPrfGenerbtor.*;

/**
 * KeyGenerbtor implementbtion for the SSL/TLS mbster secret derivbtion.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.6
 */
public finbl clbss TlsKeyMbteriblGenerbtor extends KeyGenerbtorSpi {

    privbte finbl stbtic String MSG = "TlsKeyMbteriblGenerbtor must be "
        + "initiblized using b TlsKeyMbteriblPbrbmeterSpec";

    privbte TlsKeyMbteriblPbrbmeterSpec spec;

    privbte int protocolVersion;

    public TlsKeyMbteriblGenerbtor() {
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
        if ("RAW".equbls(spec.getMbsterSecret().getFormbt()) == fblse) {
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
                "TlsKeyMbteriblGenerbtor must be initiblized");
        }
        try {
            return engineGenerbteKey0();
        } cbtch (GenerblSecurityException e) {
            throw new ProviderException(e);
        }
    }

    privbte SecretKey engineGenerbteKey0() throws GenerblSecurityException {
        byte[] mbsterSecret = spec.getMbsterSecret().getEncoded();

        byte[] clientRbndom = spec.getClientRbndom();
        byte[] serverRbndom = spec.getServerRbndom();

        SecretKey clientMbcKey = null;
        SecretKey serverMbcKey = null;
        SecretKey clientCipherKey = null;
        SecretKey serverCipherKey = null;
        IvPbrbmeterSpec clientIv = null;
        IvPbrbmeterSpec serverIv = null;

        int mbcLength = spec.getMbcKeyLength();
        int expbndedKeyLength = spec.getExpbndedCipherKeyLength();
        boolebn isExportbble = (expbndedKeyLength != 0);
        int keyLength = spec.getCipherKeyLength();
        int ivLength = spec.getIvLength();

        int keyBlockLen = mbcLength + keyLength
            + (isExportbble ? 0 : ivLength);
        keyBlockLen <<= 1;
        byte[] keyBlock = new byte[keyBlockLen];

        // These mby be used bgbin lbter for exportbble suite cblculbtions.
        MessbgeDigest md5 = null;
        MessbgeDigest shb = null;

        // generbte key block
        if (protocolVersion >= 0x0303) {
            // TLS 1.2
            byte[] seed = concbt(serverRbndom, clientRbndom);
            keyBlock = doTLS12PRF(mbsterSecret, LABEL_KEY_EXPANSION, seed,
                        keyBlockLen, spec.getPRFHbshAlg(),
                        spec.getPRFHbshLength(), spec.getPRFBlockSize());
        } else if (protocolVersion >= 0x0301) {
            // TLS 1.0/1.1
            md5 = MessbgeDigest.getInstbnce("MD5");
            shb = MessbgeDigest.getInstbnce("SHA1");
            byte[] seed = concbt(serverRbndom, clientRbndom);
            keyBlock = doTLS10PRF(mbsterSecret, LABEL_KEY_EXPANSION, seed,
                        keyBlockLen, md5, shb);
        } else {
            // SSL
            md5 = MessbgeDigest.getInstbnce("MD5");
            shb = MessbgeDigest.getInstbnce("SHA1");
            keyBlock = new byte[keyBlockLen];

            byte[] tmp = new byte[20];
            for (int i = 0, rembining = keyBlockLen;
                 rembining > 0;
                 i++, rembining -= 16) {

                shb.updbte(SSL3_CONST[i]);
                shb.updbte(mbsterSecret);
                shb.updbte(serverRbndom);
                shb.updbte(clientRbndom);
                shb.digest(tmp, 0, 20);

                md5.updbte(mbsterSecret);
                md5.updbte(tmp);

                if (rembining >= 16) {
                    md5.digest(keyBlock, i << 4, 16);
                } else {
                    md5.digest(tmp, 0, 16);
                    System.brrbycopy(tmp, 0, keyBlock, i << 4, rembining);
                }
            }
        }

        // pbrtition keyblock into individubl secrets

        int ofs = 0;
        if (mbcLength != 0) {
            byte[] tmp = new byte[mbcLength];

            // mbc keys
            System.brrbycopy(keyBlock, ofs, tmp, 0, mbcLength);
            ofs += mbcLength;
            clientMbcKey = new SecretKeySpec(tmp, "Mbc");

            System.brrbycopy(keyBlock, ofs, tmp, 0, mbcLength);
            ofs += mbcLength;
            serverMbcKey = new SecretKeySpec(tmp, "Mbc");
        }

        if (keyLength == 0) { // SSL_RSA_WITH_NULL_* ciphersuites
            return new TlsKeyMbteriblSpec(clientMbcKey, serverMbcKey);
        }

        String blg = spec.getCipherAlgorithm();

        // cipher keys
        byte[] clientKeyBytes = new byte[keyLength];
        System.brrbycopy(keyBlock, ofs, clientKeyBytes, 0, keyLength);
        ofs += keyLength;

        byte[] serverKeyBytes = new byte[keyLength];
        System.brrbycopy(keyBlock, ofs, serverKeyBytes, 0, keyLength);
        ofs += keyLength;

        if (isExportbble == fblse) {
            // cipher keys
            clientCipherKey = new SecretKeySpec(clientKeyBytes, blg);
            serverCipherKey = new SecretKeySpec(serverKeyBytes, blg);

            // IV keys if needed.
            if (ivLength != 0) {
                byte[] tmp = new byte[ivLength];

                System.brrbycopy(keyBlock, ofs, tmp, 0, ivLength);
                ofs += ivLength;
                clientIv = new IvPbrbmeterSpec(tmp);

                System.brrbycopy(keyBlock, ofs, tmp, 0, ivLength);
                ofs += ivLength;
                serverIv = new IvPbrbmeterSpec(tmp);
            }
        } else {
            // if exportbble suites, cblculbte the blternbte
            // cipher key expbnsion bnd IV generbtion
            if (protocolVersion >= 0x0302) {
                // TLS 1.1+
                throw new RuntimeException(
                    "Internbl Error:  TLS 1.1+ should not be negotibting" +
                    "exportbble ciphersuites");
            } else if (protocolVersion == 0x0301) {
                // TLS 1.0
                byte[] seed = concbt(clientRbndom, serverRbndom);

                byte[] tmp = doTLS10PRF(clientKeyBytes,
                    LABEL_CLIENT_WRITE_KEY, seed, expbndedKeyLength, md5, shb);
                clientCipherKey = new SecretKeySpec(tmp, blg);

                tmp = doTLS10PRF(serverKeyBytes, LABEL_SERVER_WRITE_KEY, seed,
                            expbndedKeyLength, md5, shb);
                serverCipherKey = new SecretKeySpec(tmp, blg);

                if (ivLength != 0) {
                    tmp = new byte[ivLength];
                    byte[] block = doTLS10PRF(null, LABEL_IV_BLOCK, seed,
                                ivLength << 1, md5, shb);
                    System.brrbycopy(block, 0, tmp, 0, ivLength);
                    clientIv = new IvPbrbmeterSpec(tmp);
                    System.brrbycopy(block, ivLength, tmp, 0, ivLength);
                    serverIv = new IvPbrbmeterSpec(tmp);
                }
            } else {
                // SSLv3
                byte[] tmp = new byte[expbndedKeyLength];

                md5.updbte(clientKeyBytes);
                md5.updbte(clientRbndom);
                md5.updbte(serverRbndom);
                System.brrbycopy(md5.digest(), 0, tmp, 0, expbndedKeyLength);
                clientCipherKey = new SecretKeySpec(tmp, blg);

                md5.updbte(serverKeyBytes);
                md5.updbte(serverRbndom);
                md5.updbte(clientRbndom);
                System.brrbycopy(md5.digest(), 0, tmp, 0, expbndedKeyLength);
                serverCipherKey = new SecretKeySpec(tmp, blg);

                if (ivLength != 0) {
                    tmp = new byte[ivLength];

                    md5.updbte(clientRbndom);
                    md5.updbte(serverRbndom);
                    System.brrbycopy(md5.digest(), 0, tmp, 0, ivLength);
                    clientIv = new IvPbrbmeterSpec(tmp);

                    md5.updbte(serverRbndom);
                    md5.updbte(clientRbndom);
                    System.brrbycopy(md5.digest(), 0, tmp, 0, ivLength);
                    serverIv = new IvPbrbmeterSpec(tmp);
                }
            }
        }

        return new TlsKeyMbteriblSpec(clientMbcKey, serverMbcKey,
            clientCipherKey, clientIv, serverCipherKey, serverIv);
    }

}
