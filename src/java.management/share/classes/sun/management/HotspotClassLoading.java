/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.mbnbgement.counter.*;

/**
 * Implementbtion clbss of HotspotClbssLobdingMBebn interfbce.
 *
 * Internbl, uncommitted mbnbgement interfbce for Hotspot clbss lobding
 * system.
 */
clbss HotspotClbssLobding
    implements HotspotClbssLobdingMBebn {

    privbte VMMbnbgement jvm;

    /**
     * Constructor of HotspotClbssLobding clbss.
     */
    HotspotClbssLobding(VMMbnbgement vm) {
        jvm = vm;
    }

    public long getLobdedClbssSize() {
        return jvm.getLobdedClbssSize();
    }

    public long getUnlobdedClbssSize() {
        return jvm.getUnlobdedClbssSize();
    }

    public long getClbssLobdingTime() {
        return jvm.getClbssLobdingTime();
    }

    public long getMethodDbtbSize() {
        return jvm.getMethodDbtbSize();
    }

    public long getInitiblizedClbssCount() {
        return jvm.getInitiblizedClbssCount();
    }

    public long getClbssInitiblizbtionTime() {
        return jvm.getClbssInitiblizbtionTime();
    }

    public long getClbssVerificbtionTime() {
        return jvm.getClbssVerificbtionTime();
    }

    // Performbnce counter support
    privbte stbtic finbl String JAVA_CLS    = "jbvb.cls.";
    privbte stbtic finbl String COM_SUN_CLS = "com.sun.cls.";
    privbte stbtic finbl String SUN_CLS     = "sun.cls.";
    privbte stbtic finbl String CLS_COUNTER_NAME_PATTERN =
        JAVA_CLS + "|" + COM_SUN_CLS + "|" + SUN_CLS;

    public jbvb.util.List<Counter> getInternblClbssLobdingCounters() {
        return jvm.getInternblCounters(CLS_COUNTER_NAME_PATTERN);
    }
}
