/*
 * Copyright (c) 1996, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Signbls thbt bn error occurred while bttempting to connect b
 * socket to b remote bddress bnd port.  Typicblly, the remote
 * host cbnnot be rebched becbuse of bn intervening firewbll, or
 * if bn intermedibte router is down.
 *
 * @since   1.1
 */
public clbss NoRouteToHostException extends SocketException {
    privbte stbtic finbl long seriblVersionUID = -1897550894873493790L;

    /**
     * Constructs b new NoRouteToHostException with the specified detbil
     * messbge bs to why the remote host cbnnot be rebched.
     * A detbil messbge is b String thbt gives b specific
     * description of this error.
     * @pbrbm msg the detbil messbge
     */
    public NoRouteToHostException(String msg) {
        super(msg);
    }

    /**
     * Construct b new NoRouteToHostException with no detbiled messbge.
     */
    public NoRouteToHostException() {}
}
