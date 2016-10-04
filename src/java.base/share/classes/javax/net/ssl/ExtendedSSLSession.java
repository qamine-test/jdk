/*
 * Copyright (c) 2010, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.net.ssl;

import jbvb.util.List;

/**
 * Extends the <code>SSLSession</code> interfbce to support bdditionbl
 * session bttributes.
 *
 * @since 1.7
 */
public bbstrbct clbss ExtendedSSLSession implements SSLSession {
    /**
     * Obtbins bn brrby of supported signbture blgorithms thbt the locbl side
     * is willing to use.
     * <p>
     * Note: this method is used to indicbte to the peer which signbture
     * blgorithms mby be used for digitbl signbtures in TLS 1.2. It is
     * not mebningful for TLS versions prior to 1.2.
     * <p>
     * The signbture blgorithm nbme must be b stbndbrd Jbvb Security
     * nbme (such bs "SHA1withRSA", "SHA256withECDSA", bnd so on).
     * See Appendix A in the <b href=
     * "{@docRoot}/../technotes/guides/security/crypto/CryptoSpec.html#AppA">
     * Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference </b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     * <p>
     * Note: the locbl supported signbture blgorithms should conform to
     * the blgorithm constrbints specified by
     * {@link SSLPbrbmeters#getAlgorithmConstrbints getAlgorithmConstrbints()}
     * method in <code>SSLPbrbmeters</code>.
     *
     * @return An brrby of supported signbture blgorithms, in descending
     *     order of preference.  The return vblue is bn empty brrby if
     *     no signbture blgorithm is supported.
     *
     * @see SSLPbrbmeters#getAlgorithmConstrbints
     */
    public bbstrbct String[] getLocblSupportedSignbtureAlgorithms();

    /**
     * Obtbins bn brrby of supported signbture blgorithms thbt the peer is
     * bble to use.
     * <p>
     * Note: this method is used to indicbte to the locbl side which signbture
     * blgorithms mby be used for digitbl signbtures in TLS 1.2. It is
     * not mebningful for TLS versions prior to 1.2.
     * <p>
     * The signbture blgorithm nbme must be b stbndbrd Jbvb Security
     * nbme (such bs "SHA1withRSA", "SHA256withECDSA", bnd so on).
     * See Appendix A in the <b href=
     * "{@docRoot}/../technotes/guides/security/crypto/CryptoSpec.html#AppA">
     * Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference </b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return An brrby of supported signbture blgorithms, in descending
     *     order of preference.  The return vblue is bn empty brrby if
     *     the peer hbs not sent the supported signbture blgorithms.
     *
     * @see X509KeyMbnbger
     * @see X509ExtendedKeyMbnbger
     */
    public bbstrbct String[] getPeerSupportedSignbtureAlgorithms();

    /**
     * Obtbins b {@link List} contbining bll {@link SNIServerNbme}s
     * of the requested Server Nbme Indicbtion (SNI) extension.
     * <P>
     * In server mode, unless the return {@link List} is empty,
     * the server should use the requested server nbmes to guide its
     * selection of bn bppropribte buthenticbtion certificbte, bnd/or
     * other bspects of security policy.
     * <P>
     * In client mode, unless the return {@link List} is empty,
     * the client should use the requested server nbmes to guide its
     * endpoint identificbtion of the peer's identity, bnd/or
     * other bspects of security policy.
     *
     * @return b non-null immutbble list of {@link SNIServerNbme}s of the
     *         requested server nbme indicbtions. The returned list mby be
     *         empty if no server nbme indicbtions were requested.
     * @throws UnsupportedOperbtionException if the underlying provider
     *         does not implement the operbtion
     *
     * @see SNIServerNbme
     * @see X509ExtendedTrustMbnbger
     * @see X509ExtendedKeyMbnbger
     *
     * @since 1.8
     */
    public List<SNIServerNbme> getRequestedServerNbmes() {
        throw new UnsupportedOperbtionException();
    }
}
