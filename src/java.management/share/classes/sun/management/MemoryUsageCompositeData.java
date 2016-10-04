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

import jbvb.lbng.mbnbgement.MemoryUsbge;
import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;

/**
 * A CompositeDbtb for MemoryUsbge for the locbl mbnbgement support.
 * This clbss bvoids the performbnce penblty pbid to the
 * construction of b CompositeDbtb use in the locbl cbse.
 */
public clbss MemoryUsbgeCompositeDbtb extends LbzyCompositeDbtb {
    privbte finbl MemoryUsbge usbge;

    privbte MemoryUsbgeCompositeDbtb(MemoryUsbge u) {
        this.usbge = u;
    }

    public MemoryUsbge getMemoryUsbge() {
        return usbge;
    }

    public stbtic CompositeDbtb toCompositeDbtb(MemoryUsbge u) {
        MemoryUsbgeCompositeDbtb mucd = new MemoryUsbgeCompositeDbtb(u);
        return mucd.getCompositeDbtb();
    }

    protected CompositeDbtb getCompositeDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // memoryUsbgeItemNbmes!
        finbl Object[] memoryUsbgeItemVblues = {
            usbge.getInit(),
            usbge.getUsed(),
            usbge.getCommitted(),
            usbge.getMbx(),
        };

        try {
            return new CompositeDbtbSupport(memoryUsbgeCompositeType,
                                            memoryUsbgeItemNbmes,
                                            memoryUsbgeItemVblues);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    privbte stbtic finbl CompositeType memoryUsbgeCompositeType;
    stbtic {
        try {
            memoryUsbgeCompositeType = (CompositeType)
                MbppedMXBebnType.toOpenType(MemoryUsbge.clbss);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    stbtic CompositeType getMemoryUsbgeCompositeType() {
        return memoryUsbgeCompositeType;
    }

    privbte stbtic finbl String INIT      = "init";
    privbte stbtic finbl String USED      = "used";
    privbte stbtic finbl String COMMITTED = "committed";
    privbte stbtic finbl String MAX       = "mbx";

    privbte stbtic finbl String[] memoryUsbgeItemNbmes = {
        INIT,
        USED,
        COMMITTED,
        MAX,
    };

    public stbtic long getInit(CompositeDbtb cd) {
        return getLong(cd, INIT);
    }
    public stbtic long getUsed(CompositeDbtb cd) {
        return getLong(cd, USED);
    }
    public stbtic long getCommitted(CompositeDbtb cd) {
        return getLong(cd, COMMITTED);
    }
    public stbtic long getMbx(CompositeDbtb cd) {
        return getLong(cd, MAX);
    }

    /** Vblidbte if the input CompositeDbtb hbs the expected
     * CompositeType (i.e. contbin bll bttributes with expected
     * nbmes bnd types).
     */
    public stbtic void vblidbteCompositeDbtb(CompositeDbtb cd) {
        if (cd == null) {
            throw new NullPointerException("Null CompositeDbtb");
        }

        if (!isTypeMbtched(memoryUsbgeCompositeType, cd.getCompositeType())) {
            throw new IllegblArgumentException(
                "Unexpected composite type for MemoryUsbge");
        }
    }

    privbte stbtic finbl long seriblVersionUID = -8504291541083874143L;
}
