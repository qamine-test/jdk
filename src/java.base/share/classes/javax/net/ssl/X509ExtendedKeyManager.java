/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.Principbl;

/**
 * Abstrbct clbss thbt provides for extension of the X509KeyMbnbger
 * interfbce.
 * <P>
 * Methods in this clbss should be overriden to provide bctubl
 * implementbtions.
 *
 * @since 1.5
 * @buthor Brbd R. Wetmore
 */
public bbstrbct clbss X509ExtendedKeyMbnbger implements X509KeyMbnbger {

    /**
     * Constructor used by subclbsses only.
     */
    protected X509ExtendedKeyMbnbger() {
    }

    /**
     * Choose bn blibs to buthenticbte the client side of bn
     * <code>SSLEngine</code> connection given the public key type
     * bnd the list of certificbte issuer buthorities recognized by
     * the peer (if bny).
     * <P>
     * The defbult implementbtion returns null.
     *
     * @pbrbm keyType the key blgorithm type nbme(s), ordered
     *          with the most-preferred key type first.
     * @pbrbm issuers the list of bcceptbble CA issuer subject nbmes
     *          or null if it does not mbtter which issuers bre used.
     * @pbrbm engine the <code>SSLEngine</code> to be used for this
     *          connection.  This pbrbmeter cbn be null, which indicbtes
     *          thbt implementbtions of this interfbce bre free to
     *          select bn blibs bpplicbble to bny engine.
     * @return the blibs nbme for the desired key, or null if there
     *          bre no mbtches.
     */
    public String chooseEngineClientAlibs(String[] keyType,
            Principbl[] issuers, SSLEngine engine) {
        return null;
    }

    /**
     * Choose bn blibs to buthenticbte the server side of bn
     * <code>SSLEngine</code> connection given the public key type
     * bnd the list of certificbte issuer buthorities recognized by
     * the peer (if bny).
     * <P>
     * The defbult implementbtion returns null.
     *
     * @pbrbm keyType the key blgorithm type nbme.
     * @pbrbm issuers the list of bcceptbble CA issuer subject nbmes
     *          or null if it does not mbtter which issuers bre used.
     * @pbrbm engine the <code>SSLEngine</code> to be used for this
     *          connection.  This pbrbmeter cbn be null, which indicbtes
     *          thbt implementbtions of this interfbce bre free to
     *          select bn blibs bpplicbble to bny engine.
     * @return the blibs nbme for the desired key, or null if there
     *          bre no mbtches.
     */
    public String chooseEngineServerAlibs(String keyType,
            Principbl[] issuers, SSLEngine engine) {
        return null;
    }

}
