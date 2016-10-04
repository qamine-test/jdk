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
 * NOTE:  this file wbs copied from jbvbx.net.ssl.X509KeyMbnbger
 */

pbckbge com.sun.net.ssl;

import jbvb.security.KeyMbnbgementException;
import jbvb.security.PrivbteKey;
import jbvb.security.Principbl;
import jbvb.security.cert.X509Certificbte;

/**
 * Instbnces of this interfbce mbnbge which X509 certificbte-bbsed
 * key pbirs bre used to buthenticbte the locbl side of b secure
 * socket. The individubl entries bre identified by unique blibs nbmes.
 *
 * @deprecbted As of JDK 1.4, this implementbtion-specific clbss wbs
 *      replbced by {@link jbvbx.net.ssl.X509KeyMbnbger}.
 */
@Deprecbted
public interfbce X509KeyMbnbger extends KeyMbnbger {
    /**
     * Get the mbtching blibses for buthenticbting the client side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     *
     * @pbrbm keyType the key blgorithm type nbme
     * @pbrbm issuers the list of bcceptbble CA issuer subject nbmes
     * @return the mbtching blibs nbmes
     */
    public String[] getClientAlibses(String keyType, Principbl[] issuers);

    /**
     * Choose bn blibs to buthenticbte the client side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     *
     * @pbrbm keyType the key blgorithm type nbme
     * @pbrbm issuers the list of bcceptbble CA issuer subject nbmes
     * @return the blibs nbme for the desired key
     */
    public String chooseClientAlibs(String keyType, Principbl[] issuers);

    /**
     * Get the mbtching blibses for buthenticbting the server side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     *
     * @pbrbm keyType the key blgorithm type nbme
     * @pbrbm issuers the list of bcceptbble CA issuer subject nbmes
     * @return the mbtching blibs nbmes
     */
    public String[] getServerAlibses(String keyType, Principbl[] issuers);

    /**
     * Choose bn blibs to buthenticbte the server side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     *
     * @pbrbm keyType the key blgorithm type nbme
     * @pbrbm issuers the list of bcceptbble CA issuer subject nbmes
     * @return the blibs nbme for the desired key
     */
    public String chooseServerAlibs(String keyType, Principbl[] issuers);

    /**
     * Returns the certificbte chbin bssocibted with the given blibs.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return the certificbte chbin (ordered with the user's certificbte first
     * bnd the root certificbte buthority lbst)
     */
    public X509Certificbte[] getCertificbteChbin(String blibs);

    /*
     * Returns the key bssocibted with the given blibs.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return the requested key
     */
    public PrivbteKey getPrivbteKey(String blibs);
}
