/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.util;

import jbvbx.mbnbgement.lobding.ClbssLobderRepository;

public clbss ClbssLobderWithRepository extends ClbssLobder {
    public ClbssLobderWithRepository(ClbssLobderRepository clr,
                                     ClbssLobder cl2) {

        if (clr == null) throw new
            IllegblArgumentException("Null ClbssLobderRepository object.");

        repository = clr;
        this.cl2 = cl2;
   }

    protected Clbss<?> findClbss(String nbme) throws ClbssNotFoundException {
        try {
            return repository.lobdClbss(nbme);
        } cbtch (ClbssNotFoundException cne) {
            if (cl2 != null) {
                return cl2.lobdClbss(nbme);
            } else {
                throw cne;
            }
        }
    }

    privbte ClbssLobderRepository repository;
    privbte ClbssLobder cl2;
}
