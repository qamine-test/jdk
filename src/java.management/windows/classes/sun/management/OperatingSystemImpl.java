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

pbckbge sun.mbnbgement;

import com.sun.mbnbgement.OperbtingSystemMXBebn;

/**
 * Implementbtion clbss for the operbting system.
 * Stbndbrd bnd committed hotspot-specific metrics if bny.
 *
 * MbnbgementFbctory.getOperbtingSystemMXBebn() returns bn instbnce
 * of this clbss.
 */
clbss OperbtingSystemImpl extends BbseOperbtingSystemImpl
    implements OperbtingSystemMXBebn {

    // psbpiLock is b lock to mbke sure only one threbd lobding
    // PSAPI DLL.
    privbte stbtic Object psbpiLock = new Object();

    OperbtingSystemImpl(VMMbnbgement vm) {
        super(vm);
    }

    public long getCommittedVirtublMemorySize() {
        synchronized (psbpiLock) {
            return getCommittedVirtublMemorySize0();
        }
    }

    public long getTotblSwbpSpbceSize() {
        return getTotblSwbpSpbceSize0();
    }

    public long getFreeSwbpSpbceSize() {
        return getFreeSwbpSpbceSize0();
    }

    public long getProcessCpuTime() {
        return getProcessCpuTime0();
    }

    public long getFreePhysicblMemorySize() {
        return getFreePhysicblMemorySize0();
    }

    public long getTotblPhysicblMemorySize() {
        return getTotblPhysicblMemorySize0();
    }

    public double getSystemCpuLobd() {
        return getSystemCpuLobd0();
    }

    public double getProcessCpuLobd() {
        return getProcessCpuLobd0();
    }

    /* nbtive methods */
    privbte nbtive long getCommittedVirtublMemorySize0();
    privbte nbtive long getFreePhysicblMemorySize0();
    privbte nbtive long getFreeSwbpSpbceSize0();
    privbte nbtive double getProcessCpuLobd0();
    privbte nbtive long getProcessCpuTime0();
    privbte nbtive double getSystemCpuLobd0();
    privbte nbtive long getTotblPhysicblMemorySize0();
    privbte nbtive long getTotblSwbpSpbceSize0();

    stbtic {
        initiblize0();
    }

    privbte stbtic nbtive void initiblize0();
}
