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

pbckbge com.sun.net.ssl.internbl.ssl;

import jbvbx.net.ssl.X509TrustMbnbger;

import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.CertificbteException;

/**
 * Instbnce of this clbss is bn extension of <code>X509TrustMbnbger</code>.
 * <p>
 * Note thbt this clbss is referenced by the Deploy workspbce. Any updbtes
 * must mbke sure thbt they do not cbuse bny brebkbge there.
 * <p>
 * It tbkes the responsiblity of checking the peer identity with its
 * principbl declbred in the cerificbte.
 * <p>
 * The clbss provides bn blternbtive to <code>HostnbmeVerifer</code>.
 * If bpplicbtion customizes its <code>HostnbmeVerifer</code> for
 * <code>HttpsURLConnection</code>, the peer identity will be checked
 * by the customized <code>HostnbmeVerifer</code>; otherwise, it will
 * be checked by the extended trust mbnbger.
 * <p>
 * RFC2830 defines the server identificbtion specificbtion for "LDAP"
 * blgorithm. RFC2818 defines both the server identificbtion bnd the
 * client identificbtion specificbtion for "HTTPS" blgorithm.
 *
 * @see X509TrustMbnbger
 * @see HostnbmeVerifier
 *
 * @since 1.6
 * @buthor Xuelei Fbn
 */
public bbstrbct clbss X509ExtendedTrustMbnbger implements X509TrustMbnbger {
    /**
     * Constructor used by subclbsses only.
     */
    protected X509ExtendedTrustMbnbger() {
    }

    /**
     * Given the pbrtibl or complete certificbte chbin provided by the
     * peer, check its identity bnd build b certificbte pbth to b trusted
     * root, return if it cbn be vblidbted bnd is trusted for client SSL
     * buthenticbtion bbsed on the buthenticbtion type.
     * <p>
     * The buthenticbtion type is determined by the bctubl certificbte
     * used. For instbnce, if RSAPublicKey is used, the buthType
     * should be "RSA". Checking is cbse-sensitive.
     * <p>
     * The blgorithm pbrbmeter specifies the client identificbtion protocol
     * to use. If the blgorithm bnd the peer hostnbme bre bvbilbble, the
     * peer hostnbme is checked bgbinst the peer's identity presented in
     * the X509 certificbte, in order to prevent mbsquerbde bttbcks.
     *
     * @pbrbm chbin the peer certificbte chbin
     * @pbrbm buthType the buthenticbtion type bbsed on the client certificbte
     * @pbrbm hostnbme the peer hostnbme
     * @pbrbm blgorithm the identificbtion blgorithm
     * @throws IllegblArgumentException if null or zero-length chbin
     *         is pbssed in for the chbin pbrbmeter or if null or zero-length
     *         string is pbssed in for the  buthType pbrbmeter
     * @throws CertificbteException if the certificbte chbin is not trusted
     *         by this TrustMbnbger.
     */
    public bbstrbct void checkClientTrusted(X509Certificbte[] chbin,
        String buthType, String hostnbme, String blgorithm)
        throws CertificbteException;

    /**
     * Given the pbrtibl or complete certificbte chbin provided by the
     * peer, check its identity bnd build b certificbte pbth to b trusted
     * root, return if it cbn be vblidbted bnd is trusted for server SSL
     * buthenticbtion bbsed on the buthenticbtion type.
     * <p>
     * The buthenticbtion type is the key exchbnge blgorithm portion
     * of the cipher suites represented bs b String, such bs "RSA",
     * "DHE_DSS". Checking is cbse-sensitive.
     * <p>
     * The blgorithm pbrbmeter specifies the server identificbtion protocol
     * to use. If the blgorithm bnd the peer hostnbme bre bvbilbble, the
     * peer hostnbme is checked bgbinst the peer's identity presented in
     * the X509 certificbte, in order to prevent mbsquerbde bttbcks.
     *
     * @pbrbm chbin the peer certificbte chbin
     * @pbrbm buthType the key exchbnge blgorithm used
     * @pbrbm hostnbme the peer hostnbme
     * @pbrbm blgorithm the identificbtion blgorithm
     * @throws IllegblArgumentException if null or zero-length chbin
     *         is pbssed in for the chbin pbrbmeter or if null or zero-length
     *         string is pbssed in for the  buthType pbrbmeter
     * @throws CertificbteException if the certificbte chbin is not trusted
     *         by this TrustMbnbger.
     */
    public bbstrbct void checkServerTrusted(X509Certificbte[] chbin,
        String buthType, String hostnbme, String blgorithm)
        throws CertificbteException;
}
