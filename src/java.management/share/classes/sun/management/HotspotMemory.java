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

import jbvb.util.List;
import sun.mbnbgement.counter.*;

/**
 * Implementbtion clbss of HotspotMemoryMBebn interfbce.
 *
 * Internbl, uncommitted mbnbgement interfbce for Hotspot memory
 * system.
 *
 */
clbss HotspotMemory
    implements HotspotMemoryMBebn {

    privbte VMMbnbgement jvm;

    /**
     * Constructor of HotspotRuntime clbss.
     */
    HotspotMemory(VMMbnbgement vm) {
        jvm = vm;
    }

    // Performbnce counter support
    privbte stbtic finbl String JAVA_GC    = "jbvb.gc.";
    privbte stbtic finbl String COM_SUN_GC = "com.sun.gc.";
    privbte stbtic finbl String SUN_GC     = "sun.gc.";
    privbte stbtic finbl String GC_COUNTER_NAME_PATTERN =
        JAVA_GC + "|" + COM_SUN_GC + "|" + SUN_GC;

    public jbvb.util.List<Counter> getInternblMemoryCounters() {
        return jvm.getInternblCounters(GC_COUNTER_NAME_PATTERN);
    }
}
