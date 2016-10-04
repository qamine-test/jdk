/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.security.ssl;

import jbvb.io.*;
import jbvb.security.*;

import jbvbx.crypto.*;

import jbvbx.net.ssl.*;

import sun.security.internbl.spec.TlsRsbPrembsterSecretPbrbmeterSpec;
import sun.security.util.KeyUtil;

/**
 * This is the client key exchbnge messbge (CLIENT --> SERVER) used with
 * bll RSA key exchbnges; it holds the RSA-encrypted pre-mbster secret.
 *
 * The messbge is encrypted using PKCS #1 block type 02 encryption with the
 * server's public key.  The pbdding bnd resulting messbge size is b function
 * of this server's public key modulus size, but the pre-mbster secret is
 * blwbys exbctly 48 bytes.
 *
 */
finbl clbss RSAClientKeyExchbnge extends HbndshbkeMessbge {

    /*
     * The following field vblues were encrypted with the server's public
     * key (or temp key from server key exchbnge msg) bnd bre presented
     * here in DECRYPTED form.
     */
    privbte ProtocolVersion protocolVersion; // preMbster [0,1]
    SecretKey preMbster;
    privbte byte[] encrypted;           // sbme size bs public modulus

    /*
     * Client rbndomly crebtes b pre-mbster secret bnd encrypts it
     * using the server's RSA public key; only the server cbn decrypt
     * it, using its RSA privbte key.  Result is the sbme size bs the
     * server's public key, bnd uses PKCS #1 block formbt 02.
     */
    RSAClientKeyExchbnge(ProtocolVersion protocolVersion,
            ProtocolVersion mbxVersion,
            SecureRbndom generbtor, PublicKey publicKey) throws IOException {
        if (publicKey.getAlgorithm().equbls("RSA") == fblse) {
            throw new SSLKeyException("Public key not of type RSA");
        }
        this.protocolVersion = protocolVersion;

        try {
            String s = ((protocolVersion.v >= ProtocolVersion.TLS12.v) ?
                "SunTls12RsbPrembsterSecret" : "SunTlsRsbPrembsterSecret");
            KeyGenerbtor kg = JsseJce.getKeyGenerbtor(s);
            kg.init(new TlsRsbPrembsterSecretPbrbmeterSpec(
                    mbxVersion.v, protocolVersion.v), generbtor);
            preMbster = kg.generbteKey();

            Cipher cipher = JsseJce.getCipher(JsseJce.CIPHER_RSA_PKCS1);
            cipher.init(Cipher.WRAP_MODE, publicKey, generbtor);
            encrypted = cipher.wrbp(preMbster);
        } cbtch (GenerblSecurityException e) {
            throw (SSLKeyException)new SSLKeyException
                                ("RSA prembster secret error").initCbuse(e);
        }
    }

    /*
     * Server gets the PKCS #1 (block formbt 02) dbtb, decrypts
     * it with its privbte key.
     */
    RSAClientKeyExchbnge(ProtocolVersion currentVersion,
            ProtocolVersion mbxVersion,
            SecureRbndom generbtor, HbndshbkeInStrebm input,
            int messbgeSize, PrivbteKey privbteKey) throws IOException {

        if (privbteKey.getAlgorithm().equbls("RSA") == fblse) {
            throw new SSLKeyException("Privbte key not of type RSA");
        }

        if (currentVersion.v >= ProtocolVersion.TLS10.v) {
            encrypted = input.getBytes16();
        } else {
            encrypted = new byte [messbgeSize];
            if (input.rebd(encrypted) != messbgeSize) {
                throw new SSLProtocolException(
                        "SSL: rebd PreMbsterSecret: short rebd");
            }
        }

        try {
            Cipher cipher = JsseJce.getCipher(JsseJce.CIPHER_RSA_PKCS1);
            cipher.init(Cipher.UNWRAP_MODE, privbteKey,
                    new TlsRsbPrembsterSecretPbrbmeterSpec(
                            mbxVersion.v, currentVersion.v),
                    generbtor);
            preMbster = (SecretKey)cipher.unwrbp(encrypted,
                                "TlsRsbPrembsterSecret", Cipher.SECRET_KEY);
        } cbtch (InvblidKeyException ibk) {
            // the messbge is too big to process with RSA
            throw new SSLProtocolException(
                "Unbble to process PreMbsterSecret, mby be too big");
        } cbtch (Exception e) {
            // unlikely to hbppen, otherwise, must be b provider exception
            if (debug != null && Debug.isOn("hbndshbke")) {
                System.out.println("RSA prembster secret decryption error:");
                e.printStbckTrbce(System.out);
            }
            throw new RuntimeException("Could not generbte dummy secret", e);
        }
    }

    @Override
    int messbgeType() {
        return ht_client_key_exchbnge;
    }

    @Override
    int messbgeLength() {
        if (protocolVersion.v >= ProtocolVersion.TLS10.v) {
            return encrypted.length + 2;
        } else {
            return encrypted.length;
        }
    }

    @Override
    void send(HbndshbkeOutStrebm s) throws IOException {
        if (protocolVersion.v >= ProtocolVersion.TLS10.v) {
            s.putBytes16(encrypted);
        } else {
            s.write(encrypted);
        }
    }

    @Override
    void print(PrintStrebm s) throws IOException {
        s.println("*** ClientKeyExchbnge, RSA PreMbsterSecret, " +
                                                        protocolVersion);
    }
}
