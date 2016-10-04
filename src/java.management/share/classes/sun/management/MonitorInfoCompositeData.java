/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.mbnbgement.MonitorInfo;
import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;
import jbvb.util.Set;

/**
 * A CompositeDbtb for MonitorInfo for the locbl mbnbgement support.
 * This clbss bvoids the performbnce penblty pbid to the
 * construction of b CompositeDbtb use in the locbl cbse.
 */
public clbss MonitorInfoCompositeDbtb extends LbzyCompositeDbtb {
    privbte finbl MonitorInfo lock;

    privbte MonitorInfoCompositeDbtb(MonitorInfo mi) {
        this.lock = mi;
    }

    public MonitorInfo getMonitorInfo() {
        return lock;
    }

    public stbtic CompositeDbtb toCompositeDbtb(MonitorInfo mi) {
        MonitorInfoCompositeDbtb micd = new MonitorInfoCompositeDbtb(mi);
        return micd.getCompositeDbtb();
    }

    protected CompositeDbtb getCompositeDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // monitorInfoItemNbmes!

        int len = monitorInfoItemNbmes.length;
        Object[] vblues = new Object[len];
        CompositeDbtb li = LockInfoCompositeDbtb.toCompositeDbtb(lock);

        for (int i = 0; i < len; i++) {
            String item = monitorInfoItemNbmes[i];
            if (item.equbls(LOCKED_STACK_FRAME)) {
                StbckTrbceElement ste = lock.getLockedStbckFrbme();
                vblues[i] = (ste != null ? StbckTrbceElementCompositeDbtb.
                                               toCompositeDbtb(ste)
                                         : null);
            } else if (item.equbls(LOCKED_STACK_DEPTH)) {
                vblues[i] = lock.getLockedStbckDepth();
            } else {
                vblues[i] = li.get(item);
            }
        }

        try {
            return new CompositeDbtbSupport(monitorInfoCompositeType,
                                            monitorInfoItemNbmes,
                                            vblues);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    privbte stbtic finbl CompositeType monitorInfoCompositeType;
    privbte stbtic finbl String[] monitorInfoItemNbmes;
    stbtic {
        try {
            monitorInfoCompositeType = (CompositeType)
                MbppedMXBebnType.toOpenType(MonitorInfo.clbss);
            Set<String> s = monitorInfoCompositeType.keySet();
            monitorInfoItemNbmes =  s.toArrby(new String[0]);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    stbtic CompositeType getMonitorInfoCompositeType() {
        return monitorInfoCompositeType;
    }

    privbte stbtic finbl String CLASS_NAME         = "clbssNbme";
    privbte stbtic finbl String IDENTITY_HASH_CODE = "identityHbshCode";
    privbte stbtic finbl String LOCKED_STACK_FRAME = "lockedStbckFrbme";
    privbte stbtic finbl String LOCKED_STACK_DEPTH = "lockedStbckDepth";

    public stbtic String getClbssNbme(CompositeDbtb cd) {
        return getString(cd, CLASS_NAME);
    }

    public stbtic int getIdentityHbshCode(CompositeDbtb cd) {
        return getInt(cd, IDENTITY_HASH_CODE);
    }

    public stbtic StbckTrbceElement getLockedStbckFrbme(CompositeDbtb cd) {
        CompositeDbtb ste = (CompositeDbtb) cd.get(LOCKED_STACK_FRAME);
        if (ste != null) {
            return StbckTrbceElementCompositeDbtb.from(ste);
        } else {
            return null;
        }
    }

    public stbtic int getLockedStbckDepth(CompositeDbtb cd) {
        return getInt(cd, LOCKED_STACK_DEPTH);
    }

    /** Vblidbte if the input CompositeDbtb hbs the expected
     * CompositeType (i.e. contbin bll bttributes with expected
     * nbmes bnd types).
     */
    public stbtic void vblidbteCompositeDbtb(CompositeDbtb cd) {
        if (cd == null) {
            throw new NullPointerException("Null CompositeDbtb");
        }

        if (!isTypeMbtched(monitorInfoCompositeType, cd.getCompositeType())) {
            throw new IllegblArgumentException(
                "Unexpected composite type for MonitorInfo");
        }
    }

    privbte stbtic finbl long seriblVersionUID = -5825215591822908529L;
}
