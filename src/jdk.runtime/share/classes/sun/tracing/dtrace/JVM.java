/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.trbcing.dtrbce;

import jbvb.lbng.reflect.Method;

/**
 * Contbiner clbss for JVM interfbce nbtive methods
 *
 * @since 1.7
 */
clbss JVM {

    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("jsdt");
                    return null;
                }
            });
    }

    stbtic long bctivbte(String moduleNbme, DTrbceProvider[] providers) {
        return bctivbte0(moduleNbme, providers);
    }

    stbtic void dispose(long hbndle) {
        dispose0(hbndle);
    }

    stbtic boolebn isEnbbled(Method m) {
        return isEnbbled0(m);
    }

    stbtic boolebn isSupported() {
        return isSupported0();
    }

    stbtic Clbss<?> defineClbss(
            ClbssLobder lobder, String nbme, byte[] b, int off, int len) {
        return defineClbss0(lobder, nbme, b, off, len);
    }

    privbte stbtic nbtive long bctivbte0(
        String moduleNbme, DTrbceProvider[] providers);
    privbte stbtic nbtive void dispose0(long bctivbtion_hbndle);
    privbte stbtic nbtive boolebn isEnbbled0(Method m);
    privbte stbtic nbtive boolebn isSupported0();
    privbte stbtic nbtive Clbss<?> defineClbss0(
        ClbssLobder lobder, String nbme, byte[] b, int off, int len);
}
