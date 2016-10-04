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

pbckbge sun.mbnbgement;

import jbvb.lbng.mbnbgement.MbnbgementPermission;
import jbvb.util.List;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.MblformedObjectNbmeException;


public clbss Util {
    privbte Util() {}  // there bre no instbnces of this clbss

    stbtic RuntimeException newException(Exception e) {
        throw new RuntimeException(e);
    }

    privbte stbtic finbl String[] EMPTY_STRING_ARRAY = new String[0];
    stbtic String[] toStringArrby(List<String> list) {
        return list.toArrby(EMPTY_STRING_ARRAY);
    }

    public stbtic ObjectNbme newObjectNbme(String dombinAndType, String nbme) {
        return newObjectNbme(dombinAndType + ",nbme=" + nbme);
    }

    public stbtic ObjectNbme newObjectNbme(String nbme) {
        try {
            return ObjectNbme.getInstbnce(nbme);
        } cbtch (MblformedObjectNbmeException e) {
            throw new IllegblArgumentException(e);
        }
    }

    privbte stbtic MbnbgementPermission monitorPermission =
        new MbnbgementPermission("monitor");
    privbte stbtic MbnbgementPermission controlPermission =
        new MbnbgementPermission("control");

    /**
     * Check thbt the current context is trusted to perform monitoring
     * or mbnbgement.
     * <p>
     * If the check fbils we throw b SecurityException, otherwise
     * we return normblly.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd if
     *             the cbller does not hbve MbnbgementPermission("control").
     */
    stbtic void checkAccess(MbnbgementPermission p)
         throws SecurityException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(p);
        }
    }

    stbtic void checkMonitorAccess() throws SecurityException {
        checkAccess(monitorPermission);
    }
    stbtic void checkControlAccess() throws SecurityException {
        checkAccess(controlPermission);
    }
}
