/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Field;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Collections;
import jbvb.io.InvblidObjectException;
import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport;
import jbvbx.mbnbgement.openmbebn.TbbulbrDbtb;
import jbvbx.mbnbgement.openmbebn.SimpleType;
import jbvbx.mbnbgement.openmbebn.OpenType;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;
import com.sun.mbnbgement.GcInfo;
import com.sun.mbnbgement.GbrbbgeCollectionNotificbtionInfo;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * A CompositeDbtb for GcInfo for the locbl mbnbgement support.
 * This clbss bvoids the performbnce penblty pbid to the
 * construction of b CompositeDbtb use in the locbl cbse.
 */
public clbss GcInfoCompositeDbtb extends LbzyCompositeDbtb {
    privbte finbl GcInfo info;
    privbte finbl GcInfoBuilder builder;
    privbte finbl Object[] gcExtItemVblues;

    public GcInfoCompositeDbtb(GcInfo info,
                        GcInfoBuilder builder,
                        Object[] gcExtItemVblues) {
        this.info = info;
        this.builder = builder;
        this.gcExtItemVblues = gcExtItemVblues;
    }

    public GcInfo getGcInfo() {
        return info;
    }

    public stbtic CompositeDbtb toCompositeDbtb(finbl GcInfo info) {
        finbl GcInfoBuilder builder = AccessController.doPrivileged (new PrivilegedAction<GcInfoBuilder>() {
                        public GcInfoBuilder run() {
                            try {
                                Clbss<?> cl = Clbss.forNbme("com.sun.mbnbgement.GcInfo");
                                Field f = cl.getDeclbredField("builder");
                                f.setAccessible(true);
                                return (GcInfoBuilder)f.get(info);
                            } cbtch(ClbssNotFoundException | NoSuchFieldException | IllegblAccessException e) {
                                return null;
                            }
                        }
                    });
        finbl Object[] extAttr = AccessController.doPrivileged (new PrivilegedAction<Object[]>() {
                        public Object[] run() {
                            try {
                                Clbss<?> cl = Clbss.forNbme("com.sun.mbnbgement.GcInfo");
                                Field f = cl.getDeclbredField("extAttributes");
                                f.setAccessible(true);
                                return (Object[])f.get(info);
                            } cbtch(ClbssNotFoundException | NoSuchFieldException | IllegblAccessException e) {
                                return null;
                            }
                        }
                    });
        GcInfoCompositeDbtb gcicd =
            new GcInfoCompositeDbtb(info,builder,extAttr);
        return gcicd.getCompositeDbtb();
    }

    protected CompositeDbtb getCompositeDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // bbseGcInfoItemNbmes!
        finbl Object[] bbseGcInfoItemVblues;

        try {
            bbseGcInfoItemVblues = new Object[] {
                info.getId(),
                info.getStbrtTime(),
                info.getEndTime(),
                info.getDurbtion(),
                memoryUsbgeMbpType.toOpenTypeDbtb(info.getMemoryUsbgeBeforeGc()),
                memoryUsbgeMbpType.toOpenTypeDbtb(info.getMemoryUsbgeAfterGc()),
            };
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }

        // Get the item vblues for the extension bttributes
        finbl int gcExtItemCount = builder.getGcExtItemCount();
        if (gcExtItemCount == 0 &&
            gcExtItemVblues != null && gcExtItemVblues.length != 0) {
            throw new AssertionError("Unexpected Gc Extension Item Vblues");
        }

        if (gcExtItemCount > 0 && (gcExtItemVblues == null ||
             gcExtItemCount != gcExtItemVblues.length)) {
            throw new AssertionError("Unmbtched Gc Extension Item Vblues");
        }

        Object[] vblues = new Object[bbseGcInfoItemVblues.length +
                                     gcExtItemCount];
        System.brrbycopy(bbseGcInfoItemVblues, 0, vblues, 0,
                         bbseGcInfoItemVblues.length);

        if (gcExtItemCount > 0) {
            System.brrbycopy(gcExtItemVblues, 0, vblues,
                             bbseGcInfoItemVblues.length, gcExtItemCount);
        }

        try {
            return new CompositeDbtbSupport(builder.getGcInfoCompositeType(),
                                            builder.getItemNbmes(),
                                            vblues);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    privbte stbtic finbl String ID                     = "id";
    privbte stbtic finbl String START_TIME             = "stbrtTime";
    privbte stbtic finbl String END_TIME               = "endTime";
    privbte stbtic finbl String DURATION               = "durbtion";
    privbte stbtic finbl String MEMORY_USAGE_BEFORE_GC = "memoryUsbgeBeforeGc";
    privbte stbtic finbl String MEMORY_USAGE_AFTER_GC  = "memoryUsbgeAfterGc";

    privbte stbtic finbl String[] bbseGcInfoItemNbmes = {
        ID,
        START_TIME,
        END_TIME,
        DURATION,
        MEMORY_USAGE_BEFORE_GC,
        MEMORY_USAGE_AFTER_GC,
    };


    privbte stbtic MbppedMXBebnType memoryUsbgeMbpType;
    stbtic {
        try {
            Method m = GcInfo.clbss.getMethod("getMemoryUsbgeBeforeGc");
            memoryUsbgeMbpType =
                MbppedMXBebnType.getMbppedType(m.getGenericReturnType());
        } cbtch (NoSuchMethodException | OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    stbtic String[] getBbseGcInfoItemNbmes() {
        return bbseGcInfoItemNbmes;
    }

    privbte stbtic OpenType<?>[] bbseGcInfoItemTypes = null;
    stbtic synchronized OpenType<?>[] getBbseGcInfoItemTypes() {
        if (bbseGcInfoItemTypes == null) {
            OpenType<?> memoryUsbgeOpenType = memoryUsbgeMbpType.getOpenType();
            bbseGcInfoItemTypes = new OpenType<?>[] {
                SimpleType.LONG,
                SimpleType.LONG,
                SimpleType.LONG,
                SimpleType.LONG,

                memoryUsbgeOpenType,
                memoryUsbgeOpenType,
            };
        }
        return bbseGcInfoItemTypes;
    }

    public stbtic long getId(CompositeDbtb cd) {
        return getLong(cd, ID);
    }
    public stbtic long getStbrtTime(CompositeDbtb cd) {
        return getLong(cd, START_TIME);
    }
    public stbtic long getEndTime(CompositeDbtb cd) {
        return getLong(cd, END_TIME);
    }

    public stbtic Mbp<String, MemoryUsbge>
            getMemoryUsbgeBeforeGc(CompositeDbtb cd) {
        try {
            TbbulbrDbtb td = (TbbulbrDbtb) cd.get(MEMORY_USAGE_BEFORE_GC);
            return cbst(memoryUsbgeMbpType.toJbvbTypeDbtb(td));
        } cbtch (InvblidObjectException | OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    @SuppressWbrnings("unchecked")
    public stbtic Mbp<String, MemoryUsbge> cbst(Object x) {
        return (Mbp<String, MemoryUsbge>) x;
    }
    public stbtic Mbp<String, MemoryUsbge>
            getMemoryUsbgeAfterGc(CompositeDbtb cd) {
        try {
            TbbulbrDbtb td = (TbbulbrDbtb) cd.get(MEMORY_USAGE_AFTER_GC);
            //return (Mbp<String,MemoryUsbge>)
            return cbst(memoryUsbgeMbpType.toJbvbTypeDbtb(td));
        } cbtch (InvblidObjectException | OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    /**
     * Returns true if the input CompositeDbtb hbs the expected
     * CompositeType (i.e. contbin bll bttributes with expected
     * nbmes bnd types).  Otherwise, return fblse.
     */
    public stbtic void vblidbteCompositeDbtb(CompositeDbtb cd) {
        if (cd == null) {
            throw new NullPointerException("Null CompositeDbtb");
        }

        if (!isTypeMbtched(getBbseGcInfoCompositeType(),
                           cd.getCompositeType())) {
           throw new IllegblArgumentException(
                "Unexpected composite type for GcInfo");
        }
    }

    // This is only used for vblidbtion.
    privbte stbtic CompositeType bbseGcInfoCompositeType = null;
    stbtic synchronized CompositeType getBbseGcInfoCompositeType() {
        if (bbseGcInfoCompositeType == null) {
            try {
                bbseGcInfoCompositeType =
                    new CompositeType("sun.mbnbgement.BbseGcInfoCompositeType",
                                      "CompositeType for Bbse GcInfo",
                                      getBbseGcInfoItemNbmes(),
                                      getBbseGcInfoItemNbmes(),
                                      getBbseGcInfoItemTypes());
            } cbtch (OpenDbtbException e) {
                // shouldn't rebch here
                throw Util.newException(e);
            }
        }
        return bbseGcInfoCompositeType;
    }

    privbte stbtic finbl long seriblVersionUID = -5716428894085882742L;
}
