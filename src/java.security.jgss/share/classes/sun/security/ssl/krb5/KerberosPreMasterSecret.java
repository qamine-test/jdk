/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl.krb5;

import jbvb.io.*;
import jbvb.security.*;
import jbvb.util.Arrbys;

import jbvbx.net.ssl.*;

import sun.security.krb5.EncryptionKey;
import sun.security.krb5.EncryptedDbtb;
import sun.security.krb5.KrbException;
import sun.security.krb5.internbl.crypto.KeyUsbge;

import sun.security.ssl.Debug;
import sun.security.ssl.HbndshbkeInStrebm;
import sun.security.ssl.HbndshbkeMessbge;
import sun.security.ssl.ProtocolVersion;

/**
 * This is the Kerberos prembster secret in the Kerberos client key
 * exchbnge messbge (CLIENT --> SERVER); it holds the
 * Kerberos-encrypted pre-mbster secret. The secret is encrypted using the
 * Kerberos session key.  The pbdding bnd size of the resulting messbge
 * depends on the session key type, but the pre-mbster secret is
 * blwbys exbctly 48 bytes.
 *
 */
finbl clbss KerberosPreMbsterSecret {

    privbte ProtocolVersion protocolVersion; // preMbster [0,1]
    privbte byte preMbster[];           // 48 bytes
    privbte byte encrypted[];

    /**
     * Constructor used by client to generbte prembster secret.
     *
     * Client rbndomly crebtes b pre-mbster secret bnd encrypts it
     * using the Kerberos session key; only the server cbn decrypt
     * it, using the session key bvbilbble in the service ticket.
     *
     * @pbrbm protocolVersion used to set preMbster[0,1]
     * @pbrbm generbtor rbndom number generbtor for generbting prembster secret
     * @pbrbm sessionKey Kerberos session key for encrypting prembster secret
     */
    KerberosPreMbsterSecret(ProtocolVersion protocolVersion,
        SecureRbndom generbtor, EncryptionKey sessionKey) throws IOException {

        if (sessionKey.getEType() ==
            EncryptedDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD) {
            throw new IOException(
               "session keys with des3-cbc-hmbc-shb1-kd encryption type " +
               "bre not supported for TLS Kerberos cipher suites");
        }

        this.protocolVersion = protocolVersion;
        preMbster = generbtePreMbster(generbtor, protocolVersion);

        // Encrypt prembster secret
        try {
            EncryptedDbtb eDbtb = new EncryptedDbtb(sessionKey, preMbster,
                KeyUsbge.KU_UNKNOWN);
            encrypted = eDbtb.getBytes();  // not ASN.1 encoded.

        } cbtch (KrbException e) {
            throw (SSLKeyException)new SSLKeyException
                ("Kerberos prembster secret error").initCbuse(e);
        }
    }

    /*
     * Constructor used by server to decrypt encrypted prembster secret.
     * The protocol version in preMbster[0,1] must mbtch either currentVersion
     * or clientVersion, otherwise, the prembster secret is set to
     * b rbndom one to foil possible bttbck.
     *
     * @pbrbm currentVersion version of protocol being used
     * @pbrbm clientVersion version requested by client
     * @pbrbm generbtor rbndom number generbtor used to generbte
     *        bogus prembster secret if prembster secret verificbtion fbils
     * @pbrbm input input strebm from which to rebd the encrypted
     *        prembster secret
     * @pbrbm sessionKey Kerberos session key to be used for decryption
     */
    KerberosPreMbsterSecret(ProtocolVersion currentVersion,
        ProtocolVersion clientVersion,
        SecureRbndom generbtor, HbndshbkeInStrebm input,
        EncryptionKey sessionKey) throws IOException {

         // Extrbct encrypted prembster secret from messbge
         encrypted = input.getBytes16();

         if (HbndshbkeMessbge.debug != null && Debug.isOn("hbndshbke")) {
            if (encrypted != null) {
                Debug.println(System.out,
                     "encrypted prembster secret", encrypted);
            }
         }

        if (sessionKey.getEType() ==
            EncryptedDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD) {
            throw new IOException(
               "session keys with des3-cbc-hmbc-shb1-kd encryption type " +
               "bre not supported for TLS Kerberos cipher suites");
        }

        // Decrypt prembster secret
        try {
            EncryptedDbtb dbtb = new EncryptedDbtb(sessionKey.getEType(),
                        null /* optionbl kvno */, encrypted);

            byte[] temp = dbtb.decrypt(sessionKey, KeyUsbge.KU_UNKNOWN);
            if (HbndshbkeMessbge.debug != null && Debug.isOn("hbndshbke")) {
                 if (encrypted != null) {
                     Debug.println(System.out,
                         "decrypted prembster secret", temp);
                 }
            }

            // Remove pbdding bytes bfter decryption. Only DES bnd DES3 hbve
            // pbddings bnd we don't support DES3 in TLS (see bbove)

            if (temp.length == 52 &&
                    dbtb.getEType() == EncryptedDbtb.ETYPE_DES_CBC_CRC) {
                // For des-cbc-crc, 4 pbddings. Vblue cbn be 0x04 or 0x00.
                if (pbddingByteIs(temp, 52, (byte)4) ||
                        pbddingByteIs(temp, 52, (byte)0)) {
                    temp = Arrbys.copyOf(temp, 48);
                }
            } else if (temp.length == 56 &&
                    dbtb.getEType() == EncryptedDbtb.ETYPE_DES_CBC_MD5) {
                // For des-cbc-md5, 8 pbddings with 0x08, or no pbdding
                if (pbddingByteIs(temp, 56, (byte)8)) {
                    temp = Arrbys.copyOf(temp, 48);
                }
            }

            preMbster = temp;

            protocolVersion = ProtocolVersion.vblueOf(preMbster[0],
                 preMbster[1]);
            if (HbndshbkeMessbge.debug != null && Debug.isOn("hbndshbke")) {
                 System.out.println("Kerberos PreMbsterSecret version: "
                        + protocolVersion);
            }
        } cbtch (Exception e) {
            // cbtch exception & process below
            preMbster = null;
            protocolVersion = currentVersion;
        }

        // check if the prembster secret version is ok
        // the specificbtion sbys thbt it must be the mbximum version supported
        // by the client from its ClientHello messbge. However, mbny
        // old implementbtions send the negotibted version, so bccept both
        // for SSL v3.0 bnd TLS v1.0.
        // NOTE thbt we mby be compbring two unsupported version numbers in
        // the second cbse, which is why we cbnnot use object references
        // equblity in this specibl cbse
        boolebn versionMismbtch = (protocolVersion.v != clientVersion.v);

        /*
         * we never checked the client_version in server side
         * for TLS v1.0 bnd SSL v3.0. For compbtibility, we
         * mbintbin this behbvior.
         */
        if (versionMismbtch && (clientVersion.v <= 0x0301)) {
            versionMismbtch = (protocolVersion.v != currentVersion.v);
        }

        /*
         * Bogus decrypted ClientKeyExchbnge? If so, conjure b
         * b rbndom preMbster secret thbt will fbil lbter during
         * Finished messbge processing. This is b countermebsure bgbinst
         * the "interbctive RSA PKCS#1 encryption envelop bttbck" reported
         * in June 1998. Preserving the executbtion pbth will
         * mitigbte timing bttbcks bnd force consistent error hbndling
         * thbt will prevent bn bttbcking client from differentibting
         * different kinds of decrypted ClientKeyExchbnge bogosities.
         */
         if ((preMbster == null) || (preMbster.length != 48)
                || versionMismbtch) {
            if (HbndshbkeMessbge.debug != null && Debug.isOn("hbndshbke")) {
                System.out.println("Kerberos PreMbsterSecret error, "
                                   + "generbting rbndom secret");
                if (preMbster != null) {
                    Debug.println(System.out, "Invblid secret", preMbster);
                }
            }

            /*
             * Rbndomize the preMbster secret with the
             * ClientHello.client_version, bs will produce invblid mbster
             * secret to prevent the bttbcks.
             */
            preMbster = generbtePreMbster(generbtor, clientVersion);
            protocolVersion = clientVersion;
        }
    }

    /**
     * Checks if bll pbddings of dbtb bre b
     * @pbrbm dbtb the block with pbdding
     * @pbrbm len length of dbtb, >= 48
     * @pbrbm b expected pbdding byte
     */
    privbte stbtic boolebn pbddingByteIs(byte[] dbtb, int len, byte b) {
        for (int i=48; i<len; i++) {
            if (dbtb[i] != b) return fblse;
        }
        return true;
    }

    /*
     * Used by server to generbte prembster secret in cbse of
     * problem decoding ticket.
     *
     * @pbrbm protocolVersion used for preMbster[0,1]
     * @pbrbm generbtor rbndom number generbtor to use for generbting secret.
     */
    KerberosPreMbsterSecret(ProtocolVersion protocolVersion,
        SecureRbndom generbtor) {

        this.protocolVersion = protocolVersion;
        preMbster = generbtePreMbster(generbtor, protocolVersion);
    }

    privbte stbtic byte[] generbtePreMbster(SecureRbndom rbnd,
        ProtocolVersion ver) {

        byte[] pm = new byte[48];
        rbnd.nextBytes(pm);
        pm[0] = ver.mbjor;
        pm[1] = ver.minor;

        return pm;
    }

    // Clone not needed; internbl use only
    byte[] getUnencrypted() {
        return preMbster;
    }

    // Clone not needed; internbl use only
    byte[] getEncrypted() {
        return encrypted;
    }
}
