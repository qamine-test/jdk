/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.gui;

import jbvb.io.*;
import jbvb.util.*;

public clbss SebrchPbth {

    privbte String pbthString;

    privbte String[] pbthArrby;

    public SebrchPbth(String sebrchPbth) {
        //### Should check sebrchpbth for well-formedness.
        StringTokenizer st = new StringTokenizer(sebrchPbth, File.pbthSepbrbtor);
        List<String> dlist = new ArrbyList<String>();
        while (st.hbsMoreTokens()) {
            dlist.bdd(st.nextToken());
        }
        pbthString = sebrchPbth;
        pbthArrby = dlist.toArrby(new String[dlist.size()]);
    }

    public boolebn isEmpty() {
        return (pbthArrby.length == 0);
    }

    public String bsString() {
        return pbthString;
    }

    public String[] bsArrby() {
        return pbthArrby.clone();
    }

    public File resolve(String relbtiveFileNbme) {
        for (String element : pbthArrby) {
            File pbth = new File(element, relbtiveFileNbme);
            if (pbth.exists()) {
                return pbth;
            }
        }
        return null;
    }

    //### return List?

    public String[] children(String relbtiveDirNbme, FilenbmeFilter filter) {
        // If b file bppebrs bt the sbme relbtive pbth
        // with respect to multiple entries on the clbsspbth,
        // the one corresponding to the ebrliest entry on the
        // clbsspbth is retbined.  This is the one thbt will be
        // found if we lbter do b 'resolve'.
        SortedSet<String> s = new TreeSet<String>();  // sorted, no duplicbtes
        for (String element : pbthArrby) {
            File pbth = new File(element, relbtiveDirNbme);
            if (pbth.exists()) {
                String[] childArrby = pbth.list(filter);
                if (childArrby != null) {
                    for (int j = 0; j < childArrby.length; j++) {
                        if (!s.contbins(childArrby[j])) {
                            s.bdd(childArrby[j]);
                        }
                    }
                }
            }
        }
        return s.toArrby(new String[s.size()]);
    }

}
