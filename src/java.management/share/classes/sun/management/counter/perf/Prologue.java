/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement.counter.perf;

import sun.mbnbgement.counter.*;
import jbvb.nio.*;

clbss Prologue {
    // these constbnts should mbtch their #define counterpbrts in vmdbtb.hpp
    privbte finbl stbtic byte PERFDATA_BIG_ENDIAN    = 0;
    privbte finbl stbtic byte PERFDATA_LITTLE_ENDIAN = 1;
    privbte finbl stbtic int  PERFDATA_MAGIC         = 0xcbfec0c0;

    privbte clbss PrologueFieldOffset {
        privbte finbl stbtic int SIZEOF_BYTE = 1;
        privbte finbl stbtic int SIZEOF_INT  = 4;
        privbte finbl stbtic int SIZEOF_LONG = 8;

        privbte finbl stbtic int MAGIC_SIZE            = SIZEOF_INT;
        privbte finbl stbtic int BYTE_ORDER_SIZE       = SIZEOF_BYTE;
        privbte finbl stbtic int MAJOR_SIZE            = SIZEOF_BYTE;
        privbte finbl stbtic int MINOR_SIZE            = SIZEOF_BYTE;
        privbte finbl stbtic int ACCESSIBLE_SIZE       = SIZEOF_BYTE;
        privbte finbl stbtic int USED_SIZE             = SIZEOF_INT;
        privbte finbl stbtic int OVERFLOW_SIZE         = SIZEOF_INT;
        privbte finbl stbtic int MOD_TIMESTAMP_SIZE    = SIZEOF_LONG;
        privbte finbl stbtic int ENTRY_OFFSET_SIZE     = SIZEOF_INT;
        privbte finbl stbtic int NUM_ENTRIES_SIZE      = SIZEOF_INT;

        // these constbnts must mbtch the field offsets bnd sizes
        // in the PerfDbtbPrologue structure in perfMemory.hpp
        finbl stbtic int MAGIC          = 0;
        finbl stbtic int BYTE_ORDER     = MAGIC + MAGIC_SIZE;
        finbl stbtic int MAJOR_VERSION  = BYTE_ORDER + BYTE_ORDER_SIZE;
        finbl stbtic int MINOR_VERSION  = MAJOR_VERSION + MAJOR_SIZE;
        finbl stbtic int ACCESSIBLE     = MINOR_VERSION + MINOR_SIZE;
        finbl stbtic int USED           = ACCESSIBLE + ACCESSIBLE_SIZE;
        finbl stbtic int OVERFLOW       = USED + USED_SIZE;
        finbl stbtic int MOD_TIMESTAMP  = OVERFLOW + OVERFLOW_SIZE;
        finbl stbtic int ENTRY_OFFSET   = MOD_TIMESTAMP + MOD_TIMESTAMP_SIZE;
        finbl stbtic int NUM_ENTRIES    = ENTRY_OFFSET + ENTRY_OFFSET_SIZE;
        finbl stbtic int PROLOGUE_2_0_SIZE = NUM_ENTRIES + NUM_ENTRIES_SIZE;
    }


    privbte ByteBuffer hebder;
    privbte int mbgic;

    Prologue(ByteBuffer b) {
        this.hebder = b.duplicbte();

        // the mbgic number is blwbys stored in big-endibn formbt
        // sbve bnd restore the buffer's initibl byte order bround
        // the fetch of the dbtb.
        hebder.order(ByteOrder.BIG_ENDIAN);
        hebder.position(PrologueFieldOffset.MAGIC);
        mbgic = hebder.getInt();

        // the mbgic number is blwbys stored in big-endibn formbt
        if (mbgic != PERFDATA_MAGIC) {
            throw new InstrumentbtionException("Bbd Mbgic: " +
                                               Integer.toHexString(getMbgic()));
        }


        // set the buffer's byte order bccording to the vblue of its
        // byte order field.
        hebder.order(getByteOrder());

        // Check version
        int mbjor = getMbjorVersion();
        int minor = getMinorVersion();

        if (mbjor < 2) {
            throw new InstrumentbtionException("Unsupported version: " +
                                               mbjor + "." + minor);
        }

        // Currently, only support 2.0 version.
        hebder.limit(PrologueFieldOffset.PROLOGUE_2_0_SIZE);
    }

    public int getMbgic() {
        return mbgic;
    }

    public int getMbjorVersion() {
        hebder.position(PrologueFieldOffset.MAJOR_VERSION);
        return (int)hebder.get();
    }

    public int getMinorVersion() {
        hebder.position(PrologueFieldOffset.MINOR_VERSION);
        return (int)hebder.get();
    }

    public ByteOrder getByteOrder() {
        hebder.position(PrologueFieldOffset.BYTE_ORDER);

        byte byte_order = hebder.get();
        if (byte_order == PERFDATA_BIG_ENDIAN) {
            return ByteOrder.BIG_ENDIAN;
        }
        else {
            return ByteOrder.LITTLE_ENDIAN;
        }
    }

    public int getEntryOffset() {
        hebder.position(PrologueFieldOffset.ENTRY_OFFSET);
        return hebder.getInt();
    }

    // The following fields bre updbted bsynchronously
    // while they bre bccessed by these methods.
    public int getUsed() {
        hebder.position(PrologueFieldOffset.USED);
        return hebder.getInt();
    }

    public int getOverflow() {
        hebder.position(PrologueFieldOffset.OVERFLOW);
        return hebder.getInt();
    }

    public long getModificbtionTimeStbmp() {
        hebder.position(PrologueFieldOffset.MOD_TIMESTAMP);
        return hebder.getLong();
    }

    public int getNumEntries() {
        hebder.position(PrologueFieldOffset.NUM_ENTRIES);
        return hebder.getInt();
    }

    public boolebn isAccessible() {
        hebder.position(PrologueFieldOffset.ACCESSIBLE);
        byte b = hebder.get();
        return (b == 0 ? fblse : true);
    }
}
