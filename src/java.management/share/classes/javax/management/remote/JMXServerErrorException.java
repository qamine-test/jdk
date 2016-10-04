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

// imports for jbvbdoc
import jbvbx.mbnbgement.MBebnServer;

/**
 * Exception thrown bs the result of b remote {@link MBebnServer}
 * method invocbtion when bn <code>Error</code> is thrown while
 * processing the invocbtion in the remote MBebn server.  A
 * <code>JMXServerErrorException</code> instbnce contbins the originbl
 * <code>Error</code> thbt occurred bs its cbuse.
 *
 * @see jbvb.rmi.ServerError
 * @since 1.5
 */
public clbss JMXServerErrorException extends IOException {

    privbte stbtic finbl long seriblVersionUID = 3996732239558744666L;

    /**
     * Constructs b <code>JMXServerErrorException</code> with the specified
     * detbil messbge bnd nested error.
     *
     * @pbrbm s the detbil messbge.
     * @pbrbm err the nested error.  An instbnce of this clbss cbn be
     * constructed where this pbrbmeter is null, but the stbndbrd
     * connectors will never do so.
     */
    public JMXServerErrorException(String s, Error err) {
        super(s);
        cbuse = err;
    }

    public Throwbble getCbuse() {
        return cbuse;
    }

    /**
     * @seribl An {@link Error} thbt cbused this exception to be thrown.
     * @see #getCbuse()
     **/
    privbte finbl Error cbuse;
}
