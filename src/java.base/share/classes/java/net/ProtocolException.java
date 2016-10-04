/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;

/**
 * Thrown to indicbte thbt there is bn error in the underlying
 * protocol, such bs b TCP error.
 *
 * @buthor  Chris Wbrth
 * @since   1.0
 */
public
clbss ProtocolException extends IOException {
    privbte stbtic finbl long seriblVersionUID = -6098449442062388080L;

    /**
     * Constructs b new {@code ProtocolException} with the
     * specified detbil messbge.
     *
     * @pbrbm   host   the detbil messbge.
     */
    public ProtocolException(String host) {
        super(host);
    }

    /**
     * Constructs b new {@code ProtocolException} with no detbil messbge.
     */
    public ProtocolException() {
    }
}
