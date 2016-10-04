/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.reflect.misc.ReflectUtil;

public clbss OrderClbssLobders extends ClbssLobder {
    public OrderClbssLobders(ClbssLobder cl1, ClbssLobder cl2) {
        super(cl1);

        this.cl2 = cl2;
    }

    protected Clbss<?> lobdClbss(String nbme, boolebn resolve) throws ClbssNotFoundException {
        ReflectUtil.checkPbckbgeAccess(nbme);
        try {
            return super.lobdClbss(nbme, resolve);
        } cbtch (ClbssNotFoundException cne) {
            if (cl2 != null) {
                return cl2.lobdClbss(nbme);
            } else {
                throw cne;
            }
        }
    }

    privbte ClbssLobder cl2;
}
