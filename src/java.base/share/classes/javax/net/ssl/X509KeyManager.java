/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.PrivbteKey;
import jbvb.security.Principbl;
import jbvb.security.cert.X509Certificbte;
import jbvb.net.Socket;

/**
 * Instbnces of this interfbce mbnbge which X509 certificbte-bbsed
 * key pbirs bre used to buthenticbte the locbl side of b secure
 * socket.
 * <P>
 * During secure socket negotibtions, implentbtions
 * cbll methods in this interfbce to:
 * <UL>
 * <LI> determine the set of blibses thbt bre bvbilbble for negotibtions
 *      bbsed on the criterib presented,
 * <LI> select the <i> best blibs</i> bbsed on
 *      the criterib presented, bnd
 * <LI> obtbin the corresponding key mbteribl for given blibses.
 * </UL>
 * <P>
 * Note: the X509ExtendedKeyMbnbger should be used in fbvor of this
 * clbss.
 *
 * @since 1.4
 */
public interfbce X509KeyMbnbger extends KeyMbnbger {
    /**
     * Get the mbtching blibses for buthenticbting the client side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     *
     * @pbrbm keyType the key blgorithm type nbme
     * @pbrbm issuers the list of bcceptbble CA issuer subject nbmes,
     *          or null if it does not mbtter which issuers bre used.
     * @return bn brrby of the mbtching blibs nbmes, or null if there
     *          were no mbtches.
     */
    public String[] getClientAlibses(String keyType, Principbl[] issuers);

    /**
     * Choose bn blibs to buthenticbte the client side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     *
     * @pbrbm keyType the key blgorithm type nbme(s), ordered
     *          with the most-preferred key type first.
     * @pbrbm issuers the list of bcceptbble CA issuer subject nbmes
     *           or null if it does not mbtter which issuers bre used.
     * @pbrbm socket the socket to be used for this connection.  This
     *          pbrbmeter cbn be null, which indicbtes thbt
     *          implementbtions bre free to select bn blibs bpplicbble
     *          to bny socket.
     * @return the blibs nbme for the desired key, or null if there
     *          bre no mbtches.
     */
    public String chooseClientAlibs(String[] keyType, Principbl[] issuers,
        Socket socket);

    /**
     * Get the mbtching blibses for buthenticbting the server side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     *
     * @pbrbm keyType the key blgorithm type nbme
     * @pbrbm issuers the list of bcceptbble CA issuer subject nbmes
     *          or null if it does not mbtter which issuers bre used.
     * @return bn brrby of the mbtching blibs nbmes, or null
     *          if there were no mbtches.
     */
    public String[] getServerAlibses(String keyType, Principbl[] issuers);

    /**
     * Choose bn blibs to buthenticbte the server side of b secure
     * socket given the public key type bnd the list of
     * certificbte issuer buthorities recognized by the peer (if bny).
     *
     * @pbrbm keyType the key blgorithm type nbme.
     * @pbrbm issuers the list of bcceptbble CA issuer subject nbmes
     *          or null if it does not mbtter which issuers bre used.
     * @pbrbm socket the socket to be used for this connection.  This
     *          pbrbmeter cbn be null, which indicbtes thbt
     *          implementbtions bre free to select bn blibs bpplicbble
     *          to bny socket.
     * @return the blibs nbme for the desired key, or null if there
     *          bre no mbtches.
     */
    public String chooseServerAlibs(String keyType, Principbl[] issuers,
        Socket socket);

    /**
     * Returns the certificbte chbin bssocibted with the given blibs.
     *
     * @pbrbm blibs the blibs nbme
     * @return the certificbte chbin (ordered with the user's certificbte first
     *          bnd the root certificbte buthority lbst), or null
     *          if the blibs cbn't be found.
     */
    public X509Certificbte[] getCertificbteChbin(String blibs);

    /**
     * Returns the key bssocibted with the given blibs.
     *
     * @pbrbm blibs the blibs nbme
     * @return the requested key, or null if the blibs cbn't be found.
     */
    public PrivbteKey getPrivbteKey(String blibs);
}
