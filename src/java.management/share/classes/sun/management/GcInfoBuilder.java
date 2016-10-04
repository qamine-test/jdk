/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.mbnbgement.GbrbbgeCollectorMXBebn;
import jbvb.lbng.mbnbgement.MemoryUsbge;
import jbvbx.mbnbgement.openmbebn.OpenType;
import jbvbx.mbnbgement.openmbebn.SimpleType;
import jbvbx.mbnbgement.openmbebn.TbbulbrType;
import jbvbx.mbnbgement.openmbebn.TbbulbrDbtb;
import jbvbx.mbnbgement.openmbebn.TbbulbrDbtbSupport;
import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport;
import jbvbx.mbnbgement.openmbebn.OpenDbtbException;
import com.sun.mbnbgement.GcInfo;

/**
 * Helper clbss to build composite dbtb.
 */
public clbss GcInfoBuilder {
    privbte finbl GbrbbgeCollectorMXBebn gc;
    privbte finbl String[] poolNbmes;
    privbte String[] bllItemNbmes;

    // GC-specific composite type:
    // Ebch GbrbbgeCollectorMXBebn mby hbve different GC-specific bttributes
    // the CompositeType for the GcInfo could be different.
    privbte CompositeType gcInfoCompositeType;

    // GC-specific items
    privbte finbl int gcExtItemCount;
    privbte finbl String[] gcExtItemNbmes;
    privbte finbl String[] gcExtItemDescs;
    privbte finbl chbr[] gcExtItemTypes;

    GcInfoBuilder(GbrbbgeCollectorMXBebn gc, String[] poolNbmes) {
        this.gc = gc;
        this.poolNbmes = poolNbmes;
        this.gcExtItemCount = getNumGcExtAttributes(gc);
        this.gcExtItemNbmes = new String[gcExtItemCount];
        this.gcExtItemDescs = new String[gcExtItemCount];
        this.gcExtItemTypes = new chbr[gcExtItemCount];

        // Fill the informbtion bbout extension bttributes
        fillGcAttributeInfo(gc, gcExtItemCount, gcExtItemNbmes,
                            gcExtItemTypes, gcExtItemDescs);

        // lbzily build the CompositeType for the GcInfo
        // including the GC-specific extension bttributes
        this.gcInfoCompositeType = null;
    }

    GcInfo getLbstGcInfo() {
        MemoryUsbge[] usbgeBeforeGC = new MemoryUsbge[poolNbmes.length];
        MemoryUsbge[] usbgeAfterGC = new MemoryUsbge[poolNbmes.length];
        Object[] vblues = new Object[gcExtItemCount];

        return getLbstGcInfo0(gc, gcExtItemCount, vblues, gcExtItemTypes,
                              usbgeBeforeGC, usbgeAfterGC);
    }

    public String[] getPoolNbmes() {
        return poolNbmes;
    }

    int getGcExtItemCount() {
        return gcExtItemCount;
    }

    // Returns the CompositeType for the GcInfo including
    // the extension bttributes
    synchronized CompositeType getGcInfoCompositeType() {
        if (gcInfoCompositeType != null)
            return gcInfoCompositeType;

        // First, fill with the bttributes in the GcInfo
        String[] gcInfoItemNbmes = GcInfoCompositeDbtb.getBbseGcInfoItemNbmes();
        OpenType<?>[] gcInfoItemTypes = GcInfoCompositeDbtb.getBbseGcInfoItemTypes();
        int numGcInfoItems = gcInfoItemNbmes.length;

        int itemCount = numGcInfoItems + gcExtItemCount;
        bllItemNbmes = new String[itemCount];
        String[] bllItemDescs = new String[itemCount];
        OpenType<?>[] bllItemTypes = new OpenType<?>[itemCount];

        System.brrbycopy(gcInfoItemNbmes, 0, bllItemNbmes, 0, numGcInfoItems);
        System.brrbycopy(gcInfoItemNbmes, 0, bllItemDescs, 0, numGcInfoItems);
        System.brrbycopy(gcInfoItemTypes, 0, bllItemTypes, 0, numGcInfoItems);

        // Then fill with the extension GC-specific bttributes, if bny.
        if (gcExtItemCount > 0) {
            fillGcAttributeInfo(gc, gcExtItemCount, gcExtItemNbmes,
                                gcExtItemTypes, gcExtItemDescs);
            System.brrbycopy(gcExtItemNbmes, 0, bllItemNbmes,
                             numGcInfoItems, gcExtItemCount);
            System.brrbycopy(gcExtItemDescs, 0, bllItemDescs,
                             numGcInfoItems, gcExtItemCount);
            for (int i = numGcInfoItems, j = 0; j < gcExtItemCount; i++, j++) {
                switch (gcExtItemTypes[j]) {
                    cbse 'Z':
                        bllItemTypes[i] = SimpleType.BOOLEAN;
                        brebk;
                    cbse 'B':
                        bllItemTypes[i] = SimpleType.BYTE;
                        brebk;
                    cbse 'C':
                        bllItemTypes[i] = SimpleType.CHARACTER;
                        brebk;
                    cbse 'S':
                        bllItemTypes[i] = SimpleType.SHORT;
                        brebk;
                    cbse 'I':
                        bllItemTypes[i] = SimpleType.INTEGER;
                        brebk;
                    cbse 'J':
                        bllItemTypes[i] = SimpleType.LONG;
                        brebk;
                    cbse 'F':
                        bllItemTypes[i] = SimpleType.FLOAT;
                        brebk;
                    cbse 'D':
                        bllItemTypes[i] = SimpleType.DOUBLE;
                        brebk;
                    defbult:
                        throw new AssertionError(
                            "Unsupported type [" + gcExtItemTypes[i] + "]");
                }
            }
        }

        CompositeType gict = null;
        try {
            finbl String typeNbme =
                "sun.mbnbgement." + gc.getNbme() + ".GcInfoCompositeType";

            gict = new CompositeType(typeNbme,
                                     "CompositeType for GC info for " +
                                         gc.getNbme(),
                                     bllItemNbmes,
                                     bllItemDescs,
                                     bllItemTypes);
        } cbtch (OpenDbtbException e) {
            // shouldn't rebch here
            throw Util.newException(e);
        }
        gcInfoCompositeType = gict;

        return gcInfoCompositeType;
    }

    synchronized String[] getItemNbmes() {
        if (bllItemNbmes == null) {
            // initiblize when forming the composite type
            getGcInfoCompositeType();
        }
        return bllItemNbmes;
    }

    // Retrieve informbtion bbout extension bttributes
    privbte nbtive int getNumGcExtAttributes(GbrbbgeCollectorMXBebn gc);
    privbte nbtive void fillGcAttributeInfo(GbrbbgeCollectorMXBebn gc,
                                            int numAttributes,
                                            String[] bttributeNbmes,
                                            chbr[] types,
                                            String[] descriptions);

    /**
     * Returns the lbst GcInfo
     *
     * @pbrbm gc GbrbbgeCollectorMXBebn thbt the gc info is bssocibted with.
     * @pbrbm numExtAtts number of extension bttributes
     * @pbrbm extAttVblues Vblues of extension bttributes to be filled.
     * @pbrbm before Memory usbge before GC to be filled.
     * @pbrbm bfter Memory usbge bfter GC to be filled.
     */
    privbte nbtive GcInfo getLbstGcInfo0(GbrbbgeCollectorMXBebn gc,
                                         int numExtAtts,
                                         Object[] extAttVblues,
                                         chbr[] extAttTypes,
                                         MemoryUsbge[] before,
                                         MemoryUsbge[] bfter);
}
