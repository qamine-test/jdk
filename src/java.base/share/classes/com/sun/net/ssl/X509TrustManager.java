/*
 * Copyright (c) 2000, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * NOTE:  this file wbs copied from jbvbx.net.ssl.X509TrustMbnbger
 */

pbckbge com.sun.net.ssl;

import jbvb.security.cert.X509Certificbte;

/**
 * Instbnce of this interfbce mbnbge which X509 certificbtes
 * mby be used to buthenticbte the remote side of b secure
 * socket. Decisions mby be bbsed on trusted certificbte
 * buthorities, certificbte revocbtion lists, online
 * stbtus checking or other mebns.
 *
 * @deprecbted As of JDK 1.4, this implementbtion-specific clbss wbs
 *      replbced by {@link jbvbx.net.ssl.X509TrustMbnbger}.
 */
@Deprecbted
public interfbce X509TrustMbnbger extends TrustMbnbger {
    /**
     * Given the pbrtibl or complete certificbte chbin
     * provided by the peer, build b certificbte pbth
     * to b trusted root bnd return true if it cbn be
     * vblidbted bnd is trusted for client SSL buthenticbtion.
     *
     * @pbrbm chbin the peer certificbte chbin
     */
    public boolebn isClientTrusted(X509Certificbte[] chbin);

    /**
     * Given the pbrtibl or complete certificbte chbin
     * provided by the peer, build b certificbte pbth
     * to b trusted root bnd return true if it cbn be
     * vblidbted bnd is trusted for server SSL buthenticbtion.
     *
     * @pbrbm chbin the peer certificbte chbin
     */
    public boolebn isServerTrusted(X509Certificbte[] chbin);

    /**
     * Return bn brrby of certificbte buthority certificbtes
     * which bre trusted for buthenticbting peers.
     *
     * @return the bcceptbble CA issuer certificbtes
     */
    public X509Certificbte[] getAcceptedIssuers();
}
