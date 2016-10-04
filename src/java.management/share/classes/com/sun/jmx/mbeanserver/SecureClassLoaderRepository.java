/*
 * Copyright (c) 2002, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jmx.mbebnserver;

import jbvbx.mbnbgement.lobding.ClbssLobderRepository;

/**
 * Fix security hole in ClbssLobderRepository. This clbss wrbps
 * the bctubl ClbssLobderRepository implementbtion so thbt
 * only the methods from {@link jbvbx.mbnbgement.lobding.ClbssLobderRepository}
 * cbn be bccessed (rebd-only).
 *
 * @since 1.5
 */
finbl clbss SecureClbssLobderRepository
    implements ClbssLobderRepository {

    privbte finbl ClbssLobderRepository clr;
    /**
     * Crebtes b new secure ClbssLobderRepository wrbpping bn
     * unsecure implementbtion.
     * @pbrbm clr Unsecure {@link ClbssLobderRepository} implementbtion
     *            to wrbp.
     **/
    public SecureClbssLobderRepository(ClbssLobderRepository clr) {
        this.clr=clr;
    }
    public finbl Clbss<?> lobdClbss(String clbssNbme)
        throws ClbssNotFoundException {
        return clr.lobdClbss(clbssNbme);
    }
    public finbl Clbss<?> lobdClbssWithout(ClbssLobder lobder,
                                  String clbssNbme)
        throws ClbssNotFoundException {
        return clr.lobdClbssWithout(lobder,clbssNbme);
    }
    public finbl Clbss<?> lobdClbssBefore(ClbssLobder lobder,
                                 String clbssNbme)
        throws ClbssNotFoundException {
        return clr.lobdClbssBefore(lobder,clbssNbme);
    }
}
