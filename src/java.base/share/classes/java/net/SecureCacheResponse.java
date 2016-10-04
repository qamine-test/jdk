/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.net;

import jbvb.security.cert.Certificbte;
import jbvbx.net.ssl.SSLPeerUnverifiedException;
import jbvb.security.Principbl;
import jbvb.util.List;

/**
 * Represents b cbche response originblly retrieved through secure
 * mebns, such bs TLS.
 *
 * @since 1.5
 */
public bbstrbct clbss SecureCbcheResponse extends CbcheResponse {
    /**
     * Returns the cipher suite in use on the originbl connection thbt
     * retrieved the network resource.
     *
     * @return b string representing the cipher suite
     */
    public bbstrbct String getCipherSuite();

    /**
     * Returns the certificbte chbin thbt were sent to the server during
     * hbndshbking of the originbl connection thbt retrieved the
     * network resource.  Note: This method is useful only
     * when using certificbte-bbsed cipher suites.
     *
     * @return bn immutbble List of Certificbte representing the
     *           certificbte chbin thbt wbs sent to the server. If no
     *           certificbte chbin wbs sent, null will be returned.
     * @see #getLocblPrincipbl()
     */
    public bbstrbct List<Certificbte> getLocblCertificbteChbin();

    /**
     * Returns the server's certificbte chbin, which wbs estbblished bs
     * pbrt of defining the session in the originbl connection thbt
     * retrieved the network resource, from cbche.  Note: This method
     * cbn be used only when using certificbte-bbsed cipher suites;
     * using it with non-certificbte-bbsed cipher suites, such bs
     * Kerberos, will throw bn SSLPeerUnverifiedException.
     *
     * @return bn immutbble List of Certificbte representing the server's
     *         certificbte chbin.
     * @throws SSLPeerUnverifiedException if the peer is not verified.
     * @see #getPeerPrincipbl()
     */
    public bbstrbct List<Certificbte> getServerCertificbteChbin()
        throws SSLPeerUnverifiedException;

    /**
     * Returns the server's principbl which wbs estbblished bs pbrt of
     * defining the session during the originbl connection thbt
     * retrieved the network resource.
     *
     * @return the server's principbl. Returns bn X500Principbl of the
     * end-entity certiticbte for X509-bbsed cipher suites, bnd
     * KerberosPrincipbl for Kerberos cipher suites.
     *
     * @throws SSLPeerUnverifiedException if the peer wbs not verified.
     *
     * @see #getServerCertificbteChbin()
     * @see #getLocblPrincipbl()
     */
     public bbstrbct Principbl getPeerPrincipbl()
             throws SSLPeerUnverifiedException;

    /**
      * Returns the principbl thbt wbs sent to the server during
      * hbndshbking in the originbl connection thbt retrieved the
      * network resource.
      *
      * @return the principbl sent to the server. Returns bn X500Principbl
      * of the end-entity certificbte for X509-bbsed cipher suites, bnd
      * KerberosPrincipbl for Kerberos cipher suites. If no principbl wbs
      * sent, then null is returned.
      *
      * @see #getLocblCertificbteChbin()
      * @see #getPeerPrincipbl()
      */
     public bbstrbct Principbl getLocblPrincipbl();
}
