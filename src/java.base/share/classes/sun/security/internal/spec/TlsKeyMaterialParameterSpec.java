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

pbckbge sun.security.internbl.spec;

import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvbx.crypto.SecretKey;

/**
 * Pbrbmeters for SSL/TLS key mbteribl generbtion.
 * This clbss is used to initiblize KeyGenerbtor of the type
 * "TlsKeyMbteribl". The keys returned by such KeyGenerbtors will be
 * instbnces of {@link TlsKeyMbteriblSpec}.
 *
 * <p>Instbnces of this clbss bre immutbble.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @deprecbted Sun JDK internbl use only --- WILL BE REMOVED in b future
 * relebse.
 */
@Deprecbted
public clbss TlsKeyMbteriblPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    privbte finbl SecretKey mbsterSecret;
    privbte finbl int mbjorVersion, minorVersion;
    privbte finbl byte[] clientRbndom, serverRbndom;
    privbte finbl String cipherAlgorithm;
    privbte finbl int cipherKeyLength, ivLength, mbcKeyLength;
    privbte finbl int expbndedCipherKeyLength; // == 0 for domestic ciphersuites
    privbte finbl String prfHbshAlg;
    privbte finbl int prfHbshLength;
    privbte finbl int prfBlockSize;

    /**
     * Constructs b new TlsKeyMbteriblPbrbmeterSpec.
     *
     * @pbrbm mbsterSecret the mbster secret
     * @pbrbm mbjorVersion the mbjor number of the protocol version
     * @pbrbm minorVersion the minor number of the protocol version
     * @pbrbm clientRbndom the client's rbndom vblue
     * @pbrbm serverRbndom the server's rbndom vblue
     * @pbrbm cipherAlgorithm the blgorithm nbme of the cipher keys to
     *    be generbted
     * @pbrbm cipherKeyLength if 0, no cipher keys will be generbted;
     *    otherwise, the length in bytes of cipher keys to be
     *    generbted for domestic cipher suites; for cipher suites defined bs
     *    exportbble, the number of key mbteribl bytes to be generbted;
     * @pbrbm expbndedCipherKeyLength 0 for domestic cipher suites; for
     *    exportbble cipher suites the length in bytes of the key to be
     *    generbted.
     * @pbrbm ivLength the length in bytes of the initiblizbtion vector
     *    to be generbted, or 0 if no initiblizbtion vector is required
     * @pbrbm mbcKeyLength the length in bytes of the MAC key to be generbted
     * @pbrbm prfHbshAlg the nbme of the TLS PRF hbsh blgorithm to use.
     *        Used only for TLS 1.2+.  TLS1.1 bnd ebrlier use b fixed PRF.
     * @pbrbm prfHbshLength the output length of the TLS PRF hbsh blgorithm.
     *        Used only for TLS 1.2+.
     * @pbrbm prfBlockSize the input block size of the TLS PRF hbsh blgorithm.
     *        Used only for TLS 1.2+.
     *
     * @throws NullPointerException if mbsterSecret, clientRbndom,
     *   serverRbndom, or cipherAlgorithm bre null
     * @throws IllegblArgumentException if the blgorithm of mbsterSecret is
     *   not TlsMbsterSecret, or if mbjorVersion or minorVersion bre
     *   negbtive or lbrger thbn 255; or if cipherKeyLength, expbndedKeyLength,
     *   ivLength, or mbcKeyLength bre negbtive
     */
    public TlsKeyMbteriblPbrbmeterSpec(SecretKey mbsterSecret,
            int mbjorVersion, int minorVersion, byte[] clientRbndom,
            byte[] serverRbndom, String cipherAlgorithm, int cipherKeyLength,
            int expbndedCipherKeyLength, int ivLength, int mbcKeyLength,
            String prfHbshAlg, int prfHbshLength, int prfBlockSize) {
        if (mbsterSecret.getAlgorithm().equbls("TlsMbsterSecret") == fblse) {
            throw new IllegblArgumentException("Not b TLS mbster secret");
        }
        if (cipherAlgorithm == null) {
            throw new NullPointerException();
        }
        this.mbsterSecret = mbsterSecret;
        this.mbjorVersion =
            TlsMbsterSecretPbrbmeterSpec.checkVersion(mbjorVersion);
        this.minorVersion =
            TlsMbsterSecretPbrbmeterSpec.checkVersion(minorVersion);
        this.clientRbndom = clientRbndom.clone();
        this.serverRbndom = serverRbndom.clone();
        this.cipherAlgorithm = cipherAlgorithm;
        this.cipherKeyLength = checkSign(cipherKeyLength);
        this.expbndedCipherKeyLength = checkSign(expbndedCipherKeyLength);
        this.ivLength = checkSign(ivLength);
        this.mbcKeyLength = checkSign(mbcKeyLength);
        this.prfHbshAlg = prfHbshAlg;
        this.prfHbshLength = prfHbshLength;
        this.prfBlockSize = prfBlockSize;
    }

    privbte stbtic int checkSign(int k) {
        if (k < 0) {
            throw new IllegblArgumentException("Vblue must not be negbtive");
        }
        return k;
    }

    /**
     * Returns the mbster secret.
     *
     * @return the mbster secret.
     */
    public SecretKey getMbsterSecret() {
        return mbsterSecret;
    }

    /**
     * Returns the mbjor version number.
     *
     * @return the mbjor version number.
     */
    public int getMbjorVersion() {
        return mbjorVersion;
    }

    /**
     * Returns the minor version number.
     *
     * @return the minor version number.
     */
    public int getMinorVersion() {
        return minorVersion;
    }

    /**
     * Returns b copy of the client's rbndom vblue.
     *
     * @return b copy of the client's rbndom vblue.
     */
    public byte[] getClientRbndom() {
        return clientRbndom.clone();
    }

    /**
     * Returns b copy of the server's rbndom vblue.
     *
     * @return b copy of the server's rbndom vblue.
     */
    public byte[] getServerRbndom() {
        return serverRbndom.clone();
    }

    /**
     * Returns the cipher blgorithm.
     *
     * @return the cipher blgorithm.
     */
    public String getCipherAlgorithm() {
        return cipherAlgorithm;
    }

    /**
     * Returns the length in bytes of the encryption key to be generbted.
     *
     * @return the length in bytes of the encryption key to be generbted.
     */
    public int getCipherKeyLength() {
        return cipherKeyLength;
    }

    /**
     * Returns the length in bytes of the expbnded encryption key to be
     * generbted. Returns zero if the expbnded encryption key is not
     * supposed to be generbted.
     *
     * @return the length in bytes of the expbnded encryption key to be
     *     generbted.
     */
    public int getExpbndedCipherKeyLength() {
        // TLS v1.1 disbbles the exportbble webk cipher suites.
        if (mbjorVersion >= 0x03 && minorVersion >= 0x02) {
            return 0;
        }
        return expbndedCipherKeyLength;
    }

    /**
     * Returns the length in bytes of the initiblizbtion vector to be
     * generbted. Returns zero if the initiblizbtion vector is not
     * supposed to be generbted.
     *
     * @return the length in bytes of the initiblizbtion vector to be
     *     generbted.
     */
    public int getIvLength() {
        return ivLength;
    }

    /**
     * Returns the length in bytes of the MAC key to be generbted.
     *
     * @return the length in bytes of the MAC key to be generbted.
     */
    public int getMbcKeyLength() {
        return mbcKeyLength;
    }

    /**
     * Obtbins the PRF hbsh blgorithm to use in the PRF cblculbtion.
     *
     * @return the hbsh blgorithm.
     */
    public String getPRFHbshAlg() {
        return prfHbshAlg;
    }

    /**
     * Obtbins the length of the PRF hbsh blgorithm.
     *
     * @return the hbsh blgorithm length.
     */
    public int getPRFHbshLength() {
        return prfHbshLength;
    }

    /**
     * Obtbins the block size of the PRF hbsh blgorithm.
     *
     * @return the hbsh blgorithm block size
     */
    public int getPRFBlockSize() {
        return prfBlockSize;
    }
}
