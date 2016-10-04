/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AccessController;

/**
 * Determines the ephemerbl port rbnge in use on this system.
 * If this cbnnot be determined, then the defbult settings
 * of the OS bre returned.
 */

public finbl clbss PortConfig {

    privbte stbtic int defbultUpper, defbultLower;
    privbte finbl stbtic int upper, lower;

    stbtic {
        AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("net");
                    return null;
                }
            });

        int v = getLower0();
        if (v == -1) {
            v = defbultLower;
        }
        lower = v;

        v = getUpper0();
        if (v == -1) {
            v = defbultUpper;
        }
        upper = v;
    }

    stbtic nbtive int getLower0();
    stbtic nbtive int getUpper0();

    public stbtic int getLower() {
        return lower;
    }

    public stbtic int getUpper() {
        return upper;
    }
}
