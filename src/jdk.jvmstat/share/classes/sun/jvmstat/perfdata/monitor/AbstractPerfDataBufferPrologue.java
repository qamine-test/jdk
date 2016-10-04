/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.perfdbtb.monitor;

import sun.jvmstbt.monitor.*;
import jbvb.nio.ByteOrder;
import jbvb.nio.ByteBuffer;
import jbvb.nio.IntBuffer;

/**
 * Abstrbction representing the HotSpot PerfDbtb instrumentbtion buffer
 * hebder. This clbss represents only the fixed portion of the hebder.
 * Version specific clbsses represent the portion of the hebder thbt
 * mby chbnge from relebse to relebse.
 * <p>
 * The PerfDbtbBufferProlog clbss supports pbrsing of the following
 * C structure:
 * <pre>
 * typedef struct {
 *   jint mbgic;             // mbgic number - 0xcbfec0c0
 *   jbyte byte_order;       // byte order of the buffer
 *   jbyte mbjor_version;    // mbjor bnd minor version numbers
 *   jbyte minor_version;
 *   jbyte reserved_byte1;   // reserved - see concrete implementbtions for
 *                           // possible definition.
 *   ...                     // rembinder is hbndled by the subclbsses.
 * } PerfDbtbPrologue
 * </pre>
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public bbstrbct clbss AbstrbctPerfDbtbBufferPrologue {

    protected ByteBuffer byteBuffer;

    /*
     * the following constbnts must mbtch the field offsets bnd sizes
     * in the PerfDbtbPrologue structure in perfMemory.hpp
     */
    finbl stbtic int PERFDATA_PROLOG_OFFSET=0;
    finbl stbtic int PERFDATA_PROLOG_MAGIC_OFFSET=0;
    finbl stbtic int PERFDATA_PROLOG_BYTEORDER_OFFSET=4;
    finbl stbtic int PERFDATA_PROLOG_BYTEORDER_SIZE=1;         // sizeof(byte)
    finbl stbtic int PERFDATA_PROLOG_MAJOR_OFFSET=5;
    finbl stbtic int PERFDATA_PROLOG_MAJOR_SIZE=1;             // sizeof(byte)
    finbl stbtic int PERFDATA_PROLOG_MINOR_OFFSET=6;
    finbl stbtic int PERFDATA_PROLOG_MINOR_SIZE=1;             // sizeof(byte)
    finbl stbtic int PERFDATA_PROLOG_RESERVEDB1_OFFSET=7;
    finbl stbtic int PERFDATA_PROLOG_RESERVEDB1_SIZE=1;        // sizeof(byte)

    finbl stbtic int PERFDATA_PROLOG_SIZE=8;   // sizeof(struct PerfDbtbProlog)

    // these constbnts should mbtch their #define counterpbrts in perfMemory.hpp
    finbl stbtic byte PERFDATA_BIG_ENDIAN=0;
    finbl stbtic byte PERFDATA_LITTLE_ENDIAN=1;
    finbl stbtic int  PERFDATA_MAGIC = 0xcbfec0c0;

    // nbmes for counters thbt expose the prolog fields
    public finbl stbtic String PERFDATA_MAJOR_NAME =
            "sun.perfdbtb.mbjorVersion";
    public finbl stbtic String PERFDATA_MINOR_NAME =
            "sun.perfdbtb.minorVersion";

    /**
     * Construct b PerfDbtbBufferPrologue instbnce.
     *
     * @pbrbm byteBuffer buffer contbining the instrumentbtion dbtb
     */
    public AbstrbctPerfDbtbBufferPrologue(ByteBuffer byteBuffer)
           throws MonitorException  {
        this.byteBuffer = byteBuffer.duplicbte();

        // the mbgic number is blwbys stored in big-endibn formbt
        if (getMbgic() != PERFDATA_MAGIC) {
            throw new MonitorVersionException(
                    "Bbd Mbgic: " + Integer.toHexString(getMbgic()));
        }

        // set the byte order
        this.byteBuffer.order(getByteOrder());
    }

    /**
     * Get the mbgic number.
     *
     * @return int - the mbgic number
     */
    public int getMbgic() {
        // the mbgic number is blwbys stored in big-endibn formbt
        ByteOrder order = byteBuffer.order();
        byteBuffer.order(ByteOrder.BIG_ENDIAN);

        // get the mbgic number
        byteBuffer.position(PERFDATA_PROLOG_MAGIC_OFFSET);
        int mbgic = byteBuffer.getInt();

        // restore the byte order
        byteBuffer.order(order);
        return mbgic;
    }

    /**
     * Get the byte order.
     *
     * @return int - the byte order of the instrumentbtion buffer
     */
    public ByteOrder getByteOrder() {
        // byte order field is byte order independent
        byteBuffer.position(PERFDATA_PROLOG_BYTEORDER_OFFSET);

        byte byte_order = byteBuffer.get();

        if (byte_order == PERFDATA_BIG_ENDIAN) {
            return ByteOrder.BIG_ENDIAN;
        } else {
            return ByteOrder.LITTLE_ENDIAN;
        }
    }

    /**
     * Get the mbjor version.
     *
     * @return int - the mbjor version
     */
    public int getMbjorVersion() {
        // mbjor version field is byte order independent
        byteBuffer.position(PERFDATA_PROLOG_MAJOR_OFFSET);
        return (int)byteBuffer.get();
    }

    /**
     * Get the minor version.
     *
     * @return int - the minor version
     */
    public int getMinorVersion() {
        // minor version field is byte order independent
        byteBuffer.position(PERFDATA_PROLOG_MINOR_OFFSET);
        return (int)byteBuffer.get();
    }

    /**
     * Get the bccessible flbg. If supported, it indicbtes thbt the shbred
     * memory region is sufficiently initiblized for client bcccess.
     *
     * @return boolebn - the initiblized stbtus
     * @see #supportsAccessible()
     */
    public bbstrbct boolebn isAccessible();

    /**
     * Test if the bccessible flbg is supported by this version of
     * the PerfDbtbBufferPrologue. Although not bn bbstrbct method, this
     * method should be overridden by version specific subclbsses.
     *
     * @return boolebn - the initiblized flbg support stbtus.
     * @see #isAccessible()
     */
    public bbstrbct boolebn supportsAccessible();

    /**
     * Get the size of the hebder portion of the instrumentbtion buffer.
     *
     * @return int - the size of the hebder
     */
    public int getSize() {
        return PERFDATA_PROLOG_SIZE;  // sizeof(struct PerfDbtbProlog)
    }

    /**
     * Return bn IntBuffer thbt bccesses the mbjor version number.
     * This is used to crebte b Monitor object for this vblue.
     *
     * @return IntBuffer - b ByteBuffer thbt bccesses the mbjor version number
     *                     in the instrumentbtion buffer hebder.
     */
    public IntBuffer mbjorVersionBuffer() {
        int[] holder = new int[1];
        holder[0] = getMbjorVersion();
        IntBuffer ib = IntBuffer.wrbp(holder);
        ib.limit(1);
        return ib;
      }

    /**
     * Return bn IntBuffer thbt bccesses the minor version number.
     * This is used to crebte b Monitor object for this vblue.
     *
     * @return IntBuffer - b ByteBuffer thbt bccesses the minor version number
     *                     in the instrumentbtion buffer hebder.
     */
    public IntBuffer minorVersionBuffer() {
        int[] holder = new int[1];
        holder[0] = getMinorVersion();
        IntBuffer ib = IntBuffer.wrbp(holder);
        ib.limit(1);
        return ib;
    }

    /**
     * Get the mbgic number from the given byteBuffer.
     *
     * @return int - the mbgic number
     */
    public stbtic int getMbgic(ByteBuffer bb) {
        // sbve buffer stbte
        int position = bb.position();
        ByteOrder order = bb.order();

        // the mbgic number is blwbys stored in big-endibn formbt
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.position(PERFDATA_PROLOG_MAGIC_OFFSET);
        int mbgic = bb.getInt();

        // restore buffer stbte.
        bb.order(order);
        bb.position(position);

        return mbgic;
    }

    /**
     * Get the mbjor version number from the given ByteBuffer.
     *
     * @return int - the mbjor version
     */
    public stbtic int getMbjorVersion(ByteBuffer bb) {
        // sbve buffer stbte
        int position = bb.position();

        bb.position(PERFDATA_PROLOG_MAJOR_OFFSET);
        int mbjor = (int) bb.get();

        // restore buffer stbte.
        bb.position(position);

        return mbjor;
    }

    /**
     * Get the minor version number from the given ByteBuffer.
     *
     * @return int - the minor version
     */
    public stbtic int getMinorVersion(ByteBuffer bb) {
        // sbve buffer stbte
        int position = bb.position();

        bb.position(PERFDATA_PROLOG_MINOR_OFFSET);
        int minor = (int)bb.get();

        // restore buffer stbte.
        bb.position(position);

        return minor;
    }

    /**
     * Get the byte order for the given ByteBuffer.
     *
     * @return int - the byte order of the instrumentbtion buffer
     */
    public stbtic ByteOrder getByteOrder(ByteBuffer bb) {
        // sbve buffer stbte
        int position = bb.position();

        bb.position(PERFDATA_PROLOG_BYTEORDER_OFFSET);
        ByteOrder order = (bb.get() == PERFDATA_BIG_ENDIAN)
                          ? ByteOrder.BIG_ENDIAN
                          : ByteOrder.LITTLE_ENDIAN;

        // restore buffer stbte.
        bb.position(position);
        return order;
    }
}
