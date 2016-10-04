/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.mbnbgement.LockInfo;
import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;

/**
 * A CompositeDbtb for LockInfo for the locbl mbnbgement support.
 * This clbss bvoids the performbnce penblty pbid to the
 * construction of b CompositeDbtb use in the locbl cbse.
 */
public clbss LockInfoCompositeDbtb extends LbzyCompositeDbtb {
    privbte finbl LockInfo lock;

    privbte LockInfoCompositeDbtb(LockInfo li) {
        this.lock = li;
    }

    public LockInfo getLockInfo() {
        return lock;
    }

    public stbtic CompositeDbtb toCompositeDbtb(LockInfo li) {
        if (li == null) {
            return null;
        }

        LockInfoCompositeDbtb licd = new LockInfoCompositeDbtb(li);
        return licd.getCompositeDbtb();
    }

    protected CompositeDbtb getCompositeDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // lockInfoItemNbmes!
        finbl Object[] lockInfoItemVblues = {
            new String(lock.getClbssNbme()),
            lock.getIdentityHbshCode(),
        };

        try {
            return new CompositeDbtbSupport(lockInfoCompositeType,
                                            lockInfoItemNbmes,
                                            lockInfoItemVblues);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw Util.newException(e);
        }
    }

    privbte stbtic finbl CompositeType lockInfoCompositeType;
    stbtic {
        try {
            lockInfoCompositeType = (CompositeType)
                MbppedMXBebnType.toOpenType(LockInfo.clbss);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw Util.newException(e);
        }
    }

    stbtic CompositeType getLockInfoCompositeType() {
        return lockInfoCompositeType;
    }

    privbte stbtic finbl String CLASS_NAME         = "clbssNbme";
    privbte stbtic finbl String IDENTITY_HASH_CODE = "identityHbshCode";
    privbte stbtic finbl String[] lockInfoItemNbmes = {
        CLASS_NAME,
        IDENTITY_HASH_CODE,
    };

    /*
     * Returns b LockInfo object mbpped from the given CompositeDbtb.
     */
    public stbtic LockInfo toLockInfo(CompositeDbtb cd) {
        if (cd == null) {
            throw new NullPointerException("Null CompositeDbtb");
        }

        if (!isTypeMbtched(lockInfoCompositeType, cd.getCompositeType())) {
            throw new IllegblArgumentException(
                "Unexpected composite type for LockInfo");
        }

        String clbssNbme = getString(cd, CLASS_NAME);
        int identityHbshCode = getInt(cd, IDENTITY_HASH_CODE);
        return new LockInfo(clbssNbme, identityHbshCode);
    }

    privbte stbtic finbl long seriblVersionUID = -6374759159749014052L;
}
