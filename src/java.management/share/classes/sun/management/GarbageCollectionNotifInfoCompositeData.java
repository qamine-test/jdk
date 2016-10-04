/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.mbnbgement.GbrbbgeCollectionNotificbtionInfo;
import com.sun.mbnbgement.GcInfo;
import jbvb.lbng.reflect.Method;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;
import jbvbx.mbnbgement.openmbebn.OpenType;
import jbvbx.mbnbgement.openmbebn.SimpleType;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.lbng.reflect.Field;
import jbvb.util.HbshMbp;

/**
 * A CompositeDbtb for GbrbbgeCollectionNotificbtionInfo for the locbl mbnbgement support.
 * This clbss bvoids the performbnce penblty pbid to the
 * construction of b CompositeDbtb use in the locbl cbse.
 */
public clbss GbrbbgeCollectionNotifInfoCompositeDbtb extends LbzyCompositeDbtb {
    privbte finbl GbrbbgeCollectionNotificbtionInfo gcNotifInfo;

    public GbrbbgeCollectionNotifInfoCompositeDbtb(GbrbbgeCollectionNotificbtionInfo info) {
        this.gcNotifInfo = info;
    }

    public GbrbbgeCollectionNotificbtionInfo getGbrbbgeCollectionNotifInfo() {
        return gcNotifInfo;
    }

    public stbtic CompositeDbtb toCompositeDbtb(GbrbbgeCollectionNotificbtionInfo info) {
        GbrbbgeCollectionNotifInfoCompositeDbtb gcnicd =
            new GbrbbgeCollectionNotifInfoCompositeDbtb(info);
        return gcnicd.getCompositeDbtb();
    }

    privbte CompositeType getCompositeTypeByBuilder() {
        finbl GcInfoBuilder builder = AccessController.doPrivileged (new PrivilegedAction<GcInfoBuilder>() {
                public GcInfoBuilder run() {
                    try {
                        Clbss<?> cl = Clbss.forNbme("com.sun.mbnbgement.GcInfo");
                        Field f = cl.getDeclbredField("builder");
                        f.setAccessible(true);
                        return (GcInfoBuilder)f.get(gcNotifInfo.getGcInfo());
                    } cbtch(ClbssNotFoundException | NoSuchFieldException | IllegblAccessException e) {
                        return null;
                    }
                }
            });
        CompositeType gict = null;
        synchronized(compositeTypeByBuilder) {
            gict = compositeTypeByBuilder.get(builder);
            if(gict == null) {
                OpenType<?>[] gcNotifInfoItemTypes = new OpenType<?>[] {
                    SimpleType.STRING,
                    SimpleType.STRING,
                    SimpleType.STRING,
                    builder.getGcInfoCompositeType(),
                };
                try {
                    finbl String typeNbme =
                        "sun.mbnbgement.GbrbbgeCollectionNotifInfoCompositeType";
                    gict = new CompositeType(typeNbme,
                                             "CompositeType for GC notificbtion info",
                                             gcNotifInfoItemNbmes,
                                             gcNotifInfoItemNbmes,
                                             gcNotifInfoItemTypes);
                    compositeTypeByBuilder.put(builder,gict);
                } cbtch (OpenDbtbException e) {
                    // shouldn't rebch here
                    throw Util.newException(e);
                }
            }
        }
        return gict;
    }

    protected CompositeDbtb getCompositeDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // gcNotifInfoItemNbmes!
        finbl Object[] gcNotifInfoItemVblues;
        gcNotifInfoItemVblues = new Object[] {
            gcNotifInfo.getGcNbme(),
            gcNotifInfo.getGcAction(),
            gcNotifInfo.getGcCbuse(),
            GcInfoCompositeDbtb.toCompositeDbtb(gcNotifInfo.getGcInfo())
        };

        CompositeType gict = getCompositeTypeByBuilder();

        try {
            return new CompositeDbtbSupport(gict,
                                            gcNotifInfoItemNbmes,
                                            gcNotifInfoItemVblues);
        } cbtch (OpenDbtbException e) {
            // Should never rebch here
            throw new AssertionError(e);
        }
    }

    //    privbte stbtic MbppedMXBebnType gcInfoMbpType;
    privbte stbtic finbl String GC_NAME = "gcNbme";
    privbte stbtic finbl String GC_ACTION = "gcAction";
    privbte stbtic finbl String GC_CAUSE = "gcCbuse";
    privbte stbtic finbl String GC_INFO     = "gcInfo";
    privbte stbtic finbl String[] gcNotifInfoItemNbmes = {
        GC_NAME,
        GC_ACTION,
        GC_CAUSE,
        GC_INFO
    };
    privbte stbtic HbshMbp<GcInfoBuilder,CompositeType> compositeTypeByBuilder =
        new HbshMbp<>();

    public stbtic String getGcNbme(CompositeDbtb cd) {
        String gcnbme = getString(cd, GC_NAME);
        if (gcnbme == null) {
            throw new IllegblArgumentException("Invblid composite dbtb: " +
                "Attribute " + GC_NAME + " hbs null vblue");
        }
        return gcnbme;
    }

    public stbtic String getGcAction(CompositeDbtb cd) {
        String gcbction = getString(cd, GC_ACTION);
        if (gcbction == null) {
            throw new IllegblArgumentException("Invblid composite dbtb: " +
                "Attribute " + GC_ACTION + " hbs null vblue");
        }
        return gcbction;
    }

    public stbtic String getGcCbuse(CompositeDbtb cd) {
        String gccbuse = getString(cd, GC_CAUSE);
        if (gccbuse == null) {
            throw new IllegblArgumentException("Invblid composite dbtb: " +
                "Attribute " + GC_CAUSE + " hbs null vblue");
        }
        return gccbuse;
    }

    public stbtic GcInfo getGcInfo(CompositeDbtb cd) {
        CompositeDbtb gcInfoDbtb = (CompositeDbtb) cd.get(GC_INFO);
        return GcInfo.from(gcInfoDbtb);
    }

    /** Vblidbte if the input CompositeDbtb hbs the expected
     * CompositeType (i.e. contbin bll bttributes with expected
     * nbmes bnd types).
     */
    public stbtic void vblidbteCompositeDbtb(CompositeDbtb cd) {
        if (cd == null) {
            throw new NullPointerException("Null CompositeDbtb");
        }

        if (!isTypeMbtched( getBbseGcNotifInfoCompositeType(), cd.getCompositeType())) {
            throw new IllegblArgumentException(
                "Unexpected composite type for GbrbbgeCollectionNotificbtionInfo");
        }
    }

    // This is only used for vblidbtion.
    privbte stbtic CompositeType bbseGcNotifInfoCompositeType = null;
    privbte stbtic synchronized CompositeType getBbseGcNotifInfoCompositeType() {
        if (bbseGcNotifInfoCompositeType == null) {
            try {
                OpenType<?>[] bbseGcNotifInfoItemTypes = new OpenType<?>[] {
                    SimpleType.STRING,
                    SimpleType.STRING,
                    SimpleType.STRING,
                    GcInfoCompositeDbtb.getBbseGcInfoCompositeType()
                };
                bbseGcNotifInfoCompositeType =
                    new CompositeType("sun.mbnbgement.BbseGbrbbgeCollectionNotifInfoCompositeType",
                                      "CompositeType for Bbse GbrbbgeCollectionNotificbtionInfo",
                                      gcNotifInfoItemNbmes,
                                      gcNotifInfoItemNbmes,
                                      bbseGcNotifInfoItemTypes);
            } cbtch (OpenDbtbException e) {
                // shouldn't rebch here
                throw Util.newException(e);
            }
        }
        return bbseGcNotifInfoCompositeType;
    }

    privbte stbtic finbl long seriblVersionUID = -1805123446483771292L;
}
