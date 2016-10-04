/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Pbrbmeters for SSL/TLS mbster secret generbtion.
 * This clbss encbpsulbtes the informbtion necessbry to cblculbte b SSL/TLS
 * mbster secret from the prembster secret bnd other pbrbmeters.
 * It is used to initiblize KeyGenerbtors of the type "TlsMbsterSecret".
 *
 * <p>Instbnces of this clbss bre immutbble.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @deprecbted Sun JDK internbl use only --- WILL BE REMOVED in b future
 * relebse.
 */
@Deprecbted
public clbss TlsMbsterSecretPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    privbte finbl SecretKey prembsterSecret;
    privbte finbl int mbjorVersion, minorVersion;
    privbte finbl byte[] clientRbndom, serverRbndom;
    privbte finbl String prfHbshAlg;
    privbte finbl int prfHbshLength;
    privbte finbl int prfBlockSize;

    /**
     * Constructs b new TlsMbsterSecretPbrbmeterSpec.
     *
     * <p>The <code>getAlgorithm()</code> method of <code>prembsterSecret</code>
     * should return <code>"TlsRsbPrembsterSecret"</code> if the key exchbnge
     * blgorithm wbs RSA bnd <code>"TlsPrembsterSecret"</code> otherwise.
     *
     * @pbrbm prembsterSecret the prembster secret
     * @pbrbm mbjorVersion the mbjor number of the protocol version
     * @pbrbm minorVersion the minor number of the protocol version
     * @pbrbm clientRbndom the client's rbndom vblue
     * @pbrbm serverRbndom the server's rbndom vblue
     * @pbrbm prfHbshAlg the nbme of the TLS PRF hbsh blgorithm to use.
     *        Used only for TLS 1.2+.  TLS1.1 bnd ebrlier use b fixed PRF.
     * @pbrbm prfHbshLength the output length of the TLS PRF hbsh blgorithm.
     *        Used only for TLS 1.2+.
     * @pbrbm prfBlockSize the input block size of the TLS PRF hbsh blgorithm.
     *        Used only for TLS 1.2+.
     *
     * @throws NullPointerException if prembsterSecret, clientRbndom,
     *   or serverRbndom bre null
     * @throws IllegblArgumentException if minorVersion or mbjorVersion bre
     *   negbtive or lbrger thbn 255
     */
    public TlsMbsterSecretPbrbmeterSpec(SecretKey prembsterSecret,
            int mbjorVersion, int minorVersion,
            byte[] clientRbndom, byte[] serverRbndom,
            String prfHbshAlg, int prfHbshLength, int prfBlockSize) {
        if (prembsterSecret == null) {
            throw new NullPointerException("prembsterSecret must not be null");
        }
        this.prembsterSecret = prembsterSecret;
        this.mbjorVersion = checkVersion(mbjorVersion);
        this.minorVersion = checkVersion(minorVersion);
        this.clientRbndom = clientRbndom.clone();
        this.serverRbndom = serverRbndom.clone();
        this.prfHbshAlg = prfHbshAlg;
        this.prfHbshLength = prfHbshLength;
        this.prfBlockSize = prfBlockSize;
    }

    stbtic int checkVersion(int version) {
        if ((version < 0) || (version > 255)) {
            throw new IllegblArgumentException(
                        "Version must be between 0 bnd 255");
        }
        return version;
    }

    /**
     * Returns the prembster secret.
     *
     * @return the prembster secret.
     */
    public SecretKey getPrembsterSecret() {
        return prembsterSecret;
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
     * @return the hbsh blgorithm block size.
     */
    public int getPRFBlockSize() {
        return prfBlockSize;
    }
}
