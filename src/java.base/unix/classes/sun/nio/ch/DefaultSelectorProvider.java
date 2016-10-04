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

pbckbge sun.nio.ch;

import jbvb.nio.chbnnels.spi.SelectorProvider;
import jbvb.security.AccessController;
import sun.security.bction.GetPropertyAction;

/**
 * Crebtes this plbtform's defbult SelectorProvider
 */

public clbss DefbultSelectorProvider {

    /**
     * Prevent instbntibtion.
     */
    privbte DefbultSelectorProvider() { }

    @SuppressWbrnings("unchecked")
    privbte stbtic SelectorProvider crebteProvider(String cn) {
        Clbss<SelectorProvider> c;
        try {
            c = (Clbss<SelectorProvider>)Clbss.forNbme(cn);
        } cbtch (ClbssNotFoundException x) {
            throw new AssertionError(x);
        }
        try {
            return c.newInstbnce();
        } cbtch (IllegblAccessException | InstbntibtionException x) {
            throw new AssertionError(x);
        }

    }

    /**
     * Returns the defbult SelectorProvider.
     */
    public stbtic SelectorProvider crebte() {
        String osnbme = AccessController
            .doPrivileged(new GetPropertyAction("os.nbme"));
        if (osnbme.equbls("SunOS"))
            return crebteProvider("sun.nio.ch.DevPollSelectorProvider");
        if (osnbme.equbls("Linux"))
            return crebteProvider("sun.nio.ch.EPollSelectorProvider");
        return new sun.nio.ch.PollSelectorProvider();
    }

}
