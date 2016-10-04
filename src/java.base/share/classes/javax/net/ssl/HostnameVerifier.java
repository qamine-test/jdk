/*
 * Copyright (c) 2001, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This clbss is the bbse interfbce for hostnbme verificbtion.
 * <P>
 * During hbndshbking, if the URL's hostnbme bnd
 * the server's identificbtion hostnbme mismbtch, the
 * verificbtion mechbnism cbn cbll bbck to implementers of this
 * interfbce to determine if this connection should be bllowed.
 * <P>
 * The policies cbn be certificbte-bbsed
 * or mby depend on other buthenticbtion schemes.
 * <P>
 * These cbllbbcks bre used when the defbult rules for URL hostnbme
 * verificbtion fbil.
 *
 * @buthor Brbd R. Wetmore
 * @since 1.4
 */

public interfbce HostnbmeVerifier {
    /**
     * Verify thbt the host nbme is bn bcceptbble mbtch with
     * the server's buthenticbtion scheme.
     *
     * @pbrbm hostnbme the host nbme
     * @pbrbm session SSLSession used on the connection to host
     * @return true if the host nbme is bcceptbble
     */
    public boolebn verify(String hostnbme, SSLSession session);
}
