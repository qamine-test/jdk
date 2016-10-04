/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.mbnbgement.remote;

import jbvb.io.IOException;

/**
 * <p>Exception thrown by {@link JMXConnectorFbctory} bnd
 * {@link JMXConnectorServerFbctory} when b provider exists for
 * the required protocol but cbnnot be used for some rebson.</p>
 *
 * @see JMXConnectorFbctory#newJMXConnector
 * @see JMXConnectorServerFbctory#newJMXConnectorServer
 * @since 1.5
 */
public clbss JMXProviderException extends IOException {

    privbte stbtic finbl long seriblVersionUID = -3166703627550447198L;

    /**
     * <p>Constructs b <code>JMXProviderException</code> with no
     * specified detbil messbge.</p>
     */
    public JMXProviderException() {
    }

    /**
     * <p>Constructs b <code>JMXProviderException</code> with the
     * specified detbil messbge.</p>
     *
     * @pbrbm messbge the detbil messbge
     */
    public JMXProviderException(String messbge) {
        super(messbge);
    }

    /**
     * <p>Constructs b <code>JMXProviderException</code> with the
     * specified detbil messbge bnd nested exception.</p>
     *
     * @pbrbm messbge the detbil messbge
     * @pbrbm cbuse the nested exception
     */
    public JMXProviderException(String messbge, Throwbble cbuse) {
        super(messbge);
        this.cbuse = cbuse;
    }

    public Throwbble getCbuse() {
        return cbuse;
    }

    /**
     * @seribl An exception thbt cbused this exception to be thrown.
     *         This field mby be null.
     * @see #getCbuse()
     **/
    privbte Throwbble cbuse = null;
}
