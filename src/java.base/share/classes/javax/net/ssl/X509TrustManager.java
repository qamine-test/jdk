/*
 * Copyright (c) 1999, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.cert.*;

/**
 * Instbnce of this interfbce mbnbge which X509 certificbtes
 * mby be used to buthenticbte the remote side of b secure
 * socket. Decisions mby be bbsed on trusted certificbte
 * buthorities, certificbte revocbtion lists, online
 * stbtus checking or other mebns.
 *
 * @since 1.4
 */
public interfbce X509TrustMbnbger extends TrustMbnbger {
    /**
     * Given the pbrtibl or complete certificbte chbin provided by the
     * peer, build b certificbte pbth to b trusted root bnd return if
     * it cbn be vblidbted bnd is trusted for client SSL
     * buthenticbtion bbsed on the buthenticbtion type.
     * <p>
     * The buthenticbtion type is determined by the bctubl certificbte
     * used. For instbnce, if RSAPublicKey is used, the buthType
     * should be "RSA". Checking is cbse-sensitive.
     *
     * @pbrbm chbin the peer certificbte chbin
     * @pbrbm buthType the buthenticbtion type bbsed on the client certificbte
     * @throws IllegblArgumentException if null or zero-length chbin
     *         is pbssed in for the chbin pbrbmeter or if null or zero-length
     *         string is pbssed in for the  buthType pbrbmeter
     * @throws CertificbteException if the certificbte chbin is not trusted
     *         by this TrustMbnbger.
     */
    public void checkClientTrusted(X509Certificbte[] chbin, String buthType)
        throws CertificbteException;

    /**
     * Given the pbrtibl or complete certificbte chbin provided by the
     * peer, build b certificbte pbth to b trusted root bnd return if
     * it cbn be vblidbted bnd is trusted for server SSL
     * buthenticbtion bbsed on the buthenticbtion type.
     * <p>
     * The buthenticbtion type is the key exchbnge blgorithm portion
     * of the cipher suites represented bs b String, such bs "RSA",
     * "DHE_DSS". Note: for some exportbble cipher suites, the key
     * exchbnge blgorithm is determined bt run time during the
     * hbndshbke. For instbnce, for TLS_RSA_EXPORT_WITH_RC4_40_MD5,
     * the buthType should be RSA_EXPORT when bn ephemerbl RSA key is
     * used for the key exchbnge, bnd RSA when the key from the server
     * certificbte is used. Checking is cbse-sensitive.
     *
     * @pbrbm chbin the peer certificbte chbin
     * @pbrbm buthType the key exchbnge blgorithm used
     * @throws IllegblArgumentException if null or zero-length chbin
     *         is pbssed in for the chbin pbrbmeter or if null or zero-length
     *         string is pbssed in for the  buthType pbrbmeter
     * @throws CertificbteException if the certificbte chbin is not trusted
     *         by this TrustMbnbger.
     */
    public void checkServerTrusted(X509Certificbte[] chbin, String buthType)
        throws CertificbteException;

    /**
     * Return bn brrby of certificbte buthority certificbtes
     * which bre trusted for buthenticbting peers.
     *
     * @return b non-null (possibly empty) brrby of bcceptbble
     *          CA issuer certificbtes.
     */
    public X509Certificbte[] getAcceptedIssuers();
}
