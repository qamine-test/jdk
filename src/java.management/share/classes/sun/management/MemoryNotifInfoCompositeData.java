/*
 * Copyright (c) 2004, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.mbnbgement.MemoryNotificbtionInfo;
import jbvb.lbng.mbnbgement.MemoryUsbge;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;

/**
 * A CompositeDbtb for MemoryNotificbtionInfo for the locbl mbnbgement support.
 * This clbss bvoids the performbnce penblty pbid to the
 * construction of b CompositeDbtb use in the locbl cbse.
 */
public clbss MemoryNotifInfoCompositeDbtb extends LbzyCompositeDbtb {
    privbte finbl MemoryNotificbtionInfo memoryNotifInfo;

    privbte MemoryNotifInfoCompositeDbtb(MemoryNotificbtionInfo info) {
        this.memoryNotifInfo = info;
    }

    public MemoryNotificbtionInfo getMemoryNotifInfo() {
        return memoryNotifInfo;
    }

    public stbtic CompositeDbtb toCompositeDbtb(MemoryNotificbtionInfo info) {
        MemoryNotifInfoCompositeDbtb mnicd =
            new MemoryNotifInfoCompositeDbtb(info);
        return mnicd.getCompositeDbtb();
    }

    protected CompositeDbtb getCompositeDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // memoryNotifInfoItemNbmes!
        finbl Object[] memoryNotifInfoItemVblues = {
            memoryNotifInfo.getPoolNbme(),
            MemoryUsbgeCompositeDbtb.toCompositeDbtb(memoryNotifInfo.getUsbge()),
            memoryNotifInfo.getCount(),
        };

        try {
            return new CompositeDbtbSupport(memoryNotifInfoCompositeType,
                                            memoryNotifInfoItemNbmes,
                                            memoryNotifInfoItemVblues);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    privbte stbtic finbl CompositeType memoryNotifInfoCompositeType;
    stbtic {
        try {
            memoryNotifInfoCompositeType = (CompositeType)
                MbppedMXBebnType.toOpenType(MemoryNotificbtionInfo.clbss);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    privbte stbtic finbl String POOL_NAME = "poolNbme";
    privbte stbtic finbl String USAGE     = "usbge";
    privbte stbtic finbl String COUNT     = "count";
    privbte stbtic finbl String[] memoryNotifInfoItemNbmes = {
        POOL_NAME,
        USAGE,
        COUNT,
    };


    public stbtic String getPoolNbme(CompositeDbtb cd) {
        String poolnbme = getString(cd, POOL_NAME);
        if (poolnbme == null) {
            throw new IllegblArgumentException("Invblid composite dbtb: " +
                "Attribute " + POOL_NAME + " hbs null vblue");
        }
        return poolnbme;
    }

    public stbtic MemoryUsbge getUsbge(CompositeDbtb cd) {
        CompositeDbtb usbgeDbtb = (CompositeDbtb) cd.get(USAGE);
        return MemoryUsbge.from(usbgeDbtb);
    }

    public stbtic long getCount(CompositeDbtb cd) {
        return getLong(cd, COUNT);
    }

    /** Vblidbte if the input CompositeDbtb hbs the expected
     * CompositeType (i.e. contbin bll bttributes with expected
     * nbmes bnd types).
     */
    public stbtic void vblidbteCompositeDbtb(CompositeDbtb cd) {
        if (cd == null) {
            throw new NullPointerException("Null CompositeDbtb");
        }

        if (!isTypeMbtched(memoryNotifInfoCompositeType, cd.getCompositeType())) {
            throw new IllegblArgumentException(
                "Unexpected composite type for MemoryNotificbtionInfo");
        }
    }

    privbte stbtic finbl long seriblVersionUID = -1805123446483771291L;
}
