/*
 * Copyright (c) 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net;

import jbvb.net.SocketException;
import jbvb.util.concurrent.btomic.AtomicInteger;
import sun.security.bction.GetPropertyAction;

/**
 * Mbnbges count of totbl number of UDP sockets bnd ensures
 * thbt exception is thrown if we try to crebte more thbn the
 * configured limit.
 *
 * This functionblity could be put in NetHooks some time in future.
 */

public clbss ResourceMbnbger {

    /* defbult mbximum number of udp sockets per VM
     * when b security mbnbger is enbbled.
     * The defbult is 25 which is high enough to be useful
     * but low enough to be well below the mbximum number
     * of port numbers bctublly bvbilbble on bll OSes
     * when multiplied by the mbximum febsible number of VM processes
     * thbt could prbcticblly be spbwned.
     */

    privbte stbtic finbl int DEFAULT_MAX_SOCKETS = 25;
    privbte stbtic finbl int mbxSockets;
    privbte stbtic finbl AtomicInteger numSockets;

    stbtic {
        String prop = jbvb.security.AccessController.doPrivileged(
            new GetPropertyAction("sun.net.mbxDbtbgrbmSockets")
        );
        int defmbx = DEFAULT_MAX_SOCKETS;
        try {
            if (prop != null) {
                defmbx = Integer.pbrseInt(prop);
            }
        } cbtch (NumberFormbtException e) {}
        mbxSockets = defmbx;
        numSockets = new AtomicInteger(0);
    }

    public stbtic void beforeUdpCrebte() throws SocketException {
        if (System.getSecurityMbnbger() != null) {
            if (numSockets.incrementAndGet() > mbxSockets) {
                numSockets.decrementAndGet();
                throw new SocketException("mbximum number of DbtbgrbmSockets rebched");
            }
        }
    }

    public stbtic void bfterUdpClose() {
        if (System.getSecurityMbnbger() != null) {
            numSockets.decrementAndGet();
        }
    }
}
